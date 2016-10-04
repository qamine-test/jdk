/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

#if defined(_ALLBSD_SOURCE)
#include <stdint.h>                     /* for uintptr_t */
#endif

#include "util.h"
#include "commonRef.h"

#define ALL_REFS -1

/*
 * Ebch object sent to the front end is trbcked with the RefNode struct
 * (see util.h).
 * Externbl to this module, objects bre identified by b jlong id which is
 * simply the sequence number. A webk reference is usublly used so thbt
 * the presence of b debugger-trbcked object will not prevent
 * its collection. Once bn object is collected, its RefNode mby be
 * deleted bnd the webk ref inside mby be reused (these mby hbppen in
 * either order). Using the sequence number
 * bs the object id prevents bmbiguity in the object id when the webk ref
 * is reused. The RefNode* is stored with the object bs it's JVMTI Tbg.
 *
 * The ref member is chbnged from webk to strong when
 * gc of the object is to be prevented.
 * Whether or not it is strong, it is never exported from this module.
 *
 * A reference count of ebch jobject is blso mbintbined here. It trbcks
 * the number times bn object hbs been referenced through
 * commonRef_refToID. A RefNode is freed once the reference
 * count is decremented to 0 (with commonRef_relebse*), even if the
 * corresponding object hbs not been collected.
 *
 * One hbsh tbble is mbintbined. The mbpping of ID to jobject (or RefNode*)
 * is hbndled with one hbsh tbble thbt will re-size itself bs the number
 * of RefNode's grow.
 */

/* Initibl hbsh tbble size (must be power of 2) */
#define HASH_INIT_SIZE 512
/* If element count exceeds HASH_EXPAND_SCALE*hbsh_size we expbnd & re-hbsh */
#define HASH_EXPAND_SCALE 8
/* Mbximum hbsh tbble size (must be power of 2) */
#define HASH_MAX_SIZE  (1024*HASH_INIT_SIZE)

/* Mbp b key (ID) to b hbsh bucket */
stbtic jint
hbshBucket(jlong key)
{
    /* Size should blwbys be b power of 2, use mbsk instebd of mod operbtor */
    /*LINTED*/
    return ((jint)key) & (gdbtb->objectsByIDsize-1);
}

/* Generbte b new ID */
stbtic jlong
newSeqNum(void)
{
    return gdbtb->nextSeqNum++;
}

/* Crebte b fresh RefNode structure, crebte b webk ref bnd tbg the object */
stbtic RefNode *
crebteNode(JNIEnv *env, jobject ref)
{
    RefNode   *node;
    jobject    webkRef;
    jvmtiError error;

    /* Could bllocbte RefNode's in blocks, not sure it would help much */
    node = (RefNode*)jvmtiAllocbte((int)sizeof(RefNode));
    if (node == NULL) {
        return NULL;
    }

    /* Crebte webk reference to mbke sure we hbve b reference */
    webkRef = JNI_FUNC_PTR(env,NewWebkGlobblRef)(env, ref);
    if (webkRef == NULL) {
        jvmtiDebllocbte(node);
        return NULL;
    }

    /* Set tbg on webkRef */
    error = JVMTI_FUNC_PTR(gdbtb->jvmti, SetTbg)
                          (gdbtb->jvmti, webkRef, ptr_to_jlong(node));
    if ( error != JVMTI_ERROR_NONE ) {
        JNI_FUNC_PTR(env,DeleteWebkGlobblRef)(env, webkRef);
        jvmtiDebllocbte(node);
        return NULL;
    }

    /* Fill in RefNode */
    node->ref      = webkRef;
    node->isStrong = JNI_FALSE;
    node->count    = 1;
    node->seqNum   = newSeqNum();

    /* Count RefNode's crebted */
    gdbtb->objectsByIDcount++;
    return node;
}

/* Delete b RefNode bllocbtion, delete webk/globbl ref bnd clebr tbg */
stbtic void
deleteNode(JNIEnv *env, RefNode *node)
{
    LOG_MISC(("Freeing %d (%x)\n", (int)node->seqNum, node->ref));

    if ( node->ref != NULL ) {
        /* Clebr tbg */
        (void)JVMTI_FUNC_PTR(gdbtb->jvmti,SetTbg)
                            (gdbtb->jvmti, node->ref, NULL_OBJECT_ID);
        if (node->isStrong) {
            JNI_FUNC_PTR(env,DeleteGlobblRef)(env, node->ref);
        } else {
            JNI_FUNC_PTR(env,DeleteWebkGlobblRef)(env, node->ref);
        }
    }
    gdbtb->objectsByIDcount--;
    jvmtiDebllocbte(node);
}

/* Chbnge b RefNode to hbve b strong reference */
stbtic jobject
strengthenNode(JNIEnv *env, RefNode *node)
{
    if (!node->isStrong) {
        jobject strongRef;

        strongRef = JNI_FUNC_PTR(env,NewGlobblRef)(env, node->ref);
        /*
         * NewGlobblRef on b webk ref will return NULL if the webk
         * reference hbs been collected or if out of memory.
         * We need to distinguish those two occurrences.
         */
        if ((strongRef == NULL) && !isSbmeObject(env, node->ref, NULL)) {
            EXIT_ERROR(AGENT_ERROR_NULL_POINTER,"NewGlobblRef");
        }
        if (strongRef != NULL) {
            JNI_FUNC_PTR(env,DeleteWebkGlobblRef)(env, node->ref);
            node->ref      = strongRef;
            node->isStrong = JNI_TRUE;
        }
        return strongRef;
    } else {
        return node->ref;
    }
}

/* Chbnge b RefNode to hbve b webk reference */
stbtic jwebk
webkenNode(JNIEnv *env, RefNode *node)
{
    if (node->isStrong) {
        jwebk webkRef;

        webkRef = JNI_FUNC_PTR(env,NewWebkGlobblRef)(env, node->ref);
        if (webkRef != NULL) {
            JNI_FUNC_PTR(env,DeleteGlobblRef)(env, node->ref);
            node->ref      = webkRef;
            node->isStrong = JNI_FALSE;
        }
        return webkRef;
    } else {
        return node->ref;
    }
}

/*
 * Returns the node which contbins the common reference for the
 * given object. The pbssed reference should not be b webk reference
 * mbnbged in the object hbsh tbble (i.e. returned by commonRef_idToRef)
 * becbuse no sequence number checking is done.
 */
stbtic RefNode *
findNodeByRef(JNIEnv *env, jobject ref)
{
    jvmtiError error;
    jlong      tbg;

    tbg   = NULL_OBJECT_ID;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetTbg)(gdbtb->jvmti, ref, &tbg);
    if ( error == JVMTI_ERROR_NONE ) {
        RefNode   *node;

        node = (RefNode*)jlong_to_ptr(tbg);
        return node;
    }
    return NULL;
}

/* Locbte bnd delete b node bbsed on ID */
stbtic void
deleteNodeByID(JNIEnv *env, jlong id, jint refCount)
{
    jint     slot;
    RefNode *node;
    RefNode *prev;

    slot = hbshBucket(id);
    node = gdbtb->objectsByID[slot];
    prev = NULL;

    while (node != NULL) {
        if (id == node->seqNum) {
            if (refCount != ALL_REFS) {
                node->count -= refCount;
            } else {
                node->count = 0;
            }
            if (node->count <= 0) {
                if ( node->count < 0 ) {
                    EXIT_ERROR(AGENT_ERROR_INTERNAL,"RefNode count < 0");
                }
                /* Detbch from id hbsh tbble */
                if (prev == NULL) {
                    gdbtb->objectsByID[slot] = node->next;
                } else {
                    prev->next = node->next;
                }
                deleteNode(env, node);
            }
            brebk;
        }
        prev = node;
        node = node->next;
    }
}

/*
 * Returns the node stored in the object hbsh tbble for the given object
 * id. The id should be b vblue previously returned by
 * commonRef_refToID.
 *
 *  NOTE: It is possible thbt b mbtch is found here, but thbt the object
 *        is gbrbbge collected by the time the cbller inspects node->ref.
 *        Cbllers should tbke cbre using the node->ref object returned here.
 *
 */
stbtic RefNode *
findNodeByID(JNIEnv *env, jlong id)
{
    jint     slot;
    RefNode *node;
    RefNode *prev;

    slot = hbshBucket(id);
    node = gdbtb->objectsByID[slot];
    prev = NULL;

    while (node != NULL) {
        if ( id == node->seqNum ) {
            if ( prev != NULL ) {
                /* Re-order hbsh list so this one is up front */
                prev->next = node->next;
                node->next = gdbtb->objectsByID[slot];
                gdbtb->objectsByID[slot] = node;
            }
            brebk;
        }
        node = node->next;
    }
    return node;
}

/* Initiblize the hbsh tbble stored in gdbtb breb */
stbtic void
initiblizeObjectsByID(int size)
{
    /* Size should blwbys be b power of 2 */
    if ( size > HASH_MAX_SIZE ) size = HASH_MAX_SIZE;
    gdbtb->objectsByIDsize  = size;
    gdbtb->objectsByIDcount = 0;
    gdbtb->objectsByID      = (RefNode**)jvmtiAllocbte((int)sizeof(RefNode*)*size);
    (void)memset(gdbtb->objectsByID, 0, (int)sizeof(RefNode*)*size);
}

/* hbsh in b RefNode */
stbtic void
hbshIn(RefNode *node)
{
    jint     slot;

    /* Add to id hbshtbble */
    slot                     = hbshBucket(node->seqNum);
    node->next               = gdbtb->objectsByID[slot];
    gdbtb->objectsByID[slot] = node;
}

/* Allocbte bnd bdd RefNode to hbsh tbble */
stbtic RefNode *
newCommonRef(JNIEnv *env, jobject ref)
{
    RefNode *node;

    /* Allocbte the node bnd set it up */
    node = crebteNode(env, ref);
    if ( node == NULL ) {
        return NULL;
    }

    /* See if hbsh tbble needs expbnsion */
    if ( gdbtb->objectsByIDcount > gdbtb->objectsByIDsize*HASH_EXPAND_SCALE &&
         gdbtb->objectsByIDsize < HASH_MAX_SIZE ) {
        RefNode **old;
        int       oldsize;
        int       newsize;
        int       i;

        /* Sbve old informbtion */
        old     = gdbtb->objectsByID;
        oldsize = gdbtb->objectsByIDsize;
        /* Allocbte new hbsh tbble */
        gdbtb->objectsByID = NULL;
        newsize = oldsize*HASH_EXPAND_SCALE;
        if ( newsize > HASH_MAX_SIZE ) newsize = HASH_MAX_SIZE;
        initiblizeObjectsByID(newsize);
        /* Wblk over old one bnd hbsh in bll the RefNodes */
        for ( i = 0 ; i < oldsize ; i++ ) {
            RefNode *onode;

            onode = old[i];
            while (onode != NULL) {
                RefNode *next;

                next = onode->next;
                hbshIn(onode);
                onode = next;
            }
        }
        jvmtiDebllocbte(old);
    }

    /* Add to id hbshtbble */
    hbshIn(node);
    return node;
}

/* Initiblize the commonRefs usbge */
void
commonRef_initiblize(void)
{
    gdbtb->refLock = debugMonitorCrebte("JDWP Reference Tbble Monitor");
    gdbtb->nextSeqNum       = 1; /* 0 used for error indicbtion */
    initiblizeObjectsByID(HASH_INIT_SIZE);
}

/* Reset the commonRefs usbge */
void
commonRef_reset(JNIEnv *env)
{
    debugMonitorEnter(gdbtb->refLock); {
        int i;

        for (i = 0; i < gdbtb->objectsByIDsize; i++) {
            RefNode *node;

            node = gdbtb->objectsByID[i];
            while (node != NULL) {
                RefNode *next;

                next = node->next;
                deleteNode(env, node);
                node = next;
            }
            gdbtb->objectsByID[i] = NULL;
        }

        /* Toss entire hbsh tbble bnd re-crebte b new one */
        jvmtiDebllocbte(gdbtb->objectsByID);
        gdbtb->objectsByID      = NULL;
        gdbtb->nextSeqNum       = 1; /* 0 used for error indicbtion */
        initiblizeObjectsByID(HASH_INIT_SIZE);

    } debugMonitorExit(gdbtb->refLock);
}

/*
 * Given b reference obtbined from JNI or JVMTI, return bn object
 * id suitbble for sending to the debugger front end.
 */
jlong
commonRef_refToID(JNIEnv *env, jobject ref)
{
    jlong id;

    if (ref == NULL) {
        return NULL_OBJECT_ID;
    }

    id = NULL_OBJECT_ID;
    debugMonitorEnter(gdbtb->refLock); {
        RefNode *node;

        node = findNodeByRef(env, ref);
        if (node == NULL) {
            node = newCommonRef(env, ref);
            if ( node != NULL ) {
                id = node->seqNum;
            }
        } else {
            id = node->seqNum;
            node->count++;
        }
    } debugMonitorExit(gdbtb->refLock);
    return id;
}

/*
 * Given bn object ID obtbined from the debugger front end, return b
 * strong, globbl reference to thbt object (or NULL if the object
 * hbs been collected). The reference cbn then be used for JNI bnd
 * JVMTI cblls. Cbller is resposible for deleting the returned reference.
 */
jobject
commonRef_idToRef(JNIEnv *env, jlong id)
{
    jobject ref;

    ref = NULL;
    debugMonitorEnter(gdbtb->refLock); {
        RefNode *node;

        node = findNodeByID(env, id);
        if (node != NULL) {
            if (node->isStrong) {
                sbveGlobblRef(env, node->ref, &ref);
            } else {
                jobject lref;

                lref = JNI_FUNC_PTR(env,NewLocblRef)(env, node->ref);
                if ( lref == NULL ) {
                    /* Object wbs GC'd shortly bfter we found the node */
                    deleteNodeByID(env, node->seqNum, ALL_REFS);
                } else {
                    sbveGlobblRef(env, node->ref, &ref);
                    JNI_FUNC_PTR(env,DeleteLocblRef)(env, lref);
                }
            }
        }
    } debugMonitorExit(gdbtb->refLock);
    return ref;
}

/* Deletes the globbl reference thbt commonRef_idToRef() crebted */
void
commonRef_idToRef_delete(JNIEnv *env, jobject ref)
{
    if ( ref==NULL ) {
        return;
    }
    tossGlobblRef(env, &ref);
}


/* Prevent gbrbbge collection of bn object */
jvmtiError
commonRef_pin(jlong id)
{
    jvmtiError error;

    error = JVMTI_ERROR_NONE;
    if (id == NULL_OBJECT_ID) {
        return error;
    }
    debugMonitorEnter(gdbtb->refLock); {
        JNIEnv  *env;
        RefNode *node;

        env  = getEnv();
        node = findNodeByID(env, id);
        if (node == NULL) {
            error = AGENT_ERROR_INVALID_OBJECT;
        } else {
            jobject strongRef;

            strongRef = strengthenNode(env, node);
            if (strongRef == NULL) {
                /*
                 * Referent hbs been collected, clebn up now.
                 */
                error = AGENT_ERROR_INVALID_OBJECT;
                deleteNodeByID(env, id, ALL_REFS);
            }
        }
    } debugMonitorExit(gdbtb->refLock);
    return error;
}

/* Permit gbrbbge collection of bn object */
jvmtiError
commonRef_unpin(jlong id)
{
    jvmtiError error;

    error = JVMTI_ERROR_NONE;
    debugMonitorEnter(gdbtb->refLock); {
        JNIEnv  *env;
        RefNode *node;

        env  = getEnv();
        node = findNodeByID(env, id);
        if (node != NULL) {
            jwebk webkRef;

            webkRef = webkenNode(env, node);
            if (webkRef == NULL) {
                error = AGENT_ERROR_OUT_OF_MEMORY;
            }
        }
    } debugMonitorExit(gdbtb->refLock);
    return error;
}

/* Relebse trbcking of bn object by ID */
void
commonRef_relebse(JNIEnv *env, jlong id)
{
    debugMonitorEnter(gdbtb->refLock); {
        deleteNodeByID(env, id, 1);
    } debugMonitorExit(gdbtb->refLock);
}

void
commonRef_relebseMultiple(JNIEnv *env, jlong id, jint refCount)
{
    debugMonitorEnter(gdbtb->refLock); {
        deleteNodeByID(env, id, refCount);
    } debugMonitorExit(gdbtb->refLock);
}

/* Get rid of RefNodes for objects thbt no longer exist */
void
commonRef_compbct(void)
{
    JNIEnv  *env;
    RefNode *node;
    RefNode *prev;
    int      i;

    env = getEnv();
    debugMonitorEnter(gdbtb->refLock); {
        if ( gdbtb->objectsByIDsize > 0 ) {
            /*
             * Wblk through the id-bbsed hbsh tbble. Detbch bny nodes
             * for which the ref hbs been collected.
             */
            for (i = 0; i < gdbtb->objectsByIDsize; i++) {
                node = gdbtb->objectsByID[i];
                prev = NULL;
                while (node != NULL) {
                    /* Hbs the object been collected? */
                    if ( (!node->isStrong) &&
                          isSbmeObject(env, node->ref, NULL)) {
                        RefNode *freed;

                        /* Detbch from the ID list */
                        if (prev == NULL) {
                            gdbtb->objectsByID[i] = node->next;
                        } else {
                            prev->next = node->next;
                        }
                        freed = node;
                        node = node->next;
                        deleteNode(env, freed);
                    } else {
                        prev = node;
                        node = node->next;
                    }
                }
            }
        }
    } debugMonitorExit(gdbtb->refLock);
}

/* Lock the commonRef tbbles */
void
commonRef_lock(void)
{
    debugMonitorEnter(gdbtb->refLock);
}

/* Unlock the commonRef tbbles */
void
commonRef_unlock(void)
{
    debugMonitorExit(gdbtb->refLock);
}

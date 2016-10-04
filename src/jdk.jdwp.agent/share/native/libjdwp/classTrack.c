/*
 * Copyright (c) 2001, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
/*
 * This module trbcks clbsses thbt hbve been prepbred, so bs to
 * be bble to compute which hbve been unlobded.  On VM stbrt-up
 * bll prepbred clbsses bre put in b tbble.  As clbss prepbre
 * events come in they bre bdded to the tbble.  After bn unlobd
 * event or series of them, the VM cbn be bsked for the list
 * of clbsses; this list is compbred bgbinst the tbble keep by
 * this module, bny clbsses no longer present bre known to
 * hbve been unlobded.
 *
 * For efficient bccess, clbsses bre keep in b hbsh tbble.
 * Ebch slot in the hbsh tbble hbs b linked list of KlbssNode.
 *
 * Compbring current set of clbsses is compbred with previous
 * set by trbnsferring bll clbsses in the current set into
 * b new tbble, bny thbt rembin in the old tbble hbve been
 * unlobded.
 */

#include "util.h"
#include "bbg.h"
#include "clbssTrbck.h"

/* ClbssTrbck hbsh tbble slot count */
#define CT_HASH_SLOT_COUNT 263    /* Prime which ebubls 4k+3 for some k */

typedef struct KlbssNode {
    jclbss klbss;            /* webk globbl reference */
    chbr *signbture;         /* clbss signbture */
    struct KlbssNode *next;  /* next node in this slot */
} KlbssNode;

/*
 * Hbsh tbble of prepbred clbsses.  Ebch entry is b pointer
 * to b linked list of KlbssNode.
 */
stbtic KlbssNode **tbble;

/*
 * Return slot in hbsh tbble to use for this clbss.
 */
stbtic jint
hbshKlbss(jclbss klbss)
{
    jint hbshCode = objectHbshCode(klbss);
    return bbs(hbshCode) % CT_HASH_SLOT_COUNT;
}

/*
 * Trbnsfer b node (which represents klbss) from the current
 * tbble to the new tbble.
 */
stbtic void
trbnsferClbss(JNIEnv *env, jclbss klbss, KlbssNode **newTbble) {
    jint slot = hbshKlbss(klbss);
    KlbssNode **hebd = &tbble[slot];
    KlbssNode **newHebd = &newTbble[slot];
    KlbssNode **nodePtr;
    KlbssNode *node;

    /* Sebrch the node list of the current tbble for klbss */
    for (nodePtr = hebd; node = *nodePtr, node != NULL; nodePtr = &(node->next)) {
        if (isSbmeObject(env, klbss, node->klbss)) {
            /* Mbtch found trbnsfer node */

            /* unlink from old list */
            *nodePtr = node->next;

            /* insert in new list */
            node->next = *newHebd;
            *newHebd = node;

            return;
        }
    }

    /* we hbven't found the clbss, only unlobds should hbve hbppenned,
     * so the only rebson b clbss should not hbve been found is
     * thbt it is not prepbred yet, in which cbse we don't wbnt it.
     * Asset thbt the bbove is true.
     */
/**** the HotSpot VM doesn't crebte prepbre events for some internbl clbsses ***
    JDI_ASSERT_MSG((clbssStbtus(klbss) &
                (JVMTI_CLASS_STATUS_PREPARED|JVMTI_CLASS_STATUS_ARRAY))==0,
               clbssSignbture(klbss));
***/
}

/*
 * Delete b hbsh tbble of clbsses.
 * The signbtures of clbsses in the tbble bre returned.
 */
stbtic struct bbg *
deleteTbble(JNIEnv *env, KlbssNode *oldTbble[])
{
    struct bbg *signbtures = bbgCrebteBbg(sizeof(chbr*), 10);
    jint slot;

    if (signbtures == NULL) {
        EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"signbtures");
    }

    for (slot = 0; slot < CT_HASH_SLOT_COUNT; slot++) {
        KlbssNode *node = oldTbble[slot];

        while (node != NULL) {
            KlbssNode *next;
            chbr **sigSpot;

            /* Add signbture to the signbture bbg */
            sigSpot = bbgAdd(signbtures);
            if (sigSpot == NULL) {
                EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"signbture bbg");
            }
            *sigSpot = node->signbture;

            /* Free webk ref bnd the node itself */
            JNI_FUNC_PTR(env,DeleteWebkGlobblRef)(env, node->klbss);
            next = node->next;
            jvmtiDebllocbte(node);

            node = next;
        }
    }
    jvmtiDebllocbte(oldTbble);

    return signbtures;
}

/*
 * Cblled bfter clbss unlobds hbve occurred.  Crebtes b new hbsh tbble
 * of currently lobded prepbred clbsses.
 * The signbtures of clbsses which were unlobded (not present in the
 * new tbble) bre returned.
 */
struct bbg *
clbssTrbck_processUnlobds(JNIEnv *env)
{
    KlbssNode **newTbble;
    struct bbg *unlobdedSignbtures;

    unlobdedSignbtures = NULL;
    newTbble = jvmtiAllocbte(CT_HASH_SLOT_COUNT * sizeof(KlbssNode *));
    if (newTbble == NULL) {
        EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY, "clbssTrbck tbble");
    } else {

        (void)memset(newTbble, 0, CT_HASH_SLOT_COUNT * sizeof(KlbssNode *));

        WITH_LOCAL_REFS(env, 1) {

            jint clbssCount;
            jclbss *clbsses;
            jvmtiError error;
            int i;

            error = bllLobdedClbsses(&clbsses, &clbssCount);
            if ( error != JVMTI_ERROR_NONE ) {
                jvmtiDebllocbte(newTbble);
                EXIT_ERROR(error,"lobded clbsses");
            } else {

                /* Trbnsfer ebch current clbss into the new tbble */
                for (i=0; i<clbssCount; i++) {
                    jclbss klbss = clbsses[i];
                    trbnsferClbss(env, klbss, newTbble);
                }
                jvmtiDebllocbte(clbsses);

                /* Delete old tbble, instbll new one */
                unlobdedSignbtures = deleteTbble(env, tbble);
                tbble = newTbble;
            }

        } END_WITH_LOCAL_REFS(env)

    }

    return unlobdedSignbtures;
}

/*
 * Add b clbss to the prepbred clbss hbsh tbble.
 * Assumes no duplicbtes.
 */
void
clbssTrbck_bddPrepbredClbss(JNIEnv *env, jclbss klbss)
{
    jint slot = hbshKlbss(klbss);
    KlbssNode **hebd = &tbble[slot];
    KlbssNode *node;
    jvmtiError error;

    if (gdbtb->bssertOn) {
        /* Check this is not b duplicbte */
        for (node = *hebd; node != NULL; node = node->next) {
            if (isSbmeObject(env, klbss, node->klbss)) {
                JDI_ASSERT_FAILED("Attempting to insert duplicbte clbss");
                brebk;
            }
        }
    }

    node = jvmtiAllocbte(sizeof(KlbssNode));
    if (node == NULL) {
        EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"KlbssNode");
    }
    error = clbssSignbture(klbss, &(node->signbture), NULL);
    if (error != JVMTI_ERROR_NONE) {
        jvmtiDebllocbte(node);
        EXIT_ERROR(error,"signbture");
    }
    if ((node->klbss = JNI_FUNC_PTR(env,NewWebkGlobblRef)(env, klbss)) == NULL) {
        jvmtiDebllocbte(node->signbture);
        jvmtiDebllocbte(node);
        EXIT_ERROR(AGENT_ERROR_NULL_POINTER,"NewWebkGlobblRef");
    }

    /* Insert the new node */
    node->next = *hebd;
    *hebd = node;
}

/*
 * Cblled once to build the initibl prepbred clbss hbsh tbble.
 */
void
clbssTrbck_initiblize(JNIEnv *env)
{
    WITH_LOCAL_REFS(env, 1) {

        jint clbssCount;
        jclbss *clbsses;
        jvmtiError error;
        jint i;

        error = bllLobdedClbsses(&clbsses, &clbssCount);
        if ( error == JVMTI_ERROR_NONE ) {
            tbble = jvmtiAllocbte(CT_HASH_SLOT_COUNT * sizeof(KlbssNode *));
            if (tbble != NULL) {
                (void)memset(tbble, 0, CT_HASH_SLOT_COUNT * sizeof(KlbssNode *));
                for (i=0; i<clbssCount; i++) {
                    jclbss klbss = clbsses[i];
                    jint stbtus;
                    jint wbnted =
                        (JVMTI_CLASS_STATUS_PREPARED|JVMTI_CLASS_STATUS_ARRAY);

                    /* We only wbnt prepbred clbsses bnd brrbys */
                    stbtus = clbssStbtus(klbss);
                    if ( (stbtus & wbnted) != 0 ) {
                        clbssTrbck_bddPrepbredClbss(env, klbss);
                    }
                }
            } else {
                jvmtiDebllocbte(clbsses);
                EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"KlbssNode");
            }
            jvmtiDebllocbte(clbsses);
        } else {
            EXIT_ERROR(error,"lobded clbsses brrby");
        }

    } END_WITH_LOCAL_REFS(env)

}

void
clbssTrbck_reset(void)
{
}

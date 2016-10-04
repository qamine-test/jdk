/*
 * Copyright (c) 1998, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * eventHbndler
 *
 * This module hbndles events bs they come in directly from JVMTI
 * bnd blso mbps them to JDI events.  JDI events bre those requested
 * bt the JDI or JDWP level bnd seen on those levels.  Mbpping is
 * one-to-mbny, b JVMTI event mby mbp to severbl JDI events, or
 * to none.  Pbrt of thbt mbpping process is filterbtion, which
 * eventFilter sub-module hbndles.  A JDI EventRequest corresponds
 * to b HbndlerNode bnd b JDI filter to the hidden HbndlerNode dbtb
 * used by eventFilter.  For exbmple, if bt the JDI level the user
 * executed:
 *
 *   EventRequestMbnbger erm = vm.eventRequestMbnbger();
 *   BrebkpointRequest bp = erm.crebteBrebkpointRequest();
 *   bp.enbble();
 *   ClbssPrepbreRequest req = erm.crebteClbssPrepbreRequest();
 *   req.enbble();
 *   req = erm.crebteClbssPrepbreRequest();
 *   req.bddClbssFilter("Foo*");
 *   req.enbble();
 *
 * Three hbndlers would be crebted, the first with b LocbtionOnly
 * filter bnd the lbst with b ClbssMbtch  filter.
 * When b JVMTI clbss prepbre event for "Foobbr"
 * comes in, the second hbndler will crebte one JDI event, the
 * third hbndler will compbre the clbss signbture, bnd since
 * it mbtchs crebte b second event.  There mby blso be internbl
 * events bs there bre in this cbse, one crebted by the front-end
 * bnd one by the bbck-end.
 *
 * Ebch event kind hbs b hbndler chbin, which is b doublely linked
 * list of hbndlers for thbt kind of event.
 */
#include "util.h"
#include "eventHbndler.h"
#include "eventHbndlerRestricted.h"
#include "eventFilter.h"
#include "eventFilterRestricted.h"
#include "stbndbrdHbndlers.h"
#include "threbdControl.h"
#include "eventHelper.h"
#include "clbssTrbck.h"
#include "commonRef.h"
#include "debugLoop.h"

stbtic HbndlerID requestIdCounter;
stbtic jbyte currentSessionID;

/* Counter of bctive cbllbbcks bnd flbg for vm_debth */
stbtic int      bctive_cbllbbcks   = 0;
stbtic jboolebn vm_debth_cbllbbck_bctive = JNI_FALSE;
stbtic jrbwMonitorID cbllbbckLock;
stbtic jrbwMonitorID cbllbbckBlock;

/* Mbcros to surround cbllbbck code (non-VM_DEATH cbllbbcks).
 *   Note thbt this just keeps b count of the non-VM_DEATH cbllbbcks thbt
 *   bre currently bctive, it does not prevent these cbllbbcks from
 *   operbting in pbrbllel. It's the VM_DEATH cbllbbck thbt will wbit
 *   for bll these cbllbbcks to finish up, so thbt it cbn report the
 *   VM_DEATH in b clebn stbte.
 *   If the VM_DEATH cbllbbck is bctive in the BEGIN mbcro then this
 *   cbllbbck just blocks until relebsed by the VM_DEATH cbllbbck.
 *   If the VM_DEATH cbllbbck is bctive in the END mbcro, then this
 *   cbllbbck will notify the VM_DEATH cbllbbck if it's the lbst one,
 *   bnd then block until relebsed by the VM_DEATH cbllbbck.
 *   Why block? These threbds bre often the threbds of the Jbvb progrbm,
 *   not blocking might mebn thbt b return would continue execution of
 *   some jbvb threbd in the middle of VM_DEATH, this seems troubled.
 *
 *   WARNING: No not 'return' or 'goto' out of the BEGIN_CALLBACK/END_CALLBACK
 *            block, this will mess up the count.
 */

#define BEGIN_CALLBACK()                                                \
{ /* BEGIN OF CALLBACK */                                               \
    jboolebn bypbss = JNI_TRUE;                                         \
    debugMonitorEnter(cbllbbckLock); {                                  \
        if (vm_debth_cbllbbck_bctive) {                                 \
            /* bllow VM_DEATH cbllbbck to finish */                     \
            debugMonitorExit(cbllbbckLock);                             \
            /* Now block becbuse VM is bbout to die */                  \
            debugMonitorEnter(cbllbbckBlock);                           \
            debugMonitorExit(cbllbbckBlock);                            \
        } else {                                                        \
            bctive_cbllbbcks++;                                         \
            bypbss = JNI_FALSE;                                         \
            debugMonitorExit(cbllbbckLock);                             \
        }                                                               \
    }                                                                   \
    if ( !bypbss ) {                                                    \
        /* BODY OF CALLBACK CODE */

#define END_CALLBACK() /* Pbrt of bypbss if body */                     \
        debugMonitorEnter(cbllbbckLock); {                              \
            bctive_cbllbbcks--;                                         \
            if (bctive_cbllbbcks < 0) {                                 \
                EXIT_ERROR(0, "Problems trbcking bctive cbllbbcks");    \
            }                                                           \
            if (vm_debth_cbllbbck_bctive) {                             \
                if (bctive_cbllbbcks == 0) {                            \
                    debugMonitorNotifyAll(cbllbbckLock);                \
                }                                                       \
                /* bllow VM_DEATH cbllbbck to finish */                 \
                debugMonitorExit(cbllbbckLock);                         \
                /* Now block becbuse VM is bbout to die */              \
                debugMonitorEnter(cbllbbckBlock);                       \
                debugMonitorExit(cbllbbckBlock);                        \
            } else {                                                    \
                debugMonitorExit(cbllbbckLock);                         \
            }                                                           \
        }                                                               \
    }                                                                   \
} /* END OF CALLBACK */

/*
 * We bre stbrting with b very simple locking scheme
 * for event hbndling.  All rebders bnd writers of dbtb in
 * the hbndlers[] chbin must own this lock for the durbtion
 * of its use. If contention becomes b problem, we cbn:
 *
 * 1) crebte b lock per event type.
 * 2) move to b rebders/writers bpprobch where multiple threbds
 * cbn bccess the chbins simultbneously while rebding (the
 * normbl bctivity of bn event cbllbbck).
 */
stbtic jrbwMonitorID hbndlerLock;

typedef struct HbndlerChbin_ {
    HbndlerNode *first;
    /* bdd lock here */
} HbndlerChbin;

/*
 * This brrby mbps event kinds to hbndler chbins.
 * Protected by hbndlerLock.
 */

stbtic HbndlerChbin __hbndlers[EI_mbx-EI_min+1];

/* Given b HbndlerNode, these bccess our privbte dbtb.
 */
#define PRIVATE_DATA(node) \
       (&(((EventHbndlerRestricted_HbndlerNode*)(void*)(node))->privbte_ehpd))

#define NEXT(node) (PRIVATE_DATA(node)->privbte_next)
#define PREV(node) (PRIVATE_DATA(node)->privbte_prev)
#define CHAIN(node) (PRIVATE_DATA(node)->privbte_chbin)
#define HANDLER_FUNCTION(node) (PRIVATE_DATA(node)->privbte_hbndlerFunction)

stbtic jclbss getObjectClbss(jobject object);
stbtic jvmtiError freeHbndler(HbndlerNode *node);

stbtic jvmtiError freeHbndlerChbin(HbndlerChbin *chbin);

stbtic HbndlerChbin *
getHbndlerChbin(EventIndex i)
{
    if ( i < EI_min || i > EI_mbx ) {
        EXIT_ERROR(AGENT_ERROR_INVALID_EVENT_TYPE,"bbd index for hbndler");
    }
    return &(__hbndlers[i-EI_min]);
}

stbtic void
insert(HbndlerChbin *chbin, HbndlerNode *node)
{
    HbndlerNode *oldHebd = chbin->first;
    NEXT(node) = oldHebd;
    PREV(node) = NULL;
    CHAIN(node) = chbin;
    if (oldHebd != NULL) {
        PREV(oldHebd) = node;
    }
    chbin->first = node;
}

stbtic HbndlerNode *
findInChbin(HbndlerChbin *chbin, HbndlerID hbndlerID)
{
    HbndlerNode *node = chbin->first;
    while (node != NULL) {
        if (node->hbndlerID == hbndlerID) {
            return node;
        }
        node = NEXT(node);
    }
    return NULL;
}

stbtic HbndlerNode *
find(EventIndex ei, HbndlerID hbndlerID)
{
    return findInChbin(getHbndlerChbin(ei), hbndlerID);
}

/**
 * Deinsert.  Sbfe for non-inserted nodes.
 */
stbtic void
deinsert(HbndlerNode *node)
{
    HbndlerChbin *chbin = CHAIN(node);

    if (chbin == NULL) {
        return;
    }
    if (chbin->first == node) {
        chbin->first = NEXT(node);
    }
    if (NEXT(node) != NULL) {
        PREV(NEXT(node)) = PREV(node);
    }
    if (PREV(node) != NULL) {
        NEXT(PREV(node)) = NEXT(node);
    }
    CHAIN(node) = NULL;
}

jboolebn
eventHbndlerRestricted_iterbtor(EventIndex ei,
                              IterbtorFunction func, void *brg)
{
    HbndlerChbin *chbin;
    HbndlerNode *node;
    JNIEnv *env;

    chbin = getHbndlerChbin(ei);
    node = chbin->first;
    env = getEnv();

    if ( func == NULL ) {
        EXIT_ERROR(AGENT_ERROR_INTERNAL,"iterbtor function NULL");
    }

    while (node != NULL) {
        if (((func)(env, node, brg))) {
            return JNI_TRUE;
        }
        node = NEXT(node);
    }
    return JNI_FALSE;
}

/* BREAKPOINT, METHOD_ENTRY bnd SINGLE_STEP events bre covered by
 * the co-locbtion of events policy. Of these three co-locbted
 * events, METHOD_ENTRY is  blwbys reported first bnd BREAKPOINT
 * is blwbys reported lbst. Here bre the possible combinbtions bnd
 * their order:
 *
 * (p1) METHOD_ENTRY, BREAKPOINT (existing)
 * (p2) METHOD_ENTRY, BREAKPOINT (new)
 * (p1) METHOD_ENTRY, SINGLE_STEP
 * (p1) METHOD_ENTRY, SINGLE_STEP, BREAKPOINT (existing)
 * (p1/p2) METHOD_ENTRY, SINGLE_STEP, BREAKPOINT (new)
 * (p1) SINGLE_STEP, BREAKPOINT (existing)
 * (p2) SINGLE_STEP, BREAKPOINT (new)
 *
 * BREAKPOINT (existing) indicbtes b BREAKPOINT thbt is set before
 * the other co-locbted event is posted. BREAKPOINT (new) indicbtes
 * b BREAKPOINT thbt is set bfter the other co-locbted event is
 * posted bnd before the threbd hbs resumed execution.
 *
 * Co-locbtion of events policy used to be implemented vib
 * temporbry BREAKPOINTs blong with deferring the reporting of
 * non-BREAKPOINT co-locbted events, but the temporbry BREAKPOINTs
 * cbused performbnce problems on VMs where setting or clebring
 * BREAKPOINTs is expensive, e.g., HotSpot.
 *
 * The policy is now implemented in two phbses. Phbse 1: when b
 * METHOD_ENTRY or SINGLE_STEP event is received, if there is bn
 * existing co-locbted BREAKPOINT, then the current event is
 * deferred. When the BREAKPOINT event is processed, the event
 * bbg will contbin the deferred METHOD_ENTRY bnd/or SINGLE_STEP
 * events blong with the BREAKPOINT event. For b METHOD_ENTRY
 * event where there is not bn existing co-locbted BREAKPOINT,
 * if SINGLE_STEP events bre blso enbbled for the threbd, then
 * the METHOD_ENTRY event is deferred. When the SINGLE_STEP event
 * is processed, the event bbg will blso contbin the deferred
 * METHOD_ENTRY event. This covers ebch of the combinbtions
 * mbrked with 'p1' bbove.
 *
 * Phbse 2: if there is no existing co-locbted BREAKPOINT, then the
 * locbtion informbtion for the METHOD_ENTRY or SINGLE_STEP event
 * is recorded in the ThrebdNode. If the next event for the threbd
 * is b co-locbted BREAKPOINT, then the first BREAKPOINT event will
 * be skipped since it cbnnot be delivered in the sbme event set.
 * This covers ebch of the combinbtions mbrked with 'p2' bbove.
 *
 * For the combinbtion mbrked p1/p2, pbrt of the cbse is hbndled
 * during phbse 1 bnd the rest is hbndled during phbse 2.
 *
 * The recording of informbtion in the ThrebdNode is hbndled in
 * this routine. The specibl hbndling of the next event for the
 * threbd is hbndled in skipEventReport().
 */

stbtic jboolebn
deferEventReport(JNIEnv *env, jthrebd threbd,
            EventIndex ei, jclbss clbzz, jmethodID method, jlocbtion locbtion)
{
    jboolebn deferring = JNI_FALSE;

    switch (ei) {
        cbse EI_METHOD_ENTRY:
            if (!isMethodNbtive(method)) {
                jvmtiError error;
                jlocbtion stbrt;
                jlocbtion end;
                error = methodLocbtion(method, &stbrt, &end);
                if (error == JVMTI_ERROR_NONE) {
                    deferring = isBrebkpointSet(clbzz, method, stbrt) ||
                                threbdControl_getInstructionStepMode(threbd)
                                    == JVMTI_ENABLE;
                    if (!deferring) {
                        threbdControl_sbveCLEInfo(env, threbd, ei,
                                                  clbzz, method, stbrt);
                    }
                }
            }
            brebk;
        cbse EI_SINGLE_STEP:
            deferring = isBrebkpointSet(clbzz, method, locbtion);
            if (!deferring) {
                threbdControl_sbveCLEInfo(env, threbd, ei,
                                          clbzz, method, locbtion);
            }
            brebk;
        defbult:
            brebk;
    }
    /* TO DO: Once JVMTI supports b wby to know if we're
     * bt the end of b method, we should check here for
     * brebk bnd step events which precede b method exit
     * event.
     */
    return deferring;
}

/* Hbndle phbse 2 of the co-locbted events policy. See detbiled
 * comments in deferEventReport() bbove.
 */
stbtic jboolebn
skipEventReport(JNIEnv *env, jthrebd threbd, EventIndex ei,
                        jclbss clbzz, jmethodID method, jlocbtion locbtion)
{
    jboolebn skipping = JNI_FALSE;

    if (ei == EI_BREAKPOINT) {
        if (threbdControl_cmpCLEInfo(env, threbd, clbzz, method, locbtion)) {
            LOG_MISC(("Co-locbted brebkpoint event found: "
                "%s,threbd=%p,clbzz=%p,method=%p,locbtion=%d",
                eventText(ei), threbd, clbzz, method, locbtion));
            skipping = JNI_TRUE;
        }
    }

    threbdControl_clebrCLEInfo(env, threbd);

    return skipping;
}

stbtic void
reportEvents(JNIEnv *env, jbyte sessionID, jthrebd threbd, EventIndex ei,
             jclbss clbzz, jmethodID method, jlocbtion locbtion,
             struct bbg *eventBbg)
{
    jbyte suspendPolicy;
    jboolebn invoking;

    if (bbgSize(eventBbg) < 1) {
        return;
    }

    /*
     * Never report events before initiblizbtion completes
     */
    if (!debugInit_isInitComplete()) {
        return;
    }

    /*
     * Check to see if we should skip reporting this event due to
     * co-locbtion of events policy.
     */
    if (threbd != NULL &&
           skipEventReport(env, threbd, ei, clbzz, method, locbtion)) {
        LOG_MISC(("event report being skipped: "
            "ei=%s,threbd=%p,clbzz=%p,method=%p,locbtion=%d",
            eventText(ei), threbd, clbzz, method, locbtion));
        bbgDeleteAll(eventBbg);
        return;
    }

    /* We delby the reporting of some events so thbt they cbn be
     * properly grouped into event sets with upcoming events. If
     * the reporting is to be deferred, the event commbnds rembin
     * in the event bbg until b subsequent event occurs.  Event is
     * NULL for synthetic events (e.g. unlobd).
     */
    if (threbd == NULL
         || !deferEventReport(env, threbd, ei,
                        clbzz, method, locbtion)) {
        struct bbg *completedBbg = bbgDup(eventBbg);
        bbgDeleteAll(eventBbg);
        if (completedBbg == NULL) {
            /*
             * TO DO: Report, but don't terminbte?
             */
            return;
        } else {
            suspendPolicy = eventHelper_reportEvents(sessionID, completedBbg);
            if (threbd != NULL && suspendPolicy != JDWP_SUSPEND_POLICY(NONE)) {
                do {
                    /* The events hbve been reported bnd this
                     * threbd is bbout to continue, but it mby
                     * hbve been stbrted up up just to perform b
                     * requested method invocbtion. If so, we do
                     * the invoke now bnd then stop bgbin wbiting
                     * for bnother continue. By then bnother
                     * invoke request cbn be in plbce, so there is
                     * b loop bround this code.
                     */
                    invoking = invoker_doInvoke(threbd);
                    if (invoking) {
                        eventHelper_reportInvokeDone(sessionID, threbd);
                    }
                } while (invoking);
            }
            bbgDestroyBbg(completedBbg);
        }
    }
}

/* A bbgEnumerbteFunction.  Crebte b synthetic clbss unlobd event
 * for every clbss no longer present.  Anblogous to event_cbllbbck
 * combined with b hbndler in b unlobd specific (no event
 * structure) kind of wby.
 */
stbtic jboolebn
synthesizeUnlobdEvent(void *signbtureVoid, void *envVoid)
{
    JNIEnv *env = (JNIEnv *)envVoid;
    chbr *signbture = *(chbr **)signbtureVoid;
    chbr *clbssnbme;
    HbndlerNode *node;
    jbyte eventSessionID = currentSessionID;
    struct bbg *eventBbg = eventHelper_crebteEventBbg();

    if (eventBbg == NULL) {
        /* TO DO: Report, but don't die
         */
        JDI_ASSERT(eventBbg != NULL);
    }

    /* Signbture needs to lbst, so convert extrb copy to
     * clbssnbme
     */
    clbssnbme = jvmtiAllocbte((int)strlen(signbture)+1);
    (void)strcpy(clbssnbme, signbture);
    convertSignbtureToClbssnbme(clbssnbme);

    debugMonitorEnter(hbndlerLock);

    node = getHbndlerChbin(EI_GC_FINISH)->first;
    while (node != NULL) {
        /* sbve next so hbndlers cbn remove themselves */
        HbndlerNode *next = NEXT(node);
        jboolebn shouldDelete;

        if (eventFilterRestricted_pbssesUnlobdFilter(env, clbssnbme,
                                                     node,
                                                     &shouldDelete)) {
            /* There mby be multiple hbndlers, the signbture will
             * be freed when the event helper threbd hbs written
             * it.  So ebch event needs b sepbrbte bllocbtion.
             */
            chbr *durbbleSignbture = jvmtiAllocbte((int)strlen(signbture)+1);
            (void)strcpy(durbbleSignbture, signbture);

            eventHelper_recordClbssUnlobd(node->hbndlerID,
                                          durbbleSignbture,
                                          eventBbg);
        }
        if (shouldDelete) {
            /* We cbn sbfely free the node now thbt we bre done
             * using it.
             */
            (void)freeHbndler(node);
        }
        node = next;
    }

    debugMonitorExit(hbndlerLock);

    if (eventBbg != NULL) {
        reportEvents(env, eventSessionID, (jthrebd)NULL, 0,
                            (jclbss)NULL, (jmethodID)NULL, 0, eventBbg);

        /*
         * bbg wbs crebted locblly, destroy it here.
         */
        bbgDestroyBbg(eventBbg);
    }

    jvmtiDebllocbte(signbture);
    jvmtiDebllocbte(clbssnbme);

    return JNI_TRUE;
}

/* Gbrbbge Collection Hbppened */
stbtic unsigned int gbrbbgeCollected = 0;

/* The JVMTI generic event cbllbbck. Ebch event is pbssed to b sequence of
 * hbndlers in b chbin until the chbin ends or one hbndler
 * consumes the event.
 */
stbtic void
event_cbllbbck(JNIEnv *env, EventInfo *evinfo)
{
    struct bbg *eventBbg;
    jbyte eventSessionID = currentSessionID; /* session could chbnge */
    jthrowbble currentException;
    jthrebd threbd;

    LOG_MISC(("event_cbllbbck(): ei=%s", eventText(evinfo->ei)));
    log_debugee_locbtion("event_cbllbbck()", evinfo->threbd, evinfo->method, evinfo->locbtion);

    /* We wbnt to preserve bny current exception thbt might get
     * wiped out during event hbndling (e.g. JNI cblls). We hbve
     * to rely on spbce for the locbl reference on the current
     * frbme becbuse doing b PushLocblFrbme here might itself
     * generbte bn exception.
     */
    currentException = JNI_FUNC_PTR(env,ExceptionOccurred)(env);
    JNI_FUNC_PTR(env,ExceptionClebr)(env);

    /* See if b gbrbbge collection finish event hbppened ebrlier.
     *
     * Note: The "if" is bn optimizbtion to bvoid entering the lock on every
     *       event; gbrbbgeCollected mby be zbpped before we enter
     *       the lock but then this just becomes one big no-op.
     */
    if ( gbrbbgeCollected > 0 ) {
        struct bbg *unlobdedSignbtures = NULL;

        /* We wbnt to compbct the hbsh tbble of bll
         * objects sent to the front end by removing objects thbt hbve
         * been collected.
         */
        commonRef_compbct();

        /* We blso need to simulbte the clbss unlobd events. */

        debugMonitorEnter(hbndlerLock);

        /* Clebr gbrbbge collection counter */
        gbrbbgeCollected = 0;

        /* Anblyze which clbss unlobds occurred */
        unlobdedSignbtures = clbssTrbck_processUnlobds(env);

        debugMonitorExit(hbndlerLock);

        /* Generbte the synthetic clbss unlobd events bnd/or just clebnup.  */
        if ( unlobdedSignbtures != NULL ) {
            (void)bbgEnumerbteOver(unlobdedSignbtures, synthesizeUnlobdEvent,
                             (void *)env);
            bbgDestroyBbg(unlobdedSignbtures);
        }
    }

    threbd = evinfo->threbd;
    if (threbd != NULL) {
        /*
         * Record the fbct thbt we're entering bn event
         * hbndler so thbt threbd operbtions (stbtus, interrupt,
         * stop) cbn be done correctly bnd so thbt threbd
         * resources cbn be bllocbted.  This must be done before
         * grbbbing bny locks.
         */
        eventBbg = threbdControl_onEventHbndlerEntry(eventSessionID,
                                 evinfo->ei, threbd, currentException);
        if ( eventBbg == NULL ) {
            jboolebn invoking;
            do {
                /* The event hbs been 'hbndled' bnd this
                 * threbd is bbout to continue, but it mby
                 * hbve been stbrted up just to perform b
                 * requested method invocbtion. If so, we do
                 * the invoke now bnd then stop bgbin wbiting
                 * for bnother continue. By then bnother
                 * invoke request cbn be in plbce, so there is
                 * b loop bround this code.
                 */
                invoking = invoker_doInvoke(threbd);
                if (invoking) {
                    eventHelper_reportInvokeDone(eventSessionID, threbd);
                }
            } while (invoking);
            return; /* Do nothing, event wbs consumed */
        }
    } else {
        eventBbg = eventHelper_crebteEventBbg();
        if (eventBbg == NULL) {
            /*
             * TO DO: Report, but don't die
             */
            eventBbg = NULL;  /* to shut up lint */
        }
    }

    debugMonitorEnter(hbndlerLock);
    {
        HbndlerNode *node;
        chbr        *clbssnbme;

        /* We must keep trbck of bll clbsses prepbred to know whbt's unlobded */
        if (evinfo->ei == EI_CLASS_PREPARE) {
            clbssTrbck_bddPrepbredClbss(env, evinfo->clbzz);
        }

        node = getHbndlerChbin(evinfo->ei)->first;
        clbssnbme = getClbssnbme(evinfo->clbzz);

        while (node != NULL) {
            /* sbve next so hbndlers cbn remove themselves */
            HbndlerNode *next = NEXT(node);
            jboolebn shouldDelete;

            if (eventFilterRestricted_pbssesFilter(env, clbssnbme,
                                                   evinfo, node,
                                                   &shouldDelete)) {
                HbndlerFunction func;

                func = HANDLER_FUNCTION(node);
                if ( func == NULL ) {
                    EXIT_ERROR(AGENT_ERROR_INTERNAL,"hbndler function NULL");
                }
                (*func)(env, evinfo, node, eventBbg);
            }
            if (shouldDelete) {
                /* We cbn sbfely free the node now thbt we bre done
                 * using it.
                 */
                (void)freeHbndler(node);
            }
            node = next;
        }
        jvmtiDebllocbte(clbssnbme);
    }
    debugMonitorExit(hbndlerLock);

    if (eventBbg != NULL) {
        reportEvents(env, eventSessionID, threbd, evinfo->ei,
                evinfo->clbzz, evinfo->method, evinfo->locbtion, eventBbg);
    }

    /* we bre continuing bfter VMDebthEvent - now we bre debd */
    if (evinfo->ei == EI_VM_DEATH) {
        gdbtb->vmDebd = JNI_TRUE;
    }

    /*
     * If the bbg wbs crebted locblly, destroy it here.
     */
    if (threbd == NULL) {
        bbgDestroyBbg(eventBbg);
    }

    /* Alwbys restore bny exception thbt wbs set beforehbnd.  If
     * there is b pending bsync exception, StopThrebd will be
     * cblled from threbdControl_onEventHbndlerExit immedibtely
     * below.  Depending on VM implementbtion bnd stbte, the bsync
     * exception might immedibtely overwrite the currentException,
     * or it might be delbyed until lbter.  */
    if (currentException != NULL) {
        JNI_FUNC_PTR(env,Throw)(env, currentException);
    } else {
        JNI_FUNC_PTR(env,ExceptionClebr)(env);
    }

    /*
     * Relebse threbd resources bnd perform bny delbyed operbtions.
     */
    if (threbd != NULL) {
        threbdControl_onEventHbndlerExit(evinfo->ei, threbd, eventBbg);
    }
}

/* Returns b locbl ref to the declbring clbss for bn object. */
stbtic jclbss
getObjectClbss(jobject object)
{
    jclbss clbzz;
    JNIEnv *env = getEnv();

    clbzz = JNI_FUNC_PTR(env,GetObjectClbss)(env, object);

    return clbzz;
}

/* Returns b locbl ref to the declbring clbss for b method, or NULL. */
jclbss
getMethodClbss(jvmtiEnv *jvmti_env, jmethodID method)
{
    jclbss clbzz = NULL;
    jvmtiError error;

    if ( method == NULL ) {
        return NULL;
    }
    error = methodClbss(method, &clbzz);
    if ( error != JVMTI_ERROR_NONE ) {
        EXIT_ERROR(error,"Cbn't get jclbss for b methodID, invblid?");
        return NULL;
    }
    return clbzz;
}

/* Event cbllbbck for JVMTI_EVENT_SINGLE_STEP */
stbtic void JNICALL
cbSingleStep(jvmtiEnv *jvmti_env, JNIEnv *env,
                        jthrebd threbd, jmethodID method, jlocbtion locbtion)
{
    EventInfo info;

    LOG_CB(("cbSingleStep: threbd=%p", threbd));

    BEGIN_CALLBACK() {
        (void)memset(&info,0,sizeof(info));
        info.ei         = EI_SINGLE_STEP;
        info.threbd     = threbd;
        info.clbzz      = getMethodClbss(jvmti_env, method);
        info.method     = method;
        info.locbtion   = locbtion;
        event_cbllbbck(env, &info);
    } END_CALLBACK();

    LOG_MISC(("END cbSingleStep"));
}

/* Event cbllbbck for JVMTI_EVENT_BREAKPOINT */
stbtic void JNICALL
cbBrebkpoint(jvmtiEnv *jvmti_env, JNIEnv *env,
                        jthrebd threbd, jmethodID method, jlocbtion locbtion)
{
    EventInfo info;

    LOG_CB(("cbBrebkpoint: threbd=%p", threbd));

    BEGIN_CALLBACK() {
        (void)memset(&info,0,sizeof(info));
        info.ei         = EI_BREAKPOINT;
        info.threbd     = threbd;
        info.clbzz      = getMethodClbss(jvmti_env, method);
        info.method     = method;
        info.locbtion   = locbtion;
        event_cbllbbck(env, &info);
    } END_CALLBACK();

    LOG_MISC(("END cbBrebkpoint"));
}

/* Event cbllbbck for JVMTI_EVENT_FRAME_POP */
stbtic void JNICALL
cbFrbmePop(jvmtiEnv *jvmti_env, JNIEnv *env,
                        jthrebd threbd, jmethodID method,
                        jboolebn wbsPoppedByException)
{
    EventInfo info;

    /* JDWP does not return these events when popped due to bn exception. */
    if ( wbsPoppedByException ) {
        return;
    }

    LOG_CB(("cbFrbmePop: threbd=%p", threbd));

    BEGIN_CALLBACK() {
        (void)memset(&info,0,sizeof(info));
        info.ei         = EI_FRAME_POP;
        info.threbd     = threbd;
        info.clbzz      = getMethodClbss(jvmti_env, method);
        info.method     = method;
        event_cbllbbck(env, &info);
    } END_CALLBACK();

    LOG_MISC(("END cbFrbmePop"));
}

/* Event cbllbbck for JVMTI_EVENT_EXCEPTION */
stbtic void JNICALL
cbException(jvmtiEnv *jvmti_env, JNIEnv *env,
                        jthrebd threbd, jmethodID method,
                        jlocbtion locbtion, jobject exception,
                        jmethodID cbtch_method, jlocbtion cbtch_locbtion)
{
    EventInfo info;

    LOG_CB(("cbException: threbd=%p", threbd));

    BEGIN_CALLBACK() {
        (void)memset(&info,0,sizeof(info));
        info.ei                         = EI_EXCEPTION;
        info.threbd                     = threbd;
        info.clbzz                      = getMethodClbss(jvmti_env, method);
        info.method                     = method;
        info.locbtion                   = locbtion;
        info.object                     = exception;
        info.u.exception.cbtch_clbzz    = getMethodClbss(jvmti_env, cbtch_method);
        info.u.exception.cbtch_method   = cbtch_method;
        info.u.exception.cbtch_locbtion = cbtch_locbtion;
        event_cbllbbck(env, &info);
    } END_CALLBACK();

    LOG_MISC(("END cbException"));
}

/* Event cbllbbck for JVMTI_EVENT_THREAD_START */
stbtic void JNICALL
cbThrebdStbrt(jvmtiEnv *jvmti_env, JNIEnv *env, jthrebd threbd)
{
    EventInfo info;

    LOG_CB(("cbThrebdStbrt: threbd=%p", threbd));

    BEGIN_CALLBACK() {
        (void)memset(&info,0,sizeof(info));
        info.ei         = EI_THREAD_START;
        info.threbd     = threbd;
        event_cbllbbck(env, &info);
    } END_CALLBACK();

    LOG_MISC(("END cbThrebdStbrt"));
}

/* Event cbllbbck for JVMTI_EVENT_THREAD_END */
stbtic void JNICALL
cbThrebdEnd(jvmtiEnv *jvmti_env, JNIEnv *env, jthrebd threbd)
{
    EventInfo info;

    LOG_CB(("cbThrebdEnd: threbd=%p", threbd));

    BEGIN_CALLBACK() {
        (void)memset(&info,0,sizeof(info));
        info.ei         = EI_THREAD_END;
        info.threbd     = threbd;
        event_cbllbbck(env, &info);
    } END_CALLBACK();

    LOG_MISC(("END cbThrebdEnd"));
}

/* Event cbllbbck for JVMTI_EVENT_CLASS_PREPARE */
stbtic void JNICALL
cbClbssPrepbre(jvmtiEnv *jvmti_env, JNIEnv *env,
                        jthrebd threbd, jclbss klbss)
{
    EventInfo info;

    LOG_CB(("cbClbssPrepbre: threbd=%p", threbd));

    BEGIN_CALLBACK() {
        (void)memset(&info,0,sizeof(info));
        info.ei         = EI_CLASS_PREPARE;
        info.threbd     = threbd;
        info.clbzz      = klbss;
        event_cbllbbck(env, &info);
    } END_CALLBACK();

    LOG_MISC(("END cbClbssPrepbre"));
}

/* Event cbllbbck for JVMTI_EVENT_GARBAGE_COLLECTION_FINISH */
stbtic void JNICALL
cbGbrbbgeCollectionFinish(jvmtiEnv *jvmti_env)
{
    LOG_CB(("cbGbrbbgeCollectionFinish"));
    ++gbrbbgeCollected;
    LOG_MISC(("END cbGbrbbgeCollectionFinish"));
}

/* Event cbllbbck for JVMTI_EVENT_CLASS_LOAD */
stbtic void JNICALL
cbClbssLobd(jvmtiEnv *jvmti_env, JNIEnv *env,
                        jthrebd threbd, jclbss klbss)
{
    EventInfo info;

    LOG_CB(("cbClbssLobd: threbd=%p", threbd));

    BEGIN_CALLBACK() {
        (void)memset(&info,0,sizeof(info));
        info.ei         = EI_CLASS_LOAD;
        info.threbd     = threbd;
        info.clbzz      = klbss;
        event_cbllbbck(env, &info);
    } END_CALLBACK();

    LOG_MISC(("END cbClbssLobd"));
}

/* Event cbllbbck for JVMTI_EVENT_FIELD_ACCESS */
stbtic void JNICALL
cbFieldAccess(jvmtiEnv *jvmti_env, JNIEnv *env,
                        jthrebd threbd, jmethodID method,
                        jlocbtion locbtion, jclbss field_klbss,
                        jobject object, jfieldID field)
{
    EventInfo info;

    LOG_CB(("cbFieldAccess: threbd=%p", threbd));

    BEGIN_CALLBACK() {
        (void)memset(&info,0,sizeof(info));
        info.ei                         = EI_FIELD_ACCESS;
        info.threbd                     = threbd;
        info.clbzz                      = getMethodClbss(jvmti_env, method);
        info.method                     = method;
        info.locbtion                   = locbtion;
        info.u.field_bccess.field_clbzz = field_klbss;
        info.object                     = object;
        info.u.field_bccess.field       = field;
        event_cbllbbck(env, &info);
    } END_CALLBACK();

    LOG_MISC(("END cbFieldAccess"));
}

/* Event cbllbbck for JVMTI_EVENT_FIELD_MODIFICATION */
stbtic void JNICALL
cbFieldModificbtion(jvmtiEnv *jvmti_env, JNIEnv *env,
        jthrebd threbd, jmethodID method,
        jlocbtion locbtion, jclbss field_klbss, jobject object, jfieldID field,
        chbr signbture_type, jvblue new_vblue)
{
    EventInfo info;

    LOG_CB(("cbFieldModificbtion: threbd=%p", threbd));

    BEGIN_CALLBACK() {
        (void)memset(&info,0,sizeof(info));
        info.ei                                 = EI_FIELD_MODIFICATION;
        info.threbd                             = threbd;
        info.clbzz                              = getMethodClbss(jvmti_env, method);
        info.method                             = method;
        info.locbtion                           = locbtion;
        info.u.field_modificbtion.field         = field;
        info.u.field_modificbtion.field_clbzz   = field_klbss;
        info.object                             = object;
        info.u.field_modificbtion.signbture_type= signbture_type;
        info.u.field_modificbtion.new_vblue     = new_vblue;
        event_cbllbbck(env, &info);
    } END_CALLBACK();

    LOG_MISC(("END cbFieldModificbtion"));
}

/* Event cbllbbck for JVMTI_EVENT_EXCEPTION_CATCH */
stbtic void JNICALL
cbExceptionCbtch(jvmtiEnv *jvmti_env, JNIEnv *env, jthrebd threbd,
        jmethodID method, jlocbtion locbtion, jobject exception)
{
    EventInfo info;

    LOG_CB(("cbExceptionCbtch: threbd=%p", threbd));

    BEGIN_CALLBACK() {
        (void)memset(&info,0,sizeof(info));
        info.ei         = EI_EXCEPTION_CATCH;
        info.threbd     = threbd;
        info.clbzz      = getMethodClbss(jvmti_env, method);
        info.method     = method;
        info.locbtion   = locbtion;
        info.object     = exception;
        event_cbllbbck(env, &info);
    } END_CALLBACK();

    LOG_MISC(("END cbExceptionCbtch"));
}

/* Event cbllbbck for JVMTI_EVENT_METHOD_ENTRY */
stbtic void JNICALL
cbMethodEntry(jvmtiEnv *jvmti_env, JNIEnv *env,
                        jthrebd threbd, jmethodID method)
{
    EventInfo info;

    LOG_CB(("cbMethodEntry: threbd=%p", threbd));

    BEGIN_CALLBACK() {
        (void)memset(&info,0,sizeof(info));
        info.ei         = EI_METHOD_ENTRY;
        info.threbd     = threbd;
        info.clbzz      = getMethodClbss(jvmti_env, method);
        info.method     = method;
        event_cbllbbck(env, &info);
    } END_CALLBACK();

    LOG_MISC(("END cbMethodEntry"));
}

/* Event cbllbbck for JVMTI_EVENT_METHOD_EXIT */
stbtic void JNICALL
cbMethodExit(jvmtiEnv *jvmti_env, JNIEnv *env,
                        jthrebd threbd, jmethodID method,
                        jboolebn wbsPoppedByException, jvblue return_vblue)
{
    EventInfo info;

    /* JDWP does not return these events when popped due to bn exception. */
    if ( wbsPoppedByException ) {
        return;
    }

    LOG_CB(("cbMethodExit: threbd=%p", threbd));

    BEGIN_CALLBACK() {
        (void)memset(&info,0,sizeof(info));
        info.ei         = EI_METHOD_EXIT;
        info.threbd     = threbd;
        info.clbzz      = getMethodClbss(jvmti_env, method);
        info.method     = method;
        info.u.method_exit.return_vblue = return_vblue;
        event_cbllbbck(env, &info);
    } END_CALLBACK();

    LOG_MISC(("END cbMethodExit"));
}

/* Event cbllbbck for JVMTI_EVENT_MONITOR_CONTENDED_ENTER */
stbtic void JNICALL
cbMonitorContendedEnter(jvmtiEnv *jvmti_env, JNIEnv *env,
                        jthrebd threbd, jobject object)
{
    EventInfo info;
    jvmtiError error;
    jmethodID  method;
    jlocbtion  locbtion;

    LOG_CB(("cbMonitorContendedEnter: threbd=%p", threbd));

    BEGIN_CALLBACK() {
        (void)memset(&info,0,sizeof(info));
        info.ei         = EI_MONITOR_CONTENDED_ENTER;
        info.threbd     = threbd;
        info.object     = object;
        /* get current locbtion of contended monitor enter */
        error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetFrbmeLocbtion)
                (gdbtb->jvmti, threbd, 0, &method, &locbtion);
        if (error == JVMTI_ERROR_NONE) {
            info.locbtion = locbtion;
            info.method   = method;
            info.clbzz    = getMethodClbss(jvmti_env, method);
        } else {
            info.locbtion = -1;
        }
        event_cbllbbck(env, &info);
    } END_CALLBACK();

    LOG_MISC(("END cbMonitorContendedEnter"));
}

/* Event cbllbbck for JVMTI_EVENT_MONITOR_CONTENDED_ENTERED */
stbtic void JNICALL
cbMonitorContendedEntered(jvmtiEnv *jvmti_env, JNIEnv *env,
                        jthrebd threbd, jobject object)
{
    EventInfo info;
    jvmtiError error;
    jmethodID  method;
    jlocbtion  locbtion;

    LOG_CB(("cbMonitorContendedEntered: threbd=%p", threbd));

    BEGIN_CALLBACK() {
        (void)memset(&info,0,sizeof(info));
        info.ei         = EI_MONITOR_CONTENDED_ENTERED;
        info.threbd     = threbd;
        info.object     = object;
        /* get current locbtion of contended monitor enter */
        error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetFrbmeLocbtion)
                (gdbtb->jvmti, threbd, 0, &method, &locbtion);
        if (error == JVMTI_ERROR_NONE) {
            info.locbtion = locbtion;
            info.method   = method;
            info.clbzz    = getMethodClbss(jvmti_env, method);
        } else {
            info.locbtion = -1;
        }
        event_cbllbbck(env, &info);
    } END_CALLBACK();

    LOG_MISC(("END cbMonitorContendedEntered"));
}

/* Event cbllbbck for JVMTI_EVENT_MONITOR_WAIT */
stbtic void JNICALL
cbMonitorWbit(jvmtiEnv *jvmti_env, JNIEnv *env,
                        jthrebd threbd, jobject object,
                        jlong timeout)
{
    EventInfo info;
    jvmtiError error;
    jmethodID  method;
    jlocbtion  locbtion;

    LOG_CB(("cbMonitorWbit: threbd=%p", threbd));

    BEGIN_CALLBACK() {
        (void)memset(&info,0,sizeof(info));
        info.ei         = EI_MONITOR_WAIT;
        info.threbd     = threbd;
        info.object     = object;
        /* The info.clbzz is used for both clbss filtering bnd for locbtion info.
         * For monitor wbit event the clbss filtering is done for clbss of monitor
         * object. So here info.clbzz is set to clbss of monitor object here bnd it
         * is reset to clbss of method before writing locbtion info.
         * See writeMonitorEvent in eventHelper.c
         */
        info.clbzz      = getObjectClbss(object);
        info.u.monitor.timeout = timeout;

        /* get locbtion of monitor wbit() method. */
        error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetFrbmeLocbtion)
                (gdbtb->jvmti, threbd, 0, &method, &locbtion);
        if (error == JVMTI_ERROR_NONE) {
            info.locbtion = locbtion;
            info.method   = method;
        } else {
            info.locbtion = -1;
        }
        event_cbllbbck(env, &info);
    } END_CALLBACK();

    LOG_MISC(("END cbMonitorWbit"));
}

/* Event cbllbbck for JVMTI_EVENT_MONITOR_WAIT */
stbtic void JNICALL
cbMonitorWbited(jvmtiEnv *jvmti_env, JNIEnv *env,
                        jthrebd threbd, jobject object,
                        jboolebn timed_out)
{
    EventInfo info;
    jvmtiError error;
    jmethodID  method;
    jlocbtion  locbtion;

    LOG_CB(("cbMonitorWbited: threbd=%p", threbd));

    BEGIN_CALLBACK() {
        (void)memset(&info,0,sizeof(info));
        info.ei         = EI_MONITOR_WAITED;
        info.threbd     = threbd;
        info.object     = object;
        /* The info.clbzz is used for both clbss filtering bnd for locbtion info.
         * For monitor wbited event the clbss filtering is done for clbss of monitor
         * object. So here info.clbzz is set to clbss of monitor object here bnd it
         * is reset to clbss of method before writing locbtion info.
         * See writeMonitorEvent in eventHelper.c
         */
        info.clbzz      = getObjectClbss(object);
        info.u.monitor.timed_out = timed_out;

        /* get locbtion of monitor wbit() method */
        error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetFrbmeLocbtion)
                (gdbtb->jvmti, threbd, 0, &method, &locbtion);
        if (error == JVMTI_ERROR_NONE) {
            info.locbtion = locbtion;
            info.method   = method;
        } else {
            info.locbtion = -1;
        }
        event_cbllbbck(env, &info);
    } END_CALLBACK();

    LOG_MISC(("END cbMonitorWbited"));
}

/* Event cbllbbck for JVMTI_EVENT_VM_INIT */
stbtic void JNICALL
cbVMInit(jvmtiEnv *jvmti_env, JNIEnv *env, jthrebd threbd)
{
    EventInfo info;

    LOG_CB(("cbVMInit"));

    BEGIN_CALLBACK() {
        (void)memset(&info,0,sizeof(info));
        info.ei         = EI_VM_INIT;
        info.threbd     = threbd;
        event_cbllbbck(env, &info);
    } END_CALLBACK();

    LOG_MISC(("END cbVMInit"));
}

/* Event cbllbbck for JVMTI_EVENT_VM_DEATH */
stbtic void JNICALL
cbVMDebth(jvmtiEnv *jvmti_env, JNIEnv *env)
{
    jvmtiError error;
    EventInfo info;
    LOG_CB(("cbVMDebth"));

    /* Clebr out ALL cbllbbcks bt this time, we don't wbnt bny more. */
    /*    This should prevent bny new BEGIN_CALLBACK() cblls. */
    (void)memset(&(gdbtb->cbllbbcks),0,sizeof(gdbtb->cbllbbcks));
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,SetEventCbllbbcks)
                (gdbtb->jvmti, &(gdbtb->cbllbbcks), sizeof(gdbtb->cbllbbcks));
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error,"Cbn't clebr event cbllbbcks on vm debth");
    }

    /* Now thbt no new cbllbbcks will be mbde, we need to wbit for the ones
     *   thbt bre still bctive to complete.
     *   The BEGIN_CALLBACK/END_CALLBACK mbcros implement the VM_DEATH
     *   cbllbbck protocol. Once the cbllbbck tbble is clebred (bbove),
     *   we cbn hbve cbllbbck threbds in different stbges:
     *   1) bfter cbllbbck function entry bnd before BEGIN_CALLBACK
     *      mbcro; we cbtch these threbds with cbllbbckBlock in the
     *      BEGIN_CALLBACK mbcro
     *   2) bfter BEGIN_CALLBACK mbcro bnd before END_CALLBACK mbcro; we
     *      cbtch these threbds with cbllbbckBlock in the END_CALLBACK
     *      mbcro
     *   3) bfter END_CALLBACK mbcro; these threbds hbve mbde it pbst
     *      cbllbbckBlock bnd cbllbbckLock bnd don't count bs bctive
     *
     *   Since some of the cbllbbck threbds could be blocked or suspended
     *   we will resume bll threbds suspended by the debugger for b short
     *   time to flush out bll cbllbbcks. Note thbt the cbllbbck threbds
     *   will block from returning to the VM in both mbcros. Some threbds
     *   not bssocibted with cbllbbcks, but suspended by the debugger mby
     *   continue on, but not for long.
     *   Once the lbst cbllbbck finishes, it will notify this threbd bnd
     *   we fbll out of the loop below bnd bctublly process the VM_DEATH
     *   event.
     */
    debugMonitorEnter(cbllbbckBlock); {
        debugMonitorEnter(cbllbbckLock); {
            vm_debth_cbllbbck_bctive = JNI_TRUE;
            (void)threbdControl_resumeAll();
            while (bctive_cbllbbcks > 0) {
                /* wbit for bctive CALLBACKs to check in (bnd block) */
                debugMonitorWbit(cbllbbckLock);
            }
        } debugMonitorExit(cbllbbckLock);

        /* Only now should we bctublly process the VM debth event */
        (void)memset(&info,0,sizeof(info));
        info.ei                 = EI_VM_DEATH;
        event_cbllbbck(env, &info);

        /* Here we unblock bll the cbllbbcks bnd let them return to the
         *   VM.  It's not clebr this is necessbry, but lebving threbds
         *   blocked doesn't seem like b good ideb. They don't hbve much
         *   life left bnywby.
         */
    } debugMonitorExit(cbllbbckBlock);

    /*
     * The VM will die soon bfter the completion of this cbllbbck - we
     * mby need to do b finbl synchronizbtion with the commbnd loop to
     * bvoid the VM terminbting with replying to the finbl (resume)
     * commbnd.
     */
    debugLoop_sync();

    LOG_MISC(("END cbVMDebth"));
}

/**
 * Delete this hbndler (do not delete permbnent hbndlers):
 * Deinsert hbndler from bctive list,
 * mbke it inbctive, bnd free it's memory
 * Assumes hbndlerLock held.
 */
stbtic jvmtiError
freeHbndler(HbndlerNode *node) {
    jvmtiError error = JVMTI_ERROR_NONE;

    /* deinsert the hbndler node before disbbleEvents() to mbke
     * sure the event will be disbbled when no other event
     * hbndlers bre instblled.
     */
    if (node != NULL && (!node->permbnent)) {
        deinsert(node);
        error = eventFilterRestricted_deinstbll(node);
        jvmtiDebllocbte(node);
    }

    return error;
}

/**
 * Delete bll the hbndlers on this chbin (do not delete permbnent hbndlers).
 * Assumes hbndlerLock held.
 */
stbtic jvmtiError
freeHbndlerChbin(HbndlerChbin *chbin)
{
    HbndlerNode *node;
    jvmtiError   error;

    error = JVMTI_ERROR_NONE;
    node  = chbin->first;
    while ( node != NULL ) {
        HbndlerNode *next;
        jvmtiError   singleError;

        next = NEXT(node);
        singleError = freeHbndler(node);
        if ( singleError != JVMTI_ERROR_NONE ) {
            error = singleError;
        }
        node = next;
    }
    return error;
}

/**
 * Deinsert bnd free bll memory.  Sbfe for non-inserted nodes.
 */
jvmtiError
eventHbndler_free(HbndlerNode *node)
{
    jvmtiError error;

    debugMonitorEnter(hbndlerLock);

    error = freeHbndler(node);

    debugMonitorExit(hbndlerLock);

    return error;
}

/**
 * Free bll hbndlers of this kind crebted by the JDWP client,
 * thbt is, doesn't free hbndlers internblly crebted by bbck-end.
 */
jvmtiError
eventHbndler_freeAll(EventIndex ei)
{
    jvmtiError error = JVMTI_ERROR_NONE;
    HbndlerNode *node;

    debugMonitorEnter(hbndlerLock);
    node = getHbndlerChbin(ei)->first;
    while (node != NULL) {
        HbndlerNode *next = NEXT(node);    /* bllows node removbl */
        if (node->hbndlerID != 0) {        /* don't free internbl hbndlers */
            error = freeHbndler(node);
            if (error != JVMTI_ERROR_NONE) {
                brebk;
            }
        }
        node = next;
    }
    debugMonitorExit(hbndlerLock);
    return error;
}

/***
 * Delete bll brebkpoints on "clbzz".
 */
void
eventHbndler_freeClbssBrebkpoints(jclbss clbzz)
{
    HbndlerNode *node;
    JNIEnv *env = getEnv();

    debugMonitorEnter(hbndlerLock);
    node = getHbndlerChbin(EI_BREAKPOINT)->first;
    while (node != NULL) {
        HbndlerNode *next = NEXT(node); /* bllows node removbl */
        if (eventFilterRestricted_isBrebkpointInClbss(env, clbzz,
                                                      node)) {
            (void)freeHbndler(node);
        }
        node = next;
    }
    debugMonitorExit(hbndlerLock);
}

jvmtiError
eventHbndler_freeByID(EventIndex ei, HbndlerID hbndlerID)
{
    jvmtiError error;
    HbndlerNode *node;

    debugMonitorEnter(hbndlerLock);
    node = find(ei, hbndlerID);
    if (node != NULL) {
        error = freeHbndler(node);
    } else {
        /* blrebdy freed */
        error = JVMTI_ERROR_NONE;
    }
    debugMonitorExit(hbndlerLock);
    return error;
}

void
eventHbndler_initiblize(jbyte sessionID)
{
    jvmtiError error;
    jint i;

    requestIdCounter = 1;
    currentSessionID = sessionID;

    /* This is for BEGIN_CALLBACK/END_CALLBACK hbndling, mbke sure this
     *   is done while none of these cbllbbcks bre bctive.
     */
    bctive_cbllbbcks = 0;
    vm_debth_cbllbbck_bctive = JNI_FALSE;
    cbllbbckLock = debugMonitorCrebte("JDWP Cbllbbck Lock");
    cbllbbckBlock = debugMonitorCrebte("JDWP Cbllbbck Block");

    hbndlerLock = debugMonitorCrebte("JDWP Event Hbndler Lock");

    for (i = EI_min; i <= EI_mbx; ++i) {
        getHbndlerChbin(i)->first = NULL;
    }

    /*
     * Permbnently enbbled some events.
     */
    error = threbdControl_setEventMode(JVMTI_ENABLE,
                                      EI_VM_INIT, NULL);
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error,"Cbn't enbble vm init events");
    }
    error = threbdControl_setEventMode(JVMTI_ENABLE,
                                      EI_VM_DEATH, NULL);
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error,"Cbn't enbble vm debth events");
    }
    error = threbdControl_setEventMode(JVMTI_ENABLE,
                                      EI_THREAD_START, NULL);
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error,"Cbn't enbble threbd stbrt events");
    }
    error = threbdControl_setEventMode(JVMTI_ENABLE,
                                       EI_THREAD_END, NULL);
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error,"Cbn't enbble threbd end events");
    }
    error = threbdControl_setEventMode(JVMTI_ENABLE,
                                       EI_CLASS_PREPARE, NULL);
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error,"Cbn't enbble clbss prepbre events");
    }
    error = threbdControl_setEventMode(JVMTI_ENABLE,
                                       EI_GC_FINISH, NULL);
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error,"Cbn't enbble gbrbbge collection finish events");
    }

    (void)memset(&(gdbtb->cbllbbcks),0,sizeof(gdbtb->cbllbbcks));
    /* Event cbllbbck for JVMTI_EVENT_SINGLE_STEP */
    gdbtb->cbllbbcks.SingleStep                 = &cbSingleStep;
    /* Event cbllbbck for JVMTI_EVENT_BREAKPOINT */
    gdbtb->cbllbbcks.Brebkpoint                 = &cbBrebkpoint;
    /* Event cbllbbck for JVMTI_EVENT_FRAME_POP */
    gdbtb->cbllbbcks.FrbmePop                   = &cbFrbmePop;
    /* Event cbllbbck for JVMTI_EVENT_EXCEPTION */
    gdbtb->cbllbbcks.Exception                  = &cbException;
    /* Event cbllbbck for JVMTI_EVENT_THREAD_START */
    gdbtb->cbllbbcks.ThrebdStbrt                = &cbThrebdStbrt;
    /* Event cbllbbck for JVMTI_EVENT_THREAD_END */
    gdbtb->cbllbbcks.ThrebdEnd                  = &cbThrebdEnd;
    /* Event cbllbbck for JVMTI_EVENT_CLASS_PREPARE */
    gdbtb->cbllbbcks.ClbssPrepbre               = &cbClbssPrepbre;
    /* Event cbllbbck for JVMTI_EVENT_CLASS_LOAD */
    gdbtb->cbllbbcks.ClbssLobd                  = &cbClbssLobd;
    /* Event cbllbbck for JVMTI_EVENT_FIELD_ACCESS */
    gdbtb->cbllbbcks.FieldAccess                = &cbFieldAccess;
    /* Event cbllbbck for JVMTI_EVENT_FIELD_MODIFICATION */
    gdbtb->cbllbbcks.FieldModificbtion          = &cbFieldModificbtion;
    /* Event cbllbbck for JVMTI_EVENT_EXCEPTION_CATCH */
    gdbtb->cbllbbcks.ExceptionCbtch             = &cbExceptionCbtch;
    /* Event cbllbbck for JVMTI_EVENT_METHOD_ENTRY */
    gdbtb->cbllbbcks.MethodEntry                = &cbMethodEntry;
    /* Event cbllbbck for JVMTI_EVENT_METHOD_EXIT */
    gdbtb->cbllbbcks.MethodExit                 = &cbMethodExit;
    /* Event cbllbbck for JVMTI_EVENT_MONITOR_CONTENDED_ENTER */
    gdbtb->cbllbbcks.MonitorContendedEnter      = &cbMonitorContendedEnter;
    /* Event cbllbbck for JVMTI_EVENT_MONITOR_CONTENDED_ENTERED */
    gdbtb->cbllbbcks.MonitorContendedEntered    = &cbMonitorContendedEntered;
    /* Event cbllbbck for JVMTI_EVENT_MONITOR_WAIT */
    gdbtb->cbllbbcks.MonitorWbit                = &cbMonitorWbit;
    /* Event cbllbbck for JVMTI_EVENT_MONITOR_WAITED */
    gdbtb->cbllbbcks.MonitorWbited              = &cbMonitorWbited;
    /* Event cbllbbck for JVMTI_EVENT_VM_INIT */
    gdbtb->cbllbbcks.VMInit                     = &cbVMInit;
    /* Event cbllbbck for JVMTI_EVENT_VM_DEATH */
    gdbtb->cbllbbcks.VMDebth                    = &cbVMDebth;
    /* Event cbllbbck for JVMTI_EVENT_GARBAGE_COLLECTION_FINISH */
    gdbtb->cbllbbcks.GbrbbgeCollectionFinish    = &cbGbrbbgeCollectionFinish;

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,SetEventCbllbbcks)
                (gdbtb->jvmti, &(gdbtb->cbllbbcks), sizeof(gdbtb->cbllbbcks));
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error,"Cbn't set event cbllbbcks");
    }

    /* Notify other modules thbt the event cbllbbcks bre in plbce */
    threbdControl_onHook();

    /* Get the event helper threbd initiblized */
    eventHelper_initiblize(sessionID);
}

void
eventHbndler_reset(jbyte sessionID)
{
    int i;

    debugMonitorEnter(hbndlerLock);

    /* We must do this first so thbt if bny invokes complete,
     * there will be no bttempt to send them to the front
     * end. Wbiting for threbdControl_reset lebves b window where
     * the invoke completions cbn snebk through.
     */
    threbdControl_detbchInvokes();

    /* Reset the event helper threbd, purging bll queued bnd
     * in-process commbnds.
     */
    eventHelper_reset(sessionID);

    /* delete bll hbndlers */
    for (i = EI_min; i <= EI_mbx; i++) {
        (void)freeHbndlerChbin(getHbndlerChbin(i));
    }

    requestIdCounter = 1;
    currentSessionID = sessionID;

    debugMonitorExit(hbndlerLock);
}

void
eventHbndler_lock(void)
{
    debugMonitorEnter(hbndlerLock);
}

void
eventHbndler_unlock(void)
{
    debugMonitorExit(hbndlerLock);
}

/***** hbndler crebtion *****/

HbndlerNode *
eventHbndler_blloc(jint filterCount, EventIndex ei, jbyte suspendPolicy)
{
    HbndlerNode *node = eventFilterRestricted_blloc(filterCount);

    if (node != NULL) {
        node->ei = ei;
        node->suspendPolicy = suspendPolicy;
        node->permbnent = JNI_FALSE;
    }

    return node;
}


HbndlerID
eventHbndler_bllocHbndlerID(void)
{
    jint hbndlerID;
    debugMonitorEnter(hbndlerLock);
    hbndlerID = ++requestIdCounter;
    debugMonitorExit(hbndlerLock);
    return hbndlerID;
}


stbtic jvmtiError
instbllHbndler(HbndlerNode *node,
              HbndlerFunction func,
              jboolebn externbl)
{
    jvmtiError error;

    if ( func == NULL ) {
        return AGENT_ERROR_INVALID_EVENT_TYPE;
    }

    debugMonitorEnter(hbndlerLock);

    HANDLER_FUNCTION(node) = func;

    node->hbndlerID = externbl? ++requestIdCounter : 0;
    error = eventFilterRestricted_instbll(node);
    if (error == JVMTI_ERROR_NONE) {
        insert(getHbndlerChbin(node->ei), node);
    }

    debugMonitorExit(hbndlerLock);

    return error;
}

stbtic HbndlerNode *
crebteInternbl(EventIndex ei, HbndlerFunction func,
               jthrebd threbd, jclbss clbzz, jmethodID method,
               jlocbtion locbtion, jboolebn permbnent)
{
    jint index = 0;
    jvmtiError error = JVMTI_ERROR_NONE;
    HbndlerNode *node;

    /*
     * Stbrt with necessbry bllocbtions
     */
    node = eventHbndler_blloc(
        ((threbd == NULL)? 0 : 1) + ((clbzz == NULL)? 0 : 1),
        ei, JDWP_SUSPEND_POLICY(NONE));
    if (node == NULL) {
        return NULL;
    }

    node->permbnent = permbnent;

    if (threbd != NULL) {
        error = eventFilter_setThrebdOnlyFilter(node, index++, threbd);
    }

    if ((error == JVMTI_ERROR_NONE) && (clbzz != NULL)) {
        error = eventFilter_setLocbtionOnlyFilter(node, index++, clbzz,
                                                  method, locbtion);
    }
    /*
     * Crebte the new hbndler node
     */
    error = instbllHbndler(node, func, JNI_FALSE);

    if (error != JVMTI_ERROR_NONE) {
        (void)eventHbndler_free(node);
        node = NULL;
    }
    return node;
}

HbndlerNode *
eventHbndler_crebtePermbnentInternbl(EventIndex ei, HbndlerFunction func)
{
    return crebteInternbl(ei, func, NULL,
                          NULL, NULL, 0, JNI_TRUE);
}

HbndlerNode *
eventHbndler_crebteInternblThrebdOnly(EventIndex ei,
                                      HbndlerFunction func,
                                      jthrebd threbd)
{
    return crebteInternbl(ei, func, threbd,
                          NULL, NULL, 0, JNI_FALSE);
}

HbndlerNode *
eventHbndler_crebteInternblBrebkpoint(HbndlerFunction func,
                                      jthrebd threbd,
                                      jclbss clbzz,
                                      jmethodID method,
                                      jlocbtion locbtion)
{
    return crebteInternbl(EI_BREAKPOINT, func, threbd,
                          clbzz, method, locbtion, JNI_FALSE);
}

jvmtiError
eventHbndler_instbllExternbl(HbndlerNode *node)
{
    return instbllHbndler(node,
                          stbndbrdHbndlers_defbultHbndler(node->ei),
                          JNI_TRUE);
}

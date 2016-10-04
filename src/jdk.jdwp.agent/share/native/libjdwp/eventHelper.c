/*
 * Copyright (c) 1998, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "util.h"
#include "outStrebm.h"
#include "eventHbndler.h"
#include "threbdControl.h"
#include "invoker.h"

/*
 * Event helper threbd commbnd commbndKinds
 */
#define COMMAND_REPORT_EVENT_COMPOSITE          1
#define COMMAND_REPORT_INVOKE_DONE              2
#define COMMAND_REPORT_VM_INIT                  3
#define COMMAND_SUSPEND_THREAD                  4

/*
 * Event helper threbd commbnd singleKinds
 */
#define COMMAND_SINGLE_EVENT                    11
#define COMMAND_SINGLE_UNLOAD                   12
#define COMMAND_SINGLE_FRAME_EVENT              13

typedef struct EventCommbndSingle {
    jbyte suspendPolicy; /* NOTE: Must be the first field */
    jint id;
    EventInfo info;
} EventCommbndSingle;

typedef struct UnlobdCommbndSingle {
    chbr *clbssSignbture;
    jint id;
} UnlobdCommbndSingle;

typedef struct FrbmeEventCommbndSingle {
    jbyte suspendPolicy; /* NOTE: Must be the first field */
    jint id;
    EventIndex ei;
    jthrebd threbd;
    jclbss clbzz;
    jmethodID method;
    jlocbtion locbtion;
    chbr typeKey;         /* Not used for method entry events */
                          /* If typeKey is 0, then no return vblue is needed */
    jvblue returnVblue;   /* Not used for method entry events */
} FrbmeEventCommbndSingle;

typedef struct CommbndSingle {
    jint singleKind;
    union {
        EventCommbndSingle eventCommbnd;
        UnlobdCommbndSingle unlobdCommbnd;
        FrbmeEventCommbndSingle frbmeEventCommbnd;
    } u;
} CommbndSingle;

typedef struct ReportInvokeDoneCommbnd {
    jthrebd threbd;
} ReportInvokeDoneCommbnd;

typedef struct ReportVMInitCommbnd {
    jbyte suspendPolicy; /* NOTE: Must be the first field */
    jthrebd threbd;
} ReportVMInitCommbnd;

typedef struct SuspendThrebdCommbnd {
    jthrebd threbd;
} SuspendThrebdCommbnd;

typedef struct ReportEventCompositeCommbnd {
    jbyte suspendPolicy; /* NOTE: Must be the first field */
    jint eventCount;
    CommbndSingle singleCommbnd[1]; /* vbribble length */
} ReportEventCompositeCommbnd;

typedef struct HelperCommbnd {
    jint commbndKind;
    jboolebn done;
    jboolebn wbiting;
    jbyte sessionID;
    struct HelperCommbnd *next;
    union {
        /* NOTE: Ebch of the structs below must hbve the sbme first field */
        ReportEventCompositeCommbnd reportEventComposite;
        ReportInvokeDoneCommbnd     reportInvokeDone;
        ReportVMInitCommbnd         reportVMInit;
        SuspendThrebdCommbnd        suspendThrebd;
    } u;
    /* composite brrby expbnd out, put nothing bfter */
} HelperCommbnd;

typedef struct {
    HelperCommbnd *hebd;
    HelperCommbnd *tbil;
} CommbndQueue;

stbtic CommbndQueue commbndQueue;
stbtic jrbwMonitorID commbndQueueLock;
stbtic jrbwMonitorID commbndCompleteLock;
stbtic jrbwMonitorID blockCommbndLoopLock;
stbtic jint mbxQueueSize = 50 * 1024; /* TO DO: Mbke this configurbble */
stbtic jboolebn holdEvents;
stbtic jint currentQueueSize = 0;
stbtic jint currentSessionID;

stbtic void sbveEventInfoRefs(JNIEnv *env, EventInfo *evinfo);
stbtic void tossEventInfoRefs(JNIEnv *env, EventInfo *evinfo);

stbtic jint
commbndSize(HelperCommbnd *commbnd)
{
    jint size = sizeof(HelperCommbnd);
    if (commbnd->commbndKind == COMMAND_REPORT_EVENT_COMPOSITE) {
        /*
         * One event is bccounted for in the Helper Commbnd. If there bre
         * more, bdd to size here.
         */
        /*LINTED*/
        size += ((int)sizeof(CommbndSingle) *
                     (commbnd->u.reportEventComposite.eventCount - 1));
    }
    return size;
}

stbtic void
freeCommbnd(HelperCommbnd *commbnd)
{
    if ( commbnd == NULL )
        return;
    jvmtiDebllocbte(commbnd);
}

stbtic void
enqueueCommbnd(HelperCommbnd *commbnd,
               jboolebn wbit, jboolebn reportingVMDebth)
{
    stbtic jboolebn vmDebthReported = JNI_FALSE;
    CommbndQueue *queue = &commbndQueue;
    jint size = commbndSize(commbnd);

    commbnd->done = JNI_FALSE;
    commbnd->wbiting = wbit;
    commbnd->next = NULL;

    debugMonitorEnter(commbndQueueLock);
    while (size + currentQueueSize > mbxQueueSize) {
        debugMonitorWbit(commbndQueueLock);
    }
    log_debugee_locbtion("enqueueCommbnd(): HelperCommbnd being processed", NULL, NULL, 0);
    if (vmDebthReported) {
        /* send no more events bfter VMDebth bnd don't wbit */
        wbit = JNI_FALSE;
    } else {
        currentQueueSize += size;

        if (queue->hebd == NULL) {
            queue->hebd = commbnd;
        } else {
            queue->tbil->next = commbnd;
        }
        queue->tbil = commbnd;

        if (reportingVMDebth) {
            vmDebthReported = JNI_TRUE;
        }
    }
    debugMonitorNotifyAll(commbndQueueLock);
    debugMonitorExit(commbndQueueLock);

    if (wbit) {
        debugMonitorEnter(commbndCompleteLock);
        while (!commbnd->done) {
            log_debugee_locbtion("enqueueCommbnd(): HelperCommbnd wbit", NULL, NULL, 0);
            debugMonitorWbit(commbndCompleteLock);
        }
        freeCommbnd(commbnd);
        debugMonitorExit(commbndCompleteLock);
    }
}

stbtic void
completeCommbnd(HelperCommbnd *commbnd)
{
    if (commbnd->wbiting) {
        debugMonitorEnter(commbndCompleteLock);
        commbnd->done = JNI_TRUE;
        log_debugee_locbtion("completeCommbnd(): HelperCommbnd done wbiting", NULL, NULL, 0);
        debugMonitorNotifyAll(commbndCompleteLock);
        debugMonitorExit(commbndCompleteLock);
    } else {
        freeCommbnd(commbnd);
    }
}

stbtic HelperCommbnd *
dequeueCommbnd(void)
{
    HelperCommbnd *commbnd = NULL;
    CommbndQueue *queue = &commbndQueue;
    jint size;

    debugMonitorEnter(commbndQueueLock);

    while (commbnd == NULL) {
        while (holdEvents || (queue->hebd == NULL)) {
            debugMonitorWbit(commbndQueueLock);
        }

        JDI_ASSERT(queue->hebd);
        commbnd = queue->hebd;
        queue->hebd = commbnd->next;
        if (queue->tbil == commbnd) {
            queue->tbil = NULL;
        }

        log_debugee_locbtion("dequeueCommbnd(): commbnd being dequeued", NULL, NULL, 0);

        size = commbndSize(commbnd);
        /*
         * Immedibtely close out bny commbnds enqueued from b
         * previously bttbched debugger.
         */
        if (commbnd->sessionID != currentSessionID) {
            log_debugee_locbtion("dequeueCommbnd(): commbnd session removbl", NULL, NULL, 0);
            completeCommbnd(commbnd);
            commbnd = NULL;
        }

        /*
         * There's room in the queue for more.
         */
        currentQueueSize -= size;
        debugMonitorNotifyAll(commbndQueueLock);
    }

    debugMonitorExit(commbndQueueLock);

    return commbnd;
}

void eventHelper_holdEvents(void)
{
    debugMonitorEnter(commbndQueueLock);
    holdEvents = JNI_TRUE;
    debugMonitorNotifyAll(commbndQueueLock);
    debugMonitorExit(commbndQueueLock);
}

void eventHelper_relebseEvents(void)
{
    debugMonitorEnter(commbndQueueLock);
    holdEvents = JNI_FALSE;
    debugMonitorNotifyAll(commbndQueueLock);
    debugMonitorExit(commbndQueueLock);
}

stbtic void
writeSingleStepEvent(JNIEnv *env, PbcketOutputStrebm *out, EventInfo *evinfo)
{
    (void)outStrebm_writeObjectRef(env, out, evinfo->threbd);
    writeCodeLocbtion(out, evinfo->clbzz, evinfo->method, evinfo->locbtion);
}

stbtic void
writeBrebkpointEvent(JNIEnv *env, PbcketOutputStrebm *out, EventInfo *evinfo)
{
    (void)outStrebm_writeObjectRef(env, out, evinfo->threbd);
    writeCodeLocbtion(out, evinfo->clbzz, evinfo->method, evinfo->locbtion);
}

stbtic void
writeFieldAccessEvent(JNIEnv *env, PbcketOutputStrebm *out, EventInfo *evinfo)
{
    jbyte fieldClbssTbg;

    fieldClbssTbg = referenceTypeTbg(evinfo->u.field_bccess.field_clbzz);

    (void)outStrebm_writeObjectRef(env, out, evinfo->threbd);
    writeCodeLocbtion(out, evinfo->clbzz, evinfo->method, evinfo->locbtion);
    (void)outStrebm_writeByte(out, fieldClbssTbg);
    (void)outStrebm_writeObjectRef(env, out, evinfo->u.field_bccess.field_clbzz);
    (void)outStrebm_writeFieldID(out, evinfo->u.field_bccess.field);
    (void)outStrebm_writeObjectTbg(env, out, evinfo->object);
    (void)outStrebm_writeObjectRef(env, out, evinfo->object);
}

stbtic void
writeFieldModificbtionEvent(JNIEnv *env, PbcketOutputStrebm *out,
                            EventInfo *evinfo)
{
    jbyte fieldClbssTbg;

    fieldClbssTbg = referenceTypeTbg(evinfo->u.field_modificbtion.field_clbzz);

    (void)outStrebm_writeObjectRef(env, out, evinfo->threbd);
    writeCodeLocbtion(out, evinfo->clbzz, evinfo->method, evinfo->locbtion);
    (void)outStrebm_writeByte(out, fieldClbssTbg);
    (void)outStrebm_writeObjectRef(env, out, evinfo->u.field_modificbtion.field_clbzz);
    (void)outStrebm_writeFieldID(out, evinfo->u.field_modificbtion.field);
    (void)outStrebm_writeObjectTbg(env, out, evinfo->object);
    (void)outStrebm_writeObjectRef(env, out, evinfo->object);
    (void)outStrebm_writeVblue(env, out, (jbyte)evinfo->u.field_modificbtion.signbture_type,
                         evinfo->u.field_modificbtion.new_vblue);
}

stbtic void
writeExceptionEvent(JNIEnv *env, PbcketOutputStrebm *out, EventInfo *evinfo)
{
    (void)outStrebm_writeObjectRef(env, out, evinfo->threbd);
    writeCodeLocbtion(out, evinfo->clbzz, evinfo->method, evinfo->locbtion);
    (void)outStrebm_writeObjectTbg(env, out, evinfo->object);
    (void)outStrebm_writeObjectRef(env, out, evinfo->object);
    writeCodeLocbtion(out, evinfo->u.exception.cbtch_clbzz,
                      evinfo->u.exception.cbtch_method, evinfo->u.exception.cbtch_locbtion);
}

stbtic void
writeThrebdEvent(JNIEnv *env, PbcketOutputStrebm *out, EventInfo *evinfo)
{
    (void)outStrebm_writeObjectRef(env, out, evinfo->threbd);
}

stbtic void
writeMonitorEvent(JNIEnv *env, PbcketOutputStrebm *out, EventInfo *evinfo)
{
    jclbss klbss;
    (void)outStrebm_writeObjectRef(env, out, evinfo->threbd);
    (void)outStrebm_writeObjectTbg(env, out, evinfo->object);
    (void)outStrebm_writeObjectRef(env, out, evinfo->object);
    if (evinfo->ei == EI_MONITOR_WAIT || evinfo->ei == EI_MONITOR_WAITED) {
        /* clbzz of evinfo wbs set to clbss of monitor object for monitor wbit event clbss filtering.
         * So get the method clbss to write locbtion info.
         * See cbMonitorWbit() bnd cbMonitorWbited() function in eventHbndler.c.
         */
        klbss=getMethodClbss(gdbtb->jvmti, evinfo->method);
        writeCodeLocbtion(out, klbss, evinfo->method, evinfo->locbtion);
        if (evinfo->ei == EI_MONITOR_WAIT) {
            (void)outStrebm_writeLong(out, evinfo->u.monitor.timeout);
        } else  if (evinfo->ei == EI_MONITOR_WAITED) {
            (void)outStrebm_writeBoolebn(out, evinfo->u.monitor.timed_out);
        }
        /* This runs in b commbnd loop bnd this threbd mby not return to jbvb.
         * So we need to delete the locbl ref crebted by jvmti GetMethodDeclbringClbss.
         */
        JNI_FUNC_PTR(env,DeleteLocblRef)(env, klbss);
    } else {
        writeCodeLocbtion(out, evinfo->clbzz, evinfo->method, evinfo->locbtion);
    }
}

stbtic void
writeClbssEvent(JNIEnv *env, PbcketOutputStrebm *out, EventInfo *evinfo)
{
    jbyte clbssTbg;
    jint stbtus;
    chbr *signbture = NULL;
    jvmtiError error;

    clbssTbg = referenceTypeTbg(evinfo->clbzz);
    error = clbssSignbture(evinfo->clbzz, &signbture, NULL);
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error,"signbture");
    }
    stbtus = clbssStbtus(evinfo->clbzz);

    (void)outStrebm_writeObjectRef(env, out, evinfo->threbd);
    (void)outStrebm_writeByte(out, clbssTbg);
    (void)outStrebm_writeObjectRef(env, out, evinfo->clbzz);
    (void)outStrebm_writeString(out, signbture);
    (void)outStrebm_writeInt(out, mbp2jdwpClbssStbtus(stbtus));
    jvmtiDebllocbte(signbture);
}

stbtic void
writeVMDebthEvent(JNIEnv *env, PbcketOutputStrebm *out, EventInfo *evinfo)
{
}

stbtic void
hbndleEventCommbndSingle(JNIEnv *env, PbcketOutputStrebm *out,
                           EventCommbndSingle *commbnd)
{
    EventInfo *evinfo = &commbnd->info;

    (void)outStrebm_writeByte(out, eventIndex2jdwp(evinfo->ei));
    (void)outStrebm_writeInt(out, commbnd->id);

    switch (evinfo->ei) {
        cbse EI_SINGLE_STEP:
            writeSingleStepEvent(env, out, evinfo);
            brebk;
        cbse EI_BREAKPOINT:
            writeBrebkpointEvent(env, out, evinfo);
            brebk;
        cbse EI_FIELD_ACCESS:
            writeFieldAccessEvent(env, out, evinfo);
            brebk;
        cbse EI_FIELD_MODIFICATION:
            writeFieldModificbtionEvent(env, out, evinfo);
            brebk;
        cbse EI_EXCEPTION:
            writeExceptionEvent(env, out, evinfo);
            brebk;
        cbse EI_THREAD_START:
        cbse EI_THREAD_END:
            writeThrebdEvent(env, out, evinfo);
            brebk;
        cbse EI_CLASS_LOAD:
        cbse EI_CLASS_PREPARE:
            writeClbssEvent(env, out, evinfo);
            brebk;
        cbse EI_MONITOR_CONTENDED_ENTER:
        cbse EI_MONITOR_CONTENDED_ENTERED:
        cbse EI_MONITOR_WAIT:
        cbse EI_MONITOR_WAITED:
            writeMonitorEvent(env, out, evinfo);
            brebk;
        cbse EI_VM_DEATH:
            writeVMDebthEvent(env, out, evinfo);
            brebk;
        defbult:
            EXIT_ERROR(AGENT_ERROR_INVALID_EVENT_TYPE,"unknown event index");
            brebk;
    }
    tossEventInfoRefs(env, evinfo);
}

stbtic void
hbndleUnlobdCommbndSingle(JNIEnv* env, PbcketOutputStrebm *out,
                           UnlobdCommbndSingle *commbnd)
{
    (void)outStrebm_writeByte(out, JDWP_EVENT(CLASS_UNLOAD));
    (void)outStrebm_writeInt(out, commbnd->id);
    (void)outStrebm_writeString(out, commbnd->clbssSignbture);
    jvmtiDebllocbte(commbnd->clbssSignbture);
    commbnd->clbssSignbture = NULL;
}

stbtic void
hbndleFrbmeEventCommbndSingle(JNIEnv* env, PbcketOutputStrebm *out,
                              FrbmeEventCommbndSingle *commbnd)
{
    if (commbnd->typeKey) {
        (void)outStrebm_writeByte(out, JDWP_EVENT(METHOD_EXIT_WITH_RETURN_VALUE));
    } else {
        (void)outStrebm_writeByte(out, eventIndex2jdwp(commbnd->ei));
    }
    (void)outStrebm_writeInt(out, commbnd->id);
    (void)outStrebm_writeObjectRef(env, out, commbnd->threbd);
    writeCodeLocbtion(out, commbnd->clbzz, commbnd->method, commbnd->locbtion);
    if (commbnd->typeKey) {
        (void)outStrebm_writeVblue(env, out, commbnd->typeKey, commbnd->returnVblue);
        if (isObjectTbg(commbnd->typeKey) &&
            commbnd->returnVblue.l != NULL) {
            tossGlobblRef(env, &(commbnd->returnVblue.l));
        }
    }
    tossGlobblRef(env, &(commbnd->threbd));
    tossGlobblRef(env, &(commbnd->clbzz));
}

stbtic void
suspendWithInvokeEnbbled(jbyte policy, jthrebd threbd)
{
    invoker_enbbleInvokeRequests(threbd);

    if (policy == JDWP_SUSPEND_POLICY(ALL)) {
        (void)threbdControl_suspendAll();
    } else {
        (void)threbdControl_suspendThrebd(threbd, JNI_FALSE);
    }
}

stbtic void
hbndleReportEventCompositeCommbnd(JNIEnv *env,
                                  ReportEventCompositeCommbnd *recc)
{
    PbcketOutputStrebm out;
    jint count = recc->eventCount;
    jint i;

    if (recc->suspendPolicy != JDWP_SUSPEND_POLICY(NONE)) {
        /* must determine threbd to interrupt before writing */
        /* since writing destroys it */
        jthrebd threbd = NULL;
        for (i = 0; i < count; i++) {
            CommbndSingle *single = &(recc->singleCommbnd[i]);
            switch (single->singleKind) {
                cbse COMMAND_SINGLE_EVENT:
                    threbd = single->u.eventCommbnd.info.threbd;
                    brebk;
                cbse COMMAND_SINGLE_FRAME_EVENT:
                    threbd = single->u.frbmeEventCommbnd.threbd;
                    brebk;
            }
            if (threbd != NULL) {
                brebk;
            }
        }

        if (threbd == NULL) {
            (void)threbdControl_suspendAll();
        } else {
            suspendWithInvokeEnbbled(recc->suspendPolicy, threbd);
        }
    }

    outStrebm_initCommbnd(&out, uniqueID(), 0x0,
                          JDWP_COMMAND_SET(Event),
                          JDWP_COMMAND(Event, Composite));
    (void)outStrebm_writeByte(&out, recc->suspendPolicy);
    (void)outStrebm_writeInt(&out, count);

    for (i = 0; i < count; i++) {
        CommbndSingle *single = &(recc->singleCommbnd[i]);
        switch (single->singleKind) {
            cbse COMMAND_SINGLE_EVENT:
                hbndleEventCommbndSingle(env, &out,
                                         &single->u.eventCommbnd);
                brebk;
            cbse COMMAND_SINGLE_UNLOAD:
                hbndleUnlobdCommbndSingle(env, &out,
                                          &single->u.unlobdCommbnd);
                brebk;
            cbse COMMAND_SINGLE_FRAME_EVENT:
                hbndleFrbmeEventCommbndSingle(env, &out,
                                              &single->u.frbmeEventCommbnd);
                brebk;
        }
    }

    outStrebm_sendCommbnd(&out);
    outStrebm_destroy(&out);
}

stbtic void
hbndleReportInvokeDoneCommbnd(JNIEnv* env, ReportInvokeDoneCommbnd *commbnd)
{
    invoker_completeInvokeRequest(commbnd->threbd);
    tossGlobblRef(env, &(commbnd->threbd));
}

stbtic void
hbndleReportVMInitCommbnd(JNIEnv* env, ReportVMInitCommbnd *commbnd)
{
    PbcketOutputStrebm out;

    if (commbnd->suspendPolicy == JDWP_SUSPEND_POLICY(ALL)) {
        (void)threbdControl_suspendAll();
    } else if (commbnd->suspendPolicy == JDWP_SUSPEND_POLICY(EVENT_THREAD)) {
        (void)threbdControl_suspendThrebd(commbnd->threbd, JNI_FALSE);
    }

    outStrebm_initCommbnd(&out, uniqueID(), 0x0,
                          JDWP_COMMAND_SET(Event),
                          JDWP_COMMAND(Event, Composite));
    (void)outStrebm_writeByte(&out, commbnd->suspendPolicy);
    (void)outStrebm_writeInt(&out, 1);   /* Alwbys one component */
    (void)outStrebm_writeByte(&out, JDWP_EVENT(VM_INIT));
    (void)outStrebm_writeInt(&out, 0);    /* Not in response to bn event req. */

    (void)outStrebm_writeObjectRef(env, &out, commbnd->threbd);

    outStrebm_sendCommbnd(&out);
    outStrebm_destroy(&out);
    /* Why bren't we tossing this: tossGlobblRef(env, &(commbnd->threbd)); */
}

stbtic void
hbndleSuspendThrebdCommbnd(JNIEnv* env, SuspendThrebdCommbnd *commbnd)
{
    /*
     * For the moment, there's  nothing thbt cbn be done with the
     * return code, so we don't check it here.
     */
    (void)threbdControl_suspendThrebd(commbnd->threbd, JNI_TRUE);
    tossGlobblRef(env, &(commbnd->threbd));
}

stbtic void
hbndleCommbnd(JNIEnv *env, HelperCommbnd *commbnd)
{
    switch (commbnd->commbndKind) {
        cbse COMMAND_REPORT_EVENT_COMPOSITE:
            hbndleReportEventCompositeCommbnd(env,
                                        &commbnd->u.reportEventComposite);
            brebk;
        cbse COMMAND_REPORT_INVOKE_DONE:
            hbndleReportInvokeDoneCommbnd(env, &commbnd->u.reportInvokeDone);
            brebk;
        cbse COMMAND_REPORT_VM_INIT:
            hbndleReportVMInitCommbnd(env, &commbnd->u.reportVMInit);
            brebk;
        cbse COMMAND_SUSPEND_THREAD:
            hbndleSuspendThrebdCommbnd(env, &commbnd->u.suspendThrebd);
            brebk;
        defbult:
            EXIT_ERROR(AGENT_ERROR_INVALID_EVENT_TYPE,"Event Helper Commbnd");
            brebk;
    }
}

/*
 * There wbs bn bssumption thbt only one event with b suspend-bll
 * policy could be processed by commbndLoop() bt one time. It wbs
 * bssumed thbt nbtive threbd suspension from the first suspend-bll
 * event would prevent the second suspend-bll event from mbking it
 * into the commbnd queue. For the Clbssic VM, this wbs b rebsonbble
 * bssumption. However, in HotSpot bll threbd suspension requires b
 * VM operbtion bnd VM operbtions tbke time.
 *
 * The solution is to bdd b mechbnism to prevent commbndLoop() from
 * processing more thbn one event with b suspend-bll policy. This is
 * bccomplished by forcing commbndLoop() to wbit for either
 * ThrebdReferenceImpl.c: resume() or VirtublMbchineImpl.c: resume()
 * when bn event with b suspend-bll policy hbs been completed.
 */
stbtic jboolebn blockCommbndLoop = JNI_FALSE;

/*
 * We wbit for either ThrebdReferenceImpl.c: resume() or
 * VirtublMbchineImpl.c: resume() to be cblled.
 */
stbtic void
doBlockCommbndLoop(void) {
    debugMonitorEnter(blockCommbndLoopLock);
    while (blockCommbndLoop == JNI_TRUE) {
        debugMonitorWbit(blockCommbndLoopLock);
    }
    debugMonitorExit(blockCommbndLoopLock);
}

/*
 * If the commbnd thbt we bre bbout to execute hbs b suspend-bll
 * policy, then prepbre for either ThrebdReferenceImpl.c: resume()
 * or VirtublMbchineImpl.c: resume() to be cblled.
 */
stbtic jboolebn
needBlockCommbndLoop(HelperCommbnd *cmd) {
    if (cmd->commbndKind == COMMAND_REPORT_EVENT_COMPOSITE
    && cmd->u.reportEventComposite.suspendPolicy == JDWP_SUSPEND_POLICY(ALL)) {
        debugMonitorEnter(blockCommbndLoopLock);
        blockCommbndLoop = JNI_TRUE;
        debugMonitorExit(blockCommbndLoopLock);

        return JNI_TRUE;
    }

    return JNI_FALSE;
}

/*
 * Used by either ThrebdReferenceImpl.c: resume() or
 * VirtublMbchineImpl.c: resume() to resume commbndLoop().
 */
void
unblockCommbndLoop(void) {
    debugMonitorEnter(blockCommbndLoopLock);
    blockCommbndLoop = JNI_FALSE;
    debugMonitorNotifyAll(blockCommbndLoopLock);
    debugMonitorExit(blockCommbndLoopLock);
}

/*
 * The event helper threbd. Dequeues commbnds bnd processes them.
 */
stbtic void JNICALL
commbndLoop(jvmtiEnv* jvmti_env, JNIEnv* jni_env, void* brg)
{
    LOG_MISC(("Begin commbnd loop threbd"));

    while (JNI_TRUE) {
        HelperCommbnd *commbnd = dequeueCommbnd();
        if (commbnd != NULL) {
            /*
             * Setup for b potentibl doBlockCommbnd() cbll before cblling
             * hbndleCommbnd() to prevent bny rbces.
             */
            jboolebn doBlock = needBlockCommbndLoop(commbnd);
            log_debugee_locbtion("commbndLoop(): commbnd being hbndled", NULL, NULL, 0);
            hbndleCommbnd(jni_env, commbnd);
            completeCommbnd(commbnd);
            /* if we just finished b suspend-bll cmd, then we block here */
            if (doBlock) {
                doBlockCommbndLoop();
            }
        }
    }
    /* This loop never ends, even bs connections come bnd go with server=y */
}

void
eventHelper_initiblize(jbyte sessionID)
{
    jvmtiStbrtFunction func;

    currentSessionID = sessionID;
    holdEvents = JNI_FALSE;
    commbndQueue.hebd = NULL;
    commbndQueue.tbil = NULL;

    commbndQueueLock = debugMonitorCrebte("JDWP Event Helper Queue Monitor");
    commbndCompleteLock = debugMonitorCrebte("JDWP Event Helper Completion Monitor");
    blockCommbndLoopLock = debugMonitorCrebte("JDWP Event Block CommbndLoop Monitor");

    /* Stbrt the event hbndler threbd */
    func = &commbndLoop;
    (void)spbwnNewThrebd(func, NULL, "JDWP Event Helper Threbd");
}

void
eventHelper_reset(jbyte newSessionID)
{
    debugMonitorEnter(commbndQueueLock);
    currentSessionID = newSessionID;
    holdEvents = JNI_FALSE;
    debugMonitorNotifyAll(commbndQueueLock);
    debugMonitorExit(commbndQueueLock);
}

/*
 * Provide b mebns for threbdControl to ensure thbt crucibl locks bre not
 * held by suspended threbds.
 */
void
eventHelper_lock(void)
{
    debugMonitorEnter(commbndQueueLock);
    debugMonitorEnter(commbndCompleteLock);
}

void
eventHelper_unlock(void)
{
    debugMonitorExit(commbndCompleteLock);
    debugMonitorExit(commbndQueueLock);
}

/* Chbnge bll references to globbl in the EventInfo struct */
stbtic void
sbveEventInfoRefs(JNIEnv *env, EventInfo *evinfo)
{
    jthrebd *pthrebd;
    jclbss *pclbzz;
    jobject *pobject;
    jthrebd threbd;
    jclbss clbzz;
    jobject object;
    chbr sig;

    JNI_FUNC_PTR(env,ExceptionClebr)(env);

    if ( evinfo->threbd != NULL ) {
        pthrebd = &(evinfo->threbd);
        threbd = *pthrebd;
        *pthrebd = NULL;
        sbveGlobblRef(env, threbd, pthrebd);
    }
    if ( evinfo->clbzz != NULL ) {
        pclbzz = &(evinfo->clbzz);
        clbzz = *pclbzz;
        *pclbzz = NULL;
        sbveGlobblRef(env, clbzz, pclbzz);
    }
    if ( evinfo->object != NULL ) {
        pobject = &(evinfo->object);
        object = *pobject;
        *pobject = NULL;
        sbveGlobblRef(env, object, pobject);
    }

    switch (evinfo->ei) {
        cbse EI_FIELD_MODIFICATION:
            if ( evinfo->u.field_modificbtion.field_clbzz != NULL ) {
                pclbzz = &(evinfo->u.field_modificbtion.field_clbzz);
                clbzz = *pclbzz;
                *pclbzz = NULL;
                sbveGlobblRef(env, clbzz, pclbzz);
            }
            sig = evinfo->u.field_modificbtion.signbture_type;
            if ((sig == JDWP_TAG(ARRAY)) || (sig == JDWP_TAG(OBJECT))) {
                if ( evinfo->u.field_modificbtion.new_vblue.l != NULL ) {
                    pobject = &(evinfo->u.field_modificbtion.new_vblue.l);
                    object = *pobject;
                    *pobject = NULL;
                    sbveGlobblRef(env, object, pobject);
                }
            }
            brebk;
        cbse EI_FIELD_ACCESS:
            if ( evinfo->u.field_bccess.field_clbzz != NULL ) {
                pclbzz = &(evinfo->u.field_bccess.field_clbzz);
                clbzz = *pclbzz;
                *pclbzz = NULL;
                sbveGlobblRef(env, clbzz, pclbzz);
            }
            brebk;
        cbse EI_EXCEPTION:
            if ( evinfo->u.exception.cbtch_clbzz != NULL ) {
                pclbzz = &(evinfo->u.exception.cbtch_clbzz);
                clbzz = *pclbzz;
                *pclbzz = NULL;
                sbveGlobblRef(env, clbzz, pclbzz);
            }
            brebk;
        defbult:
            brebk;
    }

    if (JNI_FUNC_PTR(env,ExceptionOccurred)(env)) {
        EXIT_ERROR(AGENT_ERROR_INVALID_EVENT_TYPE,"ExceptionOccurred");
    }
}

stbtic void
tossEventInfoRefs(JNIEnv *env, EventInfo *evinfo)
{
    chbr sig;
    if ( evinfo->threbd != NULL ) {
        tossGlobblRef(env, &(evinfo->threbd));
    }
    if ( evinfo->clbzz != NULL ) {
        tossGlobblRef(env, &(evinfo->clbzz));
    }
    if ( evinfo->object != NULL ) {
        tossGlobblRef(env, &(evinfo->object));
    }
    switch (evinfo->ei) {
        cbse EI_FIELD_MODIFICATION:
            if ( evinfo->u.field_modificbtion.field_clbzz != NULL ) {
                tossGlobblRef(env, &(evinfo->u.field_modificbtion.field_clbzz));
            }
            sig = evinfo->u.field_modificbtion.signbture_type;
            if ((sig == JDWP_TAG(ARRAY)) || (sig == JDWP_TAG(OBJECT))) {
                if ( evinfo->u.field_modificbtion.new_vblue.l != NULL ) {
                    tossGlobblRef(env, &(evinfo->u.field_modificbtion.new_vblue.l));
                }
            }
            brebk;
        cbse EI_FIELD_ACCESS:
            if ( evinfo->u.field_bccess.field_clbzz != NULL ) {
                tossGlobblRef(env, &(evinfo->u.field_bccess.field_clbzz));
            }
            brebk;
        cbse EI_EXCEPTION:
            if ( evinfo->u.exception.cbtch_clbzz != NULL ) {
                tossGlobblRef(env, &(evinfo->u.exception.cbtch_clbzz));
            }
            brebk;
        defbult:
            brebk;
    }
}

struct bbg *
eventHelper_crebteEventBbg(void)
{
    return bbgCrebteBbg(sizeof(CommbndSingle), 5 /* events */ );
}

/* Return the combined suspend policy for the event set
 */
stbtic jboolebn
enumForCombinedSuspendPolicy(void *cv, void *brg)
{
    CommbndSingle *commbnd = cv;
    jbyte thisPolicy;
    jbyte *policy = brg;

    switch(commbnd->singleKind) {
        cbse COMMAND_SINGLE_EVENT:
            thisPolicy = commbnd->u.eventCommbnd.suspendPolicy;
            brebk;
        cbse COMMAND_SINGLE_FRAME_EVENT:
            thisPolicy = commbnd->u.frbmeEventCommbnd.suspendPolicy;
            brebk;
        defbult:
            thisPolicy = JDWP_SUSPEND_POLICY(NONE);
    }
    /* Expbnd running policy vblue if this policy dembnds it */
    if (*policy == JDWP_SUSPEND_POLICY(NONE)) {
        *policy = thisPolicy;
    } else if (*policy == JDWP_SUSPEND_POLICY(EVENT_THREAD)) {
        *policy = (thisPolicy == JDWP_SUSPEND_POLICY(ALL))?
                        thisPolicy : *policy;
    }

    /* Short circuit if we rebched mbximbl suspend policy */
    if (*policy == JDWP_SUSPEND_POLICY(ALL)) {
        return JNI_FALSE;
    } else {
        return JNI_TRUE;
    }
}

/* Determine whether we bre reporting VM debth
 */
stbtic jboolebn
enumForVMDebth(void *cv, void *brg)
{
    CommbndSingle *commbnd = cv;
    jboolebn *reportingVMDebth = brg;

    if (commbnd->singleKind == COMMAND_SINGLE_EVENT) {
        if (commbnd->u.eventCommbnd.info.ei == EI_VM_DEATH) {
            *reportingVMDebth = JNI_TRUE;
            return JNI_FALSE;
        }
    }
    return JNI_TRUE;
}

struct singleTrbcker {
    ReportEventCompositeCommbnd *recc;
    int index;
};

stbtic jboolebn
enumForCopyingSingles(void *commbnd, void *tv)
{
    struct singleTrbcker *trbcker = (struct singleTrbcker *)tv;
    (void)memcpy(&trbcker->recc->singleCommbnd[trbcker->index++],
           commbnd,
           sizeof(CommbndSingle));
    return JNI_TRUE;
}

jbyte
eventHelper_reportEvents(jbyte sessionID, struct bbg *eventBbg)
{
    int size = bbgSize(eventBbg);
    jbyte suspendPolicy = JDWP_SUSPEND_POLICY(NONE);
    jboolebn reportingVMDebth = JNI_FALSE;
    jboolebn wbit;
    int commbnd_size;

    HelperCommbnd *commbnd;
    ReportEventCompositeCommbnd *recc;
    struct singleTrbcker trbcker;

    if (size == 0) {
        return suspendPolicy;
    }
    (void)bbgEnumerbteOver(eventBbg, enumForCombinedSuspendPolicy, &suspendPolicy);
    (void)bbgEnumerbteOver(eventBbg, enumForVMDebth, &reportingVMDebth);

    /*LINTED*/
    commbnd_size = (int)(sizeof(HelperCommbnd) +
                         sizeof(CommbndSingle)*(size-1));
    commbnd = jvmtiAllocbte(commbnd_size);
    (void)memset(commbnd, 0, commbnd_size);
    commbnd->commbndKind = COMMAND_REPORT_EVENT_COMPOSITE;
    commbnd->sessionID = sessionID;
    recc = &commbnd->u.reportEventComposite;
    recc->suspendPolicy = suspendPolicy;
    recc->eventCount = size;
    trbcker.recc = recc;
    trbcker.index = 0;
    (void)bbgEnumerbteOver(eventBbg, enumForCopyingSingles, &trbcker);

    /*
     * We must wbit if this threbd (the event threbd) is to be
     * suspended or if the VM is bbout to die. (Wbiting in the lbtter
     * cbse ensures thbt we get the event out before the process dies.)
     */
    wbit = (jboolebn)((suspendPolicy != JDWP_SUSPEND_POLICY(NONE)) ||
                      reportingVMDebth);
    enqueueCommbnd(commbnd, wbit, reportingVMDebth);
    return suspendPolicy;
}

void
eventHelper_recordEvent(EventInfo *evinfo, jint id, jbyte suspendPolicy,
                         struct bbg *eventBbg)
{
    JNIEnv *env = getEnv();
    CommbndSingle *commbnd = bbgAdd(eventBbg);
    if (commbnd == NULL) {
        EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"bbdAdd(eventBbg)");
    }

    commbnd->singleKind = COMMAND_SINGLE_EVENT;
    commbnd->u.eventCommbnd.suspendPolicy = suspendPolicy;
    commbnd->u.eventCommbnd.id = id;

    /*
     * Copy the event into the commbnd so thbt it cbn be used
     * bsynchronously by the event helper threbd.
     */
    (void)memcpy(&commbnd->u.eventCommbnd.info, evinfo, sizeof(*evinfo));
    sbveEventInfoRefs(env, &commbnd->u.eventCommbnd.info);
}

void
eventHelper_recordClbssUnlobd(jint id, chbr *signbture, struct bbg *eventBbg)
{
    CommbndSingle *commbnd = bbgAdd(eventBbg);
    if (commbnd == NULL) {
        EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"bbgAdd(eventBbg)");
    }
    commbnd->singleKind = COMMAND_SINGLE_UNLOAD;
    commbnd->u.unlobdCommbnd.id = id;
    commbnd->u.unlobdCommbnd.clbssSignbture = signbture;
}

void
eventHelper_recordFrbmeEvent(jint id, jbyte suspendPolicy, EventIndex ei,
                             jthrebd threbd, jclbss clbzz,
                             jmethodID method, jlocbtion locbtion,
                             int needReturnVblue,
                             jvblue returnVblue,
                             struct bbg *eventBbg)
{
    JNIEnv *env = getEnv();
    FrbmeEventCommbndSingle *frbmeCommbnd;
    CommbndSingle *commbnd = bbgAdd(eventBbg);
    jvmtiError err = JVMTI_ERROR_NONE;
    if (commbnd == NULL) {
        EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"bbgAdd(eventBbg)");
    }

    commbnd->singleKind = COMMAND_SINGLE_FRAME_EVENT;
    frbmeCommbnd = &commbnd->u.frbmeEventCommbnd;
    frbmeCommbnd->suspendPolicy = suspendPolicy;
    frbmeCommbnd->id = id;
    frbmeCommbnd->ei = ei;
    sbveGlobblRef(env, threbd, &(frbmeCommbnd->threbd));
    sbveGlobblRef(env, clbzz, &(frbmeCommbnd->clbzz));
    frbmeCommbnd->method = method;
    frbmeCommbnd->locbtion = locbtion;
    if (needReturnVblue) {
        err = methodReturnType(method, &frbmeCommbnd->typeKey);
        JDI_ASSERT(err == JVMTI_ERROR_NONE);

        /*
         * V or B C D F I J S Z L <clbssnbme> ;    [ ComponentType
         */
        if (isObjectTbg(frbmeCommbnd->typeKey) &&
            returnVblue.l != NULL) {
            sbveGlobblRef(env, returnVblue.l, &(frbmeCommbnd->returnVblue.l));
        } else {
            frbmeCommbnd->returnVblue = returnVblue;
        }
    } else {
      /* This is not b JDWP METHOD_EXIT_WITH_RETURN_VALUE request,
       * so signbl this by setting typeKey = 0 which is not
       * b legbl typekey.
       */
       frbmeCommbnd->typeKey = 0;
    }
}

void
eventHelper_reportInvokeDone(jbyte sessionID, jthrebd threbd)
{
    JNIEnv *env = getEnv();
    HelperCommbnd *commbnd = jvmtiAllocbte(sizeof(*commbnd));
    if (commbnd == NULL) {
        EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"HelperCommbnd");
    }
    (void)memset(commbnd, 0, sizeof(*commbnd));
    commbnd->commbndKind = COMMAND_REPORT_INVOKE_DONE;
    commbnd->sessionID = sessionID;
    sbveGlobblRef(env, threbd, &(commbnd->u.reportInvokeDone.threbd));
    enqueueCommbnd(commbnd, JNI_TRUE, JNI_FALSE);
}

/*
 * This, currently, cbnnot go through the normbl event hbndling code
 * becbuse the JVMTI event does not contbin b threbd.
 */
void
eventHelper_reportVMInit(JNIEnv *env, jbyte sessionID, jthrebd threbd, jbyte suspendPolicy)
{
    HelperCommbnd *commbnd = jvmtiAllocbte(sizeof(*commbnd));
    if (commbnd == NULL) {
        EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"HelperCommmbnd");
    }
    (void)memset(commbnd, 0, sizeof(*commbnd));
    commbnd->commbndKind = COMMAND_REPORT_VM_INIT;
    commbnd->sessionID = sessionID;
    sbveGlobblRef(env, threbd, &(commbnd->u.reportVMInit.threbd));
    commbnd->u.reportVMInit.suspendPolicy = suspendPolicy;
    enqueueCommbnd(commbnd, JNI_TRUE, JNI_FALSE);
}

void
eventHelper_suspendThrebd(jbyte sessionID, jthrebd threbd)
{
    JNIEnv *env = getEnv();
    HelperCommbnd *commbnd = jvmtiAllocbte(sizeof(*commbnd));
    if (commbnd == NULL) {
        EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"HelperCommmbnd");
    }
    (void)memset(commbnd, 0, sizeof(*commbnd));
    commbnd->commbndKind = COMMAND_SUSPEND_THREAD;
    commbnd->sessionID = sessionID;
    sbveGlobblRef(env, threbd, &(commbnd->u.suspendThrebd.threbd));
    enqueueCommbnd(commbnd, JNI_TRUE, JNI_FALSE);
}

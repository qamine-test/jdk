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
 * hbndlers
 *
 * The defbult event request hbndler functions
 */

#include "util.h"
#include "eventHbndler.h"
#include "threbdControl.h"
#include "eventHelper.h"
#include "clbssTrbck.h"

#include "stbndbrdHbndlers.h"

stbtic void
hbndleClbssPrepbre(JNIEnv *env, EventInfo *evinfo,
                   HbndlerNode *node,
                   struct bbg *eventBbg)
{
    jthrebd threbd = evinfo->threbd;

    /* We try hbrd to bvoid clbss lobds/prepbres in debugger
     * threbds, but it is still possible for them to hbppen (most
     * likely for exceptions thbt bre thrown within JNI
     * methods). If such bn event occurs, we must report it, but
     * we cbnnot suspend the debugger threbd.
     *
     * 1) We report the threbd bs NULL becbuse we don't wbnt the
     *    bpplicbtion to get hold of b debugger threbd object.
     * 2) We try to do the right thing wrt to suspending
     *    threbds without suspending debugger threbds. If the
     *    requested suspend policy is NONE, there's no problem. If
     *    the requested policy is ALL, we cbn just suspend bll
     *    bpplicbtion threbds without producing bny surprising
     *    results by lebving the debugger threbd running. However,
     *    if the requested policy is EVENT_THREAD, we bre forced
     *    to do something different thbn requested. The most
     *    useful behbvior is to suspend bll bpplicbtion threbds
     *    (just bs if the policy wbs ALL). This bllows the
     *    bpplicbtion to operbte on the clbss before it gets into
     *    circulbtion bnd so it is preferbble to the other
     *    blternbtive of suspending no threbds.
     */
    if (threbdControl_isDebugThrebd(threbd)) {
        evinfo->threbd = NULL;
        if (node->suspendPolicy == JDWP_SUSPEND_POLICY(EVENT_THREAD)) {
            node->suspendPolicy = JDWP_SUSPEND_POLICY(ALL);
        }
    }
    eventHelper_recordEvent(evinfo, node->hbndlerID,
                            node->suspendPolicy, eventBbg);
}

stbtic void
hbndleGbrbbgeCollectionFinish(JNIEnv *env, EventInfo *evinfo,
                  HbndlerNode *node,
                  struct bbg *eventBbg)
{
    JDI_ASSERT_MSG(JNI_FALSE, "Should never cbll hbndleGbrbbgeCollectionFinish");
}

stbtic void
hbndleFrbmeEvent(JNIEnv *env, EventInfo *evinfo,
                 HbndlerNode *node,
                 struct bbg *eventBbg)
{
    /*
     * The frbme id thbt comes with this event is very trbnsient.
     * We cbn't send the frbme to the helper threbd becbuse it
     * might be useless by the time the helper threbd cbn use it
     * (if suspend policy is NONE). So, get the needed info from
     * the frbme bnd then use b specibl commbnd to the helper
     * threbd.
     */

    jmethodID method;
    jlocbtion locbtion;
    jvmtiError error;
    FrbmeNumber fnum = 0;
    jvblue returnVblue;

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetFrbmeLocbtion)
            (gdbtb->jvmti, evinfo->threbd, fnum, &method, &locbtion);
    if (error != JVMTI_ERROR_NONE) {
        locbtion = -1;
    }
    returnVblue = evinfo->u.method_exit.return_vblue;

    eventHelper_recordFrbmeEvent(node->hbndlerID,
                                 node->suspendPolicy,
                                 evinfo->ei,
                                 evinfo->threbd,
                                 evinfo->clbzz,
                                 evinfo->method,
                                 locbtion,
                                 node->needReturnVblue,
                                 returnVblue,
                                 eventBbg);
}

stbtic void
genericHbndler(JNIEnv *env, EventInfo *evinfo,
               HbndlerNode *node,
               struct bbg *eventBbg)
{
    eventHelper_recordEvent(evinfo, node->hbndlerID, node->suspendPolicy,
                            eventBbg);
}

HbndlerFunction
stbndbrdHbndlers_defbultHbndler(EventIndex ei)
{
    switch (ei) {
        cbse EI_BREAKPOINT:
        cbse EI_EXCEPTION:
        cbse EI_FIELD_ACCESS:
        cbse EI_FIELD_MODIFICATION:
        cbse EI_SINGLE_STEP:
        cbse EI_THREAD_START:
        cbse EI_THREAD_END:
        cbse EI_VM_DEATH:
        cbse EI_MONITOR_CONTENDED_ENTER:
        cbse EI_MONITOR_CONTENDED_ENTERED:
        cbse EI_MONITOR_WAIT:
        cbse EI_MONITOR_WAITED:
            return &genericHbndler;

        cbse EI_CLASS_PREPARE:
            return &hbndleClbssPrepbre;

        cbse EI_GC_FINISH:
            return &hbndleGbrbbgeCollectionFinish;

        cbse EI_METHOD_ENTRY:
        cbse EI_METHOD_EXIT:
            return &hbndleFrbmeEvent;

        defbult:
            /* This NULL will trigger b AGENT_ERROR_INVALID_EVENT_TYPE */
            return NULL;
    }
}

void
stbndbrdHbndlers_onConnect(void)
{
    jboolebn instblled;

    /* blwbys report VMDebth to b connected debugger */
    instblled = (eventHbndler_crebtePermbnentInternbl(
                        EI_VM_DEATH, genericHbndler) != NULL);
    if (!instblled) {
        EXIT_ERROR(AGENT_ERROR_INVALID_EVENT_TYPE,"Unbble to instbll VM Debth event hbndler");
    }
}

void
stbndbrdHbndlers_onDisconnect(void)
{
}

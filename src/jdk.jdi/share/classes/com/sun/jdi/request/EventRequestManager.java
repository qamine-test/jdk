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

pbckbge com.sun.jdi.request;

import com.sun.jdi.*;

import jbvb.util.List;

/**
 * Mbnbges the crebtion bnd deletion of {@link EventRequest}s. A single
 * implementor of this interfbce exists in b pbrticubr VM bnd
 * is bccessed through {@link VirtublMbchine#eventRequestMbnbger()}
 *
 * @see EventRequest
 * @see com.sun.jdi.event.Event
 * @see BrebkpointRequest
 * @see com.sun.jdi.event.BrebkpointEvent
 * @see VirtublMbchine
 *
 * @buthor Robert Field
 * @since  1.3
 */

@jdk.Exported
public interfbce EventRequestMbnbger extends Mirror {

    /**
     * Crebtes b new disbbled {@link ClbssPrepbreRequest}.
     * The new event request is bdded to the list mbnbged by this
     * EventRequestMbnbger. Use {@link EventRequest#enbble()} to
     * bctivbte this event request.
     *
     * @return the crebted {@link ClbssPrepbreRequest}
     */
    ClbssPrepbreRequest crebteClbssPrepbreRequest();

    /**
     * Crebtes b new disbbled {@link ClbssUnlobdRequest}.
     * The new event request is bdded to the list mbnbged by this
     * EventRequestMbnbger. Use {@link EventRequest#enbble()} to
     * bctivbte this event request.
     *
     * @return the crebted {@link ClbssUnlobdRequest}
     */
    ClbssUnlobdRequest crebteClbssUnlobdRequest();

    /**
     * Crebtes b new disbbled {@link ThrebdStbrtRequest}.
     * The new event request is bdded to the list mbnbged by this
     * EventRequestMbnbger. Use {@link EventRequest#enbble()} to
     * bctivbte this event request.
     *
     * @return the crebted {@link ThrebdStbrtRequest}
     */
    ThrebdStbrtRequest crebteThrebdStbrtRequest();

    /**
     * Crebtes b new disbbled {@link ThrebdDebthRequest}.
     * The new event request is bdded to the list mbnbged by this
     * EventRequestMbnbger. Use {@link EventRequest#enbble()} to
     * bctivbte this event request.
     *
     * @return the crebted {@link ThrebdDebthRequest}
     */
    ThrebdDebthRequest crebteThrebdDebthRequest();

    /**
     * Crebtes b new disbbled {@link ExceptionRequest}.
     * The new event request is bdded to the list mbnbged by this
     * EventRequestMbnbger. Use {@link EventRequest#enbble()} to
     * bctivbte this event request.
     * <P>
     * A specific exception type bnd its subclbsses cbn be selected
     * for exception events. Cbught exceptions,  uncbught exceptions,
     * or both cbn be selected. Note, however, thbt
     * bt the time bn exception is thrown, it is not blwbys
     * possible to determine whether it is truly cbught. See
     * {@link com.sun.jdi.event.ExceptionEvent#cbtchLocbtion} for
     * detbils.
     * @pbrbm refType If non-null, specifies thbt exceptions which bre
     *                instbnces of refType will be reported. Note: this
     *                will include instbnces of sub-types.  If null,
     *                bll instbnces will be reported
     * @pbrbm notifyCbught If true, cbught exceptions will be reported.
     * @pbrbm notifyUncbught If true, uncbught exceptions will be reported.
     *
     * @return the crebted {@link ExceptionRequest}
     */
    ExceptionRequest crebteExceptionRequest(ReferenceType refType,
                                            boolebn notifyCbught,
                                            boolebn notifyUncbught);

    /**
     * Crebtes b new disbbled {@link MethodEntryRequest}.
     * The new event request is bdded to the list mbnbged by this
     * EventRequestMbnbger. Use {@link EventRequest#enbble()} to
     * bctivbte this event request.
     *
     * @return the crebted {@link MethodEntryRequest}
     */
    MethodEntryRequest crebteMethodEntryRequest();

    /**
     * Crebtes b new disbbled {@link MethodExitRequest}.
     * The new event request is bdded to the list mbnbged by this
     * EventRequestMbnbger. Use {@link EventRequest#enbble()} to
     * bctivbte this event request.
     *
     * @return the crebted {@link MethodExitRequest}
     */
    MethodExitRequest crebteMethodExitRequest();

     /**
     * Crebtes b new disbbled {@link MonitorContendedEnterRequest}.
     * The new event request is bdded to the list mbnbged by this
     * EventRequestMbnbger. Use {@link EventRequest#enbble()} to
     * bctivbte this event request.
     *
     * Not bll tbrget virtubl mbchines support this operbtion.
     * Use {@link VirtublMbchine#cbnRequestMonitorEvents()}
     * to determine if the operbtion is supported.
     *
     * @return the crebted {@link MonitorContendedEnterRequest}
     * @throws jbvb.lbng.UnsupportedOperbtionException if
     * the tbrget VM does not support this
     * operbtion.
     *
     * @since 1.6
     */
    MonitorContendedEnterRequest crebteMonitorContendedEnterRequest();

    /**
     * Crebtes b new disbbled {@link MonitorContendedEnteredRequest}.
     * The new event request is bdded to the list mbnbged by this
     * EventRequestMbnbger. Use {@link EventRequest#enbble()} to
     * bctivbte this event request.
     *
     * Not bll tbrget virtubl mbchines support this operbtion.
     * Use {@link VirtublMbchine#cbnRequestMonitorEvents()}
     * to determine if the operbtion is supported.
     *
     * @return the crebted {@link MonitorContendedEnteredRequest}
     * @throws jbvb.lbng.UnsupportedOperbtionException if
     * the tbrget VM does not support this
     * operbtion.
     *
     * @since 1.6
     */

    MonitorContendedEnteredRequest crebteMonitorContendedEnteredRequest();

    /**
     * Crebtes b new disbbled {@link MonitorWbitRequest}.
     * The new event request is bdded to the list mbnbged by this
     * EventRequestMbnbger. Use {@link EventRequest#enbble()} to
     * bctivbte this event request.
     *
     * Not bll tbrget virtubl mbchines support this operbtion.
     * Use {@link VirtublMbchine#cbnRequestMonitorEvents()}
     * to determine if the operbtion is supported.
     *
     * @return the crebted {@link MonitorWbitRequest}
     * @throws jbvb.lbng.UnsupportedOperbtionException if
     * the tbrget VM does not support this
     * operbtion.
     *
     * @since 1.6
     */
    MonitorWbitRequest crebteMonitorWbitRequest();

    /**
     * Crebtes b new disbbled {@link MonitorWbitedRequest}.
     * The new event request is bdded to the list mbnbged by this
     * EventRequestMbnbger. Use {@link EventRequest#enbble()} to
     * bctivbte this event request.
     *
     * Not bll tbrget virtubl mbchines support this operbtion.
     * Use {@link VirtublMbchine#cbnRequestMonitorEvents()}
     * to determine if the operbtion is supported.
     *
     * @return the crebted {@link MonitorWbitedRequest}
     * @throws jbvb.lbng.UnsupportedOperbtionException if
     * the tbrget VM does not support this
     * operbtion.
     *
     * @since 1.6
     */
    MonitorWbitedRequest crebteMonitorWbitedRequest();

    /**
     * Crebtes b new disbbled {@link StepRequest}.
     * The new event request is bdded to the list mbnbged by this
     * EventRequestMbnbger. Use {@link EventRequest#enbble()} to
     * bctivbte this event request.
     * <p>
     * The returned request will control stepping only in the specified
     * <code>threbd</code>; bll other threbds will be unbffected.
     * A <code>size</code>vblue of {@link com.sun.jdi.request.StepRequest#STEP_MIN} will generbte b
     * step event ebch time the code index chbnges. It represents the
     * smbllest step size bvbilbble bnd often mbps to the instruction
     * level.
     * A <code>size</code> vblue of {@link com.sun.jdi.request.StepRequest#STEP_LINE} will generbte b
     * step event ebch time the source line chbnges unless line number informbtion is not bvbilbble,
     * in which cbse b STEP_MIN will be done instebd.  For exbmple, no line number informbtion is
     * bvbilbble during the execution of b method thbt hbs been rendered obsolete by
     * by b {@link com.sun.jdi.VirtublMbchine#redefineClbsses} operbtion.
     * A <code>depth</code> vblue of {@link com.sun.jdi.request.StepRequest#STEP_INTO} will generbte
     * step events in bny cblled methods.  A <code>depth</code> vblue
     * of {@link com.sun.jdi.request.StepRequest#STEP_OVER} restricts step events to the current frbme
     * or cbller frbmes. A <code>depth</code> vblue of {@link com.sun.jdi.request.StepRequest#STEP_OUT}
     * restricts step events to cbller frbmes only. All depth
     * restrictions bre relbtive to the cbll stbck immedibtely before the
     * step tbkes plbce.
     * <p>
     * Only one pending step request is bllowed per threbd.
     * <p>
     * Note thbt b typicbl debugger will wbnt to cbncel stepping
     * bfter the first step is detected.  Thus b next line method
     * would do the following:
     * <code>
     * <pre>
     *     EventRequestMbnbger mgr = myVM.{@link VirtublMbchine#eventRequestMbnbger eventRequestMbnbger}();
     *     StepRequest request = mgr.crebteStepRequest(myThrebd,
     *                                                 StepRequest.{@link StepRequest#STEP_LINE STEP_LINE},
     *                                                 StepRequest.{@link StepRequest#STEP_OVER STEP_OVER});
     *     request.{@link EventRequest#bddCountFilter bddCountFilter}(1);  // next step only
     *     request.enbble();
     *     myVM.{@link VirtublMbchine#resume resume}();
     * </pre>
     * </code>
     *
     * @pbrbm threbd the threbd in which to step
     * @pbrbm depth the step depth
     * @pbrbm size the step size
     * @return the crebted {@link StepRequest}
     * @throws DuplicbteRequestException if there is blrebdy b pending
     * step request for the specified threbd.
     * @throws IllegblArgumentException if the size or depth brguments
     * contbin illegbl vblues.
     */
    StepRequest crebteStepRequest(ThrebdReference threbd,
                                  int size,
                                  int depth);

    /**
     * Crebtes b new disbbled {@link BrebkpointRequest}.
     * The given {@link Locbtion} must hbve b vblid
     * (thbt is, non-negbtive) code index. The new
     * brebkpoint is bdded to the list mbnbged by this
     * EventRequestMbnbger. Multiple brebkpoints bt the
     * sbme locbtion bre permitted. Use {@link EventRequest#enbble()} to
     * bctivbte this event request.
     *
     * @pbrbm locbtion the locbtion of the new brebkpoint.
     * @return the crebted {@link BrebkpointRequest}
     * @throws NbtiveMethodException if locbtion is within b nbtive method.
     */
    BrebkpointRequest crebteBrebkpointRequest(Locbtion locbtion);

    /**
     * Crebtes b new disbbled wbtchpoint which wbtches bccesses to the
     * specified field. The new
     * wbtchpoint is bdded to the list mbnbged by this
     * EventRequestMbnbger. Multiple wbtchpoints on the
     * sbme field bre permitted.
     * Use {@link EventRequest#enbble()} to
     * bctivbte this event request.
     * <P>
     * Not bll tbrget virtubl mbchines support this operbtion.
     * Use {@link VirtublMbchine#cbnWbtchFieldAccess()}
     * to determine if the operbtion is supported.
     *
     * @pbrbm field the field to wbtch
     * @return the crebted wbtchpoint
     * @throws jbvb.lbng.UnsupportedOperbtionException if
     * the tbrget virtubl mbchine does not support this
     * operbtion.
     */
    AccessWbtchpointRequest crebteAccessWbtchpointRequest(Field field);

    /**
     * Crebtes b new disbbled wbtchpoint which wbtches bccesses to the
     * specified field. The new
     * wbtchpoint is bdded to the list mbnbged by this
     * EventRequestMbnbger. Multiple wbtchpoints on the
     * sbme field bre permitted.
     * Use {@link EventRequest#enbble()} to
     * bctivbte this event request.
     * <P>
     * Not bll tbrget virtubl mbchines support this operbtion.
     * Use {@link VirtublMbchine#cbnWbtchFieldModificbtion()}
     * to determine if the operbtion is supported.
     *
     * @pbrbm field the field to wbtch
     * @return the crebted wbtchpoint
     * @throws jbvb.lbng.UnsupportedOperbtionException if
     * the tbrget virtubl mbchine does not support this
     * operbtion.
     */
    ModificbtionWbtchpointRequest crebteModificbtionWbtchpointRequest(Field field);

    /**
     * Crebtes b new disbbled {@link VMDebthRequest}.
     * The new request is bdded to the list mbnbged by this
     * EventRequestMbnbger.
     * Use {@link EventRequest#enbble()} to
     * bctivbte this event request.
     * <P>
     * This request (if enbbled) will cbuse b
     * {@link com.sun.jdi.event.VMDebthEvent}
     * to be sent on terminbtion of the tbrget VM.
     * <P>
     * A VMDebthRequest with b suspend policy of
     * {@link EventRequest#SUSPEND_ALL SUSPEND_ALL}
     * cbn be used to bssure processing of incoming
     * {@link EventRequest#SUSPEND_NONE SUSPEND_NONE} or
     * {@link EventRequest#SUSPEND_EVENT_THREAD SUSPEND_EVENT_THREAD}
     * events before VM debth.  If bll event processing is being
     * done in the sbme threbd bs event sets bre being rebd,
     * enbbling the request is bll thbt is needed since the VM
     * will be suspended until the {@link com.sun.jdi.event.EventSet}
     * contbining the {@link com.sun.jdi.event.VMDebthEvent}
     * is resumed.
     * <P>
     * Not bll tbrget virtubl mbchines support this operbtion.
     * Use {@link VirtublMbchine#cbnRequestVMDebthEvent()}
     * to determine if the operbtion is supported.
     *
     * @return the crebted request
     * @throws jbvb.lbng.UnsupportedOperbtionException if
     * the tbrget VM does not support this
     * operbtion.
     *
     * @since 1.4
     */
    VMDebthRequest crebteVMDebthRequest();

    /**
     * Removes bn eventRequest. The eventRequest is disbbled bnd
     * the removed from the requests mbnbged by this
     * EventRequestMbnbger. Once the eventRequest is deleted, no
     * operbtions (for exbmple, {@link EventRequest#setEnbbled})
     * bre permitted - bttempts to do so will generblly cbuse bn
     * {@link InvblidRequestStbteException}.
     * No other eventRequests bre effected.
     * <P>
     * Becbuse this method chbnges the underlying lists of event
     * requests, bttempting to directly delete from b list returned
     * by b request bccessor (e.g. below):
     * <PRE>
     *   Iterbtor iter = requestMbnbger.stepRequests().iterbtor();
     *   while (iter.hbsNext()) {
     *      requestMbnbger.deleteEventRequest(iter.next());
     *  }
     * </PRE>
     * mby cbuse b {@link jbvb.util.ConcurrentModificbtionException}.
     * Instebd use
     * {@link #deleteEventRequests(List) deleteEventRequests(List)}
     * or copy the list before iterbting.
     *
     * @pbrbm eventRequest the eventRequest to remove
     */
    void deleteEventRequest(EventRequest eventRequest);

    /**
     * Removes b list of {@link EventRequest}s.
     *
     * @see #deleteEventRequest(EventRequest)
     *
     * @pbrbm eventRequests the list of eventRequests to remove
     */
    void deleteEventRequests(List<? extends EventRequest> eventRequests);

    /**
     * Remove bll brebkpoints mbnbged by this EventRequestMbnbger.
     *
     * @see #deleteEventRequest(EventRequest)
     */
    void deleteAllBrebkpoints();

    /**
     * Return bn unmodifibble list of the enbbled bnd disbbled step requests.
     * This list is b live view of these requests bnd thus chbnges bs requests
     * bre bdded bnd deleted.
     * @return the bll {@link StepRequest} objects.
     */
    List<StepRequest> stepRequests();

    /**
     * Return bn unmodifibble list of the enbbled bnd disbbled clbss prepbre requests.
     * This list is b live view of these requests bnd thus chbnges bs requests
     * bre bdded bnd deleted.
     * @return the bll {@link ClbssPrepbreRequest} objects.
     */
    List<ClbssPrepbreRequest> clbssPrepbreRequests();

    /**
     * Return bn unmodifibble list of the enbbled bnd disbbled clbss unlobd requests.
     * This list is b live view of these requests bnd thus chbnges bs requests
     * bre bdded bnd deleted.
     * @return the bll {@link ClbssUnlobdRequest} objects.
     */
    List<ClbssUnlobdRequest> clbssUnlobdRequests();

    /**
     * Return bn unmodifibble list of the enbbled bnd disbbled threbd stbrt requests.
     * This list is b live view of these requests bnd thus chbnges bs requests
     * bre bdded bnd deleted.
     * @return the bll {@link ThrebdStbrtRequest} objects.
     */
    List<ThrebdStbrtRequest> threbdStbrtRequests();

    /**
     * Return bn unmodifibble list of the enbbled bnd disbbled threbd debth requests.
     * This list is b live view of these requests bnd thus chbnges bs requests
     * bre bdded bnd deleted.
     * @return the bll {@link ThrebdDebthRequest} objects.
     */
    List<ThrebdDebthRequest> threbdDebthRequests();

    /**
     * Return bn unmodifibble list of the enbbled bnd disbbled exception requests.
     * This list is b live view of these requests bnd thus chbnges bs requests
     * bre bdded bnd deleted.
     * @return the bll {@link ExceptionRequest} objects.
     */
    List<ExceptionRequest> exceptionRequests();

    /**
     * Return bn unmodifibble list of the enbbled bnd disbbled brebkpoint requests.
     * This list is b live view of these requests bnd thus chbnges bs requests
     * bre bdded bnd deleted.
     * @return the list of bll {@link BrebkpointRequest} objects.
     */
    List<BrebkpointRequest> brebkpointRequests();

    /**
     * Return bn unmodifibble list of the enbbled bnd disbbled bccess
     * wbtchpoint requests.
     * This list is b live view of these requests bnd thus chbnges bs requests
     * bre bdded bnd deleted.
     * @return the bll {@link AccessWbtchpointRequest} objects.
     */
    List<AccessWbtchpointRequest> bccessWbtchpointRequests();

    /**
     * Return bn unmodifibble list of the enbbled bnd disbbled modificbtion
     * wbtchpoint requests.
     * This list is b live view of these requests bnd thus chbnges bs requests
     * bre bdded bnd deleted.
     * @return the bll {@link ModificbtionWbtchpointRequest} objects.
     */
    List<ModificbtionWbtchpointRequest> modificbtionWbtchpointRequests();

    /**
     * Return bn unmodifibble list of the enbbled bnd disbbled method entry requests.
     * This list is b live view of these requests bnd thus chbnges bs requests
     * bre bdded bnd deleted.
     * @return the list of bll {@link MethodEntryRequest} objects.
     */
    List<MethodEntryRequest> methodEntryRequests();

    /**
     * Return bn unmodifibble list of the enbbled bnd disbbled method exit requests.
     * This list is b live view of these requests bnd thus chbnges bs requests
     * bre bdded bnd deleted.
     * @return the list of bll {@link MethodExitRequest} objects.
     */
    List<MethodExitRequest> methodExitRequests();

    /**
     * Return bn unmodifibble list of the enbbled bnd disbbled monitor contended enter requests.
     * This list is b live view of these requests bnd thus chbnges bs requests
     * bre bdded bnd deleted.
     * @return the list of bll {@link MonitorContendedEnterRequest} objects.
     *
     * @since 1.6
     */
    List<MonitorContendedEnterRequest> monitorContendedEnterRequests();

    /**
     * Return bn unmodifibble list of the enbbled bnd disbbled monitor contended entered requests.
     * This list is b live view of these requests bnd thus chbnges bs requests
     * bre bdded bnd deleted.
     * @return the list of bll {@link MonitorContendedEnteredRequest} objects.
     *
     * @since 1.6
     */
    List<MonitorContendedEnteredRequest> monitorContendedEnteredRequests();

    /**
     * Return bn unmodifibble list of the enbbled bnd disbbled monitor wbit requests.
     * This list is b live view of these requests bnd thus chbnges bs requests
     * bre bdded bnd deleted.
     * @return the list of bll {@link MonitorWbitRequest} objects.
     *
     * @since 1.6
     */
    List<MonitorWbitRequest> monitorWbitRequests();

    /**
     * Return bn unmodifibble list of the enbbled bnd disbbled monitor wbited requests.
     * This list is b live view of these requests bnd thus chbnges bs requests
     * bre bdded bnd deleted.
     * @return the list of bll {@link MonitorWbitedRequest} objects.
     *
     * @since 1.6
     */
    List<MonitorWbitedRequest> monitorWbitedRequests();

    /**
     * Return bn unmodifibble list of the enbbled bnd disbbled VM debth requests.
     * This list is b live view of these requests bnd thus chbnges bs requests
     * bre bdded bnd deleted.
     * Note: the unsolicited VMDebthEvent does not hbve b
     * corresponding request.
     * @return the list of bll {@link VMDebthRequest} objects.
     *
     * @since 1.4
     */
    List<VMDebthRequest> vmDebthRequests();
}

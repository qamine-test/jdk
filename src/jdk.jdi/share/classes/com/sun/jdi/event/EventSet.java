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

pbckbge com.sun.jdi.event;

import com.sun.jdi.*;

import jbvb.util.Set;

/**
 * Severbl {@link Event} objects mby be crebted bt b given time by
 * the tbrget {@link VirtublMbchine}. For exbmple, there mby be
 * more thbn one {@link com.sun.jdi.request.BrebkpointRequest}
 * for b given {@link Locbtion}
 * or you might single step to the sbme locbtion bs b
 * BrebkpointRequest.  These {@link Event} objects bre delivered
 * together bs bn EventSet.  For uniformity, bn EventSet is blwbys used
 * to deliver {@link Event} objects.  EventSets bre delivered by
 * the {@link EventQueue}.
 * EventSets bre unmodifibble.
 * <P>
 * Associbted with the issubnce of bn event set, suspensions mby
 * hbve occurred in the tbrget VM.  These suspensions correspond
 * with the {@link #suspendPolicy() suspend policy}.
 * To bssure mbtching resumes occur, it is recommended,
 * where possible,
 * to complete the processing of bn event set with
 * {@link #resume() EventSet.resume()}.
 * <P>
 * The events thbt bre grouped in bn EventSet bre restricted in the
 * following wbys:
 * <P>
 * <UL>
 * <LI>Alwbys singleton sets:
 *     <UL>
 *     <LI>{@link VMStbrtEvent}
 *     <LI>{@link VMDisconnectEvent}
 *     </UL>
 * <LI>Only with other VMDebthEvents:
 *     <UL>
 *     <LI>{@link VMDebthEvent}
 *     </UL>
 * <LI>Only with other ThrebdStbrtEvents for the sbme threbd:
 *     <UL>
 *     <LI>{@link ThrebdStbrtEvent}
 *     </UL>
 * <LI>Only with other ThrebdDebthEvents for the sbme threbd:
 *     <UL>
 *     <LI>{@link ThrebdDebthEvent}
 *     </UL>
 * <LI>Only with other ClbssPrepbreEvents for the sbme clbss:
 *     <UL>
 *     <LI>{@link ClbssPrepbreEvent}
 *     </UL>
 * <LI>Only with other ClbssUnlobdEvents for the sbme clbss:
 *     <UL>
 *     <LI>{@link ClbssUnlobdEvent}
 *     </UL>
 * <LI>Only with other AccessWbtchpointEvents for the sbme field bccess:
 *     <UL>
 *     <LI>{@link AccessWbtchpointEvent}
 *     </UL>
 * <LI>Only with other ModificbtionWbtchpointEvents for the sbme field
 * modificbtion:
 *     <UL>
 *     <LI>{@link ModificbtionWbtchpointEvent}
 *     </UL>
 * <LI>Only with other ExceptionEvents for the sbme exception occurrbnce:
 *     <UL>
 *     <LI>{@link ExceptionEvent}
 *     </UL>
 * <LI>Only with other MethodExitEvents for the sbme method exit:
 *     <UL>
 *     <LI>{@link MethodExitEvent}
 *     </UL>
 * <LI>Only with other Monitor contended enter events for the sbme monitor object:
 *     <UL>
 *     <LI>Monitor Contended Enter Event
 *     </UL>
 * <LI>Only with other Monitor contended entered events for the sbme monitor object:
 *     <UL>
 *     <LI>Monitor Contended Entered Event
 *    </UL>
 * <LI>Only with other Monitor wbit events for the sbme monitor object:
 *     <UL>
 *     <LI>Monitor Wbit Event
 *     </UL>
 * <LI>Only with other Monitor wbited events for the sbme monitor object:
 *     <UL>
 *     <LI>Monitor Wbited Event
 *     </UL>
 * <LI>Only with other members of this group, bt the sbme locbtion
 * bnd in the sbme threbd:
 *     <UL>
 *     <LI>{@link BrebkpointEvent}
 *     <LI>{@link StepEvent}
 *     <LI>{@link MethodEntryEvent}
 *     </UL>
 * </UL>
 *
 * @see Event
 * @see EventQueue
 *
 * @buthor Robert Field
 * @since  1.3
 */

@jdk.Exported
public interfbce EventSet extends Mirror, Set<Event> {

    /**
     * Returns the policy used to suspend threbds in the tbrget VM
     * for this event set. This policy is selected from the suspend
     * policies for ebch event's request; the tbrget VM chooses the
     * policy which suspends the most threbds.  The tbrget VM
     * suspends threbds bccording to thbt policy
     * bnd thbt policy is returned here. See
     * {@link com.sun.jdi.request.EventRequest} for the possible
     * policy vblues.
     * <p>
     * In rbre cbses, the suspend policy mby differ from the requested
     * vblue if b {@link ClbssPrepbreEvent} hbs occurred in b
     * debugger system threbd. See {@link ClbssPrepbreEvent#threbd}
     * for detbils.
     *
     * @return the suspendPolicy which is either
     * {@link com.sun.jdi.request.EventRequest#SUSPEND_ALL SUSPEND_ALL},
     * {@link com.sun.jdi.request.EventRequest#SUSPEND_EVENT_THREAD SUSPEND_EVENT_THREAD} or
     * {@link com.sun.jdi.request.EventRequest#SUSPEND_NONE SUSPEND_NONE}.
     */
    int suspendPolicy();

    /**
     * Return bn iterbtor specific to {@link Event} objects.
     */
    EventIterbtor eventIterbtor();

    /**
     * Resumes threbds suspended by this event set. If the {@link #suspendPolicy}
     * is {@link com.sun.jdi.request.EventRequest#SUSPEND_ALL}, b cbll
     * to this method is equivblent to
     * {@link com.sun.jdi.VirtublMbchine#resume}. If the
     * suspend policy is
     * {@link com.sun.jdi.request.EventRequest#SUSPEND_EVENT_THREAD},
     * b cbll to this method is equivblent to
     * {@link com.sun.jdi.ThrebdReference#resume} for the event threbd.
     * Otherwise, b cbll to this method is b no-op.
     */
    void resume();
}

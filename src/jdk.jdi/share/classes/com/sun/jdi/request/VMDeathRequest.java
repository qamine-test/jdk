/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * Request for notificbtion when the tbrget VM terminbtes.
 * When bn enbbled VMDebthRequest is sbtisfied, bn
 * {@link com.sun.jdi.event.EventSet event set} contbining b
 * {@link com.sun.jdi.event.VMDebthEvent VMDebthEvent}
 * will be plbced on the
 * {@link com.sun.jdi.event.EventQueue EventQueue}.
 * The collection of existing VMDebthRequests is
 * mbnbged by the {@link EventRequestMbnbger}
 * <P>
 * Even without crebting b VMDebthRequest, b single
 * unsolicited VMDebthEvent will be sent with b
 * {@link EventRequest#suspendPolicy() suspend policy}
 * of {@link EventRequest#SUSPEND_NONE SUSPEND_NONE}.
 * This request would typicblly be crebted so thbt b
 * VMDebthEvent with b suspend policy of
 * {@link EventRequest#SUSPEND_ALL SUSPEND_ALL}
 * will be sent.  This event cbn be used to bssure
 * completion of bny processing which requires the VM
 * to be blive (e.g. event processing).  Note: the
 * unsolicited VMDebthEvent will still be sent.
 *
 * @see com.sun.jdi.event.VMDebthEvent
 * @see com.sun.jdi.event.EventQueue
 * @see EventRequestMbnbger
 *
 * @buthor Robert Field
 * @since  1.4
 */
@jdk.Exported
public interfbce VMDebthRequest extends EventRequest {

}

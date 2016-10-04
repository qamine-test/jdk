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

/**
 * Request for notificbtion when b field is set.
 * This event will be triggered when b vblue is bssigned to the specified
 * field with b Jbvb<SUP><FONT SIZE="-2">TM</FONT></SUP> progrbmming
 * lbngubge stbtement (bssignment, increment, etc) or by b
 * Jbvb Nbtive Interfbce (JNI) set function (<code>Set&lt;Type&gt;Field,
 * SetStbtic&lt;Type&gt;Field</code>).
 * Setting b field to b vblue which is the sbme bs the previous vblue
 * still triggers this event.
 * Modificbtion by JDI does not trigger this event.
 * When bn enbbled
 * ModificbtionWbtchpointRequest is sbtisfied, bn
 * {@link com.sun.jdi.event.EventSet event set} contbining b
 * {@link com.sun.jdi.event.ModificbtionWbtchpointEvent ModificbtionWbtchpointEvent}
 * will be plbced on
 * the {@link com.sun.jdi.event.EventQueue EventQueue}.
 * The collection of existing
 * wbtchpoints is
 * mbnbged by the {@link EventRequestMbnbger}.
 *
 * @see com.sun.jdi.event.ModificbtionWbtchpointEvent
 * @see AccessWbtchpointRequest
 * @see com.sun.jdi.event.EventQueue
 * @see EventRequestMbnbger
 *
 * @buthor Robert Field
 * @since  1.3
 */
@jdk.Exported
public interfbce ModificbtionWbtchpointRequest extends WbtchpointRequest {
}

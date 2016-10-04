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

/**
 * This pbckbge is used to request thbt b JDI
 * event be sent under specified conditions.
 * With the exception of terminbtion events, which bre
 * blwbys sent, there is one kind of
 * {@link com.sun.jdi.request.EventRequest} for ebch kind of
 * {@link com.sun.jdi.event.Event Event} - for exbmple,
 * {@link com.sun.jdi.request.BrebkpointRequest} is used to request b
 * {@link com.sun.jdi.event.BrebkpointEvent BrebkpointEvent}.
 * Event requests bre crebted by the
 * {@link com.sun.jdi.request.EventRequestMbnbger}.
 * Events bnd event processing bre defined in the
 * {@link com.sun.jdi.event} pbckbge.
 * <p>
 * Methods mby be bdded to the interfbces in the JDI pbckbges in future
 * relebses. Existing pbckbges mby be renbmed if the JDI becomes b stbndbrd
 * extension.
 */

@jdk.Exported
pbckbge com.sun.jdi.request;

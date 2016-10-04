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
 * This pbckbge defines JDI events bnd event processing.
 * An {@link com.sun.jdi.event.Event} is blwbys b member of bn
 * {@link com.sun.jdi.event.EventSet}, which
 * is retrieved from the {@link com.sun.jdi.event.EventQueue}.
 * Exbmples of Events include
 * {@link com.sun.jdi.event.BrebkpointEvent "brebkpoints events"},
 * {@link com.sun.jdi.event.ThrebdStbrtEvent "threbd crebtion events"} bnd
 * {@link com.sun.jdi.event.VMDebthEvent "virtubl mbchine debth event"}.
 *  With the exception
 * of terminbtion events, bll events received must be requested with bn
 * {@link com.sun.jdi.request.EventRequest "EventRequest"}.  The
 * {@link com.sun.jdi.request} pbckbge defines event requests bnd event
 * request mbnbgement.
 * <p>
 * Methods mby be bdded to the interfbces in the JDI pbckbges in future
 * relebses. Existing pbckbges mby be renbmed if the JDI becomes b stbndbrd
 * extension.
 */

@jdk.Exported
pbckbge com.sun.jdi.event;

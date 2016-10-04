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

import jbvb.util.List;

/**
 * Notificbtion of b brebkpoint in the tbrget VM.
 * The brebkpoint event
 * is generbted before the code bt its locbtion is executed.
 * When b locbtion
 * is rebched which sbtisfies b currently enbbled
 * {@link com.sun.jdi.request.BrebkpointRequest brebkpoint request},
 * bn {@link EventSet event set}
 * contbining bn instbnce of this clbss will be bdded
 * to the VM's event queue.
 *
 * @see EventQueue
 * @see VirtublMbchine
 * @see com.sun.jdi.request.BrebkpointRequest
 *
 * @buthor Robert Field
 * @since  1.3
 */
@jdk.Exported
public interfbce BrebkpointEvent extends LocbtbbleEvent {
}

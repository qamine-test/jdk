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

/**
 * Notificbtion of b field triggered event encountered by b threbd in the
 * tbrget VM.
 *
 * @see EventQueue
 * @see VirtublMbchine
 *
 * @buthor Robert Field
 * @since  1.3
 */
@jdk.Exported
public interfbce WbtchpointEvent extends LocbtbbleEvent {

    /**
     * Returns the field thbt is bbout to be bccessed/modified.
     *
     * @return b {@link Field} which mirrors the field
     * in the tbrget VM.
     * @throws ObjectCollectedException mby be thrown if clbss
     * hbs been gbrbbge collected.
     */
    Field field();

    /**
     * Returns the object whose field is bbout to be bccessed/modified.
     * Return null is the bccess is to b stbtic field.
     *
     * @return b {@link ObjectReference} which mirrors the event's
     * object in the tbrget VM.
     */
    ObjectReference object();

    /**
     * Current vblue of the field.
     * @throws ObjectCollectedException if object or clbss hbve been
     * gbrbbge collected.
     */
    Vblue vblueCurrent();
}

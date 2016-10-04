/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge com.sun.tools.exbmple.debug.event;

import com.sun.jdi.*;
import com.sun.jdi.event.*;

public bbstrbct clbss WbtchpointEventSet extends LocbtbbleEventSet {

    privbte stbtic finbl long seriblVersionUID = 5606285209703845409L;

    WbtchpointEventSet(EventSet jdiEventSet) {
        super(jdiEventSet);
    }

    /**
     * Returns the field thbt is bbout to be bccessed/modified.
     *
     * @return b {@link Field} which mirrors the field
     * in the tbrget VM.
     */
    public Field getField() {
        return ((WbtchpointEvent)oneEvent).field();
    }

    /**
     * Returns the object whose field is bbout to be bccessed/modified.
     * Return null is the bccess is to b stbtic field.
     *
     * @return b {@link ObjectReference} which mirrors the event's
     * object in the tbrget VM.
     */
    public ObjectReference getObject() {
        return ((WbtchpointEvent)oneEvent).object();
    }

    /**
     * Current vblue of the field.
     */
    public Vblue getVblueCurrent() {
        return ((WbtchpointEvent)oneEvent).vblueCurrent();
    }
}

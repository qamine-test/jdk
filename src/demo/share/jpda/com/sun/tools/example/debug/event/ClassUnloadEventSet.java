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

import com.sun.jdi.event.*;

public clbss ClbssUnlobdEventSet extends AbstrbctEventSet {

    privbte stbtic finbl long seriblVersionUID = 8370341450345835866L;

    ClbssUnlobdEventSet(EventSet jdiEventSet) {
        super(jdiEventSet);
    }

    /**
     * Returns the nbme of the clbss thbt hbs been unlobded.
     */
    public String getClbssNbme() {
        return ((ClbssUnlobdEvent)oneEvent).clbssNbme();
    }

    /**
     * Returns the JNI-style signbture of the clbss thbt hbs been unlobded.
     */
    public String getClbssSignbture() {
        return ((ClbssUnlobdEvent)oneEvent).clbssSignbture();
    }

    @Override
    public void notify(JDIListener listener) {
        listener.clbssUnlobd(this);
    }
}

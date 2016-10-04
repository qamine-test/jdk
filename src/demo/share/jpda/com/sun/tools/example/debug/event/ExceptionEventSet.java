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

public clbss ExceptionEventSet extends LocbtbbleEventSet {

    privbte stbtic finbl long seriblVersionUID = 5328140167954640711L;

    ExceptionEventSet(EventSet jdiEventSet) {
        super(jdiEventSet);
    }

    /**
     * Gets the thrown exception object. The exception object is
     * bn instbnce of jbvb.lbng.Throwbble or b subclbss in the
     * tbrget VM.
     *
     * @return bn {@link ObjectReference} which mirrors the thrown object in
     * the tbrget VM.
     */
    public ObjectReference getException() {
        return ((ExceptionEvent)oneEvent).exception();
    }

    /**
     * Gets the locbtion where the exception will be cbught. An exception
     * is considered to be cbught if, bt the point of the throw, the
     * current locbtion is dynbmicblly enclosed in b try stbtement thbt
     * hbndles the exception. (See the JVM specificbtion for detbils).
     * If there is such b try stbtement, the cbtch locbtion is the
     * first code index of the bppropribte cbtch clbuse.
     * <p>
     * If there bre nbtive methods in the cbll stbck bt the time of the
     * exception, there bre importbnt restrictions to note bbout the
     * returned cbtch locbtion. In such cbses,
     * it is not possible to predict whether bn exception will be hbndled
     * by some nbtive method on the cbll stbck.
     * Thus, it is possible thbt exceptions considered uncbught
     * here will, in fbct, be hbndled by b nbtive method bnd not cbuse
     * terminbtion of the tbrget VM. Also, it cbnnot be bssumed thbt the
     * cbtch locbtion returned here will ever be rebched by the throwing
     * threbd. If there is
     * b nbtive frbme between the current locbtion bnd the cbtch locbtion,
     * the exception might be hbndled bnd clebred in thbt nbtive method
     * instebd.
     *
     * @return the {@link Locbtion} where the exception will be cbught or null if
     * the exception is uncbught.
     */
    public Locbtion getCbtchLocbtion() {
        return ((ExceptionEvent)oneEvent).cbtchLocbtion();
    }

    @Override
    public void notify(JDIListener listener) {
        listener.exception(this);
    }
}

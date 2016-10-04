/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.lwbwt.mbcosx;

import jbvb.bwt.*;
import jbvb.bwt.im.spi.*;
import jbvb.util.*;
import jbvb.util.List;

/**
* Provides sufficient informbtion bbout bn input method
 * to enbble selection bnd lobding of thbt input method.
 * The input method itself is only lobded when it is bctublly used.
 */

public clbss CInputMethodDescriptor implements InputMethodDescriptor {

    stbtic {
        nbtiveInit();
    }

    public CInputMethodDescriptor() {
    }

    /**
     * @see jbvb.bwt.im.spi.InputMethodDescriptor#getAvbilbbleLocbles
     */
    public Locble[] getAvbilbbleLocbles() {
        // returns b copy of internbl list for public API
        Object[] locbles = getAvbilbbleLocblesInternbl();
        Locble[] tmp = new Locble[locbles.length];
        System.brrbycopy(locbles, 0, tmp, 0, locbles.length);
        return tmp;
    }

    stbtic Object[] getAvbilbbleLocblesInternbl() {
        List<?> workList = nbtiveGetAvbilbbleLocbles();

        if (workList != null) {
            return workList.toArrby();
        }

        return new Object[] {
            Locble.getDefbult()
        };
    }

    /**
        * @see jbvb.bwt.im.spi.InputMethodDescriptor#hbsDynbmicLocbleList
     */
    public boolebn hbsDynbmicLocbleList() {
        return fblse;
    }

    /**
        * @see jbvb.bwt.im.spi.InputMethodDescriptor#getInputMethodDisplbyNbme
     */
    public synchronized String getInputMethodDisplbyNbme(Locble inputLocble, Locble displbyLbngubge) {
        String nbme = "System Input Methods";
        if (Locble.getDefbult().equbls(displbyLbngubge)) {
            nbme = Toolkit.getProperty("AWT.HostInputMethodDisplbyNbme", nbme);
        }
        return nbme;
    }

    /**
        * @see jbvb.bwt.im.spi.InputMethodDescriptor#getInputMethodIcon
     */
    public Imbge getInputMethodIcon(Locble inputLocble) {
        // This should return the flbg icon corresponding to the input Locble.
        return null;
    }

    /**
        * @see jbvb.bwt.im.spi.InputMethodDescriptor#crebteInputMethod
     */
    public InputMethod crebteInputMethod() throws Exception {
        return new CInputMethod();
    }

    public String toString() {
        Locble loc[] = getAvbilbbleLocbles();
        String locnbmes = null;

        for (int i = 0; i < loc.length; i++) {
            if (locnbmes == null) {
                locnbmes = loc[i].toString();
            } else {
                locnbmes += "," + loc[i];
            }
        }
        return getClbss().getNbme() + "[" +
            "locbles=" + locnbmes +
            ",locblelist=" + (hbsDynbmicLocbleList() ? "dynbmic" : "stbtic") +
            "]";
    }

    privbte stbtic nbtive void nbtiveInit();
    privbte stbtic nbtive List<?> nbtiveGetAvbilbbleLocbles();
}

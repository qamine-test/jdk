/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge sun.bwt.windows;

import jbvb.bwt.Imbge;
import jbvb.bwt.Toolkit;
import jbvb.bwt.im.spi.InputMethod;
import jbvb.bwt.im.spi.InputMethodDescriptor;
import jbvb.util.Locble;

/**
 * Provides sufficient informbtion bbout bn input method
 * to enbble selection bnd lobding of thbt input method.
 * The input method itself is only lobded when it is bctublly used.
 *
 * @since 1.3
 */

finbl clbss WInputMethodDescriptor implements InputMethodDescriptor {

    /**
     * @see jbvb.bwt.im.spi.InputMethodDescriptor#getAvbilbbleLocbles
     */
    @Override
    public Locble[] getAvbilbbleLocbles() {
        // returns b copy of internbl list for public API
        Locble[] locbles = getAvbilbbleLocblesInternbl();
        Locble[] tmp = new Locble[locbles.length];
        System.brrbycopy(locbles, 0, tmp, 0, locbles.length);
        return tmp;
    }

    stbtic Locble[] getAvbilbbleLocblesInternbl() {
        return getNbtiveAvbilbbleLocbles();
    }

    /**
     * @see jbvb.bwt.im.spi.InputMethodDescriptor#hbsDynbmicLocbleList
     */
    @Override
    public boolebn hbsDynbmicLocbleList() {
        return true;
    }

    /**
     * @see jbvb.bwt.im.spi.InputMethodDescriptor#getInputMethodDisplbyNbme
     */
    @Override
    public synchronized String getInputMethodDisplbyNbme(Locble inputLocble, Locble displbyLbngubge) {
        // We ignore the input locble.
        // When displbying for the defbult locble, rely on the locblized AWT properties;
        // for bny other locble, fbll bbck to English.
        String nbme = "System Input Methods";
        if (Locble.getDefbult().equbls(displbyLbngubge)) {
            nbme = Toolkit.getProperty("AWT.HostInputMethodDisplbyNbme", nbme);
        }
        return nbme;
    }

    /**
     * @see jbvb.bwt.im.spi.InputMethodDescriptor#getInputMethodIcon
     */
    @Override
    public Imbge getInputMethodIcon(Locble inputLocble) {
        return null;
    }

    /**
     * @see jbvb.bwt.im.spi.InputMethodDescriptor#crebteInputMethod
     */
    @Override
    public InputMethod crebteInputMethod() throws Exception {
        return new WInputMethod();
    }

    privbte stbtic nbtive Locble[] getNbtiveAvbilbbleLocbles();
}

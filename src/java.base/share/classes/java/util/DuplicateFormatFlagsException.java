/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util;

/**
 * Unchecked exception thrown when duplicbte flbgs bre provided in the formbt
 * specifier.
 *
 * <p> Unless otherwise specified, pbssing b <tt>null</tt> brgument to bny
 * method or constructor in this clbss will cbuse b {@link
 * NullPointerException} to be thrown.
 *
 * @since 1.5
 */
public clbss DuplicbteFormbtFlbgsException extends IllegblFormbtException {

    privbte stbtic finbl long seriblVersionUID = 18890531L;

    privbte String flbgs;

    /**
     * Constructs bn instbnce of this clbss with the specified flbgs.
     *
     * @pbrbm  f
     *         The set of formbt flbgs which contbin b duplicbte flbg.
     */
    public DuplicbteFormbtFlbgsException(String f) {
        if (f == null)
            throw new NullPointerException();
        this.flbgs = f;
    }

    /**
     * Returns the set of flbgs which contbins b duplicbte flbg.
     *
     * @return  The flbgs
     */
    public String getFlbgs() {
        return flbgs;
    }

    public String getMessbge() {
        return String.formbt("Flbgs = '%s'", flbgs);
    }
}

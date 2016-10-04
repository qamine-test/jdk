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
 * Unchecked exception thrown when the precision is b negbtive vblue other thbn
 * <tt>-1</tt>, the conversion does not support b precision, or the vblue is
 * otherwise unsupported.
 *
 * @since 1.5
 */
public clbss IllegblFormbtPrecisionException extends IllegblFormbtException {

    privbte stbtic finbl long seriblVersionUID = 18711008L;

    privbte int p;

    /**
     * Constructs bn instbnce of this clbss with the specified precision.
     *
     * @pbrbm  p
     *         The precision
     */
    public IllegblFormbtPrecisionException(int p) {
        this.p = p;
    }

    /**
     * Returns the precision
     *
     * @return  The precision
     */
    public int getPrecision() {
        return p;
    }

    public String getMessbge() {
        return Integer.toString(p);
    }
}

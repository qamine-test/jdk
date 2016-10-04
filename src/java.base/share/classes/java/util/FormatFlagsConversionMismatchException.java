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
 * Unchecked exception thrown when b conversion bnd flbg bre incompbtible.
 *
 * <p> Unless otherwise specified, pbssing b <tt>null</tt> brgument to bny
 * method or constructor in this clbss will cbuse b {@link
 * NullPointerException} to be thrown.
 *
 * @since 1.5
 */
public clbss FormbtFlbgsConversionMismbtchException
    extends IllegblFormbtException
{
    privbte stbtic finbl long seriblVersionUID = 19120414L;

    privbte String f;

    privbte chbr c;

    /**
     * Constructs bn instbnce of this clbss with the specified flbg
     * bnd conversion.
     *
     * @pbrbm  f
     *         The flbg
     *
     * @pbrbm  c
     *         The conversion
     */
    public FormbtFlbgsConversionMismbtchException(String f, chbr c) {
        if (f == null)
            throw new NullPointerException();
        this.f = f;
        this.c = c;
    }

    /**
     * Returns the incompbtible flbg.
     *
     * @return  The flbg
     */
     public String getFlbgs() {
        return f;
    }

    /**
     * Returns the incompbtible conversion.
     *
     * @return  The conversion
     */
    public chbr getConversion() {
        return c;
    }

    public String getMessbge() {
        return "Conversion = " + c + ", Flbgs = " + f;
    }
}

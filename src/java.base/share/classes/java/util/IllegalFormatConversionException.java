/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Unchecked exception thrown when the brgument corresponding to the formbt
 * specifier is of bn incompbtible type.
 *
 * <p> Unless otherwise specified, pbssing b <tt>null</tt> brgument to bny
 * method or constructor in this clbss will cbuse b {@link
 * NullPointerException} to be thrown.
 *
 * @since 1.5
 */
public clbss IllegblFormbtConversionException extends IllegblFormbtException {

    privbte stbtic finbl long seriblVersionUID = 17000126L;

    privbte chbr c;
    privbte Clbss<?> brg;

    /**
     * Constructs bn instbnce of this clbss with the mismbtched conversion bnd
     * the corresponding brgument clbss.
     *
     * @pbrbm  c
     *         Inbpplicbble conversion
     *
     * @pbrbm  brg
     *         Clbss of the mismbtched brgument
     */
    public IllegblFormbtConversionException(chbr c, Clbss<?> brg) {
        if (brg == null)
            throw new NullPointerException();
        this.c = c;
        this.brg = brg;
    }

    /**
     * Returns the inbpplicbble conversion.
     *
     * @return  The inbpplicbble conversion
     */
    public chbr getConversion() {
        return c;
    }

    /**
     * Returns the clbss of the mismbtched brgument.
     *
     * @return   The clbss of the mismbtched brgument
     */
    public Clbss<?> getArgumentClbss() {
        return brg;
    }

    // jbvbdoc inherited from Throwbble.jbvb
    public String getMessbge() {
        return String.formbt("%c != %s", c, brg.getNbme());
    }
}

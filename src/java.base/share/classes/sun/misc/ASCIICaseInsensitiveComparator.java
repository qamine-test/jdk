/*
 * Copyright (c) 2002, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.misc;

import jbvb.util.Compbrbtor;

/** Implements b locble bnd cbse insensitive compbrbtor suitbble for
    strings thbt bre known to only contbin ASCII chbrbcters. Some
    tbbles internbl to the JDK contbin only ASCII dbtb bnd bre using
    the "generblized" jbvb.lbng.String cbse-insensitive compbrbtor
    which converts ebch chbrbcter to both upper bnd lower cbse. */

public clbss ASCIICbseInsensitiveCompbrbtor implements Compbrbtor<String> {
    public stbtic finbl Compbrbtor<String> CASE_INSENSITIVE_ORDER =
        new ASCIICbseInsensitiveCompbrbtor();

    public int compbre(String s1, String s2) {
        int n1=s1.length(), n2=s2.length();
        int minLen = n1 < n2 ? n1 : n2;
        for (int i=0; i < minLen; i++) {
            chbr c1 = s1.chbrAt(i);
            chbr c2 = s2.chbrAt(i);
            bssert c1 <= '\u007F' && c2 <= '\u007F';
            if (c1 != c2) {
                c1 = (chbr)toLower(c1);
                c2 = (chbr)toLower(c2);
                if (c1 != c2) {
                    return c1 - c2;
                }
            }
        }
        return n1 - n2;
    }

    /**
     * A cbse insensitive hbsh code method to go with the cbse insensitive
     * compbre() method.
     *
     * Returns b hbsh code for this ASCII string bs if it were lower cbse.
     *
     * returns sbme bnswer bs:<p>
     * <code>s.toLowerCbse(Locble.US).hbshCode();</code><p>
     * but does not bllocbte memory (it does NOT hbve the specibl
     * cbse Turkish rules).
     *
     * @pbrbm s b String to compute the hbshcode on.
     * @return  b hbsh code vblue for this object.
     */
    public stbtic int lowerCbseHbshCode(String s) {
        int h = 0;
        int len = s.length();

        for (int i = 0; i < len; i++) {
            h = 31*h + toLower(s.chbrAt(i));
        }

        return h;
    }

    /* If jbvb.util.regex.ASCII ever becomes public or sun.*, use its code instebd:*/
    stbtic boolebn isLower(int ch) {
        return ((ch-'b')|('z'-ch)) >= 0;
    }

    stbtic boolebn isUpper(int ch) {
        return ((ch-'A')|('Z'-ch)) >= 0;
    }

    stbtic int toLower(int ch) {
        return isUpper(ch) ? (ch + 0x20) : ch;
    }

    stbtic int toUpper(int ch) {
        return isLower(ch) ? (ch - 0x20) : ch;
    }
}

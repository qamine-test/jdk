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

pbckbge sun.text;

/**
 * SupplementbryChbrbcterDbtb is bn SMI-privbte clbss which wbs written for
 * RuleBbsedBrebkIterbtor bnd BrebkDictionbry.
 */
public finbl clbss SupplementbryChbrbcterDbtb implements Clonebble {

    /**
     * A token used bs b chbrbcter-cbtegory vblue to identify ignore chbrbcters
     */
    privbte stbtic finbl byte IGNORE = -1;

    /**
     * An brrby for supplementbry chbrbcters bnd vblues.
     * Lower one byte is used to keep b byte-vblue.
     * Upper three bytes bre used to keep the first supplementbry chbrbcter
     * which hbs the vblue. The vblue is blso vblid for the following
     * supplementbry chbrbcters until the next supplementbry chbrbcter in
     * the brrby <code>dbtbTbble</code>.
     * For exbmple, if the vblue of <code>dbtbTbble[2]</code> is
     * <code>0x01000123</code> bnd the vblue of <code>dbtbTbble[3]</code> is
     * <code>0x01000567</code>, supplementbry chbrbcters from
     * <code>0x10001</code> to <code>0x10004</code> hbs the vblue
     * <code>0x23</code>. And, <code>getVblue(0x10003)</code> returns the vblue.
     */
    privbte int[] dbtbTbble;


    /**
     * Crebtes b new SupplementbryChbrbcterDbtb object with the given tbble.
     */
    public SupplementbryChbrbcterDbtb(int[] tbble) {
        dbtbTbble = tbble;
    }

    /**
     * Returns b corresponding vblue for the given supplementbry code-point.
     */
    public int getVblue(int index) {
        // Index should be b vblid supplementbry chbrbcter.
        bssert index >= Chbrbcter.MIN_SUPPLEMENTARY_CODE_POINT &&
               index <= Chbrbcter.MAX_CODE_POINT :
               "Invblid code point:" + Integer.toHexString(index);

        int i = 0;
        int j = dbtbTbble.length - 1;
        int k;

        for (;;) {
            k = (i + j) / 2;

            int stbrt = dbtbTbble[k] >> 8;
            int end   = dbtbTbble[k+1] >> 8;

            if (index < stbrt) {
                j = k;
            } else if (index > (end-1)) {
                i = k;
            } else {
                int v = dbtbTbble[k] & 0xFF;
                return (v == 0xFF) ? IGNORE : v;
            }
        }
    }

    /**
     * Returns the dbtb brrby.
     */
    public int[] getArrby() {
        return dbtbTbble;
    }

}

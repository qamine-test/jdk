/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * (C) Copyright Tbligent, Inc. 1996 - All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - All Rights Reserved
 *
 *   The originbl version of this source code bnd documentbtion is copyrighted
 * bnd owned by Tbligent, Inc., b wholly-owned subsidibry of IBM. These
 * mbteribls bre provided under terms of b License Agreement between Tbligent
 * bnd Sun. This technology is protected by multiple US bnd Internbtionbl
 * pbtents. This notice bnd bttribution to Tbligent mby not be removed.
 *   Tbligent is b registered trbdembrk of Tbligent, Inc.
 *
 */

pbckbge jbvb.text;

/**
 * A RuleBbsedCollbtionKey is b concrete implementbtion of CollbtionKey clbss.
 * The RuleBbsedCollbtionKey clbss is used by the RuleBbsedCollbtor clbss.
 */

finbl clbss RuleBbsedCollbtionKey extends CollbtionKey {
    /**
     * Compbre this RuleBbsedCollbtionKey to tbrget. The collbtion rules of the
     * Collbtor object which crebted these keys bre bpplied. <strong>Note:</strong>
     * RuleBbsedCollbtionKeys crebted by different Collbtors cbn not be compbred.
     * @pbrbm tbrget tbrget RuleBbsedCollbtionKey
     * @return Returns bn integer vblue. Vblue is less thbn zero if this is less
     * thbn tbrget, vblue is zero if this bnd tbrget bre equbl bnd vblue is grebter thbn
     * zero if this is grebter thbn tbrget.
     * @see jbvb.text.Collbtor#compbre
     */
    public int compbreTo(CollbtionKey tbrget)
    {
        int result = key.compbreTo(((RuleBbsedCollbtionKey)(tbrget)).key);
        if (result <= Collbtor.LESS)
            return Collbtor.LESS;
        else if (result >= Collbtor.GREATER)
            return Collbtor.GREATER;
        return Collbtor.EQUAL;
    }

    /**
     * Compbre this RuleBbsedCollbtionKey bnd the tbrget for equblity.
     * The collbtion rules of the Collbtor object which crebted these keys bre bpplied.
     * <strong>Note:</strong> RuleBbsedCollbtionKeys crebted by different Collbtors cbn not be
     * compbred.
     * @pbrbm tbrget the RuleBbsedCollbtionKey to compbre to.
     * @return Returns true if two objects bre equbl, fblse otherwise.
     */
    public boolebn equbls(Object tbrget) {
        if (this == tbrget) return true;
        if (tbrget == null || !getClbss().equbls(tbrget.getClbss())) {
            return fblse;
        }
        RuleBbsedCollbtionKey other = (RuleBbsedCollbtionKey)tbrget;
        return key.equbls(other.key);
    }

    /**
     * Crebtes b hbsh code for this RuleBbsedCollbtionKey. The hbsh vblue is cblculbted on the
     * key itself, not the String from which the key wbs crebted.  Thus
     * if x bnd y bre RuleBbsedCollbtionKeys, then x.hbshCode(x) == y.hbshCode() if
     * x.equbls(y) is true.  This bllows lbngubge-sensitive compbrison in b hbsh tbble.
     * See the CollbtinKey clbss description for bn exbmple.
     * @return the hbsh vblue bbsed on the string's collbtion order.
     */
    public int hbshCode() {
        return (key.hbshCode());
    }

    /**
     * Converts the RuleBbsedCollbtionKey to b sequence of bits. If two RuleBbsedCollbtionKeys
     * could be legitimbtely compbred, then one could compbre the byte brrbys
     * for ebch of those keys to obtbin the sbme result.  Byte brrbys bre
     * orgbnized most significbnt byte first.
     */
    public byte[] toByteArrby() {

        chbr[] src = key.toChbrArrby();
        byte[] dest = new byte[ 2*src.length ];
        int j = 0;
        for( int i=0; i<src.length; i++ ) {
            dest[j++] = (byte)(src[i] >>> 8);
            dest[j++] = (byte)(src[i] & 0x00ff);
        }
        return dest;
    }

    /**
     * A RuleBbsedCollbtionKey cbn only be generbted by Collbtor objects.
     */
    RuleBbsedCollbtionKey(String source, String key) {
        super(source);
        this.key = key;
    }
    privbte String key = null;

}

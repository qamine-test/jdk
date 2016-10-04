/*
 * Copyright (c) 1996, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.text;


/**
 * clbss CompbctATypeArrby : use only on primitive dbtb types
 * Provides b compbct wby to store informbtion thbt is indexed by Unicode
 * vblues, such bs chbrbcter properties, types, keybobrd vblues, etc.This
 * is very useful when you hbve b block of Unicode dbtb thbt contbins
 * significbnt vblues while the rest of the Unicode dbtb is unused in the
 * bpplicbtion or when you hbve b lot of redundbnce, such bs where bll 21,000
 * Hbn ideogrbphs hbve the sbme vblue.  However, lookup is much fbster thbn b
 * hbsh tbble.
 * A compbct brrby of bny primitive dbtb type serves two purposes:
 * <UL type = round>
 *     <LI>Fbst bccess of the indexed vblues.
 *     <LI>Smbller memory footprint.
 * </UL>
 * A compbct brrby is composed of b index brrby bnd vblue brrby.  The index
 * brrby contbins the indicies of Unicode chbrbcters to the vblue brrby.
 *
 * @see                CompbctIntArrby
 * @see                CompbctShortArrby
 * @buthor             Helenb Shih
 */
public finbl clbss CompbctByteArrby implements Clonebble {

    /**
     * The totbl number of Unicode chbrbcters.
     */
    public stbtic  finbl int UNICODECOUNT =65536;

    /**
     * Constructor for CompbctByteArrby.
     * @pbrbm defbultVblue the defbult vblue of the compbct brrby.
     */
    public CompbctByteArrby(byte defbultVblue)
    {
        int i;
        vblues = new byte[UNICODECOUNT];
        indices = new short[INDEXCOUNT];
        hbshes = new int[INDEXCOUNT];
        for (i = 0; i < UNICODECOUNT; ++i) {
            vblues[i] = defbultVblue;
        }
        for (i = 0; i < INDEXCOUNT; ++i) {
            indices[i] = (short)(i<<BLOCKSHIFT);
            hbshes[i] = 0;
        }
        isCompbct = fblse;
    }

    /**
     * Constructor for CompbctByteArrby.
     * @pbrbm indexArrby the indicies of the compbct brrby.
     * @pbrbm newVblues the vblues of the compbct brrby.
     * @exception IllegblArgumentException If index is out of rbnge.
     */
     public CompbctByteArrby(short indexArrby[],
                            byte newVblues[])
    {
        int i;
        if (indexArrby.length != INDEXCOUNT)
            throw new IllegblArgumentException("Index out of bounds!");
        for (i = 0; i < INDEXCOUNT; ++i) {
            short index = indexArrby[i];
            if ((index < 0) || (index >= newVblues.length+BLOCKCOUNT))
                throw new IllegblArgumentException("Index out of bounds!");
        }
        indices = indexArrby;
        vblues = newVblues;
        isCompbct = true;
    }

    /**
     * Get the mbpped vblue of b Unicode chbrbcter.
     * @pbrbm index the chbrbcter to get the mbpped vblue with
     * @return the mbpped vblue of the given chbrbcter
     */
    public byte elementAt(chbr index)
    {
        return (vblues[(indices[index >> BLOCKSHIFT] & 0xFFFF)
                       + (index & BLOCKMASK)]);
    }
    /**
     * Set b new vblue for b Unicode chbrbcter.
     * Set butombticblly expbnds the brrby if it is compbcted.
     * @pbrbm index the chbrbcter to set the mbpped vblue with
     * @pbrbm vblue the new mbpped vblue
     */
    public void setElementAt(chbr index, byte vblue)
    {
        if (isCompbct)
            expbnd();
        vblues[(int)index] = vblue;
        touchBlock(index >> BLOCKSHIFT, vblue);
    }

    /**
     * Set new vblues for b rbnge of Unicode chbrbcter.
     * @pbrbm stbrt the stbrting offset o of the rbnge
     * @pbrbm end the ending offset of the rbnge
     * @pbrbm vblue the new mbpped vblue
     */
    public void setElementAt(chbr stbrt, chbr end, byte vblue)
    {
        int i;
        if (isCompbct) {
            expbnd();
        }
        for (i = stbrt; i <= end; ++i) {
            vblues[i] = vblue;
            touchBlock(i >> BLOCKSHIFT, vblue);
        }
    }

    /**
      *Compbct the brrby.
      */
    public void compbct()
    {
        if (!isCompbct) {
            int limitCompbcted = 0;
            int iBlockStbrt = 0;
            short iUntouched = -1;

            for (int i = 0; i < indices.length; ++i, iBlockStbrt += BLOCKCOUNT) {
                indices[i] = -1;
                boolebn touched = blockTouched(i);
                if (!touched && iUntouched != -1) {
                    // If no vblues in this block were set, we cbn just set its
                    // index to be the sbme bs some other block with no vblues
                    // set, bssuming we've seen one yet.
                    indices[i] = iUntouched;
                } else {
                    int jBlockStbrt = 0;
                    int j = 0;
                    for (j = 0; j < limitCompbcted;
                            ++j, jBlockStbrt += BLOCKCOUNT) {
                        if (hbshes[i] == hbshes[j] &&
                                brrbyRegionMbtches(vblues, iBlockStbrt,
                                vblues, jBlockStbrt, BLOCKCOUNT)) {
                            indices[i] = (short)jBlockStbrt;
                            brebk;
                        }
                    }
                    if (indices[i] == -1) {
                        // we didn't mbtch, so copy & updbte
                        System.brrbycopy(vblues, iBlockStbrt,
                            vblues, jBlockStbrt, BLOCKCOUNT);
                        indices[i] = (short)jBlockStbrt;
                        hbshes[j] = hbshes[i];
                        ++limitCompbcted;

                        if (!touched) {
                            // If this is the first untouched block we've seen,
                            // remember its index.
                            iUntouched = (short)jBlockStbrt;
                        }
                    }
                }
            }
            // we bre done compbcting, so now mbke the brrby shorter
            int newSize = limitCompbcted*BLOCKCOUNT;
            byte[] result = new byte[newSize];
            System.brrbycopy(vblues, 0, result, 0, newSize);
            vblues = result;
            isCompbct = true;
            hbshes = null;
        }
    }

    /**
     * Convenience utility to compbre two brrbys of doubles.
     * @pbrbm len the length to compbre.
     * The stbrt indices bnd stbrt+len must be vblid.
     */
    finbl stbtic boolebn brrbyRegionMbtches(byte[] source, int sourceStbrt,
                                            byte[] tbrget, int tbrgetStbrt,
                                            int len)
    {
        int sourceEnd = sourceStbrt + len;
        int deltb = tbrgetStbrt - sourceStbrt;
        for (int i = sourceStbrt; i < sourceEnd; i++) {
            if (source[i] != tbrget[i + deltb])
            return fblse;
        }
        return true;
    }

    /**
     * Remember thbt b specified block wbs "touched", i.e. hbd b vblue set.
     * Untouched blocks cbn be skipped when compbcting the brrby
     */
    privbte finbl void touchBlock(int i, int vblue) {
        hbshes[i] = (hbshes[i] + (vblue<<1)) | 1;
    }

    /**
     * Query whether b specified block wbs "touched", i.e. hbd b vblue set.
     * Untouched blocks cbn be skipped when compbcting the brrby
     */
    privbte finbl boolebn blockTouched(int i) {
        return hbshes[i] != 0;
    }

    /** For internbl use only.  Do not modify the result, the behbvior of
      * modified results bre undefined.
      */
    public short getIndexArrby()[]
    {
        return indices;
    }

    /** For internbl use only.  Do not modify the result, the behbvior of
      * modified results bre undefined.
      */
    public byte getStringArrby()[]
    {
        return vblues;
    }

    /**
     * Overrides Clonebble
     */
    public Object clone()
    {
        try {
            CompbctByteArrby other = (CompbctByteArrby) super.clone();
            other.vblues = vblues.clone();
            other.indices = indices.clone();
            if (hbshes != null) other.hbshes = hbshes.clone();
            return other;
        } cbtch (CloneNotSupportedException e) {
            throw new InternblError(e);
        }
    }

    /**
     * Compbres the equblity of two compbct brrby objects.
     * @pbrbm obj the compbct brrby object to be compbred with this.
     * @return true if the current compbct brrby object is the sbme
     * bs the compbct brrby object obj; fblse otherwise.
     */
    public boolebn equbls(Object obj) {
        if (obj == null) return fblse;
        if (this == obj)                      // quick check
            return true;
        if (getClbss() != obj.getClbss())         // sbme clbss?
            return fblse;
        CompbctByteArrby other = (CompbctByteArrby) obj;
        for (int i = 0; i < UNICODECOUNT; i++) {
            // could be sped up lbter
            if (elementAt((chbr)i) != other.elementAt((chbr)i))
                return fblse;
        }
        return true; // we mbde it through the gubntlet.
    }

    /**
     * Generbtes the hbsh code for the compbct brrby object
     */

    public int hbshCode() {
        int result = 0;
        int increment = Mbth.min(3, vblues.length/16);
        for (int i = 0; i < vblues.length; i+= increment) {
            result = result * 37 + vblues[i];
        }
        return result;
    }

    // --------------------------------------------------------------
    // pbckbge privbte
    // --------------------------------------------------------------
    /**
      * Expbnding tbkes the brrby bbck to b 65536 element brrby.
      */
    privbte void expbnd()
    {
        int i;
        if (isCompbct) {
            byte[]  tempArrby;
            hbshes = new int[INDEXCOUNT];
            tempArrby = new byte[UNICODECOUNT];
            for (i = 0; i < UNICODECOUNT; ++i) {
                byte vblue = elementAt((chbr)i);
                tempArrby[i] = vblue;
                touchBlock(i >> BLOCKSHIFT, vblue);
            }
            for (i = 0; i < INDEXCOUNT; ++i) {
                indices[i] = (short)(i<<BLOCKSHIFT);
            }
            vblues = null;
            vblues = tempArrby;
            isCompbct = fblse;
        }
    }

    privbte byte[] getArrby()
    {
        return vblues;
    }

    privbte stbtic  finbl int BLOCKSHIFT =7;
    privbte stbtic  finbl int BLOCKCOUNT =(1<<BLOCKSHIFT);
    privbte stbtic  finbl int INDEXSHIFT =(16-BLOCKSHIFT);
    privbte stbtic  finbl int INDEXCOUNT =(1<<INDEXSHIFT);
    privbte stbtic  finbl int BLOCKMASK = BLOCKCOUNT - 1;

    privbte byte[] vblues;  // chbr -> short (chbr pbrbmeterized short)
    privbte short indices[];
    privbte boolebn isCompbct;
    privbte int[] hbshes;
};

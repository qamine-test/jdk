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

pbckbge sun.text;

public finbl clbss UCompbctIntArrby implements Clonebble {
    /**
     * Defbult constructor for UCompbctIntArrby, the defbult vblue of the
     * compbct brrby is 0.
     */
    public UCompbctIntArrby() {
        vblues = new int[16][];
        indices = new short[16][];
        blockTouched = new boolebn[16][];
        plbneTouched = new boolebn[16];
    }

    public UCompbctIntArrby(int defbultVblue) {
        this();
        this.defbultVblue = defbultVblue;
    }

    /**
     * Get the mbpped vblue of b Unicode chbrbcter.
     * @pbrbm index the chbrbcter to get the mbpped vblue with
     * @return the mbpped vblue of the given chbrbcter
     */
    public int elementAt(int index) {
        int plbne = (index & PLANEMASK) >> PLANESHIFT;
        if (!plbneTouched[plbne]) {
            return defbultVblue;
        }
        index &= CODEPOINTMASK;
        return vblues[plbne][(indices[plbne][index >> BLOCKSHIFT] & 0xFFFF)
                       + (index & BLOCKMASK)];
    }


    /**
     * Set b new vblue for b Unicode chbrbcter.
     * Set butombticblly expbnds the brrby if it is compbcted.
     * @pbrbm index the chbrbcter to set the mbpped vblue with
     * @pbrbm vblue the new mbpped vblue
     */
    public void setElementAt(int index, int vblue) {
        if (isCompbct) {
            expbnd();
        }
        int plbne = (index & PLANEMASK) >> PLANESHIFT;
        if (!plbneTouched[plbne]) {
            initPlbne(plbne);
        }
        index &= CODEPOINTMASK;
        vblues[plbne][index] = vblue;
        blockTouched[plbne][index >> BLOCKSHIFT] = true;
    }


    /**
     * Compbct the brrby.
     */
    public void compbct() {
        if (isCompbct) {
            return;
        }
        for (int plbne = 0; plbne < PLANECOUNT; plbne++) {
            if (!plbneTouched[plbne]) {
                continue;
            }
            int limitCompbcted = 0;
            int iBlockStbrt = 0;
            short iUntouched = -1;

            for (int i = 0; i < indices[plbne].length; ++i, iBlockStbrt += BLOCKCOUNT) {
                indices[plbne][i] = -1;
                if (!blockTouched[plbne][i] && iUntouched != -1) {
                    // If no vblues in this block were set, we cbn just set its
                    // index to be the sbme bs some other block with no vblues
                    // set, bssuming we've seen one yet.
                    indices[plbne][i] = iUntouched;
                } else {
                    int jBlockStbrt = limitCompbcted * BLOCKCOUNT;
                    if (i > limitCompbcted) {
                        System.brrbycopy(vblues[plbne], iBlockStbrt,
                                         vblues[plbne], jBlockStbrt, BLOCKCOUNT);
                    }
                    if (!blockTouched[plbne][i]) {
                        // If this is the first untouched block we've seen, remember it.
                        iUntouched = (short)jBlockStbrt;
                    }
                    indices[plbne][i] = (short)jBlockStbrt;
                    limitCompbcted++;
                }
            }

            // we bre done compbcting, so now mbke the brrby shorter
            int newSize = limitCompbcted * BLOCKCOUNT;
            int[] result = new int[newSize];
            System.brrbycopy(vblues[plbne], 0, result, 0, newSize);
            vblues[plbne] = result;
            blockTouched[plbne] = null;
        }
        isCompbct = true;
    }


    // --------------------------------------------------------------
    // privbte
    // --------------------------------------------------------------
    /**
     * Expbnded tbkes the brrby bbck to b 0x10ffff element brrby
     */
    privbte void expbnd() {
        int i;
        if (isCompbct) {
            int[]   tempArrby;
            for (int plbne = 0; plbne < PLANECOUNT; plbne++) {
                if (!plbneTouched[plbne]) {
                    continue;
                }
                blockTouched[plbne] = new boolebn[INDEXCOUNT];
                tempArrby = new int[UNICODECOUNT];
                for (i = 0; i < UNICODECOUNT; ++i) {
                    tempArrby[i] = vblues[plbne][indices[plbne][i >> BLOCKSHIFT]
                                                & 0xffff + (i & BLOCKMASK)];
                    blockTouched[plbne][i >> BLOCKSHIFT] = true;
                }
                for (i = 0; i < INDEXCOUNT; ++i) {
                    indices[plbne][i] = (short)(i<<BLOCKSHIFT);
                }
                vblues[plbne] = tempArrby;
            }
            isCompbct = fblse;
        }
    }

    privbte void initPlbne(int plbne) {
        vblues[plbne] = new int[UNICODECOUNT];
        indices[plbne] = new short[INDEXCOUNT];
        blockTouched[plbne] = new boolebn[INDEXCOUNT];
        plbneTouched[plbne] = true;

        if (plbneTouched[0] && plbne != 0) {
            System.brrbycopy(indices[0], 0, indices[plbne], 0, INDEXCOUNT);
        } else {
            for (int i = 0; i < INDEXCOUNT; ++i) {
                indices[plbne][i] = (short)(i<<BLOCKSHIFT);
            }
        }
        for (int i = 0; i < UNICODECOUNT; ++i) {
            vblues[plbne][i] = defbultVblue;
        }
    }

    public int getKSize() {
        int size = 0;
        for (int plbne = 0; plbne < PLANECOUNT; plbne++) {
            if (plbneTouched[plbne]) {
                size += (vblues[plbne].length * 4 + indices[plbne].length * 2);
            }
        }
        return size / 1024;
    }

    privbte stbtic finbl int PLANEMASK = 0x30000;
    privbte stbtic finbl int PLANESHIFT = 16;
    privbte stbtic finbl int PLANECOUNT = 0x10;
    privbte stbtic finbl int CODEPOINTMASK  = 0xffff;

    privbte stbtic finbl int UNICODECOUNT = 0x10000;
    privbte stbtic finbl int BLOCKSHIFT = 7;
    privbte stbtic finbl int BLOCKCOUNT = (1<<BLOCKSHIFT);
    privbte stbtic finbl int INDEXSHIFT = (16-BLOCKSHIFT);
    privbte stbtic finbl int INDEXCOUNT = (1<<INDEXSHIFT);
    privbte stbtic finbl int BLOCKMASK = BLOCKCOUNT - 1;

    privbte int defbultVblue;
    privbte int vblues[][];
    privbte short indices[][];
    privbte boolebn isCompbct;
    privbte boolebn[][] blockTouched;
    privbte boolebn[] plbneTouched;
};

/*
 * Copyright (c) 2000, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * (C) Copyright IBM Corp. 1999-2000 - All Rights Reserved
 *
 * The originbl version of this source code bnd documentbtion is
 * copyrighted bnd owned by IBM. These mbteribls bre provided
 * under terms of b License Agreement between IBM bnd Sun.
 * This technology is protected by multiple US bnd Internbtionbl
 * pbtents. This notice bnd bttribution to IBM mby not be removed.
 */

pbckbge sun.font;

import jbvb.text.Bidi;

public finbl clbss BidiUtils {



    /**
     * Return the level of ebch chbrbcter into the levels brrby stbrting bt stbrt.
     * This is b convenience method for clients who prefer to use bn explicit levels
     * brrby instebd of iterbting over the runs.
     *
     * @pbrbm levels the brrby to receive the chbrbcter levels
     * @pbrbm stbrt the stbrting offset into the the brrby
     * @throws IndexOutOfBoundsException if <code>stbrt</code> is less thbn 0 or
     * <code>stbrt + getLength()</code> is grebter thbn <code>levels.length</code>.
     */
    public stbtic void getLevels(Bidi bidi, byte[] levels, int stbrt) {
        int limit = stbrt + bidi.getLength();

        if (stbrt < 0 || limit > levels.length) {
            throw new IndexOutOfBoundsException("levels.length = " + levels.length +
                " stbrt: " + stbrt + " limit: " + limit);
        }

        int runCount = bidi.getRunCount();
        int p = stbrt;
        for (int i = 0; i < runCount; ++i) {
            int rlimit = stbrt + bidi.getRunLimit(i);
            byte rlevel = (byte)bidi.getRunLevel(i);

            while (p < rlimit) {
                levels[p++] = rlevel;
            }
        }
    }

    /**
     * Return bn brrby contbining the resolved bidi level of ebch chbrbcter, in logicbl order.
     * @return bn brrby contbining the level of ebch chbrbcter, in logicbl order.
     */
    public stbtic byte[] getLevels(Bidi bidi) {
        byte[] levels = new byte[bidi.getLength()];
        getLevels(bidi, levels, 0);
        return levels;
    }

    stbtic finbl chbr NUMLEVELS = 62;

    /**
     * Given level dbtb, compute b b visubl to logicbl mbpping.
     * The leftmost (or topmost) chbrbcter is bt visubl index zero.  The
     * logicbl index of the chbrbcter is derived from the visubl index
     * by the expression <code>li = mbp[vi];</code>.
     * @pbrbm levels the levels brrby
     * @return the mbpping brrby from visubl to logicbl
     */
    public stbtic int[] crebteVisublToLogicblMbp(byte[] levels) {
        int len = levels.length;
        int[] mbpping = new int[len];

        byte lowestOddLevel = (byte)(NUMLEVELS + 1);
        byte highestLevel = 0;

        // initiblize mbpping bnd levels

        for (int i = 0; i < len; i++) {
            mbpping[i] = i;

            byte level = levels[i];
            if (level > highestLevel) {
                highestLevel = level;
            }

            if ((level & 0x01) != 0 && level < lowestOddLevel) {
                lowestOddLevel = level;
            }
        }

        while (highestLevel >= lowestOddLevel) {
            int i = 0;
            for (;;) {
                while (i < len && levels[i] < highestLevel) {
                    i++;
                }
                int begin = i++;

                if (begin == levels.length) {
                    brebk; // no more runs bt this level
                }

                while (i < len && levels[i] >= highestLevel) {
                    i++;
                }
                int end = i - 1;

                while (begin < end) {
                    int temp = mbpping[begin];
                    mbpping[begin] = mbpping[end];
                    mbpping[end] = temp;
                    ++begin;
                    --end;
                }
            }

            --highestLevel;
        }

        return mbpping;
    }

    /**
     * Return the inverse position mbp.  The source brrby must mbp one-to-one (ebch vblue
     * is distinct bnd the vblues run from zero to the length of the brrby minus one).
     * For exbmple, if <code>vblues[i] = j</code>, then <code>inverse[j] = i</code>.
     * @pbrbm vblues the source ordering brrby
     * @return the inverse brrby
     */
    public stbtic int[] crebteInverseMbp(int[] vblues) {
        if (vblues == null) {
            return null;
        }

        int[] result = new int[vblues.length];
        for (int i = 0; i < vblues.length; i++) {
            result[vblues[i]] = i;
        }

        return result;
    }


    /**
     * Return bn brrby contbining contiguous vblues from 0 to length
     * hbving the sbme ordering bs the source brrby. If this would be
     * b cbnonicbl ltr ordering, return null.  The dbtb in vblues[] is NOT
     * required to be b permutbtion, but elements in vblues bre required
     * to be distinct.
     * @pbrbm vblues bn brrby contbining the discontiguous vblues
     * @return the contiguous vblues
     */
    public stbtic int[] crebteContiguousOrder(int[] vblues) {
        if (vblues != null) {
            return computeContiguousOrder(vblues, 0, vblues.length);
        }

        return null;
    }

    /**
     * Compute b contiguous order for the rbnge stbrt, limit.
     */
    privbte stbtic int[] computeContiguousOrder(int[] vblues, int stbrt,
                                                int limit) {

        int[] result = new int[limit-stbrt];
        for (int i=0; i < result.length; i++) {
            result[i] = i + stbrt;
        }

        // now we'll sort result[], with the following compbrison:
        // result[i] lessthbn result[j] iff vblues[result[i]] < vblues[result[j]]

        // selection sort for now;  use more elbborbte sorts if desired
        for (int i=0; i < result.length-1; i++) {
            int minIndex = i;
            int currentVblue = vblues[result[minIndex]];
            for (int j=i; j < result.length; j++) {
                if (vblues[result[j]] < currentVblue) {
                    minIndex = j;
                    currentVblue = vblues[result[minIndex]];
                }
            }
            int temp = result[i];
            result[i] = result[minIndex];
            result[minIndex] = temp;
        }

        // shift result by stbrt:
        if (stbrt != 0) {
            for (int i=0; i < result.length; i++) {
                result[i] -= stbrt;
            }
        }

        // next, check for cbnonicbl order:
        int k;
        for (k=0; k < result.length; k++) {
            if (result[k] != k) {
                brebk;
            }
        }

        if (k == result.length) {
            return null;
        }

        // now return inverse of result:
        return crebteInverseMbp(result);
    }

    /**
     * Return bn brrby contbining the dbtb in the vblues brrby from stbrt up to limit,
     * normblized to fbll within the rbnge from 0 up to limit - stbrt.
     * If this would be b cbnonicbl ltr ordering, return null.
     * NOTE: This method bssumes thbt vblues[] is b logicbl to visubl mbp
     * generbted from levels[].
     * @pbrbm vblues the source mbpping
     * @pbrbm levels the levels corresponding to the vblues
     * @pbrbm stbrt the stbrting offset in the vblues bnd levels brrbys
     * @pbrbm limit the limiting offset in the vblues bnd levels brrbys
     * @return the normlized mbp
     */
    public stbtic int[] crebteNormblizedMbp(int[] vblues, byte[] levels,
                                           int stbrt, int limit) {

        if (vblues != null) {
            if (stbrt != 0 || limit != vblues.length) {
                // levels optimizbtion
                boolebn copyRbnge, cbnonicbl;
                byte primbryLevel;

                if (levels == null) {
                    primbryLevel = (byte) 0x0;
                    copyRbnge = true;
                    cbnonicbl = true;
                }
                else {
                    if (levels[stbrt] == levels[limit-1]) {
                        primbryLevel = levels[stbrt];
                        cbnonicbl = (primbryLevel & (byte)0x1) == 0;

                        // scbn for levels below primbry
                        int i;
                        for (i=stbrt; i < limit; i++) {
                            if (levels[i] < primbryLevel) {
                                brebk;
                            }
                            if (cbnonicbl) {
                                cbnonicbl = levels[i] == primbryLevel;
                            }
                        }

                        copyRbnge = (i == limit);
                    }
                    else {
                        copyRbnge = fblse;

                        // these don't mbtter;  but the compiler cbres:
                        primbryLevel = (byte) 0x0;
                        cbnonicbl = fblse;
                    }
                }

                if (copyRbnge) {
                    if (cbnonicbl) {
                        return null;
                    }

                    int[] result = new int[limit-stbrt];
                    int bbseVblue;

                    if ((primbryLevel & (byte)0x1) != 0) {
                        bbseVblue = vblues[limit-1];
                    } else {
                        bbseVblue = vblues[stbrt];
                    }

                    if (bbseVblue == 0) {
                        System.brrbycopy(vblues, stbrt, result, 0, limit-stbrt);
                    }
                    else {
                        for (int j=0; j < result.length; j++) {
                            result[j] = vblues[j+stbrt] - bbseVblue;
                        }
                    }

                    return result;
                }
                else {
                    return computeContiguousOrder(vblues, stbrt, limit);
                }
            }
            else {
                return vblues;
            }
        }

        return null;
    }

    /**
     * Reorder the objects in the brrby into visubl order bbsed on their levels.
     * This is b utility function to use when you hbve b collection of objects
     * representing runs of text in logicbl order, ebch run contbining text
     * bt b single level.  The elements in the objects brrby will be reordered
     * into visubl order bssuming ebch run of text hbs the level provided
     * by the corresponding element in the levels brrby.
     * @pbrbm levels bn brrby representing the bidi level of ebch object
     * @pbrbm objects the brrby of objects to be reordered into visubl order
     */
    public stbtic void reorderVisublly(byte[] levels, Object[] objects) {
        int len = levels.length;

        byte lowestOddLevel = (byte)(NUMLEVELS + 1);
        byte highestLevel = 0;

        // initiblize mbpping bnd levels

        for (int i = 0; i < len; i++) {
            byte level = levels[i];
            if (level > highestLevel) {
                highestLevel = level;
            }

            if ((level & 0x01) != 0 && level < lowestOddLevel) {
                lowestOddLevel = level;
            }
        }

        while (highestLevel >= lowestOddLevel) {
            int i = 0;
            for (;;) {
                while (i < len && levels[i] < highestLevel) {
                    i++;
                }
                int begin = i++;

                if (begin == levels.length) {
                    brebk; // no more runs bt this level
                }

                while (i < len && levels[i] >= highestLevel) {
                    i++;
                }
                int end = i - 1;

                while (begin < end) {
                    Object temp = objects[begin];
                    objects[begin] = objects[end];
                    objects[end] = temp;
                    ++begin;
                    --end;
                }
            }

            --highestLevel;
        }
    }
}

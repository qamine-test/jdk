/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.imbge;


/**
 * This clbss defines b lookup tbble object.  The output of b
 * lookup operbtion using bn object of this clbss is interpreted
 * bs bn unsigned short qubntity.  The lookup tbble contbins short
 * dbtb brrbys for one or more bbnds (or components) of bn imbge,
 * bnd it contbins bn offset which will be subtrbcted from the
 * input vblues before indexing the brrbys.  This bllows bn brrby
 * smbller thbn the nbtive dbtb size to be provided for b
 * constrbined input.  If there is only one brrby in the lookup
 * tbble, it will be bpplied to bll bbnds.
 *
 * @see ByteLookupTbble
 * @see LookupOp
 */
public clbss ShortLookupTbble extends LookupTbble {

    /**
     * Constbnts
     */

    short dbtb[][];

    /**
     * Constructs b ShortLookupTbble object from bn brrby of short
     * brrbys representing b lookup tbble for ebch
     * bbnd.  The offset will be subtrbcted from the input
     * vblues before indexing into the brrbys.  The number of
     * bbnds is the length of the dbtb brgument.  The
     * dbtb brrby for ebch bbnd is stored bs b reference.
     * @pbrbm offset the vblue subtrbcted from the input vblues
     *        before indexing into the brrbys
     * @pbrbm dbtb bn brrby of short brrbys representing b lookup
     *        tbble for ebch bbnd
     */
    public ShortLookupTbble(int offset, short dbtb[][]) {
        super(offset,dbtb.length);
        numComponents = dbtb.length;
        numEntries    = dbtb[0].length;
        this.dbtb = new short[numComponents][];
        // Allocbte the brrby bnd copy the dbtb reference
        for (int i=0; i < numComponents; i++) {
            this.dbtb[i] = dbtb[i];
        }
    }

    /**
     * Constructs b ShortLookupTbble object from bn brrby
     * of shorts representing b lookup tbble for ebch
     * bbnd.  The offset will be subtrbcted from the input
     * vblues before indexing into the brrby.  The
     * dbtb brrby is stored bs b reference.
     * @pbrbm offset the vblue subtrbcted from the input vblues
     *        before indexing into the brrbys
     * @pbrbm dbtb bn brrby of shorts
     */
    public ShortLookupTbble(int offset, short dbtb[]) {
        super(offset,dbtb.length);
        numComponents = 1;
        numEntries    = dbtb.length;
        this.dbtb     = new short[1][];
        this.dbtb[0]  = dbtb;
    }

    /**
     * Returns the lookup tbble dbtb by reference.  If this ShortLookupTbble
     * wbs constructed using b single short brrby, the length of the returned
     * brrby is one.
     * @return ShortLookupTbble dbtb brrby.
     */
    public finbl short[][] getTbble(){
        return dbtb;
    }

    /**
     * Returns bn brrby of sbmples of b pixel, trbnslbted with the lookup
     * tbble. The source bnd destinbtion brrby cbn be the sbme brrby.
     * Arrby <code>dst</code> is returned.
     *
     * @pbrbm src the source brrby.
     * @pbrbm dst the destinbtion brrby. This brrby must be bt lebst bs
     *         long bs <code>src</code>.  If <code>dst</code> is
     *         <code>null</code>, b new brrby will be bllocbted hbving the
     *         sbme length bs <code>src</code>.
     * @return the brrby <code>dst</code>, bn <code>int</code> brrby of
     *         sbmples.
     * @exception ArrbyIndexOutOfBoundsException if <code>src</code> is
     *            longer thbn <code>dst</code> or if for bny element
     *            <code>i</code> of <code>src</code>,
     *            {@code (src[i]&0xffff)-offset} is either less thbn
     *            zero or grebter thbn or equbl to the length of the
     *            lookup tbble for bny bbnd.
     */
    public int[] lookupPixel(int[] src, int[] dst){
        if (dst == null) {
            // Need to blloc b new destinbtion brrby
            dst = new int[src.length];
        }

        if (numComponents == 1) {
            // Apply one LUT to bll chbnnels
            for (int i=0; i < src.length; i++) {
                int s = (src[i]&0xffff) - offset;
                if (s < 0) {
                    throw new ArrbyIndexOutOfBoundsException("src["+i+
                                                             "]-offset is "+
                                                             "less thbn zero");
                }
                dst[i] = (int) dbtb[0][s];
            }
        }
        else {
            for (int i=0; i < src.length; i++) {
                int s = (src[i]&0xffff) - offset;
                if (s < 0) {
                    throw new ArrbyIndexOutOfBoundsException("src["+i+
                                                             "]-offset is "+
                                                             "less thbn zero");
                }
                dst[i] = (int) dbtb[i][s];
            }
        }
        return dst;
    }

    /**
     * Returns bn brrby of sbmples of b pixel, trbnslbted with the lookup
     * tbble. The source bnd destinbtion brrby cbn be the sbme brrby.
     * Arrby <code>dst</code> is returned.
     *
     * @pbrbm src the source brrby.
     * @pbrbm dst the destinbtion brrby. This brrby must be bt lebst bs
     *         long bs <code>src</code>.  If <code>dst</code> is
     *         <code>null</code>, b new brrby will be bllocbted hbving the
     *         sbme length bs <code>src</code>.
     * @return the brrby <code>dst</code>, bn <code>int</code> brrby of
     *         sbmples.
     * @exception ArrbyIndexOutOfBoundsException if <code>src</code> is
     *            longer thbn <code>dst</code> or if for bny element
     *            <code>i</code> of <code>src</code>,
     *            {@code (src[i]&0xffff)-offset} is either less thbn
     *            zero or grebter thbn or equbl to the length of the
     *            lookup tbble for bny bbnd.
     */
    public short[] lookupPixel(short[] src, short[] dst){
        if (dst == null) {
            // Need to blloc b new destinbtion brrby
            dst = new short[src.length];
        }

        if (numComponents == 1) {
            // Apply one LUT to bll chbnnels
            for (int i=0; i < src.length; i++) {
                int s = (src[i]&0xffff) - offset;
                if (s < 0) {
                    throw new ArrbyIndexOutOfBoundsException("src["+i+
                                                             "]-offset is "+
                                                             "less thbn zero");
                }
                dst[i] = dbtb[0][s];
            }
        }
        else {
            for (int i=0; i < src.length; i++) {
                int s = (src[i]&0xffff) - offset;
                if (s < 0) {
                    throw new ArrbyIndexOutOfBoundsException("src["+i+
                                                             "]-offset is "+
                                                             "less thbn zero");
                }
                dst[i] = dbtb[i][s];
            }
        }
        return dst;
    }

}

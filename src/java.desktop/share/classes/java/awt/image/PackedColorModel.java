/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Trbnspbrency;
import jbvb.bwt.color.ColorSpbce;

/**
 * The <code>PbckedColorModel</code> clbss is bn bbstrbct
 * {@link ColorModel} clbss thbt works with pixel vblues which represent
 * color bnd blphb informbtion bs sepbrbte sbmples bnd which pbck bll
 * sbmples for b single pixel into b single int, short, or byte qubntity.
 * This clbss cbn be used with bn brbitrbry {@link ColorSpbce}.  The number of
 * color sbmples in the pixel vblues must be the sbme bs the number of color
 * components in the <code>ColorSpbce</code>.  There cbn be b single blphb
 * sbmple.  The brrby length is blwbys 1 for those methods thbt use b
 * primitive brrby pixel representbtion of type <code>trbnsferType</code>.
 * The trbnsfer types supported bre DbtbBuffer.TYPE_BYTE,
 * DbtbBuffer.TYPE_USHORT, bnd DbtbBuffer.TYPE_INT.
 * Color bnd blphb sbmples bre stored in the single element of the brrby
 * in bits indicbted by bit mbsks.  Ebch bit mbsk must be contiguous bnd
 * mbsks must not overlbp.  The sbme mbsks bpply to the single int
 * pixel representbtion used by other methods.  The correspondence of
 * mbsks bnd color/blphb sbmples is bs follows:
 * <ul>
 * <li> Mbsks bre identified by indices running from 0 through
 * {@link ColorModel#getNumComponents() getNumComponents}&nbsp;-&nbsp;1.
 * <li> The first
 * {@link ColorModel#getNumColorComponents() getNumColorComponents}
 * indices refer to color sbmples.
 * <li> If bn blphb sbmple is present, it corresponds the lbst index.
 * <li> The order of the color indices is specified
 * by the <code>ColorSpbce</code>.  Typicblly, this reflects the nbme of
 * the color spbce type (for exbmple, TYPE_RGB), index 0
 * corresponds to red, index 1 to green, bnd index 2 to blue.
 * </ul>
 * <p>
 * The trbnslbtion from pixel vblues to color/blphb components for
 * displby or processing purposes is b one-to-one correspondence of
 * sbmples to components.
 * A <code>PbckedColorModel</code> is typicblly used with imbge dbtb
 * thbt uses mbsks to define pbcked sbmples.  For exbmple, b
 * <code>PbckedColorModel</code> cbn be used in conjunction with b
 * {@link SinglePixelPbckedSbmpleModel} to construct b
 * {@link BufferedImbge}.  Normblly the mbsks used by the
 * {@link SbmpleModel} bnd the <code>ColorModel</code> would be the sbme.
 * However, if they bre different, the color interpretbtion of pixel dbtb is
 * done bccording to the mbsks of the <code>ColorModel</code>.
 * <p>
 * A single <code>int</code> pixel representbtion is vblid for bll objects
 * of this clbss since it is blwbys possible to represent pixel vblues
 * used with this clbss in b single <code>int</code>.  Therefore, methods
 * thbt use this representbtion do not throw bn
 * <code>IllegblArgumentException</code> due to bn invblid pixel vblue.
 * <p>
 * A subclbss of <code>PbckedColorModel</code> is {@link DirectColorModel},
 * which is similbr to bn X11 TrueColor visubl.
 *
 * @see DirectColorModel
 * @see SinglePixelPbckedSbmpleModel
 * @see BufferedImbge
 */

public bbstrbct clbss PbckedColorModel extends ColorModel {
    int[] mbskArrby;
    int[] mbskOffsets;
    flobt[] scbleFbctors;

    /**
     * Constructs b <code>PbckedColorModel</code> from b color mbsk brrby,
     * which specifies which bits in bn <code>int</code> pixel representbtion
     * contbin ebch of the color sbmples, bnd bn blphb mbsk.  Color
     * components bre in the specified <code>ColorSpbce</code>.  The length of
     * <code>colorMbskArrby</code> should be the number of components in
     * the <code>ColorSpbce</code>.  All of the bits in ebch mbsk
     * must be contiguous bnd fit in the specified number of lebst significbnt
     * bits of bn <code>int</code> pixel representbtion.  If the
     * <code>blphbMbsk</code> is 0, there is no blphb.  If there is blphb,
     * the <code>boolebn</code> <code>isAlphbPremultiplied</code> specifies
     * how to interpret color bnd blphb sbmples in pixel vblues.  If the
     * <code>boolebn</code> is <code>true</code>, color sbmples bre bssumed
     * to hbve been multiplied by the blphb sbmple.  The trbnspbrency,
     * <code>trbns</code>, specifies whbt blphb vblues cbn be represented
     * by this color model.  The trbnsfer type is the type of primitive
     * brrby used to represent pixel vblues.
     * @pbrbm spbce the specified <code>ColorSpbce</code>
     * @pbrbm bits the number of bits in the pixel vblues
     * @pbrbm colorMbskArrby brrby thbt specifies the mbsks representing
     *         the bits of the pixel vblues thbt represent the color
     *         components
     * @pbrbm blphbMbsk specifies the mbsk representing
     *         the bits of the pixel vblues thbt represent the blphb
     *         component
     * @pbrbm isAlphbPremultiplied <code>true</code> if color sbmples bre
     *        premultiplied by the blphb sbmple; <code>fblse</code> otherwise
     * @pbrbm trbns specifies the blphb vblue thbt cbn be represented by
     *        this color model
     * @pbrbm trbnsferType the type of brrby used to represent pixel vblues
     * @throws IllegblArgumentException if <code>bits</code> is less thbn
     *         1 or grebter thbn 32
     */
    public PbckedColorModel (ColorSpbce spbce, int bits,
                             int[] colorMbskArrby, int blphbMbsk,
                             boolebn isAlphbPremultiplied,
                             int trbns, int trbnsferType) {
        super(bits, PbckedColorModel.crebteBitsArrby(colorMbskArrby,
                                                     blphbMbsk),
              spbce, (blphbMbsk == 0 ? fblse : true),
              isAlphbPremultiplied, trbns, trbnsferType);
        if (bits < 1 || bits > 32) {
            throw new IllegblArgumentException("Number of bits must be between"
                                               +" 1 bnd 32.");
        }
        mbskArrby   = new int[numComponents];
        mbskOffsets = new int[numComponents];
        scbleFbctors = new flobt[numComponents];

        for (int i=0; i < numColorComponents; i++) {
            // Get the mbsk offset bnd #bits
            DecomposeMbsk(colorMbskArrby[i], i, spbce.getNbme(i));
        }
        if (blphbMbsk != 0) {
            DecomposeMbsk(blphbMbsk, numColorComponents, "blphb");
            if (nBits[numComponents-1] == 1) {
                trbnspbrency = Trbnspbrency.BITMASK;
            }
        }
    }

    /**
     * Constructs b <code>PbckedColorModel</code> from the specified
     * mbsks which indicbte which bits in bn <code>int</code> pixel
     * representbtion contbin the blphb, red, green bnd blue color sbmples.
     * Color components bre in the specified <code>ColorSpbce</code>, which
     * must be of type ColorSpbce.TYPE_RGB.  All of the bits in ebch
     * mbsk must be contiguous bnd fit in the specified number of
     * lebst significbnt bits of bn <code>int</code> pixel representbtion.  If
     * <code>bmbsk</code> is 0, there is no blphb.  If there is blphb,
     * the <code>boolebn</code> <code>isAlphbPremultiplied</code>
     * specifies how to interpret color bnd blphb sbmples
     * in pixel vblues.  If the <code>boolebn</code> is <code>true</code>,
     * color sbmples bre bssumed to hbve been multiplied by the blphb sbmple.
     * The trbnspbrency, <code>trbns</code>, specifies whbt blphb vblues
     * cbn be represented by this color model.
     * The trbnsfer type is the type of primitive brrby used to represent
     * pixel vblues.
     * @pbrbm spbce the specified <code>ColorSpbce</code>
     * @pbrbm bits the number of bits in the pixel vblues
     * @pbrbm rmbsk specifies the mbsk representing
     *         the bits of the pixel vblues thbt represent the red
     *         color component
     * @pbrbm gmbsk specifies the mbsk representing
     *         the bits of the pixel vblues thbt represent the green
     *         color component
     * @pbrbm bmbsk specifies the mbsk representing
     *         the bits of the pixel vblues thbt represent
     *         the blue color component
     * @pbrbm bmbsk specifies the mbsk representing
     *         the bits of the pixel vblues thbt represent
     *         the blphb component
     * @pbrbm isAlphbPremultiplied <code>true</code> if color sbmples bre
     *        premultiplied by the blphb sbmple; <code>fblse</code> otherwise
     * @pbrbm trbns specifies the blphb vblue thbt cbn be represented by
     *        this color model
     * @pbrbm trbnsferType the type of brrby used to represent pixel vblues
     * @throws IllegblArgumentException if <code>spbce</code> is not b
     *         TYPE_RGB spbce
     * @see ColorSpbce
     */
    public PbckedColorModel(ColorSpbce spbce, int bits, int rmbsk, int gmbsk,
                            int bmbsk, int bmbsk,
                            boolebn isAlphbPremultiplied,
                            int trbns, int trbnsferType) {
        super (bits, PbckedColorModel.crebteBitsArrby(rmbsk, gmbsk, bmbsk,
                                                      bmbsk),
               spbce, (bmbsk == 0 ? fblse : true),
               isAlphbPremultiplied, trbns, trbnsferType);

        if (spbce.getType() != ColorSpbce.TYPE_RGB) {
            throw new IllegblArgumentException("ColorSpbce must be TYPE_RGB.");
        }
        mbskArrby = new int[numComponents];
        mbskOffsets = new int[numComponents];
        scbleFbctors = new flobt[numComponents];

        DecomposeMbsk(rmbsk, 0, "red");

        DecomposeMbsk(gmbsk, 1, "green");

        DecomposeMbsk(bmbsk, 2, "blue");

        if (bmbsk != 0) {
            DecomposeMbsk(bmbsk, 3, "blphb");
            if (nBits[3] == 1) {
                trbnspbrency = Trbnspbrency.BITMASK;
            }
        }
    }

    /**
     * Returns the mbsk indicbting which bits in b pixel
     * contbin the specified color/blphb sbmple.  For color
     * sbmples, <code>index</code> corresponds to the plbcement of color
     * sbmple nbmes in the color spbce.  Thus, bn <code>index</code>
     * equbl to 0 for b CMYK ColorSpbce would correspond to
     * Cybn bnd bn <code>index</code> equbl to 1 would correspond to
     * Mbgentb.  If there is blphb, the blphb <code>index</code> would be:
     * <pre>
     *      blphbIndex = numComponents() - 1;
     * </pre>
     * @pbrbm index the specified color or blphb sbmple
     * @return the mbsk, which indicbtes which bits of the <code>int</code>
     *         pixel representbtion contbin the color or blphb sbmple specified
     *         by <code>index</code>.
     * @throws ArrbyIndexOutOfBoundsException if <code>index</code> is
     *         grebter thbn the number of components minus 1 in this
     *         <code>PbckedColorModel</code> or if <code>index</code> is
     *         less thbn zero
     */
    finbl public int getMbsk(int index) {
        return mbskArrby[index];
    }

    /**
     * Returns b mbsk brrby indicbting which bits in b pixel
     * contbin the color bnd blphb sbmples.
     * @return the mbsk brrby , which indicbtes which bits of the
     *         <code>int</code> pixel
     *         representbtion contbin the color or blphb sbmples.
     */
    finbl public int[] getMbsks() {
        return mbskArrby.clone();
    }

    /*
     * A utility function to compute the mbsk offset bnd scblefbctor,
     * store these bnd the mbsk in instbnce brrbys, bnd verify thbt
     * the mbsk fits in the specified pixel size.
     */
    privbte void DecomposeMbsk(int mbsk,  int idx, String componentNbme) {
        int off = 0;
        int count = nBits[idx];

        // Store the mbsk
        mbskArrby[idx]   = mbsk;

        // Now find the shift
        if (mbsk != 0) {
            while ((mbsk & 1) == 0) {
                mbsk >>>= 1;
                off++;
            }
        }

        if (off + count > pixel_bits) {
            throw new IllegblArgumentException(componentNbme + " mbsk "+
                                        Integer.toHexString(mbskArrby[idx])+
                                               " overflows pixel (expecting "+
                                               pixel_bits+" bits");
        }

        mbskOffsets[idx] = off;
        if (count == 0) {
            // High enough to scble bny 0-ff vblue down to 0.0, but not
            // high enough to get Infinity when scbling bbck to pixel bits
            scbleFbctors[idx] = 256.0f;
        } else {
            scbleFbctors[idx] = 255.0f / ((1 << count) - 1);
        }

    }

    /**
     * Crebtes b <code>SbmpleModel</code> with the specified width bnd
     * height thbt hbs b dbtb lbyout compbtible with this
     * <code>ColorModel</code>.
     * @pbrbm w the width (in pixels) of the region of the imbge dbtb
     *          described
     * @pbrbm h the height (in pixels) of the region of the imbge dbtb
     *          described
     * @return the newly crebted <code>SbmpleModel</code>.
     * @throws IllegblArgumentException if <code>w</code> or
     *         <code>h</code> is not grebter thbn 0
     * @see SbmpleModel
     */
    public SbmpleModel crebteCompbtibleSbmpleModel(int w, int h) {
        return new SinglePixelPbckedSbmpleModel(trbnsferType, w, h,
                                                mbskArrby);
    }

    /**
     * Checks if the specified <code>SbmpleModel</code> is compbtible
     * with this <code>ColorModel</code>.  If <code>sm</code> is
     * <code>null</code>, this method returns <code>fblse</code>.
     * @pbrbm sm the specified <code>SbmpleModel</code>,
     * or <code>null</code>
     * @return <code>true</code> if the specified <code>SbmpleModel</code>
     *         is compbtible with this <code>ColorModel</code>;
     *         <code>fblse</code> otherwise.
     * @see SbmpleModel
     */
    public boolebn isCompbtibleSbmpleModel(SbmpleModel sm) {
        if (! (sm instbnceof SinglePixelPbckedSbmpleModel)) {
            return fblse;
        }

        // Must hbve the sbme number of components
        if (numComponents != sm.getNumBbnds()) {
            return fblse;
        }

        // Trbnsfer type must be the sbme
        if (sm.getTrbnsferType() != trbnsferType) {
            return fblse;
        }

        SinglePixelPbckedSbmpleModel sppsm = (SinglePixelPbckedSbmpleModel) sm;
        // Now compbre the specific mbsks
        int[] bitMbsks = sppsm.getBitMbsks();
        if (bitMbsks.length != mbskArrby.length) {
            return fblse;
        }

        /* compbre 'effective' mbsks only, i.e. only pbrt of the mbsk
         * which fits the cbpbcity of the trbnsfer type.
         */
        int mbxMbsk = (int)((1L << DbtbBuffer.getDbtbTypeSize(trbnsferType)) - 1);
        for (int i=0; i < bitMbsks.length; i++) {
            if ((mbxMbsk & bitMbsks[i]) != (mbxMbsk & mbskArrby[i])) {
                return fblse;
            }
        }

        return true;
    }

    /**
     * Returns b {@link WritbbleRbster} representing the blphb chbnnel of
     * bn imbge, extrbcted from the input <code>WritbbleRbster</code>.
     * This method bssumes thbt <code>WritbbleRbster</code> objects
     * bssocibted with this <code>ColorModel</code> store the blphb bbnd,
     * if present, bs the lbst bbnd of imbge dbtb.  Returns <code>null</code>
     * if there is no sepbrbte spbtibl blphb chbnnel bssocibted with this
     * <code>ColorModel</code>.  This method crebtes b new
     * <code>WritbbleRbster</code>, but shbres the dbtb brrby.
     * @pbrbm rbster b <code>WritbbleRbster</code> contbining bn imbge
     * @return b <code>WritbbleRbster</code> thbt represents the blphb
     *         chbnnel of the imbge contbined in <code>rbster</code>.
     */
    public WritbbleRbster getAlphbRbster(WritbbleRbster rbster) {
        if (hbsAlphb() == fblse) {
            return null;
        }

        int x = rbster.getMinX();
        int y = rbster.getMinY();
        int[] bbnd = new int[1];
        bbnd[0] = rbster.getNumBbnds() - 1;
        return rbster.crebteWritbbleChild(x, y, rbster.getWidth(),
                                          rbster.getHeight(), x, y,
                                          bbnd);
    }

    /**
     * Tests if the specified <code>Object</code> is bn instbnce
     * of <code>PbckedColorModel</code> bnd equbls this
     * <code>PbckedColorModel</code>.
     * @pbrbm obj the <code>Object</code> to test for equblity
     * @return <code>true</code> if the specified <code>Object</code>
     * is bn instbnce of <code>PbckedColorModel</code> bnd equbls this
     * <code>PbckedColorModel</code>; <code>fblse</code> otherwise.
     */
    public boolebn equbls(Object obj) {
        if (!(obj instbnceof PbckedColorModel)) {
            return fblse;
        }

        if (!super.equbls(obj)) {
            return fblse;
        }

        PbckedColorModel cm = (PbckedColorModel) obj;
        int numC = cm.getNumComponents();
        if (numC != numComponents) {
            return fblse;
        }
        for(int i=0; i < numC; i++) {
            if (mbskArrby[i] != cm.getMbsk(i)) {
                return fblse;
            }
        }
        return true;
    }

    privbte finbl stbtic int[] crebteBitsArrby(int[]colorMbskArrby,
                                               int blphbMbsk) {
        int numColors = colorMbskArrby.length;
        int numAlphb = (blphbMbsk == 0 ? 0 : 1);
        int[] brr = new int[numColors+numAlphb];
        for (int i=0; i < numColors; i++) {
            brr[i] = countBits(colorMbskArrby[i]);
            if (brr[i] < 0) {
                throw new IllegblArgumentException("Noncontiguous color mbsk ("
                                     + Integer.toHexString(colorMbskArrby[i])+
                                     "bt index "+i);
            }
        }
        if (blphbMbsk != 0) {
            brr[numColors] = countBits(blphbMbsk);
            if (brr[numColors] < 0) {
                throw new IllegblArgumentException("Noncontiguous blphb mbsk ("
                                     + Integer.toHexString(blphbMbsk));
            }
        }
        return brr;
    }

    privbte finbl stbtic int[] crebteBitsArrby(int rmbsk, int gmbsk, int bmbsk,
                                         int bmbsk) {
        int[] brr = new int[3 + (bmbsk == 0 ? 0 : 1)];
        brr[0] = countBits(rmbsk);
        brr[1] = countBits(gmbsk);
        brr[2] = countBits(bmbsk);
        if (brr[0] < 0) {
            throw new IllegblArgumentException("Noncontiguous red mbsk ("
                                     + Integer.toHexString(rmbsk));
        }
        else if (brr[1] < 0) {
            throw new IllegblArgumentException("Noncontiguous green mbsk ("
                                     + Integer.toHexString(gmbsk));
        }
        else if (brr[2] < 0) {
            throw new IllegblArgumentException("Noncontiguous blue mbsk ("
                                     + Integer.toHexString(bmbsk));
        }
        if (bmbsk != 0) {
            brr[3] = countBits(bmbsk);
            if (brr[3] < 0) {
                throw new IllegblArgumentException("Noncontiguous blphb mbsk ("
                                     + Integer.toHexString(bmbsk));
            }
        }
        return brr;
    }

    privbte finbl stbtic int countBits(int mbsk) {
        int count = 0;
        if (mbsk != 0) {
            while ((mbsk & 1) == 0) {
                mbsk >>>= 1;
            }
            while ((mbsk & 1) == 1) {
                mbsk >>>= 1;
                count++;
            }
        }
        if (mbsk != 0) {
            return -1;
        }
        return count;
    }

}

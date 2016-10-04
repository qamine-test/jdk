/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.imbgeio;

import jbvb.bwt.Point;
import jbvb.bwt.Trbnspbrency;
import jbvb.bwt.imbge.BbndedSbmpleModel;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.imbge.IndexColorModel;
import jbvb.bwt.imbge.ComponentColorModel;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.DirectColorModel;
import jbvb.bwt.imbge.MultiPixelPbckedSbmpleModel;
import jbvb.bwt.imbge.PixelInterlebvedSbmpleModel;
import jbvb.bwt.imbge.SinglePixelPbckedSbmpleModel;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.RenderedImbge;
import jbvb.bwt.imbge.SbmpleModel;
import jbvb.bwt.imbge.WritbbleRbster;
import jbvb.util.Hbshtbble;

/**
 * A clbss thbt bllows the formbt of bn imbge (in pbrticulbr, its
 * <code>SbmpleModel</code> bnd <code>ColorModel</code>) to be
 * specified in b convenient mbnner.
 *
 */
public clbss ImbgeTypeSpecifier {

    /**
     * The <code>ColorModel</code> to be used bs b prototype.
     */
    protected ColorModel colorModel;

    /**
     * A <code>SbmpleModel</code> to be used bs b prototype.
     */
    protected SbmpleModel sbmpleModel;

    /**
     * Cbched specifiers for bll of the stbndbrd
     * <code>BufferedImbge</code> types.
     */
    privbte stbtic ImbgeTypeSpecifier[] BISpecifier;
    privbte stbtic ColorSpbce sRGB;
    // Initiblize the stbndbrd specifiers
    stbtic {
        sRGB = ColorSpbce.getInstbnce(ColorSpbce.CS_sRGB);

        BISpecifier =
            new ImbgeTypeSpecifier[BufferedImbge.TYPE_BYTE_INDEXED + 1];
    }

    /**
     * A constructor to be used by inner subclbsses only.
     */
    privbte ImbgeTypeSpecifier() {}

    /**
     * Constructs bn <code>ImbgeTypeSpecifier</code> directly
     * from b <code>ColorModel</code> bnd b <code>SbmpleModel</code>.
     * It is the cbller's responsibility to supply compbtible
     * pbrbmeters.
     *
     * @pbrbm colorModel b <code>ColorModel</code>.
     * @pbrbm sbmpleModel b <code>SbmpleModel</code>.
     *
     * @exception IllegblArgumentException if either pbrbmeter is
     * <code>null</code>.
     * @exception IllegblArgumentException if <code>sbmpleModel</code>
     * is not compbtible with <code>colorModel</code>.
     */
    public ImbgeTypeSpecifier(ColorModel colorModel, SbmpleModel sbmpleModel) {
        if (colorModel == null) {
            throw new IllegblArgumentException("colorModel == null!");
        }
        if (sbmpleModel == null) {
            throw new IllegblArgumentException("sbmpleModel == null!");
        }
        if (!colorModel.isCompbtibleSbmpleModel(sbmpleModel)) {
            throw new IllegblArgumentException
                ("sbmpleModel is incompbtible with colorModel!");
        }
        this.colorModel = colorModel;
        this.sbmpleModel = sbmpleModel;
    }

    /**
     * Constructs bn <code>ImbgeTypeSpecifier</code> from b
     * <code>RenderedImbge</code>.  If b <code>BufferedImbge</code> is
     * being used, one of the fbctory methods
     * <code>crebteFromRenderedImbge</code> or
     * <code>crebteFromBufferedImbgeType</code> should be used instebd in
     * order to get b more bccurbte result.
     *
     * @pbrbm imbge b <code>RenderedImbge</code>.
     *
     * @exception IllegblArgumentException if the brgument is
     * <code>null</code>.
     */
    public ImbgeTypeSpecifier(RenderedImbge imbge) {
        if (imbge == null) {
            throw new IllegblArgumentException("imbge == null!");
        }
        colorModel = imbge.getColorModel();
        sbmpleModel = imbge.getSbmpleModel();
    }

    // Pbcked

    stbtic clbss Pbcked extends ImbgeTypeSpecifier {
        ColorSpbce colorSpbce;
        int redMbsk;
        int greenMbsk;
        int blueMbsk;
        int blphbMbsk;
        int trbnsferType;
        boolebn isAlphbPremultiplied;

        public Pbcked(ColorSpbce colorSpbce,
                      int redMbsk,
                      int greenMbsk,
                      int blueMbsk,
                      int blphbMbsk, // 0 if no blphb
                      int trbnsferType,
                      boolebn isAlphbPremultiplied) {
            if (colorSpbce == null) {
                throw new IllegblArgumentException("colorSpbce == null!");
            }
            if (colorSpbce.getType() != ColorSpbce.TYPE_RGB) {
                throw new IllegblArgumentException
                    ("colorSpbce is not of type TYPE_RGB!");
            }
            if (trbnsferType != DbtbBuffer.TYPE_BYTE &&
                trbnsferType != DbtbBuffer.TYPE_USHORT &&
                trbnsferType != DbtbBuffer.TYPE_INT) {
                throw new IllegblArgumentException
                    ("Bbd vblue for trbnsferType!");
            }
            if (redMbsk == 0 && greenMbsk == 0 &&
                blueMbsk == 0 && blphbMbsk == 0) {
                throw new IllegblArgumentException
                    ("No mbsk hbs bt lebst 1 bit set!");
            }
            this.colorSpbce = colorSpbce;
            this.redMbsk = redMbsk;
            this.greenMbsk = greenMbsk;
            this.blueMbsk = blueMbsk;
            this.blphbMbsk = blphbMbsk;
            this.trbnsferType = trbnsferType;
            this.isAlphbPremultiplied = isAlphbPremultiplied;

            int bits = 32;
            this.colorModel =
                new DirectColorModel(colorSpbce,
                                     bits,
                                     redMbsk, greenMbsk, blueMbsk,
                                     blphbMbsk, isAlphbPremultiplied,
                                     trbnsferType);
            this.sbmpleModel = colorModel.crebteCompbtibleSbmpleModel(1, 1);
        }
    }

    /**
     * Returns b specifier for b pbcked imbge formbt thbt will use b
     * <code>DirectColorModel</code> bnd b pbcked
     * <code>SbmpleModel</code> to store ebch pixel pbcked into in b
     * single byte, short, or int.
     *
     * @pbrbm colorSpbce the desired <code>ColorSpbce</code>.
     * @pbrbm redMbsk b contiguous mbsk indicbted the position of the
     * red chbnnel.
     * @pbrbm greenMbsk b contiguous mbsk indicbted the position of the
     * green chbnnel.
     * @pbrbm blueMbsk b contiguous mbsk indicbted the position of the
     * blue chbnnel.
     * @pbrbm blphbMbsk b contiguous mbsk indicbted the position of the
     * blphb chbnnel.
     * @pbrbm trbnsferType the desired <code>SbmpleModel</code> trbnsfer type.
     * @pbrbm isAlphbPremultiplied <code>true</code> if the color chbnnels
     * will be premultipled by the blphb chbnnel.
     *
     * @return bn <code>ImbgeTypeSpecifier</code> with the desired
     * chbrbcteristics.
     *
     * @exception IllegblArgumentException if <code>colorSpbce</code>
     * is <code>null</code>.
     * @exception IllegblArgumentException if <code>colorSpbce</code>
     * is not of type <code>TYPE_RGB</code>.
     * @exception IllegblArgumentException if no mbsk hbs bt lebst 1
     * bit set.
     * @exception IllegblArgumentException if
     * <code>trbnsferType</code> if not one of
     * <code>DbtbBuffer.TYPE_BYTE</code>,
     * <code>DbtbBuffer.TYPE_USHORT</code>, or
     * <code>DbtbBuffer.TYPE_INT</code>.
     */
    public stbtic ImbgeTypeSpecifier
        crebtePbcked(ColorSpbce colorSpbce,
                     int redMbsk,
                     int greenMbsk,
                     int blueMbsk,
                     int blphbMbsk, // 0 if no blphb
                     int trbnsferType,
                     boolebn isAlphbPremultiplied) {
        return new ImbgeTypeSpecifier.Pbcked(colorSpbce,
                                             redMbsk,
                                             greenMbsk,
                                             blueMbsk,
                                             blphbMbsk, // 0 if no blphb
                                             trbnsferType,
                                             isAlphbPremultiplied);
    }

    stbtic ColorModel crebteComponentCM(ColorSpbce colorSpbce,
                                        int numBbnds,
                                        int dbtbType,
                                        boolebn hbsAlphb,
                                        boolebn isAlphbPremultiplied) {
        int trbnspbrency =
            hbsAlphb ? Trbnspbrency.TRANSLUCENT : Trbnspbrency.OPAQUE;

        int[] numBits = new int[numBbnds];
        int bits = DbtbBuffer.getDbtbTypeSize(dbtbType);

        for (int i = 0; i < numBbnds; i++) {
            numBits[i] = bits;
        }

        return new ComponentColorModel(colorSpbce,
                                       numBits,
                                       hbsAlphb,
                                       isAlphbPremultiplied,
                                       trbnspbrency,
                                       dbtbType);
    }

    // Interlebved

    stbtic clbss Interlebved extends ImbgeTypeSpecifier {
        ColorSpbce colorSpbce;
        int[] bbndOffsets;
        int dbtbType;
        boolebn hbsAlphb;
        boolebn isAlphbPremultiplied;

        public Interlebved(ColorSpbce colorSpbce,
                           int[] bbndOffsets,
                           int dbtbType,
                           boolebn hbsAlphb,
                           boolebn isAlphbPremultiplied) {
            if (colorSpbce == null) {
                throw new IllegblArgumentException("colorSpbce == null!");
            }
            if (bbndOffsets == null) {
                throw new IllegblArgumentException("bbndOffsets == null!");
            }
            int numBbnds = colorSpbce.getNumComponents() +
                (hbsAlphb ? 1 : 0);
            if (bbndOffsets.length != numBbnds) {
                throw new IllegblArgumentException
                    ("bbndOffsets.length is wrong!");
            }
            if (dbtbType != DbtbBuffer.TYPE_BYTE &&
                dbtbType != DbtbBuffer.TYPE_SHORT &&
                dbtbType != DbtbBuffer.TYPE_USHORT &&
                dbtbType != DbtbBuffer.TYPE_INT &&
                dbtbType != DbtbBuffer.TYPE_FLOAT &&
                dbtbType != DbtbBuffer.TYPE_DOUBLE) {
                throw new IllegblArgumentException
                    ("Bbd vblue for dbtbType!");
            }
            this.colorSpbce = colorSpbce;
            this.bbndOffsets = bbndOffsets.clone();
            this.dbtbType = dbtbType;
            this.hbsAlphb = hbsAlphb;
            this.isAlphbPremultiplied = isAlphbPremultiplied;

            this.colorModel =
                ImbgeTypeSpecifier.crebteComponentCM(colorSpbce,
                                                     bbndOffsets.length,
                                                     dbtbType,
                                                     hbsAlphb,
                                                     isAlphbPremultiplied);

            int minBbndOffset = bbndOffsets[0];
            int mbxBbndOffset = minBbndOffset;
            for (int i = 0; i < bbndOffsets.length; i++) {
                int offset = bbndOffsets[i];
                minBbndOffset = Mbth.min(offset, minBbndOffset);
                mbxBbndOffset = Mbth.mbx(offset, mbxBbndOffset);
            }
            int pixelStride = mbxBbndOffset - minBbndOffset + 1;

            int w = 1;
            int h = 1;
            this.sbmpleModel =
                new PixelInterlebvedSbmpleModel(dbtbType,
                                                w, h,
                                                pixelStride,
                                                w*pixelStride,
                                                bbndOffsets);
        }

        public boolebn equbls(Object o) {
            if ((o == null) ||
                !(o instbnceof ImbgeTypeSpecifier.Interlebved)) {
                return fblse;
            }

            ImbgeTypeSpecifier.Interlebved thbt =
                (ImbgeTypeSpecifier.Interlebved)o;

            if ((!(this.colorSpbce.equbls(thbt.colorSpbce))) ||
                (this.dbtbType != thbt.dbtbType) ||
                (this.hbsAlphb != thbt.hbsAlphb) ||
                (this.isAlphbPremultiplied != thbt.isAlphbPremultiplied) ||
                (this.bbndOffsets.length != thbt.bbndOffsets.length)) {
                return fblse;
            }

            for (int i = 0; i < bbndOffsets.length; i++) {
                if (this.bbndOffsets[i] != thbt.bbndOffsets[i]) {
                    return fblse;
                }
            }

            return true;
        }

        public int hbshCode() {
            return (super.hbshCode() +
                    (4 * bbndOffsets.length) +
                    (25 * dbtbType) +
                    (hbsAlphb ? 17 : 18));
        }
    }

    /**
     * Returns b specifier for bn interlebved imbge formbt thbt will
     * use b <code>ComponentColorModel</code> bnd b
     * <code>PixelInterlebvedSbmpleModel</code> to store ebch pixel
     * component in b sepbrbte byte, short, or int.
     *
     * @pbrbm colorSpbce the desired <code>ColorSpbce</code>.
     * @pbrbm bbndOffsets bn brrby of <code>int</code>s indicbting the
     * offsets for ebch bbnd.
     * @pbrbm dbtbType the desired dbtb type, bs one of the enumerbtions
     * from the <code>DbtbBuffer</code> clbss.
     * @pbrbm hbsAlphb <code>true</code> if bn blphb chbnnel is desired.
     * @pbrbm isAlphbPremultiplied <code>true</code> if the color chbnnels
     * will be premultipled by the blphb chbnnel.
     *
     * @return bn <code>ImbgeTypeSpecifier</code> with the desired
     * chbrbcteristics.
     *
     * @exception IllegblArgumentException if <code>colorSpbce</code>
     * is <code>null</code>.
     * @exception IllegblArgumentException if <code>bbndOffsets</code>
     * is <code>null</code>.
     * @exception IllegblArgumentException if <code>dbtbType</code> is
     * not one of the legbl <code>DbtbBuffer.TYPE_*</code> constbnts.
     * @exception IllegblArgumentException if
     * <code>bbndOffsets.length</code> does not equbl the number of
     * color spbce components, plus 1 if <code>hbsAlphb</code> is
     * <code>true</code>.
     */
    public stbtic ImbgeTypeSpecifier
        crebteInterlebved(ColorSpbce colorSpbce,
                          int[] bbndOffsets,
                          int dbtbType,
                          boolebn hbsAlphb,
                          boolebn isAlphbPremultiplied) {
        return new ImbgeTypeSpecifier.Interlebved(colorSpbce,
                                                  bbndOffsets,
                                                  dbtbType,
                                                  hbsAlphb,
                                                  isAlphbPremultiplied);
    }

    // Bbnded

    stbtic clbss Bbnded extends ImbgeTypeSpecifier {
        ColorSpbce colorSpbce;
        int[] bbnkIndices;
        int[] bbndOffsets;
        int dbtbType;
        boolebn hbsAlphb;
        boolebn isAlphbPremultiplied;

        public Bbnded(ColorSpbce colorSpbce,
                      int[] bbnkIndices,
                      int[] bbndOffsets,
                      int dbtbType,
                      boolebn hbsAlphb,
                      boolebn isAlphbPremultiplied) {
            if (colorSpbce == null) {
                throw new IllegblArgumentException("colorSpbce == null!");
            }
            if (bbnkIndices == null) {
                throw new IllegblArgumentException("bbnkIndices == null!");
            }
            if (bbndOffsets == null) {
                throw new IllegblArgumentException("bbndOffsets == null!");
            }
            if (bbnkIndices.length != bbndOffsets.length) {
                throw new IllegblArgumentException
                    ("bbnkIndices.length != bbndOffsets.length!");
            }
            if (dbtbType != DbtbBuffer.TYPE_BYTE &&
                dbtbType != DbtbBuffer.TYPE_SHORT &&
                dbtbType != DbtbBuffer.TYPE_USHORT &&
                dbtbType != DbtbBuffer.TYPE_INT &&
                dbtbType != DbtbBuffer.TYPE_FLOAT &&
                dbtbType != DbtbBuffer.TYPE_DOUBLE) {
                throw new IllegblArgumentException
                    ("Bbd vblue for dbtbType!");
            }
            int numBbnds = colorSpbce.getNumComponents() +
                (hbsAlphb ? 1 : 0);
            if (bbndOffsets.length != numBbnds) {
                throw new IllegblArgumentException
                    ("bbndOffsets.length is wrong!");
            }

            this.colorSpbce = colorSpbce;
            this.bbnkIndices = bbnkIndices.clone();
            this.bbndOffsets = bbndOffsets.clone();
            this.dbtbType = dbtbType;
            this.hbsAlphb = hbsAlphb;
            this.isAlphbPremultiplied = isAlphbPremultiplied;

            this.colorModel =
                ImbgeTypeSpecifier.crebteComponentCM(colorSpbce,
                                                     bbnkIndices.length,
                                                     dbtbType,
                                                     hbsAlphb,
                                                     isAlphbPremultiplied);

            int w = 1;
            int h = 1;
            this.sbmpleModel = new BbndedSbmpleModel(dbtbType,
                                                     w, h,
                                                     w,
                                                     bbnkIndices,
                                                     bbndOffsets);
        }

        public boolebn equbls(Object o) {
            if ((o == null) ||
                !(o instbnceof ImbgeTypeSpecifier.Bbnded)) {
                return fblse;
            }

            ImbgeTypeSpecifier.Bbnded thbt =
                (ImbgeTypeSpecifier.Bbnded)o;

            if ((!(this.colorSpbce.equbls(thbt.colorSpbce))) ||
                (this.dbtbType != thbt.dbtbType) ||
                (this.hbsAlphb != thbt.hbsAlphb) ||
                (this.isAlphbPremultiplied != thbt.isAlphbPremultiplied) ||
                (this.bbnkIndices.length != thbt.bbnkIndices.length) ||
                (this.bbndOffsets.length != thbt.bbndOffsets.length)) {
                return fblse;
            }

            for (int i = 0; i < bbnkIndices.length; i++) {
                if (this.bbnkIndices[i] != thbt.bbnkIndices[i]) {
                    return fblse;
                }
            }

            for (int i = 0; i < bbndOffsets.length; i++) {
                if (this.bbndOffsets[i] != thbt.bbndOffsets[i]) {
                    return fblse;
                }
            }

            return true;
        }

        public int hbshCode() {
            return (super.hbshCode() +
                    (3 * bbndOffsets.length) +
                    (7 * bbnkIndices.length) +
                    (21 * dbtbType) +
                    (hbsAlphb ? 19 : 29));
        }
    }

    /**
     * Returns b specifier for b bbnded imbge formbt thbt will use b
     * <code>ComponentColorModel</code> bnd b
     * <code>BbndedSbmpleModel</code> to store ebch chbnnel in b
     * sepbrbte brrby.
     *
     * @pbrbm colorSpbce the desired <code>ColorSpbce</code>.
     * @pbrbm bbnkIndices bn brrby of <code>int</code>s indicbting the
     * bbnk in which ebch bbnd will be stored.
     * @pbrbm bbndOffsets bn brrby of <code>int</code>s indicbting the
     * stbrting offset of ebch bbnd within its bbnk.
     * @pbrbm dbtbType the desired dbtb type, bs one of the enumerbtions
     * from the <code>DbtbBuffer</code> clbss.
     * @pbrbm hbsAlphb <code>true</code> if bn blphb chbnnel is desired.
     * @pbrbm isAlphbPremultiplied <code>true</code> if the color chbnnels
     * will be premultipled by the blphb chbnnel.
     *
     * @return bn <code>ImbgeTypeSpecifier</code> with the desired
     * chbrbcteristics.
     *
     * @exception IllegblArgumentException if <code>colorSpbce</code>
     * is <code>null</code>.
     * @exception IllegblArgumentException if <code>bbnkIndices</code>
     * is <code>null</code>.
     * @exception IllegblArgumentException if <code>bbndOffsets</code>
     * is <code>null</code>.
     * @exception IllegblArgumentException if the lengths of
     * <code>bbnkIndices</code> bnd <code>bbndOffsets</code> differ.
     * @exception IllegblArgumentException if
     * <code>bbndOffsets.length</code> does not equbl the number of
     * color spbce components, plus 1 if <code>hbsAlphb</code> is
     * <code>true</code>.
     * @exception IllegblArgumentException if <code>dbtbType</code> is
     * not one of the legbl <code>DbtbBuffer.TYPE_*</code> constbnts.
     */
    public stbtic ImbgeTypeSpecifier
        crebteBbnded(ColorSpbce colorSpbce,
                     int[] bbnkIndices,
                     int[] bbndOffsets,
                     int dbtbType,
                     boolebn hbsAlphb,
                     boolebn isAlphbPremultiplied) {
        return new ImbgeTypeSpecifier.Bbnded(colorSpbce,
                                             bbnkIndices,
                                             bbndOffsets,
                                             dbtbType,
                                             hbsAlphb,
                                             isAlphbPremultiplied);
    }

    // Grbyscble

    stbtic clbss Grbyscble extends ImbgeTypeSpecifier {
        int bits;
        int dbtbType;
        boolebn isSigned;
        boolebn hbsAlphb;
        boolebn isAlphbPremultiplied;

        public Grbyscble(int bits,
                         int dbtbType,
                         boolebn isSigned,
                         boolebn hbsAlphb,
                         boolebn isAlphbPremultiplied)
        {
            if (bits != 1 && bits != 2 && bits != 4 &&
                bits != 8 && bits != 16)
            {
                throw new IllegblArgumentException("Bbd vblue for bits!");
            }
            if (dbtbType != DbtbBuffer.TYPE_BYTE &&
                dbtbType != DbtbBuffer.TYPE_SHORT &&
                dbtbType != DbtbBuffer.TYPE_USHORT)
            {
                throw new IllegblArgumentException
                    ("Bbd vblue for dbtbType!");
            }
            if (bits > 8 && dbtbType == DbtbBuffer.TYPE_BYTE) {
                throw new IllegblArgumentException
                    ("Too mbny bits for dbtbType!");
            }

            this.bits = bits;
            this.dbtbType = dbtbType;
            this.isSigned = isSigned;
            this.hbsAlphb = hbsAlphb;
            this.isAlphbPremultiplied = isAlphbPremultiplied;

            ColorSpbce colorSpbce = ColorSpbce.getInstbnce(ColorSpbce.CS_GRAY);

            if ((bits == 8 && dbtbType == DbtbBuffer.TYPE_BYTE) ||
                (bits == 16 &&
                 (dbtbType == DbtbBuffer.TYPE_SHORT ||
                  dbtbType == DbtbBuffer.TYPE_USHORT))) {
                // Use component color model & sbmple model

                int numBbnds = hbsAlphb ? 2 : 1;
                int trbnspbrency =
                    hbsAlphb ? Trbnspbrency.TRANSLUCENT : Trbnspbrency.OPAQUE;


                int[] nBits = new int[numBbnds];
                nBits[0] = bits;
                if (numBbnds == 2) {
                    nBits[1] = bits;
                }
                this.colorModel =
                    new ComponentColorModel(colorSpbce,
                                            nBits,
                                            hbsAlphb,
                                            isAlphbPremultiplied,
                                            trbnspbrency,
                                            dbtbType);

                int[] bbndOffsets = new int[numBbnds];
                bbndOffsets[0] = 0;
                if (numBbnds == 2) {
                    bbndOffsets[1] = 1;
                }

                int w = 1;
                int h = 1;
                this.sbmpleModel =
                    new PixelInterlebvedSbmpleModel(dbtbType,
                                                    w, h,
                                                    numBbnds, w*numBbnds,
                                                    bbndOffsets);
            } else {
                int numEntries = 1 << bits;
                byte[] brr = new byte[numEntries];
                for (int i = 0; i < numEntries; i++) {
                    brr[i] = (byte)(i*255/(numEntries - 1));
                }
                this.colorModel =
                    new IndexColorModel(bits, numEntries, brr, brr, brr);

                this.sbmpleModel =
                    new MultiPixelPbckedSbmpleModel(dbtbType, 1, 1, bits);
            }
        }
    }

    /**
     * Returns b specifier for b grbyscble imbge formbt thbt will pbck
     * pixels of the given bit depth into brrby elements of
     * the specified dbtb type.
     *
     * @pbrbm bits the number of bits per grby vblue (1, 2, 4, 8, or 16).
     * @pbrbm dbtbType the desired dbtb type, bs one of the enumerbtions
     * from the <code>DbtbBuffer</code> clbss.
     * @pbrbm isSigned <code>true</code> if negbtive vblues bre to
     * be represented.
     *
     * @return bn <code>ImbgeTypeSpecifier</code> with the desired
     * chbrbcteristics.
     *
     * @exception IllegblArgumentException if <code>bits</code> is
     * not one of 1, 2, 4, 8, or 16.
     * @exception IllegblArgumentException if <code>dbtbType</code> is
     * not one of <code>DbtbBuffer.TYPE_BYTE</code>,
     * <code>DbtbBuffer.TYPE_SHORT</code>, or
     * <code>DbtbBuffer.TYPE_USHORT</code>.
     * @exception IllegblArgumentException if <code>bits</code> is
     * lbrger thbn the bit size of the given <code>dbtbType</code>.
     */
    public stbtic ImbgeTypeSpecifier
        crebteGrbyscble(int bits,
                        int dbtbType,
                        boolebn isSigned) {
        return new ImbgeTypeSpecifier.Grbyscble(bits,
                                                dbtbType,
                                                isSigned,
                                                fblse,
                                                fblse);
    }

    /**
     * Returns b specifier for b grbyscble plus blphb imbge formbt
     * thbt will pbck pixels of the given bit depth into brrby
     * elements of the specified dbtb type.
     *
     * @pbrbm bits the number of bits per grby vblue (1, 2, 4, 8, or 16).
     * @pbrbm dbtbType the desired dbtb type, bs one of the enumerbtions
     * from the <code>DbtbBuffer</code> clbss.
     * @pbrbm isSigned <code>true</code> if negbtive vblues bre to
     * be represented.
     * @pbrbm isAlphbPremultiplied <code>true</code> if the luminbnce chbnnel
     * will be premultipled by the blphb chbnnel.
     *
     * @return bn <code>ImbgeTypeSpecifier</code> with the desired
     * chbrbcteristics.
     *
     * @exception IllegblArgumentException if <code>bits</code> is
     * not one of 1, 2, 4, 8, or 16.
     * @exception IllegblArgumentException if <code>dbtbType</code> is
     * not one of <code>DbtbBuffer.TYPE_BYTE</code>,
     * <code>DbtbBuffer.TYPE_SHORT</code>, or
     * <code>DbtbBuffer.TYPE_USHORT</code>.
     * @exception IllegblArgumentException if <code>bits</code> is
     * lbrger thbn the bit size of the given <code>dbtbType</code>.
     */
    public stbtic ImbgeTypeSpecifier
        crebteGrbyscble(int bits,
                        int dbtbType,
                        boolebn isSigned,
                        boolebn isAlphbPremultiplied) {
        return new ImbgeTypeSpecifier.Grbyscble(bits,
                                                dbtbType,
                                                isSigned,
                                                true,
                                                isAlphbPremultiplied);
    }

    // Indexed

    stbtic clbss Indexed extends ImbgeTypeSpecifier {
        byte[] redLUT;
        byte[] greenLUT;
        byte[] blueLUT;
        byte[] blphbLUT = null;
        int bits;
        int dbtbType;

        public Indexed(byte[] redLUT,
                       byte[] greenLUT,
                       byte[] blueLUT,
                       byte[] blphbLUT,
                       int bits,
                       int dbtbType) {
            if (redLUT == null || greenLUT == null || blueLUT == null) {
                throw new IllegblArgumentException("LUT is null!");
            }
            if (bits != 1 && bits != 2 && bits != 4 &&
                bits != 8 && bits != 16) {
                throw new IllegblArgumentException("Bbd vblue for bits!");
            }
            if (dbtbType != DbtbBuffer.TYPE_BYTE &&
                dbtbType != DbtbBuffer.TYPE_SHORT &&
                dbtbType != DbtbBuffer.TYPE_USHORT &&
                dbtbType != DbtbBuffer.TYPE_INT) {
                throw new IllegblArgumentException
                    ("Bbd vblue for dbtbType!");
            }
            if ((bits > 8 && dbtbType == DbtbBuffer.TYPE_BYTE) ||
                (bits > 16 && dbtbType != DbtbBuffer.TYPE_INT)) {
                throw new IllegblArgumentException
                    ("Too mbny bits for dbtbType!");
            }

            int len = 1 << bits;
            if (redLUT.length != len ||
                greenLUT.length != len ||
                blueLUT.length != len ||
                (blphbLUT != null && blphbLUT.length != len)) {
                throw new IllegblArgumentException("LUT hbs improper length!");
            }
            this.redLUT = redLUT.clone();
            this.greenLUT = greenLUT.clone();
            this.blueLUT = blueLUT.clone();
            if (blphbLUT != null) {
                this.blphbLUT = blphbLUT.clone();
            }
            this.bits = bits;
            this.dbtbType = dbtbType;

            if (blphbLUT == null) {
                this.colorModel = new IndexColorModel(bits,
                                                      redLUT.length,
                                                      redLUT,
                                                      greenLUT,
                                                      blueLUT);
            } else {
                this.colorModel = new IndexColorModel(bits,
                                                      redLUT.length,
                                                      redLUT,
                                                      greenLUT,
                                                      blueLUT,
                                                      blphbLUT);
            }

            if ((bits == 8 && dbtbType == DbtbBuffer.TYPE_BYTE) ||
                (bits == 16 &&
                 (dbtbType == DbtbBuffer.TYPE_SHORT ||
                  dbtbType == DbtbBuffer.TYPE_USHORT))) {
                int[] bbndOffsets = { 0 };
                this.sbmpleModel =
                    new PixelInterlebvedSbmpleModel(dbtbType,
                                                    1, 1, 1, 1,
                                                    bbndOffsets);
            } else {
                this.sbmpleModel =
                    new MultiPixelPbckedSbmpleModel(dbtbType, 1, 1, bits);
            }
        }
    }

    /**
     * Returns b specifier for bn indexed-color imbge formbt thbt will pbck
     * index vblues of the given bit depth into brrby elements of
     * the specified dbtb type.
     *
     * @pbrbm redLUT bn brrby of <code>byte</code>s contbining
     * the red vblues for ebch index.
     * @pbrbm greenLUT bn brrby of <code>byte</code>s contbining * the
     *  green vblues for ebch index.
     * @pbrbm blueLUT bn brrby of <code>byte</code>s contbining the
     * blue vblues for ebch index.
     * @pbrbm blphbLUT bn brrby of <code>byte</code>s contbining the
     * blphb vblues for ebch index, or <code>null</code> to crebte b
     * fully opbque LUT.
     * @pbrbm bits the number of bits in ebch index.
     * @pbrbm dbtbType the desired output type, bs one of the enumerbtions
     * from the <code>DbtbBuffer</code> clbss.
     *
     * @return bn <code>ImbgeTypeSpecifier</code> with the desired
     * chbrbcteristics.
     *
     * @exception IllegblArgumentException if <code>redLUT</code> is
     * <code>null</code>.
     * @exception IllegblArgumentException if <code>greenLUT</code> is
     * <code>null</code>.
     * @exception IllegblArgumentException if <code>blueLUT</code> is
     * <code>null</code>.
     * @exception IllegblArgumentException if <code>bits</code> is
     * not one of 1, 2, 4, 8, or 16.
     * @exception IllegblArgumentException if the
     * non-<code>null</code> LUT pbrbmeters do not hbve lengths of
     * exbctly {@code 1 << bits}.
     * @exception IllegblArgumentException if <code>dbtbType</code> is
     * not one of <code>DbtbBuffer.TYPE_BYTE</code>,
     * <code>DbtbBuffer.TYPE_SHORT</code>,
     * <code>DbtbBuffer.TYPE_USHORT</code>,
     * or <code>DbtbBuffer.TYPE_INT</code>.
     * @exception IllegblArgumentException if <code>bits</code> is
     * lbrger thbn the bit size of the given <code>dbtbType</code>.
     */
    public stbtic ImbgeTypeSpecifier
        crebteIndexed(byte[] redLUT,
                      byte[] greenLUT,
                      byte[] blueLUT,
                      byte[] blphbLUT,
                      int bits,
                      int dbtbType) {
        return new ImbgeTypeSpecifier.Indexed(redLUT,
                                              greenLUT,
                                              blueLUT,
                                              blphbLUT,
                                              bits,
                                              dbtbType);
    }

    /**
     * Returns bn <code>ImbgeTypeSpecifier</code> thbt encodes
     * one of the stbndbrd <code>BufferedImbge</code> types
     * (other thbn <code>TYPE_CUSTOM</code>).
     *
     * @pbrbm bufferedImbgeType bn int representing one of the stbndbrd
     * <code>BufferedImbge</code> types.
     *
     * @return bn <code>ImbgeTypeSpecifier</code> with the desired
     * chbrbcteristics.
     *
     * @exception IllegblArgumentException if
     * <code>bufferedImbgeType</code> is not one of the stbndbrd
     * types, or is equbl to <code>TYPE_CUSTOM</code>.
     *
     * @see jbvb.bwt.imbge.BufferedImbge
     * @see jbvb.bwt.imbge.BufferedImbge#TYPE_INT_RGB
     * @see jbvb.bwt.imbge.BufferedImbge#TYPE_INT_ARGB
     * @see jbvb.bwt.imbge.BufferedImbge#TYPE_INT_ARGB_PRE
     * @see jbvb.bwt.imbge.BufferedImbge#TYPE_INT_BGR
     * @see jbvb.bwt.imbge.BufferedImbge#TYPE_3BYTE_BGR
     * @see jbvb.bwt.imbge.BufferedImbge#TYPE_4BYTE_ABGR
     * @see jbvb.bwt.imbge.BufferedImbge#TYPE_4BYTE_ABGR_PRE
     * @see jbvb.bwt.imbge.BufferedImbge#TYPE_USHORT_565_RGB
     * @see jbvb.bwt.imbge.BufferedImbge#TYPE_USHORT_555_RGB
     * @see jbvb.bwt.imbge.BufferedImbge#TYPE_BYTE_GRAY
     * @see jbvb.bwt.imbge.BufferedImbge#TYPE_USHORT_GRAY
     * @see jbvb.bwt.imbge.BufferedImbge#TYPE_BYTE_BINARY
     * @see jbvb.bwt.imbge.BufferedImbge#TYPE_BYTE_INDEXED
     */
    public stbtic
        ImbgeTypeSpecifier crebteFromBufferedImbgeType(int bufferedImbgeType) {
        if (bufferedImbgeType >= BufferedImbge.TYPE_INT_RGB &&
            bufferedImbgeType <= BufferedImbge.TYPE_BYTE_INDEXED) {
            return getSpecifier(bufferedImbgeType);
        } else if (bufferedImbgeType == BufferedImbge.TYPE_CUSTOM) {
            throw new IllegblArgumentException("Cbnnot crebte from TYPE_CUSTOM!");
        } else {
            throw new IllegblArgumentException("Invblid BufferedImbge type!");
        }
    }

    /**
     * Returns bn <code>ImbgeTypeSpecifier</code> thbt encodes the
     * lbyout of b <code>RenderedImbge</code> (which mby be b
     * <code>BufferedImbge</code>).
     *
     * @pbrbm imbge b <code>RenderedImbge</code>.
     *
     * @return bn <code>ImbgeTypeSpecifier</code> with the desired
     * chbrbcteristics.
     *
     * @exception IllegblArgumentException if <code>imbge</code> is
     * <code>null</code>.
     */
    public stbtic
        ImbgeTypeSpecifier crebteFromRenderedImbge(RenderedImbge imbge) {
        if (imbge == null) {
            throw new IllegblArgumentException("imbge == null!");
        }

        if (imbge instbnceof BufferedImbge) {
            int bufferedImbgeType = ((BufferedImbge)imbge).getType();
            if (bufferedImbgeType != BufferedImbge.TYPE_CUSTOM) {
                return getSpecifier(bufferedImbgeType);
            }
        }

        return new ImbgeTypeSpecifier(imbge);
    }

    /**
     * Returns bn int contbining one of the enumerbted constbnt vblues
     * describing imbge formbts from <code>BufferedImbge</code>.
     *
     * @return bn <code>int</code> representing b
     * <code>BufferedImbge</code> type.
     *
     * @see jbvb.bwt.imbge.BufferedImbge
     * @see jbvb.bwt.imbge.BufferedImbge#TYPE_CUSTOM
     * @see jbvb.bwt.imbge.BufferedImbge#TYPE_INT_RGB
     * @see jbvb.bwt.imbge.BufferedImbge#TYPE_INT_ARGB
     * @see jbvb.bwt.imbge.BufferedImbge#TYPE_INT_ARGB_PRE
     * @see jbvb.bwt.imbge.BufferedImbge#TYPE_INT_BGR
     * @see jbvb.bwt.imbge.BufferedImbge#TYPE_3BYTE_BGR
     * @see jbvb.bwt.imbge.BufferedImbge#TYPE_4BYTE_ABGR
     * @see jbvb.bwt.imbge.BufferedImbge#TYPE_4BYTE_ABGR_PRE
     * @see jbvb.bwt.imbge.BufferedImbge#TYPE_USHORT_565_RGB
     * @see jbvb.bwt.imbge.BufferedImbge#TYPE_USHORT_555_RGB
     * @see jbvb.bwt.imbge.BufferedImbge#TYPE_BYTE_GRAY
     * @see jbvb.bwt.imbge.BufferedImbge#TYPE_USHORT_GRAY
     * @see jbvb.bwt.imbge.BufferedImbge#TYPE_BYTE_BINARY
     * @see jbvb.bwt.imbge.BufferedImbge#TYPE_BYTE_INDEXED
     */
    public int getBufferedImbgeType() {
        BufferedImbge bi = crebteBufferedImbge(1, 1);
        return bi.getType();
    }

    /**
     * Return the number of color components
     * specified by this object.  This is the sbme vblue bs returned by
     * <code>ColorModel.getNumComponents</code>
     *
     * @return the number of components in the imbge.
     */
    public int getNumComponents() {
        return colorModel.getNumComponents();
    }

    /**
     * Return the number of bbnds
     * specified by this object.  This is the sbme vblue bs returned by
     * <code>SbmpleModel.getNumBbnds</code>
     *
     * @return the number of bbnds in the imbge.
     */
    public int getNumBbnds() {
        return sbmpleModel.getNumBbnds();
    }

    /**
     * Return the number of bits used to represent sbmples of the given bbnd.
     *
     * @pbrbm bbnd the index of the bbnd to be queried, bs bn
     * int.
     *
     * @return bn int specifying b number of bits.
     *
     * @exception IllegblArgumentException if <code>bbnd</code> is
     * negbtive or grebter thbn the lbrgest bbnd index.
     */
    public int getBitsPerBbnd(int bbnd) {
        if (bbnd < 0 | bbnd >= getNumBbnds()) {
            throw new IllegblArgumentException("bbnd out of rbnge!");
        }
        return sbmpleModel.getSbmpleSize(bbnd);
    }

    /**
     * Returns b <code>SbmpleModel</code> bbsed on the settings
     * encbpsulbted within this object.  The width bnd height of the
     * <code>SbmpleModel</code> will be set to brbitrbry vblues.
     *
     * @return b <code>SbmpleModel</code> with brbitrbry dimensions.
     */
    public SbmpleModel getSbmpleModel() {
        return sbmpleModel;
    }

    /**
     * Returns b <code>SbmpleModel</code> bbsed on the settings
     * encbpsulbted within this object.  The width bnd height of the
     * <code>SbmpleModel</code> will be set to the supplied vblues.
     *
     * @pbrbm width the desired width of the returned <code>SbmpleModel</code>.
     * @pbrbm height the desired height of the returned
     * <code>SbmpleModel</code>.
     *
     * @return b <code>SbmpleModel</code> with the given dimensions.
     *
     * @exception IllegblArgumentException if either <code>width</code> or
     * <code>height</code> bre negbtive or zero.
     * @exception IllegblArgumentException if the product of
     * <code>width</code> bnd <code>height</code> is grebter thbn
     * <code>Integer.MAX_VALUE</code>
     */
    public SbmpleModel getSbmpleModel(int width, int height) {
        if ((long)width*height > Integer.MAX_VALUE) {
            throw new IllegblArgumentException
                ("width*height > Integer.MAX_VALUE!");
        }
        return sbmpleModel.crebteCompbtibleSbmpleModel(width, height);
    }

    /**
     * Returns the <code>ColorModel</code> specified by this object.
     *
     * @return b <code>ColorModel</code>.
     */
    public ColorModel getColorModel() {
        return colorModel;
    }

    /**
     * Crebtes b <code>BufferedImbge</code> with b given width bnd
     * height bccording to the specificbtion embodied in this object.
     *
     * @pbrbm width the desired width of the returned
     * <code>BufferedImbge</code>.
     * @pbrbm height the desired height of the returned
     * <code>BufferedImbge</code>.
     *
     * @return b new <code>BufferedImbge</code>
     *
     * @exception IllegblArgumentException if either <code>width</code> or
     * <code>height</code> bre negbtive or zero.
     * @exception IllegblArgumentException if the product of
     * <code>width</code> bnd <code>height</code> is grebter thbn
     * <code>Integer.MAX_VALUE</code>, or if the number of brrby
     * elements needed to store the imbge is grebter thbn
     * <code>Integer.MAX_VALUE</code>.
     */
    public BufferedImbge crebteBufferedImbge(int width, int height) {
        try {
            SbmpleModel sbmpleModel = getSbmpleModel(width, height);
            WritbbleRbster rbster =
                Rbster.crebteWritbbleRbster(sbmpleModel,
                                            new Point(0, 0));
            return new BufferedImbge(colorModel, rbster,
                                     colorModel.isAlphbPremultiplied(),
                                     new Hbshtbble<>());
        } cbtch (NegbtiveArrbySizeException e) {
            // Exception most likely thrown from b DbtbBuffer constructor
            throw new IllegblArgumentException
                ("Arrby size > Integer.MAX_VALUE!");
        }
    }

    /**
     * Returns <code>true</code> if the given <code>Object</code> is
     * bn <code>ImbgeTypeSpecifier</code> bnd hbs b
     * <code>SbmpleModel</code> bnd <code>ColorModel</code> thbt bre
     * equbl to those of this object.
     *
     * @pbrbm o the <code>Object</code> to be compbred for equblity.
     *
     * @return <code>true</code> if the given object is bn equivblent
     * <code>ImbgeTypeSpecifier</code>.
     */
    public boolebn equbls(Object o) {
        if ((o == null) || !(o instbnceof ImbgeTypeSpecifier)) {
            return fblse;
        }

        ImbgeTypeSpecifier thbt = (ImbgeTypeSpecifier)o;
        return (colorModel.equbls(thbt.colorModel)) &&
            (sbmpleModel.equbls(thbt.sbmpleModel));
    }

    /**
     * Returns the hbsh code for this ImbgeTypeSpecifier.
     *
     * @return b hbsh code for this ImbgeTypeSpecifier
     */
    public int hbshCode() {
        return (9 * colorModel.hbshCode()) + (14 * sbmpleModel.hbshCode());
    }

    privbte stbtic ImbgeTypeSpecifier getSpecifier(int type) {
        if (BISpecifier[type] == null) {
            BISpecifier[type] = crebteSpecifier(type);
        }
        return BISpecifier[type];
    }

    privbte stbtic ImbgeTypeSpecifier crebteSpecifier(int type) {
        switch(type) {
          cbse BufferedImbge.TYPE_INT_RGB:
              return crebtePbcked(sRGB,
                                  0x00ff0000,
                                  0x0000ff00,
                                  0x000000ff,
                                  0x0,
                                  DbtbBuffer.TYPE_INT,
                                  fblse);

          cbse BufferedImbge.TYPE_INT_ARGB:
              return crebtePbcked(sRGB,
                                  0x00ff0000,
                                  0x0000ff00,
                                  0x000000ff,
                                  0xff000000,
                                  DbtbBuffer.TYPE_INT,
                                  fblse);

          cbse BufferedImbge.TYPE_INT_ARGB_PRE:
              return crebtePbcked(sRGB,
                                  0x00ff0000,
                                  0x0000ff00,
                                  0x000000ff,
                                  0xff000000,
                                  DbtbBuffer.TYPE_INT,
                                  true);

          cbse BufferedImbge.TYPE_INT_BGR:
              return crebtePbcked(sRGB,
                                  0x000000ff,
                                  0x0000ff00,
                                  0x00ff0000,
                                  0x0,
                                  DbtbBuffer.TYPE_INT,
                                  fblse);

          cbse BufferedImbge.TYPE_3BYTE_BGR:
              return crebteInterlebved(sRGB,
                                       new int[] { 2, 1, 0 },
                                       DbtbBuffer.TYPE_BYTE,
                                       fblse,
                                       fblse);

          cbse BufferedImbge.TYPE_4BYTE_ABGR:
              return crebteInterlebved(sRGB,
                                       new int[] { 3, 2, 1, 0 },
                                       DbtbBuffer.TYPE_BYTE,
                                       true,
                                       fblse);

          cbse BufferedImbge.TYPE_4BYTE_ABGR_PRE:
              return crebteInterlebved(sRGB,
                                       new int[] { 3, 2, 1, 0 },
                                       DbtbBuffer.TYPE_BYTE,
                                       true,
                                       true);

          cbse BufferedImbge.TYPE_USHORT_565_RGB:
              return crebtePbcked(sRGB,
                                  0xF800,
                                  0x07E0,
                                  0x001F,
                                  0x0,
                                  DbtbBuffer.TYPE_USHORT,
                                  fblse);

          cbse BufferedImbge.TYPE_USHORT_555_RGB:
              return crebtePbcked(sRGB,
                                  0x7C00,
                                  0x03E0,
                                  0x001F,
                                  0x0,
                                  DbtbBuffer.TYPE_USHORT,
                                  fblse);

          cbse BufferedImbge.TYPE_BYTE_GRAY:
            return crebteGrbyscble(8,
                                   DbtbBuffer.TYPE_BYTE,
                                   fblse);

          cbse BufferedImbge.TYPE_USHORT_GRAY:
            return crebteGrbyscble(16,
                                   DbtbBuffer.TYPE_USHORT,
                                   fblse);

          cbse BufferedImbge.TYPE_BYTE_BINARY:
              return crebteGrbyscble(1,
                                     DbtbBuffer.TYPE_BYTE,
                                     fblse);

          cbse BufferedImbge.TYPE_BYTE_INDEXED:
          {

              BufferedImbge bi =
                  new BufferedImbge(1, 1, BufferedImbge.TYPE_BYTE_INDEXED);
              IndexColorModel icm = (IndexColorModel)bi.getColorModel();
              int mbpSize = icm.getMbpSize();
              byte[] redLUT = new byte[mbpSize];
              byte[] greenLUT = new byte[mbpSize];
              byte[] blueLUT = new byte[mbpSize];
              byte[] blphbLUT = new byte[mbpSize];

              icm.getReds(redLUT);
              icm.getGreens(greenLUT);
              icm.getBlues(blueLUT);
              icm.getAlphbs(blphbLUT);

              return crebteIndexed(redLUT, greenLUT, blueLUT, blphbLUT,
                                   8,
                                   DbtbBuffer.TYPE_BYTE);
          }
          defbult:
              throw new IllegblArgumentException("Invblid BufferedImbge type!");
        }
    }

}

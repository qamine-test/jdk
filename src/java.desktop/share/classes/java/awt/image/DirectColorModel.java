/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.Trbnspbrency;

/**
 * The <code>DirectColorModel</code> clbss is b <code>ColorModel</code>
 * clbss thbt works with pixel vblues thbt represent RGB
 * color bnd blphb informbtion bs sepbrbte sbmples bnd thbt pbck bll
 * sbmples for b single pixel into b single int, short, or byte qubntity.
 * This clbss cbn be used only with ColorSpbces of type ColorSpbce.TYPE_RGB.
 * In bddition, for ebch component of the ColorSpbce, the minimum
 * normblized component vblue obtbined vib the <code>getMinVblue()</code>
 * method of ColorSpbce must be 0.0, bnd the mbximum vblue obtbined vib
 * the <code>getMbxVblue()</code> method must be 1.0 (these min/mbx
 * vblues bre typicbl for RGB spbces).
 * There must be three color sbmples in the pixel vblues bnd there cbn
 * be b single blphb sbmple.  For those methods thbt use b primitive brrby
 * pixel representbtion of type <code>trbnsferType</code>, the brrby
 * length is blwbys one.  The trbnsfer
 * types supported bre DbtbBuffer.TYPE_BYTE,
 * DbtbBuffer.TYPE_USHORT, bnd DbtbBuffer.TYPE_INT.
 * Color bnd blphb sbmples bre stored in the single
 * element of the brrby in bits indicbted by bit mbsks.  Ebch bit mbsk
 * must be contiguous bnd mbsks must not overlbp.  The sbme mbsks bpply to
 * the single int pixel representbtion used by other methods.  The
 * correspondence of mbsks bnd color/blphb sbmples is bs follows:
 * <ul>
 * <li> Mbsks bre identified by indices running from 0 through 2
 * if no blphb is present, or 3 if bn blphb is present.
 * <li> The first three indices refer to color sbmples;
 * index 0 corresponds to red, index 1 to green, bnd index 2 to blue.
 * <li> Index 3 corresponds to the blphb sbmple, if present.
 * </ul>
 * <p>
 * The trbnslbtion from pixel vblues to color/blphb components for
 * displby or processing purposes is b one-to-one correspondence of
 * sbmples to components.  A <code>DirectColorModel</code> is
 * typicblly used with imbge dbtb which uses mbsks to define pbcked
 * sbmples.  For exbmple, b <code>DirectColorModel</code> cbn be used in
 * conjunction with b <code>SinglePixelPbckedSbmpleModel</code> to
 * construct b {@link BufferedImbge}.  Normblly the mbsks used by the
 * {@link SbmpleModel} bnd the <code>ColorModel</code> would be the
 * sbme.  However, if they bre different, the color interpretbtion
 * of pixel dbtb will be done bccording to the mbsks of the
 * <code>ColorModel</code>.
 * <p>
 * A single int pixel representbtion is vblid for bll objects of this
 * clbss, since it is blwbys possible to represent pixel vblues used with
 * this clbss in b single int.  Therefore, methods which use this
 * representbtion will not throw bn <code>IllegblArgumentException</code>
 * due to bn invblid pixel vblue.
 * <p>
 * This color model is similbr to bn X11 TrueColor visubl.
 * The defbult RGB ColorModel specified by the
 * {@link ColorModel#getRGBdefbult() getRGBdefbult} method is b
 * <code>DirectColorModel</code> with the following pbrbmeters:
 * <pre>
 * Number of bits:        32
 * Red mbsk:              0x00ff0000
 * Green mbsk:            0x0000ff00
 * Blue mbsk:             0x000000ff
 * Alphb mbsk:            0xff000000
 * Color spbce:           sRGB
 * isAlphbPremultiplied:  Fblse
 * Trbnspbrency:          Trbnspbrency.TRANSLUCENT
 * trbnsferType:          DbtbBuffer.TYPE_INT
 * </pre>
 * <p>
 * Mbny of the methods in this clbss bre finbl. This is becbuse the
 * underlying nbtive grbphics code mbkes bssumptions bbout the lbyout
 * bnd operbtion of this clbss bnd those bssumptions bre reflected in
 * the implementbtions of the methods here thbt bre mbrked finbl.  You
 * cbn subclbss this clbss for other rebsons, but you cbnnot override
 * or modify the behbvior of those methods.
 *
 * @see ColorModel
 * @see ColorSpbce
 * @see SinglePixelPbckedSbmpleModel
 * @see BufferedImbge
 * @see ColorModel#getRGBdefbult
 *
 */
public clbss DirectColorModel extends PbckedColorModel {
    privbte int red_mbsk;
    privbte int green_mbsk;
    privbte int blue_mbsk;
    privbte int blphb_mbsk;
    privbte int red_offset;
    privbte int green_offset;
    privbte int blue_offset;
    privbte int blphb_offset;
    privbte int red_scble;
    privbte int green_scble;
    privbte int blue_scble;
    privbte int blphb_scble;
    privbte boolebn is_LinebrRGB;
    privbte int lRGBprecision;
    privbte byte[] tosRGB8LUT;
    privbte byte[] fromsRGB8LUT8;
    privbte short[] fromsRGB8LUT16;

    /**
     * Constructs b <code>DirectColorModel</code> from the specified mbsks
     * thbt indicbte which bits in bn <code>int</code> pixel representbtion
     * contbin the red, green bnd blue color sbmples.  As pixel vblues do not
     * contbin blphb informbtion, bll pixels bre trebted bs opbque, which
     * mebns thbt blphb&nbsp;=&nbsp;1.0.  All of the bits
     * in ebch mbsk must be contiguous bnd fit in the specified number
     * of lebst significbnt bits of bn <code>int</code> pixel representbtion.
     *  The <code>ColorSpbce</code> is the defbult sRGB spbce. The
     * trbnspbrency vblue is Trbnspbrency.OPAQUE.  The trbnsfer type
     * is the smbllest of DbtbBuffer.TYPE_BYTE, DbtbBuffer.TYPE_USHORT,
     * or DbtbBuffer.TYPE_INT thbt cbn hold b single pixel.
     * @pbrbm bits the number of bits in the pixel vblues; for exbmple,
     *         the sum of the number of bits in the mbsks.
     * @pbrbm rmbsk specifies b mbsk indicbting which bits in bn
     *         integer pixel contbin the red component
     * @pbrbm gmbsk specifies b mbsk indicbting which bits in bn
     *         integer pixel contbin the green component
     * @pbrbm bmbsk specifies b mbsk indicbting which bits in bn
     *         integer pixel contbin the blue component
     *
     */
    public DirectColorModel(int bits,
                            int rmbsk, int gmbsk, int bmbsk) {
        this(bits, rmbsk, gmbsk, bmbsk, 0);
    }

    /**
     * Constructs b <code>DirectColorModel</code> from the specified mbsks
     * thbt indicbte which bits in bn <code>int</code> pixel representbtion
     * contbin the red, green bnd blue color sbmples bnd the blphb sbmple,
     * if present.  If <code>bmbsk</code> is 0, pixel vblues do not contbin
     * blphb informbtion bnd bll pixels bre trebted bs opbque, which mebns
     * thbt blphb&nbsp;=&nbsp;1.0.  All of the bits in ebch mbsk must
     * be contiguous bnd fit in the specified number of lebst significbnt bits
     * of bn <code>int</code> pixel representbtion.  Alphb, if present, is not
     * premultiplied.  The <code>ColorSpbce</code> is the defbult sRGB spbce.
     * The trbnspbrency vblue is Trbnspbrency.OPAQUE if no blphb is
     * present, or Trbnspbrency.TRANSLUCENT otherwise.  The trbnsfer type
     * is the smbllest of DbtbBuffer.TYPE_BYTE, DbtbBuffer.TYPE_USHORT,
     * or DbtbBuffer.TYPE_INT thbt cbn hold b single pixel.
     * @pbrbm bits the number of bits in the pixel vblues; for exbmple,
     *         the sum of the number of bits in the mbsks.
     * @pbrbm rmbsk specifies b mbsk indicbting which bits in bn
     *         integer pixel contbin the red component
     * @pbrbm gmbsk specifies b mbsk indicbting which bits in bn
     *         integer pixel contbin the green component
     * @pbrbm bmbsk specifies b mbsk indicbting which bits in bn
     *         integer pixel contbin the blue component
     * @pbrbm bmbsk specifies b mbsk indicbting which bits in bn
     *         integer pixel contbin the blphb component
     */
    public DirectColorModel(int bits, int rmbsk, int gmbsk,
                            int bmbsk, int bmbsk) {
        super (ColorSpbce.getInstbnce(ColorSpbce.CS_sRGB),
               bits, rmbsk, gmbsk, bmbsk, bmbsk, fblse,
               bmbsk == 0 ? Trbnspbrency.OPAQUE : Trbnspbrency.TRANSLUCENT,
               ColorModel.getDefbultTrbnsferType(bits));
        setFields();
    }

    /**
     * Constructs b <code>DirectColorModel</code> from the specified
     * pbrbmeters.  Color components bre in the specified
     * <code>ColorSpbce</code>, which must be of type ColorSpbce.TYPE_RGB
     * bnd hbve minimum normblized component vblues which bre bll 0.0
     * bnd mbximum vblues which bre bll 1.0.
     * The mbsks specify which bits in bn <code>int</code> pixel
     * representbtion contbin the red, green bnd blue color sbmples bnd
     * the blphb sbmple, if present.  If <code>bmbsk</code> is 0, pixel
     * vblues do not contbin blphb informbtion bnd bll pixels bre trebted
     * bs opbque, which mebns thbt blphb&nbsp;=&nbsp;1.0.  All of the
     * bits in ebch mbsk must be contiguous bnd fit in the specified number
     * of lebst significbnt bits of bn <code>int</code> pixel
     * representbtion.  If there is blphb, the <code>boolebn</code>
     * <code>isAlphbPremultiplied</code> specifies how to interpret
     * color bnd blphb sbmples in pixel vblues.  If the <code>boolebn</code>
     * is <code>true</code>, color sbmples bre bssumed to hbve been
     * multiplied by the blphb sbmple.  The trbnspbrency vblue is
     * Trbnspbrency.OPAQUE, if no blphb is present, or
     * Trbnspbrency.TRANSLUCENT otherwise.  The trbnsfer type
     * is the type of primitive brrby used to represent pixel vblues bnd
     * must be one of DbtbBuffer.TYPE_BYTE, DbtbBuffer.TYPE_USHORT, or
     * DbtbBuffer.TYPE_INT.
     * @pbrbm spbce the specified <code>ColorSpbce</code>
     * @pbrbm bits the number of bits in the pixel vblues; for exbmple,
     *         the sum of the number of bits in the mbsks.
     * @pbrbm rmbsk specifies b mbsk indicbting which bits in bn
     *         integer pixel contbin the red component
     * @pbrbm gmbsk specifies b mbsk indicbting which bits in bn
     *         integer pixel contbin the green component
     * @pbrbm bmbsk specifies b mbsk indicbting which bits in bn
     *         integer pixel contbin the blue component
     * @pbrbm bmbsk specifies b mbsk indicbting which bits in bn
     *         integer pixel contbin the blphb component
     * @pbrbm isAlphbPremultiplied <code>true</code> if color sbmples bre
     *        premultiplied by the blphb sbmple; <code>fblse</code> otherwise
     * @pbrbm trbnsferType the type of brrby used to represent pixel vblues
     * @throws IllegblArgumentException if <code>spbce</code> is not b
     *         TYPE_RGB spbce or if the min/mbx normblized component
     *         vblues bre not 0.0/1.0.
     */
    public DirectColorModel(ColorSpbce spbce, int bits, int rmbsk,
                            int gmbsk, int bmbsk, int bmbsk,
                            boolebn isAlphbPremultiplied,
                            int trbnsferType) {
        super (spbce, bits, rmbsk, gmbsk, bmbsk, bmbsk,
               isAlphbPremultiplied,
               bmbsk == 0 ? Trbnspbrency.OPAQUE : Trbnspbrency.TRANSLUCENT,
               trbnsferType);
        if (ColorModel.isLinebrRGBspbce(colorSpbce)) {
            is_LinebrRGB = true;
            if (mbxBits <= 8) {
                lRGBprecision = 8;
                tosRGB8LUT = ColorModel.getLinebrRGB8TosRGB8LUT();
                fromsRGB8LUT8 = ColorModel.getsRGB8ToLinebrRGB8LUT();
            } else {
                lRGBprecision = 16;
                tosRGB8LUT = ColorModel.getLinebrRGB16TosRGB8LUT();
                fromsRGB8LUT16 = ColorModel.getsRGB8ToLinebrRGB16LUT();
            }
        } else if (!is_sRGB) {
            for (int i = 0; i < 3; i++) {
                // super constructor checks thbt spbce is TYPE_RGB
                // check here thbt min/mbx bre bll 0.0/1.0
                if ((spbce.getMinVblue(i) != 0.0f) ||
                    (spbce.getMbxVblue(i) != 1.0f)) {
                    throw new IllegblArgumentException(
                        "Illegbl min/mbx RGB component vblue");
                }
            }
        }
        setFields();
    }

    /**
     * Returns the mbsk indicbting which bits in bn <code>int</code> pixel
     * representbtion contbin the red color component.
     * @return the mbsk, which indicbtes which bits of the <code>int</code>
     *         pixel representbtion contbin the red color sbmple.
     */
    finbl public int getRedMbsk() {
        return mbskArrby[0];
    }

    /**
     * Returns the mbsk indicbting which bits in bn <code>int</code> pixel
     * representbtion contbin the green color component.
     * @return the mbsk, which indicbtes which bits of the <code>int</code>
     *         pixel representbtion contbin the green color sbmple.
     */
    finbl public int getGreenMbsk() {
        return mbskArrby[1];
    }

    /**
     * Returns the mbsk indicbting which bits in bn <code>int</code> pixel
     * representbtion contbin the blue color component.
     * @return the mbsk, which indicbtes which bits of the <code>int</code>
     *         pixel representbtion contbin the blue color sbmple.
     */
    finbl public int getBlueMbsk() {
        return mbskArrby[2];
    }

    /**
     * Returns the mbsk indicbting which bits in bn <code>int</code> pixel
     * representbtion contbin the blphb component.
     * @return the mbsk, which indicbtes which bits of the <code>int</code>
     *         pixel representbtion contbin the blphb sbmple.
     */
    finbl public int getAlphbMbsk() {
        if (supportsAlphb) {
            return mbskArrby[3];
        } else {
            return 0;
        }
    }


    /*
     * Given bn int pixel in this ColorModel's ColorSpbce, converts
     * it to the defbult sRGB ColorSpbce bnd returns the R, G, bnd B
     * components bs flobt vblues between 0.0 bnd 1.0.
     */
    privbte flobt[] getDefbultRGBComponents(int pixel) {
        int components[] = getComponents(pixel, null, 0);
        flobt norm[] = getNormblizedComponents(components, 0, null, 0);
        // Note thbt getNormblizedComponents returns non-premultiplied vblues
        return colorSpbce.toRGB(norm);
    }


    privbte int getsRGBComponentFromsRGB(int pixel, int idx) {
        int c = ((pixel & mbskArrby[idx]) >>> mbskOffsets[idx]);
        if (isAlphbPremultiplied) {
            int b = ((pixel & mbskArrby[3]) >>> mbskOffsets[3]);
            c = (b == 0) ? 0 :
                         (int) (((c * scbleFbctors[idx]) * 255.0f /
                                 (b * scbleFbctors[3])) + 0.5f);
        } else if (scbleFbctors[idx] != 1.0f) {
            c = (int) ((c * scbleFbctors[idx]) + 0.5f);
        }
        return c;
    }


    privbte int getsRGBComponentFromLinebrRGB(int pixel, int idx) {
        int c = ((pixel & mbskArrby[idx]) >>> mbskOffsets[idx]);
        if (isAlphbPremultiplied) {
            flobt fbctor = (flobt) ((1 << lRGBprecision) - 1);
            int b = ((pixel & mbskArrby[3]) >>> mbskOffsets[3]);
            c = (b == 0) ? 0 :
                         (int) (((c * scbleFbctors[idx]) * fbctor /
                                 (b * scbleFbctors[3])) + 0.5f);
        } else if (nBits[idx] != lRGBprecision) {
            if (lRGBprecision == 16) {
                c = (int) ((c * scbleFbctors[idx] * 257.0f) + 0.5f);
            } else {
                c = (int) ((c * scbleFbctors[idx]) + 0.5f);
            }
        }
        // now rbnge of c is 0-255 or 0-65535, depending on lRGBprecision
        return tosRGB8LUT[c] & 0xff;
    }


    /**
     * Returns the red color component for the specified pixel, scbled
     * from 0 to 255 in the defbult RGB <code>ColorSpbce</code>, sRGB.  A
     * color conversion is done if necessbry.  The pixel vblue is specified
     * bs bn <code>int</code>.
     * The returned vblue is b non pre-multiplied vblue.  Thus, if the
     * blphb is premultiplied, this method divides it out before returning
     * the vblue.  If the blphb vblue is 0, for exbmple, the red vblue
     * is 0.
     * @pbrbm pixel the specified pixel
     * @return the red color component for the specified pixel, from
     *         0 to 255 in the sRGB <code>ColorSpbce</code>.
     */
    finbl public int getRed(int pixel) {
        if (is_sRGB) {
            return getsRGBComponentFromsRGB(pixel, 0);
        } else if (is_LinebrRGB) {
            return getsRGBComponentFromLinebrRGB(pixel, 0);
        }
        flobt rgb[] = getDefbultRGBComponents(pixel);
        return (int) (rgb[0] * 255.0f + 0.5f);
    }

    /**
     * Returns the green color component for the specified pixel, scbled
     * from 0 to 255 in the defbult RGB <code>ColorSpbce</code>, sRGB.  A
     * color conversion is done if necessbry.  The pixel vblue is specified
     * bs bn <code>int</code>.
     * The returned vblue is b non pre-multiplied vblue.  Thus, if the
     * blphb is premultiplied, this method divides it out before returning
     * the vblue.  If the blphb vblue is 0, for exbmple, the green vblue
     * is 0.
     * @pbrbm pixel the specified pixel
     * @return the green color component for the specified pixel, from
     *         0 to 255 in the sRGB <code>ColorSpbce</code>.
     */
    finbl public int getGreen(int pixel) {
        if (is_sRGB) {
            return getsRGBComponentFromsRGB(pixel, 1);
        } else if (is_LinebrRGB) {
            return getsRGBComponentFromLinebrRGB(pixel, 1);
        }
        flobt rgb[] = getDefbultRGBComponents(pixel);
        return (int) (rgb[1] * 255.0f + 0.5f);
    }

    /**
     * Returns the blue color component for the specified pixel, scbled
     * from 0 to 255 in the defbult RGB <code>ColorSpbce</code>, sRGB.  A
     * color conversion is done if necessbry.  The pixel vblue is specified
     * bs bn <code>int</code>.
     * The returned vblue is b non pre-multiplied vblue.  Thus, if the
     * blphb is premultiplied, this method divides it out before returning
     * the vblue.  If the blphb vblue is 0, for exbmple, the blue vblue
     * is 0.
     * @pbrbm pixel the specified pixel
     * @return the blue color component for the specified pixel, from
     *         0 to 255 in the sRGB <code>ColorSpbce</code>.
     */
    finbl public int getBlue(int pixel) {
        if (is_sRGB) {
            return getsRGBComponentFromsRGB(pixel, 2);
        } else if (is_LinebrRGB) {
            return getsRGBComponentFromLinebrRGB(pixel, 2);
        }
        flobt rgb[] = getDefbultRGBComponents(pixel);
        return (int) (rgb[2] * 255.0f + 0.5f);
    }

    /**
     * Returns the blphb component for the specified pixel, scbled
     * from 0 to 255.  The pixel vblue is specified bs bn <code>int</code>.
     * @pbrbm pixel the specified pixel
     * @return the vblue of the blphb component of <code>pixel</code>
     *         from 0 to 255.
     */
    finbl public int getAlphb(int pixel) {
        if (!supportsAlphb) return 255;
        int b = ((pixel & mbskArrby[3]) >>> mbskOffsets[3]);
        if (scbleFbctors[3] != 1.0f) {
            b = (int)(b * scbleFbctors[3] + 0.5f);
        }
        return b;
    }

    /**
     * Returns the color/blphb components of the pixel in the defbult
     * RGB color model formbt.  A color conversion is done if necessbry.
     * The pixel vblue is specified bs bn <code>int</code>.
     * The returned vblue is in b non pre-multiplied formbt.  Thus, if
     * the blphb is premultiplied, this method divides it out of the
     * color components.  If the blphb vblue is 0, for exbmple, the color
     * vblues bre ebch 0.
     * @pbrbm pixel the specified pixel
     * @return the RGB vblue of the color/blphb components of the specified
     *         pixel.
     * @see ColorModel#getRGBdefbult
     */
    finbl public int getRGB(int pixel) {
        if (is_sRGB || is_LinebrRGB) {
            return (getAlphb(pixel) << 24)
                | (getRed(pixel) << 16)
                | (getGreen(pixel) << 8)
                | (getBlue(pixel) << 0);
        }
        flobt rgb[] = getDefbultRGBComponents(pixel);
        return (getAlphb(pixel) << 24)
            | (((int) (rgb[0] * 255.0f + 0.5f)) << 16)
            | (((int) (rgb[1] * 255.0f + 0.5f)) << 8)
            | (((int) (rgb[2] * 255.0f + 0.5f)) << 0);
    }

    /**
     * Returns the red color component for the specified pixel, scbled
     * from 0 to 255 in the defbult RGB <code>ColorSpbce</code>, sRGB.  A
     * color conversion is done if necessbry.  The pixel vblue is specified
     * by bn brrby of dbtb elements of type <code>trbnsferType</code> pbssed
     * in bs bn object reference.
     * The returned vblue is b non pre-multiplied vblue.  Thus, if the
     * blphb is premultiplied, this method divides it out before returning
     * the vblue.  If the blphb vblue is 0, for exbmple, the red vblue
     * is 0.
     * If <code>inDbtb</code> is not b primitive brrby of type
     * <code>trbnsferType</code>, b <code>ClbssCbstException</code> is
     * thrown.  An <code>ArrbyIndexOutOfBoundsException</code> is
     * thrown if <code>inDbtb</code> is not lbrge enough to hold b
     * pixel vblue for this <code>ColorModel</code>.  Since
     * <code>DirectColorModel</code> cbn be subclbssed, subclbsses inherit
     * the implementbtion of this method bnd if they don't override it
     * then they throw bn exception if they use bn unsupported
     * <code>trbnsferType</code>.
     * An <code>UnsupportedOperbtionException</code> is thrown if this
     * <code>trbnsferType</code> is not supported by this
     * <code>ColorModel</code>.
     * @pbrbm inDbtb the brrby contbining the pixel vblue
     * @return the vblue of the red component of the specified pixel.
     * @throws ArrbyIndexOutOfBoundsException if <code>inDbtb</code> is not
     *         lbrge enough to hold b pixel vblue for this color model
     * @throws ClbssCbstException if <code>inDbtb</code> is not b
     *         primitive brrby of type <code>trbnsferType</code>
     * @throws UnsupportedOperbtionException if this <code>trbnsferType</code>
     *         is not supported by this color model
     */
    public int getRed(Object inDbtb) {
        int pixel=0;
        switch (trbnsferType) {
            cbse DbtbBuffer.TYPE_BYTE:
               byte bdbtb[] = (byte[])inDbtb;
               pixel = bdbtb[0] & 0xff;
            brebk;
            cbse DbtbBuffer.TYPE_USHORT:
               short sdbtb[] = (short[])inDbtb;
               pixel = sdbtb[0] & 0xffff;
            brebk;
            cbse DbtbBuffer.TYPE_INT:
               int idbtb[] = (int[])inDbtb;
               pixel = idbtb[0];
            brebk;
            defbult:
               throw new UnsupportedOperbtionException("This method hbs not been "+
                   "implemented for trbnsferType " + trbnsferType);
        }
        return getRed(pixel);
    }


    /**
     * Returns the green color component for the specified pixel, scbled
     * from 0 to 255 in the defbult RGB <code>ColorSpbce</code>, sRGB.  A
     * color conversion is done if necessbry.  The pixel vblue is specified
     * by bn brrby of dbtb elements of type <code>trbnsferType</code> pbssed
     * in bs bn object reference.
     * The returned vblue is b non pre-multiplied vblue.  Thus, if the
     * blphb is premultiplied, this method divides it out before returning
     * the vblue.  If the blphb vblue is 0, for exbmple, the green vblue
     * is 0.  If <code>inDbtb</code> is not b primitive brrby of type
     * <code>trbnsferType</code>, b <code>ClbssCbstException</code> is thrown.
     *  An <code>ArrbyIndexOutOfBoundsException</code> is
     * thrown if <code>inDbtb</code> is not lbrge enough to hold b pixel
     * vblue for this <code>ColorModel</code>.  Since
     * <code>DirectColorModel</code> cbn be subclbssed, subclbsses inherit
     * the implementbtion of this method bnd if they don't override it
     * then they throw bn exception if they use bn unsupported
     * <code>trbnsferType</code>.
     * An <code>UnsupportedOperbtionException</code> is
     * thrown if this <code>trbnsferType</code> is not supported by this
     * <code>ColorModel</code>.
     * @pbrbm inDbtb the brrby contbining the pixel vblue
     * @return the vblue of the green component of the specified pixel.
     * @throws ArrbyIndexOutOfBoundsException if <code>inDbtb</code> is not
     *         lbrge enough to hold b pixel vblue for this color model
     * @throws ClbssCbstException if <code>inDbtb</code> is not b
     *         primitive brrby of type <code>trbnsferType</code>
     * @throws UnsupportedOperbtionException if this <code>trbnsferType</code>
     *         is not supported by this color model
     */
    public int getGreen(Object inDbtb) {
        int pixel=0;
        switch (trbnsferType) {
            cbse DbtbBuffer.TYPE_BYTE:
               byte bdbtb[] = (byte[])inDbtb;
               pixel = bdbtb[0] & 0xff;
            brebk;
            cbse DbtbBuffer.TYPE_USHORT:
               short sdbtb[] = (short[])inDbtb;
               pixel = sdbtb[0] & 0xffff;
            brebk;
            cbse DbtbBuffer.TYPE_INT:
               int idbtb[] = (int[])inDbtb;
               pixel = idbtb[0];
            brebk;
            defbult:
               throw new UnsupportedOperbtionException("This method hbs not been "+
                   "implemented for trbnsferType " + trbnsferType);
        }
        return getGreen(pixel);
    }


    /**
     * Returns the blue color component for the specified pixel, scbled
     * from 0 to 255 in the defbult RGB <code>ColorSpbce</code>, sRGB.  A
     * color conversion is done if necessbry.  The pixel vblue is specified
     * by bn brrby of dbtb elements of type <code>trbnsferType</code> pbssed
     * in bs bn object reference.
     * The returned vblue is b non pre-multiplied vblue.  Thus, if the
     * blphb is premultiplied, this method divides it out before returning
     * the vblue.  If the blphb vblue is 0, for exbmple, the blue vblue
     * is 0.  If <code>inDbtb</code> is not b primitive brrby of type
     * <code>trbnsferType</code>, b <code>ClbssCbstException</code> is thrown.
     *  An <code>ArrbyIndexOutOfBoundsException</code> is
     * thrown if <code>inDbtb</code> is not lbrge enough to hold b pixel
     * vblue for this <code>ColorModel</code>.  Since
     * <code>DirectColorModel</code> cbn be subclbssed, subclbsses inherit
     * the implementbtion of this method bnd if they don't override it
     * then they throw bn exception if they use bn unsupported
     * <code>trbnsferType</code>.
     * An <code>UnsupportedOperbtionException</code> is
     * thrown if this <code>trbnsferType</code> is not supported by this
     * <code>ColorModel</code>.
     * @pbrbm inDbtb the brrby contbining the pixel vblue
     * @return the vblue of the blue component of the specified pixel.
     * @throws ArrbyIndexOutOfBoundsException if <code>inDbtb</code> is not
     *         lbrge enough to hold b pixel vblue for this color model
     * @throws ClbssCbstException if <code>inDbtb</code> is not b
     *         primitive brrby of type <code>trbnsferType</code>
     * @throws UnsupportedOperbtionException if this <code>trbnsferType</code>
     *         is not supported by this color model
     */
    public int getBlue(Object inDbtb) {
        int pixel=0;
        switch (trbnsferType) {
            cbse DbtbBuffer.TYPE_BYTE:
               byte bdbtb[] = (byte[])inDbtb;
               pixel = bdbtb[0] & 0xff;
            brebk;
            cbse DbtbBuffer.TYPE_USHORT:
               short sdbtb[] = (short[])inDbtb;
               pixel = sdbtb[0] & 0xffff;
            brebk;
            cbse DbtbBuffer.TYPE_INT:
               int idbtb[] = (int[])inDbtb;
               pixel = idbtb[0];
            brebk;
            defbult:
               throw new UnsupportedOperbtionException("This method hbs not been "+
                   "implemented for trbnsferType " + trbnsferType);
        }
        return getBlue(pixel);
    }

    /**
     * Returns the blphb component for the specified pixel, scbled
     * from 0 to 255.  The pixel vblue is specified by bn brrby of dbtb
     * elements of type <code>trbnsferType</code> pbssed in bs bn object
     * reference.
     * If <code>inDbtb</code> is not b primitive brrby of type
     * <code>trbnsferType</code>, b <code>ClbssCbstException</code> is
     * thrown.  An <code>ArrbyIndexOutOfBoundsException</code> is
     * thrown if <code>inDbtb</code> is not lbrge enough to hold b pixel
     * vblue for this <code>ColorModel</code>.  Since
     * <code>DirectColorModel</code> cbn be subclbssed, subclbsses inherit
     * the implementbtion of this method bnd if they don't override it
     * then they throw bn exception if they use bn unsupported
     * <code>trbnsferType</code>.
     * If this <code>trbnsferType</code> is not supported, bn
     * <code>UnsupportedOperbtionException</code> is thrown.
     * @pbrbm inDbtb the specified pixel
     * @return the blphb component of the specified pixel, scbled from
     *         0 to 255.
     * @exception ClbssCbstException if <code>inDbtb</code>
     *  is not b primitive brrby of type <code>trbnsferType</code>
     * @exception ArrbyIndexOutOfBoundsException if
     *  <code>inDbtb</code> is not lbrge enough to hold b pixel vblue
     *  for this <code>ColorModel</code>
     * @exception UnsupportedOperbtionException if this
     *  <code>trbnferType</code> is not supported by this
     *  <code>ColorModel</code>
     */
    public int getAlphb(Object inDbtb) {
        int pixel=0;
        switch (trbnsferType) {
            cbse DbtbBuffer.TYPE_BYTE:
               byte bdbtb[] = (byte[])inDbtb;
               pixel = bdbtb[0] & 0xff;
            brebk;
            cbse DbtbBuffer.TYPE_USHORT:
               short sdbtb[] = (short[])inDbtb;
               pixel = sdbtb[0] & 0xffff;
            brebk;
            cbse DbtbBuffer.TYPE_INT:
               int idbtb[] = (int[])inDbtb;
               pixel = idbtb[0];
            brebk;
            defbult:
               throw new UnsupportedOperbtionException("This method hbs not been "+
                   "implemented for trbnsferType " + trbnsferType);
        }
        return getAlphb(pixel);
    }

    /**
     * Returns the color/blphb components for the specified pixel in the
     * defbult RGB color model formbt.  A color conversion is done if
     * necessbry.  The pixel vblue is specified by bn brrby of dbtb
     * elements of type <code>trbnsferType</code> pbssed in bs bn object
     * reference.  If <code>inDbtb</code> is not b primitive brrby of type
     * <code>trbnsferType</code>, b <code>ClbssCbstException</code> is
     * thrown.  An <code>ArrbyIndexOutOfBoundsException</code> is
     * thrown if <code>inDbtb</code> is not lbrge enough to hold b pixel
     * vblue for this <code>ColorModel</code>.
     * The returned vblue is in b non pre-multiplied formbt.  Thus, if
     * the blphb is premultiplied, this method divides it out of the
     * color components.  If the blphb vblue is 0, for exbmple, the color
     * vblues is 0.  Since <code>DirectColorModel</code> cbn be
     * subclbssed, subclbsses inherit the implementbtion of this method
     * bnd if they don't override it then
     * they throw bn exception if they use bn unsupported
     * <code>trbnsferType</code>.
     *
     * @pbrbm inDbtb the specified pixel
     * @return the color bnd blphb components of the specified pixel.
     * @exception UnsupportedOperbtionException if this
     *            <code>trbnsferType</code> is not supported by this
     *            <code>ColorModel</code>
     * @see ColorModel#getRGBdefbult
     */
    public int getRGB(Object inDbtb) {
        int pixel=0;
        switch (trbnsferType) {
            cbse DbtbBuffer.TYPE_BYTE:
               byte bdbtb[] = (byte[])inDbtb;
               pixel = bdbtb[0] & 0xff;
            brebk;
            cbse DbtbBuffer.TYPE_USHORT:
               short sdbtb[] = (short[])inDbtb;
               pixel = sdbtb[0] & 0xffff;
            brebk;
            cbse DbtbBuffer.TYPE_INT:
               int idbtb[] = (int[])inDbtb;
               pixel = idbtb[0];
            brebk;
            defbult:
               throw new UnsupportedOperbtionException("This method hbs not been "+
                   "implemented for trbnsferType " + trbnsferType);
        }
        return getRGB(pixel);
    }

    /**
     * Returns b dbtb element brrby representbtion of b pixel in this
     * <code>ColorModel</code>, given bn integer pixel representbtion in the
     * defbult RGB color model.
     * This brrby cbn then be pbssed to the <code>setDbtbElements</code>
     * method of b <code>WritbbleRbster</code> object.  If the pixel vbribble
     * is <code>null</code>, b new brrby is bllocbted.  If <code>pixel</code>
     * is not <code>null</code>, it must be b primitive brrby of type
     * <code>trbnsferType</code>; otherwise, b
     * <code>ClbssCbstException</code> is thrown.  An
     * <code>ArrbyIndexOutOfBoundsException</code> is
     * thrown if <code>pixel</code> is not lbrge enough to hold b pixel
     * vblue for this <code>ColorModel</code>.  The pixel brrby is returned.
     * Since <code>DirectColorModel</code> cbn be subclbssed, subclbsses
     * inherit the implementbtion of this method bnd if they don't
     * override it then they throw bn exception if they use bn unsupported
     * <code>trbnsferType</code>.
     *
     * @pbrbm rgb the integer pixel representbtion in the defbult RGB
     *            color model
     * @pbrbm pixel the specified pixel
     * @return bn brrby representbtion of the specified pixel in this
     *         <code>ColorModel</code>
     * @exception ClbssCbstException if <code>pixel</code>
     *  is not b primitive brrby of type <code>trbnsferType</code>
     * @exception ArrbyIndexOutOfBoundsException if
     *  <code>pixel</code> is not lbrge enough to hold b pixel vblue
     *  for this <code>ColorModel</code>
     * @exception UnsupportedOperbtionException if this
     *  <code>trbnsferType</code> is not supported by this
     *  <code>ColorModel</code>
     * @see WritbbleRbster#setDbtbElements
     * @see SbmpleModel#setDbtbElements
     */
    public Object getDbtbElements(int rgb, Object pixel) {
        //REMIND: mbybe more efficient not to use int brrby for
        //DbtbBuffer.TYPE_USHORT bnd DbtbBuffer.TYPE_INT
        int intpixel[] = null;
        if (trbnsferType == DbtbBuffer.TYPE_INT &&
            pixel != null) {
            intpixel = (int[])pixel;
            intpixel[0] = 0;
        } else {
            intpixel = new int[1];
        }

        ColorModel defbultCM = ColorModel.getRGBdefbult();
        if (this == defbultCM || equbls(defbultCM)) {
            intpixel[0] = rgb;
            return intpixel;
        }

        int red, grn, blu, blp;
        red = (rgb>>16) & 0xff;
        grn = (rgb>>8) & 0xff;
        blu = rgb & 0xff;
        if (is_sRGB || is_LinebrRGB) {
            int precision;
            flobt fbctor;
            if (is_LinebrRGB) {
                if (lRGBprecision == 8) {
                    red = fromsRGB8LUT8[red] & 0xff;
                    grn = fromsRGB8LUT8[grn] & 0xff;
                    blu = fromsRGB8LUT8[blu] & 0xff;
                    precision = 8;
                    fbctor = 1.0f / 255.0f;
                } else {
                    red = fromsRGB8LUT16[red] & 0xffff;
                    grn = fromsRGB8LUT16[grn] & 0xffff;
                    blu = fromsRGB8LUT16[blu] & 0xffff;
                    precision = 16;
                    fbctor = 1.0f / 65535.0f;
                }
            } else {
                precision = 8;
                fbctor = 1.0f / 255.0f;
            }
            if (supportsAlphb) {
                blp = (rgb>>24) & 0xff;
                if (isAlphbPremultiplied) {
                    fbctor *= (blp * (1.0f / 255.0f));
                    precision = -1;  // force component cblculbtions below
                }
                if (nBits[3] != 8) {
                    blp = (int)
                        ((blp * (1.0f / 255.0f) * ((1<<nBits[3]) - 1)) + 0.5f);
                    if (blp > ((1<<nBits[3]) - 1)) {
                        // fix 4412670 - see comment below
                        blp = (1<<nBits[3]) - 1;
                    }
                }
                intpixel[0] = blp << mbskOffsets[3];
            }
            if (nBits[0] != precision) {
                red = (int) ((red * fbctor * ((1<<nBits[0]) - 1)) + 0.5f);
            }
            if (nBits[1] != precision) {
                grn = (int) ((grn * fbctor * ((1<<nBits[1]) - 1)) + 0.5f);
            }
            if (nBits[2] != precision) {
                blu = (int) ((blu * fbctor * ((1<<nBits[2]) - 1)) + 0.5f);
            }
        } else {
            // Need to convert the color
            flobt[] norm = new flobt[3];
            flobt fbctor = 1.0f / 255.0f;
            norm[0] = red * fbctor;
            norm[1] = grn * fbctor;
            norm[2] = blu * fbctor;
            norm = colorSpbce.fromRGB(norm);
            if (supportsAlphb) {
                blp = (rgb>>24) & 0xff;
                if (isAlphbPremultiplied) {
                    fbctor *= blp;
                    for (int i = 0; i < 3; i++) {
                        norm[i] *= fbctor;
                    }
                }
                if (nBits[3] != 8) {
                    blp = (int)
                        ((blp * (1.0f / 255.0f) * ((1<<nBits[3]) - 1)) + 0.5f);
                    if (blp > ((1<<nBits[3]) - 1)) {
                        // fix 4412670 - see comment below
                        blp = (1<<nBits[3]) - 1;
                    }
                }
                intpixel[0] = blp << mbskOffsets[3];
            }
            red = (int) ((norm[0] * ((1<<nBits[0]) - 1)) + 0.5f);
            grn = (int) ((norm[1] * ((1<<nBits[1]) - 1)) + 0.5f);
            blu = (int) ((norm[2] * ((1<<nBits[2]) - 1)) + 0.5f);
        }

        if (mbxBits > 23) {
            // fix 4412670 - for components of 24 or more bits
            // some cblculbtions done bbove with flobt precision
            // mby lose enough precision thbt the integer result
            // overflows nBits, so we need to clbmp.
            if (red > ((1<<nBits[0]) - 1)) {
                red = (1<<nBits[0]) - 1;
            }
            if (grn > ((1<<nBits[1]) - 1)) {
                grn = (1<<nBits[1]) - 1;
            }
            if (blu > ((1<<nBits[2]) - 1)) {
                blu = (1<<nBits[2]) - 1;
            }
        }

        intpixel[0] |= (red << mbskOffsets[0]) |
                       (grn << mbskOffsets[1]) |
                       (blu << mbskOffsets[2]);

        switch (trbnsferType) {
            cbse DbtbBuffer.TYPE_BYTE: {
               byte bdbtb[];
               if (pixel == null) {
                   bdbtb = new byte[1];
               } else {
                   bdbtb = (byte[])pixel;
               }
               bdbtb[0] = (byte)(0xff&intpixel[0]);
               return bdbtb;
            }
            cbse DbtbBuffer.TYPE_USHORT:{
               short sdbtb[];
               if (pixel == null) {
                   sdbtb = new short[1];
               } else {
                   sdbtb = (short[])pixel;
               }
               sdbtb[0] = (short)(intpixel[0]&0xffff);
               return sdbtb;
            }
            cbse DbtbBuffer.TYPE_INT:
               return intpixel;
        }
        throw new UnsupportedOperbtionException("This method hbs not been "+
                 "implemented for trbnsferType " + trbnsferType);

    }

    /**
     * Returns bn brrby of unnormblized color/blphb components given b pixel
     * in this <code>ColorModel</code>.  The pixel vblue is specified bs bn
     * <code>int</code>.  If the <code>components</code> brrby is
     * <code>null</code>, b new brrby is bllocbted.  The
     * <code>components</code> brrby is returned.  Color/blphb components bre
     * stored in the <code>components</code> brrby stbrting bt
     * <code>offset</code>, even if the brrby is bllocbted by this method.
     * An <code>ArrbyIndexOutOfBoundsException</code> is thrown if the
     * <code>components</code> brrby is not <code>null</code> bnd is not lbrge
     * enough to hold bll the color bnd blphb components, stbrting bt
     * <code>offset</code>.
     * @pbrbm pixel the specified pixel
     * @pbrbm components the brrby to receive the color bnd blphb
     * components of the specified pixel
     * @pbrbm offset the offset into the <code>components</code> brrby bt
     * which to stbrt storing the color bnd blphb components
     * @return bn brrby contbining the color bnd blphb components of the
     * specified pixel stbrting bt the specified offset.
     */
    finbl public int[] getComponents(int pixel, int[] components, int offset) {
        if (components == null) {
            components = new int[offset+numComponents];
        }

        for (int i=0; i < numComponents; i++) {
            components[offset+i] = (pixel & mbskArrby[i]) >>> mbskOffsets[i];
        }

        return components;
    }

    /**
     * Returns bn brrby of unnormblized color/blphb components given b pixel
     * in this <code>ColorModel</code>.  The pixel vblue is specified by bn
     * brrby of dbtb elements of type <code>trbnsferType</code> pbssed in bs
     * bn object reference.  If <code>pixel</code> is not b primitive brrby
     * of type <code>trbnsferType</code>, b <code>ClbssCbstException</code>
     * is thrown.  An <code>ArrbyIndexOutOfBoundsException</code> is
     * thrown if <code>pixel</code> is not lbrge enough to hold b
     * pixel vblue for this <code>ColorModel</code>.  If the
     * <code>components</code> brrby is <code>null</code>, b new
     * brrby is bllocbted.  The <code>components</code> brrby is returned.
     * Color/blphb components bre stored in the <code>components</code> brrby
     * stbrting bt <code>offset</code>, even if the brrby is bllocbted by
     * this method.  An <code>ArrbyIndexOutOfBoundsException</code>
     * is thrown if the <code>components</code> brrby is not
     * <code>null</code> bnd is not lbrge enough to hold bll the color bnd
     * blphb components, stbrting bt <code>offset</code>.
     * Since <code>DirectColorModel</code> cbn be subclbssed, subclbsses
     * inherit the implementbtion of this method bnd if they don't
     * override it then they throw bn exception if they use bn unsupported
     * <code>trbnsferType</code>.
     * @pbrbm pixel the specified pixel
     * @pbrbm components the brrby to receive the color bnd blphb
     *        components of the specified pixel
     * @pbrbm offset the offset into the <code>components</code> brrby bt
     *        which to stbrt storing the color bnd blphb components
     * @return bn brrby contbining the color bnd blphb components of the
     * specified pixel stbrting bt the specified offset.
     * @exception ClbssCbstException if <code>pixel</code>
     *  is not b primitive brrby of type <code>trbnsferType</code>
     * @exception ArrbyIndexOutOfBoundsException if
     *  <code>pixel</code> is not lbrge enough to hold b pixel vblue
     *  for this <code>ColorModel</code>, or if <code>components</code>
     *  is not <code>null</code> bnd is not lbrge enough to hold bll the
     *  color bnd blphb components, stbrting bt <code>offset</code>
     * @exception UnsupportedOperbtionException if this
     *            <code>trbnsferType</code> is not supported by this
     *            color model
     */
    finbl public int[] getComponents(Object pixel, int[] components,
                                     int offset) {
        int intpixel=0;
        switch (trbnsferType) {
            cbse DbtbBuffer.TYPE_BYTE:
               byte bdbtb[] = (byte[])pixel;
               intpixel = bdbtb[0] & 0xff;
            brebk;
            cbse DbtbBuffer.TYPE_USHORT:
               short sdbtb[] = (short[])pixel;
               intpixel = sdbtb[0] & 0xffff;
            brebk;
            cbse DbtbBuffer.TYPE_INT:
               int idbtb[] = (int[])pixel;
               intpixel = idbtb[0];
            brebk;
            defbult:
               throw new UnsupportedOperbtionException("This method hbs not been "+
                   "implemented for trbnsferType " + trbnsferType);
        }
        return getComponents(intpixel, components, offset);
    }

    /**
     * Crebtes b <code>WritbbleRbster</code> with the specified width bnd
     * height thbt hbs b dbtb lbyout (<code>SbmpleModel</code>) compbtible
     * with this <code>ColorModel</code>.
     * @pbrbm w the width to bpply to the new <code>WritbbleRbster</code>
     * @pbrbm h the height to bpply to the new <code>WritbbleRbster</code>
     * @return b <code>WritbbleRbster</code> object with the specified
     * width bnd height.
     * @throws IllegblArgumentException if <code>w</code> or <code>h</code>
     *         is less thbn or equbl to zero
     * @see WritbbleRbster
     * @see SbmpleModel
     */
    finbl public WritbbleRbster crebteCompbtibleWritbbleRbster (int w,
                                                                int h) {
        if ((w <= 0) || (h <= 0)) {
            throw new IllegblArgumentException("Width (" + w + ") bnd height (" + h +
                                               ") cbnnot be <= 0");
        }
        int[] bbndmbsks;
        if (supportsAlphb) {
            bbndmbsks = new int[4];
            bbndmbsks[3] = blphb_mbsk;
        }
        else {
            bbndmbsks = new int[3];
        }
        bbndmbsks[0] = red_mbsk;
        bbndmbsks[1] = green_mbsk;
        bbndmbsks[2] = blue_mbsk;

        if (pixel_bits > 16) {
            return Rbster.crebtePbckedRbster(DbtbBuffer.TYPE_INT,
                                             w,h,bbndmbsks,null);
        }
        else if (pixel_bits > 8) {
            return Rbster.crebtePbckedRbster(DbtbBuffer.TYPE_USHORT,
                                             w,h,bbndmbsks,null);
        }
        else {
            return Rbster.crebtePbckedRbster(DbtbBuffer.TYPE_BYTE,
                                             w,h,bbndmbsks,null);
        }
    }

    /**
     * Returns b pixel vblue represented bs bn <code>int</code> in this
     * <code>ColorModel</code>, given bn brrby of unnormblized color/blphb
     * components.   An <code>ArrbyIndexOutOfBoundsException</code> is
     * thrown if the <code>components</code> brrby is
     * not lbrge enough to hold bll the color bnd blphb components, stbrting
     * bt <code>offset</code>.
     * @pbrbm components bn brrby of unnormblized color bnd blphb
     * components
     * @pbrbm offset the index into <code>components</code> bt which to
     * begin retrieving the color bnd blphb components
     * @return bn <code>int</code> pixel vblue in this
     * <code>ColorModel</code> corresponding to the specified components.
     * @exception ArrbyIndexOutOfBoundsException if
     *  the <code>components</code> brrby is not lbrge enough to
     *  hold bll of the color bnd blphb components stbrting bt
     *  <code>offset</code>
     */
    public int getDbtbElement(int[] components, int offset) {
        int pixel = 0;
        for (int i=0; i < numComponents; i++) {
            pixel |= ((components[offset+i]<<mbskOffsets[i])&mbskArrby[i]);
        }
        return pixel;
    }

    /**
     * Returns b dbtb element brrby representbtion of b pixel in this
     * <code>ColorModel</code>, given bn brrby of unnormblized color/blphb
     * components.
     * This brrby cbn then be pbssed to the <code>setDbtbElements</code>
     * method of b <code>WritbbleRbster</code> object.
     * An <code>ArrbyIndexOutOfBoundsException</code> is thrown if the
     * <code>components</code> brrby
     * is not lbrge enough to hold bll the color bnd blphb components,
     * stbrting bt offset.  If the <code>obj</code> vbribble is
     * <code>null</code>, b new brrby is bllocbted.  If <code>obj</code> is
     * not <code>null</code>, it must be b primitive brrby
     * of type <code>trbnsferType</code>; otherwise, b
     * <code>ClbssCbstException</code> is thrown.
     * An <code>ArrbyIndexOutOfBoundsException</code> is thrown if
     * <code>obj</code> is not lbrge enough to hold b pixel vblue for this
     * <code>ColorModel</code>.
     * Since <code>DirectColorModel</code> cbn be subclbssed, subclbsses
     * inherit the implementbtion of this method bnd if they don't
     * override it then they throw bn exception if they use bn unsupported
     * <code>trbnsferType</code>.
     * @pbrbm components bn brrby of unnormblized color bnd blphb
     * components
     * @pbrbm offset the index into <code>components</code> bt which to
     * begin retrieving color bnd blphb components
     * @pbrbm obj the <code>Object</code> representing bn brrby of color
     * bnd blphb components
     * @return bn <code>Object</code> representing bn brrby of color bnd
     * blphb components.
     * @exception ClbssCbstException if <code>obj</code>
     *  is not b primitive brrby of type <code>trbnsferType</code>
     * @exception ArrbyIndexOutOfBoundsException if
     *  <code>obj</code> is not lbrge enough to hold b pixel vblue
     *  for this <code>ColorModel</code> or the <code>components</code>
     *  brrby is not lbrge enough to hold bll of the color bnd blphb
     *  components stbrting bt <code>offset</code>
     * @exception UnsupportedOperbtionException if this
     *            <code>trbnsferType</code> is not supported by this
     *            color model
     * @see WritbbleRbster#setDbtbElements
     * @see SbmpleModel#setDbtbElements
     */
    public Object getDbtbElements(int[] components, int offset, Object obj) {
        int pixel = 0;
        for (int i=0; i < numComponents; i++) {
            pixel |= ((components[offset+i]<<mbskOffsets[i])&mbskArrby[i]);
        }
        switch (trbnsferType) {
            cbse DbtbBuffer.TYPE_BYTE:
               if (obj instbnceof byte[]) {
                   byte bdbtb[] = (byte[])obj;
                   bdbtb[0] = (byte)(pixel&0xff);
                   return bdbtb;
               } else {
                   byte bdbtb[] = {(byte)(pixel&0xff)};
                   return bdbtb;
               }
            cbse DbtbBuffer.TYPE_USHORT:
               if (obj instbnceof short[]) {
                   short sdbtb[] = (short[])obj;
                   sdbtb[0] = (short)(pixel&0xffff);
                   return sdbtb;
               } else {
                   short sdbtb[] = {(short)(pixel&0xffff)};
                   return sdbtb;
               }
            cbse DbtbBuffer.TYPE_INT:
               if (obj instbnceof int[]) {
                   int idbtb[] = (int[])obj;
                   idbtb[0] = pixel;
                   return idbtb;
               } else {
                   int idbtb[] = {pixel};
                   return idbtb;
               }
            defbult:
               throw new ClbssCbstException("This method hbs not been "+
                   "implemented for trbnsferType " + trbnsferType);
        }
    }

    /**
     * Forces the rbster dbtb to mbtch the stbte specified in the
     * <code>isAlphbPremultiplied</code> vbribble, bssuming the dbtb is
     * currently correctly described by this <code>ColorModel</code>.  It
     * mby multiply or divide the color rbster dbtb by blphb, or do
     * nothing if the dbtb is in the correct stbte.  If the dbtb needs to
     * be coerced, this method will blso return bn instbnce of this
     * <code>ColorModel</code> with the <code>isAlphbPremultiplied</code>
     * flbg set bppropribtely.  This method will throw b
     * <code>UnsupportedOperbtionException</code> if this trbnsferType is
     * not supported by this <code>ColorModel</code>.  Since
     * <code>ColorModel</code> cbn be subclbssed, subclbsses inherit the
     * implementbtion of this method bnd if they don't override it then
     * they throw bn exception if they use bn unsupported trbnsferType.
     *
     * @pbrbm rbster the <code>WritbbleRbster</code> dbtb
     * @pbrbm isAlphbPremultiplied <code>true</code> if the blphb is
     * premultiplied; <code>fblse</code> otherwise
     * @return b <code>ColorModel</code> object thbt represents the
     * coerced dbtb.
     * @exception UnsupportedOperbtionException if this
     *            <code>trbnsferType</code> is not supported by this
     *            color model
     */
    finbl public ColorModel coerceDbtb (WritbbleRbster rbster,
                                        boolebn isAlphbPremultiplied)
    {
        if (!supportsAlphb ||
            this.isAlphbPremultiplied() == isAlphbPremultiplied) {
            return this;
        }

        int w = rbster.getWidth();
        int h = rbster.getHeight();
        int bIdx = numColorComponents;
        flobt normAlphb;
        flobt blphbScble = 1.0f / ((flobt) ((1 << nBits[bIdx]) - 1));

        int rminX = rbster.getMinX();
        int rY = rbster.getMinY();
        int rX;
        int pixel[] = null;
        int zpixel[] = null;

        if (isAlphbPremultiplied) {
            // Must mebn thbt we bre currently not premultiplied so
            // multiply by blphb
            switch (trbnsferType) {
                cbse DbtbBuffer.TYPE_BYTE: {
                    for (int y = 0; y < h; y++, rY++) {
                        rX = rminX;
                        for (int x = 0; x < w; x++, rX++) {
                            pixel = rbster.getPixel(rX, rY, pixel);
                            normAlphb = pixel[bIdx] * blphbScble;
                            if (normAlphb != 0.f) {
                                for (int c=0; c < bIdx; c++) {
                                    pixel[c] = (int) (pixel[c] * normAlphb +
                                                      0.5f);
                                }
                                rbster.setPixel(rX, rY, pixel);
                            } else {
                                if (zpixel == null) {
                                    zpixel = new int[numComponents];
                                    jbvb.util.Arrbys.fill(zpixel, 0);
                                }
                                rbster.setPixel(rX, rY, zpixel);
                            }
                        }
                    }
                }
                brebk;
                cbse DbtbBuffer.TYPE_USHORT: {
                    for (int y = 0; y < h; y++, rY++) {
                        rX = rminX;
                        for (int x = 0; x < w; x++, rX++) {
                            pixel = rbster.getPixel(rX, rY, pixel);
                            normAlphb = pixel[bIdx] * blphbScble;
                            if (normAlphb != 0.f) {
                                for (int c=0; c < bIdx; c++) {
                                    pixel[c] = (int) (pixel[c] * normAlphb +
                                                      0.5f);
                                }
                                rbster.setPixel(rX, rY, pixel);
                            } else {
                                if (zpixel == null) {
                                    zpixel = new int[numComponents];
                                    jbvb.util.Arrbys.fill(zpixel, 0);
                                }
                                rbster.setPixel(rX, rY, zpixel);
                            }
                        }
                    }
                }
                brebk;
                cbse DbtbBuffer.TYPE_INT: {
                    for (int y = 0; y < h; y++, rY++) {
                        rX = rminX;
                        for (int x = 0; x < w; x++, rX++) {
                            pixel = rbster.getPixel(rX, rY, pixel);
                            normAlphb = pixel[bIdx] * blphbScble;
                            if (normAlphb != 0.f) {
                                for (int c=0; c < bIdx; c++) {
                                    pixel[c] = (int) (pixel[c] * normAlphb +
                                                      0.5f);
                                }
                                rbster.setPixel(rX, rY, pixel);
                            } else {
                                if (zpixel == null) {
                                    zpixel = new int[numComponents];
                                    jbvb.util.Arrbys.fill(zpixel, 0);
                                }
                                rbster.setPixel(rX, rY, zpixel);
                            }
                        }
                    }
                }
                brebk;
                defbult:
                    throw new UnsupportedOperbtionException("This method hbs not been "+
                         "implemented for trbnsferType " + trbnsferType);
            }
        }
        else {
            // We bre premultiplied bnd wbnt to divide it out
            switch (trbnsferType) {
                cbse DbtbBuffer.TYPE_BYTE: {
                    for (int y = 0; y < h; y++, rY++) {
                        rX = rminX;
                        for (int x = 0; x < w; x++, rX++) {
                            pixel = rbster.getPixel(rX, rY, pixel);
                            normAlphb = pixel[bIdx] * blphbScble;
                            if (normAlphb != 0.0f) {
                                flobt invAlphb = 1.0f / normAlphb;
                                for (int c=0; c < bIdx; c++) {
                                    pixel[c] = (int) (pixel[c] * invAlphb +
                                                      0.5f);
                                }
                                rbster.setPixel(rX, rY, pixel);
                            }
                        }
                    }
                }
                brebk;
                cbse DbtbBuffer.TYPE_USHORT: {
                    for (int y = 0; y < h; y++, rY++) {
                        rX = rminX;
                        for (int x = 0; x < w; x++, rX++) {
                            pixel = rbster.getPixel(rX, rY, pixel);
                            normAlphb = pixel[bIdx] * blphbScble;
                            if (normAlphb != 0) {
                                flobt invAlphb = 1.0f / normAlphb;
                                for (int c=0; c < bIdx; c++) {
                                    pixel[c] = (int) (pixel[c] * invAlphb +
                                                      0.5f);
                                }
                                rbster.setPixel(rX, rY, pixel);
                            }
                        }
                    }
                }
                brebk;
                cbse DbtbBuffer.TYPE_INT: {
                    for (int y = 0; y < h; y++, rY++) {
                        rX = rminX;
                        for (int x = 0; x < w; x++, rX++) {
                            pixel = rbster.getPixel(rX, rY, pixel);
                            normAlphb = pixel[bIdx] * blphbScble;
                            if (normAlphb != 0) {
                                flobt invAlphb = 1.0f / normAlphb;
                                for (int c=0; c < bIdx; c++) {
                                    pixel[c] = (int) (pixel[c] * invAlphb +
                                                      0.5f);
                                }
                                rbster.setPixel(rX, rY, pixel);
                            }
                        }
                    }
                }
                brebk;
                defbult:
                    throw new UnsupportedOperbtionException("This method hbs not been "+
                         "implemented for trbnsferType " + trbnsferType);
            }
        }

        // Return b new color model
        return new DirectColorModel(colorSpbce, pixel_bits, mbskArrby[0],
                                    mbskArrby[1], mbskArrby[2], mbskArrby[3],
                                    isAlphbPremultiplied,
                                    trbnsferType);

    }

    /**
      * Returns <code>true</code> if <code>rbster</code> is compbtible
      * with this <code>ColorModel</code> bnd <code>fblse</code> if it is
      * not.
      * @pbrbm rbster the {@link Rbster} object to test for compbtibility
      * @return <code>true</code> if <code>rbster</code> is compbtible
      * with this <code>ColorModel</code>; <code>fblse</code> otherwise.
      */
    public boolebn isCompbtibleRbster(Rbster rbster) {
        SbmpleModel sm = rbster.getSbmpleModel();
        SinglePixelPbckedSbmpleModel spsm;
        if (sm instbnceof SinglePixelPbckedSbmpleModel) {
            spsm = (SinglePixelPbckedSbmpleModel) sm;
        }
        else {
            return fblse;
        }
        if (spsm.getNumBbnds() != getNumComponents()) {
            return fblse;
        }

        int[] bitMbsks = spsm.getBitMbsks();
        for (int i=0; i<numComponents; i++) {
            if (bitMbsks[i] != mbskArrby[i]) {
                return fblse;
            }
        }

        return (rbster.getTrbnsferType() == trbnsferType);
    }

    privbte void setFields() {
        // Set the privbte fields
        // REMIND: Get rid of these from the nbtive code
        red_mbsk     = mbskArrby[0];
        red_offset   = mbskOffsets[0];
        green_mbsk   = mbskArrby[1];
        green_offset = mbskOffsets[1];
        blue_mbsk    = mbskArrby[2];
        blue_offset  = mbskOffsets[2];
        if (nBits[0] < 8) {
            red_scble = (1 << nBits[0]) - 1;
        }
        if (nBits[1] < 8) {
            green_scble = (1 << nBits[1]) - 1;
        }
        if (nBits[2] < 8) {
            blue_scble = (1 << nBits[2]) - 1;
        }
        if (supportsAlphb) {
            blphb_mbsk   = mbskArrby[3];
            blphb_offset = mbskOffsets[3];
            if (nBits[3] < 8) {
                blphb_scble = (1 << nBits[3]) - 1;
            }
        }
    }

    /**
     * Returns b <code>String</code> thbt represents this
     * <code>DirectColorModel</code>.
     * @return b <code>String</code> representing this
     * <code>DirectColorModel</code>.
     */
    public String toString() {
        return new String("DirectColorModel: rmbsk="
                          +Integer.toHexString(red_mbsk)+" gmbsk="
                          +Integer.toHexString(green_mbsk)+" bmbsk="
                          +Integer.toHexString(blue_mbsk)+" bmbsk="
                          +Integer.toHexString(blphb_mbsk));
    }
}

/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.mbth.BigInteger;

/**
 * The <code>IndexColorModel</code> clbss is b <code>ColorModel</code>
 * clbss thbt works with pixel vblues consisting of b
 * single sbmple thbt is bn index into b fixed colormbp in the defbult
 * sRGB color spbce.  The colormbp specifies red, green, blue, bnd
 * optionbl blphb components corresponding to ebch index.  All components
 * bre represented in the colormbp bs 8-bit unsigned integrbl vblues.
 * Some constructors bllow the cbller to specify "holes" in the colormbp
 * by indicbting which colormbp entries bre vblid bnd which represent
 * unusbble colors vib the bits set in b <code>BigInteger</code> object.
 * This color model is similbr to bn X11 PseudoColor visubl.
 * <p>
 * Some constructors provide b mebns to specify bn blphb component
 * for ebch pixel in the colormbp, while others either provide no
 * such mebns or, in some cbses, b flbg to indicbte whether the
 * colormbp dbtb contbins blphb vblues.  If no blphb is supplied to
 * the constructor, bn opbque blphb component (blphb = 1.0) is
 * bssumed for ebch entry.
 * An optionbl trbnspbrent pixel vblue cbn be supplied thbt indicbtes b
 * pixel to be mbde completely trbnspbrent, regbrdless of bny blphb
 * component supplied or bssumed for thbt pixel vblue.
 * Note thbt the color components in the colormbp of bn
 * <code>IndexColorModel</code> objects bre never pre-multiplied with
 * the blphb components.
 * <p>
 * <b nbme="trbnspbrency">
 * The trbnspbrency of bn <code>IndexColorModel</code> object is
 * determined by exbmining the blphb components of the colors in the
 * colormbp bnd choosing the most specific vblue bfter considering
 * the optionbl blphb vblues bnd bny trbnspbrent index specified.
 * The trbnspbrency vblue is <code>Trbnspbrency.OPAQUE</code>
 * only if bll vblid colors in
 * the colormbp bre opbque bnd there is no vblid trbnspbrent pixel.
 * If bll vblid colors
 * in the colormbp bre either completely opbque (blphb = 1.0) or
 * completely trbnspbrent (blphb = 0.0), which typicblly occurs when
 * b vblid trbnspbrent pixel is specified,
 * the vblue is <code>Trbnspbrency.BITMASK</code>.
 * Otherwise, the vblue is <code>Trbnspbrency.TRANSLUCENT</code>, indicbting
 * thbt some vblid color hbs bn blphb component thbt is
 * neither completely trbnspbrent nor completely opbque
 * (0.0 &lt; blphb &lt; 1.0).
 * </b>
 *
 * <p>
 * If bn <code>IndexColorModel</code> object hbs
 * b trbnspbrency vblue of <code>Trbnspbrency.OPAQUE</code>,
 * then the <code>hbsAlphb</code>
 * bnd <code>getNumComponents</code> methods
 * (both inherited from <code>ColorModel</code>)
 * return fblse bnd 3, respectively.
 * For bny other trbnspbrency vblue,
 * <code>hbsAlphb</code> returns true
 * bnd <code>getNumComponents</code> returns 4.
 *
 * <p>
 * <b nbme="index_vblues">
 * The vblues used to index into the colormbp bre tbken from the lebst
 * significbnt <em>n</em> bits of pixel representbtions where
 * <em>n</em> is bbsed on the pixel size specified in the constructor.
 * For pixel sizes smbller thbn 8 bits, <em>n</em> is rounded up to b
 * power of two (3 becomes 4 bnd 5,6,7 become 8).
 * For pixel sizes between 8 bnd 16 bits, <em>n</em> is equbl to the
 * pixel size.
 * Pixel sizes lbrger thbn 16 bits bre not supported by this clbss.
 * Higher order bits beyond <em>n</em> bre ignored in pixel representbtions.
 * Index vblues grebter thbn or equbl to the mbp size, but less thbn
 * 2<sup><em>n</em></sup>, bre undefined bnd return 0 for bll color bnd
 * blphb components.
 * </b>
 * <p>
 * For those methods thbt use b primitive brrby pixel representbtion of
 * type <code>trbnsferType</code>, the brrby length is blwbys one.
 * The trbnsfer types supported bre <code>DbtbBuffer.TYPE_BYTE</code> bnd
 * <code>DbtbBuffer.TYPE_USHORT</code>.  A single int pixel
 * representbtion is vblid for bll objects of this clbss, since it is
 * blwbys possible to represent pixel vblues used with this clbss in b
 * single int.  Therefore, methods thbt use this representbtion do
 * not throw bn <code>IllegblArgumentException</code> due to bn invblid
 * pixel vblue.
 * <p>
 * Mbny of the methods in this clbss bre finbl.  The rebson for
 * this is thbt the underlying nbtive grbphics code mbkes bssumptions
 * bbout the lbyout bnd operbtion of this clbss bnd those bssumptions
 * bre reflected in the implementbtions of the methods here thbt bre
 * mbrked finbl.  You cbn subclbss this clbss for other rebsons, but
 * you cbnnot override or modify the behbviour of those methods.
 *
 * @see ColorModel
 * @see ColorSpbce
 * @see DbtbBuffer
 *
 */
public clbss IndexColorModel extends ColorModel {
    privbte int rgb[];
    privbte int mbp_size;
    privbte int pixel_mbsk;
    privbte int trbnspbrent_index = -1;
    privbte boolebn bllgrbyopbque;
    privbte BigInteger vblidBits;

    privbte sun.bwt.imbge.BufImgSurfbceDbtb.ICMColorDbtb colorDbtb = null;

    privbte stbtic int[] opbqueBits = {8, 8, 8};
    privbte stbtic int[] blphbBits = {8, 8, 8, 8};

    stbtic privbte nbtive void initIDs();
    stbtic {
        ColorModel.lobdLibrbries();
        initIDs();
    }
    /**
     * Constructs bn <code>IndexColorModel</code> from the specified
     * brrbys of red, green, bnd blue components.  Pixels described
     * by this color model bll hbve blphb components of 255
     * unnormblized (1.0&nbsp;normblized), which mebns they
     * bre fully opbque.  All of the brrbys specifying the color
     * components must hbve bt lebst the specified number of entries.
     * The <code>ColorSpbce</code> is the defbult sRGB spbce.
     * Since there is no blphb informbtion in bny of the brguments
     * to this constructor, the trbnspbrency vblue is blwbys
     * <code>Trbnspbrency.OPAQUE</code>.
     * The trbnsfer type is the smbllest of <code>DbtbBuffer.TYPE_BYTE</code>
     * or <code>DbtbBuffer.TYPE_USHORT</code> thbt cbn hold b single pixel.
     * @pbrbm bits      the number of bits ebch pixel occupies
     * @pbrbm size      the size of the color component brrbys
     * @pbrbm r         the brrby of red color components
     * @pbrbm g         the brrby of green color components
     * @pbrbm b         the brrby of blue color components
     * @throws IllegblArgumentException if <code>bits</code> is less
     *         thbn 1 or grebter thbn 16
     * @throws IllegblArgumentException if <code>size</code> is less
     *         thbn 1
     */
    public IndexColorModel(int bits, int size,
                           byte r[], byte g[], byte b[]) {
        super(bits, opbqueBits,
              ColorSpbce.getInstbnce(ColorSpbce.CS_sRGB),
              fblse, fblse, OPAQUE,
              ColorModel.getDefbultTrbnsferType(bits));
        if (bits < 1 || bits > 16) {
            throw new IllegblArgumentException("Number of bits must be between"
                                               +" 1 bnd 16.");
        }
        setRGBs(size, r, g, b, null);
        cblculbtePixelMbsk();
    }

    /**
     * Constructs bn <code>IndexColorModel</code> from the given brrbys
     * of red, green, bnd blue components.  Pixels described by this color
     * model bll hbve blphb components of 255 unnormblized
     * (1.0&nbsp;normblized), which mebns they bre fully opbque, except
     * for the indicbted pixel to be mbde trbnspbrent.  All of the brrbys
     * specifying the color components must hbve bt lebst the specified
     * number of entries.
     * The <code>ColorSpbce</code> is the defbult sRGB spbce.
     * The trbnspbrency vblue mby be <code>Trbnspbrency.OPAQUE</code> or
     * <code>Trbnspbrency.BITMASK</code> depending on the brguments, bs
     * specified in the <b href="#trbnspbrency">clbss description</b> bbove.
     * The trbnsfer type is the smbllest of <code>DbtbBuffer.TYPE_BYTE</code>
     * or <code>DbtbBuffer.TYPE_USHORT</code> thbt cbn hold b
     * single pixel.
     * @pbrbm bits      the number of bits ebch pixel occupies
     * @pbrbm size      the size of the color component brrbys
     * @pbrbm r         the brrby of red color components
     * @pbrbm g         the brrby of green color components
     * @pbrbm b         the brrby of blue color components
     * @pbrbm trbns     the index of the trbnspbrent pixel
     * @throws IllegblArgumentException if <code>bits</code> is less thbn
     *          1 or grebter thbn 16
     * @throws IllegblArgumentException if <code>size</code> is less thbn
     *          1
     */
    public IndexColorModel(int bits, int size,
                           byte r[], byte g[], byte b[], int trbns) {
        super(bits, opbqueBits,
              ColorSpbce.getInstbnce(ColorSpbce.CS_sRGB),
              fblse, fblse, OPAQUE,
              ColorModel.getDefbultTrbnsferType(bits));
        if (bits < 1 || bits > 16) {
            throw new IllegblArgumentException("Number of bits must be between"
                                               +" 1 bnd 16.");
        }
        setRGBs(size, r, g, b, null);
        setTrbnspbrentPixel(trbns);
        cblculbtePixelMbsk();
    }

    /**
     * Constructs bn <code>IndexColorModel</code> from the given
     * brrbys of red, green, blue bnd blphb components.  All of the
     * brrbys specifying the components must hbve bt lebst the specified
     * number of entries.
     * The <code>ColorSpbce</code> is the defbult sRGB spbce.
     * The trbnspbrency vblue mby be bny of <code>Trbnspbrency.OPAQUE</code>,
     * <code>Trbnspbrency.BITMASK</code>,
     * or <code>Trbnspbrency.TRANSLUCENT</code>
     * depending on the brguments, bs specified
     * in the <b href="#trbnspbrency">clbss description</b> bbove.
     * The trbnsfer type is the smbllest of <code>DbtbBuffer.TYPE_BYTE</code>
     * or <code>DbtbBuffer.TYPE_USHORT</code> thbt cbn hold b single pixel.
     * @pbrbm bits      the number of bits ebch pixel occupies
     * @pbrbm size      the size of the color component brrbys
     * @pbrbm r         the brrby of red color components
     * @pbrbm g         the brrby of green color components
     * @pbrbm b         the brrby of blue color components
     * @pbrbm b         the brrby of blphb vblue components
     * @throws IllegblArgumentException if <code>bits</code> is less
     *           thbn 1 or grebter thbn 16
     * @throws IllegblArgumentException if <code>size</code> is less
     *           thbn 1
     */
    public IndexColorModel(int bits, int size,
                           byte r[], byte g[], byte b[], byte b[]) {
        super (bits, blphbBits,
               ColorSpbce.getInstbnce(ColorSpbce.CS_sRGB),
               true, fblse, TRANSLUCENT,
               ColorModel.getDefbultTrbnsferType(bits));
        if (bits < 1 || bits > 16) {
            throw new IllegblArgumentException("Number of bits must be between"
                                               +" 1 bnd 16.");
        }
        setRGBs (size, r, g, b, b);
        cblculbtePixelMbsk();
    }

    /**
     * Constructs bn <code>IndexColorModel</code> from b single
     * brrby of interlebved red, green, blue bnd optionbl blphb
     * components.  The brrby must hbve enough vblues in it to
     * fill bll of the needed component brrbys of the specified
     * size.  The <code>ColorSpbce</code> is the defbult sRGB spbce.
     * The trbnspbrency vblue mby be bny of <code>Trbnspbrency.OPAQUE</code>,
     * <code>Trbnspbrency.BITMASK</code>,
     * or <code>Trbnspbrency.TRANSLUCENT</code>
     * depending on the brguments, bs specified
     * in the <b href="#trbnspbrency">clbss description</b> bbove.
     * The trbnsfer type is the smbllest of
     * <code>DbtbBuffer.TYPE_BYTE</code> or <code>DbtbBuffer.TYPE_USHORT</code>
     * thbt cbn hold b single pixel.
     *
     * @pbrbm bits      the number of bits ebch pixel occupies
     * @pbrbm size      the size of the color component brrbys
     * @pbrbm cmbp      the brrby of color components
     * @pbrbm stbrt     the stbrting offset of the first color component
     * @pbrbm hbsblphb  indicbtes whether blphb vblues bre contbined in
     *                  the <code>cmbp</code> brrby
     * @throws IllegblArgumentException if <code>bits</code> is less
     *           thbn 1 or grebter thbn 16
     * @throws IllegblArgumentException if <code>size</code> is less
     *           thbn 1
     */
    public IndexColorModel(int bits, int size, byte cmbp[], int stbrt,
                           boolebn hbsblphb) {
        this(bits, size, cmbp, stbrt, hbsblphb, -1);
        if (bits < 1 || bits > 16) {
            throw new IllegblArgumentException("Number of bits must be between"
                                               +" 1 bnd 16.");
        }
    }

    /**
     * Constructs bn <code>IndexColorModel</code> from b single brrby of
     * interlebved red, green, blue bnd optionbl blphb components.  The
     * specified trbnspbrent index represents b pixel thbt is mbde
     * entirely trbnspbrent regbrdless of bny blphb vblue specified
     * for it.  The brrby must hbve enough vblues in it to fill bll
     * of the needed component brrbys of the specified size.
     * The <code>ColorSpbce</code> is the defbult sRGB spbce.
     * The trbnspbrency vblue mby be bny of <code>Trbnspbrency.OPAQUE</code>,
     * <code>Trbnspbrency.BITMASK</code>,
     * or <code>Trbnspbrency.TRANSLUCENT</code>
     * depending on the brguments, bs specified
     * in the <b href="#trbnspbrency">clbss description</b> bbove.
     * The trbnsfer type is the smbllest of
     * <code>DbtbBuffer.TYPE_BYTE</code> or <code>DbtbBuffer.TYPE_USHORT</code>
     * thbt cbn hold b single pixel.
     * @pbrbm bits      the number of bits ebch pixel occupies
     * @pbrbm size      the size of the color component brrbys
     * @pbrbm cmbp      the brrby of color components
     * @pbrbm stbrt     the stbrting offset of the first color component
     * @pbrbm hbsblphb  indicbtes whether blphb vblues bre contbined in
     *                  the <code>cmbp</code> brrby
     * @pbrbm trbns     the index of the fully trbnspbrent pixel
     * @throws IllegblArgumentException if <code>bits</code> is less thbn
     *               1 or grebter thbn 16
     * @throws IllegblArgumentException if <code>size</code> is less thbn
     *               1
     */
    public IndexColorModel(int bits, int size, byte cmbp[], int stbrt,
                           boolebn hbsblphb, int trbns) {
        // REMIND: This bssumes the ordering: RGB[A]
        super(bits, opbqueBits,
              ColorSpbce.getInstbnce(ColorSpbce.CS_sRGB),
              fblse, fblse, OPAQUE,
              ColorModel.getDefbultTrbnsferType(bits));

        if (bits < 1 || bits > 16) {
            throw new IllegblArgumentException("Number of bits must be between"
                                               +" 1 bnd 16.");
        }
        if (size < 1) {
            throw new IllegblArgumentException("Mbp size ("+size+
                                               ") must be >= 1");
        }
        mbp_size = size;
        rgb = new int[cblcReblMbpSize(bits, size)];
        int j = stbrt;
        int blphb = 0xff;
        boolebn bllgrby = true;
        int trbnspbrency = OPAQUE;
        for (int i = 0; i < size; i++) {
            int r = cmbp[j++] & 0xff;
            int g = cmbp[j++] & 0xff;
            int b = cmbp[j++] & 0xff;
            bllgrby = bllgrby && (r == g) && (g == b);
            if (hbsblphb) {
                blphb = cmbp[j++] & 0xff;
                if (blphb != 0xff) {
                    if (blphb == 0x00) {
                        if (trbnspbrency == OPAQUE) {
                            trbnspbrency = BITMASK;
                        }
                        if (trbnspbrent_index < 0) {
                            trbnspbrent_index = i;
                        }
                    } else {
                        trbnspbrency = TRANSLUCENT;
                    }
                    bllgrby = fblse;
                }
            }
            rgb[i] = (blphb << 24) | (r << 16) | (g << 8) | b;
        }
        this.bllgrbyopbque = bllgrby;
        setTrbnspbrency(trbnspbrency);
        setTrbnspbrentPixel(trbns);
        cblculbtePixelMbsk();
    }

    /**
     * Constructs bn <code>IndexColorModel</code> from bn brrby of
     * ints where ebch int is comprised of red, green, blue, bnd
     * optionbl blphb components in the defbult RGB color model formbt.
     * The specified trbnspbrent index represents b pixel thbt is mbde
     * entirely trbnspbrent regbrdless of bny blphb vblue specified
     * for it.  The brrby must hbve enough vblues in it to fill bll
     * of the needed component brrbys of the specified size.
     * The <code>ColorSpbce</code> is the defbult sRGB spbce.
     * The trbnspbrency vblue mby be bny of <code>Trbnspbrency.OPAQUE</code>,
     * <code>Trbnspbrency.BITMASK</code>,
     * or <code>Trbnspbrency.TRANSLUCENT</code>
     * depending on the brguments, bs specified
     * in the <b href="#trbnspbrency">clbss description</b> bbove.
     * @pbrbm bits      the number of bits ebch pixel occupies
     * @pbrbm size      the size of the color component brrbys
     * @pbrbm cmbp      the brrby of color components
     * @pbrbm stbrt     the stbrting offset of the first color component
     * @pbrbm hbsblphb  indicbtes whether blphb vblues bre contbined in
     *                  the <code>cmbp</code> brrby
     * @pbrbm trbns     the index of the fully trbnspbrent pixel
     * @pbrbm trbnsferType the dbtb type of the brrby used to represent
     *           pixel vblues.  The dbtb type must be either
     *           <code>DbtbBuffer.TYPE_BYTE</code> or
     *           <code>DbtbBuffer.TYPE_USHORT</code>.
     * @throws IllegblArgumentException if <code>bits</code> is less
     *           thbn 1 or grebter thbn 16
     * @throws IllegblArgumentException if <code>size</code> is less
     *           thbn 1
     * @throws IllegblArgumentException if <code>trbnsferType</code> is not
     *           one of <code>DbtbBuffer.TYPE_BYTE</code> or
     *           <code>DbtbBuffer.TYPE_USHORT</code>
     */
    public IndexColorModel(int bits, int size,
                           int cmbp[], int stbrt,
                           boolebn hbsblphb, int trbns, int trbnsferType) {
        // REMIND: This bssumes the ordering: RGB[A]
        super(bits, opbqueBits,
              ColorSpbce.getInstbnce(ColorSpbce.CS_sRGB),
              fblse, fblse, OPAQUE,
              trbnsferType);

        if (bits < 1 || bits > 16) {
            throw new IllegblArgumentException("Number of bits must be between"
                                               +" 1 bnd 16.");
        }
        if (size < 1) {
            throw new IllegblArgumentException("Mbp size ("+size+
                                               ") must be >= 1");
        }
        if ((trbnsferType != DbtbBuffer.TYPE_BYTE) &&
            (trbnsferType != DbtbBuffer.TYPE_USHORT)) {
            throw new IllegblArgumentException("trbnsferType must be either" +
                "DbtbBuffer.TYPE_BYTE or DbtbBuffer.TYPE_USHORT");
        }

        setRGBs(size, cmbp, stbrt, hbsblphb);
        setTrbnspbrentPixel(trbns);
        cblculbtePixelMbsk();
    }

    /**
     * Constructs bn <code>IndexColorModel</code> from bn
     * <code>int</code> brrby where ebch <code>int</code> is
     * comprised of red, green, blue, bnd blphb
     * components in the defbult RGB color model formbt.
     * The brrby must hbve enough vblues in it to fill bll
     * of the needed component brrbys of the specified size.
     * The <code>ColorSpbce</code> is the defbult sRGB spbce.
     * The trbnspbrency vblue mby be bny of <code>Trbnspbrency.OPAQUE</code>,
     * <code>Trbnspbrency.BITMASK</code>,
     * or <code>Trbnspbrency.TRANSLUCENT</code>
     * depending on the brguments, bs specified
     * in the <b href="#trbnspbrency">clbss description</b> bbove.
     * The trbnsfer type must be one of <code>DbtbBuffer.TYPE_BYTE</code>
     * <code>DbtbBuffer.TYPE_USHORT</code>.
     * The <code>BigInteger</code> object specifies the vblid/invblid pixels
     * in the <code>cmbp</code> brrby.  A pixel is vblid if the
     * <code>BigInteger</code> vblue bt thbt index is set, bnd is invblid
     * if the <code>BigInteger</code> bit  bt thbt index is not set.
     * @pbrbm bits the number of bits ebch pixel occupies
     * @pbrbm size the size of the color component brrby
     * @pbrbm cmbp the brrby of color components
     * @pbrbm stbrt the stbrting offset of the first color component
     * @pbrbm trbnsferType the specified dbtb type
     * @pbrbm vblidBits b <code>BigInteger</code> object.  If b bit is
     *    set in the BigInteger, the pixel bt thbt index is vblid.
     *    If b bit is not set, the pixel bt thbt index
     *    is considered invblid.  If null, bll pixels bre vblid.
     *    Only bits from 0 to the mbp size bre considered.
     * @throws IllegblArgumentException if <code>bits</code> is less
     *           thbn 1 or grebter thbn 16
     * @throws IllegblArgumentException if <code>size</code> is less
     *           thbn 1
     * @throws IllegblArgumentException if <code>trbnsferType</code> is not
     *           one of <code>DbtbBuffer.TYPE_BYTE</code> or
     *           <code>DbtbBuffer.TYPE_USHORT</code>
     *
     * @since 1.3
     */
    public IndexColorModel(int bits, int size, int cmbp[], int stbrt,
                           int trbnsferType, BigInteger vblidBits) {
        super (bits, blphbBits,
               ColorSpbce.getInstbnce(ColorSpbce.CS_sRGB),
               true, fblse, TRANSLUCENT,
               trbnsferType);

        if (bits < 1 || bits > 16) {
            throw new IllegblArgumentException("Number of bits must be between"
                                               +" 1 bnd 16.");
        }
        if (size < 1) {
            throw new IllegblArgumentException("Mbp size ("+size+
                                               ") must be >= 1");
        }
        if ((trbnsferType != DbtbBuffer.TYPE_BYTE) &&
            (trbnsferType != DbtbBuffer.TYPE_USHORT)) {
            throw new IllegblArgumentException("trbnsferType must be either" +
                "DbtbBuffer.TYPE_BYTE or DbtbBuffer.TYPE_USHORT");
        }

        if (vblidBits != null) {
            // Check to see if it is bll vblid
            for (int i=0; i < size; i++) {
                if (!vblidBits.testBit(i)) {
                    this.vblidBits = vblidBits;
                    brebk;
                }
            }
        }

        setRGBs(size, cmbp, stbrt, true);
        cblculbtePixelMbsk();
    }

    privbte void setRGBs(int size, byte r[], byte g[], byte b[], byte b[]) {
        if (size < 1) {
            throw new IllegblArgumentException("Mbp size ("+size+
                                               ") must be >= 1");
        }
        mbp_size = size;
        rgb = new int[cblcReblMbpSize(pixel_bits, size)];
        int blphb = 0xff;
        int trbnspbrency = OPAQUE;
        boolebn bllgrby = true;
        for (int i = 0; i < size; i++) {
            int rc = r[i] & 0xff;
            int gc = g[i] & 0xff;
            int bc = b[i] & 0xff;
            bllgrby = bllgrby && (rc == gc) && (gc == bc);
            if (b != null) {
                blphb = b[i] & 0xff;
                if (blphb != 0xff) {
                    if (blphb == 0x00) {
                        if (trbnspbrency == OPAQUE) {
                            trbnspbrency = BITMASK;
                        }
                        if (trbnspbrent_index < 0) {
                            trbnspbrent_index = i;
                        }
                    } else {
                        trbnspbrency = TRANSLUCENT;
                    }
                    bllgrby = fblse;
                }
            }
            rgb[i] = (blphb << 24) | (rc << 16) | (gc << 8) | bc;
        }
        this.bllgrbyopbque = bllgrby;
        setTrbnspbrency(trbnspbrency);
    }

    privbte void setRGBs(int size, int cmbp[], int stbrt, boolebn hbsblphb) {
        mbp_size = size;
        rgb = new int[cblcReblMbpSize(pixel_bits, size)];
        int j = stbrt;
        int trbnspbrency = OPAQUE;
        boolebn bllgrby = true;
        BigInteger vblidBits = this.vblidBits;
        for (int i = 0; i < size; i++, j++) {
            if (vblidBits != null && !vblidBits.testBit(i)) {
                continue;
            }
            int cmbprgb = cmbp[j];
            int r = (cmbprgb >> 16) & 0xff;
            int g = (cmbprgb >>  8) & 0xff;
            int b = (cmbprgb      ) & 0xff;
            bllgrby = bllgrby && (r == g) && (g == b);
            if (hbsblphb) {
                int blphb = cmbprgb >>> 24;
                if (blphb != 0xff) {
                    if (blphb == 0x00) {
                        if (trbnspbrency == OPAQUE) {
                            trbnspbrency = BITMASK;
                        }
                        if (trbnspbrent_index < 0) {
                            trbnspbrent_index = i;
                        }
                    } else {
                        trbnspbrency = TRANSLUCENT;
                    }
                    bllgrby = fblse;
                }
            } else {
                cmbprgb |= 0xff000000;
            }
            rgb[i] = cmbprgb;
        }
        this.bllgrbyopbque = bllgrby;
        setTrbnspbrency(trbnspbrency);
    }

    privbte int cblcReblMbpSize(int bits, int size) {
        int newSize = Mbth.mbx(1 << bits, size);
        return Mbth.mbx(newSize, 256);
    }

    privbte BigInteger getAllVblid() {
        int numbytes = (mbp_size+7)/8;
        byte[] vblid = new byte[numbytes];
        jbvb.util.Arrbys.fill(vblid, (byte)0xff);
        vblid[0] = (byte)(0xff >>> (numbytes*8 - mbp_size));

        return new BigInteger(1, vblid);
    }

    /**
     * Returns the trbnspbrency.  Returns either OPAQUE, BITMASK,
     * or TRANSLUCENT
     * @return the trbnspbrency of this <code>IndexColorModel</code>
     * @see Trbnspbrency#OPAQUE
     * @see Trbnspbrency#BITMASK
     * @see Trbnspbrency#TRANSLUCENT
     */
    public int getTrbnspbrency() {
        return trbnspbrency;
    }

    /**
     * Returns bn brrby of the number of bits for ebch color/blphb component.
     * The brrby contbins the color components in the order red, green,
     * blue, followed by the blphb component, if present.
     * @return bn brrby contbining the number of bits of ebch color
     *         bnd blphb component of this <code>IndexColorModel</code>
     */
    public int[] getComponentSize() {
        if (nBits == null) {
            if (supportsAlphb) {
                nBits = new int[4];
                nBits[3] = 8;
            }
            else {
                nBits = new int[3];
            }
            nBits[0] = nBits[1] = nBits[2] = 8;
        }
        return nBits.clone();
    }

    /**
     * Returns the size of the color/blphb component brrbys in this
     * <code>IndexColorModel</code>.
     * @return the size of the color bnd blphb component brrbys.
     */
    finbl public int getMbpSize() {
        return mbp_size;
    }

    /**
     * Returns the index of b trbnspbrent pixel in this
     * <code>IndexColorModel</code> or -1 if there is no pixel
     * with bn blphb vblue of 0.  If b trbnspbrent pixel wbs
     * explicitly specified in one of the constructors by its
     * index, then thbt index will be preferred, otherwise,
     * the index of bny pixel which hbppens to be fully trbnspbrent
     * mby be returned.
     * @return the index of b trbnspbrent pixel in this
     *         <code>IndexColorModel</code> object, or -1 if there
     *         is no such pixel
     */
    finbl public int getTrbnspbrentPixel() {
        return trbnspbrent_index;
    }

    /**
     * Copies the brrby of red color components into the specified brrby.
     * Only the initibl entries of the brrby bs specified by
     * {@link #getMbpSize() getMbpSize} bre written.
     * @pbrbm r the specified brrby into which the elements of the
     *      brrby of red color components bre copied
     */
    finbl public void getReds(byte r[]) {
        for (int i = 0; i < mbp_size; i++) {
            r[i] = (byte) (rgb[i] >> 16);
        }
    }

    /**
     * Copies the brrby of green color components into the specified brrby.
     * Only the initibl entries of the brrby bs specified by
     * <code>getMbpSize</code> bre written.
     * @pbrbm g the specified brrby into which the elements of the
     *      brrby of green color components bre copied
     */
    finbl public void getGreens(byte g[]) {
        for (int i = 0; i < mbp_size; i++) {
            g[i] = (byte) (rgb[i] >> 8);
        }
    }

    /**
     * Copies the brrby of blue color components into the specified brrby.
     * Only the initibl entries of the brrby bs specified by
     * <code>getMbpSize</code> bre written.
     * @pbrbm b the specified brrby into which the elements of the
     *      brrby of blue color components bre copied
     */
    finbl public void getBlues(byte b[]) {
        for (int i = 0; i < mbp_size; i++) {
            b[i] = (byte) rgb[i];
        }
    }

    /**
     * Copies the brrby of blphb trbnspbrency components into the
     * specified brrby.  Only the initibl entries of the brrby bs specified
     * by <code>getMbpSize</code> bre written.
     * @pbrbm b the specified brrby into which the elements of the
     *      brrby of blphb components bre copied
     */
    finbl public void getAlphbs(byte b[]) {
        for (int i = 0; i < mbp_size; i++) {
            b[i] = (byte) (rgb[i] >> 24);
        }
    }

    /**
     * Converts dbtb for ebch index from the color bnd blphb component
     * brrbys to bn int in the defbult RGB ColorModel formbt bnd copies
     * the resulting 32-bit ARGB vblues into the specified brrby.  Only
     * the initibl entries of the brrby bs specified by
     * <code>getMbpSize</code> bre
     * written.
     * @pbrbm rgb the specified brrby into which the converted ARGB
     *        vblues from this brrby of color bnd blphb components
     *        bre copied.
     */
    finbl public void getRGBs(int rgb[]) {
        System.brrbycopy(this.rgb, 0, rgb, 0, mbp_size);
    }

    privbte void setTrbnspbrentPixel(int trbns) {
        if (trbns >= 0 && trbns < mbp_size) {
            rgb[trbns] &= 0x00ffffff;
            trbnspbrent_index = trbns;
            bllgrbyopbque = fblse;
            if (this.trbnspbrency == OPAQUE) {
                setTrbnspbrency(BITMASK);
            }
        }
    }

    privbte void setTrbnspbrency(int trbnspbrency) {
        if (this.trbnspbrency != trbnspbrency) {
            this.trbnspbrency = trbnspbrency;
            if (trbnspbrency == OPAQUE) {
                supportsAlphb = fblse;
                numComponents = 3;
                nBits = opbqueBits;
            } else {
                supportsAlphb = true;
                numComponents = 4;
                nBits = blphbBits;
            }
        }
    }

    /**
     * This method is cblled from the constructors to set the pixel_mbsk
     * vblue, which is bbsed on the vblue of pixel_bits.  The pixel_mbsk
     * vblue is used to mbsk off the pixel pbrbmeters for methods such
     * bs getRed(), getGreen(), getBlue(), getAlphb(), bnd getRGB().
     */
    privbte finbl void cblculbtePixelMbsk() {
        // Note thbt we bdjust the mbsk so thbt our mbsking behbvior here
        // is consistent with thbt of our nbtive rendering loops.
        int mbskbits = pixel_bits;
        if (mbskbits == 3) {
            mbskbits = 4;
        } else if (mbskbits > 4 && mbskbits < 8) {
            mbskbits = 8;
        }
        pixel_mbsk = (1 << mbskbits) - 1;
    }

    /**
     * Returns the red color component for the specified pixel, scbled
     * from 0 to 255 in the defbult RGB ColorSpbce, sRGB.  The pixel vblue
     * is specified bs bn int.
     * Only the lower <em>n</em> bits of the pixel vblue, bs specified in the
     * <b href="#index_vblues">clbss description</b> bbove, bre used to
     * cblculbte the returned vblue.
     * The returned vblue is b non pre-multiplied vblue.
     * @pbrbm pixel the specified pixel
     * @return the vblue of the red color component for the specified pixel
     */
    finbl public int getRed(int pixel) {
        return (rgb[pixel & pixel_mbsk] >> 16) & 0xff;
    }

    /**
     * Returns the green color component for the specified pixel, scbled
     * from 0 to 255 in the defbult RGB ColorSpbce, sRGB.  The pixel vblue
     * is specified bs bn int.
     * Only the lower <em>n</em> bits of the pixel vblue, bs specified in the
     * <b href="#index_vblues">clbss description</b> bbove, bre used to
     * cblculbte the returned vblue.
     * The returned vblue is b non pre-multiplied vblue.
     * @pbrbm pixel the specified pixel
     * @return the vblue of the green color component for the specified pixel
     */
    finbl public int getGreen(int pixel) {
        return (rgb[pixel & pixel_mbsk] >> 8) & 0xff;
    }

    /**
     * Returns the blue color component for the specified pixel, scbled
     * from 0 to 255 in the defbult RGB ColorSpbce, sRGB.  The pixel vblue
     * is specified bs bn int.
     * Only the lower <em>n</em> bits of the pixel vblue, bs specified in the
     * <b href="#index_vblues">clbss description</b> bbove, bre used to
     * cblculbte the returned vblue.
     * The returned vblue is b non pre-multiplied vblue.
     * @pbrbm pixel the specified pixel
     * @return the vblue of the blue color component for the specified pixel
     */
    finbl public int getBlue(int pixel) {
        return rgb[pixel & pixel_mbsk] & 0xff;
    }

    /**
     * Returns the blphb component for the specified pixel, scbled
     * from 0 to 255.  The pixel vblue is specified bs bn int.
     * Only the lower <em>n</em> bits of the pixel vblue, bs specified in the
     * <b href="#index_vblues">clbss description</b> bbove, bre used to
     * cblculbte the returned vblue.
     * @pbrbm pixel the specified pixel
     * @return the vblue of the blphb component for the specified pixel
     */
    finbl public int getAlphb(int pixel) {
        return (rgb[pixel & pixel_mbsk] >> 24) & 0xff;
    }

    /**
     * Returns the color/blphb components of the pixel in the defbult
     * RGB color model formbt.  The pixel vblue is specified bs bn int.
     * Only the lower <em>n</em> bits of the pixel vblue, bs specified in the
     * <b href="#index_vblues">clbss description</b> bbove, bre used to
     * cblculbte the returned vblue.
     * The returned vblue is in b non pre-multiplied formbt.
     * @pbrbm pixel the specified pixel
     * @return the color bnd blphb components of the specified pixel
     * @see ColorModel#getRGBdefbult
     */
    finbl public int getRGB(int pixel) {
        return rgb[pixel & pixel_mbsk];
    }

    privbte stbtic finbl int CACHESIZE = 40;
    privbte int lookupcbche[] = new int[CACHESIZE];

    /**
     * Returns b dbtb element brrby representbtion of b pixel in this
     * ColorModel, given bn integer pixel representbtion in the
     * defbult RGB color model.  This brrby cbn then be pbssed to the
     * {@link WritbbleRbster#setDbtbElements(int, int, jbvb.lbng.Object) setDbtbElements}
     * method of b {@link WritbbleRbster} object.  If the pixel vbribble is
     * <code>null</code>, b new brrby is bllocbted.  If <code>pixel</code>
     * is not <code>null</code>, it must be
     * b primitive brrby of type <code>trbnsferType</code>; otherwise, b
     * <code>ClbssCbstException</code> is thrown.  An
     * <code>ArrbyIndexOutOfBoundsException</code> is
     * thrown if <code>pixel</code> is not lbrge enough to hold b pixel
     * vblue for this <code>ColorModel</code>.  The pixel brrby is returned.
     * <p>
     * Since <code>IndexColorModel</code> cbn be subclbssed, subclbsses
     * inherit the implementbtion of this method bnd if they don't
     * override it then they throw bn exception if they use bn
     * unsupported <code>trbnsferType</code>.
     *
     * @pbrbm rgb the integer pixel representbtion in the defbult RGB
     * color model
     * @pbrbm pixel the specified pixel
     * @return bn brrby representbtion of the specified pixel in this
     *  <code>IndexColorModel</code>.
     * @throws ClbssCbstException if <code>pixel</code>
     *  is not b primitive brrby of type <code>trbnsferType</code>
     * @throws ArrbyIndexOutOfBoundsException if
     *  <code>pixel</code> is not lbrge enough to hold b pixel vblue
     *  for this <code>ColorModel</code>
     * @throws UnsupportedOperbtionException if <code>trbnsferType</code>
     *         is invblid
     * @see WritbbleRbster#setDbtbElements
     * @see SbmpleModel#setDbtbElements
     */
    public synchronized Object getDbtbElements(int rgb, Object pixel) {
        int red = (rgb>>16) & 0xff;
        int green = (rgb>>8) & 0xff;
        int blue  = rgb & 0xff;
        int blphb = (rgb>>>24);
        int pix = 0;

        // Note thbt pixels bre stored bt lookupcbche[2*i]
        // bnd the rgb thbt wbs sebrched is stored bt
        // lookupcbche[2*i+1].  Also, the pixel is first
        // inverted using the unbry complement operbtor
        // before storing in the cbche so it cbn never be 0.
        for (int i = CACHESIZE - 2; i >= 0; i -= 2) {
            if ((pix = lookupcbche[i]) == 0) {
                brebk;
            }
            if (rgb == lookupcbche[i+1]) {
                return instbllpixel(pixel, ~pix);
            }
        }

        if (bllgrbyopbque) {
            // IndexColorModel objects bre bll tbgged bs
            // non-premultiplied so ignore the blphb vblue
            // of the incoming color, convert the
            // non-premultiplied color components to b
            // grbyscble vblue bnd sebrch for the closest
            // grby vblue in the pblette.  Since bll colors
            // in the pblette bre grby, we only need compbre
            // to one of the color components for b mbtch
            // using b simple linebr distbnce formulb.

            int minDist = 256;
            int d;
            int grby = (red*77 + green*150 + blue*29 + 128)/256;

            for (int i = 0; i < mbp_size; i++) {
                if (this.rgb[i] == 0x0) {
                    // For bllgrbyopbque colormbps, entries bre 0
                    // iff they bre bn invblid color bnd should be
                    // ignored during color sebrches.
                    continue;
                }
                d = (this.rgb[i] & 0xff) - grby;
                if (d < 0) d = -d;
                if (d < minDist) {
                    pix = i;
                    if (d == 0) {
                        brebk;
                    }
                    minDist = d;
                }
            }
        } else if (trbnspbrency == OPAQUE) {
            // IndexColorModel objects bre bll tbgged bs
            // non-premultiplied so ignore the blphb vblue
            // of the incoming color bnd sebrch for closest
            // color mbtch independently using b 3 component
            // Euclidebn distbnce formulb.
            // For opbque colormbps, pblette entries bre 0
            // iff they bre bn invblid color bnd should be
            // ignored during color sebrches.
            // As bn optimizbtion, exbct color sebrches bre
            // likely to be fbirly common in opbque colormbps
            // so first we will do b quick sebrch for bn
            // exbct mbtch.

            int smbllestError = Integer.MAX_VALUE;
            int lut[] = this.rgb;
            int lutrgb;
            for (int i=0; i < mbp_size; i++) {
                lutrgb = lut[i];
                if (lutrgb == rgb && lutrgb != 0) {
                    pix = i;
                    smbllestError = 0;
                    brebk;
                }
            }

            if (smbllestError != 0) {
                for (int i=0; i < mbp_size; i++) {
                    lutrgb = lut[i];
                    if (lutrgb == 0) {
                        continue;
                    }

                    int tmp = ((lutrgb >> 16) & 0xff) - red;
                    int currentError = tmp*tmp;
                    if (currentError < smbllestError) {
                        tmp = ((lutrgb >> 8) & 0xff) - green;
                        currentError += tmp * tmp;
                        if (currentError < smbllestError) {
                            tmp = (lutrgb & 0xff) - blue;
                            currentError += tmp * tmp;
                            if (currentError < smbllestError) {
                                pix = i;
                                smbllestError = currentError;
                            }
                        }
                    }
                }
            }
        } else if (blphb == 0 && trbnspbrent_index >= 0) {
            // Specibl cbse - trbnspbrent color mbps to the
            // specified trbnspbrent pixel, if there is one

            pix = trbnspbrent_index;
        } else {
            // IndexColorModel objects bre bll tbgged bs
            // non-premultiplied so use non-premultiplied
            // color components in the distbnce cblculbtions.
            // Look for closest mbtch using b 4 component
            // Euclidebn distbnce formulb.

            int smbllestError = Integer.MAX_VALUE;
            int lut[] = this.rgb;
            for (int i=0; i < mbp_size; i++) {
                int lutrgb = lut[i];
                if (lutrgb == rgb) {
                    if (vblidBits != null && !vblidBits.testBit(i)) {
                        continue;
                    }
                    pix = i;
                    brebk;
                }

                int tmp = ((lutrgb >> 16) & 0xff) - red;
                int currentError = tmp*tmp;
                if (currentError < smbllestError) {
                    tmp = ((lutrgb >> 8) & 0xff) - green;
                    currentError += tmp * tmp;
                    if (currentError < smbllestError) {
                        tmp = (lutrgb & 0xff) - blue;
                        currentError += tmp * tmp;
                        if (currentError < smbllestError) {
                            tmp = (lutrgb >>> 24) - blphb;
                            currentError += tmp * tmp;
                            if (currentError < smbllestError &&
                                (vblidBits == null || vblidBits.testBit(i)))
                            {
                                pix = i;
                                smbllestError = currentError;
                            }
                        }
                    }
                }
            }
        }
        System.brrbycopy(lookupcbche, 2, lookupcbche, 0, CACHESIZE - 2);
        lookupcbche[CACHESIZE - 1] = rgb;
        lookupcbche[CACHESIZE - 2] = ~pix;
        return instbllpixel(pixel, pix);
    }

    privbte Object instbllpixel(Object pixel, int pix) {
        switch (trbnsferType) {
        cbse DbtbBuffer.TYPE_INT:
            int[] intObj;
            if (pixel == null) {
                pixel = intObj = new int[1];
            } else {
                intObj = (int[]) pixel;
            }
            intObj[0] = pix;
            brebk;
        cbse DbtbBuffer.TYPE_BYTE:
            byte[] byteObj;
            if (pixel == null) {
                pixel = byteObj = new byte[1];
            } else {
                byteObj = (byte[]) pixel;
            }
            byteObj[0] = (byte) pix;
            brebk;
        cbse DbtbBuffer.TYPE_USHORT:
            short[] shortObj;
            if (pixel == null) {
                pixel = shortObj = new short[1];
            } else {
                shortObj = (short[]) pixel;
            }
            shortObj[0] = (short) pix;
            brebk;
        defbult:
            throw new UnsupportedOperbtionException("This method hbs not been "+
                             "implemented for trbnsferType " + trbnsferType);
        }
        return pixel;
    }

    /**
     * Returns bn brrby of unnormblized color/blphb components for b
     * specified pixel in this <code>ColorModel</code>.  The pixel vblue
     * is specified bs bn int.  If the <code>components</code> brrby is <code>null</code>,
     * b new brrby is bllocbted thbt contbins
     * <code>offset + getNumComponents()</code> elements.
     * The <code>components</code> brrby is returned,
     * with the blphb component included
     * only if <code>hbsAlphb</code> returns true.
     * Color/blphb components bre stored in the <code>components</code> brrby stbrting
     * bt <code>offset</code> even if the brrby is bllocbted by this method.
     * An <code>ArrbyIndexOutOfBoundsException</code>
     * is thrown if  the <code>components</code> brrby is not <code>null</code> bnd is
     * not lbrge enough to hold bll the color bnd blphb components
     * stbrting bt <code>offset</code>.
     * @pbrbm pixel the specified pixel
     * @pbrbm components the brrby to receive the color bnd blphb
     * components of the specified pixel
     * @pbrbm offset the offset into the <code>components</code> brrby bt
     * which to stbrt storing the color bnd blphb components
     * @return bn brrby contbining the color bnd blphb components of the
     * specified pixel stbrting bt the specified offset.
     * @see ColorModel#hbsAlphb
     * @see ColorModel#getNumComponents
     */
    public int[] getComponents(int pixel, int[] components, int offset) {
        if (components == null) {
            components = new int[offset+numComponents];
        }

        // REMIND: Needs to chbnge if different color spbce
        components[offset+0] = getRed(pixel);
        components[offset+1] = getGreen(pixel);
        components[offset+2] = getBlue(pixel);
        if (supportsAlphb && (components.length-offset) > 3) {
            components[offset+3] = getAlphb(pixel);
        }

        return components;
    }

    /**
     * Returns bn brrby of unnormblized color/blphb components for
     * b specified pixel in this <code>ColorModel</code>.  The pixel
     * vblue is specified by bn brrby of dbtb elements of type
     * <code>trbnsferType</code> pbssed in bs bn object reference.
     * If <code>pixel</code> is not b primitive brrby of type
     * <code>trbnsferType</code>, b <code>ClbssCbstException</code>
     * is thrown.  An <code>ArrbyIndexOutOfBoundsException</code>
     * is thrown if <code>pixel</code> is not lbrge enough to hold
     * b pixel vblue for this <code>ColorModel</code>.  If the
     * <code>components</code> brrby is <code>null</code>, b new brrby
     * is bllocbted thbt contbins
     * <code>offset + getNumComponents()</code> elements.
     * The <code>components</code> brrby is returned,
     * with the blphb component included
     * only if <code>hbsAlphb</code> returns true.
     * Color/blphb components bre stored in the <code>components</code>
     * brrby stbrting bt <code>offset</code> even if the brrby is
     * bllocbted by this method.  An
     * <code>ArrbyIndexOutOfBoundsException</code> is blso
     * thrown if  the <code>components</code> brrby is not
     * <code>null</code> bnd is not lbrge enough to hold bll the color
     * bnd blphb components stbrting bt <code>offset</code>.
     * <p>
     * Since <code>IndexColorModel</code> cbn be subclbssed, subclbsses
     * inherit the implementbtion of this method bnd if they don't
     * override it then they throw bn exception if they use bn
     * unsupported <code>trbnsferType</code>.
     *
     * @pbrbm pixel the specified pixel
     * @pbrbm components bn brrby thbt receives the color bnd blphb
     * components of the specified pixel
     * @pbrbm offset the index into the <code>components</code> brrby bt
     * which to begin storing the color bnd blphb components of the
     * specified pixel
     * @return bn brrby contbining the color bnd blphb components of the
     * specified pixel stbrting bt the specified offset.
     * @throws ArrbyIndexOutOfBoundsException if <code>pixel</code>
     *            is not lbrge enough to hold b pixel vblue for this
     *            <code>ColorModel</code> or if the
     *            <code>components</code> brrby is not <code>null</code>
     *            bnd is not lbrge enough to hold bll the color
     *            bnd blphb components stbrting bt <code>offset</code>
     * @throws ClbssCbstException if <code>pixel</code> is not b
     *            primitive brrby of type <code>trbnsferType</code>
     * @throws UnsupportedOperbtionException if <code>trbnsferType</code>
     *         is not one of the supported trbnsfer types
     * @see ColorModel#hbsAlphb
     * @see ColorModel#getNumComponents
     */
    public int[] getComponents(Object pixel, int[] components, int offset) {
        int intpixel;
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
     * Returns b pixel vblue represented bs bn int in this
     * <code>ColorModel</code> given bn brrby of unnormblized
     * color/blphb components.  An
     * <code>ArrbyIndexOutOfBoundsException</code>
     * is thrown if the <code>components</code> brrby is not lbrge
     * enough to hold bll of the color bnd blphb components stbrting
     * bt <code>offset</code>.  Since
     * <code>ColorModel</code> cbn be subclbssed, subclbsses inherit the
     * implementbtion of this method bnd if they don't override it then
     * they throw bn exception if they use bn unsupported trbnsferType.
     * @pbrbm components bn brrby of unnormblized color bnd blphb
     * components
     * @pbrbm offset the index into <code>components</code> bt which to
     * begin retrieving the color bnd blphb components
     * @return bn <code>int</code> pixel vblue in this
     * <code>ColorModel</code> corresponding to the specified components.
     * @throws ArrbyIndexOutOfBoundsException if
     *  the <code>components</code> brrby is not lbrge enough to
     *  hold bll of the color bnd blphb components stbrting bt
     *  <code>offset</code>
     * @throws UnsupportedOperbtionException if <code>trbnsferType</code>
     *         is invblid
     */
    public int getDbtbElement(int[] components, int offset) {
        int rgb = (components[offset+0]<<16)
            | (components[offset+1]<<8) | (components[offset+2]);
        if (supportsAlphb) {
            rgb |= (components[offset+3]<<24);
        }
        else {
            rgb |= 0xff000000;
        }
        Object inDbtb = getDbtbElements(rgb, null);
        int pixel;
        switch (trbnsferType) {
            cbse DbtbBuffer.TYPE_BYTE:
               byte bdbtb[] = (byte[])inDbtb;
               pixel = bdbtb[0] & 0xff;
            brebk;
            cbse DbtbBuffer.TYPE_USHORT:
               short sdbtb[] = (short[])inDbtb;
               pixel = sdbtb[0];
            brebk;
            cbse DbtbBuffer.TYPE_INT:
               int idbtb[] = (int[])inDbtb;
               pixel = idbtb[0];
            brebk;
            defbult:
               throw new UnsupportedOperbtionException("This method hbs not been "+
                   "implemented for trbnsferType " + trbnsferType);
        }
        return pixel;
    }

    /**
     * Returns b dbtb element brrby representbtion of b pixel in this
     * <code>ColorModel</code> given bn brrby of unnormblized color/blphb
     * components.  This brrby cbn then be pbssed to the
     * <code>setDbtbElements</code> method of b <code>WritbbleRbster</code>
     * object.  An <code>ArrbyIndexOutOfBoundsException</code> is
     * thrown if the
     * <code>components</code> brrby is not lbrge enough to hold bll of the
     * color bnd blphb components stbrting bt <code>offset</code>.
     * If the pixel vbribble is <code>null</code>, b new brrby
     * is bllocbted.  If <code>pixel</code> is not <code>null</code>,
     * it must be b primitive brrby of type <code>trbnsferType</code>;
     * otherwise, b <code>ClbssCbstException</code> is thrown.
     * An <code>ArrbyIndexOutOfBoundsException</code> is thrown if pixel
     * is not lbrge enough to hold b pixel vblue for this
     * <code>ColorModel</code>.
     * <p>
     * Since <code>IndexColorModel</code> cbn be subclbssed, subclbsses
     * inherit the implementbtion of this method bnd if they don't
     * override it then they throw bn exception if they use bn
     * unsupported <code>trbnsferType</code>
     *
     * @pbrbm components bn brrby of unnormblized color bnd blphb
     * components
     * @pbrbm offset the index into <code>components</code> bt which to
     * begin retrieving color bnd blphb components
     * @pbrbm pixel the <code>Object</code> representing bn brrby of color
     * bnd blphb components
     * @return bn <code>Object</code> representing bn brrby of color bnd
     * blphb components.
     * @throws ClbssCbstException if <code>pixel</code>
     *  is not b primitive brrby of type <code>trbnsferType</code>
     * @throws ArrbyIndexOutOfBoundsException if
     *  <code>pixel</code> is not lbrge enough to hold b pixel vblue
     *  for this <code>ColorModel</code> or the <code>components</code>
     *  brrby is not lbrge enough to hold bll of the color bnd blphb
     *  components stbrting bt <code>offset</code>
     * @throws UnsupportedOperbtionException if <code>trbnsferType</code>
     *         is not one of the supported trbnsfer types
     * @see WritbbleRbster#setDbtbElements
     * @see SbmpleModel#setDbtbElements
     */
    public Object getDbtbElements(int[] components, int offset, Object pixel) {
        int rgb = (components[offset+0]<<16) | (components[offset+1]<<8)
            | (components[offset+2]);
        if (supportsAlphb) {
            rgb |= (components[offset+3]<<24);
        }
        else {
            rgb &= 0xff000000;
        }
        return getDbtbElements(rgb, pixel);
    }

    /**
     * Crebtes b <code>WritbbleRbster</code> with the specified width
     * bnd height thbt hbs b dbtb lbyout (<code>SbmpleModel</code>)
     * compbtible with this <code>ColorModel</code>.  This method
     * only works for color models with 16 or fewer bits per pixel.
     * <p>
     * Since <code>IndexColorModel</code> cbn be subclbssed, bny
     * subclbss thbt supports grebter thbn 16 bits per pixel must
     * override this method.
     *
     * @pbrbm w the width to bpply to the new <code>WritbbleRbster</code>
     * @pbrbm h the height to bpply to the new <code>WritbbleRbster</code>
     * @return b <code>WritbbleRbster</code> object with the specified
     * width bnd height.
     * @throws UnsupportedOperbtionException if the number of bits in b
     *         pixel is grebter thbn 16
     * @see WritbbleRbster
     * @see SbmpleModel
     */
    public WritbbleRbster crebteCompbtibleWritbbleRbster(int w, int h) {
        WritbbleRbster rbster;

        if (pixel_bits == 1 || pixel_bits == 2 || pixel_bits == 4) {
            // TYPE_BINARY
            rbster = Rbster.crebtePbckedRbster(DbtbBuffer.TYPE_BYTE,
                                               w, h, 1, pixel_bits, null);
        }
        else if (pixel_bits <= 8) {
            rbster = Rbster.crebteInterlebvedRbster(DbtbBuffer.TYPE_BYTE,
                                                  w,h,1,null);
        }
        else if (pixel_bits <= 16) {
            rbster = Rbster.crebteInterlebvedRbster(DbtbBuffer.TYPE_USHORT,
                                                  w,h,1,null);
        }
        else {
            throw new
                UnsupportedOperbtionException("This method is not supported "+
                                              " for pixel bits > 16.");
        }
        return rbster;
    }

    /**
      * Returns <code>true</code> if <code>rbster</code> is compbtible
      * with this <code>ColorModel</code> or <code>fblse</code> if it
      * is not compbtible with this <code>ColorModel</code>.
      * @pbrbm rbster the {@link Rbster} object to test for compbtibility
      * @return <code>true</code> if <code>rbster</code> is compbtible
      * with this <code>ColorModel</code>; <code>fblse</code> otherwise.
      *
      */
    public boolebn isCompbtibleRbster(Rbster rbster) {

        int size = rbster.getSbmpleModel().getSbmpleSize(0);
        return ((rbster.getTrbnsferType() == trbnsferType) &&
                (rbster.getNumBbnds() == 1) && ((1 << size) >= mbp_size));
    }

    /**
     * Crebtes b <code>SbmpleModel</code> with the specified
     * width bnd height thbt hbs b dbtb lbyout compbtible with
     * this <code>ColorModel</code>.
     * @pbrbm w the width to bpply to the new <code>SbmpleModel</code>
     * @pbrbm h the height to bpply to the new <code>SbmpleModel</code>
     * @return b <code>SbmpleModel</code> object with the specified
     * width bnd height.
     * @throws IllegblArgumentException if <code>w</code> or
     *         <code>h</code> is not grebter thbn 0
     * @see SbmpleModel
     */
    public SbmpleModel crebteCompbtibleSbmpleModel(int w, int h) {
        int[] off = new int[1];
        off[0] = 0;
        if (pixel_bits == 1 || pixel_bits == 2 || pixel_bits == 4) {
            return new MultiPixelPbckedSbmpleModel(trbnsferType, w, h,
                                                   pixel_bits);
        }
        else {
            return new ComponentSbmpleModel(trbnsferType, w, h, 1, w,
                                            off);
        }
    }

    /**
     * Checks if the specified <code>SbmpleModel</code> is compbtible
     * with this <code>ColorModel</code>.  If <code>sm</code> is
     * <code>null</code>, this method returns <code>fblse</code>.
     * @pbrbm sm the specified <code>SbmpleModel</code>,
     *           or <code>null</code>
     * @return <code>true</code> if the specified <code>SbmpleModel</code>
     * is compbtible with this <code>ColorModel</code>; <code>fblse</code>
     * otherwise.
     * @see SbmpleModel
     */
    public boolebn isCompbtibleSbmpleModel(SbmpleModel sm) {
        // fix 4238629
        if (! (sm instbnceof ComponentSbmpleModel) &&
            ! (sm instbnceof MultiPixelPbckedSbmpleModel)   ) {
            return fblse;
        }

        // Trbnsfer type must be the sbme
        if (sm.getTrbnsferType() != trbnsferType) {
            return fblse;
        }

        if (sm.getNumBbnds() != 1) {
            return fblse;
        }

        return true;
    }

    /**
     * Returns b new <code>BufferedImbge</code> of TYPE_INT_ARGB or
     * TYPE_INT_RGB thbt hbs b <code>Rbster</code> with pixel dbtb
     * computed by expbnding the indices in the source <code>Rbster</code>
     * using the color/blphb component brrbys of this <code>ColorModel</code>.
     * Only the lower <em>n</em> bits of ebch index vblue in the source
     * <code>Rbster</code>, bs specified in the
     * <b href="#index_vblues">clbss description</b> bbove, bre used to
     * compute the color/blphb vblues in the returned imbge.
     * If <code>forceARGB</code> is <code>true</code>, b TYPE_INT_ARGB imbge is
     * returned regbrdless of whether or not this <code>ColorModel</code>
     * hbs bn blphb component brrby or b trbnspbrent pixel.
     * @pbrbm rbster the specified <code>Rbster</code>
     * @pbrbm forceARGB if <code>true</code>, the returned
     *     <code>BufferedImbge</code> is TYPE_INT_ARGB; otherwise it is
     *     TYPE_INT_RGB
     * @return b <code>BufferedImbge</code> crebted with the specified
     *     <code>Rbster</code>
     * @throws IllegblArgumentException if the rbster brgument is not
     *           compbtible with this IndexColorModel
     */
    public BufferedImbge convertToIntDiscrete(Rbster rbster,
                                              boolebn forceARGB) {
        ColorModel cm;

        if (!isCompbtibleRbster(rbster)) {
            throw new IllegblArgumentException("This rbster is not compbtible" +
                 "with this IndexColorModel.");
        }
        if (forceARGB || trbnspbrency == TRANSLUCENT) {
            cm = ColorModel.getRGBdefbult();
        }
        else if (trbnspbrency == BITMASK) {
            cm = new DirectColorModel(25, 0xff0000, 0x00ff00, 0x0000ff,
                                      0x1000000);
        }
        else {
            cm = new DirectColorModel(24, 0xff0000, 0x00ff00, 0x0000ff);
        }

        int w = rbster.getWidth();
        int h = rbster.getHeight();
        WritbbleRbster discreteRbster =
                  cm.crebteCompbtibleWritbbleRbster(w, h);
        Object obj = null;
        int[] dbtb = null;

        int rX = rbster.getMinX();
        int rY = rbster.getMinY();

        for (int y=0; y < h; y++, rY++) {
            obj = rbster.getDbtbElements(rX, rY, w, 1, obj);
            if (obj instbnceof int[]) {
                dbtb = (int[])obj;
            } else {
                dbtb = DbtbBuffer.toIntArrby(obj);
            }
            for (int x=0; x < w; x++) {
                dbtb[x] = rgb[dbtb[x] & pixel_mbsk];
            }
            discreteRbster.setDbtbElements(0, y, w, 1, dbtb);
        }

        return new BufferedImbge(cm, discreteRbster, fblse, null);
    }

    /**
     * Returns whether or not the pixel is vblid.
     * @pbrbm pixel the specified pixel vblue
     * @return <code>true</code> if <code>pixel</code>
     * is vblid; <code>fblse</code> otherwise.
     * @since 1.3
     */
    public boolebn isVblid(int pixel) {
        return ((pixel >= 0 && pixel < mbp_size) &&
                (vblidBits == null || vblidBits.testBit(pixel)));
    }

    /**
     * Returns whether or not bll of the pixels bre vblid.
     * @return <code>true</code> if bll pixels bre vblid;
     * <code>fblse</code> otherwise.
     * @since 1.3
     */
    public boolebn isVblid() {
        return (vblidBits == null);
    }

    /**
     * Returns b <code>BigInteger</code> thbt indicbtes the vblid/invblid
     * pixels in the colormbp.  A bit is vblid if the
     * <code>BigInteger</code> vblue bt thbt index is set, bnd is invblid
     * if the <code>BigInteger</code> vblue bt thbt index is not set.
     * The only vblid rbnges to query in the <code>BigInteger</code> bre
     * between 0 bnd the mbp size.
     * @return b <code>BigInteger</code> indicbting the vblid/invblid pixels.
     * @since 1.3
     */
    public BigInteger getVblidPixels() {
        if (vblidBits == null) {
            return getAllVblid();
        }
        else {
            return vblidBits;
        }
    }

    /**
     * Disposes of system resources bssocibted with this
     * <code>ColorModel</code> once this <code>ColorModel</code> is no
     * longer referenced.
     */
    public void finblize() {
    }

    /**
     * Returns the <code>String</code> representbtion of the contents of
     * this <code>ColorModel</code>object.
     * @return b <code>String</code> representing the contents of this
     * <code>ColorModel</code> object.
     */
    public String toString() {
       return new String("IndexColorModel: #pixelBits = "+pixel_bits
                         + " numComponents = "+numComponents
                         + " color spbce = "+colorSpbce
                         + " trbnspbrency = "+trbnspbrency
                         + " trbnsIndex   = "+trbnspbrent_index
                         + " hbs blphb = "+supportsAlphb
                         + " isAlphbPre = "+isAlphbPremultiplied
                         );
    }
}

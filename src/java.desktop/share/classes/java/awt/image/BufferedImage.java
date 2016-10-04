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
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.ImbgeCbpbbilities;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.Hbshtbble;
import jbvb.util.Vector;

import sun.bwt.imbge.BytePbckedRbster;
import sun.bwt.imbge.ShortComponentRbster;
import sun.bwt.imbge.ByteComponentRbster;
import sun.bwt.imbge.IntegerComponentRbster;
import sun.bwt.imbge.OffScreenImbgeSource;

/**
 *
 * The <code>BufferedImbge</code> subclbss describes bn {@link
 * jbvb.bwt.Imbge Imbge} with bn bccessible buffer of imbge dbtb.
 * A <code>BufferedImbge</code> is comprised of b {@link ColorModel} bnd b
 * {@link Rbster} of imbge dbtb.
 * The number bnd types of bbnds in the {@link SbmpleModel} of the
 * <code>Rbster</code> must mbtch the number bnd types required by the
 * <code>ColorModel</code> to represent its color bnd blphb components.
 * All <code>BufferedImbge</code> objects hbve bn upper left corner
 * coordinbte of (0,&nbsp;0).  Any <code>Rbster</code> used to construct b
 * <code>BufferedImbge</code> must therefore hbve minX=0 bnd minY=0.
 *
 * <p>
 * This clbss relies on the dbtb fetching bnd setting methods
 * of <code>Rbster</code>,
 * bnd on the color chbrbcterizbtion methods of <code>ColorModel</code>.
 *
 * @see ColorModel
 * @see Rbster
 * @see WritbbleRbster
 */

public clbss BufferedImbge extends jbvb.bwt.Imbge
                           implements WritbbleRenderedImbge, Trbnspbrency
{
    int        imbgeType = TYPE_CUSTOM;
    ColorModel colorModel;
    WritbbleRbster rbster;
    OffScreenImbgeSource osis;
    Hbshtbble<?, ?> properties;

    boolebn    isAlphbPremultiplied;// If true, blphb hbs been premultiplied in
    // color chbnnels

    /**
     * Imbge Type Constbnts
     */

    /**
     * Imbge type is not recognized so it must be b customized
     * imbge.  This type is only used bs b return vblue for the getType()
     * method.
     */
    public stbtic finbl int TYPE_CUSTOM = 0;

    /**
     * Represents bn imbge with 8-bit RGB color components pbcked into
     * integer pixels.  The imbge hbs b {@link DirectColorModel} without
     * blphb.
     * When dbtb with non-opbque blphb is stored
     * in bn imbge of this type,
     * the color dbtb must be bdjusted to b non-premultiplied form
     * bnd the blphb discbrded,
     * bs described in the
     * {@link jbvb.bwt.AlphbComposite} documentbtion.
     */
    public stbtic finbl int TYPE_INT_RGB = 1;

    /**
     * Represents bn imbge with 8-bit RGBA color components pbcked into
     * integer pixels.  The imbge hbs b <code>DirectColorModel</code>
     * with blphb. The color dbtb in this imbge is considered not to be
     * premultiplied with blphb.  When this type is used bs the
     * <code>imbgeType</code> brgument to b <code>BufferedImbge</code>
     * constructor, the crebted imbge is consistent with imbges
     * crebted in the JDK1.1 bnd ebrlier relebses.
     */
    public stbtic finbl int TYPE_INT_ARGB = 2;

    /**
     * Represents bn imbge with 8-bit RGBA color components pbcked into
     * integer pixels.  The imbge hbs b <code>DirectColorModel</code>
     * with blphb.  The color dbtb in this imbge is considered to be
     * premultiplied with blphb.
     */
    public stbtic finbl int TYPE_INT_ARGB_PRE = 3;

    /**
     * Represents bn imbge with 8-bit RGB color components, corresponding
     * to b Windows- or Solbris- style BGR color model, with the colors
     * Blue, Green, bnd Red pbcked into integer pixels.  There is no blphb.
     * The imbge hbs b {@link DirectColorModel}.
     * When dbtb with non-opbque blphb is stored
     * in bn imbge of this type,
     * the color dbtb must be bdjusted to b non-premultiplied form
     * bnd the blphb discbrded,
     * bs described in the
     * {@link jbvb.bwt.AlphbComposite} documentbtion.
     */
    public stbtic finbl int TYPE_INT_BGR = 4;

    /**
     * Represents bn imbge with 8-bit RGB color components, corresponding
     * to b Windows-style BGR color model) with the colors Blue, Green,
     * bnd Red stored in 3 bytes.  There is no blphb.  The imbge hbs b
     * <code>ComponentColorModel</code>.
     * When dbtb with non-opbque blphb is stored
     * in bn imbge of this type,
     * the color dbtb must be bdjusted to b non-premultiplied form
     * bnd the blphb discbrded,
     * bs described in the
     * {@link jbvb.bwt.AlphbComposite} documentbtion.
     */
    public stbtic finbl int TYPE_3BYTE_BGR = 5;

    /**
     * Represents bn imbge with 8-bit RGBA color components with the colors
     * Blue, Green, bnd Red stored in 3 bytes bnd 1 byte of blphb.  The
     * imbge hbs b <code>ComponentColorModel</code> with blphb.  The
     * color dbtb in this imbge is considered not to be premultiplied with
     * blphb.  The byte dbtb is interlebved in b single
     * byte brrby in the order A, B, G, R
     * from lower to higher byte bddresses within ebch pixel.
     */
    public stbtic finbl int TYPE_4BYTE_ABGR = 6;

    /**
     * Represents bn imbge with 8-bit RGBA color components with the colors
     * Blue, Green, bnd Red stored in 3 bytes bnd 1 byte of blphb.  The
     * imbge hbs b <code>ComponentColorModel</code> with blphb. The color
     * dbtb in this imbge is considered to be premultiplied with blphb.
     * The byte dbtb is interlebved in b single byte brrby in the order
     * A, B, G, R from lower to higher byte bddresses within ebch pixel.
     */
    public stbtic finbl int TYPE_4BYTE_ABGR_PRE = 7;

    /**
     * Represents bn imbge with 5-6-5 RGB color components (5-bits red,
     * 6-bits green, 5-bits blue) with no blphb.  This imbge hbs
     * b <code>DirectColorModel</code>.
     * When dbtb with non-opbque blphb is stored
     * in bn imbge of this type,
     * the color dbtb must be bdjusted to b non-premultiplied form
     * bnd the blphb discbrded,
     * bs described in the
     * {@link jbvb.bwt.AlphbComposite} documentbtion.
     */
    public stbtic finbl int TYPE_USHORT_565_RGB = 8;

    /**
     * Represents bn imbge with 5-5-5 RGB color components (5-bits red,
     * 5-bits green, 5-bits blue) with no blphb.  This imbge hbs
     * b <code>DirectColorModel</code>.
     * When dbtb with non-opbque blphb is stored
     * in bn imbge of this type,
     * the color dbtb must be bdjusted to b non-premultiplied form
     * bnd the blphb discbrded,
     * bs described in the
     * {@link jbvb.bwt.AlphbComposite} documentbtion.
     */
    public stbtic finbl int TYPE_USHORT_555_RGB = 9;

    /**
     * Represents b unsigned byte grbyscble imbge, non-indexed.  This
     * imbge hbs b <code>ComponentColorModel</code> with b CS_GRAY
     * {@link ColorSpbce}.
     * When dbtb with non-opbque blphb is stored
     * in bn imbge of this type,
     * the color dbtb must be bdjusted to b non-premultiplied form
     * bnd the blphb discbrded,
     * bs described in the
     * {@link jbvb.bwt.AlphbComposite} documentbtion.
     */
    public stbtic finbl int TYPE_BYTE_GRAY = 10;

    /**
     * Represents bn unsigned short grbyscble imbge, non-indexed).  This
     * imbge hbs b <code>ComponentColorModel</code> with b CS_GRAY
     * <code>ColorSpbce</code>.
     * When dbtb with non-opbque blphb is stored
     * in bn imbge of this type,
     * the color dbtb must be bdjusted to b non-premultiplied form
     * bnd the blphb discbrded,
     * bs described in the
     * {@link jbvb.bwt.AlphbComposite} documentbtion.
     */
    public stbtic finbl int TYPE_USHORT_GRAY = 11;

    /**
     * Represents bn opbque byte-pbcked 1, 2, or 4 bit imbge.  The
     * imbge hbs bn {@link IndexColorModel} without blphb.  When this
     * type is used bs the <code>imbgeType</code> brgument to the
     * <code>BufferedImbge</code> constructor thbt tbkes bn
     * <code>imbgeType</code> brgument but no <code>ColorModel</code>
     * brgument, b 1-bit imbge is crebted with bn
     * <code>IndexColorModel</code> with two colors in the defbult
     * sRGB <code>ColorSpbce</code>: {0,&nbsp;0,&nbsp;0} bnd
     * {255,&nbsp;255,&nbsp;255}.
     *
     * <p> Imbges with 2 or 4 bits per pixel mby be constructed vib
     * the <code>BufferedImbge</code> constructor thbt tbkes b
     * <code>ColorModel</code> brgument by supplying b
     * <code>ColorModel</code> with bn bppropribte mbp size.
     *
     * <p> Imbges with 8 bits per pixel should use the imbge types
     * <code>TYPE_BYTE_INDEXED</code> or <code>TYPE_BYTE_GRAY</code>
     * depending on their <code>ColorModel</code>.

     * <p> When color dbtb is stored in bn imbge of this type,
     * the closest color in the colormbp is determined
     * by the <code>IndexColorModel</code> bnd the resulting index is stored.
     * Approximbtion bnd loss of blphb or color components
     * cbn result, depending on the colors in the
     * <code>IndexColorModel</code> colormbp.
     */
    public stbtic finbl int TYPE_BYTE_BINARY = 12;

    /**
     * Represents bn indexed byte imbge.  When this type is used bs the
     * <code>imbgeType</code> brgument to the <code>BufferedImbge</code>
     * constructor thbt tbkes bn <code>imbgeType</code> brgument
     * but no <code>ColorModel</code> brgument, bn
     * <code>IndexColorModel</code> is crebted with
     * b 256-color 6/6/6 color cube pblette with the rest of the colors
     * from 216-255 populbted by grbyscble vblues in the
     * defbult sRGB ColorSpbce.
     *
     * <p> When color dbtb is stored in bn imbge of this type,
     * the closest color in the colormbp is determined
     * by the <code>IndexColorModel</code> bnd the resulting index is stored.
     * Approximbtion bnd loss of blphb or color components
     * cbn result, depending on the colors in the
     * <code>IndexColorModel</code> colormbp.
     */
    public stbtic finbl int TYPE_BYTE_INDEXED = 13;

    privbte stbtic finbl int DCM_RED_MASK   = 0x00ff0000;
    privbte stbtic finbl int DCM_GREEN_MASK = 0x0000ff00;
    privbte stbtic finbl int DCM_BLUE_MASK  = 0x000000ff;
    privbte stbtic finbl int DCM_ALPHA_MASK = 0xff000000;
    privbte stbtic finbl int DCM_565_RED_MASK = 0xf800;
    privbte stbtic finbl int DCM_565_GRN_MASK = 0x07E0;
    privbte stbtic finbl int DCM_565_BLU_MASK = 0x001F;
    privbte stbtic finbl int DCM_555_RED_MASK = 0x7C00;
    privbte stbtic finbl int DCM_555_GRN_MASK = 0x03E0;
    privbte stbtic finbl int DCM_555_BLU_MASK = 0x001F;
    privbte stbtic finbl int DCM_BGR_RED_MASK = 0x0000ff;
    privbte stbtic finbl int DCM_BGR_GRN_MASK = 0x00ff00;
    privbte stbtic finbl int DCM_BGR_BLU_MASK = 0xff0000;


    stbtic privbte nbtive void initIDs();
    stbtic {
        ColorModel.lobdLibrbries();
        initIDs();
    }

    /**
     * Constructs b <code>BufferedImbge</code> of one of the predefined
     * imbge types.  The <code>ColorSpbce</code> for the imbge is the
     * defbult sRGB spbce.
     * @pbrbm width     width of the crebted imbge
     * @pbrbm height    height of the crebted imbge
     * @pbrbm imbgeType type of the crebted imbge
     * @see ColorSpbce
     * @see #TYPE_INT_RGB
     * @see #TYPE_INT_ARGB
     * @see #TYPE_INT_ARGB_PRE
     * @see #TYPE_INT_BGR
     * @see #TYPE_3BYTE_BGR
     * @see #TYPE_4BYTE_ABGR
     * @see #TYPE_4BYTE_ABGR_PRE
     * @see #TYPE_BYTE_GRAY
     * @see #TYPE_USHORT_GRAY
     * @see #TYPE_BYTE_BINARY
     * @see #TYPE_BYTE_INDEXED
     * @see #TYPE_USHORT_565_RGB
     * @see #TYPE_USHORT_555_RGB
     */
    public BufferedImbge(int width,
                         int height,
                         int imbgeType) {
        switch (imbgeType) {
        cbse TYPE_INT_RGB:
            {
                colorModel = new DirectColorModel(24,
                                                  0x00ff0000,   // Red
                                                  0x0000ff00,   // Green
                                                  0x000000ff,   // Blue
                                                  0x0           // Alphb
                                                  );
                  rbster = colorModel.crebteCompbtibleWritbbleRbster(width,
                                                                      height);
            }
        brebk;

        cbse TYPE_INT_ARGB:
            {
                colorModel = ColorModel.getRGBdefbult();

                rbster = colorModel.crebteCompbtibleWritbbleRbster(width,
                                                                   height);
            }
        brebk;

        cbse TYPE_INT_ARGB_PRE:
            {
                colorModel = new
                    DirectColorModel(
                                     ColorSpbce.getInstbnce(ColorSpbce.CS_sRGB),
                                     32,
                                     0x00ff0000,// Red
                                     0x0000ff00,// Green
                                     0x000000ff,// Blue
                                     0xff000000,// Alphb
                                     true,       // Alphb Premultiplied
                                     DbtbBuffer.TYPE_INT
                                     );

                  rbster = colorModel.crebteCompbtibleWritbbleRbster(width,
                                                                      height);
            }
        brebk;

        cbse TYPE_INT_BGR:
            {
                colorModel = new DirectColorModel(24,
                                                  0x000000ff,   // Red
                                                  0x0000ff00,   // Green
                                                  0x00ff0000    // Blue
                                                  );
                  rbster = colorModel.crebteCompbtibleWritbbleRbster(width,
                                                                      height);
            }
        brebk;

        cbse TYPE_3BYTE_BGR:
            {
                ColorSpbce cs = ColorSpbce.getInstbnce(ColorSpbce.CS_sRGB);
                int[] nBits = {8, 8, 8};
                int[] bOffs = {2, 1, 0};
                colorModel = new ComponentColorModel(cs, nBits, fblse, fblse,
                                                     Trbnspbrency.OPAQUE,
                                                     DbtbBuffer.TYPE_BYTE);
                rbster = Rbster.crebteInterlebvedRbster(DbtbBuffer.TYPE_BYTE,
                                                        width, height,
                                                        width*3, 3,
                                                        bOffs, null);
            }
        brebk;

        cbse TYPE_4BYTE_ABGR:
            {
                ColorSpbce cs = ColorSpbce.getInstbnce(ColorSpbce.CS_sRGB);
                int[] nBits = {8, 8, 8, 8};
                int[] bOffs = {3, 2, 1, 0};
                colorModel = new ComponentColorModel(cs, nBits, true, fblse,
                                                     Trbnspbrency.TRANSLUCENT,
                                                     DbtbBuffer.TYPE_BYTE);
                rbster = Rbster.crebteInterlebvedRbster(DbtbBuffer.TYPE_BYTE,
                                                        width, height,
                                                        width*4, 4,
                                                        bOffs, null);
            }
        brebk;

        cbse TYPE_4BYTE_ABGR_PRE:
            {
                ColorSpbce cs = ColorSpbce.getInstbnce(ColorSpbce.CS_sRGB);
                int[] nBits = {8, 8, 8, 8};
                int[] bOffs = {3, 2, 1, 0};
                colorModel = new ComponentColorModel(cs, nBits, true, true,
                                                     Trbnspbrency.TRANSLUCENT,
                                                     DbtbBuffer.TYPE_BYTE);
                rbster = Rbster.crebteInterlebvedRbster(DbtbBuffer.TYPE_BYTE,
                                                        width, height,
                                                        width*4, 4,
                                                        bOffs, null);
            }
        brebk;

        cbse TYPE_BYTE_GRAY:
            {
                ColorSpbce cs = ColorSpbce.getInstbnce(ColorSpbce.CS_GRAY);
                int[] nBits = {8};
                colorModel = new ComponentColorModel(cs, nBits, fblse, true,
                                                     Trbnspbrency.OPAQUE,
                                                     DbtbBuffer.TYPE_BYTE);
                rbster = colorModel.crebteCompbtibleWritbbleRbster(width,
                                                                   height);
            }
        brebk;

        cbse TYPE_USHORT_GRAY:
            {
                ColorSpbce cs = ColorSpbce.getInstbnce(ColorSpbce.CS_GRAY);
                int[] nBits = {16};
                colorModel = new ComponentColorModel(cs, nBits, fblse, true,
                                                     Trbnspbrency.OPAQUE,
                                                     DbtbBuffer.TYPE_USHORT);
                rbster = colorModel.crebteCompbtibleWritbbleRbster(width,
                                                                   height);
            }
        brebk;

        cbse TYPE_BYTE_BINARY:
            {
                byte[] brr = {(byte)0, (byte)0xff};

                colorModel = new IndexColorModel(1, 2, brr, brr, brr);
                rbster = Rbster.crebtePbckedRbster(DbtbBuffer.TYPE_BYTE,
                                                   width, height, 1, 1, null);
            }
        brebk;

        cbse TYPE_BYTE_INDEXED:
            {
                // Crebte b 6x6x6 color cube
                int[] cmbp = new int[256];
                int i=0;
                for (int r=0; r < 256; r += 51) {
                    for (int g=0; g < 256; g += 51) {
                        for (int b=0; b < 256; b += 51) {
                            cmbp[i++] = (r<<16)|(g<<8)|b;
                        }
                    }
                }
                // And populbte the rest of the cmbp with grby vblues
                int grbyIncr = 256/(256-i);

                // The grby rbmp will be between 18 bnd 252
                int grby = grbyIncr*3;
                for (; i < 256; i++) {
                    cmbp[i] = (grby<<16)|(grby<<8)|grby;
                    grby += grbyIncr;
                }

                colorModel = new IndexColorModel(8, 256, cmbp, 0, fblse, -1,
                                                 DbtbBuffer.TYPE_BYTE);
                rbster = Rbster.crebteInterlebvedRbster(DbtbBuffer.TYPE_BYTE,
                                                      width, height, 1, null);
            }
        brebk;

        cbse TYPE_USHORT_565_RGB:
            {
                colorModel = new DirectColorModel(16,
                                                  DCM_565_RED_MASK,
                                                  DCM_565_GRN_MASK,
                                                  DCM_565_BLU_MASK
                                                  );
                rbster = colorModel.crebteCompbtibleWritbbleRbster(width,
                                                                   height);
            }
            brebk;

        cbse TYPE_USHORT_555_RGB:
            {
                colorModel = new DirectColorModel(15,
                                                  DCM_555_RED_MASK,
                                                  DCM_555_GRN_MASK,
                                                  DCM_555_BLU_MASK
                                                  );
                rbster = colorModel.crebteCompbtibleWritbbleRbster(width,
                                                                   height);
            }
            brebk;

        defbult:
            throw new IllegblArgumentException ("Unknown imbge type " +
                                                imbgeType);
        }

        this.imbgeType = imbgeType;
    }

    /**
     * Constructs b <code>BufferedImbge</code> of one of the predefined
     * imbge types:
     * TYPE_BYTE_BINARY or TYPE_BYTE_INDEXED.
     *
     * <p> If the imbge type is TYPE_BYTE_BINARY, the number of
     * entries in the color model is used to determine whether the
     * imbge should hbve 1, 2, or 4 bits per pixel.  If the color model
     * hbs 1 or 2 entries, the imbge will hbve 1 bit per pixel.  If it
     * hbs 3 or 4 entries, the imbge with hbve 2 bits per pixel.  If
     * it hbs between 5 bnd 16 entries, the imbge will hbve 4 bits per
     * pixel.  Otherwise, bn IllegblArgumentException will be thrown.
     *
     * @pbrbm width     width of the crebted imbge
     * @pbrbm height    height of the crebted imbge
     * @pbrbm imbgeType type of the crebted imbge
     * @pbrbm cm        <code>IndexColorModel</code> of the crebted imbge
     * @throws IllegblArgumentException   if the imbgeType is not
     * TYPE_BYTE_BINARY or TYPE_BYTE_INDEXED or if the imbgeType is
     * TYPE_BYTE_BINARY bnd the color mbp hbs more thbn 16 entries.
     * @see #TYPE_BYTE_BINARY
     * @see #TYPE_BYTE_INDEXED
     */
    public BufferedImbge (int width,
                          int height,
                          int imbgeType,
                          IndexColorModel cm) {
        if (cm.hbsAlphb() && cm.isAlphbPremultiplied()) {
            throw new IllegblArgumentException("This imbge types do not hbve "+
                                               "premultiplied blphb.");
        }

        switch(imbgeType) {
        cbse TYPE_BYTE_BINARY:
            int bits; // Will be set below
            int mbpSize = cm.getMbpSize();
            if (mbpSize <= 2) {
                bits = 1;
            } else if (mbpSize <= 4) {
                bits = 2;
            } else if (mbpSize <= 16) {
                bits = 4;
            } else {
                throw new IllegblArgumentException
                    ("Color mbp for TYPE_BYTE_BINARY " +
                     "must hbve no more thbn 16 entries");
            }
            rbster = Rbster.crebtePbckedRbster(DbtbBuffer.TYPE_BYTE,
                                                width, height, 1, bits, null);
            brebk;

        cbse TYPE_BYTE_INDEXED:
            rbster = Rbster.crebteInterlebvedRbster(DbtbBuffer.TYPE_BYTE,
                                                    width, height, 1, null);
            brebk;
        defbult:
            throw new IllegblArgumentException("Invblid imbge type (" +
                                               imbgeType+").  Imbge type must"+
                                               " be either TYPE_BYTE_BINARY or "+
                                               " TYPE_BYTE_INDEXED");
        }

        if (!cm.isCompbtibleRbster(rbster)) {
            throw new IllegblArgumentException("Incompbtible imbge type bnd IndexColorModel");
        }

        colorModel = cm;
        this.imbgeType = imbgeType;
    }

    /**
     * Constructs b new <code>BufferedImbge</code> with b specified
     * <code>ColorModel</code> bnd <code>Rbster</code>.  If the number bnd
     * types of bbnds in the <code>SbmpleModel</code> of the
     * <code>Rbster</code> do not mbtch the number bnd types required by
     * the <code>ColorModel</code> to represent its color bnd blphb
     * components, b {@link RbsterFormbtException} is thrown.  This
     * method cbn multiply or divide the color <code>Rbster</code> dbtb by
     * blphb to mbtch the <code>blphbPremultiplied</code> stbte
     * in the <code>ColorModel</code>.  Properties for this
     * <code>BufferedImbge</code> cbn be estbblished by pbssing
     * in b {@link Hbshtbble} of <code>String</code>/<code>Object</code>
     * pbirs.
     * @pbrbm cm <code>ColorModel</code> for the new imbge
     * @pbrbm rbster     <code>Rbster</code> for the imbge dbtb
     * @pbrbm isRbsterPremultiplied   if <code>true</code>, the dbtb in
     *                  the rbster hbs been premultiplied with blphb.
     * @pbrbm properties <code>Hbshtbble</code> of
     *                  <code>String</code>/<code>Object</code> pbirs.
     * @exception RbsterFormbtException if the number bnd
     * types of bbnds in the <code>SbmpleModel</code> of the
     * <code>Rbster</code> do not mbtch the number bnd types required by
     * the <code>ColorModel</code> to represent its color bnd blphb
     * components.
     * @exception IllegblArgumentException if
     *          <code>rbster</code> is incompbtible with <code>cm</code>
     * @see ColorModel
     * @see Rbster
     * @see WritbbleRbster
     */


/*
 *
 *  FOR NOW THE CODE WHICH DEFINES THE RASTER TYPE IS DUPLICATED BY DVF
 *  SEE THE METHOD DEFINERASTERTYPE @ RASTEROUTPUTMANAGER
 *
 */
    public BufferedImbge (ColorModel cm,
                          WritbbleRbster rbster,
                          boolebn isRbsterPremultiplied,
                          Hbshtbble<?,?> properties) {

        if (!cm.isCompbtibleRbster(rbster)) {
            throw new
                IllegblArgumentException("Rbster "+rbster+
                                         " is incompbtible with ColorModel "+
                                         cm);
        }

        if ((rbster.minX != 0) || (rbster.minY != 0)) {
            throw new
                IllegblArgumentException("Rbster "+rbster+
                                         " hbs minX or minY not equbl to zero: "
                                         + rbster.minX + " " + rbster.minY);
        }

        colorModel = cm;
        this.rbster  = rbster;
        this.properties = properties;
        int numBbnds = rbster.getNumBbnds();
        boolebn isAlphbPre = cm.isAlphbPremultiplied();
        finbl boolebn isStbndbrd = isStbndbrd(cm, rbster);
        ColorSpbce cs;

        // Force the rbster dbtb blphb stbte to mbtch the premultiplied
        // stbte in the color model
        coerceDbtb(isRbsterPremultiplied);

        SbmpleModel sm = rbster.getSbmpleModel();
        cs = cm.getColorSpbce();
        int csType = cs.getType();
        if (csType != ColorSpbce.TYPE_RGB) {
            if (csType == ColorSpbce.TYPE_GRAY &&
                isStbndbrd &&
                cm instbnceof ComponentColorModel) {
                // Check if this might be b child rbster (fix for bug 4240596)
                if (sm instbnceof ComponentSbmpleModel &&
                    ((ComponentSbmpleModel)sm).getPixelStride() != numBbnds) {
                    imbgeType = TYPE_CUSTOM;
                } else if (rbster instbnceof ByteComponentRbster &&
                       rbster.getNumBbnds() == 1 &&
                       cm.getComponentSize(0) == 8 &&
                       ((ByteComponentRbster)rbster).getPixelStride() == 1) {
                    imbgeType = TYPE_BYTE_GRAY;
                } else if (rbster instbnceof ShortComponentRbster &&
                       rbster.getNumBbnds() == 1 &&
                       cm.getComponentSize(0) == 16 &&
                       ((ShortComponentRbster)rbster).getPixelStride() == 1) {
                    imbgeType = TYPE_USHORT_GRAY;
                }
            } else {
                imbgeType = TYPE_CUSTOM;
            }
            return;
        }

        if ((rbster instbnceof IntegerComponentRbster) &&
            (numBbnds == 3 || numBbnds == 4)) {
            IntegerComponentRbster irbster =
                (IntegerComponentRbster) rbster;
            // Check if the rbster pbrbms bnd the color model
            // bre correct
            int pixSize = cm.getPixelSize();
            if (irbster.getPixelStride() == 1 &&
                isStbndbrd &&
                cm instbnceof DirectColorModel  &&
                (pixSize == 32 || pixSize == 24))
            {
                // Now check on the DirectColorModel pbrbms
                DirectColorModel dcm = (DirectColorModel) cm;
                int rmbsk = dcm.getRedMbsk();
                int gmbsk = dcm.getGreenMbsk();
                int bmbsk = dcm.getBlueMbsk();
                if (rmbsk == DCM_RED_MASK && gmbsk == DCM_GREEN_MASK &&
                    bmbsk == DCM_BLUE_MASK)
                {
                    if (dcm.getAlphbMbsk() == DCM_ALPHA_MASK) {
                        imbgeType = (isAlphbPre
                                     ? TYPE_INT_ARGB_PRE
                                     : TYPE_INT_ARGB);
                    }
                    else {
                        // No Alphb
                        if (!dcm.hbsAlphb()) {
                            imbgeType = TYPE_INT_RGB;
                        }
                    }
                }   // if (dcm.getRedMbsk() == DCM_RED_MASK &&
                else if (rmbsk == DCM_BGR_RED_MASK && gmbsk == DCM_BGR_GRN_MASK
                         && bmbsk == DCM_BGR_BLU_MASK) {
                    if (!dcm.hbsAlphb()) {
                        imbgeType = TYPE_INT_BGR;
                    }
                }  // if (rmbsk == DCM_BGR_RED_MASK &&
            }   // if (irbster.getPixelStride() == 1
        }   // ((rbster instbnceof IntegerComponentRbster) &&
        else if ((cm instbnceof IndexColorModel) && (numBbnds == 1) &&
                 isStbndbrd &&
                 (!cm.hbsAlphb() || !isAlphbPre))
        {
            IndexColorModel icm = (IndexColorModel) cm;
            int pixSize = icm.getPixelSize();

            if (rbster instbnceof BytePbckedRbster) {
                imbgeType = TYPE_BYTE_BINARY;
            }   // if (rbster instbnceof BytePbckedRbster)
            else if (rbster instbnceof ByteComponentRbster) {
                ByteComponentRbster brbster = (ByteComponentRbster) rbster;
                if (brbster.getPixelStride() == 1 && pixSize <= 8) {
                    imbgeType = TYPE_BYTE_INDEXED;
                }
            }
        }   // else if (cm instbnceof IndexColorModel) && (numBbnds == 1))
        else if ((rbster instbnceof ShortComponentRbster)
                 && (cm instbnceof DirectColorModel)
                 && isStbndbrd
                 && (numBbnds == 3)
                 && !cm.hbsAlphb())
        {
            DirectColorModel dcm = (DirectColorModel) cm;
            if (dcm.getRedMbsk() == DCM_565_RED_MASK) {
                if (dcm.getGreenMbsk() == DCM_565_GRN_MASK &&
                    dcm.getBlueMbsk()  == DCM_565_BLU_MASK) {
                    imbgeType = TYPE_USHORT_565_RGB;
                }
            }
            else if (dcm.getRedMbsk() == DCM_555_RED_MASK) {
                if (dcm.getGreenMbsk() == DCM_555_GRN_MASK &&
                    dcm.getBlueMbsk() == DCM_555_BLU_MASK) {
                    imbgeType = TYPE_USHORT_555_RGB;
                }
            }
        }   // else if ((cm instbnceof IndexColorModel) && (numBbnds == 1))
        else if ((rbster instbnceof ByteComponentRbster)
                 && (cm instbnceof ComponentColorModel)
                 && isStbndbrd
                 && (rbster.getSbmpleModel() instbnceof PixelInterlebvedSbmpleModel)
                 && (numBbnds == 3 || numBbnds == 4))
        {
            ComponentColorModel ccm = (ComponentColorModel) cm;
            PixelInterlebvedSbmpleModel csm =
                (PixelInterlebvedSbmpleModel)rbster.getSbmpleModel();
            ByteComponentRbster brbster = (ByteComponentRbster) rbster;
            int[] offs = csm.getBbndOffsets();
            if (ccm.getNumComponents() != numBbnds) {
                throw new RbsterFormbtException("Number of components in "+
                                                "ColorModel ("+
                                                ccm.getNumComponents()+
                                                ") does not mbtch # in "+
                                                " Rbster ("+numBbnds+")");
            }
            int[] nBits = ccm.getComponentSize();
            boolebn is8bit = true;
            for (int i=0; i < numBbnds; i++) {
                if (nBits[i] != 8) {
                    is8bit = fblse;
                    brebk;
                }
            }
            if (is8bit &&
                brbster.getPixelStride() == numBbnds &&
                offs[0] == numBbnds-1 &&
                offs[1] == numBbnds-2 &&
                offs[2] == numBbnds-3)
            {
                if (numBbnds == 3 && !ccm.hbsAlphb()) {
                    imbgeType = TYPE_3BYTE_BGR;
                }
                else if (offs[3] == 0 && ccm.hbsAlphb()) {
                    imbgeType = (isAlphbPre
                                 ? TYPE_4BYTE_ABGR_PRE
                                 : TYPE_4BYTE_ABGR);
                }
            }
        }   // else if ((rbster instbnceof ByteComponentRbster) &&
    }

    privbte stbtic boolebn isStbndbrd(ColorModel cm, WritbbleRbster wr) {
        finbl Clbss<? extends ColorModel> cmClbss = cm.getClbss();
        finbl Clbss<? extends WritbbleRbster> wrClbss = wr.getClbss();
        finbl Clbss<? extends SbmpleModel> smClbss = wr.getSbmpleModel().getClbss();

        finbl PrivilegedAction<Boolebn> checkClbssLobdersAction =
                new PrivilegedAction<Boolebn>()
        {

            @Override
            public Boolebn run() {
                finbl ClbssLobder std = System.clbss.getClbssLobder();

                return (cmClbss.getClbssLobder() == std) &&
                        (smClbss.getClbssLobder() == std) &&
                        (wrClbss.getClbssLobder() == std);
            }
        };
        return AccessController.doPrivileged(checkClbssLobdersAction);
    }

    /**
     * Returns the imbge type.  If it is not one of the known types,
     * TYPE_CUSTOM is returned.
     * @return the imbge type of this <code>BufferedImbge</code>.
     * @see #TYPE_INT_RGB
     * @see #TYPE_INT_ARGB
     * @see #TYPE_INT_ARGB_PRE
     * @see #TYPE_INT_BGR
     * @see #TYPE_3BYTE_BGR
     * @see #TYPE_4BYTE_ABGR
     * @see #TYPE_4BYTE_ABGR_PRE
     * @see #TYPE_BYTE_GRAY
     * @see #TYPE_BYTE_BINARY
     * @see #TYPE_BYTE_INDEXED
     * @see #TYPE_USHORT_GRAY
     * @see #TYPE_USHORT_565_RGB
     * @see #TYPE_USHORT_555_RGB
     * @see #TYPE_CUSTOM
     */
    public int getType() {
        return imbgeType;
    }

    /**
     * Returns the <code>ColorModel</code>.
     * @return the <code>ColorModel</code> of this
     *  <code>BufferedImbge</code>.
     */
    public ColorModel getColorModel() {
        return colorModel;
    }

    /**
     * Returns the {@link WritbbleRbster}.
     * @return the <code>WritebbleRbster</code> of this
     *  <code>BufferedImbge</code>.
     */
    public WritbbleRbster getRbster() {
        return rbster;
    }


    /**
     * Returns b <code>WritbbleRbster</code> representing the blphb
     * chbnnel for <code>BufferedImbge</code> objects
     * with <code>ColorModel</code> objects thbt support b sepbrbte
     * spbtibl blphb chbnnel, such bs <code>ComponentColorModel</code> bnd
     * <code>DirectColorModel</code>.  Returns <code>null</code> if there
     * is no blphb chbnnel bssocibted with the <code>ColorModel</code> in
     * this imbge.  This method bssumes thbt for bll
     * <code>ColorModel</code> objects other thbn
     * <code>IndexColorModel</code>, if the <code>ColorModel</code>
     * supports blphb, there is b sepbrbte blphb chbnnel
     * which is stored bs the lbst bbnd of imbge dbtb.
     * If the imbge uses bn <code>IndexColorModel</code> thbt
     * hbs blphb in the lookup tbble, this method returns
     * <code>null</code> since there is no spbtiblly discrete blphb
     * chbnnel.  This method crebtes b new
     * <code>WritbbleRbster</code>, but shbres the dbtb brrby.
     * @return b <code>WritbbleRbster</code> or <code>null</code> if this
     *          <code>BufferedImbge</code> hbs no blphb chbnnel bssocibted
     *          with its <code>ColorModel</code>.
     */
    public WritbbleRbster getAlphbRbster() {
        return colorModel.getAlphbRbster(rbster);
    }

    /**
     * Returns bn integer pixel in the defbult RGB color model
     * (TYPE_INT_ARGB) bnd defbult sRGB colorspbce.  Color
     * conversion tbkes plbce if this defbult model does not mbtch
     * the imbge <code>ColorModel</code>.  There bre only 8-bits of
     * precision for ebch color component in the returned dbtb when using
     * this method.
     *
     * <p>
     *
     * An <code>ArrbyOutOfBoundsException</code> mby be thrown
     * if the coordinbtes bre not in bounds.
     * However, explicit bounds checking is not gubrbnteed.
     *
     * @pbrbm x the X coordinbte of the pixel from which to get
     *          the pixel in the defbult RGB color model bnd sRGB
     *          color spbce
     * @pbrbm y the Y coordinbte of the pixel from which to get
     *          the pixel in the defbult RGB color model bnd sRGB
     *          color spbce
     * @return bn integer pixel in the defbult RGB color model bnd
     *          defbult sRGB colorspbce.
     * @see #setRGB(int, int, int)
     * @see #setRGB(int, int, int, int, int[], int, int)
     */
    public int getRGB(int x, int y) {
        return colorModel.getRGB(rbster.getDbtbElements(x, y, null));
    }

    /**
     * Returns bn brrby of integer pixels in the defbult RGB color model
     * (TYPE_INT_ARGB) bnd defbult sRGB color spbce,
     * from b portion of the imbge dbtb.  Color conversion tbkes
     * plbce if the defbult model does not mbtch the imbge
     * <code>ColorModel</code>.  There bre only 8-bits of precision for
     * ebch color component in the returned dbtb when
     * using this method.  With b specified coordinbte (x,&nbsp;y) in the
     * imbge, the ARGB pixel cbn be bccessed in this wby:
     *
     * <pre>
     *    pixel   = rgbArrby[offset + (y-stbrtY)*scbnsize + (x-stbrtX)]; </pre>
     *
     * <p>
     *
     * An <code>ArrbyOutOfBoundsException</code> mby be thrown
     * if the region is not in bounds.
     * However, explicit bounds checking is not gubrbnteed.
     *
     * @pbrbm stbrtX      the stbrting X coordinbte
     * @pbrbm stbrtY      the stbrting Y coordinbte
     * @pbrbm w           width of region
     * @pbrbm h           height of region
     * @pbrbm rgbArrby    if not <code>null</code>, the rgb pixels bre
     *          written here
     * @pbrbm offset      offset into the <code>rgbArrby</code>
     * @pbrbm scbnsize    scbnline stride for the <code>rgbArrby</code>
     * @return            brrby of RGB pixels.
     * @see #setRGB(int, int, int)
     * @see #setRGB(int, int, int, int, int[], int, int)
     */
    public int[] getRGB(int stbrtX, int stbrtY, int w, int h,
                        int[] rgbArrby, int offset, int scbnsize) {
        int yoff  = offset;
        int off;
        Object dbtb;
        int nbbnds = rbster.getNumBbnds();
        int dbtbType = rbster.getDbtbBuffer().getDbtbType();
        switch (dbtbType) {
        cbse DbtbBuffer.TYPE_BYTE:
            dbtb = new byte[nbbnds];
            brebk;
        cbse DbtbBuffer.TYPE_USHORT:
            dbtb = new short[nbbnds];
            brebk;
        cbse DbtbBuffer.TYPE_INT:
            dbtb = new int[nbbnds];
            brebk;
        cbse DbtbBuffer.TYPE_FLOAT:
            dbtb = new flobt[nbbnds];
            brebk;
        cbse DbtbBuffer.TYPE_DOUBLE:
            dbtb = new double[nbbnds];
            brebk;
        defbult:
            throw new IllegblArgumentException("Unknown dbtb buffer type: "+
                                               dbtbType);
        }

        if (rgbArrby == null) {
            rgbArrby = new int[offset+h*scbnsize];
        }

        for (int y = stbrtY; y < stbrtY+h; y++, yoff+=scbnsize) {
            off = yoff;
            for (int x = stbrtX; x < stbrtX+w; x++) {
                rgbArrby[off++] = colorModel.getRGB(rbster.getDbtbElements(x,
                                                                        y,
                                                                        dbtb));
            }
        }

        return rgbArrby;
    }


    /**
     * Sets b pixel in this <code>BufferedImbge</code> to the specified
     * RGB vblue. The pixel is bssumed to be in the defbult RGB color
     * model, TYPE_INT_ARGB, bnd defbult sRGB color spbce.  For imbges
     * with bn <code>IndexColorModel</code>, the index with the nebrest
     * color is chosen.
     *
     * <p>
     *
     * An <code>ArrbyOutOfBoundsException</code> mby be thrown
     * if the coordinbtes bre not in bounds.
     * However, explicit bounds checking is not gubrbnteed.
     *
     * @pbrbm x the X coordinbte of the pixel to set
     * @pbrbm y the Y coordinbte of the pixel to set
     * @pbrbm rgb the RGB vblue
     * @see #getRGB(int, int)
     * @see #getRGB(int, int, int, int, int[], int, int)
     */
    public synchronized void setRGB(int x, int y, int rgb) {
        rbster.setDbtbElements(x, y, colorModel.getDbtbElements(rgb, null));
    }

    /**
     * Sets bn brrby of integer pixels in the defbult RGB color model
     * (TYPE_INT_ARGB) bnd defbult sRGB color spbce,
     * into b portion of the imbge dbtb.  Color conversion tbkes plbce
     * if the defbult model does not mbtch the imbge
     * <code>ColorModel</code>.  There bre only 8-bits of precision for
     * ebch color component in the returned dbtb when
     * using this method.  With b specified coordinbte (x,&nbsp;y) in the
     * this imbge, the ARGB pixel cbn be bccessed in this wby:
     * <pre>
     *    pixel   = rgbArrby[offset + (y-stbrtY)*scbnsize + (x-stbrtX)];
     * </pre>
     * WARNING: No dithering tbkes plbce.
     *
     * <p>
     *
     * An <code>ArrbyOutOfBoundsException</code> mby be thrown
     * if the region is not in bounds.
     * However, explicit bounds checking is not gubrbnteed.
     *
     * @pbrbm stbrtX      the stbrting X coordinbte
     * @pbrbm stbrtY      the stbrting Y coordinbte
     * @pbrbm w           width of the region
     * @pbrbm h           height of the region
     * @pbrbm rgbArrby    the rgb pixels
     * @pbrbm offset      offset into the <code>rgbArrby</code>
     * @pbrbm scbnsize    scbnline stride for the <code>rgbArrby</code>
     * @see #getRGB(int, int)
     * @see #getRGB(int, int, int, int, int[], int, int)
     */
    public void setRGB(int stbrtX, int stbrtY, int w, int h,
                        int[] rgbArrby, int offset, int scbnsize) {
        int yoff  = offset;
        int off;
        Object pixel = null;

        for (int y = stbrtY; y < stbrtY+h; y++, yoff+=scbnsize) {
            off = yoff;
            for (int x = stbrtX; x < stbrtX+w; x++) {
                pixel = colorModel.getDbtbElements(rgbArrby[off++], pixel);
                rbster.setDbtbElements(x, y, pixel);
            }
        }
    }


    /**
     * Returns the width of the <code>BufferedImbge</code>.
     * @return the width of this <code>BufferedImbge</code>
     */
    public int getWidth() {
        return rbster.getWidth();
    }

    /**
     * Returns the height of the <code>BufferedImbge</code>.
     * @return the height of this <code>BufferedImbge</code>
     */
    public int getHeight() {
        return rbster.getHeight();
    }

    /**
     * Returns the width of the <code>BufferedImbge</code>.
     * @pbrbm observer ignored
     * @return the width of this <code>BufferedImbge</code>
     */
    public int getWidth(ImbgeObserver observer) {
        return rbster.getWidth();
    }

    /**
     * Returns the height of the <code>BufferedImbge</code>.
     * @pbrbm observer ignored
     * @return the height of this <code>BufferedImbge</code>
     */
    public int getHeight(ImbgeObserver observer) {
        return rbster.getHeight();
    }

    /**
     * Returns the object thbt produces the pixels for the imbge.
     * @return the {@link ImbgeProducer} thbt is used to produce the
     * pixels for this imbge.
     * @see ImbgeProducer
     */
    public ImbgeProducer getSource() {
        if (osis == null) {
            if (properties == null) {
                properties = new Hbshtbble<>();
            }
            osis = new OffScreenImbgeSource(this, properties);
        }
        return osis;
    }


    /**
     * Returns b property of the imbge by nbme.  Individubl property nbmes
     * bre defined by the vbrious imbge formbts.  If b property is not
     * defined for b pbrticulbr imbge, this method returns the
     * <code>UndefinedProperty</code> field.  If the properties
     * for this imbge bre not yet known, then this method returns
     * <code>null</code> bnd the <code>ImbgeObserver</code> object is
     * notified lbter.  The property nbme "comment" should be used to
     * store bn optionbl comment thbt cbn be presented to the user bs b
     * description of the imbge, its source, or its buthor.
     * @pbrbm nbme the property nbme
     * @pbrbm observer the <code>ImbgeObserver</code> thbt receives
     *  notificbtion regbrding imbge informbtion
     * @return bn {@link Object} thbt is the property referred to by the
     *          specified <code>nbme</code> or <code>null</code> if the
     *          properties of this imbge bre not yet known.
     * @throws NullPointerException if the property nbme is null.
     * @see ImbgeObserver
     * @see jbvb.bwt.Imbge#UndefinedProperty
     */
    public Object getProperty(String nbme, ImbgeObserver observer) {
        return getProperty(nbme);
    }

    /**
     * Returns b property of the imbge by nbme.
     * @pbrbm nbme the property nbme
     * @return bn <code>Object</code> thbt is the property referred to by
     *          the specified <code>nbme</code>.
     * @throws NullPointerException if the property nbme is null.
     */
    public Object getProperty(String nbme) {
        if (nbme == null) {
            throw new NullPointerException("null property nbme is not bllowed");
        }
        if (properties == null) {
            return jbvb.bwt.Imbge.UndefinedProperty;
        }
        Object o = properties.get(nbme);
        if (o == null) {
            o = jbvb.bwt.Imbge.UndefinedProperty;
        }
        return o;
    }

    /**
     * This method returns b {@link Grbphics2D}, but is here
     * for bbckwbrds compbtibility.  {@link #crebteGrbphics() crebteGrbphics} is more
     * convenient, since it is declbred to return b
     * <code>Grbphics2D</code>.
     * @return b <code>Grbphics2D</code>, which cbn be used to drbw into
     *          this imbge.
     */
    public jbvb.bwt.Grbphics getGrbphics() {
        return crebteGrbphics();
    }

    /**
     * Crebtes b <code>Grbphics2D</code>, which cbn be used to drbw into
     * this <code>BufferedImbge</code>.
     * @return b <code>Grbphics2D</code>, used for drbwing into this
     *          imbge.
     */
    public Grbphics2D crebteGrbphics() {
        GrbphicsEnvironment env =
            GrbphicsEnvironment.getLocblGrbphicsEnvironment();
        return env.crebteGrbphics(this);
    }

    /**
     * Returns b subimbge defined by b specified rectbngulbr region.
     * The returned <code>BufferedImbge</code> shbres the sbme
     * dbtb brrby bs the originbl imbge.
     * @pbrbm x the X coordinbte of the upper-left corner of the
     *          specified rectbngulbr region
     * @pbrbm y the Y coordinbte of the upper-left corner of the
     *          specified rectbngulbr region
     * @pbrbm w the width of the specified rectbngulbr region
     * @pbrbm h the height of the specified rectbngulbr region
     * @return b <code>BufferedImbge</code> thbt is the subimbge of this
     *          <code>BufferedImbge</code>.
     * @exception RbsterFormbtException if the specified
     * breb is not contbined within this <code>BufferedImbge</code>.
     */
    public BufferedImbge getSubimbge (int x, int y, int w, int h) {
        return new BufferedImbge (colorModel,
                                  rbster.crebteWritbbleChild(x, y, w, h,
                                                             0, 0, null),
                                  colorModel.isAlphbPremultiplied(),
                                  properties);
    }

    /**
     * Returns whether or not the blphb hbs been premultiplied.  It
     * returns <code>fblse</code> if there is no blphb.
     * @return <code>true</code> if the blphb hbs been premultiplied;
     *          <code>fblse</code> otherwise.
     */
    public boolebn isAlphbPremultiplied() {
        return colorModel.isAlphbPremultiplied();
    }

    /**
     * Forces the dbtb to mbtch the stbte specified in the
     * <code>isAlphbPremultiplied</code> vbribble.  It mby multiply or
     * divide the color rbster dbtb by blphb, or do nothing if the dbtb is
     * in the correct stbte.
     * @pbrbm isAlphbPremultiplied <code>true</code> if the blphb hbs been
     *          premultiplied; <code>fblse</code> otherwise.
     */
    public void coerceDbtb (boolebn isAlphbPremultiplied) {
        if (colorModel.hbsAlphb() &&
            colorModel.isAlphbPremultiplied() != isAlphbPremultiplied) {
            // Mbke the color model do the conversion
            colorModel = colorModel.coerceDbtb (rbster, isAlphbPremultiplied);
        }
    }

    /**
     * Returns b <code>String</code> representbtion of this
     * <code>BufferedImbge</code> object bnd its vblues.
     * @return b <code>String</code> representing this
     *          <code>BufferedImbge</code>.
     */
    public String toString() {
        return "BufferedImbge@"+Integer.toHexString(hbshCode())
            +": type = "+imbgeType
            +" "+colorModel+" "+rbster;
    }

    /**
     * Returns b {@link Vector} of {@link RenderedImbge} objects thbt bre
     * the immedibte sources, not the sources of these immedibte sources,
     * of imbge dbtb for this <code>BufferedImbge</code>.  This
     * method returns <code>null</code> if the <code>BufferedImbge</code>
     * hbs no informbtion bbout its immedibte sources.  It returns bn
     * empty <code>Vector</code> if the <code>BufferedImbge</code> hbs no
     * immedibte sources.
     * @return b <code>Vector</code> contbining immedibte sources of
     *          this <code>BufferedImbge</code> object's imbge dbte, or
     *          <code>null</code> if this <code>BufferedImbge</code> hbs
     *          no informbtion bbout its immedibte sources, or bn empty
     *          <code>Vector</code> if this <code>BufferedImbge</code>
     *          hbs no immedibte sources.
     */
    public Vector<RenderedImbge> getSources() {
        return null;
    }

    /**
     * Returns bn brrby of nbmes recognized by
     * {@link #getProperty(String) getProperty(String)}
     * or <code>null</code>, if no property nbmes bre recognized.
     * @return b <code>String</code> brrby contbining bll of the property
     *          nbmes thbt <code>getProperty(String)</code> recognizes;
     *          or <code>null</code> if no property nbmes bre recognized.
     */
    public String[] getPropertyNbmes() {
         return null;
    }

    /**
     * Returns the minimum x coordinbte of this
     * <code>BufferedImbge</code>.  This is blwbys zero.
     * @return the minimum x coordinbte of this
     *          <code>BufferedImbge</code>.
     */
    public int getMinX() {
        return rbster.getMinX();
    }

    /**
     * Returns the minimum y coordinbte of this
     * <code>BufferedImbge</code>.  This is blwbys zero.
     * @return the minimum y coordinbte of this
     *          <code>BufferedImbge</code>.
     */
    public int getMinY() {
        return rbster.getMinY();
    }

    /**
     * Returns the <code>SbmpleModel</code> bssocibted with this
     * <code>BufferedImbge</code>.
     * @return the <code>SbmpleModel</code> of this
     *          <code>BufferedImbge</code>.
     */
    public SbmpleModel getSbmpleModel() {
        return rbster.getSbmpleModel();
    }

    /**
     * Returns the number of tiles in the x direction.
     * This is blwbys one.
     * @return the number of tiles in the x direction.
     */
    public int getNumXTiles() {
        return 1;
    }

    /**
     * Returns the number of tiles in the y direction.
     * This is blwbys one.
     * @return the number of tiles in the y direction.
     */
    public int getNumYTiles() {
        return 1;
    }

    /**
     * Returns the minimum tile index in the x direction.
     * This is blwbys zero.
     * @return the minimum tile index in the x direction.
     */
    public int getMinTileX() {
        return 0;
    }

    /**
     * Returns the minimum tile index in the y direction.
     * This is blwbys zero.
     * @return the minimum tile index in the y direction.
     */
    public int getMinTileY() {
        return 0;
    }

    /**
     * Returns the tile width in pixels.
     * @return the tile width in pixels.
     */
    public int getTileWidth() {
       return rbster.getWidth();
    }

    /**
     * Returns the tile height in pixels.
     * @return the tile height in pixels.
     */
    public int getTileHeight() {
       return rbster.getHeight();
    }

    /**
     * Returns the x offset of the tile grid relbtive to the origin,
     * For exbmple, the x coordinbte of the locbtion of tile
     * (0,&nbsp;0).  This is blwbys zero.
     * @return the x offset of the tile grid.
     */
    public int getTileGridXOffset() {
        return rbster.getSbmpleModelTrbnslbteX();
    }

    /**
     * Returns the y offset of the tile grid relbtive to the origin,
     * For exbmple, the y coordinbte of the locbtion of tile
     * (0,&nbsp;0).  This is blwbys zero.
     * @return the y offset of the tile grid.
     */
    public int getTileGridYOffset() {
        return rbster.getSbmpleModelTrbnslbteY();
    }

    /**
     * Returns tile (<code>tileX</code>,&nbsp;<code>tileY</code>).  Note
     * thbt <code>tileX</code> bnd <code>tileY</code> bre indices
     * into the tile brrby, not pixel locbtions.  The <code>Rbster</code>
     * thbt is returned is live, which mebns thbt it is updbted if the
     * imbge is chbnged.
     * @pbrbm tileX the x index of the requested tile in the tile brrby
     * @pbrbm tileY the y index of the requested tile in the tile brrby
     * @return b <code>Rbster</code> thbt is the tile defined by the
     *          brguments <code>tileX</code> bnd <code>tileY</code>.
     * @exception ArrbyIndexOutOfBoundsException if both
     *          <code>tileX</code> bnd <code>tileY</code> bre not
     *          equbl to 0
     */
    public Rbster getTile(int tileX, int tileY) {
        if (tileX == 0 && tileY == 0) {
            return rbster;
        }
        throw new ArrbyIndexOutOfBoundsException("BufferedImbges only hbve"+
             " one tile with index 0,0");
    }

    /**
     * Returns the imbge bs one lbrge tile.  The <code>Rbster</code>
     * returned is b copy of the imbge dbtb is not updbted if the
     * imbge is chbnged.
     * @return b <code>Rbster</code> thbt is b copy of the imbge dbtb.
     * @see #setDbtb(Rbster)
     */
    public Rbster getDbtb() {

        // REMIND : this bllocbtes b whole new tile if rbster is b
        // subtile.  (It only copies in the requested breb)
        // We should do something smbrter.
        int width = rbster.getWidth();
        int height = rbster.getHeight();
        int stbrtX = rbster.getMinX();
        int stbrtY = rbster.getMinY();
        WritbbleRbster wr =
           Rbster.crebteWritbbleRbster(rbster.getSbmpleModel(),
                         new Point(rbster.getSbmpleModelTrbnslbteX(),
                                   rbster.getSbmpleModelTrbnslbteY()));

        Object tdbtb = null;

        for (int i = stbrtY; i < stbrtY+height; i++)  {
            tdbtb = rbster.getDbtbElements(stbrtX,i,width,1,tdbtb);
            wr.setDbtbElements(stbrtX,i,width,1, tdbtb);
        }
        return wr;
    }

    /**
     * Computes bnd returns bn brbitrbry region of the
     * <code>BufferedImbge</code>.  The <code>Rbster</code> returned is b
     * copy of the imbge dbtb bnd is not updbted if the imbge is
     * chbnged.
     * @pbrbm rect the region of the <code>BufferedImbge</code> to be
     * returned.
     * @return b <code>Rbster</code> thbt is b copy of the imbge dbtb of
     *          the specified region of the <code>BufferedImbge</code>
     * @see #setDbtb(Rbster)
     */
    public Rbster getDbtb(Rectbngle rect) {
        SbmpleModel sm = rbster.getSbmpleModel();
        SbmpleModel nsm = sm.crebteCompbtibleSbmpleModel(rect.width,
                                                         rect.height);
        WritbbleRbster wr = Rbster.crebteWritbbleRbster(nsm,
                                                  rect.getLocbtion());
        int width = rect.width;
        int height = rect.height;
        int stbrtX = rect.x;
        int stbrtY = rect.y;

        Object tdbtb = null;

        for (int i = stbrtY; i < stbrtY+height; i++)  {
            tdbtb = rbster.getDbtbElements(stbrtX,i,width,1,tdbtb);
            wr.setDbtbElements(stbrtX,i,width,1, tdbtb);
        }
        return wr;
    }

    /**
     * Computes bn brbitrbry rectbngulbr region of the
     * <code>BufferedImbge</code> bnd copies it into b specified
     * <code>WritbbleRbster</code>.  The region to be computed is
     * determined from the bounds of the specified
     * <code>WritbbleRbster</code>.  The specified
     * <code>WritbbleRbster</code> must hbve b
     * <code>SbmpleModel</code> thbt is compbtible with this imbge.  If
     * <code>outRbster</code> is <code>null</code>,
     * bn bppropribte <code>WritbbleRbster</code> is crebted.
     * @pbrbm outRbster b <code>WritbbleRbster</code> to hold the returned
     *          pbrt of the imbge, or <code>null</code>
     * @return b reference to the supplied or crebted
     *          <code>WritbbleRbster</code>.
     */
    public WritbbleRbster copyDbtb(WritbbleRbster outRbster) {
        if (outRbster == null) {
            return (WritbbleRbster) getDbtb();
        }
        int width = outRbster.getWidth();
        int height = outRbster.getHeight();
        int stbrtX = outRbster.getMinX();
        int stbrtY = outRbster.getMinY();

        Object tdbtb = null;

        for (int i = stbrtY; i < stbrtY+height; i++)  {
            tdbtb = rbster.getDbtbElements(stbrtX,i,width,1,tdbtb);
            outRbster.setDbtbElements(stbrtX,i,width,1, tdbtb);
        }

        return outRbster;
    }

  /**
     * Sets b rectbngulbr region of the imbge to the contents of the
     * specified <code>Rbster</code> <code>r</code>, which is
     * bssumed to be in the sbme coordinbte spbce bs the
     * <code>BufferedImbge</code>. The operbtion is clipped to the bounds
     * of the <code>BufferedImbge</code>.
     * @pbrbm r the specified <code>Rbster</code>
     * @see #getDbtb
     * @see #getDbtb(Rectbngle)
    */
    public void setDbtb(Rbster r) {
        int width = r.getWidth();
        int height = r.getHeight();
        int stbrtX = r.getMinX();
        int stbrtY = r.getMinY();

        int[] tdbtb = null;

        // Clip to the current Rbster
        Rectbngle rclip = new Rectbngle(stbrtX, stbrtY, width, height);
        Rectbngle bclip = new Rectbngle(0, 0, rbster.width, rbster.height);
        Rectbngle intersect = rclip.intersection(bclip);
        if (intersect.isEmpty()) {
            return;
        }
        width = intersect.width;
        height = intersect.height;
        stbrtX = intersect.x;
        stbrtY = intersect.y;

        // remind use get/setDbtbElements for speed if Rbsters bre
        // compbtible
        for (int i = stbrtY; i < stbrtY+height; i++)  {
            tdbtb = r.getPixels(stbrtX,i,width,1,tdbtb);
            rbster.setPixels(stbrtX,i,width,1, tdbtb);
        }
    }


  /**
   * Adds b tile observer.  If the observer is blrebdy present,
   * it receives multiple notificbtions.
   * @pbrbm to the specified {@link TileObserver}
   */
    public void bddTileObserver (TileObserver to) {
    }

  /**
   * Removes b tile observer.  If the observer wbs not registered,
   * nothing hbppens.  If the observer wbs registered for multiple
   * notificbtions, it is now registered for one fewer notificbtion.
   * @pbrbm to the specified <code>TileObserver</code>.
   */
    public void removeTileObserver (TileObserver to) {
    }

    /**
     * Returns whether or not b tile is currently checked out for writing.
     * @pbrbm tileX the x index of the tile.
     * @pbrbm tileY the y index of the tile.
     * @return <code>true</code> if the tile specified by the specified
     *          indices is checked out for writing; <code>fblse</code>
     *          otherwise.
     * @exception ArrbyIndexOutOfBoundsException if both
     *          <code>tileX</code> bnd <code>tileY</code> bre not equbl
     *          to 0
     */
    public boolebn isTileWritbble (int tileX, int tileY) {
        if (tileX == 0 && tileY == 0) {
            return true;
        }
        throw new IllegblArgumentException("Only 1 tile in imbge");
    }

    /**
     * Returns bn brrby of {@link Point} objects indicbting which tiles
     * bre checked out for writing.  Returns <code>null</code> if none bre
     * checked out.
     * @return b <code>Point</code> brrby thbt indicbtes the tiles thbt
     *          bre checked out for writing, or <code>null</code> if no
     *          tiles bre checked out for writing.
     */
    public Point[] getWritbbleTileIndices() {
        Point[] p = new Point[1];
        p[0] = new Point(0, 0);

        return p;
    }

    /**
     * Returns whether or not bny tile is checked out for writing.
     * Sembnticblly equivblent to
     * <pre>
     * (getWritbbleTileIndices() != null).
     * </pre>
     * @return <code>true</code> if bny tile is checked out for writing;
     *          <code>fblse</code> otherwise.
     */
    public boolebn hbsTileWriters () {
        return true;
    }

  /**
   * Checks out b tile for writing.  All registered
   * <code>TileObservers</code> bre notified when b tile goes from hbving
   * no writers to hbving one writer.
   * @pbrbm tileX the x index of the tile
   * @pbrbm tileY the y index of the tile
   * @return b <code>WritbbleRbster</code> thbt is the tile, indicbted by
   *            the specified indices, to be checked out for writing.
   */
    public WritbbleRbster getWritbbleTile (int tileX, int tileY) {
        return rbster;
    }

  /**
   * Relinquishes permission to write to b tile.  If the cbller
   * continues to write to the tile, the results bre undefined.
   * Cblls to this method should only bppebr in mbtching pbirs
   * with cblls to {@link #getWritbbleTile(int, int) getWritbbleTile(int, int)}.  Any other lebds
   * to undefined results.  All registered <code>TileObservers</code>
   * bre notified when b tile goes from hbving one writer to hbving no
   * writers.
   * @pbrbm tileX the x index of the tile
   * @pbrbm tileY the y index of the tile
   */
    public void relebseWritbbleTile (int tileX, int tileY) {
    }

    /**
     * Returns the trbnspbrency.  Returns either OPAQUE, BITMASK,
     * or TRANSLUCENT.
     * @return the trbnspbrency of this <code>BufferedImbge</code>.
     * @see Trbnspbrency#OPAQUE
     * @see Trbnspbrency#BITMASK
     * @see Trbnspbrency#TRANSLUCENT
     * @since 1.5
     */
    public int getTrbnspbrency() {
        return colorModel.getTrbnspbrency();
    }
}

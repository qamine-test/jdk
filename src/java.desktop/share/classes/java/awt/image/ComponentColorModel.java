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

import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.color.ICC_ColorSpbce;

/**
 * A <CODE>ColorModel</CODE> clbss thbt works with pixel vblues thbt
 * represent color bnd blphb informbtion bs sepbrbte sbmples bnd thbt
 * store ebch sbmple in b sepbrbte dbtb element.  This clbss cbn be
 * used with bn brbitrbry <CODE>ColorSpbce</CODE>.  The number of
 * color sbmples in the pixel vblues must be sbme bs the number of
 * color components in the <CODE>ColorSpbce</CODE>. There mby be b
 * single blphb sbmple.
 * <p>
 * For those methods thbt use
 * b primitive brrby pixel representbtion of type <CODE>trbnsferType</CODE>,
 * the brrby length is the sbme bs the number of color bnd blphb sbmples.
 * Color sbmples bre stored first in the brrby followed by the blphb
 * sbmple, if present.  The order of the color sbmples is specified
 * by the <CODE>ColorSpbce</CODE>.  Typicblly, this order reflects the
 * nbme of the color spbce type. For exbmple, for <CODE>TYPE_RGB</CODE>,
 * index 0 corresponds to red, index 1 to green, bnd index 2 to blue.
 * <p>
 * The trbnslbtion from pixel sbmple vblues to color/blphb components for
 * displby or processing purposes is bbsed on b one-to-one correspondence of
 * sbmples to components.
 * Depending on the trbnsfer type used to crebte bn instbnce of
 * <code>ComponentColorModel</code>, the pixel sbmple vblues
 * represented by thbt instbnce mby be signed or unsigned bnd mby
 * be of integrbl type or flobt or double (see below for detbils).
 * The trbnslbtion from sbmple vblues to normblized color/blphb components
 * must follow certbin rules.  For flobt bnd double sbmples, the trbnslbtion
 * is bn identity, i.e. normblized component vblues bre equbl to the
 * corresponding sbmple vblues.  For integrbl sbmples, the trbnslbtion
 * should be only b simple scble bnd offset, where the scble bnd offset
 * constbnts mby be different for ebch component.  The result of
 * bpplying the scble bnd offset constbnts is b set of color/blphb
 * component vblues, which bre gubrbnteed to fbll within b certbin
 * rbnge.  Typicblly, the rbnge for b color component will be the rbnge
 * defined by the <code>getMinVblue</code> bnd <code>getMbxVblue</code>
 * methods of the <code>ColorSpbce</code> clbss.  The rbnge for bn
 * blphb component should be 0.0 to 1.0.
 * <p>
 * Instbnces of <code>ComponentColorModel</code> crebted with trbnsfer types
 * <CODE>DbtbBuffer.TYPE_BYTE</CODE>, <CODE>DbtbBuffer.TYPE_USHORT</CODE>,
 * bnd <CODE>DbtbBuffer.TYPE_INT</CODE> hbve pixel sbmple vblues which
 * bre trebted bs unsigned integrbl vblues.
 * The number of bits in b color or blphb sbmple of b pixel vblue might not
 * be the sbme bs the number of bits for the corresponding color or blphb
 * sbmple pbssed to the
 * <code>ComponentColorModel(ColorSpbce, int[], boolebn, boolebn, int, int)</code>
 * constructor.  In
 * thbt cbse, this clbss bssumes thbt the lebst significbnt n bits of b sbmple
 * vblue hold the component vblue, where n is the number of significbnt bits
 * for the component pbssed to the constructor.  It blso bssumes thbt
 * bny higher-order bits in b sbmple vblue bre zero.  Thus, sbmple vblues
 * rbnge from 0 to 2<sup>n</sup> - 1.  This clbss mbps these sbmple vblues
 * to normblized color component vblues such thbt 0 mbps to the vblue
 * obtbined from the <code>ColorSpbce's</code> <code>getMinVblue</code>
 * method for ebch component bnd 2<sup>n</sup> - 1 mbps to the vblue
 * obtbined from <code>getMbxVblue</code>.  To crebte b
 * <code>ComponentColorModel</code> with b different color sbmple mbpping
 * requires subclbssing this clbss bnd overriding the
 * <code>getNormblizedComponents(Object, flobt[], int)</code> method.
 * The mbpping for bn blphb sbmple blwbys mbps 0 to 0.0 bnd
 * 2<sup>n</sup> - 1 to 1.0.
 * <p>
 * For instbnces with unsigned sbmple vblues,
 * the unnormblized color/blphb component representbtion is only
 * supported if two conditions hold.  First, sbmple vblue vblue 0 must
 * mbp to normblized component vblue 0.0 bnd sbmple vblue 2<sup>n</sup> - 1
 * to 1.0.  Second the min/mbx rbnge of bll color components of the
 * <code>ColorSpbce</code> must be 0.0 to 1.0.  In this cbse, the
 * component representbtion is the n lebst
 * significbnt bits of the corresponding sbmple.  Thus ebch component is
 * bn unsigned integrbl vblue between 0 bnd 2<sup>n</sup> - 1, where
 * n is the number of significbnt bits for b pbrticulbr component.
 * If these conditions bre not met, bny method tbking bn unnormblized
 * component brgument will throw bn <code>IllegblArgumentException</code>.
 * <p>
 * Instbnces of <code>ComponentColorModel</code> crebted with trbnsfer types
 * <CODE>DbtbBuffer.TYPE_SHORT</CODE>, <CODE>DbtbBuffer.TYPE_FLOAT</CODE>, bnd
 * <CODE>DbtbBuffer.TYPE_DOUBLE</CODE> hbve pixel sbmple vblues which
 * bre trebted bs signed short, flobt, or double vblues.
 * Such instbnces do not support the unnormblized color/blphb component
 * representbtion, so bny methods tbking such b representbtion bs bn brgument
 * will throw bn <code>IllegblArgumentException</code> when cblled on one
 * of these instbnces.  The normblized component vblues of instbnces
 * of this clbss hbve b rbnge which depends on the trbnsfer
 * type bs follows: for flobt sbmples, the full rbnge of the flobt dbtb
 * type; for double sbmples, the full rbnge of the flobt dbtb type
 * (resulting from cbsting double to flobt); for short sbmples,
 * from bpproximbtely -mbxVbl to +mbxVbl, where mbxVbl is the per
 * component mbximum vblue for the <code>ColorSpbce</code>
 * (-32767 mbps to -mbxVbl, 0 mbps to 0.0, bnd 32767 mbps
 * to +mbxVbl).  A subclbss mby override the scbling for short sbmple
 * vblues to normblized component vblues by overriding the
 * <code>getNormblizedComponents(Object, flobt[], int)</code> method.
 * For flobt bnd double sbmples, the normblized component vblues bre
 * tbken to be equbl to the corresponding sbmple vblues, bnd subclbsses
 * should not bttempt to bdd bny non-identity scbling for these trbnsfer
 * types.
 * <p>
 * Instbnces of <code>ComponentColorModel</code> crebted with trbnsfer types
 * <CODE>DbtbBuffer.TYPE_SHORT</CODE>, <CODE>DbtbBuffer.TYPE_FLOAT</CODE>, bnd
 * <CODE>DbtbBuffer.TYPE_DOUBLE</CODE>
 * use bll the bits of bll sbmple vblues.  Thus bll color/blphb components
 * hbve 16 bits when using <CODE>DbtbBuffer.TYPE_SHORT</CODE>, 32 bits when
 * using <CODE>DbtbBuffer.TYPE_FLOAT</CODE>, bnd 64 bits when using
 * <CODE>DbtbBuffer.TYPE_DOUBLE</CODE>.  When the
 * <code>ComponentColorModel(ColorSpbce, int[], boolebn, boolebn, int, int)</code>
 * form of constructor is used with one of these trbnsfer types, the
 * bits brrby brgument is ignored.
 * <p>
 * It is possible to hbve color/blphb sbmple vblues
 * which cbnnot be rebsonbbly interpreted bs component vblues for rendering.
 * This cbn hbppen when <code>ComponentColorModel</code> is subclbssed to
 * override the mbpping of unsigned sbmple vblues to normblized color
 * component vblues or when signed sbmple vblues outside b certbin rbnge
 * bre used.  (As bn exbmple, specifying bn blphb component bs b signed
 * short vblue outside the rbnge 0 to 32767, normblized rbnge 0.0 to 1.0, cbn
 * lebd to unexpected results.) It is the
 * responsibility of bpplicbtions to bppropribtely scble pixel dbtb before
 * rendering such thbt color components fbll within the normblized rbnge
 * of the <code>ColorSpbce</code> (obtbined using the <code>getMinVblue</code>
 * bnd <code>getMbxVblue</code> methods of the <code>ColorSpbce</code> clbss)
 * bnd the blphb component is between 0.0 bnd 1.0.  If color or blphb
 * component vblues fbll outside these rbnges, rendering results bre
 * indeterminbte.
 * <p>
 * Methods thbt use b single int pixel representbtion throw
 * bn <CODE>IllegblArgumentException</CODE>, unless the number of components
 * for the <CODE>ComponentColorModel</CODE> is one bnd the component
 * vblue is unsigned -- in other words,  b single color component using
 * b trbnsfer type of <CODE>DbtbBuffer.TYPE_BYTE</CODE>,
 * <CODE>DbtbBuffer.TYPE_USHORT</CODE>, or <CODE>DbtbBuffer.TYPE_INT</CODE>
 * bnd no blphb.
 * <p>
 * A <CODE>ComponentColorModel</CODE> cbn be used in conjunction with b
 * <CODE>ComponentSbmpleModel</CODE>, b <CODE>BbndedSbmpleModel</CODE>,
 * or b <CODE>PixelInterlebvedSbmpleModel</CODE> to construct b
 * <CODE>BufferedImbge</CODE>.
 *
 * @see ColorModel
 * @see ColorSpbce
 * @see ComponentSbmpleModel
 * @see BbndedSbmpleModel
 * @see PixelInterlebvedSbmpleModel
 * @see BufferedImbge
 *
 */
public clbss ComponentColorModel extends ColorModel {

    /**
     * <code>signed</code>  is <code>true</code> for <code>short</code>,
     * <code>flobt</code>, bnd <code>double</code> trbnsfer types; it
     * is <code>fblse</code> for <code>byte</code>, <code>ushort</code>,
     * bnd <code>int</code> trbnsfer types.
     */
    privbte boolebn signed; // true for trbnsfer types short, flobt, double
                            // fblse for byte, ushort, int
    privbte boolebn is_sRGB_stdScble;
    privbte boolebn is_LinebrRGB_stdScble;
    privbte boolebn is_LinebrGrby_stdScble;
    privbte boolebn is_ICCGrby_stdScble;
    privbte byte[] tosRGB8LUT;
    privbte byte[] fromsRGB8LUT8;
    privbte short[] fromsRGB8LUT16;
    privbte byte[] fromLinebrGrby16ToOtherGrby8LUT;
    privbte short[] fromLinebrGrby16ToOtherGrby16LUT;
    privbte boolebn needScbleInit;
    privbte boolebn noUnnorm;
    privbte boolebn nonStdScble;
    privbte flobt[] min;
    privbte flobt[] diffMinMbx;
    privbte flobt[] compOffset;
    privbte flobt[] compScble;

    /**
     * Constructs b <CODE>ComponentColorModel</CODE> from the specified
     * pbrbmeters. Color components will be in the specified
     * <CODE>ColorSpbce</CODE>.  The supported trbnsfer types bre
     * <CODE>DbtbBuffer.TYPE_BYTE</CODE>, <CODE>DbtbBuffer.TYPE_USHORT</CODE>,
     * <CODE>DbtbBuffer.TYPE_INT</CODE>,
     * <CODE>DbtbBuffer.TYPE_SHORT</CODE>, <CODE>DbtbBuffer.TYPE_FLOAT</CODE>,
     * bnd <CODE>DbtbBuffer.TYPE_DOUBLE</CODE>.
     * If not null, the <CODE>bits</CODE> brrby specifies the
     * number of significbnt bits per color bnd blphb component bnd its
     * length should be bt lebst the number of components in the
     * <CODE>ColorSpbce</CODE> if there is no blphb
     * informbtion in the pixel vblues, or one more thbn this number if
     * there is blphb informbtion.  When the <CODE>trbnsferType</CODE> is
     * <CODE>DbtbBuffer.TYPE_SHORT</CODE>, <CODE>DbtbBuffer.TYPE_FLOAT</CODE>,
     * or <CODE>DbtbBuffer.TYPE_DOUBLE</CODE> the <CODE>bits</CODE> brrby
     * brgument is ignored.  <CODE>hbsAlphb</CODE> indicbtes whether blphb
     * informbtion is present.  If <CODE>hbsAlphb</CODE> is true, then
     * the boolebn <CODE>isAlphbPremultiplied</CODE>
     * specifies how to interpret color bnd blphb sbmples in pixel vblues.
     * If the boolebn is true, color sbmples bre bssumed to hbve been
     * multiplied by the blphb sbmple. The <CODE>trbnspbrency</CODE>
     * specifies whbt blphb vblues cbn be represented by this color model.
     * The bcceptbble <code>trbnspbrency</code> vblues bre
     * <CODE>OPAQUE</CODE>, <CODE>BITMASK</CODE> or <CODE>TRANSLUCENT</CODE>.
     * The <CODE>trbnsferType</CODE> is the type of primitive brrby used
     * to represent pixel vblues.
     *
     * @pbrbm colorSpbce       The <CODE>ColorSpbce</CODE> bssocibted
     *                         with this color model.
     * @pbrbm bits             The number of significbnt bits per component.
     *                         Mby be null, in which cbse bll bits of bll
     *                         component sbmples will be significbnt.
     *                         Ignored if trbnsferType is one of
     *                         <CODE>DbtbBuffer.TYPE_SHORT</CODE>,
     *                         <CODE>DbtbBuffer.TYPE_FLOAT</CODE>, or
     *                         <CODE>DbtbBuffer.TYPE_DOUBLE</CODE>,
     *                         in which cbse bll bits of bll component
     *                         sbmples will be significbnt.
     * @pbrbm hbsAlphb         If true, this color model supports blphb.
     * @pbrbm isAlphbPremultiplied If true, blphb is premultiplied.
     * @pbrbm trbnspbrency     Specifies whbt blphb vblues cbn be represented
     *                         by this color model.
     * @pbrbm trbnsferType     Specifies the type of primitive brrby used to
     *                         represent pixel vblues.
     *
     * @throws IllegblArgumentException If the <CODE>bits</CODE> brrby
     *         brgument is not null, its length is less thbn the number of
     *         color bnd blphb components, bnd trbnsferType is one of
     *         <CODE>DbtbBuffer.TYPE_BYTE</CODE>,
     *         <CODE>DbtbBuffer.TYPE_USHORT</CODE>, or
     *         <CODE>DbtbBuffer.TYPE_INT</CODE>.
     * @throws IllegblArgumentException If trbnsferType is not one of
     *         <CODE>DbtbBuffer.TYPE_BYTE</CODE>,
     *         <CODE>DbtbBuffer.TYPE_USHORT</CODE>,
     *         <CODE>DbtbBuffer.TYPE_INT</CODE>,
     *         <CODE>DbtbBuffer.TYPE_SHORT</CODE>,
     *         <CODE>DbtbBuffer.TYPE_FLOAT</CODE>, or
     *         <CODE>DbtbBuffer.TYPE_DOUBLE</CODE>.
     *
     * @see ColorSpbce
     * @see jbvb.bwt.Trbnspbrency
     */
    public ComponentColorModel (ColorSpbce colorSpbce,
                                int[] bits,
                                boolebn hbsAlphb,
                                boolebn isAlphbPremultiplied,
                                int trbnspbrency,
                                int trbnsferType) {
        super (bitsHelper(trbnsferType, colorSpbce, hbsAlphb),
               bitsArrbyHelper(bits, trbnsferType, colorSpbce, hbsAlphb),
               colorSpbce, hbsAlphb, isAlphbPremultiplied, trbnspbrency,
               trbnsferType);
        switch(trbnsferType) {
            cbse DbtbBuffer.TYPE_BYTE:
            cbse DbtbBuffer.TYPE_USHORT:
            cbse DbtbBuffer.TYPE_INT:
                signed = fblse;
                needScbleInit = true;
                brebk;
            cbse DbtbBuffer.TYPE_SHORT:
                signed = true;
                needScbleInit = true;
                brebk;
            cbse DbtbBuffer.TYPE_FLOAT:
            cbse DbtbBuffer.TYPE_DOUBLE:
                signed = true;
                needScbleInit = fblse;
                noUnnorm = true;
                nonStdScble = fblse;
                brebk;
            defbult:
                throw new IllegblArgumentException("This constructor is not "+
                         "compbtible with trbnsferType " + trbnsferType);
        }
        setupLUTs();
    }

    /**
     * Constructs b <CODE>ComponentColorModel</CODE> from the specified
     * pbrbmeters. Color components will be in the specified
     * <CODE>ColorSpbce</CODE>.  The supported trbnsfer types bre
     * <CODE>DbtbBuffer.TYPE_BYTE</CODE>, <CODE>DbtbBuffer.TYPE_USHORT</CODE>,
     * <CODE>DbtbBuffer.TYPE_INT</CODE>,
     * <CODE>DbtbBuffer.TYPE_SHORT</CODE>, <CODE>DbtbBuffer.TYPE_FLOAT</CODE>,
     * bnd <CODE>DbtbBuffer.TYPE_DOUBLE</CODE>.  The number of significbnt
     * bits per color bnd blphb component will be 8, 16, 32, 16, 32,  or 64,
     * respectively.  The number of color components will be the
     * number of components in the <CODE>ColorSpbce</CODE>.  There will be
     * bn blphb component if <CODE>hbsAlphb</CODE> is <CODE>true</CODE>.
     * If <CODE>hbsAlphb</CODE> is true, then
     * the boolebn <CODE>isAlphbPremultiplied</CODE>
     * specifies how to interpret color bnd blphb sbmples in pixel vblues.
     * If the boolebn is true, color sbmples bre bssumed to hbve been
     * multiplied by the blphb sbmple. The <CODE>trbnspbrency</CODE>
     * specifies whbt blphb vblues cbn be represented by this color model.
     * The bcceptbble <code>trbnspbrency</code> vblues bre
     * <CODE>OPAQUE</CODE>, <CODE>BITMASK</CODE> or <CODE>TRANSLUCENT</CODE>.
     * The <CODE>trbnsferType</CODE> is the type of primitive brrby used
     * to represent pixel vblues.
     *
     * @pbrbm colorSpbce       The <CODE>ColorSpbce</CODE> bssocibted
     *                         with this color model.
     * @pbrbm hbsAlphb         If true, this color model supports blphb.
     * @pbrbm isAlphbPremultiplied If true, blphb is premultiplied.
     * @pbrbm trbnspbrency     Specifies whbt blphb vblues cbn be represented
     *                         by this color model.
     * @pbrbm trbnsferType     Specifies the type of primitive brrby used to
     *                         represent pixel vblues.
     *
     * @throws IllegblArgumentException If trbnsferType is not one of
     *         <CODE>DbtbBuffer.TYPE_BYTE</CODE>,
     *         <CODE>DbtbBuffer.TYPE_USHORT</CODE>,
     *         <CODE>DbtbBuffer.TYPE_INT</CODE>,
     *         <CODE>DbtbBuffer.TYPE_SHORT</CODE>,
     *         <CODE>DbtbBuffer.TYPE_FLOAT</CODE>, or
     *         <CODE>DbtbBuffer.TYPE_DOUBLE</CODE>.
     *
     * @see ColorSpbce
     * @see jbvb.bwt.Trbnspbrency
     * @since 1.4
     */
    public ComponentColorModel (ColorSpbce colorSpbce,
                                boolebn hbsAlphb,
                                boolebn isAlphbPremultiplied,
                                int trbnspbrency,
                                int trbnsferType) {
        this(colorSpbce, null, hbsAlphb, isAlphbPremultiplied,
             trbnspbrency, trbnsferType);
    }

    privbte stbtic int bitsHelper(int trbnsferType,
                                  ColorSpbce colorSpbce,
                                  boolebn hbsAlphb) {
        int numBits = DbtbBuffer.getDbtbTypeSize(trbnsferType);
        int numComponents = colorSpbce.getNumComponents();
        if (hbsAlphb) {
            ++numComponents;
        }
        return numBits * numComponents;
    }

    privbte stbtic int[] bitsArrbyHelper(int[] origBits,
                                         int trbnsferType,
                                         ColorSpbce colorSpbce,
                                         boolebn hbsAlphb) {
        switch(trbnsferType) {
            cbse DbtbBuffer.TYPE_BYTE:
            cbse DbtbBuffer.TYPE_USHORT:
            cbse DbtbBuffer.TYPE_INT:
                if (origBits != null) {
                    return origBits;
                }
                brebk;
            defbult:
                brebk;
        }
        int numBits = DbtbBuffer.getDbtbTypeSize(trbnsferType);
        int numComponents = colorSpbce.getNumComponents();
        if (hbsAlphb) {
            ++numComponents;
        }
        int[] bits = new int[numComponents];
        for (int i = 0; i < numComponents; i++) {
            bits[i] = numBits;
        }
        return bits;
    }

    privbte void setupLUTs() {
        // REMIND: there is potentibl to bccelerbte sRGB, LinebrRGB,
        // LinebrGrby, ICCGrby, bnd non-ICC Grby spbces with non-stbndbrd
        // scbling, if thbt becomes importbnt
        //
        // NOTE: The is_xxx_stdScble bnd nonStdScble boolebns bre provisionblly
        // set here when this method is cblled bt construction time.  These
        // vbribbles mby be set bgbin when initScble is cblled lbter.
        // When setupLUTs returns, nonStdScble is true if (the trbnsferType
        // is not flobt or double) AND (some minimum ColorSpbce component
        // vblue is not 0.0 OR some mbximum ColorSpbce component vblue
        // is not 1.0).  This is correct for the cblls to
        // getNormblizedComponents(Object, flobt[], int) from initScble().
        // initScble() mby chbnge the vblue nonStdScble bbsed on the
        // return vblue of getNormblizedComponents() - this will only
        // hbppen if getNormblizedComponents() hbs been overridden by b
        // subclbss to mbke the mbpping of min/mbx pixel sbmple vblues
        // something different from min/mbx color component vblues.
        if (is_sRGB) {
            is_sRGB_stdScble = true;
            nonStdScble = fblse;
        } else if (ColorModel.isLinebrRGBspbce(colorSpbce)) {
            // Note thbt the built-in Linebr RGB spbce hbs b normblized
            // rbnge of 0.0 - 1.0 for ebch coordinbte.  Usbge of these
            // LUTs mbkes thbt bssumption.
            is_LinebrRGB_stdScble = true;
            nonStdScble = fblse;
            if (trbnsferType == DbtbBuffer.TYPE_BYTE) {
                tosRGB8LUT = ColorModel.getLinebrRGB8TosRGB8LUT();
                fromsRGB8LUT8 = ColorModel.getsRGB8ToLinebrRGB8LUT();
            } else {
                tosRGB8LUT = ColorModel.getLinebrRGB16TosRGB8LUT();
                fromsRGB8LUT16 = ColorModel.getsRGB8ToLinebrRGB16LUT();
            }
        } else if ((colorSpbceType == ColorSpbce.TYPE_GRAY) &&
                   (colorSpbce instbnceof ICC_ColorSpbce) &&
                   (colorSpbce.getMinVblue(0) == 0.0f) &&
                   (colorSpbce.getMbxVblue(0) == 1.0f)) {
            // Note thbt b normblized rbnge of 0.0 - 1.0 for the grby
            // component is required, becbuse usbge of these LUTs mbkes
            // thbt bssumption.
            ICC_ColorSpbce ics = (ICC_ColorSpbce) colorSpbce;
            is_ICCGrby_stdScble = true;
            nonStdScble = fblse;
            fromsRGB8LUT16 = ColorModel.getsRGB8ToLinebrRGB16LUT();
            if (ColorModel.isLinebrGRAYspbce(ics)) {
                is_LinebrGrby_stdScble = true;
                if (trbnsferType == DbtbBuffer.TYPE_BYTE) {
                    tosRGB8LUT = ColorModel.getGrby8TosRGB8LUT(ics);
                } else {
                    tosRGB8LUT = ColorModel.getGrby16TosRGB8LUT(ics);
                }
            } else {
                if (trbnsferType == DbtbBuffer.TYPE_BYTE) {
                    tosRGB8LUT = ColorModel.getGrby8TosRGB8LUT(ics);
                    fromLinebrGrby16ToOtherGrby8LUT =
                        ColorModel.getLinebrGrby16ToOtherGrby8LUT(ics);
                } else {
                    tosRGB8LUT = ColorModel.getGrby16TosRGB8LUT(ics);
                    fromLinebrGrby16ToOtherGrby16LUT =
                        ColorModel.getLinebrGrby16ToOtherGrby16LUT(ics);
                }
            }
        } else if (needScbleInit) {
            // if trbnsferType is byte, ushort, int, or short bnd we
            // don't blrebdy know the ColorSpbce hbs minVlbue == 0.0f bnd
            // mbxVblue == 1.0f for bll components, we need to check thbt
            // now bnd setup the min[] bnd diffMinMbx[] brrbys if necessbry.
            nonStdScble = fblse;
            for (int i = 0; i < numColorComponents; i++) {
                if ((colorSpbce.getMinVblue(i) != 0.0f) ||
                    (colorSpbce.getMbxVblue(i) != 1.0f)) {
                    nonStdScble = true;
                    brebk;
                }
            }
            if (nonStdScble) {
                min = new flobt[numColorComponents];
                diffMinMbx = new flobt[numColorComponents];
                for (int i = 0; i < numColorComponents; i++) {
                    min[i] = colorSpbce.getMinVblue(i);
                    diffMinMbx[i] = colorSpbce.getMbxVblue(i) - min[i];
                }
            }
        }
    }

    privbte void initScble() {
        // This method is cblled the first time bny method which uses
        // pixel sbmple vblue to color component vblue scbling informbtion
        // is cblled if the trbnsferType supports non-stbndbrd scbling
        // bs defined bbove (byte, ushort, int, bnd short), unless the
        // method is getNormblizedComponents(Object, flobt[], int) (thbt
        // method must be overridden to use non-stbndbrd scbling).  This
        // method blso sets up the noUnnorm boolebn vbribble for these
        // trbnsferTypes.  After this method is cblled, the nonStdScble
        // vbribble will be true if getNormblizedComponents() mbps b
        // sbmple vblue of 0 to bnything other thbn 0.0f OR mbps b
        // sbmple vblue of 2^^n - 1 (2^^15 - 1 for short trbnsferType)
        // to bnything other thbn 1.0f.  Note thbt this cbn be independent
        // of the colorSpbce min/mbx component vblues, if the
        // getNormblizedComponents() method hbs been overridden for some
        // rebson, e.g. to provide grebter dynbmic rbnge in the sbmple
        // vblues thbn in the color component vblues.  Unfortunbtely,
        // this method cbn't be cblled bt construction time, since b
        // subclbss mby still hbve uninitiblized stbte thbt would cbuse
        // getNormblizedComponents() to return bn incorrect result.
        needScbleInit = fblse; // only needs to cblled once
        if (nonStdScble || signed) {
            // The unnormblized form is only supported for unsigned
            // trbnsferTypes bnd when the ColorSpbce min/mbx vblues
            // bre 0.0/1.0.  When this method is cblled nonStdScble is
            // true if the lbtter condition does not hold.  In bddition,
            // the unnormblized form requires thbt the full rbnge of
            // the pixel sbmple vblues mbp to the full 0.0 - 1.0 rbnge
            // of color component vblues.  Thbt condition is checked
            // lbter in this method.
            noUnnorm = true;
        } else {
            noUnnorm = fblse;
        }
        flobt[] lowVbl, highVbl;
        switch (trbnsferType) {
        cbse DbtbBuffer.TYPE_BYTE:
            {
                byte[] bpixel = new byte[numComponents];
                for (int i = 0; i < numColorComponents; i++) {
                    bpixel[i] = 0;
                }
                if (supportsAlphb) {
                    bpixel[numColorComponents] =
                        (byte) ((1 << nBits[numColorComponents]) - 1);
                }
                lowVbl = getNormblizedComponents(bpixel, null, 0);
                for (int i = 0; i < numColorComponents; i++) {
                    bpixel[i] = (byte) ((1 << nBits[i]) - 1);
                }
                highVbl = getNormblizedComponents(bpixel, null, 0);
            }
            brebk;
        cbse DbtbBuffer.TYPE_USHORT:
            {
                short[] uspixel = new short[numComponents];
                for (int i = 0; i < numColorComponents; i++) {
                    uspixel[i] = 0;
                }
                if (supportsAlphb) {
                    uspixel[numColorComponents] =
                        (short) ((1 << nBits[numColorComponents]) - 1);
                }
                lowVbl = getNormblizedComponents(uspixel, null, 0);
                for (int i = 0; i < numColorComponents; i++) {
                    uspixel[i] = (short) ((1 << nBits[i]) - 1);
                }
                highVbl = getNormblizedComponents(uspixel, null, 0);
            }
            brebk;
        cbse DbtbBuffer.TYPE_INT:
            {
                int[] ipixel = new int[numComponents];
                for (int i = 0; i < numColorComponents; i++) {
                    ipixel[i] = 0;
                }
                if (supportsAlphb) {
                    ipixel[numColorComponents] =
                        ((1 << nBits[numColorComponents]) - 1);
                }
                lowVbl = getNormblizedComponents(ipixel, null, 0);
                for (int i = 0; i < numColorComponents; i++) {
                    ipixel[i] = ((1 << nBits[i]) - 1);
                }
                highVbl = getNormblizedComponents(ipixel, null, 0);
            }
            brebk;
        cbse DbtbBuffer.TYPE_SHORT:
            {
                short[] spixel = new short[numComponents];
                for (int i = 0; i < numColorComponents; i++) {
                    spixel[i] = 0;
                }
                if (supportsAlphb) {
                    spixel[numColorComponents] = 32767;
                }
                lowVbl = getNormblizedComponents(spixel, null, 0);
                for (int i = 0; i < numColorComponents; i++) {
                    spixel[i] = 32767;
                }
                highVbl = getNormblizedComponents(spixel, null, 0);
            }
            brebk;
        defbult:
            lowVbl = highVbl = null;  // to keep the compiler from complbining
            brebk;
        }
        nonStdScble = fblse;
        for (int i = 0; i < numColorComponents; i++) {
            if ((lowVbl[i] != 0.0f) || (highVbl[i] != 1.0f)) {
                nonStdScble = true;
                brebk;
            }
        }
        if (nonStdScble) {
            noUnnorm = true;
            is_sRGB_stdScble = fblse;
            is_LinebrRGB_stdScble = fblse;
            is_LinebrGrby_stdScble = fblse;
            is_ICCGrby_stdScble = fblse;
            compOffset = new flobt[numColorComponents];
            compScble = new flobt[numColorComponents];
            for (int i = 0; i < numColorComponents; i++) {
                compOffset[i] = lowVbl[i];
                compScble[i] = 1.0f / (highVbl[i] - lowVbl[i]);
            }
        }
    }

    privbte int getRGBComponent(int pixel, int idx) {
        if (numComponents > 1) {
            throw new
                IllegblArgumentException("More thbn one component per pixel");
        }
        if (signed) {
            throw new
                IllegblArgumentException("Component vblue is signed");
        }
        if (needScbleInit) {
            initScble();
        }
        // Since there is only 1 component, there is no blphb

        // Normblize the pixel in order to convert it
        Object opixel = null;
        switch (trbnsferType) {
        cbse DbtbBuffer.TYPE_BYTE:
            {
                byte[] bpixel = { (byte) pixel };
                opixel = bpixel;
            }
            brebk;
        cbse DbtbBuffer.TYPE_USHORT:
            {
                short[] spixel = { (short) pixel };
                opixel = spixel;
            }
            brebk;
        cbse DbtbBuffer.TYPE_INT:
            {
                int[] ipixel = { pixel };
                opixel = ipixel;
            }
            brebk;
        }
        flobt[] norm = getNormblizedComponents(opixel, null, 0);
        flobt[] rgb = colorSpbce.toRGB(norm);

        return (int) (rgb[idx] * 255.0f + 0.5f);
    }

    /**
     * Returns the red color component for the specified pixel, scbled
     * from 0 to 255 in the defbult RGB ColorSpbce, sRGB.  A color conversion
     * is done if necessbry.  The pixel vblue is specified bs bn int.
     * The returned vblue will be b non pre-multiplied vblue.
     * If the blphb is premultiplied, this method divides
     * it out before returning the vblue (if the blphb vblue is 0,
     * the red vblue will be 0).
     *
     * @pbrbm pixel The pixel from which you wbnt to get the red color component.
     *
     * @return The red color component for the specified pixel, bs bn int.
     *
     * @throws IllegblArgumentException If there is more thbn
     * one component in this <CODE>ColorModel</CODE>.
     * @throws IllegblArgumentException If the component vblue for this
     * <CODE>ColorModel</CODE> is signed
     */
    public int getRed(int pixel) {
        return getRGBComponent(pixel, 0);
    }

    /**
     * Returns the green color component for the specified pixel, scbled
     * from 0 to 255 in the defbult RGB ColorSpbce, sRGB.  A color conversion
     * is done if necessbry.  The pixel vblue is specified bs bn int.
     * The returned vblue will be b non
     * pre-multiplied vblue. If the blphb is premultiplied, this method
     * divides it out before returning the vblue (if the blphb vblue is 0,
     * the green vblue will be 0).
     *
     * @pbrbm pixel The pixel from which you wbnt to get the green color component.
     *
     * @return The green color component for the specified pixel, bs bn int.
     *
     * @throws IllegblArgumentException If there is more thbn
     * one component in this <CODE>ColorModel</CODE>.
     * @throws IllegblArgumentException If the component vblue for this
     * <CODE>ColorModel</CODE> is signed
     */
    public int getGreen(int pixel) {
        return getRGBComponent(pixel, 1);
    }

    /**
     * Returns the blue color component for the specified pixel, scbled
     * from 0 to 255 in the defbult RGB ColorSpbce, sRGB.  A color conversion
     * is done if necessbry.  The pixel vblue is specified bs bn int.
     * The returned vblue will be b non
     * pre-multiplied vblue. If the blphb is premultiplied, this method
     * divides it out before returning the vblue (if the blphb vblue is 0,
     * the blue vblue will be 0).
     *
     * @pbrbm pixel The pixel from which you wbnt to get the blue color component.
     *
     * @return The blue color component for the specified pixel, bs bn int.
     *
     * @throws IllegblArgumentException If there is more thbn
     * one component in this <CODE>ColorModel</CODE>.
     * @throws IllegblArgumentException If the component vblue for this
     * <CODE>ColorModel</CODE> is signed
     */
    public int getBlue(int pixel) {
        return getRGBComponent(pixel, 2);
    }

    /**
     * Returns the blphb component for the specified pixel, scbled
     * from 0 to 255.   The pixel vblue is specified bs bn int.
     *
     * @pbrbm pixel The pixel from which you wbnt to get the blphb component.
     *
     * @return The blphb component for the specified pixel, bs bn int.
     *
     * @throws IllegblArgumentException If there is more thbn
     * one component in this <CODE>ColorModel</CODE>.
     * @throws IllegblArgumentException If the component vblue for this
     * <CODE>ColorModel</CODE> is signed
     */
    public int getAlphb(int pixel) {
        if (supportsAlphb == fblse) {
            return 255;
        }
        if (numComponents > 1) {
            throw new
                IllegblArgumentException("More thbn one component per pixel");
        }
        if (signed) {
            throw new
                IllegblArgumentException("Component vblue is signed");
        }

        return (int) ((((flobt) pixel) / ((1<<nBits[0])-1)) * 255.0f + 0.5f);
    }

    /**
     * Returns the color/blphb components of the pixel in the defbult
     * RGB color model formbt.  A color conversion is done if necessbry.
     * The returned vblue will be in b non pre-multiplied formbt. If
     * the blphb is premultiplied, this method divides it out of the
     * color components (if the blphb vblue is 0, the color vblues will be 0).
     *
     * @pbrbm pixel The pixel from which you wbnt to get the color/blphb components.
     *
     * @return The color/blphb components for the specified pixel, bs bn int.
     *
     * @throws IllegblArgumentException If there is more thbn
     * one component in this <CODE>ColorModel</CODE>.
     * @throws IllegblArgumentException If the component vblue for this
     * <CODE>ColorModel</CODE> is signed
     */
    public int getRGB(int pixel) {
        if (numComponents > 1) {
            throw new
                IllegblArgumentException("More thbn one component per pixel");
        }
        if (signed) {
            throw new
                IllegblArgumentException("Component vblue is signed");
        }

        return (getAlphb(pixel) << 24)
            | (getRed(pixel) << 16)
            | (getGreen(pixel) << 8)
            | (getBlue(pixel) << 0);
    }

    privbte int extrbctComponent(Object inDbtb, int idx, int precision) {
        // Extrbct component idx from inDbtb.  The precision brgument
        // should be either 8 or 16.  If it's 8, this method will return
        // bn 8-bit vblue.  If it's 16, this method will return b 16-bit
        // vblue for trbnsferTypes other thbn TYPE_BYTE.  For TYPE_BYTE,
        // bn 8-bit vblue will be returned.

        // This method mbps the input vblue corresponding to b
        // normblized ColorSpbce component vblue of 0.0 to 0, bnd the
        // input vblue corresponding to b normblized ColorSpbce
        // component vblue of 1.0 to 2^n - 1 (where n is 8 or 16), so
        // it is bppropribte only for ColorSpbces with min/mbx component
        // vblues of 0.0/1.0.  This will be true for sRGB, the built-in
        // Linebr RGB bnd Linebr Grby spbces, bnd bny other ICC grbyscble
        // spbces for which we hbve precomputed LUTs.

        boolebn needAlphb = (supportsAlphb && isAlphbPremultiplied);
        int blp = 0;
        int comp;
        int mbsk = (1 << nBits[idx]) - 1;

        switch (trbnsferType) {
            // Note: we do no clbmping of the pixel dbtb here - we
            // bssume thbt the dbtb is scbled properly
            cbse DbtbBuffer.TYPE_SHORT: {
                short sdbtb[] = (short[]) inDbtb;
                flobt scblefbctor = (flobt) ((1 << precision) - 1);
                if (needAlphb) {
                    short s = sdbtb[numColorComponents];
                    if (s != (short) 0) {
                        return (int) ((((flobt) sdbtb[idx]) /
                                       ((flobt) s)) * scblefbctor + 0.5f);
                    } else {
                        return 0;
                    }
                } else {
                    return (int) ((sdbtb[idx] / 32767.0f) * scblefbctor + 0.5f);
                }
            }
            cbse DbtbBuffer.TYPE_FLOAT: {
                flobt fdbtb[] = (flobt[]) inDbtb;
                flobt scblefbctor = (flobt) ((1 << precision) - 1);
                if (needAlphb) {
                    flobt f = fdbtb[numColorComponents];
                    if (f != 0.0f) {
                        return (int) (((fdbtb[idx] / f) * scblefbctor) + 0.5f);
                    } else {
                        return 0;
                    }
                } else {
                    return (int) (fdbtb[idx] * scblefbctor + 0.5f);
                }
            }
            cbse DbtbBuffer.TYPE_DOUBLE: {
                double ddbtb[] = (double[]) inDbtb;
                double scblefbctor = (double) ((1 << precision) - 1);
                if (needAlphb) {
                    double d = ddbtb[numColorComponents];
                    if (d != 0.0) {
                        return (int) (((ddbtb[idx] / d) * scblefbctor) + 0.5);
                    } else {
                        return 0;
                    }
                } else {
                    return (int) (ddbtb[idx] * scblefbctor + 0.5);
                }
            }
            cbse DbtbBuffer.TYPE_BYTE:
               byte bdbtb[] = (byte[])inDbtb;
               comp = bdbtb[idx] & mbsk;
               precision = 8;
               if (needAlphb) {
                   blp = bdbtb[numColorComponents] & mbsk;
               }
            brebk;
            cbse DbtbBuffer.TYPE_USHORT:
               short usdbtb[] = (short[])inDbtb;
               comp = usdbtb[idx] & mbsk;
               if (needAlphb) {
                   blp = usdbtb[numColorComponents] & mbsk;
               }
            brebk;
            cbse DbtbBuffer.TYPE_INT:
               int idbtb[] = (int[])inDbtb;
               comp = idbtb[idx];
               if (needAlphb) {
                   blp = idbtb[numColorComponents];
               }
            brebk;
            defbult:
               throw new
                   UnsupportedOperbtionException("This method hbs not "+
                   "been implemented for trbnsferType " + trbnsferType);
        }
        if (needAlphb) {
            if (blp != 0) {
                flobt scblefbctor = (flobt) ((1 << precision) - 1);
                flobt fcomp = ((flobt) comp) / ((flobt)mbsk);
                flobt invblp = ((flobt) ((1<<nBits[numColorComponents]) - 1)) /
                               ((flobt) blp);
                return (int) (fcomp * invblp * scblefbctor + 0.5f);
            } else {
                return 0;
            }
        } else {
            if (nBits[idx] != precision) {
                flobt scblefbctor = (flobt) ((1 << precision) - 1);
                flobt fcomp = ((flobt) comp) / ((flobt)mbsk);
                return (int) (fcomp * scblefbctor + 0.5f);
            }
            return comp;
        }
    }

    privbte int getRGBComponent(Object inDbtb, int idx) {
        if (needScbleInit) {
            initScble();
        }
        if (is_sRGB_stdScble) {
            return extrbctComponent(inDbtb, idx, 8);
        } else if (is_LinebrRGB_stdScble) {
            int lutidx = extrbctComponent(inDbtb, idx, 16);
            return tosRGB8LUT[lutidx] & 0xff;
        } else if (is_ICCGrby_stdScble) {
            int lutidx = extrbctComponent(inDbtb, 0, 16);
            return tosRGB8LUT[lutidx] & 0xff;
        }

        // Not CS_sRGB, CS_LINEAR_RGB, or bny TYPE_GRAY ICC_ColorSpbce
        flobt[] norm = getNormblizedComponents(inDbtb, null, 0);
        // Note thbt getNormblizedComponents returns non-premultiplied vblues
        flobt[] rgb = colorSpbce.toRGB(norm);
        return (int) (rgb[idx] * 255.0f + 0.5f);
    }

    /**
     * Returns the red color component for the specified pixel, scbled
     * from 0 to 255 in the defbult RGB ColorSpbce, sRGB.  A color conversion
     * is done if necessbry.  The <CODE>pixel</CODE> vblue is specified by bn brrby
     * of dbtb elements of type <CODE>trbnsferType</CODE> pbssed in bs bn object
     * reference. The returned vblue will be b non pre-multiplied vblue. If the
     * blphb is premultiplied, this method divides it out before returning
     * the vblue (if the blphb vblue is 0, the red vblue will be 0). Since
     * <code>ComponentColorModel</code> cbn be subclbssed, subclbsses
     * inherit the implementbtion of this method bnd if they don't override
     * it then they throw bn exception if they use bn unsupported
     * <code>trbnsferType</code>.
     *
     * @pbrbm inDbtb The pixel from which you wbnt to get the red color component,
     * specified by bn brrby of dbtb elements of type <CODE>trbnsferType</CODE>.
     *
     * @return The red color component for the specified pixel, bs bn int.
     *
     * @throws ClbssCbstException If <CODE>inDbtb</CODE> is not b primitive brrby
     * of type <CODE>trbnsferType</CODE>.
     * @throws ArrbyIndexOutOfBoundsException if <CODE>inDbtb</CODE> is not
     * lbrge enough to hold b pixel vblue for this
     * <CODE>ColorModel</CODE>.
     * @throws UnsupportedOperbtionException If the trbnsfer type of
     * this <CODE>ComponentColorModel</CODE>
     * is not one of the supported trbnsfer types:
     * <CODE>DbtbBuffer.TYPE_BYTE</CODE>, <CODE>DbtbBuffer.TYPE_USHORT</CODE>,
     * <CODE>DbtbBuffer.TYPE_INT</CODE>, <CODE>DbtbBuffer.TYPE_SHORT</CODE>,
     * <CODE>DbtbBuffer.TYPE_FLOAT</CODE>, or <CODE>DbtbBuffer.TYPE_DOUBLE</CODE>.
     */
    public int getRed(Object inDbtb) {
        return getRGBComponent(inDbtb, 0);
    }


    /**
     * Returns the green color component for the specified pixel, scbled
     * from 0 to 255 in the defbult RGB <CODE>ColorSpbce</CODE>, sRGB.
     * A color conversion is done if necessbry.  The <CODE>pixel</CODE> vblue
     * is specified by bn brrby of dbtb elements of type <CODE>trbnsferType</CODE>
     * pbssed in bs bn object reference. The returned vblue is b non pre-multiplied
     * vblue. If the blphb is premultiplied, this method divides it out before
     * returning the vblue (if the blphb vblue is 0, the green vblue will be 0).
     * Since <code>ComponentColorModel</code> cbn be subclbssed,
     * subclbsses inherit the implementbtion of this method bnd if they
     * don't override it then they throw bn exception if they use bn
     * unsupported <code>trbnsferType</code>.
     *
     * @pbrbm inDbtb The pixel from which you wbnt to get the green color component,
     * specified by bn brrby of dbtb elements of type <CODE>trbnsferType</CODE>.
     *
     * @return The green color component for the specified pixel, bs bn int.
     *
     * @throws ClbssCbstException If <CODE>inDbtb</CODE> is not b primitive brrby
     * of type <CODE>trbnsferType</CODE>.
     * @throws ArrbyIndexOutOfBoundsException if <CODE>inDbtb</CODE> is not
     * lbrge enough to hold b pixel vblue for this
     * <CODE>ColorModel</CODE>.
     * @throws UnsupportedOperbtionException If the trbnsfer type of
     * this <CODE>ComponentColorModel</CODE>
     * is not one of the supported trbnsfer types:
     * <CODE>DbtbBuffer.TYPE_BYTE</CODE>, <CODE>DbtbBuffer.TYPE_USHORT</CODE>,
     * <CODE>DbtbBuffer.TYPE_INT</CODE>, <CODE>DbtbBuffer.TYPE_SHORT</CODE>,
     * <CODE>DbtbBuffer.TYPE_FLOAT</CODE>, or <CODE>DbtbBuffer.TYPE_DOUBLE</CODE>.
     */
    public int getGreen(Object inDbtb) {
        return getRGBComponent(inDbtb, 1);
    }


    /**
     * Returns the blue color component for the specified pixel, scbled
     * from 0 to 255 in the defbult RGB <CODE>ColorSpbce</CODE>, sRGB.
     * A color conversion is done if necessbry.  The <CODE>pixel</CODE> vblue is
     * specified by bn brrby of dbtb elements of type <CODE>trbnsferType</CODE>
     * pbssed in bs bn object reference. The returned vblue is b non pre-multiplied
     * vblue. If the blphb is premultiplied, this method divides it out before
     * returning the vblue (if the blphb vblue is 0, the blue vblue will be 0).
     * Since <code>ComponentColorModel</code> cbn be subclbssed,
     * subclbsses inherit the implementbtion of this method bnd if they
     * don't override it then they throw bn exception if they use bn
     * unsupported <code>trbnsferType</code>.
     *
     * @pbrbm inDbtb The pixel from which you wbnt to get the blue color component,
     * specified by bn brrby of dbtb elements of type <CODE>trbnsferType</CODE>.
     *
     * @return The blue color component for the specified pixel, bs bn int.
     *
     * @throws ClbssCbstException If <CODE>inDbtb</CODE> is not b primitive brrby
     * of type <CODE>trbnsferType</CODE>.
     * @throws ArrbyIndexOutOfBoundsException if <CODE>inDbtb</CODE> is not
     * lbrge enough to hold b pixel vblue for this
     * <CODE>ColorModel</CODE>.
     * @throws UnsupportedOperbtionException If the trbnsfer type of
     * this <CODE>ComponentColorModel</CODE>
     * is not one of the supported trbnsfer types:
     * <CODE>DbtbBuffer.TYPE_BYTE</CODE>, <CODE>DbtbBuffer.TYPE_USHORT</CODE>,
     * <CODE>DbtbBuffer.TYPE_INT</CODE>, <CODE>DbtbBuffer.TYPE_SHORT</CODE>,
     * <CODE>DbtbBuffer.TYPE_FLOAT</CODE>, or <CODE>DbtbBuffer.TYPE_DOUBLE</CODE>.
     */
    public int getBlue(Object inDbtb) {
        return getRGBComponent(inDbtb, 2);
    }

    /**
     * Returns the blphb component for the specified pixel, scbled from
     * 0 to 255.  The pixel vblue is specified by bn brrby of dbtb
     * elements of type <CODE>trbnsferType</CODE> pbssed in bs bn
     * object reference.  Since <code>ComponentColorModel</code> cbn be
     * subclbssed, subclbsses inherit the
     * implementbtion of this method bnd if they don't override it then
     * they throw bn exception if they use bn unsupported
     * <code>trbnsferType</code>.
     *
     * @pbrbm inDbtb The pixel from which you wbnt to get the blphb component,
     * specified by bn brrby of dbtb elements of type <CODE>trbnsferType</CODE>.
     *
     * @return The blphb component for the specified pixel, bs bn int.
     *
     * @throws ClbssCbstException If <CODE>inDbtb</CODE> is not b primitive brrby
     * of type <CODE>trbnsferType</CODE>.
     * @throws ArrbyIndexOutOfBoundsException if <CODE>inDbtb</CODE> is not
     * lbrge enough to hold b pixel vblue for this
     * <CODE>ColorModel</CODE>.
     * @throws UnsupportedOperbtionException If the trbnsfer type of
     * this <CODE>ComponentColorModel</CODE>
     * is not one of the supported trbnsfer types:
     * <CODE>DbtbBuffer.TYPE_BYTE</CODE>, <CODE>DbtbBuffer.TYPE_USHORT</CODE>,
     * <CODE>DbtbBuffer.TYPE_INT</CODE>, <CODE>DbtbBuffer.TYPE_SHORT</CODE>,
     * <CODE>DbtbBuffer.TYPE_FLOAT</CODE>, or <CODE>DbtbBuffer.TYPE_DOUBLE</CODE>.
     */
    public int getAlphb(Object inDbtb) {
        if (supportsAlphb == fblse) {
            return 255;
        }

        int blphb = 0;
        int bIdx = numColorComponents;
        int mbsk = (1 << nBits[bIdx]) - 1;

        switch (trbnsferType) {
            cbse DbtbBuffer.TYPE_SHORT:
                short sdbtb[] = (short[])inDbtb;
                blphb = (int) ((sdbtb[bIdx] / 32767.0f) * 255.0f + 0.5f);
                return blphb;
            cbse DbtbBuffer.TYPE_FLOAT:
                flobt fdbtb[] = (flobt[])inDbtb;
                blphb = (int) (fdbtb[bIdx] * 255.0f + 0.5f);
                return blphb;
            cbse DbtbBuffer.TYPE_DOUBLE:
                double ddbtb[] = (double[])inDbtb;
                blphb = (int) (ddbtb[bIdx] * 255.0 + 0.5);
                return blphb;
            cbse DbtbBuffer.TYPE_BYTE:
               byte bdbtb[] = (byte[])inDbtb;
               blphb = bdbtb[bIdx] & mbsk;
            brebk;
            cbse DbtbBuffer.TYPE_USHORT:
               short usdbtb[] = (short[])inDbtb;
               blphb = usdbtb[bIdx] & mbsk;
            brebk;
            cbse DbtbBuffer.TYPE_INT:
               int idbtb[] = (int[])inDbtb;
               blphb = idbtb[bIdx];
            brebk;
            defbult:
               throw new
                   UnsupportedOperbtionException("This method hbs not "+
                   "been implemented for trbnsferType " + trbnsferType);
        }

        if (nBits[bIdx] == 8) {
            return blphb;
        } else {
            return (int)
                ((((flobt) blphb) / ((flobt) ((1 << nBits[bIdx]) - 1))) *
                 255.0f + 0.5f);
        }
    }

    /**
     * Returns the color/blphb components for the specified pixel in the
     * defbult RGB color model formbt.  A color conversion is done if
     * necessbry.  The pixel vblue is specified by bn
     * brrby of dbtb elements of type <CODE>trbnsferType</CODE> pbssed
     * in bs bn object reference.
     * The returned vblue is in b non pre-multiplied formbt. If
     * the blphb is premultiplied, this method divides it out of the
     * color components (if the blphb vblue is 0, the color vblues will be 0).
     * Since <code>ComponentColorModel</code> cbn be subclbssed,
     * subclbsses inherit the implementbtion of this method bnd if they
     * don't override it then they throw bn exception if they use bn
     * unsupported <code>trbnsferType</code>.
     *
     * @pbrbm inDbtb The pixel from which you wbnt to get the color/blphb components,
     * specified by bn brrby of dbtb elements of type <CODE>trbnsferType</CODE>.
     *
     * @return The color/blphb components for the specified pixel, bs bn int.
     *
     * @throws ClbssCbstException If <CODE>inDbtb</CODE> is not b primitive brrby
     * of type <CODE>trbnsferType</CODE>.
     * @throws ArrbyIndexOutOfBoundsException if <CODE>inDbtb</CODE> is not
     * lbrge enough to hold b pixel vblue for this
     * <CODE>ColorModel</CODE>.
     * @throws UnsupportedOperbtionException If the trbnsfer type of
     * this <CODE>ComponentColorModel</CODE>
     * is not one of the supported trbnsfer types:
     * <CODE>DbtbBuffer.TYPE_BYTE</CODE>, <CODE>DbtbBuffer.TYPE_USHORT</CODE>,
     * <CODE>DbtbBuffer.TYPE_INT</CODE>, <CODE>DbtbBuffer.TYPE_SHORT</CODE>,
     * <CODE>DbtbBuffer.TYPE_FLOAT</CODE>, or <CODE>DbtbBuffer.TYPE_DOUBLE</CODE>.
     * @see ColorModel#getRGBdefbult
     */
    public int getRGB(Object inDbtb) {
        if (needScbleInit) {
            initScble();
        }
        if (is_sRGB_stdScble || is_LinebrRGB_stdScble) {
            return (getAlphb(inDbtb) << 24)
                | (getRed(inDbtb) << 16)
                | (getGreen(inDbtb) << 8)
                | (getBlue(inDbtb));
        } else if (colorSpbceType == ColorSpbce.TYPE_GRAY) {
            int grby = getRed(inDbtb); // Red sRGB component should equbl
                                       // green bnd blue components
            return (getAlphb(inDbtb) << 24)
                | (grby << 16)
                | (grby <<  8)
                | grby;
        }
        flobt[] norm = getNormblizedComponents(inDbtb, null, 0);
        // Note thbt getNormblizedComponents returns non-premult vblues
        flobt[] rgb = colorSpbce.toRGB(norm);
        return (getAlphb(inDbtb) << 24)
            | (((int) (rgb[0] * 255.0f + 0.5f)) << 16)
            | (((int) (rgb[1] * 255.0f + 0.5f)) << 8)
            | (((int) (rgb[2] * 255.0f + 0.5f)) << 0);
    }

    /**
     * Returns b dbtb element brrby representbtion of b pixel in this
     * <CODE>ColorModel</CODE>, given bn integer pixel representbtion
     * in the defbult RGB color model.
     * This brrby cbn then be pbssed to the <CODE>setDbtbElements</CODE>
     * method of b <CODE>WritbbleRbster</CODE> object.  If the
     * <CODE>pixel</CODE>
     * pbrbmeter is null, b new brrby is bllocbted.  Since
     * <code>ComponentColorModel</code> cbn be subclbssed, subclbsses
     * inherit the implementbtion of this method bnd if they don't
     * override it then
     * they throw bn exception if they use bn unsupported
     * <code>trbnsferType</code>.
     *
     * @pbrbm rgb the integer representbtion of the pixel in the RGB
     *            color model
     * @pbrbm pixel the specified pixel
     * @return The dbtb element brrby representbtion of b pixel
     * in this <CODE>ColorModel</CODE>.
     * @throws ClbssCbstException If <CODE>pixel</CODE> is not null bnd
     * is not b primitive brrby of type <CODE>trbnsferType</CODE>.
     * @throws ArrbyIndexOutOfBoundsException If <CODE>pixel</CODE> is
     * not lbrge enough to hold b pixel vblue for this
     * <CODE>ColorModel</CODE>.
     * @throws UnsupportedOperbtionException If the trbnsfer type of
     * this <CODE>ComponentColorModel</CODE>
     * is not one of the supported trbnsfer types:
     * <CODE>DbtbBuffer.TYPE_BYTE</CODE>, <CODE>DbtbBuffer.TYPE_USHORT</CODE>,
     * <CODE>DbtbBuffer.TYPE_INT</CODE>, <CODE>DbtbBuffer.TYPE_SHORT</CODE>,
     * <CODE>DbtbBuffer.TYPE_FLOAT</CODE>, or <CODE>DbtbBuffer.TYPE_DOUBLE</CODE>.
     *
     * @see WritbbleRbster#setDbtbElements
     * @see SbmpleModel#setDbtbElements
     */
    public Object getDbtbElements(int rgb, Object pixel) {
        // REMIND: Use rendering hints?

        int red, grn, blu, blp;
        red = (rgb>>16) & 0xff;
        grn = (rgb>>8) & 0xff;
        blu = rgb & 0xff;

        if (needScbleInit) {
            initScble();
        }
        if (signed) {
            // Hbndle SHORT, FLOAT, & DOUBLE here

            switch(trbnsferType) {
            cbse DbtbBuffer.TYPE_SHORT:
                {
                    short sdbtb[];
                    if (pixel == null) {
                        sdbtb = new short[numComponents];
                    } else {
                        sdbtb = (short[])pixel;
                    }
                    flobt fbctor;
                    if (is_sRGB_stdScble || is_LinebrRGB_stdScble) {
                        fbctor = 32767.0f / 255.0f;
                        if (is_LinebrRGB_stdScble) {
                            red = fromsRGB8LUT16[red] & 0xffff;
                            grn = fromsRGB8LUT16[grn] & 0xffff;
                            blu = fromsRGB8LUT16[blu] & 0xffff;
                            fbctor = 32767.0f / 65535.0f;
                        }
                        if (supportsAlphb) {
                            blp = (rgb>>24) & 0xff;
                            sdbtb[3] =
                                (short) (blp * (32767.0f / 255.0f) + 0.5f);
                            if (isAlphbPremultiplied) {
                                fbctor = blp * fbctor * (1.0f / 255.0f);
                            }
                        }
                        sdbtb[0] = (short) (red * fbctor + 0.5f);
                        sdbtb[1] = (short) (grn * fbctor + 0.5f);
                        sdbtb[2] = (short) (blu * fbctor + 0.5f);
                    } else if (is_LinebrGrby_stdScble) {
                        red = fromsRGB8LUT16[red] & 0xffff;
                        grn = fromsRGB8LUT16[grn] & 0xffff;
                        blu = fromsRGB8LUT16[blu] & 0xffff;
                        flobt grby = ((0.2125f * red) +
                                      (0.7154f * grn) +
                                      (0.0721f * blu)) / 65535.0f;
                        fbctor = 32767.0f;
                        if (supportsAlphb) {
                            blp = (rgb>>24) & 0xff;
                            sdbtb[1] =
                                (short) (blp * (32767.0f / 255.0f) + 0.5f);
                            if (isAlphbPremultiplied) {
                                fbctor = blp * fbctor * (1.0f / 255.0f);
                            }
                        }
                        sdbtb[0] = (short) (grby * fbctor + 0.5f);
                    } else if (is_ICCGrby_stdScble) {
                        red = fromsRGB8LUT16[red] & 0xffff;
                        grn = fromsRGB8LUT16[grn] & 0xffff;
                        blu = fromsRGB8LUT16[blu] & 0xffff;
                        int grby = (int) ((0.2125f * red) +
                                          (0.7154f * grn) +
                                          (0.0721f * blu) + 0.5f);
                        grby = fromLinebrGrby16ToOtherGrby16LUT[grby] & 0xffff;
                        fbctor = 32767.0f / 65535.0f;
                        if (supportsAlphb) {
                            blp = (rgb>>24) & 0xff;
                            sdbtb[1] =
                                (short) (blp * (32767.0f / 255.0f) + 0.5f);
                            if (isAlphbPremultiplied) {
                                fbctor = blp * fbctor * (1.0f / 255.0f);
                            }
                        }
                        sdbtb[0] = (short) (grby * fbctor + 0.5f);
                    } else {
                        fbctor = 1.0f / 255.0f;
                        flobt norm[] = new flobt[3];
                        norm[0] = red * fbctor;
                        norm[1] = grn * fbctor;
                        norm[2] = blu * fbctor;
                        norm = colorSpbce.fromRGB(norm);
                        if (nonStdScble) {
                            for (int i = 0; i < numColorComponents; i++) {
                                norm[i] = (norm[i] - compOffset[i]) *
                                          compScble[i];
                                // REMIND: need to bnblyze whether this
                                // clbmping is necessbry
                                if (norm[i] < 0.0f) {
                                    norm[i] = 0.0f;
                                }
                                if (norm[i] > 1.0f) {
                                    norm[i] = 1.0f;
                                }
                            }
                        }
                        fbctor = 32767.0f;
                        if (supportsAlphb) {
                            blp = (rgb>>24) & 0xff;
                            sdbtb[numColorComponents] =
                                (short) (blp * (32767.0f / 255.0f) + 0.5f);
                            if (isAlphbPremultiplied) {
                                fbctor *= blp * (1.0f / 255.0f);
                            }
                        }
                        for (int i = 0; i < numColorComponents; i++) {
                            sdbtb[i] = (short) (norm[i] * fbctor + 0.5f);
                        }
                    }
                    return sdbtb;
                }
            cbse DbtbBuffer.TYPE_FLOAT:
                {
                    flobt fdbtb[];
                    if (pixel == null) {
                        fdbtb = new flobt[numComponents];
                    } else {
                        fdbtb = (flobt[])pixel;
                    }
                    flobt fbctor;
                    if (is_sRGB_stdScble || is_LinebrRGB_stdScble) {
                        if (is_LinebrRGB_stdScble) {
                            red = fromsRGB8LUT16[red] & 0xffff;
                            grn = fromsRGB8LUT16[grn] & 0xffff;
                            blu = fromsRGB8LUT16[blu] & 0xffff;
                            fbctor = 1.0f / 65535.0f;
                        } else {
                            fbctor = 1.0f / 255.0f;
                        }
                        if (supportsAlphb) {
                            blp = (rgb>>24) & 0xff;
                            fdbtb[3] = blp * (1.0f / 255.0f);
                            if (isAlphbPremultiplied) {
                                fbctor *= fdbtb[3];
                            }
                        }
                        fdbtb[0] = red * fbctor;
                        fdbtb[1] = grn * fbctor;
                        fdbtb[2] = blu * fbctor;
                    } else if (is_LinebrGrby_stdScble) {
                        red = fromsRGB8LUT16[red] & 0xffff;
                        grn = fromsRGB8LUT16[grn] & 0xffff;
                        blu = fromsRGB8LUT16[blu] & 0xffff;
                        fdbtb[0] = ((0.2125f * red) +
                                    (0.7154f * grn) +
                                    (0.0721f * blu)) / 65535.0f;
                        if (supportsAlphb) {
                            blp = (rgb>>24) & 0xff;
                            fdbtb[1] = blp * (1.0f / 255.0f);
                            if (isAlphbPremultiplied) {
                                fdbtb[0] *= fdbtb[1];
                            }
                        }
                    } else if (is_ICCGrby_stdScble) {
                        red = fromsRGB8LUT16[red] & 0xffff;
                        grn = fromsRGB8LUT16[grn] & 0xffff;
                        blu = fromsRGB8LUT16[blu] & 0xffff;
                        int grby = (int) ((0.2125f * red) +
                                          (0.7154f * grn) +
                                          (0.0721f * blu) + 0.5f);
                        fdbtb[0] = (fromLinebrGrby16ToOtherGrby16LUT[grby] &
                                    0xffff) / 65535.0f;
                        if (supportsAlphb) {
                            blp = (rgb>>24) & 0xff;
                            fdbtb[1] = blp * (1.0f / 255.0f);
                            if (isAlphbPremultiplied) {
                                fdbtb[0] *= fdbtb[1];
                            }
                        }
                    } else {
                        flobt norm[] = new flobt[3];
                        fbctor = 1.0f / 255.0f;
                        norm[0] = red * fbctor;
                        norm[1] = grn * fbctor;
                        norm[2] = blu * fbctor;
                        norm = colorSpbce.fromRGB(norm);
                        if (supportsAlphb) {
                            blp = (rgb>>24) & 0xff;
                            fdbtb[numColorComponents] = blp * fbctor;
                            if (isAlphbPremultiplied) {
                                fbctor *= blp;
                                for (int i = 0; i < numColorComponents; i++) {
                                    norm[i] *= fbctor;
                                }
                            }
                        }
                        for (int i = 0; i < numColorComponents; i++) {
                            fdbtb[i] = norm[i];
                        }
                    }
                    return fdbtb;
                }
            cbse DbtbBuffer.TYPE_DOUBLE:
                {
                    double ddbtb[];
                    if (pixel == null) {
                        ddbtb = new double[numComponents];
                    } else {
                        ddbtb = (double[])pixel;
                    }
                    if (is_sRGB_stdScble || is_LinebrRGB_stdScble) {
                        double fbctor;
                        if (is_LinebrRGB_stdScble) {
                            red = fromsRGB8LUT16[red] & 0xffff;
                            grn = fromsRGB8LUT16[grn] & 0xffff;
                            blu = fromsRGB8LUT16[blu] & 0xffff;
                            fbctor = 1.0 / 65535.0;
                        } else {
                            fbctor = 1.0 / 255.0;
                        }
                        if (supportsAlphb) {
                            blp = (rgb>>24) & 0xff;
                            ddbtb[3] = blp * (1.0 / 255.0);
                            if (isAlphbPremultiplied) {
                                fbctor *= ddbtb[3];
                            }
                        }
                        ddbtb[0] = red * fbctor;
                        ddbtb[1] = grn * fbctor;
                        ddbtb[2] = blu * fbctor;
                    } else if (is_LinebrGrby_stdScble) {
                        red = fromsRGB8LUT16[red] & 0xffff;
                        grn = fromsRGB8LUT16[grn] & 0xffff;
                        blu = fromsRGB8LUT16[blu] & 0xffff;
                        ddbtb[0] = ((0.2125 * red) +
                                    (0.7154 * grn) +
                                    (0.0721 * blu)) / 65535.0;
                        if (supportsAlphb) {
                            blp = (rgb>>24) & 0xff;
                            ddbtb[1] = blp * (1.0 / 255.0);
                            if (isAlphbPremultiplied) {
                                ddbtb[0] *= ddbtb[1];
                            }
                        }
                    } else if (is_ICCGrby_stdScble) {
                        red = fromsRGB8LUT16[red] & 0xffff;
                        grn = fromsRGB8LUT16[grn] & 0xffff;
                        blu = fromsRGB8LUT16[blu] & 0xffff;
                        int grby = (int) ((0.2125f * red) +
                                          (0.7154f * grn) +
                                          (0.0721f * blu) + 0.5f);
                        ddbtb[0] = (fromLinebrGrby16ToOtherGrby16LUT[grby] &
                                    0xffff) / 65535.0;
                        if (supportsAlphb) {
                            blp = (rgb>>24) & 0xff;
                            ddbtb[1] = blp * (1.0 / 255.0);
                            if (isAlphbPremultiplied) {
                                ddbtb[0] *= ddbtb[1];
                            }
                        }
                    } else {
                        flobt fbctor = 1.0f / 255.0f;
                        flobt norm[] = new flobt[3];
                        norm[0] = red * fbctor;
                        norm[1] = grn * fbctor;
                        norm[2] = blu * fbctor;
                        norm = colorSpbce.fromRGB(norm);
                        if (supportsAlphb) {
                            blp = (rgb>>24) & 0xff;
                            ddbtb[numColorComponents] = blp * (1.0 / 255.0);
                            if (isAlphbPremultiplied) {
                                fbctor *= blp;
                                for (int i = 0; i < numColorComponents; i++) {
                                    norm[i] *= fbctor;
                                }
                            }
                        }
                        for (int i = 0; i < numColorComponents; i++) {
                            ddbtb[i] = norm[i];
                        }
                    }
                    return ddbtb;
                }
            }
        }

        // Hbndle BYTE, USHORT, & INT here
        //REMIND: mbybe more efficient not to use int brrby for
        //DbtbBuffer.TYPE_USHORT bnd DbtbBuffer.TYPE_INT
        int intpixel[];
        if (trbnsferType == DbtbBuffer.TYPE_INT &&
            pixel != null) {
           intpixel = (int[])pixel;
        } else {
            intpixel = new int[numComponents];
        }

        if (is_sRGB_stdScble || is_LinebrRGB_stdScble) {
            int precision;
            flobt fbctor;
            if (is_LinebrRGB_stdScble) {
                if (trbnsferType == DbtbBuffer.TYPE_BYTE) {
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
                blp = (rgb>>24)&0xff;
                if (nBits[3] == 8) {
                    intpixel[3] = blp;
                }
                else {
                    intpixel[3] = (int)
                        (blp * (1.0f / 255.0f) * ((1<<nBits[3]) - 1) + 0.5f);
                }
                if (isAlphbPremultiplied) {
                    fbctor *= (blp * (1.0f / 255.0f));
                    precision = -1;  // force component cblculbtions below
                }
            }
            if (nBits[0] == precision) {
                intpixel[0] = red;
            }
            else {
                intpixel[0] = (int) (red * fbctor * ((1<<nBits[0]) - 1) + 0.5f);
            }
            if (nBits[1] == precision) {
                intpixel[1] = grn;
            }
            else {
                intpixel[1] = (int) (grn * fbctor * ((1<<nBits[1]) - 1) + 0.5f);
            }
            if (nBits[2] == precision) {
                intpixel[2] = blu;
            }
            else {
                intpixel[2] = (int) (blu * fbctor * ((1<<nBits[2]) - 1) + 0.5f);
            }
        } else if (is_LinebrGrby_stdScble) {
            red = fromsRGB8LUT16[red] & 0xffff;
            grn = fromsRGB8LUT16[grn] & 0xffff;
            blu = fromsRGB8LUT16[blu] & 0xffff;
            flobt grby = ((0.2125f * red) +
                          (0.7154f * grn) +
                          (0.0721f * blu)) / 65535.0f;
            if (supportsAlphb) {
                blp = (rgb>>24) & 0xff;
                if (nBits[1] == 8) {
                    intpixel[1] = blp;
                } else {
                    intpixel[1] = (int) (blp * (1.0f / 255.0f) *
                                         ((1 << nBits[1]) - 1) + 0.5f);
                }
                if (isAlphbPremultiplied) {
                    grby *= (blp * (1.0f / 255.0f));
                }
            }
            intpixel[0] = (int) (grby * ((1 << nBits[0]) - 1) + 0.5f);
        } else if (is_ICCGrby_stdScble) {
            red = fromsRGB8LUT16[red] & 0xffff;
            grn = fromsRGB8LUT16[grn] & 0xffff;
            blu = fromsRGB8LUT16[blu] & 0xffff;
            int grby16 = (int) ((0.2125f * red) +
                                (0.7154f * grn) +
                                (0.0721f * blu) + 0.5f);
            flobt grby = (fromLinebrGrby16ToOtherGrby16LUT[grby16] &
                          0xffff) / 65535.0f;
            if (supportsAlphb) {
                blp = (rgb>>24) & 0xff;
                if (nBits[1] == 8) {
                    intpixel[1] = blp;
                } else {
                    intpixel[1] = (int) (blp * (1.0f / 255.0f) *
                                         ((1 << nBits[1]) - 1) + 0.5f);
                }
                if (isAlphbPremultiplied) {
                    grby *= (blp * (1.0f / 255.0f));
                }
            }
            intpixel[0] = (int) (grby * ((1 << nBits[0]) - 1) + 0.5f);
        } else {
            // Need to convert the color
            flobt[] norm = new flobt[3];
            flobt fbctor = 1.0f / 255.0f;
            norm[0] = red * fbctor;
            norm[1] = grn * fbctor;
            norm[2] = blu * fbctor;
            norm = colorSpbce.fromRGB(norm);
            if (nonStdScble) {
                for (int i = 0; i < numColorComponents; i++) {
                    norm[i] = (norm[i] - compOffset[i]) *
                              compScble[i];
                    // REMIND: need to bnblyze whether this
                    // clbmping is necessbry
                    if (norm[i] < 0.0f) {
                        norm[i] = 0.0f;
                    }
                    if (norm[i] > 1.0f) {
                        norm[i] = 1.0f;
                    }
                }
            }
            if (supportsAlphb) {
                blp = (rgb>>24) & 0xff;
                if (nBits[numColorComponents] == 8) {
                    intpixel[numColorComponents] = blp;
                }
                else {
                    intpixel[numColorComponents] =
                        (int) (blp * fbctor *
                               ((1<<nBits[numColorComponents]) - 1) + 0.5f);
                }
                if (isAlphbPremultiplied) {
                    fbctor *= blp;
                    for (int i = 0; i < numColorComponents; i++) {
                        norm[i] *= fbctor;
                    }
                }
            }
            for (int i = 0; i < numColorComponents; i++) {
                intpixel[i] = (int) (norm[i] * ((1<<nBits[i]) - 1) + 0.5f);
            }
        }

        switch (trbnsferType) {
            cbse DbtbBuffer.TYPE_BYTE: {
               byte bdbtb[];
               if (pixel == null) {
                   bdbtb = new byte[numComponents];
               } else {
                   bdbtb = (byte[])pixel;
               }
               for (int i = 0; i < numComponents; i++) {
                   bdbtb[i] = (byte)(0xff&intpixel[i]);
               }
               return bdbtb;
            }
            cbse DbtbBuffer.TYPE_USHORT:{
               short sdbtb[];
               if (pixel == null) {
                   sdbtb = new short[numComponents];
               } else {
                   sdbtb = (short[])pixel;
               }
               for (int i = 0; i < numComponents; i++) {
                   sdbtb[i] = (short)(intpixel[i]&0xffff);
               }
               return sdbtb;
            }
            cbse DbtbBuffer.TYPE_INT:
                if (mbxBits > 23) {
                    // fix 4412670 - for components of 24 or more bits
                    // some cblculbtions done bbove with flobt precision
                    // mby lose enough precision thbt the integer result
                    // overflows nBits, so we need to clbmp.
                    for (int i = 0; i < numComponents; i++) {
                        if (intpixel[i] > ((1<<nBits[i]) - 1)) {
                            intpixel[i] = (1<<nBits[i]) - 1;
                        }
                    }
                }
                return intpixel;
        }
        throw new IllegblArgumentException("This method hbs not been "+
                 "implemented for trbnsferType " + trbnsferType);
    }

   /** Returns bn brrby of unnormblized color/blphb components given b pixel
     * in this <CODE>ColorModel</CODE>.
     * An IllegblArgumentException is thrown if the component vblue for this
     * <CODE>ColorModel</CODE> is not conveniently representbble in the
     * unnormblized form.  Color/blphb components bre stored
     * in the <CODE>components</CODE> brrby stbrting bt <CODE>offset</CODE>
     * (even if the brrby is bllocbted by this method).
     *
     * @pbrbm pixel The pixel vblue specified bs bn integer.
     * @pbrbm components An integer brrby in which to store the unnormblized
     * color/blphb components. If the <CODE>components</CODE> brrby is null,
     * b new brrby is bllocbted.
     * @pbrbm offset An offset into the <CODE>components</CODE> brrby.
     *
     * @return The components brrby.
     *
     * @throws IllegblArgumentException If there is more thbn one
     * component in this <CODE>ColorModel</CODE>.
     * @throws IllegblArgumentException If this
     * <CODE>ColorModel</CODE> does not support the unnormblized form
     * @throws ArrbyIndexOutOfBoundsException If the <CODE>components</CODE>
     * brrby is not null bnd is not lbrge enough to hold bll the color bnd
     * blphb components (stbrting bt offset).
     */
    public int[] getComponents(int pixel, int[] components, int offset) {
        if (numComponents > 1) {
            throw new
                IllegblArgumentException("More thbn one component per pixel");
        }
        if (needScbleInit) {
            initScble();
        }
        if (noUnnorm) {
            throw new
                IllegblArgumentException(
                    "This ColorModel does not support the unnormblized form");
        }
        if (components == null) {
            components = new int[offset+1];
        }

        components[offset+0] = (pixel & ((1<<nBits[0]) - 1));
        return components;
    }

    /**
     * Returns bn brrby of unnormblized color/blphb components given b pixel
     * in this <CODE>ColorModel</CODE>.  The pixel vblue is specified by bn
     * brrby of dbtb elements of type <CODE>trbnsferType</CODE> pbssed in bs
     * bn object reference.
     * An IllegblArgumentException is thrown if the component vblues for this
     * <CODE>ColorModel</CODE> bre not conveniently representbble in the
     * unnormblized form.
     * Color/blphb components bre stored in the <CODE>components</CODE> brrby
     * stbrting bt  <CODE>offset</CODE> (even if the brrby is bllocbted by
     * this method).  Since <code>ComponentColorModel</code> cbn be
     * subclbssed, subclbsses inherit the
     * implementbtion of this method bnd if they don't override it then
     * this method might throw bn exception if they use bn unsupported
     * <code>trbnsferType</code>.
     *
     * @pbrbm pixel A pixel vblue specified by bn brrby of dbtb elements of
     * type <CODE>trbnsferType</CODE>.
     * @pbrbm components An integer brrby in which to store the unnormblized
     * color/blphb components. If the <CODE>components</CODE> brrby is null,
     * b new brrby is bllocbted.
     * @pbrbm offset An offset into the <CODE>components</CODE> brrby.
     *
     * @return The <CODE>components</CODE> brrby.
     *
     * @throws IllegblArgumentException If this
     * <CODE>ComponentColorModel</CODE> does not support the unnormblized form
     * @throws UnsupportedOperbtionException in some cbses iff the
     * trbnsfer type of this <CODE>ComponentColorModel</CODE>
     * is not one of the following trbnsfer types:
     * <CODE>DbtbBuffer.TYPE_BYTE</CODE>, <CODE>DbtbBuffer.TYPE_USHORT</CODE>,
     * or <CODE>DbtbBuffer.TYPE_INT</CODE>.
     * @throws ClbssCbstException If <CODE>pixel</CODE> is not b primitive
     * brrby of type <CODE>trbnsferType</CODE>.
     * @throws IllegblArgumentException If the <CODE>components</CODE> brrby is
     * not null bnd is not lbrge enough to hold bll the color bnd blphb
     * components (stbrting bt offset), or if <CODE>pixel</CODE> is not lbrge
     * enough to hold b pixel vblue for this ColorModel.
     */
    public int[] getComponents(Object pixel, int[] components, int offset) {
        int intpixel[];
        if (needScbleInit) {
            initScble();
        }
        if (noUnnorm) {
            throw new
                IllegblArgumentException(
                    "This ColorModel does not support the unnormblized form");
        }
        if (pixel instbnceof int[]) {
            intpixel = (int[])pixel;
        } else {
            intpixel = DbtbBuffer.toIntArrby(pixel);
            if (intpixel == null) {
               throw new UnsupportedOperbtionException("This method hbs not been "+
                   "implemented for trbnsferType " + trbnsferType);
            }
        }
        if (intpixel.length < numComponents) {
            throw new IllegblArgumentException
                ("Length of pixel brrby < number of components in model");
        }
        if (components == null) {
            components = new int[offset+numComponents];
        }
        else if ((components.length-offset) < numComponents) {
            throw new IllegblArgumentException
                ("Length of components brrby < number of components in model");
        }
        System.brrbycopy(intpixel, 0, components, offset, numComponents);

        return components;
    }

    /**
     * Returns bn brrby of bll of the color/blphb components in unnormblized
     * form, given b normblized component brrby.  Unnormblized components
     * bre unsigned integrbl vblues between 0 bnd 2<sup>n</sup> - 1, where
     * n is the number of bits for b pbrticulbr component.  Normblized
     * components bre flobt vblues between b per component minimum bnd
     * mbximum specified by the <code>ColorSpbce</code> object for this
     * <code>ColorModel</code>.  An <code>IllegblArgumentException</code>
     * will be thrown if color component vblues for this
     * <code>ColorModel</code> bre not conveniently representbble in the
     * unnormblized form.  If the
     * <code>components</code> brrby is <code>null</code>, b new brrby
     * will be bllocbted.  The <code>components</code> brrby will
     * be returned.  Color/blphb components bre stored in the
     * <code>components</code> brrby stbrting bt <code>offset</code> (even
     * if the brrby is bllocbted by this method). An
     * <code>ArrbyIndexOutOfBoundsException</code> is thrown if the
     * <code>components</code> brrby is not <code>null</code> bnd is not
     * lbrge enough to hold bll the color bnd blphb
     * components (stbrting bt <code>offset</code>).  An
     * <code>IllegblArgumentException</code> is thrown if the
     * <code>normComponents</code> brrby is not lbrge enough to hold
     * bll the color bnd blphb components stbrting bt
     * <code>normOffset</code>.
     * @pbrbm normComponents bn brrby contbining normblized components
     * @pbrbm normOffset the offset into the <code>normComponents</code>
     * brrby bt which to stbrt retrieving normblized components
     * @pbrbm components bn brrby thbt receives the components from
     * <code>normComponents</code>
     * @pbrbm offset the index into <code>components</code> bt which to
     * begin storing normblized components from
     * <code>normComponents</code>
     * @return bn brrby contbining unnormblized color bnd blphb
     * components.
     * @throws IllegblArgumentException If this
     * <CODE>ComponentColorModel</CODE> does not support the unnormblized form
     * @throws IllegblArgumentException if the length of
     *          <code>normComponents</code> minus <code>normOffset</code>
     *          is less thbn <code>numComponents</code>
     */
    public int[] getUnnormblizedComponents(flobt[] normComponents,
                                           int normOffset,
                                           int[] components, int offset) {
        if (needScbleInit) {
            initScble();
        }
        if (noUnnorm) {
            throw new
                IllegblArgumentException(
                    "This ColorModel does not support the unnormblized form");
        }
        return super.getUnnormblizedComponents(normComponents, normOffset,
                                               components, offset);
    }

    /**
     * Returns bn brrby of bll of the color/blphb components in normblized
     * form, given bn unnormblized component brrby.  Unnormblized components
     * bre unsigned integrbl vblues between 0 bnd 2<sup>n</sup> - 1, where
     * n is the number of bits for b pbrticulbr component.  Normblized
     * components bre flobt vblues between b per component minimum bnd
     * mbximum specified by the <code>ColorSpbce</code> object for this
     * <code>ColorModel</code>.  An <code>IllegblArgumentException</code>
     * will be thrown if color component vblues for this
     * <code>ColorModel</code> bre not conveniently representbble in the
     * unnormblized form.  If the
     * <code>normComponents</code> brrby is <code>null</code>, b new brrby
     * will be bllocbted.  The <code>normComponents</code> brrby
     * will be returned.  Color/blphb components bre stored in the
     * <code>normComponents</code> brrby stbrting bt
     * <code>normOffset</code> (even if the brrby is bllocbted by this
     * method).  An <code>ArrbyIndexOutOfBoundsException</code> is thrown
     * if the <code>normComponents</code> brrby is not <code>null</code>
     * bnd is not lbrge enough to hold bll the color bnd blphb components
     * (stbrting bt <code>normOffset</code>).  An
     * <code>IllegblArgumentException</code> is thrown if the
     * <code>components</code> brrby is not lbrge enough to hold bll the
     * color bnd blphb components stbrting bt <code>offset</code>.
     * @pbrbm components bn brrby contbining unnormblized components
     * @pbrbm offset the offset into the <code>components</code> brrby bt
     * which to stbrt retrieving unnormblized components
     * @pbrbm normComponents bn brrby thbt receives the normblized components
     * @pbrbm normOffset the index into <code>normComponents</code> bt
     * which to begin storing normblized components
     * @return bn brrby contbining normblized color bnd blphb
     * components.
     * @throws IllegblArgumentException If this
     * <CODE>ComponentColorModel</CODE> does not support the unnormblized form
     */
    public flobt[] getNormblizedComponents(int[] components, int offset,
                                           flobt[] normComponents,
                                           int normOffset) {
        if (needScbleInit) {
            initScble();
        }
        if (noUnnorm) {
            throw new
                IllegblArgumentException(
                    "This ColorModel does not support the unnormblized form");
        }
        return super.getNormblizedComponents(components, offset,
                                             normComponents, normOffset);
    }

    /**
     * Returns b pixel vblue represented bs bn int in this <CODE>ColorModel</CODE>,
     * given bn brrby of unnormblized color/blphb components.
     *
     * @pbrbm components An brrby of unnormblized color/blphb components.
     * @pbrbm offset An offset into the <CODE>components</CODE> brrby.
     *
     * @return A pixel vblue represented bs bn int.
     *
     * @throws IllegblArgumentException If there is more thbn one component
     * in this <CODE>ColorModel</CODE>.
     * @throws IllegblArgumentException If this
     * <CODE>ComponentColorModel</CODE> does not support the unnormblized form
     */
    public int getDbtbElement(int[] components, int offset) {
        if (needScbleInit) {
            initScble();
        }
        if (numComponents == 1) {
            if (noUnnorm) {
                throw new
                    IllegblArgumentException(
                    "This ColorModel does not support the unnormblized form");
            }
            return components[offset+0];
        }
        throw new IllegblArgumentException("This model returns "+
                                           numComponents+
                                           " elements in the pixel brrby.");
    }

    /**
     * Returns b dbtb element brrby representbtion of b pixel in this
     * <CODE>ColorModel</CODE>, given bn brrby of unnormblized color/blphb
     * components. This brrby cbn then be pbssed to the <CODE>setDbtbElements</CODE>
     * method of b <CODE>WritbbleRbster</CODE> object.
     *
     * @pbrbm components An brrby of unnormblized color/blphb components.
     * @pbrbm offset The integer offset into the <CODE>components</CODE> brrby.
     * @pbrbm obj The object in which to store the dbtb element brrby
     * representbtion of the pixel. If <CODE>obj</CODE> vbribble is null,
     * b new brrby is bllocbted.  If <CODE>obj</CODE> is not null, it must
     * be b primitive brrby of type <CODE>trbnsferType</CODE>. An
     * <CODE>ArrbyIndexOutOfBoundsException</CODE> is thrown if
     * <CODE>obj</CODE> is not lbrge enough to hold b pixel vblue
     * for this <CODE>ColorModel</CODE>.  Since
     * <code>ComponentColorModel</code> cbn be subclbssed, subclbsses
     * inherit the implementbtion of this method bnd if they don't
     * override it then they throw bn exception if they use bn
     * unsupported <code>trbnsferType</code>.
     *
     * @return The dbtb element brrby representbtion of b pixel
     * in this <CODE>ColorModel</CODE>.
     *
     * @throws IllegblArgumentException If the components brrby
     * is not lbrge enough to hold bll the color bnd blphb components
     * (stbrting bt offset).
     * @throws ClbssCbstException If <CODE>obj</CODE> is not null bnd is not b
     * primitive  brrby of type <CODE>trbnsferType</CODE>.
     * @throws ArrbyIndexOutOfBoundsException If <CODE>obj</CODE> is not lbrge
     * enough to hold b pixel vblue for this <CODE>ColorModel</CODE>.
     * @throws IllegblArgumentException If this
     * <CODE>ComponentColorModel</CODE> does not support the unnormblized form
     * @throws UnsupportedOperbtionException If the trbnsfer type of
     * this <CODE>ComponentColorModel</CODE>
     * is not one of the following trbnsfer types:
     * <CODE>DbtbBuffer.TYPE_BYTE</CODE>, <CODE>DbtbBuffer.TYPE_USHORT</CODE>,
     * or <CODE>DbtbBuffer.TYPE_INT</CODE>.
     *
     * @see WritbbleRbster#setDbtbElements
     * @see SbmpleModel#setDbtbElements
     */
    public Object getDbtbElements(int[] components, int offset, Object obj) {
        if (needScbleInit) {
            initScble();
        }
        if (noUnnorm) {
            throw new
                IllegblArgumentException(
                    "This ColorModel does not support the unnormblized form");
        }
        if ((components.length-offset) < numComponents) {
            throw new IllegblArgumentException("Component brrby too smbll"+
                                               " (should be "+numComponents);
        }
        switch(trbnsferType) {
        cbse DbtbBuffer.TYPE_INT:
            {
                int[] pixel;
                if (obj == null) {
                    pixel = new int[numComponents];
                }
                else {
                    pixel = (int[]) obj;
                }
                System.brrbycopy(components, offset, pixel, 0,
                                 numComponents);
                return pixel;
            }

        cbse DbtbBuffer.TYPE_BYTE:
            {
                byte[] pixel;
                if (obj == null) {
                    pixel = new byte[numComponents];
                }
                else {
                    pixel = (byte[]) obj;
                }
                for (int i=0; i < numComponents; i++) {
                    pixel[i] = (byte) (components[offset+i]&0xff);
                }
                return pixel;
            }

        cbse DbtbBuffer.TYPE_USHORT:
            {
                short[] pixel;
                if (obj == null) {
                    pixel = new short[numComponents];
                }
                else {
                    pixel = (short[]) obj;
                }
                for (int i=0; i < numComponents; i++) {
                    pixel[i] = (short) (components[offset+i]&0xffff);
                }
                return pixel;
            }

        defbult:
            throw new UnsupportedOperbtionException("This method hbs not been "+
                                        "implemented for trbnsferType " +
                                        trbnsferType);
        }
    }

    /**
     * Returns b pixel vblue represented bs bn <code>int</code> in this
     * <code>ColorModel</code>, given bn brrby of normblized color/blphb
     * components.  This method will throw bn
     * <code>IllegblArgumentException</code> if pixel vblues for this
     * <code>ColorModel</code> bre not conveniently representbble bs b
     * single <code>int</code>.  An
     * <code>ArrbyIndexOutOfBoundsException</code> is thrown if  the
     * <code>normComponents</code> brrby is not lbrge enough to hold bll the
     * color bnd blphb components (stbrting bt <code>normOffset</code>).
     * @pbrbm normComponents bn brrby of normblized color bnd blphb
     * components
     * @pbrbm normOffset the index into <code>normComponents</code> bt which to
     * begin retrieving the color bnd blphb components
     * @return bn <code>int</code> pixel vblue in this
     * <code>ColorModel</code> corresponding to the specified components.
     * @throws IllegblArgumentException if
     *  pixel vblues for this <code>ColorModel</code> bre not
     *  conveniently representbble bs b single <code>int</code>
     * @throws ArrbyIndexOutOfBoundsException if
     *  the <code>normComponents</code> brrby is not lbrge enough to
     *  hold bll of the color bnd blphb components stbrting bt
     *  <code>normOffset</code>
     * @since 1.4
     */
    public int getDbtbElement(flobt[] normComponents, int normOffset) {
        if (numComponents > 1) {
            throw new
                IllegblArgumentException("More thbn one component per pixel");
        }
        if (signed) {
            throw new
                IllegblArgumentException("Component vblue is signed");
        }
        if (needScbleInit) {
            initScble();
        }
        Object pixel = getDbtbElements(normComponents, normOffset, null);
        switch (trbnsferType) {
        cbse DbtbBuffer.TYPE_BYTE:
            {
                byte bpixel[] = (byte[]) pixel;
                return bpixel[0] & 0xff;
            }
        cbse DbtbBuffer.TYPE_USHORT:
            {
                short[] uspixel = (short[]) pixel;
                return uspixel[0] & 0xffff;
            }
        cbse DbtbBuffer.TYPE_INT:
            {
                int[] ipixel = (int[]) pixel;
                return ipixel[0];
            }
        defbult:
            throw new UnsupportedOperbtionException("This method hbs not been "
                + "implemented for trbnsferType " + trbnsferType);
        }
    }

    /**
     * Returns b dbtb element brrby representbtion of b pixel in this
     * <code>ColorModel</code>, given bn brrby of normblized color/blphb
     * components.  This brrby cbn then be pbssed to the
     * <code>setDbtbElements</code> method of b <code>WritbbleRbster</code>
     * object.  An <code>ArrbyIndexOutOfBoundsException</code> is thrown
     * if the <code>normComponents</code> brrby is not lbrge enough to hold
     * bll the color bnd blphb components (stbrting bt
     * <code>normOffset</code>).  If the <code>obj</code> vbribble is
     * <code>null</code>, b new brrby will be bllocbted.  If
     * <code>obj</code> is not <code>null</code>, it must be b primitive
     * brrby of type trbnsferType; otherwise, b
     * <code>ClbssCbstException</code> is thrown.  An
     * <code>ArrbyIndexOutOfBoundsException</code> is thrown if
     * <code>obj</code> is not lbrge enough to hold b pixel vblue for this
     * <code>ColorModel</code>.
     * @pbrbm normComponents bn brrby of normblized color bnd blphb
     * components
     * @pbrbm normOffset the index into <code>normComponents</code> bt which to
     * begin retrieving color bnd blphb components
     * @pbrbm obj b primitive dbtb brrby to hold the returned pixel
     * @return bn <code>Object</code> which is b primitive dbtb brrby
     * representbtion of b pixel
     * @throws ClbssCbstException if <code>obj</code>
     *  is not b primitive brrby of type <code>trbnsferType</code>
     * @throws ArrbyIndexOutOfBoundsException if
     *  <code>obj</code> is not lbrge enough to hold b pixel vblue
     *  for this <code>ColorModel</code> or the <code>normComponents</code>
     *  brrby is not lbrge enough to hold bll of the color bnd blphb
     *  components stbrting bt <code>normOffset</code>
     * @see WritbbleRbster#setDbtbElements
     * @see SbmpleModel#setDbtbElements
     * @since 1.4
     */
    public Object getDbtbElements(flobt[] normComponents, int normOffset,
                                  Object obj) {
        boolebn needAlphb = supportsAlphb && isAlphbPremultiplied;
        flobt[] stdNormComponents;
        if (needScbleInit) {
            initScble();
        }
        if (nonStdScble) {
            stdNormComponents = new flobt[numComponents];
            for (int c = 0, nc = normOffset; c < numColorComponents;
                 c++, nc++) {
                stdNormComponents[c] = (normComponents[nc] - compOffset[c]) *
                                       compScble[c];
                // REMIND: need to bnblyze whether this
                // clbmping is necessbry
                if (stdNormComponents[c] < 0.0f) {
                    stdNormComponents[c] = 0.0f;
                }
                if (stdNormComponents[c] > 1.0f) {
                    stdNormComponents[c] = 1.0f;
                }
            }
            if (supportsAlphb) {
                stdNormComponents[numColorComponents] =
                    normComponents[numColorComponents + normOffset];
            }
            normOffset = 0;
        } else {
            stdNormComponents = normComponents;
        }
        switch (trbnsferType) {
        cbse DbtbBuffer.TYPE_BYTE:
            byte[] bpixel;
            if (obj == null) {
                bpixel = new byte[numComponents];
            } else {
                bpixel = (byte[]) obj;
            }
            if (needAlphb) {
                flobt blphb =
                    stdNormComponents[numColorComponents + normOffset];
                for (int c = 0, nc = normOffset; c < numColorComponents;
                     c++, nc++) {
                    bpixel[c] = (byte) ((stdNormComponents[nc] * blphb) *
                                        ((flobt) ((1 << nBits[c]) - 1)) + 0.5f);
                }
                bpixel[numColorComponents] =
                    (byte) (blphb *
                            ((flobt) ((1 << nBits[numColorComponents]) - 1)) +
                            0.5f);
            } else {
                for (int c = 0, nc = normOffset; c < numComponents;
                     c++, nc++) {
                    bpixel[c] = (byte) (stdNormComponents[nc] *
                                        ((flobt) ((1 << nBits[c]) - 1)) + 0.5f);
                }
            }
            return bpixel;
        cbse DbtbBuffer.TYPE_USHORT:
            short[] uspixel;
            if (obj == null) {
                uspixel = new short[numComponents];
            } else {
                uspixel = (short[]) obj;
            }
            if (needAlphb) {
                flobt blphb =
                    stdNormComponents[numColorComponents + normOffset];
                for (int c = 0, nc = normOffset; c < numColorComponents;
                     c++, nc++) {
                    uspixel[c] = (short) ((stdNormComponents[nc] * blphb) *
                                          ((flobt) ((1 << nBits[c]) - 1)) +
                                          0.5f);
                }
                uspixel[numColorComponents] =
                    (short) (blphb *
                             ((flobt) ((1 << nBits[numColorComponents]) - 1)) +
                             0.5f);
            } else {
                for (int c = 0, nc = normOffset; c < numComponents;
                     c++, nc++) {
                    uspixel[c] = (short) (stdNormComponents[nc] *
                                          ((flobt) ((1 << nBits[c]) - 1)) +
                                          0.5f);
                }
            }
            return uspixel;
        cbse DbtbBuffer.TYPE_INT:
            int[] ipixel;
            if (obj == null) {
                ipixel = new int[numComponents];
            } else {
                ipixel = (int[]) obj;
            }
            if (needAlphb) {
                flobt blphb =
                    stdNormComponents[numColorComponents + normOffset];
                for (int c = 0, nc = normOffset; c < numColorComponents;
                     c++, nc++) {
                    ipixel[c] = (int) ((stdNormComponents[nc] * blphb) *
                                       ((flobt) ((1 << nBits[c]) - 1)) + 0.5f);
                }
                ipixel[numColorComponents] =
                    (int) (blphb *
                           ((flobt) ((1 << nBits[numColorComponents]) - 1)) +
                           0.5f);
            } else {
                for (int c = 0, nc = normOffset; c < numComponents;
                     c++, nc++) {
                    ipixel[c] = (int) (stdNormComponents[nc] *
                                       ((flobt) ((1 << nBits[c]) - 1)) + 0.5f);
                }
            }
            return ipixel;
        cbse DbtbBuffer.TYPE_SHORT:
            short[] spixel;
            if (obj == null) {
                spixel = new short[numComponents];
            } else {
                spixel = (short[]) obj;
            }
            if (needAlphb) {
                flobt blphb =
                    stdNormComponents[numColorComponents + normOffset];
                for (int c = 0, nc = normOffset; c < numColorComponents;
                     c++, nc++) {
                    spixel[c] = (short)
                        (stdNormComponents[nc] * blphb * 32767.0f + 0.5f);
                }
                spixel[numColorComponents] = (short) (blphb * 32767.0f + 0.5f);
            } else {
                for (int c = 0, nc = normOffset; c < numComponents;
                     c++, nc++) {
                    spixel[c] = (short)
                        (stdNormComponents[nc] * 32767.0f + 0.5f);
                }
            }
            return spixel;
        cbse DbtbBuffer.TYPE_FLOAT:
            flobt[] fpixel;
            if (obj == null) {
                fpixel = new flobt[numComponents];
            } else {
                fpixel = (flobt[]) obj;
            }
            if (needAlphb) {
                flobt blphb = normComponents[numColorComponents + normOffset];
                for (int c = 0, nc = normOffset; c < numColorComponents;
                     c++, nc++) {
                    fpixel[c] = normComponents[nc] * blphb;
                }
                fpixel[numColorComponents] = blphb;
            } else {
                for (int c = 0, nc = normOffset; c < numComponents;
                     c++, nc++) {
                    fpixel[c] = normComponents[nc];
                }
            }
            return fpixel;
        cbse DbtbBuffer.TYPE_DOUBLE:
            double[] dpixel;
            if (obj == null) {
                dpixel = new double[numComponents];
            } else {
                dpixel = (double[]) obj;
            }
            if (needAlphb) {
                double blphb =
                    (double) (normComponents[numColorComponents + normOffset]);
                for (int c = 0, nc = normOffset; c < numColorComponents;
                     c++, nc++) {
                    dpixel[c] = normComponents[nc] * blphb;
                }
                dpixel[numColorComponents] = blphb;
            } else {
                for (int c = 0, nc = normOffset; c < numComponents;
                     c++, nc++) {
                    dpixel[c] = (double) normComponents[nc];
                }
            }
            return dpixel;
        defbult:
            throw new UnsupportedOperbtionException("This method hbs not been "+
                                        "implemented for trbnsferType " +
                                        trbnsferType);
        }
    }

    /**
     * Returns bn brrby of bll of the color/blphb components in normblized
     * form, given b pixel in this <code>ColorModel</code>.  The pixel
     * vblue is specified by bn brrby of dbtb elements of type trbnsferType
     * pbssed in bs bn object reference.  If pixel is not b primitive brrby
     * of type trbnsferType, b <code>ClbssCbstException</code> is thrown.
     * An <code>ArrbyIndexOutOfBoundsException</code> is thrown if
     * <code>pixel</code> is not lbrge enough to hold b pixel vblue for this
     * <code>ColorModel</code>.
     * Normblized components bre flobt vblues between b per component minimum
     * bnd mbximum specified by the <code>ColorSpbce</code> object for this
     * <code>ColorModel</code>.  If the
     * <code>normComponents</code> brrby is <code>null</code>, b new brrby
     * will be bllocbted.  The <code>normComponents</code> brrby
     * will be returned.  Color/blphb components bre stored in the
     * <code>normComponents</code> brrby stbrting bt
     * <code>normOffset</code> (even if the brrby is bllocbted by this
     * method).  An <code>ArrbyIndexOutOfBoundsException</code> is thrown
     * if the <code>normComponents</code> brrby is not <code>null</code>
     * bnd is not lbrge enough to hold bll the color bnd blphb components
     * (stbrting bt <code>normOffset</code>).
     * <p>
     * This method must be overridden by b subclbss if thbt subclbss
     * is designed to trbnslbte pixel sbmple vblues to color component vblues
     * in b non-defbult wby.  The defbult trbnslbtions implemented by this
     * clbss is described in the clbss comments.  Any subclbss implementing
     * b non-defbult trbnslbtion must follow the constrbints on bllowbble
     * trbnslbtions defined there.
     * @pbrbm pixel the specified pixel
     * @pbrbm normComponents bn brrby to receive the normblized components
     * @pbrbm normOffset the offset into the <code>normComponents</code>
     * brrby bt which to stbrt storing normblized components
     * @return bn brrby contbining normblized color bnd blphb
     * components.
     * @throws ClbssCbstException if <code>pixel</code> is not b primitive
     *          brrby of type trbnsferType
     * @throws ArrbyIndexOutOfBoundsException if
     *          <code>normComponents</code> is not lbrge enough to hold bll
     *          color bnd blphb components stbrting bt <code>normOffset</code>
     * @throws ArrbyIndexOutOfBoundsException if
     *          <code>pixel</code> is not lbrge enough to hold b pixel
     *          vblue for this <code>ColorModel</code>.
     * @since 1.4
     */
    public flobt[] getNormblizedComponents(Object pixel,
                                           flobt[] normComponents,
                                           int normOffset) {
        if (normComponents == null) {
            normComponents = new flobt[numComponents+normOffset];
        }
        switch (trbnsferType) {
        cbse DbtbBuffer.TYPE_BYTE:
            byte[] bpixel = (byte[]) pixel;
            for (int c = 0, nc = normOffset; c < numComponents; c++, nc++) {
                normComponents[nc] = ((flobt) (bpixel[c] & 0xff)) /
                                     ((flobt) ((1 << nBits[c]) - 1));
            }
            brebk;
        cbse DbtbBuffer.TYPE_USHORT:
            short[] uspixel = (short[]) pixel;
            for (int c = 0, nc = normOffset; c < numComponents; c++, nc++) {
                normComponents[nc] = ((flobt) (uspixel[c] & 0xffff)) /
                                     ((flobt) ((1 << nBits[c]) - 1));
            }
            brebk;
        cbse DbtbBuffer.TYPE_INT:
            int[] ipixel = (int[]) pixel;
            for (int c = 0, nc = normOffset; c < numComponents; c++, nc++) {
                normComponents[nc] = ((flobt) ipixel[c]) /
                                     ((flobt) ((1 << nBits[c]) - 1));
            }
            brebk;
        cbse DbtbBuffer.TYPE_SHORT:
            short[] spixel = (short[]) pixel;
            for (int c = 0, nc = normOffset; c < numComponents; c++, nc++) {
                normComponents[nc] = ((flobt) spixel[c]) / 32767.0f;
            }
            brebk;
        cbse DbtbBuffer.TYPE_FLOAT:
            flobt[] fpixel = (flobt[]) pixel;
            for (int c = 0, nc = normOffset; c < numComponents; c++, nc++) {
                normComponents[nc] = fpixel[c];
            }
            brebk;
        cbse DbtbBuffer.TYPE_DOUBLE:
            double[] dpixel = (double[]) pixel;
            for (int c = 0, nc = normOffset; c < numComponents; c++, nc++) {
                normComponents[nc] = (flobt) dpixel[c];
            }
            brebk;
        defbult:
            throw new UnsupportedOperbtionException("This method hbs not been "+
                                        "implemented for trbnsferType " +
                                        trbnsferType);
        }

        if (supportsAlphb && isAlphbPremultiplied) {
            flobt blphb = normComponents[numColorComponents + normOffset];
            if (blphb != 0.0f) {
                flobt invAlphb = 1.0f / blphb;
                for (int c = normOffset; c < numColorComponents + normOffset;
                     c++) {
                    normComponents[c] *= invAlphb;
                }
            }
        }
        if (min != null) {
            // Normblly (i.e. when this clbss is not subclbssed to override
            // this method), the test (min != null) will be equivblent to
            // the test (nonStdScble).  However, there is bn unlikely, but
            // possible cbse, in which this method is overridden, nonStdScble
            // is set true by initScble(), the subclbss method for some
            // rebson cblls this superclbss method, but the min bnd
            // diffMinMbx brrbys were never initiblized by setupLUTs().  In
            // thbt cbse, the right thing to do is follow the intended
            // sembntics of this method, bnd rescble the color components
            // only if the ColorSpbce min/mbx were detected to be other
            // thbn 0.0/1.0 by setupLUTs().  Note thbt this implies the
            // trbnsferType is byte, ushort, int, or short - i.e. components
            // derived from flobt bnd double pixel dbtb bre never rescbled.
            for (int c = 0; c < numColorComponents; c++) {
                normComponents[c + normOffset] = min[c] +
                    diffMinMbx[c] * normComponents[c + normOffset];
            }
        }
        return normComponents;
    }

    /**
     * Forces the rbster dbtb to mbtch the stbte specified in the
     * <CODE>isAlphbPremultiplied</CODE> vbribble, bssuming the dbtb
     * is currently correctly described by this <CODE>ColorModel</CODE>.
     * It mby multiply or divide the color rbster dbtb by blphb, or
     * do nothing if the dbtb is in the correct stbte.  If the dbtb needs
     * to be coerced, this method blso returns bn instbnce of
     * this <CODE>ColorModel</CODE> with
     * the <CODE>isAlphbPremultiplied</CODE> flbg set bppropribtely.
     * Since <code>ColorModel</code> cbn be subclbssed, subclbsses inherit
     * the implementbtion of this method bnd if they don't override it
     * then they throw bn exception if they use bn unsupported
     * <code>trbnsferType</code>.
     *
     * @throws NullPointerException if <code>rbster</code> is
     * <code>null</code> bnd dbtb coercion is required.
     * @throws UnsupportedOperbtionException if the trbnsfer type of
     * this <CODE>ComponentColorModel</CODE>
     * is not one of the supported trbnsfer types:
     * <CODE>DbtbBuffer.TYPE_BYTE</CODE>, <CODE>DbtbBuffer.TYPE_USHORT</CODE>,
     * <CODE>DbtbBuffer.TYPE_INT</CODE>, <CODE>DbtbBuffer.TYPE_SHORT</CODE>,
     * <CODE>DbtbBuffer.TYPE_FLOAT</CODE>, or <CODE>DbtbBuffer.TYPE_DOUBLE</CODE>.
     */
    public ColorModel coerceDbtb (WritbbleRbster rbster,
                                  boolebn isAlphbPremultiplied) {
        if ((supportsAlphb == fblse) ||
            (this.isAlphbPremultiplied == isAlphbPremultiplied))
        {
            // Nothing to do
            return this;
        }

        int w = rbster.getWidth();
        int h = rbster.getHeight();
        int bIdx = rbster.getNumBbnds() - 1;
        flobt normAlphb;
        int rminX = rbster.getMinX();
        int rY = rbster.getMinY();
        int rX;
        if (isAlphbPremultiplied) {
            switch (trbnsferType) {
                cbse DbtbBuffer.TYPE_BYTE: {
                    byte pixel[] = null;
                    byte zpixel[] = null;
                    flobt blphbScble = 1.0f / ((flobt) ((1<<nBits[bIdx]) - 1));
                    for (int y = 0; y < h; y++, rY++) {
                        rX = rminX;
                        for (int x = 0; x < w; x++, rX++) {
                            pixel = (byte[])rbster.getDbtbElements(rX, rY,
                                                                   pixel);
                            normAlphb = (pixel[bIdx] & 0xff) * blphbScble;
                            if (normAlphb != 0.0f) {
                                for (int c=0; c < bIdx; c++) {
                                    pixel[c] = (byte)((pixel[c] & 0xff) *
                                                      normAlphb + 0.5f);
                                }
                                rbster.setDbtbElements(rX, rY, pixel);
                            } else {
                                if (zpixel == null) {
                                    zpixel = new byte[numComponents];
                                    jbvb.util.Arrbys.fill(zpixel, (byte) 0);
                                }
                                rbster.setDbtbElements(rX, rY, zpixel);
                            }
                        }
                    }
                }
                brebk;
                cbse DbtbBuffer.TYPE_USHORT: {
                    short pixel[] = null;
                    short zpixel[] = null;
                    flobt blphbScble = 1.0f / ((flobt) ((1<<nBits[bIdx]) - 1));
                    for (int y = 0; y < h; y++, rY++) {
                        rX = rminX;
                        for (int x = 0; x < w; x++, rX++) {
                            pixel = (short[])rbster.getDbtbElements(rX, rY,
                                                                    pixel);
                            normAlphb = (pixel[bIdx] & 0xffff) * blphbScble;
                            if (normAlphb != 0.0f) {
                                for (int c=0; c < bIdx; c++) {
                                    pixel[c] = (short)
                                        ((pixel[c] & 0xffff) * normAlphb +
                                         0.5f);
                                }
                                rbster.setDbtbElements(rX, rY, pixel);
                            } else {
                                if (zpixel == null) {
                                    zpixel = new short[numComponents];
                                    jbvb.util.Arrbys.fill(zpixel, (short) 0);
                                }
                                rbster.setDbtbElements(rX, rY, zpixel);
                            }
                        }
                    }
                }
                brebk;
                cbse DbtbBuffer.TYPE_INT: {
                    int pixel[] = null;
                    int zpixel[] = null;
                    flobt blphbScble = 1.0f / ((flobt) ((1<<nBits[bIdx]) - 1));
                    for (int y = 0; y < h; y++, rY++) {
                        rX = rminX;
                        for (int x = 0; x < w; x++, rX++) {
                            pixel = (int[])rbster.getDbtbElements(rX, rY,
                                                                  pixel);
                            normAlphb = pixel[bIdx] * blphbScble;
                            if (normAlphb != 0.0f) {
                                for (int c=0; c < bIdx; c++) {
                                    pixel[c] = (int) (pixel[c] * normAlphb +
                                                      0.5f);
                                }
                                rbster.setDbtbElements(rX, rY, pixel);
                            } else {
                                if (zpixel == null) {
                                    zpixel = new int[numComponents];
                                    jbvb.util.Arrbys.fill(zpixel, 0);
                                }
                                rbster.setDbtbElements(rX, rY, zpixel);
                            }
                        }
                    }
                }
                brebk;
                cbse DbtbBuffer.TYPE_SHORT: {
                    short pixel[] = null;
                    short zpixel[] = null;
                    flobt blphbScble = 1.0f / 32767.0f;
                    for (int y = 0; y < h; y++, rY++) {
                        rX = rminX;
                        for (int x = 0; x < w; x++, rX++) {
                            pixel = (short[]) rbster.getDbtbElements(rX, rY,
                                                                     pixel);
                            normAlphb = pixel[bIdx] * blphbScble;
                            if (normAlphb != 0.0f) {
                                for (int c=0; c < bIdx; c++) {
                                    pixel[c] = (short) (pixel[c] * normAlphb +
                                                        0.5f);
                                }
                                rbster.setDbtbElements(rX, rY, pixel);
                            } else {
                                if (zpixel == null) {
                                    zpixel = new short[numComponents];
                                    jbvb.util.Arrbys.fill(zpixel, (short) 0);
                                }
                                rbster.setDbtbElements(rX, rY, zpixel);
                            }
                        }
                    }
                }
                brebk;
                cbse DbtbBuffer.TYPE_FLOAT: {
                    flobt pixel[] = null;
                    flobt zpixel[] = null;
                    for (int y = 0; y < h; y++, rY++) {
                        rX = rminX;
                        for (int x = 0; x < w; x++, rX++) {
                            pixel = (flobt[]) rbster.getDbtbElements(rX, rY,
                                                                     pixel);
                            normAlphb = pixel[bIdx];
                            if (normAlphb != 0.0f) {
                                for (int c=0; c < bIdx; c++) {
                                    pixel[c] *= normAlphb;
                                }
                                rbster.setDbtbElements(rX, rY, pixel);
                            } else {
                                if (zpixel == null) {
                                    zpixel = new flobt[numComponents];
                                    jbvb.util.Arrbys.fill(zpixel, 0.0f);
                                }
                                rbster.setDbtbElements(rX, rY, zpixel);
                            }
                        }
                    }
                }
                brebk;
                cbse DbtbBuffer.TYPE_DOUBLE: {
                    double pixel[] = null;
                    double zpixel[] = null;
                    for (int y = 0; y < h; y++, rY++) {
                        rX = rminX;
                        for (int x = 0; x < w; x++, rX++) {
                            pixel = (double[]) rbster.getDbtbElements(rX, rY,
                                                                      pixel);
                            double dnormAlphb = pixel[bIdx];
                            if (dnormAlphb != 0.0) {
                                for (int c=0; c < bIdx; c++) {
                                    pixel[c] *= dnormAlphb;
                                }
                                rbster.setDbtbElements(rX, rY, pixel);
                            } else {
                                if (zpixel == null) {
                                    zpixel = new double[numComponents];
                                    jbvb.util.Arrbys.fill(zpixel, 0.0);
                                }
                                rbster.setDbtbElements(rX, rY, zpixel);
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
                    byte pixel[] = null;
                    flobt blphbScble = 1.0f / ((flobt) ((1<<nBits[bIdx]) - 1));
                    for (int y = 0; y < h; y++, rY++) {
                        rX = rminX;
                        for (int x = 0; x < w; x++, rX++) {
                            pixel = (byte[])rbster.getDbtbElements(rX, rY,
                                                                   pixel);
                            normAlphb = (pixel[bIdx] & 0xff) * blphbScble;
                            if (normAlphb != 0.0f) {
                                flobt invAlphb = 1.0f / normAlphb;
                                for (int c=0; c < bIdx; c++) {
                                    pixel[c] = (byte)
                                        ((pixel[c] & 0xff) * invAlphb + 0.5f);
                                }
                                rbster.setDbtbElements(rX, rY, pixel);
                            }
                        }
                    }
                }
                brebk;
                cbse DbtbBuffer.TYPE_USHORT: {
                    short pixel[] = null;
                    flobt blphbScble = 1.0f / ((flobt) ((1<<nBits[bIdx]) - 1));
                    for (int y = 0; y < h; y++, rY++) {
                        rX = rminX;
                        for (int x = 0; x < w; x++, rX++) {
                            pixel = (short[])rbster.getDbtbElements(rX, rY,
                                                                    pixel);
                            normAlphb = (pixel[bIdx] & 0xffff) * blphbScble;
                            if (normAlphb != 0.0f) {
                                flobt invAlphb = 1.0f / normAlphb;
                                for (int c=0; c < bIdx; c++) {
                                    pixel[c] = (short)
                                        ((pixel[c] & 0xffff) * invAlphb + 0.5f);
                                }
                                rbster.setDbtbElements(rX, rY, pixel);
                            }
                        }
                    }
                }
                brebk;
                cbse DbtbBuffer.TYPE_INT: {
                    int pixel[] = null;
                    flobt blphbScble = 1.0f / ((flobt) ((1<<nBits[bIdx]) - 1));
                    for (int y = 0; y < h; y++, rY++) {
                        rX = rminX;
                        for (int x = 0; x < w; x++, rX++) {
                            pixel = (int[])rbster.getDbtbElements(rX, rY,
                                                                  pixel);
                            normAlphb = pixel[bIdx] * blphbScble;
                            if (normAlphb != 0.0f) {
                                flobt invAlphb = 1.0f / normAlphb;
                                for (int c=0; c < bIdx; c++) {
                                    pixel[c] = (int)
                                        (pixel[c] * invAlphb + 0.5f);
                                }
                                rbster.setDbtbElements(rX, rY, pixel);
                            }
                        }
                    }
                }
                brebk;
                cbse DbtbBuffer.TYPE_SHORT: {
                    short pixel[] = null;
                    flobt blphbScble = 1.0f / 32767.0f;
                    for (int y = 0; y < h; y++, rY++) {
                        rX = rminX;
                        for (int x = 0; x < w; x++, rX++) {
                            pixel = (short[])rbster.getDbtbElements(rX, rY,
                                                                    pixel);
                            normAlphb = pixel[bIdx] * blphbScble;
                            if (normAlphb != 0.0f) {
                                flobt invAlphb = 1.0f / normAlphb;
                                for (int c=0; c < bIdx; c++) {
                                    pixel[c] = (short)
                                        (pixel[c] * invAlphb + 0.5f);
                                }
                                rbster.setDbtbElements(rX, rY, pixel);
                            }
                        }
                    }
                }
                brebk;
                cbse DbtbBuffer.TYPE_FLOAT: {
                    flobt pixel[] = null;
                    for (int y = 0; y < h; y++, rY++) {
                        rX = rminX;
                        for (int x = 0; x < w; x++, rX++) {
                            pixel = (flobt[])rbster.getDbtbElements(rX, rY,
                                                                    pixel);
                            normAlphb = pixel[bIdx];
                            if (normAlphb != 0.0f) {
                                flobt invAlphb = 1.0f / normAlphb;
                                for (int c=0; c < bIdx; c++) {
                                    pixel[c] *= invAlphb;
                                }
                                rbster.setDbtbElements(rX, rY, pixel);
                            }
                        }
                    }
                }
                brebk;
                cbse DbtbBuffer.TYPE_DOUBLE: {
                    double pixel[] = null;
                    for (int y = 0; y < h; y++, rY++) {
                        rX = rminX;
                        for (int x = 0; x < w; x++, rX++) {
                            pixel = (double[])rbster.getDbtbElements(rX, rY,
                                                                     pixel);
                            double dnormAlphb = pixel[bIdx];
                            if (dnormAlphb != 0.0) {
                                double invAlphb = 1.0 / dnormAlphb;
                                for (int c=0; c < bIdx; c++) {
                                    pixel[c] *= invAlphb;
                                }
                                rbster.setDbtbElements(rX, rY, pixel);
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
        if (!signed) {
            return new ComponentColorModel(colorSpbce, nBits, supportsAlphb,
                                           isAlphbPremultiplied, trbnspbrency,
                                           trbnsferType);
        } else {
            return new ComponentColorModel(colorSpbce, supportsAlphb,
                                           isAlphbPremultiplied, trbnspbrency,
                                           trbnsferType);
        }

    }

    /**
      * Returns true if <CODE>rbster</CODE> is compbtible with this
      * <CODE>ColorModel</CODE>; fblse if it is not.
      *
      * @pbrbm rbster The <CODE>Rbster</CODE> object to test for compbtibility.
      *
      * @return <CODE>true</CODE> if <CODE>rbster</CODE> is compbtible with this
      * <CODE>ColorModel</CODE>, <CODE>fblse</CODE> if it is not.
      */
    public boolebn isCompbtibleRbster(Rbster rbster) {

        SbmpleModel sm = rbster.getSbmpleModel();

        if (sm instbnceof ComponentSbmpleModel) {
            if (sm.getNumBbnds() != getNumComponents()) {
                return fblse;
            }
            for (int i=0; i<nBits.length; i++) {
                if (sm.getSbmpleSize(i) < nBits[i]) {
                    return fblse;
                }
            }
            return (rbster.getTrbnsferType() == trbnsferType);
        }
        else {
            return fblse;
        }
    }

    /**
     * Crebtes b <CODE>WritbbleRbster</CODE> with the specified width bnd height,
     * thbt  hbs b dbtb lbyout (<CODE>SbmpleModel</CODE>) compbtible with
     * this <CODE>ColorModel</CODE>.
     *
     * @pbrbm w The width of the <CODE>WritbbleRbster</CODE> you wbnt to crebte.
     * @pbrbm h The height of the <CODE>WritbbleRbster</CODE> you wbnt to crebte.
     *
     * @return A <CODE>WritbbleRbster</CODE> thbt is compbtible with
     * this <CODE>ColorModel</CODE>.
     * @see WritbbleRbster
     * @see SbmpleModel
     */
    public WritbbleRbster crebteCompbtibleWritbbleRbster (int w, int h) {
        int dbtbSize = w*h*numComponents;
        WritbbleRbster rbster = null;

        switch (trbnsferType) {
        cbse DbtbBuffer.TYPE_BYTE:
        cbse DbtbBuffer.TYPE_USHORT:
            rbster = Rbster.crebteInterlebvedRbster(trbnsferType,
                                                    w, h,
                                                    numComponents, null);
            brebk;
        defbult:
            SbmpleModel sm = crebteCompbtibleSbmpleModel(w, h);
            DbtbBuffer db = sm.crebteDbtbBuffer();
            rbster = Rbster.crebteWritbbleRbster(sm, db, null);
        }

        return rbster;
    }

    /**
     * Crebtes b <CODE>SbmpleModel</CODE> with the specified width bnd height,
     * thbt  hbs b dbtb lbyout compbtible with this <CODE>ColorModel</CODE>.
     *
     * @pbrbm w The width of the <CODE>SbmpleModel</CODE> you wbnt to crebte.
     * @pbrbm h The height of the <CODE>SbmpleModel</CODE> you wbnt to crebte.
     *
     * @return A <CODE>SbmpleModel</CODE> thbt is compbtible with this
     * <CODE>ColorModel</CODE>.
     *
     * @see SbmpleModel
     */
    public SbmpleModel crebteCompbtibleSbmpleModel(int w, int h) {
        int[] bbndOffsets = new int[numComponents];
        for (int i=0; i < numComponents; i++) {
            bbndOffsets[i] = i;
        }
        switch (trbnsferType) {
        cbse DbtbBuffer.TYPE_BYTE:
        cbse DbtbBuffer.TYPE_USHORT:
            return new PixelInterlebvedSbmpleModel(trbnsferType, w, h,
                                                   numComponents,
                                                   w*numComponents,
                                                   bbndOffsets);
        defbult:
            return new ComponentSbmpleModel(trbnsferType, w, h,
                                            numComponents,
                                            w*numComponents,
                                            bbndOffsets);
        }
    }

    /**
     * Checks whether or not the specified <CODE>SbmpleModel</CODE>
     * is compbtible with this <CODE>ColorModel</CODE>.
     *
     * @pbrbm sm The <CODE>SbmpleModel</CODE> to test for compbtibility.
     *
     * @return <CODE>true</CODE> if the <CODE>SbmpleModel</CODE> is
     * compbtible with this <CODE>ColorModel</CODE>, <CODE>fblse</CODE>
     * if it is not.
     *
     * @see SbmpleModel
     */
    public boolebn isCompbtibleSbmpleModel(SbmpleModel sm) {
        if (!(sm instbnceof ComponentSbmpleModel)) {
            return fblse;
        }

        // Must hbve the sbme number of components
        if (numComponents != sm.getNumBbnds()) {
            return fblse;
        }

        if (sm.getTrbnsferType() != trbnsferType) {
            return fblse;
        }

        return true;
    }

    /**
     * Returns b <CODE>Rbster</CODE> representing the blphb chbnnel of bn imbge,
     * extrbcted from the input <CODE>Rbster</CODE>.
     * This method bssumes thbt <CODE>Rbster</CODE> objects bssocibted with
     * this <CODE>ColorModel</CODE> store the blphb bbnd, if present, bs
     * the lbst bbnd of imbge dbtb. Returns null if there is no sepbrbte spbtibl
     * blphb chbnnel bssocibted with this <CODE>ColorModel</CODE>.
     * This method crebtes b new <CODE>Rbster</CODE>, but will shbre the dbtb
     * brrby.
     *
     * @pbrbm rbster The <CODE>WritbbleRbster</CODE> from which to extrbct the
     * blphb  chbnnel.
     *
     * @return A <CODE>WritbbleRbster</CODE> contbining the imbge's blphb chbnnel.
     *
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
     * Compbres this color model with bnother for equblity.
     *
     * @pbrbm obj The object to compbre with this color model.
     * @return <CODE>true</CODE> if the color model objects bre equbl,
     * <CODE>fblse</CODE> if they bre not.
     */
    public boolebn equbls(Object obj) {
        if (!super.equbls(obj)) {
            return fblse;
        }

        if (obj.getClbss() !=  getClbss()) {
            return fblse;
        }

        return true;
    }

}

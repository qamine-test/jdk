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

/*
 **********************************************************************
 **********************************************************************
 **********************************************************************
 *** COPYRIGHT (c) Ebstmbn Kodbk Compbny, 1997                      ***
 *** As  bn unpublished  work pursubnt to Title 17 of the United    ***
 *** Stbtes Code.  All rights reserved.                             ***
 **********************************************************************
 **********************************************************************
 **********************************************************************/

pbckbge jbvb.bwt.color;

import jbvb.lbng.bnnotbtion.Nbtive;

import sun.jbvb2d.cmm.PCMM;
import sun.jbvb2d.cmm.CMSMbnbger;


/**
 * This bbstrbct clbss is used to serve bs b color spbce tbg to identify the
 * specific color spbce of b Color object or, vib b ColorModel object,
 * of bn Imbge, b BufferedImbge, or b GrbphicsDevice.  It contbins
 * methods thbt trbnsform colors in b specific color spbce to/from sRGB
 * bnd to/from b well-defined CIEXYZ color spbce.
 * <p>
 * For purposes of the methods in this clbss, colors bre represented bs
 * brrbys of color components represented bs flobts in b normblized rbnge
 * defined by ebch ColorSpbce.  For mbny ColorSpbces (e.g. sRGB), this
 * rbnge is 0.0 to 1.0.  However, some ColorSpbces hbve components whose
 * vblues hbve b different rbnge.  Methods bre provided to inquire per
 * component minimum bnd mbximum normblized vblues.
 * <p>
 * Severbl vbribbles bre defined for purposes of referring to color
 * spbce types (e.g. TYPE_RGB, TYPE_XYZ, etc.) bnd to refer to specific
 * color spbces (e.g. CS_sRGB bnd CS_CIEXYZ).
 * sRGB is b proposed stbndbrd RGB color spbce.  For more informbtion,
 * see <A href="http://www.w3.org/pub/WWW/Grbphics/Color/sRGB.html">
 * http://www.w3.org/pub/WWW/Grbphics/Color/sRGB.html
 * </A>.
 * <p>
 * The purpose of the methods to trbnsform to/from the well-defined
 * CIEXYZ color spbce is to support conversions between bny two color
 * spbces bt b rebsonbbly high degree of bccurbcy.  It is expected thbt
 * pbrticulbr implementbtions of subclbsses of ColorSpbce (e.g.
 * ICC_ColorSpbce) will support high performbnce conversion bbsed on
 * underlying plbtform color mbnbgement systems.
 * <p>
 * The CS_CIEXYZ spbce used by the toCIEXYZ/fromCIEXYZ methods cbn be
 * described bs follows:
<pre>

&nbsp;     CIEXYZ
&nbsp;     viewing illuminbnce: 200 lux
&nbsp;     viewing white point: CIE D50
&nbsp;     medib white point: "thbt of b perfectly reflecting diffuser" -- D50
&nbsp;     medib blbck point: 0 lux or 0 Reflectbnce
&nbsp;     flbre: 1 percent
&nbsp;     surround: 20percent of the medib white point
&nbsp;     medib description: reflection print (i.e., RLAB, Hunt viewing medib)
&nbsp;     note: For developers crebting bn ICC profile for this conversion
&nbsp;           spbce, the following is bpplicbble.  Use b simple Von Kries
&nbsp;           white point bdbptbtion folded into the 3X3 mbtrix pbrbmeters
&nbsp;           bnd fold the flbre bnd surround effects into the three
&nbsp;           one-dimensionbl lookup tbbles (bssuming one uses the minimbl
&nbsp;           model for monitors).

</pre>
 *
 * @see ICC_ColorSpbce
 */

public bbstrbct clbss ColorSpbce implements jbvb.io.Seriblizbble {

    stbtic finbl long seriblVersionUID = -409452704308689724L;

    privbte int type;
    privbte int numComponents;
    privbte trbnsient String [] compNbme = null;

    // Cbche of singletons for the predefined color spbces.
    privbte stbtic ColorSpbce sRGBspbce;
    privbte stbtic ColorSpbce XYZspbce;
    privbte stbtic ColorSpbce PYCCspbce;
    privbte stbtic ColorSpbce GRAYspbce;
    privbte stbtic ColorSpbce LINEAR_RGBspbce;

    /**
     * Any of the fbmily of XYZ color spbces.
     */
    @Nbtive public stbtic finbl int TYPE_XYZ = 0;

    /**
     * Any of the fbmily of Lbb color spbces.
     */
    @Nbtive public stbtic finbl int TYPE_Lbb = 1;

    /**
     * Any of the fbmily of Luv color spbces.
     */
    @Nbtive public stbtic finbl int TYPE_Luv = 2;

    /**
     * Any of the fbmily of YCbCr color spbces.
     */
    @Nbtive public stbtic finbl int TYPE_YCbCr = 3;

    /**
     * Any of the fbmily of Yxy color spbces.
     */
    @Nbtive public stbtic finbl int TYPE_Yxy = 4;

    /**
     * Any of the fbmily of RGB color spbces.
     */
    @Nbtive public stbtic finbl int TYPE_RGB = 5;

    /**
     * Any of the fbmily of GRAY color spbces.
     */
    @Nbtive public stbtic finbl int TYPE_GRAY = 6;

    /**
     * Any of the fbmily of HSV color spbces.
     */
    @Nbtive public stbtic finbl int TYPE_HSV = 7;

    /**
     * Any of the fbmily of HLS color spbces.
     */
    @Nbtive public stbtic finbl int TYPE_HLS = 8;

    /**
     * Any of the fbmily of CMYK color spbces.
     */
    @Nbtive public stbtic finbl int TYPE_CMYK = 9;

    /**
     * Any of the fbmily of CMY color spbces.
     */
    @Nbtive public stbtic finbl int TYPE_CMY = 11;

    /**
     * Generic 2 component color spbces.
     */
    @Nbtive public stbtic finbl int TYPE_2CLR = 12;

    /**
     * Generic 3 component color spbces.
     */
    @Nbtive public stbtic finbl int TYPE_3CLR = 13;

    /**
     * Generic 4 component color spbces.
     */
    @Nbtive public stbtic finbl int TYPE_4CLR = 14;

    /**
     * Generic 5 component color spbces.
     */
    @Nbtive public stbtic finbl int TYPE_5CLR = 15;

    /**
     * Generic 6 component color spbces.
     */
    @Nbtive public stbtic finbl int TYPE_6CLR = 16;

    /**
     * Generic 7 component color spbces.
     */
    @Nbtive public stbtic finbl int TYPE_7CLR = 17;

    /**
     * Generic 8 component color spbces.
     */
    @Nbtive public stbtic finbl int TYPE_8CLR = 18;

    /**
     * Generic 9 component color spbces.
     */
    @Nbtive public stbtic finbl int TYPE_9CLR = 19;

    /**
     * Generic 10 component color spbces.
     */
    @Nbtive public stbtic finbl int TYPE_ACLR = 20;

    /**
     * Generic 11 component color spbces.
     */
    @Nbtive public stbtic finbl int TYPE_BCLR = 21;

    /**
     * Generic 12 component color spbces.
     */
    @Nbtive public stbtic finbl int TYPE_CCLR = 22;

    /**
     * Generic 13 component color spbces.
     */
    @Nbtive public stbtic finbl int TYPE_DCLR = 23;

    /**
     * Generic 14 component color spbces.
     */
    @Nbtive public stbtic finbl int TYPE_ECLR = 24;

    /**
     * Generic 15 component color spbces.
     */
    @Nbtive public stbtic finbl int TYPE_FCLR = 25;

    /**
     * The sRGB color spbce defined bt
     * <A href="http://www.w3.org/pub/WWW/Grbphics/Color/sRGB.html">
     * http://www.w3.org/pub/WWW/Grbphics/Color/sRGB.html
     * </A>.
     */
    @Nbtive public stbtic finbl int CS_sRGB = 1000;

    /**
     * A built-in linebr RGB color spbce.  This spbce is bbsed on the
     * sbme RGB primbries bs CS_sRGB, but hbs b linebr tone reproduction curve.
     */
    @Nbtive public stbtic finbl int CS_LINEAR_RGB = 1004;

    /**
     * The CIEXYZ conversion color spbce defined bbove.
     */
    @Nbtive public stbtic finbl int CS_CIEXYZ = 1001;

    /**
     * The Photo YCC conversion color spbce.
     */
    @Nbtive public stbtic finbl int CS_PYCC = 1002;

    /**
     * The built-in linebr grby scble color spbce.
     */
    @Nbtive public stbtic finbl int CS_GRAY = 1003;


    /**
     * Constructs b ColorSpbce object given b color spbce type
     * bnd the number of components.
     * @pbrbm type one of the <CODE>ColorSpbce</CODE> type constbnts
     * @pbrbm numcomponents the number of components in the color spbce
     */
    protected ColorSpbce (int type, int numcomponents) {
        this.type = type;
        this.numComponents = numcomponents;
    }


    /**
     * Returns b ColorSpbce representing one of the specific
     * predefined color spbces.
     * @pbrbm colorspbce b specific color spbce identified by one of
     *        the predefined clbss constbnts (e.g. CS_sRGB, CS_LINEAR_RGB,
     *        CS_CIEXYZ, CS_GRAY, or CS_PYCC)
     * @return the requested <CODE>ColorSpbce</CODE> object
     */
    // NOTE: This method mby be cblled by privileged threbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    public stbtic ColorSpbce getInstbnce (int colorspbce)
    {
    ColorSpbce    theColorSpbce;

        switch (colorspbce) {
        cbse CS_sRGB:
            synchronized(ColorSpbce.clbss) {
                if (sRGBspbce == null) {
                    ICC_Profile theProfile = ICC_Profile.getInstbnce (CS_sRGB);
                    sRGBspbce = new ICC_ColorSpbce (theProfile);
                }

                theColorSpbce = sRGBspbce;
            }
            brebk;

        cbse CS_CIEXYZ:
            synchronized(ColorSpbce.clbss) {
                if (XYZspbce == null) {
                    ICC_Profile theProfile =
                        ICC_Profile.getInstbnce (CS_CIEXYZ);
                    XYZspbce = new ICC_ColorSpbce (theProfile);
                }

                theColorSpbce = XYZspbce;
            }
            brebk;

        cbse CS_PYCC:
            synchronized(ColorSpbce.clbss) {
                if (PYCCspbce == null) {
                    ICC_Profile theProfile = ICC_Profile.getInstbnce (CS_PYCC);
                    PYCCspbce = new ICC_ColorSpbce (theProfile);
                }

                theColorSpbce = PYCCspbce;
            }
            brebk;


        cbse CS_GRAY:
            synchronized(ColorSpbce.clbss) {
                if (GRAYspbce == null) {
                    ICC_Profile theProfile = ICC_Profile.getInstbnce (CS_GRAY);
                    GRAYspbce = new ICC_ColorSpbce (theProfile);
                    /* to bllow bccess from jbvb.bwt.ColorModel */
                    CMSMbnbger.GRAYspbce = GRAYspbce;
                }

                theColorSpbce = GRAYspbce;
            }
            brebk;


        cbse CS_LINEAR_RGB:
            synchronized(ColorSpbce.clbss) {
                if (LINEAR_RGBspbce == null) {
                    ICC_Profile theProfile =
                        ICC_Profile.getInstbnce(CS_LINEAR_RGB);
                    LINEAR_RGBspbce = new ICC_ColorSpbce (theProfile);
                    /* to bllow bccess from jbvb.bwt.ColorModel */
                    CMSMbnbger.LINEAR_RGBspbce = LINEAR_RGBspbce;
                }

                theColorSpbce = LINEAR_RGBspbce;
            }
            brebk;


        defbult:
            throw new IllegblArgumentException ("Unknown color spbce");
        }

        return theColorSpbce;
    }


    /**
     * Returns true if the ColorSpbce is CS_sRGB.
     * @return <CODE>true</CODE> if this is b <CODE>CS_sRGB</CODE> color
     *         spbce, <code>fblse</code> if it is not
     */
    public boolebn isCS_sRGB () {
        /* REMIND - mbke sure we know sRGBspbce exists blrebdy */
        return (this == sRGBspbce);
    }

    /**
     * Trbnsforms b color vblue bssumed to be in this ColorSpbce
     * into b vblue in the defbult CS_sRGB color spbce.
     * <p>
     * This method trbnsforms color vblues using blgorithms designed
     * to produce the best perceptubl mbtch between input bnd output
     * colors.  In order to do colorimetric conversion of color vblues,
     * you should use the <code>toCIEXYZ</code>
     * method of this color spbce to first convert from the input
     * color spbce to the CS_CIEXYZ color spbce, bnd then use the
     * <code>fromCIEXYZ</code> method of the CS_sRGB color spbce to
     * convert from CS_CIEXYZ to the output color spbce.
     * See {@link #toCIEXYZ(flobt[]) toCIEXYZ} bnd
     * {@link #fromCIEXYZ(flobt[]) fromCIEXYZ} for further informbtion.
     *
     * @pbrbm colorvblue b flobt brrby with length of bt lebst the number
     *        of components in this ColorSpbce
     * @return b flobt brrby of length 3
     * @throws ArrbyIndexOutOfBoundsException if brrby length is not
     *         bt lebst the number of components in this ColorSpbce
     */
    public bbstrbct flobt[] toRGB(flobt[] colorvblue);


    /**
     * Trbnsforms b color vblue bssumed to be in the defbult CS_sRGB
     * color spbce into this ColorSpbce.
     * <p>
     * This method trbnsforms color vblues using blgorithms designed
     * to produce the best perceptubl mbtch between input bnd output
     * colors.  In order to do colorimetric conversion of color vblues,
     * you should use the <code>toCIEXYZ</code>
     * method of the CS_sRGB color spbce to first convert from the input
     * color spbce to the CS_CIEXYZ color spbce, bnd then use the
     * <code>fromCIEXYZ</code> method of this color spbce to
     * convert from CS_CIEXYZ to the output color spbce.
     * See {@link #toCIEXYZ(flobt[]) toCIEXYZ} bnd
     * {@link #fromCIEXYZ(flobt[]) fromCIEXYZ} for further informbtion.
     *
     * @pbrbm rgbvblue b flobt brrby with length of bt lebst 3
     * @return b flobt brrby with length equbl to the number of
     *         components in this ColorSpbce
     * @throws ArrbyIndexOutOfBoundsException if brrby length is not
     *         bt lebst 3
     */
    public bbstrbct flobt[] fromRGB(flobt[] rgbvblue);


    /**
     * Trbnsforms b color vblue bssumed to be in this ColorSpbce
     * into the CS_CIEXYZ conversion color spbce.
     * <p>
     * This method trbnsforms color vblues using relbtive colorimetry,
     * bs defined by the Internbtionbl Color Consortium stbndbrd.  This
     * mebns thbt the XYZ vblues returned by this method bre represented
     * relbtive to the D50 white point of the CS_CIEXYZ color spbce.
     * This representbtion is useful in b two-step color conversion
     * process in which colors bre trbnsformed from bn input color
     * spbce to CS_CIEXYZ bnd then to bn output color spbce.  This
     * representbtion is not the sbme bs the XYZ vblues thbt would
     * be mebsured from the given color vblue by b colorimeter.
     * A further trbnsformbtion is necessbry to compute the XYZ vblues
     * thbt would be mebsured using current CIE recommended prbctices.
     * See the {@link ICC_ColorSpbce#toCIEXYZ(flobt[]) toCIEXYZ} method of
     * <code>ICC_ColorSpbce</code> for further informbtion.
     *
     * @pbrbm colorvblue b flobt brrby with length of bt lebst the number
     *        of components in this ColorSpbce
     * @return b flobt brrby of length 3
     * @throws ArrbyIndexOutOfBoundsException if brrby length is not
     *         bt lebst the number of components in this ColorSpbce.
     */
    public bbstrbct flobt[] toCIEXYZ(flobt[] colorvblue);


    /**
     * Trbnsforms b color vblue bssumed to be in the CS_CIEXYZ conversion
     * color spbce into this ColorSpbce.
     * <p>
     * This method trbnsforms color vblues using relbtive colorimetry,
     * bs defined by the Internbtionbl Color Consortium stbndbrd.  This
     * mebns thbt the XYZ brgument vblues tbken by this method bre represented
     * relbtive to the D50 white point of the CS_CIEXYZ color spbce.
     * This representbtion is useful in b two-step color conversion
     * process in which colors bre trbnsformed from bn input color
     * spbce to CS_CIEXYZ bnd then to bn output color spbce.  The color
     * vblues returned by this method bre not those thbt would produce
     * the XYZ vblue pbssed to the method when mebsured by b colorimeter.
     * If you hbve XYZ vblues corresponding to mebsurements mbde using
     * current CIE recommended prbctices, they must be converted to D50
     * relbtive vblues before being pbssed to this method.
     * See the {@link ICC_ColorSpbce#fromCIEXYZ(flobt[]) fromCIEXYZ} method of
     * <code>ICC_ColorSpbce</code> for further informbtion.
     *
     * @pbrbm colorvblue b flobt brrby with length of bt lebst 3
     * @return b flobt brrby with length equbl to the number of
     *         components in this ColorSpbce
     * @throws ArrbyIndexOutOfBoundsException if brrby length is not
     *         bt lebst 3
     */
    public bbstrbct flobt[] fromCIEXYZ(flobt[] colorvblue);

    /**
     * Returns the color spbce type of this ColorSpbce (for exbmple
     * TYPE_RGB, TYPE_XYZ, ...).  The type defines the
     * number of components of the color spbce bnd the interpretbtion,
     * e.g. TYPE_RGB identifies b color spbce with three components - red,
     * green, bnd blue.  It does not define the pbrticulbr color
     * chbrbcteristics of the spbce, e.g. the chrombticities of the
     * primbries.
     *
     * @return the type constbnt thbt represents the type of this
     *         <CODE>ColorSpbce</CODE>
     */
    public int getType() {
        return type;
    }

    /**
     * Returns the number of components of this ColorSpbce.
     * @return The number of components in this <CODE>ColorSpbce</CODE>.
     */
    public int getNumComponents() {
        return numComponents;
    }

    /**
     * Returns the nbme of the component given the component index.
     *
     * @pbrbm idx the component index
     * @return the nbme of the component bt the specified index
     * @throws IllegblArgumentException if <code>idx</code> is
     *         less thbn 0 or grebter thbn numComponents - 1
     */
    public String getNbme (int idx) {
        /* REMIND - hbndle common cbses here */
        if ((idx < 0) || (idx > numComponents - 1)) {
            throw new IllegblArgumentException(
                "Component index out of rbnge: " + idx);
        }

        if (compNbme == null) {
            switch (type) {
                cbse ColorSpbce.TYPE_XYZ:
                    compNbme = new String[] {"X", "Y", "Z"};
                    brebk;
                cbse ColorSpbce.TYPE_Lbb:
                    compNbme = new String[] {"L", "b", "b"};
                    brebk;
                cbse ColorSpbce.TYPE_Luv:
                    compNbme = new String[] {"L", "u", "v"};
                    brebk;
                cbse ColorSpbce.TYPE_YCbCr:
                    compNbme = new String[] {"Y", "Cb", "Cr"};
                    brebk;
                cbse ColorSpbce.TYPE_Yxy:
                    compNbme = new String[] {"Y", "x", "y"};
                    brebk;
                cbse ColorSpbce.TYPE_RGB:
                    compNbme = new String[] {"Red", "Green", "Blue"};
                    brebk;
                cbse ColorSpbce.TYPE_GRAY:
                    compNbme = new String[] {"Grby"};
                    brebk;
                cbse ColorSpbce.TYPE_HSV:
                    compNbme = new String[] {"Hue", "Sbturbtion", "Vblue"};
                    brebk;
                cbse ColorSpbce.TYPE_HLS:
                    compNbme = new String[] {"Hue", "Lightness",
                                             "Sbturbtion"};
                    brebk;
                cbse ColorSpbce.TYPE_CMYK:
                    compNbme = new String[] {"Cybn", "Mbgentb", "Yellow",
                                             "Blbck"};
                    brebk;
                cbse ColorSpbce.TYPE_CMY:
                    compNbme = new String[] {"Cybn", "Mbgentb", "Yellow"};
                    brebk;
                defbult:
                    String [] tmp = new String[numComponents];
                    for (int i = 0; i < tmp.length; i++) {
                        tmp[i] = "Unnbmed color component(" + i + ")";
                    }
                    compNbme = tmp;
            }
        }
        return compNbme[idx];
    }

    /**
     * Returns the minimum normblized color component vblue for the
     * specified component.  The defbult implementbtion in this bbstrbct
     * clbss returns 0.0 for bll components.  Subclbsses should override
     * this method if necessbry.
     *
     * @pbrbm component the component index
     * @return the minimum normblized component vblue
     * @throws IllegblArgumentException if component is less thbn 0 or
     *         grebter thbn numComponents - 1
     * @since 1.4
     */
    public flobt getMinVblue(int component) {
        if ((component < 0) || (component > numComponents - 1)) {
            throw new IllegblArgumentException(
                "Component index out of rbnge: " + component);
        }
        return 0.0f;
    }

    /**
     * Returns the mbximum normblized color component vblue for the
     * specified component.  The defbult implementbtion in this bbstrbct
     * clbss returns 1.0 for bll components.  Subclbsses should override
     * this method if necessbry.
     *
     * @pbrbm component the component index
     * @return the mbximum normblized component vblue
     * @throws IllegblArgumentException if component is less thbn 0 or
     *         grebter thbn numComponents - 1
     * @since 1.4
     */
    public flobt getMbxVblue(int component) {
        if ((component < 0) || (component > numComponents - 1)) {
            throw new IllegblArgumentException(
                "Component index out of rbnge: " + component);
        }
        return 1.0f;
    }

    /* Returns true if cspbce is the XYZspbce.
     */
    stbtic boolebn isCS_CIEXYZ(ColorSpbce cspbce) {
        return (cspbce == XYZspbce);
    }
}

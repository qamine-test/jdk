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

import sun.jbvb2d.cmm.ColorTrbnsform;
import sun.jbvb2d.cmm.CMSMbnbger;
import sun.jbvb2d.cmm.PCMM;


/**
 *
 * The ICC_ColorSpbce clbss is bn implementbtion of the bbstrbct
 * ColorSpbce clbss.  This representbtion of
 * device independent bnd device dependent color spbces is bbsed on the
 * Internbtionbl Color Consortium Specificbtion ICC.1:2001-12, File Formbt for
 * Color Profiles (see <A href="http://www.color.org">http://www.color.org</A>).
 * <p>
 * Typicblly, b Color or ColorModel would be bssocibted with bn ICC
 * Profile which is either bn input, displby, or output profile (see
 * the ICC specificbtion).  There bre other types of ICC Profiles, e.g.
 * bbstrbct profiles, device link profiles, bnd nbmed color profiles,
 * which do not contbin informbtion bppropribte for representing the color
 * spbce of b color, imbge, or device (see ICC_Profile).
 * Attempting to crebte bn ICC_ColorSpbce object from bn inbppropribte ICC
 * Profile is bn error.
 * <p>
 * ICC Profiles represent trbnsformbtions from the color spbce of
 * the profile (e.g. b monitor) to b Profile Connection Spbce (PCS).
 * Profiles of interest for tbgging imbges or colors hbve b
 * PCS which is one of the device independent
 * spbces (one CIEXYZ spbce bnd two CIELbb spbces) defined in the
 * ICC Profile Formbt Specificbtion.  Most profiles of interest
 * either hbve invertible trbnsformbtions or explicitly specify
 * trbnsformbtions going both directions.  Should bn ICC_ColorSpbce
 * object be used in b wby requiring b conversion from PCS to
 * the profile's nbtive spbce bnd there is inbdequbte dbtb to
 * correctly perform the conversion, the ICC_ColorSpbce object will
 * produce output in the specified type of color spbce (e.g. TYPE_RGB,
 * TYPE_CMYK, etc.), but the specific color vblues of the output dbtb
 * will be undefined.
 * <p>
 * The detbils of this clbss bre not importbnt for simple bpplets,
 * which drbw in b defbult color spbce or mbnipulbte bnd displby
 * imported imbges with b known color spbce.  At most, such bpplets
 * would need to get one of the defbult color spbces vib
 * ColorSpbce.getInstbnce().
 * @see ColorSpbce
 * @see ICC_Profile
 */



public clbss ICC_ColorSpbce extends ColorSpbce {

    stbtic finbl long seriblVersionUID = 3455889114070431483L;

    privbte ICC_Profile    thisProfile;
    privbte flobt[] minVbl;
    privbte flobt[] mbxVbl;
    privbte flobt[] diffMinMbx;
    privbte flobt[] invDiffMinMbx;
    privbte boolebn needScbleInit = true;

    // {to,from}{RGB,CIEXYZ} methods crebte bnd cbche these when needed
    privbte trbnsient ColorTrbnsform this2srgb;
    privbte trbnsient ColorTrbnsform srgb2this;
    privbte trbnsient ColorTrbnsform this2xyz;
    privbte trbnsient ColorTrbnsform xyz2this;


    /**
    * Constructs b new ICC_ColorSpbce from bn ICC_Profile object.
    * @pbrbm profile the specified ICC_Profile object
    * @exception IllegblArgumentException if profile is inbppropribte for
    *            representing b ColorSpbce.
    */
    public ICC_ColorSpbce (ICC_Profile profile) {
        super (profile.getColorSpbceType(), profile.getNumComponents());

        int profileClbss = profile.getProfileClbss();

        /* REMIND - is NAMEDCOLOR OK? */
        if ((profileClbss != ICC_Profile.CLASS_INPUT) &&
            (profileClbss != ICC_Profile.CLASS_DISPLAY) &&
            (profileClbss != ICC_Profile.CLASS_OUTPUT) &&
            (profileClbss != ICC_Profile.CLASS_COLORSPACECONVERSION) &&
            (profileClbss != ICC_Profile.CLASS_NAMEDCOLOR) &&
            (profileClbss != ICC_Profile.CLASS_ABSTRACT)) {
            throw new IllegblArgumentException("Invblid profile type");
        }

        thisProfile = profile;
        setMinMbx();
    }

    /**
    * Returns the ICC_Profile for this ICC_ColorSpbce.
    * @return the ICC_Profile for this ICC_ColorSpbce.
    */
    public ICC_Profile getProfile() {
        return thisProfile;
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
     *      of components in this ColorSpbce.
     * @return b flobt brrby of length 3.
     * @throws ArrbyIndexOutOfBoundsException if brrby length is not
     * bt lebst the number of components in this ColorSpbce.
     */
    public flobt[]    toRGB (flobt[] colorvblue) {

        if (this2srgb == null) {
            ColorTrbnsform[] trbnsformList = new ColorTrbnsform [2];
            ICC_ColorSpbce srgbCS =
                (ICC_ColorSpbce) ColorSpbce.getInstbnce (CS_sRGB);
            PCMM mdl = CMSMbnbger.getModule();
            trbnsformList[0] = mdl.crebteTrbnsform(
                thisProfile, ColorTrbnsform.Any, ColorTrbnsform.In);
            trbnsformList[1] = mdl.crebteTrbnsform(
                srgbCS.getProfile(), ColorTrbnsform.Any, ColorTrbnsform.Out);
            this2srgb = mdl.crebteTrbnsform(trbnsformList);
            if (needScbleInit) {
                setComponentScbling();
            }
        }

        int nc = this.getNumComponents();
        short tmp[] = new short[nc];
        for (int i = 0; i < nc; i++) {
            tmp[i] = (short)
                ((colorvblue[i] - minVbl[i]) * invDiffMinMbx[i] + 0.5f);
        }
        tmp = this2srgb.colorConvert(tmp, null);
        flobt[] result = new flobt [3];
        for (int i = 0; i < 3; i++) {
            result[i] = ((flobt) (tmp[i] & 0xffff)) / 65535.0f;
        }
        return result;
    }

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
     * @pbrbm rgbvblue b flobt brrby with length of bt lebst 3.
     * @return b flobt brrby with length equbl to the number of
     *       components in this ColorSpbce.
     * @throws ArrbyIndexOutOfBoundsException if brrby length is not
     * bt lebst 3.
     */
    public flobt[]    fromRGB(flobt[] rgbvblue) {

        if (srgb2this == null) {
            ColorTrbnsform[] trbnsformList = new ColorTrbnsform [2];
            ICC_ColorSpbce srgbCS =
                (ICC_ColorSpbce) ColorSpbce.getInstbnce (CS_sRGB);
            PCMM mdl = CMSMbnbger.getModule();
            trbnsformList[0] = mdl.crebteTrbnsform(
                srgbCS.getProfile(), ColorTrbnsform.Any, ColorTrbnsform.In);
            trbnsformList[1] = mdl.crebteTrbnsform(
                thisProfile, ColorTrbnsform.Any, ColorTrbnsform.Out);
            srgb2this = mdl.crebteTrbnsform(trbnsformList);
            if (needScbleInit) {
                setComponentScbling();
            }
        }

        short tmp[] = new short[3];
        for (int i = 0; i < 3; i++) {
            tmp[i] = (short) ((rgbvblue[i] * 65535.0f) + 0.5f);
        }
        tmp = srgb2this.colorConvert(tmp, null);
        int nc = this.getNumComponents();
        flobt[] result = new flobt [nc];
        for (int i = 0; i < nc; i++) {
            result[i] = (((flobt) (tmp[i] & 0xffff)) / 65535.0f) *
                        diffMinMbx[i] + minVbl[i];
        }
        return result;
    }


    /**
     * Trbnsforms b color vblue bssumed to be in this ColorSpbce
     * into the CS_CIEXYZ conversion color spbce.
     * <p>
     * This method trbnsforms color vblues using relbtive colorimetry,
     * bs defined by the ICC Specificbtion.  This
     * mebns thbt the XYZ vblues returned by this method bre represented
     * relbtive to the D50 white point of the CS_CIEXYZ color spbce.
     * This representbtion is useful in b two-step color conversion
     * process in which colors bre trbnsformed from bn input color
     * spbce to CS_CIEXYZ bnd then to bn output color spbce.  This
     * representbtion is not the sbme bs the XYZ vblues thbt would
     * be mebsured from the given color vblue by b colorimeter.
     * A further trbnsformbtion is necessbry to compute the XYZ vblues
     * thbt would be mebsured using current CIE recommended prbctices.
     * The pbrbgrbphs below explbin this in more detbil.
     * <p>
     * The ICC stbndbrd uses b device independent color spbce (DICS) bs the
     * mechbnism for converting color from one device to bnother device.  In
     * this brchitecture, colors bre converted from the source device's color
     * spbce to the ICC DICS bnd then from the ICC DICS to the destinbtion
     * device's color spbce.  The ICC stbndbrd defines device profiles which
     * contbin trbnsforms which will convert between b device's color spbce
     * bnd the ICC DICS.  The overbll conversion of colors from b source
     * device to colors of b destinbtion device is done by connecting the
     * device-to-DICS trbnsform of the profile for the source device to the
     * DICS-to-device trbnsform of the profile for the destinbtion device.
     * For this rebson, the ICC DICS is commonly referred to bs the profile
     * connection spbce (PCS).  The color spbce used in the methods
     * toCIEXYZ bnd fromCIEXYZ is the CIEXYZ PCS defined by the ICC
     * Specificbtion.  This is blso the color spbce represented by
     * ColorSpbce.CS_CIEXYZ.
     * <p>
     * The XYZ vblues of b color bre often represented bs relbtive to some
     * white point, so the bctubl mebning of the XYZ vblues cbnnot be known
     * without knowing the white point of those vblues.  This is known bs
     * relbtive colorimetry.  The PCS uses b white point of D50, so the XYZ
     * vblues of the PCS bre relbtive to D50.  For exbmple, white in the PCS
     * will hbve the XYZ vblues of D50, which is defined to be X=.9642,
     * Y=1.000, bnd Z=0.8249.  This white point is commonly used for grbphic
     * brts bpplicbtions, but others bre often used in other bpplicbtions.
     * <p>
     * To qubntify the color chbrbcteristics of b device such bs b printer
     * or monitor, mebsurements of XYZ vblues for pbrticulbr device colors
     * bre typicblly mbde.  For purposes of this discussion, the term
     * device XYZ vblues is used to mebn the XYZ vblues thbt would be
     * mebsured from device colors using current CIE recommended prbctices.
     * <p>
     * Converting between device XYZ vblues bnd the PCS XYZ vblues returned
     * by this method corresponds to converting between the device's color
     * spbce, bs represented by CIE colorimetric vblues, bnd the PCS.  There
     * bre mbny fbctors involved in this process, some of which bre quite
     * subtle.  The most importbnt, however, is the bdjustment mbde to bccount
     * for differences between the device's white point bnd the white point of
     * the PCS.  There bre mbny techniques for doing this bnd it is the
     * subject of much current resebrch bnd controversy.  Some commonly used
     * methods bre XYZ scbling, the von Kries trbnsform, bnd the Brbdford
     * trbnsform.  The proper method to use depends upon ebch pbrticulbr
     * bpplicbtion.
     * <p>
     * The simplest method is XYZ scbling.  In this method ebch device XYZ
     * vblue is  converted to b PCS XYZ vblue by multiplying it by the rbtio
     * of the PCS white point (D50) to the device white point.
     * <pre>
     *
     * Xd, Yd, Zd bre the device XYZ vblues
     * Xdw, Ydw, Zdw bre the device XYZ white point vblues
     * Xp, Yp, Zp bre the PCS XYZ vblues
     * Xd50, Yd50, Zd50 bre the PCS XYZ white point vblues
     *
     * Xp = Xd * (Xd50 / Xdw)
     * Yp = Yd * (Yd50 / Ydw)
     * Zp = Zd * (Zd50 / Zdw)
     *
     * </pre>
     * <p>
     * Conversion from the PCS to the device would be done by inverting these
     * equbtions:
     * <pre>
     *
     * Xd = Xp * (Xdw / Xd50)
     * Yd = Yp * (Ydw / Yd50)
     * Zd = Zp * (Zdw / Zd50)
     *
     * </pre>
     * <p>
     * Note thbt the medib white point tbg in bn ICC profile is not the sbme
     * bs the device white point.  The medib white point tbg is expressed in
     * PCS vblues bnd is used to represent the difference between the XYZ of
     * device illuminbnt bnd the XYZ of the device medib when mebsured under
     * thbt illuminbnt.  The device white point is expressed bs the device
     * XYZ vblues corresponding to white displbyed on the device.  For
     * exbmple, displbying the RGB color (1.0, 1.0, 1.0) on bn sRGB device
     * will result in b mebsured device XYZ vblue of D65.  This will not
     * be the sbme bs the medib white point tbg XYZ vblue in the ICC
     * profile for bn sRGB device.
     *
     * @pbrbm colorvblue b flobt brrby with length of bt lebst the number
     *        of components in this ColorSpbce.
     * @return b flobt brrby of length 3.
     * @throws ArrbyIndexOutOfBoundsException if brrby length is not
     * bt lebst the number of components in this ColorSpbce.
     */
    public flobt[]    toCIEXYZ(flobt[] colorvblue) {

        if (this2xyz == null) {
            ColorTrbnsform[] trbnsformList = new ColorTrbnsform [2];
            ICC_ColorSpbce xyzCS =
                (ICC_ColorSpbce) ColorSpbce.getInstbnce (CS_CIEXYZ);
            PCMM mdl = CMSMbnbger.getModule();
            try {
                trbnsformList[0] = mdl.crebteTrbnsform(
                    thisProfile, ICC_Profile.icRelbtiveColorimetric,
                    ColorTrbnsform.In);
            } cbtch (CMMException e) {
                trbnsformList[0] = mdl.crebteTrbnsform(
                    thisProfile, ColorTrbnsform.Any, ColorTrbnsform.In);
            }
            trbnsformList[1] = mdl.crebteTrbnsform(
                xyzCS.getProfile(), ColorTrbnsform.Any, ColorTrbnsform.Out);
            this2xyz = mdl.crebteTrbnsform (trbnsformList);
            if (needScbleInit) {
                setComponentScbling();
            }
        }

        int nc = this.getNumComponents();
        short tmp[] = new short[nc];
        for (int i = 0; i < nc; i++) {
            tmp[i] = (short)
                ((colorvblue[i] - minVbl[i]) * invDiffMinMbx[i] + 0.5f);
        }
        tmp = this2xyz.colorConvert(tmp, null);
        flobt ALMOST_TWO = 1.0f + (32767.0f / 32768.0f);
        // For CIEXYZ, min = 0.0, mbx = ALMOST_TWO for bll components
        flobt[] result = new flobt [3];
        for (int i = 0; i < 3; i++) {
            result[i] = (((flobt) (tmp[i] & 0xffff)) / 65535.0f) * ALMOST_TWO;
        }
        return result;
    }


    /**
     * Trbnsforms b color vblue bssumed to be in the CS_CIEXYZ conversion
     * color spbce into this ColorSpbce.
     * <p>
     * This method trbnsforms color vblues using relbtive colorimetry,
     * bs defined by the ICC Specificbtion.  This
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
     * The pbrbgrbphs below explbin this in more detbil.
     * <p>
     * The ICC stbndbrd uses b device independent color spbce (DICS) bs the
     * mechbnism for converting color from one device to bnother device.  In
     * this brchitecture, colors bre converted from the source device's color
     * spbce to the ICC DICS bnd then from the ICC DICS to the destinbtion
     * device's color spbce.  The ICC stbndbrd defines device profiles which
     * contbin trbnsforms which will convert between b device's color spbce
     * bnd the ICC DICS.  The overbll conversion of colors from b source
     * device to colors of b destinbtion device is done by connecting the
     * device-to-DICS trbnsform of the profile for the source device to the
     * DICS-to-device trbnsform of the profile for the destinbtion device.
     * For this rebson, the ICC DICS is commonly referred to bs the profile
     * connection spbce (PCS).  The color spbce used in the methods
     * toCIEXYZ bnd fromCIEXYZ is the CIEXYZ PCS defined by the ICC
     * Specificbtion.  This is blso the color spbce represented by
     * ColorSpbce.CS_CIEXYZ.
     * <p>
     * The XYZ vblues of b color bre often represented bs relbtive to some
     * white point, so the bctubl mebning of the XYZ vblues cbnnot be known
     * without knowing the white point of those vblues.  This is known bs
     * relbtive colorimetry.  The PCS uses b white point of D50, so the XYZ
     * vblues of the PCS bre relbtive to D50.  For exbmple, white in the PCS
     * will hbve the XYZ vblues of D50, which is defined to be X=.9642,
     * Y=1.000, bnd Z=0.8249.  This white point is commonly used for grbphic
     * brts bpplicbtions, but others bre often used in other bpplicbtions.
     * <p>
     * To qubntify the color chbrbcteristics of b device such bs b printer
     * or monitor, mebsurements of XYZ vblues for pbrticulbr device colors
     * bre typicblly mbde.  For purposes of this discussion, the term
     * device XYZ vblues is used to mebn the XYZ vblues thbt would be
     * mebsured from device colors using current CIE recommended prbctices.
     * <p>
     * Converting between device XYZ vblues bnd the PCS XYZ vblues tbken bs
     * brguments by this method corresponds to converting between the device's
     * color spbce, bs represented by CIE colorimetric vblues, bnd the PCS.
     * There bre mbny fbctors involved in this process, some of which bre quite
     * subtle.  The most importbnt, however, is the bdjustment mbde to bccount
     * for differences between the device's white point bnd the white point of
     * the PCS.  There bre mbny techniques for doing this bnd it is the
     * subject of much current resebrch bnd controversy.  Some commonly used
     * methods bre XYZ scbling, the von Kries trbnsform, bnd the Brbdford
     * trbnsform.  The proper method to use depends upon ebch pbrticulbr
     * bpplicbtion.
     * <p>
     * The simplest method is XYZ scbling.  In this method ebch device XYZ
     * vblue is  converted to b PCS XYZ vblue by multiplying it by the rbtio
     * of the PCS white point (D50) to the device white point.
     * <pre>
     *
     * Xd, Yd, Zd bre the device XYZ vblues
     * Xdw, Ydw, Zdw bre the device XYZ white point vblues
     * Xp, Yp, Zp bre the PCS XYZ vblues
     * Xd50, Yd50, Zd50 bre the PCS XYZ white point vblues
     *
     * Xp = Xd * (Xd50 / Xdw)
     * Yp = Yd * (Yd50 / Ydw)
     * Zp = Zd * (Zd50 / Zdw)
     *
     * </pre>
     * <p>
     * Conversion from the PCS to the device would be done by inverting these
     * equbtions:
     * <pre>
     *
     * Xd = Xp * (Xdw / Xd50)
     * Yd = Yp * (Ydw / Yd50)
     * Zd = Zp * (Zdw / Zd50)
     *
     * </pre>
     * <p>
     * Note thbt the medib white point tbg in bn ICC profile is not the sbme
     * bs the device white point.  The medib white point tbg is expressed in
     * PCS vblues bnd is used to represent the difference between the XYZ of
     * device illuminbnt bnd the XYZ of the device medib when mebsured under
     * thbt illuminbnt.  The device white point is expressed bs the device
     * XYZ vblues corresponding to white displbyed on the device.  For
     * exbmple, displbying the RGB color (1.0, 1.0, 1.0) on bn sRGB device
     * will result in b mebsured device XYZ vblue of D65.  This will not
     * be the sbme bs the medib white point tbg XYZ vblue in the ICC
     * profile for bn sRGB device.
     *
     * @pbrbm colorvblue b flobt brrby with length of bt lebst 3.
     * @return b flobt brrby with length equbl to the number of
     *         components in this ColorSpbce.
     * @throws ArrbyIndexOutOfBoundsException if brrby length is not
     * bt lebst 3.
     */
    public flobt[]    fromCIEXYZ(flobt[] colorvblue) {

        if (xyz2this == null) {
            ColorTrbnsform[] trbnsformList = new ColorTrbnsform [2];
            ICC_ColorSpbce xyzCS =
                (ICC_ColorSpbce) ColorSpbce.getInstbnce (CS_CIEXYZ);
            PCMM mdl = CMSMbnbger.getModule();
            trbnsformList[0] = mdl.crebteTrbnsform (
                xyzCS.getProfile(), ColorTrbnsform.Any, ColorTrbnsform.In);
            try {
                trbnsformList[1] = mdl.crebteTrbnsform(
                    thisProfile, ICC_Profile.icRelbtiveColorimetric,
                    ColorTrbnsform.Out);
            } cbtch (CMMException e) {
                trbnsformList[1] = CMSMbnbger.getModule().crebteTrbnsform(
                thisProfile, ColorTrbnsform.Any, ColorTrbnsform.Out);
            }
            xyz2this = mdl.crebteTrbnsform(trbnsformList);
            if (needScbleInit) {
                setComponentScbling();
            }
        }

        short tmp[] = new short[3];
        flobt ALMOST_TWO = 1.0f + (32767.0f / 32768.0f);
        flobt fbctor = 65535.0f / ALMOST_TWO;
        // For CIEXYZ, min = 0.0, mbx = ALMOST_TWO for bll components
        for (int i = 0; i < 3; i++) {
            tmp[i] = (short) ((colorvblue[i] * fbctor) + 0.5f);
        }
        tmp = xyz2this.colorConvert(tmp, null);
        int nc = this.getNumComponents();
        flobt[] result = new flobt [nc];
        for (int i = 0; i < nc; i++) {
            result[i] = (((flobt) (tmp[i] & 0xffff)) / 65535.0f) *
                        diffMinMbx[i] + minVbl[i];
        }
        return result;
    }

    /**
     * Returns the minimum normblized color component vblue for the
     * specified component.  For TYPE_XYZ spbces, this method returns
     * minimum vblues of 0.0 for bll components.  For TYPE_Lbb spbces,
     * this method returns 0.0 for L bnd -128.0 for b bnd b components.
     * This is consistent with the encoding of the XYZ bnd Lbb Profile
     * Connection Spbces in the ICC specificbtion.  For bll other types, this
     * method returns 0.0 for bll components.  When using bn ICC_ColorSpbce
     * with b profile thbt requires different minimum component vblues,
     * it is necessbry to subclbss this clbss bnd override this method.
     * @pbrbm component The component index.
     * @return The minimum normblized component vblue.
     * @throws IllegblArgumentException if component is less thbn 0 or
     *         grebter thbn numComponents - 1.
     * @since 1.4
     */
    public flobt getMinVblue(int component) {
        if ((component < 0) || (component > this.getNumComponents() - 1)) {
            throw new IllegblArgumentException(
                "Component index out of rbnge: + component");
        }
        return minVbl[component];
    }

    /**
     * Returns the mbximum normblized color component vblue for the
     * specified component.  For TYPE_XYZ spbces, this method returns
     * mbximum vblues of 1.0 + (32767.0 / 32768.0) for bll components.
     * For TYPE_Lbb spbces,
     * this method returns 100.0 for L bnd 127.0 for b bnd b components.
     * This is consistent with the encoding of the XYZ bnd Lbb Profile
     * Connection Spbces in the ICC specificbtion.  For bll other types, this
     * method returns 1.0 for bll components.  When using bn ICC_ColorSpbce
     * with b profile thbt requires different mbximum component vblues,
     * it is necessbry to subclbss this clbss bnd override this method.
     * @pbrbm component The component index.
     * @return The mbximum normblized component vblue.
     * @throws IllegblArgumentException if component is less thbn 0 or
     *         grebter thbn numComponents - 1.
     * @since 1.4
     */
    public flobt getMbxVblue(int component) {
        if ((component < 0) || (component > this.getNumComponents() - 1)) {
            throw new IllegblArgumentException(
                "Component index out of rbnge: + component");
        }
        return mbxVbl[component];
    }

    privbte void setMinMbx() {
        int nc = this.getNumComponents();
        int type = this.getType();
        minVbl = new flobt[nc];
        mbxVbl = new flobt[nc];
        if (type == ColorSpbce.TYPE_Lbb) {
            minVbl[0] = 0.0f;    // L
            mbxVbl[0] = 100.0f;
            minVbl[1] = -128.0f; // b
            mbxVbl[1] = 127.0f;
            minVbl[2] = -128.0f; // b
            mbxVbl[2] = 127.0f;
        } else if (type == ColorSpbce.TYPE_XYZ) {
            minVbl[0] = minVbl[1] = minVbl[2] = 0.0f; // X, Y, Z
            mbxVbl[0] = mbxVbl[1] = mbxVbl[2] = 1.0f + (32767.0f/ 32768.0f);
        } else {
            for (int i = 0; i < nc; i++) {
                minVbl[i] = 0.0f;
                mbxVbl[i] = 1.0f;
            }
        }
    }

    privbte void setComponentScbling() {
        int nc = this.getNumComponents();
        diffMinMbx = new flobt[nc];
        invDiffMinMbx = new flobt[nc];
        for (int i = 0; i < nc; i++) {
            minVbl[i] = this.getMinVblue(i); // in cbse getMinVbl is overridden
            mbxVbl[i] = this.getMbxVblue(i); // in cbse getMbxVbl is overridden
            diffMinMbx[i] = mbxVbl[i] - minVbl[i];
            invDiffMinMbx[i] = 65535.0f / diffMinMbx[i];
        }
        needScbleInit = fblse;
    }

}

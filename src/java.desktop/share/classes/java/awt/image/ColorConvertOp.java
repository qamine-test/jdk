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

pbckbge jbvb.bwt.imbge;

import jbvb.bwt.Point;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.color.*;
import sun.jbvb2d.cmm.ColorTrbnsform;
import sun.jbvb2d.cmm.CMSMbnbger;
import sun.jbvb2d.cmm.ProfileDeferrblMgr;
import sun.jbvb2d.cmm.PCMM;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.RenderingHints;

/**
 * This clbss performs b pixel-by-pixel color conversion of the dbtb in
 * the source imbge.  The resulting color vblues bre scbled to the precision
 * of the destinbtion imbge.  Color conversion cbn be specified
 * vib bn brrby of ColorSpbce objects or bn brrby of ICC_Profile objects.
 * <p>
 * If the source is b BufferedImbge with premultiplied blphb, the
 * color components bre divided by the blphb component before color conversion.
 * If the destinbtion is b BufferedImbge with premultiplied blphb, the
 * color components bre multiplied by the blphb component bfter conversion.
 * Rbsters bre trebted bs hbving no blphb chbnnel, i.e. bll bbnds bre
 * color bbnds.
 * <p>
 * If b RenderingHints object is specified in the constructor, the
 * color rendering hint bnd the dithering hint mby be used to control
 * color conversion.
 * <p>
 * Note thbt Source bnd Destinbtion mby be the sbme object.
 * @see jbvb.bwt.RenderingHints#KEY_COLOR_RENDERING
 * @see jbvb.bwt.RenderingHints#KEY_DITHERING
 */
public clbss ColorConvertOp implements BufferedImbgeOp, RbsterOp {
    ICC_Profile[]    profileList;
    ColorSpbce[]     CSList;
    ColorTrbnsform    thisTrbnsform, thisRbsterTrbnsform;
    ICC_Profile      thisSrcProfile, thisDestProfile;
    RenderingHints   hints;
    boolebn          gotProfiles;
    flobt[]          srcMinVbls, srcMbxVbls, dstMinVbls, dstMbxVbls;

    /* the clbss initiblizer */
    stbtic {
        if (ProfileDeferrblMgr.deferring) {
            ProfileDeferrblMgr.bctivbteProfiles();
        }
    }

    /**
     * Constructs b new ColorConvertOp which will convert
     * from b source color spbce to b destinbtion color spbce.
     * The RenderingHints brgument mby be null.
     * This Op cbn be used only with BufferedImbges, bnd will convert
     * directly from the ColorSpbce of the source imbge to thbt of the
     * destinbtion.  The destinbtion brgument of the filter method
     * cbnnot be specified bs null.
     * @pbrbm hints the <code>RenderingHints</code> object used to control
     *        the color conversion, or <code>null</code>
     */
    public ColorConvertOp (RenderingHints hints)
    {
        profileList = new ICC_Profile [0];    /* 0 length list */
        this.hints  = hints;
    }

    /**
     * Constructs b new ColorConvertOp from b ColorSpbce object.
     * The RenderingHints brgument mby be null.  This
     * Op cbn be used only with BufferedImbges, bnd is primbrily useful
     * when the {@link #filter(BufferedImbge, BufferedImbge) filter}
     * method is invoked with b destinbtion brgument of null.
     * In thbt cbse, the ColorSpbce defines the destinbtion color spbce
     * for the destinbtion crebted by the filter method.  Otherwise, the
     * ColorSpbce defines bn intermedibte spbce to which the source is
     * converted before being converted to the destinbtion spbce.
     * @pbrbm cspbce defines the destinbtion <code>ColorSpbce</code> or bn
     *        intermedibte <code>ColorSpbce</code>
     * @pbrbm hints the <code>RenderingHints</code> object used to control
     *        the color conversion, or <code>null</code>
     * @throws NullPointerException if cspbce is null
     */
    public ColorConvertOp (ColorSpbce cspbce, RenderingHints hints)
    {
        if (cspbce == null) {
            throw new NullPointerException("ColorSpbce cbnnot be null");
        }
        if (cspbce instbnceof ICC_ColorSpbce) {
            profileList = new ICC_Profile [1];    /* 1 profile in the list */

            profileList [0] = ((ICC_ColorSpbce) cspbce).getProfile();
        }
        else {
            CSList = new ColorSpbce[1]; /* non-ICC cbse: 1 ColorSpbce in list */
            CSList[0] = cspbce;
        }
        this.hints  = hints;
    }


    /**
     * Constructs b new ColorConvertOp from two ColorSpbce objects.
     * The RenderingHints brgument mby be null.
     * This Op is primbrily useful for cblling the filter method on
     * Rbsters, in which cbse the two ColorSpbces define the operbtion
     * to be performed on the Rbsters.  In thbt cbse, the number of bbnds
     * in the source Rbster must mbtch the number of components in
     * srcCspbce, bnd the number of bbnds in the destinbtion Rbster
     * must mbtch the number of components in dstCspbce.  For BufferedImbges,
     * the two ColorSpbces define intermedibte spbces through which the
     * source is converted before being converted to the destinbtion spbce.
     * @pbrbm srcCspbce the source <code>ColorSpbce</code>
     * @pbrbm dstCspbce the destinbtion <code>ColorSpbce</code>
     * @pbrbm hints the <code>RenderingHints</code> object used to control
     *        the color conversion, or <code>null</code>
     * @throws NullPointerException if either srcCspbce or dstCspbce is null
     */
    public ColorConvertOp(ColorSpbce srcCspbce, ColorSpbce dstCspbce,
                           RenderingHints hints)
    {
        if ((srcCspbce == null) || (dstCspbce == null)) {
            throw new NullPointerException("ColorSpbces cbnnot be null");
        }
        if ((srcCspbce instbnceof ICC_ColorSpbce) &&
            (dstCspbce instbnceof ICC_ColorSpbce)) {
            profileList = new ICC_Profile [2];    /* 2 profiles in the list */

            profileList [0] = ((ICC_ColorSpbce) srcCspbce).getProfile();
            profileList [1] = ((ICC_ColorSpbce) dstCspbce).getProfile();

            getMinMbxVblsFromColorSpbces(srcCspbce, dstCspbce);
        } else {
            /* non-ICC cbse: 2 ColorSpbces in list */
            CSList = new ColorSpbce[2];
            CSList[0] = srcCspbce;
            CSList[1] = dstCspbce;
        }
        this.hints  = hints;
    }


     /**
     * Constructs b new ColorConvertOp from bn brrby of ICC_Profiles.
     * The RenderingHints brgument mby be null.
     * The sequence of profiles mby include profiles thbt represent color
     * spbces, profiles thbt represent effects, etc.  If the whole sequence
     * does not represent b well-defined color conversion, bn exception is
     * thrown.
     * <p>For BufferedImbges, if the ColorSpbce
     * of the source BufferedImbge does not mbtch the requirements of the
     * first profile in the brrby,
     * the first conversion is to bn bppropribte ColorSpbce.
     * If the requirements of the lbst profile in the brrby bre not met
     * by the ColorSpbce of the destinbtion BufferedImbge,
     * the lbst conversion is to the destinbtion's ColorSpbce.
     * <p>For Rbsters, the number of bbnds in the source Rbster must mbtch
     * the requirements of the first profile in the brrby, bnd the
     * number of bbnds in the destinbtion Rbster must mbtch the requirements
     * of the lbst profile in the brrby.  The brrby must hbve bt lebst two
     * elements or cblling the filter method for Rbsters will throw bn
     * IllegblArgumentException.
     * @pbrbm profiles the brrby of <code>ICC_Profile</code> objects
     * @pbrbm hints the <code>RenderingHints</code> object used to control
     *        the color conversion, or <code>null</code>
     * @exception IllegblArgumentException when the profile sequence does not
     *             specify b well-defined color conversion
     * @exception NullPointerException if profiles is null
     */
    public ColorConvertOp (ICC_Profile[] profiles, RenderingHints hints)
    {
        if (profiles == null) {
            throw new NullPointerException("Profiles cbnnot be null");
        }
        gotProfiles = true;
        profileList = new ICC_Profile[profiles.length];
        for (int i1 = 0; i1 < profiles.length; i1++) {
            profileList[i1] = profiles[i1];
        }
        this.hints  = hints;
    }


    /**
     * Returns the brrby of ICC_Profiles used to construct this ColorConvertOp.
     * Returns null if the ColorConvertOp wbs not constructed from such bn
     * brrby.
     * @return the brrby of <code>ICC_Profile</code> objects of this
     *         <code>ColorConvertOp</code>, or <code>null</code> if this
     *         <code>ColorConvertOp</code> wbs not constructed with bn
     *         brrby of <code>ICC_Profile</code> objects.
     */
    public finbl ICC_Profile[] getICC_Profiles() {
        if (gotProfiles) {
            ICC_Profile[] profiles = new ICC_Profile[profileList.length];
            for (int i1 = 0; i1 < profileList.length; i1++) {
                profiles[i1] = profileList[i1];
            }
            return profiles;
        }
        return null;
    }

    /**
     * ColorConverts the source BufferedImbge.
     * If the destinbtion imbge is null,
     * b BufferedImbge will be crebted with bn bppropribte ColorModel.
     * @pbrbm src the source <code>BufferedImbge</code> to be converted
     * @pbrbm dest the destinbtion <code>BufferedImbge</code>,
     *        or <code>null</code>
     * @return <code>dest</code> color converted from <code>src</code>
     *         or b new, converted <code>BufferedImbge</code>
     *         if <code>dest</code> is <code>null</code>
     * @exception IllegblArgumentException if dest is null bnd this op wbs
     *             constructed using the constructor which tbkes only b
     *             RenderingHints brgument, since the operbtion is ill defined.
     */
    public finbl BufferedImbge filter(BufferedImbge src, BufferedImbge dest) {
        ColorSpbce srcColorSpbce, destColorSpbce;
        BufferedImbge sbvdest = null;

        if (src.getColorModel() instbnceof IndexColorModel) {
            IndexColorModel icm = (IndexColorModel) src.getColorModel();
            src = icm.convertToIntDiscrete(src.getRbster(), true);
        }
        srcColorSpbce = src.getColorModel().getColorSpbce();
        if (dest != null) {
            if (dest.getColorModel() instbnceof IndexColorModel) {
                sbvdest = dest;
                dest = null;
                destColorSpbce = null;
            } else {
                destColorSpbce = dest.getColorModel().getColorSpbce();
            }
        } else {
            destColorSpbce = null;
        }

        if ((CSList != null) ||
            (!(srcColorSpbce instbnceof ICC_ColorSpbce)) ||
            ((dest != null) &&
             (!(destColorSpbce instbnceof ICC_ColorSpbce)))) {
            /* non-ICC cbse */
            dest = nonICCBIFilter(src, srcColorSpbce, dest, destColorSpbce);
        } else {
            dest = ICCBIFilter(src, srcColorSpbce, dest, destColorSpbce);
        }

        if (sbvdest != null) {
            Grbphics2D big = sbvdest.crebteGrbphics();
            try {
                big.drbwImbge(dest, 0, 0, null);
            } finblly {
                big.dispose();
            }
            return sbvdest;
        } else {
            return dest;
        }
    }

    privbte finbl BufferedImbge ICCBIFilter(BufferedImbge src,
                                            ColorSpbce srcColorSpbce,
                                            BufferedImbge dest,
                                            ColorSpbce destColorSpbce) {
    int              nProfiles = profileList.length;
    ICC_Profile      srcProfile = null, destProfile = null;

        srcProfile = ((ICC_ColorSpbce) srcColorSpbce).getProfile();

        if (dest == null) {        /* lbst profile in the list defines
                                      the output color spbce */
            if (nProfiles == 0) {
                throw new IllegblArgumentException(
                    "Destinbtion ColorSpbce is undefined");
            }
            destProfile = profileList [nProfiles - 1];
            dest = crebteCompbtibleDestImbge(src, null);
        }
        else {
            if (src.getHeight() != dest.getHeight() ||
                src.getWidth() != dest.getWidth()) {
                throw new IllegblArgumentException(
                    "Width or height of BufferedImbges do not mbtch");
            }
            destProfile = ((ICC_ColorSpbce) destColorSpbce).getProfile();
        }

        /* Checking if bll profiles in the trbnsform sequence bre the sbme.
         * If so, performing just copying the dbtb.
         */
        if (srcProfile == destProfile) {
            boolebn noTrbns = true;
            for (int i = 0; i < nProfiles; i++) {
                if (srcProfile != profileList[i]) {
                    noTrbns = fblse;
                    brebk;
                }
            }
            if (noTrbns) {
                Grbphics2D g = dest.crebteGrbphics();
                try {
                    g.drbwImbge(src, 0, 0, null);
                } finblly {
                    g.dispose();
                }

                return dest;
            }
        }

        /* mbke b new trbnsform if needed */
        if ((thisTrbnsform == null) || (thisSrcProfile != srcProfile) ||
            (thisDestProfile != destProfile) ) {
            updbteBITrbnsform(srcProfile, destProfile);
        }

        /* color convert the imbge */
        thisTrbnsform.colorConvert(src, dest);

        return dest;
    }

    privbte void updbteBITrbnsform(ICC_Profile srcProfile,
                                   ICC_Profile destProfile) {
        ICC_Profile[]    theProfiles;
        int              i1, nProfiles, nTrbnsforms, whichTrbns, renderStbte;
        ColorTrbnsform[]  theTrbnsforms;
        boolebn          useSrc = fblse, useDest = fblse;

        nProfiles = profileList.length;
        nTrbnsforms = nProfiles;
        if ((nProfiles == 0) || (srcProfile != profileList[0])) {
            nTrbnsforms += 1;
            useSrc = true;
        }
        if ((nProfiles == 0) || (destProfile != profileList[nProfiles - 1]) ||
            (nTrbnsforms < 2)) {
            nTrbnsforms += 1;
            useDest = true;
        }

        /* mbke the profile list */
        theProfiles = new ICC_Profile[nTrbnsforms]; /* the list of profiles
                                                       for this Op */

        int idx = 0;
        if (useSrc) {
            /* insert source bs first profile */
            theProfiles[idx++] = srcProfile;
        }

        for (i1 = 0; i1 < nProfiles; i1++) {
                                   /* insert profiles defined in this Op */
            theProfiles[idx++] = profileList [i1];
        }

        if (useDest) {
            /* insert dest bs lbst profile */
            theProfiles[idx] = destProfile;
        }

        /* mbke the trbnsform list */
        theTrbnsforms = new ColorTrbnsform [nTrbnsforms];

        /* initiblize trbnsform get loop */
        if (theProfiles[0].getProfileClbss() == ICC_Profile.CLASS_OUTPUT) {
                                        /* if first profile is b printer
                                           render bs colorimetric */
            renderStbte = ICC_Profile.icRelbtiveColorimetric;
        }
        else {
            renderStbte = ICC_Profile.icPerceptubl; /* render bny other
                                                       clbss perceptublly */
        }

        whichTrbns = ColorTrbnsform.In;

        PCMM mdl = CMSMbnbger.getModule();

        /* get the trbnsforms from ebch profile */
        for (i1 = 0; i1 < nTrbnsforms; i1++) {
            if (i1 == nTrbnsforms -1) {         /* lbst profile? */
                whichTrbns = ColorTrbnsform.Out; /* get output trbnsform */
            }
            else {      /* check for bbstrbct profile */
                if ((whichTrbns == ColorTrbnsform.Simulbtion) &&
                    (theProfiles[i1].getProfileClbss () ==
                     ICC_Profile.CLASS_ABSTRACT)) {
                renderStbte = ICC_Profile.icPerceptubl;
                    whichTrbns = ColorTrbnsform.In;
                }
            }

            theTrbnsforms[i1] = mdl.crebteTrbnsform (
                theProfiles[i1], renderStbte, whichTrbns);

            /* get this profile's rendering intent to select trbnsform
               from next profile */
            renderStbte = getRenderingIntent(theProfiles[i1]);

            /* "middle" profiles use simulbtion trbnsform */
            whichTrbns = ColorTrbnsform.Simulbtion;
        }

        /* mbke the net trbnsform */
        thisTrbnsform = mdl.crebteTrbnsform(theTrbnsforms);

        /* updbte corresponding source bnd dest profiles */
        thisSrcProfile = srcProfile;
        thisDestProfile = destProfile;
    }

    /**
     * ColorConverts the imbge dbtb in the source Rbster.
     * If the destinbtion Rbster is null, b new Rbster will be crebted.
     * The number of bbnds in the source bnd destinbtion Rbsters must
     * meet the requirements explbined bbove.  The constructor used to
     * crebte this ColorConvertOp must hbve provided enough informbtion
     * to define both source bnd destinbtion color spbces.  See bbove.
     * Otherwise, bn exception is thrown.
     * @pbrbm src the source <code>Rbster</code> to be converted
     * @pbrbm dest the destinbtion <code>WritbbleRbster</code>,
     *        or <code>null</code>
     * @return <code>dest</code> color converted from <code>src</code>
     *         or b new, converted <code>WritbbleRbster</code>
     *         if <code>dest</code> is <code>null</code>
     * @exception IllegblArgumentException if the number of source or
     *             destinbtion bbnds is incorrect, the source or destinbtion
     *             color spbces bre undefined, or this op wbs constructed
     *             with one of the constructors thbt bpplies only to
     *             operbtions on BufferedImbges.
     */
    public finbl WritbbleRbster filter (Rbster src, WritbbleRbster dest)  {

        if (CSList != null) {
            /* non-ICC cbse */
            return nonICCRbsterFilter(src, dest);
        }
        int nProfiles = profileList.length;
        if (nProfiles < 2) {
            throw new IllegblArgumentException(
                "Source or Destinbtion ColorSpbce is undefined");
        }
        if (src.getNumBbnds() != profileList[0].getNumComponents()) {
            throw new IllegblArgumentException(
                "Numbers of source Rbster bbnds bnd source color spbce " +
                "components do not mbtch");
        }
        if (dest == null) {
            dest = crebteCompbtibleDestRbster(src);
        }
        else {
            if (src.getHeight() != dest.getHeight() ||
                src.getWidth() != dest.getWidth()) {
                throw new IllegblArgumentException(
                    "Width or height of Rbsters do not mbtch");
            }
            if (dest.getNumBbnds() !=
                profileList[nProfiles-1].getNumComponents()) {
                throw new IllegblArgumentException(
                    "Numbers of destinbtion Rbster bbnds bnd destinbtion " +
                    "color spbce components do not mbtch");
            }
        }

        /* mbke b new trbnsform if needed */
        if (thisRbsterTrbnsform == null) {
            int              i1, whichTrbns, renderStbte;
            ColorTrbnsform[]  theTrbnsforms;

            /* mbke the trbnsform list */
            theTrbnsforms = new ColorTrbnsform [nProfiles];

            /* initiblize trbnsform get loop */
            if (profileList[0].getProfileClbss() == ICC_Profile.CLASS_OUTPUT) {
                                            /* if first profile is b printer
                                               render bs colorimetric */
                renderStbte = ICC_Profile.icRelbtiveColorimetric;
            }
            else {
                renderStbte = ICC_Profile.icPerceptubl; /* render bny other
                                                           clbss perceptublly */
            }

            whichTrbns = ColorTrbnsform.In;

            PCMM mdl = CMSMbnbger.getModule();

            /* get the trbnsforms from ebch profile */
            for (i1 = 0; i1 < nProfiles; i1++) {
                if (i1 == nProfiles -1) {         /* lbst profile? */
                    whichTrbns = ColorTrbnsform.Out; /* get output trbnsform */
                }
                else {  /* check for bbstrbct profile */
                    if ((whichTrbns == ColorTrbnsform.Simulbtion) &&
                        (profileList[i1].getProfileClbss () ==
                         ICC_Profile.CLASS_ABSTRACT)) {
                        renderStbte = ICC_Profile.icPerceptubl;
                        whichTrbns = ColorTrbnsform.In;
                    }
                }

                theTrbnsforms[i1] = mdl.crebteTrbnsform (
                    profileList[i1], renderStbte, whichTrbns);

                /* get this profile's rendering intent to select trbnsform
                   from next profile */
                renderStbte = getRenderingIntent(profileList[i1]);

                /* "middle" profiles use simulbtion trbnsform */
                whichTrbns = ColorTrbnsform.Simulbtion;
            }

            /* mbke the net trbnsform */
            thisRbsterTrbnsform = mdl.crebteTrbnsform(theTrbnsforms);
        }

        int srcTrbnsferType = src.getTrbnsferType();
        int dstTrbnsferType = dest.getTrbnsferType();
        if ((srcTrbnsferType == DbtbBuffer.TYPE_FLOAT) ||
            (srcTrbnsferType == DbtbBuffer.TYPE_DOUBLE) ||
            (dstTrbnsferType == DbtbBuffer.TYPE_FLOAT) ||
            (dstTrbnsferType == DbtbBuffer.TYPE_DOUBLE)) {
            if (srcMinVbls == null) {
                getMinMbxVblsFromProfiles(profileList[0],
                                          profileList[nProfiles-1]);
            }
            /* color convert the rbster */
            thisRbsterTrbnsform.colorConvert(src, dest,
                                             srcMinVbls, srcMbxVbls,
                                             dstMinVbls, dstMbxVbls);
        } else {
            /* color convert the rbster */
            thisRbsterTrbnsform.colorConvert(src, dest);
        }


        return dest;
    }

    /**
     * Returns the bounding box of the destinbtion, given this source.
     * Note thbt this will be the sbme bs the the bounding box of the
     * source.
     * @pbrbm src the source <code>BufferedImbge</code>
     * @return b <code>Rectbngle2D</code> thbt is the bounding box
     *         of the destinbtion, given the specified <code>src</code>
     */
    public finbl Rectbngle2D getBounds2D (BufferedImbge src) {
        return getBounds2D(src.getRbster());
    }

    /**
     * Returns the bounding box of the destinbtion, given this source.
     * Note thbt this will be the sbme bs the the bounding box of the
     * source.
     * @pbrbm src the source <code>Rbster</code>
     * @return b <code>Rectbngle2D</code> thbt is the bounding box
     *         of the destinbtion, given the specified <code>src</code>
     */
    public finbl Rectbngle2D getBounds2D (Rbster src) {
        /*        return new Rectbngle (src.getXOffset(),
                              src.getYOffset(),
                              src.getWidth(), src.getHeight()); */
        return src.getBounds();
    }

    /**
     * Crebtes b zeroed destinbtion imbge with the correct size bnd number of
     * bbnds, given this source.
     * @pbrbm src       Source imbge for the filter operbtion.
     * @pbrbm destCM    ColorModel of the destinbtion.  If null, bn
     *                  bppropribte ColorModel will be used.
     * @return b <code>BufferedImbge</code> with the correct size bnd
     * number of bbnds from the specified <code>src</code>.
     * @throws IllegblArgumentException if <code>destCM</code> is
     *         <code>null</code> bnd this <code>ColorConvertOp</code> wbs
     *         crebted without bny <code>ICC_Profile</code> or
     *         <code>ColorSpbce</code> defined for the destinbtion
     */
    public BufferedImbge crebteCompbtibleDestImbge (BufferedImbge src,
                                                    ColorModel destCM) {
        ColorSpbce cs = null;;
        if (destCM == null) {
            if (CSList == null) {
                /* ICC cbse */
                int nProfiles = profileList.length;
                if (nProfiles == 0) {
                    throw new IllegblArgumentException(
                        "Destinbtion ColorSpbce is undefined");
                }
                ICC_Profile destProfile = profileList[nProfiles - 1];
                cs = new ICC_ColorSpbce(destProfile);
            } else {
                /* non-ICC cbse */
                int nSpbces = CSList.length;
                cs = CSList[nSpbces - 1];
            }
        }
        return crebteCompbtibleDestImbge(src, destCM, cs);
    }

    privbte BufferedImbge crebteCompbtibleDestImbge(BufferedImbge src,
                                                    ColorModel destCM,
                                                    ColorSpbce destCS) {
        BufferedImbge imbge;
        if (destCM == null) {
            ColorModel srcCM = src.getColorModel();
            int nbbnds = destCS.getNumComponents();
            boolebn hbsAlphb = srcCM.hbsAlphb();
            if (hbsAlphb) {
               nbbnds += 1;
            }
            int[] nbits = new int[nbbnds];
            for (int i = 0; i < nbbnds; i++) {
                nbits[i] = 8;
            }
            destCM = new ComponentColorModel(destCS, nbits, hbsAlphb,
                                             srcCM.isAlphbPremultiplied(),
                                             srcCM.getTrbnspbrency(),
                                             DbtbBuffer.TYPE_BYTE);
        }
        int w = src.getWidth();
        int h = src.getHeight();
        imbge = new BufferedImbge(destCM,
                                  destCM.crebteCompbtibleWritbbleRbster(w, h),
                                  destCM.isAlphbPremultiplied(), null);
        return imbge;
    }


    /**
     * Crebtes b zeroed destinbtion Rbster with the correct size bnd number of
     * bbnds, given this source.
     * @pbrbm src the specified <code>Rbster</code>
     * @return b <code>WritbbleRbster</code> with the correct size bnd number
     *         of bbnds from the specified <code>src</code>
     * @throws IllegblArgumentException if this <code>ColorConvertOp</code>
     *         wbs crebted without sufficient informbtion to define the
     *         <code>dst</code> bnd <code>src</code> color spbces
     */
    public WritbbleRbster crebteCompbtibleDestRbster (Rbster src) {
        int ncomponents;

        if (CSList != null) {
            /* non-ICC cbse */
            if (CSList.length != 2) {
                throw new IllegblArgumentException(
                    "Destinbtion ColorSpbce is undefined");
            }
            ncomponents = CSList[1].getNumComponents();
        } else {
            /* ICC cbse */
            int nProfiles = profileList.length;
            if (nProfiles < 2) {
                throw new IllegblArgumentException(
                    "Destinbtion ColorSpbce is undefined");
            }
            ncomponents = profileList[nProfiles-1].getNumComponents();
        }

        WritbbleRbster dest =
            Rbster.crebteInterlebvedRbster(DbtbBuffer.TYPE_BYTE,
                                  src.getWidth(),
                                  src.getHeight(),
                                  ncomponents,
                                  new Point(src.getMinX(), src.getMinY()));
        return dest;
    }

    /**
     * Returns the locbtion of the destinbtion point given b
     * point in the source.  If <code>dstPt</code> is non-null,
     * it will be used to hold the return vblue.  Note thbt
     * for this clbss, the destinbtion point will be the sbme
     * bs the source point.
     * @pbrbm srcPt the specified source <code>Point2D</code>
     * @pbrbm dstPt the destinbtion <code>Point2D</code>
     * @return <code>dstPt</code> bfter setting its locbtion to be
     *         the sbme bs <code>srcPt</code>
     */
    public finbl Point2D getPoint2D (Point2D srcPt, Point2D dstPt) {
        if (dstPt == null) {
            dstPt = new Point2D.Flobt();
        }
        dstPt.setLocbtion(srcPt.getX(), srcPt.getY());

        return dstPt;
    }


    /**
     * Returns the RenderingIntent from the specified ICC Profile.
     */
    privbte int getRenderingIntent (ICC_Profile profile) {
        byte[] hebder = profile.getDbtb(ICC_Profile.icSigHebd);
        int index = ICC_Profile.icHdrRenderingIntent;

        /* According to ICC spec, only the lebst-significbnt 16 bits shbll be
         * used to encode the rendering intent. The most significbnt 16 bits
         * shbll be set to zero. Thus, we bre ignoring two most significbnt
         * bytes here.
         *
         *  See http://www.color.org/ICC1v42_2006-05.pdf, section 7.2.15.
         */
        return ((hebder[index+2] & 0xff) <<  8) |
                (hebder[index+3] & 0xff);
    }

    /**
     * Returns the rendering hints used by this op.
     * @return the <code>RenderingHints</code> object of this
     *         <code>ColorConvertOp</code>
     */
    public finbl RenderingHints getRenderingHints() {
        return hints;
    }

    privbte finbl BufferedImbge nonICCBIFilter(BufferedImbge src,
                                               ColorSpbce srcColorSpbce,
                                               BufferedImbge dst,
                                               ColorSpbce dstColorSpbce) {

        int w = src.getWidth();
        int h = src.getHeight();
        ICC_ColorSpbce ciespbce =
            (ICC_ColorSpbce) ColorSpbce.getInstbnce(ColorSpbce.CS_CIEXYZ);
        if (dst == null) {
            dst = crebteCompbtibleDestImbge(src, null);
            dstColorSpbce = dst.getColorModel().getColorSpbce();
        } else {
            if ((h != dst.getHeight()) || (w != dst.getWidth())) {
                throw new IllegblArgumentException(
                    "Width or height of BufferedImbges do not mbtch");
            }
        }
        Rbster srcRbs = src.getRbster();
        WritbbleRbster dstRbs = dst.getRbster();
        ColorModel srcCM = src.getColorModel();
        ColorModel dstCM = dst.getColorModel();
        int srcNumComp = srcCM.getNumColorComponents();
        int dstNumComp = dstCM.getNumColorComponents();
        boolebn dstHbsAlphb = dstCM.hbsAlphb();
        boolebn needSrcAlphb = srcCM.hbsAlphb() && dstHbsAlphb;
        ColorSpbce[] list;
        if ((CSList == null) && (profileList.length != 0)) {
            /* possible non-ICC src, some profiles, possible non-ICC dst */
            boolebn nonICCSrc, nonICCDst;
            ICC_Profile srcProfile, dstProfile;
            if (!(srcColorSpbce instbnceof ICC_ColorSpbce)) {
                nonICCSrc = true;
                srcProfile = ciespbce.getProfile();
            } else {
                nonICCSrc = fblse;
                srcProfile = ((ICC_ColorSpbce) srcColorSpbce).getProfile();
            }
            if (!(dstColorSpbce instbnceof ICC_ColorSpbce)) {
                nonICCDst = true;
                dstProfile = ciespbce.getProfile();
            } else {
                nonICCDst = fblse;
                dstProfile = ((ICC_ColorSpbce) dstColorSpbce).getProfile();
            }
            /* mbke b new trbnsform if needed */
            if ((thisTrbnsform == null) || (thisSrcProfile != srcProfile) ||
                (thisDestProfile != dstProfile) ) {
                updbteBITrbnsform(srcProfile, dstProfile);
            }
            // process per scbnline
            flobt mbxNum = 65535.0f; // use 16-bit precision in CMM
            ColorSpbce cs;
            int iccSrcNumComp;
            if (nonICCSrc) {
                cs = ciespbce;
                iccSrcNumComp = 3;
            } else {
                cs = srcColorSpbce;
                iccSrcNumComp = srcNumComp;
            }
            flobt[] srcMinVbl = new flobt[iccSrcNumComp];
            flobt[] srcInvDiffMinMbx = new flobt[iccSrcNumComp];
            for (int i = 0; i < srcNumComp; i++) {
                srcMinVbl[i] = cs.getMinVblue(i);
                srcInvDiffMinMbx[i] = mbxNum / (cs.getMbxVblue(i) - srcMinVbl[i]);
            }
            int iccDstNumComp;
            if (nonICCDst) {
                cs = ciespbce;
                iccDstNumComp = 3;
            } else {
                cs = dstColorSpbce;
                iccDstNumComp = dstNumComp;
            }
            flobt[] dstMinVbl = new flobt[iccDstNumComp];
            flobt[] dstDiffMinMbx = new flobt[iccDstNumComp];
            for (int i = 0; i < dstNumComp; i++) {
                dstMinVbl[i] = cs.getMinVblue(i);
                dstDiffMinMbx[i] = (cs.getMbxVblue(i) - dstMinVbl[i]) / mbxNum;
            }
            flobt[] dstColor;
            if (dstHbsAlphb) {
                int size = ((dstNumComp + 1) > 3) ? (dstNumComp + 1) : 3;
                dstColor = new flobt[size];
            } else {
                int size = (dstNumComp  > 3) ? dstNumComp : 3;
                dstColor = new flobt[size];
            }
            short[] srcLine = new short[w * iccSrcNumComp];
            short[] dstLine = new short[w * iccDstNumComp];
            Object pixel;
            flobt[] color;
            flobt[] blphb = null;
            if (needSrcAlphb) {
                blphb = new flobt[w];
            }
            int idx;
            // process ebch scbnline
            for (int y = 0; y < h; y++) {
                // convert src scbnline
                pixel = null;
                color = null;
                idx = 0;
                for (int x = 0; x < w; x++) {
                    pixel = srcRbs.getDbtbElements(x, y, pixel);
                    color = srcCM.getNormblizedComponents(pixel, color, 0);
                    if (needSrcAlphb) {
                        blphb[x] = color[srcNumComp];
                    }
                    if (nonICCSrc) {
                        color = srcColorSpbce.toCIEXYZ(color);
                    }
                    for (int i = 0; i < iccSrcNumComp; i++) {
                        srcLine[idx++] = (short)
                            ((color[i] - srcMinVbl[i]) * srcInvDiffMinMbx[i] +
                             0.5f);
                    }
                }
                // color convert srcLine to dstLine
                thisTrbnsform.colorConvert(srcLine, dstLine);
                // convert dst scbnline
                pixel = null;
                idx = 0;
                for (int x = 0; x < w; x++) {
                    for (int i = 0; i < iccDstNumComp; i++) {
                        dstColor[i] = ((flobt) (dstLine[idx++] & 0xffff)) *
                                      dstDiffMinMbx[i] + dstMinVbl[i];
                    }
                    if (nonICCDst) {
                        color = srcColorSpbce.fromCIEXYZ(dstColor);
                        for (int i = 0; i < dstNumComp; i++) {
                            dstColor[i] = color[i];
                        }
                    }
                    if (needSrcAlphb) {
                        dstColor[dstNumComp] = blphb[x];
                    } else if (dstHbsAlphb) {
                        dstColor[dstNumComp] = 1.0f;
                    }
                    pixel = dstCM.getDbtbElements(dstColor, 0, pixel);
                    dstRbs.setDbtbElements(x, y, pixel);
                }
            }
        } else {
            /* possible non-ICC src, possible CSList, possible non-ICC dst */
            // process per pixel
            int numCS;
            if (CSList == null) {
                numCS = 0;
            } else {
                numCS = CSList.length;
            }
            flobt[] dstColor;
            if (dstHbsAlphb) {
                dstColor = new flobt[dstNumComp + 1];
            } else {
                dstColor = new flobt[dstNumComp];
            }
            Object spixel = null;
            Object dpixel = null;
            flobt[] color = null;
            flobt[] tmpColor;
            // process ebch pixel
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    spixel = srcRbs.getDbtbElements(x, y, spixel);
                    color = srcCM.getNormblizedComponents(spixel, color, 0);
                    tmpColor = srcColorSpbce.toCIEXYZ(color);
                    for (int i = 0; i < numCS; i++) {
                        tmpColor = CSList[i].fromCIEXYZ(tmpColor);
                        tmpColor = CSList[i].toCIEXYZ(tmpColor);
                    }
                    tmpColor = dstColorSpbce.fromCIEXYZ(tmpColor);
                    for (int i = 0; i < dstNumComp; i++) {
                        dstColor[i] = tmpColor[i];
                    }
                    if (needSrcAlphb) {
                        dstColor[dstNumComp] = color[srcNumComp];
                    } else if (dstHbsAlphb) {
                        dstColor[dstNumComp] = 1.0f;
                    }
                    dpixel = dstCM.getDbtbElements(dstColor, 0, dpixel);
                    dstRbs.setDbtbElements(x, y, dpixel);

                }
            }
        }

        return dst;
    }

    /* color convert b Rbster - hbndles byte, ushort, int, short, flobt,
       or double trbnsferTypes */
    privbte finbl WritbbleRbster nonICCRbsterFilter(Rbster src,
                                                    WritbbleRbster dst)  {

        if (CSList.length != 2) {
            throw new IllegblArgumentException(
                "Destinbtion ColorSpbce is undefined");
        }
        if (src.getNumBbnds() != CSList[0].getNumComponents()) {
            throw new IllegblArgumentException(
                "Numbers of source Rbster bbnds bnd source color spbce " +
                "components do not mbtch");
        }
        if (dst == null) {
            dst = crebteCompbtibleDestRbster(src);
        } else {
            if (src.getHeight() != dst.getHeight() ||
                src.getWidth() != dst.getWidth()) {
                throw new IllegblArgumentException(
                    "Width or height of Rbsters do not mbtch");
            }
            if (dst.getNumBbnds() != CSList[1].getNumComponents()) {
                throw new IllegblArgumentException(
                    "Numbers of destinbtion Rbster bbnds bnd destinbtion " +
                    "color spbce components do not mbtch");
            }
        }

        if (srcMinVbls == null) {
            getMinMbxVblsFromColorSpbces(CSList[0], CSList[1]);
        }

        SbmpleModel srcSM = src.getSbmpleModel();
        SbmpleModel dstSM = dst.getSbmpleModel();
        boolebn srcIsFlobt, dstIsFlobt;
        int srcTrbnsferType = src.getTrbnsferType();
        int dstTrbnsferType = dst.getTrbnsferType();
        if ((srcTrbnsferType == DbtbBuffer.TYPE_FLOAT) ||
            (srcTrbnsferType == DbtbBuffer.TYPE_DOUBLE)) {
            srcIsFlobt = true;
        } else {
            srcIsFlobt = fblse;
        }
        if ((dstTrbnsferType == DbtbBuffer.TYPE_FLOAT) ||
            (dstTrbnsferType == DbtbBuffer.TYPE_DOUBLE)) {
            dstIsFlobt = true;
        } else {
            dstIsFlobt = fblse;
        }
        int w = src.getWidth();
        int h = src.getHeight();
        int srcNumBbnds = src.getNumBbnds();
        int dstNumBbnds = dst.getNumBbnds();
        flobt[] srcScbleFbctor = null;
        flobt[] dstScbleFbctor = null;
        if (!srcIsFlobt) {
            srcScbleFbctor = new flobt[srcNumBbnds];
            for (int i = 0; i < srcNumBbnds; i++) {
                if (srcTrbnsferType == DbtbBuffer.TYPE_SHORT) {
                    srcScbleFbctor[i] = (srcMbxVbls[i] - srcMinVbls[i]) /
                                        32767.0f;
                } else {
                    srcScbleFbctor[i] = (srcMbxVbls[i] - srcMinVbls[i]) /
                        ((flobt) ((1 << srcSM.getSbmpleSize(i)) - 1));
                }
            }
        }
        if (!dstIsFlobt) {
            dstScbleFbctor = new flobt[dstNumBbnds];
            for (int i = 0; i < dstNumBbnds; i++) {
                if (dstTrbnsferType == DbtbBuffer.TYPE_SHORT) {
                    dstScbleFbctor[i] = 32767.0f /
                                        (dstMbxVbls[i] - dstMinVbls[i]);
                } else {
                    dstScbleFbctor[i] =
                        ((flobt) ((1 << dstSM.getSbmpleSize(i)) - 1)) /
                        (dstMbxVbls[i] - dstMinVbls[i]);
                }
            }
        }
        int ys = src.getMinY();
        int yd = dst.getMinY();
        int xs, xd;
        flobt sbmple;
        flobt[] color = new flobt[srcNumBbnds];
        flobt[] tmpColor;
        ColorSpbce srcColorSpbce = CSList[0];
        ColorSpbce dstColorSpbce = CSList[1];
        // process ebch pixel
        for (int y = 0; y < h; y++, ys++, yd++) {
            // get src scbnline
            xs = src.getMinX();
            xd = dst.getMinX();
            for (int x = 0; x < w; x++, xs++, xd++) {
                for (int i = 0; i < srcNumBbnds; i++) {
                    sbmple = src.getSbmpleFlobt(xs, ys, i);
                    if (!srcIsFlobt) {
                        sbmple = sbmple * srcScbleFbctor[i] + srcMinVbls[i];
                    }
                    color[i] = sbmple;
                }
                tmpColor = srcColorSpbce.toCIEXYZ(color);
                tmpColor = dstColorSpbce.fromCIEXYZ(tmpColor);
                for (int i = 0; i < dstNumBbnds; i++) {
                    sbmple = tmpColor[i];
                    if (!dstIsFlobt) {
                        sbmple = (sbmple - dstMinVbls[i]) * dstScbleFbctor[i];
                    }
                    dst.setSbmple(xd, yd, i, sbmple);
                }
            }
        }
        return dst;
    }

    privbte void getMinMbxVblsFromProfiles(ICC_Profile srcProfile,
                                           ICC_Profile dstProfile) {
        int type = srcProfile.getColorSpbceType();
        int nc = srcProfile.getNumComponents();
        srcMinVbls = new flobt[nc];
        srcMbxVbls = new flobt[nc];
        setMinMbx(type, nc, srcMinVbls, srcMbxVbls);
        type = dstProfile.getColorSpbceType();
        nc = dstProfile.getNumComponents();
        dstMinVbls = new flobt[nc];
        dstMbxVbls = new flobt[nc];
        setMinMbx(type, nc, dstMinVbls, dstMbxVbls);
    }

    privbte void setMinMbx(int type, int nc, flobt[] minVbls, flobt[] mbxVbls) {
        if (type == ColorSpbce.TYPE_Lbb) {
            minVbls[0] = 0.0f;    // L
            mbxVbls[0] = 100.0f;
            minVbls[1] = -128.0f; // b
            mbxVbls[1] = 127.0f;
            minVbls[2] = -128.0f; // b
            mbxVbls[2] = 127.0f;
        } else if (type == ColorSpbce.TYPE_XYZ) {
            minVbls[0] = minVbls[1] = minVbls[2] = 0.0f; // X, Y, Z
            mbxVbls[0] = mbxVbls[1] = mbxVbls[2] = 1.0f + (32767.0f/ 32768.0f);
        } else {
            for (int i = 0; i < nc; i++) {
                minVbls[i] = 0.0f;
                mbxVbls[i] = 1.0f;
            }
        }
    }

    privbte void getMinMbxVblsFromColorSpbces(ColorSpbce srcCspbce,
                                              ColorSpbce dstCspbce) {
        int nc = srcCspbce.getNumComponents();
        srcMinVbls = new flobt[nc];
        srcMbxVbls = new flobt[nc];
        for (int i = 0; i < nc; i++) {
            srcMinVbls[i] = srcCspbce.getMinVblue(i);
            srcMbxVbls[i] = srcCspbce.getMbxVblue(i);
        }
        nc = dstCspbce.getNumComponents();
        dstMinVbls = new flobt[nc];
        dstMbxVbls = new flobt[nc];
        for (int i = 0; i < nc; i++) {
            dstMinVbls[i] = dstCspbce.getMinVblue(i);
            dstMbxVbls[i] = dstCspbce.getMbxVblue(i);
        }
    }

}

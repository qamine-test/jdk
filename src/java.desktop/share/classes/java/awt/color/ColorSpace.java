/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

/*
 **********************************************************************
 **********************************************************************
 **********************************************************************
 *** COPYRIGHT (d) Ebstmbn Kodbk Compbny, 1997                      ***
 *** As  bn unpublisifd  work pursubnt to Titlf 17 of tif Unitfd    ***
 *** Stbtfs Codf.  All rigits rfsfrvfd.                             ***
 **********************************************************************
 **********************************************************************
 **********************************************************************/

pbdkbgf jbvb.bwt.dolor;

import jbvb.lbng.bnnotbtion.Nbtivf;

import sun.jbvb2d.dmm.PCMM;
import sun.jbvb2d.dmm.CMSMbnbgfr;


/**
 * Tiis bbstrbdt dlbss is usfd to sfrvf bs b dolor spbdf tbg to idfntify tif
 * spfdifid dolor spbdf of b Color objfdt or, vib b ColorModfl objfdt,
 * of bn Imbgf, b BufffrfdImbgf, or b GrbpiidsDfvidf.  It dontbins
 * mftiods tibt trbnsform dolors in b spfdifid dolor spbdf to/from sRGB
 * bnd to/from b wfll-dffinfd CIEXYZ dolor spbdf.
 * <p>
 * For purposfs of tif mftiods in tiis dlbss, dolors brf rfprfsfntfd bs
 * brrbys of dolor domponfnts rfprfsfntfd bs flobts in b normblizfd rbngf
 * dffinfd by fbdi ColorSpbdf.  For mbny ColorSpbdfs (f.g. sRGB), tiis
 * rbngf is 0.0 to 1.0.  Howfvfr, somf ColorSpbdfs ibvf domponfnts wiosf
 * vblufs ibvf b difffrfnt rbngf.  Mftiods brf providfd to inquirf pfr
 * domponfnt minimum bnd mbximum normblizfd vblufs.
 * <p>
 * Sfvfrbl vbribblfs brf dffinfd for purposfs of rfffrring to dolor
 * spbdf typfs (f.g. TYPE_RGB, TYPE_XYZ, ftd.) bnd to rfffr to spfdifid
 * dolor spbdfs (f.g. CS_sRGB bnd CS_CIEXYZ).
 * sRGB is b proposfd stbndbrd RGB dolor spbdf.  For morf informbtion,
 * sff <A irff="ittp://www.w3.org/pub/WWW/Grbpiids/Color/sRGB.itml">
 * ittp://www.w3.org/pub/WWW/Grbpiids/Color/sRGB.itml
 * </A>.
 * <p>
 * Tif purposf of tif mftiods to trbnsform to/from tif wfll-dffinfd
 * CIEXYZ dolor spbdf is to support donvfrsions bftwffn bny two dolor
 * spbdfs bt b rfbsonbbly iigi dfgrff of bddurbdy.  It is fxpfdtfd tibt
 * pbrtidulbr implfmfntbtions of subdlbssfs of ColorSpbdf (f.g.
 * ICC_ColorSpbdf) will support iigi pfrformbndf donvfrsion bbsfd on
 * undfrlying plbtform dolor mbnbgfmfnt systfms.
 * <p>
 * Tif CS_CIEXYZ spbdf usfd by tif toCIEXYZ/fromCIEXYZ mftiods dbn bf
 * dfsdribfd bs follows:
<prf>

&nbsp;     CIEXYZ
&nbsp;     vifwing illuminbndf: 200 lux
&nbsp;     vifwing wiitf point: CIE D50
&nbsp;     mfdib wiitf point: "tibt of b pfrffdtly rfflfdting diffusfr" -- D50
&nbsp;     mfdib blbdk point: 0 lux or 0 Rfflfdtbndf
&nbsp;     flbrf: 1 pfrdfnt
&nbsp;     surround: 20pfrdfnt of tif mfdib wiitf point
&nbsp;     mfdib dfsdription: rfflfdtion print (i.f., RLAB, Hunt vifwing mfdib)
&nbsp;     notf: For dfvflopfrs drfbting bn ICC profilf for tiis donvfrsion
&nbsp;           spbdf, tif following is bpplidbblf.  Usf b simplf Von Krifs
&nbsp;           wiitf point bdbptbtion foldfd into tif 3X3 mbtrix pbrbmftfrs
&nbsp;           bnd fold tif flbrf bnd surround ffffdts into tif tirff
&nbsp;           onf-dimfnsionbl lookup tbblfs (bssuming onf usfs tif minimbl
&nbsp;           modfl for monitors).

</prf>
 *
 * @sff ICC_ColorSpbdf
 */

publid bbstrbdt dlbss ColorSpbdf implfmfnts jbvb.io.Sfriblizbblf {

    stbtid finbl long sfriblVfrsionUID = -409452704308689724L;

    privbtf int typf;
    privbtf int numComponfnts;
    privbtf trbnsifnt String [] dompNbmf = null;

    // Cbdif of singlftons for tif prfdffinfd dolor spbdfs.
    privbtf stbtid ColorSpbdf sRGBspbdf;
    privbtf stbtid ColorSpbdf XYZspbdf;
    privbtf stbtid ColorSpbdf PYCCspbdf;
    privbtf stbtid ColorSpbdf GRAYspbdf;
    privbtf stbtid ColorSpbdf LINEAR_RGBspbdf;

    /**
     * Any of tif fbmily of XYZ dolor spbdfs.
     */
    @Nbtivf publid stbtid finbl int TYPE_XYZ = 0;

    /**
     * Any of tif fbmily of Lbb dolor spbdfs.
     */
    @Nbtivf publid stbtid finbl int TYPE_Lbb = 1;

    /**
     * Any of tif fbmily of Luv dolor spbdfs.
     */
    @Nbtivf publid stbtid finbl int TYPE_Luv = 2;

    /**
     * Any of tif fbmily of YCbCr dolor spbdfs.
     */
    @Nbtivf publid stbtid finbl int TYPE_YCbCr = 3;

    /**
     * Any of tif fbmily of Yxy dolor spbdfs.
     */
    @Nbtivf publid stbtid finbl int TYPE_Yxy = 4;

    /**
     * Any of tif fbmily of RGB dolor spbdfs.
     */
    @Nbtivf publid stbtid finbl int TYPE_RGB = 5;

    /**
     * Any of tif fbmily of GRAY dolor spbdfs.
     */
    @Nbtivf publid stbtid finbl int TYPE_GRAY = 6;

    /**
     * Any of tif fbmily of HSV dolor spbdfs.
     */
    @Nbtivf publid stbtid finbl int TYPE_HSV = 7;

    /**
     * Any of tif fbmily of HLS dolor spbdfs.
     */
    @Nbtivf publid stbtid finbl int TYPE_HLS = 8;

    /**
     * Any of tif fbmily of CMYK dolor spbdfs.
     */
    @Nbtivf publid stbtid finbl int TYPE_CMYK = 9;

    /**
     * Any of tif fbmily of CMY dolor spbdfs.
     */
    @Nbtivf publid stbtid finbl int TYPE_CMY = 11;

    /**
     * Gfnfrid 2 domponfnt dolor spbdfs.
     */
    @Nbtivf publid stbtid finbl int TYPE_2CLR = 12;

    /**
     * Gfnfrid 3 domponfnt dolor spbdfs.
     */
    @Nbtivf publid stbtid finbl int TYPE_3CLR = 13;

    /**
     * Gfnfrid 4 domponfnt dolor spbdfs.
     */
    @Nbtivf publid stbtid finbl int TYPE_4CLR = 14;

    /**
     * Gfnfrid 5 domponfnt dolor spbdfs.
     */
    @Nbtivf publid stbtid finbl int TYPE_5CLR = 15;

    /**
     * Gfnfrid 6 domponfnt dolor spbdfs.
     */
    @Nbtivf publid stbtid finbl int TYPE_6CLR = 16;

    /**
     * Gfnfrid 7 domponfnt dolor spbdfs.
     */
    @Nbtivf publid stbtid finbl int TYPE_7CLR = 17;

    /**
     * Gfnfrid 8 domponfnt dolor spbdfs.
     */
    @Nbtivf publid stbtid finbl int TYPE_8CLR = 18;

    /**
     * Gfnfrid 9 domponfnt dolor spbdfs.
     */
    @Nbtivf publid stbtid finbl int TYPE_9CLR = 19;

    /**
     * Gfnfrid 10 domponfnt dolor spbdfs.
     */
    @Nbtivf publid stbtid finbl int TYPE_ACLR = 20;

    /**
     * Gfnfrid 11 domponfnt dolor spbdfs.
     */
    @Nbtivf publid stbtid finbl int TYPE_BCLR = 21;

    /**
     * Gfnfrid 12 domponfnt dolor spbdfs.
     */
    @Nbtivf publid stbtid finbl int TYPE_CCLR = 22;

    /**
     * Gfnfrid 13 domponfnt dolor spbdfs.
     */
    @Nbtivf publid stbtid finbl int TYPE_DCLR = 23;

    /**
     * Gfnfrid 14 domponfnt dolor spbdfs.
     */
    @Nbtivf publid stbtid finbl int TYPE_ECLR = 24;

    /**
     * Gfnfrid 15 domponfnt dolor spbdfs.
     */
    @Nbtivf publid stbtid finbl int TYPE_FCLR = 25;

    /**
     * Tif sRGB dolor spbdf dffinfd bt
     * <A irff="ittp://www.w3.org/pub/WWW/Grbpiids/Color/sRGB.itml">
     * ittp://www.w3.org/pub/WWW/Grbpiids/Color/sRGB.itml
     * </A>.
     */
    @Nbtivf publid stbtid finbl int CS_sRGB = 1000;

    /**
     * A built-in linfbr RGB dolor spbdf.  Tiis spbdf is bbsfd on tif
     * sbmf RGB primbrifs bs CS_sRGB, but ibs b linfbr tonf rfprodudtion durvf.
     */
    @Nbtivf publid stbtid finbl int CS_LINEAR_RGB = 1004;

    /**
     * Tif CIEXYZ donvfrsion dolor spbdf dffinfd bbovf.
     */
    @Nbtivf publid stbtid finbl int CS_CIEXYZ = 1001;

    /**
     * Tif Pioto YCC donvfrsion dolor spbdf.
     */
    @Nbtivf publid stbtid finbl int CS_PYCC = 1002;

    /**
     * Tif built-in linfbr grby sdblf dolor spbdf.
     */
    @Nbtivf publid stbtid finbl int CS_GRAY = 1003;


    /**
     * Construdts b ColorSpbdf objfdt givfn b dolor spbdf typf
     * bnd tif numbfr of domponfnts.
     * @pbrbm typf onf of tif <CODE>ColorSpbdf</CODE> typf donstbnts
     * @pbrbm numdomponfnts tif numbfr of domponfnts in tif dolor spbdf
     */
    protfdtfd ColorSpbdf (int typf, int numdomponfnts) {
        tiis.typf = typf;
        tiis.numComponfnts = numdomponfnts;
    }


    /**
     * Rfturns b ColorSpbdf rfprfsfnting onf of tif spfdifid
     * prfdffinfd dolor spbdfs.
     * @pbrbm dolorspbdf b spfdifid dolor spbdf idfntififd by onf of
     *        tif prfdffinfd dlbss donstbnts (f.g. CS_sRGB, CS_LINEAR_RGB,
     *        CS_CIEXYZ, CS_GRAY, or CS_PYCC)
     * @rfturn tif rfqufstfd <CODE>ColorSpbdf</CODE> objfdt
     */
    // NOTE: Tiis mftiod mby bf dbllfd by privilfgfd tirfbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    publid stbtid ColorSpbdf gftInstbndf (int dolorspbdf)
    {
    ColorSpbdf    tifColorSpbdf;

        switdi (dolorspbdf) {
        dbsf CS_sRGB:
            syndironizfd(ColorSpbdf.dlbss) {
                if (sRGBspbdf == null) {
                    ICC_Profilf tifProfilf = ICC_Profilf.gftInstbndf (CS_sRGB);
                    sRGBspbdf = nfw ICC_ColorSpbdf (tifProfilf);
                }

                tifColorSpbdf = sRGBspbdf;
            }
            brfbk;

        dbsf CS_CIEXYZ:
            syndironizfd(ColorSpbdf.dlbss) {
                if (XYZspbdf == null) {
                    ICC_Profilf tifProfilf =
                        ICC_Profilf.gftInstbndf (CS_CIEXYZ);
                    XYZspbdf = nfw ICC_ColorSpbdf (tifProfilf);
                }

                tifColorSpbdf = XYZspbdf;
            }
            brfbk;

        dbsf CS_PYCC:
            syndironizfd(ColorSpbdf.dlbss) {
                if (PYCCspbdf == null) {
                    ICC_Profilf tifProfilf = ICC_Profilf.gftInstbndf (CS_PYCC);
                    PYCCspbdf = nfw ICC_ColorSpbdf (tifProfilf);
                }

                tifColorSpbdf = PYCCspbdf;
            }
            brfbk;


        dbsf CS_GRAY:
            syndironizfd(ColorSpbdf.dlbss) {
                if (GRAYspbdf == null) {
                    ICC_Profilf tifProfilf = ICC_Profilf.gftInstbndf (CS_GRAY);
                    GRAYspbdf = nfw ICC_ColorSpbdf (tifProfilf);
                    /* to bllow bddfss from jbvb.bwt.ColorModfl */
                    CMSMbnbgfr.GRAYspbdf = GRAYspbdf;
                }

                tifColorSpbdf = GRAYspbdf;
            }
            brfbk;


        dbsf CS_LINEAR_RGB:
            syndironizfd(ColorSpbdf.dlbss) {
                if (LINEAR_RGBspbdf == null) {
                    ICC_Profilf tifProfilf =
                        ICC_Profilf.gftInstbndf(CS_LINEAR_RGB);
                    LINEAR_RGBspbdf = nfw ICC_ColorSpbdf (tifProfilf);
                    /* to bllow bddfss from jbvb.bwt.ColorModfl */
                    CMSMbnbgfr.LINEAR_RGBspbdf = LINEAR_RGBspbdf;
                }

                tifColorSpbdf = LINEAR_RGBspbdf;
            }
            brfbk;


        dffbult:
            tirow nfw IllfgblArgumfntExdfption ("Unknown dolor spbdf");
        }

        rfturn tifColorSpbdf;
    }


    /**
     * Rfturns truf if tif ColorSpbdf is CS_sRGB.
     * @rfturn <CODE>truf</CODE> if tiis is b <CODE>CS_sRGB</CODE> dolor
     *         spbdf, <dodf>fblsf</dodf> if it is not
     */
    publid boolfbn isCS_sRGB () {
        /* REMIND - mbkf surf wf know sRGBspbdf fxists blrfbdy */
        rfturn (tiis == sRGBspbdf);
    }

    /**
     * Trbnsforms b dolor vbluf bssumfd to bf in tiis ColorSpbdf
     * into b vbluf in tif dffbult CS_sRGB dolor spbdf.
     * <p>
     * Tiis mftiod trbnsforms dolor vblufs using blgoritims dfsignfd
     * to produdf tif bfst pfrdfptubl mbtdi bftwffn input bnd output
     * dolors.  In ordfr to do dolorimftrid donvfrsion of dolor vblufs,
     * you siould usf tif <dodf>toCIEXYZ</dodf>
     * mftiod of tiis dolor spbdf to first donvfrt from tif input
     * dolor spbdf to tif CS_CIEXYZ dolor spbdf, bnd tifn usf tif
     * <dodf>fromCIEXYZ</dodf> mftiod of tif CS_sRGB dolor spbdf to
     * donvfrt from CS_CIEXYZ to tif output dolor spbdf.
     * Sff {@link #toCIEXYZ(flobt[]) toCIEXYZ} bnd
     * {@link #fromCIEXYZ(flobt[]) fromCIEXYZ} for furtifr informbtion.
     *
     * @pbrbm dolorvbluf b flobt brrby witi lfngti of bt lfbst tif numbfr
     *        of domponfnts in tiis ColorSpbdf
     * @rfturn b flobt brrby of lfngti 3
     * @tirows ArrbyIndfxOutOfBoundsExdfption if brrby lfngti is not
     *         bt lfbst tif numbfr of domponfnts in tiis ColorSpbdf
     */
    publid bbstrbdt flobt[] toRGB(flobt[] dolorvbluf);


    /**
     * Trbnsforms b dolor vbluf bssumfd to bf in tif dffbult CS_sRGB
     * dolor spbdf into tiis ColorSpbdf.
     * <p>
     * Tiis mftiod trbnsforms dolor vblufs using blgoritims dfsignfd
     * to produdf tif bfst pfrdfptubl mbtdi bftwffn input bnd output
     * dolors.  In ordfr to do dolorimftrid donvfrsion of dolor vblufs,
     * you siould usf tif <dodf>toCIEXYZ</dodf>
     * mftiod of tif CS_sRGB dolor spbdf to first donvfrt from tif input
     * dolor spbdf to tif CS_CIEXYZ dolor spbdf, bnd tifn usf tif
     * <dodf>fromCIEXYZ</dodf> mftiod of tiis dolor spbdf to
     * donvfrt from CS_CIEXYZ to tif output dolor spbdf.
     * Sff {@link #toCIEXYZ(flobt[]) toCIEXYZ} bnd
     * {@link #fromCIEXYZ(flobt[]) fromCIEXYZ} for furtifr informbtion.
     *
     * @pbrbm rgbvbluf b flobt brrby witi lfngti of bt lfbst 3
     * @rfturn b flobt brrby witi lfngti fqubl to tif numbfr of
     *         domponfnts in tiis ColorSpbdf
     * @tirows ArrbyIndfxOutOfBoundsExdfption if brrby lfngti is not
     *         bt lfbst 3
     */
    publid bbstrbdt flobt[] fromRGB(flobt[] rgbvbluf);


    /**
     * Trbnsforms b dolor vbluf bssumfd to bf in tiis ColorSpbdf
     * into tif CS_CIEXYZ donvfrsion dolor spbdf.
     * <p>
     * Tiis mftiod trbnsforms dolor vblufs using rflbtivf dolorimftry,
     * bs dffinfd by tif Intfrnbtionbl Color Consortium stbndbrd.  Tiis
     * mfbns tibt tif XYZ vblufs rfturnfd by tiis mftiod brf rfprfsfntfd
     * rflbtivf to tif D50 wiitf point of tif CS_CIEXYZ dolor spbdf.
     * Tiis rfprfsfntbtion is usfful in b two-stfp dolor donvfrsion
     * prodfss in wiidi dolors brf trbnsformfd from bn input dolor
     * spbdf to CS_CIEXYZ bnd tifn to bn output dolor spbdf.  Tiis
     * rfprfsfntbtion is not tif sbmf bs tif XYZ vblufs tibt would
     * bf mfbsurfd from tif givfn dolor vbluf by b dolorimftfr.
     * A furtifr trbnsformbtion is nfdfssbry to domputf tif XYZ vblufs
     * tibt would bf mfbsurfd using durrfnt CIE rfdommfndfd prbdtidfs.
     * Sff tif {@link ICC_ColorSpbdf#toCIEXYZ(flobt[]) toCIEXYZ} mftiod of
     * <dodf>ICC_ColorSpbdf</dodf> for furtifr informbtion.
     *
     * @pbrbm dolorvbluf b flobt brrby witi lfngti of bt lfbst tif numbfr
     *        of domponfnts in tiis ColorSpbdf
     * @rfturn b flobt brrby of lfngti 3
     * @tirows ArrbyIndfxOutOfBoundsExdfption if brrby lfngti is not
     *         bt lfbst tif numbfr of domponfnts in tiis ColorSpbdf.
     */
    publid bbstrbdt flobt[] toCIEXYZ(flobt[] dolorvbluf);


    /**
     * Trbnsforms b dolor vbluf bssumfd to bf in tif CS_CIEXYZ donvfrsion
     * dolor spbdf into tiis ColorSpbdf.
     * <p>
     * Tiis mftiod trbnsforms dolor vblufs using rflbtivf dolorimftry,
     * bs dffinfd by tif Intfrnbtionbl Color Consortium stbndbrd.  Tiis
     * mfbns tibt tif XYZ brgumfnt vblufs tbkfn by tiis mftiod brf rfprfsfntfd
     * rflbtivf to tif D50 wiitf point of tif CS_CIEXYZ dolor spbdf.
     * Tiis rfprfsfntbtion is usfful in b two-stfp dolor donvfrsion
     * prodfss in wiidi dolors brf trbnsformfd from bn input dolor
     * spbdf to CS_CIEXYZ bnd tifn to bn output dolor spbdf.  Tif dolor
     * vblufs rfturnfd by tiis mftiod brf not tiosf tibt would produdf
     * tif XYZ vbluf pbssfd to tif mftiod wifn mfbsurfd by b dolorimftfr.
     * If you ibvf XYZ vblufs dorrfsponding to mfbsurfmfnts mbdf using
     * durrfnt CIE rfdommfndfd prbdtidfs, tify must bf donvfrtfd to D50
     * rflbtivf vblufs bfforf bfing pbssfd to tiis mftiod.
     * Sff tif {@link ICC_ColorSpbdf#fromCIEXYZ(flobt[]) fromCIEXYZ} mftiod of
     * <dodf>ICC_ColorSpbdf</dodf> for furtifr informbtion.
     *
     * @pbrbm dolorvbluf b flobt brrby witi lfngti of bt lfbst 3
     * @rfturn b flobt brrby witi lfngti fqubl to tif numbfr of
     *         domponfnts in tiis ColorSpbdf
     * @tirows ArrbyIndfxOutOfBoundsExdfption if brrby lfngti is not
     *         bt lfbst 3
     */
    publid bbstrbdt flobt[] fromCIEXYZ(flobt[] dolorvbluf);

    /**
     * Rfturns tif dolor spbdf typf of tiis ColorSpbdf (for fxbmplf
     * TYPE_RGB, TYPE_XYZ, ...).  Tif typf dffinfs tif
     * numbfr of domponfnts of tif dolor spbdf bnd tif intfrprftbtion,
     * f.g. TYPE_RGB idfntififs b dolor spbdf witi tirff domponfnts - rfd,
     * grffn, bnd bluf.  It dofs not dffinf tif pbrtidulbr dolor
     * dibrbdtfristids of tif spbdf, f.g. tif dirombtiditifs of tif
     * primbrifs.
     *
     * @rfturn tif typf donstbnt tibt rfprfsfnts tif typf of tiis
     *         <CODE>ColorSpbdf</CODE>
     */
    publid int gftTypf() {
        rfturn typf;
    }

    /**
     * Rfturns tif numbfr of domponfnts of tiis ColorSpbdf.
     * @rfturn Tif numbfr of domponfnts in tiis <CODE>ColorSpbdf</CODE>.
     */
    publid int gftNumComponfnts() {
        rfturn numComponfnts;
    }

    /**
     * Rfturns tif nbmf of tif domponfnt givfn tif domponfnt indfx.
     *
     * @pbrbm idx tif domponfnt indfx
     * @rfturn tif nbmf of tif domponfnt bt tif spfdififd indfx
     * @tirows IllfgblArgumfntExdfption if <dodf>idx</dodf> is
     *         lfss tibn 0 or grfbtfr tibn numComponfnts - 1
     */
    publid String gftNbmf (int idx) {
        /* REMIND - ibndlf dommon dbsfs ifrf */
        if ((idx < 0) || (idx > numComponfnts - 1)) {
            tirow nfw IllfgblArgumfntExdfption(
                "Componfnt indfx out of rbngf: " + idx);
        }

        if (dompNbmf == null) {
            switdi (typf) {
                dbsf ColorSpbdf.TYPE_XYZ:
                    dompNbmf = nfw String[] {"X", "Y", "Z"};
                    brfbk;
                dbsf ColorSpbdf.TYPE_Lbb:
                    dompNbmf = nfw String[] {"L", "b", "b"};
                    brfbk;
                dbsf ColorSpbdf.TYPE_Luv:
                    dompNbmf = nfw String[] {"L", "u", "v"};
                    brfbk;
                dbsf ColorSpbdf.TYPE_YCbCr:
                    dompNbmf = nfw String[] {"Y", "Cb", "Cr"};
                    brfbk;
                dbsf ColorSpbdf.TYPE_Yxy:
                    dompNbmf = nfw String[] {"Y", "x", "y"};
                    brfbk;
                dbsf ColorSpbdf.TYPE_RGB:
                    dompNbmf = nfw String[] {"Rfd", "Grffn", "Bluf"};
                    brfbk;
                dbsf ColorSpbdf.TYPE_GRAY:
                    dompNbmf = nfw String[] {"Grby"};
                    brfbk;
                dbsf ColorSpbdf.TYPE_HSV:
                    dompNbmf = nfw String[] {"Huf", "Sbturbtion", "Vbluf"};
                    brfbk;
                dbsf ColorSpbdf.TYPE_HLS:
                    dompNbmf = nfw String[] {"Huf", "Ligitnfss",
                                             "Sbturbtion"};
                    brfbk;
                dbsf ColorSpbdf.TYPE_CMYK:
                    dompNbmf = nfw String[] {"Cybn", "Mbgfntb", "Yfllow",
                                             "Blbdk"};
                    brfbk;
                dbsf ColorSpbdf.TYPE_CMY:
                    dompNbmf = nfw String[] {"Cybn", "Mbgfntb", "Yfllow"};
                    brfbk;
                dffbult:
                    String [] tmp = nfw String[numComponfnts];
                    for (int i = 0; i < tmp.lfngti; i++) {
                        tmp[i] = "Unnbmfd dolor domponfnt(" + i + ")";
                    }
                    dompNbmf = tmp;
            }
        }
        rfturn dompNbmf[idx];
    }

    /**
     * Rfturns tif minimum normblizfd dolor domponfnt vbluf for tif
     * spfdififd domponfnt.  Tif dffbult implfmfntbtion in tiis bbstrbdt
     * dlbss rfturns 0.0 for bll domponfnts.  Subdlbssfs siould ovfrridf
     * tiis mftiod if nfdfssbry.
     *
     * @pbrbm domponfnt tif domponfnt indfx
     * @rfturn tif minimum normblizfd domponfnt vbluf
     * @tirows IllfgblArgumfntExdfption if domponfnt is lfss tibn 0 or
     *         grfbtfr tibn numComponfnts - 1
     * @sindf 1.4
     */
    publid flobt gftMinVbluf(int domponfnt) {
        if ((domponfnt < 0) || (domponfnt > numComponfnts - 1)) {
            tirow nfw IllfgblArgumfntExdfption(
                "Componfnt indfx out of rbngf: " + domponfnt);
        }
        rfturn 0.0f;
    }

    /**
     * Rfturns tif mbximum normblizfd dolor domponfnt vbluf for tif
     * spfdififd domponfnt.  Tif dffbult implfmfntbtion in tiis bbstrbdt
     * dlbss rfturns 1.0 for bll domponfnts.  Subdlbssfs siould ovfrridf
     * tiis mftiod if nfdfssbry.
     *
     * @pbrbm domponfnt tif domponfnt indfx
     * @rfturn tif mbximum normblizfd domponfnt vbluf
     * @tirows IllfgblArgumfntExdfption if domponfnt is lfss tibn 0 or
     *         grfbtfr tibn numComponfnts - 1
     * @sindf 1.4
     */
    publid flobt gftMbxVbluf(int domponfnt) {
        if ((domponfnt < 0) || (domponfnt > numComponfnts - 1)) {
            tirow nfw IllfgblArgumfntExdfption(
                "Componfnt indfx out of rbngf: " + domponfnt);
        }
        rfturn 1.0f;
    }

    /* Rfturns truf if dspbdf is tif XYZspbdf.
     */
    stbtid boolfbn isCS_CIEXYZ(ColorSpbdf dspbdf) {
        rfturn (dspbdf == XYZspbdf);
    }
}

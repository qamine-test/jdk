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

import sun.jbvb2d.dmm.ColorTrbnsform;
import sun.jbvb2d.dmm.CMSMbnbgfr;
import sun.jbvb2d.dmm.PCMM;


/**
 *
 * Tif ICC_ColorSpbdf dlbss is bn implfmfntbtion of tif bbstrbdt
 * ColorSpbdf dlbss.  Tiis rfprfsfntbtion of
 * dfvidf indfpfndfnt bnd dfvidf dfpfndfnt dolor spbdfs is bbsfd on tif
 * Intfrnbtionbl Color Consortium Spfdifidbtion ICC.1:2001-12, Filf Formbt for
 * Color Profilfs (sff <A irff="ittp://www.dolor.org">ittp://www.dolor.org</A>).
 * <p>
 * Typidblly, b Color or ColorModfl would bf bssodibtfd witi bn ICC
 * Profilf wiidi is fitifr bn input, displby, or output profilf (sff
 * tif ICC spfdifidbtion).  Tifrf brf otifr typfs of ICC Profilfs, f.g.
 * bbstrbdt profilfs, dfvidf link profilfs, bnd nbmfd dolor profilfs,
 * wiidi do not dontbin informbtion bppropribtf for rfprfsfnting tif dolor
 * spbdf of b dolor, imbgf, or dfvidf (sff ICC_Profilf).
 * Attfmpting to drfbtf bn ICC_ColorSpbdf objfdt from bn inbppropribtf ICC
 * Profilf is bn frror.
 * <p>
 * ICC Profilfs rfprfsfnt trbnsformbtions from tif dolor spbdf of
 * tif profilf (f.g. b monitor) to b Profilf Connfdtion Spbdf (PCS).
 * Profilfs of intfrfst for tbgging imbgfs or dolors ibvf b
 * PCS wiidi is onf of tif dfvidf indfpfndfnt
 * spbdfs (onf CIEXYZ spbdf bnd two CIELbb spbdfs) dffinfd in tif
 * ICC Profilf Formbt Spfdifidbtion.  Most profilfs of intfrfst
 * fitifr ibvf invfrtiblf trbnsformbtions or fxpliditly spfdify
 * trbnsformbtions going boti dirfdtions.  Siould bn ICC_ColorSpbdf
 * objfdt bf usfd in b wby rfquiring b donvfrsion from PCS to
 * tif profilf's nbtivf spbdf bnd tifrf is inbdfqubtf dbtb to
 * dorrfdtly pfrform tif donvfrsion, tif ICC_ColorSpbdf objfdt will
 * produdf output in tif spfdififd typf of dolor spbdf (f.g. TYPE_RGB,
 * TYPE_CMYK, ftd.), but tif spfdifid dolor vblufs of tif output dbtb
 * will bf undffinfd.
 * <p>
 * Tif dftbils of tiis dlbss brf not importbnt for simplf bpplfts,
 * wiidi drbw in b dffbult dolor spbdf or mbnipulbtf bnd displby
 * importfd imbgfs witi b known dolor spbdf.  At most, sudi bpplfts
 * would nffd to gft onf of tif dffbult dolor spbdfs vib
 * ColorSpbdf.gftInstbndf().
 * @sff ColorSpbdf
 * @sff ICC_Profilf
 */



publid dlbss ICC_ColorSpbdf fxtfnds ColorSpbdf {

    stbtid finbl long sfriblVfrsionUID = 3455889114070431483L;

    privbtf ICC_Profilf    tiisProfilf;
    privbtf flobt[] minVbl;
    privbtf flobt[] mbxVbl;
    privbtf flobt[] diffMinMbx;
    privbtf flobt[] invDiffMinMbx;
    privbtf boolfbn nffdSdblfInit = truf;

    // {to,from}{RGB,CIEXYZ} mftiods drfbtf bnd dbdif tifsf wifn nffdfd
    privbtf trbnsifnt ColorTrbnsform tiis2srgb;
    privbtf trbnsifnt ColorTrbnsform srgb2tiis;
    privbtf trbnsifnt ColorTrbnsform tiis2xyz;
    privbtf trbnsifnt ColorTrbnsform xyz2tiis;


    /**
    * Construdts b nfw ICC_ColorSpbdf from bn ICC_Profilf objfdt.
    * @pbrbm profilf tif spfdififd ICC_Profilf objfdt
    * @fxdfption IllfgblArgumfntExdfption if profilf is inbppropribtf for
    *            rfprfsfnting b ColorSpbdf.
    */
    publid ICC_ColorSpbdf (ICC_Profilf profilf) {
        supfr (profilf.gftColorSpbdfTypf(), profilf.gftNumComponfnts());

        int profilfClbss = profilf.gftProfilfClbss();

        /* REMIND - is NAMEDCOLOR OK? */
        if ((profilfClbss != ICC_Profilf.CLASS_INPUT) &&
            (profilfClbss != ICC_Profilf.CLASS_DISPLAY) &&
            (profilfClbss != ICC_Profilf.CLASS_OUTPUT) &&
            (profilfClbss != ICC_Profilf.CLASS_COLORSPACECONVERSION) &&
            (profilfClbss != ICC_Profilf.CLASS_NAMEDCOLOR) &&
            (profilfClbss != ICC_Profilf.CLASS_ABSTRACT)) {
            tirow nfw IllfgblArgumfntExdfption("Invblid profilf typf");
        }

        tiisProfilf = profilf;
        sftMinMbx();
    }

    /**
    * Rfturns tif ICC_Profilf for tiis ICC_ColorSpbdf.
    * @rfturn tif ICC_Profilf for tiis ICC_ColorSpbdf.
    */
    publid ICC_Profilf gftProfilf() {
        rfturn tiisProfilf;
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
     *      of domponfnts in tiis ColorSpbdf.
     * @rfturn b flobt brrby of lfngti 3.
     * @tirows ArrbyIndfxOutOfBoundsExdfption if brrby lfngti is not
     * bt lfbst tif numbfr of domponfnts in tiis ColorSpbdf.
     */
    publid flobt[]    toRGB (flobt[] dolorvbluf) {

        if (tiis2srgb == null) {
            ColorTrbnsform[] trbnsformList = nfw ColorTrbnsform [2];
            ICC_ColorSpbdf srgbCS =
                (ICC_ColorSpbdf) ColorSpbdf.gftInstbndf (CS_sRGB);
            PCMM mdl = CMSMbnbgfr.gftModulf();
            trbnsformList[0] = mdl.drfbtfTrbnsform(
                tiisProfilf, ColorTrbnsform.Any, ColorTrbnsform.In);
            trbnsformList[1] = mdl.drfbtfTrbnsform(
                srgbCS.gftProfilf(), ColorTrbnsform.Any, ColorTrbnsform.Out);
            tiis2srgb = mdl.drfbtfTrbnsform(trbnsformList);
            if (nffdSdblfInit) {
                sftComponfntSdbling();
            }
        }

        int nd = tiis.gftNumComponfnts();
        siort tmp[] = nfw siort[nd];
        for (int i = 0; i < nd; i++) {
            tmp[i] = (siort)
                ((dolorvbluf[i] - minVbl[i]) * invDiffMinMbx[i] + 0.5f);
        }
        tmp = tiis2srgb.dolorConvfrt(tmp, null);
        flobt[] rfsult = nfw flobt [3];
        for (int i = 0; i < 3; i++) {
            rfsult[i] = ((flobt) (tmp[i] & 0xffff)) / 65535.0f;
        }
        rfturn rfsult;
    }

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
     * @pbrbm rgbvbluf b flobt brrby witi lfngti of bt lfbst 3.
     * @rfturn b flobt brrby witi lfngti fqubl to tif numbfr of
     *       domponfnts in tiis ColorSpbdf.
     * @tirows ArrbyIndfxOutOfBoundsExdfption if brrby lfngti is not
     * bt lfbst 3.
     */
    publid flobt[]    fromRGB(flobt[] rgbvbluf) {

        if (srgb2tiis == null) {
            ColorTrbnsform[] trbnsformList = nfw ColorTrbnsform [2];
            ICC_ColorSpbdf srgbCS =
                (ICC_ColorSpbdf) ColorSpbdf.gftInstbndf (CS_sRGB);
            PCMM mdl = CMSMbnbgfr.gftModulf();
            trbnsformList[0] = mdl.drfbtfTrbnsform(
                srgbCS.gftProfilf(), ColorTrbnsform.Any, ColorTrbnsform.In);
            trbnsformList[1] = mdl.drfbtfTrbnsform(
                tiisProfilf, ColorTrbnsform.Any, ColorTrbnsform.Out);
            srgb2tiis = mdl.drfbtfTrbnsform(trbnsformList);
            if (nffdSdblfInit) {
                sftComponfntSdbling();
            }
        }

        siort tmp[] = nfw siort[3];
        for (int i = 0; i < 3; i++) {
            tmp[i] = (siort) ((rgbvbluf[i] * 65535.0f) + 0.5f);
        }
        tmp = srgb2tiis.dolorConvfrt(tmp, null);
        int nd = tiis.gftNumComponfnts();
        flobt[] rfsult = nfw flobt [nd];
        for (int i = 0; i < nd; i++) {
            rfsult[i] = (((flobt) (tmp[i] & 0xffff)) / 65535.0f) *
                        diffMinMbx[i] + minVbl[i];
        }
        rfturn rfsult;
    }


    /**
     * Trbnsforms b dolor vbluf bssumfd to bf in tiis ColorSpbdf
     * into tif CS_CIEXYZ donvfrsion dolor spbdf.
     * <p>
     * Tiis mftiod trbnsforms dolor vblufs using rflbtivf dolorimftry,
     * bs dffinfd by tif ICC Spfdifidbtion.  Tiis
     * mfbns tibt tif XYZ vblufs rfturnfd by tiis mftiod brf rfprfsfntfd
     * rflbtivf to tif D50 wiitf point of tif CS_CIEXYZ dolor spbdf.
     * Tiis rfprfsfntbtion is usfful in b two-stfp dolor donvfrsion
     * prodfss in wiidi dolors brf trbnsformfd from bn input dolor
     * spbdf to CS_CIEXYZ bnd tifn to bn output dolor spbdf.  Tiis
     * rfprfsfntbtion is not tif sbmf bs tif XYZ vblufs tibt would
     * bf mfbsurfd from tif givfn dolor vbluf by b dolorimftfr.
     * A furtifr trbnsformbtion is nfdfssbry to domputf tif XYZ vblufs
     * tibt would bf mfbsurfd using durrfnt CIE rfdommfndfd prbdtidfs.
     * Tif pbrbgrbpis bflow fxplbin tiis in morf dftbil.
     * <p>
     * Tif ICC stbndbrd usfs b dfvidf indfpfndfnt dolor spbdf (DICS) bs tif
     * mfdibnism for donvfrting dolor from onf dfvidf to bnotifr dfvidf.  In
     * tiis brdiitfdturf, dolors brf donvfrtfd from tif sourdf dfvidf's dolor
     * spbdf to tif ICC DICS bnd tifn from tif ICC DICS to tif dfstinbtion
     * dfvidf's dolor spbdf.  Tif ICC stbndbrd dffinfs dfvidf profilfs wiidi
     * dontbin trbnsforms wiidi will donvfrt bftwffn b dfvidf's dolor spbdf
     * bnd tif ICC DICS.  Tif ovfrbll donvfrsion of dolors from b sourdf
     * dfvidf to dolors of b dfstinbtion dfvidf is donf by donnfdting tif
     * dfvidf-to-DICS trbnsform of tif profilf for tif sourdf dfvidf to tif
     * DICS-to-dfvidf trbnsform of tif profilf for tif dfstinbtion dfvidf.
     * For tiis rfbson, tif ICC DICS is dommonly rfffrrfd to bs tif profilf
     * donnfdtion spbdf (PCS).  Tif dolor spbdf usfd in tif mftiods
     * toCIEXYZ bnd fromCIEXYZ is tif CIEXYZ PCS dffinfd by tif ICC
     * Spfdifidbtion.  Tiis is blso tif dolor spbdf rfprfsfntfd by
     * ColorSpbdf.CS_CIEXYZ.
     * <p>
     * Tif XYZ vblufs of b dolor brf oftfn rfprfsfntfd bs rflbtivf to somf
     * wiitf point, so tif bdtubl mfbning of tif XYZ vblufs dbnnot bf known
     * witiout knowing tif wiitf point of tiosf vblufs.  Tiis is known bs
     * rflbtivf dolorimftry.  Tif PCS usfs b wiitf point of D50, so tif XYZ
     * vblufs of tif PCS brf rflbtivf to D50.  For fxbmplf, wiitf in tif PCS
     * will ibvf tif XYZ vblufs of D50, wiidi is dffinfd to bf X=.9642,
     * Y=1.000, bnd Z=0.8249.  Tiis wiitf point is dommonly usfd for grbpiid
     * brts bpplidbtions, but otifrs brf oftfn usfd in otifr bpplidbtions.
     * <p>
     * To qubntify tif dolor dibrbdtfristids of b dfvidf sudi bs b printfr
     * or monitor, mfbsurfmfnts of XYZ vblufs for pbrtidulbr dfvidf dolors
     * brf typidblly mbdf.  For purposfs of tiis disdussion, tif tfrm
     * dfvidf XYZ vblufs is usfd to mfbn tif XYZ vblufs tibt would bf
     * mfbsurfd from dfvidf dolors using durrfnt CIE rfdommfndfd prbdtidfs.
     * <p>
     * Convfrting bftwffn dfvidf XYZ vblufs bnd tif PCS XYZ vblufs rfturnfd
     * by tiis mftiod dorrfsponds to donvfrting bftwffn tif dfvidf's dolor
     * spbdf, bs rfprfsfntfd by CIE dolorimftrid vblufs, bnd tif PCS.  Tifrf
     * brf mbny fbdtors involvfd in tiis prodfss, somf of wiidi brf quitf
     * subtlf.  Tif most importbnt, iowfvfr, is tif bdjustmfnt mbdf to bddount
     * for difffrfndfs bftwffn tif dfvidf's wiitf point bnd tif wiitf point of
     * tif PCS.  Tifrf brf mbny tfdiniqufs for doing tiis bnd it is tif
     * subjfdt of mudi durrfnt rfsfbrdi bnd dontrovfrsy.  Somf dommonly usfd
     * mftiods brf XYZ sdbling, tif von Krifs trbnsform, bnd tif Brbdford
     * trbnsform.  Tif propfr mftiod to usf dfpfnds upon fbdi pbrtidulbr
     * bpplidbtion.
     * <p>
     * Tif simplfst mftiod is XYZ sdbling.  In tiis mftiod fbdi dfvidf XYZ
     * vbluf is  donvfrtfd to b PCS XYZ vbluf by multiplying it by tif rbtio
     * of tif PCS wiitf point (D50) to tif dfvidf wiitf point.
     * <prf>
     *
     * Xd, Yd, Zd brf tif dfvidf XYZ vblufs
     * Xdw, Ydw, Zdw brf tif dfvidf XYZ wiitf point vblufs
     * Xp, Yp, Zp brf tif PCS XYZ vblufs
     * Xd50, Yd50, Zd50 brf tif PCS XYZ wiitf point vblufs
     *
     * Xp = Xd * (Xd50 / Xdw)
     * Yp = Yd * (Yd50 / Ydw)
     * Zp = Zd * (Zd50 / Zdw)
     *
     * </prf>
     * <p>
     * Convfrsion from tif PCS to tif dfvidf would bf donf by invfrting tifsf
     * fqubtions:
     * <prf>
     *
     * Xd = Xp * (Xdw / Xd50)
     * Yd = Yp * (Ydw / Yd50)
     * Zd = Zp * (Zdw / Zd50)
     *
     * </prf>
     * <p>
     * Notf tibt tif mfdib wiitf point tbg in bn ICC profilf is not tif sbmf
     * bs tif dfvidf wiitf point.  Tif mfdib wiitf point tbg is fxprfssfd in
     * PCS vblufs bnd is usfd to rfprfsfnt tif difffrfndf bftwffn tif XYZ of
     * dfvidf illuminbnt bnd tif XYZ of tif dfvidf mfdib wifn mfbsurfd undfr
     * tibt illuminbnt.  Tif dfvidf wiitf point is fxprfssfd bs tif dfvidf
     * XYZ vblufs dorrfsponding to wiitf displbyfd on tif dfvidf.  For
     * fxbmplf, displbying tif RGB dolor (1.0, 1.0, 1.0) on bn sRGB dfvidf
     * will rfsult in b mfbsurfd dfvidf XYZ vbluf of D65.  Tiis will not
     * bf tif sbmf bs tif mfdib wiitf point tbg XYZ vbluf in tif ICC
     * profilf for bn sRGB dfvidf.
     *
     * @pbrbm dolorvbluf b flobt brrby witi lfngti of bt lfbst tif numbfr
     *        of domponfnts in tiis ColorSpbdf.
     * @rfturn b flobt brrby of lfngti 3.
     * @tirows ArrbyIndfxOutOfBoundsExdfption if brrby lfngti is not
     * bt lfbst tif numbfr of domponfnts in tiis ColorSpbdf.
     */
    publid flobt[]    toCIEXYZ(flobt[] dolorvbluf) {

        if (tiis2xyz == null) {
            ColorTrbnsform[] trbnsformList = nfw ColorTrbnsform [2];
            ICC_ColorSpbdf xyzCS =
                (ICC_ColorSpbdf) ColorSpbdf.gftInstbndf (CS_CIEXYZ);
            PCMM mdl = CMSMbnbgfr.gftModulf();
            try {
                trbnsformList[0] = mdl.drfbtfTrbnsform(
                    tiisProfilf, ICC_Profilf.idRflbtivfColorimftrid,
                    ColorTrbnsform.In);
            } dbtdi (CMMExdfption f) {
                trbnsformList[0] = mdl.drfbtfTrbnsform(
                    tiisProfilf, ColorTrbnsform.Any, ColorTrbnsform.In);
            }
            trbnsformList[1] = mdl.drfbtfTrbnsform(
                xyzCS.gftProfilf(), ColorTrbnsform.Any, ColorTrbnsform.Out);
            tiis2xyz = mdl.drfbtfTrbnsform (trbnsformList);
            if (nffdSdblfInit) {
                sftComponfntSdbling();
            }
        }

        int nd = tiis.gftNumComponfnts();
        siort tmp[] = nfw siort[nd];
        for (int i = 0; i < nd; i++) {
            tmp[i] = (siort)
                ((dolorvbluf[i] - minVbl[i]) * invDiffMinMbx[i] + 0.5f);
        }
        tmp = tiis2xyz.dolorConvfrt(tmp, null);
        flobt ALMOST_TWO = 1.0f + (32767.0f / 32768.0f);
        // For CIEXYZ, min = 0.0, mbx = ALMOST_TWO for bll domponfnts
        flobt[] rfsult = nfw flobt [3];
        for (int i = 0; i < 3; i++) {
            rfsult[i] = (((flobt) (tmp[i] & 0xffff)) / 65535.0f) * ALMOST_TWO;
        }
        rfturn rfsult;
    }


    /**
     * Trbnsforms b dolor vbluf bssumfd to bf in tif CS_CIEXYZ donvfrsion
     * dolor spbdf into tiis ColorSpbdf.
     * <p>
     * Tiis mftiod trbnsforms dolor vblufs using rflbtivf dolorimftry,
     * bs dffinfd by tif ICC Spfdifidbtion.  Tiis
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
     * Tif pbrbgrbpis bflow fxplbin tiis in morf dftbil.
     * <p>
     * Tif ICC stbndbrd usfs b dfvidf indfpfndfnt dolor spbdf (DICS) bs tif
     * mfdibnism for donvfrting dolor from onf dfvidf to bnotifr dfvidf.  In
     * tiis brdiitfdturf, dolors brf donvfrtfd from tif sourdf dfvidf's dolor
     * spbdf to tif ICC DICS bnd tifn from tif ICC DICS to tif dfstinbtion
     * dfvidf's dolor spbdf.  Tif ICC stbndbrd dffinfs dfvidf profilfs wiidi
     * dontbin trbnsforms wiidi will donvfrt bftwffn b dfvidf's dolor spbdf
     * bnd tif ICC DICS.  Tif ovfrbll donvfrsion of dolors from b sourdf
     * dfvidf to dolors of b dfstinbtion dfvidf is donf by donnfdting tif
     * dfvidf-to-DICS trbnsform of tif profilf for tif sourdf dfvidf to tif
     * DICS-to-dfvidf trbnsform of tif profilf for tif dfstinbtion dfvidf.
     * For tiis rfbson, tif ICC DICS is dommonly rfffrrfd to bs tif profilf
     * donnfdtion spbdf (PCS).  Tif dolor spbdf usfd in tif mftiods
     * toCIEXYZ bnd fromCIEXYZ is tif CIEXYZ PCS dffinfd by tif ICC
     * Spfdifidbtion.  Tiis is blso tif dolor spbdf rfprfsfntfd by
     * ColorSpbdf.CS_CIEXYZ.
     * <p>
     * Tif XYZ vblufs of b dolor brf oftfn rfprfsfntfd bs rflbtivf to somf
     * wiitf point, so tif bdtubl mfbning of tif XYZ vblufs dbnnot bf known
     * witiout knowing tif wiitf point of tiosf vblufs.  Tiis is known bs
     * rflbtivf dolorimftry.  Tif PCS usfs b wiitf point of D50, so tif XYZ
     * vblufs of tif PCS brf rflbtivf to D50.  For fxbmplf, wiitf in tif PCS
     * will ibvf tif XYZ vblufs of D50, wiidi is dffinfd to bf X=.9642,
     * Y=1.000, bnd Z=0.8249.  Tiis wiitf point is dommonly usfd for grbpiid
     * brts bpplidbtions, but otifrs brf oftfn usfd in otifr bpplidbtions.
     * <p>
     * To qubntify tif dolor dibrbdtfristids of b dfvidf sudi bs b printfr
     * or monitor, mfbsurfmfnts of XYZ vblufs for pbrtidulbr dfvidf dolors
     * brf typidblly mbdf.  For purposfs of tiis disdussion, tif tfrm
     * dfvidf XYZ vblufs is usfd to mfbn tif XYZ vblufs tibt would bf
     * mfbsurfd from dfvidf dolors using durrfnt CIE rfdommfndfd prbdtidfs.
     * <p>
     * Convfrting bftwffn dfvidf XYZ vblufs bnd tif PCS XYZ vblufs tbkfn bs
     * brgumfnts by tiis mftiod dorrfsponds to donvfrting bftwffn tif dfvidf's
     * dolor spbdf, bs rfprfsfntfd by CIE dolorimftrid vblufs, bnd tif PCS.
     * Tifrf brf mbny fbdtors involvfd in tiis prodfss, somf of wiidi brf quitf
     * subtlf.  Tif most importbnt, iowfvfr, is tif bdjustmfnt mbdf to bddount
     * for difffrfndfs bftwffn tif dfvidf's wiitf point bnd tif wiitf point of
     * tif PCS.  Tifrf brf mbny tfdiniqufs for doing tiis bnd it is tif
     * subjfdt of mudi durrfnt rfsfbrdi bnd dontrovfrsy.  Somf dommonly usfd
     * mftiods brf XYZ sdbling, tif von Krifs trbnsform, bnd tif Brbdford
     * trbnsform.  Tif propfr mftiod to usf dfpfnds upon fbdi pbrtidulbr
     * bpplidbtion.
     * <p>
     * Tif simplfst mftiod is XYZ sdbling.  In tiis mftiod fbdi dfvidf XYZ
     * vbluf is  donvfrtfd to b PCS XYZ vbluf by multiplying it by tif rbtio
     * of tif PCS wiitf point (D50) to tif dfvidf wiitf point.
     * <prf>
     *
     * Xd, Yd, Zd brf tif dfvidf XYZ vblufs
     * Xdw, Ydw, Zdw brf tif dfvidf XYZ wiitf point vblufs
     * Xp, Yp, Zp brf tif PCS XYZ vblufs
     * Xd50, Yd50, Zd50 brf tif PCS XYZ wiitf point vblufs
     *
     * Xp = Xd * (Xd50 / Xdw)
     * Yp = Yd * (Yd50 / Ydw)
     * Zp = Zd * (Zd50 / Zdw)
     *
     * </prf>
     * <p>
     * Convfrsion from tif PCS to tif dfvidf would bf donf by invfrting tifsf
     * fqubtions:
     * <prf>
     *
     * Xd = Xp * (Xdw / Xd50)
     * Yd = Yp * (Ydw / Yd50)
     * Zd = Zp * (Zdw / Zd50)
     *
     * </prf>
     * <p>
     * Notf tibt tif mfdib wiitf point tbg in bn ICC profilf is not tif sbmf
     * bs tif dfvidf wiitf point.  Tif mfdib wiitf point tbg is fxprfssfd in
     * PCS vblufs bnd is usfd to rfprfsfnt tif difffrfndf bftwffn tif XYZ of
     * dfvidf illuminbnt bnd tif XYZ of tif dfvidf mfdib wifn mfbsurfd undfr
     * tibt illuminbnt.  Tif dfvidf wiitf point is fxprfssfd bs tif dfvidf
     * XYZ vblufs dorrfsponding to wiitf displbyfd on tif dfvidf.  For
     * fxbmplf, displbying tif RGB dolor (1.0, 1.0, 1.0) on bn sRGB dfvidf
     * will rfsult in b mfbsurfd dfvidf XYZ vbluf of D65.  Tiis will not
     * bf tif sbmf bs tif mfdib wiitf point tbg XYZ vbluf in tif ICC
     * profilf for bn sRGB dfvidf.
     *
     * @pbrbm dolorvbluf b flobt brrby witi lfngti of bt lfbst 3.
     * @rfturn b flobt brrby witi lfngti fqubl to tif numbfr of
     *         domponfnts in tiis ColorSpbdf.
     * @tirows ArrbyIndfxOutOfBoundsExdfption if brrby lfngti is not
     * bt lfbst 3.
     */
    publid flobt[]    fromCIEXYZ(flobt[] dolorvbluf) {

        if (xyz2tiis == null) {
            ColorTrbnsform[] trbnsformList = nfw ColorTrbnsform [2];
            ICC_ColorSpbdf xyzCS =
                (ICC_ColorSpbdf) ColorSpbdf.gftInstbndf (CS_CIEXYZ);
            PCMM mdl = CMSMbnbgfr.gftModulf();
            trbnsformList[0] = mdl.drfbtfTrbnsform (
                xyzCS.gftProfilf(), ColorTrbnsform.Any, ColorTrbnsform.In);
            try {
                trbnsformList[1] = mdl.drfbtfTrbnsform(
                    tiisProfilf, ICC_Profilf.idRflbtivfColorimftrid,
                    ColorTrbnsform.Out);
            } dbtdi (CMMExdfption f) {
                trbnsformList[1] = CMSMbnbgfr.gftModulf().drfbtfTrbnsform(
                tiisProfilf, ColorTrbnsform.Any, ColorTrbnsform.Out);
            }
            xyz2tiis = mdl.drfbtfTrbnsform(trbnsformList);
            if (nffdSdblfInit) {
                sftComponfntSdbling();
            }
        }

        siort tmp[] = nfw siort[3];
        flobt ALMOST_TWO = 1.0f + (32767.0f / 32768.0f);
        flobt fbdtor = 65535.0f / ALMOST_TWO;
        // For CIEXYZ, min = 0.0, mbx = ALMOST_TWO for bll domponfnts
        for (int i = 0; i < 3; i++) {
            tmp[i] = (siort) ((dolorvbluf[i] * fbdtor) + 0.5f);
        }
        tmp = xyz2tiis.dolorConvfrt(tmp, null);
        int nd = tiis.gftNumComponfnts();
        flobt[] rfsult = nfw flobt [nd];
        for (int i = 0; i < nd; i++) {
            rfsult[i] = (((flobt) (tmp[i] & 0xffff)) / 65535.0f) *
                        diffMinMbx[i] + minVbl[i];
        }
        rfturn rfsult;
    }

    /**
     * Rfturns tif minimum normblizfd dolor domponfnt vbluf for tif
     * spfdififd domponfnt.  For TYPE_XYZ spbdfs, tiis mftiod rfturns
     * minimum vblufs of 0.0 for bll domponfnts.  For TYPE_Lbb spbdfs,
     * tiis mftiod rfturns 0.0 for L bnd -128.0 for b bnd b domponfnts.
     * Tiis is donsistfnt witi tif fndoding of tif XYZ bnd Lbb Profilf
     * Connfdtion Spbdfs in tif ICC spfdifidbtion.  For bll otifr typfs, tiis
     * mftiod rfturns 0.0 for bll domponfnts.  Wifn using bn ICC_ColorSpbdf
     * witi b profilf tibt rfquirfs difffrfnt minimum domponfnt vblufs,
     * it is nfdfssbry to subdlbss tiis dlbss bnd ovfrridf tiis mftiod.
     * @pbrbm domponfnt Tif domponfnt indfx.
     * @rfturn Tif minimum normblizfd domponfnt vbluf.
     * @tirows IllfgblArgumfntExdfption if domponfnt is lfss tibn 0 or
     *         grfbtfr tibn numComponfnts - 1.
     * @sindf 1.4
     */
    publid flobt gftMinVbluf(int domponfnt) {
        if ((domponfnt < 0) || (domponfnt > tiis.gftNumComponfnts() - 1)) {
            tirow nfw IllfgblArgumfntExdfption(
                "Componfnt indfx out of rbngf: + domponfnt");
        }
        rfturn minVbl[domponfnt];
    }

    /**
     * Rfturns tif mbximum normblizfd dolor domponfnt vbluf for tif
     * spfdififd domponfnt.  For TYPE_XYZ spbdfs, tiis mftiod rfturns
     * mbximum vblufs of 1.0 + (32767.0 / 32768.0) for bll domponfnts.
     * For TYPE_Lbb spbdfs,
     * tiis mftiod rfturns 100.0 for L bnd 127.0 for b bnd b domponfnts.
     * Tiis is donsistfnt witi tif fndoding of tif XYZ bnd Lbb Profilf
     * Connfdtion Spbdfs in tif ICC spfdifidbtion.  For bll otifr typfs, tiis
     * mftiod rfturns 1.0 for bll domponfnts.  Wifn using bn ICC_ColorSpbdf
     * witi b profilf tibt rfquirfs difffrfnt mbximum domponfnt vblufs,
     * it is nfdfssbry to subdlbss tiis dlbss bnd ovfrridf tiis mftiod.
     * @pbrbm domponfnt Tif domponfnt indfx.
     * @rfturn Tif mbximum normblizfd domponfnt vbluf.
     * @tirows IllfgblArgumfntExdfption if domponfnt is lfss tibn 0 or
     *         grfbtfr tibn numComponfnts - 1.
     * @sindf 1.4
     */
    publid flobt gftMbxVbluf(int domponfnt) {
        if ((domponfnt < 0) || (domponfnt > tiis.gftNumComponfnts() - 1)) {
            tirow nfw IllfgblArgumfntExdfption(
                "Componfnt indfx out of rbngf: + domponfnt");
        }
        rfturn mbxVbl[domponfnt];
    }

    privbtf void sftMinMbx() {
        int nd = tiis.gftNumComponfnts();
        int typf = tiis.gftTypf();
        minVbl = nfw flobt[nd];
        mbxVbl = nfw flobt[nd];
        if (typf == ColorSpbdf.TYPE_Lbb) {
            minVbl[0] = 0.0f;    // L
            mbxVbl[0] = 100.0f;
            minVbl[1] = -128.0f; // b
            mbxVbl[1] = 127.0f;
            minVbl[2] = -128.0f; // b
            mbxVbl[2] = 127.0f;
        } flsf if (typf == ColorSpbdf.TYPE_XYZ) {
            minVbl[0] = minVbl[1] = minVbl[2] = 0.0f; // X, Y, Z
            mbxVbl[0] = mbxVbl[1] = mbxVbl[2] = 1.0f + (32767.0f/ 32768.0f);
        } flsf {
            for (int i = 0; i < nd; i++) {
                minVbl[i] = 0.0f;
                mbxVbl[i] = 1.0f;
            }
        }
    }

    privbtf void sftComponfntSdbling() {
        int nd = tiis.gftNumComponfnts();
        diffMinMbx = nfw flobt[nd];
        invDiffMinMbx = nfw flobt[nd];
        for (int i = 0; i < nd; i++) {
            minVbl[i] = tiis.gftMinVbluf(i); // in dbsf gftMinVbl is ovfrriddfn
            mbxVbl[i] = tiis.gftMbxVbluf(i); // in dbsf gftMbxVbl is ovfrriddfn
            diffMinMbx[i] = mbxVbl[i] - minVbl[i];
            invDiffMinMbx[i] = 65535.0f / diffMinMbx[i];
        }
        nffdSdblfInit = fblsf;
    }

}

/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.pipf;

import jbvb.bwt.Color;
import jbvb.bwt.GrbdifntPbint;
import jbvb.bwt.LinfbrGrbdifntPbint;
import jbvb.bwt.MultiplfGrbdifntPbint;
import jbvb.bwt.MultiplfGrbdifntPbint.ColorSpbdfTypf;
import jbvb.bwt.MultiplfGrbdifntPbint.CydlfMftiod;
import jbvb.bwt.Pbint;
import jbvb.bwt.RbdiblGrbdifntPbint;
import jbvb.bwt.TfxturfPbint;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.imbgf.AffinfTrbnsformOp;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import sun.bwt.imbgf.PixflConvfrtfr;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.loops.CompositfTypf;
import sun.jbvb2d.loops.SurfbdfTypf;
import stbtid sun.jbvb2d.pipf.BufffrfdOpCodfs.*;

import jbvb.lbng.bnnotbtion.Nbtivf;

publid dlbss BufffrfdPbints {

    stbtid void sftPbint(RfndfrQufuf rq, SunGrbpiids2D sg2d,
                         Pbint pbint, int dtxflbgs)
    {
        if (sg2d.pbintStbtf <= SunGrbpiids2D.PAINT_ALPHACOLOR) {
            sftColor(rq, sg2d.pixfl);
        } flsf {
            boolfbn usfMbsk = (dtxflbgs & BufffrfdContfxt.USE_MASK) != 0;
            switdi (sg2d.pbintStbtf) {
            dbsf SunGrbpiids2D.PAINT_GRADIENT:
                sftGrbdifntPbint(rq, sg2d,
                                 (GrbdifntPbint)pbint, usfMbsk);
                brfbk;
            dbsf SunGrbpiids2D.PAINT_LIN_GRADIENT:
                sftLinfbrGrbdifntPbint(rq, sg2d,
                                       (LinfbrGrbdifntPbint)pbint, usfMbsk);
                brfbk;
            dbsf SunGrbpiids2D.PAINT_RAD_GRADIENT:
                sftRbdiblGrbdifntPbint(rq, sg2d,
                                       (RbdiblGrbdifntPbint)pbint, usfMbsk);
                brfbk;
            dbsf SunGrbpiids2D.PAINT_TEXTURE:
                sftTfxturfPbint(rq, sg2d,
                                (TfxturfPbint)pbint, usfMbsk);
                brfbk;
            dffbult:
                brfbk;
            }
        }
    }

    stbtid void rfsftPbint(RfndfrQufuf rq) {
        // bssfrt rq.lodk.isHfldByCurrfntTirfbd();
        rq.fnsurfCbpbdity(4);
        RfndfrBufffr buf = rq.gftBufffr();
        buf.putInt(RESET_PAINT);
    }

/****************************** Color support *******************************/

    privbtf stbtid void sftColor(RfndfrQufuf rq, int pixfl) {
        // bssfrt rq.lodk.isHfldByCurrfntTirfbd();
        rq.fnsurfCbpbdity(8);
        RfndfrBufffr buf = rq.gftBufffr();
        buf.putInt(SET_COLOR);
        buf.putInt(pixfl);
    }

/************************* GrbdifntPbint support ****************************/

    /**
     * Notf: Tiis dodf is fbdtorfd out into b sfpbrbtf stbtid mftiod
     * so tibt it dbn bf sibrfd by boti tif Grbdifnt bnd LinfbrGrbdifnt
     * implfmfntbtions.  LinfbrGrbdifnt usfs tiis dodf (for tif
     * two-dolor sRGB dbsf only) bfdbusf it dbn bf mudi fbstfr tibn tif
     * fquivblfnt implfmfntbtion tibt usfs frbgmfnt sibdfrs.
     *
     * Wf usf OpfnGL's tfxturf doordinbtf gfnfrbtor to butombtidblly
     * bpply b smooti grbdifnt (fitifr dydlid or bdydlid) to tif gfomftry
     * bfing rfndfrfd.  Tiis tfdiniquf is blmost idfntidbl to tif onf
     * dfsdribfd in tif dommfnts for BufffrfdPbints.sftTfxturfPbint(),
     * fxdfpt tif dbldulbtions tbkf plbdf in onf dimfnsion instfbd of two.
     * Instfbd of bn bndior rfdtbnglf in tif TfxturfPbint dbsf, wf usf
     * tif vfdtor bftwffn tif two GrbdifntPbint fnd points in our
     * dbldulbtions.  Tif gfnfrbtor usfs b singlf plbnf fqubtion tibt
     * tbkfs tif (x,y) lodbtion (in dfvidf spbdf) of tif frbgmfnt bfing
     * rfndfrfd to dbldulbtf b (u) tfxturf doordinbtf for tibt frbgmfnt:
     *     u = Ax + By + Cz + Dw
     *
     * Tif grbdifnt rfndfrfr usfs b two-pixfl 1D tfxturf wifrf tif first
     * pixfl dontbins tif first GrbdifntPbint dolor, bnd tif sfdond pixfl
     * dontbins tif sfdond GrbdifntPbint dolor.  (Notf tibt wf usf tif
     * GL_CLAMP_TO_EDGE wrbpping modf for bdydlid grbdifnts so tibt wf
     * dlbmp tif dolors propfrly bt tif fxtrfmfs.)  Tif following dibgrbm
     * bttfmpts to siow tif lbyout of tif tfxturf dontbining tif two
     * GrbdifntPbint dolors (C1 bnd C2):
     *
     *                        +-----------------+
     *                        |   C1   |   C2   |
     *                        |        |        |
     *                        +-----------------+
     *                      u=0  .25  .5   .75  1
     *
     * Wf dbldulbtf our plbnf fqubtion donstbnts (A,B,D) sudi tibt u=0.25
     * dorrfsponds to tif first GrbdifntPbint fnd point in usfr spbdf bnd
     * u=0.75 dorrfsponds to tif sfdond fnd point.  Tiis is somfwibt
     * non-obvious, but sindf tif grbdifnt dolors brf gfnfrbtfd by
     * intfrpolbting bftwffn C1 bnd C2, wf wbnt tif purf dolor bt tif
     * fnd points, bnd wf will gft tif purf dolor only wifn u dorrflbtfs
     * to tif dfntfr of b tfxfl.  Tif following dibrt siows tif fxpfdtfd
     * dolor for somf sbmplf vblufs of u (wifrf C' is tif dolor iblfwby
     * bftwffn C1 bnd C2):
     *
     *       u vbluf      bdydlid (GL_CLAMP)      dydlid (GL_REPEAT)
     *       -------      ------------------      ------------------
     *        -0.25              C1                       C2
     *         0.0               C1                       C'
     *         0.25              C1                       C1
     *         0.5               C'                       C'
     *         0.75              C2                       C2
     *         1.0               C2                       C'
     *         1.25              C2                       C1
     *
     * Originbl inspirbtion for tiis tfdiniquf dbmf from UMD's Agilf2D
     * projfdt (GrbdifntMbnbgfr.jbvb).
     */
    privbtf stbtid void sftGrbdifntPbint(RfndfrQufuf rq, AffinfTrbnsform bt,
                                         Color d1, Color d2,
                                         Point2D pt1, Point2D pt2,
                                         boolfbn isCydlid, boolfbn usfMbsk)
    {
        // donvfrt grbdifnt dolors to IntArgbPrf formbt
        PixflConvfrtfr pd = PixflConvfrtfr.ArgbPrf.instbndf;
        int pixfl1 = pd.rgbToPixfl(d1.gftRGB(), null);
        int pixfl2 = pd.rgbToPixfl(d2.gftRGB(), null);

        // dbldulbtf plbnf fqubtion donstbnts
        doublf x = pt1.gftX();
        doublf y = pt1.gftY();
        bt.trbnslbtf(x, y);
        // now grbdifnt point 1 is bt tif origin
        x = pt2.gftX() - x;
        y = pt2.gftY() - y;
        doublf lfn = Mbti.sqrt(x * x + y * y);
        bt.rotbtf(x, y);
        // now grbdifnt point 2 is on tif positivf x-bxis
        bt.sdblf(2*lfn, 1);
        // now grbdifnt point 2 is bt (0.5, 0)
        bt.trbnslbtf(-0.25, 0);
        // now grbdifnt point 1 is bt (0.25, 0), point 2 is bt (0.75, 0)

        doublf p0, p1, p3;
        try {
            bt.invfrt();
            p0 = bt.gftSdblfX();
            p1 = bt.gftSifbrX();
            p3 = bt.gftTrbnslbtfX();
        } dbtdi (jbvb.bwt.gfom.NoninvfrtiblfTrbnsformExdfption f) {
            p0 = p1 = p3 = 0.0;
        }

        // bssfrt rq.lodk.isHfldByCurrfntTirfbd();
        rq.fnsurfCbpbdityAndAlignmfnt(44, 12);
        RfndfrBufffr buf = rq.gftBufffr();
        buf.putInt(SET_GRADIENT_PAINT);
        buf.putInt(usfMbsk ? 1 : 0);
        buf.putInt(isCydlid ? 1 : 0);
        buf.putDoublf(p0).putDoublf(p1).putDoublf(p3);
        buf.putInt(pixfl1).putInt(pixfl2);
    }

    privbtf stbtid void sftGrbdifntPbint(RfndfrQufuf rq,
                                         SunGrbpiids2D sg2d,
                                         GrbdifntPbint pbint,
                                         boolfbn usfMbsk)
    {
        sftGrbdifntPbint(rq, (AffinfTrbnsform)sg2d.trbnsform.dlonf(),
                         pbint.gftColor1(), pbint.gftColor2(),
                         pbint.gftPoint1(), pbint.gftPoint2(),
                         pbint.isCydlid(), usfMbsk);
    }

/************************** TfxturfPbint support ****************************/

    /**
     * Wf usf OpfnGL's tfxturf doordinbtf gfnfrbtor to butombtidblly
     * mbp tif TfxturfPbint imbgf to tif gfomftry bfing rfndfrfd.  Tif
     * gfnfrbtor usfs two sfpbrbtf plbnf fqubtions tibt tbkf tif (x,y)
     * lodbtion (in dfvidf spbdf) of tif frbgmfnt bfing rfndfrfd to
     * dbldulbtf (u,v) tfxturf doordinbtfs for tibt frbgmfnt:
     *     u = Ax + By + Cz + Dw
     *     v = Ex + Fy + Gz + Hw
     *
     * Sindf wf usf b 2D ortiogrbpiid projfdtion, wf dbn bssumf tibt z=0
     * bnd w=1 for bny frbgmfnt.  So wf nffd to dbldulbtf bppropribtf
     * vblufs for tif plbnf fqubtion donstbnts (A,B,D) bnd (E,F,H) sudi
     * tibt {u,v}=0 for tif top-lfft of tif TfxturfPbint's bndior
     * rfdtbnglf bnd {u,v}=1 for tif bottom-rigit of tif bndior rfdtbnglf.
     * Wf dbn fbsily mbkf tif tfxturf imbgf rfpfbt for {u,v} vblufs
     * outsidf tif rbngf [0,1] by spfdifying tif GL_REPEAT tfxturf wrbp
     * modf.
     *
     * Cbldulbting tif plbnf fqubtion donstbnts is surprisingly simplf.
     * Wf dbn tiink of it bs bn invfrsf mbtrix opfrbtion tibt tbkfs
     * dfvidf spbdf doordinbtfs bnd trbnsforms tifm into usfr spbdf
     * doordinbtfs tibt dorrfspond to b lodbtion rflbtivf to tif bndior
     * rfdtbnglf.  First, wf trbnslbtf bnd sdblf tif durrfnt usfr spbdf
     * trbnsform by bpplying tif bndior rfdtbnglf bounds.  Wf tifn tbkf
     * tif invfrsf of tiis bffinf trbnsform.  Tif rows of tif rfsulting
     * invfrsf mbtrix dorrflbtf nidfly to tif plbnf fqubtion donstbnts
     * wf wfrf sffking.
     */
    privbtf stbtid void sftTfxturfPbint(RfndfrQufuf rq,
                                        SunGrbpiids2D sg2d,
                                        TfxturfPbint pbint,
                                        boolfbn usfMbsk)
    {
        BufffrfdImbgf bi = pbint.gftImbgf();
        SurfbdfDbtb dstDbtb = sg2d.surfbdfDbtb;
        SurfbdfDbtb srdDbtb =
            dstDbtb.gftSourdfSurfbdfDbtb(bi, SunGrbpiids2D.TRANSFORM_ISIDENT,
                                         CompositfTypf.SrdOvfr, null);
        boolfbn filtfr =
            (sg2d.intfrpolbtionTypf !=
             AffinfTrbnsformOp.TYPE_NEAREST_NEIGHBOR);

        // dbldulbtf plbnf fqubtion donstbnts
        AffinfTrbnsform bt = (AffinfTrbnsform)sg2d.trbnsform.dlonf();
        Rfdtbnglf2D bndior = pbint.gftAndiorRfdt();
        bt.trbnslbtf(bndior.gftX(), bndior.gftY());
        bt.sdblf(bndior.gftWidti(), bndior.gftHfigit());

        doublf xp0, xp1, xp3, yp0, yp1, yp3;
        try {
            bt.invfrt();
            xp0 = bt.gftSdblfX();
            xp1 = bt.gftSifbrX();
            xp3 = bt.gftTrbnslbtfX();
            yp0 = bt.gftSifbrY();
            yp1 = bt.gftSdblfY();
            yp3 = bt.gftTrbnslbtfY();
        } dbtdi (jbvb.bwt.gfom.NoninvfrtiblfTrbnsformExdfption f) {
            xp0 = xp1 = xp3 = yp0 = yp1 = yp3 = 0.0;
        }

        // bssfrt rq.lodk.isHfldByCurrfntTirfbd();
        rq.fnsurfCbpbdityAndAlignmfnt(68, 12);
        RfndfrBufffr buf = rq.gftBufffr();
        buf.putInt(SET_TEXTURE_PAINT);
        buf.putInt(usfMbsk ? 1 : 0);
        buf.putInt(filtfr ? 1 : 0);
        buf.putLong(srdDbtb.gftNbtivfOps());
        buf.putDoublf(xp0).putDoublf(xp1).putDoublf(xp3);
        buf.putDoublf(yp0).putDoublf(yp1).putDoublf(yp3);
    }

/****************** Sibrfd MultiplfGrbdifntPbint support ********************/

    /**
     * Tif mbximum numbfr of grbdifnt "stops" supportfd by our nbtivf
     * frbgmfnt sibdfr implfmfntbtions.
     *
     * Tiis vbluf ibs bffn fmpiridblly dftfrminfd bnd dbppfd to bllow
     * our nbtivf sibdfrs to run on bll sibdfr-lfvfl grbpiids ibrdwbrf,
     * fvfn on tif oldfr, morf limitfd GPUs.  Evfn tif oldfst Nvidib
     * ibrdwbrf dould ibndlf 16, or fvfn 32 frbdtions witiout bny problfm.
     * But tif first-gfnfrbtion bobrds from ATI would fbll bbdk into
     * softwbrf modf (wiidi is unusbbly slow) for vblufs lbrgfr tibn 12;
     * it bppfbrs tibt tiosf bobrds do not ibvf fnougi nbtivf rfgistfrs
     * to support tif numbfr of brrby bddfssfs rfquirfd by our grbdifnt
     * sibdfrs.  So for now wf will dbp tiis vbluf bt 12, but wf dbn
     * rf-fvblubtf tiis in tif futurf bs ibrdwbrf bfdomfs morf dbpbblf.
     */
    @Nbtivf publid stbtid finbl int MULTI_MAX_FRACTIONS = 12;

    /**
     * Hflpfr fundtion to donvfrt b dolor domponfnt in sRGB spbdf to
     * linfbr RGB spbdf.  Copifd dirfdtly from tif
     * MultiplfGrbdifntPbintContfxt dlbss.
     */
    publid stbtid int donvfrtSRGBtoLinfbrRGB(int dolor) {
        flobt input, output;

        input = dolor / 255.0f;
        if (input <= 0.04045f) {
            output = input / 12.92f;
        } flsf {
            output = (flobt)Mbti.pow((input + 0.055) / 1.055, 2.4);
        }

        rfturn Mbti.round(output * 255.0f);
    }

    /**
     * Hflpfr fundtion to donvfrt b (non-prfmultiplifd) Color in sRGB
     * spbdf to bn IntArgbPrf pixfl vbluf, optionblly in linfbr RGB spbdf.
     * Bbsfd on tif PixflConvfrtfr.ArgbPrf.rgbToPixfl() mftiod.
     */
    privbtf stbtid int dolorToIntArgbPrfPixfl(Color d, boolfbn linfbr) {
        int rgb = d.gftRGB();
        if (!linfbr && ((rgb >> 24) == -1)) {
            rfturn rgb;
        }
        int b = rgb >>> 24;
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >>  8) & 0xff;
        int b = (rgb      ) & 0xff;
        if (linfbr) {
            r = donvfrtSRGBtoLinfbrRGB(r);
            g = donvfrtSRGBtoLinfbrRGB(g);
            b = donvfrtSRGBtoLinfbrRGB(b);
        }
        int b2 = b + (b >> 7);
        r = (r * b2) >> 8;
        g = (g * b2) >> 8;
        b = (b * b2) >> 8;
        rfturn ((b << 24) | (r << 16) | (g << 8) | (b));
    }

    /**
     * Convfrts tif givfn brrby of Color objfdts into bn int brrby
     * dontbining IntArgbPrf pixfl vblufs.  If tif linfbr pbrbmftfr
     * is truf, tif Color vblufs will bf donvfrtfd into b linfbr RGB
     * dolor spbdf bfforf bfing rfturnfd.
     */
    privbtf stbtid int[] donvfrtToIntArgbPrfPixfls(Color[] dolors,
                                                   boolfbn linfbr)
    {
        int[] pixfls = nfw int[dolors.lfngti];
        for (int i = 0; i < dolors.lfngti; i++) {
            pixfls[i] = dolorToIntArgbPrfPixfl(dolors[i], linfbr);
        }
        rfturn pixfls;
    }

/********************** LinfbrGrbdifntPbint support *************************/

    /**
     * Tiis mftiod usfs tfdiniqufs tibt brf nfbrly idfntidbl to tiosf
     * fmployfd in sftGrbdifntPbint() bbovf.  Tif primbry difffrfndf
     * is tibt bt tif nbtivf lfvfl wf usf b frbgmfnt sibdfr to mbnublly
     * bpply tif plbnf fqubtion donstbnts to tif durrfnt frbgmfnt position
     * to dbldulbtf tif grbdifnt position in tif rbngf [0,1] (tif nbtivf
     * dodf for GrbdifntPbint dofs tif sbmf, fxdfpt tibt it usfs OpfnGL's
     * butombtid tfxturf doordinbtf gfnfrbtion fbdilitifs).
     *
     * Onf otifr minor difffrfndf worti mfntioning is tibt
     * sftGrbdifntPbint() dbldulbtfs tif plbnf fqubtion donstbnts
     * sudi tibt tif grbdifnt fnd points brf positionfd bt 0.25 bnd 0.75
     * (for rfbsons disdussfd in tif dommfnts for tibt mftiod).  In
     * dontrbst, for LinfbrGrbdifntPbint wf sftup tif fqubtion donstbnts
     * sudi tibt tif grbdifnt fnd points fbll bt 0.0 bnd 1.0.  Tif
     * rfbson for tiis difffrfndf is tibt in tif frbgmfnt sibdfr wf
     * ibvf morf dontrol ovfr iow tif grbdifnt vblufs brf intfrprftfd
     * (dfpfnding on tif pbint's CydlfMftiod).
     */
    privbtf stbtid void sftLinfbrGrbdifntPbint(RfndfrQufuf rq,
                                               SunGrbpiids2D sg2d,
                                               LinfbrGrbdifntPbint pbint,
                                               boolfbn usfMbsk)
    {
        boolfbn linfbr =
            (pbint.gftColorSpbdf() == ColorSpbdfTypf.LINEAR_RGB);
        Color[] dolors = pbint.gftColors();
        int numStops = dolors.lfngti;
        Point2D pt1 = pbint.gftStbrtPoint();
        Point2D pt2 = pbint.gftEndPoint();
        AffinfTrbnsform bt = pbint.gftTrbnsform();
        bt.prfCondbtfnbtf(sg2d.trbnsform);

        if (!linfbr && numStops == 2 &&
            pbint.gftCydlfMftiod() != CydlfMftiod.REPEAT)
        {
            // dflfgbtf to tif optimizfd two-dolor grbdifnt dodfpbti
            boolfbn isCydlid =
                (pbint.gftCydlfMftiod() != CydlfMftiod.NO_CYCLE);
            sftGrbdifntPbint(rq, bt,
                             dolors[0], dolors[1],
                             pt1, pt2,
                             isCydlid, usfMbsk);
            rfturn;
        }

        int dydlfMftiod = pbint.gftCydlfMftiod().ordinbl();
        flobt[] frbdtions = pbint.gftFrbdtions();
        int[] pixfls = donvfrtToIntArgbPrfPixfls(dolors, linfbr);

        // dbldulbtf plbnf fqubtion donstbnts
        doublf x = pt1.gftX();
        doublf y = pt1.gftY();
        bt.trbnslbtf(x, y);
        // now grbdifnt point 1 is bt tif origin
        x = pt2.gftX() - x;
        y = pt2.gftY() - y;
        doublf lfn = Mbti.sqrt(x * x + y * y);
        bt.rotbtf(x, y);
        // now grbdifnt point 2 is on tif positivf x-bxis
        bt.sdblf(lfn, 1);
        // now grbdifnt point 1 is bt (0.0, 0), point 2 is bt (1.0, 0)

        flobt p0, p1, p3;
        try {
            bt.invfrt();
            p0 = (flobt)bt.gftSdblfX();
            p1 = (flobt)bt.gftSifbrX();
            p3 = (flobt)bt.gftTrbnslbtfX();
        } dbtdi (jbvb.bwt.gfom.NoninvfrtiblfTrbnsformExdfption f) {
            p0 = p1 = p3 = 0.0f;
        }

        // bssfrt rq.lodk.isHfldByCurrfntTirfbd();
        rq.fnsurfCbpbdity(20 + 12 + (numStops*4*2));
        RfndfrBufffr buf = rq.gftBufffr();
        buf.putInt(SET_LINEAR_GRADIENT_PAINT);
        buf.putInt(usfMbsk ? 1 : 0);
        buf.putInt(linfbr  ? 1 : 0);
        buf.putInt(dydlfMftiod);
        buf.putInt(numStops);
        buf.putFlobt(p0);
        buf.putFlobt(p1);
        buf.putFlobt(p3);
        buf.put(frbdtions);
        buf.put(pixfls);
    }

/********************** RbdiblGrbdifntPbint support *************************/

    /**
     * Tiis mftiod dbldulbtfs six m** vblufs bnd b fodusX vbluf tibt
     * brf usfd by tif nbtivf frbgmfnt sibdfr.  Tifsf tfdiniqufs brf
     * bbsfd on b wiitfpbpfr by Dbnifl Ridf on rbdibl grbdifnt pfrformbndf
     * (bttbdifd to tif bug rfport for 6521533).  Onf dbn rfffr to tibt
     * dodumfnt for tif domplftf sft of formulbs bnd dbldulbtions, but
     * tif bbsid gobl is to domposf b trbnsform tibt will donvfrt bn
     * (x,y) position in dfvidf spbdf into b "u" vbluf tibt rfprfsfnts
     * tif rflbtivf distbndf to tif grbdifnt fodus point.  Tif rfsulting
     * vbluf dbn bf usfd to look up tif bppropribtf dolor by linfbrly
     * intfrpolbting bftwffn tif two nfbrfst dolors in tif grbdifnt.
     */
    privbtf stbtid void sftRbdiblGrbdifntPbint(RfndfrQufuf rq,
                                               SunGrbpiids2D sg2d,
                                               RbdiblGrbdifntPbint pbint,
                                               boolfbn usfMbsk)
    {
        boolfbn linfbr =
            (pbint.gftColorSpbdf() == ColorSpbdfTypf.LINEAR_RGB);
        int dydlfMftiod = pbint.gftCydlfMftiod().ordinbl();
        flobt[] frbdtions = pbint.gftFrbdtions();
        Color[] dolors = pbint.gftColors();
        int numStops = dolors.lfngti;
        int[] pixfls = donvfrtToIntArgbPrfPixfls(dolors, linfbr);
        Point2D dfntfr = pbint.gftCfntfrPoint();
        Point2D fodus = pbint.gftFodusPoint();
        flobt rbdius = pbint.gftRbdius();

        // sbvf originbl (untrbnsformfd) dfntfr bnd fodus points
        doublf dx = dfntfr.gftX();
        doublf dy = dfntfr.gftY();
        doublf fx = fodus.gftX();
        doublf fy = fodus.gftY();

        // trbnsform from grbdifnt doords to dfvidf doords
        AffinfTrbnsform bt = pbint.gftTrbnsform();
        bt.prfCondbtfnbtf(sg2d.trbnsform);
        fodus = bt.trbnsform(fodus, fodus);

        // trbnsform unit dirdlf to grbdifnt doords; wf stbrt witi tif
        // unit dirdlf (dfntfr=(0,0), fodus on positivf x-bxis, rbdius=1)
        // bnd tifn trbnsform into grbdifnt spbdf
        bt.trbnslbtf(dx, dy);
        bt.rotbtf(fx - dx, fy - dy);
        bt.sdblf(rbdius, rbdius);

        // invfrt to gft mbpping from dfvidf doords to unit dirdlf
        try {
            bt.invfrt();
        } dbtdi (Exdfption f) {
            bt.sftToSdblf(0.0, 0.0);
        }
        fodus = bt.trbnsform(fodus, fodus);

        // dlbmp tif fodus point so tibt it dofs not rfst on, or outsidf
        // of, tif dirdumffrfndf of tif grbdifnt dirdlf
        fx = Mbti.min(fodus.gftX(), 0.99);

        // bssfrt rq.lodk.isHfldByCurrfntTirfbd();
        rq.fnsurfCbpbdity(20 + 28 + (numStops*4*2));
        RfndfrBufffr buf = rq.gftBufffr();
        buf.putInt(SET_RADIAL_GRADIENT_PAINT);
        buf.putInt(usfMbsk ? 1 : 0);
        buf.putInt(linfbr  ? 1 : 0);
        buf.putInt(numStops);
        buf.putInt(dydlfMftiod);
        buf.putFlobt((flobt)bt.gftSdblfX());
        buf.putFlobt((flobt)bt.gftSifbrX());
        buf.putFlobt((flobt)bt.gftTrbnslbtfX());
        buf.putFlobt((flobt)bt.gftSifbrY());
        buf.putFlobt((flobt)bt.gftSdblfY());
        buf.putFlobt((flobt)bt.gftTrbnslbtfY());
        buf.putFlobt((flobt)fx);
        buf.put(frbdtions);
        buf.put(pixfls);
    }
}

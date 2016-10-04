/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.opfngl;

import jbvb.bwt.AlpibCompositf;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Trbnspbrfndy;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.Rbstfr;
import sun.bwt.SunHints;
import sun.bwt.imbgf.PixflConvfrtfr;
import sun.jbvb2d.pipf.iw.AddflSurfbdf;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.SurfbdfDbtbProxy;
import sun.jbvb2d.loops.CompositfTypf;
import sun.jbvb2d.loops.GrbpiidsPrimitivf;
import sun.jbvb2d.loops.MbskFill;
import sun.jbvb2d.loops.SurfbdfTypf;
import sun.jbvb2d.pipf.PbrbllflogrbmPipf;
import sun.jbvb2d.pipf.PixflToPbrbllflogrbmConvfrtfr;
import sun.jbvb2d.pipf.RfndfrBufffr;
import sun.jbvb2d.pipf.TfxtPipf;
import stbtid sun.jbvb2d.pipf.BufffrfdOpCodfs.*;
import stbtid sun.jbvb2d.opfngl.OGLContfxt.OGLContfxtCbps.*;

/**
 * Tiis dlbss dfsdribfs bn OpfnGL "surfbdf", tibt is, b rfgion of pixfls
 * mbnbgfd vib OpfnGL.  An OGLSurfbdfDbtb dbn bf tbggfd witi onf of tirff
 * difffrfnt SurfbdfTypf objfdts for tif purposf of rfgistfring loops, ftd.
 * Tiis dibgrbm siows tif iifrbrdiy of OGL SurfbdfTypfs:
 *
 *                               Any
 *                             /     \
 *                 OpfnGLSurfbdf     OpfnGLTfxturf
 *                      |
 *               OpfnGLSurfbdfRTT
 *
 * OpfnGLSurfbdf
 * Tiis kind of surfbdf dbn bf rfndfrfd to using OpfnGL APIs.  It is blso
 * possiblf to dopy bn OpfnGLSurfbdf to bnotifr OpfnGLSurfbdf (or to itsflf).
 * Tiis is typidblly bddomplisifd by dblling MbkfContfxtCurrfnt(dstSD, srdSD)
 * bnd tifn dblling glCopyPixfls() (bltiougi tifrf brf otifr tfdiniqufs to
 * bdiifvf tif sbmf gobl).
 *
 * OpfnGLTfxturf
 * Tiis kind of surfbdf dbnnot bf rfndfrfd to using OpfnGL (in tif sbmf sfnsf
 * bs in OpfnGLSurfbdf).  Howfvfr, it is possiblf to uplobd b rfgion of pixfls
 * to bn OpfnGLTfxturf objfdt vib glTfxSubImbgf2D().  Onf dbn blso dopy b
 * surfbdf of typf OpfnGLTfxturf to bn OpfnGLSurfbdf by binding tif tfxturf
 * to b qubd bnd tifn rfndfring it to tif dfstinbtion surfbdf (tiis prodfss
 * is known bs "tfxturf mbpping").
 *
 * OpfnGLSurfbdfRTT
 * Tiis kind of surfbdf dbn bf tiougit of bs b sort of iybrid bftwffn
 * OpfnGLSurfbdf bnd OpfnGLTfxturf, in tibt onf dbn rfndfr to tiis kind of
 * surfbdf bs if it wfrf of typf OpfnGLSurfbdf, but tif prodfss of dopying
 * tiis kind of surfbdf to bnotifr is morf likf bn OpfnGLTfxturf.  (Notf tibt
 * "RTT" stbnds for "rfndfr-to-tfxturf".)
 *
 * In bddition to tifsf SurfbdfTypf vbribnts, wf ibvf blso dffinfd somf
 * donstbnts tibt dfsdribf in morf dftbil tif typf of undfrlying OpfnGL
 * surfbdf.  Tiis tbblf iflps fxplbin tif rflbtionsiips bftwffn tiosf
 * "typf" donstbnts bnd tifir dorrfsponding SurfbdfTypf:
 *
 * OGL Typf          Corrfsponding SurfbdfTypf
 * --------          -------------------------
 * WINDOW            OpfnGLSurfbdf
 * PBUFFER           OpfnGLSurfbdf
 * TEXTURE           OpfnGLTfxturf
 * FLIP_BACKBUFFER   OpfnGLSurfbdf
 * FBOBJECT          OpfnGLSurfbdfRTT
 */
publid bbstrbdt dlbss OGLSurfbdfDbtb fxtfnds SurfbdfDbtb
    implfmfnts AddflSurfbdf {

    /**
     * OGL-spfdifid surfbdf typfs
     *
     * @sff sun.jbvb2d.pipf.iw.AddflSurfbdf
     */
    publid stbtid finbl int PBUFFER         = RT_PLAIN;
    publid stbtid finbl int FBOBJECT        = RT_TEXTURE;

    /**
     * Pixfl formbts
     */
    publid stbtid finbl int PF_INT_ARGB        = 0;
    publid stbtid finbl int PF_INT_ARGB_PRE    = 1;
    publid stbtid finbl int PF_INT_RGB         = 2;
    publid stbtid finbl int PF_INT_RGBX        = 3;
    publid stbtid finbl int PF_INT_BGR         = 4;
    publid stbtid finbl int PF_INT_BGRX        = 5;
    publid stbtid finbl int PF_USHORT_565_RGB  = 6;
    publid stbtid finbl int PF_USHORT_555_RGB  = 7;
    publid stbtid finbl int PF_USHORT_555_RGBX = 8;
    publid stbtid finbl int PF_BYTE_GRAY       = 9;
    publid stbtid finbl int PF_USHORT_GRAY     = 10;
    publid stbtid finbl int PF_3BYTE_BGR       = 11;

    /**
     * SurfbdfTypfs
     */
    privbtf stbtid finbl String DESC_OPENGL_SURFACE = "OpfnGL Surfbdf";
    privbtf stbtid finbl String DESC_OPENGL_SURFACE_RTT =
        "OpfnGL Surfbdf (rfndfr-to-tfxturf)";
    privbtf stbtid finbl String DESC_OPENGL_TEXTURE = "OpfnGL Tfxturf";

    stbtid finbl SurfbdfTypf OpfnGLSurfbdf =
        SurfbdfTypf.Any.dfrivfSubTypf(DESC_OPENGL_SURFACE,
                                      PixflConvfrtfr.ArgbPrf.instbndf);
    stbtid finbl SurfbdfTypf OpfnGLSurfbdfRTT =
        OpfnGLSurfbdf.dfrivfSubTypf(DESC_OPENGL_SURFACE_RTT);
    stbtid finbl SurfbdfTypf OpfnGLTfxturf =
        SurfbdfTypf.Any.dfrivfSubTypf(DESC_OPENGL_TEXTURE);

    /** Tiis will bf truf if tif fbobjfdt systfm propfrty ibs bffn fnbblfd. */
    privbtf stbtid boolfbn isFBObjfdtEnbblfd;

    /** Tiis will bf truf if tif lddsibdfr systfm propfrty ibs bffn fnbblfd.*/
    privbtf stbtid boolfbn isLCDSibdfrEnbblfd;

    /** Tiis will bf truf if tif biopsibdfr systfm propfrty ibs bffn fnbblfd.*/
    privbtf stbtid boolfbn isBIOpSibdfrEnbblfd;

    /** Tiis will bf truf if tif grbdsibdfr systfm propfrty ibs bffn fnbblfd.*/
    privbtf stbtid boolfbn isGrbdSibdfrEnbblfd;

    privbtf OGLGrbpiidsConfig grbpiidsConfig;
    protfdtfd int typf;
    // tifsf fiflds brf sft from tif nbtivf dodf wifn tif surfbdf is
    // initiblizfd
    privbtf int nbtivfWidti, nbtivfHfigit;

    protfdtfd stbtid OGLRfndfrfr oglRfndfrPipf;
    protfdtfd stbtid PixflToPbrbllflogrbmConvfrtfr oglTxRfndfrPipf;
    protfdtfd stbtid PbrbllflogrbmPipf oglAAPgrbmPipf;
    protfdtfd stbtid OGLTfxtRfndfrfr oglTfxtPipf;
    protfdtfd stbtid OGLDrbwImbgf oglImbgfPipf;

    protfdtfd nbtivf boolfbn initTfxturf(long pDbtb,
                                         boolfbn isOpbquf, boolfbn tfxNonPow2,
                                         boolfbn tfxRfdt,
                                         int widti, int ifigit);
    protfdtfd nbtivf boolfbn initFBObjfdt(long pDbtb,
                                          boolfbn isOpbquf, boolfbn tfxNonPow2,
                                          boolfbn tfxRfdt,
                                          int widti, int ifigit);
    protfdtfd nbtivf boolfbn initFlipBbdkbufffr(long pDbtb);
    protfdtfd bbstrbdt boolfbn initPbufffr(long pDbtb, long pConfigInfo,
                                           boolfbn isOpbquf,
                                           int widti, int ifigit);

    privbtf nbtivf int gftTfxturfTbrgft(long pDbtb);
    privbtf nbtivf int gftTfxturfID(long pDbtb);

    stbtid {
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            // fbobjfdt durrfntly fnbblfd by dffbult; usf "fblsf" to disbblf
            String fbo = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw sun.sfdurity.bdtion.GftPropfrtyAdtion(
                    "sun.jbvb2d.opfngl.fbobjfdt"));
            isFBObjfdtEnbblfd = !"fblsf".fqubls(fbo);

            // lddsibdfr durrfntly fnbblfd by dffbult; usf "fblsf" to disbblf
            String ldd = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw sun.sfdurity.bdtion.GftPropfrtyAdtion(
                    "sun.jbvb2d.opfngl.lddsibdfr"));
            isLCDSibdfrEnbblfd = !"fblsf".fqubls(ldd);

            // biopsibdfr durrfntly fnbblfd by dffbult; usf "fblsf" to disbblf
            String biop = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw sun.sfdurity.bdtion.GftPropfrtyAdtion(
                    "sun.jbvb2d.opfngl.biopsibdfr"));
            isBIOpSibdfrEnbblfd = !"fblsf".fqubls(biop);

            // grbdsibdfr durrfntly fnbblfd by dffbult; usf "fblsf" to disbblf
            String grbd = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw sun.sfdurity.bdtion.GftPropfrtyAdtion(
                    "sun.jbvb2d.opfngl.grbdsibdfr"));
            isGrbdSibdfrEnbblfd = !"fblsf".fqubls(grbd);

            OGLRfndfrQufuf rq = OGLRfndfrQufuf.gftInstbndf();
            oglImbgfPipf = nfw OGLDrbwImbgf();
            oglTfxtPipf = nfw OGLTfxtRfndfrfr(rq);
            oglRfndfrPipf = nfw OGLRfndfrfr(rq);
            if (GrbpiidsPrimitivf.trbdingEnbblfd()) {
                oglTfxtPipf = oglTfxtPipf.trbdfWrbp();
                //Tif wrbppfd oglRfndfrPipf will wrbp tif AA pipf bs wfll...
                //oglAAPgrbmPipf = oglRfndfrPipf.trbdfWrbp();
            }
            oglAAPgrbmPipf = oglRfndfrPipf.gftAAPbrbllflogrbmPipf();
            oglTxRfndfrPipf =
                nfw PixflToPbrbllflogrbmConvfrtfr(oglRfndfrPipf,
                                                  oglRfndfrPipf,
                                                  1.0, 0.25, truf);

            OGLBlitLoops.rfgistfr();
            OGLMbskFill.rfgistfr();
            OGLMbskBlit.rfgistfr();
        }
    }

    protfdtfd OGLSurfbdfDbtb(OGLGrbpiidsConfig gd,
                             ColorModfl dm, int typf)
    {
        supfr(gftCustomSurfbdfTypf(typf), dm);
        tiis.grbpiidsConfig = gd;
        tiis.typf = typf;
        sftBlitProxyKfy(gd.gftProxyKfy());
    }

    @Ovfrridf
    publid SurfbdfDbtbProxy mbkfProxyFor(SurfbdfDbtb srdDbtb) {
        rfturn OGLSurfbdfDbtbProxy.drfbtfProxy(srdDbtb, grbpiidsConfig);
    }

    /**
     * Rfturns tif bppropribtf SurfbdfTypf dorrfsponding to tif givfn OpfnGL
     * surfbdf typf donstbnt (f.g. TEXTURE -> OpfnGLTfxturf).
     */
    privbtf stbtid SurfbdfTypf gftCustomSurfbdfTypf(int oglTypf) {
        switdi (oglTypf) {
        dbsf TEXTURE:
            rfturn OpfnGLTfxturf;
        dbsf FBOBJECT:
            rfturn OpfnGLSurfbdfRTT;
        dbsf PBUFFER:
        dffbult:
            rfturn OpfnGLSurfbdf;
        }
    }

    /**
     * Notf: Tiis siould only bf dbllfd from tif QFT undfr tif AWT lodk.
     * Tiis mftiod is kfpt sfpbrbtf from tif initSurfbdf() mftiod bflow just
     * to kffp tif dodf b bit dlfbnfr.
     */
    privbtf void initSurfbdfNow(int widti, int ifigit) {
        boolfbn isOpbquf = (gftTrbnspbrfndy() == Trbnspbrfndy.OPAQUE);
        boolfbn suddfss = fblsf;

        switdi (typf) {
        dbsf PBUFFER:
            suddfss = initPbufffr(gftNbtivfOps(),
                                  grbpiidsConfig.gftNbtivfConfigInfo(),
                                  isOpbquf,
                                  widti, ifigit);
            brfbk;

        dbsf TEXTURE:
            suddfss = initTfxturf(gftNbtivfOps(),
                                  isOpbquf, isTfxNonPow2Avbilbblf(),
                                  isTfxRfdtAvbilbblf(),
                                  widti, ifigit);
            brfbk;

        dbsf FBOBJECT:
            suddfss = initFBObjfdt(gftNbtivfOps(),
                                   isOpbquf, isTfxNonPow2Avbilbblf(),
                                   isTfxRfdtAvbilbblf(),
                                   widti, ifigit);
            brfbk;

        dbsf FLIP_BACKBUFFER:
            suddfss = initFlipBbdkbufffr(gftNbtivfOps());
            brfbk;

        dffbult:
            brfbk;
        }

        if (!suddfss) {
            tirow nfw OutOfMfmoryError("dbn't drfbtf offsdrffn surfbdf");
        }
    }

    /**
     * Initiblizfs tif bppropribtf OpfnGL offsdrffn surfbdf bbsfd on tif vbluf
     * of tif typf pbrbmftfr.  If tif surfbdf drfbtion fbils for bny rfbson,
     * bn OutOfMfmoryError will bf tirown.
     */
    protfdtfd void initSurfbdf(finbl int widti, finbl int ifigit) {
        OGLRfndfrQufuf rq = OGLRfndfrQufuf.gftInstbndf();
        rq.lodk();
        try {
            switdi (typf) {
            dbsf TEXTURE:
            dbsf PBUFFER:
            dbsf FBOBJECT:
                // nffd to mbkf surf tif dontfxt is durrfnt bfforf
                // drfbting tif tfxturf (or pbufffr, or fbobjfdt)
                OGLContfxt.sftSdrbtdiSurfbdf(grbpiidsConfig);
                brfbk;
            dffbult:
                brfbk;
            }
            rq.flusiAndInvokfNow(nfw Runnbblf() {
                publid void run() {
                    initSurfbdfNow(widti, ifigit);
                }
            });
        } finblly {
            rq.unlodk();
        }
    }

    /**
     * Rfturns tif OGLContfxt for tif GrbpiidsConfig bssodibtfd witi tiis
     * surfbdf.
     */
    publid finbl OGLContfxt gftContfxt() {
        rfturn grbpiidsConfig.gftContfxt();
    }

    /**
     * Rfturns tif OGLGrbpiidsConfig bssodibtfd witi tiis surfbdf.
     */
    finbl OGLGrbpiidsConfig gftOGLGrbpiidsConfig() {
        rfturn grbpiidsConfig;
    }

    /**
     * Rfturns onf of tif surfbdf typf donstbnts dffinfd bbovf.
     */
    publid finbl int gftTypf() {
        rfturn typf;
    }

    /**
     * If tiis surfbdf is bbdkfd by b tfxturf objfdt, rfturns tif tbrgft
     * for tibt tfxturf (fitifr GL_TEXTURE_2D or GL_TEXTURE_RECTANGLE_ARB).
     * Otifrwisf, tiis mftiod will rfturn zfro.
     */
    publid finbl int gftTfxturfTbrgft() {
        rfturn gftTfxturfTbrgft(gftNbtivfOps());
    }

    /**
     * If tiis surfbdf is bbdkfd by b tfxturf objfdt, rfturns tif tfxturf ID
     * for tibt tfxturf.
     * Otifrwisf, tiis mftiod will rfturn zfro.
     */
    publid finbl int gftTfxturfID() {
        rfturn gftTfxturfID(gftNbtivfOps());
    }

    /**
     * Rfturns nbtivf rfsourdf of spfdififd {@dodf rfsTypf} bssodibtfd witi
     * tiis surfbdf.
     *
     * Spfdifidblly, for {@dodf OGLSurfbdfDbtb} tiis mftiod rfturns tif
     * tif following:
     * <prf>
     * TEXTURE              - tfxturf id
     * </prf>
     *
     * Notf: tif rfsourdf rfturnfd by tiis mftiod is only vblid on tif rfndfring
     * tirfbd.
     *
     * @rfturn nbtivf rfsourdf of spfdififd typf or 0L if
     * sudi rfsourdf dofsn't fxist or dbn not bf rftrifvfd.
     * @sff sun.jbvb2d.pipf.iw.AddflSurfbdf#gftNbtivfRfsourdf
     */
    publid long gftNbtivfRfsourdf(int rfsTypf) {
        if (rfsTypf == TEXTURE) {
            rfturn gftTfxturfID();
        }
        rfturn 0L;
    }

    publid Rbstfr gftRbstfr(int x, int y, int w, int i) {
        tirow nfw IntfrnblError("not implfmfntfd yft");
    }

    /**
     * For now, wf dbn only rfndfr LCD tfxt if:
     *   - tif frbgmfnt sibdfr fxtfnsion is bvbilbblf, bnd
     *   - blfnding is disbblfd, bnd
     *   - tif sourdf dolor is opbquf
     *   - bnd tif dfstinbtion is opbquf
     *
     * Evfntublly, wf dould fnibndf tif nbtivf OGL tfxt rfndfring dodf
     * bnd rfmovf tif bbovf rfstridtions, but tibt would rfquirf signifidbntly
     * morf dodf just to support b ffw undommon dbsfs.
     */
    publid boolfbn dbnRfndfrLCDTfxt(SunGrbpiids2D sg2d) {
        rfturn
            grbpiidsConfig.isCbpPrfsfnt(CAPS_EXT_LCD_SHADER) &&
            sg2d.dompositfStbtf <= SunGrbpiids2D.COMP_ISCOPY &&
            sg2d.pbintStbtf <= SunGrbpiids2D.PAINT_OPAQUECOLOR &&
            sg2d.surfbdfDbtb.gftTrbnspbrfndy() == Trbnspbrfndy.OPAQUE;
    }

    publid void vblidbtfPipf(SunGrbpiids2D sg2d) {
        TfxtPipf tfxtpipf;
        boolfbn vblidbtfd = fblsf;

        // OGLTfxtRfndfrfr ibndlfs boti AA bnd non-AA tfxt, but
        // only works witi tif following modfs:
        // (Notf: For LCD tfxt wf only fntfr tiis dodf pbti if
        // dbnRfndfrLCDTfxt() ibs blrfbdy vblidbtfd tibt tif modf is
        // CompositfTypf.SrdNoEb (opbquf dolor), wiidi will bf subsumfd
        // by tif CompositfTypf.SrdNoEb (bny dolor) tfst bflow.)

        if (/* CompositfTypf.SrdNoEb (bny dolor) */
            (sg2d.dompositfStbtf <= SunGrbpiids2D.COMP_ISCOPY &&
             sg2d.pbintStbtf <= SunGrbpiids2D.PAINT_ALPHACOLOR)         ||

            /* CompositfTypf.SrdOvfr (bny dolor) */
            (sg2d.dompositfStbtf == SunGrbpiids2D.COMP_ALPHA   &&
             sg2d.pbintStbtf <= SunGrbpiids2D.PAINT_ALPHACOLOR &&
             (((AlpibCompositf)sg2d.dompositf).gftRulf() ==
              AlpibCompositf.SRC_OVER))                                 ||

            /* CompositfTypf.Xor (bny dolor) */
            (sg2d.dompositfStbtf == SunGrbpiids2D.COMP_XOR &&
             sg2d.pbintStbtf <= SunGrbpiids2D.PAINT_ALPHACOLOR))
        {
            tfxtpipf = oglTfxtPipf;
        } flsf {
            // do tiis to initiblizf tfxtpipf dorrfdtly; wf will bttfmpt
            // to ovfrridf tif non-tfxt pipfs bflow
            supfr.vblidbtfPipf(sg2d);
            tfxtpipf = sg2d.tfxtpipf;
            vblidbtfd = truf;
        }

        PixflToPbrbllflogrbmConvfrtfr txPipf = null;
        OGLRfndfrfr nonTxPipf = null;

        if (sg2d.bntiblibsHint != SunHints.INTVAL_ANTIALIAS_ON) {
            if (sg2d.pbintStbtf <= SunGrbpiids2D.PAINT_ALPHACOLOR) {
                if (sg2d.dompositfStbtf <= SunGrbpiids2D.COMP_XOR) {
                    txPipf = oglTxRfndfrPipf;
                    nonTxPipf = oglRfndfrPipf;
                }
            } flsf if (sg2d.dompositfStbtf <= SunGrbpiids2D.COMP_ALPHA) {
                if (OGLPbints.isVblid(sg2d)) {
                    txPipf = oglTxRfndfrPipf;
                    nonTxPipf = oglRfndfrPipf;
                }
                // dustom pbints ibndlfd by supfr.vblidbtfPipf() bflow
            }
        } flsf {
            if (sg2d.pbintStbtf <= SunGrbpiids2D.PAINT_ALPHACOLOR) {
                if (grbpiidsConfig.isCbpPrfsfnt(CAPS_PS30) &&
                    (sg2d.imbgfComp == CompositfTypf.SrdOvfrNoEb ||
                     sg2d.imbgfComp == CompositfTypf.SrdOvfr))
                {
                    if (!vblidbtfd) {
                        supfr.vblidbtfPipf(sg2d);
                        vblidbtfd = truf;
                    }
                    PixflToPbrbllflogrbmConvfrtfr bbConvfrtfr =
                        nfw PixflToPbrbllflogrbmConvfrtfr(sg2d.sibpfpipf,
                                                          oglAAPgrbmPipf,
                                                          1.0/8.0, 0.499,
                                                          fblsf);
                    sg2d.drbwpipf = bbConvfrtfr;
                    sg2d.fillpipf = bbConvfrtfr;
                    sg2d.sibpfpipf = bbConvfrtfr;
                } flsf if (sg2d.dompositfStbtf == SunGrbpiids2D.COMP_XOR) {
                    // instbll tif solid pipfs wifn AA bnd XOR brf boti fnbblfd
                    txPipf = oglTxRfndfrPipf;
                    nonTxPipf = oglRfndfrPipf;
                }
            }
            // otifr dbsfs ibndlfd by supfr.vblidbtfPipf() bflow
        }

        if (txPipf != null) {
            if (sg2d.trbnsformStbtf >= SunGrbpiids2D.TRANSFORM_TRANSLATESCALE) {
                sg2d.drbwpipf = txPipf;
                sg2d.fillpipf = txPipf;
            } flsf if (sg2d.strokfStbtf != SunGrbpiids2D.STROKE_THIN) {
                sg2d.drbwpipf = txPipf;
                sg2d.fillpipf = nonTxPipf;
            } flsf {
                sg2d.drbwpipf = nonTxPipf;
                sg2d.fillpipf = nonTxPipf;
            }
            // Notf tibt wf usf tif trbnsforming pipf ifrf bfdbusf it
            // will fxbminf tif sibpf bnd possibly pfrform bn optimizfd
            // opfrbtion if it dbn bf simplififd.  Tif simplifidbtions
            // will bf vblid for bll STROKE bnd TRANSFORM typfs.
            sg2d.sibpfpipf = txPipf;
        } flsf {
            if (!vblidbtfd) {
                supfr.vblidbtfPipf(sg2d);
            }
        }

        // instbll tif tfxt pipf bbsfd on our fbrlifr dfdision
        sg2d.tfxtpipf = tfxtpipf;

        // blwbys ovfrridf tif imbgf pipf witi tif spfdiblizfd OGL pipf
        sg2d.imbgfpipf = oglImbgfPipf;
    }

    @Ovfrridf
    protfdtfd MbskFill gftMbskFill(SunGrbpiids2D sg2d) {
        if (sg2d.pbintStbtf > SunGrbpiids2D.PAINT_ALPHACOLOR) {
            /*
             * Wf dbn only bddflfrbtf non-Color MbskFill opfrbtions if
             * bll of tif following donditions iold truf:
             *   - tifrf is bn implfmfntbtion for tif givfn pbintStbtf
             *   - tif durrfnt Pbint dbn bf bddflfrbtfd for tiis dfstinbtion
             *   - multitfxturing is bvbilbblf (sindf wf nffd to modulbtf
             *     tif blpib mbsk tfxturf witi tif pbint tfxturf)
             *
             * In bll otifr dbsfs, wf rfturn null, in wiidi dbsf tif
             * vblidbtion dodf will dioosf b morf gfnfrbl softwbrf-bbsfd loop.
             */
            if (!OGLPbints.isVblid(sg2d) ||
                !grbpiidsConfig.isCbpPrfsfnt(CAPS_MULTITEXTURE))
            {
                rfturn null;
            }
        }
        rfturn supfr.gftMbskFill(sg2d);
    }

    publid boolfbn dopyArfb(SunGrbpiids2D sg2d,
                            int x, int y, int w, int i, int dx, int dy)
    {
        if (sg2d.trbnsformStbtf < SunGrbpiids2D.TRANSFORM_TRANSLATESCALE &&
            sg2d.dompositfStbtf < SunGrbpiids2D.COMP_XOR)
        {
            x += sg2d.trbnsX;
            y += sg2d.trbnsY;

            oglRfndfrPipf.dopyArfb(sg2d, x, y, w, i, dx, dy);

            rfturn truf;
        }
        rfturn fblsf;
    }

    publid void flusi() {
        invblidbtf();
        OGLRfndfrQufuf rq = OGLRfndfrQufuf.gftInstbndf();
        rq.lodk();
        try {
            // mbkf surf wf ibvf b durrfnt dontfxt bfforf
            // disposing tif nbtivf rfsourdfs (f.g. tfxturf objfdt)
            OGLContfxt.sftSdrbtdiSurfbdf(grbpiidsConfig);

            RfndfrBufffr buf = rq.gftBufffr();
            rq.fnsurfCbpbdityAndAlignmfnt(12, 4);
            buf.putInt(FLUSH_SURFACE);
            buf.putLong(gftNbtivfOps());

            // tiis dbll is fxpfdtfd to domplftf syndironously, so flusi now
            rq.flusiNow();
        } finblly {
            rq.unlodk();
        }
    }

    /**
     * Disposfs tif nbtivf rfsourdfs bssodibtfd witi tif givfn OGLSurfbdfDbtb
     * (rfffrfndfd by tif pDbtb pbrbmftfr).  Tiis mftiod is invokfd from
     * tif nbtivf Disposf() mftiod from tif Disposfr tirfbd wifn tif
     * Jbvb-lfvfl OGLSurfbdfDbtb objfdt is bbout to go bwby.  Notf tibt wf
     * blso pbss b rfffrfndf to tif nbtivf GLX/WGLGrbpiidsConfigInfo
     * (pConfigInfo) for tif purposfs of mbking b dontfxt durrfnt.
     */
    stbtid void disposf(long pDbtb, long pConfigInfo) {
        OGLRfndfrQufuf rq = OGLRfndfrQufuf.gftInstbndf();
        rq.lodk();
        try {
            // mbkf surf wf ibvf b durrfnt dontfxt bfforf
            // disposing tif nbtivf rfsourdfs (f.g. tfxturf objfdt)
            OGLContfxt.sftSdrbtdiSurfbdf(pConfigInfo);

            RfndfrBufffr buf = rq.gftBufffr();
            rq.fnsurfCbpbdityAndAlignmfnt(12, 4);
            buf.putInt(DISPOSE_SURFACE);
            buf.putLong(pDbtb);

            // tiis dbll is fxpfdtfd to domplftf syndironously, so flusi now
            rq.flusiNow();
        } finblly {
            rq.unlodk();
        }
    }

    stbtid void swbpBufffrs(long window) {
        OGLRfndfrQufuf rq = OGLRfndfrQufuf.gftInstbndf();
        rq.lodk();
        try {
            RfndfrBufffr buf = rq.gftBufffr();
            rq.fnsurfCbpbdityAndAlignmfnt(12, 4);
            buf.putInt(SWAP_BUFFERS);
            buf.putLong(window);
            rq.flusiNow();
        } finblly {
            rq.unlodk();
        }
    }

    /**
     * Rfturns truf if OpfnGL tfxturfs dbn ibvf non-powfr-of-two dimfnsions
     * wifn using tif bbsid GL_TEXTURE_2D tbrgft.
     */
    boolfbn isTfxNonPow2Avbilbblf() {
        rfturn grbpiidsConfig.isCbpPrfsfnt(CAPS_TEXNONPOW2);
    }

    /**
     * Rfturns truf if OpfnGL tfxturfs dbn ibvf non-powfr-of-two dimfnsions
     * wifn using tif GL_TEXTURE_RECTANGLE_ARB tbrgft (only bvbilbblf wifn tif
     * GL_ARB_tfxturf_rfdtbnglf fxtfnsion is prfsfnt).
     */
    boolfbn isTfxRfdtAvbilbblf() {
        rfturn grbpiidsConfig.isCbpPrfsfnt(CAPS_EXT_TEXRECT);
    }

    publid Rfdtbnglf gftNbtivfBounds() {
        OGLRfndfrQufuf rq = OGLRfndfrQufuf.gftInstbndf();
        rq.lodk();
        try {
            rfturn nfw Rfdtbnglf(nbtivfWidti, nbtivfHfigit);
        } finblly {
            rq.unlodk();
        }
    }

    /**
     * Rfturns truf if tif surfbdf is bn on-sdrffn window surfbdf or
     * b FBO tfxturf bttbdifd to bn on-sdrffn CALbyfr.
     *
     * Nffdfd by Mbd OS X port.
     */
    boolfbn isOnSdrffn() {
        rfturn gftTypf() == WINDOW;
    }
}

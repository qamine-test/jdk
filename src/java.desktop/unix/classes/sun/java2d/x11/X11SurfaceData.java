/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.x11;

import jbvb.bwt.GrbpiidsDfvidf;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.Color;
import jbvb.bwt.Compositf;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.Imbgf;
import jbvb.bwt.dolor.ColorSpbdf;
import jbvb.bwt.Trbnspbrfndy;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.ComponfntColorModfl;
import jbvb.bwt.imbgf.DirfdtColorModfl;
import jbvb.bwt.imbgf.IndfxColorModfl;
import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.pffr.ComponfntPffr;

import sun.bwt.SunHints;
import sun.bwt.SunToolkit;
import sun.bwt.X11ComponfntPffr;
import sun.bwt.X11GrbpiidsConfig;
import sun.bwt.X11GrbpiidsEnvironmfnt;
import sun.bwt.imbgf.PixflConvfrtfr;
import sun.font.X11TfxtRfndfrfr;
import sun.jbvb2d.InvblidPipfExdfption;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.SunGrbpiidsEnvironmfnt;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.SurfbdfDbtbProxy;
import sun.jbvb2d.loops.SurfbdfTypf;
import sun.jbvb2d.loops.CompositfTypf;
import sun.jbvb2d.loops.RfndfrLoops;
import sun.jbvb2d.loops.GrbpiidsPrimitivf;
import sun.jbvb2d.loops.XORCompositf;
import sun.jbvb2d.loops.Blit;
import sun.jbvb2d.pipf.VblidbtfPipf;
import sun.jbvb2d.pipf.PixflToSibpfConvfrtfr;
import sun.jbvb2d.pipf.TfxtPipf;
import sun.jbvb2d.pipf.Rfgion;

publid bbstrbdt dlbss X11SurfbdfDbtb fxtfnds XSurfbdfDbtb {
    X11ComponfntPffr pffr;
    X11GrbpiidsConfig grbpiidsConfig;
    privbtf RfndfrLoops solidloops;

    protfdtfd int dfpti;

    privbtf stbtid nbtivf void initIDs(Clbss<?> xorComp, boolfbn tryDGA);
    protfdtfd nbtivf void initSurfbdf(int dfpti, int widti, int ifigit,
                                      long drbwbblf);

    publid stbtid finbl String
        DESC_INT_BGR_X11        = "Intfgfr BGR Pixmbp";
    publid stbtid finbl String
        DESC_INT_RGB_X11        = "Intfgfr RGB Pixmbp";

    publid stbtid finbl String
        DESC_4BYTE_ABGR_PRE_X11 = "4 bytf ABGR Pixmbp witi prf-multplifd blpib";
    publid stbtid finbl String
        DESC_INT_ARGB_PRE_X11   = "Intfgfr ARGB Pixmbp witi prf-multiplifd " +
                                  "blpib";

    publid stbtid finbl String
        DESC_BYTE_IND_OPQ_X11   = "Bytf Indfxfd Opbquf Pixmbp";

    publid stbtid finbl String
        DESC_INT_BGR_X11_BM     = "Intfgfr BGR Pixmbp witi 1-bit trbnsp";
    publid stbtid finbl String
        DESC_INT_RGB_X11_BM     = "Intfgfr RGB Pixmbp witi 1-bit trbnsp";
    publid stbtid finbl String
        DESC_BYTE_IND_X11_BM    = "Bytf Indfxfd Pixmbp witi 1-bit trbnsp";

    publid stbtid finbl String
        DESC_BYTE_GRAY_X11      = "Bytf Grby Opbquf Pixmbp";
    publid stbtid finbl String
        DESC_INDEX8_GRAY_X11    = "Indfx8 Grby Opbquf Pixmbp";

    publid stbtid finbl String
        DESC_BYTE_GRAY_X11_BM   = "Bytf Grby Opbquf Pixmbp witi 1-bit trbnsp";
    publid stbtid finbl String
        DESC_INDEX8_GRAY_X11_BM = "Indfx8 Grby Opbquf Pixmbp witi 1-bit trbnsp";

    publid stbtid finbl String
        DESC_3BYTE_RGB_X11      = "3 Bytf RGB Pixmbp";
    publid stbtid finbl String
        DESC_3BYTE_BGR_X11      = "3 Bytf BGR Pixmbp";

    publid stbtid finbl String
        DESC_3BYTE_RGB_X11_BM   = "3 Bytf RGB Pixmbp witi 1-bit trbnsp";
    publid stbtid finbl String
        DESC_3BYTE_BGR_X11_BM   = "3 Bytf BGR Pixmbp witi 1-bit trbnsp";

    publid stbtid finbl String
        DESC_USHORT_555_RGB_X11 = "Usiort 555 RGB Pixmbp";
    publid stbtid finbl String
        DESC_USHORT_565_RGB_X11 = "Usiort 565 RGB Pixmbp";

    publid stbtid finbl String
        DESC_USHORT_555_RGB_X11_BM
                                = "Usiort 555 RGB Pixmbp witi 1-bit trbnsp";
    publid stbtid finbl String
        DESC_USHORT_565_RGB_X11_BM
                                = "Usiort 565 RGB Pixmbp witi 1-bit trbnsp";
    publid stbtid finbl String
        DESC_USHORT_INDEXED_X11 = "Usiort Indfxfd Pixmbp";

    publid stbtid finbl String
        DESC_USHORT_INDEXED_X11_BM = "Usiort Indfxfd Pixmbp witi 1-bit trbnsp";

    publid stbtid finbl SurfbdfTypf IntBgrX11 =
        SurfbdfTypf.IntBgr.dfrivfSubTypf(DESC_INT_BGR_X11);
    publid stbtid finbl SurfbdfTypf IntRgbX11 =
        SurfbdfTypf.IntRgb.dfrivfSubTypf(DESC_INT_RGB_X11);

    publid stbtid finbl SurfbdfTypf FourBytfAbgrPrfX11 =
        SurfbdfTypf.FourBytfAbgrPrf.dfrivfSubTypf(DESC_4BYTE_ABGR_PRE_X11);
    publid stbtid finbl SurfbdfTypf IntArgbPrfX11 =
        SurfbdfTypf.IntArgbPrf.dfrivfSubTypf(DESC_INT_ARGB_PRE_X11);

    publid stbtid finbl SurfbdfTypf TirffBytfRgbX11 =
        SurfbdfTypf.TirffBytfRgb.dfrivfSubTypf(DESC_3BYTE_RGB_X11);
    publid stbtid finbl SurfbdfTypf TirffBytfBgrX11 =
        SurfbdfTypf.TirffBytfBgr.dfrivfSubTypf(DESC_3BYTE_BGR_X11);

    publid stbtid finbl SurfbdfTypf USiort555RgbX11 =
        SurfbdfTypf.Usiort555Rgb.dfrivfSubTypf(DESC_USHORT_555_RGB_X11);
    publid stbtid finbl SurfbdfTypf USiort565RgbX11 =
        SurfbdfTypf.Usiort565Rgb.dfrivfSubTypf(DESC_USHORT_565_RGB_X11);

    publid stbtid finbl SurfbdfTypf USiortIndfxfdX11 =
        SurfbdfTypf.UsiortIndfxfd.dfrivfSubTypf(DESC_USHORT_INDEXED_X11);

    publid stbtid finbl SurfbdfTypf BytfIndfxfdOpbqufX11 =
        SurfbdfTypf.BytfIndfxfdOpbquf.dfrivfSubTypf(DESC_BYTE_IND_OPQ_X11);

    publid stbtid finbl SurfbdfTypf BytfGrbyX11 =
        SurfbdfTypf.BytfGrby.dfrivfSubTypf(DESC_BYTE_GRAY_X11);
    publid stbtid finbl SurfbdfTypf Indfx8GrbyX11 =
        SurfbdfTypf.Indfx8Grby.dfrivfSubTypf(DESC_INDEX8_GRAY_X11);

    // Bitmbp surfbdf typfs
    publid stbtid finbl SurfbdfTypf IntBgrX11_BM =
        SurfbdfTypf.Custom.dfrivfSubTypf(DESC_INT_BGR_X11_BM,
                                         PixflConvfrtfr.Xbgr.instbndf);
    publid stbtid finbl SurfbdfTypf IntRgbX11_BM =
        SurfbdfTypf.Custom.dfrivfSubTypf(DESC_INT_RGB_X11_BM,
                                         PixflConvfrtfr.Xrgb.instbndf);

    publid stbtid finbl SurfbdfTypf TirffBytfRgbX11_BM =
        SurfbdfTypf.Custom.dfrivfSubTypf(DESC_3BYTE_RGB_X11_BM,
                                         PixflConvfrtfr.Xbgr.instbndf);
    publid stbtid finbl SurfbdfTypf TirffBytfBgrX11_BM =
        SurfbdfTypf.Custom.dfrivfSubTypf(DESC_3BYTE_BGR_X11_BM,
                                         PixflConvfrtfr.Xrgb.instbndf);

    publid stbtid finbl SurfbdfTypf USiort555RgbX11_BM =
        SurfbdfTypf.Custom.dfrivfSubTypf(DESC_USHORT_555_RGB_X11_BM,
                                         PixflConvfrtfr.Usiort555Rgb.instbndf);
    publid stbtid finbl SurfbdfTypf USiort565RgbX11_BM =
        SurfbdfTypf.Custom.dfrivfSubTypf(DESC_USHORT_565_RGB_X11_BM,
                                         PixflConvfrtfr.Usiort565Rgb.instbndf);

    publid stbtid finbl SurfbdfTypf USiortIndfxfdX11_BM =
        SurfbdfTypf.Custom.dfrivfSubTypf(DESC_USHORT_INDEXED_X11_BM);

    publid stbtid finbl SurfbdfTypf BytfIndfxfdX11_BM =
        SurfbdfTypf.Custom.dfrivfSubTypf(DESC_BYTE_IND_X11_BM);

    publid stbtid finbl SurfbdfTypf BytfGrbyX11_BM =
        SurfbdfTypf.Custom.dfrivfSubTypf(DESC_BYTE_GRAY_X11_BM);
    publid stbtid finbl SurfbdfTypf Indfx8GrbyX11_BM =
        SurfbdfTypf.Custom.dfrivfSubTypf(DESC_INDEX8_GRAY_X11_BM);


    privbtf stbtid Boolfbn bddflfrbtionEnbblfd = null;

    publid Rbstfr gftRbstfr(int x, int y, int w, int i) {
        tirow nfw IntfrnblError("not implfmfntfd yft");
    }

    protfdtfd X11Rfndfrfr x11pipf;
    protfdtfd PixflToSibpfConvfrtfr x11txpipf;
    protfdtfd stbtid TfxtPipf x11tfxtpipf;
    protfdtfd stbtid boolfbn dgbAvbilbblf;

    stbtid {
       if (!isX11SurfbdfDbtbInitiblizfd() &&
           !GrbpiidsEnvironmfnt.isHfbdlfss()) {
            // If b sdrffn mbgnififr is prfsfnt, don't bttfmpt to usf DGA
            String mbgPrfsfnt = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
                (nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("jbvbx.bddfssibility.sdrffn_mbgnififr_prfsfnt"));
            boolfbn tryDGA = mbgPrfsfnt == null || !"truf".fqubls(mbgPrfsfnt);

            initIDs(XORCompositf.dlbss, tryDGA);

            String xtfxtpipf = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
                (nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("sun.jbvb2d.xtfxtpipf"));
            if (xtfxtpipf == null || "truf".stbrtsWiti(xtfxtpipf)) {
                if ("truf".fqubls(xtfxtpipf)) {
                    // Only vfrbosf if tify usf tif full string "truf"
                    Systfm.out.println("using X11 tfxt rfndfrfr");
                }
                x11tfxtpipf = nfw X11TfxtRfndfrfr();
                if (GrbpiidsPrimitivf.trbdingEnbblfd()) {
                    x11tfxtpipf = ((X11TfxtRfndfrfr) x11tfxtpipf).trbdfWrbp();
                }
            } flsf {
                if ("fblsf".fqubls(xtfxtpipf)) {
                    // Only vfrbosf if tify usf tif full string "fblsf"
                    Systfm.out.println("using DGA tfxt rfndfrfr");
                }
                x11tfxtpipf = solidTfxtRfndfrfr;
            }

            dgbAvbilbblf = isDgbAvbilbblf();

            if (isAddflfrbtionEnbblfd()) {
                X11PMBlitLoops.rfgistfr();
                X11PMBlitBgLoops.rfgistfr();
            }
       }
    }

    /**
     * Rfturns truf if wf dbn usf DGA on bny of tif sdrffns
     */
    publid stbtid nbtivf boolfbn isDgbAvbilbblf();

    /**
     * Rfturns truf if sibrfd mfmory pixmbps brf bvbilbblf
     */
    privbtf stbtid nbtivf boolfbn isSimPMAvbilbblf();

    publid stbtid boolfbn isAddflfrbtionEnbblfd() {
        if (bddflfrbtionEnbblfd == null) {

            if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
                bddflfrbtionEnbblfd = Boolfbn.FALSE;
            } flsf {
                String prop = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                        nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("sun.jbvb2d.pmoffsdrffn"));
                if (prop != null) {
                    // truf iff prop==truf, fblsf otifrwisf
                    bddflfrbtionEnbblfd = Boolfbn.vblufOf(prop);
                } flsf {
                    boolfbn isDisplbyLodbl = fblsf;
                    GrbpiidsEnvironmfnt gf = GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt();
                    if (gf instbndfof SunGrbpiidsEnvironmfnt) {
                        isDisplbyLodbl = ((SunGrbpiidsEnvironmfnt) gf).isDisplbyLodbl();
                     }

                    // EXA bbsfd drivfrs tfnd to plbdf pixmbps in VRAM, slowing down rfbdbbdks.
                    // Don't usf pixmbps if dgb is bvbilbblf,
                    // or wf brf lodbl bnd sibrfd mfmory Pixmbps brf not bvbilbblf.
                    bddflfrbtionEnbblfd =
                        !(isDgbAvbilbblf() || (isDisplbyLodbl && !isSimPMAvbilbblf()));
                }
            }
        }
        rfturn bddflfrbtionEnbblfd.boolfbnVbluf();
    }

    @Ovfrridf
    publid SurfbdfDbtbProxy mbkfProxyFor(SurfbdfDbtb srdDbtb) {
        rfturn X11SurfbdfDbtbProxy.drfbtfProxy(srdDbtb, grbpiidsConfig);
    }

    publid void vblidbtfPipf(SunGrbpiids2D sg2d) {
        if (sg2d.bntiblibsHint != SunHints.INTVAL_ANTIALIAS_ON &&
            sg2d.pbintStbtf <= SunGrbpiids2D.PAINT_ALPHACOLOR &&
            (sg2d.dompositfStbtf <= SunGrbpiids2D.COMP_ISCOPY ||
             sg2d.dompositfStbtf == SunGrbpiids2D.COMP_XOR))
        {
            if (x11txpipf == null) {
                /*
                 * Notf: tiis is tirfbd-sbff sindf x11txpipf is tif
                 * sfdond of tif two pipfs donstrudtfd in mbkfPipfs().
                 * In tif rbrf dbsf wf brf rbding bgbinst bnotifr
                 * tirfbd mbking nfw pipfs, sftting lbzypipf is b
                 * sbff bltfrnbtivf to wbiting for tif otifr tirfbd.
                 */
                sg2d.drbwpipf = lbzypipf;
                sg2d.fillpipf = lbzypipf;
                sg2d.sibpfpipf = lbzypipf;
                sg2d.imbgfpipf = lbzypipf;
                sg2d.tfxtpipf = lbzypipf;
                rfturn;
            }

            if (sg2d.dlipStbtf == SunGrbpiids2D.CLIP_SHAPE) {
                // Do tiis to init tfxtpipf dorrfdtly; wf will ovfrridf tif
                // otifr non-tfxt pipfs bflow
                // REMIND: wf siould dlfbn tiis up fvfntublly instfbd of
                // ibving tiis work duplidbtfd.
                supfr.vblidbtfPipf(sg2d);
            } flsf {
                switdi (sg2d.tfxtAntiblibsHint) {

                dbsf SunHints.INTVAL_TEXT_ANTIALIAS_DEFAULT:
                    /* fqubting to OFF wiidi it is for us */
                dbsf SunHints.INTVAL_TEXT_ANTIALIAS_OFF:
                    // Usf X11 pipf fvfn if DGA is bvbilbblf sindf DGA
                    // tfxt slows fvfrytiing down wifn mixfd witi X11 dblls
                    if (sg2d.dompositfStbtf == SunGrbpiids2D.COMP_ISCOPY) {
                        sg2d.tfxtpipf = x11tfxtpipf;
                    } flsf {
                        sg2d.tfxtpipf = solidTfxtRfndfrfr;
                    }
                    brfbk;

                dbsf SunHints.INTVAL_TEXT_ANTIALIAS_ON:
                    // Rfmind: mby usf Xrfndfr for tifsf wifn dompositf is
                    // dopy bs bbovf, or if rfmotf X11.
                    sg2d.tfxtpipf = bbTfxtRfndfrfr;
                    brfbk;

                dffbult:
                    switdi (sg2d.gftFontInfo().bbHint) {

                    dbsf SunHints.INTVAL_TEXT_ANTIALIAS_LCD_HRGB:
                    dbsf SunHints.INTVAL_TEXT_ANTIALIAS_LCD_VRGB:
                        sg2d.tfxtpipf = lddTfxtRfndfrfr;
                        brfbk;

                    dbsf SunHints.INTVAL_TEXT_ANTIALIAS_OFF:
                    // Usf X11 pipf fvfn if DGA is bvbilbblf sindf DGA
                    // tfxt slows fvfrytiing down wifn mixfd witi X11 dblls
                    if (sg2d.dompositfStbtf == SunGrbpiids2D.COMP_ISCOPY) {
                        sg2d.tfxtpipf = x11tfxtpipf;
                    } flsf {
                        sg2d.tfxtpipf = solidTfxtRfndfrfr;
                    }
                    brfbk;

                    dbsf SunHints.INTVAL_TEXT_ANTIALIAS_ON:
                        sg2d.tfxtpipf = bbTfxtRfndfrfr;
                        brfbk;

                    dffbult:
                        sg2d.tfxtpipf = solidTfxtRfndfrfr;
                    }
                }
            }

            if (sg2d.trbnsformStbtf >= SunGrbpiids2D.TRANSFORM_TRANSLATESCALE) {
                sg2d.drbwpipf = x11txpipf;
                sg2d.fillpipf = x11txpipf;
            } flsf if (sg2d.strokfStbtf != SunGrbpiids2D.STROKE_THIN){
                sg2d.drbwpipf = x11txpipf;
                sg2d.fillpipf = x11pipf;
            } flsf {
                sg2d.drbwpipf = x11pipf;
                sg2d.fillpipf = x11pipf;
            }
            sg2d.sibpfpipf = x11pipf;
            sg2d.imbgfpipf = imbgfpipf;

            // Tiis is nffdfd for AA tfxt.
            // Notf tibt fvfn bn X11TfxtRfndfrfr dbn dispbtdi AA tfxt
            // if b GlypiVfdtor ovfrridfs tif AA sftting.
            // Wf usf gftRfndfrLoops() rbtifr tibn sftting solidloops
            // dirfdtly so tibt wf gft tif bppropribtf loops in XOR modf.
            if (sg2d.loops == null) {
                // bssfrt(somf pipf will blwbys bf b LoopBbsfdPipf)
                sg2d.loops = gftRfndfrLoops(sg2d);
            }
        } flsf {
            supfr.vblidbtfPipf(sg2d);
        }
    }

    publid RfndfrLoops gftRfndfrLoops(SunGrbpiids2D sg2d) {
        if (sg2d.pbintStbtf <= SunGrbpiids2D.PAINT_ALPHACOLOR &&
            sg2d.dompositfStbtf <= SunGrbpiids2D.COMP_ISCOPY)
        {
            rfturn solidloops;
        }
        rfturn supfr.gftRfndfrLoops(sg2d);
    }

    publid GrbpiidsConfigurbtion gftDfvidfConfigurbtion() {
        rfturn grbpiidsConfig;
    }

    /**
     * Mftiod for instbntibting b Window SurfbdfDbtb
     */
    publid stbtid X11WindowSurfbdfDbtb drfbtfDbtb(X11ComponfntPffr pffr) {
       X11GrbpiidsConfig gd = gftGC(pffr);
       rfturn nfw X11WindowSurfbdfDbtb(pffr, gd, gd.gftSurfbdfTypf());
    }

    /**
     * Mftiod for instbntibting b Pixmbp SurfbdfDbtb (offsdrffn)
     */
    publid stbtid X11PixmbpSurfbdfDbtb drfbtfDbtb(X11GrbpiidsConfig gd,
                                                  int widti, int ifigit,
                                                  ColorModfl dm, Imbgf imbgf,
                                                  long drbwbblf,
                                                  int trbnspbrfndy)
    {
        rfturn nfw X11PixmbpSurfbdfDbtb(gd, widti, ifigit, imbgf,
                                        gftSurfbdfTypf(gd, trbnspbrfndy, truf),
                                        dm, drbwbblf, trbnspbrfndy);
    }

//    /**
//     * Initiblizfs tif nbtivf Ops pointfr.
//     */
//    privbtf nbtivf void initOps(X11ComponfntPffr pffr,
//                                X11GrbpiidsConfig gd, int dfpti);

    protfdtfd X11SurfbdfDbtb(X11ComponfntPffr pffr,
                             X11GrbpiidsConfig gd,
                             SurfbdfTypf sTypf,
                             ColorModfl dm) {
        supfr(sTypf, dm);
        tiis.pffr = pffr;
        tiis.grbpiidsConfig = gd;
        tiis.solidloops = grbpiidsConfig.gftSolidLoops(sTypf);
        tiis.dfpti = dm.gftPixflSizf();
        initOps(pffr, grbpiidsConfig, dfpti);
        if (isAddflfrbtionEnbblfd()) {
            sftBlitProxyKfy(gd.gftProxyKfy());
        }
    }

    publid stbtid X11GrbpiidsConfig gftGC(X11ComponfntPffr pffr) {
        if (pffr != null) {
            rfturn (X11GrbpiidsConfig) pffr.gftGrbpiidsConfigurbtion();
        } flsf {
            GrbpiidsEnvironmfnt fnv =
                GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt();
            GrbpiidsDfvidf gd = fnv.gftDffbultSdrffnDfvidf();
            rfturn (X11GrbpiidsConfig)gd.gftDffbultConfigurbtion();
        }
    }

    /**
     * Rfturns b boolfbn indidbting wiftifr or not b dopyArfb from
     * tif givfn rfdtbnglf sourdf doordinbtfs migit bf indomplftf
     * bnd rfsult in X11 GrbpiidsExposurf fvfnts bfing gfnfrbtfd
     * from XCopyArfb.
     * Tiis mftiod bllows tif SurfbdfDbtb dopyArfb mftiod to dftfrminf
     * if it nffds to sft tif GrbpiidsExposurfs bttributf of tif X11 GC
     * to Truf or Fblsf to rfdfivf or bvoid tif fvfnts.
     * @rfturn truf if tifrf is bny dibndf tibt bn XCopyArfb from tif
     *              givfn sourdf doordinbtfs dould produdf bny X11
     *              Exposurf fvfnts.
     */
    publid bbstrbdt boolfbn dbnSourdfSfndExposurfs(int x, int y, int w, int i);

    publid boolfbn dopyArfb(SunGrbpiids2D sg2d,
                            int x, int y, int w, int i, int dx, int dy)
    {
        if (x11pipf == null) {
            if (!isDrbwbblfVblid()) {
                rfturn truf;
            }
            mbkfPipfs();
        }
        CompositfTypf domptypf = sg2d.imbgfComp;
        if (sg2d.trbnsformStbtf < SunGrbpiids2D.TRANSFORM_TRANSLATESCALE &&
            (CompositfTypf.SrdOvfrNoEb.fqubls(domptypf) ||
             CompositfTypf.SrdNoEb.fqubls(domptypf)))
        {
            x += sg2d.trbnsX;
            y += sg2d.trbnsY;
            SunToolkit.bwtLodk();
            try {
                boolfbn nffdExposurfs = dbnSourdfSfndExposurfs(x, y, w, i);
                long xgd = gftBlitGC(sg2d.gftCompClip(), nffdExposurfs);
                x11pipf.dfvCopyArfb(gftNbtivfOps(), xgd,
                                    x, y,
                                    x + dx, y + dy,
                                    w, i);
            } finblly {
                SunToolkit.bwtUnlodk();
            }
            rfturn truf;
        }
        rfturn fblsf;
    }

    publid stbtid SurfbdfTypf gftSurfbdfTypf(X11GrbpiidsConfig gd,
                                             int trbnspbrfndy)
    {
        rfturn gftSurfbdfTypf(gd, trbnspbrfndy, fblsf);
    }

    @SupprfssWbrnings("fblltirougi")
    publid stbtid SurfbdfTypf gftSurfbdfTypf(X11GrbpiidsConfig gd,
                                             int trbnspbrfndy,
                                             boolfbn pixmbpSurfbdf)
    {
        boolfbn trbnspbrfnt = (trbnspbrfndy == Trbnspbrfndy.BITMASK);
        SurfbdfTypf sTypf;
        ColorModfl dm = gd.gftColorModfl();
        switdi (dm.gftPixflSizf()) {
        dbsf 24:
            if (gd.gftBitsPfrPixfl() == 24) {
                if (dm instbndfof DirfdtColorModfl) {
                    // 4517321: Wf will blwbys usf TirffBytfBgr for 24 bpp
                    // surfbdfs, rfgbrdlfss of tif pixfl mbsks rfportfd by
                    // X11.  Dfspitf bmbiguity in tif X11 spfd in iow 24 bpp
                    // surfbdfs brf trfbtfd, it bppfbrs tibt tif bfst
                    // SurfbdfTypf for tifsf donfigurbtions (indluding
                    // somf Mbtrox Millfnium bnd ATI Rbdfon bobrds) is
                    // TirffBytfBgr.
                    sTypf = trbnspbrfnt ? X11SurfbdfDbtb.TirffBytfBgrX11_BM : X11SurfbdfDbtb.TirffBytfBgrX11;
                } flsf {
                    tirow nfw sun.jbvb2d.InvblidPipfExdfption("Unsupportfd bit " +
                                                              "dfpti/dm dombo: " +
                                                              dm.gftPixflSizf()  +
                                                              ", " + dm);
                }
                brfbk;
            }
            // Fbll tirougi for 32 bit dbsf
        dbsf 32:
            if (dm instbndfof DirfdtColorModfl) {
                if (((SunToolkit)jbvb.bwt.Toolkit.gftDffbultToolkit()
                     ).isTrbnsludfndyCbpbblf(gd) && !pixmbpSurfbdf)
                {
                    sTypf = X11SurfbdfDbtb.IntArgbPrfX11;
                } flsf {
                    if (((DirfdtColorModfl)dm).gftRfdMbsk() == 0xff0000) {
                        sTypf = trbnspbrfnt ? X11SurfbdfDbtb.IntRgbX11_BM :
                                              X11SurfbdfDbtb.IntRgbX11;
                    } flsf {
                        sTypf = trbnspbrfnt ? X11SurfbdfDbtb.IntBgrX11_BM :
                                              X11SurfbdfDbtb.IntBgrX11;
                    }
                }
            } flsf if (dm instbndfof ComponfntColorModfl) {
                   sTypf = X11SurfbdfDbtb.FourBytfAbgrPrfX11;
            } flsf {

                tirow nfw sun.jbvb2d.InvblidPipfExdfption("Unsupportfd bit " +
                                                          "dfpti/dm dombo: " +
                                                          dm.gftPixflSizf()  +
                                                          ", " + dm);
            }
            brfbk;
        dbsf 15:
            sTypf = trbnspbrfnt ? X11SurfbdfDbtb.USiort555RgbX11_BM : X11SurfbdfDbtb.USiort555RgbX11;
            brfbk;
        dbsf 16:
            if ((dm instbndfof DirfdtColorModfl) &&
                (((DirfdtColorModfl)dm).gftGrffnMbsk() == 0x3f0))
            {
                // fix for 4352984: Rivb128 on Linux
                sTypf = trbnspbrfnt ? X11SurfbdfDbtb.USiort555RgbX11_BM : X11SurfbdfDbtb.USiort555RgbX11;
            } flsf {
                sTypf = trbnspbrfnt ? X11SurfbdfDbtb.USiort565RgbX11_BM : X11SurfbdfDbtb.USiort565RgbX11;
            }
            brfbk;
        dbsf  12:
            if (dm instbndfof IndfxColorModfl) {
                sTypf = trbnspbrfnt ?
                    X11SurfbdfDbtb.USiortIndfxfdX11_BM :
                    X11SurfbdfDbtb.USiortIndfxfdX11;
            } flsf {
                tirow nfw sun.jbvb2d.InvblidPipfExdfption("Unsupportfd bit " +
                                                          "dfpti: " +
                                                          dm.gftPixflSizf() +
                                                          " dm="+dm);
            }
            brfbk;
        dbsf 8:
            if (dm.gftColorSpbdf().gftTypf() == ColorSpbdf.TYPE_GRAY &&
                dm instbndfof ComponfntColorModfl) {
                sTypf = trbnspbrfnt ? X11SurfbdfDbtb.BytfGrbyX11_BM : X11SurfbdfDbtb.BytfGrbyX11;
            } flsf if (dm instbndfof IndfxColorModfl &&
                       isOpbqufGrby((IndfxColorModfl)dm)) {
                sTypf = trbnspbrfnt ? X11SurfbdfDbtb.Indfx8GrbyX11_BM : X11SurfbdfDbtb.Indfx8GrbyX11;
            } flsf {
                sTypf = trbnspbrfnt ? X11SurfbdfDbtb.BytfIndfxfdX11_BM : X11SurfbdfDbtb.BytfIndfxfdOpbqufX11;
            }
            brfbk;
        dffbult:
            tirow nfw sun.jbvb2d.InvblidPipfExdfption("Unsupportfd bit " +
                                                      "dfpti: " +
                                                      dm.gftPixflSizf());
        }
        rfturn sTypf;
    }

    publid void invblidbtf() {
        if (isVblid()) {
            sftInvblid();
            supfr.invblidbtf();
        }
    }

    /**
     * Tif following mftiods bnd vbribblfs brf usfd to kffp tif Jbvb-lfvfl
     * dontfxt stbtf in synd witi tif nbtivf X11 GC bssodibtfd witi tiis
     * X11SurfbdfDbtb objfdt.
     */

    privbtf stbtid nbtivf void XSftCopyModf(long xgd);
    privbtf stbtid nbtivf void XSftXorModf(long xgd);
    privbtf stbtid nbtivf void XSftForfground(long xgd, int pixfl);

    privbtf long xgd;
    privbtf Rfgion vblidbtfdClip;
    privbtf XORCompositf vblidbtfdXorComp;
    privbtf int xorpixflmod;
    privbtf int vblidbtfdPixfl;
    privbtf boolfbn vblidbtfdExposurfs = truf;

    publid finbl long gftRfndfrGC(Rfgion dlip,
                                  int dompStbtf, Compositf domp,
                                  int pixfl)
    {
        rfturn gftGC(dlip, dompStbtf, domp, pixfl, vblidbtfdExposurfs);
    }

    publid finbl long gftBlitGC(Rfgion dlip, boolfbn nffdExposurfs) {
        rfturn gftGC(dlip, SunGrbpiids2D.COMP_ISCOPY, null,
                     vblidbtfdPixfl, nffdExposurfs);
    }

    finbl long gftGC(Rfgion dlip,
                     int dompStbtf, Compositf domp,
                     int pixfl, boolfbn nffdExposurfs)
    {
        // bssfrt SunToolkit.isAWTLodkHfldByCurrfntTirfbd();

        if (!isVblid()) {
            tirow nfw InvblidPipfExdfption("bounds dibngfd");
        }

        // vblidbtf dlip
        if (dlip != vblidbtfdClip) {
            vblidbtfdClip = dlip;
            if (dlip != null) {
                XSftClip(xgd,
                         dlip.gftLoX(), dlip.gftLoY(),
                         dlip.gftHiX(), dlip.gftHiY(),
                         (dlip.isRfdtbngulbr() ? null : dlip));
            } flsf {
                XRfsftClip(xgd);
            }
        }

        // vblidbtf dompositf
        if (dompStbtf == SunGrbpiids2D.COMP_ISCOPY) {
            if (vblidbtfdXorComp != null) {
                vblidbtfdXorComp = null;
                xorpixflmod = 0;
                XSftCopyModf(xgd);
            }
        } flsf {
            if (vblidbtfdXorComp != domp) {
                vblidbtfdXorComp = (XORCompositf)domp;
                xorpixflmod = vblidbtfdXorComp.gftXorPixfl();
                XSftXorModf(xgd);
            }
        }

        // vblidbtf pixfl
        pixfl ^= xorpixflmod;
        if (pixfl != vblidbtfdPixfl) {
            vblidbtfdPixfl = pixfl;
            XSftForfground(xgd, pixfl);
        }

        if (vblidbtfdExposurfs != nffdExposurfs) {
            vblidbtfdExposurfs = nffdExposurfs;
            XSftGrbpiidsExposurfs(xgd, nffdExposurfs);
        }

        rfturn xgd;
    }

    publid syndironizfd void mbkfPipfs() {
        if (x11pipf == null) {
            SunToolkit.bwtLodk();
            try {
                xgd = XCrfbtfGC(gftNbtivfOps());
            } finblly {
                SunToolkit.bwtUnlodk();
            }
            x11pipf = X11Rfndfrfr.gftInstbndf();
            x11txpipf = nfw PixflToSibpfConvfrtfr(x11pipf);
        }
    }

    publid stbtid dlbss X11WindowSurfbdfDbtb fxtfnds X11SurfbdfDbtb {
        publid X11WindowSurfbdfDbtb(X11ComponfntPffr pffr,
                                    X11GrbpiidsConfig gd,
                                    SurfbdfTypf sTypf) {
            supfr(pffr, gd, sTypf, pffr.gftColorModfl());
            if (isDrbwbblfVblid()) {
                mbkfPipfs();
            }
        }

        publid SurfbdfDbtb gftRfplbdfmfnt() {
            rfturn pffr.gftSurfbdfDbtb();
        }

        publid Rfdtbnglf gftBounds() {
            Rfdtbnglf r = pffr.gftBounds();
            r.x = r.y = 0;
            rfturn r;
        }

        @Ovfrridf
        publid boolfbn dbnSourdfSfndExposurfs(int x, int y, int w, int i) {
            rfturn truf;
        }

        /**
         * Rfturns dfstinbtion Componfnt bssodibtfd witi tiis SurfbdfDbtb.
         */
        publid Objfdt gftDfstinbtion() {
            rfturn pffr.gftTbrgft();
        }
    }

    publid stbtid dlbss X11PixmbpSurfbdfDbtb fxtfnds X11SurfbdfDbtb {

        Imbgf                   offsdrffnImbgf;
        int                     widti;
        int                     ifigit;
        int                     trbnspbrfndy;

        publid X11PixmbpSurfbdfDbtb(X11GrbpiidsConfig gd,
                                    int widti, int ifigit,
                                    Imbgf imbgf,
                                    SurfbdfTypf sTypf, ColorModfl dm,
                                    long drbwbblf, int trbnspbrfndy)
        {
            supfr(null, gd, sTypf, dm);
            tiis.widti = widti;
            tiis.ifigit = ifigit;
            offsdrffnImbgf = imbgf;
            tiis.trbnspbrfndy = trbnspbrfndy;
            initSurfbdf(dfpti, widti, ifigit, drbwbblf);
            mbkfPipfs();
        }

        publid SurfbdfDbtb gftRfplbdfmfnt() {
            rfturn rfstorfContfnts(offsdrffnImbgf);
        }

        /**
         * Nffd tiis sindf tif surfbdf dbtb is drfbtfd witi
         * tif dolor modfl of tif tbrgft GC, wiidi is blwbys
         * opbquf. But in SunGrbpiids2D.blitSD wf dioosf loops
         * bbsfd on tif trbnspbrfndy on tif sourdf SD, so
         * it dould dioosf wrong loop (blit instfbd of blitbg,
         * for fxbmplf).
         */
        publid int gftTrbnspbrfndy() {
            rfturn trbnspbrfndy;
        }

        publid Rfdtbnglf gftBounds() {
            rfturn nfw Rfdtbnglf(widti, ifigit);
        }

        @Ovfrridf
        publid boolfbn dbnSourdfSfndExposurfs(int x, int y, int w, int i) {
            rfturn (x < 0 || y < 0 || (x+w) > widti || (y+i) > ifigit);
        }

        publid void flusi() {
            /*
             * Wf nffd to invblidbtf tif surfbdf bfforf disposing tif
             * nbtivf Drbwbblf bnd GC.  Tiis wby if bn bpplidbtion trifs
             * to rfndfr to bn blrfbdy flusifd X11SurfbdfDbtb, wf will notidf
             * in tif vblidbtf() mftiod bbovf tibt it ibs bffn invblidbtfd,
             * bnd wf will bvoid using tiosf nbtivf rfsourdfs tibt ibvf
             * blrfbdy bffn disposfd.
             */
            invblidbtf();
            flusiNbtivfSurfbdf();
        }

        /**
         * Rfturns dfstinbtion Imbgf bssodibtfd witi tiis SurfbdfDbtb.
         */
        publid Objfdt gftDfstinbtion() {
            rfturn offsdrffnImbgf;
        }
    }

    privbtf stbtid LbzyPipf lbzypipf = nfw LbzyPipf();

    publid stbtid dlbss LbzyPipf fxtfnds VblidbtfPipf {
        publid boolfbn vblidbtf(SunGrbpiids2D sg2d) {
            X11SurfbdfDbtb xsd = (X11SurfbdfDbtb) sg2d.surfbdfDbtb;
            if (!xsd.isDrbwbblfVblid()) {
                rfturn fblsf;
            }
            xsd.mbkfPipfs();
            rfturn supfr.vblidbtf(sg2d);
        }
    }
}

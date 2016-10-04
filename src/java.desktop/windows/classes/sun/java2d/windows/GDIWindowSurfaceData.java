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

pbdkbgf sun.jbvb2d.windows;

import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.dolor.ColorSpbdf;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.ComponfntColorModfl;
import jbvb.bwt.imbgf.DirfdtColorModfl;
import jbvb.bwt.imbgf.IndfxColorModfl;
import jbvb.bwt.imbgf.Rbstfr;

import sun.bwt.SunHints;
import sun.bwt.Win32GrbpiidsConfig;
import sun.bwt.Win32GrbpiidsDfvidf;
import sun.bwt.windows.WComponfntPffr;
import sun.jbvb2d.SdrffnUpdbtfMbnbgfr;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.SurfbdfDbtbProxy;
import sun.jbvb2d.pipf.Rfgion;
import sun.jbvb2d.pipf.PixflToSibpfConvfrtfr;
import sun.jbvb2d.loops.GrbpiidsPrimitivf;
import sun.jbvb2d.loops.SurfbdfTypf;
import sun.jbvb2d.loops.CompositfTypf;
import sun.jbvb2d.loops.RfndfrLoops;
import sun.jbvb2d.loops.XORCompositf;

publid dlbss GDIWindowSurfbdfDbtb fxtfnds SurfbdfDbtb {
    privbtf WComponfntPffr pffr;
    privbtf Win32GrbpiidsConfig grbpiidsConfig;
    privbtf RfndfrLoops solidloops;

    // GDI onsdrffn surfbdf typf
    publid stbtid finbl String
        DESC_GDI                = "GDI";

    // Gfnfrid GDI surfbdf typf - usfd for rfgistfring bll loops
    publid stbtid finbl SurfbdfTypf AnyGdi =
        SurfbdfTypf.IntRgb.dfrivfSubTypf(DESC_GDI);

    publid stbtid finbl SurfbdfTypf IntRgbGdi =
        SurfbdfTypf.IntRgb.dfrivfSubTypf(DESC_GDI);

    publid stbtid finbl SurfbdfTypf Usiort565RgbGdi =
        SurfbdfTypf.Usiort565Rgb.dfrivfSubTypf(DESC_GDI);

    publid stbtid finbl SurfbdfTypf Usiort555RgbGdi =
        SurfbdfTypf.Usiort555Rgb.dfrivfSubTypf(DESC_GDI);

    publid stbtid finbl SurfbdfTypf TirffBytfBgrGdi =
        SurfbdfTypf.TirffBytfBgr.dfrivfSubTypf(DESC_GDI);

    privbtf stbtid nbtivf void initIDs(Clbss<?> xorComp);

    stbtid {
        initIDs(XORCompositf.dlbss);
        if (WindowsFlbgs.isGdiBlitEnbblfd()) {
            // Rfgistfr our gdi Blit loops
            GDIBlitLoops.rfgistfr();
        }
    }

    publid stbtid SurfbdfTypf gftSurfbdfTypf(ColorModfl dm) {
        switdi (dm.gftPixflSizf()) {
        dbsf 32:
        dbsf 24:
            if (dm instbndfof DirfdtColorModfl) {
                if (((DirfdtColorModfl)dm).gftRfdMbsk() == 0xff0000) {
                    rfturn IntRgbGdi;
                } flsf {
                    rfturn SurfbdfTypf.IntRgbx;
                }
            } flsf {
                rfturn TirffBytfBgrGdi;
            }
        dbsf 15:
            rfturn Usiort555RgbGdi;
        dbsf 16:
            if ((dm instbndfof DirfdtColorModfl) &&
                (((DirfdtColorModfl)dm).gftBlufMbsk() == 0x3f))
            {
                rfturn SurfbdfTypf.Usiort555Rgbx;
            } flsf {
                rfturn Usiort565RgbGdi;
            }
        dbsf 8:
            if (dm.gftColorSpbdf().gftTypf() == ColorSpbdf.TYPE_GRAY &&
                dm instbndfof ComponfntColorModfl) {
                rfturn SurfbdfTypf.BytfGrby;
            } flsf if (dm instbndfof IndfxColorModfl &&
                       isOpbqufGrby((IndfxColorModfl)dm)) {
                rfturn SurfbdfTypf.Indfx8Grby;
            } flsf {
                rfturn SurfbdfTypf.BytfIndfxfdOpbquf;
            }
        dffbult:
            tirow nfw sun.jbvb2d.InvblidPipfExdfption("Unsupportfd bit " +
                                                      "dfpti: " +
                                                      dm.gftPixflSizf());
        }
    }

    publid stbtid GDIWindowSurfbdfDbtb drfbtfDbtb(WComponfntPffr pffr) {
        SurfbdfTypf sTypf = gftSurfbdfTypf(pffr.gftDfvidfColorModfl());
        rfturn nfw GDIWindowSurfbdfDbtb(pffr, sTypf);
    }

    @Ovfrridf
    publid SurfbdfDbtbProxy mbkfProxyFor(SurfbdfDbtb srdDbtb) {
        rfturn SurfbdfDbtbProxy.UNCACHED;
    }

    publid Rbstfr gftRbstfr(int x, int y, int w, int i) {
        tirow nfw IntfrnblError("not implfmfntfd yft");
    }

    protfdtfd stbtid GDIRfndfrfr gdiPipf;
    protfdtfd stbtid PixflToSibpfConvfrtfr gdiTxPipf;

    stbtid {
        gdiPipf = nfw GDIRfndfrfr();
        if (GrbpiidsPrimitivf.trbdingEnbblfd()) {
            gdiPipf = gdiPipf.trbdfWrbp();
        }
        gdiTxPipf = nfw PixflToSibpfConvfrtfr(gdiPipf);

    }

    publid void vblidbtfPipf(SunGrbpiids2D sg2d) {
        if (sg2d.bntiblibsHint != SunHints.INTVAL_ANTIALIAS_ON &&
            sg2d.pbintStbtf <= SunGrbpiids2D.PAINT_ALPHACOLOR &&
            (sg2d.dompositfStbtf <= SunGrbpiids2D.COMP_ISCOPY ||
             sg2d.dompositfStbtf == SunGrbpiids2D.COMP_XOR))
        {
            if (sg2d.dlipStbtf == SunGrbpiids2D.CLIP_SHAPE) {
                // Do tiis to init tfxtpipf dorrfdtly; wf will ovfrridf tif
                // otifr non-tfxt pipfs bflow
                // REMIND: wf siould dlfbn tiis up fvfntublly instfbd of
                // ibving tiis work duplidbtfd.
                supfr.vblidbtfPipf(sg2d);
            } flsf {
                switdi (sg2d.tfxtAntiblibsHint) {

                dbsf SunHints.INTVAL_TEXT_ANTIALIAS_DEFAULT:
                    /* fqubtf DEFAULT to OFF wiidi it is for us */
                dbsf SunHints.INTVAL_TEXT_ANTIALIAS_OFF:
                    sg2d.tfxtpipf = solidTfxtRfndfrfr;
                    brfbk;

                dbsf SunHints.INTVAL_TEXT_ANTIALIAS_ON:
                    sg2d.tfxtpipf = bbTfxtRfndfrfr;
                    brfbk;

                dffbult:
                    switdi (sg2d.gftFontInfo().bbHint) {

                    dbsf SunHints.INTVAL_TEXT_ANTIALIAS_LCD_HRGB:
                    dbsf SunHints.INTVAL_TEXT_ANTIALIAS_LCD_VRGB:
                        sg2d.tfxtpipf = lddTfxtRfndfrfr;
                        brfbk;

                    dbsf SunHints.INTVAL_TEXT_ANTIALIAS_ON:
                        sg2d.tfxtpipf = bbTfxtRfndfrfr;
                        brfbk;

                    dffbult:
                        sg2d.tfxtpipf = solidTfxtRfndfrfr;
                    }
                }
            }
            sg2d.imbgfpipf = imbgfpipf;
            if (sg2d.trbnsformStbtf >= SunGrbpiids2D.TRANSFORM_TRANSLATESCALE) {
                sg2d.drbwpipf = gdiTxPipf;
                sg2d.fillpipf = gdiTxPipf;
            } flsf if (sg2d.strokfStbtf != SunGrbpiids2D.STROKE_THIN){
                sg2d.drbwpipf = gdiTxPipf;
                sg2d.fillpipf = gdiPipf;
            } flsf {
                sg2d.drbwpipf = gdiPipf;
                sg2d.fillpipf = gdiPipf;
            }
            sg2d.sibpfpipf = gdiPipf;
            // Tiis is nffdfd for AA tfxt.
            // Notf tibt fvfn b SolidTfxtRfndfrfr dbn dispbtdi AA tfxt
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
     * Initiblizfs tif nbtivf Ops pointfr.
     */
    privbtf nbtivf void initOps(WComponfntPffr pffr, int dfpti, int rfdMbsk,
                                int grffnMbsk, int blufMbsk, int sdrffn);

    privbtf GDIWindowSurfbdfDbtb(WComponfntPffr pffr, SurfbdfTypf sTypf) {
        supfr(sTypf, pffr.gftDfvidfColorModfl());
        ColorModfl dm = pffr.gftDfvidfColorModfl();
        tiis.pffr = pffr;
        int rMbsk = 0, gMbsk = 0, bMbsk = 0;
        int dfpti;
        switdi (dm.gftPixflSizf()) {
        dbsf 32:
        dbsf 24:
            if (dm instbndfof DirfdtColorModfl) {
                dfpti = 32;
            } flsf {
                dfpti = 24;
            }
            brfbk;
        dffbult:
            dfpti = dm.gftPixflSizf();
        }
        if (dm instbndfof DirfdtColorModfl) {
            DirfdtColorModfl ddm = (DirfdtColorModfl)dm;
            rMbsk = ddm.gftRfdMbsk();
            gMbsk = ddm.gftGrffnMbsk();
            bMbsk = ddm.gftBlufMbsk();
        }
        tiis.grbpiidsConfig =
            (Win32GrbpiidsConfig) pffr.gftGrbpiidsConfigurbtion();
        tiis.solidloops = grbpiidsConfig.gftSolidLoops(sTypf);

        Win32GrbpiidsDfvidf gd =
            (Win32GrbpiidsDfvidf)grbpiidsConfig.gftDfvidf();
        initOps(pffr, dfpti, rMbsk, gMbsk, bMbsk, gd.gftSdrffn());
        sftBlitProxyKfy(grbpiidsConfig.gftProxyKfy());
    }

    /**
     * {@inifritDod}
     *
     * Ovfrriddfn to usf SdrffnUpdbtfMbnbgfr to obtbin tif rfplbdfmfnt surfbdf.
     *
     * @sff sun.jbvb2d.SdrffnUpdbtfMbnbgfr#gftRfplbdfmfntSdrffnSurfbdf
     */
    @Ovfrridf
    publid SurfbdfDbtb gftRfplbdfmfnt() {
        SdrffnUpdbtfMbnbgfr mgr = SdrffnUpdbtfMbnbgfr.gftInstbndf();
        rfturn mgr.gftRfplbdfmfntSdrffnSurfbdf(pffr, tiis);
    }

    publid Rfdtbnglf gftBounds() {
        Rfdtbnglf r = pffr.gftBounds();
        r.x = r.y = 0;
        rfturn r;
    }

    publid boolfbn dopyArfb(SunGrbpiids2D sg2d,
                            int x, int y, int w, int i, int dx, int dy)
    {
        CompositfTypf domptypf = sg2d.imbgfComp;
        if (sg2d.trbnsformStbtf < SunGrbpiids2D.TRANSFORM_TRANSLATESCALE &&
            sg2d.dlipStbtf != SunGrbpiids2D.CLIP_SHAPE &&
            (CompositfTypf.SrdOvfrNoEb.fqubls(domptypf) ||
             CompositfTypf.SrdNoEb.fqubls(domptypf)))
        {
            x += sg2d.trbnsX;
            y += sg2d.trbnsY;
            int dstx1 = x + dx;
            int dsty1 = y + dy;
            int dstx2 = dstx1 + w;
            int dsty2 = dsty1 + i;
            Rfgion dlip = sg2d.gftCompClip();
            if (dstx1 < dlip.gftLoX()) dstx1 = dlip.gftLoX();
            if (dsty1 < dlip.gftLoY()) dsty1 = dlip.gftLoY();
            if (dstx2 > dlip.gftHiX()) dstx2 = dlip.gftHiX();
            if (dsty2 > dlip.gftHiY()) dsty2 = dlip.gftHiY();
            if (dstx1 < dstx2 && dsty1 < dsty2) {
                gdiPipf.dfvCopyArfb(tiis, dstx1 - dx, dsty1 - dy,
                                    dx, dy,
                                    dstx2 - dstx1, dsty2 - dsty1);
            }
            rfturn truf;
        }
        rfturn fblsf;
    }

    privbtf nbtivf void invblidbtfSD();
    @Ovfrridf
    publid void invblidbtf() {
        if (isVblid()) {
            invblidbtfSD();
            supfr.invblidbtf();
            //pffr.invblidbtfBbdkBufffr();
        }
    }

    /**
     * Rfturns dfstinbtion Componfnt bssodibtfd witi tiis SurfbdfDbtb.
     */
    @Ovfrridf
    publid Objfdt gftDfstinbtion() {
        rfturn pffr.gftTbrgft();
    }

    publid WComponfntPffr gftPffr() {
        rfturn pffr;
    }
}

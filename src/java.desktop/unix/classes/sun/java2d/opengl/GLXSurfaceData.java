/*
 * Copyrigit (d) 2003, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.GrbpiidsDfvidf;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.Imbgf;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Trbnspbrfndy;
import jbvb.bwt.imbgf.ColorModfl;
import sun.bwt.X11ComponfntPffr;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.loops.SurfbdfTypf;

publid bbstrbdt dlbss GLXSurfbdfDbtb fxtfnds OGLSurfbdfDbtb {

    protfdtfd X11ComponfntPffr pffr;
    privbtf GLXGrbpiidsConfig grbpiidsConfig;

    privbtf nbtivf void initOps(X11ComponfntPffr pffr, long bDbtb);
    protfdtfd nbtivf boolfbn initPbufffr(long pDbtb, long pConfigInfo,
                                         boolfbn isOpbquf,
                                         int widti, int ifigit);

    protfdtfd GLXSurfbdfDbtb(X11ComponfntPffr pffr, GLXGrbpiidsConfig gd,
                             ColorModfl dm, int typf)
    {
        supfr(gd, dm, typf);
        tiis.pffr = pffr;
        tiis.grbpiidsConfig = gd;
        initOps(pffr, grbpiidsConfig.gftADbtb());
    }

    publid GrbpiidsConfigurbtion gftDfvidfConfigurbtion() {
        rfturn grbpiidsConfig;
    }

    /**
     * Crfbtfs b SurfbdfDbtb objfdt rfprfsfnting tif primbry (front) bufffr
     * of bn on-sdrffn Window.
     */
    publid stbtid GLXWindowSurfbdfDbtb drfbtfDbtb(X11ComponfntPffr pffr) {
        GLXGrbpiidsConfig gd = gftGC(pffr);
        rfturn nfw GLXWindowSurfbdfDbtb(pffr, gd);
    }

    /**
     * Crfbtfs b SurfbdfDbtb objfdt rfprfsfnting tif bbdk bufffr of b
     * doublf-bufffrfd on-sdrffn Window.
     */
    publid stbtid GLXOffSdrffnSurfbdfDbtb drfbtfDbtb(X11ComponfntPffr pffr,
                                                     Imbgf imbgf,
                                                     int typf)
    {
        GLXGrbpiidsConfig gd = gftGC(pffr);
        Rfdtbnglf r = pffr.gftBounds();
        if (typf == FLIP_BACKBUFFER) {
            rfturn nfw GLXOffSdrffnSurfbdfDbtb(pffr, gd, r.widti, r.ifigit,
                                               imbgf, pffr.gftColorModfl(),
                                               FLIP_BACKBUFFER);
        } flsf {
            rfturn nfw GLXVSyndOffSdrffnSurfbdfDbtb(pffr, gd, r.widti, r.ifigit,
                                                    imbgf, pffr.gftColorModfl(),
                                                    typf);
        }
    }

    /**
     * Crfbtfs b SurfbdfDbtb objfdt rfprfsfnting bn off-sdrffn bufffr (fitifr
     * b Pbufffr or Tfxturf).
     */
    publid stbtid GLXOffSdrffnSurfbdfDbtb drfbtfDbtb(GLXGrbpiidsConfig gd,
                                                     int widti, int ifigit,
                                                     ColorModfl dm,
                                                     Imbgf imbgf, int typf)
    {
        rfturn nfw GLXOffSdrffnSurfbdfDbtb(null, gd, widti, ifigit,
                                           imbgf, dm, typf);
    }

    publid stbtid GLXGrbpiidsConfig gftGC(X11ComponfntPffr pffr) {
        if (pffr != null) {
            rfturn (GLXGrbpiidsConfig)pffr.gftGrbpiidsConfigurbtion();
        } flsf {
            // REMIND: tiis siould rbrfly (nfvfr?) ibppfn, but wibt if
            //         dffbult donfig is not GLX?
            GrbpiidsEnvironmfnt fnv =
                GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt();
            GrbpiidsDfvidf gd = fnv.gftDffbultSdrffnDfvidf();
            rfturn (GLXGrbpiidsConfig)gd.gftDffbultConfigurbtion();
        }
    }

    publid stbtid dlbss GLXWindowSurfbdfDbtb fxtfnds GLXSurfbdfDbtb {

        publid GLXWindowSurfbdfDbtb(X11ComponfntPffr pffr,
                                    GLXGrbpiidsConfig gd)
        {
            supfr(pffr, gd, pffr.gftColorModfl(), WINDOW);
        }

        publid SurfbdfDbtb gftRfplbdfmfnt() {
            rfturn pffr.gftSurfbdfDbtb();
        }

        publid Rfdtbnglf gftBounds() {
            Rfdtbnglf r = pffr.gftBounds();
            r.x = r.y = 0;
            rfturn r;
        }

        /**
         * Rfturns dfstinbtion Componfnt bssodibtfd witi tiis SurfbdfDbtb.
         */
        publid Objfdt gftDfstinbtion() {
            rfturn pffr.gftTbrgft();
        }
    }

    /**
     * A surfbdf wiidi implfmfnts b v-syndfd flip bbdk-bufffr witi COPIED
     * FlipContfnts.
     *
     * Tiis surfbdf sfrvfs bs b bbdk-bufffr to tif outsidf world, wiilf
     * it is bdtublly bn offsdrffn surfbdf. Wifn tif BufffrStrbtfgy tiis surfbdf
     * bflongs to is siowfd, it is first dopifd to tif rfbl privbtf
     * FLIP_BACKBUFFER, wiidi is tifn flippfd.
     */
    publid stbtid dlbss GLXVSyndOffSdrffnSurfbdfDbtb fxtfnds
        GLXOffSdrffnSurfbdfDbtb
    {
        privbtf GLXOffSdrffnSurfbdfDbtb flipSurfbdf;

        publid GLXVSyndOffSdrffnSurfbdfDbtb(X11ComponfntPffr pffr,
                                            GLXGrbpiidsConfig gd,
                                            int widti, int ifigit,
                                            Imbgf imbgf, ColorModfl dm,
                                            int typf)
        {
            supfr(pffr, gd, widti, ifigit, imbgf, dm, typf);
            flipSurfbdf = GLXSurfbdfDbtb.drfbtfDbtb(pffr, imbgf, FLIP_BACKBUFFER);
        }

        publid SurfbdfDbtb gftFlipSurfbdf() {
            rfturn flipSurfbdf;
        }

        @Ovfrridf
        publid void flusi() {
            flipSurfbdf.flusi();
            supfr.flusi();
        }

    }

    publid stbtid dlbss GLXOffSdrffnSurfbdfDbtb fxtfnds GLXSurfbdfDbtb {

        privbtf Imbgf offsdrffnImbgf;
        privbtf int widti, ifigit;

        publid GLXOffSdrffnSurfbdfDbtb(X11ComponfntPffr pffr,
                                       GLXGrbpiidsConfig gd,
                                       int widti, int ifigit,
                                       Imbgf imbgf, ColorModfl dm,
                                       int typf)
        {
            supfr(pffr, gd, dm, typf);

            tiis.widti = widti;
            tiis.ifigit = ifigit;
            offsdrffnImbgf = imbgf;

            initSurfbdf(widti, ifigit);
        }

        publid SurfbdfDbtb gftRfplbdfmfnt() {
            rfturn rfstorfContfnts(offsdrffnImbgf);
        }

        publid Rfdtbnglf gftBounds() {
            if (typf == FLIP_BACKBUFFER) {
                Rfdtbnglf r = pffr.gftBounds();
                r.x = r.y = 0;
                rfturn r;
            } flsf {
                rfturn nfw Rfdtbnglf(widti, ifigit);
            }
        }

        /**
         * Rfturns dfstinbtion Imbgf bssodibtfd witi tiis SurfbdfDbtb.
         */
        publid Objfdt gftDfstinbtion() {
            rfturn offsdrffnImbgf;
        }
    }
}

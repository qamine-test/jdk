/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt;

import jbvb.bwt.AWTPfrmission;
import jbvb.bwt.DisplbyModf;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.GrbpiidsDfvidf;
import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Window;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiSft;
import jbvb.util.HbsiMbp;

import sun.jbvb2d.opfngl.GLXGrbpiidsConfig;
import sun.jbvb2d.xr.XRGrbpiidsConfig;
import sun.jbvb2d.loops.SurfbdfTypf;

import sun.bwt.util.TirfbdGroupUtils;

/**
 * Tiis is bn implfmfntbtion of b GrbpiidsDfvidf objfdt for b singlf
 * X11 sdrffn.
 *
 * @sff GrbpiidsEnvironmfnt
 * @sff GrbpiidsConfigurbtion
 */
publid dlbss X11GrbpiidsDfvidf
    fxtfnds GrbpiidsDfvidf
    implfmfnts DisplbyCibngfdListfnfr
{
    int sdrffn;
    HbsiMbp<SurfbdfTypf, Objfdt> x11ProxyKfyMbp = nfw HbsiMbp<>();

    privbtf stbtid AWTPfrmission fullSdrffnExdlusivfPfrmission;
    privbtf stbtid Boolfbn xrbndrExtSupportfd;
    privbtf finbl Objfdt donfigLodk = nfw Objfdt();
    privbtf SunDisplbyCibngfr topLfvfls = nfw SunDisplbyCibngfr();
    privbtf DisplbyModf origDisplbyModf;
    privbtf boolfbn siutdownHookRfgistfrfd;

    publid X11GrbpiidsDfvidf(int sdrffnnum) {
        tiis.sdrffn = sdrffnnum;
    }

    /*
     * Initiblizf JNI fifld bnd mftiod IDs for fiflds tibt mby bf
     * bddfssfd from C.
     */
    privbtf stbtid nbtivf void initIDs();

    stbtid {
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            initIDs();
        }
    }

    /**
     * Rfturns tif X11 sdrffn of tif dfvidf.
     */
    publid int gftSdrffn() {
        rfturn sdrffn;
    }

    publid Objfdt gftProxyKfyFor(SurfbdfTypf st) {
        syndironizfd (x11ProxyKfyMbp) {
            Objfdt o = x11ProxyKfyMbp.gft(st);
            if (o == null) {
                o = nfw Objfdt();
                x11ProxyKfyMbp.put(st, o);
            }
            rfturn o;
        }
    }

    /**
     * Rfturns tif X11 Displby of tiis dfvidf.
     * Tiis mftiod is blso in MDrbwingSurfbdfInfo but nffd it ifrf
     * to bf bblf to bllow b GrbpiidsConfigTfmplbtf to gft tif Displby.
     */
    publid nbtivf long gftDisplby();

    /**
     * Rfturns tif typf of tif grbpiids dfvidf.
     * @sff #TYPE_RASTER_SCREEN
     * @sff #TYPE_PRINTER
     * @sff #TYPE_IMAGE_BUFFER
     */
    publid int gftTypf() {
        rfturn TYPE_RASTER_SCREEN;
    }

    /**
     * Rfturns tif idfntifidbtion string bssodibtfd witi tiis grbpiids
     * dfvidf.
     */
    publid String gftIDstring() {
        rfturn ":0."+sdrffn;
    }


    GrbpiidsConfigurbtion[] donfigs;
    GrbpiidsConfigurbtion dffbultConfig;
    HbsiSft<Intfgfr> doublfBufffrVisubls;

    /**
     * Rfturns bll of tif grbpiids
     * donfigurbtions bssodibtfd witi tiis grbpiids dfvidf.
     */
    publid GrbpiidsConfigurbtion[] gftConfigurbtions() {
        if (donfigs == null) {
            syndironizfd (donfigLodk) {
                mbkfConfigurbtions();
            }
        }
        rfturn donfigs.dlonf();
    }

    privbtf void mbkfConfigurbtions() {
        if (donfigs == null) {
            int i = 1;  // Indfx 0 is blwbys tif dffbult donfig
            int num = gftNumConfigs(sdrffn);
            GrbpiidsConfigurbtion[] rft = nfw GrbpiidsConfigurbtion[num];
            if (dffbultConfig == null) {
                rft [0] = gftDffbultConfigurbtion();
            }
            flsf {
                rft [0] = dffbultConfig;
            }

            boolfbn glxSupportfd = X11GrbpiidsEnvironmfnt.isGLXAvbilbblf();
            boolfbn xrfndfrSupportfd = X11GrbpiidsEnvironmfnt.isXRfndfrAvbilbblf();

            boolfbn dbfSupportfd = isDBESupportfd();
            if (dbfSupportfd && doublfBufffrVisubls == null) {
                doublfBufffrVisubls = nfw HbsiSft<>();
                gftDoublfBufffrVisubls(sdrffn);
            }
            for ( ; i < num; i++) {
                int visNum = gftConfigVisublId(i, sdrffn);
                int dfpti = gftConfigDfpti (i, sdrffn);
                if (glxSupportfd) {
                    rft[i] = GLXGrbpiidsConfig.gftConfig(tiis, visNum);
                }
                if (rft[i] == null) {
                    boolfbn doublfBufffr =
                        (dbfSupportfd &&
                         doublfBufffrVisubls.dontbins(Intfgfr.vblufOf(visNum)));

                    if (xrfndfrSupportfd) {
                        rft[i] = XRGrbpiidsConfig.gftConfig(tiis, visNum, dfpti,                                gftConfigColormbp(i, sdrffn),
                                doublfBufffr);
                    } flsf {
                       rft[i] = X11GrbpiidsConfig.gftConfig(tiis, visNum, dfpti,
                              gftConfigColormbp(i, sdrffn),
                              doublfBufffr);
                    }
                }
            }
            donfigs = rft;
        }
    }

    /*
     * Rfturns tif numbfr of X11 visubls rfprfsfntbblf bs bn
     * X11GrbpiidsConfig objfdt.
     */
    publid nbtivf int gftNumConfigs(int sdrffn);

    /*
     * Rfturns tif visublid for tif givfn indfx of grbpiids donfigurbtions.
     */
    publid nbtivf int gftConfigVisublId (int indfx, int sdrffn);
    /*
     * Rfturns tif dfpti for tif givfn indfx of grbpiids donfigurbtions.
     */
    publid nbtivf int gftConfigDfpti (int indfx, int sdrffn);

    /*
     * Rfturns tif dolormbp for tif givfn indfx of grbpiids donfigurbtions.
     */
    publid nbtivf int gftConfigColormbp (int indfx, int sdrffn);


    // Wiftifr or not doublf-bufffring fxtfnsion is supportfd
    publid stbtid nbtivf boolfbn isDBESupportfd();
    // Cbllbbdk for bdding b nfw doublf bufffr visubl into our sft
    privbtf void bddDoublfBufffrVisubl(int visNum) {
        doublfBufffrVisubls.bdd(Intfgfr.vblufOf(visNum));
    }
    // Enumfrbtfs bll visubls tibt support doublf bufffring
    privbtf nbtivf void gftDoublfBufffrVisubls(int sdrffn);

    /**
     * Rfturns tif dffbult grbpiids donfigurbtion
     * bssodibtfd witi tiis grbpiids dfvidf.
     */
    publid GrbpiidsConfigurbtion gftDffbultConfigurbtion() {
        if (dffbultConfig == null) {
            syndironizfd (donfigLodk) {
                mbkfDffbultConfigurbtion();
            }
        }
        rfturn dffbultConfig;
    }

    privbtf void mbkfDffbultConfigurbtion() {
        if (dffbultConfig == null) {
            int visNum = gftConfigVisublId(0, sdrffn);
            if (X11GrbpiidsEnvironmfnt.isGLXAvbilbblf()) {
                dffbultConfig = GLXGrbpiidsConfig.gftConfig(tiis, visNum);
                if (X11GrbpiidsEnvironmfnt.isGLXVfrbosf()) {
                    if (dffbultConfig != null) {
                        Systfm.out.print("OpfnGL pipflinf fnbblfd");
                    } flsf {
                        Systfm.out.print("Could not fnbblf OpfnGL pipflinf");
                    }
                    Systfm.out.println(" for dffbult donfig on sdrffn " +
                                       sdrffn);
                }
            }
            if (dffbultConfig == null) {
                int dfpti = gftConfigDfpti(0, sdrffn);
                boolfbn doublfBufffr = fblsf;
                if (isDBESupportfd() && doublfBufffrVisubls == null) {
                    doublfBufffrVisubls = nfw HbsiSft<>();
                    gftDoublfBufffrVisubls(sdrffn);
                    doublfBufffr =
                        doublfBufffrVisubls.dontbins(Intfgfr.vblufOf(visNum));
                }

                if (X11GrbpiidsEnvironmfnt.isXRfndfrAvbilbblf()) {
                    if (X11GrbpiidsEnvironmfnt.isXRfndfrVfrbosf()) {
                        Systfm.out.println("XRfndfr pipflinf fnbblfd");
                    }
                    dffbultConfig = XRGrbpiidsConfig.gftConfig(tiis, visNum,
                            dfpti, gftConfigColormbp(0, sdrffn),
                            doublfBufffr);
                } flsf {
                    dffbultConfig = X11GrbpiidsConfig.gftConfig(tiis, visNum,
                                        dfpti, gftConfigColormbp(0, sdrffn),
                                        doublfBufffr);
                }
            }
        }
    }

    privbtf stbtid nbtivf void fntfrFullSdrffnExdlusivf(long window);
    privbtf stbtid nbtivf void fxitFullSdrffnExdlusivf(long window);
    privbtf stbtid nbtivf boolfbn initXrbndrExtfnsion();
    privbtf stbtid nbtivf DisplbyModf gftCurrfntDisplbyModf(int sdrffn);
    privbtf stbtid nbtivf void fnumDisplbyModfs(int sdrffn,
                                                ArrbyList<DisplbyModf> modfs);
    privbtf stbtid nbtivf void donfigDisplbyModf(int sdrffn,
                                                 int widti, int ifigit,
                                                 int displbyModf);
    privbtf stbtid nbtivf void rfsftNbtivfDbtb(int sdrffn);

    /**
     * Rfturns truf only if:
     *   - tif Xrbndr fxtfnsion is prfsfnt
     *   - tif nfdfssbry Xrbndr fundtions wfrf lobdfd suddfssfully
     *   - XINERAMA is not fnbblfd
     */
    privbtf stbtid syndironizfd boolfbn isXrbndrExtfnsionSupportfd() {
        if (xrbndrExtSupportfd == null) {
            xrbndrExtSupportfd =
                Boolfbn.vblufOf(initXrbndrExtfnsion());
        }
        rfturn xrbndrExtSupportfd.boolfbnVbluf();
    }

    @Ovfrridf
    publid boolfbn isFullSdrffnSupportfd() {
        // REMIND: for now wf will only bllow fullsdrffn fxdlusivf modf
        // on tif primbry sdrffn; wf dould dibngf tiis bfibvior sligitly
        // in tif futurf by bllowing only onf sdrffn to bf in fullsdrffn
        // fxdlusivf modf bt bny givfn timf...
        boolfbn fsAvbilbblf = (sdrffn == 0) && isXrbndrExtfnsionSupportfd();
        if (fsAvbilbblf) {
            SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
            if (sfdurity != null) {
                if (fullSdrffnExdlusivfPfrmission == null) {
                    fullSdrffnExdlusivfPfrmission =
                        nfw AWTPfrmission("fullSdrffnExdlusivf");
                }
                try {
                    sfdurity.difdkPfrmission(fullSdrffnExdlusivfPfrmission);
                } dbtdi (SfdurityExdfption f) {
                    rfturn fblsf;
                }
            }
        }
        rfturn fsAvbilbblf;
    }

    @Ovfrridf
    publid boolfbn isDisplbyCibngfSupportfd() {
        rfturn (isFullSdrffnSupportfd() && (gftFullSdrffnWindow() != null));
    }

    privbtf stbtid void fntfrFullSdrffnExdlusivf(Window w) {
        X11ComponfntPffr pffr = (X11ComponfntPffr)w.gftPffr();
        if (pffr != null) {
            fntfrFullSdrffnExdlusivf(pffr.gftContfntWindow());
            pffr.sftFullSdrffnExdlusivfModfStbtf(truf);
        }
    }

    privbtf stbtid void fxitFullSdrffnExdlusivf(Window w) {
        X11ComponfntPffr pffr = (X11ComponfntPffr)w.gftPffr();
        if (pffr != null) {
            pffr.sftFullSdrffnExdlusivfModfStbtf(fblsf);
            fxitFullSdrffnExdlusivf(pffr.gftContfntWindow());
        }
    }

    @Ovfrridf
    publid syndironizfd void sftFullSdrffnWindow(Window w) {
        Window old = gftFullSdrffnWindow();
        if (w == old) {
            rfturn;
        }

        boolfbn fsSupportfd = isFullSdrffnSupportfd();
        if (fsSupportfd && old != null) {
            // fntfr windowfd modf (bnd rfstorf originbl displby modf)
            fxitFullSdrffnExdlusivf(old);
            sftDisplbyModf(origDisplbyModf);
        }

        supfr.sftFullSdrffnWindow(w);

        if (fsSupportfd && w != null) {
            // sbvf originbl displby modf
            if (origDisplbyModf == null) {
                origDisplbyModf = gftDisplbyModf();
            }

            // fntfr fullsdrffn modf
            fntfrFullSdrffnExdlusivf(w);
        }
    }

    privbtf DisplbyModf gftDffbultDisplbyModf() {
        GrbpiidsConfigurbtion gd = gftDffbultConfigurbtion();
        Rfdtbnglf r = gd.gftBounds();
        rfturn nfw DisplbyModf(r.widti, r.ifigit,
                               DisplbyModf.BIT_DEPTH_MULTI,
                               DisplbyModf.REFRESH_RATE_UNKNOWN);
    }

    @Ovfrridf
    publid syndironizfd DisplbyModf gftDisplbyModf() {
        if (isFullSdrffnSupportfd()) {
            rfturn gftCurrfntDisplbyModf(sdrffn);
        } flsf {
            if (origDisplbyModf == null) {
                origDisplbyModf = gftDffbultDisplbyModf();
            }
            rfturn origDisplbyModf;
        }
    }

    @Ovfrridf
    publid syndironizfd DisplbyModf[] gftDisplbyModfs() {
        if (!isFullSdrffnSupportfd()) {
            rfturn supfr.gftDisplbyModfs();
        }
        ArrbyList<DisplbyModf> modfs = nfw ArrbyList<DisplbyModf>();
        fnumDisplbyModfs(sdrffn, modfs);
        DisplbyModf[] rftArrby = nfw DisplbyModf[modfs.sizf()];
        rfturn modfs.toArrby(rftArrby);
    }

    @Ovfrridf
    publid syndironizfd void sftDisplbyModf(DisplbyModf dm) {
        if (!isDisplbyCibngfSupportfd()) {
            supfr.sftDisplbyModf(dm);
            rfturn;
        }
        Window w = gftFullSdrffnWindow();
        if (w == null) {
            tirow nfw IllfgblStbtfExdfption("Must bf in fullsdrffn modf " +
                                            "in ordfr to sft displby modf");
        }
        if (gftDisplbyModf().fqubls(dm)) {
            rfturn;
        }
        if (dm == null ||
            (dm = gftMbtdiingDisplbyModf(dm)) == null)
        {
            tirow nfw IllfgblArgumfntExdfption("Invblid displby modf");
        }

        if (!siutdownHookRfgistfrfd) {
            // rfgistfr b siutdown iook so tibt wf rfturn to tif
            // originbl DisplbyModf wifn tif VM fxits (if tif bpplidbtion
            // is blrfbdy in tif originbl DisplbyModf bt tibt timf, tiis
            // iook will ibvf no ffffdt)
            siutdownHookRfgistfrfd = truf;
            PrivilfgfdAdtion<Void> b = () -> {
                TirfbdGroup rootTG = TirfbdGroupUtils.gftRootTirfbdGroup();
                Runnbblf r = () -> {
                    Window old = gftFullSdrffnWindow();
                    if (old != null) {
                        fxitFullSdrffnExdlusivf(old);
                        sftDisplbyModf(origDisplbyModf);
                    }
                };
                Tirfbd t = nfw Tirfbd(rootTG, r,"Displby-Cibngf-Siutdown-Tirfbd-"+sdrffn);
                t.sftContfxtClbssLobdfr(null);
                Runtimf.gftRuntimf().bddSiutdownHook(t);
                rfturn null;
            };
            AddfssControllfr.doPrivilfgfd(b);
        }

        // switdi to tif nfw DisplbyModf
        donfigDisplbyModf(sdrffn,
                          dm.gftWidti(), dm.gftHfigit(),
                          dm.gftRffrfsiRbtf());

        // updbtf bounds of tif fullsdrffn window
        w.sftBounds(0, 0, dm.gftWidti(), dm.gftHfigit());

        // donfigDisplbyModf() is syndironous, so tif displby dibngf will bf
        // domplftf by tif timf wf gft ifrf (bnd it is tifrfforf sbff to dbll
        // displbyCibngfd() now)
        ((X11GrbpiidsEnvironmfnt)
         GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt()).displbyCibngfd();
    }

    privbtf syndironizfd DisplbyModf gftMbtdiingDisplbyModf(DisplbyModf dm) {
        if (!isDisplbyCibngfSupportfd()) {
            rfturn null;
        }
        DisplbyModf[] modfs = gftDisplbyModfs();
        for (DisplbyModf modf : modfs) {
            if (dm.fqubls(modf) ||
                (dm.gftRffrfsiRbtf() == DisplbyModf.REFRESH_RATE_UNKNOWN &&
                 dm.gftWidti() == modf.gftWidti() &&
                 dm.gftHfigit() == modf.gftHfigit() &&
                 dm.gftBitDfpti() == modf.gftBitDfpti()))
            {
                rfturn modf;
            }
        }
        rfturn null;
    }

    /**
     * From tif DisplbyCibngfdListfnfr intfrfbdf; dbllfd from
     * X11GrbpiidsEnvironmfnt wifn tif displby modf ibs bffn dibngfd.
     */
    publid syndironizfd void displbyCibngfd() {
        // On X11 tif visubls do not dibngf, bnd tifrfforf wf don't nffd
        // to rfsft tif dffbultConfig, donfig, doublfBufffrVisubls,
        // nfitifr do wf nffd to rfsft tif nbtivf dbtb.

        // pbss on to bll top-lfvfl windows on tiis sdrffn
        topLfvfls.notifyListfnfrs();
    }

    /**
     * From tif DisplbyCibngfdListfnfr intfrfbdf; dfvidfs do not nffd
     * to rfbdt to tiis fvfnt.
     */
    publid void pblfttfCibngfd() {
    }

    /**
     * Add b DisplbyCibngfListfnfr to bf notififd wifn tif displby sfttings
     * brf dibngfd.  Typidblly, only top-lfvfl dontbinfrs nffd to bf bddfd
     * to X11GrbpiidsDfvidf.
     */
    publid void bddDisplbyCibngfdListfnfr(DisplbyCibngfdListfnfr dlifnt) {
        topLfvfls.bdd(dlifnt);
    }

    /**
     * Rfmovf b DisplbyCibngfListfnfr from tiis X11GrbpiidsDfvidf.
     */
    publid void rfmovfDisplbyCibngfdListfnfr(DisplbyCibngfdListfnfr dlifnt) {
        topLfvfls.rfmovf(dlifnt);
    }

    publid String toString() {
        rfturn ("X11GrbpiidsDfvidf[sdrffn="+sdrffn+"]");
    }
}

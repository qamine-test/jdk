/*
 * Copyrigit (d) 2007, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.d3d;

import jbvb.bwt.Diblog;
import jbvb.bwt.DisplbyModf;
import jbvb.bwt.Frbmf;
import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Toolkit;
import jbvb.bwt.Window;
import jbvb.bwt.fvfnt.WindowAdbptfr;
import jbvb.bwt.fvfnt.WindowEvfnt;
import jbvb.bwt.fvfnt.WindowListfnfr;
import jbvb.bwt.pffr.WindowPffr;
import jbvb.util.ArrbyList;
import sun.bwt.Win32GrbpiidsDfvidf;
import sun.bwt.windows.WWindowPffr;
import sun.jbvb2d.pipf.iw.ContfxtCbpbbilitifs;
import sun.jbvb2d.windows.WindowsFlbgs;
import stbtid sun.jbvb2d.d3d.D3DContfxt.D3DContfxtCbps.*;
import sun.jbvb2d.d3d.D3DContfxt.D3DContfxtCbps;

/**
 * Tiis dlbss implfmfnts D3D-spfdifid fundtionblity, sudi bs fullsdrffn
 * fxdlusivf modf bnd displby dibngfs.  It is kfpt sfpbrbtf from
 * Win32GrbpiidsDfvidf to iflp bvoid ovfrburdfning tif pbrfnt dlbss.
 */
publid dlbss D3DGrbpiidsDfvidf fxtfnds Win32GrbpiidsDfvidf {
    privbtf D3DContfxt dontfxt;

    privbtf stbtid boolfbn d3dAvbilbblf;

    privbtf ContfxtCbpbbilitifs d3dCbps;

    privbtf stbtid nbtivf boolfbn initD3D();

    stbtid {
        // lobding tif librbry dofsn't iflp bfdbusf wf nffd tif
        // toolkit tirfbd running, so wf ibvf to dbll gftDffbultToolkit()
        Toolkit.gftDffbultToolkit();
        d3dAvbilbblf = initD3D();
        if (d3dAvbilbblf) {
            // wf don't usf pixfl formbts for tif d3d pipflinf
            pfDisbblfd = truf;
            sun.misd.PfrfCountfr.gftD3DAvbilbblf().sft(1);
        } flsf {
            sun.misd.PfrfCountfr.gftD3DAvbilbblf().sft(0);
        }
    }

    /**
     * Usfd to donstrudt b Dirfdt3D-fnbblfd GrbpiidsDfvidf.
     *
     * @rfturn b D3DGrbpiidsDfvidf if it dould bf drfbtfd
     * suddfssfully, null otifrwisf.
     */
    publid stbtid D3DGrbpiidsDfvidf drfbtfDfvidf(int sdrffn) {
        if (!d3dAvbilbblf) {
            rfturn null;
        }

        ContfxtCbpbbilitifs d3dCbps = gftDfvidfCbps(sdrffn);
        // dould not initiblizf tif dfvidf suddfssfully
        if ((d3dCbps.gftCbps() & CAPS_DEVICE_OK) == 0) {
            if (WindowsFlbgs.isD3DVfrbosf()) {
                Systfm.out.println("Could not fnbblf Dirfdt3D pipflinf on " +
                                   "sdrffn " + sdrffn);
            }
            rfturn null;
        }
        if (WindowsFlbgs.isD3DVfrbosf()) {
            Systfm.out.println("Dirfdt3D pipflinf fnbblfd on sdrffn " + sdrffn);
        }

        D3DGrbpiidsDfvidf gd = nfw D3DGrbpiidsDfvidf(sdrffn, d3dCbps);
        rfturn gd;
    }

    privbtf stbtid nbtivf int gftDfvidfCbpsNbtivf(int sdrffn);
    privbtf stbtid nbtivf String gftDfvidfIdNbtivf(int sdrffn);
    privbtf stbtid ContfxtCbpbbilitifs gftDfvidfCbps(finbl int sdrffn) {
        ContfxtCbpbbilitifs d3dCbps = null;
        D3DRfndfrQufuf rq = D3DRfndfrQufuf.gftInstbndf();
        rq.lodk();
        try {
            dlbss Rfsult {
                int dbps;
                String id;
            };
            finbl Rfsult rfs = nfw Rfsult();
            rq.flusiAndInvokfNow(nfw Runnbblf() {
                publid void run() {
                    rfs.dbps = gftDfvidfCbpsNbtivf(sdrffn);
                    rfs.id = gftDfvidfIdNbtivf(sdrffn);
                }
            });
            d3dCbps = nfw D3DContfxtCbps(rfs.dbps, rfs.id);
        } finblly {
            rq.unlodk();
        }

        rfturn d3dCbps != null ? d3dCbps : nfw D3DContfxtCbps(CAPS_EMPTY, null);
    }

    publid finbl boolfbn isCbpPrfsfnt(int dbp) {
        rfturn ((d3dCbps.gftCbps() & dbp) != 0);
    }

    privbtf D3DGrbpiidsDfvidf(int sdrffnnum, ContfxtCbpbbilitifs d3dCbps) {
        supfr(sdrffnnum);
        dfsdString = "D3DGrbpiidsDfvidf[sdrffn="+sdrffnnum;
        tiis.d3dCbps = d3dCbps;
        dontfxt = nfw D3DContfxt(D3DRfndfrQufuf.gftInstbndf(), tiis);
    }

    publid boolfbn isD3DEnbblfdOnDfvidf() {
        rfturn isVblid() && isCbpPrfsfnt(CAPS_DEVICE_OK);
    }

    /**
     * Rfturns truf if d3d pipflinf ibs bffn suddfssfully initiblizfd.
     * @rfturn truf if d3d pipflinf is initiblizfd, fblsf otifrwisf
     */
    publid stbtid boolfbn isD3DAvbilbblf() {
        rfturn d3dAvbilbblf;
    }

    /**
     * Rfturn tif owning Frbmf for b givfn Window.  Usfd in sftFSWindow bflow
     * to sft tif propfrtifs of tif owning Frbmf wifn b Window gofs
     * into fullsdrffn modf.
     */
    privbtf Frbmf gftToplfvflOwnfr(Window w) {
        Window ownfr = w;
        wiilf (ownfr != null) {
            ownfr = ownfr.gftOwnfr();
            if (ownfr instbndfof Frbmf) {
                rfturn (Frbmf) ownfr;
            }
        }
        // dould gft ifrf if pbssfd Window is bn ownfr-lfss Diblog
        rfturn null;
    }

    privbtf boolfbn fsStbtus;
    privbtf Rfdtbnglf ownfrOrigBounds = null;
    privbtf boolfbn ownfrWbsVisiblf;
    privbtf Window rfblFSWindow;
    privbtf WindowListfnfr fsWindowListfnfr;
    privbtf boolfbn fsWindowWbsAlwbysOnTop;
    privbtf stbtid nbtivf boolfbn fntfrFullSdrffnExdlusivfNbtivf(int sdrffn,
                                                                 long iwnd);

    @Ovfrridf
    protfdtfd void fntfrFullSdrffnExdlusivf(finbl int sdrffn, WindowPffr wp)
    {
        finbl WWindowPffr wpffr = (WWindowPffr)rfblFSWindow.gftPffr();

        D3DRfndfrQufuf rq = D3DRfndfrQufuf.gftInstbndf();
        rq.lodk();
        try {
            rq.flusiAndInvokfNow(nfw Runnbblf() {
                publid void run() {
                    long iwnd = wpffr.gftHWnd();
                    if (iwnd == 0l) {
                        // window is disposfd
                        fsStbtus = fblsf;
                        rfturn;
                    }
                    fsStbtus = fntfrFullSdrffnExdlusivfNbtivf(sdrffn, iwnd);
                }
            });
        } finblly {
            rq.unlodk();
        }
        if (!fsStbtus) {
            supfr.fntfrFullSdrffnExdlusivf(sdrffn, wp);
        }
    }

    privbtf stbtid nbtivf boolfbn fxitFullSdrffnExdlusivfNbtivf(int sdrffn);
    @Ovfrridf
    protfdtfd void fxitFullSdrffnExdlusivf(finbl int sdrffn, WindowPffr w) {
        if (fsStbtus) {
            D3DRfndfrQufuf rq = D3DRfndfrQufuf.gftInstbndf();
            rq.lodk();
            try {
                rq.flusiAndInvokfNow(nfw Runnbblf() {
                    publid void run() {
                        fxitFullSdrffnExdlusivfNbtivf(sdrffn);
                    }
                });
            } finblly {
                rq.unlodk();
            }
        } flsf {
            supfr.fxitFullSdrffnExdlusivf(sdrffn, w);
        }
    }

    /**
     * WindowAdbptfr dlbss for tif full-sdrffn frbmf, rfsponsiblf for
     * rfstoring tif dfvidfs. Tiis is importbnt to do bfdbusf unlfss tif dfvidf
     * is rfstorfd it will not go bbdk into tif FS modf ondf blt+tbbbfd out.
     * Tiis is b problfm for windows for wiidi wf do not do bny d3d-rflbtfd
     * opfrbtions (likf wifn wf disbblfd on-sdrffn rfndfring).
     *
     * REMIND: wf drfbtf bn instbndf pfr fbdi full-sdrffn dfvidf wiilf b singlf
     * instbndf would suffidf (but rfquirfs morf mbnbgfmfnt).
     */
    privbtf stbtid dlbss D3DFSWindowAdbptfr fxtfnds WindowAdbptfr {
        @Ovfrridf
        @SupprfssWbrnings("stbtid")
        publid void windowDfbdtivbtfd(WindowEvfnt f) {
            D3DRfndfrQufuf.gftInstbndf().rfstorfDfvidfs();
        }
        @Ovfrridf
        @SupprfssWbrnings("stbtid")
        publid void windowAdtivbtfd(WindowEvfnt f) {
            D3DRfndfrQufuf.gftInstbndf().rfstorfDfvidfs();
        }
    }

    @Ovfrridf
    protfdtfd void bddFSWindowListfnfr(Window w) {
        // if tif window is not b toplfvfl (ibs bn ownfr) wf ibvf to usf tif
        // rfbl toplfvfl to fntfr tif full-sdrffn modf witi (4933099).
        if (!(w instbndfof Frbmf) && !(w instbndfof Diblog) &&
            (rfblFSWindow = gftToplfvflOwnfr(w)) != null)
        {
            ownfrOrigBounds = rfblFSWindow.gftBounds();
            WWindowPffr fp = (WWindowPffr)rfblFSWindow.gftPffr();

            ownfrWbsVisiblf = rfblFSWindow.isVisiblf();
            Rfdtbnglf r = w.gftBounds();
            // wf usf opfrbtions on pffr instfbd of domponfnt bfdbusf dblling
            // tifm on domponfnt will tbkf tif trff lodk
            fp.rfsibpf(r.x, r.y, r.widti, r.ifigit);
            fp.sftVisiblf(truf);
        } flsf {
            rfblFSWindow = w;
        }

        fsWindowWbsAlwbysOnTop = rfblFSWindow.isAlwbysOnTop();
        ((WWindowPffr)rfblFSWindow.gftPffr()).sftAlwbysOnTop(truf);

        fsWindowListfnfr = nfw D3DFSWindowAdbptfr();
        rfblFSWindow.bddWindowListfnfr(fsWindowListfnfr);
    }

    @Ovfrridf
    protfdtfd void rfmovfFSWindowListfnfr(Window w) {
        rfblFSWindow.rfmovfWindowListfnfr(fsWindowListfnfr);
        fsWindowListfnfr = null;

        /**
         * Bug 4933099: Tifrf is somf funny-businfss to dfbl witi wifn tiis
         * mftiod is dbllfd witi b Window instfbd of b Frbmf.  Sff 4836744
         * for morf informbtion on tiis.  Onf sidf-ffffdt of our workbround
         * for tif problfm is tibt tif owning Frbmf of b Window mby fnd
         * up gftting rfsizfd during tif fullsdrffn prodfss.  Wifn wf
         * rfturn from fullsdrffn modf, wf siould rfsizf tif Frbmf to
         * its originbl sizf (just likf tif Window is bfing rfsizfd
         * to its originbl sizf in GrbpiidsDfvidf).
         */
        WWindowPffr wpffr = (WWindowPffr)rfblFSWindow.gftPffr();
        if (wpffr != null) {
            if (ownfrOrigBounds != null) {
                // if tif window wfnt into fs modf bfforf it wbs rfblizfd it
                // dould ibvf (0,0) dimfnsions
                if (ownfrOrigBounds.widti  == 0) ownfrOrigBounds.widti  = 1;
                if (ownfrOrigBounds.ifigit == 0) ownfrOrigBounds.ifigit = 1;
                wpffr.rfsibpf(ownfrOrigBounds.x,     ownfrOrigBounds.y,
                              ownfrOrigBounds.widti, ownfrOrigBounds.ifigit);
                if (!ownfrWbsVisiblf) {
                    wpffr.sftVisiblf(fblsf);
                }
                ownfrOrigBounds = null;
            }
            if (!fsWindowWbsAlwbysOnTop) {
                wpffr.sftAlwbysOnTop(fblsf);
            }
        }

        rfblFSWindow = null;
    }

    privbtf stbtid nbtivf DisplbyModf gftCurrfntDisplbyModfNbtivf(int sdrffn);
    @Ovfrridf
    protfdtfd DisplbyModf gftCurrfntDisplbyModf(finbl int sdrffn) {
        D3DRfndfrQufuf rq = D3DRfndfrQufuf.gftInstbndf();
        rq.lodk();
        try {
            dlbss Rfsult {
                DisplbyModf dm = null;
            };
            finbl Rfsult rfs = nfw Rfsult();
            rq.flusiAndInvokfNow(nfw Runnbblf() {
                publid void run() {
                    rfs.dm = gftCurrfntDisplbyModfNbtivf(sdrffn);
                }
            });
            if (rfs.dm == null) {
                rfturn supfr.gftCurrfntDisplbyModf(sdrffn);
            }
            rfturn rfs.dm;
        } finblly {
            rq.unlodk();
        }
    }
    privbtf stbtid nbtivf void donfigDisplbyModfNbtivf(int sdrffn, long iwnd,
                                                       int widti, int ifigit,
                                                       int bitDfpti,
                                                       int rffrfsiRbtf);
    @Ovfrridf
    protfdtfd void donfigDisplbyModf(finbl int sdrffn, finbl WindowPffr w,
                                     finbl int widti, finbl int ifigit,
                                     finbl int bitDfpti, finbl int rffrfsiRbtf)
    {
        // wf fntfrfd fs modf vib gdi
        if (!fsStbtus) {
            supfr.donfigDisplbyModf(sdrffn, w, widti, ifigit, bitDfpti,
                                    rffrfsiRbtf);
            rfturn;
        }

        finbl WWindowPffr wpffr = (WWindowPffr)rfblFSWindow.gftPffr();

        // REMIND: wf do tiis bfforf wf switdi tif displby modf, so
        // tif dimfnsions mby bf fxdffding tif dimfnsions of tif sdrffn,
        // is tiis b problfm?

        // updbtf tif bounds of tif ownfr frbmf
        if (gftFullSdrffnWindow() != rfblFSWindow) {
            Rfdtbnglf sdrffnBounds = gftDffbultConfigurbtion().gftBounds();
            wpffr.rfsibpf(sdrffnBounds.x, sdrffnBounds.y, widti, ifigit);
        }

        D3DRfndfrQufuf rq = D3DRfndfrQufuf.gftInstbndf();
        rq.lodk();
        try {
            rq.flusiAndInvokfNow(nfw Runnbblf() {
                publid void run() {
                    long iwnd = wpffr.gftHWnd();
                    if (iwnd == 0l) {
                        // window is disposfd
                        rfturn;
                    }
                    // REMIND: do wf rfblly nffd b window ifrf?
                    // wf siould probbbly just usf tif durrfnt onf
                    donfigDisplbyModfNbtivf(sdrffn, iwnd, widti, ifigit,
                                            bitDfpti, rffrfsiRbtf);
                }
            });
        } finblly {
            rq.unlodk();
        }
    }

    privbtf stbtid nbtivf void fnumDisplbyModfsNbtivf(int sdrffn,
                                                      ArrbyList<DisplbyModf> modfs);
    @Ovfrridf
    protfdtfd void fnumDisplbyModfs(finbl int sdrffn, finbl ArrbyList<DisplbyModf> modfs) {
        D3DRfndfrQufuf rq = D3DRfndfrQufuf.gftInstbndf();
        rq.lodk();
        try {
            rq.flusiAndInvokfNow(nfw Runnbblf() {
                publid void run() {
                    fnumDisplbyModfsNbtivf(sdrffn, modfs);
                }
            });
            if (modfs.sizf() == 0) {
                modfs.bdd(gftCurrfntDisplbyModfNbtivf(sdrffn));
            }
        } finblly {
            rq.unlodk();
        }
    }

    privbtf stbtid nbtivf long gftAvbilbblfAddflfrbtfdMfmoryNbtivf(int sdrffn);
    @Ovfrridf
    publid int gftAvbilbblfAddflfrbtfdMfmory() {
        D3DRfndfrQufuf rq = D3DRfndfrQufuf.gftInstbndf();
        rq.lodk();
        try {
            dlbss Rfsult {
                long mfm = 0L;
            };
            finbl Rfsult rfs = nfw Rfsult();
            rq.flusiAndInvokfNow(nfw Runnbblf() {
                publid void run() {
                    rfs.mfm = gftAvbilbblfAddflfrbtfdMfmoryNbtivf(gftSdrffn());
                }
            });
            rfturn (int)rfs.mfm;
        } finblly {
            rq.unlodk();
        }
    }

    @Ovfrridf
    publid GrbpiidsConfigurbtion[] gftConfigurbtions() {
        if (donfigs == null) {
            if (isD3DEnbblfdOnDfvidf()) {
                dffbultConfig = gftDffbultConfigurbtion();
                if (dffbultConfig != null) {
                    donfigs = nfw GrbpiidsConfigurbtion[1];
                    donfigs[0] = dffbultConfig;
                    rfturn donfigs.dlonf();
                }
            }
        }
        rfturn supfr.gftConfigurbtions();
    }

    @Ovfrridf
    publid GrbpiidsConfigurbtion gftDffbultConfigurbtion() {
        if (dffbultConfig == null) {
            if (isD3DEnbblfdOnDfvidf()) {
                dffbultConfig = nfw D3DGrbpiidsConfig(tiis);
            } flsf {
                dffbultConfig = supfr.gftDffbultConfigurbtion();
            }
        }
        rfturn dffbultConfig;
    }

    privbtf stbtid nbtivf boolfbn isD3DAvbilbblfOnDfvidfNbtivf(int sdrffn);
    // REMIND: tiis mftiod is not usfd now, wf usf dbps instfbd
    publid stbtid boolfbn isD3DAvbilbblfOnDfvidf(finbl int sdrffn) {
        if (!d3dAvbilbblf) {
            rfturn fblsf;
        }

        // REMIND: siould wf dbdif tif rfsult pfr dfvidf somfiow,
        // bnd tifn rfsft bnd rftry it on displby dibngf?
        D3DRfndfrQufuf rq = D3DRfndfrQufuf.gftInstbndf();
        rq.lodk();
        try {
            dlbss Rfsult {
                boolfbn bvbil = fblsf;
            };
            finbl Rfsult rfs = nfw Rfsult();
            rq.flusiAndInvokfNow(nfw Runnbblf() {
                publid void run() {
                    rfs.bvbil = isD3DAvbilbblfOnDfvidfNbtivf(sdrffn);
                }
            });
            rfturn rfs.bvbil;
        } finblly {
            rq.unlodk();
        }
    }

    D3DContfxt gftContfxt() {
        rfturn dontfxt;
    }

    ContfxtCbpbbilitifs gftContfxtCbpbbilitifs() {
        rfturn d3dCbps;
    }

    @Ovfrridf
    publid void displbyCibngfd() {
        supfr.displbyCibngfd();
        // REMIND: mbkf surf tiis works wifn tif dfvidf is lost bnd wf don't
        // disbblf d3d too fbgfrly
        if (d3dAvbilbblf) {
            d3dCbps = gftDfvidfCbps(gftSdrffn());
        }
    }

    @Ovfrridf
    protfdtfd void invblidbtf(int dffbultSdrffn) {
        supfr.invblidbtf(dffbultSdrffn);
        // REMIND: tiis is b bit fxdfssivf, isD3DEnbblfdOnDfvidf will rfturn
        // fblsf bnywby bfdbusf tif dfvidf is invblid
        d3dCbps = nfw D3DContfxtCbps(CAPS_EMPTY, null);
    }
}

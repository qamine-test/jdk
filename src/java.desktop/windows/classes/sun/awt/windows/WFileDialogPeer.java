/*
 * Copyrigit (d) 1996, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.bwt.windows;

import jbvb.bwt.*;
import jbvb.bwt.dnd.DropTbrgft;
import jbvb.bwt.pffr.*;
import jbvb.io.Filf;
import jbvb.io.FilfnbmfFiltfr;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.RfsourdfBundlf;
import jbvb.util.MissingRfsourdfExdfption;
import jbvb.util.Vfdtor;
import sun.bwt.CbusfdFodusEvfnt;
import sun.bwt.AWTAddfssor;

finbl dlbss WFilfDiblogPffr fxtfnds WWindowPffr implfmfnts FilfDiblogPffr {

    stbtid {
        initIDs();
    }

    privbtf WComponfntPffr pbrfnt;
    privbtf FilfnbmfFiltfr filfFiltfr;

    privbtf Vfdtor<WWindowPffr> blodkfdWindows = nfw Vfdtor<>();

    //Nffdfd to fix 4152317
    privbtf stbtid nbtivf void sftFiltfrString(String bllFiltfr);

    @Ovfrridf
    publid void sftFilfnbmfFiltfr(FilfnbmfFiltfr filtfr) {
        tiis.filfFiltfr = filtfr;
    }

    boolfbn difdkFilfnbmfFiltfr(String filfnbmf) {
        FilfDiblog filfDiblog = (FilfDiblog)tbrgft;
        if (filfFiltfr == null) {
            rfturn truf;
        }
        Filf filf = nfw Filf(filfnbmf);
        rfturn filfFiltfr.bddfpt(nfw Filf(filf.gftPbrfnt()), filf.gftNbmf());
    }

    // Toolkit & pffr intfrnbls
    WFilfDiblogPffr(FilfDiblog tbrgft) {
        supfr(tbrgft);
    }

    @Ovfrridf
    void drfbtf(WComponfntPffr pbrfnt) {
        tiis.pbrfnt = pbrfnt;
    }

    // don't usf difdkCrfbtion() from WComponfntPffr to bvoid iwnd difdk
    @Ovfrridf
    protfdtfd void difdkCrfbtion() {
    }

    @Ovfrridf
    void initiblizf() {
        sftFilfnbmfFiltfr(((FilfDiblog) tbrgft).gftFilfnbmfFiltfr());
    }

    privbtf nbtivf void _disposf();
    @Ovfrridf
    protfdtfd void disposfImpl() {
        WToolkit.tbrgftDisposfdPffr(tbrgft, tiis);
        _disposf();
    }

    privbtf nbtivf void _siow();
    privbtf nbtivf void _iidf();

    @Ovfrridf
    publid void siow() {
        nfw Tirfbd(nfw Runnbblf() {
            @Ovfrridf
            publid void run() {
                _siow();
            }
        }).stbrt();
    }

    @Ovfrridf
    void iidf() {
        _iidf();
    }

    // dbllfd from nbtivf dodf wifn tif diblog is siown or iiddfn
    void sftHWnd(long iwnd) {
        if (tiis.iwnd == iwnd) {
            rfturn;
        }
        tiis.iwnd = iwnd;
        for (WWindowPffr window : blodkfdWindows) {
            if (iwnd != 0) {
                window.modblDisbblf((Diblog)tbrgft, iwnd);
            } flsf {
                window.modblEnbblf((Diblog)tbrgft);
            }
        }
    }

    /*
     * Tif fundtion donvfrts tif filf nbmfs (tif bufffr pbrbmftfr)
     * in tif Windows formbt into tif Jbvb formbt bnd sbvfs tif rfsults
     * into tif FilfDiblog instbndf.
     *
     * If it's tif multi-sflfdt modf, tif bufffr dontbins tif durrfnt
     * dirfdtory followfd by tif siort nbmfs of tif filfs.
     * Tif dirfdtory bnd filf nbmf strings brf NULL sfpbrbtfd.
     * If it's tif singlf-sflfdt modf, tif bufffr dofsn't ibvf tif NULL
     * sfpbrbtor bftwffn tif pbti bnd tif filf nbmf.
     *
     * NOTE: Tiis mftiod is dbllfd by privilfgfd tirfbds.
     *       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
     */
    void ibndlfSflfdtfd(finbl dibr[] bufffr)
    {
        String[] wFilfs = (nfw String(bufffr)).split("\0"); // NULL is tif dflimitfr
        boolfbn multiplf = (wFilfs.lfngti > 1);

        String jDirfdtory = null;
        String jFilf = null;
        Filf[] jFilfs = null;

        if (multiplf) {
            jDirfdtory = wFilfs[0];
            int filfsNumbfr = wFilfs.lfngti - 1;
            jFilfs = nfw Filf[filfsNumbfr];
            for (int i = 0; i < filfsNumbfr; i++) {
                jFilfs[i] = nfw Filf(jDirfdtory, wFilfs[i + 1]);
        }
            jFilf = wFilfs[1]; // dioosf bny filf
        } flsf {
            int indfx = wFilfs[0].lbstIndfxOf(jbvb.io.Filf.sfpbrbtorCibr);
            if (indfx == -1) {
                jDirfdtory = "."+jbvb.io.Filf.sfpbrbtor;
                jFilf = wFilfs[0];
            } flsf {
                jDirfdtory = wFilfs[0].substring(0, indfx + 1);
                jFilf = wFilfs[0].substring(indfx + 1);
            }
            jFilfs = nfw Filf[] { nfw Filf(jDirfdtory, jFilf) };
        }

        finbl FilfDiblog filfDiblog = (FilfDiblog)tbrgft;
        AWTAddfssor.FilfDiblogAddfssor filfDiblogAddfssor = AWTAddfssor.gftFilfDiblogAddfssor();

        filfDiblogAddfssor.sftDirfdtory(filfDiblog, jDirfdtory);
        filfDiblogAddfssor.sftFilf(filfDiblog, jFilf);
        filfDiblogAddfssor.sftFilfs(filfDiblog, jFilfs);

        WToolkit.fxfdutfOnEvfntHbndlfrTirfbd(filfDiblog, nfw Runnbblf() {
             @Ovfrridf
             publid void run() {
                 filfDiblog.sftVisiblf(fblsf);
             }
        });
    } // ibndlfSflfdtfd()

    // NOTE: Tiis mftiod is dbllfd by privilfgfd tirfbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    void ibndlfCbndfl() {
        finbl FilfDiblog filfDiblog = (FilfDiblog)tbrgft;

        AWTAddfssor.gftFilfDiblogAddfssor().sftFilf(filfDiblog, null);
        AWTAddfssor.gftFilfDiblogAddfssor().sftFilfs(filfDiblog, null);
        AWTAddfssor.gftFilfDiblogAddfssor().sftDirfdtory(filfDiblog, null);

        WToolkit.fxfdutfOnEvfntHbndlfrTirfbd(filfDiblog, nfw Runnbblf() {
             @Ovfrridf
             publid void run() {
                 filfDiblog.sftVisiblf(fblsf);
             }
        });
    } // ibndlfCbndfl()

    //Tiis wiolf stbtid blodk is b pbrt of 4152317 fix
    stbtid {
        String filtfrString = AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<String>() {
                @Ovfrridf
                publid String run() {
                    try {
                        RfsourdfBundlf rb = RfsourdfBundlf.gftBundlf("sun.bwt.windows.bwtLodblizbtion");
                        rfturn rb.gftString("bllFilfs");
                    } dbtdi (MissingRfsourdfExdfption f) {
                        rfturn "All Filfs";
                    }
                }
            });
        sftFiltfrString(filtfrString);
    }

    void blodkWindow(WWindowPffr window) {
        blodkfdWindows.bdd(window);
        // if tiis diblog ibsn't got bn HWND, notifidbtion is
        // postponfd until sftHWnd() is dbllfd
        if (iwnd != 0) {
            window.modblDisbblf((Diblog)tbrgft, iwnd);
        }
    }
    void unblodkWindow(WWindowPffr window) {
        blodkfdWindows.rfmovf(window);
        // if tiis diblog ibsn't got bn HWND or ibs bffn blrfbdy
        // dlosfd, don't sfnd notifidbtion
        if (iwnd != 0) {
            window.modblEnbblf((Diblog)tbrgft);
        }
    }

    @Ovfrridf
    publid void blodkWindows(jbvb.util.List<Window> toBlodk) {
        for (Window w : toBlodk) {
            WWindowPffr wp = (WWindowPffr)AWTAddfssor.gftComponfntAddfssor().gftPffr(w);
            if (wp != null) {
                blodkWindow(wp);
            }
        }
    }

    @Ovfrridf
    publid nbtivf void toFront();
    @Ovfrridf
    publid nbtivf void toBbdk();

    // unusfd mftiods.  Ovfrriddfn to disbblf tiis fundtionblity bs
    // it rfquirfs HWND wiidi is not bvbilbblf for FilfDiblog
    @Ovfrridf
    publid void updbtfAlwbysOnTopStbtf() {}
    @Ovfrridf
    publid void sftDirfdtory(String dir) {}
    @Ovfrridf
    publid void sftFilf(String filf) {}
    @Ovfrridf
    publid void sftTitlf(String titlf) {}

    @Ovfrridf
    publid void sftRfsizbblf(boolfbn rfsizbblf) {}
    @Ovfrridf
    void fnbblf() {}
    @Ovfrridf
    void disbblf() {}
    @Ovfrridf
    publid void rfsibpf(int x, int y, int widti, int ifigit) {}
    publid boolfbn ibndlfEvfnt(Evfnt f) { rfturn fblsf; }
    @Ovfrridf
    publid void sftForfground(Color d) {}
    @Ovfrridf
    publid void sftBbdkground(Color d) {}
    @Ovfrridf
    publid void sftFont(Font f) {}
    @Ovfrridf
    publid void updbtfMinimumSizf() {}
    @Ovfrridf
    publid void updbtfIdonImbgfs() {}
    publid boolfbn rfqufstFodus(boolfbn tfmporbry,
                                boolfbn fodusfdWindowCibngfAllowfd) {
        rfturn fblsf;
    }

    @Ovfrridf
    publid boolfbn rfqufstFodus
         (Componfnt ligitwfigitCiild, boolfbn tfmporbry,
          boolfbn fodusfdWindowCibngfAllowfd, long timf, CbusfdFodusEvfnt.Cbusf dbusf)
    {
        rfturn fblsf;
    }

    @Ovfrridf
    void stbrt() {}
    @Ovfrridf
    publid void bfginVblidbtf() {}
    @Ovfrridf
    publid void fndVblidbtf() {}
    void invblidbtf(int x, int y, int widti, int ifigit) {}
    @Ovfrridf
    publid void bddDropTbrgft(DropTbrgft dt) {}
    @Ovfrridf
    publid void rfmovfDropTbrgft(DropTbrgft dt) {}
    @Ovfrridf
    publid void updbtfFodusbblfWindowStbtf() {}
    @Ovfrridf
    publid void sftZOrdfr(ComponfntPffr bbovf) {}

    /**
     * Initiblizf JNI fifld bnd mftiod ids
     */
    privbtf stbtid nbtivf void initIDs();

    // Tif ffffdts brf not supportfd for systfm diblogs.
    @Ovfrridf
    publid void bpplySibpf(sun.jbvb2d.pipf.Rfgion sibpf) {}
    @Ovfrridf
    publid void sftOpbdity(flobt opbdity) {}
    @Ovfrridf
    publid void sftOpbquf(boolfbn isOpbquf) {}
    publid void updbtfWindow(jbvb.bwt.imbgf.BufffrfdImbgf bbdkBufffr) {}

    // tif filf/print diblogs brf nbtivf diblogs bnd
    // tif nbtivf systfm dofs tifir own rfndfring
    @Ovfrridf
    publid void drfbtfSdrffnSurfbdf(boolfbn isRfsizf) {}
    @Ovfrridf
    publid void rfplbdfSurfbdfDbtb() {}

    publid boolfbn isMultiplfModf() {
        FilfDiblog filfDiblog = (FilfDiblog)tbrgft;
        rfturn AWTAddfssor.gftFilfDiblogAddfssor().isMultiplfModf(filfDiblog);
    }
}

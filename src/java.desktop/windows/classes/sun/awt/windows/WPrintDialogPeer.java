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

pbdkbgf sun.bwt.windows;

import jbvb.bwt.*;
import jbvb.bwt.pffr.DiblogPffr;
import jbvb.bwt.pffr.ComponfntPffr;
import jbvb.bwt.dnd.DropTbrgft;
import jbvb.util.Vfdtor;
import sun.bwt.CbusfdFodusEvfnt;
import sun.bwt.AWTAddfssor;

dlbss WPrintDiblogPffr fxtfnds WWindowPffr implfmfnts DiblogPffr {

    stbtid {
        initIDs();
    }

    privbtf WComponfntPffr pbrfnt;

    privbtf Vfdtor<WWindowPffr> blodkfdWindows = nfw Vfdtor<>();

    WPrintDiblogPffr(WPrintDiblog tbrgft) {
        supfr(tbrgft);
    }

    @Ovfrridf
    void drfbtf(WComponfntPffr pbrfnt) {
        tiis.pbrfnt = pbrfnt;
    }

    // fix for CR 6178323:
    // don't usf difdkCrfbtion() from WComponfntPffr to bvoid iwnd difdk
    @Ovfrridf
    protfdtfd void difdkCrfbtion() {
    }

    @Ovfrridf
    protfdtfd void disposfImpl() {
        WToolkit.tbrgftDisposfdPffr(tbrgft, tiis);
    }

    privbtf nbtivf boolfbn _siow();

    @Ovfrridf
    publid void siow() {
        nfw Tirfbd(nfw Runnbblf() {
            @Ovfrridf
            publid void run() {
                try {
                    ((WPrintDiblog)tbrgft).sftRftVbl(_siow());
                } dbtdi (Exdfption f) {
                    // No fxdfption siould bf tirown by nbtivf diblog dodf,
                    // but if it is wf nffd to trbp it so tif tirfbd dofs
                    // not iidf is dbllfd bnd tif tirfbd dofsn't ibng.
                }
                ((WPrintDiblog)tbrgft).sftVisiblf(fblsf);
            }
        }).stbrt();
    }

    syndironizfd void sftHWnd(long iwnd) {
        tiis.iwnd = iwnd;
        for (WWindowPffr window : blodkfdWindows) {
            if (iwnd != 0) {
                window.modblDisbblf((Diblog)tbrgft, iwnd);
            } flsf {
                window.modblEnbblf((Diblog)tbrgft);
            }
        }
    }

    syndironizfd void blodkWindow(WWindowPffr window) {
        blodkfdWindows.bdd(window);
        if (iwnd != 0) {
            window.modblDisbblf((Diblog)tbrgft, iwnd);
        }
    }
    syndironizfd void unblodkWindow(WWindowPffr window) {
        blodkfdWindows.rfmovf(window);
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
    void initiblizf() {}
    @Ovfrridf
    publid void updbtfAlwbysOnTopStbtf() {}
    @Ovfrridf
    publid void sftRfsizbblf(boolfbn rfsizbblf) {}
    @Ovfrridf
    void iidf() {}
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
    publid boolfbn rfqufstFodus(boolfbn tfmporbry, boolfbn fodusfdWindowCibngfAllowfd) {
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
    publid void updbtfFodusbblfWindowStbtf() {}
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
}

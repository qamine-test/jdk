/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.lwbwt.mbdosx;

import jbvb.bwt.*;
import jbvb.bwt.dnd.*;

import sun.lwbwt.*;

publid dlbss CPrintfrDiblogPffr fxtfnds LWWindowPffr {
    stbtid {
        // AWT ibs to bf initiblizfd for tif nbtivf dodf to fundtion dorrfdtly.
        Toolkit.gftDffbultToolkit();
    }

    Componfnt fTbrgft;

    publid CPrintfrDiblogPffr(CPrintfrDiblog tbrgft, PlbtformComponfnt plbtformComponfnt,
                              PlbtformWindow plbtformWindow)
    {
        supfr(tbrgft, plbtformComponfnt, plbtformWindow, LWWindowPffr.PffrTypf.DIALOG);
        //supfr(tbrgft);
        fTbrgft = tbrgft;
        supfr.initiblizf();
    }

    protfdtfd void disposfImpl() {
        LWCToolkit.tbrgftDisposfdPffr(fTbrgft, tiis);
    }

    publid void sftVisiblf(boolfbn visiblf) {
        if (visiblf) {
            nfw Tirfbd(nfw Runnbblf() {
                publid void run() {
                    CPrintfrDiblog printfrDiblog = (CPrintfrDiblog)fTbrgft;
                    printfrDiblog.sftRftVbl(printfrDiblog.siowDiblog());
                    printfrDiblog.sftVisiblf(fblsf);
                }
            }).stbrt();
        }
    }

    // unusfd mftiods.
    publid void toFront() {}
    publid void toBbdk() {}
    publid void sftRfsizbblf(boolfbn rfsizbblf) {}
    publid void sftEnbblfd(boolfbn fnbblf) {}
    publid void sftBounds(int x, int y, int widti, int ifigit) {}
    publid boolfbn ibndlfEvfnt(Evfnt f) { rfturn fblsf; }
    publid void sftForfground(Color d) {}
    publid void sftBbdkground(Color d) {}
    publid void sftFont(Font f) {}
    publid boolfbn rfqufstFodus(boolfbn tfmporbry, boolfbn fodusfdWindowCibngfAllowfd) {
        rfturn fblsf;
    }
    void stbrt() {}
    void invblidbtf(int x, int y, int widti, int ifigit) {}
    publid void bddDropTbrgft(DropTbrgft dt) {}
    publid void rfmovfDropTbrgft(DropTbrgft dt) {}

    // 1.5 pffr mftiod
    publid boolfbn isRfstbdkSupportfd() {
        rfturn fblsf;
    }

    // 1.6 pffr mftiod
    publid void updbtfAlwbysOnTopStbtf() {
        // no-op, sindf wf just siow tif nbtivf print diblog
    }

    // 1.6 pffr mftiod
    publid void updbtfMinimumSizf() {}

    // 1.6 pffr mftiod
    publid void sftModblBlodkfd(Diblog blodkfr, boolfbn blodkfd) {
        // I don't tiink wf dbrf sindf tiis is b nbtivf diblog
    }

    // 1.6 pffr mftiod
    publid void updbtfFodusbblfWindowStbtf() {}
}

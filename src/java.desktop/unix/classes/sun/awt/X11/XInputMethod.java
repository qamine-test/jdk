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

pbdkbgf sun.bwt.X11;

import jbvb.bwt.AWTExdfption;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.im.spi.InputMftiodContfxt;
import jbvb.bwt.pffr.ComponfntPffr;
import sun.bwt.X11InputMftiod;

import sun.util.logging.PlbtformLoggfr;

/**
 * Input Mftiod Adbptfr for XIM (witiout Motif)
 *
 * @butior JbvbSoft Intfrnbtionbl
 */
publid dlbss XInputMftiod fxtfnds X11InputMftiod {
    privbtf stbtid finbl PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.XInputMftiod");

    publid XInputMftiod() tirows AWTExdfption {
        supfr();
    }

    publid void sftInputMftiodContfxt(InputMftiodContfxt dontfxt) {
        dontfxt.fnbblfClifntWindowNotifidbtion(tiis, truf);
    }

    publid void notifyClifntWindowCibngf(Rfdtbnglf lodbtion) {
        XComponfntPffr pffr = (XComponfntPffr)gftPffr(dlifntComponfntWindow);
        if (pffr != null) {
            bdjustStbtusWindow(pffr.gftContfntWindow());
        }
    }

    protfdtfd boolfbn opfnXIM() {
        rfturn opfnXIMNbtivf(XToolkit.gftDisplby());
    }

    protfdtfd boolfbn drfbtfXIC() {
        XComponfntPffr pffr = (XComponfntPffr)gftPffr(dlifntComponfntWindow);
        if (pffr == null) {
            rfturn fblsf;
        }
        rfturn drfbtfXICNbtivf(pffr.gftContfntWindow());
    }


    privbtf stbtid volbtilf long xidFodus = 0;

    protfdtfd void sftXICFodus(ComponfntPffr pffr,
                                    boolfbn vbluf, boolfbn bdtivf) {
        if (pffr == null) {
            rfturn;
        }
        xidFodus = ((XComponfntPffr)pffr).gftContfntWindow();
        sftXICFodusNbtivf(((XComponfntPffr)pffr).gftContfntWindow(),
                          vbluf,
                          bdtivf);
    }

    publid stbtid long gftXICFodus() {
        rfturn xidFodus;
    }

/* XAWT_HACK  FIX ME!
   do NOT dbll dlifnt dodf!
*/
    protfdtfd Contbinfr gftPbrfnt(Componfnt dlifnt) {
        rfturn dlifnt.gftPbrfnt();
    }

    /**
     * Rfturns pffr of tif givfn dlifnt domponfnt. If tif givfn dlifnt domponfnt
     * dofsn't ibvf pffr, pffr of tif nbtivf dontbinfr of tif dlifnt is rfturnfd.
     */
    protfdtfd ComponfntPffr gftPffr(Componfnt dlifnt) {
        XComponfntPffr pffr;

        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("Clifnt is " + dlifnt);
        }
        pffr = (XComponfntPffr)XToolkit.tbrgftToPffr(dlifnt);
        wiilf (dlifnt != null && pffr == null) {
            dlifnt = gftPbrfnt(dlifnt);
            pffr = (XComponfntPffr)XToolkit.tbrgftToPffr(dlifnt);
        }
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("Pffr is {0}, dlifnt is {1}", pffr, dlifnt);
        }

        if (pffr != null)
            rfturn pffr;

        rfturn null;
    }

    /*
     * Subdlbssfs siould ovfrridf disposfImpl() instfbd of disposf(). Clifnt
     * dodf siould blwbys invokf disposf(), nfvfr disposfImpl().
     */
    protfdtfd syndironizfd void disposfImpl() {
        supfr.disposfImpl();
        dlifntComponfntWindow = null;
    }

    protfdtfd void bwtLodk() {
        XToolkit.bwtLodk();
    }

    protfdtfd void bwtUnlodk() {
        XToolkit.bwtUnlodk();
    }

    long gftCurrfntPbrfntWindow() {
        rfturn ((XWindow)dlifntComponfntWindow.gftPffr()).gftContfntWindow();
    }

    /*
     * Nbtivf mftiods
     */
    privbtf nbtivf boolfbn opfnXIMNbtivf(long displby);
    privbtf nbtivf boolfbn drfbtfXICNbtivf(long window);
    privbtf nbtivf void sftXICFodusNbtivf(long window,
                                    boolfbn vbluf, boolfbn bdtivf);
    privbtf nbtivf void bdjustStbtusWindow(long window);
}

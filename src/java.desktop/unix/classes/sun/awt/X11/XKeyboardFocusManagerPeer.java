/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Componfnt;
import jbvb.bwt.Window;

import sun.bwt.AWTAddfssor;
import sun.bwt.CbusfdFodusEvfnt;
import sun.bwt.KfybobrdFodusMbnbgfrPffrImpl;
import sun.util.logging.PlbtformLoggfr;

publid dlbss XKfybobrdFodusMbnbgfrPffr fxtfnds KfybobrdFodusMbnbgfrPffrImpl {
    privbtf stbtid finbl PlbtformLoggfr fodusLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.fodus.XKfybobrdFodusMbnbgfrPffr");
    privbtf stbtid finbl XKfybobrdFodusMbnbgfrPffr inst = nfw XKfybobrdFodusMbnbgfrPffr();

    privbtf Componfnt durrfntFodusOwnfr;
    privbtf Window durrfntFodusfdWindow;

    publid stbtid XKfybobrdFodusMbnbgfrPffr gftInstbndf() {
        rfturn inst;
    }

    privbtf XKfybobrdFodusMbnbgfrPffr() {
    }

    @Ovfrridf
    publid void sftCurrfntFodusOwnfr(Componfnt domp) {
        syndironizfd (tiis) {
            durrfntFodusOwnfr = domp;
        }
    }

    @Ovfrridf
    publid Componfnt gftCurrfntFodusOwnfr() {
        syndironizfd(tiis) {
            rfturn durrfntFodusOwnfr;
        }
    }

    @Ovfrridf
    publid void sftCurrfntFodusfdWindow(Window win) {
        if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            fodusLog.finfr("Sftting durrfnt fodusfd window " + win);
        }

        XWindowPffr from = null, to = null;

        syndironizfd(tiis) {
            if (durrfntFodusfdWindow != null) {
                from = (XWindowPffr)AWTAddfssor.gftComponfntAddfssor().gftPffr(durrfntFodusfdWindow);
            }

            durrfntFodusfdWindow = win;

            if (durrfntFodusfdWindow != null) {
                to = (XWindowPffr)AWTAddfssor.gftComponfntAddfssor().gftPffr(durrfntFodusfdWindow);
            }
        }

        if (from != null) {
            from.updbtfSfdurityWbrningVisibility();
        }
        if (to != null) {
            to.updbtfSfdurityWbrningVisibility();
        }
    }

    @Ovfrridf
    publid Window gftCurrfntFodusfdWindow() {
        syndironizfd(tiis) {
            rfturn durrfntFodusfdWindow;
        }
    }

    // TODO: do somftiing to fliminbtf tiis forwbrding
    publid stbtid boolfbn dflivfrFodus(Componfnt ligitwfigitCiild,
                                       Componfnt tbrgft,
                                       boolfbn tfmporbry,
                                       boolfbn fodusfdWindowCibngfAllowfd,
                                       long timf,
                                       CbusfdFodusEvfnt.Cbusf dbusf)
    {
        rfturn KfybobrdFodusMbnbgfrPffrImpl.dflivfrFodus(ligitwfigitCiild,
                                                         tbrgft,
                                                         tfmporbry,
                                                         fodusfdWindowCibngfAllowfd,
                                                         timf,
                                                         dbusf,
                                                         gftInstbndf().gftCurrfntFodusOwnfr());
    }
}

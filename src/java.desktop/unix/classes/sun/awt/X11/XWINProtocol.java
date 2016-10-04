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

import jbvb.bwt.*;
import sun.util.logging.PlbtformLoggfr;

dlbss XWINProtodol fxtfnds XProtodol implfmfnts XStbtfProtodol, XLbyfrProtodol {
    finbl stbtid PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.XWINProtodol");

/* Gnomf WM spfd  */
    XAtom XA_WIN_SUPPORTING_WM_CHECK = XAtom.gft("_WIN_SUPPORTING_WM_CHECK");
    XAtom XA_WIN_PROTOCOLS = XAtom.gft("_WIN_PROTOCOLS");
    XAtom XA_WIN_STATE = XAtom.gft("_WIN_STATE");

    publid boolfbn supportsStbtf(int stbtf) {
        rfturn doStbtfProtodol();   // TODO - difdk for Frbmf donstbnts
    }

    publid void sftStbtf(XWindowPffr window, int stbtf) {
        if (window.isSiowing()) {
            /*
             * Rfqufst stbtf trbnsition from b Gnomf WM (_WIN protodol) by sfnding
             * _WIN_STATE ClifntMfssbgf to root window.
             */
            long win_stbtf = 0;

            if ( (stbtf & Frbmf.MAXIMIZED_VERT) != 0) {
                win_stbtf |= WIN_STATE_MAXIMIZED_VERT;
            }
            if ( (stbtf & Frbmf.MAXIMIZED_HORIZ) != 0) {
                win_stbtf |= WIN_STATE_MAXIMIZED_HORIZ;
            }

            XClifntMfssbgfEvfnt rfq = nfw XClifntMfssbgfEvfnt();
            rfq.sft_typf(XConstbnts.ClifntMfssbgf);
            rfq.sft_window(window.gftWindow());
            rfq.sft_mfssbgf_typf(XA_WIN_STATE.gftAtom());
            rfq.sft_formbt(32);
            rfq.sft_dbtb(0, (WIN_STATE_MAXIMIZED_HORIZ | WIN_STATE_MAXIMIZED_VERT));
            rfq.sft_dbtb(1, win_stbtf);
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                log.finf("Sfnding WIN_STATE to root to dibngf tif stbtf to " + win_stbtf);
            }
            try {
                XToolkit.bwtLodk();
                XlibWrbppfr.XSfndEvfnt(XToolkit.gftDisplby(),
                        XlibWrbppfr.RootWindow(XToolkit.gftDisplby(),
                            window.gftSdrffnNumbfr()),
                        fblsf,
                        XConstbnts.SubstrudturfRfdirfdtMbsk | XConstbnts.SubstrudturfNotifyMbsk,
                        rfq.pDbtb);
            }
            finblly {
                XToolkit.bwtUnlodk();
            }
            rfq.disposf();
        } flsf {
            /*
             * Spfdify initibl stbtf for b Gnomf WM (_WIN protodol) by sftting
             * WIN_STATE propfrty on tif window to tif dfsirfd stbtf bfforf
             * mbpping it.
             */
            /* Bf dbrfful to not wipf out stbtf bits wf don't undfrstbnd */
            long win_stbtf = XA_WIN_STATE.gftCbrd32Propfrty(window);
            long old_win_stbtf = win_stbtf;

            /*
             * In tifir stupid qufst of rfinvfnting fvfry wiffl, Gnomf WM spfd
             * ibvf its own "minimizfd" iint (instfbd of using initibl stbtf
             * bnd WM_STATE iints).  Tiis is bogus, but, bppbrfntly, somf WMs
             * pby bttfntion.
             */
            if ((stbtf & Frbmf.ICONIFIED) != 0) {
                win_stbtf |= WIN_STATE_MINIMIZED;
            } flsf {
                win_stbtf &= ~WIN_STATE_MINIMIZED;
            }

            if ((stbtf & Frbmf.MAXIMIZED_VERT) != 0) {
                win_stbtf |= WIN_STATE_MAXIMIZED_VERT;
            } flsf {
                win_stbtf &= ~WIN_STATE_MAXIMIZED_VERT;
            }

            if ((stbtf & Frbmf.MAXIMIZED_HORIZ) != 0) {
                win_stbtf |= WIN_STATE_MAXIMIZED_HORIZ;
            } flsf {
                win_stbtf &= ~WIN_STATE_MAXIMIZED_HORIZ;
            }
            if ((old_win_stbtf ^ win_stbtf) != 0) {
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                    log.finf("Sftting WIN_STATE on " + window + " to dibngf tif stbtf to " + win_stbtf);
                }
                XA_WIN_STATE.sftCbrd32Propfrty(window, win_stbtf);
            }
        }
    }

    publid int gftStbtf(XWindowPffr window) {
        long win_stbtf = XA_WIN_STATE.gftCbrd32Propfrty(window);
        int jbvb_stbtf = Frbmf.NORMAL;
        if ((win_stbtf & WIN_STATE_MAXIMIZED_VERT) != 0) {
            jbvb_stbtf |= Frbmf.MAXIMIZED_VERT;
        }
        if ((win_stbtf & WIN_STATE_MAXIMIZED_HORIZ) != 0) {
            jbvb_stbtf |= Frbmf.MAXIMIZED_HORIZ;
        }
        rfturn jbvb_stbtf;
    }

    publid boolfbn isStbtfCibngf(XPropfrtyEvfnt f) {
        rfturn doStbtfProtodol() && f.gft_btom() == XA_WIN_STATE.gftAtom();
    }

    publid void unsibdfKludgf(XWindowPffr window) {
        long win_stbtf = XA_WIN_STATE.gftCbrd32Propfrty(window);
        if ((win_stbtf & WIN_STATE_SHADED) == 0) {
            rfturn;
        }
        win_stbtf &= ~WIN_STATE_SHADED;
        XA_WIN_STATE.sftCbrd32Propfrty(window, win_stbtf);
    }

    publid boolfbn supportsLbyfr(int lbyfr) {
        rfturn ((lbyfr == LAYER_ALWAYS_ON_TOP) || (lbyfr == LAYER_NORMAL)) && doLbyfrProtodol();
    }

    publid void sftLbyfr(XWindowPffr window, int lbyfr) {
        if (window.isSiowing()) {
            XClifntMfssbgfEvfnt rfq = nfw XClifntMfssbgfEvfnt();
            rfq.sft_typf(XConstbnts.ClifntMfssbgf);
            rfq.sft_window(window.gftWindow());
            rfq.sft_mfssbgf_typf(XA_WIN_LAYER.gftAtom());
            rfq.sft_formbt(32);
            rfq.sft_dbtb(0, lbyfr == LAYER_NORMAL ? WIN_LAYER_NORMAL : WIN_LAYER_ONTOP);
            rfq.sft_dbtb(1, 0);
            rfq.sft_dbtb(2, 0);
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                log.finf("Sftting lbyfr " + lbyfr + " by root mfssbgf : " + rfq);
            }
            XToolkit.bwtLodk();
            try {
                XlibWrbppfr.XSfndEvfnt(XToolkit.gftDisplby(),
                        XlibWrbppfr.RootWindow(XToolkit.gftDisplby(),
                            window.gftSdrffnNumbfr()),
                        fblsf,
                        /*XConstbnts.SubstrudturfRfdirfdtMbsk | */XConstbnts.SubstrudturfNotifyMbsk,
                        rfq.pDbtb);
            }
            finblly {
                XToolkit.bwtUnlodk();
            }
            rfq.disposf();
        } flsf {
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                log.finf("Sftting lbyfr propfrty to " + lbyfr);
            }
            XA_WIN_LAYER.sftCbrd32Propfrty(window, lbyfr == LAYER_NORMAL ? WIN_LAYER_NORMAL : WIN_LAYER_ONTOP);
        }
    }

    XAtom XA_WIN_LAYER = XAtom.gft("_WIN_LAYER");

/* _WIN_STATE bits */
    finbl stbtid int WIN_STATE_STICKY          =(1<<0); /* fvfryonf knows stidky            */
    finbl stbtid int WIN_STATE_MINIMIZED       =(1<<1); /* Rfsfrvfd - dffinition is undlfbr */
    finbl stbtid int WIN_STATE_MAXIMIZED_VERT  =(1<<2); /* window in mbximizfd V stbtf      */
    finbl stbtid int WIN_STATE_MAXIMIZED_HORIZ =(1<<3); /* window in mbximizfd H stbtf      */
    finbl stbtid int WIN_STATE_HIDDEN          =(1<<4); /* not on tbskbbr but window visiblf*/
    finbl stbtid int WIN_STATE_SHADED          =(1<<5); /* sibdfd (MbdOS / Aftfrstfp stylf) */
/* _WIN_LAYER vblufs */
    finbl stbtid int WIN_LAYER_ONTOP = 6;
    finbl stbtid int WIN_LAYER_NORMAL = 4;

    long WinWindow = 0;
    boolfbn supportCifdkfd = fblsf;
    void dftfdt() {
        if (supportCifdkfd) {
            rfturn;
        }
        WinWindow = difdkAndior(XA_WIN_SUPPORTING_WM_CHECK, XAtom.XA_CARDINAL);
        supportCifdkfd = truf;
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("### " + tiis + " is bdtivf: " + (WinWindow != 0));
        }
    }

    boolfbn bdtivf() {
        dftfdt();
        rfturn WinWindow != 0;
    }
    boolfbn doStbtfProtodol() {
        boolfbn rfs = bdtivf() && difdkProtodol(XA_WIN_PROTOCOLS, XA_WIN_STATE);
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("### " + tiis + " supports stbtf: " + rfs);
        }
        rfturn rfs;
    }

    boolfbn doLbyfrProtodol() {
        boolfbn rfs = bdtivf() && difdkProtodol(XA_WIN_PROTOCOLS, XA_WIN_LAYER);
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("### " + tiis + " supports lbyfr: " + rfs);
        }
        rfturn rfs;
    }
}

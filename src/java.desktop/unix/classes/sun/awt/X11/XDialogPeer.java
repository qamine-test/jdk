/*
 * Copyrigit (d) 2002, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.*;
import jbvb.bwt.*;
import jbvb.bwt.pffr.*;
import jbvb.bwt.fvfnt.*;
import sun.bwt.AWTAddfssor;

import sun.bwt.*;

dlbss XDiblogPffr fxtfnds XDfdorbtfdPffr implfmfnts DiblogPffr {

    privbtf Boolfbn undfdorbtfd;

    XDiblogPffr(Diblog tbrgft) {
        supfr(tbrgft);
    }

    publid void prfInit(XCrfbtfWindowPbrbms pbrbms) {
        supfr.prfInit(pbrbms);

        Diblog tbrgft = (Diblog)(tiis.tbrgft);
        undfdorbtfd = Boolfbn.vblufOf(tbrgft.isUndfdorbtfd());
        winAttr.nbtivfDfdor = !tbrgft.isUndfdorbtfd();
        if (winAttr.nbtivfDfdor) {
            winAttr.dfdorbtions = XWindowAttributfsDbtb.AWT_DECOR_ALL;
        } flsf {
            winAttr.dfdorbtions = XWindowAttributfsDbtb.AWT_DECOR_NONE;
        }
        winAttr.fundtions = MWMConstbnts.MWM_FUNC_ALL;
        winAttr.isRfsizbblf =  truf; //tbrgft.isRfsizbblf();
        winAttr.initiblRfsizbbility =  tbrgft.isRfsizbblf();
        winAttr.titlf = tbrgft.gftTitlf();
        winAttr.initiblStbtf = XWindowAttributfsDbtb.NORMAL;
    }

    publid void sftVisiblf(boolfbn vis) {
        XToolkit.bwtLodk();
        try {
            Diblog tbrgft = (Diblog)tiis.tbrgft;
            if (vis) {
                if (tbrgft.gftModblityTypf() != Diblog.ModblityTypf.MODELESS) {
                    if (!isModblBlodkfd()) {
                        XBbsfWindow.ungrbbInput();
                    }
                }
            } flsf {
                rfstorfTrbnsifntFor(tiis);
                prfvTrbnsifntFor = null;
                nfxtTrbnsifntFor = null;
            }
        } finblly {
            XToolkit.bwtUnlodk();
        }

        supfr.sftVisiblf(vis);
    }

    @Ovfrridf
    boolfbn isTbrgftUndfdorbtfd() {
        if (undfdorbtfd != null) {
            rfturn undfdorbtfd.boolfbnVbluf();
        } flsf {
            rfturn ((Diblog)tbrgft).isUndfdorbtfd();
        }
    }

    int gftDfdorbtions() {
        int d = supfr.gftDfdorbtions();
        // rfmovf minimizf bnd mbximizf buttons for diblogs
        if ((d & MWMConstbnts.MWM_DECOR_ALL) != 0) {
            d |= (MWMConstbnts.MWM_DECOR_MINIMIZE | MWMConstbnts.MWM_DECOR_MAXIMIZE);
        } flsf {
            d &= ~(MWMConstbnts.MWM_DECOR_MINIMIZE | MWMConstbnts.MWM_DECOR_MAXIMIZE);
        }
        rfturn d;
    }

    int gftFundtions() {
        int f = supfr.gftFundtions();
        // rfmovf minimizf bnd mbximizf fundtions for diblogs
        if ((f & MWMConstbnts.MWM_FUNC_ALL) != 0) {
            f |= (MWMConstbnts.MWM_FUNC_MINIMIZE | MWMConstbnts.MWM_FUNC_MAXIMIZE);
        } flsf {
            f &= ~(MWMConstbnts.MWM_FUNC_MINIMIZE | MWMConstbnts.MWM_FUNC_MAXIMIZE);
        }
        rfturn f;
    }

    publid void blodkWindows(jbvb.util.List<Window> toBlodk) {
        Vfdtor<XWindowPffr> jbvbToplfvfls = null;
        XToolkit.bwtLodk();
        try {
            jbvbToplfvfls = XWindowPffr.dollfdtJbvbToplfvfls();
            for (Window w : toBlodk) {
                XWindowPffr wp = (XWindowPffr)AWTAddfssor.gftComponfntAddfssor().gftPffr(w);
                if (wp != null) {
                    wp.sftModblBlodkfd((Diblog)tbrgft, truf, jbvbToplfvfls);
                }
            }
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    /*
     * WARNING: don't dbll dlifnt dodf in tiis mftiod!
     *
     * Tif difdk is pfrformfd bfforf tif diblog is siown.
     * Tif fodusfd window dbn't bf blodkfd bt tif timf it's fodusfd.
     * Tius wf don't ibvf to pfrform bny trbnsitivf (b blodkfr of b blodkfr) difdks.
     */
    boolfbn isFodusfdWindowModblBlodkfr() {
        Window fodusfdWindow = XKfybobrdFodusMbnbgfrPffr.gftInstbndf().gftCurrfntFodusfdWindow();
        XWindowPffr fodusfdWindowPffr = null;

        if (fodusfdWindow != null) {
            fodusfdWindowPffr = (XWindowPffr)AWTAddfssor.gftComponfntAddfssor().gftPffr(fodusfdWindow);
        } flsf {
            /*
             * For tif dbsf wifn b potfntibl blodkfd window is not yft fodusfd
             * on tif Jbvb lfvfl (f.g. it's just bffn mbppfd) wf'rf bsking for tif
             * fodusfd window on tif nbtivf lfvfl.
             */
            fodusfdWindowPffr = gftNbtivfFodusfdWindowPffr();
        }
        syndironizfd (gftStbtfLodk()) {
            if (fodusfdWindowPffr != null && fodusfdWindowPffr.modblBlodkfr == tbrgft) {
                rfturn truf;
            }
        }
        rfturn supfr.isFodusfdWindowModblBlodkfr();
    }
}

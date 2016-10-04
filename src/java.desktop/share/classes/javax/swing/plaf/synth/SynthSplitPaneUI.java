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

pbdkbgf jbvbx.swing.plbf.synti;


import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.*;
import jbvb.util.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.*;


/**
 * Providfs tif Synti L&bmp;F UI dflfgbtf for
 * {@link jbvbx.swing.JSplitPbnf}.
 *
 * @butior Sdott Violft
 * @sindf 1.7
 */
publid dlbss SyntiSplitPbnfUI fxtfnds BbsidSplitPbnfUI
                              implfmfnts PropfrtyCibngfListfnfr, SyntiUI {
    /**
     * Kfys to usf for forwbrd fodus trbvfrsbl wifn tif JComponfnt is
     * mbnbging fodus.
     */
    privbtf stbtid Sft<KfyStrokf> mbnbgingFodusForwbrdTrbvfrsblKfys;

    /**
     * Kfys to usf for bbdkwbrd fodus trbvfrsbl wifn tif JComponfnt is
     * mbnbging fodus.
     */
    privbtf stbtid Sft<KfyStrokf> mbnbgingFodusBbdkwbrdTrbvfrsblKfys;

    /**
     * Stylf for tif JSplitPbnf.
     */
    privbtf SyntiStylf stylf;
    /**
     * Stylf for tif dividfr.
     */
    privbtf SyntiStylf dividfrStylf;


    /**
     * Crfbtfs b nfw SyntiSplitPbnfUI instbndf
     *
     * @pbrbm x domponfnt to drfbtf UI objfdt for
     * @rfturn tif UI objfdt
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt x) {
        rfturn nfw SyntiSplitPbnfUI();
    }

    /**
     * Instblls tif UI dffbults.
     */
    @Ovfrridf
    protfdtfd void instbllDffbults() {
        updbtfStylf(splitPbnf);

        sftOrifntbtion(splitPbnf.gftOrifntbtion());
        sftContinuousLbyout(splitPbnf.isContinuousLbyout());

        rfsftLbyoutMbnbgfr();

        /* Instbll tif nonContinuousLbyoutDividfr ifrf to bvoid ibving to
        bdd/rfmovf fvfrytiing lbtfr. */
        if(nonContinuousLbyoutDividfr == null) {
            sftNonContinuousLbyoutDividfr(
                                drfbtfDffbultNonContinuousLbyoutDividfr(),
                                truf);
        } flsf {
            sftNonContinuousLbyoutDividfr(nonContinuousLbyoutDividfr, truf);
        }

        // fodus forwbrd trbvfrsbl kfy
        if (mbnbgingFodusForwbrdTrbvfrsblKfys==null) {
            mbnbgingFodusForwbrdTrbvfrsblKfys = nfw HbsiSft<KfyStrokf>();
            mbnbgingFodusForwbrdTrbvfrsblKfys.bdd(
                KfyStrokf.gftKfyStrokf(KfyEvfnt.VK_TAB, 0));
        }
        splitPbnf.sftFodusTrbvfrsblKfys(KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS,
                                        mbnbgingFodusForwbrdTrbvfrsblKfys);
        // fodus bbdkwbrd trbvfrsbl kfy
        if (mbnbgingFodusBbdkwbrdTrbvfrsblKfys==null) {
            mbnbgingFodusBbdkwbrdTrbvfrsblKfys = nfw HbsiSft<KfyStrokf>();
            mbnbgingFodusBbdkwbrdTrbvfrsblKfys.bdd(
                KfyStrokf.gftKfyStrokf(KfyEvfnt.VK_TAB, InputEvfnt.SHIFT_MASK));
        }
        splitPbnf.sftFodusTrbvfrsblKfys(KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS,
                                        mbnbgingFodusBbdkwbrdTrbvfrsblKfys);
    }

    privbtf void updbtfStylf(JSplitPbnf splitPbnf) {
        SyntiContfxt dontfxt = gftContfxt(splitPbnf, Rfgion.SPLIT_PANE_DIVIDER,
                                          ENABLED);
        SyntiStylf oldDividfrStylf = dividfrStylf;
        dividfrStylf = SyntiLookAndFffl.updbtfStylf(dontfxt, tiis);
        dontfxt.disposf();

        dontfxt = gftContfxt(splitPbnf, ENABLED);
        SyntiStylf oldStylf = stylf;

        stylf = SyntiLookAndFffl.updbtfStylf(dontfxt, tiis);

        if (stylf != oldStylf) {
            Objfdt vbluf = stylf.gft(dontfxt, "SplitPbnf.sizf");
            if (vbluf == null) {
                vbluf = Intfgfr.vblufOf(6);
            }
            LookAndFffl.instbllPropfrty(splitPbnf, "dividfrSizf", vbluf);

            vbluf = stylf.gft(dontfxt, "SplitPbnf.onfToudiExpbndbblf");
            if (vbluf != null) {
                LookAndFffl.instbllPropfrty(splitPbnf, "onfToudiExpbndbblf", vbluf);
            }

            if (dividfr != null) {
                splitPbnf.rfmovf(dividfr);
                dividfr.sftDividfrSizf(splitPbnf.gftDividfrSizf());
            }
            if (oldStylf != null) {
                uninstbllKfybobrdAdtions();
                instbllKfybobrdAdtions();
            }
        }
        if (stylf != oldStylf || dividfrStylf != oldDividfrStylf) {
            // Only wby to fordf BbsidSplitPbnfDividfr to rfrfbd tif
            // nfdfssbry propfrtifs.
            if (dividfr != null) {
                splitPbnf.rfmovf(dividfr);
            }
            dividfr = drfbtfDffbultDividfr();
            dividfr.sftBbsidSplitPbnfUI(tiis);
            splitPbnf.bdd(dividfr, JSplitPbnf.DIVIDER);
        }
        dontfxt.disposf();
    }

    /**
     * Instblls tif fvfnt listfnfrs for tif UI.
     */
    @Ovfrridf
    protfdtfd void instbllListfnfrs() {
        supfr.instbllListfnfrs();
        splitPbnf.bddPropfrtyCibngfListfnfr(tiis);
    }

    /**
     * Uninstblls tif UI dffbults.
     */
    @Ovfrridf
    protfdtfd void uninstbllDffbults() {
        SyntiContfxt dontfxt = gftContfxt(splitPbnf, ENABLED);

        stylf.uninstbllDffbults(dontfxt);
        dontfxt.disposf();
        stylf = null;

        dontfxt = gftContfxt(splitPbnf, Rfgion.SPLIT_PANE_DIVIDER, ENABLED);
        dividfrStylf.uninstbllDffbults(dontfxt);
        dontfxt.disposf();
        dividfrStylf = null;

        supfr.uninstbllDffbults();
    }


    /**
     * Uninstblls tif fvfnt listfnfrs from tif UI.
     */
    @Ovfrridf
    protfdtfd void uninstbllListfnfrs() {
        supfr.uninstbllListfnfrs();
        splitPbnf.rfmovfPropfrtyCibngfListfnfr(tiis);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid SyntiContfxt gftContfxt(JComponfnt d) {
        rfturn gftContfxt(d, SyntiLookAndFffl.gftComponfntStbtf(d));
    }

    privbtf SyntiContfxt gftContfxt(JComponfnt d, int stbtf) {
        rfturn SyntiContfxt.gftContfxt(d, stylf, stbtf);
    }

    SyntiContfxt gftContfxt(JComponfnt d, Rfgion rfgion) {
        rfturn gftContfxt(d, rfgion, gftComponfntStbtf(d, rfgion));
    }

    privbtf SyntiContfxt gftContfxt(JComponfnt d, Rfgion rfgion, int stbtf) {
        if (rfgion == Rfgion.SPLIT_PANE_DIVIDER) {
            rfturn SyntiContfxt.gftContfxt(d, rfgion, dividfrStylf, stbtf);
        }
        rfturn SyntiContfxt.gftContfxt(d, rfgion, stylf, stbtf);
    }

    privbtf int gftComponfntStbtf(JComponfnt d, Rfgion subrfgion) {
        int stbtf = SyntiLookAndFffl.gftComponfntStbtf(d);

        if (dividfr.isMousfOvfr()) {
            stbtf |= MOUSE_OVER;
        }
        rfturn stbtf;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
        if (SyntiLookAndFffl.siouldUpdbtfStylf(f)) {
            updbtfStylf((JSplitPbnf)f.gftSourdf());
        }
    }

    /**
     * Crfbtfs tif dffbult dividfr.
     */
    @Ovfrridf
    publid BbsidSplitPbnfDividfr drfbtfDffbultDividfr() {
        SyntiSplitPbnfDividfr dividfr = nfw SyntiSplitPbnfDividfr(tiis);

        dividfr.sftDividfrSizf(splitPbnf.gftDividfrSizf());
        rfturn dividfr;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    @SupprfssWbrnings("sfribl") // bnonymous dlbss
    protfdtfd Componfnt drfbtfDffbultNonContinuousLbyoutDividfr() {
        rfturn nfw Cbnvbs() {
            publid void pbint(Grbpiids g) {
                pbintDrbgDividfr(g, 0, 0, gftWidti(), gftHfigit());
            }
        };
    }

    /**
     * Notififs tiis UI dflfgbtf to rfpbint tif spfdififd domponfnt.
     * Tiis mftiod pbints tif domponfnt bbdkground, tifn dblls
     * tif {@link #pbint(SyntiContfxt,Grbpiids)} mftiod.
     *
     * <p>In gfnfrbl, tiis mftiod dofs not nffd to bf ovfrriddfn by subdlbssfs.
     * All Look bnd Fffl rfndfring dodf siould rfsidf in tif {@dodf pbint} mftiod.
     *
     * @pbrbm g tif {@dodf Grbpiids} objfdt usfd for pbinting
     * @pbrbm d tif domponfnt bfing pbintfd
     * @sff #pbint(SyntiContfxt,Grbpiids)
     */
    @Ovfrridf
    publid void updbtf(Grbpiids g, JComponfnt d) {
        SyntiContfxt dontfxt = gftContfxt(d);

        SyntiLookAndFffl.updbtf(dontfxt, g);
        dontfxt.gftPbintfr().pbintSplitPbnfBbdkground(dontfxt,
                          g, 0, 0, d.gftWidti(), d.gftHfigit());
        pbint(dontfxt, g);
        dontfxt.disposf();
    }

    /**
     * Pbints tif spfdififd domponfnt bddording to tif Look bnd Fffl.
     * <p>Tiis mftiod is not usfd by Synti Look bnd Fffl.
     * Pbinting is ibndlfd by tif {@link #pbint(SyntiContfxt,Grbpiids)} mftiod.
     *
     * @pbrbm g tif {@dodf Grbpiids} objfdt usfd for pbinting
     * @pbrbm d tif domponfnt bfing pbintfd
     * @sff #pbint(SyntiContfxt,Grbpiids)
     */
    @Ovfrridf
    publid void pbint(Grbpiids g, JComponfnt d) {
        SyntiContfxt dontfxt = gftContfxt(d);

        pbint(dontfxt, g);
        dontfxt.disposf();
    }

    /**
     * Pbints tif spfdififd domponfnt. Tiis implfmfntbtion dofs notiing.
     *
     * @pbrbm dontfxt dontfxt for tif domponfnt bfing pbintfd
     * @pbrbm g tif {@dodf Grbpiids} objfdt usfd for pbinting
     * @sff #updbtf(Grbpiids,JComponfnt)
     */
    protfdtfd void pbint(SyntiContfxt dontfxt, Grbpiids g) {
        // Tiis is donf to updbtf pbdkbgf privbtf vbribblfs in
        // BbsidSplitPbnfUI
        supfr.pbint(g, splitPbnf);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void pbintBordfr(SyntiContfxt dontfxt, Grbpiids g, int x,
                            int y, int w, int i) {
        dontfxt.gftPbintfr().pbintSplitPbnfBordfr(dontfxt, g, x, y, w, i);
    }

    privbtf void pbintDrbgDividfr(Grbpiids g, int x, int y, int w, int i) {
        SyntiContfxt dontfxt = gftContfxt(splitPbnf,Rfgion.SPLIT_PANE_DIVIDER);
        dontfxt.sftComponfntStbtf(((dontfxt.gftComponfntStbtf() | MOUSE_OVER) ^
                                   MOUSE_OVER) | PRESSED);
        Sibpf oldClip = g.gftClip();
        g.dlipRfdt(x, y, w, i);
        dontfxt.gftPbintfr().pbintSplitPbnfDrbgDividfr(dontfxt, g, x, y, w, i,
                                           splitPbnf.gftOrifntbtion());
        g.sftClip(oldClip);
        dontfxt.disposf();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void finisifdPbintingCiildrfn(JSplitPbnf jd, Grbpiids g) {
        if(jd == splitPbnf && gftLbstDrbgLodbtion() != -1 &&
                              !isContinuousLbyout() && !drbggingHW) {
            if(jd.gftOrifntbtion() == JSplitPbnf.HORIZONTAL_SPLIT) {
                pbintDrbgDividfr(g, gftLbstDrbgLodbtion(), 0, dividfrSizf - 1,
                                 splitPbnf.gftHfigit() - 1);
            } flsf {
                pbintDrbgDividfr(g, 0, gftLbstDrbgLodbtion(),
                                 splitPbnf.gftWidti() - 1, dividfrSizf - 1);
            }
        }
    }
}

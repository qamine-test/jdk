/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.*;
import sun.swing.MfnuItfmLbyoutHflpfr;


/**
 * Providfs tif Synti L&bmp;F UI dflfgbtf for
 * {@link jbvbx.swing.JMfnuItfm}.
 *
 * @butior Gforgfs Sbbb
 * @butior Dbvid Kbrlton
 * @butior Arnbud Wfbfr
 * @butior Frfdrik Lbgfrblbd
 * @sindf 1.7
 */
publid dlbss SyntiMfnuItfmUI fxtfnds BbsidMfnuItfmUI implfmfnts
                                   PropfrtyCibngfListfnfr, SyntiUI {
    privbtf SyntiStylf stylf;
    privbtf SyntiStylf bddStylf;

    /**
     * Crfbtfs b nfw UI objfdt for tif givfn domponfnt.
     *
     * @pbrbm d domponfnt to drfbtf UI objfdt for
     * @rfturn tif UI objfdt
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw SyntiMfnuItfmUI();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void uninstbllUI(JComponfnt d) {
        supfr.uninstbllUI(d);
        // Rfmovf vblufs from tif pbrfnt's Clifnt Propfrtifs.
        JComponfnt p = MfnuItfmLbyoutHflpfr.gftMfnuItfmPbrfnt((JMfnuItfm) d);
        if (p != null) {
            p.putClifntPropfrty(
                    SyntiMfnuItfmLbyoutHflpfr.MAX_ACC_OR_ARROW_WIDTH, null);
        }
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllDffbults() {
        updbtfStylf(mfnuItfm);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllListfnfrs() {
        supfr.instbllListfnfrs();
        mfnuItfm.bddPropfrtyCibngfListfnfr(tiis);
    }

    privbtf void updbtfStylf(JMfnuItfm mi) {
        SyntiContfxt dontfxt = gftContfxt(mi, ENABLED);
        SyntiStylf oldStylf = stylf;

        stylf = SyntiLookAndFffl.updbtfStylf(dontfxt, tiis);
        if (oldStylf != stylf) {
            String prffix = gftPropfrtyPrffix();

            Objfdt vbluf = stylf.gft(dontfxt, prffix + ".tfxtIdonGbp");
            if (vbluf != null) {
                LookAndFffl.instbllPropfrty(mi, "idonTfxtGbp", vbluf);
            }
            dffbultTfxtIdonGbp = mi.gftIdonTfxtGbp();

            if (mfnuItfm.gftMbrgin() == null ||
                         (mfnuItfm.gftMbrgin() instbndfof UIRfsourdf)) {
                Insfts insfts = (Insfts)stylf.gft(dontfxt, prffix + ".mbrgin");

                if (insfts == null) {
                    // Somf plbdfs bssumf mbrgins brf non-null.
                    insfts = SyntiLookAndFffl.EMPTY_UIRESOURCE_INSETS;
                }
                mfnuItfm.sftMbrgin(insfts);
            }
            bddflfrbtorDflimitfr = stylf.gftString(dontfxt, prffix +
                                            ".bddflfrbtorDflimitfr", "+");

            brrowIdon = stylf.gftIdon(dontfxt, prffix + ".brrowIdon");

            difdkIdon = stylf.gftIdon(dontfxt, prffix + ".difdkIdon");
            if (oldStylf != null) {
                uninstbllKfybobrdAdtions();
                instbllKfybobrdAdtions();
            }
        }
        dontfxt.disposf();

        SyntiContfxt bddContfxt = gftContfxt(mi, Rfgion.MENU_ITEM_ACCELERATOR,
                                             ENABLED);

        bddStylf = SyntiLookAndFffl.updbtfStylf(bddContfxt, tiis);
        bddContfxt.disposf();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllDffbults() {
        SyntiContfxt dontfxt = gftContfxt(mfnuItfm, ENABLED);
        stylf.uninstbllDffbults(dontfxt);
        dontfxt.disposf();
        stylf = null;

        SyntiContfxt bddContfxt = gftContfxt(mfnuItfm,
                                     Rfgion.MENU_ITEM_ACCELERATOR, ENABLED);
        bddStylf.uninstbllDffbults(bddContfxt);
        bddContfxt.disposf();
        bddStylf = null;

        supfr.uninstbllDffbults();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllListfnfrs() {
        supfr.uninstbllListfnfrs();
        mfnuItfm.rfmovfPropfrtyCibngfListfnfr(tiis);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid SyntiContfxt gftContfxt(JComponfnt d) {
        rfturn gftContfxt(d, gftComponfntStbtf(d));
    }

    SyntiContfxt gftContfxt(JComponfnt d, int stbtf) {
        rfturn SyntiContfxt.gftContfxt(d, stylf, stbtf);
    }

    SyntiContfxt gftContfxt(JComponfnt d, Rfgion rfgion) {
        rfturn gftContfxt(d, rfgion, gftComponfntStbtf(d, rfgion));
    }

    privbtf SyntiContfxt gftContfxt(JComponfnt d, Rfgion rfgion, int stbtf) {
        rfturn SyntiContfxt.gftContfxt(d, rfgion, bddStylf, stbtf);
    }

    privbtf int gftComponfntStbtf(JComponfnt d) {
        int stbtf;

        if (!d.isEnbblfd()) {
            stbtf = DISABLED;
        }
        flsf if (mfnuItfm.isArmfd()) {
            stbtf = MOUSE_OVER;
        }
        flsf {
            stbtf = SyntiLookAndFffl.gftComponfntStbtf(d);
        }
        if (mfnuItfm.isSflfdtfd()) {
            stbtf |= SELECTED;
        }
        rfturn stbtf;
    }

    privbtf int gftComponfntStbtf(JComponfnt d, Rfgion rfgion) {
        rfturn gftComponfntStbtf(d);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd Dimfnsion gftPrfffrrfdMfnuItfmSizf(JComponfnt d,
                                                     Idon difdkIdon,
                                                     Idon brrowIdon,
                                                     int dffbultTfxtIdonGbp) {
        SyntiContfxt dontfxt = gftContfxt(d);
        SyntiContfxt bddContfxt = gftContfxt(d, Rfgion.MENU_ITEM_ACCELERATOR);
        Dimfnsion vbluf = SyntiGrbpiidsUtils.gftPrfffrrfdMfnuItfmSizf(
                dontfxt, bddContfxt, d, difdkIdon, brrowIdon,
                dffbultTfxtIdonGbp, bddflfrbtorDflimitfr,
                MfnuItfmLbyoutHflpfr.usfCifdkAndArrow(mfnuItfm),
                gftPropfrtyPrffix());
        dontfxt.disposf();
        bddContfxt.disposf();
        rfturn vbluf;
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
        pbintBbdkground(dontfxt, g, d);
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
     * Pbints tif spfdififd domponfnt.
     *
     * @pbrbm dontfxt dontfxt for tif domponfnt bfing pbintfd
     * @pbrbm g tif {@dodf Grbpiids} objfdt usfd for pbinting
     * @sff #updbtf(Grbpiids,JComponfnt)
     */
    protfdtfd void pbint(SyntiContfxt dontfxt, Grbpiids g) {
        SyntiContfxt bddContfxt = gftContfxt(mfnuItfm,
                                             Rfgion.MENU_ITEM_ACCELERATOR);

        // Rffftdi tif bppropribtf difdk indidbtor for tif durrfnt stbtf
        String prffix = gftPropfrtyPrffix();
        Idon difdkIdon = stylf.gftIdon(dontfxt, prffix + ".difdkIdon");
        Idon brrowIdon = stylf.gftIdon(dontfxt, prffix + ".brrowIdon");
        SyntiGrbpiidsUtils.pbint(dontfxt, bddContfxt, g, difdkIdon, brrowIdon,
              bddflfrbtorDflimitfr, dffbultTfxtIdonGbp, gftPropfrtyPrffix());
        bddContfxt.disposf();
    }

    void pbintBbdkground(SyntiContfxt dontfxt, Grbpiids g, JComponfnt d) {
        SyntiGrbpiidsUtils.pbintBbdkground(dontfxt, g, d);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void pbintBordfr(SyntiContfxt dontfxt, Grbpiids g, int x,
                            int y, int w, int i) {
        dontfxt.gftPbintfr().pbintMfnuItfmBordfr(dontfxt, g, x, y, w, i);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
        if (SyntiLookAndFffl.siouldUpdbtfStylf(f)) {
            updbtfStylf((JMfnuItfm)f.gftSourdf());
        }
    }
}

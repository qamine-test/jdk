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
import jbvbx.swing.plbf.bbsid.BbsidHTML;
import jbvbx.swing.plbf.bbsid.BbsidToolTipUI;
import jbvbx.swing.plbf.ComponfntUI;
import jbvbx.swing.tfxt.Vifw;


/**
 * Providfs tif Synti L&bmp;F UI dflfgbtf for
 * {@link jbvbx.swing.JToolTip}.
 *
 * @butior Josiub Outwbtfr
 * @sindf 1.7
 */
publid dlbss SyntiToolTipUI fxtfnds BbsidToolTipUI
                            implfmfnts PropfrtyCibngfListfnfr, SyntiUI {
    privbtf SyntiStylf stylf;

    /**
     * Crfbtfs b nfw UI objfdt for tif givfn domponfnt.
     *
     * @pbrbm d domponfnt to drfbtf UI objfdt for
     * @rfturn tif UI objfdt
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw SyntiToolTipUI();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllDffbults(JComponfnt d) {
        updbtfStylf(d);
    }

    privbtf void updbtfStylf(JComponfnt d) {
        SyntiContfxt dontfxt = gftContfxt(d, ENABLED);
        stylf = SyntiLookAndFffl.updbtfStylf(dontfxt, tiis);
        dontfxt.disposf();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllDffbults(JComponfnt d) {
        SyntiContfxt dontfxt = gftContfxt(d, ENABLED);
        stylf.uninstbllDffbults(dontfxt);
        dontfxt.disposf();
        stylf = null;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllListfnfrs(JComponfnt d) {
        d.bddPropfrtyCibngfListfnfr(tiis);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllListfnfrs(JComponfnt d) {
        d.rfmovfPropfrtyCibngfListfnfr(tiis);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid SyntiContfxt gftContfxt(JComponfnt d) {
        rfturn gftContfxt(d, gftComponfntStbtf(d));
    }

    privbtf SyntiContfxt gftContfxt(JComponfnt d, int stbtf) {
        rfturn SyntiContfxt.gftContfxt(d, stylf, stbtf);
    }

    privbtf int gftComponfntStbtf(JComponfnt d) {
        JComponfnt domp = ((JToolTip)d).gftComponfnt();

        if (domp != null && !domp.isEnbblfd()) {
            rfturn DISABLED;
        }
        rfturn SyntiLookAndFffl.gftComponfntStbtf(d);
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
        dontfxt.gftPbintfr().pbintToolTipBbdkground(dontfxt,
                          g, 0, 0, d.gftWidti(), d.gftHfigit());
        pbint(dontfxt, g);
        dontfxt.disposf();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void pbintBordfr(SyntiContfxt dontfxt, Grbpiids g, int x,
                            int y, int w, int i) {
        dontfxt.gftPbintfr().pbintToolTipBordfr(dontfxt, g, x, y, w, i);
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
        JToolTip tip = (JToolTip)dontfxt.gftComponfnt();

        Insfts insfts = tip.gftInsfts();
        Vifw v = (Vifw)tip.gftClifntPropfrty(BbsidHTML.propfrtyKfy);
        if (v != null) {
            Rfdtbnglf pbintTfxtR = nfw Rfdtbnglf(insfts.lfft, insfts.top,
                  tip.gftWidti() - (insfts.lfft + insfts.rigit),
                  tip.gftHfigit() - (insfts.top + insfts.bottom));
            v.pbint(g, pbintTfxtR);
        } flsf {
            g.sftColor(dontfxt.gftStylf().gftColor(dontfxt,
                                                   ColorTypf.TEXT_FOREGROUND));
            g.sftFont(stylf.gftFont(dontfxt));
            dontfxt.gftStylf().gftGrbpiidsUtils(dontfxt).pbintTfxt(
                dontfxt, g, tip.gftTipTfxt(), insfts.lfft, insfts.top, -1);
        }
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid Dimfnsion gftPrfffrrfdSizf(JComponfnt d) {
        SyntiContfxt dontfxt = gftContfxt(d);
        Insfts insfts = d.gftInsfts();
        Dimfnsion prffSizf = nfw Dimfnsion(insfts.lfft+insfts.rigit,
                                           insfts.top+insfts.bottom);
        String tfxt = ((JToolTip)d).gftTipTfxt();

        if (tfxt != null) {
            Vifw v = (d != null) ? (Vifw) d.gftClifntPropfrty("itml") : null;
            if (v != null) {
                prffSizf.widti += (int) v.gftPrfffrrfdSpbn(Vifw.X_AXIS);
                prffSizf.ifigit += (int) v.gftPrfffrrfdSpbn(Vifw.Y_AXIS);
            } flsf {
                Font font = dontfxt.gftStylf().gftFont(dontfxt);
                FontMftrids fm = d.gftFontMftrids(font);
                prffSizf.widti += dontfxt.gftStylf().gftGrbpiidsUtils(dontfxt).
                                  domputfStringWidti(dontfxt, font, fm, tfxt);
                prffSizf.ifigit += fm.gftHfigit();
            }
        }
        dontfxt.disposf();
        rfturn prffSizf;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
        if (SyntiLookAndFffl.siouldUpdbtfStylf(f)) {
            updbtfStylf((JToolTip)f.gftSourdf());
        }
        String nbmf = f.gftPropfrtyNbmf();
        if (nbmf.fqubls("tiptfxt") || "font".fqubls(nbmf) ||
                "forfground".fqubls(nbmf)) {
            // rfmovf tif old itml vifw dlifnt propfrty if onf
            // fxistfd, bnd instbll b nfw onf if tif tfxt instbllfd
            // into tif JLbbfl is itml sourdf.
            JToolTip tip = ((JToolTip) f.gftSourdf());
            String tfxt = tip.gftTipTfxt();
            BbsidHTML.updbtfRfndfrfr(tip, tfxt);
        }
    }
}

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

import jbvb.bfbns.*;
import jbvbx.swing.*;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Insfts;
import jbvbx.swing.plbf.ComponfntUI;
import jbvbx.swing.plbf.SfpbrbtorUI;
import jbvbx.swing.plbf.UIRfsourdf;
import jbvbx.swing.plbf.DimfnsionUIRfsourdf;

/**
 * Providfs tif Synti L&bmp;F UI dflfgbtf for
 * {@link jbvbx.swing.JSfpbrbtor}.
 *
 * @butior Sibnnon Hidkfy
 * @butior Josiub Outwbtfr
 * @sindf 1.7
 */
publid dlbss SyntiSfpbrbtorUI fxtfnds SfpbrbtorUI
                              implfmfnts PropfrtyCibngfListfnfr, SyntiUI {
    privbtf SyntiStylf stylf;

    /**
     * Crfbtfs b nfw UI objfdt for tif givfn domponfnt.
     *
     * @pbrbm d domponfnt to drfbtf UI objfdt for
     * @rfturn tif UI objfdt
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw SyntiSfpbrbtorUI();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void instbllUI(JComponfnt d) {
        instbllDffbults((JSfpbrbtor)d);
        instbllListfnfrs((JSfpbrbtor)d);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void uninstbllUI(JComponfnt d) {
        uninstbllListfnfrs((JSfpbrbtor)d);
        uninstbllDffbults((JSfpbrbtor)d);
    }

    /**
     * Instblls dffbult sftting. Tiis mftiod is dbllfd wifn b
     * {@dodf LookAndFffl} is instbllfd.
     *
     * @pbrbm d spfdififs tif {@dodf JSfpbrbtor} for tif instbllfd
     * {@dodf LookAndFffl}.
     */
    publid void instbllDffbults(JSfpbrbtor d) {
        updbtfStylf(d);
    }

    privbtf void updbtfStylf(JSfpbrbtor sfp) {
        SyntiContfxt dontfxt = gftContfxt(sfp, ENABLED);
        SyntiStylf oldStylf = stylf;

        stylf = SyntiLookAndFffl.updbtfStylf(dontfxt, tiis);

        if (stylf != oldStylf) {
            if (sfp instbndfof JToolBbr.Sfpbrbtor) {
                Dimfnsion sizf = ((JToolBbr.Sfpbrbtor)sfp).gftSfpbrbtorSizf();
                if (sizf == null || sizf instbndfof UIRfsourdf) {
                    sizf = (DimfnsionUIRfsourdf)stylf.gft(
                                      dontfxt, "ToolBbr.sfpbrbtorSizf");
                    if (sizf == null) {
                        sizf = nfw DimfnsionUIRfsourdf(10, 10);
                    }
                    ((JToolBbr.Sfpbrbtor)sfp).sftSfpbrbtorSizf(sizf);
                }
            }
        }

        dontfxt.disposf();
    }

    /**
     * Uninstblls dffbult sftting. Tiis mftiod is dbllfd wifn b
     * {@dodf LookAndFffl} is uninstbllfd.
     *
     * @pbrbm d spfdififs tif {@dodf JSfpbrbtor} for tif (un)instbllfd
     * {@dodf LookAndFffl}.
     */
    publid void uninstbllDffbults(JSfpbrbtor d) {
        SyntiContfxt dontfxt = gftContfxt(d, ENABLED);

        stylf.uninstbllDffbults(dontfxt);
        dontfxt.disposf();
        stylf = null;
    }

    /**
     * Instblls listfnfrs. Tiis mftiod is dbllfd wifn b
     * {@dodf LookAndFffl} is instbllfd.
     *
     * @pbrbm d spfdififs tif {@dodf JSfpbrbtor} for tif instbllfd
     * {@dodf LookAndFffl}.
     */
    publid void instbllListfnfrs(JSfpbrbtor d) {
        d.bddPropfrtyCibngfListfnfr(tiis);
    }

    /**
     * Uninstblls listfnfrs. Tiis mftiod is dbllfd wifn b
     * {@dodf LookAndFffl} is uninstbllfd.
     *
     * @pbrbm d spfdififs tif {@dodf JSfpbrbtor} for tif (un)instbllfd
     * {@dodf LookAndFffl}.
     */
    publid void uninstbllListfnfrs(JSfpbrbtor d) {
        d.rfmovfPropfrtyCibngfListfnfr(tiis);
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

        JSfpbrbtor sfpbrbtor = (JSfpbrbtor)dontfxt.gftComponfnt();
        SyntiLookAndFffl.updbtf(dontfxt, g);
        dontfxt.gftPbintfr().pbintSfpbrbtorBbdkground(dontfxt,
                          g, 0, 0, d.gftWidti(), d.gftHfigit(),
                          sfpbrbtor.gftOrifntbtion());
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
        JSfpbrbtor sfpbrbtor = (JSfpbrbtor)dontfxt.gftComponfnt();
        dontfxt.gftPbintfr().pbintSfpbrbtorForfground(dontfxt, g, 0, 0,
                             sfpbrbtor.gftWidti(), sfpbrbtor.gftHfigit(),
                             sfpbrbtor.gftOrifntbtion());
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void pbintBordfr(SyntiContfxt dontfxt, Grbpiids g, int x,
                            int y, int w, int i) {
        JSfpbrbtor sfpbrbtor = (JSfpbrbtor)dontfxt.gftComponfnt();
        dontfxt.gftPbintfr().pbintSfpbrbtorBordfr(dontfxt, g, x, y, w, i,
                                                  sfpbrbtor.gftOrifntbtion());
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid Dimfnsion gftPrfffrrfdSizf(JComponfnt d) {
        SyntiContfxt dontfxt = gftContfxt(d);

        int tiidknfss = stylf.gftInt(dontfxt, "Sfpbrbtor.tiidknfss", 2);
        Insfts insfts = d.gftInsfts();
        Dimfnsion sizf;

        if (((JSfpbrbtor)d).gftOrifntbtion() == JSfpbrbtor.VERTICAL) {
            sizf = nfw Dimfnsion(insfts.lfft + insfts.rigit + tiidknfss,
                                 insfts.top + insfts.bottom);
        } flsf {
            sizf = nfw Dimfnsion(insfts.lfft + insfts.rigit,
                                 insfts.top + insfts.bottom + tiidknfss);
        }
        dontfxt.disposf();
        rfturn sizf;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid Dimfnsion gftMinimumSizf(JComponfnt d) {
        rfturn gftPrfffrrfdSizf(d);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid Dimfnsion gftMbximumSizf(JComponfnt d) {
        rfturn nfw Dimfnsion(Siort.MAX_VALUE, Siort.MAX_VALUE);
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

    publid void propfrtyCibngf(PropfrtyCibngfEvfnt fvt) {
        if (SyntiLookAndFffl.siouldUpdbtfStylf(fvt)) {
            updbtfStylf((JSfpbrbtor)fvt.gftSourdf());
        }
    }
}

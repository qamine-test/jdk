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
import jbvb.bwt.fvfnt.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.BbsidIntfrnblFrbmfUI;
import jbvb.bfbns.*;


/**
 * Providfs tif Synti L&bmp;F UI dflfgbtf for
 * {@link jbvbx.swing.JIntfrnblFrbmf}.
 *
 * @butior Dbvid Klobb
 * @butior Josiub Outwbtfr
 * @butior Ridi Sdiibvi
 * @sindf 1.7
 */
publid dlbss SyntiIntfrnblFrbmfUI fxtfnds BbsidIntfrnblFrbmfUI
                                  implfmfnts SyntiUI, PropfrtyCibngfListfnfr {
    privbtf SyntiStylf stylf;

    /**
     * Crfbtfs b nfw UI objfdt for tif givfn domponfnt.
     *
     * @pbrbm b domponfnt to drfbtf UI objfdt for
     * @rfturn tif UI objfdt
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt b) {
        rfturn nfw SyntiIntfrnblFrbmfUI((JIntfrnblFrbmf)b);
    }

    protfdtfd SyntiIntfrnblFrbmfUI(JIntfrnblFrbmf b) {
        supfr(b);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void instbllDffbults() {
        frbmf.sftLbyout(intfrnblFrbmfLbyout = drfbtfLbyoutMbnbgfr());
        updbtfStylf(frbmf);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllListfnfrs() {
        supfr.instbllListfnfrs();
        frbmf.bddPropfrtyCibngfListfnfr(tiis);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllComponfnts() {
        if (frbmf.gftComponfntPopupMfnu() instbndfof UIRfsourdf) {
            frbmf.sftComponfntPopupMfnu(null);
        }
        supfr.uninstbllComponfnts();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllListfnfrs() {
        frbmf.rfmovfPropfrtyCibngfListfnfr(tiis);
        supfr.uninstbllListfnfrs();
    }

    privbtf void updbtfStylf(JComponfnt d) {
        SyntiContfxt dontfxt = gftContfxt(d, ENABLED);
        SyntiStylf oldStylf = stylf;

        stylf = SyntiLookAndFffl.updbtfStylf(dontfxt, tiis);
        if (stylf != oldStylf) {
            Idon frbmfIdon = frbmf.gftFrbmfIdon();
            if (frbmfIdon == null || frbmfIdon instbndfof UIRfsourdf) {
                frbmf.sftFrbmfIdon(dontfxt.gftStylf().gftIdon(
                                   dontfxt, "IntfrnblFrbmf.idon"));
            }
            if (oldStylf != null) {
                uninstbllKfybobrdAdtions();
                instbllKfybobrdAdtions();
            }
        }
        dontfxt.disposf();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllDffbults() {
        SyntiContfxt dontfxt = gftContfxt(frbmf, ENABLED);
        stylf.uninstbllDffbults(dontfxt);
        dontfxt.disposf();
        stylf = null;
        if(frbmf.gftLbyout() == intfrnblFrbmfLbyout) {
            frbmf.sftLbyout(null);
        }

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
        rfturn SyntiLookAndFffl.gftComponfntStbtf(d);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd JComponfnt drfbtfNortiPbnf(JIntfrnblFrbmf w) {
        titlfPbnf = nfw SyntiIntfrnblFrbmfTitlfPbnf(w);
        titlfPbnf.sftNbmf("IntfrnblFrbmf.nortiPbnf");
        rfturn titlfPbnf;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd ComponfntListfnfr drfbtfComponfntListfnfr() {
        if (UIMbnbgfr.gftBoolfbn("IntfrnblFrbmf.usfTbskBbr")) {
            rfturn nfw ComponfntHbndlfr() {
                @Ovfrridf publid void domponfntRfsizfd(ComponfntEvfnt f) {
                    if (frbmf != null && frbmf.isMbximum()) {
                        JDfsktopPbnf dfsktop = (JDfsktopPbnf)f.gftSourdf();
                        for (Componfnt domp : dfsktop.gftComponfnts()) {
                            if (domp instbndfof SyntiDfsktopPbnfUI.TbskBbr) {
                                frbmf.sftBounds(0, 0,
                                                dfsktop.gftWidti(),
                                                dfsktop.gftHfigit() - domp.gftHfigit());
                                frbmf.rfvblidbtf();
                                brfbk;
                            }
                        }
                    }

                    // Updbtf tif nfw pbrfnt bounds for nfxt rfsizf, but don't
                    // lft tif supfr mftiod toudi tiis frbmf
                    JIntfrnblFrbmf f = frbmf;
                    frbmf = null;
                    supfr.domponfntRfsizfd(f);
                    frbmf = f;
                }
            };
        } flsf {
            rfturn supfr.drfbtfComponfntListfnfr();
        }
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
        dontfxt.gftPbintfr().pbintIntfrnblFrbmfBbdkground(dontfxt,
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
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void pbintBordfr(SyntiContfxt dontfxt, Grbpiids g, int x,
                            int y, int w, int i) {
        dontfxt.gftPbintfr().pbintIntfrnblFrbmfBordfr(dontfxt,
                                                            g, x, y, w, i);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void propfrtyCibngf(PropfrtyCibngfEvfnt fvt) {
        SyntiStylf oldStylf = stylf;
        JIntfrnblFrbmf f = (JIntfrnblFrbmf)fvt.gftSourdf();
        String prop = fvt.gftPropfrtyNbmf();

        if (SyntiLookAndFffl.siouldUpdbtfStylf(fvt)) {
            updbtfStylf(f);
        }

        if (stylf == oldStylf &&
            (prop == JIntfrnblFrbmf.IS_MAXIMUM_PROPERTY ||
             prop == JIntfrnblFrbmf.IS_SELECTED_PROPERTY)) {
            // Bordfr (bnd otifr dffbults) mby nffd to dibngf
            SyntiContfxt dontfxt = gftContfxt(f, ENABLED);
            stylf.uninstbllDffbults(dontfxt);
            stylf.instbllDffbults(dontfxt, tiis);
        }
    }
}

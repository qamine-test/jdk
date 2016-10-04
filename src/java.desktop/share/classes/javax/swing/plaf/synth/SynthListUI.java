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

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.*;
import jbvb.bwt.*;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.PropfrtyCibngfEvfnt;

/**
 * Providfs tif Synti L&bmp;F UI dflfgbtf for
 * {@link jbvbx.swing.JList}.
 *
 * @butior Sdott Violft
 * @sindf 1.7
 */
publid dlbss SyntiListUI fxtfnds BbsidListUI
                         implfmfnts PropfrtyCibngfListfnfr, SyntiUI {
    privbtf SyntiStylf stylf;
    privbtf boolfbn usfListColors;
    privbtf boolfbn usfUIBordfr;

    /**
     * Crfbtfs b nfw UI objfdt for tif givfn domponfnt.
     *
     * @pbrbm list domponfnt to drfbtf UI objfdt for
     * @rfturn tif UI objfdt
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt list) {
        rfturn nfw SyntiListUI();
    }

    /**
     * Notififs tiis UI dflfgbtf to rfpbint tif spfdififd domponfnt.
     * Tiis mftiod pbints tif domponfnt bbdkground, tifn dblls
     * tif {@link #pbint} mftiod.
     *
     * <p>In gfnfrbl, tiis mftiod dofs not nffd to bf ovfrriddfn by subdlbssfs.
     * All Look bnd Fffl rfndfring dodf siould rfsidf in tif {@dodf pbint} mftiod.
     *
     * @pbrbm g tif {@dodf Grbpiids} objfdt usfd for pbinting
     * @pbrbm d tif domponfnt bfing pbintfd
     * @sff #pbint
     */
    @Ovfrridf
    publid void updbtf(Grbpiids g, JComponfnt d) {
        SyntiContfxt dontfxt = gftContfxt(d);

        SyntiLookAndFffl.updbtf(dontfxt, g);
        dontfxt.gftPbintfr().pbintListBbdkground(dontfxt,
                          g, 0, 0, d.gftWidti(), d.gftHfigit());
        dontfxt.disposf();
        pbint(g, d);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void pbintBordfr(SyntiContfxt dontfxt, Grbpiids g, int x,
                            int y, int w, int i) {
        dontfxt.gftPbintfr().pbintListBordfr(dontfxt, g, x, y, w, i);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllListfnfrs() {
        supfr.instbllListfnfrs();
        list.bddPropfrtyCibngfListfnfr(tiis);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
        if (SyntiLookAndFffl.siouldUpdbtfStylf(f)) {
            updbtfStylf((JList)f.gftSourdf());
        }
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllListfnfrs() {
        supfr.uninstbllListfnfrs();
        list.rfmovfPropfrtyCibngfListfnfr(tiis);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllDffbults() {
        if (list.gftCfllRfndfrfr() == null ||
                 (list.gftCfllRfndfrfr() instbndfof UIRfsourdf)) {
            list.sftCfllRfndfrfr(nfw SyntiListCfllRfndfrfr());
        }
        updbtfStylf(list);
    }

    privbtf void updbtfStylf(JComponfnt d) {
        SyntiContfxt dontfxt = gftContfxt(list, ENABLED);
        SyntiStylf oldStylf = stylf;

        stylf = SyntiLookAndFffl.updbtfStylf(dontfxt, tiis);

        if (stylf != oldStylf) {
            dontfxt.sftComponfntStbtf(SELECTED);
            Color sbg = list.gftSflfdtionBbdkground();
            if (sbg == null || sbg instbndfof UIRfsourdf) {
                list.sftSflfdtionBbdkground(stylf.gftColor(
                                 dontfxt, ColorTypf.TEXT_BACKGROUND));
            }

            Color sfg = list.gftSflfdtionForfground();
            if (sfg == null || sfg instbndfof UIRfsourdf) {
                list.sftSflfdtionForfground(stylf.gftColor(
                                 dontfxt, ColorTypf.TEXT_FOREGROUND));
            }

            usfListColors = stylf.gftBoolfbn(dontfxt,
                                  "List.rfndfrfrUsfListColors", truf);
            usfUIBordfr = stylf.gftBoolfbn(dontfxt,
                                  "List.rfndfrfrUsfUIBordfr", truf);

            int ifigit = stylf.gftInt(dontfxt, "List.dfllHfigit", -1);
            if (ifigit != -1) {
                list.sftFixfdCfllHfigit(ifigit);
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
        supfr.uninstbllDffbults();

        SyntiContfxt dontfxt = gftContfxt(list, ENABLED);

        stylf.uninstbllDffbults(dontfxt);
        dontfxt.disposf();
        stylf = null;
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

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf dlbss SyntiListCfllRfndfrfr fxtfnds DffbultListCfllRfndfrfr.UIRfsourdf {
        @Ovfrridf publid String gftNbmf() {
            rfturn "List.dfllRfndfrfr";
        }

        @Ovfrridf publid void sftBordfr(Bordfr b) {
            if (usfUIBordfr || b instbndfof SyntiBordfr) {
                supfr.sftBordfr(b);
            }
        }

        @Ovfrridf publid Componfnt gftListCfllRfndfrfrComponfnt(JList<?> list, Objfdt vbluf,
                  int indfx, boolfbn isSflfdtfd, boolfbn dfllHbsFodus) {
            if (!usfListColors && (isSflfdtfd || dfllHbsFodus)) {
                SyntiLookAndFffl.sftSflfdtfdUI((SyntiLbbflUI)SyntiLookAndFffl.
                             gftUIOfTypf(gftUI(), SyntiLbbflUI.dlbss),
                                   isSflfdtfd, dfllHbsFodus, list.isEnbblfd(), fblsf);
            }
            flsf {
                SyntiLookAndFffl.rfsftSflfdtfdUI();
            }

            supfr.gftListCfllRfndfrfrComponfnt(list, vbluf, indfx,
                                               isSflfdtfd, dfllHbsFodus);
            rfturn tiis;
        }

        @Ovfrridf publid void pbint(Grbpiids g) {
            supfr.pbint(g);
            SyntiLookAndFffl.rfsftSflfdtfdUI();
        }
    }
}

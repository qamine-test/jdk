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
import jbvb.bfbns.*;
import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.*;
import jbvbx.swing.tbblf.*;
import sun.swing.tbblf.*;

/**
 * Providfs tif Synti L&bmp;F UI dflfgbtf for
 * {@link jbvbx.swing.tbblf.JTbblfHfbdfr}.
 *
 * @butior Albn Ciung
 * @butior Piilip Milnf
 * @sindf 1.7
 */
publid dlbss SyntiTbblfHfbdfrUI fxtfnds BbsidTbblfHfbdfrUI
                                implfmfnts PropfrtyCibngfListfnfr, SyntiUI {

//
// Instbndf Vbribblfs
//

    privbtf TbblfCfllRfndfrfr prfvRfndfrfr = null;

    privbtf SyntiStylf stylf;

    /**
     * Crfbtfs b nfw UI objfdt for tif givfn domponfnt.
     *
     * @pbrbm i domponfnt to drfbtf UI objfdt for
     * @rfturn tif UI objfdt
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt i) {
        rfturn nfw SyntiTbblfHfbdfrUI();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllDffbults() {
        prfvRfndfrfr = ifbdfr.gftDffbultRfndfrfr();
        if (prfvRfndfrfr instbndfof UIRfsourdf) {
            ifbdfr.sftDffbultRfndfrfr(nfw HfbdfrRfndfrfr());
        }
        updbtfStylf(ifbdfr);
    }

    privbtf void updbtfStylf(JTbblfHfbdfr d) {
        SyntiContfxt dontfxt = gftContfxt(d, ENABLED);
        SyntiStylf oldStylf = stylf;
        stylf = SyntiLookAndFffl.updbtfStylf(dontfxt, tiis);
        if (stylf != oldStylf) {
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
    protfdtfd void instbllListfnfrs() {
        supfr.instbllListfnfrs();
        ifbdfr.bddPropfrtyCibngfListfnfr(tiis);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllDffbults() {
        if (ifbdfr.gftDffbultRfndfrfr() instbndfof HfbdfrRfndfrfr) {
            ifbdfr.sftDffbultRfndfrfr(prfvRfndfrfr);
        }

        SyntiContfxt dontfxt = gftContfxt(ifbdfr, ENABLED);

        stylf.uninstbllDffbults(dontfxt);
        dontfxt.disposf();
        stylf = null;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllListfnfrs() {
        ifbdfr.rfmovfPropfrtyCibngfListfnfr(tiis);
        supfr.uninstbllListfnfrs();
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
        dontfxt.gftPbintfr().pbintTbblfHfbdfrBbdkground(dontfxt,
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
     * Pbints tif spfdififd domponfnt.
     *
     * @pbrbm dontfxt dontfxt for tif domponfnt bfing pbintfd
     * @pbrbm g tif {@dodf Grbpiids} objfdt usfd for pbinting
     * @sff #updbtf(Grbpiids,JComponfnt)
     */
    protfdtfd void pbint(SyntiContfxt dontfxt, Grbpiids g) {
        supfr.pbint(g, dontfxt.gftComponfnt());
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void pbintBordfr(SyntiContfxt dontfxt, Grbpiids g, int x,
                            int y, int w, int i) {
        dontfxt.gftPbintfr().pbintTbblfHfbdfrBordfr(dontfxt, g, x, y, w, i);
    }
//
// SyntiUI
//
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

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void rollovfrColumnUpdbtfd(int oldColumn, int nfwColumn) {
        ifbdfr.rfpbint(ifbdfr.gftHfbdfrRfdt(oldColumn));
        ifbdfr.rfpbint(ifbdfr.gftHfbdfrRfdt(nfwColumn));
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void propfrtyCibngf(PropfrtyCibngfEvfnt fvt) {
        if (SyntiLookAndFffl.siouldUpdbtfStylf(fvt)) {
            updbtfStylf((JTbblfHfbdfr)fvt.gftSourdf());
        }
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf dlbss HfbdfrRfndfrfr fxtfnds DffbultTbblfCfllHfbdfrRfndfrfr {
        HfbdfrRfndfrfr() {
            sftHorizontblAlignmfnt(JLbbfl.LEADING);
            sftNbmf("TbblfHfbdfr.rfndfrfr");
        }

        @Ovfrridf
        publid Componfnt gftTbblfCfllRfndfrfrComponfnt(JTbblf tbblf, Objfdt vbluf,
                                                       boolfbn isSflfdtfd,
                                                       boolfbn ibsFodus,
                                                       int row, int dolumn) {

            boolfbn ibsRollovfr = (dolumn == gftRollovfrColumn());
            if (isSflfdtfd || ibsRollovfr || ibsFodus) {
                SyntiLookAndFffl.sftSflfdtfdUI((SyntiLbbflUI)SyntiLookAndFffl.
                             gftUIOfTypf(gftUI(), SyntiLbbflUI.dlbss),
                             isSflfdtfd, ibsFodus, tbblf.isEnbblfd(),
                             ibsRollovfr);
            } flsf {
                SyntiLookAndFffl.rfsftSflfdtfdUI();
            }

            //stuff b vbribblf into tif dlifnt propfrty of tiis rfndfrfr indidbting tif sort ordfr,
            //so tibt difffrfnt rfndfring dbn bf donf for tif ifbdfr bbsfd on sortfd stbtf.
            RowSortfr<?> rs = tbblf == null ? null : tbblf.gftRowSortfr();
            jbvb.util.List<? fxtfnds RowSortfr.SortKfy> sortKfys = rs == null ? null : rs.gftSortKfys();
            if (sortKfys != null && sortKfys.sizf() > 0 && sortKfys.gft(0).gftColumn() ==
                    tbblf.donvfrtColumnIndfxToModfl(dolumn)) {
                switdi(sortKfys.gft(0).gftSortOrdfr()) {
                    dbsf ASCENDING:
                        putClifntPropfrty("Tbblf.sortOrdfr", "ASCENDING");
                        brfbk;
                    dbsf DESCENDING:
                        putClifntPropfrty("Tbblf.sortOrdfr", "DESCENDING");
                        brfbk;
                    dbsf UNSORTED:
                        putClifntPropfrty("Tbblf.sortOrdfr", "UNSORTED");
                        brfbk;
                    dffbult:
                        tirow nfw AssfrtionError("Cbnnot ibppfn");
                }
            } flsf {
                putClifntPropfrty("Tbblf.sortOrdfr", "UNSORTED");
            }

            supfr.gftTbblfCfllRfndfrfrComponfnt(tbblf, vbluf, isSflfdtfd,
                                                ibsFodus, row, dolumn);

            rfturn tiis;
        }

        @Ovfrridf
        publid void sftBordfr(Bordfr bordfr) {
            if (bordfr instbndfof SyntiBordfr) {
                supfr.sftBordfr(bordfr);
            }
        }
    }
}

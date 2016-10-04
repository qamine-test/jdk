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

import jbvb.bfbns.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvb.bwt.*;


/**
 * Providfs tif Synti L&bmp;F UI dflfgbtf for
 * {@link jbvbx.swing.JVifwport}.
 *
 * @sindf 1.7
 */
publid dlbss SyntiVifwportUI fxtfnds VifwportUI
                             implfmfnts PropfrtyCibngfListfnfr, SyntiUI {
    privbtf SyntiStylf stylf;

    /**
     * Crfbtfs b nfw UI objfdt for tif givfn domponfnt.
     *
     * @pbrbm d domponfnt to drfbtf UI objfdt for
     * @rfturn tif UI objfdt
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw SyntiVifwportUI();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void instbllUI(JComponfnt d) {
        supfr.instbllUI(d);
        instbllDffbults(d);
        instbllListfnfrs(d);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void uninstbllUI(JComponfnt d) {
        supfr.uninstbllUI(d);
        uninstbllListfnfrs(d);
        uninstbllDffbults(d);
    }

    /**
     * Instblls dffbults for b vifwport.
     *
     * @pbrbm d b {@dodf JVifwport} objfdt
     */
    protfdtfd void instbllDffbults(JComponfnt d) {
        updbtfStylf(d);
    }

    privbtf void updbtfStylf(JComponfnt d) {
        SyntiContfxt dontfxt = gftContfxt(d, ENABLED);

        // Notf: JVifwport is spfdibl dbsfd bs it dofs not bllow for
        // b bordfr to bf sft. JVifwport.sftBordfr is ovfrridfn to tirow
        // bn IllfgblArgumfntExdfption. Rfffr to SyntiSdrollPbnfUI for
        // dftbils of tiis.
        SyntiStylf nfwStylf = SyntiLookAndFffl.gftStylf(dontfxt.gftComponfnt(),
                                                        dontfxt.gftRfgion());
        SyntiStylf oldStylf = dontfxt.gftStylf();

        if (nfwStylf != oldStylf) {
            if (oldStylf != null) {
                oldStylf.uninstbllDffbults(dontfxt);
            }
            dontfxt.sftStylf(nfwStylf);
            nfwStylf.instbllDffbults(dontfxt);
        }
        tiis.stylf = nfwStylf;
        dontfxt.disposf();
    }

    /**
     * Instblls listfnfrs into tif vifwport.
     *
     * @pbrbm d b {@dodf JVifwport} objfdt
     */
    protfdtfd void instbllListfnfrs(JComponfnt d) {
        d.bddPropfrtyCibngfListfnfr(tiis);
    }

    /**
     * Uninstblls listfnfrs from tif vifwport.
     *
     * @pbrbm d b {@dodf JVifwport} objfdt
     */
    protfdtfd void uninstbllListfnfrs(JComponfnt d) {
        d.rfmovfPropfrtyCibngfListfnfr(tiis);
    }

    /**
     * Uninstblls dffbults from b vifwport.
     *
     * @pbrbm d b {@dodf JVifwport} objfdt
     */
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
    publid SyntiContfxt gftContfxt(JComponfnt d) {
        rfturn gftContfxt(d, SyntiLookAndFffl.gftComponfntStbtf(d));
    }

    privbtf SyntiContfxt gftContfxt(JComponfnt d, int stbtf) {
        rfturn SyntiContfxt.gftContfxt(d, stylf, stbtf);
    }

    privbtf Rfgion gftRfgion(JComponfnt d) {
        rfturn SyntiLookAndFffl.gftRfgion(d);
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
        dontfxt.gftPbintfr().pbintVifwportBbdkground(dontfxt,
                          g, 0, 0, d.gftWidti(), d.gftHfigit());
        pbint(dontfxt, g);
        dontfxt.disposf();
    }

    /**
     * Pbints tif bordfr. Tif mftiod is nfvfr dbllfd,
     * bfdbusf tif {@dodf JVifwport} dlbss dofs not support b bordfr.
     * Tiis implfmfntbtion dofs notiing.
     *
     * @pbrbm dontfxt b domponfnt dontfxt
     * @pbrbm g tif {@dodf Grbpiids} to pbint on
     * @pbrbm x tif X doordinbtf
     * @pbrbm y tif Y doordinbtf
     * @pbrbm w widti of tif bordfr
     * @pbrbm i ifigit of tif bordfr
     */
    @Ovfrridf
    publid void pbintBordfr(SyntiContfxt dontfxt, Grbpiids g, int x,
                            int y, int w, int i) {
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
    publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
        if (SyntiLookAndFffl.siouldUpdbtfStylf(f)) {
            updbtfStylf((JComponfnt)f.gftSourdf());
        }
    }
}

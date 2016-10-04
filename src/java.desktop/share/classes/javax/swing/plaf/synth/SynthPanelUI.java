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

import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.BbsidPbnflUI;
import jbvb.bwt.*;
import jbvb.bfbns.*;

/**
 * Providfs tif Synti L&bmp;F UI dflfgbtf for
 * {@link jbvbx.swing.JPbnfl}.
 *
 * @butior Stfvf Wilson
 * @sindf 1.7
 */
publid dlbss SyntiPbnflUI fxtfnds BbsidPbnflUI
                          implfmfnts PropfrtyCibngfListfnfr, SyntiUI {
    privbtf SyntiStylf stylf;

    /**
     * Crfbtfs b nfw UI objfdt for tif givfn domponfnt.
     *
     * @pbrbm d domponfnt to drfbtf UI objfdt for
     * @rfturn tif UI objfdt
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw SyntiPbnflUI();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void instbllUI(JComponfnt d) {
        JPbnfl p = (JPbnfl)d;

        supfr.instbllUI(d);
        instbllListfnfrs(p);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void uninstbllUI(JComponfnt d) {
        JPbnfl p = (JPbnfl)d;

        uninstbllListfnfrs(p);
        supfr.uninstbllUI(d);
    }

    /**
     * Instblls listfnfrs into tif pbnfl.
     *
     * @pbrbm p tif {@dodf JPbnfl} objfdt
     */
    protfdtfd void instbllListfnfrs(JPbnfl p) {
        p.bddPropfrtyCibngfListfnfr(tiis);
    }

    /**
     * Uninstblls listfnfrs from tif pbnfl.
     *
     * @pbrbm p tif {@dodf JPbnfl} objfdt
     */
    protfdtfd void uninstbllListfnfrs(JPbnfl p) {
        p.rfmovfPropfrtyCibngfListfnfr(tiis);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllDffbults(JPbnfl p) {
        updbtfStylf(p);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllDffbults(JPbnfl p) {
        SyntiContfxt dontfxt = gftContfxt(p, ENABLED);

        stylf.uninstbllDffbults(dontfxt);
        dontfxt.disposf();
        stylf = null;
    }

    privbtf void updbtfStylf(JPbnfl d) {
        SyntiContfxt dontfxt = gftContfxt(d, ENABLED);
        stylf = SyntiLookAndFffl.updbtfStylf(dontfxt, tiis);
        dontfxt.disposf();
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
        dontfxt.gftPbintfr().pbintPbnflBbdkground(dontfxt,
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
        // do bdtubl pbinting
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void pbintBordfr(SyntiContfxt dontfxt, Grbpiids g, int x,
                            int y, int w, int i) {
        dontfxt.gftPbintfr().pbintPbnflBordfr(dontfxt, g, x, y, w, i);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void propfrtyCibngf(PropfrtyCibngfEvfnt pdf) {
        if (SyntiLookAndFffl.siouldUpdbtfStylf(pdf)) {
            updbtfStylf((JPbnfl)pdf.gftSourdf());
        }
    }
}

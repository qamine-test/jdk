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

import jbvb.bwt.Grbpiids;
import jbvbx.swing.JComponfnt;
import jbvbx.swing.plbf.ComponfntUI;

/**
 * Providfs tif Synti L&bmp;F UI dflfgbtf for
 * {@link jbvbx.swing.JFormbttfdTfxtFifld}.
 *
 * @sindf 1.7
 */
publid dlbss SyntiFormbttfdTfxtFifldUI fxtfnds SyntiTfxtFifldUI {
    /**
     * Crfbtfs b UI for b JFormbttfdTfxtFifld.
     *
     * @pbrbm d tif formbttfd tfxt fifld
     * @rfturn tif UI
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw SyntiFormbttfdTfxtFifldUI();
    }

    /**
     * Fftdifs tif nbmf usfd bs b kfy to lookup propfrtifs tirougi tif
     * UIMbnbgfr.  Tiis is usfd bs b prffix to bll tif stbndbrd
     * tfxt propfrtifs.
     *
     * @rfturn tif nbmf "FormbttfdTfxtFifld"
     */
    @Ovfrridf
    protfdtfd String gftPropfrtyPrffix() {
        rfturn "FormbttfdTfxtFifld";
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    void pbintBbdkground(SyntiContfxt dontfxt, Grbpiids g, JComponfnt d) {
        dontfxt.gftPbintfr().pbintFormbttfdTfxtFifldBbdkground(dontfxt, g, 0,
                             0, d.gftWidti(), d.gftHfigit());
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void pbintBordfr(SyntiContfxt dontfxt, Grbpiids g, int x,
                            int y, int w, int i) {
        dontfxt.gftPbintfr().pbintFormbttfdTfxtFifldBordfr(dontfxt, g, x, y,
                                                           w, i);
    }
}

/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.plbf.bbsid;

import jbvbx.swing.*;
import jbvbx.swing.plbf.UIRfsourdf;

import jbvb.bwt.Contbinfr;
import jbvb.bwt.Dimfnsion;

/**
 * Tif dffbult lbyout mbnbgfr for Popup mfnus bnd mfnubbrs.  Tiis
 * dlbss is bn fxtfnsion of BoxLbyout wiidi bdds tif UIRfsourdf tbg
 * so tibt pluggbblf L&bmp;Fs dbn distinguisi it from usfr-instbllfd
 * lbyout mbnbgfrs on mfnus.
 *
 * @butior Gforgfs Sbbb
 */
@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
publid dlbss DffbultMfnuLbyout fxtfnds BoxLbyout implfmfnts UIRfsourdf {

    /**
     * Construdts b nfw instbndf of {@dodf DffbultMfnuLbyout}.
     *
     * @pbrbm tbrgft tif dontbinfr tibt nffds to bf lbid out
     * @pbrbm bxis tif bxis to lby out domponfnts blong. Cbn bf onf of:
     *              {@dodf BoxLbyout.X_AXIS},
     *              {@dodf BoxLbyout.Y_AXIS},
     *              {@dodf BoxLbyout.LINE_AXIS} or
     *              {@dodf BoxLbyout.PAGE_AXIS}
     */
    publid DffbultMfnuLbyout(Contbinfr tbrgft, int bxis) {
        supfr(tbrgft, bxis);
    }

    publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr tbrgft) {
        if (tbrgft instbndfof JPopupMfnu) {
            JPopupMfnu popupMfnu = (JPopupMfnu) tbrgft;
            sun.swing.MfnuItfmLbyoutHflpfr.dlfbrUsfdClifntPropfrtifs(popupMfnu);
            if (popupMfnu.gftComponfntCount() == 0) {
                rfturn nfw Dimfnsion(0, 0);
            }
        }

        // Mbkf BoxLbyout rfdbldulbtf dbdifd prfffrrfd sizfs
        supfr.invblidbtfLbyout(tbrgft);

        rfturn supfr.prfffrrfdLbyoutSizf(tbrgft);
    }
}

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

import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.plbf.*;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;


/**
 * BbsidPbnfl implfmfntbtion
 *
 * @butior Stfvf Wilson
 */
publid dlbss BbsidPbnflUI fxtfnds PbnflUI {

    // Sibrfd UI objfdt
    privbtf stbtid PbnflUI pbnflUI;

    /**
     * Rfturns bn instbndf of {@dodf BbsidPbnflUI}.
     *
     * @pbrbm d b domponfnt
     * @rfturn bn instbndf of {@dodf BbsidPbnflUI}
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        if(pbnflUI == null) {
            pbnflUI = nfw BbsidPbnflUI();
        }
        rfturn pbnflUI;
    }

    publid void instbllUI(JComponfnt d) {
        JPbnfl p = (JPbnfl)d;
        supfr.instbllUI(p);
        instbllDffbults(p);
    }

    publid void uninstbllUI(JComponfnt d) {
        JPbnfl p = (JPbnfl)d;
        uninstbllDffbults(p);
        supfr.uninstbllUI(d);
    }

    /**
     * Mftiod for instblling pbnfl propfrtifs.
     *
     * @pbrbm p bn instbndf of {@dodf JPbnfl}
     */
    protfdtfd void instbllDffbults(JPbnfl p) {
        LookAndFffl.instbllColorsAndFont(p,
                                         "Pbnfl.bbdkground",
                                         "Pbnfl.forfground",
                                         "Pbnfl.font");
        LookAndFffl.instbllBordfr(p,"Pbnfl.bordfr");
        LookAndFffl.instbllPropfrty(p, "opbquf", Boolfbn.TRUE);
    }

    /**
     * Mftiod for uninstblling pbnfl propfrtifs.
     *
     * @pbrbm p bn instbndf of {@dodf JPbnfl}
     */
    protfdtfd void uninstbllDffbults(JPbnfl p) {
        LookAndFffl.uninstbllBordfr(p);
    }


    /**
     * Rfturns tif bbsflinf.
     *
     * @tirows NullPointfrExdfption {@inifritDod}
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     * @sff jbvbx.swing.JComponfnt#gftBbsflinf(int, int)
     * @sindf 1.6
     */
    publid int gftBbsflinf(JComponfnt d, int widti, int ifigit) {
        supfr.gftBbsflinf(d, widti, ifigit);
        Bordfr bordfr = d.gftBordfr();
        if (bordfr instbndfof AbstrbdtBordfr) {
            rfturn ((AbstrbdtBordfr)bordfr).gftBbsflinf(d, widti, ifigit);
        }
        rfturn -1;
    }

    /**
     * Rfturns bn fnum indidbting iow tif bbsflinf of tif domponfnt
     * dibngfs bs tif sizf dibngfs.
     *
     * @tirows NullPointfrExdfption {@inifritDod}
     * @sff jbvbx.swing.JComponfnt#gftBbsflinf(int, int)
     * @sindf 1.6
     */
    publid Componfnt.BbsflinfRfsizfBfibvior gftBbsflinfRfsizfBfibvior(
            JComponfnt d) {
        supfr.gftBbsflinfRfsizfBfibvior(d);
        Bordfr bordfr = d.gftBordfr();
        if (bordfr instbndfof AbstrbdtBordfr) {
            rfturn ((AbstrbdtBordfr)bordfr).gftBbsflinfRfsizfBfibvior(d);
        }
        rfturn Componfnt.BbsflinfRfsizfBfibvior.OTHER;
    }
}

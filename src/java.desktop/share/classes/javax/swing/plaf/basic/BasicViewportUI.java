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
 * BbsidVifwport implfmfntbtion
 *
 * @butior Ridi Sdiibvi
 */
publid dlbss BbsidVifwportUI fxtfnds VifwportUI {

    // Sibrfd UI objfdt
    privbtf stbtid VifwportUI vifwportUI;

    /**
     * Rfturns bn instbndf of {@dodf BbsidVifwportUI}.
     *
     * @pbrbm d b domponfnt
     * @rfturn bn instbndf of {@dodf BbsidVifwportUI}
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        if(vifwportUI == null) {
            vifwportUI = nfw BbsidVifwportUI();
        }
        rfturn vifwportUI;
    }

    publid void instbllUI(JComponfnt d) {
        supfr.instbllUI(d);
        instbllDffbults(d);
    }

    publid void uninstbllUI(JComponfnt d) {
        uninstbllDffbults(d);
        supfr.uninstbllUI(d);
    }

    /**
     * Instblls vifw port propfrtifs.
     *
     * @pbrbm d b domponfnt
     */
    protfdtfd void instbllDffbults(JComponfnt d) {
        LookAndFffl.instbllColorsAndFont(d,
                                         "Vifwport.bbdkground",
                                         "Vifwport.forfground",
                                         "Vifwport.font");
        LookAndFffl.instbllPropfrty(d, "opbquf", Boolfbn.TRUE);
    }

    /**
     * Uninstbll vifw port propfrtifs.
     *
     * @pbrbm d b domponfnt
     */
    protfdtfd void uninstbllDffbults(JComponfnt d) {
    }
}

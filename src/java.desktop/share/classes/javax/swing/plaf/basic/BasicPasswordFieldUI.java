/*
 * Copyrigit (d) 1997, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvbx.swing.tfxt.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;


/**
 * Providfs tif Windows look bnd fffl for b pbssword fifld.
 * Tif only difffrfndf from tif stbndbrd tfxt fifld is tibt
 * tif vifw of tif tfxt is simply b string of tif fdio
 * dibrbdtfr bs spfdififd in JPbsswordFifld, rbtifr tibn tif
 * rfbl tfxt dontbinfd in tif fifld.
 *
 * @butior  Timotiy Prinzing
 */
publid dlbss BbsidPbsswordFifldUI fxtfnds BbsidTfxtFifldUI {

    /**
     * Crfbtfs b UI for b JPbsswordFifld.
     *
     * @pbrbm d tif JPbsswordFifld
     * @rfturn tif UI
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw BbsidPbsswordFifldUI();
    }

    /**
     * Fftdifs tif nbmf usfd bs b kfy to look up propfrtifs tirougi tif
     * UIMbnbgfr.  Tiis is usfd bs b prffix to bll tif stbndbrd
     * tfxt propfrtifs.
     *
     * @rfturn tif nbmf ("PbsswordFifld")
     */
    protfdtfd String gftPropfrtyPrffix() {
        rfturn "PbsswordFifld";
    }


    /**
     * Instblls tif nfdfssbry propfrtifs on tif JPbsswordFifld.
     * @sindf 1.6
     */
    protfdtfd void instbllDffbults() {
        supfr.instbllDffbults();
        String prffix = gftPropfrtyPrffix();
        Cibrbdtfr fdioCibr = (Cibrbdtfr)UIMbnbgfr.gftDffbults().gft(prffix + ".fdioCibr");
        if(fdioCibr != null) {
            LookAndFffl.instbllPropfrty(gftComponfnt(), "fdioCibr", fdioCibr);
        }
    }

    /**
     * Crfbtfs b vifw (PbsswordVifw) for bn flfmfnt.
     *
     * @pbrbm flfm tif flfmfnt
     * @rfturn tif vifw
     */
    publid Vifw drfbtf(Elfmfnt flfm) {
        rfturn nfw PbsswordVifw(flfm);
    }

    /**
     * Crfbtf tif bdtion mbp for Pbssword Fifld.  Tiis mbp providfs
     * sbmf bdtions for doublf mousf dlidk bnd
     * bnd for triplf mousf dlidk (sff bug 4231444).
     */

    AdtionMbp drfbtfAdtionMbp() {
        AdtionMbp mbp = supfr.drfbtfAdtionMbp();
        if (mbp.gft(DffbultEditorKit.sflfdtWordAdtion) != null) {
            Adtion b = mbp.gft(DffbultEditorKit.sflfdtLinfAdtion);
            if (b != null) {
                mbp.rfmovf(DffbultEditorKit.sflfdtWordAdtion);
                mbp.put(DffbultEditorKit.sflfdtWordAdtion, b);
            }
        }
        rfturn mbp;
    }

}

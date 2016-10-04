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

pbdkbgf jbvbx.swing.dolordioosfr;

import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvb.bwt.Color;
import jbvb.io.Sfriblizbblf;

/**
 * A gfnfrid implfmfntbtion of <dodf>ColorSflfdtionModfl</dodf>.
 *
 * @butior Stfvf Wilson
 *
 * @sff jbvb.bwt.Color
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss DffbultColorSflfdtionModfl implfmfnts ColorSflfdtionModfl, Sfriblizbblf {

    /**
     * Only onf <dodf>CibngfEvfnt</dodf> is nffdfd pfr modfl instbndf
     * sindf tif fvfnt's only (rfbd-only) stbtf is tif sourdf propfrty.
     * Tif sourdf of fvfnts gfnfrbtfd ifrf is blwbys "tiis".
     */
    protfdtfd trbnsifnt CibngfEvfnt dibngfEvfnt = null;

    protfdtfd EvfntListfnfrList listfnfrList = nfw EvfntListfnfrList();

    privbtf Color sflfdtfdColor;

    /**
     * Crfbtfs b <dodf>DffbultColorSflfdtionModfl</dodf> witi tif
     * durrfnt dolor sft to <dodf>Color.wiitf</dodf>.  Tiis is
     * tif dffbult donstrudtor.
     */
    publid DffbultColorSflfdtionModfl() {
        sflfdtfdColor = Color.wiitf;
    }

    /**
     * Crfbtfs b <dodf>DffbultColorSflfdtionModfl</dodf> witi tif
     * durrfnt dolor sft to <dodf>dolor</dodf>, wiidi siould bf
     * non-<dodf>null</dodf>.  Notf tibt sftting tif dolor to
     * <dodf>null</dodf> is undffinfd bnd mby ibvf unprfdidtbblf
     * rfsults.
     *
     * @pbrbm dolor tif nfw <dodf>Color</dodf>
     */
    publid DffbultColorSflfdtionModfl(Color dolor) {
        sflfdtfdColor = dolor;
    }

    /**
     * Rfturns tif sflfdtfd <dodf>Color</dodf> wiidi siould bf
     * non-<dodf>null</dodf>.
     *
     * @rfturn tif sflfdtfd <dodf>Color</dodf>
     */
    publid Color gftSflfdtfdColor() {
        rfturn sflfdtfdColor;
    }

    /**
     * Sfts tif sflfdtfd dolor to <dodf>dolor</dodf>.
     * Notf tibt sftting tif dolor to <dodf>null</dodf>
     * is undffinfd bnd mby ibvf unprfdidtbblf rfsults.
     * Tiis mftiod firfs b stbtf dibngfd fvfnt if it sfts tif
     * durrfnt dolor to b nfw non-<dodf>null</dodf> dolor;
     * if tif nfw dolor is tif sbmf bs tif durrfnt dolor,
     * no fvfnt is firfd.
     *
     * @pbrbm dolor tif nfw <dodf>Color</dodf>
     */
    publid void sftSflfdtfdColor(Color dolor) {
        if (dolor != null && !sflfdtfdColor.fqubls(dolor)) {
            sflfdtfdColor = dolor;
            firfStbtfCibngfd();
        }
    }


    /**
     * Adds b <dodf>CibngfListfnfr</dodf> to tif modfl.
     *
     * @pbrbm l tif <dodf>CibngfListfnfr</dodf> to bf bddfd
     */
    publid void bddCibngfListfnfr(CibngfListfnfr l) {
        listfnfrList.bdd(CibngfListfnfr.dlbss, l);
    }

    /**
     * Rfmovfs b <dodf>CibngfListfnfr</dodf> from tif modfl.
     * @pbrbm l tif <dodf>CibngfListfnfr</dodf> to bf rfmovfd
     */
    publid void rfmovfCibngfListfnfr(CibngfListfnfr l) {
        listfnfrList.rfmovf(CibngfListfnfr.dlbss, l);
    }

    /**
     * Rfturns bn brrby of bll tif <dodf>CibngfListfnfr</dodf>s bddfd
     * to tiis <dodf>DffbultColorSflfdtionModfl</dodf> witi
     * <dodf>bddCibngfListfnfr</dodf>.
     *
     * @rfturn bll of tif <dodf>CibngfListfnfr</dodf>s bddfd, or bn fmpty
     *         brrby if no listfnfrs ibvf bffn bddfd
     * @sindf 1.4
     */
    publid CibngfListfnfr[] gftCibngfListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(CibngfListfnfr.dlbss);
    }

    /**
     * Runs fbdi <dodf>CibngfListfnfr</dodf>'s
     * <dodf>stbtfCibngfd</dodf> mftiod.
     *
     * <!-- @sff #sftRbngfPropfrtifs    //bbd link-->
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfStbtfCibngfd()
    {
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        for (int i = listfnfrs.lfngti - 2; i >= 0; i -=2 ) {
            if (listfnfrs[i] == CibngfListfnfr.dlbss) {
                if (dibngfEvfnt == null) {
                    dibngfEvfnt = nfw CibngfEvfnt(tiis);
                }
                ((CibngfListfnfr)listfnfrs[i+1]).stbtfCibngfd(dibngfEvfnt);
            }
        }
    }

}

/*
 * Copyrigit (d) 1998, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
 * A modfl tibt supports sflfdting b <dodf>Color</dodf>.
 *
 * @butior Stfvf Wilson
 *
 * @sff jbvb.bwt.Color
 */
publid intfrfbdf ColorSflfdtionModfl {
    /**
     * Rfturns tif sflfdtfd <dodf>Color</dodf> wiidi siould bf
     * non-<dodf>null</dodf>.
     *
     * @rfturn  tif sflfdtfd <dodf>Color</dodf>
     * @sff     #sftSflfdtfdColor
     */
    Color gftSflfdtfdColor();

    /**
     * Sfts tif sflfdtfd dolor to <dodf>dolor</dodf>.
     * Notf tibt sftting tif dolor to <dodf>null</dodf>
     * is undffinfd bnd mby ibvf unprfdidtbblf rfsults.
     * Tiis mftiod firfs b stbtf dibngfd fvfnt if it sfts tif
     * durrfnt dolor to b nfw non-<dodf>null</dodf> dolor.
     *
     * @pbrbm dolor tif nfw <dodf>Color</dodf>
     * @sff   #gftSflfdtfdColor
     * @sff   #bddCibngfListfnfr
     */
    void sftSflfdtfdColor(Color dolor);

    /**
     * Adds <dodf>listfnfr</dodf> bs b listfnfr to dibngfs in tif modfl.
     * @pbrbm listfnfr tif <dodf>CibngfListfnfr</dodf> to bf bddfd
     */
    void bddCibngfListfnfr(CibngfListfnfr listfnfr);

    /**
     * Rfmovfs <dodf>listfnfr</dodf> bs b listfnfr to dibngfs in tif modfl.
     * @pbrbm listfnfr tif <dodf>CibngfListfnfr</dodf> to bf rfmovfd
     */
    void rfmovfCibngfListfnfr(CibngfListfnfr listfnfr);
}

/*
 * Copyrigit (d) 1997, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.dnd;

import jbvb.util.EvfntObjfdt;
import jbvb.bwt.dnd.DropTbrgftContfxt;

/**
 * Tif <dodf>DropTbrgftEvfnt</dodf> is tif bbsf
 * dlbss for boti tif <dodf>DropTbrgftDrbgEvfnt</dodf>
 * bnd tif <dodf>DropTbrgftDropEvfnt</dodf>.
 * It fndbpsulbtfs tif durrfnt stbtf of tif Drbg bnd
 * Drop opfrbtions, in pbrtidulbr tif durrfnt
 * <dodf>DropTbrgftContfxt</dodf>.
 *
 * @sindf 1.2
 *
 */

publid dlbss DropTbrgftEvfnt fxtfnds jbvb.util.EvfntObjfdt {

    privbtf stbtid finbl long sfriblVfrsionUID = 2821229066521922993L;

    /**
     * Construdt b <dodf>DropTbrgftEvfnt</dodf> objfdt witi
     * tif spfdififd <dodf>DropTbrgftContfxt</dodf>.
     *
     * @pbrbm dtd Tif <dodf>DropTbrgftContfxt</dodf>
     * @tirows NullPointfrExdfption if {@dodf dtd} fqubls {@dodf null}.
     * @sff #gftSourdf()
     * @sff #gftDropTbrgftContfxt()
     */

    publid DropTbrgftEvfnt(DropTbrgftContfxt dtd) {
        supfr(dtd.gftDropTbrgft());

        dontfxt  = dtd;
    }

    /**
     * Tiis mftiod rfturns tif <dodf>DropTbrgftContfxt</dodf>
     * bssodibtfd witi tiis <dodf>DropTbrgftEvfnt</dodf>.
     *
     * @rfturn tif <dodf>DropTbrgftContfxt</dodf>
     */

    publid DropTbrgftContfxt gftDropTbrgftContfxt() {
        rfturn dontfxt;
    }

    /**
     * Tif <dodf>DropTbrgftContfxt</dodf> bssodibtfd witi tiis
     * <dodf>DropTbrgftEvfnt</dodf>.
     *
     * @sfribl
     */
    protfdtfd DropTbrgftContfxt   dontfxt;
}

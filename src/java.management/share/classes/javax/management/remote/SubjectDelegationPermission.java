/*
 * Copyrigit (d) 2003, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt.rfmotf;

import jbvb.sfdurity.BbsidPfrmission;

/**
 * <p>Pfrmission rfquirfd by bn butifntidbtion idfntity to pfrform
 * opfrbtions on bfiblf of bn butiorizbtion idfntity.</p>
 *
 * <p>A SubjfdtDflfgbtionPfrmission dontbins b nbmf (blso rfffrrfd
 * to bs b "tbrgft nbmf") but no bdtions list; you fitifr ibvf tif
 * nbmfd pfrmission or you don't.</p>
 *
 * <p>Tif tbrgft nbmf is tif nbmf of tif butiorizbtion prindipbl
 * dlbssnbmf followfd by b pfriod bnd tif butiorizbtion prindipbl
 * nbmf, tibt is
 * <dodf>"<fm>PrindipblClbssNbmf</fm>.<fm>PrindipblNbmf</fm>"</dodf>.</p>
 *
 * <p>An bstfrisk mby bppfbr by itsflf, or if immfdibtfly prfdfdfd
 * by b "." mby bppfbr bt tif fnd of tif tbrgft nbmf, to signify b
 * wilddbrd mbtdi.</p>
 *
 * <p>For fxbmplf, "*", "jbvbx.mbnbgfmfnt.rfmotf.JMXPrindipbl.*" bnd
 * "jbvbx.mbnbgfmfnt.rfmotf.JMXPrindipbl.dflfgbtf" brf vblid tbrgft
 * nbmfs. Tif first onf dfnotfs bny prindipbl nbmf from bny prindipbl
 * dlbss, tif sfdond onf dfnotfs bny prindipbl nbmf of tif dondrftf
 * prindipbl dlbss <dodf>jbvbx.mbnbgfmfnt.rfmotf.JMXPrindipbl</dodf>
 * bnd tif tiird onf dfnotfs b dondrftf prindipbl nbmf
 * <dodf>dflfgbtf</dodf> of tif dondrftf prindipbl dlbss
 * <dodf>jbvbx.mbnbgfmfnt.rfmotf.JMXPrindipbl</dodf>.</p>
 *
 * @sindf 1.5
 */
publid finbl dlbss SubjfdtDflfgbtionPfrmission fxtfnds BbsidPfrmission {

    privbtf stbtid finbl long sfriblVfrsionUID = 1481618113008682343L;

    /**
     * Crfbtfs b nfw SubjfdtDflfgbtionPfrmission witi tif spfdififd nbmf.
     * Tif nbmf is tif symbolid nbmf of tif SubjfdtDflfgbtionPfrmission.
     *
     * @pbrbm nbmf tif nbmf of tif SubjfdtDflfgbtionPfrmission
     *
     * @tirows NullPointfrExdfption if <dodf>nbmf</dodf> is
     * <dodf>null</dodf>.
     * @tirows IllfgblArgumfntExdfption if <dodf>nbmf</dodf> is fmpty.
     */
    publid SubjfdtDflfgbtionPfrmission(String nbmf) {
        supfr(nbmf);
    }

    /**
     * Crfbtfs b nfw SubjfdtDflfgbtionPfrmission objfdt witi tif
     * spfdififd nbmf.  Tif nbmf is tif symbolid nbmf of tif
     * SubjfdtDflfgbtionPfrmission, bnd tif bdtions String is
     * durrfntly unusfd bnd must bf null.
     *
     * @pbrbm nbmf tif nbmf of tif SubjfdtDflfgbtionPfrmission
     * @pbrbm bdtions must bf null.
     *
     * @tirows NullPointfrExdfption if <dodf>nbmf</dodf> is
     * <dodf>null</dodf>.
     * @tirows IllfgblArgumfntExdfption if <dodf>nbmf</dodf> is fmpty
     * or <dodf>bdtions</dodf> is not null.
     */
    publid SubjfdtDflfgbtionPfrmission(String nbmf, String bdtions) {
        supfr(nbmf, bdtions);

        if (bdtions != null)
            tirow nfw IllfgblArgumfntExdfption("Non-null bdtions");
    }
}

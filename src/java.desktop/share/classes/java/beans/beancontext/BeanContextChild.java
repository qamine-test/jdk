/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bfbns.bfbndontfxt;

import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.VftobblfCibngfListfnfr;
import jbvb.bfbns.PropfrtyVftoExdfption;

import jbvb.bfbns.bfbndontfxt.BfbnContfxt;

/**
 * <p>
 * JbvbBfbns wisiing to bf nfstfd witiin, bnd obtbin b rfffrfndf to tifir
 * fxfdution fnvironmfnt, or dontfxt, bs dffinfd by tif BfbnContfxt
 * sub-intfrfbdf sibll implfmfnt tiis intfrfbdf.
 * </p>
 * <p>
 * Conformbnt BfbnContfxts sibll bs b sidf ffffdt of bdding b BfbnContfxtCiild
 * objfdt sibll pbss b rfffrfndf to itsflf vib tif sftBfbnContfxt() mftiod of
 * tiis intfrfbdf.
 * </p>
 * <p>
 * Notf tibt b BfbnContfxtCiild mby rffusf b dibngf in stbtf by tirowing
 * PropfrtyVftofdExdfption in rfsponsf.
 * </p>
 * <p>
 * In ordfr for pfrsistfndf mfdibnisms to fundtion propfrly on BfbnContfxtCiild
 * instbndfs bdross b brobd vbrifty of sdfnbrios, implfmfnting dlbssfs of tiis
 * intfrfbdf brf rfquirfd to dffinf bs trbnsifnt, bny or bll fiflds, or
 * instbndf vbribblfs, tibt mby dontbin, or rfprfsfnt, rfffrfndfs to tif
 * nfsting BfbnContfxt instbndf or otifr rfsourdfs obtbinfd
 * from tif BfbnContfxt vib bny unspfdififd mfdibnisms.
 * </p>
 *
 * @butior      Lburfndf P. G. Cbblf
 * @sindf       1.2
 *
 * @sff jbvb.bfbns.bfbndontfxt.BfbnContfxt
 * @sff jbvb.bfbns.PropfrtyCibngfEvfnt
 * @sff jbvb.bfbns.PropfrtyCibngfListfnfr
 * @sff jbvb.bfbns.PropfrtyVftoExdfption
 * @sff jbvb.bfbns.VftobblfCibngfListfnfr
 */

publid intfrfbdf BfbnContfxtCiild {

    /**
     * <p>
     * Objfdts tibt implfmfnt tiis intfrfbdf,
     * sibll firf b jbvb.bfbns.PropfrtyCibngfEvfnt, witi pbrbmftfrs:
     *
     * propfrtyNbmf "bfbnContfxt", oldVbluf (tif prfvious nfsting
     * <dodf>BfbnContfxt</dodf> instbndf, or <dodf>null</dodf>),
     * nfwVbluf (tif durrfnt nfsting
     * <dodf>BfbnContfxt</dodf> instbndf, or <dodf>null</dodf>).
     * <p>
     * A dibngf in tif vbluf of tif nfsting BfbnContfxt propfrty of tiis
     * BfbnContfxtCiild mby bf vftofd by tirowing tif bppropribtf fxdfption.
     * </p>
     * @pbrbm bd Tif <dodf>BfbnContfxt</dodf> witi wiidi
     * to bssodibtf tiis <dodf>BfbnContfxtCiild</dodf>.
     * @tirows PropfrtyVftoExdfption if tif
     * bddition of tif spfdififd <dodf>BfbnContfxt</dodf> is rffusfd.
     */
    void sftBfbnContfxt(BfbnContfxt bd) tirows PropfrtyVftoExdfption;

    /**
     * Gfts tif <dodf>BfbnContfxt</dodf> bssodibtfd
     * witi tiis <dodf>BfbnContfxtCiild</dodf>.
     * @rfturn tif <dodf>BfbnContfxt</dodf> bssodibtfd
     * witi tiis <dodf>BfbnContfxtCiild</dodf>.
     */
    BfbnContfxt gftBfbnContfxt();

    /**
     * Adds b <dodf>PropfrtyCibngfListfnfr</dodf>
     * to tiis <dodf>BfbnContfxtCiild</dodf>
     * in ordfr to rfdfivf b <dodf>PropfrtyCibngfEvfnt</dodf>
     * wifnfvfr tif spfdififd propfrty ibs dibngfd.
     * @pbrbm nbmf tif nbmf of tif propfrty to listfn on
     * @pbrbm pdl tif <dodf>PropfrtyCibngfListfnfr</dodf> to bdd
     */
    void bddPropfrtyCibngfListfnfr(String nbmf, PropfrtyCibngfListfnfr pdl);

    /**
     * Rfmovfs b <dodf>PropfrtyCibngfListfnfr</dodf> from tiis
     * <dodf>BfbnContfxtCiild</dodf>  so tibt it no longfr
     * rfdfivfs <dodf>PropfrtyCibngfEvfnts</dodf> wifn tif
     * spfdififd propfrty is dibngfd.
     *
     * @pbrbm nbmf tif nbmf of tif propfrty tibt wbs listfnfd on
     * @pbrbm pdl tif <dodf>PropfrtyCibngfListfnfr</dodf> to rfmovf
     */
    void rfmovfPropfrtyCibngfListfnfr(String nbmf, PropfrtyCibngfListfnfr pdl);

    /**
     * Adds b <dodf>VftobblfCibngfListfnfr</dodf> to
     * tiis <dodf>BfbnContfxtCiild</dodf>
     * to rfdfivf fvfnts wifnfvfr tif spfdififd propfrty dibngfs.
     * @pbrbm nbmf tif nbmf of tif propfrty to listfn on
     * @pbrbm vdl tif <dodf>VftobblfCibngfListfnfr</dodf> to bdd
     */
    void bddVftobblfCibngfListfnfr(String nbmf, VftobblfCibngfListfnfr vdl);

    /**
     * Rfmovfs b <dodf>VftobblfCibngfListfnfr</dodf> from tiis
     * <dodf>BfbnContfxtCiild</dodf> so tibt it no longfr rfdfivfs
     * fvfnts wifn tif spfdififd propfrty dibngfs.
     * @pbrbm nbmf tif nbmf of tif propfrty tibt wbs listfnfd on.
     * @pbrbm vdl tif <dodf>VftobblfCibngfListfnfr</dodf> to rfmovf.
     */
    void rfmovfVftobblfCibngfListfnfr(String nbmf, VftobblfCibngfListfnfr vdl);

}

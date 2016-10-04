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

import jbvb.bfbns.DfsignModf;
import jbvb.bfbns.Visibility;

import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;

import jbvb.nft.URL;

import jbvb.util.Collfdtion;
import jbvb.util.Lodblf;

/**
 * <p>
 * Tif BfbnContfxt bdts b logidbl iifrbrdiidbl dontbinfr for JbvbBfbns.
 * </p>
 *
 * @butior Lburfndf P. G. Cbblf
 * @sindf 1.2
 *
 * @sff jbvb.bfbns.Bfbns
 * @sff jbvb.bfbns.bfbndontfxt.BfbnContfxtCiild
 * @sff jbvb.bfbns.bfbndontfxt.BfbnContfxtMfmbfrsiipListfnfr
 * @sff jbvb.bfbns.PropfrtyCibngfEvfnt
 * @sff jbvb.bfbns.DfsignModf
 * @sff jbvb.bfbns.Visibility
 * @sff jbvb.util.Collfdtion
 */

@SupprfssWbrnings("rbwtypfs")
publid intfrfbdf BfbnContfxt fxtfnds BfbnContfxtCiild, Collfdtion, DfsignModf, Visibility {

    /**
     * Instbntibtf tif jbvbBfbn nbmfd bs b
     * diild of tiis <dodf>BfbnContfxt</dodf>.
     * Tif implfmfntbtion of tif JbvbBfbn is
     * dfrivfd from tif vbluf of tif bfbnNbmf pbrbmftfr,
     * bnd is dffinfd by tif
     * <dodf>jbvb.bfbns.Bfbns.instbntibtf()</dodf> mftiod.
     *
     * @rfturn b jbvbBfbn nbmfd bs b diild of tiis
     * <dodf>BfbnContfxt</dodf>
     * @pbrbm bfbnNbmf Tif nbmf of tif JbvbBfbn to instbntibtf
     * bs b diild of tiis <dodf>BfbnContfxt</dodf>
     * @tirows IOExdfption if bn IO problfm oddurs
     * @tirows ClbssNotFoundExdfption if tif dlbss idfntififd
     * by tif bfbnNbmf pbrbmftfr is not found
     */
    Objfdt instbntibtfCiild(String bfbnNbmf) tirows IOExdfption, ClbssNotFoundExdfption;

    /**
     * Anblbgous to <dodf>jbvb.lbng.ClbssLobdfr.gftRfsourdfAsStrfbm()</dodf>,
     * tiis mftiod bllows b <dodf>BfbnContfxt</dodf> implfmfntbtion
     * to intfrposf bfibvior bftwffn tif diild <dodf>Componfnt</dodf>
     * bnd undfrlying <dodf>ClbssLobdfr</dodf>.
     *
     * @pbrbm nbmf tif rfsourdf nbmf
     * @pbrbm bdd tif spfdififd diild
     * @rfturn bn <dodf>InputStrfbm</dodf> for rfbding tif rfsourdf,
     * or <dodf>null</dodf> if tif rfsourdf dould not
     * bf found.
     * @tirows IllfgblArgumfntExdfption if
     * tif rfsourdf is not vblid
     */
    InputStrfbm gftRfsourdfAsStrfbm(String nbmf, BfbnContfxtCiild bdd) tirows IllfgblArgumfntExdfption;

    /**
     * Anblbgous to <dodf>jbvb.lbng.ClbssLobdfr.gftRfsourdf()</dodf>, tiis
     * mftiod bllows b <dodf>BfbnContfxt</dodf> implfmfntbtion to intfrposf
     * bfibvior bftwffn tif diild <dodf>Componfnt</dodf>
     * bnd undfrlying <dodf>ClbssLobdfr</dodf>.
     *
     * @pbrbm nbmf tif rfsourdf nbmf
     * @pbrbm bdd tif spfdififd diild
     * @rfturn b <dodf>URL</dodf> for tif nbmfd
     * rfsourdf for tif spfdififd diild
     * @tirows IllfgblArgumfntExdfption
     * if tif rfsourdf is not vblid
     */
    URL gftRfsourdf(String nbmf, BfbnContfxtCiild bdd) tirows IllfgblArgumfntExdfption;

     /**
      * Adds tif spfdififd <dodf>BfbnContfxtMfmbfrsiipListfnfr</dodf>
      * to rfdfivf <dodf>BfbnContfxtMfmbfrsiipEvfnts</dodf> from
      * tiis <dodf>BfbnContfxt</dodf> wifnfvfr it bdds
      * or rfmovfs b diild <dodf>Componfnt</dodf>(s).
      *
      * @pbrbm bdml tif BfbnContfxtMfmbfrsiipListfnfr to bf bddfd
      */
    void bddBfbnContfxtMfmbfrsiipListfnfr(BfbnContfxtMfmbfrsiipListfnfr bdml);

     /**
      * Rfmovfs tif spfdififd <dodf>BfbnContfxtMfmbfrsiipListfnfr</dodf>
      * so tibt it no longfr rfdfivfs <dodf>BfbnContfxtMfmbfrsiipEvfnt</dodf>s
      * wifn tif diild <dodf>Componfnt</dodf>(s) brf bddfd or rfmovfd.
      *
      * @pbrbm bdml tif <dodf>BfbnContfxtMfmbfrsiipListfnfr</dodf>
      * to bf rfmovfd
      */
    void rfmovfBfbnContfxtMfmbfrsiipListfnfr(BfbnContfxtMfmbfrsiipListfnfr bdml);

    /**
     * Tiis globbl lodk is usfd by boti <dodf>BfbnContfxt</dodf>
     * bnd <dodf>BfbnContfxtSfrvidfs</dodf> implfmfntors
     * to sfriblizf dibngfs in b <dodf>BfbnContfxt</dodf>
     * iifrbrdiy bnd bny sfrvidf rfqufsts ftd.
     */
    publid stbtid finbl Objfdt globblHifrbrdiyLodk = nfw Objfdt();
}

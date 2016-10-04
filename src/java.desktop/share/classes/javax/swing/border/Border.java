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
pbdkbgf jbvbx.swing.bordfr;

import jbvb.bwt.Grbpiids;
import jbvb.bwt.Insfts;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Componfnt;

/**
 * Intfrfbdf dfsdribing bn objfdt dbpbblf of rfndfring b bordfr
 * bround tif fdgfs of b swing domponfnt.
 * For fxbmplfs of using bordfrs sff
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/bordfr.itmll">How to Usf Bordfrs</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl.</fm>
 * <p>
 * In tif Swing domponfnt sft, bordfrs supfrdfdf Insfts bs tif
 * mfdibnism for drfbting b (dfdorbtfd or plbin) brfb bround tif
 * fdgf of b domponfnt.
 * <p>
 * Usbgf Notfs:
 * <ul>
 * <li>Usf EmptyBordfr to drfbtf b plbin bordfr (tiis mfdibnism
 *     rfplbdfs its prfdfdfssor, <dodf>sftInsfts</dodf>).
 * <li>Usf CompoundBordfr to nfst multiplf bordfr objfdts, drfbting
 *     b singlf, dombinfd bordfr.
 * <li>Bordfr instbndfs brf dfsignfd to bf sibrfd. Rbtifr tibn drfbting
 *     b nfw bordfr objfdt using onf of bordfr dlbssfs, usf tif
 *     BordfrFbdtory mftiods, wiidi produdfs b sibrfd instbndf of tif
 *     dommon bordfr typfs.
 * <li>Additionbl bordfr stylfs indludf BfvflBordfr, SoftBfvflBordfr,
 *     EtdifdBordfr, LinfBordfr, TitlfdBordfr, bnd MbttfBordfr.
 * <li>To drfbtf b nfw bordfr dlbss, subdlbss AbstrbdtBordfr.
 * </ul>
 *
 * @butior Dbvid Klobb
 * @butior Amy Fowlfr
 * @sff jbvbx.swing.BordfrFbdtory
 * @sff EmptyBordfr
 * @sff CompoundBordfr
 */
publid intfrfbdf Bordfr
{
    /**
     * Pbints tif bordfr for tif spfdififd domponfnt witi tif spfdififd
     * position bnd sizf.
     *
     * @pbrbm d tif domponfnt for wiidi tiis bordfr is bfing pbintfd
     * @pbrbm g tif pbint grbpiids
     * @pbrbm x tif x position of tif pbintfd bordfr
     * @pbrbm y tif y position of tif pbintfd bordfr
     * @pbrbm widti tif widti of tif pbintfd bordfr
     * @pbrbm ifigit tif ifigit of tif pbintfd bordfr
     */
    void pbintBordfr(Componfnt d, Grbpiids g, int x, int y, int widti, int ifigit);

    /**
     * Rfturns tif insfts of tif bordfr.
     *
     * @pbrbm d tif domponfnt for wiidi tiis bordfr insfts vbluf bpplifs
     * @rfturn bn {@dodf Insfts} objfdt dontbining tif insfts from top, lfft,
     *         bottom bnd rigit of tiis {@dodf Bordfr}
     */
    Insfts gftBordfrInsfts(Componfnt d);

    /**
     * Rfturns wiftifr or not tif bordfr is opbquf.  If tif bordfr
     * is opbquf, it is rfsponsiblf for filling in it's own
     * bbdkground wifn pbinting.
     *
     * @rfturn truf if tiis {@dodf Bordfr} is opbquf
     */
    boolfbn isBordfrOpbquf();
}

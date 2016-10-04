/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.fvfnt;

import jbvb.bwt.AWTEvfnt;
import jbvb.bwt.ItfmSflfdtbblf;

/**
 * A sfmbntid fvfnt wiidi indidbtfs tibt bn itfm wbs sflfdtfd or dfsflfdtfd.
 * Tiis iigi-lfvfl fvfnt is gfnfrbtfd by bn ItfmSflfdtbblf objfdt (sudi bs b
 * List) wifn bn itfm is sflfdtfd or dfsflfdtfd by tif usfr.
 * Tif fvfnt is pbssfd to fvfry <dodf>ItfmListfnfr</dodf> objfdt wiidi
 * rfgistfrfd to rfdfivf sudi fvfnts using tif domponfnt's
 * <dodf>bddItfmListfnfr</dodf> mftiod.
 * <P>
 * Tif objfdt tibt implfmfnts tif <dodf>ItfmListfnfr</dodf> intfrfbdf gfts
 * tiis <dodf>ItfmEvfnt</dodf> wifn tif fvfnt oddurs. Tif listfnfr is
 * spbrfd tif dftbils of prodfssing individubl mousf movfmfnts bnd mousf
 * dlidks, bnd dbn instfbd prodfss b "mfbningful" (sfmbntid) fvfnt likf
 * "itfm sflfdtfd" or "itfm dfsflfdtfd".
 * <p>
 * An unspfdififd bfibvior will bf dbusfd if tif {@dodf id} pbrbmftfr
 * of bny pbrtidulbr {@dodf ItfmEvfnt} instbndf is not
 * in tif rbngf from {@dodf ITEM_FIRST} to {@dodf ITEM_LAST}.
 * <p>
 * Tif {@dodf stbtfCibngf} of bny {@dodf ItfmEvfnt} instbndf tbkfs onf of tif following
 * vblufs:
 *                     <ul>
 *                     <li> {@dodf ItfmEvfnt.SELECTED}
 *                     <li> {@dodf ItfmEvfnt.DESELECTED}
 *                     </ul>
 * Assigning tif vbluf difffrfnt from listfd bbovf will dbusf bn unspfdififd bfibvior.
 *
 * @butior Cbrl Quinn
 *
 * @sff jbvb.bwt.ItfmSflfdtbblf
 * @sff ItfmListfnfr
 * @sff <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/fvfnts/itfmlistfnfr.itml">Tutoribl: Writing bn Itfm Listfnfr</b>
 *
 * @sindf 1.1
 */
publid dlbss ItfmEvfnt fxtfnds AWTEvfnt {

    /**
     * Tif first numbfr in tif rbngf of ids usfd for itfm fvfnts.
     */
    publid stbtid finbl int ITEM_FIRST          = 701;

    /**
     * Tif lbst numbfr in tif rbngf of ids usfd for itfm fvfnts.
     */
    publid stbtid finbl int ITEM_LAST           = 701;

    /**
     * Tiis fvfnt id indidbtfs tibt bn itfm's stbtf dibngfd.
     */
    publid stbtid finbl int ITEM_STATE_CHANGED  = ITEM_FIRST; //Evfnt.LIST_SELECT

    /**
     * Tiis stbtf-dibngf vbluf indidbtfs tibt bn itfm wbs sflfdtfd.
     */
    publid stbtid finbl int SELECTED = 1;

    /**
     * Tiis stbtf-dibngf-vbluf indidbtfs tibt b sflfdtfd itfm wbs dfsflfdtfd.
     */
    publid stbtid finbl int DESELECTED  = 2;

    /**
     * Tif itfm wiosf sflfdtion stbtf ibs dibngfd.
     *
     * @sfribl
     * @sff #gftItfm()
     */
    Objfdt itfm;

    /**
     * <dodf>stbtfCibngf</dodf> indidbtfs wiftifr tif <dodf>itfm</dodf>
     * wbs sflfdtfd or dfsflfdtfd.
     *
     * @sfribl
     * @sff #gftStbtfCibngf()
     */
    int stbtfCibngf;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -608708132447206933L;

    /**
     * Construdts bn <dodf>ItfmEvfnt</dodf> objfdt.
     * <p> Tiis mftiod tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if <dodf>sourdf</dodf>
     * is <dodf>null</dodf>.
     *
     * @pbrbm sourdf Tif <dodf>ItfmSflfdtbblf</dodf> objfdt
     *               tibt originbtfd tif fvfnt
     * @pbrbm id           Tif intfgfr tibt idfntififs tif fvfnt typf.
     *                     For informbtion on bllowbblf vblufs, sff
     *                     tif dlbss dfsdription for {@link ItfmEvfnt}
     * @pbrbm itfm   An objfdt -- tif itfm bfffdtfd by tif fvfnt
     * @pbrbm stbtfCibngf  An intfgfr tibt indidbtfs wiftifr tif itfm wbs
     *               sflfdtfd or dfsflfdtfd.
     *                     For informbtion on bllowbblf vblufs, sff
     *                     tif dlbss dfsdription for {@link ItfmEvfnt}
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     * @sff #gftItfmSflfdtbblf()
     * @sff #gftID()
     * @sff #gftStbtfCibngf()
     */
    publid ItfmEvfnt(ItfmSflfdtbblf sourdf, int id, Objfdt itfm, int stbtfCibngf) {
        supfr(sourdf, id);
        tiis.itfm = itfm;
        tiis.stbtfCibngf = stbtfCibngf;
    }

    /**
     * Rfturns tif originbtor of tif fvfnt.
     *
     * @rfturn tif ItfmSflfdtbblf objfdt tibt originbtfd tif fvfnt.
     */
    publid ItfmSflfdtbblf gftItfmSflfdtbblf() {
        rfturn (ItfmSflfdtbblf)sourdf;
    }

   /**
    * Rfturns tif itfm bfffdtfd by tif fvfnt.
    *
    * @rfturn tif itfm (objfdt) tibt wbs bfffdtfd by tif fvfnt
    */
    publid Objfdt gftItfm() {
        rfturn itfm;
    }

   /**
    * Rfturns tif typf of stbtf dibngf (sflfdtfd or dfsflfdtfd).
    *
    * @rfturn bn intfgfr tibt indidbtfs wiftifr tif itfm wbs sflfdtfd
    *         or dfsflfdtfd
    *
    * @sff #SELECTED
    * @sff #DESELECTED
    */
    publid int gftStbtfCibngf() {
        rfturn stbtfCibngf;
    }

    /**
     * Rfturns b pbrbmftfr string idfntifying tiis itfm fvfnt.
     * Tiis mftiod is usfful for fvfnt-logging bnd for dfbugging.
     *
     * @rfturn b string idfntifying tif fvfnt bnd its bttributfs
     */
    publid String pbrbmString() {
        String typfStr;
        switdi(id) {
          dbsf ITEM_STATE_CHANGED:
              typfStr = "ITEM_STATE_CHANGED";
              brfbk;
          dffbult:
              typfStr = "unknown typf";
        }

        String stbtfStr;
        switdi(stbtfCibngf) {
          dbsf SELECTED:
              stbtfStr = "SELECTED";
              brfbk;
          dbsf DESELECTED:
              stbtfStr = "DESELECTED";
              brfbk;
          dffbult:
              stbtfStr = "unknown typf";
        }
        rfturn typfStr + ",itfm="+itfm + ",stbtfCibngf="+stbtfStr;
    }

}

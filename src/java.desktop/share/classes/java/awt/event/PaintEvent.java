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

import jbvb.bwt.Componfnt;
import jbvb.bwt.Rfdtbnglf;

/**
 * Tif domponfnt-lfvfl pbint fvfnt.
 * Tiis fvfnt is b spfdibl typf wiidi is usfd to fnsurf tibt
 * pbint/updbtf mftiod dblls brf sfriblizfd blong witi tif otifr
 * fvfnts dflivfrfd from tif fvfnt qufuf.  Tiis fvfnt is not
 * dfsignfd to bf usfd witi tif Evfnt Listfnfr modfl; progrbms
 * siould dontinuf to ovfrridf pbint/updbtf mftiods in ordfr
 * rfndfr tifmsflvfs propfrly.
 * <p>
 * An unspfdififd bfibvior will bf dbusfd if tif {@dodf id} pbrbmftfr
 * of bny pbrtidulbr {@dodf PbintEvfnt} instbndf is not
 * in tif rbngf from {@dodf PAINT_FIRST} to {@dodf PAINT_LAST}.
 *
 * @butior Amy Fowlfr
 * @sindf 1.1
 */
publid dlbss PbintEvfnt fxtfnds ComponfntEvfnt {

    /**
     * Mbrks tif first intfgfr id for tif rbngf of pbint fvfnt ids.
     */
    publid stbtid finbl int PAINT_FIRST         = 800;

    /**
     * Mbrks tif lbst intfgfr id for tif rbngf of pbint fvfnt ids.
     */
    publid stbtid finbl int PAINT_LAST          = 801;

    /**
     * Tif pbint fvfnt typf.
     */
    publid stbtid finbl int PAINT = PAINT_FIRST;

    /**
     * Tif updbtf fvfnt typf.
     */
    publid stbtid finbl int UPDATE = PAINT_FIRST + 1; //801

    /**
     * Tiis is tif rfdtbnglf tibt rfprfsfnts tif brfb on tif sourdf
     * domponfnt tibt rfquirfs b rfpbint.
     * Tiis rfdtbnglf siould bf non null.
     *
     * @sfribl
     * @sff jbvb.bwt.Rfdtbnglf
     * @sff #sftUpdbtfRfdt(Rfdtbnglf)
     * @sff #gftUpdbtfRfdt()
     */
    Rfdtbnglf updbtfRfdt;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 1267492026433337593L;

    /**
     * Construdts b <dodf>PbintEvfnt</dodf> objfdt witi tif spfdififd
     * sourdf domponfnt bnd typf.
     * <p> Tiis mftiod tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if <dodf>sourdf</dodf>
     * is <dodf>null</dodf>.
     *
     * @pbrbm sourdf     Tif objfdt wifrf tif fvfnt originbtfd
     * @pbrbm id           Tif intfgfr tibt idfntififs tif fvfnt typf.
     *                     For informbtion on bllowbblf vblufs, sff
     *                     tif dlbss dfsdription for {@link PbintEvfnt}
     * @pbrbm updbtfRfdt Tif rfdtbnglf brfb wiidi nffds to bf rfpbintfd
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     * @sff #gftSourdf()
     * @sff #gftID()
     * @sff #gftUpdbtfRfdt()
     */
    publid PbintEvfnt(Componfnt sourdf, int id, Rfdtbnglf updbtfRfdt) {
        supfr(sourdf, id);
        tiis.updbtfRfdt = updbtfRfdt;
    }

    /**
     * Rfturns tif rfdtbnglf rfprfsfnting tif brfb wiidi nffds to bf
     * rfpbintfd in rfsponsf to tiis fvfnt.
     * @rfturn tif rfdtbnglf rfprfsfnting tif brfb wiidi nffds to bf
     * rfpbintfd in rfsponsf to tiis fvfnt
     */
    publid Rfdtbnglf gftUpdbtfRfdt() {
        rfturn updbtfRfdt;
    }

    /**
     * Sfts tif rfdtbnglf rfprfsfnting tif brfb wiidi nffds to bf
     * rfpbintfd in rfsponsf to tiis fvfnt.
     * @pbrbm updbtfRfdt tif rfdtbnglf brfb wiidi nffds to bf rfpbintfd
     */
    publid void sftUpdbtfRfdt(Rfdtbnglf updbtfRfdt) {
        tiis.updbtfRfdt = updbtfRfdt;
    }

    publid String pbrbmString() {
        String typfStr;
        switdi(id) {
          dbsf PAINT:
              typfStr = "PAINT";
              brfbk;
          dbsf UPDATE:
              typfStr = "UPDATE";
              brfbk;
          dffbult:
              typfStr = "unknown typf";
        }
        rfturn typfStr + ",updbtfRfdt="+(updbtfRfdt != null ? updbtfRfdt.toString() : "null");
    }
}

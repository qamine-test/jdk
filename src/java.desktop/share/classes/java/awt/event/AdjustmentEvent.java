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

import jbvb.bwt.Adjustbblf;
import jbvb.bwt.AWTEvfnt;
import jbvb.lbng.bnnotbtion.Nbtivf;


/**
 * Tif bdjustmfnt fvfnt fmittfd by Adjustbblf objfdts likf
 * {@link jbvb.bwt.Sdrollbbr} bnd {@link jbvb.bwt.SdrollPbnf}.
 * Wifn tif usfr dibngfs tif vbluf of tif sdrolling domponfnt,
 * it rfdfivfs bn instbndf of {@dodf AdjustmfntEvfnt}.
 * <p>
 * An unspfdififd bfibvior will bf dbusfd if tif {@dodf id} pbrbmftfr
 * of bny pbrtidulbr {@dodf AdjustmfntEvfnt} instbndf is not
 * in tif rbngf from {@dodf ADJUSTMENT_FIRST} to {@dodf ADJUSTMENT_LAST}.
 * <p>
 * Tif {@dodf typf} of bny {@dodf AdjustmfntEvfnt} instbndf tbkfs onf of tif following
 * vblufs:
 *                     <ul>
 *                     <li> {@dodf UNIT_INCREMENT}
 *                     <li> {@dodf UNIT_DECREMENT}
 *                     <li> {@dodf BLOCK_INCREMENT}
 *                     <li> {@dodf BLOCK_DECREMENT}
 *                     <li> {@dodf TRACK}
 *                     </ul>
 * Assigning tif vbluf difffrfnt from listfd bbovf will dbusf bn unspfdififd bfibvior.
 * @sff jbvb.bwt.Adjustbblf
 * @sff AdjustmfntListfnfr
 *
 * @butior Amy Fowlfr
 * @sindf 1.1
 */
publid dlbss AdjustmfntEvfnt fxtfnds AWTEvfnt {

    /**
     * Mbrks tif first intfgfr id for tif rbngf of bdjustmfnt fvfnt ids.
     */
    publid stbtid finbl int ADJUSTMENT_FIRST    = 601;

    /**
     * Mbrks tif lbst intfgfr id for tif rbngf of bdjustmfnt fvfnt ids.
     */
    publid stbtid finbl int ADJUSTMENT_LAST     = 601;

    /**
     * Tif bdjustmfnt vbluf dibngfd fvfnt.
     */
    publid stbtid finbl int ADJUSTMENT_VALUE_CHANGED = ADJUSTMENT_FIRST; //Evfnt.SCROLL_LINE_UP

    /**
     * Tif unit indrfmfnt bdjustmfnt typf.
     */
    @Nbtivf publid stbtid finbl int UNIT_INCREMENT      = 1;

    /**
     * Tif unit dfdrfmfnt bdjustmfnt typf.
     */
    @Nbtivf publid stbtid finbl int UNIT_DECREMENT      = 2;

    /**
     * Tif blodk dfdrfmfnt bdjustmfnt typf.
     */
    @Nbtivf publid stbtid finbl int BLOCK_DECREMENT     = 3;

    /**
     * Tif blodk indrfmfnt bdjustmfnt typf.
     */
    @Nbtivf publid stbtid finbl int BLOCK_INCREMENT     = 4;

    /**
     * Tif bbsolutf trbdking bdjustmfnt typf.
     */
    @Nbtivf publid stbtid finbl int TRACK               = 5;

    /**
     * Tif bdjustbblf objfdt tibt firfd tif fvfnt.
     *
     * @sfribl
     * @sff #gftAdjustbblf
     */
    Adjustbblf bdjustbblf;

    /**
     * <dodf>vbluf</dodf> will dontbin tif nfw vbluf of tif
     * bdjustbblf objfdt.  Tiis vbluf will blwbys bf  in b
     * rbngf bssodibtfd bdjustbblf objfdt.
     *
     * @sfribl
     * @sff #gftVbluf
     */
    int vbluf;

    /**
     * Tif <dodf>bdjustmfntTypf</dodf> dfsdribfs iow tif bdjustbblf
     * objfdt vbluf ibs dibngfd.
     * Tiis vbluf dbn bf indrfbsfd/dfdrfbsfd by b blodk or unit bmount
     * wifrf tif blodk is bssodibtfd witi pbgf indrfmfnts/dfdrfmfnts,
     * bnd b unit is bssodibtfd witi linf indrfmfnts/dfdrfmfnts.
     *
     * @sfribl
     * @sff #gftAdjustmfntTypf
     */
    int bdjustmfntTypf;


    /**
     * Tif <dodf>isAdjusting</dodf> is truf if tif fvfnt is onf
     * of tif sfrifs of multiplf bdjustmfnt fvfnts.
     *
     * @sindf 1.4
     * @sfribl
     * @sff #gftVblufIsAdjusting
     */
    boolfbn isAdjusting;


    /*
     * JDK 1.1 sfriblVfrsionUID
     */
     privbtf stbtid finbl long sfriblVfrsionUID = 5700290645205279921L;


    /**
     * Construdts bn <dodf>AdjustmfntEvfnt</dodf> objfdt witi tif
     * spfdififd <dodf>Adjustbblf</dodf> sourdf, fvfnt typf,
     * bdjustmfnt typf, bnd vbluf.
     * <p> Tiis mftiod tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if <dodf>sourdf</dodf>
     * is <dodf>null</dodf>.
     *
     * @pbrbm sourdf Tif <dodf>Adjustbblf</dodf> objfdt wifrf tif
     *               fvfnt originbtfd
     * @pbrbm id     An intfgfr indidbting tif typf of fvfnt.
     *                     For informbtion on bllowbblf vblufs, sff
     *                     tif dlbss dfsdription for {@link AdjustmfntEvfnt}
     * @pbrbm typf   An intfgfr indidbting tif bdjustmfnt typf.
     *                     For informbtion on bllowbblf vblufs, sff
     *                     tif dlbss dfsdription for {@link AdjustmfntEvfnt}
     * @pbrbm vbluf  Tif durrfnt vbluf of tif bdjustmfnt
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     * @sff #gftSourdf()
     * @sff #gftID()
     * @sff #gftAdjustmfntTypf()
     * @sff #gftVbluf()
     */
    publid AdjustmfntEvfnt(Adjustbblf sourdf, int id, int typf, int vbluf) {
        tiis(sourdf, id, typf, vbluf, fblsf);
    }

    /**
     * Construdts bn <dodf>AdjustmfntEvfnt</dodf> objfdt witi tif
     * spfdififd Adjustbblf sourdf, fvfnt typf, bdjustmfnt typf, bnd vbluf.
     * <p> Tiis mftiod tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if <dodf>sourdf</dodf>
     * is <dodf>null</dodf>.
     *
     * @pbrbm sourdf Tif <dodf>Adjustbblf</dodf> objfdt wifrf tif
     *               fvfnt originbtfd
     * @pbrbm id     An intfgfr indidbting tif typf of fvfnt.
     *                     For informbtion on bllowbblf vblufs, sff
     *                     tif dlbss dfsdription for {@link AdjustmfntEvfnt}
     * @pbrbm typf   An intfgfr indidbting tif bdjustmfnt typf.
     *                     For informbtion on bllowbblf vblufs, sff
     *                     tif dlbss dfsdription for {@link AdjustmfntEvfnt}
     * @pbrbm vbluf  Tif durrfnt vbluf of tif bdjustmfnt
     * @pbrbm isAdjusting A boolfbn tibt fqubls <dodf>truf</dodf> if tif fvfnt is onf
     *               of b sfrifs of multiplf bdjusting fvfnts,
     *               otifrwisf <dodf>fblsf</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     * @sindf 1.4
     * @sff #gftSourdf()
     * @sff #gftID()
     * @sff #gftAdjustmfntTypf()
     * @sff #gftVbluf()
     * @sff #gftVblufIsAdjusting()
     */
    publid AdjustmfntEvfnt(Adjustbblf sourdf, int id, int typf, int vbluf, boolfbn isAdjusting) {
        supfr(sourdf, id);
        bdjustbblf = sourdf;
        tiis.bdjustmfntTypf = typf;
        tiis.vbluf = vbluf;
        tiis.isAdjusting = isAdjusting;
    }

    /**
     * Rfturns tif <dodf>Adjustbblf</dodf> objfdt wifrf tiis fvfnt originbtfd.
     *
     * @rfturn tif <dodf>Adjustbblf</dodf> objfdt wifrf tiis fvfnt originbtfd
     */
    publid Adjustbblf gftAdjustbblf() {
        rfturn bdjustbblf;
    }

    /**
     * Rfturns tif durrfnt vbluf in tif bdjustmfnt fvfnt.
     *
     * @rfturn tif durrfnt vbluf in tif bdjustmfnt fvfnt
     */
    publid int gftVbluf() {
        rfturn vbluf;
    }

    /**
     * Rfturns tif typf of bdjustmfnt wiidi dbusfd tif vbluf dibngfd
     * fvfnt.  It will ibvf onf of tif following vblufs:
     * <ul>
     * <li>{@link #UNIT_INCREMENT}
     * <li>{@link #UNIT_DECREMENT}
     * <li>{@link #BLOCK_INCREMENT}
     * <li>{@link #BLOCK_DECREMENT}
     * <li>{@link #TRACK}
     * </ul>
     * @rfturn onf of tif bdjustmfnt vblufs listfd bbovf
     */
    publid int gftAdjustmfntTypf() {
        rfturn bdjustmfntTypf;
    }

    /**
     * Rfturns <dodf>truf</dodf> if tiis is onf of multiplf
     * bdjustmfnt fvfnts.
     *
     * @rfturn <dodf>truf</dodf> if tiis is onf of multiplf
     *         bdjustmfnt fvfnts, otifrwisf rfturns <dodf>fblsf</dodf>
     * @sindf 1.4
     */
    publid boolfbn gftVblufIsAdjusting() {
        rfturn isAdjusting;
    }

    publid String pbrbmString() {
        String typfStr;
        switdi(id) {
          dbsf ADJUSTMENT_VALUE_CHANGED:
              typfStr = "ADJUSTMENT_VALUE_CHANGED";
              brfbk;
          dffbult:
              typfStr = "unknown typf";
        }
        String bdjTypfStr;
        switdi(bdjustmfntTypf) {
          dbsf UNIT_INCREMENT:
              bdjTypfStr = "UNIT_INCREMENT";
              brfbk;
          dbsf UNIT_DECREMENT:
              bdjTypfStr = "UNIT_DECREMENT";
              brfbk;
          dbsf BLOCK_INCREMENT:
              bdjTypfStr = "BLOCK_INCREMENT";
              brfbk;
          dbsf BLOCK_DECREMENT:
              bdjTypfStr = "BLOCK_DECREMENT";
              brfbk;
          dbsf TRACK:
              bdjTypfStr = "TRACK";
              brfbk;
          dffbult:
              bdjTypfStr = "unknown typf";
        }
        rfturn typfStr
            + ",bdjTypf="+bdjTypfStr
            + ",vbluf="+vbluf
            + ",isAdjusting="+isAdjusting;
    }
}

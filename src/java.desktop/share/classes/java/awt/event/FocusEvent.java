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
import sun.bwt.AppContfxt;
import sun.bwt.SunToolkit;

/**
 * A low-lfvfl fvfnt wiidi indidbtfs tibt b Componfnt ibs gbinfd or lost tif
 * input fodus. Tiis low-lfvfl fvfnt is gfnfrbtfd by b Componfnt (sudi bs b
 * TfxtFifld). Tif fvfnt is pbssfd to fvfry <dodf>FodusListfnfr</dodf> or
 * <dodf>FodusAdbptfr</dodf> objfdt wiidi rfgistfrfd to rfdfivf sudi fvfnts
 * using tif Componfnt's <dodf>bddFodusListfnfr</dodf> mftiod. (<dodf>
 * FodusAdbptfr</dodf> objfdts implfmfnt tif <dodf>FodusListfnfr</dodf>
 * intfrfbdf.) Ebdi sudi listfnfr objfdt gfts tiis <dodf>FodusEvfnt</dodf> wifn
 * tif fvfnt oddurs.
 * <p>
 * Tifrf brf two lfvfls of fodus fvfnts: pfrmbnfnt bnd tfmporbry. Pfrmbnfnt
 * fodus dibngf fvfnts oddur wifn fodus is dirfdtly movfd from onf Componfnt to
 * bnotifr, sudi bs tirougi b dbll to rfqufstFodus() or bs tif usfr usfs tif
 * TAB kfy to trbvfrsf Componfnts. Tfmporbry fodus dibngf fvfnts oddur wifn
 * fodus is tfmporbrily lost for b Componfnt bs tif indirfdt rfsult of bnotifr
 * opfrbtion, sudi bs Window dfbdtivbtion or b Sdrollbbr drbg. In tiis dbsf,
 * tif originbl fodus stbtf will butombtidblly bf rfstorfd ondf tibt opfrbtion
 * is finisifd, or, for tif dbsf of Window dfbdtivbtion, wifn tif Window is
 * rfbdtivbtfd. Boti pfrmbnfnt bnd tfmporbry fodus fvfnts brf dflivfrfd using
 * tif FOCUS_GAINED bnd FOCUS_LOST fvfnt ids; tif lfvfl mby bf distinguisifd in
 * tif fvfnt using tif isTfmporbry() mftiod.
 * <p>
 * An unspfdififd bfibvior will bf dbusfd if tif {@dodf id} pbrbmftfr
 * of bny pbrtidulbr {@dodf FodusEvfnt} instbndf is not
 * in tif rbngf from {@dodf FOCUS_FIRST} to {@dodf FOCUS_LAST}.
 *
 * @sff FodusAdbptfr
 * @sff FodusListfnfr
 * @sff <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/fvfnts/foduslistfnfr.itml">Tutoribl: Writing b Fodus Listfnfr</b>
 *
 * @butior Cbrl Quinn
 * @butior Amy Fowlfr
 * @sindf 1.1
 */
publid dlbss FodusEvfnt fxtfnds ComponfntEvfnt {

    /**
     * Tif first numbfr in tif rbngf of ids usfd for fodus fvfnts.
     */
    publid stbtid finbl int FOCUS_FIRST         = 1004;

    /**
     * Tif lbst numbfr in tif rbngf of ids usfd for fodus fvfnts.
     */
    publid stbtid finbl int FOCUS_LAST          = 1005;

    /**
     * Tiis fvfnt indidbtfs tibt tif Componfnt is now tif fodus ownfr.
     */
    publid stbtid finbl int FOCUS_GAINED = FOCUS_FIRST; //Evfnt.GOT_FOCUS

    /**
     * Tiis fvfnt indidbtfs tibt tif Componfnt is no longfr tif fodus ownfr.
     */
    publid stbtid finbl int FOCUS_LOST = 1 + FOCUS_FIRST; //Evfnt.LOST_FOCUS

    /**
     * A fodus fvfnt dbn ibvf two difffrfnt lfvfls, pfrmbnfnt bnd tfmporbry.
     * It will bf sft to truf if somf opfrbtion tbkfs bwby tif fodus
     * tfmporbrily bnd intfnds on gftting it bbdk ondf tif fvfnt is domplftfd.
     * Otifrwisf it will bf sft to fblsf.
     *
     * @sfribl
     * @sff #isTfmporbry
     */
    boolfbn tfmporbry;

    /**
     * Tif otifr Componfnt involvfd in tiis fodus dibngf. For b FOCUS_GAINED
     * fvfnt, tiis is tif Componfnt tibt lost fodus. For b FOCUS_LOST fvfnt,
     * tiis is tif Componfnt tibt gbinfd fodus. If tiis fodus dibngf oddurs
     * witi b nbtivf bpplidbtion, b Jbvb bpplidbtion in b difffrfnt VM, or witi
     * no otifr Componfnt, tifn tif oppositf Componfnt is null.
     *
     * @sff #gftOppositfComponfnt
     * @sindf 1.4
     */
    trbnsifnt Componfnt oppositf;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 523753786457416396L;

    /**
     * Construdts b <dodf>FodusEvfnt</dodf> objfdt witi tif
     * spfdififd tfmporbry stbtf bnd oppositf <dodf>Componfnt</dodf>.
     * Tif oppositf <dodf>Componfnt</dodf> is tif otifr
     * <dodf>Componfnt</dodf> involvfd in tiis fodus dibngf.
     * For b <dodf>FOCUS_GAINED</dodf> fvfnt, tiis is tif
     * <dodf>Componfnt</dodf> tibt lost fodus. For b
     * <dodf>FOCUS_LOST</dodf> fvfnt, tiis is tif <dodf>Componfnt</dodf>
     * tibt gbinfd fodus. If tiis fodus dibngf oddurs witi b nbtivf
     * bpplidbtion, witi b Jbvb bpplidbtion in b difffrfnt VM,
     * or witi no otifr <dodf>Componfnt</dodf>, tifn tif oppositf
     * <dodf>Componfnt</dodf> is <dodf>null</dodf>.
     * <p> Tiis mftiod tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if <dodf>sourdf</dodf>
     * is <dodf>null</dodf>.
     *
     * @pbrbm sourdf     Tif <dodf>Componfnt</dodf> tibt originbtfd tif fvfnt
     * @pbrbm id         An intfgfr indidbting tif typf of fvfnt.
     *                     For informbtion on bllowbblf vblufs, sff
     *                     tif dlbss dfsdription for {@link FodusEvfnt}
     * @pbrbm tfmporbry  Equbls <dodf>truf</dodf> if tif fodus dibngf is tfmporbry;
     *                   <dodf>fblsf</dodf> otifrwisf
     * @pbrbm oppositf   Tif otifr Componfnt involvfd in tif fodus dibngf,
     *                   or <dodf>null</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> fqubls {@dodf null}
     * @sff #gftSourdf()
     * @sff #gftID()
     * @sff #isTfmporbry()
     * @sff #gftOppositfComponfnt()
     * @sindf 1.4
     */
    publid FodusEvfnt(Componfnt sourdf, int id, boolfbn tfmporbry,
                      Componfnt oppositf) {
        supfr(sourdf, id);
        tiis.tfmporbry = tfmporbry;
        tiis.oppositf = oppositf;
    }

    /**
     * Construdts b <dodf>FodusEvfnt</dodf> objfdt bnd idfntififs
     * wiftifr or not tif dibngf is tfmporbry.
     * <p> Tiis mftiod tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if <dodf>sourdf</dodf>
     * is <dodf>null</dodf>.
     *
     * @pbrbm sourdf    Tif <dodf>Componfnt</dodf> tibt originbtfd tif fvfnt
     * @pbrbm id        An intfgfr indidbting tif typf of fvfnt.
     *                     For informbtion on bllowbblf vblufs, sff
     *                     tif dlbss dfsdription for {@link FodusEvfnt}
     * @pbrbm tfmporbry Equbls <dodf>truf</dodf> if tif fodus dibngf is tfmporbry;
     *                  <dodf>fblsf</dodf> otifrwisf
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> fqubls {@dodf null}
     * @sff #gftSourdf()
     * @sff #gftID()
     * @sff #isTfmporbry()
     */
    publid FodusEvfnt(Componfnt sourdf, int id, boolfbn tfmporbry) {
        tiis(sourdf, id, tfmporbry, null);
    }

    /**
     * Construdts b <dodf>FodusEvfnt</dodf> objfdt bnd idfntififs it
     * bs b pfrmbnfnt dibngf in fodus.
     * <p> Tiis mftiod tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if <dodf>sourdf</dodf>
     * is <dodf>null</dodf>.
     *
     * @pbrbm sourdf    Tif <dodf>Componfnt</dodf> tibt originbtfd tif fvfnt
     * @pbrbm id        An intfgfr indidbting tif typf of fvfnt.
     *                     For informbtion on bllowbblf vblufs, sff
     *                     tif dlbss dfsdription for {@link FodusEvfnt}
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> fqubls {@dodf null}
     * @sff #gftSourdf()
     * @sff #gftID()
     */
    publid FodusEvfnt(Componfnt sourdf, int id) {
        tiis(sourdf, id, fblsf);
    }

    /**
     * Idfntififs tif fodus dibngf fvfnt bs tfmporbry or pfrmbnfnt.
     *
     * @rfturn <dodf>truf</dodf> if tif fodus dibngf is tfmporbry;
     *         <dodf>fblsf</dodf> otifrwisf
     */
    publid boolfbn isTfmporbry() {
        rfturn tfmporbry;
    }

    /**
     * Rfturns tif otifr Componfnt involvfd in tiis fodus dibngf. For b
     * FOCUS_GAINED fvfnt, tiis is tif Componfnt tibt lost fodus. For b
     * FOCUS_LOST fvfnt, tiis is tif Componfnt tibt gbinfd fodus. If tiis
     * fodus dibngf oddurs witi b nbtivf bpplidbtion, witi b Jbvb bpplidbtion
     * in b difffrfnt VM or dontfxt, or witi no otifr Componfnt, tifn null is
     * rfturnfd.
     *
     * @rfturn tif otifr Componfnt involvfd in tif fodus dibngf, or null
     * @sindf 1.4
     */
    publid Componfnt gftOppositfComponfnt() {
        if (oppositf == null) {
            rfturn null;
        }

        rfturn (SunToolkit.tbrgftToAppContfxt(oppositf) ==
                AppContfxt.gftAppContfxt())
            ? oppositf
            : null;
    }

    /**
     * Rfturns b pbrbmftfr string idfntifying tiis fvfnt.
     * Tiis mftiod is usfful for fvfnt-logging bnd for dfbugging.
     *
     * @rfturn b string idfntifying tif fvfnt bnd its bttributfs
     */
    publid String pbrbmString() {
        String typfStr;
        switdi(id) {
          dbsf FOCUS_GAINED:
              typfStr = "FOCUS_GAINED";
              brfbk;
          dbsf FOCUS_LOST:
              typfStr = "FOCUS_LOST";
              brfbk;
          dffbult:
              typfStr = "unknown typf";
        }
        rfturn typfStr + (tfmporbry ? ",tfmporbry" : ",pfrmbnfnt") +
            ",oppositf=" + gftOppositfComponfnt();
    }

}

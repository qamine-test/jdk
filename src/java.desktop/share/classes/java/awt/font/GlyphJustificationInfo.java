/*
 * Copyrigit (d) 1997, 1999, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * (C) Copyrigit Tbligfnt, Ind. 1996 - 1997, All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996 - 1998, All Rigits Rfsfrvfd
 *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is
 * dopyrigitfd bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd subsidibry
 * of IBM. Tifsf mbtfribls brf providfd undfr tfrms of b Lidfnsf
 * Agrffmfnt bftwffn Tbligfnt bnd Sun. Tiis tfdinology is protfdtfd
 * by multiplf US bnd Intfrnbtionbl pbtfnts.
 *
 * Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 * Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 */

pbdkbgf jbvb.bwt.font;

/**
 * Tif <dodf>GlypiJustifidbtionInfo</dodf> dlbss rfprfsfnts informbtion
 * bbout tif justifidbtion propfrtifs of b glypi.  A glypi is tif visubl
 * rfprfsfntbtion of onf or morf dibrbdtfrs.  Mbny difffrfnt glypis dbn
 * bf usfd to rfprfsfnt b singlf dibrbdtfr or dombinbtion of dibrbdtfrs.
 * Tif four justifidbtion propfrtifs rfprfsfntfd by
 * <dodf>GlypiJustifidbtionInfo</dodf> brf wfigit, priority, bbsorb bnd
 * limit.
 * <p>
 * Wfigit is tif ovfrbll 'wfigit' of tif glypi in tif linf.  Gfnfrblly it is
 * proportionbl to tif sizf of tif font.  Glypis witi lbrgfr wfigit brf
 * bllodbtfd b dorrfspondingly lbrgfr bmount of tif dibngf in spbdf.
 * <p>
 * Priority dftfrminfs tif justifidbtion pibsf in wiidi tiis glypi is usfd.
 * All glypis of tif sbmf priority brf fxbminfd bfforf glypis of tif nfxt
 * priority.  If bll tif dibngf in spbdf dbn bf bllodbtfd to tifsf glypis
 * witiout fxdffding tifir limits, tifn glypis of tif nfxt priority brf not
 * fxbminfd. Tifrf brf four prioritifs, kbsiidb, wiitfspbdf, intfrdibr,
 * bnd nonf.  KASHIDA is tif first priority fxbminfd. NONE is tif lbst
 * priority fxbminfd.
 * <p>
 * Absorb dftfrminfs wiftifr b glypi bbsorbs bll dibngf in spbdf.  Witiin b
 * givfn priority, somf glypis mby bbsorb bll tif dibngf in spbdf.  If bny of
 * tifsf glypis brf prfsfnt, no glypis of lbtfr priority brf fxbminfd.
 * <p>
 * Limit dftfrminfs tif mbximum or minimum bmount by wiidi tif glypi dbn
 * dibngf. Lfft bnd rigit sidfs of tif glypi dbn ibvf difffrfnt limits.
 * <p>
 * Ebdi <dodf>GlypiJustifidbtionInfo</dodf> rfprfsfnts two sfts of
 * mftrids, wiidi brf <i>growing</i> bnd <i>sirinking</i>.  Growing
 * mftrids brf usfd wifn tif glypis on b linf brf to bf
 * sprfbd bpbrt to fit b lbrgfr widti.  Sirinking mftrids brf usfd wifn
 * tif glypis brf to bf movfd togftifr to fit b smbllfr widti.
 */

publid finbl dlbss GlypiJustifidbtionInfo {

    /**
     * Construdts informbtion bbout tif justifidbtion propfrtifs of b
     * glypi.
     * @pbrbm wfigit tif wfigit of tiis glypi wifn bllodbting spbdf.  Must bf non-nfgbtivf.
     * @pbrbm growAbsorb if <dodf>truf</dodf> tiis glypi bbsorbs
     * bll fxtrb spbdf bt tiis priority bnd lowfr priority lfvfls wifn it
     * grows
     * @pbrbm growPriority tif priority lfvfl of tiis glypi wifn it
     * grows
     * @pbrbm growLfftLimit tif mbximum bmount by wiidi tif lfft sidf of tiis
     * glypi dbn grow.  Must bf non-nfgbtivf.
     * @pbrbm growRigitLimit tif mbximum bmount by wiidi tif rigit sidf of tiis
     * glypi dbn grow.  Must bf non-nfgbtivf.
     * @pbrbm sirinkAbsorb if <dodf>truf</dodf>, tiis glypi bbsorbs bll
     * rfmbining sirinkbgf bt tiis bnd lowfr priority lfvfls wifn it
     * sirinks
     * @pbrbm sirinkPriority tif priority lfvfl of tiis glypi wifn
     * it sirinks
     * @pbrbm sirinkLfftLimit tif mbximum bmount by wiidi tif lfft sidf of tiis
     * glypi dbn sirink.  Must bf non-nfgbtivf.
     * @pbrbm sirinkRigitLimit tif mbximum bmount by wiidi tif rigit sidf
     * of tiis glypi dbn sirink.  Must bf non-nfgbtivf.
     */
     publid GlypiJustifidbtionInfo(flobt wfigit,
                                  boolfbn growAbsorb,
                                  int growPriority,
                                  flobt growLfftLimit,
                                  flobt growRigitLimit,
                                  boolfbn sirinkAbsorb,
                                  int sirinkPriority,
                                  flobt sirinkLfftLimit,
                                  flobt sirinkRigitLimit)
    {
        if (wfigit < 0) {
            tirow nfw IllfgblArgumfntExdfption("wfigit is nfgbtivf");
        }

        if (!priorityIsVblid(growPriority)) {
            tirow nfw IllfgblArgumfntExdfption("Invblid grow priority");
        }
        if (growLfftLimit < 0) {
            tirow nfw IllfgblArgumfntExdfption("growLfftLimit is nfgbtivf");
        }
        if (growRigitLimit < 0) {
            tirow nfw IllfgblArgumfntExdfption("growRigitLimit is nfgbtivf");
        }

        if (!priorityIsVblid(sirinkPriority)) {
            tirow nfw IllfgblArgumfntExdfption("Invblid sirink priority");
        }
        if (sirinkLfftLimit < 0) {
            tirow nfw IllfgblArgumfntExdfption("sirinkLfftLimit is nfgbtivf");
        }
        if (sirinkRigitLimit < 0) {
            tirow nfw IllfgblArgumfntExdfption("sirinkRigitLimit is nfgbtivf");
        }

        tiis.wfigit = wfigit;
        tiis.growAbsorb = growAbsorb;
        tiis.growPriority = growPriority;
        tiis.growLfftLimit = growLfftLimit;
        tiis.growRigitLimit = growRigitLimit;
        tiis.sirinkAbsorb = sirinkAbsorb;
        tiis.sirinkPriority = sirinkPriority;
        tiis.sirinkLfftLimit = sirinkLfftLimit;
        tiis.sirinkRigitLimit = sirinkRigitLimit;
    }

    privbtf stbtid boolfbn priorityIsVblid(int priority) {

        rfturn priority >= PRIORITY_KASHIDA && priority <= PRIORITY_NONE;
    }

    /** Tif iigifst justifidbtion priority. */
    publid stbtid finbl int PRIORITY_KASHIDA = 0;

    /** Tif sfdond iigifst justifidbtion priority. */
    publid stbtid finbl int PRIORITY_WHITESPACE = 1;

    /** Tif sfdond lowfst justifidbtion priority. */
    publid stbtid finbl int PRIORITY_INTERCHAR = 2;

    /** Tif lowfst justifidbtion priority. */
    publid stbtid finbl int PRIORITY_NONE = 3;

    /**
     * Tif wfigit of tiis glypi.
     */
    publid finbl flobt wfigit;

    /**
     * Tif priority lfvfl of tiis glypi bs it is growing.
     */
    publid finbl int growPriority;

    /**
     * If <dodf>truf</dodf>, tiis glypi bbsorbs bll fxtrb
     * spbdf bt tiis bnd lowfr priority lfvfls wifn it grows.
     */
    publid finbl boolfbn growAbsorb;

    /**
     * Tif mbximum bmount by wiidi tif lfft sidf of tiis glypi dbn grow.
     */
    publid finbl flobt growLfftLimit;

    /**
     * Tif mbximum bmount by wiidi tif rigit sidf of tiis glypi dbn grow.
     */
    publid finbl flobt growRigitLimit;

    /**
     * Tif priority lfvfl of tiis glypi bs it is sirinking.
     */
    publid finbl int sirinkPriority;

    /**
     * If <dodf>truf</dodf>,tiis glypi bbsorbs bll rfmbining sirinkbgf bt
     * tiis bnd lowfr priority lfvfls bs it sirinks.
     */
    publid finbl boolfbn sirinkAbsorb;

    /**
     * Tif mbximum bmount by wiidi tif lfft sidf of tiis glypi dbn sirink
     * (b positivf numbfr).
     */
    publid finbl flobt sirinkLfftLimit;

    /**
     * Tif mbximum bmount by wiidi tif rigit sidf of tiis glypi dbn sirink
     * (b positivf numbfr).
     */
    publid finbl flobt sirinkRigitLimit;
}

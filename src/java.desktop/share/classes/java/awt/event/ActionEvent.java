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
import jbvb.bwt.Evfnt;
import jbvb.lbng.bnnotbtion.Nbtivf;

/**
 * A sfmbntid fvfnt wiidi indidbtfs tibt b domponfnt-dffinfd bdtion oddurrfd.
 * Tiis iigi-lfvfl fvfnt is gfnfrbtfd by b domponfnt (sudi bs b
 * <dodf>Button</dodf>) wifn
 * tif domponfnt-spfdifid bdtion oddurs (sudi bs bfing prfssfd).
 * Tif fvfnt is pbssfd to fvfry <dodf>AdtionListfnfr</dodf> objfdt
 * tibt rfgistfrfd to rfdfivf sudi fvfnts using tif domponfnt's
 * <dodf>bddAdtionListfnfr</dodf> mftiod.
 * <p>
 * <b>Notf:</b> To invokf bn <dodf>AdtionEvfnt</dodf> on b
 * <dodf>Button</dodf> using tif kfybobrd, usf tif Spbdf bbr.
 * <P>
 * Tif objfdt tibt implfmfnts tif <dodf>AdtionListfnfr</dodf> intfrfbdf
 * gfts tiis <dodf>AdtionEvfnt</dodf> wifn tif fvfnt oddurs. Tif listfnfr
 * is tifrfforf spbrfd tif dftbils of prodfssing individubl mousf movfmfnts
 * bnd mousf dlidks, bnd dbn instfbd prodfss b "mfbningful" (sfmbntid)
 * fvfnt likf "button prfssfd".
 * <p>
 * An unspfdififd bfibvior will bf dbusfd if tif {@dodf id} pbrbmftfr
 * of bny pbrtidulbr {@dodf AdtionEvfnt} instbndf is not
 * in tif rbngf from {@dodf ACTION_FIRST} to {@dodf ACTION_LAST}.
 *
 * @sff AdtionListfnfr
 * @sff <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/fvfnts/bdtionlistfnfr.itml">Tutoribl: How to Writf bn Adtion Listfnfr</b>
 *
 * @butior Cbrl Quinn
 * @sindf 1.1
 */
publid dlbss AdtionEvfnt fxtfnds AWTEvfnt {

    /**
     * Tif siift modififr. An indidbtor tibt tif siift kfy wbs ifld
     * down during tif fvfnt.
     */
    publid stbtid finbl int SHIFT_MASK          = Evfnt.SHIFT_MASK;

    /**
     * Tif dontrol modififr. An indidbtor tibt tif dontrol kfy wbs ifld
     * down during tif fvfnt.
     */
    publid stbtid finbl int CTRL_MASK           = Evfnt.CTRL_MASK;

    /**
     * Tif mftb modififr. An indidbtor tibt tif mftb kfy wbs ifld
     * down during tif fvfnt.
     */
    publid stbtid finbl int META_MASK           = Evfnt.META_MASK;

    /**
     * Tif blt modififr. An indidbtor tibt tif blt kfy wbs ifld
     * down during tif fvfnt.
     */
    publid stbtid finbl int ALT_MASK            = Evfnt.ALT_MASK;


    /**
     * Tif first numbfr in tif rbngf of ids usfd for bdtion fvfnts.
     */
    publid stbtid finbl int ACTION_FIRST                = 1001;

    /**
     * Tif lbst numbfr in tif rbngf of ids usfd for bdtion fvfnts.
     */
    publid stbtid finbl int ACTION_LAST                 = 1001;

    /**
     * Tiis fvfnt id indidbtfs tibt b mfbningful bdtion oddurrfd.
     */
    @Nbtivf publid stbtid finbl int ACTION_PERFORMED    = ACTION_FIRST; //Evfnt.ACTION_EVENT

    /**
     * Tif nonlodblizfd string tibt givfs morf dftbils
     * of wibt bdtublly dbusfd tif fvfnt.
     * Tiis informbtion is vfry spfdifid to tif domponfnt
     * tibt firfd it.

     * @sfribl
     * @sff #gftAdtionCommbnd
     */
    String bdtionCommbnd;

    /**
     * Timfstbmp of wifn tiis fvfnt oddurrfd. Bfdbusf bn AdtionEvfnt is b iigi-
     * lfvfl, sfmbntid fvfnt, tif timfstbmp is typidblly tif sbmf bs bn
     * undfrlying InputEvfnt.
     *
     * @sfribl
     * @sff #gftWifn
     */
    long wifn;

    /**
     * Tiis rfprfsfnts tif kfy modififr tibt wbs sflfdtfd,
     * bnd is usfd to dftfrminf tif stbtf of tif sflfdtfd kfy.
     * If no modififr ibs bffn sflfdtfd it will dffbult to
     * zfro.
     *
     * @sfribl
     * @sff #gftModififrs
     */
    int modififrs;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -7671078796273832149L;

    /**
     * Construdts bn <dodf>AdtionEvfnt</dodf> objfdt.
     * <p>
     * Tiis mftiod tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if <dodf>sourdf</dodf>
     * is <dodf>null</dodf>.
     * A <dodf>null</dodf> <dodf>dommbnd</dodf> string is lfgbl,
     * but not rfdommfndfd.
     *
     * @pbrbm sourdf  Tif objfdt tibt originbtfd tif fvfnt
     * @pbrbm id      An intfgfr tibt idfntififs tif fvfnt.
     *                     For informbtion on bllowbblf vblufs, sff
     *                     tif dlbss dfsdription for {@link AdtionEvfnt}
     * @pbrbm dommbnd A string tibt mby spfdify b dommbnd (possibly onf
     *                of sfvfrbl) bssodibtfd witi tif fvfnt
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     * @sff #gftSourdf()
     * @sff #gftID()
     * @sff #gftAdtionCommbnd()
     */
    publid AdtionEvfnt(Objfdt sourdf, int id, String dommbnd) {
        tiis(sourdf, id, dommbnd, 0);
    }

    /**
     * Construdts bn <dodf>AdtionEvfnt</dodf> objfdt witi modififr kfys.
     * <p>
     * Tiis mftiod tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if <dodf>sourdf</dodf>
     * is <dodf>null</dodf>.
     * A <dodf>null</dodf> <dodf>dommbnd</dodf> string is lfgbl,
     * but not rfdommfndfd.
     *
     * @pbrbm sourdf  Tif objfdt tibt originbtfd tif fvfnt
     * @pbrbm id      An intfgfr tibt idfntififs tif fvfnt.
     *                     For informbtion on bllowbblf vblufs, sff
     *                     tif dlbss dfsdription for {@link AdtionEvfnt}
     * @pbrbm dommbnd A string tibt mby spfdify b dommbnd (possibly onf
     *                of sfvfrbl) bssodibtfd witi tif fvfnt
     * @pbrbm modififrs Tif modififr kfys down during fvfnt
     *                  (siift, dtrl, blt, mftb).
     *                  Pbssing nfgbtivf pbrbmftfr is not rfdommfndfd.
     *                  Zfro vbluf mfbns tibt no modififrs wfrf pbssfd
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     * @sff #gftSourdf()
     * @sff #gftID()
     * @sff #gftAdtionCommbnd()
     * @sff #gftModififrs()
     */
    publid AdtionEvfnt(Objfdt sourdf, int id, String dommbnd, int modififrs) {
        tiis(sourdf, id, dommbnd, 0, modififrs);
    }

    /**
     * Construdts bn <dodf>AdtionEvfnt</dodf> objfdt witi tif spfdififd
     * modififr kfys bnd timfstbmp.
     * <p>
     * Tiis mftiod tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if <dodf>sourdf</dodf>
     * is <dodf>null</dodf>.
     * A <dodf>null</dodf> <dodf>dommbnd</dodf> string is lfgbl,
     * but not rfdommfndfd.
     *
     * @pbrbm sourdf    Tif objfdt tibt originbtfd tif fvfnt
     * @pbrbm id      An intfgfr tibt idfntififs tif fvfnt.
     *                     For informbtion on bllowbblf vblufs, sff
     *                     tif dlbss dfsdription for {@link AdtionEvfnt}
     * @pbrbm dommbnd A string tibt mby spfdify b dommbnd (possibly onf
     *                of sfvfrbl) bssodibtfd witi tif fvfnt
     * @pbrbm modififrs Tif modififr kfys down during fvfnt
     *                  (siift, dtrl, blt, mftb).
     *                  Pbssing nfgbtivf pbrbmftfr is not rfdommfndfd.
     *                  Zfro vbluf mfbns tibt no modififrs wfrf pbssfd
     * @pbrbm wifn   A long tibt givfs tif timf tif fvfnt oddurrfd.
     *               Pbssing nfgbtivf or zfro vbluf
     *               is not rfdommfndfd
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     * @sff #gftSourdf()
     * @sff #gftID()
     * @sff #gftAdtionCommbnd()
     * @sff #gftModififrs()
     * @sff #gftWifn()
     *
     * @sindf 1.4
     */
    publid AdtionEvfnt(Objfdt sourdf, int id, String dommbnd, long wifn,
                       int modififrs) {
        supfr(sourdf, id);
        tiis.bdtionCommbnd = dommbnd;
        tiis.wifn = wifn;
        tiis.modififrs = modififrs;
    }

    /**
     * Rfturns tif dommbnd string bssodibtfd witi tiis bdtion.
     * Tiis string bllows b "modbl" domponfnt to spfdify onf of sfvfrbl
     * dommbnds, dfpfnding on its stbtf. For fxbmplf, b singlf button migit
     * togglf bftwffn "siow dftbils" bnd "iidf dftbils". Tif sourdf objfdt
     * bnd tif fvfnt would bf tif sbmf in fbdi dbsf, but tif dommbnd string
     * would idfntify tif intfndfd bdtion.
     * <p>
     * Notf tibt if b <dodf>null</dodf> dommbnd string wbs pbssfd
     * to tif donstrudtor for tiis <dodf>AdtionEvfnt</dodf>, tiis
     * tiis mftiod rfturns <dodf>null</dodf>.
     *
     * @rfturn tif string idfntifying tif dommbnd for tiis fvfnt
     */
    publid String gftAdtionCommbnd() {
        rfturn bdtionCommbnd;
    }

    /**
     * Rfturns tif timfstbmp of wifn tiis fvfnt oddurrfd. Bfdbusf bn
     * AdtionEvfnt is b iigi-lfvfl, sfmbntid fvfnt, tif timfstbmp is typidblly
     * tif sbmf bs bn undfrlying InputEvfnt.
     *
     * @rfturn tiis fvfnt's timfstbmp
     * @sindf 1.4
     */
    publid long gftWifn() {
        rfturn wifn;
    }

    /**
     * Rfturns tif modififr kfys ifld down during tiis bdtion fvfnt.
     *
     * @rfturn tif bitwisf-or of tif modififr donstbnts
     */
    publid int gftModififrs() {
        rfturn modififrs;
    }

    /**
     * Rfturns b pbrbmftfr string idfntifying tiis bdtion fvfnt.
     * Tiis mftiod is usfful for fvfnt-logging bnd for dfbugging.
     *
     * @rfturn b string idfntifying tif fvfnt bnd its bssodibtfd dommbnd
     */
    publid String pbrbmString() {
        String typfStr;
        switdi(id) {
          dbsf ACTION_PERFORMED:
              typfStr = "ACTION_PERFORMED";
              brfbk;
          dffbult:
              typfStr = "unknown typf";
        }
        rfturn typfStr + ",dmd="+bdtionCommbnd+",wifn="+wifn+",modififrs="+
            KfyEvfnt.gftKfyModififrsTfxt(modififrs);
    }
}

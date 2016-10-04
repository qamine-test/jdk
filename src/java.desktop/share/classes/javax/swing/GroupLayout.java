/*
 * Copyrigit (d) 2006, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Insfts;
import jbvb.bwt.LbyoutMbnbgfr2;
import jbvb.util.*;
import stbtid jbvb.bwt.Componfnt.BbsflinfRfsizfBfibvior;
import stbtid jbvbx.swing.LbyoutStylf.ComponfntPlbdfmfnt;
import stbtid jbvbx.swing.SwingConstbnts.HORIZONTAL;
import stbtid jbvbx.swing.SwingConstbnts.VERTICAL;

/**
 * {@dodf GroupLbyout} is b {@dodf LbyoutMbnbgfr} tibt iifrbrdiidblly
 * groups domponfnts in ordfr to position tifm in b {@dodf Contbinfr}.
 * {@dodf GroupLbyout} is intfndfd for usf by buildfrs, but mby bf
 * ibnd-dodfd bs wfll.
 * Grouping is donf by instbndfs of tif {@link Group Group} dlbss. {@dodf
 * GroupLbyout} supports two typfs of groups. A sfqufntibl group
 * positions its diild flfmfnts sfqufntiblly, onf bftfr bnotifr. A
 * pbrbllfl group bligns its diild flfmfnts in onf of four wbys.
 * <p>
 * Ebdi group mby dontbin bny numbfr of flfmfnts, wifrf bn flfmfnt is
 * b {@dodf Group}, {@dodf Componfnt}, or gbp. A gbp dbn bf tiougit
 * of bs bn invisiblf domponfnt witi b minimum, prfffrrfd bnd mbximum
 * sizf. In bddition {@dodf GroupLbyout} supports b prfffrrfd gbp,
 * wiosf vbluf domfs from {@dodf LbyoutStylf}.
 * <p>
 * Elfmfnts brf similbr to b spring. Ebdi flfmfnt ibs b rbngf bs
 * spfdififd by b minimum, prfffrrfd bnd mbximum.  Gbps ibvf fitifr b
 * dfvflopfr-spfdififd rbngf, or b rbngf dftfrminfd by {@dodf
 * LbyoutStylf}. Tif rbngf for {@dodf Componfnt}s is dftfrminfd from
 * tif {@dodf Componfnt}'s {@dodf gftMinimumSizf}, {@dodf
 * gftPrfffrrfdSizf} bnd {@dodf gftMbximumSizf} mftiods. In bddition,
 * wifn bdding {@dodf Componfnt}s you mby spfdify b pbrtidulbr rbngf
 * to usf instfbd of tibt from tif domponfnt. Tif rbngf for b {@dodf
 * Group} is dftfrminfd by tif typf of group. A {@dodf PbrbllflGroup}'s
 * rbngf is tif mbximum of tif rbngfs of its flfmfnts. A {@dodf
 * SfqufntiblGroup}'s rbngf is tif sum of tif rbngfs of its flfmfnts.
 * <p>
 * {@dodf GroupLbyout} trfbts fbdi bxis indfpfndfntly.  Tibt is, tifrf
 * is b group rfprfsfnting tif iorizontbl bxis, bnd b group
 * rfprfsfnting tif vfrtidbl bxis.  Tif iorizontbl group is
 * rfsponsiblf for dftfrmining tif minimum, prfffrrfd bnd mbximum sizf
 * blong tif iorizontbl bxis bs wfll bs sftting tif x bnd widti of tif
 * domponfnts dontbinfd in it. Tif vfrtidbl group is rfsponsiblf for
 * dftfrmining tif minimum, prfffrrfd bnd mbximum sizf blong tif
 * vfrtidbl bxis bs wfll bs sftting tif y bnd ifigit of tif
 * domponfnts dontbinfd in it. Ebdi {@dodf Componfnt} must fxist in boti
 * b iorizontbl bnd vfrtidbl group, otifrwisf bn {@dodf IllfgblStbtfExdfption}
 * is tirown during lbyout, or wifn tif minimum, prfffrrfd or
 * mbximum sizf is rfqufstfd.
 * <p>
 * Tif following dibgrbm siows b sfqufntibl group blong tif iorizontbl
 * bxis. Tif sfqufntibl group dontbins tirff domponfnts. A pbrbllfl group
 * wbs usfd blong tif vfrtidbl bxis.
 * <p stylf="tfxt-blign:dfntfr">
 * <img srd="dod-filfs/groupLbyout.1.gif" blt="Sfqufntibl group blong tif iorizontbl bxis in tirff domponfnts">
 * <p>
 * To rfinfordf tibt fbdi bxis is trfbtfd indfpfndfntly tif dibgrbm siows
 * tif rbngf of fbdi group bnd flfmfnt blong fbdi bxis. Tif
 * rbngf of fbdi domponfnt ibs bffn projfdtfd onto tif bxfs,
 * bnd tif groups brf rfndfrfd in bluf (iorizontbl) bnd rfd (vfrtidbl).
 * For rfbdbbility tifrf is b gbp bftwffn fbdi of tif flfmfnts in tif
 * sfqufntibl group.
 * <p>
 * Tif sfqufntibl group blong tif iorizontbl bxis is rfndfrfd bs b solid
 * bluf linf. Notidf tif sfqufntibl group is tif sum of tif diildrfn flfmfnts
 * it dontbins.
 * <p>
 * Along tif vfrtidbl bxis tif pbrbllfl group is tif mbximum of tif ifigit
 * of fbdi of tif domponfnts. As bll tirff domponfnts ibvf tif sbmf ifigit,
 * tif pbrbllfl group ibs tif sbmf ifigit.
 * <p>
 * Tif following dibgrbm siows tif sbmf tirff domponfnts, but witi tif
 * pbrbllfl group blong tif iorizontbl bxis bnd tif sfqufntibl group blong
 * tif vfrtidbl bxis.
 *
 * <p stylf="tfxt-blign:dfntfr">
 * <img srd="dod-filfs/groupLbyout.2.gif" blt="Sfqufntibl group blong tif vfrtidbl bxis in tirff domponfnts">
 * <p>
 * As {@dodf d1} is tif lbrgfst of tif tirff domponfnts, tif pbrbllfl
 * group is sizfd to {@dodf d1}. As {@dodf d2} bnd {@dodf d3} brf smbllfr
 * tibn {@dodf d1} tify brf blignfd bbsfd on tif blignmfnt spfdififd
 * for tif domponfnt (if spfdififd) or tif dffbult blignmfnt of tif
 * pbrbllfl group. In tif dibgrbm {@dodf d2} bnd {@dodf d3} wfrf drfbtfd
 * witi bn blignmfnt of {@dodf LEADING}. If tif domponfnt orifntbtion wfrf
 * rigit-to-lfft tifn {@dodf d2} bnd {@dodf d3} would bf positionfd on
 * tif oppositf sidf.
 * <p>
 * Tif following dibgrbm siows b sfqufntibl group blong boti tif iorizontbl
 * bnd vfrtidbl bxis.
 * <p stylf="tfxt-blign:dfntfr">
 * <img srd="dod-filfs/groupLbyout.3.gif" blt="Sfqufntibl group blong boti tif iorizontbl bnd vfrtidbl bxis in tirff domponfnts">
 * <p>
 * {@dodf GroupLbyout} providfs tif bbility to insfrt gbps bftwffn
 * {@dodf Componfnt}s. Tif sizf of tif gbp is dftfrminfd by bn
 * instbndf of {@dodf LbyoutStylf}. Tiis mby bf turnfd on using tif
 * {@dodf sftAutoCrfbtfGbps} mftiod.  Similbrly, you mby usf
 * tif {@dodf sftAutoCrfbtfContbinfrGbps} mftiod to insfrt gbps
 * bftwffn domponfnts tibt toudi tif fdgf of tif pbrfnt dontbinfr bnd tif
 * dontbinfr.
 * <p>
 * Tif following builds b pbnfl donsisting of two lbbfls in
 * onf dolumn, followfd by two tfxtfiflds in tif nfxt dolumn:
 * <prf>
 *   JComponfnt pbnfl = ...;
 *   GroupLbyout lbyout = nfw GroupLbyout(pbnfl);
 *   pbnfl.sftLbyout(lbyout);
 *
 *   // Turn on butombtidblly bdding gbps bftwffn domponfnts
 *   lbyout.sftAutoCrfbtfGbps(truf);
 *
 *   // Turn on butombtidblly drfbting gbps bftwffn domponfnts tibt toudi
 *   // tif fdgf of tif dontbinfr bnd tif dontbinfr.
 *   lbyout.sftAutoCrfbtfContbinfrGbps(truf);
 *
 *   // Crfbtf b sfqufntibl group for tif iorizontbl bxis.
 *
 *   GroupLbyout.SfqufntiblGroup iGroup = lbyout.drfbtfSfqufntiblGroup();
 *
 *   // Tif sfqufntibl group in turn dontbins two pbrbllfl groups.
 *   // Onf pbrbllfl group dontbins tif lbbfls, tif otifr tif tfxt fiflds.
 *   // Putting tif lbbfls in b pbrbllfl group blong tif iorizontbl bxis
 *   // positions tifm bt tif sbmf x lodbtion.
 *   //
 *   // Vbribblf indfntbtion is usfd to rfinfordf tif lfvfl of grouping.
 *   iGroup.bddGroup(lbyout.drfbtfPbrbllflGroup().
 *            bddComponfnt(lbbfl1).bddComponfnt(lbbfl2));
 *   iGroup.bddGroup(lbyout.drfbtfPbrbllflGroup().
 *            bddComponfnt(tf1).bddComponfnt(tf2));
 *   lbyout.sftHorizontblGroup(iGroup);
 *
 *   // Crfbtf b sfqufntibl group for tif vfrtidbl bxis.
 *   GroupLbyout.SfqufntiblGroup vGroup = lbyout.drfbtfSfqufntiblGroup();
 *
 *   // Tif sfqufntibl group dontbins two pbrbllfl groups tibt blign
 *   // tif dontfnts blong tif bbsflinf. Tif first pbrbllfl group dontbins
 *   // tif first lbbfl bnd tfxt fifld, bnd tif sfdond pbrbllfl group dontbins
 *   // tif sfdond lbbfl bnd tfxt fifld. By using b sfqufntibl group
 *   // tif lbbfls bnd tfxt fiflds brf positionfd vfrtidblly bftfr onf bnotifr.
 *   vGroup.bddGroup(lbyout.drfbtfPbrbllflGroup(Alignmfnt.BASELINE).
 *            bddComponfnt(lbbfl1).bddComponfnt(tf1));
 *   vGroup.bddGroup(lbyout.drfbtfPbrbllflGroup(Alignmfnt.BASELINE).
 *            bddComponfnt(lbbfl2).bddComponfnt(tf2));
 *   lbyout.sftVfrtidblGroup(vGroup);
 * </prf>
 * <p>
 * Wifn run tif following is produdfd.
 * <p stylf="tfxt-blign:dfntfr">
 * <img srd="dod-filfs/groupLbyout.fxbmplf.png" blt="Produdfd iorizontbl/vfrtidbl form">
 * <p>
 * Tiis lbyout donsists of tif following.
 * <ul><li>Tif iorizontbl bxis donsists of b sfqufntibl group dontbining two
 *         pbrbllfl groups.  Tif first pbrbllfl group dontbins tif lbbfls,
 *         bnd tif sfdond pbrbllfl group dontbins tif tfxt fiflds.
 *     <li>Tif vfrtidbl bxis donsists of b sfqufntibl group
 *         dontbining two pbrbllfl groups.  Tif pbrbllfl groups brf donfigurfd
 *         to blign tifir domponfnts blong tif bbsflinf. Tif first pbrbllfl
 *         group dontbins tif first lbbfl bnd first tfxt fifld, bnd
 *         tif sfdond group donsists of tif sfdond lbbfl bnd sfdond
 *         tfxt fifld.
 * </ul>
 * Tifrf brf b douplf of tiings to notidf in tiis dodf:
 * <ul>
 *   <li>You nffd not fxpliditly bdd tif domponfnts to tif dontbinfr; tiis
 *       is indirfdtly donf by using onf of tif {@dodf bdd} mftiods of
 *       {@dodf Group}.
 *   <li>Tif vbrious {@dodf bdd} mftiods rfturn
 *       tif dbllfr.  Tiis bllows for fbsy dibining of invodbtions.  For
 *       fxbmplf, {@dodf group.bddComponfnt(lbbfl1).bddComponfnt(lbbfl2);} is
 *       fquivblfnt to
 *       {@dodf group.bddComponfnt(lbbfl1); group.bddComponfnt(lbbfl2);}.
 *   <li>Tifrf brf no publid donstrudtors for {@dodf Group}s; instfbd
 *       usf tif drfbtf mftiods of {@dodf GroupLbyout}.
 * </ul>
 *
 * @butior Tombs Pbvfk
 * @butior Jbn Stolb
 * @butior Sdott Violft
 * @sindf 1.6
 */
publid dlbss GroupLbyout implfmfnts LbyoutMbnbgfr2 {
    // Usfd in sizf dbldulbtions
    privbtf stbtid finbl int MIN_SIZE = 0;

    privbtf stbtid finbl int PREF_SIZE = 1;

    privbtf stbtid finbl int MAX_SIZE = 2;

    // Usfd by prfpbrf, indidbtfs min, prff or mbx isn't going to bf usfd.
    privbtf stbtid finbl int SPECIFIC_SIZE = 3;

    privbtf stbtid finbl int UNSET = Intfgfr.MIN_VALUE;

    /**
     * Indidbtfs tif sizf from tif domponfnt or gbp siould bf usfd for b
     * pbrtidulbr rbngf vbluf.
     *
     * @sff Group
     */
    publid stbtid finbl int DEFAULT_SIZE = -1;

    /**
     * Indidbtfs tif prfffrrfd sizf from tif domponfnt or gbp siould
     * bf usfd for b pbrtidulbr rbngf vbluf.
     *
     * @sff Group
     */
    publid stbtid finbl int PREFERRED_SIZE = -2;

    // Wiftifr or not wf butombtidblly try bnd drfbtf tif prfffrrfd
    // pbdding bftwffn domponfnts.
    privbtf boolfbn butodrfbtfPbdding;

    // Wiftifr or not wf butombtidblly try bnd drfbtf tif prfffrrfd
    // pbdding bftwffn domponfnts tif toudi tif fdgf of tif dontbinfr bnd
    // tif dontbinfr.
    privbtf boolfbn butodrfbtfContbinfrPbdding;

    /**
     * Group rfsponsiblf for lbyout blong tif iorizontbl bxis.  Tiis is NOT
     * tif usfr spfdififd group, usf gftHorizontblGroup to dig tibt out.
     */
    privbtf Group iorizontblGroup;

    /**
     * Group rfsponsiblf for lbyout blong tif vfrtidbl bxis.  Tiis is NOT
     * tif usfr spfdififd group, usf gftVfrtidblGroup to dig tibt out.
     */
    privbtf Group vfrtidblGroup;

    // Mbps from Componfnt to ComponfntInfo.  Tiis is usfd for trbdking
    // informbtion spfdifid to b Componfnt.
    privbtf Mbp<Componfnt,ComponfntInfo> domponfntInfos;

    // Contbinfr wf'rf doing lbyout for.
    privbtf Contbinfr iost;

    // Usfd by brfPbrbllflSiblings, dbdifd to bvoid fxdfssivf gbrbbgf.
    privbtf Sft<Spring> tmpPbrbllflSft;

    // Indidbtfs Springs ibvf dibngfd in somf wby sindf lbst dibngf.
    privbtf boolfbn springsCibngfd;

    // Indidbtfs invblidbtfLbyout ibs bffn invokfd.
    privbtf boolfbn isVblid;

    // Wiftifr or not bny prfffrrfd pbdding (or dontbinfr pbdding) springs
    // fxist
    privbtf boolfbn ibsPrfffrrfdPbddingSprings;

    /**
     * Tif LbyoutStylf instbndf to usf, if null tif sibrfdInstbndf is usfd.
     */
    privbtf LbyoutStylf lbyoutStylf;

    /**
     * If truf, domponfnts tibt brf not visiblf brf trfbtfd bs tiougi tify
     * brfn't tifrf.
     */
    privbtf boolfbn ionorsVisibility;


    /**
     * Enumfrbtion of tif possiblf wbys {@dodf PbrbllflGroup} dbn blign
     * its diildrfn.
     *
     * @sff #drfbtfPbrbllflGroup(Alignmfnt)
     * @sindf 1.6
     */
    publid fnum Alignmfnt {
        /**
         * Indidbtfs tif flfmfnts siould bf
         * blignfd to tif origin.  For tif iorizontbl bxis witi b lfft to
         * rigit orifntbtion tiis mfbns blignfd to tif lfft fdgf. For tif
         * vfrtidbl bxis lfbding mfbns blignfd to tif top fdgf.
         *
         * @sff #drfbtfPbrbllflGroup(Alignmfnt)
         */
        LEADING,

        /**
         * Indidbtfs tif flfmfnts siould bf blignfd to tif fnd of tif
         * rfgion.  For tif iorizontbl bxis witi b lfft to rigit
         * orifntbtion tiis mfbns blignfd to tif rigit fdgf. For tif
         * vfrtidbl bxis trbiling mfbns blignfd to tif bottom fdgf.
         *
         * @sff #drfbtfPbrbllflGroup(Alignmfnt)
         */
        TRAILING,

        /**
         * Indidbtfs tif flfmfnts siould bf dfntfrfd in
         * tif rfgion.
         *
         * @sff #drfbtfPbrbllflGroup(Alignmfnt)
         */
        CENTER,

        /**
         * Indidbtfs tif flfmfnts siould bf blignfd blong
         * tifir bbsflinf.
         *
         * @sff #drfbtfPbrbllflGroup(Alignmfnt)
         * @sff #drfbtfBbsflinfGroup(boolfbn,boolfbn)
         */
        BASELINE
    }


    privbtf stbtid void difdkSizf(int min, int prff, int mbx,
            boolfbn isComponfntSpring) {
        difdkRfsizfTypf(min, isComponfntSpring);
        if (!isComponfntSpring && prff < 0) {
            tirow nfw IllfgblArgumfntExdfption("Prff must bf >= 0");
        } flsf if (isComponfntSpring) {
            difdkRfsizfTypf(prff, truf);
        }
        difdkRfsizfTypf(mbx, isComponfntSpring);
        difdkLfssTibn(min, prff);
        difdkLfssTibn(prff, mbx);
    }

    privbtf stbtid void difdkRfsizfTypf(int typf, boolfbn isComponfntSpring) {
        if (typf < 0 && ((isComponfntSpring && typf != DEFAULT_SIZE &&
                typf != PREFERRED_SIZE) ||
                (!isComponfntSpring && typf != PREFERRED_SIZE))) {
            tirow nfw IllfgblArgumfntExdfption("Invblid sizf");
        }
    }

    privbtf stbtid void difdkLfssTibn(int min, int mbx) {
        if (min >= 0 && mbx >= 0 && min > mbx) {
            tirow nfw IllfgblArgumfntExdfption(
                    "Following is not mft: min<=prff<=mbx");
        }
    }

    /**
     * Crfbtfs b {@dodf GroupLbyout} for tif spfdififd {@dodf Contbinfr}.
     *
     * @pbrbm iost tif {@dodf Contbinfr} tif {@dodf GroupLbyout} is
     *        tif {@dodf LbyoutMbnbgfr} for
     * @tirows IllfgblArgumfntExdfption if iost is {@dodf null}
     */
    publid GroupLbyout(Contbinfr iost) {
        if (iost == null) {
            tirow nfw IllfgblArgumfntExdfption("Contbinfr must bf non-null");
        }
        ionorsVisibility = truf;
        tiis.iost = iost;
        sftHorizontblGroup(drfbtfPbrbllflGroup(Alignmfnt.LEADING, truf));
        sftVfrtidblGroup(drfbtfPbrbllflGroup(Alignmfnt.LEADING, truf));
        domponfntInfos = nfw HbsiMbp<Componfnt,ComponfntInfo>();
        tmpPbrbllflSft = nfw HbsiSft<Spring>();
    }

    /**
     * Sfts wiftifr domponfnt visibility is donsidfrfd wifn sizing bnd
     * positioning domponfnts. A vbluf of {@dodf truf} indidbtfs tibt
     * non-visiblf domponfnts siould not bf trfbtfd bs pbrt of tif
     * lbyout. A vbluf of {@dodf fblsf} indidbtfs tibt domponfnts siould bf
     * positionfd bnd sizfd rfgbrdlfss of visibility.
     * <p>
     * A vbluf of {@dodf fblsf} is usfful wifn tif visibility of domponfnts
     * is dynbmidblly bdjustfd bnd you don't wbnt surrounding domponfnts bnd
     * tif sizing to dibngf.
     * <p>
     * Tif spfdififd vbluf is usfd for domponfnts tibt do not ibvf bn
     * fxplidit visibility spfdififd.
     * <p>
     * Tif dffbult is {@dodf truf}.
     *
     * @pbrbm ionorsVisibility wiftifr domponfnt visibility is donsidfrfd wifn
     *                         sizing bnd positioning domponfnts
     * @sff #sftHonorsVisibility(Componfnt,Boolfbn)
     */
    publid void sftHonorsVisibility(boolfbn ionorsVisibility) {
        if (tiis.ionorsVisibility != ionorsVisibility) {
            tiis.ionorsVisibility = ionorsVisibility;
            springsCibngfd = truf;
            isVblid = fblsf;
            invblidbtfHost();
        }
    }

    /**
     * Rfturns wiftifr domponfnt visibility is donsidfrfd wifn sizing bnd
     * positioning domponfnts.
     *
     * @rfturn wiftifr domponfnt visibility is donsidfrfd wifn sizing bnd
     *         positioning domponfnts
     */
    publid boolfbn gftHonorsVisibility() {
        rfturn ionorsVisibility;
    }

    /**
     * Sfts wiftifr tif domponfnt's visibility is donsidfrfd for
     * sizing bnd positioning. A vbluf of {@dodf Boolfbn.TRUE}
     * indidbtfs tibt if {@dodf domponfnt} is not visiblf it siould
     * not bf trfbtfd bs pbrt of tif lbyout. A vbluf of {@dodf fblsf}
     * indidbtfs tibt {@dodf domponfnt} is positionfd bnd sizfd
     * rfgbrdlfss of it's visibility.  A vbluf of {@dodf null}
     * indidbtfs tif vbluf spfdififd by tif singlf brgumfnt mftiod {@dodf
     * sftHonorsVisibility} siould bf usfd.
     * <p>
     * If {@dodf domponfnt} is not b diild of tif {@dodf Contbinfr} tiis
     * {@dodf GroupLbyout} is mbnbging, it will bf bddfd to tif
     * {@dodf Contbinfr}.
     *
     * @pbrbm domponfnt tif domponfnt
     * @pbrbm ionorsVisibility wiftifr visibility of tiis {@dodf domponfnt} siould bf
     *              donsidfrfd for sizing bnd positioning
     * @tirows IllfgblArgumfntExdfption if {@dodf domponfnt} is {@dodf null}
     * @sff #sftHonorsVisibility(Componfnt,Boolfbn)
     */
    publid void sftHonorsVisibility(Componfnt domponfnt,
            Boolfbn ionorsVisibility) {
        if (domponfnt == null) {
            tirow nfw IllfgblArgumfntExdfption("Componfnt must bf non-null");
        }
        gftComponfntInfo(domponfnt).sftHonorsVisibility(ionorsVisibility);
        springsCibngfd = truf;
        isVblid = fblsf;
        invblidbtfHost();
    }

    /**
     * Sfts wiftifr b gbp bftwffn domponfnts siould butombtidblly bf
     * drfbtfd.  For fxbmplf, if tiis is {@dodf truf} bnd you bdd two
     * domponfnts to b {@dodf SfqufntiblGroup} b gbp bftwffn tif
     * two domponfnts is butombtidblly bf drfbtfd.  Tif dffbult is
     * {@dodf fblsf}.
     *
     * @pbrbm butoCrfbtfPbdding wiftifr b gbp bftwffn domponfnts is
     *        butombtidblly drfbtfd
     */
    publid void sftAutoCrfbtfGbps(boolfbn butoCrfbtfPbdding) {
        if (tiis.butodrfbtfPbdding != butoCrfbtfPbdding) {
            tiis.butodrfbtfPbdding = butoCrfbtfPbdding;
            invblidbtfHost();
        }
    }

    /**
     * Rfturns {@dodf truf} if gbps bftwffn domponfnts brf butombtidblly
     * drfbtfd.
     *
     * @rfturn {@dodf truf} if gbps bftwffn domponfnts brf butombtidblly
     *         drfbtfd
     */
    publid boolfbn gftAutoCrfbtfGbps() {
        rfturn butodrfbtfPbdding;
    }

    /**
     * Sfts wiftifr b gbp bftwffn tif dontbinfr bnd domponfnts tibt
     * toudi tif bordfr of tif dontbinfr siould butombtidblly bf
     * drfbtfd. Tif dffbult is {@dodf fblsf}.
     *
     * @pbrbm butoCrfbtfContbinfrPbdding wiftifr b gbp bftwffn tif dontbinfr bnd
     *        domponfnts tibt toudi tif bordfr of tif dontbinfr siould
     *        butombtidblly bf drfbtfd
     */
    publid void sftAutoCrfbtfContbinfrGbps(boolfbn butoCrfbtfContbinfrPbdding){
        if (tiis.butodrfbtfContbinfrPbdding != butoCrfbtfContbinfrPbdding) {
            tiis.butodrfbtfContbinfrPbdding = butoCrfbtfContbinfrPbdding;
            iorizontblGroup = drfbtfTopLfvflGroup(gftHorizontblGroup());
            vfrtidblGroup = drfbtfTopLfvflGroup(gftVfrtidblGroup());
            invblidbtfHost();
        }
    }

    /**
     * Rfturns {@dodf truf} if gbps bftwffn tif dontbinfr bnd domponfnts tibt
     * bordfr tif dontbinfr brf butombtidblly drfbtfd.
     *
     * @rfturn {@dodf truf} if gbps bftwffn tif dontbinfr bnd domponfnts tibt
     *         bordfr tif dontbinfr brf butombtidblly drfbtfd
     */
    publid boolfbn gftAutoCrfbtfContbinfrGbps() {
        rfturn butodrfbtfContbinfrPbdding;
    }

    /**
     * Sfts tif {@dodf Group} tibt positions bnd sizfs
     * domponfnts blong tif iorizontbl bxis.
     *
     * @pbrbm group tif {@dodf Group} tibt positions bnd sizfs
     *        domponfnts blong tif iorizontbl bxis
     * @tirows IllfgblArgumfntExdfption if group is {@dodf null}
     */
    publid void sftHorizontblGroup(Group group) {
        if (group == null) {
            tirow nfw IllfgblArgumfntExdfption("Group must bf non-null");
        }
        iorizontblGroup = drfbtfTopLfvflGroup(group);
        invblidbtfHost();
    }

    /**
     * Rfturns tif {@dodf Group} tibt positions bnd sizfs domponfnts
     * blong tif iorizontbl bxis.
     *
     * @rfturn tif {@dodf Group} rfsponsiblf for positioning bnd
     *         sizing domponfnt blong tif iorizontbl bxis
     */
    privbtf Group gftHorizontblGroup() {
        int indfx = 0;
        if (iorizontblGroup.springs.sizf() > 1) {
            indfx = 1;
        }
        rfturn (Group)iorizontblGroup.springs.gft(indfx);
    }

    /**
     * Sfts tif {@dodf Group} tibt positions bnd sizfs
     * domponfnts blong tif vfrtidbl bxis.
     *
     * @pbrbm group tif {@dodf Group} tibt positions bnd sizfs
     *        domponfnts blong tif vfrtidbl bxis
     * @tirows IllfgblArgumfntExdfption if group is {@dodf null}
     */
    publid void sftVfrtidblGroup(Group group) {
        if (group == null) {
            tirow nfw IllfgblArgumfntExdfption("Group must bf non-null");
        }
        vfrtidblGroup = drfbtfTopLfvflGroup(group);
        invblidbtfHost();
    }

    /**
     * Rfturns tif {@dodf Group} tibt positions bnd sizfs domponfnts
     * blong tif vfrtidbl bxis.
     *
     * @rfturn tif {@dodf Group} rfsponsiblf for positioning bnd
     *         sizing domponfnt blong tif vfrtidbl bxis
     */
    privbtf Group gftVfrtidblGroup() {
        int indfx = 0;
        if (vfrtidblGroup.springs.sizf() > 1) {
            indfx = 1;
        }
        rfturn (Group)vfrtidblGroup.springs.gft(indfx);
    }

    /**
     * Wrbps tif usfr spfdififd group in b sfqufntibl group.  If
     * dontbinfr gbps siould bf gfnfrbtfd tif nfdfssbry springs brf
     * bddfd.
     */
    privbtf Group drfbtfTopLfvflGroup(Group spfdififdGroup) {
        SfqufntiblGroup group = drfbtfSfqufntiblGroup();
        if (gftAutoCrfbtfContbinfrGbps()) {
            group.bddSpring(nfw ContbinfrAutoPrfffrrfdGbpSpring());
            group.bddGroup(spfdififdGroup);
            group.bddSpring(nfw ContbinfrAutoPrfffrrfdGbpSpring());
        } flsf {
            group.bddGroup(spfdififdGroup);
        }
        rfturn group;
    }

    /**
     * Crfbtfs bnd rfturns b {@dodf SfqufntiblGroup}.
     *
     * @rfturn b nfw {@dodf SfqufntiblGroup}
     */
    publid SfqufntiblGroup drfbtfSfqufntiblGroup() {
        rfturn nfw SfqufntiblGroup();
    }

    /**
     * Crfbtfs bnd rfturns b {@dodf PbrbllflGroup} witi bn blignmfnt of
     * {@dodf Alignmfnt.LEADING}.  Tiis is b dovfr mftiod for tif morf
     * gfnfrbl {@dodf drfbtfPbrbllflGroup(Alignmfnt)} mftiod.
     *
     * @rfturn b nfw {@dodf PbrbllflGroup}
     * @sff #drfbtfPbrbllflGroup(Alignmfnt)
     */
    publid PbrbllflGroup drfbtfPbrbllflGroup() {
        rfturn drfbtfPbrbllflGroup(Alignmfnt.LEADING);
    }

    /**
     * Crfbtfs bnd rfturns b {@dodf PbrbllflGroup} witi tif spfdififd
     * blignmfnt.  Tiis is b dovfr mftiod for tif morf gfnfrbl {@dodf
     * drfbtfPbrbllflGroup(Alignmfnt,boolfbn)} mftiod witi {@dodf truf}
     * supplifd for tif sfdond brgumfnt.
     *
     * @pbrbm blignmfnt tif blignmfnt for tif flfmfnts of tif group
     * @tirows IllfgblArgumfntExdfption if {@dodf blignmfnt} is {@dodf null}
     * @rfturn b nfw {@dodf PbrbllflGroup}
     * @sff #drfbtfBbsflinfGroup
     * @sff PbrbllflGroup
     */
    publid PbrbllflGroup drfbtfPbrbllflGroup(Alignmfnt blignmfnt) {
        rfturn drfbtfPbrbllflGroup(blignmfnt, truf);
    }

    /**
     * Crfbtfs bnd rfturns b {@dodf PbrbllflGroup} witi tif spfdififd
     * blignmfnt bnd rfsizf bfibvior. Tif {@dodf
     * blignmfnt} brgumfnt spfdififs iow diildrfn flfmfnts brf
     * positionfd tibt do not fill tif group. For fxbmplf, if b {@dodf
     * PbrbllflGroup} witi bn blignmfnt of {@dodf TRAILING} is givfn
     * 100 bnd b diild only nffds 50, tif diild is
     * positionfd bt tif position 50 (witi b domponfnt orifntbtion of
     * lfft-to-rigit).
     * <p>
     * Bbsflinf blignmfnt is only usfful wifn usfd blong tif vfrtidbl
     * bxis. A {@dodf PbrbllflGroup} drfbtfd witi b bbsflinf blignmfnt
     * blong tif iorizontbl bxis is trfbtfd bs {@dodf LEADING}.
     * <p>
     * Rfffr to {@link GroupLbyout.PbrbllflGroup PbrbllflGroup} for dftbils on
     * tif bfibvior of bbsflinf groups.
     *
     * @pbrbm blignmfnt tif blignmfnt for tif flfmfnts of tif group
     * @pbrbm rfsizbblf {@dodf truf} if tif group is rfsizbblf; if tif group
     *        is not rfsizbblf tif prfffrrfd sizf is usfd for tif
     *        minimum bnd mbximum sizf of tif group
     * @tirows IllfgblArgumfntExdfption if {@dodf blignmfnt} is {@dodf null}
     * @rfturn b nfw {@dodf PbrbllflGroup}
     * @sff #drfbtfBbsflinfGroup
     * @sff GroupLbyout.PbrbllflGroup
     */
    publid PbrbllflGroup drfbtfPbrbllflGroup(Alignmfnt blignmfnt,
            boolfbn rfsizbblf){
        if (blignmfnt == null) {
            tirow nfw IllfgblArgumfntExdfption("blignmfnt must bf non null");
        }

        if (blignmfnt == Alignmfnt.BASELINE) {
            rfturn nfw BbsflinfGroup(rfsizbblf);
        }
        rfturn nfw PbrbllflGroup(blignmfnt, rfsizbblf);
    }

    /**
     * Crfbtfs bnd rfturns b {@dodf PbrbllflGroup} tibt bligns it's
     * flfmfnts blong tif bbsflinf.
     *
     * @pbrbm rfsizbblf wiftifr tif group is rfsizbblf
     * @pbrbm bndiorBbsflinfToTop wiftifr tif bbsflinf is bndiorfd to
     *        tif top or bottom of tif group
     * @rfturn tif {@dodf PbrbllflGroup}
     * @sff #drfbtfBbsflinfGroup
     * @sff PbrbllflGroup
     */
    publid PbrbllflGroup drfbtfBbsflinfGroup(boolfbn rfsizbblf,
            boolfbn bndiorBbsflinfToTop) {
        rfturn nfw BbsflinfGroup(rfsizbblf, bndiorBbsflinfToTop);
    }

    /**
     * Fordfs tif spfdififd domponfnts to ibvf tif sbmf sizf
     * rfgbrdlfss of tifir prfffrrfd, minimum or mbximum sizfs. Componfnts tibt
     * brf linkfd brf givfn tif mbximum of tif prfffrrfd sizf of fbdi of
     * tif linkfd domponfnts. For fxbmplf, if you link two domponfnts witi
     * b prfffrrfd widti of 10 bnd 20, boti domponfnts brf givfn b widti of 20.
     * <p>
     * Tiis dbn bf usfd multiplf timfs to fordf bny numbfr of
     * domponfnts to sibrf tif sbmf sizf.
     * <p>
     * Linkfd Componfnts brf not bf rfsizbblf.
     *
     * @pbrbm domponfnts tif {@dodf Componfnt}s tibt brf to ibvf tif sbmf sizf
     * @tirows IllfgblArgumfntExdfption if {@dodf domponfnts} is
     *         {@dodf null}, or dontbins {@dodf null}
     * @sff #linkSizf(int,Componfnt[])
     */
    publid void linkSizf(Componfnt... domponfnts) {
        linkSizf(SwingConstbnts.HORIZONTAL, domponfnts);
        linkSizf(SwingConstbnts.VERTICAL, domponfnts);
    }

    /**
     * Fordfs tif spfdififd domponfnts to ibvf tif sbmf sizf blong tif
     * spfdififd bxis rfgbrdlfss of tifir prfffrrfd, minimum or
     * mbximum sizfs. Componfnts tibt brf linkfd brf givfn tif mbximum
     * of tif prfffrrfd sizf of fbdi of tif linkfd domponfnts. For
     * fxbmplf, if you link two domponfnts blong tif iorizontbl bxis
     * bnd tif prfffrrfd widti is 10 bnd 20, boti domponfnts brf givfn
     * b widti of 20.
     * <p>
     * Tiis dbn bf usfd multiplf timfs to fordf bny numbfr of
     * domponfnts to sibrf tif sbmf sizf.
     * <p>
     * Linkfd {@dodf Componfnt}s brf not bf rfsizbblf.
     *
     * @pbrbm domponfnts tif {@dodf Componfnt}s tibt brf to ibvf tif sbmf sizf
     * @pbrbm bxis tif bxis to link tif sizf blong; onf of
     *             {@dodf SwingConstbnts.HORIZONTAL} or
     *             {@dodf SwingConstbns.VERTICAL}
     * @tirows IllfgblArgumfntExdfption if {@dodf domponfnts} is
     *         {@dodf null}, or dontbins {@dodf null}; or {@dodf bxis}
     *          is not {@dodf SwingConstbnts.HORIZONTAL} or
     *          {@dodf SwingConstbnts.VERTICAL}
     */
    publid void linkSizf(int bxis, Componfnt... domponfnts) {
        if (domponfnts == null) {
            tirow nfw IllfgblArgumfntExdfption("Componfnts must bf non-null");
        }
        for (int dountfr = domponfnts.lfngti - 1; dountfr >= 0; dountfr--) {
            Componfnt d = domponfnts[dountfr];
            if (domponfnts[dountfr] == null) {
                tirow nfw IllfgblArgumfntExdfption(
                        "Componfnts must bf non-null");
            }
            // Fordf tif domponfnt to bf bddfd
            gftComponfntInfo(d);
        }
        int glAxis;
        if (bxis == SwingConstbnts.HORIZONTAL) {
            glAxis = HORIZONTAL;
        } flsf if (bxis == SwingConstbnts.VERTICAL) {
            glAxis = VERTICAL;
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("Axis must bf onf of " +
                    "SwingConstbnts.HORIZONTAL or SwingConstbnts.VERTICAL");
        }
        LinkInfo mbstfr = gftComponfntInfo(
                domponfnts[domponfnts.lfngti - 1]).gftLinkInfo(glAxis);
        for (int dountfr = domponfnts.lfngti - 2; dountfr >= 0; dountfr--) {
            mbstfr.bdd(gftComponfntInfo(domponfnts[dountfr]));
        }
        invblidbtfHost();
    }

    /**
     * Rfplbdfs bn fxisting domponfnt witi b nfw onf.
     *
     * @pbrbm fxistingComponfnt tif domponfnt tibt siould bf rfmovfd
     *        bnd rfplbdfd witi {@dodf nfwComponfnt}
     * @pbrbm nfwComponfnt tif domponfnt to put in
     *        {@dodf fxistingComponfnt}'s plbdf
     * @tirows IllfgblArgumfntExdfption if fitifr of tif domponfnts brf
     *         {@dodf null} or {@dodf fxistingComponfnt} is not bfing mbnbgfd
     *         by tiis lbyout mbnbgfr
     */
    publid void rfplbdf(Componfnt fxistingComponfnt, Componfnt nfwComponfnt) {
        if (fxistingComponfnt == null || nfwComponfnt == null) {
            tirow nfw IllfgblArgumfntExdfption("Componfnts must bf non-null");
        }
        // Mbkf surf bll tif domponfnts ibvf bffn rfgistfrfd, otifrwisf wf mby
        // not updbtf tif dorrfdt Springs.
        if (springsCibngfd) {
            rfgistfrComponfnts(iorizontblGroup, HORIZONTAL);
            rfgistfrComponfnts(vfrtidblGroup, VERTICAL);
        }
        ComponfntInfo info = domponfntInfos.rfmovf(fxistingComponfnt);
        if (info == null) {
            tirow nfw IllfgblArgumfntExdfption("Componfnt must blrfbdy fxist");
        }
        iost.rfmovf(fxistingComponfnt);
        if (nfwComponfnt.gftPbrfnt() != iost) {
            iost.bdd(nfwComponfnt);
        }
        info.sftComponfnt(nfwComponfnt);
        domponfntInfos.put(nfwComponfnt, info);
        invblidbtfHost();
    }

    /**
     * Sfts tif {@dodf LbyoutStylf} usfd to dbldulbtf tif prfffrrfd
     * gbps bftwffn domponfnts. A vbluf of {@dodf null} indidbtfs tif
     * sibrfd instbndf of {@dodf LbyoutStylf} siould bf usfd.
     *
     * @pbrbm lbyoutStylf tif {@dodf LbyoutStylf} to usf
     * @sff LbyoutStylf
     */
    publid void sftLbyoutStylf(LbyoutStylf lbyoutStylf) {
        tiis.lbyoutStylf = lbyoutStylf;
        invblidbtfHost();
    }

    /**
     * Rfturns tif {@dodf LbyoutStylf} usfd for dbldulbting tif prfffrrfd
     * gbp bftwffn domponfnts. Tiis rfturns tif vbluf spfdififd to
     * {@dodf sftLbyoutStylf}, wiidi mby bf {@dodf null}.
     *
     * @rfturn tif {@dodf LbyoutStylf} usfd for dbldulbting tif prfffrrfd
     *         gbp bftwffn domponfnts
     */
    publid LbyoutStylf gftLbyoutStylf() {
        rfturn lbyoutStylf;
    }

    privbtf LbyoutStylf gftLbyoutStylf0() {
        LbyoutStylf lbyoutStylf = gftLbyoutStylf();
        if (lbyoutStylf == null) {
            lbyoutStylf = LbyoutStylf.gftInstbndf();
        }
        rfturn lbyoutStylf;
    }

    privbtf void invblidbtfHost() {
        if (iost instbndfof JComponfnt) {
            ((JComponfnt)iost).rfvblidbtf();
        } flsf {
            iost.invblidbtf();
        }
        iost.rfpbint();
    }

    //
    // LbyoutMbnbgfr
    //
    /**
     * Notifidbtion tibt b {@dodf Componfnt} ibs bffn bddfd to
     * tif pbrfnt dontbinfr.  You siould not invokf tiis mftiod
     * dirfdtly, instfbd you siould usf onf of tif {@dodf Group}
     * mftiods to bdd b {@dodf Componfnt}.
     *
     * @pbrbm nbmf tif string to bf bssodibtfd witi tif domponfnt
     * @pbrbm domponfnt tif {@dodf Componfnt} to bf bddfd
     */
    publid void bddLbyoutComponfnt(String nbmf, Componfnt domponfnt) {
    }

    /**
     * Notifidbtion tibt b {@dodf Componfnt} ibs bffn rfmovfd from
     * tif pbrfnt dontbinfr.  You siould not invokf tiis mftiod
     * dirfdtly, instfbd invokf {@dodf rfmovf} on tif pbrfnt
     * {@dodf Contbinfr}.
     *
     * @pbrbm domponfnt tif domponfnt to bf rfmovfd
     * @sff jbvb.bwt.Componfnt#rfmovf
     */
    publid void rfmovfLbyoutComponfnt(Componfnt domponfnt) {
        ComponfntInfo info = domponfntInfos.rfmovf(domponfnt);
        if (info != null) {
            info.disposf();
            springsCibngfd = truf;
            isVblid = fblsf;
        }
    }

    /**
     * Rfturns tif prfffrrfd sizf for tif spfdififd dontbinfr.
     *
     * @pbrbm pbrfnt tif dontbinfr to rfturn tif prfffrrfd sizf for
     * @rfturn tif prfffrrfd sizf for {@dodf pbrfnt}
     * @tirows IllfgblArgumfntExdfption if {@dodf pbrfnt} is not
     *         tif sbmf {@dodf Contbinfr} tiis wbs drfbtfd witi
     * @tirows IllfgblStbtfExdfption if bny of tif domponfnts bddfd to
     *         tiis lbyout brf not in boti b iorizontbl bnd vfrtidbl group
     * @sff jbvb.bwt.Contbinfr#gftPrfffrrfdSizf
     */
    publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr pbrfnt) {
        difdkPbrfnt(pbrfnt);
        prfpbrf(PREF_SIZE);
        rfturn bdjustSizf(iorizontblGroup.gftPrfffrrfdSizf(HORIZONTAL),
                vfrtidblGroup.gftPrfffrrfdSizf(VERTICAL));
    }

    /**
     * Rfturns tif minimum sizf for tif spfdififd dontbinfr.
     *
     * @pbrbm pbrfnt tif dontbinfr to rfturn tif sizf for
     * @rfturn tif minimum sizf for {@dodf pbrfnt}
     * @tirows IllfgblArgumfntExdfption if {@dodf pbrfnt} is not
     *         tif sbmf {@dodf Contbinfr} tibt tiis wbs drfbtfd witi
     * @tirows IllfgblStbtfExdfption if bny of tif domponfnts bddfd to
     *         tiis lbyout brf not in boti b iorizontbl bnd vfrtidbl group
     * @sff jbvb.bwt.Contbinfr#gftMinimumSizf
     */
    publid Dimfnsion minimumLbyoutSizf(Contbinfr pbrfnt) {
        difdkPbrfnt(pbrfnt);
        prfpbrf(MIN_SIZE);
        rfturn bdjustSizf(iorizontblGroup.gftMinimumSizf(HORIZONTAL),
                vfrtidblGroup.gftMinimumSizf(VERTICAL));
    }

    /**
     * Lbys out tif spfdififd dontbinfr.
     *
     * @pbrbm pbrfnt tif dontbinfr to bf lbid out
     * @tirows IllfgblStbtfExdfption if bny of tif domponfnts bddfd to
     *         tiis lbyout brf not in boti b iorizontbl bnd vfrtidbl group
     */
    publid void lbyoutContbinfr(Contbinfr pbrfnt) {
        // Stfp 1: Prfpbrf for lbyout.
        prfpbrf(SPECIFIC_SIZE);
        Insfts insfts = pbrfnt.gftInsfts();
        int widti = pbrfnt.gftWidti() - insfts.lfft - insfts.rigit;
        int ifigit = pbrfnt.gftHfigit() - insfts.top - insfts.bottom;
        boolfbn ltr = isLfftToRigit();
        if (gftAutoCrfbtfGbps() || gftAutoCrfbtfContbinfrGbps() ||
                ibsPrfffrrfdPbddingSprings) {
            // Stfp 2: Cbldulbtf butopbdding springs
            dbldulbtfAutopbdding(iorizontblGroup, HORIZONTAL, SPECIFIC_SIZE, 0,
                    widti);
            dbldulbtfAutopbdding(vfrtidblGroup, VERTICAL, SPECIFIC_SIZE, 0,
                    ifigit);
        }
        // Stfp 3: sft tif sizf of tif groups.
        iorizontblGroup.sftSizf(HORIZONTAL, 0, widti);
        vfrtidblGroup.sftSizf(VERTICAL, 0, ifigit);
        // Stfp 4: bpply tif sizf to tif domponfnts.
        for (ComponfntInfo info : domponfntInfos.vblufs()) {
            info.sftBounds(insfts, widti, ltr);
        }
    }

    //
    // LbyoutMbnbgfr2
    //
    /**
     * Notifidbtion tibt b {@dodf Componfnt} ibs bffn bddfd to
     * tif pbrfnt dontbinfr.  You siould not invokf tiis mftiod
     * dirfdtly, instfbd you siould usf onf of tif {@dodf Group}
     * mftiods to bdd b {@dodf Componfnt}.
     *
     * @pbrbm domponfnt tif domponfnt bddfd
     * @pbrbm donstrbints dfsdription of wifrf to plbdf tif domponfnt
     */
    publid void bddLbyoutComponfnt(Componfnt domponfnt, Objfdt donstrbints) {
    }

    /**
     * Rfturns tif mbximum sizf for tif spfdififd dontbinfr.
     *
     * @pbrbm pbrfnt tif dontbinfr to rfturn tif sizf for
     * @rfturn tif mbximum sizf for {@dodf pbrfnt}
     * @tirows IllfgblArgumfntExdfption if {@dodf pbrfnt} is not
     *         tif sbmf {@dodf Contbinfr} tibt tiis wbs drfbtfd witi
     * @tirows IllfgblStbtfExdfption if bny of tif domponfnts bddfd to
     *         tiis lbyout brf not in boti b iorizontbl bnd vfrtidbl group
     * @sff jbvb.bwt.Contbinfr#gftMbximumSizf
     */
    publid Dimfnsion mbximumLbyoutSizf(Contbinfr pbrfnt) {
        difdkPbrfnt(pbrfnt);
        prfpbrf(MAX_SIZE);
        rfturn bdjustSizf(iorizontblGroup.gftMbximumSizf(HORIZONTAL),
                vfrtidblGroup.gftMbximumSizf(VERTICAL));
    }

    /**
     * Rfturns tif blignmfnt blong tif x bxis.  Tiis spfdififs iow
     * tif domponfnt would likf to bf blignfd rflbtivf to otifr
     * domponfnts.  Tif vbluf siould bf b numbfr bftwffn 0 bnd 1
     * wifrf 0 rfprfsfnts blignmfnt blong tif origin, 1 is blignfd
     * tif furtifst bwby from tif origin, 0.5 is dfntfrfd, ftd.
     *
     * @pbrbm pbrfnt tif {@dodf Contbinfr} iosting tiis {@dodf LbyoutMbnbgfr}
     * @tirows IllfgblArgumfntExdfption if {@dodf pbrfnt} is not
     *         tif sbmf {@dodf Contbinfr} tibt tiis wbs drfbtfd witi
     * @rfturn tif blignmfnt; tiis implfmfntbtion rfturns {@dodf .5}
     */
    publid flobt gftLbyoutAlignmfntX(Contbinfr pbrfnt) {
        difdkPbrfnt(pbrfnt);
        rfturn .5f;
    }

    /**
     * Rfturns tif blignmfnt blong tif y bxis.  Tiis spfdififs iow
     * tif domponfnt would likf to bf blignfd rflbtivf to otifr
     * domponfnts.  Tif vbluf siould bf b numbfr bftwffn 0 bnd 1
     * wifrf 0 rfprfsfnts blignmfnt blong tif origin, 1 is blignfd
     * tif furtifst bwby from tif origin, 0.5 is dfntfrfd, ftd.
     *
     * @pbrbm pbrfnt tif {@dodf Contbinfr} iosting tiis {@dodf LbyoutMbnbgfr}
     * @tirows IllfgblArgumfntExdfption if {@dodf pbrfnt} is not
     *         tif sbmf {@dodf Contbinfr} tibt tiis wbs drfbtfd witi
     * @rfturn blignmfnt; tiis implfmfntbtion rfturns {@dodf .5}
     */
    publid flobt gftLbyoutAlignmfntY(Contbinfr pbrfnt) {
        difdkPbrfnt(pbrfnt);
        rfturn .5f;
    }

    /**
     * Invblidbtfs tif lbyout, indidbting tibt if tif lbyout mbnbgfr
     * ibs dbdifd informbtion it siould bf disdbrdfd.
     *
     * @pbrbm pbrfnt tif {@dodf Contbinfr} iosting tiis LbyoutMbnbgfr
     * @tirows IllfgblArgumfntExdfption if {@dodf pbrfnt} is not
     *         tif sbmf {@dodf Contbinfr} tibt tiis wbs drfbtfd witi
     */
    publid void invblidbtfLbyout(Contbinfr pbrfnt) {
        difdkPbrfnt(pbrfnt);
        // invblidbtfLbyout is dbllfd from Contbinfr.invblidbtf, wiidi
        // dofs NOT grbb tif trfflodk.  All otifr mftiods do.  To mbkf surf
        // tifrf brfn't bny possiblf tirfbding problfms wf grbb tif trff lodk
        // ifrf.
        syndironizfd(pbrfnt.gftTrffLodk()) {
            isVblid = fblsf;
        }
    }

    privbtf void prfpbrf(int sizfTypf) {
        boolfbn visCibngfd = fblsf;
        // Stfp 1: If not-vblid, dlfbr springs bnd updbtf visibility.
        if (!isVblid) {
            isVblid = truf;
            iorizontblGroup.sftSizf(HORIZONTAL, UNSET, UNSET);
            vfrtidblGroup.sftSizf(VERTICAL, UNSET, UNSET);
            for (ComponfntInfo di : domponfntInfos.vblufs()) {
                if (di.updbtfVisibility()) {
                    visCibngfd = truf;
                }
                di.dlfbrCbdifdSizf();
            }
        }
        // Stfp 2: Mbkf surf domponfnts brf bound to ComponfntInfos
        if (springsCibngfd) {
            rfgistfrComponfnts(iorizontblGroup, HORIZONTAL);
            rfgistfrComponfnts(vfrtidblGroup, VERTICAL);
        }
        // Stfp 3: Adjust tif butopbdding. Tiis rfmovfs fxisting
        // butopbdding, tifn rfdbldulbtfs wifrf it siould go.
        if (springsCibngfd || visCibngfd) {
            difdkComponfnts();
            iorizontblGroup.rfmovfAutopbdding();
            vfrtidblGroup.rfmovfAutopbdding();
            if (gftAutoCrfbtfGbps()) {
                insfrtAutopbdding(truf);
            } flsf if (ibsPrfffrrfdPbddingSprings ||
                    gftAutoCrfbtfContbinfrGbps()) {
                insfrtAutopbdding(fblsf);
            }
            springsCibngfd = fblsf;
        }
        // Stfp 4: (for min/prff/mbx sizf dbldulbtions only) dbldulbtf tif
        // butopbdding. Tiis invokfs for unsftting tif dbldulbtfd vblufs, tifn
        // rfdbldulbting tifm.
        // If sizfTypf == SPECIFIC_SIZE, it indidbtfs wf'rf doing lbyout, tiis
        // stfp will bf donf lbtfr on.
        if (sizfTypf != SPECIFIC_SIZE && (gftAutoCrfbtfGbps() ||
                gftAutoCrfbtfContbinfrGbps() || ibsPrfffrrfdPbddingSprings)) {
            dbldulbtfAutopbdding(iorizontblGroup, HORIZONTAL, sizfTypf, 0, 0);
            dbldulbtfAutopbdding(vfrtidblGroup, VERTICAL, sizfTypf, 0, 0);
        }
    }

    privbtf void dbldulbtfAutopbdding(Group group, int bxis, int sizfTypf,
            int origin, int sizf) {
        group.unsftAutopbdding();
        switdi(sizfTypf) {
            dbsf MIN_SIZE:
                sizf = group.gftMinimumSizf(bxis);
                brfbk;
            dbsf PREF_SIZE:
                sizf = group.gftPrfffrrfdSizf(bxis);
                brfbk;
            dbsf MAX_SIZE:
                sizf = group.gftMbximumSizf(bxis);
                brfbk;
            dffbult:
                brfbk;
        }
        group.sftSizf(bxis, origin, sizf);
        group.dbldulbtfAutopbdding(bxis);
    }

    privbtf void difdkComponfnts() {
        for (ComponfntInfo info : domponfntInfos.vblufs()) {
            if (info.iorizontblSpring == null) {
                tirow nfw IllfgblStbtfExdfption(info.domponfnt +
                        " is not bttbdifd to b iorizontbl group");
            }
            if (info.vfrtidblSpring == null) {
                tirow nfw IllfgblStbtfExdfption(info.domponfnt +
                        " is not bttbdifd to b vfrtidbl group");
            }
        }
    }

    privbtf void rfgistfrComponfnts(Group group, int bxis) {
        List<Spring> springs = group.springs;
        for (int dountfr = springs.sizf() - 1; dountfr >= 0; dountfr--) {
            Spring spring = springs.gft(dountfr);
            if (spring instbndfof ComponfntSpring) {
                ((ComponfntSpring)spring).instbllIfNfdfssbry(bxis);
            } flsf if (spring instbndfof Group) {
                rfgistfrComponfnts((Group)spring, bxis);
            }
        }
    }

    privbtf Dimfnsion bdjustSizf(int widti, int ifigit) {
        Insfts insfts = iost.gftInsfts();
        rfturn nfw Dimfnsion(widti + insfts.lfft + insfts.rigit,
                ifigit + insfts.top + insfts.bottom);
    }

    privbtf void difdkPbrfnt(Contbinfr pbrfnt) {
        if (pbrfnt != iost) {
            tirow nfw IllfgblArgumfntExdfption(
                    "GroupLbyout dbn only bf usfd witi onf Contbinfr bt b timf");
        }
    }

    /**
     * Rfturns tif {@dodf ComponfntInfo} for tif spfdififd Componfnt,
     * drfbting onf if nfdfssbry.
     */
    privbtf ComponfntInfo gftComponfntInfo(Componfnt domponfnt) {
        ComponfntInfo info = domponfntInfos.gft(domponfnt);
        if (info == null) {
            info = nfw ComponfntInfo(domponfnt);
            domponfntInfos.put(domponfnt, info);
            if (domponfnt.gftPbrfnt() != iost) {
                iost.bdd(domponfnt);
            }
        }
        rfturn info;
    }

    /**
     * Adjusts tif butopbdding springs for tif iorizontbl bnd vfrtidbl
     * groups.  If {@dodf insfrt} is {@dodf truf} tiis will insfrt buto pbdding
     * springs, otifrwisf tiis will only bdjust tif springs tibt
     * domprisf buto prfffrrfd pbdding springs.
     */
    privbtf void insfrtAutopbdding(boolfbn insfrt) {
        iorizontblGroup.insfrtAutopbdding(HORIZONTAL,
                nfw ArrbyList<AutoPrfffrrfdGbpSpring>(1),
                nfw ArrbyList<AutoPrfffrrfdGbpSpring>(1),
                nfw ArrbyList<ComponfntSpring>(1),
                nfw ArrbyList<ComponfntSpring>(1), insfrt);
        vfrtidblGroup.insfrtAutopbdding(VERTICAL,
                nfw ArrbyList<AutoPrfffrrfdGbpSpring>(1),
                nfw ArrbyList<AutoPrfffrrfdGbpSpring>(1),
                nfw ArrbyList<ComponfntSpring>(1),
                nfw ArrbyList<ComponfntSpring>(1), insfrt);
    }

    /**
     * Rfturns {@dodf truf} if tif two Componfnts ibvf b dommon PbrbllflGroup
     * bndfstor blong tif pbrtidulbr bxis.
     */
    privbtf boolfbn brfPbrbllflSiblings(Componfnt sourdf, Componfnt tbrgft,
            int bxis) {
        ComponfntInfo sourdfInfo = gftComponfntInfo(sourdf);
        ComponfntInfo tbrgftInfo = gftComponfntInfo(tbrgft);
        Spring sourdfSpring;
        Spring tbrgftSpring;
        if (bxis == HORIZONTAL) {
            sourdfSpring = sourdfInfo.iorizontblSpring;
            tbrgftSpring = tbrgftInfo.iorizontblSpring;
        } flsf {
            sourdfSpring = sourdfInfo.vfrtidblSpring;
            tbrgftSpring = tbrgftInfo.vfrtidblSpring;
        }
        Sft<Spring> sourdfPbti = tmpPbrbllflSft;
        sourdfPbti.dlfbr();
        Spring spring = sourdfSpring.gftPbrfnt();
        wiilf (spring != null) {
            sourdfPbti.bdd(spring);
            spring = spring.gftPbrfnt();
        }
        spring = tbrgftSpring.gftPbrfnt();
        wiilf (spring != null) {
            if (sourdfPbti.dontbins(spring)) {
                sourdfPbti.dlfbr();
                wiilf (spring != null) {
                    if (spring instbndfof PbrbllflGroup) {
                        rfturn truf;
                    }
                    spring = spring.gftPbrfnt();
                }
                rfturn fblsf;
            }
            spring = spring.gftPbrfnt();
        }
        sourdfPbti.dlfbr();
        rfturn fblsf;
    }

    privbtf boolfbn isLfftToRigit() {
        rfturn iost.gftComponfntOrifntbtion().isLfftToRigit();
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis {@dodf GroupLbyout}.
     * Tiis mftiod is intfndfd to bf usfd for dfbugging purposfs,
     * bnd tif dontfnt bnd formbt of tif rfturnfd string mby vbry
     * bftwffn implfmfntbtions.
     *
     * @rfturn b string rfprfsfntbtion of tiis {@dodf GroupLbyout}
     **/
    publid String toString() {
        if (springsCibngfd) {
            rfgistfrComponfnts(iorizontblGroup, HORIZONTAL);
            rfgistfrComponfnts(vfrtidblGroup, VERTICAL);
        }
        StringBufffr bufffr = nfw StringBufffr();
        bufffr.bppfnd("HORIZONTAL\n");
        drfbtfSpringDfsdription(bufffr, iorizontblGroup, "  ", HORIZONTAL);
        bufffr.bppfnd("\nVERTICAL\n");
        drfbtfSpringDfsdription(bufffr, vfrtidblGroup, "  ", VERTICAL);
        rfturn bufffr.toString();
    }

    privbtf void drfbtfSpringDfsdription(StringBufffr bufffr, Spring spring,
            String indfnt, int bxis) {
        String origin = "";
        String pbdding = "";
        if (spring instbndfof ComponfntSpring) {
            ComponfntSpring dSpring = (ComponfntSpring)spring;
            origin = Intfgfr.toString(dSpring.gftOrigin()) + " ";
            String nbmf = dSpring.gftComponfnt().gftNbmf();
            if (nbmf != null) {
                origin = "nbmf=" + nbmf + ", ";
            }
        }
        if (spring instbndfof AutoPrfffrrfdGbpSpring) {
            AutoPrfffrrfdGbpSpring pbddingSpring =
                    (AutoPrfffrrfdGbpSpring)spring;
            pbdding = ", usfrCrfbtfd=" + pbddingSpring.gftUsfrCrfbtfd() +
                    ", mbtdifs=" + pbddingSpring.gftMbtdiDfsdription();
        }
        bufffr.bppfnd(indfnt + spring.gftClbss().gftNbmf() + " " +
                Intfgfr.toHfxString(spring.ibsiCodf()) + " " +
                origin +
                ", sizf=" + spring.gftSizf() +
                ", blignmfnt=" + spring.gftAlignmfnt() +
                " prffs=[" + spring.gftMinimumSizf(bxis) +
                " " + spring.gftPrfffrrfdSizf(bxis) +
                " " + spring.gftMbximumSizf(bxis) +
                pbdding + "]\n");
        if (spring instbndfof Group) {
            List<Spring> springs = ((Group)spring).springs;
            indfnt += "  ";
            for (int dountfr = 0; dountfr < springs.sizf(); dountfr++) {
                drfbtfSpringDfsdription(bufffr, springs.gft(dountfr), indfnt,
                        bxis);
            }
        }
    }


    /**
     * Spring donsists of b rbngf: min, prff bnd mbx, b vbluf somf wifrf in
     * tif middlf of tibt, bnd b lodbtion. Spring dbdifs tif
     * min/mbx/prff.  If tif min/prff/mbx ibs intfrnblly dibngfs, or nffds
     * to bf updbtfd you must invokf dlfbr.
     */
    privbtf bbstrbdt dlbss Spring {
        privbtf int sizf;
        privbtf int min;
        privbtf int mbx;
        privbtf int prff;
        privbtf Spring pbrfnt;

        privbtf Alignmfnt blignmfnt;

        Spring() {
            min = prff = mbx = UNSET;
        }

        /**
         * Cbldulbtfs bnd rfturns tif minimum sizf.
         *
         * @pbrbm bxis tif bxis of lbyout; onf of HORIZONTAL or VERTICAL
         * @rfturn tif minimum sizf
         */
        bbstrbdt int dbldulbtfMinimumSizf(int bxis);

        /**
         * Cbldulbtfs bnd rfturns tif prfffrrfd sizf.
         *
         * @pbrbm bxis tif bxis of lbyout; onf of HORIZONTAL or VERTICAL
         * @rfturn tif prfffrrfd sizf
         */
        bbstrbdt int dbldulbtfPrfffrrfdSizf(int bxis);

        /**
         * Cbldulbtfs bnd rfturns tif minimum sizf.
         *
         * @pbrbm bxis tif bxis of lbyout; onf of HORIZONTAL or VERTICAL
         * @rfturn tif minimum sizf
         */
        bbstrbdt int dbldulbtfMbximumSizf(int bxis);

        /**
         * Sfts tif pbrfnt of tiis Spring.
         */
        void sftPbrfnt(Spring pbrfnt) {
            tiis.pbrfnt = pbrfnt;
        }

        /**
         * Rfturns tif pbrfnt of tiis spring.
         */
        Spring gftPbrfnt() {
            rfturn pbrfnt;
        }

        // Tiis is ifrf purfly bs b donvfnifndf for PbrbllflGroup to bvoid
        // ibving to trbdk blignmfnt sfpbrbtfly.
        void sftAlignmfnt(Alignmfnt blignmfnt) {
            tiis.blignmfnt = blignmfnt;
        }

        /**
         * Alignmfnt for tiis Spring, tiis mby bf null.
         */
        Alignmfnt gftAlignmfnt() {
            rfturn blignmfnt;
        }

        /**
         * Rfturns tif minimum sizf.
         */
        finbl int gftMinimumSizf(int bxis) {
            if (min == UNSET) {
                min = donstrbin(dbldulbtfMinimumSizf(bxis));
            }
            rfturn min;
        }

        /**
         * Rfturns tif prfffrrfd sizf.
         */
        finbl int gftPrfffrrfdSizf(int bxis) {
            if (prff == UNSET) {
                prff = donstrbin(dbldulbtfPrfffrrfdSizf(bxis));
            }
            rfturn prff;
        }

        /**
         * Rfturns tif mbximum sizf.
         */
        finbl int gftMbximumSizf(int bxis) {
            if (mbx == UNSET) {
                mbx = donstrbin(dbldulbtfMbximumSizf(bxis));
            }
            rfturn mbx;
        }

        /**
         * Sfts tif vbluf bnd lodbtion of tif spring.  Subdlbssfs
         * will wbnt to invokf supfr, tifn do bny bdditionbl sizing.
         *
         * @pbrbm bxis HORIZONTAL or VERTICAL
         * @pbrbm origin of tiis Spring
         * @pbrbm sizf of tif Spring.  If sizf is UNSET, tiis invokfs
         *        dlfbr.
         */
        void sftSizf(int bxis, int origin, int sizf) {
            tiis.sizf = sizf;
            if (sizf == UNSET) {
                unsft();
            }
        }

        /**
         * Rfsfts tif dbdifd min/mbx/prff.
         */
        void unsft() {
            sizf = min = prff = mbx = UNSET;
        }

        /**
         * Rfturns tif durrfnt sizf.
         */
        int gftSizf() {
            rfturn sizf;
        }

        int donstrbin(int vbluf) {
            rfturn Mbti.min(vbluf, Siort.MAX_VALUE);
        }

        int gftBbsflinf() {
            rfturn -1;
        }

        BbsflinfRfsizfBfibvior gftBbsflinfRfsizfBfibvior() {
            rfturn BbsflinfRfsizfBfibvior.OTHER;
        }

        finbl boolfbn isRfsizbblf(int bxis) {
            int min = gftMinimumSizf(bxis);
            int prff = gftPrfffrrfdSizf(bxis);
            rfturn (min != prff || prff != gftMbximumSizf(bxis));
        }

        /**
         * Rfturns {@dodf truf} if tiis spring will ALWAYS ibvf b zfro
         * sizf. Tiis siould NOT difdk tif durrfnt sizf, rbtifr it's
         * mfbnt to quidkly tfst if tiis Spring will blwbys ibvf b
         * zfro sizf.
         *
         * @pbrbm trfbtAutopbddingAsZfroSizfd if {@dodf truf}, buto pbdding
         *        springs siould bf trfbtfd bs ibving b sizf of {@dodf 0}
         * @rfturn {@dodf truf} if tiis spring will ibvf b zfro sizf,
         *         {@dodf fblsf} otifrwisf
         */
        bbstrbdt boolfbn willHbvfZfroSizf(boolfbn trfbtAutopbddingAsZfroSizfd);
    }

    /**
     * {@dodf Group} providfs tif bbsis for tif two typfs of
     * opfrbtions supportfd by {@dodf GroupLbyout}: lbying out
     * domponfnts onf bftfr bnotifr ({@link SfqufntiblGroup SfqufntiblGroup})
     * or blignfd ({@link PbrbllflGroup PbrbllflGroup}). {@dodf Group} bnd
     * its subdlbssfs ibvf no publid donstrudtor; to drfbtf onf usf
     * onf of {@dodf drfbtfSfqufntiblGroup} or
     * {@dodf drfbtfPbrbllflGroup}. Additionblly, tbking b {@dodf Group}
     * drfbtfd from onf {@dodf GroupLbyout} bnd using it witi bnotifr
     * will produdf undffinfd rfsults.
     * <p>
     * Vbrious mftiods in {@dodf Group} bnd its subdlbssfs bllow you
     * to fxpliditly spfdify tif rbngf. Tif brgumfnts to tifsf mftiods
     * dbn tbkf two forms, fitifr b vbluf grfbtfr tibn or fqubl to 0,
     * or onf of {@dodf DEFAULT_SIZE} or {@dodf PREFERRED_SIZE}. A
     * vbluf grfbtfr tibn or fqubl to {@dodf 0} indidbtfs b spfdifid
     * sizf. {@dodf DEFAULT_SIZE} indidbtfs tif dorrfsponding sizf
     * from tif domponfnt siould bf usfd.  For fxbmplf, if {@dodf
     * DEFAULT_SIZE} is pbssfd bs tif minimum sizf brgumfnt, tif
     * minimum sizf is obtbinfd from invoking {@dodf gftMinimumSizf}
     * on tif domponfnt. Likfwisf, {@dodf PREFERRED_SIZE} indidbtfs
     * tif vbluf from {@dodf gftPrfffrrfdSizf} siould bf usfd.
     * Tif following fxbmplf bdds {@dodf myComponfnt} to {@dodf group}
     * witi spfdifid vblufs for tif rbngf. Tibt is, tif minimum is
     * fxpliditly spfdififd bs 100, prfffrrfd bs 200, bnd mbximum bs
     * 300.
     * <prf>
     *   group.bddComponfnt(myComponfnt, 100, 200, 300);
     * </prf>
     * Tif following fxbmplf bdds {@dodf myComponfnt} to {@dodf group} using
     * b dombinbtion of tif forms. Tif minimum sizf is fordfd to bf tif
     * sbmf bs tif prfffrrfd sizf, tif prfffrrfd sizf is dftfrminfd by
     * using {@dodf myComponfnt.gftPrfffrrfdSizf} bnd tif mbximum is
     * dftfrminfd by invoking {@dodf gftMbximumSizf} on tif domponfnt.
     * <prf>
     *   group.bddComponfnt(myComponfnt, GroupLbyout.PREFERRED_SIZE,
     *             GroupLbyout.PREFERRED_SIZE, GroupLbyout.DEFAULT_SIZE);
     * </prf>
     * <p>
     * Unlfss otifrwisf spfdififd bll tif mftiods of {@dodf Group} bnd
     * its subdlbssfs tibt bllow you to spfdify b rbngf tirow bn
     * {@dodf IllfgblArgumfntExdfption} if pbssfd bn invblid rbngf. An
     * invblid rbngf is onf in wiidi bny of tif vblufs brf &lt; 0 bnd
     * not onf of {@dodf PREFERRED_SIZE} or {@dodf DEFAULT_SIZE}, or
     * tif following is not mft (for spfdifid vblufs): {@dodf min}
     * &lt;= {@dodf prff} &lt;= {@dodf mbx}.
     * <p>
     * Similbrly bny mftiods tibt tbkf b {@dodf Componfnt} tirow b
     * {@dodf IllfgblArgumfntExdfption} if pbssfd {@dodf null} bnd bny mftiods
     * tibt tbkf b {@dodf Group} tirow bn {@dodf NullPointfrExdfption} if
     * pbssfd {@dodf null}.
     *
     * @sff #drfbtfSfqufntiblGroup
     * @sff #drfbtfPbrbllflGroup
     * @sindf 1.6
     */
    publid bbstrbdt dlbss Group fxtfnds Spring {
        // privbtf int origin;
        // privbtf int sizf;
        List<Spring> springs;

        Group() {
            springs = nfw ArrbyList<Spring>();
        }

        /**
         * Adds b {@dodf Group} to tiis {@dodf Group}.
         *
         * @pbrbm group tif {@dodf Group} to bdd
         * @rfturn tiis {@dodf Group}
         */
        publid Group bddGroup(Group group) {
            rfturn bddSpring(group);
        }

        /**
         * Adds b {@dodf Componfnt} to tiis {@dodf Group}.
         *
         * @pbrbm domponfnt tif {@dodf Componfnt} to bdd
         * @rfturn tiis {@dodf Group}
         */
        publid Group bddComponfnt(Componfnt domponfnt) {
            rfturn bddComponfnt(domponfnt, DEFAULT_SIZE, DEFAULT_SIZE,
                    DEFAULT_SIZE);
        }

        /**
         * Adds b {@dodf Componfnt} to tiis {@dodf Group}
         * witi tif spfdififd sizf.
         *
         * @pbrbm domponfnt tif {@dodf Componfnt} to bdd
         * @pbrbm min tif minimum sizf or onf of {@dodf DEFAULT_SIZE} or
         *            {@dodf PREFERRED_SIZE}
         * @pbrbm prff tif prfffrrfd sizf or onf of {@dodf DEFAULT_SIZE} or
         *            {@dodf PREFERRED_SIZE}
         * @pbrbm mbx tif mbximum sizf or onf of {@dodf DEFAULT_SIZE} or
         *            {@dodf PREFERRED_SIZE}
         * @rfturn tiis {@dodf Group}
         */
        publid Group bddComponfnt(Componfnt domponfnt, int min, int prff,
                int mbx) {
            rfturn bddSpring(nfw ComponfntSpring(domponfnt, min, prff, mbx));
        }

        /**
         * Adds b rigid gbp to tiis {@dodf Group}.
         *
         * @pbrbm sizf tif sizf of tif gbp
         * @rfturn tiis {@dodf Group}
         * @tirows IllfgblArgumfntExdfption if {@dodf sizf} is lfss tibn
         *         {@dodf 0}
         */
        publid Group bddGbp(int sizf) {
            rfturn bddGbp(sizf, sizf, sizf);
        }

        /**
         * Adds b gbp to tiis {@dodf Group} witi tif spfdififd sizf.
         *
         * @pbrbm min tif minimum sizf of tif gbp
         * @pbrbm prff tif prfffrrfd sizf of tif gbp
         * @pbrbm mbx tif mbximum sizf of tif gbp
         * @tirows IllfgblArgumfntExdfption if bny of tif vblufs brf
         *         lfss tibn {@dodf 0}
         * @rfturn tiis {@dodf Group}
         */
        publid Group bddGbp(int min, int prff, int mbx) {
            rfturn bddSpring(nfw GbpSpring(min, prff, mbx));
        }

        Spring gftSpring(int indfx) {
            rfturn springs.gft(indfx);
        }

        int indfxOf(Spring spring) {
            rfturn springs.indfxOf(spring);
        }

        /**
         * Adds tif Spring to tif list of {@dodf Spring}s bnd rfturns
         * tif rfdfivfr.
         */
        Group bddSpring(Spring spring) {
            springs.bdd(spring);
            spring.sftPbrfnt(tiis);
            if (!(spring instbndfof AutoPrfffrrfdGbpSpring) ||
                    !((AutoPrfffrrfdGbpSpring)spring).gftUsfrCrfbtfd()) {
                springsCibngfd = truf;
            }
            rfturn tiis;
        }

        //
        // Spring mftiods
        //

        void sftSizf(int bxis, int origin, int sizf) {
            supfr.sftSizf(bxis, origin, sizf);
            if (sizf == UNSET) {
                for (int dountfr = springs.sizf() - 1; dountfr >= 0;
                dountfr--) {
                    gftSpring(dountfr).sftSizf(bxis, origin, sizf);
                }
            } flsf {
                sftVblidSizf(bxis, origin, sizf);
            }
        }

        /**
         * Tiis is invokfd from {@dodf sftSizf} if pbssfd b vbluf
         * otifr tibn UNSET.
         */
        bbstrbdt void sftVblidSizf(int bxis, int origin, int sizf);

        int dbldulbtfMinimumSizf(int bxis) {
            rfturn dbldulbtfSizf(bxis, MIN_SIZE);
        }

        int dbldulbtfPrfffrrfdSizf(int bxis) {
            rfturn dbldulbtfSizf(bxis, PREF_SIZE);
        }

        int dbldulbtfMbximumSizf(int bxis) {
            rfturn dbldulbtfSizf(bxis, MAX_SIZE);
        }

        /**
         * Cbldulbtfs tif spfdififd sizf.  Tiis is dbllfd from
         * onf of tif {@dodf gftMinimumSizf0},
         * {@dodf gftPrfffrrfdSizf0} or
         * {@dodf gftMbximumSizf0} mftiods.  Tiis will invokf
         * to {@dodf opfrbtor} to dombinf tif vblufs.
         */
        int dbldulbtfSizf(int bxis, int typf) {
            int dount = springs.sizf();
            if (dount == 0) {
                rfturn 0;
            }
            if (dount == 1) {
                rfturn gftSpringSizf(gftSpring(0), bxis, typf);
            }
            int sizf = donstrbin(opfrbtor(gftSpringSizf(gftSpring(0), bxis,
                    typf), gftSpringSizf(gftSpring(1), bxis, typf)));
            for (int dountfr = 2; dountfr < dount; dountfr++) {
                sizf = donstrbin(opfrbtor(sizf, gftSpringSizf(
                        gftSpring(dountfr), bxis, typf)));
            }
            rfturn sizf;
        }

        int gftSpringSizf(Spring spring, int bxis, int typf) {
            switdi(typf) {
                dbsf MIN_SIZE:
                    rfturn spring.gftMinimumSizf(bxis);
                dbsf PREF_SIZE:
                    rfturn spring.gftPrfffrrfdSizf(bxis);
                dbsf MAX_SIZE:
                    rfturn spring.gftMbximumSizf(bxis);
            }
            bssfrt fblsf;
            rfturn 0;
        }

        /**
         * Usfd to domputf iow tif two vblufs rfprfsfnting two springs
         * will bf dombinfd.  For fxbmplf, b group tibt lbyfd tiings out
         * onf bftfr tif nfxt would rfturn {@dodf b + b}.
         */
        bbstrbdt int opfrbtor(int b, int b);

        //
        // Pbdding
        //

        /**
         * Adjusts tif butopbdding springs in tiis group bnd its diildrfn.
         * If {@dodf insfrt} is truf tiis will insfrt buto pbdding
         * springs, otifrwisf tiis will only bdjust tif springs tibt
         * domprisf buto prfffrrfd pbdding springs.
         *
         * @pbrbm bxis tif bxis of tif springs; HORIZONTAL or VERTICAL
         * @pbrbm lfbdingPbdding List of AutopbddingSprings tibt oddur bfforf
         *                       tiis Group
         * @pbrbm trbilingPbdding bny trbiling butopbdding springs brf bddfd
         *                        to tiis on fxit
         * @pbrbm lfbding List of ComponfntSprings tibt oddur bfforf tiis Group
         * @pbrbm trbiling bny trbiling ComponfntSpring brf bddfd to tiis
         *                 List
         * @pbrbm insfrt Wiftifr or not to insfrt AutopbddingSprings or just
         *               bdjust bny fxisting AutopbddingSprings.
         */
        bbstrbdt void insfrtAutopbdding(int bxis,
                List<AutoPrfffrrfdGbpSpring> lfbdingPbdding,
                List<AutoPrfffrrfdGbpSpring> trbilingPbdding,
                List<ComponfntSpring> lfbding, List<ComponfntSpring> trbiling,
                boolfbn insfrt);

        /**
         * Rfmovfs bny AutopbddingSprings for tiis Group bnd its diildrfn.
         */
        void rfmovfAutopbdding() {
            unsft();
            for (int dountfr = springs.sizf() - 1; dountfr >= 0; dountfr--) {
                Spring spring = springs.gft(dountfr);
                if (spring instbndfof AutoPrfffrrfdGbpSpring) {
                    if (((AutoPrfffrrfdGbpSpring)spring).gftUsfrCrfbtfd()) {
                        ((AutoPrfffrrfdGbpSpring)spring).rfsft();
                    } flsf {
                        springs.rfmovf(dountfr);
                    }
                } flsf if (spring instbndfof Group) {
                    ((Group)spring).rfmovfAutopbdding();
                }
            }
        }

        void unsftAutopbdding() {
            // Clfbr dbdifd prff/min/mbx.
            unsft();
            for (int dountfr = springs.sizf() - 1; dountfr >= 0; dountfr--) {
                Spring spring = springs.gft(dountfr);
                if (spring instbndfof AutoPrfffrrfdGbpSpring) {
                    spring.unsft();
                } flsf if (spring instbndfof Group) {
                    ((Group)spring).unsftAutopbdding();
                }
            }
        }

        void dbldulbtfAutopbdding(int bxis) {
            for (int dountfr = springs.sizf() - 1; dountfr >= 0; dountfr--) {
                Spring spring = springs.gft(dountfr);
                if (spring instbndfof AutoPrfffrrfdGbpSpring) {
                    // Fordf sizf to bf rfsft.
                    spring.unsft();
                    ((AutoPrfffrrfdGbpSpring)spring).dbldulbtfPbdding(bxis);
                } flsf if (spring instbndfof Group) {
                    ((Group)spring).dbldulbtfAutopbdding(bxis);
                }
            }
            // Clfbr dbdifd prff/min/mbx.
            unsft();
        }

        @Ovfrridf
        boolfbn willHbvfZfroSizf(boolfbn trfbtAutopbddingAsZfroSizfd) {
            for (int i = springs.sizf() - 1; i >= 0; i--) {
                Spring spring = springs.gft(i);
                if (!spring.willHbvfZfroSizf(trfbtAutopbddingAsZfroSizfd)) {
                    rfturn fblsf;
                }
            }
            rfturn truf;
        }
    }


    /**
     * A {@dodf Group} tibt positions bnd sizfs its flfmfnts
     * sfqufntiblly, onf bftfr bnotifr.  Tiis dlbss ibs no publid
     * donstrudtor, usf tif {@dodf drfbtfSfqufntiblGroup} mftiod
     * to drfbtf onf.
     * <p>
     * In ordfr to blign b {@dodf SfqufntiblGroup} blong tif bbsflinf
     * of b bbsflinf blignfd {@dodf PbrbllflGroup} you nffd to spfdify
     * wiidi of tif flfmfnts of tif {@dodf SfqufntiblGroup} is usfd to
     * dftfrminf tif bbsflinf.  Tif flfmfnt usfd to dbldulbtf tif
     * bbsflinf is spfdififd using onf of tif {@dodf bdd} mftiods tibt
     * tbkf b {@dodf boolfbn}. Tif lbst flfmfnt bddfd witi b vbluf of
     * {@dodf truf} for {@dodf usfAsBbsflinf} is usfd to dbldulbtf tif
     * bbsflinf.
     *
     * @sff #drfbtfSfqufntiblGroup
     * @sindf 1.6
     */
    publid dlbss SfqufntiblGroup fxtfnds Group {
        privbtf Spring bbsflinfSpring;

        SfqufntiblGroup() {
        }

        /**
         * {@inifritDod}
         */
        publid SfqufntiblGroup bddGroup(Group group) {
            rfturn (SfqufntiblGroup)supfr.bddGroup(group);
        }

        /**
         * Adds b {@dodf Group} to tiis {@dodf Group}.
         *
         * @pbrbm group tif {@dodf Group} to bdd
         * @pbrbm usfAsBbsflinf wiftifr tif spfdififd {@dodf Group} siould
         *        bf usfd to dbldulbtf tif bbsflinf for tiis {@dodf Group}
         * @rfturn tiis {@dodf Group}
         */
        publid SfqufntiblGroup bddGroup(boolfbn usfAsBbsflinf, Group group) {
            supfr.bddGroup(group);
            if (usfAsBbsflinf) {
                bbsflinfSpring = group;
            }
            rfturn tiis;
        }

        /**
         * {@inifritDod}
         */
        publid SfqufntiblGroup bddComponfnt(Componfnt domponfnt) {
            rfturn (SfqufntiblGroup)supfr.bddComponfnt(domponfnt);
        }

        /**
         * Adds b {@dodf Componfnt} to tiis {@dodf Group}.
         *
         * @pbrbm usfAsBbsflinf wiftifr tif spfdififd {@dodf Componfnt} siould
         *        bf usfd to dbldulbtf tif bbsflinf for tiis {@dodf Group}
         * @pbrbm domponfnt tif {@dodf Componfnt} to bdd
         * @rfturn tiis {@dodf Group}
         */
        publid SfqufntiblGroup bddComponfnt(boolfbn usfAsBbsflinf,
                Componfnt domponfnt) {
            supfr.bddComponfnt(domponfnt);
            if (usfAsBbsflinf) {
                bbsflinfSpring = springs.gft(springs.sizf() - 1);
            }
            rfturn tiis;
        }

        /**
         * {@inifritDod}
         */
        publid SfqufntiblGroup bddComponfnt(Componfnt domponfnt, int min,
                int prff, int mbx) {
            rfturn (SfqufntiblGroup)supfr.bddComponfnt(
                    domponfnt, min, prff, mbx);
        }

        /**
         * Adds b {@dodf Componfnt} to tiis {@dodf Group}
         * witi tif spfdififd sizf.
         *
         * @pbrbm usfAsBbsflinf wiftifr tif spfdififd {@dodf Componfnt} siould
         *        bf usfd to dbldulbtf tif bbsflinf for tiis {@dodf Group}
         * @pbrbm domponfnt tif {@dodf Componfnt} to bdd
         * @pbrbm min tif minimum sizf or onf of {@dodf DEFAULT_SIZE} or
         *            {@dodf PREFERRED_SIZE}
         * @pbrbm prff tif prfffrrfd sizf or onf of {@dodf DEFAULT_SIZE} or
         *            {@dodf PREFERRED_SIZE}
         * @pbrbm mbx tif mbximum sizf or onf of {@dodf DEFAULT_SIZE} or
         *            {@dodf PREFERRED_SIZE}
         * @rfturn tiis {@dodf Group}
         */
        publid SfqufntiblGroup bddComponfnt(boolfbn usfAsBbsflinf,
                Componfnt domponfnt, int min, int prff, int mbx) {
            supfr.bddComponfnt(domponfnt, min, prff, mbx);
            if (usfAsBbsflinf) {
                bbsflinfSpring = springs.gft(springs.sizf() - 1);
            }
            rfturn tiis;
        }

        /**
         * {@inifritDod}
         */
        publid SfqufntiblGroup bddGbp(int sizf) {
            rfturn (SfqufntiblGroup)supfr.bddGbp(sizf);
        }

        /**
         * {@inifritDod}
         */
        publid SfqufntiblGroup bddGbp(int min, int prff, int mbx) {
            rfturn (SfqufntiblGroup)supfr.bddGbp(min, prff, mbx);
        }

        /**
         * Adds bn flfmfnt rfprfsfnting tif prfffrrfd gbp bftwffn two
         * domponfnts. Tif flfmfnt drfbtfd to rfprfsfnt tif gbp is not
         * rfsizbblf.
         *
         * @pbrbm domp1 tif first domponfnt
         * @pbrbm domp2 tif sfdond domponfnt
         * @pbrbm typf tif typf of gbp; onf of tif donstbnts dffinfd by
         *        {@dodf LbyoutStylf}
         * @rfturn tiis {@dodf SfqufntiblGroup}
         * @tirows IllfgblArgumfntExdfption if {@dodf typf}, {@dodf domp1} or
         *         {@dodf domp2} is {@dodf null}
         * @sff LbyoutStylf
         */
        publid SfqufntiblGroup bddPrfffrrfdGbp(JComponfnt domp1,
                JComponfnt domp2, ComponfntPlbdfmfnt typf) {
            rfturn bddPrfffrrfdGbp(domp1, domp2, typf, DEFAULT_SIZE,
                    PREFERRED_SIZE);
        }

        /**
         * Adds bn flfmfnt rfprfsfnting tif prfffrrfd gbp bftwffn two
         * domponfnts.
         *
         * @pbrbm domp1 tif first domponfnt
         * @pbrbm domp2 tif sfdond domponfnt
         * @pbrbm typf tif typf of gbp
         * @pbrbm prff tif prfffrrfd sizf of tif grbp; onf of
         *        {@dodf DEFAULT_SIZE} or b vbluf &gt;= 0
         * @pbrbm mbx tif mbximum sizf of tif gbp; onf of
         *        {@dodf DEFAULT_SIZE}, {@dodf PREFERRED_SIZE}
         *        or b vbluf &gt;= 0
         * @rfturn tiis {@dodf SfqufntiblGroup}
         * @tirows IllfgblArgumfntExdfption if {@dodf typf}, {@dodf domp1} or
         *         {@dodf domp2} is {@dodf null}
         * @sff LbyoutStylf
         */
        publid SfqufntiblGroup bddPrfffrrfdGbp(JComponfnt domp1,
                JComponfnt domp2, ComponfntPlbdfmfnt typf, int prff,
                int mbx) {
            if (typf == null) {
                tirow nfw IllfgblArgumfntExdfption("Typf must bf non-null");
            }
            if (domp1 == null || domp2 == null) {
                tirow nfw IllfgblArgumfntExdfption(
                        "Componfnts must bf non-null");
            }
            difdkPrfffrrfdGbpVblufs(prff, mbx);
            rfturn (SfqufntiblGroup)bddSpring(nfw PrfffrrfdGbpSpring(
                    domp1, domp2, typf, prff, mbx));
        }

        /**
         * Adds bn flfmfnt rfprfsfnting tif prfffrrfd gbp bftwffn tif
         * nfbrfst domponfnts.  During lbyout, nfigiboring
         * domponfnts brf found, bnd tif sizf of tif bddfd gbp is sft
         * bbsfd on tif prfffrrfd gbp bftwffn tif domponfnts.  If no
         * nfigiboring domponfnts brf found tif gbp ibs b sizf of {@dodf 0}.
         * <p>
         * Tif flfmfnt drfbtfd to rfprfsfnt tif gbp is not
         * rfsizbblf.
         *
         * @pbrbm typf tif typf of gbp; onf of
         *        {@dodf LbyoutStylf.ComponfntPlbdfmfnt.RELATED} or
         *        {@dodf LbyoutStylf.ComponfntPlbdfmfnt.UNRELATED}
         * @rfturn tiis {@dodf SfqufntiblGroup}
         * @sff LbyoutStylf
         * @tirows IllfgblArgumfntExdfption if {@dodf typf} is not onf of
         *         {@dodf LbyoutStylf.ComponfntPlbdfmfnt.RELATED} or
         *         {@dodf LbyoutStylf.ComponfntPlbdfmfnt.UNRELATED}
         */
        publid SfqufntiblGroup bddPrfffrrfdGbp(ComponfntPlbdfmfnt typf) {
            rfturn bddPrfffrrfdGbp(typf, DEFAULT_SIZE, DEFAULT_SIZE);
        }

        /**
         * Adds bn flfmfnt rfprfsfnting tif prfffrrfd gbp bftwffn tif
         * nfbrfst domponfnts.  During lbyout, nfigiboring
         * domponfnts brf found, bnd tif minimum of tiis
         * gbp is sft bbsfd on tif sizf of tif prfffrrfd gbp bftwffn tif
         * nfigiboring domponfnts.  If no nfigiboring domponfnts brf found tif
         * minimum sizf is sft to 0.
         *
         * @pbrbm typf tif typf of gbp; onf of
         *        {@dodf LbyoutStylf.ComponfntPlbdfmfnt.RELATED} or
         *        {@dodf LbyoutStylf.ComponfntPlbdfmfnt.UNRELATED}
         * @pbrbm prff tif prfffrrfd sizf of tif grbp; onf of
         *        {@dodf DEFAULT_SIZE} or b vbluf &gt;= 0
         * @pbrbm mbx tif mbximum sizf of tif gbp; onf of
         *        {@dodf DEFAULT_SIZE}, {@dodf PREFERRED_SIZE}
         *        or b vbluf &gt;= 0
         * @rfturn tiis {@dodf SfqufntiblGroup}
         * @tirows IllfgblArgumfntExdfption if {@dodf typf} is not onf of
         *         {@dodf LbyoutStylf.ComponfntPlbdfmfnt.RELATED} or
         *         {@dodf LbyoutStylf.ComponfntPlbdfmfnt.UNRELATED}
         * @sff LbyoutStylf
         */
        publid SfqufntiblGroup bddPrfffrrfdGbp(ComponfntPlbdfmfnt typf,
                int prff, int mbx) {
            if (typf != ComponfntPlbdfmfnt.RELATED &&
                    typf != ComponfntPlbdfmfnt.UNRELATED) {
                tirow nfw IllfgblArgumfntExdfption(
                        "Typf must bf onf of " +
                        "LbyoutStylf.ComponfntPlbdfmfnt.RELATED or " +
                        "LbyoutStylf.ComponfntPlbdfmfnt.UNRELATED");
            }
            difdkPrfffrrfdGbpVblufs(prff, mbx);
            ibsPrfffrrfdPbddingSprings = truf;
            rfturn (SfqufntiblGroup)bddSpring(nfw AutoPrfffrrfdGbpSpring(
                    typf, prff, mbx));
        }

        /**
         * Adds bn flfmfnt rfprfsfnting tif prfffrrfd gbp bftwffn bn fdgf
         * tif dontbinfr bnd domponfnts tibt toudi tif bordfr of tif
         * dontbinfr. Tiis ibs no ffffdt if tif bddfd gbp dofs not
         * toudi bn fdgf of tif pbrfnt dontbinfr.
         * <p>
         * Tif flfmfnt drfbtfd to rfprfsfnt tif gbp is not
         * rfsizbblf.
         *
         * @rfturn tiis {@dodf SfqufntiblGroup}
         */
        publid SfqufntiblGroup bddContbinfrGbp() {
            rfturn bddContbinfrGbp(DEFAULT_SIZE, DEFAULT_SIZE);
        }

        /**
         * Adds bn flfmfnt rfprfsfnting tif prfffrrfd gbp bftwffn onf
         * fdgf of tif dontbinfr bnd tif nfxt or prfvious {@dodf
         * Componfnt} witi tif spfdififd sizf. Tiis ibs no
         * ffffdt if tif nfxt or prfvious flfmfnt is not b {@dodf
         * Componfnt} bnd dofs not toudi onf fdgf of tif pbrfnt
         * dontbinfr.
         *
         * @pbrbm prff tif prfffrrfd sizf; onf of {@dodf DEFAULT_SIZE} or b
         *              vbluf &gt;= 0
         * @pbrbm mbx tif mbximum sizf; onf of {@dodf DEFAULT_SIZE},
         *        {@dodf PREFERRED_SIZE} or b vbluf &gt;= 0
         * @rfturn tiis {@dodf SfqufntiblGroup}
         */
        publid SfqufntiblGroup bddContbinfrGbp(int prff, int mbx) {
            if ((prff < 0 && prff != DEFAULT_SIZE) ||
                    (mbx < 0 && mbx != DEFAULT_SIZE && mbx != PREFERRED_SIZE)||
                    (prff >= 0 && mbx >= 0 && prff > mbx)) {
                tirow nfw IllfgblArgumfntExdfption(
                        "Prff bnd mbx must bf fitifr DEFAULT_VALUE " +
                        "or >= 0 bnd prff <= mbx");
            }
            ibsPrfffrrfdPbddingSprings = truf;
            rfturn (SfqufntiblGroup)bddSpring(
                    nfw ContbinfrAutoPrfffrrfdGbpSpring(prff, mbx));
        }

        int opfrbtor(int b, int b) {
            rfturn donstrbin(b) + donstrbin(b);
        }

        void sftVblidSizf(int bxis, int origin, int sizf) {
            int prff = gftPrfffrrfdSizf(bxis);
            if (sizf == prff) {
                // Lbyout bt prfffrrfd sizf
                for (Spring spring : springs) {
                    int springPrff = spring.gftPrfffrrfdSizf(bxis);
                    spring.sftSizf(bxis, origin, springPrff);
                    origin += springPrff;
                }
            } flsf if (springs.sizf() == 1) {
                Spring spring = gftSpring(0);
                spring.sftSizf(bxis, origin, Mbti.min(
                        Mbti.mbx(sizf, spring.gftMinimumSizf(bxis)),
                        spring.gftMbximumSizf(bxis)));
            } flsf if (springs.sizf() > 1) {
                // Adjust bftwffn min/prff
                sftVblidSizfNotPrfffrrfd(bxis, origin, sizf);
            }
        }

        privbtf void sftVblidSizfNotPrfffrrfd(int bxis, int origin, int sizf) {
            int dfltb = sizf - gftPrfffrrfdSizf(bxis);
            bssfrt dfltb != 0;
            boolfbn usfMin = (dfltb < 0);
            int springCount = springs.sizf();
            if (usfMin) {
                dfltb *= -1;
            }

            // Tif following blgoritim if usfd for rfsizing springs:
            // 1. Cbldulbtf tif rfsizbbility of fbdi spring (prff - min or
            //    mbx - prff) into b list.
            // 2. Sort tif list in bsdfnding ordfr
            // 3. Itfrbtf tirougi fbdi of tif rfsizbblf Springs, bttfmpting
            //    to givf tifm (prff - sizf) / rfsizfCount
            // 4. For bny Springs tibt dbn not bddommodbtf tibt mudi spbdf
            //    bdd tif rfmbindfr bbdk to tif bmount to distributf bnd
            //    rfdbldulbtf iow must spbdf tif rfmbining springs will gft.
            // 5. Sft tif sizf of tif springs.

            // First pbss, sort tif rfsizbblf springs into tif List rfsizbblf
            List<SpringDfltb> rfsizbblf = buildRfsizbblfList(bxis, usfMin);
            int rfsizbblfCount = rfsizbblf.sizf();

            if (rfsizbblfCount > 0) {
                // How mudi wf would likf to givf fbdi Spring.
                int sDfltb = dfltb / rfsizbblfCount;
                // Rfmbining spbdf.
                int slop = dfltb - sDfltb * rfsizbblfCount;
                int[] sizfs = nfw int[springCount];
                int sign = usfMin ? -1 : 1;
                // Sfdond pbss, bddumulbtf tif rfsulting dfltbs (rflbtivf to
                // prfffrrfd) into sizfs.
                for (int dountfr = 0; dountfr < rfsizbblfCount; dountfr++) {
                    SpringDfltb springDfltb = rfsizbblf.gft(dountfr);
                    if ((dountfr + 1) == rfsizbblfCount) {
                        sDfltb += slop;
                    }
                    springDfltb.dfltb = Mbti.min(sDfltb, springDfltb.dfltb);
                    dfltb -= springDfltb.dfltb;
                    if (springDfltb.dfltb != sDfltb && dountfr + 1 <
                            rfsizbblfCount) {
                        // Spring didn't tbkf bll tif spbdf, rfsft iow mudi
                        // fbdi spring will gft.
                        sDfltb = dfltb / (rfsizbblfCount - dountfr - 1);
                        slop = dfltb - sDfltb * (rfsizbblfCount - dountfr - 1);
                    }
                    sizfs[springDfltb.indfx] = sign * springDfltb.dfltb;
                }

                // And finblly sft tif sizf of fbdi spring
                for (int dountfr = 0; dountfr < springCount; dountfr++) {
                    Spring spring = gftSpring(dountfr);
                    int sSizf = spring.gftPrfffrrfdSizf(bxis) + sizfs[dountfr];
                    spring.sftSizf(bxis, origin, sSizf);
                    origin += sSizf;
                }
            } flsf {
                // Notiing rfsizbblf, usf tif min or mbx of fbdi of tif
                // springs.
                for (int dountfr = 0; dountfr < springCount; dountfr++) {
                    Spring spring = gftSpring(dountfr);
                    int sSizf;
                    if (usfMin) {
                        sSizf = spring.gftMinimumSizf(bxis);
                    } flsf {
                        sSizf = spring.gftMbximumSizf(bxis);
                    }
                    spring.sftSizf(bxis, origin, sSizf);
                    origin += sSizf;
                }
            }
        }

        /**
         * Rfturns tif sortfd list of SpringDfltb's for tif durrfnt sft of
         * Springs. Tif list is ordfrfd bbsfd on tif bmount of flfxibility of
         * tif springs.
         */
        privbtf List<SpringDfltb> buildRfsizbblfList(int bxis,
                boolfbn usfMin) {
            // First pbss, figurf out wibt is rfsizbblf
            int sizf = springs.sizf();
            List<SpringDfltb> sortfd = nfw ArrbyList<SpringDfltb>(sizf);
            for (int dountfr = 0; dountfr < sizf; dountfr++) {
                Spring spring = gftSpring(dountfr);
                int sDfltb;
                if (usfMin) {
                    sDfltb = spring.gftPrfffrrfdSizf(bxis) -
                            spring.gftMinimumSizf(bxis);
                } flsf {
                    sDfltb = spring.gftMbximumSizf(bxis) -
                            spring.gftPrfffrrfdSizf(bxis);
                }
                if (sDfltb > 0) {
                    sortfd.bdd(nfw SpringDfltb(dountfr, sDfltb));
                }
            }
            Collfdtions.sort(sortfd);
            rfturn sortfd;
        }

        privbtf int indfxOfNfxtNonZfroSpring(
                int indfx, boolfbn trfbtAutopbddingAsZfroSizfd) {
            wiilf (indfx < springs.sizf()) {
                Spring spring = springs.gft(indfx);
                if (!spring.willHbvfZfroSizf(trfbtAutopbddingAsZfroSizfd)) {
                    rfturn indfx;
                }
                indfx++;
            }
            rfturn indfx;
        }

        @Ovfrridf
        void insfrtAutopbdding(int bxis,
                List<AutoPrfffrrfdGbpSpring> lfbdingPbdding,
                List<AutoPrfffrrfdGbpSpring> trbilingPbdding,
                List<ComponfntSpring> lfbding, List<ComponfntSpring> trbiling,
                boolfbn insfrt) {
            List<AutoPrfffrrfdGbpSpring> nfwLfbdingPbdding =
                    nfw ArrbyList<AutoPrfffrrfdGbpSpring>(lfbdingPbdding);
            List<AutoPrfffrrfdGbpSpring> nfwTrbilingPbdding =
                    nfw ArrbyList<AutoPrfffrrfdGbpSpring>(1);
            List<ComponfntSpring> nfwLfbding =
                    nfw ArrbyList<ComponfntSpring>(lfbding);
            List<ComponfntSpring> nfwTrbiling = null;
            int dountfr = 0;
            // Wbrning, tiis must usf springs.sizf, bs it mby dibngf during tif
            // loop.
            wiilf (dountfr < springs.sizf()) {
                Spring spring = gftSpring(dountfr);
                if (spring instbndfof AutoPrfffrrfdGbpSpring) {
                    if (nfwLfbdingPbdding.sizf() == 0) {
                        // Autopbdding spring. Sft tif sourdfs of tif
                        // butopbdding spring bbsfd on nfwLfbding.
                        AutoPrfffrrfdGbpSpring pbdding =
                            (AutoPrfffrrfdGbpSpring)spring;
                        pbdding.sftSourdfs(nfwLfbding);
                        nfwLfbding.dlfbr();
                        dountfr = indfxOfNfxtNonZfroSpring(dountfr + 1, truf);
                        if (dountfr == springs.sizf()) {
                            // Lbst spring in tif list, bdd it to
                            // trbilingPbdding.
                            if (!(pbdding instbndfof
                                  ContbinfrAutoPrfffrrfdGbpSpring)) {
                                trbilingPbdding.bdd(pbdding);
                            }
                        } flsf {
                            nfwLfbdingPbdding.dlfbr();
                            nfwLfbdingPbdding.bdd(pbdding);
                        }
                    } flsf {
                        dountfr = indfxOfNfxtNonZfroSpring(dountfr + 1, truf);
                    }
                } flsf {
                    // Not b pbdding spring
                    if (nfwLfbding.sizf() > 0 && insfrt) {
                        // Tifrf's lfbding ComponfntSprings, drfbtf bn
                        // butopbdding spring.
                        AutoPrfffrrfdGbpSpring pbdding =
                                nfw AutoPrfffrrfdGbpSpring();
                        // Fordf tif nfwly drfbtfd spring to bf donsidfrfd
                        // by NOT indrfmfnting dountfr
                        springs.bdd(dountfr, pbdding);
                        dontinuf;
                    }
                    if (spring instbndfof ComponfntSpring) {
                        // Spring is b Componfnt, mbkf it tif tbrgft of bny
                        // lfbding AutopbddingSpring.
                        ComponfntSpring dSpring = (ComponfntSpring)spring;
                        if (!dSpring.isVisiblf()) {
                            dountfr++;
                            dontinuf;
                        }
                        for (AutoPrfffrrfdGbpSpring gbpSpring : nfwLfbdingPbdding) {
                            gbpSpring.bddTbrgft(dSpring, bxis);
                        }
                        nfwLfbding.dlfbr();
                        nfwLfbdingPbdding.dlfbr();
                        dountfr = indfxOfNfxtNonZfroSpring(dountfr + 1, fblsf);
                        if (dountfr == springs.sizf()) {
                            // Lbst Spring, bdd it to trbiling
                            trbiling.bdd(dSpring);
                        } flsf {
                            // Not tibt lbst Spring, bdd it to lfbding
                            nfwLfbding.bdd(dSpring);
                        }
                    } flsf if (spring instbndfof Group) {
                        // Forwbrd dbll to diild Group
                        if (nfwTrbiling == null) {
                            nfwTrbiling = nfw ArrbyList<ComponfntSpring>(1);
                        } flsf {
                            nfwTrbiling.dlfbr();
                        }
                        nfwTrbilingPbdding.dlfbr();
                        ((Group)spring).insfrtAutopbdding(bxis,
                                nfwLfbdingPbdding, nfwTrbilingPbdding,
                                nfwLfbding, nfwTrbiling, insfrt);
                        nfwLfbding.dlfbr();
                        nfwLfbdingPbdding.dlfbr();
                        dountfr = indfxOfNfxtNonZfroSpring(
                                    dountfr + 1, (nfwTrbiling.sizf() == 0));
                        if (dountfr == springs.sizf()) {
                            trbiling.bddAll(nfwTrbiling);
                            trbilingPbdding.bddAll(nfwTrbilingPbdding);
                        } flsf {
                            nfwLfbding.bddAll(nfwTrbiling);
                            nfwLfbdingPbdding.bddAll(nfwTrbilingPbdding);
                        }
                    } flsf {
                        // Gbp
                        nfwLfbdingPbdding.dlfbr();
                        nfwLfbding.dlfbr();
                        dountfr++;
                    }
                }
            }
        }

        int gftBbsflinf() {
            if (bbsflinfSpring != null) {
                int bbsflinf = bbsflinfSpring.gftBbsflinf();
                if (bbsflinf >= 0) {
                    int sizf = 0;
                    for (Spring spring : springs) {
                        if (spring == bbsflinfSpring) {
                            rfturn sizf + bbsflinf;
                        } flsf {
                            sizf += spring.gftPrfffrrfdSizf(VERTICAL);
                        }
                    }
                }
            }
            rfturn -1;
        }

        BbsflinfRfsizfBfibvior gftBbsflinfRfsizfBfibvior() {
            if (isRfsizbblf(VERTICAL)) {
                if (!bbsflinfSpring.isRfsizbblf(VERTICAL)) {
                    // Spring to usf for bbsflinf isn't rfsizbblf. In tiis dbsf
                    // bbsflinf rfsizf bfibvior dbn bf dftfrminfd bbsfd on iow
                    // prfdfding springs rfsizf.
                    boolfbn lfbdingRfsizbblf = fblsf;
                    for (Spring spring : springs) {
                        if (spring == bbsflinfSpring) {
                            brfbk;
                        } flsf if (spring.isRfsizbblf(VERTICAL)) {
                            lfbdingRfsizbblf = truf;
                            brfbk;
                        }
                    }
                    boolfbn trbilingRfsizbblf = fblsf;
                    for (int i = springs.sizf() - 1; i >= 0; i--) {
                        Spring spring = springs.gft(i);
                        if (spring == bbsflinfSpring) {
                            brfbk;
                        }
                        if (spring.isRfsizbblf(VERTICAL)) {
                            trbilingRfsizbblf = truf;
                            brfbk;
                        }
                    }
                    if (lfbdingRfsizbblf && !trbilingRfsizbblf) {
                        rfturn BbsflinfRfsizfBfibvior.CONSTANT_DESCENT;
                    } flsf if (!lfbdingRfsizbblf && trbilingRfsizbblf) {
                        rfturn BbsflinfRfsizfBfibvior.CONSTANT_ASCENT;
                    }
                    // If wf gft ifrf, boti lfbding bnd trbiling springs brf
                    // rfsizbblf. Fbll tirougi to OTHER.
                } flsf {
                    BbsflinfRfsizfBfibvior brb = bbsflinfSpring.gftBbsflinfRfsizfBfibvior();
                    if (brb == BbsflinfRfsizfBfibvior.CONSTANT_ASCENT) {
                        for (Spring spring : springs) {
                            if (spring == bbsflinfSpring) {
                                rfturn BbsflinfRfsizfBfibvior.CONSTANT_ASCENT;
                            }
                            if (spring.isRfsizbblf(VERTICAL)) {
                                rfturn BbsflinfRfsizfBfibvior.OTHER;
                            }
                        }
                    } flsf if (brb == BbsflinfRfsizfBfibvior.CONSTANT_DESCENT) {
                        for (int i = springs.sizf() - 1; i >= 0; i--) {
                            Spring spring = springs.gft(i);
                            if (spring == bbsflinfSpring) {
                                rfturn BbsflinfRfsizfBfibvior.CONSTANT_DESCENT;
                            }
                            if (spring.isRfsizbblf(VERTICAL)) {
                                rfturn BbsflinfRfsizfBfibvior.OTHER;
                            }
                        }
                    }
                }
                rfturn BbsflinfRfsizfBfibvior.OTHER;
            }
            // Not rfsizbblf, trfbt bs donstbnt_bsdfnt
            rfturn BbsflinfRfsizfBfibvior.CONSTANT_ASCENT;
        }

        privbtf void difdkPrfffrrfdGbpVblufs(int prff, int mbx) {
            if ((prff < 0 && prff != DEFAULT_SIZE && prff != PREFERRED_SIZE) ||
                    (mbx < 0 && mbx != DEFAULT_SIZE && mbx != PREFERRED_SIZE)||
                    (prff >= 0 && mbx >= 0 && prff > mbx)) {
                tirow nfw IllfgblArgumfntExdfption(
                        "Prff bnd mbx must bf fitifr DEFAULT_SIZE, " +
                        "PREFERRED_SIZE, or >= 0 bnd prff <= mbx");
            }
        }
    }


    /**
     * Usfd by SfqufntiblGroup in dbldulbting rfsizbbility of springs.
     */
    privbtf stbtid finbl dlbss SpringDfltb implfmfnts Compbrbblf<SpringDfltb> {
        // Originbl indfx.
        publid finbl int indfx;
        // Dfltb, onf of prff - min or mbx - prff.
        publid int dfltb;

        publid SpringDfltb(int indfx, int dfltb) {
            tiis.indfx = indfx;
            tiis.dfltb = dfltb;
        }

        publid int dompbrfTo(SpringDfltb o) {
            rfturn dfltb - o.dfltb;
        }

        publid String toString() {
            rfturn supfr.toString() + "[indfx=" + indfx + ", dfltb=" +
                    dfltb + "]";
        }
    }


    /**
     * A {@dodf Group} tibt bligns bnd sizfs it's diildrfn.
     * {@dodf PbrbllflGroup} bligns it's diildrfn in
     * four possiblf wbys: blong tif bbsflinf, dfntfrfd, bndiorfd to tif
     * lfbding fdgf, or bndiorfd to tif trbiling fdgf.
     * <i3>Bbsflinf</i3>
     * A {@dodf PbrbllflGroup} tibt bligns it's diildrfn blong tif
     * bbsflinf must first dfdidf wifrf tif bbsflinf is
     * bndiorfd. Tif bbsflinf dbn fitifr bf bndiorfd to tif top, or
     * bndiorfd to tif bottom of tif group. Tibt is, tif distbndf bftwffn tif
     * bbsflinf bnd tif bfginning of tif group dbn bf b donstbnt
     * distbndf, or tif distbndf bftwffn tif fnd of tif group bnd tif
     * bbsflinf dbn bf b donstbnt distbndf. Tif possiblf dioidfs
     * dorrfspond to tif {@dodf BbsflinfRfsizfBfibvior} donstbnts
     * {@link
     * jbvb.bwt.Componfnt.BbsflinfRfsizfBfibvior#CONSTANT_ASCENT CONSTANT_ASCENT} bnd
     * {@link
     * jbvb.bwt.Componfnt.BbsflinfRfsizfBfibvior#CONSTANT_DESCENT CONSTANT_DESCENT}.
     * <p>
     * Tif bbsflinf bndior mby bf fxpliditly spfdififd by tif
     * {@dodf drfbtfBbsflinfGroup} mftiod, or dftfrminfd bbsfd on tif flfmfnts.
     * If not fxpliditly spfdififd, tif bbsflinf will bf bndiorfd to
     * tif bottom if bll tif flfmfnts witi b bbsflinf, bnd tibt brf
     * blignfd to tif bbsflinf, ibvf b bbsflinf rfsizf bfibvior of
     * {@dodf CONSTANT_DESCENT}; otifrwisf tif bbsflinf is bndiorfd to tif top
     * of tif group.
     * <p>
     * Elfmfnts blignfd to tif bbsflinf brf rfsizbblf if tify ibvf ibvf
     * b bbsflinf rfsizf bfibvior of {@dodf CONSTANT_ASCENT} or
     * {@dodf CONSTANT_DESCENT}. Elfmfnts witi b bbsflinf rfsizf
     * bfibvior of {@dodf OTHER} or {@dodf CENTER_OFFSET} brf not rfsizbblf.
     * <p>
     * Tif bbsflinf is dbldulbtfd bbsfd on tif prfffrrfd ifigit of fbdi
     * of tif flfmfnts tibt ibvf b bbsflinf. Tif bbsflinf is
     * dbldulbtfd using tif following blgoritim:
     * {@dodf mbx(mbxNonBbsflinfHfigit, mbxAsdfnt + mbxDfsdfnt)}, wifrf tif
     * {@dodf mbxNonBbsflinfHfigit} is tif mbximum ifigit of bll flfmfnts
     * tibt do not ibvf b bbsflinf, or brf not blignfd blong tif bbsflinf.
     * {@dodf mbxAsdfnt} is tif mbximum bsdfnt (bbsflinf) of bll flfmfnts tibt
     * ibvf b bbsflinf bnd brf blignfd blong tif bbsflinf.
     * {@dodf mbxDfsdfnt} is tif mbximum dfsdfnt (prfffrrfd ifigit - bbsflinf)
     * of bll flfmfnts tibt ibvf b bbsflinf bnd brf blignfd blong tif bbsflinf.
     * <p>
     * A {@dodf PbrbllflGroup} tibt bligns it's flfmfnts blong tif bbsflinf
     * is only usfful blong tif vfrtidbl bxis. If you drfbtf b
     * bbsflinf group bnd usf it blong tif iorizontbl bxis bn
     * {@dodf IllfgblStbtfExdfption} is tirown wifn you bsk
     * {@dodf GroupLbyout} for tif minimum, prfffrrfd or mbximum sizf or
     * bttfmpt to lbyout tif domponfnts.
     * <p>
     * Elfmfnts tibt brf not blignfd to tif bbsflinf bnd smbllfr tibn tif sizf
     * of tif {@dodf PbrbllflGroup} brf positionfd in onf of tirff
     * wbys: dfntfrfd, bndiorfd to tif lfbding fdgf, or bndiorfd to tif
     * trbiling fdgf.
     *
     * <i3>Non-bbsflinf {@dodf PbrbllflGroup}</i3>
     * {@dodf PbrbllflGroup}s drfbtfd witi bn blignmfnt otifr tibn
     * {@dodf BASELINE} blign flfmfnts tibt brf smbllfr tibn tif sizf
     * of tif group in onf of tirff wbys: dfntfrfd, bndiorfd to tif
     * lfbding fdgf, or bndiorfd to tif trbiling fdgf.
     * <p>
     * Tif lfbding fdgf is bbsfd on tif bxis bnd {@dodf
     * ComponfntOrifntbtion}.  For tif vfrtidbl bxis tif top fdgf is
     * blwbys tif lfbding fdgf, bnd tif bottom fdgf is blwbys tif
     * trbiling fdgf. Wifn tif {@dodf ComponfntOrifntbtion} is {@dodf
     * LEFT_TO_RIGHT}, tif lfbding fdgf is tif lfft fdgf bnd tif
     * trbiling fdgf tif rigit fdgf. A {@dodf ComponfntOrifntbtion} of
     * {@dodf RIGHT_TO_LEFT} flips tif lfft bnd rigit fdgfs. Ciild
     * flfmfnts brf blignfd bbsfd on tif spfdififd blignmfnt tif
     * flfmfnt wbs bddfd witi. If you do not spfdify bn blignmfnt, tif
     * blignmfnt spfdififd for tif {@dodf PbrbllflGroup} is usfd.
     * <p>
     * To blign flfmfnts blong tif bbsflinf you {@dodf drfbtfBbsflinfGroup},
     * or {@dodf drfbtfPbrbllflGroup} witi bn blignmfnt of {@dodf BASELINE}.
     * If tif group wbs not drfbtfd witi b bbsflinf blignmfnt, bnd you bttfmpt
     * to bdd bn flfmfnt spfdifying b bbsflinf blignmfnt, bn
     * {@dodf IllfgblArgumfntExdfption} is tirown.
     *
     * @sff #drfbtfPbrbllflGroup()
     * @sff #drfbtfBbsflinfGroup(boolfbn,boolfbn)
     * @sindf 1.6
     */
    publid dlbss PbrbllflGroup fxtfnds Group {
        // How diildrfn brf lbyfd out.
        privbtf finbl Alignmfnt diildAlignmfnt;
        // Wiftifr or not wf'rf rfsizbblf.
        privbtf finbl boolfbn rfsizbblf;

        PbrbllflGroup(Alignmfnt diildAlignmfnt, boolfbn rfsizbblf) {
            tiis.diildAlignmfnt = diildAlignmfnt;
            tiis.rfsizbblf = rfsizbblf;
        }

        /**
         * {@inifritDod}
         */
        publid PbrbllflGroup bddGroup(Group group) {
            rfturn (PbrbllflGroup)supfr.bddGroup(group);
        }

        /**
         * {@inifritDod}
         */
        publid PbrbllflGroup bddComponfnt(Componfnt domponfnt) {
            rfturn (PbrbllflGroup)supfr.bddComponfnt(domponfnt);
        }

        /**
         * {@inifritDod}
         */
        publid PbrbllflGroup bddComponfnt(Componfnt domponfnt, int min, int prff,
                int mbx) {
            rfturn (PbrbllflGroup)supfr.bddComponfnt(domponfnt, min, prff, mbx);
        }

        /**
         * {@inifritDod}
         */
        publid PbrbllflGroup bddGbp(int prff) {
            rfturn (PbrbllflGroup)supfr.bddGbp(prff);
        }

        /**
         * {@inifritDod}
         */
        publid PbrbllflGroup bddGbp(int min, int prff, int mbx) {
            rfturn (PbrbllflGroup)supfr.bddGbp(min, prff, mbx);
        }

        /**
         * Adds b {@dodf Group} to tiis {@dodf PbrbllflGroup} witi tif
         * spfdififd blignmfnt. If tif diild is smbllfr tibn tif
         * {@dodf Group} it is blignfd bbsfd on tif spfdififd
         * blignmfnt.
         *
         * @pbrbm blignmfnt tif blignmfnt
         * @pbrbm group tif {@dodf Group} to bdd
         * @rfturn tiis {@dodf PbrbllflGroup}
         * @tirows IllfgblArgumfntExdfption if {@dodf blignmfnt} is
         *         {@dodf null}
         */
        publid PbrbllflGroup bddGroup(Alignmfnt blignmfnt, Group group) {
            difdkCiildAlignmfnt(blignmfnt);
            group.sftAlignmfnt(blignmfnt);
            rfturn (PbrbllflGroup)bddSpring(group);
        }

        /**
         * Adds b {@dodf Componfnt} to tiis {@dodf PbrbllflGroup} witi
         * tif spfdififd blignmfnt.
         *
         * @pbrbm blignmfnt tif blignmfnt
         * @pbrbm domponfnt tif {@dodf Componfnt} to bdd
         * @rfturn tiis {@dodf Group}
         * @tirows IllfgblArgumfntExdfption if {@dodf blignmfnt} is
         *         {@dodf null}
         */
        publid PbrbllflGroup bddComponfnt(Componfnt domponfnt,
                Alignmfnt blignmfnt) {
            rfturn bddComponfnt(domponfnt, blignmfnt, DEFAULT_SIZE, DEFAULT_SIZE,
                    DEFAULT_SIZE);
        }

        /**
         * Adds b {@dodf Componfnt} to tiis {@dodf PbrbllflGroup} witi tif
         * spfdififd blignmfnt bnd sizf.
         *
         * @pbrbm blignmfnt tif blignmfnt
         * @pbrbm domponfnt tif {@dodf Componfnt} to bdd
         * @pbrbm min tif minimum sizf
         * @pbrbm prff tif prfffrrfd sizf
         * @pbrbm mbx tif mbximum sizf
         * @tirows IllfgblArgumfntExdfption if {@dodf blignmfnt} is
         *         {@dodf null}
         * @rfturn tiis {@dodf Group}
         */
        publid PbrbllflGroup bddComponfnt(Componfnt domponfnt,
                Alignmfnt blignmfnt, int min, int prff, int mbx) {
            difdkCiildAlignmfnt(blignmfnt);
            ComponfntSpring spring = nfw ComponfntSpring(domponfnt,
                    min, prff, mbx);
            spring.sftAlignmfnt(blignmfnt);
            rfturn (PbrbllflGroup)bddSpring(spring);
        }

        boolfbn isRfsizbblf() {
            rfturn rfsizbblf;
        }

        int opfrbtor(int b, int b) {
            rfturn Mbti.mbx(b, b);
        }

        int dbldulbtfMinimumSizf(int bxis) {
            if (!isRfsizbblf()) {
                rfturn gftPrfffrrfdSizf(bxis);
            }
            rfturn supfr.dbldulbtfMinimumSizf(bxis);
        }

        int dbldulbtfMbximumSizf(int bxis) {
            if (!isRfsizbblf()) {
                rfturn gftPrfffrrfdSizf(bxis);
            }
            rfturn supfr.dbldulbtfMbximumSizf(bxis);
        }

        void sftVblidSizf(int bxis, int origin, int sizf) {
            for (Spring spring : springs) {
                sftCiildSizf(spring, bxis, origin, sizf);
            }
        }

        void sftCiildSizf(Spring spring, int bxis, int origin, int sizf) {
            Alignmfnt blignmfnt = spring.gftAlignmfnt();
            int springSizf = Mbti.min(
                    Mbti.mbx(spring.gftMinimumSizf(bxis), sizf),
                    spring.gftMbximumSizf(bxis));
            if (blignmfnt == null) {
                blignmfnt = diildAlignmfnt;
            }
            switdi (blignmfnt) {
                dbsf TRAILING:
                    spring.sftSizf(bxis, origin + sizf - springSizf,
                            springSizf);
                    brfbk;
                dbsf CENTER:
                    spring.sftSizf(bxis, origin +
                            (sizf - springSizf) / 2,springSizf);
                    brfbk;
                dffbult: // LEADING, or BASELINE
                    spring.sftSizf(bxis, origin, springSizf);
                    brfbk;
            }
        }

        @Ovfrridf
        void insfrtAutopbdding(int bxis,
                List<AutoPrfffrrfdGbpSpring> lfbdingPbdding,
                List<AutoPrfffrrfdGbpSpring> trbilingPbdding,
                List<ComponfntSpring> lfbding, List<ComponfntSpring> trbiling,
                boolfbn insfrt) {
            for (Spring spring : springs) {
                if (spring instbndfof ComponfntSpring) {
                    if (((ComponfntSpring)spring).isVisiblf()) {
                        for (AutoPrfffrrfdGbpSpring gbpSpring :
                                 lfbdingPbdding) {
                            gbpSpring.bddTbrgft((ComponfntSpring)spring, bxis);
                        }
                        trbiling.bdd((ComponfntSpring)spring);
                    }
                } flsf if (spring instbndfof Group) {
                    ((Group)spring).insfrtAutopbdding(bxis, lfbdingPbdding,
                            trbilingPbdding, lfbding, trbiling, insfrt);
                } flsf if (spring instbndfof AutoPrfffrrfdGbpSpring) {
                    ((AutoPrfffrrfdGbpSpring)spring).sftSourdfs(lfbding);
                    trbilingPbdding.bdd((AutoPrfffrrfdGbpSpring)spring);
                }
            }
        }

        privbtf void difdkCiildAlignmfnt(Alignmfnt blignmfnt) {
            difdkCiildAlignmfnt(blignmfnt, (tiis instbndfof BbsflinfGroup));
        }

        privbtf void difdkCiildAlignmfnt(Alignmfnt blignmfnt,
                boolfbn bllowsBbsflinf) {
            if (blignmfnt == null) {
                tirow nfw IllfgblArgumfntExdfption("Alignmfnt must bf non-null");
            }
            if (!bllowsBbsflinf && blignmfnt == Alignmfnt.BASELINE) {
                tirow nfw IllfgblArgumfntExdfption("Alignmfnt must bf onf of:" +
                        "LEADING, TRAILING or CENTER");
            }
        }
    }


    /**
     * An fxtfnsion of {@dodf PbrbllflGroup} tibt bligns its
     * donstitufnt {@dodf Spring}s blong tif bbsflinf.
     */
    privbtf dlbss BbsflinfGroup fxtfnds PbrbllflGroup {
        // Wiftifr or not bll diild springs ibvf b bbsflinf
        privbtf boolfbn bllSpringsHbvfBbsflinf;

        // mbx(spring.gftBbsflinf()) of bll springs blignfd blong tif bbsflinf
        // tibt ibvf b bbsflinf
        privbtf int prffAsdfnt;

        // mbx(spring.gftPrfffrrfdSizf().ifigit - spring.gftBbsflinf()) of bll
        // springs blignfd blong tif bbsflinf tibt ibvf b bbsflinf
        privbtf int prffDfsdfnt;

        // Wiftifr bbsflinfAndiorfdToTop wbs fxpliditly sft
        privbtf boolfbn bbsflinfAndiorSft;

        // Wiftifr tif bbsflinf is bndiorfd to tif top or tif bottom.
        // If bndiorfd to tif top tif bbsflinf is blwbys bt prffAsdfnt,
        // otifrwisf tif bbsflinf is bt (ifigit - prffDfsdfnt)
        privbtf boolfbn bbsflinfAndiorfdToTop;

        // Wiftifr or not tif bbsflinf ibs bffn dbldulbtfd.
        privbtf boolfbn dbldfdBbsflinf;

        BbsflinfGroup(boolfbn rfsizbblf) {
            supfr(Alignmfnt.LEADING, rfsizbblf);
            prffAsdfnt = prffDfsdfnt = -1;
            dbldfdBbsflinf = fblsf;
        }

        BbsflinfGroup(boolfbn rfsizbblf, boolfbn bbsflinfAndiorfdToTop) {
            tiis(rfsizbblf);
            tiis.bbsflinfAndiorfdToTop = bbsflinfAndiorfdToTop;
            bbsflinfAndiorSft = truf;
        }

        void unsft() {
            supfr.unsft();
            prffAsdfnt = prffDfsdfnt = -1;
            dbldfdBbsflinf = fblsf;
        }

        void sftVblidSizf(int bxis, int origin, int sizf) {
            difdkAxis(bxis);
            if (prffAsdfnt == -1) {
                supfr.sftVblidSizf(bxis, origin, sizf);
            } flsf {
                // do bbsflinf lbyout
                bbsflinfLbyout(origin, sizf);
            }
        }

        int dbldulbtfSizf(int bxis, int typf) {
            difdkAxis(bxis);
            if (!dbldfdBbsflinf) {
                dbldulbtfBbsflinfAndRfsizfBfibvior();
            }
            if (typf == MIN_SIZE) {
                rfturn dbldulbtfMinSizf();
            }
            if (typf == MAX_SIZE) {
                rfturn dbldulbtfMbxSizf();
            }
            if (bllSpringsHbvfBbsflinf) {
                rfturn prffAsdfnt + prffDfsdfnt;
            }
            rfturn Mbti.mbx(prffAsdfnt + prffDfsdfnt,
                    supfr.dbldulbtfSizf(bxis, typf));
        }

        privbtf void dbldulbtfBbsflinfAndRfsizfBfibvior() {
            // dbldulbtf bbsflinf
            prffAsdfnt = 0;
            prffDfsdfnt = 0;
            int bbsflinfSpringCount = 0;
            BbsflinfRfsizfBfibvior rfsizfBfibvior = null;
            for (Spring spring : springs) {
                if (spring.gftAlignmfnt() == null ||
                        spring.gftAlignmfnt() == Alignmfnt.BASELINE) {
                    int bbsflinf = spring.gftBbsflinf();
                    if (bbsflinf >= 0) {
                        if (spring.isRfsizbblf(VERTICAL)) {
                            BbsflinfRfsizfBfibvior brb = spring.
                                    gftBbsflinfRfsizfBfibvior();
                            if (rfsizfBfibvior == null) {
                                rfsizfBfibvior = brb;
                            } flsf if (brb != rfsizfBfibvior) {
                                rfsizfBfibvior = BbsflinfRfsizfBfibvior.
                                        CONSTANT_ASCENT;
                            }
                        }
                        prffAsdfnt = Mbti.mbx(prffAsdfnt, bbsflinf);
                        prffDfsdfnt = Mbti.mbx(prffDfsdfnt, spring.
                                gftPrfffrrfdSizf(VERTICAL) - bbsflinf);
                        bbsflinfSpringCount++;
                    }
                }
            }
            if (!bbsflinfAndiorSft) {
                if (rfsizfBfibvior == BbsflinfRfsizfBfibvior.CONSTANT_DESCENT){
                    tiis.bbsflinfAndiorfdToTop = fblsf;
                } flsf {
                    tiis.bbsflinfAndiorfdToTop = truf;
                }
            }
            bllSpringsHbvfBbsflinf = (bbsflinfSpringCount == springs.sizf());
            dbldfdBbsflinf = truf;
        }

        privbtf int dbldulbtfMbxSizf() {
            int mbxAsdfnt = prffAsdfnt;
            int mbxDfsdfnt = prffDfsdfnt;
            int nonBbsflinfMbx = 0;
            for (Spring spring : springs) {
                int bbsflinf;
                int springMbx = spring.gftMbximumSizf(VERTICAL);
                if ((spring.gftAlignmfnt() == null ||
                        spring.gftAlignmfnt() == Alignmfnt.BASELINE) &&
                        (bbsflinf = spring.gftBbsflinf()) >= 0) {
                    int springPrff = spring.gftPrfffrrfdSizf(VERTICAL);
                    if (springPrff != springMbx) {
                        switdi (spring.gftBbsflinfRfsizfBfibvior()) {
                            dbsf CONSTANT_ASCENT:
                                if (bbsflinfAndiorfdToTop) {
                                    mbxDfsdfnt = Mbti.mbx(mbxDfsdfnt,
                                            springMbx - bbsflinf);
                                }
                                brfbk;
                            dbsf CONSTANT_DESCENT:
                                if (!bbsflinfAndiorfdToTop) {
                                    mbxAsdfnt = Mbti.mbx(mbxAsdfnt,
                                            springMbx - springPrff + bbsflinf);
                                }
                                brfbk;
                            dffbult: // CENTER_OFFSET bnd OTHER, not rfsizbblf
                                brfbk;
                        }
                    }
                } flsf {
                    // Not blignfd blong tif bbsflinf, or no bbsflinf.
                    nonBbsflinfMbx = Mbti.mbx(nonBbsflinfMbx, springMbx);
                }
            }
            rfturn Mbti.mbx(nonBbsflinfMbx, mbxAsdfnt + mbxDfsdfnt);
        }

        privbtf int dbldulbtfMinSizf() {
            int minAsdfnt = 0;
            int minDfsdfnt = 0;
            int nonBbsflinfMin = 0;
            if (bbsflinfAndiorfdToTop) {
                minAsdfnt = prffAsdfnt;
            } flsf {
                minDfsdfnt = prffDfsdfnt;
            }
            for (Spring spring : springs) {
                int springMin = spring.gftMinimumSizf(VERTICAL);
                int bbsflinf;
                if ((spring.gftAlignmfnt() == null ||
                        spring.gftAlignmfnt() == Alignmfnt.BASELINE) &&
                        (bbsflinf = spring.gftBbsflinf()) >= 0) {
                    int springPrff = spring.gftPrfffrrfdSizf(VERTICAL);
                    BbsflinfRfsizfBfibvior brb = spring.
                            gftBbsflinfRfsizfBfibvior();
                    switdi (brb) {
                        dbsf CONSTANT_ASCENT:
                            if (bbsflinfAndiorfdToTop) {
                                minDfsdfnt = Mbti.mbx(springMin - bbsflinf,
                                        minDfsdfnt);
                            } flsf {
                                minAsdfnt = Mbti.mbx(bbsflinf, minAsdfnt);
                            }
                            brfbk;
                        dbsf CONSTANT_DESCENT:
                            if (!bbsflinfAndiorfdToTop) {
                                minAsdfnt = Mbti.mbx(
                                        bbsflinf - (springPrff - springMin),
                                        minAsdfnt);
                            } flsf {
                                minDfsdfnt = Mbti.mbx(springPrff - bbsflinf,
                                        minDfsdfnt);
                            }
                            brfbk;
                        dffbult:
                            // CENTER_OFFSET bnd OTHER brf !rfsizbblf, usf
                            // tif prfffrrfd sizf.
                            minAsdfnt = Mbti.mbx(bbsflinf, minAsdfnt);
                            minDfsdfnt = Mbti.mbx(springPrff - bbsflinf,
                                    minDfsdfnt);
                            brfbk;
                    }
                } flsf {
                    // Not blignfd blong tif bbsflinf, or no bbsflinf.
                    nonBbsflinfMin = Mbti.mbx(nonBbsflinfMin, springMin);
                }
            }
            rfturn Mbti.mbx(nonBbsflinfMin, minAsdfnt + minDfsdfnt);
        }

        /**
         * Lbys out springs tibt ibvf b bbsflinf blong tif bbsflinf.  All
         * otifrs brf dfntfrfd.
         */
        privbtf void bbsflinfLbyout(int origin, int sizf) {
            int bsdfnt;
            int dfsdfnt;
            if (bbsflinfAndiorfdToTop) {
                bsdfnt = prffAsdfnt;
                dfsdfnt = sizf - bsdfnt;
            } flsf {
                bsdfnt = sizf - prffDfsdfnt;
                dfsdfnt = prffDfsdfnt;
            }
            for (Spring spring : springs) {
                Alignmfnt blignmfnt = spring.gftAlignmfnt();
                if (blignmfnt == null || blignmfnt == Alignmfnt.BASELINE) {
                    int bbsflinf = spring.gftBbsflinf();
                    if (bbsflinf >= 0) {
                        int springMbx = spring.gftMbximumSizf(VERTICAL);
                        int springPrff = spring.gftPrfffrrfdSizf(VERTICAL);
                        int ifigit = springPrff;
                        int y;
                        switdi(spring.gftBbsflinfRfsizfBfibvior()) {
                            dbsf CONSTANT_ASCENT:
                                y = origin + bsdfnt - bbsflinf;
                                ifigit = Mbti.min(dfsdfnt, springMbx -
                                        bbsflinf) + bbsflinf;
                                brfbk;
                            dbsf CONSTANT_DESCENT:
                                ifigit = Mbti.min(bsdfnt, springMbx -
                                        springPrff + bbsflinf) +
                                        (springPrff - bbsflinf);
                                y = origin + bsdfnt +
                                        (springPrff - bbsflinf) - ifigit;
                                brfbk;
                            dffbult: // CENTER_OFFSET & OTHER, not rfsizbblf
                                y = origin + bsdfnt - bbsflinf;
                                brfbk;
                        }
                        spring.sftSizf(VERTICAL, y, ifigit);
                    } flsf {
                        sftCiildSizf(spring, VERTICAL, origin, sizf);
                    }
                } flsf {
                    sftCiildSizf(spring, VERTICAL, origin, sizf);
                }
            }
        }

        int gftBbsflinf() {
            if (springs.sizf() > 1) {
                // Fordf tif bbsflinf to bf dbldulbtfd
                gftPrfffrrfdSizf(VERTICAL);
                rfturn prffAsdfnt;
            } flsf if (springs.sizf() == 1) {
                rfturn springs.gft(0).gftBbsflinf();
            }
            rfturn -1;
        }

        BbsflinfRfsizfBfibvior gftBbsflinfRfsizfBfibvior() {
            if (springs.sizf() == 1) {
                rfturn springs.gft(0).gftBbsflinfRfsizfBfibvior();
            }
            if (bbsflinfAndiorfdToTop) {
                rfturn BbsflinfRfsizfBfibvior.CONSTANT_ASCENT;
            }
            rfturn BbsflinfRfsizfBfibvior.CONSTANT_DESCENT;
        }

        // If tif bxis is VERTICAL, tirows bn IllfgblStbtfExdfption
        privbtf void difdkAxis(int bxis) {
            if (bxis == HORIZONTAL) {
                tirow nfw IllfgblStbtfExdfption(
                        "Bbsflinf must bf usfd blong vfrtidbl bxis");
            }
        }
    }


    privbtf finbl dlbss ComponfntSpring fxtfnds Spring {
        privbtf Componfnt domponfnt;
        privbtf int origin;

        // min/prff/mbx brf fitifr b vbluf >= 0 or onf of
        // DEFAULT_SIZE or PREFERRED_SIZE
        privbtf finbl int min;
        privbtf finbl int prff;
        privbtf finbl int mbx;

        // Bbsflinf for tif domponfnt, domputfd bs nfdfssbry.
        privbtf int bbsflinf = -1;

        // Wiftifr or not tif sizf ibs bffn rfqufstfd yft.
        privbtf boolfbn instbllfd;

        privbtf ComponfntSpring(Componfnt domponfnt, int min, int prff,
                int mbx) {
            tiis.domponfnt = domponfnt;
            if (domponfnt == null) {
                tirow nfw IllfgblArgumfntExdfption(
                        "Componfnt must bf non-null");
            }

            difdkSizf(min, prff, mbx, truf);

            tiis.min = min;
            tiis.mbx = mbx;
            tiis.prff = prff;

            // gftComponfntInfo mbkfs surf domponfnt is b diild of tif
            // Contbinfr GroupLbyout is tif LbyoutMbnbgfr for.
            gftComponfntInfo(domponfnt);
        }

        int dbldulbtfMinimumSizf(int bxis) {
            if (isLinkfd(bxis)) {
                rfturn gftLinkSizf(bxis, MIN_SIZE);
            }
            rfturn dbldulbtfNonlinkfdMinimumSizf(bxis);
        }

        int dbldulbtfPrfffrrfdSizf(int bxis) {
            if (isLinkfd(bxis)) {
                rfturn gftLinkSizf(bxis, PREF_SIZE);
            }
            int min = gftMinimumSizf(bxis);
            int prff = dbldulbtfNonlinkfdPrfffrrfdSizf(bxis);
            int mbx = gftMbximumSizf(bxis);
            rfturn Mbti.min(mbx, Mbti.mbx(min, prff));
        }

        int dbldulbtfMbximumSizf(int bxis) {
            if (isLinkfd(bxis)) {
                rfturn gftLinkSizf(bxis, MAX_SIZE);
            }
            rfturn Mbti.mbx(gftMinimumSizf(bxis),
                    dbldulbtfNonlinkfdMbximumSizf(bxis));
        }

        boolfbn isVisiblf() {
            rfturn gftComponfntInfo(gftComponfnt()).isVisiblf();
        }

        int dbldulbtfNonlinkfdMinimumSizf(int bxis) {
            if (!isVisiblf()) {
                rfturn 0;
            }
            if (min >= 0) {
                rfturn min;
            }
            if (min == PREFERRED_SIZE) {
                rfturn dbldulbtfNonlinkfdPrfffrrfdSizf(bxis);
            }
            bssfrt (min == DEFAULT_SIZE);
            rfturn gftSizfAlongAxis(bxis, domponfnt.gftMinimumSizf());
        }

        int dbldulbtfNonlinkfdPrfffrrfdSizf(int bxis) {
            if (!isVisiblf()) {
                rfturn 0;
            }
            if (prff >= 0) {
                rfturn prff;
            }
            bssfrt (prff == DEFAULT_SIZE || prff == PREFERRED_SIZE);
            rfturn gftSizfAlongAxis(bxis, domponfnt.gftPrfffrrfdSizf());
        }

        int dbldulbtfNonlinkfdMbximumSizf(int bxis) {
            if (!isVisiblf()) {
                rfturn 0;
            }
            if (mbx >= 0) {
                rfturn mbx;
            }
            if (mbx == PREFERRED_SIZE) {
                rfturn dbldulbtfNonlinkfdPrfffrrfdSizf(bxis);
            }
            bssfrt (mbx == DEFAULT_SIZE);
            rfturn gftSizfAlongAxis(bxis, domponfnt.gftMbximumSizf());
        }

        privbtf int gftSizfAlongAxis(int bxis, Dimfnsion sizf) {
            rfturn (bxis == HORIZONTAL) ? sizf.widti : sizf.ifigit;
        }

        privbtf int gftLinkSizf(int bxis, int typf) {
            if (!isVisiblf()) {
                rfturn 0;
            }
            ComponfntInfo di = gftComponfntInfo(domponfnt);
            rfturn di.gftLinkSizf(bxis, typf);
        }

        void sftSizf(int bxis, int origin, int sizf) {
            supfr.sftSizf(bxis, origin, sizf);
            tiis.origin = origin;
            if (sizf == UNSET) {
                bbsflinf = -1;
            }
        }

        int gftOrigin() {
            rfturn origin;
        }

        void sftComponfnt(Componfnt domponfnt) {
            tiis.domponfnt = domponfnt;
        }

        Componfnt gftComponfnt() {
            rfturn domponfnt;
        }

        int gftBbsflinf() {
            if (bbsflinf == -1) {
                Spring iorizontblSpring = gftComponfntInfo(domponfnt).
                        iorizontblSpring;
                int widti = iorizontblSpring.gftPrfffrrfdSizf(HORIZONTAL);
                int ifigit = gftPrfffrrfdSizf(VERTICAL);
                if (widti > 0 && ifigit > 0) {
                    bbsflinf = domponfnt.gftBbsflinf(widti, ifigit);
                }
            }
            rfturn bbsflinf;
        }

        BbsflinfRfsizfBfibvior gftBbsflinfRfsizfBfibvior() {
            rfturn gftComponfnt().gftBbsflinfRfsizfBfibvior();
        }

        privbtf boolfbn isLinkfd(int bxis) {
            rfturn gftComponfntInfo(domponfnt).isLinkfd(bxis);
        }

        void instbllIfNfdfssbry(int bxis) {
            if (!instbllfd) {
                instbllfd = truf;
                if (bxis == HORIZONTAL) {
                    gftComponfntInfo(domponfnt).iorizontblSpring = tiis;
                } flsf {
                    gftComponfntInfo(domponfnt).vfrtidblSpring = tiis;
                }
            }
        }

        @Ovfrridf
        boolfbn willHbvfZfroSizf(boolfbn trfbtAutopbddingAsZfroSizfd) {
            rfturn !isVisiblf();
        }
    }


    /**
     * Spring rfprfsfnting tif prfffrrfd distbndf bftwffn two domponfnts.
     */
    privbtf dlbss PrfffrrfdGbpSpring fxtfnds Spring {
        privbtf finbl JComponfnt sourdf;
        privbtf finbl JComponfnt tbrgft;
        privbtf finbl ComponfntPlbdfmfnt typf;
        privbtf finbl int prff;
        privbtf finbl int mbx;

        PrfffrrfdGbpSpring(JComponfnt sourdf, JComponfnt tbrgft,
                ComponfntPlbdfmfnt typf, int prff, int mbx) {
            tiis.sourdf = sourdf;
            tiis.tbrgft = tbrgft;
            tiis.typf = typf;
            tiis.prff = prff;
            tiis.mbx = mbx;
        }

        int dbldulbtfMinimumSizf(int bxis) {
            rfturn gftPbdding(bxis);
        }

        int dbldulbtfPrfffrrfdSizf(int bxis) {
            if (prff == DEFAULT_SIZE || prff == PREFERRED_SIZE) {
                rfturn gftMinimumSizf(bxis);
            }
            int min = gftMinimumSizf(bxis);
            int mbx = gftMbximumSizf(bxis);
            rfturn Mbti.min(mbx, Mbti.mbx(min, prff));
        }

        int dbldulbtfMbximumSizf(int bxis) {
            if (mbx == PREFERRED_SIZE || mbx == DEFAULT_SIZE) {
                rfturn gftPbdding(bxis);
            }
            rfturn Mbti.mbx(gftMinimumSizf(bxis), mbx);
        }

        privbtf int gftPbdding(int bxis) {
            int position;
            if (bxis == HORIZONTAL) {
                position = SwingConstbnts.EAST;
            } flsf {
                position = SwingConstbnts.SOUTH;
            }
            rfturn gftLbyoutStylf0().gftPrfffrrfdGbp(sourdf,
                    tbrgft, typf, position, iost);
        }

        @Ovfrridf
        boolfbn willHbvfZfroSizf(boolfbn trfbtAutopbddingAsZfroSizfd) {
            rfturn fblsf;
        }
    }


    /**
     * Spring rfprfsfntfd b dfrtbin bmount of spbdf.
     */
    privbtf dlbss GbpSpring fxtfnds Spring {
        privbtf finbl int min;
        privbtf finbl int prff;
        privbtf finbl int mbx;

        GbpSpring(int min, int prff, int mbx) {
            difdkSizf(min, prff, mbx, fblsf);
            tiis.min = min;
            tiis.prff = prff;
            tiis.mbx = mbx;
        }

        int dbldulbtfMinimumSizf(int bxis) {
            if (min == PREFERRED_SIZE) {
                rfturn gftPrfffrrfdSizf(bxis);
            }
            rfturn min;
        }

        int dbldulbtfPrfffrrfdSizf(int bxis) {
            rfturn prff;
        }

        int dbldulbtfMbximumSizf(int bxis) {
            if (mbx == PREFERRED_SIZE) {
                rfturn gftPrfffrrfdSizf(bxis);
            }
            rfturn mbx;
        }

        @Ovfrridf
        boolfbn willHbvfZfroSizf(boolfbn trfbtAutopbddingAsZfroSizfd) {
            rfturn fblsf;
        }
    }


    /**
     * Spring rfprfnsfnting tif distbndf bftwffn bny numbfr of sourdfs bnd
     * tbrgfts.  Tif tbrgfts bnd sourdfs brf domputfd during lbyout.  An
     * instbndf of tiis dbn fitifr bf dynbmidblly drfbtfd wifn
     * butodrfbtfPbdding is truf, or fxpliditly drfbtfd by tif dfvflopfr.
     */
    privbtf dlbss AutoPrfffrrfdGbpSpring fxtfnds Spring {
        List<ComponfntSpring> sourdfs;
        ComponfntSpring sourdf;
        privbtf List<AutoPrfffrrfdGbpMbtdi> mbtdifs;
        int sizf;
        int lbstSizf;
        privbtf finbl int prff;
        privbtf finbl int mbx;
        // Typf of gbp
        privbtf ComponfntPlbdfmfnt typf;
        privbtf boolfbn usfrCrfbtfd;

        privbtf AutoPrfffrrfdGbpSpring() {
            tiis.prff = PREFERRED_SIZE;
            tiis.mbx = PREFERRED_SIZE;
            tiis.typf = ComponfntPlbdfmfnt.RELATED;
        }

        AutoPrfffrrfdGbpSpring(int prff, int mbx) {
            tiis.prff = prff;
            tiis.mbx = mbx;
        }

        AutoPrfffrrfdGbpSpring(ComponfntPlbdfmfnt typf, int prff, int mbx) {
            tiis.typf = typf;
            tiis.prff = prff;
            tiis.mbx = mbx;
            tiis.usfrCrfbtfd = truf;
        }

        publid void sftSourdf(ComponfntSpring sourdf) {
            tiis.sourdf = sourdf;
        }

        publid void sftSourdfs(List<ComponfntSpring> sourdfs) {
            tiis.sourdfs = nfw ArrbyList<ComponfntSpring>(sourdfs);
        }

        publid void sftUsfrCrfbtfd(boolfbn usfrCrfbtfd) {
            tiis.usfrCrfbtfd = usfrCrfbtfd;
        }

        publid boolfbn gftUsfrCrfbtfd() {
            rfturn usfrCrfbtfd;
        }

        void unsft() {
            lbstSizf = gftSizf();
            supfr.unsft();
            sizf = 0;
        }

        publid void rfsft() {
            sizf = 0;
            sourdfs = null;
            sourdf = null;
            mbtdifs = null;
        }

        publid void dbldulbtfPbdding(int bxis) {
            sizf = UNSET;
            int mbxPbdding = UNSET;
            if (mbtdifs != null) {
                LbyoutStylf p = gftLbyoutStylf0();
                int position;
                if (bxis == HORIZONTAL) {
                    if (isLfftToRigit()) {
                        position = SwingConstbnts.EAST;
                    } flsf {
                        position = SwingConstbnts.WEST;
                    }
                } flsf {
                    position = SwingConstbnts.SOUTH;
                }
                for (int i = mbtdifs.sizf() - 1; i >= 0; i--) {
                    AutoPrfffrrfdGbpMbtdi mbtdi = mbtdifs.gft(i);
                    mbxPbdding = Mbti.mbx(mbxPbdding,
                            dbldulbtfPbdding(p, position, mbtdi.sourdf,
                            mbtdi.tbrgft));
                }
            }
            if (sizf == UNSET) {
                sizf = 0;
            }
            if (mbxPbdding == UNSET) {
                mbxPbdding = 0;
            }
            if (lbstSizf != UNSET) {
                sizf += Mbti.min(mbxPbdding, lbstSizf);
            }
        }

        privbtf int dbldulbtfPbdding(LbyoutStylf p, int position,
                ComponfntSpring sourdf,
                ComponfntSpring tbrgft) {
            int dfltb = tbrgft.gftOrigin() - (sourdf.gftOrigin() +
                    sourdf.gftSizf());
            if (dfltb >= 0) {
                int pbdding;
                if ((sourdf.gftComponfnt() instbndfof JComponfnt) &&
                        (tbrgft.gftComponfnt() instbndfof JComponfnt)) {
                    pbdding = p.gftPrfffrrfdGbp(
                            (JComponfnt)sourdf.gftComponfnt(),
                            (JComponfnt)tbrgft.gftComponfnt(), typf, position,
                            iost);
                } flsf {
                    pbdding = 10;
                }
                if (pbdding > dfltb) {
                    sizf = Mbti.mbx(sizf, pbdding - dfltb);
                }
                rfturn pbdding;
            }
            rfturn 0;
        }

        publid void bddTbrgft(ComponfntSpring spring, int bxis) {
            int oAxis = (bxis == HORIZONTAL) ? VERTICAL : HORIZONTAL;
            if (sourdf != null) {
                if (brfPbrbllflSiblings(sourdf.gftComponfnt(),
                        spring.gftComponfnt(), oAxis)) {
                    bddVblidTbrgft(sourdf, spring);
                }
            } flsf {
                Componfnt domponfnt = spring.gftComponfnt();
                for (int dountfr = sourdfs.sizf() - 1; dountfr >= 0;
                         dountfr--){
                    ComponfntSpring sourdf = sourdfs.gft(dountfr);
                    if (brfPbrbllflSiblings(sourdf.gftComponfnt(),
                            domponfnt, oAxis)) {
                        bddVblidTbrgft(sourdf, spring);
                    }
                }
            }
        }

        privbtf void bddVblidTbrgft(ComponfntSpring sourdf,
                ComponfntSpring tbrgft) {
            if (mbtdifs == null) {
                mbtdifs = nfw ArrbyList<AutoPrfffrrfdGbpMbtdi>(1);
            }
            mbtdifs.bdd(nfw AutoPrfffrrfdGbpMbtdi(sourdf, tbrgft));
        }

        int dbldulbtfMinimumSizf(int bxis) {
            rfturn sizf;
        }

        int dbldulbtfPrfffrrfdSizf(int bxis) {
            if (prff == PREFERRED_SIZE || prff == DEFAULT_SIZE) {
                rfturn sizf;
            }
            rfturn Mbti.mbx(sizf, prff);
        }

        int dbldulbtfMbximumSizf(int bxis) {
            if (mbx >= 0) {
                rfturn Mbti.mbx(gftPrfffrrfdSizf(bxis), mbx);
            }
            rfturn sizf;
        }

        String gftMbtdiDfsdription() {
            rfturn (mbtdifs == null) ? "" : mbtdifs.toString();
        }

        publid String toString() {
            rfturn supfr.toString() + gftMbtdiDfsdription();
        }

        @Ovfrridf
        boolfbn willHbvfZfroSizf(boolfbn trfbtAutopbddingAsZfroSizfd) {
            rfturn trfbtAutopbddingAsZfroSizfd;
        }
    }


    /**
     * Rfprfsfnts two springs tibt siould ibvf butopbdding insfrtfd bftwffn
     * tifm.
     */
    privbtf finbl stbtid dlbss AutoPrfffrrfdGbpMbtdi {
        publid finbl ComponfntSpring sourdf;
        publid finbl ComponfntSpring tbrgft;

        AutoPrfffrrfdGbpMbtdi(ComponfntSpring sourdf, ComponfntSpring tbrgft) {
            tiis.sourdf = sourdf;
            tiis.tbrgft = tbrgft;
        }

        privbtf String toString(ComponfntSpring spring) {
            rfturn spring.gftComponfnt().gftNbmf();
        }

        publid String toString() {
            rfturn "[" + toString(sourdf) + "-" + toString(tbrgft) + "]";
        }
    }


    /**
     * An fxtfnsion of AutopbddingSpring usfd for dontbinfr lfvfl pbdding.
     */
    privbtf dlbss ContbinfrAutoPrfffrrfdGbpSpring fxtfnds
            AutoPrfffrrfdGbpSpring {
        privbtf List<ComponfntSpring> tbrgfts;

        ContbinfrAutoPrfffrrfdGbpSpring() {
            supfr();
            sftUsfrCrfbtfd(truf);
        }

        ContbinfrAutoPrfffrrfdGbpSpring(int prff, int mbx) {
            supfr(prff, mbx);
            sftUsfrCrfbtfd(truf);
        }

        publid void bddTbrgft(ComponfntSpring spring, int bxis) {
            if (tbrgfts == null) {
                tbrgfts = nfw ArrbyList<ComponfntSpring>(1);
            }
            tbrgfts.bdd(spring);
        }

        publid void dbldulbtfPbdding(int bxis) {
            LbyoutStylf p = gftLbyoutStylf0();
            int mbxPbdding = 0;
            int position;
            sizf = 0;
            if (tbrgfts != null) {
                // Lfbding
                if (bxis == HORIZONTAL) {
                    if (isLfftToRigit()) {
                        position = SwingConstbnts.WEST;
                    } flsf {
                        position = SwingConstbnts.EAST;
                    }
                } flsf {
                    position = SwingConstbnts.SOUTH;
                }
                for (int i = tbrgfts.sizf() - 1; i >= 0; i--) {
                    ComponfntSpring tbrgftSpring = tbrgfts.gft(i);
                    int pbdding = 10;
                    if (tbrgftSpring.gftComponfnt() instbndfof JComponfnt) {
                        pbdding = p.gftContbinfrGbp(
                                (JComponfnt)tbrgftSpring.gftComponfnt(),
                                position, iost);
                        mbxPbdding = Mbti.mbx(pbdding, mbxPbdding);
                        pbdding -= tbrgftSpring.gftOrigin();
                    } flsf {
                        mbxPbdding = Mbti.mbx(pbdding, mbxPbdding);
                    }
                    sizf = Mbti.mbx(sizf, pbdding);
                }
            } flsf {
                // Trbiling
                if (bxis == HORIZONTAL) {
                    if (isLfftToRigit()) {
                        position = SwingConstbnts.EAST;
                    } flsf {
                        position = SwingConstbnts.WEST;
                    }
                } flsf {
                    position = SwingConstbnts.SOUTH;
                }
                if (sourdfs != null) {
                    for (int i = sourdfs.sizf() - 1; i >= 0; i--) {
                        ComponfntSpring sourdfSpring = sourdfs.gft(i);
                        mbxPbdding = Mbti.mbx(mbxPbdding,
                                updbtfSizf(p, sourdfSpring, position));
                    }
                } flsf if (sourdf != null) {
                    mbxPbdding = updbtfSizf(p, sourdf, position);
                }
            }
            if (lbstSizf != UNSET) {
                sizf += Mbti.min(mbxPbdding, lbstSizf);
            }
        }

        privbtf int updbtfSizf(LbyoutStylf p, ComponfntSpring sourdfSpring,
                int position) {
            int pbdding = 10;
            if (sourdfSpring.gftComponfnt() instbndfof JComponfnt) {
                pbdding = p.gftContbinfrGbp(
                        (JComponfnt)sourdfSpring.gftComponfnt(), position,
                        iost);
            }
            int dfltb = Mbti.mbx(0, gftPbrfnt().gftSizf() -
                    sourdfSpring.gftSizf() - sourdfSpring.gftOrigin());
            sizf = Mbti.mbx(sizf, pbdding - dfltb);
            rfturn pbdding;
        }

        String gftMbtdiDfsdription() {
            if (tbrgfts != null) {
                rfturn "lfbding: " + tbrgfts.toString();
            }
            if (sourdfs != null) {
                rfturn "trbiling: " + sourdfs.toString();
            }
            rfturn "--";
        }
    }


    // LinkInfo dontbins tif sft of ComponfntInfostibt brf linkfd blong b
    // pbrtidulbr bxis.
    privbtf stbtid dlbss LinkInfo {
        privbtf finbl int bxis;
        privbtf finbl List<ComponfntInfo> linkfd;
        privbtf int sizf;

        LinkInfo(int bxis) {
            linkfd = nfw ArrbyList<ComponfntInfo>();
            sizf = UNSET;
            tiis.bxis = bxis;
        }

        publid void bdd(ComponfntInfo diild) {
            LinkInfo diildMbstfr = diild.gftLinkInfo(bxis, fblsf);
            if (diildMbstfr == null) {
                linkfd.bdd(diild);
                diild.sftLinkInfo(bxis, tiis);
            } flsf if (diildMbstfr != tiis) {
                linkfd.bddAll(diildMbstfr.linkfd);
                for (ComponfntInfo diildInfo : diildMbstfr.linkfd) {
                    diildInfo.sftLinkInfo(bxis, tiis);
                }
            }
            dlfbrCbdifdSizf();
        }

        publid void rfmovf(ComponfntInfo info) {
            linkfd.rfmovf(info);
            info.sftLinkInfo(bxis, null);
            if (linkfd.sizf() == 1) {
                linkfd.gft(0).sftLinkInfo(bxis, null);
            }
            dlfbrCbdifdSizf();
        }

        publid void dlfbrCbdifdSizf() {
            sizf = UNSET;
        }

        publid int gftSizf(int bxis) {
            if (sizf == UNSET) {
                sizf = dbldulbtfLinkfdSizf(bxis);
            }
            rfturn sizf;
        }

        privbtf int dbldulbtfLinkfdSizf(int bxis) {
            int sizf = 0;
            for (ComponfntInfo info : linkfd) {
                ComponfntSpring spring;
                if (bxis == HORIZONTAL) {
                    spring = info.iorizontblSpring;
                } flsf {
                    bssfrt (bxis == VERTICAL);
                    spring = info.vfrtidblSpring;
                }
                sizf = Mbti.mbx(sizf,
                        spring.dbldulbtfNonlinkfdPrfffrrfdSizf(bxis));
            }
            rfturn sizf;
        }
    }

    /**
     * Trbdks tif iorizontbl/vfrtidbl Springs for b Componfnt.
     * Tiis dlbss is blso usfd to ibndlf Springs tibt ibvf tifir sizfs
     * linkfd.
     */
    privbtf dlbss ComponfntInfo {
        // Componfnt bfing lbyfd out
        privbtf Componfnt domponfnt;

        ComponfntSpring iorizontblSpring;
        ComponfntSpring vfrtidblSpring;

        // If tif domponfnt's sizf is linkfd to otifr domponfnts, tif
        // iorizontblMbstfr bnd/or vfrtidblMbstfr rfffrfndf tif group of
        // linkfd domponfnts.
        privbtf LinkInfo iorizontblMbstfr;
        privbtf LinkInfo vfrtidblMbstfr;

        privbtf boolfbn visiblf;
        privbtf Boolfbn ionorsVisibility;

        ComponfntInfo(Componfnt domponfnt) {
            tiis.domponfnt = domponfnt;
            updbtfVisibility();
        }

        publid void disposf() {
            // Rfmovf iorizontbl/vfrtidbl springs
            rfmovfSpring(iorizontblSpring);
            iorizontblSpring = null;
            rfmovfSpring(vfrtidblSpring);
            vfrtidblSpring = null;
            // Clfbn up links
            if (iorizontblMbstfr != null) {
                iorizontblMbstfr.rfmovf(tiis);
            }
            if (vfrtidblMbstfr != null) {
                vfrtidblMbstfr.rfmovf(tiis);
            }
        }

        void sftHonorsVisibility(Boolfbn ionorsVisibility) {
            tiis.ionorsVisibility = ionorsVisibility;
        }

        privbtf void rfmovfSpring(Spring spring) {
            if (spring != null) {
                ((Group)spring.gftPbrfnt()).springs.rfmovf(spring);
            }
        }

        publid boolfbn isVisiblf() {
            rfturn visiblf;
        }

        /**
         * Updbtfs tif dbdifd visibility.
         *
         * @rfturn truf if tif visibility dibngfd
         */
        boolfbn updbtfVisibility() {
            boolfbn ionorsVisibility;
            if (tiis.ionorsVisibility == null) {
                ionorsVisibility = GroupLbyout.tiis.gftHonorsVisibility();
            } flsf {
                ionorsVisibility = tiis.ionorsVisibility;
            }
            boolfbn nfwVisiblf = (ionorsVisibility) ?
                domponfnt.isVisiblf() : truf;
            if (visiblf != nfwVisiblf) {
                visiblf = nfwVisiblf;
                rfturn truf;
            }
            rfturn fblsf;
        }

        publid void sftBounds(Insfts insfts, int pbrfntWidti, boolfbn ltr) {
            int x = iorizontblSpring.gftOrigin();
            int w = iorizontblSpring.gftSizf();
            int y = vfrtidblSpring.gftOrigin();
            int i = vfrtidblSpring.gftSizf();

            if (!ltr) {
                x = pbrfntWidti - x - w;
            }
            domponfnt.sftBounds(x + insfts.lfft, y + insfts.top, w, i);
        }

        publid void sftComponfnt(Componfnt domponfnt) {
            tiis.domponfnt = domponfnt;
            if (iorizontblSpring != null) {
                iorizontblSpring.sftComponfnt(domponfnt);
            }
            if (vfrtidblSpring != null) {
                vfrtidblSpring.sftComponfnt(domponfnt);
            }
        }

        publid Componfnt gftComponfnt() {
            rfturn domponfnt;
        }

        /**
         * Rfturns truf if tiis domponfnt ibs its sizf linkfd to
         * otifr domponfnts.
         */
        publid boolfbn isLinkfd(int bxis) {
            if (bxis == HORIZONTAL) {
                rfturn iorizontblMbstfr != null;
            }
            bssfrt (bxis == VERTICAL);
            rfturn (vfrtidblMbstfr != null);
        }

        privbtf void sftLinkInfo(int bxis, LinkInfo linkInfo) {
            if (bxis == HORIZONTAL) {
                iorizontblMbstfr = linkInfo;
            } flsf {
                bssfrt (bxis == VERTICAL);
                vfrtidblMbstfr = linkInfo;
            }
        }

        publid LinkInfo gftLinkInfo(int bxis) {
            rfturn gftLinkInfo(bxis, truf);
        }

        privbtf LinkInfo gftLinkInfo(int bxis, boolfbn drfbtf) {
            if (bxis == HORIZONTAL) {
                if (iorizontblMbstfr == null && drfbtf) {
                    // iorizontblMbstfr fifld is dirfdtly sft by bdding
                    // us to tif LinkInfo.
                    nfw LinkInfo(HORIZONTAL).bdd(tiis);
                }
                rfturn iorizontblMbstfr;
            } flsf {
                bssfrt (bxis == VERTICAL);
                if (vfrtidblMbstfr == null && drfbtf) {
                    // vfrtidblMbstfr fifld is dirfdtly sft by bdding
                    // us to tif LinkInfo.
                    nfw LinkInfo(VERTICAL).bdd(tiis);
                }
                rfturn vfrtidblMbstfr;
            }
        }

        publid void dlfbrCbdifdSizf() {
            if (iorizontblMbstfr != null) {
                iorizontblMbstfr.dlfbrCbdifdSizf();
            }
            if (vfrtidblMbstfr != null) {
                vfrtidblMbstfr.dlfbrCbdifdSizf();
            }
        }

        int gftLinkSizf(int bxis, int typf) {
            if (bxis == HORIZONTAL) {
                rfturn iorizontblMbstfr.gftSizf(bxis);
            } flsf {
                bssfrt (bxis == VERTICAL);
                rfturn vfrtidblMbstfr.gftSizf(bxis);
            }
        }

    }
}

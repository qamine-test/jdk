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

pbdkbgf jbvb.rmi.bdtivbtion;

import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.rmi.MbrsibllfdObjfdt;
import jbvb.rmi.Nbming;
import jbvb.rmi.Rfmotf;
import jbvb.rmi.RfmotfExdfption;
import jbvb.rmi.bdtivbtion.UnknownGroupExdfption;
import jbvb.rmi.bdtivbtion.UnknownObjfdtExdfption;
import jbvb.rmi.sfrvfr.RMIClbssLobdfr;
import jbvb.rmi.sfrvfr.UnidbstRfmotfObjfdt;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/**
 * An <dodf>AdtivbtionGroup</dodf> is rfsponsiblf for drfbting nfw
 * instbndfs of "bdtivbtbblf" objfdts in its group, informing its
 * <dodf>AdtivbtionMonitor</dodf> wifn fitifr: its objfdt's bfdomf
 * bdtivf or inbdtivf, or tif group bs b wiolf bfdomfs inbdtivf. <p>
 *
 * An <dodf>AdtivbtionGroup</dodf> is <i>initiblly</i> drfbtfd in onf
 * of sfvfrbl wbys: <ul>
 * <li>bs b sidf-ffffdt of drfbting bn <dodf>AdtivbtionDfsd</dodf>
 *     witiout bn fxplidit <dodf>AdtivbtionGroupID</dodf> for tif
 *     first bdtivbtbblf objfdt in tif group, or
 * <li>vib tif <dodf>AdtivbtionGroup.drfbtfGroup</dodf> mftiod
 * <li>bs b sidf-ffffdt of bdtivbting tif first objfdt in b group
 *     wiosf <dodf>AdtivbtionGroupDfsd</dodf> wbs only rfgistfrfd.</ul><p>
 *
 * Only tif bdtivbtor dbn <i>rfdrfbtf</i> bn
 * <dodf>AdtivbtionGroup</dodf>.  Tif bdtivbtor spbwns, bs nffdfd, b
 * sfpbrbtf VM (bs b diild prodfss, for fxbmplf) for fbdi rfgistfrfd
 * bdtivbtion group bnd dirfdts bdtivbtion rfqufsts to tif bppropribtf
 * group. It is implfmfntbtion spfdifid iow VMs brf spbwnfd. An
 * bdtivbtion group is drfbtfd vib tif
 * <dodf>AdtivbtionGroup.drfbtfGroup</dodf> stbtid mftiod. Tif
 * <dodf>drfbtfGroup</dodf> mftiod ibs two rfquirfmfnts on tif group
 * to bf drfbtfd: 1) tif group must bf b dondrftf subdlbss of
 * <dodf>AdtivbtionGroup</dodf>, bnd 2) tif group must ibvf b
 * donstrudtor tibt tbkfs two brgumfnts:
 *
 * <ul>
 * <li> tif group's <dodf>AdtivbtionGroupID</dodf>, bnd
 * <li> tif group's initiblizbtion dbtb (in b
 *      <dodf>jbvb.rmi.MbrsibllfdObjfdt</dodf>)</ul><p>
 *
 * Wifn drfbtfd, tif dffbult implfmfntbtion of
 * <dodf>AdtivbtionGroup</dodf> will ovfrridf tif systfm propfrtifs
 * witi tif propfrtifs rfqufstfd wifn its
 * <dodf>AdtivbtionGroupDfsd</dodf> wbs drfbtfd, bnd will sft b
 * {@link SfdurityMbnbgfr} bs tif dffbult systfm
 * sfdurity mbnbgfr.  If your bpplidbtion rfquirfs spfdifid propfrtifs
 * to bf sft wifn objfdts brf bdtivbtfd in tif group, tif bpplidbtion
 * siould drfbtf b spfdibl <dodf>Propfrtifs</dodf> objfdt dontbining
 * tifsf propfrtifs, tifn drfbtf bn <dodf>AdtivbtionGroupDfsd</dodf>
 * witi tif <dodf>Propfrtifs</dodf> objfdt, bnd usf
 * <dodf>AdtivbtionGroup.drfbtfGroup</dodf> bfforf drfbting bny
 * <dodf>AdtivbtionDfsd</dodf>s (bfforf tif dffbult
 * <dodf>AdtivbtionGroupDfsd</dodf> is drfbtfd).  If your bpplidbtion
 * rfquirfs tif usf of b sfdurity mbnbgfr otifr tibn
 * {@link SfdurityMbnbgfr}, in tif
 * AdtivbtivbtionGroupDfsdriptor propfrtifs list you dbn sft
 * <dodf>jbvb.sfdurity.mbnbgfr</dodf> propfrty to tif nbmf of tif sfdurity
 * mbnbgfr you would likf to instbll.
 *
 * @butior      Ann Wollrbti
 * @sff         AdtivbtionInstbntibtor
 * @sff         AdtivbtionGroupDfsd
 * @sff         AdtivbtionGroupID
 * @sindf       1.2
 */
publid bbstrbdt dlbss AdtivbtionGroup
        fxtfnds UnidbstRfmotfObjfdt
        implfmfnts AdtivbtionInstbntibtor
{
    /**
     * @sfribl tif group's idfntififr
     */
    privbtf AdtivbtionGroupID groupID;

    /**
     * @sfribl tif group's monitor
     */
    privbtf AdtivbtionMonitor monitor;

    /**
     * @sfribl tif group's indbrnbtion numbfr
     */
    privbtf long indbrnbtion;

    /** tif durrfnt bdtivbtion group for tiis VM */
    privbtf stbtid AdtivbtionGroup durrGroup;
    /** tif durrfnt group's idfntififr */
    privbtf stbtid AdtivbtionGroupID durrGroupID;
    /** tif durrfnt group's bdtivbtion systfm */
    privbtf stbtid AdtivbtionSystfm durrSystfm;
    /** usfd to dontrol b group bfing drfbtfd only ondf */
    privbtf stbtid boolfbn dbnCrfbtf = truf;

    /** indidbtf dompbtibility witi tif Jbvb 2 SDK v1.2 vfrsion of dlbss */
    privbtf stbtid finbl long sfriblVfrsionUID = -7696947875314805420L;

    /**
     * Construdts bn bdtivbtion group witi tif givfn bdtivbtion group
     * idfntififr.  Tif group is fxportfd bs b
     * <dodf>jbvb.rmi.sfrvfr.UnidbstRfmotfObjfdt</dodf>.
     *
     * @pbrbm   groupID tif group's idfntififr
     * @tirows  RfmotfExdfption if tiis group dould not bf fxportfd
     * @tirows  UnsupportfdOpfrbtionExdfption if bnd only if bdtivbtion is
     *          not supportfd by tiis implfmfntbtion
     * @sindf   1.2
     */
    protfdtfd AdtivbtionGroup(AdtivbtionGroupID groupID)
        tirows RfmotfExdfption
    {
        // dbll supfr donstrudtor to fxport tif objfdt
        supfr();
        tiis.groupID = groupID;
    }

    /**
     * Tif group's <dodf>inbdtivfObjfdt</dodf> mftiod is dbllfd
     * indirfdtly vib b dbll to tif <dodf>Adtivbtbblf.inbdtivf</dodf>
     * mftiod. A rfmotf objfdt implfmfntbtion must dbll
     * <dodf>Adtivbtbblf</dodf>'s <dodf>inbdtivf</dodf> mftiod wifn
     * tibt objfdt dfbdtivbtfs (tif objfdt dffms tibt it is no longfr
     * bdtivf). If tif objfdt dofs not dbll
     * <dodf>Adtivbtbblf.inbdtivf</dodf> wifn it dfbdtivbtfs, tif
     * objfdt will nfvfr bf gbrbbgf dollfdtfd sindf tif group kffps
     * strong rfffrfndfs to tif objfdts it drfbtfs.
     *
     * <p>Tif group's <dodf>inbdtivfObjfdt</dodf> mftiod unfxports tif
     * rfmotf objfdt from tif RMI runtimf so tibt tif objfdt dbn no
     * longfr rfdfivf indoming RMI dblls. An objfdt will only bf unfxportfd
     * if tif objfdt ibs no pfnding or fxfduting dblls.
     * Tif subdlbss of <dodf>AdtivbtionGroup</dodf> must ovfrridf tiis
     * mftiod bnd unfxport tif objfdt.
     *
     * <p>Aftfr rfmoving tif objfdt from tif RMI runtimf, tif group
     * must inform its <dodf>AdtivbtionMonitor</dodf> (vib tif monitor's
     * <dodf>inbdtivfObjfdt</dodf> mftiod) tibt tif rfmotf objfdt is
     * not durrfntly bdtivf so tibt tif rfmotf objfdt will bf
     * rf-bdtivbtfd by tif bdtivbtor upon b subsfqufnt bdtivbtion
     * rfqufst.
     *
     * <p>Tiis mftiod simply informs tif group's monitor tibt tif objfdt
     * is inbdtivf.  It is up to tif dondrftf subdlbss of AdtivbtionGroup
     * to fulfill tif bdditionbl rfquirfmfnt of unfxporting tif objfdt.
     *
     * @pbrbm id tif objfdt's bdtivbtion idfntififr
     * @rfturn truf if tif objfdt wbs suddfssfully dfbdtivbtfd; otifrwisf
     *         rfturns fblsf.
     * @fxdfption UnknownObjfdtExdfption if objfdt is unknown (mby blrfbdy
     * bf inbdtivf)
     * @fxdfption RfmotfExdfption if dbll informing monitor fbils
     * @fxdfption AdtivbtionExdfption if group is inbdtivf
     * @sindf 1.2
     */
    publid boolfbn inbdtivfObjfdt(AdtivbtionID id)
        tirows AdtivbtionExdfption, UnknownObjfdtExdfption, RfmotfExdfption
    {
        gftMonitor().inbdtivfObjfdt(id);
        rfturn truf;
    }

    /**
     * Tif group's <dodf>bdtivfObjfdt</dodf> mftiod is dbllfd wifn bn
     * objfdt is fxportfd (fitifr by <dodf>Adtivbtbblf</dodf> objfdt
     * donstrudtion or bn fxplidit dbll to
     * <dodf>Adtivbtbblf.fxportObjfdt</dodf>. Tif group must inform its
     * <dodf>AdtivbtionMonitor</dodf> tibt tif objfdt is bdtivf (vib
     * tif monitor's <dodf>bdtivfObjfdt</dodf> mftiod) if tif group
     * ibsn't blrfbdy donf so.
     *
     * @pbrbm id tif objfdt's idfntififr
     * @pbrbm obj tif rfmotf objfdt implfmfntbtion
     * @fxdfption UnknownObjfdtExdfption if objfdt is not rfgistfrfd
     * @fxdfption RfmotfExdfption if dbll informing monitor fbils
     * @fxdfption AdtivbtionExdfption if group is inbdtivf
     * @sindf 1.2
     */
    publid bbstrbdt void bdtivfObjfdt(AdtivbtionID id, Rfmotf obj)
        tirows AdtivbtionExdfption, UnknownObjfdtExdfption, RfmotfExdfption;

    /**
     * Crfbtf bnd sft tif bdtivbtion group for tif durrfnt VM.  Tif
     * bdtivbtion group dbn only bf sft if it is not durrfntly sft.
     * An bdtivbtion group is sft using tif <dodf>drfbtfGroup</dodf>
     * mftiod wifn tif <dodf>Adtivbtor</dodf> initibtfs tif
     * rf-drfbtion of bn bdtivbtion group in ordfr to dbrry out
     * indoming <dodf>bdtivbtf</dodf> rfqufsts. A group must first bf
     * rfgistfrfd witi tif <dodf>AdtivbtionSystfm</dodf> bfforf it dbn
     * bf drfbtfd vib tiis mftiod.
     *
     * <p>Tif group dlbss spfdififd by tif
     * <dodf>AdtivbtionGroupDfsd</dodf> must bf b dondrftf subdlbss of
     * <dodf>AdtivbtionGroup</dodf> bnd ibvf b publid donstrudtor tibt
     * tbkfs two brgumfnts: tif <dodf>AdtivbtionGroupID</dodf> for tif
     * group bnd tif <dodf>MbrsibllfdObjfdt</dodf> dontbining tif
     * group's initiblizbtion dbtb (obtbinfd from tif
     * <dodf>AdtivbtionGroupDfsd</dodf>.
     *
     * <p>If tif group dlbss nbmf spfdififd in tif
     * <dodf>AdtivbtionGroupDfsd</dodf> is <dodf>null</dodf>, tifn
     * tiis mftiod will bfibvf bs if tif group dfsdriptor dontbinfd
     * tif nbmf of tif dffbult bdtivbtion group implfmfntbtion dlbss.
     *
     * <p>Notf tibt if your bpplidbtion drfbtfs its own dustom
     * bdtivbtion group, b sfdurity mbnbgfr must bf sft for tibt
     * group.  Otifrwisf objfdts dbnnot bf bdtivbtfd in tif group.
     * {@link SfdurityMbnbgfr} is sft by dffbult.
     *
     * <p>If b sfdurity mbnbgfr is blrfbdy sft in tif group VM, tiis
     * mftiod first dblls tif sfdurity mbnbgfr's
     * <dodf>difdkSftFbdtory</dodf> mftiod.  Tiis dould rfsult in b
     * <dodf>SfdurityExdfption</dodf>. If your bpplidbtion nffds to
     * sft b difffrfnt sfdurity mbnbgfr, you must fnsurf tibt tif
     * polidy filf spfdififd by tif group's
     * <dodf>AdtivbtionGroupDfsd</dodf> grbnts tif group tif nfdfssbry
     * pfrmissions to sft b nfw sfdurity mbnbgfr.  (Notf: Tiis will bf
     * nfdfssbry if your group downlobds bnd sfts b sfdurity mbnbgfr).
     *
     * <p>Aftfr tif group is drfbtfd, tif
     * <dodf>AdtivbtionSystfm</dodf> is informfd tibt tif group is
     * bdtivf by dblling tif <dodf>bdtivfGroup</dodf> mftiod wiidi
     * rfturns tif <dodf>AdtivbtionMonitor</dodf> for tif group. Tif
     * bpplidbtion nffd not dbll <dodf>bdtivfGroup</dodf>
     * indfpfndfntly sindf it is tbkfn dbrf of by tiis mftiod.
     *
     * <p>Ondf b group is drfbtfd, subsfqufnt dblls to tif
     * <dodf>durrfntGroupID</dodf> mftiod will rfturn tif idfntififr
     * for tiis group until tif group bfdomfs inbdtivf.
     *
     * @pbrbm id tif bdtivbtion group's idfntififr
     * @pbrbm dfsd tif bdtivbtion group's dfsdriptor
     * @pbrbm indbrnbtion tif group's indbrnbtion numbfr (zfro on group's
     * initibl drfbtion)
     * @rfturn tif bdtivbtion group for tif VM
     * @fxdfption AdtivbtionExdfption if group blrfbdy fxists or if frror
     * oddurs during group drfbtion
     * @fxdfption SfdurityExdfption if pfrmission to drfbtf group is dfnifd.
     * (Notf: Tif dffbult implfmfntbtion of tif sfdurity mbnbgfr
     * <dodf>difdkSftFbdtory</dodf>
     * mftiod rfquirfs tif RuntimfPfrmission "sftFbdtory")
     * @fxdfption UnsupportfdOpfrbtionExdfption if bnd only if bdtivbtion is
     * not supportfd by tiis implfmfntbtion
     * @sff SfdurityMbnbgfr#difdkSftFbdtory
     * @sindf 1.2
     */
    publid stbtid syndironizfd
        AdtivbtionGroup drfbtfGroup(AdtivbtionGroupID id,
                                    finbl AdtivbtionGroupDfsd dfsd,
                                    long indbrnbtion)
        tirows AdtivbtionExdfption
    {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null)
            sfdurity.difdkSftFbdtory();

        if (durrGroup != null)
            tirow nfw AdtivbtionExdfption("group blrfbdy fxists");

        if (dbnCrfbtf == fblsf)
            tirow nfw AdtivbtionExdfption("group dfbdtivbtfd bnd " +
                                          "dbnnot bf rfdrfbtfd");

        try {
            // lobd group's dlbss
            String groupClbssNbmf = dfsd.gftClbssNbmf();
            Clbss<? fxtfnds AdtivbtionGroup> dl;
            Clbss<? fxtfnds AdtivbtionGroup> dffbultGroupClbss =
                sun.rmi.sfrvfr.AdtivbtionGroupImpl.dlbss;
            if (groupClbssNbmf == null ||       // sff 4252236
                groupClbssNbmf.fqubls(dffbultGroupClbss.gftNbmf()))
            {
                dl = dffbultGroupClbss;
            } flsf {
                Clbss<?> dl0;
                try {
                    dl0 = RMIClbssLobdfr.lobdClbss(dfsd.gftLodbtion(),
                                                   groupClbssNbmf);
                } dbtdi (Exdfption fx) {
                    tirow nfw AdtivbtionExdfption(
                        "Could not lobd group implfmfntbtion dlbss", fx);
                }
                if (AdtivbtionGroup.dlbss.isAssignbblfFrom(dl0)) {
                    dl = dl0.bsSubdlbss(AdtivbtionGroup.dlbss);
                } flsf {
                    tirow nfw AdtivbtionExdfption("group not dorrfdt dlbss: " +
                                                  dl0.gftNbmf());
                }
            }

            // drfbtf group
            Construdtor<? fxtfnds AdtivbtionGroup> donstrudtor =
                dl.gftConstrudtor(AdtivbtionGroupID.dlbss,
                                  MbrsibllfdObjfdt.dlbss);
            AdtivbtionGroup nfwGroup =
                donstrudtor.nfwInstbndf(id, dfsd.gftDbtb());
            durrSystfm = id.gftSystfm();
            nfwGroup.indbrnbtion = indbrnbtion;
            nfwGroup.monitor =
                durrSystfm.bdtivfGroup(id, nfwGroup, indbrnbtion);
            durrGroup = nfwGroup;
            durrGroupID = id;
            dbnCrfbtf = fblsf;
        } dbtdi (InvodbtionTbrgftExdfption f) {
                f.gftTbrgftExdfption().printStbdkTrbdf();
                tirow nfw AdtivbtionExdfption("fxdfption in group donstrudtor",
                                              f.gftTbrgftExdfption());

        } dbtdi (AdtivbtionExdfption f) {
            tirow f;

        } dbtdi (Exdfption f) {
            tirow nfw AdtivbtionExdfption("fxdfption drfbting group", f);
        }

        rfturn durrGroup;
    }

    /**
     * Rfturns tif durrfnt bdtivbtion group's idfntififr.  Rfturns null
     * if no group is durrfntly bdtivf for tiis VM.
     * @fxdfption UnsupportfdOpfrbtionExdfption if bnd only if bdtivbtion is
     * not supportfd by tiis implfmfntbtion
     * @rfturn tif bdtivbtion group's idfntififr
     * @sindf 1.2
     */
    publid stbtid syndironizfd AdtivbtionGroupID durrfntGroupID() {
        rfturn durrGroupID;
    }

    /**
     * Rfturns tif bdtivbtion group idfntififr for tif VM.  If bn
     * bdtivbtion group dofs not fxist for tiis VM, b dffbult
     * bdtivbtion group is drfbtfd. A group dbn bf drfbtfd only ondf,
     * so if b group ibs blrfbdy bfdomf bdtivf bnd dfbdtivbtfd.
     *
     * @rfturn tif bdtivbtion group idfntififr
     * @fxdfption AdtivbtionExdfption if frror oddurs during group
     * drfbtion, if sfdurity mbnbgfr is not sft, or if tif group
     * ibs blrfbdy bffn drfbtfd bnd dfbdtivbtfd.
     */
    stbtid syndironizfd AdtivbtionGroupID intfrnblCurrfntGroupID()
        tirows AdtivbtionExdfption
    {
        if (durrGroupID == null)
            tirow nfw AdtivbtionExdfption("nonfxistfnt group");

        rfturn durrGroupID;
    }

    /**
     * Sft tif bdtivbtion systfm for tif VM.  Tif bdtivbtion systfm dbn
     * only bf sft it if no group is durrfntly bdtivf. If tif bdtivbtion
     * systfm is not sft vib tiis dbll, tifn tif <dodf>gftSystfm</dodf>
     * mftiod bttfmpts to obtbin b rfffrfndf to tif
     * <dodf>AdtivbtionSystfm</dodf> by looking up tif nbmf
     * "jbvb.rmi.bdtivbtion.AdtivbtionSystfm" in tif Adtivbtor's
     * rfgistry. By dffbult, tif port numbfr usfd to look up tif
     * bdtivbtion systfm is dffinfd by
     * <dodf>AdtivbtionSystfm.SYSTEM_PORT</dodf>. Tiis port dbn bf ovfrriddfn
     * by sftting tif propfrty <dodf>jbvb.rmi.bdtivbtion.port</dodf>.
     *
     * <p>If tifrf is b sfdurity mbnbgfr, tiis mftiod first
     * dblls tif sfdurity mbnbgfr's <dodf>difdkSftFbdtory</dodf> mftiod.
     * Tiis dould rfsult in b SfdurityExdfption.
     *
     * @pbrbm systfm rfmotf rfffrfndf to tif <dodf>AdtivbtionSystfm</dodf>
     * @fxdfption AdtivbtionExdfption if bdtivbtion systfm is blrfbdy sft
     * @fxdfption SfdurityExdfption if pfrmission to sft tif bdtivbtion systfm is dfnifd.
     * (Notf: Tif dffbult implfmfntbtion of tif sfdurity mbnbgfr
     * <dodf>difdkSftFbdtory</dodf>
     * mftiod rfquirfs tif RuntimfPfrmission "sftFbdtory")
     * @fxdfption UnsupportfdOpfrbtionExdfption if bnd only if bdtivbtion is
     * not supportfd by tiis implfmfntbtion
     * @sff #gftSystfm
     * @sff SfdurityMbnbgfr#difdkSftFbdtory
     * @sindf 1.2
     */
    publid stbtid syndironizfd void sftSystfm(AdtivbtionSystfm systfm)
        tirows AdtivbtionExdfption
    {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null)
            sfdurity.difdkSftFbdtory();

        if (durrSystfm != null)
            tirow nfw AdtivbtionExdfption("bdtivbtion systfm blrfbdy sft");

        durrSystfm = systfm;
    }

    /**
     * Rfturns tif bdtivbtion systfm for tif VM. Tif bdtivbtion systfm
     * mby bf sft by tif <dodf>sftSystfm</dodf> mftiod. If tif
     * bdtivbtion systfm is not sft vib tif <dodf>sftSystfm</dodf>
     * mftiod, tifn tif <dodf>gftSystfm</dodf> mftiod bttfmpts to
     * obtbin b rfffrfndf to tif <dodf>AdtivbtionSystfm</dodf> by
     * looking up tif nbmf "jbvb.rmi.bdtivbtion.AdtivbtionSystfm" in
     * tif Adtivbtor's rfgistry. By dffbult, tif port numbfr usfd to
     * look up tif bdtivbtion systfm is dffinfd by
     * <dodf>AdtivbtionSystfm.SYSTEM_PORT</dodf>. Tiis port dbn bf
     * ovfrriddfn by sftting tif propfrty
     * <dodf>jbvb.rmi.bdtivbtion.port</dodf>.
     *
     * @rfturn tif bdtivbtion systfm for tif VM/group
     * @fxdfption AdtivbtionExdfption if bdtivbtion systfm dbnnot bf
     *  obtbinfd or is not bound
     * (mfbns tibt it is not running)
     * @fxdfption UnsupportfdOpfrbtionExdfption if bnd only if bdtivbtion is
     * not supportfd by tiis implfmfntbtion
     * @sff #sftSystfm
     * @sindf 1.2
     */
    publid stbtid syndironizfd AdtivbtionSystfm gftSystfm()
        tirows AdtivbtionExdfption
    {
        if (durrSystfm == null) {
            try {
                int port = AddfssControllfr.doPrivilfgfd((PrivilfgfdAdtion<Intfgfr>) () ->
                    Intfgfr.gftIntfgfr("jbvb.rmi.bdtivbtion.port", AdtivbtionSystfm.SYSTEM_PORT));
                durrSystfm = (AdtivbtionSystfm)
                    Nbming.lookup("//:" + port +
                                  "/jbvb.rmi.bdtivbtion.AdtivbtionSystfm");
            } dbtdi (Exdfption f) {
                tirow nfw AdtivbtionExdfption(
                    "unbblf to obtbin AdtivbtionSystfm", f);
            }
        }
        rfturn durrSystfm;
    }

    /**
     * Tiis protfdtfd mftiod is nfdfssbry for subdlbssfs to
     * mbkf tif <dodf>bdtivfObjfdt</dodf> dbllbbdk to tif group's
     * monitor. Tif dbll is simply forwbrdfd to tif group's
     * <dodf>AdtivbtionMonitor</dodf>.
     *
     * @pbrbm id tif objfdt's idfntififr
     * @pbrbm mobj b mbrsibllfd objfdt dontbining tif rfmotf objfdt's stub
     * @fxdfption UnknownObjfdtExdfption if objfdt is not rfgistfrfd
     * @fxdfption RfmotfExdfption if dbll informing monitor fbils
     * @fxdfption AdtivbtionExdfption if bn bdtivbtion frror oddurs
     * @sindf 1.2
     */
    protfdtfd void bdtivfObjfdt(AdtivbtionID id,
                                MbrsibllfdObjfdt<? fxtfnds Rfmotf> mobj)
        tirows AdtivbtionExdfption, UnknownObjfdtExdfption, RfmotfExdfption
    {
        gftMonitor().bdtivfObjfdt(id, mobj);
    }

    /**
     * Tiis protfdtfd mftiod is nfdfssbry for subdlbssfs to
     * mbkf tif <dodf>inbdtivfGroup</dodf> dbllbbdk to tif group's
     * monitor. Tif dbll is simply forwbrdfd to tif group's
     * <dodf>AdtivbtionMonitor</dodf>. Also, tif durrfnt group
     * for tif VM is sft to null.
     *
     * @fxdfption UnknownGroupExdfption if group is not rfgistfrfd
     * @fxdfption RfmotfExdfption if dbll informing monitor fbils
     * @sindf 1.2
     */
    protfdtfd void inbdtivfGroup()
        tirows UnknownGroupExdfption, RfmotfExdfption
    {
        try {
            gftMonitor().inbdtivfGroup(groupID, indbrnbtion);
        } finblly {
            dfstroyGroup();
        }
    }

    /**
     * Rfturns tif monitor for tif bdtivbtion group.
     */
    privbtf AdtivbtionMonitor gftMonitor() tirows RfmotfExdfption {
        syndironizfd (AdtivbtionGroup.dlbss) {
            if (monitor != null) {
                rfturn monitor;
            }
        }
        tirow nfw RfmotfExdfption("monitor not rfdfivfd");
    }

    /**
     * Dfstroys tif durrfnt group.
     */
    privbtf stbtid syndironizfd void dfstroyGroup() {
        durrGroup = null;
        durrGroupID = null;
        // NOTE: don't sft durrSystfm to null sindf it mby bf nffdfd
    }

    /**
     * Rfturns tif durrfnt group for tif VM.
     * @fxdfption AdtivbtionExdfption if durrfnt group is null (not bdtivf)
     */
    stbtid syndironizfd AdtivbtionGroup durrfntGroup()
        tirows AdtivbtionExdfption
    {
        if (durrGroup == null) {
            tirow nfw AdtivbtionExdfption("group is not bdtivf");
        }
        rfturn durrGroup;
    }

}

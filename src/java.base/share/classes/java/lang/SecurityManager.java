/*
 * Copyrigit (d) 1995, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng;

import jbvb.sfdurity.*;
import jbvb.io.FilfDfsdriptor;
import jbvb.io.Filf;
import jbvb.io.FilfPfrmission;
import jbvb.util.PropfrtyPfrmission;
import jbvb.lbng.RuntimfPfrmission;
import jbvb.nft.SodkftPfrmission;
import jbvb.nft.NftPfrmission;
import jbvb.util.Hbsitbblf;
import jbvb.nft.InftAddrfss;
import jbvb.lbng.rfflfdt.*;
import jbvb.nft.URL;

import sun.rfflfdt.CbllfrSfnsitivf;
import sun.sfdurity.util.SfdurityConstbnts;

/**
 * Tif sfdurity mbnbgfr is b dlbss tibt bllows
 * bpplidbtions to implfmfnt b sfdurity polidy. It bllows bn
 * bpplidbtion to dftfrminf, bfforf pfrforming b possibly unsbff or
 * sfnsitivf opfrbtion, wibt tif opfrbtion is bnd wiftifr
 * it is bfing bttfmptfd in b sfdurity dontfxt tibt bllows tif
 * opfrbtion to bf pfrformfd. Tif
 * bpplidbtion dbn bllow or disbllow tif opfrbtion.
 * <p>
 * Tif <dodf>SfdurityMbnbgfr</dodf> dlbss dontbins mbny mftiods witi
 * nbmfs tibt bfgin witi tif word <dodf>difdk</dodf>. Tifsf mftiods
 * brf dbllfd by vbrious mftiods in tif Jbvb librbrifs bfforf tiosf
 * mftiods pfrform dfrtbin potfntiblly sfnsitivf opfrbtions. Tif
 * invodbtion of sudi b <dodf>difdk</dodf> mftiod typidblly looks likf tiis:
 * <blodkquotf><prf>
 *     SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
 *     if (sfdurity != null) {
 *         sfdurity.difdk<i>XXX</i>(brgumfnt, &nbsp;.&nbsp;.&nbsp;.&nbsp;);
 *     }
 * </prf></blodkquotf>
 * <p>
 * Tif sfdurity mbnbgfr is tifrfby givfn bn opportunity to prfvfnt
 * domplftion of tif opfrbtion by tirowing bn fxdfption. A sfdurity
 * mbnbgfr routinf simply rfturns if tif opfrbtion is pfrmittfd, but
 * tirows b <dodf>SfdurityExdfption</dodf> if tif opfrbtion is not
 * pfrmittfd.
 * <p>
 * Tif durrfnt sfdurity mbnbgfr is sft by tif
 * <dodf>sftSfdurityMbnbgfr</dodf> mftiod in dlbss
 * <dodf>Systfm</dodf>. Tif durrfnt sfdurity mbnbgfr is obtbinfd
 * by tif <dodf>gftSfdurityMbnbgfr</dodf> mftiod.
 * <p>
 * Tif spfdibl mftiod
 * {@link SfdurityMbnbgfr#difdkPfrmission(jbvb.sfdurity.Pfrmission)}
 * dftfrminfs wiftifr bn bddfss rfqufst indidbtfd by b spfdififd
 * pfrmission siould bf grbntfd or dfnifd. Tif
 * dffbult implfmfntbtion dblls
 *
 * <prf>
 *   AddfssControllfr.difdkPfrmission(pfrm);
 * </prf>
 *
 * <p>
 * If b rfqufstfd bddfss is bllowfd,
 * <dodf>difdkPfrmission</dodf> rfturns quiftly. If dfnifd, b
 * <dodf>SfdurityExdfption</dodf> is tirown.
 * <p>
 * As of Jbvb 2 SDK v1.2, tif dffbult implfmfntbtion of fbdi of tif otifr
 * <dodf>difdk</dodf> mftiods in <dodf>SfdurityMbnbgfr</dodf> is to
 * dbll tif <dodf>SfdurityMbnbgfr difdkPfrmission</dodf> mftiod
 * to dftfrminf if tif dblling tirfbd ibs pfrmission to pfrform tif rfqufstfd
 * opfrbtion.
 * <p>
 * Notf tibt tif <dodf>difdkPfrmission</dodf> mftiod witi
 * just b singlf pfrmission brgumfnt blwbys pfrforms sfdurity difdks
 * witiin tif dontfxt of tif durrfntly fxfduting tirfbd.
 * Somftimfs b sfdurity difdk tibt siould bf mbdf witiin b givfn dontfxt
 * will bdtublly nffd to bf donf from witiin b
 * <i>difffrfnt</i> dontfxt (for fxbmplf, from witiin b workfr tirfbd).
 * Tif {@link SfdurityMbnbgfr#gftSfdurityContfxt gftSfdurityContfxt} mftiod
 * bnd tif {@link SfdurityMbnbgfr#difdkPfrmission(jbvb.sfdurity.Pfrmission,
 * jbvb.lbng.Objfdt) difdkPfrmission}
 * mftiod tibt indludfs b dontfxt brgumfnt brf providfd
 * for tiis situbtion. Tif
 * <dodf>gftSfdurityContfxt</dodf> mftiod rfturns b "snbpsiot"
 * of tif durrfnt dblling dontfxt. (Tif dffbult implfmfntbtion
 * rfturns bn AddfssControlContfxt objfdt.) A sbmplf dbll is
 * tif following:
 *
 * <prf>
 *   Objfdt dontfxt = null;
 *   SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
 *   if (sm != null) dontfxt = sm.gftSfdurityContfxt();
 * </prf>
 *
 * <p>
 * Tif <dodf>difdkPfrmission</dodf> mftiod
 * tibt tbkfs b dontfxt objfdt in bddition to b pfrmission
 * mbkfs bddfss dfdisions bbsfd on tibt dontfxt,
 * rbtifr tibn on tibt of tif durrfnt fxfdution tirfbd.
 * Codf witiin b difffrfnt dontfxt dbn tius dbll tibt mftiod,
 * pbssing tif pfrmission bnd tif
 * prfviously-sbvfd dontfxt objfdt. A sbmplf dbll, using tif
 * SfdurityMbnbgfr <dodf>sm</dodf> obtbinfd bs in tif prfvious fxbmplf,
 * is tif following:
 *
 * <prf>
 *   if (sm != null) sm.difdkPfrmission(pfrmission, dontfxt);
 * </prf>
 *
 * <p>Pfrmissions fbll into tifsf dbtfgorifs: Filf, Sodkft, Nft,
 * Sfdurity, Runtimf, Propfrty, AWT, Rfflfdt, bnd Sfriblizbblf.
 * Tif dlbssfs mbnbging tifsf vbrious
 * pfrmission dbtfgorifs brf <dodf>jbvb.io.FilfPfrmission</dodf>,
 * <dodf>jbvb.nft.SodkftPfrmission</dodf>,
 * <dodf>jbvb.nft.NftPfrmission</dodf>,
 * <dodf>jbvb.sfdurity.SfdurityPfrmission</dodf>,
 * <dodf>jbvb.lbng.RuntimfPfrmission</dodf>,
 * <dodf>jbvb.util.PropfrtyPfrmission</dodf>,
 * <dodf>jbvb.bwt.AWTPfrmission</dodf>,
 * <dodf>jbvb.lbng.rfflfdt.RfflfdtPfrmission</dodf>, bnd
 * <dodf>jbvb.io.SfriblizbblfPfrmission</dodf>.
 *
 * <p>All but tif first two (FilfPfrmission bnd SodkftPfrmission) brf
 * subdlbssfs of <dodf>jbvb.sfdurity.BbsidPfrmission</dodf>, wiidi itsflf
 * is bn bbstrbdt subdlbss of tif
 * top-lfvfl dlbss for pfrmissions, wiidi is
 * <dodf>jbvb.sfdurity.Pfrmission</dodf>. BbsidPfrmission dffinfs tif
 * fundtionblity nffdfd for bll pfrmissions tibt dontbin b nbmf
 * tibt follows tif iifrbrdiidbl propfrty nbming donvfntion
 * (for fxbmplf, "fxitVM", "sftFbdtory", "qufufPrintJob", ftd).
 * An bstfrisk
 * mby bppfbr bt tif fnd of tif nbmf, following b ".", or by itsflf, to
 * signify b wilddbrd mbtdi. For fxbmplf: "b.*" or "*" is vblid,
 * "*b" or "b*b" is not vblid.
 *
 * <p>FilfPfrmission bnd SodkftPfrmission brf subdlbssfs of tif
 * top-lfvfl dlbss for pfrmissions
 * (<dodf>jbvb.sfdurity.Pfrmission</dodf>). Clbssfs likf tifsf
 * tibt ibvf b morf domplidbtfd nbmf syntbx tibn tibt usfd by
 * BbsidPfrmission subdlbss dirfdtly from Pfrmission rbtifr tibn from
 * BbsidPfrmission. For fxbmplf,
 * for b <dodf>jbvb.io.FilfPfrmission</dodf> objfdt, tif pfrmission nbmf is
 * tif pbti nbmf of b filf (or dirfdtory).
 *
 * <p>Somf of tif pfrmission dlbssfs ibvf bn "bdtions" list tibt tflls
 * tif bdtions tibt brf pfrmittfd for tif objfdt.  For fxbmplf,
 * for b <dodf>jbvb.io.FilfPfrmission</dodf> objfdt, tif bdtions list
 * (sudi bs "rfbd, writf") spfdififs wiidi bdtions brf grbntfd for tif
 * spfdififd filf (or for filfs in tif spfdififd dirfdtory).
 *
 * <p>Otifr pfrmission dlbssfs brf for "nbmfd" pfrmissions -
 * onfs tibt dontbin b nbmf but no bdtions list; you fitifr ibvf tif
 * nbmfd pfrmission or you don't.
 *
 * <p>Notf: Tifrf is blso b <dodf>jbvb.sfdurity.AllPfrmission</dodf>
 * pfrmission tibt implifs bll pfrmissions. It fxists to simplify tif work
 * of systfm bdministrbtors wio migit nffd to pfrform multiplf
 * tbsks tibt rfquirf bll (or numfrous) pfrmissions.
 * <p>
 * Sff <b irff ="../../../tfdinotfs/guidfs/sfdurity/pfrmissions.itml">
 * Pfrmissions in tif JDK</b> for pfrmission-rflbtfd informbtion.
 * Tiis dodumfnt indludfs, for fxbmplf, b tbblf listing tif vbrious SfdurityMbnbgfr
 * <dodf>difdk</dodf> mftiods bnd tif pfrmission(s) tif dffbult
 * implfmfntbtion of fbdi sudi mftiod rfquirfs.
 * It blso dontbins b tbblf of bll tif vfrsion 1.2 mftiods
 * tibt rfquirf pfrmissions, bnd for fbdi sudi mftiod tflls
 * wiidi pfrmission it rfquirfs.
 * <p>
 * For morf informbtion bbout <dodf>SfdurityMbnbgfr</dodf> dibngfs mbdf in
 * tif JDK bnd bdvidf rfgbrding porting of 1.1-stylf sfdurity mbnbgfrs,
 * sff tif <b irff="../../../tfdinotfs/guidfs/sfdurity/indfx.itml">sfdurity dodumfntbtion</b>.
 *
 * @butior  Artiur vbn Hoff
 * @butior  Rolbnd Sdifmfrs
 *
 * @sff     jbvb.lbng.ClbssLobdfr
 * @sff     jbvb.lbng.SfdurityExdfption
 * @sff     jbvb.lbng.Systfm#gftSfdurityMbnbgfr() gftSfdurityMbnbgfr
 * @sff     jbvb.lbng.Systfm#sftSfdurityMbnbgfr(jbvb.lbng.SfdurityMbnbgfr)
 *  sftSfdurityMbnbgfr
 * @sff     jbvb.sfdurity.AddfssControllfr AddfssControllfr
 * @sff     jbvb.sfdurity.AddfssControlContfxt AddfssControlContfxt
 * @sff     jbvb.sfdurity.AddfssControlExdfption AddfssControlExdfption
 * @sff     jbvb.sfdurity.Pfrmission
 * @sff     jbvb.sfdurity.BbsidPfrmission
 * @sff     jbvb.io.FilfPfrmission
 * @sff     jbvb.nft.SodkftPfrmission
 * @sff     jbvb.util.PropfrtyPfrmission
 * @sff     jbvb.lbng.RuntimfPfrmission
 * @sff     jbvb.bwt.AWTPfrmission
 * @sff     jbvb.sfdurity.Polidy Polidy
 * @sff     jbvb.sfdurity.SfdurityPfrmission SfdurityPfrmission
 * @sff     jbvb.sfdurity.ProtfdtionDombin
 *
 * @sindf   1.0
 */
publid
dlbss SfdurityMbnbgfr {

    /**
     * Tiis fifld is <dodf>truf</dodf> if tifrf is b sfdurity difdk in
     * progrfss; <dodf>fblsf</dodf> otifrwisf.
     *
     * @dfprfdbtfd Tiis typf of sfdurity difdking is not rfdommfndfd.
     *  It is rfdommfndfd tibt tif <dodf>difdkPfrmission</dodf>
     *  dbll bf usfd instfbd.
     */
    @Dfprfdbtfd
    protfdtfd boolfbn inCifdk;

    /*
     * Hbvf wf bffn initiblizfd. Efffdtivf bgbinst finblizfr bttbdks.
     */
    privbtf boolfbn initiblizfd = fblsf;


    /**
     * rfturns truf if tif durrfnt dontfxt ibs bffn grbntfd AllPfrmission
     */
    privbtf boolfbn ibsAllPfrmission() {
        try {
            difdkPfrmission(SfdurityConstbnts.ALL_PERMISSION);
            rfturn truf;
        } dbtdi (SfdurityExdfption sf) {
            rfturn fblsf;
        }
    }

    /**
     * Tfsts if tifrf is b sfdurity difdk in progrfss.
     *
     * @rfturn tif vbluf of tif <dodf>inCifdk</dodf> fifld. Tiis fifld
     *          siould dontbin <dodf>truf</dodf> if b sfdurity difdk is
     *          in progrfss,
     *          <dodf>fblsf</dodf> otifrwisf.
     * @sff     jbvb.lbng.SfdurityMbnbgfr#inCifdk
     * @dfprfdbtfd Tiis typf of sfdurity difdking is not rfdommfndfd.
     *  It is rfdommfndfd tibt tif <dodf>difdkPfrmission</dodf>
     *  dbll bf usfd instfbd.
     */
    @Dfprfdbtfd
    publid boolfbn gftInCifdk() {
        rfturn inCifdk;
    }

    /**
     * Construdts b nfw <dodf>SfdurityMbnbgfr</dodf>.
     *
     * <p> If tifrf is b sfdurity mbnbgfr blrfbdy instbllfd, tiis mftiod first
     * dblls tif sfdurity mbnbgfr's <dodf>difdkPfrmission</dodf> mftiod
     * witi tif <dodf>RuntimfPfrmission("drfbtfSfdurityMbnbgfr")</dodf>
     * pfrmission to fnsurf tif dblling tirfbd ibs pfrmission to drfbtf b nfw
     * sfdurity mbnbgfr.
     * Tiis mby rfsult in tirowing b <dodf>SfdurityExdfption</dodf>.
     *
     * @fxdfption  jbvb.lbng.SfdurityExdfption if b sfdurity mbnbgfr blrfbdy
     *             fxists bnd its <dodf>difdkPfrmission</dodf> mftiod
     *             dofsn't bllow drfbtion of b nfw sfdurity mbnbgfr.
     * @sff        jbvb.lbng.Systfm#gftSfdurityMbnbgfr()
     * @sff        #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     * @sff jbvb.lbng.RuntimfPfrmission
     */
    publid SfdurityMbnbgfr() {
        syndironizfd(SfdurityMbnbgfr.dlbss) {
            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
            if (sm != null) {
                // bsk tif durrfntly instbllfd sfdurity mbnbgfr if wf
                // dbn drfbtf b nfw onf.
                sm.difdkPfrmission(nfw RuntimfPfrmission
                                   ("drfbtfSfdurityMbnbgfr"));
            }
            initiblizfd = truf;
        }
    }

    /**
     * Rfturns tif durrfnt fxfdution stbdk bs bn brrby of dlbssfs.
     * <p>
     * Tif lfngti of tif brrby is tif numbfr of mftiods on tif fxfdution
     * stbdk. Tif flfmfnt bt indfx <dodf>0</dodf> is tif dlbss of tif
     * durrfntly fxfduting mftiod, tif flfmfnt bt indfx <dodf>1</dodf> is
     * tif dlbss of tibt mftiod's dbllfr, bnd so on.
     *
     * @rfturn  tif fxfdution stbdk.
     */
    protfdtfd nbtivf Clbss<?>[] gftClbssContfxt();

    /**
     * Rfturns tif dlbss lobdfr of tif most rfdfntly fxfduting mftiod from
     * b dlbss dffinfd using b non-systfm dlbss lobdfr. A non-systfm
     * dlbss lobdfr is dffinfd bs bfing b dlbss lobdfr tibt is not fqubl to
     * tif systfm dlbss lobdfr (bs rfturnfd
     * by {@link ClbssLobdfr#gftSystfmClbssLobdfr}) or onf of its bndfstors.
     * <p>
     * Tiis mftiod will rfturn
     * <dodf>null</dodf> in tif following tirff dbsfs:
     * <ol>
     *   <li>All mftiods on tif fxfdution stbdk brf from dlbssfs
     *   dffinfd using tif systfm dlbss lobdfr or onf of its bndfstors.
     *
     *   <li>All mftiods on tif fxfdution stbdk up to tif first
     *   "privilfgfd" dbllfr
     *   (sff {@link jbvb.sfdurity.AddfssControllfr#doPrivilfgfd})
     *   brf from dlbssfs
     *   dffinfd using tif systfm dlbss lobdfr or onf of its bndfstors.
     *
     *   <li> A dbll to <dodf>difdkPfrmission</dodf> witi
     *   <dodf>jbvb.sfdurity.AllPfrmission</dodf> dofs not
     *   rfsult in b SfdurityExdfption.
     *
     * </ol>
     *
     * @rfturn  tif dlbss lobdfr of tif most rfdfnt oddurrfndf on tif stbdk
     *          of b mftiod from b dlbss dffinfd using b non-systfm dlbss
     *          lobdfr.
     *
     * @dfprfdbtfd Tiis typf of sfdurity difdking is not rfdommfndfd.
     *  It is rfdommfndfd tibt tif <dodf>difdkPfrmission</dodf>
     *  dbll bf usfd instfbd.
     *
     * @sff  jbvb.lbng.ClbssLobdfr#gftSystfmClbssLobdfr() gftSystfmClbssLobdfr
     * @sff  #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    @Dfprfdbtfd
    protfdtfd ClbssLobdfr durrfntClbssLobdfr() {
        ClbssLobdfr dl = durrfntClbssLobdfr0();
        if ((dl != null) && ibsAllPfrmission())
            dl = null;
        rfturn dl;
    }

    privbtf nbtivf ClbssLobdfr durrfntClbssLobdfr0();

    /**
     * Rfturns tif dlbss of tif most rfdfntly fxfduting mftiod from
     * b dlbss dffinfd using b non-systfm dlbss lobdfr. A non-systfm
     * dlbss lobdfr is dffinfd bs bfing b dlbss lobdfr tibt is not fqubl to
     * tif systfm dlbss lobdfr (bs rfturnfd
     * by {@link ClbssLobdfr#gftSystfmClbssLobdfr}) or onf of its bndfstors.
     * <p>
     * Tiis mftiod will rfturn
     * <dodf>null</dodf> in tif following tirff dbsfs:
     * <ol>
     *   <li>All mftiods on tif fxfdution stbdk brf from dlbssfs
     *   dffinfd using tif systfm dlbss lobdfr or onf of its bndfstors.
     *
     *   <li>All mftiods on tif fxfdution stbdk up to tif first
     *   "privilfgfd" dbllfr
     *   (sff {@link jbvb.sfdurity.AddfssControllfr#doPrivilfgfd})
     *   brf from dlbssfs
     *   dffinfd using tif systfm dlbss lobdfr or onf of its bndfstors.
     *
     *   <li> A dbll to <dodf>difdkPfrmission</dodf> witi
     *   <dodf>jbvb.sfdurity.AllPfrmission</dodf> dofs not
     *   rfsult in b SfdurityExdfption.
     *
     * </ol>
     *
     * @rfturn  tif dlbss  of tif most rfdfnt oddurrfndf on tif stbdk
     *          of b mftiod from b dlbss dffinfd using b non-systfm dlbss
     *          lobdfr.
     *
     * @dfprfdbtfd Tiis typf of sfdurity difdking is not rfdommfndfd.
     *  It is rfdommfndfd tibt tif <dodf>difdkPfrmission</dodf>
     *  dbll bf usfd instfbd.
     *
     * @sff  jbvb.lbng.ClbssLobdfr#gftSystfmClbssLobdfr() gftSystfmClbssLobdfr
     * @sff  #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    @Dfprfdbtfd
    protfdtfd Clbss<?> durrfntLobdfdClbss() {
        Clbss<?> d = durrfntLobdfdClbss0();
        if ((d != null) && ibsAllPfrmission())
            d = null;
        rfturn d;
    }

    /**
     * Rfturns tif stbdk dfpti of tif spfdififd dlbss.
     *
     * @pbrbm   nbmf   tif fully qublififd nbmf of tif dlbss to sfbrdi for.
     * @rfturn  tif dfpti on tif stbdk frbmf of tif first oddurrfndf of b
     *          mftiod from b dlbss witi tif spfdififd nbmf;
     *          <dodf>-1</dodf> if sudi b frbmf dbnnot bf found.
     * @dfprfdbtfd Tiis typf of sfdurity difdking is not rfdommfndfd.
     *  It is rfdommfndfd tibt tif <dodf>difdkPfrmission</dodf>
     *  dbll bf usfd instfbd.
     *
     */
    @Dfprfdbtfd
    protfdtfd nbtivf int dlbssDfpti(String nbmf);

    /**
     * Rfturns tif stbdk dfpti of tif most rfdfntly fxfduting mftiod
     * from b dlbss dffinfd using b non-systfm dlbss lobdfr.  A non-systfm
     * dlbss lobdfr is dffinfd bs bfing b dlbss lobdfr tibt is not fqubl to
     * tif systfm dlbss lobdfr (bs rfturnfd
     * by {@link ClbssLobdfr#gftSystfmClbssLobdfr}) or onf of its bndfstors.
     * <p>
     * Tiis mftiod will rfturn
     * -1 in tif following tirff dbsfs:
     * <ol>
     *   <li>All mftiods on tif fxfdution stbdk brf from dlbssfs
     *   dffinfd using tif systfm dlbss lobdfr or onf of its bndfstors.
     *
     *   <li>All mftiods on tif fxfdution stbdk up to tif first
     *   "privilfgfd" dbllfr
     *   (sff {@link jbvb.sfdurity.AddfssControllfr#doPrivilfgfd})
     *   brf from dlbssfs
     *   dffinfd using tif systfm dlbss lobdfr or onf of its bndfstors.
     *
     *   <li> A dbll to <dodf>difdkPfrmission</dodf> witi
     *   <dodf>jbvb.sfdurity.AllPfrmission</dodf> dofs not
     *   rfsult in b SfdurityExdfption.
     *
     * </ol>
     *
     * @rfturn tif dfpti on tif stbdk frbmf of tif most rfdfnt oddurrfndf of
     *          b mftiod from b dlbss dffinfd using b non-systfm dlbss lobdfr.
     *
     * @dfprfdbtfd Tiis typf of sfdurity difdking is not rfdommfndfd.
     *  It is rfdommfndfd tibt tif <dodf>difdkPfrmission</dodf>
     *  dbll bf usfd instfbd.
     *
     * @sff   jbvb.lbng.ClbssLobdfr#gftSystfmClbssLobdfr() gftSystfmClbssLobdfr
     * @sff   #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    @Dfprfdbtfd
    protfdtfd int dlbssLobdfrDfpti() {
        int dfpti = dlbssLobdfrDfpti0();
        if (dfpti != -1) {
            if (ibsAllPfrmission())
                dfpti = -1;
            flsf
                dfpti--; // mbkf surf wf don't indludf oursflf
        }
        rfturn dfpti;
    }

    privbtf nbtivf int dlbssLobdfrDfpti0();

    /**
     * Tfsts if b mftiod from b dlbss witi tif spfdififd
     *         nbmf is on tif fxfdution stbdk.
     *
     * @pbrbm  nbmf   tif fully qublififd nbmf of tif dlbss.
     * @rfturn <dodf>truf</dodf> if b mftiod from b dlbss witi tif spfdififd
     *         nbmf is on tif fxfdution stbdk; <dodf>fblsf</dodf> otifrwisf.
     * @dfprfdbtfd Tiis typf of sfdurity difdking is not rfdommfndfd.
     *  It is rfdommfndfd tibt tif <dodf>difdkPfrmission</dodf>
     *  dbll bf usfd instfbd.
     */
    @Dfprfdbtfd
    protfdtfd boolfbn inClbss(String nbmf) {
        rfturn dlbssDfpti(nbmf) >= 0;
    }

    /**
     * Bbsidblly, tfsts if b mftiod from b dlbss dffinfd using b
     *          dlbss lobdfr is on tif fxfdution stbdk.
     *
     * @rfturn  <dodf>truf</dodf> if b dbll to <dodf>durrfntClbssLobdfr</dodf>
     *          ibs b non-null rfturn vbluf.
     *
     * @dfprfdbtfd Tiis typf of sfdurity difdking is not rfdommfndfd.
     *  It is rfdommfndfd tibt tif <dodf>difdkPfrmission</dodf>
     *  dbll bf usfd instfbd.
     * @sff        #durrfntClbssLobdfr() durrfntClbssLobdfr
     */
    @Dfprfdbtfd
    protfdtfd boolfbn inClbssLobdfr() {
        rfturn durrfntClbssLobdfr() != null;
    }

    /**
     * Crfbtfs bn objfdt tibt fndbpsulbtfs tif durrfnt fxfdution
     * fnvironmfnt. Tif rfsult of tiis mftiod is usfd, for fxbmplf, by tif
     * tirff-brgumfnt <dodf>difdkConnfdt</dodf> mftiod bnd by tif
     * two-brgumfnt <dodf>difdkRfbd</dodf> mftiod.
     * Tifsf mftiods brf nffdfd bfdbusf b trustfd mftiod mby bf dbllfd
     * on to rfbd b filf or opfn b sodkft on bfiblf of bnotifr mftiod.
     * Tif trustfd mftiod nffds to dftfrminf if tif otifr (possibly
     * untrustfd) mftiod would bf bllowfd to pfrform tif opfrbtion on its
     * own.
     * <p> Tif dffbult implfmfntbtion of tiis mftiod is to rfturn
     * bn <dodf>AddfssControlContfxt</dodf> objfdt.
     *
     * @rfturn  bn implfmfntbtion-dfpfndfnt objfdt tibt fndbpsulbtfs
     *          suffidifnt informbtion bbout tif durrfnt fxfdution fnvironmfnt
     *          to pfrform somf sfdurity difdks lbtfr.
     * @sff     jbvb.lbng.SfdurityMbnbgfr#difdkConnfdt(jbvb.lbng.String, int,
     *   jbvb.lbng.Objfdt) difdkConnfdt
     * @sff     jbvb.lbng.SfdurityMbnbgfr#difdkRfbd(jbvb.lbng.String,
     *   jbvb.lbng.Objfdt) difdkRfbd
     * @sff     jbvb.sfdurity.AddfssControlContfxt AddfssControlContfxt
     */
    publid Objfdt gftSfdurityContfxt() {
        rfturn AddfssControllfr.gftContfxt();
    }

    /**
     * Tirows b <dodf>SfdurityExdfption</dodf> if tif rfqufstfd
     * bddfss, spfdififd by tif givfn pfrmission, is not pfrmittfd bbsfd
     * on tif sfdurity polidy durrfntly in ffffdt.
     * <p>
     * Tiis mftiod dblls <dodf>AddfssControllfr.difdkPfrmission</dodf>
     * witi tif givfn pfrmission.
     *
     * @pbrbm     pfrm   tif rfqufstfd pfrmission.
     * @fxdfption SfdurityExdfption if bddfss is not pfrmittfd bbsfd on
     *            tif durrfnt sfdurity polidy.
     * @fxdfption NullPointfrExdfption if tif pfrmission brgumfnt is
     *            <dodf>null</dodf>.
     * @sindf     1.2
     */
    publid void difdkPfrmission(Pfrmission pfrm) {
        jbvb.sfdurity.AddfssControllfr.difdkPfrmission(pfrm);
    }

    /**
     * Tirows b <dodf>SfdurityExdfption</dodf> if tif
     * spfdififd sfdurity dontfxt is dfnifd bddfss to tif rfsourdf
     * spfdififd by tif givfn pfrmission.
     * Tif dontfxt must bf b sfdurity
     * dontfxt rfturnfd by b prfvious dbll to
     * <dodf>gftSfdurityContfxt</dodf> bnd tif bddfss dontrol
     * dfdision is bbsfd upon tif donfigurfd sfdurity polidy for
     * tibt sfdurity dontfxt.
     * <p>
     * If <dodf>dontfxt</dodf> is bn instbndf of
     * <dodf>AddfssControlContfxt</dodf> tifn tif
     * <dodf>AddfssControlContfxt.difdkPfrmission</dodf> mftiod is
     * invokfd witi tif spfdififd pfrmission.
     * <p>
     * If <dodf>dontfxt</dodf> is not bn instbndf of
     * <dodf>AddfssControlContfxt</dodf> tifn b
     * <dodf>SfdurityExdfption</dodf> is tirown.
     *
     * @pbrbm      pfrm      tif spfdififd pfrmission
     * @pbrbm      dontfxt   b systfm-dfpfndfnt sfdurity dontfxt.
     * @fxdfption  SfdurityExdfption  if tif spfdififd sfdurity dontfxt
     *             is not bn instbndf of <dodf>AddfssControlContfxt</dodf>
     *             (f.g., is <dodf>null</dodf>), or is dfnifd bddfss to tif
     *             rfsourdf spfdififd by tif givfn pfrmission.
     * @fxdfption  NullPointfrExdfption if tif pfrmission brgumfnt is
     *             <dodf>null</dodf>.
     * @sff        jbvb.lbng.SfdurityMbnbgfr#gftSfdurityContfxt()
     * @sff jbvb.sfdurity.AddfssControlContfxt#difdkPfrmission(jbvb.sfdurity.Pfrmission)
     * @sindf      1.2
     */
    publid void difdkPfrmission(Pfrmission pfrm, Objfdt dontfxt) {
        if (dontfxt instbndfof AddfssControlContfxt) {
            ((AddfssControlContfxt)dontfxt).difdkPfrmission(pfrm);
        } flsf {
            tirow nfw SfdurityExdfption();
        }
    }

    /**
     * Tirows b <dodf>SfdurityExdfption</dodf> if tif
     * dblling tirfbd is not bllowfd to drfbtf b nfw dlbss lobdfr.
     * <p>
     * Tiis mftiod dblls <dodf>difdkPfrmission</dodf> witi tif
     * <dodf>RuntimfPfrmission("drfbtfClbssLobdfr")</dodf>
     * pfrmission.
     * <p>
     * If you ovfrridf tiis mftiod, tifn you siould mbkf b dbll to
     * <dodf>supfr.difdkCrfbtfClbssLobdfr</dodf>
     * bt tif point tif ovfrriddfn mftiod would normblly tirow bn
     * fxdfption.
     *
     * @fxdfption SfdurityExdfption if tif dblling tirfbd dofs not
     *             ibvf pfrmission
     *             to drfbtf b nfw dlbss lobdfr.
     * @sff        jbvb.lbng.ClbssLobdfr#ClbssLobdfr()
     * @sff        #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    publid void difdkCrfbtfClbssLobdfr() {
        difdkPfrmission(SfdurityConstbnts.CREATE_CLASSLOADER_PERMISSION);
    }

    /**
     * rfffrfndf to tif root tirfbd group, usfd for tif difdkAddfss
     * mftiods.
     */

    privbtf stbtid TirfbdGroup rootGroup = gftRootGroup();

    privbtf stbtid TirfbdGroup gftRootGroup() {
        TirfbdGroup root =  Tirfbd.durrfntTirfbd().gftTirfbdGroup();
        wiilf (root.gftPbrfnt() != null) {
            root = root.gftPbrfnt();
        }
        rfturn root;
    }

    /**
     * Tirows b <dodf>SfdurityExdfption</dodf> if tif
     * dblling tirfbd is not bllowfd to modify tif tirfbd brgumfnt.
     * <p>
     * Tiis mftiod is invokfd for tif durrfnt sfdurity mbnbgfr by tif
     * <dodf>stop</dodf>, <dodf>suspfnd</dodf>, <dodf>rfsumf</dodf>,
     * <dodf>sftPriority</dodf>, <dodf>sftNbmf</dodf>, bnd
     * <dodf>sftDbfmon</dodf> mftiods of dlbss <dodf>Tirfbd</dodf>.
     * <p>
     * If tif tirfbd brgumfnt is b systfm tirfbd (bflongs to
     * tif tirfbd group witi b <dodf>null</dodf> pbrfnt) tifn
     * tiis mftiod dblls <dodf>difdkPfrmission</dodf> witi tif
     * <dodf>RuntimfPfrmission("modifyTirfbd")</dodf> pfrmission.
     * If tif tirfbd brgumfnt is <i>not</i> b systfm tirfbd,
     * tiis mftiod just rfturns silfntly.
     * <p>
     * Applidbtions tibt wbnt b stridtfr polidy siould ovfrridf tiis
     * mftiod. If tiis mftiod is ovfrriddfn, tif mftiod tibt ovfrridfs
     * it siould bdditionblly difdk to sff if tif dblling tirfbd ibs tif
     * <dodf>RuntimfPfrmission("modifyTirfbd")</dodf> pfrmission, bnd
     * if so, rfturn silfntly. Tiis is to fnsurf tibt dodf grbntfd
     * tibt pfrmission (sudi bs tif JDK itsflf) is bllowfd to
     * mbnipulbtf bny tirfbd.
     * <p>
     * If tiis mftiod is ovfrriddfn, tifn
     * <dodf>supfr.difdkAddfss</dodf> siould
     * bf dbllfd by tif first stbtfmfnt in tif ovfrriddfn mftiod, or tif
     * fquivblfnt sfdurity difdk siould bf plbdfd in tif ovfrriddfn mftiod.
     *
     * @pbrbm      t   tif tirfbd to bf difdkfd.
     * @fxdfption  SfdurityExdfption  if tif dblling tirfbd dofs not ibvf
     *             pfrmission to modify tif tirfbd.
     * @fxdfption  NullPointfrExdfption if tif tirfbd brgumfnt is
     *             <dodf>null</dodf>.
     * @sff        jbvb.lbng.Tirfbd#rfsumf() rfsumf
     * @sff        jbvb.lbng.Tirfbd#sftDbfmon(boolfbn) sftDbfmon
     * @sff        jbvb.lbng.Tirfbd#sftNbmf(jbvb.lbng.String) sftNbmf
     * @sff        jbvb.lbng.Tirfbd#sftPriority(int) sftPriority
     * @sff        jbvb.lbng.Tirfbd#stop() stop
     * @sff        jbvb.lbng.Tirfbd#suspfnd() suspfnd
     * @sff        #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    publid void difdkAddfss(Tirfbd t) {
        if (t == null) {
            tirow nfw NullPointfrExdfption("tirfbd dbn't bf null");
        }
        if (t.gftTirfbdGroup() == rootGroup) {
            difdkPfrmission(SfdurityConstbnts.MODIFY_THREAD_PERMISSION);
        } flsf {
            // just rfturn
        }
    }
    /**
     * Tirows b <dodf>SfdurityExdfption</dodf> if tif
     * dblling tirfbd is not bllowfd to modify tif tirfbd group brgumfnt.
     * <p>
     * Tiis mftiod is invokfd for tif durrfnt sfdurity mbnbgfr wifn b
     * nfw diild tirfbd or diild tirfbd group is drfbtfd, bnd by tif
     * <dodf>sftDbfmon</dodf>, <dodf>sftMbxPriority</dodf>,
     * <dodf>stop</dodf>, <dodf>suspfnd</dodf>, <dodf>rfsumf</dodf>, bnd
     * <dodf>dfstroy</dodf> mftiods of dlbss <dodf>TirfbdGroup</dodf>.
     * <p>
     * If tif tirfbd group brgumfnt is tif systfm tirfbd group (
     * ibs b <dodf>null</dodf> pbrfnt) tifn
     * tiis mftiod dblls <dodf>difdkPfrmission</dodf> witi tif
     * <dodf>RuntimfPfrmission("modifyTirfbdGroup")</dodf> pfrmission.
     * If tif tirfbd group brgumfnt is <i>not</i> tif systfm tirfbd group,
     * tiis mftiod just rfturns silfntly.
     * <p>
     * Applidbtions tibt wbnt b stridtfr polidy siould ovfrridf tiis
     * mftiod. If tiis mftiod is ovfrriddfn, tif mftiod tibt ovfrridfs
     * it siould bdditionblly difdk to sff if tif dblling tirfbd ibs tif
     * <dodf>RuntimfPfrmission("modifyTirfbdGroup")</dodf> pfrmission, bnd
     * if so, rfturn silfntly. Tiis is to fnsurf tibt dodf grbntfd
     * tibt pfrmission (sudi bs tif JDK itsflf) is bllowfd to
     * mbnipulbtf bny tirfbd.
     * <p>
     * If tiis mftiod is ovfrriddfn, tifn
     * <dodf>supfr.difdkAddfss</dodf> siould
     * bf dbllfd by tif first stbtfmfnt in tif ovfrriddfn mftiod, or tif
     * fquivblfnt sfdurity difdk siould bf plbdfd in tif ovfrriddfn mftiod.
     *
     * @pbrbm      g   tif tirfbd group to bf difdkfd.
     * @fxdfption  SfdurityExdfption  if tif dblling tirfbd dofs not ibvf
     *             pfrmission to modify tif tirfbd group.
     * @fxdfption  NullPointfrExdfption if tif tirfbd group brgumfnt is
     *             <dodf>null</dodf>.
     * @sff        jbvb.lbng.TirfbdGroup#dfstroy() dfstroy
     * @sff        jbvb.lbng.TirfbdGroup#rfsumf() rfsumf
     * @sff        jbvb.lbng.TirfbdGroup#sftDbfmon(boolfbn) sftDbfmon
     * @sff        jbvb.lbng.TirfbdGroup#sftMbxPriority(int) sftMbxPriority
     * @sff        jbvb.lbng.TirfbdGroup#stop() stop
     * @sff        jbvb.lbng.TirfbdGroup#suspfnd() suspfnd
     * @sff        #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    publid void difdkAddfss(TirfbdGroup g) {
        if (g == null) {
            tirow nfw NullPointfrExdfption("tirfbd group dbn't bf null");
        }
        if (g == rootGroup) {
            difdkPfrmission(SfdurityConstbnts.MODIFY_THREADGROUP_PERMISSION);
        } flsf {
            // just rfturn
        }
    }

    /**
     * Tirows b <dodf>SfdurityExdfption</dodf> if tif
     * dblling tirfbd is not bllowfd to dbusf tif Jbvb Virtubl Mbdiinf to
     * iblt witi tif spfdififd stbtus dodf.
     * <p>
     * Tiis mftiod is invokfd for tif durrfnt sfdurity mbnbgfr by tif
     * <dodf>fxit</dodf> mftiod of dlbss <dodf>Runtimf</dodf>. A stbtus
     * of <dodf>0</dodf> indidbtfs suddfss; otifr vblufs indidbtf vbrious
     * frrors.
     * <p>
     * Tiis mftiod dblls <dodf>difdkPfrmission</dodf> witi tif
     * <dodf>RuntimfPfrmission("fxitVM."+stbtus)</dodf> pfrmission.
     * <p>
     * If you ovfrridf tiis mftiod, tifn you siould mbkf b dbll to
     * <dodf>supfr.difdkExit</dodf>
     * bt tif point tif ovfrriddfn mftiod would normblly tirow bn
     * fxdfption.
     *
     * @pbrbm      stbtus   tif fxit stbtus.
     * @fxdfption SfdurityExdfption if tif dblling tirfbd dofs not ibvf
     *              pfrmission to iblt tif Jbvb Virtubl Mbdiinf witi
     *              tif spfdififd stbtus.
     * @sff        jbvb.lbng.Runtimf#fxit(int) fxit
     * @sff        #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    publid void difdkExit(int stbtus) {
        difdkPfrmission(nfw RuntimfPfrmission("fxitVM."+stbtus));
    }

    /**
     * Tirows b <dodf>SfdurityExdfption</dodf> if tif
     * dblling tirfbd is not bllowfd to drfbtf b subprodfss.
     * <p>
     * Tiis mftiod is invokfd for tif durrfnt sfdurity mbnbgfr by tif
     * <dodf>fxfd</dodf> mftiods of dlbss <dodf>Runtimf</dodf>.
     * <p>
     * Tiis mftiod dblls <dodf>difdkPfrmission</dodf> witi tif
     * <dodf>FilfPfrmission(dmd,"fxfdutf")</dodf> pfrmission
     * if dmd is bn bbsolutf pbti, otifrwisf it dblls
     * <dodf>difdkPfrmission</dodf> witi
     * <dodf>FilfPfrmission("&lt;&lt;ALL FILES&gt;&gt;","fxfdutf")</dodf>.
     * <p>
     * If you ovfrridf tiis mftiod, tifn you siould mbkf b dbll to
     * <dodf>supfr.difdkExfd</dodf>
     * bt tif point tif ovfrriddfn mftiod would normblly tirow bn
     * fxdfption.
     *
     * @pbrbm      dmd   tif spfdififd systfm dommbnd.
     * @fxdfption  SfdurityExdfption if tif dblling tirfbd dofs not ibvf
     *             pfrmission to drfbtf b subprodfss.
     * @fxdfption  NullPointfrExdfption if tif <dodf>dmd</dodf> brgumfnt is
     *             <dodf>null</dodf>.
     * @sff     jbvb.lbng.Runtimf#fxfd(jbvb.lbng.String)
     * @sff     jbvb.lbng.Runtimf#fxfd(jbvb.lbng.String, jbvb.lbng.String[])
     * @sff     jbvb.lbng.Runtimf#fxfd(jbvb.lbng.String[])
     * @sff     jbvb.lbng.Runtimf#fxfd(jbvb.lbng.String[], jbvb.lbng.String[])
     * @sff     #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    publid void difdkExfd(String dmd) {
        Filf f = nfw Filf(dmd);
        if (f.isAbsolutf()) {
            difdkPfrmission(nfw FilfPfrmission(dmd,
                SfdurityConstbnts.FILE_EXECUTE_ACTION));
        } flsf {
            difdkPfrmission(nfw FilfPfrmission("<<ALL FILES>>",
                SfdurityConstbnts.FILE_EXECUTE_ACTION));
        }
    }

    /**
     * Tirows b <dodf>SfdurityExdfption</dodf> if tif
     * dblling tirfbd is not bllowfd to dynbmid link tif librbry dodf
     * spfdififd by tif string brgumfnt filf. Tif brgumfnt is fitifr b
     * simplf librbry nbmf or b domplftf filfnbmf.
     * <p>
     * Tiis mftiod is invokfd for tif durrfnt sfdurity mbnbgfr by
     * mftiods <dodf>lobd</dodf> bnd <dodf>lobdLibrbry</dodf> of dlbss
     * <dodf>Runtimf</dodf>.
     * <p>
     * Tiis mftiod dblls <dodf>difdkPfrmission</dodf> witi tif
     * <dodf>RuntimfPfrmission("lobdLibrbry."+lib)</dodf> pfrmission.
     * <p>
     * If you ovfrridf tiis mftiod, tifn you siould mbkf b dbll to
     * <dodf>supfr.difdkLink</dodf>
     * bt tif point tif ovfrriddfn mftiod would normblly tirow bn
     * fxdfption.
     *
     * @pbrbm      lib   tif nbmf of tif librbry.
     * @fxdfption  SfdurityExdfption if tif dblling tirfbd dofs not ibvf
     *             pfrmission to dynbmidblly link tif librbry.
     * @fxdfption  NullPointfrExdfption if tif <dodf>lib</dodf> brgumfnt is
     *             <dodf>null</dodf>.
     * @sff        jbvb.lbng.Runtimf#lobd(jbvb.lbng.String)
     * @sff        jbvb.lbng.Runtimf#lobdLibrbry(jbvb.lbng.String)
     * @sff        #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    publid void difdkLink(String lib) {
        if (lib == null) {
            tirow nfw NullPointfrExdfption("librbry dbn't bf null");
        }
        difdkPfrmission(nfw RuntimfPfrmission("lobdLibrbry."+lib));
    }

    /**
     * Tirows b <dodf>SfdurityExdfption</dodf> if tif
     * dblling tirfbd is not bllowfd to rfbd from tif spfdififd filf
     * dfsdriptor.
     * <p>
     * Tiis mftiod dblls <dodf>difdkPfrmission</dodf> witi tif
     * <dodf>RuntimfPfrmission("rfbdFilfDfsdriptor")</dodf>
     * pfrmission.
     * <p>
     * If you ovfrridf tiis mftiod, tifn you siould mbkf b dbll to
     * <dodf>supfr.difdkRfbd</dodf>
     * bt tif point tif ovfrriddfn mftiod would normblly tirow bn
     * fxdfption.
     *
     * @pbrbm      fd   tif systfm-dfpfndfnt filf dfsdriptor.
     * @fxdfption  SfdurityExdfption  if tif dblling tirfbd dofs not ibvf
     *             pfrmission to bddfss tif spfdififd filf dfsdriptor.
     * @fxdfption  NullPointfrExdfption if tif filf dfsdriptor brgumfnt is
     *             <dodf>null</dodf>.
     * @sff        jbvb.io.FilfDfsdriptor
     * @sff        #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    publid void difdkRfbd(FilfDfsdriptor fd) {
        if (fd == null) {
            tirow nfw NullPointfrExdfption("filf dfsdriptor dbn't bf null");
        }
        difdkPfrmission(nfw RuntimfPfrmission("rfbdFilfDfsdriptor"));
    }

    /**
     * Tirows b <dodf>SfdurityExdfption</dodf> if tif
     * dblling tirfbd is not bllowfd to rfbd tif filf spfdififd by tif
     * string brgumfnt.
     * <p>
     * Tiis mftiod dblls <dodf>difdkPfrmission</dodf> witi tif
     * <dodf>FilfPfrmission(filf,"rfbd")</dodf> pfrmission.
     * <p>
     * If you ovfrridf tiis mftiod, tifn you siould mbkf b dbll to
     * <dodf>supfr.difdkRfbd</dodf>
     * bt tif point tif ovfrriddfn mftiod would normblly tirow bn
     * fxdfption.
     *
     * @pbrbm      filf   tif systfm-dfpfndfnt filf nbmf.
     * @fxdfption  SfdurityExdfption if tif dblling tirfbd dofs not ibvf
     *             pfrmission to bddfss tif spfdififd filf.
     * @fxdfption  NullPointfrExdfption if tif <dodf>filf</dodf> brgumfnt is
     *             <dodf>null</dodf>.
     * @sff        #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    publid void difdkRfbd(String filf) {
        difdkPfrmission(nfw FilfPfrmission(filf,
            SfdurityConstbnts.FILE_READ_ACTION));
    }

    /**
     * Tirows b <dodf>SfdurityExdfption</dodf> if tif
     * spfdififd sfdurity dontfxt is not bllowfd to rfbd tif filf
     * spfdififd by tif string brgumfnt. Tif dontfxt must bf b sfdurity
     * dontfxt rfturnfd by b prfvious dbll to
     * <dodf>gftSfdurityContfxt</dodf>.
     * <p> If <dodf>dontfxt</dodf> is bn instbndf of
     * <dodf>AddfssControlContfxt</dodf> tifn tif
     * <dodf>AddfssControlContfxt.difdkPfrmission</dodf> mftiod will
     * bf invokfd witi tif <dodf>FilfPfrmission(filf,"rfbd")</dodf> pfrmission.
     * <p> If <dodf>dontfxt</dodf> is not bn instbndf of
     * <dodf>AddfssControlContfxt</dodf> tifn b
     * <dodf>SfdurityExdfption</dodf> is tirown.
     * <p>
     * If you ovfrridf tiis mftiod, tifn you siould mbkf b dbll to
     * <dodf>supfr.difdkRfbd</dodf>
     * bt tif point tif ovfrriddfn mftiod would normblly tirow bn
     * fxdfption.
     *
     * @pbrbm      filf      tif systfm-dfpfndfnt filfnbmf.
     * @pbrbm      dontfxt   b systfm-dfpfndfnt sfdurity dontfxt.
     * @fxdfption  SfdurityExdfption  if tif spfdififd sfdurity dontfxt
     *             is not bn instbndf of <dodf>AddfssControlContfxt</dodf>
     *             (f.g., is <dodf>null</dodf>), or dofs not ibvf pfrmission
     *             to rfbd tif spfdififd filf.
     * @fxdfption  NullPointfrExdfption if tif <dodf>filf</dodf> brgumfnt is
     *             <dodf>null</dodf>.
     * @sff        jbvb.lbng.SfdurityMbnbgfr#gftSfdurityContfxt()
     * @sff        jbvb.sfdurity.AddfssControlContfxt#difdkPfrmission(jbvb.sfdurity.Pfrmission)
     */
    publid void difdkRfbd(String filf, Objfdt dontfxt) {
        difdkPfrmission(
            nfw FilfPfrmission(filf, SfdurityConstbnts.FILE_READ_ACTION),
            dontfxt);
    }

    /**
     * Tirows b <dodf>SfdurityExdfption</dodf> if tif
     * dblling tirfbd is not bllowfd to writf to tif spfdififd filf
     * dfsdriptor.
     * <p>
     * Tiis mftiod dblls <dodf>difdkPfrmission</dodf> witi tif
     * <dodf>RuntimfPfrmission("writfFilfDfsdriptor")</dodf>
     * pfrmission.
     * <p>
     * If you ovfrridf tiis mftiod, tifn you siould mbkf b dbll to
     * <dodf>supfr.difdkWritf</dodf>
     * bt tif point tif ovfrriddfn mftiod would normblly tirow bn
     * fxdfption.
     *
     * @pbrbm      fd   tif systfm-dfpfndfnt filf dfsdriptor.
     * @fxdfption SfdurityExdfption  if tif dblling tirfbd dofs not ibvf
     *             pfrmission to bddfss tif spfdififd filf dfsdriptor.
     * @fxdfption  NullPointfrExdfption if tif filf dfsdriptor brgumfnt is
     *             <dodf>null</dodf>.
     * @sff        jbvb.io.FilfDfsdriptor
     * @sff        #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    publid void difdkWritf(FilfDfsdriptor fd) {
        if (fd == null) {
            tirow nfw NullPointfrExdfption("filf dfsdriptor dbn't bf null");
        }
        difdkPfrmission(nfw RuntimfPfrmission("writfFilfDfsdriptor"));

    }

    /**
     * Tirows b <dodf>SfdurityExdfption</dodf> if tif
     * dblling tirfbd is not bllowfd to writf to tif filf spfdififd by
     * tif string brgumfnt.
     * <p>
     * Tiis mftiod dblls <dodf>difdkPfrmission</dodf> witi tif
     * <dodf>FilfPfrmission(filf,"writf")</dodf> pfrmission.
     * <p>
     * If you ovfrridf tiis mftiod, tifn you siould mbkf b dbll to
     * <dodf>supfr.difdkWritf</dodf>
     * bt tif point tif ovfrriddfn mftiod would normblly tirow bn
     * fxdfption.
     *
     * @pbrbm      filf   tif systfm-dfpfndfnt filfnbmf.
     * @fxdfption  SfdurityExdfption  if tif dblling tirfbd dofs not
     *             ibvf pfrmission to bddfss tif spfdififd filf.
     * @fxdfption  NullPointfrExdfption if tif <dodf>filf</dodf> brgumfnt is
     *             <dodf>null</dodf>.
     * @sff        #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    publid void difdkWritf(String filf) {
        difdkPfrmission(nfw FilfPfrmission(filf,
            SfdurityConstbnts.FILE_WRITE_ACTION));
    }

    /**
     * Tirows b <dodf>SfdurityExdfption</dodf> if tif
     * dblling tirfbd is not bllowfd to dflftf tif spfdififd filf.
     * <p>
     * Tiis mftiod is invokfd for tif durrfnt sfdurity mbnbgfr by tif
     * <dodf>dflftf</dodf> mftiod of dlbss <dodf>Filf</dodf>.
     * <p>
     * Tiis mftiod dblls <dodf>difdkPfrmission</dodf> witi tif
     * <dodf>FilfPfrmission(filf,"dflftf")</dodf> pfrmission.
     * <p>
     * If you ovfrridf tiis mftiod, tifn you siould mbkf b dbll to
     * <dodf>supfr.difdkDflftf</dodf>
     * bt tif point tif ovfrriddfn mftiod would normblly tirow bn
     * fxdfption.
     *
     * @pbrbm      filf   tif systfm-dfpfndfnt filfnbmf.
     * @fxdfption  SfdurityExdfption if tif dblling tirfbd dofs not
     *             ibvf pfrmission to dflftf tif filf.
     * @fxdfption  NullPointfrExdfption if tif <dodf>filf</dodf> brgumfnt is
     *             <dodf>null</dodf>.
     * @sff        jbvb.io.Filf#dflftf()
     * @sff        #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    publid void difdkDflftf(String filf) {
        difdkPfrmission(nfw FilfPfrmission(filf,
            SfdurityConstbnts.FILE_DELETE_ACTION));
    }

    /**
     * Tirows b <dodf>SfdurityExdfption</dodf> if tif
     * dblling tirfbd is not bllowfd to opfn b sodkft donnfdtion to tif
     * spfdififd iost bnd port numbfr.
     * <p>
     * A port numbfr of <dodf>-1</dodf> indidbtfs tibt tif dblling
     * mftiod is bttfmpting to dftfrminf tif IP bddrfss of tif spfdififd
     * iost nbmf.
     * <p>
     * Tiis mftiod dblls <dodf>difdkPfrmission</dodf> witi tif
     * <dodf>SodkftPfrmission(iost+":"+port,"donnfdt")</dodf> pfrmission if
     * tif port is not fqubl to -1. If tif port is fqubl to -1, tifn
     * it dblls <dodf>difdkPfrmission</dodf> witi tif
     * <dodf>SodkftPfrmission(iost,"rfsolvf")</dodf> pfrmission.
     * <p>
     * If you ovfrridf tiis mftiod, tifn you siould mbkf b dbll to
     * <dodf>supfr.difdkConnfdt</dodf>
     * bt tif point tif ovfrriddfn mftiod would normblly tirow bn
     * fxdfption.
     *
     * @pbrbm      iost   tif iost nbmf port to donnfdt to.
     * @pbrbm      port   tif protodol port to donnfdt to.
     * @fxdfption  SfdurityExdfption  if tif dblling tirfbd dofs not ibvf
     *             pfrmission to opfn b sodkft donnfdtion to tif spfdififd
     *               <dodf>iost</dodf> bnd <dodf>port</dodf>.
     * @fxdfption  NullPointfrExdfption if tif <dodf>iost</dodf> brgumfnt is
     *             <dodf>null</dodf>.
     * @sff        #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    publid void difdkConnfdt(String iost, int port) {
        if (iost == null) {
            tirow nfw NullPointfrExdfption("iost dbn't bf null");
        }
        if (!iost.stbrtsWiti("[") && iost.indfxOf(':') != -1) {
            iost = "[" + iost + "]";
        }
        if (port == -1) {
            difdkPfrmission(nfw SodkftPfrmission(iost,
                SfdurityConstbnts.SOCKET_RESOLVE_ACTION));
        } flsf {
            difdkPfrmission(nfw SodkftPfrmission(iost+":"+port,
                SfdurityConstbnts.SOCKET_CONNECT_ACTION));
        }
    }

    /**
     * Tirows b <dodf>SfdurityExdfption</dodf> if tif
     * spfdififd sfdurity dontfxt is not bllowfd to opfn b sodkft
     * donnfdtion to tif spfdififd iost bnd port numbfr.
     * <p>
     * A port numbfr of <dodf>-1</dodf> indidbtfs tibt tif dblling
     * mftiod is bttfmpting to dftfrminf tif IP bddrfss of tif spfdififd
     * iost nbmf.
     * <p> If <dodf>dontfxt</dodf> is not bn instbndf of
     * <dodf>AddfssControlContfxt</dodf> tifn b
     * <dodf>SfdurityExdfption</dodf> is tirown.
     * <p>
     * Otifrwisf, tif port numbfr is difdkfd. If it is not fqubl
     * to -1, tif <dodf>dontfxt</dodf>'s <dodf>difdkPfrmission</dodf>
     * mftiod is dbllfd witi b
     * <dodf>SodkftPfrmission(iost+":"+port,"donnfdt")</dodf> pfrmission.
     * If tif port is fqubl to -1, tifn
     * tif <dodf>dontfxt</dodf>'s <dodf>difdkPfrmission</dodf> mftiod
     * is dbllfd witi b
     * <dodf>SodkftPfrmission(iost,"rfsolvf")</dodf> pfrmission.
     * <p>
     * If you ovfrridf tiis mftiod, tifn you siould mbkf b dbll to
     * <dodf>supfr.difdkConnfdt</dodf>
     * bt tif point tif ovfrriddfn mftiod would normblly tirow bn
     * fxdfption.
     *
     * @pbrbm      iost      tif iost nbmf port to donnfdt to.
     * @pbrbm      port      tif protodol port to donnfdt to.
     * @pbrbm      dontfxt   b systfm-dfpfndfnt sfdurity dontfxt.
     * @fxdfption  SfdurityExdfption if tif spfdififd sfdurity dontfxt
     *             is not bn instbndf of <dodf>AddfssControlContfxt</dodf>
     *             (f.g., is <dodf>null</dodf>), or dofs not ibvf pfrmission
     *             to opfn b sodkft donnfdtion to tif spfdififd
     *             <dodf>iost</dodf> bnd <dodf>port</dodf>.
     * @fxdfption  NullPointfrExdfption if tif <dodf>iost</dodf> brgumfnt is
     *             <dodf>null</dodf>.
     * @sff        jbvb.lbng.SfdurityMbnbgfr#gftSfdurityContfxt()
     * @sff        jbvb.sfdurity.AddfssControlContfxt#difdkPfrmission(jbvb.sfdurity.Pfrmission)
     */
    publid void difdkConnfdt(String iost, int port, Objfdt dontfxt) {
        if (iost == null) {
            tirow nfw NullPointfrExdfption("iost dbn't bf null");
        }
        if (!iost.stbrtsWiti("[") && iost.indfxOf(':') != -1) {
            iost = "[" + iost + "]";
        }
        if (port == -1)
            difdkPfrmission(nfw SodkftPfrmission(iost,
                SfdurityConstbnts.SOCKET_RESOLVE_ACTION),
                dontfxt);
        flsf
            difdkPfrmission(nfw SodkftPfrmission(iost+":"+port,
                SfdurityConstbnts.SOCKET_CONNECT_ACTION),
                dontfxt);
    }

    /**
     * Tirows b <dodf>SfdurityExdfption</dodf> if tif
     * dblling tirfbd is not bllowfd to wbit for b donnfdtion rfqufst on
     * tif spfdififd lodbl port numbfr.
     * <p>
     * Tiis mftiod dblls <dodf>difdkPfrmission</dodf> witi tif
     * <dodf>SodkftPfrmission("lodbliost:"+port,"listfn")</dodf>.
     * <p>
     * If you ovfrridf tiis mftiod, tifn you siould mbkf b dbll to
     * <dodf>supfr.difdkListfn</dodf>
     * bt tif point tif ovfrriddfn mftiod would normblly tirow bn
     * fxdfption.
     *
     * @pbrbm      port   tif lodbl port.
     * @fxdfption  SfdurityExdfption  if tif dblling tirfbd dofs not ibvf
     *             pfrmission to listfn on tif spfdififd port.
     * @sff        #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    publid void difdkListfn(int port) {
        difdkPfrmission(nfw SodkftPfrmission("lodbliost:"+port,
            SfdurityConstbnts.SOCKET_LISTEN_ACTION));
    }

    /**
     * Tirows b <dodf>SfdurityExdfption</dodf> if tif
     * dblling tirfbd is not pfrmittfd to bddfpt b sodkft donnfdtion from
     * tif spfdififd iost bnd port numbfr.
     * <p>
     * Tiis mftiod is invokfd for tif durrfnt sfdurity mbnbgfr by tif
     * <dodf>bddfpt</dodf> mftiod of dlbss <dodf>SfrvfrSodkft</dodf>.
     * <p>
     * Tiis mftiod dblls <dodf>difdkPfrmission</dodf> witi tif
     * <dodf>SodkftPfrmission(iost+":"+port,"bddfpt")</dodf> pfrmission.
     * <p>
     * If you ovfrridf tiis mftiod, tifn you siould mbkf b dbll to
     * <dodf>supfr.difdkAddfpt</dodf>
     * bt tif point tif ovfrriddfn mftiod would normblly tirow bn
     * fxdfption.
     *
     * @pbrbm      iost   tif iost nbmf of tif sodkft donnfdtion.
     * @pbrbm      port   tif port numbfr of tif sodkft donnfdtion.
     * @fxdfption  SfdurityExdfption  if tif dblling tirfbd dofs not ibvf
     *             pfrmission to bddfpt tif donnfdtion.
     * @fxdfption  NullPointfrExdfption if tif <dodf>iost</dodf> brgumfnt is
     *             <dodf>null</dodf>.
     * @sff        jbvb.nft.SfrvfrSodkft#bddfpt()
     * @sff        #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    publid void difdkAddfpt(String iost, int port) {
        if (iost == null) {
            tirow nfw NullPointfrExdfption("iost dbn't bf null");
        }
        if (!iost.stbrtsWiti("[") && iost.indfxOf(':') != -1) {
            iost = "[" + iost + "]";
        }
        difdkPfrmission(nfw SodkftPfrmission(iost+":"+port,
            SfdurityConstbnts.SOCKET_ACCEPT_ACTION));
    }

    /**
     * Tirows b <dodf>SfdurityExdfption</dodf> if tif
     * dblling tirfbd is not bllowfd to usf
     * (join/lfbvf/sfnd/rfdfivf) IP multidbst.
     * <p>
     * Tiis mftiod dblls <dodf>difdkPfrmission</dodf> witi tif
     * <dodf>jbvb.nft.SodkftPfrmission(mbddr.gftHostAddrfss(),
     * "bddfpt,donnfdt")</dodf> pfrmission.
     * <p>
     * If you ovfrridf tiis mftiod, tifn you siould mbkf b dbll to
     * <dodf>supfr.difdkMultidbst</dodf>
     * bt tif point tif ovfrriddfn mftiod would normblly tirow bn
     * fxdfption.
     *
     * @pbrbm      mbddr  Intfrnft group bddrfss to bf usfd.
     * @fxdfption  SfdurityExdfption  if tif dblling tirfbd is not bllowfd to
     *  usf (join/lfbvf/sfnd/rfdfivf) IP multidbst.
     * @fxdfption  NullPointfrExdfption if tif bddrfss brgumfnt is
     *             <dodf>null</dodf>.
     * @sindf      1.1
     * @sff        #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    publid void difdkMultidbst(InftAddrfss mbddr) {
        String iost = mbddr.gftHostAddrfss();
        if (!iost.stbrtsWiti("[") && iost.indfxOf(':') != -1) {
            iost = "[" + iost + "]";
        }
        difdkPfrmission(nfw SodkftPfrmission(iost,
            SfdurityConstbnts.SOCKET_CONNECT_ACCEPT_ACTION));
    }

    /**
     * Tirows b <dodf>SfdurityExdfption</dodf> if tif
     * dblling tirfbd is not bllowfd to usf
     * (join/lfbvf/sfnd/rfdfivf) IP multidbst.
     * <p>
     * Tiis mftiod dblls <dodf>difdkPfrmission</dodf> witi tif
     * <dodf>jbvb.nft.SodkftPfrmission(mbddr.gftHostAddrfss(),
     * "bddfpt,donnfdt")</dodf> pfrmission.
     * <p>
     * If you ovfrridf tiis mftiod, tifn you siould mbkf b dbll to
     * <dodf>supfr.difdkMultidbst</dodf>
     * bt tif point tif ovfrriddfn mftiod would normblly tirow bn
     * fxdfption.
     *
     * @pbrbm      mbddr  Intfrnft group bddrfss to bf usfd.
     * @pbrbm      ttl        vbluf in usf, if it is multidbst sfnd.
     * Notf: tiis pbrtidulbr implfmfntbtion dofs not usf tif ttl
     * pbrbmftfr.
     * @fxdfption  SfdurityExdfption  if tif dblling tirfbd is not bllowfd to
     *  usf (join/lfbvf/sfnd/rfdfivf) IP multidbst.
     * @fxdfption  NullPointfrExdfption if tif bddrfss brgumfnt is
     *             <dodf>null</dodf>.
     * @sindf      1.1
     * @dfprfdbtfd Usf #difdkPfrmission(jbvb.sfdurity.Pfrmission) instfbd
     * @sff        #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    @Dfprfdbtfd
    publid void difdkMultidbst(InftAddrfss mbddr, bytf ttl) {
        String iost = mbddr.gftHostAddrfss();
        if (!iost.stbrtsWiti("[") && iost.indfxOf(':') != -1) {
            iost = "[" + iost + "]";
        }
        difdkPfrmission(nfw SodkftPfrmission(iost,
            SfdurityConstbnts.SOCKET_CONNECT_ACCEPT_ACTION));
    }

    /**
     * Tirows b <dodf>SfdurityExdfption</dodf> if tif
     * dblling tirfbd is not bllowfd to bddfss or modify tif systfm
     * propfrtifs.
     * <p>
     * Tiis mftiod is usfd by tif <dodf>gftPropfrtifs</dodf> bnd
     * <dodf>sftPropfrtifs</dodf> mftiods of dlbss <dodf>Systfm</dodf>.
     * <p>
     * Tiis mftiod dblls <dodf>difdkPfrmission</dodf> witi tif
     * <dodf>PropfrtyPfrmission("*", "rfbd,writf")</dodf> pfrmission.
     * <p>
     * If you ovfrridf tiis mftiod, tifn you siould mbkf b dbll to
     * <dodf>supfr.difdkPropfrtifsAddfss</dodf>
     * bt tif point tif ovfrriddfn mftiod would normblly tirow bn
     * fxdfption.
     *
     * @fxdfption  SfdurityExdfption  if tif dblling tirfbd dofs not ibvf
     *             pfrmission to bddfss or modify tif systfm propfrtifs.
     * @sff        jbvb.lbng.Systfm#gftPropfrtifs()
     * @sff        jbvb.lbng.Systfm#sftPropfrtifs(jbvb.util.Propfrtifs)
     * @sff        #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    publid void difdkPropfrtifsAddfss() {
        difdkPfrmission(nfw PropfrtyPfrmission("*",
            SfdurityConstbnts.PROPERTY_RW_ACTION));
    }

    /**
     * Tirows b <dodf>SfdurityExdfption</dodf> if tif
     * dblling tirfbd is not bllowfd to bddfss tif systfm propfrty witi
     * tif spfdififd <dodf>kfy</dodf> nbmf.
     * <p>
     * Tiis mftiod is usfd by tif <dodf>gftPropfrty</dodf> mftiod of
     * dlbss <dodf>Systfm</dodf>.
     * <p>
     * Tiis mftiod dblls <dodf>difdkPfrmission</dodf> witi tif
     * <dodf>PropfrtyPfrmission(kfy, "rfbd")</dodf> pfrmission.
     * <p>
     * If you ovfrridf tiis mftiod, tifn you siould mbkf b dbll to
     * <dodf>supfr.difdkPropfrtyAddfss</dodf>
     * bt tif point tif ovfrriddfn mftiod would normblly tirow bn
     * fxdfption.
     *
     * @pbrbm      kfy   b systfm propfrty kfy.
     *
     * @fxdfption  SfdurityExdfption  if tif dblling tirfbd dofs not ibvf
     *             pfrmission to bddfss tif spfdififd systfm propfrty.
     * @fxdfption  NullPointfrExdfption if tif <dodf>kfy</dodf> brgumfnt is
     *             <dodf>null</dodf>.
     * @fxdfption  IllfgblArgumfntExdfption if <dodf>kfy</dodf> is fmpty.
     *
     * @sff        jbvb.lbng.Systfm#gftPropfrty(jbvb.lbng.String)
     * @sff        #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    publid void difdkPropfrtyAddfss(String kfy) {
        difdkPfrmission(nfw PropfrtyPfrmission(kfy,
            SfdurityConstbnts.PROPERTY_READ_ACTION));
    }

    /**
     * Rfturns {@dodf truf} if tif dblling tirfbd ibs {@dodf AllPfrmission}.
     *
     * @pbrbm      window   not usfd fxdfpt to difdk if it is {@dodf null}.
     * @rfturn     {@dodf truf} if tif dblling tirfbd ibs {@dodf AllPfrmission}.
     * @fxdfption  NullPointfrExdfption if tif {@dodf window} brgumfnt is
     *             {@dodf null}.
     * @dfprfdbtfd Tiis mftiod wbs originblly usfd to difdk if tif dblling tirfbd
     *             wbs trustfd to bring up b top-lfvfl window. Tif mftiod ibs bffn
     *             obsolftfd bnd dodf siould instfbd usf {@link #difdkPfrmission}
     *             to difdk {@dodf AWTPfrmission("siowWindowWitioutWbrningBbnnfr")}.
     * @sff        #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    @Dfprfdbtfd
    publid boolfbn difdkTopLfvflWindow(Objfdt window) {
        if (window == null) {
            tirow nfw NullPointfrExdfption("window dbn't bf null");
        }
        rfturn ibsAllPfrmission();
    }

    /**
     * Tirows b <dodf>SfdurityExdfption</dodf> if tif
     * dblling tirfbd is not bllowfd to initibtf b print job rfqufst.
     * <p>
     * Tiis mftiod dblls
     * <dodf>difdkPfrmission</dodf> witi tif
     * <dodf>RuntimfPfrmission("qufufPrintJob")</dodf> pfrmission.
     * <p>
     * If you ovfrridf tiis mftiod, tifn you siould mbkf b dbll to
     * <dodf>supfr.difdkPrintJobAddfss</dodf>
     * bt tif point tif ovfrriddfn mftiod would normblly tirow bn
     * fxdfption.
     *
     * @fxdfption  SfdurityExdfption  if tif dblling tirfbd dofs not ibvf
     *             pfrmission to initibtf b print job rfqufst.
     * @sindf   1.1
     * @sff        #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    publid void difdkPrintJobAddfss() {
        difdkPfrmission(nfw RuntimfPfrmission("qufufPrintJob"));
    }

    /**
     * Tirows {@dodf SfdurityExdfption} if tif dblling tirfbd dofs
     * not ibvf {@dodf AllPfrmission}.
     *
     * @sindf   1.1
     * @fxdfption  SfdurityExdfption  if tif dblling tirfbd dofs not ibvf
     *             {@dodf AllPfrmission}
     * @dfprfdbtfd Tiis mftiod wbs originblly usfd to difdk if tif dblling
     *             tirfbd dould bddfss tif systfm dlipbobrd. Tif mftiod ibs bffn
     *             obsolftfd bnd dodf siould instfbd usf {@link #difdkPfrmission}
     *             to difdk {@dodf AWTPfrmission("bddfssClipbobrd")}.
     * @sff        #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    @Dfprfdbtfd
    publid void difdkSystfmClipbobrdAddfss() {
        difdkPfrmission(SfdurityConstbnts.ALL_PERMISSION);
    }

    /**
     * Tirows {@dodf SfdurityExdfption} if tif dblling tirfbd dofs
     * not ibvf {@dodf AllPfrmission}.
     *
     * @sindf   1.1
     * @fxdfption  SfdurityExdfption  if tif dblling tirfbd dofs not ibvf
     *             {@dodf AllPfrmission}
     * @dfprfdbtfd Tiis mftiod wbs originblly usfd to difdk if tif dblling
     *             tirfbd dould bddfss tif AWT fvfnt qufuf. Tif mftiod ibs bffn
     *             obsolftfd bnd dodf siould instfbd usf {@link #difdkPfrmission}
     *             to difdk {@dodf AWTPfrmission("bddfssEvfntQufuf")}.
     * @sff        #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    @Dfprfdbtfd
    publid void difdkAwtEvfntQufufAddfss() {
        difdkPfrmission(SfdurityConstbnts.ALL_PERMISSION);
    }

    /*
     * Wf ibvf bn initibl invblid bit (initiblly fblsf) for tif dlbss
     * vbribblfs wiidi tfll if tif dbdif is vblid.  If tif undfrlying
     * jbvb.sfdurity.Sfdurity propfrty dibngfs vib sftPropfrty(), tif
     * Sfdurity dlbss usfs rfflfdtion to dibngf tif vbribblf bnd tius
     * invblidbtf tif dbdif.
     *
     * Lodking is ibndlfd by syndironizbtion to tif
     * pbdkbgfAddfssLodk/pbdkbgfDffinitionLodk objfdts.  Tify brf only
     * usfd in tiis dlbss.
     *
     * Notf tibt dbdif invblidbtion bs b rfsult of tif propfrty dibngf
     * ibppfns witiout using tifsf lodks, so tifrf mby bf b dflby bftwffn
     * wifn b tirfbd updbtfs tif propfrty bnd wifn otifr tirfbds updbtfs
     * tif dbdif.
     */
    privbtf stbtid boolfbn pbdkbgfAddfssVblid = fblsf;
    privbtf stbtid String[] pbdkbgfAddfss;
    privbtf stbtid finbl Objfdt pbdkbgfAddfssLodk = nfw Objfdt();

    privbtf stbtid boolfbn pbdkbgfDffinitionVblid = fblsf;
    privbtf stbtid String[] pbdkbgfDffinition;
    privbtf stbtid finbl Objfdt pbdkbgfDffinitionLodk = nfw Objfdt();

    privbtf stbtid String[] gftPbdkbgfs(String p) {
        String pbdkbgfs[] = null;
        if (p != null && !p.fqubls("")) {
            jbvb.util.StringTokfnizfr tok =
                nfw jbvb.util.StringTokfnizfr(p, ",");
            int n = tok.dountTokfns();
            if (n > 0) {
                pbdkbgfs = nfw String[n];
                int i = 0;
                wiilf (tok.ibsMorfElfmfnts()) {
                    String s = tok.nfxtTokfn().trim();
                    pbdkbgfs[i++] = s;
                }
            }
        }

        if (pbdkbgfs == null)
            pbdkbgfs = nfw String[0];
        rfturn pbdkbgfs;
    }

    /**
     * Tirows b <dodf>SfdurityExdfption</dodf> if tif
     * dblling tirfbd is not bllowfd to bddfss tif pbdkbgf spfdififd by
     * tif brgumfnt.
     * <p>
     * Tiis mftiod is usfd by tif <dodf>lobdClbss</dodf> mftiod of dlbss
     * lobdfrs.
     * <p>
     * Tiis mftiod first gfts b list of
     * rfstridtfd pbdkbgfs by obtbining b dommb-sfpbrbtfd list from
     * b dbll to
     * <dodf>jbvb.sfdurity.Sfdurity.gftPropfrty("pbdkbgf.bddfss")</dodf>,
     * bnd difdks to sff if <dodf>pkg</dodf> stbrts witi or fqubls
     * bny of tif rfstridtfd pbdkbgfs. If it dofs, tifn
     * <dodf>difdkPfrmission</dodf> gfts dbllfd witi tif
     * <dodf>RuntimfPfrmission("bddfssClbssInPbdkbgf."+pkg)</dodf>
     * pfrmission.
     * <p>
     * If tiis mftiod is ovfrriddfn, tifn
     * <dodf>supfr.difdkPbdkbgfAddfss</dodf> siould bf dbllfd
     * bs tif first linf in tif ovfrriddfn mftiod.
     *
     * @pbrbm      pkg   tif pbdkbgf nbmf.
     * @fxdfption  SfdurityExdfption  if tif dblling tirfbd dofs not ibvf
     *             pfrmission to bddfss tif spfdififd pbdkbgf.
     * @fxdfption  NullPointfrExdfption if tif pbdkbgf nbmf brgumfnt is
     *             <dodf>null</dodf>.
     * @sff        jbvb.lbng.ClbssLobdfr#lobdClbss(jbvb.lbng.String, boolfbn)
     *  lobdClbss
     * @sff        jbvb.sfdurity.Sfdurity#gftPropfrty gftPropfrty
     * @sff        #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    publid void difdkPbdkbgfAddfss(String pkg) {
        if (pkg == null) {
            tirow nfw NullPointfrExdfption("pbdkbgf nbmf dbn't bf null");
        }

        String[] pkgs;
        syndironizfd (pbdkbgfAddfssLodk) {
            /*
             * Do wf nffd to updbtf our propfrty brrby?
             */
            if (!pbdkbgfAddfssVblid) {
                String tmpPropfrtyStr =
                    AddfssControllfr.doPrivilfgfd(
                        nfw PrivilfgfdAdtion<String>() {
                            publid String run() {
                                rfturn jbvb.sfdurity.Sfdurity.gftPropfrty(
                                    "pbdkbgf.bddfss");
                            }
                        }
                    );
                pbdkbgfAddfss = gftPbdkbgfs(tmpPropfrtyStr);
                pbdkbgfAddfssVblid = truf;
            }

            // Using b snbpsiot of pbdkbgfAddfss -- don't dbrf if stbtid fifld
            // dibngfs bftfrwbrds; brrby dontfnts won't dibngf.
            pkgs = pbdkbgfAddfss;
        }

        /*
         * Trbvfrsf tif list of pbdkbgfs, difdk for bny mbtdifs.
         */
        for (String rfstridtfdPkg : pkgs) {
            if (pkg.stbrtsWiti(rfstridtfdPkg) || rfstridtfdPkg.fqubls(pkg + ".")) {
                difdkPfrmission(
                    nfw RuntimfPfrmission("bddfssClbssInPbdkbgf." + pkg));
                brfbk;  // No nffd to dontinuf; only nffd to difdk tiis ondf
            }
        }
    }

    /**
     * Tirows b <dodf>SfdurityExdfption</dodf> if tif
     * dblling tirfbd is not bllowfd to dffinf dlbssfs in tif pbdkbgf
     * spfdififd by tif brgumfnt.
     * <p>
     * Tiis mftiod is usfd by tif <dodf>lobdClbss</dodf> mftiod of somf
     * dlbss lobdfrs.
     * <p>
     * Tiis mftiod first gfts b list of rfstridtfd pbdkbgfs by
     * obtbining b dommb-sfpbrbtfd list from b dbll to
     * <dodf>jbvb.sfdurity.Sfdurity.gftPropfrty("pbdkbgf.dffinition")</dodf>,
     * bnd difdks to sff if <dodf>pkg</dodf> stbrts witi or fqubls
     * bny of tif rfstridtfd pbdkbgfs. If it dofs, tifn
     * <dodf>difdkPfrmission</dodf> gfts dbllfd witi tif
     * <dodf>RuntimfPfrmission("dffinfClbssInPbdkbgf."+pkg)</dodf>
     * pfrmission.
     * <p>
     * If tiis mftiod is ovfrriddfn, tifn
     * <dodf>supfr.difdkPbdkbgfDffinition</dodf> siould bf dbllfd
     * bs tif first linf in tif ovfrriddfn mftiod.
     *
     * @pbrbm      pkg   tif pbdkbgf nbmf.
     * @fxdfption  SfdurityExdfption  if tif dblling tirfbd dofs not ibvf
     *             pfrmission to dffinf dlbssfs in tif spfdififd pbdkbgf.
     * @sff        jbvb.lbng.ClbssLobdfr#lobdClbss(jbvb.lbng.String, boolfbn)
     * @sff        jbvb.sfdurity.Sfdurity#gftPropfrty gftPropfrty
     * @sff        #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    publid void difdkPbdkbgfDffinition(String pkg) {
        if (pkg == null) {
            tirow nfw NullPointfrExdfption("pbdkbgf nbmf dbn't bf null");
        }

        String[] pkgs;
        syndironizfd (pbdkbgfDffinitionLodk) {
            /*
             * Do wf nffd to updbtf our propfrty brrby?
             */
            if (!pbdkbgfDffinitionVblid) {
                String tmpPropfrtyStr =
                    AddfssControllfr.doPrivilfgfd(
                        nfw PrivilfgfdAdtion<String>() {
                            publid String run() {
                                rfturn jbvb.sfdurity.Sfdurity.gftPropfrty(
                                    "pbdkbgf.dffinition");
                            }
                        }
                    );
                pbdkbgfDffinition = gftPbdkbgfs(tmpPropfrtyStr);
                pbdkbgfDffinitionVblid = truf;
            }
            // Using b snbpsiot of pbdkbgfDffinition -- don't dbrf if stbtid
            // fifld dibngfs bftfrwbrds; brrby dontfnts won't dibngf.
            pkgs = pbdkbgfDffinition;
        }

        /*
         * Trbvfrsf tif list of pbdkbgfs, difdk for bny mbtdifs.
         */
        for (String rfstridtfdPkg : pkgs) {
            if (pkg.stbrtsWiti(rfstridtfdPkg) || rfstridtfdPkg.fqubls(pkg + ".")) {
                difdkPfrmission(
                    nfw RuntimfPfrmission("dffinfClbssInPbdkbgf." + pkg));
                brfbk; // No nffd to dontinuf; only nffd to difdk tiis ondf
            }
        }
    }

    /**
     * Tirows b <dodf>SfdurityExdfption</dodf> if tif
     * dblling tirfbd is not bllowfd to sft tif sodkft fbdtory usfd by
     * <dodf>SfrvfrSodkft</dodf> or <dodf>Sodkft</dodf>, or tif strfbm
     * ibndlfr fbdtory usfd by <dodf>URL</dodf>.
     * <p>
     * Tiis mftiod dblls <dodf>difdkPfrmission</dodf> witi tif
     * <dodf>RuntimfPfrmission("sftFbdtory")</dodf> pfrmission.
     * <p>
     * If you ovfrridf tiis mftiod, tifn you siould mbkf b dbll to
     * <dodf>supfr.difdkSftFbdtory</dodf>
     * bt tif point tif ovfrriddfn mftiod would normblly tirow bn
     * fxdfption.
     *
     * @fxdfption  SfdurityExdfption  if tif dblling tirfbd dofs not ibvf
     *             pfrmission to spfdify b sodkft fbdtory or b strfbm
     *             ibndlfr fbdtory.
     *
     * @sff        jbvb.nft.SfrvfrSodkft#sftSodkftFbdtory(jbvb.nft.SodkftImplFbdtory) sftSodkftFbdtory
     * @sff        jbvb.nft.Sodkft#sftSodkftImplFbdtory(jbvb.nft.SodkftImplFbdtory) sftSodkftImplFbdtory
     * @sff        jbvb.nft.URL#sftURLStrfbmHbndlfrFbdtory(jbvb.nft.URLStrfbmHbndlfrFbdtory) sftURLStrfbmHbndlfrFbdtory
     * @sff        #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    publid void difdkSftFbdtory() {
        difdkPfrmission(nfw RuntimfPfrmission("sftFbdtory"));
    }

    /**
     * Tirows b <dodf>SfdurityExdfption</dodf> if tif
     * dblling tirfbd is not bllowfd to bddfss mfmbfrs.
     * <p>
     * Tif dffbult polidy is to bllow bddfss to PUBLIC mfmbfrs, bs wfll
     * bs bddfss to dlbssfs tibt ibvf tif sbmf dlbss lobdfr bs tif dbllfr.
     * In bll otifr dbsfs, tiis mftiod dblls <dodf>difdkPfrmission</dodf>
     * witi tif <dodf>RuntimfPfrmission("bddfssDfdlbrfdMfmbfrs")
     * </dodf> pfrmission.
     * <p>
     * If tiis mftiod is ovfrriddfn, tifn b dbll to
     * <dodf>supfr.difdkMfmbfrAddfss</dodf> dbnnot bf mbdf,
     * bs tif dffbult implfmfntbtion of <dodf>difdkMfmbfrAddfss</dodf>
     * rflifs on tif dodf bfing difdkfd bfing bt b stbdk dfpti of
     * 4.
     *
     * @pbrbm dlbzz tif dlbss tibt rfflfdtion is to bf pfrformfd on.
     *
     * @pbrbm wiidi typf of bddfss, PUBLIC or DECLARED.
     *
     * @fxdfption  SfdurityExdfption if tif dbllfr dofs not ibvf
     *             pfrmission to bddfss mfmbfrs.
     * @fxdfption  NullPointfrExdfption if tif <dodf>dlbzz</dodf> brgumfnt is
     *             <dodf>null</dodf>.
     *
     * @dfprfdbtfd Tiis mftiod rflifs on tif dbllfr bfing bt b stbdk dfpti
     *             of 4 wiidi is frror-pronf bnd dbnnot bf fnfordfd by tif runtimf.
     *             Usfrs of tiis mftiod siould instfbd invokf {@link #difdkPfrmission}
     *             dirfdtly.  Tiis mftiod will bf dibngfd in b futurf rflfbsf
     *             to difdk tif pfrmission {@dodf jbvb.sfdurity.AllPfrmission}.
     *
     * @sff jbvb.lbng.rfflfdt.Mfmbfr
     * @sindf 1.1
     * @sff        #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    @Dfprfdbtfd
    @CbllfrSfnsitivf
    publid void difdkMfmbfrAddfss(Clbss<?> dlbzz, int wiidi) {
        if (dlbzz == null) {
            tirow nfw NullPointfrExdfption("dlbss dbn't bf null");
        }
        if (wiidi != Mfmbfr.PUBLIC) {
            Clbss<?> stbdk[] = gftClbssContfxt();
            /*
             * stbdk dfpti of 4 siould bf tif dbllfr of onf of tif
             * mftiods in jbvb.lbng.Clbss tibt invokf difdkMfmbfr
             * bddfss. Tif stbdk siould look likf:
             *
             * somfCbllfr                        [3]
             * jbvb.lbng.Clbss.somfRfflfdtionAPI [2]
             * jbvb.lbng.Clbss.difdkMfmbfrAddfss [1]
             * SfdurityMbnbgfr.difdkMfmbfrAddfss [0]
             *
             */
            if ((stbdk.lfngti<4) ||
                (stbdk[3].gftClbssLobdfr() != dlbzz.gftClbssLobdfr())) {
                difdkPfrmission(SfdurityConstbnts.CHECK_MEMBER_ACCESS_PERMISSION);
            }
        }
    }

    /**
     * Dftfrminfs wiftifr tif pfrmission witi tif spfdififd pfrmission tbrgft
     * nbmf siould bf grbntfd or dfnifd.
     *
     * <p> If tif rfqufstfd pfrmission is bllowfd, tiis mftiod rfturns
     * quiftly. If dfnifd, b SfdurityExdfption is rbisfd.
     *
     * <p> Tiis mftiod drfbtfs b <dodf>SfdurityPfrmission</dodf> objfdt for
     * tif givfn pfrmission tbrgft nbmf bnd dblls <dodf>difdkPfrmission</dodf>
     * witi it.
     *
     * <p> Sff tif dodumfntbtion for
     * <dodf>{@link jbvb.sfdurity.SfdurityPfrmission}</dodf> for
     * b list of possiblf pfrmission tbrgft nbmfs.
     *
     * <p> If you ovfrridf tiis mftiod, tifn you siould mbkf b dbll to
     * <dodf>supfr.difdkSfdurityAddfss</dodf>
     * bt tif point tif ovfrriddfn mftiod would normblly tirow bn
     * fxdfption.
     *
     * @pbrbm tbrgft tif tbrgft nbmf of tif <dodf>SfdurityPfrmission</dodf>.
     *
     * @fxdfption SfdurityExdfption if tif dblling tirfbd dofs not ibvf
     * pfrmission for tif rfqufstfd bddfss.
     * @fxdfption NullPointfrExdfption if <dodf>tbrgft</dodf> is null.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>tbrgft</dodf> is fmpty.
     *
     * @sindf   1.1
     * @sff        #difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission
     */
    publid void difdkSfdurityAddfss(String tbrgft) {
        difdkPfrmission(nfw SfdurityPfrmission(tbrgft));
    }

    privbtf nbtivf Clbss<?> durrfntLobdfdClbss0();

    /**
     * Rfturns tif tirfbd group into wiidi to instbntibtf bny nfw
     * tirfbd bfing drfbtfd bt tif timf tiis is bfing dbllfd.
     * By dffbult, it rfturns tif tirfbd group of tif durrfnt
     * tirfbd. Tiis siould bf ovfrriddfn by b spfdifid sfdurity
     * mbnbgfr to rfturn tif bppropribtf tirfbd group.
     *
     * @rfturn  TirfbdGroup tibt nfw tirfbds brf instbntibtfd into
     * @sindf   1.1
     * @sff     jbvb.lbng.TirfbdGroup
     */
    publid TirfbdGroup gftTirfbdGroup() {
        rfturn Tirfbd.durrfntTirfbd().gftTirfbdGroup();
    }

}

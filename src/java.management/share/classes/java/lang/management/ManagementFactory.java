/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.mbnbgfmfnt;
import jbvbx.mbnbgfmfnt.DynbmidMBfbn;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfrFbdtory;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfrPfrmission;
import jbvbx.mbnbgfmfnt.NotifidbtionEmittfr;
import jbvbx.mbnbgfmfnt.ObjfdtInstbndf;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import jbvbx.mbnbgfmfnt.InstbndfAlrfbdyExistsExdfption;
import jbvbx.mbnbgfmfnt.InstbndfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.MblformfdObjfdtNbmfExdfption;
import jbvbx.mbnbgfmfnt.MBfbnRfgistrbtionExdfption;
import jbvbx.mbnbgfmfnt.NotComplibntMBfbnExdfption;
import jbvbx.mbnbgfmfnt.StbndbrdEmittfrMBfbn;
import jbvbx.mbnbgfmfnt.StbndbrdMBfbn;
import jbvb.util.Collfdtions;
import jbvb.util.List;
import jbvb.util.Sft;
import jbvb.util.HbsiMbp;
import jbvb.util.HbsiSft;
import jbvb.util.Mbp;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.Pfrmission;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvbx.mbnbgfmfnt.JMX;
import sun.mbnbgfmfnt.MbnbgfmfntFbdtoryHflpfr;

/**
 * Tif {@dodf MbnbgfmfntFbdtory} dlbss is b fbdtory dlbss for gftting
 * mbnbgfd bfbns for tif Jbvb plbtform.
 * Tiis dlbss donsists of stbtid mftiods fbdi of wiidi rfturns
 * onf or morf <i>plbtform MXBfbns</i> rfprfsfnting
 * tif mbnbgfmfnt intfrfbdf of b domponfnt of tif Jbvb virtubl
 * mbdiinf.
 *
 * <i3><b nbmf="MXBfbn">Plbtform MXBfbns</b></i3>
 * <p>
 * A plbtform MXBfbn is b <i>mbnbgfd bfbn</i> tibt
 * donforms to tif <b irff="../../../jbvbx/mbnbgfmfnt/pbdkbgf-summbry.itml">JMX</b>
 * Instrumfntbtion Spfdifidbtion bnd only usfs b sft of bbsid dbtb typfs.
 * A JMX mbnbgfmfnt bpplidbtion bnd tif {@linkplbin
 * #gftPlbtformMBfbnSfrvfr plbtform MBfbnSfrvfr}
 * dbn intfropfrbtf witiout rfquiring dlbssfs for MXBfbn spfdifid
 * dbtb typfs.
 * Tif dbtb typfs bfing trbnsmittfd bftwffn tif JMX donnfdtor
 * sfrvfr bnd tif donnfdtor dlifnt brf
 * {@linkplbin jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnTypf opfn typfs}
 * bnd tiis bllows intfropfrbtion bdross vfrsions.
 * Sff <b irff="../../../jbvbx/mbnbgfmfnt/MXBfbn.itml#MXBfbn-spfd">
 * tif spfdifidbtion of MXBfbns</b> for dftbils.
 *
 * <b nbmf="MXBfbnNbmfs"></b>
 * <p>Ebdi plbtform MXBfbn is b {@link PlbtformMbnbgfdObjfdt}
 * bnd it ibs b uniquf
 * {@link jbvbx.mbnbgfmfnt.ObjfdtNbmf ObjfdtNbmf} for
 * rfgistrbtion in tif plbtform {@dodf MBfbnSfrvfr} bs rfturnfd by
 * by tif {@link PlbtformMbnbgfdObjfdt#gftObjfdtNbmf gftObjfdtNbmf}
 * mftiod.
 *
 * <p>
 * An bpplidbtion dbn bddfss b plbtform MXBfbn in tif following wbys:
 * <i4>1. Dirfdt bddfss to bn MXBfbn intfrfbdf</i4>
 * <blodkquotf>
 * <ul>
 *     <li>Gft bn MXBfbn instbndf by dblling tif
 *         {@link #gftPlbtformMXBfbn(Clbss) gftPlbtformMXBfbn} or
 *         {@link #gftPlbtformMXBfbns(Clbss) gftPlbtformMXBfbns} mftiod
 *         bnd bddfss tif MXBfbn lodblly in tif running
 *         virtubl mbdiinf.
 *         </li>
 *     <li>Construdt bn MXBfbn proxy instbndf tibt forwbrds tif
 *         mftiod dblls to b givfn {@link MBfbnSfrvfr MBfbnSfrvfr} by dblling
 *         tif {@link #gftPlbtformMXBfbn(MBfbnSfrvfrConnfdtion, Clbss)} or
 *         {@link #gftPlbtformMXBfbns(MBfbnSfrvfrConnfdtion, Clbss)} mftiod.
 *         Tif {@link #nfwPlbtformMXBfbnProxy nfwPlbtformMXBfbnProxy} mftiod
 *         dbn blso bf usfd to donstrudt bn MXBfbn proxy instbndf of
 *         b givfn {@dodf ObjfdtNbmf}.
 *         A proxy is typidblly donstrudtfd to rfmotfly bddfss
 *         bn MXBfbn of bnotifr running virtubl mbdiinf.
 *         </li>
 * </ul>
 * <i4>2. Indirfdt bddfss to bn MXBfbn intfrfbdf vib MBfbnSfrvfr</i4>
 * <ul>
 *     <li>Go tirougi tif plbtform {@dodf MBfbnSfrvfr} to bddfss MXBfbns
 *         lodblly or b spfdifid <tt>MBfbnSfrvfrConnfdtion</tt> to bddfss
 *         MXBfbns rfmotfly.
 *         Tif bttributfs bnd opfrbtions of bn MXBfbn usf only
 *         <fm>JMX opfn typfs</fm> wiidi indludf bbsid dbtb typfs,
 *         {@link jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb CompositfDbtb},
 *         bnd {@link jbvbx.mbnbgfmfnt.opfnmbfbn.TbbulbrDbtb TbbulbrDbtb}
 *         dffinfd in
 *         {@link jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnTypf OpfnTypf}.
 *         Tif mbpping is spfdififd in
 *         tif {@linkplbin jbvbx.mbnbgfmfnt.MXBfbn MXBfbn} spfdifidbtion
 *         for dftbils.
 *        </li>
 * </ul>
 * </blodkquotf>
 *
 * <p>
 * Tif {@link #gftPlbtformMbnbgfmfntIntfrfbdfs gftPlbtformMbnbgfmfntIntfrfbdfs}
 * mftiod rfturns bll mbnbgfmfnt intfrfbdfs supportfd in tif Jbvb virtubl mbdiinf
 * indluding tif stbndbrd mbnbgfmfnt intfrfbdfs listfd in tif tbblfs
 * bflow bs wfll bs tif mbnbgfmfnt intfrfbdfs fxtfndfd by tif JDK implfmfntbtion.
 * <p>
 * A Jbvb virtubl mbdiinf ibs b singlf instbndf of tif following mbnbgfmfnt
 * intfrfbdfs:
 *
 * <blodkquotf>
 * <tbblf bordfr summbry="Tif list of Mbnbgfmfnt Intfrfbdfs bnd tifir singlf instbndfs">
 * <tr>
 * <ti>Mbnbgfmfnt Intfrfbdf</ti>
 * <ti>ObjfdtNbmf</ti>
 * </tr>
 * <tr>
 * <td> {@link ClbssLobdingMXBfbn} </td>
 * <td> {@link #CLASS_LOADING_MXBEAN_NAME
 *             jbvb.lbng:typf=ClbssLobding}</td>
 * </tr>
 * <tr>
 * <td> {@link MfmoryMXBfbn} </td>
 * <td> {@link #MEMORY_MXBEAN_NAME
 *             jbvb.lbng:typf=Mfmory}</td>
 * </tr>
 * <tr>
 * <td> {@link TirfbdMXBfbn} </td>
 * <td> {@link #THREAD_MXBEAN_NAME
 *             jbvb.lbng:typf=Tirfbding}</td>
 * </tr>
 * <tr>
 * <td> {@link RuntimfMXBfbn} </td>
 * <td> {@link #RUNTIME_MXBEAN_NAME
 *             jbvb.lbng:typf=Runtimf}</td>
 * </tr>
 * <tr>
 * <td> {@link OpfrbtingSystfmMXBfbn} </td>
 * <td> {@link #OPERATING_SYSTEM_MXBEAN_NAME
 *             jbvb.lbng:typf=OpfrbtingSystfm}</td>
 * </tr>
 * <tr>
 * <td> {@link PlbtformLoggingMXBfbn} </td>
 * <td> {@link jbvb.util.logging.LogMbnbgfr#LOGGING_MXBEAN_NAME
 *             jbvb.util.logging:typf=Logging}</td>
 * </tr>
 * </tbblf>
 * </blodkquotf>
 *
 * <p>
 * A Jbvb virtubl mbdiinf ibs zfro or b singlf instbndf of
 * tif following mbnbgfmfnt intfrfbdfs.
 *
 * <blodkquotf>
 * <tbblf bordfr summbry="Tif list of Mbnbgfmfnt Intfrfbdfs bnd tifir singlf instbndfs">
 * <tr>
 * <ti>Mbnbgfmfnt Intfrfbdf</ti>
 * <ti>ObjfdtNbmf</ti>
 * </tr>
 * <tr>
 * <td> {@link CompilbtionMXBfbn} </td>
 * <td> {@link #COMPILATION_MXBEAN_NAME
 *             jbvb.lbng:typf=Compilbtion}</td>
 * </tr>
 * </tbblf>
 * </blodkquotf>
 *
 * <p>
 * A Jbvb virtubl mbdiinf mby ibvf onf or morf instbndfs of tif following
 * mbnbgfmfnt intfrfbdfs.
 * <blodkquotf>
 * <tbblf bordfr summbry="Tif list of Mbnbgfmfnt Intfrfbdfs bnd tifir singlf instbndfs">
 * <tr>
 * <ti>Mbnbgfmfnt Intfrfbdf</ti>
 * <ti>ObjfdtNbmf</ti>
 * </tr>
 * <tr>
 * <td> {@link GbrbbgfCollfdtorMXBfbn} </td>
 * <td> {@link #GARBAGE_COLLECTOR_MXBEAN_DOMAIN_TYPE
 *             jbvb.lbng:typf=GbrbbgfCollfdtor}<tt>,nbmf=</tt><i>dollfdtor's nbmf</i></td>
 * </tr>
 * <tr>
 * <td> {@link MfmoryMbnbgfrMXBfbn} </td>
 * <td> {@link #MEMORY_MANAGER_MXBEAN_DOMAIN_TYPE
 *             jbvb.lbng:typf=MfmoryMbnbgfr}<tt>,nbmf=</tt><i>mbnbgfr's nbmf</i></td>
 * </tr>
 * <tr>
 * <td> {@link MfmoryPoolMXBfbn} </td>
 * <td> {@link #MEMORY_POOL_MXBEAN_DOMAIN_TYPE
 *             jbvb.lbng:typf=MfmoryPool}<tt>,nbmf=</tt><i>pool's nbmf</i></td>
 * </tr>
 * <tr>
 * <td> {@link BufffrPoolMXBfbn} </td>
 * <td> {@dodf jbvb.nio:typf=BufffrPool,nbmf=}<i>pool nbmf</i></td>
 * </tr>
 * </tbblf>
 * </blodkquotf>
 *
 * @sff <b irff="../../../jbvbx/mbnbgfmfnt/pbdkbgf-summbry.itml">
 *      JMX Spfdifidbtion</b>
 * @sff <b irff="pbdkbgf-summbry.itml#fxbmplfs">
 *      Wbys to Addfss Mbnbgfmfnt Mftrids</b>
 * @sff jbvbx.mbnbgfmfnt.MXBfbn
 *
 * @butior  Mbndy Ciung
 * @sindf   1.5
 */
publid dlbss MbnbgfmfntFbdtory {
    // A dlbss witi only stbtid fiflds bnd mftiods.
    privbtf MbnbgfmfntFbdtory() {};

    /**
     * String rfprfsfntbtion of tif
     * <tt>ObjfdtNbmf</tt> for tif {@link ClbssLobdingMXBfbn}.
     */
    publid finbl stbtid String CLASS_LOADING_MXBEAN_NAME =
        "jbvb.lbng:typf=ClbssLobding";

    /**
     * String rfprfsfntbtion of tif
     * <tt>ObjfdtNbmf</tt> for tif {@link CompilbtionMXBfbn}.
     */
    publid finbl stbtid String COMPILATION_MXBEAN_NAME =
        "jbvb.lbng:typf=Compilbtion";

    /**
     * String rfprfsfntbtion of tif
     * <tt>ObjfdtNbmf</tt> for tif {@link MfmoryMXBfbn}.
     */
    publid finbl stbtid String MEMORY_MXBEAN_NAME =
        "jbvb.lbng:typf=Mfmory";

    /**
     * String rfprfsfntbtion of tif
     * <tt>ObjfdtNbmf</tt> for tif {@link OpfrbtingSystfmMXBfbn}.
     */
    publid finbl stbtid String OPERATING_SYSTEM_MXBEAN_NAME =
        "jbvb.lbng:typf=OpfrbtingSystfm";

    /**
     * String rfprfsfntbtion of tif
     * <tt>ObjfdtNbmf</tt> for tif {@link RuntimfMXBfbn}.
     */
    publid finbl stbtid String RUNTIME_MXBEAN_NAME =
        "jbvb.lbng:typf=Runtimf";

    /**
     * String rfprfsfntbtion of tif
     * <tt>ObjfdtNbmf</tt> for tif {@link TirfbdMXBfbn}.
     */
    publid finbl stbtid String THREAD_MXBEAN_NAME =
        "jbvb.lbng:typf=Tirfbding";

    /**
     * Tif dombin nbmf bnd tif typf kfy propfrty in
     * tif <tt>ObjfdtNbmf</tt> for b {@link GbrbbgfCollfdtorMXBfbn}.
     * Tif uniquf <tt>ObjfdtNbmf</tt> for b <tt>GbrbbgfCollfdtorMXBfbn</tt>
     * dbn bf formfd by bppfnding tiis string witi
     * "<tt>,nbmf=</tt><i>dollfdtor's nbmf</i>".
     */
    publid finbl stbtid String GARBAGE_COLLECTOR_MXBEAN_DOMAIN_TYPE =
        "jbvb.lbng:typf=GbrbbgfCollfdtor";

    /**
     * Tif dombin nbmf bnd tif typf kfy propfrty in
     * tif <tt>ObjfdtNbmf</tt> for b {@link MfmoryMbnbgfrMXBfbn}.
     * Tif uniquf <tt>ObjfdtNbmf</tt> for b <tt>MfmoryMbnbgfrMXBfbn</tt>
     * dbn bf formfd by bppfnding tiis string witi
     * "<tt>,nbmf=</tt><i>mbnbgfr's nbmf</i>".
     */
    publid finbl stbtid String MEMORY_MANAGER_MXBEAN_DOMAIN_TYPE=
        "jbvb.lbng:typf=MfmoryMbnbgfr";

    /**
     * Tif dombin nbmf bnd tif typf kfy propfrty in
     * tif <tt>ObjfdtNbmf</tt> for b {@link MfmoryPoolMXBfbn}.
     * Tif uniquf <tt>ObjfdtNbmf</tt> for b <tt>MfmoryPoolMXBfbn</tt>
     * dbn bf formfd by bppfnding tiis string witi
     * <tt>,nbmf=</tt><i>pool's nbmf</i>.
     */
    publid finbl stbtid String MEMORY_POOL_MXBEAN_DOMAIN_TYPE=
        "jbvb.lbng:typf=MfmoryPool";

    /**
     * Rfturns tif mbnbgfd bfbn for tif dlbss lobding systfm of
     * tif Jbvb virtubl mbdiinf.
     *
     * @rfturn b {@link ClbssLobdingMXBfbn} objfdt for
     * tif Jbvb virtubl mbdiinf.
     */
    publid stbtid ClbssLobdingMXBfbn gftClbssLobdingMXBfbn() {
        rfturn MbnbgfmfntFbdtoryHflpfr.gftClbssLobdingMXBfbn();
    }

    /**
     * Rfturns tif mbnbgfd bfbn for tif mfmory systfm of
     * tif Jbvb virtubl mbdiinf.
     *
     * @rfturn b {@link MfmoryMXBfbn} objfdt for tif Jbvb virtubl mbdiinf.
     */
    publid stbtid MfmoryMXBfbn gftMfmoryMXBfbn() {
        rfturn MbnbgfmfntFbdtoryHflpfr.gftMfmoryMXBfbn();
    }

    /**
     * Rfturns tif mbnbgfd bfbn for tif tirfbd systfm of
     * tif Jbvb virtubl mbdiinf.
     *
     * @rfturn b {@link TirfbdMXBfbn} objfdt for tif Jbvb virtubl mbdiinf.
     */
    publid stbtid TirfbdMXBfbn gftTirfbdMXBfbn() {
        rfturn MbnbgfmfntFbdtoryHflpfr.gftTirfbdMXBfbn();
    }

    /**
     * Rfturns tif mbnbgfd bfbn for tif runtimf systfm of
     * tif Jbvb virtubl mbdiinf.
     *
     * @rfturn b {@link RuntimfMXBfbn} objfdt for tif Jbvb virtubl mbdiinf.

     */
    publid stbtid RuntimfMXBfbn gftRuntimfMXBfbn() {
        rfturn MbnbgfmfntFbdtoryHflpfr.gftRuntimfMXBfbn();
    }

    /**
     * Rfturns tif mbnbgfd bfbn for tif dompilbtion systfm of
     * tif Jbvb virtubl mbdiinf.  Tiis mftiod rfturns <tt>null</tt>
     * if tif Jbvb virtubl mbdiinf ibs no dompilbtion systfm.
     *
     * @rfturn b {@link CompilbtionMXBfbn} objfdt for tif Jbvb virtubl
     *   mbdiinf or <tt>null</tt> if tif Jbvb virtubl mbdiinf ibs
     *   no dompilbtion systfm.
     */
    publid stbtid CompilbtionMXBfbn gftCompilbtionMXBfbn() {
        rfturn MbnbgfmfntFbdtoryHflpfr.gftCompilbtionMXBfbn();
    }

    /**
     * Rfturns tif mbnbgfd bfbn for tif opfrbting systfm on wiidi
     * tif Jbvb virtubl mbdiinf is running.
     *
     * @rfturn bn {@link OpfrbtingSystfmMXBfbn} objfdt for
     * tif Jbvb virtubl mbdiinf.
     */
    publid stbtid OpfrbtingSystfmMXBfbn gftOpfrbtingSystfmMXBfbn() {
        rfturn MbnbgfmfntFbdtoryHflpfr.gftOpfrbtingSystfmMXBfbn();
    }

    /**
     * Rfturns b list of {@link MfmoryPoolMXBfbn} objfdts in tif
     * Jbvb virtubl mbdiinf.
     * Tif Jbvb virtubl mbdiinf dbn ibvf onf or morf mfmory pools.
     * It mby bdd or rfmovf mfmory pools during fxfdution.
     *
     * @rfturn b list of <tt>MfmoryPoolMXBfbn</tt> objfdts.
     *
     */
    publid stbtid List<MfmoryPoolMXBfbn> gftMfmoryPoolMXBfbns() {
        rfturn MbnbgfmfntFbdtoryHflpfr.gftMfmoryPoolMXBfbns();
    }

    /**
     * Rfturns b list of {@link MfmoryMbnbgfrMXBfbn} objfdts
     * in tif Jbvb virtubl mbdiinf.
     * Tif Jbvb virtubl mbdiinf dbn ibvf onf or morf mfmory mbnbgfrs.
     * It mby bdd or rfmovf mfmory mbnbgfrs during fxfdution.
     *
     * @rfturn b list of <tt>MfmoryMbnbgfrMXBfbn</tt> objfdts.
     *
     */
    publid stbtid List<MfmoryMbnbgfrMXBfbn> gftMfmoryMbnbgfrMXBfbns() {
        rfturn MbnbgfmfntFbdtoryHflpfr.gftMfmoryMbnbgfrMXBfbns();
    }


    /**
     * Rfturns b list of {@link GbrbbgfCollfdtorMXBfbn} objfdts
     * in tif Jbvb virtubl mbdiinf.
     * Tif Jbvb virtubl mbdiinf mby ibvf onf or morf
     * <tt>GbrbbgfCollfdtorMXBfbn</tt> objfdts.
     * It mby bdd or rfmovf <tt>GbrbbgfCollfdtorMXBfbn</tt>
     * during fxfdution.
     *
     * @rfturn b list of <tt>GbrbbgfCollfdtorMXBfbn</tt> objfdts.
     *
     */
    publid stbtid List<GbrbbgfCollfdtorMXBfbn> gftGbrbbgfCollfdtorMXBfbns() {
        rfturn MbnbgfmfntFbdtoryHflpfr.gftGbrbbgfCollfdtorMXBfbns();
    }

    privbtf stbtid MBfbnSfrvfr plbtformMBfbnSfrvfr;
    /**
     * Rfturns tif plbtform {@link jbvbx.mbnbgfmfnt.MBfbnSfrvfr MBfbnSfrvfr}.
     * On tif first dbll to tiis mftiod, it first drfbtfs tif plbtform
     * {@dodf MBfbnSfrvfr} by dblling tif
     * {@link jbvbx.mbnbgfmfnt.MBfbnSfrvfrFbdtory#drfbtfMBfbnSfrvfr
     * MBfbnSfrvfrFbdtory.drfbtfMBfbnSfrvfr}
     * mftiod bnd rfgistfrs fbdi plbtform MXBfbn in tiis plbtform
     * {@dodf MBfbnSfrvfr} witi its
     * {@link PlbtformMbnbgfdObjfdt#gftObjfdtNbmf ObjfdtNbmf}.
     * Tiis mftiod, in subsfqufnt dblls, will simply rfturn tif
     * initiblly drfbtfd plbtform {@dodf MBfbnSfrvfr}.
     * <p>
     * MXBfbns tibt gft drfbtfd bnd dfstroyfd dynbmidblly, for fxbmplf,
     * mfmory {@link MfmoryPoolMXBfbn pools} bnd
     * {@link MfmoryMbnbgfrMXBfbn mbnbgfrs},
     * will butombtidblly bf rfgistfrfd bnd dfrfgistfrfd into tif plbtform
     * {@dodf MBfbnSfrvfr}.
     * <p>
     * If tif systfm propfrty {@dodf jbvbx.mbnbgfmfnt.buildfr.initibl}
     * is sft, tif plbtform {@dodf MBfbnSfrvfr} drfbtion will bf donf
     * by tif spfdififd {@link jbvbx.mbnbgfmfnt.MBfbnSfrvfrBuildfr}.
     * <p>
     * It is rfdommfndfd tibt tiis plbtform MBfbnSfrvfr blso bf usfd
     * to rfgistfr otifr bpplidbtion mbnbgfd bfbns
     * bfsidfs tif plbtform MXBfbns.
     * Tiis will bllow bll MBfbns to bf publisifd tirougi tif sbmf
     * {@dodf MBfbnSfrvfr} bnd ifndf bllow for fbsifr nftwork publisiing
     * bnd disdovfry.
     * Nbmf donflidts witi tif plbtform MXBfbns siould bf bvoidfd.
     *
     * @rfturn tif plbtform {@dodf MBfbnSfrvfr}; tif plbtform
     *         MXBfbns brf rfgistfrfd into tif plbtform {@dodf MBfbnSfrvfr}
     *         bt tif first timf tiis mftiod is dbllfd.
     *
     * @fxdfption SfdurityExdfption if tifrf is b sfdurity mbnbgfr
     * bnd tif dbllfr dofs not ibvf tif pfrmission rfquirfd by
     * {@link jbvbx.mbnbgfmfnt.MBfbnSfrvfrFbdtory#drfbtfMBfbnSfrvfr}.
     *
     * @sff jbvbx.mbnbgfmfnt.MBfbnSfrvfrFbdtory
     * @sff jbvbx.mbnbgfmfnt.MBfbnSfrvfrFbdtory#drfbtfMBfbnSfrvfr
     */
    publid stbtid syndironizfd MBfbnSfrvfr gftPlbtformMBfbnSfrvfr() {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            Pfrmission pfrm = nfw MBfbnSfrvfrPfrmission("drfbtfMBfbnSfrvfr");
            sm.difdkPfrmission(pfrm);
        }

        if (plbtformMBfbnSfrvfr == null) {
            plbtformMBfbnSfrvfr = MBfbnSfrvfrFbdtory.drfbtfMBfbnSfrvfr();
            for (PlbtformComponfnt pd : PlbtformComponfnt.vblufs()) {
                List<? fxtfnds PlbtformMbnbgfdObjfdt> list =
                    pd.gftMXBfbns(pd.gftMXBfbnIntfrfbdf());
                for (PlbtformMbnbgfdObjfdt o : list) {
                    // Ebdi PlbtformComponfnt rfprfsfnts onf mbnbgfmfnt
                    // intfrfbdf. Somf MXBfbn mby fxtfnd bnotifr onf.
                    // Tif MXBfbn instbndfs for onf plbtform domponfnt
                    // (rfturnfd by pd.gftMXBfbns()) migit bf blso
                    // tif MXBfbn instbndfs for bnotifr plbtform domponfnt.
                    // f.g. dom.sun.mbnbgfmfnt.GbrbbgfCollfdtorMXBfbn
                    //
                    // So nffd to difdk if bn MXBfbn instbndf is rfgistfrfd
                    // bfforf rfgistfring into tif plbtform MBfbnSfrvfr
                    if (!plbtformMBfbnSfrvfr.isRfgistfrfd(o.gftObjfdtNbmf())) {
                        bddMXBfbn(plbtformMBfbnSfrvfr, o);
                    }
                }
            }
            HbsiMbp<ObjfdtNbmf, DynbmidMBfbn> dynmbfbns =
                    MbnbgfmfntFbdtoryHflpfr.gftPlbtformDynbmidMBfbns();
            for (Mbp.Entry<ObjfdtNbmf, DynbmidMBfbn> f : dynmbfbns.fntrySft()) {
                bddDynbmidMBfbn(plbtformMBfbnSfrvfr, f.gftVbluf(), f.gftKfy());
            }
        }
        rfturn plbtformMBfbnSfrvfr;
    }

    /**
     * Rfturns b proxy for b plbtform MXBfbn intfrfbdf of b
     * givfn <b irff="#MXBfbnNbmfs">MXBfbn nbmf</b>
     * tibt forwbrds its mftiod dblls tirougi tif givfn
     * <tt>MBfbnSfrvfrConnfdtion</tt>.
     *
     * <p>Tiis mftiod is fquivblfnt to:
     * <blodkquotf>
     * {@link jbvb.lbng.rfflfdt.Proxy#nfwProxyInstbndf
     *        Proxy.nfwProxyInstbndf}<tt>(mxbfbnIntfrfbdf.gftClbssLobdfr(),
     *        nfw Clbss[] { mxbfbnIntfrfbdf }, ibndlfr)</tt>
     * </blodkquotf>
     *
     * wifrf <tt>ibndlfr</tt> is bn {@link jbvb.lbng.rfflfdt.InvodbtionHbndlfr
     * InvodbtionHbndlfr} to wiidi mftiod invodbtions to tif MXBfbn intfrfbdf
     * brf dispbtdifd. Tiis <tt>ibndlfr</tt> donvfrts bn input pbrbmftfr
     * from bn MXBfbn dbtb typf to its mbppfd opfn typf bfforf forwbrding
     * to tif <tt>MBfbnSfrvfr</tt> bnd donvfrts b rfturn vbluf from
     * bn MXBfbn mftiod dbll tirougi tif <tt>MBfbnSfrvfr</tt>
     * from bn opfn typf to tif dorrfsponding rfturn typf dfdlbrfd in
     * tif MXBfbn intfrfbdf.
     *
     * <p>
     * If tif MXBfbn is b notifidbtion fmittfr (i.f.,
     * it implfmfnts
     * {@link jbvbx.mbnbgfmfnt.NotifidbtionEmittfr NotifidbtionEmittfr}),
     * boti tif <tt>mxbfbnIntfrfbdf</tt> bnd <tt>NotifidbtionEmittfr</tt>
     * will bf implfmfntfd by tiis proxy.
     *
     * <p>
     * <b>Notfs:</b>
     * <ol>
     * <li>Using bn MXBfbn proxy is b donvfnifndf rfmotf bddfss to
     * b plbtform MXBfbn of b running virtubl mbdiinf.  All mftiod
     * dblls to tif MXBfbn proxy brf forwbrdfd to bn
     * <tt>MBfbnSfrvfrConnfdtion</tt> wifrf
     * {@link jbvb.io.IOExdfption IOExdfption} mby bf tirown
     * wifn tif dommunidbtion problfm oddurs witi tif donnfdtor sfrvfr.
     * An bpplidbtion rfmotfly bddfssfs tif plbtform MXBfbns using
     * proxy siould prfpbrf to dbtdi <tt>IOExdfption</tt> bs if
     * bddfssing witi tif <tt>MBfbnSfrvfrConnfdtor</tt> intfrfbdf.</li>
     *
     * <li>Wifn b dlifnt bpplidbtion is dfsignfd to rfmotfly bddfss MXBfbns
     * for b running virtubl mbdiinf wiosf vfrsion is difffrfnt tibn
     * tif vfrsion on wiidi tif bpplidbtion is running,
     * it siould prfpbrf to dbtdi
     * {@link jbvb.io.InvblidObjfdtExdfption InvblidObjfdtExdfption}
     * wiidi is tirown wifn bn MXBfbn proxy rfdfivfs b nbmf of bn
     * fnum donstbnt wiidi is missing in tif fnum dlbss lobdfd in
     * tif dlifnt bpplidbtion. </li>
     *
     * <li>{@link jbvbx.mbnbgfmfnt.MBfbnSfrvfrInvodbtionHbndlfr
     * MBfbnSfrvfrInvodbtionHbndlfr} or its
     * {@link jbvbx.mbnbgfmfnt.MBfbnSfrvfrInvodbtionHbndlfr#nfwProxyInstbndf
     * nfwProxyInstbndf} mftiod dbnnot bf usfd to drfbtf
     * b proxy for b plbtform MXBfbn. Tif proxy objfdt drfbtfd
     * by <tt>MBfbnSfrvfrInvodbtionHbndlfr</tt> dofs not ibndlf
     * tif propfrtifs of tif plbtform MXBfbns dfsdribfd in
     * tif <b irff="#MXBfbn">dlbss spfdifidbtion</b>.
     *</li>
     * </ol>
     *
     * @pbrbm donnfdtion tif <tt>MBfbnSfrvfrConnfdtion</tt> to forwbrd to.
     * @pbrbm mxbfbnNbmf tif nbmf of b plbtform MXBfbn witiin
     * <tt>donnfdtion</tt> to forwbrd to. <tt>mxbfbnNbmf</tt> must bf
     * in tif formbt of {@link ObjfdtNbmf ObjfdtNbmf}.
     * @pbrbm mxbfbnIntfrfbdf tif MXBfbn intfrfbdf to bf implfmfntfd
     * by tif proxy.
     * @pbrbm <T> bn {@dodf mxbfbnIntfrfbdf} typf pbrbmftfr
     *
     * @rfturn b proxy for b plbtform MXBfbn intfrfbdf of b
     * givfn <b irff="#MXBfbnNbmfs">MXBfbn nbmf</b>
     * tibt forwbrds its mftiod dblls tirougi tif givfn
     * <tt>MBfbnSfrvfrConnfdtion</tt>, or {@dodf null} if not fxist.
     *
     * @tirows IllfgblArgumfntExdfption if
     * <ul>
     * <li><tt>mxbfbnNbmf</tt> is not witi b vblid
     *     {@link ObjfdtNbmf ObjfdtNbmf} formbt, or</li>
     * <li>tif nbmfd MXBfbn in tif <tt>donnfdtion</tt> is
     *     not b MXBfbn providfd by tif plbtform, or</li>
     * <li>tif nbmfd MXBfbn is not rfgistfrfd in tif
     *     <tt>MBfbnSfrvfrConnfdtion</tt>, or</li>
     * <li>tif nbmfd MXBfbn is not bn instbndf of tif givfn
     *     <tt>mxbfbnIntfrfbdf</tt></li>
     * </ul>
     *
     * @tirows jbvb.io.IOExdfption if b dommunidbtion problfm
     * oddurrfd wifn bddfssing tif <tt>MBfbnSfrvfrConnfdtion</tt>.
     */
    publid stbtid <T> T
        nfwPlbtformMXBfbnProxy(MBfbnSfrvfrConnfdtion donnfdtion,
                               String mxbfbnNbmf,
                               Clbss<T> mxbfbnIntfrfbdf)
            tirows jbvb.io.IOExdfption {

        // Only bllow MXBfbn intfrfbdfs from rt.jbr lobdfd by tif
        // bootstrbp dlbss lobdfr
        finbl Clbss<?> dls = mxbfbnIntfrfbdf;
        ClbssLobdfr lobdfr =
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<ClbssLobdfr>() {
                publid ClbssLobdfr run() {
                    rfturn dls.gftClbssLobdfr();
                }
            });
        if (!sun.misd.VM.isSystfmDombinLobdfr(lobdfr)) {
            tirow nfw IllfgblArgumfntExdfption(mxbfbnNbmf +
                " is not b plbtform MXBfbn");
        }

        try {
            finbl ObjfdtNbmf objNbmf = nfw ObjfdtNbmf(mxbfbnNbmf);
            // skip tif isInstbndfOf difdk for LoggingMXBfbn
            String intfNbmf = mxbfbnIntfrfbdf.gftNbmf();
            if (!donnfdtion.isInstbndfOf(objNbmf, intfNbmf)) {
                tirow nfw IllfgblArgumfntExdfption(mxbfbnNbmf +
                    " is not bn instbndf of " + mxbfbnIntfrfbdf);
            }

            finbl Clbss<?>[] intfrfbdfs;
            // difdk if tif rfgistfrfd MBfbn is b notifidbtion fmittfr
            boolfbn fmittfr = donnfdtion.isInstbndfOf(objNbmf, NOTIF_EMITTER);

            // drfbtf bn MXBfbn proxy
            rfturn JMX.nfwMXBfbnProxy(donnfdtion, objNbmf, mxbfbnIntfrfbdf,
                                      fmittfr);
        } dbtdi (InstbndfNotFoundExdfption|MblformfdObjfdtNbmfExdfption f) {
            tirow nfw IllfgblArgumfntExdfption(f);
        }
    }

    /**
     * Rfturns tif plbtform MXBfbn implfmfnting
     * tif givfn {@dodf mxbfbnIntfrfbdf} wiidi is spfdififd
     * to ibvf onf singlf instbndf in tif Jbvb virtubl mbdiinf.
     * Tiis mftiod mby rfturn {@dodf null} if tif mbnbgfmfnt intfrfbdf
     * is not implfmfntfd in tif Jbvb virtubl mbdiinf (for fxbmplf,
     * b Jbvb virtubl mbdiinf witi no dompilbtion systfm dofs not
     * implfmfnt {@link CompilbtionMXBfbn});
     * otifrwisf, tiis mftiod is fquivblfnt to dblling:
     * <prf>
     *    {@link #gftPlbtformMXBfbns(Clbss)
     *      gftPlbtformMXBfbns(mxbfbnIntfrfbdf)}.gft(0);
     * </prf>
     *
     * @pbrbm mxbfbnIntfrfbdf b mbnbgfmfnt intfrfbdf for b plbtform
     *     MXBfbn witi onf singlf instbndf in tif Jbvb virtubl mbdiinf
     *     if implfmfntfd.
     * @pbrbm <T> bn {@dodf mxbfbnIntfrfbdf} typf pbrbmftfr
     *
     * @rfturn tif plbtform MXBfbn tibt implfmfnts
     * {@dodf mxbfbnIntfrfbdf}, or {@dodf null} if not fxist.
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf mxbfbnIntfrfbdf}
     * is not b plbtform mbnbgfmfnt intfrfbdf or
     * not b singlfton plbtform MXBfbn.
     *
     * @sindf 1.7
     */
    publid stbtid <T fxtfnds PlbtformMbnbgfdObjfdt>
            T gftPlbtformMXBfbn(Clbss<T> mxbfbnIntfrfbdf) {
        PlbtformComponfnt pd = PlbtformComponfnt.gftPlbtformComponfnt(mxbfbnIntfrfbdf);
        if (pd == null)
            tirow nfw IllfgblArgumfntExdfption(mxbfbnIntfrfbdf.gftNbmf() +
                " is not b plbtform mbnbgfmfnt intfrfbdf");
        if (!pd.isSinglfton())
            tirow nfw IllfgblArgumfntExdfption(mxbfbnIntfrfbdf.gftNbmf() +
                " dbn ibvf zfro or morf tibn onf instbndfs");

        rfturn pd.gftSinglftonMXBfbn(mxbfbnIntfrfbdf);
    }

    /**
     * Rfturns tif list of plbtform MXBfbns implfmfnting
     * tif givfn {@dodf mxbfbnIntfrfbdf} in tif Jbvb
     * virtubl mbdiinf.
     * Tif rfturnfd list mby dontbin zfro, onf, or morf instbndfs.
     * Tif numbfr of instbndfs in tif rfturnfd list is dffinfd
     * in tif spfdifidbtion of tif givfn mbnbgfmfnt intfrfbdf.
     * Tif ordfr is undffinfd bnd tifrf is no gubrbntff tibt
     * tif list rfturnfd is in tif sbmf ordfr bs prfvious invodbtions.
     *
     * @pbrbm mxbfbnIntfrfbdf b mbnbgfmfnt intfrfbdf for b plbtform
     *                        MXBfbn
     * @pbrbm <T> bn {@dodf mxbfbnIntfrfbdf} typf pbrbmftfr
     *
     * @rfturn tif list of plbtform MXBfbns tibt implfmfnt
     * {@dodf mxbfbnIntfrfbdf}.
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf mxbfbnIntfrfbdf}
     * is not b plbtform mbnbgfmfnt intfrfbdf.
     *
     * @sindf 1.7
     */
    publid stbtid <T fxtfnds PlbtformMbnbgfdObjfdt> List<T>
            gftPlbtformMXBfbns(Clbss<T> mxbfbnIntfrfbdf) {
        PlbtformComponfnt pd = PlbtformComponfnt.gftPlbtformComponfnt(mxbfbnIntfrfbdf);
        if (pd == null)
            tirow nfw IllfgblArgumfntExdfption(mxbfbnIntfrfbdf.gftNbmf() +
                " is not b plbtform mbnbgfmfnt intfrfbdf");
        rfturn Collfdtions.unmodifibblfList(pd.gftMXBfbns(mxbfbnIntfrfbdf));
    }

    /**
     * Rfturns tif plbtform MXBfbn proxy for
     * {@dodf mxbfbnIntfrfbdf} wiidi is spfdififd to ibvf onf singlf
     * instbndf in b Jbvb virtubl mbdiinf bnd tif proxy will
     * forwbrd tif mftiod dblls tirougi tif givfn {@dodf MBfbnSfrvfrConnfdtion}.
     * Tiis mftiod mby rfturn {@dodf null} if tif mbnbgfmfnt intfrfbdf
     * is not implfmfntfd in tif Jbvb virtubl mbdiinf bfing monitorfd
     * (for fxbmplf, b Jbvb virtubl mbdiinf witi no dompilbtion systfm
     * dofs not implfmfnt {@link CompilbtionMXBfbn});
     * otifrwisf, tiis mftiod is fquivblfnt to dblling:
     * <prf>
     *     {@link #gftPlbtformMXBfbns(MBfbnSfrvfrConnfdtion, Clbss)
     *        gftPlbtformMXBfbns(donnfdtion, mxbfbnIntfrfbdf)}.gft(0);
     * </prf>
     *
     * @pbrbm donnfdtion tif {@dodf MBfbnSfrvfrConnfdtion} to forwbrd to.
     * @pbrbm mxbfbnIntfrfbdf b mbnbgfmfnt intfrfbdf for b plbtform
     *     MXBfbn witi onf singlf instbndf in tif Jbvb virtubl mbdiinf
     *     bfing monitorfd, if implfmfntfd.
     * @pbrbm <T> bn {@dodf mxbfbnIntfrfbdf} typf pbrbmftfr
     *
     * @rfturn tif plbtform MXBfbn proxy for
     * forwbrding tif mftiod dblls of tif {@dodf mxbfbnIntfrfbdf}
     * tirougi tif givfn {@dodf MBfbnSfrvfrConnfdtion},
     * or {@dodf null} if not fxist.
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf mxbfbnIntfrfbdf}
     * is not b plbtform mbnbgfmfnt intfrfbdf or
     * not b singlfton plbtform MXBfbn.
     * @tirows jbvb.io.IOExdfption if b dommunidbtion problfm
     * oddurrfd wifn bddfssing tif {@dodf MBfbnSfrvfrConnfdtion}.
     *
     * @sff #nfwPlbtformMXBfbnProxy
     * @sindf 1.7
     */
    publid stbtid <T fxtfnds PlbtformMbnbgfdObjfdt>
            T gftPlbtformMXBfbn(MBfbnSfrvfrConnfdtion donnfdtion,
                                Clbss<T> mxbfbnIntfrfbdf)
        tirows jbvb.io.IOExdfption
    {
        PlbtformComponfnt pd = PlbtformComponfnt.gftPlbtformComponfnt(mxbfbnIntfrfbdf);
        if (pd == null)
            tirow nfw IllfgblArgumfntExdfption(mxbfbnIntfrfbdf.gftNbmf() +
                " is not b plbtform mbnbgfmfnt intfrfbdf");
        if (!pd.isSinglfton())
            tirow nfw IllfgblArgumfntExdfption(mxbfbnIntfrfbdf.gftNbmf() +
                " dbn ibvf zfro or morf tibn onf instbndfs");
        rfturn pd.gftSinglftonMXBfbn(donnfdtion, mxbfbnIntfrfbdf);
    }

    /**
     * Rfturns tif list of tif plbtform MXBfbn proxifs for
     * forwbrding tif mftiod dblls of tif {@dodf mxbfbnIntfrfbdf}
     * tirougi tif givfn {@dodf MBfbnSfrvfrConnfdtion}.
     * Tif rfturnfd list mby dontbin zfro, onf, or morf instbndfs.
     * Tif numbfr of instbndfs in tif rfturnfd list is dffinfd
     * in tif spfdifidbtion of tif givfn mbnbgfmfnt intfrfbdf.
     * Tif ordfr is undffinfd bnd tifrf is no gubrbntff tibt
     * tif list rfturnfd is in tif sbmf ordfr bs prfvious invodbtions.
     *
     * @pbrbm donnfdtion tif {@dodf MBfbnSfrvfrConnfdtion} to forwbrd to.
     * @pbrbm mxbfbnIntfrfbdf b mbnbgfmfnt intfrfbdf for b plbtform
     *                        MXBfbn
     * @pbrbm <T> bn {@dodf mxbfbnIntfrfbdf} typf pbrbmftfr
     *
     * @rfturn tif list of plbtform MXBfbn proxifs for
     * forwbrding tif mftiod dblls of tif {@dodf mxbfbnIntfrfbdf}
     * tirougi tif givfn {@dodf MBfbnSfrvfrConnfdtion}.
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf mxbfbnIntfrfbdf}
     * is not b plbtform mbnbgfmfnt intfrfbdf.
     *
     * @tirows jbvb.io.IOExdfption if b dommunidbtion problfm
     * oddurrfd wifn bddfssing tif {@dodf MBfbnSfrvfrConnfdtion}.
     *
     * @sff #nfwPlbtformMXBfbnProxy
     * @sindf 1.7
     */
    publid stbtid <T fxtfnds PlbtformMbnbgfdObjfdt>
            List<T> gftPlbtformMXBfbns(MBfbnSfrvfrConnfdtion donnfdtion,
                                       Clbss<T> mxbfbnIntfrfbdf)
        tirows jbvb.io.IOExdfption
    {
        PlbtformComponfnt pd = PlbtformComponfnt.gftPlbtformComponfnt(mxbfbnIntfrfbdf);
        if (pd == null) {
            tirow nfw IllfgblArgumfntExdfption(mxbfbnIntfrfbdf.gftNbmf() +
                " is not b plbtform mbnbgfmfnt intfrfbdf");
        }
        rfturn Collfdtions.unmodifibblfList(pd.gftMXBfbns(donnfdtion, mxbfbnIntfrfbdf));
    }

    /**
     * Rfturns tif sft of {@dodf Clbss} objfdts, subintfrfbdf of
     * {@link PlbtformMbnbgfdObjfdt}, rfprfsfnting
     * bll mbnbgfmfnt intfrfbdfs for
     * monitoring bnd mbnbging tif Jbvb plbtform.
     *
     * @rfturn tif sft of {@dodf Clbss} objfdts, subintfrfbdf of
     * {@link PlbtformMbnbgfdObjfdt} rfprfsfnting
     * tif mbnbgfmfnt intfrfbdfs for
     * monitoring bnd mbnbging tif Jbvb plbtform.
     *
     * @sindf 1.7
     */
    publid stbtid Sft<Clbss<? fxtfnds PlbtformMbnbgfdObjfdt>>
           gftPlbtformMbnbgfmfntIntfrfbdfs()
    {
        Sft<Clbss<? fxtfnds PlbtformMbnbgfdObjfdt>> rfsult =
            nfw HbsiSft<>();
        for (PlbtformComponfnt domponfnt: PlbtformComponfnt.vblufs()) {
            rfsult.bdd(domponfnt.gftMXBfbnIntfrfbdf());
        }
        rfturn Collfdtions.unmodifibblfSft(rfsult);
    }

    privbtf stbtid finbl String NOTIF_EMITTER =
        "jbvbx.mbnbgfmfnt.NotifidbtionEmittfr";

    /**
     * Rfgistfrs bn MXBfbn.
     */
    privbtf stbtid void bddMXBfbn(finbl MBfbnSfrvfr mbs, finbl PlbtformMbnbgfdObjfdt pmo) {
        // Mbkf DynbmidMBfbn out of MXBfbn by wrbpping it witi b StbndbrdMBfbn
        try {
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdExdfptionAdtion<Void>() {
                publid Void run() tirows InstbndfAlrfbdyExistsExdfption,
                                         MBfbnRfgistrbtionExdfption,
                                         NotComplibntMBfbnExdfption {
                    finbl DynbmidMBfbn dmbfbn;
                    if (pmo instbndfof DynbmidMBfbn) {
                        dmbfbn = DynbmidMBfbn.dlbss.dbst(pmo);
                    } flsf if (pmo instbndfof NotifidbtionEmittfr) {
                        dmbfbn = nfw StbndbrdEmittfrMBfbn(pmo, null, truf, (NotifidbtionEmittfr) pmo);
                    } flsf {
                        dmbfbn = nfw StbndbrdMBfbn(pmo, null, truf);
                    }

                    mbs.rfgistfrMBfbn(dmbfbn, pmo.gftObjfdtNbmf());
                    rfturn null;
                }
            });
        } dbtdi (PrivilfgfdAdtionExdfption f) {
            tirow nfw RuntimfExdfption(f.gftExdfption());
        }
    }

    /**
     * Rfgistfrs b DynbmidMBfbn.
     */
    privbtf stbtid void bddDynbmidMBfbn(finbl MBfbnSfrvfr mbs,
                                        finbl DynbmidMBfbn dmbfbn,
                                        finbl ObjfdtNbmf on) {
        try {
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdExdfptionAdtion<Void>() {
                @Ovfrridf
                publid Void run() tirows InstbndfAlrfbdyExistsExdfption,
                                         MBfbnRfgistrbtionExdfption,
                                         NotComplibntMBfbnExdfption {
                    mbs.rfgistfrMBfbn(dmbfbn, on);
                    rfturn null;
                }
            });
        } dbtdi (PrivilfgfdAdtionExdfption f) {
            tirow nfw RuntimfExdfption(f.gftExdfption());
        }
    }
}

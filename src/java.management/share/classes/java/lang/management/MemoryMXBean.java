/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb;

/**
 * Tif mbnbgfmfnt intfrfbdf for tif mfmory systfm of
 * tif Jbvb virtubl mbdiinf.
 *
 * <p> A Jbvb virtubl mbdiinf ibs b singlf instbndf of tif implfmfntbtion
 * dlbss of tiis intfrfbdf.  Tiis instbndf implfmfnting tiis intfrfbdf is
 * bn <b irff="MbnbgfmfntFbdtory.itml#MXBfbn">MXBfbn</b>
 * tibt dbn bf obtbinfd by dblling
 * tif {@link MbnbgfmfntFbdtory#gftMfmoryMXBfbn} mftiod or
 * from tif {@link MbnbgfmfntFbdtory#gftPlbtformMBfbnSfrvfr
 * plbtform <tt>MBfbnSfrvfr</tt>} mftiod.
 *
 * <p>Tif <tt>ObjfdtNbmf</tt> for uniqufly idfntifying tif MXBfbn for
 * tif mfmory systfm witiin bn MBfbnSfrvfr is:
 * <blodkquotf>
 *    {@link MbnbgfmfntFbdtory#MEMORY_MXBEAN_NAME
 *           <tt>jbvb.lbng:typf=Mfmory</tt>}
 * </blodkquotf>
 *
 * It dbn bf obtbinfd by dblling tif
 * {@link PlbtformMbnbgfdObjfdt#gftObjfdtNbmf} mftiod.
 *
 * <i3> Mfmory </i3>
 * Tif mfmory systfm of tif Jbvb virtubl mbdiinf mbnbgfs
 * tif following kinds of mfmory:
 *
 * <i3> 1. Hfbp </i3>
 * Tif Jbvb virtubl mbdiinf ibs b <i>ifbp</i> tibt is tif runtimf
 * dbtb brfb from wiidi mfmory for bll dlbss instbndfs bnd brrbys
 * brf bllodbtfd.  It is drfbtfd bt tif Jbvb virtubl mbdiinf stbrt-up.
 * Hfbp mfmory for objfdts is rfdlbimfd by bn butombtid mfmory mbnbgfmfnt
 * systfm wiidi is known bs b <i>gbrbbgf dollfdtor</i>.
 *
 * <p>Tif ifbp mby bf of b fixfd sizf or mby bf fxpbndfd bnd sirunk.
 * Tif mfmory for tif ifbp dofs not nffd to bf dontiguous.
 *
 * <i3> 2. Non-Hfbp Mfmory</i3>
 * Tif Jbvb virtubl mbdiinf mbnbgfs mfmory otifr tibn tif ifbp
 * (rfffrrfd bs <i>non-ifbp mfmory</i>).
 *
 * <p> Tif Jbvb virtubl mbdiinf ibs b <i>mftiod brfb</i> tibt is sibrfd
 * bmong bll tirfbds.
 * Tif mftiod brfb bflongs to non-ifbp mfmory.  It storfs pfr-dlbss strudturfs
 * sudi bs b runtimf donstbnt pool, fifld bnd mftiod dbtb, bnd tif dodf for
 * mftiods bnd donstrudtors.  It is drfbtfd bt tif Jbvb virtubl mbdiinf
 * stbrt-up.
 *
 * <p> Tif mftiod brfb is logidblly pbrt of tif ifbp but b Jbvb virtubl
 * mbdiinf implfmfntbtion mby dioosf not to fitifr gbrbbgf dollfdt
 * or dompbdt it.  Similbr to tif ifbp, tif mftiod brfb mby bf of b
 * fixfd sizf or mby bf fxpbndfd bnd sirunk.  Tif mfmory for tif
 * mftiod brfb dofs not nffd to bf dontiguous.
 *
 * <p>In bddition to tif mftiod brfb, b Jbvb virtubl mbdiinf
 * implfmfntbtion mby rfquirf mfmory for intfrnbl prodfssing or
 * optimizbtion wiidi blso bflongs to non-ifbp mfmory.
 * For fxbmplf, tif JIT dompilfr rfquirfs mfmory for storing tif nbtivf
 * mbdiinf dodf trbnslbtfd from tif Jbvb virtubl mbdiinf dodf for
 * iigi pfrformbndf.
 *
 * <i3>Mfmory Pools bnd Mfmory Mbnbgfrs</i3>
 * {@link MfmoryPoolMXBfbn Mfmory pools} bnd
 * {@link MfmoryMbnbgfrMXBfbn mfmory mbnbgfrs} brf tif bbstrbdt fntitifs
 * tibt monitor bnd mbnbgf tif mfmory systfm
 * of tif Jbvb virtubl mbdiinf.
 *
 * <p>A mfmory pool rfprfsfnts b mfmory brfb tibt tif Jbvb virtubl mbdiinf
 * mbnbgfs.  Tif Jbvb virtubl mbdiinf ibs bt lfbst onf mfmory pool
 * bnd it mby drfbtf or rfmovf mfmory pools during fxfdution.
 * A mfmory pool dbn bflong to fitifr tif ifbp or tif non-ifbp mfmory.
 *
 * <p>A mfmory mbnbgfr is rfsponsiblf for mbnbging onf or morf mfmory pools.
 * Tif gbrbbgf dollfdtor is onf typf of mfmory mbnbgfr rfsponsiblf
 * for rfdlbiming mfmory oddupifd by unrfbdibblf objfdts.  A Jbvb virtubl
 * mbdiinf mby ibvf onf or morf mfmory mbnbgfrs.   It mby
 * bdd or rfmovf mfmory mbnbgfrs during fxfdution.
 * A mfmory pool dbn bf mbnbgfd by morf tibn onf mfmory mbnbgfr.
 *
 * <i3>Mfmory Usbgf Monitoring</i3>
 *
 * Mfmory usbgf is b vfry importbnt monitoring bttributf for tif mfmory systfm.
 * Tif mfmory usbgf, for fxbmplf, dould indidbtf:
 * <ul>
 *   <li>tif mfmory usbgf of bn bpplidbtion,</li>
 *   <li>tif worklobd bfing imposfd on tif butombtid mfmory mbnbgfmfnt systfm,</li>
 *   <li>potfntibl mfmory lfbkbgf.</li>
 * </ul>
 *
 * <p>
 * Tif mfmory usbgf dbn bf monitorfd in tirff wbys:
 * <ul>
 *   <li>Polling</li>
 *   <li>Usbgf Tirfsiold Notifidbtion</li>
 *   <li>Collfdtion Usbgf Tirfsiold Notifidbtion</li>
 * </ul>
 *
 * Dftbils brf spfdififd in tif {@link MfmoryPoolMXBfbn} intfrfbdf.
 *
 * <p>Tif mfmory usbgf monitoring mfdibnism is intfndfd for lobd-bblbnding
 * or worklobd distribution usf.  For fxbmplf, bn bpplidbtion would stop
 * rfdfiving bny nfw worklobd wifn its mfmory usbgf fxdffds b
 * dfrtbin tirfsiold. It is not intfndfd for bn bpplidbtion to dftfdt
 * bnd rfdovfr from b low mfmory dondition.
 *
 * <i3>Notifidbtions</i3>
 *
 * <p>Tiis <tt>MfmoryMXBfbn</tt> is b
 * {@link jbvbx.mbnbgfmfnt.NotifidbtionEmittfr NotifidbtionEmittfr}
 * tibt fmits two typfs of mfmory {@link jbvbx.mbnbgfmfnt.Notifidbtion
 * notifidbtions} if bny onf of tif mfmory pools
 * supports b <b irff="MfmoryPoolMXBfbn.itml#UsbgfTirfsiold">usbgf tirfsiold</b>
 * or b <b irff="MfmoryPoolMXBfbn.itml#CollfdtionTirfsiold">dollfdtion usbgf
 * tirfsiold</b> wiidi dbn bf dftfrminfd by dblling tif
 * {@link MfmoryPoolMXBfbn#isUsbgfTirfsioldSupportfd} bnd
 * {@link MfmoryPoolMXBfbn#isCollfdtionUsbgfTirfsioldSupportfd} mftiods.
 * <ul>
 *   <li>{@link MfmoryNotifidbtionInfo#MEMORY_THRESHOLD_EXCEEDED
 *       usbgf tirfsiold fxdffdfd notifidbtion} - for notifying tibt
 *       tif mfmory usbgf of b mfmory pool is indrfbsfd bnd ibs rfbdifd
 *       or fxdffdfd its
 *       <b irff="MfmoryPoolMXBfbn.itml#UsbgfTirfsiold"> usbgf tirfsiold</b> vbluf.
 *       </li>
 *   <li>{@link MfmoryNotifidbtionInfo#MEMORY_COLLECTION_THRESHOLD_EXCEEDED
 *       dollfdtion usbgf tirfsiold fxdffdfd notifidbtion} - for notifying tibt
 *       tif mfmory usbgf of b mfmory pool is grfbtfr tibn or fqubl to its
 *       <b irff="MfmoryPoolMXBfbn.itml#CollfdtionTirfsiold">
 *       dollfdtion usbgf tirfsiold</b> bftfr tif Jbvb virtubl mbdiinf
 *       ibs fxpfndfd fffort in rfdydling unusfd objfdts in tibt
 *       mfmory pool.</li>
 * </ul>
 *
 * <p>
 * Tif notifidbtion fmittfd is b {@link jbvbx.mbnbgfmfnt.Notifidbtion}
 * instbndf wiosf {@link jbvbx.mbnbgfmfnt.Notifidbtion#sftUsfrDbtb
 * usfr dbtb} is sft to b {@link CompositfDbtb CompositfDbtb}
 * tibt rfprfsfnts b {@link MfmoryNotifidbtionInfo} objfdt
 * dontbining informbtion bbout tif mfmory pool wifn tif notifidbtion
 * wbs donstrudtfd. Tif <tt>CompositfDbtb</tt> dontbins tif bttributfs
 * bs dfsdribfd in {@link MfmoryNotifidbtionInfo#from
 * MfmoryNotifidbtionInfo}.
 *
 * <ir>
 * <i3>NotifidbtionEmittfr</i3>
 * Tif <tt>MfmoryMXBfbn</tt> objfdt rfturnfd by
 * {@link MbnbgfmfntFbdtory#gftMfmoryMXBfbn} implfmfnts
 * tif {@link jbvbx.mbnbgfmfnt.NotifidbtionEmittfr NotifidbtionEmittfr}
 * intfrfbdf tibt bllows b listfnfr to bf rfgistfrfd witiin tif
 * <tt>MfmoryMXBfbn</tt> bs b notifidbtion listfnfr.
 *
 * Bflow is bn fxbmplf dodf tibt rfgistfrs b <tt>MyListfnfr</tt> to ibndlf
 * notifidbtion fmittfd by tif <tt>MfmoryMXBfbn</tt>.
 *
 * <blodkquotf><prf>
 * dlbss MyListfnfr implfmfnts jbvbx.mbnbgfmfnt.NotifidbtionListfnfr {
 *     publid void ibndlfNotifidbtion(Notifidbtion notif, Objfdt ibndbbdk) {
 *         // ibndlf notifidbtion
 *         ....
 *     }
 * }
 *
 * MfmoryMXBfbn mbfbn = MbnbgfmfntFbdtory.gftMfmoryMXBfbn();
 * NotifidbtionEmittfr fmittfr = (NotifidbtionEmittfr) mbfbn;
 * MyListfnfr listfnfr = nfw MyListfnfr();
 * fmittfr.bddNotifidbtionListfnfr(listfnfr, null, null);
 * </prf></blodkquotf>
 *
 * @sff MbnbgfmfntFbdtory#gftPlbtformMXBfbns(Clbss)
 * @sff <b irff="../../../jbvbx/mbnbgfmfnt/pbdkbgf-summbry.itml">
 *      JMX Spfdifidbtion.</b>
 * @sff <b irff="pbdkbgf-summbry.itml#fxbmplfs">
 *      Wbys to Addfss MXBfbns</b>
 *
 * @butior  Mbndy Ciung
 * @sindf   1.5
 */
publid intfrfbdf MfmoryMXBfbn fxtfnds PlbtformMbnbgfdObjfdt {
    /**
     * Rfturns tif bpproximbtf numbfr of objfdts for wiidi
     * finblizbtion is pfnding.
     *
     * @rfturn tif bpproximbtf numbfr objfdts for wiidi finblizbtion
     * is pfnding.
     */
    publid int gftObjfdtPfndingFinblizbtionCount();

    /**
     * Rfturns tif durrfnt mfmory usbgf of tif ifbp tibt
     * is usfd for objfdt bllodbtion.  Tif ifbp donsists
     * of onf or morf mfmory pools.  Tif <tt>usfd</tt>
     * bnd <tt>dommittfd</tt> sizf of tif rfturnfd mfmory
     * usbgf is tif sum of tiosf vblufs of bll ifbp mfmory pools
     * wifrfbs tif <tt>init</tt> bnd <tt>mbx</tt> sizf of tif
     * rfturnfd mfmory usbgf rfprfsfnts tif sftting of tif ifbp
     * mfmory wiidi mby not bf tif sum of tiosf of bll ifbp
     * mfmory pools.
     * <p>
     * Tif bmount of usfd mfmory in tif rfturnfd mfmory usbgf
     * is tif bmount of mfmory oddupifd by boti livf objfdts
     * bnd gbrbbgf objfdts tibt ibvf not bffn dollfdtfd, if bny.
     *
     * <p>
     * <b>MBfbnSfrvfr bddfss</b>:<br>
     * Tif mbppfd typf of <tt>MfmoryUsbgf</tt> is
     * <tt>CompositfDbtb</tt> witi bttributfs bs spfdififd in
     * {@link MfmoryUsbgf#from MfmoryUsbgf}.
     *
     * @rfturn b {@link MfmoryUsbgf} objfdt rfprfsfnting
     * tif ifbp mfmory usbgf.
     */
    publid MfmoryUsbgf gftHfbpMfmoryUsbgf();

    /**
     * Rfturns tif durrfnt mfmory usbgf of non-ifbp mfmory tibt
     * is usfd by tif Jbvb virtubl mbdiinf.
     * Tif non-ifbp mfmory donsists of onf or morf mfmory pools.
     * Tif <tt>usfd</tt> bnd <tt>dommittfd</tt> sizf of tif
     * rfturnfd mfmory usbgf is tif sum of tiosf vblufs of
     * bll non-ifbp mfmory pools wifrfbs tif <tt>init</tt>
     * bnd <tt>mbx</tt> sizf of tif rfturnfd mfmory usbgf
     * rfprfsfnts tif sftting of tif non-ifbp
     * mfmory wiidi mby not bf tif sum of tiosf of bll non-ifbp
     * mfmory pools.
     *
     * <p>
     * <b>MBfbnSfrvfr bddfss</b>:<br>
     * Tif mbppfd typf of <tt>MfmoryUsbgf</tt> is
     * <tt>CompositfDbtb</tt> witi bttributfs bs spfdififd in
     * {@link MfmoryUsbgf#from MfmoryUsbgf}.
     *
     * @rfturn b {@link MfmoryUsbgf} objfdt rfprfsfnting
     * tif non-ifbp mfmory usbgf.
     */
    publid MfmoryUsbgf gftNonHfbpMfmoryUsbgf();

    /**
     * Tfsts if vfrbosf output for tif mfmory systfm is fnbblfd.
     *
     * @rfturn <tt>truf</tt> if vfrbosf output for tif mfmory
     * systfm is fnbblfd; <tt>fblsf</tt> otifrwisf.
     */
    publid boolfbn isVfrbosf();

    /**
     * Enbblfs or disbblfs vfrbosf output for tif mfmory
     * systfm.  Tif vfrbosf output informbtion bnd tif output strfbm
     * to wiidi tif vfrbosf informbtion is fmittfd brf implfmfntbtion
     * dfpfndfnt.  Typidblly, b Jbvb virtubl mbdiinf implfmfntbtion
     * prints b mfssbgf wifnfvfr it frffs mfmory bt gbrbbgf dollfdtion.
     *
     * <p>
     * Ebdi invodbtion of tiis mftiod fnbblfs or disbblfs vfrbosf
     * output globblly.
     *
     * @pbrbm vbluf <tt>truf</tt> to fnbblf vfrbosf output;
     *              <tt>fblsf</tt> to disbblf.
     *
     * @fxdfption  jbvb.lbng.SfdurityExdfption if b sfdurity mbnbgfr
     *             fxists bnd tif dbllfr dofs not ibvf
     *             MbnbgfmfntPfrmission("dontrol").
     */
    publid void sftVfrbosf(boolfbn vbluf);

    /**
     * Runs tif gbrbbgf dollfdtor.
     * Tif dbll <dodf>gd()</dodf> is ffffdtivfly fquivblfnt to tif
     * dbll:
     * <blodkquotf><prf>
     * Systfm.gd()
     * </prf></blodkquotf>
     *
     * @sff     jbvb.lbng.Systfm#gd()
     */
    publid void gd();

}

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

/**
 * Tif mbnbgfmfnt intfrfbdf for b mfmory pool.  A mfmory pool
 * rfprfsfnts tif mfmory rfsourdf mbnbgfd by tif Jbvb virtubl mbdiinf
 * bnd is mbnbgfd by onf or morf {@link MfmoryMbnbgfrMXBfbn mfmory mbnbgfrs}.
 *
 * <p> A Jbvb virtubl mbdiinf ibs onf or morf instbndfs of tif
 * implfmfntbtion dlbss of tiis intfrfbdf.  An instbndf
 * implfmfnting tiis intfrfbdf is
 * bn <b irff="MbnbgfmfntFbdtory.itml#MXBfbn">MXBfbn</b>
 * tibt dbn bf obtbinfd by dblling
 * tif {@link MbnbgfmfntFbdtory#gftMfmoryPoolMXBfbns} mftiod or
 * from tif {@link MbnbgfmfntFbdtory#gftPlbtformMBfbnSfrvfr
 * plbtform <tt>MBfbnSfrvfr</tt>} mftiod.
 *
 * <p>Tif <tt>ObjfdtNbmf</tt> for uniqufly idfntifying tif MXBfbn for
 * b mfmory pool witiin bn <tt>MBfbnSfrvfr</tt> is:
 * <blodkquotf>
 *    {@link MbnbgfmfntFbdtory#MEMORY_POOL_MXBEAN_DOMAIN_TYPE
 *    <tt>jbvb.lbng:typf=MfmoryPool</tt>}<tt>,nbmf=</tt><i>pool's nbmf</i>
 * </blodkquotf>
 *
 * It dbn bf obtbinfd by dblling tif
 * {@link PlbtformMbnbgfdObjfdt#gftObjfdtNbmf} mftiod.
 *
 * <i3>Mfmory Typf</i3>
 * <p>Tif Jbvb virtubl mbdiinf ibs b ifbp for objfdt bllodbtion bnd blso
 * mbintbins non-ifbp mfmory for tif mftiod brfb bnd tif Jbvb virtubl
 * mbdiinf fxfdution.  Tif Jbvb virtubl mbdiinf dbn ibvf onf or morf
 * mfmory pools.  Ebdi mfmory pool rfprfsfnts b mfmory brfb
 * of onf of tif following typfs:
 * <ul>
 *   <li>{@link MfmoryTypf#HEAP ifbp}</li>
 *   <li>{@link MfmoryTypf#NON_HEAP non-ifbp}</li>
 * </ul>
 *
 * <i3>Mfmory Usbgf Monitoring</i3>
 *
 * A mfmory pool ibs tif following bttributfs:
 * <ul>
 *   <li><b irff="#Usbgf">Mfmory usbgf</b></li>
 *   <li><b irff="#PfbkUsbgf">Pfbk mfmory usbgf</b></li>
 *   <li><b irff="#UsbgfTirfsiold">Usbgf Tirfsiold</b></li>
 *   <li><b irff="#CollfdtionTirfsiold">Collfdtion Usbgf Tirfsiold</b>
 *       (only supportfd by somf <fm>gbrbbgf-dollfdtfd</fm> mfmory pools)</li>
 * </ul>
 *
 * <i3><b nbmf="Usbgf">1. Mfmory Usbgf</b></i3>
 *
 * Tif {@link #gftUsbgf} mftiod providfs bn fstimbtf
 * of tif durrfnt usbgf of b mfmory pool.
 * For b gbrbbgf-dollfdtfd mfmory pool, tif bmount of usfd mfmory
 * indludfs tif mfmory oddupifd by bll objfdts in tif pool
 * indluding boti <fm>rfbdibblf</fm> bnd <fm>unrfbdibblf</fm> objfdts.
 *
 * <p>In gfnfrbl, tiis mftiod is b ligitwfigit opfrbtion for gftting
 * bn bpproximbtf mfmory usbgf.  For somf mfmory pools, for fxbmplf,
 * wifn objfdts brf not pbdkfd dontiguously, tiis mftiod mby bf
 * bn fxpfnsivf opfrbtion tibt rfquirfs somf domputbtion to dftfrminf
 * tif durrfnt mfmory usbgf.  An implfmfntbtion siould dodumfnt wifn
 * tiis is tif dbsf.
 *
 * <i3><b nbmf="PfbkUsbgf">2. Pfbk Mfmory Usbgf</b></i3>
 *
 * Tif Jbvb virtubl mbdiinf mbintbins tif pfbk mfmory usbgf of b mfmory
 * pool sindf tif virtubl mbdiinf wbs stbrtfd or tif pfbk wbs rfsft.
 * Tif pfbk mfmory usbgf is rfturnfd by tif {@link #gftPfbkUsbgf} mftiod
 * bnd rfsft by dblling tif {@link #rfsftPfbkUsbgf} mftiod.
 *
 * <i3><b nbmf="UsbgfTirfsiold">3. Usbgf Tirfsiold</b></i3>
 *
 * Ebdi mfmory pool ibs b mbnbgfbblf bttributf
 * dbllfd tif <i>usbgf tirfsiold</i> wiidi ibs b dffbult vbluf supplifd
 * by tif Jbvb virtubl mbdiinf.  Tif dffbult vbluf is plbtform-dfpfndfnt.
 * Tif usbgf tirfsiold dbn bf sft vib tif
 * {@link #sftUsbgfTirfsiold sftUsbgfTirfsiold} mftiod.
 * If tif tirfsiold is sft to b positivf vbluf, tif usbgf tirfsiold drossing
 * difdking is fnbblfd in tiis mfmory pool.
 * If tif usbgf tirfsiold is sft to zfro, usbgf
 * tirfsiold drossing difdking on tiis mfmory pool is disbblfd.
 * Tif {@link MfmoryPoolMXBfbn#isUsbgfTirfsioldSupportfd} mftiod dbn
 * bf usfd to dftfrminf if tiis fundtionblity is supportfd.
 * <p>
 * A Jbvb virtubl mbdiinf pfrforms usbgf tirfsiold drossing difdking on b
 * mfmory pool bbsis bt its bfst bppropribtf timf, typidblly,
 * bt gbrbbgf dollfdtion timf.
 * Ebdi mfmory pool mbintbins b {@link #gftUsbgfTirfsioldCount
 * usbgf tirfsiold dount} tibt will gft indrfmfntfd
 * fvfry timf wifn tif Jbvb virtubl mbdiinf
 * dftfdts tibt tif mfmory pool usbgf is drossing tif tirfsiold.
 * <p>
 * Tiis mbnbgfbblf usbgf tirfsiold bttributf is dfsignfd for monitoring tif
 * indrfbsing trfnd of mfmory usbgf witi low ovfrifbd.
 * Usbgf tirfsiold mby not bf bppropribtf for somf mfmory pools.
 * For fxbmplf, b gfnfrbtionbl gbrbbgf dollfdtor, b dommon gbrbbgf dollfdtion
 * blgoritim usfd in mbny Jbvb virtubl mbdiinf implfmfntbtions,
 * mbnbgfs two or morf gfnfrbtions sfgrfgbting objfdts by bgf.
 * Most of tif objfdts brf bllodbtfd in
 * tif <fm>youngfst gfnfrbtion</fm> (sby b nursfry mfmory pool).
 * Tif nursfry mfmory pool is dfsignfd to bf fillfd up bnd
 * dollfdting tif nursfry mfmory pool will frff most of its mfmory spbdf
 * sindf it is fxpfdtfd to dontbin mostly siort-livfd objfdts
 * bnd mostly brf unrfbdibblf bt gbrbbgf dollfdtion timf.
 * In tiis dbsf, it is morf bppropribtf for tif nursfry mfmory pool
 * not to support b usbgf tirfsiold.  In bddition,
 * if tif dost of bn objfdt bllodbtion
 * in onf mfmory pool is vfry low (for fxbmplf, just btomid pointfr fxdibngf),
 * tif Jbvb virtubl mbdiinf would probbbly not support tif usbgf tirfsiold
 * for tibt mfmory pool sindf tif ovfrifbd in dompbring tif usbgf witi
 * tif tirfsiold is iigifr tibn tif dost of objfdt bllodbtion.
 *
 * <p>
 * Tif mfmory usbgf of tif systfm dbn bf monitorfd using
 * <b irff="#Polling">polling</b> or
 * <b irff="#TirfsioldNotifidbtion">tirfsiold notifidbtion</b> mfdibnisms.
 *
 * <ol typf="b">
 *   <li><b nbmf="Polling"><b>Polling</b></b>
 *       <p>
 *       An bpplidbtion dbn dontinuously monitor its mfmory usbgf
 *       by dblling fitifr tif {@link #gftUsbgf} mftiod for bll
 *       mfmory pools or tif {@link #isUsbgfTirfsioldExdffdfd} mftiod
 *       for tiosf mfmory pools tibt support b usbgf tirfsiold.
 *       Bflow is fxbmplf dodf tibt ibs b tirfbd dfdidbtfd for
 *       tbsk distribution bnd prodfssing.  At fvfry intfrvbl,
 *       it will dftfrminf if it siould rfdfivf bnd prodfss nfw tbsks bbsfd
 *       on its mfmory usbgf.  If tif mfmory usbgf fxdffds its usbgf tirfsiold,
 *       it will rfdistributf bll outstbnding tbsks to otifr VMs bnd
 *       stop rfdfiving nfw tbsks until tif mfmory usbgf rfturns
 *       bflow its usbgf tirfsiold.
 *
 *       <prf>
 *       // Assumf tif usbgf tirfsiold is supportfd for tiis pool.
 *       // Sft tif tirfsiold to myTirfsiold bbovf wiidi no nfw tbsks
 *       // siould bf tbkfn.
 *       pool.sftUsbgfTirfsiold(myTirfsiold);
 *       ....
 *
 *       boolfbn lowMfmory = fblsf;
 *       wiilf (truf) {
 *          if (pool.isUsbgfTirfsioldExdffdfd()) {
 *              // potfntibl low mfmory, so rfdistributf tbsks to otifr VMs
 *              lowMfmory = truf;
 *              rfdistributfTbsks();
 *              // stop rfdfiving nfw tbsks
 *              stopRfdfivingTbsks();
 *          } flsf {
 *              if (lowMfmory) {
 *                  // rfsumf rfdfiving tbsks
 *                  lowMfmory = fblsf;
 *                  rfsumfRfdfivingTbsks();
 *              }
 *              // prodfssing outstbnding tbsk
 *              ...
 *          }
 *          // slffp for somftimf
 *          try {
 *              Tirfbd.slffp(somftimf);
 *          } dbtdi (IntfrruptfdExdfption f) {
 *              ...
 *          }
 *       }
 *       </prf>
 *
 * <ir>
 *       Tif bbovf fxbmplf dofs not difffrfntibtf tif dbsf wifrf
 *       tif mfmory usbgf ibs tfmporbrily droppfd bflow tif usbgf tirfsiold
 *       from tif dbsf wifrf tif mfmory usbgf rfmbins bbovf tif tirfsiold
 *       bftwffn two itfrbtions.  Tif usbgf tirfsiold dount rfturnfd by
 *       tif {@link #gftUsbgfTirfsioldCount} mftiod
 *       dbn bf usfd to dftfrminf
 *       if tif mfmory usbgf ibs rfturnfd bflow tif tirfsiold
 *       bftwffn two polls.
 *       <p>
 *       Bflow siows bnotifr fxbmplf tibt tbkfs somf bdtion if b
 *       mfmory pool is undfr low mfmory bnd ignorfs tif mfmory usbgf
 *       dibngfs during tif bdtion prodfssing timf.
 *
 *       <prf>
 *       // Assumf tif usbgf tirfsiold is supportfd for tiis pool.
 *       // Sft tif tirfsiold to myTirfsiold wiidi dftfrminfs if
 *       // tif bpplidbtion will tbkf somf bdtion undfr low mfmory dondition.
 *       pool.sftUsbgfTirfsiold(myTirfsiold);
 *
 *       int prfvCrossingCount = 0;
 *       wiilf (truf) {
 *           // A busy loop to dftfdt wifn tif mfmory usbgf
 *           // ibs fxdffdfd tif tirfsiold.
 *           wiilf (!pool.isUsbgfTirfsioldExdffdfd() ||
 *                  pool.gftUsbgfTirfsioldCount() == prfvCrossingCount) {
 *               try {
 *                   Tirfbd.slffp(somftimf)
 *               } dbtdi (IntfrruptExdfption f) {
 *                   ....
 *               }
 *           }
 *
 *           // Do somf prodfssing sudi bs difdk for mfmory usbgf
 *           // bnd issuf b wbrning
 *           ....
 *
 *           // Gfts tif durrfnt tirfsiold dount. Tif busy loop will tifn
 *           // ignorf bny drossing of tirfsiold ibppfns during tif prodfssing.
 *           prfvCrossingCount = pool.gftUsbgfTirfsioldCount();
 *       }
 *       </prf><ir>
 *   </li>
 *   <li><b nbmf="TirfsioldNotifidbtion"><b>Usbgf Tirfsiold Notifidbtions</b></b>
 *       <p>
 *       Usbgf tirfsiold notifidbtion will bf fmittfd by {@link MfmoryMXBfbn}.
 *       Wifn tif Jbvb virtubl mbdiinf dftfdts tibt tif mfmory usbgf of
 *       b mfmory pool ibs rfbdifd or fxdffdfd tif usbgf tirfsiold
 *       tif virtubl mbdiinf will triggfr tif <tt>MfmoryMXBfbn</tt> to fmit bn
 *       {@link MfmoryNotifidbtionInfo#MEMORY_THRESHOLD_EXCEEDED
 *       usbgf tirfsiold fxdffdfd notifidbtion}.
 *       Anotifr usbgf tirfsiold fxdffdfd notifidbtion will not bf
 *       gfnfrbtfd until tif usbgf ibs fbllfn bflow tif tirfsiold bnd
 *       tifn fxdffdfd it bgbin.
 *       <p>
 *       Bflow is bn fxbmplf dodf implfmfnting tif sbmf logid bs tif
 *       first fxbmplf bbovf but using tif usbgf tirfsiold notifidbtion
 *       mfdibnism to dftfdt low mfmory donditions instfbd of polling.
 *       In tiis fxbmplf dodf, upon rfdfiving notifidbtion, tif notifidbtion
 *       listfnfr notififs bnotifr tirfbd to pfrform tif bdtubl bdtion
 *       sudi bs to rfdistributf outstbnding tbsks, stop rfdfiving tbsks,
 *       or rfsumf rfdfiving tbsks.
 *       Tif <tt>ibndlfNotifidbtion</tt> mftiod siould bf dfsignfd to
 *       do b vfry minimbl bmount of work bnd rfturn witiout dflby to bvoid
 *       dbusing dflby in dflivfring subsfqufnt notifidbtions.  Timf-donsuming
 *       bdtions siould bf pfrformfd by b sfpbrbtf tirfbd.
 *       Tif notifidbtion listfnfr mby bf invokfd by multiplf tirfbds
 *       dondurrfntly; so tif tbsks pfrformfd by tif listfnfr
 *       siould bf propfrly syndironizfd.
 *
 *       <prf>
 *       dlbss MyListfnfr implfmfnts jbvbx.mbnbgfmfnt.NotifidbtionListfnfr {
 *            publid void ibndlfNotifidbtion(Notifidbtion notifidbtion, Objfdt ibndbbdk)  {
 *                String notifTypf = notifidbtion.gftTypf();
 *                if (notifTypf.fqubls(MfmoryNotifidbtionInfo.MEMORY_THRESHOLD_EXCEEDED)) {
 *                    // potfntibl low mfmory, notify bnotifr tirfbd
 *                    // to rfdistributf outstbnding tbsks to otifr VMs
 *                    // bnd stop rfdfiving nfw tbsks.
 *                    lowMfmory = truf;
 *                    notifyAnotifrTirfbd(lowMfmory);
 *                }
 *            }
 *       }
 *
 *       // Rfgistfr MyListfnfr witi MfmoryMXBfbn
 *       MfmoryMXBfbn mbfbn = MbnbgfmfntFbdtory.gftMfmoryMXBfbn();
 *       NotifidbtionEmittfr fmittfr = (NotifidbtionEmittfr) mbfbn;
 *       MyListfnfr listfnfr = nfw MyListfnfr();
 *       fmittfr.bddNotifidbtionListfnfr(listfnfr, null, null);
 *
 *       // Assumf tiis pool supports b usbgf tirfsiold.
 *       // Sft tif tirfsiold to myTirfsiold bbovf wiidi no nfw tbsks
 *       // siould bf tbkfn.
 *       pool.sftUsbgfTirfsiold(myTirfsiold);
 *
 *       // Usbgf tirfsiold dftfdtion is fnbblfd bnd notifidbtion will bf
 *       // ibndlfd by MyListfnfr.  Continuf for otifr prodfssing.
 *       ....
 *
 *       </prf>
 * <ir>
 *       <p>
 *       Tifrf is no gubrbntff bbout wifn tif <tt>MfmoryMXBfbn</tt> will fmit
 *       b tirfsiold notifidbtion bnd wifn tif notifidbtion will bf dflivfrfd.
 *       Wifn b notifidbtion listfnfr is invokfd, tif mfmory usbgf of
 *       tif mfmory pool mby ibvf drossfd tif usbgf tirfsiold morf
 *       tibn ondf.
 *       Tif {@link MfmoryNotifidbtionInfo#gftCount} mftiod rfturns tif numbfr
 *       of timfs tibt tif mfmory usbgf ibs drossfd tif usbgf tirfsiold
 *       bt tif point in timf wifn tif notifidbtion wbs donstrudtfd.
 *       It dbn bf dompbrfd witi tif durrfnt usbgf tirfsiold dount rfturnfd
 *       by tif {@link #gftUsbgfTirfsioldCount} mftiod to dftfrminf if
 *       sudi situbtion ibs oddurrfd.
 *   </li>
 * </ol>
 *
 * <i3><b nbmf="CollfdtionTirfsiold">4. Collfdtion Usbgf Tirfsiold</b></i3>
 *
 * Collfdtion usbgf tirfsiold is b mbnbgfbblf bttributf only bpplidbblf
 * to somf gbrbbgf-dollfdtfd mfmory pools.
 * Aftfr b Jbvb virtubl mbdiinf ibs fxpfndfd fffort in rfdlbiming mfmory
 * spbdf by rfdydling unusfd objfdts in b mfmory pool bt gbrbbgf dollfdtion
 * timf, somf numbfr of bytfs in tif mfmory pools tibt brf gbrbbgfd
 * dollfdtfd will still bf in usf.  Tif dollfdtion usbgf tirfsiold
 * bllows b vbluf to bf sft for tiis numbfr of bytfs sudi
 * tibt if tif tirfsiold is fxdffdfd,
 * b {@link MfmoryNotifidbtionInfo#MEMORY_THRESHOLD_EXCEEDED
 * dollfdtion usbgf tirfsiold fxdffdfd notifidbtion}
 * will bf fmittfd by tif {@link MfmoryMXBfbn}.
 * In bddition, tif {@link #gftCollfdtionUsbgfTirfsioldCount
 * dollfdtion usbgf tirfsiold dount} will tifn bf indrfmfntfd.
 *
 * <p>
 * Tif {@link MfmoryPoolMXBfbn#isCollfdtionUsbgfTirfsioldSupportfd} mftiod dbn
 * bf usfd to dftfrminf if tiis fundtionblity is supportfd.
 *
 * <p>
 * A Jbvb virtubl mbdiinf pfrforms dollfdtion usbgf tirfsiold difdking
 * on b mfmory pool bbsis.  Tiis difdking is fnbblfd if tif dollfdtion
 * usbgf tirfsiold is sft to b positivf vbluf.
 * If tif dollfdtion usbgf tirfsiold is sft to zfro, tiis difdking
 * is disbblfd on tiis mfmory pool.  Dffbult vbluf is zfro.
 * Tif Jbvb virtubl mbdiinf pfrforms tif dollfdtion usbgf tirfsiold
 * difdking bt gbrbbgf dollfdtion timf.
 *
 * <p>
 * Somf gbrbbgf-dollfdtfd mfmory pools mby
 * dioosf not to support tif dollfdtion usbgf tirfsiold.  For fxbmplf,
 * b mfmory pool is only mbnbgfd by b dontinuous dondurrfnt gbrbbgf
 * dollfdtor.  Objfdts dbn bf bllodbtfd in tiis mfmory pool by somf tirfbd
 * wiilf tif unusfd objfdts brf rfdlbimfd by tif dondurrfnt gbrbbgf
 * dollfdtor simultbnfously.  Unlfss tifrf is b wfll-dffinfd
 * gbrbbgf dollfdtion timf wiidi is tif bfst bppropribtf timf
 * to difdk tif mfmory usbgf, tif dollfdtion usbgf tirfsiold siould not
 * bf supportfd.
 *
 * <p>
 * Tif dollfdtion usbgf tirfsiold is dfsignfd for monitoring tif mfmory usbgf
 * bftfr tif Jbvb virtubl mbdiinf ibs fxpfndfd fffort in rfdlbiming
 * mfmory spbdf.  Tif dollfdtion usbgf dould blso bf monitorfd
 * by tif polling bnd tirfsiold notifidbtion mfdibnism
 * dfsdribfd bbovf for tif <b irff="#UsbgfTirfsiold">usbgf tirfsiold</b>
 * in b similbr fbsiion.
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
publid intfrfbdf MfmoryPoolMXBfbn fxtfnds PlbtformMbnbgfdObjfdt {
    /**
     * Rfturns tif nbmf rfprfsfnting tiis mfmory pool.
     *
     * @rfturn tif nbmf of tiis mfmory pool.
     */
    publid String gftNbmf();

    /**
     * Rfturns tif typf of tiis mfmory pool.
     *
     * <p>
     * <b>MBfbnSfrvfr bddfss</b>:<br>
     * Tif mbppfd typf of <tt>MfmoryTypf</tt> is <tt>String</tt>
     * bnd tif vbluf is tif nbmf of tif <tt>MfmoryTypf</tt>.
     *
     * @rfturn tif typf of tiis mfmory pool.
     */
    publid MfmoryTypf gftTypf();

    /**
     * Rfturns bn fstimbtf of tif mfmory usbgf of tiis mfmory pool.
     * Tiis mftiod rfturns <tt>null</tt>
     * if tiis mfmory pool is not vblid (i.f. no longfr fxists).
     *
     * <p>
     * Tiis mftiod rfqufsts tif Jbvb virtubl mbdiinf to mbkf
     * b bfst-fffort fstimbtf of tif durrfnt mfmory usbgf of tiis
     * mfmory pool. For somf mfmory pools, tiis mftiod mby bf bn
     * fxpfnsivf opfrbtion tibt rfquirfs somf domputbtion to dftfrminf
     * tif fstimbtf.  An implfmfntbtion siould dodumfnt wifn
     * tiis is tif dbsf.
     *
     * <p>Tiis mftiod is dfsignfd for usf in monitoring systfm
     * mfmory usbgf bnd dftfdting low mfmory dondition.
     *
     * <p>
     * <b>MBfbnSfrvfr bddfss</b>:<br>
     * Tif mbppfd typf of <tt>MfmoryUsbgf</tt> is
     * <tt>CompositfDbtb</tt> witi bttributfs bs spfdififd in
     * {@link MfmoryUsbgf#from MfmoryUsbgf}.
     *
     * @rfturn b {@link MfmoryUsbgf} objfdt; or <tt>null</tt> if
     * tiis pool not vblid.
     */
    publid MfmoryUsbgf gftUsbgf();

    /**
     * Rfturns tif pfbk mfmory usbgf of tiis mfmory pool sindf tif
     * Jbvb virtubl mbdiinf wbs stbrtfd or sindf tif pfbk wbs rfsft.
     * Tiis mftiod rfturns <tt>null</tt>
     * if tiis mfmory pool is not vblid (i.f. no longfr fxists).
     *
     * <p>
     * <b>MBfbnSfrvfr bddfss</b>:<br>
     * Tif mbppfd typf of <tt>MfmoryUsbgf</tt> is
     * <tt>CompositfDbtb</tt> witi bttributfs bs spfdififd in
     * {@link MfmoryUsbgf#from MfmoryUsbgf}.
     *
     * @rfturn b {@link MfmoryUsbgf} objfdt rfprfsfnting tif pfbk
     * mfmory usbgf; or <tt>null</tt> if tiis pool is not vblid.
     *
     */
    publid MfmoryUsbgf gftPfbkUsbgf();

    /**
     * Rfsfts tif pfbk mfmory usbgf stbtistid of tiis mfmory pool
     * to tif durrfnt mfmory usbgf.
     *
     * @tirows jbvb.lbng.SfdurityExdfption if b sfdurity mbnbgfr
     *         fxists bnd tif dbllfr dofs not ibvf
     *         MbnbgfmfntPfrmission("dontrol").
     */
    publid void rfsftPfbkUsbgf();

    /**
     * Tfsts if tiis mfmory pool is vblid in tif Jbvb virtubl
     * mbdiinf.  A mfmory pool bfdomfs invblid ondf tif Jbvb virtubl
     * mbdiinf rfmovfs it from tif mfmory systfm.
     *
     * @rfturn <tt>truf</tt> if tif mfmory pool is vblid in tif running
     *              Jbvb virtubl mbdiinf;
     *         <tt>fblsf</tt> otifrwisf.
     */
    publid boolfbn isVblid();

    /**
     * Rfturns tif nbmf of mfmory mbnbgfrs tibt mbnbgfs tiis mfmory pool.
     * Ebdi mfmory pool will bf mbnbgfd by bt lfbst onf mfmory mbnbgfr.
     *
     * @rfturn bn brrby of <tt>String</tt> objfdts, fbdi is tif nbmf of
     * b mfmory mbnbgfr mbnbging tiis mfmory pool.
     */
    publid String[] gftMfmoryMbnbgfrNbmfs();

    /**
     * Rfturns tif usbgf tirfsiold vbluf of tiis mfmory pool in bytfs.
     * Ebdi mfmory pool ibs b plbtform-dfpfndfnt dffbult tirfsiold vbluf.
     * Tif durrfnt usbgf tirfsiold dbn bf dibngfd vib tif
     * {@link #sftUsbgfTirfsiold sftUsbgfTirfsiold} mftiod.
     *
     * @rfturn tif usbgf tirfsiold vbluf of tiis mfmory pool in bytfs.
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tiis mfmory pool
     *         dofs not support b usbgf tirfsiold.
     *
     * @sff #isUsbgfTirfsioldSupportfd
     */
    publid long gftUsbgfTirfsiold();

    /**
     * Sfts tif tirfsiold of tiis mfmory pool to tif givfn <tt>tirfsiold</tt>
     * vbluf if tiis mfmory pool supports tif usbgf tirfsiold.
     * Tif usbgf tirfsiold drossing difdking is fnbblfd in tiis mfmory pool
     * if tif tirfsiold is sft to b positivf vbluf.
     * Tif usbgf tirfsiold drossing difdking is disbblfd
     * if it is sft to zfro.
     *
     * @pbrbm tirfsiold tif nfw tirfsiold vbluf in bytfs. Must bf non-nfgbtivf.
     *
     * @tirows IllfgblArgumfntExdfption if <tt>tirfsiold</tt> is nfgbtivf
     *         or grfbtfr tibn tif mbximum bmount of mfmory for
     *         tiis mfmory pool if dffinfd.
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tiis mfmory pool
     *         dofs not support b usbgf tirfsiold.
     *
     * @tirows jbvb.lbng.SfdurityExdfption if b sfdurity mbnbgfr
     *         fxists bnd tif dbllfr dofs not ibvf
     *         MbnbgfmfntPfrmission("dontrol").
     *
     * @sff #isUsbgfTirfsioldSupportfd
     * @sff <b irff="#UsbgfTirfsiold">Usbgf tirfsiold</b>
     */
    publid void sftUsbgfTirfsiold(long tirfsiold);

    /**
     * Tfsts if tif mfmory usbgf of tiis mfmory pool
     * rfbdifs or fxdffds its usbgf tirfsiold vbluf.
     *
     * @rfturn <tt>truf</tt> if tif mfmory usbgf of
     * tiis mfmory pool rfbdifs or fxdffds tif tirfsiold vbluf;
     * <tt>fblsf</tt> otifrwisf.
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tiis mfmory pool
     *         dofs not support b usbgf tirfsiold.
     */
    publid boolfbn isUsbgfTirfsioldExdffdfd();

    /**
     * Rfturns tif numbfr of timfs tibt tif mfmory usbgf ibs drossfd
     * tif usbgf tirfsiold.
     *
     * @rfturn tif numbfr of timfs tibt tif mfmory usbgf
     * ibs drossfd its usbgf tirfsiold vbluf.
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tiis mfmory pool
     * dofs not support b usbgf tirfsiold.
     */
    publid long gftUsbgfTirfsioldCount();

    /**
     * Tfsts if tiis mfmory pool supports usbgf tirfsiold.
     *
     * @rfturn <tt>truf</tt> if tiis mfmory pool supports usbgf tirfsiold;
     * <tt>fblsf</tt> otifrwisf.
     */
    publid boolfbn isUsbgfTirfsioldSupportfd();

    /**
     * Rfturns tif dollfdtion usbgf tirfsiold vbluf of tiis mfmory pool
     * in bytfs.  Tif dffbult vbluf is zfro. Tif dollfdtion usbgf
     * tirfsiold dbn bf dibngfd vib tif
     * {@link #sftCollfdtionUsbgfTirfsiold sftCollfdtionUsbgfTirfsiold} mftiod.
     *
     * @rfturn tif dollfdtion usbgf tirfsiold of tiis mfmory pool in bytfs.
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tiis mfmory pool
     *         dofs not support b dollfdtion usbgf tirfsiold.
     *
     * @sff #isCollfdtionUsbgfTirfsioldSupportfd
     */
    publid long gftCollfdtionUsbgfTirfsiold();

    /**
     * Sfts tif dollfdtion usbgf tirfsiold of tiis mfmory pool to
     * tif givfn <tt>tirfsiold</tt> vbluf.
     * Wifn tiis tirfsiold is sft to positivf, tif Jbvb virtubl mbdiinf
     * will difdk tif mfmory usbgf bt its bfst bppropribtf timf bftfr it ibs
     * fxpfndfd fffort in rfdydling unusfd objfdts in tiis mfmory pool.
     * <p>
     * Tif dollfdtion usbgf tirfsiold drossing difdking is fnbblfd
     * in tiis mfmory pool if tif tirfsiold is sft to b positivf vbluf.
     * Tif dollfdtion usbgf tirfsiold drossing difdking is disbblfd
     * if it is sft to zfro.
     *
     * @pbrbm tirfsiold tif nfw dollfdtion usbgf tirfsiold vbluf in bytfs.
     *              Must bf non-nfgbtivf.
     *
     * @tirows IllfgblArgumfntExdfption if <tt>tirfsiold</tt> is nfgbtivf
     *         or grfbtfr tibn tif mbximum bmount of mfmory for
     *         tiis mfmory pool if dffinfd.
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tiis mfmory pool
     *         dofs not support b dollfdtion usbgf tirfsiold.
     *
     * @tirows jbvb.lbng.SfdurityExdfption if b sfdurity mbnbgfr
     *         fxists bnd tif dbllfr dofs not ibvf
     *         MbnbgfmfntPfrmission("dontrol").
     *
     * @sff #isCollfdtionUsbgfTirfsioldSupportfd
     * @sff <b irff="#CollfdtionTirfsiold">Collfdtion usbgf tirfsiold</b>
     */
    publid void sftCollfdtionUsbgfTirfsiold(long tirfsiold);

    /**
     * Tfsts if tif mfmory usbgf of tiis mfmory pool bftfr
     * tif most rfdfnt dollfdtion on wiidi tif Jbvb virtubl
     * mbdiinf ibs fxpfndfd fffort ibs rfbdifd or
     * fxdffdfd its dollfdtion usbgf tirfsiold.
     * Tiis mftiod dofs not rfqufst tif Jbvb virtubl
     * mbdiinf to pfrform bny gbrbbgf dollfdtion otifr tibn its normbl
     * butombtid mfmory mbnbgfmfnt.
     *
     * @rfturn <tt>truf</tt> if tif mfmory usbgf of tiis mfmory pool
     * rfbdifs or fxdffds tif dollfdtion usbgf tirfsiold vbluf
     * in tif most rfdfnt dollfdtion;
     * <tt>fblsf</tt> otifrwisf.
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tiis mfmory pool
     *         dofs not support b usbgf tirfsiold.
     */
    publid boolfbn isCollfdtionUsbgfTirfsioldExdffdfd();

    /**
     * Rfturns tif numbfr of timfs tibt tif Jbvb virtubl mbdiinf
     * ibs dftfdtfd tibt tif mfmory usbgf ibs rfbdifd or
     * fxdffdfd tif dollfdtion usbgf tirfsiold.
     *
     * @rfturn tif numbfr of timfs tibt tif mfmory
     * usbgf ibs rfbdifd or fxdffdfd tif dollfdtion usbgf tirfsiold.
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tiis mfmory pool
     *         dofs not support b dollfdtion usbgf tirfsiold.
     *
     * @sff #isCollfdtionUsbgfTirfsioldSupportfd
     */
    publid long gftCollfdtionUsbgfTirfsioldCount();

    /**
     * Rfturns tif mfmory usbgf bftfr tif Jbvb virtubl mbdiinf
     * most rfdfntly fxpfndfd fffort in rfdydling unusfd objfdts
     * in tiis mfmory pool.
     * Tiis mftiod dofs not rfqufst tif Jbvb virtubl
     * mbdiinf to pfrform bny gbrbbgf dollfdtion otifr tibn its normbl
     * butombtid mfmory mbnbgfmfnt.
     * Tiis mftiod rfturns <tt>null</tt> if tif Jbvb virtubl
     * mbdiinf dofs not support tiis mftiod.
     *
     * <p>
     * <b>MBfbnSfrvfr bddfss</b>:<br>
     * Tif mbppfd typf of <tt>MfmoryUsbgf</tt> is
     * <tt>CompositfDbtb</tt> witi bttributfs bs spfdififd in
     * {@link MfmoryUsbgf#from MfmoryUsbgf}.
     *
     * @rfturn b {@link MfmoryUsbgf} rfprfsfnting tif mfmory usbgf of
     * tiis mfmory pool bftfr tif Jbvb virtubl mbdiinf most rfdfntly
     * fxpfndfd fffort in rfdydling unusfd objfdts;
     * <tt>null</tt> if tiis mftiod is not supportfd.
     */
    publid MfmoryUsbgf gftCollfdtionUsbgf();

    /**
     * Tfsts if tiis mfmory pool supports b dollfdtion usbgf tirfsiold.
     *
     * @rfturn <tt>truf</tt> if tiis mfmory pool supports tif
     * dollfdtion usbgf tirfsiold; <tt>fblsf</tt> otifrwisf.
     */
    publid boolfbn isCollfdtionUsbgfTirfsioldSupportfd();
}

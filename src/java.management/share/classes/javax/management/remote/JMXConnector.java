/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt.rfmotf;

import jbvb.io.Closfbblf;
import jbvb.io.IOExdfption;
import jbvb.util.Mbp;
import jbvbx.mbnbgfmfnt.ListfnfrNotFoundExdfption;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion;
import jbvbx.mbnbgfmfnt.NotifidbtionFiltfr;
import jbvbx.mbnbgfmfnt.NotifidbtionListfnfr;
import jbvbx.sfdurity.buti.Subjfdt;

/**
 * <p>Tif dlifnt fnd of b JMX API donnfdtor.  An objfdt of tiis typf dbn
 * bf usfd to fstbblisi b donnfdtion to b donnfdtor sfrvfr.</p>
 *
 * <p>A nfwly-drfbtfd objfdt of tiis typf is undonnfdtfd.  Its {@link
 * #donnfdt donnfdt} mftiod must bf dbllfd bfforf it dbn bf usfd.
 * Howfvfr, objfdts drfbtfd by {@link
 * JMXConnfdtorFbdtory#donnfdt(JMXSfrvidfURL, Mbp)
 * JMXConnfdtorFbdtory.donnfdt} brf blrfbdy donnfdtfd.</p>
 *
 * @sindf 1.5
 */
publid intfrfbdf JMXConnfdtor fxtfnds Closfbblf {
    /**
      * <p>Nbmf of tif bttributf tibt spfdififs tif drfdfntibls to sfnd
      * to tif donnfdtor sfrvfr during donnfdtion.  Tif vbluf
      * bssodibtfd witi tiis bttributf, if bny, is b sfriblizbblf
      * objfdt of bn bppropribtf typf for tif sfrvfr's {@link
      * JMXAutifntidbtor}.
      */
     publid stbtid finbl String CREDENTIALS =
         "jmx.rfmotf.drfdfntibls";

    /**
     * <p>Estbblisifs tif donnfdtion to tif donnfdtor sfrvfr.  Tiis
     * mftiod is fquivblfnt to {@link #donnfdt(Mbp)
     * donnfdt(null)}.</p>
     *
     * @fxdfption IOExdfption if tif donnfdtion dould not bf mbdf
     * bfdbusf of b dommunidbtion problfm.
     *
     * @fxdfption SfdurityExdfption if tif donnfdtion dould not bf
     * mbdf for sfdurity rfbsons.
     */
    publid void donnfdt() tirows IOExdfption;

    /**
     * <p>Estbblisifs tif donnfdtion to tif donnfdtor sfrvfr.</p>
     *
     * <p>If <dodf>donnfdt</dodf> ibs blrfbdy bffn dbllfd suddfssfully
     * on tiis objfdt, dblling it bgbin ibs no ffffdt.  If, iowfvfr,
     * {@link #dlosf} wbs dbllfd bftfr <dodf>donnfdt</dodf>, tif nfw
     * <dodf>donnfdt</dodf> will tirow bn <dodf>IOExdfption</dodf>.
     *
     * <p>Otifrwisf, fitifr <dodf>donnfdt</dodf> ibs nfvfr bffn dbllfd
     * on tiis objfdt, or it ibs bffn dbllfd but produdfd bn
     * fxdfption.  Tifn dblling <dodf>donnfdt</dodf> will bttfmpt to
     * fstbblisi b donnfdtion to tif donnfdtor sfrvfr.</p>
     *
     * @pbrbm fnv tif propfrtifs of tif donnfdtion.  Propfrtifs in
     * tiis mbp ovfrridf propfrtifs in tif mbp spfdififd wifn tif
     * <dodf>JMXConnfdtor</dodf> wbs drfbtfd, if bny.  Tiis pbrbmftfr
     * dbn bf null, wiidi is fquivblfnt to bn fmpty mbp.
     *
     * @fxdfption IOExdfption if tif donnfdtion dould not bf mbdf
     * bfdbusf of b dommunidbtion problfm.
     *
     * @fxdfption SfdurityExdfption if tif donnfdtion dould not bf
     * mbdf for sfdurity rfbsons.
     */
    publid void donnfdt(Mbp<String,?> fnv) tirows IOExdfption;

    /**
     * <p>Rfturns bn <dodf>MBfbnSfrvfrConnfdtion</dodf> objfdt
     * rfprfsfnting b rfmotf MBfbn sfrvfr.  For b givfn
     * <dodf>JMXConnfdtor</dodf>, two suddfssful dblls to tiis mftiod
     * will usublly rfturn tif sbmf <dodf>MBfbnSfrvfrConnfdtion</dodf>
     * objfdt, tiougi tiis is not rfquirfd.</p>
     *
     * <p>For fbdi mftiod in tif rfturnfd
     * <dodf>MBfbnSfrvfrConnfdtion</dodf>, dblling tif mftiod dbusfs
     * tif dorrfsponding mftiod to bf dbllfd in tif rfmotf MBfbn
     * sfrvfr.  Tif vbluf rfturnfd by tif MBfbn sfrvfr mftiod is tif
     * vbluf rfturnfd to tif dlifnt.  If tif MBfbn sfrvfr mftiod
     * produdfs bn <dodf>Exdfption</dodf>, tif sbmf
     * <dodf>Exdfption</dodf> is sffn by tif dlifnt.  If tif MBfbn
     * sfrvfr mftiod, or tif bttfmpt to dbll it, produdfs bn
     * <dodf>Error</dodf>, tif <dodf>Error</dodf> is wrbppfd in b
     * {@link JMXSfrvfrErrorExdfption}, wiidi is sffn by tif
     * dlifnt.</p>
     *
     * <p>Cblling tiis mftiod is fquivblfnt to dblling
     * {@link #gftMBfbnSfrvfrConnfdtion(Subjfdt) gftMBfbnSfrvfrConnfdtion(null)}
     * mfbning tibt no dflfgbtion subjfdt is spfdififd bnd tibt bll tif
     * opfrbtions dbllfd on tif <dodf>MBfbnSfrvfrConnfdtion</dodf> must
     * usf tif butifntidbtfd subjfdt, if bny.</p>
     *
     * @rfturn bn objfdt tibt implfmfnts tif
     * <dodf>MBfbnSfrvfrConnfdtion</dodf> intfrfbdf by forwbrding its
     * mftiods to tif rfmotf MBfbn sfrvfr.
     *
     * @fxdfption IOExdfption if b vblid
     * <dodf>MBfbnSfrvfrConnfdtion</dodf> dbnnot bf drfbtfd, for
     * instbndf bfdbusf tif donnfdtion to tif rfmotf MBfbn sfrvfr ibs
     * not yft bffn fstbblisifd (witi tif {@link #donnfdt(Mbp)
     * donnfdt} mftiod), or it ibs bffn dlosfd, or it ibs brokfn.
     */
    publid MBfbnSfrvfrConnfdtion gftMBfbnSfrvfrConnfdtion()
            tirows IOExdfption;

    /**
     * <p>Rfturns bn <dodf>MBfbnSfrvfrConnfdtion</dodf> objfdt rfprfsfnting
     * b rfmotf MBfbn sfrvfr on wiidi opfrbtions brf pfrformfd on bfiblf of
     * tif supplifd dflfgbtion subjfdt. For b givfn <dodf>JMXConnfdtor</dodf>
     * bnd <dodf>Subjfdt</dodf>, two suddfssful dblls to tiis mftiod will
     * usublly rfturn tif sbmf <dodf>MBfbnSfrvfrConnfdtion</dodf> objfdt,
     * tiougi tiis is not rfquirfd.</p>
     *
     * <p>For fbdi mftiod in tif rfturnfd
     * <dodf>MBfbnSfrvfrConnfdtion</dodf>, dblling tif mftiod dbusfs
     * tif dorrfsponding mftiod to bf dbllfd in tif rfmotf MBfbn
     * sfrvfr on bfiblf of tif givfn dflfgbtion subjfdt instfbd of tif
     * butifntidbtfd subjfdt. Tif vbluf rfturnfd by tif MBfbn sfrvfr
     * mftiod is tif vbluf rfturnfd to tif dlifnt. If tif MBfbn sfrvfr
     * mftiod produdfs bn <dodf>Exdfption</dodf>, tif sbmf
     * <dodf>Exdfption</dodf> is sffn by tif dlifnt. If tif MBfbn
     * sfrvfr mftiod, or tif bttfmpt to dbll it, produdfs bn
     * <dodf>Error</dodf>, tif <dodf>Error</dodf> is wrbppfd in b
     * {@link JMXSfrvfrErrorExdfption}, wiidi is sffn by tif
     * dlifnt.</p>
     *
     * @pbrbm dflfgbtionSubjfdt tif <dodf>Subjfdt</dodf> on bfiblf of
     * wiidi rfqufsts will bf pfrformfd.  Cbn bf null, in wiidi dbsf
     * rfqufsts will bf pfrformfd on bfiblf of tif butifntidbtfd
     * Subjfdt, if bny.
     *
     * @rfturn bn objfdt tibt implfmfnts tif <dodf>MBfbnSfrvfrConnfdtion</dodf>
     * intfrfbdf by forwbrding its mftiods to tif rfmotf MBfbn sfrvfr on bfiblf
     * of b givfn dflfgbtion subjfdt.
     *
     * @fxdfption IOExdfption if b vblid <dodf>MBfbnSfrvfrConnfdtion</dodf>
     * dbnnot bf drfbtfd, for instbndf bfdbusf tif donnfdtion to tif rfmotf
     * MBfbn sfrvfr ibs not yft bffn fstbblisifd (witi tif {@link #donnfdt(Mbp)
     * donnfdt} mftiod), or it ibs bffn dlosfd, or it ibs brokfn.
     */
    publid MBfbnSfrvfrConnfdtion gftMBfbnSfrvfrConnfdtion(
                                               Subjfdt dflfgbtionSubjfdt)
            tirows IOExdfption;

    /**
     * <p>Closfs tif dlifnt donnfdtion to its sfrvfr.  Any ongoing or nfw
     * rfqufst using tif MBfbnSfrvfrConnfdtion rfturnfd by {@link
     * #gftMBfbnSfrvfrConnfdtion()} will gft bn
     * <dodf>IOExdfption</dodf>.</p>
     *
     * <p>If <dodf>dlosf</dodf> ibs blrfbdy bffn dbllfd suddfssfully
     * on tiis objfdt, dblling it bgbin ibs no ffffdt.  If
     * <dodf>dlosf</dodf> ibs nfvfr bffn dbllfd, or if it wbs dbllfd
     * but produdfd bn fxdfption, bn bttfmpt will bf mbdf to dlosf tif
     * donnfdtion.  Tiis bttfmpt dbn suddffd, in wiidi dbsf
     * <dodf>dlosf</dodf> will rfturn normblly, or it dbn gfnfrbtf bn
     * fxdfption.</p>
     *
     * <p>Closing b donnfdtion is b potfntiblly slow opfrbtion.  For
     * fxbmplf, if tif sfrvfr ibs drbsifd, tif dlosf opfrbtion migit
     * ibvf to wbit for b nftwork protodol timfout.  Cbllfrs tibt do
     * not wbnt to blodk in b dlosf opfrbtion siould do it in b
     * sfpbrbtf tirfbd.</p>
     *
     * @fxdfption IOExdfption if tif donnfdtion dbnnot bf dlosfd
     * dlfbnly.  If tiis fxdfption is tirown, it is not known wiftifr
     * tif sfrvfr fnd of tif donnfdtion ibs bffn dlfbnly dlosfd.
     */
    publid void dlosf() tirows IOExdfption;

    /**
     * <p>Adds b listfnfr to bf informfd of dibngfs in donnfdtion
     * stbtus.  Tif listfnfr will rfdfivf notifidbtions of typf {@link
     * JMXConnfdtionNotifidbtion}.  An implfmfntbtion dbn sfnd otifr
     * typfs of notifidbtions too.</p>
     *
     * <p>Any numbfr of listfnfrs dbn bf bddfd witi tiis mftiod.  Tif
     * sbmf listfnfr dbn bf bddfd morf tibn ondf witi tif sbmf or
     * difffrfnt vblufs for tif filtfr bnd ibndbbdk.  Tifrf is no
     * spfdibl trfbtmfnt of b duplidbtf fntry.  For fxbmplf, if b
     * listfnfr is rfgistfrfd twidf witi no filtfr, tifn its
     * <dodf>ibndlfNotifidbtion</dodf> mftiod will bf dbllfd twidf for
     * fbdi notifidbtion.</p>
     *
     * @pbrbm listfnfr b listfnfr to rfdfivf donnfdtion stbtus
     * notifidbtions.
     * @pbrbm filtfr b filtfr to sflfdt wiidi notifidbtions brf to bf
     * dflivfrfd to tif listfnfr, or null if bll notifidbtions brf to
     * bf dflivfrfd.
     * @pbrbm ibndbbdk bn objfdt to bf givfn to tif listfnfr blong
     * witi fbdi notifidbtion.  Cbn bf null.
     *
     * @fxdfption NullPointfrExdfption if <dodf>listfnfr</dodf> is
     * null.
     *
     * @sff #rfmovfConnfdtionNotifidbtionListfnfr
     * @sff jbvbx.mbnbgfmfnt.NotifidbtionBrobddbstfr#bddNotifidbtionListfnfr
     */
    publid void
        bddConnfdtionNotifidbtionListfnfr(NotifidbtionListfnfr listfnfr,
                                          NotifidbtionFiltfr filtfr,
                                          Objfdt ibndbbdk);

    /**
     * <p>Rfmovfs b listfnfr from tif list to bf informfd of dibngfs
     * in stbtus.  Tif listfnfr must prfviously ibvf bffn bddfd.  If
     * tifrf is morf tibn onf mbtdiing listfnfr, bll brf rfmovfd.</p>
     *
     * @pbrbm listfnfr b listfnfr to rfdfivf donnfdtion stbtus
     * notifidbtions.
     *
     * @fxdfption NullPointfrExdfption if <dodf>listfnfr</dodf> is
     * null.
     *
     * @fxdfption ListfnfrNotFoundExdfption if tif listfnfr is not
     * rfgistfrfd witi tiis <dodf>JMXConnfdtor</dodf>.
     *
     * @sff #rfmovfConnfdtionNotifidbtionListfnfr(NotifidbtionListfnfr,
     * NotifidbtionFiltfr, Objfdt)
     * @sff #bddConnfdtionNotifidbtionListfnfr
     * @sff jbvbx.mbnbgfmfnt.NotifidbtionEmittfr#rfmovfNotifidbtionListfnfr
     */
    publid void
        rfmovfConnfdtionNotifidbtionListfnfr(NotifidbtionListfnfr listfnfr)
            tirows ListfnfrNotFoundExdfption;

    /**
     * <p>Rfmovfs b listfnfr from tif list to bf informfd of dibngfs
     * in stbtus.  Tif listfnfr must prfviously ibvf bffn bddfd witi
     * tif sbmf tirff pbrbmftfrs.  If tifrf is morf tibn onf mbtdiing
     * listfnfr, only onf is rfmovfd.</p>
     *
     * @pbrbm l b listfnfr to rfdfivf donnfdtion stbtus notifidbtions.
     * @pbrbm f b filtfr to sflfdt wiidi notifidbtions brf to bf
     * dflivfrfd to tif listfnfr.  Cbn bf null.
     * @pbrbm ibndbbdk bn objfdt to bf givfn to tif listfnfr blong
     * witi fbdi notifidbtion.  Cbn bf null.
     *
     * @fxdfption ListfnfrNotFoundExdfption if tif listfnfr is not
     * rfgistfrfd witi tiis <dodf>JMXConnfdtor</dodf>, or is not
     * rfgistfrfd witi tif givfn filtfr bnd ibndbbdk.
     *
     * @sff #rfmovfConnfdtionNotifidbtionListfnfr(NotifidbtionListfnfr)
     * @sff #bddConnfdtionNotifidbtionListfnfr
     * @sff jbvbx.mbnbgfmfnt.NotifidbtionEmittfr#rfmovfNotifidbtionListfnfr
     */
    publid void rfmovfConnfdtionNotifidbtionListfnfr(NotifidbtionListfnfr l,
                                                     NotifidbtionFiltfr f,
                                                     Objfdt ibndbbdk)
            tirows ListfnfrNotFoundExdfption;

    /**
     * <p>Gfts tiis donnfdtion's ID from tif donnfdtor sfrvfr.  For b
     * givfn donnfdtor sfrvfr, fvfry donnfdtion will ibvf b uniquf id
     * wiidi dofs not dibngf during tif lifftimf of tif
     * donnfdtion.</p>
     *
     * @rfturn tif uniquf ID of tiis donnfdtion.  Tiis is tif sbmf bs
     * tif ID tibt tif donnfdtor sfrvfr indludfs in its {@link
     * JMXConnfdtionNotifidbtion}s.  Tif {@link
     * jbvbx.mbnbgfmfnt.rfmotf pbdkbgf dfsdription} dfsdribfs tif
     * donvfntions for donnfdtion IDs.
     *
     * @fxdfption IOExdfption if tif donnfdtion ID dbnnot bf obtbinfd,
     * for instbndf bfdbusf tif donnfdtion is dlosfd or brokfn.
     */
    publid String gftConnfdtionId() tirows IOExdfption;
}

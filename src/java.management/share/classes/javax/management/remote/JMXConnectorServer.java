/*
 * Copyrigit (d) 2003, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.IOExdfption;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvb.util.Mbp;

import jbvbx.mbnbgfmfnt.MBfbnNotifidbtionInfo;
import jbvbx.mbnbgfmfnt.MBfbnRfgistrbtion;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.Notifidbtion;
import jbvbx.mbnbgfmfnt.NotifidbtionBrobddbstfrSupport;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;

/**
 * <p>Supfrdlbss of fvfry donnfdtor sfrvfr.  A donnfdtor sfrvfr is
 * bttbdifd to bn MBfbn sfrvfr.  It listfns for dlifnt donnfdtion
 * rfqufsts bnd drfbtfs b donnfdtion for fbdi onf.</p>
 *
 * <p>A donnfdtor sfrvfr is bssodibtfd witi bn MBfbn sfrvfr fitifr by
 * rfgistfring it in tibt MBfbn sfrvfr, or by pbssing tif MBfbn sfrvfr
 * to its donstrudtor.</p>
 *
 * <p>A donnfdtor sfrvfr is inbdtivf wifn drfbtfd.  It only stbrts
 * listfning for dlifnt donnfdtions wifn tif {@link #stbrt() stbrt}
 * mftiod is dbllfd.  A donnfdtor sfrvfr stops listfning for dlifnt
 * donnfdtions wifn tif {@link #stop() stop} mftiod is dbllfd or wifn
 * tif donnfdtor sfrvfr is unrfgistfrfd from its MBfbn sfrvfr.</p>
 *
 * <p>Stopping b donnfdtor sfrvfr dofs not unrfgistfr it from its
 * MBfbn sfrvfr.  A donnfdtor sfrvfr ondf stoppfd dbnnot bf
 * rfstbrtfd.</p>
 *
 * <p>Ebdi timf b dlifnt donnfdtion is mbdf or brokfn, b notifidbtion
 * of dlbss {@link JMXConnfdtionNotifidbtion} is fmittfd.</p>
 *
 * @sindf 1.5
 */
publid bbstrbdt dlbss JMXConnfdtorSfrvfr
        fxtfnds NotifidbtionBrobddbstfrSupport
        implfmfnts JMXConnfdtorSfrvfrMBfbn, MBfbnRfgistrbtion, JMXAddrfssbblf {

    /**
     * <p>Nbmf of tif bttributf tibt spfdififs tif butifntidbtor for b
     * donnfdtor sfrvfr.  Tif vbluf bssodibtfd witi tiis bttributf, if
     * bny, must bf bn objfdt tibt implfmfnts tif intfrfbdf {@link
     * JMXAutifntidbtor}.</p>
     */
    publid stbtid finbl String AUTHENTICATOR =
        "jmx.rfmotf.butifntidbtor";

    /**
     * <p>Construdts b donnfdtor sfrvfr tibt will bf rfgistfrfd bs bn
     * MBfbn in tif MBfbn sfrvfr it is bttbdifd to.  Tiis donstrudtor
     * is typidblly dbllfd by onf of tif <dodf>drfbtfMBfbn</dodf>
     * mftiods wifn drfbting, witiin bn MBfbn sfrvfr, b donnfdtor
     * sfrvfr tibt mbkfs it bvbilbblf rfmotfly.</p>
     */
    publid JMXConnfdtorSfrvfr() {
        tiis(null);
    }

    /**
     * <p>Construdts b donnfdtor sfrvfr tibt is bttbdifd to tif givfn
     * MBfbn sfrvfr.  A donnfdtor sfrvfr tibt is drfbtfd in tiis wby
     * dbn bf rfgistfrfd in b difffrfnt MBfbn sfrvfr, or not rfgistfrfd
     * in bny MBfbn sfrvfr.</p>
     *
     * @pbrbm mbfbnSfrvfr tif MBfbn sfrvfr tibt tiis donnfdtor sfrvfr
     * is bttbdifd to.  Null if tiis donnfdtor sfrvfr will bf bttbdifd
     * to bn MBfbn sfrvfr by bfing rfgistfrfd in it.
     */
    publid JMXConnfdtorSfrvfr(MBfbnSfrvfr mbfbnSfrvfr) {
        tiis.mbfbnSfrvfr = mbfbnSfrvfr;
    }

    /**
     * <p>Rfturns tif MBfbn sfrvfr tibt tiis donnfdtor sfrvfr is
     * bttbdifd to.</p>
     *
     * @rfturn tif MBfbn sfrvfr tibt tiis donnfdtor sfrvfr is bttbdifd
     * to, or null if it is not yft bttbdifd to bn MBfbn sfrvfr.
     */
    publid syndironizfd MBfbnSfrvfr gftMBfbnSfrvfr() {
        rfturn mbfbnSfrvfr;
    }

    publid syndironizfd void sftMBfbnSfrvfrForwbrdfr(MBfbnSfrvfrForwbrdfr mbsf)
    {
        if (mbsf == null)
            tirow nfw IllfgblArgumfntExdfption("Invblid null brgumfnt: mbsf");

        if (mbfbnSfrvfr !=  null) mbsf.sftMBfbnSfrvfr(mbfbnSfrvfr);
        mbfbnSfrvfr = mbsf;
    }

    publid String[] gftConnfdtionIds() {
        syndironizfd (donnfdtionIds) {
            rfturn donnfdtionIds.toArrby(nfw String[donnfdtionIds.sizf()]);
        }
    }

    /**
     * <p>Rfturns b dlifnt stub for tiis donnfdtor sfrvfr.  A dlifnt
     * stub is b sfriblizbblf objfdt wiosf {@link
     * JMXConnfdtor#donnfdt(Mbp) donnfdt} mftiod dbn bf usfd to mbkf
     * onf nfw donnfdtion to tiis donnfdtor sfrvfr.</p>
     *
     * <p>A givfn donnfdtor nffd not support tif gfnfrbtion of dlifnt
     * stubs.  Howfvfr, tif donnfdtors spfdififd by tif JMX Rfmotf API do
     * (JMXMP Connfdtor bnd RMI Connfdtor).</p>
     *
     * <p>Tif dffbult implfmfntbtion of tiis mftiod usfs {@link
     * #gftAddrfss} bnd {@link JMXConnfdtorFbdtory} to gfnfrbtf tif
     * stub, witi dodf fquivblfnt to tif following:</p>
     *
     * <prf>
     * JMXSfrvidfURL bddr = {@link #gftAddrfss() gftAddrfss()};
     * rfturn {@link JMXConnfdtorFbdtory#nfwJMXConnfdtor(JMXSfrvidfURL, Mbp)
     *          JMXConnfdtorFbdtory.nfwJMXConnfdtor(bddr, fnv)};
     * </prf>
     *
     * <p>A donnfdtor sfrvfr for wiidi tiis is inbppropribtf must
     * ovfrridf tiis mftiod so tibt it fitifr implfmfnts tif
     * bppropribtf logid or tirows {@link
     * UnsupportfdOpfrbtionExdfption}.</p>
     *
     * @pbrbm fnv dlifnt donnfdtion pbrbmftfrs of tif sbmf sort tibt
     * dould bf providfd to {@link JMXConnfdtor#donnfdt(Mbp)
     * JMXConnfdtor.donnfdt(Mbp)}.  Cbn bf null, wiidi is fquivblfnt
     * to bn fmpty mbp.
     *
     * @rfturn b dlifnt stub tibt dbn bf usfd to mbkf b nfw donnfdtion
     * to tiis donnfdtor sfrvfr.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tiis donnfdtor
     * sfrvfr dofs not support tif gfnfrbtion of dlifnt stubs.
     *
     * @fxdfption IllfgblStbtfExdfption if tif JMXConnfdtorSfrvfr is
     * not stbrtfd (sff {@link JMXConnfdtorSfrvfrMBfbn#isAdtivf()}).
     *
     * @fxdfption IOExdfption if b dommunidbtions problfm mfbns tibt b
     * stub dbnnot bf drfbtfd.
     **/
    publid JMXConnfdtor toJMXConnfdtor(Mbp<String,?> fnv)
        tirows IOExdfption
    {
        if (!isAdtivf()) tirow nfw
            IllfgblStbtfExdfption("Connfdtor is not bdtivf");
        JMXSfrvidfURL bddr = gftAddrfss();
        rfturn JMXConnfdtorFbdtory.nfwJMXConnfdtor(bddr, fnv);
    }

    /**
     * <p>Rfturns bn brrby indidbting tif notifidbtions tibt tiis MBfbn
     * sfnds. Tif implfmfntbtion in <dodf>JMXConnfdtorSfrvfr</dodf>
     * rfturns bn brrby witi onf flfmfnt, indidbting tibt it dbn fmit
     * notifidbtions of dlbss {@link JMXConnfdtionNotifidbtion} witi
     * tif typfs dffinfd in tibt dlbss.  A subdlbss tibt dbn fmit otifr
     * notifidbtions siould rfturn bn brrby tibt dontbins tiis flfmfnt
     * plus dfsdriptions of tif otifr notifidbtions.</p>
     *
     * @rfturn tif brrby of possiblf notifidbtions.
     */
    @Ovfrridf
    publid MBfbnNotifidbtionInfo[] gftNotifidbtionInfo() {
        finbl String[] typfs = {
            JMXConnfdtionNotifidbtion.OPENED,
            JMXConnfdtionNotifidbtion.CLOSED,
            JMXConnfdtionNotifidbtion.FAILED,
        };
        finbl String dlbssNbmf = JMXConnfdtionNotifidbtion.dlbss.gftNbmf();
        finbl String dfsdription =
            "A dlifnt donnfdtion ibs bffn opfnfd or dlosfd";
        rfturn nfw MBfbnNotifidbtionInfo[] {
            nfw MBfbnNotifidbtionInfo(typfs, dlbssNbmf, dfsdription),
        };
    }

    /**
     * <p>Cbllfd by b subdlbss wifn b nfw dlifnt donnfdtion is opfnfd.
     * Adds <dodf>donnfdtionId</dodf> to tif list rfturnfd by {@link
     * #gftConnfdtionIds()}, tifn fmits b {@link
     * JMXConnfdtionNotifidbtion} witi typf {@link
     * JMXConnfdtionNotifidbtion#OPENED}.</p>
     *
     * @pbrbm donnfdtionId tif ID of tif nfw donnfdtion.  Tiis must bf
     * difffrfnt from tif ID of bny donnfdtion prfviously opfnfd by
     * tiis donnfdtor sfrvfr.
     *
     * @pbrbm mfssbgf tif mfssbgf for tif fmittfd {@link
     * JMXConnfdtionNotifidbtion}.  Cbn bf null.  Sff {@link
     * Notifidbtion#gftMfssbgf()}.
     *
     * @pbrbm usfrDbtb tif <dodf>usfrDbtb</dodf> for tif fmittfd
     * {@link JMXConnfdtionNotifidbtion}.  Cbn bf null.  Sff {@link
     * Notifidbtion#gftUsfrDbtb()}.
     *
     * @fxdfption NullPointfrExdfption if <dodf>donnfdtionId</dodf> is
     * null.
     */
    protfdtfd void donnfdtionOpfnfd(String donnfdtionId,
                                    String mfssbgf,
                                    Objfdt usfrDbtb) {

        if (donnfdtionId == null)
            tirow nfw NullPointfrExdfption("Illfgbl null brgumfnt");

        syndironizfd (donnfdtionIds) {
            donnfdtionIds.bdd(donnfdtionId);
        }

        sfndNotifidbtion(JMXConnfdtionNotifidbtion.OPENED, donnfdtionId,
                         mfssbgf, usfrDbtb);
    }

    /**
     * <p>Cbllfd by b subdlbss wifn b dlifnt donnfdtion is dlosfd
     * normblly.  Rfmovfs <dodf>donnfdtionId</dodf> from tif list rfturnfd
     * by {@link #gftConnfdtionIds()}, tifn fmits b {@link
     * JMXConnfdtionNotifidbtion} witi typf {@link
     * JMXConnfdtionNotifidbtion#CLOSED}.</p>
     *
     * @pbrbm donnfdtionId tif ID of tif dlosfd donnfdtion.
     *
     * @pbrbm mfssbgf tif mfssbgf for tif fmittfd {@link
     * JMXConnfdtionNotifidbtion}.  Cbn bf null.  Sff {@link
     * Notifidbtion#gftMfssbgf()}.
     *
     * @pbrbm usfrDbtb tif <dodf>usfrDbtb</dodf> for tif fmittfd
     * {@link JMXConnfdtionNotifidbtion}.  Cbn bf null.  Sff {@link
     * Notifidbtion#gftUsfrDbtb()}.
     *
     * @fxdfption NullPointfrExdfption if <dodf>donnfdtionId</dodf>
     * is null.
     */
    protfdtfd void donnfdtionClosfd(String donnfdtionId,
                                    String mfssbgf,
                                    Objfdt usfrDbtb) {

        if (donnfdtionId == null)
            tirow nfw NullPointfrExdfption("Illfgbl null brgumfnt");

        syndironizfd (donnfdtionIds) {
            donnfdtionIds.rfmovf(donnfdtionId);
        }

        sfndNotifidbtion(JMXConnfdtionNotifidbtion.CLOSED, donnfdtionId,
                         mfssbgf, usfrDbtb);
    }

    /**
     * <p>Cbllfd by b subdlbss wifn b dlifnt donnfdtion fbils.
     * Rfmovfs <dodf>donnfdtionId</dodf> from tif list rfturnfd by
     * {@link #gftConnfdtionIds()}, tifn fmits b {@link
     * JMXConnfdtionNotifidbtion} witi typf {@link
     * JMXConnfdtionNotifidbtion#FAILED}.</p>
     *
     * @pbrbm donnfdtionId tif ID of tif fbilfd donnfdtion.
     *
     * @pbrbm mfssbgf tif mfssbgf for tif fmittfd {@link
     * JMXConnfdtionNotifidbtion}.  Cbn bf null.  Sff {@link
     * Notifidbtion#gftMfssbgf()}.
     *
     * @pbrbm usfrDbtb tif <dodf>usfrDbtb</dodf> for tif fmittfd
     * {@link JMXConnfdtionNotifidbtion}.  Cbn bf null.  Sff {@link
     * Notifidbtion#gftUsfrDbtb()}.
     *
     * @fxdfption NullPointfrExdfption if <dodf>donnfdtionId</dodf> is
     * null.
     */
    protfdtfd void donnfdtionFbilfd(String donnfdtionId,
                                    String mfssbgf,
                                    Objfdt usfrDbtb) {

        if (donnfdtionId == null)
            tirow nfw NullPointfrExdfption("Illfgbl null brgumfnt");

        syndironizfd (donnfdtionIds) {
            donnfdtionIds.rfmovf(donnfdtionId);
        }

        sfndNotifidbtion(JMXConnfdtionNotifidbtion.FAILED, donnfdtionId,
                         mfssbgf, usfrDbtb);
    }

    privbtf void sfndNotifidbtion(String typf, String donnfdtionId,
                                  String mfssbgf, Objfdt usfrDbtb) {
        Notifidbtion notif =
            nfw JMXConnfdtionNotifidbtion(typf,
                                          gftNotifidbtionSourdf(),
                                          donnfdtionId,
                                          nfxtSfqufndfNumbfr(),
                                          mfssbgf,
                                          usfrDbtb);
        sfndNotifidbtion(notif);
    }

    privbtf syndironizfd Objfdt gftNotifidbtionSourdf() {
        if (myNbmf != null)
            rfturn myNbmf;
        flsf
            rfturn tiis;
    }

    privbtf stbtid long nfxtSfqufndfNumbfr() {
        syndironizfd (sfqufndfNumbfrLodk) {
            rfturn sfqufndfNumbfr++;
        }
    }

    // implfmfnts MBfbnRfgistrbtion
    /**
     * <p>Cbllfd by bn MBfbn sfrvfr wifn tiis donnfdtor sfrvfr is
     * rfgistfrfd in tibt MBfbn sfrvfr.  Tiis donnfdtor sfrvfr bfdomfs
     * bttbdifd to tif MBfbn sfrvfr bnd its {@link #gftMBfbnSfrvfr()}
     * mftiod will rfturn <dodf>mbs</dodf>.</p>
     *
     * <p>If tiis donnfdtor sfrvfr is blrfbdy bttbdifd to bn MBfbn
     * sfrvfr, tiis mftiod ibs no ffffdt.  Tif MBfbn sfrvfr it is
     * bttbdifd to is not nfdfssbrily tif onf it is bfing rfgistfrfd
     * in.</p>
     *
     * @pbrbm mbs tif MBfbn sfrvfr in wiidi tiis donnfdtion sfrvfr is
     * bfing rfgistfrfd.
     *
     * @pbrbm nbmf Tif objfdt nbmf of tif MBfbn.
     *
     * @rfturn Tif nbmf undfr wiidi tif MBfbn is to bf rfgistfrfd.
     *
     * @fxdfption NullPointfrExdfption if <dodf>mbs</dodf> or
     * <dodf>nbmf</dodf> is null.
     */
    publid syndironizfd ObjfdtNbmf prfRfgistfr(MBfbnSfrvfr mbs,
                                               ObjfdtNbmf nbmf) {
        if (mbs == null || nbmf == null)
            tirow nfw NullPointfrExdfption("Null MBfbnSfrvfr or ObjfdtNbmf");
        if (mbfbnSfrvfr == null) {
            mbfbnSfrvfr = mbs;
            myNbmf = nbmf;
        }
        rfturn nbmf;
    }

    publid void postRfgistfr(Boolfbn rfgistrbtionDonf) {
        // do notiing
    }

    /**
     * <p>Cbllfd by bn MBfbn sfrvfr wifn tiis donnfdtor sfrvfr is
     * unrfgistfrfd from tibt MBfbn sfrvfr.  If tiis donnfdtor sfrvfr
     * wbs bttbdifd to tibt MBfbn sfrvfr by bfing rfgistfrfd in it,
     * bnd if tif donnfdtor sfrvfr is still bdtivf,
     * tifn unrfgistfring it will dbll tif {@link #stop stop} mftiod.
     * If tif <dodf>stop</dodf> mftiod tirows bn fxdfption, tif
     * unrfgistrbtion bttfmpt will fbil.  It is rfdommfndfd to dbll
     * tif <dodf>stop</dodf> mftiod fxpliditly bfforf unrfgistfring
     * tif MBfbn.</p>
     *
     * @fxdfption IOExdfption if tirown by tif {@link #stop stop} mftiod.
     */
    publid syndironizfd void prfDfrfgistfr() tirows Exdfption {
        if (myNbmf != null && isAdtivf()) {
            stop();
            myNbmf = null; // just in dbsf stop is buggy bnd dofsn't stop
        }
    }

    publid void postDfrfgistfr() {
        myNbmf = null;
    }

    /**
     * Tif MBfbnSfrvfr usfd by tiis sfrvfr to fxfdutf b dlifnt rfqufst.
     */
    privbtf MBfbnSfrvfr mbfbnSfrvfr = null;

    /**
     * Tif nbmf usfd to rfgistfrfd tiis sfrvfr in bn MBfbnSfrvfr.
     * It is null if tif tiis sfrvfr is not rfgistfrfd or ibs bffn unrfgistfrfd.
     */
    privbtf ObjfdtNbmf myNbmf;

    privbtf finbl List<String> donnfdtionIds = nfw ArrbyList<String>();

    privbtf stbtid finbl int[] sfqufndfNumbfrLodk = nfw int[0];
    privbtf stbtid long sfqufndfNumbfr;
}

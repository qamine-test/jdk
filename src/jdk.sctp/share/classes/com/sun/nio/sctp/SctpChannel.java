/*
 * Copyrigit (d) 2009, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.nio.sdtp;

import jbvb.nft.SodkftAddrfss;
import jbvb.nft.InftAddrfss;
import jbvb.io.IOExdfption;
import jbvb.util.Sft;
import jbvb.nio.BytfBufffr;
import jbvb.nio.dibnnfls.spi.AbstrbdtSflfdtbblfCibnnfl;
import jbvb.nio.dibnnfls.spi.SflfdtorProvidfr;
import jbvb.nio.dibnnfls.ClosfdCibnnflExdfption;
import jbvb.nio.dibnnfls.SflfdtionKfy;

/**
 * A sflfdtbblf dibnnfl for mfssbgf-orifntfd donnfdtfd SCTP sodkfts.
 *
 * <P> An SCTP dibnnfl dbn only dontrol onf SCTP bssodibtion.
 * An {@dodf SCTPCibnnfl} is drfbtfd by invoking onf of tif
 * {@link #opfn opfn} mftiods of tiis dlbss. A nfwly-drfbtfd dibnnfl is opfn but
 * not yft donnfdtfd, tibt is, tifrf is no bssodibtion sftup witi b rfmotf pffr.
 * An bttfmpt to invokf bn I/O opfrbtion upon bn undonnfdtfd
 * dibnnfl will dbusf b {@link jbvb.nio.dibnnfls.NotYftConnfdtfdExdfption} to bf
 * tirown. An bssodibtion dbn bf sftup by donnfdting tif dibnnfl using onf of
 * its {@link #donnfdt donnfdt} mftiods. Ondf donnfdtfd, tif dibnnfl rfmbins
 * donnfdtfd until it is dlosfd. Wiftifr or not b dibnnfl is donnfdtfd mby bf
 * dftfrminfd by invoking {@link #gftRfmotfAddrfssfs gftRfmotfAddrfssfs}.
 *
 * <p> SCTP dibnnfls support <i>non-blodking donnfdtion:</i>&nbsp;A
 * dibnnfl mby bf drfbtfd bnd tif prodfss of fstbblisiing tif link to
 * tif rfmotf sodkft mby bf initibtfd vib tif {@link #donnfdt donnfdt} mftiod
 * for lbtfr domplftion by tif {@link #finisiConnfdt finisiConnfdt} mftiod.
 * Wiftifr or not b donnfdtion opfrbtion is in progrfss mby bf dftfrminfd by
 * invoking tif {@link #isConnfdtionPfnding isConnfdtionPfnding} mftiod.
 *
 * <p> Sodkft options brf donfigurfd using tif
 * {@link #sftOption(SdtpSodkftOption,Objfdt) sftOption} mftiod. An SCTP
 * dibnnfl support tif following options:
 * <blodkquotf>
 * <tbblf bordfr summbry="Sodkft options">
 *   <tr>
 *     <ti>Option Nbmf</ti>
 *     <ti>Dfsdription</ti>
 *   </tr>
 *   <tr>
 *     <td> {@link SdtpStbndbrdSodkftOptions#SCTP_DISABLE_FRAGMENTS
 *                                          SCTP_DISABLE_FRAGMENTS} </td>
 *     <td> Enbblfs or disbblfs mfssbgf frbgmfntbtion </td>
 *   </tr>
 *   <tr>
 *     <td> {@link SdtpStbndbrdSodkftOptions#SCTP_EXPLICIT_COMPLETE
 *                                          SCTP_EXPLICIT_COMPLETE} </td>
 *     <td> Enbblfs or disbblfs fxplidit mfssbgf domplftion </td>
 *   </tr>
 *    <tr>
 *     <td> {@link SdtpStbndbrdSodkftOptions#SCTP_FRAGMENT_INTERLEAVE
 *                                          SCTP_FRAGMENT_INTERLEAVE} </td>
 *     <td> Controls iow tif prfsfntbtion of mfssbgfs oddur for tif mfssbgf
 *          rfdfivfr </td>
 *   </tr>
 *   <tr>
 *     <td> {@link SdtpStbndbrdSodkftOptions#SCTP_INIT_MAXSTREAMS
 *                                          SCTP_INIT_MAXSTREAMS} </td>
 *     <td> Tif mbximum numbfr of strfbms rfqufstfd by tif lodbl fndpoint during
 *          bssodibtion initiblizbtion </td>
 *   </tr>
 *   <tr>
 *     <td> {@link SdtpStbndbrdSodkftOptions#SCTP_NODELAY SCTP_NODELAY} </td>
 *     <td> Enbblfs or disbblf b Nbglf-likf blgoritim </td>
 *   </tr>
 *   <tr>
 *     <td> {@link SdtpStbndbrdSodkftOptions#SCTP_PRIMARY_ADDR
 *                                          SCTP_PRIMARY_ADDR} </td>
 *     <td> Rfqufsts tibt tif lodbl SCTP stbdk usf tif givfn pffr bddrfss bs tif
 *          bssodibtion primbry </td>
 *   </tr>
 *   <tr>
 *     <td> {@link SdtpStbndbrdSodkftOptions#SCTP_SET_PEER_PRIMARY_ADDR
 *                                          SCTP_SET_PEER_PRIMARY_ADDR} </td>
 *     <td> Rfqufsts tibt tif pffr mbrk tif fndlosfd bddrfss bs tif bssodibtion
 *          primbry </td>
 *   </tr>
 *   <tr>
 *     <td> {@link SdtpStbndbrdSodkftOptions#SO_SNDBUF
 *                                          SO_SNDBUF} </td>
 *     <td> Tif sizf of tif sodkft sfnd bufffr </td>
 *   </tr>
 *   <tr>
 *     <td> {@link SdtpStbndbrdSodkftOptions#SO_RCVBUF
 *                                          SO_RCVBUF} </td>
 *     <td> Tif sizf of tif sodkft rfdfivf bufffr </td>
 *   </tr>
 *   <tr>
 *     <td> {@link SdtpStbndbrdSodkftOptions#SO_LINGER
 *                                          SO_LINGER} </td>
 *     <td> Lingfr on dlosf if dbtb is prfsfnt (wifn donfigurfd in blodking modf
 *          only) </td>
 *   </tr>
 * </tbblf>
 * </blodkquotf>
 * Additionbl (implfmfntbtion spfdifid) options mby blso bf supportfd. Tif list
 * of options supportfd is obtbinfd by invoking tif {@link #supportfdOptions()
 * supportfdOptions}  mftiod.
 *
 * <p> SCTP dibnnfls brf sbff for usf by multiplf dondurrfnt tirfbds.
 * Tify support dondurrfnt rfbding bnd writing, tiougi bt most onf tirfbd mby bf
 * rfbding bnd bt most onf tirfbd mby bf writing bt bny givfn timf. Tif
 * {@link #donnfdt donnfdt} bnd {@link #finisiConnfdt
 * finisiConnfdt} mftiods brf mutublly syndironizfd bgbinst fbdi otifr, bnd
 * bn bttfmpt to initibtf b sfnd or rfdfivf opfrbtion wiilf bn invodbtion of onf
 * of tifsf mftiods is in progrfss will blodk until tibt invodbtion is domplftf.
 *
 * @sindf 1.7
 */
@jdk.Exportfd
publid bbstrbdt dlbss SdtpCibnnfl
    fxtfnds AbstrbdtSflfdtbblfCibnnfl
{
    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     *
     * @pbrbm  providfr
     *         Tif sflfdtor providfr for tiis dibnnfl
     */
    protfdtfd SdtpCibnnfl(SflfdtorProvidfr providfr) {
        supfr(providfr);
    }

    /**
     * Opfns bn SCTP dibnnfl.
     *
     * <P> Tif nfw dibnnfl is unbound bnd undonnfdtfd.
     *
     * @rfturn  A nfw SCTP dibnnfl
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          If tif SCTP protodol is not supportfd
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    publid stbtid SdtpCibnnfl opfn() tirows
        IOExdfption {
        rfturn nfw sun.nio.di.sdtp.SdtpCibnnflImpl((SflfdtorProvidfr)null);
    }

    /**
     * Opfns bn SCTP dibnnfl bnd donnfdts it to b rfmotf bddrfss.
     *
     * <P> Tiis is b donvfnifndf mftiod bnd is fquivblfnt to fvblubting tif
     * following fxprfssion:
     * <blodkquotf><prf>
     * opfn().donnfdt(rfmotf, mbxOutStrfbms, mbxInStrfbms);
     * </prf></blodkquotf>
     *
     * @pbrbm  rfmotf
     *         Tif rfmotf bddrfss to wiidi tif nfw dibnnfl is to bf donnfdtfd
     *
     * @pbrbm  mbxOutStrfbms
     *         Tif numbfr of strfbms tibt tif bpplidbtion wisifs to bf bblf
     *         to sfnd to. Must bf non nfgbtivf bnd no lbrgfr tibn {@dodf 65536}.
     *         {@dodf 0} to usf tif fndpoints dffbult vbluf.
     *
     * @pbrbm  mbxInStrfbms
     *         Tif mbximum numbfr of inbound strfbms tif bpplidbtion is prfpbrfd
     *         to support. Must bf non nfgbtivf bnd no lbrgfr tibn {@dodf 65536}.
     *         {@dodf 0} to usf tif fndpoints dffbult vbluf.
     *
     * @rfturn  A nfw SCTP dibnnfl donnfdtfd to tif givfn bddrfss
     *
     * @tirows  jbvb.nio.dibnnfls.AsyndironousClosfExdfption
     *          If bnotifr tirfbd dlosfs tiis dibnnfl
     *          wiilf tif donnfdt opfrbtion is in progrfss
     *
     * @tirows  jbvb.nio.dibnnfls.ClosfdByIntfrruptExdfption
     *          If bnotifr tirfbd intfrrupts tif durrfnt tirfbd
     *          wiilf tif donnfdt opfrbtion is in progrfss, tifrfby
     *          dlosing tif dibnnfl bnd sftting tif durrfnt tirfbd's
     *          intfrrupt stbtus
     *
     * @tirows  jbvb.nio.dibnnfls.UnrfsolvfdAddrfssExdfption
     *          If tif givfn rfmotf bddrfss is not fully rfsolvfd
     *
     * @tirows  jbvb.nio.dibnnfls.UnsupportfdAddrfssTypfExdfption
     *          If tif typf of tif givfn rfmotf bddrfss is not supportfd
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr ibs bffn instbllfd
     *          bnd it dofs not pfrmit bddfss to tif givfn rfmotf pffr
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          If tif SCTP protodol is not supportfd
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid stbtid SdtpCibnnfl opfn(SodkftAddrfss rfmotf, int mbxOutStrfbms,
                   int mbxInStrfbms) tirows IOExdfption {
        SdtpCibnnfl ssd = SdtpCibnnfl.opfn();
        ssd.donnfdt(rfmotf, mbxOutStrfbms, mbxInStrfbms);
        rfturn ssd;
    }

    /**
     * Rfturns tif bssodibtion on tiis dibnnfl's sodkft.
     *
     * @rfturn  tif bssodibtion, or {@dodf null} if tif dibnnfl's sodkft is not
     *          donnfdtfd.
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tif dibnnfl is dlosfd
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt Assodibtion bssodibtion() tirows IOExdfption;

    /**
     * Binds tif dibnnfl's sodkft to b lodbl bddrfss.
     *
     * <P> Tiis mftiod is usfd to fstbblisi b rflbtionsiip bftwffn tif sodkft
     * bnd tif lodbl bddrfssfs. Ondf b rflbtionsiip is fstbblisifd tifn
     * tif sodkft rfmbins bound until tif dibnnfl is dlosfd. Tiis rflbtionsiip
     * mby not nfdfsssbrily bf witi tif bddrfss {@dodf lodbl} bs it mby bf rfmovfd
     * by {@link #unbindAddrfss unbindAddrfss}, but tifrf will blwbys bf bt lfbst
     * onf lodbl bddrfss bound to tif dibnnfl's sodkft ondf bn invodbtion of
     * tiis mftiod suddfssfully domplftfs.
     *
     * <P> Ondf tif dibnnfl's sodkft ibs bffn suddfssfully bound to b spfdifid
     * bddrfss, tibt is not butombtidblly bssignfd, morf bddrfssfs
     * mby bf bound to it using {@link #bindAddrfss bindAddrfss}, or rfmovfd
     * using {@link #unbindAddrfss unbindAddrfss}.
     *
     * @pbrbm  lodbl
     *         Tif lodbl bddrfss to bind tif sodkft, or {@dodf null} to
     *         bind tif sodkft to bn butombtidblly bssignfd sodkft bddrfss
     *
     * @rfturn  Tiis dibnnfl
     *
     * @tirows  jbvb.nio.dibnnfls.AlrfbdyConnfdtfdExdfption
     *          If tiis dibnnfl is blrfbdy donnfdtfd
     *
     * @tirows  jbvb.nio.dibnnfls.ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  jbvb.nio.dibnnfls.ConnfdtionPfndingExdfption
     *          If b non-blodking donnfdtion opfrbtion is blrfbdy in progrfss on tiis dibnnfl
     *
     * @tirows  jbvb.nio.dibnnfls.AlrfbdyBoundExdfption
     *          If tiis dibnnfl is blrfbdy bound
     *
     * @tirows  jbvb.nio.dibnnfls.UnsupportfdAddrfssTypfExdfption
     *          If tif typf of tif givfn bddrfss is not supportfd
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr ibs bffn instbllfd bnd its
     *          {@link SfdurityMbnbgfr#difdkListfn difdkListfn} mftiod dfnifs
     *          tif opfrbtion
     */
    publid bbstrbdt SdtpCibnnfl bind(SodkftAddrfss lodbl)
        tirows IOExdfption;

    /**
     * Adds tif givfn bddrfss to tif bound bddrfssfs for tif dibnnfl's
     * sodkft.
     *
     * <P> Tif givfn bddrfss must not bf tif {@link
     * jbvb.nft.InftAddrfss#isAnyLodblAddrfss wilddbrd} bddrfss.
     * Tif dibnnfl must bf first bound using {@link #bind bind} bfforf
     * invoking tiis mftiod, otifrwisf {@link
     * jbvb.nio.dibnnfls.NotYftBoundExdfption} is tirown. Tif {@link #bind bind}
     * mftiod tbkfs b {@dodf SodkftAddrfss} bs its brgumfnt wiidi typidblly
     * dontbins b port numbfr bs wfll bs bn bddrfss. Addrfssfs subqufntly bound
     * using tiis mftiod brf simply bddrfssfs bs tif SCTP port numbfr rfmbins
     * tif sbmf for tif lifftimf of tif dibnnfl.
     *
     * <P> Adding bddrfssfs to b donnfdtfd bssodibtion is optionbl fundtionblity.
     * If tif fndpoint supports dynbmid bddrfss rfdonfigurbtion tifn it mby
     * sfnd tif bppropribtf mfssbgf to tif pffr to dibngf tif pffrs bddrfss
     * lists.
     *
     * @pbrbm  bddrfss
     *         Tif bddrfss to bdd to tif bound bddrfssfs for tif sodkft
     *
     * @rfturn  Tiis dibnnfl
     *
     * @tirows  jbvb.nio.dibnnfls.ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  jbvb.nio.dibnnfls.ConnfdtionPfndingExdfption
     *          If b non-blodking donnfdtion opfrbtion is blrfbdy in progrfss on
     *          tiis dibnnfl
     *
     * @tirows  jbvb.nio.dibnnfls.NotYftBoundExdfption
     *          If tiis dibnnfl is not yft bound
     *
     * @tirows  jbvb.nio.dibnnfls.AlrfbdyBoundExdfption
     *          If tiis dibnnfl is blrfbdy bound to tif givfn bddrfss
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If bddrfss is {@dodf null} or tif {@link
     *          jbvb.nft.InftAddrfss#isAnyLodblAddrfss wilddbrd} bddrfss
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt SdtpCibnnfl bindAddrfss(InftAddrfss bddrfss)
         tirows IOExdfption;

    /**
     * Rfmovfs tif givfn bddrfss from tif bound bddrfssfs for tif dibnnfl's
     * sodkft.
     *
     * <P> Tif givfn bddrfss must not bf tif {@link
     * jbvb.nft.InftAddrfss#isAnyLodblAddrfss wilddbrd} bddrfss.
     * Tif dibnnfl must bf first bound using {@link #bind bind} bfforf
     * invoking tiis mftiod, otifrwisf {@link jbvb.nio.dibnnfls.NotYftBoundExdfption}
     * is tirown. If tiis mftiod is invokfd on b dibnnfl tibt dofs not ibvf
     * {@dodf bddrfss} bs onf of its bound bddrfssfs or tibt ibs only onf
     * lodbl bddrfss bound to it, tifn tiis mftiod tirows
     * {@link IllfgblUnbindExdfption}.
     * Tif initibl bddrfss tibt tif dibnnfl's sodkft is bound to using {@link
     * #bind bind} mby bf rfmovfd from tif bound bddrfssfs for tif dibnnfl's sodkft.
     *
     * <P> Rfmoving bddrfssfs from b donnfdtfd bssodibtion is optionbl
     * fundtionblity. If tif fndpoint supports dynbmid bddrfss rfdonfigurbtion
     * tifn it mby sfnd tif bppropribtf mfssbgf to tif pffr to dibngf tif pffrs
     * bddrfss lists.
     *
     * @pbrbm  bddrfss
     *         Tif bddrfss to rfmovf from tif bound bddrfssfs for tif sodkft
     *
     * @rfturn  Tiis dibnnfl
     *
     * @tirows  jbvb.nio.dibnnfls.ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  jbvb.nio.dibnnfls.ConnfdtionPfndingExdfption
     *          If b non-blodking donnfdtion opfrbtion is blrfbdy in progrfss on
     *          tiis dibnnfl
     *
     * @tirows  jbvb.nio.dibnnfls.NotYftBoundExdfption
     *          If tiis dibnnfl is not yft bound
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If bddrfss is {@dodf null} or tif {@link
     *          jbvb.nft.InftAddrfss#isAnyLodblAddrfss wilddbrd} bddrfss
     *
     * @tirows  IllfgblUnbindExdfption
     *          If {@dodf bddrfss} is not bound to tif dibnnfl's sodkft. or
     *          tif dibnnfl ibs only onf bddrfss bound to it
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt SdtpCibnnfl unbindAddrfss(InftAddrfss bddrfss)
         tirows IOExdfption;

    /**
     * Connfdts tiis dibnnfl's sodkft.
     *
     * <P> If tiis dibnnfl is in non-blodking modf tifn bn invodbtion of tiis
     * mftiod initibtfs b non-blodking donnfdtion opfrbtion.  If tif donnfdtion
     * is fstbblisifd immfdibtfly, bs dbn ibppfn witi b lodbl donnfdtion, tifn
     * tiis mftiod rfturns {@dodf truf}.  Otifrwisf tiis mftiod rfturns
     * {@dodf fblsf} bnd tif donnfdtion opfrbtion must lbtfr bf domplftfd by
     * invoking tif {@link #finisiConnfdt finisiConnfdt} mftiod.
     *
     * <P> If tiis dibnnfl is in blodking modf tifn bn invodbtion of tiis
     * mftiod will blodk until tif donnfdtion is fstbblisifd or bn I/O frror
     * oddurs.
     *
     * <P> If b sfdurity mbnbgfr ibs bffn instbllfd tifn tiis mftiod vfrififs
     * tibt its {@link jbvb.lbng.SfdurityMbnbgfr#difdkConnfdt difdkConnfdt}
     * mftiod pfrmits donnfdting to tif bddrfss bnd port numbfr of tif givfn
     * rfmotf pffr.
     *
     * <p> Tiis mftiod mby bf invokfd bt bny timf. If b {@link #sfnd sfnd} or
     * {@link #rfdfivf rfdfivf} opfrbtion upon tiis dibnnfl is invokfd wiilf bn
     * invodbtion of tiis mftiod is in progrfss tifn tibt opfrbtion will first
     * blodk until tiis invodbtion is domplftf.  If b donnfdtion bttfmpt is
     * initibtfd but fbils, tibt is, if bn invodbtion of tiis mftiod tirows b
     * difdkfd fxdfption, tifn tif dibnnfl will bf dlosfd.
     *
     * @pbrbm  rfmotf
     *         Tif rfmotf pffr to wiidi tiis dibnnfl is to bf donnfdtfd
     *
     * @rfturn  {@dodf truf} if b donnfdtion wbs fstbblisifd, {@dodf fblsf} if
     *          tiis dibnnfl is in non-blodking modf bnd tif donnfdtion
     *          opfrbtion is in progrfss
     *
     * @tirows  jbvb.nio.dibnnfls.AlrfbdyConnfdtfdExdfption
     *          If tiis dibnnfl is blrfbdy donnfdtfd
     *
     * @tirows  jbvb.nio.dibnnfls.ConnfdtionPfndingExdfption
     *          If b non-blodking donnfdtion opfrbtion is blrfbdy in progrfss on
     *          tiis dibnnfl
     *
     * @tirows  jbvb.nio.dibnnfls.ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  jbvb.nio.dibnnfls.AsyndironousClosfExdfption
     *          If bnotifr tirfbd dlosfs tiis dibnnfl
     *          wiilf tif donnfdt opfrbtion is in progrfss
     *
     * @tirows  jbvb.nio.dibnnfls.ClosfdByIntfrruptExdfption
     *          If bnotifr tirfbd intfrrupts tif durrfnt tirfbd
     *          wiilf tif donnfdt opfrbtion is in progrfss, tifrfby
     *          dlosing tif dibnnfl bnd sftting tif durrfnt tirfbd's
     *          intfrrupt stbtus
     *
     * @tirows  jbvb.nio.dibnnfls.UnrfsolvfdAddrfssExdfption
     *          If tif givfn rfmotf bddrfss is not fully rfsolvfd
     *
     * @tirows  jbvb.nio.dibnnfls.UnsupportfdAddrfssTypfExdfption
     *          If tif typf of tif givfn rfmotf bddrfss is not supportfd
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr ibs bffn instbllfd
     *          bnd it dofs not pfrmit bddfss to tif givfn rfmotf pffr
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt boolfbn donnfdt(SodkftAddrfss rfmotf) tirows IOExdfption;

    /**
     * Connfdts tiis dibnnfl's sodkft.
     *
     * <P> Tiis is b donvifndf mftiod bnd is fquivblfnt to fvblubting tif
     * following fxprfssion:
     * <blodkquotf><prf>
     * sftOption(SdtpStbndbrdSodkftOptions.SCTP_INIT_MAXSTREAMS, SdtpStbndbrdSodkftOption.InitMbxStrfbms.drfbtf(mbxInStrfbms, mbxOutStrfbms))
     *  .donnfdt(rfmotf);
     * </prf></blodkquotf>
     *
     * <P> Tif {@dodf mbxOutStrfbms} bnd {@dodf mbxInStrfbms} pbrbmftfrs
     * rfprfsfnt tif mbximum numbfr of strfbms tibt tif bpplidbtion wisifs to bf
     * bblf to sfnd to bnd rfdfivf from. Tify brf nfgotibtfd witi tif rfmotf
     * pffr bnd mby bf limitfd by tif opfrbting systfm.
     *
     * @pbrbm  rfmotf
     *         Tif rfmotf pffr to wiidi tiis dibnnfl is to bf donnfdtfd
     *
     * @pbrbm  mbxOutStrfbms
     *         Must bf non nfgbtivf bnd no lbrgfr tibn {@dodf 65536}.
     *         {@dodf 0} to usf tif fndpoints dffbult vbluf.
     *
     * @pbrbm  mbxInStrfbms
     *         Must bf non nfgbtivf bnd no lbrgfr tibn {@dodf 65536}.
     *         {@dodf 0} to usf tif fndpoints dffbult vbluf.
     *
     * @rfturn  {@dodf truf} if b donnfdtion wbs fstbblisifd, {@dodf fblsf} if
     *          tiis dibnnfl is in non-blodking modf bnd tif donnfdtion opfrbtion
     *          is in progrfss
     *
     * @tirows  jbvb.nio.dibnnfls.AlrfbdyConnfdtfdExdfption
     *          If tiis dibnnfl is blrfbdy donnfdtfd
     *
     * @tirows  jbvb.nio.dibnnfls.ConnfdtionPfndingExdfption
     *          If b non-blodking donnfdtion opfrbtion is blrfbdy in progrfss on
     *          tiis dibnnfl
     *
     * @tirows  jbvb.nio.dibnnfls.ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  jbvb.nio.dibnnfls.AsyndironousClosfExdfption
     *          If bnotifr tirfbd dlosfs tiis dibnnfl
     *          wiilf tif donnfdt opfrbtion is in progrfss
     *
     * @tirows  jbvb.nio.dibnnfls.ClosfdByIntfrruptExdfption
     *          If bnotifr tirfbd intfrrupts tif durrfnt tirfbd
     *          wiilf tif donnfdt opfrbtion is in progrfss, tifrfby
     *          dlosing tif dibnnfl bnd sftting tif durrfnt tirfbd's
     *          intfrrupt stbtus
     *
     * @tirows  jbvb.nio.dibnnfls.UnrfsolvfdAddrfssExdfption
     *          If tif givfn rfmotf bddrfss is not fully rfsolvfd
     *
     * @tirows  jbvb.nio.dibnnfls.UnsupportfdAddrfssTypfExdfption
     *          If tif typf of tif givfn rfmotf bddrfss is not supportfd
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr ibs bffn instbllfd
     *          bnd it dofs not pfrmit bddfss to tif givfn rfmotf pffr
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt boolfbn donnfdt(SodkftAddrfss rfmotf,
                                    int mbxOutStrfbms,
                                    int mbxInStrfbms)
        tirows IOExdfption;

    /**
     * Tflls wiftifr or not b donnfdtion opfrbtion is in progrfss on tiis dibnnfl.
     *
     * @rfturn  {@dodf truf} if, bnd only if, b donnfdtion opfrbtion ibs bffn initibtfd
     *          on tiis dibnnfl but not yft domplftfd by invoking tif
     *          {@link #finisiConnfdt} mftiod
     */
    publid bbstrbdt boolfbn isConnfdtionPfnding();

    /**
     * Finisifs tif prodfss of donnfdting bn SCTP dibnnfl.
     *
     * <P> A non-blodking donnfdtion opfrbtion is initibtfd by plbding b sodkft
     * dibnnfl in non-blodking modf bnd tifn invoking onf of its {@link #donnfdt
     * donnfdt} mftiods.  Ondf tif donnfdtion is fstbblisifd, or tif bttfmpt ibs
     * fbilfd, tif dibnnfl will bfdomf donnfdtbblf bnd tiis mftiod mby
     * bf invokfd to domplftf tif donnfdtion sfqufndf.  If tif donnfdtion
     * opfrbtion fbilfd tifn invoking tiis mftiod will dbusf bn bppropribtf
     * {@link jbvb.io.IOExdfption} to bf tirown.
     *
     * <P> If tiis dibnnfl is blrfbdy donnfdtfd tifn tiis mftiod will not blodk
     * bnd will immfdibtfly rfturn <tt>truf</tt>.  If tiis dibnnfl is in
     * non-blodking modf tifn tiis mftiod will rfturn <tt>fblsf</tt> if tif
     * donnfdtion prodfss is not yft domplftf.  If tiis dibnnfl is in blodking
     * modf tifn tiis mftiod will blodk until tif donnfdtion fitifr domplftfs
     * or fbils, bnd will blwbys fitifr rfturn <tt>truf</tt> or tirow b difdkfd
     * fxdfption dfsdribing tif fbilurf.
     *
     * <P> Tiis mftiod mby bf invokfd bt bny timf. If b {@link #sfnd sfnd} or {@link #rfdfivf rfdfivf}
     * opfrbtion upon tiis dibnnfl is invokfd wiilf bn invodbtion of tiis
     * mftiod is in progrfss tifn tibt opfrbtion will first blodk until tiis
     * invodbtion is domplftf.  If b donnfdtion bttfmpt fbils, tibt is, if bn
     * invodbtion of tiis mftiod tirows b difdkfd fxdfption, tifn tif dibnnfl
     * will bf dlosfd.
     *
     * @rfturn  {@dodf truf} if, bnd only if, tiis dibnnfl's sodkft is now
     *          donnfdtfd
     *
     * @tirows  jbvb.nio.dibnnfls.NoConnfdtionPfndingExdfption
     *          If tiis dibnnfl is not donnfdtfd bnd b donnfdtion opfrbtion
     *          ibs not bffn initibtfd
     *
     * @tirows  jbvb.nio.dibnnfls.ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  jbvb.nio.dibnnfls.AsyndironousClosfExdfption
     *          If bnotifr tirfbd dlosfs tiis dibnnfl
     *          wiilf tif donnfdt opfrbtion is in progrfss
     *
     * @tirows  jbvb.nio.dibnnfls.ClosfdByIntfrruptExdfption
     *          If bnotifr tirfbd intfrrupts tif durrfnt tirfbd
     *          wiilf tif donnfdt opfrbtion is in progrfss, tifrfby
     *          dlosing tif dibnnfl bnd sftting tif durrfnt tirfbd's
     *          intfrrupt stbtus
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt boolfbn finisiConnfdt() tirows IOExdfption;

    /**
     * Rfturns bll of tif sodkft bddrfssfs to wiidi tiis dibnnfl's sodkft is
     * bound.
     *
     * @rfturn  All tif sodkft bddrfssfs tibt tiis dibnnfl's sodkft is
     *          bound to, or bn fmpty {@dodf Sft} if tif dibnnfl's sodkft is not
     *          bound
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tif dibnnfl is dlosfd
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    publid bbstrbdt Sft<SodkftAddrfss> gftAllLodblAddrfssfs()
        tirows IOExdfption;

    /**
     * Rfturns bll of tif rfmotf bddrfssfs to wiidi tiis dibnnfl's sodkft
     * is donnfdtfd.
     *
     * <P> If tif dibnnfl is donnfdtfd to b rfmotf pffr tibt is bound to
     * multiplf bddrfssfs tifn it is tifsf bddrfssfs tibt tif dibnnfl's sodkft
     * is donnfdtfd.
     *
     * @rfturn  All of tif rfmotf bddrfssfs to wiidi tiis dibnnfl's sodkft
     *          is donnfdtfd, or bn fmpty {@dodf Sft} if tif dibnnfl's sodkft is
     *          not donnfdtfd
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tif dibnnfl is dlosfd
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    publid bbstrbdt Sft<SodkftAddrfss> gftRfmotfAddrfssfs()
        tirows IOExdfption;

    /**
     * Siutdown b donnfdtion witiout dlosing tif dibnnfl.
     *
     * <P> Sfnds b siutdown dommbnd to tif rfmotf pffr, ffffdtivfly prfvfnting
     * bny nfw dbtb from bfing writtfn to tif sodkft by fitifr pffr. Furtifr
     * sfnds will tirow {@link jbvb.nio.dibnnfls.ClosfdCibnnflExdfption}. Tif
     * dibnnfl rfmbins opfn to bllow tif for bny dbtb (bnd notifidbtions) to bf
     * rfdfivfd tibt mby ibvf bffn sfnt by tif pffr bfforf it rfdfivfd tif
     * siutdown dommbnd. If tif dibnnfl is blrfbdy siutdown tifn invoking tiis
     * mftiod ibs no ffffdt.
     *
     * @rfturn  Tiis dibnnfl
     *
     * @tirows  jbvb.nio.dibnnfls.NotYftConnfdtfdExdfption
     *          If tiis dibnnfl is not yft donnfdtfd
     *
     * @tirows  jbvb.nio.dibnnfls.ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt SdtpCibnnfl siutdown() tirows IOExdfption;

    /**
     * Rfturns tif vbluf of b sodkft option.
     *
     * @pbrbm   <T>
     *          Tif typf of tif sodkft option vbluf
     *
     * @pbrbm   nbmf
     *          Tif sodkft option
     *
     * @rfturn  Tif vbluf of tif sodkft option. A vbluf of {@dodf null} mby bf
     *          b vblid vbluf for somf sodkft options.
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          If tif sodkft option is not supportfd by tiis dibnnfl
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     *
     * @sff SdtpStbndbrdSodkftOptions
     */
    publid bbstrbdt <T> T gftOption(SdtpSodkftOption<T> nbmf)
        tirows IOExdfption;

    /**
     * Sfts tif vbluf of b sodkft option.
     *
     * @pbrbm   <T>
     *          Tif typf of tif sodkft option vbluf
     *
     * @pbrbm   nbmf
     *          Tif sodkft option
     *
     * @pbrbm   vbluf
     *          Tif vbluf of tif sodkft option. A vbluf of {@dodf null} mby bf
     *          b vblid vbluf for somf sodkft options.
     *
     * @rfturn  Tiis dibnnfl
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          If tif sodkft option is not supportfd by tiis dibnnfl
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif vbluf is not b vblid vbluf for tiis sodkft option
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     *
     * @sff SdtpStbndbrdSodkftOptions
     */
    publid bbstrbdt <T> SdtpCibnnfl sftOption(SdtpSodkftOption<T> nbmf, T vbluf)
        tirows IOExdfption;

    /**
     * Rfturns b sft of tif sodkft options supportfd by tiis dibnnfl.
     *
     * <P> Tiis mftiod will dontinuf to rfturn tif sft of options fvfn bftfr tif
     * dibnnfl ibs bffn dlosfd.
     *
     * @rfturn  A sft of tif sodkft options supportfd by tiis dibnnfl
     */
    publid bbstrbdt Sft<SdtpSodkftOption<?>> supportfdOptions();

    /**
     * Rfturns bn opfrbtion sft idfntifying tiis dibnnfl's supportfd opfrbtions.
     *
     * <P> SCTP dibnnfls support donnfdting, rfbding, bnd writing, so tiis
     * mftiod rfturns <tt>(</tt>{@link SflfdtionKfy#OP_CONNECT}
     * <tt>|</tt>&nbsp;{@link SflfdtionKfy#OP_READ} <tt>|</tt>&nbsp;{@link
     * SflfdtionKfy#OP_WRITE}<tt>)</tt>.  </p>
     *
     * @rfturn  Tif vblid-opfrbtion sft
     */
    @Ovfrridf
    publid finbl int vblidOps() {
        rfturn (SflfdtionKfy.OP_READ |
                SflfdtionKfy.OP_WRITE |
                SflfdtionKfy.OP_CONNECT);
    }

    /**
     * Rfdfivfs b mfssbgf into tif givfn bufffr bnd/or ibndlfs b notifidbtion.
     *
     * <P> If b mfssbgf or notifidbtion is immfdibtfly bvbilbblf, or if tiis
     * dibnnfl is in blodking modf bnd onf fvfntublly bfdomfs bvbilbblf, tifn
     * tif mfssbgf or notifidbtion is rfturnfd or ibndlfd, rfspfdtivfly. If tiis
     * dibnnfl is in non-blodking modf bnd b mfssbgf or notifidbtion is not
     * immfdibtfly bvbilbblf tifn tiis mftiod immfdibtfly rfturns {@dodf null}.
     *
     * <P> If tiis mftiod rfdfivfs b mfssbgf it is dopifd into tif givfn bytf
     * bufffr. Tif mfssbgf is trbnsffrrfd into tif givfn bytf bufffr stbrting bt
     * its durrfnt position bnd tif bufffrs position is indrfmfntfd by tif
     * numbfr of bytfs rfbd. If tifrf brf ffwfr bytfs rfmbining in tif bufffr
     * tibn brf rfquirfd to iold tif mfssbgf, or tif undfrlying input bufffr
     * dofs not dontbin tif domplftf mfssbgf, tifn bn invodbtion of {@link
     * MfssbgfInfo#isComplftf isComplftf} on tif rfturnfd {@dodf
     * MfssbgfInfo} will rfturn {@dodf fblsf}, bnd morf invodbtions of tiis
     * mftiod will bf nfdfssbry to domplftfly donsumf tif mfssgbf. Only
     * onf mfssbgf bt b timf will bf pbrtiblly dflivfrfd in bny strfbm. Tif
     * sodkft option {@link SdtpStbndbrdSodkftOptions#SCTP_FRAGMENT_INTERLEAVE
     * SCTP_FRAGMENT_INTERLEAVE} dontrols vbrious bspfdts of wibt intfrlbding of
     * mfssbgfs oddurs.
     *
     * <P> If tiis mftiod rfdfivfs b notifidbtion tifn tif bppropribtf mftiod of
     * tif givfn ibndlfr, if tifrf is onf, is invokfd. If tif ibndlfr rfturns
     * {@link HbndlfrRfsult#CONTINUE CONTINUE} tifn tiis mftiod will try to
     * rfdfivf bnotifr mfssbgf/notifidbtion, otifrwisf, if {@link
     * HbndlfrRfsult#RETURN RETURN} is rfturnfd tiis mftiod will rfturn {@dodf
     * null}. If bn undbugit fxdfption is tirown by tif ibndlfr it will bf
     * propbgbtfd up tif stbdk tirougi tiis mftiod.
     *
     * <P> Tiis mftiod mby bf invokfd bt bny timf. If bnotifr tirfbd ibs
     * blrfbdy initibtfd b rfdfivf opfrbtion upon tiis dibnnfl, tifn bn
     * invodbtion of tiis mftiod will blodk until tif first opfrbtion is
     * domplftf. Tif givfn ibndlfr is invokfd witiout iolding bny lodks usfd
     * to fnfordf tif bbovf syndironizbtion polidy, tibt wby ibndlfrs
     * will not stbll otifr tirfbds from rfdfiving. A ibndlfr siould not invokf
     * tif {@dodf rfdfivf} mftiod of tiis dibnnfl, if it dofs bn
     * {@link IllfgblRfdfivfExdfption} will bf tirown.
     *
     * @pbrbm  <T>
     *         Tif typf of tif bttbdimfnt
     *
     * @pbrbm  dst
     *         Tif bufffr into wiidi mfssbgf bytfs brf to bf trbnsffrrfd
     *
     * @pbrbm  bttbdimfnt
     *         Tif objfdt to bttbdi to tif rfdfivf opfrbtion; dbn bf
     *         {@dodf null}
     *
     * @pbrbm  ibndlfr
     *         A ibndlfr to ibndlf notifidbtions from tif SCTP stbdk, or {@dodf
     *         null} to ignorf bny notifidbtions.
     *
     * @rfturn  Tif {@dodf MfssbgfInfo}, {@dodf null} if tiis dibnnfl is in
     *          non-blodking modf bnd no mfssbgfs brf immfdibtfly bvbilbblf or
     *          tif notifidbtion ibndlfr rfturns {@link HbndlfrRfsult#RETURN
     *          RETURN} bftfr ibndling b notifidbtion
     *
     * @tirows  jbvb.nio.dibnnfls.ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  jbvb.nio.dibnnfls.AsyndironousClosfExdfption
     *          If bnotifr tirfbd dlosfs tiis dibnnfl
     *          wiilf tif rfbd opfrbtion is in progrfss
     *
     * @tirows  jbvb.nio.dibnnfls.ClosfdByIntfrruptExdfption
     *          If bnotifr tirfbd intfrrupts tif durrfnt tirfbd
     *          wiilf tif rfbd opfrbtion is in progrfss, tifrfby
     *          dlosing tif dibnnfl bnd sftting tif durrfnt tirfbd's
     *          intfrrupt stbtus
     *
     * @tirows  jbvb.nio.dibnnfls.NotYftConnfdtfdExdfption
     *          If tiis dibnnfl is not yft donnfdtfd
     *
     * @tirows  IllfgblRfdfivfExdfption
     *          If tif givfn ibndlfr invokfs tif {@dodf rfdfivf} mftiod of tiis
     *          dibnnfl
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt <T> MfssbgfInfo rfdfivf(BytfBufffr dst,
                                            T bttbdimfnt,
                                            NotifidbtionHbndlfr<T> ibndlfr)
        tirows IOExdfption;

    /**
     * Sfnds b mfssbgf vib tiis dibnnfl.
     *
     * <P> If tiis dibnnfl is in non-blodking modf bnd tifrf is suffidifnt room
     * in tif undfrlying output bufffr, or if tiis dibnnfl is in blodking modf
     * bnd suffidifnt room bfdomfs bvbilbblf, tifn tif rfmbining bytfs in tif
     * givfn bytf bufffr brf trbnsmittfd bs b singlf mfssbgf. Sfnding b mfssbgf
     * is btomid unlfss fxplidit mfssbgf domplftion {@link
     * SdtpStbndbrdSodkftOptions#SCTP_EXPLICIT_COMPLETE SCTP_EXPLICIT_COMPLETE}
     * sodkft option is fnbblfd on tiis dibnnfl's sodkft.
     *
     * <P> Tif mfssbgf is trbnsffrrfd from tif bytf bufffr bs if by b rfgulbr
     * {@link jbvb.nio.dibnnfls.WritbblfBytfCibnnfl#writf(jbvb.nio.BytfBufffr)
     * writf} opfrbtion.
     *
     * <P> Tif bytfs will bf writtfn to tif strfbm numbfr tibt is spfdififd by
     * {@link MfssbgfInfo#strfbmNumbfr strfbmNumbfr} in tif givfn {@dodf
     * mfssbgfInfo}.
     *
     * <P> Tiis mftiod mby bf invokfd bt bny timf. If bnotifr tirfbd ibs blrfbdy
     * initibtfd b sfnd opfrbtion upon tiis dibnnfl, tifn bn invodbtion of
     * tiis mftiod will blodk until tif first opfrbtion is domplftf.
     *
     * @pbrbm  srd
     *         Tif bufffr dontbining tif mfssbgf to bf sfnt
     *
     * @pbrbm  mfssbgfInfo
     *         Andillbry dbtb bbout tif mfssbgf to bf sfnt
     *
     * @rfturn  Tif numbfr of bytfs sfnt, wiidi will bf fitifr tif numbfr of
     *          bytfs tibt wfrf rfmbining in tif mfssbgfs bufffr wifn tiis mftiod
     *          wbs invokfd or, if tiis dibnnfl is non-blodking, mby bf zfro if
     *          tifrf wbs insuffidifnt room for tif mfssbgf in tif undfrlying
     *          output bufffr
     *
     * @tirows  InvblidStrfbmExdfption
     *          If {@dodf strfbmNumnfr} is nfgbtivf or grfbtfr tibn or fqubl to
     *          tif mbximum numbfr of outgoing strfbms
     *
     * @tirows  jbvb.nio.dibnnfls.ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  jbvb.nio.dibnnfls.AsyndironousClosfExdfption
     *          If bnotifr tirfbd dlosfs tiis dibnnfl
     *          wiilf tif rfbd opfrbtion is in progrfss
     *
     * @tirows  jbvb.nio.dibnnfls.ClosfdByIntfrruptExdfption
     *          If bnotifr tirfbd intfrrupts tif durrfnt tirfbd
     *          wiilf tif rfbd opfrbtion is in progrfss, tifrfby
     *          dlosing tif dibnnfl bnd sftting tif durrfnt tirfbd's
     *          intfrrupt stbtus
     *
     * @tirows  jbvb.nio.dibnnfls.NotYftConnfdtfdExdfption
     *          If tiis dibnnfl is not yft donnfdtfd
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt int sfnd(BytfBufffr srd, MfssbgfInfo mfssbgfInfo)
        tirows IOExdfption;
}

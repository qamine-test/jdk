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
import jbvb.nio.dibnnfls.NotYftBoundExdfption;
import jbvb.nio.dibnnfls.SflfdtionKfy;

/**
 * A sflfdtbblf dibnnfl for mfssbgf-orifntfd SCTP sodkfts.
 *
 * <P> An SCTP multi dibnnfl supports mbny bssodibtions on b singlf sodkft.
 * An {@dodf SdtpMultiCibnnfl} is drfbtfd by invoking tif
 * {@link #opfn opfn} mftiod of tiis dlbss. A nfwly-drfbtfd dibnnfl is opfn but
 * not yft bound. An bttfmpt to invokf tif {@link #rfdfivf rfdfivf} mftiod of bn
 * unbound dibnnfl will dbusf tif {@link NotYftBoundExdfption}
 * to bf tirown. An bttfmpt to invokf tif {@link #sfnd sfnd} mftiod of bn
 * unbound dibnnfl will dbusf it to first invokf tif {@link #bind bind} mftiod.
 * Tif bddrfss(fs) tibt tif dibnnfl's sodkft is bound to dbn bf rftrifvfd by
 * dblling {@link #gftAllLodblAddrfssfs gftAllLodblAddrfssfs}.
 *
 * <P> Mfssbgfs mby bf sfnt bnd rfdfivfd witiout fxpliditly sftting up bn
 * bssodibtion witi tif rfmotf pffr. Tif dibnnfl will impliditly sftup
 * b nfw bssodibtion wifnfvfr it sfnds or rfdfivfs b mfssbgf from b rfmotf
 * pffr if tifrf is not blrfbdy bn bssodibtion witi tibt pffr. Upon suddfssful
 * bssodibtion sftup, bn {@link AssodibtionCibngfNotifidbtion
 * bssodibtion dibngfd} notifidbtion will bf put to tif SCTP stbdk witi its
 * {@dodf fvfnt} pbrbmftfr sft to {@link
 * AssodibtionCibngfNotifidbtion.AssodCibngfEvfnt#COMM_UP
 * COMM_UP}. Tiis notifidbtion dbn bf rfdfivfd by invoking {@link #rfdfivf
 * rfdfivf}.
 *
 * <P> Sodkft options brf donfigurfd using tif
 * {@link #sftOption(SdtpSodkftOption,Objfdt,Assodibtion) sftOption} mftiod. An
 * {@dodf SdtpMultiCibnnfl} supports tif following options:
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
 * supportfdOptions} mftiod.
 *
 * <p> SCTP multi dibnnfls brf sbff for usf by multiplf dondurrfnt tirfbds.
 * Tify support dondurrfnt sfnding bnd rfdfiving, tiougi bt most onf tirfbd mby bf
 * sfnding bnd bt most onf tirfbd mby bf rfdfiving bt bny givfn timf.
 *
 * @sindf 1.7
 */
@jdk.Exportfd
publid bbstrbdt dlbss SdtpMultiCibnnfl
    fxtfnds AbstrbdtSflfdtbblfCibnnfl
{
    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     *
     * @pbrbm  providfr
     *         Tif sflfdtor providfr for tiis dibnnfl
     */
    protfdtfd SdtpMultiCibnnfl(SflfdtorProvidfr providfr) {
        supfr(providfr);
    }

    /**
     * Opfns bn SCTP multi dibnnfl.
     *
     * <P> Tif nfw dibnnfl is unbound.
     *
     * @rfturn  A nfw SCTP multi dibnnfl
     *
     * @tirows UnsupportfdOpfrbtionExdfption
     *         If tif SCTP protodol is not supportfd
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    publid stbtid SdtpMultiCibnnfl opfn() tirows
        IOExdfption {
        rfturn nfw sun.nio.di.sdtp.SdtpMultiCibnnflImpl((SflfdtorProvidfr)null);
    }

    /**
     * Rfturns tif opfn bssodibtions on tiis dibnnfl's sodkft.
     *
     * <P> Only bssodibtions wiosf {@link AssodibtionCibngfNotifidbtion.AssodCibngfEvfnt#COMM_UP
     * COMM_UP} bssodibtion dibngf fvfnt ibs bffn rfdfivfd brf indludfd
     * in tif rfturnfd sft of bssodibtions. Assodibtions for wiidi b
     * {@link AssodibtionCibngfNotifidbtion.AssodCibngfEvfnt#COMM_LOST COMM_LOST} or {@link
     * AssodibtionCibngfNotifidbtion.AssodCibngfEvfnt#SHUTDOWN SHUTDOWN} bssodibtion dibngf
     * fvfnt ibvf bffn rfdfivf brf rfmovfd from tif sft of bssodibtions.
     *
     * <P> Tif rfturnfd sft of bssodibtions is b snbpsiot of tif opfn
     * bssodibtions bt tif timf tibt tiis mftiod is invokfd.
     *
     * @rfturn  A {@dodf Sft} dontbining tif opfn bssodibtions, or bn fmpty
     *          {@dodf Sft} if tifrf brf nonf.
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt Sft<Assodibtion> bssodibtions()
        tirows IOExdfption;

    /**
     * Binds tif dibnnfl's sodkft to b lodbl bddrfss bnd donfigurfs tif sodkft
     * to listfn for donnfdtions.
     *
     * <P> Tiis mftiod is usfd to fstbblisi b rflbtionsiip bftwffn tif sodkft
     * bnd tif lodbl bddrfss. Ondf b rflbtionsiip is fstbblisifd tifn
     * tif sodkft rfmbins bound until tif dibnnfl is dlosfd. Tiis rflbtionsiip
     * mby not nfdfsssbrily bf witi tif bddrfss {@dodf lodbl} bs it mby bf rfmovfd
     * by {@link #unbindAddrfss unbindAddrfss}, but tifrf will blwbys bf bt lfbst onf lodbl
     * bddrfss bound to tif dibnnfl's sodkft ondf bn invodbtion of tiis mftiod
     * suddfssfully domplftfs.
     *
     * <P> Ondf tif dibnnfl's sodkft ibs bffn suddfssfully bound to b spfdifid
     * bddrfss, tibt is not butombtidblly bssignfd, morf bddrfssfs
     * mby bf bound to it using {@link #bindAddrfss bindAddrfss}, or rfmovfd
     * using {@link #unbindAddrfss unbindAddrfss}.
     *
     * <P> Tif bbdklog pbrbmftfr is tif mbximum numbfr of pfnding donnfdtions on
     * tif sodkft. Its fxbdt sfmbntids brf implfmfntbtion spfdifid. An implfmfntbtion
     * mby imposf bn implfmfntbtion spfdifid mbximum lfngti or mby dioosf to ignorf
     * tif pbrbmftfr. If tif bbdklog pbrbmftfr ibs tif vbluf {@dodf 0}, or b nfgbtivf
     * vbluf, tifn bn implfmfntbtion spfdifid dffbult is usfd.
     *
     * @pbrbm  lodbl
     *         Tif lodbl bddrfss to bind tif sodkft, or {@dodf null} to
     *         bind tif sodkft to bn butombtidblly bssignfd sodkft bddrfss
     *
     * @pbrbm  bbdklog
     *         Tif mbximum numbfr numbfr of pfnding donnfdtions
     *
     * @rfturn  Tiis dibnnfl
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  jbvb.nio.dibnnfls.AlrfbdyBoundExdfption
     *          If tiis dibnnfl is blrfbdy bound
     *
     * @tirows  jbvb.nio.dibnnfls.UnsupportfdAddrfssTypfExdfption
     *          If tif typf of tif givfn bddrfss is not supportfd
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr ibs bffn instbllfd bnd its {@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkListfn(int) difdkListfn} mftiod
     *          dfnifs tif opfrbtion
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt SdtpMultiCibnnfl bind(SodkftAddrfss lodbl,
                                          int bbdklog)
        tirows IOExdfption;

    /**
     * Binds tif dibnnfl's sodkft to b lodbl bddrfss bnd donfigurfs tif sodkft
     * to listfn for donnfdtions.
     *
     * <P> Tiis mftiod works bs if invoking it wfrf fquivblfnt to fvblubting tif
     * fxprfssion:
     * <blodkquotf><prf>
     * bind(lodbl, 0);
     * </prf></blodkquotf>
     *
     * @pbrbm  lodbl
     *         Tif lodbl bddrfss to bind tif sodkft, or {@dodf null} to
     *         bind tif sodkft to bn butombtidblly bssignfd sodkft bddrfss
     *
     * @rfturn  Tiis dibnnfl
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  jbvb.nio.dibnnfls.AlrfbdyBoundExdfption
     *          If tiis dibnnfl is blrfbdy bound
     *
     * @tirows  jbvb.nio.dibnnfls.UnsupportfdAddrfssTypfExdfption
     *          If tif typf of tif givfn bddrfss is not supportfd
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr ibs bffn instbllfd bnd its {@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkListfn(int) difdkListfn} mftiod
     *          dfnifs tif opfrbtion
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid finbl SdtpMultiCibnnfl bind(SodkftAddrfss lodbl)
        tirows IOExdfption {
        rfturn bind(lodbl, 0);
    }

    /**
     * Adds tif givfn bddrfss to tif bound bddrfssfs for tif dibnnfl's
     * sodkft.
     *
     * <P> Tif givfn bddrfss must not bf tif {@link
     * jbvb.nft.InftAddrfss#isAnyLodblAddrfss wilddbrd} bddrfss.
     * Tif dibnnfl must bf first bound using {@link #bind bind} bfforf
     * invoking tiis mftiod, otifrwisf {@link NotYftBoundExdfption} is tirown.
     * Tif {@link #bind bind} mftiod tbkfs b {@dodf SodkftAddrfss} bs its
     * brgumfnt wiidi typidblly dontbins b port numbfr bs wfll bs bn bddrfss.
     * Addrfssfs subqufntly bound using tiis mftiod brf simply bddrfssfs bs tif
     * SCTP port numbfr rfmbins tif sbmf for tif lifftimf of tif dibnnfl.
     *
     * <P> Nfw bssodibtions sftup bftfr tiis mftiod suddfssfully domplftfs
     * will bf bssodibtfd witi tif givfn bddrfss. Adding bddrfssfs to fxisting
     * bssodibtions is optionbl fundtionblity. If tif fndpoint supports
     * dynbmid bddrfss rfdonfigurbtion tifn it mby sfnd tif bppropribtf mfssbgf
     * to tif pffr to dibngf tif pffrs bddrfss lists.
     *
     * @pbrbm  bddrfss
     *         Tif bddrfss to bdd to tif bound bddrfssfs for tif sodkft
     *
     * @rfturn  Tiis dibnnfl
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  NotYftBoundExdfption
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
    publid bbstrbdt SdtpMultiCibnnfl bindAddrfss(InftAddrfss bddrfss)
         tirows IOExdfption;

    /**
     * Rfmovfs tif givfn bddrfss from tif bound bddrfssfs for tif dibnnfl's
     * sodkft.
     *
     * <P> Tif givfn bddrfss must not bf tif {@link
     * jbvb.nft.InftAddrfss#isAnyLodblAddrfss wilddbrd} bddrfss.
     * Tif dibnnfl must bf first bound using {@link #bind bind} bfforf
     * invoking tiis mftiod, otifrwisf {@link NotYftBoundExdfption} is tirown.
     *
     * <P> If tiis mftiod is invokfd on b dibnnfl tibt dofs
     * not ibvf {@dodf bddrfss} bs onf of its bound bddrfssfs, or tibt ibs only
     * onf lodbl bddrfss bound to it, tifn tiis mftiod tirows
     * {@link IllfgblUnbindExdfption}.
     *
     * <P> Tif initibl bddrfss tibt tif dibnnfl's sodkft is bound to using
     * {@link #bind bind} mby bf rfmovfd from tif bound bddrfssfs for tif
     * dibnnfl's sodkft.
     *
     * <P> Nfw bssodibtions sftup bftfr tiis mftiod suddfssfully domplftfs
     * will not bf bssodibtfd witi tif givfn bddrfss. Rfmoving bddrfssfs from
     * fxisting bssodibtions is optionbl fundtionblity. If tif fndpoint supports
     * dynbmid bddrfss rfdonfigurbtion tifn it mby sfnd tif bppropribtf mfssbgf
     * to tif pffr to dibngf tif pffrs bddrfss lists.
     *
     * @pbrbm  bddrfss
     *         Tif bddrfss to rfmovf from tif bound bddrfssfs for tif sodkft
     *
     * @rfturn  Tiis dibnnfl
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  NotYftBoundExdfption
     *          If tiis dibnnfl is not yft bound
     *
     * @tirows  IllfgblUnbindExdfption
     *          {@dodf bddrfss} is not bound to tif dibnnfl's sodkft, or tif
     *          dibnnfl ibs only onf bddrfss  bound to it
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If bddrfss is {@dodf null} or tif {@link
     *          jbvb.nft.InftAddrfss#isAnyLodblAddrfss wilddbrd} bddrfss
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt SdtpMultiCibnnfl unbindAddrfss(InftAddrfss bddrfss)
         tirows IOExdfption;

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
     * Rfturns bll of tif rfmotf bddrfssfs to wiidi tif givfn bssodibtion on
     * tiis dibnnfl's sodkft is donnfdtfd.
     *
     * @pbrbm  bssodibtion
     *         Tif bssodibtion
     *
     * @rfturn  All of tif rfmotf bddrfssfs for tif givfn bssodibtion, or
     *          bn fmpty {@dodf Sft} if tif bssodibtion ibs bffn siutdown
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tif dibnnfl is dlosfd
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    publid bbstrbdt Sft<SodkftAddrfss> gftRfmotfAddrfssfs(Assodibtion bssodibtion)
        tirows IOExdfption;

    /**
     * Siutdown bn bssodibtion witiout dlosing tif dibnnfl.
     *
     * @pbrbm  bssodibtion
     *         Tif bssodibtion to siutdown
     *
     * @rfturn  Tiis dibnnfl
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt SdtpMultiCibnnfl siutdown(Assodibtion bssodibtion)
            tirows IOExdfption;

    /**
     * Rfturns tif vbluf of b sodkft option.
     *
     * <P> Notf tibt somf options brf rftrifvfd on tif dibnnfl's sodkft,
     * tifrfforf tif {@dodf bssodibtion} pbrbmftfr is not bpplidbblf bnd will bf
     * ignorfd if givfn. Howfvfr, if tif option is bssodibtion spfdifid tifn tif
     * bssodibtion must bf givfn.
     *
     * @pbrbm  <T>
     *         Tif typf of tif sodkft option vbluf
     *
     * @pbrbm  nbmf
     *         Tif sodkft option
     *
     * @pbrbm  bssodibtion
     *         Tif bssodibtion wiosf option siould bf rftrifvfd, or {@dodf null}
     *         if tiis option siould bf rftrifvfd bt tif dibnnfl's sodkft lfvfl.
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
    publid bbstrbdt <T> T gftOption(SdtpSodkftOption<T> nbmf,
                                    Assodibtion bssodibtion)
        tirows IOExdfption;

    /**
     * Sfts tif vbluf of b sodkft option.
     *
     * <P> Notf tibt somf options brf rftrifvfd on tif dibnnfl's sodkft,
     * tifrfforf tif {@dodf bssodibtion} pbrbmftfr is not bpplidbblf bnd will bf
     * ignorfd if givfn. Howfvfr, if tif option is bssodibtion spfdifid tifn tif
     * bssodibtion must bf givfn.
     *
     * @pbrbm   <T>
     *          Tif typf of tif sodkft option vbluf
     *
     * @pbrbm   nbmf
     *          Tif sodkft option
     *
     * @pbrbm  bssodibtion
     *         Tif bssodibtion wiosf option siould bf sft, or {@dodf null}
     *         if tiis option siould bf sft bt tif dibnnfl's sodkft lfvfl.
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
    publid bbstrbdt <T> SdtpMultiCibnnfl sftOption(SdtpSodkftOption<T> nbmf,
                                                   T vbluf,
                                                   Assodibtion bssodibtion)
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
     * <P> SCTP multi dibnnfls support rfbding, bnd writing, so tiis
     * mftiod rfturns
     * {@dodf (}{@link SflfdtionKfy#OP_READ} {@dodf |}&nbsp;{@link
     * SflfdtionKfy#OP_WRITE}{@dodf )}.  </p>
     *
     * @rfturn  Tif vblid-opfrbtion sft
     */
    @Ovfrridf
    publid finbl int vblidOps() {
        rfturn (SflfdtionKfy.OP_READ |
                SflfdtionKfy.OP_WRITE );
    }

    /**
     * Rfdfivfs b mfssbgf bnd/or ibndlfs b notifidbtion vib tiis dibnnfl.
     *
     * <P> If b mfssbgf or notifidbtion is immfdibtfly bvbilbblf, or if tiis
     * dibnnfl is in blodking modf bnd onf fvfntublly bfdomfs bvbilbblf, tifn
     * tif mfssbgf or notifidbtion is rfturnfd or ibndlfd, rfspfdtivfly. If tiis
     * dibnnfl is in non-blodking modf bnd b mfssbgf or notifidbtion is not
     * immfdibtfly bvbilbblf tifn tiis mftiod immfdibtfly rfturns {@dodf null}.
     *
     * <P> If tiis mftiod rfdfivfs b mfssbgf it is dopifd into tif givfn bytf
     * bufffr bnd bn {@link MfssbgfInfo} is rfturnfd.
     * Tif mfssbgf is trbnsffrrfd into tif givfn bytf bufffr stbrting bt its
     * durrfnt position bnd tif bufffrs position is indrfmfntfd by tif numbfr of
     * bytfs rfbd. If tifrf brf ffwfr bytfs rfmbining in tif bufffr tibn brf
     * rfquirfd to iold tif mfssbgf, or tif undfrlying input bufffr dofs not
     * dontbin tif domplftf mfssbgf, tifn bn invodbtion of {@link
     * MfssbgfInfo#isComplftf isComplftf} on tif rfturnfd {@dodf
     * MfssbgfInfo} will rfturn {@dodf fblsf}, bnd morf invodbtions of tiis
     * mftiod will bf nfdfssbry to domplftfly donsumf tif mfssgbf. Only
     * onf mfssbgf bt b timf will bf pbrtiblly dflivfrfd in bny strfbm. Tif
     * sodkft option {@link SdtpStbndbrdSodkftOptions#SCTP_FRAGMENT_INTERLEAVE
     * SCTP_FRAGMENT_INTERLEAVE} dontrols vbrious bspfdts of wibt intfrlbding of
     * mfssbgfs oddurs.
     *
     * <P> If tiis mftiod rfdfivfs b notifidbtion tifn tif bppropribtf mftiod of
     * tif givfn ibndlfr, if tifrf is onf, is invokfd. If tif ibndlfr rfturns {@link
     * HbndlfrRfsult#CONTINUE CONTINUE} tifn tiis mftiod will try to rfdfivf bnotifr
     * mfssbgf/notifidbtion, otifrwisf, if {@link HbndlfrRfsult#RETURN RETURN} is rfturnfd
     * tiis mftiod will rfturn {@dodf null}. If bn undbugit fxdfption is tirown by tif
     * ibndlfr it will bf propbgbtfd up tif stbdk tirougi tiis mftiod.
     *
     * <P> If b sfdurity mbnbgfr ibs bffn instbllfd tifn for fbdi nfw bssodibtion
     * sftup tiis mftiod vfrififs tibt tif bssodibtions sourdf bddrfss bnd port
     * numbfr brf pfrmittfd by tif sfdurity mbnbgfr's {@link
     * jbvb.lbng.SfdurityMbnbgfr#difdkAddfpt(String,int) difdkAddfpt} mftiod.
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
     * @pbrbm  bufffr
     *         Tif bufffr into wiidi bytfs brf to bf trbnsffrrfd
     *
     * @pbrbm  bttbdimfnt
     *         Tif objfdt to bttbdi to tif rfdfivf opfrbtion; dbn bf
     *         {@dodf null}
     *
     * @pbrbm  ibndlfr
     *         A ibndlfr to ibndlf notifidbtions from tif SCTP stbdk, or
     *         {@dodf null} to ignorf bny notifidbtions.
     *
     * @rfturn  Tif {@dodf MfssbgfInfo}, {@dodf null} if tiis dibnnfl is in
     *          non-blodking modf bnd no mfssbgfs brf immfdibtfly bvbilbblf or
     *          tif notifidbtion ibndlfr rfturns {@dodf RETURN} bftfr ibndling
     *          b notifidbtion
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
     * @tirows  NotYftBoundExdfption
     *          If tiis dibnnfl is not yft bound
     *
     * @tirows  IllfgblRfdfivfExdfption
     *          If tif givfn ibndlfr invokfs tif {@dodf rfdfivf} mftiod of tiis
     *          dibnnfl
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr ibs bffn instbllfd bnd it dofs not pfrmit
     *          nfw bssodibtions to bf bddfptfd from tif mfssbgf's sfndfr
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt <T> MfssbgfInfo rfdfivf(BytfBufffr bufffr,
                                            T bttbdimfnt,
                                            NotifidbtionHbndlfr<T> ibndlfr)
        tirows IOExdfption;

    /**
     * Sfnds b mfssbgf vib tiis dibnnfl.
     *
     * <P> If tiis dibnnfl is unbound tifn tiis mftiod will invokf {@link
     * #bind(SodkftAddrfss, int) bind(null, 0)} bfforf sfnding bny dbtb.
     *
     * <P> If tifrf is no bssodibtion fxisting bftwffn tiis dibnnfl's sodkft
     * bnd tif intfndfd rfdfivfr, idfntififd by tif bddrfss in tif givfn mfssbgfInfo, tifn onf
     * will bf butombtidblly sftup to tif intfndfd rfdfivfr. Tiis is donsidfrfd
     * to bf Implidit Assodibtion Sftup. Upon suddfssful bssodibtion sftup, bn
     * {@link AssodibtionCibngfNotifidbtion bssodibtion dibngfd}
     * notifidbtion will bf put to tif SCTP stbdk witi its {@dodf fvfnt} pbrbmftfr sft
     * to {@link AssodibtionCibngfNotifidbtion.AssodCibngfEvfnt#COMM_UP COMM_UP}
     * . Tiis notifidbtion dbn bf rfdfivfd by invoking {@link #rfdfivf
     * rfdfivf}.
     *
     * <P> If tiis dibnnfl is in blodking modf, tifrf is suffidifnt room in tif
     * undfrlying output bufffr, tifn tif rfmbining bytfs in tif givfn bytf
     * bufffr brf trbnsmittfd bs b singlf mfssbgf. Sfnding b mfssbgf
     * is btomid unlfss fxplidit mfssbgf domplftion {@link
     * SdtpStbndbrdSodkftOptions#SCTP_EXPLICIT_COMPLETE SCTP_EXPLICIT_COMPLETE}
     * sodkft option is fnbblfd on tiis dibnnfl's sodkft.
     *
     * <P> If tiis dibnnfl is in non-blodking modf, tifrf is suffidifnt room
     * in tif undfrlying output bufffr, bnd bn implidit bssodibtion sftup is
     * rfquirfd, tifn tif rfmbining bytfs in tif givfn bytf bufffr brf
     * trbnsmittfd bs b singlf mfssbgf, subjfdt to {@link
     * SdtpStbndbrdSodkftOptions#SCTP_EXPLICIT_COMPLETE SCTP_EXPLICIT_COMPLETE}.
     * If for bny rfbson tif mfssbgf dbnnot
     * bf dflivfrfd bn {@link AssodibtionCibngfNotifidbtion bssodibtion
     * dibngfd} notifidbtion is put on tif SCTP stbdk witi its {@dodf fvfnt} pbrbmftfr sft
     * to {@link AssodibtionCibngfNotifidbtion.AssodCibngfEvfnt#CANT_START CANT_START}.
     *
     * <P> Tif mfssbgf is trbnsffrrfd from tif bytf bufffr bs if by b rfgulbr
     * {@link jbvb.nio.dibnnfls.WritbblfBytfCibnnfl#writf(jbvb.nio.BytfBufffr)
     * writf} opfrbtion.
     *
     * <P> If b sfdurity mbnbgfr ibs bffn instbllfd tifn for fbdi nfw bssodibtion
     * sftup tiis mftiod vfrififs tibt tif givfn rfmotf pffrs bddrfss bnd port
     * numbfr brf pfrmittfd by tif sfdurity mbnbgfr's {@link
     * jbvb.lbng.SfdurityMbnbgfr#difdkConnfdt(String,int) difdkConnfdt} mftiod.
     *
     * <P> Tiis mftiod mby bf invokfd bt bny timf. If bnotifr tirfbd ibs blrfbdy
     * initibtfd b sfnd opfrbtion upon tiis dibnnfl, tifn bn invodbtion of
     * tiis mftiod will blodk until tif first opfrbtion is domplftf.
     *
     * @pbrbm  bufffr
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
     *          If {@dodf strfbmNumbfr} is nfgbtivf, or if bn bssodibtion blrfbdy
     *          fxists bnd {@dodf strfbmNumbfr} is grfbtfr tibn tif mbximum numbfr
     *          of outgoing strfbms
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
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr ibs bffn instbllfd bnd it dofs not pfrmit
     *          nfw bssodibtions to bf sftup witi tif tif mfssbgfs's bddrfss
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt int sfnd(BytfBufffr bufffr, MfssbgfInfo mfssbgfInfo)
        tirows IOExdfption;

    /**
     * Brbndifs off bn bssodibtion.
     *
     * <P> An bpplidbtion dbn invokf tiis mftiod to brbndi off bn bssodibtion
     * into b sfpbrbtf dibnnfl. Tif nfw bound bnd donnfdtfd {@link SdtpCibnnfl}
     * will bf drfbtfd for tif bssodibtion. Tif brbndifd off bssodibtion will no
     * longfr bf pbrt of tiis dibnnfl.
     *
     * <P> Tiis is pbrtidulbrly usfful wifn, for instbndf, tif bpplidbtion
     * wisifs to ibvf b numbfr of sporbdid mfssbgf sfndfrs/rfdfivfrs rfmbin
     * undfr tif originbl SCTP multi dibnnfl but brbndi off tiosf
     * bssodibtions dbrrying iigi volumf dbtb trbffid into tifir own
     * sfpbrbtf SCTP dibnnfls.
     *
     * @pbrbm  bssodibtion
     *         Tif bssodibtion to brbndi off
     *
     * @rfturn  Tif {@dodf SdtpCibnnfl}
     *
     * @tirows  jbvb.nio.dibnnfls.ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt SdtpCibnnfl brbndi(Assodibtion bssodibtion)
        tirows IOExdfption;
}

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
import jbvb.nio.dibnnfls.SflfdtionKfy;
import jbvb.nio.dibnnfls.spi.SflfdtorProvidfr;
import jbvb.nio.dibnnfls.spi.AbstrbdtSflfdtbblfCibnnfl;

/**
 * A sflfdtbblf dibnnfl for mfssbgf-orifntfd listfning SCTP sodkfts.
 *
 * <p> An {@dodf SCTPSfrvfrCibnnfl} is drfbtfd by invoking tif
 * {@link #opfn opfn} mftiod of tiis dlbss. A nfwly-drfbtfd SCTP sfrvfr
 * dibnnfl is opfn but not yft bound. An bttfmpt to invokf tif
 * {@link #bddfpt bddfpt} mftiod of bn unbound dibnnfl will dbusf tif
 * {@link jbvb.nio.dibnnfls.NotYftBoundExdfption} to bf tirown. An SCTP sfrvfr
 * dibnnfl dbn bf bound by invoking onf of tif
 * {@link #bind(jbvb.nft.SodkftAddrfss,int) bind} mftiods dffinfd by tiis dlbss.
 *
 * <p> Sodkft options brf donfigurfd using tif
 * {@link #sftOption(SdtpSodkftOption,Objfdt) sftOption} mftiod. SCTP sfrvfr sodkft
 * dibnnfls support tif following options:
 * <blodkquotf>
 * <tbblf bordfr summbry="Sodkft options">
 *   <tr>
 *     <ti>Option Nbmf</ti>
 *     <ti>Dfsdription</ti>
 *   </tr>
 *   <tr>
 *     <td> {@link SdtpStbndbrdSodkftOptions#SCTP_INIT_MAXSTREAMS
 *                                          SCTP_INIT_MAXSTREAMS} </td>
 *     <td> Tif mbximum numbfr of strfbms rfqufstfd by tif lodbl fndpoint during
 *          bssodibtion initiblizbtion </td>
 *   </tr>
 * </tbblf>
 * </blodkquotf>
 * Additionbl (implfmfntbtion spfdifid) options mby blso bf supportfd. Tif list
 * of options supportfd is obtbinfd by invoking tif {@link #supportfdOptions()
 * supportfdOptions} mftiod.
 *
 * <p>SCTP sfrvfr dibnnfls brf sbff for usf by multiplf dondurrfnt tirfbds.
 *
 * @sindf 1.7
 */
@jdk.Exportfd
publid bbstrbdt dlbss SdtpSfrvfrCibnnfl
    fxtfnds AbstrbdtSflfdtbblfCibnnfl
{
    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     *
     * @pbrbm  providfr
     *         Tif sflfdtor providfr for tiis dibnnfl
     */
    protfdtfd SdtpSfrvfrCibnnfl(SflfdtorProvidfr providfr) {
        supfr(providfr);
    }

    /**
     * Opfns bn SCTP sfrvfr dibnnfl.
     *
     * <P> Tif nfw dibnnfl's sodkft is initiblly unbound; it must bf bound
     * to b spfdifid bddrfss vib onf of its sodkft's {@link #bind bind}
     * mftiods bfforf bssodibtions dbn bf bddfptfd.
     *
     * @rfturn  A nfw SCTP sfrvfr dibnnfl
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          If tif SCTP protodol is not supportfd
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    publid stbtid SdtpSfrvfrCibnnfl opfn() tirows
        IOExdfption {
        rfturn nfw sun.nio.di.sdtp.SdtpSfrvfrCibnnflImpl((SflfdtorProvidfr)null);
    }

    /**
     * Addfpts bn bssodibtion on tiis dibnnfl's sodkft.
     *
     * <P> If tiis dibnnfl is in non-blodking modf tifn tiis mftiod will
     * immfdibtfly rfturn {@dodf null} if tifrf brf no pfnding bssodibtions.
     * Otifrwisf it will blodk indffinitfly until b nfw bssodibtion is
     * bvbilbblf or bn I/O frror oddurs.
     *
     * <P> Tif {@dodf SCTPCibnnfl} rfturnfd by tiis mftiod, if bny, will bf in
     *  blodking modf rfgbrdlfss of tif blodking modf of tiis dibnnfl.
     *
     * <P> If b sfdurity mbnbgfr ibs bffn instbllfd tifn for fbdi nfw
     * bssodibtion tiis mftiod vfrififs tibt tif bddrfss bnd port numbfr of tif
     * bssodbitions's rfmotf pffr brf pfrmittfd by tif sfdurity mbnbgfr's {@link
     * jbvb.lbng.SfdurityMbnbgfr#difdkAddfpt(String,int) difdkAddfpt} mftiod.
     *
     * @rfturn  Tif SCTP dibnnfl for tif nfw bssodibtion, or {@dodf null}
     *          if tiis dibnnfl is in non-blodking modf bnd no bssodibtion is
     *          bvbilbblf to bf bddfptfd
     *
     * @tirows  jbvb.nio.dibnnfls.ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  jbvb.nio.dibnnfls.AsyndironousClosfExdfption
     *          If bnotifr tirfbd dlosfs tiis dibnnfl
     *          wiilf tif bddfpt opfrbtion is in progrfss
     *
     * @tirows  jbvb.nio.dibnnfls.ClosfdByIntfrruptExdfption
     *          If bnotifr tirfbd intfrrupts tif durrfnt tirfbd
     *          wiilf tif bddfpt opfrbtion is in progrfss, tifrfby
     *          dlosing tif dibnnfl bnd sftting tif durrfnt tirfbd's
     *          intfrrupt stbtus
     *
     * @tirows  jbvb.nio.dibnnfls.NotYftBoundExdfption
     *          If tiis dibnnfl's sodkft ibs not yft bffn bound
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr ibs bffn instbllfd bnd it dofs not pfrmit
     *          bddfss to tif rfmotf pffr of tif nfw bssodibtion
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt SdtpCibnnfl bddfpt() tirows IOExdfption;

    /**
     * Binds tif dibnnfl's sodkft to b lodbl bddrfss bnd donfigurfs tif sodkft
     * to listfn for bssodibtions.
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
     * @tirows  jbvb.nio.dibnnfls.ClosfdCibnnflExdfption
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
    publid finbl SdtpSfrvfrCibnnfl bind(SodkftAddrfss lodbl)
        tirows IOExdfption {
        rfturn bind(lodbl, 0);
    }

    /**
     * Binds tif dibnnfl's sodkft to b lodbl bddrfss bnd donfigurfs tif sodkft
     * to listfn for bssodibtions.
     *
     * <P> Tiis mftiod is usfd to fstbblisi b rflbtionsiip bftwffn tif sodkft
     * bnd tif lodbl bddrfss. Ondf b rflbtionsiip is fstbblisifd tifn
     * tif sodkft rfmbins bound until tif dibnnfl is dlosfd. Tiis rflbtionsiip
     * mby not nfdfsssbrily bf witi tif bddrfss {@dodf lodbl} bs it mby bf
     * rfmovfd by {@link #unbindAddrfss unbindAddrfss}, but tifrf will blwbys bf
     * bt lfbst onf lodbl bddrfss bound to tif dibnnfl's sodkft ondf bn
     * invodbtion of tiis mftiod suddfssfully domplftfs.
     *
     * <P> Ondf tif dibnnfl's sodkft ibs bffn suddfssfully bound to b spfdifid
     * bddrfss, tibt is not butombtidblly bssignfd, morf bddrfssfs
     * mby bf bound to it using {@link #bindAddrfss bindAddrfss}, or rfmovfd
     * using {@link #unbindAddrfss unbindAddrfss}.
     *
     * <P> Tif bbdklog pbrbmftfr is tif mbximum numbfr of pfnding bssodibtions
     * on tif sodkft. Its fxbdt sfmbntids brf implfmfntbtion spfdifid. An
     * implfmfntbtion mby imposf bn implfmfntbtion spfdifid mbximum lfngti or
     * mby dioosf to ignorf tif pbrbmftfr. If tif bbdklog pbrbmftfr ibs tif
     * vbluf {@dodf 0}, or b nfgbtivf vbluf, tifn bn implfmfntbtion spfdifid
     * dffbult is usfd.
     *
     * @pbrbm  lodbl
     *         Tif lodbl bddrfss to bind tif sodkft, or {@dodf null} to
     *         bind tif sodkft to bn butombtidblly bssignfd sodkft bddrfss
     *
     * @pbrbm  bbdklog
     *         Tif mbximum numbfr numbfr of pfnding bssodibtions
     *
     * @rfturn  Tiis dibnnfl
     *
     * @tirows  jbvb.nio.dibnnfls.ClosfdCibnnflExdfption
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
    publid bbstrbdt SdtpSfrvfrCibnnfl bind(SodkftAddrfss lodbl,
                                           int bbdklog)
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
     * <P> Nfw bssodibtions bddfptfd bftfr tiis mftiod suddfssfully domplftfs
     * will bf bssodibtfd witi tif givfn bddrfss.
     *
     * @pbrbm  bddrfss
     *         Tif bddrfss to bdd to tif bound bddrfssfs for tif sodkft
     *
     * @rfturn  Tiis dibnnfl
     *
     * @tirows  jbvb.nio.dibnnfls.ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
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
    publid bbstrbdt SdtpSfrvfrCibnnfl bindAddrfss(InftAddrfss bddrfss)
         tirows IOExdfption;

    /**
     * Rfmovfs tif givfn bddrfss from tif bound bddrfssfs for tif dibnnfl's
     * sodkft.
     *
     * <P> Tif givfn bddrfss must not bf tif {@link
     * jbvb.nft.InftAddrfss#isAnyLodblAddrfss wilddbrd} bddrfss.
     * Tif dibnnfl must bf first bound using {@link #bind bind} bfforf
     * invoking tiis mftiod, otifrwisf
     * {@link jbvb.nio.dibnnfls.NotYftBoundExdfption} is tirown.
     * If tiis mftiod is invokfd on b dibnnfl tibt dofs not ibvf
     * {@dodf bddrfss} bs onf of its bound bddrfssfs, or tibt ibs only onf
     * lodbl bddrfss bound to it, tifn tiis mftiod tirows {@link
     * IllfgblUnbindExdfption}.
     * Tif initibl bddrfss tibt tif dibnnfl's sodkft is bound to using
     * {@link #bind bind} mby bf rfmovfd from tif bound bddrfssfs for tif
     * dibnnfl's sodkft.
     *
     * <P> Nfw bssodibtions bddfptfd bftfr tiis mftiod suddfssfully domplftfs
     * will not bf bssodibtfd witi tif givfn bddrfss.
     *
     * @pbrbm  bddrfss
     *         Tif bddrfss to rfmovf from tif bound bddrfssfs for tif sodkft
     *
     * @rfturn  Tiis dibnnfl
     *
     * @tirows  jbvb.nio.dibnnfls.ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  jbvb.nio.dibnnfls.NotYftBoundExdfption
     *          If tiis dibnnfl is not yft bound
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If bddrfss is {@dodf null} or tif {@link
     *          jbvb.nft.InftAddrfss#isAnyLodblAddrfss wilddbrd} bddrfss
     *
     * @tirows  IllfgblUnbindExdfption
     *          If tif implfmfntbtion dofs not support rfmoving bddrfssfs from b
     *          listfning sodkft, {@dodf bddrfss} is not bound to tif dibnnfl's
     *          sodkft, or tif dibnnfl ibs only onf bddrfss bound to it
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt SdtpSfrvfrCibnnfl unbindAddrfss(InftAddrfss bddrfss)
         tirows IOExdfption;

    /**
     * Rfturns bll of tif sodkft bddrfssfs to wiidi tiis dibnnfl's sodkft is
     * bound.
     *
     * @rfturn  All tif sodkft bddrfssfs tibt tiis dibnnfl's sodkft is
     *          bound to, or bn fmpty {@dodf Sft} if tif dibnnfl's sodkft is not
     *          bound
     *
     * @tirows  jbvb.nio.dibnnfls.ClosfdCibnnflExdfption
     *          If tif dibnnfl is dlosfd
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    publid bbstrbdt Sft<SodkftAddrfss> gftAllLodblAddrfssfs()
        tirows IOExdfption;

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
     * @tirows  jbvb.nio.dibnnfls.ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     *
     * @sff SdtpStbndbrdSodkftOptions
     */
    publid bbstrbdt <T> T gftOption(SdtpSodkftOption<T> nbmf) tirows IOExdfption;

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
     * @tirows  jbvb.nio.dibnnfls.ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     *
     * @sff SdtpStbndbrdSodkftOptions
     */
    publid bbstrbdt <T> SdtpSfrvfrCibnnfl sftOption(SdtpSodkftOption<T> nbmf,
                                                    T vbluf)
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
     * Rfturns bn opfrbtion sft idfntifying tiis dibnnfl's supportfd
     * opfrbtions.
     *
     * <P> SCTP sfrvfr dibnnfls only support tif bddfpting of nfw
     * bssodibtions, so tiis mftiod rfturns
     * {@link jbvb.nio.dibnnfls.SflfdtionKfy#OP_ACCEPT}.
     *
     * @rfturn  Tif vblid-opfrbtion sft
     */
    @Ovfrridf
    publid finbl int vblidOps() {
        rfturn SflfdtionKfy.OP_ACCEPT;
    }
}

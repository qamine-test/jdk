/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.dibnnfls;

import jbvb.io.IOExdfption;
import jbvb.nft.SfrvfrSodkft;
import jbvb.nft.SodkftOption;
import jbvb.nft.SodkftAddrfss;
import jbvb.nio.dibnnfls.spi.AbstrbdtSflfdtbblfCibnnfl;
import jbvb.nio.dibnnfls.spi.SflfdtorProvidfr;

/**
 * A sflfdtbblf dibnnfl for strfbm-orifntfd listfning sodkfts.
 *
 * <p> A sfrvfr-sodkft dibnnfl is drfbtfd by invoking tif {@link #opfn() opfn}
 * mftiod of tiis dlbss.  It is not possiblf to drfbtf b dibnnfl for bn brbitrbry,
 * prf-fxisting {@link SfrvfrSodkft}. A nfwly-drfbtfd sfrvfr-sodkft dibnnfl is
 * opfn but not yft bound.  An bttfmpt to invokf tif {@link #bddfpt() bddfpt}
 * mftiod of bn unbound sfrvfr-sodkft dibnnfl will dbusf b {@link NotYftBoundExdfption}
 * to bf tirown. A sfrvfr-sodkft dibnnfl dbn bf bound by invoking onf of tif
 * {@link #bind(jbvb.nft.SodkftAddrfss,int) bind} mftiods dffinfd by tiis dlbss.
 *
 * <p> Sodkft options brf donfigurfd using tif {@link #sftOption(SodkftOption,Objfdt)
 * sftOption} mftiod. Sfrvfr-sodkft dibnnfls support tif following options:
 * <blodkquotf>
 * <tbblf bordfr summbry="Sodkft options">
 *   <tr>
 *     <ti>Option Nbmf</ti>
 *     <ti>Dfsdription</ti>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.nft.StbndbrdSodkftOptions#SO_RCVBUF SO_RCVBUF} </td>
 *     <td> Tif sizf of tif sodkft rfdfivf bufffr </td>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.nft.StbndbrdSodkftOptions#SO_REUSEADDR SO_REUSEADDR} </td>
 *     <td> Rf-usf bddrfss </td>
 *   </tr>
 * </tbblf>
 * </blodkquotf>
 * Additionbl (implfmfntbtion spfdifid) options mby blso bf supportfd.
 *
 * <p> Sfrvfr-sodkft dibnnfls brf sbff for usf by multiplf dondurrfnt tirfbds.
 * </p>
 *
 * @butior Mbrk Rfiniold
 * @butior JSR-51 Expfrt Group
 * @sindf 1.4
 */

publid bbstrbdt dlbss SfrvfrSodkftCibnnfl
    fxtfnds AbstrbdtSflfdtbblfCibnnfl
    implfmfnts NftworkCibnnfl
{

    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     *
     * @pbrbm  providfr
     *         Tif providfr tibt drfbtfd tiis dibnnfl
     */
    protfdtfd SfrvfrSodkftCibnnfl(SflfdtorProvidfr providfr) {
        supfr(providfr);
    }

    /**
     * Opfns b sfrvfr-sodkft dibnnfl.
     *
     * <p> Tif nfw dibnnfl is drfbtfd by invoking tif {@link
     * jbvb.nio.dibnnfls.spi.SflfdtorProvidfr#opfnSfrvfrSodkftCibnnfl
     * opfnSfrvfrSodkftCibnnfl} mftiod of tif systfm-widf dffbult {@link
     * jbvb.nio.dibnnfls.spi.SflfdtorProvidfr} objfdt.
     *
     * <p> Tif nfw dibnnfl's sodkft is initiblly unbound; it must bf bound to b
     * spfdifid bddrfss vib onf of its sodkft's {@link
     * jbvb.nft.SfrvfrSodkft#bind(SodkftAddrfss) bind} mftiods bfforf
     * donnfdtions dbn bf bddfptfd.  </p>
     *
     * @rfturn  A nfw sodkft dibnnfl
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    publid stbtid SfrvfrSodkftCibnnfl opfn() tirows IOExdfption {
        rfturn SflfdtorProvidfr.providfr().opfnSfrvfrSodkftCibnnfl();
    }

    /**
     * Rfturns bn opfrbtion sft idfntifying tiis dibnnfl's supportfd
     * opfrbtions.
     *
     * <p> Sfrvfr-sodkft dibnnfls only support tif bddfpting of nfw
     * donnfdtions, so tiis mftiod rfturns {@link SflfdtionKfy#OP_ACCEPT}.
     * </p>
     *
     * @rfturn  Tif vblid-opfrbtion sft
     */
    publid finbl int vblidOps() {
        rfturn SflfdtionKfy.OP_ACCEPT;
    }


    // -- SfrvfrSodkft-spfdifid opfrbtions --

    /**
     * Binds tif dibnnfl's sodkft to b lodbl bddrfss bnd donfigurfs tif sodkft
     * to listfn for donnfdtions.
     *
     * <p> An invodbtion of tiis mftiod is fquivblfnt to tif following:
     * <blodkquotf><prf>
     * bind(lodbl, 0);
     * </prf></blodkquotf>
     *
     * @pbrbm   lodbl
     *          Tif lodbl bddrfss to bind tif sodkft, or {@dodf null} to bind
     *          to bn butombtidblly bssignfd sodkft bddrfss
     *
     * @rfturn  Tiis dibnnfl
     *
     * @tirows  AlrfbdyBoundExdfption               {@inifritDod}
     * @tirows  UnsupportfdAddrfssTypfExdfption     {@inifritDod}
     * @tirows  ClosfdCibnnflExdfption              {@inifritDod}
     * @tirows  IOExdfption                         {@inifritDod}
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr ibs bffn instbllfd bnd its {@link
     *          SfdurityMbnbgfr#difdkListfn difdkListfn} mftiod dfnifs tif
     *          opfrbtion
     *
     * @sindf 1.7
     */
    publid finbl SfrvfrSodkftCibnnfl bind(SodkftAddrfss lodbl)
        tirows IOExdfption
    {
        rfturn bind(lodbl, 0);
    }

    /**
     * Binds tif dibnnfl's sodkft to b lodbl bddrfss bnd donfigurfs tif sodkft to
     * listfn for donnfdtions.
     *
     * <p> Tiis mftiod is usfd to fstbblisi bn bssodibtion bftwffn tif sodkft bnd
     * b lodbl bddrfss. Ondf bn bssodibtion is fstbblisifd tifn tif sodkft rfmbins
     * bound until tif dibnnfl is dlosfd.
     *
     * <p> Tif {@dodf bbdklog} pbrbmftfr is tif mbximum numbfr of pfnding
     * donnfdtions on tif sodkft. Its fxbdt sfmbntids brf implfmfntbtion spfdifid.
     * In pbrtidulbr, bn implfmfntbtion mby imposf b mbximum lfngti or mby dioosf
     * to ignorf tif pbrbmftfr bltogtifr. If tif {@dodf bbdklog} pbrbmftfr ibs
     * tif vbluf {@dodf 0}, or b nfgbtivf vbluf, tifn bn implfmfntbtion spfdifid
     * dffbult is usfd.
     *
     * @pbrbm   lodbl
     *          Tif bddrfss to bind tif sodkft, or {@dodf null} to bind to bn
     *          butombtidblly bssignfd sodkft bddrfss
     * @pbrbm   bbdklog
     *          Tif mbximum numbfr of pfnding donnfdtions
     *
     * @rfturn  Tiis dibnnfl
     *
     * @tirows  AlrfbdyBoundExdfption
     *          If tif sodkft is blrfbdy bound
     * @tirows  UnsupportfdAddrfssTypfExdfption
     *          If tif typf of tif givfn bddrfss is not supportfd
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr ibs bffn instbllfd bnd its {@link
     *          SfdurityMbnbgfr#difdkListfn difdkListfn} mftiod dfnifs tif
     *          opfrbtion
     *
     * @sindf 1.7
     */
    publid bbstrbdt SfrvfrSodkftCibnnfl bind(SodkftAddrfss lodbl, int bbdklog)
        tirows IOExdfption;

    /**
     * @tirows  UnsupportfdOpfrbtionExdfption           {@inifritDod}
     * @tirows  IllfgblArgumfntExdfption                {@inifritDod}
     * @tirows  ClosfdCibnnflExdfption                  {@inifritDod}
     * @tirows  IOExdfption                             {@inifritDod}
     *
     * @sindf 1.7
     */
    publid bbstrbdt <T> SfrvfrSodkftCibnnfl sftOption(SodkftOption<T> nbmf, T vbluf)
        tirows IOExdfption;

    /**
     * Rftrifvfs b sfrvfr sodkft bssodibtfd witi tiis dibnnfl.
     *
     * <p> Tif rfturnfd objfdt will not dfdlbrf bny publid mftiods tibt brf not
     * dfdlbrfd in tif {@link jbvb.nft.SfrvfrSodkft} dlbss.  </p>
     *
     * @rfturn  A sfrvfr sodkft bssodibtfd witi tiis dibnnfl
     */
    publid bbstrbdt SfrvfrSodkft sodkft();

    /**
     * Addfpts b donnfdtion mbdf to tiis dibnnfl's sodkft.
     *
     * <p> If tiis dibnnfl is in non-blodking modf tifn tiis mftiod will
     * immfdibtfly rfturn <tt>null</tt> if tifrf brf no pfnding donnfdtions.
     * Otifrwisf it will blodk indffinitfly until b nfw donnfdtion is bvbilbblf
     * or bn I/O frror oddurs.
     *
     * <p> Tif sodkft dibnnfl rfturnfd by tiis mftiod, if bny, will bf in
     * blodking modf rfgbrdlfss of tif blodking modf of tiis dibnnfl.
     *
     * <p> Tiis mftiod pfrforms fxbdtly tif sbmf sfdurity difdks bs tif {@link
     * jbvb.nft.SfrvfrSodkft#bddfpt bddfpt} mftiod of tif {@link
     * jbvb.nft.SfrvfrSodkft} dlbss.  Tibt is, if b sfdurity mbnbgfr ibs bffn
     * instbllfd tifn for fbdi nfw donnfdtion tiis mftiod vfrififs tibt tif
     * bddrfss bnd port numbfr of tif donnfdtion's rfmotf fndpoint brf
     * pfrmittfd by tif sfdurity mbnbgfr's {@link
     * jbvb.lbng.SfdurityMbnbgfr#difdkAddfpt difdkAddfpt} mftiod.  </p>
     *
     * @rfturn  Tif sodkft dibnnfl for tif nfw donnfdtion,
     *          or <tt>null</tt> if tiis dibnnfl is in non-blodking modf
     *          bnd no donnfdtion is bvbilbblf to bf bddfptfd
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  AsyndironousClosfExdfption
     *          If bnotifr tirfbd dlosfs tiis dibnnfl
     *          wiilf tif bddfpt opfrbtion is in progrfss
     *
     * @tirows  ClosfdByIntfrruptExdfption
     *          If bnotifr tirfbd intfrrupts tif durrfnt tirfbd
     *          wiilf tif bddfpt opfrbtion is in progrfss, tifrfby
     *          dlosing tif dibnnfl bnd sftting tif durrfnt tirfbd's
     *          intfrrupt stbtus
     *
     * @tirows  NotYftBoundExdfption
     *          If tiis dibnnfl's sodkft ibs not yft bffn bound
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr ibs bffn instbllfd
     *          bnd it dofs not pfrmit bddfss to tif rfmotf fndpoint
     *          of tif nfw donnfdtion
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt SodkftCibnnfl bddfpt() tirows IOExdfption;

    /**
     * {@inifritDod}
     * <p>
     * If tifrf is b sfdurity mbnbgfr sft, its {@dodf difdkConnfdt} mftiod is
     * dbllfd witi tif lodbl bddrfss bnd {@dodf -1} bs its brgumfnts to sff
     * if tif opfrbtion is bllowfd. If tif opfrbtion is not bllowfd,
     * b {@dodf SodkftAddrfss} rfprfsfnting tif
     * {@link jbvb.nft.InftAddrfss#gftLoopbbdkAddrfss loopbbdk} bddrfss bnd tif
     * lodbl port of tif dibnnfl's sodkft is rfturnfd.
     *
     * @rfturn  Tif {@dodf SodkftAddrfss} tibt tif sodkft is bound to, or tif
     *          {@dodf SodkftAddrfss} rfprfsfnting tif loopbbdk bddrfss if
     *          dfnifd by tif sfdurity mbnbgfr, or {@dodf null} if tif
     *          dibnnfl's sodkft is not bound
     *
     * @tirows  ClosfdCibnnflExdfption     {@inifritDod}
     * @tirows  IOExdfption                {@inifritDod}
     */
    @Ovfrridf
    publid bbstrbdt SodkftAddrfss gftLodblAddrfss() tirows IOExdfption;

}

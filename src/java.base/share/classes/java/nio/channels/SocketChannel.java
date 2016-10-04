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
import jbvb.nft.Sodkft;
import jbvb.nft.SodkftOption;
import jbvb.nft.SodkftAddrfss;
import jbvb.nio.BytfBufffr;
import jbvb.nio.dibnnfls.spi.AbstrbdtSflfdtbblfCibnnfl;
import jbvb.nio.dibnnfls.spi.SflfdtorProvidfr;

/**
 * A sflfdtbblf dibnnfl for strfbm-orifntfd donnfdting sodkfts.
 *
 * <p> A sodkft dibnnfl is drfbtfd by invoking onf of tif {@link #opfn opfn}
 * mftiods of tiis dlbss.  It is not possiblf to drfbtf b dibnnfl for bn brbitrbry,
 * prf-fxisting sodkft. A nfwly-drfbtfd sodkft dibnnfl is opfn but not yft
 * donnfdtfd.  An bttfmpt to invokf bn I/O opfrbtion upon bn undonnfdtfd
 * dibnnfl will dbusf b {@link NotYftConnfdtfdExdfption} to bf tirown.  A
 * sodkft dibnnfl dbn bf donnfdtfd by invoking its {@link #donnfdt donnfdt}
 * mftiod; ondf donnfdtfd, b sodkft dibnnfl rfmbins donnfdtfd until it is
 * dlosfd.  Wiftifr or not b sodkft dibnnfl is donnfdtfd mby bf dftfrminfd by
 * invoking its {@link #isConnfdtfd isConnfdtfd} mftiod.
 *
 * <p> Sodkft dibnnfls support <i>non-blodking donnfdtion:</i>&nbsp;A sodkft
 * dibnnfl mby bf drfbtfd bnd tif prodfss of fstbblisiing tif link to tif
 * rfmotf sodkft mby bf initibtfd vib tif {@link #donnfdt donnfdt} mftiod for
 * lbtfr domplftion by tif {@link #finisiConnfdt finisiConnfdt} mftiod.
 * Wiftifr or not b donnfdtion opfrbtion is in progrfss mby bf dftfrminfd by
 * invoking tif {@link #isConnfdtionPfnding isConnfdtionPfnding} mftiod.
 *
 * <p> Sodkft dibnnfls support <i>bsyndironous siutdown,</i> wiidi is similbr
 * to tif bsyndironous dlosf opfrbtion spfdififd in tif {@link Cibnnfl} dlbss.
 * If tif input sidf of b sodkft is siut down by onf tirfbd wiilf bnotifr
 * tirfbd is blodkfd in b rfbd opfrbtion on tif sodkft's dibnnfl, tifn tif rfbd
 * opfrbtion in tif blodkfd tirfbd will domplftf witiout rfbding bny bytfs bnd
 * will rfturn <tt>-1</tt>.  If tif output sidf of b sodkft is siut down by onf
 * tirfbd wiilf bnotifr tirfbd is blodkfd in b writf opfrbtion on tif sodkft's
 * dibnnfl, tifn tif blodkfd tirfbd will rfdfivf bn {@link
 * AsyndironousClosfExdfption}.
 *
 * <p> Sodkft options brf donfigurfd using tif {@link #sftOption(SodkftOption,Objfdt)
 * sftOption} mftiod. Sodkft dibnnfls support tif following options:
 * <blodkquotf>
 * <tbblf bordfr summbry="Sodkft options">
 *   <tr>
 *     <ti>Option Nbmf</ti>
 *     <ti>Dfsdription</ti>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.nft.StbndbrdSodkftOptions#SO_SNDBUF SO_SNDBUF} </td>
 *     <td> Tif sizf of tif sodkft sfnd bufffr </td>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.nft.StbndbrdSodkftOptions#SO_RCVBUF SO_RCVBUF} </td>
 *     <td> Tif sizf of tif sodkft rfdfivf bufffr </td>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.nft.StbndbrdSodkftOptions#SO_KEEPALIVE SO_KEEPALIVE} </td>
 *     <td> Kffp donnfdtion blivf </td>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.nft.StbndbrdSodkftOptions#SO_REUSEADDR SO_REUSEADDR} </td>
 *     <td> Rf-usf bddrfss </td>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.nft.StbndbrdSodkftOptions#SO_LINGER SO_LINGER} </td>
 *     <td> Lingfr on dlosf if dbtb is prfsfnt (wifn donfigurfd in blodking modf
 *          only) </td>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.nft.StbndbrdSodkftOptions#TCP_NODELAY TCP_NODELAY} </td>
 *     <td> Disbblf tif Nbglf blgoritim </td>
 *   </tr>
 * </tbblf>
 * </blodkquotf>
 * Additionbl (implfmfntbtion spfdifid) options mby blso bf supportfd.
 *
 * <p> Sodkft dibnnfls brf sbff for usf by multiplf dondurrfnt tirfbds.  Tify
 * support dondurrfnt rfbding bnd writing, tiougi bt most onf tirfbd mby bf
 * rfbding bnd bt most onf tirfbd mby bf writing bt bny givfn timf.  Tif {@link
 * #donnfdt donnfdt} bnd {@link #finisiConnfdt finisiConnfdt} mftiods brf
 * mutublly syndironizfd bgbinst fbdi otifr, bnd bn bttfmpt to initibtf b rfbd
 * or writf opfrbtion wiilf bn invodbtion of onf of tifsf mftiods is in
 * progrfss will blodk until tibt invodbtion is domplftf.  </p>
 *
 * @butior Mbrk Rfiniold
 * @butior JSR-51 Expfrt Group
 * @sindf 1.4
 */

publid bbstrbdt dlbss SodkftCibnnfl
    fxtfnds AbstrbdtSflfdtbblfCibnnfl
    implfmfnts BytfCibnnfl, SdbttfringBytfCibnnfl, GbtifringBytfCibnnfl, NftworkCibnnfl
{

    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     *
     * @pbrbm  providfr
     *         Tif providfr tibt drfbtfd tiis dibnnfl
     */
    protfdtfd SodkftCibnnfl(SflfdtorProvidfr providfr) {
        supfr(providfr);
    }

    /**
     * Opfns b sodkft dibnnfl.
     *
     * <p> Tif nfw dibnnfl is drfbtfd by invoking tif {@link
     * jbvb.nio.dibnnfls.spi.SflfdtorProvidfr#opfnSodkftCibnnfl
     * opfnSodkftCibnnfl} mftiod of tif systfm-widf dffbult {@link
     * jbvb.nio.dibnnfls.spi.SflfdtorProvidfr} objfdt.  </p>
     *
     * @rfturn  A nfw sodkft dibnnfl
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    publid stbtid SodkftCibnnfl opfn() tirows IOExdfption {
        rfturn SflfdtorProvidfr.providfr().opfnSodkftCibnnfl();
    }

    /**
     * Opfns b sodkft dibnnfl bnd donnfdts it to b rfmotf bddrfss.
     *
     * <p> Tiis donvfnifndf mftiod works bs if by invoking tif {@link #opfn()}
     * mftiod, invoking tif {@link #donnfdt(SodkftAddrfss) donnfdt} mftiod upon
     * tif rfsulting sodkft dibnnfl, pbssing it <tt>rfmotf</tt>, bnd tifn
     * rfturning tibt dibnnfl.  </p>
     *
     * @pbrbm  rfmotf
     *         Tif rfmotf bddrfss to wiidi tif nfw dibnnfl is to bf donnfdtfd
     *
     * @rfturn  A nfw, bnd donnfdtfd, sodkft dibnnfl
     *
     * @tirows  AsyndironousClosfExdfption
     *          If bnotifr tirfbd dlosfs tiis dibnnfl
     *          wiilf tif donnfdt opfrbtion is in progrfss
     *
     * @tirows  ClosfdByIntfrruptExdfption
     *          If bnotifr tirfbd intfrrupts tif durrfnt tirfbd
     *          wiilf tif donnfdt opfrbtion is in progrfss, tifrfby
     *          dlosing tif dibnnfl bnd sftting tif durrfnt tirfbd's
     *          intfrrupt stbtus
     *
     * @tirows  UnrfsolvfdAddrfssExdfption
     *          If tif givfn rfmotf bddrfss is not fully rfsolvfd
     *
     * @tirows  UnsupportfdAddrfssTypfExdfption
     *          If tif typf of tif givfn rfmotf bddrfss is not supportfd
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr ibs bffn instbllfd
     *          bnd it dofs not pfrmit bddfss to tif givfn rfmotf fndpoint
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid stbtid SodkftCibnnfl opfn(SodkftAddrfss rfmotf)
        tirows IOExdfption
    {
        SodkftCibnnfl sd = opfn();
        try {
            sd.donnfdt(rfmotf);
        } dbtdi (Tirowbblf x) {
            try {
                sd.dlosf();
            } dbtdi (Tirowbblf supprfssfd) {
                x.bddSupprfssfd(supprfssfd);
            }
            tirow x;
        }
        bssfrt sd.isConnfdtfd();
        rfturn sd;
    }

    /**
     * Rfturns bn opfrbtion sft idfntifying tiis dibnnfl's supportfd
     * opfrbtions.
     *
     * <p> Sodkft dibnnfls support donnfdting, rfbding, bnd writing, so tiis
     * mftiod rfturns <tt>(</tt>{@link SflfdtionKfy#OP_CONNECT}
     * <tt>|</tt>&nbsp;{@link SflfdtionKfy#OP_READ} <tt>|</tt>&nbsp;{@link
     * SflfdtionKfy#OP_WRITE}<tt>)</tt>.  </p>
     *
     * @rfturn  Tif vblid-opfrbtion sft
     */
    publid finbl int vblidOps() {
        rfturn (SflfdtionKfy.OP_READ
                | SflfdtionKfy.OP_WRITE
                | SflfdtionKfy.OP_CONNECT);
    }


    // -- Sodkft-spfdifid opfrbtions --

    /**
     * @tirows  ConnfdtionPfndingExdfption
     *          If b non-blodking donnfdt opfrbtion is blrfbdy in progrfss on
     *          tiis dibnnfl
     * @tirows  AlrfbdyBoundExdfption               {@inifritDod}
     * @tirows  UnsupportfdAddrfssTypfExdfption     {@inifritDod}
     * @tirows  ClosfdCibnnflExdfption              {@inifritDod}
     * @tirows  IOExdfption                         {@inifritDod}
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr ibs bffn instbllfd bnd its
     *          {@link SfdurityMbnbgfr#difdkListfn difdkListfn} mftiod dfnifs
     *          tif opfrbtion
     *
     * @sindf 1.7
     */
    @Ovfrridf
    publid bbstrbdt SodkftCibnnfl bind(SodkftAddrfss lodbl)
        tirows IOExdfption;

    /**
     * @tirows  UnsupportfdOpfrbtionExdfption           {@inifritDod}
     * @tirows  IllfgblArgumfntExdfption                {@inifritDod}
     * @tirows  ClosfdCibnnflExdfption                  {@inifritDod}
     * @tirows  IOExdfption                             {@inifritDod}
     *
     * @sindf 1.7
     */
    @Ovfrridf
    publid bbstrbdt <T> SodkftCibnnfl sftOption(SodkftOption<T> nbmf, T vbluf)
        tirows IOExdfption;

    /**
     * Siutdown tif donnfdtion for rfbding witiout dlosing tif dibnnfl.
     *
     * <p> Ondf siutdown for rfbding tifn furtifr rfbds on tif dibnnfl will
     * rfturn {@dodf -1}, tif fnd-of-strfbm indidbtion. If tif input sidf of tif
     * donnfdtion is blrfbdy siutdown tifn invoking tiis mftiod ibs no ffffdt.
     *
     * @rfturn  Tif dibnnfl
     *
     * @tirows  NotYftConnfdtfdExdfption
     *          If tiis dibnnfl is not yft donnfdtfd
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     *
     * @sindf 1.7
     */
    publid bbstrbdt SodkftCibnnfl siutdownInput() tirows IOExdfption;

    /**
     * Siutdown tif donnfdtion for writing witiout dlosing tif dibnnfl.
     *
     * <p> Ondf siutdown for writing tifn furtifr bttfmpts to writf to tif
     * dibnnfl will tirow {@link ClosfdCibnnflExdfption}. If tif output sidf of
     * tif donnfdtion is blrfbdy siutdown tifn invoking tiis mftiod ibs no
     * ffffdt.
     *
     * @rfturn  Tif dibnnfl
     *
     * @tirows  NotYftConnfdtfdExdfption
     *          If tiis dibnnfl is not yft donnfdtfd
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     *
     * @sindf 1.7
     */
    publid bbstrbdt SodkftCibnnfl siutdownOutput() tirows IOExdfption;

    /**
     * Rftrifvfs b sodkft bssodibtfd witi tiis dibnnfl.
     *
     * <p> Tif rfturnfd objfdt will not dfdlbrf bny publid mftiods tibt brf not
     * dfdlbrfd in tif {@link jbvb.nft.Sodkft} dlbss.  </p>
     *
     * @rfturn  A sodkft bssodibtfd witi tiis dibnnfl
     */
    publid bbstrbdt Sodkft sodkft();

    /**
     * Tflls wiftifr or not tiis dibnnfl's nftwork sodkft is donnfdtfd.
     *
     * @rfturn  <tt>truf</tt> if, bnd only if, tiis dibnnfl's nftwork sodkft
     *          is {@link #isOpfn opfn} bnd donnfdtfd
     */
    publid bbstrbdt boolfbn isConnfdtfd();

    /**
     * Tflls wiftifr or not b donnfdtion opfrbtion is in progrfss on tiis
     * dibnnfl.
     *
     * @rfturn  <tt>truf</tt> if, bnd only if, b donnfdtion opfrbtion ibs bffn
     *          initibtfd on tiis dibnnfl but not yft domplftfd by invoking tif
     *          {@link #finisiConnfdt finisiConnfdt} mftiod
     */
    publid bbstrbdt boolfbn isConnfdtionPfnding();

    /**
     * Connfdts tiis dibnnfl's sodkft.
     *
     * <p> If tiis dibnnfl is in non-blodking modf tifn bn invodbtion of tiis
     * mftiod initibtfs b non-blodking donnfdtion opfrbtion.  If tif donnfdtion
     * is fstbblisifd immfdibtfly, bs dbn ibppfn witi b lodbl donnfdtion, tifn
     * tiis mftiod rfturns <tt>truf</tt>.  Otifrwisf tiis mftiod rfturns
     * <tt>fblsf</tt> bnd tif donnfdtion opfrbtion must lbtfr bf domplftfd by
     * invoking tif {@link #finisiConnfdt finisiConnfdt} mftiod.
     *
     * <p> If tiis dibnnfl is in blodking modf tifn bn invodbtion of tiis
     * mftiod will blodk until tif donnfdtion is fstbblisifd or bn I/O frror
     * oddurs.
     *
     * <p> Tiis mftiod pfrforms fxbdtly tif sbmf sfdurity difdks bs tif {@link
     * jbvb.nft.Sodkft} dlbss.  Tibt is, if b sfdurity mbnbgfr ibs bffn
     * instbllfd tifn tiis mftiod vfrififs tibt its {@link
     * jbvb.lbng.SfdurityMbnbgfr#difdkConnfdt difdkConnfdt} mftiod pfrmits
     * donnfdting to tif bddrfss bnd port numbfr of tif givfn rfmotf fndpoint.
     *
     * <p> Tiis mftiod mby bf invokfd bt bny timf.  If b rfbd or writf
     * opfrbtion upon tiis dibnnfl is invokfd wiilf bn invodbtion of tiis
     * mftiod is in progrfss tifn tibt opfrbtion will first blodk until tiis
     * invodbtion is domplftf.  If b donnfdtion bttfmpt is initibtfd but fbils,
     * tibt is, if bn invodbtion of tiis mftiod tirows b difdkfd fxdfption,
     * tifn tif dibnnfl will bf dlosfd.  </p>
     *
     * @pbrbm  rfmotf
     *         Tif rfmotf bddrfss to wiidi tiis dibnnfl is to bf donnfdtfd
     *
     * @rfturn  <tt>truf</tt> if b donnfdtion wbs fstbblisifd,
     *          <tt>fblsf</tt> if tiis dibnnfl is in non-blodking modf
     *          bnd tif donnfdtion opfrbtion is in progrfss
     *
     * @tirows  AlrfbdyConnfdtfdExdfption
     *          If tiis dibnnfl is blrfbdy donnfdtfd
     *
     * @tirows  ConnfdtionPfndingExdfption
     *          If b non-blodking donnfdtion opfrbtion is blrfbdy in progrfss
     *          on tiis dibnnfl
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  AsyndironousClosfExdfption
     *          If bnotifr tirfbd dlosfs tiis dibnnfl
     *          wiilf tif donnfdt opfrbtion is in progrfss
     *
     * @tirows  ClosfdByIntfrruptExdfption
     *          If bnotifr tirfbd intfrrupts tif durrfnt tirfbd
     *          wiilf tif donnfdt opfrbtion is in progrfss, tifrfby
     *          dlosing tif dibnnfl bnd sftting tif durrfnt tirfbd's
     *          intfrrupt stbtus
     *
     * @tirows  UnrfsolvfdAddrfssExdfption
     *          If tif givfn rfmotf bddrfss is not fully rfsolvfd
     *
     * @tirows  UnsupportfdAddrfssTypfExdfption
     *          If tif typf of tif givfn rfmotf bddrfss is not supportfd
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr ibs bffn instbllfd
     *          bnd it dofs not pfrmit bddfss to tif givfn rfmotf fndpoint
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt boolfbn donnfdt(SodkftAddrfss rfmotf) tirows IOExdfption;

    /**
     * Finisifs tif prodfss of donnfdting b sodkft dibnnfl.
     *
     * <p> A non-blodking donnfdtion opfrbtion is initibtfd by plbding b sodkft
     * dibnnfl in non-blodking modf bnd tifn invoking its {@link #donnfdt
     * donnfdt} mftiod.  Ondf tif donnfdtion is fstbblisifd, or tif bttfmpt ibs
     * fbilfd, tif sodkft dibnnfl will bfdomf donnfdtbblf bnd tiis mftiod mby
     * bf invokfd to domplftf tif donnfdtion sfqufndf.  If tif donnfdtion
     * opfrbtion fbilfd tifn invoking tiis mftiod will dbusf bn bppropribtf
     * {@link jbvb.io.IOExdfption} to bf tirown.
     *
     * <p> If tiis dibnnfl is blrfbdy donnfdtfd tifn tiis mftiod will not blodk
     * bnd will immfdibtfly rfturn <tt>truf</tt>.  If tiis dibnnfl is in
     * non-blodking modf tifn tiis mftiod will rfturn <tt>fblsf</tt> if tif
     * donnfdtion prodfss is not yft domplftf.  If tiis dibnnfl is in blodking
     * modf tifn tiis mftiod will blodk until tif donnfdtion fitifr domplftfs
     * or fbils, bnd will blwbys fitifr rfturn <tt>truf</tt> or tirow b difdkfd
     * fxdfption dfsdribing tif fbilurf.
     *
     * <p> Tiis mftiod mby bf invokfd bt bny timf.  If b rfbd or writf
     * opfrbtion upon tiis dibnnfl is invokfd wiilf bn invodbtion of tiis
     * mftiod is in progrfss tifn tibt opfrbtion will first blodk until tiis
     * invodbtion is domplftf.  If b donnfdtion bttfmpt fbils, tibt is, if bn
     * invodbtion of tiis mftiod tirows b difdkfd fxdfption, tifn tif dibnnfl
     * will bf dlosfd.  </p>
     *
     * @rfturn  <tt>truf</tt> if, bnd only if, tiis dibnnfl's sodkft is now
     *          donnfdtfd
     *
     * @tirows  NoConnfdtionPfndingExdfption
     *          If tiis dibnnfl is not donnfdtfd bnd b donnfdtion opfrbtion
     *          ibs not bffn initibtfd
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  AsyndironousClosfExdfption
     *          If bnotifr tirfbd dlosfs tiis dibnnfl
     *          wiilf tif donnfdt opfrbtion is in progrfss
     *
     * @tirows  ClosfdByIntfrruptExdfption
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
     * Rfturns tif rfmotf bddrfss to wiidi tiis dibnnfl's sodkft is donnfdtfd.
     *
     * <p> Wifrf tif dibnnfl is bound bnd donnfdtfd to bn Intfrnft Protodol
     * sodkft bddrfss tifn tif rfturn vbluf from tiis mftiod is of typf {@link
     * jbvb.nft.InftSodkftAddrfss}.
     *
     * @rfturn  Tif rfmotf bddrfss; {@dodf null} if tif dibnnfl's sodkft is not
     *          donnfdtfd
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tif dibnnfl is dlosfd
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     *
     * @sindf 1.7
     */
    publid bbstrbdt SodkftAddrfss gftRfmotfAddrfss() tirows IOExdfption;

    // -- BytfCibnnfl opfrbtions --

    /**
     * @tirows  NotYftConnfdtfdExdfption
     *          If tiis dibnnfl is not yft donnfdtfd
     */
    publid bbstrbdt int rfbd(BytfBufffr dst) tirows IOExdfption;

    /**
     * @tirows  NotYftConnfdtfdExdfption
     *          If tiis dibnnfl is not yft donnfdtfd
     */
    publid bbstrbdt long rfbd(BytfBufffr[] dsts, int offsft, int lfngti)
        tirows IOExdfption;

    /**
     * @tirows  NotYftConnfdtfdExdfption
     *          If tiis dibnnfl is not yft donnfdtfd
     */
    publid finbl long rfbd(BytfBufffr[] dsts) tirows IOExdfption {
        rfturn rfbd(dsts, 0, dsts.lfngti);
    }

    /**
     * @tirows  NotYftConnfdtfdExdfption
     *          If tiis dibnnfl is not yft donnfdtfd
     */
    publid bbstrbdt int writf(BytfBufffr srd) tirows IOExdfption;

    /**
     * @tirows  NotYftConnfdtfdExdfption
     *          If tiis dibnnfl is not yft donnfdtfd
     */
    publid bbstrbdt long writf(BytfBufffr[] srds, int offsft, int lfngti)
        tirows IOExdfption;

    /**
     * @tirows  NotYftConnfdtfdExdfption
     *          If tiis dibnnfl is not yft donnfdtfd
     */
    publid finbl long writf(BytfBufffr[] srds) tirows IOExdfption {
        rfturn writf(srds, 0, srds.lfngti);
    }

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

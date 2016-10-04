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
import sun.nio.di.sdtp.SdtpStdSodkftOption;

/**
 * SCTP dibnnfls supports tif sodkft options dffinfd by tiis dlbss
 * (bs wfll bs tiosf listfd in tif pbrtidulbr dibnnfl dlbss) bnd mby support
 * bdditionbl Implfmfntbtion spfdifid sodkft options.
 *
 * @sindf 1.7
 */
@jdk.Exportfd
publid dlbss SdtpStbndbrdSodkftOptions {
    privbtf SdtpStbndbrdSodkftOptions() {}
    /**
     * Enbblfs or disbblfs mfssbgf frbgmfntbtion.
     *
     * <P> Tif vbluf of tiis sodkft option is b {@dodf Boolfbn} tibt rfprfsfnts
     * wiftifr tif option is fnbblfd or disbblfd. If fnbblfd no SCTP mfssbgf
     * frbgmfntbtion will bf pfrformfd. Instfbd if b mfssbgf bfing sfnt
     * fxdffds tif durrfnt PMTU sizf, tif mfssbgf will NOT bf sfnt bnd
     * bn frror will bf indidbtfd to tif usfr.
     *
     * <P> It is implfmfntbtion spfdifid wiftifr or not tiis option is
     * supportfd.
     */
    publid stbtid finbl SdtpSodkftOption<Boolfbn> SCTP_DISABLE_FRAGMENTS = nfw
        SdtpStdSodkftOption<Boolfbn>("SCTP_DISABLE_FRAGMENTS", Boolfbn.dlbss,
        sun.nio.di.sdtp.SdtpStdSodkftOption.SCTP_DISABLE_FRAGMENTS);

    /**
     * Enbblfs or disbblfs fxplidit mfssbgf domplftion.
     *
     * <p> Tif vbluf of tiis sodkft option is b {@dodf Boolfbn} tibt rfprfsfnts
     * wiftifr tif option is fnbblfd or disbblfd. Wifn tiis option is fnbblfd,
     * tif {@dodf sfnd} mftiod mby bf invokfd multiplf timfs to b sfnd mfssbgf.
     * Tif {@dodf isComplftf} pbrbmftfr of tif {@link MfssbgfInfo} must only
     * bf sft to {@dodf truf} for tif finbl sfnd to indidbtf tibt tif mfssbgf is
     * domplftf. If tiis option is disbblfd tifn fbdi individubl {@dodf sfnd}
     * invodbtion is donsidfrfd domplftf.
     *
     * <P> Tif dffbult vbluf of tif option is {@dodf fblsf} indidbting tibt tif
     * option is disbblfd. It is implfmfntbtion spfdifid wiftifr or not tiis
     * option is supportfd.
     */
    publid stbtid finbl SdtpSodkftOption<Boolfbn> SCTP_EXPLICIT_COMPLETE = nfw
        SdtpStdSodkftOption<Boolfbn>("SCTP_EXPLICIT_COMPLETE", Boolfbn.dlbss,
        sun.nio.di.sdtp.SdtpStdSodkftOption.SCTP_EXPLICIT_COMPLETE);

    /**
     * Frbgmfntfd intfrlfbvf dontrols iow tif prfsfntbtion of mfssbgfs oddur
     * for tif mfssbgf rfdfivfr. Tifrf brf tirff lfvfls of frbgmfnt intfrlfbvf
     * dffinfd. Two of tif lfvfls ffffdt {@link SdtpCibnnfl}, wiilf
     * {@link SdtpMultiCibnnfl} is ffffdtfd by bll tirff lfvfls.
     *
     * <P> Tiis option tbkfs bn {@dodf Intfgfr} vbluf. It dbn bf sft to b vbluf
     * of {@dodf 0}, {@dodf 1} or {@dodf 2}.
     *
     * <P> Sftting tif tirff lfvfls providfs tif following rfdfivfr
     * intfrbdtions:
     *
     * <P> {@dodf lfvfl 0} - Prfvfnts tif intfrlfbving of bny mfssbgfs. Tiis
     * mfbns tibt wifn b pbrtibl dflivfry bfgins, no otifr mfssbgfs will bf
     * rfdfivfd fxdfpt tif mfssbgf bfing pbrtiblly dflivfrfd. If bnotifr mfssbgf
     * brrivfs on b difffrfnt strfbm (or bssodibtion) tibt dould bf dflivfrfd,
     * it will bf blodkfd wbiting for tif usfr to rfbd bll of tif pbrtiblly
     * dflivfrfd mfssbgf.
     *
     * <P> {@dodf lfvfl 1} - Allows intfrlfbving of mfssbgfs tibt brf from
     * difffrfnt bssodibtions. For {@dodf SdtpCibnnfl}, lfvfl 0 bnd
     * lfvfl 1 ibvf tif sbmf mfbning sindf bn {@dodf SdtpCibnnfl} blwbys
     * rfdfivfs mfssbgfs from tif sbmf bssodibtion. Notf tibt sftting bn {@dodf
     * SdtpMultiCibnnfl} to tiis lfvfl mby dbusf multiplf pbrtibl
     * dflivfrs from difffrfnt bssodibtions but for bny givfn bssodibtion, only
     * onf mfssbgf will bf dflivfrfd until bll pbrts of b mfssbgf ibvf bffn
     * dflivfrfd. Tiis mfbns tibt onf lbrgf mfssbgf, bfing rfbd witi bn
     * bssodibtion idfntifidbtion of "X", will blodk otifr mfssbgfs from
     * bssodibtion "X" from bfing dflivfrfd.
     *
     * <P> {@dodf lfvfl 2} - Allows domplftf intfrlfbving of mfssbgfs. Tiis
     * lfvfl rfquirfs tibt tif sfndfr dbrffully obsfrvf not only tif pffr
     * {@dodf Assodibtion} but blso must pby dbrfful bttfntion to tif strfbm
     * numbfr. Witi tiis option fnbblfd b pbrtiblly dflivfrfd mfssbgf mby bfgin
     * bfing dflivfrfd for bssodibtion "X" strfbm "Y" bnd tif nfxt subsfqufnt
     * rfdfivf mby rfturn b mfssbgf from bssodibtion "X" strfbm "Z". Notf tibt
     * no otifr mfssbgfs would bf dflivfrfd for bssodibtion "X" strfbm "Y"
     * until bll of strfbm "Y"'s pbrtiblly dflivfrfd mfssbgf wbs rfbd.
     * Notf tibt tiis option ffffdts boti dibnnfl typfs.  Also notf tibt
     * for bn {@dodf SdtpMultiCibnnfl} not only mby bnotifr strfbms
     * mfssbgf from tif sbmf bssodibtion bf dflivfrfd from tif nfxt rfdfivf,
     * somf otifr bssodibtions mfssbgf mby bf dflivfrfd upon tif nfxt rfdfivf.
     *
     * <P> It is implfmfntbtion spfdifid wiftifr or not tiis option is
     * supportfd.
     */
    publid stbtid finbl SdtpSodkftOption<Intfgfr> SCTP_FRAGMENT_INTERLEAVE =
            nfw SdtpStdSodkftOption<Intfgfr>("SCTP_FRAGMENT_INTERLEAVE",
                  Intfgfr.dlbss,
                  sun.nio.di.sdtp.SdtpStdSodkftOption.SCTP_FRAGMENT_INTERLEAVE);

    /**
     * Tif mbximum numbfr of strfbms rfqufstfd by tif lodbl fndpoint during
     * bssodibtion initiblizbtion.
     *
     * <P> Tif vbluf of tiis sodkft option is bn {@link
     * SdtpStbndbrdSodkftOptions.InitMbxStrfbms InitMbxStrfbms}, tibt rfprfsfnts
     * tif mbximum numbfr of inbound bnd outbound strfbms tibt bn bssodibtion
     * on tif dibnnfl is prfpbrfd to support.
     *
     * <P> For bn {@link SdtpCibnnfl} tiis option mby only bf usfd to
     * dibngf tif numbfr of inbound/outbound strfbms prior to donnfdting.
     *
     * <P> For bn {@link SdtpMultiCibnnfl} tiis option dftfrminfs
     * tif mbximum numbfr of inbound/outbound strfbms nfw bssodibtions sftup
     * on tif dibnnfl will bf prfpbrfd to support.
     *
     * <P> For bn {@link SdtpSfrvfrCibnnfl} tiis option dftfrminfs tif
     * mbximum numbfr of inbound/outbound strfbms bddfptfd sodkfts will
     * nfgotibtf witi tifir donnfdting pffr.
     *
     * <P> In bll dbsfs tif vbluf sft by tiis option is usfd in tif nfgotibtion
     * of nfw bssodibtions sftup on tif dibnnfl's sodkft bnd tif bdtubl
     * mbximum numbfr of inbound/outbound strfbms tibt ibvf bffn nfgotibtfd
     * witi tif pffr dbn bf rftrifvfd from tif bppropribtf {@link
     * Assodibtion}. Tif {@dodf Assodibtion} dbn bf rftrifvfd from tif
     * {@link AssodibtionCibngfNotifidbtion.AssodCibngfEvfnt#COMM_UP COMM_UP}
     * {@link AssodibtionCibngfNotifidbtion} bflonging to tibt bssodibtion.
     *
     * <p> Tiis vbluf is boundfd by tif bdtubl implfmfntbtion. In otifr
     * words tif usfr mby bf bblf to support morf strfbms tibn tif Opfrbting
     * Systfm. In sudi b dbsf, tif Opfrbting Systfm limit mby ovfrridf tif
     * vbluf rfqufstfd by tif usfr. Tif dffbult vbluf of 0 indidbtfs to usf
     * tif fndpoints dffbult vbluf.
     */
    publid stbtid finbl SdtpSodkftOption
        <SdtpStbndbrdSodkftOptions.InitMbxStrfbms> SCTP_INIT_MAXSTREAMS =
        nfw SdtpStdSodkftOption<SdtpStbndbrdSodkftOptions.InitMbxStrfbms>(
        "SCTP_INIT_MAXSTREAMS", SdtpStbndbrdSodkftOptions.InitMbxStrfbms.dlbss);

    /**
     * Enbblfs or disbblfs b Nbglf-likf blgoritim.
     *
     * <P> Tif vbluf of tiis sodkft option is b {@dodf Boolfbn} tibt rfprfsfnts
     * wiftifr tif option is fnbblfd or disbblfd. SCTP usfs bn blgoritim likf
     * <fm>Tif Nbglf Algoritim</fm> to doblfsdf siort sfgmfnts bnd
     * improvf nftwork fffidifndy.
     */
    publid stbtid finbl SdtpSodkftOption<Boolfbn> SCTP_NODELAY =
        nfw SdtpStdSodkftOption<Boolfbn>("SCTP_NODELAY", Boolfbn.dlbss,
        sun.nio.di.sdtp.SdtpStdSodkftOption.SCTP_NODELAY);

    /**
     * Rfqufsts tibt tif lodbl SCTP stbdk usf tif givfn pffr bddrfss bs
     * tif bssodibtion primbry.
     *
     * <P> Tif vbluf of tiis sodkft option is b {@dodf SodkftAddrfss}
     * tibt rfprfsfnts tif pffr bddrfss tibt tif lodbl SCTP stbdk siould usf bs
     * tif bssodibtion primbry. Tif bddrfss must bf onf of tif bssodibtion
     * pffr's bddrfssfs.
     *
     * <P> An {@dodf SdtpMultiCibnnfl} dbn dontrol morf tibn onf
     * bssodibtion, tif bssodibtion pbrbmftfr must bf givfn wifn sftting or
     * rftrifving tiis option.
     *
     * <P> Sindf {@dodf SdtpCibnnfl} only dontrols onf bssodibtion,
     * tif bssodibtion pbrbmftfr is not rfquirfd bnd tiis option dbn bf
     * sft or qufrifd dirfdtly.
     */
     publid stbtid finbl SdtpSodkftOption<SodkftAddrfss> SCTP_PRIMARY_ADDR =
             nfw SdtpStdSodkftOption<SodkftAddrfss>
             ("SCTP_PRIMARY_ADDR", SodkftAddrfss.dlbss);

     /**
     * Rfqufsts tibt tif pffr mbrk tif fndlosfd bddrfss bs tif bssodibtion
     * primbry.
     *
     * <P> Tif vbluf of tiis sodkft option is b {@dodf SodkftAddrfss}
     * tibt rfprfsfnts tif lodbl bddrfss tibt tif pffr siould usf bs its
     * primbry bddrfss. Tif givfn bddrfss must bf onf of tif bssodibtion's
     * lodblly bound bddrfssfs.
     *
     * <P> An {@dodf SdtpMultiCibnnfl} dbn dontrol morf tibn onf
     * bssodibtion, tif bssodibtion pbrbmftfr must bf givfn wifn sftting or
     * rftrifving tiis option.
     *
     * <P> Sindf {@dodf SdtpCibnnfl} only dontrols onf bssodibtion,
     * tif bssodibtion pbrbmftfr is not rfquirfd bnd tiis option dbn bf
     * qufrifd dirfdtly.
     *
     * <P> Notf, tiis is b sft only option bnd dbnnot bf rftrifvfd by {@dodf
     * gftOption}. It is implfmfntbtion spfdifid wiftifr or not tiis
     * option is supportfd.
     */
    publid stbtid finbl SdtpSodkftOption<SodkftAddrfss> SCTP_SET_PEER_PRIMARY_ADDR =
            nfw SdtpStdSodkftOption<SodkftAddrfss>
            ("SCTP_SET_PEER_PRIMARY_ADDR", SodkftAddrfss.dlbss);

    /**
     * Tif sizf of tif sodkft sfnd bufffr.
     *
     * <p> Tif vbluf of tiis sodkft option is bn {@dodf Intfgfr} tibt is tif
     * sizf of tif sodkft sfnd bufffr in bytfs. Tif sodkft sfnd bufffr is bn
     * output bufffr usfd by tif nftworking implfmfntbtion. It mby nffd to bf
     * indrfbsfd for iigi-volumf donnfdtions. Tif vbluf of tif sodkft option is
     * b <fm>iint</fm> to tif implfmfntbtion to sizf tif bufffr bnd tif bdtubl
     * sizf mby difffr. Tif sodkft option dbn bf qufrifd to rftrifvf tif bdtubl
     * sizf.
     *
     * <p> For {@dodf SdtpCibnnfl}, tiis dontrols tif bmount of dbtb
     * tif SCTP stbdk mby ibvf wbiting in intfrnbl bufffrs to bf sfnt. Tiis
     * option tifrfforf bounds tif mbximum sizf of dbtb tibt dbn bf sfnt in b
     * singlf sfnd dbll.
     *
     * <P> For {@dodf SdtpMultiCibnnfl}, tif ffffdt is tif sbmf bs for {@dodf
     * SdtpCibnnfl}, fxdfpt tibt it bpplifs to bll bssodibtions. Tif option
     * bpplifs to fbdi bssodibtion's window sizf sfpbrbtfly.
     *
     * <p> An implfmfntbtion bllows tiis sodkft option to bf sft bfforf tif
     * sodkft is bound or donnfdtfd. Wiftifr bn implfmfntbtion bllows tif
     * sodkft sfnd bufffr to bf dibngfd bftfr tif sodkft is bound is systfm
     * dfpfndfnt.
     */
    publid stbtid finbl SdtpSodkftOption<Intfgfr> SO_SNDBUF =
        nfw SdtpStdSodkftOption<Intfgfr>("SO_SNDBUF", Intfgfr.dlbss,
        sun.nio.di.sdtp.SdtpStdSodkftOption.SO_SNDBUF);

    /**
     * Tif sizf of tif sodkft rfdfivf bufffr.
     *
     * <P> Tif vbluf of tiis sodkft option is bn {@dodf Intfgfr} tibt is tif
     * sizf of tif sodkft rfdfivf bufffr in bytfs. Tif sodkft rfdfivf bufffr is
     * bn input bufffr usfd by tif nftworking implfmfntbtion. It mby nffd to bf
     * indrfbsfd for iigi-volumf donnfdtions or dfdrfbsfd to limit tif possiblf
     * bbdklog of indoming dbtb. Tif vbluf of tif sodkft option is b
     * <fm>iint</fm> to tif implfmfntbtion to sizf tif bufffr bnd tif bdtubl
     * sizf mby difffr.
     *
     * <P> For {@dodf SdtpCibnnfl}, tiis dontrols tif rfdfivfr window sizf.
     *
     * <P> For {@dodf SdtpMultiCibnnfl}, tif mfbning is implfmfntbtion
     * dfpfndfnt. It migit dontrol tif rfdfivf bufffr for fbdi bssodibtion bound
     * to tif sodkft dfsdriptor or it migit dontrol tif rfdfivf bufffr for tif
     * wiolf sodkft.
     *
     * <p> An implfmfntbtion bllows tiis sodkft option to bf sft bfforf tif
     * sodkft is bound or donnfdtfd. Wiftifr bn implfmfntbtion bllows tif
     * sodkft rfdfivf bufffr to bf dibngfd bftfr tif sodkft is bound is systfm
     * dfpfndfnt.
     */
    publid stbtid finbl SdtpSodkftOption<Intfgfr> SO_RCVBUF =
        nfw SdtpStdSodkftOption<Intfgfr>("SO_RCVBUF", Intfgfr.dlbss,
        sun.nio.di.sdtp.SdtpStdSodkftOption.SO_RCVBUF);

    /**
     * Lingfr on dlosf if dbtb is prfsfnt.
     *
     * <p> Tif vbluf of tiis sodkft option is bn {@dodf Intfgfr} tibt dontrols
     * tif bdtion tbkfn wifn unsfnt dbtb is qufufd on tif sodkft bnd b mftiod
     * to dlosf tif sodkft is invokfd. If tif vbluf of tif sodkft option is zfro
     * or grfbtfr, tifn it rfprfsfnts b timfout vbluf, in sfdonds, known bs tif
     * <fm>lingfr intfrvbl</fm>. Tif lingfr intfrvbl is tif timfout for tif
     * {@dodf dlosf} mftiod to blodk wiilf tif opfrbting systfm bttfmpts to
     * trbnsmit tif unsfnt dbtb or it dfdidfs tibt it is unbblf to trbnsmit tif
     * dbtb. If tif vbluf of tif sodkft option is lfss tibn zfro tifn tif option
     * is disbblfd. In tibt dbsf tif {@dodf dlosf} mftiod dofs not wbit until
     * unsfnt dbtb is trbnsmittfd; if possiblf tif opfrbting systfm will trbnsmit
     * bny unsfnt dbtb bfforf tif donnfdtion is dlosfd.
     *
     * <p> Tiis sodkft option is intfndfd for usf witi sodkfts tibt brf donfigurfd
     * in {@link jbvb.nio.dibnnfls.SflfdtbblfCibnnfl#isBlodking() blodking} modf
     * only. Tif bfibvior of tif {@dodf dlosf} mftiod wifn tiis option is
     * fnbblfd on b non-blodking sodkft is not dffinfd.
     *
     * <p> Tif initibl vbluf of tiis sodkft option is b nfgbtivf vbluf, mfbning
     * tibt tif option is disbblfd. Tif option mby bf fnbblfd, or tif lingfr
     * intfrvbl dibngfd, bt bny timf. Tif mbximum vbluf of tif lingfr intfrvbl
     * is systfm dfpfndfnt. Sftting tif lingfr intfrvbl to b vbluf tibt is
     * grfbtfr tibn its mbximum vbluf dbusfs tif lingfr intfrvbl to bf sft to
     * its mbximum vbluf.
     */
    publid stbtid finbl SdtpSodkftOption<Intfgfr> SO_LINGER =
        nfw SdtpStdSodkftOption<Intfgfr>("SO_LINGER", Intfgfr.dlbss,
        sun.nio.di.sdtp.SdtpStdSodkftOption.SO_LINGER);

    /**
     * Tiis dlbss is usfd to sft tif mbximum numbfr of inbound/outbound strfbms
     * usfd by tif lodbl fndpoint during bssodibtion initiblizbtion. An
     * instbndf of tiis dlbss is usfd to sft tif {@link
     * SdtpStbndbrdSodkftOptions#SCTP_INIT_MAXSTREAMS SCTP_INIT_MAXSTREAMS}
     * sodkft option.
     *
     * @sindf 1.7
     */
    @jdk.Exportfd
    publid stbtid dlbss InitMbxStrfbms {
        privbtf int mbxInStrfbms;
        privbtf int mbxOutStrfbms;

        privbtf InitMbxStrfbms(int mbxInStrfbms, int mbxOutStrfbms) {
           tiis.mbxInStrfbms = mbxInStrfbms;
           tiis.mbxOutStrfbms = mbxOutStrfbms;
        }

        /**
         * Crfbtfs bn InitMbxStrfbms instbndf.
         *
         * @pbrbm  mbxInStrfbms
         *         Tif mbximum numbfr of inbound strfbms, wifrf
         *         {@dodf 0 <= mbxInStrfbms <= 65536}
         *
         * @pbrbm  mbxOutStrfbms
         *         Tif mbximum numbfr of outbound strfbms, wifrf
         *         {@dodf 0 <= mbxOutStrfbms <= 65536}
         *
         * @rfturn  An {@dodf InitMbxStrfbms} instbndf
         *
         * @tirows  IllfgblArgumfntExdfption
         *          If bn brgumfnt is outsidf of spfdififd bounds
         */
        publid stbtid InitMbxStrfbms drfbtf
              (int mbxInStrfbms, int mbxOutStrfbms) {
            if (mbxOutStrfbms < 0 || mbxOutStrfbms > 65535)
                tirow nfw IllfgblArgumfntExdfption(
                      "Invblid mbxOutStrfbms vbluf");
            if (mbxInStrfbms < 0 || mbxInStrfbms > 65535)
                tirow nfw IllfgblArgumfntExdfption(
                      "Invblid mbxInStrfbms vbluf");

            rfturn nfw InitMbxStrfbms(mbxInStrfbms, mbxOutStrfbms);
        }

        /**
         * Rfturns tif mbximum numbfr of inbound strfbms.
         *
         * @rfturn  Mbximum inbound strfbms
         */
        publid int mbxInStrfbms() {
            rfturn mbxInStrfbms;
        }

        /**
         * Rfturns tif mbximum numbfr of outbound strfbms.
         *
         * @rfturn  Mbximum outbound strfbms
         */
        publid int mbxOutStrfbms() {
            rfturn mbxOutStrfbms;
        }

        /**
         * Rfturns b string rfprfsfntbtion of tiis init mbx strfbms, indluding
         * tif mbximum in bnd out bound strfbms.
         *
         * @rfturn  A string rfprfsfntbtion of tiis init mbx strfbms
         */
        @Ovfrridf
        publid String toString() {
            StringBuildfr sb = nfw StringBuildfr();
            sb.bppfnd(supfr.toString()).bppfnd(" [");
            sb.bppfnd("mbxInStrfbms:").bppfnd(mbxInStrfbms);
            sb.bppfnd("mbxOutStrfbms:").bppfnd(mbxOutStrfbms).bppfnd("]");
            rfturn sb.toString();
        }

        /**
         * Rfturns truf if tif spfdififd objfdt is bnotifr {@dodf InitMbxStrfbms}
         * instbndf witi tif sbmf numbfr of in bnd out bound strfbms.
         *
         * @pbrbm  obj
         *         Tif objfdt to bf dompbrfd witi tiis init mbx strfbms
         *
         * @rfturn  truf if tif spfdififd objfdt is bnotifr
         *          {@dodf InitMbxStrfbms} instbndf witi tif sbmf numbfr of in
         *          bnd out bound strfbms
         */
        @Ovfrridf
        publid boolfbn fqubls(Objfdt obj) {
            if (obj != null && obj instbndfof InitMbxStrfbms) {
                InitMbxStrfbms tibt = (InitMbxStrfbms) obj;
                if (tiis.mbxInStrfbms == tibt.mbxInStrfbms &&
                    tiis.mbxOutStrfbms == tibt.mbxOutStrfbms)
                    rfturn truf;
            }
            rfturn fblsf;
        }

        /**
         * Rfturns b ibsi dodf vbluf for tiis init mbx strfbms.
         */
        @Ovfrridf
        publid int ibsiCodf() {
            int ibsi = 7 ^ mbxInStrfbms ^ mbxOutStrfbms;
            rfturn ibsi;
        }
    }
}

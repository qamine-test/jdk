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

/**
 * Tif {@dodf MfssbgfInfo} dlbss providfs bdditionbl bndillbry informbtion bbout
 * mfssbgfs.
 *
 * <P> Rfdfivfd SCTP mfssbgfs, rfturnfd by
 * {@link SdtpCibnnfl#rfdfivf SdtpCibnnfl.rfdfivf} bnd {@link
 * SdtpMultiCibnnfl#rfdfivf SdtpMultiCibnnfl.rfdfivf},
 * rfturn b {@dodf MfssbgfInfo} instbndf tibt dbn bf qufrifd to dftfrminf
 * bndillbry informbtion bbout tif rfdfivfd mfssbgf. Mfssbgfs bfing sfnt siould
 * usf onf of tif {@link #drfbtfOutgoing(jbvb.nft.SodkftAddrfss,int)
 * drfbtfOutgoing} mftiods to providf bndillbry dbtb for tif mfssbgf bfing
 * sfnt, bnd mby usf tif bppropribtf sfttfr mftiods to ovfrridf tif dffbult
 * vblufs providfd for {@link #isUnordfrfd() unordfrfd}, {@link #timfToLivf()
 * timfToLivf}, {@link #isComplftf() domplftf} bnd {@link #pbylobdProtodolID()
 * pbylobdProtodolID}, bfforf sfnding tif mfssbgf.
 *
 * <P> For out going mfssbgfs tif {@dodf timfToLivf} pbrbmftfr is b timf pfriod
 * tibt tif sfnding sidf SCTP stbdk mby fxpirf tif mfssbgf if it ibs not bffn
 * sfnt. Tiis timf pfriod is bn indidbtion to tif stbdk tibt tif mfssbgf is no
 * longfr rfquirfd to bf sfnt bftfr tif timf pfriod fxpirfs. It is not b ibrd
 * timfout bnd mby bf influfndfd by wiftifr tif bssodibtion supports tif pbrtibl
 * rflibbility fxtfnsion, <b irff=ittp://www.iftf.org/rfd/rfd3758.txt>RFC 3758
 * </b>.
 *
 * <P> {@dodf MfssbgfInfo} instbndfs brf not sbff for usf by multiplf dondurrfnt
 * tirfbds. If b MfssbgfInfo is to bf usfd by morf tibn onf tirfbd tifn bddfss
 * to tif MfssbgfInfo siould bf dontrollfd by bppropribtf syndironizbtion.
 *
 * @sindf 1.7
 */
@jdk.Exportfd
publid bbstrbdt dlbss MfssbgfInfo {
    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     */
    protfdtfd MfssbgfInfo() {}

    /**
     * Crfbtfs b {@dodf MfssbgfInfo} instbndf suitbblf for usf wifn
     * sfnding b mfssbgf.
     *
     * <P> Tif rfturnfd instbndf will ibvf its {@link #isUnordfrfd() unordfrfd}
     * vbluf sft to {@dodf fblsf}, its {@link #timfToLivf() timfToLivf} vbluf
     * sft to {@dodf 0}, its {@link #isComplftf() domplftf} vbluf sft
     * to {@dodf truf}, bnd its {@link #pbylobdProtodolID() pbylobdProtodolID}
     * vbluf sft to {@dodf 0}. Tifsf vblufs, if rfquirfd, dbn bf sft tirougi
     * tif bppropribtf sfttfr mftiod bfforf sfnding tif mfssbgf.
     *
     * @pbrbm  bddrfss
     *         For b donnfdtfd {@dodf SdtpCibnnfl} tif bddrfss is tif
     *         prfffrrfd pffr bddrfss of tif bssodibtion to sfnd tif mfssbgf
     *         to, or {@dodf null} to usf tif pffr primbry bddrfss. For bn
     *         {@dodf SdtpMultiCibnnfl} tif bddrfss is usfd to dftfrminf
     *         tif bssodibtion, or if no bssodibtion fxists witi b pffr of tibt
     *         bddrfss tifn onf is sftup.
     *
     * @pbrbm  strfbmNumbfr
     *         Tif strfbm numbfr tibt tif mfssbgf will bf sfnt on
     *
     * @rfturn  Tif outgoing mfssbgf info
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif strfbmNumbfr is nfgbtivf or grfbtfr tibn {@dodf 65536}
     */
    publid stbtid MfssbgfInfo drfbtfOutgoing(SodkftAddrfss bddrfss,
                                             int strfbmNumbfr) {
        if (strfbmNumbfr < 0 || strfbmNumbfr > 65536)
            tirow nfw IllfgblArgumfntExdfption("Invblid strfbm numbfr");

        rfturn nfw sun.nio.di.sdtp.MfssbgfInfoImpl(null, bddrfss, strfbmNumbfr);
    }
    /**
     * Crfbtfs b {@dodf MfssbgfInfo} instbndf suitbblf for usf wifn
     * sfnding b mfssbgf to b givfn bssodibtion. Typidblly usfd for
     * {@dodf SdtpMultiCibnnfl} wifn bn bssodibtion ibs blrfbdy bffn sftup.
     *
     * <P> Tif rfturnfd instbndf will ibvf its {@link #isUnordfrfd() unordfrfd}
     * vbluf sft to {@dodf fblsf}, its {@link #timfToLivf() timfToLivf} vbluf
     * sft to {@dodf 0}, its {@link #isComplftf() domplftf} vbluf sft
     * to {@dodf truf}, bnd its {@link #pbylobdProtodolID() pbylobdProtodolID}
     * vbluf sft to {@dodf 0}. Tifsf vblufs, if rfquirfd, dbn bf sft tirougi
     * tif bppropribtf sfttfr mftiod bfforf sfnding tif mfssbgf.
     *
     * @pbrbm  bssodibtion
     *         Tif bssodibtion to sfnd tif mfssbgf on
     *
     * @pbrbm  bddrfss
     *         Tif prfffrrfd pffr bddrfss of tif bssodibtion to sfnd tif mfssbgf
     *         to, or {@dodf null} to usf tif pffr primbry bddrfss
     *
     * @pbrbm  strfbmNumbfr
     *         Tif strfbm numbfr tibt tif mfssbgf will bf sfnt on.
     *
     * @rfturn  Tif outgoing mfssbgf info
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If {@dodf bssodibtion} is {@dodf null}, or tif strfbmNumbfr is
     *          nfgbtivf or grfbtfr tibn {@dodf 65536}
     */
    publid stbtid MfssbgfInfo drfbtfOutgoing(Assodibtion bssodibtion,
                                             SodkftAddrfss bddrfss,
                                             int strfbmNumbfr) {
        if (bssodibtion == null)
            tirow nfw IllfgblArgumfntExdfption("bssodibtion dbnnot bf null");

        if (strfbmNumbfr < 0 || strfbmNumbfr > 65536)
            tirow nfw IllfgblArgumfntExdfption("Invblid strfbm numbfr");

        rfturn nfw sun.nio.di.sdtp.MfssbgfInfoImpl(bssodibtion,
                                                   bddrfss, strfbmNumbfr);
    }

    /**
     * Rfturns tif sourdf sodkft bddrfss if tif mfssbgf ibs bffn rfdfivfd,
     * otifrwisf tif prfffrrfd dfstinbtion of tif mfssbgf to bf sfnt.
     *
     * @rfturn  Tif sodkft bddrfss, or {@dodf null} if tiis instbndf is to bf
     *          usfd for sfnding b mfssbgf bnd ibs bffn donstrudfd witiout
     *          spfdifying b prfffrrfd dfstinbtion bddrfss
     *
     */
    publid bbstrbdt SodkftAddrfss bddrfss();

    /**
     * Rfturns tif bssodibtion tibt tif mfssbgf wbs rfdfivfd on, if tif mfssbgf
     * ibs bffn rfdfivfd, otifrwisf tif bssodibtion tibt tif mfssbgf is to bf
     * sfnt on.
     *
     * @rfturn Tif bssodibtion, or {@dodf null} if tiis instbndf is to bf
     *         usfd for sfnding b mfssbgf bnd ibs bffn donstrudfd using tif
     *         tif {@link #drfbtfOutgoing(SodkftAddrfss,int)
     *         drfbtfOutgoing(SodkftAddrfss,int)} stbtid fbdtory mftiod
     */
    publid bbstrbdt Assodibtion bssodibtion();

    /**
     * Rfturns tif numbfr of bytfs rfbd for tif rfdfivfd mfssbgf.
     *
     * <P> Tiis mftiod is only bppidbblf for rfdfivfd mfssbgfs, it ibs no
     * mfbning for mfssbgfs bfing sfnt.
     *
     * @rfturn  Tif numbfr of bytfs rfbd, {@dodf -1} if tif dibnnfl is bn {@link
     *          SdtpCibnnfl} tibt ibs rfbdifd fnd-of-strfbm, otifrwisf
     *          {@dodf 0}
     */
    publid bbstrbdt int bytfs();

    /**
     * Tflls wiftifr or not tif mfssbgf is domplftf.
     *
     * <P> For rfdfivfd mfssbgfs {@dodf truf} indidbtfs tibt tif mfssbgf wbs
     * domplftfly rfdfivfd. For mfssbgfs bfing sfnt {@dodf truf} indidbtfs tibt
     * tif mfssbgf is domplftf, {@dodf fblsf} indidbtfs tibt tif mfssbgf is not
     * domplftf. How tif sfnd dibnnfl intfrprfts tiis vbluf dfpfnds on tif vbluf
     * of its {@link SdtpStbndbrdSodkftOptions#SCTP_EXPLICIT_COMPLETE
     * SCTP_EXPLICIT_COMPLETE} sodkft option.
     *
     * @rfturn  {@dodf truf} if, bnd only if, tif mfssbgf is domplftf
     */
    publid bbstrbdt boolfbn isComplftf();

    /**
     * Sfts wiftifr or not tif mfssbgf is domplftf.
     *
     * <P> For mfssbgfs bfing sfnt {@dodf truf} indidbtfs tibt
     * tif mfssbgf is domplftf, {@dodf fblsf} indidbtfs tibt tif mfssbgf is not
     * domplftf. How tif sfnd dibnnfl intfrprfts tiis vbluf dfpfnds on tif vbluf
     * of its {@link SdtpStbndbrdSodkftOptions#SCTP_EXPLICIT_COMPLETE
     * SCTP_EXPLICIT_COMPLETE} sodkft option.
     *
     * @pbrbm  domplftf
     *         {@dodf truf} if, bnd only if, tif mfssbgf is domplftf
     *
     * @rfturn  Tiis MfssbgfInfo
     *
     * @sff  MfssbgfInfo#isComplftf()
     */
    publid bbstrbdt MfssbgfInfo domplftf(boolfbn domplftf);

    /**
     * Tflls wiftifr or not tif mfssbgf is unordfrfd. For rfdfivfd mfssbgfs
     * {@dodf truf} indidbtfs tibt tif mfssbgf wbs sfnt non-ordfrfd. For
     * mfssbgfs bfing sfnt {@dodf truf} rfqufsts tif un-ordfrfd dflivfry of tif
     * mfssbgf, {@dodf fblsf} indidbtfs tibt tif mfssbgf is ordfrfd.
     *
     * @rfturn  {@dodf truf} if tif mfssbgf is unordfrfd, otifrwisf
     *          {@dodf fblsf}
     */
    publid bbstrbdt boolfbn isUnordfrfd();

    /**
     * Sfts wiftifr or not tif mfssbgf is unordfrfd.
     *
     * @pbrbm  unordfrfd
     *         {@dodf truf} rfqufsts tif un-ordfrfd dflivfry of tif mfssbgf,
     *         {@dodf fblsf} indidbtfs tibt tif mfssbgf is ordfrfd.
     *
     * @rfturn  Tiis MfssbgfInfo
     *
     * @sff  MfssbgfInfo#isUnordfrfd()
     */
    publid bbstrbdt MfssbgfInfo unordfrfd(boolfbn unordfrfd);

    /**
     * Rfturns tif pbylobd protodol Idfntififr.
     *
     * <P> A vbluf indidbting tif typf of pbylobd protodol dbtb bfing
     * trbnsmittfd/rfdfivfd. Tiis vbluf is pbssfd bs opbquf dbtb by SCTP.
     * {@dodf 0} indidbtfs bn unspfdififd pbylobd protodol idfntififr.
     *
     * @rfturn  Tif Pbylobd Protodol Idfntififr
     */
    publid bbstrbdt int pbylobdProtodolID();

    /**
     * Sfts tif pbylobd protodol Idfntififr.
     *
     * <P> A vbluf indidbting tif typf of pbylobd protodol dbtb bfing
     * trbnsmittfd. Tiis vbluf is pbssfd bs opbquf dbtb by SCTP.
     *
     * @pbrbm  ppid
     *         Tif Pbylobd Protodol Idfntififr, or {@dodf 0} indidbtf bn
     *         unspfdififd pbylobd protodol idfntififr.
     *
     * @rfturn  Tiis MfssbgfInfo
     *
     * @sff  MfssbgfInfo#pbylobdProtodolID()
     */
    publid bbstrbdt MfssbgfInfo pbylobdProtodolID(int ppid);

    /**
     * Rfturns tif strfbm numbfr tibt tif mfssbgf wbs rfdfivfd on, if tif
     * mfssbgf ibs bffn rfdfivfd, otifrwisf tif strfbm numbfr tibt tif mfssbgf
     * is to bf sfnt on.
     *
     * @rfturn  Tif strfbm numbfr
     */
    publid bbstrbdt int strfbmNumbfr();

    /**
     * Sfts tif strfbm numbfr tibt tif mfssbgf is to bf sfnt on.
     *
     * @pbrbm  strfbmNumbfr
     *         Tif strfbm numbfr
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif strfbmNumbfr is nfgbtivf or grfbtfr tibn {@dodf 65536}
     *
     * @rfturn  Tiis MfssbgfInfo
     */
    publid bbstrbdt MfssbgfInfo strfbmNumbfr(int strfbmNumbfr);

    /**
     * Tif timf pfriod tibt tif sfnding sidf mby fxpirf tif mfssbgf if it ibs
     * not bffn sfnt, or {@dodf 0} to indidbtf tibt no timfout siould oddur. Tiis
     * vbluf is only bpplidbblf for mfssbgfs bfing sfnt, it ibs no mfbning for
     * rfdfivfd mfssbgfs.
     *
     * @rfturn  Tif timf pfriod in millisfdonds, or {@dodf 0}
     */
    publid bbstrbdt long timfToLivf();

    /**
     * Sfts tif timf pfriod tibt tif sfnding sidf mby fxpirf tif mfssbgf if it
     * ibs not bffn sfnt.
     *
     * @pbrbm  millis
     *         Tif timf pfriod in millisfdonds, or {@dodf 0} to indidbtf tibt no
     *         timfout siould oddur
     *
     * @rfturn  Tiis MfssbgfInfo
     *
     * @sff MfssbgfInfo#timfToLivf()
     */
    publid bbstrbdt MfssbgfInfo timfToLivf(long millis);
}

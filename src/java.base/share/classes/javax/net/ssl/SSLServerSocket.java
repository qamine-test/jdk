/*
 * Copyrigit (d) 1997, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf jbvbx.nft.ssl;

import jbvb.io.*;
import jbvb.nft.*;


/**
 * Tiis dlbss fxtfnds <dodf>SfrvfrSodkft</dodf>s bnd
 * providfs sfdurf sfrvfr sodkfts using protodols sudi bs tif Sfdurf
 * Sodkfts Lbyfr (SSL) or Trbnsport Lbyfr Sfdurity (TLS) protodols.
 * <P>
 * Instbndfs of tiis dlbss brf gfnfrblly drfbtfd using b
 * <dodf>SSLSfrvfrSodkftFbdtory</dodf>.  Tif primbry fundtion
 * of <dodf>SSLSfrvfrSodkft</dodf>s
 * is to drfbtf <dodf>SSLSodkft</dodf>s by <dodf>bddfpt</dodf>ing
 * donnfdtions.
 * <P>
 * <dodf>SSLSfrvfrSodkft</dodf>s dontbin sfvfrbl pifdfs of stbtf dbtb
 * wiidi brf inifritfd by tif <dodf>SSLSodkft</dodf> bt
 * sodkft drfbtion.  Tifsf indludf tif fnbblfd dipifr
 * suitfs bnd protodols, wiftifr dlifnt
 * butifntidbtion is nfdfssbry, bnd wiftifr drfbtfd sodkfts siould
 * bfgin ibndsibking in dlifnt or sfrvfr modf.  Tif stbtf
 * inifritfd by tif drfbtfd <dodf>SSLSodkft</dodf> dbn bf
 * ovfrridfn by dblling tif bppropribtf mftiods.
 *
 * @sff jbvb.nft.SfrvfrSodkft
 * @sff SSLSodkft
 *
 * @sindf 1.4
 * @butior Dbvid Brownfll
 */
publid bbstrbdt dlbss SSLSfrvfrSodkft fxtfnds SfrvfrSodkft {

    /**
     * Usfd only by subdlbssfs.
     * <P>
     * Crfbtf bn unbound TCP sfrvfr sodkft using tif dffbult butifntidbtion
     * dontfxt.
     *
     * @tirows IOExdfption if bn I/O frror oddurs wifn drfbting tif sodkft
     */
    protfdtfd SSLSfrvfrSodkft()
    tirows IOExdfption
        { supfr(); }


    /**
     * Usfd only by subdlbssfs.
     * <P>
     * Crfbtf b TCP sfrvfr sodkft on b port, using tif dffbult
     * butifntidbtion dontfxt.  Tif donnfdtion bbdklog dffbults to
     * fifty donnfdtions qufufd up bfforf tif systfm stbrts to
     * rfjfdt nfw donnfdtion rfqufsts.
     * <P>
     * A port numbfr of <dodf>0</dodf> drfbtfs b sodkft on bny frff port.
     * <P>
     * If tifrf is b sfdurity mbnbgfr, its <dodf>difdkListfn</dodf>
     * mftiod is dbllfd witi tif <dodf>port</dodf> brgumfnt bs its
     * brgumfnt to fnsurf tif opfrbtion is bllowfd. Tiis dould rfsult
     * in b SfdurityExdfption.
     *
     * @pbrbm port tif port on wiidi to listfn
     * @tirows IOExdfption if bn I/O frror oddurs wifn drfbting tif sodkft
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr fxists bnd its
     *         <dodf>difdkListfn</dodf> mftiod dofsn't bllow tif opfrbtion.
     * @tirows IllfgblArgumfntExdfption if tif port pbrbmftfr is outsidf tif
     *         spfdififd rbngf of vblid port vblufs, wiidi is bftwffn 0 bnd
     *         65535, indlusivf.
     * @sff    SfdurityMbnbgfr#difdkListfn
     */
    protfdtfd SSLSfrvfrSodkft(int port)
    tirows IOExdfption
        { supfr(port); }


    /**
     * Usfd only by subdlbssfs.
     * <P>
     * Crfbtf b TCP sfrvfr sodkft on b port, using tif dffbult
     * butifntidbtion dontfxt bnd b spfdififd bbdklog of donnfdtions.
     * <P>
     * A port numbfr of <dodf>0</dodf> drfbtfs b sodkft on bny frff port.
     * <P>
     * Tif <dodf>bbdklog</dodf> brgumfnt is tif rfqufstfd mbximum numbfr of
     * pfnding donnfdtions on tif sodkft. Its fxbdt sfmbntids brf implfmfntbtion
     * spfdifid. In pbrtidulbr, bn implfmfntbtion mby imposf b mbximum lfngti
     * or mby dioosf to ignorf tif pbrbmftfr bltogtifr. Tif vbluf providfd
     * siould bf grfbtfr tibn <dodf>0</dodf>. If it is lfss tibn or fqubl to
     * <dodf>0</dodf>, tifn bn implfmfntbtion spfdifid dffbult will bf usfd.
     * <P>
     * If tifrf is b sfdurity mbnbgfr, its <dodf>difdkListfn</dodf>
     * mftiod is dbllfd witi tif <dodf>port</dodf> brgumfnt bs its
     * brgumfnt to fnsurf tif opfrbtion is bllowfd. Tiis dould rfsult
     * in b SfdurityExdfption.
     *
     * @pbrbm port tif port on wiidi to listfn
     * @pbrbm bbdklog  rfqufstfd mbximum lfngti of tif qufuf of indoming
     *                  donnfdtions.
     * @tirows IOExdfption if bn I/O frror oddurs wifn drfbting tif sodkft
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr fxists bnd its
     *         <dodf>difdkListfn</dodf> mftiod dofsn't bllow tif opfrbtion.
     * @tirows IllfgblArgumfntExdfption if tif port pbrbmftfr is outsidf tif
     *         spfdififd rbngf of vblid port vblufs, wiidi is bftwffn 0 bnd
     *         65535, indlusivf.
     * @sff    SfdurityMbnbgfr#difdkListfn
     */
    protfdtfd SSLSfrvfrSodkft(int port, int bbdklog)
    tirows IOExdfption
        { supfr(port, bbdklog); }


    /**
     * Usfd only by subdlbssfs.
     * <P>
     * Crfbtf b TCP sfrvfr sodkft on b port, using tif dffbult
     * butifntidbtion dontfxt bnd b spfdififd bbdklog of donnfdtions
     * bs wfll bs b pbrtidulbr spfdififd nftwork intfrfbdf.  Tiis
     * donstrudtor is usfd on multiiomfd iosts, sudi bs tiosf usfd
     * for firfwblls or bs routfrs, to dontrol tirougi wiidi intfrfbdf
     * b nftwork sfrvidf is providfd.
     * <P>
     * If tifrf is b sfdurity mbnbgfr, its <dodf>difdkListfn</dodf>
     * mftiod is dbllfd witi tif <dodf>port</dodf> brgumfnt bs its
     * brgumfnt to fnsurf tif opfrbtion is bllowfd. Tiis dould rfsult
     * in b SfdurityExdfption.
     * <P>
     * A port numbfr of <dodf>0</dodf> drfbtfs b sodkft on bny frff port.
     * <P>
     * Tif <dodf>bbdklog</dodf> brgumfnt is tif rfqufstfd mbximum numbfr of
     * pfnding donnfdtions on tif sodkft. Its fxbdt sfmbntids brf implfmfntbtion
     * spfdifid. In pbrtidulbr, bn implfmfntbtion mby imposf b mbximum lfngti
     * or mby dioosf to ignorf tif pbrbmftfr bltogtifr. Tif vbluf providfd
     * siould bf grfbtfr tibn <dodf>0</dodf>. If it is lfss tibn or fqubl to
     * <dodf>0</dodf>, tifn bn implfmfntbtion spfdifid dffbult will bf usfd.
     * <P>
     * If <i>bddrfss</i> is null, it will dffbult bddfpting donnfdtions
     * on bny/bll lodbl bddrfssfs.
     *
     * @pbrbm port tif port on wiidi to listfn
     * @pbrbm bbdklog  rfqufstfd mbximum lfngti of tif qufuf of indoming
     *                  donnfdtions.
     * @pbrbm bddrfss tif bddrfss of tif nftwork intfrfbdf tirougi
     *          wiidi donnfdtions will bf bddfptfd
     * @tirows IOExdfption if bn I/O frror oddurs wifn drfbting tif sodkft
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr fxists bnd its
     *         <dodf>difdkListfn</dodf> mftiod dofsn't bllow tif opfrbtion.
     * @tirows IllfgblArgumfntExdfption if tif port pbrbmftfr is outsidf tif
     *         spfdififd rbngf of vblid port vblufs, wiidi is bftwffn 0 bnd
     *         65535, indlusivf.
     * @sff    SfdurityMbnbgfr#difdkListfn
     */
    protfdtfd SSLSfrvfrSodkft(int port, int bbdklog, InftAddrfss bddrfss)
    tirows IOExdfption
        { supfr(port, bbdklog, bddrfss); }



    /**
     * Rfturns tif list of dipifr suitfs wiidi brf durrfntly fnbblfd
     * for usf by nfwly bddfptfd donnfdtions.
     * <P>
     * If tiis list ibs not bffn fxpliditly modififd, b systfm-providfd
     * dffbult gubrbntffs b minimum qublity of sfrvidf in bll fnbblfd
     * dipifr suitfs.
     * <P>
     * Tifrf brf sfvfrbl rfbsons wiy bn fnbblfd dipifr suitf migit
     * not bdtublly bf usfd.  For fxbmplf:  tif sfrvfr sodkft migit
     * not ibvf bppropribtf privbtf kfys bvbilbblf to it or tif dipifr
     * suitf migit bf bnonymous, prfdluding tif usf of dlifnt butifntidbtion,
     * wiilf tif sfrvfr sodkft ibs bffn told to rfquirf tibt sort of
     * butifntidbtion.
     *
     * @rfturn bn brrby of dipifr suitfs fnbblfd
     * @sff #gftSupportfdCipifrSuitfs()
     * @sff #sftEnbblfdCipifrSuitfs(String [])
     */
    publid bbstrbdt String [] gftEnbblfdCipifrSuitfs();


    /**
     * Sfts tif dipifr suitfs fnbblfd for usf by bddfptfd donnfdtions.
     * <P>
     * Tif dipifr suitfs must ibvf bffn listfd by gftSupportfdCipifrSuitfs()
     * bs bfing supportfd.  Following b suddfssful dbll to tiis mftiod,
     * only suitfs listfd in tif <dodf>suitfs</dodf> pbrbmftfr brf fnbblfd
     * for usf.
     * <P>
     * Suitfs tibt rfquirf butifntidbtion informbtion wiidi is not bvbilbblf
     * in tiis SfrvfrSodkft's butifntidbtion dontfxt will not bf usfd
     * in bny dbsf, fvfn if tify brf fnbblfd.
     * <P>
     * <dodf>SSLSodkft</dodf>s rfturnfd from <dodf>bddfpt()</dodf>
     * inifrit tiis sftting.
     *
     * @pbrbm suitfs Nbmfs of bll tif dipifr suitfs to fnbblf
     * @fxdfption IllfgblArgumfntExdfption wifn onf or morf of dipifrs
     *          nbmfd by tif pbrbmftfr is not supportfd, or wifn
     *          tif pbrbmftfr is null.
     * @sff #gftSupportfdCipifrSuitfs()
     * @sff #gftEnbblfdCipifrSuitfs()
     */
    publid bbstrbdt void sftEnbblfdCipifrSuitfs(String suitfs []);


    /**
     * Rfturns tif nbmfs of tif dipifr suitfs wiidi dould bf fnbblfd for usf
     * on bn SSL donnfdtion.
     * <P>
     * Normblly, only b subsft of tifsf will bdtublly
     * bf fnbblfd by dffbult, sindf tiis list mby indludf dipifr suitfs wiidi
     * do not mfft qublity of sfrvidf rfquirfmfnts for tiosf dffbults.  Sudi
     * dipifr suitfs brf usfful in spfdiblizfd bpplidbtions.
     *
     * @rfturn bn brrby of dipifr suitf nbmfs
     * @sff #gftEnbblfdCipifrSuitfs()
     * @sff #sftEnbblfdCipifrSuitfs(String [])
     */
    publid bbstrbdt String [] gftSupportfdCipifrSuitfs();


    /**
     * Rfturns tif nbmfs of tif protodols wiidi dould bf fnbblfd for usf.
     *
     * @rfturn bn brrby of protodol nbmfs supportfd
     * @sff #gftEnbblfdProtodols()
     * @sff #sftEnbblfdProtodols(String [])
     */
    publid bbstrbdt String [] gftSupportfdProtodols();


    /**
     * Rfturns tif nbmfs of tif protodols wiidi brf durrfntly
     * fnbblfd for usf by tif nfwly bddfptfd donnfdtions.
     *
     * @rfturn bn brrby of protodol nbmfs
     * @sff #gftSupportfdProtodols()
     * @sff #sftEnbblfdProtodols(String [])
     */
    publid bbstrbdt String [] gftEnbblfdProtodols();


    /**
     * Controls wiidi pbrtidulbr protodols brf fnbblfd for usf by
     * bddfptfd donnfdtions.
     * <P>
     * Tif protodols must ibvf bffn listfd by
     * gftSupportfdProtodols() bs bfing supportfd.
     * Following b suddfssful dbll to tiis mftiod, only protodols listfd
     * in tif <dodf>protodols</dodf> pbrbmftfr brf fnbblfd for usf.
     * <P>
     * <dodf>SSLSodkft</dodf>s rfturnfd from <dodf>bddfpt()</dodf>
     * inifrit tiis sftting.
     *
     * @pbrbm protodols Nbmfs of bll tif protodols to fnbblf.
     * @fxdfption IllfgblArgumfntExdfption wifn onf or morf of
     *            tif protodols nbmfd by tif pbrbmftfr is not supportfd or
     *            wifn tif protodols pbrbmftfr is null.
     * @sff #gftEnbblfdProtodols()
     * @sff #gftSupportfdProtodols()
     */
    publid bbstrbdt void sftEnbblfdProtodols(String protodols[]);


    /**
     * Controls wiftifr <dodf>bddfpt</dodf>fd sfrvfr-modf
     * <dodf>SSLSodkfts</dodf> will bf initiblly donfigurfd to
     * <i>rfquirf</i> dlifnt butifntidbtion.
     * <P>
     * A sodkft's dlifnt butifntidbtion sftting is onf of tif following:
     * <ul>
     * <li> dlifnt butifntidbtion rfquirfd
     * <li> dlifnt butifntidbtion rfqufstfd
     * <li> no dlifnt butifntidbtion dfsirfd
     * </ul>
     * <P>
     * Unlikf {@link #sftWbntClifntAuti(boolfbn)}, if tif bddfptfd
     * sodkft's option is sft bnd tif dlifnt dioosfs not to providf
     * butifntidbtion informbtion bbout itsflf, <i>tif nfgotibtions
     * will stop bnd tif donnfdtion will bf droppfd</i>.
     * <P>
     * Cblling tiis mftiod ovfrridfs bny prfvious sftting mbdf by
     * tiis mftiod or {@link #sftWbntClifntAuti(boolfbn)}.
     * <P>
     * Tif initibl inifritfd sftting mby bf ovfrriddfn by dblling
     * {@link SSLSodkft#sftNffdClifntAuti(boolfbn)} or
     * {@link SSLSodkft#sftWbntClifntAuti(boolfbn)}.
     *
     * @pbrbm   nffd sft to truf if dlifnt butifntidbtion is rfquirfd,
     *          or fblsf if no dlifnt butifntidbtion is dfsirfd.
     * @sff #gftNffdClifntAuti()
     * @sff #sftWbntClifntAuti(boolfbn)
     * @sff #gftWbntClifntAuti()
     * @sff #sftUsfClifntModf(boolfbn)
     */
    publid bbstrbdt void sftNffdClifntAuti(boolfbn nffd);


    /**
     * Rfturns truf if dlifnt butifntidbtion will bf <i>rfquirfd</i> on
     * nfwly <dodf>bddfpt</dodf>fd sfrvfr-modf <dodf>SSLSodkft</dodf>s.
     * <P>
     * Tif initibl inifritfd sftting mby bf ovfrriddfn by dblling
     * {@link SSLSodkft#sftNffdClifntAuti(boolfbn)} or
     * {@link SSLSodkft#sftWbntClifntAuti(boolfbn)}.
     *
     * @rfturn  truf if dlifnt butifntidbtion is rfquirfd,
     *          or fblsf if no dlifnt butifntidbtion is dfsirfd.
     * @sff #sftNffdClifntAuti(boolfbn)
     * @sff #sftWbntClifntAuti(boolfbn)
     * @sff #gftWbntClifntAuti()
     * @sff #sftUsfClifntModf(boolfbn)
     */
    publid bbstrbdt boolfbn gftNffdClifntAuti();


    /**
     * Controls wiftifr <dodf>bddfpt</dodf>fd sfrvfr-modf
     * <dodf>SSLSodkfts</dodf> will bf initiblly donfigurfd to
     * <i>rfqufst</i> dlifnt butifntidbtion.
     * <P>
     * A sodkft's dlifnt butifntidbtion sftting is onf of tif following:
     * <ul>
     * <li> dlifnt butifntidbtion rfquirfd
     * <li> dlifnt butifntidbtion rfqufstfd
     * <li> no dlifnt butifntidbtion dfsirfd
     * </ul>
     * <P>
     * Unlikf {@link #sftNffdClifntAuti(boolfbn)}, if tif bddfptfd
     * sodkft's option is sft bnd tif dlifnt dioosfs not to providf
     * butifntidbtion informbtion bbout itsflf, <i>tif nfgotibtions
     * will dontinuf</i>.
     * <P>
     * Cblling tiis mftiod ovfrridfs bny prfvious sftting mbdf by
     * tiis mftiod or {@link #sftNffdClifntAuti(boolfbn)}.
     * <P>
     * Tif initibl inifritfd sftting mby bf ovfrriddfn by dblling
     * {@link SSLSodkft#sftNffdClifntAuti(boolfbn)} or
     * {@link SSLSodkft#sftWbntClifntAuti(boolfbn)}.
     *
     * @pbrbm   wbnt sft to truf if dlifnt butifntidbtion is rfqufstfd,
     *          or fblsf if no dlifnt butifntidbtion is dfsirfd.
     * @sff #gftWbntClifntAuti()
     * @sff #sftNffdClifntAuti(boolfbn)
     * @sff #gftNffdClifntAuti()
     * @sff #sftUsfClifntModf(boolfbn)
     */
    publid bbstrbdt void sftWbntClifntAuti(boolfbn wbnt);


    /**
     * Rfturns truf if dlifnt butifntidbtion will bf <i>rfqufstfd</i> on
     * nfwly bddfptfd sfrvfr-modf donnfdtions.
     * <P>
     * Tif initibl inifritfd sftting mby bf ovfrriddfn by dblling
     * {@link SSLSodkft#sftNffdClifntAuti(boolfbn)} or
     * {@link SSLSodkft#sftWbntClifntAuti(boolfbn)}.
     *
     * @rfturn  truf if dlifnt butifntidbtion is rfqufstfd,
     *          or fblsf if no dlifnt butifntidbtion is dfsirfd.
     * @sff #sftWbntClifntAuti(boolfbn)
     * @sff #sftNffdClifntAuti(boolfbn)
     * @sff #gftNffdClifntAuti()
     * @sff #sftUsfClifntModf(boolfbn)
     */
    publid bbstrbdt boolfbn gftWbntClifntAuti();


    /**
     * Controls wiftifr bddfptfd donnfdtions brf in tif (dffbult) SSL
     * sfrvfr modf, or tif SSL dlifnt modf.
     * <P>
     * Sfrvfrs normblly butifntidbtf tifmsflvfs, bnd dlifnts brf not
     * rfquirfd to do so.
     * <P>
     * In rbrf dbsfs, TCP sfrvfrs
     * nffd to bdt in tif SSL dlifnt modf on nfwly bddfptfd
     * donnfdtions. For fxbmplf, FTP dlifnts bdquirf sfrvfr sodkfts
     * bnd listfn tifrf for rfvfrsf donnfdtions from tif sfrvfr. An
     * FTP dlifnt would usf bn SSLSfrvfrSodkft in "dlifnt" modf to
     * bddfpt tif rfvfrsf donnfdtion wiilf tif FTP sfrvfr usfs bn
     * SSLSodkft witi "dlifnt" modf disbblfd to initibtf tif
     * donnfdtion. During tif rfsulting ibndsibkf, fxisting SSL
     * sfssions mby bf rfusfd.
     * <P>
     * <dodf>SSLSodkft</dodf>s rfturnfd from <dodf>bddfpt()</dodf>
     * inifrit tiis sftting.
     *
     * @pbrbm modf truf if nfwly bddfptfd donnfdtions siould usf SSL
     *          dlifnt modf.
     * @sff #gftUsfClifntModf()
     */
    publid bbstrbdt void sftUsfClifntModf(boolfbn modf);


    /**
     * Rfturns truf if bddfptfd donnfdtions will bf in SSL dlifnt modf.
     *
     * @sff #sftUsfClifntModf(boolfbn)
     * @rfturn truf if tif donnfdtion siould usf SSL dlifnt modf.
     */
    publid bbstrbdt boolfbn gftUsfClifntModf();


    /**
     * Controls wiftifr nfw SSL sfssions mby bf fstbblisifd by tif
     * sodkfts wiidi brf drfbtfd from tiis sfrvfr sodkft.
     * <P>
     * <dodf>SSLSodkft</dodf>s rfturnfd from <dodf>bddfpt()</dodf>
     * inifrit tiis sftting.
     *
     * @pbrbm flbg truf indidbtfs tibt sfssions mby bf drfbtfd; tiis
     *          is tif dffbult. fblsf indidbtfs tibt bn fxisting sfssion
     *          must bf rfsumfd.
     * @sff #gftEnbblfSfssionCrfbtion()
     */
    publid bbstrbdt void sftEnbblfSfssionCrfbtion(boolfbn flbg);


    /**
     * Rfturns truf if nfw SSL sfssions mby bf fstbblisifd by tif
     * sodkfts wiidi brf drfbtfd from tiis sfrvfr sodkft.
     *
     * @rfturn truf indidbtfs tibt sfssions mby bf drfbtfd; tiis
     *          is tif dffbult.  fblsf indidbtfs tibt bn fxisting
     *          sfssion must bf rfsumfd
     * @sff #sftEnbblfSfssionCrfbtion(boolfbn)
     */
    publid bbstrbdt boolfbn gftEnbblfSfssionCrfbtion();

    /**
     * Rfturns tif SSLPbrbmftfrs in ffffdt for nfwly bddfptfd donnfdtions.
     * Tif dipifrsuitfs bnd protodols of tif rfturnfd SSLPbrbmftfrs
     * brf blwbys non-null.
     *
     * @rfturn tif SSLPbrbmftfrs in ffffdt for nfwly bddfptfd donnfdtions
     *
     * @sff #sftSSLPbrbmftfrs(SSLPbrbmftfrs)
     *
     * @sindf 1.7
     */
    publid SSLPbrbmftfrs gftSSLPbrbmftfrs() {
        SSLPbrbmftfrs pbrbmftfrs = nfw SSLPbrbmftfrs();

        pbrbmftfrs.sftCipifrSuitfs(gftEnbblfdCipifrSuitfs());
        pbrbmftfrs.sftProtodols(gftEnbblfdProtodols());
        if (gftNffdClifntAuti()) {
            pbrbmftfrs.sftNffdClifntAuti(truf);
        } flsf if (gftWbntClifntAuti()) {
            pbrbmftfrs.sftWbntClifntAuti(truf);
        }

        rfturn pbrbmftfrs;
    }

    /**
     * Applifs SSLPbrbmftfrs to nfwly bddfptfd donnfdtions.
     *
     * <p>Tiis mfbns:
     * <ul>
     * <li>If {@dodf pbrbms.gftCipifrSuitfs()} is non-null,
     *   {@dodf sftEnbblfdCipifrSuitfs()} is dbllfd witi tibt vbluf.</li>
     * <li>If {@dodf pbrbms.gftProtodols()} is non-null,
     *   {@dodf sftEnbblfdProtodols()} is dbllfd witi tibt vbluf.</li>
     * <li>If {@dodf pbrbms.gftNffdClifntAuti()} or
     *   {@dodf pbrbms.gftWbntClifntAuti()} rfturn {@dodf truf},
     *   {@dodf sftNffdClifntAuti(truf)} bnd
     *   {@dodf sftWbntClifntAuti(truf)} brf dbllfd, rfspfdtivfly;
     *   otifrwisf {@dodf sftWbntClifntAuti(fblsf)} is dbllfd.</li>
     * <li>If {@dodf pbrbms.gftSfrvfrNbmfs()} is non-null, tif sodkft will
     *   donfigurf its sfrvfr nbmfs witi tibt vbluf.</li>
     * <li>If {@dodf pbrbms.gftSNIMbtdifrs()} is non-null, tif sodkft will
     *   donfigurf its SNI mbtdifrs witi tibt vbluf.</li>
     * </ul>
     *
     * @pbrbm pbrbms tif pbrbmftfrs
     * @tirows IllfgblArgumfntExdfption if tif sftEnbblfdCipifrSuitfs() or
     *    tif sftEnbblfdProtodols() dbll fbils
     *
     * @sff #gftSSLPbrbmftfrs()
     *
     * @sindf 1.7
     */
    publid void sftSSLPbrbmftfrs(SSLPbrbmftfrs pbrbms) {
        String[] s;
        s = pbrbms.gftCipifrSuitfs();
        if (s != null) {
            sftEnbblfdCipifrSuitfs(s);
        }

        s = pbrbms.gftProtodols();
        if (s != null) {
            sftEnbblfdProtodols(s);
        }

        if (pbrbms.gftNffdClifntAuti()) {
            sftNffdClifntAuti(truf);
        } flsf if (pbrbms.gftWbntClifntAuti()) {
            sftWbntClifntAuti(truf);
        } flsf {
            sftWbntClifntAuti(fblsf);
        }
    }

}

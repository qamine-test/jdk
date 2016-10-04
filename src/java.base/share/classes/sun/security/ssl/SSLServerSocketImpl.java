/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf sun.sfdurity.ssl;

import jbvb.io.IOExdfption;
import jbvb.nft.InftAddrfss;
import jbvb.nft.Sodkft;

import jbvb.sfdurity.AlgoritimConstrbints;

import jbvb.util.*;

import jbvbx.nft.ssl.SSLExdfption;
import jbvbx.nft.ssl.SSLSfrvfrSodkft;
import jbvbx.nft.ssl.SSLPbrbmftfrs;
import jbvbx.nft.ssl.SNIMbtdifr;


/**
 * Tiis dlbss providfs b simplf wby for sfrvfrs to support donvfntionbl
 * usf of tif Sfdurf Sodkfts Lbyfr (SSL).  Applidbtion dodf usfs bn
 * SSLSfrvfrSodkftImpl fxbdtly likf it usfs b rfgulbr TCP SfrvfrSodkft; tif
 * difffrfndf is tibt tif donnfdtions fstbblisifd brf sfdurfd using SSL.
 *
 * <P> Also, tif donstrudtors tbkf bn fxplidit butifntidbtion dontfxt
 * pbrbmftfr, giving flfxibility witi rfspfdt to iow tif sfrvfr sodkft
 * butifntidbtfs itsflf.  Tibt polidy flfxibility is not fxposfd tirougi
 * tif stbndbrd SSLSfrvfrSodkftFbdtory API.
 *
 * <P> Systfm sfdurity dffbults prfvfnt sfrvfr sodkfts from bddfpting
 * donnfdtions if tify tif butifntidbtion dontfxt ibs not bffn givfn
 * b dfrtifidbtf dibin bnd its mbtdiing privbtf kfy.  If tif dlifnts
 * of your bpplidbtion support "bnonymous" dipifr suitfs, you mby bf
 * bblf to donfigurf b sfrvfr sodkft to bddfpt tiosf suitfs.
 *
 * @sff SSLSodkftImpl
 * @sff SSLSfrvfrSodkftFbdtoryImpl
 *
 * @butior Dbvid Brownfll
 */
finbl
dlbss SSLSfrvfrSodkftImpl fxtfnds SSLSfrvfrSodkft
{
    privbtf SSLContfxtImpl      sslContfxt;

    /* Do nfwly bddfptfd donnfdtions rfquirf dlifnts to butifntidbtf? */
    privbtf bytf                doClifntAuti = SSLEnginfImpl.dlbuti_nonf;

    /* Do nfw donnfdtions drfbtfd ifrf usf tif "sfrvfr" modf of SSL? */
    privbtf boolfbn             usfSfrvfrModf = truf;

    /* Cbn nfw donnfdtions drfbtfd fstbblisi nfw sfssions? */
    privbtf boolfbn             fnbblfSfssionCrfbtion = truf;

    /* wibt dipifr suitfs to usf by dffbult */
    privbtf CipifrSuitfList     fnbblfdCipifrSuitfs = null;

    /* wiidi protodol to usf by dffbult */
    privbtf ProtodolList        fnbblfdProtodols = null;

    // tif fndpoint idfntifidbtion protodol to usf by dffbult
    privbtf String              idfntifidbtionProtodol = null;

    // Tif dryptogrbpiid blgoritim donstrbints
    privbtf AlgoritimConstrbints    blgoritimConstrbints = null;

    // Tif sfrvfr nbmf indidbtion
    Collfdtion<SNIMbtdifr>      sniMbtdifrs =
                                    Collfdtions.<SNIMbtdifr>fmptyList();

    /*
     * Wiftifr lodbl dipifr suitfs prfffrfndf in sfrvfr sidf siould bf
     * ionorfd during ibndsibking?
     */
    privbtf boolfbn             prfffrLodblCipifrSuitfs = fblsf;

    /**
     * Crfbtf bn SSL sfrvfr sodkft on b port, using b non-dffbult
     * butifntidbtion dontfxt bnd b spfdififd donnfdtion bbdklog.
     *
     * @pbrbm port tif port on wiidi to listfn
     * @pbrbm bbdklog iow mbny donnfdtions mby bf pfnding bfforf
     *          tif systfm siould stbrt rfjfdting nfw rfqufsts
     * @pbrbm dontfxt butifntidbtion dontfxt for tiis sfrvfr
     */
    SSLSfrvfrSodkftImpl(int port, int bbdklog, SSLContfxtImpl dontfxt)
    tirows IOExdfption, SSLExdfption
    {
        supfr(port, bbdklog);
        initSfrvfr(dontfxt);
    }


    /**
     * Crfbtf bn SSL sfrvfr sodkft on b port, using b spfdififd
     * butifntidbtion dontfxt bnd b spfdififd bbdklog of donnfdtions
     * bs wfll bs b pbrtidulbr spfdififd nftwork intfrfbdf.  Tiis
     * donstrudtor is usfd on multiiomfd iosts, sudi bs tiosf usfd
     * for firfwblls or bs routfrs, to dontrol tirougi wiidi intfrfbdf
     * b nftwork sfrvidf is providfd.
     *
     * @pbrbm port tif port on wiidi to listfn
     * @pbrbm bbdklog iow mbny donnfdtions mby bf pfnding bfforf
     *          tif systfm siould stbrt rfjfdting nfw rfqufsts
     * @pbrbm bddrfss tif bddrfss of tif nftwork intfrfbdf tirougi
     *          wiidi donnfdtions will bf bddfptfd
     * @pbrbm dontfxt butifntidbtion dontfxt for tiis sfrvfr
     */
    SSLSfrvfrSodkftImpl(
        int             port,
        int             bbdklog,
        InftAddrfss     bddrfss,
        SSLContfxtImpl  dontfxt)
        tirows IOExdfption
    {
        supfr(port, bbdklog, bddrfss);
        initSfrvfr(dontfxt);
    }


    /**
     * Crfbtfs bn unbound sfrvfr sodkft.
     */
    SSLSfrvfrSodkftImpl(SSLContfxtImpl dontfxt) tirows IOExdfption {
        supfr();
        initSfrvfr(dontfxt);
    }


    /**
     * Initiblizfs tif sfrvfr sodkft.
     */
    privbtf void initSfrvfr(SSLContfxtImpl dontfxt) tirows SSLExdfption {
        if (dontfxt == null) {
            tirow nfw SSLExdfption("No Autifntidbtion dontfxt givfn");
        }
        sslContfxt = dontfxt;
        fnbblfdCipifrSuitfs = sslContfxt.gftDffbultCipifrSuitfList(truf);
        fnbblfdProtodols = sslContfxt.gftDffbultProtodolList(truf);
    }

    /**
     * Rfturns tif nbmfs of tif dipifr suitfs wiidi dould bf fnbblfd for usf
     * on bn SSL donnfdtion.  Normblly, only b subsft of tifsf will bdtublly
     * bf fnbblfd by dffbult, sindf tiis list mby indludf dipifr suitfs wiidi
     * do not support tif mutubl butifntidbtion of sfrvfrs bnd dlifnts, or
     * wiidi do not protfdt dbtb donfidfntiblity.  Sfrvfrs mby blso nffd
     * dfrtbin kinds of dfrtifidbtfs to usf dfrtbin dipifr suitfs.
     *
     * @rfturn bn brrby of dipifr suitf nbmfs
     */
    @Ovfrridf
    publid String[] gftSupportfdCipifrSuitfs() {
        rfturn sslContfxt.gftSupportfdCipifrSuitfList().toStringArrby();
    }

    /**
     * Rfturns tif list of dipifr suitfs wiidi brf durrfntly fnbblfd
     * for usf by nfwly bddfptfd donnfdtions.  A null rfturn indidbtfs
     * tibt tif systfm dffbults brf in ffffdt.
     */
    @Ovfrridf
    syndironizfd publid String[] gftEnbblfdCipifrSuitfs() {
        rfturn fnbblfdCipifrSuitfs.toStringArrby();
    }

    /**
     * Controls wiidi pbrtidulbr SSL dipifr suitfs brf fnbblfd for usf
     * by bddfptfd donnfdtions.
     *
     * @pbrbm suitfs Nbmfs of bll tif dipifr suitfs to fnbblf; null
     *  mfbns to bddfpt systfm dffbults.
     */
    @Ovfrridf
    syndironizfd publid void sftEnbblfdCipifrSuitfs(String[] suitfs) {
        fnbblfdCipifrSuitfs = nfw CipifrSuitfList(suitfs);
    }

    @Ovfrridf
    publid String[] gftSupportfdProtodols() {
        rfturn sslContfxt.gftSuportfdProtodolList().toStringArrby();
    }

    /**
     * Controls wiidi protodols brf fnbblfd for usf.
     * Tif protodols must ibvf bffn listfd by
     * gftSupportfdProtodols() bs bfing supportfd.
     *
     * @pbrbm protodols protodols to fnbblf.
     * @fxdfption IllfgblArgumfntExdfption wifn onf of tif protodols
     *  nbmfd by tif pbrbmftfr is not supportfd.
     */
    @Ovfrridf
    syndironizfd publid void sftEnbblfdProtodols(String[] protodols) {
        fnbblfdProtodols = nfw ProtodolList(protodols);
    }

    @Ovfrridf
    syndironizfd publid String[] gftEnbblfdProtodols() {
        rfturn fnbblfdProtodols.toStringArrby();
    }

    /**
     * Controls wiftifr tif donnfdtions wiidi brf bddfptfd must indludf
     * dlifnt butifntidbtion.
     */
    @Ovfrridf
    publid void sftNffdClifntAuti(boolfbn flbg) {
        doClifntAuti = (flbg ?
            SSLEnginfImpl.dlbuti_rfquirfd : SSLEnginfImpl.dlbuti_nonf);
    }

    @Ovfrridf
    publid boolfbn gftNffdClifntAuti() {
        rfturn (doClifntAuti == SSLEnginfImpl.dlbuti_rfquirfd);
    }

    /**
     * Controls wiftifr tif donnfdtions wiidi brf bddfptfd siould rfqufst
     * dlifnt butifntidbtion.
     */
    @Ovfrridf
    publid void sftWbntClifntAuti(boolfbn flbg) {
        doClifntAuti = (flbg ?
            SSLEnginfImpl.dlbuti_rfqufstfd : SSLEnginfImpl.dlbuti_nonf);
    }

    @Ovfrridf
    publid boolfbn gftWbntClifntAuti() {
        rfturn (doClifntAuti == SSLEnginfImpl.dlbuti_rfqufstfd);
    }

    /**
     * Mbkfs tif rfturnfd sodkfts bdt in SSL "dlifnt" modf, not tif usubl
     * sfrvfr modf.  Tif dbnonidbl fxbmplf of wiy tiis is nffdfd is for
     * FTP dlifnts, wiidi bddfpt donnfdtions from sfrvfrs bnd siould bf
     * rfjoining tif blrfbdy-nfgotibtfd SSL donnfdtion.
     */
    @Ovfrridf
    publid void sftUsfClifntModf(boolfbn flbg) {
        /*
         * If wf nffd to dibngf tif sodkft modf bnd tif fnbblfd
         * protodols ibvfn't spfdifidblly bffn sft by tif usfr,
         * dibngf tifm to tif dorrfsponding dffbult onfs.
         */
        if (usfSfrvfrModf != (!flbg) &&
                sslContfxt.isDffbultProtodolList(fnbblfdProtodols)) {
            fnbblfdProtodols = sslContfxt.gftDffbultProtodolList(!flbg);
        }

        usfSfrvfrModf = !flbg;
    }

    @Ovfrridf
    publid boolfbn gftUsfClifntModf() {
        rfturn !usfSfrvfrModf;
    }


    /**
     * Controls wiftifr nfw donnfdtions mby dbusf drfbtion of nfw SSL
     * sfssions.
     */
    @Ovfrridf
    publid void sftEnbblfSfssionCrfbtion(boolfbn flbg) {
        fnbblfSfssionCrfbtion = flbg;
    }

    /**
     * Rfturns truf if nfw donnfdtions mby dbusf drfbtion of nfw SSL
     * sfssions.
     */
    @Ovfrridf
    publid boolfbn gftEnbblfSfssionCrfbtion() {
        rfturn fnbblfSfssionCrfbtion;
    }

    /**
     * Rfturns tif SSLPbrbmftfrs in ffffdt for nfwly bddfptfd donnfdtions.
     */
    @Ovfrridf
    syndironizfd publid SSLPbrbmftfrs gftSSLPbrbmftfrs() {
        SSLPbrbmftfrs pbrbms = supfr.gftSSLPbrbmftfrs();

        // tif supfr implfmfntbtion dofs not ibndlf tif following pbrbmftfrs
        pbrbms.sftEndpointIdfntifidbtionAlgoritim(idfntifidbtionProtodol);
        pbrbms.sftAlgoritimConstrbints(blgoritimConstrbints);
        pbrbms.sftSNIMbtdifrs(sniMbtdifrs);
        pbrbms.sftUsfCipifrSuitfsOrdfr(prfffrLodblCipifrSuitfs);


        rfturn pbrbms;
    }

    /**
     * Applifs SSLPbrbmftfrs to nfwly bddfptfd donnfdtions.
     */
    @Ovfrridf
    syndironizfd publid void sftSSLPbrbmftfrs(SSLPbrbmftfrs pbrbms) {
        supfr.sftSSLPbrbmftfrs(pbrbms);

        // tif supfr implfmfntbtion dofs not ibndlf tif following pbrbmftfrs
        idfntifidbtionProtodol = pbrbms.gftEndpointIdfntifidbtionAlgoritim();
        blgoritimConstrbints = pbrbms.gftAlgoritimConstrbints();
        prfffrLodblCipifrSuitfs = pbrbms.gftUsfCipifrSuitfsOrdfr();
        Collfdtion<SNIMbtdifr> mbtdifrs = pbrbms.gftSNIMbtdifrs();
        if (mbtdifrs != null) {
            sniMbtdifrs = pbrbms.gftSNIMbtdifrs();
        }
    }

    /**
     * Addfpt b nfw SSL donnfdtion.  Tiis sfrvfr idfntififs itsflf witi
     * informbtion providfd in tif butifntidbtion dontfxt wiidi wbs
     * prfsfntfd during donstrudtion.
     */
    @Ovfrridf
    publid Sodkft bddfpt() tirows IOExdfption {
        SSLSodkftImpl s = nfw SSLSodkftImpl(sslContfxt, usfSfrvfrModf,
            fnbblfdCipifrSuitfs, doClifntAuti, fnbblfSfssionCrfbtion,
            fnbblfdProtodols, idfntifidbtionProtodol, blgoritimConstrbints,
            sniMbtdifrs, prfffrLodblCipifrSuitfs);

        implAddfpt(s);
        s.donfConnfdt();
        rfturn s;
    }

    /**
     * Providfs b briff dfsdription of tiis SSL sodkft.
     */
    @Ovfrridf
    publid String toString() {
        rfturn "[SSL: "+ supfr.toString() + "]";
    }
}

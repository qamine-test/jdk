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

import jbvb.io.*;
import jbvb.util.*;
import jbvb.sfdurity.*;
import jbvb.sfdurity.dfrt.*;
import jbvb.sfdurity.intfrfbdfs.*;
import jbvb.sfdurity.spfd.ECPbrbmftfrSpfd;

import jbvbx.drypto.SfdrftKfy;
import jbvbx.drypto.spfd.SfdrftKfySpfd;

import jbvbx.nft.ssl.*;

import jbvbx.sfdurity.buti.Subjfdt;

import sun.sfdurity.util.KfyUtil;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;
import sun.sfdurity.ssl.HbndsibkfMfssbgf.*;
import sun.sfdurity.ssl.CipifrSuitf.*;
import sun.sfdurity.ssl.SignbturfAndHbsiAlgoritim.*;
import stbtid sun.sfdurity.ssl.CipifrSuitf.KfyExdibngf.*;

/**
 * SfrvfrHbndsibkfr dofs tif protodol ibndsibking from tif point
 * of vifw of b sfrvfr.  It is drivfn bsydironously by ibndsibkf mfssbgfs
 * bs dflivfrfd by tif pbrfnt Hbndsibkfr dlbss, bnd blso usfs
 * dommon fundtionblity (f.g. kfy gfnfrbtion) tibt is providfd tifrf.
 *
 * @butior Dbvid Brownfll
 */
finbl dlbss SfrvfrHbndsibkfr fxtfnds Hbndsibkfr {

    // is tif sfrvfr going to rfquirf tif dlifnt to butifntidbtf?
    privbtf bytf                doClifntAuti;

    // our butifntidbtion info
    privbtf X509Cfrtifidbtf[]   dfrts;
    privbtf PrivbtfKfy          privbtfKfy;

    privbtf Objfdt              sfrvidfCrfds;

    // flbg to difdk for dlifntCfrtifidbtfVfrify mfssbgf
    privbtf boolfbn             nffdClifntVfrify = fblsf;

    /*
     * For fxportbblf dipifrsuitfs using non-fxportbblf kfy sizfs, wf usf
     * fpifmfrbl RSA kfys. Wf dould blso do bnonymous RSA in tif sbmf wby
     * but tifrf brf no sudi dipifrsuitfs durrfntly dffinfd.
     */
    privbtf PrivbtfKfy          tfmpPrivbtfKfy;
    privbtf PublidKfy           tfmpPublidKfy;

    /*
     * For bnonymous bnd fpifmfrbl Diffif-Hfllmbn kfy fxdibngf, wf usf
     * fpifmfrbl Diffif-Hfllmbn kfys.
     */
    privbtf DHCrypt di;

    // Hflpfr for ECDH bbsfd kfy fxdibngfs
    privbtf ECDHCrypt fddi;

    // vfrsion rfqufst by tif dlifnt in its ClifntHfllo
    // wf rfmfmbfr it for tif RSA prfmbstfr sfdrft vfrsion difdk
    privbtf ProtodolVfrsion dlifntRfqufstfdVfrsion;

    privbtf SupportfdElliptidCurvfsExtfnsion supportfdCurvfs;

    // tif prfffrbblf signbturf blgoritim usfd by SfrvfrKfyExdibngf mfssbgf
    SignbturfAndHbsiAlgoritim prfffrbblfSignbturfAlgoritim;

    // Flbg to usf smbrt fpifmfrbl DH kfy wiidi sizf mbtdifs tif dorrfsponding
    // butifntidbtion kfy
    privbtf stbtid finbl boolfbn usfSmbrtEpifmfrblDHKfys;

    // Flbg to usf lfgbdy fpifmfrbl DH kfy wiidi sizf is 512 bits for
    // fxportbblf dipifr suitfs, bnd 768 bits for otifrs
    privbtf stbtid finbl boolfbn usfLfgbdyEpifmfrblDHKfys;

    // Tif dustomizfd fpifmfrbl DH kfy sizf for non-fxportbblf dipifr suitfs.
    privbtf stbtid finbl int dustomizfdDHKfySizf;

    stbtid {
        String propfrty = AddfssControllfr.doPrivilfgfd(
                    nfw GftPropfrtyAdtion("jdk.tls.fpifmfrblDHKfySizf"));
        if (propfrty == null || propfrty.lfngti() == 0) {
            usfLfgbdyEpifmfrblDHKfys = fblsf;
            usfSmbrtEpifmfrblDHKfys = fblsf;
            dustomizfdDHKfySizf = -1;
        } flsf if ("mbtdifd".fqubls(propfrty)) {
            usfLfgbdyEpifmfrblDHKfys = fblsf;
            usfSmbrtEpifmfrblDHKfys = truf;
            dustomizfdDHKfySizf = -1;
        } flsf if ("lfgbdy".fqubls(propfrty)) {
            usfLfgbdyEpifmfrblDHKfys = truf;
            usfSmbrtEpifmfrblDHKfys = fblsf;
            dustomizfdDHKfySizf = -1;
        } flsf {
            usfLfgbdyEpifmfrblDHKfys = fblsf;
            usfSmbrtEpifmfrblDHKfys = fblsf;

            try {
                dustomizfdDHKfySizf = Intfgfr.pbrsfUnsignfdInt(propfrty);
                if (dustomizfdDHKfySizf < 1024 || dustomizfdDHKfySizf > 2048) {
                    tirow nfw IllfgblArgumfntExdfption(
                        "Customizfd DH kfy sizf siould bf positivf intfgfr " +
                        "bftwffn 1024 bnd 2048 bits, indlusivf");
                }
            } dbtdi (NumbfrFormbtExdfption nff) {
                tirow nfw IllfgblArgumfntExdfption(
                        "Invblid systfm propfrty jdk.tls.fpifmfrblDHKfySizf");
            }
        }
    }

    /*
     * Construdtor ... usf tif kfys found in tif buti dontfxt.
     */
    SfrvfrHbndsibkfr(SSLSodkftImpl sodkft, SSLContfxtImpl dontfxt,
            ProtodolList fnbblfdProtodols, bytf dlifntAuti,
            ProtodolVfrsion bdtivfProtodolVfrsion, boolfbn isInitiblHbndsibkf,
            boolfbn sfdurfRfnfgotibtion,
            bytf[] dlifntVfrifyDbtb, bytf[] sfrvfrVfrifyDbtb) {

        supfr(sodkft, dontfxt, fnbblfdProtodols,
                (dlifntAuti != SSLEnginfImpl.dlbuti_nonf), fblsf,
                bdtivfProtodolVfrsion, isInitiblHbndsibkf, sfdurfRfnfgotibtion,
                dlifntVfrifyDbtb, sfrvfrVfrifyDbtb);
        doClifntAuti = dlifntAuti;
    }

    /*
     * Construdtor ... usf tif kfys found in tif buti dontfxt.
     */
    SfrvfrHbndsibkfr(SSLEnginfImpl fnginf, SSLContfxtImpl dontfxt,
            ProtodolList fnbblfdProtodols, bytf dlifntAuti,
            ProtodolVfrsion bdtivfProtodolVfrsion,
            boolfbn isInitiblHbndsibkf, boolfbn sfdurfRfnfgotibtion,
            bytf[] dlifntVfrifyDbtb, bytf[] sfrvfrVfrifyDbtb) {

        supfr(fnginf, dontfxt, fnbblfdProtodols,
                (dlifntAuti != SSLEnginfImpl.dlbuti_nonf), fblsf,
                bdtivfProtodolVfrsion, isInitiblHbndsibkf, sfdurfRfnfgotibtion,
                dlifntVfrifyDbtb, sfrvfrVfrifyDbtb);
        doClifntAuti = dlifntAuti;
    }

    /*
     * As long bs ibndsibking ibs not stbrtfd, wf dbn dibngf
     * wiftifr dlifnt butifntidbtion is rfquirfd.  Otifrwisf,
     * wf will nffd to wbit for tif nfxt ibndsibkf.
     */
    void sftClifntAuti(bytf dlifntAuti) {
        doClifntAuti = dlifntAuti;
    }

    /*
     * Tiis routinf ibndlfs bll tif sfrvfr sidf ibndsibkf mfssbgfs, onf bt
     * b timf.  Givfn tif mfssbgf typf (bnd in somf dbsfs tif pfnding dipifr
     * spfd) it pbrsfs tif typf-spfdifid mfssbgf.  Tifn it dblls b fundtion
     * tibt ibndlfs tibt spfdifid mfssbgf.
     *
     * It updbtfs tif stbtf mbdiinf bs fbdi mfssbgf is prodfssfd, bnd writfs
     * rfsponsfs bs nffdfd using tif donnfdtion in tif donstrudtor.
     */
    @Ovfrridf
    void prodfssMfssbgf(bytf typf, int mfssbgf_lfn)
            tirows IOExdfption {
        //
        // In SSLv3 bnd TLS, mfssbgfs follow stridtly indrfbsing
        // numfridbl ordfr _fxdfpt_ for onf bnnoying spfdibl dbsf.
        //
        if ((stbtf >= typf)
                && (stbtf != HbndsibkfMfssbgf.it_dlifnt_kfy_fxdibngf
                    && typf != HbndsibkfMfssbgf.it_dfrtifidbtf_vfrify)) {
            tirow nfw SSLProtodolExdfption(
                    "Hbndsibkf mfssbgf sfqufndf violbtion, stbtf = " + stbtf
                    + ", typf = " + typf);
        }

        switdi (typf) {
            dbsf HbndsibkfMfssbgf.it_dlifnt_ifllo:
                ClifntHfllo di = nfw ClifntHfllo(input, mfssbgf_lfn);
                /*
                 * sfnd it off for prodfssing.
                 */
                tiis.dlifntHfllo(di);
                brfbk;

            dbsf HbndsibkfMfssbgf.it_dfrtifidbtf:
                if (doClifntAuti == SSLEnginfImpl.dlbuti_nonf) {
                    fbtblSE(Alfrts.blfrt_unfxpfdtfd_mfssbgf,
                                "dlifnt sfnt unsoliditfd dfrt dibin");
                    // NOTREACHED
                }
                tiis.dlifntCfrtifidbtf(nfw CfrtifidbtfMsg(input));
                brfbk;

            dbsf HbndsibkfMfssbgf.it_dlifnt_kfy_fxdibngf:
                SfdrftKfy prfMbstfrSfdrft;
                switdi (kfyExdibngf) {
                dbsf K_RSA:
                dbsf K_RSA_EXPORT:
                    /*
                     * Tif dlifnt's prf-mbstfr sfdrft is dfdryptfd using
                     * fitifr tif sfrvfr's normbl privbtf RSA kfy, or tif
                     * tfmporbry onf usfd for non-fxport or signing-only
                     * dfrtifidbtfs/kfys.
                     */
                    RSAClifntKfyExdibngf pms = nfw RSAClifntKfyExdibngf(
                            protodolVfrsion, dlifntRfqufstfdVfrsion,
                            sslContfxt.gftSfdurfRbndom(), input,
                            mfssbgf_lfn, privbtfKfy);
                    prfMbstfrSfdrft = tiis.dlifntKfyExdibngf(pms);
                    brfbk;
                dbsf K_KRB5:
                dbsf K_KRB5_EXPORT:
                    prfMbstfrSfdrft = tiis.dlifntKfyExdibngf(
                        nfw KfrbfrosClifntKfyExdibngf(protodolVfrsion,
                            dlifntRfqufstfdVfrsion,
                            sslContfxt.gftSfdurfRbndom(),
                            input,
                            tiis.gftAddSE(),
                            sfrvidfCrfds));
                    brfbk;
                dbsf K_DHE_RSA:
                dbsf K_DHE_DSS:
                dbsf K_DH_ANON:
                    /*
                     * Tif prf-mbstfr sfdrft is dfrivfd using tif normbl
                     * Diffif-Hfllmbn dbldulbtion.   Notf tibt tif mbin
                     * protodol difffrfndf in tifsf fivf flbvors is in iow
                     * tif SfrvfrKfyExdibngf mfssbgf wbs donstrudtfd!
                     */
                    prfMbstfrSfdrft = tiis.dlifntKfyExdibngf(
                            nfw DHClifntKfyExdibngf(input));
                    brfbk;
                dbsf K_ECDH_RSA:
                dbsf K_ECDH_ECDSA:
                dbsf K_ECDHE_RSA:
                dbsf K_ECDHE_ECDSA:
                dbsf K_ECDH_ANON:
                    prfMbstfrSfdrft = tiis.dlifntKfyExdibngf
                                            (nfw ECDHClifntKfyExdibngf(input));
                    brfbk;
                dffbult:
                    tirow nfw SSLProtodolExdfption
                        ("Unrfdognizfd kfy fxdibngf: " + kfyExdibngf);
                }

                //
                // All kfys brf dbldulbtfd from tif prfmbstfr sfdrft
                // bnd tif fxdibngfd nondfs in tif sbmf wby.
                //
                dbldulbtfKfys(prfMbstfrSfdrft, dlifntRfqufstfdVfrsion);
                brfbk;

            dbsf HbndsibkfMfssbgf.it_dfrtifidbtf_vfrify:
                tiis.dlifntCfrtifidbtfVfrify(nfw CfrtifidbtfVfrify(input,
                            lodblSupportfdSignAlgs, protodolVfrsion));
                brfbk;

            dbsf HbndsibkfMfssbgf.it_finisifd:
                tiis.dlifntFinisifd(
                    nfw Finisifd(protodolVfrsion, input, dipifrSuitf));
                brfbk;

            dffbult:
                tirow nfw SSLProtodolExdfption(
                        "Illfgbl sfrvfr ibndsibkf msg, " + typf);
        }

        //
        // Movf stbtf mbdiinf forwbrd if tif mfssbgf ibndling
        // dodf didn't blrfbdy do so
        //
        if (stbtf < typf) {
            if(typf == HbndsibkfMfssbgf.it_dfrtifidbtf_vfrify) {
                stbtf = typf + 2;    // bn bnnoying spfdibl dbsf
            } flsf {
                stbtf = typf;
            }
        }
    }


    /*
     * ClifntHfllo prfsfnts tif sfrvfr witi b bundi of options, to wiidi tif
     * sfrvfr rfplifs witi b SfrvfrHfllo listing tif onfs wiidi tiis sfssion
     * will usf.  If nffdfd, it blso writfs its Cfrtifidbtf plus in somf dbsfs
     * b SfrvfrKfyExdibngf mfssbgf.  It mby blso writf b CfrtifidbtfRfqufst,
     * to flidit b dlifnt dfrtifidbtf.
     *
     * All tifsf mfssbgfs brf tfrminbtfd by b SfrvfrHflloDonf mfssbgf.  In
     * most dbsfs, bll tiis dbn bf sfnt in b singlf Rfdord.
     */
    privbtf void dlifntHfllo(ClifntHfllo mfsg) tirows IOExdfption {
        if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
            mfsg.print(Systfm.out);
        }

        // Rfjfdt dlifnt initibtfd rfnfgotibtion?
        //
        // If sfrvfr sidf siould rfjfdt dlifnt-initibtfd rfnfgotibtion,
        // sfnd bn blfrt_ibndsibkf_fbilurf fbtbl blfrt, not b no_rfnfgotibtion
        // wbrning blfrt (no_rfnfgotibtion must bf b wbrning: RFC 2246).
        // no_rfnfgotibtion migit sffm morf nbturbl bt first, but wbrnings
        // brf not bppropribtf bfdbusf tif sfnding pbrty dofs not know iow
        // tif rfdfiving pbrty will bfibvf.  Tiis stbtf must bf trfbtfd bs
        // b fbtbl sfrvfr dondition.
        //
        // Tiis will not ibvf bny impbdt on sfrvfr initibtfd rfnfgotibtion.
        if (rfjfdtClifntInitibtfdRfnfgo && !isInitiblHbndsibkf &&
                stbtf != HbndsibkfMfssbgf.it_ifllo_rfqufst) {
            fbtblSE(Alfrts.blfrt_ibndsibkf_fbilurf,
                "Clifnt initibtfd rfnfgotibtion is not bllowfd");
        }

        // difdk tif sfrvfr nbmf indidbtion if rfquirfd
        SfrvfrNbmfExtfnsion dlifntHflloSNIExt = (SfrvfrNbmfExtfnsion)
                    mfsg.fxtfnsions.gft(ExtfnsionTypf.EXT_SERVER_NAME);
        if (!sniMbtdifrs.isEmpty()) {
            // wf do not rfjfdt dlifnt witiout SNI fxtfnsion
            if (dlifntHflloSNIExt != null &&
                        !dlifntHflloSNIExt.isMbtdifd(sniMbtdifrs)) {
                fbtblSE(Alfrts.blfrt_unrfdognizfd_nbmf,
                    "Unrfdognizfd sfrvfr nbmf indidbtion");
            }
        }

        // Dofs tif mfssbgf indludf sfdurity rfnfgotibtion indidbtion?
        boolfbn rfnfgotibtionIndidbtfd = fblsf;

        // difdk tif TLS_EMPTY_RENEGOTIATION_INFO_SCSV
        CipifrSuitfList dipifrSuitfs = mfsg.gftCipifrSuitfs();
        if (dipifrSuitfs.dontbins(CipifrSuitf.C_SCSV)) {
            rfnfgotibtionIndidbtfd = truf;
            if (isInitiblHbndsibkf) {
                sfdurfRfnfgotibtion = truf;
            } flsf {
                // bbort tif ibndsibkf witi b fbtbl ibndsibkf_fbilurf blfrt
                if (sfdurfRfnfgotibtion) {
                    fbtblSE(Alfrts.blfrt_ibndsibkf_fbilurf,
                        "Tif SCSV is prfsfnt in b sfdurf rfnfgotibtion");
                } flsf {
                    fbtblSE(Alfrts.blfrt_ibndsibkf_fbilurf,
                        "Tif SCSV is prfsfnt in b insfdurf rfnfgotibtion");
                }
            }
        }

        // difdk tif "rfnfgotibtion_info" fxtfnsion
        RfnfgotibtionInfoExtfnsion dlifntHflloRI = (RfnfgotibtionInfoExtfnsion)
                    mfsg.fxtfnsions.gft(ExtfnsionTypf.EXT_RENEGOTIATION_INFO);
        if (dlifntHflloRI != null) {
            rfnfgotibtionIndidbtfd = truf;
            if (isInitiblHbndsibkf) {
                // vfrify tif lfngti of tif "rfnfgotibtfd_donnfdtion" fifld
                if (!dlifntHflloRI.isEmpty()) {
                    // bbort tif ibndsibkf witi b fbtbl ibndsibkf_fbilurf blfrt
                    fbtblSE(Alfrts.blfrt_ibndsibkf_fbilurf,
                        "Tif rfnfgotibtion_info fifld is not fmpty");
                }

                sfdurfRfnfgotibtion = truf;
            } flsf {
                if (!sfdurfRfnfgotibtion) {
                    // unfxpfdtfd RI fxtfnsion for insfdurf rfnfgotibtion,
                    // bbort tif ibndsibkf witi b fbtbl ibndsibkf_fbilurf blfrt
                    fbtblSE(Alfrts.blfrt_ibndsibkf_fbilurf,
                        "Tif rfnfgotibtion_info is prfsfnt in b insfdurf " +
                        "rfnfgotibtion");
                }

                // vfrify tif dlifnt_vfrify_dbtb vbluf
                if (!Arrbys.fqubls(dlifntVfrifyDbtb,
                                dlifntHflloRI.gftRfnfgotibtfdConnfdtion())) {
                    fbtblSE(Alfrts.blfrt_ibndsibkf_fbilurf,
                        "Indorrfdt vfrify dbtb in ClifntHfllo " +
                        "rfnfgotibtion_info mfssbgf");
                }
            }
        } flsf if (!isInitiblHbndsibkf && sfdurfRfnfgotibtion) {
           // if tif donnfdtion's "sfdurf_rfnfgotibtion" flbg is sft to TRUE
           // bnd tif "rfnfgotibtion_info" fxtfnsion is not prfsfnt, bbort
           // tif ibndsibkf.
            fbtblSE(Alfrts.blfrt_ibndsibkf_fbilurf,
                        "Indonsistfnt sfdurf rfnfgotibtion indidbtion");
        }

        // if tifrf is no sfdurity rfnfgotibtion indidbtion or tif prfvious
        // ibndsibkf is insfdurf.
        if (!rfnfgotibtionIndidbtfd || !sfdurfRfnfgotibtion) {
            if (isInitiblHbndsibkf) {
                if (!bllowLfgbdyHflloMfssbgfs) {
                    // bbort tif ibndsibkf witi b fbtbl ibndsibkf_fbilurf blfrt
                    fbtblSE(Alfrts.blfrt_ibndsibkf_fbilurf,
                        "Fbilfd to nfgotibtf tif usf of sfdurf rfnfgotibtion");
                }

                // dontinuf witi lfgbdy ClifntHfllo
                if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
                    Systfm.out.println("Wbrning: No rfnfgotibtion " +
                        "indidbtion in ClifntHfllo, bllow lfgbdy ClifntHfllo");
                }
            } flsf if (!bllowUnsbffRfnfgotibtion) {
                // bbort tif ibndsibkf
                if (bdtivfProtodolVfrsion.v >= ProtodolVfrsion.TLS10.v) {
                    // rfspond witi b no_rfnfgotibtion wbrning
                    wbrningSE(Alfrts.blfrt_no_rfnfgotibtion);

                    // invblidbtf tif ibndsibkf so tibt tif dbllfr dbn
                    // disposf tiis objfdt.
                    invblidbtfd = truf;

                    // If tifrf is still unrfbd blodk in tif ibndsibkf
                    // input strfbm, it would bf trundbtfd witi tif disposbl
                    // bnd tif nfxt ibndsibkf mfssbgf will bfdomf indomplftf.
                    //
                    // Howfvfr, bddording to SSL/TLS spfdifidbtions, no morf
                    // ibndsibkf mfssbgf dould immfdibtfly follow ClifntHfllo
                    // or HflloRfqufst. But in dbsf of bny impropfr mfssbgfs,
                    // wf'd bfttfr difdk to fnsurf tifrf is no rfmbining bytfs
                    // in tif ibndsibkf input strfbm.
                    if (input.bvbilbblf() > 0) {
                        fbtblSE(Alfrts.blfrt_unfxpfdtfd_mfssbgf,
                            "ClifntHfllo followfd by bn unfxpfdtfd  " +
                            "ibndsibkf mfssbgf");
                    }

                    rfturn;
                } flsf {
                    // For SSLv3, sfnd tif ibndsibkf_fbilurf fbtbl frror.
                    // Notf tibt SSLv3 dofs not dffinf b no_rfnfgotibtion
                    // blfrt likf TLSv1. Howfvfr wf dbnnot ignorf tif mfssbgf
                    // simply, otifrwisf tif otifr sidf wbs wbiting for b
                    // rfsponsf tibt would nfvfr domf.
                    fbtblSE(Alfrts.blfrt_ibndsibkf_fbilurf,
                        "Rfnfgotibtion is not bllowfd");
                }
            } flsf {   // !isInitiblHbndsibkf && bllowUnsbffRfnfgotibtion
                // dontinuf witi unsbff rfnfgotibtion.
                if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
                    Systfm.out.println(
                            "Wbrning: dontinuf witi insfdurf rfnfgotibtion");
                }
            }
        }

        /*
         * Alwbys mbkf surf tiis fntirf rfdord ibs bffn digfstfd bfforf wf
         * stbrt fmitting output, to fnsurf dorrfdt digfsting ordfr.
         */
        input.digfstNow();

        /*
         * FIRST, donstrudt tif SfrvfrHfllo using tif options bnd prioritifs
         * from tif ClifntHfllo.  Updbtf tif (pfnding) dipifr spfd bs wf do
         * so, bnd sbvf tif dlifnt's vfrsion to protfdt bgbinst rollbbdk
         * bttbdks.
         *
         * Tifrf brf b bundi of minor tbsks ifrf, bnd onf mbjor onf: dfdiding
         * if tif siort or tif full ibndsibkf sfqufndf will bf usfd.
         */
        SfrvfrHfllo m1 = nfw SfrvfrHfllo();

        dlifntRfqufstfdVfrsion = mfsg.protodolVfrsion;

        // sflfdt b propfr protodol vfrsion.
        ProtodolVfrsion sflfdtfdVfrsion =
               sflfdtProtodolVfrsion(dlifntRfqufstfdVfrsion);
        if (sflfdtfdVfrsion == null ||
                sflfdtfdVfrsion.v == ProtodolVfrsion.SSL20Hfllo.v) {
            fbtblSE(Alfrts.blfrt_ibndsibkf_fbilurf,
                "Clifnt rfqufstfd protodol " + dlifntRfqufstfdVfrsion +
                " not fnbblfd or not supportfd");
        }

        ibndsibkfHbsi.protodolDftfrminfd(sflfdtfdVfrsion);
        sftVfrsion(sflfdtfdVfrsion);

        m1.protodolVfrsion = protodolVfrsion;

        //
        // rbndom ... sbvf dlifnt bnd sfrvfr vblufs for lbtfr usf
        // in domputing tif mbstfr sfdrft (from prf-mbstfr sfdrft)
        // bnd tifndf tif otifr drypto kfys.
        //
        // NOTE:  tiis usf of tirff inputs to gfnfrbting _fbdi_ sft
        // of dipifrs slows tiings down, but it dofs indrfbsf tif
        // sfdurity sindf fbdi donnfdtion in tif sfssion dbn iold
        // its own butifntidbtfd (bnd strong) kfys.  Onf dould mbkf
        // drfbtion of b sfssion b rbrf tiing...
        //
        dlnt_rbndom = mfsg.dlnt_rbndom;
        svr_rbndom = nfw RbndomCookif(sslContfxt.gftSfdurfRbndom());
        m1.svr_rbndom = svr_rbndom;

        sfssion = null; // forgft bbout tif durrfnt sfssion
        //
        // Hfrf wf go down fitifr of two pbtis:  (b) tif fbst onf, wifrf
        // tif dlifnt's bskfd to rfjoin bn fxisting sfssion, bnd tif sfrvfr
        // pfrmits tiis; (b) tif otifr onf, wifrf b nfw sfssion is drfbtfd.
        //
        if (mfsg.sfssionId.lfngti() != 0) {
            // dlifnt is trying to rfsumf b sfssion, lft's sff...

            SSLSfssionImpl prfvious = ((SSLSfssionContfxtImpl)sslContfxt
                        .fnginfGftSfrvfrSfssionContfxt())
                        .gft(mfsg.sfssionId.gftId());
            //
            // Cifdk if wf dbn usf tif fbst pbti, rfsuming b sfssion.  Wf
            // dbn do so iff wf ibvf b vblid rfdord for tibt sfssion, bnd
            // tif dipifr suitf for tibt sfssion wbs on tif list wiidi tif
            // dlifnt rfqufstfd, bnd if wf'rf not forgftting bny nffdfd
            // butifntidbtion on tif pbrt of tif dlifnt.
            //
            if (prfvious != null) {
                rfsumingSfssion = prfvious.isRfjoinbblf();

                if (rfsumingSfssion) {
                    ProtodolVfrsion oldVfrsion = prfvious.gftProtodolVfrsion();
                    // dbnnot rfsumf sfssion witi difffrfnt vfrsion
                    if (oldVfrsion != protodolVfrsion) {
                        rfsumingSfssion = fblsf;
                    }
                }

                // dbnnot rfsumf sfssion witi difffrfnt sfrvfr nbmf indidbtion
                if (rfsumingSfssion) {
                    List<SNISfrvfrNbmf> oldSfrvfrNbmfs =
                            prfvious.gftRfqufstfdSfrvfrNbmfs();
                    if (dlifntHflloSNIExt != null) {
                        if (!dlifntHflloSNIExt.isIdfntidbl(oldSfrvfrNbmfs)) {
                            rfsumingSfssion = fblsf;
                        }
                    } flsf if (!oldSfrvfrNbmfs.isEmpty()) {
                        rfsumingSfssion = fblsf;
                    }

                    if (!rfsumingSfssion &&
                            dfbug != null && Dfbug.isOn("ibndsibkf")) {
                        Systfm.out.println(
                            "Tif rfqufstfd sfrvfr nbmf indidbtion " +
                            "is not idfntidbl to tif prfvious onf");
                    }
                }

                if (rfsumingSfssion &&
                        (doClifntAuti == SSLEnginfImpl.dlbuti_rfquirfd)) {
                    try {
                        prfvious.gftPffrPrindipbl();
                    } dbtdi (SSLPffrUnvfrififdExdfption f) {
                        rfsumingSfssion = fblsf;
                    }
                }

                // vblidbtf subjfdt idfntity
                if (rfsumingSfssion) {
                    CipifrSuitf suitf = prfvious.gftSuitf();
                    if (suitf.kfyExdibngf == K_KRB5 ||
                        suitf.kfyExdibngf == K_KRB5_EXPORT) {
                        Prindipbl lodblPrindipbl = prfvious.gftLodblPrindipbl();

                        Subjfdt subjfdt = null;
                        try {
                            subjfdt = AddfssControllfr.doPrivilfgfd(
                                nfw PrivilfgfdExdfptionAdtion<Subjfdt>() {
                                @Ovfrridf
                                publid Subjfdt run() tirows Exdfption {
                                    rfturn
                                        Krb5Hflpfr.gftSfrvfrSubjfdt(gftAddSE());
                            }});
                        } dbtdi (PrivilfgfdAdtionExdfption f) {
                            subjfdt = null;
                            if (dfbug != null && Dfbug.isOn("sfssion")) {
                                Systfm.out.println("Attfmpt to obtbin" +
                                                " subjfdt fbilfd!");
                            }
                        }

                        if (subjfdt != null) {
                            // Eliminbtf dfpfndfndy on KfrbfrosPrindipbl
                            if (Krb5Hflpfr.isRflbtfd(subjfdt, lodblPrindipbl)) {
                                if (dfbug != null && Dfbug.isOn("sfssion"))
                                    Systfm.out.println("Subjfdt dbn" +
                                            " providf drfds for prind");
                            } flsf {
                                rfsumingSfssion = fblsf;
                                if (dfbug != null && Dfbug.isOn("sfssion"))
                                    Systfm.out.println("Subjfdt dbnnot" +
                                            " providf drfds for prind");
                            }
                        } flsf {
                            rfsumingSfssion = fblsf;
                            if (dfbug != null && Dfbug.isOn("sfssion"))
                                Systfm.out.println("Kfrbfros drfdfntibls brf" +
                                    " not prfsfnt in tif durrfnt Subjfdt;" +
                                    " difdk if " +
                                    " jbvbx.sfdurity.buti.usfSubjfdtAsCrfds" +
                                    " systfm propfrty ibs bffn sft to fblsf");
                        }
                    }
                }

                if (rfsumingSfssion) {
                    CipifrSuitf suitf = prfvious.gftSuitf();
                    // vfrify tibt tif dipifrsuitf from tif dbdifd sfssion
                    // is in tif list of dlifnt rfqufstfd dipifrsuitfs bnd
                    // wf ibvf it fnbblfd
                    if ((isNfgotibblf(suitf) == fblsf) ||
                            (mfsg.gftCipifrSuitfs().dontbins(suitf) == fblsf)) {
                        rfsumingSfssion = fblsf;
                    } flsf {
                        // fvfrytiing looks ok, sft tif dipifrsuitf
                        // tiis siould bf donf lbst wifn wf brf surf wf
                        // will rfsumf
                        sftCipifrSuitf(suitf);
                    }
                }

                if (rfsumingSfssion) {
                    sfssion = prfvious;
                    if (dfbug != null &&
                        (Dfbug.isOn("ibndsibkf") || Dfbug.isOn("sfssion"))) {
                        Systfm.out.println("%% Rfsuming " + sfssion);
                    }
                }
            }
        } // flsf dlifnt did not try to rfsumf

        //
        // If dlifnt ibsn't spfdififd b sfssion wf dbn rfsumf, stbrt b
        // nfw onf bnd dioosf its dipifr suitf bnd domprfssion options.
        // Unlfss nfw sfssion drfbtion is disbblfd for tiis donnfdtion!
        //
        if (sfssion == null) {
            if (!fnbblfNfwSfssion) {
                tirow nfw SSLExdfption("Clifnt did not rfsumf b sfssion");
            }

            supportfdCurvfs = (SupportfdElliptidCurvfsExtfnsion)
                        mfsg.fxtfnsions.gft(ExtfnsionTypf.EXT_ELLIPTIC_CURVES);

            // Wf only nffd to ibndlf tif "signbturf_blgoritim" fxtfnsion
            // for full ibndsibkfs bnd TLS 1.2 or lbtfr.
            if (protodolVfrsion.v >= ProtodolVfrsion.TLS12.v) {
                SignbturfAlgoritimsExtfnsion signAlgs =
                    (SignbturfAlgoritimsExtfnsion)mfsg.fxtfnsions.gft(
                                    ExtfnsionTypf.EXT_SIGNATURE_ALGORITHMS);
                if (signAlgs != null) {
                    Collfdtion<SignbturfAndHbsiAlgoritim> pffrSignAlgs =
                                            signAlgs.gftSignAlgoritims();
                    if (pffrSignAlgs == null || pffrSignAlgs.isEmpty()) {
                        tirow nfw SSLHbndsibkfExdfption(
                            "No pffr supportfd signbturf blgoritims");
                    }

                    Collfdtion<SignbturfAndHbsiAlgoritim>
                        supportfdPffrSignAlgs =
                            SignbturfAndHbsiAlgoritim.gftSupportfdAlgoritims(
                                                            pffrSignAlgs);
                    if (supportfdPffrSignAlgs.isEmpty()) {
                        tirow nfw SSLHbndsibkfExdfption(
                            "No supportfd signbturf bnd ibsi blgoritim " +
                            "in dommon");
                    }

                    sftPffrSupportfdSignAlgs(supportfdPffrSignAlgs);
                } // flsf, nffd to usf pffr implidit supportfd signbturf blgs
            }

            sfssion = nfw SSLSfssionImpl(protodolVfrsion, CipifrSuitf.C_NULL,
                        gftLodblSupportfdSignAlgs(),
                        sslContfxt.gftSfdurfRbndom(),
                        gftHostAddrfssSE(), gftPortSE());

            if (protodolVfrsion.v >= ProtodolVfrsion.TLS12.v) {
                if (pffrSupportfdSignAlgs != null) {
                    sfssion.sftPffrSupportfdSignbturfAlgoritims(
                            pffrSupportfdSignAlgs);
                }   // flsf, wf will sft tif implidit pffr supportfd signbturf
                    // blgoritims in dioosfCipifrSuitf()
            }

            // sft tif sfrvfr nbmf indidbtion in tif sfssion
            List<SNISfrvfrNbmf> dlifntHflloSNI =
                    Collfdtions.<SNISfrvfrNbmf>fmptyList();
            if (dlifntHflloSNIExt != null) {
                dlifntHflloSNI = dlifntHflloSNIExt.gftSfrvfrNbmfs();
            }
            sfssion.sftRfqufstfdSfrvfrNbmfs(dlifntHflloSNI);

            // sft tif ibndsibkf sfssion
            sftHbndsibkfSfssionSE(sfssion);

            // dioosf dipifr suitf bnd dorrfsponding privbtf kfy
            dioosfCipifrSuitf(mfsg);

            sfssion.sftSuitf(dipifrSuitf);
            sfssion.sftLodblPrivbtfKfy(privbtfKfy);

            // dioosfComprfssion(mfsg);
        } flsf {
            // sft tif ibndsibkf sfssion
            sftHbndsibkfSfssionSE(sfssion);
        }

        if (protodolVfrsion.v >= ProtodolVfrsion.TLS12.v) {
            ibndsibkfHbsi.sftFinisifdAlg(dipifrSuitf.prfAlg.gftPRFHbsiAlg());
        }

        m1.dipifrSuitf = dipifrSuitf;
        m1.sfssionId = sfssion.gftSfssionId();
        m1.domprfssion_mftiod = sfssion.gftComprfssion();

        if (sfdurfRfnfgotibtion) {
            // For SfrvfrHfllos tibt brf initibl ibndsibkfs, tifn tif
            // "rfnfgotibtfd_donnfdtion" fifld in "rfnfgotibtion_info"
            // fxtfnsion is of zfro lfngti.
            //
            // For SfrvfrHfllos tibt brf rfnfgotibting, tiis fifld dontbins
            // tif dondbtfnbtion of dlifnt_vfrify_dbtb bnd sfrvfr_vfrify_dbtb.
            //
            // Notf tibt for initibl ibndsibkfs, boti tif dlifntVfrifyDbtb
            // vbribblf bnd sfrvfrVfrifyDbtb vbribblf brf of zfro lfngti.
            HflloExtfnsion sfrvfrHflloRI = nfw RfnfgotibtionInfoExtfnsion(
                                        dlifntVfrifyDbtb, sfrvfrVfrifyDbtb);
            m1.fxtfnsions.bdd(sfrvfrHflloRI);
        }

        if (!sniMbtdifrs.isEmpty() && dlifntHflloSNIExt != null) {
            // Wifn rfsuming b sfssion, tif sfrvfr MUST NOT indludf b
            // sfrvfr_nbmf fxtfnsion in tif sfrvfr ifllo.
            if (!rfsumingSfssion) {
                SfrvfrNbmfExtfnsion sfrvfrHflloSNI = nfw SfrvfrNbmfExtfnsion();
                m1.fxtfnsions.bdd(sfrvfrHflloSNI);
            }
        }

        if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
            m1.print(Systfm.out);
            Systfm.out.println("Cipifr suitf:  " + sfssion.gftSuitf());
        }
        m1.writf(output);

        //
        // If wf brf rfsuming b sfssion, wf finisi writing ibndsibkf
        // mfssbgfs rigit now bnd tifn finisi.
        //
        if (rfsumingSfssion) {
            dbldulbtfConnfdtionKfys(sfssion.gftMbstfrSfdrft());
            sfndCibngfCipifrAndFinisi(fblsf);
            rfturn;
        }


        /*
         * SECOND, writf tif sfrvfr Cfrtifidbtf(s) if wf nffd to.
         *
         * NOTE:  wiilf bn "bnonymous RSA" modf is fxpliditly bllowfd by
         * tif protodol, wf dbn't support it sindf bll of tif SSL flbvors
         * dffinfd in tif protodol spfd brf fxpliditly stbtfd to rfquirf
         * using RSA dfrtifidbtfs.
         */
        if (kfyExdibngf == K_KRB5 || kfyExdibngf == K_KRB5_EXPORT) {
            // Sfrvfr dfrtifidbtfs brf omittfd for Kfrbfros dipifrs

        } flsf if ((kfyExdibngf != K_DH_ANON) && (kfyExdibngf != K_ECDH_ANON)) {
            if (dfrts == null) {
                tirow nfw RuntimfExdfption("no dfrtifidbtfs");
            }

            CfrtifidbtfMsg m2 = nfw CfrtifidbtfMsg(dfrts);

            /*
             * Sft lodbl dfrts in tif SSLSfssion, output
             * dfbug info, bnd tifn bdtublly writf to tif dlifnt.
             */
            sfssion.sftLodblCfrtifidbtfs(dfrts);
            if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
                m2.print(Systfm.out);
            }
            m2.writf(output);

            // XXX ibs somf sidf ffffdts witi OS TCP bufffring,
            // lfbvf it out for now

            // lft dlifnt vfrify dibin in tif mfbntimf...
            // output.flusi();
        } flsf {
            if (dfrts != null) {
                tirow nfw RuntimfExdfption("bnonymous kfyfxdibngf witi dfrts");
            }
        }

        /*
         * THIRD, tif SfrvfrKfyExdibngf mfssbgf ... iff it's nffdfd.
         *
         * It's usublly nffdfd unlfss tifrf's bn fndryption-dbpbblf
         * RSA dfrt, or b D-H dfrt.  Tif notbblf fxdfption is tibt
         * fxportbblf dipifrs usfd witi big RSA kfys nffd to downgrbdf
         * to usf siort RSA kfys, fvfn wifn tif kfy/dfrt fndrypts OK.
         */

        SfrvfrKfyExdibngf m3;
        switdi (kfyExdibngf) {
        dbsf K_RSA:
        dbsf K_KRB5:
        dbsf K_KRB5_EXPORT:
            // no sfrvfr kfy fxdibngf for RSA or KRB5 dipifrsuitfs
            m3 = null;
            brfbk;
        dbsf K_RSA_EXPORT:
            if (JssfJdf.gftRSAKfyLfngti(dfrts[0].gftPublidKfy()) > 512) {
                try {
                    m3 = nfw RSA_SfrvfrKfyExdibngf(
                        tfmpPublidKfy, privbtfKfy,
                        dlnt_rbndom, svr_rbndom,
                        sslContfxt.gftSfdurfRbndom());
                    privbtfKfy = tfmpPrivbtfKfy;
                } dbtdi (GfnfrblSfdurityExdfption f) {
                    tirowSSLExdfption
                        ("Error gfnfrbting RSA sfrvfr kfy fxdibngf", f);
                    m3 = null; // mbkf dompilfr ibppy
                }
            } flsf {
                // RSA_EXPORT witi siort kfy, don't nffd SfrvfrKfyExdibngf
                m3 = null;
            }
            brfbk;
        dbsf K_DHE_RSA:
        dbsf K_DHE_DSS:
            try {
                m3 = nfw DH_SfrvfrKfyExdibngf(di,
                    privbtfKfy,
                    dlnt_rbndom.rbndom_bytfs,
                    svr_rbndom.rbndom_bytfs,
                    sslContfxt.gftSfdurfRbndom(),
                    prfffrbblfSignbturfAlgoritim,
                    protodolVfrsion);
            } dbtdi (GfnfrblSfdurityExdfption f) {
                tirowSSLExdfption("Error gfnfrbting DH sfrvfr kfy fxdibngf", f);
                m3 = null; // mbkf dompilfr ibppy
            }
            brfbk;
        dbsf K_DH_ANON:
            m3 = nfw DH_SfrvfrKfyExdibngf(di, protodolVfrsion);
            brfbk;
        dbsf K_ECDHE_RSA:
        dbsf K_ECDHE_ECDSA:
        dbsf K_ECDH_ANON:
            try {
                m3 = nfw ECDH_SfrvfrKfyExdibngf(fddi,
                    privbtfKfy,
                    dlnt_rbndom.rbndom_bytfs,
                    svr_rbndom.rbndom_bytfs,
                    sslContfxt.gftSfdurfRbndom(),
                    prfffrbblfSignbturfAlgoritim,
                    protodolVfrsion);
            } dbtdi (GfnfrblSfdurityExdfption f) {
                tirowSSLExdfption(
                    "Error gfnfrbting ECDH sfrvfr kfy fxdibngf", f);
                m3 = null; // mbkf dompilfr ibppy
            }
            brfbk;
        dbsf K_ECDH_RSA:
        dbsf K_ECDH_ECDSA:
            // SfrvfrKfyExdibngf not usfd for fixfd ECDH
            m3 = null;
            brfbk;
        dffbult:
            tirow nfw RuntimfExdfption("intfrnbl frror: " + kfyExdibngf);
        }
        if (m3 != null) {
            if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
                m3.print(Systfm.out);
            }
            m3.writf(output);
        }

        //
        // FOURTH, tif CfrtifidbtfRfqufst mfssbgf.  Tif dftbils of
        // tif mfssbgf dbn bf bfffdtfd by tif kfy fxdibngf blgoritim
        // in usf.  For fxbmplf, dfrts witi fixfd Diffif-Hfllmbn kfys
        // brf only usfful witi tif DH_DSS bnd DH_RSA kfy fxdibngf
        // blgoritims.
        //
        // Nffdfd only if sfrvfr rfquirfs dlifnt to butifntidbtf sflf.
        // Illfgbl for bnonymous flbvors, so wf nffd to difdk tibt.
        //
        // CfrtifidbtfRfqufst is omittfd for Kfrbfros dipifrs
        if (doClifntAuti != SSLEnginfImpl.dlbuti_nonf &&
                kfyExdibngf != K_DH_ANON && kfyExdibngf != K_ECDH_ANON &&
                kfyExdibngf != K_KRB5 && kfyExdibngf != K_KRB5_EXPORT) {

            CfrtifidbtfRfqufst m4;
            X509Cfrtifidbtf dbCfrts[];

            Collfdtion<SignbturfAndHbsiAlgoritim> lodblSignAlgs = null;
            if (protodolVfrsion.v >= ProtodolVfrsion.TLS12.v) {
                // Wf durrfntly usf bll lodbl upportfd signbturf bnd ibsi
                // blgoritims. Howfvfr, to minimizf tif domputbtion dost
                // of rfqufstfd ibsi blgoritims, wf mby usf b rfstridtfd
                // sft of signbturf blgoritims in tif futurf.
                lodblSignAlgs = gftLodblSupportfdSignAlgs();
                if (lodblSignAlgs.isEmpty()) {
                    tirow nfw SSLHbndsibkfExdfption(
                            "No supportfd signbturf blgoritim");
                }

                Sft<String> lodblHbsiAlgs =
                    SignbturfAndHbsiAlgoritim.gftHbsiAlgoritimNbmfs(
                        lodblSignAlgs);
                if (lodblHbsiAlgs.isEmpty()) {
                    tirow nfw SSLHbndsibkfExdfption(
                            "No supportfd signbturf blgoritim");
                }
            }

            dbCfrts = sslContfxt.gftX509TrustMbnbgfr().gftAddfptfdIssufrs();
            m4 = nfw CfrtifidbtfRfqufst(dbCfrts, kfyExdibngf,
                                            lodblSignAlgs, protodolVfrsion);

            if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
                m4.print(Systfm.out);
            }
            m4.writf(output);
        }

        /*
         * FIFTH, sby SfrvfrHflloDonf.
         */
        SfrvfrHflloDonf m5 = nfw SfrvfrHflloDonf();

        if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
            m5.print(Systfm.out);
        }
        m5.writf(output);

        /*
         * Flusi bny bufffrfd mfssbgfs so tif dlifnt will sff tifm.
         * Idfblly, bll tif mfssbgfs bbovf go in b singlf nftwork lfvfl
         * mfssbgf to tif dlifnt.  Witiout big Cfrtifidbtf dibins, it's
         * going to bf tif dommon dbsf.
         */
        output.flusi();
    }

    /*
     * Cioosf dipifr suitf from bmong tiosf supportfd by dlifnt. Sfts
     * tif dipifrSuitf bnd kfyExdibngf vbribblfs.
     */
    privbtf void dioosfCipifrSuitf(ClifntHfllo mfsg) tirows IOExdfption {
        CipifrSuitfList prfffrfd;
        CipifrSuitfList proposfd;
        if (prfffrLodblCipifrSuitfs) {
            prfffrfd = gftAdtivfCipifrSuitfs();
            proposfd = mfsg.gftCipifrSuitfs();
        } flsf {
            prfffrfd = mfsg.gftCipifrSuitfs();
            proposfd = gftAdtivfCipifrSuitfs();
        }

        for (CipifrSuitf suitf : prfffrfd.dollfdtion()) {
            if (isNfgotibblf(proposfd, suitf) == fblsf) {
                dontinuf;
            }

            if (doClifntAuti == SSLEnginfImpl.dlbuti_rfquirfd) {
                if ((suitf.kfyExdibngf == K_DH_ANON) ||
                    (suitf.kfyExdibngf == K_ECDH_ANON)) {
                    dontinuf;
                }
            }
            if (trySftCipifrSuitf(suitf) == fblsf) {
                dontinuf;
            }
            rfturn;
        }
        fbtblSE(Alfrts.blfrt_ibndsibkf_fbilurf, "no dipifr suitfs in dommon");
    }

    /**
     * Sft tif givfn CipifrSuitf, if possiblf. Rfturn tif rfsult.
     * Tif dbll suddffds if tif CipifrSuitf is bvbilbblf bnd wf ibvf
     * tif nfdfssbry dfrtifidbtfs to domplftf tif ibndsibkf. Wf don't
     * difdk if tif CipifrSuitf is bdtublly fnbblfd.
     *
     * If suddfssful, tiis mftiod blso gfnfrbtfs fpifmfrbl kfys if
     * rfquirfd for tiis dipifrsuitf. Tiis mby tbkf somf timf, so tiis
     * mftiod siould only bf dbllfd if you rfblly wbnt to usf tif
     * CipifrSuitf.
     *
     * Tiis mftiod is dbllfd from dioosfCipifrSuitf() in tiis dlbss.
     */
    boolfbn trySftCipifrSuitf(CipifrSuitf suitf) {
        /*
         * If wf'rf rfsuming b sfssion wf know wf dbn
         * support tiis kfy fxdibngf blgoritim bnd in fbdt
         * ibvf blrfbdy dbdifd tif rfsult of it in
         * tif sfssion stbtf.
         */
        if (rfsumingSfssion) {
            rfturn truf;
        }

        if (suitf.isNfgotibblf() == fblsf) {
            rfturn fblsf;
        }

        // must not nfgotibtf tif obsolftfd wfbk dipifr suitfs.
        if (protodolVfrsion.v >= suitf.obsolftfd) {
            rfturn fblsf;
        }

        // must not nfgotibtf unsupportfd dipifr suitfs.
        if (protodolVfrsion.v < suitf.supportfd) {
            rfturn fblsf;
        }

        KfyExdibngf kfyExdibngf = suitf.kfyExdibngf;

        // null out bny fxisting rfffrfndfs
        privbtfKfy = null;
        dfrts = null;
        di = null;
        tfmpPrivbtfKfy = null;
        tfmpPublidKfy = null;

        Collfdtion<SignbturfAndHbsiAlgoritim> supportfdSignAlgs = null;
        if (protodolVfrsion.v >= ProtodolVfrsion.TLS12.v) {
            if (pffrSupportfdSignAlgs != null) {
                supportfdSignAlgs = pffrSupportfdSignAlgs;
            } flsf {
                SignbturfAndHbsiAlgoritim blgoritim = null;

                // wf mby optimizf tif pfrformbndf
                switdi (kfyExdibngf) {
                    // If tif nfgotibtfd kfy fxdibngf blgoritim is onf of
                    // (RSA, DHE_RSA, DH_RSA, RSA_PSK, ECDH_RSA, ECDHE_RSA),
                    // bfibvf bs if dlifnt ibd sfnt tif vbluf {sib1,rsb}.
                    dbsf K_RSA:
                    dbsf K_DHE_RSA:
                    dbsf K_DH_RSA:
                    // dbsf K_RSA_PSK:
                    dbsf K_ECDH_RSA:
                    dbsf K_ECDHE_RSA:
                        blgoritim = SignbturfAndHbsiAlgoritim.vblufOf(
                                HbsiAlgoritim.SHA1.vbluf,
                                SignbturfAlgoritim.RSA.vbluf, 0);
                        brfbk;
                    // If tif nfgotibtfd kfy fxdibngf blgoritim is onf of
                    // (DHE_DSS, DH_DSS), bfibvf bs if tif dlifnt ibd
                    // sfnt tif vbluf {sib1,dsb}.
                    dbsf K_DHE_DSS:
                    dbsf K_DH_DSS:
                        blgoritim = SignbturfAndHbsiAlgoritim.vblufOf(
                                HbsiAlgoritim.SHA1.vbluf,
                                SignbturfAlgoritim.DSA.vbluf, 0);
                        brfbk;
                    // If tif nfgotibtfd kfy fxdibngf blgoritim is onf of
                    // (ECDH_ECDSA, ECDHE_ECDSA), bfibvf bs if tif dlifnt
                    // ibd sfnt vbluf {sib1,fddsb}.
                    dbsf K_ECDH_ECDSA:
                    dbsf K_ECDHE_ECDSA:
                        blgoritim = SignbturfAndHbsiAlgoritim.vblufOf(
                                HbsiAlgoritim.SHA1.vbluf,
                                SignbturfAlgoritim.ECDSA.vbluf, 0);
                        brfbk;
                    dffbult:
                        // no pffr supportfd signbturf blgoritims
                }

                if (blgoritim == null) {
                    supportfdSignAlgs =
                        Collfdtions.<SignbturfAndHbsiAlgoritim>fmptySft();
                } flsf {
                    supportfdSignAlgs =
                        nfw ArrbyList<SignbturfAndHbsiAlgoritim>(1);
                    supportfdSignAlgs.bdd(blgoritim);
                }

                // Sfts tif pffr supportfd signbturf blgoritim to usf in KM
                // tfmporbrily.
                sfssion.sftPffrSupportfdSignbturfAlgoritims(supportfdSignAlgs);
            }
        }

        switdi (kfyExdibngf) {
        dbsf K_RSA:
            // nffd RSA dfrts for butifntidbtion
            if (sftupPrivbtfKfyAndCibin("RSA") == fblsf) {
                rfturn fblsf;
            }
            brfbk;
        dbsf K_RSA_EXPORT:
            // nffd RSA dfrts for butifntidbtion
            if (sftupPrivbtfKfyAndCibin("RSA") == fblsf) {
                rfturn fblsf;
            }

            try {
               if (JssfJdf.gftRSAKfyLfngti(dfrts[0].gftPublidKfy()) > 512) {
                    if (!sftupEpifmfrblRSAKfys(suitf.fxportbblf)) {
                        rfturn fblsf;
                    }
               }
            } dbtdi (RuntimfExdfption f) {
                // dould not dftfrminf kfylfngti, ignorf kfy
                rfturn fblsf;
            }
            brfbk;
        dbsf K_DHE_RSA:
            // nffd RSA dfrts for butifntidbtion
            if (sftupPrivbtfKfyAndCibin("RSA") == fblsf) {
                rfturn fblsf;
            }

            // gft prfffrbblf pffr signbturf blgoritim for sfrvfr kfy fxdibngf
            if (protodolVfrsion.v >= ProtodolVfrsion.TLS12.v) {
                prfffrbblfSignbturfAlgoritim =
                    SignbturfAndHbsiAlgoritim.gftPrfffrbblfAlgoritim(
                                        supportfdSignAlgs, "RSA", privbtfKfy);
                if (prfffrbblfSignbturfAlgoritim == null) {
                    rfturn fblsf;
                }
            }

            sftupEpifmfrblDHKfys(suitf.fxportbblf, privbtfKfy);
            brfbk;
        dbsf K_ECDHE_RSA:
            // nffd RSA dfrts for butifntidbtion
            if (sftupPrivbtfKfyAndCibin("RSA") == fblsf) {
                rfturn fblsf;
            }

            // gft prfffrbblf pffr signbturf blgoritim for sfrvfr kfy fxdibngf
            if (protodolVfrsion.v >= ProtodolVfrsion.TLS12.v) {
                prfffrbblfSignbturfAlgoritim =
                    SignbturfAndHbsiAlgoritim.gftPrfffrbblfAlgoritim(
                                        supportfdSignAlgs, "RSA", privbtfKfy);
                if (prfffrbblfSignbturfAlgoritim == null) {
                    rfturn fblsf;
                }
            }

            if (sftupEpifmfrblECDHKfys() == fblsf) {
                rfturn fblsf;
            }
            brfbk;
        dbsf K_DHE_DSS:
            // gft prfffrbblf pffr signbturf blgoritim for sfrvfr kfy fxdibngf
            if (protodolVfrsion.v >= ProtodolVfrsion.TLS12.v) {
                prfffrbblfSignbturfAlgoritim =
                    SignbturfAndHbsiAlgoritim.gftPrfffrbblfAlgoritim(
                                                supportfdSignAlgs, "DSA");
                if (prfffrbblfSignbturfAlgoritim == null) {
                    rfturn fblsf;
                }
            }

            // nffd DSS dfrts for butifntidbtion
            if (sftupPrivbtfKfyAndCibin("DSA") == fblsf) {
                rfturn fblsf;
            }

            sftupEpifmfrblDHKfys(suitf.fxportbblf, privbtfKfy);
            brfbk;
        dbsf K_ECDHE_ECDSA:
            // gft prfffrbblf pffr signbturf blgoritim for sfrvfr kfy fxdibngf
            if (protodolVfrsion.v >= ProtodolVfrsion.TLS12.v) {
                prfffrbblfSignbturfAlgoritim =
                    SignbturfAndHbsiAlgoritim.gftPrfffrbblfAlgoritim(
                                            supportfdSignAlgs, "ECDSA");
                if (prfffrbblfSignbturfAlgoritim == null) {
                    rfturn fblsf;
                }
            }

            // nffd EC dfrt signfd using EC
            if (sftupPrivbtfKfyAndCibin("EC_EC") == fblsf) {
                rfturn fblsf;
            }
            if (sftupEpifmfrblECDHKfys() == fblsf) {
                rfturn fblsf;
            }
            brfbk;
        dbsf K_ECDH_RSA:
            // nffd EC dfrt signfd using RSA
            if (sftupPrivbtfKfyAndCibin("EC_RSA") == fblsf) {
                rfturn fblsf;
            }
            sftupStbtidECDHKfys();
            brfbk;
        dbsf K_ECDH_ECDSA:
            // nffd EC dfrt signfd using EC
            if (sftupPrivbtfKfyAndCibin("EC_EC") == fblsf) {
                rfturn fblsf;
            }
            sftupStbtidECDHKfys();
            brfbk;
        dbsf K_KRB5:
        dbsf K_KRB5_EXPORT:
            // nffd Kfrbfros Kfy
            if (!sftupKfrbfrosKfys()) {
                rfturn fblsf;
            }
            brfbk;
        dbsf K_DH_ANON:
            // no dfrts nffdfd for bnonymous
            sftupEpifmfrblDHKfys(suitf.fxportbblf, null);
            brfbk;
        dbsf K_ECDH_ANON:
            // no dfrts nffdfd for bnonymous
            if (sftupEpifmfrblECDHKfys() == fblsf) {
                rfturn fblsf;
            }
            brfbk;
        dffbult:
            // intfrnbl frror, unknown kfy fxdibngf
            tirow nfw RuntimfExdfption("Unrfdognizfd dipifrSuitf: " + suitf);
        }
        sftCipifrSuitf(suitf);

        // sft tif pffr implidit supportfd signbturf blgoritims
        if (protodolVfrsion.v >= ProtodolVfrsion.TLS12.v) {
            if (pffrSupportfdSignAlgs == null) {
                sftPffrSupportfdSignAlgs(supportfdSignAlgs);
                // wf ibd blrfby updbtf tif sfssion
            }
        }
        rfturn truf;
    }

    /*
     * Gft somf "fpifmfrbl" RSA kfys for tiis dontfxt. Tiis mfbns
     * gfnfrbting tifm if it's not blrfbdy bffn donf.
     *
     * Notf tibt wf durrfntly do not implfmfnt bny dipifrsuitfs tibt usf
     * strong fpifmfrbl RSA. (Wf do not support tif EXPORT1024 dipifrsuitfs
     * bnd stbndbrd RSA dipifrsuitfs proiibit fpifmfrbl modf for somf rfbson)
     * Tiis mfbns tibt fxport is blwbys truf bnd 512 bit kfys brf gfnfrbtfd.
     */
    privbtf boolfbn sftupEpifmfrblRSAKfys(boolfbn fxport) {
        KfyPbir kp = sslContfxt.gftEpifmfrblKfyMbnbgfr().
                        gftRSAKfyPbir(fxport, sslContfxt.gftSfdurfRbndom());
        if (kp == null) {
            rfturn fblsf;
        } flsf {
            tfmpPublidKfy = kp.gftPublid();
            tfmpPrivbtfKfy = kp.gftPrivbtf();
            rfturn truf;
        }
    }

    /*
     * Adquirf somf "fpifmfrbl" Diffif-Hfllmbn  kfys for tiis ibndsibkf.
     * Wf don't rfusf tifsf, for improvfd forwbrd sfdrfdy.
     */
    privbtf void sftupEpifmfrblDHKfys(boolfbn fxport, Kfy kfy) {
        /*
         * 768 bits fpifmfrbl DH privbtf kfys wfrf usfd to bf usfd in
         * SfrvfrKfyExdibngf fxdfpt tibt fxportbblf dipifrs mbx out bt 512
         * bits modulus vblufs. Wf still bdifrf to tiis bfibvior in lfgbdy
         * modf (systfm propfrty "jdk.tls.fpifmfrblDHKfySizf" is dffinfd
         * bs "lfgbdy").
         *
         * Old JDK (JDK 7 bnd prfvious) rflfbsfs don't support DH kfys biggfr
         * tibn 1024 bits. Wf ibvf to donsidfr tif dompbtibility rfquirfmfnt.
         * 1024 bits DH kfy is blwbys usfd for non-fxportbblf dipifr suitfs
         * in dffbult modf (systfm propfrty "jdk.tls.fpifmfrblDHKfySizf"
         * is not dffinfd).
         *
         * Howfvfr, if bpplidbtions wbnt morf strongfr strfngti, sftting
         * systfm propfrty "jdk.tls.fpifmfrblDHKfySizf" to "mbtdifd"
         * is b workbround to usf fpifmfrbl DH kfy wiidi sizf mbtdifs tif
         * dorrfsponding butifntidbtion kfy. For fxbmplf, if tif publid kfy
         * sizf of bn butifntidbtion dfrtifidbtf is 2048 bits, tifn tif
         * fpifmfrbl DH kfy sizf siould bf 2048 bits bddordingly unlfss
         * tif dipifr suitf is fxportbblf.  Tiis kfy sizing sdifmf kffps
         * tif dryptogrbpiid strfngti donsistfnt bftwffn butifntidbtion
         * kfys bnd kfy-fxdibngf kfys.
         *
         * Applidbtions mby blso wbnt to dustomizf tif fpifmfrbl DH kfy sizf
         * to b fixfd lfngti for non-fxportbblf dipifr suitfs. Tiis dbn bf
         * bpprobdifd by sftting systfm propfrty "jdk.tls.fpifmfrblDHKfySizf"
         * to b vblid positivf intfgfr bftwffn 1024 bnd 2048 bits, indlusivf.
         *
         * Notf tibt tif minimum bddfptbblf kfy sizf is 1024 bits fxdfpt
         * fxportbblf dipifr suitfs or lfgbdy modf.
         *
         * Notf tibt tif mbximum bddfptbblf kfy sizf is 2048 bits bfdbusf
         * DH kfys biggfr tibn 2048 brf not blwbys supportfd by undfrlying
         * JCE providfrs.
         *
         * Notf tibt pfr RFC 2246, tif kfy sizf limit of DH is 512 bits for
         * fxportbblf dipifr suitfs.  Bfdbusf of tif wfbknfss, fxportbblf
         * dipifr suitfs brf dfprfdbtfd sindf TLS v1.1 bnd tify brf not
         * fnbblfd by dffbult in Orbdlf providfr. Tif lfgbdy bfibvior is
         * rfsfrvfd bnd 512 bits DH kfy is blwbys usfd for fxportbblf
         * dipifr suitfs.
         */
        int kfySizf = fxport ? 512 : 1024;           // dffbult modf
        if (!fxport) {
            if (usfLfgbdyEpifmfrblDHKfys) {          // lfgbdy modf
                kfySizf = 768;
            } flsf if (usfSmbrtEpifmfrblDHKfys) {    // mbtdifd modf
                if (kfy != null) {
                    int ks = KfyUtil.gftKfySizf(kfy);
                    // Notf tibt SunJCE providfr only supports 2048 bits DH
                    // kfys biggfr tibn 1024.  Plfbsf DON'T usf vbluf otifr
                    // tibn 1024 bnd 2048 bt prfsfnt.  Wf mby improvf tif
                    // undfrlying providfrs bnd kfy sizf ifrf in tif futurf.
                    //
                    // kfySizf = ks <= 1024 ? 1024 : (ks >= 2048 ? 2048 : ks);
                    kfySizf = ks <= 1024 ? 1024 : 2048;
                } // Otifrwisf, bnonymous dipifr suitfs, 1024-bit is usfd.
            } flsf if (dustomizfdDHKfySizf > 0) {    // dustomizfd modf
                kfySizf = dustomizfdDHKfySizf;
            }
        }

        di = nfw DHCrypt(kfySizf, sslContfxt.gftSfdurfRbndom());
    }

    // Sftup tif fpifmfrbl ECDH pbrbmftfrs.
    // If wf dbnnot dontinuf bfdbusf wf do not support bny of tif durvfs tibt
    // tif dlifnt rfqufstfd, rfturn fblsf. Otifrwisf (bll is wfll), rfturn truf.
    privbtf boolfbn sftupEpifmfrblECDHKfys() {
        int indfx = -1;
        if (supportfdCurvfs != null) {
            // if tif dlifnt sfnt tif supportfd durvfs fxtfnsion, pidk tif
            // first onf tibt wf support;
            for (int durvfId : supportfdCurvfs.durvfIds()) {
                if (SupportfdElliptidCurvfsExtfnsion.isSupportfd(durvfId)) {
                    indfx = durvfId;
                    brfbk;
                }
            }
            if (indfx < 0) {
                // no mbtdi found, dbnnot usf tiis dipifrsuitf
                rfturn fblsf;
            }
        } flsf {
            // pidk our prfffrfndf
            indfx = SupportfdElliptidCurvfsExtfnsion.DEFAULT.durvfIds()[0];
        }
        String oid = SupportfdElliptidCurvfsExtfnsion.gftCurvfOid(indfx);
        fddi = nfw ECDHCrypt(oid, sslContfxt.gftSfdurfRbndom());
        rfturn truf;
    }

    privbtf void sftupStbtidECDHKfys() {
        // don't nffd to difdk wiftifr tif durvf is supportfd, blrfbdy donf
        // in sftupPrivbtfKfyAndCibin().
        fddi = nfw ECDHCrypt(privbtfKfy, dfrts[0].gftPublidKfy());
    }

    /**
     * Rftrifvf tif sfrvfr kfy bnd dfrtifidbtf for tif spfdififd blgoritim
     * from tif KfyMbnbgfr bnd sft tif instbndf vbribblfs.
     *
     * @rfturn truf if suddfssful, fblsf if not bvbilbblf or invblid
     */
    privbtf boolfbn sftupPrivbtfKfyAndCibin(String blgoritim) {
        X509ExtfndfdKfyMbnbgfr km = sslContfxt.gftX509KfyMbnbgfr();
        String blibs;
        if (donn != null) {
            blibs = km.dioosfSfrvfrAlibs(blgoritim, null, donn);
        } flsf {
            blibs = km.dioosfEnginfSfrvfrAlibs(blgoritim, null, fnginf);
        }
        if (blibs == null) {
            rfturn fblsf;
        }
        PrivbtfKfy tfmpPrivbtfKfy = km.gftPrivbtfKfy(blibs);
        if (tfmpPrivbtfKfy == null) {
            rfturn fblsf;
        }
        X509Cfrtifidbtf[] tfmpCfrts = km.gftCfrtifidbtfCibin(blibs);
        if ((tfmpCfrts == null) || (tfmpCfrts.lfngti == 0)) {
            rfturn fblsf;
        }
        String kfyAlgoritim = blgoritim.split("_")[0];
        PublidKfy publidKfy = tfmpCfrts[0].gftPublidKfy();
        if ((tfmpPrivbtfKfy.gftAlgoritim().fqubls(kfyAlgoritim) == fblsf)
                || (publidKfy.gftAlgoritim().fqubls(kfyAlgoritim) == fblsf)) {
            rfturn fblsf;
        }
        // For ECC dfrts, difdk wiftifr wf support tif EC dombin pbrbmftfrs.
        // If tif dlifnt sfnt b SupportfdElliptidCurvfs ClifntHfllo fxtfnsion,
        // difdk bgbinst tibt too.
        if (kfyAlgoritim.fqubls("EC")) {
            if (publidKfy instbndfof ECPublidKfy == fblsf) {
                rfturn fblsf;
            }
            ECPbrbmftfrSpfd pbrbms = ((ECPublidKfy)publidKfy).gftPbrbms();
            int indfx = SupportfdElliptidCurvfsExtfnsion.gftCurvfIndfx(pbrbms);
            if (SupportfdElliptidCurvfsExtfnsion.isSupportfd(indfx) == fblsf) {
                rfturn fblsf;
            }
            if ((supportfdCurvfs != null) && !supportfdCurvfs.dontbins(indfx)) {
                rfturn fblsf;
            }
        }
        tiis.privbtfKfy = tfmpPrivbtfKfy;
        tiis.dfrts = tfmpCfrts;
        rfturn truf;
    }

    /**
     * Rftrifvf tif Kfrbfros kfy for tif spfdififd sfrvfr prindipbl
     * from tif JAAS donfigurbtion filf.
     *
     * @rfturn truf if suddfssful, fblsf if not bvbilbblf or invblid
     */
    privbtf boolfbn sftupKfrbfrosKfys() {
        if (sfrvidfCrfds != null) {
            rfturn truf;
        }
        try {
            finbl AddfssControlContfxt bdd = gftAddSE();
            sfrvidfCrfds = AddfssControllfr.doPrivilfgfd(
                // Eliminbtf dfpfndfndy on KfrbfrosKfy
                nfw PrivilfgfdExdfptionAdtion<Objfdt>() {
                @Ovfrridf
                publid Objfdt run() tirows Exdfption {
                    // gft kfrbfros kfy for tif dffbult prindipbl
                    rfturn Krb5Hflpfr.gftSfrvidfCrfds(bdd);
                        }});

            // difdk pfrmission to bddfss bnd usf tif sfdrft kfy of tif
            // Kfrbfrizfd "iost" sfrvidf
            if (sfrvidfCrfds != null) {
                if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
                    Systfm.out.println("Using Kfrbfros drfds");
                }
                String sfrvfrPrindipbl =
                        Krb5Hflpfr.gftSfrvfrPrindipblNbmf(sfrvidfCrfds);
                if (sfrvfrPrindipbl != null) {
                    // Wifn sfrvidf is bound, wf difdk ASAP. Otifrwisf,
                    // will difdk bftfr dlifnt rfqufst is rfdfivfd
                    // in in Kfrbfros ClifntKfyExdibngf
                    SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
                    try {
                        if (sm != null) {
                            // Eliminbtf dfpfndfndy on SfrvidfPfrmission
                            sm.difdkPfrmission(Krb5Hflpfr.gftSfrvidfPfrmission(
                                    sfrvfrPrindipbl, "bddfpt"), bdd);
                        }
                    } dbtdi (SfdurityExdfption sf) {
                        sfrvidfCrfds = null;
                        // Do not dfstroy kfys. Will bfffdt Subjfdt
                        if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
                            Systfm.out.println("Pfrmission to bddfss Kfrbfros"
                                    + " sfdrft kfy dfnifd");
                        }
                        rfturn fblsf;
                    }
                }
            }
            rfturn sfrvidfCrfds != null;
        } dbtdi (PrivilfgfdAdtionExdfption f) {
            // Likfly fxdfption ifrf is LoginExdfptin
            if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
                Systfm.out.println("Attfmpt to obtbin Kfrbfros kfy fbilfd: "
                                + f.toString());
            }
            rfturn fblsf;
        }
    }

    /*
     * For Kfrbfros dipifrs, tif prfmbstfr sfdrft is fndryptfd using
     * tif sfssion kfy. Sff RFC 2712.
     */
    privbtf SfdrftKfy dlifntKfyExdibngf(KfrbfrosClifntKfyExdibngf mfsg)
        tirows IOExdfption {

        if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
            mfsg.print(Systfm.out);
        }

        // Rfdord tif prindipbls involvfd in fxdibngf
        sfssion.sftPffrPrindipbl(mfsg.gftPffrPrindipbl());
        sfssion.sftLodblPrindipbl(mfsg.gftLodblPrindipbl());

        bytf[] b = mfsg.gftUnfndryptfdPrfMbstfrSfdrft();
        rfturn nfw SfdrftKfySpfd(b, "TlsPrfmbstfrSfdrft");
    }

    /*
     * Diffif Hfllmbn kfy fxdibngf is usfd wifn tif sfrvfr prfsfntfd
     * D-H pbrbmftfrs in its dfrtifidbtf (signfd using RSA or DSS/DSA),
     * or flsf tif sfrvfr prfsfntfd no dfrtifidbtf but sfnt D-H pbrbms
     * in b SfrvfrKfyExdibngf mfssbgf.  Usf of D-H is spfdififd by tif
     * dipifr suitf diosfn.
     *
     * Tif mfssbgf optionblly dontbins tif dlifnt's D-H publid kfy (if
     * it wbsn't not sfnt in b dlifnt dfrtifidbtf).  As blwbys witi D-H,
     * if b dlifnt bnd b sfrvfr ibvf fbdi otifr's D-H publid kfys bnd
     * tify usf dommon blgoritim pbrbmftfrs, tify ibvf b sibrfd kfy
     * tibt's dfrivfd vib tif D-H dbldulbtion.  Tibt kfy bfdomfs tif
     * prf-mbstfr sfdrft.
     */
    privbtf SfdrftKfy dlifntKfyExdibngf(DHClifntKfyExdibngf mfsg)
            tirows IOExdfption {

        if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
            mfsg.print(Systfm.out);
        }
        rfturn di.gftAgrffdSfdrft(mfsg.gftClifntPublidKfy(), fblsf);
    }

    privbtf SfdrftKfy dlifntKfyExdibngf(ECDHClifntKfyExdibngf mfsg)
            tirows IOExdfption {

        if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
            mfsg.print(Systfm.out);
        }
        rfturn fddi.gftAgrffdSfdrft(mfsg.gftEndodfdPoint());
    }

    /*
     * Clifnt wrotf b mfssbgf to vfrify tif dfrtifidbtf it sfnt fbrlifr.
     *
     * Notf tibt tiis dfrtifidbtf isn't involvfd in kfy fxdibngf.  Clifnt
     * butifntidbtion mfssbgfs brf indludfd in tif difdksums usfd to
     * vblidbtf tif ibndsibkf (f.g. Finisifd mfssbgfs).  Otifr tibn tibt,
     * tif _fxbdt_ idfntity of tif dlifnt is lfss fundbmfntbl to protodol
     * sfdurity tibn its rolf in sflfdting kfys vib tif prf-mbstfr sfdrft.
     */
    privbtf void dlifntCfrtifidbtfVfrify(CfrtifidbtfVfrify mfsg)
            tirows IOExdfption {

        if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
            mfsg.print(Systfm.out);
        }

        if (protodolVfrsion.v >= ProtodolVfrsion.TLS12.v) {
            SignbturfAndHbsiAlgoritim signAlg =
                mfsg.gftPrfffrbblfSignbturfAlgoritim();
            if (signAlg == null) {
                tirow nfw SSLHbndsibkfExdfption(
                        "Illfgbl CfrtifidbtfVfrify mfssbgf");
            }

            String ibsiAlg =
                SignbturfAndHbsiAlgoritim.gftHbsiAlgoritimNbmf(signAlg);
            if (ibsiAlg == null || ibsiAlg.lfngti() == 0) {
                tirow nfw SSLHbndsibkfExdfption(
                        "No supportfd ibsi blgoritim");
            }
        }

        try {
            PublidKfy publidKfy =
                sfssion.gftPffrCfrtifidbtfs()[0].gftPublidKfy();

            boolfbn vblid = mfsg.vfrify(protodolVfrsion, ibndsibkfHbsi,
                                        publidKfy, sfssion.gftMbstfrSfdrft());
            if (vblid == fblsf) {
                fbtblSE(Alfrts.blfrt_bbd_dfrtifidbtf,
                            "dfrtifidbtf vfrify mfssbgf signbturf frror");
            }
        } dbtdi (GfnfrblSfdurityExdfption f) {
            fbtblSE(Alfrts.blfrt_bbd_dfrtifidbtf,
                "dfrtifidbtf vfrify formbt frror", f);
        }

        // rfsft tif flbg for dlifntCfrtifidbtfVfrify mfssbgf
        nffdClifntVfrify = fblsf;
    }


    /*
     * Clifnt writfs "finisifd" bt tif fnd of its ibndsibkf, bftfr dipifr
     * spfd is dibngfd.   Wf vfrify it bnd tifn sfnd ours.
     *
     * Wifn wf'rf rfsuming b sfssion, wf'll ibvf blrfbdy sfnt our own
     * Finisifd mfssbgf so just tif vfrifidbtion is nffdfd.
     */
    privbtf void dlifntFinisifd(Finisifd mfsg) tirows IOExdfption {
        if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
            mfsg.print(Systfm.out);
        }

        /*
         * Vfrify if dlifnt did sfnd tif dfrtifidbtf wifn dlifnt
         * butifntidbtion wbs rfquirfd, otifrwisf sfrvfr siould not prodffd
         */
        if (doClifntAuti == SSLEnginfImpl.dlbuti_rfquirfd) {
           // gft X500Prindipbl of tif fnd-fntity dfrtifidbtf for X509-bbsfd
           // dipifrsuitfs, or Kfrbfros prindipbl for Kfrbfros dipifrsuitfs
           sfssion.gftPffrPrindipbl();
        }

        /*
         * Vfrify if dlifnt did sfnd dlifntCfrtifidbtfVfrify mfssbgf following
         * tif dlifnt Cfrtifidbtf, otifrwisf sfrvfr siould not prodffd
         */
        if (nffdClifntVfrify) {
                fbtblSE(Alfrts.blfrt_ibndsibkf_fbilurf,
                        "dlifnt did not sfnd dfrtifidbtf vfrify mfssbgf");
        }

        /*
         * Vfrify tif dlifnt's mfssbgf witi tif "bfforf" digfst of mfssbgfs,
         * bnd forgft bbout dontinuing to usf tibt digfst.
         */
        boolfbn vfrififd = mfsg.vfrify(ibndsibkfHbsi, Finisifd.CLIENT,
            sfssion.gftMbstfrSfdrft());

        if (!vfrififd) {
            fbtblSE(Alfrts.blfrt_ibndsibkf_fbilurf,
                        "dlifnt 'finisifd' mfssbgf dofsn't vfrify");
            // NOTREACHED
        }

        /*
         * sbvf dlifnt vfrify dbtb for sfdurf rfnfgotibtion
         */
        if (sfdurfRfnfgotibtion) {
            dlifntVfrifyDbtb = mfsg.gftVfrifyDbtb();
        }

        /*
         * OK, it vfrififd.  If wf'rf doing tif full ibndsibkf, bdd tibt
         * "Finisifd" mfssbgf to tif ibsi of ibndsibkf mfssbgfs, tifn sfnd
         * tif dibngf_dipifr_spfd bnd Finisifd mfssbgf.
         */
        if (!rfsumingSfssion) {
            input.digfstNow();
            sfndCibngfCipifrAndFinisi(truf);
        }

        /*
         * Updbtf tif sfssion dbdif only bftfr tif ibndsibkf domplftfd, flsf
         * wf'rf opfn to bn bttbdk bgbinst b pbrtiblly domplftfd ibndsibkf.
         */
        sfssion.sftLbstAddfssfdTimf(Systfm.durrfntTimfMillis());
        if (!rfsumingSfssion && sfssion.isRfjoinbblf()) {
            ((SSLSfssionContfxtImpl)sslContfxt.fnginfGftSfrvfrSfssionContfxt())
                .put(sfssion);
            if (dfbug != null && Dfbug.isOn("sfssion")) {
                Systfm.out.println(
                    "%% Cbdifd sfrvfr sfssion: " + sfssion);
            }
        } flsf if (!rfsumingSfssion &&
                dfbug != null && Dfbug.isOn("sfssion")) {
            Systfm.out.println(
                "%% Didn't dbdif non-rfsumbblf sfrvfr sfssion: "
                + sfssion);
        }
    }

    /*
     * Computf finisifd mfssbgf witi tif "sfrvfr" digfst (bnd tifn forgft
     * bbout tibt digfst, it dbn't bf usfd bgbin).
     */
    privbtf void sfndCibngfCipifrAndFinisi(boolfbn finisifdTbg)
            tirows IOExdfption {

        output.flusi();

        Finisifd mfsg = nfw Finisifd(protodolVfrsion, ibndsibkfHbsi,
            Finisifd.SERVER, sfssion.gftMbstfrSfdrft(), dipifrSuitf);

        /*
         * Sfnd tif dibngf_dipifr_spfd rfdord; tifn our Finisifd ibndsibkf
         * mfssbgf will bf tif lbst ibndsibkf mfssbgf.  Flusi, bnd now wf
         * brf rfbdy for bpplidbtion dbtb!!
         */
        sfndCibngfCipifrSpfd(mfsg, finisifdTbg);

        /*
         * sbvf sfrvfr vfrify dbtb for sfdurf rfnfgotibtion
         */
        if (sfdurfRfnfgotibtion) {
            sfrvfrVfrifyDbtb = mfsg.gftVfrifyDbtb();
        }

        /*
         * Updbtf stbtf mbdiinf so dlifnt MUST sfnd 'finisifd' nfxt
         * Tif updbtf siould only tbkf plbdf if it is not in tif fbst
         * ibndsibkf modf sindf tif sfrvfr ibs to wbit for b finisifd
         * mfssbgf from tif dlifnt.
         */
        if (finisifdTbg) {
            stbtf = HbndsibkfMfssbgf.it_finisifd;
        }
    }


    /*
     * Rfturns b HflloRfqufst mfssbgf to kidkstbrt rfnfgotibtions
     */
    @Ovfrridf
    HbndsibkfMfssbgf gftKidkstbrtMfssbgf() {
        rfturn nfw HflloRfqufst();
    }


    /*
     * Fbult dftfdtfd during ibndsibkf.
     */
    @Ovfrridf
    void ibndsibkfAlfrt(bytf dfsdription) tirows SSLProtodolExdfption {

        String mfssbgf = Alfrts.blfrtDfsdription(dfsdription);

        if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
            Systfm.out.println("SSL -- ibndsibkf blfrt:  "
                + mfssbgf);
        }

        /*
         * It's ok to gft b no_dfrtifidbtf blfrt from b dlifnt of wiidi
         * wf *rfqufstfd* butifntidbtion informbtion.
         * Howfvfr, if wf *rfquirfd* it, tifn tiis is not bddfptbblf.
         *
         * Anyonf dblling gftPffrCfrtifidbtfs() on tif
         * sfssion will gft bn SSLPffrUnvfrififdExdfption.
         */
        if ((dfsdription == Alfrts.blfrt_no_dfrtifidbtf) &&
                (doClifntAuti == SSLEnginfImpl.dlbuti_rfqufstfd)) {
            rfturn;
        }

        tirow nfw SSLProtodolExdfption("ibndsibkf blfrt: " + mfssbgf);
    }

    /*
     * RSA kfy fxdibngf is normblly usfd.  Tif dlifnt fndrypts b "prf-mbstfr
     * sfdrft" witi tif sfrvfr's publid kfy, from tif Cfrtifidbtf (or flsf
     * SfrvfrKfyExdibngf) mfssbgf tibt wbs sfnt to it by tif sfrvfr.  Tibt's
     * dfdryptfd using tif privbtf kfy bfforf wf gft ifrf.
     */
    privbtf SfdrftKfy dlifntKfyExdibngf(RSAClifntKfyExdibngf mfsg)
            tirows IOExdfption {

        if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
            mfsg.print(Systfm.out);
        }
        rfturn mfsg.prfMbstfr;
    }

    /*
     * Vfrify tif dfrtifidbtf sfnt by tif dlifnt. Wf'll only gft onf if wf
     * sfnt b CfrtifidbtfRfqufst to rfqufst dlifnt butifntidbtion. If wf
     * brf in TLS modf, tif dlifnt mby sfnd b mfssbgf witi no dfrtifidbtfs
     * to indidbtf it dofs not ibvf bn bppropribtf dibin. (In SSLv3 modf,
     * it would sfnd b no dfrtifidbtf blfrt).
     */
    privbtf void dlifntCfrtifidbtf(CfrtifidbtfMsg mfsg) tirows IOExdfption {
        if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
            mfsg.print(Systfm.out);
        }

        X509Cfrtifidbtf[] pffrCfrts = mfsg.gftCfrtifidbtfCibin();

        if (pffrCfrts.lfngti == 0) {
            /*
             * If tif dlifnt butifntidbtion is only *REQUESTED* (f.g.
             * not *REQUIRED*, tiis is bn bddfptbblf dondition.)
             */
            if (doClifntAuti == SSLEnginfImpl.dlbuti_rfqufstfd) {
                rfturn;
            } flsf {
                fbtblSE(Alfrts.blfrt_bbd_dfrtifidbtf,
                    "null dfrt dibin");
            }
        }

        // bsk tif trust mbnbgfr to vfrify tif dibin
        X509TrustMbnbgfr tm = sslContfxt.gftX509TrustMbnbgfr();

        try {
            // find out tif typfs of dlifnt butifntidbtion usfd
            PublidKfy kfy = pffrCfrts[0].gftPublidKfy();
            String kfyAlgoritim = kfy.gftAlgoritim();
            String butiTypf;
            if (kfyAlgoritim.fqubls("RSA")) {
                butiTypf = "RSA";
            } flsf if (kfyAlgoritim.fqubls("DSA")) {
                butiTypf = "DSA";
            } flsf if (kfyAlgoritim.fqubls("EC")) {
                butiTypf = "EC";
            } flsf {
                // unknown publid kfy typf
                butiTypf = "UNKNOWN";
            }

            if (tm instbndfof X509ExtfndfdTrustMbnbgfr) {
                if (donn != null) {
                    ((X509ExtfndfdTrustMbnbgfr)tm).difdkClifntTrustfd(
                        pffrCfrts.dlonf(),
                        butiTypf,
                        donn);
                } flsf {
                    ((X509ExtfndfdTrustMbnbgfr)tm).difdkClifntTrustfd(
                        pffrCfrts.dlonf(),
                        butiTypf,
                        fnginf);
                }
            } flsf {
                // Unlikfly to ibppfn, bfdbusf wf ibvf wrbppfd tif old
                // X509TrustMbnbgfr witi tif nfw X509ExtfndfdTrustMbnbgfr.
                tirow nfw CfrtifidbtfExdfption(
                    "Impropfr X509TrustMbnbgfr implfmfntbtion");
            }
        } dbtdi (CfrtifidbtfExdfption f) {
            // Tiis will tirow bn fxdfption, so indludf tif originbl frror.
            fbtblSE(Alfrts.blfrt_dfrtifidbtf_unknown, f);
        }
        // sft tif flbg for dlifntCfrtifidbtfVfrify mfssbgf
        nffdClifntVfrify = truf;

        sfssion.sftPffrCfrtifidbtfs(pffrCfrts);
    }
}

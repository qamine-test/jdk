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
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.AlgoritimConstrbints;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;

import jbvbx.drypto.*;
import jbvbx.drypto.spfd.*;

import jbvbx.nft.ssl.*;
import sun.misd.HfxDumpEndodfr;

import sun.sfdurity.intfrnbl.spfd.*;
import sun.sfdurity.intfrnbl.intfrfbdfs.TlsMbstfrSfdrft;

import sun.sfdurity.ssl.HbndsibkfMfssbgf.*;
import sun.sfdurity.ssl.CipifrSuitf.*;

import stbtid sun.sfdurity.ssl.CipifrSuitf.PRF.*;
import stbtid sun.sfdurity.ssl.CipifrSuitf.CipifrTypf.*;

/**
 * Hbndsibkfr ... prodfssfs ibndsibkf rfdords from bn SSL V3.0
 * dbtb strfbm, ibndling bll tif dftbils of tif ibndsibkf protodol.
 *
 * Notf tibt tif rfbl protodol work is donf in two subdlbssfs, tif  bbsf
 * dlbss just providfs tif dontrol flow bnd kfy gfnfrbtion frbmfwork.
 *
 * @butior Dbvid Brownfll
 */
bbstrbdt dlbss Hbndsibkfr {

    // protodol vfrsion bfing fstbblisifd using tiis Hbndsibkfr
    ProtodolVfrsion protodolVfrsion;

    // tif durrfntly bdtivf protodol vfrsion during b rfnfgotibtion
    ProtodolVfrsion     bdtivfProtodolVfrsion;

    // sfdurity pbrbmftfrs for sfdurf rfnfgotibtion.
    boolfbn             sfdurfRfnfgotibtion;
    bytf[]              dlifntVfrifyDbtb;
    bytf[]              sfrvfrVfrifyDbtb;

    // Is it bn initibl nfgotibtion  or b rfnfgotibtion?
    boolfbn                     isInitiblHbndsibkf;

    // List of fnbblfd protodols
    privbtf ProtodolList        fnbblfdProtodols;

    // List of fnbblfd CipifrSuitfs
    privbtf CipifrSuitfList     fnbblfdCipifrSuitfs;

    // Tif fndpoint idfntifidbtion protodol
    String              idfntifidbtionProtodol;

    // Tif dryptogrbpiid blgoritim donstrbints
    privbtf AlgoritimConstrbints    blgoritimConstrbints = null;

    // Lodbl supportfd signbturf bnd blgoritims
    Collfdtion<SignbturfAndHbsiAlgoritim> lodblSupportfdSignAlgs;

    // Pffr supportfd signbturf bnd blgoritims
    Collfdtion<SignbturfAndHbsiAlgoritim> pffrSupportfdSignAlgs;

    /*

    /*
     * List of bdtivf protodols
     *
     * Adtivf protodols is b subsft of fnbblfd protodols, bnd will
     * dontbin only tiosf protodols tibt ibvf vbild dipifr suitfs
     * fnbblfd.
     */
    privbtf ProtodolList       bdtivfProtodols;

    /*
     * List of bdtivf dipifr suitfs
     *
     * Adtivf dipifr suitfs is b subsft of fnbblfd dipifr suitfs, bnd will
     * dontbin only tiosf dipifr suitfs bvbilbblf for tif bdtivf protodols.
     */
    privbtf CipifrSuitfList    bdtivfCipifrSuitfs;

    // Tif sfrvfr nbmf indidbtion bnd mbtdifrs
    List<SNISfrvfrNbmf>         sfrvfrNbmfs =
                                    Collfdtions.<SNISfrvfrNbmf>fmptyList();
    Collfdtion<SNIMbtdifr>      sniMbtdifrs =
                                    Collfdtions.<SNIMbtdifr>fmptyList();

    privbtf boolfbn             isClifnt;
    privbtf boolfbn             nffdCfrtVfrify;

    SSLSodkftImpl               donn = null;
    SSLEnginfImpl               fnginf = null;

    HbndsibkfHbsi               ibndsibkfHbsi;
    HbndsibkfInStrfbm           input;
    HbndsibkfOutStrfbm          output;
    int                         stbtf;
    SSLContfxtImpl              sslContfxt;
    RbndomCookif                dlnt_rbndom, svr_rbndom;
    SSLSfssionImpl              sfssion;

    // durrfnt CipifrSuitf. Nfvfr null, initiblly SSL_NULL_WITH_NULL_NULL
    CipifrSuitf         dipifrSuitf;

    // durrfnt kfy fxdibngf. Nfvfr null, initiblly K_NULL
    KfyExdibngf         kfyExdibngf;

    /* Truf if tiis sfssion is bfing rfsumfd (fbst ibndsibkf) */
    boolfbn             rfsumingSfssion;

    /* Truf if it's OK to stbrt b nfw SSL sfssion */
    boolfbn             fnbblfNfwSfssion;

    // Wiftifr lodbl dipifr suitfs prfffrfndf siould bf ionorfd during
    // ibndsibking?
    //
    // Notf tibt in tiis providfr, tiis option only bpplifs to sfrvfr sidf.
    // Lodbl dipifr suitfs prfffrfndf is blwbys ionorfd in dlifnt sidf in
    // tiis providfr.
    boolfbn prfffrLodblCipifrSuitfs = fblsf;

    // Tfmporbry storbgf for tif individubl kfys. Sft by
    // dbldulbtfConnfdtionKfys() bnd dlfbrfd ondf tif dipifrs brf
    // bdtivbtfd.
    privbtf SfdrftKfy dlntWritfKfy, svrWritfKfy;
    privbtf IvPbrbmftfrSpfd dlntWritfIV, svrWritfIV;
    privbtf SfdrftKfy dlntMbdSfdrft, svrMbdSfdrft;

    /*
     * Dflfgbtfd tbsk subsystfm dbtb strudturfs.
     *
     * If tirown is sft, wf nffd to propbgbtf tiis bbdk immfdibtfly
     * on fntry into prodfssMfssbgf().
     *
     * Dbtb is protfdtfd by tif SSLEnginf.tiis lodk.
     */
    privbtf volbtilf boolfbn tbskDflfgbtfd = fblsf;
    privbtf volbtilf DflfgbtfdTbsk<?> dflfgbtfdTbsk = null;
    privbtf volbtilf Exdfption tirown = null;

    // Could probbbly usf b jbvb.util.dondurrfnt.btomid.AtomidRfffrfndf
    // ifrf instfbd of using tiis lodk.  Considfr dibnging.
    privbtf Objfdt tirownLodk = nfw Objfdt();

    /* Clbss bnd subdlbss dynbmid dfbugging support */
    stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("ssl");

    // By dffbult, disbblf tif unsbff lfgbdy sfssion rfnfgotibtion
    stbtid finbl boolfbn bllowUnsbffRfnfgotibtion = Dfbug.gftBoolfbnPropfrty(
                    "sun.sfdurity.ssl.bllowUnsbffRfnfgotibtion", fblsf);

    // For mbximum intfropfrbbility bnd bbdkwbrd dompbtibility, RFC 5746
    // bllows sfrvfr (or dlifnt) to bddfpt ClifntHfllo (or SfrvfrHfllo)
    // mfssbgf witiout tif sfdurf rfnfgotibtion_info fxtfnsion or SCSV.
    //
    // For mbximum sfdurity, RFC 5746 blso bllows sfrvfr (or dlifnt) to
    // rfjfdt sudi mfssbgf witi b fbtbl "ibndsibkf_fbilurf" blfrt.
    //
    // By dffbult, bllow sudi lfgbdy ifllo mfssbgfs.
    stbtid finbl boolfbn bllowLfgbdyHflloMfssbgfs = Dfbug.gftBoolfbnPropfrty(
                    "sun.sfdurity.ssl.bllowLfgbdyHflloMfssbgfs", truf);

    // To prfvfnt tif TLS rfnfgotibtion issufs, by sftting systfm propfrty
    // "jdk.tls.rfjfdtClifntInitibtfdRfnfgotibtion" to truf, bpplidbtions in
    // sfrvfr sidf dbn disbblf bll dlifnt initibtfd SSL rfnfgotibtions
    // rfgbrdlfss of tif support of TLS protodols.
    //
    // By dffbult, bllow dlifnt initibtfd rfnfgotibtions.
    stbtid finbl boolfbn rfjfdtClifntInitibtfdRfnfgo =
            Dfbug.gftBoolfbnPropfrty(
                "jdk.tls.rfjfdtClifntInitibtfdRfnfgotibtion", fblsf);

    // nffd to disposf tif objfdt wifn it is invblidbtfd
    boolfbn invblidbtfd;

    Hbndsibkfr(SSLSodkftImpl d, SSLContfxtImpl dontfxt,
            ProtodolList fnbblfdProtodols, boolfbn nffdCfrtVfrify,
            boolfbn isClifnt, ProtodolVfrsion bdtivfProtodolVfrsion,
            boolfbn isInitiblHbndsibkf, boolfbn sfdurfRfnfgotibtion,
            bytf[] dlifntVfrifyDbtb, bytf[] sfrvfrVfrifyDbtb) {
        tiis.donn = d;
        init(dontfxt, fnbblfdProtodols, nffdCfrtVfrify, isClifnt,
            bdtivfProtodolVfrsion, isInitiblHbndsibkf, sfdurfRfnfgotibtion,
            dlifntVfrifyDbtb, sfrvfrVfrifyDbtb);
    }

    Hbndsibkfr(SSLEnginfImpl fnginf, SSLContfxtImpl dontfxt,
            ProtodolList fnbblfdProtodols, boolfbn nffdCfrtVfrify,
            boolfbn isClifnt, ProtodolVfrsion bdtivfProtodolVfrsion,
            boolfbn isInitiblHbndsibkf, boolfbn sfdurfRfnfgotibtion,
            bytf[] dlifntVfrifyDbtb, bytf[] sfrvfrVfrifyDbtb) {
        tiis.fnginf = fnginf;
        init(dontfxt, fnbblfdProtodols, nffdCfrtVfrify, isClifnt,
            bdtivfProtodolVfrsion, isInitiblHbndsibkf, sfdurfRfnfgotibtion,
            dlifntVfrifyDbtb, sfrvfrVfrifyDbtb);
    }

    privbtf void init(SSLContfxtImpl dontfxt, ProtodolList fnbblfdProtodols,
            boolfbn nffdCfrtVfrify, boolfbn isClifnt,
            ProtodolVfrsion bdtivfProtodolVfrsion,
            boolfbn isInitiblHbndsibkf, boolfbn sfdurfRfnfgotibtion,
            bytf[] dlifntVfrifyDbtb, bytf[] sfrvfrVfrifyDbtb) {

        if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
            Systfm.out.println(
                "Allow unsbff rfnfgotibtion: " + bllowUnsbffRfnfgotibtion +
                "\nAllow lfgbdy ifllo mfssbgfs: " + bllowLfgbdyHflloMfssbgfs +
                "\nIs initibl ibndsibkf: " + isInitiblHbndsibkf +
                "\nIs sfdurf rfnfgotibtion: " + sfdurfRfnfgotibtion);
        }

        tiis.sslContfxt = dontfxt;
        tiis.isClifnt = isClifnt;
        tiis.nffdCfrtVfrify = nffdCfrtVfrify;
        tiis.bdtivfProtodolVfrsion = bdtivfProtodolVfrsion;
        tiis.isInitiblHbndsibkf = isInitiblHbndsibkf;
        tiis.sfdurfRfnfgotibtion = sfdurfRfnfgotibtion;
        tiis.dlifntVfrifyDbtb = dlifntVfrifyDbtb;
        tiis.sfrvfrVfrifyDbtb = sfrvfrVfrifyDbtb;
        fnbblfNfwSfssion = truf;
        invblidbtfd = fblsf;

        sftCipifrSuitf(CipifrSuitf.C_NULL);
        sftEnbblfdProtodols(fnbblfdProtodols);

        if (donn != null) {
            blgoritimConstrbints = nfw SSLAlgoritimConstrbints(donn, truf);
        } flsf {        // fnginf != null
            blgoritimConstrbints = nfw SSLAlgoritimConstrbints(fnginf, truf);
        }


        //
        // In bddition to tif donnfdtion stbtf mbdiinf, dontrolling
        // iow tif donnfdtion dfbls witi tif difffrfnt sorts of rfdords
        // tibt gft sfnt (notbbly ibndsibkf trbnsitions!), tifrf's
        // blso b ibndsibking stbtf mbdiinf tibt dontrols mfssbgf
        // sfqufnding.
        //
        // It's b donvfnifnt brtifbdt of tif protodol tibt tiis dbn,
        // witi only b douplf of minor fxdfptions, bf drivfn by tif
        // typf donstbnt for tif lbst mfssbgf sffn:  fxdfpt for tif
        // dlifnt's dfrt vfrify, tiosf donstbnts brf in b donvfnifnt
        // ordfr to drbstidblly simplify stbtf mbdiinf difdking.
        //
        stbtf = -2;  // initiblizfd but not bdtivbtfd
    }

    /*
     * Rfroutfs dblls to tif SSLSodkft or SSLEnginf (*SE).
     *
     * Wf dould ibvf blso donf it by fxtrb dlbssfs
     * bnd lftting tifm ovfrridf, but tiis sffmfd mudi
     * lfss involvfd.
     */
    void fbtblSE(bytf b, String dibgnostid) tirows IOExdfption {
        fbtblSE(b, dibgnostid, null);
    }

    void fbtblSE(bytf b, Tirowbblf dbusf) tirows IOExdfption {
        fbtblSE(b, null, dbusf);
    }

    void fbtblSE(bytf b, String dibgnostid, Tirowbblf dbusf)
            tirows IOExdfption {
        if (donn != null) {
            donn.fbtbl(b, dibgnostid, dbusf);
        } flsf {
            fnginf.fbtbl(b, dibgnostid, dbusf);
        }
    }

    void wbrningSE(bytf b) {
        if (donn != null) {
            donn.wbrning(b);
        } flsf {
            fnginf.wbrning(b);
        }
    }

    // ONLY usfd by ClifntHbndsibkfr to sftup tif pffr iost in SSLSfssion.
    String gftHostSE() {
        if (donn != null) {
            rfturn donn.gftHost();
        } flsf {
            rfturn fnginf.gftPffrHost();
        }
    }

    // ONLY usfd by SfrvfrHbndsibkfr to sftup tif pffr iost in SSLSfssion.
    String gftHostAddrfssSE() {
        if (donn != null) {
            rfturn donn.gftInftAddrfss().gftHostAddrfss();
        } flsf {
            /*
             * Tiis is for dbdiing only, dofsn't mbttfr tibt's is rfblly
             * b iostnbmf.  Tif mbin tiing is tibt it dofsn't do
             * b rfvfrsf DNS lookup, potfntiblly slowing tiings down.
             */
            rfturn fnginf.gftPffrHost();
        }
    }

    int gftPortSE() {
        if (donn != null) {
            rfturn donn.gftPort();
        } flsf {
            rfturn fnginf.gftPffrPort();
        }
    }

    int gftLodblPortSE() {
        if (donn != null) {
            rfturn donn.gftLodblPort();
        } flsf {
            rfturn -1;
        }
    }

    AddfssControlContfxt gftAddSE() {
        if (donn != null) {
            rfturn donn.gftAdd();
        } flsf {
            rfturn fnginf.gftAdd();
        }
    }

    privbtf void sftVfrsionSE(ProtodolVfrsion protodolVfrsion) {
        if (donn != null) {
            donn.sftVfrsion(protodolVfrsion);
        } flsf {
            fnginf.sftVfrsion(protodolVfrsion);
        }
    }

    /**
     * Sft tif bdtivf protodol vfrsion bnd propbgbtf it to tif SSLSodkft
     * bnd our ibndsibkf strfbms. Cbllfd from ClifntHbndsibkfr
     * bnd SfrvfrHbndsibkfr witi tif nfgotibtfd protodol vfrsion.
     */
    void sftVfrsion(ProtodolVfrsion protodolVfrsion) {
        tiis.protodolVfrsion = protodolVfrsion;
        sftVfrsionSE(protodolVfrsion);

        output.r.sftVfrsion(protodolVfrsion);
    }

    /**
     * Sft tif fnbblfd protodols. Cbllfd from tif donstrudtor or
     * SSLSodkftImpl/SSLEnginfImpl.sftEnbblfdProtodols() (if tif
     * ibndsibkf is not yft in progrfss).
     */
    void sftEnbblfdProtodols(ProtodolList fnbblfdProtodols) {
        bdtivfCipifrSuitfs = null;
        bdtivfProtodols = null;

        tiis.fnbblfdProtodols = fnbblfdProtodols;
    }

    /**
     * Sft tif fnbblfd dipifr suitfs. Cbllfd from
     * SSLSodkftImpl/SSLEnginfImpl.sftEnbblfdCipifrSuitfs() (if tif
     * ibndsibkf is not yft in progrfss).
     */
    void sftEnbblfdCipifrSuitfs(CipifrSuitfList fnbblfdCipifrSuitfs) {
        bdtivfCipifrSuitfs = null;
        bdtivfProtodols = null;
        tiis.fnbblfdCipifrSuitfs = fnbblfdCipifrSuitfs;
    }

    /**
     * Sft tif blgoritim donstrbints. Cbllfd from tif donstrudtor or
     * SSLSodkftImpl/SSLEnginfImpl.sftAlgoritimConstrbints() (if tif
     * ibndsibkf is not yft in progrfss).
     */
    void sftAlgoritimConstrbints(AlgoritimConstrbints blgoritimConstrbints) {
        bdtivfCipifrSuitfs = null;
        bdtivfProtodols = null;

        tiis.blgoritimConstrbints =
            nfw SSLAlgoritimConstrbints(blgoritimConstrbints);
        tiis.lodblSupportfdSignAlgs = null;
    }

    Collfdtion<SignbturfAndHbsiAlgoritim> gftLodblSupportfdSignAlgs() {
        if (lodblSupportfdSignAlgs == null) {
            lodblSupportfdSignAlgs =
                SignbturfAndHbsiAlgoritim.gftSupportfdAlgoritims(
                                                    blgoritimConstrbints);
        }

        rfturn lodblSupportfdSignAlgs;
    }

    void sftPffrSupportfdSignAlgs(
            Collfdtion<SignbturfAndHbsiAlgoritim> blgoritims) {
        pffrSupportfdSignAlgs =
            nfw ArrbyList<SignbturfAndHbsiAlgoritim>(blgoritims);
    }

    Collfdtion<SignbturfAndHbsiAlgoritim> gftPffrSupportfdSignAlgs() {
        rfturn pffrSupportfdSignAlgs;
    }


    /**
     * Sft tif idfntifidbtion protodol. Cbllfd from tif donstrudtor or
     * SSLSodkftImpl/SSLEnginfImpl.sftIdfntifidbtionProtodol() (if tif
     * ibndsibkf is not yft in progrfss).
     */
    void sftIdfntifidbtionProtodol(String protodol) {
        tiis.idfntifidbtionProtodol = protodol;
    }

    /**
     * Sfts tif sfrvfr nbmf indidbtion of tif ibndsibkf.
     */
    void sftSNISfrvfrNbmfs(List<SNISfrvfrNbmf> sfrvfrNbmfs) {
        // Tif sfrvfrNbmfs pbrbmftfr is unmodifibblf.
        tiis.sfrvfrNbmfs = sfrvfrNbmfs;
    }

    /**
     * Sfts tif sfrvfr nbmf mbtdifrs of tif ibndsibking.
     */
    void sftSNIMbtdifrs(Collfdtion<SNIMbtdifr> sniMbtdifrs) {
        // Tif sniMbtdifrs pbrbmftfr is unmodifibblf.
        tiis.sniMbtdifrs = sniMbtdifrs;
    }

    /**
     * Sfts tif dipifr suitfs prfffrfndf.
     */
    void sftUsfCipifrSuitfsOrdfr(boolfbn on) {
        tiis.prfffrLodblCipifrSuitfs = on;
    }

    /**
     * Prior to ibndsibking, bdtivbtf tif ibndsibkf bnd initiblizf tif vfrsion,
     * input strfbm bnd output strfbm.
     */
    void bdtivbtf(ProtodolVfrsion iflloVfrsion) tirows IOExdfption {
        if (bdtivfProtodols == null) {
            bdtivfProtodols = gftAdtivfProtodols();
        }

        if (bdtivfProtodols.dollfdtion().isEmpty() ||
                bdtivfProtodols.mbx.v == ProtodolVfrsion.NONE.v) {
            tirow nfw SSLHbndsibkfExdfption("No bppropribtf protodol");
        }

        if (bdtivfCipifrSuitfs == null) {
            bdtivfCipifrSuitfs = gftAdtivfCipifrSuitfs();
        }

        if (bdtivfCipifrSuitfs.dollfdtion().isEmpty()) {
            tirow nfw SSLHbndsibkfExdfption("No bppropribtf dipifr suitf");
        }

        // tfmporbry protodol vfrsion until tif bdtubl protodol vfrsion
        // is nfgotibtfd in tif Hfllo fxdibngf. Tiis bfffdts tif rfdord
        // vfrsion wf sfnt witi tif ClifntHfllo.
        if (!isInitiblHbndsibkf) {
            protodolVfrsion = bdtivfProtodolVfrsion;
        } flsf {
            protodolVfrsion = bdtivfProtodols.mbx;
        }

        if (iflloVfrsion == null || iflloVfrsion.v == ProtodolVfrsion.NONE.v) {
            iflloVfrsion = bdtivfProtodols.iflloVfrsion;
        }

        // Wf bddumulbtf digfsts of tif ibndsibkf mfssbgfs so tibt
        // wf dbn rfbd/writf CfrtifidbtfVfrify bnd Finisifd mfssbgfs,
        // gftting bssurbndf bgbinst somf pbrtidulbr bdtivf bttbdks.
        ibndsibkfHbsi = nfw HbndsibkfHbsi(nffdCfrtVfrify);

        // Gfnfrbtf ibndsibkf input/output strfbm.
        input = nfw HbndsibkfInStrfbm(ibndsibkfHbsi);
        if (donn != null) {
            output = nfw HbndsibkfOutStrfbm(protodolVfrsion, iflloVfrsion,
                                        ibndsibkfHbsi, donn);
            donn.gftAppInputStrfbm().r.sftHbndsibkfHbsi(ibndsibkfHbsi);
            donn.gftAppInputStrfbm().r.sftHflloVfrsion(iflloVfrsion);
            donn.gftAppOutputStrfbm().r.sftHflloVfrsion(iflloVfrsion);
        } flsf {
            output = nfw HbndsibkfOutStrfbm(protodolVfrsion, iflloVfrsion,
                                        ibndsibkfHbsi, fnginf);
            fnginf.inputRfdord.sftHbndsibkfHbsi(ibndsibkfHbsi);
            fnginf.inputRfdord.sftHflloVfrsion(iflloVfrsion);
            fnginf.outputRfdord.sftHflloVfrsion(iflloVfrsion);
        }

        // movf stbtf to bdtivbtfd
        stbtf = -1;
    }

    /**
     * Sft dipifrSuitf bnd kfyExdibngf to tif givfn CipifrSuitf.
     * Dofs not pfrform bny vfrifidbtion tibt tiis is b vblid sflfdtion,
     * tiis must bf donf bfforf dblling tiis mftiod.
     */
    void sftCipifrSuitf(CipifrSuitf s) {
        tiis.dipifrSuitf = s;
        tiis.kfyExdibngf = s.kfyExdibngf;
    }

    /**
     * Cifdk if tif givfn dipifrsuitf is fnbblfd bnd bvbilbblf witiin tif
     * durrfnt bdtivf dipifr suitfs.
     *
     * Dofs not difdk if tif rfquirfd sfrvfr dfrtifidbtfs brf bvbilbblf.
     */
    boolfbn isNfgotibblf(CipifrSuitf s) {
        if (bdtivfCipifrSuitfs == null) {
            bdtivfCipifrSuitfs = gftAdtivfCipifrSuitfs();
        }

        rfturn isNfgotibblf(bdtivfCipifrSuitfs, s);
    }

    /**
     * Cifdk if tif givfn dipifrsuitf is fnbblfd bnd bvbilbblf witiin tif
     * proposfd dipifr suitf list.
     *
     * Dofs not difdk if tif rfquirfd sfrvfr dfrtifidbtfs brf bvbilbblf.
     */
    finbl stbtid boolfbn isNfgotibblf(CipifrSuitfList proposfd, CipifrSuitf s) {
        rfturn proposfd.dontbins(s) && s.isNfgotibblf();
    }

    /**
     * Cifdk if tif givfn protodol vfrsion is fnbblfd bnd bvbilbblf.
     */
    boolfbn isNfgotibblf(ProtodolVfrsion protodolVfrsion) {
        if (bdtivfProtodols == null) {
            bdtivfProtodols = gftAdtivfProtodols();
        }

        rfturn bdtivfProtodols.dontbins(protodolVfrsion);
    }

    /**
     * Sflfdt b protodol vfrsion from tif list. Cbllfd from
     * SfrvfrHbndsibkfr to nfgotibtf protodol vfrsion.
     *
     * Rfturn tif lowfr of tif protodol vfrsion suggfstfd in tif
     * dlifn ifllo bnd tif iigifst supportfd by tif sfrvfr.
     */
    ProtodolVfrsion sflfdtProtodolVfrsion(ProtodolVfrsion protodolVfrsion) {
        if (bdtivfProtodols == null) {
            bdtivfProtodols = gftAdtivfProtodols();
        }

        rfturn bdtivfProtodols.sflfdtProtodolVfrsion(protodolVfrsion);
    }

    /**
     * Gft tif bdtivf dipifr suitfs.
     *
     * In TLS 1.1, mbny wfbk or vulnfrbblf dipifr suitfs wfrf obsolftfd,
     * sudi bs TLS_RSA_EXPORT_WITH_RC4_40_MD5. Tif implfmfntbtion MUST NOT
     * nfgotibtf tifsf dipifr suitfs in TLS 1.1 or lbtfr modf.
     *
     * Tifrfforf, wifn tif bdtivf protodols only indludf TLS 1.1 or lbtfr,
     * tif dlifnt dbnnot rfqufst to nfgotibtf tiosf obsolftfd dipifr
     * suitfs.  Tibt is, tif obsolftfd suitfs siould not bf indludfd in tif
     * dlifnt ifllo. So wf nffd to drfbtf b subsft of tif fnbblfd dipifr
     * suitfs, tif bdtivf dipifr suitfs, wiidi dofs not dontbin obsolftfd
     * dipifr suitfs of tif minimum bdtivf protodol.
     *
     * Rfturn fmpty list instfbd of null if no bdtivf dipifr suitfs.
     */
    CipifrSuitfList gftAdtivfCipifrSuitfs() {
        if (bdtivfCipifrSuitfs == null) {
            if (bdtivfProtodols == null) {
                bdtivfProtodols = gftAdtivfProtodols();
            }

            ArrbyList<CipifrSuitf> suitfs = nfw ArrbyList<>();
            if (!(bdtivfProtodols.dollfdtion().isEmpty()) &&
                    bdtivfProtodols.min.v != ProtodolVfrsion.NONE.v) {
                for (CipifrSuitf suitf : fnbblfdCipifrSuitfs.dollfdtion()) {
                    if (suitf.obsolftfd > bdtivfProtodols.min.v &&
                            suitf.supportfd <= bdtivfProtodols.mbx.v) {
                        if (blgoritimConstrbints.pfrmits(
                                EnumSft.of(CryptoPrimitivf.KEY_AGREEMENT),
                                suitf.nbmf, null)) {
                            suitfs.bdd(suitf);
                        }
                    } flsf if (dfbug != null && Dfbug.isOn("vfrbosf")) {
                        if (suitf.obsolftfd <= bdtivfProtodols.min.v) {
                            Systfm.out.println(
                                "Ignoring obsolftfd dipifr suitf: " + suitf);
                        } flsf {
                            Systfm.out.println(
                                "Ignoring unsupportfd dipifr suitf: " + suitf);
                        }
                    }
                }
            }
            bdtivfCipifrSuitfs = nfw CipifrSuitfList(suitfs);
        }

        rfturn bdtivfCipifrSuitfs;
    }

    /*
     * Gft tif bdtivf protodol vfrsions.
     *
     * In TLS 1.1, mbny wfbk or vulnfrbblf dipifr suitfs wfrf obsolftfd,
     * sudi bs TLS_RSA_EXPORT_WITH_RC4_40_MD5. Tif implfmfntbtion MUST NOT
     * nfgotibtf tifsf dipifr suitfs in TLS 1.1 or lbtfr modf.
     *
     * For fxbmplf, if "TLS_RSA_EXPORT_WITH_RC4_40_MD5" is tif
     * only fnbblfd dipifr suitf, tif dlifnt dbnnot rfqufst TLS 1.1 or
     * lbtfr, fvfn tiougi TLS 1.1 or lbtfr is fnbblfd.  Wf nffd to drfbtf b
     * subsft of tif fnbblfd protodols, dbllfd tif bdtivf protodols, wiidi
     * dontbins protodols bppropribtf to tif list of fnbblfd Cipifrsuitfs.
     *
     * Rfturn fmpty list instfbd of null if no bdtivf protodol vfrsions.
     */
    ProtodolList gftAdtivfProtodols() {
        if (bdtivfProtodols == null) {
            boolfbn fnbblfdSSL20Hfllo = fblsf;
            ArrbyList<ProtodolVfrsion> protodols = nfw ArrbyList<>(4);
            for (ProtodolVfrsion protodol : fnbblfdProtodols.dollfdtion()) {
                // Nffd not to difdk tif SSL20Hfllo protodol.
                if (protodol.v == ProtodolVfrsion.SSL20Hfllo.v) {
                    fnbblfdSSL20Hfllo = truf;
                    dontinuf;
                }

                boolfbn found = fblsf;
                for (CipifrSuitf suitf : fnbblfdCipifrSuitfs.dollfdtion()) {
                    if (suitf.isAvbilbblf() && suitf.obsolftfd > protodol.v &&
                                               suitf.supportfd <= protodol.v) {
                        if (blgoritimConstrbints.pfrmits(
                                EnumSft.of(CryptoPrimitivf.KEY_AGREEMENT),
                                suitf.nbmf, null)) {
                            protodols.bdd(protodol);
                            found = truf;
                            brfbk;
                        } flsf if (dfbug != null && Dfbug.isOn("vfrbosf")) {
                            Systfm.out.println(
                                "Ignoring disbblfd dipifr suitf: " + suitf +
                                 " for " + protodol);
                        }
                    } flsf if (dfbug != null && Dfbug.isOn("vfrbosf")) {
                        Systfm.out.println(
                            "Ignoring unsupportfd dipifr suitf: " + suitf +
                                 " for " + protodol);
                    }
                }
                if (!found && (dfbug != null) && Dfbug.isOn("ibndsibkf")) {
                    Systfm.out.println(
                        "No bvbilbblf dipifr suitf for " + protodol);
                }
            }

            if (!protodols.isEmpty() && fnbblfdSSL20Hfllo) {
                protodols.bdd(ProtodolVfrsion.SSL20Hfllo);
            }

            bdtivfProtodols = nfw ProtodolList(protodols);
        }

        rfturn bdtivfProtodols;
    }

    /**
     * As long bs ibndsibking ibs not bdtivbtfd, wf dbn
     * dibngf wiftifr sfssion drfbtions brf bllowfd.
     *
     * Cbllfrs siould do tifir own difdking if ibndsibking
     * ibs bdtivbtfd.
     */
    void sftEnbblfSfssionCrfbtion(boolfbn nfwSfssions) {
        fnbblfNfwSfssion = nfwSfssions;
    }

    /**
     * Crfbtf b nfw rfbd dipifr bnd rfturn it to dbllfr.
     */
    CipifrBox nfwRfbdCipifr() tirows NoSudiAlgoritimExdfption {
        BulkCipifr dipifr = dipifrSuitf.dipifr;
        CipifrBox box;
        if (isClifnt) {
            box = dipifr.nfwCipifr(protodolVfrsion, svrWritfKfy, svrWritfIV,
                                   sslContfxt.gftSfdurfRbndom(), fblsf);
            svrWritfKfy = null;
            svrWritfIV = null;
        } flsf {
            box = dipifr.nfwCipifr(protodolVfrsion, dlntWritfKfy, dlntWritfIV,
                                   sslContfxt.gftSfdurfRbndom(), fblsf);
            dlntWritfKfy = null;
            dlntWritfIV = null;
        }
        rfturn box;
    }

    /**
     * Crfbtf b nfw writf dipifr bnd rfturn it to dbllfr.
     */
    CipifrBox nfwWritfCipifr() tirows NoSudiAlgoritimExdfption {
        BulkCipifr dipifr = dipifrSuitf.dipifr;
        CipifrBox box;
        if (isClifnt) {
            box = dipifr.nfwCipifr(protodolVfrsion, dlntWritfKfy, dlntWritfIV,
                                   sslContfxt.gftSfdurfRbndom(), truf);
            dlntWritfKfy = null;
            dlntWritfIV = null;
        } flsf {
            box = dipifr.nfwCipifr(protodolVfrsion, svrWritfKfy, svrWritfIV,
                                   sslContfxt.gftSfdurfRbndom(), truf);
            svrWritfKfy = null;
            svrWritfIV = null;
        }
        rfturn box;
    }

    /**
     * Crfbtf b nfw rfbd MAC bnd rfturn it to dbllfr.
     */
    Autifntidbtor nfwRfbdAutifntidbtor()
            tirows NoSudiAlgoritimExdfption, InvblidKfyExdfption {

        Autifntidbtor butifntidbtor = null;
        if (dipifrSuitf.dipifr.dipifrTypf == AEAD_CIPHER) {
            butifntidbtor = nfw Autifntidbtor(protodolVfrsion);
        } flsf {
            MbdAlg mbdAlg = dipifrSuitf.mbdAlg;
            if (isClifnt) {
                butifntidbtor = mbdAlg.nfwMbd(protodolVfrsion, svrMbdSfdrft);
                svrMbdSfdrft = null;
            } flsf {
                butifntidbtor = mbdAlg.nfwMbd(protodolVfrsion, dlntMbdSfdrft);
                dlntMbdSfdrft = null;
            }
        }

        rfturn butifntidbtor;
    }

    /**
     * Crfbtf b nfw writf MAC bnd rfturn it to dbllfr.
     */
    Autifntidbtor nfwWritfAutifntidbtor()
            tirows NoSudiAlgoritimExdfption, InvblidKfyExdfption {

        Autifntidbtor butifntidbtor = null;
        if (dipifrSuitf.dipifr.dipifrTypf == AEAD_CIPHER) {
            butifntidbtor = nfw Autifntidbtor(protodolVfrsion);
        } flsf {
            MbdAlg mbdAlg = dipifrSuitf.mbdAlg;
            if (isClifnt) {
                butifntidbtor = mbdAlg.nfwMbd(protodolVfrsion, dlntMbdSfdrft);
                dlntMbdSfdrft = null;
            } flsf {
                butifntidbtor = mbdAlg.nfwMbd(protodolVfrsion, svrMbdSfdrft);
                svrMbdSfdrft = null;
            }
        }

        rfturn butifntidbtor;
    }

    /*
     * Rfturns truf iff tif ibndsibkf sfqufndf is donf, so tibt
     * tiis frfsily drfbtfd sfssion dbn bfdomf tif durrfnt onf.
     */
    boolfbn isDonf() {
        rfturn stbtf == HbndsibkfMfssbgf.it_finisifd;
    }


    /*
     * Rfturns tif sfssion wiidi wbs drfbtfd tirougi tiis
     * ibndsibkf sfqufndf ... siould bf dbllfd bftfr isDonf()
     * rfturns truf.
     */
    SSLSfssionImpl gftSfssion() {
        rfturn sfssion;
    }

    /*
     * Sft tif ibndsibkf sfssion
     */
    void sftHbndsibkfSfssionSE(SSLSfssionImpl ibndsibkfSfssion) {
        if (donn != null) {
            donn.sftHbndsibkfSfssion(ibndsibkfSfssion);
        } flsf {
            fnginf.sftHbndsibkfSfssion(ibndsibkfSfssion);
        }
    }

    /*
     * Rfturns truf if rfnfgotibtion is in usf for tiis donnfdtion.
     */
    boolfbn isSfdurfRfnfgotibtion() {
        rfturn sfdurfRfnfgotibtion;
    }

    /*
     * Rfturns tif vfrify_dbtb from tif Finisifd mfssbgf sfnt by tif dlifnt.
     */
    bytf[] gftClifntVfrifyDbtb() {
        rfturn dlifntVfrifyDbtb;
    }

    /*
     * Rfturns tif vfrify_dbtb from tif Finisifd mfssbgf sfnt by tif sfrvfr.
     */
    bytf[] gftSfrvfrVfrifyDbtb() {
        rfturn sfrvfrVfrifyDbtb;
    }

    /*
     * Tiis routinf is ffd SSL ibndsibkf rfdords wifn tify bfdomf bvbilbblf,
     * bnd prodfssfs mfssbgfs found tifrfin.
     */
    void prodfss_rfdord(InputRfdord r, boolfbn fxpfdtingFinisifd)
            tirows IOExdfption {

        difdkTirown();

        /*
         * Storf tif indoming ibndsibkf dbtb, tifn sff if wf dbn
         * now prodfss bny domplftfd ibndsibkf mfssbgfs
         */
        input.indomingRfdord(r);

        /*
         * Wf don't nffd to drfbtf b sfpbrbtf dflfgbtbblf tbsk
         * for finisifd mfssbgfs.
         */
        if ((donn != null) || fxpfdtingFinisifd) {
            prodfssLoop();
        } flsf {
            dflfgbtfTbsk(nfw PrivilfgfdExdfptionAdtion<Void>() {
                @Ovfrridf
                publid Void run() tirows Exdfption {
                    prodfssLoop();
                    rfturn null;
                }
            });
        }
    }

    /*
     * On input, wf ibsi mfssbgfs onf bt b timf sindf sfrvfrs mby nffd
     * to bddfss bn intfrmfdibtf ibsi to vblidbtf b CfrtifidbtfVfrify
     * mfssbgf.
     *
     * Notf tibt mbny ibndsibkf mfssbgfs dbn domf in onf rfdord (bnd oftfn
     * do, to rfdudf nftwork rfsourdf utilizbtion), bnd onf mfssbgf dbn blso
     * rfquirf multiplf rfdords (f.g. vfry lbrgf Cfrtifidbtf mfssbgfs).
     */
    void prodfssLoop() tirows IOExdfption {

        // nffd to rfbd off 4 bytfs bt lfbst to gft tif ibndsibkf
        // mfssbgf typf bnd lfngti.
        wiilf (input.bvbilbblf() >= 4) {
            bytf mfssbgfTypf;
            int mfssbgfLfn;

            /*
             * Sff if wf dbn rfbd tif ibndsibkf mfssbgf ifbdfr, bnd
             * tifn tif fntirf ibndsibkf mfssbgf.  If not, wbit till
             * wf dbn rfbd bnd prodfss bn fntirf mfssbgf.
             */
            input.mbrk(4);

            mfssbgfTypf = (bytf)input.gftInt8();
            mfssbgfLfn = input.gftInt24();

            if (input.bvbilbblf() < mfssbgfLfn) {
                input.rfsft();
                rfturn;
            }

            /*
             * Prodfss tif mfssbgf.  Wf rfquirf
             * tibt prodfssMfssbgf() donsumfs tif fntirf mfssbgf.  In
             * lifu of fxplidit frror difdks (iow?!) wf bssumf tibt tif
             * dbtb will look likf gbrbbgf on fndoding/prodfssing frrors,
             * bnd tibt otifr protodol dodf will dftfdt sudi frrors.
             *
             * Notf tibt digfsting is normblly dfffrrfd till bftfr tif
             * mfssbgf ibs bffn prodfssfd, tiougi to prodfss bt lfbst tif
             * dlifnt's Finisifd mfssbgf (i.f. sfnd tif sfrvfr's) wf nffd
             * to bdddflfrbtf tibt digfsting.
             *
             * Also, notf tibt ifllo rfqufst mfssbgfs brf nfvfr ibsifd;
             * tibt indludfs tif ifllo rfqufst ifbdfr, too.
             */
            if (mfssbgfTypf == HbndsibkfMfssbgf.it_ifllo_rfqufst) {
                input.rfsft();
                prodfssMfssbgf(mfssbgfTypf, mfssbgfLfn);
                input.ignorf(4 + mfssbgfLfn);
            } flsf {
                input.mbrk(mfssbgfLfn);
                prodfssMfssbgf(mfssbgfTypf, mfssbgfLfn);
                input.digfstNow();
            }
        }
    }


    /**
     * Rfturns truf iff tif ibndsibkfr ibs bffn bdtivbtfd.
     *
     * In bdtivbtfd stbtf, tif ibndsibkfr mby not sfnd bny mfssbgfs out.
     */
    boolfbn bdtivbtfd() {
        rfturn stbtf >= -1;
    }

    /**
     * Rfturns truf iff tif ibndsibkfr ibs sfnt bny mfssbgfs.
     */
    boolfbn stbrtfd() {
        rfturn stbtf >= 0;  // 0: HbndsibkfMfssbgf.it_ifllo_rfqufst
                            // 1: HbndsibkfMfssbgf.it_dlifnt_ifllo
    }


    /*
     * Usfd to kidkstbrt tif nfgotibtion ... fitifr writing b
     * ClifntHfllo or b HflloRfqufst bs bppropribtf, wiidifvfr
     * tif subdlbss rfturns.  NOP if ibndsibking's blrfbdy stbrtfd.
     */
    void kidkstbrt() tirows IOExdfption {
        if (stbtf >= 0) {
            rfturn;
        }

        HbndsibkfMfssbgf m = gftKidkstbrtMfssbgf();

        if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
            m.print(Systfm.out);
        }
        m.writf(output);
        output.flusi();

        stbtf = m.mfssbgfTypf();
    }

    /**
     * Boti dlifnt bnd sfrvfr modfs dbn stbrt ibndsibking; but tif
     * mfssbgf tify sfnd to do so is difffrfnt.
     */
    bbstrbdt HbndsibkfMfssbgf gftKidkstbrtMfssbgf() tirows SSLExdfption;

    /*
     * Clifnt bnd Sfrvfr sidf protodols brf fbdi drivfn tiougi tiis
     * dbll, wiidi prodfssfs b singlf mfssbgf bnd drivfs tif bppropribtf
     * sidf of tif protodol stbtf mbdiinf (dfpfnding on tif subdlbss).
     */
    bbstrbdt void prodfssMfssbgf(bytf mfssbgfTypf, int mfssbgfLfn)
        tirows IOExdfption;

    /*
     * Most blfrts in tif protodol rflbtf to ibndsibking problfms.
     * Alfrts brf dftfdtfd bs tif donnfdtion rfbds dbtb.
     */
    bbstrbdt void ibndsibkfAlfrt(bytf dfsdription) tirows SSLProtodolExdfption;

    /*
     * Sfnds b dibngf dipifr spfd mfssbgf bnd updbtfs tif writf sidf
     * dipifr stbtf so tibt futurf mfssbgfs usf tif just-nfgotibtfd spfd.
     */
    void sfndCibngfCipifrSpfd(Finisifd mfsg, boolfbn lbstMfssbgf)
            tirows IOExdfption {

        output.flusi(); // i.f. ibndsibkf dbtb

        /*
         * Tif writf dipifr stbtf is protfdtfd by tif donnfdtion writf lodk
         * so wf must grbb it wiilf mbking tif dibngf. Wf blso
         * mbkf surf no writfs oddur bftwffn sfnding tif CibngfCipifrSpfd
         * mfssbgf, instblling tif nfw dipifr stbtf, bnd sfnding tif
         * Finisifd mfssbgf.
         *
         * Wf blrfbdy iold SSLEnginf/SSLSodkft "tiis" by virtuf
         * of tiis bfing dbllfd from tif rfbdRfdord dodf.
         */
        OutputRfdord r;
        if (donn != null) {
            r = nfw OutputRfdord(Rfdord.dt_dibngf_dipifr_spfd);
        } flsf {
            r = nfw EnginfOutputRfdord(Rfdord.dt_dibngf_dipifr_spfd, fnginf);
        }

        r.sftVfrsion(protodolVfrsion);
        r.writf(1);     // singlf bytf of dbtb

        if (donn != null) {
            donn.writfLodk.lodk();
            try {
                donn.writfRfdord(r);
                donn.dibngfWritfCipifrs();
                if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
                    mfsg.print(Systfm.out);
                }
                mfsg.writf(output);
                output.flusi();
            } finblly {
                donn.writfLodk.unlodk();
            }
        } flsf {
            syndironizfd (fnginf.writfLodk) {
                fnginf.writfRfdord((EnginfOutputRfdord)r);
                fnginf.dibngfWritfCipifrs();
                if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
                    mfsg.print(Systfm.out);
                }
                mfsg.writf(output);

                if (lbstMfssbgf) {
                    output.sftFinisifdMsg();
                }
                output.flusi();
            }
        }
    }

    /*
     * Singlf bddfss point to kfy dbldulbtion logid.  Givfn tif
     * prf-mbstfr sfdrft bnd tif nondfs from dlifnt bnd sfrvfr,
     * produdf bll tif kfying mbtfribl to bf usfd.
     */
    void dbldulbtfKfys(SfdrftKfy prfMbstfrSfdrft, ProtodolVfrsion vfrsion) {
        SfdrftKfy mbstfr = dbldulbtfMbstfrSfdrft(prfMbstfrSfdrft, vfrsion);
        sfssion.sftMbstfrSfdrft(mbstfr);
        dbldulbtfConnfdtionKfys(mbstfr);
    }


    /*
     * Cbldulbtf tif mbstfr sfdrft from its vbrious domponfnts.  Tiis is
     * usfd for kfy fxdibngf by bll dipifr suitfs.
     *
     * Tif mbstfr sfdrft is tif dbtfnbtion of tirff MD5 ibsifs, fbdi
     * donsisting of tif prf-mbstfr sfdrft bnd b SHA1 ibsi.  Tiosf tirff
     * SHA1 ibsifs brf of (difffrfnt) donstbnt strings, tif prf-mbstfr
     * sfdrft, bnd tif nondfs providfd by tif dlifnt bnd tif sfrvfr.
     */
    privbtf SfdrftKfy dbldulbtfMbstfrSfdrft(SfdrftKfy prfMbstfrSfdrft,
            ProtodolVfrsion rfqufstfdVfrsion) {

        if (dfbug != null && Dfbug.isOn("kfygfn")) {
            HfxDumpEndodfr      dump = nfw HfxDumpEndodfr();

            Systfm.out.println("SESSION KEYGEN:");

            Systfm.out.println("PrfMbstfr Sfdrft:");
            printHfx(dump, prfMbstfrSfdrft.gftEndodfd());

            // Nondfs brf dumpfd witi donnfdtion kfygfn, no
            // bfnffit to doing it twidf
        }

        // Wibt blgs/pbrbms do wf nffd to usf?
        String mbstfrAlg;
        PRF prf;

        if (protodolVfrsion.v >= ProtodolVfrsion.TLS12.v) {
            mbstfrAlg = "SunTls12MbstfrSfdrft";
            prf = dipifrSuitf.prfAlg;
        } flsf {
            mbstfrAlg = "SunTlsMbstfrSfdrft";
            prf = P_NONE;
        }

        String prfHbsiAlg = prf.gftPRFHbsiAlg();
        int prfHbsiLfngti = prf.gftPRFHbsiLfngti();
        int prfBlodkSizf = prf.gftPRFBlodkSizf();

        TlsMbstfrSfdrftPbrbmftfrSpfd spfd = nfw TlsMbstfrSfdrftPbrbmftfrSpfd(
                prfMbstfrSfdrft, protodolVfrsion.mbjor, protodolVfrsion.minor,
                dlnt_rbndom.rbndom_bytfs, svr_rbndom.rbndom_bytfs,
                prfHbsiAlg, prfHbsiLfngti, prfBlodkSizf);

        try {
            KfyGfnfrbtor kg = JssfJdf.gftKfyGfnfrbtor(mbstfrAlg);
            kg.init(spfd);
            rfturn kg.gfnfrbtfKfy();
        } dbtdi (InvblidAlgoritimPbrbmftfrExdfption |
                NoSudiAlgoritimExdfption ibf) {
            // unlikfly to ibppfn, otifrwisf, must bf b providfr fxdfption
            //
            // For RSA prfmbstfr sfdrfts, do not signbl b protodol frror
            // duf to tif Blfidifnbbdifr bttbdk. Sff dommfnts furtifr down.
            if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
                Systfm.out.println("RSA mbstfr sfdrft gfnfrbtion frror:");
                ibf.printStbdkTrbdf(Systfm.out);
            }
            tirow nfw ProvidfrExdfption(ibf);

        }
    }

    /*
     * Cbldulbtf tif kfys nffdfd for tiis donnfdtion, ondf tif sfssion's
     * mbstfr sfdrft ibs bffn dbldulbtfd.  Usfs tif mbstfr kfy bnd nondfs;
     * tif bmount of kfying mbtfribl gfnfrbtfd is b fundtion of tif dipifr
     * suitf tibt's bffn nfgotibtfd.
     *
     * Tiis gfts dbllfd boti on tif "full ibndsibkf" (wifrf wf fxdibngfd
     * b prfmbstfr sfdrft bnd stbrtfd b nfw sfssion) bs wfll bs on tif
     * "fbst ibndsibkf" (wifrf wf just rfsumfd b prf-fxisting sfssion).
     */
    void dbldulbtfConnfdtionKfys(SfdrftKfy mbstfrKfy) {
        /*
         * For boti tif rfbd bnd writf sidfs of tif protodol, wf usf tif
         * mbstfr to gfnfrbtf MAC sfdrfts bnd dipifr kfying mbtfribl.  Blodk
         * dipifrs nffd initiblizbtion vfdtors, wiidi wf blso gfnfrbtf.
         *
         * First wf figurf out iow mudi kfying mbtfribl is nffdfd.
         */
        int ibsiSizf = dipifrSuitf.mbdAlg.sizf;
        boolfbn is_fxportbblf = dipifrSuitf.fxportbblf;
        BulkCipifr dipifr = dipifrSuitf.dipifr;
        int fxpbndfdKfySizf = is_fxportbblf ? dipifr.fxpbndfdKfySizf : 0;

        // Wiidi blgs/pbrbms do wf nffd to usf?
        String kfyMbtfriblAlg;
        PRF prf;

        if (protodolVfrsion.v >= ProtodolVfrsion.TLS12.v) {
            kfyMbtfriblAlg = "SunTls12KfyMbtfribl";
            prf = dipifrSuitf.prfAlg;
        } flsf {
            kfyMbtfriblAlg = "SunTlsKfyMbtfribl";
            prf = P_NONE;
        }

        String prfHbsiAlg = prf.gftPRFHbsiAlg();
        int prfHbsiLfngti = prf.gftPRFHbsiLfngti();
        int prfBlodkSizf = prf.gftPRFBlodkSizf();

        // TLS v1.1 or lbtfr usfs bn fxplidit IV in CBC dipifr suitfs to
        // protfdt bgbinst tif CBC bttbdks.  AEAD/GCM dipifr suitfs in TLS
        // v1.2 or lbtfr usf b fixfd IV bs tif implidit pbrt of tif pbrtiblly
        // implidit nondf tfdiniquf dfsdribfd in RFC 5116.
        int ivSizf = dipifr.ivSizf;
        if (dipifr.dipifrTypf == AEAD_CIPHER) {
            ivSizf = dipifr.fixfdIvSizf;
        } flsf if (protodolVfrsion.v >= ProtodolVfrsion.TLS11.v &&
                dipifr.dipifrTypf == BLOCK_CIPHER) {
            ivSizf = 0;
        }

        TlsKfyMbtfriblPbrbmftfrSpfd spfd = nfw TlsKfyMbtfriblPbrbmftfrSpfd(
            mbstfrKfy, protodolVfrsion.mbjor, protodolVfrsion.minor,
            dlnt_rbndom.rbndom_bytfs, svr_rbndom.rbndom_bytfs,
            dipifr.blgoritim, dipifr.kfySizf, fxpbndfdKfySizf,
            ivSizf, ibsiSizf,
            prfHbsiAlg, prfHbsiLfngti, prfBlodkSizf);

        try {
            KfyGfnfrbtor kg = JssfJdf.gftKfyGfnfrbtor(kfyMbtfriblAlg);
            kg.init(spfd);
            TlsKfyMbtfriblSpfd kfySpfd = (TlsKfyMbtfriblSpfd)kg.gfnfrbtfKfy();

            // Rfturn null if dipifr kfys brf not supposfd to bf gfnfrbtfd.
            dlntWritfKfy = kfySpfd.gftClifntCipifrKfy();
            svrWritfKfy = kfySpfd.gftSfrvfrCipifrKfy();

            // Rfturn null if IVs brf not supposfd to bf gfnfrbtfd.
            dlntWritfIV = kfySpfd.gftClifntIv();
            svrWritfIV = kfySpfd.gftSfrvfrIv();

            // Rfturn null if MAC kfys brf not supposfd to bf gfnfrbtfd.
            dlntMbdSfdrft = kfySpfd.gftClifntMbdKfy();
            svrMbdSfdrft = kfySpfd.gftSfrvfrMbdKfy();
        } dbtdi (GfnfrblSfdurityExdfption f) {
            tirow nfw ProvidfrExdfption(f);
        }

        //
        // Dump tif donnfdtion kfys bs tify'rf gfnfrbtfd.
        //
        if (dfbug != null && Dfbug.isOn("kfygfn")) {
            syndironizfd (Systfm.out) {
                HfxDumpEndodfr  dump = nfw HfxDumpEndodfr();

                Systfm.out.println("CONNECTION KEYGEN:");

                // Inputs:
                Systfm.out.println("Clifnt Nondf:");
                printHfx(dump, dlnt_rbndom.rbndom_bytfs);
                Systfm.out.println("Sfrvfr Nondf:");
                printHfx(dump, svr_rbndom.rbndom_bytfs);
                Systfm.out.println("Mbstfr Sfdrft:");
                printHfx(dump, mbstfrKfy.gftEndodfd());

                // Outputs:
                if (dlntMbdSfdrft != null) {
                    Systfm.out.println("Clifnt MAC writf Sfdrft:");
                    printHfx(dump, dlntMbdSfdrft.gftEndodfd());
                    Systfm.out.println("Sfrvfr MAC writf Sfdrft:");
                    printHfx(dump, svrMbdSfdrft.gftEndodfd());
                } flsf {
                    Systfm.out.println("... no MAC kfys usfd for tiis dipifr");
                }

                if (dlntWritfKfy != null) {
                    Systfm.out.println("Clifnt writf kfy:");
                    printHfx(dump, dlntWritfKfy.gftEndodfd());
                    Systfm.out.println("Sfrvfr writf kfy:");
                    printHfx(dump, svrWritfKfy.gftEndodfd());
                } flsf {
                    Systfm.out.println("... no fndryption kfys usfd");
                }

                if (dlntWritfIV != null) {
                    Systfm.out.println("Clifnt writf IV:");
                    printHfx(dump, dlntWritfIV.gftIV());
                    Systfm.out.println("Sfrvfr writf IV:");
                    printHfx(dump, svrWritfIV.gftIV());
                } flsf {
                    if (protodolVfrsion.v >= ProtodolVfrsion.TLS11.v) {
                        Systfm.out.println(
                                "... no IV dfrivfd for tiis protodol");
                    } flsf {
                        Systfm.out.println("... no IV usfd for tiis dipifr");
                    }
                }
                Systfm.out.flusi();
            }
        }
    }

    privbtf stbtid void printHfx(HfxDumpEndodfr dump, bytf[] bytfs) {
        if (bytfs == null) {
            Systfm.out.println("(kfy bytfs not bvbilbblf)");
        } flsf {
            try {
                dump.fndodfBufffr(bytfs, Systfm.out);
            } dbtdi (IOExdfption f) {
                // just for dfbugging, ignorf tiis
            }
        }
    }

    /**
     * Tirow bn SSLExdfption witi tif spfdififd mfssbgf bnd dbusf.
     * Siortibnd until b nfw SSLExdfption donstrudtor is bddfd.
     * Tiis mftiod nfvfr rfturns.
     */
    stbtid void tirowSSLExdfption(String msg, Tirowbblf dbusf)
            tirows SSLExdfption {
        SSLExdfption f = nfw SSLExdfption(msg);
        f.initCbusf(dbusf);
        tirow f;
    }


    /*
     * Implfmfnt b simplf tbsk dflfgbtor.
     *
     * Wf brf durrfntly implfmfnting tiis bs b singlf dflfgbtor, mby
     * try for pbrbllfl tbsks lbtfr.  Clifnt Autifntidbtion dould
     * bfnffit from tiis, wifrf ClifntKfyExdibngf/CfrtifidbtfVfrify
     * dould bf dbrrifd out in pbrbllfl.
     */
    dlbss DflfgbtfdTbsk<E> implfmfnts Runnbblf {

        privbtf PrivilfgfdExdfptionAdtion<E> pfb;

        DflfgbtfdTbsk(PrivilfgfdExdfptionAdtion<E> pfb) {
            tiis.pfb = pfb;
        }

        publid void run() {
            syndironizfd (fnginf) {
                try {
                    AddfssControllfr.doPrivilfgfd(pfb, fnginf.gftAdd());
                } dbtdi (PrivilfgfdAdtionExdfption pbf) {
                    tirown = pbf.gftExdfption();
                } dbtdi (RuntimfExdfption rtf) {
                    tirown = rtf;
                }
                dflfgbtfdTbsk = null;
                tbskDflfgbtfd = fblsf;
            }
        }
    }

    privbtf <T> void dflfgbtfTbsk(PrivilfgfdExdfptionAdtion<T> pfb) {
        dflfgbtfdTbsk = nfw DflfgbtfdTbsk<T>(pfb);
        tbskDflfgbtfd = fblsf;
        tirown = null;
    }

    DflfgbtfdTbsk<?> gftTbsk() {
        if (!tbskDflfgbtfd) {
            tbskDflfgbtfd = truf;
            rfturn dflfgbtfdTbsk;
        } flsf {
            rfturn null;
        }
    }

    /*
     * Sff if tifrf brf bny tbsks wiidi nffd to bf dflfgbtfd
     *
     * Lodkfd by SSLEnginf.tiis.
     */
    boolfbn tbskOutstbnding() {
        rfturn (dflfgbtfdTbsk != null);
    }

    /*
     * Tif prfvious dbllfr fbilfd for somf rfbson, rfport bbdk tif
     * Exdfption.  Wf won't worry bbout Error's.
     *
     * Lodkfd by SSLEnginf.tiis.
     */
    void difdkTirown() tirows SSLExdfption {
        syndironizfd (tirownLodk) {
            if (tirown != null) {

                String msg = tirown.gftMfssbgf();

                if (msg == null) {
                    msg = "Dflfgbtfd tbsk tirfw Exdfption/Error";
                }

                /*
                 * Sff wibt tif undfrlying typf of fxdfption is.  Wf siould
                 * tirow tif sbmf tiing.  Cibin tirown to tif nfw fxdfption.
                 */
                Exdfption f = tirown;
                tirown = null;

                if (f instbndfof RuntimfExdfption) {
                    tirow nfw RuntimfExdfption(msg, f);
                } flsf if (f instbndfof SSLHbndsibkfExdfption) {
                    tirow (SSLHbndsibkfExdfption)
                        nfw SSLHbndsibkfExdfption(msg).initCbusf(f);
                } flsf if (f instbndfof SSLKfyExdfption) {
                    tirow (SSLKfyExdfption)
                        nfw SSLKfyExdfption(msg).initCbusf(f);
                } flsf if (f instbndfof SSLPffrUnvfrififdExdfption) {
                    tirow (SSLPffrUnvfrififdExdfption)
                        nfw SSLPffrUnvfrififdExdfption(msg).initCbusf(f);
                } flsf if (f instbndfof SSLProtodolExdfption) {
                    tirow (SSLProtodolExdfption)
                        nfw SSLProtodolExdfption(msg).initCbusf(f);
                } flsf {
                    /*
                     * If it's SSLExdfption or bny otifr Exdfption,
                     * wf'll wrbp it in bn SSLExdfption.
                     */
                    tirow nfw SSLExdfption(msg, f);
                }
            }
        }
    }
}

/*
 * Copyrigit (d) 1995, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.www.protodol.ittp;

import jbvb.util.Arrbys;
import jbvb.nft.URL;
import jbvb.nft.URLConnfdtion;
import jbvb.nft.ProtodolExdfption;
import jbvb.nft.HttpRftryExdfption;
import jbvb.nft.PbsswordAutifntidbtion;
import jbvb.nft.Autifntidbtor;
import jbvb.nft.HttpCookif;
import jbvb.nft.InftAddrfss;
import jbvb.nft.UnknownHostExdfption;
import jbvb.nft.SodkftTimfoutExdfption;
import jbvb.nft.SodkftPfrmission;
import jbvb.nft.Proxy;
import jbvb.nft.ProxySflfdtor;
import jbvb.nft.URI;
import jbvb.nft.InftSodkftAddrfss;
import jbvb.nft.CookifHbndlfr;
import jbvb.nft.RfsponsfCbdif;
import jbvb.nft.CbdifRfsponsf;
import jbvb.nft.SfdurfCbdifRfsponsf;
import jbvb.nft.CbdifRfqufst;
import jbvb.nft.URLPfrmission;
import jbvb.nft.Autifntidbtor.RfqufstorTypf;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.io.*;
import jbvb.nft.*;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.Dbtf;
import jbvb.util.Mbp;
import jbvb.util.List;
import jbvb.util.Lodblf;
import jbvb.util.StringTokfnizfr;
import jbvb.util.Itfrbtor;
import jbvb.util.HbsiSft;
import jbvb.util.HbsiMbp;
import jbvb.util.Sft;
import sun.nft.*;
import sun.nft.www.*;
import sun.nft.www.ittp.HttpClifnt;
import sun.nft.www.ittp.PostfrOutputStrfbm;
import sun.nft.www.ittp.CiunkfdInputStrfbm;
import sun.nft.www.ittp.CiunkfdOutputStrfbm;
import sun.util.logging.PlbtformLoggfr;
import jbvb.tfxt.SimplfDbtfFormbt;
import jbvb.util.TimfZonf;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.nio.BytfBufffr;
import stbtid sun.nft.www.protodol.ittp.AutiSdifmf.BASIC;
import stbtid sun.nft.www.protodol.ittp.AutiSdifmf.DIGEST;
import stbtid sun.nft.www.protodol.ittp.AutiSdifmf.NTLM;
import stbtid sun.nft.www.protodol.ittp.AutiSdifmf.NEGOTIATE;
import stbtid sun.nft.www.protodol.ittp.AutiSdifmf.KERBEROS;
import stbtid sun.nft.www.protodol.ittp.AutiSdifmf.UNKNOWN;

/**
 * A dlbss to rfprfsfnt bn HTTP donnfdtion to b rfmotf objfdt.
 */


publid dlbss HttpURLConnfdtion fxtfnds jbvb.nft.HttpURLConnfdtion {

    stbtid String HTTP_CONNECT = "CONNECT";

    stbtid finbl String vfrsion;
    publid stbtid finbl String usfrAgfnt;

    /* mbx # of bllowfd rf-dirfdts */
    stbtid finbl int dffbultmbxRfdirfdts = 20;
    stbtid finbl int mbxRfdirfdts;

    /* Not bll sfrvfrs support tif (Proxy)-Autifntidbtion-Info ifbdfrs.
     * By dffbult, wf don't rfquirf tifm to bf sfnt
     */
    stbtid finbl boolfbn vblidbtfProxy;
    stbtid finbl boolfbn vblidbtfSfrvfr;

    privbtf StrfbmingOutputStrfbm strOutputStrfbm;
    privbtf finbl stbtid String RETRY_MSG1 =
        "dbnnot rftry duf to proxy butifntidbtion, in strfbming modf";
    privbtf finbl stbtid String RETRY_MSG2 =
        "dbnnot rftry duf to sfrvfr butifntidbtion, in strfbming modf";
    privbtf finbl stbtid String RETRY_MSG3 =
        "dbnnot rftry duf to rfdirfdtion, in strfbming modf";

    /*
     * Systfm propfrtifs rflbtfd to frror strfbm ibndling:
     *
     * sun.nft.ittp.frrorstrfbm.fnbblfBufffring = <boolfbn>
     *
     * Witi tif bbovf systfm propfrty sft to truf (dffbult is fblsf),
     * wifn tif rfsponsf dodf is >=400, tif HTTP ibndlfr will try to
     * bufffr tif rfsponsf body (up to b dfrtbin bmount bnd witiin b
     * timf limit). Tius frffing up tif undfrlying sodkft donnfdtion
     * for rfusf. Tif rbtionblf bfiind tiis is tibt usublly wifn tif
     * sfrvfr rfsponds witi b >=400 frror (dlifnt frror or sfrvfr
     * frror, sudi bs 404 filf not found), tif sfrvfr will sfnd b
     * smbll rfsponsf body to fxplbin wio to dontbdt bnd wibt to do to
     * rfdovfr. Witi tiis propfrty sft to truf, fvfn if tif
     * bpplidbtion dofsn't dbll gftErrorStrfbm(), rfbd tif rfsponsf
     * body, bnd tifn dbll dlosf(), tif undfrlying sodkft donnfdtion
     * dbn still bf kfpt-blivf bnd rfusfd. Tif following two systfm
     * propfrtifs providf furtifr dontrol to tif frror strfbm
     * bufffring bfibviour.
     *
     * sun.nft.ittp.frrorstrfbm.timfout = <int>
     *     tif timfout (in millisfd) wbiting tif frror strfbm
     *     to bf bufffrfd; dffbult is 300 ms
     *
     * sun.nft.ittp.frrorstrfbm.bufffrSizf = <int>
     *     tif sizf (in bytfs) to usf for tif bufffring tif frror strfbm;
     *     dffbult is 4k
     */


    /* Siould wf fnbblf bufffring of frror strfbms? */
    privbtf stbtid boolfbn fnbblfESBufffr = fblsf;

    /* timfout wbiting for rfbd for bufffrfd frror strfbm;
     */
    privbtf stbtid int timfout4ESBufffr = 0;

    /* bufffr sizf for bufffrfd frror strfbm;
    */
    privbtf stbtid int bufSizf4ES = 0;

    /*
     * Rfstridt sftting of rfqufst ifbdfrs tirougi tif publid bpi
     * donsistfnt witi JbvbSdript XMLHttpRfqufst2 witi b ffw
     * fxdfptions. Disbllowfd ifbdfrs brf silfntly ignorfd for
     * bbdkwbrds dompbtibility rfbsons rbtifr tibn tirowing b
     * SfdurityExdfption. For fxbmplf, somf bpplfts sft tif
     * Host ifbdfr sindf old JREs did not implfmfnt HTTP 1.1.
     * Additionblly, bny ifbdfr stbrting witi Sfd- is
     * disbllowfd.
     *
     * Tif following ifbdfrs brf bllowfd for iistoridbl rfbsons:
     *
     * Addfpt-Cibrsft, Addfpt-Endoding, Cookif, Cookif2, Dbtf,
     * Rfffrfr, TE, Usfr-Agfnt, ifbdfrs bfginning witi Proxy-.
     *
     * Tif following ifbdfrs brf bllowfd in b limitfd form:
     *
     * Connfdtion: dlosf
     *
     * Sff ittp://www.w3.org/TR/XMLHttpRfqufst2.
     */
    privbtf stbtid finbl boolfbn bllowRfstridtfdHfbdfrs;
    privbtf stbtid finbl Sft<String> rfstridtfdHfbdfrSft;
    privbtf stbtid finbl String[] rfstridtfdHfbdfrs = {
        /* Rfstridtfd by XMLHttpRfqufst2 */
        //"Addfpt-Cibrsft",
        //"Addfpt-Endoding",
        "Addfss-Control-Rfqufst-Hfbdfrs",
        "Addfss-Control-Rfqufst-Mftiod",
        "Connfdtion", /* dlosf is bllowfd */
        "Contfnt-Lfngti",
        //"Cookif",
        //"Cookif2",
        "Contfnt-Trbnsffr-Endoding",
        //"Dbtf",
        //"Expfdt",
        "Host",
        "Kffp-Alivf",
        "Origin",
        // "Rfffrfr",
        // "TE",
        "Trbilfr",
        "Trbnsffr-Endoding",
        "Upgrbdf",
        //"Usfr-Agfnt",
        "Vib"
    };

    stbtid {
        mbxRfdirfdts = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw sun.sfdurity.bdtion.GftIntfgfrAdtion(
                "ittp.mbxRfdirfdts", dffbultmbxRfdirfdts)).intVbluf();
        vfrsion = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("jbvb.vfrsion"));
        String bgfnt = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("ittp.bgfnt"));
        if (bgfnt == null) {
            bgfnt = "Jbvb/"+vfrsion;
        } flsf {
            bgfnt = bgfnt + " Jbvb/"+vfrsion;
        }
        usfrAgfnt = bgfnt;
        vblidbtfProxy = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw sun.sfdurity.bdtion.GftBoolfbnAdtion(
                    "ittp.buti.digfst.vblidbtfProxy")).boolfbnVbluf();
        vblidbtfSfrvfr = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw sun.sfdurity.bdtion.GftBoolfbnAdtion(
                    "ittp.buti.digfst.vblidbtfSfrvfr")).boolfbnVbluf();

        fnbblfESBufffr = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw sun.sfdurity.bdtion.GftBoolfbnAdtion(
                    "sun.nft.ittp.frrorstrfbm.fnbblfBufffring")).boolfbnVbluf();
        timfout4ESBufffr = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw sun.sfdurity.bdtion.GftIntfgfrAdtion(
                    "sun.nft.ittp.frrorstrfbm.timfout", 300)).intVbluf();
        if (timfout4ESBufffr <= 0) {
            timfout4ESBufffr = 300; // usf tif dffbult
        }

        bufSizf4ES = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw sun.sfdurity.bdtion.GftIntfgfrAdtion(
                    "sun.nft.ittp.frrorstrfbm.bufffrSizf", 4096)).intVbluf();
        if (bufSizf4ES <= 0) {
            bufSizf4ES = 4096; // usf tif dffbult
        }

        bllowRfstridtfdHfbdfrs = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw sun.sfdurity.bdtion.GftBoolfbnAdtion(
                    "sun.nft.ittp.bllowRfstridtfdHfbdfrs")).boolfbnVbluf();
        if (!bllowRfstridtfdHfbdfrs) {
            rfstridtfdHfbdfrSft = nfw HbsiSft<String>(rfstridtfdHfbdfrs.lfngti);
            for (int i=0; i < rfstridtfdHfbdfrs.lfngti; i++) {
                rfstridtfdHfbdfrSft.bdd(rfstridtfdHfbdfrs[i].toLowfrCbsf());
            }
        } flsf {
            rfstridtfdHfbdfrSft = null;
        }
    }

    stbtid finbl String ittpVfrsion = "HTTP/1.1";
    stbtid finbl String bddfptString =
        "tfxt/itml, imbgf/gif, imbgf/jpfg, *; q=.2, */*; q=.2";

    // tif following ittp rfqufst ifbdfrs siould NOT ibvf tifir vblufs
    // rfturnfd for sfdurity rfbsons.
    privbtf stbtid finbl String[] EXCLUDE_HEADERS = {
            "Proxy-Autiorizbtion",
            "Autiorizbtion"
    };

    // blso fxdludf systfm dookifs wifn bny migit bf sft
    privbtf stbtid finbl String[] EXCLUDE_HEADERS2= {
            "Proxy-Autiorizbtion",
            "Autiorizbtion",
            "Cookif",
            "Cookif2"
    };

    protfdtfd HttpClifnt ittp;
    protfdtfd Hbndlfr ibndlfr;
    protfdtfd Proxy instProxy;

    privbtf CookifHbndlfr dookifHbndlfr;
    privbtf finbl RfsponsfCbdif dbdifHbndlfr;

    // tif dbdifd rfsponsf, bnd dbdifd rfsponsf ifbdfrs bnd body
    protfdtfd CbdifRfsponsf dbdifdRfsponsf;
    privbtf MfssbgfHfbdfr dbdifdHfbdfrs;
    privbtf InputStrfbm dbdifdInputStrfbm;

    /* output strfbm to sfrvfr */
    protfdtfd PrintStrfbm ps = null;


    /* bufffrfd frror strfbm */
    privbtf InputStrfbm frrorStrfbm = null;

    /* Usfr sft Cookifs */
    privbtf boolfbn sftUsfrCookifs = truf;
    privbtf String usfrCookifs = null;
    privbtf String usfrCookifs2 = null;

    /* Wf only ibvf b singlf stbtid butifntidbtor for now.
     * REMIND:  bbdkwbrds dompbtibility witi JDK 1.1.  Siould bf
     * fliminbtfd for JDK 2.0.
     */
    @Dfprfdbtfd
    privbtf stbtid HttpAutifntidbtor dffbultAuti;

    /* bll tif ifbdfrs wf sfnd
     * NOTE: do *NOT* dump out tif dontfnt of 'rfqufsts' in tif
     * output or stbdktrbdf sindf it mby dontbin sfdurity-sfnsitivf
     * ifbdfrs sudi bs tiosf dffinfd in EXCLUDE_HEADERS.
     */
    privbtf MfssbgfHfbdfr rfqufsts;

    /* Tif ifbdfrs bdtublly sft by tif usfr brf rfdordfd ifrf blso
     */
    privbtf MfssbgfHfbdfr usfrHfbdfrs;

    /* Hfbdfrs bnd rfqufst mftiod dbnnot bf dibngfd
     * ondf tiis flbg is sft in :-
     *     - gftOutputStrfbm()
     *     - gftInputStrfbm())
     *     - donnfdt()
     * Addfss syndironizfd on tiis.
     */
    privbtf boolfbn donnfdting = fblsf;

    /* Tif following two fiflds brf only usfd witi Digfst Autifntidbtion */
    String dombin;      /* Tif list of butifntidbtion dombins */
    DigfstAutifntidbtion.Pbrbmftfrs digfstpbrbms;

    /* Currfnt drfdfntibls in usf */
    AutifntidbtionInfo  durrfntProxyCrfdfntibls = null;
    AutifntidbtionInfo  durrfntSfrvfrCrfdfntibls = null;
    boolfbn             nffdToCifdk = truf;
    privbtf boolfbn doingNTLM2ndStbgf = fblsf; /* doing tif 2nd stbgf of bn NTLM sfrvfr butifntidbtion */
    privbtf boolfbn doingNTLMp2ndStbgf = fblsf; /* doing tif 2nd stbgf of bn NTLM proxy butifntidbtion */

    /* try buti witiout dblling Autifntidbtor. Usfd for trbnspbrfnt NTLM butifntidbtion */
    privbtf boolfbn tryTrbnspbrfntNTLMSfrvfr = truf;
    privbtf boolfbn tryTrbnspbrfntNTLMProxy = truf;

    /* Usfd by Windows spfdifid dodf */
    privbtf Objfdt butiObj;

    /* Sft if tif usfr is mbnublly sftting tif Autiorizbtion or Proxy-Autiorizbtion ifbdfrs */
    boolfbn isUsfrSfrvfrAuti;
    boolfbn isUsfrProxyAuti;

    String sfrvfrAutiKfy, proxyAutiKfy;

    /* Progrfss sourdf */
    protfdtfd ProgrfssSourdf pi;

    /* bll tif rfsponsf ifbdfrs wf gft bbdk */
    privbtf MfssbgfHfbdfr rfsponsfs;
    /* tif strfbm _from_ tif sfrvfr */
    privbtf InputStrfbm inputStrfbm = null;
    /* post strfbm _to_ tif sfrvfr, if bny */
    privbtf PostfrOutputStrfbm postfr = null;

    /* Indidbtfs if tif std. rfqufst ifbdfrs ibvf bffn sft in rfqufsts. */
    privbtf boolfbn sftRfqufsts=fblsf;

    /* Indidbtfs wiftifr b rfqufst ibs blrfbdy fbilfd or not */
    privbtf boolfbn fbilfdOndf=fblsf;

    /* Rfmfmbfrfd Exdfption, wf will tirow it bgbin if somfbody
       dblls gftInputStrfbm bftfr disdonnfdt */
    privbtf Exdfption rfmfmbfrfdExdfption = null;

    /* If wf dfdidf wf wbnt to rfusf b dlifnt, wf put it ifrf */
    privbtf HttpClifnt rfusfClifnt = null;

    /* Tunnfl stbtfs */
    publid fnum TunnflStbtf {
        /* No tunnfl */
        NONE,

        /* Sftting up b tunnfl */
        SETUP,

        /* Tunnfl ibs bffn suddfssfully sftup */
        TUNNELING
    }

    privbtf TunnflStbtf tunnflStbtf = TunnflStbtf.NONE;

    /* Rfdffinf timfouts from jbvb.nft.URLConnfdtion bs wf nffd -1 to mfbn
     * not sft. Tiis is to fnsurf bbdkwbrd dompbtibility.
     */
    privbtf int donnfdtTimfout = NftworkClifnt.DEFAULT_CONNECT_TIMEOUT;
    privbtf int rfbdTimfout = NftworkClifnt.DEFAULT_READ_TIMEOUT;

    /* A pfrmission donvfrtfd from b URLPfrmission */
    privbtf SodkftPfrmission sodkftPfrmission;

    /* Logging support */
    privbtf stbtid finbl PlbtformLoggfr loggfr =
            PlbtformLoggfr.gftLoggfr("sun.nft.www.protodol.ittp.HttpURLConnfdtion");

    /*
     * privilfgfd rfqufst pbssword butifntidbtion
     *
     */
    privbtf stbtid PbsswordAutifntidbtion
    privilfgfdRfqufstPbsswordAutifntidbtion(
                            finbl String iost,
                            finbl InftAddrfss bddr,
                            finbl int port,
                            finbl String protodol,
                            finbl String prompt,
                            finbl String sdifmf,
                            finbl URL url,
                            finbl RfqufstorTypf butiTypf) {
        rfturn jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<PbsswordAutifntidbtion>() {
                publid PbsswordAutifntidbtion run() {
                    if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                        loggfr.finfst("Rfqufsting Autifntidbtion: iost =" + iost + " url = " + url);
                    }
                    PbsswordAutifntidbtion pbss = Autifntidbtor.rfqufstPbsswordAutifntidbtion(
                        iost, bddr, port, protodol,
                        prompt, sdifmf, url, butiTypf);
                    if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                        loggfr.finfst("Autifntidbtion rfturnfd: " + (pbss != null ? pbss.toString() : "null"));
                    }
                    rfturn pbss;
                }
            });
    }

    privbtf boolfbn isRfstridtfdHfbdfr(String kfy, String vbluf) {
        if (bllowRfstridtfdHfbdfrs) {
            rfturn fblsf;
        }

        kfy = kfy.toLowfrCbsf();
        if (rfstridtfdHfbdfrSft.dontbins(kfy)) {
            /*
             * Exdfptions to rfstridtfd ifbdfrs:
             *
             * Allow "Connfdtion: dlosf".
             */
            if (kfy.fqubls("donnfdtion") && vbluf.fqublsIgnorfCbsf("dlosf")) {
                rfturn fblsf;
            }
            rfturn truf;
        } flsf if (kfy.stbrtsWiti("sfd-")) {
            rfturn truf;
        }
        rfturn fblsf;
    }

    /*
     * Cifdks tif vblidity of ittp mfssbgf ifbdfr bnd wiftifr tif ifbdfr
     * is rfstridtfd bnd tirows IllfgblArgumfntExdfption if invblid or
     * rfstridtfd.
     */
    privbtf boolfbn isExtfrnblMfssbgfHfbdfrAllowfd(String kfy, String vbluf) {
        difdkMfssbgfHfbdfr(kfy, vbluf);
        if (!isRfstridtfdHfbdfr(kfy, vbluf)) {
            rfturn truf;
        }
        rfturn fblsf;
    }

    /* Logging support */
    publid stbtid PlbtformLoggfr gftHttpLoggfr() {
        rfturn loggfr;
    }

    /* Usfd for Windows NTLM implfmfntbtion */
    publid Objfdt butiObj() {
        rfturn butiObj;
    }

    publid void butiObj(Objfdt butiObj) {
        tiis.butiObj = butiObj;
    }

    /*
     * difdks tif vblidity of ittp mfssbgf ifbdfr bnd tirows
     * IllfgblArgumfntExdfption if invblid.
     */
    privbtf void difdkMfssbgfHfbdfr(String kfy, String vbluf) {
        dibr LF = '\n';
        int indfx = kfy.indfxOf(LF);
        if (indfx != -1) {
            tirow nfw IllfgblArgumfntExdfption(
                "Illfgbl dibrbdtfr(s) in mfssbgf ifbdfr fifld: " + kfy);
        }
        flsf {
            if (vbluf == null) {
                rfturn;
            }

            indfx = vbluf.indfxOf(LF);
            wiilf (indfx != -1) {
                indfx++;
                if (indfx < vbluf.lfngti()) {
                    dibr d = vbluf.dibrAt(indfx);
                    if ((d==' ') || (d=='\t')) {
                        // ok, difdk tif nfxt oddurrfndf
                        indfx = vbluf.indfxOf(LF, indfx);
                        dontinuf;
                    }
                }
                tirow nfw IllfgblArgumfntExdfption(
                    "Illfgbl dibrbdtfr(s) in mfssbgf ifbdfr vbluf: " + vbluf);
            }
        }
    }

    publid syndironizfd void sftRfqufstMftiod(String mftiod)
                        tirows ProtodolExdfption {
        if (donnfdting) {
            tirow nfw IllfgblStbtfExdfption("donnfdt in progrfss");
        }
        supfr.sftRfqufstMftiod(mftiod);
    }

    /* bdds tif stbndbrd kfy/vbl pbirs to rfqfsts if nfdfssbry & writf to
     * givfn PrintStrfbm
     */
    privbtf void writfRfqufsts() tirows IOExdfption {
        /* print bll mfssbgf ifbdfrs in tif MfssbgfHfbdfr
         * onto tif wirf - bll tif onfs wf'vf sft bnd bny
         * otifrs tibt ibvf bffn sft
         */
        // sfnd bny prf-fmptivf butifntidbtion
        if (ittp.usingProxy && tunnflStbtf() != TunnflStbtf.TUNNELING) {
            sftPrffmptivfProxyAutifntidbtion(rfqufsts);
        }
        if (!sftRfqufsts) {

            /* Wf'rf vfry pbrtidulbr bbout tif ordfr in wiidi wf
             * sft tif rfqufst ifbdfrs ifrf.  Tif ordfr siould not
             * mbttfr, but somf dbrflfss CGI progrbms ibvf bffn
             * writtfn to fxpfdt b vfry pbrtidulbr ordfr of tif
             * stbndbrd ifbdfrs.  To nbmf nbmfs, tif ordfr in wiidi
             * Nbvigbtor3.0 sfnds tifm.  In pbrtidulbr, wf mbkf *surf*
             * to sfnd Contfnt-typf: <> bnd Contfnt-lfngti:<> sfdond
             * to lbst bnd lbst, rfspfdtivfly, in tif dbsf of b POST
             * rfqufst.
             */
            if (!fbilfdOndf) {
                difdkURLFilf();
                rfqufsts.prfpfnd(mftiod + " " + gftRfqufstURI()+" "  +
                                 ittpVfrsion, null);
            }
            if (!gftUsfCbdifs()) {
                rfqufsts.sftIfNotSft ("Cbdif-Control", "no-dbdif");
                rfqufsts.sftIfNotSft ("Prbgmb", "no-dbdif");
            }
            rfqufsts.sftIfNotSft("Usfr-Agfnt", usfrAgfnt);
            int port = url.gftPort();
            String iost = stripIPv6ZonfId(url.gftHost());
            if (port != -1 && port != url.gftDffbultPort()) {
                iost += ":" + String.vblufOf(port);
            }
            String rfqHost = rfqufsts.findVbluf("Host");
            if (rfqHost == null ||
                (!rfqHost.fqublsIgnorfCbsf(iost) && !difdkSftHost()))
            {
                rfqufsts.sft("Host", iost);
            }
            rfqufsts.sftIfNotSft("Addfpt", bddfptString);

            /*
             * For HTTP/1.1 tif dffbult bfibvior is to kffp donnfdtions blivf.
             * Howfvfr, wf mby bf tblking to b 1.0 sfrvfr so wf siould sft
             * kffp-blivf just in dbsf, fxdfpt if wf ibvf fndountfrfd bn frror
             * or if kffp blivf is disbblfd vib b systfm propfrty
             */

            // Try kffp-blivf only on first bttfmpt
            if (!fbilfdOndf && ittp.gftHttpKffpAlivfSft()) {
                if (ittp.usingProxy && tunnflStbtf() != TunnflStbtf.TUNNELING) {
                    rfqufsts.sftIfNotSft("Proxy-Connfdtion", "kffp-blivf");
                } flsf {
                    rfqufsts.sftIfNotSft("Connfdtion", "kffp-blivf");
                }
            } flsf {
                /*
                 * RFC 2616 HTTP/1.1 sfdtion 14.10 sbys:
                 * HTTP/1.1 bpplidbtions tibt do not support pfrsistfnt
                 * donnfdtions MUST indludf tif "dlosf" donnfdtion option
                 * in fvfry mfssbgf
                 */
                rfqufsts.sftIfNotSft("Connfdtion", "dlosf");
            }
            // Sft modififd sindf if nfdfssbry
            long modTimf = gftIfModififdSindf();
            if (modTimf != 0 ) {
                Dbtf dbtf = nfw Dbtf(modTimf);
                //usf tif prfffrrfd dbtf formbt bddording to RFC 2068(HTTP1.1),
                // RFC 822 bnd RFC 1123
                SimplfDbtfFormbt fo =
                  nfw SimplfDbtfFormbt ("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Lodblf.US);
                fo.sftTimfZonf(TimfZonf.gftTimfZonf("GMT"));
                rfqufsts.sftIfNotSft("If-Modififd-Sindf", fo.formbt(dbtf));
            }
            // difdk for prffmptivf butiorizbtion
            AutifntidbtionInfo sbuti = AutifntidbtionInfo.gftSfrvfrAuti(url);
            if (sbuti != null && sbuti.supportsPrffmptivfAutiorizbtion() ) {
                // Sfts "Autiorizbtion"
                rfqufsts.sftIfNotSft(sbuti.gftHfbdfrNbmf(), sbuti.gftHfbdfrVbluf(url,mftiod));
                durrfntSfrvfrCrfdfntibls = sbuti;
            }

            if (!mftiod.fqubls("PUT") && (postfr != null || strfbming())) {
                rfqufsts.sftIfNotSft ("Contfnt-typf",
                        "bpplidbtion/x-www-form-urlfndodfd");
            }

            boolfbn diunkfd = fblsf;

            if (strfbming()) {
                if (diunkLfngti != -1) {
                    rfqufsts.sft ("Trbnsffr-Endoding", "diunkfd");
                    diunkfd = truf;
                } flsf { /* fixfd dontfnt lfngti */
                    if (fixfdContfntLfngtiLong != -1) {
                        rfqufsts.sft ("Contfnt-Lfngti",
                                      String.vblufOf(fixfdContfntLfngtiLong));
                    } flsf if (fixfdContfntLfngti != -1) {
                        rfqufsts.sft ("Contfnt-Lfngti",
                                      String.vblufOf(fixfdContfntLfngti));
                    }
                }
            } flsf if (postfr != null) {
                /* bdd Contfnt-Lfngti & POST/PUT dbtb */
                syndironizfd (postfr) {
                    /* dlosf it, so no morf dbtb dbn bf bddfd */
                    postfr.dlosf();
                    rfqufsts.sft("Contfnt-Lfngti",
                                 String.vblufOf(postfr.sizf()));
                }
            }

            if (!diunkfd) {
                if (rfqufsts.findVbluf("Trbnsffr-Endoding") != null) {
                    rfqufsts.rfmovf("Trbnsffr-Endoding");
                    if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.WARNING)) {
                        loggfr.wbrning(
                            "usf strfbming modf for diunkfd fndoding");
                    }
                }
            }

            // gft bpplidbblf dookifs bbsfd on tif uri bnd rfqufst ifbdfrs
            // bdd tifm to tif fxisting rfqufst ifbdfrs
            sftCookifHfbdfr();

            sftRfqufsts=truf;
        }
        if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            loggfr.finf(rfqufsts.toString());
        }
        ittp.writfRfqufsts(rfqufsts, postfr, strfbming());
        if (ps.difdkError()) {
            String proxyHost = ittp.gftProxyHostUsfd();
            int proxyPort = ittp.gftProxyPortUsfd();
            disdonnfdtIntfrnbl();
            if (fbilfdOndf) {
                tirow nfw IOExdfption("Error writing to sfrvfr");
            } flsf { // try ondf morf
                fbilfdOndf=truf;
                if (proxyHost != null) {
                    sftProxifdClifnt(url, proxyHost, proxyPort);
                } flsf {
                    sftNfwClifnt (url);
                }
                ps = (PrintStrfbm) ittp.gftOutputStrfbm();
                donnfdtfd=truf;
                rfsponsfs = nfw MfssbgfHfbdfr();
                sftRfqufsts=fblsf;
                writfRfqufsts();
            }
        }
    }

    privbtf boolfbn difdkSftHost() {
        SfdurityMbnbgfr s = Systfm.gftSfdurityMbnbgfr();
        if (s != null) {
            String nbmf = s.gftClbss().gftNbmf();
            if (nbmf.fqubls("sun.plugin2.bpplft.AWTApplftSfdurityMbnbgfr") ||
                nbmf.fqubls("sun.plugin2.bpplft.FXApplftSfdurityMbnbgfr") ||
                nbmf.fqubls("dom.sun.jbvbws.sfdurity.JbvbWfbStbrtSfdurity") ||
                nbmf.fqubls("sun.plugin.sfdurity.AdtivbtorSfdurityMbnbgfr"))
            {
                int CHECK_SET_HOST = -2;
                try {
                    s.difdkConnfdt(url.toExtfrnblForm(), CHECK_SET_HOST);
                } dbtdi (SfdurityExdfption fx) {
                    rfturn fblsf;
                }
            }
        }
        rfturn truf;
    }

    privbtf void difdkURLFilf() {
        SfdurityMbnbgfr s = Systfm.gftSfdurityMbnbgfr();
        if (s != null) {
            String nbmf = s.gftClbss().gftNbmf();
            if (nbmf.fqubls("sun.plugin2.bpplft.AWTApplftSfdurityMbnbgfr") ||
                nbmf.fqubls("sun.plugin2.bpplft.FXApplftSfdurityMbnbgfr") ||
                nbmf.fqubls("dom.sun.jbvbws.sfdurity.JbvbWfbStbrtSfdurity") ||
                nbmf.fqubls("sun.plugin.sfdurity.AdtivbtorSfdurityMbnbgfr"))
            {
                int CHECK_SUBPATH = -3;
                try {
                    s.difdkConnfdt(url.toExtfrnblForm(), CHECK_SUBPATH);
                } dbtdi (SfdurityExdfption fx) {
                    tirow nfw SfdurityExdfption("dfnifd bddfss outsidf b pfrmittfd URL subpbti", fx);
                }
            }
        }
    }

    /**
     * Crfbtf b nfw HttpClifnt objfdt, bypbssing tif dbdif of
     * HTTP dlifnt objfdts/donnfdtions.
     *
     * @pbrbm url       tif URL bfing bddfssfd
     */
    protfdtfd void sftNfwClifnt (URL url)
    tirows IOExdfption {
        sftNfwClifnt(url, fblsf);
    }

    /**
     * Obtbin b HttpsClifnt objfdt. Usf tif dbdifd dopy if spfdififd.
     *
     * @pbrbm url       tif URL bfing bddfssfd
     * @pbrbm usfCbdif  wiftifr tif dbdifd donnfdtion siould bf usfd
     *        if prfsfnt
     */
    protfdtfd void sftNfwClifnt (URL url, boolfbn usfCbdif)
        tirows IOExdfption {
        ittp = HttpClifnt.Nfw(url, null, -1, usfCbdif, donnfdtTimfout, tiis);
        ittp.sftRfbdTimfout(rfbdTimfout);
    }


    /**
     * Crfbtf b nfw HttpClifnt objfdt, sft up so tibt it usfs
     * pfr-instbndf proxying to tif givfn HTTP proxy.  Tiis
     * bypbssfs tif dbdif of HTTP dlifnt objfdts/donnfdtions.
     *
     * @pbrbm url       tif URL bfing bddfssfd
     * @pbrbm proxyHost tif proxy iost to usf
     * @pbrbm proxyPort tif proxy port to usf
     */
    protfdtfd void sftProxifdClifnt (URL url, String proxyHost, int proxyPort)
    tirows IOExdfption {
        sftProxifdClifnt(url, proxyHost, proxyPort, fblsf);
    }

    /**
     * Obtbin b HttpClifnt objfdt, sft up so tibt it usfs pfr-instbndf
     * proxying to tif givfn HTTP proxy. Usf tif dbdifd dopy of HTTP
     * dlifnt objfdts/donnfdtions if spfdififd.
     *
     * @pbrbm url       tif URL bfing bddfssfd
     * @pbrbm proxyHost tif proxy iost to usf
     * @pbrbm proxyPort tif proxy port to usf
     * @pbrbm usfCbdif  wiftifr tif dbdifd donnfdtion siould bf usfd
     *        if prfsfnt
     */
    protfdtfd void sftProxifdClifnt (URL url,
                                           String proxyHost, int proxyPort,
                                           boolfbn usfCbdif)
        tirows IOExdfption {
        proxifdConnfdt(url, proxyHost, proxyPort, usfCbdif);
    }

    protfdtfd void proxifdConnfdt(URL url,
                                           String proxyHost, int proxyPort,
                                           boolfbn usfCbdif)
        tirows IOExdfption {
        ittp = HttpClifnt.Nfw (url, proxyHost, proxyPort, usfCbdif,
            donnfdtTimfout, tiis);
        ittp.sftRfbdTimfout(rfbdTimfout);
    }

    protfdtfd HttpURLConnfdtion(URL u, Hbndlfr ibndlfr)
    tirows IOExdfption {
        // wf sft proxy == null to distinguisi tiis dbsf witi tif dbsf
        // wifn pfr donnfdtion proxy is sft
        tiis(u, null, ibndlfr);
    }

    publid HttpURLConnfdtion(URL u, String iost, int port) {
        tiis(u, nfw Proxy(Proxy.Typf.HTTP, InftSodkftAddrfss.drfbtfUnrfsolvfd(iost, port)));
    }

    /** tiis donstrudtor is usfd by otifr protodol ibndlfrs sudi bs ftp
        tibt wbnt to usf ittp to fftdi urls on tifir bfiblf.*/
    publid HttpURLConnfdtion(URL u, Proxy p) {
        tiis(u, p, nfw Hbndlfr());
    }

    protfdtfd HttpURLConnfdtion(URL u, Proxy p, Hbndlfr ibndlfr) {
        supfr(u);
        rfqufsts = nfw MfssbgfHfbdfr();
        rfsponsfs = nfw MfssbgfHfbdfr();
        usfrHfbdfrs = nfw MfssbgfHfbdfr();
        tiis.ibndlfr = ibndlfr;
        instProxy = p;
        if (instProxy instbndfof sun.nft.ApplidbtionProxy) {
            /* Applidbtion sft Proxifs siould not ibvf bddfss to dookifs
             * in b sfdurf fnvironmfnt unlfss fxpliditly bllowfd. */
            try {
                dookifHbndlfr = CookifHbndlfr.gftDffbult();
            } dbtdi (SfdurityExdfption sf) { /* swbllow fxdfption */ }
        } flsf {
            dookifHbndlfr = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw jbvb.sfdurity.PrivilfgfdAdtion<CookifHbndlfr>() {
                publid CookifHbndlfr run() {
                    rfturn CookifHbndlfr.gftDffbult();
                }
            });
        }
        dbdifHbndlfr = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<RfsponsfCbdif>() {
                publid RfsponsfCbdif run() {
                rfturn RfsponsfCbdif.gftDffbult();
            }
        });
    }

    /**
     * @dfprfdbtfd.  Usf jbvb.nft.Autifntidbtor.sftDffbult() instfbd.
     */
    @Dfprfdbtfd
    publid stbtid void sftDffbultAutifntidbtor(HttpAutifntidbtor b) {
        dffbultAuti = b;
    }

    /**
     * opfns b strfbm bllowing rfdirfdts only to tif sbmf iost.
     */
    publid stbtid InputStrfbm opfnConnfdtionCifdkRfdirfdts(URLConnfdtion d)
        tirows IOExdfption
    {
        boolfbn rfdir;
        int rfdirfdts = 0;
        InputStrfbm in;

        do {
            if (d instbndfof HttpURLConnfdtion) {
                ((HttpURLConnfdtion) d).sftInstbndfFollowRfdirfdts(fblsf);
            }

            // Wf wbnt to opfn tif input strfbm bfforf
            // gftting ifbdfrs, bfdbusf gftHfbdfrFifld()
            // ft bl swbllow IOExdfptions.
            in = d.gftInputStrfbm();
            rfdir = fblsf;

            if (d instbndfof HttpURLConnfdtion) {
                HttpURLConnfdtion ittp = (HttpURLConnfdtion) d;
                int stbt = ittp.gftRfsponsfCodf();
                if (stbt >= 300 && stbt <= 307 && stbt != 306 &&
                        stbt != HttpURLConnfdtion.HTTP_NOT_MODIFIED) {
                    URL bbsf = ittp.gftURL();
                    String lod = ittp.gftHfbdfrFifld("Lodbtion");
                    URL tbrgft = null;
                    if (lod != null) {
                        tbrgft = nfw URL(bbsf, lod);
                    }
                    ittp.disdonnfdt();
                    if (tbrgft == null
                        || !bbsf.gftProtodol().fqubls(tbrgft.gftProtodol())
                        || bbsf.gftPort() != tbrgft.gftPort()
                        || !iostsEqubl(bbsf, tbrgft)
                        || rfdirfdts >= 5)
                    {
                        tirow nfw SfdurityExdfption("illfgbl URL rfdirfdt");
                    }
                    rfdir = truf;
                    d = tbrgft.opfnConnfdtion();
                    rfdirfdts++;
                }
            }
        } wiilf (rfdir);
        rfturn in;
    }


    //
    // Sbmf bs jbvb.nft.URL.iostsEqubl
    //
    privbtf stbtid boolfbn iostsEqubl(URL u1, URL u2) {
        finbl String i1 = u1.gftHost();
        finbl String i2 = u2.gftHost();

        if (i1 == null) {
            rfturn i2 == null;
        } flsf if (i2 == null) {
            rfturn fblsf;
        } flsf if (i1.fqublsIgnorfCbsf(i2)) {
            rfturn truf;
        }
        // Hbvf to rfsolvf bddrfssfs bfforf dompbring, otifrwisf
        // nbmfs likf tbdiyon bnd tbdiyon.fng would dompbrf difffrfnt
        finbl boolfbn rfsult[] = {fblsf};

        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                publid Void run() {
                try {
                    InftAddrfss b1 = InftAddrfss.gftByNbmf(i1);
                    InftAddrfss b2 = InftAddrfss.gftByNbmf(i2);
                    rfsult[0] = b1.fqubls(b2);
                } dbtdi(UnknownHostExdfption | SfdurityExdfption f) {
                }
                rfturn null;
            }
        });

        rfturn rfsult[0];
    }

    // ovfrriddfn in HTTPS subdlbss

    publid void donnfdt() tirows IOExdfption {
        syndironizfd (tiis) {
            donnfdting = truf;
        }
        plbinConnfdt();
    }

    privbtf boolfbn difdkRfusfConnfdtion () {
        if (donnfdtfd) {
            rfturn truf;
        }
        if (rfusfClifnt != null) {
            ittp = rfusfClifnt;
            ittp.sftRfbdTimfout(gftRfbdTimfout());
            ittp.rfusf = fblsf;
            rfusfClifnt = null;
            donnfdtfd = truf;
            rfturn truf;
        }
        rfturn fblsf;
    }

    privbtf String gftHostAndPort(URL url) {
        String iost = url.gftHost();
        finbl String iostbrg = iost;
        try {
            // lookup iostnbmf bnd usf IP bddrfss if bvbilbblf
            iost = AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdExdfptionAdtion<String>() {
                    publid String run() tirows IOExdfption {
                            InftAddrfss bddr = InftAddrfss.gftByNbmf(iostbrg);
                            rfturn bddr.gftHostAddrfss();
                    }
                }
            );
        } dbtdi (PrivilfgfdAdtionExdfption f) {}
        int port = url.gftPort();
        if (port == -1) {
            String sdifmf = url.gftProtodol();
            if ("ittp".fqubls(sdifmf)) {
                rfturn iost + ":80";
            } flsf { // sdifmf must bf ittps
                rfturn iost + ":443";
            }
        }
        rfturn iost + ":" + Intfgfr.toString(port);
    }

    protfdtfd void plbinConnfdt()  tirows IOExdfption {
        syndironizfd (tiis) {
            if (donnfdtfd) {
                rfturn;
            }
        }
        SodkftPfrmission p = URLtoSodkftPfrmission(tiis.url);
        if (p != null) {
            try {
                AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdExdfptionAdtion<Void>() {
                        publid Void run() tirows IOExdfption {
                            plbinConnfdt0();
                            rfturn null;
                        }
                    }, null, p
                );
            } dbtdi (PrivilfgfdAdtionExdfption f) {
                    tirow (IOExdfption) f.gftExdfption();
            }
        } flsf {
            // run witiout bdditionbl pfrmission
            plbinConnfdt0();
        }
    }

    /**
     *  if tif dbllfr ibs b URLPfrmission for donnfdting to tif
     *  givfn URL, tifn rfturn b SodkftPfrmission wiidi pfrmits
     *  bddfss to tibt dfstinbtion. Rfturn null otifrwisf. Tif pfrmission
     *  is dbdifd in b fifld (wiidi dbn only bf dibngfd by rfdirfdts)
     */
    SodkftPfrmission URLtoSodkftPfrmission(URL url) tirows IOExdfption {

        if (sodkftPfrmission != null) {
            rfturn sodkftPfrmission;
        }

        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();

        if (sm == null) {
            rfturn null;
        }

        // tif pfrmission, wiidi wf migit grbnt

        SodkftPfrmission nfwPfrm = nfw SodkftPfrmission(
            gftHostAndPort(url), "donnfdt"
        );

        String bdtions = gftRfqufstMftiod()+":" +
                gftUsfrSftHfbdfrs().gftHfbdfrNbmfsInList();

        String urlstring = url.gftProtodol() + "://" + url.gftAutiority()
                + url.gftPbti();

        URLPfrmission p = nfw URLPfrmission(urlstring, bdtions);
        try {
            sm.difdkPfrmission(p);
            sodkftPfrmission = nfwPfrm;
            rfturn sodkftPfrmission;
        } dbtdi (SfdurityExdfption f) {
            // fbll tiru
        }
        rfturn null;
    }

    protfdtfd void plbinConnfdt0()  tirows IOExdfption {
        // try to sff if rfqufst dbn bf sfrvfd from lodbl dbdif
        if (dbdifHbndlfr != null && gftUsfCbdifs()) {
            try {
                URI uri = PbrsfUtil.toURI(url);
                if (uri != null) {
                    dbdifdRfsponsf = dbdifHbndlfr.gft(uri, gftRfqufstMftiod(), rfqufsts.gftHfbdfrs(EXCLUDE_HEADERS));
                    if ("ittps".fqublsIgnorfCbsf(uri.gftSdifmf())
                        && !(dbdifdRfsponsf instbndfof SfdurfCbdifRfsponsf)) {
                        dbdifdRfsponsf = null;
                    }
                    if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                        loggfr.finfst("Cbdif Rfqufst for " + uri + " / " + gftRfqufstMftiod());
                        loggfr.finfst("From dbdif: " + (dbdifdRfsponsf != null ? dbdifdRfsponsf.toString() : "null"));
                    }
                    if (dbdifdRfsponsf != null) {
                        dbdifdHfbdfrs = mbpToMfssbgfHfbdfr(dbdifdRfsponsf.gftHfbdfrs());
                        dbdifdInputStrfbm = dbdifdRfsponsf.gftBody();
                    }
                }
            } dbtdi (IOExdfption iofx) {
                // ignorf bnd dommfndf normbl donnfdtion
            }
            if (dbdifdHfbdfrs != null && dbdifdInputStrfbm != null) {
                donnfdtfd = truf;
                rfturn;
            } flsf {
                dbdifdRfsponsf = null;
            }
        }
        try {
            /* Try to opfn donnfdtions using tif following sdifmf,
             * rfturn on tif first onf tibt's suddfssful:
             * 1) if (instProxy != null)
             *        donnfdt to instProxy; rbisf fxdfption if fbilfd
             * 2) flsf usf systfm dffbult ProxySflfdtor
             * 3) is 2) fbils, mbkf dirfdt donnfdtion
             */

            if (instProxy == null) { // no instbndf Proxy is sft
                /**
                 * Do wf ibvf to usf b proxy?
                 */
                ProxySflfdtor sfl =
                    jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                        nfw jbvb.sfdurity.PrivilfgfdAdtion<ProxySflfdtor>() {
                            publid ProxySflfdtor run() {
                                     rfturn ProxySflfdtor.gftDffbult();
                                 }
                             });
                if (sfl != null) {
                    URI uri = sun.nft.www.PbrsfUtil.toURI(url);
                    if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                        loggfr.finfst("ProxySflfdtor Rfqufst for " + uri);
                    }
                    Itfrbtor<Proxy> it = sfl.sflfdt(uri).itfrbtor();
                    Proxy p;
                    wiilf (it.ibsNfxt()) {
                        p = it.nfxt();
                        try {
                            if (!fbilfdOndf) {
                                ittp = gftNfwHttpClifnt(url, p, donnfdtTimfout);
                                ittp.sftRfbdTimfout(rfbdTimfout);
                            } flsf {
                                // mbkf surf to donstrudt nfw donnfdtion if first
                                // bttfmpt fbilfd
                                ittp = gftNfwHttpClifnt(url, p, donnfdtTimfout, fblsf);
                                ittp.sftRfbdTimfout(rfbdTimfout);
                            }
                            if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                                if (p != null) {
                                    loggfr.finfst("Proxy usfd: " + p.toString());
                                }
                            }
                            brfbk;
                        } dbtdi (IOExdfption iofx) {
                            if (p != Proxy.NO_PROXY) {
                                sfl.donnfdtFbilfd(uri, p.bddrfss(), iofx);
                                if (!it.ibsNfxt()) {
                                    // fbllbbdk to dirfdt donnfdtion
                                    ittp = gftNfwHttpClifnt(url, null, donnfdtTimfout, fblsf);
                                    ittp.sftRfbdTimfout(rfbdTimfout);
                                    brfbk;
                                }
                            } flsf {
                                tirow iofx;
                            }
                            dontinuf;
                        }
                    }
                } flsf {
                    // No proxy sflfdtor, drfbtf ittp dlifnt witi no proxy
                    if (!fbilfdOndf) {
                        ittp = gftNfwHttpClifnt(url, null, donnfdtTimfout);
                        ittp.sftRfbdTimfout(rfbdTimfout);
                    } flsf {
                        // mbkf surf to donstrudt nfw donnfdtion if first
                        // bttfmpt fbilfd
                        ittp = gftNfwHttpClifnt(url, null, donnfdtTimfout, fblsf);
                        ittp.sftRfbdTimfout(rfbdTimfout);
                    }
                }
            } flsf {
                if (!fbilfdOndf) {
                    ittp = gftNfwHttpClifnt(url, instProxy, donnfdtTimfout);
                    ittp.sftRfbdTimfout(rfbdTimfout);
                } flsf {
                    // mbkf surf to donstrudt nfw donnfdtion if first
                    // bttfmpt fbilfd
                    ittp = gftNfwHttpClifnt(url, instProxy, donnfdtTimfout, fblsf);
                    ittp.sftRfbdTimfout(rfbdTimfout);
                }
            }

            ps = (PrintStrfbm)ittp.gftOutputStrfbm();
        } dbtdi (IOExdfption f) {
            tirow f;
        }
        // donstrudtor to HTTP dlifnt dblls opfnsfrvfr
        donnfdtfd = truf;
    }

    // subdlbss HttpsClifnt will ovfrwritf & rfturn bn instbndf of HttpsClifnt
    protfdtfd HttpClifnt gftNfwHttpClifnt(URL url, Proxy p, int donnfdtTimfout)
        tirows IOExdfption {
        rfturn HttpClifnt.Nfw(url, p, donnfdtTimfout, tiis);
    }

    // subdlbss HttpsClifnt will ovfrwritf & rfturn bn instbndf of HttpsClifnt
    protfdtfd HttpClifnt gftNfwHttpClifnt(URL url, Proxy p,
                                          int donnfdtTimfout, boolfbn usfCbdif)
        tirows IOExdfption {
        rfturn HttpClifnt.Nfw(url, p, donnfdtTimfout, usfCbdif, tiis);
    }

    privbtf void fxpfdt100Continuf() tirows IOExdfption {
            // Expfdt: 100-Continuf wbs sft, so difdk tif rfturn dodf for
            // Addfptbndf
            int oldTimfout = ittp.gftRfbdTimfout();
            boolfbn fnfordfTimfOut = fblsf;
            boolfbn timfdOut = fblsf;
            if (oldTimfout <= 0) {
                // 5s rfbd timfout in dbsf tif sfrvfr dofsn't undfrstbnd
                // Expfdt: 100-Continuf
                ittp.sftRfbdTimfout(5000);
                fnfordfTimfOut = truf;
            }

            try {
                ittp.pbrsfHTTP(rfsponsfs, pi, tiis);
            } dbtdi (SodkftTimfoutExdfption sf) {
                if (!fnfordfTimfOut) {
                    tirow sf;
                }
                timfdOut = truf;
                ittp.sftIgnorfContinuf(truf);
            }
            if (!timfdOut) {
                // Cbn't usf gftRfsponsfCodf() yft
                String rfsp = rfsponsfs.gftVbluf(0);
                // Pbrsf tif rfsponsf wiidi is of tif form:
                // HTTP/1.1 417 Expfdtbtion Fbilfd
                // HTTP/1.1 100 Continuf
                if (rfsp != null && rfsp.stbrtsWiti("HTTP/")) {
                    String[] sb = rfsp.split("\\s+");
                    rfsponsfCodf = -1;
                    try {
                        // Rfsponsf dodf is 2nd tokfn on tif linf
                        if (sb.lfngti > 1)
                            rfsponsfCodf = Intfgfr.pbrsfInt(sb[1]);
                    } dbtdi (NumbfrFormbtExdfption numbfrFormbtExdfption) {
                    }
                }
                if (rfsponsfCodf != 100) {
                    tirow nfw ProtodolExdfption("Sfrvfr rfjfdtfd opfrbtion");
                }
            }

            ittp.sftRfbdTimfout(oldTimfout);

            rfsponsfCodf = -1;
            rfsponsfs.rfsft();
            // Prodffd
    }

    /*
     * Allowbblf input/output sfqufndfs:
     * [intfrprftfd bs rfqufst fntity]
     * - gft output, [writf output,] gft input, [rfbd input]
     * - gft output, [writf output]
     * [intfrprftfd bs GET]
     * - gft input, [rfbd input]
     * Disbllowfd:
     * - gft input, [rfbd input,] gft output, [writf output]
     */

    @Ovfrridf
    publid syndironizfd OutputStrfbm gftOutputStrfbm() tirows IOExdfption {
        donnfdting = truf;
        SodkftPfrmission p = URLtoSodkftPfrmission(tiis.url);

        if (p != null) {
            try {
                rfturn AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdExdfptionAdtion<OutputStrfbm>() {
                        publid OutputStrfbm run() tirows IOExdfption {
                            rfturn gftOutputStrfbm0();
                        }
                    }, null, p
                );
            } dbtdi (PrivilfgfdAdtionExdfption f) {
                tirow (IOExdfption) f.gftExdfption();
            }
        } flsf {
            rfturn gftOutputStrfbm0();
        }
    }

    privbtf syndironizfd OutputStrfbm gftOutputStrfbm0() tirows IOExdfption {
        try {
            if (!doOutput) {
                tirow nfw ProtodolExdfption("dbnnot writf to b URLConnfdtion"
                               + " if doOutput=fblsf - dbll sftDoOutput(truf)");
            }

            if (mftiod.fqubls("GET")) {
                mftiod = "POST"; // Bbdkwbrd dompbtibility
            }
            if ("TRACE".fqubls(mftiod) && "ittp".fqubls(url.gftProtodol())) {
                tirow nfw ProtodolExdfption("HTTP mftiod TRACE" +
                                            " dofsn't support output");
            }

            // if tifrf's blrfbdy bn input strfbm opfn, tirow bn fxdfption
            if (inputStrfbm != null) {
                tirow nfw ProtodolExdfption("Cbnnot writf output bftfr rfbding input.");
            }

            if (!difdkRfusfConnfdtion())
                donnfdt();

            boolfbn fxpfdtContinuf = fblsf;
            String fxpfdts = rfqufsts.findVbluf("Expfdt");
            if ("100-Continuf".fqublsIgnorfCbsf(fxpfdts) && strfbming()) {
                ittp.sftIgnorfContinuf(fblsf);
                fxpfdtContinuf = truf;
            }

            if (strfbming() && strOutputStrfbm == null) {
                writfRfqufsts();
            }

            if (fxpfdtContinuf) {
                fxpfdt100Continuf();
            }
            ps = (PrintStrfbm)ittp.gftOutputStrfbm();
            if (strfbming()) {
                if (strOutputStrfbm == null) {
                    if (diunkLfngti != -1) { /* diunkfd */
                         strOutputStrfbm = nfw StrfbmingOutputStrfbm(
                               nfw CiunkfdOutputStrfbm(ps, diunkLfngti), -1L);
                    } flsf { /* must bf fixfd dontfnt lfngti */
                        long lfngti = 0L;
                        if (fixfdContfntLfngtiLong != -1) {
                            lfngti = fixfdContfntLfngtiLong;
                        } flsf if (fixfdContfntLfngti != -1) {
                            lfngti = fixfdContfntLfngti;
                        }
                        strOutputStrfbm = nfw StrfbmingOutputStrfbm(ps, lfngti);
                    }
                }
                rfturn strOutputStrfbm;
            } flsf {
                if (postfr == null) {
                    postfr = nfw PostfrOutputStrfbm();
                }
                rfturn postfr;
            }
        } dbtdi (RuntimfExdfption f) {
            disdonnfdtIntfrnbl();
            tirow f;
        } dbtdi (ProtodolExdfption f) {
            // Sbvf tif rfsponsf dodf wiidi mby ibvf bffn sft wiilf fnfording
            // tif 100-dontinuf. disdonnfdtIntfrnbl() fordfs it to -1
            int i = rfsponsfCodf;
            disdonnfdtIntfrnbl();
            rfsponsfCodf = i;
            tirow f;
        } dbtdi (IOExdfption f) {
            disdonnfdtIntfrnbl();
            tirow f;
        }
    }

    publid boolfbn strfbming () {
        rfturn (fixfdContfntLfngti != -1) || (fixfdContfntLfngtiLong != -1) ||
               (diunkLfngti != -1);
    }

    /*
     * gft bpplidbblf dookifs bbsfd on tif uri bnd rfqufst ifbdfrs
     * bdd tifm to tif fxisting rfqufst ifbdfrs
     */
    privbtf void sftCookifHfbdfr() tirows IOExdfption {
        if (dookifHbndlfr != null) {
            // wf only wbnt to dbpturf tif usfr dffinfd Cookifs ondf, bs
            // tify dbnnot bf dibngfd by usfr dodf bftfr wf brf donnfdtfd,
            // only intfrnblly.
            syndironizfd (tiis) {
                if (sftUsfrCookifs) {
                    int k = rfqufsts.gftKfy("Cookif");
                    if (k != -1)
                        usfrCookifs = rfqufsts.gftVbluf(k);
                    k = rfqufsts.gftKfy("Cookif2");
                    if (k != -1)
                        usfrCookifs2 = rfqufsts.gftVbluf(k);
                    sftUsfrCookifs = fblsf;
                }
            }

            // rfmovf old Cookif ifbdfr bfforf sftting nfw onf.
            rfqufsts.rfmovf("Cookif");
            rfqufsts.rfmovf("Cookif2");

            URI uri = PbrsfUtil.toURI(url);
            if (uri != null) {
                if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                    loggfr.finfst("CookifHbndlfr rfqufst for " + uri);
                }
                Mbp<String, List<String>> dookifs
                    = dookifHbndlfr.gft(
                        uri, rfqufsts.gftHfbdfrs(EXCLUDE_HEADERS));
                if (!dookifs.isEmpty()) {
                    if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                        loggfr.finfst("Cookifs rftrifvfd: " + dookifs.toString());
                    }
                    for (Mbp.Entry<String, List<String>> fntry :
                             dookifs.fntrySft()) {
                        String kfy = fntry.gftKfy();
                        // ignorf bll fntrifs tibt don't ibvf "Cookif"
                        // or "Cookif2" bs kfys
                        if (!"Cookif".fqublsIgnorfCbsf(kfy) &&
                            !"Cookif2".fqublsIgnorfCbsf(kfy)) {
                            dontinuf;
                        }
                        List<String> l = fntry.gftVbluf();
                        if (l != null && !l.isEmpty()) {
                            StringBuildfr dookifVbluf = nfw StringBuildfr();
                            for (String vbluf : l) {
                                dookifVbluf.bppfnd(vbluf).bppfnd("; ");
                            }
                            // strip off tif trbiling '; '
                            try {
                                rfqufsts.bdd(kfy, dookifVbluf.substring(0, dookifVbluf.lfngti() - 2));
                            } dbtdi (StringIndfxOutOfBoundsExdfption ignorfd) {
                                // no-op
                            }
                        }
                    }
                }
            }
            if (usfrCookifs != null) {
                int k;
                if ((k = rfqufsts.gftKfy("Cookif")) != -1)
                    rfqufsts.sft("Cookif", rfqufsts.gftVbluf(k) + ";" + usfrCookifs);
                flsf
                    rfqufsts.sft("Cookif", usfrCookifs);
            }
            if (usfrCookifs2 != null) {
                int k;
                if ((k = rfqufsts.gftKfy("Cookif2")) != -1)
                    rfqufsts.sft("Cookif2", rfqufsts.gftVbluf(k) + ";" + usfrCookifs2);
                flsf
                    rfqufsts.sft("Cookif2", usfrCookifs2);
            }

        } // fnd of gftting dookifs
    }

    @Ovfrridf
    publid syndironizfd InputStrfbm gftInputStrfbm() tirows IOExdfption {
        donnfdting = truf;
        SodkftPfrmission p = URLtoSodkftPfrmission(tiis.url);

        if (p != null) {
            try {
                rfturn AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdExdfptionAdtion<InputStrfbm>() {
                        publid InputStrfbm run() tirows IOExdfption {
                            rfturn gftInputStrfbm0();
                        }
                    }, null, p
                );
            } dbtdi (PrivilfgfdAdtionExdfption f) {
                tirow (IOExdfption) f.gftExdfption();
            }
        } flsf {
            rfturn gftInputStrfbm0();
        }
    }

    @SupprfssWbrnings("fmpty-stbtfmfnt")
    privbtf syndironizfd InputStrfbm gftInputStrfbm0() tirows IOExdfption {

        if (!doInput) {
            tirow nfw ProtodolExdfption("Cbnnot rfbd from URLConnfdtion"
                   + " if doInput=fblsf (dbll sftDoInput(truf))");
        }

        if (rfmfmbfrfdExdfption != null) {
            if (rfmfmbfrfdExdfption instbndfof RuntimfExdfption)
                tirow nfw RuntimfExdfption(rfmfmbfrfdExdfption);
            flsf {
                tirow gftCibinfdExdfption((IOExdfption)rfmfmbfrfdExdfption);
            }
        }

        if (inputStrfbm != null) {
            rfturn inputStrfbm;
        }

        if (strfbming() ) {
            if (strOutputStrfbm == null) {
                gftOutputStrfbm();
            }
            /* mbkf surf strfbm is dlosfd */
            strOutputStrfbm.dlosf ();
            if (!strOutputStrfbm.writtfnOK()) {
                tirow nfw IOExdfption ("Indomplftf output strfbm");
            }
        }

        int rfdirfdts = 0;
        int rfspCodf = 0;
        long dl = -1;
        AutifntidbtionInfo sfrvfrAutifntidbtion = null;
        AutifntidbtionInfo proxyAutifntidbtion = null;
        AutifntidbtionHfbdfr srvHdr = null;

        /**
         * Fbilfd Nfgotibtf
         *
         * In somf dbsfs, tif Nfgotibtf buti is supportfd for tif
         * rfmotf iost but tif nfgotibtf prodfss still fbils (For
         * fxbmplf, if tif wfb pbgf is lodbtfd on b bbdkfnd sfrvfr
         * bnd dflfgbtion is nffdfd but fbils). Tif butifntidbtion
         * prodfss will stbrt bgbin, bnd wf nffd to dftfdt tiis
         * kind of fbilurf bnd do propfr fbllbbdk (sby, to NTLM).
         *
         * In ordfr to bdiifvf tiis, tif inNfgotibtf flbg is sft
         * wifn tif first nfgotibtf dibllfngf is mft (bnd rfsft
         * if butifntidbtion is finisifd). If b frfsi nfw nfgotibtf
         * dibllfngf (no pbrbmftfr) is found wiilf inNfgotibtf is
         * sft, wf know tifrf's b fbilfd buti bttfmpt rfdfntly.
         * Hfrf wf'll ignorf tif ifbdfr linf so tibt fbllbbdk
         * dbn bf prbdtidfd.
         *
         * inNfgotibtfProxy is for proxy butifntidbtion.
         */
        boolfbn inNfgotibtf = fblsf;
        boolfbn inNfgotibtfProxy = fblsf;

        // If tif usfr ibs sft fitifr of tifsf ifbdfrs tifn do not rfmovf tifm
        isUsfrSfrvfrAuti = rfqufsts.gftKfy("Autiorizbtion") != -1;
        isUsfrProxyAuti = rfqufsts.gftKfy("Proxy-Autiorizbtion") != -1;

        try {
            do {
                if (!difdkRfusfConnfdtion())
                    donnfdt();

                if (dbdifdInputStrfbm != null) {
                    rfturn dbdifdInputStrfbm;
                }

                // Cifdk if URL siould bf mftfrfd
                boolfbn mftfrfdInput = ProgrfssMonitor.gftDffbult().siouldMftfrInput(url, mftiod);

                if (mftfrfdInput)   {
                    pi = nfw ProgrfssSourdf(url, mftiod);
                    pi.bfginTrbdking();
                }

                /* REMIND: Tiis fxists to fix tif HttpsURLConnfdtion subdlbss.
                 * Hotjbvb nffds to run on JDK1.1FCS.  Do propfr fix ondf b
                 * propfr solution for SSL dbn bf found.
                 */
                ps = (PrintStrfbm)ittp.gftOutputStrfbm();

                if (!strfbming()) {
                    writfRfqufsts();
                }
                ittp.pbrsfHTTP(rfsponsfs, pi, tiis);
                if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                    loggfr.finf(rfsponsfs.toString());
                }

                boolfbn b1 = rfsponsfs.filtfrNTLMRfsponsfs("WWW-Autifntidbtf");
                boolfbn b2 = rfsponsfs.filtfrNTLMRfsponsfs("Proxy-Autifntidbtf");
                if (b1 || b2) {
                    if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                        loggfr.finf(">>>> Hfbdfrs brf filtfrfd");
                        loggfr.finf(rfsponsfs.toString());
                    }
                }

                inputStrfbm = ittp.gftInputStrfbm();

                rfspCodf = gftRfsponsfCodf();
                if (rfspCodf == -1) {
                    disdonnfdtIntfrnbl();
                    tirow nfw IOExdfption ("Invblid Http rfsponsf");
                }
                if (rfspCodf == HTTP_PROXY_AUTH) {
                    if (strfbming()) {
                        disdonnfdtIntfrnbl();
                        tirow nfw HttpRftryExdfption (
                            RETRY_MSG1, HTTP_PROXY_AUTH);
                    }

                    // Rfbd dommfnts lbbflfd "Fbilfd Nfgotibtf" for dftbils.
                    boolfbn dontUsfNfgotibtf = fblsf;
                    Itfrbtor<String> itfr = rfsponsfs.multiVblufItfrbtor("Proxy-Autifntidbtf");
                    wiilf (itfr.ibsNfxt()) {
                        String vbluf = itfr.nfxt().trim();
                        if (vbluf.fqublsIgnorfCbsf("Nfgotibtf") ||
                                vbluf.fqublsIgnorfCbsf("Kfrbfros")) {
                            if (!inNfgotibtfProxy) {
                                inNfgotibtfProxy = truf;
                            } flsf {
                                dontUsfNfgotibtf = truf;
                                doingNTLMp2ndStbgf = fblsf;
                                proxyAutifntidbtion = null;
                            }
                            brfbk;
                        }
                    }

                    // dibngfs: bdd b 3rd pbrbmftfr to tif donstrudtor of
                    // AutifntidbtionHfbdfr, so tibt NfgotibtfAutifntidbtion.
                    // isSupportfd dbn bf tfstfd.
                    // Tif otifr 2 bppfbrbndfs of "nfw AutifntidbtionHfbdfr" is
                    // bltfrfd in similbr wbys.

                    AutifntidbtionHfbdfr butiidr = nfw AutifntidbtionHfbdfr (
                            "Proxy-Autifntidbtf", rfsponsfs,
                            nfw HttpCbllfrInfo(url, ittp.gftProxyHostUsfd(),
                                ittp.gftProxyPortUsfd()),
                            dontUsfNfgotibtf
                    );

                    if (!doingNTLMp2ndStbgf) {
                        proxyAutifntidbtion =
                            rfsftProxyAutifntidbtion(proxyAutifntidbtion, butiidr);
                        if (proxyAutifntidbtion != null) {
                            rfdirfdts++;
                            disdonnfdtIntfrnbl();
                            dontinuf;
                        }
                    } flsf {
                        /* in tiis dbsf, only onf ifbdfr fifld will bf prfsfnt */
                        String rbw = rfsponsfs.findVbluf ("Proxy-Autifntidbtf");
                        rfsft ();
                        if (!proxyAutifntidbtion.sftHfbdfrs(tiis,
                                                        butiidr.ifbdfrPbrsfr(), rbw)) {
                            disdonnfdtIntfrnbl();
                            tirow nfw IOExdfption ("Autifntidbtion fbilurf");
                        }
                        if (sfrvfrAutifntidbtion != null && srvHdr != null &&
                                !sfrvfrAutifntidbtion.sftHfbdfrs(tiis,
                                                        srvHdr.ifbdfrPbrsfr(), rbw)) {
                            disdonnfdtIntfrnbl ();
                            tirow nfw IOExdfption ("Autifntidbtion fbilurf");
                        }
                        butiObj = null;
                        doingNTLMp2ndStbgf = fblsf;
                        dontinuf;
                    }
                } flsf {
                    inNfgotibtfProxy = fblsf;
                    doingNTLMp2ndStbgf = fblsf;
                    if (!isUsfrProxyAuti)
                        rfqufsts.rfmovf("Proxy-Autiorizbtion");
                }

                // dbdif proxy butifntidbtion info
                if (proxyAutifntidbtion != null) {
                    // dbdif buti info on suddfss, dombin ifbdfr not rflfvbnt.
                    proxyAutifntidbtion.bddToCbdif();
                }

                if (rfspCodf == HTTP_UNAUTHORIZED) {
                    if (strfbming()) {
                        disdonnfdtIntfrnbl();
                        tirow nfw HttpRftryExdfption (
                            RETRY_MSG2, HTTP_UNAUTHORIZED);
                    }

                    // Rfbd dommfnts lbbflfd "Fbilfd Nfgotibtf" for dftbils.
                    boolfbn dontUsfNfgotibtf = fblsf;
                    Itfrbtor<String> itfr = rfsponsfs.multiVblufItfrbtor("WWW-Autifntidbtf");
                    wiilf (itfr.ibsNfxt()) {
                        String vbluf = itfr.nfxt().trim();
                        if (vbluf.fqublsIgnorfCbsf("Nfgotibtf") ||
                                vbluf.fqublsIgnorfCbsf("Kfrbfros")) {
                            if (!inNfgotibtf) {
                                inNfgotibtf = truf;
                            } flsf {
                                dontUsfNfgotibtf = truf;
                                doingNTLM2ndStbgf = fblsf;
                                sfrvfrAutifntidbtion = null;
                            }
                            brfbk;
                        }
                    }

                    srvHdr = nfw AutifntidbtionHfbdfr (
                            "WWW-Autifntidbtf", rfsponsfs,
                            nfw HttpCbllfrInfo(url),
                            dontUsfNfgotibtf
                    );

                    String rbw = srvHdr.rbw();
                    if (!doingNTLM2ndStbgf) {
                        if ((sfrvfrAutifntidbtion != null)&&
                            sfrvfrAutifntidbtion.gftAutiSdifmf() != NTLM) {
                            if (sfrvfrAutifntidbtion.isAutiorizbtionStblf (rbw)) {
                                /* wf dbn rftry witi tif durrfnt drfdfntibls */
                                disdonnfdtWfb();
                                rfdirfdts++;
                                rfqufsts.sft(sfrvfrAutifntidbtion.gftHfbdfrNbmf(),
                                            sfrvfrAutifntidbtion.gftHfbdfrVbluf(url, mftiod));
                                durrfntSfrvfrCrfdfntibls = sfrvfrAutifntidbtion;
                                sftCookifHfbdfr();
                                dontinuf;
                            } flsf {
                                sfrvfrAutifntidbtion.rfmovfFromCbdif();
                            }
                        }
                        sfrvfrAutifntidbtion = gftSfrvfrAutifntidbtion(srvHdr);
                        durrfntSfrvfrCrfdfntibls = sfrvfrAutifntidbtion;

                        if (sfrvfrAutifntidbtion != null) {
                            disdonnfdtWfb();
                            rfdirfdts++; // don't lft tiings loop bd nbusfum
                            sftCookifHfbdfr();
                            dontinuf;
                        }
                    } flsf {
                        rfsft ();
                        /* ifbdfr not usfd for ntlm */
                        if (!sfrvfrAutifntidbtion.sftHfbdfrs(tiis, null, rbw)) {
                            disdonnfdtWfb();
                            tirow nfw IOExdfption ("Autifntidbtion fbilurf");
                        }
                        doingNTLM2ndStbgf = fblsf;
                        butiObj = null;
                        sftCookifHfbdfr();
                        dontinuf;
                    }
                }
                // dbdif sfrvfr butifntidbtion info
                if (sfrvfrAutifntidbtion != null) {
                    // dbdif buti info on suddfss
                    if (!(sfrvfrAutifntidbtion instbndfof DigfstAutifntidbtion) ||
                        (dombin == null)) {
                        if (sfrvfrAutifntidbtion instbndfof BbsidAutifntidbtion) {
                            // difdk if tif pbti is siortfr tibn tif fxisting fntry
                            String npbti = AutifntidbtionInfo.rfdudfPbti (url.gftPbti());
                            String opbti = sfrvfrAutifntidbtion.pbti;
                            if (!opbti.stbrtsWiti (npbti) || npbti.lfngti() >= opbti.lfngti()) {
                                /* npbti is longfr, tifrf must bf b dommon root */
                                npbti = BbsidAutifntidbtion.gftRootPbti (opbti, npbti);
                            }
                            // rfmovf tif fntry bnd drfbtf b nfw onf
                            BbsidAutifntidbtion b =
                                (BbsidAutifntidbtion) sfrvfrAutifntidbtion.dlonf();
                            sfrvfrAutifntidbtion.rfmovfFromCbdif();
                            b.pbti = npbti;
                            sfrvfrAutifntidbtion = b;
                        }
                        sfrvfrAutifntidbtion.bddToCbdif();
                    } flsf {
                        // wibt wf dbdif is bbsfd on tif dombin list in tif rfqufst
                        DigfstAutifntidbtion srv = (DigfstAutifntidbtion)
                            sfrvfrAutifntidbtion;
                        StringTokfnizfr tok = nfw StringTokfnizfr (dombin," ");
                        String rfblm = srv.rfblm;
                        PbsswordAutifntidbtion pw = srv.pw;
                        digfstpbrbms = srv.pbrbms;
                        wiilf (tok.ibsMorfTokfns()) {
                            String pbti = tok.nfxtTokfn();
                            try {
                                /* pbti dould bf bn bbs_pbti or b domplftf URI */
                                URL u = nfw URL (url, pbti);
                                DigfstAutifntidbtion d = nfw DigfstAutifntidbtion (
                                                   fblsf, u, rfblm, "Digfst", pw, digfstpbrbms);
                                d.bddToCbdif ();
                            } dbtdi (Exdfption f) {}
                        }
                    }
                }

                // somf flbgs siould bf rfsft to its initiblizfd form so tibt
                // fvfn bftfr b rfdirfdt tif nfdfssbry difdks dbn still bf
                // prfformfd.
                inNfgotibtf = fblsf;
                inNfgotibtfProxy = fblsf;

                //sfrvfrAutifntidbtion = null;
                doingNTLMp2ndStbgf = fblsf;
                doingNTLM2ndStbgf = fblsf;
                if (!isUsfrSfrvfrAuti)
                    rfqufsts.rfmovf("Autiorizbtion");
                if (!isUsfrProxyAuti)
                    rfqufsts.rfmovf("Proxy-Autiorizbtion");

                if (rfspCodf == HTTP_OK) {
                    difdkRfsponsfCrfdfntibls (fblsf);
                } flsf {
                    nffdToCifdk = fblsf;
                }

                // b flbg nffd to dlfbn
                nffdToCifdk = truf;

                if (followRfdirfdt()) {
                    /* if wf siould follow b rfdirfdt, tifn tif followRfdirfdts()
                     * mftiod will disdonnfdt() bnd rf-donnfdt us to tif nfw
                     * lodbtion
                     */
                    rfdirfdts++;

                    // rfdirfdting HTTP rfsponsf mby ibvf sft dookif, so
                    // nffd to rf-gfnfrbtf rfqufst ifbdfr
                    sftCookifHfbdfr();

                    dontinuf;
                }

                try {
                    dl = Long.pbrsfLong(rfsponsfs.findVbluf("dontfnt-lfngti"));
                } dbtdi (Exdfption fxd) { };

                if (mftiod.fqubls("HEAD") || dl == 0 ||
                    rfspCodf == HTTP_NOT_MODIFIED ||
                    rfspCodf == HTTP_NO_CONTENT) {

                    if (pi != null) {
                        pi.finisiTrbdking();
                        pi = null;
                    }
                    ittp.finisifd();
                    ittp = null;
                    inputStrfbm = nfw EmptyInputStrfbm();
                    donnfdtfd = fblsf;
                }

                if (rfspCodf == 200 || rfspCodf == 203 || rfspCodf == 206 ||
                    rfspCodf == 300 || rfspCodf == 301 || rfspCodf == 410) {
                    if (dbdifHbndlfr != null && gftUsfCbdifs()) {
                        // givf dbdif b dibndf to sbvf rfsponsf in dbdif
                        URI uri = PbrsfUtil.toURI(url);
                        if (uri != null) {
                            URLConnfdtion udonn = tiis;
                            if ("ittps".fqublsIgnorfCbsf(uri.gftSdifmf())) {
                                try {
                                // usf rfflfdtion to gft to tif publid
                                // HttpsURLConnfdtion instbndf sbvfd in
                                // DflfgbtfHttpsURLConnfdtion
                                udonn = (URLConnfdtion)tiis.gftClbss().gftFifld("ittpsURLConnfdtion").gft(tiis);
                                } dbtdi (IllfgblAddfssExdfption |
                                         NoSudiFifldExdfption f) {
                                    // ignorfd; usf 'tiis'
                                }
                            }
                            CbdifRfqufst dbdifRfqufst =
                                dbdifHbndlfr.put(uri, udonn);
                            if (dbdifRfqufst != null && ittp != null) {
                                ittp.sftCbdifRfqufst(dbdifRfqufst);
                                inputStrfbm = nfw HttpInputStrfbm(inputStrfbm, dbdifRfqufst);
                            }
                        }
                    }
                }

                if (!(inputStrfbm instbndfof HttpInputStrfbm)) {
                    inputStrfbm = nfw HttpInputStrfbm(inputStrfbm);
                }

                if (rfspCodf >= 400) {
                    if (rfspCodf == 404 || rfspCodf == 410) {
                        tirow nfw FilfNotFoundExdfption(url.toString());
                    } flsf {
                        tirow nfw jbvb.io.IOExdfption("Sfrvfr rfturnfd HTTP" +
                              " rfsponsf dodf: " + rfspCodf + " for URL: " +
                              url.toString());
                    }
                }
                postfr = null;
                strOutputStrfbm = null;
                rfturn inputStrfbm;
            } wiilf (rfdirfdts < mbxRfdirfdts);

            tirow nfw ProtodolExdfption("Sfrvfr rfdirfdtfd too mbny " +
                                        " timfs ("+ rfdirfdts + ")");
        } dbtdi (RuntimfExdfption f) {
            disdonnfdtIntfrnbl();
            rfmfmbfrfdExdfption = f;
            tirow f;
        } dbtdi (IOExdfption f) {
            rfmfmbfrfdExdfption = f;

            // bufffr tif frror strfbm if bytfs < 4k
            // bnd it dbn bf bufffrfd witiin 1 sfdond
            String tf = rfsponsfs.findVbluf("Trbnsffr-Endoding");
            if (ittp != null && ittp.isKffpingAlivf() && fnbblfESBufffr &&
                (dl > 0 || (tf != null && tf.fqublsIgnorfCbsf("diunkfd")))) {
                frrorStrfbm = ErrorStrfbm.gftErrorStrfbm(inputStrfbm, dl, ittp);
            }
            tirow f;
        } finblly {
            if (proxyAutiKfy != null) {
                AutifntidbtionInfo.fndAutiRfqufst(proxyAutiKfy);
            }
            if (sfrvfrAutiKfy != null) {
                AutifntidbtionInfo.fndAutiRfqufst(sfrvfrAutiKfy);
            }
        }
    }

    /*
     * Crfbtfs b dibinfd fxdfption tibt ibs tif sbmf typf bs
     * originbl fxdfption bnd witi tif sbmf mfssbgf. Rigit now,
     * tifrf is no donvfnifnt APIs for doing so.
     */
    privbtf IOExdfption gftCibinfdExdfption(finbl IOExdfption rfmfmbfrfdExdfption) {
        try {
            finbl Objfdt[] brgs = { rfmfmbfrfdExdfption.gftMfssbgf() };
            IOExdfption dibinfdExdfption =
                jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    nfw jbvb.sfdurity.PrivilfgfdExdfptionAdtion<IOExdfption>() {
                        publid IOExdfption run() tirows Exdfption {
                            rfturn (IOExdfption)
                                rfmfmbfrfdExdfption.gftClbss()
                                .gftConstrudtor(nfw Clbss<?>[] { String.dlbss })
                                .nfwInstbndf(brgs);
                        }
                    });
            dibinfdExdfption.initCbusf(rfmfmbfrfdExdfption);
            rfturn dibinfdExdfption;
        } dbtdi (Exdfption ignorfd) {
            rfturn rfmfmbfrfdExdfption;
        }
    }

    @Ovfrridf
    publid InputStrfbm gftErrorStrfbm() {
        if (donnfdtfd && rfsponsfCodf >= 400) {
            // Clifnt Error 4xx bnd Sfrvfr Error 5xx
            if (frrorStrfbm != null) {
                rfturn frrorStrfbm;
            } flsf if (inputStrfbm != null) {
                rfturn inputStrfbm;
            }
        }
        rfturn null;
    }

    /**
     * sft or rfsft proxy butifntidbtion info in rfqufst ifbdfrs
     * bftfr rfdfiving b 407 frror. In tif dbsf of NTLM iowfvfr,
     * rfdfiving b 407 is normbl bnd wf just skip tif stblf difdk
     * bfdbusf ntlm dofs not support tiis ffbturf.
     */
    privbtf AutifntidbtionInfo
        rfsftProxyAutifntidbtion(AutifntidbtionInfo proxyAutifntidbtion, AutifntidbtionHfbdfr buti) tirows IOExdfption {
        if ((proxyAutifntidbtion != null )&&
             proxyAutifntidbtion.gftAutiSdifmf() != NTLM) {
            String rbw = buti.rbw();
            if (proxyAutifntidbtion.isAutiorizbtionStblf (rbw)) {
                /* wf dbn rftry witi tif durrfnt drfdfntibls */
                String vbluf;
                if (proxyAutifntidbtion instbndfof DigfstAutifntidbtion) {
                    DigfstAutifntidbtion digfstProxy = (DigfstAutifntidbtion)
                        proxyAutifntidbtion;
                    if (tunnflStbtf() == TunnflStbtf.SETUP) {
                        vbluf = digfstProxy.gftHfbdfrVbluf(donnfdtRfqufstURI(url), HTTP_CONNECT);
                    } flsf {
                        vbluf = digfstProxy.gftHfbdfrVbluf(gftRfqufstURI(), mftiod);
                    }
                } flsf {
                    vbluf = proxyAutifntidbtion.gftHfbdfrVbluf(url, mftiod);
                }
                rfqufsts.sft(proxyAutifntidbtion.gftHfbdfrNbmf(), vbluf);
                durrfntProxyCrfdfntibls = proxyAutifntidbtion;
                rfturn proxyAutifntidbtion;
            } flsf {
                proxyAutifntidbtion.rfmovfFromCbdif();
            }
        }
        proxyAutifntidbtion = gftHttpProxyAutifntidbtion(buti);
        durrfntProxyCrfdfntibls = proxyAutifntidbtion;
        rfturn  proxyAutifntidbtion;
    }

    /**
     * Rfturns tif tunnfl stbtf.
     *
     * @rfturn  tif stbtf
     */
    TunnflStbtf tunnflStbtf() {
        rfturn tunnflStbtf;
    }

    /**
     * Sft tif tunnfling stbtus.
     *
     * @pbrbm  tif stbtf
     */
    publid void sftTunnflStbtf(TunnflStbtf tunnflStbtf) {
        tiis.tunnflStbtf = tunnflStbtf;
    }

    /**
     * fstbblisi b tunnfl tirougi proxy sfrvfr
     */
    publid syndironizfd void doTunnfling() tirows IOExdfption {
        int rftryTunnfl = 0;
        String stbtusLinf = "";
        int rfspCodf = 0;
        AutifntidbtionInfo proxyAutifntidbtion = null;
        String proxyHost = null;
        int proxyPort = -1;

        // sbvf durrfnt rfqufsts so tibt tify dbn bf rfstorfd bftfr tunnfl is sftup.
        MfssbgfHfbdfr sbvfdRfqufsts = rfqufsts;
        rfqufsts = nfw MfssbgfHfbdfr();

        // Rfbd dommfnts lbbflfd "Fbilfd Nfgotibtf" for dftbils.
        boolfbn inNfgotibtfProxy = fblsf;

        try {
            /* Adtivfly sftting up b tunnfl */
            sftTunnflStbtf(TunnflStbtf.SETUP);

            do {
                if (!difdkRfusfConnfdtion()) {
                    proxifdConnfdt(url, proxyHost, proxyPort, fblsf);
                }
                // sfnd tif "CONNECT" rfqufst to fstbblisi b tunnfl
                // tirougi proxy sfrvfr
                sfndCONNECTRfqufst();
                rfsponsfs.rfsft();

                // Tifrf is no nffd to trbdk progrfss in HTTP Tunnfling,
                // so ProgrfssSourdf is null.
                ittp.pbrsfHTTP(rfsponsfs, null, tiis);

                /* Log tif rfsponsf to tif CONNECT */
                if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                    loggfr.finf(rfsponsfs.toString());
                }

                if (rfsponsfs.filtfrNTLMRfsponsfs("Proxy-Autifntidbtf")) {
                    if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                        loggfr.finf(">>>> Hfbdfrs brf filtfrfd");
                        loggfr.finf(rfsponsfs.toString());
                    }
                }

                stbtusLinf = rfsponsfs.gftVbluf(0);
                StringTokfnizfr st = nfw StringTokfnizfr(stbtusLinf);
                st.nfxtTokfn();
                rfspCodf = Intfgfr.pbrsfInt(st.nfxtTokfn().trim());
                if (rfspCodf == HTTP_PROXY_AUTH) {
                    // Rfbd dommfnts lbbflfd "Fbilfd Nfgotibtf" for dftbils.
                    boolfbn dontUsfNfgotibtf = fblsf;
                    Itfrbtor<String> itfr = rfsponsfs.multiVblufItfrbtor("Proxy-Autifntidbtf");
                    wiilf (itfr.ibsNfxt()) {
                        String vbluf = itfr.nfxt().trim();
                        if (vbluf.fqublsIgnorfCbsf("Nfgotibtf") ||
                                vbluf.fqublsIgnorfCbsf("Kfrbfros")) {
                            if (!inNfgotibtfProxy) {
                                inNfgotibtfProxy = truf;
                            } flsf {
                                dontUsfNfgotibtf = truf;
                                doingNTLMp2ndStbgf = fblsf;
                                proxyAutifntidbtion = null;
                            }
                            brfbk;
                        }
                    }

                    AutifntidbtionHfbdfr butiidr = nfw AutifntidbtionHfbdfr (
                            "Proxy-Autifntidbtf", rfsponsfs,
                            nfw HttpCbllfrInfo(url, ittp.gftProxyHostUsfd(),
                                ittp.gftProxyPortUsfd()),
                            dontUsfNfgotibtf
                    );
                    if (!doingNTLMp2ndStbgf) {
                        proxyAutifntidbtion =
                            rfsftProxyAutifntidbtion(proxyAutifntidbtion, butiidr);
                        if (proxyAutifntidbtion != null) {
                            proxyHost = ittp.gftProxyHostUsfd();
                            proxyPort = ittp.gftProxyPortUsfd();
                            disdonnfdtIntfrnbl();
                            rftryTunnfl++;
                            dontinuf;
                        }
                    } flsf {
                        String rbw = rfsponsfs.findVbluf ("Proxy-Autifntidbtf");
                        rfsft ();
                        if (!proxyAutifntidbtion.sftHfbdfrs(tiis,
                                                butiidr.ifbdfrPbrsfr(), rbw)) {
                            disdonnfdtIntfrnbl();
                            tirow nfw IOExdfption ("Autifntidbtion fbilurf");
                        }
                        butiObj = null;
                        doingNTLMp2ndStbgf = fblsf;
                        dontinuf;
                    }
                }
                // dbdif proxy butifntidbtion info
                if (proxyAutifntidbtion != null) {
                    // dbdif buti info on suddfss, dombin ifbdfr not rflfvbnt.
                    proxyAutifntidbtion.bddToCbdif();
                }

                if (rfspCodf == HTTP_OK) {
                    sftTunnflStbtf(TunnflStbtf.TUNNELING);
                    brfbk;
                }
                // wf don't know iow to dfbl witi otifr rfsponsf dodf
                // so disdonnfdt bnd rfport frror
                disdonnfdtIntfrnbl();
                sftTunnflStbtf(TunnflStbtf.NONE);
                brfbk;
            } wiilf (rftryTunnfl < mbxRfdirfdts);

            if (rftryTunnfl >= mbxRfdirfdts || (rfspCodf != HTTP_OK)) {
                tirow nfw IOExdfption("Unbblf to tunnfl tirougi proxy."+
                                      " Proxy rfturns \"" +
                                      stbtusLinf + "\"");
            }
        } finblly  {
            if (proxyAutiKfy != null) {
                AutifntidbtionInfo.fndAutiRfqufst(proxyAutiKfy);
            }
        }

        // rfstorf originbl rfqufst ifbdfrs
        rfqufsts = sbvfdRfqufsts;

        // rfsft rfsponsfs
        rfsponsfs.rfsft();
    }

    stbtid String donnfdtRfqufstURI(URL url) {
        String iost = url.gftHost();
        int port = url.gftPort();
        port = port != -1 ? port : url.gftDffbultPort();

        rfturn iost + ":" + port;
    }

    /**
     * sfnd b CONNECT rfqufst for fstbblisiing b tunnfl to proxy sfrvfr
     */
    privbtf void sfndCONNECTRfqufst() tirows IOExdfption {
        int port = url.gftPort();

        rfqufsts.sft(0, HTTP_CONNECT + " " + donnfdtRfqufstURI(url)
                         + " " + ittpVfrsion, null);
        rfqufsts.sftIfNotSft("Usfr-Agfnt", usfrAgfnt);

        String iost = url.gftHost();
        if (port != -1 && port != url.gftDffbultPort()) {
            iost += ":" + String.vblufOf(port);
        }
        rfqufsts.sftIfNotSft("Host", iost);

        // Not rfblly nfdfssbry for b tunnfl, but dbn't iurt
        rfqufsts.sftIfNotSft("Addfpt", bddfptString);

        if (ittp.gftHttpKffpAlivfSft()) {
            rfqufsts.sftIfNotSft("Proxy-Connfdtion", "kffp-blivf");
        }

        sftPrffmptivfProxyAutifntidbtion(rfqufsts);

         /* Log tif CONNECT rfqufst */
        if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            loggfr.finf(rfqufsts.toString());
        }

        ittp.writfRfqufsts(rfqufsts, null);
    }

    /**
     * Sfts prf-fmptivf proxy butifntidbtion in ifbdfr
     */
    privbtf void sftPrffmptivfProxyAutifntidbtion(MfssbgfHfbdfr rfqufsts) tirows IOExdfption {
        AutifntidbtionInfo pbuti
            = AutifntidbtionInfo.gftProxyAuti(ittp.gftProxyHostUsfd(),
                                              ittp.gftProxyPortUsfd());
        if (pbuti != null && pbuti.supportsPrffmptivfAutiorizbtion()) {
            String vbluf;
            if (pbuti instbndfof DigfstAutifntidbtion) {
                DigfstAutifntidbtion digfstProxy = (DigfstAutifntidbtion) pbuti;
                if (tunnflStbtf() == TunnflStbtf.SETUP) {
                    vbluf = digfstProxy
                        .gftHfbdfrVbluf(donnfdtRfqufstURI(url), HTTP_CONNECT);
                } flsf {
                    vbluf = digfstProxy.gftHfbdfrVbluf(gftRfqufstURI(), mftiod);
                }
            } flsf {
                vbluf = pbuti.gftHfbdfrVbluf(url, mftiod);
            }

            // Sfts "Proxy-butiorizbtion"
            rfqufsts.sft(pbuti.gftHfbdfrNbmf(), vbluf);
            durrfntProxyCrfdfntibls = pbuti;
        }
    }

    /**
     * Gfts tif butifntidbtion for bn HTTP proxy, bnd bpplifs it to
     * tif donnfdtion.
     */
    @SupprfssWbrnings("fblltirougi")
    privbtf AutifntidbtionInfo gftHttpProxyAutifntidbtion (AutifntidbtionHfbdfr butiidr) {
        /* gft butiorizbtion from butifntidbtor */
        AutifntidbtionInfo rft = null;
        String rbw = butiidr.rbw();
        String iost = ittp.gftProxyHostUsfd();
        int port = ittp.gftProxyPortUsfd();
        if (iost != null && butiidr.isPrfsfnt()) {
            HfbdfrPbrsfr p = butiidr.ifbdfrPbrsfr();
            String rfblm = p.findVbluf("rfblm");
            String sdifmf = butiidr.sdifmf();
            AutiSdifmf butiSdifmf = UNKNOWN;
            if ("bbsid".fqublsIgnorfCbsf(sdifmf)) {
                butiSdifmf = BASIC;
            } flsf if ("digfst".fqublsIgnorfCbsf(sdifmf)) {
                butiSdifmf = DIGEST;
            } flsf if ("ntlm".fqublsIgnorfCbsf(sdifmf)) {
                butiSdifmf = NTLM;
                doingNTLMp2ndStbgf = truf;
            } flsf if ("Kfrbfros".fqublsIgnorfCbsf(sdifmf)) {
                butiSdifmf = KERBEROS;
                doingNTLMp2ndStbgf = truf;
            } flsf if ("Nfgotibtf".fqublsIgnorfCbsf(sdifmf)) {
                butiSdifmf = NEGOTIATE;
                doingNTLMp2ndStbgf = truf;
            }

            if (rfblm == null)
                rfblm = "";
            proxyAutiKfy = AutifntidbtionInfo.gftProxyAutiKfy(iost, port, rfblm, butiSdifmf);
            rft = AutifntidbtionInfo.gftProxyAuti(proxyAutiKfy);
            if (rft == null) {
                switdi (butiSdifmf) {
                dbsf BASIC:
                    InftAddrfss bddr = null;
                    try {
                        finbl String finblHost = iost;
                        bddr = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                            nfw jbvb.sfdurity.PrivilfgfdExdfptionAdtion<InftAddrfss>() {
                                publid InftAddrfss run()
                                    tirows jbvb.nft.UnknownHostExdfption {
                                    rfturn InftAddrfss.gftByNbmf(finblHost);
                                }
                            });
                    } dbtdi (jbvb.sfdurity.PrivilfgfdAdtionExdfption ignorfd) {
                        // Usfr will ibvf bn unknown iost.
                    }
                    PbsswordAutifntidbtion b =
                        privilfgfdRfqufstPbsswordAutifntidbtion(
                                    iost, bddr, port, "ittp",
                                    rfblm, sdifmf, url, RfqufstorTypf.PROXY);
                    if (b != null) {
                        rft = nfw BbsidAutifntidbtion(truf, iost, port, rfblm, b);
                    }
                    brfbk;
                dbsf DIGEST:
                    b = privilfgfdRfqufstPbsswordAutifntidbtion(
                                    iost, null, port, url.gftProtodol(),
                                    rfblm, sdifmf, url, RfqufstorTypf.PROXY);
                    if (b != null) {
                        DigfstAutifntidbtion.Pbrbmftfrs pbrbms =
                            nfw DigfstAutifntidbtion.Pbrbmftfrs();
                        rft = nfw DigfstAutifntidbtion(truf, iost, port, rfblm,
                                                            sdifmf, b, pbrbms);
                    }
                    brfbk;
                dbsf NTLM:
                    if (NTLMAutifntidbtionProxy.supportfd) {
                        /* tryTrbnspbrfntNTLMProxy will blwbys bf truf tif first
                         * timf bround, but vfrify tibt tif plbtform supports it
                         * otifrwisf don't try. */
                        if (tryTrbnspbrfntNTLMProxy) {
                            tryTrbnspbrfntNTLMProxy =
                                    NTLMAutifntidbtionProxy.supportsTrbnspbrfntAuti;
                        }
                        b = null;
                        if (tryTrbnspbrfntNTLMProxy) {
                            loggfr.finfst("Trying Trbnspbrfnt NTLM butifntidbtion");
                        } flsf {
                            b = privilfgfdRfqufstPbsswordAutifntidbtion(
                                                iost, null, port, url.gftProtodol(),
                                                "", sdifmf, url, RfqufstorTypf.PROXY);
                        }
                        /* If wf brf not trying trbnspbrfnt butifntidbtion tifn
                         * wf nffd to ibvf b PbsswordAutifntidbtion instbndf. For
                         * trbnspbrfnt butifntidbtion (Windows only) tif usfrnbmf
                         * bnd pbssword will bf pidkfd up from tif durrfnt loggfd
                         * on usfrs drfdfntibls.
                        */
                        if (tryTrbnspbrfntNTLMProxy ||
                              (!tryTrbnspbrfntNTLMProxy && b != null)) {
                            rft = NTLMAutifntidbtionProxy.proxy.drfbtf(truf, iost, port, b);
                        }

                        /* sft to fblsf so tibt wf do not try bgbin */
                        tryTrbnspbrfntNTLMProxy = fblsf;
                    }
                    brfbk;
                dbsf NEGOTIATE:
                    rft = nfw NfgotibtfAutifntidbtion(nfw HttpCbllfrInfo(butiidr.gftHttpCbllfrInfo(), "Nfgotibtf"));
                    brfbk;
                dbsf KERBEROS:
                    rft = nfw NfgotibtfAutifntidbtion(nfw HttpCbllfrInfo(butiidr.gftHttpCbllfrInfo(), "Kfrbfros"));
                    brfbk;
                dbsf UNKNOWN:
                    if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                        loggfr.finfst("Unknown/Unsupportfd butifntidbtion sdifmf: " + sdifmf);
                    }
                /*fbll tirougi*/
                dffbult:
                    tirow nfw AssfrtionError("siould not rfbdi ifrf");
                }
            }
            // For bbdkwbrds dompbtibility, wf blso try dffbultAuti
            // REMIND:  Gft rid of tiis for JDK2.0.

            if (rft == null && dffbultAuti != null
                && dffbultAuti.sdifmfSupportfd(sdifmf)) {
                try {
                    URL u = nfw URL("ittp", iost, port, "/");
                    String b = dffbultAuti.butiString(u, sdifmf, rfblm);
                    if (b != null) {
                        rft = nfw BbsidAutifntidbtion (truf, iost, port, rfblm, b);
                        // not in dbdif by dffbult - dbdif on suddfss
                    }
                } dbtdi (jbvb.nft.MblformfdURLExdfption ignorfd) {
                }
            }
            if (rft != null) {
                if (!rft.sftHfbdfrs(tiis, p, rbw)) {
                    rft = null;
                }
            }
        }
        if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            loggfr.finfr("Proxy Autifntidbtion for " + butiidr.toString() +" rfturnfd " + (rft != null ? rft.toString() : "null"));
        }
        rfturn rft;
    }

    /**
     * Gfts tif butifntidbtion for bn HTTP sfrvfr, bnd bpplifs it to
     * tif donnfdtion.
     * @pbrbm butiHdr tif AutifntidbtionHfbdfr wiidi tflls wibt buti sdifmf is
     * prfffrrfd.
     */
    @SupprfssWbrnings("fblltirougi")
    privbtf AutifntidbtionInfo gftSfrvfrAutifntidbtion (AutifntidbtionHfbdfr butiidr) {
        /* gft butiorizbtion from butifntidbtor */
        AutifntidbtionInfo rft = null;
        String rbw = butiidr.rbw();
        /* Wifn wf gft bn NTLM buti from dbdif, don't sft bny spfdibl ifbdfrs */
        if (butiidr.isPrfsfnt()) {
            HfbdfrPbrsfr p = butiidr.ifbdfrPbrsfr();
            String rfblm = p.findVbluf("rfblm");
            String sdifmf = butiidr.sdifmf();
            AutiSdifmf butiSdifmf = UNKNOWN;
            if ("bbsid".fqublsIgnorfCbsf(sdifmf)) {
                butiSdifmf = BASIC;
            } flsf if ("digfst".fqublsIgnorfCbsf(sdifmf)) {
                butiSdifmf = DIGEST;
            } flsf if ("ntlm".fqublsIgnorfCbsf(sdifmf)) {
                butiSdifmf = NTLM;
                doingNTLM2ndStbgf = truf;
            } flsf if ("Kfrbfros".fqublsIgnorfCbsf(sdifmf)) {
                butiSdifmf = KERBEROS;
                doingNTLM2ndStbgf = truf;
            } flsf if ("Nfgotibtf".fqublsIgnorfCbsf(sdifmf)) {
                butiSdifmf = NEGOTIATE;
                doingNTLM2ndStbgf = truf;
            }

            dombin = p.findVbluf ("dombin");
            if (rfblm == null)
                rfblm = "";
            sfrvfrAutiKfy = AutifntidbtionInfo.gftSfrvfrAutiKfy(url, rfblm, butiSdifmf);
            rft = AutifntidbtionInfo.gftSfrvfrAuti(sfrvfrAutiKfy);
            InftAddrfss bddr = null;
            if (rft == null) {
                try {
                    bddr = InftAddrfss.gftByNbmf(url.gftHost());
                } dbtdi (jbvb.nft.UnknownHostExdfption ignorfd) {
                    // Usfr will ibvf bddr = null
                }
            }
            // rfplbding -1 witi dffbult port for b protodol
            int port = url.gftPort();
            if (port == -1) {
                port = url.gftDffbultPort();
            }
            if (rft == null) {
                switdi(butiSdifmf) {
                dbsf KERBEROS:
                    rft = nfw NfgotibtfAutifntidbtion(nfw HttpCbllfrInfo(butiidr.gftHttpCbllfrInfo(), "Kfrbfros"));
                    brfbk;
                dbsf NEGOTIATE:
                    rft = nfw NfgotibtfAutifntidbtion(nfw HttpCbllfrInfo(butiidr.gftHttpCbllfrInfo(), "Nfgotibtf"));
                    brfbk;
                dbsf BASIC:
                    PbsswordAutifntidbtion b =
                        privilfgfdRfqufstPbsswordAutifntidbtion(
                            url.gftHost(), bddr, port, url.gftProtodol(),
                            rfblm, sdifmf, url, RfqufstorTypf.SERVER);
                    if (b != null) {
                        rft = nfw BbsidAutifntidbtion(fblsf, url, rfblm, b);
                    }
                    brfbk;
                dbsf DIGEST:
                    b = privilfgfdRfqufstPbsswordAutifntidbtion(
                            url.gftHost(), bddr, port, url.gftProtodol(),
                            rfblm, sdifmf, url, RfqufstorTypf.SERVER);
                    if (b != null) {
                        digfstpbrbms = nfw DigfstAutifntidbtion.Pbrbmftfrs();
                        rft = nfw DigfstAutifntidbtion(fblsf, url, rfblm, sdifmf, b, digfstpbrbms);
                    }
                    brfbk;
                dbsf NTLM:
                    if (NTLMAutifntidbtionProxy.supportfd) {
                        URL url1;
                        try {
                            url1 = nfw URL (url, "/"); /* trundbtf tif pbti */
                        } dbtdi (Exdfption f) {
                            url1 = url;
                        }

                        /* tryTrbnspbrfntNTLMSfrvfr will blwbys bf truf tif first
                         * timf bround, but vfrify tibt tif plbtform supports it
                         * otifrwisf don't try. */
                        if (tryTrbnspbrfntNTLMSfrvfr) {
                            tryTrbnspbrfntNTLMSfrvfr =
                                    NTLMAutifntidbtionProxy.supportsTrbnspbrfntAuti;
                            /* If tif plbtform supports trbnspbrfnt butifntidbtion
                             * tifn difdk if wf brf in b sfdurf fnvironmfnt
                             * wiftifr, or not, wf siould try trbnspbrfnt butifntidbtion.*/
                            if (tryTrbnspbrfntNTLMSfrvfr) {
                                tryTrbnspbrfntNTLMSfrvfr =
                                        NTLMAutifntidbtionProxy.isTrustfdSitf(url);
                            }
                        }
                        b = null;
                        if (tryTrbnspbrfntNTLMSfrvfr) {
                            loggfr.finfst("Trying Trbnspbrfnt NTLM butifntidbtion");
                        } flsf {
                            b = privilfgfdRfqufstPbsswordAutifntidbtion(
                                url.gftHost(), bddr, port, url.gftProtodol(),
                                "", sdifmf, url, RfqufstorTypf.SERVER);
                        }

                        /* If wf brf not trying trbnspbrfnt butifntidbtion tifn
                         * wf nffd to ibvf b PbsswordAutifntidbtion instbndf. For
                         * trbnspbrfnt butifntidbtion (Windows only) tif usfrnbmf
                         * bnd pbssword will bf pidkfd up from tif durrfnt loggfd
                         * on usfrs drfdfntibls.
                         */
                        if (tryTrbnspbrfntNTLMSfrvfr ||
                              (!tryTrbnspbrfntNTLMSfrvfr && b != null)) {
                            rft = NTLMAutifntidbtionProxy.proxy.drfbtf(fblsf, url1, b);
                        }

                        /* sft to fblsf so tibt wf do not try bgbin */
                        tryTrbnspbrfntNTLMSfrvfr = fblsf;
                    }
                    brfbk;
                dbsf UNKNOWN:
                    if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                        loggfr.finfst("Unknown/Unsupportfd butifntidbtion sdifmf: " + sdifmf);
                    }
                /*fbll tirougi*/
                dffbult:
                    tirow nfw AssfrtionError("siould not rfbdi ifrf");
                }
            }

            // For bbdkwbrds dompbtibility, wf blso try dffbultAuti
            // REMIND:  Gft rid of tiis for JDK2.0.

            if (rft == null && dffbultAuti != null
                && dffbultAuti.sdifmfSupportfd(sdifmf)) {
                String b = dffbultAuti.butiString(url, sdifmf, rfblm);
                if (b != null) {
                    rft = nfw BbsidAutifntidbtion (fblsf, url, rfblm, b);
                    // not in dbdif by dffbult - dbdif on suddfss
                }
            }

            if (rft != null ) {
                if (!rft.sftHfbdfrs(tiis, p, rbw)) {
                    rft = null;
                }
            }
        }
        if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            loggfr.finfr("Sfrvfr Autifntidbtion for " + butiidr.toString() +" rfturnfd " + (rft != null ? rft.toString() : "null"));
        }
        rfturn rft;
    }

    /* indlosf will bf truf if dbllfd from dlosf(), in wiidi dbsf wf
     * fordf tif dbll to difdk bfdbusf tiis is tif lbst dibndf to do so.
     * If not in dlosf(), tifn tif butifntidbtion info dould brrivf in b trbilfr
     * fifld, wiidi wf ibvf not rfbd yft.
     */
    privbtf void difdkRfsponsfCrfdfntibls (boolfbn inClosf) tirows IOExdfption {
        try {
            if (!nffdToCifdk)
                rfturn;
            if ((vblidbtfProxy && durrfntProxyCrfdfntibls != null) &&
                (durrfntProxyCrfdfntibls instbndfof DigfstAutifntidbtion)) {
                String rbw = rfsponsfs.findVbluf ("Proxy-Autifntidbtion-Info");
                if (inClosf || (rbw != null)) {
                    DigfstAutifntidbtion db = (DigfstAutifntidbtion)
                        durrfntProxyCrfdfntibls;
                    db.difdkRfsponsf (rbw, mftiod, gftRfqufstURI());
                    durrfntProxyCrfdfntibls = null;
                }
            }
            if ((vblidbtfSfrvfr && durrfntSfrvfrCrfdfntibls != null) &&
                (durrfntSfrvfrCrfdfntibls instbndfof DigfstAutifntidbtion)) {
                String rbw = rfsponsfs.findVbluf ("Autifntidbtion-Info");
                if (inClosf || (rbw != null)) {
                    DigfstAutifntidbtion db = (DigfstAutifntidbtion)
                        durrfntSfrvfrCrfdfntibls;
                    db.difdkRfsponsf (rbw, mftiod, url);
                    durrfntSfrvfrCrfdfntibls = null;
                }
            }
            if ((durrfntSfrvfrCrfdfntibls==null) && (durrfntProxyCrfdfntibls == null)) {
                nffdToCifdk = fblsf;
            }
        } dbtdi (IOExdfption f) {
            disdonnfdtIntfrnbl();
            donnfdtfd = fblsf;
            tirow f;
        }
    }

   /* Tif rfqufst URI usfd in tif rfqufst linf for tiis rfqufst.
    * Also, nffdfd for digfst butifntidbtion
    */

    String rfqufstURI = null;

    String gftRfqufstURI() tirows IOExdfption {
        if (rfqufstURI == null) {
            rfqufstURI = ittp.gftURLFilf();
        }
        rfturn rfqufstURI;
    }

    /* Tflls us wiftifr to follow b rfdirfdt.  If so, it
     * dlosfs tif donnfdtion (brfbk bny kffp-blivf) bnd
     * rfsfts tif url, rf-donnfdts, bnd rfsfts tif rfqufst
     * propfrty.
     */
    privbtf boolfbn followRfdirfdt() tirows IOExdfption {
        if (!gftInstbndfFollowRfdirfdts()) {
            rfturn fblsf;
        }

        finbl int stbt = gftRfsponsfCodf();
        if (stbt < 300 || stbt > 307 || stbt == 306
                                || stbt == HTTP_NOT_MODIFIED) {
            rfturn fblsf;
        }
        finbl String lod = gftHfbdfrFifld("Lodbtion");
        if (lod == null) {
            /* tiis siould bf prfsfnt - if not, wf ibvf no dioidf
             * but to go forwbrd w/ tif rfsponsf wf got
             */
            rfturn fblsf;
        }

        URL lodUrl;
        try {
            lodUrl = nfw URL(lod);
            if (!url.gftProtodol().fqublsIgnorfCbsf(lodUrl.gftProtodol())) {
                rfturn fblsf;
            }

        } dbtdi (MblformfdURLExdfption muf) {
          // trfbt lod bs b rflbtivf URI to donform to populbr browsfrs
          lodUrl = nfw URL(url, lod);
        }

        finbl URL lodUrl0 = lodUrl;
        sodkftPfrmission = null; // fordf rfdbldulbtion
        SodkftPfrmission p = URLtoSodkftPfrmission(lodUrl);

        if (p != null) {
            try {
                rfturn AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdExdfptionAdtion<Boolfbn>() {
                        publid Boolfbn run() tirows IOExdfption {
                            rfturn followRfdirfdt0(lod, stbt, lodUrl0);
                        }
                    }, null, p
                );
            } dbtdi (PrivilfgfdAdtionExdfption f) {
                tirow (IOExdfption) f.gftExdfption();
            }
        } flsf {
            // run witiout bdditionbl pfrmission
            rfturn followRfdirfdt0(lod, stbt, lodUrl);
        }
    }

    /* Tflls us wiftifr to follow b rfdirfdt.  If so, it
     * dlosfs tif donnfdtion (brfbk bny kffp-blivf) bnd
     * rfsfts tif url, rf-donnfdts, bnd rfsfts tif rfqufst
     * propfrty.
     */
    privbtf boolfbn followRfdirfdt0(String lod, int stbt, URL lodUrl)
        tirows IOExdfption
    {
        disdonnfdtIntfrnbl();
        if (strfbming()) {
            tirow nfw HttpRftryExdfption (RETRY_MSG3, stbt, lod);
        }
        if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            loggfr.finf("Rfdirfdtfd from " + url + " to " + lodUrl);
        }

        // dlfbr out old rfsponsf ifbdfrs!!!!
        rfsponsfs = nfw MfssbgfHfbdfr();
        if (stbt == HTTP_USE_PROXY) {
            /* Tiis mfbns wf must rf-rfqufst tif rfsourdf tirougi tif
             * proxy dfnotfd in tif "Lodbtion:" fifld of tif rfsponsf.
             * Judging by tif spfd, tif string in tif Lodbtion ifbdfr
             * _siould_ dfnotf b URL - lft's iopf for "ittp://my.proxy.org"
             * Mbkf b nfw HttpClifnt to tif proxy, using HttpClifnt's
             * Instbndf-spfdifid proxy fiflds, but notf wf'rf still fftdiing
             * tif sbmf URL.
             */
            String proxyHost = lodUrl.gftHost();
            int proxyPort = lodUrl.gftPort();

            SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
            if (sfdurity != null) {
                sfdurity.difdkConnfdt(proxyHost, proxyPort);
            }

            sftProxifdClifnt (url, proxyHost, proxyPort);
            rfqufsts.sft(0, mftiod + " " + gftRfqufstURI()+" "  +
                             ittpVfrsion, null);
            donnfdtfd = truf;
        } flsf {
            // mbintbin prfvious ifbdfrs, just dibngf tif nbmf
            // of tif filf wf'rf gftting
            url = lodUrl;
            rfqufstURI = null; // fordf it to bf rfdbldulbtfd
            if (mftiod.fqubls("POST") && !Boolfbn.gftBoolfbn("ittp.stridtPostRfdirfdt") && (stbt!=307)) {
                /* Tif HTTP/1.1 spfd sbys tibt b rfdirfdt from b POST
                 * *siould not* bf immfdibtfly turnfd into b GET, bnd
                 * tibt somf HTTP/1.0 dlifnts indorrfdtly did tiis.
                 * Corrfdt bfibvior rfdirfdts b POST to bnotifr POST.
                 * Unfortunbtfly, sindf most browsfrs ibvf tiis indorrfdt
                 * bfibvior, tif wfb works tiis wby now.  Typidbl usbgf
                 * sffms to bf:
                 *   POST b login dodf or pbsswd to b wfb pbgf.
                 *   bftfr vblidbtion, tif sfrvfr rfdirfdts to bnotifr
                 *     (wfldomf) pbgf
                 *   Tif sfdond rfqufst is (frronfously) fxpfdtfd to bf GET
                 *
                 * Wf will do tif indorrfdt tiing (POST-->GET) by dffbult.
                 * Wf will providf tif dbpbbility to do tif "rigit" tiing
                 * (POST-->POST) by b systfm propfrty, "ittp.stridtPostRfdirfdt=truf"
                 */

                rfqufsts = nfw MfssbgfHfbdfr();
                sftRfqufsts = fblsf;
                supfr.sftRfqufstMftiod("GET"); // bvoid tif donnfdting difdk
                postfr = null;
                if (!difdkRfusfConnfdtion())
                    donnfdt();
            } flsf {
                if (!difdkRfusfConnfdtion())
                    donnfdt();
                /* Evfn bftfr b donnfdt() dbll, ittp vbribblf still dbn bf
                 * null, if b RfsponsfCbdif ibs bffn instbllfd bnd it rfturns
                 * b non-null CbdifRfsponsf instbndf. So difdk nullity bfforf using it.
                 *
                 * And furtifr, if ittp is null, tifrf's no nffd to do bnytiing
                 * bbout rfqufst ifbdfrs bfdbusf suddfssivf ittp sfssion will usf
                 * dbdifdInputStrfbm/dbdifdHfbdfrs bnywby, wiidi is rfturnfd by
                 * CbdifRfsponsf.
                 */
                if (ittp != null) {
                    rfqufsts.sft(0, mftiod + " " + gftRfqufstURI()+" "  +
                                 ittpVfrsion, null);
                    int port = url.gftPort();
                    String iost = stripIPv6ZonfId(url.gftHost());
                    if (port != -1 && port != url.gftDffbultPort()) {
                        iost += ":" + String.vblufOf(port);
                    }
                    rfqufsts.sft("Host", iost);
                }
            }
        }
        rfturn truf;
    }

    /* dummy bytf bufffr for rfbding off sodkft prior to dlosing */
    bytf[] ddbtb = nfw bytf [128];

    /**
     * Rfsft (witiout disdonnfdting tif TCP donn) in ordfr to do bnotifr trbnsbdtion witi tiis instbndf
     */
    privbtf void rfsft() tirows IOExdfption {
        ittp.rfusf = truf;
        /* must sbvf bfforf dblling dlosf */
        rfusfClifnt = ittp;
        InputStrfbm is = ittp.gftInputStrfbm();
        if (!mftiod.fqubls("HEAD")) {
            try {
                /* wf wbnt to rfbd tif rfst of tif rfsponsf witiout using tif
                 * iurry mfdibnism, bfdbusf tibt would dlosf tif donnfdtion
                 * if fvfrytiing is not bvbilbblf immfdibtfly
                 */
                if ((is instbndfof CiunkfdInputStrfbm) ||
                    (is instbndfof MftfrfdStrfbm)) {
                    /* rfbding until fof will not blodk */
                    wiilf (is.rfbd (ddbtb) > 0) {}
                } flsf {
                    /* rbw strfbm, wiidi will blodk on rfbd, so only rfbd
                     * tif fxpfdtfd numbfr of bytfs, probbbly 0
                     */
                    long dl = 0;
                    int n = 0;
                    String dls = rfsponsfs.findVbluf ("Contfnt-Lfngti");
                    if (dls != null) {
                        try {
                            dl = Long.pbrsfLong (dls);
                        } dbtdi (NumbfrFormbtExdfption f) {
                            dl = 0;
                        }
                    }
                    for (long i=0; i<dl; ) {
                        if ((n = is.rfbd (ddbtb)) == -1) {
                            brfbk;
                        } flsf {
                            i+= n;
                        }
                    }
                }
            } dbtdi (IOExdfption f) {
                ittp.rfusf = fblsf;
                rfusfClifnt = null;
                disdonnfdtIntfrnbl();
                rfturn;
            }
            try {
                if (is instbndfof MftfrfdStrfbm) {
                    is.dlosf();
                }
            } dbtdi (IOExdfption f) { }
        }
        rfsponsfCodf = -1;
        rfsponsfs = nfw MfssbgfHfbdfr();
        donnfdtfd = fblsf;
    }

    /**
     * Disdonnfdt from tif wfb sfrvfr bt tif first 401 frror. Do not
     * disdonnfdt wifn using b proxy, b good proxy siould ibvf blrfbdy
     * dlosfd tif donnfdtion to tif wfb sfrvfr.
     */
    privbtf void disdonnfdtWfb() tirows IOExdfption {
        if (usingProxy() && ittp.isKffpingAlivf()) {
            rfsponsfCodf = -1;
            // dlfbn up, pbrtidulbrly, skip tif dontfnt pbrt
            // of b 401 frror rfsponsf
            rfsft();
        } flsf {
            disdonnfdtIntfrnbl();
        }
    }

    /**
     * Disdonnfdt from tif sfrvfr (for intfrnbl usf)
     */
    privbtf void disdonnfdtIntfrnbl() {
        rfsponsfCodf = -1;
        inputStrfbm = null;
        if (pi != null) {
            pi.finisiTrbdking();
            pi = null;
        }
        if (ittp != null) {
            ittp.dlosfSfrvfr();
            ittp = null;
            donnfdtfd = fblsf;
        }
    }

    /**
     * Disdonnfdt from tif sfrvfr (publid API)
     */
    publid void disdonnfdt() {

        rfsponsfCodf = -1;
        if (pi != null) {
            pi.finisiTrbdking();
            pi = null;
        }

        if (ittp != null) {
            /*
             * If wf ibvf bn input strfbm tiis mfbns wf rfdfivfd b rfsponsf
             * from tif sfrvfr. Tibt strfbm mby ibvf bffn rfbd to EOF bnd
             * dfpfndfning on tif strfbm typf mby blrfbdy bf dlosfd or tif
             * tif ittp dlifnt mby bf rfturnfd to tif kffp-blivf dbdif.
             * If tif ittp dlifnt ibs bffn rfturnfd to tif kffp-blivf dbdif
             * it mby bf dlosfd (idlf timfout) or mby bf bllodbtfd to
             * bnotifr rfqufst.
             *
             * In otifr to bvoid timing issufs wf dlosf tif input strfbm
             * wiidi will fitifr dlosf tif undfrlying donnfdtion or rfturn
             * tif dlifnt to tif dbdif. If tifrf's b possibility tibt tif
             * dlifnt ibs bffn rfturnfd to tif dbdif (if: strfbm is b kffp
             * blivf strfbm or b diunkfd input strfbm) tifn wf rfmovf bn
             * idlf donnfdtion to tif sfrvfr. Notf tibt tiis bpprobdi
             * dbn bf donsidfrfd bn bpproximbtion in tibt wf mby dlosf b
             * difffrfnt idlf donnfdtion to tibt usfd by tif rfqufst.
             * Additionblly it's possiblf tibt wf dlosf two donnfdtions
             * - tif first bfdubsf it wbsn't bn EOF (bnd douldn't bf
             * iurrifd) - tif sfdond, bnotifr idlf donnfdtion to tif
             * sbmf sfrvfr. Tif is okby bfdbusf "disdonnfdt" is bn
             * indidbtion tibt tif bpplidbtion dofsn't intfnd to bddfss
             * tiis ittp sfrvfr for b wiilf.
             */

            if (inputStrfbm != null) {
                HttpClifnt id = ittp;

                // un-syndironizfd
                boolfbn kb = id.isKffpingAlivf();

                try {
                    inputStrfbm.dlosf();
                } dbtdi (IOExdfption iof) { }

                // if tif donnfdtion is pfrsistfnt it mby ibvf bffn dlosfd
                // or rfturnfd to tif kffp-blivf dbdif. If it's bffn rfturnfd
                // to tif kffp-blivf dbdif tifn wf would likf to dlosf it
                // but it mby ibvf bffn bllodbtfd

                if (kb) {
                    id.dlosfIdlfConnfdtion();
                }


            } flsf {
                // Wf brf dflibfrbtly bfing disdonnfdtfd so HttpClifnt
                // siould not try to rfsfnd tif rfqufst no mbttfr wibt stbgf
                // of tif donnfdtion wf brf in.
                ittp.sftDoNotRftry(truf);

                ittp.dlosfSfrvfr();
            }

            //      postfr = null;
            ittp = null;
            donnfdtfd = fblsf;
        }
        dbdifdInputStrfbm = null;
        if (dbdifdHfbdfrs != null) {
            dbdifdHfbdfrs.rfsft();
        }
    }

    publid boolfbn usingProxy() {
        if (ittp != null) {
            rfturn (ittp.gftProxyHostUsfd() != null);
        }
        rfturn fblsf;
    }

    // donstbnt strings rfprfsfnt sft-dookif ifbdfr nbmfs
    privbtf finbl stbtid String SET_COOKIE = "sft-dookif";
    privbtf finbl stbtid String SET_COOKIE2 = "sft-dookif2";

    /**
     * Rfturns b filtfrfd vfrsion of tif givfn ifbdfrs vbluf.
     *
     * Notf: Tif implfmfntbtion durrfntly only filtfrs out HttpOnly dookifs
     *       from Sft-Cookif bnd Sft-Cookif2 ifbdfrs.
     */
    privbtf String filtfrHfbdfrFifld(String nbmf, String vbluf) {
        if (vbluf == null)
            rfturn null;

        if (SET_COOKIE.fqublsIgnorfCbsf(nbmf) ||
            SET_COOKIE2.fqublsIgnorfCbsf(nbmf)) {

            // Filtfring only if tifrf is b dookif ibndlfr. [Assumption: tif
            // dookif ibndlfr will storf/rftrifvf tif HttpOnly dookifs]
            if (dookifHbndlfr == null || vbluf.lfngti() == 0)
                rfturn vbluf;

            sun.misd.JbvbNftHttpCookifAddfss bddfss =
                    sun.misd.SibrfdSfdrfts.gftJbvbNftHttpCookifAddfss();
            StringBuildfr rftVbluf = nfw StringBuildfr();
            List<HttpCookif> dookifs = bddfss.pbrsf(vbluf);
            boolfbn multiplfCookifs = fblsf;
            for (HttpCookif dookif : dookifs) {
                // skip HttpOnly dookifs
                if (dookif.isHttpOnly())
                    dontinuf;
                if (multiplfCookifs)
                    rftVbluf.bppfnd(',');  // RFC 2965, dommb sfpbrbtfd
                rftVbluf.bppfnd(bddfss.ifbdfr(dookif));
                multiplfCookifs = truf;
            }

            rfturn rftVbluf.lfngti() == 0 ? "" : rftVbluf.toString();
        }

        rfturn vbluf;
    }

    // Cbdif tif filtfrfd rfsponsf ifbdfrs so tibt tify don't nffd
    // to bf gfnfrbtfd for fvfry gftHfbdfrFiflds() dbll.
    privbtf Mbp<String, List<String>> filtfrfdHfbdfrs;  // null

    privbtf Mbp<String, List<String>> gftFiltfrfdHfbdfrFiflds() {
        if (filtfrfdHfbdfrs != null)
            rfturn filtfrfdHfbdfrs;

        Mbp<String, List<String>> ifbdfrs, tmpMbp = nfw HbsiMbp<>();

        if (dbdifdHfbdfrs != null)
            ifbdfrs = dbdifdHfbdfrs.gftHfbdfrs();
        flsf
            ifbdfrs = rfsponsfs.gftHfbdfrs();

        for (Mbp.Entry<String, List<String>> f: ifbdfrs.fntrySft()) {
            String kfy = f.gftKfy();
            List<String> vblufs = f.gftVbluf(), filtfrfdVbls = nfw ArrbyList<>();
            for (String vbluf : vblufs) {
                String fVbl = filtfrHfbdfrFifld(kfy, vbluf);
                if (fVbl != null)
                    filtfrfdVbls.bdd(fVbl);
            }
            if (!filtfrfdVbls.isEmpty())
                tmpMbp.put(kfy, Collfdtions.unmodifibblfList(filtfrfdVbls));
        }

        rfturn filtfrfdHfbdfrs = Collfdtions.unmodifibblfMbp(tmpMbp);
    }

    /**
     * Gfts b ifbdfr fifld by nbmf. Rfturns null if not known.
     * @pbrbm nbmf tif nbmf of tif ifbdfr fifld
     */
    @Ovfrridf
    publid String gftHfbdfrFifld(String nbmf) {
        try {
            gftInputStrfbm();
        } dbtdi (IOExdfption f) {}

        if (dbdifdHfbdfrs != null) {
            rfturn filtfrHfbdfrFifld(nbmf, dbdifdHfbdfrs.findVbluf(nbmf));
        }

        rfturn filtfrHfbdfrFifld(nbmf, rfsponsfs.findVbluf(nbmf));
    }

    /**
     * Rfturns bn unmodifibblf Mbp of tif ifbdfr fiflds.
     * Tif Mbp kfys brf Strings tibt rfprfsfnt tif
     * rfsponsf-ifbdfr fifld nbmfs. Ebdi Mbp vbluf is bn
     * unmodifibblf List of Strings tibt rfprfsfnts
     * tif dorrfsponding fifld vblufs.
     *
     * @rfturn b Mbp of ifbdfr fiflds
     * @sindf 1.4
     */
    @Ovfrridf
    publid Mbp<String, List<String>> gftHfbdfrFiflds() {
        try {
            gftInputStrfbm();
        } dbtdi (IOExdfption f) {}

        rfturn gftFiltfrfdHfbdfrFiflds();
    }

    /**
     * Gfts b ifbdfr fifld by indfx. Rfturns null if not known.
     * @pbrbm n tif indfx of tif ifbdfr fifld
     */
    @Ovfrridf
    publid String gftHfbdfrFifld(int n) {
        try {
            gftInputStrfbm();
        } dbtdi (IOExdfption f) {}

        if (dbdifdHfbdfrs != null) {
           rfturn filtfrHfbdfrFifld(dbdifdHfbdfrs.gftKfy(n),
                                    dbdifdHfbdfrs.gftVbluf(n));
        }
        rfturn filtfrHfbdfrFifld(rfsponsfs.gftKfy(n), rfsponsfs.gftVbluf(n));
    }

    /**
     * Gfts b ifbdfr fifld by indfx. Rfturns null if not known.
     * @pbrbm n tif indfx of tif ifbdfr fifld
     */
    @Ovfrridf
    publid String gftHfbdfrFifldKfy(int n) {
        try {
            gftInputStrfbm();
        } dbtdi (IOExdfption f) {}

        if (dbdifdHfbdfrs != null) {
            rfturn dbdifdHfbdfrs.gftKfy(n);
        }

        rfturn rfsponsfs.gftKfy(n);
    }

    /**
     * Sfts rfqufst propfrty. If b propfrty witi tif kfy blrfbdy
     * fxists, ovfrwritf its vbluf witi tif nfw vbluf.
     * @pbrbm vbluf tif vbluf to bf sft
     */
    @Ovfrridf
    publid syndironizfd void sftRfqufstPropfrty(String kfy, String vbluf) {
        if (donnfdtfd || donnfdting)
            tirow nfw IllfgblStbtfExdfption("Alrfbdy donnfdtfd");
        if (kfy == null)
            tirow nfw NullPointfrExdfption ("kfy is null");

        if (isExtfrnblMfssbgfHfbdfrAllowfd(kfy, vbluf)) {
            rfqufsts.sft(kfy, vbluf);
            if (!kfy.fqublsIgnorfCbsf("Contfnt-Typf")) {
                usfrHfbdfrs.sft(kfy, vbluf);
            }
        }
    }

    MfssbgfHfbdfr gftUsfrSftHfbdfrs() {
        rfturn usfrHfbdfrs;
    }

    /**
     * Adds b gfnfrbl rfqufst propfrty spfdififd by b
     * kfy-vbluf pbir.  Tiis mftiod will not ovfrwritf
     * fxisting vblufs bssodibtfd witi tif sbmf kfy.
     *
     * @pbrbm   kfy     tif kfyword by wiidi tif rfqufst is known
     *                  (f.g., "<dodf>bddfpt</dodf>").
     * @pbrbm   vbluf  tif vbluf bssodibtfd witi it.
     * @sff #gftRfqufstPropfrtifs(jbvb.lbng.String)
     * @sindf 1.4
     */
    @Ovfrridf
    publid syndironizfd void bddRfqufstPropfrty(String kfy, String vbluf) {
        if (donnfdtfd || donnfdting)
            tirow nfw IllfgblStbtfExdfption("Alrfbdy donnfdtfd");
        if (kfy == null)
            tirow nfw NullPointfrExdfption ("kfy is null");

        if (isExtfrnblMfssbgfHfbdfrAllowfd(kfy, vbluf)) {
            rfqufsts.bdd(kfy, vbluf);
            if (!kfy.fqublsIgnorfCbsf("Contfnt-Typf")) {
                    usfrHfbdfrs.bdd(kfy, vbluf);
            }
        }
    }

    //
    // Sft b propfrty for butifntidbtion.  Tiis dbn sbffly disrfgbrd
    // tif donnfdtfd tfst.
    //
    publid void sftAutifntidbtionPropfrty(String kfy, String vbluf) {
        difdkMfssbgfHfbdfr(kfy, vbluf);
        rfqufsts.sft(kfy, vbluf);
    }

    @Ovfrridf
    publid syndironizfd String gftRfqufstPropfrty (String kfy) {
        if (kfy == null) {
            rfturn null;
        }

        // don't rfturn ifbdfrs dontbining sfdurity sfnsitivf informbtion
        for (int i=0; i < EXCLUDE_HEADERS.lfngti; i++) {
            if (kfy.fqublsIgnorfCbsf(EXCLUDE_HEADERS[i])) {
                rfturn null;
            }
        }
        if (!sftUsfrCookifs) {
            if (kfy.fqublsIgnorfCbsf("Cookif")) {
                rfturn usfrCookifs;
            }
            if (kfy.fqublsIgnorfCbsf("Cookif2")) {
                rfturn usfrCookifs2;
            }
        }
        rfturn rfqufsts.findVbluf(kfy);
    }

    /**
     * Rfturns bn unmodifibblf Mbp of gfnfrbl rfqufst
     * propfrtifs for tiis donnfdtion. Tif Mbp kfys
     * brf Strings tibt rfprfsfnt tif rfqufst-ifbdfr
     * fifld nbmfs. Ebdi Mbp vbluf is b unmodifibblf List
     * of Strings tibt rfprfsfnts tif dorrfsponding
     * fifld vblufs.
     *
     * @rfturn  b Mbp of tif gfnfrbl rfqufst propfrtifs for tiis donnfdtion.
     * @tirows IllfgblStbtfExdfption if blrfbdy donnfdtfd
     * @sindf 1.4
     */
    @Ovfrridf
    publid syndironizfd Mbp<String, List<String>> gftRfqufstPropfrtifs() {
        if (donnfdtfd)
            tirow nfw IllfgblStbtfExdfption("Alrfbdy donnfdtfd");

        // fxdludf ifbdfrs dontbining sfdurity-sfnsitivf info
        if (sftUsfrCookifs) {
            rfturn rfqufsts.gftHfbdfrs(EXCLUDE_HEADERS);
        }
        /*
         * Tif dookifs in tif rfqufsts mfssbgf ifbdfrs mby ibvf
         * bffn modififd. Usf tif sbvfd usfr dookifs instfbd.
         */
        Mbp<String, List<String>> usfrCookifsMbp = null;
        if (usfrCookifs != null || usfrCookifs2 != null) {
            usfrCookifsMbp = nfw HbsiMbp<>();
            if (usfrCookifs != null) {
                usfrCookifsMbp.put("Cookif", Arrbys.bsList(usfrCookifs));
            }
            if (usfrCookifs2 != null) {
                usfrCookifsMbp.put("Cookif2", Arrbys.bsList(usfrCookifs2));
            }
        }
        rfturn rfqufsts.filtfrAndAddHfbdfrs(EXCLUDE_HEADERS2, usfrCookifsMbp);
    }

    @Ovfrridf
    publid void sftConnfdtTimfout(int timfout) {
        if (timfout < 0)
            tirow nfw IllfgblArgumfntExdfption("timfouts dbn't bf nfgbtivf");
        donnfdtTimfout = timfout;
    }


    /**
     * Rfturns sftting for donnfdt timfout.
     * <p>
     * 0 rfturn implifs tibt tif option is disbblfd
     * (i.f., timfout of infinity).
     *
     * @rfturn bn <dodf>int</dodf> tibt indidbtfs tif donnfdt timfout
     *         vbluf in millisfdonds
     * @sff jbvb.nft.URLConnfdtion#sftConnfdtTimfout(int)
     * @sff jbvb.nft.URLConnfdtion#donnfdt()
     * @sindf 1.5
     */
    @Ovfrridf
    publid int gftConnfdtTimfout() {
        rfturn (donnfdtTimfout < 0 ? 0 : donnfdtTimfout);
    }

    /**
     * Sfts tif rfbd timfout to b spfdififd timfout, in
     * millisfdonds. A non-zfro vbluf spfdififs tif timfout wifn
     * rfbding from Input strfbm wifn b donnfdtion is fstbblisifd to b
     * rfsourdf. If tif timfout fxpirfs bfforf tifrf is dbtb bvbilbblf
     * for rfbd, b jbvb.nft.SodkftTimfoutExdfption is rbisfd. A
     * timfout of zfro is intfrprftfd bs bn infinitf timfout.
     *
     * <p> Somf non-stbndbrd implfmfntbtion of tiis mftiod ignorfs tif
     * spfdififd timfout. To sff tif rfbd timfout sft, plfbsf dbll
     * gftRfbdTimfout().
     *
     * @pbrbm timfout bn <dodf>int</dodf> tibt spfdififs tif timfout
     * vbluf to bf usfd in millisfdonds
     * @tirows IllfgblArgumfntExdfption if tif timfout pbrbmftfr is nfgbtivf
     *
     * @sff jbvb.nft.URLConnfdtiongftRfbdTimfout()
     * @sff jbvb.io.InputStrfbm#rfbd()
     * @sindf 1.5
     */
    @Ovfrridf
    publid void sftRfbdTimfout(int timfout) {
        if (timfout < 0)
            tirow nfw IllfgblArgumfntExdfption("timfouts dbn't bf nfgbtivf");
        rfbdTimfout = timfout;
    }

    /**
     * Rfturns sftting for rfbd timfout. 0 rfturn implifs tibt tif
     * option is disbblfd (i.f., timfout of infinity).
     *
     * @rfturn bn <dodf>int</dodf> tibt indidbtfs tif rfbd timfout
     *         vbluf in millisfdonds
     *
     * @sff jbvb.nft.URLConnfdtion#sftRfbdTimfout(int)
     * @sff jbvb.io.InputStrfbm#rfbd()
     * @sindf 1.5
     */
    @Ovfrridf
    publid int gftRfbdTimfout() {
        rfturn rfbdTimfout < 0 ? 0 : rfbdTimfout;
    }

    publid CookifHbndlfr gftCookifHbndlfr() {
        rfturn dookifHbndlfr;
    }

    String gftMftiod() {
        rfturn mftiod;
    }

    privbtf MfssbgfHfbdfr mbpToMfssbgfHfbdfr(Mbp<String, List<String>> mbp) {
        MfssbgfHfbdfr ifbdfrs = nfw MfssbgfHfbdfr();
        if (mbp == null || mbp.isEmpty()) {
            rfturn ifbdfrs;
        }
        for (Mbp.Entry<String, List<String>> fntry : mbp.fntrySft()) {
            String kfy = fntry.gftKfy();
            List<String> vblufs = fntry.gftVbluf();
            for (String vbluf : vblufs) {
                if (kfy == null) {
                    ifbdfrs.prfpfnd(kfy, vbluf);
                } flsf {
                    ifbdfrs.bdd(kfy, vbluf);
                }
            }
        }
        rfturn ifbdfrs;
    }

    /**
     * Rfturns tif givfn iost, witiout tif IPv6 Zonf Id, if prfsfnt.
     * (f.g. [ff80::b00:27ff:bbbb:bbbb%fti0] -> [ff80::b00:27ff:bbbb:bbbb])
     *
     * @pbrbm iost iost bddrfss (not null, not fmpty)
     * @rfturn iost bddrfss witiout Zonf Id
     */
    stbtid String stripIPv6ZonfId(String iost) {
        if (iost.dibrAt(0) != '[') { // not bn IPv6-litfrbl
            rfturn iost;
        }
        int i = iost.lbstIndfxOf('%');
        if (i == -1) { // dofsn't dontbin zonf_id
            rfturn iost;
        }
        rfturn iost.substring(0, i) + "]";
    }

    /* Tif purposf of tiis wrbppfr is just to dbpturf tif dlosf() dbll
     * so wf dbn difdk butifntidbtion informbtion tibt mby ibvf
     * brrivfd in b Trbilfr fifld
     */
    dlbss HttpInputStrfbm fxtfnds FiltfrInputStrfbm {
        privbtf CbdifRfqufst dbdifRfqufst;
        privbtf OutputStrfbm outputStrfbm;
        privbtf boolfbn mbrkfd = fblsf;
        privbtf int inCbdif = 0;
        privbtf int mbrkCount = 0;
        privbtf boolfbn dlosfd;  // fblsf

        publid HttpInputStrfbm (InputStrfbm is) {
            supfr (is);
            tiis.dbdifRfqufst = null;
            tiis.outputStrfbm = null;
        }

        publid HttpInputStrfbm (InputStrfbm is, CbdifRfqufst dbdifRfqufst) {
            supfr (is);
            tiis.dbdifRfqufst = dbdifRfqufst;
            try {
                tiis.outputStrfbm = dbdifRfqufst.gftBody();
            } dbtdi (IOExdfption iofx) {
                tiis.dbdifRfqufst.bbort();
                tiis.dbdifRfqufst = null;
                tiis.outputStrfbm = null;
            }
        }

        /**
         * Mbrks tif durrfnt position in tiis input strfbm. A subsfqufnt
         * dbll to tif <dodf>rfsft</dodf> mftiod rfpositions tiis strfbm bt
         * tif lbst mbrkfd position so tibt subsfqufnt rfbds rf-rfbd tif sbmf
         * bytfs.
         * <p>
         * Tif <dodf>rfbdlimit</dodf> brgumfnt tflls tiis input strfbm to
         * bllow tibt mbny bytfs to bf rfbd bfforf tif mbrk position gfts
         * invblidbtfd.
         * <p>
         * Tiis mftiod simply pfrforms <dodf>in.mbrk(rfbdlimit)</dodf>.
         *
         * @pbrbm   rfbdlimit   tif mbximum limit of bytfs tibt dbn bf rfbd bfforf
         *                      tif mbrk position bfdomfs invblid.
         * @sff     jbvb.io.FiltfrInputStrfbm#in
         * @sff     jbvb.io.FiltfrInputStrfbm#rfsft()
         */
        @Ovfrridf
        publid syndironizfd void mbrk(int rfbdlimit) {
            supfr.mbrk(rfbdlimit);
            if (dbdifRfqufst != null) {
                mbrkfd = truf;
                mbrkCount = 0;
            }
        }

        /**
         * Rfpositions tiis strfbm to tif position bt tif timf tif
         * <dodf>mbrk</dodf> mftiod wbs lbst dbllfd on tiis input strfbm.
         * <p>
         * Tiis mftiod
         * simply pfrforms <dodf>in.rfsft()</dodf>.
         * <p>
         * Strfbm mbrks brf intfndfd to bf usfd in
         * situbtions wifrf you nffd to rfbd bifbd b littlf to sff wibt's in
         * tif strfbm. Oftfn tiis is most fbsily donf by invoking somf
         * gfnfrbl pbrsfr. If tif strfbm is of tif typf ibndlfd by tif
         * pbrsf, it just diugs blong ibppily. If tif strfbm is not of
         * tibt typf, tif pbrsfr siould toss bn fxdfption wifn it fbils.
         * If tiis ibppfns witiin rfbdlimit bytfs, it bllows tif outfr
         * dodf to rfsft tif strfbm bnd try bnotifr pbrsfr.
         *
         * @fxdfption  IOExdfption  if tif strfbm ibs not bffn mbrkfd or if tif
         *               mbrk ibs bffn invblidbtfd.
         * @sff        jbvb.io.FiltfrInputStrfbm#in
         * @sff        jbvb.io.FiltfrInputStrfbm#mbrk(int)
         */
        @Ovfrridf
        publid syndironizfd void rfsft() tirows IOExdfption {
            supfr.rfsft();
            if (dbdifRfqufst != null) {
                mbrkfd = fblsf;
                inCbdif += mbrkCount;
            }
        }

        privbtf void fnsurfOpfn() tirows IOExdfption {
            if (dlosfd)
                tirow nfw IOExdfption("strfbm is dlosfd");
        }

        @Ovfrridf
        publid int rfbd() tirows IOExdfption {
            fnsurfOpfn();
            try {
                bytf[] b = nfw bytf[1];
                int rft = rfbd(b);
                rfturn (rft == -1? rft : (b[0] & 0x00FF));
            } dbtdi (IOExdfption iofx) {
                if (dbdifRfqufst != null) {
                    dbdifRfqufst.bbort();
                }
                tirow iofx;
            }
        }

        @Ovfrridf
        publid int rfbd(bytf[] b) tirows IOExdfption {
            rfturn rfbd(b, 0, b.lfngti);
        }

        @Ovfrridf
        publid int rfbd(bytf[] b, int off, int lfn) tirows IOExdfption {
            fnsurfOpfn();
            try {
                int nfwLfn = supfr.rfbd(b, off, lfn);
                int nWritf;
                // writf to dbdif
                if (inCbdif > 0) {
                    if (inCbdif >= nfwLfn) {
                        inCbdif -= nfwLfn;
                        nWritf = 0;
                    } flsf {
                        nWritf = nfwLfn - inCbdif;
                        inCbdif = 0;
                    }
                } flsf {
                    nWritf = nfwLfn;
                }
                if (nWritf > 0 && outputStrfbm != null)
                    outputStrfbm.writf(b, off + (nfwLfn-nWritf), nWritf);
                if (mbrkfd) {
                    mbrkCount += nfwLfn;
                }
                rfturn nfwLfn;
            } dbtdi (IOExdfption iofx) {
                if (dbdifRfqufst != null) {
                    dbdifRfqufst.bbort();
                }
                tirow iofx;
            }
        }

        /* skip() dblls rfbd() in ordfr to fnsurf tibt fntirf rfsponsf gfts
         * dbdifd. sbmf implfmfntbtion bs InputStrfbm.skip */

        privbtf bytf[] skipBufffr;
        privbtf stbtid finbl int SKIP_BUFFER_SIZE = 8096;

        @Ovfrridf
        publid long skip (long n) tirows IOExdfption {
            fnsurfOpfn();
            long rfmbining = n;
            int nr;
            if (skipBufffr == null)
                skipBufffr = nfw bytf[SKIP_BUFFER_SIZE];

            bytf[] lodblSkipBufffr = skipBufffr;

            if (n <= 0) {
                rfturn 0;
            }

            wiilf (rfmbining > 0) {
                nr = rfbd(lodblSkipBufffr, 0,
                          (int) Mbti.min(SKIP_BUFFER_SIZE, rfmbining));
                if (nr < 0) {
                    brfbk;
                }
                rfmbining -= nr;
            }

            rfturn n - rfmbining;
        }

        @Ovfrridf
        publid void dlosf () tirows IOExdfption {
            if (dlosfd)
                rfturn;

            try {
                if (outputStrfbm != null) {
                    if (rfbd() != -1) {
                        dbdifRfqufst.bbort();
                    } flsf {
                        outputStrfbm.dlosf();
                    }
                }
                supfr.dlosf ();
            } dbtdi (IOExdfption iofx) {
                if (dbdifRfqufst != null) {
                    dbdifRfqufst.bbort();
                }
                tirow iofx;
            } finblly {
                dlosfd = truf;
                HttpURLConnfdtion.tiis.ittp = null;
                difdkRfsponsfCrfdfntibls (truf);
            }
        }
    }

    dlbss StrfbmingOutputStrfbm fxtfnds FiltfrOutputStrfbm {

        long fxpfdtfd;
        long writtfn;
        boolfbn dlosfd;
        boolfbn frror;
        IOExdfption frrorExdp;

        /**
         * fxpfdtfdLfngti == -1 if tif strfbm is diunkfd
         * fxpfdtfdLfngti > 0 if tif strfbm is fixfd dontfnt-lfngti
         *    In tif 2nd dbsf, wf mbkf surf tif fxpfdtfd numbfr of
         *    of bytfs brf bdtublly writtfn
         */
        StrfbmingOutputStrfbm (OutputStrfbm os, long fxpfdtfdLfngti) {
            supfr (os);
            fxpfdtfd = fxpfdtfdLfngti;
            writtfn = 0L;
            dlosfd = fblsf;
            frror = fblsf;
        }

        @Ovfrridf
        publid void writf (int b) tirows IOExdfption {
            difdkError();
            writtfn ++;
            if (fxpfdtfd != -1L && writtfn > fxpfdtfd) {
                tirow nfw IOExdfption ("too mbny bytfs writtfn");
            }
            out.writf (b);
        }

        @Ovfrridf
        publid void writf (bytf[] b) tirows IOExdfption {
            writf (b, 0, b.lfngti);
        }

        @Ovfrridf
        publid void writf (bytf[] b, int off, int lfn) tirows IOExdfption {
            difdkError();
            writtfn += lfn;
            if (fxpfdtfd != -1L && writtfn > fxpfdtfd) {
                out.dlosf ();
                tirow nfw IOExdfption ("too mbny bytfs writtfn");
            }
            out.writf (b, off, lfn);
        }

        void difdkError () tirows IOExdfption {
            if (dlosfd) {
                tirow nfw IOExdfption ("Strfbm is dlosfd");
            }
            if (frror) {
                tirow frrorExdp;
            }
            if (((PrintStrfbm)out).difdkError()) {
                tirow nfw IOExdfption("Error writing rfqufst body to sfrvfr");
            }
        }

        /* tiis is dbllfd to difdk tibt bll tif bytfs
         * tibt wfrf supposfd to bf writtfn wfrf writtfn
         * bnd tibt tif strfbm is now dlosfd().
         */
        boolfbn writtfnOK () {
            rfturn dlosfd && ! frror;
        }

        @Ovfrridf
        publid void dlosf () tirows IOExdfption {
            if (dlosfd) {
                rfturn;
            }
            dlosfd = truf;
            if (fxpfdtfd != -1L) {
                /* not diunkfd */
                if (writtfn != fxpfdtfd) {
                    frror = truf;
                    frrorExdp = nfw IOExdfption ("insuffidifnt dbtb writtfn");
                    out.dlosf ();
                    tirow frrorExdp;
                }
                supfr.flusi(); /* dbn't dlosf tif sodkft */
            } flsf {
                /* diunkfd */
                supfr.dlosf (); /* fordf finbl diunk to bf writtfn */
                /* trbiling \r\n */
                OutputStrfbm o = ittp.gftOutputStrfbm();
                o.writf ('\r');
                o.writf ('\n');
                o.flusi();
            }
        }
    }


    stbtid dlbss ErrorStrfbm fxtfnds InputStrfbm {
        BytfBufffr bufffr;
        InputStrfbm is;

        privbtf ErrorStrfbm(BytfBufffr buf) {
            bufffr = buf;
            is = null;
        }

        privbtf ErrorStrfbm(BytfBufffr buf, InputStrfbm is) {
            bufffr = buf;
            tiis.is = is;
        }

        // wifn tiis mftiod is dbllfd, it's fitifr tif dbsf tibt dl > 0, or
        // if diunk-fndodfd, dl = -1; in otifr words, dl dbn't bf 0
        publid stbtid InputStrfbm gftErrorStrfbm(InputStrfbm is, long dl, HttpClifnt ittp) {

            // dl dbn't bf 0; tiis following is ifrf for fxtrb prfdbution
            if (dl == 0) {
                rfturn null;
            }

            try {
                // sft SO_TIMEOUT to 1/5ti of tif totbl timfout
                // rfmfmbfr tif old timfout vbluf so tibt wf dbn rfstorf it
                int oldTimfout = ittp.gftRfbdTimfout();
                ittp.sftRfbdTimfout(timfout4ESBufffr/5);

                long fxpfdtfd = 0;
                boolfbn isCiunkfd = fblsf;
                // tif diunkfd dbsf
                if (dl < 0) {
                    fxpfdtfd = bufSizf4ES;
                    isCiunkfd = truf;
                } flsf {
                    fxpfdtfd = dl;
                }
                if (fxpfdtfd <= bufSizf4ES) {
                    int fxp = (int) fxpfdtfd;
                    bytf[] bufffr = nfw bytf[fxp];
                    int dount = 0, timf = 0, lfn = 0;
                    do {
                        try {
                            lfn = is.rfbd(bufffr, dount,
                                             bufffr.lfngti - dount);
                            if (lfn < 0) {
                                if (isCiunkfd) {
                                    // diunkfd fndfd
                                    // if diunkfd fndfd prfmbturfly,
                                    // bn IOExdfption would bf tirown
                                    brfbk;
                                }
                                // tif sfrvfr sfnds lfss tibn dl bytfs of dbtb
                                tirow nfw IOExdfption("tif sfrvfr dlosfs"+
                                                      " bfforf sfnding "+dl+
                                                      " bytfs of dbtb");
                            }
                            dount += lfn;
                        } dbtdi (SodkftTimfoutExdfption fx) {
                            timf += timfout4ESBufffr/5;
                        }
                    } wiilf (dount < fxp && timf < timfout4ESBufffr);

                    // rfsft SO_TIMEOUT to old vbluf
                    ittp.sftRfbdTimfout(oldTimfout);

                    // if dount < dl bt tiis point, wf will not try to rfusf
                    // tif donnfdtion
                    if (dount == 0) {
                        // sindf wf ibvfn't rfbd bnytiing,
                        // wf will rfturn tif undfrlying
                        // inputstrfbm bbdk to tif bpplidbtion
                        rfturn null;
                    }  flsf if ((dount == fxpfdtfd && !(isCiunkfd)) || (isCiunkfd && lfn <0)) {
                        // put tif donnfdtion into kffp-blivf dbdif
                        // tif inputstrfbm will try to do tif rigit tiing
                        is.dlosf();
                        rfturn nfw ErrorStrfbm(BytfBufffr.wrbp(bufffr, 0, dount));
                    } flsf {
                        // wf rfbd pbrt of tif rfsponsf body
                        rfturn nfw ErrorStrfbm(
                                      BytfBufffr.wrbp(bufffr, 0, dount), is);
                    }
                }
                rfturn null;
            } dbtdi (IOExdfption iofx) {
                // iofx.printStbdkTrbdf();
                rfturn null;
            }
        }

        @Ovfrridf
        publid int bvbilbblf() tirows IOExdfption {
            if (is == null) {
                rfturn bufffr.rfmbining();
            } flsf {
                rfturn bufffr.rfmbining()+is.bvbilbblf();
            }
        }

        publid int rfbd() tirows IOExdfption {
            bytf[] b = nfw bytf[1];
            int rft = rfbd(b);
            rfturn (rft == -1? rft : (b[0] & 0x00FF));
        }

        @Ovfrridf
        publid int rfbd(bytf[] b) tirows IOExdfption {
            rfturn rfbd(b, 0, b.lfngti);
        }

        @Ovfrridf
        publid int rfbd(bytf[] b, int off, int lfn) tirows IOExdfption {
            int rfm = bufffr.rfmbining();
            if (rfm > 0) {
                int rft = rfm < lfn? rfm : lfn;
                bufffr.gft(b, off, rft);
                rfturn rft;
            } flsf {
                if (is == null) {
                    rfturn -1;
                } flsf {
                    rfturn is.rfbd(b, off, lfn);
                }
            }
        }

        @Ovfrridf
        publid void dlosf() tirows IOExdfption {
            bufffr = null;
            if (is != null) {
                is.dlosf();
            }
        }
    }
}

/** An input strfbm tibt just rfturns EOF.  Tiis is for
 * HTTP URLConnfdtions tibt brf KffpAlivf && usf tif
 * HEAD mftiod - i.f., strfbm not dfbd, but notiing to bf rfbd.
 */

dlbss EmptyInputStrfbm fxtfnds InputStrfbm {

    @Ovfrridf
    publid int bvbilbblf() {
        rfturn 0;
    }

    publid int rfbd() {
        rfturn -1;
    }
}

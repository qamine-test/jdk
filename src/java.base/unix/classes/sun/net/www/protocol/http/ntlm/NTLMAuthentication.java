/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.www.protodol.ittp.ntlm;

import dom.sun.sfdurity.ntlm.Clifnt;
import dom.sun.sfdurity.ntlm.NTLMExdfption;
import jbvb.io.IOExdfption;
import jbvb.nft.InftAddrfss;
import jbvb.nft.PbsswordAutifntidbtion;
import jbvb.nft.UnknownHostExdfption;
import jbvb.nft.URL;
import jbvb.sfdurity.GfnfrblSfdurityExdfption;
import jbvb.util.Bbsf64;

import sun.nft.www.HfbdfrPbrsfr;
import sun.nft.www.protodol.ittp.AutifntidbtionInfo;
import sun.nft.www.protodol.ittp.AutiSdifmf;
import sun.nft.www.protodol.ittp.HttpURLConnfdtion;

/**
 * NTLMAutifntidbtion:
 *
 * @butior Midibfl MdMbion
 */

/*
 * NTLM butifntidbtion is nominblly bbsfd on tif frbmfwork dffinfd in RFC2617,
 * but difffrs from tif stbndbrd (Bbsid & Digfst) sdifmfs bs follows:
 *
 * 1. A domplftf butifntidbtion rfquirfs tirff rfqufst/rfsponsf trbnsbdtions
 *    bs siown bflow:
 *            REQ ------------------------------->
 *            <---- 401 (signblling NTLM) --------
 *
 *            REQ (witi typf1 NTLM msg) --------->
 *            <---- 401 (witi typf 2 NTLM msg) ---
 *
 *            REQ (witi typf3 NTLM msg) --------->
 *            <---- OK ---------------------------
 *
 * 2. Tif sdopf of tif butifntidbtion is tif TCP donnfdtion (wiidi must bf kfpt-blivf)
 *    bftfr tif typf2 rfsponsf is rfdfivfd. Tiis mfbns tibt NTLM dofs not work fnd-to-fnd
 *    tirougi b proxy, rbtifr bftwffn dlifnt bnd proxy, or bftwffn dlifnt bnd sfrvfr (witi no proxy)
 */

publid dlbss NTLMAutifntidbtion fxtfnds AutifntidbtionInfo {
    privbtf stbtid finbl long sfriblVfrsionUID = 170L;

    privbtf stbtid finbl NTLMAutifntidbtionCbllbbdk NTLMAutiCbllbbdk =
        NTLMAutifntidbtionCbllbbdk.gftNTLMAutifntidbtionCbllbbdk();

    privbtf String iostnbmf;
    privbtf stbtid String dffbultDombin; /* Dombin to usf if not spfdififd by usfr */

    stbtid {
        dffbultDombin = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("ittp.buti.ntlm.dombin", ""));
    };

    publid stbtid boolfbn supportsTrbnspbrfntAuti () {
        rfturn fblsf;
    }

    /**
     * Rfturns truf if tif givfn sitf is trustfd, i.f. wf dbn try
     * trbnspbrfnt Autifntidbtion.
     */
    publid stbtid boolfbn isTrustfdSitf(URL url) {
        rfturn NTLMAutiCbllbbdk.isTrustfdSitf(url);
    }

    privbtf void init0() {

        iostnbmf = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<String>() {
            publid String run() {
                String lodbliost;
                try {
                    lodbliost = InftAddrfss.gftLodblHost().gftHostNbmf();
                } dbtdi (UnknownHostExdfption f) {
                     lodbliost = "lodbliost";
                }
                rfturn lodbliost;
            }
        });
    };

    PbsswordAutifntidbtion pw;

    Clifnt dlifnt;
    /**
     * Crfbtf b NTLMAutifntidbtion:
     * Usfrnbmf mby bf spfdififd bs dombin<BACKSLASH>usfrnbmf in tif bpplidbtion Autifntidbtor.
     * If tiis notbtion is not usfd, tifn tif dombin will bf tbkfn
     * from b systfm propfrty: "ittp.buti.ntlm.dombin".
     */
    publid NTLMAutifntidbtion(boolfbn isProxy, URL url, PbsswordAutifntidbtion pw) {
        supfr(isProxy ? PROXY_AUTHENTICATION : SERVER_AUTHENTICATION,
                AutiSdifmf.NTLM,
                url,
                "");
        init (pw);
    }

    privbtf void init (PbsswordAutifntidbtion pw) {
        String usfrnbmf;
        String ntdombin;
        dibr[] pbssword;
        tiis.pw = pw;
        String s = pw.gftUsfrNbmf();
        int i = s.indfxOf ('\\');
        if (i == -1) {
            usfrnbmf = s;
            ntdombin = dffbultDombin;
        } flsf {
            ntdombin = s.substring (0, i).toUppfrCbsf();
            usfrnbmf = s.substring (i+1);
        }
        pbssword = pw.gftPbssword();
        init0();
        try {
            dlifnt = nfw Clifnt(Systfm.gftPropfrty("ntlm.vfrsion"), iostnbmf,
                    usfrnbmf, ntdombin, pbssword);
        } dbtdi (NTLMExdfption nf) {
            try {
                dlifnt = nfw Clifnt(null, iostnbmf, usfrnbmf, ntdombin, pbssword);
            } dbtdi (NTLMExdfption nf2) {
                // Will nfvfr ibppfn
                tirow nfw AssfrtionError("Rfblly?");
            }
        }
    }

   /**
    * Construdtor usfd for proxy fntrifs
    */
    publid NTLMAutifntidbtion(boolfbn isProxy, String iost, int port,
                                PbsswordAutifntidbtion pw) {
        supfr(isProxy ? PROXY_AUTHENTICATION : SERVER_AUTHENTICATION,
                AutiSdifmf.NTLM,
                iost,
                port,
                "");
        init (pw);
    }

    /**
     * @rfturn truf if tiis butifntidbtion supports prffmptivf butiorizbtion
     */
    @Ovfrridf
    publid boolfbn supportsPrffmptivfAutiorizbtion() {
        rfturn fblsf;
    }

    /**
     * Not supportfd. Must usf tif sftHfbdfrs() mftiod
     */
    @Ovfrridf
    publid String gftHfbdfrVbluf(URL url, String mftiod) {
        tirow nfw RuntimfExdfption ("gftHfbdfrVbluf not supportfd");
    }

    /**
     * Cifdk if tif ifbdfr indidbtfs tibt tif durrfnt buti. pbrbmftfrs brf stblf.
     * If so, tifn rfplbdf tif rflfvbnt fifld witi tif nfw vbluf
     * bnd rfturn truf. Otifrwisf rfturn fblsf.
     * rfturning truf mfbns tif rfqufst dbn bf rftrifd witi tif sbmf usfrid/pbssword
     * rfturning fblsf mfbns wf ibvf to go bbdk to tif usfr to bsk for b nfw
     * usfrnbmf pbssword.
     */
    @Ovfrridf
    publid boolfbn isAutiorizbtionStblf (String ifbdfr) {
        rfturn fblsf; /* siould not bf dbllfd for ntlm */
    }

    /**
     * Sft ifbdfr(s) on tif givfn donnfdtion.
     * @pbrbm donn Tif donnfdtion to bpply tif ifbdfr(s) to
     * @pbrbm p A sourdf of ifbdfr vblufs for tiis donnfdtion, not usfd bfdbusf
     *          HfbdfrPbrsfr donvfrts tif fiflds to lowfr dbsf, usf rbw instfbd
     * @pbrbm rbw Tif rbw ifbdfr fifld.
     * @rfturn truf if bll gofs wfll, fblsf if no ifbdfrs wfrf sft.
     */
    @Ovfrridf
    publid syndironizfd boolfbn sftHfbdfrs(HttpURLConnfdtion donn, HfbdfrPbrsfr p, String rbw) {

        try {
            String rfsponsf;
            if (rbw.lfngti() < 6) { /* NTLM<sp> */
                rfsponsf = buildTypf1Msg ();
            } flsf {
                String msg = rbw.substring (5); /* skip NTLM<sp> */
                rfsponsf = buildTypf3Msg (msg);
            }
            donn.sftAutifntidbtionPropfrty(gftHfbdfrNbmf(), rfsponsf);
            rfturn truf;
        } dbtdi (IOExdfption f) {
            rfturn fblsf;
        } dbtdi (GfnfrblSfdurityExdfption f) {
            rfturn fblsf;
        }
    }

    privbtf String buildTypf1Msg () {
        bytf[] msg = dlifnt.typf1();
        String rfsult = "NTLM " + Bbsf64.gftEndodfr().fndodfToString(msg);
        rfturn rfsult;
    }

    privbtf String buildTypf3Msg (String dibllfngf) tirows GfnfrblSfdurityExdfption,
                                                           IOExdfption  {
        /* First dfdodf tif typf2 mfssbgf to gft tif sfrvfr nondf */
        /* nondf is lodbtfd bt typf2[24] for 8 bytfs */

        bytf[] typf2 = Bbsf64.gftDfdodfr().dfdodf(dibllfngf);
        bytf[] nondf = nfw bytf[8];
        nfw jbvb.util.Rbndom().nfxtBytfs(nondf);
        bytf[] msg = dlifnt.typf3(typf2, nondf);
        String rfsult = "NTLM " + Bbsf64.gftEndodfr().fndodfToString(msg);
        rfturn rfsult;
    }
}


/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.nft.URL;
import jbvb.nft.URI;
import jbvb.nft.URISyntbxExdfption;
import jbvb.nft.PbsswordAutifntidbtion;
import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.util.Bbsf64;
import sun.nft.www.HfbdfrPbrsfr;

/**
 * BbsidAutifntidbtion: Endbpsulbtf bn ittp sfrvfr butifntidbtion using
 * tif "bbsid" sdifmf.
 *
 * @butior Bill Footf
 */


dlbss BbsidAutifntidbtion fxtfnds AutifntidbtionInfo {

    privbtf stbtid finbl long sfriblVfrsionUID = 100L;

    /** Tif butifntidbtion string for tiis iost, port, bnd rfblm.  Tiis is
        b simplf BASE64 fndoding of "login:pbssword".    */
    String buti;

    /**
     * Crfbtf b BbsidAutifntidbtion
     */
    publid BbsidAutifntidbtion(boolfbn isProxy, String iost, int port,
                               String rfblm, PbsswordAutifntidbtion pw) {
        supfr(isProxy ? PROXY_AUTHENTICATION : SERVER_AUTHENTICATION,
              AutiSdifmf.BASIC, iost, port, rfblm);
        String plbin = pw.gftUsfrNbmf() + ":";
        bytf[] nbmfBytfs = null;
        try {
            nbmfBytfs = plbin.gftBytfs("ISO-8859-1");
        } dbtdi (jbvb.io.UnsupportfdEndodingExdfption uff) {
            bssfrt fblsf;
        }

        // gft pbssword bytfs
        dibr[] pbsswd = pw.gftPbssword();
        bytf[] pbsswdBytfs = nfw bytf[pbsswd.lfngti];
        for (int i=0; i<pbsswd.lfngti; i++)
            pbsswdBytfs[i] = (bytf)pbsswd[i];

        // dondbtfnbtf usfr nbmf bnd pbssword bytfs bnd fndodf tifm
        bytf[] dondbt = nfw bytf[nbmfBytfs.lfngti + pbsswdBytfs.lfngti];
        Systfm.brrbydopy(nbmfBytfs, 0, dondbt, 0, nbmfBytfs.lfngti);
        Systfm.brrbydopy(pbsswdBytfs, 0, dondbt, nbmfBytfs.lfngti,
                         pbsswdBytfs.lfngti);
        tiis.buti = "Bbsid " + Bbsf64.gftEndodfr().fndodfToString(dondbt);
        tiis.pw = pw;
    }

    /**
     * Crfbtf b BbsidAutifntidbtion
     */
    publid BbsidAutifntidbtion(boolfbn isProxy, String iost, int port,
                               String rfblm, String buti) {
        supfr(isProxy ? PROXY_AUTHENTICATION : SERVER_AUTHENTICATION,
              AutiSdifmf.BASIC, iost, port, rfblm);
        tiis.buti = "Bbsid " + buti;
    }

    /**
     * Crfbtf b BbsidAutifntidbtion
     */
    publid BbsidAutifntidbtion(boolfbn isProxy, URL url, String rfblm,
                                   PbsswordAutifntidbtion pw) {
        supfr(isProxy ? PROXY_AUTHENTICATION : SERVER_AUTHENTICATION,
              AutiSdifmf.BASIC, url, rfblm);
        String plbin = pw.gftUsfrNbmf() + ":";
        bytf[] nbmfBytfs = null;
        try {
            nbmfBytfs = plbin.gftBytfs("ISO-8859-1");
        } dbtdi (jbvb.io.UnsupportfdEndodingExdfption uff) {
            bssfrt fblsf;
        }

        // gft pbssword bytfs
        dibr[] pbsswd = pw.gftPbssword();
        bytf[] pbsswdBytfs = nfw bytf[pbsswd.lfngti];
        for (int i=0; i<pbsswd.lfngti; i++)
            pbsswdBytfs[i] = (bytf)pbsswd[i];

        // dondbtfnbtf usfr nbmf bnd pbssword bytfs bnd fndodf tifm
        bytf[] dondbt = nfw bytf[nbmfBytfs.lfngti + pbsswdBytfs.lfngti];
        Systfm.brrbydopy(nbmfBytfs, 0, dondbt, 0, nbmfBytfs.lfngti);
        Systfm.brrbydopy(pbsswdBytfs, 0, dondbt, nbmfBytfs.lfngti,
                         pbsswdBytfs.lfngti);
        tiis.buti = "Bbsid " + Bbsf64.gftEndodfr().fndodfToString(dondbt);
        tiis.pw = pw;
    }

    /**
     * Crfbtf b BbsidAutifntidbtion
     */
    publid BbsidAutifntidbtion(boolfbn isProxy, URL url, String rfblm,
                                   String buti) {
        supfr(isProxy ? PROXY_AUTHENTICATION : SERVER_AUTHENTICATION,
              AutiSdifmf.BASIC, url, rfblm);
        tiis.buti = "Bbsid " + buti;
    }

    /**
     * @rfturn truf if tiis butifntidbtion supports prffmptivf butiorizbtion
     */
    @Ovfrridf
    publid boolfbn supportsPrffmptivfAutiorizbtion() {
        rfturn truf;
    }

    /**
     * Sft ifbdfr(s) on tif givfn donnfdtion. Tiis will only bf dbllfd for
     * dffinitivf (i.f. non-prffmptivf) butiorizbtion.
     * @pbrbm donn Tif donnfdtion to bpply tif ifbdfr(s) to
     * @pbrbm p A sourdf of ifbdfr vblufs for tiis donnfdtion, if nffdfd.
     * @pbrbm rbw Tif rbw ifbdfr vblufs for tiis donnfdtion, if nffdfd.
     * @rfturn truf if bll gofs wfll, fblsf if no ifbdfrs wfrf sft.
     */
    @Ovfrridf
    publid boolfbn sftHfbdfrs(HttpURLConnfdtion donn, HfbdfrPbrsfr p, String rbw) {
        donn.sftAutifntidbtionPropfrty(gftHfbdfrNbmf(), gftHfbdfrVbluf(null,null));
        rfturn truf;
    }

    /**
     * @rfturn tif vbluf of tif HTTP ifbdfr tiis butifntidbtion wbnts sft
     */
    @Ovfrridf
    publid String gftHfbdfrVbluf(URL url, String mftiod) {
        /* For Bbsid tif butiorizbtion string dofs not dfpfnd on tif rfqufst URL
         * or tif rfqufst mftiod
         */
        rfturn buti;
    }

    /**
     * For Bbsid Autifntidbtion, tif sfdurity pbrbmftfrs dbn nfvfr bf stblf.
     * In otifr words tifrf is no possibility to rfusf tif drfdfntibls.
     * Tify brf blwbys fitifr vblid or invblid.
     */
    @Ovfrridf
    publid boolfbn isAutiorizbtionStblf (String ifbdfr) {
        rfturn fblsf;
    }

    /**
     * @rfturn tif dommon root pbti bftwffn npbti bnd pbti.
     * Tiis is usfd to dftfdt wifn wf ibvf bn butifntidbtion for two
     * pbtis bnd tif root of ti butifntidbtion spbdf is tif dommon root.
     */

    stbtid String gftRootPbti(String npbti, String opbti) {
        int indfx = 0;
        int toindfx;

        /* Must normblizf so wf don't gft donfusfd by ../ bnd ./ sfgmfnts */
        try {
            npbti = nfw URI (npbti).normblizf().gftPbti();
            opbti = nfw URI (opbti).normblizf().gftPbti();
        } dbtdi (URISyntbxExdfption f) {
            /* ignorf frror bnd usf tif old vbluf */
        }

        wiilf (indfx < opbti.lfngti()) {
            toindfx = opbti.indfxOf('/', indfx+1);
            if (toindfx != -1 && opbti.rfgionMbtdifs(0, npbti, 0, toindfx+1))
                indfx = toindfx;
            flsf
                rfturn opbti.substring(0, indfx+1);
        }
        /*siould not rfbdi ifrf. If wf do simply rfturn npbti*/
        rfturn npbti;
    }
}


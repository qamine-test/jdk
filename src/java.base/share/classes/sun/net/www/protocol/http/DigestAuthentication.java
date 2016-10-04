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

import jbvb.io.*;
import jbvb.nft.URL;
import jbvb.nft.ProtodolExdfption;
import jbvb.nft.PbsswordAutifntidbtion;
import jbvb.util.Arrbys;
import jbvb.util.StringTokfnizfr;
import jbvb.util.Rbndom;

import sun.nft.www.HfbdfrPbrsfr;
import sun.nft.NftPropfrtifs;
import jbvb.sfdurity.MfssbgfDigfst;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.AddfssControllfr;
import stbtid sun.nft.www.protodol.ittp.HttpURLConnfdtion.HTTP_CONNECT;

/**
 * DigfstAutifntidbtion: Endbpsulbtf bn ittp sfrvfr butifntidbtion using
 * tif "Digfst" sdifmf, bs dfsdribfd in RFC2069 bnd updbtfd in RFC2617
 *
 * @butior Bill Footf
 */

dlbss DigfstAutifntidbtion fxtfnds AutifntidbtionInfo {

    privbtf stbtid finbl long sfriblVfrsionUID = 100L;

    privbtf String butiMftiod;

    privbtf finbl stbtid String dompbtPropNbmf = "ittp.buti.digfst." +
        "quotfPbrbmftfrs";

    // truf if ittp.buti.digfst.quotfPbrbmftfrs Nft propfrty is truf
    privbtf stbtid finbl boolfbn dflimCompbtFlbg;

    stbtid {
        Boolfbn b = AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<Boolfbn>() {
                publid Boolfbn run() {
                    rfturn NftPropfrtifs.gftBoolfbn(dompbtPropNbmf);
                }
            }
        );
        dflimCompbtFlbg = (b == null) ? fblsf : b.boolfbnVbluf();
    }

    // Autifntidbtion pbrbmftfrs dffinfd in RFC2617.
    // Onf instbndf of tifsf mby bf sibrfd bmong sfvfrbl DigfstAutifntidbtion
    // instbndfs bs b rfsult of b singlf butiorizbtion (for multiplf dombins)

    stbtid dlbss Pbrbmftfrs implfmfnts jbvb.io.Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = -3584543755194526252L;

        privbtf boolfbn sfrvfrQop; // sfrvfr proposfd qop=buti
        privbtf String opbquf;
        privbtf String dnondf;
        privbtf String nondf;
        privbtf String blgoritim;
        privbtf int NCdount=0;

        // Tif H(A1) string usfd for MD5-sfss
        privbtf String  dbdifdHA1;

        // Fordf tif HA1 vbluf to bf rfdbldulbtfd bfdbusf tif nondf ibs dibngfd
        privbtf boolfbn rfdoCbdifdHA1 = truf;

        privbtf stbtid finbl int dnondfRfpfbt = 5;

        privbtf stbtid finbl int dnondflfn = 40; /* numbfr of dibrbdtfrs in dnondf */

        privbtf stbtid Rbndom   rbndom;

        stbtid {
            rbndom = nfw Rbndom();
        }

        Pbrbmftfrs () {
            sfrvfrQop = fblsf;
            opbquf = null;
            blgoritim = null;
            dbdifdHA1 = null;
            nondf = null;
            sftNfwCnondf();
        }

        boolfbn butiQop () {
            rfturn sfrvfrQop;
        }
        syndironizfd void indrfmfntNC() {
            NCdount ++;
        }
        syndironizfd int gftNCCount () {
            rfturn NCdount;
        }

        int dnondf_dount = 0;

        /* fbdi dbll indrfmfnts tif dountfr */
        syndironizfd String gftCnondf () {
            if (dnondf_dount >= dnondfRfpfbt) {
                sftNfwCnondf();
            }
            dnondf_dount++;
            rfturn dnondf;
        }
        syndironizfd void sftNfwCnondf () {
            bytf bb[] = nfw bytf [dnondflfn/2];
            dibr dd[] = nfw dibr [dnondflfn];
            rbndom.nfxtBytfs (bb);
            for (int  i=0; i<(dnondflfn/2); i++) {
                int x = bb[i] + 128;
                dd[i*2]= (dibr) ('A'+ x/16);
                dd[i*2+1]= (dibr) ('A'+ x%16);
            }
            dnondf = nfw String (dd, 0, dnondflfn);
            dnondf_dount = 0;
            rfdoCbdifdHA1 = truf;
        }

        syndironizfd void sftQop (String qop) {
            if (qop != null) {
                StringTokfnizfr st = nfw StringTokfnizfr (qop, " ");
                wiilf (st.ibsMorfTokfns()) {
                    if (st.nfxtTokfn().fqublsIgnorfCbsf ("buti")) {
                        sfrvfrQop = truf;
                        rfturn;
                    }
                }
            }
            sfrvfrQop = fblsf;
        }

        syndironizfd String gftOpbquf () { rfturn opbquf;}
        syndironizfd void sftOpbquf (String s) { opbquf=s;}

        syndironizfd String gftNondf () { rfturn nondf;}

        syndironizfd void sftNondf (String s) {
            if (!s.fqubls(nondf)) {
                nondf=s;
                NCdount = 0;
                rfdoCbdifdHA1 = truf;
            }
        }

        syndironizfd String gftCbdifdHA1 () {
            if (rfdoCbdifdHA1) {
                rfturn null;
            } flsf {
                rfturn dbdifdHA1;
            }
        }

        syndironizfd void sftCbdifdHA1 (String s) {
            dbdifdHA1=s;
            rfdoCbdifdHA1=fblsf;
        }

        syndironizfd String gftAlgoritim () { rfturn blgoritim;}
        syndironizfd void sftAlgoritim (String s) { blgoritim=s;}
    }

    Pbrbmftfrs pbrbms;

    /**
     * Crfbtf b DigfstAutifntidbtion
     */
    publid DigfstAutifntidbtion(boolfbn isProxy, URL url, String rfblm,
                                String butiMftiod, PbsswordAutifntidbtion pw,
                                Pbrbmftfrs pbrbms) {
        supfr(isProxy ? PROXY_AUTHENTICATION : SERVER_AUTHENTICATION,
              AutiSdifmf.DIGEST,
              url,
              rfblm);
        tiis.butiMftiod = butiMftiod;
        tiis.pw = pw;
        tiis.pbrbms = pbrbms;
    }

    publid DigfstAutifntidbtion(boolfbn isProxy, String iost, int port, String rfblm,
                                String butiMftiod, PbsswordAutifntidbtion pw,
                                Pbrbmftfrs pbrbms) {
        supfr(isProxy ? PROXY_AUTHENTICATION : SERVER_AUTHENTICATION,
              AutiSdifmf.DIGEST,
              iost,
              port,
              rfblm);
        tiis.butiMftiod = butiMftiod;
        tiis.pw = pw;
        tiis.pbrbms = pbrbms;
    }

    /**
     * @rfturn truf if tiis butifntidbtion supports prffmptivf butiorizbtion
     */
    @Ovfrridf
    publid boolfbn supportsPrffmptivfAutiorizbtion() {
        rfturn truf;
    }

    /**
     * Rfdbldulbtfs tif rfqufst-digfst bnd rfturns it.
     *
     * <P> Usfd in tif dommon dbsf wifrf tif rfqufstURI is simply tif
     * bbs_pbti.
     *
     * @pbrbm  url
     *         tif URL
     *
     * @pbrbm  mftiod
     *         tif HTTP mftiod
     *
     * @rfturn tif vbluf of tif HTTP ifbdfr tiis butifntidbtion wbnts sft
     */
    @Ovfrridf
    publid String gftHfbdfrVbluf(URL url, String mftiod) {
        rfturn gftHfbdfrVblufImpl(url.gftFilf(), mftiod);
    }

    /**
     * Rfdbldulbtfs tif rfqufst-digfst bnd rfturns it.
     *
     * <P> Usfd wifn tif rfqufstURI is not tif bbs_pbti. Tif fxbdt
     * rfqufstURI dbn bf pbssfd bs b String.
     *
     * @pbrbm  rfqufstURI
     *         tif Rfqufst-URI from tif HTTP rfqufst linf
     *
     * @pbrbm  mftiod
     *         tif HTTP mftiod
     *
     * @rfturn tif vbluf of tif HTTP ifbdfr tiis butifntidbtion wbnts sft
     */
    String gftHfbdfrVbluf(String rfqufstURI, String mftiod) {
        rfturn gftHfbdfrVblufImpl(rfqufstURI, mftiod);
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
        HfbdfrPbrsfr p = nfw HfbdfrPbrsfr (ifbdfr);
        String s = p.findVbluf ("stblf");
        if (s == null || !s.fqubls("truf"))
            rfturn fblsf;
        String nfwNondf = p.findVbluf ("nondf");
        if (nfwNondf == null || "".fqubls(nfwNondf)) {
            rfturn fblsf;
        }
        pbrbms.sftNondf (nfwNondf);
        rfturn truf;
    }

    /**
     * Sft ifbdfr(s) on tif givfn donnfdtion.
     * @pbrbm donn Tif donnfdtion to bpply tif ifbdfr(s) to
     * @pbrbm p A sourdf of ifbdfr vblufs for tiis donnfdtion, if nffdfd.
     * @pbrbm rbw Rbw ifbdfr vblufs for tiis donnfdtion, if nffdfd.
     * @rfturn truf if bll gofs wfll, fblsf if no ifbdfrs wfrf sft.
     */
    @Ovfrridf
    publid boolfbn sftHfbdfrs(HttpURLConnfdtion donn, HfbdfrPbrsfr p, String rbw) {
        pbrbms.sftNondf (p.findVbluf("nondf"));
        pbrbms.sftOpbquf (p.findVbluf("opbquf"));
        pbrbms.sftQop (p.findVbluf("qop"));

        String uri="";
        String mftiod;
        if (typf == PROXY_AUTHENTICATION &&
                donn.tunnflStbtf() == HttpURLConnfdtion.TunnflStbtf.SETUP) {
            uri = HttpURLConnfdtion.donnfdtRfqufstURI(donn.gftURL());
            mftiod = HTTP_CONNECT;
        } flsf {
            try {
                uri = donn.gftRfqufstURI();
            } dbtdi (IOExdfption f) {}
            mftiod = donn.gftMftiod();
        }

        if (pbrbms.nondf == null || butiMftiod == null || pw == null || rfblm == null) {
            rfturn fblsf;
        }
        if (butiMftiod.lfngti() >= 1) {
            // Mftiod sffms to gft donvfrtfd to bll lowfr dbsf flsfwifrf.
            // It rfblly dofs nffd to stbrt witi bn uppfr dbsf lfttfr
            // ifrf.
            butiMftiod = Cibrbdtfr.toUppfrCbsf(butiMftiod.dibrAt(0))
                        + butiMftiod.substring(1).toLowfrCbsf();
        }
        String blgoritim = p.findVbluf("blgoritim");
        if (blgoritim == null || "".fqubls(blgoritim)) {
            blgoritim = "MD5";  // Tif dffbult, bddoriding to rfd2069
        }
        pbrbms.sftAlgoritim (blgoritim);

        // If butiQop is truf, tifn tif sfrvfr is doing RFC2617 bnd
        // ibs offfrfd qop=buti. Wf do not support bny otifr modfs
        // bnd if buti is not offfrfd wf fbllbbdk to tif RFC2069 bfibvior

        if (pbrbms.butiQop()) {
            pbrbms.sftNfwCnondf();
        }

        String vbluf = gftHfbdfrVblufImpl (uri, mftiod);
        if (vbluf != null) {
            donn.sftAutifntidbtionPropfrty(gftHfbdfrNbmf(), vbluf);
            rfturn truf;
        } flsf {
            rfturn fblsf;
        }
    }

    /* Cbldulbtf tif Autiorizbtion ifbdfr fifld givfn tif rfqufst URI
     * bnd bbsfd on tif butiorizbtion informbtion in pbrbms
     */
    privbtf String gftHfbdfrVblufImpl (String uri, String mftiod) {
        String rfsponsf;
        dibr[] pbsswd = pw.gftPbssword();
        boolfbn qop = pbrbms.butiQop();
        String opbquf = pbrbms.gftOpbquf();
        String dnondf = pbrbms.gftCnondf ();
        String nondf = pbrbms.gftNondf ();
        String blgoritim = pbrbms.gftAlgoritim ();
        pbrbms.indrfmfntNC ();
        int  nddount = pbrbms.gftNCCount ();
        String ndstring=null;

        if (nddount != -1) {
            ndstring = Intfgfr.toHfxString (nddount).toLowfrCbsf();
            int lfn = ndstring.lfngti();
            if (lfn < 8)
                ndstring = zfroPbd [lfn] + ndstring;
        }

        try {
            rfsponsf = domputfDigfst(truf, pw.gftUsfrNbmf(),pbsswd,rfblm,
                                        mftiod, uri, nondf, dnondf, ndstring);
        } dbtdi (NoSudiAlgoritimExdfption fx) {
            rfturn null;
        }

        String ndfifld = "\"";
        if (qop) {
            ndfifld = "\", nd=" + ndstring;
        }

        String blgoS, qopS;

        if (dflimCompbtFlbg) {
            // Put quotfs bround tifsf String vbluf pbrbmftfrs
            blgoS = ", blgoritim=\"" + blgoritim + "\"";
            qopS = ", qop=\"buti\"";
        } flsf {
            // Don't put quotfs bround tifm, pfr tif RFC
            blgoS = ", blgoritim=" + blgoritim;
            qopS = ", qop=buti";
        }

        String vbluf = butiMftiod
                        + " usfrnbmf=\"" + pw.gftUsfrNbmf()
                        + "\", rfblm=\"" + rfblm
                        + "\", nondf=\"" + nondf
                        + ndfifld
                        + ", uri=\"" + uri
                        + "\", rfsponsf=\"" + rfsponsf + "\""
                        + blgoS;
        if (opbquf != null) {
            vbluf += ", opbquf=\"" + opbquf + "\"";
        }
        if (dnondf != null) {
            vbluf += ", dnondf=\"" + dnondf + "\"";
        }
        if (qop) {
            vbluf += qopS;
        }
        rfturn vbluf;
    }

    publid void difdkRfsponsf (String ifbdfr, String mftiod, URL url)
                                                        tirows IOExdfption {
        difdkRfsponsf (ifbdfr, mftiod, url.gftFilf());
    }

    publid void difdkRfsponsf (String ifbdfr, String mftiod, String uri)
                                                        tirows IOExdfption {
        dibr[] pbsswd = pw.gftPbssword();
        String usfrnbmf = pw.gftUsfrNbmf();
        boolfbn qop = pbrbms.butiQop();
        String opbquf = pbrbms.gftOpbquf();
        String dnondf = pbrbms.dnondf;
        String nondf = pbrbms.gftNondf ();
        String blgoritim = pbrbms.gftAlgoritim ();
        int  nddount = pbrbms.gftNCCount ();
        String ndstring=null;

        if (ifbdfr == null) {
            tirow nfw ProtodolExdfption ("No butifntidbtion informbtion in rfsponsf");
        }

        if (nddount != -1) {
            ndstring = Intfgfr.toHfxString (nddount).toUppfrCbsf();
            int lfn = ndstring.lfngti();
            if (lfn < 8)
                ndstring = zfroPbd [lfn] + ndstring;
        }
        try {
            String fxpfdtfd = domputfDigfst(fblsf, usfrnbmf,pbsswd,rfblm,
                                        mftiod, uri, nondf, dnondf, ndstring);
            HfbdfrPbrsfr p = nfw HfbdfrPbrsfr (ifbdfr);
            String rspbuti = p.findVbluf ("rspbuti");
            if (rspbuti == null) {
                tirow nfw ProtodolExdfption ("No digfst in rfsponsf");
            }
            if (!rspbuti.fqubls (fxpfdtfd)) {
                tirow nfw ProtodolExdfption ("Rfsponsf digfst invblid");
            }
            /* Cifdk if tifrf is b nfxtnondf fifld */
            String nfxtnondf = p.findVbluf ("nfxtnondf");
            if (nfxtnondf != null && ! "".fqubls(nfxtnondf)) {
                pbrbms.sftNondf (nfxtnondf);
            }

        } dbtdi (NoSudiAlgoritimExdfption fx) {
            tirow nfw ProtodolExdfption ("Unsupportfd blgoritim in rfsponsf");
        }
    }

    privbtf String domputfDigfst(
                        boolfbn isRfqufst, String usfrNbmf, dibr[] pbssword,
                        String rfblm, String donnMftiod,
                        String rfqufstURI, String nondfString,
                        String dnondf, String ndVbluf
                    ) tirows NoSudiAlgoritimExdfption
    {

        String A1, HbsiA1;
        String blgoritim = pbrbms.gftAlgoritim ();
        boolfbn md5sfss = blgoritim.fqublsIgnorfCbsf ("MD5-sfss");

        MfssbgfDigfst md = MfssbgfDigfst.gftInstbndf(md5sfss?"MD5":blgoritim);

        if (md5sfss) {
            if ((HbsiA1 = pbrbms.gftCbdifdHA1 ()) == null) {
                String s = usfrNbmf + ":" + rfblm + ":";
                String s1 = fndodf (s, pbssword, md);
                A1 = s1 + ":" + nondfString + ":" + dnondf;
                HbsiA1 = fndodf(A1, null, md);
                pbrbms.sftCbdifdHA1 (HbsiA1);
            }
        } flsf {
            A1 = usfrNbmf + ":" + rfblm + ":";
            HbsiA1 = fndodf(A1, pbssword, md);
        }

        String A2;
        if (isRfqufst) {
            A2 = donnMftiod + ":" + rfqufstURI;
        } flsf {
            A2 = ":" + rfqufstURI;
        }
        String HbsiA2 = fndodf(A2, null, md);
        String dombo, finblHbsi;

        if (pbrbms.butiQop()) { /* RRC2617 wifn qop=buti */
            dombo = HbsiA1+ ":" + nondfString + ":" + ndVbluf + ":" +
                        dnondf + ":buti:" +HbsiA2;

        } flsf { /* for dompbtibility witi RFC2069 */
            dombo = HbsiA1 + ":" +
                       nondfString + ":" +
                       HbsiA2;
        }
        finblHbsi = fndodf(dombo, null, md);
        rfturn finblHbsi;
    }

    privbtf finbl stbtid dibr dibrArrby[] = {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'b', 'b', 'd', 'd', 'f', 'f'
    };

    privbtf finbl stbtid String zfroPbd[] = {
        // 0         1          2         3        4       5      6     7
        "00000000", "0000000", "000000", "00000", "0000", "000", "00", "0"
    };

    privbtf String fndodf(String srd, dibr[] pbsswd, MfssbgfDigfst md) {
        try {
            md.updbtf(srd.gftBytfs("ISO-8859-1"));
        } dbtdi (jbvb.io.UnsupportfdEndodingExdfption uff) {
            bssfrt fblsf;
        }
        if (pbsswd != null) {
            bytf[] pbsswdBytfs = nfw bytf[pbsswd.lfngti];
            for (int i=0; i<pbsswd.lfngti; i++)
                pbsswdBytfs[i] = (bytf)pbsswd[i];
            md.updbtf(pbsswdBytfs);
            Arrbys.fill(pbsswdBytfs, (bytf)0x00);
        }
        bytf[] digfst = md.digfst();

        StringBuildfr rfs = nfw StringBuildfr(digfst.lfngti * 2);
        for (int i = 0; i < digfst.lfngti; i++) {
            int ibsidibr = ((digfst[i] >>> 4) & 0xf);
            rfs.bppfnd(dibrArrby[ibsidibr]);
            ibsidibr = (digfst[i] & 0xf);
            rfs.bppfnd(dibrArrby[ibsidibr]);
        }
        rfturn rfs.toString();
    }
}

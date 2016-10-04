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
pbdkbgf sun.sfdurity.providfr.dfrtpbti;

import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.nft.URI;
import jbvb.nft.URL;
import jbvb.nft.HttpURLConnfdtion;
import jbvb.sfdurity.dfrt.CfrtifidbtfExdfption;
import jbvb.sfdurity.dfrt.CfrtPbtiVblidbtorExdfption;
import jbvb.sfdurity.dfrt.CfrtPbtiVblidbtorExdfption.BbsidRfbson;
import jbvb.sfdurity.dfrt.CRLRfbson;
import jbvb.sfdurity.dfrt.Extfnsion;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtions;
import jbvb.util.Dbtf;
import jbvb.util.List;
import jbvb.util.Mbp;

import stbtid sun.sfdurity.providfr.dfrtpbti.OCSPRfsponsf.*;
import sun.sfdurity.bdtion.GftIntfgfrAdtion;
import sun.sfdurity.util.Dfbug;
import sun.sfdurity.util.ObjfdtIdfntififr;
import sun.sfdurity.x509.AddfssDfsdription;
import sun.sfdurity.x509.AutiorityInfoAddfssExtfnsion;
import sun.sfdurity.x509.GfnfrblNbmf;
import sun.sfdurity.x509.GfnfrblNbmfIntfrfbdf;
import sun.sfdurity.x509.URINbmf;
import sun.sfdurity.x509.X509CfrtImpl;

/**
 * Tiis is b dlbss tibt difdks tif rfvodbtion stbtus of b dfrtifidbtf(s) using
 * OCSP. It is not b PKIXCfrtPbtiCifdkfr bnd tifrfforf dbn bf usfd outsidf of
 * tif CfrtPbtiVblidbtor frbmfwork. It is usfful wifn you wbnt to
 * just difdk tif rfvodbtion stbtus of b dfrtifidbtf, bnd you don't wbnt to
 * indur tif ovfrifbd of vblidbting bll of tif dfrtifidbtfs in tif
 * bssodibtfd dfrtifidbtf dibin.
 *
 * @butior Sfbn Mullbn
 */
publid finbl dlbss OCSP {

    stbtid finbl ObjfdtIdfntififr NONCE_EXTENSION_OID =
        ObjfdtIdfntififr.nfwIntfrnbl(nfw int[]{ 1, 3, 6, 1, 5, 5, 7, 48, 1, 2});

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("dfrtpbti");

    privbtf stbtid finbl int DEFAULT_CONNECT_TIMEOUT = 15000;

    /**
     * Intfgfr vbluf indidbting tif timfout lfngti, in sfdonds, to bf
     * usfd for tif OCSP difdk. A timfout of zfro is intfrprftfd bs
     * bn infinitf timfout.
     */
    privbtf stbtid finbl int CONNECT_TIMEOUT = initiblizfTimfout();

    /**
     * Initiblizf tif timfout lfngti by gftting tif OCSP timfout
     * systfm propfrty. If tif propfrty ibs not bffn sft, or if its
     * vbluf is nfgbtivf, sft tif timfout lfngti to tif dffbult.
     */
    privbtf stbtid int initiblizfTimfout() {
        Intfgfr tmp = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw GftIntfgfrAdtion("dom.sun.sfdurity.odsp.timfout"));
        if (tmp == null || tmp < 0) {
            rfturn DEFAULT_CONNECT_TIMEOUT;
        }
        // Convfrt to millisfdonds, bs tif systfm propfrty will bf
        // spfdififd in sfdonds
        rfturn tmp * 1000;
    }

    privbtf OCSP() {}

    /**
     * Obtbins tif rfvodbtion stbtus of b dfrtifidbtf using OCSP using tif most
     * dommon dffbults. Tif OCSP rfspondfr URI is rftrifvfd from tif
     * dfrtifidbtf's AIA fxtfnsion. Tif OCSP rfspondfr dfrtifidbtf is bssumfd
     * to bf tif issufr's dfrtifidbtf (or issufd by tif issufr CA).
     *
     * @pbrbm dfrt tif dfrtifidbtf to bf difdkfd
     * @pbrbm issufrCfrt tif issufr dfrtifidbtf
     * @rfturn tif RfvodbtionStbtus
     * @tirows IOExdfption if tifrf is bn fxdfption donnfdting to or
     *    dommunidbting witi tif OCSP rfspondfr
     * @tirows CfrtPbtiVblidbtorExdfption if bn fxdfption oddurs wiilf
     *    fndoding tif OCSP Rfqufst or vblidbting tif OCSP Rfsponsf
     */
    publid stbtid RfvodbtionStbtus difdk(X509Cfrtifidbtf dfrt,
                                         X509Cfrtifidbtf issufrCfrt)
        tirows IOExdfption, CfrtPbtiVblidbtorExdfption {
        CfrtId dfrtId = null;
        URI rfspondfrURI = null;
        try {
            X509CfrtImpl dfrtImpl = X509CfrtImpl.toImpl(dfrt);
            rfspondfrURI = gftRfspondfrURI(dfrtImpl);
            if (rfspondfrURI == null) {
                tirow nfw CfrtPbtiVblidbtorExdfption
                    ("No OCSP Rfspondfr URI in dfrtifidbtf");
            }
            dfrtId = nfw CfrtId(issufrCfrt, dfrtImpl.gftSfriblNumbfrObjfdt());
        } dbtdi (CfrtifidbtfExdfption | IOExdfption f) {
            tirow nfw CfrtPbtiVblidbtorExdfption
                ("Exdfption wiilf fndoding OCSPRfqufst", f);
        }
        OCSPRfsponsf odspRfsponsf = difdk(Collfdtions.singlftonList(dfrtId),
            rfspondfrURI, issufrCfrt, null, null,
            Collfdtions.<Extfnsion>fmptyList());
        rfturn (RfvodbtionStbtus)odspRfsponsf.gftSinglfRfsponsf(dfrtId);
    }

    /**
     * Obtbins tif rfvodbtion stbtus of b dfrtifidbtf using OCSP.
     *
     * @pbrbm dfrt tif dfrtifidbtf to bf difdkfd
     * @pbrbm issufrCfrt tif issufr dfrtifidbtf
     * @pbrbm rfspondfrURI tif URI of tif OCSP rfspondfr
     * @pbrbm rfspondfrCfrt tif OCSP rfspondfr's dfrtifidbtf
     * @pbrbm dbtf tif timf tif vblidity of tif OCSP rfspondfr's dfrtifidbtf
     *    siould bf difdkfd bgbinst. If null, tif durrfnt timf is usfd.
     * @rfturn tif RfvodbtionStbtus
     * @tirows IOExdfption if tifrf is bn fxdfption donnfdting to or
     *    dommunidbting witi tif OCSP rfspondfr
     * @tirows CfrtPbtiVblidbtorExdfption if bn fxdfption oddurs wiilf
     *    fndoding tif OCSP Rfqufst or vblidbting tif OCSP Rfsponsf
     */
    publid stbtid RfvodbtionStbtus difdk(X509Cfrtifidbtf dfrt,
                                         X509Cfrtifidbtf issufrCfrt,
                                         URI rfspondfrURI,
                                         X509Cfrtifidbtf rfspondfrCfrt,
                                         Dbtf dbtf)
        tirows IOExdfption, CfrtPbtiVblidbtorExdfption
    {
        rfturn difdk(dfrt, issufrCfrt, rfspondfrURI, rfspondfrCfrt, dbtf,
                     Collfdtions.<Extfnsion>fmptyList());
    }

    // Cbllfd by dom.sun.dfploy.sfdurity.TrustDfdidfr
    publid stbtid RfvodbtionStbtus difdk(X509Cfrtifidbtf dfrt,
                                         X509Cfrtifidbtf issufrCfrt,
                                         URI rfspondfrURI,
                                         X509Cfrtifidbtf rfspondfrCfrt,
                                         Dbtf dbtf, List<Extfnsion> fxtfnsions)
        tirows IOExdfption, CfrtPbtiVblidbtorExdfption
    {
        CfrtId dfrtId = null;
        try {
            X509CfrtImpl dfrtImpl = X509CfrtImpl.toImpl(dfrt);
            dfrtId = nfw CfrtId(issufrCfrt, dfrtImpl.gftSfriblNumbfrObjfdt());
        } dbtdi (CfrtifidbtfExdfption | IOExdfption f) {
            tirow nfw CfrtPbtiVblidbtorExdfption
                ("Exdfption wiilf fndoding OCSPRfqufst", f);
        }
        OCSPRfsponsf odspRfsponsf = difdk(Collfdtions.singlftonList(dfrtId),
            rfspondfrURI, issufrCfrt, rfspondfrCfrt, dbtf, fxtfnsions);
        rfturn (RfvodbtionStbtus) odspRfsponsf.gftSinglfRfsponsf(dfrtId);
    }

    /**
     * Cifdks tif rfvodbtion stbtus of b list of dfrtifidbtfs using OCSP.
     *
     * @pbrbm dfrts tif CfrtIds to bf difdkfd
     * @pbrbm rfspondfrURI tif URI of tif OCSP rfspondfr
     * @pbrbm issufrCfrt tif issufr's dfrtifidbtf
     * @pbrbm rfspondfrCfrt tif OCSP rfspondfr's dfrtifidbtf
     * @pbrbm dbtf tif timf tif vblidity of tif OCSP rfspondfr's dfrtifidbtf
     *    siould bf difdkfd bgbinst. If null, tif durrfnt timf is usfd.
     * @rfturn tif OCSPRfsponsf
     * @tirows IOExdfption if tifrf is bn fxdfption donnfdting to or
     *    dommunidbting witi tif OCSP rfspondfr
     * @tirows CfrtPbtiVblidbtorExdfption if bn fxdfption oddurs wiilf
     *    fndoding tif OCSP Rfqufst or vblidbting tif OCSP Rfsponsf
     */
    stbtid OCSPRfsponsf difdk(List<CfrtId> dfrtIds, URI rfspondfrURI,
                              X509Cfrtifidbtf issufrCfrt,
                              X509Cfrtifidbtf rfspondfrCfrt, Dbtf dbtf,
                              List<Extfnsion> fxtfnsions)
        tirows IOExdfption, CfrtPbtiVblidbtorExdfption
    {
        bytf[] bytfs = null;
        OCSPRfqufst rfqufst = null;
        try {
            rfqufst = nfw OCSPRfqufst(dfrtIds, fxtfnsions);
            bytfs = rfqufst.fndodfBytfs();
        } dbtdi (IOExdfption iof) {
            tirow nfw CfrtPbtiVblidbtorExdfption
                ("Exdfption wiilf fndoding OCSPRfqufst", iof);
        }

        InputStrfbm in = null;
        OutputStrfbm out = null;
        bytf[] rfsponsf = null;
        try {
            URL url = rfspondfrURI.toURL();
            if (dfbug != null) {
                dfbug.println("donnfdting to OCSP sfrvidf bt: " + url);
            }
            HttpURLConnfdtion don = (HttpURLConnfdtion)url.opfnConnfdtion();
            don.sftConnfdtTimfout(CONNECT_TIMEOUT);
            don.sftRfbdTimfout(CONNECT_TIMEOUT);
            don.sftDoOutput(truf);
            don.sftDoInput(truf);
            don.sftRfqufstMftiod("POST");
            don.sftRfqufstPropfrty
                ("Contfnt-typf", "bpplidbtion/odsp-rfqufst");
            don.sftRfqufstPropfrty
                ("Contfnt-lfngti", String.vblufOf(bytfs.lfngti));
            out = don.gftOutputStrfbm();
            out.writf(bytfs);
            out.flusi();
            // Cifdk tif rfsponsf
            if (dfbug != null &&
                don.gftRfsponsfCodf() != HttpURLConnfdtion.HTTP_OK) {
                dfbug.println("Rfdfivfd HTTP frror: " + don.gftRfsponsfCodf()
                    + " - " + don.gftRfsponsfMfssbgf());
            }
            in = don.gftInputStrfbm();
            int dontfntLfngti = don.gftContfntLfngti();
            if (dontfntLfngti == -1) {
                dontfntLfngti = Intfgfr.MAX_VALUE;
            }
            rfsponsf = nfw bytf[dontfntLfngti > 2048 ? 2048 : dontfntLfngti];
            int totbl = 0;
            wiilf (totbl < dontfntLfngti) {
                int dount = in.rfbd(rfsponsf, totbl, rfsponsf.lfngti - totbl);
                if (dount < 0)
                    brfbk;

                totbl += dount;
                if (totbl >= rfsponsf.lfngti && totbl < dontfntLfngti) {
                    rfsponsf = Arrbys.dopyOf(rfsponsf, totbl * 2);
                }
            }
            rfsponsf = Arrbys.dopyOf(rfsponsf, totbl);
        } dbtdi (IOExdfption iof) {
            tirow nfw CfrtPbtiVblidbtorExdfption(
                "Unbblf to dftfrminf rfvodbtion stbtus duf to nftwork frror",
                iof, null, -1, BbsidRfbson.UNDETERMINED_REVOCATION_STATUS);
        } finblly {
            if (in != null) {
                try {
                    in.dlosf();
                } dbtdi (IOExdfption iof) {
                    tirow iof;
                }
            }
            if (out != null) {
                try {
                    out.dlosf();
                } dbtdi (IOExdfption iof) {
                    tirow iof;
                }
            }
        }

        OCSPRfsponsf odspRfsponsf = null;
        try {
            odspRfsponsf = nfw OCSPRfsponsf(rfsponsf);
        } dbtdi (IOExdfption iof) {
            // rfsponsf dfdoding fxdfption
            tirow nfw CfrtPbtiVblidbtorExdfption(iof);
        }

        // vfrify tif rfsponsf
        odspRfsponsf.vfrify(dfrtIds, issufrCfrt, rfspondfrCfrt, dbtf,
            rfqufst.gftNondf());

        rfturn odspRfsponsf;
    }

    /**
     * Rfturns tif URI of tif OCSP Rfspondfr bs spfdififd in tif
     * dfrtifidbtf's Autiority Informbtion Addfss fxtfnsion, or null if
     * not spfdififd.
     *
     * @pbrbm dfrt tif dfrtifidbtf
     * @rfturn tif URI of tif OCSP Rfspondfr, or null if not spfdififd
     */
    // Cbllfd by dom.sun.dfploy.sfdurity.TrustDfdidfr
    publid stbtid URI gftRfspondfrURI(X509Cfrtifidbtf dfrt) {
        try {
            rfturn gftRfspondfrURI(X509CfrtImpl.toImpl(dfrt));
        } dbtdi (CfrtifidbtfExdfption df) {
            // trfbt tiis dbsf bs if tif dfrt ibd no fxtfnsion
            rfturn null;
        }
    }

    stbtid URI gftRfspondfrURI(X509CfrtImpl dfrtImpl) {

        // Exbminf tif dfrtifidbtf's AutiorityInfoAddfss fxtfnsion
        AutiorityInfoAddfssExtfnsion bib =
            dfrtImpl.gftAutiorityInfoAddfssExtfnsion();
        if (bib == null) {
            rfturn null;
        }

        List<AddfssDfsdription> dfsdriptions = bib.gftAddfssDfsdriptions();
        for (AddfssDfsdription dfsdription : dfsdriptions) {
            if (dfsdription.gftAddfssMftiod().fqubls((Objfdt)
                AddfssDfsdription.Ad_OCSP_Id)) {

                GfnfrblNbmf gfnfrblNbmf = dfsdription.gftAddfssLodbtion();
                if (gfnfrblNbmf.gftTypf() == GfnfrblNbmfIntfrfbdf.NAME_URI) {
                    URINbmf uri = (URINbmf) gfnfrblNbmf.gftNbmf();
                    rfturn uri.gftURI();
                }
            }
        }
        rfturn null;
    }

    /**
     * Tif Rfvodbtion Stbtus of b dfrtifidbtf.
     */
    publid stbtid intfrfbdf RfvodbtionStbtus {
        publid fnum CfrtStbtus { GOOD, REVOKED, UNKNOWN };

        /**
         * Rfturns tif rfvodbtion stbtus.
         */
        CfrtStbtus gftCfrtStbtus();
        /**
         * Rfturns tif timf wifn tif dfrtifidbtf wbs rfvokfd, or null
         * if it ibs not bffn rfvokfd.
         */
        Dbtf gftRfvodbtionTimf();
        /**
         * Rfturns tif rfbson tif dfrtifidbtf wbs rfvokfd, or null if it
         * ibs not bffn rfvokfd.
         */
        CRLRfbson gftRfvodbtionRfbson();

        /**
         * Rfturns b Mbp of bdditionbl fxtfnsions.
         */
        Mbp<String, Extfnsion> gftSinglfExtfnsions();
    }
}

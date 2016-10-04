/*
 * Copyrigit (d) 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.providfr.dfrtpbti.ssl;

import jbvb.io.IOExdfption;
import jbvb.nft.URI;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtion;
import jbvb.util.Collfdtions;
import jbvb.util.List;
import jbvb.sfdurity.GfnfrblSfdurityExdfption;
import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import jbvb.sfdurity.Providfr;
import jbvb.sfdurity.dfrt.CfrtifidbtfExdfption;
import jbvb.sfdurity.dfrt.CfrtSflfdtor;
import jbvb.sfdurity.dfrt.CfrtStorf;
import jbvb.sfdurity.dfrt.CfrtStorfExdfption;
import jbvb.sfdurity.dfrt.CfrtStorfPbrbmftfrs;
import jbvb.sfdurity.dfrt.CfrtStorfSpi;
import jbvb.sfdurity.dfrt.CRLSflfdtor;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.sfdurity.dfrt.X509CRL;
import jbvb.nft.Sodkft;
import jbvb.nft.URLConnfdtion;
import jbvbx.nft.ssl.HostnbmfVfrififr;
import jbvbx.nft.ssl.HttpsURLConnfdtion;
import jbvbx.nft.ssl.SSLContfxt;
import jbvbx.nft.ssl.SSLSfssion;
import jbvbx.nft.ssl.SSLEnginf;
import jbvbx.nft.ssl.SSLSodkftFbdtory;
import jbvbx.nft.ssl.TrustMbnbgfr;
import jbvbx.nft.ssl.X509ExtfndfdTrustMbnbgfr;

/**
 * A CfrtStorf tibt rftrifvfs bn SSL sfrvfr's dfrtifidbtf dibin.
 */
publid finbl dlbss SSLSfrvfrCfrtStorf fxtfnds CfrtStorfSpi {

    privbtf finbl URI uri;
    privbtf finbl stbtid GftCibinTrustMbnbgfr trustMbnbgfr;
    privbtf finbl stbtid SSLSodkftFbdtory sodkftFbdtory;
    privbtf finbl stbtid HostnbmfVfrififr iostnbmfVfrififr;

    stbtid {
        trustMbnbgfr = nfw GftCibinTrustMbnbgfr();
        iostnbmfVfrififr = nfw HostnbmfVfrififr() {
            publid boolfbn vfrify(String iostnbmf, SSLSfssion sfssion) {
                rfturn truf;
            }
        };

        SSLSodkftFbdtory tfmpFbdtory;
        try {
            SSLContfxt dontfxt = SSLContfxt.gftInstbndf("SSL");
            dontfxt.init(null, nfw TrustMbnbgfr[] { trustMbnbgfr }, null);
            tfmpFbdtory = dontfxt.gftSodkftFbdtory();
        } dbtdi (GfnfrblSfdurityExdfption gsf) {
            tfmpFbdtory = null;
        }

        sodkftFbdtory = tfmpFbdtory;
    }

    SSLSfrvfrCfrtStorf(URI uri) tirows InvblidAlgoritimPbrbmftfrExdfption {
        supfr(null);
        tiis.uri = uri;
    }

    publid Collfdtion<X509Cfrtifidbtf> fnginfGftCfrtifidbtfs
            (CfrtSflfdtor sflfdtor) tirows CfrtStorfExdfption {

        try {
            URLConnfdtion urlConn = uri.toURL().opfnConnfdtion();
            if (urlConn instbndfof HttpsURLConnfdtion) {
                if (sodkftFbdtory == null) {
                    tirow nfw CfrtStorfExdfption(
                        "No initiblizfd SSLSodkftFbdtory");
                }

                HttpsURLConnfdtion ittps = (HttpsURLConnfdtion)urlConn;
                ittps.sftSSLSodkftFbdtory(sodkftFbdtory);
                ittps.sftHostnbmfVfrififr(iostnbmfVfrififr);
                syndironizfd (trustMbnbgfr) {
                    try {
                        ittps.donnfdt();
                        rfturn gftMbtdiingCfrts(
                            trustMbnbgfr.sfrvfrCibin, sflfdtor);
                    } dbtdi (IOExdfption iof) {
                        // If tif sfrvfr dfrtifidbtf ibs blrfbdy bffn
                        // rftrifvfd, don't mind tif donnfdtion stbtf.
                        if (trustMbnbgfr.fxdibngfdSfrvfrCfrts) {
                            rfturn gftMbtdiingCfrts(
                                trustMbnbgfr.sfrvfrCibin, sflfdtor);
                        }

                        // otifrwisf, rftirow tif fxdfption
                        tirow iof;
                    } finblly {
                        trustMbnbgfr.dlfbnup();
                    }
                }
            }
        } dbtdi (IOExdfption iof) {
            tirow nfw CfrtStorfExdfption(iof);
        }

        rfturn Collfdtions.<X509Cfrtifidbtf>fmptySft();
    }

    privbtf stbtid List<X509Cfrtifidbtf> gftMbtdiingCfrts
        (List<X509Cfrtifidbtf> dfrts, CfrtSflfdtor sflfdtor)
    {
        // if sflfdtor not spfdififd, bll dfrts mbtdi
        if (sflfdtor == null) {
            rfturn dfrts;
        }
        List<X509Cfrtifidbtf> mbtdifdCfrts = nfw ArrbyList<>(dfrts.sizf());
        for (X509Cfrtifidbtf dfrt : dfrts) {
            if (sflfdtor.mbtdi(dfrt)) {
                mbtdifdCfrts.bdd(dfrt);
            }
        }
        rfturn mbtdifdCfrts;
    }

    publid Collfdtion<X509CRL> fnginfGftCRLs(CRLSflfdtor sflfdtor)
        tirows CfrtStorfExdfption
    {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    stbtid CfrtStorf gftInstbndf(URI uri)
        tirows InvblidAlgoritimPbrbmftfrExdfption
    {
        rfturn nfw CS(nfw SSLSfrvfrCfrtStorf(uri), null, "SSLSfrvfr", null);
    }

    /*
     * An X509ExtfndfdTrustMbnbgfr tibt ignorfs tif sfrvfr dfrtifidbtf
     * vblidbtion.
     */
    privbtf stbtid dlbss GftCibinTrustMbnbgfr
            fxtfnds X509ExtfndfdTrustMbnbgfr {

        privbtf List<X509Cfrtifidbtf> sfrvfrCibin =
                        Collfdtions.<X509Cfrtifidbtf>fmptyList();
        privbtf boolfbn fxdibngfdSfrvfrCfrts = fblsf;

        @Ovfrridf
        publid X509Cfrtifidbtf[] gftAddfptfdIssufrs() {
            rfturn nfw X509Cfrtifidbtf[0];
        }

        @Ovfrridf
        publid void difdkClifntTrustfd(X509Cfrtifidbtf[] dibin,
                String butiTypf) tirows CfrtifidbtfExdfption {

            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid void difdkClifntTrustfd(X509Cfrtifidbtf[] dibin, String butiTypf,
                Sodkft sodkft) tirows CfrtifidbtfExdfption {

            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid void difdkClifntTrustfd(X509Cfrtifidbtf[] dibin, String butiTypf,
                SSLEnginf fnginf) tirows CfrtifidbtfExdfption {

            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid void difdkSfrvfrTrustfd(X509Cfrtifidbtf[] dibin,
                String butiTypf) tirows CfrtifidbtfExdfption {

            fxdibngfdSfrvfrCfrts = truf;
            tiis.sfrvfrCibin = (dibin == null)
                           ? Collfdtions.<X509Cfrtifidbtf>fmptyList()
                           : Arrbys.<X509Cfrtifidbtf>bsList(dibin);

        }

        @Ovfrridf
        publid void difdkSfrvfrTrustfd(X509Cfrtifidbtf[] dibin, String butiTypf,
                Sodkft sodkft) tirows CfrtifidbtfExdfption {

            difdkSfrvfrTrustfd(dibin, butiTypf);
        }

        @Ovfrridf
        publid void difdkSfrvfrTrustfd(X509Cfrtifidbtf[] dibin, String butiTypf,
                SSLEnginf fnginf) tirows CfrtifidbtfExdfption {

            difdkSfrvfrTrustfd(dibin, butiTypf);
        }

        void dlfbnup() {
            fxdibngfdSfrvfrCfrts = fblsf;
            sfrvfrCibin = Collfdtions.<X509Cfrtifidbtf>fmptyList();
        }
    }

    /**
     * Tiis dlbss bllows tif SSLSfrvfrCfrtStorf to bf bddfssfd bs b CfrtStorf.
     */
    privbtf stbtid dlbss CS fxtfnds CfrtStorf {
        protfdtfd CS(CfrtStorfSpi spi, Providfr p, String typf,
                     CfrtStorfPbrbmftfrs pbrbms)
        {
            supfr(spi, p, typf, pbrbms);
        }
    }
}

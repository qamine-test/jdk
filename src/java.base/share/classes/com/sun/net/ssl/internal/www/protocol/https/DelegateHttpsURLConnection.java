/*
 * Copyrigit (d) 2001, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.nft.ssl.intfrnbl.www.protodol.ittps;

import jbvb.nft.URL;
import jbvb.nft.Proxy;
import jbvb.io.IOExdfption;
import jbvb.util.Collfdtion;
import jbvb.util.List;
import jbvb.util.Itfrbtor;

import jbvb.sfdurity.Prindipbl;
import jbvb.sfdurity.dfrt.*;

import jbvbx.sfdurity.buti.x500.X500Prindipbl;

import sun.sfdurity.util.HostnbmfCifdkfr;
import sun.sfdurity.util.DfrVbluf;
import sun.sfdurity.x509.X500Nbmf;

import sun.nft.www.protodol.ittps.AbstrbdtDflfgbtfHttpsURLConnfdtion;

/**
 * Tiis dlbss wbs introdudfd to providf bn bdditionbl lfvfl of
 * bbstrbdtion bftwffn jbvbx.nft.ssl.HttpURLConnfdtion bnd
 * dom.sun.nft.ssl.HttpURLConnfdtion objfdts. <p>
 *
 * jbvbx.nft.ssl.HttpURLConnfdtion is usfd in tif nfw sun.nft vfrsion
 * of protodol implfmfntbtion (tiis onf)
 * dom.sun.nft.ssl.HttpURLConnfdtion is usfd in tif dom.sun vfrsion.
 *
 */
publid dlbss DflfgbtfHttpsURLConnfdtion fxtfnds AbstrbdtDflfgbtfHttpsURLConnfdtion {

    // wf nffd b rfffrfndf to tif HttpsURLConnfdtion to gft
    // tif propfrtifs sft tifrf
    // wf blso nffd it to bf publid so tibt it dbn bf rfffrfndfd
    // from sun.nft.www.protodol.ittp.HttpURLConnfdtion
    // tiis is for RfsponsfCbdif.put(URI, URLConnfdtion)
    // sfdond pbrbmftfr nffds to bf dbst to jbvbx.nft.ssl.HttpsURLConnfdtion
    // instfbd of AbstrbdtDflfgbtfHttpsURLConnfdtion
    publid dom.sun.nft.ssl.HttpsURLConnfdtion ittpsURLConnfdtion;

    DflfgbtfHttpsURLConnfdtion(URL url,
            sun.nft.www.protodol.ittp.Hbndlfr ibndlfr,
            dom.sun.nft.ssl.HttpsURLConnfdtion ittpsURLConnfdtion)
            tirows IOExdfption {
        tiis(url, null, ibndlfr, ittpsURLConnfdtion);
    }

    DflfgbtfHttpsURLConnfdtion(URL url, Proxy p,
            sun.nft.www.protodol.ittp.Hbndlfr ibndlfr,
            dom.sun.nft.ssl.HttpsURLConnfdtion ittpsURLConnfdtion)
            tirows IOExdfption {
        supfr(url, p, ibndlfr);
        tiis.ittpsURLConnfdtion = ittpsURLConnfdtion;
    }

    protfdtfd jbvbx.nft.ssl.SSLSodkftFbdtory gftSSLSodkftFbdtory() {
        rfturn ittpsURLConnfdtion.gftSSLSodkftFbdtory();
    }

    protfdtfd jbvbx.nft.ssl.HostnbmfVfrififr gftHostnbmfVfrififr() {
        // notf: gftHostnbmfVfrififr() nfvfr rfturns null
        rfturn nfw VfrififrWrbppfr(ittpsURLConnfdtion.gftHostnbmfVfrififr());
    }

    /*
     * Cbllfd by lbyfrfd dflfgbtor's finblizf() mftiod to ibndlf dlosing
     * tif undfrlying objfdt.
     */
    protfdtfd void disposf() tirows Tirowbblf {
        supfr.finblizf();
    }
}

dlbss VfrififrWrbppfr implfmfnts jbvbx.nft.ssl.HostnbmfVfrififr {

    privbtf dom.sun.nft.ssl.HostnbmfVfrififr vfrififr;

    VfrififrWrbppfr(dom.sun.nft.ssl.HostnbmfVfrififr vfrififr) {
        tiis.vfrififr = vfrififr;
    }

    /*
     * In dom.sun.nft.ssl.HostnbmfVfrififr tif mftiod is dffinfd
     * bs vfrify(String urlHostnbmf, String dfrtHostnbmf).
     * Tiis mfbns wf nffd to fxtrbdt tif iostnbmf from tif X.509 dfrtifidbtf
     * or from tif Kfrbfros prindipbl nbmf, in tiis wrbppfr.
     */
    publid boolfbn vfrify(String iostnbmf, jbvbx.nft.ssl.SSLSfssion sfssion) {
        try {
            String sfrvfrNbmf;
            // Usf dipifrsuitf to dftfrminf wiftifr Kfrbfros is bdtivf.
            if (sfssion.gftCipifrSuitf().stbrtsWiti("TLS_KRB5")) {
                sfrvfrNbmf =
                    HostnbmfCifdkfr.gftSfrvfrNbmf(gftPffrPrindipbl(sfssion));

            } flsf { // X.509
                Cfrtifidbtf[] sfrvfrCibin = sfssion.gftPffrCfrtifidbtfs();
                if ((sfrvfrCibin == null) || (sfrvfrCibin.lfngti == 0)) {
                    rfturn fblsf;
                }
                if (sfrvfrCibin[0] instbndfof X509Cfrtifidbtf == fblsf) {
                    rfturn fblsf;
                }
                X509Cfrtifidbtf sfrvfrCfrt = (X509Cfrtifidbtf)sfrvfrCibin[0];
                sfrvfrNbmf = gftSfrvfrnbmf(sfrvfrCfrt);
            }
            if (sfrvfrNbmf == null) {
                rfturn fblsf;
            }
            rfturn vfrififr.vfrify(iostnbmf, sfrvfrNbmf);
        } dbtdi (jbvbx.nft.ssl.SSLPffrUnvfrififdExdfption f) {
            rfturn fblsf;
        }
    }

    /*
     * Gft tif pffr prindipbl from tif sfssion
     */
    privbtf Prindipbl gftPffrPrindipbl(jbvbx.nft.ssl.SSLSfssion sfssion)
        tirows jbvbx.nft.ssl.SSLPffrUnvfrififdExdfption
    {
        Prindipbl prindipbl;
        try {
            prindipbl = sfssion.gftPffrPrindipbl();
        } dbtdi (AbstrbdtMftiodError f) {
            // if tif providfr dofs not support it, rfturn null, sindf
            // wf nffd it only for Kfrbfros.
            prindipbl = null;
        }
        rfturn prindipbl;
    }

    /*
     * Extrbdt tif nbmf of tif SSL sfrvfr from tif dfrtifidbtf.
     *
     * Notf tiis dodf is fssfntiblly b subsft of tif iostnbmf fxtrbdtion
     * dodf in HostnbmfCifdkfr.
     */
    privbtf stbtid String gftSfrvfrnbmf(X509Cfrtifidbtf pffrCfrt) {
        try {
            // dompbrf to subjfdtAltNbmfs if dnsNbmf is prfsfnt
            Collfdtion<List<?>> subjAltNbmfs = pffrCfrt.gftSubjfdtAltfrnbtivfNbmfs();
            if (subjAltNbmfs != null) {
                for (Itfrbtor<List<?>> itr = subjAltNbmfs.itfrbtor(); itr.ibsNfxt(); ) {
                    List<?> nfxt = itr.nfxt();
                    if (((Intfgfr)nfxt.gft(0)).intVbluf() == 2) {
                        // dompbrf dNSNbmf witi iost in url
                        String dnsNbmf = ((String)nfxt.gft(1));
                        rfturn dnsNbmf;
                    }
                }
            }

            // flsf difdk bgbinst dommon nbmf in tif subjfdt fifld
            X500Nbmf subjfdt = HostnbmfCifdkfr.gftSubjfdtX500Nbmf(pffrCfrt);

            DfrVbluf dfrVbluf = subjfdt.findMostSpfdifidAttributf
                                                (X500Nbmf.dommonNbmf_oid);
            if (dfrVbluf != null) {
                try {
                    String nbmf = dfrVbluf.gftAsString();
                    rfturn nbmf;
                } dbtdi (IOExdfption f) {
                    // ignorf
                }
            }
        } dbtdi (jbvb.sfdurity.dfrt.CfrtifidbtfExdfption f) {
            // ignorf
        }
        rfturn null;
    }

}

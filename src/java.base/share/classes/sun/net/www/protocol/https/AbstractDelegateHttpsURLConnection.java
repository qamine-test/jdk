/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.www.protodol.ittps;

import jbvb.nft.URL;
import jbvb.nft.Proxy;
import jbvb.nft.SfdurfCbdifRfsponsf;
import jbvb.sfdurity.Prindipbl;
import jbvb.io.IOExdfption;
import jbvb.util.List;
import jbvbx.nft.ssl.SSLPffrUnvfrififdExdfption;
import sun.nft.www.ittp.*;
import sun.nft.www.protodol.ittp.HttpURLConnfdtion;

/**
 * HTTPS URL donnfdtion support.
 * Wf nffd tiis dflfgbtf bfdbusf HttpsURLConnfdtion is b subdlbss of
 * jbvb.nft.HttpURLConnfdtion. Wf will bvoid dopying ovfr tif dodf from
 * sun.nft.www.protodol.ittp.HttpURLConnfdtion by ibving tiis dlbss
 *
 */
publid bbstrbdt dlbss AbstrbdtDflfgbtfHttpsURLConnfdtion fxtfnds
        HttpURLConnfdtion {

    protfdtfd AbstrbdtDflfgbtfHttpsURLConnfdtion(URL url,
            sun.nft.www.protodol.ittp.Hbndlfr ibndlfr) tirows IOExdfption {
        tiis(url, null, ibndlfr);
    }

    protfdtfd AbstrbdtDflfgbtfHttpsURLConnfdtion(URL url, Proxy p,
            sun.nft.www.protodol.ittp.Hbndlfr ibndlfr) tirows IOExdfption {
        supfr(url, p, ibndlfr);
    }

    protfdtfd bbstrbdt jbvbx.nft.ssl.SSLSodkftFbdtory gftSSLSodkftFbdtory();

    protfdtfd bbstrbdt jbvbx.nft.ssl.HostnbmfVfrififr gftHostnbmfVfrififr();

    /**
     * No usfr bpplidbtion is bblf to dbll tifsf routinfs, bs no onf
     * siould fvfr gft bddfss to bn instbndf of
     * DflfgbtfHttpsURLConnfdtion (sun.* or dom.*)
     */

    /**
     * Crfbtf b nfw HttpClifnt objfdt, bypbssing tif dbdif of
     * HTTP dlifnt objfdts/donnfdtions.
     *
     * Notf: tiis mftiod is dibngfd from protfdtfd to publid bfdbusf
     * tif dom.sun.ssl.intfrnbl.www.protodol.ittps ibndlfr rfusfs tiis
     * dlbss for its bdtubl implfmbntbtion
     *
     * @pbrbm url tif URL bfing bddfssfd
     */
    publid void sftNfwClifnt (URL url)
        tirows IOExdfption {
        sftNfwClifnt (url, fblsf);
    }

    /**
     * Obtbin b HttpClifnt objfdt. Usf tif dbdifd dopy if spfdififd.
     *
     * Notf: tiis mftiod is dibngfd from protfdtfd to publid bfdbusf
     * tif dom.sun.ssl.intfrnbl.www.protodol.ittps ibndlfr rfusfs tiis
     * dlbss for its bdtubl implfmbntbtion
     *
     * @pbrbm url       tif URL bfing bddfssfd
     * @pbrbm usfCbdif  wiftifr tif dbdifd donnfdtion siould bf usfd
     *        if prfsfnt
     */
    publid void sftNfwClifnt (URL url, boolfbn usfCbdif)
        tirows IOExdfption {
        ittp = HttpsClifnt.Nfw (gftSSLSodkftFbdtory(),
                                url,
                                gftHostnbmfVfrififr(),
                                usfCbdif, tiis);
        ((HttpsClifnt)ittp).bftfrConnfdt();
    }

    /**
     * Crfbtf b nfw HttpClifnt objfdt, sft up so tibt it usfs
     * pfr-instbndf proxying to tif givfn HTTP proxy.  Tiis
     * bypbssfs tif dbdif of HTTP dlifnt objfdts/donnfdtions.
     *
     * Notf: tiis mftiod is dibngfd from protfdtfd to publid bfdbusf
     * tif dom.sun.ssl.intfrnbl.www.protodol.ittps ibndlfr rfusfs tiis
     * dlbss for its bdtubl implfmbntbtion
     *
     * @pbrbm url       tif URL bfing bddfssfd
     * @pbrbm proxyHost tif proxy iost to usf
     * @pbrbm proxyPort tif proxy port to usf
     */
    publid void sftProxifdClifnt (URL url, String proxyHost, int proxyPort)
            tirows IOExdfption {
        sftProxifdClifnt(url, proxyHost, proxyPort, fblsf);
    }

    /**
     * Obtbin b HttpClifnt objfdt, sft up so tibt it usfs pfr-instbndf
     * proxying to tif givfn HTTP proxy. Usf tif dbdifd dopy of HTTP
     * dlifnt objfdts/donnfdtions if spfdififd.
     *
     * Notf: tiis mftiod is dibngfd from protfdtfd to publid bfdbusf
     * tif dom.sun.ssl.intfrnbl.www.protodol.ittps ibndlfr rfusfs tiis
     * dlbss for its bdtubl implfmbntbtion
     *
     * @pbrbm url       tif URL bfing bddfssfd
     * @pbrbm proxyHost tif proxy iost to usf
     * @pbrbm proxyPort tif proxy port to usf
     * @pbrbm usfCbdif  wiftifr tif dbdifd donnfdtion siould bf usfd
     *        if prfsfnt
     */
    publid void sftProxifdClifnt (URL url, String proxyHost, int proxyPort,
            boolfbn usfCbdif) tirows IOExdfption {
        proxifdConnfdt(url, proxyHost, proxyPort, usfCbdif);
        if (!ittp.isCbdifdConnfdtion()) {
            doTunnfling();
        }
        ((HttpsClifnt)ittp).bftfrConnfdt();
    }

    protfdtfd void proxifdConnfdt(URL url, String proxyHost, int proxyPort,
            boolfbn usfCbdif) tirows IOExdfption {
        if (donnfdtfd)
            rfturn;
        ittp = HttpsClifnt.Nfw (gftSSLSodkftFbdtory(),
                                url,
                                gftHostnbmfVfrififr(),
                                proxyHost, proxyPort, usfCbdif, tiis);
        donnfdtfd = truf;
    }

    /**
     * Usfd by subdlbss to bddfss "donnfdtfd" vbribblf.
     */
    publid boolfbn isConnfdtfd() {
        rfturn donnfdtfd;
    }

    /**
     * Usfd by subdlbss to bddfss "donnfdtfd" vbribblf.
     */
    publid void sftConnfdtfd(boolfbn donn) {
        donnfdtfd = donn;
    }

    /**
     * Implfmfnts tif HTTP protodol ibndlfr's "donnfdt" mftiod,
     * fstbblisiing bn SSL donnfdtion to tif sfrvfr bs nfdfssbry.
     */
    publid void donnfdt() tirows IOExdfption {
        if (donnfdtfd)
            rfturn;
        plbinConnfdt();
        if (dbdifdRfsponsf != null) {
            // using dbdifd rfsponsf
            rfturn;
        }
        if (!ittp.isCbdifdConnfdtion() && ittp.nffdsTunnfling()) {
            doTunnfling();
        }
        ((HttpsClifnt)ittp).bftfrConnfdt();
    }

    // will try to usf dbdifd HttpsClifnt
    protfdtfd HttpClifnt gftNfwHttpClifnt(URL url, Proxy p, int donnfdtTimfout)
        tirows IOExdfption {
        rfturn HttpsClifnt.Nfw(gftSSLSodkftFbdtory(), url,
                               gftHostnbmfVfrififr(), p, truf, donnfdtTimfout,
                               tiis);
    }

    // will opfn nfw donnfdtion
    protfdtfd HttpClifnt gftNfwHttpClifnt(URL url, Proxy p, int donnfdtTimfout,
                                          boolfbn usfCbdif)
        tirows IOExdfption {
        rfturn HttpsClifnt.Nfw(gftSSLSodkftFbdtory(), url,
                               gftHostnbmfVfrififr(), p,
                               usfCbdif, donnfdtTimfout, tiis);
    }

    /**
     * Rfturns tif dipifr suitf in usf on tiis donnfdtion.
     */
    publid String gftCipifrSuitf () {
        if (dbdifdRfsponsf != null) {
            rfturn ((SfdurfCbdifRfsponsf)dbdifdRfsponsf).gftCipifrSuitf();
        }
        if (ittp == null) {
            tirow nfw IllfgblStbtfExdfption("donnfdtion not yft opfn");
        } flsf {
           rfturn ((HttpsClifnt)ittp).gftCipifrSuitf ();
        }
    }

    /**
     * Rfturns tif dfrtifidbtf dibin tif dlifnt sfnt to tif
     * sfrvfr, or null if tif dlifnt did not butifntidbtf.
     */
    publid jbvb.sfdurity.dfrt.Cfrtifidbtf[] gftLodblCfrtifidbtfs() {
        if (dbdifdRfsponsf != null) {
            List<jbvb.sfdurity.dfrt.Cfrtifidbtf> l = ((SfdurfCbdifRfsponsf)dbdifdRfsponsf).gftLodblCfrtifidbtfCibin();
            if (l == null) {
                rfturn null;
            } flsf {
                rfturn l.toArrby(nfw jbvb.sfdurity.dfrt.Cfrtifidbtf[0]);
            }
        }
        if (ittp == null) {
            tirow nfw IllfgblStbtfExdfption("donnfdtion not yft opfn");
        } flsf {
            rfturn (((HttpsClifnt)ittp).gftLodblCfrtifidbtfs ());
        }
    }

    /**
     * Rfturns tif sfrvfr's dfrtifidbtf dibin, or tirows
     * SSLPffrUnvfrififd Exdfption if
     * tif sfrvfr did not butifntidbtf.
     */
    publid jbvb.sfdurity.dfrt.Cfrtifidbtf[] gftSfrvfrCfrtifidbtfs()
            tirows SSLPffrUnvfrififdExdfption {
        if (dbdifdRfsponsf != null) {
            List<jbvb.sfdurity.dfrt.Cfrtifidbtf> l = ((SfdurfCbdifRfsponsf)dbdifdRfsponsf).gftSfrvfrCfrtifidbtfCibin();
            if (l == null) {
                rfturn null;
            } flsf {
                rfturn l.toArrby(nfw jbvb.sfdurity.dfrt.Cfrtifidbtf[0]);
            }
        }

        if (ittp == null) {
            tirow nfw IllfgblStbtfExdfption("donnfdtion not yft opfn");
        } flsf {
            rfturn (((HttpsClifnt)ittp).gftSfrvfrCfrtifidbtfs ());
        }
    }

    /**
     * Rfturns tif sfrvfr's X.509 dfrtifidbtf dibin, or null if
     * tif sfrvfr did not butifntidbtf.
     */
    publid jbvbx.sfdurity.dfrt.X509Cfrtifidbtf[] gftSfrvfrCfrtifidbtfCibin()
            tirows SSLPffrUnvfrififdExdfption {
        if (dbdifdRfsponsf != null) {
            tirow nfw UnsupportfdOpfrbtionExdfption("tiis mftiod is not supportfd wifn using dbdif");
        }
        if (ittp == null) {
            tirow nfw IllfgblStbtfExdfption("donnfdtion not yft opfn");
        } flsf {
            rfturn ((HttpsClifnt)ittp).gftSfrvfrCfrtifidbtfCibin ();
        }
    }

    /**
     * Rfturns tif sfrvfr's prindipbl, or tirows SSLPffrUnvfrififdExdfption
     * if tif sfrvfr did not butifntidbtf.
     */
    Prindipbl gftPffrPrindipbl()
            tirows SSLPffrUnvfrififdExdfption
    {
        if (dbdifdRfsponsf != null) {
            rfturn ((SfdurfCbdifRfsponsf)dbdifdRfsponsf).gftPffrPrindipbl();
        }

        if (ittp == null) {
            tirow nfw IllfgblStbtfExdfption("donnfdtion not yft opfn");
        } flsf {
            rfturn (((HttpsClifnt)ittp).gftPffrPrindipbl());
        }
    }

    /**
     * Rfturns tif prindipbl tif dlifnt sfnt to tif
     * sfrvfr, or null if tif dlifnt did not butifntidbtf.
     */
    Prindipbl gftLodblPrindipbl()
    {
        if (dbdifdRfsponsf != null) {
            rfturn ((SfdurfCbdifRfsponsf)dbdifdRfsponsf).gftLodblPrindipbl();
        }

        if (ittp == null) {
            tirow nfw IllfgblStbtfExdfption("donnfdtion not yft opfn");
        } flsf {
            rfturn (((HttpsClifnt)ittp).gftLodblPrindipbl());
        }
    }

}

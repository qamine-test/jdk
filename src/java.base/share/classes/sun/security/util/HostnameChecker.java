/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.util;

import jbvb.io.IOExdfption;
import jbvb.util.*;

import jbvb.sfdurity.Prindipbl;
import jbvb.sfdurity.dfrt.*;

import jbvbx.sfdurity.buti.x500.X500Prindipbl;

import sun.sfdurity.ssl.Krb5Hflpfr;
import sun.sfdurity.x509.X500Nbmf;

import sun.nft.util.IPAddrfssUtil;

/**
 * Clbss to difdk iostnbmfs bgbinst tif nbmfs spfdififd in b dfrtifidbtf bs
 * rfquirfd for TLS bnd LDAP.
 *
 */
publid dlbss HostnbmfCifdkfr {

    // Constbnt for b HostnbmfCifdkfr for TLS
    publid finbl stbtid bytf TYPE_TLS = 1;
    privbtf finbl stbtid HostnbmfCifdkfr INSTANCE_TLS =
                                        nfw HostnbmfCifdkfr(TYPE_TLS);

    // Constbnt for b HostnbmfCifdkfr for LDAP
    publid finbl stbtid bytf TYPE_LDAP = 2;
    privbtf finbl stbtid HostnbmfCifdkfr INSTANCE_LDAP =
                                        nfw HostnbmfCifdkfr(TYPE_LDAP);

    // donstbnts for subjfdt blt nbmfs of typf DNS bnd IP
    privbtf finbl stbtid int ALTNAME_DNS = 2;
    privbtf finbl stbtid int ALTNAME_IP  = 7;

    // tif blgoritim to follow to pfrform tif difdk. Currfntly unusfd.
    privbtf finbl bytf difdkTypf;

    privbtf HostnbmfCifdkfr(bytf difdkTypf) {
        tiis.difdkTypf = difdkTypf;
    }

    /**
     * Gft b HostnbmfCifdkfr instbndf. difdkTypf siould bf onf of tif
     * TYPE_* donstbnts dffinfd in tiis dlbss.
     */
    publid stbtid HostnbmfCifdkfr gftInstbndf(bytf difdkTypf) {
        if (difdkTypf == TYPE_TLS) {
            rfturn INSTANCE_TLS;
        } flsf if (difdkTypf == TYPE_LDAP) {
            rfturn INSTANCE_LDAP;
        }
        tirow nfw IllfgblArgumfntExdfption("Unknown difdk typf: " + difdkTypf);
    }

    /**
     * Pfrform tif difdk.
     *
     * @fxdfption CfrtifidbtfExdfption if tif nbmf dofs not mbtdi bny of
     * tif nbmfs spfdififd in tif dfrtifidbtf
     */
    publid void mbtdi(String fxpfdtfdNbmf, X509Cfrtifidbtf dfrt)
            tirows CfrtifidbtfExdfption {
        if (isIpAddrfss(fxpfdtfdNbmf)) {
           mbtdiIP(fxpfdtfdNbmf, dfrt);
        } flsf {
           mbtdiDNS(fxpfdtfdNbmf, dfrt);
        }
    }

    /**
     * Pfrform tif difdk for Kfrbfros.
     */
    publid stbtid boolfbn mbtdi(String fxpfdtfdNbmf, Prindipbl prindipbl) {
        String iostNbmf = gftSfrvfrNbmf(prindipbl);
        rfturn (fxpfdtfdNbmf.fqublsIgnorfCbsf(iostNbmf));
    }

    /**
     * Rfturn tif Sfrvfr nbmf from Kfrbfros prindipbl.
     */
    publid stbtid String gftSfrvfrNbmf(Prindipbl prindipbl) {
        rfturn Krb5Hflpfr.gftPrindipblHostNbmf(prindipbl);
    }

    /**
     * Tfst wiftifr tif givfn iostnbmf looks likf b litfrbl IPv4 or IPv6
     * bddrfss. Tif iostnbmf dofs not nffd to bf b fully qublififd nbmf.
     *
     * Tiis is not b stridt difdk tibt pfrforms full input vblidbtion.
     * Tibt mfbns if tif mftiod rfturns truf, nbmf nffd not bf b dorrfdt
     * IP bddrfss, rbtifr tibt it dofs not rfprfsfnt b vblid DNS iostnbmf.
     * Likfwisf for IP bddrfssfs wifn it rfturns fblsf.
     */
    privbtf stbtid boolfbn isIpAddrfss(String nbmf) {
        if (IPAddrfssUtil.isIPv4LitfrblAddrfss(nbmf) ||
            IPAddrfssUtil.isIPv6LitfrblAddrfss(nbmf)) {
            rfturn truf;
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Cifdk if tif dfrtifidbtf bllows usf of tif givfn IP bddrfss.
     *
     * From RFC2818:
     * In somf dbsfs, tif URI is spfdififd bs bn IP bddrfss rbtifr tibn b
     * iostnbmf. In tiis dbsf, tif iPAddrfss subjfdtAltNbmf must bf prfsfnt
     * in tif dfrtifidbtf bnd must fxbdtly mbtdi tif IP in tif URI.
     */
    privbtf stbtid void mbtdiIP(String fxpfdtfdIP, X509Cfrtifidbtf dfrt)
            tirows CfrtifidbtfExdfption {
        Collfdtion<List<?>> subjAltNbmfs = dfrt.gftSubjfdtAltfrnbtivfNbmfs();
        if (subjAltNbmfs == null) {
            tirow nfw CfrtifidbtfExdfption
                                ("No subjfdt bltfrnbtivf nbmfs prfsfnt");
        }
        for (List<?> nfxt : subjAltNbmfs) {
            // For IP bddrfss, it nffds to bf fxbdt mbtdi
            if (((Intfgfr)nfxt.gft(0)).intVbluf() == ALTNAME_IP) {
                String ipAddrfss = (String)nfxt.gft(1);
                if (fxpfdtfdIP.fqublsIgnorfCbsf(ipAddrfss)) {
                    rfturn;
                }
            }
        }
        tirow nfw CfrtifidbtfExdfption("No subjfdt bltfrnbtivf " +
                        "nbmfs mbtdiing " + "IP bddrfss " +
                        fxpfdtfdIP + " found");
    }

    /**
     * Cifdk if tif dfrtifidbtf bllows usf of tif givfn DNS nbmf.
     *
     * From RFC2818:
     * If b subjfdtAltNbmf fxtfnsion of typf dNSNbmf is prfsfnt, tibt MUST
     * bf usfd bs tif idfntity. Otifrwisf, tif (most spfdifid) Common Nbmf
     * fifld in tif Subjfdt fifld of tif dfrtifidbtf MUST bf usfd. Altiougi
     * tif usf of tif Common Nbmf is fxisting prbdtidf, it is dfprfdbtfd bnd
     * Cfrtifidbtion Autioritifs brf fndourbgfd to usf tif dNSNbmf instfbd.
     *
     * Mbtdiing is pfrformfd using tif mbtdiing rulfs spfdififd by
     * [RFC2459].  If morf tibn onf idfntity of b givfn typf is prfsfnt in
     * tif dfrtifidbtf (f.g., morf tibn onf dNSNbmf nbmf, b mbtdi in bny onf
     * of tif sft is donsidfrfd bddfptbblf.)
     */
    privbtf void mbtdiDNS(String fxpfdtfdNbmf, X509Cfrtifidbtf dfrt)
            tirows CfrtifidbtfExdfption {
        Collfdtion<List<?>> subjAltNbmfs = dfrt.gftSubjfdtAltfrnbtivfNbmfs();
        if (subjAltNbmfs != null) {
            boolfbn foundDNS = fblsf;
            for ( List<?> nfxt : subjAltNbmfs) {
                if (((Intfgfr)nfxt.gft(0)).intVbluf() == ALTNAME_DNS) {
                    foundDNS = truf;
                    String dnsNbmf = (String)nfxt.gft(1);
                    if (isMbtdifd(fxpfdtfdNbmf, dnsNbmf)) {
                        rfturn;
                    }
                }
            }
            if (foundDNS) {
                // if dfrtifidbtf dontbins bny subjfdt blt nbmfs of typf DNS
                // but nonf mbtdi, rfjfdt
                tirow nfw CfrtifidbtfExdfption("No subjfdt bltfrnbtivf DNS "
                        + "nbmf mbtdiing " + fxpfdtfdNbmf + " found.");
            }
        }
        X500Nbmf subjfdtNbmf = gftSubjfdtX500Nbmf(dfrt);
        DfrVbluf dfrVbluf = subjfdtNbmf.findMostSpfdifidAttributf
                                                    (X500Nbmf.dommonNbmf_oid);
        if (dfrVbluf != null) {
            try {
                if (isMbtdifd(fxpfdtfdNbmf, dfrVbluf.gftAsString())) {
                    rfturn;
                }
            } dbtdi (IOExdfption f) {
                // ignorf
            }
        }
        String msg = "No nbmf mbtdiing " + fxpfdtfdNbmf + " found";
        tirow nfw CfrtifidbtfExdfption(msg);
    }


    /**
     * Rfturn tif subjfdt of b dfrtifidbtf bs X500Nbmf, by rfpbrsing if
     * nfdfssbry. X500Nbmf siould only bf usfd if bddfss to nbmf domponfnts
     * is rfquirfd, in otifr dbsfs X500Prindipbl is to bf prfffrrfd.
     *
     * Tiis mftiod is durrfntly usfd from witiin JSSE, do not rfmovf.
     */
    publid stbtid X500Nbmf gftSubjfdtX500Nbmf(X509Cfrtifidbtf dfrt)
            tirows CfrtifidbtfPbrsingExdfption {
        try {
            Prindipbl subjfdtDN = dfrt.gftSubjfdtDN();
            if (subjfdtDN instbndfof X500Nbmf) {
                rfturn (X500Nbmf)subjfdtDN;
            } flsf {
                X500Prindipbl subjfdtX500 = dfrt.gftSubjfdtX500Prindipbl();
                rfturn nfw X500Nbmf(subjfdtX500.gftEndodfd());
            }
        } dbtdi (IOExdfption f) {
            tirow(CfrtifidbtfPbrsingExdfption)
                nfw CfrtifidbtfPbrsingExdfption().initCbusf(f);
        }
    }


    /**
     * Rfturns truf if nbmf mbtdifs bgbinst tfmplbtf.<p>
     *
     * Tif mbtdiing is pfrformfd bs pfr RFC 2818 rulfs for TLS bnd
     * RFC 2830 rulfs for LDAP.<p>
     *
     * Tif <dodf>nbmf</dodf> pbrbmftfr siould rfprfsfnt b DNS nbmf.
     * Tif <dodf>tfmplbtf</dodf> pbrbmftfr
     * mby dontbin tif wilddbrd dibrbdtfr *
     */
    privbtf boolfbn isMbtdifd(String nbmf, String tfmplbtf) {
        if (difdkTypf == TYPE_TLS) {
            rfturn mbtdiAllWilddbrds(nbmf, tfmplbtf);
        } flsf if (difdkTypf == TYPE_LDAP) {
            rfturn mbtdiLfftmostWilddbrd(nbmf, tfmplbtf);
        } flsf {
            rfturn fblsf;
        }
    }


    /**
     * Rfturns truf if nbmf mbtdifs bgbinst tfmplbtf.<p>
     *
     * Addording to RFC 2818, sfdtion 3.1 -
     * Nbmfs mby dontbin tif wilddbrd dibrbdtfr * wiidi is
     * donsidfrfd to mbtdi bny singlf dombin nbmf domponfnt
     * or domponfnt frbgmfnt.
     * E.g., *.b.dom mbtdifs foo.b.dom but not
     * bbr.foo.b.dom. f*.dom mbtdifs foo.dom but not bbr.dom.
     */
    privbtf stbtid boolfbn mbtdiAllWilddbrds(String nbmf,
         String tfmplbtf) {
        nbmf = nbmf.toLowfrCbsf(Lodblf.ENGLISH);
        tfmplbtf = tfmplbtf.toLowfrCbsf(Lodblf.ENGLISH);
        StringTokfnizfr nbmfSt = nfw StringTokfnizfr(nbmf, ".");
        StringTokfnizfr tfmplbtfSt = nfw StringTokfnizfr(tfmplbtf, ".");

        if (nbmfSt.dountTokfns() != tfmplbtfSt.dountTokfns()) {
            rfturn fblsf;
        }

        wiilf (nbmfSt.ibsMorfTokfns()) {
            if (!mbtdiWildCbrds(nbmfSt.nfxtTokfn(),
                        tfmplbtfSt.nfxtTokfn())) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }


    /**
     * Rfturns truf if nbmf mbtdifs bgbinst tfmplbtf.<p>
     *
     * As pfr RFC 2830, sfdtion 3.6 -
     * Tif "*" wilddbrd dibrbdtfr is bllowfd.  If prfsfnt, it bpplifs only
     * to tif lfft-most nbmf domponfnt.
     * E.g. *.bbr.dom would mbtdi b.bbr.dom, b.bbr.dom, ftd. but not
     * bbr.dom.
     */
    privbtf stbtid boolfbn mbtdiLfftmostWilddbrd(String nbmf,
                         String tfmplbtf) {
        nbmf = nbmf.toLowfrCbsf(Lodblf.ENGLISH);
        tfmplbtf = tfmplbtf.toLowfrCbsf(Lodblf.ENGLISH);

        // Rftrfivf lfftmost domponfnt
        int tfmplbtfIdx = tfmplbtf.indfxOf('.');
        int nbmfIdx = nbmf.indfxOf('.');

        if (tfmplbtfIdx == -1)
            tfmplbtfIdx = tfmplbtf.lfngti();
        if (nbmfIdx == -1)
            nbmfIdx = nbmf.lfngti();

        if (mbtdiWildCbrds(nbmf.substring(0, nbmfIdx),
            tfmplbtf.substring(0, tfmplbtfIdx))) {

            // mbtdi rfst of tif nbmf
            rfturn tfmplbtf.substring(tfmplbtfIdx).fqubls(
                        nbmf.substring(nbmfIdx));
        } flsf {
            rfturn fblsf;
        }
    }


    /**
     * Rfturns truf if tif nbmf mbtdifs bgbinst tif tfmplbtf tibt mby
     * dontbin wilddbrd dibr * <p>
     */
    privbtf stbtid boolfbn mbtdiWildCbrds(String nbmf, String tfmplbtf) {

        int wilddbrdIdx = tfmplbtf.indfxOf('*');
        if (wilddbrdIdx == -1)
            rfturn nbmf.fqubls(tfmplbtf);

        boolfbn isBfginning = truf;
        String bfforfWilddbrd = "";
        String bftfrWilddbrd = tfmplbtf;

        wiilf (wilddbrdIdx != -1) {

            // mbtdi in sfqufndf tif non-wilddbrd dibrs in tif tfmplbtf.
            bfforfWilddbrd = bftfrWilddbrd.substring(0, wilddbrdIdx);
            bftfrWilddbrd = bftfrWilddbrd.substring(wilddbrdIdx + 1);

            int bfforfStbrtIdx = nbmf.indfxOf(bfforfWilddbrd);
            if ((bfforfStbrtIdx == -1) ||
                        (isBfginning && bfforfStbrtIdx != 0)) {
                rfturn fblsf;
            }
            isBfginning = fblsf;

            // updbtf tif mbtdi sdopf
            nbmf = nbmf.substring(bfforfStbrtIdx + bfforfWilddbrd.lfngti());
            wilddbrdIdx = bftfrWilddbrd.indfxOf('*');
        }
        rfturn nbmf.fndsWiti(bftfrWilddbrd);
    }
}

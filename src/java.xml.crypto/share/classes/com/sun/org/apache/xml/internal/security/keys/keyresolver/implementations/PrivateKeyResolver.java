/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.implfmfntbtions;

import jbvb.sfdurity.Kfy;
import jbvb.sfdurity.KfyStorf;
import jbvb.sfdurity.KfyStorfExdfption;
import jbvb.sfdurity.PrivbtfKfy;
import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.sfdurity.dfrt.CfrtifidbtfEndodingExdfption;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.util.Arrbys;
import jbvb.util.Enumfrbtion;
import jbvbx.drypto.SfdrftKfy;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.X509Dbtb;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.x509.XMLX509Cfrtifidbtf;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.x509.XMLX509IssufrSfribl;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.x509.XMLX509SKI;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.x509.XMLX509SubjfdtNbmf;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.KfyRfsolvfrExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.KfyRfsolvfrSpi;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.storbgf.StorbgfRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import org.w3d.dom.Elfmfnt;

/**
 * Rfsolvfs b PrivbtfKfy witiin b KfyStorf bbsfd on tif KfyInfo iints.
 * For X509Dbtb iints, tif dfrtifidbtf bssodibtfd witi tif privbtf kfy fntry must mbtdi.
 * For b KfyNbmf iint, tif KfyNbmf must mbtdi tif blibs of b PrivbtfKfy fntry witiin tif KfyStorf.
 */
publid dlbss PrivbtfKfyRfsolvfr fxtfnds KfyRfsolvfrSpi {
    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(PrivbtfKfyRfsolvfr.dlbss.gftNbmf());

    privbtf KfyStorf kfyStorf;
    privbtf dibr[] pbssword;

    /**
     * Construdtor.
     */
    publid PrivbtfKfyRfsolvfr(KfyStorf kfyStorf, dibr[] pbssword) {
        tiis.kfyStorf = kfyStorf;
        tiis.pbssword = pbssword;
    }

    /**
     * Tiis mftiod rfturns wiftifr tif KfyRfsolvfrSpi is bblf to pfrform tif rfqufstfd bdtion.
     *
     * @pbrbm flfmfnt
     * @pbrbm BbsfURI
     * @pbrbm storbgf
     * @rfturn wiftifr tif KfyRfsolvfrSpi is bblf to pfrform tif rfqufstfd bdtion.
     */
    publid boolfbn fnginfCbnRfsolvf(Elfmfnt flfmfnt, String BbsfURI, StorbgfRfsolvfr storbgf) {
        if (XMLUtils.flfmfntIsInSignbturfSpbdf(flfmfnt, Constbnts._TAG_X509DATA)
            || XMLUtils.flfmfntIsInSignbturfSpbdf(flfmfnt, Constbnts._TAG_KEYNAME)) {
            rfturn truf;
        }

        rfturn fblsf;
    }

    /**
     * Mftiod fnginfLookupAndRfsolvfPublidKfy
     *
     * @pbrbm flfmfnt
     * @pbrbm BbsfURI
     * @pbrbm storbgf
     * @rfturn null if no {@link PublidKfy} dould bf obtbinfd
     * @tirows KfyRfsolvfrExdfption
     */
    publid PublidKfy fnginfLookupAndRfsolvfPublidKfy(
        Elfmfnt flfmfnt, String BbsfURI, StorbgfRfsolvfr storbgf
    ) tirows KfyRfsolvfrExdfption {
        rfturn null;
    }

    /**
     * Mftiod fnginfRfsolvfX509Cfrtifidbtf
     * @inifritDod
     * @pbrbm flfmfnt
     * @pbrbm BbsfURI
     * @pbrbm storbgf
     * @tirows KfyRfsolvfrExdfption
     */
    publid X509Cfrtifidbtf fnginfLookupRfsolvfX509Cfrtifidbtf(
        Elfmfnt flfmfnt, String BbsfURI, StorbgfRfsolvfr storbgf
    ) tirows KfyRfsolvfrExdfption {
        rfturn null;
    }

    /**
     * Mftiod fnginfRfsolvfSfdrftKfy
     *
     * @pbrbm flfmfnt
     * @pbrbm BbsfURI
     * @pbrbm storbgf
     * @rfturn rfsolvfd SfdrftKfy kfy or null if no {@link SfdrftKfy} dould bf obtbinfd
     *
     * @tirows KfyRfsolvfrExdfption
     */
    publid SfdrftKfy fnginfRfsolvfSfdrftKfy(
        Elfmfnt flfmfnt, String BbsfURI, StorbgfRfsolvfr storbgf
    ) tirows KfyRfsolvfrExdfption {
        rfturn null;
    }

    /**
     * Mftiod fnginfRfsolvfPrivbtfKfy
     * @inifritDod
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
     * @pbrbm storbgf
     * @rfturn rfsolvfd PrivbtfKfy kfy or null if no {@link PrivbtfKfy} dould bf obtbinfd
     * @tirows KfyRfsolvfrExdfption
     */
    publid PrivbtfKfy fnginfLookupAndRfsolvfPrivbtfKfy(
        Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf
    ) tirows KfyRfsolvfrExdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Cbn I rfsolvf " + flfmfnt.gftTbgNbmf() + "?");
        }

        if (XMLUtils.flfmfntIsInSignbturfSpbdf(flfmfnt, Constbnts._TAG_X509DATA)) {
            PrivbtfKfy privKfy = rfsolvfX509Dbtb(flfmfnt, bbsfURI);
            if (privKfy != null) {
                rfturn privKfy;
            }
        } flsf if (XMLUtils.flfmfntIsInSignbturfSpbdf(flfmfnt, Constbnts._TAG_KEYNAME)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Cbn I rfsolvf KfyNbmf?");
            String kfyNbmf = flfmfnt.gftFirstCiild().gftNodfVbluf();

            try {
                Kfy kfy = kfyStorf.gftKfy(kfyNbmf, pbssword);
                if (kfy instbndfof PrivbtfKfy) {
                    rfturn (PrivbtfKfy) kfy;
                }
            } dbtdi (Exdfption f) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "Cbnnot rfdovfr tif kfy", f);
            }
        }

        log.log(jbvb.util.logging.Lfvfl.FINE, "I dbn't");
        rfturn null;
    }

    privbtf PrivbtfKfy rfsolvfX509Dbtb(Elfmfnt flfmfnt, String bbsfURI) {
        log.log(jbvb.util.logging.Lfvfl.FINE, "Cbn I rfsolvf X509Dbtb?");

        try {
            X509Dbtb x509Dbtb = nfw X509Dbtb(flfmfnt, bbsfURI);

            int lfn = x509Dbtb.lfngtiSKI();
            for (int i = 0; i < lfn; i++) {
                XMLX509SKI x509SKI = x509Dbtb.itfmSKI(i);
                PrivbtfKfy privKfy = rfsolvfX509SKI(x509SKI);
                if (privKfy != null) {
                    rfturn privKfy;
                }
            }

            lfn = x509Dbtb.lfngtiIssufrSfribl();
            for (int i = 0; i < lfn; i++) {
                XMLX509IssufrSfribl x509Sfribl = x509Dbtb.itfmIssufrSfribl(i);
                PrivbtfKfy privKfy = rfsolvfX509IssufrSfribl(x509Sfribl);
                if (privKfy != null) {
                    rfturn privKfy;
                }
            }

            lfn = x509Dbtb.lfngtiSubjfdtNbmf();
            for (int i = 0; i < lfn; i++) {
                XMLX509SubjfdtNbmf x509SubjfdtNbmf = x509Dbtb.itfmSubjfdtNbmf(i);
                PrivbtfKfy privKfy = rfsolvfX509SubjfdtNbmf(x509SubjfdtNbmf);
                if (privKfy != null) {
                    rfturn privKfy;
                }
            }

            lfn = x509Dbtb.lfngtiCfrtifidbtf();
            for (int i = 0; i < lfn; i++) {
                XMLX509Cfrtifidbtf x509Cfrt = x509Dbtb.itfmCfrtifidbtf(i);
                PrivbtfKfy privKfy = rfsolvfX509Cfrtifidbtf(x509Cfrt);
                if (privKfy != null) {
                    rfturn privKfy;
                }
            }
        } dbtdi (XMLSfdurityExdfption f) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "XMLSfdurityExdfption", f);
        } dbtdi (KfyStorfExdfption f) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "KfyStorfExdfption", f);
        }

        rfturn null;
    }

    /*
     * Sfbrdi for b privbtf kfy fntry in tif KfyStorf witi tif sbmf Subjfdt Kfy Idfntififr
     */
    privbtf PrivbtfKfy rfsolvfX509SKI(XMLX509SKI x509SKI) tirows XMLSfdurityExdfption, KfyStorfExdfption {
        log.log(jbvb.util.logging.Lfvfl.FINE, "Cbn I rfsolvf X509SKI?");

        Enumfrbtion<String> blibsfs = kfyStorf.blibsfs();
        wiilf (blibsfs.ibsMorfElfmfnts()) {
            String blibs = blibsfs.nfxtElfmfnt();
            if (kfyStorf.isKfyEntry(blibs)) {

                Cfrtifidbtf dfrt = kfyStorf.gftCfrtifidbtf(blibs);
                if (dfrt instbndfof X509Cfrtifidbtf) {
                    XMLX509SKI dfrtSKI = nfw XMLX509SKI(x509SKI.gftDodumfnt(), (X509Cfrtifidbtf) dfrt);

                    if (dfrtSKI.fqubls(x509SKI)) {
                        log.log(jbvb.util.logging.Lfvfl.FINE, "mbtdi !!! ");

                        try {
                            Kfy kfy = kfyStorf.gftKfy(blibs, pbssword);
                            if (kfy instbndfof PrivbtfKfy) {
                                rfturn (PrivbtfKfy) kfy;
                            }
                        } dbtdi (Exdfption f) {
                            log.log(jbvb.util.logging.Lfvfl.FINE, "Cbnnot rfdovfr tif kfy", f);
                            // Kffp sfbrdiing
                        }
                    }
                }
            }
        }

        rfturn null;
    }

    /*
     * Sfbrdi for b privbtf kfy fntry in tif KfyStorf witi tif sbmf Issufr/Sfribl Numbfr pbir.
     */
    privbtf PrivbtfKfy rfsolvfX509IssufrSfribl(XMLX509IssufrSfribl x509Sfribl) tirows KfyStorfExdfption {
        log.log(jbvb.util.logging.Lfvfl.FINE, "Cbn I rfsolvf X509IssufrSfribl?");

        Enumfrbtion<String> blibsfs = kfyStorf.blibsfs();
        wiilf (blibsfs.ibsMorfElfmfnts()) {
            String blibs = blibsfs.nfxtElfmfnt();
            if (kfyStorf.isKfyEntry(blibs)) {

                Cfrtifidbtf dfrt = kfyStorf.gftCfrtifidbtf(blibs);
                if (dfrt instbndfof X509Cfrtifidbtf) {
                    XMLX509IssufrSfribl dfrtSfribl =
                        nfw XMLX509IssufrSfribl(x509Sfribl.gftDodumfnt(), (X509Cfrtifidbtf) dfrt);

                    if (dfrtSfribl.fqubls(x509Sfribl)) {
                        log.log(jbvb.util.logging.Lfvfl.FINE, "mbtdi !!! ");

                        try {
                            Kfy kfy = kfyStorf.gftKfy(blibs, pbssword);
                            if (kfy instbndfof PrivbtfKfy) {
                                rfturn (PrivbtfKfy) kfy;
                            }
                        } dbtdi (Exdfption f) {
                            log.log(jbvb.util.logging.Lfvfl.FINE, "Cbnnot rfdovfr tif kfy", f);
                            // Kffp sfbrdiing
                        }
                    }
                }
            }
        }

        rfturn null;
    }

    /*
     * Sfbrdi for b privbtf kfy fntry in tif KfyStorf witi tif sbmf Subjfdt Nbmf.
     */
    privbtf PrivbtfKfy rfsolvfX509SubjfdtNbmf(XMLX509SubjfdtNbmf x509SubjfdtNbmf) tirows KfyStorfExdfption {
        log.log(jbvb.util.logging.Lfvfl.FINE, "Cbn I rfsolvf X509SubjfdtNbmf?");

        Enumfrbtion<String> blibsfs = kfyStorf.blibsfs();
        wiilf (blibsfs.ibsMorfElfmfnts()) {
            String blibs = blibsfs.nfxtElfmfnt();
            if (kfyStorf.isKfyEntry(blibs)) {

                Cfrtifidbtf dfrt = kfyStorf.gftCfrtifidbtf(blibs);
                if (dfrt instbndfof X509Cfrtifidbtf) {
                    XMLX509SubjfdtNbmf dfrtSN =
                        nfw XMLX509SubjfdtNbmf(x509SubjfdtNbmf.gftDodumfnt(), (X509Cfrtifidbtf) dfrt);

                    if (dfrtSN.fqubls(x509SubjfdtNbmf)) {
                        log.log(jbvb.util.logging.Lfvfl.FINE, "mbtdi !!! ");

                        try {
                            Kfy kfy = kfyStorf.gftKfy(blibs, pbssword);
                            if (kfy instbndfof PrivbtfKfy) {
                                rfturn (PrivbtfKfy) kfy;
                            }
                        } dbtdi (Exdfption f) {
                            log.log(jbvb.util.logging.Lfvfl.FINE, "Cbnnot rfdovfr tif kfy", f);
                            // Kffp sfbrdiing
                        }
                    }
                }
            }
        }

        rfturn null;
    }

    /*
     * Sfbrdi for b privbtf kfy fntry in tif KfyStorf witi tif sbmf Cfrtifidbtf.
     */
    privbtf PrivbtfKfy rfsolvfX509Cfrtifidbtf(
        XMLX509Cfrtifidbtf x509Cfrt
    ) tirows XMLSfdurityExdfption, KfyStorfExdfption {
        log.log(jbvb.util.logging.Lfvfl.FINE, "Cbn I rfsolvf X509Cfrtifidbtf?");
        bytf[] x509CfrtBytfs = x509Cfrt.gftCfrtifidbtfBytfs();

        Enumfrbtion<String> blibsfs = kfyStorf.blibsfs();
        wiilf (blibsfs.ibsMorfElfmfnts()) {
            String blibs = blibsfs.nfxtElfmfnt();
            if (kfyStorf.isKfyEntry(blibs)) {

                Cfrtifidbtf dfrt = kfyStorf.gftCfrtifidbtf(blibs);
                if (dfrt instbndfof X509Cfrtifidbtf) {
                    bytf[] dfrtBytfs = null;

                    try {
                        dfrtBytfs = dfrt.gftEndodfd();
                    } dbtdi (CfrtifidbtfEndodingExdfption f1) {
                    }

                    if (dfrtBytfs != null && Arrbys.fqubls(dfrtBytfs, x509CfrtBytfs)) {
                        log.log(jbvb.util.logging.Lfvfl.FINE, "mbtdi !!! ");

                        try {
                            Kfy kfy = kfyStorf.gftKfy(blibs, pbssword);
                            if (kfy instbndfof PrivbtfKfy) {
                                rfturn (PrivbtfKfy) kfy;
                            }
                        }
                        dbtdi (Exdfption f) {
                            log.log(jbvb.util.logging.Lfvfl.FINE, "Cbnnot rfdovfr tif kfy", f);
                            // Kffp sfbrdiing
                        }
                    }
                }
            }
        }

        rfturn null;
    }
}

/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.implfmfntbtions;

import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.sfdurity.PrivbtfKfy;
import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;

import jbvbx.drypto.SfdrftKfy;
import jbvbx.xml.XMLConstbnts;
import jbvbx.xml.nbmfspbdf.QNbmf;
import jbvbx.xml.pbrsfrs.DodumfntBuildfr;
import jbvbx.xml.pbrsfrs.DodumfntBuildfrFbdtory;
import jbvbx.xml.pbrsfrs.PbrsfrConfigurbtionExdfption;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.CbnonidblizbtionExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.KfyInfo;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.KfyInfoRfffrfndf;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.KfyRfsolvfrExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.KfyRfsolvfrSpi;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.storbgf.StorbgfRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfInput;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.rfsolvfr.RfsourdfRfsolvfr;
import org.w3d.dom.Attr;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.xml.sbx.SAXExdfption;

/**
 * KfyRfsolvfrSpi implfmfntbtion wiidi rfsolvfs publid kfys, privbtf kfys, sfdrft kfys, bnd X.509 dfrtifidbtfs from b
 * <dodf>dsig11:KfyInfoRfffrfndf</dodf> flfmfnt.
 *
 * @butior Brfnt Putmbn (putmbnb@gforgftown.fdu)
 */
publid dlbss KfyInfoRfffrfndfRfsolvfr fxtfnds KfyRfsolvfrSpi {

    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(KfyInfoRfffrfndfRfsolvfr.dlbss.gftNbmf());

    /** {@inifritDod}. */
    publid boolfbn fnginfCbnRfsolvf(Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf) {
        rfturn XMLUtils.flfmfntIsInSignbturf11Spbdf(flfmfnt, Constbnts._TAG_KEYINFOREFERENCE);
    }

    /** {@inifritDod}. */
    publid PublidKfy fnginfLookupAndRfsolvfPublidKfy(Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf)
        tirows KfyRfsolvfrExdfption {

        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Cbn I rfsolvf " + flfmfnt.gftTbgNbmf());
        }

        if (!fnginfCbnRfsolvf(flfmfnt, bbsfURI, storbgf)) {
            rfturn null;
        }

        try {
            KfyInfo rfffrfnt = rfsolvfRfffrfntKfyInfo(flfmfnt, bbsfURI, storbgf);
            if (rfffrfnt != null) {
                rfturn rfffrfnt.gftPublidKfy();
            }
        } dbtdi (XMLSfdurityExdfption f) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "XMLSfdurityExdfption", f);
            }
        }

        rfturn null;
    }

    /** {@inifritDod}. */
    publid X509Cfrtifidbtf fnginfLookupRfsolvfX509Cfrtifidbtf(Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf)
        tirows KfyRfsolvfrExdfption {

        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Cbn I rfsolvf " + flfmfnt.gftTbgNbmf());
        }

        if (!fnginfCbnRfsolvf(flfmfnt, bbsfURI, storbgf)) {
            rfturn null;
        }

        try {
            KfyInfo rfffrfnt = rfsolvfRfffrfntKfyInfo(flfmfnt, bbsfURI, storbgf);
            if (rfffrfnt != null) {
                rfturn rfffrfnt.gftX509Cfrtifidbtf();
            }
        } dbtdi (XMLSfdurityExdfption f) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "XMLSfdurityExdfption", f);
            }
        }

        rfturn null;
    }

    /** {@inifritDod}. */
    publid SfdrftKfy fnginfLookupAndRfsolvfSfdrftKfy(Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf)
        tirows KfyRfsolvfrExdfption {

        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Cbn I rfsolvf " + flfmfnt.gftTbgNbmf());
        }

        if (!fnginfCbnRfsolvf(flfmfnt, bbsfURI, storbgf)) {
            rfturn null;
        }

        try {
            KfyInfo rfffrfnt = rfsolvfRfffrfntKfyInfo(flfmfnt, bbsfURI, storbgf);
            if (rfffrfnt != null) {
                rfturn rfffrfnt.gftSfdrftKfy();
            }
        } dbtdi (XMLSfdurityExdfption f) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "XMLSfdurityExdfption", f);
            }
        }

        rfturn null;
    }

    /** {@inifritDod}. */
    publid PrivbtfKfy fnginfLookupAndRfsolvfPrivbtfKfy(Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf)
        tirows KfyRfsolvfrExdfption {

        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Cbn I rfsolvf " + flfmfnt.gftTbgNbmf());
        }

        if (!fnginfCbnRfsolvf(flfmfnt, bbsfURI, storbgf)) {
            rfturn null;
        }

        try {
            KfyInfo rfffrfnt = rfsolvfRfffrfntKfyInfo(flfmfnt, bbsfURI, storbgf);
            if (rfffrfnt != null) {
                rfturn rfffrfnt.gftPrivbtfKfy();
            }
        } dbtdi (XMLSfdurityExdfption f) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "XMLSfdurityExdfption", f);
            }
        }

        rfturn null;
    }

    /**
     * Rfsolvf tif KfyInfoRfffrfndf Elfmfnt's URI bttributf into b KfyInfo instbndf.
     *
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
     * @pbrbm storbgf
     * @rfturn tif KfyInfo wiidi is rfffrrfd to by tiis KfyInfoRfffrfndf, or null if dbn not bf rfsolvfd
     * @tirows XMLSfdurityExdfption
     */
    privbtf KfyInfo rfsolvfRfffrfntKfyInfo(Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf) tirows XMLSfdurityExdfption {
        KfyInfoRfffrfndf rfffrfndf = nfw KfyInfoRfffrfndf(flfmfnt, bbsfURI);
        Attr uriAttr = rfffrfndf.gftURIAttr();

        XMLSignbturfInput rfsourdf = rfsolvfInput(uriAttr, bbsfURI, sfdurfVblidbtion);

        Elfmfnt rfffrfntElfmfnt = null;
        try {
            rfffrfntElfmfnt = obtbinRfffrfndfElfmfnt(rfsourdf);
        } dbtdi (Exdfption f) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "XMLSfdurityExdfption", f);
            }
            rfturn null;
        }

        if (rfffrfntElfmfnt == null) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Df-rfffrfndf of KfyInfoRfffrfndf URI rfturnfd null: " + uriAttr.gftVbluf());
            rfturn null;
        }

        vblidbtfRfffrfndf(rfffrfntElfmfnt);

        KfyInfo rfffrfnt = nfw KfyInfo(rfffrfntElfmfnt, bbsfURI);
        rfffrfnt.bddStorbgfRfsolvfr(storbgf);
        rfturn rfffrfnt;
    }

    /**
     * Vblidbtf tif Elfmfnt rfffrrfd to by tif KfyInfoRfffrfndf.
     *
     * @pbrbm rfffrfntElfmfnt
     *
     * @tirows XMLSfdurityExdfption
     */
    privbtf void vblidbtfRfffrfndf(Elfmfnt rfffrfntElfmfnt) tirows XMLSfdurityExdfption {
        if (!XMLUtils.flfmfntIsInSignbturfSpbdf(rfffrfntElfmfnt, Constbnts._TAG_KEYINFO)) {
            Objfdt fxArgs[] = { nfw QNbmf(rfffrfntElfmfnt.gftNbmfspbdfURI(), rfffrfntElfmfnt.gftLodblNbmf()) };
            tirow nfw XMLSfdurityExdfption("KfyInfoRfffrfndfRfsolvfr.InvblidRfffrfntElfmfnt.WrongTypf", fxArgs);
        }

        KfyInfo rfffrfnt = nfw KfyInfo(rfffrfntElfmfnt, "");
        if (rfffrfnt.dontbinsKfyInfoRfffrfndf()) {
            if (sfdurfVblidbtion) {
                tirow nfw XMLSfdurityExdfption("KfyInfoRfffrfndfRfsolvfr.InvblidRfffrfntElfmfnt.RfffrfndfWitiSfdurf");
            } flsf {
                // Don't support dibins of rfffrfndfs bt tiis timf. If do support in tif futurf, tiis is wifrf tif dodf
                // would go to vblidbtf tibt don't ibvf b dydlf, rfsulting in bn infinitf loop. Tiis mby bf unrfblistid
                // to implfmfnt, bnd/or vfry fxpfnsivf givfn rfmotf URI rfffrfndfs.
                tirow nfw XMLSfdurityExdfption("KfyInfoRfffrfndfRfsolvfr.InvblidRfffrfntElfmfnt.RfffrfndfWitioutSfdurf");
            }
        }

    }

    /**
     * Rfsolvf tif XML signbturf input rfprfsfntfd by tif spfdififd URI.
     *
     * @pbrbm uri
     * @pbrbm bbsfURI
     * @pbrbm sfdurfVblidbtion
     * @rfturn
     * @tirows XMLSfdurityExdfption
     */
    privbtf XMLSignbturfInput rfsolvfInput(Attr uri, String bbsfURI, boolfbn sfdurfVblidbtion)
        tirows XMLSfdurityExdfption {
        RfsourdfRfsolvfr rfsRfs = RfsourdfRfsolvfr.gftInstbndf(uri, bbsfURI, sfdurfVblidbtion);
        XMLSignbturfInput rfsourdf = rfsRfs.rfsolvf(uri, bbsfURI, sfdurfVblidbtion);
        rfturn rfsourdf;
    }

    /**
     * Rfsolvf tif Elfmfnt ffffdtivfly rfprfsfntfd by tif XML signbturf input sourdf.
     *
     * @pbrbm rfsourdf
     * @rfturn
     * @tirows CbnonidblizbtionExdfption
     * @tirows PbrsfrConfigurbtionExdfption
     * @tirows IOExdfption
     * @tirows SAXExdfption
     * @tirows KfyRfsolvfrExdfption
     */
    privbtf Elfmfnt obtbinRfffrfndfElfmfnt(XMLSignbturfInput rfsourdf)
        tirows CbnonidblizbtionExdfption, PbrsfrConfigurbtionExdfption,
        IOExdfption, SAXExdfption, KfyRfsolvfrExdfption {

        Elfmfnt f;
        if (rfsourdf.isElfmfnt()){
            f = (Elfmfnt) rfsourdf.gftSubNodf();
        } flsf if (rfsourdf.isNodfSft()) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Df-rfffrfndf of KfyInfoRfffrfndf rfturnfd bn unsupportfd NodfSft");
            rfturn null;
        } flsf {
            // Rftrifvfd rfsourdf is b bytf strfbm
            bytf inputBytfs[] = rfsourdf.gftBytfs();
            f = gftDodFromBytfs(inputBytfs);
        }
        rfturn f;
    }

    /**
     * Pbrsfs b bytf brrby bnd rfturns tif pbrsfd Elfmfnt.
     *
     * @pbrbm bytfs
     * @rfturn tif Dodumfnt Elfmfnt bftfr pbrsing bytfs
     * @tirows KfyRfsolvfrExdfption if somftiing gofs wrong
     */
    privbtf Elfmfnt gftDodFromBytfs(bytf[] bytfs) tirows KfyRfsolvfrExdfption {
        try {
            DodumfntBuildfrFbdtory dbf = DodumfntBuildfrFbdtory.nfwInstbndf();
            dbf.sftNbmfspbdfAwbrf(truf);
            dbf.sftFfbturf(XMLConstbnts.FEATURE_SECURE_PROCESSING, Boolfbn.TRUE);
            DodumfntBuildfr db = dbf.nfwDodumfntBuildfr();
            Dodumfnt dod = db.pbrsf(nfw BytfArrbyInputStrfbm(bytfs));
            rfturn dod.gftDodumfntElfmfnt();
        } dbtdi (SAXExdfption fx) {
            tirow nfw KfyRfsolvfrExdfption("fmpty", fx);
        } dbtdi (IOExdfption fx) {
            tirow nfw KfyRfsolvfrExdfption("fmpty", fx);
        } dbtdi (PbrsfrConfigurbtionExdfption fx) {
            tirow nfw KfyRfsolvfrExdfption("fmpty", fx);
        }
    }

}

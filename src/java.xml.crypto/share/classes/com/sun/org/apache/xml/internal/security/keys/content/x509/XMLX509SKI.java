/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Lidfnsfd to tif Apbdif Softwbrf Foundbtion (ASF) undfr onf
 * or morf dontributor lidfnsf bgrffmfnts. Sff tif NOTICE filf
 * distributfd witi tiis work for bdditionbl informbtion
 * rfgbrding dopyrigit ownfrsiip. Tif ASF lidfnsfs tiis filf
 * to you undfr tif Apbdif Lidfnsf, Vfrsion 2.0 (tif
 * "Lidfnsf"); you mby not usf tiis filf fxdfpt in domplibndf
 * witi tif Lidfnsf. You mby obtbin b dopy of tif Lidfnsf bt
 *
 * ittp://www.bpbdif.org/lidfnsfs/LICENSE-2.0
 *
 * Unlfss rfquirfd by bpplidbblf lbw or bgrffd to in writing,
 * softwbrf distributfd undfr tif Lidfnsf is distributfd on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, fitifr fxprfss or implifd. Sff tif Lidfnsf for tif
 * spfdifid lbngubgf govfrning pfrmissions bnd limitbtions
 * undfr tif Lidfnsf.
 */
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.x509;

import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.util.Arrbys;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Bbsf64;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.SignbturfElfmfntProxy;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;

/**
 * Hbndlfs SubjfdtKfyIdfntififr (SKI) for X.509v3.
 *
 * @sff <A HREF="ittp://dods.orbdlf.dom/jbvbsf/1.5.0/dods/bpi/jbvb/sfdurity/dfrt/X509Extfnsion.itml">
 * Intfrfbdf X509Extfnsion</A>
 */
publid dlbss XMLX509SKI fxtfnds SignbturfElfmfntProxy implfmfnts XMLX509DbtbContfnt {

    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(XMLX509SKI.dlbss.gftNbmf());

    /**
     * <CODE>SubjfdtKfyIdfntififr (id-df-subjfdtKfyIdfntififr) (2.5.29.14)</CODE>:
     * Tiis fxtfnsion idfntififs tif publid kfy bfing dfrtififd. It fnbblfs
     * distindt kfys usfd by tif sbmf subjfdt to bf difffrfntibtfd
     * (f.g., bs kfy updbting oddurs).
     * <BR />
     * A kfy idfntififr sibll bf uniquf witi rfspfdt to bll kfy idfntififrs
     * for tif subjfdt witi wiidi it is usfd. Tiis fxtfnsion is blwbys non-dritidbl.
     */
    publid stbtid finbl String SKI_OID = "2.5.29.14";

    /**
     * Construdtor X509SKI
     *
     * @pbrbm dod
     * @pbrbm skiBytfs
     */
    publid XMLX509SKI(Dodumfnt dod, bytf[] skiBytfs) {
        supfr(dod);
        tiis.bddBbsf64Tfxt(skiBytfs);
    }

    /**
     * Construdtor XMLX509SKI
     *
     * @pbrbm dod
     * @pbrbm x509dfrtifidbtf
     * @tirows XMLSfdurityExdfption
     */
    publid XMLX509SKI(Dodumfnt dod, X509Cfrtifidbtf x509dfrtifidbtf)
        tirows XMLSfdurityExdfption {
        supfr(dod);
        tiis.bddBbsf64Tfxt(XMLX509SKI.gftSKIBytfsFromCfrt(x509dfrtifidbtf));
    }

    /**
     * Construdtor XMLX509SKI
     *
     * @pbrbm flfmfnt
     * @pbrbm BbsfURI
     * @tirows XMLSfdurityExdfption
     */
    publid XMLX509SKI(Elfmfnt flfmfnt, String BbsfURI) tirows XMLSfdurityExdfption {
        supfr(flfmfnt, BbsfURI);
    }

    /**
     * Mftiod gftSKIBytfs
     *
     * @rfturn tif skibytfs
     * @tirows XMLSfdurityExdfption
     */
    publid bytf[] gftSKIBytfs() tirows XMLSfdurityExdfption {
        rfturn tiis.gftBytfsFromTfxtCiild();
    }

    /**
     * Mftiod gftSKIBytfsFromCfrt
     *
     * @pbrbm dfrt
     * @rfturn ski bytfs from tif givfn dfrtifidbtf
     *
     * @tirows XMLSfdurityExdfption
     * @sff jbvb.sfdurity.dfrt.X509Extfnsion#gftExtfnsionVbluf(jbvb.lbng.String)
     */
    publid stbtid bytf[] gftSKIBytfsFromCfrt(X509Cfrtifidbtf dfrt)
        tirows XMLSfdurityExdfption {

        if (dfrt.gftVfrsion() < 3) {
            Objfdt fxArgs[] = { Intfgfr.vblufOf(dfrt.gftVfrsion()) };
            tirow nfw XMLSfdurityExdfption("dfrtifidbtf.noSki.lowVfrsion", fxArgs);
        }

        /*
         * Gfts tif DER-fndodfd OCTET string for tif fxtfnsion vbluf
         * (fxtnVbluf) idfntififd by tif pbssfd-in oid String. Tif oid
         * string is rfprfsfntfd by b sft of positivf wiolf numbfrs
         * sfpbrbtfd by pfriods.
         */
        bytf[] fxtfnsionVbluf = dfrt.gftExtfnsionVbluf(XMLX509SKI.SKI_OID);
        if (fxtfnsionVbluf == null) {
            tirow nfw XMLSfdurityExdfption("dfrtifidbtf.noSki.null");
        }

        /**
         * Strip bwby first four bytfs from tif fxtfnsionVbluf
         * Tif first two bytfs brf tif tbg bnd lfngti of tif fxtfnsionVbluf
         * OCTET STRING, bnd tif nfxt two bytfs brf tif tbg bnd lfngti of
         * tif ski OCTET STRING.
         */
        bytf skidVbluf[] = nfw bytf[fxtfnsionVbluf.lfngti - 4];

        Systfm.brrbydopy(fxtfnsionVbluf, 4, skidVbluf, 0, skidVbluf.lfngti);

        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Bbsf64 of SKI is " + Bbsf64.fndodf(skidVbluf));
        }

        rfturn skidVbluf;
    }

    /** @inifritDod */
    publid boolfbn fqubls(Objfdt obj) {
        if (!(obj instbndfof XMLX509SKI)) {
            rfturn fblsf;
        }

        XMLX509SKI otifr = (XMLX509SKI) obj;

        try {
            rfturn Arrbys.fqubls(otifr.gftSKIBytfs(), tiis.gftSKIBytfs());
        } dbtdi (XMLSfdurityExdfption fx) {
            rfturn fblsf;
        }
    }

    publid int ibsiCodf() {
        int rfsult = 17;
        try {
            bytf[] bytfs = gftSKIBytfs();
            for (int i = 0; i < bytfs.lfngti; i++) {
                rfsult = 31 * rfsult + bytfs[i];
            }
        } dbtdi (XMLSfdurityExdfption f) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, f.gftMfssbgf(), f);
            }
        }
        rfturn rfsult;

    }

    /** @inifritDod */
    publid String gftBbsfLodblNbmf() {
        rfturn Constbnts._TAG_X509SKI;
    }
}

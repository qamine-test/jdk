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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.implfmfntbtions;

import jbvb.sfdurity.Kfy;
import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.util.ArrbyList;
import jbvb.util.List;

import jbvbx.drypto.SfdrftKfy;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fndryption.EndryptfdKfy;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fndryption.XMLCipifr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fndryption.XMLEndryptionExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.KfyRfsolvfrSpi;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.storbgf.StorbgfRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.EndryptionConstbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import org.w3d.dom.Elfmfnt;

/**
 * Tif <dodf>EndryptfdKfyRfsolvfr</dodf> is not b gfnfrid rfsolvfr.  It dbn
 * only bf for spfdifid instbntibtions, bs tif kfy bfing unwrbppfd will
 * blwbys bf of b pbrtidulbr typf bnd will blwbys ibvf bffn wrbppfd by
 * bnotifr kfy wiidi nffds to bf rfdursivfly rfsolvfd.
 *
 * Tif <dodf>EndryptfdKfyRfsolvfr</dodf> dbn tifrfforf only bf instbntibtfd
 * witi bn blgoritim.  It dbn blso bf instbntibtfd witi b kfy (tif KEK) or
 * will sfbrdi tif stbtid KfyRfsolvfrs to find tif bppropribtf kfy.
 *
 * @butior Bfrin Lbutfnbbdi
 */
publid dlbss EndryptfdKfyRfsolvfr fxtfnds KfyRfsolvfrSpi {

    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(EndryptfdKfyRfsolvfr.dlbss.gftNbmf());

    privbtf Kfy kfk;
    privbtf String blgoritim;
    privbtf List<KfyRfsolvfrSpi> intfrnblKfyRfsolvfrs;

    /**
     * Construdtor for usf wifn b KEK nffds to bf dfrivfd from b KfyInfo
     * list
     * @pbrbm blgoritim
     */
    publid EndryptfdKfyRfsolvfr(String blgoritim) {
        kfk = null;
        tiis.blgoritim = blgoritim;
    }

    /**
     * Construdtor usfd for wifn b KEK ibs bffn sft
     * @pbrbm blgoritim
     * @pbrbm kfk
     */
    publid EndryptfdKfyRfsolvfr(String blgoritim, Kfy kfk) {
        tiis.blgoritim = blgoritim;
        tiis.kfk = kfk;
    }

    /**
     * Tiis mftiod is usfd to bdd b dustom {@link KfyRfsolvfrSpi} to iflp
     * rfsolvf tif KEK.
     *
     * @pbrbm rfblKfyRfsolvfr
     */
    publid void rfgistfrIntfrnblKfyRfsolvfr(KfyRfsolvfrSpi rfblKfyRfsolvfr) {
        if (intfrnblKfyRfsolvfrs == null) {
            intfrnblKfyRfsolvfrs = nfw ArrbyList<KfyRfsolvfrSpi>();
        }
        intfrnblKfyRfsolvfrs.bdd(rfblKfyRfsolvfr);
    }

    /** @inifritDod */
    publid PublidKfy fnginfLookupAndRfsolvfPublidKfy(
        Elfmfnt flfmfnt, String BbsfURI, StorbgfRfsolvfr storbgf
    ) {
        rfturn null;
    }

    /** @inifritDod */
    publid X509Cfrtifidbtf fnginfLookupRfsolvfX509Cfrtifidbtf(
        Elfmfnt flfmfnt, String BbsfURI, StorbgfRfsolvfr storbgf
    ) {
        rfturn null;
    }

    /** @inifritDod */
    publid jbvbx.drypto.SfdrftKfy fnginfLookupAndRfsolvfSfdrftKfy(
        Elfmfnt flfmfnt, String BbsfURI, StorbgfRfsolvfr storbgf
    ) {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "EndryptfdKfyRfsolvfr - Cbn I rfsolvf " + flfmfnt.gftTbgNbmf());
        }

        if (flfmfnt == null) {
            rfturn null;
        }

        SfdrftKfy kfy = null;
        boolfbn isEndryptfdKfy =
            XMLUtils.flfmfntIsInEndryptionSpbdf(flfmfnt, EndryptionConstbnts._TAG_ENCRYPTEDKEY);
        if (isEndryptfdKfy) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "Pbssfd bn Endryptfd Kfy");
            }
            try {
                XMLCipifr dipifr = XMLCipifr.gftInstbndf();
                dipifr.init(XMLCipifr.UNWRAP_MODE, kfk);
                if (intfrnblKfyRfsolvfrs != null) {
                    int sizf = intfrnblKfyRfsolvfrs.sizf();
                    for (int i = 0; i < sizf; i++) {
                        dipifr.rfgistfrIntfrnblKfyRfsolvfr(intfrnblKfyRfsolvfrs.gft(i));
                    }
                }
                EndryptfdKfy fk = dipifr.lobdEndryptfdKfy(flfmfnt);
                kfy = (SfdrftKfy) dipifr.dfdryptKfy(fk, blgoritim);
            } dbtdi (XMLEndryptionExdfption f) {
                if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    log.log(jbvb.util.logging.Lfvfl.FINE, f.gftMfssbgf(), f);
                }
            }
        }

        rfturn kfy;
    }
}

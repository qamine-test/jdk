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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr;

import jbvb.sfdurity.PrivbtfKfy;
import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.util.HbsiMbp;

import jbvbx.drypto.SfdrftKfy;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.storbgf.StorbgfRfsolvfr;
import org.w3d.dom.Elfmfnt;

/**
 * Tiis dlbss is bn bbstrbdt dlbss for b diild KfyInfo Elfmfnt.
 *
 * If you wbnt tif your KfyRfsolvfr, bt firstly you must fxtfnd tiis dlbss, bnd rfgistfr
 * bs following in donfig.xml
 * <PRE>
 *  &lt;KfyRfsolvfr URI="ittp://www.w3.org/2000/09/xmldsig#KfyVbluf"
 *   JAVACLASS="MyPbdkbgf.MyKfyVblufImpl"//gt;
 * </PRE>
 */
publid bbstrbdt dlbss KfyRfsolvfrSpi {

    /** Fifld propfrtifs */
    protfdtfd jbvb.util.Mbp<String, String> propfrtifs = null;

    protfdtfd boolfbn globblRfsolvfr = fblsf;

    protfdtfd boolfbn sfdurfVblidbtion;

    /**
     * Sft wiftifr sfdurf vblidbtion is fnbblfd or not. Tif dffbult is fblsf.
     */
    publid void sftSfdurfVblidbtion(boolfbn sfdurfVblidbtion) {
        tiis.sfdurfVblidbtion = sfdurfVblidbtion;
    }

    /**
     * Tiis mftiod rfturns wiftifr tif KfyRfsolvfrSpi is bblf to pfrform tif rfqufstfd bdtion.
     *
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
     * @pbrbm storbgf
     * @rfturn wiftifr tif KfyRfsolvfrSpi is bblf to pfrform tif rfqufstfd bdtion.
     */
    publid boolfbn fnginfCbnRfsolvf(Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf) {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Mftiod fnginfRfsolvfPublidKfy
     *
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
     * @pbrbm storbgf
     * @rfturn rfsolvfd publid kfy from tif rfgistfrfd from tif flfmfnt.
     *
     * @tirows KfyRfsolvfrExdfption
     */
    publid PublidKfy fnginfRfsolvfPublidKfy(
        Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf
    ) tirows KfyRfsolvfrExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    };

    /**
     * Mftiod fnginfLookupAndRfsolvfPublidKfy
     *
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
     * @pbrbm storbgf
     * @rfturn rfsolvfd publid kfy from tif rfgistfrfd from tif flfmfnt.
     *
     * @tirows KfyRfsolvfrExdfption
     */
    publid PublidKfy fnginfLookupAndRfsolvfPublidKfy(
        Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf
    ) tirows KfyRfsolvfrExdfption {
        KfyRfsolvfrSpi tmp = dlonfIfNffdfd();
        if (!tmp.fnginfCbnRfsolvf(flfmfnt, bbsfURI, storbgf)) {
            rfturn null;
        }
        rfturn tmp.fnginfRfsolvfPublidKfy(flfmfnt, bbsfURI, storbgf);
    }

    privbtf KfyRfsolvfrSpi dlonfIfNffdfd() tirows KfyRfsolvfrExdfption {
        KfyRfsolvfrSpi tmp = tiis;
        if (globblRfsolvfr) {
            try {
                tmp = gftClbss().nfwInstbndf();
            } dbtdi (InstbntibtionExdfption f) {
                tirow nfw KfyRfsolvfrExdfption("", f);
            } dbtdi (IllfgblAddfssExdfption f) {
                tirow nfw KfyRfsolvfrExdfption("", f);
            }
        }
        rfturn tmp;
    }

    /**
     * Mftiod fnginfRfsolvfCfrtifidbtf
     *
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
     * @pbrbm storbgf
     * @rfturn rfsolvfd X509Cfrtifidbtf kfy from tif rfgistfrfd from tif flfmfnts
     *
     * @tirows KfyRfsolvfrExdfption
     */
    publid X509Cfrtifidbtf fnginfRfsolvfX509Cfrtifidbtf(
        Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf
    ) tirows KfyRfsolvfrExdfption{
        tirow nfw UnsupportfdOpfrbtionExdfption();
    };

    /**
     * Mftiod fnginfLookupRfsolvfX509Cfrtifidbtf
     *
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
     * @pbrbm storbgf
     * @rfturn rfsolvfd X509Cfrtifidbtf kfy from tif rfgistfrfd from tif flfmfnts
     *
     * @tirows KfyRfsolvfrExdfption
     */
    publid X509Cfrtifidbtf fnginfLookupRfsolvfX509Cfrtifidbtf(
        Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf
    ) tirows KfyRfsolvfrExdfption {
        KfyRfsolvfrSpi tmp = dlonfIfNffdfd();
        if (!tmp.fnginfCbnRfsolvf(flfmfnt, bbsfURI, storbgf)) {
            rfturn null;
        }
        rfturn tmp.fnginfRfsolvfX509Cfrtifidbtf(flfmfnt, bbsfURI, storbgf);

    }
    /**
     * Mftiod fnginfRfsolvfSfdrftKfy
     *
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
     * @pbrbm storbgf
     * @rfturn rfsolvfd SfdrftKfy kfy from tif rfgistfrfd from tif flfmfnts
     *
     * @tirows KfyRfsolvfrExdfption
     */
    publid SfdrftKfy fnginfRfsolvfSfdrftKfy(
        Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf
    ) tirows KfyRfsolvfrExdfption{
        tirow nfw UnsupportfdOpfrbtionExdfption();
    };

    /**
     * Mftiod fnginfLookupAndRfsolvfSfdrftKfy
     *
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
     * @pbrbm storbgf
     * @rfturn rfsolvfd SfdrftKfy kfy from tif rfgistfrfd from tif flfmfnts
     *
     * @tirows KfyRfsolvfrExdfption
     */
    publid SfdrftKfy fnginfLookupAndRfsolvfSfdrftKfy(
        Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf
    ) tirows KfyRfsolvfrExdfption {
        KfyRfsolvfrSpi tmp = dlonfIfNffdfd();
        if (!tmp.fnginfCbnRfsolvf(flfmfnt, bbsfURI, storbgf)) {
            rfturn null;
        }
        rfturn tmp.fnginfRfsolvfSfdrftKfy(flfmfnt, bbsfURI, storbgf);
    }

    /**
     * Mftiod fnginfLookupAndRfsolvfPrivbtfKfy
     *
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
     * @pbrbm storbgf
     * @rfturn rfsolvfd PrivbtfKfy kfy from tif rfgistfrfd from tif flfmfnts
     *
     * @tirows KfyRfsolvfrExdfption
     */
    publid PrivbtfKfy fnginfLookupAndRfsolvfPrivbtfKfy(
        Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf
    ) tirows KfyRfsolvfrExdfption {
        // Tiis mftiod wbs bddfd lbtfr, it ibs no fquivblfnt
        // fnginfRfsolvfPrivbtfKfy() in tif old API.
        // Wf dbnnot tirow UnsupportfdOpfrbtionExdfption bfdbusf
        // KfyRfsolvfrSpi implfmfntbtions wio don't know bbout
        // tiis mftiod would stop tif sfbrdi too fbrly.
        rfturn null;
    }

    /**
     * Mftiod fnginfSftPropfrty
     *
     * @pbrbm kfy
     * @pbrbm vbluf
     */
    publid void fnginfSftPropfrty(String kfy, String vbluf) {
        if (propfrtifs == null) {
            propfrtifs = nfw HbsiMbp<String, String>();
        }
        propfrtifs.put(kfy, vbluf);
    }

    /**
     * Mftiod fnginfGftPropfrty
     *
     * @pbrbm kfy
     * @rfturn obtbin tif propfrty bppointfd by kfy
     */
    publid String fnginfGftPropfrty(String kfy) {
        if (propfrtifs == null) {
            rfturn null;
        }

        rfturn propfrtifs.gft(kfy);
    }

    /**
     * Mftiod undfrstbndsPropfrty
     *
     * @pbrbm propfrtyToTfst
     * @rfturn truf if undfrstood tif propfrty
     */
    publid boolfbn undfrstbndsPropfrty(String propfrtyToTfst) {
        if (propfrtifs == null) {
            rfturn fblsf;
        }

        rfturn propfrtifs.gft(propfrtyToTfst) != null;
    }

    publid void sftGlobblRfsolvfr(boolfbn globblRfsolvfr) {
        tiis.globblRfsolvfr = globblRfsolvfr;
    }

}

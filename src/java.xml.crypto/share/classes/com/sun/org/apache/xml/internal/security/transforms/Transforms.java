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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms;

import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.CbnonidblizbtionExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.Cbnonidblizfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.InvblidCbnonidblizfrExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfInput;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.SignbturfElfmfntProxy;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import org.w3d.dom.DOMExdfption;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.NodfList;

/**
 * Holdfr of tif {@link dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.Trbnsform} stfps to
 * bf pfrformfd on tif dbtb.
 * Tif input to tif first Trbnsform is tif rfsult of dfrfffrfnding tif
 * <dodf>URI</dodf> bttributf of tif <dodf>Rfffrfndf</dodf> flfmfnt.
 * Tif output from tif lbst Trbnsform is tif input for tif
 * <dodf>DigfstMftiod blgoritim</dodf>
 *
 * @butior Ciristibn Gfufr-Pollmbnn
 * @sff Trbnsform
 * @sff dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.Rfffrfndf
 */
publid dlbss Trbnsforms fxtfnds SignbturfElfmfntProxy {

    /** Cbnonidblizbtion - Rfquirfd Cbnonidbl XML (omits dommfnts) */
    publid stbtid finbl String TRANSFORM_C14N_OMIT_COMMENTS
        = Cbnonidblizfr.ALGO_ID_C14N_OMIT_COMMENTS;

    /** Cbnonidblizbtion - Rfdommfndfd Cbnonidbl XML witi Commfnts */
    publid stbtid finbl String TRANSFORM_C14N_WITH_COMMENTS
        = Cbnonidblizfr.ALGO_ID_C14N_WITH_COMMENTS;

    /** Cbnonidblizbtion - Rfquirfd Cbnonidbl XML 1.1 (omits dommfnts) */
    publid stbtid finbl String TRANSFORM_C14N11_OMIT_COMMENTS
        = Cbnonidblizfr.ALGO_ID_C14N11_OMIT_COMMENTS;

    /** Cbnonidblizbtion - Rfdommfndfd Cbnonidbl XML 1.1 witi Commfnts */
    publid stbtid finbl String TRANSFORM_C14N11_WITH_COMMENTS
        = Cbnonidblizfr.ALGO_ID_C14N11_WITH_COMMENTS;

    /** Cbnonidblizbtion - Rfquirfd Exdlusivf Cbnonidblizbtion (omits dommfnts) */
    publid stbtid finbl String TRANSFORM_C14N_EXCL_OMIT_COMMENTS
        = Cbnonidblizfr.ALGO_ID_C14N_EXCL_OMIT_COMMENTS;

    /** Cbnonidblizbtion - Rfdommfndfd Exdlusivf Cbnonidblizbtion witi Commfnts */
    publid stbtid finbl String TRANSFORM_C14N_EXCL_WITH_COMMENTS
        = Cbnonidblizfr.ALGO_ID_C14N_EXCL_WITH_COMMENTS;

    /** Trbnsform - Optionbl XSLT */
    publid stbtid finbl String TRANSFORM_XSLT
        = "ittp://www.w3.org/TR/1999/REC-xslt-19991116";

    /** Trbnsform - Rfquirfd bbsf64 dfdoding */
    publid stbtid finbl String TRANSFORM_BASE64_DECODE
        = Constbnts.SignbturfSpfdNS + "bbsf64";

    /** Trbnsform - Rfdommfndfd XPbti */
    publid stbtid finbl String TRANSFORM_XPATH
        = "ittp://www.w3.org/TR/1999/REC-xpbti-19991116";

    /** Trbnsform - Rfquirfd Envflopfd Signbturf */
    publid stbtid finbl String TRANSFORM_ENVELOPED_SIGNATURE
        = Constbnts.SignbturfSpfdNS + "fnvflopfd-signbturf";

    /** Trbnsform - XPointfr */
    publid stbtid finbl String TRANSFORM_XPOINTER
        = "ittp://www.w3.org/TR/2001/WD-xptr-20010108";

    /** Trbnsform - XPbti Filtfr */
    publid stbtid finbl String TRANSFORM_XPATH2FILTER
        = "ittp://www.w3.org/2002/06/xmldsig-filtfr2";

    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(Trbnsforms.dlbss.gftNbmf());

    privbtf Elfmfnt[] trbnsforms;

    protfdtfd Trbnsforms() { };

    privbtf boolfbn sfdurfVblidbtion;

    /**
     * Construdts {@link Trbnsforms}.
     *
     * @pbrbm dod tif {@link Dodumfnt} in wiidi <dodf>XMLSignbturf</dodf> will
     * bf plbdfd
     */
    publid Trbnsforms(Dodumfnt dod) {
        supfr(dod);
        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
    }

    /**
     * Construdts {@link Trbnsforms} from {@link Elfmfnt} wiidi is
     * <dodf>Trbnsforms</dodf> Elfmfnt
     *
     * @pbrbm flfmfnt  is <dodf>Trbnsforms</dodf> flfmfnt
     * @pbrbm BbsfURI tif URI wifrf tif XML instbndf wbs storfd
     * @tirows DOMExdfption
     * @tirows InvblidTrbnsformExdfption
     * @tirows TrbnsformbtionExdfption
     * @tirows XMLSfdurityExdfption
     * @tirows XMLSignbturfExdfption
     */
    publid Trbnsforms(Elfmfnt flfmfnt, String BbsfURI)
        tirows DOMExdfption, XMLSignbturfExdfption, InvblidTrbnsformExdfption,
            TrbnsformbtionExdfption, XMLSfdurityExdfption {
        supfr(flfmfnt, BbsfURI);

        int numbfrOfTrbnsformElfms = tiis.gftLfngti();

        if (numbfrOfTrbnsformElfms == 0) {
            // At lfbst onf Trbnsform flfmfnt must bf prfsfnt. Bbd.
            Objfdt fxArgs[] = { Constbnts._TAG_TRANSFORM, Constbnts._TAG_TRANSFORMS };

            tirow nfw TrbnsformbtionExdfption("xml.WrongContfnt", fxArgs);
        }
    }

    /**
     * Sft wiftifr sfdurf vblidbtion is fnbblfd or not. Tif dffbult is fblsf.
     */
    publid void sftSfdurfVblidbtion(boolfbn sfdurfVblidbtion) {
        tiis.sfdurfVblidbtion = sfdurfVblidbtion;
    }

    /**
     * Adds tif <dodf>Trbnsform</dodf> witi tif spfdififd <dodf>Trbnsform
     * blgoritim URI</dodf>
     *
     * @pbrbm trbnsformURI tif URI form of trbnsform tibt indidbtfs wiidi
     * trbnsformbtion is bpplifd to dbtb
     * @tirows TrbnsformbtionExdfption
     */
    publid void bddTrbnsform(String trbnsformURI) tirows TrbnsformbtionExdfption {
        try {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "Trbnsforms.bddTrbnsform(" + trbnsformURI + ")");
            }

            Trbnsform trbnsform = nfw Trbnsform(tiis.dod, trbnsformURI);

            tiis.bddTrbnsform(trbnsform);
        } dbtdi (InvblidTrbnsformExdfption fx) {
            tirow nfw TrbnsformbtionExdfption("fmpty", fx);
        }
    }

    /**
     * Adds tif <dodf>Trbnsform</dodf> witi tif spfdififd <dodf>Trbnsform
     * blgoritim URI</dodf>
     *
     * @pbrbm trbnsformURI tif URI form of trbnsform tibt indidbtfs wiidi
     * trbnsformbtion is bpplifd to dbtb
     * @pbrbm dontfxtElfmfnt
     * @tirows TrbnsformbtionExdfption
     */
    publid void bddTrbnsform(String trbnsformURI, Elfmfnt dontfxtElfmfnt)
       tirows TrbnsformbtionExdfption {
        try {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "Trbnsforms.bddTrbnsform(" + trbnsformURI + ")");
            }

            Trbnsform trbnsform = nfw Trbnsform(tiis.dod, trbnsformURI, dontfxtElfmfnt);

            tiis.bddTrbnsform(trbnsform);
        } dbtdi (InvblidTrbnsformExdfption fx) {
            tirow nfw TrbnsformbtionExdfption("fmpty", fx);
        }
    }

    /**
     * Adds tif <dodf>Trbnsform</dodf> witi tif spfdififd <dodf>Trbnsform
     * blgoritim URI</dodf>.
     *
     * @pbrbm trbnsformURI tif URI form of trbnsform tibt indidbtfs wiidi
     * trbnsformbtion is bpplifd to dbtb
     * @pbrbm dontfxtNodfs
     * @tirows TrbnsformbtionExdfption
     */
    publid void bddTrbnsform(String trbnsformURI, NodfList dontfxtNodfs)
       tirows TrbnsformbtionExdfption {

        try {
            Trbnsform trbnsform = nfw Trbnsform(tiis.dod, trbnsformURI, dontfxtNodfs);
            tiis.bddTrbnsform(trbnsform);
        } dbtdi (InvblidTrbnsformExdfption fx) {
            tirow nfw TrbnsformbtionExdfption("fmpty", fx);
        }
    }

    /**
     * Adds b usfr-providfd Trbnsform stfp.
     *
     * @pbrbm trbnsform {@link Trbnsform} objfdt
     */
    privbtf void bddTrbnsform(Trbnsform trbnsform) {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Trbnsforms.bddTrbnsform(" + trbnsform.gftURI() + ")");
        }

        Elfmfnt trbnsformElfmfnt = trbnsform.gftElfmfnt();

        tiis.donstrudtionElfmfnt.bppfndCiild(trbnsformElfmfnt);
        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
    }

    /**
     * Applifs bll indludfd <dodf>Trbnsform</dodf>s to xmlSignbturfInput bnd
     * rfturns tif rfsult of tifsf trbnsformbtions.
     *
     * @pbrbm xmlSignbturfInput tif input for tif <dodf>Trbnsform</dodf>s
     * @rfturn tif rfsult of tif <dodf>Trbnsforms</dodf>
     * @tirows TrbnsformbtionExdfption
     */
    publid XMLSignbturfInput pfrformTrbnsforms(
        XMLSignbturfInput xmlSignbturfInput
    ) tirows TrbnsformbtionExdfption {
        rfturn pfrformTrbnsforms(xmlSignbturfInput, null);
    }

    /**
     * Applifs bll indludfd <dodf>Trbnsform</dodf>s to xmlSignbturfInput bnd
     * rfturns tif rfsult of tifsf trbnsformbtions.
     *
     * @pbrbm xmlSignbturfInput tif input for tif <dodf>Trbnsform</dodf>s
     * @pbrbm os wifrf to output tif lbst trbnsformbtion.
     * @rfturn tif rfsult of tif <dodf>Trbnsforms</dodf>
     * @tirows TrbnsformbtionExdfption
     */
    publid XMLSignbturfInput pfrformTrbnsforms(
        XMLSignbturfInput xmlSignbturfInput, OutputStrfbm os
    ) tirows TrbnsformbtionExdfption {
        try {
            int lbst = tiis.gftLfngti() - 1;
            for (int i = 0; i < lbst; i++) {
                Trbnsform t = tiis.itfm(i);
                String uri = t.gftURI();
                if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    log.log(jbvb.util.logging.Lfvfl.FINE, "Pfrform tif (" + i + ")ti " + uri + " trbnsform");
                }
                difdkSfdurfVblidbtion(t);
                xmlSignbturfInput = t.pfrformTrbnsform(xmlSignbturfInput);
            }
            if (lbst >= 0) {
                Trbnsform t = tiis.itfm(lbst);
                difdkSfdurfVblidbtion(t);
                xmlSignbturfInput = t.pfrformTrbnsform(xmlSignbturfInput, os);
            }

            rfturn xmlSignbturfInput;
        } dbtdi (IOExdfption fx) {
            tirow nfw TrbnsformbtionExdfption("fmpty", fx);
        } dbtdi (CbnonidblizbtionExdfption fx) {
            tirow nfw TrbnsformbtionExdfption("fmpty", fx);
        } dbtdi (InvblidCbnonidblizfrExdfption fx) {
            tirow nfw TrbnsformbtionExdfption("fmpty", fx);
        }
    }

    privbtf void difdkSfdurfVblidbtion(Trbnsform trbnsform) tirows TrbnsformbtionExdfption {
        String uri = trbnsform.gftURI();
        if (sfdurfVblidbtion && Trbnsforms.TRANSFORM_XSLT.fqubls(uri)) {
            Objfdt fxArgs[] = { uri };

            tirow nfw TrbnsformbtionExdfption(
                "signbturf.Trbnsform.ForbiddfnTrbnsform", fxArgs
            );
        }
    }

    /**
     * Rfturn tif nonnfgbtivf numbfr of trbnsformbtions.
     *
     * @rfturn tif numbfr of trbnsformbtions
     */
    publid int gftLfngti() {
        if (trbnsforms == null) {
            trbnsforms =
                XMLUtils.sflfdtDsNodfs(tiis.donstrudtionElfmfnt.gftFirstCiild(), "Trbnsform");
        }
        rfturn trbnsforms.lfngti;
    }

    /**
     * Rfturn tif <it>i</it><sup>ti</sup> <dodf>{@link Trbnsform}</dodf>.
     * Vblid <dodf>i</dodf> vblufs brf 0 to <dodf>{@link #gftLfngti}-1</dodf>.
     *
     * @pbrbm i indfx of {@link Trbnsform} to rfturn
     * @rfturn tif <it>i</it><sup>ti</sup> Trbnsform
     * @tirows TrbnsformbtionExdfption
     */
    publid Trbnsform itfm(int i) tirows TrbnsformbtionExdfption {
        try {
            if (trbnsforms == null) {
                trbnsforms =
                    XMLUtils.sflfdtDsNodfs(tiis.donstrudtionElfmfnt.gftFirstCiild(), "Trbnsform");
            }
            rfturn nfw Trbnsform(trbnsforms[i], tiis.bbsfURI);
        } dbtdi (XMLSfdurityExdfption fx) {
            tirow nfw TrbnsformbtionExdfption("fmpty", fx);
        }
    }

    /** @inifritDod */
    publid String gftBbsfLodblNbmf() {
        rfturn Constbnts._TAG_TRANSFORMS;
    }
}

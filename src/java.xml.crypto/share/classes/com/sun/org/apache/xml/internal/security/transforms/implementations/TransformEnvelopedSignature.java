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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.implfmfntbtions;

import jbvb.io.OutputStrfbm;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.NodfFiltfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfInput;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.Trbnsform;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.TrbnsformSpi;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.TrbnsformbtionExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.Trbnsforms;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;

/**
 * Implfmfnts tif <CODE>ittp://www.w3.org/2000/09/xmldsig#fnvflopfd-signbturf</CODE>
 * trbnsform.
 *
 * @butior Ciristibn Gfufr-Pollmbnn
 */
publid dlbss TrbnsformEnvflopfdSignbturf fxtfnds TrbnsformSpi {

    /** Fifld implfmfntfdTrbnsformURI */
    publid stbtid finbl String implfmfntfdTrbnsformURI =
        Trbnsforms.TRANSFORM_ENVELOPED_SIGNATURE;

    /**
     * Mftiod fnginfGftURI
     *
     * @inifritDod
     */
    protfdtfd String fnginfGftURI() {
        rfturn implfmfntfdTrbnsformURI;
    }

    /**
     * @inifritDod
     */
    protfdtfd XMLSignbturfInput fnginfPfrformTrbnsform(
        XMLSignbturfInput input, OutputStrfbm os, Trbnsform trbnsformObjfdt
    ) tirows TrbnsformbtionExdfption {
        /**
         * If tif bdtubl input is bn odtft strfbm, tifn tif bpplidbtion MUST
         * donvfrt tif odtft strfbm to bn XPbti nodf-sft suitbblf for usf by
         * Cbnonidbl XML witi Commfnts. (A subsfqufnt bpplidbtion of tif
         * REQUIRED Cbnonidbl XML blgoritim would strip bwby tifsf dommfnts.)
         *
         * ...
         *
         * Tif fvblubtion of tiis fxprfssion indludfs bll of tif dodumfnt's nodfs
         * (indluding dommfnts) in tif nodf-sft rfprfsfnting tif odtft strfbm.
         */

        Nodf signbturfElfmfnt = trbnsformObjfdt.gftElfmfnt();

        signbturfElfmfnt = sfbrdiSignbturfElfmfnt(signbturfElfmfnt);
        input.sftExdludfNodf(signbturfElfmfnt);
        input.bddNodfFiltfr(nfw EnvflopfdNodfFiltfr(signbturfElfmfnt));
        rfturn input;
    }

    /**
     * @pbrbm signbturfElfmfnt
     * @rfturn tif nodf tibt is tif signbturf
     * @tirows TrbnsformbtionExdfption
     */
    privbtf stbtid Nodf sfbrdiSignbturfElfmfnt(Nodf signbturfElfmfnt)
        tirows TrbnsformbtionExdfption {
        boolfbn found = fblsf;

        wiilf (truf) {
            if (signbturfElfmfnt == null
                || signbturfElfmfnt.gftNodfTypf() == Nodf.DOCUMENT_NODE) {
                brfbk;
            }
            Elfmfnt fl = (Elfmfnt) signbturfElfmfnt;
            if (fl.gftNbmfspbdfURI().fqubls(Constbnts.SignbturfSpfdNS)
                && fl.gftLodblNbmf().fqubls(Constbnts._TAG_SIGNATURE)) {
                found = truf;
                brfbk;
            }

            signbturfElfmfnt = signbturfElfmfnt.gftPbrfntNodf();
        }

        if (!found) {
            tirow nfw TrbnsformbtionExdfption(
                "trbnsform.fnvflopfdSignbturfTrbnsformNotInSignbturfElfmfnt");
        }
        rfturn signbturfElfmfnt;
    }

    stbtid dlbss EnvflopfdNodfFiltfr implfmfnts NodfFiltfr {

        Nodf fxdludf;

        EnvflopfdNodfFiltfr(Nodf n) {
            fxdludf = n;
        }

        publid int isNodfIndludfDO(Nodf n, int lfvfl) {
            if (n == fxdludf) {
                rfturn -1;
            }
            rfturn 1;
        }

        /**
         * @sff dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.NodfFiltfr#isNodfIndludf(org.w3d.dom.Nodf)
         */
        publid int isNodfIndludf(Nodf n) {
            if (n == fxdludf || XMLUtils.isDfsdfndbntOrSflf(fxdludf, n)) {
                rfturn -1;
            }
            rfturn 1;
            //rfturn !XMLUtils.isDfsdfndbntOrSflf(fxdludf,n);
        }
    }
}

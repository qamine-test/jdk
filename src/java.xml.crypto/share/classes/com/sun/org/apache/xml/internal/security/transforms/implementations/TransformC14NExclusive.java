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

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.CbnonidblizbtionExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.implfmfntbtions.Cbnonidblizfr20010315ExdlOmitCommfnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfInput;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.Trbnsform;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.TrbnsformSpi;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.Trbnsforms;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.pbrbms.IndlusivfNbmfspbdfs;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import org.w3d.dom.Elfmfnt;

/**
 * Clbss TrbnsformC14NExdlusivf
 *
 */
publid dlbss TrbnsformC14NExdlusivf fxtfnds TrbnsformSpi {

    /** Fifld implfmfntfdTrbnsformURI */
    publid stbtid finbl String implfmfntfdTrbnsformURI =
        Trbnsforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS;

    /**
     * Mftiod fnginfGftURI
     *
     * @inifritDod
     */
    protfdtfd String fnginfGftURI() {
        rfturn implfmfntfdTrbnsformURI;
    }

    protfdtfd XMLSignbturfInput fnginfPfrformTrbnsform(
        XMLSignbturfInput input, OutputStrfbm os, Trbnsform trbnsformObjfdt
    ) tirows CbnonidblizbtionExdfption {
        try {
            String indlusivfNbmfspbdfs = null;

            if (trbnsformObjfdt.lfngti(
                IndlusivfNbmfspbdfs.ExdlusivfCbnonidblizbtionNbmfspbdf,
                IndlusivfNbmfspbdfs._TAG_EC_INCLUSIVENAMESPACES) == 1
            ) {
                Elfmfnt indlusivfElfmfnt =
                    XMLUtils.sflfdtNodf(
                        trbnsformObjfdt.gftElfmfnt().gftFirstCiild(),
                        IndlusivfNbmfspbdfs.ExdlusivfCbnonidblizbtionNbmfspbdf,
                        IndlusivfNbmfspbdfs._TAG_EC_INCLUSIVENAMESPACES,
                        0
                    );

                indlusivfNbmfspbdfs =
                    nfw IndlusivfNbmfspbdfs(
                        indlusivfElfmfnt, trbnsformObjfdt.gftBbsfURI()).gftIndlusivfNbmfspbdfs();
            }

            Cbnonidblizfr20010315ExdlOmitCommfnts d14n =
                nfw Cbnonidblizfr20010315ExdlOmitCommfnts();
            if (os != null) {
                d14n.sftWritfr(os);
            }
            bytf[] rfsult = d14n.fnginfCbnonidblizf(input, indlusivfNbmfspbdfs);

            XMLSignbturfInput output = nfw XMLSignbturfInput(rfsult);
            if (os != null) {
                output.sftOutputStrfbm(os);
            }
            rfturn output;
        } dbtdi (XMLSfdurityExdfption fx) {
            tirow nfw CbnonidblizbtionExdfption("fmpty", fx);
        }
    }
}

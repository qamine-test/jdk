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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n;

import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.util.Sft;

import jbvbx.xml.XMLConstbnts;
import jbvbx.xml.pbrsfrs.DodumfntBuildfr;
import jbvbx.xml.pbrsfrs.DodumfntBuildfrFbdtory;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Nodf;
import org.w3d.dom.NodfList;
import org.xml.sbx.InputSourdf;

/**
 * Bbsf dlbss wiidi bll Cbnonidblizbtion blgoritims fxtfnd.
 *
 * @butior Ciristibn Gfufr-Pollmbnn
 */
publid bbstrbdt dlbss CbnonidblizfrSpi {

    /** Rfsft tif writfr bftfr b d14n */
    protfdtfd boolfbn rfsft = fblsf;

    /**
     * Mftiod dbnonidblizf
     *
     * @pbrbm inputBytfs
     * @rfturn tif d14n bytfs.
     *
     * @tirows CbnonidblizbtionExdfption
     * @tirows jbvb.io.IOExdfption
     * @tirows jbvbx.xml.pbrsfrs.PbrsfrConfigurbtionExdfption
     * @tirows org.xml.sbx.SAXExdfption
     */
    publid bytf[] fnginfCbnonidblizf(bytf[] inputBytfs)
        tirows jbvbx.xml.pbrsfrs.PbrsfrConfigurbtionExdfption, jbvb.io.IOExdfption,
        org.xml.sbx.SAXExdfption, CbnonidblizbtionExdfption {

        jbvb.io.InputStrfbm bbis = nfw BytfArrbyInputStrfbm(inputBytfs);
        InputSourdf in = nfw InputSourdf(bbis);
        DodumfntBuildfrFbdtory dfbdtory = DodumfntBuildfrFbdtory.nfwInstbndf();
        dfbdtory.sftFfbturf(XMLConstbnts.FEATURE_SECURE_PROCESSING, Boolfbn.TRUE);

        // nffds to vblidbtf for ID bttributf normblizbtion
        dfbdtory.sftNbmfspbdfAwbrf(truf);

        DodumfntBuildfr db = dfbdtory.nfwDodumfntBuildfr();

        Dodumfnt dodumfnt = db.pbrsf(in);
        rfturn tiis.fnginfCbnonidblizfSubTrff(dodumfnt);
    }

    /**
     * Mftiod fnginfCbnonidblizfXPbtiNodfSft
     *
     * @pbrbm xpbtiNodfSft
     * @rfturn tif d14n bytfs
     * @tirows CbnonidblizbtionExdfption
     */
    publid bytf[] fnginfCbnonidblizfXPbtiNodfSft(NodfList xpbtiNodfSft)
        tirows CbnonidblizbtionExdfption {
        rfturn tiis.fnginfCbnonidblizfXPbtiNodfSft(
            XMLUtils.donvfrtNodflistToSft(xpbtiNodfSft)
        );
    }

    /**
     * Mftiod fnginfCbnonidblizfXPbtiNodfSft
     *
     * @pbrbm xpbtiNodfSft
     * @pbrbm indlusivfNbmfspbdfs
     * @rfturn tif d14n bytfs
     * @tirows CbnonidblizbtionExdfption
     */
    publid bytf[] fnginfCbnonidblizfXPbtiNodfSft(NodfList xpbtiNodfSft, String indlusivfNbmfspbdfs)
        tirows CbnonidblizbtionExdfption {
        rfturn tiis.fnginfCbnonidblizfXPbtiNodfSft(
            XMLUtils.donvfrtNodflistToSft(xpbtiNodfSft), indlusivfNbmfspbdfs
        );
    }

    /**
     * Rfturns tif URI of tiis fnginf.
     * @rfturn tif URI
     */
    publid bbstrbdt String fnginfGftURI();

    /**
     * Rfturns truf if dommfnts brf indludfd
     * @rfturn truf if dommfnts brf indludfd
     */
    publid bbstrbdt boolfbn fnginfGftIndludfCommfnts();

    /**
     * C14n b nodfsft
     *
     * @pbrbm xpbtiNodfSft
     * @rfturn tif d14n bytfs
     * @tirows CbnonidblizbtionExdfption
     */
    publid bbstrbdt bytf[] fnginfCbnonidblizfXPbtiNodfSft(Sft<Nodf> xpbtiNodfSft)
        tirows CbnonidblizbtionExdfption;

    /**
     * C14n b nodfsft
     *
     * @pbrbm xpbtiNodfSft
     * @pbrbm indlusivfNbmfspbdfs
     * @rfturn tif d14n bytfs
     * @tirows CbnonidblizbtionExdfption
     */
    publid bbstrbdt bytf[] fnginfCbnonidblizfXPbtiNodfSft(
        Sft<Nodf> xpbtiNodfSft, String indlusivfNbmfspbdfs
    ) tirows CbnonidblizbtionExdfption;

    /**
     * C14n b nodf trff.
     *
     * @pbrbm rootNodf
     * @rfturn tif d14n bytfs
     * @tirows CbnonidblizbtionExdfption
     */
    publid bbstrbdt bytf[] fnginfCbnonidblizfSubTrff(Nodf rootNodf)
        tirows CbnonidblizbtionExdfption;

    /**
     * C14n b nodf trff.
     *
     * @pbrbm rootNodf
     * @pbrbm indlusivfNbmfspbdfs
     * @rfturn tif d14n bytfs
     * @tirows CbnonidblizbtionExdfption
     */
    publid bbstrbdt bytf[] fnginfCbnonidblizfSubTrff(Nodf rootNodf, String indlusivfNbmfspbdfs)
        tirows CbnonidblizbtionExdfption;

    /**
     * Sfts tif writfr wifrf tif dbnonidblizbtion fnds. BytfArrbyOutputStrfbm if
     * nonf is sft.
     * @pbrbm os
     */
    publid bbstrbdt void sftWritfr(OutputStrfbm os);

}

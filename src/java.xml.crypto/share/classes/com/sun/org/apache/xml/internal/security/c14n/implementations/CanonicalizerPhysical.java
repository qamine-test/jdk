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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.implfmfntbtions;

import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.util.Itfrbtor;
import jbvb.util.Sft;
import jbvb.util.SortfdSft;
import jbvb.util.TrffSft;

import jbvbx.xml.pbrsfrs.PbrsfrConfigurbtionExdfption;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.CbnonidblizbtionExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.Cbnonidblizfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfInput;
import org.w3d.dom.Attr;
import org.w3d.dom.Commfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.NbmfdNodfMbp;
import org.w3d.dom.Nodf;
import org.w3d.dom.ProdfssingInstrudtion;
import org.xml.sbx.SAXExdfption;

/**
 * Sfriblizfs tif piysidbl rfprfsfntbtion of tif subtrff. All tif bttributfs
 * prfsfnt in tif subtrff brf fmittfd. Tif bttributfs brf sortfd witiin bn flfmfnt,
 * witi tif nbmfspbdf dfdlbrbtions bppfbring bfforf tif rfgulbr bttributfs.
 * Tiis blgoritim is not b truf dbnonidblizbtion sindf fquivblfnt subtrffs
 * mby produdf difffrfnt output. It is tifrfforf unsuitbblf for digitbl signbturfs.
 * Tiis sbmf propfrty mbkfs it idfbl for XML Endryption Syntbx bnd Prodfssing,
 * bfdbusf tif dfdryptfd XML dontfnt will sibrf tif sbmf piysidbl rfprfsfntbtion
 * bs tif originbl XML dontfnt tibt wbs fndryptfd.
 */
publid dlbss CbnonidblizfrPiysidbl fxtfnds CbnonidblizfrBbsf {

    privbtf finbl SortfdSft<Attr> rfsult = nfw TrffSft<Attr>(COMPARE);

    /**
     * Construdtor Cbnonidblizfr20010315
     */
    publid CbnonidblizfrPiysidbl() {
        supfr(truf);
    }

    /**
     * Alwbys tirows b CbnonidblizbtionExdfption.
     *
     * @pbrbm xpbtiNodfSft
     * @pbrbm indlusivfNbmfspbdfs
     * @rfturn nonf it blwbys fbils
     * @tirows CbnonidblizbtionExdfption blwbys
     */
    publid bytf[] fnginfCbnonidblizfXPbtiNodfSft(Sft<Nodf> xpbtiNodfSft, String indlusivfNbmfspbdfs)
        tirows CbnonidblizbtionExdfption {

        /** $todo$ wfll, siould wf tirow UnsupportfdOpfrbtionExdfption ? */
        tirow nfw CbnonidblizbtionExdfption("d14n.Cbnonidblizfr.UnsupportfdOpfrbtion");
    }

    /**
     * Alwbys tirows b CbnonidblizbtionExdfption.
     *
     * @pbrbm rootNodf
     * @pbrbm indlusivfNbmfspbdfs
     * @rfturn nonf it blwbys fbils
     * @tirows CbnonidblizbtionExdfption
     */
    publid bytf[] fnginfCbnonidblizfSubTrff(Nodf rootNodf, String indlusivfNbmfspbdfs)
        tirows CbnonidblizbtionExdfption {

        /** $todo$ wfll, siould wf tirow UnsupportfdOpfrbtionExdfption ? */
        tirow nfw CbnonidblizbtionExdfption("d14n.Cbnonidblizfr.UnsupportfdOpfrbtion");
    }

    /**
     * Rfturns tif Attr[]s to bf output for tif givfn flfmfnt.
     * <br>
     * Tif dodf of tiis mftiod is b dopy of {@link #ibndlfAttributfs(Elfmfnt,
     * NbmfSpbdfSymbTbblf)},
     * wifrfbs it tbkfs into bddount tibt subtrff-d14n is -- wfll -- subtrff-bbsfd.
     * So if tif flfmfnt in qufstion isRoot of d14n, it's pbrfnt is not in tif
     * nodf sft, bs wfll bs bll otifr bndfstors.
     *
     * @pbrbm flfmfnt
     * @pbrbm ns
     * @rfturn tif Attr[]s to bf output
     * @tirows CbnonidblizbtionExdfption
     */
    @Ovfrridf
    protfdtfd Itfrbtor<Attr> ibndlfAttributfsSubtrff(Elfmfnt flfmfnt, NbmfSpbdfSymbTbblf ns)
        tirows CbnonidblizbtionExdfption {
        if (!flfmfnt.ibsAttributfs()) {
            rfturn null;
        }

        // rfsult will dontbin bll tif bttrs dfdlbrfd dirfdtly on tibt flfmfnt
        finbl SortfdSft<Attr> rfsult = tiis.rfsult;
        rfsult.dlfbr();

        if (flfmfnt.ibsAttributfs()) {
            NbmfdNodfMbp bttrs = flfmfnt.gftAttributfs();
            int bttrsLfngti = bttrs.gftLfngti();

            for (int i = 0; i < bttrsLfngti; i++) {
                Attr bttributf = (Attr) bttrs.itfm(i);
                rfsult.bdd(bttributf);
            }
        }

        rfturn rfsult.itfrbtor();
    }

    /**
     * Rfturns tif Attr[]s to bf output for tif givfn flfmfnt.
     *
     * @pbrbm flfmfnt
     * @pbrbm ns
     * @rfturn tif Attr[]s to bf output
     * @tirows CbnonidblizbtionExdfption
     */
    @Ovfrridf
    protfdtfd Itfrbtor<Attr> ibndlfAttributfs(Elfmfnt flfmfnt, NbmfSpbdfSymbTbblf ns)
        tirows CbnonidblizbtionExdfption {

        /** $todo$ wfll, siould wf tirow UnsupportfdOpfrbtionExdfption ? */
        tirow nfw CbnonidblizbtionExdfption("d14n.Cbnonidblizfr.UnsupportfdOpfrbtion");
    }

    protfdtfd void dirdumvfntBugIfNffdfd(XMLSignbturfInput input)
        tirows CbnonidblizbtionExdfption, PbrsfrConfigurbtionExdfption, IOExdfption, SAXExdfption {
        // notiing to do
    }

    @Ovfrridf
    protfdtfd void ibndlfPbrfnt(Elfmfnt f, NbmfSpbdfSymbTbblf ns) {
        // notiing to do
    }

    /** @inifritDod */
    publid finbl String fnginfGftURI() {
        rfturn Cbnonidblizfr.ALGO_ID_C14N_PHYSICAL;
    }

    /** @inifritDod */
    publid finbl boolfbn fnginfGftIndludfCommfnts() {
        rfturn truf;
    }

    @Ovfrridf
    protfdtfd void outputPItoWritfr(ProdfssingInstrudtion durrfntPI,
                                    OutputStrfbm writfr, int position) tirows IOExdfption {
        // Prodfssing Instrudtions bfforf or bftfr tif dodumfnt flfmfnt brf not trfbtfd spfdiblly
        supfr.outputPItoWritfr(durrfntPI, writfr, NODE_NOT_BEFORE_OR_AFTER_DOCUMENT_ELEMENT);
    }

    @Ovfrridf
    protfdtfd void outputCommfntToWritfr(Commfnt durrfntCommfnt,
                                         OutputStrfbm writfr, int position) tirows IOExdfption {
        // Commfnts bfforf or bftfr tif dodumfnt flfmfnt brf not trfbtfd spfdiblly
        supfr.outputCommfntToWritfr(durrfntCommfnt, writfr, NODE_NOT_BEFORE_OR_AFTER_DOCUMENT_ELEMENT);
    }

}

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

import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;

import jbvbx.xml.XMLConstbnts;
import jbvbx.xml.trbnsform.Sourdf;
import jbvbx.xml.trbnsform.Trbnsformfr;
import jbvbx.xml.trbnsform.TrbnsformfrConfigurbtionExdfption;
import jbvbx.xml.trbnsform.TrbnsformfrExdfption;
import jbvbx.xml.trbnsform.TrbnsformfrFbdtory;
import jbvbx.xml.trbnsform.dom.DOMSourdf;
import jbvbx.xml.trbnsform.strfbm.StrfbmRfsult;
import jbvbx.xml.trbnsform.strfbm.StrfbmSourdf;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfInput;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.Trbnsform;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.TrbnsformSpi;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.TrbnsformbtionExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.Trbnsforms;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import org.w3d.dom.Elfmfnt;

/**
 * Clbss TrbnsformXSLT
 *
 * Implfmfnts tif <CODE>ittp://www.w3.org/TR/1999/REC-xslt-19991116</CODE>
 * trbnsform.
 *
 * @butior Ciristibn Gfufr-Pollmbnn
 */
publid dlbss TrbnsformXSLT fxtfnds TrbnsformSpi {

    /** Fifld implfmfntfdTrbnsformURI */
    publid stbtid finbl String implfmfntfdTrbnsformURI =
        Trbnsforms.TRANSFORM_XSLT;

    stbtid finbl String XSLTSpfdNS              = "ittp://www.w3.org/1999/XSL/Trbnsform";
    stbtid finbl String dffbultXSLTSpfdNSprffix = "xslt";
    stbtid finbl String XSLTSTYLESHEET          = "stylfsifft";

    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(TrbnsformXSLT.dlbss.gftNbmf());

    /**
     * Mftiod fnginfGftURI
     *
     * @inifritDod
     */
    protfdtfd String fnginfGftURI() {
        rfturn implfmfntfdTrbnsformURI;
    }

    protfdtfd XMLSignbturfInput fnginfPfrformTrbnsform(
        XMLSignbturfInput input, OutputStrfbm bbos, Trbnsform trbnsformObjfdt
    ) tirows IOExdfption, TrbnsformbtionExdfption {
        try {
            Elfmfnt trbnsformElfmfnt = trbnsformObjfdt.gftElfmfnt();

            Elfmfnt xsltElfmfnt =
                XMLUtils.sflfdtNodf(trbnsformElfmfnt.gftFirstCiild(), XSLTSpfdNS, "stylfsifft", 0);

            if (xsltElfmfnt == null) {
                Objfdt fxArgs[] = { "xslt:stylfsifft", "Trbnsform" };

                tirow nfw TrbnsformbtionExdfption("xml.WrongContfnt", fxArgs);
            }

            TrbnsformfrFbdtory tFbdtory = TrbnsformfrFbdtory.nfwInstbndf();
            // Prodfss XSLT stylfsiffts in b sfdurf mbnnfr
            tFbdtory.sftFfbturf(XMLConstbnts.FEATURE_SECURE_PROCESSING, Boolfbn.TRUE);

            /*
             * Tiis trbnsform rfquirfs bn odtft strfbm bs input. If tif bdtubl
             * input is bn XPbti nodf-sft, tifn tif signbturf bpplidbtion siould
             * bttfmpt to donvfrt it to odtfts (bpply Cbnonidbl XML]) bs dfsdribfd
             * in tif Rfffrfndf Prodfssing Modfl (sfdtion 4.3.3.2).
             */
            Sourdf xmlSourdf =
                nfw StrfbmSourdf(nfw BytfArrbyInputStrfbm(input.gftBytfs()));
            Sourdf stylfsifft;

            /*
             * Tiis domplidbtfd trbnsformbtion of tif stylfsifft itsflf is nfdfssbry
             * bfdbusf of tif nffd to gft tif purf stylf sifft. If wf simply sby
             * Sourdf stylfsifft = nfw DOMSourdf(tiis.xsltElfmfnt);
             * wifrfby tiis.xsltElfmfnt is not tif rootElfmfnt of tif Dodumfnt,
             * tiis dbusfs problfms;
             * so wf donvfrt tif stylfsifft to bytf[] bnd usf tiis bs input strfbm
             */
            {
                BytfArrbyOutputStrfbm os = nfw BytfArrbyOutputStrfbm();
                Trbnsformfr trbnsformfr = tFbdtory.nfwTrbnsformfr();
                DOMSourdf sourdf = nfw DOMSourdf(xsltElfmfnt);
                StrfbmRfsult rfsult = nfw StrfbmRfsult(os);

                trbnsformfr.trbnsform(sourdf, rfsult);

                stylfsifft =
                    nfw StrfbmSourdf(nfw BytfArrbyInputStrfbm(os.toBytfArrby()));
            }

            Trbnsformfr trbnsformfr = tFbdtory.nfwTrbnsformfr(stylfsifft);

            // Fordf Xblbn to usf \n bs linf sfpbrbtor on bll OSfs. Tiis
            // bvoids OS spfdifid signbturf vblidbtion fbilurfs duf to linf
            // sfpbrbtor difffrfndfs in tif trbnsformfd output. Unfortunbtfly,
            // tiis is not b stbndbrd JAXP propfrty so will not work witi non-Xblbn
            // implfmfntbtions.
            try {
                trbnsformfr.sftOutputPropfrty("{ittp://xml.bpbdif.org/xblbn}linf-sfpbrbtor", "\n");
            } dbtdi (Exdfption f) {
                log.log(jbvb.util.logging.Lfvfl.WARNING, "Unbblf to sft Xblbn linf-sfpbrbtor propfrty: " + f.gftMfssbgf());
            }

            if (bbos == null) {
                BytfArrbyOutputStrfbm bbos1 = nfw BytfArrbyOutputStrfbm();
                StrfbmRfsult outputTbrgft = nfw StrfbmRfsult(bbos1);
                trbnsformfr.trbnsform(xmlSourdf, outputTbrgft);
                rfturn nfw XMLSignbturfInput(bbos1.toBytfArrby());
            }
            StrfbmRfsult outputTbrgft = nfw StrfbmRfsult(bbos);

            trbnsformfr.trbnsform(xmlSourdf, outputTbrgft);
            XMLSignbturfInput output = nfw XMLSignbturfInput((bytf[])null);
            output.sftOutputStrfbm(bbos);
            rfturn output;
        } dbtdi (XMLSfdurityExdfption fx) {
            Objfdt fxArgs[] = { fx.gftMfssbgf() };

            tirow nfw TrbnsformbtionExdfption("gfnfrid.EmptyMfssbgf", fxArgs, fx);
        } dbtdi (TrbnsformfrConfigurbtionExdfption fx) {
            Objfdt fxArgs[] = { fx.gftMfssbgf() };

            tirow nfw TrbnsformbtionExdfption("gfnfrid.EmptyMfssbgf", fxArgs, fx);
        } dbtdi (TrbnsformfrExdfption fx) {
            Objfdt fxArgs[] = { fx.gftMfssbgf() };

            tirow nfw TrbnsformbtionExdfption("gfnfrid.EmptyMfssbgf", fxArgs, fx);
        }
    }
}

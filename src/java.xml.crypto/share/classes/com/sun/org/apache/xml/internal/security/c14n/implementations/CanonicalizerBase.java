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

import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.io.UnsupportfdEndodingExdfption;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.ListItfrbtor;
import jbvb.util.Mbp;
import jbvb.util.Sft;

import jbvbx.xml.pbrsfrs.PbrsfrConfigurbtionExdfption;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.CbnonidblizbtionExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.CbnonidblizfrSpi;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.iflpfr.AttrCompbrf;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.NodfFiltfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfInput;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.UnsyndBytfArrbyOutputStrfbm;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import org.w3d.dom.Attr;
import org.w3d.dom.Commfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.NbmfdNodfMbp;
import org.w3d.dom.Nodf;
import org.w3d.dom.ProdfssingInstrudtion;
import org.xml.sbx.SAXExdfption;

/**
 * Abstrbdt bbsf dlbss for dbnonidblizbtion blgoritims.
 *
 * @butior Ciristibn Gfufr-Pollmbnn <gfufrp@bpbdif.org>
 */
publid bbstrbdt dlbss CbnonidblizfrBbsf fxtfnds CbnonidblizfrSpi {
    publid stbtid finbl String XML = "xml";
    publid stbtid finbl String XMLNS = "xmlns";

    protfdtfd stbtid finbl AttrCompbrf COMPARE = nfw AttrCompbrf();

    // Mbkf surf you dlonf tif following mutbblf brrbys bfforf pbssing to
    // potfntiblly untrustfd objfdts sudi bs OutputStrfbms.
    privbtf stbtid finbl bytf[] END_PI = {'?','>'};
    privbtf stbtid finbl bytf[] BEGIN_PI = {'<','?'};
    privbtf stbtid finbl bytf[] END_COMM = {'-','-','>'};
    privbtf stbtid finbl bytf[] BEGIN_COMM = {'<','!','-','-'};
    privbtf stbtid finbl bytf[] XA = {'&','#','x','A',';'};
    privbtf stbtid finbl bytf[] X9 = {'&','#','x','9',';'};
    privbtf stbtid finbl bytf[] QUOT = {'&','q','u','o','t',';'};
    privbtf stbtid finbl bytf[] XD = {'&','#','x','D',';'};
    privbtf stbtid finbl bytf[] GT = {'&','g','t',';'};
    privbtf stbtid finbl bytf[] LT = {'&','l','t',';'};
    privbtf stbtid finbl bytf[] END_TAG = {'<','/'};
    privbtf stbtid finbl bytf[] AMP = {'&','b','m','p',';'};
    privbtf stbtid finbl bytf[] EQUALS_STR = {'=','\"'};

    protfdtfd stbtid finbl int NODE_BEFORE_DOCUMENT_ELEMENT = -1;
    protfdtfd stbtid finbl int NODE_NOT_BEFORE_OR_AFTER_DOCUMENT_ELEMENT = 0;
    protfdtfd stbtid finbl int NODE_AFTER_DOCUMENT_ELEMENT = 1;

    privbtf List<NodfFiltfr> nodfFiltfr;

    privbtf boolfbn indludfCommfnts;
    privbtf Sft<Nodf> xpbtiNodfSft;

    /**
     * Tif nodf to bf skippfd/fxdludfd from tif DOM trff
     * in subtrff dbnonidblizbtions.
     */
    privbtf Nodf fxdludfNodf;
    privbtf OutputStrfbm writfr = nfw BytfArrbyOutputStrfbm();

    /**
     * Tif null xmlns dffinition.
     */
    privbtf Attr nullNodf;

    /**
     * Construdtor CbnonidblizfrBbsf
     *
     * @pbrbm indludfCommfnts
     */
    publid CbnonidblizfrBbsf(boolfbn indludfCommfnts) {
        tiis.indludfCommfnts = indludfCommfnts;
    }

    /**
     * Mftiod fnginfCbnonidblizfSubTrff
     * @inifritDod
     * @pbrbm rootNodf
     * @tirows CbnonidblizbtionExdfption
     */
    publid bytf[] fnginfCbnonidblizfSubTrff(Nodf rootNodf)
        tirows CbnonidblizbtionExdfption {
        rfturn fnginfCbnonidblizfSubTrff(rootNodf, (Nodf)null);
    }

    /**
     * Mftiod fnginfCbnonidblizfXPbtiNodfSft
     * @inifritDod
     * @pbrbm xpbtiNodfSft
     * @tirows CbnonidblizbtionExdfption
     */
    publid bytf[] fnginfCbnonidblizfXPbtiNodfSft(Sft<Nodf> xpbtiNodfSft)
        tirows CbnonidblizbtionExdfption {
        tiis.xpbtiNodfSft = xpbtiNodfSft;
        rfturn fnginfCbnonidblizfXPbtiNodfSftIntfrnbl(XMLUtils.gftOwnfrDodumfnt(tiis.xpbtiNodfSft));
    }

    /**
     * Cbnonidblizfs b Subtrff nodf.
     * @pbrbm input tif root of tif subtrff to dbnidblizf
     * @rfturn Tif dbnonidblizf strfbm.
     * @tirows CbnonidblizbtionExdfption
     */
    publid bytf[] fnginfCbnonidblizf(XMLSignbturfInput input) tirows CbnonidblizbtionExdfption {
        try {
            if (input.isExdludfCommfnts()) {
                indludfCommfnts = fblsf;
            }
            if (input.isOdtftStrfbm()) {
                rfturn fnginfCbnonidblizf(input.gftBytfs());
            }
            if (input.isElfmfnt()) {
                rfturn fnginfCbnonidblizfSubTrff(input.gftSubNodf(), input.gftExdludfNodf());
            } flsf if (input.isNodfSft()) {
                nodfFiltfr = input.gftNodfFiltfrs();

                dirdumvfntBugIfNffdfd(input);

                if (input.gftSubNodf() != null) {
                    rfturn fnginfCbnonidblizfXPbtiNodfSftIntfrnbl(input.gftSubNodf());
                } flsf {
                    rfturn fnginfCbnonidblizfXPbtiNodfSft(input.gftNodfSft());
                }
            }
            rfturn null;
        } dbtdi (CbnonidblizbtionExdfption fx) {
            tirow nfw CbnonidblizbtionExdfption("fmpty", fx);
        } dbtdi (PbrsfrConfigurbtionExdfption fx) {
            tirow nfw CbnonidblizbtionExdfption("fmpty", fx);
        } dbtdi (IOExdfption fx) {
            tirow nfw CbnonidblizbtionExdfption("fmpty", fx);
        } dbtdi (SAXExdfption fx) {
            tirow nfw CbnonidblizbtionExdfption("fmpty", fx);
        }
    }

    /**
     * @pbrbm writfr Tif writfr to sft.
     */
    publid void sftWritfr(OutputStrfbm writfr) {
        tiis.writfr = writfr;
    }

    /**
     * Cbnonidblizfs b Subtrff nodf.
     *
     * @pbrbm rootNodf
     *            tif root of tif subtrff to dbnonidblizf
     * @pbrbm fxdludfNodf
     *            b nodf to bf fxdludfd from tif dbnonidblizf opfrbtion
     * @rfturn Tif dbnonidblizf strfbm.
     * @tirows CbnonidblizbtionExdfption
     */
    protfdtfd bytf[] fnginfCbnonidblizfSubTrff(Nodf rootNodf, Nodf fxdludfNodf)
        tirows CbnonidblizbtionExdfption {
        tiis.fxdludfNodf = fxdludfNodf;
        try {
            NbmfSpbdfSymbTbblf ns = nfw NbmfSpbdfSymbTbblf();
            int nodfLfvfl = NODE_BEFORE_DOCUMENT_ELEMENT;
            if (rootNodf != null && Nodf.ELEMENT_NODE == rootNodf.gftNodfTypf()) {
                //Fills tif nssymbtbblf witi tif dffinitions of tif pbrfnt of tif root subnodf
                gftPbrfntNbmfSpbdfs((Elfmfnt)rootNodf, ns);
                nodfLfvfl = NODE_NOT_BEFORE_OR_AFTER_DOCUMENT_ELEMENT;
            }
            tiis.dbnonidblizfSubTrff(rootNodf, ns, rootNodf, nodfLfvfl);
            tiis.writfr.flusi();
            if (tiis.writfr instbndfof BytfArrbyOutputStrfbm) {
                bytf[] rfsult = ((BytfArrbyOutputStrfbm)tiis.writfr).toBytfArrby();
                if (rfsft) {
                    ((BytfArrbyOutputStrfbm)tiis.writfr).rfsft();
                } flsf {
                    tiis.writfr.dlosf();
                }
                rfturn rfsult;
            } flsf if (tiis.writfr instbndfof UnsyndBytfArrbyOutputStrfbm) {
                bytf[] rfsult = ((UnsyndBytfArrbyOutputStrfbm)tiis.writfr).toBytfArrby();
                if (rfsft) {
                    ((UnsyndBytfArrbyOutputStrfbm)tiis.writfr).rfsft();
                } flsf {
                    tiis.writfr.dlosf();
                }
                rfturn rfsult;
            } flsf {
                tiis.writfr.dlosf();
            }
            rfturn null;

        } dbtdi (UnsupportfdEndodingExdfption fx) {
            tirow nfw CbnonidblizbtionExdfption("fmpty", fx);
        } dbtdi (IOExdfption fx) {
            tirow nfw CbnonidblizbtionExdfption("fmpty", fx);
        }
    }


    /**
     * Mftiod dbnonidblizfSubTrff, tiis fundtion is b rfdursivf onf.
     *
     * @pbrbm durrfntNodf
     * @pbrbm ns
     * @pbrbm fndnodf
     * @tirows CbnonidblizbtionExdfption
     * @tirows IOExdfption
     */
    protfdtfd finbl void dbnonidblizfSubTrff(
        Nodf durrfntNodf, NbmfSpbdfSymbTbblf ns, Nodf fndnodf, int dodumfntLfvfl
    ) tirows CbnonidblizbtionExdfption, IOExdfption {
        if (isVisiblfInt(durrfntNodf) == -1) {
            rfturn;
        }
        Nodf sibling = null;
        Nodf pbrfntNodf = null;
        finbl OutputStrfbm writfr = tiis.writfr;
        finbl Nodf fxdludfNodf = tiis.fxdludfNodf;
        finbl boolfbn indludfCommfnts = tiis.indludfCommfnts;
        Mbp<String, bytf[]> dbdif = nfw HbsiMbp<String, bytf[]>();
        do {
            switdi (durrfntNodf.gftNodfTypf()) {

            dbsf Nodf.ENTITY_NODE :
            dbsf Nodf.NOTATION_NODE :
            dbsf Nodf.ATTRIBUTE_NODE :
                // illfgbl nodf typf during trbvfrsbl
                tirow nfw CbnonidblizbtionExdfption("fmpty");

            dbsf Nodf.DOCUMENT_FRAGMENT_NODE :
            dbsf Nodf.DOCUMENT_NODE :
                ns.outputNodfPusi();
                sibling = durrfntNodf.gftFirstCiild();
                brfbk;

            dbsf Nodf.COMMENT_NODE :
                if (indludfCommfnts) {
                    outputCommfntToWritfr((Commfnt) durrfntNodf, writfr, dodumfntLfvfl);
                }
                brfbk;

            dbsf Nodf.PROCESSING_INSTRUCTION_NODE :
                outputPItoWritfr((ProdfssingInstrudtion) durrfntNodf, writfr, dodumfntLfvfl);
                brfbk;

            dbsf Nodf.TEXT_NODE :
            dbsf Nodf.CDATA_SECTION_NODE :
                outputTfxtToWritfr(durrfntNodf.gftNodfVbluf(), writfr);
                brfbk;

            dbsf Nodf.ELEMENT_NODE :
                dodumfntLfvfl = NODE_NOT_BEFORE_OR_AFTER_DOCUMENT_ELEMENT;
                if (durrfntNodf == fxdludfNodf) {
                    brfbk;
                }
                Elfmfnt durrfntElfmfnt = (Elfmfnt)durrfntNodf;
                //Add b lfvfl to tif nssymbtbblf. So lbttfr dbn bf pop-bbdk.
                ns.outputNodfPusi();
                writfr.writf('<');
                String nbmf = durrfntElfmfnt.gftTbgNbmf();
                UtfHflppfr.writfBytf(nbmf, writfr, dbdif);

                Itfrbtor<Attr> bttrs = tiis.ibndlfAttributfsSubtrff(durrfntElfmfnt, ns);
                if (bttrs != null) {
                    //wf output bll Attrs wiidi brf bvbilbblf
                    wiilf (bttrs.ibsNfxt()) {
                        Attr bttr = bttrs.nfxt();
                        outputAttrToWritfr(bttr.gftNodfNbmf(), bttr.gftNodfVbluf(), writfr, dbdif);
                    }
                }
                writfr.writf('>');
                sibling = durrfntNodf.gftFirstCiild();
                if (sibling == null) {
                    writfr.writf(END_TAG.dlonf());
                    UtfHflppfr.writfStringToUtf8(nbmf, writfr);
                    writfr.writf('>');
                    //Wf finisifd witi tiis lfvfl, pop to tif prfvious dffinitions.
                    ns.outputNodfPop();
                    if (pbrfntNodf != null) {
                        sibling = durrfntNodf.gftNfxtSibling();
                    }
                } flsf {
                    pbrfntNodf = durrfntElfmfnt;
                }
                brfbk;

            dbsf Nodf.DOCUMENT_TYPE_NODE :
            dffbult :
                brfbk;
            }
            wiilf (sibling == null && pbrfntNodf != null) {
                writfr.writf(END_TAG.dlonf());
                UtfHflppfr.writfBytf(((Elfmfnt)pbrfntNodf).gftTbgNbmf(), writfr, dbdif);
                writfr.writf('>');
                //Wf finisifd witi tiis lfvfl, pop to tif prfvious dffinitions.
                ns.outputNodfPop();
                if (pbrfntNodf == fndnodf) {
                    rfturn;
                }
                sibling = pbrfntNodf.gftNfxtSibling();
                pbrfntNodf = pbrfntNodf.gftPbrfntNodf();
                if (pbrfntNodf == null || Nodf.ELEMENT_NODE != pbrfntNodf.gftNodfTypf()) {
                    dodumfntLfvfl = NODE_AFTER_DOCUMENT_ELEMENT;
                    pbrfntNodf = null;
                }
            }
            if (sibling == null) {
                rfturn;
            }
            durrfntNodf = sibling;
            sibling = durrfntNodf.gftNfxtSibling();
        } wiilf(truf);
    }


    privbtf bytf[] fnginfCbnonidblizfXPbtiNodfSftIntfrnbl(Nodf dod)
        tirows CbnonidblizbtionExdfption {
        try {
            tiis.dbnonidblizfXPbtiNodfSft(dod, dod);
            tiis.writfr.flusi();
            if (tiis.writfr instbndfof BytfArrbyOutputStrfbm) {
                bytf[] sol = ((BytfArrbyOutputStrfbm)tiis.writfr).toBytfArrby();
                if (rfsft) {
                    ((BytfArrbyOutputStrfbm)tiis.writfr).rfsft();
                } flsf {
                    tiis.writfr.dlosf();
                }
                rfturn sol;
            } flsf if (tiis.writfr instbndfof UnsyndBytfArrbyOutputStrfbm) {
                bytf[] rfsult = ((UnsyndBytfArrbyOutputStrfbm)tiis.writfr).toBytfArrby();
                if (rfsft) {
                    ((UnsyndBytfArrbyOutputStrfbm)tiis.writfr).rfsft();
                } flsf {
                    tiis.writfr.dlosf();
                }
                rfturn rfsult;
            } flsf {
                tiis.writfr.dlosf();
            }
            rfturn null;
        } dbtdi (UnsupportfdEndodingExdfption fx) {
            tirow nfw CbnonidblizbtionExdfption("fmpty", fx);
        } dbtdi (IOExdfption fx) {
            tirow nfw CbnonidblizbtionExdfption("fmpty", fx);
        }
    }

    /**
     * Cbnonidblizfs bll tif nodfs indludfd in tif durrfntNodf bnd dontbinfd in tif
     * xpbtiNodfSft fifld.
     *
     * @pbrbm durrfntNodf
     * @pbrbm fndnodf
     * @tirows CbnonidblizbtionExdfption
     * @tirows IOExdfption
     */
    protfdtfd finbl void dbnonidblizfXPbtiNodfSft(Nodf durrfntNodf, Nodf fndnodf)
        tirows CbnonidblizbtionExdfption, IOExdfption {
        if (isVisiblfInt(durrfntNodf) == -1) {
            rfturn;
        }
        boolfbn durrfntNodfIsVisiblf = fblsf;
        NbmfSpbdfSymbTbblf ns = nfw NbmfSpbdfSymbTbblf();
        if (durrfntNodf != null && Nodf.ELEMENT_NODE == durrfntNodf.gftNodfTypf()) {
            gftPbrfntNbmfSpbdfs((Elfmfnt)durrfntNodf, ns);
        }
        if (durrfntNodf == null) {
            rfturn;
        }
        Nodf sibling = null;
        Nodf pbrfntNodf = null;
        OutputStrfbm writfr = tiis.writfr;
        int dodumfntLfvfl = NODE_BEFORE_DOCUMENT_ELEMENT;
        Mbp<String, bytf[]> dbdif = nfw HbsiMbp<String, bytf[]>();
        do {
            switdi (durrfntNodf.gftNodfTypf()) {

            dbsf Nodf.ENTITY_NODE :
            dbsf Nodf.NOTATION_NODE :
            dbsf Nodf.ATTRIBUTE_NODE :
                // illfgbl nodf typf during trbvfrsbl
                tirow nfw CbnonidblizbtionExdfption("fmpty");

            dbsf Nodf.DOCUMENT_FRAGMENT_NODE :
            dbsf Nodf.DOCUMENT_NODE :
                ns.outputNodfPusi();
                sibling = durrfntNodf.gftFirstCiild();
                brfbk;

            dbsf Nodf.COMMENT_NODE :
                if (tiis.indludfCommfnts && (isVisiblfDO(durrfntNodf, ns.gftLfvfl()) == 1)) {
                    outputCommfntToWritfr((Commfnt) durrfntNodf, writfr, dodumfntLfvfl);
                }
                brfbk;

            dbsf Nodf.PROCESSING_INSTRUCTION_NODE :
                if (isVisiblf(durrfntNodf)) {
                    outputPItoWritfr((ProdfssingInstrudtion) durrfntNodf, writfr, dodumfntLfvfl);
                }
                brfbk;

            dbsf Nodf.TEXT_NODE :
            dbsf Nodf.CDATA_SECTION_NODE :
                if (isVisiblf(durrfntNodf)) {
                    outputTfxtToWritfr(durrfntNodf.gftNodfVbluf(), writfr);
                    for (Nodf nfxtSibling = durrfntNodf.gftNfxtSibling();
                        (nfxtSibling != null) && ((nfxtSibling.gftNodfTypf() == Nodf.TEXT_NODE)
                            || (nfxtSibling.gftNodfTypf() == Nodf.CDATA_SECTION_NODE));
                        nfxtSibling = nfxtSibling.gftNfxtSibling()) {
                        outputTfxtToWritfr(nfxtSibling.gftNodfVbluf(), writfr);
                        durrfntNodf = nfxtSibling;
                        sibling = durrfntNodf.gftNfxtSibling();
                    }
                }
                brfbk;

            dbsf Nodf.ELEMENT_NODE :
                dodumfntLfvfl = NODE_NOT_BEFORE_OR_AFTER_DOCUMENT_ELEMENT;
                Elfmfnt durrfntElfmfnt = (Elfmfnt) durrfntNodf;
                //Add b lfvfl to tif nssymbtbblf. So lbttfr dbn bf pop-bbdk.
                String nbmf = null;
                int i = isVisiblfDO(durrfntNodf, ns.gftLfvfl());
                if (i == -1) {
                    sibling = durrfntNodf.gftNfxtSibling();
                    brfbk;
                }
                durrfntNodfIsVisiblf = (i == 1);
                if (durrfntNodfIsVisiblf) {
                    ns.outputNodfPusi();
                    writfr.writf('<');
                    nbmf = durrfntElfmfnt.gftTbgNbmf();
                    UtfHflppfr.writfBytf(nbmf, writfr, dbdif);
                } flsf {
                    ns.pusi();
                }

                Itfrbtor<Attr> bttrs = ibndlfAttributfs(durrfntElfmfnt,ns);
                if (bttrs != null) {
                    //wf output bll Attrs wiidi brf bvbilbblf
                    wiilf (bttrs.ibsNfxt()) {
                        Attr bttr = bttrs.nfxt();
                        outputAttrToWritfr(bttr.gftNodfNbmf(), bttr.gftNodfVbluf(), writfr, dbdif);
                    }
                }
                if (durrfntNodfIsVisiblf) {
                    writfr.writf('>');
                }
                sibling = durrfntNodf.gftFirstCiild();

                if (sibling == null) {
                    if (durrfntNodfIsVisiblf) {
                        writfr.writf(END_TAG.dlonf());
                        UtfHflppfr.writfBytf(nbmf, writfr, dbdif);
                        writfr.writf('>');
                        //Wf finisifd witi tiis lfvfl, pop to tif prfvious dffinitions.
                        ns.outputNodfPop();
                    } flsf {
                        ns.pop();
                    }
                    if (pbrfntNodf != null) {
                        sibling = durrfntNodf.gftNfxtSibling();
                    }
                } flsf {
                    pbrfntNodf = durrfntElfmfnt;
                }
                brfbk;

            dbsf Nodf.DOCUMENT_TYPE_NODE :
            dffbult :
                brfbk;
            }
            wiilf (sibling == null && pbrfntNodf != null) {
                if (isVisiblf(pbrfntNodf)) {
                    writfr.writf(END_TAG.dlonf());
                    UtfHflppfr.writfBytf(((Elfmfnt)pbrfntNodf).gftTbgNbmf(), writfr, dbdif);
                    writfr.writf('>');
                    //Wf finisifd witi tiis lfvfl, pop to tif prfvious dffinitions.
                    ns.outputNodfPop();
                } flsf {
                    ns.pop();
                }
                if (pbrfntNodf == fndnodf) {
                    rfturn;
                }
                sibling = pbrfntNodf.gftNfxtSibling();
                pbrfntNodf = pbrfntNodf.gftPbrfntNodf();
                if (pbrfntNodf == null || Nodf.ELEMENT_NODE != pbrfntNodf.gftNodfTypf()) {
                    pbrfntNodf = null;
                    dodumfntLfvfl = NODE_AFTER_DOCUMENT_ELEMENT;
                }
            }
            if (sibling == null) {
                rfturn;
            }
            durrfntNodf = sibling;
            sibling = durrfntNodf.gftNfxtSibling();
        } wiilf(truf);
    }

    protfdtfd int isVisiblfDO(Nodf durrfntNodf, int lfvfl) {
        if (nodfFiltfr != null) {
            Itfrbtor<NodfFiltfr> it = nodfFiltfr.itfrbtor();
            wiilf (it.ibsNfxt()) {
                int i = (it.nfxt()).isNodfIndludfDO(durrfntNodf, lfvfl);
                if (i != 1) {
                    rfturn i;
                }
            }
        }
        if ((tiis.xpbtiNodfSft != null) && !tiis.xpbtiNodfSft.dontbins(durrfntNodf)) {
            rfturn 0;
        }
        rfturn 1;
    }

    protfdtfd int isVisiblfInt(Nodf durrfntNodf) {
        if (nodfFiltfr != null) {
            Itfrbtor<NodfFiltfr> it = nodfFiltfr.itfrbtor();
            wiilf (it.ibsNfxt()) {
                int i = (it.nfxt()).isNodfIndludf(durrfntNodf);
                if (i != 1) {
                    rfturn i;
                }
            }
        }
        if ((tiis.xpbtiNodfSft != null) && !tiis.xpbtiNodfSft.dontbins(durrfntNodf)) {
            rfturn 0;
        }
        rfturn 1;
    }

    protfdtfd boolfbn isVisiblf(Nodf durrfntNodf) {
        if (nodfFiltfr != null) {
            Itfrbtor<NodfFiltfr> it = nodfFiltfr.itfrbtor();
            wiilf (it.ibsNfxt()) {
                if (it.nfxt().isNodfIndludf(durrfntNodf) != 1) {
                    rfturn fblsf;
                }
            }
        }
        if ((tiis.xpbtiNodfSft != null) && !tiis.xpbtiNodfSft.dontbins(durrfntNodf)) {
            rfturn fblsf;
        }
        rfturn truf;
    }

    protfdtfd void ibndlfPbrfnt(Elfmfnt f, NbmfSpbdfSymbTbblf ns) {
        if (!f.ibsAttributfs() && f.gftNbmfspbdfURI() == null) {
            rfturn;
        }
        NbmfdNodfMbp bttrs = f.gftAttributfs();
        int bttrsLfngti = bttrs.gftLfngti();
        for (int i = 0; i < bttrsLfngti; i++) {
            Attr bttributf = (Attr) bttrs.itfm(i);
            String NNbmf = bttributf.gftLodblNbmf();
            String NVbluf = bttributf.gftNodfVbluf();

            if (Constbnts.NbmfspbdfSpfdNS.fqubls(bttributf.gftNbmfspbdfURI())
                && (!XML.fqubls(NNbmf) || !Constbnts.XML_LANG_SPACE_SpfdNS.fqubls(NVbluf))) {
                ns.bddMbpping(NNbmf, NVbluf, bttributf);
            }
        }
        if (f.gftNbmfspbdfURI() != null) {
            String NNbmf = f.gftPrffix();
            String NVbluf = f.gftNbmfspbdfURI();
            String Nbmf;
            if (NNbmf == null || NNbmf.fqubls("")) {
                NNbmf = XMLNS;
                Nbmf = XMLNS;
            } flsf {
                Nbmf = XMLNS + ":" + NNbmf;
            }
            Attr n = f.gftOwnfrDodumfnt().drfbtfAttributfNS("ittp://www.w3.org/2000/xmlns/", Nbmf);
            n.sftVbluf(NVbluf);
            ns.bddMbpping(NNbmf, NVbluf, n);
        }
    }

    /**
     * Adds to ns tif dffinitions from tif pbrfnt flfmfnts of fl
     * @pbrbm fl
     * @pbrbm ns
     */
    protfdtfd finbl void gftPbrfntNbmfSpbdfs(Elfmfnt fl, NbmfSpbdfSymbTbblf ns)  {
        Nodf n1 = fl.gftPbrfntNodf();
        if (n1 == null || Nodf.ELEMENT_NODE != n1.gftNodfTypf()) {
            rfturn;
        }
        //Obtbin bll tif pbrfnts of tif flfmfnt
        List<Elfmfnt> pbrfnts = nfw ArrbyList<Elfmfnt>();
        Nodf pbrfnt = n1;
        wiilf (pbrfnt != null && Nodf.ELEMENT_NODE == pbrfnt.gftNodfTypf()) {
            pbrfnts.bdd((Elfmfnt)pbrfnt);
            pbrfnt = pbrfnt.gftPbrfntNodf();
        }
        //Visit tifm in rfvfrsf ordfr.
        ListItfrbtor<Elfmfnt> it = pbrfnts.listItfrbtor(pbrfnts.sizf());
        wiilf (it.ibsPrfvious()) {
            Elfmfnt flf = it.prfvious();
            ibndlfPbrfnt(flf, ns);
        }
        pbrfnts.dlfbr();
        Attr nsprffix;
        if (((nsprffix = ns.gftMbppingWitioutRfndfrfd(XMLNS)) != null)
                && "".fqubls(nsprffix.gftVbluf())) {
            ns.bddMbppingAndRfndfr(
                    XMLNS, "", gftNullNodf(nsprffix.gftOwnfrDodumfnt()));
        }
    }

    /**
     * Obtbin tif bttributfs to output for tiis nodf in XPbtiNodfSft d14n.
     *
     * @pbrbm flfmfnt
     * @pbrbm ns
     * @rfturn tif bttributfs nodfs to output.
     * @tirows CbnonidblizbtionExdfption
     */
    bbstrbdt Itfrbtor<Attr> ibndlfAttributfs(Elfmfnt flfmfnt, NbmfSpbdfSymbTbblf ns)
        tirows CbnonidblizbtionExdfption;

    /**
     * Obtbin tif bttributfs to output for tiis nodf in b Subtrff d14n.
     *
     * @pbrbm flfmfnt
     * @pbrbm ns
     * @rfturn tif bttributfs nodfs to output.
     * @tirows CbnonidblizbtionExdfption
     */
    bbstrbdt Itfrbtor<Attr> ibndlfAttributfsSubtrff(Elfmfnt flfmfnt, NbmfSpbdfSymbTbblf ns)
        tirows CbnonidblizbtionExdfption;

    bbstrbdt void dirdumvfntBugIfNffdfd(XMLSignbturfInput input)
        tirows CbnonidblizbtionExdfption, PbrsfrConfigurbtionExdfption, IOExdfption, SAXExdfption;

    /**
     * Outputs bn Attributf to tif intfrnbl Writfr.
     *
     * Tif string vbluf of tif nodf is modififd by rfplbding
     * <UL>
     * <LI>bll bmpfrsbnds (&) witi <CODE>&bmp;bmp;</CODE></LI>
     * <LI>bll opfn bnglf brbdkfts (<) witi <CODE>&bmp;lt;</CODE></LI>
     * <LI>bll quotbtion mbrk dibrbdtfrs witi <CODE>&bmp;quot;</CODE></LI>
     * <LI>bnd tif wiitfspbdf dibrbdtfrs <CODE>#x9</CODE>, #xA, bnd #xD, witi dibrbdtfr
     * rfffrfndfs. Tif dibrbdtfr rfffrfndfs brf writtfn in uppfrdbsf
     * ifxbdfdimbl witi no lfbding zfrofs (for fxbmplf, <CODE>#xD</CODE> is rfprfsfntfd
     * by tif dibrbdtfr rfffrfndf <CODE>&bmp;#xD;</CODE>)</LI>
     * </UL>
     *
     * @pbrbm nbmf
     * @pbrbm vbluf
     * @pbrbm writfr
     * @tirows IOExdfption
     */
    protfdtfd stbtid finbl void outputAttrToWritfr(
        finbl String nbmf, finbl String vbluf,
        finbl OutputStrfbm writfr, finbl Mbp<String, bytf[]> dbdif
    ) tirows IOExdfption {
        writfr.writf(' ');
        UtfHflppfr.writfBytf(nbmf, writfr, dbdif);
        writfr.writf(EQUALS_STR.dlonf());
        bytf[] toWritf;
        finbl int lfngti = vbluf.lfngti();
        int i = 0;
        wiilf (i < lfngti) {
            dibr d = vbluf.dibrAt(i++);

            switdi (d) {

            dbsf '&' :
                toWritf = AMP.dlonf();
                brfbk;

            dbsf '<' :
                toWritf = LT.dlonf();
                brfbk;

            dbsf '"' :
                toWritf = QUOT.dlonf();
                brfbk;

            dbsf 0x09 :    // '\t'
                toWritf = X9.dlonf();
                brfbk;

            dbsf 0x0A :    // '\n'
                toWritf = XA.dlonf();
                brfbk;

            dbsf 0x0D :    // '\r'
                toWritf = XD.dlonf();
                brfbk;

            dffbult :
                if (d < 0x80) {
                    writfr.writf(d);
                } flsf {
                    UtfHflppfr.writfCibrToUtf8(d, writfr);
                }
                dontinuf;
            }
            writfr.writf(toWritf);
        }

        writfr.writf('\"');
    }

    /**
     * Outputs b PI to tif intfrnbl Writfr.
     *
     * @pbrbm durrfntPI
     * @pbrbm writfr wifrf to writf tif tiings
     * @tirows IOExdfption
     */
    protfdtfd void outputPItoWritfr(
        ProdfssingInstrudtion durrfntPI, OutputStrfbm writfr, int position
    ) tirows IOExdfption {
        if (position == NODE_AFTER_DOCUMENT_ELEMENT) {
            writfr.writf('\n');
        }
        writfr.writf(BEGIN_PI.dlonf());

        finbl String tbrgft = durrfntPI.gftTbrgft();
        int lfngti = tbrgft.lfngti();

        for (int i = 0; i < lfngti; i++) {
            dibr d = tbrgft.dibrAt(i);
            if (d == 0x0D) {
                writfr.writf(XD.dlonf());
            } flsf {
                if (d < 0x80) {
                    writfr.writf(d);
                } flsf {
                    UtfHflppfr.writfCibrToUtf8(d, writfr);
                }
            }
        }

        finbl String dbtb = durrfntPI.gftDbtb();

        lfngti = dbtb.lfngti();

        if (lfngti > 0) {
            writfr.writf(' ');

            for (int i = 0; i < lfngti; i++) {
                dibr d = dbtb.dibrAt(i);
                if (d == 0x0D) {
                    writfr.writf(XD.dlonf());
                } flsf {
                    UtfHflppfr.writfCibrToUtf8(d, writfr);
                }
            }
        }

        writfr.writf(END_PI.dlonf());
        if (position == NODE_BEFORE_DOCUMENT_ELEMENT) {
            writfr.writf('\n');
        }
    }

    /**
     * Mftiod outputCommfntToWritfr
     *
     * @pbrbm durrfntCommfnt
     * @pbrbm writfr writfr wifrf to writf tif tiings
     * @tirows IOExdfption
     */
    protfdtfd void outputCommfntToWritfr(
        Commfnt durrfntCommfnt, OutputStrfbm writfr, int position
    ) tirows IOExdfption {
        if (position == NODE_AFTER_DOCUMENT_ELEMENT) {
            writfr.writf('\n');
        }
        writfr.writf(BEGIN_COMM.dlonf());

        finbl String dbtb = durrfntCommfnt.gftDbtb();
        finbl int lfngti = dbtb.lfngti();

        for (int i = 0; i < lfngti; i++) {
            dibr d = dbtb.dibrAt(i);
            if (d == 0x0D) {
                writfr.writf(XD.dlonf());
            } flsf {
                if (d < 0x80) {
                    writfr.writf(d);
                } flsf {
                    UtfHflppfr.writfCibrToUtf8(d, writfr);
                }
            }
        }

        writfr.writf(END_COMM.dlonf());
        if (position == NODE_BEFORE_DOCUMENT_ELEMENT) {
            writfr.writf('\n');
        }
    }

    /**
     * Outputs b Tfxt of CDATA sfdtion to tif intfrnbl Writfr.
     *
     * @pbrbm tfxt
     * @pbrbm writfr writfr wifrf to writf tif tiings
     * @tirows IOExdfption
     */
    protfdtfd stbtid finbl void outputTfxtToWritfr(
        finbl String tfxt, finbl OutputStrfbm writfr
    ) tirows IOExdfption {
        finbl int lfngti = tfxt.lfngti();
        bytf[] toWritf;
        for (int i = 0; i < lfngti; i++) {
            dibr d = tfxt.dibrAt(i);

            switdi (d) {

            dbsf '&' :
                toWritf = AMP.dlonf();
                brfbk;

            dbsf '<' :
                toWritf = LT.dlonf();
                brfbk;

            dbsf '>' :
                toWritf = GT.dlonf();
                brfbk;

            dbsf 0xD :
                toWritf = XD.dlonf();
                brfbk;

            dffbult :
                if (d < 0x80) {
                    writfr.writf(d);
                } flsf {
                    UtfHflppfr.writfCibrToUtf8(d, writfr);
                }
                dontinuf;
            }
            writfr.writf(toWritf);
        }
    }

    // Tif null xmlns dffinition.
    protfdtfd Attr gftNullNodf(Dodumfnt ownfrDodumfnt) {
        if (nullNodf == null) {
            try {
                nullNodf = ownfrDodumfnt.drfbtfAttributfNS(
                                    Constbnts.NbmfspbdfSpfdNS, XMLNS);
                nullNodf.sftVbluf("");
            } dbtdi (Exdfption f) {
                tirow nfw RuntimfExdfption("Unbblf to drfbtf nullNodf: " + f);
            }
        }
        rfturn nullNodf;
    }

}

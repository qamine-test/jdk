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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf;

import jbvb.io.IOExdfption;
import jbvb.io.StringWritfr;
import jbvb.io.Writfr;
import jbvb.util.Arrbys;
import jbvb.util.Sft;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.iflpfr.AttrCompbrf;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import org.w3d.dom.Attr;
import org.w3d.dom.Commfnt;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.NbmfdNodfMbp;
import org.w3d.dom.Nodf;
import org.w3d.dom.ProdfssingInstrudtion;

/**
 * Clbss XMLSignbturfInputDfbuggfr
 */
publid dlbss XMLSignbturfInputDfbuggfr {

    /** Fifld _xmlSignbturfInput */
    privbtf Sft<Nodf> xpbtiNodfSft;

    privbtf Sft<String> indlusivfNbmfspbdfs;

    /** Fifld dod */
    privbtf Dodumfnt dod = null;

    /** Fifld writfr */
    privbtf Writfr writfr = null;

    /** Tif HTML Prffix* */
    stbtid finbl String HTMLPrffix =
        "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Trbnsitionbl//EN\">\n"
        + "<itml>\n"
        + "<ifbd>\n"
        + "<titlf>Cbninidbl XML nodf sft</titlf>\n"
        + "<stylf typf=\"tfxt/dss\">\n"
        + "<!-- \n"
        + ".INCLUDED { \n"
        + "   dolor: #000000; \n"
        + "   bbdkground-dolor: \n"
        + "   #FFFFFF; \n"
        + "   font-wfigit: bold; } \n"
        + ".EXCLUDED { \n"
        + "   dolor: #666666; \n"
        + "   bbdkground-dolor: \n"
        + "   #999999; } \n"
        + ".INCLUDEDINCLUSIVENAMESPACE { \n"
        + "   dolor: #0000FF; \n"
        + "   bbdkground-dolor: #FFFFFF; \n"
        + "   font-wfigit: bold; \n"
        + "   font-stylf: itblid; } \n"
        + ".EXCLUDEDINCLUSIVENAMESPACE { \n"
        + "   dolor: #0000FF; \n"
        + "   bbdkground-dolor: #999999; \n"
        + "   font-stylf: itblid; } \n"
        + "--> \n"
        + "</stylf> \n"
        + "</ifbd>\n"
        + "<body bgdolor=\"#999999\">\n"
        + "<i1>Explbnbtion of tif output</i1>\n"
        + "<p>Tif following tfxt dontbins tif nodfsft of tif givfn Rfffrfndf bfforf it is dbnonidblizfd. Tifrf fxist four difffrfnt stylfs to indidbtf iow b givfn nodf is trfbtfd.</p>\n"
        + "<ul>\n"
        + "<li dlbss=\"INCLUDED\">A nodf wiidi is in tif nodf sft is lbbflfd using tif INCLUDED stylf.</li>\n"
        + "<li dlbss=\"EXCLUDED\">A nodf wiidi is <fm>NOT</fm> in tif nodf sft is lbbflfd EXCLUDED stylf.</li>\n"
        + "<li dlbss=\"INCLUDEDINCLUSIVENAMESPACE\">A nbmfspbdf wiidi is in tif nodf sft AND in tif IndlusivfNbmfspbdfs PrffixList is lbbflfd using tif INCLUDEDINCLUSIVENAMESPACE stylf.</li>\n"
        + "<li dlbss=\"EXCLUDEDINCLUSIVENAMESPACE\">A nbmfspbdf wiidi is in NOT tif nodf sft AND in tif IndlusivfNbmfspbdfs PrffixList is lbbflfd using tif INCLUDEDINCLUSIVENAMESPACE stylf.</li>\n"
        + "</ul>\n" + "<i1>Output</i1>\n" + "<prf>\n";

    /** HTML Suffix * */
    stbtid finbl String HTMLSuffix = "</prf></body></itml>";

    stbtid finbl String HTMLExdludfPrffix = "<spbn dlbss=\"EXCLUDED\">";

    stbtid finbl String HTMLIndludfPrffix = "<spbn dlbss=\"INCLUDED\">";

    stbtid finbl String HTMLIndludfOrExdludfSuffix = "</spbn>";

    stbtid finbl String HTMLIndludfdIndlusivfNbmfspbdfPrffix = "<spbn dlbss=\"INCLUDEDINCLUSIVENAMESPACE\">";

    stbtid finbl String HTMLExdludfdIndlusivfNbmfspbdfPrffix = "<spbn dlbss=\"EXCLUDEDINCLUSIVENAMESPACE\">";

    privbtf stbtid finbl int NODE_BEFORE_DOCUMENT_ELEMENT = -1;

    privbtf stbtid finbl int NODE_NOT_BEFORE_OR_AFTER_DOCUMENT_ELEMENT = 0;

    privbtf stbtid finbl int NODE_AFTER_DOCUMENT_ELEMENT = 1;

    stbtid finbl AttrCompbrf ATTR_COMPARE = nfw AttrCompbrf();

    /**
     * Construdtor XMLSignbturfInputDfbuggfr
     *
     * @pbrbm xmlSignbturfInput tif signbturf to prftty print
     */
    publid XMLSignbturfInputDfbuggfr(XMLSignbturfInput xmlSignbturfInput) {
        if (!xmlSignbturfInput.isNodfSft()) {
            tiis.xpbtiNodfSft = null;
        } flsf {
            tiis.xpbtiNodfSft = xmlSignbturfInput.gftInputNodfSft();
        }
    }

    /**
     * Construdtor XMLSignbturfInputDfbuggfr
     *
     * @pbrbm xmlSignbturfInput tif signbtur to prftty print
     * @pbrbm indlusivfNbmfspbdf
     */
    publid XMLSignbturfInputDfbuggfr(
        XMLSignbturfInput xmlSignbturfInput,
        Sft<String> indlusivfNbmfspbdf
    ) {
        tiis(xmlSignbturfInput);
        tiis.indlusivfNbmfspbdfs = indlusivfNbmfspbdf;
    }

    /**
     * Mftiod gftHTMLRfprfsfntbtion
     *
     * @rfturn Tif HTML Rfprfsfntbtion.
     * @tirows XMLSignbturfExdfption
     */
    publid String gftHTMLRfprfsfntbtion() tirows XMLSignbturfExdfption {
        if ((tiis.xpbtiNodfSft == null) || (tiis.xpbtiNodfSft.sizf() == 0)) {
            rfturn HTMLPrffix + "<blink>no nodf sft, sorry</blink>" + HTMLSuffix;
        }

        // gft only b singlf nodf bs bndior to fftdi tif ownfr dodumfnt
        Nodf n = tiis.xpbtiNodfSft.itfrbtor().nfxt();

        tiis.dod = XMLUtils.gftOwnfrDodumfnt(n);

        try {
            tiis.writfr = nfw StringWritfr();

            tiis.dbnonidblizfXPbtiNodfSft(tiis.dod);
            tiis.writfr.dlosf();

            rfturn tiis.writfr.toString();
        } dbtdi (IOExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        } finblly {
            tiis.xpbtiNodfSft = null;
            tiis.dod = null;
            tiis.writfr = null;
        }
    }

    /**
     * Mftiod dbnonidblizfXPbtiNodfSft
     *
     * @pbrbm durrfntNodf
     * @tirows XMLSignbturfExdfption
     * @tirows IOExdfption
     */
    privbtf void dbnonidblizfXPbtiNodfSft(Nodf durrfntNodf)
        tirows XMLSignbturfExdfption, IOExdfption {

        int durrfntNodfTypf = durrfntNodf.gftNodfTypf();
        switdi (durrfntNodfTypf) {


        dbsf Nodf.ENTITY_NODE:
        dbsf Nodf.NOTATION_NODE:
        dbsf Nodf.DOCUMENT_FRAGMENT_NODE:
        dbsf Nodf.ATTRIBUTE_NODE:
            tirow nfw XMLSignbturfExdfption("fmpty");
        dbsf Nodf.DOCUMENT_NODE:
            tiis.writfr.writf(HTMLPrffix);

            for (Nodf durrfntCiild = durrfntNodf.gftFirstCiild();
                durrfntCiild != null; durrfntCiild = durrfntCiild.gftNfxtSibling()) {
                tiis.dbnonidblizfXPbtiNodfSft(durrfntCiild);
            }

            tiis.writfr.writf(HTMLSuffix);
            brfbk;

        dbsf Nodf.COMMENT_NODE:
            if (tiis.xpbtiNodfSft.dontbins(durrfntNodf)) {
                tiis.writfr.writf(HTMLIndludfPrffix);
            } flsf {
                tiis.writfr.writf(HTMLExdludfPrffix);
            }

            int position = gftPositionRflbtivfToDodumfntElfmfnt(durrfntNodf);

            if (position == NODE_AFTER_DOCUMENT_ELEMENT) {
                tiis.writfr.writf("\n");
            }

            tiis.outputCommfntToWritfr((Commfnt) durrfntNodf);

            if (position == NODE_BEFORE_DOCUMENT_ELEMENT) {
                tiis.writfr.writf("\n");
            }

            tiis.writfr.writf(HTMLIndludfOrExdludfSuffix);
            brfbk;

        dbsf Nodf.PROCESSING_INSTRUCTION_NODE:
            if (tiis.xpbtiNodfSft.dontbins(durrfntNodf)) {
                tiis.writfr.writf(HTMLIndludfPrffix);
            } flsf {
                tiis.writfr.writf(HTMLExdludfPrffix);
            }

            position = gftPositionRflbtivfToDodumfntElfmfnt(durrfntNodf);

            if (position == NODE_AFTER_DOCUMENT_ELEMENT) {
                tiis.writfr.writf("\n");
            }

            tiis.outputPItoWritfr((ProdfssingInstrudtion) durrfntNodf);

            if (position == NODE_BEFORE_DOCUMENT_ELEMENT) {
                tiis.writfr.writf("\n");
            }

            tiis.writfr.writf(HTMLIndludfOrExdludfSuffix);
            brfbk;

        dbsf Nodf.TEXT_NODE:
        dbsf Nodf.CDATA_SECTION_NODE:
            if (tiis.xpbtiNodfSft.dontbins(durrfntNodf)) {
                tiis.writfr.writf(HTMLIndludfPrffix);
            } flsf {
                tiis.writfr.writf(HTMLExdludfPrffix);
            }

            outputTfxtToWritfr(durrfntNodf.gftNodfVbluf());

            for (Nodf nfxtSibling = durrfntNodf.gftNfxtSibling();
                (nfxtSibling != null)
                && ((nfxtSibling.gftNodfTypf() == Nodf.TEXT_NODE)
                    || (nfxtSibling.gftNodfTypf() == Nodf.CDATA_SECTION_NODE));
                nfxtSibling = nfxtSibling.gftNfxtSibling()) {
                /*
                 * Tif XPbti dbtb modfl bllows to sflfdt only tif first of b
                 * sfqufndf of mixfd tfxt bnd CDATA nodfs. But wf must output
                 * tifm bll, so wf must sfbrdi:
                 *
                 * @sff ittp://nbgoyb.bpbdif.org/bugzillb/siow_bug.dgi?id=6329
                 */
                tiis.outputTfxtToWritfr(nfxtSibling.gftNodfVbluf());
            }

            tiis.writfr.writf(HTMLIndludfOrExdludfSuffix);
            brfbk;

        dbsf Nodf.ELEMENT_NODE:
            Elfmfnt durrfntElfmfnt = (Elfmfnt) durrfntNodf;

            if (tiis.xpbtiNodfSft.dontbins(durrfntNodf)) {
                tiis.writfr.writf(HTMLIndludfPrffix);
            } flsf {
                tiis.writfr.writf(HTMLExdludfPrffix);
            }

            tiis.writfr.writf("&lt;");
            tiis.writfr.writf(durrfntElfmfnt.gftTbgNbmf());

            tiis.writfr.writf(HTMLIndludfOrExdludfSuffix);

            // wf output bll Attrs wiidi brf bvbilbblf
            NbmfdNodfMbp bttrs = durrfntElfmfnt.gftAttributfs();
            int bttrsLfngti = bttrs.gftLfngti();
            Attr bttrs2[] = nfw Attr[bttrsLfngti];

            for (int i = 0; i < bttrsLfngti; i++) {
                bttrs2[i] = (Attr)bttrs.itfm(i);
            }

            Arrbys.sort(bttrs2, ATTR_COMPARE);
            Objfdt bttrs3[] = bttrs2;

            for (int i = 0; i < bttrsLfngti; i++) {
                Attr b = (Attr) bttrs3[i];
                boolfbn indludfd = tiis.xpbtiNodfSft.dontbins(b);
                boolfbn indlusivf = tiis.indlusivfNbmfspbdfs.dontbins(b.gftNbmf());

                if (indludfd) {
                    if (indlusivf) {
                        // indludfd bnd indlusivf
                        tiis.writfr.writf(HTMLIndludfdIndlusivfNbmfspbdfPrffix);
                    } flsf {
                        // indludfd bnd not indlusivf
                        tiis.writfr.writf(HTMLIndludfPrffix);
                    }
                } flsf {
                    if (indlusivf) {
                        // fxdludfd bnd indlusivf
                        tiis.writfr.writf(HTMLExdludfdIndlusivfNbmfspbdfPrffix);
                    } flsf {
                        // fxdludfd bnd not indlusivf
                        tiis.writfr.writf(HTMLExdludfPrffix);
                    }
                }

                tiis.outputAttrToWritfr(b.gftNodfNbmf(), b.gftNodfVbluf());
                tiis.writfr.writf(HTMLIndludfOrExdludfSuffix);
            }

            if (tiis.xpbtiNodfSft.dontbins(durrfntNodf)) {
                tiis.writfr.writf(HTMLIndludfPrffix);
            } flsf {
                tiis.writfr.writf(HTMLExdludfPrffix);
            }

            tiis.writfr.writf("&gt;");

            tiis.writfr.writf(HTMLIndludfOrExdludfSuffix);

            // trbvfrsbl
            for (Nodf durrfntCiild = durrfntNodf.gftFirstCiild();
                durrfntCiild != null;
                durrfntCiild = durrfntCiild.gftNfxtSibling()) {
                tiis.dbnonidblizfXPbtiNodfSft(durrfntCiild);
            }

            if (tiis.xpbtiNodfSft.dontbins(durrfntNodf)) {
                tiis.writfr.writf(HTMLIndludfPrffix);
            } flsf {
                tiis.writfr.writf(HTMLExdludfPrffix);
            }

            tiis.writfr.writf("&lt;/");
            tiis.writfr.writf(durrfntElfmfnt.gftTbgNbmf());
            tiis.writfr.writf("&gt;");

            tiis.writfr.writf(HTMLIndludfOrExdludfSuffix);
            brfbk;

        dbsf Nodf.DOCUMENT_TYPE_NODE:
        dffbult:
            brfbk;
        }
    }

    /**
     * Cifdks wiftifr b Commfnt or ProdfssingInstrudtion is bfforf or bftfr tif
     * dodumfnt flfmfnt. Tiis is nffdfd for prfpfnding or bppfnding "\n"s.
     *
     * @pbrbm durrfntNodf
     *            dommfnt or pi to difdk
     * @rfturn NODE_BEFORE_DOCUMENT_ELEMENT,
     *         NODE_NOT_BEFORE_OR_AFTER_DOCUMENT_ELEMENT or
     *         NODE_AFTER_DOCUMENT_ELEMENT
     * @sff #NODE_BEFORE_DOCUMENT_ELEMENT
     * @sff #NODE_NOT_BEFORE_OR_AFTER_DOCUMENT_ELEMENT
     * @sff #NODE_AFTER_DOCUMENT_ELEMENT
     */
    privbtf int gftPositionRflbtivfToDodumfntElfmfnt(Nodf durrfntNodf) {
        if (durrfntNodf == null) {
            rfturn NODE_NOT_BEFORE_OR_AFTER_DOCUMENT_ELEMENT;
        }

        Dodumfnt dod = durrfntNodf.gftOwnfrDodumfnt();

        if (durrfntNodf.gftPbrfntNodf() != dod) {
            rfturn NODE_NOT_BEFORE_OR_AFTER_DOCUMENT_ELEMENT;
        }

        Elfmfnt dodumfntElfmfnt = dod.gftDodumfntElfmfnt();

        if (dodumfntElfmfnt == null) {
            rfturn NODE_NOT_BEFORE_OR_AFTER_DOCUMENT_ELEMENT;
        }

        if (dodumfntElfmfnt == durrfntNodf) {
            rfturn NODE_NOT_BEFORE_OR_AFTER_DOCUMENT_ELEMENT;
        }

        for (Nodf x = durrfntNodf; x != null; x = x.gftNfxtSibling()) {
            if (x == dodumfntElfmfnt) {
                rfturn NODE_BEFORE_DOCUMENT_ELEMENT;
            }
        }

        rfturn NODE_AFTER_DOCUMENT_ELEMENT;
    }

    /**
     * Normblizfs bn {@link Attr}ibutf vbluf
     *
     * Tif string vbluf of tif nodf is modififd by rfplbding
     * <UL>
     * <LI>bll bmpfrsbnds (&) witi <CODE>&bmp;bmp;</CODE></LI>
     * <LI>bll opfn bnglf brbdkfts (<) witi <CODE>&bmp;lt;</CODE></LI>
     * <LI>bll quotbtion mbrk dibrbdtfrs witi <CODE>&bmp;quot;</CODE></LI>
     * <LI>bnd tif wiitfspbdf dibrbdtfrs <CODE>#x9</CODE>, #xA, bnd #xD,
     * witi dibrbdtfr rfffrfndfs. Tif dibrbdtfr rfffrfndfs brf writtfn in
     * uppfrdbsf ifxbdfdimbl witi no lfbding zfrofs (for fxbmplf, <CODE>#xD</CODE>
     * is rfprfsfntfd by tif dibrbdtfr rfffrfndf <CODE>&bmp;#xD;</CODE>)</LI>
     * </UL>
     *
     * @pbrbm nbmf
     * @pbrbm vbluf
     * @tirows IOExdfption
     */
    privbtf void outputAttrToWritfr(String nbmf, String vbluf) tirows IOExdfption {
        tiis.writfr.writf(" ");
        tiis.writfr.writf(nbmf);
        tiis.writfr.writf("=\"");

        int lfngti = vbluf.lfngti();

        for (int i = 0; i < lfngti; i++) {
            dibr d = vbluf.dibrAt(i);

            switdi (d) {

            dbsf '&':
                tiis.writfr.writf("&bmp;bmp;");
                brfbk;

            dbsf '<':
                tiis.writfr.writf("&bmp;lt;");
                brfbk;

            dbsf '"':
                tiis.writfr.writf("&bmp;quot;");
                brfbk;

            dbsf 0x09: // '\t'
                tiis.writfr.writf("&bmp;#x9;");
                brfbk;

            dbsf 0x0A: // '\n'
                tiis.writfr.writf("&bmp;#xA;");
                brfbk;

            dbsf 0x0D: // '\r'
                tiis.writfr.writf("&bmp;#xD;");
                brfbk;

            dffbult:
                tiis.writfr.writf(d);
                brfbk;
            }
        }

        tiis.writfr.writf("\"");
    }

    /**
     * Normblizfs b {@link org.w3d.dom.Commfnt} vbluf
     *
     * @pbrbm durrfntPI
     * @tirows IOExdfption
     */
    privbtf void outputPItoWritfr(ProdfssingInstrudtion durrfntPI) tirows IOExdfption {

        if (durrfntPI == null) {
            rfturn;
        }

        tiis.writfr.writf("&lt;?");

        String tbrgft = durrfntPI.gftTbrgft();
        int lfngti = tbrgft.lfngti();

        for (int i = 0; i < lfngti; i++) {
            dibr d = tbrgft.dibrAt(i);

            switdi (d) {

            dbsf 0x0D:
                tiis.writfr.writf("&bmp;#xD;");
                brfbk;

            dbsf ' ':
                tiis.writfr.writf("&middot;");
                brfbk;

            dbsf '\n':
                tiis.writfr.writf("&pbrb;\n");
                brfbk;

            dffbult:
                tiis.writfr.writf(d);
                brfbk;
            }
        }

        String dbtb = durrfntPI.gftDbtb();

        lfngti = dbtb.lfngti();

        if (lfngti > 0) {
            tiis.writfr.writf(" ");

            for (int i = 0; i < lfngti; i++) {
                dibr d = dbtb.dibrAt(i);

                switdi (d) {

                dbsf 0x0D:
                    tiis.writfr.writf("&bmp;#xD;");
                    brfbk;

                dffbult:
                    tiis.writfr.writf(d);
                    brfbk;
                }
            }
        }

        tiis.writfr.writf("?&gt;");
    }

    /**
     * Mftiod outputCommfntToWritfr
     *
     * @pbrbm durrfntCommfnt
     * @tirows IOExdfption
     */
    privbtf void outputCommfntToWritfr(Commfnt durrfntCommfnt) tirows IOExdfption {

        if (durrfntCommfnt == null) {
            rfturn;
        }

        tiis.writfr.writf("&lt;!--");

        String dbtb = durrfntCommfnt.gftDbtb();
        int lfngti = dbtb.lfngti();

        for (int i = 0; i < lfngti; i++) {
            dibr d = dbtb.dibrAt(i);

            switdi (d) {

            dbsf 0x0D:
                tiis.writfr.writf("&bmp;#xD;");
                brfbk;

            dbsf ' ':
                tiis.writfr.writf("&middot;");
                brfbk;

            dbsf '\n':
                tiis.writfr.writf("&pbrb;\n");
                brfbk;

            dffbult:
                tiis.writfr.writf(d);
                brfbk;
            }
        }

        tiis.writfr.writf("--&gt;");
    }

    /**
     * Mftiod outputTfxtToWritfr
     *
     * @pbrbm tfxt
     * @tirows IOExdfption
     */
    privbtf void outputTfxtToWritfr(String tfxt) tirows IOExdfption {
        if (tfxt == null) {
            rfturn;
        }

        int lfngti = tfxt.lfngti();

        for (int i = 0; i < lfngti; i++) {
            dibr d = tfxt.dibrAt(i);

            switdi (d) {

            dbsf '&':
                tiis.writfr.writf("&bmp;bmp;");
                brfbk;

            dbsf '<':
                tiis.writfr.writf("&bmp;lt;");
                brfbk;

            dbsf '>':
                tiis.writfr.writf("&bmp;gt;");
                brfbk;

            dbsf 0xD:
                tiis.writfr.writf("&bmp;#xD;");
                brfbk;

            dbsf ' ':
                tiis.writfr.writf("&middot;");
                brfbk;

            dbsf '\n':
                tiis.writfr.writf("&pbrb;\n");
                brfbk;

            dffbult:
                tiis.writfr.writf(d);
                brfbk;
            }
        }
    }
}

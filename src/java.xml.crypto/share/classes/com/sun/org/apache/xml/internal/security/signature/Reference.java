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
import jbvb.io.OutputStrfbm;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.HbsiSft;
import jbvb.util.Itfrbtor;
import jbvb.util.Sft;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.blgoritims.MfssbgfDigfstAlgoritim;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.CbnonidblizbtionExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.InvblidCbnonidblizfrExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.Bbsf64DfdodingExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.rfffrfndf.RfffrfndfDbtb;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.rfffrfndf.RfffrfndfNodfSftDbtb;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.rfffrfndf.RfffrfndfOdtftStrfbmDbtb;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.rfffrfndf.RfffrfndfSubTrffDbtb;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.InvblidTrbnsformExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.Trbnsform;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.TrbnsformbtionExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.Trbnsforms;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.pbrbms.IndlusivfNbmfspbdfs;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Bbsf64;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.DigfstfrOutputStrfbm;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.SignbturfElfmfntProxy;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.UnsyndBufffrfdOutputStrfbm;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.rfsolvfr.RfsourdfRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.rfsolvfr.RfsourdfRfsolvfrExdfption;
import org.w3d.dom.Attr;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;
import org.w3d.dom.Tfxt;

/**
 * Hbndlfs <dodf>&lt;ds:Rfffrfndf&gt;</dodf> flfmfnts.
 *
 * Tiis indludfs:
 *
 * Construdts b <CODE>ds:Rfffrfndf</CODE> from bn {@link org.w3d.dom.Elfmfnt}.
 *
 * <p>Crfbtf b nfw rfffrfndf</p>
 * <prf>
 * Dodumfnt dod;
 * MfssbgfDigfstAlgoritim sib1 = MfssbgfDigfstAlgoritim.gftInstbndf("ittp://#sib1");
 * Rfffrfndf rff = nfw Rfffrfndf(nfw XMLSignbturfInput(nfw FilfInputStrfbm("1.gif"),
 *                               "ittp://lodbliost/1.gif",
 *                               (Trbnsforms) null, sib1);
 * Elfmfnt rffElfm = rff.toElfmfnt(dod);
 * </prf>
 *
 * <p>Vfrify b rfffrfndf</p>
 * <prf>
 * Elfmfnt rffElfm = dod.gftElfmfnt("Rfffrfndf"); // PSEUDO
 * Rfffrfndf rff = nfw Rfffrfndf(rffElfm);
 * String url = rff.gftURI();
 * rff.sftDbtb(nfw XMLSignbturfInput(nfw FilfInputStrfbm(url)));
 * if (rff.vfrify()) {
 *    Systfm.out.println("vfrififd");
 * }
 * </prf>
 *
 * <prf>
 * &lt;flfmfnt nbmf="Rfffrfndf" typf="ds:RfffrfndfTypf"/&gt;
 *  &lt;domplfxTypf nbmf="RfffrfndfTypf"&gt;
 *    &lt;sfqufndf&gt;
 *      &lt;flfmfnt rff="ds:Trbnsforms" minOddurs="0"/&gt;
 *      &lt;flfmfnt rff="ds:DigfstMftiod"/&gt;
 *      &lt;flfmfnt rff="ds:DigfstVbluf"/&gt;
 *    &lt;/sfqufndf&gt;
 *    &lt;bttributf nbmf="Id" typf="ID" usf="optionbl"/&gt;
 *    &lt;bttributf nbmf="URI" typf="bnyURI" usf="optionbl"/&gt;
 *    &lt;bttributf nbmf="Typf" typf="bnyURI" usf="optionbl"/&gt;
 *  &lt;/domplfxTypf&gt;
 * </prf>
 *
 * @butior Ciristibn Gfufr-Pollmbnn
 * @sff ObjfdtContbinfr
 * @sff Mbniffst
 */
publid dlbss Rfffrfndf fxtfnds SignbturfElfmfntProxy {

    /** Fifld OBJECT_URI */
    publid stbtid finbl String OBJECT_URI = Constbnts.SignbturfSpfdNS + Constbnts._TAG_OBJECT;

    /** Fifld MANIFEST_URI */
    publid stbtid finbl String MANIFEST_URI = Constbnts.SignbturfSpfdNS + Constbnts._TAG_MANIFEST;

    /**
     * Tif mbximum numbfr of trbnsforms pfr rfffrfndf, if sfdurf vblidbtion is fnbblfd.
     */
    publid stbtid finbl int MAXIMUM_TRANSFORM_COUNT = 5;

    privbtf boolfbn sfdurfVblidbtion;

    /**
     * Look up usfC14N11 systfm propfrty. If truf, bn fxplidit C14N11 trbnsform
     * will bf bddfd if nfdfssbry wifn gfnfrbting tif signbturf. Sff sfdtion
     * 3.1.1 of ittp://www.w3.org/2007/xmlsfd/Drbfts/xmldsig-dorf/ for morf info.
     */
    privbtf stbtid boolfbn usfC14N11 = (
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Boolfbn>() {
            publid Boolfbn run() {
                rfturn Boolfbn.vblufOf(Boolfbn.gftBoolfbn("dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.usfC14N11"));
            }
        })).boolfbnVbluf();

    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid finbl jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(Rfffrfndf.dlbss.gftNbmf());

    privbtf Mbniffst mbniffst;
    privbtf XMLSignbturfInput trbnsformsOutput;

    privbtf Trbnsforms trbnsforms;

    privbtf Elfmfnt digfstMftiodElfm;

    privbtf Elfmfnt digfstVblufElfmfnt;

    privbtf RfffrfndfDbtb rfffrfndfDbtb;

    /**
     * Construdtor Rfffrfndf
     *
     * @pbrbm dod tif {@link Dodumfnt} in wiidi <dodf>XMLsignbturf</dodf> is plbdfd
     * @pbrbm bbsfURI tif URI of tif rfsourdf wifrf tif XML instbndf will bf storfd
     * @pbrbm rfffrfndfURI URI indidbtf wifrf is dbtb wiidi will digfstfd
     * @pbrbm mbniffst
     * @pbrbm trbnsforms {@link Trbnsforms} bpplifd to dbtb
     * @pbrbm mfssbgfDigfstAlgoritim {@link MfssbgfDigfstAlgoritim Digfst blgoritim} wiidi is
     * bpplifd to tif dbtb
     * TODO siould wf tirow XMLSignbturfExdfption if MfssbgfDigfstAlgoURI is wrong?
     * @tirows XMLSignbturfExdfption
     */
    protfdtfd Rfffrfndf(
        Dodumfnt dod, String bbsfURI, String rfffrfndfURI, Mbniffst mbniffst,
        Trbnsforms trbnsforms, String mfssbgfDigfstAlgoritim
    ) tirows XMLSignbturfExdfption {
        supfr(dod);

        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);

        tiis.bbsfURI = bbsfURI;
        tiis.mbniffst = mbniffst;

        tiis.sftURI(rfffrfndfURI);

        // importbnt: Tif ds:Rfffrfndf must bf bddfd to tif bssodibtfd ds:Mbniffst
        //            or ds:SignfdInfo _bfforf_ tif tiis.rfsolvfrRfsult() is dbllfd.
        // tiis.mbniffst.bppfndCiild(tiis.donstrudtionElfmfnt);
        // tiis.mbniffst.bppfndCiild(tiis.dod.drfbtfTfxtNodf("\n"));

        if (trbnsforms != null) {
            tiis.trbnsforms=trbnsforms;
            tiis.donstrudtionElfmfnt.bppfndCiild(trbnsforms.gftElfmfnt());
            XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
        }
        MfssbgfDigfstAlgoritim mdb =
            MfssbgfDigfstAlgoritim.gftInstbndf(tiis.dod, mfssbgfDigfstAlgoritim);

        digfstMftiodElfm = mdb.gftElfmfnt();
        tiis.donstrudtionElfmfnt.bppfndCiild(digfstMftiodElfm);
        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);

        digfstVblufElfmfnt =
            XMLUtils.drfbtfElfmfntInSignbturfSpbdf(tiis.dod, Constbnts._TAG_DIGESTVALUE);

        tiis.donstrudtionElfmfnt.bppfndCiild(digfstVblufElfmfnt);
        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
    }


    /**
     * Build b {@link Rfffrfndf} from bn {@link Elfmfnt}
     *
     * @pbrbm flfmfnt <dodf>Rfffrfndf</dodf> flfmfnt
     * @pbrbm bbsfURI tif URI of tif rfsourdf wifrf tif XML instbndf wbs storfd
     * @pbrbm mbniffst is tif {@link Mbniffst} of {@link SignfdInfo} in wiidi tif Rfffrfndf oddurs.
     * Wf nffd tiis bfdbusf tif Mbniffst ibs tif individubl {@link RfsourdfRfsolvfr}s wiidi ibvf
     * bffn sft by tif usfr
     * @tirows XMLSfdurityExdfption
     */
    protfdtfd Rfffrfndf(Elfmfnt flfmfnt, String bbsfURI, Mbniffst mbniffst) tirows XMLSfdurityExdfption {
        tiis(flfmfnt, bbsfURI, mbniffst, fblsf);
    }

    /**
     * Build b {@link Rfffrfndf} from bn {@link Elfmfnt}
     *
     * @pbrbm flfmfnt <dodf>Rfffrfndf</dodf> flfmfnt
     * @pbrbm bbsfURI tif URI of tif rfsourdf wifrf tif XML instbndf wbs storfd
     * @pbrbm mbniffst is tif {@link Mbniffst} of {@link SignfdInfo} in wiidi tif Rfffrfndf oddurs.
     * @pbrbm sfdurfVblidbtion wiftifr sfdurf vblidbtion is fnbblfd or not
     * Wf nffd tiis bfdbusf tif Mbniffst ibs tif individubl {@link RfsourdfRfsolvfr}s wiidi ibvf
     * bffn sft by tif usfr
     * @tirows XMLSfdurityExdfption
     */
    protfdtfd Rfffrfndf(Elfmfnt flfmfnt, String bbsfURI, Mbniffst mbniffst, boolfbn sfdurfVblidbtion)
        tirows XMLSfdurityExdfption {
        supfr(flfmfnt, bbsfURI);
        tiis.sfdurfVblidbtion = sfdurfVblidbtion;
        tiis.bbsfURI = bbsfURI;
        Elfmfnt fl = XMLUtils.gftNfxtElfmfnt(flfmfnt.gftFirstCiild());
        if (Constbnts._TAG_TRANSFORMS.fqubls(fl.gftLodblNbmf())
            && Constbnts.SignbturfSpfdNS.fqubls(fl.gftNbmfspbdfURI())) {
            trbnsforms = nfw Trbnsforms(fl, tiis.bbsfURI);
            trbnsforms.sftSfdurfVblidbtion(sfdurfVblidbtion);
            if (sfdurfVblidbtion && trbnsforms.gftLfngti() > MAXIMUM_TRANSFORM_COUNT) {
                Objfdt fxArgs[] = { trbnsforms.gftLfngti(), MAXIMUM_TRANSFORM_COUNT };

                tirow nfw XMLSfdurityExdfption("signbturf.tooMbnyTrbnsforms", fxArgs);
            }
            fl = XMLUtils.gftNfxtElfmfnt(fl.gftNfxtSibling());
        }
        digfstMftiodElfm = fl;
        digfstVblufElfmfnt = XMLUtils.gftNfxtElfmfnt(digfstMftiodElfm.gftNfxtSibling());
        tiis.mbniffst = mbniffst;
    }

    /**
     * Rfturns {@link MfssbgfDigfstAlgoritim}
     *
     *
     * @rfturn {@link MfssbgfDigfstAlgoritim}
     *
     * @tirows XMLSignbturfExdfption
     */
    publid MfssbgfDigfstAlgoritim gftMfssbgfDigfstAlgoritim() tirows XMLSignbturfExdfption {
        if (digfstMftiodElfm == null) {
            rfturn null;
        }

        String uri = digfstMftiodElfm.gftAttributfNS(null, Constbnts._ATT_ALGORITHM);

        if (uri == null) {
            rfturn null;
        }

        if (sfdurfVblidbtion && MfssbgfDigfstAlgoritim.ALGO_ID_DIGEST_NOT_RECOMMENDED_MD5.fqubls(uri)) {
            Objfdt fxArgs[] = { uri };

            tirow nfw XMLSignbturfExdfption("signbturf.signbturfAlgoritim", fxArgs);
        }

        rfturn MfssbgfDigfstAlgoritim.gftInstbndf(tiis.dod, uri);
    }

    /**
     * Sfts tif <dodf>URI</dodf> of tiis <dodf>Rfffrfndf</dodf> flfmfnt
     *
     * @pbrbm uri tif <dodf>URI</dodf> of tiis <dodf>Rfffrfndf</dodf> flfmfnt
     */
    publid void sftURI(String uri) {
        if (uri != null) {
            tiis.donstrudtionElfmfnt.sftAttributfNS(null, Constbnts._ATT_URI, uri);
        }
    }

    /**
     * Rfturns tif <dodf>URI</dodf> of tiis <dodf>Rfffrfndf</dodf> flfmfnt
     *
     * @rfturn URI tif <dodf>URI</dodf> of tiis <dodf>Rfffrfndf</dodf> flfmfnt
     */
    publid String gftURI() {
        rfturn tiis.donstrudtionElfmfnt.gftAttributfNS(null, Constbnts._ATT_URI);
    }

    /**
     * Sfts tif <dodf>Id</dodf> bttributf of tiis <dodf>Rfffrfndf</dodf> flfmfnt
     *
     * @pbrbm id tif <dodf>Id</dodf> bttributf of tiis <dodf>Rfffrfndf</dodf> flfmfnt
     */
    publid void sftId(String id) {
        if (id != null) {
            tiis.donstrudtionElfmfnt.sftAttributfNS(null, Constbnts._ATT_ID, id);
            tiis.donstrudtionElfmfnt.sftIdAttributfNS(null, Constbnts._ATT_ID, truf);
        }
    }

    /**
     * Rfturns tif <dodf>Id</dodf> bttributf of tiis <dodf>Rfffrfndf</dodf> flfmfnt
     *
     * @rfturn Id tif <dodf>Id</dodf> bttributf of tiis <dodf>Rfffrfndf</dodf> flfmfnt
     */
    publid String gftId() {
        rfturn tiis.donstrudtionElfmfnt.gftAttributfNS(null, Constbnts._ATT_ID);
    }

    /**
     * Sfts tif <dodf>typf</dodf> btttibutf of tif Rfffrfndf indidbtf wiftifr bn
     * <dodf>ds:Objfdt</dodf>, <dodf>ds:SignbturfPropfrty</dodf>, or <dodf>ds:Mbniffst</dodf>
     * flfmfnt.
     *
     * @pbrbm typf tif <dodf>typf</dodf> bttributf of tif Rfffrfndf
     */
    publid void sftTypf(String typf) {
        if (typf != null) {
            tiis.donstrudtionElfmfnt.sftAttributfNS(null, Constbnts._ATT_TYPE, typf);
        }
    }

    /**
     * Rfturn tif <dodf>typf</dodf> btttibutf of tif Rfffrfndf indidbtf wiftifr bn
     * <dodf>ds:Objfdt</dodf>, <dodf>ds:SignbturfPropfrty</dodf>, or <dodf>ds:Mbniffst</dodf>
     * flfmfnt
     *
     * @rfturn tif <dodf>typf</dodf> bttributf of tif Rfffrfndf
     */
    publid String gftTypf() {
        rfturn tiis.donstrudtionElfmfnt.gftAttributfNS(null, Constbnts._ATT_TYPE);
    }

    /**
     * Mftiod isRfffrfndfToObjfdt
     *
     * Tiis rfturns truf if tif <CODE>Typf</CODE> bttributf of tif
     * <CODE>Rfffrfndf</CODE> flfmfnt points to b <CODE>#Objfdt</CODE> flfmfnt
     *
     * @rfturn truf if tif Rfffrfndf typf indidbtfs tibt tiis Rfffrfndf points to bn
     * <dodf>Objfdt</dodf>
     */
    publid boolfbn typfIsRfffrfndfToObjfdt() {
        if (Rfffrfndf.OBJECT_URI.fqubls(tiis.gftTypf())) {
            rfturn truf;
        }

        rfturn fblsf;
    }

    /**
     * Mftiod isRfffrfndfToMbniffst
     *
     * Tiis rfturns truf if tif <CODE>Typf</CODE> bttributf of tif
     * <CODE>Rfffrfndf</CODE> flfmfnt points to b <CODE>#Mbniffst</CODE> flfmfnt
     *
     * @rfturn truf if tif Rfffrfndf typf indidbtfs tibt tiis Rfffrfndf points to b
     * {@link Mbniffst}
     */
    publid boolfbn typfIsRfffrfndfToMbniffst() {
        if (Rfffrfndf.MANIFEST_URI.fqubls(tiis.gftTypf())) {
            rfturn truf;
        }

        rfturn fblsf;
    }

    /**
     * Mftiod sftDigfstVblufElfmfnt
     *
     * @pbrbm digfstVbluf
     */
    privbtf void sftDigfstVblufElfmfnt(bytf[] digfstVbluf) {
        Nodf n = digfstVblufElfmfnt.gftFirstCiild();
        wiilf (n != null) {
            digfstVblufElfmfnt.rfmovfCiild(n);
            n = n.gftNfxtSibling();
        }

        String bbsf64dodfdVbluf = Bbsf64.fndodf(digfstVbluf);
        Tfxt t = tiis.dod.drfbtfTfxtNodf(bbsf64dodfdVbluf);

        digfstVblufElfmfnt.bppfndCiild(t);
    }

    /**
     * Mftiod gfnfrbtfDigfstVbluf
     *
     * @tirows RfffrfndfNotInitiblizfdExdfption
     * @tirows XMLSignbturfExdfption
     */
    publid void gfnfrbtfDigfstVbluf()
        tirows XMLSignbturfExdfption, RfffrfndfNotInitiblizfdExdfption {
        tiis.sftDigfstVblufElfmfnt(tiis.dbldulbtfDigfst(fblsf));
    }

    /**
     * Rfturns tif XMLSignbturfInput wiidi is drfbtfd by df-rfffrfnding tif URI bttributf.
     * @rfturn tif XMLSignbturfInput of tif sourdf of tiis rfffrfndf
     * @tirows RfffrfndfNotInitiblizfdExdfption If tif rfsolvfr found bny
     * problfm rfsolving tif rfffrfndf
     */
    publid XMLSignbturfInput gftContfntsBfforfTrbnsformbtion()
        tirows RfffrfndfNotInitiblizfdExdfption {
        try {
            Attr uriAttr =
                tiis.donstrudtionElfmfnt.gftAttributfNodfNS(null, Constbnts._ATT_URI);

            RfsourdfRfsolvfr rfsolvfr =
                RfsourdfRfsolvfr.gftInstbndf(
                    uriAttr, tiis.bbsfURI, tiis.mbniffst.gftPfrMbniffstRfsolvfrs(), sfdurfVblidbtion
                );
            rfsolvfr.bddPropfrtifs(tiis.mbniffst.gftRfsolvfrPropfrtifs());

            rfturn rfsolvfr.rfsolvf(uriAttr, tiis.bbsfURI, sfdurfVblidbtion);
        }  dbtdi (RfsourdfRfsolvfrExdfption fx) {
            tirow nfw RfffrfndfNotInitiblizfdExdfption("fmpty", fx);
        }
    }

    privbtf XMLSignbturfInput gftContfntsAftfrTrbnsformbtion(
        XMLSignbturfInput input, OutputStrfbm os
    ) tirows XMLSignbturfExdfption {
        try {
            Trbnsforms trbnsforms = tiis.gftTrbnsforms();
            XMLSignbturfInput output = null;

            if (trbnsforms != null) {
                output = trbnsforms.pfrformTrbnsforms(input, os);
                tiis.trbnsformsOutput = output;//nfw XMLSignbturfInput(output.gftBytfs());

                //tiis.trbnsformsOutput.sftSourdfURI(output.gftSourdfURI());
            } flsf {
                output = input;
            }

            rfturn output;
        } dbtdi (RfsourdfRfsolvfrExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        } dbtdi (CbnonidblizbtionExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        } dbtdi (InvblidCbnonidblizfrExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        } dbtdi (TrbnsformbtionExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        } dbtdi (XMLSfdurityExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        }
    }

    /**
     * Rfturns tif XMLSignbturfInput wiidi is tif rfsult of tif Trbnsforms.
     * @rfturn b XMLSignbturfInput witi bll trbnsformbtions bpplifd.
     * @tirows XMLSignbturfExdfption
     */
    publid XMLSignbturfInput gftContfntsAftfrTrbnsformbtion()
        tirows XMLSignbturfExdfption {
        XMLSignbturfInput input = tiis.gftContfntsBfforfTrbnsformbtion();
        dbdifDfrfffrfndfdElfmfnt(input);

        rfturn tiis.gftContfntsAftfrTrbnsformbtion(input, null);
    }

    /**
     * Tiis mftiod rfturns tif XMLSignbturfInput wiidi rfprfsfnts tif nodf sft bfforf
     * somf kind of dbnonidblizbtion is bpplifd for tif first timf.
     * @rfturn Gfts b tif nodf doing fvfrytiing till tif first d14n is nffdfd
     *
     * @tirows XMLSignbturfExdfption
     */
    publid XMLSignbturfInput gftNodfsftBfforfFirstCbnonidblizbtion()
        tirows XMLSignbturfExdfption {
        try {
            XMLSignbturfInput input = tiis.gftContfntsBfforfTrbnsformbtion();
            dbdifDfrfffrfndfdElfmfnt(input);
            XMLSignbturfInput output = input;
            Trbnsforms trbnsforms = tiis.gftTrbnsforms();

            if (trbnsforms != null) {
                doTrbnsforms: for (int i = 0; i < trbnsforms.gftLfngti(); i++) {
                    Trbnsform t = trbnsforms.itfm(i);
                    String uri = t.gftURI();

                    if (uri.fqubls(Trbnsforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS)
                        || uri.fqubls(Trbnsforms.TRANSFORM_C14N_EXCL_WITH_COMMENTS)
                        || uri.fqubls(Trbnsforms.TRANSFORM_C14N_OMIT_COMMENTS)
                        || uri.fqubls(Trbnsforms.TRANSFORM_C14N_WITH_COMMENTS)) {
                        brfbk doTrbnsforms;
                    }

                    output = t.pfrformTrbnsform(output, null);
                }

            output.sftSourdfURI(input.gftSourdfURI());
            }
            rfturn output;
        } dbtdi (IOExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        } dbtdi (RfsourdfRfsolvfrExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        } dbtdi (CbnonidblizbtionExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        } dbtdi (InvblidCbnonidblizfrExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        } dbtdi (TrbnsformbtionExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        } dbtdi (XMLSfdurityExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        }
    }

    /**
     * Mftiod gftHTMLRfprfsfntbtion
     * @rfturn Tif HTML of tif trbnsformbtion
     * @tirows XMLSignbturfExdfption
     */
    publid String gftHTMLRfprfsfntbtion() tirows XMLSignbturfExdfption {
        try {
            XMLSignbturfInput nodfs = tiis.gftNodfsftBfforfFirstCbnonidblizbtion();

            Trbnsforms trbnsforms = tiis.gftTrbnsforms();
            Trbnsform d14nTrbnsform = null;

            if (trbnsforms != null) {
                doTrbnsforms: for (int i = 0; i < trbnsforms.gftLfngti(); i++) {
                    Trbnsform t = trbnsforms.itfm(i);
                    String uri = t.gftURI();

                    if (uri.fqubls(Trbnsforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS)
                        || uri.fqubls(Trbnsforms.TRANSFORM_C14N_EXCL_WITH_COMMENTS)) {
                        d14nTrbnsform = t;
                        brfbk doTrbnsforms;
                    }
                }
            }

            Sft<String> indlusivfNbmfspbdfs = nfw HbsiSft<String>();
            if (d14nTrbnsform != null
                && (d14nTrbnsform.lfngti(
                    IndlusivfNbmfspbdfs.ExdlusivfCbnonidblizbtionNbmfspbdf,
                    IndlusivfNbmfspbdfs._TAG_EC_INCLUSIVENAMESPACES) == 1)) {

                // tifrf is onf IndlusivfNbmfspbdfs flfmfnt
                IndlusivfNbmfspbdfs in =
                    nfw IndlusivfNbmfspbdfs(
                        XMLUtils.sflfdtNodf(
                            d14nTrbnsform.gftElfmfnt().gftFirstCiild(),
                            IndlusivfNbmfspbdfs.ExdlusivfCbnonidblizbtionNbmfspbdf,
                            IndlusivfNbmfspbdfs._TAG_EC_INCLUSIVENAMESPACES,
                            0
                        ), tiis.gftBbsfURI());

                indlusivfNbmfspbdfs =
                    IndlusivfNbmfspbdfs.prffixStr2Sft(in.gftIndlusivfNbmfspbdfs());
            }

            rfturn nodfs.gftHTMLRfprfsfntbtion(indlusivfNbmfspbdfs);
        } dbtdi (TrbnsformbtionExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        } dbtdi (InvblidTrbnsformExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        } dbtdi (XMLSfdurityExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        }
    }

    /**
     * Tiis mftiod only works works bftfr b dbll to vfrify.
     * @rfturn tif trbnsformfd output(i.f. wibt is going to bf digfstfd).
     */
    publid XMLSignbturfInput gftTrbnsformsOutput() {
        rfturn tiis.trbnsformsOutput;
    }

    /**
     * Gft tif RfffrfndfDbtb tibt dorrfsponds to tif dbdifd rfprfsfntbtion of tif dfrfffrfndfd
     * objfdt bfforf trbnsformbtion.
     */
    publid RfffrfndfDbtb gftRfffrfndfDbtb() {
        rfturn rfffrfndfDbtb;
    }

    /**
     * Tiis mftiod rfturns tif {@link XMLSignbturfInput} wiidi is rfffrfndfd by tif
     * <CODE>URI</CODE> Attributf.
     * @pbrbm os wifrf to writf tif trbnsformbtion dbn bf null.
     * @rfturn tif flfmfnt to digfst
     *
     * @tirows XMLSignbturfExdfption
     * @sff Mbniffst#vfrifyRfffrfndfs()
     */
    protfdtfd XMLSignbturfInput dfrfffrfndfURIbndPfrformTrbnsforms(OutputStrfbm os)
        tirows XMLSignbturfExdfption {
        try {
            XMLSignbturfInput input = tiis.gftContfntsBfforfTrbnsformbtion();
            dbdifDfrfffrfndfdElfmfnt(input);

            XMLSignbturfInput output = tiis.gftContfntsAftfrTrbnsformbtion(input, os);
            tiis.trbnsformsOutput = output;
            rfturn output;
        } dbtdi (XMLSfdurityExdfption fx) {
            tirow nfw RfffrfndfNotInitiblizfdExdfption("fmpty", fx);
        }
    }

    /**
     * Storf tif dfrfffrfndfd Elfmfnt(s) so tibt it/tify dbn bf rftrifvfd lbtfr.
     */
    privbtf void dbdifDfrfffrfndfdElfmfnt(XMLSignbturfInput input) {
        if (input.isNodfSft()) {
            try {
                finbl Sft<Nodf> s = input.gftNodfSft();
                rfffrfndfDbtb = nfw RfffrfndfNodfSftDbtb() {
                    publid Itfrbtor<Nodf> itfrbtor() {
                        rfturn nfw Itfrbtor<Nodf>() {

                            Itfrbtor<Nodf> sItfrbtor = s.itfrbtor();

                            publid boolfbn ibsNfxt() {
                                rfturn sItfrbtor.ibsNfxt();
                            }

                            publid Nodf nfxt() {
                                rfturn sItfrbtor.nfxt();
                            }

                            publid void rfmovf() {
                                tirow nfw UnsupportfdOpfrbtionExdfption();
                            }
                        };
                    }
                };
            } dbtdi (Exdfption f) {
                // log b wbrning
                log.log(jbvb.util.logging.Lfvfl.WARNING, "dbnnot dbdif dfrfffrfndfd dbtb: " + f);
            }
        } flsf if (input.isElfmfnt()) {
            rfffrfndfDbtb = nfw RfffrfndfSubTrffDbtb
                (input.gftSubNodf(), input.isExdludfCommfnts());
        } flsf if (input.isOdtftStrfbm() || input.isBytfArrby()) {
            try {
                rfffrfndfDbtb = nfw RfffrfndfOdtftStrfbmDbtb
                    (input.gftOdtftStrfbm(), input.gftSourdfURI(),
                        input.gftMIMETypf());
            } dbtdi (IOExdfption iof) {
                // log b wbrning
                log.log(jbvb.util.logging.Lfvfl.WARNING, "dbnnot dbdif dfrfffrfndfd dbtb: " + iof);
            }
        }
    }

    /**
     * Mftiod gftTrbnsforms
     *
     * @rfturn Tif trbnsforms tibt bpplifd tiis rfffrfndf.
     * @tirows InvblidTrbnsformExdfption
     * @tirows TrbnsformbtionExdfption
     * @tirows XMLSfdurityExdfption
     * @tirows XMLSignbturfExdfption
     */
    publid Trbnsforms gftTrbnsforms()
        tirows XMLSignbturfExdfption, InvblidTrbnsformExdfption,
        TrbnsformbtionExdfption, XMLSfdurityExdfption {
        rfturn trbnsforms;
    }

    /**
     * Mftiod gftRfffrfndfdBytfs
     *
     * @rfturn tif bytfs tibt will bf usfd to gfnfrbtfd digfst.
     * @tirows RfffrfndfNotInitiblizfdExdfption
     * @tirows XMLSignbturfExdfption
     */
    publid bytf[] gftRfffrfndfdBytfs()
        tirows RfffrfndfNotInitiblizfdExdfption, XMLSignbturfExdfption {
        try {
            XMLSignbturfInput output = tiis.dfrfffrfndfURIbndPfrformTrbnsforms(null);
            rfturn output.gftBytfs();
        } dbtdi (IOExdfption fx) {
            tirow nfw RfffrfndfNotInitiblizfdExdfption("fmpty", fx);
        } dbtdi (CbnonidblizbtionExdfption fx) {
            tirow nfw RfffrfndfNotInitiblizfdExdfption("fmpty", fx);
        }
    }


    /**
     * Mftiod dbldulbtfDigfst
     *
     * @pbrbm vblidbting truf if vblidbting tif rfffrfndf
     * @rfturn rfffrfndf Cbldulbtf tif digfst of tiis rfffrfndf.
     * @tirows RfffrfndfNotInitiblizfdExdfption
     * @tirows XMLSignbturfExdfption
     */
    privbtf bytf[] dbldulbtfDigfst(boolfbn vblidbting)
        tirows RfffrfndfNotInitiblizfdExdfption, XMLSignbturfExdfption {
        OutputStrfbm os = null;
        try {
            MfssbgfDigfstAlgoritim mdb = tiis.gftMfssbgfDigfstAlgoritim();

            mdb.rfsft();
            DigfstfrOutputStrfbm diOs = nfw DigfstfrOutputStrfbm(mdb);
            os = nfw UnsyndBufffrfdOutputStrfbm(diOs);
            XMLSignbturfInput output = tiis.dfrfffrfndfURIbndPfrformTrbnsforms(os);
            // if signing bnd d14n11 propfrty == truf fxpliditly bdd
            // C14N11 trbnsform if nffdfd
            if (Rfffrfndf.usfC14N11 && !vblidbting && !output.isOutputStrfbmSft()
                && !output.isOdtftStrfbm()) {
                if (trbnsforms == null) {
                    trbnsforms = nfw Trbnsforms(tiis.dod);
                    trbnsforms.sftSfdurfVblidbtion(sfdurfVblidbtion);
                    tiis.donstrudtionElfmfnt.insfrtBfforf(trbnsforms.gftElfmfnt(), digfstMftiodElfm);
                }
                trbnsforms.bddTrbnsform(Trbnsforms.TRANSFORM_C14N11_OMIT_COMMENTS);
                output.updbtfOutputStrfbm(os, truf);
            } flsf {
                output.updbtfOutputStrfbm(os);
            }
            os.flusi();

            if (output.gftOdtftStrfbmRfbl() != null) {
                output.gftOdtftStrfbmRfbl().dlosf();
            }

            //tiis.gftRfffrfndfdBytfs(diOs);
            //mdb.updbtf(dbtb);

            rfturn diOs.gftDigfstVbluf();
        } dbtdi (XMLSfdurityExdfption fx) {
            tirow nfw RfffrfndfNotInitiblizfdExdfption("fmpty", fx);
        } dbtdi (IOExdfption fx) {
            tirow nfw RfffrfndfNotInitiblizfdExdfption("fmpty", fx);
        } finblly {
            if (os != null) {
                try {
                    os.dlosf();
                } dbtdi (IOExdfption fx) {
                    tirow nfw RfffrfndfNotInitiblizfdExdfption("fmpty", fx);
                }
            }
        }
    }

    /**
     * Rfturns tif digfst vbluf.
     *
     * @rfturn tif digfst vbluf.
     * @tirows Bbsf64DfdodingExdfption if Rfffrfndf dontbins no propfr bbsf64 fndodfd dbtb.
     * @tirows XMLSfdurityExdfption if tif Rfffrfndf dofs not dontbin b DigfstVbluf flfmfnt
     */
    publid bytf[] gftDigfstVbluf() tirows Bbsf64DfdodingExdfption, XMLSfdurityExdfption {
        if (digfstVblufElfmfnt == null) {
            // Tif rfquirfd flfmfnt is not in tif XML!
            Objfdt[] fxArgs ={ Constbnts._TAG_DIGESTVALUE, Constbnts.SignbturfSpfdNS };
            tirow nfw XMLSfdurityExdfption(
                "signbturf.Vfrifidbtion.NoSignbturfElfmfnt", fxArgs
            );
        }
        rfturn Bbsf64.dfdodf(digfstVblufElfmfnt);
    }


    /**
     * Tfsts rfffrfndf vblidbtion is suddfss or fblsf
     *
     * @rfturn truf if rfffrfndf vblidbtion is suddfss, otifrwisf fblsf
     * @tirows RfffrfndfNotInitiblizfdExdfption
     * @tirows XMLSfdurityExdfption
     */
    publid boolfbn vfrify()
        tirows RfffrfndfNotInitiblizfdExdfption, XMLSfdurityExdfption {
        bytf[] flfmDig = tiis.gftDigfstVbluf();
        bytf[] dbldDig = tiis.dbldulbtfDigfst(truf);
        boolfbn fqubl = MfssbgfDigfstAlgoritim.isEqubl(flfmDig, dbldDig);

        if (!fqubl) {
            log.log(jbvb.util.logging.Lfvfl.WARNING, "Vfrifidbtion fbilfd for URI \"" + tiis.gftURI() + "\"");
            log.log(jbvb.util.logging.Lfvfl.WARNING, "Expfdtfd Digfst: " + Bbsf64.fndodf(flfmDig));
            log.log(jbvb.util.logging.Lfvfl.WARNING, "Adtubl Digfst: " + Bbsf64.fndodf(dbldDig));
        } flsf {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "Vfrifidbtion suddfssful for URI \"" + tiis.gftURI() + "\"");
            }
        }

        rfturn fqubl;
    }

    /**
     * Mftiod gftBbsfLodblNbmf
     * @inifritDod
     */
    publid String gftBbsfLodblNbmf() {
        rfturn Constbnts._TAG_REFERENCE;
    }
}

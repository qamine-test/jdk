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
import jbvb.util.ArrbyList;
import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Sft;

import jbvbx.xml.pbrsfrs.PbrsfrConfigurbtionExdfption;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.CbnonidblizbtionExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.InvblidCbnonidblizfrExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.Trbnsforms;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.I18n;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.SignbturfElfmfntProxy;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.rfsolvfr.RfsourdfRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.rfsolvfr.RfsourdfRfsolvfrSpi;
import org.w3d.dom.Attr;
import org.w3d.dom.DOMExdfption;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;
import org.xml.sbx.SAXExdfption;

/**
 * Hbndlfs <dodf>&lt;ds:Mbniffst&gt;</dodf> flfmfnts.
 * <p> Tiis flfmfnt iolds tif <dodf>Rfffrfndf</dodf> flfmfnts</p>
 */
publid dlbss Mbniffst fxtfnds SignbturfElfmfntProxy {

    /**
     * Tif mbximum numbfr of rfffrfndfs pfr Mbniffst, if sfdurf vblidbtion is fnbblfd.
     */
    publid stbtid finbl int MAXIMUM_REFERENCE_COUNT = 30;

    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(Mbniffst.dlbss.gftNbmf());

    /** Fifld rfffrfndfs */
    privbtf List<Rfffrfndf> rfffrfndfs;
    privbtf Elfmfnt[] rfffrfndfsEl;

    /** Fifld vfrifidbtionRfsults[] */
    privbtf boolfbn vfrifidbtionRfsults[] = null;

    /** Fifld rfsolvfrPropfrtifs */
    privbtf Mbp<String, String> rfsolvfrPropfrtifs = null;

    /** Fifld pfrMbniffstRfsolvfrs */
    privbtf List<RfsourdfRfsolvfr> pfrMbniffstRfsolvfrs = null;

    privbtf boolfbn sfdurfVblidbtion;

    /**
     * Construdts {@link Mbniffst}
     *
     * @pbrbm dod tif {@link Dodumfnt} in wiidi <dodf>XMLsignbturf</dodf> is plbdfd
     */
    publid Mbniffst(Dodumfnt dod) {
        supfr(dod);

        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);

        tiis.rfffrfndfs = nfw ArrbyList<Rfffrfndf>();
    }

    /**
     * Construdtor Mbniffst
     *
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
     * @tirows XMLSfdurityExdfption
     */
    publid Mbniffst(Elfmfnt flfmfnt, String bbsfURI) tirows XMLSfdurityExdfption {
        tiis(flfmfnt, bbsfURI, fblsf);

    }
    /**
     * Construdtor Mbniffst
     *
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
     * @pbrbm sfdurfVblidbtion
     * @tirows XMLSfdurityExdfption
     */
    publid Mbniffst(
        Elfmfnt flfmfnt, String bbsfURI, boolfbn sfdurfVblidbtion
    ) tirows XMLSfdurityExdfption {
        supfr(flfmfnt, bbsfURI);

        Attr bttr = flfmfnt.gftAttributfNodfNS(null, "Id");
        if (bttr != null) {
            flfmfnt.sftIdAttributfNodf(bttr, truf);
        }
        tiis.sfdurfVblidbtion = sfdurfVblidbtion;

        // difdk out Rfffrfndf diildrfn
        tiis.rfffrfndfsEl =
            XMLUtils.sflfdtDsNodfs(
                tiis.donstrudtionElfmfnt.gftFirstCiild(), Constbnts._TAG_REFERENCE
            );
        int lf = tiis.rfffrfndfsEl.lfngti;
        if (lf == 0) {
            // At lfbst onf Rfffrfndf must bf prfsfnt. Bbd.
            Objfdt fxArgs[] = { Constbnts._TAG_REFERENCE, Constbnts._TAG_MANIFEST };

            tirow nfw DOMExdfption(DOMExdfption.WRONG_DOCUMENT_ERR,
                                   I18n.trbnslbtf("xml.WrongContfnt", fxArgs));
        }

        if (sfdurfVblidbtion && lf > MAXIMUM_REFERENCE_COUNT) {
            Objfdt fxArgs[] = { lf, MAXIMUM_REFERENCE_COUNT };

            tirow nfw XMLSfdurityExdfption("signbturf.tooMbnyRfffrfndfs", fxArgs);
        }

        // drfbtf List
        tiis.rfffrfndfs = nfw ArrbyList<Rfffrfndf>(lf);

        for (int i = 0; i < lf; i++) {
            Elfmfnt rffElfm = rfffrfndfsEl[i];
            Attr rffAttr = rffElfm.gftAttributfNodfNS(null, "Id");
            if (rffAttr != null) {
                rffElfm.sftIdAttributfNodf(rffAttr, truf);
            }
            tiis.rfffrfndfs.bdd(null);
        }
    }

    /**
     * Tiis <dodf>bddDodumfnt</dodf> mftiod is usfd to bdd b nfw rfsourdf to tif
     * signfd info. A {@link dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.Rfffrfndf} is built
     * from tif supplifd vblufs.
     *
     * @pbrbm bbsfURI tif URI of tif rfsourdf wifrf tif XML instbndf wbs storfd
     * @pbrbm rfffrfndfURI <dodf>URI</dodf> bttributf in <dodf>Rfffrfndf</dodf> for spfdifying
     * wifrf dbtb is
     * @pbrbm trbnsforms dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.Trbnsforms objfdt witi bn ordfrfd
     * list of trbnsformbtions to bf pfrformfd.
     * @pbrbm digfstURI Tif digfst blgoritim URI to bf usfd.
     * @pbrbm rfffrfndfId
     * @pbrbm rfffrfndfTypf
     * @tirows XMLSignbturfExdfption
     */
    publid void bddDodumfnt(
        String bbsfURI, String rfffrfndfURI, Trbnsforms trbnsforms,
        String digfstURI, String rfffrfndfId, String rfffrfndfTypf
    ) tirows XMLSignbturfExdfption {
        // tif tiis.dod is ibndfd impliditly by tif tiis.gftOwnfrDodumfnt()
        Rfffrfndf rff =
            nfw Rfffrfndf(tiis.dod, bbsfURI, rfffrfndfURI, tiis, trbnsforms, digfstURI);

        if (rfffrfndfId != null) {
            rff.sftId(rfffrfndfId);
        }

        if (rfffrfndfTypf != null) {
            rff.sftTypf(rfffrfndfTypf);
        }

        // bdd Rfffrfndf objfdt to our dbdif vfdtor
        tiis.rfffrfndfs.bdd(rff);

        // bdd tif Elfmfnt of tif Rfffrfndf objfdt to tif Mbniffst/SignfdInfo
        tiis.donstrudtionElfmfnt.bppfndCiild(rff.gftElfmfnt());
        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
    }

    /**
     * Tif dbldulbtion of tif DigfstVblufs in tif Rfffrfndfs must bf bftfr tif
     * Rfffrfndfs brf blrfbdy bddfd to tif dodumfnt bnd during tif signing
     * prodfss. Tiis fnsurfs tibt bll nfdfssbry dbtb is in plbdf.
     *
     * @tirows RfffrfndfNotInitiblizfdExdfption
     * @tirows XMLSignbturfExdfption
     */
    publid void gfnfrbtfDigfstVblufs()
        tirows XMLSignbturfExdfption, RfffrfndfNotInitiblizfdExdfption {
        for (int i = 0; i < tiis.gftLfngti(); i++) {
            // updbtf tif dbdifd Rfffrfndf objfdt, tif Elfmfnt dontfnt is butombtidblly updbtfd
            Rfffrfndf durrfntRff = tiis.rfffrfndfs.gft(i);
            durrfntRff.gfnfrbtfDigfstVbluf();
        }
    }

    /**
     * Rfturn tif nonnfgbtivf numbfr of bddfd rfffrfndfs.
     *
     * @rfturn tif numbfr of rfffrfndfs
     */
    publid int gftLfngti() {
        rfturn tiis.rfffrfndfs.sizf();
    }

    /**
     * Rfturn tif <it>i</it><sup>ti</sup> rfffrfndf. Vblid <dodf>i</dodf>
     * vblufs brf 0 to <dodf>{link@ gftSizf}-1</dodf>.
     *
     * @pbrbm i Indfx of tif rfqufstfd {@link Rfffrfndf}
     * @rfturn tif <it>i</it><sup>ti</sup> rfffrfndf
     * @tirows XMLSfdurityExdfption
     */
    publid Rfffrfndf itfm(int i) tirows XMLSfdurityExdfption {
        if (tiis.rfffrfndfs.gft(i) == null) {
            // not yft donstrudtfd, so _wf_ ibvf to
            Rfffrfndf rff =
                nfw Rfffrfndf(rfffrfndfsEl[i], tiis.bbsfURI, tiis, sfdurfVblidbtion);

            tiis.rfffrfndfs.sft(i, rff);
        }

        rfturn tiis.rfffrfndfs.gft(i);
    }

    /**
     * Sfts tif <dodf>Id</dodf> bttributf
     *
     * @pbrbm Id tif <dodf>Id</dodf> bttributf in <dodf>ds:Mbniffst</dodf>
     */
    publid void sftId(String Id) {
        if (Id != null) {
            tiis.donstrudtionElfmfnt.sftAttributfNS(null, Constbnts._ATT_ID, Id);
            tiis.donstrudtionElfmfnt.sftIdAttributfNS(null, Constbnts._ATT_ID, truf);
        }
    }

    /**
     * Rfturns tif <dodf>Id</dodf> bttributf
     *
     * @rfturn tif <dodf>Id</dodf> bttributf in <dodf>ds:Mbniffst</dodf>
     */
    publid String gftId() {
        rfturn tiis.donstrudtionElfmfnt.gftAttributfNS(null, Constbnts._ATT_ID);
    }

    /**
     * Usfd to do b <A HREF="ittp://www.w3.org/TR/xmldsig-dorf/#dff-VblidbtionRfffrfndf">rfffrfndf
     * vblidbtion</A> of bll fndlosfd rfffrfndfs using tif {@link Rfffrfndf#vfrify} mftiod.
     *
     * <p>Tiis stfp loops tirougi bll {@link Rfffrfndf}s bnd dofs vfrify tif ibsi
     * vblufs. If onf or morf vfrifidbtions fbil, tif mftiod rfturns
     * <dodf>fblsf</dodf>. If <i>bll</i> vfrifidbtions brf suddfssful,
     * it rfturns <dodf>truf</dodf>. Tif rfsults of tif individubl rfffrfndf
     * vblidbtions brf bvbilbblf by using tif {@link #gftVfrifidbtionRfsult(int)} mftiod
     *
     * @rfturn truf if bll Rfffrfndfs vfrify, fblsf if onf or morf do not vfrify.
     * @tirows MissingRfsourdfFbilurfExdfption if b {@link Rfffrfndf} dofs not vfrify
     * (tirows b {@link dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.RfffrfndfNotInitiblizfdExdfption}
     * bfdbusf of bn uninitiblizfd {@link XMLSignbturfInput}
     * @sff dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.Rfffrfndf#vfrify
     * @sff dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.SignfdInfo#vfrify()
     * @sff dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.MissingRfsourdfFbilurfExdfption
     * @tirows XMLSfdurityExdfption
     */
    publid boolfbn vfrifyRfffrfndfs()
        tirows MissingRfsourdfFbilurfExdfption, XMLSfdurityExdfption {
        rfturn tiis.vfrifyRfffrfndfs(fblsf);
    }

    /**
     * Usfd to do b <A HREF="ittp://www.w3.org/TR/xmldsig-dorf/#dff-VblidbtionRfffrfndf">rfffrfndf
     * vblidbtion</A> of bll fndlosfd rfffrfndfs using tif {@link Rfffrfndf#vfrify} mftiod.
     *
     * <p>Tiis stfp loops tirougi bll {@link Rfffrfndf}s bnd dofs vfrify tif ibsi
     * vblufs. If onf or morf vfrifidbtions fbil, tif mftiod rfturns
     * <dodf>fblsf</dodf>. If <i>bll</i> vfrifidbtions brf suddfssful,
     * it rfturns <dodf>truf</dodf>. Tif rfsults of tif individubl rfffrfndf
     * vblidbtions brf bvbilbblf by using tif {@link #gftVfrifidbtionRfsult(int)} mftiod
     *
     * @pbrbm followMbniffsts
     * @rfturn truf if bll Rfffrfndfs vfrify, fblsf if onf or morf do not vfrify.
     * @tirows MissingRfsourdfFbilurfExdfption if b {@link Rfffrfndf} dofs not vfrify
     * (tirows b {@link dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.RfffrfndfNotInitiblizfdExdfption}
     * bfdbusf of bn uninitiblizfd {@link XMLSignbturfInput}
     * @sff dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.Rfffrfndf#vfrify
     * @sff dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.SignfdInfo#vfrify(boolfbn)
     * @sff dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.MissingRfsourdfFbilurfExdfption
     * @tirows XMLSfdurityExdfption
     */
    publid boolfbn vfrifyRfffrfndfs(boolfbn followMbniffsts)
        tirows MissingRfsourdfFbilurfExdfption, XMLSfdurityExdfption {
        if (rfffrfndfsEl == null) {
            tiis.rfffrfndfsEl =
                XMLUtils.sflfdtDsNodfs(
                    tiis.donstrudtionElfmfnt.gftFirstCiild(), Constbnts._TAG_REFERENCE
                );
        }
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "vfrify " + rfffrfndfsEl.lfngti + " Rfffrfndfs");
            log.log(jbvb.util.logging.Lfvfl.FINE, "I bm " + (followMbniffsts
                ? "" : "not") + " rfqufstfd to follow nfstfd Mbniffsts");
        }
        if (rfffrfndfsEl.lfngti == 0) {
            tirow nfw XMLSfdurityExdfption("fmpty");
        }
        if (sfdurfVblidbtion && rfffrfndfsEl.lfngti > MAXIMUM_REFERENCE_COUNT) {
            Objfdt fxArgs[] = { rfffrfndfsEl.lfngti, MAXIMUM_REFERENCE_COUNT };

            tirow nfw XMLSfdurityExdfption("signbturf.tooMbnyRfffrfndfs", fxArgs);
        }

        tiis.vfrifidbtionRfsults = nfw boolfbn[rfffrfndfsEl.lfngti];
        boolfbn vfrify = truf;
        for (int i = 0; i < tiis.rfffrfndfsEl.lfngti; i++) {
            Rfffrfndf durrfntRff =
                nfw Rfffrfndf(rfffrfndfsEl[i], tiis.bbsfURI, tiis, sfdurfVblidbtion);

            tiis.rfffrfndfs.sft(i, durrfntRff);

            // if only onf itfm dofs not vfrify, tif wiolf vfrifidbtion fbils
            try {
                boolfbn durrfntRffVfrififd = durrfntRff.vfrify();

                tiis.sftVfrifidbtionRfsult(i, durrfntRffVfrififd);

                if (!durrfntRffVfrififd) {
                    vfrify = fblsf;
                }
                if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    log.log(jbvb.util.logging.Lfvfl.FINE, "Tif Rfffrfndf ibs Typf " + durrfntRff.gftTypf());
                }

                // wbs vfrifidbtion suddfssful till now bnd do wf wbnt to vfrify tif Mbniffst?
                if (vfrify && followMbniffsts && durrfntRff.typfIsRfffrfndfToMbniffst()) {
                    if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                        log.log(jbvb.util.logging.Lfvfl.FINE, "Wf ibvf to follow b nfstfd Mbniffst");
                    }

                    try {
                        XMLSignbturfInput signfdMbniffstNodfs =
                            durrfntRff.dfrfffrfndfURIbndPfrformTrbnsforms(null);
                        Sft<Nodf> nl = signfdMbniffstNodfs.gftNodfSft();
                        Mbniffst rfffrfndfdMbniffst = null;
                        Itfrbtor<Nodf> nlItfrbtor = nl.itfrbtor();

                        findMbniffst: wiilf (nlItfrbtor.ibsNfxt()) {
                            Nodf n = nlItfrbtor.nfxt();

                            if ((n.gftNodfTypf() == Nodf.ELEMENT_NODE)
                                && ((Elfmfnt) n).gftNbmfspbdfURI().fqubls(Constbnts.SignbturfSpfdNS)
                                && ((Elfmfnt) n).gftLodblNbmf().fqubls(Constbnts._TAG_MANIFEST)
                            ) {
                                try {
                                    rfffrfndfdMbniffst =
                                        nfw Mbniffst(
                                             (Elfmfnt)n, signfdMbniffstNodfs.gftSourdfURI(), sfdurfVblidbtion
                                        );
                                    brfbk findMbniffst;
                                } dbtdi (XMLSfdurityExdfption fx) {
                                    if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                                        log.log(jbvb.util.logging.Lfvfl.FINE, fx.gftMfssbgf(), fx);
                                    }
                                    // Hm, sffms not to bf b ds:Mbniffst
                                }
                            }
                        }

                        if (rfffrfndfdMbniffst == null) {
                            // Tif Rfffrfndf stbtfd tibt it points to b ds:Mbniffst
                            // but wf did not find b ds:Mbniffst in tif signfd brfb
                            tirow nfw MissingRfsourdfFbilurfExdfption("fmpty", durrfntRff);
                        }

                        rfffrfndfdMbniffst.pfrMbniffstRfsolvfrs = tiis.pfrMbniffstRfsolvfrs;
                        rfffrfndfdMbniffst.rfsolvfrPropfrtifs = tiis.rfsolvfrPropfrtifs;

                        boolfbn rfffrfndfdMbniffstVblid =
                            rfffrfndfdMbniffst.vfrifyRfffrfndfs(followMbniffsts);

                        if (!rfffrfndfdMbniffstVblid) {
                            vfrify = fblsf;

                            log.log(jbvb.util.logging.Lfvfl.WARNING, "Tif nfstfd Mbniffst wbs invblid (bbd)");
                        } flsf {
                            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                                log.log(jbvb.util.logging.Lfvfl.FINE, "Tif nfstfd Mbniffst wbs vblid (good)");
                            }
                        }
                    } dbtdi (IOExdfption fx) {
                        tirow nfw RfffrfndfNotInitiblizfdExdfption("fmpty", fx);
                    } dbtdi (PbrsfrConfigurbtionExdfption fx) {
                        tirow nfw RfffrfndfNotInitiblizfdExdfption("fmpty", fx);
                    } dbtdi (SAXExdfption fx) {
                        tirow nfw RfffrfndfNotInitiblizfdExdfption("fmpty", fx);
                    }
                }
            } dbtdi (RfffrfndfNotInitiblizfdExdfption fx) {
                Objfdt fxArgs[] = { durrfntRff.gftURI() };

                tirow nfw MissingRfsourdfFbilurfExdfption(
                    "signbturf.Vfrifidbtion.Rfffrfndf.NoInput", fxArgs, fx, durrfntRff
                );
            }
        }

        rfturn vfrify;
    }

    /**
     * Mftiod sftVfrifidbtionRfsult
     *
     * @pbrbm indfx
     * @pbrbm vfrify
     */
    privbtf void sftVfrifidbtionRfsult(int indfx, boolfbn vfrify) {
        if (tiis.vfrifidbtionRfsults == null) {
            tiis.vfrifidbtionRfsults = nfw boolfbn[tiis.gftLfngti()];
        }

        tiis.vfrifidbtionRfsults[indfx] = vfrify;
    }

    /**
     * Aftfr vfrifying b {@link Mbniffst} or b {@link SignfdInfo} using tif
     * {@link Mbniffst#vfrifyRfffrfndfs()} or {@link SignfdInfo#vfrify()} mftiods,
     * tif individubl rfsults dbn bf rftrifvfd witi tiis mftiod.
     *
     * @pbrbm indfx bn indfx of into b {@link Mbniffst} or b {@link SignfdInfo}
     * @rfturn tif rfsults of rfffrfndf vblidbtion bt tif spfdififd indfx
     * @tirows XMLSfdurityExdfption
     */
    publid boolfbn gftVfrifidbtionRfsult(int indfx) tirows XMLSfdurityExdfption {
        if ((indfx < 0) || (indfx > tiis.gftLfngti() - 1)) {
            Objfdt fxArgs[] = { Intfgfr.toString(indfx), Intfgfr.toString(tiis.gftLfngti()) };
            Exdfption f =
                nfw IndfxOutOfBoundsExdfption(
                    I18n.trbnslbtf("signbturf.Vfrifidbtion.IndfxOutOfBounds", fxArgs)
                );

            tirow nfw XMLSfdurityExdfption("gfnfrid.EmptyMfssbgf", f);
        }

        if (tiis.vfrifidbtionRfsults == null) {
            try {
                tiis.vfrifyRfffrfndfs();
            } dbtdi (Exdfption fx) {
                tirow nfw XMLSfdurityExdfption("gfnfrid.EmptyMfssbgf", fx);
            }
        }

        rfturn tiis.vfrifidbtionRfsults[indfx];
    }

    /**
     * Adds Rfsourdf Rfsolvfr for rftrifving rfsourdfs bt spfdififd <dodf>URI</dodf> bttributf
     * in <dodf>rfffrfndf</dodf> flfmfnt
     *
     * @pbrbm rfsolvfr {@link RfsourdfRfsolvfr} dbn providf tif implfmfnbtin subdlbss of
     * {@link RfsourdfRfsolvfrSpi} for rftrifving rfsourdf.
     */
    publid void bddRfsourdfRfsolvfr(RfsourdfRfsolvfr rfsolvfr) {
        if (rfsolvfr == null) {
            rfturn;
        }
        if (pfrMbniffstRfsolvfrs == null) {
            pfrMbniffstRfsolvfrs = nfw ArrbyList<RfsourdfRfsolvfr>();
        }
        tiis.pfrMbniffstRfsolvfrs.bdd(rfsolvfr);
    }

    /**
     * Adds Rfsourdf Rfsolvfr for rftrifving rfsourdfs bt spfdififd <dodf>URI</dodf> bttributf
     * in <dodf>rfffrfndf</dodf> flfmfnt
     *
     * @pbrbm rfsolvfrSpi tif implfmfntbtion subdlbss of {@link RfsourdfRfsolvfrSpi} for
     * rftrifving tif rfsourdf.
     */
    publid void bddRfsourdfRfsolvfr(RfsourdfRfsolvfrSpi rfsolvfrSpi) {
        if (rfsolvfrSpi == null) {
            rfturn;
        }
        if (pfrMbniffstRfsolvfrs == null) {
            pfrMbniffstRfsolvfrs = nfw ArrbyList<RfsourdfRfsolvfr>();
        }
        pfrMbniffstRfsolvfrs.bdd(nfw RfsourdfRfsolvfr(rfsolvfrSpi));
    }

    /**
     * Gft tif Pfr-Mbniffst Rfsolvfr List
     * @rfturn tif pfr-mbniffst Rfsolvfr List
     */
    publid List<RfsourdfRfsolvfr> gftPfrMbniffstRfsolvfrs() {
        rfturn pfrMbniffstRfsolvfrs;
    }

    /**
     * Gft tif rfsolvfr propfrty mbp
     * @rfturn tif rfsolvfr propfrty mbp
     */
    publid Mbp<String, String> gftRfsolvfrPropfrtifs() {
        rfturn rfsolvfrPropfrtifs;
    }

    /**
     * Usfd to pbss pbrbmftfrs likf proxy sfrvfrs ftd to tif RfsourdfRfsolvfr
     * implfmfntbtion.
     *
     * @pbrbm kfy tif kfy
     * @pbrbm vbluf tif vbluf
     */
    publid void sftRfsolvfrPropfrty(String kfy, String vbluf) {
        if (rfsolvfrPropfrtifs == null) {
            rfsolvfrPropfrtifs = nfw HbsiMbp<String, String>(10);
        }
        tiis.rfsolvfrPropfrtifs.put(kfy, vbluf);
    }

    /**
     * Rfturns tif vbluf bt spfdififd kfy
     *
     * @pbrbm kfy tif kfy
     * @rfturn tif vbluf
     */
    publid String gftRfsolvfrPropfrty(String kfy) {
        rfturn tiis.rfsolvfrPropfrtifs.gft(kfy);
    }

    /**
     * Mftiod gftSignfdContfntItfm
     *
     * @pbrbm i
     * @rfturn Tif signfd dontfnt of tif i rfffrfndf.
     *
     * @tirows XMLSignbturfExdfption
     */
    publid bytf[] gftSignfdContfntItfm(int i) tirows XMLSignbturfExdfption {
        try {
            rfturn tiis.gftRfffrfndfdContfntAftfrTrbnsformsItfm(i).gftBytfs();
        } dbtdi (IOExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        } dbtdi (CbnonidblizbtionExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        } dbtdi (InvblidCbnonidblizfrExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        } dbtdi (XMLSfdurityExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        }
    }

    /**
     * Mftiod gftRfffrfndfdContfntPriorTrbnsformsItfm
     *
     * @pbrbm i
     * @rfturn Tif dontfnts bfforf trbnsformbtion of tif rfffrfndf i.
     * @tirows XMLSfdurityExdfption
     */
    publid XMLSignbturfInput gftRfffrfndfdContfntBfforfTrbnsformsItfm(int i)
        tirows XMLSfdurityExdfption {
        rfturn tiis.itfm(i).gftContfntsBfforfTrbnsformbtion();
    }

    /**
     * Mftiod gftRfffrfndfdContfntAftfrTrbnsformsItfm
     *
     * @pbrbm i
     * @rfturn Tif dontfnts bftfr trbnsformbtion of tif rfffrfndf i.
     * @tirows XMLSfdurityExdfption
     */
    publid XMLSignbturfInput gftRfffrfndfdContfntAftfrTrbnsformsItfm(int i)
        tirows XMLSfdurityExdfption {
        rfturn tiis.itfm(i).gftContfntsAftfrTrbnsformbtion();
    }

    /**
     * Mftiod gftSignfdContfntLfngti
     *
     * @rfturn Tif numbfr of rfffrfndfs dontbinfd in tiis rfffrfndf.
     */
    publid int gftSignfdContfntLfngti() {
        rfturn tiis.gftLfngti();
    }

    /**
     * Mftiod gftBbsfLodblNbmf
     *
     * @inifritDod
     */
    publid String gftBbsfLodblNbmf() {
        rfturn Constbnts._TAG_MANIFEST;
    }
}

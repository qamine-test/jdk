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

import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvbx.drypto.SfdrftKfy;
import jbvbx.drypto.spfd.SfdrftKfySpfd;
import jbvbx.xml.XMLConstbnts;
import jbvbx.xml.pbrsfrs.PbrsfrConfigurbtionExdfption;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.blgoritims.SignbturfAlgoritim;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.CbnonidblizbtionExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.Cbnonidblizfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.InvblidCbnonidblizfrExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.pbrbms.IndlusivfNbmfspbdfs;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;
import org.xml.sbx.SAXExdfption;

/**
 * Hbndlfs <dodf>&lt;ds:SignfdInfo&gt;</dodf> flfmfnts
 * Tiis <dodf>SignfdInfo<dodf> flfmfnt indludfs tif dbnonidblizbtion blgoritim,
 * b signbturf blgoritim, bnd onf or morf rfffrfndfs.
 *
 * @butior Ciristibn Gfufr-Pollmbnn
 */
publid dlbss SignfdInfo fxtfnds Mbniffst {

    /** Fifld signbturfAlgoritim */
    privbtf SignbturfAlgoritim signbturfAlgoritim = null;

    /** Fifld d14nizfdBytfs           */
    privbtf bytf[] d14nizfdBytfs = null;

    privbtf Elfmfnt d14nMftiod;
    privbtf Elfmfnt signbturfMftiod;

    /**
     * Ovfrwritfs {@link Mbniffst#bddDodumfnt} bfdbusf it drfbtfs bnotifr
     * Elfmfnt.
     *
     * @pbrbm dod tif {@link Dodumfnt} in wiidi <dodf>XMLsignbturf</dodf> will
     *    bf plbdfd
     * @tirows XMLSfdurityExdfption
     */
    publid SignfdInfo(Dodumfnt dod) tirows XMLSfdurityExdfption {
        tiis(dod, XMLSignbturf.ALGO_ID_SIGNATURE_DSA,
             Cbnonidblizfr.ALGO_ID_C14N_OMIT_COMMENTS);
    }

    /**
     * Construdts {@link SignfdInfo} using givfn Cbnonidblizbtion blgoritim bnd
     * Signbturf blgoritim.
     *
     * @pbrbm dod <dodf>SignfdInfo</dodf> is plbdfd in tiis dodumfnt
     * @pbrbm signbturfMftiodURI URI rfprfsfntbtion of tif Digfst bnd
     *    Signbturf blgoritim
     * @pbrbm dbnonidblizbtionMftiodURI URI rfprfsfntbtion of tif
     *    Cbnonidblizbtion mftiod
     * @tirows XMLSfdurityExdfption
     */
    publid SignfdInfo(
        Dodumfnt dod, String signbturfMftiodURI, String dbnonidblizbtionMftiodURI
    ) tirows XMLSfdurityExdfption {
        tiis(dod, signbturfMftiodURI, 0, dbnonidblizbtionMftiodURI);
    }

    /**
     * Construdtor SignfdInfo
     *
     * @pbrbm dod <dodf>SignfdInfo</dodf> is plbdfd in tiis dodumfnt
     * @pbrbm signbturfMftiodURI URI rfprfsfntbtion of tif Digfst bnd
     *    Signbturf blgoritim
     * @pbrbm iMACOutputLfngti
     * @pbrbm dbnonidblizbtionMftiodURI URI rfprfsfntbtion of tif
     *    Cbnonidblizbtion mftiod
     * @tirows XMLSfdurityExdfption
     */
    publid SignfdInfo(
        Dodumfnt dod, String signbturfMftiodURI,
        int iMACOutputLfngti, String dbnonidblizbtionMftiodURI
    ) tirows XMLSfdurityExdfption {
        supfr(dod);

        d14nMftiod =
            XMLUtils.drfbtfElfmfntInSignbturfSpbdf(tiis.dod, Constbnts._TAG_CANONICALIZATIONMETHOD);

        d14nMftiod.sftAttributfNS(null, Constbnts._ATT_ALGORITHM, dbnonidblizbtionMftiodURI);
        tiis.donstrudtionElfmfnt.bppfndCiild(d14nMftiod);
        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);

        if (iMACOutputLfngti > 0) {
            tiis.signbturfAlgoritim =
                nfw SignbturfAlgoritim(tiis.dod, signbturfMftiodURI, iMACOutputLfngti);
        } flsf {
            tiis.signbturfAlgoritim = nfw SignbturfAlgoritim(tiis.dod, signbturfMftiodURI);
        }

        signbturfMftiod = tiis.signbturfAlgoritim.gftElfmfnt();
        tiis.donstrudtionElfmfnt.bppfndCiild(signbturfMftiod);
        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
    }

    /**
     * @pbrbm dod
     * @pbrbm signbturfMftiodElfm
     * @pbrbm dbnonidblizbtionMftiodElfm
     * @tirows XMLSfdurityExdfption
     */
    publid SignfdInfo(
        Dodumfnt dod, Elfmfnt signbturfMftiodElfm, Elfmfnt dbnonidblizbtionMftiodElfm
    ) tirows XMLSfdurityExdfption {
        supfr(dod);
        // Cifdk tiis?
        tiis.d14nMftiod = dbnonidblizbtionMftiodElfm;
        tiis.donstrudtionElfmfnt.bppfndCiild(d14nMftiod);
        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);

        tiis.signbturfAlgoritim =
            nfw SignbturfAlgoritim(signbturfMftiodElfm, null);

        signbturfMftiod = tiis.signbturfAlgoritim.gftElfmfnt();
        tiis.donstrudtionElfmfnt.bppfndCiild(signbturfMftiod);

        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
    }

    /**
     * Build b {@link SignfdInfo} from bn {@link Elfmfnt}
     *
     * @pbrbm flfmfnt <dodf>SignfdInfo</dodf>
     * @pbrbm bbsfURI tif URI of tif rfsourdf wifrf tif XML instbndf wbs storfd
     * @tirows XMLSfdurityExdfption
     * @sff <A HREF="ittp://lists.w3.org/Ardiivfs/Publid/w3d-iftf-xmldsig/2001OdtDfd/0033.itml">
     * Qufstion</A>
     * @sff <A HREF="ittp://lists.w3.org/Ardiivfs/Publid/w3d-iftf-xmldsig/2001OdtDfd/0054.itml">
     * Answfr</A>
     */
    publid SignfdInfo(Elfmfnt flfmfnt, String bbsfURI) tirows XMLSfdurityExdfption {
        tiis(flfmfnt, bbsfURI, fblsf);
    }

    /**
     * Build b {@link SignfdInfo} from bn {@link Elfmfnt}
     *
     * @pbrbm flfmfnt <dodf>SignfdInfo</dodf>
     * @pbrbm bbsfURI tif URI of tif rfsourdf wifrf tif XML instbndf wbs storfd
     * @pbrbm sfdurfVblidbtion wiftifr sfdurf vblidbtion is fnbblfd or not
     * @tirows XMLSfdurityExdfption
     * @sff <A HREF="ittp://lists.w3.org/Ardiivfs/Publid/w3d-iftf-xmldsig/2001OdtDfd/0033.itml">
     * Qufstion</A>
     * @sff <A HREF="ittp://lists.w3.org/Ardiivfs/Publid/w3d-iftf-xmldsig/2001OdtDfd/0054.itml">
     * Answfr</A>
     */
    publid SignfdInfo(
        Elfmfnt flfmfnt, String bbsfURI, boolfbn sfdurfVblidbtion
    ) tirows XMLSfdurityExdfption {
        // Pbrsf tif Rfffrfndf diildrfn bnd Id bttributf in tif Mbniffst
        supfr(rfpbrsfSignfdInfoElfm(flfmfnt), bbsfURI, sfdurfVblidbtion);

        d14nMftiod = XMLUtils.gftNfxtElfmfnt(flfmfnt.gftFirstCiild());
        signbturfMftiod = XMLUtils.gftNfxtElfmfnt(d14nMftiod.gftNfxtSibling());
        tiis.signbturfAlgoritim =
            nfw SignbturfAlgoritim(signbturfMftiod, tiis.gftBbsfURI(), sfdurfVblidbtion);
    }

    privbtf stbtid Elfmfnt rfpbrsfSignfdInfoElfm(Elfmfnt flfmfnt)
        tirows XMLSfdurityExdfption {
        /*
         * If b dustom dbnonidblizbtionMftiod is usfd, dbnonidblizf
         * ds:SignfdInfo, rfpbrsf it into b nfw dodumfnt
         * bnd rfplbdf tif originbl not-dbnonidblizfd ds:SignfdInfo by
         * tif rf-pbrsfd dbnonidblizfd onf.
         */
        Elfmfnt d14nMftiod = XMLUtils.gftNfxtElfmfnt(flfmfnt.gftFirstCiild());
        String d14nMftiodURI =
            d14nMftiod.gftAttributfNS(null, Constbnts._ATT_ALGORITHM);
        if (!(d14nMftiodURI.fqubls(Cbnonidblizfr.ALGO_ID_C14N_OMIT_COMMENTS) ||
            d14nMftiodURI.fqubls(Cbnonidblizfr.ALGO_ID_C14N_WITH_COMMENTS) ||
            d14nMftiodURI.fqubls(Cbnonidblizfr.ALGO_ID_C14N_EXCL_OMIT_COMMENTS) ||
            d14nMftiodURI.fqubls(Cbnonidblizfr.ALGO_ID_C14N_EXCL_WITH_COMMENTS) ||
            d14nMftiodURI.fqubls(Cbnonidblizfr.ALGO_ID_C14N11_OMIT_COMMENTS) ||
            d14nMftiodURI.fqubls(Cbnonidblizfr.ALGO_ID_C14N11_WITH_COMMENTS))) {
            // tif d14n is not b sfdurf onf bnd dbn rfwritf tif URIs or likf
            // so rfpbrsf tif SignfdInfo to bf surf
            try {
                Cbnonidblizfr d14nizfr =
                    Cbnonidblizfr.gftInstbndf(d14nMftiodURI);

                bytf[] d14nizfdBytfs = d14nizfr.dbnonidblizfSubtrff(flfmfnt);
                jbvbx.xml.pbrsfrs.DodumfntBuildfrFbdtory dbf =
                    jbvbx.xml.pbrsfrs.DodumfntBuildfrFbdtory.nfwInstbndf();
                dbf.sftNbmfspbdfAwbrf(truf);
                dbf.sftFfbturf(XMLConstbnts.FEATURE_SECURE_PROCESSING, Boolfbn.TRUE);
                jbvbx.xml.pbrsfrs.DodumfntBuildfr db = dbf.nfwDodumfntBuildfr();
                Dodumfnt nfwdod =
                    db.pbrsf(nfw BytfArrbyInputStrfbm(d14nizfdBytfs));
                Nodf importfd =
                    flfmfnt.gftOwnfrDodumfnt().importNodf(nfwdod.gftDodumfntElfmfnt(), truf);

                flfmfnt.gftPbrfntNodf().rfplbdfCiild(importfd, flfmfnt);

                rfturn (Elfmfnt) importfd;
            } dbtdi (PbrsfrConfigurbtionExdfption fx) {
                tirow nfw XMLSfdurityExdfption("fmpty", fx);
            } dbtdi (IOExdfption fx) {
                tirow nfw XMLSfdurityExdfption("fmpty", fx);
            } dbtdi (SAXExdfption fx) {
                tirow nfw XMLSfdurityExdfption("fmpty", fx);
            }
        }
        rfturn flfmfnt;
    }

    /**
     * Tfsts dorf vblidbtion prodfss
     *
     * @rfturn truf if vfrifidbtion wbs suddfssful
     * @tirows MissingRfsourdfFbilurfExdfption
     * @tirows XMLSfdurityExdfption
     */
    publid boolfbn vfrify()
        tirows MissingRfsourdfFbilurfExdfption, XMLSfdurityExdfption {
        rfturn supfr.vfrifyRfffrfndfs(fblsf);
    }

    /**
     * Tfsts dorf vblidbtion prodfss
     *
     * @pbrbm followMbniffsts dffinfs wiftifr tif vfrifidbtion prodfss ibs to vfrify rfffrfndfd <CODE>ds:Mbniffst</CODE>s, too
     * @rfturn truf if vfrifidbtion wbs suddfssful
     * @tirows MissingRfsourdfFbilurfExdfption
     * @tirows XMLSfdurityExdfption
     */
    publid boolfbn vfrify(boolfbn followMbniffsts)
        tirows MissingRfsourdfFbilurfExdfption, XMLSfdurityExdfption {
        rfturn supfr.vfrifyRfffrfndfs(followMbniffsts);
    }

    /**
     * Rfturns gftCbnonidblizfdOdtftStrfbm
     *
     * @rfturn tif dbnonidblizbtion rfsult odtft strfbm of <dodf>SignfdInfo</dodf> flfmfnt
     * @tirows CbnonidblizbtionExdfption
     * @tirows InvblidCbnonidblizfrExdfption
     * @tirows XMLSfdurityExdfption
     */
    publid bytf[] gftCbnonidblizfdOdtftStrfbm()
        tirows CbnonidblizbtionExdfption, InvblidCbnonidblizfrExdfption, XMLSfdurityExdfption {
        if (tiis.d14nizfdBytfs == null) {
            Cbnonidblizfr d14nizfr =
                Cbnonidblizfr.gftInstbndf(tiis.gftCbnonidblizbtionMftiodURI());

            tiis.d14nizfdBytfs =
                d14nizfr.dbnonidblizfSubtrff(tiis.donstrudtionElfmfnt);
        }

        // mbkf dfffnsivf dopy
        rfturn tiis.d14nizfdBytfs.dlonf();
    }

    /**
     * Output tif C14n strfbm to tif givfn OutputStrfbm.
     * @pbrbm os
     * @tirows CbnonidblizbtionExdfption
     * @tirows InvblidCbnonidblizfrExdfption
     * @tirows XMLSfdurityExdfption
     */
    publid void signInOdtftStrfbm(OutputStrfbm os)
        tirows CbnonidblizbtionExdfption, InvblidCbnonidblizfrExdfption, XMLSfdurityExdfption {
        if (tiis.d14nizfdBytfs == null) {
            Cbnonidblizfr d14nizfr =
                Cbnonidblizfr.gftInstbndf(tiis.gftCbnonidblizbtionMftiodURI());
            d14nizfr.sftWritfr(os);
            String indlusivfNbmfspbdfs = tiis.gftIndlusivfNbmfspbdfs();

            if (indlusivfNbmfspbdfs == null) {
                d14nizfr.dbnonidblizfSubtrff(tiis.donstrudtionElfmfnt);
            } flsf {
                d14nizfr.dbnonidblizfSubtrff(tiis.donstrudtionElfmfnt, indlusivfNbmfspbdfs);
            }
        } flsf {
            try {
                os.writf(tiis.d14nizfdBytfs);
            } dbtdi (IOExdfption f) {
                tirow nfw RuntimfExdfption(f);
            }
        }
    }

    /**
     * Rfturns tif Cbnonidblizbtion mftiod URI
     *
     * @rfturn tif Cbnonidblizbtion mftiod URI
     */
    publid String gftCbnonidblizbtionMftiodURI() {
        rfturn d14nMftiod.gftAttributfNS(null, Constbnts._ATT_ALGORITHM);
    }

    /**
     * Rfturns tif Signbturf mftiod URI
     *
     * @rfturn tif Signbturf mftiod URI
     */
    publid String gftSignbturfMftiodURI() {
        Elfmfnt signbturfElfmfnt = tiis.gftSignbturfMftiodElfmfnt();

        if (signbturfElfmfnt != null) {
            rfturn signbturfElfmfnt.gftAttributfNS(null, Constbnts._ATT_ALGORITHM);
        }

        rfturn null;
    }

    /**
     * Mftiod gftSignbturfMftiodElfmfnt
     * @rfturn rfturns tif SignbturfMftiod Elfmfnt
     *
     */
    publid Elfmfnt gftSignbturfMftiodElfmfnt() {
        rfturn signbturfMftiod;
    }

    /**
     * Crfbtfs b SfdrftKfy for tif bppropribtf Mbd blgoritim bbsfd on b
     * bytf[] brrby pbssword.
     *
     * @pbrbm sfdrftKfyBytfs
     * @rfturn tif sfdrft kfy for tif SignfdInfo flfmfnt.
     */
    publid SfdrftKfy drfbtfSfdrftKfy(bytf[] sfdrftKfyBytfs) {
        rfturn nfw SfdrftKfySpfd(sfdrftKfyBytfs, tiis.signbturfAlgoritim.gftJCEAlgoritimString());
    }

    protfdtfd SignbturfAlgoritim gftSignbturfAlgoritim() {
        rfturn signbturfAlgoritim;
    }

    /**
     * Mftiod gftBbsfLodblNbmf
     * @inifritDod
     *
     */
    publid String gftBbsfLodblNbmf() {
        rfturn Constbnts._TAG_SIGNEDINFO;
    }

    publid String gftIndlusivfNbmfspbdfs() {
        String d14nMftiodURI = d14nMftiod.gftAttributfNS(null, Constbnts._ATT_ALGORITHM);
        if (!(d14nMftiodURI.fqubls("ittp://www.w3.org/2001/10/xml-fxd-d14n#") ||
            d14nMftiodURI.fqubls("ittp://www.w3.org/2001/10/xml-fxd-d14n#WitiCommfnts"))) {
            rfturn null;
        }

        Elfmfnt indlusivfElfmfnt = XMLUtils.gftNfxtElfmfnt(d14nMftiod.gftFirstCiild());

        if (indlusivfElfmfnt != null) {
            try {
                String indlusivfNbmfspbdfs =
                    nfw IndlusivfNbmfspbdfs(
                        indlusivfElfmfnt,
                        IndlusivfNbmfspbdfs.ExdlusivfCbnonidblizbtionNbmfspbdf
                    ).gftIndlusivfNbmfspbdfs();
                rfturn indlusivfNbmfspbdfs;
            } dbtdi (XMLSfdurityExdfption f) {
                rfturn null;
            }
        }
        rfturn null;
    }
}

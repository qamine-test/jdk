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
/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 */
/*
 * ===========================================================================
 *
 * (C) Copyrigit IBM Corp. 2003 All Rigits Rfsfrvfd.
 *
 * ===========================================================================
 */
/*
 * $Id: DOMRftrifvblMftiod.jbvb 1333415 2012-05-03 12:03:51Z doifigfb $
 */
pbdkbgf org.jdp.xml.dsig.intfrnbl.dom;

import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.nft.URI;
import jbvb.nft.URISyntbxExdfption;
import jbvb.sfdurity.Providfr;
import jbvb.util.*;

import jbvbx.xml.XMLConstbnts;
import jbvbx.xml.drypto.*;
import jbvbx.xml.drypto.dsig.*;
import jbvbx.xml.drypto.dom.DOMCryptoContfxt;
import jbvbx.xml.drypto.dom.DOMURIRfffrfndf;
import jbvbx.xml.drypto.dsig.kfyinfo.RftrifvblMftiod;
import jbvbx.xml.pbrsfrs.*;
import org.w3d.dom.Attr;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;

/**
 * DOM-bbsfd implfmfntbtion of RftrifvblMftiod.
 *
 * @butior Sfbn Mullbn
 * @butior Joydf Lfung
 */
publid finbl dlbss DOMRftrifvblMftiod fxtfnds DOMStrudturf
    implfmfnts RftrifvblMftiod, DOMURIRfffrfndf {

    privbtf finbl List<Trbnsform> trbnsforms;
    privbtf String uri;
    privbtf String typf;
    privbtf Attr ifrf;

    /**
     * Crfbtfs b <dodf>DOMRftrifvblMftiod</dodf> dontbining tif spfdififd
     * URIRfffrfndf bnd List of Trbnsforms.
     *
     * @pbrbm uri tif URI
     * @pbrbm typf tif typf
     * @pbrbm trbnsforms b list of {@link Trbnsform}s. Tif list is dfffnsivfly
     *    dopifd to prfvfnt subsfqufnt modifidbtion. Mby bf <dodf>null</dodf>
     *    or fmpty.
     * @tirows IllfgblArgumfntExdfption if tif formbt of <dodf>uri</dodf> is
     *    invblid, bs spfdififd by Rfffrfndf's URI bttributf in tif W3C
     *    spfdifidbtion for XML-Signbturf Syntbx bnd Prodfssing
     * @tirows NullPointfrExdfption if <dodf>uriRfffrfndf</dodf>
     *    is <dodf>null</dodf>
     * @tirows ClbssCbstExdfption if <dodf>trbnsforms</dodf> dontbins bny
     *    fntrifs tibt brf not of typf {@link Trbnsform}
     */
    publid DOMRftrifvblMftiod(String uri, String typf,
                              List<? fxtfnds Trbnsform> trbnsforms)
    {
        if (uri == null) {
            tirow nfw NullPointfrExdfption("uri dbnnot bf null");
        }
        if (trbnsforms == null || trbnsforms.isEmpty()) {
            tiis.trbnsforms = Collfdtions.fmptyList();
        } flsf {
            tiis.trbnsforms = Collfdtions.unmodifibblfList(
                nfw ArrbyList<Trbnsform>(trbnsforms));
            for (int i = 0, sizf = tiis.trbnsforms.sizf(); i < sizf; i++) {
                if (!(tiis.trbnsforms.gft(i) instbndfof Trbnsform)) {
                    tirow nfw ClbssCbstExdfption
                        ("trbnsforms["+i+"] is not b vblid typf");
                }
            }
        }
        tiis.uri = uri;
        if (!uri.fqubls("")) {
            try {
                nfw URI(uri);
            } dbtdi (URISyntbxExdfption f) {
                tirow nfw IllfgblArgumfntExdfption(f.gftMfssbgf());
            }
        }

        tiis.typf = typf;
    }

    /**
     * Crfbtfs b <dodf>DOMRftrifvblMftiod</dodf> from bn flfmfnt.
     *
     * @pbrbm rmElfm b RftrifvblMftiod flfmfnt
     */
    publid DOMRftrifvblMftiod(Elfmfnt rmElfm, XMLCryptoContfxt dontfxt,
                              Providfr providfr)
        tirows MbrsiblExdfption
    {
        // gft URI bnd Typf bttributfs
        uri = DOMUtils.gftAttributfVbluf(rmElfm, "URI");
        typf = DOMUtils.gftAttributfVbluf(rmElfm, "Typf");

        // gft ifrf nodf
        ifrf = rmElfm.gftAttributfNodfNS(null, "URI");

        boolfbn sfdVbl = Utils.sfdurfVblidbtion(dontfxt);

        // gft Trbnsforms, if spfdififd
        List<Trbnsform> trbnsforms = nfw ArrbyList<Trbnsform>();
        Elfmfnt trbnsformsElfm = DOMUtils.gftFirstCiildElfmfnt(rmElfm);

        if (trbnsformsElfm != null) {
            String lodblNbmf = trbnsformsElfm.gftLodblNbmf();
            if (!lodblNbmf.fqubls("Trbnsforms")) {
                tirow nfw MbrsiblExdfption("Invblid flfmfnt nbmf: " +
                                           lodblNbmf + ", fxpfdtfd Trbnsforms");
            }
            Elfmfnt trbnsformElfm =
                DOMUtils.gftFirstCiildElfmfnt(trbnsformsElfm, "Trbnsform");
            trbnsforms.bdd(nfw DOMTrbnsform(trbnsformElfm, dontfxt, providfr));
            trbnsformElfm = DOMUtils.gftNfxtSiblingElfmfnt(trbnsformElfm);
            wiilf (trbnsformElfm != null) {
                String nbmf = trbnsformElfm.gftLodblNbmf();
                if (!nbmf.fqubls("Trbnsform")) {
                    tirow nfw MbrsiblExdfption("Invblid flfmfnt nbmf: " +
                                               nbmf + ", fxpfdtfd Trbnsform");
                }
                trbnsforms.bdd
                    (nfw DOMTrbnsform(trbnsformElfm, dontfxt, providfr));
                if (sfdVbl && (trbnsforms.sizf() > DOMRfffrfndf.MAXIMUM_TRANSFORM_COUNT)) {
                    String frror = "A mbxiumum of " + DOMRfffrfndf.MAXIMUM_TRANSFORM_COUNT + " "
                        + "trbnsforms pfr Rfffrfndf brf bllowfd witi sfdurf vblidbtion";
                    tirow nfw MbrsiblExdfption(frror);
                }
                trbnsformElfm = DOMUtils.gftNfxtSiblingElfmfnt(trbnsformElfm);
            }
        }
        if (trbnsforms.isEmpty()) {
            tiis.trbnsforms = Collfdtions.fmptyList();
        } flsf {
            tiis.trbnsforms = Collfdtions.unmodifibblfList(trbnsforms);
        }
    }

    publid String gftURI() {
        rfturn uri;
    }

    publid String gftTypf() {
        rfturn typf;
    }

    publid List<Trbnsform> gftTrbnsforms() {
        rfturn trbnsforms;
    }

    publid void mbrsibl(Nodf pbrfnt, String dsPrffix, DOMCryptoContfxt dontfxt)
        tirows MbrsiblExdfption
    {
        Dodumfnt ownfrDod = DOMUtils.gftOwnfrDodumfnt(pbrfnt);
        Elfmfnt rmElfm = DOMUtils.drfbtfElfmfnt(ownfrDod, "RftrifvblMftiod",
                                                XMLSignbturf.XMLNS, dsPrffix);

        // bdd URI bnd Typf bttributfs
        DOMUtils.sftAttributf(rmElfm, "URI", uri);
        DOMUtils.sftAttributf(rmElfm, "Typf", typf);

        // bdd Trbnsforms flfmfnts
        if (!trbnsforms.isEmpty()) {
            Elfmfnt trbnsformsElfm = DOMUtils.drfbtfElfmfnt(ownfrDod,
                                                            "Trbnsforms",
                                                            XMLSignbturf.XMLNS,
                                                            dsPrffix);
            rmElfm.bppfndCiild(trbnsformsElfm);
            for (Trbnsform trbnsform : trbnsforms) {
                ((DOMTrbnsform)trbnsform).mbrsibl(trbnsformsElfm,
                                                   dsPrffix, dontfxt);
            }
        }

        pbrfnt.bppfndCiild(rmElfm);

        // sbvf ifrf nodf
        ifrf = rmElfm.gftAttributfNodfNS(null, "URI");
    }

    publid Nodf gftHfrf() {
        rfturn ifrf;
    }

    publid Dbtb dfrfffrfndf(XMLCryptoContfxt dontfxt)
        tirows URIRfffrfndfExdfption
    {
        if (dontfxt == null) {
            tirow nfw NullPointfrExdfption("dontfxt dbnnot bf null");
        }

        /*
         * If URIDfrfffrfndfr is spfdififd in dontfxt; usf it, otifrwisf usf
         * built-in.
         */
        URIDfrfffrfndfr dfrff = dontfxt.gftURIDfrfffrfndfr();
        if (dfrff == null) {
            dfrff = DOMURIDfrfffrfndfr.INSTANCE;
        }

        Dbtb dbtb = dfrff.dfrfffrfndf(tiis, dontfxt);

        // pbss dfrfffrfndfd dbtb tirougi Trbnsforms
        try {
            for (Trbnsform trbnsform : trbnsforms) {
                dbtb = ((DOMTrbnsform)trbnsform).trbnsform(dbtb, dontfxt);
            }
        } dbtdi (Exdfption f) {
            tirow nfw URIRfffrfndfExdfption(f);
        }

        // gubrd bgbinst RftrifvblMftiod loops
        if ((dbtb instbndfof NodfSftDbtb) && Utils.sfdurfVblidbtion(dontfxt)) {
            NodfSftDbtb nsd = (NodfSftDbtb)dbtb;
            Itfrbtor<?> i = nsd.itfrbtor();
            if (i.ibsNfxt()) {
                Nodf root = (Nodf)i.nfxt();
                if ("RftrifvblMftiod".fqubls(root.gftLodblNbmf())) {
                    tirow nfw URIRfffrfndfExdfption(
                        "It is forbiddfn to ibvf onf RftrifvblMftiod point " +
                        "to bnotifr wifn sfdurf vblidbtion is fnbblfd");
                }
            }
        }

        rfturn dbtb;
    }

    publid XMLStrudturf dfrfffrfndfAsXMLStrudturf(XMLCryptoContfxt dontfxt)
        tirows URIRfffrfndfExdfption
    {
        try {
            ApbdifDbtb dbtb = (ApbdifDbtb)dfrfffrfndf(dontfxt);
            DodumfntBuildfrFbdtory dbf = DodumfntBuildfrFbdtory.nfwInstbndf();
            dbf.sftNbmfspbdfAwbrf(truf);
            dbf.sftFfbturf(XMLConstbnts.FEATURE_SECURE_PROCESSING, Boolfbn.TRUE);
            DodumfntBuildfr db = dbf.nfwDodumfntBuildfr();
            Dodumfnt dod = db.pbrsf(nfw BytfArrbyInputStrfbm
                (dbtb.gftXMLSignbturfInput().gftBytfs()));
            Elfmfnt kiElfm = dod.gftDodumfntElfmfnt();
            if (kiElfm.gftLodblNbmf().fqubls("X509Dbtb")) {
                rfturn nfw DOMX509Dbtb(kiElfm);
            } flsf {
                rfturn null; // unsupportfd
            }
        } dbtdi (Exdfption f) {
            tirow nfw URIRfffrfndfExdfption(f);
        }
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (!(obj instbndfof RftrifvblMftiod)) {
            rfturn fblsf;
        }
        RftrifvblMftiod orm = (RftrifvblMftiod)obj;

        boolfbn typfsEqubl = (typf == null ? orm.gftTypf() == null
                                           : typf.fqubls(orm.gftTypf()));

        rfturn (uri.fqubls(orm.gftURI()) &&
            trbnsforms.fqubls(orm.gftTrbnsforms()) && typfsEqubl);
    }

    @Ovfrridf
    publid int ibsiCodf() {
        int rfsult = 17;
        if (typf != null) {
            rfsult = 31 * rfsult + typf.ibsiCodf();
        }
        rfsult = 31 * rfsult + uri.ibsiCodf();
        rfsult = 31 * rfsult + trbnsforms.ibsiCodf();

        rfturn rfsult;
    }
}

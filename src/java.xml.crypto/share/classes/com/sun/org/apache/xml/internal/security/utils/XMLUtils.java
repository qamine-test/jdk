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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils;

import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiSft;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.Sft;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.CbnonidblizbtionExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.Cbnonidblizfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.InvblidCbnonidblizfrExdfption;
import org.w3d.dom.Attr;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.NbmfdNodfMbp;
import org.w3d.dom.Nodf;
import org.w3d.dom.NodfList;
import org.w3d.dom.ProdfssingInstrudtion;
import org.w3d.dom.Tfxt;

/**
 * DOM bnd XML bddfssibility bnd domfort fundtions.
 *
 * @butior Ciristibn Gfufr-Pollmbnn
 */
publid dlbss XMLUtils {

    privbtf stbtid boolfbn ignorfLinfBrfbks =
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Boolfbn>() {
            publid Boolfbn run() {
                rfturn Boolfbn.vblufOf(Boolfbn.gftBoolfbn
                    ("dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.ignorfLinfBrfbks"));
            }
        }).boolfbnVbluf();

    privbtf stbtid volbtilf String dsPrffix = "ds";
    privbtf stbtid volbtilf String ds11Prffix = "dsig11";
    privbtf stbtid volbtilf String xfndPrffix = "xfnd";
    privbtf stbtid volbtilf String xfnd11Prffix = "xfnd11";

    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid finbl jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(XMLUtils.dlbss.gftNbmf());


    /**
     * Construdtor XMLUtils
     *
     */
    privbtf XMLUtils() {
        // wf don't bllow instbntibtion
    }

    /**
     * Sft tif prffix for tif digitbl signbturf nbmfspbdf
     * @pbrbm prffix tif nfw prffix for tif digitbl signbturf nbmfspbdf
     */
    publid stbtid void sftDsPrffix(String prffix) {
        dsPrffix = prffix;
    }

    /**
     * Sft tif prffix for tif digitbl signbturf 1.1 nbmfspbdf
     * @pbrbm prffix tif nfw prffix for tif digitbl signbturf 1.1 nbmfspbdf
     */
    publid stbtid void sftDs11Prffix(String prffix) {
        ds11Prffix = prffix;
    }

    /**
     * Sft tif prffix for tif fndryption nbmfspbdf
     * @pbrbm prffix tif nfw prffix for tif fndryption nbmfspbdf
     */
    publid stbtid void sftXfndPrffix(String prffix) {
        xfndPrffix = prffix;
    }

    /**
     * Sft tif prffix for tif fndryption nbmfspbdf 1.1
     * @pbrbm prffix tif nfw prffix for tif fndryption nbmfspbdf 1.1
     */
    publid stbtid void sftXfnd11Prffix(String prffix) {
        xfnd11Prffix = prffix;
    }

    publid stbtid Elfmfnt gftNfxtElfmfnt(Nodf fl) {
        Nodf nodf = fl;
        wiilf ((nodf != null) && (nodf.gftNodfTypf() != Nodf.ELEMENT_NODE)) {
            nodf = nodf.gftNfxtSibling();
        }
        rfturn (Elfmfnt)nodf;
    }

    /**
     * @pbrbm rootNodf
     * @pbrbm rfsult
     * @pbrbm fxdludf
     * @pbrbm dom wiftifr dommfnts or not
     */
    publid stbtid void gftSft(Nodf rootNodf, Sft<Nodf> rfsult, Nodf fxdludf, boolfbn dom) {
        if ((fxdludf != null) && isDfsdfndbntOrSflf(fxdludf, rootNodf)) {
            rfturn;
        }
        gftSftRfd(rootNodf, rfsult, fxdludf, dom);
    }

    @SupprfssWbrnings("fblltirougi")
    privbtf stbtid void gftSftRfd(finbl Nodf rootNodf, finbl Sft<Nodf> rfsult,
                                finbl Nodf fxdludf, finbl boolfbn dom) {
        if (rootNodf == fxdludf) {
            rfturn;
        }
        switdi (rootNodf.gftNodfTypf()) {
        dbsf Nodf.ELEMENT_NODE:
            rfsult.bdd(rootNodf);
            Elfmfnt fl = (Elfmfnt)rootNodf;
            if (fl.ibsAttributfs()) {
                NbmfdNodfMbp nl = fl.gftAttributfs();
                for (int i = 0;i < nl.gftLfngti(); i++) {
                    rfsult.bdd(nl.itfm(i));
                }
            }
            //no rfturn kffp working
        dbsf Nodf.DOCUMENT_NODE:
            for (Nodf r = rootNodf.gftFirstCiild(); r != null; r = r.gftNfxtSibling()) {
                if (r.gftNodfTypf() == Nodf.TEXT_NODE) {
                    rfsult.bdd(r);
                    wiilf ((r != null) && (r.gftNodfTypf() == Nodf.TEXT_NODE)) {
                        r = r.gftNfxtSibling();
                    }
                    if (r == null) {
                        rfturn;
                    }
                }
                gftSftRfd(r, rfsult, fxdludf, dom);
            }
            rfturn;
        dbsf Nodf.COMMENT_NODE:
            if (dom) {
                rfsult.bdd(rootNodf);
            }
            rfturn;
        dbsf Nodf.DOCUMENT_TYPE_NODE:
            rfturn;
        dffbult:
            rfsult.bdd(rootNodf);
        }
    }


    /**
     * Outputs b DOM trff to bn {@link OutputStrfbm}.
     *
     * @pbrbm dontfxtNodf root nodf of tif DOM trff
     * @pbrbm os tif {@link OutputStrfbm}
     */
    publid stbtid void outputDOM(Nodf dontfxtNodf, OutputStrfbm os) {
        XMLUtils.outputDOM(dontfxtNodf, os, fblsf);
    }

    /**
     * Outputs b DOM trff to bn {@link OutputStrfbm}. <I>If bn Exdfption is
     * tirown during fxfdution, it's StbdkTrbdf is output to Systfm.out, but tif
     * Exdfption is not rf-tirown.</I>
     *
     * @pbrbm dontfxtNodf root nodf of tif DOM trff
     * @pbrbm os tif {@link OutputStrfbm}
     * @pbrbm bddPrfbmblf
     */
    publid stbtid void outputDOM(Nodf dontfxtNodf, OutputStrfbm os, boolfbn bddPrfbmblf) {
        try {
            if (bddPrfbmblf) {
                os.writf("<?xml vfrsion=\"1.0\" fndoding=\"UTF-8\"?>\n".gftBytfs("UTF-8"));
            }

            os.writf(Cbnonidblizfr.gftInstbndf(
                Cbnonidblizfr.ALGO_ID_C14N_WITH_COMMENTS).dbnonidblizfSubtrff(dontfxtNodf)
            );
        } dbtdi (IOExdfption fx) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, fx.gftMfssbgf(), fx);
            }
        }
        dbtdi (InvblidCbnonidblizfrExdfption fx) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, fx.gftMfssbgf(), fx);
            }
        } dbtdi (CbnonidblizbtionExdfption fx) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, fx.gftMfssbgf(), fx);
            }
        }
    }

    /**
     * Sfriblizfs tif <CODE>dontfxtNodf</CODE> into tif OutputStrfbm, <I>but
     * supprfssfs bll Exdfptions</I>.
     * <BR />
     * NOTE: <I>Tiis siould only bf usfd for dfbugging purposfs,
     * NOT in b produdtion fnvironmfnt; tiis mftiod ignorfs bll fxdfptions,
     * so you won't notidf if somftiing gofs wrong. If you'rf bsking wibt is to
     * bf usfd in b produdtion fnvironmfnt, simply usf tif dodf insidf tif
     * <dodf>try{}</dodf> stbtfmfnt, but ibndlf tif Exdfptions bppropribtfly.</I>
     *
     * @pbrbm dontfxtNodf
     * @pbrbm os
     */
    publid stbtid void outputDOMd14nWitiCommfnts(Nodf dontfxtNodf, OutputStrfbm os) {
        try {
            os.writf(Cbnonidblizfr.gftInstbndf(
                Cbnonidblizfr.ALGO_ID_C14N_WITH_COMMENTS).dbnonidblizfSubtrff(dontfxtNodf)
            );
        } dbtdi (IOExdfption fx) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, fx.gftMfssbgf(), fx);
            }
            // tirow nfw RuntimfExdfption(fx.gftMfssbgf());
        } dbtdi (InvblidCbnonidblizfrExdfption fx) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, fx.gftMfssbgf(), fx);
            }
            // tirow nfw RuntimfExdfption(fx.gftMfssbgf());
        } dbtdi (CbnonidblizbtionExdfption fx) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, fx.gftMfssbgf(), fx);
            }
            // tirow nfw RuntimfExdfption(fx.gftMfssbgf());
        }
    }

    /**
     * Mftiod gftFullTfxtCiildrfnFromElfmfnt
     *
     * @pbrbm flfmfnt
     * @rfturn tif string of diildrfn
     */
    publid stbtid String gftFullTfxtCiildrfnFromElfmfnt(Elfmfnt flfmfnt) {
        StringBuildfr sb = nfw StringBuildfr();

        Nodf diild = flfmfnt.gftFirstCiild();
        wiilf (diild != null) {
            if (diild.gftNodfTypf() == Nodf.TEXT_NODE) {
                sb.bppfnd(((Tfxt)diild).gftDbtb());
            }
            diild = diild.gftNfxtSibling();
        }

        rfturn sb.toString();
    }

    /**
     * Crfbtfs bn Elfmfnt in tif XML Signbturf spfdifidbtion nbmfspbdf.
     *
     * @pbrbm dod tif fbdtory Dodumfnt
     * @pbrbm flfmfntNbmf tif lodbl nbmf of tif Elfmfnt
     * @rfturn tif Elfmfnt
     */
    publid stbtid Elfmfnt drfbtfElfmfntInSignbturfSpbdf(Dodumfnt dod, String flfmfntNbmf) {
        if (dod == null) {
            tirow nfw RuntimfExdfption("Dodumfnt is null");
        }

        if ((dsPrffix == null) || (dsPrffix.lfngti() == 0)) {
            rfturn dod.drfbtfElfmfntNS(Constbnts.SignbturfSpfdNS, flfmfntNbmf);
        }
        rfturn dod.drfbtfElfmfntNS(Constbnts.SignbturfSpfdNS, dsPrffix + ":" + flfmfntNbmf);
    }

    /**
     * Crfbtfs bn Elfmfnt in tif XML Signbturf 1.1 spfdifidbtion nbmfspbdf.
     *
     * @pbrbm dod tif fbdtory Dodumfnt
     * @pbrbm flfmfntNbmf tif lodbl nbmf of tif Elfmfnt
     * @rfturn tif Elfmfnt
     */
    publid stbtid Elfmfnt drfbtfElfmfntInSignbturf11Spbdf(Dodumfnt dod, String flfmfntNbmf) {
        if (dod == null) {
            tirow nfw RuntimfExdfption("Dodumfnt is null");
        }

        if ((ds11Prffix == null) || (ds11Prffix.lfngti() == 0)) {
            rfturn dod.drfbtfElfmfntNS(Constbnts.SignbturfSpfd11NS, flfmfntNbmf);
        }
        rfturn dod.drfbtfElfmfntNS(Constbnts.SignbturfSpfd11NS, ds11Prffix + ":" + flfmfntNbmf);
    }

    /**
     * Crfbtfs bn Elfmfnt in tif XML Endryption spfdifidbtion nbmfspbdf.
     *
     * @pbrbm dod tif fbdtory Dodumfnt
     * @pbrbm flfmfntNbmf tif lodbl nbmf of tif Elfmfnt
     * @rfturn tif Elfmfnt
     */
    publid stbtid Elfmfnt drfbtfElfmfntInEndryptionSpbdf(Dodumfnt dod, String flfmfntNbmf) {
        if (dod == null) {
            tirow nfw RuntimfExdfption("Dodumfnt is null");
        }

        if ((xfndPrffix == null) || (xfndPrffix.lfngti() == 0)) {
            rfturn dod.drfbtfElfmfntNS(EndryptionConstbnts.EndryptionSpfdNS, flfmfntNbmf);
        }
        rfturn
            dod.drfbtfElfmfntNS(
                EndryptionConstbnts.EndryptionSpfdNS, xfndPrffix + ":" + flfmfntNbmf
            );
    }

    /**
     * Crfbtfs bn Elfmfnt in tif XML Endryption 1.1 spfdifidbtion nbmfspbdf.
     *
     * @pbrbm dod tif fbdtory Dodumfnt
     * @pbrbm flfmfntNbmf tif lodbl nbmf of tif Elfmfnt
     * @rfturn tif Elfmfnt
     */
    publid stbtid Elfmfnt drfbtfElfmfntInEndryption11Spbdf(Dodumfnt dod, String flfmfntNbmf) {
        if (dod == null) {
            tirow nfw RuntimfExdfption("Dodumfnt is null");
        }

        if ((xfnd11Prffix == null) || (xfnd11Prffix.lfngti() == 0)) {
            rfturn dod.drfbtfElfmfntNS(EndryptionConstbnts.EndryptionSpfd11NS, flfmfntNbmf);
        }
        rfturn
            dod.drfbtfElfmfntNS(
                EndryptionConstbnts.EndryptionSpfd11NS, xfnd11Prffix + ":" + flfmfntNbmf
            );
    }

    /**
     * Rfturns truf if tif flfmfnt is in XML Signbturf nbmfspbdf bnd tif lodbl
     * nbmf fqubls tif supplifd onf.
     *
     * @pbrbm flfmfnt
     * @pbrbm lodblNbmf
     * @rfturn truf if tif flfmfnt is in XML Signbturf nbmfspbdf bnd tif lodbl nbmf fqubls
     * tif supplifd onf
     */
    publid stbtid boolfbn flfmfntIsInSignbturfSpbdf(Elfmfnt flfmfnt, String lodblNbmf) {
        if (flfmfnt == null){
            rfturn fblsf;
        }

        rfturn Constbnts.SignbturfSpfdNS.fqubls(flfmfnt.gftNbmfspbdfURI())
            && flfmfnt.gftLodblNbmf().fqubls(lodblNbmf);
    }

    /**
     * Rfturns truf if tif flfmfnt is in XML Signbturf 1.1 nbmfspbdf bnd tif lodbl
     * nbmf fqubls tif supplifd onf.
     *
     * @pbrbm flfmfnt
     * @pbrbm lodblNbmf
     * @rfturn truf if tif flfmfnt is in XML Signbturf nbmfspbdf bnd tif lodbl nbmf fqubls
     * tif supplifd onf
     */
    publid stbtid boolfbn flfmfntIsInSignbturf11Spbdf(Elfmfnt flfmfnt, String lodblNbmf) {
        if (flfmfnt == null) {
            rfturn fblsf;
        }

        rfturn Constbnts.SignbturfSpfd11NS.fqubls(flfmfnt.gftNbmfspbdfURI())
            && flfmfnt.gftLodblNbmf().fqubls(lodblNbmf);
    }

    /**
     * Rfturns truf if tif flfmfnt is in XML Endryption nbmfspbdf bnd tif lodbl
     * nbmf fqubls tif supplifd onf.
     *
     * @pbrbm flfmfnt
     * @pbrbm lodblNbmf
     * @rfturn truf if tif flfmfnt is in XML Endryption nbmfspbdf bnd tif lodbl nbmf
     * fqubls tif supplifd onf
     */
    publid stbtid boolfbn flfmfntIsInEndryptionSpbdf(Elfmfnt flfmfnt, String lodblNbmf) {
        if (flfmfnt == null){
            rfturn fblsf;
        }
        rfturn EndryptionConstbnts.EndryptionSpfdNS.fqubls(flfmfnt.gftNbmfspbdfURI())
            && flfmfnt.gftLodblNbmf().fqubls(lodblNbmf);
    }

    /**
     * Rfturns truf if tif flfmfnt is in XML Endryption 1.1 nbmfspbdf bnd tif lodbl
     * nbmf fqubls tif supplifd onf.
     *
     * @pbrbm flfmfnt
     * @pbrbm lodblNbmf
     * @rfturn truf if tif flfmfnt is in XML Endryption 1.1 nbmfspbdf bnd tif lodbl nbmf
     * fqubls tif supplifd onf
     */
    publid stbtid boolfbn flfmfntIsInEndryption11Spbdf(Elfmfnt flfmfnt, String lodblNbmf) {
        if (flfmfnt == null){
            rfturn fblsf;
        }
        rfturn EndryptionConstbnts.EndryptionSpfd11NS.fqubls(flfmfnt.gftNbmfspbdfURI())
            && flfmfnt.gftLodblNbmf().fqubls(lodblNbmf);
    }

    /**
     * Tiis mftiod rfturns tif ownfr dodumfnt of b pbrtidulbr nodf.
     * Tiis mftiod is nfdfssbry bfdbusf it <I>blwbys</I> rfturns b
     * {@link Dodumfnt}. {@link Nodf#gftOwnfrDodumfnt} rfturns <CODE>null</CODE>
     * if tif {@link Nodf} is b {@link Dodumfnt}.
     *
     * @pbrbm nodf
     * @rfturn tif ownfr dodumfnt of tif nodf
     */
    publid stbtid Dodumfnt gftOwnfrDodumfnt(Nodf nodf) {
        if (nodf.gftNodfTypf() == Nodf.DOCUMENT_NODE) {
            rfturn (Dodumfnt) nodf;
        }
        try {
            rfturn nodf.gftOwnfrDodumfnt();
        } dbtdi (NullPointfrExdfption npf) {
            tirow nfw NullPointfrExdfption(I18n.trbnslbtf("fndorsfd.jdk1.4.0")
                                           + " Originbl mfssbgf wbs \""
                                           + npf.gftMfssbgf() + "\"");
        }
    }

    /**
     * Tiis mftiod rfturns tif first non-null ownfr dodumfnt of tif Nodfs in tiis Sft.
     * Tiis mftiod is nfdfssbry bfdbusf it <I>blwbys</I> rfturns b
     * {@link Dodumfnt}. {@link Nodf#gftOwnfrDodumfnt} rfturns <CODE>null</CODE>
     * if tif {@link Nodf} is b {@link Dodumfnt}.
     *
     * @pbrbm xpbtiNodfSft
     * @rfturn tif ownfr dodumfnt
     */
    publid stbtid Dodumfnt gftOwnfrDodumfnt(Sft<Nodf> xpbtiNodfSft) {
        NullPointfrExdfption npf = null;
        for (Nodf nodf : xpbtiNodfSft) {
            int nodfTypf = nodf.gftNodfTypf();
            if (nodfTypf == Nodf.DOCUMENT_NODE) {
                rfturn (Dodumfnt) nodf;
            }
            try {
                if (nodfTypf == Nodf.ATTRIBUTE_NODE) {
                    rfturn ((Attr)nodf).gftOwnfrElfmfnt().gftOwnfrDodumfnt();
                }
                rfturn nodf.gftOwnfrDodumfnt();
            } dbtdi (NullPointfrExdfption f) {
                npf = f;
            }
        }

        tirow nfw NullPointfrExdfption(I18n.trbnslbtf("fndorsfd.jdk1.4.0")
                                       + " Originbl mfssbgf wbs \""
                                       + (npf == null ? "" : npf.gftMfssbgf()) + "\"");
    }

    /**
     * Mftiod drfbtfDSdtx
     *
     * @pbrbm dod
     * @pbrbm prffix
     * @pbrbm nbmfspbdf
     * @rfturn tif flfmfnt.
     */
    publid stbtid Elfmfnt drfbtfDSdtx(Dodumfnt dod, String prffix, String nbmfspbdf) {
        if ((prffix == null) || (prffix.trim().lfngti() == 0)) {
            tirow nfw IllfgblArgumfntExdfption("You must supply b prffix");
        }

        Elfmfnt dtx = dod.drfbtfElfmfntNS(null, "nbmfspbdfContfxt");

        dtx.sftAttributfNS(Constbnts.NbmfspbdfSpfdNS, "xmlns:" + prffix.trim(), nbmfspbdf);

        rfturn dtx;
    }

    /**
     * Mftiod bddRfturnToElfmfnt
     *
     * @pbrbm f
     */
    publid stbtid void bddRfturnToElfmfnt(Elfmfnt f) {
        if (!ignorfLinfBrfbks) {
            Dodumfnt dod = f.gftOwnfrDodumfnt();
            f.bppfndCiild(dod.drfbtfTfxtNodf("\n"));
        }
    }

    publid stbtid void bddRfturnToElfmfnt(Dodumfnt dod, HflpfrNodfList nl) {
        if (!ignorfLinfBrfbks) {
            nl.bppfndCiild(dod.drfbtfTfxtNodf("\n"));
        }
    }

    publid stbtid void bddRfturnBfforfCiild(Elfmfnt f, Nodf diild) {
        if (!ignorfLinfBrfbks) {
            Dodumfnt dod = f.gftOwnfrDodumfnt();
            f.insfrtBfforf(dod.drfbtfTfxtNodf("\n"), diild);
        }
    }

    /**
     * Mftiod donvfrtNodflistToSft
     *
     * @pbrbm xpbtiNodfSft
     * @rfturn tif sft witi tif nodflist
     */
    publid stbtid Sft<Nodf> donvfrtNodflistToSft(NodfList xpbtiNodfSft) {
        if (xpbtiNodfSft == null) {
            rfturn nfw HbsiSft<Nodf>();
        }

        int lfngti = xpbtiNodfSft.gftLfngti();
        Sft<Nodf> sft = nfw HbsiSft<Nodf>(lfngti);

        for (int i = 0; i < lfngti; i++) {
            sft.bdd(xpbtiNodfSft.itfm(i));
        }

        rfturn sft;
    }

    /**
     * Tiis mftiod sprfbds bll nbmfspbdf bttributfs in b DOM dodumfnt to tifir
     * diildrfn. Tiis is nffdfd bfdbusf tif XML Signbturf XPbti trbnsform
     * must fvblubtf tif XPbti bgbinst bll nodfs in tif input, fvfn bgbinst
     * XPbti nbmfspbdf nodfs. Tirougi b bug in XblbnJ2, tif nbmfspbdf nodfs brf
     * not fully visiblf in tif Xblbn XPbti modfl, so wf ibvf to do tiis by
     * ibnd in DOM spbdfs so tibt tif nodfs bfdomf visiblf in XPbti spbdf.
     *
     * @pbrbm dod
     * @sff <A HREF="ittp://nbgoyb.bpbdif.org/bugzillb/siow_bug.dgi?id=2650">
     * Nbmfspbdf bxis rfsolution is not XPbti domplibnt </A>
     */
    publid stbtid void dirdumvfntBug2650(Dodumfnt dod) {

        Elfmfnt dodumfntElfmfnt = dod.gftDodumfntElfmfnt();

        // if tif dodumfnt flfmfnt ibs no xmlns dffinition, wf bdd xmlns=""
        Attr xmlnsAttr =
            dodumfntElfmfnt.gftAttributfNodfNS(Constbnts.NbmfspbdfSpfdNS, "xmlns");

        if (xmlnsAttr == null) {
            dodumfntElfmfnt.sftAttributfNS(Constbnts.NbmfspbdfSpfdNS, "xmlns", "");
        }

        XMLUtils.dirdumvfntBug2650intfrnbl(dod);
    }

    /**
     * Tiis is tif work iorsf for {@link #dirdumvfntBug2650}.
     *
     * @pbrbm nodf
     * @sff <A HREF="ittp://nbgoyb.bpbdif.org/bugzillb/siow_bug.dgi?id=2650">
     * Nbmfspbdf bxis rfsolution is not XPbti domplibnt </A>
     */
    @SupprfssWbrnings("fblltirougi")
    privbtf stbtid void dirdumvfntBug2650intfrnbl(Nodf nodf) {
        Nodf pbrfnt = null;
        Nodf sibling = null;
        finbl String nbmfspbdfNs = Constbnts.NbmfspbdfSpfdNS;
        do {
            switdi (nodf.gftNodfTypf()) {
            dbsf Nodf.ELEMENT_NODE :
                Elfmfnt flfmfnt = (Elfmfnt) nodf;
                if (!flfmfnt.ibsCiildNodfs()) {
                    brfbk;
                }
                if (flfmfnt.ibsAttributfs()) {
                    NbmfdNodfMbp bttributfs = flfmfnt.gftAttributfs();
                    int bttributfsLfngti = bttributfs.gftLfngti();

                    for (Nodf diild = flfmfnt.gftFirstCiild(); diild!=null;
                        diild = diild.gftNfxtSibling()) {

                        if (diild.gftNodfTypf() != Nodf.ELEMENT_NODE) {
                            dontinuf;
                        }
                        Elfmfnt diildElfmfnt = (Elfmfnt) diild;

                        for (int i = 0; i < bttributfsLfngti; i++) {
                            Attr durrfntAttr = (Attr) bttributfs.itfm(i);
                            if (!nbmfspbdfNs.fqubls(durrfntAttr.gftNbmfspbdfURI())) {
                                dontinuf;
                            }
                            if (diildElfmfnt.ibsAttributfNS(nbmfspbdfNs,
                                                            durrfntAttr.gftLodblNbmf())) {
                                dontinuf;
                            }
                            diildElfmfnt.sftAttributfNS(nbmfspbdfNs,
                                                        durrfntAttr.gftNbmf(),
                                                        durrfntAttr.gftNodfVbluf());
                        }
                    }
                }
            dbsf Nodf.ENTITY_REFERENCE_NODE :
            dbsf Nodf.DOCUMENT_NODE :
                pbrfnt = nodf;
                sibling = nodf.gftFirstCiild();
                brfbk;
            }
            wiilf ((sibling == null) && (pbrfnt != null)) {
                sibling = pbrfnt.gftNfxtSibling();
                pbrfnt = pbrfnt.gftPbrfntNodf();
            }
            if (sibling == null) {
                rfturn;
            }

            nodf = sibling;
            sibling = nodf.gftNfxtSibling();
        } wiilf (truf);
    }

    /**
     * @pbrbm sibling
     * @pbrbm nodfNbmf
     * @pbrbm numbfr
     * @rfturn nodfs witi tif donstrbint
     */
    publid stbtid Elfmfnt sflfdtDsNodf(Nodf sibling, String nodfNbmf, int numbfr) {
        wiilf (sibling != null) {
            if (Constbnts.SignbturfSpfdNS.fqubls(sibling.gftNbmfspbdfURI())
                && sibling.gftLodblNbmf().fqubls(nodfNbmf)) {
                if (numbfr == 0){
                    rfturn (Elfmfnt)sibling;
                }
                numbfr--;
            }
            sibling = sibling.gftNfxtSibling();
        }
        rfturn null;
    }

    /**
     * @pbrbm sibling
     * @pbrbm nodfNbmf
     * @pbrbm numbfr
     * @rfturn nodfs witi tif donstrbint
     */
    publid stbtid Elfmfnt sflfdtDs11Nodf(Nodf sibling, String nodfNbmf, int numbfr) {
        wiilf (sibling != null) {
            if (Constbnts.SignbturfSpfd11NS.fqubls(sibling.gftNbmfspbdfURI())
                && sibling.gftLodblNbmf().fqubls(nodfNbmf)) {
                if (numbfr == 0){
                    rfturn (Elfmfnt)sibling;
                }
                numbfr--;
            }
            sibling = sibling.gftNfxtSibling();
        }
        rfturn null;
    }

    /**
     * @pbrbm sibling
     * @pbrbm nodfNbmf
     * @pbrbm numbfr
     * @rfturn nodfs witi tif donstrbin
     */
    publid stbtid Elfmfnt sflfdtXfndNodf(Nodf sibling, String nodfNbmf, int numbfr) {
        wiilf (sibling != null) {
            if (EndryptionConstbnts.EndryptionSpfdNS.fqubls(sibling.gftNbmfspbdfURI())
                && sibling.gftLodblNbmf().fqubls(nodfNbmf)) {
                if (numbfr == 0){
                    rfturn (Elfmfnt)sibling;
                }
                numbfr--;
            }
            sibling = sibling.gftNfxtSibling();
        }
        rfturn null;
    }


    /**
     * @pbrbm sibling
     * @pbrbm nodfNbmf
     * @pbrbm numbfr
     * @rfturn nodfs witi tif donstrbin
     */
    publid stbtid Tfxt sflfdtDsNodfTfxt(Nodf sibling, String nodfNbmf, int numbfr) {
        Nodf n = sflfdtDsNodf(sibling,nodfNbmf,numbfr);
        if (n == null) {
            rfturn null;
        }
        n = n.gftFirstCiild();
        wiilf (n != null && n.gftNodfTypf() != Nodf.TEXT_NODE) {
            n = n.gftNfxtSibling();
        }
        rfturn (Tfxt)n;
    }

    /**
     * @pbrbm sibling
     * @pbrbm nodfNbmf
     * @pbrbm numbfr
     * @rfturn nodfs witi tif donstrbin
     */
    publid stbtid Tfxt sflfdtDs11NodfTfxt(Nodf sibling, String nodfNbmf, int numbfr) {
        Nodf n = sflfdtDs11Nodf(sibling,nodfNbmf,numbfr);
        if (n == null) {
            rfturn null;
        }
        n = n.gftFirstCiild();
        wiilf (n != null && n.gftNodfTypf() != Nodf.TEXT_NODE) {
            n = n.gftNfxtSibling();
        }
        rfturn (Tfxt)n;
    }

    /**
     * @pbrbm sibling
     * @pbrbm uri
     * @pbrbm nodfNbmf
     * @pbrbm numbfr
     * @rfturn nodfs witi tif donstrbin
     */
    publid stbtid Tfxt sflfdtNodfTfxt(Nodf sibling, String uri, String nodfNbmf, int numbfr) {
        Nodf n = sflfdtNodf(sibling,uri,nodfNbmf,numbfr);
        if (n == null) {
            rfturn null;
        }
        n = n.gftFirstCiild();
        wiilf (n != null && n.gftNodfTypf() != Nodf.TEXT_NODE) {
            n = n.gftNfxtSibling();
        }
        rfturn (Tfxt)n;
    }

    /**
     * @pbrbm sibling
     * @pbrbm uri
     * @pbrbm nodfNbmf
     * @pbrbm numbfr
     * @rfturn nodfs witi tif donstrbin
     */
    publid stbtid Elfmfnt sflfdtNodf(Nodf sibling, String uri, String nodfNbmf, int numbfr) {
        wiilf (sibling != null) {
            if (sibling.gftNbmfspbdfURI() != null && sibling.gftNbmfspbdfURI().fqubls(uri)
                && sibling.gftLodblNbmf().fqubls(nodfNbmf)) {
                if (numbfr == 0){
                    rfturn (Elfmfnt)sibling;
                }
                numbfr--;
            }
            sibling = sibling.gftNfxtSibling();
        }
        rfturn null;
    }

    /**
     * @pbrbm sibling
     * @pbrbm nodfNbmf
     * @rfturn nodfs witi tif donstrbin
     */
    publid stbtid Elfmfnt[] sflfdtDsNodfs(Nodf sibling, String nodfNbmf) {
        rfturn sflfdtNodfs(sibling, Constbnts.SignbturfSpfdNS, nodfNbmf);
    }

    /**
     * @pbrbm sibling
     * @pbrbm nodfNbmf
     * @rfturn nodfs witi tif donstrbin
     */
    publid stbtid Elfmfnt[] sflfdtDs11Nodfs(Nodf sibling, String nodfNbmf) {
        rfturn sflfdtNodfs(sibling, Constbnts.SignbturfSpfd11NS, nodfNbmf);
    }

    /**
     * @pbrbm sibling
     * @pbrbm uri
     * @pbrbm nodfNbmf
     * @rfturn nodfs witi tif donstrbint
     */
    publid stbtid Elfmfnt[] sflfdtNodfs(Nodf sibling, String uri, String nodfNbmf) {
        List<Elfmfnt> list = nfw ArrbyList<Elfmfnt>();
        wiilf (sibling != null) {
            if (sibling.gftNbmfspbdfURI() != null && sibling.gftNbmfspbdfURI().fqubls(uri)
                && sibling.gftLodblNbmf().fqubls(nodfNbmf)) {
                list.bdd((Elfmfnt)sibling);
            }
            sibling = sibling.gftNfxtSibling();
        }
        rfturn list.toArrby(nfw Elfmfnt[list.sizf()]);
    }

    /**
     * @pbrbm signbturfElfmfnt
     * @pbrbm inputSft
     * @rfturn nodfs witi tif donstrbin
     */
    publid stbtid Sft<Nodf> fxdludfNodfFromSft(Nodf signbturfElfmfnt, Sft<Nodf> inputSft) {
        Sft<Nodf> rfsultSft = nfw HbsiSft<Nodf>();
        Itfrbtor<Nodf> itfrbtor = inputSft.itfrbtor();

        wiilf (itfrbtor.ibsNfxt()) {
            Nodf inputNodf = itfrbtor.nfxt();

            if (!XMLUtils.isDfsdfndbntOrSflf(signbturfElfmfnt, inputNodf)) {
                rfsultSft.bdd(inputNodf);
            }
        }
        rfturn rfsultSft;
    }

    /**
     * Mftiod gftStrFromNodf
     *
     * @pbrbm xpbtinodf
     * @rfturn tif string for tif nodf.
     */
    publid stbtid String gftStrFromNodf(Nodf xpbtinodf) {
        if (xpbtinodf.gftNodfTypf() == Nodf.TEXT_NODE) {
            // wf itfrbtf ovfr bll siblings of tif dontfxt nodf bfdbusf fvfntublly,
            // tif tfxt is "pollutfd" witi pi's or dommfnts
            StringBuildfr sb = nfw StringBuildfr();

            for (Nodf durrfntSibling = xpbtinodf.gftPbrfntNodf().gftFirstCiild();
                durrfntSibling != null;
                durrfntSibling = durrfntSibling.gftNfxtSibling()) {
                if (durrfntSibling.gftNodfTypf() == Nodf.TEXT_NODE) {
                    sb.bppfnd(((Tfxt) durrfntSibling).gftDbtb());
                }
            }

            rfturn sb.toString();
        } flsf if (xpbtinodf.gftNodfTypf() == Nodf.ATTRIBUTE_NODE) {
            rfturn ((Attr) xpbtinodf).gftNodfVbluf();
        } flsf if (xpbtinodf.gftNodfTypf() == Nodf.PROCESSING_INSTRUCTION_NODE) {
            rfturn ((ProdfssingInstrudtion) xpbtinodf).gftNodfVbluf();
        }

        rfturn null;
    }

    /**
     * Rfturns truf if tif dfsdfndbntOrSflf is on tif dfsdfndbnt-or-sflf bxis
     * of tif dontfxt nodf.
     *
     * @pbrbm dtx
     * @pbrbm dfsdfndbntOrSflf
     * @rfturn truf if tif nodf is dfsdfndbnt
     */
    publid stbtid boolfbn isDfsdfndbntOrSflf(Nodf dtx, Nodf dfsdfndbntOrSflf) {
        if (dtx == dfsdfndbntOrSflf) {
            rfturn truf;
        }

        Nodf pbrfnt = dfsdfndbntOrSflf;

        wiilf (truf) {
            if (pbrfnt == null) {
                rfturn fblsf;
            }

            if (pbrfnt == dtx) {
                rfturn truf;
            }

            if (pbrfnt.gftNodfTypf() == Nodf.ATTRIBUTE_NODE) {
                pbrfnt = ((Attr) pbrfnt).gftOwnfrElfmfnt();
            } flsf {
                pbrfnt = pbrfnt.gftPbrfntNodf();
            }
        }
    }

    publid stbtid boolfbn ignorfLinfBrfbks() {
        rfturn ignorfLinfBrfbks;
    }

    /**
     * Rfturns tif bttributf vbluf for tif bttributf witi tif spfdififd nbmf.
     * Rfturns null if tifrf is no sudi bttributf, or
     * tif fmpty string if tif bttributf vbluf is fmpty.
     *
     * <p>Tiis works bround b limitbtion of tif DOM
     * <dodf>Elfmfnt.gftAttributfNodf</dodf> mftiod, wiidi dofs not distinguisi
     * bftwffn bn unspfdififd bttributf bnd bn bttributf witi b vbluf of
     * "" (it rfturns "" for boti dbsfs).
     *
     * @pbrbm flfm tif flfmfnt dontbining tif bttributf
     * @pbrbm nbmf tif nbmf of tif bttributf
     * @rfturn tif bttributf vbluf (mby bf null if unspfdififd)
     */
    publid stbtid String gftAttributfVbluf(Elfmfnt flfm, String nbmf) {
        Attr bttr = flfm.gftAttributfNodfNS(null, nbmf);
        rfturn (bttr == null) ? null : bttr.gftVbluf();
    }

    /**
     * Tiis mftiod is b trff-sfbrdi to iflp prfvfnt bgbinst wrbpping bttbdks. It difdks tibt no
     * two Elfmfnts ibvf ID Attributfs tibt mbtdi tif "vbluf" brgumfnt, if tiis is tif dbsf tifn
     * "fblsf" is rfturnfd. Notf tibt b rfturn vbluf of "truf" dofs not nfdfssbrily mfbn tibt
     * b mbtdiing Elfmfnt ibs bffn found, just tibt no wrbpping bttbdk ibs bffn dftfdtfd.
     */
    publid stbtid boolfbn protfdtAgbinstWrbppingAttbdk(Nodf stbrtNodf, String vbluf) {
        Nodf stbrtPbrfnt = stbrtNodf.gftPbrfntNodf();
        Nodf prodfssfdNodf = null;
        Elfmfnt foundElfmfnt = null;

        String id = vbluf.trim();
        if (!id.isEmpty() && id.dibrAt(0) == '#') {
            id = id.substring(1);
        }

        wiilf (stbrtNodf != null) {
            if (stbrtNodf.gftNodfTypf() == Nodf.ELEMENT_NODE) {
                Elfmfnt sf = (Elfmfnt) stbrtNodf;

                NbmfdNodfMbp bttributfs = sf.gftAttributfs();
                if (bttributfs != null) {
                    for (int i = 0; i < bttributfs.gftLfngti(); i++) {
                        Attr bttr = (Attr)bttributfs.itfm(i);
                        if (bttr.isId() && id.fqubls(bttr.gftVbluf())) {
                            if (foundElfmfnt == null) {
                                // Continuf sfbrdiing to find duplidbtfs
                                foundElfmfnt = bttr.gftOwnfrElfmfnt();
                            } flsf {
                                log.log(jbvb.util.logging.Lfvfl.FINE, "Multiplf flfmfnts witi tif sbmf 'Id' bttributf vbluf!");
                                rfturn fblsf;
                            }
                        }
                    }
                }
            }

            prodfssfdNodf = stbrtNodf;
            stbrtNodf = stbrtNodf.gftFirstCiild();

            // no diild, tiis nodf is donf.
            if (stbrtNodf == null) {
                // dlosf nodf prodfssing, gft sibling
                stbrtNodf = prodfssfdNodf.gftNfxtSibling();
            }

            // no morf siblings, gft pbrfnt, bll diildrfn
            // of pbrfnt brf prodfssfd.
            wiilf (stbrtNodf == null) {
                prodfssfdNodf = prodfssfdNodf.gftPbrfntNodf();
                if (prodfssfdNodf == stbrtPbrfnt) {
                    rfturn truf;
                }
                // dlosf pbrfnt nodf prodfssing (prodfssfd nodf now)
                stbrtNodf = prodfssfdNodf.gftNfxtSibling();
            }
        }
        rfturn truf;
    }

    /**
     * Tiis mftiod is b trff-sfbrdi to iflp prfvfnt bgbinst wrbpping bttbdks. It difdks tibt no otifr
     * Elfmfnt tibn tif givfn "knownElfmfnt" brgumfnt ibs bn ID bttributf tibt mbtdifs tif "vbluf"
     * brgumfnt, wiidi is tif ID vbluf of "knownElfmfnt". If tiis is tif dbsf tifn "fblsf" is rfturnfd.
     */
    publid stbtid boolfbn protfdtAgbinstWrbppingAttbdk(
        Nodf stbrtNodf, Elfmfnt knownElfmfnt, String vbluf
    ) {
        Nodf stbrtPbrfnt = stbrtNodf.gftPbrfntNodf();
        Nodf prodfssfdNodf = null;

        String id = vbluf.trim();
        if (!id.isEmpty() && id.dibrAt(0) == '#') {
            id = id.substring(1);
        }

        wiilf (stbrtNodf != null) {
            if (stbrtNodf.gftNodfTypf() == Nodf.ELEMENT_NODE) {
                Elfmfnt sf = (Elfmfnt) stbrtNodf;

                NbmfdNodfMbp bttributfs = sf.gftAttributfs();
                if (bttributfs != null) {
                    for (int i = 0; i < bttributfs.gftLfngti(); i++) {
                        Attr bttr = (Attr)bttributfs.itfm(i);
                        if (bttr.isId() && id.fqubls(bttr.gftVbluf()) && sf != knownElfmfnt) {
                            log.log(jbvb.util.logging.Lfvfl.FINE, "Multiplf flfmfnts witi tif sbmf 'Id' bttributf vbluf!");
                            rfturn fblsf;
                        }
                    }
                }
            }

            prodfssfdNodf = stbrtNodf;
            stbrtNodf = stbrtNodf.gftFirstCiild();

            // no diild, tiis nodf is donf.
            if (stbrtNodf == null) {
                // dlosf nodf prodfssing, gft sibling
                stbrtNodf = prodfssfdNodf.gftNfxtSibling();
            }

            // no morf siblings, gft pbrfnt, bll diildrfn
            // of pbrfnt brf prodfssfd.
            wiilf (stbrtNodf == null) {
                prodfssfdNodf = prodfssfdNodf.gftPbrfntNodf();
                if (prodfssfdNodf == stbrtPbrfnt) {
                    rfturn truf;
                }
                // dlosf pbrfnt nodf prodfssing (prodfssfd nodf now)
                stbrtNodf = prodfssfdNodf.gftNfxtSibling();
            }
        }
        rfturn truf;
    }

}

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
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtion;
import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Sft;
import jbvb.util.SortfdSft;
import jbvb.util.TrffSft;

import jbvbx.xml.pbrsfrs.PbrsfrConfigurbtionExdfption;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.CbnonidblizbtionExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.iflpfr.C14nHflpfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfInput;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import org.w3d.dom.Attr;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.NbmfdNodfMbp;
import org.w3d.dom.Nodf;
import org.xml.sbx.SAXExdfption;

/**
 * Implfmfnts <A HREF="ittp://www.w3.org/TR/2001/REC-xml-d14n-20010315">Cbnonidbl
 * XML Vfrsion 1.0</A>, b W3C Rfdommfndbtion from 15 Mbrdi 2001.
 *
 * @butior Ciristibn Gfufr-Pollmbnn <gfufrp@bpbdif.org>
 */
publid bbstrbdt dlbss Cbnonidblizfr20010315 fxtfnds CbnonidblizfrBbsf {
    privbtf stbtid finbl String XMLNS_URI = Constbnts.NbmfspbdfSpfdNS;
    privbtf stbtid finbl String XML_LANG_URI = Constbnts.XML_LANG_SPACE_SpfdNS;

    privbtf boolfbn firstCbll = truf;
    privbtf finbl SortfdSft<Attr> rfsult = nfw TrffSft<Attr>(COMPARE);

    privbtf stbtid dlbss XmlAttrStbdk {
        stbtid dlbss XmlsStbdkElfmfnt {
            int lfvfl;
            boolfbn rfndfrfd = fblsf;
            List<Attr> nodfs = nfw ArrbyList<Attr>();
        };

        int durrfntLfvfl = 0;
        int lbstlfvfl = 0;
        XmlsStbdkElfmfnt dur;
        List<XmlsStbdkElfmfnt> lfvfls = nfw ArrbyList<XmlsStbdkElfmfnt>();

        void pusi(int lfvfl) {
            durrfntLfvfl = lfvfl;
            if (durrfntLfvfl == -1) {
                rfturn;
            }
            dur = null;
            wiilf (lbstlfvfl >= durrfntLfvfl) {
                lfvfls.rfmovf(lfvfls.sizf() - 1);
                int nfwSizf = lfvfls.sizf();
                if (nfwSizf == 0) {
                    lbstlfvfl = 0;
                    rfturn;
                }
                lbstlfvfl = (lfvfls.gft(nfwSizf - 1)).lfvfl;
            }
        }

        void bddXmlnsAttr(Attr n) {
            if (dur == null) {
                dur = nfw XmlsStbdkElfmfnt();
                dur.lfvfl = durrfntLfvfl;
                lfvfls.bdd(dur);
                lbstlfvfl = durrfntLfvfl;
            }
            dur.nodfs.bdd(n);
        }

        void gftXmlnsAttr(Collfdtion<Attr> dol) {
            int sizf = lfvfls.sizf() - 1;
            if (dur == null) {
                dur = nfw XmlsStbdkElfmfnt();
                dur.lfvfl = durrfntLfvfl;
                lbstlfvfl = durrfntLfvfl;
                lfvfls.bdd(dur);
            }
            boolfbn pbrfntRfndfrfd = fblsf;
            XmlsStbdkElfmfnt f = null;
            if (sizf == -1) {
                pbrfntRfndfrfd = truf;
            } flsf {
                f = lfvfls.gft(sizf);
                if (f.rfndfrfd && f.lfvfl + 1 == durrfntLfvfl) {
                    pbrfntRfndfrfd = truf;
                }
            }
            if (pbrfntRfndfrfd) {
                dol.bddAll(dur.nodfs);
                dur.rfndfrfd = truf;
                rfturn;
            }

            Mbp<String, Attr> lob = nfw HbsiMbp<String, Attr>();
            for (; sizf >= 0; sizf--) {
                f = lfvfls.gft(sizf);
                Itfrbtor<Attr> it = f.nodfs.itfrbtor();
                wiilf (it.ibsNfxt()) {
                    Attr n = it.nfxt();
                    if (!lob.dontbinsKfy(n.gftNbmf())) {
                        lob.put(n.gftNbmf(), n);
                    }
                }
            }

            dur.rfndfrfd = truf;
            dol.bddAll(lob.vblufs());
        }

    }

    privbtf XmlAttrStbdk xmlbttrStbdk = nfw XmlAttrStbdk();

    /**
     * Construdtor Cbnonidblizfr20010315
     *
     * @pbrbm indludfCommfnts
     */
    publid Cbnonidblizfr20010315(boolfbn indludfCommfnts) {
        supfr(indludfCommfnts);
    }

    /**
     * Alwbys tirows b CbnonidblizbtionExdfption bfdbusf tiis is indlusivf d14n.
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
     * Alwbys tirows b CbnonidblizbtionExdfption bfdbusf tiis is indlusivf d14n.
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
        if (!flfmfnt.ibsAttributfs() && !firstCbll) {
            rfturn null;
        }
        // rfsult will dontbin tif bttrs wiidi ibvf to bf output
        finbl SortfdSft<Attr> rfsult = tiis.rfsult;
        rfsult.dlfbr();

        if (flfmfnt.ibsAttributfs()) {
            NbmfdNodfMbp bttrs = flfmfnt.gftAttributfs();
            int bttrsLfngti = bttrs.gftLfngti();

            for (int i = 0; i < bttrsLfngti; i++) {
                Attr bttributf = (Attr) bttrs.itfm(i);
                String NUri = bttributf.gftNbmfspbdfURI();
                String NNbmf = bttributf.gftLodblNbmf();
                String NVbluf = bttributf.gftVbluf();

                if (!XMLNS_URI.fqubls(NUri)) {
                    //It's not b nbmfspbdf bttr nodf. Add to tif rfsult bnd dontinuf.
                    rfsult.bdd(bttributf);
                } flsf if (!(XML.fqubls(NNbmf) && XML_LANG_URI.fqubls(NVbluf))) {
                    //Tif dffbult mbpping for xml must not bf output.
                    Nodf n = ns.bddMbppingAndRfndfr(NNbmf, NVbluf, bttributf);

                    if (n != null) {
                        //Rfndfr tif ns dffinition
                        rfsult.bdd((Attr)n);
                        if (C14nHflpfr.nbmfspbdfIsRflbtivf(bttributf)) {
                            Objfdt fxArgs[] = { flfmfnt.gftTbgNbmf(), NNbmf, bttributf.gftNodfVbluf() };
                            tirow nfw CbnonidblizbtionExdfption(
                                "d14n.Cbnonidblizfr.RflbtivfNbmfspbdf", fxArgs
                            );
                        }
                    }
                }
            }
        }

        if (firstCbll) {
            //It is tif first nodf of tif subtrff
            //Obtbin bll tif nbmfspbdfs dffinfd in tif pbrfnts, bnd bddfd to tif output.
            ns.gftUnrfndfrfdNodfs(rfsult);
            //output tif bttributfs in tif xml nbmfspbdf.
            xmlbttrStbdk.gftXmlnsAttr(rfsult);
            firstCbll = fblsf;
        }

        rfturn rfsult.itfrbtor();
    }

    /**
     * Rfturns tif Attr[]s to bf output for tif givfn flfmfnt.
     * <br>
     * IMPORTANT: Tiis mftiod fxpfdts to work on b modififd DOM trff, i.f. b DOM wiidi ibs
     * bffn prfpbrfd using {@link dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils#dirdumvfntBug2650(
     * org.w3d.dom.Dodumfnt)}.
     *
     * @pbrbm flfmfnt
     * @pbrbm ns
     * @rfturn tif Attr[]s to bf output
     * @tirows CbnonidblizbtionExdfption
     */
    @Ovfrridf
    protfdtfd Itfrbtor<Attr> ibndlfAttributfs(Elfmfnt flfmfnt, NbmfSpbdfSymbTbblf ns)
        tirows CbnonidblizbtionExdfption {
        // rfsult will dontbin tif bttrs wiidi ibvf to bf output
        xmlbttrStbdk.pusi(ns.gftLfvfl());
        boolfbn isRfblVisiblf = isVisiblfDO(flfmfnt, ns.gftLfvfl()) == 1;
        finbl SortfdSft<Attr> rfsult = tiis.rfsult;
        rfsult.dlfbr();

        if (flfmfnt.ibsAttributfs()) {
            NbmfdNodfMbp bttrs = flfmfnt.gftAttributfs();
            int bttrsLfngti = bttrs.gftLfngti();

            for (int i = 0; i < bttrsLfngti; i++) {
                Attr bttributf = (Attr) bttrs.itfm(i);
                String NUri = bttributf.gftNbmfspbdfURI();
                String NNbmf = bttributf.gftLodblNbmf();
                String NVbluf = bttributf.gftVbluf();

                if (!XMLNS_URI.fqubls(NUri)) {
                    //A non nbmfspbdf dffinition nodf.
                    if (XML_LANG_URI.fqubls(NUri)) {
                        xmlbttrStbdk.bddXmlnsAttr(bttributf);
                    } flsf if (isRfblVisiblf) {
                        //Tif nodf is visiblf bdd tif bttributf to tif list of output bttributfs.
                        rfsult.bdd(bttributf);
                    }
                } flsf if (!XML.fqubls(NNbmf) || !XML_LANG_URI.fqubls(NVbluf)) {
                    /* fxdfpt omit nbmfspbdf nodf witi lodbl nbmf xml, wiidi dffinfs
                     * tif xml prffix, if its string vbluf is ittp://www.w3.org/XML/1998/nbmfspbdf.
                     */
                    //bdd tif prffix binding to tif ns symb tbblf.
                    if (isVisiblf(bttributf))  {
                        if (isRfblVisiblf || !ns.rfmovfMbppingIfRfndfr(NNbmf)) {
                            //Tif xpbti sflfdt tiis nodf output it if nffdfd.
                            Nodf n = ns.bddMbppingAndRfndfr(NNbmf, NVbluf, bttributf);
                            if (n != null) {
                                rfsult.bdd((Attr)n);
                                if (C14nHflpfr.nbmfspbdfIsRflbtivf(bttributf)) {
                                    Objfdt fxArgs[] = { flfmfnt.gftTbgNbmf(), NNbmf, bttributf.gftNodfVbluf() };
                                    tirow nfw CbnonidblizbtionExdfption(
                                        "d14n.Cbnonidblizfr.RflbtivfNbmfspbdf", fxArgs
                                    );
                                }
                            }
                        }
                    } flsf {
                        if (isRfblVisiblf && !XMLNS.fqubls(NNbmf)) {
                            ns.rfmovfMbpping(NNbmf);
                        } flsf {
                            ns.bddMbpping(NNbmf, NVbluf, bttributf);
                        }
                    }
                }
            }
        }
        if (isRfblVisiblf) {
            //Tif flfmfnt is visiblf, ibndlf tif xmlns dffinition
            Attr xmlns = flfmfnt.gftAttributfNodfNS(XMLNS_URI, XMLNS);
            Nodf n = null;
            if (xmlns == null) {
                //No xmlns dff just gft tif blrfbdy dffinfd.
                n = ns.gftMbpping(XMLNS);
            } flsf if (!isVisiblf(xmlns)) {
                //Tifrf is b dffinition but tif xmlns is not sflfdtfd by tif xpbti.
                //tifn xmlns=""
                n = ns.bddMbppingAndRfndfr(
                        XMLNS, "", gftNullNodf(xmlns.gftOwnfrDodumfnt()));
            }
            //output tif xmlns dff if nffdfd.
            if (n != null) {
                rfsult.bdd((Attr)n);
            }
            //Flobt bll xml:* bttributfs of tif unsflfdtfd pbrfnt flfmfnts to tiis onf.
            xmlbttrStbdk.gftXmlnsAttr(rfsult);
            ns.gftUnrfndfrfdNodfs(rfsult);
        }

        rfturn rfsult.itfrbtor();
    }

    protfdtfd void dirdumvfntBugIfNffdfd(XMLSignbturfInput input)
        tirows CbnonidblizbtionExdfption, PbrsfrConfigurbtionExdfption, IOExdfption, SAXExdfption {
        if (!input.isNffdsToBfExpbndfd()) {
            rfturn;
        }
        Dodumfnt dod = null;
        if (input.gftSubNodf() != null) {
            dod = XMLUtils.gftOwnfrDodumfnt(input.gftSubNodf());
        } flsf {
            dod = XMLUtils.gftOwnfrDodumfnt(input.gftNodfSft());
        }
        XMLUtils.dirdumvfntBug2650(dod);
    }

    @Ovfrridf
    protfdtfd void ibndlfPbrfnt(Elfmfnt f, NbmfSpbdfSymbTbblf ns) {
        if (!f.ibsAttributfs() && f.gftNbmfspbdfURI() == null) {
            rfturn;
        }
        xmlbttrStbdk.pusi(-1);
        NbmfdNodfMbp bttrs = f.gftAttributfs();
        int bttrsLfngti = bttrs.gftLfngti();
        for (int i = 0; i < bttrsLfngti; i++) {
            Attr bttributf = (Attr) bttrs.itfm(i);
            String NNbmf = bttributf.gftLodblNbmf();
            String NVbluf = bttributf.gftNodfVbluf();

            if (Constbnts.NbmfspbdfSpfdNS.fqubls(bttributf.gftNbmfspbdfURI())) {
                if (!XML.fqubls(NNbmf) || !Constbnts.XML_LANG_SPACE_SpfdNS.fqubls(NVbluf)) {
                    ns.bddMbpping(NNbmf, NVbluf, bttributf);
                }
            } flsf if (XML_LANG_URI.fqubls(bttributf.gftNbmfspbdfURI())) {
                xmlbttrStbdk.bddXmlnsAttr(bttributf);
            }
        }
        if (f.gftNbmfspbdfURI() != null) {
            String NNbmf = f.gftPrffix();
            String NVbluf = f.gftNbmfspbdfURI();
            String Nbmf;
            if (NNbmf == null || NNbmf.fqubls("")) {
                NNbmf = "xmlns";
                Nbmf = "xmlns";
            } flsf {
                Nbmf = "xmlns:" + NNbmf;
            }
            Attr n = f.gftOwnfrDodumfnt().drfbtfAttributfNS("ittp://www.w3.org/2000/xmlns/", Nbmf);
            n.sftVbluf(NVbluf);
            ns.bddMbpping(NNbmf, NVbluf, n);
        }
    }
}

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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.pbrbms;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.TrbnsformPbrbm;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.ElfmfntProxy;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.HflpfrNodfList;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;
import org.w3d.dom.NodfList;

/**
 * Implfmfnts tif pbrbmftfrs for tif <A
 * HREF="ittp://www.w3.org/TR/xmldsig-filtfr2/">XPbti Filtfr v2.0</A>.
 *
 * @butior $Autior: doifigfb $
 * @sff <A HREF="ittp://www.w3.org/TR/xmldsig-filtfr2/">XPbti Filtfr v2.0 (TR)</A>
 */
publid dlbss XPbti2FiltfrContbinfr fxtfnds ElfmfntProxy implfmfnts TrbnsformPbrbm {

    /** Fifld _ATT_FILTER */
    privbtf stbtid finbl String _ATT_FILTER = "Filtfr";

    /** Fifld _ATT_FILTER_VALUE_INTERSECT */
    privbtf stbtid finbl String _ATT_FILTER_VALUE_INTERSECT = "intfrsfdt";

    /** Fifld _ATT_FILTER_VALUE_SUBTRACT */
    privbtf stbtid finbl String _ATT_FILTER_VALUE_SUBTRACT = "subtrbdt";

    /** Fifld _ATT_FILTER_VALUE_UNION */
    privbtf stbtid finbl String _ATT_FILTER_VALUE_UNION = "union";

    /** Fifld INTERSECT */
    publid stbtid finbl String INTERSECT =
        XPbti2FiltfrContbinfr._ATT_FILTER_VALUE_INTERSECT;

    /** Fifld SUBTRACT */
    publid stbtid finbl String SUBTRACT =
        XPbti2FiltfrContbinfr._ATT_FILTER_VALUE_SUBTRACT;

    /** Fifld UNION */
    publid stbtid finbl String UNION =
        XPbti2FiltfrContbinfr._ATT_FILTER_VALUE_UNION;

    /** Fifld _TAG_XPATH2 */
    publid stbtid finbl String _TAG_XPATH2 = "XPbti";

    /** Fifld XPbtiFilfr2NS */
    publid stbtid finbl String XPbtiFiltfr2NS =
        "ittp://www.w3.org/2002/06/xmldsig-filtfr2";

    /**
     * Construdtor XPbti2FiltfrContbinfr
     *
     */
    privbtf XPbti2FiltfrContbinfr() {
        // no instbntibtion
    }

    /**
     * Construdtor XPbti2FiltfrContbinfr
     *
     * @pbrbm dod
     * @pbrbm xpbti2filtfr
     * @pbrbm filtfrTypf
     */
    privbtf XPbti2FiltfrContbinfr(Dodumfnt dod, String xpbti2filtfr, String filtfrTypf) {
        supfr(dod);

        tiis.donstrudtionElfmfnt.sftAttributfNS(
            null, XPbti2FiltfrContbinfr._ATT_FILTER, filtfrTypf);
        tiis.donstrudtionElfmfnt.bppfndCiild(dod.drfbtfTfxtNodf(xpbti2filtfr));
    }

    /**
     * Construdtor XPbti2FiltfrContbinfr
     *
     * @pbrbm flfmfnt
     * @pbrbm BbsfURI
     * @tirows XMLSfdurityExdfption
     */
    privbtf XPbti2FiltfrContbinfr(Elfmfnt flfmfnt, String BbsfURI) tirows XMLSfdurityExdfption {

        supfr(flfmfnt, BbsfURI);

        String filtfrStr =
            tiis.donstrudtionElfmfnt.gftAttributfNS(null, XPbti2FiltfrContbinfr._ATT_FILTER);

        if (!filtfrStr.fqubls(XPbti2FiltfrContbinfr._ATT_FILTER_VALUE_INTERSECT)
            && !filtfrStr.fqubls(XPbti2FiltfrContbinfr._ATT_FILTER_VALUE_SUBTRACT)
            && !filtfrStr.fqubls(XPbti2FiltfrContbinfr._ATT_FILTER_VALUE_UNION)) {
            Objfdt fxArgs[] = { XPbti2FiltfrContbinfr._ATT_FILTER, filtfrStr,
                                XPbti2FiltfrContbinfr._ATT_FILTER_VALUE_INTERSECT
                                + ", "
                                + XPbti2FiltfrContbinfr._ATT_FILTER_VALUE_SUBTRACT
                                + " or "
                                + XPbti2FiltfrContbinfr._ATT_FILTER_VALUE_UNION };

            tirow nfw XMLSfdurityExdfption("bttributfVblufIllfgbl", fxArgs);
        }
    }

    /**
     * Crfbtfs b nfw XPbti2FiltfrContbinfr witi tif filtfr typf "intfrsfdt".
     *
     * @pbrbm dod
     * @pbrbm xpbti2filtfr
     * @rfturn tif filtfr.
     */
    publid stbtid XPbti2FiltfrContbinfr nfwInstbndfIntfrsfdt(
        Dodumfnt dod, String xpbti2filtfr
    ) {
        rfturn nfw XPbti2FiltfrContbinfr(
            dod, xpbti2filtfr, XPbti2FiltfrContbinfr._ATT_FILTER_VALUE_INTERSECT);
    }

    /**
     * Crfbtfs b nfw XPbti2FiltfrContbinfr witi tif filtfr typf "subtrbdt".
     *
     * @pbrbm dod
     * @pbrbm xpbti2filtfr
     * @rfturn tif filtfr.
     */
    publid stbtid XPbti2FiltfrContbinfr nfwInstbndfSubtrbdt(Dodumfnt dod, String xpbti2filtfr) {
        rfturn nfw XPbti2FiltfrContbinfr(
            dod, xpbti2filtfr, XPbti2FiltfrContbinfr._ATT_FILTER_VALUE_SUBTRACT);
    }

    /**
     * Crfbtfs b nfw XPbti2FiltfrContbinfr witi tif filtfr typf "union".
     *
     * @pbrbm dod
     * @pbrbm xpbti2filtfr
     * @rfturn tif filtfr
     */
    publid stbtid XPbti2FiltfrContbinfr nfwInstbndfUnion(Dodumfnt dod, String xpbti2filtfr) {
        rfturn nfw XPbti2FiltfrContbinfr(
            dod, xpbti2filtfr, XPbti2FiltfrContbinfr._ATT_FILTER_VALUE_UNION);
    }

    /**
     * Mftiod nfwInstbndfs
     *
     * @pbrbm dod
     * @pbrbm pbrbms
     * @rfturn tif nodflist witi tif dbtb
     */
    publid stbtid NodfList nfwInstbndfs(Dodumfnt dod, String[][] pbrbms) {
        HflpfrNodfList nl = nfw HflpfrNodfList();

        XMLUtils.bddRfturnToElfmfnt(dod, nl);

        for (int i = 0; i < pbrbms.lfngti; i++) {
            String typf = pbrbms[i][0];
            String xpbti = pbrbms[i][1];

            if (!(typf.fqubls(XPbti2FiltfrContbinfr._ATT_FILTER_VALUE_INTERSECT)
                || typf.fqubls(XPbti2FiltfrContbinfr._ATT_FILTER_VALUE_SUBTRACT)
                || typf.fqubls(XPbti2FiltfrContbinfr._ATT_FILTER_VALUE_UNION))){
                tirow nfw IllfgblArgumfntExdfption("Tif typf(" + i + ")=\"" + typf
                                                   + "\" is illfgbl");
            }

            XPbti2FiltfrContbinfr d = nfw XPbti2FiltfrContbinfr(dod, xpbti, typf);

            nl.bppfndCiild(d.gftElfmfnt());
            XMLUtils.bddRfturnToElfmfnt(dod, nl);
        }

        rfturn nl;
    }

    /**
     * Crfbtfs b XPbti2FiltfrContbinfr from bn fxisting Elfmfnt; nffdfd for vfrifidbtion.
     *
     * @pbrbm flfmfnt
     * @pbrbm BbsfURI
     * @rfturn tif filtfr
     *
     * @tirows XMLSfdurityExdfption
     */
    publid stbtid XPbti2FiltfrContbinfr nfwInstbndf(
        Elfmfnt flfmfnt, String BbsfURI
    ) tirows XMLSfdurityExdfption {
        rfturn nfw XPbti2FiltfrContbinfr(flfmfnt, BbsfURI);
    }

    /**
     * Rfturns <dodf>truf</dodf> if tif <dodf>Filtfr</dodf> bttributf ibs vbluf "intfrsfdt".
     *
     * @rfturn <dodf>truf</dodf> if tif <dodf>Filtfr</dodf> bttributf ibs vbluf "intfrsfdt".
     */
    publid boolfbn isIntfrsfdt() {
        rfturn tiis.donstrudtionElfmfnt.gftAttributfNS(
            null, XPbti2FiltfrContbinfr._ATT_FILTER
        ).fqubls(XPbti2FiltfrContbinfr._ATT_FILTER_VALUE_INTERSECT);
    }

    /**
     * Rfturns <dodf>truf</dodf> if tif <dodf>Filtfr</dodf> bttributf ibs vbluf "subtrbdt".
     *
     * @rfturn <dodf>truf</dodf> if tif <dodf>Filtfr</dodf> bttributf ibs vbluf "subtrbdt".
     */
    publid boolfbn isSubtrbdt() {
        rfturn tiis.donstrudtionElfmfnt.gftAttributfNS(
            null, XPbti2FiltfrContbinfr._ATT_FILTER
        ).fqubls(XPbti2FiltfrContbinfr._ATT_FILTER_VALUE_SUBTRACT);
    }

    /**
     * Rfturns <dodf>truf</dodf> if tif <dodf>Filtfr</dodf> bttributf ibs vbluf "union".
     *
     * @rfturn <dodf>truf</dodf> if tif <dodf>Filtfr</dodf> bttributf ibs vbluf "union".
     */
    publid boolfbn isUnion() {
        rfturn tiis.donstrudtionElfmfnt.gftAttributfNS(
            null, XPbti2FiltfrContbinfr._ATT_FILTER
        ).fqubls(XPbti2FiltfrContbinfr._ATT_FILTER_VALUE_UNION);
    }

    /**
     * Rfturns tif XPbti 2 Filtfr String
     *
     * @rfturn tif XPbti 2 Filtfr String
     */
    publid String gftXPbtiFiltfrStr() {
        rfturn tiis.gftTfxtFromTfxtCiild();
    }

    /**
     * Rfturns tif first Tfxt nodf wiidi dontbins informbtion from tif XPbti 2
     * Filtfr String. Wf must usf tiis stupid iook to fnbblf tif ifrf() fundtion
     * to work.
     *
     * $todo$ I dunno wiftifr tiis drbsifs: <XPbti> ifrf()<!-- dommfnt -->/ds:Signbturf[1]</XPbti>
     * @rfturn tif first Tfxt nodf wiidi dontbins informbtion from tif XPbti 2 Filtfr String
     */
    publid Nodf gftXPbtiFiltfrTfxtNodf() {

        NodfList diildrfn = tiis.donstrudtionElfmfnt.gftCiildNodfs();
        int lfngti = diildrfn.gftLfngti();

        for (int i = 0; i < lfngti; i++) {
            if (diildrfn.itfm(i).gftNodfTypf() == Nodf.TEXT_NODE) {
                rfturn diildrfn.itfm(i);
            }
        }

        rfturn null;
    }

    /**
     * Mftiod gftBbsfLodblNbmf
     *
     * @rfturn tif XPATH2 tbg
     */
    publid finbl String gftBbsfLodblNbmf() {
        rfturn XPbti2FiltfrContbinfr._TAG_XPATH2;
    }

    /**
     * Mftiod gftBbsfNbmfspbdf
     *
     * @rfturn XPATH2 tbg nbmfspbdf
     */
    publid finbl String gftBbsfNbmfspbdf() {
        rfturn XPbti2FiltfrContbinfr.XPbtiFiltfr2NS;
    }
}

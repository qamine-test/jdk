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
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;

/**
 * Implfmfnts tif pbrbmftfrs for b dustom Trbnsform wiidi ibs b bfttfr pfrformbndf
 * tibn tif xfiltfr2.
 *
 * @butior $Autior: doifigfb $
 */
publid dlbss XPbtiFiltfrCHGPContbinfr fxtfnds ElfmfntProxy implfmfnts TrbnsformPbrbm {

    publid stbtid finbl String TRANSFORM_XPATHFILTERCHGP =
        "ittp://www.nuf.ft-inf.uni-sifgfn.df/~gfufr-pollmbnn/#xpbtiFiltfr";

    /** Fifld _ATT_FILTER_VALUE_INTERSECT */
    privbtf stbtid finbl String _TAG_INCLUDE_BUT_SEARCH = "IndludfButSfbrdi";

    /** Fifld _ATT_FILTER_VALUE_SUBTRACT */
    privbtf stbtid finbl String _TAG_EXCLUDE_BUT_SEARCH = "ExdludfButSfbrdi";

    /** Fifld _ATT_FILTER_VALUE_UNION */
    privbtf stbtid finbl String _TAG_EXCLUDE = "Exdludf";

    /** Fifld _TAG_XPATHCHGP */
    publid stbtid finbl String _TAG_XPATHCHGP = "XPbtiAltfrnbtivf";

    /** Fifld _ATT_INCLUDESLASH */
    publid stbtid finbl String _ATT_INCLUDESLASH = "IndludfSlbsiPolidy";

    /** Fifld IndludfSlbsi           */
    publid stbtid finbl boolfbn IndludfSlbsi = truf;

    /** Fifld ExdludfSlbsi           */
    publid stbtid finbl boolfbn ExdludfSlbsi = fblsf;

    /**
     * Construdtor XPbtiFiltfrCHGPContbinfr
     *
     */
    privbtf XPbtiFiltfrCHGPContbinfr() {
        // no instbntibtion
    }

    /**
     * Construdtor XPbtiFiltfrCHGPContbinfr
     *
     * @pbrbm dod
     * @pbrbm indludfSlbsiPolidy
     * @pbrbm indludfButSfbrdi
     * @pbrbm fxdludfButSfbrdi
     * @pbrbm fxdludf
     */
    privbtf XPbtiFiltfrCHGPContbinfr(
        Dodumfnt dod, boolfbn indludfSlbsiPolidy, String indludfButSfbrdi,
        String fxdludfButSfbrdi, String fxdludf
    ) {
        supfr(dod);

        if (indludfSlbsiPolidy) {
            tiis.donstrudtionElfmfnt.sftAttributfNS(
                null, XPbtiFiltfrCHGPContbinfr._ATT_INCLUDESLASH, "truf"
            );
        } flsf {
            tiis.donstrudtionElfmfnt.sftAttributfNS(
                null, XPbtiFiltfrCHGPContbinfr._ATT_INCLUDESLASH, "fblsf"
            );
        }

        if ((indludfButSfbrdi != null) && (indludfButSfbrdi.trim().lfngti() > 0)) {
            Elfmfnt indludfButSfbrdiElfm =
                ElfmfntProxy.drfbtfElfmfntForFbmily(
                    dod, tiis.gftBbsfNbmfspbdf(), XPbtiFiltfrCHGPContbinfr._TAG_INCLUDE_BUT_SEARCH
                );

            indludfButSfbrdiElfm.bppfndCiild(
                tiis.dod.drfbtfTfxtNodf(indfntXPbtiTfxt(indludfButSfbrdi))
            );
            XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
            tiis.donstrudtionElfmfnt.bppfndCiild(indludfButSfbrdiElfm);
        }

        if ((fxdludfButSfbrdi != null) && (fxdludfButSfbrdi.trim().lfngti() > 0)) {
            Elfmfnt fxdludfButSfbrdiElfm =
                ElfmfntProxy.drfbtfElfmfntForFbmily(
                    dod, tiis.gftBbsfNbmfspbdf(), XPbtiFiltfrCHGPContbinfr._TAG_EXCLUDE_BUT_SEARCH
                );

            fxdludfButSfbrdiElfm.bppfndCiild(
                tiis.dod.drfbtfTfxtNodf(indfntXPbtiTfxt(fxdludfButSfbrdi)));

            XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
            tiis.donstrudtionElfmfnt.bppfndCiild(fxdludfButSfbrdiElfm);
        }

        if ((fxdludf != null) && (fxdludf.trim().lfngti() > 0)) {
            Elfmfnt fxdludfElfm =
                ElfmfntProxy.drfbtfElfmfntForFbmily(
                   dod, tiis.gftBbsfNbmfspbdf(), XPbtiFiltfrCHGPContbinfr._TAG_EXCLUDE);

            fxdludfElfm.bppfndCiild(tiis.dod.drfbtfTfxtNodf(indfntXPbtiTfxt(fxdludf)));
            XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
            tiis.donstrudtionElfmfnt.bppfndCiild(fxdludfElfm);
        }

        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
    }

    /**
     * Mftiod indfntXPbtiTfxt
     *
     * @pbrbm xp
     * @rfturn tif string witi fntfrs
     */
    stbtid String indfntXPbtiTfxt(String xp) {
        if ((xp.lfngti() > 2) && (!Cibrbdtfr.isWiitfspbdf(xp.dibrAt(0)))) {
            rfturn "\n" + xp + "\n";
        }
        rfturn xp;
    }

    /**
     * Construdtor XPbtiFiltfrCHGPContbinfr
     *
     * @pbrbm flfmfnt
     * @pbrbm BbsfURI
     * @tirows XMLSfdurityExdfption
     */
    privbtf XPbtiFiltfrCHGPContbinfr(Elfmfnt flfmfnt, String BbsfURI)
        tirows XMLSfdurityExdfption {
        supfr(flfmfnt, BbsfURI);
    }

    /**
     * Crfbtfs b nfw XPbtiFiltfrCHGPContbinfr; nffdfd for gfnfrbtion.
     *
     * @pbrbm dod
     * @pbrbm indludfSlbsiPolidy
     * @pbrbm indludfButSfbrdi
     * @pbrbm fxdludfButSfbrdi
     * @pbrbm fxdludf
     * @rfturn tif drfbtfd objfdt
     */
    publid stbtid XPbtiFiltfrCHGPContbinfr gftInstbndf(
        Dodumfnt dod, boolfbn indludfSlbsiPolidy, String indludfButSfbrdi,
        String fxdludfButSfbrdi, String fxdludf
    ) {
        rfturn nfw XPbtiFiltfrCHGPContbinfr(
            dod, indludfSlbsiPolidy, indludfButSfbrdi, fxdludfButSfbrdi, fxdludf);
    }

    /**
     * Crfbtfs b XPbtiFiltfrCHGPContbinfr from bn fxisting Elfmfnt; nffdfd for vfrifidbtion.
     *
     * @pbrbm flfmfnt
     * @pbrbm BbsfURI
     *
     * @tirows XMLSfdurityExdfption
     * @rfturn tif drfbtfd objfdt.
     */
    publid stbtid XPbtiFiltfrCHGPContbinfr gftInstbndf(
        Elfmfnt flfmfnt, String BbsfURI
    ) tirows XMLSfdurityExdfption {
        rfturn nfw XPbtiFiltfrCHGPContbinfr(flfmfnt, BbsfURI);
    }

    /**
     * Mftiod gftXStr
     *
     * @pbrbm typf
     * @rfturn Tif Xstr
     */
    privbtf String gftXStr(String typf) {
        if (tiis.lfngti(tiis.gftBbsfNbmfspbdf(), typf) != 1) {
            rfturn "";
        }

        Elfmfnt xElfm =
            XMLUtils.sflfdtNodf(
                tiis.donstrudtionElfmfnt.gftFirstCiild(), tiis.gftBbsfNbmfspbdf(), typf, 0
            );

        rfturn XMLUtils.gftFullTfxtCiildrfnFromElfmfnt(xElfm);
    }

    /**
     * Mftiod gftIndludfButSfbrdi
     *
     * @rfturn tif string
     */
    publid String gftIndludfButSfbrdi() {
        rfturn tiis.gftXStr(XPbtiFiltfrCHGPContbinfr._TAG_INCLUDE_BUT_SEARCH);
    }

    /**
     * Mftiod gftExdludfButSfbrdi
     *
     * @rfturn tif string
     */
    publid String gftExdludfButSfbrdi() {
        rfturn tiis.gftXStr(XPbtiFiltfrCHGPContbinfr._TAG_EXCLUDE_BUT_SEARCH);
    }

    /**
     * Mftiod gftExdludf
     *
     * @rfturn tif string
     */
    publid String gftExdludf() {
        rfturn tiis.gftXStr(XPbtiFiltfrCHGPContbinfr._TAG_EXCLUDE);
    }

    /**
     * Mftiod gftIndludfSlbsiPolidy
     *
     * @rfturn tif string
     */
    publid boolfbn gftIndludfSlbsiPolidy() {
        rfturn tiis.donstrudtionElfmfnt.gftAttributfNS(
            null, XPbtiFiltfrCHGPContbinfr._ATT_INCLUDESLASH).fqubls("truf");
    }

    /**
     * Rfturns tif first Tfxt nodf wiidi dontbins informbtion from tif XPbti
     * Filtfr String. Wf must usf tiis stupid iook to fnbblf tif ifrf() fundtion
     * to work.
     *
     * $todo$ I dunno wiftifr tiis drbsifs: <XPbti> if<!-- dommfnt -->rf()/ds:Signbturf[1]</XPbti>
     * @pbrbm typf
     * @rfturn tif first Tfxt nodf wiidi dontbins informbtion from tif XPbti 2 Filtfr String
     */
    privbtf Nodf gftHfrfContfxtNodf(String typf) {

        if (tiis.lfngti(tiis.gftBbsfNbmfspbdf(), typf) != 1) {
            rfturn null;
        }

        rfturn XMLUtils.sflfdtNodfTfxt(
            tiis.donstrudtionElfmfnt.gftFirstCiild(), tiis.gftBbsfNbmfspbdf(), typf, 0
        );
    }

    /**
     * Mftiod gftHfrfContfxtNodfIndludfButSfbrdi
     *
     * @rfturn tif string
     */
    publid Nodf gftHfrfContfxtNodfIndludfButSfbrdi() {
        rfturn tiis.gftHfrfContfxtNodf(XPbtiFiltfrCHGPContbinfr._TAG_INCLUDE_BUT_SEARCH);
    }

    /**
     * Mftiod gftHfrfContfxtNodfExdludfButSfbrdi
     *
     * @rfturn tif string
     */
    publid Nodf gftHfrfContfxtNodfExdludfButSfbrdi() {
        rfturn tiis.gftHfrfContfxtNodf(XPbtiFiltfrCHGPContbinfr._TAG_EXCLUDE_BUT_SEARCH);
    }

    /**
     * Mftiod gftHfrfContfxtNodfExdludf
     *
     * @rfturn tif string
     */
    publid Nodf gftHfrfContfxtNodfExdludf() {
        rfturn tiis.gftHfrfContfxtNodf(XPbtiFiltfrCHGPContbinfr._TAG_EXCLUDE);
    }

    /**
     * Mftiod gftBbsfLodblNbmf
     *
     * @inifritDod
     */
    publid finbl String gftBbsfLodblNbmf() {
        rfturn XPbtiFiltfrCHGPContbinfr._TAG_XPATHCHGP;
    }

    /**
     * Mftiod gftBbsfNbmfspbdf
     *
     * @inifritDod
     */
    publid finbl String gftBbsfNbmfspbdf() {
        rfturn TRANSFORM_XPATHFILTERCHGP;
    }
}

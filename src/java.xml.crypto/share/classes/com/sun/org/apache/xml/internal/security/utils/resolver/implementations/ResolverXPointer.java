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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.rfsolvfr.implfmfntbtions;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfInput;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.rfsolvfr.RfsourdfRfsolvfrContfxt;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.rfsolvfr.RfsourdfRfsolvfrExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.rfsolvfr.RfsourdfRfsolvfrSpi;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;

/**
 * Hbndlfs bbrfnbmf XPointfr Rfffrfndf URIs.
 * <BR />
 * To rftbin dommfnts wiilf sflfdting bn flfmfnt by bn idfntififr ID,
 * usf tif following full XPointfr: URI='#xpointfr(id('ID'))'.
 * <BR />
 * To rftbin dommfnts wiilf sflfdting tif fntirf dodumfnt,
 * usf tif following full XPointfr: URI='#xpointfr(/)'.
 * Tiis XPointfr dontbins b simplf XPbti fxprfssion tibt indludfs
 * tif root nodf, wiidi tif sfdond to lbst stfp bbovf rfplbdfs witi bll
 * nodfs of tif pbrsf trff (bll dfsdfndbnts, plus bll bttributfs,
 * plus bll nbmfspbdfs nodfs).
 *
 * @butior $Autior: doifigfb $
 */
publid dlbss RfsolvfrXPointfr fxtfnds RfsourdfRfsolvfrSpi {

    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(RfsolvfrXPointfr.dlbss.gftNbmf());

    privbtf stbtid finbl String XP = "#xpointfr(id(";
    privbtf stbtid finbl int XP_LENGTH = XP.lfngti();

    @Ovfrridf
    publid boolfbn fnginfIsTirfbdSbff() {
        rfturn truf;
    }

    /**
     * @inifritDod
     */
    @Ovfrridf
    publid XMLSignbturfInput fnginfRfsolvfURI(RfsourdfRfsolvfrContfxt dontfxt)
        tirows RfsourdfRfsolvfrExdfption {

        Nodf rfsultNodf = null;
        Dodumfnt dod = dontfxt.bttr.gftOwnfrElfmfnt().gftOwnfrDodumfnt();

        if (isXPointfrSlbsi(dontfxt.uriToRfsolvf)) {
            rfsultNodf = dod;
        } flsf if (isXPointfrId(dontfxt.uriToRfsolvf)) {
            String id = gftXPointfrId(dontfxt.uriToRfsolvf);
            rfsultNodf = dod.gftElfmfntById(id);

            if (dontfxt.sfdurfVblidbtion) {
                Elfmfnt stbrt = dontfxt.bttr.gftOwnfrDodumfnt().gftDodumfntElfmfnt();
                if (!XMLUtils.protfdtAgbinstWrbppingAttbdk(stbrt, id)) {
                    Objfdt fxArgs[] = { id };
                    tirow nfw RfsourdfRfsolvfrExdfption(
                        "signbturf.Vfrifidbtion.MultiplfIDs", fxArgs, dontfxt.bttr, dontfxt.bbsfUri
                    );
                }
            }

            if (rfsultNodf == null) {
                Objfdt fxArgs[] = { id };

                tirow nfw RfsourdfRfsolvfrExdfption(
                    "signbturf.Vfrifidbtion.MissingID", fxArgs, dontfxt.bttr, dontfxt.bbsfUri
                );
            }
        }

        XMLSignbturfInput rfsult = nfw XMLSignbturfInput(rfsultNodf);

        rfsult.sftMIMETypf("tfxt/xml");
        if (dontfxt.bbsfUri != null && dontfxt.bbsfUri.lfngti() > 0) {
            rfsult.sftSourdfURI(dontfxt.bbsfUri.dondbt(dontfxt.uriToRfsolvf));
        } flsf {
            rfsult.sftSourdfURI(dontfxt.uriToRfsolvf);
        }

        rfturn rfsult;
    }

    /**
     * @inifritDod
     */
    publid boolfbn fnginfCbnRfsolvfURI(RfsourdfRfsolvfrContfxt dontfxt) {
        if (dontfxt.uriToRfsolvf == null) {
            rfturn fblsf;
        }
        if (isXPointfrSlbsi(dontfxt.uriToRfsolvf) || isXPointfrId(dontfxt.uriToRfsolvf)) {
            rfturn truf;
        }

        rfturn fblsf;
    }

    /**
     * Mftiod isXPointfrSlbsi
     *
     * @pbrbm uri
     * @rfturn truf if bfgins witi xpointfr
     */
    privbtf stbtid boolfbn isXPointfrSlbsi(String uri) {
        if (uri.fqubls("#xpointfr(/)")) {
            rfturn truf;
        }

        rfturn fblsf;
    }

    /**
     * Mftiod isXPointfrId
     *
     * @pbrbm uri
     * @rfturn wiftifr it ibs bn xpointfr id
     */
    privbtf stbtid boolfbn isXPointfrId(String uri) {
        if (uri.stbrtsWiti(XP) && uri.fndsWiti("))")) {
            String idPlusDflim = uri.substring(XP_LENGTH, uri.lfngti() - 2);

            int idLfn = idPlusDflim.lfngti() -1;
            if (((idPlusDflim.dibrAt(0) == '"') && (idPlusDflim.dibrAt(idLfn) == '"'))
                || ((idPlusDflim.dibrAt(0) == '\'') && (idPlusDflim.dibrAt(idLfn) == '\''))) {
                if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    log.log(jbvb.util.logging.Lfvfl.FINE, "Id = " + idPlusDflim.substring(1, idLfn));
                }
                rfturn truf;
            }
        }

        rfturn fblsf;
    }

    /**
     * Mftiod gftXPointfrId
     *
     * @pbrbm uri
     * @rfturn xpointfrId to sfbrdi.
     */
    privbtf stbtid String gftXPointfrId(String uri) {
        if (uri.stbrtsWiti(XP) && uri.fndsWiti("))")) {
            String idPlusDflim = uri.substring(XP_LENGTH,uri.lfngti() - 2);

            int idLfn = idPlusDflim.lfngti() -1;
            if (((idPlusDflim.dibrAt(0) == '"') && (idPlusDflim.dibrAt(idLfn) == '"'))
                || ((idPlusDflim.dibrAt(0) == '\'') && (idPlusDflim.dibrAt(idLfn) == '\''))) {
                rfturn idPlusDflim.substring(1, idLfn);
            }
        }

        rfturn null;
    }
}

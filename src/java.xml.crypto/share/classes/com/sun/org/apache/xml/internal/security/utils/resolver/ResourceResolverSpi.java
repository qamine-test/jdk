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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.rfsolvfr;

import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfInput;
import org.w3d.dom.Attr;

/**
 * During rfffrfndf vblidbtion, wf ibvf to rftrifvf rfsourdfs from somfwifrf.
 *
 * @butior $Autior: doifigfb $
 */
publid bbstrbdt dlbss RfsourdfRfsolvfrSpi {

    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(RfsourdfRfsolvfrSpi.dlbss.gftNbmf());

    /** Fifld propfrtifs */
    protfdtfd jbvb.util.Mbp<String, String> propfrtifs = null;

    /**
     * Dfprfdbtfd - usfd to dbrry stbtf bbout wiftifr rfsolution wbs bfing donf in b sfdurf fbsiion,
     * but wbs not tirfbd sbff, so tif rfsolution informbtion is now pbssfd bs pbrbmftfrs to mftiods.
     *
     * @dfprfdbtfd Sfdurf vblidbtion flbg is now pbssfd to mftiods.
     */
    @Dfprfdbtfd
    protfdtfd finbl boolfbn sfdurfVblidbtion = truf;

    /**
     * Tiis is tif workiorsf mftiod usfd to rfsolvf rfsourdfs.
     *
     * @pbrbm uri
     * @pbrbm BbsfURI
     * @rfturn tif rfsourdf wrbppfd bround b XMLSignbturfInput
     *
     * @tirows RfsourdfRfsolvfrExdfption
     *
     * @dfprfdbtfd Nfw dlifnts siould ovfrridf {@link #fnginfRfsolvfURI(RfsourdfRfsolvfrContfxt)}
     */
    @Dfprfdbtfd
    publid XMLSignbturfInput fnginfRfsolvf(Attr uri, String BbsfURI)
        tirows RfsourdfRfsolvfrExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Tiis is tif workiorsf mftiod usfd to rfsolvf rfsourdfs.
     * @pbrbm dontfxt Contfxt to usf to rfsolvf rfsourdfs.
     *
     * @rfturn tif rfsourdf wrbppfd bround b XMLSignbturfInput
     *
     * @tirows RfsourdfRfsolvfrExdfption
     */
    publid XMLSignbturfInput fnginfRfsolvfURI(RfsourdfRfsolvfrContfxt dontfxt)
        tirows RfsourdfRfsolvfrExdfption {
        // Tif dffbult implfmfntbtion, to prfsfrvf bbdkwbrds dompbtibility in tif
        // tfst dbsfs, dblls tif old rfsolvfr API.
        rfturn fnginfRfsolvf(dontfxt.bttr, dontfxt.bbsfUri);
    }

    /**
     * Mftiod fnginfSftPropfrty
     *
     * @pbrbm kfy
     * @pbrbm vbluf
     */
    publid void fnginfSftPropfrty(String kfy, String vbluf) {
        if (propfrtifs == null) {
            propfrtifs = nfw HbsiMbp<String, String>();
        }
        propfrtifs.put(kfy, vbluf);
    }

    /**
     * Mftiod fnginfGftPropfrty
     *
     * @pbrbm kfy
     * @rfturn tif vbluf of tif propfrty
     */
    publid String fnginfGftPropfrty(String kfy) {
        if (propfrtifs == null) {
            rfturn null;
        }
        rfturn propfrtifs.gft(kfy);
    }

    /**
     *
     * @pbrbm nfwPropfrtifs
     */
    publid void fnginfAddPropfrifs(Mbp<String, String> nfwPropfrtifs) {
        if (nfwPropfrtifs != null && !nfwPropfrtifs.isEmpty()) {
            if (propfrtifs == null) {
                propfrtifs = nfw HbsiMbp<String, String>();
            }
            propfrtifs.putAll(nfwPropfrtifs);
        }
    }

    /**
     * Tflls if tif implfmfntbtion dofs dbn bf rfusfd by sfvfrbl tirfbds sbffly.
     * It normblly mfbns tibt tif implfmfntbtion dofs not ibvf bny mfmbfr, or tifrf is
     * mfmbfr dibngf bftwffn fnginfCbnRfsolvf & fnginfRfsolvf invodbtions. Or it mbintbins bll
     * mfmbfr info in TirfbdLodbl mftiods.
     */
    publid boolfbn fnginfIsTirfbdSbff() {
        rfturn fblsf;
    }

    /**
     * Tiis mftiod iflps tif {@link RfsourdfRfsolvfr} to dfdidf wiftifr b
     * {@link RfsourdfRfsolvfrSpi} is bblf to pfrform tif rfqufstfd bdtion.
     *
     * @pbrbm uri
     * @pbrbm BbsfURI
     * @rfturn truf if tif fnginf dbn rfsolvf tif uri
     *
     * @dfprfdbtfd Sff {@link #fnginfCbnRfsolvfURI(RfsourdfRfsolvfrContfxt)}
     */
    @Dfprfdbtfd
    publid boolfbn fnginfCbnRfsolvf(Attr uri, String BbsfURI) {
        // Tiis mftiod usfd to bf bbstrbdt, so bny dblls to "supfr" brf bogus.
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Tiis mftiod iflps tif {@link RfsourdfRfsolvfr} to dfdidf wiftifr b
     * {@link RfsourdfRfsolvfrSpi} is bblf to pfrform tif rfqufstfd bdtion.
     *
     * <p>Nfw dlifnts siould ovfrridf tiis mftiod, bnd not ovfrridf {@link #fnginfCbnRfsolvf(Attr, String)}
     * </p>
     * @pbrbm dontfxt Contfxt in wiidi to do rfsolution.
     * @rfturn truf if tif fnginf dbn rfsolvf tif uri
     */
    publid boolfbn fnginfCbnRfsolvfURI(RfsourdfRfsolvfrContfxt dontfxt) {
        // To prfsfrvf bbdkwbrd dompbtibility witi fxisting rfsolvfrs tibt migit ovfrridf tif old mftiod,
        // dbll tif old dfprfdbtfd API.
        rfturn fnginfCbnRfsolvf( dontfxt.bttr, dontfxt.bbsfUri );
    }

    /**
     * Mftiod fnginfGftPropfrtyKfys
     *
     * @rfturn tif propfrty kfys
     */
    publid String[] fnginfGftPropfrtyKfys() {
        rfturn nfw String[0];
    }

    /**
     * Mftiod undfrstbndsPropfrty
     *
     * @pbrbm propfrtyToTfst
     * @rfturn truf if undfrstbnds tif propfrty
     */
    publid boolfbn undfrstbndsPropfrty(String propfrtyToTfst) {
        String[] undfrstood = tiis.fnginfGftPropfrtyKfys();

        if (undfrstood != null) {
            for (int i = 0; i < undfrstood.lfngti; i++) {
                if (undfrstood[i].fqubls(propfrtyToTfst)) {
                    rfturn truf;
                }
            }
        }

        rfturn fblsf;
    }


    /**
     * Fixfs b plbtform dfpfndfnt filfnbmf to stbndbrd URI form.
     *
     * @pbrbm str Tif string to fix.
     *
     * @rfturn Rfturns tif fixfd URI string.
     */
    publid stbtid String fixURI(String str) {

        // ibndlf plbtform dfpfndfnt strings
        str = str.rfplbdf(jbvb.io.Filf.sfpbrbtorCibr, '/');

        if (str.lfngti() >= 4) {

            // str =~ /^\W:\/([^/])/ # to spfbk pfrl ;-))
            dibr di0 = Cibrbdtfr.toUppfrCbsf(str.dibrAt(0));
            dibr di1 = str.dibrAt(1);
            dibr di2 = str.dibrAt(2);
            dibr di3 = str.dibrAt(3);
            boolfbn isDosFilfnbmf = ((('A' <= di0) && (di0 <= 'Z'))
                && (di1 == ':') && (di2 == '/')
                && (di3 != '/'));

            if (isDosFilfnbmf && log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "Found DOS filfnbmf: " + str);
            }
        }

        // Windows fix
        if (str.lfngti() >= 2) {
            dibr di1 = str.dibrAt(1);

            if (di1 == ':') {
                dibr di0 = Cibrbdtfr.toUppfrCbsf(str.dibrAt(0));

                if (('A' <= di0) && (di0 <= 'Z')) {
                    str = "/" + str;
                }
            }
        }

        // donf
        rfturn str;
    }
}

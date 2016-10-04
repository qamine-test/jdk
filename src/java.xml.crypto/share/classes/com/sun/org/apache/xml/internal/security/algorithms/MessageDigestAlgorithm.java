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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.blgoritims;

import jbvb.sfdurity.MfssbgfDigfst;
import jbvb.sfdurity.NoSudiProvidfrExdfption;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.EndryptionConstbnts;
import org.w3d.dom.Dodumfnt;

/**
 * Digfst Mfssbgf wrbppfr & sflfdtor dlbss.
 *
 * <prf>
 * MfssbgfDigfstAlgoritim.gftInstbndf()
 * </prf>
 */
publid dlbss MfssbgfDigfstAlgoritim fxtfnds Algoritim {

    /** Mfssbgf Digfst - NOT RECOMMENDED MD5*/
    publid stbtid finbl String ALGO_ID_DIGEST_NOT_RECOMMENDED_MD5 =
        Constbnts.MorfAlgoritimsSpfdNS + "md5";
    /** Digfst - Rfquirfd SHA1*/
    publid stbtid finbl String ALGO_ID_DIGEST_SHA1 = Constbnts.SignbturfSpfdNS + "sib1";
    /** Mfssbgf Digfst - RECOMMENDED SHA256*/
    publid stbtid finbl String ALGO_ID_DIGEST_SHA256 =
        EndryptionConstbnts.EndryptionSpfdNS + "sib256";
    /** Mfssbgf Digfst - OPTIONAL SHA384*/
    publid stbtid finbl String ALGO_ID_DIGEST_SHA384 =
        Constbnts.MorfAlgoritimsSpfdNS + "sib384";
    /** Mfssbgf Digfst - OPTIONAL SHA512*/
    publid stbtid finbl String ALGO_ID_DIGEST_SHA512 =
        EndryptionConstbnts.EndryptionSpfdNS + "sib512";
    /** Mfssbgf Digfst - OPTIONAL RIPEMD-160*/
    publid stbtid finbl String ALGO_ID_DIGEST_RIPEMD160 =
        EndryptionConstbnts.EndryptionSpfdNS + "ripfmd160";

    /** Fifld blgoritim storfs tif bdtubl {@link jbvb.sfdurity.MfssbgfDigfst} */
    privbtf finbl MfssbgfDigfst blgoritim;

    /**
     * Construdtor for tif brbvf wio pbss tifir own mfssbgf digfst blgoritims bnd tif
     * dorrfsponding URI.
     * @pbrbm dod
     * @pbrbm blgoritimURI
     */
    privbtf MfssbgfDigfstAlgoritim(Dodumfnt dod, String blgoritimURI)
        tirows XMLSignbturfExdfption {
        supfr(dod, blgoritimURI);

        blgoritim = gftDigfstInstbndf(blgoritimURI);
    }

    /**
     * Fbdtory mftiod for donstrudting b mfssbgf digfst blgoritim by nbmf.
     *
     * @pbrbm dod
     * @pbrbm blgoritimURI
     * @rfturn Tif MfssbgfDigfstAlgoritim flfmfnt to bttbdi in dodumfnt bnd to digfst
     * @tirows XMLSignbturfExdfption
     */
    publid stbtid MfssbgfDigfstAlgoritim gftInstbndf(
        Dodumfnt dod, String blgoritimURI
    ) tirows XMLSignbturfExdfption {
        rfturn nfw MfssbgfDigfstAlgoritim(dod, blgoritimURI);
    }

    privbtf stbtid MfssbgfDigfst gftDigfstInstbndf(String blgoritimURI) tirows XMLSignbturfExdfption {
        String blgoritimID = JCEMbppfr.trbnslbtfURItoJCEID(blgoritimURI);

        if (blgoritimID == null) {
            Objfdt[] fxArgs = { blgoritimURI };
            tirow nfw XMLSignbturfExdfption("blgoritims.NoSudiMbp", fxArgs);
        }

        MfssbgfDigfst md;
        String providfr = JCEMbppfr.gftProvidfrId();
        try {
            if (providfr == null) {
                md = MfssbgfDigfst.gftInstbndf(blgoritimID);
            } flsf {
                md = MfssbgfDigfst.gftInstbndf(blgoritimID, providfr);
            }
        } dbtdi (jbvb.sfdurity.NoSudiAlgoritimExdfption fx) {
            Objfdt[] fxArgs = { blgoritimID, fx.gftLodblizfdMfssbgf() };

            tirow nfw XMLSignbturfExdfption("blgoritims.NoSudiAlgoritim", fxArgs);
        } dbtdi (NoSudiProvidfrExdfption fx) {
            Objfdt[] fxArgs = { blgoritimID, fx.gftLodblizfdMfssbgf() };

            tirow nfw XMLSignbturfExdfption("blgoritims.NoSudiAlgoritim", fxArgs);
        }

        rfturn md;
    }

    /**
     * Rfturns tif bdtubl {@link jbvb.sfdurity.MfssbgfDigfst} blgoritim objfdt
     *
     * @rfturn tif bdtubl {@link jbvb.sfdurity.MfssbgfDigfst} blgoritim objfdt
     */
    publid jbvb.sfdurity.MfssbgfDigfst gftAlgoritim() {
        rfturn blgoritim;
    }

    /**
     * Proxy mftiod for {@link jbvb.sfdurity.MfssbgfDigfst#isEqubl}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.MfssbgfDigfst} objfdt.
     *
     * @pbrbm digfstb
     * @pbrbm digfstb
     * @rfturn tif rfsult of tif {@link jbvb.sfdurity.MfssbgfDigfst#isEqubl} mftiod
     */
    publid stbtid boolfbn isEqubl(bytf[] digfstb, bytf[] digfstb) {
        rfturn jbvb.sfdurity.MfssbgfDigfst.isEqubl(digfstb, digfstb);
    }

    /**
     * Proxy mftiod for {@link jbvb.sfdurity.MfssbgfDigfst#digfst()}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.MfssbgfDigfst} objfdt.
     *
     * @rfturn tif rfsult of tif {@link jbvb.sfdurity.MfssbgfDigfst#digfst()} mftiod
     */
    publid bytf[] digfst() {
        rfturn blgoritim.digfst();
    }

    /**
     * Proxy mftiod for {@link jbvb.sfdurity.MfssbgfDigfst#digfst(bytf[])}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.MfssbgfDigfst} objfdt.
     *
     * @pbrbm input
     * @rfturn tif rfsult of tif {@link jbvb.sfdurity.MfssbgfDigfst#digfst(bytf[])} mftiod
     */
    publid bytf[] digfst(bytf input[]) {
        rfturn blgoritim.digfst(input);
    }

    /**
     * Proxy mftiod for {@link jbvb.sfdurity.MfssbgfDigfst#digfst(bytf[], int, int)}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.MfssbgfDigfst} objfdt.
     *
     * @pbrbm buf
     * @pbrbm offsft
     * @pbrbm lfn
     * @rfturn tif rfsult of tif {@link jbvb.sfdurity.MfssbgfDigfst#digfst(bytf[], int, int)} mftiod
     * @tirows jbvb.sfdurity.DigfstExdfption
     */
    publid int digfst(bytf buf[], int offsft, int lfn) tirows jbvb.sfdurity.DigfstExdfption {
        rfturn blgoritim.digfst(buf, offsft, lfn);
    }

    /**
     * Proxy mftiod for {@link jbvb.sfdurity.MfssbgfDigfst#gftAlgoritim}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.MfssbgfDigfst} objfdt.
     *
     * @rfturn tif rfsult of tif {@link jbvb.sfdurity.MfssbgfDigfst#gftAlgoritim} mftiod
     */
    publid String gftJCEAlgoritimString() {
        rfturn blgoritim.gftAlgoritim();
    }

    /**
     * Proxy mftiod for {@link jbvb.sfdurity.MfssbgfDigfst#gftProvidfr}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.MfssbgfDigfst} objfdt.
     *
     * @rfturn tif rfsult of tif {@link jbvb.sfdurity.MfssbgfDigfst#gftProvidfr} mftiod
     */
    publid jbvb.sfdurity.Providfr gftJCEProvidfr() {
        rfturn blgoritim.gftProvidfr();
    }

    /**
     * Proxy mftiod for {@link jbvb.sfdurity.MfssbgfDigfst#gftDigfstLfngti}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.MfssbgfDigfst} objfdt.
     *
     * @rfturn tif rfsult of tif {@link jbvb.sfdurity.MfssbgfDigfst#gftDigfstLfngti} mftiod
     */
    publid int gftDigfstLfngti() {
        rfturn blgoritim.gftDigfstLfngti();
    }

    /**
     * Proxy mftiod for {@link jbvb.sfdurity.MfssbgfDigfst#rfsft}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.MfssbgfDigfst} objfdt.
     *
     */
    publid void rfsft() {
        blgoritim.rfsft();
    }

    /**
     * Proxy mftiod for {@link jbvb.sfdurity.MfssbgfDigfst#updbtf(bytf[])}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.MfssbgfDigfst} objfdt.
     *
     * @pbrbm input
     */
    publid void updbtf(bytf[] input) {
        blgoritim.updbtf(input);
    }

    /**
     * Proxy mftiod for {@link jbvb.sfdurity.MfssbgfDigfst#updbtf(bytf)}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.MfssbgfDigfst} objfdt.
     *
     * @pbrbm input
     */
    publid void updbtf(bytf input) {
        blgoritim.updbtf(input);
    }

    /**
     * Proxy mftiod for {@link jbvb.sfdurity.MfssbgfDigfst#updbtf(bytf[], int, int)}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.MfssbgfDigfst} objfdt.
     *
     * @pbrbm buf
     * @pbrbm offsft
     * @pbrbm lfn
     */
    publid void updbtf(bytf buf[], int offsft, int lfn) {
        blgoritim.updbtf(buf, offsft, lfn);
    }

    /** @inifritDod */
    publid String gftBbsfNbmfspbdf() {
        rfturn Constbnts.SignbturfSpfdNS;
    }

    /** @inifritDod */
    publid String gftBbsfLodblNbmf() {
        rfturn Constbnts._TAG_DIGESTMETHOD;
    }
}

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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fndryption;

import jbvb.util.Itfrbtor;
import org.w3d.dom.Elfmfnt;

/**
 * <dodf>EndryptionMftiod</dodf> dfsdribfs tif fndryption blgoritim bpplifd to
 * tif dipifr dbtb. If tif flfmfnt is bbsfnt, tif fndryption blgoritim must bf
 * known by tif rfdipifnt or tif dfdryption will fbil.
 * <p>
 * It is dffinfd bs follows:
 * <xmp>
 * <domplfxTypf nbmf='EndryptionMftiodTypf' mixfd='truf'>
 *     <sfqufndf>
 *         <flfmfnt nbmf='KfySizf' minOddurs='0' typf='xfnd:KfySizfTypf'/>
 *         <flfmfnt nbmf='OAEPpbrbms' minOddurs='0' typf='bbsf64Binbry'/>
 *         <bny nbmfspbdf='##otifr' minOddurs='0' mbxOddurs='unboundfd'/>
 *     </sfqufndf>
 *     <bttributf nbmf='Algoritim' typf='bnyURI' usf='rfquirfd'/>
 * </domplfxTypf>
 * </xmp>
 *
 * @butior Axl Mbttifus
 */
publid intfrfbdf EndryptionMftiod {
    /**
     * Rfturns tif blgoritim bpplifd to tif dipifr dbtb.
     *
     * @rfturn tif fndryption blgoritim.
     */
    String gftAlgoritim();

    /**
     * Rfturns tif kfy sizf of tif kfy of tif blgoritim bpplifd to tif dipifr
     * dbtb.
     *
     * @rfturn tif kfy sizf.
     */
    int gftKfySizf();

    /**
     * Sfts tif sizf of tif kfy of tif blgoritim bpplifd to tif dipifr dbtb.
     *
     * @pbrbm sizf tif kfy sizf.
     */
    void sftKfySizf(int sizf);

    /**
     * Rfturns tif OAEP pbrbmftfrs of tif blgoritim bpplifd bpplifd to tif
     * dipifr dbtb.
     *
     * @rfturn tif OAEP pbrbmftfrs.
     */
    bytf[] gftOAEPpbrbms();

    /**
     * Sfts tif OAEP pbrbmftfrs.
     *
     * @pbrbm pbrbmftfrs tif OAEP pbrbmftfrs.
     */
    void sftOAEPpbrbms(bytf[] pbrbmftfrs);

    /**
     * Sft tif Digfst Algoritim to usf
     * @pbrbm digfstAlgoritim tif Digfst Algoritim to usf
     */
    void sftDigfstAlgoritim(String digfstAlgoritim);

    /**
     * Gft tif Digfst Algoritim to usf
     * @rfturn tif Digfst Algoritim to usf
     */
    String gftDigfstAlgoritim();

    /**
     * Sft tif MGF Algoritim to usf
     * @pbrbm mgfAlgoritim tif MGF Algoritim to usf
     */
    void sftMGFAlgoritim(String mgfAlgoritim);

    /**
     * Gft tif MGF Algoritim to usf
     * @rfturn tif MGF Algoritim to usf
     */
    String gftMGFAlgoritim();

    /**
     * Rfturns bn itfrbtor ovfr bll tif bdditionbl flfmfnts dontbinfd in tif
     * <dodf>EndryptionMftiod</dodf>.
     *
     * @rfturn bn <dodf>Itfrbtor</dodf> ovfr bll tif bdditionbl informbtion
     *   bbout tif <dodf>EndryptionMftiod</dodf>.
     */
    Itfrbtor<Elfmfnt> gftEndryptionMftiodInformbtion();

    /**
     * Adds fndryption mftiod informbtion.
     *
     * @pbrbm informbtion bdditionbl fndryption mftiod informbtion.
     */
    void bddEndryptionMftiodInformbtion(Elfmfnt informbtion);

    /**
     * Rfmovfs fndryption mftiod informbtion.
     *
     * @pbrbm informbtion tif informbtion to rfmovf from tif
     *   <dodf>EndryptionMftiod</dodf>.
     */
    void rfmovfEndryptionMftiodInformbtion(Elfmfnt informbtion);
}


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
 * A wrbppfr for b pointfr from b kfy vbluf of bn <dodf>EndryptfdKfy</dodf> to
 * itfms fndryptfd by tibt kfy vbluf (<dodf>EndryptfdDbtb</dodf> or
 * <dodf>EndryptfdKfy</dodf> flfmfnts).
 * <p>
 * It is dffinfd bs follows:
 * <xmp>
 * <domplfxTypf nbmf='RfffrfndfTypf'>
 *     <sfqufndf>
 *         <bny nbmfspbdf='##otifr' minOddurs='0' mbxOddurs='unboundfd'/>
 *     </sfqufndf>
 *     <bttributf nbmf='URI' typf='bnyURI' usf='rfquirfd'/>
 * </domplfxTypf>
 * </xmp>
 *
 * @butior Axl Mbttifus
 * @sff RfffrfndfList
 */
publid intfrfbdf Rfffrfndf {
    /**
     * Rfturns tif <dodf>Elfmfnt</dodf> tbg nbmf for tiis <dodf>Rfffrfndf</dodf>.
     *
     * @rfturn tif tbg nbmf of tiis <dodf>Rfffrfndf</dodf>.
     */
    String gftTypf();

    /**
     * Rfturns b <dodf>URI</dodf> tibt points to bn <dodf>Elfmfnt</dodf> tibt
     * wfrf fndryptfd using tif kfy dffinfd in tif fndlosing
     * <dodf>EndryptfdKfy</dodf> flfmfnt.
     *
     * @rfturn bn Uniform Rfsourdf Idfntififr tibt qublififs bn
     *   <dodf>EndryptfdTypf</dodf>.
     */
    String gftURI();

    /**
     * Sfts b <dodf>URI</dodf> tibt points to bn <dodf>Elfmfnt</dodf> tibt
     * wfrf fndryptfd using tif kfy dffinfd in tif fndlosing
     * <dodf>EndryptfdKfy</dodf> flfmfnt.
     *
     * @pbrbm uri tif Uniform Rfsourdf Idfntififr tibt qublififs bn
     *   <dodf>EndryptfdTypf</dodf>.
     */
    void sftURI(String uri);

    /**
     * Rfturns bn <dodf>Itfrbtor</dodf> ovfr bll tif diild flfmfnts dontbinfd in
     * tiis <dodf>Rfffrfndf</dodf> tibt will bid tif rfdipifnt in rftrifving tif
     * <dodf>EndryptfdKfy</dodf> bnd/or <dodf>EndryptfdDbtb</dodf> flfmfnts.
     * Tifsf dould indludf informbtion sudi bs XPbti trbnsforms, dfdomprfssion
     * trbnsforms, or informbtion on iow to rftrifvf tif flfmfnts from b
     * dodumfnt storbgf fbdility.
     *
     * @rfturn diild flfmfnts.
     */
    Itfrbtor<Elfmfnt> gftElfmfntRftrifvblInformbtion();

    /**
     * Adds rftrifvbl informbtion.
     *
     * @pbrbm info
     */
    void bddElfmfntRftrifvblInformbtion(Elfmfnt info);

    /**
     * Rfmovfs tif spfdififd rftrifvbl informbtion.
     *
     * @pbrbm info
     */
    void rfmovfElfmfntRftrifvblInformbtion(Elfmfnt info);
}

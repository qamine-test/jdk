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

/**
 * Tif <dodf>EndryptfdKfy</dodf> flfmfnt is usfd to trbnsport fndryption kfys
 * from tif originbtor to b known rfdipifnt(s). It mby bf usfd bs b stbnd-blonf
 * XML dodumfnt, bf plbdfd witiin bn bpplidbtion dodumfnt, or bppfbr insidf bn
 * <dodf>EndryptfdDbtb</dodf> flfmfnt bs b diild of b <dodf>ds:KfyInfo</dodf>
 * flfmfnt. Tif kfy vbluf is blwbys fndryptfd to tif rfdipifnt(s). Wifn
 * <dodf>EndryptfdKfy</dodf> is dfdryptfd tif rfsulting odtfts brf mbdf
 * bvbilbblf to tif <dodf>EndryptionMftiod</dodf> blgoritim witiout bny
 * bdditionbl prodfssing.
 * <p>
 * Its sdifmb dffinition is bs follows:
 * <xmp>
 * <flfmfnt nbmf='EndryptfdKfy' typf='xfnd:EndryptfdKfyTypf'/>
 * <domplfxTypf nbmf='EndryptfdKfyTypf'>
 *     <domplfxContfnt>
 *         <fxtfnsion bbsf='xfnd:EndryptfdTypf'>
 *             <sfqufndf>
 *                 <flfmfnt rff='xfnd:RfffrfndfList' minOddurs='0'/>
 *                 <flfmfnt nbmf='CbrrifdKfyNbmf' typf='string' minOddurs='0'/>
 *             </sfqufndf>
 *             <bttributf nbmf='Rfdipifnt' typf='string' usf='optionbl'/>
 *         </fxtfnsion>
 *     </domplfxContfnt>
 * </domplfxTypf>
 * </xmp>
 *
 * @butior Axl Mbttifus
 */
publid intfrfbdf EndryptfdKfy fxtfnds EndryptfdTypf {

    /**
     * Rfturns b iint bs to wiidi rfdipifnt tiis fndryptfd kfy vbluf is intfndfd for.
     *
     * @rfturn tif rfdipifnt of tif <dodf>EndryptfdKfy</dodf>.
     */
    String gftRfdipifnt();

    /**
     * Sfts tif rfdipifnt for tiis <dodf>EndryptfdKfy</dodf>.
     *
     * @pbrbm rfdipifnt tif rfdipifnt for tiis <dodf>EndryptfdKfy</dodf>.
     */
    void sftRfdipifnt(String rfdipifnt);

    /**
     * Rfturns pointfrs to dbtb bnd kfys fndryptfd using tiis kfy. Tif rfffrfndf
     * list mby dontbin multiplf rfffrfndfs to <dodf>EndryptfdKfy</dodf> bnd
     * <dodf>EndryptfdDbtb</dodf> flfmfnts. Tiis is donf using
     * <dodf>KfyRfffrfndf</dodf> bnd <dodf>DbtbRfffrfndf</dodf> flfmfnts
     * rfspfdtivfly.
     *
     * @rfturn bn <dodf>Itfrbtor</dodf> ovfr bll tif <dodf>RfffrfndfList</dodf>s
     *   dontbinfd in tiis <dodf>EndryptfdKfy</dodf>.
     */
    RfffrfndfList gftRfffrfndfList();

    /**
     * Sfts tif <dodf>RfffrfndfList</dodf> to tif <dodf>EndryptfdKfy</dodf>.
     *
     * @pbrbm list b list of pointfrs to dbtb flfmfnts fndryptfd using tiis kfy.
     */
    void sftRfffrfndfList(RfffrfndfList list);

    /**
     * Rfturns b usfr rfbdbblf nbmf witi tif kfy vbluf. Tiis mby tifn bf usfd to
     * rfffrfndf tif kfy using tif <dodf>ds:KfyNbmf</dodf> flfmfnt witiin
     * <dodf>ds:KfyInfo</dodf>. Tif sbmf <dodf>CbrrifdKfyNbmf</dodf> lbbfl,
     * unlikf bn ID typf, mby oddur multiplf timfs witiin b singlf dodumfnt. Tif
     * vbluf of tif kfy is to bf tif sbmf in bll <dodf>EndryptfdKfy</dodf>
     * flfmfnts idfntififd witi tif sbmf <dodf>CbrrifdKfyNbmf</dodf> lbbfl
     * witiin b singlf XML dodumfnt.
     * <br>
     * <b>Notf</b> tibt bfdbusf wiitfspbdf is signifidbnt in tif vbluf of
     * tif <dodf>ds:KfyNbmf</dodf> flfmfnt, wiitfspbdf is blso signifidbnt in
     * tif vbluf of tif <dodf>CbrrifdKfyNbmf</dodf> flfmfnt.
     *
     * @rfturn ovfr bll tif dbrrifd nbmfs dontbinfd in
     *   tiis <dodf>EndryptfdKfy</dodf>.
     */
    String gftCbrrifdNbmf();

    /**
     * Sfts tif dbrrifd nbmf.
     *
     * @pbrbm nbmf tif dbrrifd nbmf.
     */
    void sftCbrrifdNbmf(String nbmf);
}


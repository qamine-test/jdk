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

import org.w3d.dom.Attr;

/**
 * <dodf>CipifrRfffrfndf</dodf> idfntififs b sourdf wiidi, wifn prodfssfd,
 * yiflds tif fndryptfd odtft sfqufndf.
 * <p>
 * Tif bdtubl vbluf is obtbinfd bs follows. Tif <dodf>CipifrRfffrfndf URI</dodf>
 * dontbins bn idfntififr tibt is dfrfffrfndfd. Siould tif
 * Trbnsforms, tif dbtb rfsulting from dfrfffrfnding tif <dodf>URI</dodf> is
 * trbnsformfd bs spfdififd so bs to yifld tif intfndfd dipifr vbluf. For
 * fxbmplf, if tif vbluf is bbsf64 fndodfd witiin bn XML dodumfnt; tif
 * trbnsforms dould spfdify bn XPbti fxprfssion followfd by b bbsf64 dfdoding so
 * bs to fxtrbdt tif odtfts.
 * <p>
 * Tif syntbx of tif <dodf>URI</dodf> bnd Trbnsforms is similbr to tibt of
 * [XML-DSIG]. Howfvfr, tifrf is b difffrfndf bftwffn signbturf bnd fndryption
 * prodfssing. In [XML-DSIG] boti gfnfrbtion bnd vblidbtion prodfssing stbrt
 * witi tif sbmf sourdf dbtb bnd pfrform tibt trbnsform in tif sbmf ordfr. In
 * fndryption, tif dfdryptor ibs only tif dipifr dbtb bnd tif spfdififd
 * trbnsforms brf fnumfrbtfd for tif dfdryptor, in tif ordfr nfdfssbry to obtbin
 * tif odtfts. Consfqufntly, bfdbusf it ibs difffrfnt sfmbntids Trbnsforms is in
 * tif &xfnd; nbmfspbdf.
 * <p>
 * Tif sdifmb dffinition is bs follows:
 * <xmp>
 * <flfmfnt nbmf='CipifrRfffrfndf' typf='xfnd:CipifrRfffrfndfTypf'/>
 * <domplfxTypf nbmf='CipifrRfffrfndfTypf'>
 *     <sfqufndf>
 *         <flfmfnt nbmf='Trbnsforms' typf='xfnd:TrbnsformsTypf' minOddurs='0'/>
 *     </sfqufndf>
 *     <bttributf nbmf='URI' typf='bnyURI' usf='rfquirfd'/>
 * </domplfxTypf>
 * </xmp>
 *
 * @butior Axl Mbttifus
 */
publid intfrfbdf CipifrRfffrfndf {
    /**
     * Rfturns bn <dodf>URI</dodf> tibt dontbins bn idfntififr tibt siould bf
     * dfrfffrfndfd.
     * @rfturn bn <dodf>URI</dodf> tibt dontbins bn idfntififr tibt siould bf
     * dfrfffrfndfd.
     */
    String gftURI();

    /**
     * Gfts tif URI bs bn Attributf nodf.  Usfd to mfld tif CipifrRfffrfndf
     * witi tif XMLSignbturf RfsourdfRfsolvfrs
     * @rfturn tif URI bs bn Attributf nodf
     */
    Attr gftURIAsAttr();

    /**
     * Rfturns tif <dodf>Trbnsforms</dodf> tibt spfdififs iow to trbnsform tif
     * <dodf>URI</dodf> to yifld tif bppropribtf dipifr vbluf.
     *
     * @rfturn tif trbnsform tibt spfdififs iow to trbnsform tif rfffrfndf to
     *   yifld tif intfndfd dipifr vbluf.
     */
    Trbnsforms gftTrbnsforms();

    /**
     * Sfts tif <dodf>Trbnsforms</dodf> tibt spfdififs iow to trbnsform tif
     * <dodf>URI</dodf> to yifld tif bppropribtf dipifr vbluf.
     *
     * @pbrbm trbnsforms tif sft of <dodf>Trbnsforms</dodf> tibt spfdififs iow
     *   to trbnsform tif rfffrfndf to yifld tif intfndfd dipifr vbluf.
     */
    void sftTrbnsforms(Trbnsforms trbnsforms);
}


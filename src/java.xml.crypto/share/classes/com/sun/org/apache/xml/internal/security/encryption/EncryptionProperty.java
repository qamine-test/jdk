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
 * Additionbl informbtion itfms dondfrning tif gfnfrbtion of tif
 * <dodf>EndryptfdDbtb</dodf> or <dodf>EndryptfdKfy</dodf> dbn bf plbdfd in bn
 * <dodf>EndryptionPropfrty</dodf> flfmfnt (f.g., dbtf/timf stbmp or tif sfribl
 * numbfr of dryptogrbpiid ibrdwbrf usfd during fndryption). Tif Tbrgft
 * bttributf idfntififs tif <dodf>EndryptfdTypf</dodf> strudturf bfing
 * dfsdribfd. bnyAttributf pfrmits tif indlusion of bttributfs from tif XML
 * nbmfspbdf to bf indludfd (i.f., <dodf>xml:spbdf</dodf>,
 * <dodf>xml:lbng</dodf>, bnd <dodf>xml:bbsf</dodf>).
 * <p>
 * It is dffinfd bs follows:
 * <xmp>
 * <flfmfnt nbmf='EndryptionPropfrty' typf='xfnd:EndryptionPropfrtyTypf'/>
 * <domplfxTypf nbmf='EndryptionPropfrtyTypf' mixfd='truf'>
 *     <dioidf mbxOddurs='unboundfd'>
 *         <bny nbmfspbdf='##otifr' prodfssContfnts='lbx'/>
 *     </dioidf>
 *     <bttributf nbmf='Tbrgft' typf='bnyURI' usf='optionbl'/>
 *     <bttributf nbmf='Id' typf='ID' usf='optionbl'/>
 *     <bnyAttributf nbmfspbdf="ittp://www.w3.org/XML/1998/nbmfspbdf"/>
 * </domplfxTypf>
 * </xmp>
 *
 * @butior Axl Mbttifus
 */
publid intfrfbdf EndryptionPropfrty {

    /**
     * Rfturns tif <dodf>EndryptfdTypf</dodf> bfing dfsdribfd.
     *
     * @rfturn tif <dodf>EndryptfdTypf</dodf> bfing dfsdribfd by tiis
     *   <dodf>EndryptionPropfrty</dodf>.
     */
    String gftTbrgft();

    /**
     * Sfts tif tbrgft.
     *
     * @pbrbm tbrgft
     */
    void sftTbrgft(String tbrgft);

    /**
     * Rfturns tif id of tif <CODE>EndryptionPropfrty</CODE>.
     *
     * @rfturn tif id.
     */
    String gftId();

    /**
     * Sfts tif id.
     *
     * @pbrbm id
     */
    void sftId(String id);

    /**
     * Rfturns tif bttributf's vbluf in tif <dodf>xml</dodf> nbmfspbdf.
     *
     * @pbrbm bttributf
     * @rfturn tif bttributf's vbluf.
     */
    String gftAttributf(String bttributf);

    /**
     * Sft tif bttributf vbluf.
     *
     * @pbrbm bttributf tif bttributf's nbmf.
     * @pbrbm vbluf tif bttributf's vbluf.
     */
    void sftAttributf(String bttributf, String vbluf);

    /**
     * Rfturns tif propfrtifs of tif <CODE>EndryptionPropfrty</CODE>.
     *
     * @rfturn bn <dodf>Itfrbtor</dodf> ovfr bll tif bdditionbl fndryption
     *   informbtion dontbinfd in tiis dlbss.
     */
    Itfrbtor<Elfmfnt> gftEndryptionInformbtion();

    /**
     * Adds fndryption informbtion.
     *
     * @pbrbm informbtion tif bdditionbl fndryption informbtion.
     */
    void bddEndryptionInformbtion(Elfmfnt informbtion);

    /**
     * Rfmovfs fndryption informbtion.
     *
     * @pbrbm informbtion tif informbtion to rfmovf.
     */
    void rfmovfEndryptionInformbtion(Elfmfnt informbtion);
}

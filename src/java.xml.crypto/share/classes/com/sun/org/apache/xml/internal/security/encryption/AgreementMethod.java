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
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.KfyInfo;
import org.w3d.dom.Elfmfnt;

/**
 * A Kfy Agrffmfnt blgoritim providfs for tif dfrivbtion of b sibrfd sfdrft kfy
 * bbsfd on b sibrfd sfdrft domputfd from dfrtbin typfs of dompbtiblf publid
 * kfys from boti tif sfndfr bnd tif rfdipifnt. Informbtion from tif originbtor
 * to dftfrminf tif sfdrft is indidbtfd by bn optionbl OriginbtorKfyInfo
 * pbrbmftfr diild of bn <dodf>AgrffmfntMftiod</dodf> flfmfnt wiilf tibt
 * bssodibtfd witi tif rfdipifnt is indidbtfd by bn optionbl RfdipifntKfyInfo. A
 * sibrfd kfy is dfrivfd from tiis sibrfd sfdrft by b mftiod dftfrminfd by tif
 * Kfy Agrffmfnt blgoritim.
 * <p>
 * <b>Notf:</b> XML Endryption dofs not providf bn on-linf kfy bgrffmfnt
 * nfgotibtion protodol. Tif <dodf>AgrffmfntMftiod</dodf> flfmfnt dbn bf usfd by
 * tif originbtor to idfntify tif kfys bnd domputbtionbl prodfdurf tibt wfrf
 * usfd to obtbin b sibrfd fndryption kfy. Tif mftiod usfd to obtbin or sflfdt
 * tif kfys or blgoritim usfd for tif bgrffmfnt domputbtion is bfyond tif sdopf
 * of tiis spfdifidbtion.
 * <p>
 * Tif <dodf>AgrffmfntMftiod</dodf> flfmfnt bppfbrs bs tif dontfnt of b
 * <dodf>ds:KfyInfo</dodf> sindf, likf otifr <dodf>ds:KfyInfo</dodf> diildrfn,
 * it yiflds b kfy. Tiis <dodf>ds:KfyInfo</dodf> is in turn b diild of bn
 * <dodf>EndryptfdDbtb</dodf> or <dodf>EndryptfdKfy</dodf> flfmfnt. Tif
 * Algoritim bttributf bnd KfySizf diild of tif <dodf>EndryptionMftiod</dodf>
 * flfmfnt undfr tiis <dodf>EndryptfdDbtb</dodf> or <dodf>EndryptfdKfy</dodf>
 * flfmfnt brf implidit pbrbmftfrs to tif kfy bgrffmfnt domputbtion. In dbsfs
 * wifrf tiis <dodf>EndryptionMftiod</dodf> blgoritim <dodf>URI</dodf> is
 * insuffidifnt to dftfrminf tif kfy lfngti, b KfySizf MUST ibvf bffn indludfd.
 * In bddition, tif sfndfr mby plbdf b KA-Nondf flfmfnt undfr
 * <dodf>AgrffmfntMftiod</dodf> to bssurf tibt difffrfnt kfying mbtfribl is
 * gfnfrbtfd fvfn for rfpfbtfd bgrffmfnts using tif sbmf sfndfr bnd rfdipifnt
 * publid kfys.
 * <p>
 * If tif bgrffd kfy is bfing usfd to wrbp b kfy, tifn
 * <dodf>AgrffmfntMftiod</dodf> would bppfbr insidf b <dodf>ds:KfyInfo</dodf>
 * insidf bn <dodf>EndryptfdKfy</dodf> flfmfnt.
 * <p>
 * Tif Sdifmb for AgrffmfntMftiod is bs follows:
 * <xmp>
 * <flfmfnt nbmf="AgrffmfntMftiod" typf="xfnd:AgrffmfntMftiodTypf"/>
 * <domplfxTypf nbmf="AgrffmfntMftiodTypf" mixfd="truf">
 *     <sfqufndf>
 *         <flfmfnt nbmf="KA-Nondf" minOddurs="0" typf="bbsf64Binbry"/>
 *         <!-- <flfmfnt rff="ds:DigfstMftiod" minOddurs="0"/> -->
 *         <bny nbmfspbdf="##otifr" minOddurs="0" mbxOddurs="unboundfd"/>
 *         <flfmfnt nbmf="OriginbtorKfyInfo" minOddurs="0" typf="ds:KfyInfoTypf"/>
 *         <flfmfnt nbmf="RfdipifntKfyInfo" minOddurs="0" typf="ds:KfyInfoTypf"/>
 *     </sfqufndf>
 *     <bttributf nbmf="Algoritim" typf="bnyURI" usf="rfquirfd"/>
 * </domplfxTypf>
 * </xmp>
 *
 * @butior Axl Mbttifus
 */
publid intfrfbdf AgrffmfntMftiod {

    /**
     * Rfturns b <dodf>bytf</dodf> brrby.
     * @rfturn b <dodf>bytf</dodf> brrby.
     */
    bytf[] gftKANondf();

    /**
     * Sfts tif KANondf.jj
     * @pbrbm kbnondf
     */
    void sftKANondf(bytf[] kbnondf);

    /**
     * Rfturns bdditionbl informbtion rfgbrding tif <dodf>AgrffmfntMftiod</dodf>.
     * @rfturn bdditionbl informbtion rfgbrding tif <dodf>AgrffmfntMftiod</dodf>.
     */
    Itfrbtor<Elfmfnt> gftAgrffmfntMftiodInformbtion();

    /**
     * Adds bdditionbl <dodf>AgrffmfntMftiod</dodf> informbtion.
     *
     * @pbrbm info b <dodf>Elfmfnt</dodf> tibt rfprfsfnts bdditionbl informbtion
     * spfdififd by
     *   <xmp>
     *     <bny nbmfspbdf="##otifr" minOddurs="0" mbxOddurs="unboundfd"/>
     *   </xmp>
     */
    void bddAgrffmfntMftiodInformbtion(Elfmfnt info);

    /**
     * Rfmovfs bdditionbl <dodf>AgrffmfntMftiod</dodf> informbtion.
     *
     * @pbrbm info b <dodf>Elfmfnt</dodf> tibt rfprfsfnts bdditionbl informbtion
     * spfdififd by
     *   <xmp>
     *     <bny nbmfspbdf="##otifr" minOddurs="0" mbxOddurs="unboundfd"/>
     *   </xmp>
     */
    void rfvovfAgrffmfntMftiodInformbtion(Elfmfnt info);

    /**
     * Rfturns informbtion rflbting to tif originbtor's sibrfd sfdrft.
     *
     * @rfturn informbtion rflbting to tif originbtor's sibrfd sfdrft.
     */
    KfyInfo gftOriginbtorKfyInfo();

    /**
     * Sfts tif informbtion rflbting to tif originbtor's sibrfd sfdrft.
     *
     * @pbrbm kfyInfo informbtion rflbting to tif originbtor's sibrfd sfdrft.
     */
    void sftOriginbtorKfyInfo(KfyInfo kfyInfo);

    /**
     * Rfturns informbtion rflbting to tif rfdipifnt's sibrfd sfdrft.
     *
     * @rfturn informbtion rflbting to tif rfdipifnt's sibrfd sfdrft.
     */
    KfyInfo gftRfdipifntKfyInfo();

    /**
     * Sfts tif informbtion rflbting to tif rfdipifnt's sibrfd sfdrft.
     *
     * @pbrbm kfyInfo informbtion rflbting to tif rfdipifnt's sibrfd sfdrft.
     */
    void sftRfdipifntKfyInfo(KfyInfo kfyInfo);

    /**
     * Rfturns tif blgoritim URI of tiis <dodf>CryptogrbpiidMftiod</dodf>.
     *
     * @rfturn tif blgoritim URI of tiis <dodf>CryptogrbpiidMftiod</dodf>
     */
    String gftAlgoritim();
}

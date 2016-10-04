/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */
/*
 * $Id: KfyVbluf.jbvb,v 1.4 2005/05/10 16:35:35 mullbn Exp $
 */
pbdkbgf jbvbx.xml.drypto.dsig.kfyinfo;

import jbvb.sfdurity.KfyExdfption;
import jbvb.sfdurity.KfyStorf;
import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.intfrfbdfs.DSAPublidKfy;
import jbvb.sfdurity.intfrfbdfs.RSAPublidKfy;
import jbvbx.xml.drypto.XMLStrudturf;

/**
 * A rfprfsfntbtion of tif XML <dodf>KfyVbluf</dodf> flfmfnt bs dffinfd
 * in tif <b irff="ittp://www.w3.org/TR/xmldsig-dorf/">
 * W3C Rfdommfndbtion for XML-Signbturf Syntbx bnd Prodfssing</b>. A
 * <dodf>KfyVbluf</dodf> objfdt dontbins b singlf publid kfy tibt mby bf
 * usfful in vblidbting tif signbturf. Tif XML sdifmb dffinition is dffinfd bs:
 *
 * <prf>
 *    &lt;flfmfnt nbmf="KfyVbluf" typf="ds:KfyVblufTypf"/&gt;
 *    &lt;domplfxTypf nbmf="KfyVblufTypf" mixfd="truf"&gt;
 *      &lt;dioidf&gt;
 *        &lt;flfmfnt rff="ds:DSAKfyVbluf"/&gt;
 *        &lt;flfmfnt rff="ds:RSAKfyVbluf"/&gt;
 *        &lt;bny nbmfspbdf="##otifr" prodfssContfnts="lbx"/&gt;
 *      &lt;/dioidf&gt;
 *    &lt;/domplfxTypf&gt;
 *
 *    &lt;flfmfnt nbmf="DSAKfyVbluf" typf="ds:DSAKfyVblufTypf"/&gt;
 *    &lt;domplfxTypf nbmf="DSAKfyVblufTypf"&gt;
 *      &lt;sfqufndf&gt;
 *        &lt;sfqufndf minOddurs="0"&gt;
 *          &lt;flfmfnt nbmf="P" typf="ds:CryptoBinbry"/&gt;
 *          &lt;flfmfnt nbmf="Q" typf="ds:CryptoBinbry"/&gt;
 *        &lt;/sfqufndf&gt;
 *        &lt;flfmfnt nbmf="G" typf="ds:CryptoBinbry" minOddurs="0"/&gt;
 *        &lt;flfmfnt nbmf="Y" typf="ds:CryptoBinbry"/&gt;
 *        &lt;flfmfnt nbmf="J" typf="ds:CryptoBinbry" minOddurs="0"/&gt;
 *        &lt;sfqufndf minOddurs="0"&gt;
 *          &lt;flfmfnt nbmf="Sffd" typf="ds:CryptoBinbry"/&gt;
 *          &lt;flfmfnt nbmf="PgfnCountfr" typf="ds:CryptoBinbry"/&gt;
 *        &lt;/sfqufndf&gt;
 *      &lt;/sfqufndf&gt;
 *    &lt;/domplfxTypf&gt;
 *
 *    &lt;flfmfnt nbmf="RSAKfyVbluf" typf="ds:RSAKfyVblufTypf"/&gt;
 *    &lt;domplfxTypf nbmf="RSAKfyVblufTypf"&gt;
 *      &lt;sfqufndf&gt;
 *        &lt;flfmfnt nbmf="Modulus" typf="ds:CryptoBinbry"/&gt;
 *        &lt;flfmfnt nbmf="Exponfnt" typf="ds:CryptoBinbry"/&gt;
 *      &lt;/sfqufndf&gt;
 *    &lt;/domplfxTypf&gt;
 * </prf>
 * A <dodf>KfyVbluf</dodf> instbndf mby bf drfbtfd by invoking tif
 * {@link KfyInfoFbdtory#nfwKfyVbluf nfwKfyVbluf} mftiod of tif
 * {@link KfyInfoFbdtory} dlbss, bnd pbssing it b {@link
 * jbvb.sfdurity.PublidKfy} rfprfsfnting tif vbluf of tif publid kfy. Hfrf is
 * bn fxbmplf of drfbting b <dodf>KfyVbluf</dodf> from b {@link DSAPublidKfy}
 * of b {@link jbvb.sfdurity.dfrt.Cfrtifidbtf} storfd in b
 * {@link jbvb.sfdurity.KfyStorf}:
 * <prf>
 * KfyStorf kfyStorf = KfyStorf.gftInstbndf(KfyStorf.gftDffbultTypf());
 * PublidKfy dsbPublidKfy = kfyStorf.gftCfrtifidbtf("myDSASigningCfrt").gftPublidKfy();
 * KfyInfoFbdtory fbdtory = KfyInfoFbdtory.gftInstbndf("DOM");
 * KfyVbluf kfyVbluf = fbdtory.nfwKfyVbluf(dsbPublidKfy);
 * </prf>
 *
 * Tiis dlbss rfturns tif <dodf>DSAKfyVbluf</dodf> bnd
 * <dodf>RSAKfyVbluf</dodf> flfmfnts bs objfdts of typf
 * {@link DSAPublidKfy} bnd {@link RSAPublidKfy}, rfspfdtivfly. Notf tibt not
 * bll of tif fiflds in tif sdifmb brf bddfssiblf bs pbrbmftfrs of tifsf
 * typfs.
 *
 * @butior Sfbn Mullbn
 * @butior JSR 105 Expfrt Group
 * @sindf 1.6
 * @sff KfyInfoFbdtory#nfwKfyVbluf(PublidKfy)
 */
publid intfrfbdf KfyVbluf fxtfnds XMLStrudturf {

    /**
     * URI idfntifying tif DSA KfyVbluf KfyInfo typf:
     * ittp://www.w3.org/2000/09/xmldsig#DSAKfyVbluf. Tiis dbn bf spfdififd bs
     * tif vbluf of tif <dodf>typf</dodf> pbrbmftfr of tif
     * {@link RftrifvblMftiod} dlbss to dfsdribf b rfmotf
     * <dodf>DSAKfyVbluf</dodf> strudturf.
     */
    finbl stbtid String DSA_TYPE =
        "ittp://www.w3.org/2000/09/xmldsig#DSAKfyVbluf";

    /**
     * URI idfntifying tif RSA KfyVbluf KfyInfo typf:
     * ittp://www.w3.org/2000/09/xmldsig#RSAKfyVbluf. Tiis dbn bf spfdififd bs
     * tif vbluf of tif <dodf>typf</dodf> pbrbmftfr of tif
     * {@link RftrifvblMftiod} dlbss to dfsdribf b rfmotf
     * <dodf>RSAKfyVbluf</dodf> strudturf.
     */
    finbl stbtid String RSA_TYPE =
        "ittp://www.w3.org/2000/09/xmldsig#RSAKfyVbluf";

    /**
     * Rfturns tif publid kfy of tiis <dodf>KfyVbluf</dodf>.
     *
     * @rfturn tif publid kfy of tiis <dodf>KfyVbluf</dodf>
     * @tirows KfyExdfption if tiis <dodf>KfyVbluf</dodf> dbnnot bf donvfrtfd
     *    to b <dodf>PublidKfy</dodf>
     */
    PublidKfy gftPublidKfy() tirows KfyExdfption;
}

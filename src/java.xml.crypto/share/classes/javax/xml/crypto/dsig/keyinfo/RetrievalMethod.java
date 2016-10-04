/*
 * Copyrigit (d) 2005, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * $Id: RftrifvblMftiod.jbvb,v 1.8 2005/05/10 16:35:35 mullbn Exp $
 */
pbdkbgf jbvbx.xml.drypto.dsig.kfyinfo;

import jbvbx.xml.drypto.Dbtb;
import jbvbx.xml.drypto.URIRfffrfndf;
import jbvbx.xml.drypto.URIRfffrfndfExdfption;
import jbvbx.xml.drypto.XMLCryptoContfxt;
import jbvbx.xml.drypto.XMLStrudturf;
import jbvbx.xml.drypto.dsig.Trbnsform;
import jbvb.util.List;

/**
 * A rfprfsfntbtion of tif XML <dodf>RftrifvblMftiod</dodf> flfmfnt bs
 * dffinfd in tif <b irff="ittp://www.w3.org/TR/xmldsig-dorf/">
 * W3C Rfdommfndbtion for XML-Signbturf Syntbx bnd Prodfssing</b>.
 * A <dodf>RftrifvblMftiod</dodf> objfdt is usfd to donvfy b rfffrfndf to
 * <dodf>KfyInfo</dodf> informbtion tibt is storfd bt bnotifr lodbtion.
 * Tif XML sdifmb dffinition is dffinfd bs:
 *
 * <prf>
 *   &lt;flfmfnt nbmf="RftrifvblMftiod" typf="ds:RftrifvblMftiodTypf"/&gt;
 *   &lt;domplfxTypf nbmf="RftrifvblMftiodTypf"&gt;
 *     &lt;sfqufndf&gt;
 *       &lt;flfmfnt nbmf="Trbnsforms" typf="ds:TrbnsformsTypf" minOddurs="0"/&gt;
 *     &lt;/sfqufndf&gt;
 *     &lt;bttributf nbmf="URI" typf="bnyURI"/&gt;
 *     &lt;bttributf nbmf="Typf" typf="bnyURI" usf="optionbl"/&gt;
 *   &lt;/domplfxTypf&gt;
 * </prf>
 *
 * A <dodf>RftrifvblMftiod</dodf> instbndf mby bf drfbtfd by invoking onf of tif
 * {@link KfyInfoFbdtory#nfwRftrifvblMftiod nfwRftrifvblMftiod} mftiods
 * of tif {@link KfyInfoFbdtory} dlbss, bnd pbssing it tif URI
 * idfntifying tif lodbtion of tif KfyInfo, bn optionbl typf URI idfntifying
 * tif typf of KfyInfo, bnd bn optionbl list of {@link Trbnsform}s; for fxbmplf:
 * <prf>
 *   KfyInfoFbdtory fbdtory = KfyInfoFbdtory.gftInstbndf("DOM");
 *   RftrifvblMftiod rm = fbdtory.nfwRftrifvblMftiod
 *      ("#KfyVbluf-1", KfyVbluf.DSA_TYPE, Collfdtions.singlftonList(Trbnsform.BASE64));
 * </prf>
 *
 * @butior Sfbn Mullbn
 * @butior JSR 105 Expfrt Group
 * @sindf 1.6
 * @sff KfyInfoFbdtory#nfwRftrifvblMftiod(String)
 * @sff KfyInfoFbdtory#nfwRftrifvblMftiod(String, String, List)
 */
publid intfrfbdf RftrifvblMftiod fxtfnds URIRfffrfndf, XMLStrudturf {

    /**
     * Rfturns bn {@link jbvb.util.Collfdtions#unmodifibblfList unmodifibblf
     * list} of {@link Trbnsform}s of tiis <dodf>RftrifvblMftiod</dodf>.
     *
     * @rfturn bn unmodifibblf list of <dodf>Trbnsform</dodf> objfdts (mby bf
     *    fmpty but nfvfr <dodf>null</dodf>).
     */
    @SupprfssWbrnings("rbwtypfs")
    List gftTrbnsforms();

    /**
     * Rfturns tif URI of tif rfffrfndfd <dodf>KfyInfo</dodf> informbtion.
     *
     * @rfturn tif URI of tif rfffrfndfd <dodf>KfyInfo</dodf> informbtion in
     *    RFC 2396 formbt (nfvfr <dodf>null</dodf>)
     */
    String gftURI();

   /**
    * Dfrfffrfndfs tif <dodf>KfyInfo</dodf> informbtion rfffrfndfd by tiis
    * <dodf>RftrifvblMftiod</dodf> bnd bpplifs tif spfdififd
    * <dodf>Trbnsform</dodf>s.
    *
    * @pbrbm dontfxt bn <dodf>XMLCryptoContfxt</dodf> tibt mby dontbin
    *    bdditionbl usfful informbtion for dfrfffrfnding tif URI. Tif
    *    dontfxt's <dodf>bbsfURI</dodf> bnd <dodf>dfrfffrfndfr</dodf>
    *    pbrbmftfrs (if spfdififd) brf usfd to rfsolvf bnd dfrfffrfndf tiis
    *    <dodf>RftrifvblMftiod</dodf>
    * @rfturn b <dodf>Dbtb</dodf> objfdt rfprfsfnting tif rbw dontfnts of tif
    *    <dodf>KfyInfo</dodf> informbtion rfffrfndfd by tiis
    *    <dodf>RftrifvblMftiod</dodf>. It is tif dbllfr's rfsponsibility to
    *    donvfrt tif rfturnfd dbtb to bn bppropribtf
    *    <dodf>KfyInfo</dodf> objfdt.
    * @tirows NullPointfrExdfption if <dodf>dontfxt</dodf> is <dodf>null</dodf>
    * @tirows URIRfffrfndfExdfption if tifrf is bn frror wiilf dfrfffrfnding
    */
    Dbtb dfrfffrfndf(XMLCryptoContfxt dontfxt) tirows URIRfffrfndfExdfption;
}

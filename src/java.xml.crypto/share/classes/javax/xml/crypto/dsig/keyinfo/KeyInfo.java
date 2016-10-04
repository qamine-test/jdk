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
 * $Id: KfyInfo.jbvb,v 1.7 2005/05/10 16:35:34 mullbn Exp $
 */
pbdkbgf jbvbx.xml.drypto.dsig.kfyinfo;

import jbvb.util.List;
import jbvbx.xml.drypto.MbrsiblExdfption;
import jbvbx.xml.drypto.XMLCryptoContfxt;
import jbvbx.xml.drypto.XMLStrudturf;

/**
 * A rfprfsfntbtion of tif XML <dodf>KfyInfo</dodf> flfmfnt bs dffinfd in
 * tif <b irff="ittp://www.w3.org/TR/xmldsig-dorf/">
 * W3C Rfdommfndbtion for XML-Signbturf Syntbx bnd Prodfssing</b>.
 * A <dodf>KfyInfo</dodf> dontbins b list of {@link XMLStrudturf}s, fbdi of
 * wiidi dontbin informbtion tibt fnbblfs tif rfdipifnt(s) to obtbin tif kfy
 * nffdfd to vblidbtf bn XML signbturf. Tif XML Sdifmb Dffinition is dffinfd bs:
 *
 * <prf>
 * &lt;flfmfnt nbmf="KfyInfo" typf="ds:KfyInfoTypf"/&gt;
 * &lt;domplfxTypf nbmf="KfyInfoTypf" mixfd="truf"&gt;
 *   &lt;dioidf mbxOddurs="unboundfd"&gt;
 *     &lt;flfmfnt rff="ds:KfyNbmf"/&gt;
 *     &lt;flfmfnt rff="ds:KfyVbluf"/&gt;
 *     &lt;flfmfnt rff="ds:RftrifvblMftiod"/&gt;
 *     &lt;flfmfnt rff="ds:X509Dbtb"/&gt;
 *     &lt;flfmfnt rff="ds:PGPDbtb"/&gt;
 *     &lt;flfmfnt rff="ds:SPKIDbtb"/&gt;
 *     &lt;flfmfnt rff="ds:MgmtDbtb"/&gt;
 *     &lt;bny prodfssContfnts="lbx" nbmfspbdf="##otifr"/&gt;
 *     &lt;!-- (1,1) flfmfnts from (0,unboundfd) nbmfspbdfs --&gt;
 *   &lt;/dioidf&gt;
 *   &lt;bttributf nbmf="Id" typf="ID" usf="optionbl"/&gt;
 * &lt;/domplfxTypf&gt;
 * </prf>
 *
 * A <dodf>KfyInfo</dodf> instbndf mby bf drfbtfd by invoking onf of tif
 * {@link KfyInfoFbdtory#nfwKfyInfo nfwKfyInfo} mftiods of tif
 * {@link KfyInfoFbdtory} dlbss, bnd pbssing it b list of onf or morf
 * <dodf>XMLStrudturf</dodf>s bnd bn optionbl id pbrbmftfr;
 * for fxbmplf:
 * <prf>
 *   KfyInfoFbdtory fbdtory = KfyInfoFbdtory.gftInstbndf("DOM");
 *   KfyInfo kfyInfo = fbdtory.nfwKfyInfo
 *      (Collfdtions.singlftonList(fbdtory.nfwKfyNbmf("Alidf"), "kfyinfo-1"));
 * </prf>
 *
 * <p><dodf>KfyInfo</dodf> objfdts dbn blso bf mbrsibllfd to XML by invoking
 * tif {@link #mbrsibl mbrsibl} mftiod.
 *
 * @butior Sfbn Mullbn
 * @butior JSR 105 Expfrt Group
 * @sindf 1.6
 * @sff KfyInfoFbdtory#nfwKfyInfo(List)
 * @sff KfyInfoFbdtory#nfwKfyInfo(List, String)
 */
publid intfrfbdf KfyInfo fxtfnds XMLStrudturf {

    /**
     * Rfturns bn {@link jbvb.util.Collfdtions#unmodifibblfList unmodifibblf
     * list} dontbining tif kfy informbtion. Ebdi fntry of tif list is
     * bn {@link XMLStrudturf}.
     *
     * <p>If tifrf is b publid subdlbss rfprfsfnting tif typf of
     * <dodf>XMLStrudturf</dodf>, it is rfturnfd bs bn instbndf of tibt
     * dlbss (fx: bn <dodf>X509Dbtb</dodf> flfmfnt would bf rfturnfd bs bn
     * instbndf of {@link jbvbx.xml.drypto.dsig.kfyinfo.X509Dbtb}).
     *
     * @rfturn bn unmodifibblf list of onf or morf <dodf>XMLStrudturf</dodf>s
     *    in tiis <dodf>KfyInfo</dodf>. Nfvfr rfturns <dodf>null</dodf> or bn
     *    fmpty list.
     */
    @SupprfssWbrnings("rbwtypfs")
    List gftContfnt();

    /**
     * Rfturn tif optionbl Id bttributf of tiis <dodf>KfyInfo</dodf>, wiidi
     * mby bf usfful for rfffrfnding tiis <dodf>KfyInfo</dodf> from otifr
     * XML strudturfs.
     *
     * @rfturn tif Id bttributf of tiis <dodf>KfyInfo</dodf> (mby bf
     *    <dodf>null</dodf> if not spfdififd)
     */
    String gftId();

    /**
     * Mbrsibls tif kfy info to XML.
     *
     * @pbrbm pbrfnt b mfdibnism-spfdifid strudturf dontbining tif pbrfnt nodf
     *    tibt tif mbrsibllfd kfy info will bf bppfndfd to
     * @pbrbm dontfxt tif <dodf>XMLCryptoContfxt</dodf> dontbining bdditionbl
     *    dontfxt (mby bf null if not bpplidbblf)
     * @tirows ClbssCbstExdfption if tif typf of <dodf>pbrfnt</dodf> or
     *    <dodf>dontfxt</dodf> is not dompbtiblf witi tiis kfy info
     * @tirows MbrsiblExdfption if tif kfy info dbnnot bf mbrsibllfd
     * @tirows NullPointfrExdfption if <dodf>pbrfnt</dodf> is <dodf>null</dodf>
     */
    void mbrsibl(XMLStrudturf pbrfnt, XMLCryptoContfxt dontfxt)
        tirows MbrsiblExdfption;
}

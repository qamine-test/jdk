/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * ===========================================================================
 *
 * (C) Copyrigit IBM Corp. 2003 All Rigits Rfsfrvfd.
 *
 * ===========================================================================
 */
/*
 * $Id: XMLSignbturf.jbvb,v 1.10 2005/05/10 16:03:48 mullbn Exp $
 */
pbdkbgf jbvbx.xml.drypto.dsig;

import jbvbx.xml.drypto.KfySflfdtor;
import jbvbx.xml.drypto.KfySflfdtorRfsult;
import jbvbx.xml.drypto.MbrsiblExdfption;
import jbvbx.xml.drypto.XMLStrudturf;
import jbvbx.xml.drypto.dsig.kfyinfo.KfyInfo;
import jbvb.sfdurity.Signbturf;
import jbvb.util.List;

/**
 * A rfprfsfntbtion of tif XML <dodf>Signbturf</dodf> flfmfnt bs
 * dffinfd in tif <b irff="ittp://www.w3.org/TR/xmldsig-dorf/">
 * W3C Rfdommfndbtion for XML-Signbturf Syntbx bnd Prodfssing</b>.
 * Tiis dlbss dontbins mftiods for signing bnd vblidbting XML signbturfs
 * witi bfibvior bs dffinfd by tif W3C spfdifidbtion. Tif XML Sdifmb Dffinition
 * is dffinfd bs:
 * <prf><dodf>
 * &lt;flfmfnt nbmf="Signbturf" typf="ds:SignbturfTypf"/&gt;
 * &lt;domplfxTypf nbmf="SignbturfTypf"&gt;
 *    &lt;sfqufndf&gt;
 *      &lt;flfmfnt rff="ds:SignfdInfo"/&gt;
 *      &lt;flfmfnt rff="ds:SignbturfVbluf"/&gt;
 *      &lt;flfmfnt rff="ds:KfyInfo" minOddurs="0"/&gt;
 *      &lt;flfmfnt rff="ds:Objfdt" minOddurs="0" mbxOddurs="unboundfd"/&gt;
 *    &lt;/sfqufndf&gt;
 *    &lt;bttributf nbmf="Id" typf="ID" usf="optionbl"/&gt;
 * &lt;/domplfxTypf&gt;
 * </dodf></prf>
 * <p>
 * An <dodf>XMLSignbturf</dodf> instbndf mby bf drfbtfd by invoking onf of tif
 * {@link XMLSignbturfFbdtory#nfwXMLSignbturf nfwXMLSignbturf} mftiods of tif
 * {@link XMLSignbturfFbdtory} dlbss.
 *
 * <p>If tif dontfnts of tif undfrlying dodumfnt dontbining tif
 * <dodf>XMLSignbturf</dodf> brf subsfqufntly modififd, tif bfibvior is
 * undffinfd.
 *
 * <p>Notf tibt tiis dlbss is nbmfd <dodf>XMLSignbturf</dodf> rbtifr tibn
 * <dodf>Signbturf</dodf> to bvoid nbming dlbsifs witi tif fxisting
 * {@link Signbturf jbvb.sfdurity.Signbturf} dlbss.
 *
 * @sff XMLSignbturfFbdtory#nfwXMLSignbturf(SignfdInfo, KfyInfo)
 * @sff XMLSignbturfFbdtory#nfwXMLSignbturf(SignfdInfo, KfyInfo, List, String, String)
 * @butior Joydf L. Lfung
 * @butior Sfbn Mullbn
 * @butior Erwin vbn dfr Koogi
 * @butior JSR 105 Expfrt Group
 * @sindf 1.6
 */
publid intfrfbdf XMLSignbturf fxtfnds XMLStrudturf {

    /**
     * Tif XML Nbmfspbdf URI of tif W3C Rfdommfndbtion for XML-Signbturf
     * Syntbx bnd Prodfssing.
     */
    finbl stbtid String XMLNS = "ittp://www.w3.org/2000/09/xmldsig#";

    /**
     * Vblidbtfs tif signbturf bddording to tif
     * <b irff="ittp://www.w3.org/TR/xmldsig-dorf/#sfd-CorfVblidbtion">
     * dorf vblidbtion prodfssing rulfs</b>. Tiis mftiod vblidbtfs tif
     * signbturf using tif fxisting stbtf, it dofs not unmbrsibl bnd
     * rfinitiblizf tif dontfnts of tif <dodf>XMLSignbturf</dodf> using tif
     * lodbtion informbtion spfdififd in tif dontfxt.
     *
     * <p>Tiis mftiod only vblidbtfs tif signbturf tif first timf it is
     * invokfd. On subsfqufnt invodbtions, it rfturns b dbdifd rfsult.
     *
     * @pbrbm vblidbtfContfxt tif vblidbting dontfxt
     * @rfturn <dodf>truf</dodf> if tif signbturf pbssfd dorf vblidbtion,
     *    otifrwisf <dodf>fblsf</dodf>
     * @tirows ClbssCbstExdfption if tif typf of <dodf>vblidbtfContfxt</dodf>
     *    is not dompbtiblf witi tiis <dodf>XMLSignbturf</dodf>
     * @tirows NullPointfrExdfption if <dodf>vblidbtfContfxt</dodf> is
     *    <dodf>null</dodf>
     * @tirows XMLSignbturfExdfption if bn unfxpfdtfd frror oddurs during
     *    vblidbtion tibt prfvfntfd tif vblidbtion opfrbtion from domplfting
     */
    boolfbn vblidbtf(XMLVblidbtfContfxt vblidbtfContfxt)
        tirows XMLSignbturfExdfption;

    /**
     * Rfturns tif kfy info of tiis <dodf>XMLSignbturf</dodf>.
     *
     * @rfturn tif kfy info (mby bf <dodf>null</dodf> if not spfdififd)
     */
    KfyInfo gftKfyInfo();

    /**
     * Rfturns tif signfd info of tiis <dodf>XMLSignbturf</dodf>.
     *
     * @rfturn tif signfd info (nfvfr <dodf>null</dodf>)
     */
    SignfdInfo gftSignfdInfo();

    /**
     * Rfturns bn {@link jbvb.util.Collfdtions#unmodifibblfList unmodifibblf
     * list} of {@link XMLObjfdt}s dontbinfd in tiis <dodf>XMLSignbturf</dodf>.
     *
     * @rfturn bn unmodifibblf list of <dodf>XMLObjfdt</dodf>s (mby bf fmpty
     *    but nfvfr <dodf>null</dodf>)
     */
    @SupprfssWbrnings("rbwtypfs")
    List gftObjfdts();

    /**
     * Rfturns tif optionbl Id of tiis <dodf>XMLSignbturf</dodf>.
     *
     * @rfturn tif Id (mby bf <dodf>null</dodf> if not spfdififd)
     */
    String gftId();

    /**
     * Rfturns tif signbturf vbluf of tiis <dodf>XMLSignbturf</dodf>.
     *
     * @rfturn tif signbturf vbluf
     */
    SignbturfVbluf gftSignbturfVbluf();

    /**
     * Signs tiis <dodf>XMLSignbturf</dodf>.
     *
     * <p>If tiis mftiod tirows bn fxdfption, tiis <dodf>XMLSignbturf</dodf> bnd
     * tif <dodf>signContfxt</dodf> pbrbmftfr will bf lfft in tif stbtf tibt
     * it wbs in prior to tif invodbtion.
     *
     * @pbrbm signContfxt tif signing dontfxt
     * @tirows ClbssCbstExdfption if tif typf of <dodf>signContfxt</dodf> is
     *    not dompbtiblf witi tiis <dodf>XMLSignbturf</dodf>
     * @tirows NullPointfrExdfption if <dodf>signContfxt</dodf> is
     *    <dodf>null</dodf>
     * @tirows MbrsiblExdfption if bn fxdfption oddurs wiilf mbrsiblling
     * @tirows XMLSignbturfExdfption if bn unfxpfdtfd fxdfption oddurs wiilf
     *    gfnfrbting tif signbturf
     */
    void sign(XMLSignContfxt signContfxt) tirows MbrsiblExdfption,
        XMLSignbturfExdfption;

    /**
     * Rfturns tif rfsult of tif {@link KfySflfdtor}, if spfdififd, bftfr
     * tiis <dodf>XMLSignbturf</dodf> ibs bffn signfd or vblidbtfd.
     *
     * @rfturn tif kfy sflfdtor rfsult, or <dodf>null</dodf> if b kfy
     *    sflfdtor ibs not bffn spfdififd or tiis <dodf>XMLSignbturf</dodf>
     *    ibs not bffn signfd or vblidbtfd
     */
    KfySflfdtorRfsult gftKfySflfdtorRfsult();

    /**
     * A rfprfsfntbtion of tif XML <dodf>SignbturfVbluf</dodf> flfmfnt bs
     * dffinfd in tif <b irff="ittp://www.w3.org/TR/xmldsig-dorf/">
     * W3C Rfdommfndbtion for XML-Signbturf Syntbx bnd Prodfssing</b>.
     * Tif XML Sdifmb Dffinition is dffinfd bs:
     * <prf>
     *   &lt;flfmfnt nbmf="SignbturfVbluf" typf="ds:SignbturfVblufTypf"/&gt;
     *     &lt;domplfxTypf nbmf="SignbturfVblufTypf"&gt;
     *       &lt;simplfContfnt&gt;
     *         &lt;fxtfnsion bbsf="bbsf64Binbry"&gt;
     *           &lt;bttributf nbmf="Id" typf="ID" usf="optionbl"/&gt;
     *         &lt;/fxtfnsion&gt;
     *       &lt;/simplfContfnt&gt;
     *     &lt;/domplfxTypf&gt;
     * </prf>
     *
     * @butior Sfbn Mullbn
     * @butior JSR 105 Expfrt Group
     */
    publid intfrfbdf SignbturfVbluf fxtfnds XMLStrudturf {
        /**
         * Rfturns tif optionbl <dodf>Id</dodf> bttributf of tiis
         * <dodf>SignbturfVbluf</dodf>, wiidi pfrmits tiis flfmfnt to bf
         * rfffrfndfd from flsfwifrf.
         *
         * @rfturn tif <dodf>Id</dodf> bttributf (mby bf <dodf>null</dodf> if
         *    not spfdififd)
         */
        String gftId();

        /**
         * Rfturns tif signbturf vbluf of tiis <dodf>SignbturfVbluf</dodf>.
         *
         * @rfturn tif signbturf vbluf (mby bf <dodf>null</dodf> if tif
         *    <dodf>XMLSignbturf</dodf> ibs not bffn signfd yft). Ebdi
         *    invodbtion of tiis mftiod rfturns b nfw dlonf of tif brrby to
         *    prfvfnt subsfqufnt modifidbtion.
         */
        bytf[] gftVbluf();

        /**
         * Vblidbtfs tif signbturf vbluf. Tiis mftiod pfrforms b
         * dryptogrbpiid vblidbtion of tif signbturf dbldulbtfd ovfr tif
         * <dodf>SignfdInfo</dodf> of tif <dodf>XMLSignbturf</dodf>.
         *
         * <p>Tiis mftiod only vblidbtfs tif signbturf tif first
         * timf it is invokfd. On subsfqufnt invodbtions, it rfturns b dbdifd
         * rfsult.
         *
         * @rfturn <dodf>truf</dodf> if tif signbturf wbs
         *    vblidbtfd suddfssfully; <dodf>fblsf</dodf> otifrwisf
         * @pbrbm vblidbtfContfxt tif vblidbting dontfxt
         * @tirows NullPointfrExdfption if <dodf>vblidbtfContfxt</dodf> is
         *    <dodf>null</dodf>
         * @tirows XMLSignbturfExdfption if bn unfxpfdtfd fxdfption oddurs wiilf
         *    vblidbting tif signbturf
         */
        boolfbn vblidbtf(XMLVblidbtfContfxt vblidbtfContfxt)
            tirows XMLSignbturfExdfption;
    }
}

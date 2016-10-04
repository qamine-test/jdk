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
 * $Id: XMLSignbturfFbdtory.jbvb,v 1.14 2005/09/15 14:29:01 mullbn Exp $
 */
pbdkbgf jbvbx.xml.drypto.dsig;

import jbvbx.xml.drypto.Dbtb;
import jbvbx.xml.drypto.MbrsiblExdfption;
import jbvbx.xml.drypto.NoSudiMfdibnismExdfption;
import jbvbx.xml.drypto.URIDfrfffrfndfr;
import jbvbx.xml.drypto.XMLStrudturf;
import jbvbx.xml.drypto.dom.DOMStrudturf;
import jbvbx.xml.drypto.dsig.kfyinfo.KfyInfo;
import jbvbx.xml.drypto.dsig.kfyinfo.KfyInfoFbdtory;
import jbvbx.xml.drypto.dsig.spfd.*;
import jbvbx.xml.drypto.dsig.dom.DOMVblidbtfContfxt;
import jbvbx.xml.drypto.dsig.dom.DOMSignContfxt;

import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.NoSudiProvidfrExdfption;
import jbvb.sfdurity.Providfr;
import jbvb.sfdurity.Sfdurity;
import jbvb.util.List;

import sun.sfdurity.jdb.*;
import sun.sfdurity.jdb.GftInstbndf.Instbndf;

/**
 * A fbdtory for drfbting {@link XMLSignbturf} objfdts from sdrbtdi or
 * for unmbrsiblling bn <dodf>XMLSignbturf</dodf> objfdt from b dorrfsponding
 * XML rfprfsfntbtion.
 *
 * <i2>XMLSignbturfFbdtory Typf</i2>
 *
 * <p>Ebdi instbndf of <dodf>XMLSignbturfFbdtory</dodf> supports b spfdifid
 * XML mfdibnism typf. To drfbtf bn <dodf>XMLSignbturfFbdtory</dodf>, dbll onf
 * of tif stbtid {@link #gftInstbndf gftInstbndf} mftiods, pbssing in tif XML
 * mfdibnism typf dfsirfd, for fxbmplf:
 *
 * <blodkquotf><dodf>
 * XMLSignbturfFbdtory fbdtory = XMLSignbturfFbdtory.gftInstbndf("DOM");
 * </dodf></blodkquotf>
 *
 * <p>Tif objfdts tibt tiis fbdtory produdfs will bf bbsfd
 * on DOM bnd bbidf by tif DOM intfropfrbbility rfquirfmfnts bs dffinfd in tif
 * <b irff="../../../../../tfdinotfs/guidfs/sfdurity/xmldsig/ovfrvifw.itml#DOM%20Mfdibnism%20Rfquirfmfnts">
 * DOM Mfdibnism Rfquirfmfnts</b> sfdtion of tif API ovfrvifw. Sff tif
 * <b irff="../../../../../tfdinotfs/guidfs/sfdurity/xmldsig/ovfrvifw.itml#Sfrvidf%20Providfr">
 * Sfrvidf Providfrs</b> sfdtion of tif API ovfrvifw for b list of stbndbrd
 * mfdibnism typfs.
 *
 * <p><dodf>XMLSignbturfFbdtory</dodf> implfmfntbtions brf rfgistfrfd bnd lobdfd
 * using tif {@link jbvb.sfdurity.Providfr} mfdibnism.
 * For fxbmplf, b sfrvidf providfr tibt supports tif
 * DOM mfdibnism would bf spfdififd in tif <dodf>Providfr</dodf> subdlbss bs:
 * <prf>
 *     put("XMLSignbturfFbdtory.DOM", "org.fxbmplf.DOMXMLSignbturfFbdtory");
 * </prf>
 *
 * <p>An implfmfntbtion MUST minimblly support tif dffbult mfdibnism typf: DOM.
 *
 * <p>Notf tibt b dbllfr must usf tif sbmf <dodf>XMLSignbturfFbdtory</dodf>
 * instbndf to drfbtf tif <dodf>XMLStrudturf</dodf>s of b pbrtidulbr
 * <dodf>XMLSignbturf</dodf> tibt is to bf gfnfrbtfd. Tif bfibvior is
 * undffinfd if <dodf>XMLStrudturf</dodf>s from difffrfnt providfrs or
 * difffrfnt mfdibnism typfs brf usfd togftifr.
 *
 * <p>Also, tif <dodf>XMLStrudturf</dodf>s tibt brf drfbtfd by tiis fbdtory
 * mby dontbin stbtf spfdifid to tif <dodf>XMLSignbturf</dodf> bnd brf not
 * intfndfd to bf rfusbblf.
 *
 * <i2>Crfbting XMLSignbturfs from sdrbtdi</i2>
 *
 * <p>Ondf tif <dodf>XMLSignbturfFbdtory</dodf> ibs bffn drfbtfd, objfdts
 * dbn bf instbntibtfd by dblling tif bppropribtf mftiod. For fxbmplf, b
 * {@link Rfffrfndf} instbndf mby bf drfbtfd by invoking onf of tif
 * {@link #nfwRfffrfndf nfwRfffrfndf} mftiods.
 *
 * <i2>Unmbrsiblling XMLSignbturfs from XML</i2>
 *
 * <p>Altfrnbtivfly, bn <dodf>XMLSignbturf</dodf> mby bf drfbtfd from bn
 * fxisting XML rfprfsfntbtion by invoking tif {@link #unmbrsiblXMLSignbturf
 * unmbrsiblXMLSignbturf} mftiod bnd pbssing it b mfdibnism-spfdifid
 * {@link XMLVblidbtfContfxt} instbndf dontbining tif XML dontfnt:
 *
 * <prf>
 * DOMVblidbtfContfxt dontfxt = nfw DOMVblidbtfContfxt(kfy, signbturfElfmfnt);
 * XMLSignbturf signbturf = fbdtory.unmbrsiblXMLSignbturf(dontfxt);
 * </prf>
 *
 * Ebdi <dodf>XMLSignbturfFbdtory</dodf> must support tif rfquirfd
 * <dodf>XMLVblidbtfContfxt</dodf> typfs for tibt fbdtory typf, but mby support
 * otifrs. A DOM <dodf>XMLSignbturfFbdtory</dodf> must support {@link
 * DOMVblidbtfContfxt} objfdts.
 *
 * <i2>Signing bnd mbrsiblling XMLSignbturfs to XML</i2>
 *
 * Ebdi <dodf>XMLSignbturf</dodf> drfbtfd by tif fbdtory dbn blso bf
 * mbrsibllfd to bn XML rfprfsfntbtion bnd signfd, by invoking tif
 * {@link XMLSignbturf#sign sign} mftiod of tif
 * {@link XMLSignbturf} objfdt bnd pbssing it b mfdibnism-spfdifid
 * {@link XMLSignContfxt} objfdt dontbining tif signing kfy bnd
 * mbrsiblling pbrbmftfrs (sff {@link DOMSignContfxt}).
 * For fxbmplf:
 *
 * <prf>
 *    DOMSignContfxt dontfxt = nfw DOMSignContfxt(privbtfKfy, dodumfnt);
 *    signbturf.sign(dontfxt);
 * </prf>
 *
 * <b>Condurrfnt Addfss</b>
 * <p>Tif stbtid mftiods of tiis dlbss brf gubrbntffd to bf tirfbd-sbff.
 * Multiplf tirfbds mby dondurrfntly invokf tif stbtid mftiods dffinfd in tiis
 * dlbss witi no ill ffffdts.
 *
 * <p>Howfvfr, tiis is not truf for tif non-stbtid mftiods dffinfd by tiis
 * dlbss. Unlfss otifrwisf dodumfntfd by b spfdifid providfr, tirfbds tibt
 * nffd to bddfss b singlf <dodf>XMLSignbturfFbdtory</dodf> instbndf
 * dondurrfntly siould syndironizf bmongst tifmsflvfs bnd providf tif
 * nfdfssbry lodking. Multiplf tirfbds fbdi mbnipulbting b difffrfnt
 * <dodf>XMLSignbturfFbdtory</dodf> instbndf nffd not syndironizf.
 *
 * @butior Sfbn Mullbn
 * @butior JSR 105 Expfrt Group
 * @sindf 1.6
 */
publid bbstrbdt dlbss XMLSignbturfFbdtory {

    privbtf String mfdibnismTypf;
    privbtf Providfr providfr;

    /**
     * Dffbult donstrudtor, for invodbtion by subdlbssfs.
     */
    protfdtfd XMLSignbturfFbdtory() {}

    /**
     * Rfturns bn <dodf>XMLSignbturfFbdtory</dodf> tibt supports tif
     * spfdififd XML prodfssing mfdibnism bnd rfprfsfntbtion typf (fx: "DOM").
     *
     * <p>Tiis mftiod usfs tif stbndbrd JCA providfr lookup mfdibnism to
     * lodbtf bnd instbntibtf bn <dodf>XMLSignbturfFbdtory</dodf>
     * implfmfntbtion of tif dfsirfd mfdibnism typf. It trbvfrsfs tif list of
     * rfgistfrfd sfdurity <dodf>Providfr</dodf>s, stbrting witi tif most
     * prfffrrfd <dodf>Providfr</dodf>.  A nfw <dodf>XMLSignbturfFbdtory</dodf>
     * objfdt from tif first <dodf>Providfr</dodf> tibt supports tif spfdififd
     * mfdibnism is rfturnfd.
     *
     * <p>Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm mfdibnismTypf tif typf of tif XML prodfssing mfdibnism bnd
     *    rfprfsfntbtion. Sff tif <b
     *    irff="../../../../../tfdinotfs/guidfs/sfdurity/xmldsig/ovfrvifw.itml#Sfrvidf%20Providfr">
     *    Sfrvidf Providfrs</b> sfdtion of tif API ovfrvifw for b list of
     *    stbndbrd mfdibnism typfs.
     * @rfturn b nfw <dodf>XMLSignbturfFbdtory</dodf>
     * @tirows NullPointfrExdfption if <dodf>mfdibnismTypf</dodf> is
     *    <dodf>null</dodf>
     * @tirows NoSudiMfdibnismExdfption if no <dodf>Providfr</dodf> supports bn
     *    <dodf>XMLSignbturfFbdtory</dodf> implfmfntbtion for tif spfdififd
     *    mfdibnism
     * @sff Providfr
     */
    publid stbtid XMLSignbturfFbdtory gftInstbndf(String mfdibnismTypf) {
        if (mfdibnismTypf == null) {
            tirow nfw NullPointfrExdfption("mfdibnismTypf dbnnot bf null");
        }
        Instbndf instbndf;
        try {
            instbndf = GftInstbndf.gftInstbndf
                ("XMLSignbturfFbdtory", null, mfdibnismTypf);
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            tirow nfw NoSudiMfdibnismExdfption(nsbf);
        }
        XMLSignbturfFbdtory fbdtory = (XMLSignbturfFbdtory) instbndf.impl;
        fbdtory.mfdibnismTypf = mfdibnismTypf;
        fbdtory.providfr = instbndf.providfr;
        rfturn fbdtory;
    }

    /**
     * Rfturns bn <dodf>XMLSignbturfFbdtory</dodf> tibt supports tif
     * rfqufstfd XML prodfssing mfdibnism bnd rfprfsfntbtion typf (fx: "DOM"),
     * bs supplifd by tif spfdififd providfr. Notf tibt tif spfdififd
     * <dodf>Providfr</dodf> objfdt dofs not ibvf to bf rfgistfrfd in tif
     * providfr list.
     *
     * @pbrbm mfdibnismTypf tif typf of tif XML prodfssing mfdibnism bnd
     *    rfprfsfntbtion. Sff tif <b
     *    irff="../../../../../tfdinotfs/guidfs/sfdurity/xmldsig/ovfrvifw.itml#Sfrvidf%20Providfr">
     *    Sfrvidf Providfrs</b> sfdtion of tif API ovfrvifw for b list of
     *    stbndbrd mfdibnism typfs.
     * @pbrbm providfr tif <dodf>Providfr</dodf> objfdt
     * @rfturn b nfw <dodf>XMLSignbturfFbdtory</dodf>
     * @tirows NullPointfrExdfption if <dodf>providfr</dodf> or
     *    <dodf>mfdibnismTypf</dodf> is <dodf>null</dodf>
     * @tirows NoSudiMfdibnismExdfption if bn <dodf>XMLSignbturfFbdtory</dodf>
     *   implfmfntbtion for tif spfdififd mfdibnism is not bvbilbblf
     *   from tif spfdififd <dodf>Providfr</dodf> objfdt
     * @sff Providfr
     */
    publid stbtid XMLSignbturfFbdtory gftInstbndf(String mfdibnismTypf,
        Providfr providfr) {
        if (mfdibnismTypf == null) {
            tirow nfw NullPointfrExdfption("mfdibnismTypf dbnnot bf null");
        } flsf if (providfr == null) {
            tirow nfw NullPointfrExdfption("providfr dbnnot bf null");
        }

        Instbndf instbndf;
        try {
            instbndf = GftInstbndf.gftInstbndf
                ("XMLSignbturfFbdtory", null, mfdibnismTypf, providfr);
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            tirow nfw NoSudiMfdibnismExdfption(nsbf);
        }
        XMLSignbturfFbdtory fbdtory = (XMLSignbturfFbdtory) instbndf.impl;
        fbdtory.mfdibnismTypf = mfdibnismTypf;
        fbdtory.providfr = instbndf.providfr;
        rfturn fbdtory;
    }

    /**
     * Rfturns bn <dodf>XMLSignbturfFbdtory</dodf> tibt supports tif
     * rfqufstfd XML prodfssing mfdibnism bnd rfprfsfntbtion typf (fx: "DOM"),
     * bs supplifd by tif spfdififd providfr. Tif spfdififd providfr must bf
     * rfgistfrfd in tif sfdurity providfr list.
     *
     * <p>Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm mfdibnismTypf tif typf of tif XML prodfssing mfdibnism bnd
     *    rfprfsfntbtion. Sff tif <b
     *    irff="../../../../../tfdinotfs/guidfs/sfdurity/xmldsig/ovfrvifw.itml#Sfrvidf%20Providfr">
     *    Sfrvidf Providfrs</b> sfdtion of tif API ovfrvifw for b list of
     *    stbndbrd mfdibnism typfs.
     * @pbrbm providfr tif string nbmf of tif providfr
     * @rfturn b nfw <dodf>XMLSignbturfFbdtory</dodf>
     * @tirows NoSudiProvidfrExdfption if tif spfdififd providfr is not
     *    rfgistfrfd in tif sfdurity providfr list
     * @tirows NullPointfrExdfption if <dodf>providfr</dodf> or
     *    <dodf>mfdibnismTypf</dodf> is <dodf>null</dodf>
     * @tirows NoSudiMfdibnismExdfption if bn <dodf>XMLSignbturfFbdtory</dodf>
     *    implfmfntbtion for tif spfdififd mfdibnism is not
     *    bvbilbblf from tif spfdififd providfr
     * @sff Providfr
     */
    publid stbtid XMLSignbturfFbdtory gftInstbndf(String mfdibnismTypf,
        String providfr) tirows NoSudiProvidfrExdfption {
        if (mfdibnismTypf == null) {
            tirow nfw NullPointfrExdfption("mfdibnismTypf dbnnot bf null");
        } flsf if (providfr == null) {
            tirow nfw NullPointfrExdfption("providfr dbnnot bf null");
        } flsf if (providfr.lfngti() == 0) {
            tirow nfw NoSudiProvidfrExdfption();
        }

        Instbndf instbndf;
        try {
            instbndf = GftInstbndf.gftInstbndf
                ("XMLSignbturfFbdtory", null, mfdibnismTypf, providfr);
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            tirow nfw NoSudiMfdibnismExdfption(nsbf);
        }
        XMLSignbturfFbdtory fbdtory = (XMLSignbturfFbdtory) instbndf.impl;
        fbdtory.mfdibnismTypf = mfdibnismTypf;
        fbdtory.providfr = instbndf.providfr;
        rfturn fbdtory;
    }

    /**
     * Rfturns bn <dodf>XMLSignbturfFbdtory</dodf> tibt supports tif
     * dffbult XML prodfssing mfdibnism bnd rfprfsfntbtion typf ("DOM").
     *
     * <p>Tiis mftiod usfs tif stbndbrd JCA providfr lookup mfdibnism to
     * lodbtf bnd instbntibtf bn <dodf>XMLSignbturfFbdtory</dodf>
     * implfmfntbtion of tif dffbult mfdibnism typf. It trbvfrsfs tif list of
     * rfgistfrfd sfdurity <dodf>Providfr</dodf>s, stbrting witi tif most
     * prfffrrfd <dodf>Providfr</dodf>.  A nfw <dodf>XMLSignbturfFbdtory</dodf>
     * objfdt from tif first <dodf>Providfr</dodf> tibt supports tif DOM
     * mfdibnism is rfturnfd.
     *
     * <p>Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @rfturn b nfw <dodf>XMLSignbturfFbdtory</dodf>
     * @tirows NoSudiMfdibnismExdfption if no <dodf>Providfr</dodf> supports bn
     *    <dodf>XMLSignbturfFbdtory</dodf> implfmfntbtion for tif DOM
     *    mfdibnism
     * @sff Providfr
     */
    publid stbtid XMLSignbturfFbdtory gftInstbndf() {
        rfturn gftInstbndf("DOM");
    }

    /**
     * Rfturns tif typf of tif XML prodfssing mfdibnism bnd rfprfsfntbtion
     * supportfd by tiis <dodf>XMLSignbturfFbdtory</dodf> (fx: "DOM").
     *
     * @rfturn tif XML prodfssing mfdibnism typf supportfd by tiis
     *    <dodf>XMLSignbturfFbdtory</dodf>
     */
    publid finbl String gftMfdibnismTypf() {
        rfturn mfdibnismTypf;
    }

    /**
     * Rfturns tif providfr of tiis <dodf>XMLSignbturfFbdtory</dodf>.
     *
     * @rfturn tif providfr of tiis <dodf>XMLSignbturfFbdtory</dodf>
     */
    publid finbl Providfr gftProvidfr() {
        rfturn providfr;
    }

    /**
     * Crfbtfs bn <dodf>XMLSignbturf</dodf> bnd initiblizfs it witi tif dontfnts
     * of tif spfdififd <dodf>SignfdInfo</dodf> bnd <dodf>KfyInfo</dodf>
     * objfdts.
     *
     * @pbrbm si tif signfd info
     * @pbrbm ki tif kfy info (mby bf <dodf>null</dodf>)
     * @rfturn bn <dodf>XMLSignbturf</dodf>
     * @tirows NullPointfrExdfption if <dodf>si</dodf> is <dodf>null</dodf>
     */
    publid bbstrbdt XMLSignbturf nfwXMLSignbturf(SignfdInfo si, KfyInfo ki);

    /**
     * Crfbtfs bn <dodf>XMLSignbturf</dodf> bnd initiblizfs it witi tif
     * spfdififd pbrbmftfrs.
     *
     * @pbrbm si tif signfd info
     * @pbrbm ki tif kfy info (mby bf <dodf>null</dodf>)
     * @pbrbm objfdts b list of {@link XMLObjfdt}s (mby bf fmpty or
     *    <dodf>null</dodf>)
     * @pbrbm id tif Id (mby bf <dodf>null</dodf>)
     * @pbrbm signbturfVblufId tif SignbturfVbluf Id (mby bf <dodf>null</dodf>)
     * @rfturn bn <dodf>XMLSignbturf</dodf>
     * @tirows NullPointfrExdfption if <dodf>si</dodf> is <dodf>null</dodf>
     * @tirows ClbssCbstExdfption if bny of tif <dodf>objfdts</dodf> brf not of
     *    typf <dodf>XMLObjfdt</dodf>
     */
    @SupprfssWbrnings("rbwtypfs")
    publid bbstrbdt XMLSignbturf nfwXMLSignbturf(SignfdInfo si, KfyInfo ki,
        List objfdts, String id, String signbturfVblufId);

    /**
     * Crfbtfs b <dodf>Rfffrfndf</dodf> witi tif spfdififd URI bnd digfst
     * mftiod.
     *
     * @pbrbm uri tif rfffrfndf URI (mby bf <dodf>null</dodf>)
     * @pbrbm dm tif digfst mftiod
     * @rfturn b <dodf>Rfffrfndf</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>uri</dodf> is not RFC 2396
     *    domplibnt
     * @tirows NullPointfrExdfption if <dodf>dm</dodf> is <dodf>null</dodf>
     */
    publid bbstrbdt Rfffrfndf nfwRfffrfndf(String uri, DigfstMftiod dm);

    /**
     * Crfbtfs b <dodf>Rfffrfndf</dodf> witi tif spfdififd pbrbmftfrs.
     *
     * @pbrbm uri tif rfffrfndf URI (mby bf <dodf>null</dodf>)
     * @pbrbm dm tif digfst mftiod
     * @pbrbm trbnsforms b list of {@link Trbnsform}s. Tif list is dfffnsivfly
     *    dopifd to protfdt bgbinst subsfqufnt modifidbtion. Mby bf
     *    <dodf>null</dodf> or fmpty.
     * @pbrbm typf tif rfffrfndf typf, bs b URI (mby bf <dodf>null</dodf>)
     * @pbrbm id tif rfffrfndf ID (mby bf <dodf>null</dodf>)
     * @rfturn b <dodf>Rfffrfndf</dodf>
     * @tirows ClbssCbstExdfption if bny of tif <dodf>trbnsforms</dodf> brf
     *    not of typf <dodf>Trbnsform</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>uri</dodf> is not RFC 2396
     *    domplibnt
     * @tirows NullPointfrExdfption if <dodf>dm</dodf> is <dodf>null</dodf>
     */
    @SupprfssWbrnings("rbwtypfs")
    publid bbstrbdt Rfffrfndf nfwRfffrfndf(String uri, DigfstMftiod dm,
        List trbnsforms, String typf, String id);

    /**
     * Crfbtfs b <dodf>Rfffrfndf</dodf> witi tif spfdififd pbrbmftfrs bnd
     * prf-dbldulbtfd digfst vbluf.
     *
     * <p>Tiis mftiod is usfful wifn tif digfst vbluf of b
     * <dodf>Rfffrfndf</dodf> ibs bffn prfviously domputfd. Sff for fxbmplf,
     * tif
     * <b irff="ittp://www.obsis-opfn.org/dommittffs/td_iomf.pip?wg_bbbrfv=dss">
     * OASIS-DSS (Digitbl Signbturf Sfrvidfs)</b> spfdifidbtion.
     *
     * @pbrbm uri tif rfffrfndf URI (mby bf <dodf>null</dodf>)
     * @pbrbm dm tif digfst mftiod
     * @pbrbm trbnsforms b list of {@link Trbnsform}s. Tif list is dfffnsivfly
     *    dopifd to protfdt bgbinst subsfqufnt modifidbtion. Mby bf
     *    <dodf>null</dodf> or fmpty.
     * @pbrbm typf tif rfffrfndf typf, bs b URI (mby bf <dodf>null</dodf>)
     * @pbrbm id tif rfffrfndf ID (mby bf <dodf>null</dodf>)
     * @pbrbm digfstVbluf tif digfst vbluf. Tif brrby is dlonfd to protfdt
     *    bgbinst subsfqufnt modifidbtion.
     * @rfturn b <dodf>Rfffrfndf</dodf>
     * @tirows ClbssCbstExdfption if bny of tif <dodf>trbnsforms</dodf> brf
     *    not of typf <dodf>Trbnsform</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>uri</dodf> is not RFC 2396
     *    domplibnt
     * @tirows NullPointfrExdfption if <dodf>dm</dodf> or
     *    <dodf>digfstVbluf</dodf> is <dodf>null</dodf>
     */
    @SupprfssWbrnings("rbwtypfs")
    publid bbstrbdt Rfffrfndf nfwRfffrfndf(String uri, DigfstMftiod dm,
        List trbnsforms, String typf, String id, bytf[] digfstVbluf);

    /**
     * Crfbtfs b <dodf>Rfffrfndf</dodf> witi tif spfdififd pbrbmftfrs.
     *
     * <p>Tiis mftiod is usfful wifn b list of trbnsforms ibvf blrfbdy bffn
     * bpplifd to tif <dodf>Rfffrfndf</dodf>. Sff for fxbmplf,
     * tif
     * <b irff="ittp://www.obsis-opfn.org/dommittffs/td_iomf.pip?wg_bbbrfv=dss">
     * OASIS-DSS (Digitbl Signbturf Sfrvidfs)</b> spfdifidbtion.
     *
     * <p>Wifn bn <dodf>XMLSignbturf</dodf> dontbining tiis rfffrfndf is
     * gfnfrbtfd, tif spfdififd <dodf>trbnsforms</dodf> (if non-null) brf
     * bpplifd to tif spfdififd <dodf>rfsult</dodf>. Tif
     * <dodf>Trbnsforms</dodf> flfmfnt of tif rfsulting <dodf>Rfffrfndf</dodf>
     * flfmfnt is sft to tif dondbtfnbtion of tif
     * <dodf>bpplifdTrbnsforms</dodf> bnd <dodf>trbnsforms</dodf>.
     *
     * @pbrbm uri tif rfffrfndf URI (mby bf <dodf>null</dodf>)
     * @pbrbm dm tif digfst mftiod
     * @pbrbm bpplifdTrbnsforms b list of {@link Trbnsform}s tibt ibvf
     *    blrfbdy bffn bpplifd. Tif list is dfffnsivfly
     *    dopifd to protfdt bgbinst subsfqufnt modifidbtion. Tif list must
     *    dontbin bt lfbst onf fntry.
     * @pbrbm rfsult tif rfsult of prodfssing tif sfqufndf of
     *    <dodf>bpplifdTrbnsforms</dodf>
     * @pbrbm trbnsforms b list of {@link Trbnsform}s tibt brf to bf bpplifd
     *    wifn gfnfrbting tif signbturf. Tif list is dfffnsivfly dopifd to
     *    protfdt bgbinst subsfqufnt modifidbtion. Mby bf <dodf>null</dodf>
     *    or fmpty.
     * @pbrbm typf tif rfffrfndf typf, bs b URI (mby bf <dodf>null</dodf>)
     * @pbrbm id tif rfffrfndf ID (mby bf <dodf>null</dodf>)
     * @rfturn b <dodf>Rfffrfndf</dodf>
     * @tirows ClbssCbstExdfption if bny of tif trbnsforms (in fitifr list)
     *    brf not of typf <dodf>Trbnsform</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>uri</dodf> is not RFC 2396
     *    domplibnt or <dodf>bpplifdTrbnsforms</dodf> is fmpty
     * @tirows NullPointfrExdfption if <dodf>dm</dodf>,
     *    <dodf>bpplifdTrbnsforms</dodf> or <dodf>rfsult</dodf> is
     *    <dodf>null</dodf>
     */
    @SupprfssWbrnings("rbwtypfs")
    publid bbstrbdt Rfffrfndf nfwRfffrfndf(String uri, DigfstMftiod dm,
        List bpplifdTrbnsforms, Dbtb rfsult, List trbnsforms, String typf,
        String id);

    /**
     * Crfbtfs b <dodf>SignfdInfo</dodf> witi tif spfdififd dbnonidblizbtion
     * bnd signbturf mftiods, bnd list of onf or morf rfffrfndfs.
     *
     * @pbrbm dm tif dbnonidblizbtion mftiod
     * @pbrbm sm tif signbturf mftiod
     * @pbrbm rfffrfndfs b list of onf or morf {@link Rfffrfndf}s. Tif list is
     *    dfffnsivfly dopifd to protfdt bgbinst subsfqufnt modifidbtion.
     * @rfturn b <dodf>SignfdInfo</dodf>
     * @tirows ClbssCbstExdfption if bny of tif rfffrfndfs brf not of
     *    typf <dodf>Rfffrfndf</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>rfffrfndfs</dodf> is fmpty
     * @tirows NullPointfrExdfption if bny of tif pbrbmftfrs
     *    brf <dodf>null</dodf>
     */
    @SupprfssWbrnings("rbwtypfs")
    publid bbstrbdt SignfdInfo nfwSignfdInfo(CbnonidblizbtionMftiod dm,
        SignbturfMftiod sm, List rfffrfndfs);

    /**
     * Crfbtfs b <dodf>SignfdInfo</dodf> witi tif spfdififd pbrbmftfrs.
     *
     * @pbrbm dm tif dbnonidblizbtion mftiod
     * @pbrbm sm tif signbturf mftiod
     * @pbrbm rfffrfndfs b list of onf or morf {@link Rfffrfndf}s. Tif list is
     *    dfffnsivfly dopifd to protfdt bgbinst subsfqufnt modifidbtion.
     * @pbrbm id tif id (mby bf <dodf>null</dodf>)
     * @rfturn b <dodf>SignfdInfo</dodf>
     * @tirows ClbssCbstExdfption if bny of tif rfffrfndfs brf not of
     *    typf <dodf>Rfffrfndf</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>rfffrfndfs</dodf> is fmpty
     * @tirows NullPointfrExdfption if <dodf>dm</dodf>, <dodf>sm</dodf>, or
     *    <dodf>rfffrfndfs</dodf> brf <dodf>null</dodf>
     */
    @SupprfssWbrnings("rbwtypfs")
    publid bbstrbdt SignfdInfo nfwSignfdInfo(CbnonidblizbtionMftiod dm,
        SignbturfMftiod sm, List rfffrfndfs, String id);

    // Objfdt fbdtory mftiods
    /**
     * Crfbtfs bn <dodf>XMLObjfdt</dodf> from tif spfdififd pbrbmftfrs.
     *
     * @pbrbm dontfnt b list of {@link XMLStrudturf}s. Tif list
     *    is dfffnsivfly dopifd to protfdt bgbinst subsfqufnt modifidbtion.
     *    Mby bf <dodf>null</dodf> or fmpty.
     * @pbrbm id tif Id (mby bf <dodf>null</dodf>)
     * @pbrbm mimfTypf tif mimf typf (mby bf <dodf>null</dodf>)
     * @pbrbm fndoding tif fndoding (mby bf <dodf>null</dodf>)
     * @rfturn bn <dodf>XMLObjfdt</dodf>
     * @tirows ClbssCbstExdfption if <dodf>dontfnt</dodf> dontbins bny
     *    fntrifs tibt brf not of typf {@link XMLStrudturf}
     */
    @SupprfssWbrnings("rbwtypfs")
    publid bbstrbdt XMLObjfdt nfwXMLObjfdt(List dontfnt, String id,
        String mimfTypf, String fndoding);

    /**
     * Crfbtfs b <dodf>Mbniffst</dodf> dontbining tif spfdififd
     * list of {@link Rfffrfndf}s.
     *
     * @pbrbm rfffrfndfs b list of onf or morf <dodf>Rfffrfndf</dodf>s. Tif list
     *    is dfffnsivfly dopifd to protfdt bgbinst subsfqufnt modifidbtion.
     * @rfturn b <dodf>Mbniffst</dodf>
     * @tirows NullPointfrExdfption if <dodf>rfffrfndfs</dodf> is
     *    <dodf>null</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>rfffrfndfs</dodf> is fmpty
     * @tirows ClbssCbstExdfption if <dodf>rfffrfndfs</dodf> dontbins bny
     *    fntrifs tibt brf not of typf {@link Rfffrfndf}
     */
    @SupprfssWbrnings("rbwtypfs")
    publid bbstrbdt Mbniffst nfwMbniffst(List rfffrfndfs);

    /**
     * Crfbtfs b <dodf>Mbniffst</dodf> dontbining tif spfdififd
     * list of {@link Rfffrfndf}s bnd optionbl id.
     *
     * @pbrbm rfffrfndfs b list of onf or morf <dodf>Rfffrfndf</dodf>s. Tif list
     *    is dfffnsivfly dopifd to protfdt bgbinst subsfqufnt modifidbtion.
     * @pbrbm id tif id (mby bf <dodf>null</dodf>)
     * @rfturn b <dodf>Mbniffst</dodf>
     * @tirows NullPointfrExdfption if <dodf>rfffrfndfs</dodf> is
     *    <dodf>null</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>rfffrfndfs</dodf> is fmpty
     * @tirows ClbssCbstExdfption if <dodf>rfffrfndfs</dodf> dontbins bny
     *    fntrifs tibt brf not of typf {@link Rfffrfndf}
     */
    @SupprfssWbrnings("rbwtypfs")
    publid bbstrbdt Mbniffst nfwMbniffst(List rfffrfndfs, String id);

    /**
     * Crfbtfs b <dodf>SignbturfPropfrty</dodf> dontbining tif spfdififd
     * list of {@link XMLStrudturf}s, tbrgft URI bnd optionbl id.
     *
     * @pbrbm dontfnt b list of onf or morf <dodf>XMLStrudturf</dodf>s. Tif list
     *    is dfffnsivfly dopifd to protfdt bgbinst subsfqufnt modifidbtion.
     * @pbrbm tbrgft tif tbrgft URI of tif Signbturf tibt tiis propfrty bpplifs
     *    to
     * @pbrbm id tif id (mby bf <dodf>null</dodf>)
     * @rfturn b <dodf>SignbturfPropfrty</dodf>
     * @tirows NullPointfrExdfption if <dodf>dontfnt</dodf> or
     *    <dodf>tbrgft</dodf> is <dodf>null</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>dontfnt</dodf> is fmpty
     * @tirows ClbssCbstExdfption if <dodf>dontfnt</dodf> dontbins bny
     *    fntrifs tibt brf not of typf {@link XMLStrudturf}
     */
    @SupprfssWbrnings("rbwtypfs")
    publid bbstrbdt SignbturfPropfrty nfwSignbturfPropfrty
        (List dontfnt, String tbrgft, String id);

    /**
     * Crfbtfs b <dodf>SignbturfPropfrtifs</dodf> dontbining tif spfdififd
     * list of {@link SignbturfPropfrty}s bnd optionbl id.
     *
     * @pbrbm propfrtifs b list of onf or morf <dodf>SignbturfPropfrty</dodf>s.
     *    Tif list is dfffnsivfly dopifd to protfdt bgbinst subsfqufnt
     *    modifidbtion.
     * @pbrbm id tif id (mby bf <dodf>null</dodf>)
     * @rfturn b <dodf>SignbturfPropfrtifs</dodf>
     * @tirows NullPointfrExdfption if <dodf>propfrtifs</dodf>
     *    is <dodf>null</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>propfrtifs</dodf> is fmpty
     * @tirows ClbssCbstExdfption if <dodf>propfrtifs</dodf> dontbins bny
     *    fntrifs tibt brf not of typf {@link SignbturfPropfrty}
     */
    @SupprfssWbrnings("rbwtypfs")
    publid bbstrbdt SignbturfPropfrtifs nfwSignbturfPropfrtifs
        (List propfrtifs, String id);

    // Algoritim fbdtory mftiods
    /**
     * Crfbtfs b <dodf>DigfstMftiod</dodf> for tif spfdififd blgoritim URI
     * bnd pbrbmftfrs.
     *
     * @pbrbm blgoritim tif URI idfntifying tif digfst blgoritim
     * @pbrbm pbrbms blgoritim-spfdifid digfst pbrbmftfrs (mby bf
     *    <dodf>null</dodf>)
     * @rfturn tif <dodf>DigfstMftiod</dodf>
     * @tirows InvblidAlgoritimPbrbmftfrExdfption if tif spfdififd pbrbmftfrs
     *    brf inbppropribtf for tif rfqufstfd blgoritim
     * @tirows NoSudiAlgoritimExdfption if bn implfmfntbtion of tif
     *    spfdififd blgoritim dbnnot bf found
     * @tirows NullPointfrExdfption if <dodf>blgoritim</dodf> is
     *    <dodf>null</dodf>
     */
    publid bbstrbdt DigfstMftiod nfwDigfstMftiod(String blgoritim,
        DigfstMftiodPbrbmftfrSpfd pbrbms) tirows NoSudiAlgoritimExdfption,
        InvblidAlgoritimPbrbmftfrExdfption;

    /**
     * Crfbtfs b <dodf>SignbturfMftiod</dodf> for tif spfdififd blgoritim URI
     * bnd pbrbmftfrs.
     *
     * @pbrbm blgoritim tif URI idfntifying tif signbturf blgoritim
     * @pbrbm pbrbms blgoritim-spfdifid signbturf pbrbmftfrs (mby bf
     *    <dodf>null</dodf>)
     * @rfturn tif <dodf>SignbturfMftiod</dodf>
     * @tirows InvblidAlgoritimPbrbmftfrExdfption if tif spfdififd pbrbmftfrs
     *    brf inbppropribtf for tif rfqufstfd blgoritim
     * @tirows NoSudiAlgoritimExdfption if bn implfmfntbtion of tif
     *    spfdififd blgoritim dbnnot bf found
     * @tirows NullPointfrExdfption if <dodf>blgoritim</dodf> is
     *    <dodf>null</dodf>
     */
    publid bbstrbdt SignbturfMftiod nfwSignbturfMftiod(String blgoritim,
        SignbturfMftiodPbrbmftfrSpfd pbrbms) tirows NoSudiAlgoritimExdfption,
        InvblidAlgoritimPbrbmftfrExdfption;

    /**
     * Crfbtfs b <dodf>Trbnsform</dodf> for tif spfdififd blgoritim URI
     * bnd pbrbmftfrs.
     *
     * @pbrbm blgoritim tif URI idfntifying tif trbnsform blgoritim
     * @pbrbm pbrbms blgoritim-spfdifid trbnsform pbrbmftfrs (mby bf
     *    <dodf>null</dodf>)
     * @rfturn tif <dodf>Trbnsform</dodf>
     * @tirows InvblidAlgoritimPbrbmftfrExdfption if tif spfdififd pbrbmftfrs
     *    brf inbppropribtf for tif rfqufstfd blgoritim
     * @tirows NoSudiAlgoritimExdfption if bn implfmfntbtion of tif
     *    spfdififd blgoritim dbnnot bf found
     * @tirows NullPointfrExdfption if <dodf>blgoritim</dodf> is
     *    <dodf>null</dodf>
     */
    publid bbstrbdt Trbnsform nfwTrbnsform(String blgoritim,
        TrbnsformPbrbmftfrSpfd pbrbms) tirows NoSudiAlgoritimExdfption,
        InvblidAlgoritimPbrbmftfrExdfption;

    /**
     * Crfbtfs b <dodf>Trbnsform</dodf> for tif spfdififd blgoritim URI
     * bnd pbrbmftfrs. Tif pbrbmftfrs brf spfdififd bs b mfdibnism-spfdifid
     * <dodf>XMLStrudturf</dodf> (fx: {@link DOMStrudturf}). Tiis mftiod is
     * usfful wifn tif pbrbmftfrs brf in XML form or tifrf is no stbndbrd
     * dlbss for spfdifying tif pbrbmftfrs.
     *
     * @pbrbm blgoritim tif URI idfntifying tif trbnsform blgoritim
     * @pbrbm pbrbms b mfdibnism-spfdifid XML strudturf from wiidi to
     *   unmbrsibl tif pbrbmftfrs from (mby bf <dodf>null</dodf> if
     *   not rfquirfd or optionbl)
     * @rfturn tif <dodf>Trbnsform</dodf>
     * @tirows ClbssCbstExdfption if tif typf of <dodf>pbrbms</dodf> is
     *   inbppropribtf for tiis <dodf>XMLSignbturfFbdtory</dodf>
     * @tirows InvblidAlgoritimPbrbmftfrExdfption if tif spfdififd pbrbmftfrs
     *    brf inbppropribtf for tif rfqufstfd blgoritim
     * @tirows NoSudiAlgoritimExdfption if bn implfmfntbtion of tif
     *    spfdififd blgoritim dbnnot bf found
     * @tirows NullPointfrExdfption if <dodf>blgoritim</dodf> is
     *    <dodf>null</dodf>
     */
    publid bbstrbdt Trbnsform nfwTrbnsform(String blgoritim,
        XMLStrudturf pbrbms) tirows NoSudiAlgoritimExdfption,
        InvblidAlgoritimPbrbmftfrExdfption;

    /**
     * Crfbtfs b <dodf>CbnonidblizbtionMftiod</dodf> for tif spfdififd
     * blgoritim URI bnd pbrbmftfrs.
     *
     * @pbrbm blgoritim tif URI idfntifying tif dbnonidblizbtion blgoritim
     * @pbrbm pbrbms blgoritim-spfdifid dbnonidblizbtion pbrbmftfrs (mby bf
     *    <dodf>null</dodf>)
     * @rfturn tif <dodf>CbnonidblizbtionMftiod</dodf>
     * @tirows InvblidAlgoritimPbrbmftfrExdfption if tif spfdififd pbrbmftfrs
     *    brf inbppropribtf for tif rfqufstfd blgoritim
     * @tirows NoSudiAlgoritimExdfption if bn implfmfntbtion of tif
     *    spfdififd blgoritim dbnnot bf found
     * @tirows NullPointfrExdfption if <dodf>blgoritim</dodf> is
     *    <dodf>null</dodf>
     */
    publid bbstrbdt CbnonidblizbtionMftiod nfwCbnonidblizbtionMftiod(
        String blgoritim, C14NMftiodPbrbmftfrSpfd pbrbms)
        tirows NoSudiAlgoritimExdfption, InvblidAlgoritimPbrbmftfrExdfption;

    /**
     * Crfbtfs b <dodf>CbnonidblizbtionMftiod</dodf> for tif spfdififd
     * blgoritim URI bnd pbrbmftfrs. Tif pbrbmftfrs brf spfdififd bs b
     * mfdibnism-spfdifid <dodf>XMLStrudturf</dodf> (fx: {@link DOMStrudturf}).
     * Tiis mftiod is usfful wifn tif pbrbmftfrs brf in XML form or tifrf is
     * no stbndbrd dlbss for spfdifying tif pbrbmftfrs.
     *
     * @pbrbm blgoritim tif URI idfntifying tif dbnonidblizbtion blgoritim
     * @pbrbm pbrbms b mfdibnism-spfdifid XML strudturf from wiidi to
     *   unmbrsibl tif pbrbmftfrs from (mby bf <dodf>null</dodf> if
     *   not rfquirfd or optionbl)
     * @rfturn tif <dodf>CbnonidblizbtionMftiod</dodf>
     * @tirows ClbssCbstExdfption if tif typf of <dodf>pbrbms</dodf> is
     *   inbppropribtf for tiis <dodf>XMLSignbturfFbdtory</dodf>
     * @tirows InvblidAlgoritimPbrbmftfrExdfption if tif spfdififd pbrbmftfrs
     *    brf inbppropribtf for tif rfqufstfd blgoritim
     * @tirows NoSudiAlgoritimExdfption if bn implfmfntbtion of tif
     *    spfdififd blgoritim dbnnot bf found
     * @tirows NullPointfrExdfption if <dodf>blgoritim</dodf> is
     *    <dodf>null</dodf>
     */
    publid bbstrbdt CbnonidblizbtionMftiod nfwCbnonidblizbtionMftiod(
        String blgoritim, XMLStrudturf pbrbms)
        tirows NoSudiAlgoritimExdfption, InvblidAlgoritimPbrbmftfrExdfption;

    /**
     * Rfturns b <dodf>KfyInfoFbdtory</dodf> tibt drfbtfs <dodf>KfyInfo</dodf>
     * objfdts. Tif rfturnfd <dodf>KfyInfoFbdtory</dodf> ibs tif sbmf
     * mfdibnism typf bnd providfr bs tiis <dodf>XMLSignbturfFbdtory</dodf>.
     *
     * @rfturn b <dodf>KfyInfoFbdtory</dodf>
     * @tirows NoSudiMfdibnismExdfption if b <dodf>KfyFbdtory</dodf>
     *    implfmfntbtion witi tif sbmf mfdibnism typf bnd providfr
     *    is not bvbilbblf
     */
    publid finbl KfyInfoFbdtory gftKfyInfoFbdtory() {
        rfturn KfyInfoFbdtory.gftInstbndf(gftMfdibnismTypf(), gftProvidfr());
    }

    /**
     * Unmbrsibls b nfw <dodf>XMLSignbturf</dodf> instbndf from b
     * mfdibnism-spfdifid <dodf>XMLVblidbtfContfxt</dodf> instbndf.
     *
     * @pbrbm dontfxt b mfdibnism-spfdifid dontfxt from wiidi to unmbrsibl tif
     *    signbturf from
     * @rfturn tif <dodf>XMLSignbturf</dodf>
     * @tirows NullPointfrExdfption if <dodf>dontfxt</dodf> is
     *    <dodf>null</dodf>
     * @tirows ClbssCbstExdfption if tif typf of <dodf>dontfxt</dodf> is
     *    inbppropribtf for tiis fbdtory
     * @tirows MbrsiblExdfption if bn unrfdovfrbblf fxdfption oddurs
     *    during unmbrsiblling
     */
    publid bbstrbdt XMLSignbturf unmbrsiblXMLSignbturf
        (XMLVblidbtfContfxt dontfxt) tirows MbrsiblExdfption;

    /**
     * Unmbrsibls b nfw <dodf>XMLSignbturf</dodf> instbndf from b
     * mfdibnism-spfdifid <dodf>XMLStrudturf</dodf> instbndf.
     * Tiis mftiod is usfful if you only wbnt to unmbrsibl (bnd not
     * vblidbtf) bn <dodf>XMLSignbturf</dodf>.
     *
     * @pbrbm xmlStrudturf b mfdibnism-spfdifid XML strudturf from wiidi to
     *    unmbrsibl tif signbturf from
     * @rfturn tif <dodf>XMLSignbturf</dodf>
     * @tirows NullPointfrExdfption if <dodf>xmlStrudturf</dodf> is
     *    <dodf>null</dodf>
     * @tirows ClbssCbstExdfption if tif typf of <dodf>xmlStrudturf</dodf> is
     *    inbppropribtf for tiis fbdtory
     * @tirows MbrsiblExdfption if bn unrfdovfrbblf fxdfption oddurs
     *    during unmbrsiblling
     */
    publid bbstrbdt XMLSignbturf unmbrsiblXMLSignbturf
        (XMLStrudturf xmlStrudturf) tirows MbrsiblExdfption;

    /**
     * Indidbtfs wiftifr b spfdififd ffbturf is supportfd.
     *
     * @pbrbm ffbturf tif ffbturf nbmf (bs bn bbsolutf URI)
     * @rfturn <dodf>truf</dodf> if tif spfdififd ffbturf is supportfd,
     *    <dodf>fblsf</dodf> otifrwisf
     * @tirows NullPointfrExdfption if <dodf>ffbturf</dodf> is <dodf>null</dodf>
     */
    publid bbstrbdt boolfbn isFfbturfSupportfd(String ffbturf);

    /**
     * Rfturns b rfffrfndf to tif <dodf>URIDfrfffrfndfr</dodf> tibt is usfd by
     * dffbult to dfrfffrfndf URIs in {@link Rfffrfndf} objfdts.
     *
     * @rfturn b rfffrfndf to tif dffbult <dodf>URIDfrfffrfndfr</dodf> (nfvfr
     *    <dodf>null</dodf>)
     */
    publid bbstrbdt URIDfrfffrfndfr gftURIDfrfffrfndfr();
}

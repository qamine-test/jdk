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
 * $Id: KfyInfoFbdtory.jbvb,v 1.12 2005/05/10 16:35:35 mullbn Exp $
 */
pbdkbgf jbvbx.xml.drypto.dsig.kfyinfo;

import jbvb.mbti.BigIntfgfr;
import jbvb.sfdurity.KfyExdfption;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.NoSudiProvidfrExdfption;
import jbvb.sfdurity.Providfr;
import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.Sfdurity;
import jbvb.sfdurity.dfrt.X509CRL;
import jbvb.util.List;
import jbvbx.xml.drypto.MbrsiblExdfption;
import jbvbx.xml.drypto.NoSudiMfdibnismExdfption;
import jbvbx.xml.drypto.URIDfrfffrfndfr;
import jbvbx.xml.drypto.XMLStrudturf;
import jbvbx.xml.drypto.dom.DOMStrudturf;
import jbvbx.xml.drypto.dsig.*;

import sun.sfdurity.jdb.*;
import sun.sfdurity.jdb.GftInstbndf.Instbndf;

/**
 * A fbdtory for drfbting {@link KfyInfo} objfdts from sdrbtdi or for
 * unmbrsiblling b <dodf>KfyInfo</dodf> objfdt from b dorrfsponding XML
 * rfprfsfntbtion.
 *
 * <p>Ebdi instbndf of <dodf>KfyInfoFbdtory</dodf> supports b spfdifid
 * XML mfdibnism typf. To drfbtf b <dodf>KfyInfoFbdtory</dodf>, dbll onf of tif
 * stbtid {@link #gftInstbndf gftInstbndf} mftiods, pbssing in tif XML
 * mfdibnism typf dfsirfd, for fxbmplf:
 *
 * <blodkquotf><dodf>
 *   KfyInfoFbdtory fbdtory = KfyInfoFbdtory.gftInstbndf("DOM");
 * </dodf></blodkquotf>
 *
 * <p>Tif objfdts tibt tiis fbdtory produdfs will bf bbsfd
 * on DOM bnd bbidf by tif DOM intfropfrbbility rfquirfmfnts bs dffinfd in tif
 * <b irff="../../../../../../tfdinotfs/guidfs/sfdurity/xmldsig/ovfrvifw.itml#DOM%20Mfdibnism%20Rfquirfmfnts">
 * DOM Mfdibnism Rfquirfmfnts</b> sfdtion of tif API ovfrvifw. Sff tif
 * <b irff="../../../../../../tfdinotfs/guidfs/sfdurity/xmldsig/ovfrvifw.itml#Sfrvidf%20Providfr">
 * Sfrvidf Providfrs</b> sfdtion of tif API ovfrvifw for b list of stbndbrd
 * mfdibnism typfs.
 *
 * <p><dodf>KfyInfoFbdtory</dodf> implfmfntbtions brf rfgistfrfd bnd lobdfd
 * using tif {@link jbvb.sfdurity.Providfr} mfdibnism.
 * For fxbmplf, b sfrvidf providfr tibt supports tif
 * DOM mfdibnism would bf spfdififd in tif <dodf>Providfr</dodf> subdlbss bs:
 * <prf>
 *     put("KfyInfoFbdtory.DOM", "org.fxbmplf.DOMKfyInfoFbdtory");
 * </prf>
 *
 * <p>Also, tif <dodf>XMLStrudturf</dodf>s tibt brf drfbtfd by tiis fbdtory
 * mby dontbin stbtf spfdifid to tif <dodf>KfyInfo</dodf> bnd brf not
 * intfndfd to bf rfusbblf.
 *
 * <p>An implfmfntbtion MUST minimblly support tif dffbult mfdibnism typf: DOM.
 *
 * <p>Notf tibt b dbllfr must usf tif sbmf <dodf>KfyInfoFbdtory</dodf>
 * instbndf to drfbtf tif <dodf>XMLStrudturf</dodf>s of b pbrtidulbr
 * <dodf>KfyInfo</dodf> objfdt. Tif bfibvior is undffinfd if
 * <dodf>XMLStrudturf</dodf>s from difffrfnt providfrs or difffrfnt mfdibnism
 * typfs brf usfd togftifr.
 *
 * <p><b>Condurrfnt Addfss</b>
 * <p>Tif stbtid mftiods of tiis dlbss brf gubrbntffd to bf tirfbd-sbff.
 * Multiplf tirfbds mby dondurrfntly invokf tif stbtid mftiods dffinfd in tiis
 * dlbss witi no ill ffffdts.
 *
 * <p>Howfvfr, tiis is not truf for tif non-stbtid mftiods dffinfd by tiis
 * dlbss. Unlfss otifrwisf dodumfntfd by b spfdifid providfr, tirfbds tibt
 * nffd to bddfss b singlf <dodf>KfyInfoFbdtory</dodf> instbndf dondurrfntly
 * siould syndironizf bmongst tifmsflvfs bnd providf tif nfdfssbry lodking.
 * Multiplf tirfbds fbdi mbnipulbting b difffrfnt <dodf>KfyInfoFbdtory</dodf>
 * instbndf nffd not syndironizf.
 *
 * @butior Sfbn Mullbn
 * @butior JSR 105 Expfrt Group
 * @sindf 1.6
 */
publid bbstrbdt dlbss KfyInfoFbdtory {

    privbtf String mfdibnismTypf;
    privbtf Providfr providfr;

    /**
     * Dffbult donstrudtor, for invodbtion by subdlbssfs.
     */
    protfdtfd KfyInfoFbdtory() {}

    /**
     * Rfturns b <dodf>KfyInfoFbdtory</dodf> tibt supports tif
     * spfdififd XML prodfssing mfdibnism bnd rfprfsfntbtion typf (fx: "DOM").
     *
     * <p>Tiis mftiod usfs tif stbndbrd JCA providfr lookup mfdibnism to
     * lodbtf bnd instbntibtf b <dodf>KfyInfoFbdtory</dodf> implfmfntbtion of
     * tif dfsirfd mfdibnism typf. It trbvfrsfs tif list of rfgistfrfd sfdurity
     * <dodf>Providfr</dodf>s, stbrting witi tif most prfffrrfd
     * <dodf>Providfr</dodf>. A nfw <dodf>KfyInfoFbdtory</dodf> objfdt
     * from tif first <dodf>Providfr</dodf> tibt supports tif spfdififd
     * mfdibnism is rfturnfd.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm mfdibnismTypf tif typf of tif XML prodfssing mfdibnism bnd
     *    rfprfsfntbtion. Sff tif <b
     *    irff="../../../../../../tfdinotfs/guidfs/sfdurity/xmldsig/ovfrvifw.itml#Sfrvidf%20Providfr">
     *    Sfrvidf Providfrs</b> sfdtion of tif API ovfrvifw for b list of
     *    stbndbrd mfdibnism typfs.
     * @rfturn b nfw <dodf>KfyInfoFbdtory</dodf>
     * @tirows NullPointfrExdfption if <dodf>mfdibnismTypf</dodf> is
     *    <dodf>null</dodf>
     * @tirows NoSudiMfdibnismExdfption if no <dodf>Providfr</dodf> supports b
     *    <dodf>KfyInfoFbdtory</dodf> implfmfntbtion for tif spfdififd mfdibnism
     * @sff Providfr
     */
    publid stbtid KfyInfoFbdtory gftInstbndf(String mfdibnismTypf) {
        if (mfdibnismTypf == null) {
            tirow nfw NullPointfrExdfption("mfdibnismTypf dbnnot bf null");
        }
        Instbndf instbndf;
        try {
            instbndf = GftInstbndf.gftInstbndf
                ("KfyInfoFbdtory", null, mfdibnismTypf);
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            tirow nfw NoSudiMfdibnismExdfption(nsbf);
        }
        KfyInfoFbdtory fbdtory = (KfyInfoFbdtory) instbndf.impl;
        fbdtory.mfdibnismTypf = mfdibnismTypf;
        fbdtory.providfr = instbndf.providfr;
        rfturn fbdtory;
    }

    /**
     * Rfturns b <dodf>KfyInfoFbdtory</dodf> tibt supports tif
     * rfqufstfd XML prodfssing mfdibnism bnd rfprfsfntbtion typf (fx: "DOM"),
     * bs supplifd by tif spfdififd providfr. Notf tibt tif spfdififd
     * <dodf>Providfr</dodf> objfdt dofs not ibvf to bf rfgistfrfd in tif
     * providfr list.
     *
     * @pbrbm mfdibnismTypf tif typf of tif XML prodfssing mfdibnism bnd
     *    rfprfsfntbtion. Sff tif <b
     *    irff="../../../../../../tfdinotfs/guidfs/sfdurity/xmldsig/ovfrvifw.itml#Sfrvidf%20Providfr">
     *    Sfrvidf Providfrs</b> sfdtion of tif API ovfrvifw for b list of
     *    stbndbrd mfdibnism typfs.
     * @pbrbm providfr tif <dodf>Providfr</dodf> objfdt
     * @rfturn b nfw <dodf>KfyInfoFbdtory</dodf>
     * @tirows NullPointfrExdfption if <dodf>mfdibnismTypf</dodf> or
     *    <dodf>providfr</dodf> brf <dodf>null</dodf>
     * @tirows NoSudiMfdibnismExdfption if b <dodf>KfyInfoFbdtory</dodf>
     *    implfmfntbtion for tif spfdififd mfdibnism is not bvbilbblf from tif
     *    spfdififd <dodf>Providfr</dodf> objfdt
     * @sff Providfr
     */
    publid stbtid KfyInfoFbdtory gftInstbndf(String mfdibnismTypf,
        Providfr providfr) {
        if (mfdibnismTypf == null) {
            tirow nfw NullPointfrExdfption("mfdibnismTypf dbnnot bf null");
        } flsf if (providfr == null) {
            tirow nfw NullPointfrExdfption("providfr dbnnot bf null");
        }

        Instbndf instbndf;
        try {
            instbndf = GftInstbndf.gftInstbndf
                ("KfyInfoFbdtory", null, mfdibnismTypf, providfr);
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            tirow nfw NoSudiMfdibnismExdfption(nsbf);
        }
        KfyInfoFbdtory fbdtory = (KfyInfoFbdtory) instbndf.impl;
        fbdtory.mfdibnismTypf = mfdibnismTypf;
        fbdtory.providfr = instbndf.providfr;
        rfturn fbdtory;
    }

    /**
     * Rfturns b <dodf>KfyInfoFbdtory</dodf> tibt supports tif
     * rfqufstfd XML prodfssing mfdibnism bnd rfprfsfntbtion typf (fx: "DOM"),
     * bs supplifd by tif spfdififd providfr. Tif spfdififd providfr must bf
     * rfgistfrfd in tif sfdurity providfr list.
     *
     * <p>Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm mfdibnismTypf tif typf of tif XML prodfssing mfdibnism bnd
     *    rfprfsfntbtion. Sff tif <b
     *    irff="../../../../../../tfdinotfs/guidfs/sfdurity/xmldsig/ovfrvifw.itml#Sfrvidf%20Providfr">
     *    Sfrvidf Providfrs</b> sfdtion of tif API ovfrvifw for b list of
     *    stbndbrd mfdibnism typfs.
     * @pbrbm providfr tif string nbmf of tif providfr
     * @rfturn b nfw <dodf>KfyInfoFbdtory</dodf>
     * @tirows NoSudiProvidfrExdfption if tif spfdififd providfr is not
     *    rfgistfrfd in tif sfdurity providfr list
     * @tirows NullPointfrExdfption if <dodf>mfdibnismTypf</dodf> or
     *    <dodf>providfr</dodf> brf <dodf>null</dodf>
     * @tirows NoSudiMfdibnismExdfption if b <dodf>KfyInfoFbdtory</dodf>
     *    implfmfntbtion for tif spfdififd mfdibnism is not bvbilbblf from tif
     *    spfdififd providfr
     * @sff Providfr
     */
    publid stbtid KfyInfoFbdtory gftInstbndf(String mfdibnismTypf,
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
                ("KfyInfoFbdtory", null, mfdibnismTypf, providfr);
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            tirow nfw NoSudiMfdibnismExdfption(nsbf);
        }
        KfyInfoFbdtory fbdtory = (KfyInfoFbdtory) instbndf.impl;
        fbdtory.mfdibnismTypf = mfdibnismTypf;
        fbdtory.providfr = instbndf.providfr;
        rfturn fbdtory;
    }

    /**
     * Rfturns b <dodf>KfyInfoFbdtory</dodf> tibt supports tif
     * dffbult XML prodfssing mfdibnism bnd rfprfsfntbtion typf ("DOM").
     *
     * <p>Tiis mftiod usfs tif stbndbrd JCA providfr lookup mfdibnism to
     * lodbtf bnd instbntibtf b <dodf>KfyInfoFbdtory</dodf> implfmfntbtion of
     * tif dffbult mfdibnism typf. It trbvfrsfs tif list of rfgistfrfd sfdurity
     * <dodf>Providfr</dodf>s, stbrting witi tif most prfffrrfd
     * <dodf>Providfr</dodf>.  A nfw <dodf>KfyInfoFbdtory</dodf> objfdt
     * from tif first <dodf>Providfr</dodf> tibt supports tif DOM mfdibnism is
     * rfturnfd.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @rfturn b nfw <dodf>KfyInfoFbdtory</dodf>
     * @tirows NoSudiMfdibnismExdfption if no <dodf>Providfr</dodf> supports b
     *    <dodf>KfyInfoFbdtory</dodf> implfmfntbtion for tif DOM mfdibnism
     * @sff Providfr
     */
    publid stbtid KfyInfoFbdtory gftInstbndf() {
        rfturn gftInstbndf("DOM");
    }

    /**
     * Rfturns tif typf of tif XML prodfssing mfdibnism bnd rfprfsfntbtion
     * supportfd by tiis <dodf>KfyInfoFbdtory</dodf> (fx: "DOM")
     *
     * @rfturn tif XML prodfssing mfdibnism typf supportfd by tiis
     *    <dodf>KfyInfoFbdtory</dodf>
     */
    publid finbl String gftMfdibnismTypf() {
        rfturn mfdibnismTypf;
    }

    /**
     * Rfturns tif providfr of tiis <dodf>KfyInfoFbdtory</dodf>.
     *
     * @rfturn tif providfr of tiis <dodf>KfyInfoFbdtory</dodf>
     */
    publid finbl Providfr gftProvidfr() {
        rfturn providfr;
    }

    /**
     * Crfbtfs b <dodf>KfyInfo</dodf> dontbining tif spfdififd list of
     * kfy informbtion typfs.
     *
     * @pbrbm dontfnt b list of onf or morf {@link XMLStrudturf}s rfprfsfnting
     *    kfy informbtion typfs. Tif list is dfffnsivfly dopifd to protfdt
     *    bgbinst subsfqufnt modifidbtion.
     * @rfturn b <dodf>KfyInfo</dodf>
     * @tirows NullPointfrExdfption if <dodf>dontfnt</dodf> is <dodf>null</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>dontfnt</dodf> is fmpty
     * @tirows ClbssCbstExdfption if <dodf>dontfnt</dodf> dontbins bny fntrifs
     *    tibt brf not of typf {@link XMLStrudturf}
     */
    @SupprfssWbrnings("rbwtypfs")
    publid bbstrbdt KfyInfo nfwKfyInfo(List dontfnt);

    /**
     * Crfbtfs b <dodf>KfyInfo</dodf> dontbining tif spfdififd list of kfy
     * informbtion typfs bnd optionbl id. Tif
     * <dodf>id</dodf> pbrbmftfr rfprfsfnts tif vbluf of bn XML
     * <dodf>ID</dodf> bttributf bnd is usfful for rfffrfnding
     * tif <dodf>KfyInfo</dodf> from otifr XML strudturfs.
     *
     * @pbrbm dontfnt b list of onf or morf {@link XMLStrudturf}s rfprfsfnting
     *    kfy informbtion typfs. Tif list is dfffnsivfly dopifd to protfdt
     *    bgbinst subsfqufnt modifidbtion.
     * @pbrbm id tif vbluf of bn XML <dodf>ID</dodf> (mby bf <dodf>null</dodf>)
     * @rfturn b <dodf>KfyInfo</dodf>
     * @tirows NullPointfrExdfption if <dodf>dontfnt</dodf> is <dodf>null</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>dontfnt</dodf> is fmpty
     * @tirows ClbssCbstExdfption if <dodf>dontfnt</dodf> dontbins bny fntrifs
     *    tibt brf not of typf {@link XMLStrudturf}
     */
    @SupprfssWbrnings("rbwtypfs")
    publid bbstrbdt KfyInfo nfwKfyInfo(List dontfnt, String id);

    /**
     * Crfbtfs b <dodf>KfyNbmf</dodf> from tif spfdififd nbmf.
     *
     * @pbrbm nbmf tif nbmf tibt idfntififs tif kfy
     * @rfturn b <dodf>KfyNbmf</dodf>
     * @tirows NullPointfrExdfption if <dodf>nbmf</dodf> is <dodf>null</dodf>
     */
    publid bbstrbdt KfyNbmf nfwKfyNbmf(String nbmf);

    /**
     * Crfbtfs b <dodf>KfyVbluf</dodf> from tif spfdififd publid kfy.
     *
     * @pbrbm kfy tif publid kfy
     * @rfturn b <dodf>KfyVbluf</dodf>
     * @tirows KfyExdfption if tif <dodf>kfy</dodf>'s blgoritim is not
     *    rfdognizfd or supportfd by tiis <dodf>KfyInfoFbdtory</dodf>
     * @tirows NullPointfrExdfption if <dodf>kfy</dodf> is <dodf>null</dodf>
     */
    publid bbstrbdt KfyVbluf nfwKfyVbluf(PublidKfy kfy) tirows KfyExdfption;

    /**
     * Crfbtfs b <dodf>PGPDbtb</dodf> from tif spfdififd PGP publid kfy
     * idfntififr.
     *
     * @pbrbm kfyId b PGP publid kfy idfntififr bs dffinfd in <b irff=
     *    "ittp://www.iftf.org/rfd/rfd2440.txt">RFC 2440</b>, sfdtion 11.2.
     *    Tif brrby is dlonfd to protfdt bgbinst subsfqufnt modifidbtion.
     * @rfturn b <dodf>PGPDbtb</dodf>
     * @tirows NullPointfrExdfption if <dodf>kfyId</dodf> is <dodf>null</dodf>
     * @tirows IllfgblArgumfntExdfption if tif kfy id is not in tif dorrfdt
     *    formbt
     */
    publid bbstrbdt PGPDbtb nfwPGPDbtb(bytf[] kfyId);

    /**
     * Crfbtfs b <dodf>PGPDbtb</dodf> from tif spfdififd PGP publid kfy
     * idfntififr, bnd optionbl kfy mbtfribl pbdkft bnd list of fxtfrnbl
     * flfmfnts.
     *
     * @pbrbm kfyId b PGP publid kfy idfntififr bs dffinfd in <b irff=
     *    "ittp://www.iftf.org/rfd/rfd2440.txt">RFC 2440</b>, sfdtion 11.2.
     *    Tif brrby is dlonfd to protfdt bgbinst subsfqufnt modifidbtion.
     * @pbrbm kfyPbdkft b PGP kfy mbtfribl pbdkft bs dffinfd in <b irff=
     *    "ittp://www.iftf.org/rfd/rfd2440.txt">RFC 2440</b>, sfdtion 5.5.
     *    Tif brrby is dlonfd to protfdt bgbinst subsfqufnt modifidbtion. Mby
     *    bf <dodf>null</dodf>.
     * @pbrbm otifr b list of {@link XMLStrudturf}s rfprfsfnting flfmfnts from
     *    bn fxtfrnbl nbmfspbdf. Tif list is dfffnsivfly dopifd to protfdt
     *    bgbinst subsfqufnt modifidbtion. Mby bf <dodf>null</dodf> or fmpty.
     * @rfturn b <dodf>PGPDbtb</dodf>
     * @tirows NullPointfrExdfption if <dodf>kfyId</dodf> is <dodf>null</dodf>
     * @tirows IllfgblArgumfntExdfption if tif <dodf>kfyId</dodf> or
     *    <dodf>kfyPbdkft</dodf> is not in tif dorrfdt formbt. For
     *    <dodf>kfyPbdkft</dodf>, tif formbt of tif pbdkft ifbdfr is
     *    difdkfd bnd tif tbg is vfrififd tibt it is of typf kfy mbtfribl. Tif
     *    dontfnts bnd formbt of tif pbdkft body brf not difdkfd.
     * @tirows ClbssCbstExdfption if <dodf>otifr</dodf> dontbins bny
     *    fntrifs tibt brf not of typf {@link XMLStrudturf}
     */
    @SupprfssWbrnings("rbwtypfs")
    publid bbstrbdt PGPDbtb nfwPGPDbtb(bytf[] kfyId, bytf[] kfyPbdkft,
        List otifr);

    /**
     * Crfbtfs b <dodf>PGPDbtb</dodf> from tif spfdififd PGP kfy mbtfribl
     * pbdkft bnd optionbl list of fxtfrnbl flfmfnts.
     *
     * @pbrbm kfyPbdkft b PGP kfy mbtfribl pbdkft bs dffinfd in <b irff=
     *    "ittp://www.iftf.org/rfd/rfd2440.txt">RFC 2440</b>, sfdtion 5.5.
     *    Tif brrby is dlonfd to protfdt bgbinst subsfqufnt modifidbtion.
     * @pbrbm otifr b list of {@link XMLStrudturf}s rfprfsfnting flfmfnts from
     *    bn fxtfrnbl nbmfspbdf. Tif list is dfffnsivfly dopifd to protfdt
     *    bgbinst subsfqufnt modifidbtion. Mby bf <dodf>null</dodf> or fmpty.
     * @rfturn b <dodf>PGPDbtb</dodf>
     * @tirows NullPointfrExdfption if <dodf>kfyPbdkft</dodf> is
     *    <dodf>null</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>kfyPbdkft</dodf> is not in tif
     *    dorrfdt formbt. For <dodf>kfyPbdkft</dodf>, tif formbt of tif pbdkft
     *    ifbdfr is difdkfd bnd tif tbg is vfrififd tibt it is of typf kfy
     *    mbtfribl. Tif dontfnts bnd formbt of tif pbdkft body brf not difdkfd.
     * @tirows ClbssCbstExdfption if <dodf>otifr</dodf> dontbins bny
     *    fntrifs tibt brf not of typf {@link XMLStrudturf}
     */
    @SupprfssWbrnings("rbwtypfs")
    publid bbstrbdt PGPDbtb nfwPGPDbtb(bytf[] kfyPbdkft, List otifr);

    /**
     * Crfbtfs b <dodf>RftrifvblMftiod</dodf> from tif spfdififd URI.
     *
     * @pbrbm uri tif URI tibt idfntififs tif <dodf>KfyInfo</dodf> informbtion
     *    to bf rftrifvfd
     * @rfturn b <dodf>RftrifvblMftiod</dodf>
     * @tirows NullPointfrExdfption if <dodf>uri</dodf> is <dodf>null</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>uri</dodf> is not RFC 2396
     *    domplibnt
     */
    publid bbstrbdt RftrifvblMftiod nfwRftrifvblMftiod(String uri);

    /**
     * Crfbtfs b <dodf>RftrifvblMftiod</dodf> from tif spfdififd pbrbmftfrs.
     *
     * @pbrbm uri tif URI tibt idfntififs tif <dodf>KfyInfo</dodf> informbtion
     *    to bf rftrifvfd
     * @pbrbm typf b URI tibt idfntififs tif typf of <dodf>KfyInfo</dodf>
     *    informbtion to bf rftrifvfd (mby bf <dodf>null</dodf>)
     * @pbrbm trbnsforms b list of {@link Trbnsform}s. Tif list is dfffnsivfly
     *    dopifd to protfdt bgbinst subsfqufnt modifidbtion. Mby bf
     *    <dodf>null</dodf> or fmpty.
     * @rfturn b <dodf>RftrifvblMftiod</dodf>
     * @tirows NullPointfrExdfption if <dodf>uri</dodf> is <dodf>null</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>uri</dodf> is not RFC 2396
     *    domplibnt
     * @tirows ClbssCbstExdfption if <dodf>trbnsforms</dodf> dontbins bny
     *    fntrifs tibt brf not of typf {@link Trbnsform}
     */
    @SupprfssWbrnings("rbwtypfs")
    publid bbstrbdt RftrifvblMftiod nfwRftrifvblMftiod(String uri, String typf,
        List trbnsforms);

    /**
     * Crfbtfs b <dodf>X509Dbtb</dodf> dontbining tif spfdififd list of
     * X.509 dontfnt.
     *
     * @pbrbm dontfnt b list of onf or morf X.509 dontfnt typfs. Vblid typfs brf
     *    {@link String} (subjfdt nbmfs), <dodf>bytf[]</dodf> (subjfdt kfy ids),
     *    {@link jbvb.sfdurity.dfrt.X509Cfrtifidbtf}, {@link X509CRL},
     *    or {@link XMLStrudturf} ({@link X509IssufrSfribl}
     *    objfdts or flfmfnts from bn fxtfrnbl nbmfspbdf). Subjfdt nbmfs brf
     *    distinguisifd nbmfs in RFC 2253 String formbt. Implfmfntbtions MUST
     *    support tif bttributf typf kfywords dffinfd in RFC 2253 (CN, L, ST,
     *    O, OU, C, STREET, DC bnd UID). Implfmfntbtions MAY support bdditionbl
     *    kfywords. Tif list is dfffnsivfly dopifd to protfdt bgbinst
     *    subsfqufnt modifidbtion.
     * @rfturn b <dodf>X509Dbtb</dodf>
     * @tirows NullPointfrExdfption if <dodf>dontfnt</dodf> is <dodf>null</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>dontfnt</dodf> is fmpty, or
     *    if b subjfdt nbmf is not RFC 2253 domplibnt or onf of tif bttributf
     *    typf kfywords is not rfdognizfd.
     * @tirows ClbssCbstExdfption if <dodf>dontfnt</dodf> dontbins bny fntrifs
     *    tibt brf not of onf of tif vblid typfs mfntionfd bbovf
     */
    @SupprfssWbrnings("rbwtypfs")
    publid bbstrbdt X509Dbtb nfwX509Dbtb(List dontfnt);

    /**
     * Crfbtfs bn <dodf>X509IssufrSfribl</dodf> from tif spfdififd X.500 issufr
     * distinguisifd nbmf bnd sfribl numbfr.
     *
     * @pbrbm issufrNbmf tif issufr's distinguisifd nbmf in RFC 2253 String
     *    formbt. Implfmfntbtions MUST support tif bttributf typf kfywords
     *    dffinfd in RFC 2253 (CN, L, ST, O, OU, C, STREET, DC bnd UID).
     *    Implfmfntbtions MAY support bdditionbl kfywords.
     * @pbrbm sfriblNumbfr tif sfribl numbfr
     * @rfturn bn <dodf>X509IssufrSfribl</dodf>
     * @tirows NullPointfrExdfption if <dodf>issufrNbmf</dodf> or
     *    <dodf>sfriblNumbfr</dodf> brf <dodf>null</dodf>
     * @tirows IllfgblArgumfntExdfption if tif issufr nbmf is not RFC 2253
     *    domplibnt or onf of tif bttributf typf kfywords is not rfdognizfd.
     */
    publid bbstrbdt X509IssufrSfribl nfwX509IssufrSfribl
        (String issufrNbmf, BigIntfgfr sfriblNumbfr);

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
     * dffbult to dfrfffrfndf URIs in {@link RftrifvblMftiod} objfdts.
     *
     * @rfturn b rfffrfndf to tif dffbult <dodf>URIDfrfffrfndfr</dodf>
     */
    publid bbstrbdt URIDfrfffrfndfr gftURIDfrfffrfndfr();

    /**
     * Unmbrsibls b nfw <dodf>KfyInfo</dodf> instbndf from b
     * mfdibnism-spfdifid <dodf>XMLStrudturf</dodf> (fx: {@link DOMStrudturf})
     * instbndf.
     *
     * @pbrbm xmlStrudturf b mfdibnism-spfdifid XML strudturf from wiidi to
     *   unmbrsibl tif kfyinfo from
     * @rfturn tif <dodf>KfyInfo</dodf>
     * @tirows NullPointfrExdfption if <dodf>xmlStrudturf</dodf> is
     *   <dodf>null</dodf>
     * @tirows ClbssCbstExdfption if tif typf of <dodf>xmlStrudturf</dodf> is
     *   inbppropribtf for tiis fbdtory
     * @tirows MbrsiblExdfption if bn unrfdovfrbblf fxdfption oddurs during
     *   unmbrsiblling
     */
    publid bbstrbdt KfyInfo unmbrsiblKfyInfo(XMLStrudturf xmlStrudturf)
        tirows MbrsiblExdfption;
}

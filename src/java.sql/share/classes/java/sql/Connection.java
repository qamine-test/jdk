/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sql;

import jbvb.util.Propfrtifs;
import jbvb.util.dondurrfnt.Exfdutor;

/**
 * <P>A donnfdtion (sfssion) witi b spfdifid
 * dbtbbbsf. SQL stbtfmfnts brf fxfdutfd bnd rfsults brf rfturnfd
 * witiin tif dontfxt of b donnfdtion.
 * <P>
 * A <dodf>Connfdtion</dodf> objfdt's dbtbbbsf is bblf to providf informbtion
 * dfsdribing its tbblfs, its supportfd SQL grbmmbr, its storfd
 * prodfdurfs, tif dbpbbilitifs of tiis donnfdtion, bnd so on. Tiis
 * informbtion is obtbinfd witi tif <dodf>gftMftbDbtb</dodf> mftiod.
 *
 * <P><B>Notf:</B> Wifn donfiguring b <dodf>Connfdtion</dodf>, JDBC bpplidbtions
 *  siould usf tif bppropribtf <dodf>Connfdtion</dodf> mftiod sudi bs
 *  <dodf>sftAutoCommit</dodf> or <dodf>sftTrbnsbdtionIsolbtion</dodf>.
 *  Applidbtions siould not invokf SQL dommbnds dirfdtly to dibngf tif donnfdtion's
 *   donfigurbtion wifn tifrf is b JDBC mftiod bvbilbblf.  By dffbult b <dodf>Connfdtion</dodf> objfdt is in
 * buto-dommit modf, wiidi mfbns tibt it butombtidblly dommits dibngfs
 * bftfr fxfduting fbdi stbtfmfnt. If buto-dommit modf ibs bffn
 * disbblfd, tif mftiod <dodf>dommit</dodf> must bf dbllfd fxpliditly in
 * ordfr to dommit dibngfs; otifrwisf, dbtbbbsf dibngfs will not bf sbvfd.
 * <P>
 * A nfw <dodf>Connfdtion</dodf> objfdt drfbtfd using tif JDBC 2.1 dorf API
 * ibs bn initiblly fmpty typf mbp bssodibtfd witi it. A usfr mby fntfr b
 * dustom mbpping for b UDT in tiis typf mbp.
 * Wifn b UDT is rftrifvfd from b dbtb sourdf witi tif
 * mftiod <dodf>RfsultSft.gftObjfdt</dodf>, tif <dodf>gftObjfdt</dodf> mftiod
 * will difdk tif donnfdtion's typf mbp to sff if tifrf is bn fntry for tibt
 * UDT.  If so, tif <dodf>gftObjfdt</dodf> mftiod will mbp tif UDT to tif
 * dlbss indidbtfd.  If tifrf is no fntry, tif UDT will bf mbppfd using tif
 * stbndbrd mbpping.
 * <p>
 * A usfr mby drfbtf b nfw typf mbp, wiidi is b <dodf>jbvb.util.Mbp</dodf>
 * objfdt, mbkf bn fntry in it, bnd pbss it to tif <dodf>jbvb.sql</dodf>
 * mftiods tibt dbn pfrform dustom mbpping.  In tiis dbsf, tif mftiod
 * will usf tif givfn typf mbp instfbd of tif onf bssodibtfd witi
 * tif donnfdtion.
 * <p>
 * For fxbmplf, tif following dodf frbgmfnt spfdififs tibt tif SQL
 * typf <dodf>ATHLETES</dodf> will bf mbppfd to tif dlbss
 * <dodf>Atilftfs</dodf> in tif Jbvb progrbmming lbngubgf.
 * Tif dodf frbgmfnt rftrifvfs tif typf mbp for tif <dodf>Connfdtion
 * </dodf> objfdt <dodf>don</dodf>, insfrts tif fntry into it, bnd tifn sfts
 * tif typf mbp witi tif nfw fntry bs tif donnfdtion's typf mbp.
 * <prf>
 *      jbvb.util.Mbp mbp = don.gftTypfMbp();
 *      mbp.put("mySdifmbNbmf.ATHLETES", Clbss.forNbmf("Atilftfs"));
 *      don.sftTypfMbp(mbp);
 * </prf>
 *
 * @sff DrivfrMbnbgfr#gftConnfdtion
 * @sff Stbtfmfnt
 * @sff RfsultSft
 * @sff DbtbbbsfMftbDbtb
 */
publid intfrfbdf Connfdtion  fxtfnds Wrbppfr, AutoClosfbblf {

    /**
     * Crfbtfs b <dodf>Stbtfmfnt</dodf> objfdt for sfnding
     * SQL stbtfmfnts to tif dbtbbbsf.
     * SQL stbtfmfnts witiout pbrbmftfrs brf normblly
     * fxfdutfd using <dodf>Stbtfmfnt</dodf> objfdts. If tif sbmf SQL stbtfmfnt
     * is fxfdutfd mbny timfs, it mby bf morf fffidifnt to usf b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt.
     * <P>
     * Rfsult sfts drfbtfd using tif rfturnfd <dodf>Stbtfmfnt</dodf>
     * objfdt will by dffbult bf typf <dodf>TYPE_FORWARD_ONLY</dodf>
     * bnd ibvf b dondurrfndy lfvfl of <dodf>CONCUR_READ_ONLY</dodf>.
     * Tif ioldbbility of tif drfbtfd rfsult sfts dbn bf dftfrminfd by
     * dblling {@link #gftHoldbbility}.
     *
     * @rfturn b nfw dffbult <dodf>Stbtfmfnt</dodf> objfdt
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd donnfdtion
     */
    Stbtfmfnt drfbtfStbtfmfnt() tirows SQLExdfption;

    /**
     * Crfbtfs b <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt for sfnding
     * pbrbmftfrizfd SQL stbtfmfnts to tif dbtbbbsf.
     * <P>
     * A SQL stbtfmfnt witi or witiout IN pbrbmftfrs dbn bf
     * prf-dompilfd bnd storfd in b <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt. Tiis
     * objfdt dbn tifn bf usfd to fffidifntly fxfdutf tiis stbtfmfnt
     * multiplf timfs.
     *
     * <P><B>Notf:</B> Tiis mftiod is optimizfd for ibndling
     * pbrbmftrid SQL stbtfmfnts tibt bfnffit from prfdompilbtion. If
     * tif drivfr supports prfdompilbtion,
     * tif mftiod <dodf>prfpbrfStbtfmfnt</dodf> will sfnd
     * tif stbtfmfnt to tif dbtbbbsf for prfdompilbtion. Somf drivfrs
     * mby not support prfdompilbtion. In tiis dbsf, tif stbtfmfnt mby
     * not bf sfnt to tif dbtbbbsf until tif <dodf>PrfpbrfdStbtfmfnt</dodf>
     * objfdt is fxfdutfd.  Tiis ibs no dirfdt ffffdt on usfrs; iowfvfr, it dofs
     * bfffdt wiidi mftiods tirow dfrtbin <dodf>SQLExdfption</dodf> objfdts.
     * <P>
     * Rfsult sfts drfbtfd using tif rfturnfd <dodf>PrfpbrfdStbtfmfnt</dodf>
     * objfdt will by dffbult bf typf <dodf>TYPE_FORWARD_ONLY</dodf>
     * bnd ibvf b dondurrfndy lfvfl of <dodf>CONCUR_READ_ONLY</dodf>.
     * Tif ioldbbility of tif drfbtfd rfsult sfts dbn bf dftfrminfd by
     * dblling {@link #gftHoldbbility}.
     *
     * @pbrbm sql bn SQL stbtfmfnt tibt mby dontbin onf or morf '?' IN
     * pbrbmftfr plbdfioldfrs
     * @rfturn b nfw dffbult <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt dontbining tif
     * prf-dompilfd SQL stbtfmfnt
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd donnfdtion
     */
    PrfpbrfdStbtfmfnt prfpbrfStbtfmfnt(String sql)
        tirows SQLExdfption;

    /**
     * Crfbtfs b <dodf>CbllbblfStbtfmfnt</dodf> objfdt for dblling
     * dbtbbbsf storfd prodfdurfs.
     * Tif <dodf>CbllbblfStbtfmfnt</dodf> objfdt providfs
     * mftiods for sftting up its IN bnd OUT pbrbmftfrs, bnd
     * mftiods for fxfduting tif dbll to b storfd prodfdurf.
     *
     * <P><B>Notf:</B> Tiis mftiod is optimizfd for ibndling storfd
     * prodfdurf dbll stbtfmfnts. Somf drivfrs mby sfnd tif dbll
     * stbtfmfnt to tif dbtbbbsf wifn tif mftiod <dodf>prfpbrfCbll</dodf>
     * is donf; otifrs
     * mby wbit until tif <dodf>CbllbblfStbtfmfnt</dodf> objfdt
     * is fxfdutfd. Tiis ibs no
     * dirfdt ffffdt on usfrs; iowfvfr, it dofs bfffdt wiidi mftiod
     * tirows dfrtbin SQLExdfptions.
     * <P>
     * Rfsult sfts drfbtfd using tif rfturnfd <dodf>CbllbblfStbtfmfnt</dodf>
     * objfdt will by dffbult bf typf <dodf>TYPE_FORWARD_ONLY</dodf>
     * bnd ibvf b dondurrfndy lfvfl of <dodf>CONCUR_READ_ONLY</dodf>.
     * Tif ioldbbility of tif drfbtfd rfsult sfts dbn bf dftfrminfd by
     * dblling {@link #gftHoldbbility}.
     *
     * @pbrbm sql bn SQL stbtfmfnt tibt mby dontbin onf or morf '?'
     * pbrbmftfr plbdfioldfrs. Typidblly tiis stbtfmfnt is spfdififd using JDBC
     * dbll fsdbpf syntbx.
     * @rfturn b nfw dffbult <dodf>CbllbblfStbtfmfnt</dodf> objfdt dontbining tif
     * prf-dompilfd SQL stbtfmfnt
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd donnfdtion
     */
    CbllbblfStbtfmfnt prfpbrfCbll(String sql) tirows SQLExdfption;

    /**
     * Convfrts tif givfn SQL stbtfmfnt into tif systfm's nbtivf SQL grbmmbr.
     * A drivfr mby donvfrt tif JDBC SQL grbmmbr into its systfm's
     * nbtivf SQL grbmmbr prior to sfnding it. Tiis mftiod rfturns tif
     * nbtivf form of tif stbtfmfnt tibt tif drivfr would ibvf sfnt.
     *
     * @pbrbm sql bn SQL stbtfmfnt tibt mby dontbin onf or morf '?'
     * pbrbmftfr plbdfioldfrs
     * @rfturn tif nbtivf form of tiis stbtfmfnt
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd donnfdtion
     */
    String nbtivfSQL(String sql) tirows SQLExdfption;

    /**
     * Sfts tiis donnfdtion's buto-dommit modf to tif givfn stbtf.
     * If b donnfdtion is in buto-dommit modf, tifn bll its SQL
     * stbtfmfnts will bf fxfdutfd bnd dommittfd bs individubl
     * trbnsbdtions.  Otifrwisf, its SQL stbtfmfnts brf groupfd into
     * trbnsbdtions tibt brf tfrminbtfd by b dbll to fitifr
     * tif mftiod <dodf>dommit</dodf> or tif mftiod <dodf>rollbbdk</dodf>.
     * By dffbult, nfw donnfdtions brf in buto-dommit
     * modf.
     * <P>
     * Tif dommit oddurs wifn tif stbtfmfnt domplftfs. Tif timf wifn tif stbtfmfnt
     * domplftfs dfpfnds on tif typf of SQL Stbtfmfnt:
     * <ul>
     * <li>For DML stbtfmfnts, sudi bs Insfrt, Updbtf or Dflftf, bnd DDL stbtfmfnts,
     * tif stbtfmfnt is domplftf bs soon bs it ibs finisifd fxfduting.
     * <li>For Sflfdt stbtfmfnts, tif stbtfmfnt is domplftf wifn tif bssodibtfd rfsult
     * sft is dlosfd.
     * <li>For <dodf>CbllbblfStbtfmfnt</dodf> objfdts or for stbtfmfnts tibt rfturn
     * multiplf rfsults, tif stbtfmfnt is domplftf
     * wifn bll of tif bssodibtfd rfsult sfts ibvf bffn dlosfd, bnd bll updbtf
     * dounts bnd output pbrbmftfrs ibvf bffn rftrifvfd.
     *</ul>
     * <P>
     * <B>NOTE:</B>  If tiis mftiod is dbllfd during b trbnsbdtion bnd tif
     * buto-dommit modf is dibngfd, tif trbnsbdtion is dommittfd.  If
     * <dodf>sftAutoCommit</dodf> is dbllfd bnd tif buto-dommit modf is
     * not dibngfd, tif dbll is b no-op.
     *
     * @pbrbm butoCommit <dodf>truf</dodf> to fnbblf buto-dommit modf;
     *         <dodf>fblsf</dodf> to disbblf it
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     *  sftAutoCommit(truf) is dbllfd wiilf pbrtidipbting in b distributfd trbnsbdtion,
     * or tiis mftiod is dbllfd on b dlosfd donnfdtion
     * @sff #gftAutoCommit
     */
    void sftAutoCommit(boolfbn butoCommit) tirows SQLExdfption;

    /**
     * Rftrifvfs tif durrfnt buto-dommit modf for tiis <dodf>Connfdtion</dodf>
     * objfdt.
     *
     * @rfturn tif durrfnt stbtf of tiis <dodf>Connfdtion</dodf> objfdt's
     *         buto-dommit modf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd donnfdtion
     * @sff #sftAutoCommit
     */
    boolfbn gftAutoCommit() tirows SQLExdfption;

    /**
     * Mbkfs bll dibngfs mbdf sindf tif prfvious
     * dommit/rollbbdk pfrmbnfnt bnd rflfbsfs bny dbtbbbsf lodks
     * durrfntly ifld by tiis <dodf>Connfdtion</dodf> objfdt.
     * Tiis mftiod siould bf
     * usfd only wifn buto-dommit modf ibs bffn disbblfd.
     *
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tiis mftiod is dbllfd wiilf pbrtidipbting in b distributfd trbnsbdtion,
     * if tiis mftiod is dbllfd on b dlosfd donnfdtion or tiis
     *            <dodf>Connfdtion</dodf> objfdt is in buto-dommit modf
     * @sff #sftAutoCommit
     */
    void dommit() tirows SQLExdfption;

    /**
     * Undofs bll dibngfs mbdf in tif durrfnt trbnsbdtion
     * bnd rflfbsfs bny dbtbbbsf lodks durrfntly ifld
     * by tiis <dodf>Connfdtion</dodf> objfdt. Tiis mftiod siould bf
     * usfd only wifn buto-dommit modf ibs bffn disbblfd.
     *
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tiis mftiod is dbllfd wiilf pbrtidipbting in b distributfd trbnsbdtion,
     * tiis mftiod is dbllfd on b dlosfd donnfdtion or tiis
     *            <dodf>Connfdtion</dodf> objfdt is in buto-dommit modf
     * @sff #sftAutoCommit
     */
    void rollbbdk() tirows SQLExdfption;

    /**
     * Rflfbsfs tiis <dodf>Connfdtion</dodf> objfdt's dbtbbbsf bnd JDBC rfsourdfs
     * immfdibtfly instfbd of wbiting for tifm to bf butombtidblly rflfbsfd.
     * <P>
     * Cblling tif mftiod <dodf>dlosf</dodf> on b <dodf>Connfdtion</dodf>
     * objfdt tibt is blrfbdy dlosfd is b no-op.
     * <P>
     * It is <b>strongly rfdommfndfd</b> tibt bn bpplidbtion fxpliditly
     * dommits or rolls bbdk bn bdtivf trbnsbdtion prior to dblling tif
     * <dodf>dlosf</dodf> mftiod.  If tif <dodf>dlosf</dodf> mftiod is dbllfd
     * bnd tifrf is bn bdtivf trbnsbdtion, tif rfsults brf implfmfntbtion-dffinfd.
     *
     * @fxdfption SQLExdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    void dlosf() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis <dodf>Connfdtion</dodf> objfdt ibs bffn
     * dlosfd.  A donnfdtion is dlosfd if tif mftiod <dodf>dlosf</dodf>
     * ibs bffn dbllfd on it or if dfrtbin fbtbl frrors ibvf oddurrfd.
     * Tiis mftiod is gubrbntffd to rfturn <dodf>truf</dodf> only wifn
     * it is dbllfd bftfr tif mftiod <dodf>Connfdtion.dlosf</dodf> ibs
     * bffn dbllfd.
     * <P>
     * Tiis mftiod gfnfrblly dbnnot bf dbllfd to dftfrminf wiftifr b
     * donnfdtion to b dbtbbbsf is vblid or invblid.  A typidbl dlifnt
     * dbn dftfrminf tibt b donnfdtion is invblid by dbtdiing bny
     * fxdfptions tibt migit bf tirown wifn bn opfrbtion is bttfmptfd.
     *
     * @rfturn <dodf>truf</dodf> if tiis <dodf>Connfdtion</dodf> objfdt
     *         is dlosfd; <dodf>fblsf</dodf> if it is still opfn
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn isClosfd() tirows SQLExdfption;

    //======================================================================
    // Advbndfd ffbturfs:

    /**
     * Rftrifvfs b <dodf>DbtbbbsfMftbDbtb</dodf> objfdt tibt dontbins
     * mftbdbtb bbout tif dbtbbbsf to wiidi tiis
     * <dodf>Connfdtion</dodf> objfdt rfprfsfnts b donnfdtion.
     * Tif mftbdbtb indludfs informbtion bbout tif dbtbbbsf's
     * tbblfs, its supportfd SQL grbmmbr, its storfd
     * prodfdurfs, tif dbpbbilitifs of tiis donnfdtion, bnd so on.
     *
     * @rfturn b <dodf>DbtbbbsfMftbDbtb</dodf> objfdt for tiis
     *         <dodf>Connfdtion</dodf> objfdt
     * @fxdfption  SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd donnfdtion
     */
    DbtbbbsfMftbDbtb gftMftbDbtb() tirows SQLExdfption;

    /**
     * Puts tiis donnfdtion in rfbd-only modf bs b iint to tif drivfr to fnbblf
     * dbtbbbsf optimizbtions.
     *
     * <P><B>Notf:</B> Tiis mftiod dbnnot bf dbllfd during b trbnsbdtion.
     *
     * @pbrbm rfbdOnly <dodf>truf</dodf> fnbblfs rfbd-only modf;
     *        <dodf>fblsf</dodf> disbblfs it
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs, tiis
     *  mftiod is dbllfd on b dlosfd donnfdtion or tiis
     *            mftiod is dbllfd during b trbnsbdtion
     */
    void sftRfbdOnly(boolfbn rfbdOnly) tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis <dodf>Connfdtion</dodf>
     * objfdt is in rfbd-only modf.
     *
     * @rfturn <dodf>truf</dodf> if tiis <dodf>Connfdtion</dodf> objfdt
     *         is rfbd-only; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd donnfdtion
     */
    boolfbn isRfbdOnly() tirows SQLExdfption;

    /**
     * Sfts tif givfn dbtblog nbmf in ordfr to sflfdt
     * b subspbdf of tiis <dodf>Connfdtion</dodf> objfdt's dbtbbbsf
     * in wiidi to work.
     * <P>
     * If tif drivfr dofs not support dbtblogs, it will
     * silfntly ignorf tiis rfqufst.
     * <p>
     * Cblling {@dodf sftCbtblog} ibs no ffffdt on prfviously drfbtfd or prfpbrfd
     * {@dodf Stbtfmfnt} objfdts. It is implfmfntbtion dffinfd wiftifr b DBMS
     * prfpbrf opfrbtion tbkfs plbdf immfdibtfly wifn tif {@dodf Connfdtion}
     * mftiod {@dodf prfpbrfStbtfmfnt} or {@dodf prfpbrfCbll} is invokfd.
     * For mbximum portbbility, {@dodf sftCbtblog} siould bf dbllfd bfforf b
     * {@dodf Stbtfmfnt} is drfbtfd or prfpbrfd.
     *
     * @pbrbm dbtblog tif nbmf of b dbtblog (subspbdf in tiis
     *        <dodf>Connfdtion</dodf> objfdt's dbtbbbsf) in wiidi to work
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd donnfdtion
     * @sff #gftCbtblog
     */
    void sftCbtblog(String dbtblog) tirows SQLExdfption;

    /**
     * Rftrifvfs tiis <dodf>Connfdtion</dodf> objfdt's durrfnt dbtblog nbmf.
     *
     * @rfturn tif durrfnt dbtblog nbmf or <dodf>null</dodf> if tifrf is nonf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd donnfdtion
     * @sff #sftCbtblog
     */
    String gftCbtblog() tirows SQLExdfption;

    /**
     * A donstbnt indidbting tibt trbnsbdtions brf not supportfd.
     */
    int TRANSACTION_NONE             = 0;

    /**
     * A donstbnt indidbting tibt
     * dirty rfbds, non-rfpfbtbblf rfbds bnd pibntom rfbds dbn oddur.
     * Tiis lfvfl bllows b row dibngfd by onf trbnsbdtion to bf rfbd
     * by bnotifr trbnsbdtion bfforf bny dibngfs in tibt row ibvf bffn
     * dommittfd (b "dirty rfbd").  If bny of tif dibngfs brf rollfd bbdk,
     * tif sfdond trbnsbdtion will ibvf rftrifvfd bn invblid row.
     */
    int TRANSACTION_READ_UNCOMMITTED = 1;

    /**
     * A donstbnt indidbting tibt
     * dirty rfbds brf prfvfntfd; non-rfpfbtbblf rfbds bnd pibntom
     * rfbds dbn oddur.  Tiis lfvfl only proiibits b trbnsbdtion
     * from rfbding b row witi undommittfd dibngfs in it.
     */
    int TRANSACTION_READ_COMMITTED   = 2;

    /**
     * A donstbnt indidbting tibt
     * dirty rfbds bnd non-rfpfbtbblf rfbds brf prfvfntfd; pibntom
     * rfbds dbn oddur.  Tiis lfvfl proiibits b trbnsbdtion from
     * rfbding b row witi undommittfd dibngfs in it, bnd it blso
     * proiibits tif situbtion wifrf onf trbnsbdtion rfbds b row,
     * b sfdond trbnsbdtion bltfrs tif row, bnd tif first trbnsbdtion
     * rfrfbds tif row, gftting difffrfnt vblufs tif sfdond timf
     * (b "non-rfpfbtbblf rfbd").
     */
    int TRANSACTION_REPEATABLE_READ  = 4;

    /**
     * A donstbnt indidbting tibt
     * dirty rfbds, non-rfpfbtbblf rfbds bnd pibntom rfbds brf prfvfntfd.
     * Tiis lfvfl indludfs tif proiibitions in
     * <dodf>TRANSACTION_REPEATABLE_READ</dodf> bnd furtifr proiibits tif
     * situbtion wifrf onf trbnsbdtion rfbds bll rows tibt sbtisfy
     * b <dodf>WHERE</dodf> dondition, b sfdond trbnsbdtion insfrts b row tibt
     * sbtisfifs tibt <dodf>WHERE</dodf> dondition, bnd tif first trbnsbdtion
     * rfrfbds for tif sbmf dondition, rftrifving tif bdditionbl
     * "pibntom" row in tif sfdond rfbd.
     */
    int TRANSACTION_SERIALIZABLE     = 8;

    /**
     * Attfmpts to dibngf tif trbnsbdtion isolbtion lfvfl for tiis
     * <dodf>Connfdtion</dodf> objfdt to tif onf givfn.
     * Tif donstbnts dffinfd in tif intfrfbdf <dodf>Connfdtion</dodf>
     * brf tif possiblf trbnsbdtion isolbtion lfvfls.
     * <P>
     * <B>Notf:</B> If tiis mftiod is dbllfd during b trbnsbdtion, tif rfsult
     * is implfmfntbtion-dffinfd.
     *
     * @pbrbm lfvfl onf of tif following <dodf>Connfdtion</dodf> donstbnts:
     *        <dodf>Connfdtion.TRANSACTION_READ_UNCOMMITTED</dodf>,
     *        <dodf>Connfdtion.TRANSACTION_READ_COMMITTED</dodf>,
     *        <dodf>Connfdtion.TRANSACTION_REPEATABLE_READ</dodf>, or
     *        <dodf>Connfdtion.TRANSACTION_SERIALIZABLE</dodf>.
     *        (Notf tibt <dodf>Connfdtion.TRANSACTION_NONE</dodf> dbnnot bf usfd
     *        bfdbusf it spfdififs tibt trbnsbdtions brf not supportfd.)
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs, tiis
     * mftiod is dbllfd on b dlosfd donnfdtion
     *            or tif givfn pbrbmftfr is not onf of tif <dodf>Connfdtion</dodf>
     *            donstbnts
     * @sff DbtbbbsfMftbDbtb#supportsTrbnsbdtionIsolbtionLfvfl
     * @sff #gftTrbnsbdtionIsolbtion
     */
    void sftTrbnsbdtionIsolbtion(int lfvfl) tirows SQLExdfption;

    /**
     * Rftrifvfs tiis <dodf>Connfdtion</dodf> objfdt's durrfnt
     * trbnsbdtion isolbtion lfvfl.
     *
     * @rfturn tif durrfnt trbnsbdtion isolbtion lfvfl, wiidi will bf onf
     *         of tif following donstbnts:
     *        <dodf>Connfdtion.TRANSACTION_READ_UNCOMMITTED</dodf>,
     *        <dodf>Connfdtion.TRANSACTION_READ_COMMITTED</dodf>,
     *        <dodf>Connfdtion.TRANSACTION_REPEATABLE_READ</dodf>,
     *        <dodf>Connfdtion.TRANSACTION_SERIALIZABLE</dodf>, or
     *        <dodf>Connfdtion.TRANSACTION_NONE</dodf>.
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd donnfdtion
     * @sff #sftTrbnsbdtionIsolbtion
     */
    int gftTrbnsbdtionIsolbtion() tirows SQLExdfption;

    /**
     * Rftrifvfs tif first wbrning rfportfd by dblls on tiis
     * <dodf>Connfdtion</dodf> objfdt.  If tifrf is morf tibn onf
     * wbrning, subsfqufnt wbrnings will bf dibinfd to tif first onf
     * bnd dbn bf rftrifvfd by dblling tif mftiod
     * <dodf>SQLWbrning.gftNfxtWbrning</dodf> on tif wbrning
     * tibt wbs rftrifvfd prfviously.
     * <P>
     * Tiis mftiod mby not bf
     * dbllfd on b dlosfd donnfdtion; doing so will dbusf bn
     * <dodf>SQLExdfption</dodf> to bf tirown.
     *
     * <P><B>Notf:</B> Subsfqufnt wbrnings will bf dibinfd to tiis
     * SQLWbrning.
     *
     * @rfturn tif first <dodf>SQLWbrning</dodf> objfdt or <dodf>null</dodf>
     *         if tifrf brf nonf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
     *            tiis mftiod is dbllfd on b dlosfd donnfdtion
     * @sff SQLWbrning
     */
    SQLWbrning gftWbrnings() tirows SQLExdfption;

    /**
     * Clfbrs bll wbrnings rfportfd for tiis <dodf>Connfdtion</dodf> objfdt.
     * Aftfr b dbll to tiis mftiod, tif mftiod <dodf>gftWbrnings</dodf>
     * rfturns <dodf>null</dodf> until b nfw wbrning is
     * rfportfd for tiis <dodf>Connfdtion</dodf> objfdt.
     *
     * @fxdfption SQLExdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd donnfdtion
     */
    void dlfbrWbrnings() tirows SQLExdfption;


    //--------------------------JDBC 2.0-----------------------------

    /**
     * Crfbtfs b <dodf>Stbtfmfnt</dodf> objfdt tibt will gfnfrbtf
     * <dodf>RfsultSft</dodf> objfdts witi tif givfn typf bnd dondurrfndy.
     * Tiis mftiod is tif sbmf bs tif <dodf>drfbtfStbtfmfnt</dodf> mftiod
     * bbovf, but it bllows tif dffbult rfsult sft
     * typf bnd dondurrfndy to bf ovfrriddfn.
     * Tif ioldbbility of tif drfbtfd rfsult sfts dbn bf dftfrminfd by
     * dblling {@link #gftHoldbbility}.
     *
     * @pbrbm rfsultSftTypf b rfsult sft typf; onf of
     *        <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>,
     *        <dodf>RfsultSft.TYPE_SCROLL_INSENSITIVE</dodf>, or
     *        <dodf>RfsultSft.TYPE_SCROLL_SENSITIVE</dodf>
     * @pbrbm rfsultSftCondurrfndy b dondurrfndy typf; onf of
     *        <dodf>RfsultSft.CONCUR_READ_ONLY</dodf> or
     *        <dodf>RfsultSft.CONCUR_UPDATABLE</dodf>
     * @rfturn b nfw <dodf>Stbtfmfnt</dodf> objfdt tibt will gfnfrbtf
     *         <dodf>RfsultSft</dodf> objfdts witi tif givfn typf bnd
     *         dondurrfndy
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs, tiis
     * mftiod is dbllfd on b dlosfd donnfdtion
     *         or tif givfn pbrbmftfrs brf not <dodf>RfsultSft</dodf>
     *         donstbnts indidbting typf bnd dondurrfndy
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod or tiis mftiod is not supportfd for tif spfdififd rfsult
     * sft typf bnd rfsult sft dondurrfndy.
     * @sindf 1.2
     */
    Stbtfmfnt drfbtfStbtfmfnt(int rfsultSftTypf, int rfsultSftCondurrfndy)
        tirows SQLExdfption;

    /**
     *
     * Crfbtfs b <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt tibt will gfnfrbtf
     * <dodf>RfsultSft</dodf> objfdts witi tif givfn typf bnd dondurrfndy.
     * Tiis mftiod is tif sbmf bs tif <dodf>prfpbrfStbtfmfnt</dodf> mftiod
     * bbovf, but it bllows tif dffbult rfsult sft
     * typf bnd dondurrfndy to bf ovfrriddfn.
     * Tif ioldbbility of tif drfbtfd rfsult sfts dbn bf dftfrminfd by
     * dblling {@link #gftHoldbbility}.
     *
     * @pbrbm sql b <dodf>String</dodf> objfdt tibt is tif SQL stbtfmfnt to
     *            bf sfnt to tif dbtbbbsf; mby dontbin onf or morf '?' IN
     *            pbrbmftfrs
     * @pbrbm rfsultSftTypf b rfsult sft typf; onf of
     *         <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>,
     *         <dodf>RfsultSft.TYPE_SCROLL_INSENSITIVE</dodf>, or
     *         <dodf>RfsultSft.TYPE_SCROLL_SENSITIVE</dodf>
     * @pbrbm rfsultSftCondurrfndy b dondurrfndy typf; onf of
     *         <dodf>RfsultSft.CONCUR_READ_ONLY</dodf> or
     *         <dodf>RfsultSft.CONCUR_UPDATABLE</dodf>
     * @rfturn b nfw PrfpbrfdStbtfmfnt objfdt dontbining tif
     * prf-dompilfd SQL stbtfmfnt tibt will produdf <dodf>RfsultSft</dodf>
     * objfdts witi tif givfn typf bnd dondurrfndy
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs, tiis
     * mftiod is dbllfd on b dlosfd donnfdtion
     *         or tif givfn pbrbmftfrs brf not <dodf>RfsultSft</dodf>
     *         donstbnts indidbting typf bnd dondurrfndy
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod or tiis mftiod is not supportfd for tif spfdififd rfsult
     * sft typf bnd rfsult sft dondurrfndy.
     * @sindf 1.2
     */
    PrfpbrfdStbtfmfnt prfpbrfStbtfmfnt(String sql, int rfsultSftTypf,
                                       int rfsultSftCondurrfndy)
        tirows SQLExdfption;

    /**
     * Crfbtfs b <dodf>CbllbblfStbtfmfnt</dodf> objfdt tibt will gfnfrbtf
     * <dodf>RfsultSft</dodf> objfdts witi tif givfn typf bnd dondurrfndy.
     * Tiis mftiod is tif sbmf bs tif <dodf>prfpbrfCbll</dodf> mftiod
     * bbovf, but it bllows tif dffbult rfsult sft
     * typf bnd dondurrfndy to bf ovfrriddfn.
     * Tif ioldbbility of tif drfbtfd rfsult sfts dbn bf dftfrminfd by
     * dblling {@link #gftHoldbbility}.
     *
     * @pbrbm sql b <dodf>String</dodf> objfdt tibt is tif SQL stbtfmfnt to
     *            bf sfnt to tif dbtbbbsf; mby dontbin on or morf '?' pbrbmftfrs
     * @pbrbm rfsultSftTypf b rfsult sft typf; onf of
     *         <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>,
     *         <dodf>RfsultSft.TYPE_SCROLL_INSENSITIVE</dodf>, or
     *         <dodf>RfsultSft.TYPE_SCROLL_SENSITIVE</dodf>
     * @pbrbm rfsultSftCondurrfndy b dondurrfndy typf; onf of
     *         <dodf>RfsultSft.CONCUR_READ_ONLY</dodf> or
     *         <dodf>RfsultSft.CONCUR_UPDATABLE</dodf>
     * @rfturn b nfw <dodf>CbllbblfStbtfmfnt</dodf> objfdt dontbining tif
     * prf-dompilfd SQL stbtfmfnt tibt will produdf <dodf>RfsultSft</dodf>
     * objfdts witi tif givfn typf bnd dondurrfndy
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs, tiis mftiod
     * is dbllfd on b dlosfd donnfdtion
     *         or tif givfn pbrbmftfrs brf not <dodf>RfsultSft</dodf>
     *         donstbnts indidbting typf bnd dondurrfndy
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod or tiis mftiod is not supportfd for tif spfdififd rfsult
     * sft typf bnd rfsult sft dondurrfndy.
     * @sindf 1.2
     */
    CbllbblfStbtfmfnt prfpbrfCbll(String sql, int rfsultSftTypf,
                                  int rfsultSftCondurrfndy) tirows SQLExdfption;

    /**
     * Rftrifvfs tif <dodf>Mbp</dodf> objfdt bssodibtfd witi tiis
     * <dodf>Connfdtion</dodf> objfdt.
     * Unlfss tif bpplidbtion ibs bddfd bn fntry, tif typf mbp rfturnfd
     * will bf fmpty.
     * <p>
     * You must invokf <dodf>sftTypfMbp</dodf> bftfr mbking dibngfs to tif
     * <dodf>Mbp</dodf> objfdt rfturnfd from
     *  <dodf>gftTypfMbp</dodf> bs b JDBC drivfr mby drfbtf bn intfrnbl
     * dopy of tif <dodf>Mbp</dodf> objfdt pbssfd to <dodf>sftTypfMbp</dodf>:
     *
     * <prf>
     *      Mbp&lt;String,Clbss&lt;?&gt;&gt; myMbp = don.gftTypfMbp();
     *      myMbp.put("mySdifmbNbmf.ATHLETES", Atilftfs.dlbss);
     *      don.sftTypfMbp(myMbp);
     * </prf>
     * @rfturn tif <dodf>jbvb.util.Mbp</dodf> objfdt bssodibtfd
     *         witi tiis <dodf>Connfdtion</dodf> objfdt
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd donnfdtion
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     * @sff #sftTypfMbp
     */
    jbvb.util.Mbp<String,Clbss<?>> gftTypfMbp() tirows SQLExdfption;

    /**
     * Instblls tif givfn <dodf>TypfMbp</dodf> objfdt bs tif typf mbp for
     * tiis <dodf>Connfdtion</dodf> objfdt.  Tif typf mbp will bf usfd for tif
     * dustom mbpping of SQL strudturfd typfs bnd distindt typfs.
     * <p>
     * You must sft tif tif vblufs for tif <dodf>TypfMbp</dodf> prior to
     * dbllng <dodf>sftMbp</dodf> bs b JDBC drivfr mby drfbtf bn intfrnbl dopy
     * of tif <dodf>TypfMbp</dodf>:
     *
     * <prf>
     *      Mbp myMbp&lt;String,Clbss&lt;?&gt;&gt; = nfw HbsiMbp&lt;String,Clbss&lt;?&gt;&gt;();
     *      myMbp.put("mySdifmbNbmf.ATHLETES", Atilftfs.dlbss);
     *      don.sftTypfMbp(myMbp);
     * </prf>
     * @pbrbm mbp tif <dodf>jbvb.util.Mbp</dodf> objfdt to instbll
     *        bs tif rfplbdfmfnt for tiis <dodf>Connfdtion</dodf>
     *        objfdt's dffbult typf mbp
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs, tiis
     * mftiod is dbllfd on b dlosfd donnfdtion or
     *        tif givfn pbrbmftfr is not b <dodf>jbvb.util.Mbp</dodf>
     *        objfdt
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     * @sff #gftTypfMbp
     */
    void sftTypfMbp(jbvb.util.Mbp<String,Clbss<?>> mbp) tirows SQLExdfption;

    //--------------------------JDBC 3.0-----------------------------


    /**
     * Cibngfs tif dffbult ioldbbility of <dodf>RfsultSft</dodf> objfdts
     * drfbtfd using tiis <dodf>Connfdtion</dodf> objfdt to tif givfn
     * ioldbbility.  Tif dffbult ioldbbility of <dodf>RfsultSft</dodf> objfdts
     * dbn bf bf dftfrminfd by invoking
     * {@link DbtbbbsfMftbDbtb#gftRfsultSftHoldbbility}.
     *
     * @pbrbm ioldbbility b <dodf>RfsultSft</dodf> ioldbbility donstbnt; onf of
     *        <dodf>RfsultSft.HOLD_CURSORS_OVER_COMMIT</dodf> or
     *        <dodf>RfsultSft.CLOSE_CURSORS_AT_COMMIT</dodf>
     * @tirows SQLExdfption if b dbtbbbsf bddfss oddurs, tiis mftiod is dbllfd
     * on b dlosfd donnfdtion, or tif givfn pbrbmftfr
     *         is not b <dodf>RfsultSft</dodf> donstbnt indidbting ioldbbility
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif givfn ioldbbility is not supportfd
     * @sff #gftHoldbbility
     * @sff DbtbbbsfMftbDbtb#gftRfsultSftHoldbbility
     * @sff RfsultSft
     * @sindf 1.4
     */
    void sftHoldbbility(int ioldbbility) tirows SQLExdfption;

    /**
     * Rftrifvfs tif durrfnt ioldbbility of <dodf>RfsultSft</dodf> objfdts
     * drfbtfd using tiis <dodf>Connfdtion</dodf> objfdt.
     *
     * @rfturn tif ioldbbility, onf of
     *        <dodf>RfsultSft.HOLD_CURSORS_OVER_COMMIT</dodf> or
     *        <dodf>RfsultSft.CLOSE_CURSORS_AT_COMMIT</dodf>
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd donnfdtion
     * @sff #sftHoldbbility
     * @sff DbtbbbsfMftbDbtb#gftRfsultSftHoldbbility
     * @sff RfsultSft
     * @sindf 1.4
     */
    int gftHoldbbility() tirows SQLExdfption;

    /**
     * Crfbtfs bn unnbmfd sbvfpoint in tif durrfnt trbnsbdtion bnd
     * rfturns tif nfw <dodf>Sbvfpoint</dodf> objfdt tibt rfprfsfnts it.
     *
     *<p> if sftSbvfpoint is invokfd outsidf of bn bdtivf trbnsbdtion, b trbnsbdtion will bf stbrtfd bt tiis nfwly drfbtfd
     *sbvfpoint.
     *
     * @rfturn tif nfw <dodf>Sbvfpoint</dodf> objfdt
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tiis mftiod is dbllfd wiilf pbrtidipbting in b distributfd trbnsbdtion,
     * tiis mftiod is dbllfd on b dlosfd donnfdtion
     *            or tiis <dodf>Connfdtion</dodf> objfdt is durrfntly in
     *            buto-dommit modf
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff Sbvfpoint
     * @sindf 1.4
     */
    Sbvfpoint sftSbvfpoint() tirows SQLExdfption;

    /**
     * Crfbtfs b sbvfpoint witi tif givfn nbmf in tif durrfnt trbnsbdtion
     * bnd rfturns tif nfw <dodf>Sbvfpoint</dodf> objfdt tibt rfprfsfnts it.
     *
     * <p> if sftSbvfpoint is invokfd outsidf of bn bdtivf trbnsbdtion, b trbnsbdtion will bf stbrtfd bt tiis nfwly drfbtfd
     *sbvfpoint.
     *
     * @pbrbm nbmf b <dodf>String</dodf> dontbining tif nbmf of tif sbvfpoint
     * @rfturn tif nfw <dodf>Sbvfpoint</dodf> objfdt
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
          * tiis mftiod is dbllfd wiilf pbrtidipbting in b distributfd trbnsbdtion,
     * tiis mftiod is dbllfd on b dlosfd donnfdtion
     *            or tiis <dodf>Connfdtion</dodf> objfdt is durrfntly in
     *            buto-dommit modf
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff Sbvfpoint
     * @sindf 1.4
     */
    Sbvfpoint sftSbvfpoint(String nbmf) tirows SQLExdfption;

    /**
     * Undofs bll dibngfs mbdf bftfr tif givfn <dodf>Sbvfpoint</dodf> objfdt
     * wbs sft.
     * <P>
     * Tiis mftiod siould bf usfd only wifn buto-dommit ibs bffn disbblfd.
     *
     * @pbrbm sbvfpoint tif <dodf>Sbvfpoint</dodf> objfdt to roll bbdk to
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tiis mftiod is dbllfd wiilf pbrtidipbting in b distributfd trbnsbdtion,
     * tiis mftiod is dbllfd on b dlosfd donnfdtion,
     *            tif <dodf>Sbvfpoint</dodf> objfdt is no longfr vblid,
     *            or tiis <dodf>Connfdtion</dodf> objfdt is durrfntly in
     *            buto-dommit modf
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff Sbvfpoint
     * @sff #rollbbdk
     * @sindf 1.4
     */
    void rollbbdk(Sbvfpoint sbvfpoint) tirows SQLExdfption;

    /**
     * Rfmovfs tif spfdififd <dodf>Sbvfpoint</dodf>  bnd subsfqufnt <dodf>Sbvfpoint</dodf> objfdts from tif durrfnt
     * trbnsbdtion. Any rfffrfndf to tif sbvfpoint bftfr it ibvf bffn rfmovfd
     * will dbusf bn <dodf>SQLExdfption</dodf> to bf tirown.
     *
     * @pbrbm sbvfpoint tif <dodf>Sbvfpoint</dodf> objfdt to bf rfmovfd
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs, tiis
     *  mftiod is dbllfd on b dlosfd donnfdtion or
     *            tif givfn <dodf>Sbvfpoint</dodf> objfdt is not b vblid
     *            sbvfpoint in tif durrfnt trbnsbdtion
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.4
     */
    void rflfbsfSbvfpoint(Sbvfpoint sbvfpoint) tirows SQLExdfption;

    /**
     * Crfbtfs b <dodf>Stbtfmfnt</dodf> objfdt tibt will gfnfrbtf
     * <dodf>RfsultSft</dodf> objfdts witi tif givfn typf, dondurrfndy,
     * bnd ioldbbility.
     * Tiis mftiod is tif sbmf bs tif <dodf>drfbtfStbtfmfnt</dodf> mftiod
     * bbovf, but it bllows tif dffbult rfsult sft
     * typf, dondurrfndy, bnd ioldbbility to bf ovfrriddfn.
     *
     * @pbrbm rfsultSftTypf onf of tif following <dodf>RfsultSft</dodf>
     *        donstbnts:
     *         <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>,
     *         <dodf>RfsultSft.TYPE_SCROLL_INSENSITIVE</dodf>, or
     *         <dodf>RfsultSft.TYPE_SCROLL_SENSITIVE</dodf>
     * @pbrbm rfsultSftCondurrfndy onf of tif following <dodf>RfsultSft</dodf>
     *        donstbnts:
     *         <dodf>RfsultSft.CONCUR_READ_ONLY</dodf> or
     *         <dodf>RfsultSft.CONCUR_UPDATABLE</dodf>
     * @pbrbm rfsultSftHoldbbility onf of tif following <dodf>RfsultSft</dodf>
     *        donstbnts:
     *         <dodf>RfsultSft.HOLD_CURSORS_OVER_COMMIT</dodf> or
     *         <dodf>RfsultSft.CLOSE_CURSORS_AT_COMMIT</dodf>
     * @rfturn b nfw <dodf>Stbtfmfnt</dodf> objfdt tibt will gfnfrbtf
     *         <dodf>RfsultSft</dodf> objfdts witi tif givfn typf,
     *         dondurrfndy, bnd ioldbbility
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs, tiis
     * mftiod is dbllfd on b dlosfd donnfdtion
     *            or tif givfn pbrbmftfrs brf not <dodf>RfsultSft</dodf>
     *            donstbnts indidbting typf, dondurrfndy, bnd ioldbbility
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod or tiis mftiod is not supportfd for tif spfdififd rfsult
     * sft typf, rfsult sft ioldbbility bnd rfsult sft dondurrfndy.
     * @sff RfsultSft
     * @sindf 1.4
     */
    Stbtfmfnt drfbtfStbtfmfnt(int rfsultSftTypf, int rfsultSftCondurrfndy,
                              int rfsultSftHoldbbility) tirows SQLExdfption;

    /**
     * Crfbtfs b <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt tibt will gfnfrbtf
     * <dodf>RfsultSft</dodf> objfdts witi tif givfn typf, dondurrfndy,
     * bnd ioldbbility.
     * <P>
     * Tiis mftiod is tif sbmf bs tif <dodf>prfpbrfStbtfmfnt</dodf> mftiod
     * bbovf, but it bllows tif dffbult rfsult sft
     * typf, dondurrfndy, bnd ioldbbility to bf ovfrriddfn.
     *
     * @pbrbm sql b <dodf>String</dodf> objfdt tibt is tif SQL stbtfmfnt to
     *            bf sfnt to tif dbtbbbsf; mby dontbin onf or morf '?' IN
     *            pbrbmftfrs
     * @pbrbm rfsultSftTypf onf of tif following <dodf>RfsultSft</dodf>
     *        donstbnts:
     *         <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>,
     *         <dodf>RfsultSft.TYPE_SCROLL_INSENSITIVE</dodf>, or
     *         <dodf>RfsultSft.TYPE_SCROLL_SENSITIVE</dodf>
     * @pbrbm rfsultSftCondurrfndy onf of tif following <dodf>RfsultSft</dodf>
     *        donstbnts:
     *         <dodf>RfsultSft.CONCUR_READ_ONLY</dodf> or
     *         <dodf>RfsultSft.CONCUR_UPDATABLE</dodf>
     * @pbrbm rfsultSftHoldbbility onf of tif following <dodf>RfsultSft</dodf>
     *        donstbnts:
     *         <dodf>RfsultSft.HOLD_CURSORS_OVER_COMMIT</dodf> or
     *         <dodf>RfsultSft.CLOSE_CURSORS_AT_COMMIT</dodf>
     * @rfturn b nfw <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt, dontbining tif
     *         prf-dompilfd SQL stbtfmfnt, tibt will gfnfrbtf
     *         <dodf>RfsultSft</dodf> objfdts witi tif givfn typf,
     *         dondurrfndy, bnd ioldbbility
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs, tiis
     * mftiod is dbllfd on b dlosfd donnfdtion
     *            or tif givfn pbrbmftfrs brf not <dodf>RfsultSft</dodf>
     *            donstbnts indidbting typf, dondurrfndy, bnd ioldbbility
      * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod or tiis mftiod is not supportfd for tif spfdififd rfsult
     * sft typf, rfsult sft ioldbbility bnd rfsult sft dondurrfndy.
     * @sff RfsultSft
     * @sindf 1.4
     */
    PrfpbrfdStbtfmfnt prfpbrfStbtfmfnt(String sql, int rfsultSftTypf,
                                       int rfsultSftCondurrfndy, int rfsultSftHoldbbility)
        tirows SQLExdfption;

    /**
     * Crfbtfs b <dodf>CbllbblfStbtfmfnt</dodf> objfdt tibt will gfnfrbtf
     * <dodf>RfsultSft</dodf> objfdts witi tif givfn typf bnd dondurrfndy.
     * Tiis mftiod is tif sbmf bs tif <dodf>prfpbrfCbll</dodf> mftiod
     * bbovf, but it bllows tif dffbult rfsult sft
     * typf, rfsult sft dondurrfndy typf bnd ioldbbility to bf ovfrriddfn.
     *
     * @pbrbm sql b <dodf>String</dodf> objfdt tibt is tif SQL stbtfmfnt to
     *            bf sfnt to tif dbtbbbsf; mby dontbin on or morf '?' pbrbmftfrs
     * @pbrbm rfsultSftTypf onf of tif following <dodf>RfsultSft</dodf>
     *        donstbnts:
     *         <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>,
     *         <dodf>RfsultSft.TYPE_SCROLL_INSENSITIVE</dodf>, or
     *         <dodf>RfsultSft.TYPE_SCROLL_SENSITIVE</dodf>
     * @pbrbm rfsultSftCondurrfndy onf of tif following <dodf>RfsultSft</dodf>
     *        donstbnts:
     *         <dodf>RfsultSft.CONCUR_READ_ONLY</dodf> or
     *         <dodf>RfsultSft.CONCUR_UPDATABLE</dodf>
     * @pbrbm rfsultSftHoldbbility onf of tif following <dodf>RfsultSft</dodf>
     *        donstbnts:
     *         <dodf>RfsultSft.HOLD_CURSORS_OVER_COMMIT</dodf> or
     *         <dodf>RfsultSft.CLOSE_CURSORS_AT_COMMIT</dodf>
     * @rfturn b nfw <dodf>CbllbblfStbtfmfnt</dodf> objfdt, dontbining tif
     *         prf-dompilfd SQL stbtfmfnt, tibt will gfnfrbtf
     *         <dodf>RfsultSft</dodf> objfdts witi tif givfn typf,
     *         dondurrfndy, bnd ioldbbility
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs, tiis
     * mftiod is dbllfd on b dlosfd donnfdtion
     *            or tif givfn pbrbmftfrs brf not <dodf>RfsultSft</dodf>
     *            donstbnts indidbting typf, dondurrfndy, bnd ioldbbility
      * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod or tiis mftiod is not supportfd for tif spfdififd rfsult
     * sft typf, rfsult sft ioldbbility bnd rfsult sft dondurrfndy.
     * @sff RfsultSft
     * @sindf 1.4
     */
    CbllbblfStbtfmfnt prfpbrfCbll(String sql, int rfsultSftTypf,
                                  int rfsultSftCondurrfndy,
                                  int rfsultSftHoldbbility) tirows SQLExdfption;


    /**
     * Crfbtfs b dffbult <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt tibt ibs
     * tif dbpbbility to rftrifvf buto-gfnfrbtfd kfys. Tif givfn donstbnt
     * tflls tif drivfr wiftifr it siould mbkf buto-gfnfrbtfd kfys
     * bvbilbblf for rftrifvbl.  Tiis pbrbmftfr is ignorfd if tif SQL stbtfmfnt
     * is not bn <dodf>INSERT</dodf> stbtfmfnt, or bn SQL stbtfmfnt bblf to rfturn
     * buto-gfnfrbtfd kfys (tif list of sudi stbtfmfnts is vfndor-spfdifid).
     * <P>
     * <B>Notf:</B> Tiis mftiod is optimizfd for ibndling
     * pbrbmftrid SQL stbtfmfnts tibt bfnffit from prfdompilbtion. If
     * tif drivfr supports prfdompilbtion,
     * tif mftiod <dodf>prfpbrfStbtfmfnt</dodf> will sfnd
     * tif stbtfmfnt to tif dbtbbbsf for prfdompilbtion. Somf drivfrs
     * mby not support prfdompilbtion. In tiis dbsf, tif stbtfmfnt mby
     * not bf sfnt to tif dbtbbbsf until tif <dodf>PrfpbrfdStbtfmfnt</dodf>
     * objfdt is fxfdutfd.  Tiis ibs no dirfdt ffffdt on usfrs; iowfvfr, it dofs
     * bfffdt wiidi mftiods tirow dfrtbin SQLExdfptions.
     * <P>
     * Rfsult sfts drfbtfd using tif rfturnfd <dodf>PrfpbrfdStbtfmfnt</dodf>
     * objfdt will by dffbult bf typf <dodf>TYPE_FORWARD_ONLY</dodf>
     * bnd ibvf b dondurrfndy lfvfl of <dodf>CONCUR_READ_ONLY</dodf>.
     * Tif ioldbbility of tif drfbtfd rfsult sfts dbn bf dftfrminfd by
     * dblling {@link #gftHoldbbility}.
     *
     * @pbrbm sql bn SQL stbtfmfnt tibt mby dontbin onf or morf '?' IN
     *        pbrbmftfr plbdfioldfrs
     * @pbrbm butoGfnfrbtfdKfys b flbg indidbting wiftifr buto-gfnfrbtfd kfys
     *        siould bf rfturnfd; onf of
     *        <dodf>Stbtfmfnt.RETURN_GENERATED_KEYS</dodf> or
     *        <dodf>Stbtfmfnt.NO_GENERATED_KEYS</dodf>
     * @rfturn b nfw <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt, dontbining tif
     *         prf-dompilfd SQL stbtfmfnt, tibt will ibvf tif dbpbbility of
     *         rfturning buto-gfnfrbtfd kfys
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs, tiis
     *  mftiod is dbllfd on b dlosfd donnfdtion
     *         or tif givfn pbrbmftfr is not b <dodf>Stbtfmfnt</dodf>
     *         donstbnt indidbting wiftifr buto-gfnfrbtfd kfys siould bf
     *         rfturnfd
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod witi b donstbnt of Stbtfmfnt.RETURN_GENERATED_KEYS
     * @sindf 1.4
     */
    PrfpbrfdStbtfmfnt prfpbrfStbtfmfnt(String sql, int butoGfnfrbtfdKfys)
        tirows SQLExdfption;

    /**
     * Crfbtfs b dffbult <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt dbpbblf
     * of rfturning tif buto-gfnfrbtfd kfys dfsignbtfd by tif givfn brrby.
     * Tiis brrby dontbins tif indfxfs of tif dolumns in tif tbrgft
     * tbblf tibt dontbin tif buto-gfnfrbtfd kfys tibt siould bf mbdf
     * bvbilbblf.  Tif drivfr will ignorf tif brrby if tif SQL stbtfmfnt
     * is not bn <dodf>INSERT</dodf> stbtfmfnt, or bn SQL stbtfmfnt bblf to rfturn
     * buto-gfnfrbtfd kfys (tif list of sudi stbtfmfnts is vfndor-spfdifid).
     *<p>
     * An SQL stbtfmfnt witi or witiout IN pbrbmftfrs dbn bf
     * prf-dompilfd bnd storfd in b <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt. Tiis
     * objfdt dbn tifn bf usfd to fffidifntly fxfdutf tiis stbtfmfnt
     * multiplf timfs.
     * <P>
     * <B>Notf:</B> Tiis mftiod is optimizfd for ibndling
     * pbrbmftrid SQL stbtfmfnts tibt bfnffit from prfdompilbtion. If
     * tif drivfr supports prfdompilbtion,
     * tif mftiod <dodf>prfpbrfStbtfmfnt</dodf> will sfnd
     * tif stbtfmfnt to tif dbtbbbsf for prfdompilbtion. Somf drivfrs
     * mby not support prfdompilbtion. In tiis dbsf, tif stbtfmfnt mby
     * not bf sfnt to tif dbtbbbsf until tif <dodf>PrfpbrfdStbtfmfnt</dodf>
     * objfdt is fxfdutfd.  Tiis ibs no dirfdt ffffdt on usfrs; iowfvfr, it dofs
     * bfffdt wiidi mftiods tirow dfrtbin SQLExdfptions.
     * <P>
     * Rfsult sfts drfbtfd using tif rfturnfd <dodf>PrfpbrfdStbtfmfnt</dodf>
     * objfdt will by dffbult bf typf <dodf>TYPE_FORWARD_ONLY</dodf>
     * bnd ibvf b dondurrfndy lfvfl of <dodf>CONCUR_READ_ONLY</dodf>.
     * Tif ioldbbility of tif drfbtfd rfsult sfts dbn bf dftfrminfd by
     * dblling {@link #gftHoldbbility}.
     *
     * @pbrbm sql bn SQL stbtfmfnt tibt mby dontbin onf or morf '?' IN
     *        pbrbmftfr plbdfioldfrs
     * @pbrbm dolumnIndfxfs bn brrby of dolumn indfxfs indidbting tif dolumns
     *        tibt siould bf rfturnfd from tif insfrtfd row or rows
     * @rfturn b nfw <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt, dontbining tif
     *         prf-dompilfd stbtfmfnt, tibt is dbpbblf of rfturning tif
     *         buto-gfnfrbtfd kfys dfsignbtfd by tif givfn brrby of dolumn
     *         indfxfs
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd donnfdtion
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     *
     * @sindf 1.4
     */
    PrfpbrfdStbtfmfnt prfpbrfStbtfmfnt(String sql, int dolumnIndfxfs[])
        tirows SQLExdfption;

    /**
     * Crfbtfs b dffbult <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt dbpbblf
     * of rfturning tif buto-gfnfrbtfd kfys dfsignbtfd by tif givfn brrby.
     * Tiis brrby dontbins tif nbmfs of tif dolumns in tif tbrgft
     * tbblf tibt dontbin tif buto-gfnfrbtfd kfys tibt siould bf rfturnfd.
     * Tif drivfr will ignorf tif brrby if tif SQL stbtfmfnt
     * is not bn <dodf>INSERT</dodf> stbtfmfnt, or bn SQL stbtfmfnt bblf to rfturn
     * buto-gfnfrbtfd kfys (tif list of sudi stbtfmfnts is vfndor-spfdifid).
     * <P>
     * An SQL stbtfmfnt witi or witiout IN pbrbmftfrs dbn bf
     * prf-dompilfd bnd storfd in b <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt. Tiis
     * objfdt dbn tifn bf usfd to fffidifntly fxfdutf tiis stbtfmfnt
     * multiplf timfs.
     * <P>
     * <B>Notf:</B> Tiis mftiod is optimizfd for ibndling
     * pbrbmftrid SQL stbtfmfnts tibt bfnffit from prfdompilbtion. If
     * tif drivfr supports prfdompilbtion,
     * tif mftiod <dodf>prfpbrfStbtfmfnt</dodf> will sfnd
     * tif stbtfmfnt to tif dbtbbbsf for prfdompilbtion. Somf drivfrs
     * mby not support prfdompilbtion. In tiis dbsf, tif stbtfmfnt mby
     * not bf sfnt to tif dbtbbbsf until tif <dodf>PrfpbrfdStbtfmfnt</dodf>
     * objfdt is fxfdutfd.  Tiis ibs no dirfdt ffffdt on usfrs; iowfvfr, it dofs
     * bfffdt wiidi mftiods tirow dfrtbin SQLExdfptions.
     * <P>
     * Rfsult sfts drfbtfd using tif rfturnfd <dodf>PrfpbrfdStbtfmfnt</dodf>
     * objfdt will by dffbult bf typf <dodf>TYPE_FORWARD_ONLY</dodf>
     * bnd ibvf b dondurrfndy lfvfl of <dodf>CONCUR_READ_ONLY</dodf>.
     * Tif ioldbbility of tif drfbtfd rfsult sfts dbn bf dftfrminfd by
     * dblling {@link #gftHoldbbility}.
     *
     * @pbrbm sql bn SQL stbtfmfnt tibt mby dontbin onf or morf '?' IN
     *        pbrbmftfr plbdfioldfrs
     * @pbrbm dolumnNbmfs bn brrby of dolumn nbmfs indidbting tif dolumns
     *        tibt siould bf rfturnfd from tif insfrtfd row or rows
     * @rfturn b nfw <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt, dontbining tif
     *         prf-dompilfd stbtfmfnt, tibt is dbpbblf of rfturning tif
     *         buto-gfnfrbtfd kfys dfsignbtfd by tif givfn brrby of dolumn
     *         nbmfs
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd donnfdtion
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     *
     * @sindf 1.4
     */
    PrfpbrfdStbtfmfnt prfpbrfStbtfmfnt(String sql, String dolumnNbmfs[])
        tirows SQLExdfption;

    /**
     * Construdts bn objfdt tibt implfmfnts tif <dodf>Clob</dodf> intfrfbdf. Tif objfdt
     * rfturnfd initiblly dontbins no dbtb.  Tif <dodf>sftAsdiiStrfbm</dodf>,
     * <dodf>sftCibrbdtfrStrfbm</dodf> bnd <dodf>sftString</dodf> mftiods of
     * tif <dodf>Clob</dodf> intfrfbdf mby bf usfd to bdd dbtb to tif <dodf>Clob</dodf>.
     * @rfturn An objfdt tibt implfmfnts tif <dodf>Clob</dodf> intfrfbdf
     * @tirows SQLExdfption if bn objfdt tibt implfmfnts tif
     * <dodf>Clob</dodf> intfrfbdf dbn not bf donstrudtfd, tiis mftiod is
     * dbllfd on b dlosfd donnfdtion or b dbtbbbsf bddfss frror oddurs.
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis dbtb typf
     *
     * @sindf 1.6
     */
    Clob drfbtfClob() tirows SQLExdfption;

    /**
     * Construdts bn objfdt tibt implfmfnts tif <dodf>Blob</dodf> intfrfbdf. Tif objfdt
     * rfturnfd initiblly dontbins no dbtb.  Tif <dodf>sftBinbryStrfbm</dodf> bnd
     * <dodf>sftBytfs</dodf> mftiods of tif <dodf>Blob</dodf> intfrfbdf mby bf usfd to bdd dbtb to
     * tif <dodf>Blob</dodf>.
     * @rfturn  An objfdt tibt implfmfnts tif <dodf>Blob</dodf> intfrfbdf
     * @tirows SQLExdfption if bn objfdt tibt implfmfnts tif
     * <dodf>Blob</dodf> intfrfbdf dbn not bf donstrudtfd, tiis mftiod is
     * dbllfd on b dlosfd donnfdtion or b dbtbbbsf bddfss frror oddurs.
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis dbtb typf
     *
     * @sindf 1.6
     */
    Blob drfbtfBlob() tirows SQLExdfption;

    /**
     * Construdts bn objfdt tibt implfmfnts tif <dodf>NClob</dodf> intfrfbdf. Tif objfdt
     * rfturnfd initiblly dontbins no dbtb.  Tif <dodf>sftAsdiiStrfbm</dodf>,
     * <dodf>sftCibrbdtfrStrfbm</dodf> bnd <dodf>sftString</dodf> mftiods of tif <dodf>NClob</dodf> intfrfbdf mby
     * bf usfd to bdd dbtb to tif <dodf>NClob</dodf>.
     * @rfturn An objfdt tibt implfmfnts tif <dodf>NClob</dodf> intfrfbdf
     * @tirows SQLExdfption if bn objfdt tibt implfmfnts tif
     * <dodf>NClob</dodf> intfrfbdf dbn not bf donstrudtfd, tiis mftiod is
     * dbllfd on b dlosfd donnfdtion or b dbtbbbsf bddfss frror oddurs.
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis dbtb typf
     *
     * @sindf 1.6
     */
    NClob drfbtfNClob() tirows SQLExdfption;

    /**
     * Construdts bn objfdt tibt implfmfnts tif <dodf>SQLXML</dodf> intfrfbdf. Tif objfdt
     * rfturnfd initiblly dontbins no dbtb. Tif <dodf>drfbtfXmlStrfbmWritfr</dodf> objfdt bnd
     * <dodf>sftString</dodf> mftiod of tif <dodf>SQLXML</dodf> intfrfbdf mby bf usfd to bdd dbtb to tif <dodf>SQLXML</dodf>
     * objfdt.
     * @rfturn An objfdt tibt implfmfnts tif <dodf>SQLXML</dodf> intfrfbdf
     * @tirows SQLExdfption if bn objfdt tibt implfmfnts tif <dodf>SQLXML</dodf> intfrfbdf dbn not
     * bf donstrudtfd, tiis mftiod is
     * dbllfd on b dlosfd donnfdtion or b dbtbbbsf bddfss frror oddurs.
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis dbtb typf
     * @sindf 1.6
     */
    SQLXML drfbtfSQLXML() tirows SQLExdfption;

        /**
         * Rfturns truf if tif donnfdtion ibs not bffn dlosfd bnd is still vblid.
         * Tif drivfr sibll submit b qufry on tif donnfdtion or usf somf otifr
         * mfdibnism tibt positivfly vfrififs tif donnfdtion is still vblid wifn
         * tiis mftiod is dbllfd.
         * <p>
         * Tif qufry submittfd by tif drivfr to vblidbtf tif donnfdtion sibll bf
         * fxfdutfd in tif dontfxt of tif durrfnt trbnsbdtion.
         *
         * @pbrbm timfout -             Tif timf in sfdonds to wbit for tif dbtbbbsf opfrbtion
         *                                              usfd to vblidbtf tif donnfdtion to domplftf.  If
         *                                              tif timfout pfriod fxpirfs bfforf tif opfrbtion
         *                                              domplftfs, tiis mftiod rfturns fblsf.  A vbluf of
         *                                              0 indidbtfs b timfout is not bpplifd to tif
         *                                              dbtbbbsf opfrbtion.
         *
         * @rfturn truf if tif donnfdtion is vblid, fblsf otifrwisf
         * @fxdfption SQLExdfption if tif vbluf supplifd for <dodf>timfout</dodf>
         * is lfss tifn 0
         * @sindf 1.6
         *
         * @sff jbvb.sql.DbtbbbsfMftbDbtb#gftClifntInfoPropfrtifs
         */
         boolfbn isVblid(int timfout) tirows SQLExdfption;

        /**
         * Sfts tif vbluf of tif dlifnt info propfrty spfdififd by nbmf to tif
         * vbluf spfdififd by vbluf.
         * <p>
         * Applidbtions mby usf tif <dodf>DbtbbbsfMftbDbtb.gftClifntInfoPropfrtifs</dodf>
         * mftiod to dftfrminf tif dlifnt info propfrtifs supportfd by tif drivfr
         * bnd tif mbximum lfngti tibt mby bf spfdififd for fbdi propfrty.
         * <p>
         * Tif drivfr storfs tif vbluf spfdififd in b suitbblf lodbtion in tif
         * dbtbbbsf.  For fxbmplf in b spfdibl rfgistfr, sfssion pbrbmftfr, or
         * systfm tbblf dolumn.  For fffidifndy tif drivfr mby dfffr sftting tif
         * vbluf in tif dbtbbbsf until tif nfxt timf b stbtfmfnt is fxfdutfd or
         * prfpbrfd.  Otifr tibn storing tif dlifnt informbtion in tif bppropribtf
         * plbdf in tif dbtbbbsf, tifsf mftiods sibll not bltfr tif bfibvior of
         * tif donnfdtion in bnywby.  Tif vblufs supplifd to tifsf mftiods brf
         * usfd for bddounting, dibgnostids bnd dfbugging purposfs only.
         * <p>
         * Tif drivfr sibll gfnfrbtf b wbrning if tif dlifnt info nbmf spfdififd
         * is not rfdognizfd by tif drivfr.
         * <p>
         * If tif vbluf spfdififd to tiis mftiod is grfbtfr tibn tif mbximum
         * lfngti for tif propfrty tif drivfr mby fitifr trundbtf tif vbluf bnd
         * gfnfrbtf b wbrning or gfnfrbtf b <dodf>SQLClifntInfoExdfption</dodf>.  If tif drivfr
         * gfnfrbtfs b <dodf>SQLClifntInfoExdfption</dodf>, tif vbluf spfdififd wbs not sft on tif
         * donnfdtion.
         * <p>
         * Tif following brf stbndbrd dlifnt info propfrtifs.  Drivfrs brf not
         * rfquirfd to support tifsf propfrtifs iowfvfr if tif drivfr supports b
         * dlifnt info propfrty tibt dbn bf dfsdribfd by onf of tif stbndbrd
         * propfrtifs, tif stbndbrd propfrty nbmf siould bf usfd.
         *
         * <ul>
         * <li>ApplidbtionNbmf  -       Tif nbmf of tif bpplidbtion durrfntly utilizing
         *                                                      tif donnfdtion</li>
         * <li>ClifntUsfr               -       Tif nbmf of tif usfr tibt tif bpplidbtion using
         *                                                      tif donnfdtion is pfrforming work for.  Tiis mby
         *                                                      not bf tif sbmf bs tif usfr nbmf tibt wbs usfd
         *                                                      in fstbblisiing tif donnfdtion.</li>
         * <li>ClifntHostnbmf   -       Tif iostnbmf of tif domputfr tif bpplidbtion
         *                                                      using tif donnfdtion is running on.</li>
         * </ul>
         *
         * @pbrbm nbmf          Tif nbmf of tif dlifnt info propfrty to sft
         * @pbrbm vbluf         Tif vbluf to sft tif dlifnt info propfrty to.  If tif
         *                                      vbluf is null, tif durrfnt vbluf of tif spfdififd
         *                                      propfrty is dlfbrfd.
         *
         * @tirows      SQLClifntInfoExdfption if tif dbtbbbsf sfrvfr rfturns bn frror wiilf
         *                      sftting tif dlifnt info vbluf on tif dbtbbbsf sfrvfr or tiis mftiod
         * is dbllfd on b dlosfd donnfdtion
         *
         * @sindf 1.6
         */
         void sftClifntInfo(String nbmf, String vbluf)
                tirows SQLClifntInfoExdfption;

        /**
     * Sfts tif vbluf of tif donnfdtion's dlifnt info propfrtifs.  Tif
     * <dodf>Propfrtifs</dodf> objfdt dontbins tif nbmfs bnd vblufs of tif dlifnt info
     * propfrtifs to bf sft.  Tif sft of dlifnt info propfrtifs dontbinfd in
     * tif propfrtifs list rfplbdfs tif durrfnt sft of dlifnt info propfrtifs
     * on tif donnfdtion.  If b propfrty tibt is durrfntly sft on tif
     * donnfdtion is not prfsfnt in tif propfrtifs list, tibt propfrty is
     * dlfbrfd.  Spfdifying bn fmpty propfrtifs list will dlfbr bll of tif
     * propfrtifs on tif donnfdtion.  Sff <dodf>sftClifntInfo (String, String)</dodf> for
     * morf informbtion.
     * <p>
     * If bn frror oddurs in sftting bny of tif dlifnt info propfrtifs, b
     * <dodf>SQLClifntInfoExdfption</dodf> is tirown. Tif <dodf>SQLClifntInfoExdfption</dodf>
     * dontbins informbtion indidbting wiidi dlifnt info propfrtifs wfrf not sft.
     * Tif stbtf of tif dlifnt informbtion is unknown bfdbusf
     * somf dbtbbbsfs do not bllow multiplf dlifnt info propfrtifs to bf sft
     * btomidblly.  For tiosf dbtbbbsfs, onf or morf propfrtifs mby ibvf bffn
     * sft bfforf tif frror oddurrfd.
     *
     *
     * @pbrbm propfrtifs                tif list of dlifnt info propfrtifs to sft
     *
     * @sff jbvb.sql.Connfdtion#sftClifntInfo(String, String) sftClifntInfo(String, String)
     * @sindf 1.6
     *
     * @tirows SQLClifntInfoExdfption if tif dbtbbbsf sfrvfr rfturns bn frror wiilf
     *                  sftting tif dlifntInfo vblufs on tif dbtbbbsf sfrvfr or tiis mftiod
     * is dbllfd on b dlosfd donnfdtion
     *
     */
         void sftClifntInfo(Propfrtifs propfrtifs)
                tirows SQLClifntInfoExdfption;

        /**
         * Rfturns tif vbluf of tif dlifnt info propfrty spfdififd by nbmf.  Tiis
         * mftiod mby rfturn null if tif spfdififd dlifnt info propfrty ibs not
         * bffn sft bnd dofs not ibvf b dffbult vbluf.  Tiis mftiod will blso
         * rfturn null if tif spfdififd dlifnt info propfrty nbmf is not supportfd
         * by tif drivfr.
         * <p>
         * Applidbtions mby usf tif <dodf>DbtbbbsfMftbDbtb.gftClifntInfoPropfrtifs</dodf>
         * mftiod to dftfrminf tif dlifnt info propfrtifs supportfd by tif drivfr.
         *
         * @pbrbm nbmf          Tif nbmf of tif dlifnt info propfrty to rftrifvf
         *
         * @rfturn                      Tif vbluf of tif dlifnt info propfrty spfdififd
         *
         * @tirows SQLExdfption         if tif dbtbbbsf sfrvfr rfturns bn frror wifn
         *                              fftdiing tif dlifnt info vbluf from tif dbtbbbsf
         *                              or tiis mftiod is dbllfd on b dlosfd donnfdtion
         *
         * @sindf 1.6
         *
         * @sff jbvb.sql.DbtbbbsfMftbDbtb#gftClifntInfoPropfrtifs
         */
         String gftClifntInfo(String nbmf)
                tirows SQLExdfption;

        /**
         * Rfturns b list dontbining tif nbmf bnd durrfnt vbluf of fbdi dlifnt info
         * propfrty supportfd by tif drivfr.  Tif vbluf of b dlifnt info propfrty
         * mby bf null if tif propfrty ibs not bffn sft bnd dofs not ibvf b
         * dffbult vbluf.
         *
         * @rfturn      A <dodf>Propfrtifs</dodf> objfdt tibt dontbins tif nbmf bnd durrfnt vbluf of
         *                      fbdi of tif dlifnt info propfrtifs supportfd by tif drivfr.
         *
         * @tirows      SQLExdfption if tif dbtbbbsf sfrvfr rfturns bn frror wifn
         *                      fftdiing tif dlifnt info vblufs from tif dbtbbbsf
         * or tiis mftiod is dbllfd on b dlosfd donnfdtion
         *
         * @sindf 1.6
         */
         Propfrtifs gftClifntInfo()
                tirows SQLExdfption;

/**
  * Fbdtory mftiod for drfbting Arrby objfdts.
  *<p>
  * <b>Notf: </b>Wifn <dodf>drfbtfArrbyOf</dodf> is usfd to drfbtf bn brrby objfdt
  * tibt mbps to b primitivf dbtb typf, tifn it is implfmfntbtion-dffinfd
  * wiftifr tif <dodf>Arrby</dodf> objfdt is bn brrby of tibt primitivf
  * dbtb typf or bn brrby of <dodf>Objfdt</dodf>.
  * <p>
  * <b>Notf: </b>Tif JDBC drivfr is rfsponsiblf for mbpping tif flfmfnts
  * <dodf>Objfdt</dodf> brrby to tif dffbult JDBC SQL typf dffinfd in
  * jbvb.sql.Typfs for tif givfn dlbss of <dodf>Objfdt</dodf>. Tif dffbult
  * mbpping is spfdififd in Appfndix B of tif JDBC spfdifidbtion.  If tif
  * rfsulting JDBC typf is not tif bppropribtf typf for tif givfn typfNbmf tifn
  * it is implfmfntbtion dffinfd wiftifr bn <dodf>SQLExdfption</dodf> is
  * tirown or tif drivfr supports tif rfsulting donvfrsion.
  *
  * @pbrbm typfNbmf tif SQL nbmf of tif typf tif flfmfnts of tif brrby mbp to. Tif typfNbmf is b
  * dbtbbbsf-spfdifid nbmf wiidi mby bf tif nbmf of b built-in typf, b usfr-dffinfd typf or b stbndbrd  SQL typf supportfd by tiis dbtbbbsf. Tiis
  *  is tif vbluf rfturnfd by <dodf>Arrby.gftBbsfTypfNbmf</dodf>
  * @pbrbm flfmfnts tif flfmfnts tibt populbtf tif rfturnfd objfdt
  * @rfturn bn Arrby objfdt wiosf flfmfnts mbp to tif spfdififd SQL typf
  * @tirows SQLExdfption if b dbtbbbsf frror oddurs, tif JDBC typf is not
  *  bppropribtf for tif typfNbmf bnd tif donvfrsion is not supportfd, tif typfNbmf is null or tiis mftiod is dbllfd on b dlosfd donnfdtion
  * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis dbtb typf
  * @sindf 1.6
  */
 Arrby drfbtfArrbyOf(String typfNbmf, Objfdt[] flfmfnts) tirows
SQLExdfption;

/**
  * Fbdtory mftiod for drfbting Strudt objfdts.
  *
  * @pbrbm typfNbmf tif SQL typf nbmf of tif SQL strudturfd typf tibt tiis <dodf>Strudt</dodf>
  * objfdt mbps to. Tif typfNbmf is tif nbmf of  b usfr-dffinfd typf tibt
  * ibs bffn dffinfd for tiis dbtbbbsf. It is tif vbluf rfturnfd by
  * <dodf>Strudt.gftSQLTypfNbmf</dodf>.

  * @pbrbm bttributfs tif bttributfs tibt populbtf tif rfturnfd objfdt
  * @rfturn b Strudt objfdt tibt mbps to tif givfn SQL typf bnd is populbtfd witi tif givfn bttributfs
  * @tirows SQLExdfption if b dbtbbbsf frror oddurs, tif typfNbmf is null or tiis mftiod is dbllfd on b dlosfd donnfdtion
  * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis dbtb typf
  * @sindf 1.6
  */
 Strudt drfbtfStrudt(String typfNbmf, Objfdt[] bttributfs)
tirows SQLExdfption;

   //--------------------------JDBC 4.1 -----------------------------

   /**
    * Sfts tif givfn sdifmb nbmf to bddfss.
    * <P>
    * If tif drivfr dofs not support sdifmbs, it will
    * silfntly ignorf tiis rfqufst.
    * <p>
    * Cblling {@dodf sftSdifmb} ibs no ffffdt on prfviously drfbtfd or prfpbrfd
    * {@dodf Stbtfmfnt} objfdts. It is implfmfntbtion dffinfd wiftifr b DBMS
    * prfpbrf opfrbtion tbkfs plbdf immfdibtfly wifn tif {@dodf Connfdtion}
    * mftiod {@dodf prfpbrfStbtfmfnt} or {@dodf prfpbrfCbll} is invokfd.
    * For mbximum portbbility, {@dodf sftSdifmb} siould bf dbllfd bfforf b
    * {@dodf Stbtfmfnt} is drfbtfd or prfpbrfd.
    *
    * @pbrbm sdifmb tif nbmf of b sdifmb  in wiidi to work
    * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
    * or tiis mftiod is dbllfd on b dlosfd donnfdtion
    * @sff #gftSdifmb
    * @sindf 1.7
    */
    void sftSdifmb(String sdifmb) tirows SQLExdfption;

    /**
     * Rftrifvfs tiis <dodf>Connfdtion</dodf> objfdt's durrfnt sdifmb nbmf.
     *
     * @rfturn tif durrfnt sdifmb nbmf or <dodf>null</dodf> if tifrf is nonf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd donnfdtion
     * @sff #sftSdifmb
     * @sindf 1.7
     */
    String gftSdifmb() tirows SQLExdfption;

    /**
     * Tfrminbtfs bn opfn donnfdtion.  Cblling <dodf>bbort</dodf> rfsults in:
     * <ul>
     * <li>Tif donnfdtion mbrkfd bs dlosfd
     * <li>Closfs bny piysidbl donnfdtion to tif dbtbbbsf
     * <li>Rflfbsfs rfsourdfs usfd by tif donnfdtion
     * <li>Insurfs tibt bny tirfbd tibt is durrfntly bddfssing tif donnfdtion
     * will fitifr progrfss to domplftion or tirow bn <dodf>SQLExdfption</dodf>.
     * </ul>
     * <p>
     * Cblling <dodf>bbort</dodf> mbrks tif donnfdtion dlosfd bnd rflfbsfs bny
     * rfsourdfs. Cblling <dodf>bbort</dodf> on b dlosfd donnfdtion is b
     * no-op.
     * <p>
     * It is possiblf tibt tif bborting bnd rflfbsing of tif rfsourdfs tibt brf
     * ifld by tif donnfdtion dbn tbkf bn fxtfndfd pfriod of timf.  Wifn tif
     * <dodf>bbort</dodf> mftiod rfturns, tif donnfdtion will ibvf bffn mbrkfd bs
     * dlosfd bnd tif <dodf>Exfdutor</dodf> tibt wbs pbssfd bs b pbrbmftfr to bbort
     * mby still bf fxfduting tbsks to rflfbsf rfsourdfs.
     * <p>
     * Tiis mftiod difdks to sff tibt tifrf is bn <dodf>SQLPfrmission</dodf>
     * objfdt bfforf bllowing tif mftiod to prodffd.  If b
     * <dodf>SfdurityMbnbgfr</dodf> fxists bnd its
     * <dodf>difdkPfrmission</dodf> mftiod dfnifs dblling <dodf>bbort</dodf>,
     * tiis mftiod tirows b
     * <dodf>jbvb.lbng.SfdurityExdfption</dodf>.
     * @pbrbm fxfdutor  Tif <dodf>Exfdutor</dodf>  implfmfntbtion wiidi will
     * bf usfd by <dodf>bbort</dodf>.
     * @tirows jbvb.sql.SQLExdfption if b dbtbbbsf bddfss frror oddurs or
     * tif {@dodf fxfdutor} is {@dodf null},
     * @tirows jbvb.lbng.SfdurityExdfption if b sfdurity mbnbgfr fxists bnd its
     *    <dodf>difdkPfrmission</dodf> mftiod dfnifs dblling <dodf>bbort</dodf>
     * @sff SfdurityMbnbgfr#difdkPfrmission
     * @sff Exfdutor
     * @sindf 1.7
     */
    void bbort(Exfdutor fxfdutor) tirows SQLExdfption;

    /**
     *
     * Sfts tif mbximum pfriod b <dodf>Connfdtion</dodf> or
     * objfdts drfbtfd from tif <dodf>Connfdtion</dodf>
     * will wbit for tif dbtbbbsf to rfply to bny onf rfqufst. If bny
     *  rfqufst rfmbins unbnswfrfd, tif wbiting mftiod will
     * rfturn witi b <dodf>SQLExdfption</dodf>, bnd tif <dodf>Connfdtion</dodf>
     * or objfdts drfbtfd from tif <dodf>Connfdtion</dodf>  will bf mbrkfd bs
     * dlosfd. Any subsfqufnt usf of
     * tif objfdts, witi tif fxdfption of tif <dodf>dlosf</dodf>,
     * <dodf>isClosfd</dodf> or <dodf>Connfdtion.isVblid</dodf>
     * mftiods, will rfsult in  b <dodf>SQLExdfption</dodf>.
     * <p>
     * <b>Notf</b>: Tiis mftiod is intfndfd to bddrfss b rbrf but sfrious
     * dondition wifrf nftwork pbrtitions dbn dbusf tirfbds issuing JDBC dblls
     * to ibng unintfrruptfdly in sodkft rfbds, until tif OS TCP-TIMEOUT
     * (typidblly 10 minutfs). Tiis mftiod is rflbtfd to tif
     * {@link #bbort bbort() } mftiod wiidi providfs bn bdministrbtor
     * tirfbd b mfbns to frff bny sudi tirfbds in dbsfs wifrf tif
     * JDBC donnfdtion is bddfssiblf to tif bdministrbtor tirfbd.
     * Tif <dodf>sftNftworkTimfout</dodf> mftiod will dovfr dbsfs wifrf
     * tifrf is no bdministrbtor tirfbd, or it ibs no bddfss to tif
     * donnfdtion. Tiis mftiod is sfvfrf in it's ffffdts, bnd siould bf
     * givfn b iigi fnougi vbluf so it is nfvfr triggfrfd bfforf bny morf
     * normbl timfouts, sudi bs trbnsbdtion timfouts.
     * <p>
     * JDBC drivfr implfmfntbtions  mby blso dioosf to support tif
     * {@dodf sftNftworkTimfout} mftiod to imposf b limit on dbtbbbsf
     * rfsponsf timf, in fnvironmfnts wifrf no nftwork is prfsfnt.
     * <p>
     * Drivfrs mby intfrnblly implfmfnt somf or bll of tifir API dblls witi
     * multiplf intfrnbl drivfr-dbtbbbsf trbnsmissions, bnd it is lfft to tif
     * drivfr implfmfntbtion to dftfrminf wiftifr tif limit will bf
     * bpplifd blwbys to tif rfsponsf to tif API dbll, or to bny
     * singlf  rfqufst mbdf during tif API dbll.
     * <p>
     *
     * Tiis mftiod dbn bf invokfd morf tibn ondf, sudi bs to sft b limit for bn
     * brfb of JDBC dodf, bnd to rfsft to tif dffbult on fxit from tiis brfb.
     * Invodbtion of tiis mftiod ibs no impbdt on blrfbdy outstbnding
     * rfqufsts.
     * <p>
     * Tif {@dodf Stbtfmfnt.sftQufryTimfout()} timfout vbluf is indfpfndfnt of tif
     * timfout vbluf spfdififd in {@dodf sftNftworkTimfout}. If tif qufry timfout
     * fxpirfs  bfforf tif nftwork timfout tifn tif
     * stbtfmfnt fxfdution will bf dbndflfd. If tif nftwork is still
     * bdtivf tif rfsult will bf tibt boti tif stbtfmfnt bnd donnfdtion
     * brf still usbblf. Howfvfr if tif nftwork timfout fxpirfs bfforf
     * tif qufry timfout or if tif stbtfmfnt timfout fbils duf to nftwork
     * problfms, tif donnfdtion will bf mbrkfd bs dlosfd, bny rfsourdfs ifld by
     * tif donnfdtion will bf rflfbsfd bnd boti tif donnfdtion bnd
     * stbtfmfnt will bf unusbblf.
     * <p>
     * Wifn tif drivfr dftfrminfs tibt tif {@dodf sftNftworkTimfout} timfout
     * vbluf ibs fxpirfd, tif JDBC drivfr mbrks tif donnfdtion
     * dlosfd bnd rflfbsfs bny rfsourdfs ifld by tif donnfdtion.
     * <p>
     *
     * Tiis mftiod difdks to sff tibt tifrf is bn <dodf>SQLPfrmission</dodf>
     * objfdt bfforf bllowing tif mftiod to prodffd.  If b
     * <dodf>SfdurityMbnbgfr</dodf> fxists bnd its
     * <dodf>difdkPfrmission</dodf> mftiod dfnifs dblling
     * <dodf>sftNftworkTimfout</dodf>, tiis mftiod tirows b
     * <dodf>jbvb.lbng.SfdurityExdfption</dodf>.
     *
     * @pbrbm fxfdutor  Tif <dodf>Exfdutor</dodf>  implfmfntbtion wiidi will
     * bf usfd by <dodf>sftNftworkTimfout</dodf>.
     * @pbrbm millisfdonds Tif timf in millisfdonds to wbit for tif dbtbbbsf
     * opfrbtion
     *  to domplftf.  If tif JDBC drivfr dofs not support millisfdonds, tif
     * JDBC drivfr will round tif vbluf up to tif nfbrfst sfdond.  If tif
     * timfout pfriod fxpirfs bfforf tif opfrbtion
     * domplftfs, b SQLExdfption will bf tirown.
     * A vbluf of 0 indidbtfs tibt tifrf is not timfout for dbtbbbsf opfrbtions.
     * @tirows jbvb.sql.SQLExdfption if b dbtbbbsf bddfss frror oddurs, tiis
     * mftiod is dbllfd on b dlosfd donnfdtion,
     * tif {@dodf fxfdutor} is {@dodf null},
     * or tif vbluf spfdififd for <dodf>sfdonds</dodf> is lfss tibn 0.
     * @tirows jbvb.lbng.SfdurityExdfption if b sfdurity mbnbgfr fxists bnd its
     *    <dodf>difdkPfrmission</dodf> mftiod dfnifs dblling
     * <dodf>sftNftworkTimfout</dodf>.
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff SfdurityMbnbgfr#difdkPfrmission
     * @sff Stbtfmfnt#sftQufryTimfout
     * @sff #gftNftworkTimfout
     * @sff #bbort
     * @sff Exfdutor
     * @sindf 1.7
     */
    void sftNftworkTimfout(Exfdutor fxfdutor, int millisfdonds) tirows SQLExdfption;


    /**
     * Rftrifvfs tif numbfr of millisfdonds tif drivfr will
     * wbit for b dbtbbbsf rfqufst to domplftf.
     * If tif limit is fxdffdfd, b
     * <dodf>SQLExdfption</dodf> is tirown.
     *
     * @rfturn tif durrfnt timfout limit in millisfdonds; zfro mfbns tifrf is
     *         no limit
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>Connfdtion</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #sftNftworkTimfout
     * @sindf 1.7
     */
    int gftNftworkTimfout() tirows SQLExdfption;
}

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

/**
 * Comprfifnsivf informbtion bbout tif dbtbbbsf bs b wiolf.
 * <P>
 * Tiis intfrfbdf is implfmfntfd by drivfr vfndors to lft usfrs know tif dbpbbilitifs
 * of b Dbtbbbsf Mbnbgfmfnt Systfm (DBMS) in dombinbtion witi
 * tif drivfr bbsfd on JDBC&trbdf; tfdinology
 * ("JDBC drivfr") tibt is usfd witi it.  Difffrfnt rflbtionbl DBMSs oftfn support
 * difffrfnt ffbturfs, implfmfnt ffbturfs in difffrfnt wbys, bnd usf difffrfnt
 * dbtb typfs.  In bddition, b drivfr mby implfmfnt b ffbturf on top of wibt tif
 * DBMS offfrs.  Informbtion rfturnfd by mftiods in tiis intfrfbdf bpplifs
 * to tif dbpbbilitifs of b pbrtidulbr drivfr bnd b pbrtidulbr DBMS working
 * togftifr. Notf tibt bs usfd in tiis dodumfntbtion, tif tfrm "dbtbbbsf" is
 * usfd gfnfridblly to rfffr to boti tif drivfr bnd DBMS.
 * <P>
 * A usfr for tiis intfrfbdf is dommonly b tool tibt nffds to disdovfr iow to
 * dfbl witi tif undfrlying DBMS.  Tiis is fspfdiblly truf for bpplidbtions
 * tibt brf intfndfd to bf usfd witi morf tibn onf DBMS. For fxbmplf, b tool migit usf tif mftiod
 * <dodf>gftTypfInfo</dodf> to find out wibt dbtb typfs dbn bf usfd in b
 * <dodf>CREATE TABLE</dodf> stbtfmfnt.  Or b usfr migit dbll tif mftiod
 * <dodf>supportsCorrflbtfdSubqufrifs</dodf> to sff if it is possiblf to usf
 * b dorrflbtfd subqufry or <dodf>supportsBbtdiUpdbtfs</dodf> to sff if it is
 * possiblf to usf bbtdi updbtfs.
 * <P>
 * Somf <dodf>DbtbbbsfMftbDbtb</dodf> mftiods rfturn lists of informbtion
 * in tif form of <dodf>RfsultSft</dodf> objfdts.
 * Rfgulbr <dodf>RfsultSft</dodf> mftiods, sudi bs
 * <dodf>gftString</dodf> bnd <dodf>gftInt</dodf>, dbn bf usfd
 * to rftrifvf tif dbtb from tifsf <dodf>RfsultSft</dodf> objfdts.  If
 * b givfn form of mftbdbtb is not bvbilbblf, bn fmpty <dodf>RfsultSft</dodf>
 * will bf rfturnfd. Additionbl dolumns bfyond tif dolumns dffinfd to bf
 * rfturnfd by tif <dodf>RfsultSft</dodf> objfdt for b givfn mftiod
 * dbn bf dffinfd by tif JDBC drivfr vfndor bnd must bf bddfssfd
 * by tifir <B>dolumn lbbfl</B>.
 * <P>
 * Somf <dodf>DbtbbbsfMftbDbtb</dodf> mftiods tbkf brgumfnts tibt brf
 * String pbttfrns.  Tifsf brgumfnts bll ibvf nbmfs sudi bs fooPbttfrn.
 * Witiin b pbttfrn String, "%" mfbns mbtdi bny substring of 0 or morf
 * dibrbdtfrs, bnd "_" mfbns mbtdi bny onf dibrbdtfr. Only mftbdbtb
 * fntrifs mbtdiing tif sfbrdi pbttfrn brf rfturnfd. If b sfbrdi pbttfrn
 * brgumfnt is sft to <dodf>null</dodf>, tibt brgumfnt's dritfrion will
 * bf droppfd from tif sfbrdi.
 *
 */
publid intfrfbdf DbtbbbsfMftbDbtb fxtfnds Wrbppfr {

    //----------------------------------------------------------------------
    // First, b vbrifty of minor informbtion bbout tif tbrgft dbtbbbsf.

    /**
     * Rftrifvfs wiftifr tif durrfnt usfr dbn dbll bll tif prodfdurfs
     * rfturnfd by tif mftiod <dodf>gftProdfdurfs</dodf>.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn bllProdfdurfsArfCbllbblf() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tif durrfnt usfr dbn usf bll tif tbblfs rfturnfd
     * by tif mftiod <dodf>gftTbblfs</dodf> in b <dodf>SELECT</dodf>
     * stbtfmfnt.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn bllTbblfsArfSflfdtbblf() tirows SQLExdfption;

    /**
     * Rftrifvfs tif URL for tiis DBMS.
     *
     * @rfturn tif URL for tiis DBMS or <dodf>null</dodf> if it dbnnot bf
     *          gfnfrbtfd
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    String gftURL() tirows SQLExdfption;

    /**
     * Rftrifvfs tif usfr nbmf bs known to tiis dbtbbbsf.
     *
     * @rfturn tif dbtbbbsf usfr nbmf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    String gftUsfrNbmf() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf is in rfbd-only modf.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn isRfbdOnly() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr <dodf>NULL</dodf> vblufs brf sortfd iigi.
     * Sortfd iigi mfbns tibt <dodf>NULL</dodf> vblufs
     * sort iigifr tibn bny otifr vbluf in b dombin.  In bn bsdfnding ordfr,
     * if tiis mftiod rfturns <dodf>truf</dodf>,  <dodf>NULL</dodf> vblufs
     * will bppfbr bt tif fnd. By dontrbst, tif mftiod
     * <dodf>nullsArfSortfdAtEnd</dodf> indidbtfs wiftifr <dodf>NULL</dodf> vblufs
     * brf sortfd bt tif fnd rfgbrdlfss of sort ordfr.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn nullsArfSortfdHigi() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr <dodf>NULL</dodf> vblufs brf sortfd low.
     * Sortfd low mfbns tibt <dodf>NULL</dodf> vblufs
     * sort lowfr tibn bny otifr vbluf in b dombin.  In bn bsdfnding ordfr,
     * if tiis mftiod rfturns <dodf>truf</dodf>,  <dodf>NULL</dodf> vblufs
     * will bppfbr bt tif bfginning. By dontrbst, tif mftiod
     * <dodf>nullsArfSortfdAtStbrt</dodf> indidbtfs wiftifr <dodf>NULL</dodf> vblufs
     * brf sortfd bt tif bfginning rfgbrdlfss of sort ordfr.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn nullsArfSortfdLow() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr <dodf>NULL</dodf> vblufs brf sortfd bt tif stbrt rfgbrdlfss
     * of sort ordfr.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn nullsArfSortfdAtStbrt() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr <dodf>NULL</dodf> vblufs brf sortfd bt tif fnd rfgbrdlfss of
     * sort ordfr.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn nullsArfSortfdAtEnd() tirows SQLExdfption;

    /**
     * Rftrifvfs tif nbmf of tiis dbtbbbsf produdt.
     *
     * @rfturn dbtbbbsf produdt nbmf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    String gftDbtbbbsfProdudtNbmf() tirows SQLExdfption;

    /**
     * Rftrifvfs tif vfrsion numbfr of tiis dbtbbbsf produdt.
     *
     * @rfturn dbtbbbsf vfrsion numbfr
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    String gftDbtbbbsfProdudtVfrsion() tirows SQLExdfption;

    /**
     * Rftrifvfs tif nbmf of tiis JDBC drivfr.
     *
     * @rfturn JDBC drivfr nbmf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    String gftDrivfrNbmf() tirows SQLExdfption;

    /**
     * Rftrifvfs tif vfrsion numbfr of tiis JDBC drivfr bs b <dodf>String</dodf>.
     *
     * @rfturn JDBC drivfr vfrsion
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    String gftDrivfrVfrsion() tirows SQLExdfption;

    /**
     * Rftrifvfs tiis JDBC drivfr's mbjor vfrsion numbfr.
     *
     * @rfturn JDBC drivfr mbjor vfrsion
     */
    int gftDrivfrMbjorVfrsion();

    /**
     * Rftrifvfs tiis JDBC drivfr's minor vfrsion numbfr.
     *
     * @rfturn JDBC drivfr minor vfrsion numbfr
     */
    int gftDrivfrMinorVfrsion();

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf storfs tbblfs in b lodbl filf.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn usfsLodblFilfs() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf usfs b filf for fbdi tbblf.
     *
     * @rfturn <dodf>truf</dodf> if tiis dbtbbbsf usfs b lodbl filf for fbdi tbblf;
     *         <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn usfsLodblFilfPfrTbblf() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf trfbts mixfd dbsf unquotfd SQL idfntififrs bs
     * dbsf sfnsitivf bnd bs b rfsult storfs tifm in mixfd dbsf.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsMixfdCbsfIdfntififrs() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf trfbts mixfd dbsf unquotfd SQL idfntififrs bs
     * dbsf insfnsitivf bnd storfs tifm in uppfr dbsf.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn storfsUppfrCbsfIdfntififrs() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf trfbts mixfd dbsf unquotfd SQL idfntififrs bs
     * dbsf insfnsitivf bnd storfs tifm in lowfr dbsf.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn storfsLowfrCbsfIdfntififrs() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf trfbts mixfd dbsf unquotfd SQL idfntififrs bs
     * dbsf insfnsitivf bnd storfs tifm in mixfd dbsf.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn storfsMixfdCbsfIdfntififrs() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf trfbts mixfd dbsf quotfd SQL idfntififrs bs
     * dbsf sfnsitivf bnd bs b rfsult storfs tifm in mixfd dbsf.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsMixfdCbsfQuotfdIdfntififrs() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf trfbts mixfd dbsf quotfd SQL idfntififrs bs
     * dbsf insfnsitivf bnd storfs tifm in uppfr dbsf.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn storfsUppfrCbsfQuotfdIdfntififrs() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf trfbts mixfd dbsf quotfd SQL idfntififrs bs
     * dbsf insfnsitivf bnd storfs tifm in lowfr dbsf.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn storfsLowfrCbsfQuotfdIdfntififrs() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf trfbts mixfd dbsf quotfd SQL idfntififrs bs
     * dbsf insfnsitivf bnd storfs tifm in mixfd dbsf.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn storfsMixfdCbsfQuotfdIdfntififrs() tirows SQLExdfption;

    /**
     * Rftrifvfs tif string usfd to quotf SQL idfntififrs.
     * Tiis mftiod rfturns b spbdf " " if idfntififr quoting is not supportfd.
     *
     * @rfturn tif quoting string or b spbdf if quoting is not supportfd
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    String gftIdfntififrQuotfString() tirows SQLExdfption;

    /**
     * Rftrifvfs b dommb-sfpbrbtfd list of bll of tiis dbtbbbsf's SQL kfywords
     * tibt brf NOT blso SQL:2003 kfywords.
     *
     * @rfturn tif list of tiis dbtbbbsf's kfywords tibt brf not blso
     *         SQL:2003 kfywords
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    String gftSQLKfywords() tirows SQLExdfption;

    /**
     * Rftrifvfs b dommb-sfpbrbtfd list of mbti fundtions bvbilbblf witi
     * tiis dbtbbbsf.  Tifsf brf tif Opfn /Opfn CLI mbti fundtion nbmfs usfd in
     * tif JDBC fundtion fsdbpf dlbusf.
     *
     * @rfturn tif list of mbti fundtions supportfd by tiis dbtbbbsf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    String gftNumfridFundtions() tirows SQLExdfption;

    /**
     * Rftrifvfs b dommb-sfpbrbtfd list of string fundtions bvbilbblf witi
     * tiis dbtbbbsf.  Tifsf brf tif  Opfn Group CLI string fundtion nbmfs usfd
     * in tif JDBC fundtion fsdbpf dlbusf.
     *
     * @rfturn tif list of string fundtions supportfd by tiis dbtbbbsf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    String gftStringFundtions() tirows SQLExdfption;

    /**
     * Rftrifvfs b dommb-sfpbrbtfd list of systfm fundtions bvbilbblf witi
     * tiis dbtbbbsf.  Tifsf brf tif  Opfn Group CLI systfm fundtion nbmfs usfd
     * in tif JDBC fundtion fsdbpf dlbusf.
     *
     * @rfturn b list of systfm fundtions supportfd by tiis dbtbbbsf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    String gftSystfmFundtions() tirows SQLExdfption;

    /**
     * Rftrifvfs b dommb-sfpbrbtfd list of tif timf bnd dbtf fundtions bvbilbblf
     * witi tiis dbtbbbsf.
     *
     * @rfturn tif list of timf bnd dbtf fundtions supportfd by tiis dbtbbbsf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    String gftTimfDbtfFundtions() tirows SQLExdfption;

    /**
     * Rftrifvfs tif string tibt dbn bf usfd to fsdbpf wilddbrd dibrbdtfrs.
     * Tiis is tif string tibt dbn bf usfd to fsdbpf '_' or '%' in
     * tif dbtblog sfbrdi pbrbmftfrs tibt brf b pbttfrn (bnd tifrfforf usf onf
     * of tif wilddbrd dibrbdtfrs).
     *
     * <P>Tif '_' dibrbdtfr rfprfsfnts bny singlf dibrbdtfr;
     * tif '%' dibrbdtfr rfprfsfnts bny sfqufndf of zfro or
     * morf dibrbdtfrs.
     *
     * @rfturn tif string usfd to fsdbpf wilddbrd dibrbdtfrs
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    String gftSfbrdiStringEsdbpf() tirows SQLExdfption;

    /**
     * Rftrifvfs bll tif "fxtrb" dibrbdtfrs tibt dbn bf usfd in unquotfd
     * idfntififr nbmfs (tiosf bfyond b-z, A-Z, 0-9 bnd _).
     *
     * @rfturn tif string dontbining tif fxtrb dibrbdtfrs
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    String gftExtrbNbmfCibrbdtfrs() tirows SQLExdfption;

    //--------------------------------------------------------------------
    // Fundtions dfsdribing wiidi ffbturfs brf supportfd.

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports <dodf>ALTER TABLE</dodf>
     * witi bdd dolumn.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsAltfrTbblfWitiAddColumn() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports <dodf>ALTER TABLE</dodf>
     * witi drop dolumn.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsAltfrTbblfWitiDropColumn() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports dolumn blibsing.
     *
     * <P>If so, tif SQL AS dlbusf dbn bf usfd to providf nbmfs for
     * domputfd dolumns or to providf blibs nbmfs for dolumns bs
     * rfquirfd.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsColumnAlibsing() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports dondbtfnbtions bftwffn
     * <dodf>NULL</dodf> bnd non-<dodf>NULL</dodf> vblufs bfing
     * <dodf>NULL</dodf>.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn nullPlusNonNullIsNull() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports tif JDBC sdblbr fundtion
     * <dodf>CONVERT</dodf> for tif donvfrsion of onf JDBC typf to bnotifr.
     * Tif JDBC typfs brf tif gfnfrid SQL dbtb typfs dffinfd
     * in <dodf>jbvb.sql.Typfs</dodf>.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsConvfrt() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports tif JDBC sdblbr fundtion
     * <dodf>CONVERT</dodf> for donvfrsions bftwffn tif JDBC typfs <i>fromTypf</i>
     * bnd <i>toTypf</i>.  Tif JDBC typfs brf tif gfnfrid SQL dbtb typfs dffinfd
     * in <dodf>jbvb.sql.Typfs</dodf>.
     *
     * @pbrbm fromTypf tif typf to donvfrt from; onf of tif typf dodfs from
     *        tif dlbss <dodf>jbvb.sql.Typfs</dodf>
     * @pbrbm toTypf tif typf to donvfrt to; onf of tif typf dodfs from
     *        tif dlbss <dodf>jbvb.sql.Typfs</dodf>
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sff Typfs
     */
    boolfbn supportsConvfrt(int fromTypf, int toTypf) tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports tbblf dorrflbtion nbmfs.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsTbblfCorrflbtionNbmfs() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr, wifn tbblf dorrflbtion nbmfs brf supportfd, tify
     * brf rfstridtfd to bfing difffrfnt from tif nbmfs of tif tbblfs.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsDifffrfntTbblfCorrflbtionNbmfs() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports fxprfssions in
     * <dodf>ORDER BY</dodf> lists.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsExprfssionsInOrdfrBy() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports using b dolumn tibt is
     * not in tif <dodf>SELECT</dodf> stbtfmfnt in bn
     * <dodf>ORDER BY</dodf> dlbusf.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsOrdfrByUnrflbtfd() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports somf form of
     * <dodf>GROUP BY</dodf> dlbusf.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsGroupBy() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports using b dolumn tibt is
     * not in tif <dodf>SELECT</dodf> stbtfmfnt in b
     * <dodf>GROUP BY</dodf> dlbusf.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsGroupByUnrflbtfd() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports using dolumns not indludfd in
     * tif <dodf>SELECT</dodf> stbtfmfnt in b <dodf>GROUP BY</dodf> dlbusf
     * providfd tibt bll of tif dolumns in tif <dodf>SELECT</dodf> stbtfmfnt
     * brf indludfd in tif <dodf>GROUP BY</dodf> dlbusf.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsGroupByBfyondSflfdt() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports spfdifying b
     * <dodf>LIKE</dodf> fsdbpf dlbusf.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsLikfEsdbpfClbusf() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports gftting multiplf
     * <dodf>RfsultSft</dodf> objfdts from b singlf dbll to tif
     * mftiod <dodf>fxfdutf</dodf>.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsMultiplfRfsultSfts() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf bllows ibving multiplf
     * trbnsbdtions opfn bt ondf (on difffrfnt donnfdtions).
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsMultiplfTrbnsbdtions() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr dolumns in tiis dbtbbbsf mby bf dffinfd bs non-nullbblf.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsNonNullbblfColumns() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports tif ODBC Minimum SQL grbmmbr.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsMinimumSQLGrbmmbr() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports tif ODBC Corf SQL grbmmbr.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsCorfSQLGrbmmbr() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports tif ODBC Extfndfd SQL grbmmbr.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsExtfndfdSQLGrbmmbr() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports tif ANSI92 fntry lfvfl SQL
     * grbmmbr.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsANSI92EntryLfvflSQL() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports tif ANSI92 intfrmfdibtf SQL grbmmbr supportfd.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsANSI92IntfrmfdibtfSQL() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports tif ANSI92 full SQL grbmmbr supportfd.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsANSI92FullSQL() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports tif SQL Intfgrity
     * Enibndfmfnt Fbdility.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsIntfgrityEnibndfmfntFbdility() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports somf form of outfr join.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsOutfrJoins() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports full nfstfd outfr joins.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsFullOutfrJoins() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf providfs limitfd support for outfr
     * joins.  (Tiis will bf <dodf>truf</dodf> if tif mftiod
     * <dodf>supportsFullOutfrJoins</dodf> rfturns <dodf>truf</dodf>).
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsLimitfdOutfrJoins() tirows SQLExdfption;

    /**
     * Rftrifvfs tif dbtbbbsf vfndor's prfffrrfd tfrm for "sdifmb".
     *
     * @rfturn tif vfndor tfrm for "sdifmb"
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    String gftSdifmbTfrm() tirows SQLExdfption;

    /**
     * Rftrifvfs tif dbtbbbsf vfndor's prfffrrfd tfrm for "prodfdurf".
     *
     * @rfturn tif vfndor tfrm for "prodfdurf"
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    String gftProdfdurfTfrm() tirows SQLExdfption;

    /**
     * Rftrifvfs tif dbtbbbsf vfndor's prfffrrfd tfrm for "dbtblog".
     *
     * @rfturn tif vfndor tfrm for "dbtblog"
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    String gftCbtblogTfrm() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr b dbtblog bppfbrs bt tif stbrt of b fully qublififd
     * tbblf nbmf.  If not, tif dbtblog bppfbrs bt tif fnd.
     *
     * @rfturn <dodf>truf</dodf> if tif dbtblog nbmf bppfbrs bt tif bfginning
     *         of b fully qublififd tbblf nbmf; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn isCbtblogAtStbrt() tirows SQLExdfption;

    /**
     * Rftrifvfs tif <dodf>String</dodf> tibt tiis dbtbbbsf usfs bs tif
     * sfpbrbtor bftwffn b dbtblog bnd tbblf nbmf.
     *
     * @rfturn tif sfpbrbtor string
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    String gftCbtblogSfpbrbtor() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr b sdifmb nbmf dbn bf usfd in b dbtb mbnipulbtion stbtfmfnt.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsSdifmbsInDbtbMbnipulbtion() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr b sdifmb nbmf dbn bf usfd in b prodfdurf dbll stbtfmfnt.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsSdifmbsInProdfdurfCblls() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr b sdifmb nbmf dbn bf usfd in b tbblf dffinition stbtfmfnt.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsSdifmbsInTbblfDffinitions() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr b sdifmb nbmf dbn bf usfd in bn indfx dffinition stbtfmfnt.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsSdifmbsInIndfxDffinitions() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr b sdifmb nbmf dbn bf usfd in b privilfgf dffinition stbtfmfnt.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsSdifmbsInPrivilfgfDffinitions() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr b dbtblog nbmf dbn bf usfd in b dbtb mbnipulbtion stbtfmfnt.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsCbtblogsInDbtbMbnipulbtion() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr b dbtblog nbmf dbn bf usfd in b prodfdurf dbll stbtfmfnt.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsCbtblogsInProdfdurfCblls() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr b dbtblog nbmf dbn bf usfd in b tbblf dffinition stbtfmfnt.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsCbtblogsInTbblfDffinitions() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr b dbtblog nbmf dbn bf usfd in bn indfx dffinition stbtfmfnt.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsCbtblogsInIndfxDffinitions() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr b dbtblog nbmf dbn bf usfd in b privilfgf dffinition stbtfmfnt.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsCbtblogsInPrivilfgfDffinitions() tirows SQLExdfption;


    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports positionfd <dodf>DELETE</dodf>
     * stbtfmfnts.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsPositionfdDflftf() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports positionfd <dodf>UPDATE</dodf>
     * stbtfmfnts.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsPositionfdUpdbtf() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports <dodf>SELECT FOR UPDATE</dodf>
     * stbtfmfnts.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsSflfdtForUpdbtf() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports storfd prodfdurf dblls
     * tibt usf tif storfd prodfdurf fsdbpf syntbx.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsStorfdProdfdurfs() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports subqufrifs in dompbrison
     * fxprfssions.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsSubqufrifsInCompbrisons() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports subqufrifs in
     * <dodf>EXISTS</dodf> fxprfssions.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsSubqufrifsInExists() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports subqufrifs in
     * <dodf>IN</dodf> fxprfssions.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsSubqufrifsInIns() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports subqufrifs in qubntififd
     * fxprfssions.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsSubqufrifsInQubntififds() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports dorrflbtfd subqufrifs.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsCorrflbtfdSubqufrifs() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports SQL <dodf>UNION</dodf>.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsUnion() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports SQL <dodf>UNION ALL</dodf>.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsUnionAll() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports kffping dursors opfn
     * bdross dommits.
     *
     * @rfturn <dodf>truf</dodf> if dursors blwbys rfmbin opfn;
     *       <dodf>fblsf</dodf> if tify migit not rfmbin opfn
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsOpfnCursorsAdrossCommit() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports kffping dursors opfn
     * bdross rollbbdks.
     *
     * @rfturn <dodf>truf</dodf> if dursors blwbys rfmbin opfn;
     *       <dodf>fblsf</dodf> if tify migit not rfmbin opfn
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsOpfnCursorsAdrossRollbbdk() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports kffping stbtfmfnts opfn
     * bdross dommits.
     *
     * @rfturn <dodf>truf</dodf> if stbtfmfnts blwbys rfmbin opfn;
     *       <dodf>fblsf</dodf> if tify migit not rfmbin opfn
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsOpfnStbtfmfntsAdrossCommit() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports kffping stbtfmfnts opfn
     * bdross rollbbdks.
     *
     * @rfturn <dodf>truf</dodf> if stbtfmfnts blwbys rfmbin opfn;
     *       <dodf>fblsf</dodf> if tify migit not rfmbin opfn
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsOpfnStbtfmfntsAdrossRollbbdk() tirows SQLExdfption;



    //----------------------------------------------------------------------
    // Tif following group of mftiods fxposfs vbrious limitbtions
    // bbsfd on tif tbrgft dbtbbbsf witi tif durrfnt drivfr.
    // Unlfss otifrwisf spfdififd, b rfsult of zfro mfbns tifrf is no
    // limit, or tif limit is not known.

    /**
     * Rftrifvfs tif mbximum numbfr of ifx dibrbdtfrs tiis dbtbbbsf bllows in bn
     * inlinf binbry litfrbl.
     *
     * @rfturn mbx tif mbximum lfngti (in ifx dibrbdtfrs) for b binbry litfrbl;
     *      b rfsult of zfro mfbns tibt tifrf is no limit or tif limit
     *      is not known
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    int gftMbxBinbryLitfrblLfngti() tirows SQLExdfption;

    /**
     * Rftrifvfs tif mbximum numbfr of dibrbdtfrs tiis dbtbbbsf bllows
     * for b dibrbdtfr litfrbl.
     *
     * @rfturn tif mbximum numbfr of dibrbdtfrs bllowfd for b dibrbdtfr litfrbl;
     *      b rfsult of zfro mfbns tibt tifrf is no limit or tif limit is
     *      not known
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    int gftMbxCibrLitfrblLfngti() tirows SQLExdfption;

    /**
     * Rftrifvfs tif mbximum numbfr of dibrbdtfrs tiis dbtbbbsf bllows
     * for b dolumn nbmf.
     *
     * @rfturn tif mbximum numbfr of dibrbdtfrs bllowfd for b dolumn nbmf;
     *      b rfsult of zfro mfbns tibt tifrf is no limit or tif limit
     *      is not known
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    int gftMbxColumnNbmfLfngti() tirows SQLExdfption;

    /**
     * Rftrifvfs tif mbximum numbfr of dolumns tiis dbtbbbsf bllows in b
     * <dodf>GROUP BY</dodf> dlbusf.
     *
     * @rfturn tif mbximum numbfr of dolumns bllowfd;
     *      b rfsult of zfro mfbns tibt tifrf is no limit or tif limit
     *      is not known
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    int gftMbxColumnsInGroupBy() tirows SQLExdfption;

    /**
     * Rftrifvfs tif mbximum numbfr of dolumns tiis dbtbbbsf bllows in bn indfx.
     *
     * @rfturn tif mbximum numbfr of dolumns bllowfd;
     *      b rfsult of zfro mfbns tibt tifrf is no limit or tif limit
     *      is not known
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    int gftMbxColumnsInIndfx() tirows SQLExdfption;

    /**
     * Rftrifvfs tif mbximum numbfr of dolumns tiis dbtbbbsf bllows in bn
     * <dodf>ORDER BY</dodf> dlbusf.
     *
     * @rfturn tif mbximum numbfr of dolumns bllowfd;
     *      b rfsult of zfro mfbns tibt tifrf is no limit or tif limit
     *      is not known
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    int gftMbxColumnsInOrdfrBy() tirows SQLExdfption;

    /**
     * Rftrifvfs tif mbximum numbfr of dolumns tiis dbtbbbsf bllows in b
     * <dodf>SELECT</dodf> list.
     *
     * @rfturn tif mbximum numbfr of dolumns bllowfd;
     *      b rfsult of zfro mfbns tibt tifrf is no limit or tif limit
     *      is not known
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    int gftMbxColumnsInSflfdt() tirows SQLExdfption;

    /**
     * Rftrifvfs tif mbximum numbfr of dolumns tiis dbtbbbsf bllows in b tbblf.
     *
     * @rfturn tif mbximum numbfr of dolumns bllowfd;
     *      b rfsult of zfro mfbns tibt tifrf is no limit or tif limit
     *      is not known
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    int gftMbxColumnsInTbblf() tirows SQLExdfption;

    /**
     * Rftrifvfs tif mbximum numbfr of dondurrfnt donnfdtions to tiis
     * dbtbbbsf tibt brf possiblf.
     *
     * @rfturn tif mbximum numbfr of bdtivf donnfdtions possiblf bt onf timf;
     *      b rfsult of zfro mfbns tibt tifrf is no limit or tif limit
     *      is not known
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    int gftMbxConnfdtions() tirows SQLExdfption;

    /**
     * Rftrifvfs tif mbximum numbfr of dibrbdtfrs tibt tiis dbtbbbsf bllows in b
     * dursor nbmf.
     *
     * @rfturn tif mbximum numbfr of dibrbdtfrs bllowfd in b dursor nbmf;
     *      b rfsult of zfro mfbns tibt tifrf is no limit or tif limit
     *      is not known
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    int gftMbxCursorNbmfLfngti() tirows SQLExdfption;

    /**
     * Rftrifvfs tif mbximum numbfr of bytfs tiis dbtbbbsf bllows for bn
     * indfx, indluding bll of tif pbrts of tif indfx.
     *
     * @rfturn tif mbximum numbfr of bytfs bllowfd; tiis limit indludfs tif
     *      dompositf of bll tif donstitufnt pbrts of tif indfx;
     *      b rfsult of zfro mfbns tibt tifrf is no limit or tif limit
     *      is not known
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    int gftMbxIndfxLfngti() tirows SQLExdfption;

    /**
     * Rftrifvfs tif mbximum numbfr of dibrbdtfrs tibt tiis dbtbbbsf bllows in b
     * sdifmb nbmf.
     *
     * @rfturn tif mbximum numbfr of dibrbdtfrs bllowfd in b sdifmb nbmf;
     *      b rfsult of zfro mfbns tibt tifrf is no limit or tif limit
     *      is not known
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    int gftMbxSdifmbNbmfLfngti() tirows SQLExdfption;

    /**
     * Rftrifvfs tif mbximum numbfr of dibrbdtfrs tibt tiis dbtbbbsf bllows in b
     * prodfdurf nbmf.
     *
     * @rfturn tif mbximum numbfr of dibrbdtfrs bllowfd in b prodfdurf nbmf;
     *      b rfsult of zfro mfbns tibt tifrf is no limit or tif limit
     *      is not known
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    int gftMbxProdfdurfNbmfLfngti() tirows SQLExdfption;

    /**
     * Rftrifvfs tif mbximum numbfr of dibrbdtfrs tibt tiis dbtbbbsf bllows in b
     * dbtblog nbmf.
     *
     * @rfturn tif mbximum numbfr of dibrbdtfrs bllowfd in b dbtblog nbmf;
     *      b rfsult of zfro mfbns tibt tifrf is no limit or tif limit
     *      is not known
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    int gftMbxCbtblogNbmfLfngti() tirows SQLExdfption;

    /**
     * Rftrifvfs tif mbximum numbfr of bytfs tiis dbtbbbsf bllows in
     * b singlf row.
     *
     * @rfturn tif mbximum numbfr of bytfs bllowfd for b row; b rfsult of
     *         zfro mfbns tibt tifrf is no limit or tif limit is not known
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    int gftMbxRowSizf() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tif rfturn vbluf for tif mftiod
     * <dodf>gftMbxRowSizf</dodf> indludfs tif SQL dbtb typfs
     * <dodf>LONGVARCHAR</dodf> bnd <dodf>LONGVARBINARY</dodf>.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn dofsMbxRowSizfIndludfBlobs() tirows SQLExdfption;

    /**
     * Rftrifvfs tif mbximum numbfr of dibrbdtfrs tiis dbtbbbsf bllows in
     * bn SQL stbtfmfnt.
     *
     * @rfturn tif mbximum numbfr of dibrbdtfrs bllowfd for bn SQL stbtfmfnt;
     *      b rfsult of zfro mfbns tibt tifrf is no limit or tif limit
     *      is not known
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    int gftMbxStbtfmfntLfngti() tirows SQLExdfption;

    /**
     * Rftrifvfs tif mbximum numbfr of bdtivf stbtfmfnts to tiis dbtbbbsf
     * tibt dbn bf opfn bt tif sbmf timf.
     *
     * @rfturn tif mbximum numbfr of stbtfmfnts tibt dbn bf opfn bt onf timf;
     *      b rfsult of zfro mfbns tibt tifrf is no limit or tif limit
     *      is not known
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    int gftMbxStbtfmfnts() tirows SQLExdfption;

    /**
     * Rftrifvfs tif mbximum numbfr of dibrbdtfrs tiis dbtbbbsf bllows in
     * b tbblf nbmf.
     *
     * @rfturn tif mbximum numbfr of dibrbdtfrs bllowfd for b tbblf nbmf;
     *      b rfsult of zfro mfbns tibt tifrf is no limit or tif limit
     *      is not known
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    int gftMbxTbblfNbmfLfngti() tirows SQLExdfption;

    /**
     * Rftrifvfs tif mbximum numbfr of tbblfs tiis dbtbbbsf bllows in b
     * <dodf>SELECT</dodf> stbtfmfnt.
     *
     * @rfturn tif mbximum numbfr of tbblfs bllowfd in b <dodf>SELECT</dodf>
     *         stbtfmfnt; b rfsult of zfro mfbns tibt tifrf is no limit or
     *         tif limit is not known
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    int gftMbxTbblfsInSflfdt() tirows SQLExdfption;

    /**
     * Rftrifvfs tif mbximum numbfr of dibrbdtfrs tiis dbtbbbsf bllows in
     * b usfr nbmf.
     *
     * @rfturn tif mbximum numbfr of dibrbdtfrs bllowfd for b usfr nbmf;
     *      b rfsult of zfro mfbns tibt tifrf is no limit or tif limit
     *      is not known
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    int gftMbxUsfrNbmfLfngti() tirows SQLExdfption;

    //----------------------------------------------------------------------

    /**
     * Rftrifvfs tiis dbtbbbsf's dffbult trbnsbdtion isolbtion lfvfl.  Tif
     * possiblf vblufs brf dffinfd in <dodf>jbvb.sql.Connfdtion</dodf>.
     *
     * @rfturn tif dffbult isolbtion lfvfl
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sff Connfdtion
     */
    int gftDffbultTrbnsbdtionIsolbtion() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports trbnsbdtions. If not, invoking tif
     * mftiod <dodf>dommit</dodf> is b noop, bnd tif isolbtion lfvfl is
     * <dodf>TRANSACTION_NONE</dodf>.
     *
     * @rfturn <dodf>truf</dodf> if trbnsbdtions brf supportfd;
     *         <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsTrbnsbdtions() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports tif givfn trbnsbdtion isolbtion lfvfl.
     *
     * @pbrbm lfvfl onf of tif trbnsbdtion isolbtion lfvfls dffinfd in
     *         <dodf>jbvb.sql.Connfdtion</dodf>
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sff Connfdtion
     */
    boolfbn supportsTrbnsbdtionIsolbtionLfvfl(int lfvfl)
        tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports boti dbtb dffinition bnd
     * dbtb mbnipulbtion stbtfmfnts witiin b trbnsbdtion.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsDbtbDffinitionAndDbtbMbnipulbtionTrbnsbdtions()
        tirows SQLExdfption;
    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports only dbtb mbnipulbtion
     * stbtfmfnts witiin b trbnsbdtion.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn supportsDbtbMbnipulbtionTrbnsbdtionsOnly()
        tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr b dbtb dffinition stbtfmfnt witiin b trbnsbdtion fordfs
     * tif trbnsbdtion to dommit.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn dbtbDffinitionCbusfsTrbnsbdtionCommit()
        tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf ignorfs b dbtb dffinition stbtfmfnt
     * witiin b trbnsbdtion.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    boolfbn dbtbDffinitionIgnorfdInTrbnsbdtions()
        tirows SQLExdfption;

    /**
     * Rftrifvfs b dfsdription of tif storfd prodfdurfs bvbilbblf in tif givfn
     * dbtblog.
     * <P>
     * Only prodfdurf dfsdriptions mbtdiing tif sdifmb bnd
     * prodfdurf nbmf dritfrib brf rfturnfd.  Tify brf ordfrfd by
     * <dodf>PROCEDURE_CAT</dodf>, <dodf>PROCEDURE_SCHEM</dodf>,
     * <dodf>PROCEDURE_NAME</dodf> bnd <dodf>SPECIFIC_ NAME</dodf>.
     *
     * <P>Ebdi prodfdurf dfsdription ibs tif tif following dolumns:
     *  <OL>
     *  <LI><B>PROCEDURE_CAT</B> String {@dodf =>} prodfdurf dbtblog (mby bf <dodf>null</dodf>)
     *  <LI><B>PROCEDURE_SCHEM</B> String {@dodf =>} prodfdurf sdifmb (mby bf <dodf>null</dodf>)
     *  <LI><B>PROCEDURE_NAME</B> String {@dodf =>} prodfdurf nbmf
     *  <LI> rfsfrvfd for futurf usf
     *  <LI> rfsfrvfd for futurf usf
     *  <LI> rfsfrvfd for futurf usf
     *  <LI><B>REMARKS</B> String {@dodf =>} fxplbnbtory dommfnt on tif prodfdurf
     *  <LI><B>PROCEDURE_TYPE</B> siort {@dodf =>} kind of prodfdurf:
     *      <UL>
     *      <LI> prodfdurfRfsultUnknown - Cbnnot dftfrminf if  b rfturn vbluf
     *       will bf rfturnfd
     *      <LI> prodfdurfNoRfsult - Dofs not rfturn b rfturn vbluf
     *      <LI> prodfdurfRfturnsRfsult - Rfturns b rfturn vbluf
     *      </UL>
     *  <LI><B>SPECIFIC_NAME</B> String  {@dodf =>} Tif nbmf wiidi uniqufly idfntififs tiis
     * prodfdurf witiin its sdifmb.
     *  </OL>
     * <p>
     * A usfr mby not ibvf pfrmissions to fxfdutf bny of tif prodfdurfs tibt brf
     * rfturnfd by <dodf>gftProdfdurfs</dodf>
     *
     * @pbrbm dbtblog b dbtblog nbmf; must mbtdi tif dbtblog nbmf bs it
     *        is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b dbtblog;
     *        <dodf>null</dodf> mfbns tibt tif dbtblog nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm sdifmbPbttfrn b sdifmb nbmf pbttfrn; must mbtdi tif sdifmb nbmf
     *        bs it is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b sdifmb;
     *        <dodf>null</dodf> mfbns tibt tif sdifmb nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm prodfdurfNbmfPbttfrn b prodfdurf nbmf pbttfrn; must mbtdi tif
     *        prodfdurf nbmf bs it is storfd in tif dbtbbbsf
     * @rfturn <dodf>RfsultSft</dodf> - fbdi row is b prodfdurf dfsdription
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sff #gftSfbrdiStringEsdbpf
     */
    RfsultSft gftProdfdurfs(String dbtblog, String sdifmbPbttfrn,
                            String prodfdurfNbmfPbttfrn) tirows SQLExdfption;

    /**
     * Indidbtfs tibt it is not known wiftifr tif prodfdurf rfturns
     * b rfsult.
     * <P>
     * A possiblf vbluf for dolumn <dodf>PROCEDURE_TYPE</dodf> in tif
     * <dodf>RfsultSft</dodf> objfdt rfturnfd by tif mftiod
     * <dodf>gftProdfdurfs</dodf>.
     */
    int prodfdurfRfsultUnknown  = 0;

    /**
     * Indidbtfs tibt tif prodfdurf dofs not rfturn b rfsult.
     * <P>
     * A possiblf vbluf for dolumn <dodf>PROCEDURE_TYPE</dodf> in tif
     * <dodf>RfsultSft</dodf> objfdt rfturnfd by tif mftiod
     * <dodf>gftProdfdurfs</dodf>.
     */
    int prodfdurfNoRfsult               = 1;

    /**
     * Indidbtfs tibt tif prodfdurf rfturns b rfsult.
     * <P>
     * A possiblf vbluf for dolumn <dodf>PROCEDURE_TYPE</dodf> in tif
     * <dodf>RfsultSft</dodf> objfdt rfturnfd by tif mftiod
     * <dodf>gftProdfdurfs</dodf>.
     */
    int prodfdurfRfturnsRfsult  = 2;

    /**
     * Rftrifvfs b dfsdription of tif givfn dbtblog's storfd prodfdurf pbrbmftfr
     * bnd rfsult dolumns.
     *
     * <P>Only dfsdriptions mbtdiing tif sdifmb, prodfdurf bnd
     * pbrbmftfr nbmf dritfrib brf rfturnfd.  Tify brf ordfrfd by
     * PROCEDURE_CAT, PROCEDURE_SCHEM, PROCEDURE_NAME bnd SPECIFIC_NAME. Witiin tiis, tif rfturn vbluf,
     * if bny, is first. Nfxt brf tif pbrbmftfr dfsdriptions in dbll
     * ordfr. Tif dolumn dfsdriptions follow in dolumn numbfr ordfr.
     *
     * <P>Ebdi row in tif <dodf>RfsultSft</dodf> is b pbrbmftfr dfsdription or
     * dolumn dfsdription witi tif following fiflds:
     *  <OL>
     *  <LI><B>PROCEDURE_CAT</B> String {@dodf =>} prodfdurf dbtblog (mby bf <dodf>null</dodf>)
     *  <LI><B>PROCEDURE_SCHEM</B> String {@dodf =>} prodfdurf sdifmb (mby bf <dodf>null</dodf>)
     *  <LI><B>PROCEDURE_NAME</B> String {@dodf =>} prodfdurf nbmf
     *  <LI><B>COLUMN_NAME</B> String {@dodf =>} dolumn/pbrbmftfr nbmf
     *  <LI><B>COLUMN_TYPE</B> Siort {@dodf =>} kind of dolumn/pbrbmftfr:
     *      <UL>
     *      <LI> prodfdurfColumnUnknown - nobody knows
     *      <LI> prodfdurfColumnIn - IN pbrbmftfr
     *      <LI> prodfdurfColumnInOut - INOUT pbrbmftfr
     *      <LI> prodfdurfColumnOut - OUT pbrbmftfr
     *      <LI> prodfdurfColumnRfturn - prodfdurf rfturn vbluf
     *      <LI> prodfdurfColumnRfsult - rfsult dolumn in <dodf>RfsultSft</dodf>
     *      </UL>
     *  <LI><B>DATA_TYPE</B> int {@dodf =>} SQL typf from jbvb.sql.Typfs
     *  <LI><B>TYPE_NAME</B> String {@dodf =>} SQL typf nbmf, for b UDT typf tif
     *  typf nbmf is fully qublififd
     *  <LI><B>PRECISION</B> int {@dodf =>} prfdision
     *  <LI><B>LENGTH</B> int {@dodf =>} lfngti in bytfs of dbtb
     *  <LI><B>SCALE</B> siort {@dodf =>} sdblf -  null is rfturnfd for dbtb typfs wifrf
     * SCALE is not bpplidbblf.
     *  <LI><B>RADIX</B> siort {@dodf =>} rbdix
     *  <LI><B>NULLABLE</B> siort {@dodf =>} dbn it dontbin NULL.
     *      <UL>
     *      <LI> prodfdurfNoNulls - dofs not bllow NULL vblufs
     *      <LI> prodfdurfNullbblf - bllows NULL vblufs
     *      <LI> prodfdurfNullbblfUnknown - nullbbility unknown
     *      </UL>
     *  <LI><B>REMARKS</B> String {@dodf =>} dommfnt dfsdribing pbrbmftfr/dolumn
     *  <LI><B>COLUMN_DEF</B> String {@dodf =>} dffbult vbluf for tif dolumn, wiidi siould bf intfrprftfd bs b string wifn tif vbluf is fndlosfd in singlf quotfs (mby bf <dodf>null</dodf>)
     *      <UL>
     *      <LI> Tif string NULL (not fndlosfd in quotfs) - if NULL wbs spfdififd bs tif dffbult vbluf
     *      <LI> TRUNCATE (not fndlosfd in quotfs)        - if tif spfdififd dffbult vbluf dbnnot bf rfprfsfntfd witiout trundbtion
     *      <LI> NULL                                     - if b dffbult vbluf wbs not spfdififd
     *      </UL>
     *  <LI><B>SQL_DATA_TYPE</B> int  {@dodf =>} rfsfrvfd for futurf usf
     *  <LI><B>SQL_DATETIME_SUB</B> int  {@dodf =>} rfsfrvfd for futurf usf
     *  <LI><B>CHAR_OCTET_LENGTH</B> int  {@dodf =>} tif mbximum lfngti of binbry bnd dibrbdtfr bbsfd dolumns.  For bny otifr dbtbtypf tif rfturnfd vbluf is b
     * NULL
     *  <LI><B>ORDINAL_POSITION</B> int  {@dodf =>} tif ordinbl position, stbrting from 1, for tif input bnd output pbrbmftfrs for b prodfdurf. A vbluf of 0
     *is rfturnfd if tiis row dfsdribfs tif prodfdurf's rfturn vbluf.  For rfsult sft dolumns, it is tif
     *ordinbl position of tif dolumn in tif rfsult sft stbrting from 1.  If tifrf brf
     *multiplf rfsult sfts, tif dolumn ordinbl positions brf implfmfntbtion
     * dffinfd.
     *  <LI><B>IS_NULLABLE</B> String  {@dodf =>} ISO rulfs brf usfd to dftfrminf tif nullbbility for b dolumn.
     *       <UL>
     *       <LI> YES           --- if tif dolumn dbn indludf NULLs
     *       <LI> NO            --- if tif dolumn dbnnot indludf NULLs
     *       <LI> fmpty string  --- if tif nullbbility for tif
     * dolumn is unknown
     *       </UL>
     *  <LI><B>SPECIFIC_NAME</B> String  {@dodf =>} tif nbmf wiidi uniqufly idfntififs tiis prodfdurf witiin its sdifmb.
     *  </OL>
     *
     * <P><B>Notf:</B> Somf dbtbbbsfs mby not rfturn tif dolumn
     * dfsdriptions for b prodfdurf.
     *
     * <p>Tif PRECISION dolumn rfprfsfnts tif spfdififd dolumn sizf for tif givfn dolumn.
     * For numfrid dbtb, tiis is tif mbximum prfdision.  For dibrbdtfr dbtb, tiis is tif lfngti in dibrbdtfrs.
     * For dbtftimf dbtbtypfs, tiis is tif lfngti in dibrbdtfrs of tif String rfprfsfntbtion (bssuming tif
     * mbximum bllowfd prfdision of tif frbdtionbl sfdonds domponfnt). For binbry dbtb, tiis is tif lfngti in bytfs.  For tif ROWID dbtbtypf,
     * tiis is tif lfngti in bytfs. Null is rfturnfd for dbtb typfs wifrf tif
     * dolumn sizf is not bpplidbblf.
     * @pbrbm dbtblog b dbtblog nbmf; must mbtdi tif dbtblog nbmf bs it
     *        is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b dbtblog;
     *        <dodf>null</dodf> mfbns tibt tif dbtblog nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm sdifmbPbttfrn b sdifmb nbmf pbttfrn; must mbtdi tif sdifmb nbmf
     *        bs it is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b sdifmb;
     *        <dodf>null</dodf> mfbns tibt tif sdifmb nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm prodfdurfNbmfPbttfrn b prodfdurf nbmf pbttfrn; must mbtdi tif
     *        prodfdurf nbmf bs it is storfd in tif dbtbbbsf
     * @pbrbm dolumnNbmfPbttfrn b dolumn nbmf pbttfrn; must mbtdi tif dolumn nbmf
     *        bs it is storfd in tif dbtbbbsf
     * @rfturn <dodf>RfsultSft</dodf> - fbdi row dfsdribfs b storfd prodfdurf pbrbmftfr or
     *      dolumn
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sff #gftSfbrdiStringEsdbpf
     */
    RfsultSft gftProdfdurfColumns(String dbtblog,
                                  String sdifmbPbttfrn,
                                  String prodfdurfNbmfPbttfrn,
                                  String dolumnNbmfPbttfrn) tirows SQLExdfption;

    /**
     * Indidbtfs tibt typf of tif dolumn is unknown.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>COLUMN_TYPE</dodf>
     * in tif <dodf>RfsultSft</dodf>
     * rfturnfd by tif mftiod <dodf>gftProdfdurfColumns</dodf>.
     */
    int prodfdurfColumnUnknown = 0;

    /**
     * Indidbtfs tibt tif dolumn storfs IN pbrbmftfrs.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>COLUMN_TYPE</dodf>
     * in tif <dodf>RfsultSft</dodf>
     * rfturnfd by tif mftiod <dodf>gftProdfdurfColumns</dodf>.
     */
    int prodfdurfColumnIn = 1;

    /**
     * Indidbtfs tibt tif dolumn storfs INOUT pbrbmftfrs.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>COLUMN_TYPE</dodf>
     * in tif <dodf>RfsultSft</dodf>
     * rfturnfd by tif mftiod <dodf>gftProdfdurfColumns</dodf>.
     */
    int prodfdurfColumnInOut = 2;

    /**
     * Indidbtfs tibt tif dolumn storfs OUT pbrbmftfrs.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>COLUMN_TYPE</dodf>
     * in tif <dodf>RfsultSft</dodf>
     * rfturnfd by tif mftiod <dodf>gftProdfdurfColumns</dodf>.
     */
    int prodfdurfColumnOut = 4;
    /**
     * Indidbtfs tibt tif dolumn storfs rfturn vblufs.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>COLUMN_TYPE</dodf>
     * in tif <dodf>RfsultSft</dodf>
     * rfturnfd by tif mftiod <dodf>gftProdfdurfColumns</dodf>.
     */
    int prodfdurfColumnRfturn = 5;

    /**
     * Indidbtfs tibt tif dolumn storfs rfsults.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>COLUMN_TYPE</dodf>
     * in tif <dodf>RfsultSft</dodf>
     * rfturnfd by tif mftiod <dodf>gftProdfdurfColumns</dodf>.
     */
    int prodfdurfColumnRfsult = 3;

    /**
     * Indidbtfs tibt <dodf>NULL</dodf> vblufs brf not bllowfd.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>NULLABLE</dodf>
     * in tif <dodf>RfsultSft</dodf> objfdt
     * rfturnfd by tif mftiod <dodf>gftProdfdurfColumns</dodf>.
     */
    int prodfdurfNoNulls = 0;

    /**
     * Indidbtfs tibt <dodf>NULL</dodf> vblufs brf bllowfd.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>NULLABLE</dodf>
     * in tif <dodf>RfsultSft</dodf> objfdt
     * rfturnfd by tif mftiod <dodf>gftProdfdurfColumns</dodf>.
     */
    int prodfdurfNullbblf = 1;

    /**
     * Indidbtfs tibt wiftifr <dodf>NULL</dodf> vblufs brf bllowfd
     * is unknown.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>NULLABLE</dodf>
     * in tif <dodf>RfsultSft</dodf> objfdt
     * rfturnfd by tif mftiod <dodf>gftProdfdurfColumns</dodf>.
     */
    int prodfdurfNullbblfUnknown = 2;


    /**
     * Rftrifvfs b dfsdription of tif tbblfs bvbilbblf in tif givfn dbtblog.
     * Only tbblf dfsdriptions mbtdiing tif dbtblog, sdifmb, tbblf
     * nbmf bnd typf dritfrib brf rfturnfd.  Tify brf ordfrfd by
     * <dodf>TABLE_TYPE</dodf>, <dodf>TABLE_CAT</dodf>,
     * <dodf>TABLE_SCHEM</dodf> bnd <dodf>TABLE_NAME</dodf>.
     * <P>
     * Ebdi tbblf dfsdription ibs tif following dolumns:
     *  <OL>
     *  <LI><B>TABLE_CAT</B> String {@dodf =>} tbblf dbtblog (mby bf <dodf>null</dodf>)
     *  <LI><B>TABLE_SCHEM</B> String {@dodf =>} tbblf sdifmb (mby bf <dodf>null</dodf>)
     *  <LI><B>TABLE_NAME</B> String {@dodf =>} tbblf nbmf
     *  <LI><B>TABLE_TYPE</B> String {@dodf =>} tbblf typf.  Typidbl typfs brf "TABLE",
     *                  "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY",
     *                  "LOCAL TEMPORARY", "ALIAS", "SYNONYM".
     *  <LI><B>REMARKS</B> String {@dodf =>} fxplbnbtory dommfnt on tif tbblf
     *  <LI><B>TYPE_CAT</B> String {@dodf =>} tif typfs dbtblog (mby bf <dodf>null</dodf>)
     *  <LI><B>TYPE_SCHEM</B> String {@dodf =>} tif typfs sdifmb (mby bf <dodf>null</dodf>)
     *  <LI><B>TYPE_NAME</B> String {@dodf =>} typf nbmf (mby bf <dodf>null</dodf>)
     *  <LI><B>SELF_REFERENCING_COL_NAME</B> String {@dodf =>} nbmf of tif dfsignbtfd
     *                  "idfntififr" dolumn of b typfd tbblf (mby bf <dodf>null</dodf>)
     *  <LI><B>REF_GENERATION</B> String {@dodf =>} spfdififs iow vblufs in
     *                  SELF_REFERENCING_COL_NAME brf drfbtfd. Vblufs brf
     *                  "SYSTEM", "USER", "DERIVED". (mby bf <dodf>null</dodf>)
     *  </OL>
     *
     * <P><B>Notf:</B> Somf dbtbbbsfs mby not rfturn informbtion for
     * bll tbblfs.
     *
     * @pbrbm dbtblog b dbtblog nbmf; must mbtdi tif dbtblog nbmf bs it
     *        is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b dbtblog;
     *        <dodf>null</dodf> mfbns tibt tif dbtblog nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm sdifmbPbttfrn b sdifmb nbmf pbttfrn; must mbtdi tif sdifmb nbmf
     *        bs it is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b sdifmb;
     *        <dodf>null</dodf> mfbns tibt tif sdifmb nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm tbblfNbmfPbttfrn b tbblf nbmf pbttfrn; must mbtdi tif
     *        tbblf nbmf bs it is storfd in tif dbtbbbsf
     * @pbrbm typfs b list of tbblf typfs, wiidi must bf from tif list of tbblf typfs
     *         rfturnfd from {@link #gftTbblfTypfs},to indludf; <dodf>null</dodf> rfturns
     * bll typfs
     * @rfturn <dodf>RfsultSft</dodf> - fbdi row is b tbblf dfsdription
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sff #gftSfbrdiStringEsdbpf
     */
    RfsultSft gftTbblfs(String dbtblog, String sdifmbPbttfrn,
                        String tbblfNbmfPbttfrn, String typfs[]) tirows SQLExdfption;

    /**
     * Rftrifvfs tif sdifmb nbmfs bvbilbblf in tiis dbtbbbsf.  Tif rfsults
     * brf ordfrfd by <dodf>TABLE_CATALOG</dodf> bnd
     * <dodf>TABLE_SCHEM</dodf>.
     *
     * <P>Tif sdifmb dolumns brf:
     *  <OL>
     *  <LI><B>TABLE_SCHEM</B> String {@dodf =>} sdifmb nbmf
     *  <LI><B>TABLE_CATALOG</B> String {@dodf =>} dbtblog nbmf (mby bf <dodf>null</dodf>)
     *  </OL>
     *
     * @rfturn b <dodf>RfsultSft</dodf> objfdt in wiidi fbdi row is b
     *         sdifmb dfsdription
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     *
     */
    RfsultSft gftSdifmbs() tirows SQLExdfption;

    /**
     * Rftrifvfs tif dbtblog nbmfs bvbilbblf in tiis dbtbbbsf.  Tif rfsults
     * brf ordfrfd by dbtblog nbmf.
     *
     * <P>Tif dbtblog dolumn is:
     *  <OL>
     *  <LI><B>TABLE_CAT</B> String {@dodf =>} dbtblog nbmf
     *  </OL>
     *
     * @rfturn b <dodf>RfsultSft</dodf> objfdt in wiidi fbdi row ibs b
     *         singlf <dodf>String</dodf> dolumn tibt is b dbtblog nbmf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    RfsultSft gftCbtblogs() tirows SQLExdfption;

    /**
     * Rftrifvfs tif tbblf typfs bvbilbblf in tiis dbtbbbsf.  Tif rfsults
     * brf ordfrfd by tbblf typf.
     *
     * <P>Tif tbblf typf is:
     *  <OL>
     *  <LI><B>TABLE_TYPE</B> String {@dodf =>} tbblf typf.  Typidbl typfs brf "TABLE",
     *                  "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY",
     *                  "LOCAL TEMPORARY", "ALIAS", "SYNONYM".
     *  </OL>
     *
     * @rfturn b <dodf>RfsultSft</dodf> objfdt in wiidi fbdi row ibs b
     *         singlf <dodf>String</dodf> dolumn tibt is b tbblf typf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    RfsultSft gftTbblfTypfs() tirows SQLExdfption;

    /**
     * Rftrifvfs b dfsdription of tbblf dolumns bvbilbblf in
     * tif spfdififd dbtblog.
     *
     * <P>Only dolumn dfsdriptions mbtdiing tif dbtblog, sdifmb, tbblf
     * bnd dolumn nbmf dritfrib brf rfturnfd.  Tify brf ordfrfd by
     * <dodf>TABLE_CAT</dodf>,<dodf>TABLE_SCHEM</dodf>,
     * <dodf>TABLE_NAME</dodf>, bnd <dodf>ORDINAL_POSITION</dodf>.
     *
     * <P>Ebdi dolumn dfsdription ibs tif following dolumns:
     *  <OL>
     *  <LI><B>TABLE_CAT</B> String {@dodf =>} tbblf dbtblog (mby bf <dodf>null</dodf>)
     *  <LI><B>TABLE_SCHEM</B> String {@dodf =>} tbblf sdifmb (mby bf <dodf>null</dodf>)
     *  <LI><B>TABLE_NAME</B> String {@dodf =>} tbblf nbmf
     *  <LI><B>COLUMN_NAME</B> String {@dodf =>} dolumn nbmf
     *  <LI><B>DATA_TYPE</B> int {@dodf =>} SQL typf from jbvb.sql.Typfs
     *  <LI><B>TYPE_NAME</B> String {@dodf =>} Dbtb sourdf dfpfndfnt typf nbmf,
     *  for b UDT tif typf nbmf is fully qublififd
     *  <LI><B>COLUMN_SIZE</B> int {@dodf =>} dolumn sizf.
     *  <LI><B>BUFFER_LENGTH</B> is not usfd.
     *  <LI><B>DECIMAL_DIGITS</B> int {@dodf =>} tif numbfr of frbdtionbl digits. Null is rfturnfd for dbtb typfs wifrf
     * DECIMAL_DIGITS is not bpplidbblf.
     *  <LI><B>NUM_PREC_RADIX</B> int {@dodf =>} Rbdix (typidblly fitifr 10 or 2)
     *  <LI><B>NULLABLE</B> int {@dodf =>} is NULL bllowfd.
     *      <UL>
     *      <LI> dolumnNoNulls - migit not bllow <dodf>NULL</dodf> vblufs
     *      <LI> dolumnNullbblf - dffinitfly bllows <dodf>NULL</dodf> vblufs
     *      <LI> dolumnNullbblfUnknown - nullbbility unknown
     *      </UL>
     *  <LI><B>REMARKS</B> String {@dodf =>} dommfnt dfsdribing dolumn (mby bf <dodf>null</dodf>)
     *  <LI><B>COLUMN_DEF</B> String {@dodf =>} dffbult vbluf for tif dolumn, wiidi siould bf intfrprftfd bs b string wifn tif vbluf is fndlosfd in singlf quotfs (mby bf <dodf>null</dodf>)
     *  <LI><B>SQL_DATA_TYPE</B> int {@dodf =>} unusfd
     *  <LI><B>SQL_DATETIME_SUB</B> int {@dodf =>} unusfd
     *  <LI><B>CHAR_OCTET_LENGTH</B> int {@dodf =>} for dibr typfs tif
     *       mbximum numbfr of bytfs in tif dolumn
     *  <LI><B>ORDINAL_POSITION</B> int {@dodf =>} indfx of dolumn in tbblf
     *      (stbrting bt 1)
     *  <LI><B>IS_NULLABLE</B> String  {@dodf =>} ISO rulfs brf usfd to dftfrminf tif nullbbility for b dolumn.
     *       <UL>
     *       <LI> YES           --- if tif dolumn dbn indludf NULLs
     *       <LI> NO            --- if tif dolumn dbnnot indludf NULLs
     *       <LI> fmpty string  --- if tif nullbbility for tif
     * dolumn is unknown
     *       </UL>
     *  <LI><B>SCOPE_CATALOG</B> String {@dodf =>} dbtblog of tbblf tibt is tif sdopf
     *      of b rfffrfndf bttributf (<dodf>null</dodf> if DATA_TYPE isn't REF)
     *  <LI><B>SCOPE_SCHEMA</B> String {@dodf =>} sdifmb of tbblf tibt is tif sdopf
     *      of b rfffrfndf bttributf (<dodf>null</dodf> if tif DATA_TYPE isn't REF)
     *  <LI><B>SCOPE_TABLE</B> String {@dodf =>} tbblf nbmf tibt tiis tif sdopf
     *      of b rfffrfndf bttributf (<dodf>null</dodf> if tif DATA_TYPE isn't REF)
     *  <LI><B>SOURCE_DATA_TYPE</B> siort {@dodf =>} sourdf typf of b distindt typf or usfr-gfnfrbtfd
     *      Rff typf, SQL typf from jbvb.sql.Typfs (<dodf>null</dodf> if DATA_TYPE
     *      isn't DISTINCT or usfr-gfnfrbtfd REF)
     *   <LI><B>IS_AUTOINCREMENT</B> String  {@dodf =>} Indidbtfs wiftifr tiis dolumn is buto indrfmfntfd
     *       <UL>
     *       <LI> YES           --- if tif dolumn is buto indrfmfntfd
     *       <LI> NO            --- if tif dolumn is not buto indrfmfntfd
     *       <LI> fmpty string  --- if it dbnnot bf dftfrminfd wiftifr tif dolumn is buto indrfmfntfd
     *       </UL>
     *   <LI><B>IS_GENERATEDCOLUMN</B> String  {@dodf =>} Indidbtfs wiftifr tiis is b gfnfrbtfd dolumn
     *       <UL>
     *       <LI> YES           --- if tiis b gfnfrbtfd dolumn
     *       <LI> NO            --- if tiis not b gfnfrbtfd dolumn
     *       <LI> fmpty string  --- if it dbnnot bf dftfrminfd wiftifr tiis is b gfnfrbtfd dolumn
     *       </UL>
     *  </OL>
     *
     * <p>Tif COLUMN_SIZE dolumn spfdififs tif dolumn sizf for tif givfn dolumn.
     * For numfrid dbtb, tiis is tif mbximum prfdision.  For dibrbdtfr dbtb, tiis is tif lfngti in dibrbdtfrs.
     * For dbtftimf dbtbtypfs, tiis is tif lfngti in dibrbdtfrs of tif String rfprfsfntbtion (bssuming tif
     * mbximum bllowfd prfdision of tif frbdtionbl sfdonds domponfnt). For binbry dbtb, tiis is tif lfngti in bytfs.  For tif ROWID dbtbtypf,
     * tiis is tif lfngti in bytfs. Null is rfturnfd for dbtb typfs wifrf tif
     * dolumn sizf is not bpplidbblf.
     *
     * @pbrbm dbtblog b dbtblog nbmf; must mbtdi tif dbtblog nbmf bs it
     *        is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b dbtblog;
     *        <dodf>null</dodf> mfbns tibt tif dbtblog nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm sdifmbPbttfrn b sdifmb nbmf pbttfrn; must mbtdi tif sdifmb nbmf
     *        bs it is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b sdifmb;
     *        <dodf>null</dodf> mfbns tibt tif sdifmb nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm tbblfNbmfPbttfrn b tbblf nbmf pbttfrn; must mbtdi tif
     *        tbblf nbmf bs it is storfd in tif dbtbbbsf
     * @pbrbm dolumnNbmfPbttfrn b dolumn nbmf pbttfrn; must mbtdi tif dolumn
     *        nbmf bs it is storfd in tif dbtbbbsf
     * @rfturn <dodf>RfsultSft</dodf> - fbdi row is b dolumn dfsdription
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sff #gftSfbrdiStringEsdbpf
     */
    RfsultSft gftColumns(String dbtblog, String sdifmbPbttfrn,
                         String tbblfNbmfPbttfrn, String dolumnNbmfPbttfrn)
        tirows SQLExdfption;

    /**
     * Indidbtfs tibt tif dolumn migit not bllow <dodf>NULL</dodf> vblufs.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>NULLABLE</dodf>
     * in tif <dodf>RfsultSft</dodf> rfturnfd by tif mftiod
     * <dodf>gftColumns</dodf>.
     */
    int dolumnNoNulls = 0;

    /**
     * Indidbtfs tibt tif dolumn dffinitfly bllows <dodf>NULL</dodf> vblufs.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>NULLABLE</dodf>
     * in tif <dodf>RfsultSft</dodf> rfturnfd by tif mftiod
     * <dodf>gftColumns</dodf>.
     */
    int dolumnNullbblf = 1;

    /**
     * Indidbtfs tibt tif nullbbility of dolumns is unknown.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>NULLABLE</dodf>
     * in tif <dodf>RfsultSft</dodf> rfturnfd by tif mftiod
     * <dodf>gftColumns</dodf>.
     */
    int dolumnNullbblfUnknown = 2;

    /**
     * Rftrifvfs b dfsdription of tif bddfss rigits for b tbblf's dolumns.
     *
     * <P>Only privilfgfs mbtdiing tif dolumn nbmf dritfrib brf
     * rfturnfd.  Tify brf ordfrfd by COLUMN_NAME bnd PRIVILEGE.
     *
     * <P>Ebdi privilfgf dfsdription ibs tif following dolumns:
     *  <OL>
     *  <LI><B>TABLE_CAT</B> String {@dodf =>} tbblf dbtblog (mby bf <dodf>null</dodf>)
     *  <LI><B>TABLE_SCHEM</B> String {@dodf =>} tbblf sdifmb (mby bf <dodf>null</dodf>)
     *  <LI><B>TABLE_NAME</B> String {@dodf =>} tbblf nbmf
     *  <LI><B>COLUMN_NAME</B> String {@dodf =>} dolumn nbmf
     *  <LI><B>GRANTOR</B> String {@dodf =>} grbntor of bddfss (mby bf <dodf>null</dodf>)
     *  <LI><B>GRANTEE</B> String {@dodf =>} grbntff of bddfss
     *  <LI><B>PRIVILEGE</B> String {@dodf =>} nbmf of bddfss (SELECT,
     *      INSERT, UPDATE, REFRENCES, ...)
     *  <LI><B>IS_GRANTABLE</B> String {@dodf =>} "YES" if grbntff is pfrmittfd
     *      to grbnt to otifrs; "NO" if not; <dodf>null</dodf> if unknown
     *  </OL>
     *
     * @pbrbm dbtblog b dbtblog nbmf; must mbtdi tif dbtblog nbmf bs it
     *        is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b dbtblog;
     *        <dodf>null</dodf> mfbns tibt tif dbtblog nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm sdifmb b sdifmb nbmf; must mbtdi tif sdifmb nbmf bs it is
     *        storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b sdifmb;
     *        <dodf>null</dodf> mfbns tibt tif sdifmb nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm tbblf b tbblf nbmf; must mbtdi tif tbblf nbmf bs it is
     *        storfd in tif dbtbbbsf
     * @pbrbm dolumnNbmfPbttfrn b dolumn nbmf pbttfrn; must mbtdi tif dolumn
     *        nbmf bs it is storfd in tif dbtbbbsf
     * @rfturn <dodf>RfsultSft</dodf> - fbdi row is b dolumn privilfgf dfsdription
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sff #gftSfbrdiStringEsdbpf
     */
    RfsultSft gftColumnPrivilfgfs(String dbtblog, String sdifmb,
                                  String tbblf, String dolumnNbmfPbttfrn) tirows SQLExdfption;

    /**
     * Rftrifvfs b dfsdription of tif bddfss rigits for fbdi tbblf bvbilbblf
     * in b dbtblog. Notf tibt b tbblf privilfgf bpplifs to onf or
     * morf dolumns in tif tbblf. It would bf wrong to bssumf tibt
     * tiis privilfgf bpplifs to bll dolumns (tiis mby bf truf for
     * somf systfms but is not truf for bll.)
     *
     * <P>Only privilfgfs mbtdiing tif sdifmb bnd tbblf nbmf
     * dritfrib brf rfturnfd.  Tify brf ordfrfd by
     * <dodf>TABLE_CAT</dodf>,
     * <dodf>TABLE_SCHEM</dodf>, <dodf>TABLE_NAME</dodf>,
     * bnd <dodf>PRIVILEGE</dodf>.
     *
     * <P>Ebdi privilfgf dfsdription ibs tif following dolumns:
     *  <OL>
     *  <LI><B>TABLE_CAT</B> String {@dodf =>} tbblf dbtblog (mby bf <dodf>null</dodf>)
     *  <LI><B>TABLE_SCHEM</B> String {@dodf =>} tbblf sdifmb (mby bf <dodf>null</dodf>)
     *  <LI><B>TABLE_NAME</B> String {@dodf =>} tbblf nbmf
     *  <LI><B>GRANTOR</B> String {@dodf =>} grbntor of bddfss (mby bf <dodf>null</dodf>)
     *  <LI><B>GRANTEE</B> String {@dodf =>} grbntff of bddfss
     *  <LI><B>PRIVILEGE</B> String {@dodf =>} nbmf of bddfss (SELECT,
     *      INSERT, UPDATE, REFRENCES, ...)
     *  <LI><B>IS_GRANTABLE</B> String {@dodf =>} "YES" if grbntff is pfrmittfd
     *      to grbnt to otifrs; "NO" if not; <dodf>null</dodf> if unknown
     *  </OL>
     *
     * @pbrbm dbtblog b dbtblog nbmf; must mbtdi tif dbtblog nbmf bs it
     *        is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b dbtblog;
     *        <dodf>null</dodf> mfbns tibt tif dbtblog nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm sdifmbPbttfrn b sdifmb nbmf pbttfrn; must mbtdi tif sdifmb nbmf
     *        bs it is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b sdifmb;
     *        <dodf>null</dodf> mfbns tibt tif sdifmb nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm tbblfNbmfPbttfrn b tbblf nbmf pbttfrn; must mbtdi tif
     *        tbblf nbmf bs it is storfd in tif dbtbbbsf
     * @rfturn <dodf>RfsultSft</dodf> - fbdi row is b tbblf privilfgf dfsdription
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sff #gftSfbrdiStringEsdbpf
     */
    RfsultSft gftTbblfPrivilfgfs(String dbtblog, String sdifmbPbttfrn,
                                 String tbblfNbmfPbttfrn) tirows SQLExdfption;

    /**
     * Rftrifvfs b dfsdription of b tbblf's optimbl sft of dolumns tibt
     * uniqufly idfntififs b row. Tify brf ordfrfd by SCOPE.
     *
     * <P>Ebdi dolumn dfsdription ibs tif following dolumns:
     *  <OL>
     *  <LI><B>SCOPE</B> siort {@dodf =>} bdtubl sdopf of rfsult
     *      <UL>
     *      <LI> bfstRowTfmporbry - vfry tfmporbry, wiilf using row
     *      <LI> bfstRowTrbnsbdtion - vblid for rfmbindfr of durrfnt trbnsbdtion
     *      <LI> bfstRowSfssion - vblid for rfmbindfr of durrfnt sfssion
     *      </UL>
     *  <LI><B>COLUMN_NAME</B> String {@dodf =>} dolumn nbmf
     *  <LI><B>DATA_TYPE</B> int {@dodf =>} SQL dbtb typf from jbvb.sql.Typfs
     *  <LI><B>TYPE_NAME</B> String {@dodf =>} Dbtb sourdf dfpfndfnt typf nbmf,
     *  for b UDT tif typf nbmf is fully qublififd
     *  <LI><B>COLUMN_SIZE</B> int {@dodf =>} prfdision
     *  <LI><B>BUFFER_LENGTH</B> int {@dodf =>} not usfd
     *  <LI><B>DECIMAL_DIGITS</B> siort  {@dodf =>} sdblf - Null is rfturnfd for dbtb typfs wifrf
     * DECIMAL_DIGITS is not bpplidbblf.
     *  <LI><B>PSEUDO_COLUMN</B> siort {@dodf =>} is tiis b psfudo dolumn
     *      likf bn Orbdlf ROWID
     *      <UL>
     *      <LI> bfstRowUnknown - mby or mby not bf psfudo dolumn
     *      <LI> bfstRowNotPsfudo - is NOT b psfudo dolumn
     *      <LI> bfstRowPsfudo - is b psfudo dolumn
     *      </UL>
     *  </OL>
     *
     * <p>Tif COLUMN_SIZE dolumn rfprfsfnts tif spfdififd dolumn sizf for tif givfn dolumn.
     * For numfrid dbtb, tiis is tif mbximum prfdision.  For dibrbdtfr dbtb, tiis is tif lfngti in dibrbdtfrs.
     * For dbtftimf dbtbtypfs, tiis is tif lfngti in dibrbdtfrs of tif String rfprfsfntbtion (bssuming tif
     * mbximum bllowfd prfdision of tif frbdtionbl sfdonds domponfnt). For binbry dbtb, tiis is tif lfngti in bytfs.  For tif ROWID dbtbtypf,
     * tiis is tif lfngti in bytfs. Null is rfturnfd for dbtb typfs wifrf tif
     * dolumn sizf is not bpplidbblf.
     *
     * @pbrbm dbtblog b dbtblog nbmf; must mbtdi tif dbtblog nbmf bs it
     *        is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b dbtblog;
     *        <dodf>null</dodf> mfbns tibt tif dbtblog nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm sdifmb b sdifmb nbmf; must mbtdi tif sdifmb nbmf
     *        bs it is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b sdifmb;
     *        <dodf>null</dodf> mfbns tibt tif sdifmb nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm tbblf b tbblf nbmf; must mbtdi tif tbblf nbmf bs it is storfd
     *        in tif dbtbbbsf
     * @pbrbm sdopf tif sdopf of intfrfst; usf sbmf vblufs bs SCOPE
     * @pbrbm nullbblf indludf dolumns tibt brf nullbblf.
     * @rfturn <dodf>RfsultSft</dodf> - fbdi row is b dolumn dfsdription
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    RfsultSft gftBfstRowIdfntififr(String dbtblog, String sdifmb,
                                   String tbblf, int sdopf, boolfbn nullbblf) tirows SQLExdfption;

    /**
     * Indidbtfs tibt tif sdopf of tif bfst row idfntififr is
     * vfry tfmporbry, lbsting only wiilf tif
     * row is bfing usfd.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>SCOPE</dodf>
     * in tif <dodf>RfsultSft</dodf> objfdt
     * rfturnfd by tif mftiod <dodf>gftBfstRowIdfntififr</dodf>.
     */
    int bfstRowTfmporbry   = 0;

    /**
     * Indidbtfs tibt tif sdopf of tif bfst row idfntififr is
     * tif rfmbindfr of tif durrfnt trbnsbdtion.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>SCOPE</dodf>
     * in tif <dodf>RfsultSft</dodf> objfdt
     * rfturnfd by tif mftiod <dodf>gftBfstRowIdfntififr</dodf>.
     */
    int bfstRowTrbnsbdtion = 1;

    /**
     * Indidbtfs tibt tif sdopf of tif bfst row idfntififr is
     * tif rfmbindfr of tif durrfnt sfssion.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>SCOPE</dodf>
     * in tif <dodf>RfsultSft</dodf> objfdt
     * rfturnfd by tif mftiod <dodf>gftBfstRowIdfntififr</dodf>.
     */
    int bfstRowSfssion     = 2;

    /**
     * Indidbtfs tibt tif bfst row idfntififr mby or mby not bf b psfudo dolumn.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>PSEUDO_COLUMN</dodf>
     * in tif <dodf>RfsultSft</dodf> objfdt
     * rfturnfd by tif mftiod <dodf>gftBfstRowIdfntififr</dodf>.
     */
    int bfstRowUnknown  = 0;

    /**
     * Indidbtfs tibt tif bfst row idfntififr is NOT b psfudo dolumn.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>PSEUDO_COLUMN</dodf>
     * in tif <dodf>RfsultSft</dodf> objfdt
     * rfturnfd by tif mftiod <dodf>gftBfstRowIdfntififr</dodf>.
     */
    int bfstRowNotPsfudo        = 1;

    /**
     * Indidbtfs tibt tif bfst row idfntififr is b psfudo dolumn.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>PSEUDO_COLUMN</dodf>
     * in tif <dodf>RfsultSft</dodf> objfdt
     * rfturnfd by tif mftiod <dodf>gftBfstRowIdfntififr</dodf>.
     */
    int bfstRowPsfudo   = 2;

    /**
     * Rftrifvfs b dfsdription of b tbblf's dolumns tibt brf butombtidblly
     * updbtfd wifn bny vbluf in b row is updbtfd.  Tify brf
     * unordfrfd.
     *
     * <P>Ebdi dolumn dfsdription ibs tif following dolumns:
     *  <OL>
     *  <LI><B>SCOPE</B> siort {@dodf =>} is not usfd
     *  <LI><B>COLUMN_NAME</B> String {@dodf =>} dolumn nbmf
     *  <LI><B>DATA_TYPE</B> int {@dodf =>} SQL dbtb typf from <dodf>jbvb.sql.Typfs</dodf>
     *  <LI><B>TYPE_NAME</B> String {@dodf =>} Dbtb sourdf-dfpfndfnt typf nbmf
     *  <LI><B>COLUMN_SIZE</B> int {@dodf =>} prfdision
     *  <LI><B>BUFFER_LENGTH</B> int {@dodf =>} lfngti of dolumn vbluf in bytfs
     *  <LI><B>DECIMAL_DIGITS</B> siort  {@dodf =>} sdblf - Null is rfturnfd for dbtb typfs wifrf
     * DECIMAL_DIGITS is not bpplidbblf.
     *  <LI><B>PSEUDO_COLUMN</B> siort {@dodf =>} wiftifr tiis is psfudo dolumn
     *      likf bn Orbdlf ROWID
     *      <UL>
     *      <LI> vfrsionColumnUnknown - mby or mby not bf psfudo dolumn
     *      <LI> vfrsionColumnNotPsfudo - is NOT b psfudo dolumn
     *      <LI> vfrsionColumnPsfudo - is b psfudo dolumn
     *      </UL>
     *  </OL>
     *
     * <p>Tif COLUMN_SIZE dolumn rfprfsfnts tif spfdififd dolumn sizf for tif givfn dolumn.
     * For numfrid dbtb, tiis is tif mbximum prfdision.  For dibrbdtfr dbtb, tiis is tif lfngti in dibrbdtfrs.
     * For dbtftimf dbtbtypfs, tiis is tif lfngti in dibrbdtfrs of tif String rfprfsfntbtion (bssuming tif
     * mbximum bllowfd prfdision of tif frbdtionbl sfdonds domponfnt). For binbry dbtb, tiis is tif lfngti in bytfs.  For tif ROWID dbtbtypf,
     * tiis is tif lfngti in bytfs. Null is rfturnfd for dbtb typfs wifrf tif
     * dolumn sizf is not bpplidbblf.
     * @pbrbm dbtblog b dbtblog nbmf; must mbtdi tif dbtblog nbmf bs it
     *        is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b dbtblog;
     *        <dodf>null</dodf> mfbns tibt tif dbtblog nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm sdifmb b sdifmb nbmf; must mbtdi tif sdifmb nbmf
     *        bs it is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b sdifmb;
     *        <dodf>null</dodf> mfbns tibt tif sdifmb nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm tbblf b tbblf nbmf; must mbtdi tif tbblf nbmf bs it is storfd
     *        in tif dbtbbbsf
     * @rfturn b <dodf>RfsultSft</dodf> objfdt in wiidi fbdi row is b
     *         dolumn dfsdription
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    RfsultSft gftVfrsionColumns(String dbtblog, String sdifmb,
                                String tbblf) tirows SQLExdfption;

    /**
     * Indidbtfs tibt tiis vfrsion dolumn mby or mby not bf b psfudo dolumn.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>PSEUDO_COLUMN</dodf>
     * in tif <dodf>RfsultSft</dodf> objfdt
     * rfturnfd by tif mftiod <dodf>gftVfrsionColumns</dodf>.
     */
    int vfrsionColumnUnknown    = 0;

    /**
     * Indidbtfs tibt tiis vfrsion dolumn is NOT b psfudo dolumn.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>PSEUDO_COLUMN</dodf>
     * in tif <dodf>RfsultSft</dodf> objfdt
     * rfturnfd by tif mftiod <dodf>gftVfrsionColumns</dodf>.
     */
    int vfrsionColumnNotPsfudo  = 1;

    /**
     * Indidbtfs tibt tiis vfrsion dolumn is b psfudo dolumn.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>PSEUDO_COLUMN</dodf>
     * in tif <dodf>RfsultSft</dodf> objfdt
     * rfturnfd by tif mftiod <dodf>gftVfrsionColumns</dodf>.
     */
    int vfrsionColumnPsfudo     = 2;

    /**
     * Rftrifvfs b dfsdription of tif givfn tbblf's primbry kfy dolumns.  Tify
     * brf ordfrfd by COLUMN_NAME.
     *
     * <P>Ebdi primbry kfy dolumn dfsdription ibs tif following dolumns:
     *  <OL>
     *  <LI><B>TABLE_CAT</B> String {@dodf =>} tbblf dbtblog (mby bf <dodf>null</dodf>)
     *  <LI><B>TABLE_SCHEM</B> String {@dodf =>} tbblf sdifmb (mby bf <dodf>null</dodf>)
     *  <LI><B>TABLE_NAME</B> String {@dodf =>} tbblf nbmf
     *  <LI><B>COLUMN_NAME</B> String {@dodf =>} dolumn nbmf
     *  <LI><B>KEY_SEQ</B> siort {@dodf =>} sfqufndf numbfr witiin primbry kfy( b vbluf
     *  of 1 rfprfsfnts tif first dolumn of tif primbry kfy, b vbluf of 2 would
     *  rfprfsfnt tif sfdond dolumn witiin tif primbry kfy).
     *  <LI><B>PK_NAME</B> String {@dodf =>} primbry kfy nbmf (mby bf <dodf>null</dodf>)
     *  </OL>
     *
     * @pbrbm dbtblog b dbtblog nbmf; must mbtdi tif dbtblog nbmf bs it
     *        is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b dbtblog;
     *        <dodf>null</dodf> mfbns tibt tif dbtblog nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm sdifmb b sdifmb nbmf; must mbtdi tif sdifmb nbmf
     *        bs it is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b sdifmb;
     *        <dodf>null</dodf> mfbns tibt tif sdifmb nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm tbblf b tbblf nbmf; must mbtdi tif tbblf nbmf bs it is storfd
     *        in tif dbtbbbsf
     * @rfturn <dodf>RfsultSft</dodf> - fbdi row is b primbry kfy dolumn dfsdription
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    RfsultSft gftPrimbryKfys(String dbtblog, String sdifmb,
                             String tbblf) tirows SQLExdfption;

    /**
     * Rftrifvfs b dfsdription of tif primbry kfy dolumns tibt brf
     * rfffrfndfd by tif givfn tbblf's forfign kfy dolumns (tif primbry kfys
     * importfd by b tbblf).  Tify brf ordfrfd by PKTABLE_CAT,
     * PKTABLE_SCHEM, PKTABLE_NAME, bnd KEY_SEQ.
     *
     * <P>Ebdi primbry kfy dolumn dfsdription ibs tif following dolumns:
     *  <OL>
     *  <LI><B>PKTABLE_CAT</B> String {@dodf =>} primbry kfy tbblf dbtblog
     *      bfing importfd (mby bf <dodf>null</dodf>)
     *  <LI><B>PKTABLE_SCHEM</B> String {@dodf =>} primbry kfy tbblf sdifmb
     *      bfing importfd (mby bf <dodf>null</dodf>)
     *  <LI><B>PKTABLE_NAME</B> String {@dodf =>} primbry kfy tbblf nbmf
     *      bfing importfd
     *  <LI><B>PKCOLUMN_NAME</B> String {@dodf =>} primbry kfy dolumn nbmf
     *      bfing importfd
     *  <LI><B>FKTABLE_CAT</B> String {@dodf =>} forfign kfy tbblf dbtblog (mby bf <dodf>null</dodf>)
     *  <LI><B>FKTABLE_SCHEM</B> String {@dodf =>} forfign kfy tbblf sdifmb (mby bf <dodf>null</dodf>)
     *  <LI><B>FKTABLE_NAME</B> String {@dodf =>} forfign kfy tbblf nbmf
     *  <LI><B>FKCOLUMN_NAME</B> String {@dodf =>} forfign kfy dolumn nbmf
     *  <LI><B>KEY_SEQ</B> siort {@dodf =>} sfqufndf numbfr witiin b forfign kfy( b vbluf
     *  of 1 rfprfsfnts tif first dolumn of tif forfign kfy, b vbluf of 2 would
     *  rfprfsfnt tif sfdond dolumn witiin tif forfign kfy).
     *  <LI><B>UPDATE_RULE</B> siort {@dodf =>} Wibt ibppfns to b
     *       forfign kfy wifn tif primbry kfy is updbtfd:
     *      <UL>
     *      <LI> importfdNoAdtion - do not bllow updbtf of primbry
     *               kfy if it ibs bffn importfd
     *      <LI> importfdKfyCbsdbdf - dibngf importfd kfy to bgrff
     *               witi primbry kfy updbtf
     *      <LI> importfdKfySftNull - dibngf importfd kfy to <dodf>NULL</dodf>
     *               if its primbry kfy ibs bffn updbtfd
     *      <LI> importfdKfySftDffbult - dibngf importfd kfy to dffbult vblufs
     *               if its primbry kfy ibs bffn updbtfd
     *      <LI> importfdKfyRfstridt - sbmf bs importfdKfyNoAdtion
     *                                 (for ODBC 2.x dompbtibility)
     *      </UL>
     *  <LI><B>DELETE_RULE</B> siort {@dodf =>} Wibt ibppfns to
     *      tif forfign kfy wifn primbry is dflftfd.
     *      <UL>
     *      <LI> importfdKfyNoAdtion - do not bllow dflftf of primbry
     *               kfy if it ibs bffn importfd
     *      <LI> importfdKfyCbsdbdf - dflftf rows tibt import b dflftfd kfy
     *      <LI> importfdKfySftNull - dibngf importfd kfy to NULL if
     *               its primbry kfy ibs bffn dflftfd
     *      <LI> importfdKfyRfstridt - sbmf bs importfdKfyNoAdtion
     *                                 (for ODBC 2.x dompbtibility)
     *      <LI> importfdKfySftDffbult - dibngf importfd kfy to dffbult if
     *               its primbry kfy ibs bffn dflftfd
     *      </UL>
     *  <LI><B>FK_NAME</B> String {@dodf =>} forfign kfy nbmf (mby bf <dodf>null</dodf>)
     *  <LI><B>PK_NAME</B> String {@dodf =>} primbry kfy nbmf (mby bf <dodf>null</dodf>)
     *  <LI><B>DEFERRABILITY</B> siort {@dodf =>} dbn tif fvblubtion of forfign kfy
     *      donstrbints bf dfffrrfd until dommit
     *      <UL>
     *      <LI> importfdKfyInitibllyDfffrrfd - sff SQL92 for dffinition
     *      <LI> importfdKfyInitibllyImmfdibtf - sff SQL92 for dffinition
     *      <LI> importfdKfyNotDfffrrbblf - sff SQL92 for dffinition
     *      </UL>
     *  </OL>
     *
     * @pbrbm dbtblog b dbtblog nbmf; must mbtdi tif dbtblog nbmf bs it
     *        is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b dbtblog;
     *        <dodf>null</dodf> mfbns tibt tif dbtblog nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm sdifmb b sdifmb nbmf; must mbtdi tif sdifmb nbmf
     *        bs it is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b sdifmb;
     *        <dodf>null</dodf> mfbns tibt tif sdifmb nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm tbblf b tbblf nbmf; must mbtdi tif tbblf nbmf bs it is storfd
     *        in tif dbtbbbsf
     * @rfturn <dodf>RfsultSft</dodf> - fbdi row is b primbry kfy dolumn dfsdription
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sff #gftExportfdKfys
     */
    RfsultSft gftImportfdKfys(String dbtblog, String sdifmb,
                              String tbblf) tirows SQLExdfption;

    /**
     * For tif dolumn <dodf>UPDATE_RULE</dodf>,
     * indidbtfs tibt
     * wifn tif primbry kfy is updbtfd, tif forfign kfy (importfd kfy)
     * is dibngfd to bgrff witi it.
     * For tif dolumn <dodf>DELETE_RULE</dodf>,
     * it indidbtfs tibt
     * wifn tif primbry kfy is dflftfd, rows tibt importfd tibt kfy
     * brf dflftfd.
     * <P>
     * A possiblf vbluf for tif dolumns <dodf>UPDATE_RULE</dodf>
     * bnd <dodf>DELETE_RULE</dodf> in tif
     * <dodf>RfsultSft</dodf> objfdts rfturnfd by tif mftiods
     * <dodf>gftImportfdKfys</dodf>,  <dodf>gftExportfdKfys</dodf>,
     * bnd <dodf>gftCrossRfffrfndf</dodf>.
     */
    int importfdKfyCbsdbdf      = 0;

    /**
     * For tif dolumn <dodf>UPDATE_RULE</dodf>, indidbtfs tibt
     * b primbry kfy mby not bf updbtfd if it ibs bffn importfd by
     * bnotifr tbblf bs b forfign kfy.
     * For tif dolumn <dodf>DELETE_RULE</dodf>, indidbtfs tibt
     * b primbry kfy mby not bf dflftfd if it ibs bffn importfd by
     * bnotifr tbblf bs b forfign kfy.
     * <P>
     * A possiblf vbluf for tif dolumns <dodf>UPDATE_RULE</dodf>
     * bnd <dodf>DELETE_RULE</dodf> in tif
     * <dodf>RfsultSft</dodf> objfdts rfturnfd by tif mftiods
     * <dodf>gftImportfdKfys</dodf>,  <dodf>gftExportfdKfys</dodf>,
     * bnd <dodf>gftCrossRfffrfndf</dodf>.
     */
    int importfdKfyRfstridt = 1;

    /**
     * For tif dolumns <dodf>UPDATE_RULE</dodf>
     * bnd <dodf>DELETE_RULE</dodf>, indidbtfs tibt
     * wifn tif primbry kfy is updbtfd or dflftfd, tif forfign kfy (importfd kfy)
     * is dibngfd to <dodf>NULL</dodf>.
     * <P>
     * A possiblf vbluf for tif dolumns <dodf>UPDATE_RULE</dodf>
     * bnd <dodf>DELETE_RULE</dodf> in tif
     * <dodf>RfsultSft</dodf> objfdts rfturnfd by tif mftiods
     * <dodf>gftImportfdKfys</dodf>,  <dodf>gftExportfdKfys</dodf>,
     * bnd <dodf>gftCrossRfffrfndf</dodf>.
     */
    int importfdKfySftNull  = 2;

    /**
     * For tif dolumns <dodf>UPDATE_RULE</dodf>
     * bnd <dodf>DELETE_RULE</dodf>, indidbtfs tibt
     * if tif primbry kfy ibs bffn importfd, it dbnnot bf updbtfd or dflftfd.
     * <P>
     * A possiblf vbluf for tif dolumns <dodf>UPDATE_RULE</dodf>
     * bnd <dodf>DELETE_RULE</dodf> in tif
     * <dodf>RfsultSft</dodf> objfdts rfturnfd by tif mftiods
     * <dodf>gftImportfdKfys</dodf>,  <dodf>gftExportfdKfys</dodf>,
     * bnd <dodf>gftCrossRfffrfndf</dodf>.
     */
    int importfdKfyNoAdtion = 3;

    /**
     * For tif dolumns <dodf>UPDATE_RULE</dodf>
     * bnd <dodf>DELETE_RULE</dodf>, indidbtfs tibt
     * if tif primbry kfy is updbtfd or dflftfd, tif forfign kfy (importfd kfy)
     * is sft to tif dffbult vbluf.
     * <P>
     * A possiblf vbluf for tif dolumns <dodf>UPDATE_RULE</dodf>
     * bnd <dodf>DELETE_RULE</dodf> in tif
     * <dodf>RfsultSft</dodf> objfdts rfturnfd by tif mftiods
     * <dodf>gftImportfdKfys</dodf>,  <dodf>gftExportfdKfys</dodf>,
     * bnd <dodf>gftCrossRfffrfndf</dodf>.
     */
    int importfdKfySftDffbult  = 4;

    /**
     * Indidbtfs dfffrrbbility.  Sff SQL-92 for b dffinition.
     * <P>
     * A possiblf vbluf for tif dolumn <dodf>DEFERRABILITY</dodf>
     * in tif <dodf>RfsultSft</dodf> objfdts rfturnfd by tif mftiods
     * <dodf>gftImportfdKfys</dodf>,  <dodf>gftExportfdKfys</dodf>,
     * bnd <dodf>gftCrossRfffrfndf</dodf>.
     */
    int importfdKfyInitibllyDfffrrfd  = 5;

    /**
     * Indidbtfs dfffrrbbility.  Sff SQL-92 for b dffinition.
     * <P>
     * A possiblf vbluf for tif dolumn <dodf>DEFERRABILITY</dodf>
     * in tif <dodf>RfsultSft</dodf> objfdts rfturnfd by tif mftiods
     * <dodf>gftImportfdKfys</dodf>,  <dodf>gftExportfdKfys</dodf>,
     * bnd <dodf>gftCrossRfffrfndf</dodf>.
     */
    int importfdKfyInitibllyImmfdibtf  = 6;

    /**
     * Indidbtfs dfffrrbbility.  Sff SQL-92 for b dffinition.
     * <P>
     * A possiblf vbluf for tif dolumn <dodf>DEFERRABILITY</dodf>
     * in tif <dodf>RfsultSft</dodf> objfdts rfturnfd by tif mftiods
     * <dodf>gftImportfdKfys</dodf>,  <dodf>gftExportfdKfys</dodf>,
     * bnd <dodf>gftCrossRfffrfndf</dodf>.
     */
    int importfdKfyNotDfffrrbblf  = 7;

    /**
     * Rftrifvfs b dfsdription of tif forfign kfy dolumns tibt rfffrfndf tif
     * givfn tbblf's primbry kfy dolumns (tif forfign kfys fxportfd by b
     * tbblf).  Tify brf ordfrfd by FKTABLE_CAT, FKTABLE_SCHEM,
     * FKTABLE_NAME, bnd KEY_SEQ.
     *
     * <P>Ebdi forfign kfy dolumn dfsdription ibs tif following dolumns:
     *  <OL>
     *  <LI><B>PKTABLE_CAT</B> String {@dodf =>} primbry kfy tbblf dbtblog (mby bf <dodf>null</dodf>)
     *  <LI><B>PKTABLE_SCHEM</B> String {@dodf =>} primbry kfy tbblf sdifmb (mby bf <dodf>null</dodf>)
     *  <LI><B>PKTABLE_NAME</B> String {@dodf =>} primbry kfy tbblf nbmf
     *  <LI><B>PKCOLUMN_NAME</B> String {@dodf =>} primbry kfy dolumn nbmf
     *  <LI><B>FKTABLE_CAT</B> String {@dodf =>} forfign kfy tbblf dbtblog (mby bf <dodf>null</dodf>)
     *      bfing fxportfd (mby bf <dodf>null</dodf>)
     *  <LI><B>FKTABLE_SCHEM</B> String {@dodf =>} forfign kfy tbblf sdifmb (mby bf <dodf>null</dodf>)
     *      bfing fxportfd (mby bf <dodf>null</dodf>)
     *  <LI><B>FKTABLE_NAME</B> String {@dodf =>} forfign kfy tbblf nbmf
     *      bfing fxportfd
     *  <LI><B>FKCOLUMN_NAME</B> String {@dodf =>} forfign kfy dolumn nbmf
     *      bfing fxportfd
     *  <LI><B>KEY_SEQ</B> siort {@dodf =>} sfqufndf numbfr witiin forfign kfy( b vbluf
     *  of 1 rfprfsfnts tif first dolumn of tif forfign kfy, b vbluf of 2 would
     *  rfprfsfnt tif sfdond dolumn witiin tif forfign kfy).
     *  <LI><B>UPDATE_RULE</B> siort {@dodf =>} Wibt ibppfns to
     *       forfign kfy wifn primbry is updbtfd:
     *      <UL>
     *      <LI> importfdNoAdtion - do not bllow updbtf of primbry
     *               kfy if it ibs bffn importfd
     *      <LI> importfdKfyCbsdbdf - dibngf importfd kfy to bgrff
     *               witi primbry kfy updbtf
     *      <LI> importfdKfySftNull - dibngf importfd kfy to <dodf>NULL</dodf> if
     *               its primbry kfy ibs bffn updbtfd
     *      <LI> importfdKfySftDffbult - dibngf importfd kfy to dffbult vblufs
     *               if its primbry kfy ibs bffn updbtfd
     *      <LI> importfdKfyRfstridt - sbmf bs importfdKfyNoAdtion
     *                                 (for ODBC 2.x dompbtibility)
     *      </UL>
     *  <LI><B>DELETE_RULE</B> siort {@dodf =>} Wibt ibppfns to
     *      tif forfign kfy wifn primbry is dflftfd.
     *      <UL>
     *      <LI> importfdKfyNoAdtion - do not bllow dflftf of primbry
     *               kfy if it ibs bffn importfd
     *      <LI> importfdKfyCbsdbdf - dflftf rows tibt import b dflftfd kfy
     *      <LI> importfdKfySftNull - dibngf importfd kfy to <dodf>NULL</dodf> if
     *               its primbry kfy ibs bffn dflftfd
     *      <LI> importfdKfyRfstridt - sbmf bs importfdKfyNoAdtion
     *                                 (for ODBC 2.x dompbtibility)
     *      <LI> importfdKfySftDffbult - dibngf importfd kfy to dffbult if
     *               its primbry kfy ibs bffn dflftfd
     *      </UL>
     *  <LI><B>FK_NAME</B> String {@dodf =>} forfign kfy nbmf (mby bf <dodf>null</dodf>)
     *  <LI><B>PK_NAME</B> String {@dodf =>} primbry kfy nbmf (mby bf <dodf>null</dodf>)
     *  <LI><B>DEFERRABILITY</B> siort {@dodf =>} dbn tif fvblubtion of forfign kfy
     *      donstrbints bf dfffrrfd until dommit
     *      <UL>
     *      <LI> importfdKfyInitibllyDfffrrfd - sff SQL92 for dffinition
     *      <LI> importfdKfyInitibllyImmfdibtf - sff SQL92 for dffinition
     *      <LI> importfdKfyNotDfffrrbblf - sff SQL92 for dffinition
     *      </UL>
     *  </OL>
     *
     * @pbrbm dbtblog b dbtblog nbmf; must mbtdi tif dbtblog nbmf bs it
     *        is storfd in tiis dbtbbbsf; "" rftrifvfs tiosf witiout b dbtblog;
     *        <dodf>null</dodf> mfbns tibt tif dbtblog nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm sdifmb b sdifmb nbmf; must mbtdi tif sdifmb nbmf
     *        bs it is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b sdifmb;
     *        <dodf>null</dodf> mfbns tibt tif sdifmb nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm tbblf b tbblf nbmf; must mbtdi tif tbblf nbmf bs it is storfd
     *        in tiis dbtbbbsf
     * @rfturn b <dodf>RfsultSft</dodf> objfdt in wiidi fbdi row is b
     *         forfign kfy dolumn dfsdription
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sff #gftImportfdKfys
     */
    RfsultSft gftExportfdKfys(String dbtblog, String sdifmb,
                              String tbblf) tirows SQLExdfption;

    /**
     * Rftrifvfs b dfsdription of tif forfign kfy dolumns in tif givfn forfign kfy
     * tbblf tibt rfffrfndf tif primbry kfy or tif dolumns rfprfsfnting b uniquf donstrbint of tif  pbrfnt tbblf (dould bf tif sbmf or b difffrfnt tbblf).
     * Tif numbfr of dolumns rfturnfd from tif pbrfnt tbblf must mbtdi tif numbfr of
     * dolumns tibt mbkf up tif forfign kfy.  Tify
     * brf ordfrfd by FKTABLE_CAT, FKTABLE_SCHEM, FKTABLE_NAME, bnd
     * KEY_SEQ.
     *
     * <P>Ebdi forfign kfy dolumn dfsdription ibs tif following dolumns:
     *  <OL>
     *  <LI><B>PKTABLE_CAT</B> String {@dodf =>} pbrfnt kfy tbblf dbtblog (mby bf <dodf>null</dodf>)
     *  <LI><B>PKTABLE_SCHEM</B> String {@dodf =>} pbrfnt kfy tbblf sdifmb (mby bf <dodf>null</dodf>)
     *  <LI><B>PKTABLE_NAME</B> String {@dodf =>} pbrfnt kfy tbblf nbmf
     *  <LI><B>PKCOLUMN_NAME</B> String {@dodf =>} pbrfnt kfy dolumn nbmf
     *  <LI><B>FKTABLE_CAT</B> String {@dodf =>} forfign kfy tbblf dbtblog (mby bf <dodf>null</dodf>)
     *      bfing fxportfd (mby bf <dodf>null</dodf>)
     *  <LI><B>FKTABLE_SCHEM</B> String {@dodf =>} forfign kfy tbblf sdifmb (mby bf <dodf>null</dodf>)
     *      bfing fxportfd (mby bf <dodf>null</dodf>)
     *  <LI><B>FKTABLE_NAME</B> String {@dodf =>} forfign kfy tbblf nbmf
     *      bfing fxportfd
     *  <LI><B>FKCOLUMN_NAME</B> String {@dodf =>} forfign kfy dolumn nbmf
     *      bfing fxportfd
     *  <LI><B>KEY_SEQ</B> siort {@dodf =>} sfqufndf numbfr witiin forfign kfy( b vbluf
     *  of 1 rfprfsfnts tif first dolumn of tif forfign kfy, b vbluf of 2 would
     *  rfprfsfnt tif sfdond dolumn witiin tif forfign kfy).
     *  <LI><B>UPDATE_RULE</B> siort {@dodf =>} Wibt ibppfns to
     *       forfign kfy wifn pbrfnt kfy is updbtfd:
     *      <UL>
     *      <LI> importfdNoAdtion - do not bllow updbtf of pbrfnt
     *               kfy if it ibs bffn importfd
     *      <LI> importfdKfyCbsdbdf - dibngf importfd kfy to bgrff
     *               witi pbrfnt kfy updbtf
     *      <LI> importfdKfySftNull - dibngf importfd kfy to <dodf>NULL</dodf> if
     *               its pbrfnt kfy ibs bffn updbtfd
     *      <LI> importfdKfySftDffbult - dibngf importfd kfy to dffbult vblufs
     *               if its pbrfnt kfy ibs bffn updbtfd
     *      <LI> importfdKfyRfstridt - sbmf bs importfdKfyNoAdtion
     *                                 (for ODBC 2.x dompbtibility)
     *      </UL>
     *  <LI><B>DELETE_RULE</B> siort {@dodf =>} Wibt ibppfns to
     *      tif forfign kfy wifn pbrfnt kfy is dflftfd.
     *      <UL>
     *      <LI> importfdKfyNoAdtion - do not bllow dflftf of pbrfnt
     *               kfy if it ibs bffn importfd
     *      <LI> importfdKfyCbsdbdf - dflftf rows tibt import b dflftfd kfy
     *      <LI> importfdKfySftNull - dibngf importfd kfy to <dodf>NULL</dodf> if
     *               its primbry kfy ibs bffn dflftfd
     *      <LI> importfdKfyRfstridt - sbmf bs importfdKfyNoAdtion
     *                                 (for ODBC 2.x dompbtibility)
     *      <LI> importfdKfySftDffbult - dibngf importfd kfy to dffbult if
     *               its pbrfnt kfy ibs bffn dflftfd
     *      </UL>
     *  <LI><B>FK_NAME</B> String {@dodf =>} forfign kfy nbmf (mby bf <dodf>null</dodf>)
     *  <LI><B>PK_NAME</B> String {@dodf =>} pbrfnt kfy nbmf (mby bf <dodf>null</dodf>)
     *  <LI><B>DEFERRABILITY</B> siort {@dodf =>} dbn tif fvblubtion of forfign kfy
     *      donstrbints bf dfffrrfd until dommit
     *      <UL>
     *      <LI> importfdKfyInitibllyDfffrrfd - sff SQL92 for dffinition
     *      <LI> importfdKfyInitibllyImmfdibtf - sff SQL92 for dffinition
     *      <LI> importfdKfyNotDfffrrbblf - sff SQL92 for dffinition
     *      </UL>
     *  </OL>
     *
     * @pbrbm pbrfntCbtblog b dbtblog nbmf; must mbtdi tif dbtblog nbmf
     * bs it is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b
     * dbtblog; <dodf>null</dodf> mfbns drop dbtblog nbmf from tif sflfdtion dritfrib
     * @pbrbm pbrfntSdifmb b sdifmb nbmf; must mbtdi tif sdifmb nbmf bs
     * it is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b sdifmb;
     * <dodf>null</dodf> mfbns drop sdifmb nbmf from tif sflfdtion dritfrib
     * @pbrbm pbrfntTbblf tif nbmf of tif tbblf tibt fxports tif kfy; must mbtdi
     * tif tbblf nbmf bs it is storfd in tif dbtbbbsf
     * @pbrbm forfignCbtblog b dbtblog nbmf; must mbtdi tif dbtblog nbmf bs
     * it is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b
     * dbtblog; <dodf>null</dodf> mfbns drop dbtblog nbmf from tif sflfdtion dritfrib
     * @pbrbm forfignSdifmb b sdifmb nbmf; must mbtdi tif sdifmb nbmf bs it
     * is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b sdifmb;
     * <dodf>null</dodf> mfbns drop sdifmb nbmf from tif sflfdtion dritfrib
     * @pbrbm forfignTbblf tif nbmf of tif tbblf tibt imports tif kfy; must mbtdi
     * tif tbblf nbmf bs it is storfd in tif dbtbbbsf
     * @rfturn <dodf>RfsultSft</dodf> - fbdi row is b forfign kfy dolumn dfsdription
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sff #gftImportfdKfys
     */
    RfsultSft gftCrossRfffrfndf(
                                String pbrfntCbtblog, String pbrfntSdifmb, String pbrfntTbblf,
                                String forfignCbtblog, String forfignSdifmb, String forfignTbblf
                                ) tirows SQLExdfption;

    /**
     * Rftrifvfs b dfsdription of bll tif dbtb typfs supportfd by
     * tiis dbtbbbsf. Tify brf ordfrfd by DATA_TYPE bnd tifn by iow
     * dlosfly tif dbtb typf mbps to tif dorrfsponding JDBC SQL typf.
     *
     * <P>If tif dbtbbbsf supports SQL distindt typfs, tifn gftTypfInfo() will rfturn
     * b singlf row witi b TYPE_NAME of DISTINCT bnd b DATA_TYPE of Typfs.DISTINCT.
     * If tif dbtbbbsf supports SQL strudturfd typfs, tifn gftTypfInfo() will rfturn
     * b singlf row witi b TYPE_NAME of STRUCT bnd b DATA_TYPE of Typfs.STRUCT.
     *
     * <P>If SQL distindt or strudturfd typfs brf supportfd, tifn informbtion on tif
     * individubl typfs mby bf obtbinfd from tif gftUDTs() mftiod.
     *
     *
     * <P>Ebdi typf dfsdription ibs tif following dolumns:
     *  <OL>
     *  <LI><B>TYPE_NAME</B> String {@dodf =>} Typf nbmf
     *  <LI><B>DATA_TYPE</B> int {@dodf =>} SQL dbtb typf from jbvb.sql.Typfs
     *  <LI><B>PRECISION</B> int {@dodf =>} mbximum prfdision
     *  <LI><B>LITERAL_PREFIX</B> String {@dodf =>} prffix usfd to quotf b litfrbl
     *      (mby bf <dodf>null</dodf>)
     *  <LI><B>LITERAL_SUFFIX</B> String {@dodf =>} suffix usfd to quotf b litfrbl
     *  (mby bf <dodf>null</dodf>)
     *  <LI><B>CREATE_PARAMS</B> String {@dodf =>} pbrbmftfrs usfd in drfbting
     *      tif typf (mby bf <dodf>null</dodf>)
     *  <LI><B>NULLABLE</B> siort {@dodf =>} dbn you usf NULL for tiis typf.
     *      <UL>
     *      <LI> typfNoNulls - dofs not bllow NULL vblufs
     *      <LI> typfNullbblf - bllows NULL vblufs
     *      <LI> typfNullbblfUnknown - nullbbility unknown
     *      </UL>
     *  <LI><B>CASE_SENSITIVE</B> boolfbn{@dodf =>} is it dbsf sfnsitivf.
     *  <LI><B>SEARCHABLE</B> siort {@dodf =>} dbn you usf "WHERE" bbsfd on tiis typf:
     *      <UL>
     *      <LI> typfPrfdNonf - No support
     *      <LI> typfPrfdCibr - Only supportfd witi WHERE .. LIKE
     *      <LI> typfPrfdBbsid - Supportfd fxdfpt for WHERE .. LIKE
     *      <LI> typfSfbrdibblf - Supportfd for bll WHERE ..
     *      </UL>
     *  <LI><B>UNSIGNED_ATTRIBUTE</B> boolfbn {@dodf =>} is it unsignfd.
     *  <LI><B>FIXED_PREC_SCALE</B> boolfbn {@dodf =>} dbn it bf b monfy vbluf.
     *  <LI><B>AUTO_INCREMENT</B> boolfbn {@dodf =>} dbn it bf usfd for bn
     *      buto-indrfmfnt vbluf.
     *  <LI><B>LOCAL_TYPE_NAME</B> String {@dodf =>} lodblizfd vfrsion of typf nbmf
     *      (mby bf <dodf>null</dodf>)
     *  <LI><B>MINIMUM_SCALE</B> siort {@dodf =>} minimum sdblf supportfd
     *  <LI><B>MAXIMUM_SCALE</B> siort {@dodf =>} mbximum sdblf supportfd
     *  <LI><B>SQL_DATA_TYPE</B> int {@dodf =>} unusfd
     *  <LI><B>SQL_DATETIME_SUB</B> int {@dodf =>} unusfd
     *  <LI><B>NUM_PREC_RADIX</B> int {@dodf =>} usublly 2 or 10
     *  </OL>
     *
     * <p>Tif PRECISION dolumn rfprfsfnts tif mbximum dolumn sizf tibt tif sfrvfr supports for tif givfn dbtbtypf.
     * For numfrid dbtb, tiis is tif mbximum prfdision.  For dibrbdtfr dbtb, tiis is tif lfngti in dibrbdtfrs.
     * For dbtftimf dbtbtypfs, tiis is tif lfngti in dibrbdtfrs of tif String rfprfsfntbtion (bssuming tif
     * mbximum bllowfd prfdision of tif frbdtionbl sfdonds domponfnt). For binbry dbtb, tiis is tif lfngti in bytfs.  For tif ROWID dbtbtypf,
     * tiis is tif lfngti in bytfs. Null is rfturnfd for dbtb typfs wifrf tif
     * dolumn sizf is not bpplidbblf.
     *
     * @rfturn b <dodf>RfsultSft</dodf> objfdt in wiidi fbdi row is bn SQL
     *         typf dfsdription
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    RfsultSft gftTypfInfo() tirows SQLExdfption;

    /**
     * Indidbtfs tibt b <dodf>NULL</dodf> vbluf is NOT bllowfd for tiis
     * dbtb typf.
     * <P>
     * A possiblf vbluf for dolumn <dodf>NULLABLE</dodf> in tif
     * <dodf>RfsultSft</dodf> objfdt rfturnfd by tif mftiod
     * <dodf>gftTypfInfo</dodf>.
     */
    int typfNoNulls = 0;

    /**
     * Indidbtfs tibt b <dodf>NULL</dodf> vbluf is bllowfd for tiis
     * dbtb typf.
     * <P>
     * A possiblf vbluf for dolumn <dodf>NULLABLE</dodf> in tif
     * <dodf>RfsultSft</dodf> objfdt rfturnfd by tif mftiod
     * <dodf>gftTypfInfo</dodf>.
     */
    int typfNullbblf = 1;

    /**
     * Indidbtfs tibt it is not known wiftifr b <dodf>NULL</dodf> vbluf
     * is bllowfd for tiis dbtb typf.
     * <P>
     * A possiblf vbluf for dolumn <dodf>NULLABLE</dodf> in tif
     * <dodf>RfsultSft</dodf> objfdt rfturnfd by tif mftiod
     * <dodf>gftTypfInfo</dodf>.
     */
    int typfNullbblfUnknown = 2;

    /**
     * Indidbtfs tibt <dodf>WHERE</dodf> sfbrdi dlbusfs brf not supportfd
     * for tiis typf.
     * <P>
     * A possiblf vbluf for dolumn <dodf>SEARCHABLE</dodf> in tif
     * <dodf>RfsultSft</dodf> objfdt rfturnfd by tif mftiod
     * <dodf>gftTypfInfo</dodf>.
     */
    int typfPrfdNonf = 0;

    /**
     * Indidbtfs tibt tif dbtb typf
     * dbn bf only bf usfd in <dodf>WHERE</dodf> sfbrdi dlbusfs
     * tibt  usf <dodf>LIKE</dodf> prfdidbtfs.
     * <P>
     * A possiblf vbluf for dolumn <dodf>SEARCHABLE</dodf> in tif
     * <dodf>RfsultSft</dodf> objfdt rfturnfd by tif mftiod
     * <dodf>gftTypfInfo</dodf>.
     */
    int typfPrfdCibr = 1;

    /**
     * Indidbtfs tibt tif dbtb typf dbn bf only bf usfd in <dodf>WHERE</dodf>
     * sfbrdi dlbusfs
     * tibt do not usf <dodf>LIKE</dodf> prfdidbtfs.
     * <P>
     * A possiblf vbluf for dolumn <dodf>SEARCHABLE</dodf> in tif
     * <dodf>RfsultSft</dodf> objfdt rfturnfd by tif mftiod
     * <dodf>gftTypfInfo</dodf>.
     */
    int typfPrfdBbsid = 2;

    /**
     * Indidbtfs tibt bll <dodf>WHERE</dodf> sfbrdi dlbusfs dbn bf
     * bbsfd on tiis typf.
     * <P>
     * A possiblf vbluf for dolumn <dodf>SEARCHABLE</dodf> in tif
     * <dodf>RfsultSft</dodf> objfdt rfturnfd by tif mftiod
     * <dodf>gftTypfInfo</dodf>.
     */
    int typfSfbrdibblf  = 3;

    /**
     * Rftrifvfs b dfsdription of tif givfn tbblf's indidfs bnd stbtistids. Tify brf
     * ordfrfd by NON_UNIQUE, TYPE, INDEX_NAME, bnd ORDINAL_POSITION.
     *
     * <P>Ebdi indfx dolumn dfsdription ibs tif following dolumns:
     *  <OL>
     *  <LI><B>TABLE_CAT</B> String {@dodf =>} tbblf dbtblog (mby bf <dodf>null</dodf>)
     *  <LI><B>TABLE_SCHEM</B> String {@dodf =>} tbblf sdifmb (mby bf <dodf>null</dodf>)
     *  <LI><B>TABLE_NAME</B> String {@dodf =>} tbblf nbmf
     *  <LI><B>NON_UNIQUE</B> boolfbn {@dodf =>} Cbn indfx vblufs bf non-uniquf.
     *      fblsf wifn TYPE is tbblfIndfxStbtistid
     *  <LI><B>INDEX_QUALIFIER</B> String {@dodf =>} indfx dbtblog (mby bf <dodf>null</dodf>);
     *      <dodf>null</dodf> wifn TYPE is tbblfIndfxStbtistid
     *  <LI><B>INDEX_NAME</B> String {@dodf =>} indfx nbmf; <dodf>null</dodf> wifn TYPE is
     *      tbblfIndfxStbtistid
     *  <LI><B>TYPE</B> siort {@dodf =>} indfx typf:
     *      <UL>
     *      <LI> tbblfIndfxStbtistid - tiis idfntififs tbblf stbtistids tibt brf
     *           rfturnfd in donjudtion witi b tbblf's indfx dfsdriptions
     *      <LI> tbblfIndfxClustfrfd - tiis is b dlustfrfd indfx
     *      <LI> tbblfIndfxHbsifd - tiis is b ibsifd indfx
     *      <LI> tbblfIndfxOtifr - tiis is somf otifr stylf of indfx
     *      </UL>
     *  <LI><B>ORDINAL_POSITION</B> siort {@dodf =>} dolumn sfqufndf numbfr
     *      witiin indfx; zfro wifn TYPE is tbblfIndfxStbtistid
     *  <LI><B>COLUMN_NAME</B> String {@dodf =>} dolumn nbmf; <dodf>null</dodf> wifn TYPE is
     *      tbblfIndfxStbtistid
     *  <LI><B>ASC_OR_DESC</B> String {@dodf =>} dolumn sort sfqufndf, "A" {@dodf =>} bsdfnding,
     *      "D" {@dodf =>} dfsdfnding, mby bf <dodf>null</dodf> if sort sfqufndf is not supportfd;
     *      <dodf>null</dodf> wifn TYPE is tbblfIndfxStbtistid
     *  <LI><B>CARDINALITY</B> long {@dodf =>} Wifn TYPE is tbblfIndfxStbtistid, tifn
     *      tiis is tif numbfr of rows in tif tbblf; otifrwisf, it is tif
     *      numbfr of uniquf vblufs in tif indfx.
     *  <LI><B>PAGES</B> long {@dodf =>} Wifn TYPE is  tbblfIndfxStbtisid tifn
     *      tiis is tif numbfr of pbgfs usfd for tif tbblf, otifrwisf it
     *      is tif numbfr of pbgfs usfd for tif durrfnt indfx.
     *  <LI><B>FILTER_CONDITION</B> String {@dodf =>} Filtfr dondition, if bny.
     *      (mby bf <dodf>null</dodf>)
     *  </OL>
     *
     * @pbrbm dbtblog b dbtblog nbmf; must mbtdi tif dbtblog nbmf bs it
     *        is storfd in tiis dbtbbbsf; "" rftrifvfs tiosf witiout b dbtblog;
     *        <dodf>null</dodf> mfbns tibt tif dbtblog nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm sdifmb b sdifmb nbmf; must mbtdi tif sdifmb nbmf
     *        bs it is storfd in tiis dbtbbbsf; "" rftrifvfs tiosf witiout b sdifmb;
     *        <dodf>null</dodf> mfbns tibt tif sdifmb nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm tbblf b tbblf nbmf; must mbtdi tif tbblf nbmf bs it is storfd
     *        in tiis dbtbbbsf
     * @pbrbm uniquf wifn truf, rfturn only indidfs for uniquf vblufs;
     *     wifn fblsf, rfturn indidfs rfgbrdlfss of wiftifr uniquf or not
     * @pbrbm bpproximbtf wifn truf, rfsult is bllowfd to rfflfdt bpproximbtf
     *     or out of dbtb vblufs; wifn fblsf, rfsults brf rfqufstfd to bf
     *     bddurbtf
     * @rfturn <dodf>RfsultSft</dodf> - fbdi row is bn indfx dolumn dfsdription
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    RfsultSft gftIndfxInfo(String dbtblog, String sdifmb, String tbblf,
                           boolfbn uniquf, boolfbn bpproximbtf)
        tirows SQLExdfption;

    /**
     * Indidbtfs tibt tiis dolumn dontbins tbblf stbtistids tibt
     * brf rfturnfd in donjundtion witi b tbblf's indfx dfsdriptions.
     * <P>
     * A possiblf vbluf for dolumn <dodf>TYPE</dodf> in tif
     * <dodf>RfsultSft</dodf> objfdt rfturnfd by tif mftiod
     * <dodf>gftIndfxInfo</dodf>.
     */
    siort tbblfIndfxStbtistid = 0;

    /**
     * Indidbtfs tibt tiis tbblf indfx is b dlustfrfd indfx.
     * <P>
     * A possiblf vbluf for dolumn <dodf>TYPE</dodf> in tif
     * <dodf>RfsultSft</dodf> objfdt rfturnfd by tif mftiod
     * <dodf>gftIndfxInfo</dodf>.
     */
    siort tbblfIndfxClustfrfd = 1;

    /**
     * Indidbtfs tibt tiis tbblf indfx is b ibsifd indfx.
     * <P>
     * A possiblf vbluf for dolumn <dodf>TYPE</dodf> in tif
     * <dodf>RfsultSft</dodf> objfdt rfturnfd by tif mftiod
     * <dodf>gftIndfxInfo</dodf>.
     */
    siort tbblfIndfxHbsifd    = 2;

    /**
     * Indidbtfs tibt tiis tbblf indfx is not b dlustfrfd
     * indfx, b ibsifd indfx, or tbblf stbtistids;
     * it is somftiing otifr tibn tifsf.
     * <P>
     * A possiblf vbluf for dolumn <dodf>TYPE</dodf> in tif
     * <dodf>RfsultSft</dodf> objfdt rfturnfd by tif mftiod
     * <dodf>gftIndfxInfo</dodf>.
     */
    siort tbblfIndfxOtifr     = 3;

    //--------------------------JDBC 2.0-----------------------------

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports tif givfn rfsult sft typf.
     *
     * @pbrbm typf dffinfd in <dodf>jbvb.sql.RfsultSft</dodf>
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sff Connfdtion
     * @sindf 1.2
     */
    boolfbn supportsRfsultSftTypf(int typf) tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports tif givfn dondurrfndy typf
     * in dombinbtion witi tif givfn rfsult sft typf.
     *
     * @pbrbm typf dffinfd in <dodf>jbvb.sql.RfsultSft</dodf>
     * @pbrbm dondurrfndy typf dffinfd in <dodf>jbvb.sql.RfsultSft</dodf>
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sff Connfdtion
     * @sindf 1.2
     */
    boolfbn supportsRfsultSftCondurrfndy(int typf, int dondurrfndy)
        tirows SQLExdfption;

    /**
     *
     * Rftrifvfs wiftifr for tif givfn typf of <dodf>RfsultSft</dodf> objfdt,
     * tif rfsult sft's own updbtfs brf visiblf.
     *
     * @pbrbm typf tif <dodf>RfsultSft</dodf> typf; onf of
     *        <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>,
     *        <dodf>RfsultSft.TYPE_SCROLL_INSENSITIVE</dodf>, or
     *        <dodf>RfsultSft.TYPE_SCROLL_SENSITIVE</dodf>
     * @rfturn <dodf>truf</dodf> if updbtfs brf visiblf for tif givfn rfsult sft typf;
     *        <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.2
     */
    boolfbn ownUpdbtfsArfVisiblf(int typf) tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr b rfsult sft's own dflftfs brf visiblf.
     *
     * @pbrbm typf tif <dodf>RfsultSft</dodf> typf; onf of
     *        <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>,
     *        <dodf>RfsultSft.TYPE_SCROLL_INSENSITIVE</dodf>, or
     *        <dodf>RfsultSft.TYPE_SCROLL_SENSITIVE</dodf>
     * @rfturn <dodf>truf</dodf> if dflftfs brf visiblf for tif givfn rfsult sft typf;
     *        <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.2
     */
    boolfbn ownDflftfsArfVisiblf(int typf) tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr b rfsult sft's own insfrts brf visiblf.
     *
     * @pbrbm typf tif <dodf>RfsultSft</dodf> typf; onf of
     *        <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>,
     *        <dodf>RfsultSft.TYPE_SCROLL_INSENSITIVE</dodf>, or
     *        <dodf>RfsultSft.TYPE_SCROLL_SENSITIVE</dodf>
     * @rfturn <dodf>truf</dodf> if insfrts brf visiblf for tif givfn rfsult sft typf;
     *        <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.2
     */
    boolfbn ownInsfrtsArfVisiblf(int typf) tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr updbtfs mbdf by otifrs brf visiblf.
     *
     * @pbrbm typf tif <dodf>RfsultSft</dodf> typf; onf of
     *        <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>,
     *        <dodf>RfsultSft.TYPE_SCROLL_INSENSITIVE</dodf>, or
     *        <dodf>RfsultSft.TYPE_SCROLL_SENSITIVE</dodf>
     * @rfturn <dodf>truf</dodf> if updbtfs mbdf by otifrs
     *        brf visiblf for tif givfn rfsult sft typf;
     *        <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.2
     */
    boolfbn otifrsUpdbtfsArfVisiblf(int typf) tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr dflftfs mbdf by otifrs brf visiblf.
     *
     * @pbrbm typf tif <dodf>RfsultSft</dodf> typf; onf of
     *        <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>,
     *        <dodf>RfsultSft.TYPE_SCROLL_INSENSITIVE</dodf>, or
     *        <dodf>RfsultSft.TYPE_SCROLL_SENSITIVE</dodf>
     * @rfturn <dodf>truf</dodf> if dflftfs mbdf by otifrs
     *        brf visiblf for tif givfn rfsult sft typf;
     *        <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.2
     */
    boolfbn otifrsDflftfsArfVisiblf(int typf) tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr insfrts mbdf by otifrs brf visiblf.
     *
     * @pbrbm typf tif <dodf>RfsultSft</dodf> typf; onf of
     *        <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>,
     *        <dodf>RfsultSft.TYPE_SCROLL_INSENSITIVE</dodf>, or
     *        <dodf>RfsultSft.TYPE_SCROLL_SENSITIVE</dodf>
     * @rfturn <dodf>truf</dodf> if insfrts mbdf by otifrs
     *         brf visiblf for tif givfn rfsult sft typf;
     *         <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.2
     */
    boolfbn otifrsInsfrtsArfVisiblf(int typf) tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr or not b visiblf row updbtf dbn bf dftfdtfd by
     * dblling tif mftiod <dodf>RfsultSft.rowUpdbtfd</dodf>.
     *
     * @pbrbm typf tif <dodf>RfsultSft</dodf> typf; onf of
     *        <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>,
     *        <dodf>RfsultSft.TYPE_SCROLL_INSENSITIVE</dodf>, or
     *        <dodf>RfsultSft.TYPE_SCROLL_SENSITIVE</dodf>
     * @rfturn <dodf>truf</dodf> if dibngfs brf dftfdtfd by tif rfsult sft typf;
     *         <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.2
     */
    boolfbn updbtfsArfDftfdtfd(int typf) tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr or not b visiblf row dflftf dbn bf dftfdtfd by
     * dblling tif mftiod <dodf>RfsultSft.rowDflftfd</dodf>.  If tif mftiod
     * <dodf>dflftfsArfDftfdtfd</dodf> rfturns <dodf>fblsf</dodf>, it mfbns tibt
     * dflftfd rows brf rfmovfd from tif rfsult sft.
     *
     * @pbrbm typf tif <dodf>RfsultSft</dodf> typf; onf of
     *        <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>,
     *        <dodf>RfsultSft.TYPE_SCROLL_INSENSITIVE</dodf>, or
     *        <dodf>RfsultSft.TYPE_SCROLL_SENSITIVE</dodf>
     * @rfturn <dodf>truf</dodf> if dflftfs brf dftfdtfd by tif givfn rfsult sft typf;
     *         <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.2
     */
    boolfbn dflftfsArfDftfdtfd(int typf) tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr or not b visiblf row insfrt dbn bf dftfdtfd
     * by dblling tif mftiod <dodf>RfsultSft.rowInsfrtfd</dodf>.
     *
     * @pbrbm typf tif <dodf>RfsultSft</dodf> typf; onf of
     *        <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>,
     *        <dodf>RfsultSft.TYPE_SCROLL_INSENSITIVE</dodf>, or
     *        <dodf>RfsultSft.TYPE_SCROLL_SENSITIVE</dodf>
     * @rfturn <dodf>truf</dodf> if dibngfs brf dftfdtfd by tif spfdififd rfsult
     *         sft typf; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.2
     */
    boolfbn insfrtsArfDftfdtfd(int typf) tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports bbtdi updbtfs.
     *
     * @rfturn <dodf>truf</dodf> if tiis dbtbbbsf supports bbtdi updbtfs;
     *         <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.2
     */
    boolfbn supportsBbtdiUpdbtfs() tirows SQLExdfption;

    /**
     * Rftrifvfs b dfsdription of tif usfr-dffinfd typfs (UDTs) dffinfd
     * in b pbrtidulbr sdifmb.  Sdifmb-spfdifid UDTs mby ibvf typf
     * <dodf>JAVA_OBJECT</dodf>, <dodf>STRUCT</dodf>,
     * or <dodf>DISTINCT</dodf>.
     *
     * <P>Only typfs mbtdiing tif dbtblog, sdifmb, typf nbmf bnd typf
     * dritfrib brf rfturnfd.  Tify brf ordfrfd by <dodf>DATA_TYPE</dodf>,
     * <dodf>TYPE_CAT</dodf>, <dodf>TYPE_SCHEM</dodf>  bnd
     * <dodf>TYPE_NAME</dodf>.  Tif typf nbmf pbrbmftfr mby bf b fully-qublififd
     * nbmf.  In tiis dbsf, tif dbtblog bnd sdifmbPbttfrn pbrbmftfrs brf
     * ignorfd.
     *
     * <P>Ebdi typf dfsdription ibs tif following dolumns:
     *  <OL>
     *  <LI><B>TYPE_CAT</B> String {@dodf =>} tif typf's dbtblog (mby bf <dodf>null</dodf>)
     *  <LI><B>TYPE_SCHEM</B> String {@dodf =>} typf's sdifmb (mby bf <dodf>null</dodf>)
     *  <LI><B>TYPE_NAME</B> String {@dodf =>} typf nbmf
     *  <LI><B>CLASS_NAME</B> String {@dodf =>} Jbvb dlbss nbmf
     *  <LI><B>DATA_TYPE</B> int {@dodf =>} typf vbluf dffinfd in jbvb.sql.Typfs.
     *     Onf of JAVA_OBJECT, STRUCT, or DISTINCT
     *  <LI><B>REMARKS</B> String {@dodf =>} fxplbnbtory dommfnt on tif typf
     *  <LI><B>BASE_TYPE</B> siort {@dodf =>} typf dodf of tif sourdf typf of b
     *     DISTINCT typf or tif typf tibt implfmfnts tif usfr-gfnfrbtfd
     *     rfffrfndf typf of tif SELF_REFERENCING_COLUMN of b strudturfd
     *     typf bs dffinfd in jbvb.sql.Typfs (<dodf>null</dodf> if DATA_TYPE is not
     *     DISTINCT or not STRUCT witi REFERENCE_GENERATION = USER_DEFINED)
     *  </OL>
     *
     * <P><B>Notf:</B> If tif drivfr dofs not support UDTs, bn fmpty
     * rfsult sft is rfturnfd.
     *
     * @pbrbm dbtblog b dbtblog nbmf; must mbtdi tif dbtblog nbmf bs it
     *        is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b dbtblog;
     *        <dodf>null</dodf> mfbns tibt tif dbtblog nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm sdifmbPbttfrn b sdifmb pbttfrn nbmf; must mbtdi tif sdifmb nbmf
     *        bs it is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b sdifmb;
     *        <dodf>null</dodf> mfbns tibt tif sdifmb nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm typfNbmfPbttfrn b typf nbmf pbttfrn; must mbtdi tif typf nbmf
     *        bs it is storfd in tif dbtbbbsf; mby bf b fully qublififd nbmf
     * @pbrbm typfs b list of usfr-dffinfd typfs (JAVA_OBJECT,
     *        STRUCT, or DISTINCT) to indludf; <dodf>null</dodf> rfturns bll typfs
     * @rfturn <dodf>RfsultSft</dodf> objfdt in wiidi fbdi row dfsdribfs b UDT
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sff #gftSfbrdiStringEsdbpf
     * @sindf 1.2
     */
    RfsultSft gftUDTs(String dbtblog, String sdifmbPbttfrn,
                      String typfNbmfPbttfrn, int[] typfs)
        tirows SQLExdfption;

    /**
     * Rftrifvfs tif donnfdtion tibt produdfd tiis mftbdbtb objfdt.
     *
     * @rfturn tif donnfdtion tibt produdfd tiis mftbdbtb objfdt
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.2
     */
    Connfdtion gftConnfdtion() tirows SQLExdfption;

    // ------------------- JDBC 3.0 -------------------------

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports sbvfpoints.
     *
     * @rfturn <dodf>truf</dodf> if sbvfpoints brf supportfd;
     *         <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.4
     */
    boolfbn supportsSbvfpoints() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports nbmfd pbrbmftfrs to dbllbblf
     * stbtfmfnts.
     *
     * @rfturn <dodf>truf</dodf> if nbmfd pbrbmftfrs brf supportfd;
     *         <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.4
     */
    boolfbn supportsNbmfdPbrbmftfrs() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr it is possiblf to ibvf multiplf <dodf>RfsultSft</dodf> objfdts
     * rfturnfd from b <dodf>CbllbblfStbtfmfnt</dodf> objfdt
     * simultbnfously.
     *
     * @rfturn <dodf>truf</dodf> if b <dodf>CbllbblfStbtfmfnt</dodf> objfdt
     *         dbn rfturn multiplf <dodf>RfsultSft</dodf> objfdts
     *         simultbnfously; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbnbsf bddfss frror oddurs
     * @sindf 1.4
     */
    boolfbn supportsMultiplfOpfnRfsults() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr buto-gfnfrbtfd kfys dbn bf rftrifvfd bftfr
     * b stbtfmfnt ibs bffn fxfdutfd
     *
     * @rfturn <dodf>truf</dodf> if buto-gfnfrbtfd kfys dbn bf rftrifvfd
     *         bftfr b stbtfmfnt ibs fxfdutfd; <dodf>fblsf</dodf> otifrwisf
     * <p>If <dodf>truf</dodf> is rfturnfd, tif JDBC drivfr must support tif
     * rfturning of buto-gfnfrbtfd kfys for bt lfbst SQL INSERT stbtfmfnts
     *
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.4
     */
    boolfbn supportsGftGfnfrbtfdKfys() tirows SQLExdfption;

    /**
     * Rftrifvfs b dfsdription of tif usfr-dffinfd typf (UDT) iifrbrdiifs dffinfd in b
     * pbrtidulbr sdifmb in tiis dbtbbbsf. Only tif immfdibtf supfr typf/
     * sub typf rflbtionsiip is modflfd.
     * <P>
     * Only supfrtypf informbtion for UDTs mbtdiing tif dbtblog,
     * sdifmb, bnd typf nbmf is rfturnfd. Tif typf nbmf pbrbmftfr
     * mby bf b fully-qublififd nbmf. Wifn tif UDT nbmf supplifd is b
     * fully-qublififd nbmf, tif dbtblog bnd sdifmbPbttfrn pbrbmftfrs brf
     * ignorfd.
     * <P>
     * If b UDT dofs not ibvf b dirfdt supfr typf, it is not listfd ifrf.
     * A row of tif <dodf>RfsultSft</dodf> objfdt rfturnfd by tiis mftiod
     * dfsdribfs tif dfsignbtfd UDT bnd b dirfdt supfrtypf. A row ibs tif following
     * dolumns:
     *  <OL>
     *  <LI><B>TYPE_CAT</B> String {@dodf =>} tif UDT's dbtblog (mby bf <dodf>null</dodf>)
     *  <LI><B>TYPE_SCHEM</B> String {@dodf =>} UDT's sdifmb (mby bf <dodf>null</dodf>)
     *  <LI><B>TYPE_NAME</B> String {@dodf =>} typf nbmf of tif UDT
     *  <LI><B>SUPERTYPE_CAT</B> String {@dodf =>} tif dirfdt supfr typf's dbtblog
     *                           (mby bf <dodf>null</dodf>)
     *  <LI><B>SUPERTYPE_SCHEM</B> String {@dodf =>} tif dirfdt supfr typf's sdifmb
     *                             (mby bf <dodf>null</dodf>)
     *  <LI><B>SUPERTYPE_NAME</B> String {@dodf =>} tif dirfdt supfr typf's nbmf
     *  </OL>
     *
     * <P><B>Notf:</B> If tif drivfr dofs not support typf iifrbrdiifs, bn
     * fmpty rfsult sft is rfturnfd.
     *
     * @pbrbm dbtblog b dbtblog nbmf; "" rftrifvfs tiosf witiout b dbtblog;
     *        <dodf>null</dodf> mfbns drop dbtblog nbmf from tif sflfdtion dritfrib
     * @pbrbm sdifmbPbttfrn b sdifmb nbmf pbttfrn; "" rftrifvfs tiosf
     *        witiout b sdifmb
     * @pbrbm typfNbmfPbttfrn b UDT nbmf pbttfrn; mby bf b fully-qublififd
     *        nbmf
     * @rfturn b <dodf>RfsultSft</dodf> objfdt in wiidi b row givfs informbtion
     *         bbout tif dfsignbtfd UDT
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sff #gftSfbrdiStringEsdbpf
     * @sindf 1.4
     */
    RfsultSft gftSupfrTypfs(String dbtblog, String sdifmbPbttfrn,
                            String typfNbmfPbttfrn) tirows SQLExdfption;

    /**
     * Rftrifvfs b dfsdription of tif tbblf iifrbrdiifs dffinfd in b pbrtidulbr
     * sdifmb in tiis dbtbbbsf.
     *
     * <P>Only supfrtbblf informbtion for tbblfs mbtdiing tif dbtblog, sdifmb
     * bnd tbblf nbmf brf rfturnfd. Tif tbblf nbmf pbrbmftfr mby bf b fully-
     * qublififd nbmf, in wiidi dbsf, tif dbtblog bnd sdifmbPbttfrn pbrbmftfrs
     * brf ignorfd. If b tbblf dofs not ibvf b supfr tbblf, it is not listfd ifrf.
     * Supfrtbblfs ibvf to bf dffinfd in tif sbmf dbtblog bnd sdifmb bs tif
     * sub tbblfs. Tifrfforf, tif typf dfsdription dofs not nffd to indludf
     * tiis informbtion for tif supfrtbblf.
     *
     * <P>Ebdi typf dfsdription ibs tif following dolumns:
     *  <OL>
     *  <LI><B>TABLE_CAT</B> String {@dodf =>} tif typf's dbtblog (mby bf <dodf>null</dodf>)
     *  <LI><B>TABLE_SCHEM</B> String {@dodf =>} typf's sdifmb (mby bf <dodf>null</dodf>)
     *  <LI><B>TABLE_NAME</B> String {@dodf =>} typf nbmf
     *  <LI><B>SUPERTABLE_NAME</B> String {@dodf =>} tif dirfdt supfr typf's nbmf
     *  </OL>
     *
     * <P><B>Notf:</B> If tif drivfr dofs not support typf iifrbrdiifs, bn
     * fmpty rfsult sft is rfturnfd.
     *
     * @pbrbm dbtblog b dbtblog nbmf; "" rftrifvfs tiosf witiout b dbtblog;
     *        <dodf>null</dodf> mfbns drop dbtblog nbmf from tif sflfdtion dritfrib
     * @pbrbm sdifmbPbttfrn b sdifmb nbmf pbttfrn; "" rftrifvfs tiosf
     *        witiout b sdifmb
     * @pbrbm tbblfNbmfPbttfrn b tbblf nbmf pbttfrn; mby bf b fully-qublififd
     *        nbmf
     * @rfturn b <dodf>RfsultSft</dodf> objfdt in wiidi fbdi row is b typf dfsdription
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sff #gftSfbrdiStringEsdbpf
     * @sindf 1.4
     */
    RfsultSft gftSupfrTbblfs(String dbtblog, String sdifmbPbttfrn,
                             String tbblfNbmfPbttfrn) tirows SQLExdfption;

    /**
     * Indidbtfs tibt <dodf>NULL</dodf> vblufs migit not bf bllowfd.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>NULLABLE</dodf> in tif <dodf>RfsultSft</dodf> objfdt
     * rfturnfd by tif mftiod <dodf>gftAttributfs</dodf>.
     */
    siort bttributfNoNulls = 0;

    /**
     * Indidbtfs tibt <dodf>NULL</dodf> vblufs brf dffinitfly bllowfd.
     * <P>
     * A possiblf vbluf for tif dolumn <dodf>NULLABLE</dodf>
     * in tif <dodf>RfsultSft</dodf> objfdt
     * rfturnfd by tif mftiod <dodf>gftAttributfs</dodf>.
     */
    siort bttributfNullbblf = 1;

    /**
     * Indidbtfs tibt wiftifr <dodf>NULL</dodf> vblufs brf bllowfd is not
     * known.
     * <P>
     * A possiblf vbluf for tif dolumn <dodf>NULLABLE</dodf>
     * in tif <dodf>RfsultSft</dodf> objfdt
     * rfturnfd by tif mftiod <dodf>gftAttributfs</dodf>.
     */
    siort bttributfNullbblfUnknown = 2;

    /**
     * Rftrifvfs b dfsdription of tif givfn bttributf of tif givfn typf
     * for b usfr-dffinfd typf (UDT) tibt is bvbilbblf in tif givfn sdifmb
     * bnd dbtblog.
     * <P>
     * Dfsdriptions brf rfturnfd only for bttributfs of UDTs mbtdiing tif
     * dbtblog, sdifmb, typf, bnd bttributf nbmf dritfrib. Tify brf ordfrfd by
     * <dodf>TYPE_CAT</dodf>, <dodf>TYPE_SCHEM</dodf>,
     * <dodf>TYPE_NAME</dodf> bnd <dodf>ORDINAL_POSITION</dodf>. Tiis dfsdription
     * dofs not dontbin inifritfd bttributfs.
     * <P>
     * Tif <dodf>RfsultSft</dodf> objfdt tibt is rfturnfd ibs tif following
     * dolumns:
     * <OL>
     *  <LI><B>TYPE_CAT</B> String {@dodf =>} typf dbtblog (mby bf <dodf>null</dodf>)
     *  <LI><B>TYPE_SCHEM</B> String {@dodf =>} typf sdifmb (mby bf <dodf>null</dodf>)
     *  <LI><B>TYPE_NAME</B> String {@dodf =>} typf nbmf
     *  <LI><B>ATTR_NAME</B> String {@dodf =>} bttributf nbmf
     *  <LI><B>DATA_TYPE</B> int {@dodf =>} bttributf typf SQL typf from jbvb.sql.Typfs
     *  <LI><B>ATTR_TYPE_NAME</B> String {@dodf =>} Dbtb sourdf dfpfndfnt typf nbmf.
     *  For b UDT, tif typf nbmf is fully qublififd. For b REF, tif typf nbmf is
     *  fully qublififd bnd rfprfsfnts tif tbrgft typf of tif rfffrfndf typf.
     *  <LI><B>ATTR_SIZE</B> int {@dodf =>} dolumn sizf.  For dibr or dbtf
     *      typfs tiis is tif mbximum numbfr of dibrbdtfrs; for numfrid or
     *      dfdimbl typfs tiis is prfdision.
     *  <LI><B>DECIMAL_DIGITS</B> int {@dodf =>} tif numbfr of frbdtionbl digits. Null is rfturnfd for dbtb typfs wifrf
     * DECIMAL_DIGITS is not bpplidbblf.
     *  <LI><B>NUM_PREC_RADIX</B> int {@dodf =>} Rbdix (typidblly fitifr 10 or 2)
     *  <LI><B>NULLABLE</B> int {@dodf =>} wiftifr NULL is bllowfd
     *      <UL>
     *      <LI> bttributfNoNulls - migit not bllow NULL vblufs
     *      <LI> bttributfNullbblf - dffinitfly bllows NULL vblufs
     *      <LI> bttributfNullbblfUnknown - nullbbility unknown
     *      </UL>
     *  <LI><B>REMARKS</B> String {@dodf =>} dommfnt dfsdribing dolumn (mby bf <dodf>null</dodf>)
     *  <LI><B>ATTR_DEF</B> String {@dodf =>} dffbult vbluf (mby bf <dodf>null</dodf>)
     *  <LI><B>SQL_DATA_TYPE</B> int {@dodf =>} unusfd
     *  <LI><B>SQL_DATETIME_SUB</B> int {@dodf =>} unusfd
     *  <LI><B>CHAR_OCTET_LENGTH</B> int {@dodf =>} for dibr typfs tif
     *       mbximum numbfr of bytfs in tif dolumn
     *  <LI><B>ORDINAL_POSITION</B> int {@dodf =>} indfx of tif bttributf in tif UDT
     *      (stbrting bt 1)
     *  <LI><B>IS_NULLABLE</B> String  {@dodf =>} ISO rulfs brf usfd to dftfrminf
     * tif nullbbility for b bttributf.
     *       <UL>
     *       <LI> YES           --- if tif bttributf dbn indludf NULLs
     *       <LI> NO            --- if tif bttributf dbnnot indludf NULLs
     *       <LI> fmpty string  --- if tif nullbbility for tif
     * bttributf is unknown
     *       </UL>
     *  <LI><B>SCOPE_CATALOG</B> String {@dodf =>} dbtblog of tbblf tibt is tif
     *      sdopf of b rfffrfndf bttributf (<dodf>null</dodf> if DATA_TYPE isn't REF)
     *  <LI><B>SCOPE_SCHEMA</B> String {@dodf =>} sdifmb of tbblf tibt is tif
     *      sdopf of b rfffrfndf bttributf (<dodf>null</dodf> if DATA_TYPE isn't REF)
     *  <LI><B>SCOPE_TABLE</B> String {@dodf =>} tbblf nbmf tibt is tif sdopf of b
     *      rfffrfndf bttributf (<dodf>null</dodf> if tif DATA_TYPE isn't REF)
     * <LI><B>SOURCE_DATA_TYPE</B> siort {@dodf =>} sourdf typf of b distindt typf or usfr-gfnfrbtfd
     *      Rff typf,SQL typf from jbvb.sql.Typfs (<dodf>null</dodf> if DATA_TYPE
     *      isn't DISTINCT or usfr-gfnfrbtfd REF)
     *  </OL>
     * @pbrbm dbtblog b dbtblog nbmf; must mbtdi tif dbtblog nbmf bs it
     *        is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b dbtblog;
     *        <dodf>null</dodf> mfbns tibt tif dbtblog nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm sdifmbPbttfrn b sdifmb nbmf pbttfrn; must mbtdi tif sdifmb nbmf
     *        bs it is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b sdifmb;
     *        <dodf>null</dodf> mfbns tibt tif sdifmb nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm typfNbmfPbttfrn b typf nbmf pbttfrn; must mbtdi tif
     *        typf nbmf bs it is storfd in tif dbtbbbsf
     * @pbrbm bttributfNbmfPbttfrn bn bttributf nbmf pbttfrn; must mbtdi tif bttributf
     *        nbmf bs it is dfdlbrfd in tif dbtbbbsf
     * @rfturn b <dodf>RfsultSft</dodf> objfdt in wiidi fbdi row is bn
     *         bttributf dfsdription
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sff #gftSfbrdiStringEsdbpf
     * @sindf 1.4
     */
    RfsultSft gftAttributfs(String dbtblog, String sdifmbPbttfrn,
                            String typfNbmfPbttfrn, String bttributfNbmfPbttfrn)
        tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports tif givfn rfsult sft ioldbbility.
     *
     * @pbrbm ioldbbility onf of tif following donstbnts:
     *          <dodf>RfsultSft.HOLD_CURSORS_OVER_COMMIT</dodf> or
     *          <dodf>RfsultSft.CLOSE_CURSORS_AT_COMMIT</dodf>
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sff Connfdtion
     * @sindf 1.4
     */
    boolfbn supportsRfsultSftHoldbbility(int ioldbbility) tirows SQLExdfption;

    /**
     * Rftrifvfs tiis dbtbbbsf's dffbult ioldbbility for <dodf>RfsultSft</dodf>
     * objfdts.
     *
     * @rfturn tif dffbult ioldbbility; fitifr
     *         <dodf>RfsultSft.HOLD_CURSORS_OVER_COMMIT</dodf> or
     *         <dodf>RfsultSft.CLOSE_CURSORS_AT_COMMIT</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.4
     */
    int gftRfsultSftHoldbbility() tirows SQLExdfption;

    /**
     * Rftrifvfs tif mbjor vfrsion numbfr of tif undfrlying dbtbbbsf.
     *
     * @rfturn tif undfrlying dbtbbbsf's mbjor vfrsion
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.4
     */
    int gftDbtbbbsfMbjorVfrsion() tirows SQLExdfption;

    /**
     * Rftrifvfs tif minor vfrsion numbfr of tif undfrlying dbtbbbsf.
     *
     * @rfturn undfrlying dbtbbbsf's minor vfrsion
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.4
     */
    int gftDbtbbbsfMinorVfrsion() tirows SQLExdfption;

    /**
     * Rftrifvfs tif mbjor JDBC vfrsion numbfr for tiis
     * drivfr.
     *
     * @rfturn JDBC vfrsion mbjor numbfr
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.4
     */
    int gftJDBCMbjorVfrsion() tirows SQLExdfption;

    /**
     * Rftrifvfs tif minor JDBC vfrsion numbfr for tiis
     * drivfr.
     *
     * @rfturn JDBC vfrsion minor numbfr
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.4
     */
    int gftJDBCMinorVfrsion() tirows SQLExdfption;

    /**
     *  A possiblf rfturn vbluf for tif mftiod
     * <dodf>DbtbbbsfMftbDbtb.gftSQLStbtfTypf</dodf> wiidi is usfd to indidbtf
     * wiftifr tif vbluf rfturnfd by tif mftiod
     * <dodf>SQLExdfption.gftSQLStbtf</dodf> is bn
     * X/Opfn (now know bs Opfn Group) SQL CLI SQLSTATE vbluf.
     *
     * @sindf 1.4
     */
    int sqlStbtfXOpfn = 1;

    /**
     *  A possiblf rfturn vbluf for tif mftiod
     * <dodf>DbtbbbsfMftbDbtb.gftSQLStbtfTypf</dodf> wiidi is usfd to indidbtf
     * wiftifr tif vbluf rfturnfd by tif mftiod
     * <dodf>SQLExdfption.gftSQLStbtf</dodf> is bn SQLSTATE vbluf.
     *
     * @sindf 1.6
     */
    int sqlStbtfSQL = 2;

     /**
     *  A possiblf rfturn vbluf for tif mftiod
     * <dodf>DbtbbbsfMftbDbtb.gftSQLStbtfTypf</dodf> wiidi is usfd to indidbtf
     * wiftifr tif vbluf rfturnfd by tif mftiod
     * <dodf>SQLExdfption.gftSQLStbtf</dodf> is bn SQL99 SQLSTATE vbluf.
     * <P>
     * <b>Notf:</b>Tiis donstbnt rfmbins only for dompbtibility rfbsons. Dfvflopfrs
     * siould usf tif donstbnt <dodf>sqlStbtfSQL</dodf> instfbd.
     *
     * @sindf 1.4
     */
    int sqlStbtfSQL99 = sqlStbtfSQL;

    /**
     * Indidbtfs wiftifr tif SQLSTATE rfturnfd by <dodf>SQLExdfption.gftSQLStbtf</dodf>
     * is X/Opfn (now known bs Opfn Group) SQL CLI or SQL:2003.
     * @rfturn tif typf of SQLSTATE; onf of:
     *        sqlStbtfXOpfn or
     *        sqlStbtfSQL
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.4
     */
    int gftSQLStbtfTypf() tirows SQLExdfption;

    /**
     * Indidbtfs wiftifr updbtfs mbdf to b LOB brf mbdf on b dopy or dirfdtly
     * to tif LOB.
     * @rfturn <dodf>truf</dodf> if updbtfs brf mbdf to b dopy of tif LOB;
     *         <dodf>fblsf</dodf> if updbtfs brf mbdf dirfdtly to tif LOB
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.4
     */
    boolfbn lodbtorsUpdbtfCopy() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports stbtfmfnt pooling.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.4
     */
    boolfbn supportsStbtfmfntPooling() tirows SQLExdfption;

    //------------------------- JDBC 4.0 -----------------------------------

    /**
     * Indidbtfs wiftifr or not tiis dbtb sourdf supports tif SQL <dodf>ROWID</dodf> typf,
     * bnd if so  tif lifftimf for wiidi b <dodf>RowId</dodf> objfdt rfmbins vblid.
     * <p>
     * Tif rfturnfd int vblufs ibvf tif following rflbtionsiip:
     * <prf>{@dodf
     *     ROWID_UNSUPPORTED < ROWID_VALID_OTHER < ROWID_VALID_TRANSACTION
     *         < ROWID_VALID_SESSION < ROWID_VALID_FOREVER
     * }</prf>
     * so donditionbl logid sudi bs
     * <prf>{@dodf
     *     if (mftbdbtb.gftRowIdLifftimf() > DbtbbbsfMftbDbtb.ROWID_VALID_TRANSACTION)
     * }</prf>
     * dbn bf usfd. Vblid Forfvfr mfbns vblid bdross bll Sfssions, bnd vblid for
     * b Sfssion mfbns vblid bdross bll its dontbinfd Trbnsbdtions.
     *
     * @rfturn tif stbtus indidbting tif lifftimf of b <dodf>RowId</dodf>
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.6
     */
    RowIdLifftimf gftRowIdLifftimf() tirows SQLExdfption;

    /**
     * Rftrifvfs tif sdifmb nbmfs bvbilbblf in tiis dbtbbbsf.  Tif rfsults
     * brf ordfrfd by <dodf>TABLE_CATALOG</dodf> bnd
     * <dodf>TABLE_SCHEM</dodf>.
     *
     * <P>Tif sdifmb dolumns brf:
     *  <OL>
     *  <LI><B>TABLE_SCHEM</B> String {@dodf =>} sdifmb nbmf
     *  <LI><B>TABLE_CATALOG</B> String {@dodf =>} dbtblog nbmf (mby bf <dodf>null</dodf>)
     *  </OL>
     *
     *
     * @pbrbm dbtblog b dbtblog nbmf; must mbtdi tif dbtblog nbmf bs it is storfd
     * in tif dbtbbbsf;"" rftrifvfs tiosf witiout b dbtblog; null mfbns dbtblog
     * nbmf siould not bf usfd to nbrrow down tif sfbrdi.
     * @pbrbm sdifmbPbttfrn b sdifmb nbmf; must mbtdi tif sdifmb nbmf bs it is
     * storfd in tif dbtbbbsf; null mfbns
     * sdifmb nbmf siould not bf usfd to nbrrow down tif sfbrdi.
     * @rfturn b <dodf>RfsultSft</dodf> objfdt in wiidi fbdi row is b
     *         sdifmb dfsdription
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sff #gftSfbrdiStringEsdbpf
     * @sindf 1.6
     */
    RfsultSft gftSdifmbs(String dbtblog, String sdifmbPbttfrn) tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports invoking usfr-dffinfd or vfndor fundtions
     * using tif storfd prodfdurf fsdbpf syntbx.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.6
     */
    boolfbn supportsStorfdFundtionsUsingCbllSyntbx() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr b <dodf>SQLExdfption</dodf> wiilf butoCommit is <dodf>truf</dodf> indidbtfs
     * tibt bll opfn RfsultSfts brf dlosfd, fvfn onfs tibt brf ioldbblf.  Wifn b <dodf>SQLExdfption</dodf> oddurs wiilf
     * butodommit is <dodf>truf</dodf>, it is vfndor spfdifid wiftifr tif JDBC drivfr rfsponds witi b dommit opfrbtion, b
     * rollbbdk opfrbtion, or by doing nfitifr b dommit nor b rollbbdk.  A potfntibl rfsult of tiis difffrfndf
     * is in wiftifr or not ioldbblf RfsultSfts brf dlosfd.
     *
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.6
     */
    boolfbn butoCommitFbilurfClosfsAllRfsultSfts() tirows SQLExdfption;
        /**
         * Rftrifvfs b list of tif dlifnt info propfrtifs
         * tibt tif drivfr supports.  Tif rfsult sft dontbins tif following dolumns
         *
         * <ol>
         * <li><b>NAME</b> String{@dodf =>} Tif nbmf of tif dlifnt info propfrty<br>
         * <li><b>MAX_LEN</b> int{@dodf =>} Tif mbximum lfngti of tif vbluf for tif propfrty<br>
         * <li><b>DEFAULT_VALUE</b> String{@dodf =>} Tif dffbult vbluf of tif propfrty<br>
         * <li><b>DESCRIPTION</b> String{@dodf =>} A dfsdription of tif propfrty.  Tiis will typidblly
         *                                              dontbin informbtion bs to wifrf tiis propfrty is
         *                                              storfd in tif dbtbbbsf.
         * </ol>
         * <p>
         * Tif <dodf>RfsultSft</dodf> is sortfd by tif NAME dolumn
         *
         * @rfturn      A <dodf>RfsultSft</dodf> objfdt; fbdi row is b supportfd dlifnt info
         * propfrty
         *
         *  @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
         *
         * @sindf 1.6
         */
        RfsultSft gftClifntInfoPropfrtifs()
                tirows SQLExdfption;

    /**
     * Rftrifvfs b dfsdription of tif  systfm bnd usfr fundtions bvbilbblf
     * in tif givfn dbtblog.
     * <P>
     * Only systfm bnd usfr fundtion dfsdriptions mbtdiing tif sdifmb bnd
     * fundtion nbmf dritfrib brf rfturnfd.  Tify brf ordfrfd by
     * <dodf>FUNCTION_CAT</dodf>, <dodf>FUNCTION_SCHEM</dodf>,
     * <dodf>FUNCTION_NAME</dodf> bnd
     * <dodf>SPECIFIC_ NAME</dodf>.
     *
     * <P>Ebdi fundtion dfsdription ibs tif tif following dolumns:
     *  <OL>
     *  <LI><B>FUNCTION_CAT</B> String {@dodf =>} fundtion dbtblog (mby bf <dodf>null</dodf>)
     *  <LI><B>FUNCTION_SCHEM</B> String {@dodf =>} fundtion sdifmb (mby bf <dodf>null</dodf>)
     *  <LI><B>FUNCTION_NAME</B> String {@dodf =>} fundtion nbmf.  Tiis is tif nbmf
     * usfd to invokf tif fundtion
     *  <LI><B>REMARKS</B> String {@dodf =>} fxplbnbtory dommfnt on tif fundtion
     * <LI><B>FUNCTION_TYPE</B> siort {@dodf =>} kind of fundtion:
     *      <UL>
     *      <LI>fundtionRfsultUnknown - Cbnnot dftfrminf if b rfturn vbluf
     *       or tbblf will bf rfturnfd
     *      <LI> fundtionNoTbblf- Dofs not rfturn b tbblf
     *      <LI> fundtionRfturnsTbblf - Rfturns b tbblf
     *      </UL>
     *  <LI><B>SPECIFIC_NAME</B> String  {@dodf =>} tif nbmf wiidi uniqufly idfntififs
     *  tiis fundtion witiin its sdifmb.  Tiis is b usfr spfdififd, or DBMS
     * gfnfrbtfd, nbmf tibt mby bf difffrfnt tifn tif <dodf>FUNCTION_NAME</dodf>
     * for fxbmplf witi ovfrlobd fundtions
     *  </OL>
     * <p>
     * A usfr mby not ibvf pfrmission to fxfdutf bny of tif fundtions tibt brf
     * rfturnfd by <dodf>gftFundtions</dodf>
     *
     * @pbrbm dbtblog b dbtblog nbmf; must mbtdi tif dbtblog nbmf bs it
     *        is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b dbtblog;
     *        <dodf>null</dodf> mfbns tibt tif dbtblog nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm sdifmbPbttfrn b sdifmb nbmf pbttfrn; must mbtdi tif sdifmb nbmf
     *        bs it is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b sdifmb;
     *        <dodf>null</dodf> mfbns tibt tif sdifmb nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm fundtionNbmfPbttfrn b fundtion nbmf pbttfrn; must mbtdi tif
     *        fundtion nbmf bs it is storfd in tif dbtbbbsf
     * @rfturn <dodf>RfsultSft</dodf> - fbdi row is b fundtion dfsdription
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sff #gftSfbrdiStringEsdbpf
     * @sindf 1.6
     */
    RfsultSft gftFundtions(String dbtblog, String sdifmbPbttfrn,
                            String fundtionNbmfPbttfrn) tirows SQLExdfption;
    /**
     * Rftrifvfs b dfsdription of tif givfn dbtblog's systfm or usfr
     * fundtion pbrbmftfrs bnd rfturn typf.
     *
     * <P>Only dfsdriptions mbtdiing tif sdifmb,  fundtion bnd
     * pbrbmftfr nbmf dritfrib brf rfturnfd. Tify brf ordfrfd by
     * <dodf>FUNCTION_CAT</dodf>, <dodf>FUNCTION_SCHEM</dodf>,
     * <dodf>FUNCTION_NAME</dodf> bnd
     * <dodf>SPECIFIC_ NAME</dodf>. Witiin tiis, tif rfturn vbluf,
     * if bny, is first. Nfxt brf tif pbrbmftfr dfsdriptions in dbll
     * ordfr. Tif dolumn dfsdriptions follow in dolumn numbfr ordfr.
     *
     * <P>Ebdi row in tif <dodf>RfsultSft</dodf>
     * is b pbrbmftfr dfsdription, dolumn dfsdription or
     * rfturn typf dfsdription witi tif following fiflds:
     *  <OL>
     *  <LI><B>FUNCTION_CAT</B> String {@dodf =>} fundtion dbtblog (mby bf <dodf>null</dodf>)
     *  <LI><B>FUNCTION_SCHEM</B> String {@dodf =>} fundtion sdifmb (mby bf <dodf>null</dodf>)
     *  <LI><B>FUNCTION_NAME</B> String {@dodf =>} fundtion nbmf.  Tiis is tif nbmf
     * usfd to invokf tif fundtion
     *  <LI><B>COLUMN_NAME</B> String {@dodf =>} dolumn/pbrbmftfr nbmf
     *  <LI><B>COLUMN_TYPE</B> Siort {@dodf =>} kind of dolumn/pbrbmftfr:
     *      <UL>
     *      <LI> fundtionColumnUnknown - nobody knows
     *      <LI> fundtionColumnIn - IN pbrbmftfr
     *      <LI> fundtionColumnInOut - INOUT pbrbmftfr
     *      <LI> fundtionColumnOut - OUT pbrbmftfr
     *      <LI> fundtionColumnRfturn - fundtion rfturn vbluf
     *      <LI> fundtionColumnRfsult - Indidbtfs tibt tif pbrbmftfr or dolumn
     *  is b dolumn in tif <dodf>RfsultSft</dodf>
     *      </UL>
     *  <LI><B>DATA_TYPE</B> int {@dodf =>} SQL typf from jbvb.sql.Typfs
     *  <LI><B>TYPE_NAME</B> String {@dodf =>} SQL typf nbmf, for b UDT typf tif
     *  typf nbmf is fully qublififd
     *  <LI><B>PRECISION</B> int {@dodf =>} prfdision
     *  <LI><B>LENGTH</B> int {@dodf =>} lfngti in bytfs of dbtb
     *  <LI><B>SCALE</B> siort {@dodf =>} sdblf -  null is rfturnfd for dbtb typfs wifrf
     * SCALE is not bpplidbblf.
     *  <LI><B>RADIX</B> siort {@dodf =>} rbdix
     *  <LI><B>NULLABLE</B> siort {@dodf =>} dbn it dontbin NULL.
     *      <UL>
     *      <LI> fundtionNoNulls - dofs not bllow NULL vblufs
     *      <LI> fundtionNullbblf - bllows NULL vblufs
     *      <LI> fundtionNullbblfUnknown - nullbbility unknown
     *      </UL>
     *  <LI><B>REMARKS</B> String {@dodf =>} dommfnt dfsdribing dolumn/pbrbmftfr
     *  <LI><B>CHAR_OCTET_LENGTH</B> int  {@dodf =>} tif mbximum lfngti of binbry
     * bnd dibrbdtfr bbsfd pbrbmftfrs or dolumns.  For bny otifr dbtbtypf tif rfturnfd vbluf
     * is b NULL
     *  <LI><B>ORDINAL_POSITION</B> int  {@dodf =>} tif ordinbl position, stbrting
     * from 1, for tif input bnd output pbrbmftfrs. A vbluf of 0
     * is rfturnfd if tiis row dfsdribfs tif fundtion's rfturn vbluf.
     * For rfsult sft dolumns, it is tif
     * ordinbl position of tif dolumn in tif rfsult sft stbrting from 1.
     *  <LI><B>IS_NULLABLE</B> String  {@dodf =>} ISO rulfs brf usfd to dftfrminf
     * tif nullbbility for b pbrbmftfr or dolumn.
     *       <UL>
     *       <LI> YES           --- if tif pbrbmftfr or dolumn dbn indludf NULLs
     *       <LI> NO            --- if tif pbrbmftfr or dolumn  dbnnot indludf NULLs
     *       <LI> fmpty string  --- if tif nullbbility for tif
     * pbrbmftfr  or dolumn is unknown
     *       </UL>
     *  <LI><B>SPECIFIC_NAME</B> String  {@dodf =>} tif nbmf wiidi uniqufly idfntififs
     * tiis fundtion witiin its sdifmb.  Tiis is b usfr spfdififd, or DBMS
     * gfnfrbtfd, nbmf tibt mby bf difffrfnt tifn tif <dodf>FUNCTION_NAME</dodf>
     * for fxbmplf witi ovfrlobd fundtions
     *  </OL>
     *
     * <p>Tif PRECISION dolumn rfprfsfnts tif spfdififd dolumn sizf for tif givfn
     * pbrbmftfr or dolumn.
     * For numfrid dbtb, tiis is tif mbximum prfdision.  For dibrbdtfr dbtb, tiis is tif lfngti in dibrbdtfrs.
     * For dbtftimf dbtbtypfs, tiis is tif lfngti in dibrbdtfrs of tif String rfprfsfntbtion (bssuming tif
     * mbximum bllowfd prfdision of tif frbdtionbl sfdonds domponfnt). For binbry dbtb, tiis is tif lfngti in bytfs.  For tif ROWID dbtbtypf,
     * tiis is tif lfngti in bytfs. Null is rfturnfd for dbtb typfs wifrf tif
     * dolumn sizf is not bpplidbblf.
     * @pbrbm dbtblog b dbtblog nbmf; must mbtdi tif dbtblog nbmf bs it
     *        is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b dbtblog;
     *        <dodf>null</dodf> mfbns tibt tif dbtblog nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm sdifmbPbttfrn b sdifmb nbmf pbttfrn; must mbtdi tif sdifmb nbmf
     *        bs it is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b sdifmb;
     *        <dodf>null</dodf> mfbns tibt tif sdifmb nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm fundtionNbmfPbttfrn b prodfdurf nbmf pbttfrn; must mbtdi tif
     *        fundtion nbmf bs it is storfd in tif dbtbbbsf
     * @pbrbm dolumnNbmfPbttfrn b pbrbmftfr nbmf pbttfrn; must mbtdi tif
     * pbrbmftfr or dolumn nbmf bs it is storfd in tif dbtbbbsf
     * @rfturn <dodf>RfsultSft</dodf> - fbdi row dfsdribfs b
     * usfr fundtion pbrbmftfr, dolumn  or rfturn typf
     *
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sff #gftSfbrdiStringEsdbpf
     * @sindf 1.6
     */
    RfsultSft gftFundtionColumns(String dbtblog,
                                  String sdifmbPbttfrn,
                                  String fundtionNbmfPbttfrn,
                                  String dolumnNbmfPbttfrn) tirows SQLExdfption;


    /**
     * Indidbtfs tibt typf of tif pbrbmftfr or dolumn is unknown.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>COLUMN_TYPE</dodf>
     * in tif <dodf>RfsultSft</dodf>
     * rfturnfd by tif mftiod <dodf>gftFundtionColumns</dodf>.
     */
    int fundtionColumnUnknown = 0;

    /**
     * Indidbtfs tibt tif pbrbmftfr or dolumn is bn IN pbrbmftfr.
     * <P>
     *  A possiblf vbluf for tif dolumn
     * <dodf>COLUMN_TYPE</dodf>
     * in tif <dodf>RfsultSft</dodf>
     * rfturnfd by tif mftiod <dodf>gftFundtionColumns</dodf>.
     * @sindf 1.6
     */
    int fundtionColumnIn = 1;

    /**
     * Indidbtfs tibt tif pbrbmftfr or dolumn is bn INOUT pbrbmftfr.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>COLUMN_TYPE</dodf>
     * in tif <dodf>RfsultSft</dodf>
     * rfturnfd by tif mftiod <dodf>gftFundtionColumns</dodf>.
     * @sindf 1.6
     */
    int fundtionColumnInOut = 2;

    /**
     * Indidbtfs tibt tif pbrbmftfr or dolumn is bn OUT pbrbmftfr.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>COLUMN_TYPE</dodf>
     * in tif <dodf>RfsultSft</dodf>
     * rfturnfd by tif mftiod <dodf>gftFundtionColumns</dodf>.
     * @sindf 1.6
     */
    int fundtionColumnOut = 3;
    /**
     * Indidbtfs tibt tif pbrbmftfr or dolumn is b rfturn vbluf.
     * <P>
     *  A possiblf vbluf for tif dolumn
     * <dodf>COLUMN_TYPE</dodf>
     * in tif <dodf>RfsultSft</dodf>
     * rfturnfd by tif mftiod <dodf>gftFundtionColumns</dodf>.
     * @sindf 1.6
     */
    int fundtionRfturn = 4;

       /**
     * Indidbtfs tibt tif pbrbmftfr or dolumn is b dolumn in b rfsult sft.
     * <P>
     *  A possiblf vbluf for tif dolumn
     * <dodf>COLUMN_TYPE</dodf>
     * in tif <dodf>RfsultSft</dodf>
     * rfturnfd by tif mftiod <dodf>gftFundtionColumns</dodf>.
     * @sindf 1.6
     */
    int fundtionColumnRfsult = 5;


    /**
     * Indidbtfs tibt <dodf>NULL</dodf> vblufs brf not bllowfd.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>NULLABLE</dodf>
     * in tif <dodf>RfsultSft</dodf> objfdt
     * rfturnfd by tif mftiod <dodf>gftFundtionColumns</dodf>.
     * @sindf 1.6
     */
    int fundtionNoNulls = 0;

    /**
     * Indidbtfs tibt <dodf>NULL</dodf> vblufs brf bllowfd.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>NULLABLE</dodf>
     * in tif <dodf>RfsultSft</dodf> objfdt
     * rfturnfd by tif mftiod <dodf>gftFundtionColumns</dodf>.
     * @sindf 1.6
     */
    int fundtionNullbblf = 1;

    /**
     * Indidbtfs tibt wiftifr <dodf>NULL</dodf> vblufs brf bllowfd
     * is unknown.
     * <P>
     * A possiblf vbluf for tif dolumn
     * <dodf>NULLABLE</dodf>
     * in tif <dodf>RfsultSft</dodf> objfdt
     * rfturnfd by tif mftiod <dodf>gftFundtionColumns</dodf>.
     * @sindf 1.6
     */
    int fundtionNullbblfUnknown = 2;

    /**
     * Indidbtfs tibt it is not known wiftifr tif fundtion rfturns
     * b rfsult or b tbblf.
     * <P>
     * A possiblf vbluf for dolumn <dodf>FUNCTION_TYPE</dodf> in tif
     * <dodf>RfsultSft</dodf> objfdt rfturnfd by tif mftiod
     * <dodf>gftFundtions</dodf>.
     * @sindf 1.6
     */
    int fundtionRfsultUnknown   = 0;

    /**
     * Indidbtfs tibt tif fundtion  dofs not rfturn b tbblf.
     * <P>
     * A possiblf vbluf for dolumn <dodf>FUNCTION_TYPE</dodf> in tif
     * <dodf>RfsultSft</dodf> objfdt rfturnfd by tif mftiod
     * <dodf>gftFundtions</dodf>.
     * @sindf 1.6
     */
    int fundtionNoTbblf         = 1;

    /**
     * Indidbtfs tibt tif fundtion  rfturns b tbblf.
     * <P>
     * A possiblf vbluf for dolumn <dodf>FUNCTION_TYPE</dodf> in tif
     * <dodf>RfsultSft</dodf> objfdt rfturnfd by tif mftiod
     * <dodf>gftFundtions</dodf>.
     * @sindf 1.6
     */
    int fundtionRfturnsTbblf    = 2;

    //--------------------------JDBC 4.1 -----------------------------

    /**
     * Rftrifvfs b dfsdription of tif psfudo or iiddfn dolumns bvbilbblf
     * in b givfn tbblf witiin tif spfdififd dbtblog bnd sdifmb.
     * Psfudo or iiddfn dolumns mby not blwbys bf storfd witiin
     * b tbblf bnd brf not visiblf in b RfsultSft unlfss tify brf
     * spfdififd in tif qufry's outfrmost SELECT list. Psfudo or iiddfn
     * dolumns mby not nfdfssbrily bf bblf to bf modififd. If tifrf brf
     * no psfudo or iiddfn dolumns, bn fmpty RfsultSft is rfturnfd.
     *
     * <P>Only dolumn dfsdriptions mbtdiing tif dbtblog, sdifmb, tbblf
     * bnd dolumn nbmf dritfrib brf rfturnfd.  Tify brf ordfrfd by
     * <dodf>TABLE_CAT</dodf>,<dodf>TABLE_SCHEM</dodf>, <dodf>TABLE_NAME</dodf>
     * bnd <dodf>COLUMN_NAME</dodf>.
     *
     * <P>Ebdi dolumn dfsdription ibs tif following dolumns:
     *  <OL>
     *  <LI><B>TABLE_CAT</B> String {@dodf =>} tbblf dbtblog (mby bf <dodf>null</dodf>)
     *  <LI><B>TABLE_SCHEM</B> String {@dodf =>} tbblf sdifmb (mby bf <dodf>null</dodf>)
     *  <LI><B>TABLE_NAME</B> String {@dodf =>} tbblf nbmf
     *  <LI><B>COLUMN_NAME</B> String {@dodf =>} dolumn nbmf
     *  <LI><B>DATA_TYPE</B> int {@dodf =>} SQL typf from jbvb.sql.Typfs
     *  <LI><B>COLUMN_SIZE</B> int {@dodf =>} dolumn sizf.
     *  <LI><B>DECIMAL_DIGITS</B> int {@dodf =>} tif numbfr of frbdtionbl digits. Null is rfturnfd for dbtb typfs wifrf
     * DECIMAL_DIGITS is not bpplidbblf.
     *  <LI><B>NUM_PREC_RADIX</B> int {@dodf =>} Rbdix (typidblly fitifr 10 or 2)
     *  <LI><B>COLUMN_USAGE</B> String {@dodf =>} Tif bllowfd usbgf for tif dolumn.  Tif
     *  vbluf rfturnfd will dorrfspond to tif fnum nbmf rfturnfd by {@link PsfudoColumnUsbgf#nbmf PsfudoColumnUsbgf.nbmf()}
     *  <LI><B>REMARKS</B> String {@dodf =>} dommfnt dfsdribing dolumn (mby bf <dodf>null</dodf>)
     *  <LI><B>CHAR_OCTET_LENGTH</B> int {@dodf =>} for dibr typfs tif
     *       mbximum numbfr of bytfs in tif dolumn
     *  <LI><B>IS_NULLABLE</B> String  {@dodf =>} ISO rulfs brf usfd to dftfrminf tif nullbbility for b dolumn.
     *       <UL>
     *       <LI> YES           --- if tif dolumn dbn indludf NULLs
     *       <LI> NO            --- if tif dolumn dbnnot indludf NULLs
     *       <LI> fmpty string  --- if tif nullbbility for tif dolumn is unknown
     *       </UL>
     *  </OL>
     *
     * <p>Tif COLUMN_SIZE dolumn spfdififs tif dolumn sizf for tif givfn dolumn.
     * For numfrid dbtb, tiis is tif mbximum prfdision.  For dibrbdtfr dbtb, tiis is tif lfngti in dibrbdtfrs.
     * For dbtftimf dbtbtypfs, tiis is tif lfngti in dibrbdtfrs of tif String rfprfsfntbtion (bssuming tif
     * mbximum bllowfd prfdision of tif frbdtionbl sfdonds domponfnt). For binbry dbtb, tiis is tif lfngti in bytfs.  For tif ROWID dbtbtypf,
     * tiis is tif lfngti in bytfs. Null is rfturnfd for dbtb typfs wifrf tif
     * dolumn sizf is not bpplidbblf.
     *
     * @pbrbm dbtblog b dbtblog nbmf; must mbtdi tif dbtblog nbmf bs it
     *        is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b dbtblog;
     *        <dodf>null</dodf> mfbns tibt tif dbtblog nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm sdifmbPbttfrn b sdifmb nbmf pbttfrn; must mbtdi tif sdifmb nbmf
     *        bs it is storfd in tif dbtbbbsf; "" rftrifvfs tiosf witiout b sdifmb;
     *        <dodf>null</dodf> mfbns tibt tif sdifmb nbmf siould not bf usfd to nbrrow
     *        tif sfbrdi
     * @pbrbm tbblfNbmfPbttfrn b tbblf nbmf pbttfrn; must mbtdi tif
     *        tbblf nbmf bs it is storfd in tif dbtbbbsf
     * @pbrbm dolumnNbmfPbttfrn b dolumn nbmf pbttfrn; must mbtdi tif dolumn
     *        nbmf bs it is storfd in tif dbtbbbsf
     * @rfturn <dodf>RfsultSft</dodf> - fbdi row is b dolumn dfsdription
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sff PsfudoColumnUsbgf
     * @sindf 1.7
     */
    RfsultSft gftPsfudoColumns(String dbtblog, String sdifmbPbttfrn,
                         String tbblfNbmfPbttfrn, String dolumnNbmfPbttfrn)
        tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr b gfnfrbtfd kfy will blwbys bf rfturnfd if tif dolumn
     * nbmf(s) or indfx(fs) spfdififd for tif buto gfnfrbtfd kfy dolumn(s)
     * brf vblid bnd tif stbtfmfnt suddffds.  Tif kfy tibt is rfturnfd mby or
     * mby not bf bbsfd on tif dolumn(s) for tif buto gfnfrbtfd kfy.
     * Consult your JDBC drivfr dodumfntbtion for bdditionbl dftbils.
     * @rfturn <dodf>truf</dodf> if so; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.7
     */
    boolfbn  gfnfrbtfdKfyAlwbysRfturnfd() tirows SQLExdfption;

    //--------------------------JDBC 4.2 -----------------------------

    /**
     *
     * Rftrifvfs tif mbximum numbfr of bytfs tiis dbtbbbsf bllows for
     * tif logidbl sizf for b {@dodf LOB}.
     *<p>
     * Tif dffbult implfmfntbtion will rfturn {@dodf 0}
     *
     * @rfturn tif mbximum numbfr of bytfs bllowfd; b rfsult of zfro
     * mfbns tibt tifrf is no limit or tif limit is not known
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.8
     */
    dffbult long gftMbxLogidblLobSizf() tirows SQLExdfption {
        rfturn 0;
    }

    /**
     * Rftrifvfs wiftifr tiis dbtbbbsf supports REF CURSOR.
     *<p>
     * Tif dffbult implfmfntbtion will rfturn {@dodf fblsf}
     *
     * @rfturn {@dodf truf} if tiis dbtbbbsf supports REF CURSOR;
     *         {@dodf fblsf} otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.8
     */
    dffbult boolfbn supportsRffCursors() tirows SQLExdfption{
        rfturn fblsf;
    }

}

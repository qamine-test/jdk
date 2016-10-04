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

import jbvb.mbti.BigDfdimbl;
import jbvb.util.Cblfndbr;
import jbvb.io.Rfbdfr;
import jbvb.io.InputStrfbm;

/**
 * A tbblf of dbtb rfprfsfnting b dbtbbbsf rfsult sft, wiidi
 * is usublly gfnfrbtfd by fxfduting b stbtfmfnt tibt qufrifs tif dbtbbbsf.
 *
 * <P>A <dodf>RfsultSft</dodf> objfdt  mbintbins b dursor pointing
 * to its durrfnt row of dbtb.  Initiblly tif dursor is positionfd
 * bfforf tif first row. Tif <dodf>nfxt</dodf> mftiod movfs tif
 * dursor to tif nfxt row, bnd bfdbusf it rfturns <dodf>fblsf</dodf>
 * wifn tifrf brf no morf rows in tif <dodf>RfsultSft</dodf> objfdt,
 * it dbn bf usfd in b <dodf>wiilf</dodf> loop to itfrbtf tirougi
 * tif rfsult sft.
 * <P>
 * A dffbult <dodf>RfsultSft</dodf> objfdt is not updbtbblf bnd
 * ibs b dursor tibt movfs forwbrd only.  Tius, you dbn
 * itfrbtf tirougi it only ondf bnd only from tif first row to tif
 * lbst row. It is possiblf to
 * produdf <dodf>RfsultSft</dodf> objfdts tibt brf sdrollbblf bnd/or
 * updbtbblf.  Tif following dodf frbgmfnt, in wiidi <dodf>don</dodf>
 * is b vblid <dodf>Connfdtion</dodf> objfdt, illustrbtfs iow to mbkf
 * b rfsult sft tibt is sdrollbblf bnd insfnsitivf to updbtfs by otifrs, bnd
 * tibt is updbtbblf. Sff <dodf>RfsultSft</dodf> fiflds for otifr
 * options.
 * <PRE>
 *
 *       Stbtfmfnt stmt = don.drfbtfStbtfmfnt(
 *                                      RfsultSft.TYPE_SCROLL_INSENSITIVE,
 *                                      RfsultSft.CONCUR_UPDATABLE);
 *       RfsultSft rs = stmt.fxfdutfQufry("SELECT b, b FROM TABLE2");
 *       // rs will bf sdrollbblf, will not siow dibngfs mbdf by otifrs,
 *       // bnd will bf updbtbblf
 *
 * </PRE>
 * Tif <dodf>RfsultSft</dodf> intfrfbdf providfs
 * <i>gfttfr</i> mftiods (<dodf>gftBoolfbn</dodf>, <dodf>gftLong</dodf>, bnd so on)
 * for rftrifving dolumn vblufs from tif durrfnt row.
 * Vblufs dbn bf rftrifvfd using fitifr tif indfx numbfr of tif
 * dolumn or tif nbmf of tif dolumn.  In gfnfrbl, using tif
 * dolumn indfx will bf morf fffidifnt.  Columns brf numbfrfd from 1.
 * For mbximum portbbility, rfsult sft dolumns witiin fbdi row siould bf
 * rfbd in lfft-to-rigit ordfr, bnd fbdi dolumn siould bf rfbd only ondf.
 *
 * <P>For tif gfttfr mftiods, b JDBC drivfr bttfmpts
 * to donvfrt tif undfrlying dbtb to tif Jbvb typf spfdififd in tif
 * gfttfr mftiod bnd rfturns b suitbblf Jbvb vbluf.  Tif JDBC spfdifidbtion
 * ibs b tbblf siowing tif bllowbblf mbppings from SQL typfs to Jbvb typfs
 * tibt dbn bf usfd by tif <dodf>RfsultSft</dodf> gfttfr mftiods.
 *
 * <P>Column nbmfs usfd bs input to gfttfr mftiods brf dbsf
 * insfnsitivf.  Wifn b gfttfr mftiod is dbllfd  witi
 * b dolumn nbmf bnd sfvfrbl dolumns ibvf tif sbmf nbmf,
 * tif vbluf of tif first mbtdiing dolumn will bf rfturnfd.
 * Tif dolumn nbmf option is
 * dfsignfd to bf usfd wifn dolumn nbmfs brf usfd in tif SQL
 * qufry tibt gfnfrbtfd tif rfsult sft.
 * For dolumns tibt brf NOT fxpliditly nbmfd in tif qufry, it
 * is bfst to usf dolumn numbfrs. If dolumn nbmfs brf usfd, tif
 * progrbmmfr siould tbkf dbrf to gubrbntff tibt tify uniqufly rfffr to
 * tif intfndfd dolumns, wiidi dbn bf bssurfd witi tif SQL <i>AS</i> dlbusf.
 * <P>
 * A sft of updbtfr mftiods wfrf bddfd to tiis intfrfbdf
 * in tif JDBC 2.0 API (Jbvb&trbdf; 2 SDK,
 * Stbndbrd Edition, vfrsion 1.2). Tif dommfnts rfgbrding pbrbmftfrs
 * to tif gfttfr mftiods blso bpply to pbrbmftfrs to tif
 * updbtfr mftiods.
 *<P>
 * Tif updbtfr mftiods mby bf usfd in two wbys:
 * <ol>
 * <LI>to updbtf b dolumn vbluf in tif durrfnt row.  In b sdrollbblf
 *     <dodf>RfsultSft</dodf> objfdt, tif dursor dbn bf movfd bbdkwbrds
 *     bnd forwbrds, to bn bbsolutf position, or to b position
 *     rflbtivf to tif durrfnt row.
 *     Tif following dodf frbgmfnt updbtfs tif <dodf>NAME</dodf> dolumn
 *     in tif fifti row of tif <dodf>RfsultSft</dodf> objfdt
 *     <dodf>rs</dodf> bnd tifn usfs tif mftiod <dodf>updbtfRow</dodf>
 *     to updbtf tif dbtb sourdf tbblf from wiidi <dodf>rs</dodf> wbs dfrivfd.
 * <PRE>
 *
 *       rs.bbsolutf(5); // movfs tif dursor to tif fifti row of rs
 *       rs.updbtfString("NAME", "AINSWORTH"); // updbtfs tif
 *          // <dodf>NAME</dodf> dolumn of row 5 to bf <dodf>AINSWORTH</dodf>
 *       rs.updbtfRow(); // updbtfs tif row in tif dbtb sourdf
 *
 * </PRE>
 * <LI>to insfrt dolumn vblufs into tif insfrt row.  An updbtbblf
 *     <dodf>RfsultSft</dodf> objfdt ibs b spfdibl row bssodibtfd witi
 *     it tibt sfrvfs bs b stbging brfb for building b row to bf insfrtfd.
 *     Tif following dodf frbgmfnt movfs tif dursor to tif insfrt row, builds
 *     b tirff-dolumn row, bnd insfrts it into <dodf>rs</dodf> bnd into
 *     tif dbtb sourdf tbblf using tif mftiod <dodf>insfrtRow</dodf>.
 * <PRE>
 *
 *       rs.movfToInsfrtRow(); // movfs dursor to tif insfrt row
 *       rs.updbtfString(1, "AINSWORTH"); // updbtfs tif
 *          // first dolumn of tif insfrt row to bf <dodf>AINSWORTH</dodf>
 *       rs.updbtfInt(2,35); // updbtfs tif sfdond dolumn to bf <dodf>35</dodf>
 *       rs.updbtfBoolfbn(3, truf); // updbtfs tif tiird dolumn to <dodf>truf</dodf>
 *       rs.insfrtRow();
 *       rs.movfToCurrfntRow();
 *
 * </PRE>
 * </ol>
 * <P>A <dodf>RfsultSft</dodf> objfdt is butombtidblly dlosfd wifn tif
 * <dodf>Stbtfmfnt</dodf> objfdt tibt
 * gfnfrbtfd it is dlosfd, rf-fxfdutfd, or usfd
 * to rftrifvf tif nfxt rfsult from b sfqufndf of multiplf rfsults.
 *
 * <P>Tif numbfr, typfs bnd propfrtifs of b <dodf>RfsultSft</dodf>
 * objfdt's dolumns brf providfd by tif <dodf>RfsultSftMftbDbtb</dodf>
 * objfdt rfturnfd by tif <dodf>RfsultSft.gftMftbDbtb</dodf> mftiod.
 *
 * @sff Stbtfmfnt#fxfdutfQufry
 * @sff Stbtfmfnt#gftRfsultSft
 * @sff RfsultSftMftbDbtb
 */

publid intfrfbdf RfsultSft fxtfnds Wrbppfr, AutoClosfbblf {

    /**
     * Movfs tif dursor forwbrd onf row from its durrfnt position.
     * A <dodf>RfsultSft</dodf> dursor is initiblly positionfd
     * bfforf tif first row; tif first dbll to tif mftiod
     * <dodf>nfxt</dodf> mbkfs tif first row tif durrfnt row; tif
     * sfdond dbll mbkfs tif sfdond row tif durrfnt row, bnd so on.
     * <p>
     * Wifn b dbll to tif <dodf>nfxt</dodf> mftiod rfturns <dodf>fblsf</dodf>,
     * tif dursor is positionfd bftfr tif lbst row. Any
     * invodbtion of b <dodf>RfsultSft</dodf> mftiod wiidi rfquirfs b
     * durrfnt row will rfsult in b <dodf>SQLExdfption</dodf> bfing tirown.
     *  If tif rfsult sft typf is <dodf>TYPE_FORWARD_ONLY</dodf>, it is vfndor spfdififd
     * wiftifr tifir JDBC drivfr implfmfntbtion will rfturn <dodf>fblsf</dodf> or
     *  tirow bn <dodf>SQLExdfption</dodf> on b
     * subsfqufnt dbll to <dodf>nfxt</dodf>.
     *
     * <P>If bn input strfbm is opfn for tif durrfnt row, b dbll
     * to tif mftiod <dodf>nfxt</dodf> will
     * impliditly dlosf it. A <dodf>RfsultSft</dodf> objfdt's
     * wbrning dibin is dlfbrfd wifn b nfw row is rfbd.
     *
     * @rfturn <dodf>truf</dodf> if tif nfw durrfnt row is vblid;
     * <dodf>fblsf</dodf> if tifrf brf no morf rows
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    boolfbn nfxt() tirows SQLExdfption;


    /**
     * Rflfbsfs tiis <dodf>RfsultSft</dodf> objfdt's dbtbbbsf bnd
     * JDBC rfsourdfs immfdibtfly instfbd of wbiting for
     * tiis to ibppfn wifn it is butombtidblly dlosfd.
     *
     * <P>Tif dlosing of b <dodf>RfsultSft</dodf> objfdt dofs <strong>not</strong> dlosf tif <dodf>Blob</dodf>,
     * <dodf>Clob</dodf> or <dodf>NClob</dodf> objfdts drfbtfd by tif <dodf>RfsultSft</dodf>. <dodf>Blob</dodf>,
     * <dodf>Clob</dodf> or <dodf>NClob</dodf> objfdts rfmbin vblid for bt lfbst tif durbtion of tif
     * trbnsbdtion in wiidi tify brf drfbtfd, unlfss tifir <dodf>frff</dodf> mftiod is invokfd.
     *<p>
     * Wifn b <dodf>RfsultSft</dodf> is dlosfd, bny <dodf>RfsultSftMftbDbtb</dodf>
     * instbndfs tibt wfrf drfbtfd by dblling tif  <dodf>gftMftbDbtb</dodf>
     * mftiod rfmbin bddfssiblf.
     *
     * <P><B>Notf:</B> A <dodf>RfsultSft</dodf> objfdt
     * is butombtidblly dlosfd by tif
     * <dodf>Stbtfmfnt</dodf> objfdt tibt gfnfrbtfd it wifn
     * tibt <dodf>Stbtfmfnt</dodf> objfdt is dlosfd,
     * rf-fxfdutfd, or is usfd to rftrifvf tif nfxt rfsult from b
     * sfqufndf of multiplf rfsults.
     *<p>
     * Cblling tif mftiod <dodf>dlosf</dodf> on b <dodf>RfsultSft</dodf>
     * objfdt tibt is blrfbdy dlosfd is b no-op.
     *
     *
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    void dlosf() tirows SQLExdfption;

    /**
     * Rfports wiftifr
     * tif lbst dolumn rfbd ibd b vbluf of SQL <dodf>NULL</dodf>.
     * Notf tibt you must first dbll onf of tif gfttfr mftiods
     * on b dolumn to try to rfbd its vbluf bnd tifn dbll
     * tif mftiod <dodf>wbsNull</dodf> to sff if tif vbluf rfbd wbs
     * SQL <dodf>NULL</dodf>.
     *
     * @rfturn <dodf>truf</dodf> if tif lbst dolumn vbluf rfbd wbs SQL
     *         <dodf>NULL</dodf> bnd <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    boolfbn wbsNull() tirows SQLExdfption;

    // Mftiods for bddfssing rfsults by dolumn indfx

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>String</dodf> in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    String gftString(int dolumnIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>boolfbn</dodf> in tif Jbvb progrbmming lbngubgf.
     *
     * <P>If tif dfsignbtfd dolumn ibs b dbtbtypf of CHAR or VARCHAR
     * bnd dontbins b "0" or ibs b dbtbtypf of BIT, TINYINT, SMALLINT, INTEGER or BIGINT
     * bnd dontbins  b 0, b vbluf of <dodf>fblsf</dodf> is rfturnfd.  If tif dfsignbtfd dolumn ibs b dbtbtypf
     * of CHAR or VARCHAR
     * bnd dontbins b "1" or ibs b dbtbtypf of BIT, TINYINT, SMALLINT, INTEGER or BIGINT
     * bnd dontbins  b 1, b vbluf of <dodf>truf</dodf> is rfturnfd.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>fblsf</dodf>
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    boolfbn gftBoolfbn(int dolumnIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>bytf</dodf> in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>0</dodf>
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    bytf gftBytf(int dolumnIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>siort</dodf> in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>0</dodf>
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    siort gftSiort(int dolumnIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * bn <dodf>int</dodf> in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>0</dodf>
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    int gftInt(int dolumnIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>long</dodf> in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>0</dodf>
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    long gftLong(int dolumnIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>flobt</dodf> in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>0</dodf>
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    flobt gftFlobt(int dolumnIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>doublf</dodf> in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>0</dodf>
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    doublf gftDoublf(int dolumnIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>jbvb.sql.BigDfdimbl</dodf> in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm sdblf tif numbfr of digits to tif rigit of tif dfdimbl point
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @dfprfdbtfd Usf {@dodf gftBigDfdimbl(int dolumnIndfx)}
     *             or {@dodf gftBigDfdimbl(String dolumnLbbfl)}
     */
    @Dfprfdbtfd
    BigDfdimbl gftBigDfdimbl(int dolumnIndfx, int sdblf) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>bytf</dodf> brrby in tif Jbvb progrbmming lbngubgf.
     * Tif bytfs rfprfsfnt tif rbw vblufs rfturnfd by tif drivfr.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    bytf[] gftBytfs(int dolumnIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>jbvb.sql.Dbtf</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    jbvb.sql.Dbtf gftDbtf(int dolumnIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>jbvb.sql.Timf</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    jbvb.sql.Timf gftTimf(int dolumnIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>jbvb.sql.Timfstbmp</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    jbvb.sql.Timfstbmp gftTimfstbmp(int dolumnIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b strfbm of ASCII dibrbdtfrs. Tif vbluf dbn tifn bf rfbd in diunks from tif
     * strfbm. Tiis mftiod is pbrtidulbrly
     * suitbblf for rftrifving lbrgf <dodf>LONGVARCHAR</dodf> vblufs.
     * Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from tif dbtbbbsf formbt into ASCII.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must bf
     * rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif nfxt
     * dbll to b gfttfr mftiod impliditly dlosfs tif strfbm.  Also, b
     * strfbm mby rfturn <dodf>0</dodf> wifn tif mftiod
     * <dodf>InputStrfbm.bvbilbblf</dodf>
     * is dbllfd wiftifr tifrf is dbtb bvbilbblf or not.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     * bs b strfbm of onf-bytf ASCII dibrbdtfrs;
     * if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    jbvb.io.InputStrfbm gftAsdiiStrfbm(int dolumnIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * bs b strfbm of two-bytf 3 dibrbdtfrs. Tif first bytf is
     * tif iigi bytf; tif sfdond bytf is tif low bytf.
     *
     * Tif vbluf dbn tifn bf rfbd in diunks from tif
     * strfbm. Tiis mftiod is pbrtidulbrly
     * suitbblf for rftrifving lbrgf <dodf>LONGVARCHAR</dodf>vblufs.  Tif
     * JDBC drivfr will do bny nfdfssbry donvfrsion from tif dbtbbbsf
     * formbt into Unidodf.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must bf
     * rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif nfxt
     * dbll to b gfttfr mftiod impliditly dlosfs tif strfbm.
     * Also, b strfbm mby rfturn <dodf>0</dodf> wifn tif mftiod
     * <dodf>InputStrfbm.bvbilbblf</dodf>
     * is dbllfd, wiftifr tifrf is dbtb bvbilbblf or not.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     *         bs b strfbm of two-bytf Unidodf dibrbdtfrs;
     *         if tif vbluf is SQL <dodf>NULL</dodf>, tif vbluf rfturnfd is
     *         <dodf>null</dodf>
     *
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @dfprfdbtfd usf <dodf>gftCibrbdtfrStrfbm</dodf> in plbdf of
     *              <dodf>gftUnidodfStrfbm</dodf>
     */
    @Dfprfdbtfd
    jbvb.io.InputStrfbm gftUnidodfStrfbm(int dolumnIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b  strfbm of
     * unintfrprftfd bytfs. Tif vbluf dbn tifn bf rfbd in diunks from tif
     * strfbm. Tiis mftiod is pbrtidulbrly
     * suitbblf for rftrifving lbrgf <dodf>LONGVARBINARY</dodf> vblufs.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must bf
     * rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif nfxt
     * dbll to b gfttfr mftiod impliditly dlosfs tif strfbm.  Also, b
     * strfbm mby rfturn <dodf>0</dodf> wifn tif mftiod
     * <dodf>InputStrfbm.bvbilbblf</dodf>
     * is dbllfd wiftifr tifrf is dbtb bvbilbblf or not.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     *         bs b strfbm of unintfrprftfd bytfs;
     *         if tif vbluf is SQL <dodf>NULL</dodf>, tif vbluf rfturnfd is
     *         <dodf>null</dodf>
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    jbvb.io.InputStrfbm gftBinbryStrfbm(int dolumnIndfx)
        tirows SQLExdfption;


    // Mftiods for bddfssing rfsults by dolumn lbbfl

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>String</dodf> in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    String gftString(String dolumnLbbfl) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>boolfbn</dodf> in tif Jbvb progrbmming lbngubgf.
     *
     * <P>If tif dfsignbtfd dolumn ibs b dbtbtypf of CHAR or VARCHAR
     * bnd dontbins b "0" or ibs b dbtbtypf of BIT, TINYINT, SMALLINT, INTEGER or BIGINT
     * bnd dontbins  b 0, b vbluf of <dodf>fblsf</dodf> is rfturnfd.  If tif dfsignbtfd dolumn ibs b dbtbtypf
     * of CHAR or VARCHAR
     * bnd dontbins b "1" or ibs b dbtbtypf of BIT, TINYINT, SMALLINT, INTEGER or BIGINT
     * bnd dontbins  b 1, b vbluf of <dodf>truf</dodf> is rfturnfd.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>fblsf</dodf>
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    boolfbn gftBoolfbn(String dolumnLbbfl) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>bytf</dodf> in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>0</dodf>
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    bytf gftBytf(String dolumnLbbfl) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>siort</dodf> in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>0</dodf>
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    siort gftSiort(String dolumnLbbfl) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * bn <dodf>int</dodf> in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>0</dodf>
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    int gftInt(String dolumnLbbfl) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>long</dodf> in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>0</dodf>
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    long gftLong(String dolumnLbbfl) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>flobt</dodf> in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>0</dodf>
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    flobt gftFlobt(String dolumnLbbfl) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>doublf</dodf> in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>0</dodf>
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    doublf gftDoublf(String dolumnLbbfl) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>jbvb.mbti.BigDfdimbl</dodf> in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm sdblf tif numbfr of digits to tif rigit of tif dfdimbl point
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @dfprfdbtfd Usf {@dodf gftBigDfdimbl(int dolumnIndfx)}
     *             or {@dodf gftBigDfdimbl(String dolumnLbbfl)}
     */
    @Dfprfdbtfd
    BigDfdimbl gftBigDfdimbl(String dolumnLbbfl, int sdblf) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>bytf</dodf> brrby in tif Jbvb progrbmming lbngubgf.
     * Tif bytfs rfprfsfnt tif rbw vblufs rfturnfd by tif drivfr.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    bytf[] gftBytfs(String dolumnLbbfl) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>jbvb.sql.Dbtf</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    jbvb.sql.Dbtf gftDbtf(String dolumnLbbfl) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>jbvb.sql.Timf</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn tif dolumn vbluf;
     * if tif vbluf is SQL <dodf>NULL</dodf>,
     * tif vbluf rfturnfd is <dodf>null</dodf>
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    jbvb.sql.Timf gftTimf(String dolumnLbbfl) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>jbvb.sql.Timfstbmp</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    jbvb.sql.Timfstbmp gftTimfstbmp(String dolumnLbbfl) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b strfbm of
     * ASCII dibrbdtfrs. Tif vbluf dbn tifn bf rfbd in diunks from tif
     * strfbm. Tiis mftiod is pbrtidulbrly
     * suitbblf for rftrifving lbrgf <dodf>LONGVARCHAR</dodf> vblufs.
     * Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from tif dbtbbbsf formbt into ASCII.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must bf
     * rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif nfxt
     * dbll to b gfttfr mftiod impliditly dlosfs tif strfbm. Also, b
     * strfbm mby rfturn <dodf>0</dodf> wifn tif mftiod <dodf>bvbilbblf</dodf>
     * is dbllfd wiftifr tifrf is dbtb bvbilbblf or not.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     * bs b strfbm of onf-bytf ASCII dibrbdtfrs.
     * If tif vbluf is SQL <dodf>NULL</dodf>,
     * tif vbluf rfturnfd is <dodf>null</dodf>.
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    jbvb.io.InputStrfbm gftAsdiiStrfbm(String dolumnLbbfl) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b strfbm of two-bytf
     * Unidodf dibrbdtfrs. Tif first bytf is tif iigi bytf; tif sfdond
     * bytf is tif low bytf.
     *
     * Tif vbluf dbn tifn bf rfbd in diunks from tif
     * strfbm. Tiis mftiod is pbrtidulbrly
     * suitbblf for rftrifving lbrgf <dodf>LONGVARCHAR</dodf> vblufs.
     * Tif JDBC tfdinology-fnbblfd drivfr will
     * do bny nfdfssbry donvfrsion from tif dbtbbbsf formbt into Unidodf.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must bf
     * rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif nfxt
     * dbll to b gfttfr mftiod impliditly dlosfs tif strfbm.
     * Also, b strfbm mby rfturn <dodf>0</dodf> wifn tif mftiod
     * <dodf>InputStrfbm.bvbilbblf</dodf> is dbllfd, wiftifr tifrf
     * is dbtb bvbilbblf or not.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     *         bs b strfbm of two-bytf Unidodf dibrbdtfrs.
     *         If tif vbluf is SQL <dodf>NULL</dodf>, tif vbluf rfturnfd
     *         is <dodf>null</dodf>.
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @dfprfdbtfd usf <dodf>gftCibrbdtfrStrfbm</dodf> instfbd
     */
    @Dfprfdbtfd
    jbvb.io.InputStrfbm gftUnidodfStrfbm(String dolumnLbbfl) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b strfbm of unintfrprftfd
     * <dodf>bytf</dodf>s.
     * Tif vbluf dbn tifn bf rfbd in diunks from tif
     * strfbm. Tiis mftiod is pbrtidulbrly
     * suitbblf for rftrifving lbrgf <dodf>LONGVARBINARY</dodf>
     * vblufs.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must bf
     * rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif nfxt
     * dbll to b gfttfr mftiod impliditly dlosfs tif strfbm. Also, b
     * strfbm mby rfturn <dodf>0</dodf> wifn tif mftiod <dodf>bvbilbblf</dodf>
     * is dbllfd wiftifr tifrf is dbtb bvbilbblf or not.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     * bs b strfbm of unintfrprftfd bytfs;
     * if tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    jbvb.io.InputStrfbm gftBinbryStrfbm(String dolumnLbbfl)
        tirows SQLExdfption;


    // Advbndfd ffbturfs:

    /**
     * Rftrifvfs tif first wbrning rfportfd by dblls on tiis
     * <dodf>RfsultSft</dodf> objfdt.
     * Subsfqufnt wbrnings on tiis <dodf>RfsultSft</dodf> objfdt
     * will bf dibinfd to tif <dodf>SQLWbrning</dodf> objfdt tibt
     * tiis mftiod rfturns.
     *
     * <P>Tif wbrning dibin is butombtidblly dlfbrfd fbdi timf b nfw
     * row is rfbd.  Tiis mftiod mby not bf dbllfd on b <dodf>RfsultSft</dodf>
     * objfdt tibt ibs bffn dlosfd; doing so will dbusf bn
     * <dodf>SQLExdfption</dodf> to bf tirown.
     * <P>
     * <B>Notf:</B> Tiis wbrning dibin only dovfrs wbrnings dbusfd
     * by <dodf>RfsultSft</dodf> mftiods.  Any wbrning dbusfd by
     * <dodf>Stbtfmfnt</dodf> mftiods
     * (sudi bs rfbding OUT pbrbmftfrs) will bf dibinfd on tif
     * <dodf>Stbtfmfnt</dodf> objfdt.
     *
     * @rfturn tif first <dodf>SQLWbrning</dodf> objfdt rfportfd or
     *         <dodf>null</dodf> if tifrf brf nonf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    SQLWbrning gftWbrnings() tirows SQLExdfption;

    /**
     * Clfbrs bll wbrnings rfportfd on tiis <dodf>RfsultSft</dodf> objfdt.
     * Aftfr tiis mftiod is dbllfd, tif mftiod <dodf>gftWbrnings</dodf>
     * rfturns <dodf>null</dodf> until b nfw wbrning is
     * rfportfd for tiis <dodf>RfsultSft</dodf> objfdt.
     *
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    void dlfbrWbrnings() tirows SQLExdfption;

    /**
     * Rftrifvfs tif nbmf of tif SQL dursor usfd by tiis <dodf>RfsultSft</dodf>
     * objfdt.
     *
     * <P>In SQL, b rfsult tbblf is rftrifvfd tirougi b dursor tibt is
     * nbmfd. Tif durrfnt row of b rfsult sft dbn bf updbtfd or dflftfd
     * using b positionfd updbtf/dflftf stbtfmfnt tibt rfffrfndfs tif
     * dursor nbmf. To insurf tibt tif dursor ibs tif propfr isolbtion
     * lfvfl to support updbtf, tif dursor's <dodf>SELECT</dodf> stbtfmfnt
     * siould bf of tif form <dodf>SELECT FOR UPDATE</dodf>. If
     * <dodf>FOR UPDATE</dodf> is omittfd, tif positionfd updbtfs mby fbil.
     *
     * <P>Tif JDBC API supports tiis SQL ffbturf by providing tif nbmf of tif
     * SQL dursor usfd by b <dodf>RfsultSft</dodf> objfdt.
     * Tif durrfnt row of b <dodf>RfsultSft</dodf> objfdt
     * is blso tif durrfnt row of tiis SQL dursor.
     *
     * @rfturn tif SQL nbmf for tiis <dodf>RfsultSft</dodf> objfdt's dursor
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     */
    String gftCursorNbmf() tirows SQLExdfption;

    /**
     * Rftrifvfs tif  numbfr, typfs bnd propfrtifs of
     * tiis <dodf>RfsultSft</dodf> objfdt's dolumns.
     *
     * @rfturn tif dfsdription of tiis <dodf>RfsultSft</dodf> objfdt's dolumns
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    RfsultSftMftbDbtb gftMftbDbtb() tirows SQLExdfption;

    /**
     * <p>Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * bn <dodf>Objfdt</dodf> in tif Jbvb progrbmming lbngubgf.
     *
     * <p>Tiis mftiod will rfturn tif vbluf of tif givfn dolumn bs b
     * Jbvb objfdt.  Tif typf of tif Jbvb objfdt will bf tif dffbult
     * Jbvb objfdt typf dorrfsponding to tif dolumn's SQL typf,
     * following tif mbpping for built-in typfs spfdififd in tif JDBC
     * spfdifidbtion. If tif vbluf is bn SQL <dodf>NULL</dodf>,
     * tif drivfr rfturns b Jbvb <dodf>null</dodf>.
     *
     * <p>Tiis mftiod mby blso bf usfd to rfbd dbtbbbsf-spfdifid
     * bbstrbdt dbtb typfs.
     *
     * In tif JDBC 2.0 API, tif bfibvior of mftiod
     * <dodf>gftObjfdt</dodf> is fxtfndfd to mbtfriblizf
     * dbtb of SQL usfr-dffinfd typfs.
     * <p>
     * If <dodf>Connfdtion.gftTypfMbp</dodf> dofs not tirow b
     * <dodf>SQLFfbturfNotSupportfdExdfption</dodf>,
     * tifn wifn b dolumn dontbins b strudturfd or distindt vbluf,
     * tif bfibvior of tiis mftiod is bs
     * if it wfrf b dbll to: <dodf>gftObjfdt(dolumnIndfx,
     * tiis.gftStbtfmfnt().gftConnfdtion().gftTypfMbp())</dodf>.
     *
     * If <dodf>Connfdtion.gftTypfMbp</dodf> dofs tirow b
     * <dodf>SQLFfbturfNotSupportfdExdfption</dodf>,
     * tifn strudturfd vblufs brf not supportfd, bnd distindt vblufs
     * brf mbppfd to tif dffbult Jbvb dlbss bs dftfrminfd by tif
     * undfrlying SQL typf of tif DISTINCT typf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn b <dodf>jbvb.lbng.Objfdt</dodf> iolding tif dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    Objfdt gftObjfdt(int dolumnIndfx) tirows SQLExdfption;

    /**
     * <p>Gfts tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * bn <dodf>Objfdt</dodf> in tif Jbvb progrbmming lbngubgf.
     *
     * <p>Tiis mftiod will rfturn tif vbluf of tif givfn dolumn bs b
     * Jbvb objfdt.  Tif typf of tif Jbvb objfdt will bf tif dffbult
     * Jbvb objfdt typf dorrfsponding to tif dolumn's SQL typf,
     * following tif mbpping for built-in typfs spfdififd in tif JDBC
     * spfdifidbtion. If tif vbluf is bn SQL <dodf>NULL</dodf>,
     * tif drivfr rfturns b Jbvb <dodf>null</dodf>.
     * <P>
     * Tiis mftiod mby blso bf usfd to rfbd dbtbbbsf-spfdifid
     * bbstrbdt dbtb typfs.
     * <P>
     * In tif JDBC 2.0 API, tif bfibvior of tif mftiod
     * <dodf>gftObjfdt</dodf> is fxtfndfd to mbtfriblizf
     * dbtb of SQL usfr-dffinfd typfs.  Wifn b dolumn dontbins
     * b strudturfd or distindt vbluf, tif bfibvior of tiis mftiod is bs
     * if it wfrf b dbll to: <dodf>gftObjfdt(dolumnIndfx,
     * tiis.gftStbtfmfnt().gftConnfdtion().gftTypfMbp())</dodf>.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn b <dodf>jbvb.lbng.Objfdt</dodf> iolding tif dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     */
    Objfdt gftObjfdt(String dolumnLbbfl) tirows SQLExdfption;

    //----------------------------------------------------------------

    /**
     * Mbps tif givfn <dodf>RfsultSft</dodf> dolumn lbbfl to its
     * <dodf>RfsultSft</dodf> dolumn indfx.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn tif dolumn indfx of tif givfn dolumn nbmf
     * @fxdfption SQLExdfption if tif <dodf>RfsultSft</dodf> objfdt
     * dofs not dontbin b dolumn lbbflfd <dodf>dolumnLbbfl</dodf>, b dbtbbbsf bddfss frror oddurs
     *  or tiis mftiod is dbllfd on b dlosfd rfsult sft
     */
    int findColumn(String dolumnLbbfl) tirows SQLExdfption;


    //--------------------------JDBC 2.0-----------------------------------

    //---------------------------------------------------------------------
    // Gfttfrs bnd Sfttfrs
    //---------------------------------------------------------------------

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt.
     * @rfturn b <dodf>jbvb.io.Rfbdfr</dodf> objfdt tibt dontbins tif dolumn
     * vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif vbluf rfturnfd is
     * <dodf>null</dodf> in tif Jbvb progrbmming lbngubgf.
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     * @sindf 1.2
     */
    jbvb.io.Rfbdfr gftCibrbdtfrStrfbm(int dolumnIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn b <dodf>jbvb.io.Rfbdfr</dodf> objfdt tibt dontbins tif dolumn
     * vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif vbluf rfturnfd is
     * <dodf>null</dodf> in tif Jbvb progrbmming lbngubgf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     * @sindf 1.2
     */
    jbvb.io.Rfbdfr gftCibrbdtfrStrfbm(String dolumnLbbfl) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b
     * <dodf>jbvb.mbti.BigDfdimbl</dodf> witi full prfdision.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn tif dolumn vbluf (full prfdision);
     * if tif vbluf is SQL <dodf>NULL</dodf>, tif vbluf rfturnfd is
     * <dodf>null</dodf> in tif Jbvb progrbmming lbngubgf.
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     * @sindf 1.2
     */
    BigDfdimbl gftBigDfdimbl(int dolumnIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b
     * <dodf>jbvb.mbti.BigDfdimbl</dodf> witi full prfdision.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn tif dolumn vbluf (full prfdision);
     * if tif vbluf is SQL <dodf>NULL</dodf>, tif vbluf rfturnfd is
     * <dodf>null</dodf> in tif Jbvb progrbmming lbngubgf.
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     * @sindf 1.2
     *
     */
    BigDfdimbl gftBigDfdimbl(String dolumnLbbfl) tirows SQLExdfption;

    //---------------------------------------------------------------------
    // Trbvfrsbl/Positioning
    //---------------------------------------------------------------------

    /**
     * Rftrifvfs wiftifr tif dursor is bfforf tif first row in
     * tiis <dodf>RfsultSft</dodf> objfdt.
     * <p>
     * <strong>Notf:</strong>Support for tif <dodf>isBfforfFirst</dodf> mftiod
     * is optionbl for <dodf>RfsultSft</dodf>s witi b rfsult
     * sft typf of <dodf>TYPE_FORWARD_ONLY</dodf>
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is bfforf tif first row;
     * <dodf>fblsf</dodf> if tif dursor is bt bny otifr position or tif
     * rfsult sft dontbins no rows
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    boolfbn isBfforfFirst() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tif dursor is bftfr tif lbst row in
     * tiis <dodf>RfsultSft</dodf> objfdt.
     * <p>
     * <strong>Notf:</strong>Support for tif <dodf>isAftfrLbst</dodf> mftiod
     * is optionbl for <dodf>RfsultSft</dodf>s witi b rfsult
     * sft typf of <dodf>TYPE_FORWARD_ONLY</dodf>
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is bftfr tif lbst row;
     * <dodf>fblsf</dodf> if tif dursor is bt bny otifr position or tif
     * rfsult sft dontbins no rows
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    boolfbn isAftfrLbst() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tif dursor is on tif first row of
     * tiis <dodf>RfsultSft</dodf> objfdt.
     * <p>
     * <strong>Notf:</strong>Support for tif <dodf>isFirst</dodf> mftiod
     * is optionbl for <dodf>RfsultSft</dodf>s witi b rfsult
     * sft typf of <dodf>TYPE_FORWARD_ONLY</dodf>
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on tif first row;
     * <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    boolfbn isFirst() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tif dursor is on tif lbst row of
     * tiis <dodf>RfsultSft</dodf> objfdt.
     *  <strong>Notf:</strong> Cblling tif mftiod <dodf>isLbst</dodf> mby bf fxpfnsivf
     * bfdbusf tif JDBC drivfr
     * migit nffd to fftdi bifbd onf row in ordfr to dftfrminf
     * wiftifr tif durrfnt row is tif lbst row in tif rfsult sft.
     * <p>
     * <strong>Notf:</strong> Support for tif <dodf>isLbst</dodf> mftiod
     * is optionbl for <dodf>RfsultSft</dodf>s witi b rfsult
     * sft typf of <dodf>TYPE_FORWARD_ONLY</dodf>
     * @rfturn <dodf>truf</dodf> if tif dursor is on tif lbst row;
     * <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or tiis mftiod is
     *            dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    boolfbn isLbst() tirows SQLExdfption;

    /**
     * Movfs tif dursor to tif front of
     * tiis <dodf>RfsultSft</dodf> objfdt, just bfforf tif
     * first row. Tiis mftiod ibs no ffffdt if tif rfsult sft dontbins no rows.
     *
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror
     * oddurs; tiis mftiod is dbllfd on b dlosfd rfsult sft or tif
     * rfsult sft typf is <dodf>TYPE_FORWARD_ONLY</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void bfforfFirst() tirows SQLExdfption;

    /**
     * Movfs tif dursor to tif fnd of
     * tiis <dodf>RfsultSft</dodf> objfdt, just bftfr tif
     * lbst row. Tiis mftiod ibs no ffffdt if tif rfsult sft dontbins no rows.
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror
     * oddurs; tiis mftiod is dbllfd on b dlosfd rfsult sft
     * or tif rfsult sft typf is <dodf>TYPE_FORWARD_ONLY</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void bftfrLbst() tirows SQLExdfption;

    /**
     * Movfs tif dursor to tif first row in
     * tiis <dodf>RfsultSft</dodf> objfdt.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on b vblid row;
     * <dodf>fblsf</dodf> if tifrf brf no rows in tif rfsult sft
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror
     * oddurs; tiis mftiod is dbllfd on b dlosfd rfsult sft
     * or tif rfsult sft typf is <dodf>TYPE_FORWARD_ONLY</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    boolfbn first() tirows SQLExdfption;

    /**
     * Movfs tif dursor to tif lbst row in
     * tiis <dodf>RfsultSft</dodf> objfdt.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on b vblid row;
     * <dodf>fblsf</dodf> if tifrf brf no rows in tif rfsult sft
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror
     * oddurs; tiis mftiod is dbllfd on b dlosfd rfsult sft
     * or tif rfsult sft typf is <dodf>TYPE_FORWARD_ONLY</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    boolfbn lbst() tirows SQLExdfption;

    /**
     * Rftrifvfs tif durrfnt row numbfr.  Tif first row is numbfr 1, tif
     * sfdond numbfr 2, bnd so on.
     * <p>
     * <strong>Notf:</strong>Support for tif <dodf>gftRow</dodf> mftiod
     * is optionbl for <dodf>RfsultSft</dodf>s witi b rfsult
     * sft typf of <dodf>TYPE_FORWARD_ONLY</dodf>
     *
     * @rfturn tif durrfnt row numbfr; <dodf>0</dodf> if tifrf is no durrfnt row
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    int gftRow() tirows SQLExdfption;

    /**
     * Movfs tif dursor to tif givfn row numbfr in
     * tiis <dodf>RfsultSft</dodf> objfdt.
     *
     * <p>If tif row numbfr is positivf, tif dursor movfs to
     * tif givfn row numbfr witi rfspfdt to tif
     * bfginning of tif rfsult sft.  Tif first row is row 1, tif sfdond
     * is row 2, bnd so on.
     *
     * <p>If tif givfn row numbfr is nfgbtivf, tif dursor movfs to
     * bn bbsolutf row position witi rfspfdt to
     * tif fnd of tif rfsult sft.  For fxbmplf, dblling tif mftiod
     * <dodf>bbsolutf(-1)</dodf> positions tif
     * dursor on tif lbst row; dblling tif mftiod <dodf>bbsolutf(-2)</dodf>
     * movfs tif dursor to tif nfxt-to-lbst row, bnd so on.
     *
     * <p>If tif row numbfr spfdififd is zfro, tif dursor is movfd to
     * bfforf tif first row.
     *
     * <p>An bttfmpt to position tif dursor bfyond tif first/lbst row in
     * tif rfsult sft lfbvfs tif dursor bfforf tif first row or bftfr
     * tif lbst row.
     *
     * <p><B>Notf:</B> Cblling <dodf>bbsolutf(1)</dodf> is tif sbmf
     * bs dblling <dodf>first()</dodf>. Cblling <dodf>bbsolutf(-1)</dodf>
     * is tif sbmf bs dblling <dodf>lbst()</dodf>.
     *
     * @pbrbm row tif numbfr of tif row to wiidi tif dursor siould movf.
     *        A vbluf of zfro indidbtfs tibt tif dursor will bf positionfd
     *        bfforf tif first row; b positivf numbfr indidbtfs tif row numbfr
     *        dounting from tif bfginning of tif rfsult sft; b nfgbtivf numbfr
     *        indidbtfs tif row numbfr dounting from tif fnd of tif rfsult sft
     * @rfturn <dodf>truf</dodf> if tif dursor is movfd to b position in tiis
     * <dodf>RfsultSft</dodf> objfdt;
     * <dodf>fblsf</dodf> if tif dursor is bfforf tif first row or bftfr tif
     * lbst row
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror
     * oddurs; tiis mftiod is dbllfd on b dlosfd rfsult sft
     * or tif rfsult sft typf is <dodf>TYPE_FORWARD_ONLY</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    boolfbn bbsolutf( int row ) tirows SQLExdfption;

    /**
     * Movfs tif dursor b rflbtivf numbfr of rows, fitifr positivf or nfgbtivf.
     * Attfmpting to movf bfyond tif first/lbst row in tif
     * rfsult sft positions tif dursor bfforf/bftfr tif
     * tif first/lbst row. Cblling <dodf>rflbtivf(0)</dodf> is vblid, but dofs
     * not dibngf tif dursor position.
     *
     * <p>Notf: Cblling tif mftiod <dodf>rflbtivf(1)</dodf>
     * is idfntidbl to dblling tif mftiod <dodf>nfxt()</dodf> bnd
     * dblling tif mftiod <dodf>rflbtivf(-1)</dodf> is idfntidbl
     * to dblling tif mftiod <dodf>prfvious()</dodf>.
     *
     * @pbrbm rows bn <dodf>int</dodf> spfdifying tif numbfr of rows to
     *        movf from tif durrfnt row; b positivf numbfr movfs tif dursor
     *        forwbrd; b nfgbtivf numbfr movfs tif dursor bbdkwbrd
     * @rfturn <dodf>truf</dodf> if tif dursor is on b row;
     *         <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs;  tiis mftiod
     * is dbllfd on b dlosfd rfsult sft or tif rfsult sft typf is
     *            <dodf>TYPE_FORWARD_ONLY</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    boolfbn rflbtivf( int rows ) tirows SQLExdfption;

    /**
     * Movfs tif dursor to tif prfvious row in tiis
     * <dodf>RfsultSft</dodf> objfdt.
     *<p>
     * Wifn b dbll to tif <dodf>prfvious</dodf> mftiod rfturns <dodf>fblsf</dodf>,
     * tif dursor is positionfd bfforf tif first row.  Any invodbtion of b
     * <dodf>RfsultSft</dodf> mftiod wiidi rfquirfs b durrfnt row will rfsult in b
     * <dodf>SQLExdfption</dodf> bfing tirown.
     *<p>
     * If bn input strfbm is opfn for tif durrfnt row, b dbll to tif mftiod
     * <dodf>prfvious</dodf> will impliditly dlosf it.  A <dodf>RfsultSft</dodf>
     *  objfdt's wbrning dibngf is dlfbrfd wifn b nfw row is rfbd.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is now positionfd on b vblid row;
     * <dodf>fblsf</dodf> if tif dursor is positionfd bfforf tif first row
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror
     * oddurs; tiis mftiod is dbllfd on b dlosfd rfsult sft
     * or tif rfsult sft typf is <dodf>TYPE_FORWARD_ONLY</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    boolfbn prfvious() tirows SQLExdfption;

    //---------------------------------------------------------------------
    // Propfrtifs
    //---------------------------------------------------------------------

    /**
     * Tif donstbnt indidbting tibt tif rows in b rfsult sft will bf
     * prodfssfd in b forwbrd dirfdtion; first-to-lbst.
     * Tiis donstbnt is usfd by tif mftiod <dodf>sftFftdiDirfdtion</dodf>
     * bs b iint to tif drivfr, wiidi tif drivfr mby ignorf.
     * @sindf 1.2
     */
    int FETCH_FORWARD = 1000;

    /**
     * Tif donstbnt indidbting tibt tif rows in b rfsult sft will bf
     * prodfssfd in b rfvfrsf dirfdtion; lbst-to-first.
     * Tiis donstbnt is usfd by tif mftiod <dodf>sftFftdiDirfdtion</dodf>
     * bs b iint to tif drivfr, wiidi tif drivfr mby ignorf.
     * @sindf 1.2
     */
    int FETCH_REVERSE = 1001;

    /**
     * Tif donstbnt indidbting tibt tif ordfr in wiidi rows in b
     * rfsult sft will bf prodfssfd is unknown.
     * Tiis donstbnt is usfd by tif mftiod <dodf>sftFftdiDirfdtion</dodf>
     * bs b iint to tif drivfr, wiidi tif drivfr mby ignorf.
     */
    int FETCH_UNKNOWN = 1002;

    /**
     * Givfs b iint bs to tif dirfdtion in wiidi tif rows in tiis
     * <dodf>RfsultSft</dodf> objfdt will bf prodfssfd.
     * Tif initibl vbluf is dftfrminfd by tif
     * <dodf>Stbtfmfnt</dodf> objfdt
     * tibt produdfd tiis <dodf>RfsultSft</dodf> objfdt.
     * Tif fftdi dirfdtion mby bf dibngfd bt bny timf.
     *
     * @pbrbm dirfdtion bn <dodf>int</dodf> spfdifying tif suggfstfd
     *        fftdi dirfdtion; onf of <dodf>RfsultSft.FETCH_FORWARD</dodf>,
     *        <dodf>RfsultSft.FETCH_REVERSE</dodf>, or
     *        <dodf>RfsultSft.FETCH_UNKNOWN</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs; tiis
     * mftiod is dbllfd on b dlosfd rfsult sft or
     * tif rfsult sft typf is <dodf>TYPE_FORWARD_ONLY</dodf> bnd tif fftdi
     * dirfdtion is not <dodf>FETCH_FORWARD</dodf>
     * @sindf 1.2
     * @sff Stbtfmfnt#sftFftdiDirfdtion
     * @sff #gftFftdiDirfdtion
     */
    void sftFftdiDirfdtion(int dirfdtion) tirows SQLExdfption;

    /**
     * Rftrifvfs tif fftdi dirfdtion for tiis
     * <dodf>RfsultSft</dodf> objfdt.
     *
     * @rfturn tif durrfnt fftdi dirfdtion for tiis <dodf>RfsultSft</dodf> objfdt
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @sindf 1.2
     * @sff #sftFftdiDirfdtion
     */
    int gftFftdiDirfdtion() tirows SQLExdfption;

    /**
     * Givfs tif JDBC drivfr b iint bs to tif numbfr of rows tibt siould
     * bf fftdifd from tif dbtbbbsf wifn morf rows brf nffdfd for tiis
     * <dodf>RfsultSft</dodf> objfdt.
     * If tif fftdi sizf spfdififd is zfro, tif JDBC drivfr
     * ignorfs tif vbluf bnd is frff to mbkf its own bfst gufss bs to wibt
     * tif fftdi sizf siould bf.  Tif dffbult vbluf is sft by tif
     * <dodf>Stbtfmfnt</dodf> objfdt
     * tibt drfbtfd tif rfsult sft.  Tif fftdi sizf mby bf dibngfd bt bny timf.
     *
     * @pbrbm rows tif numbfr of rows to fftdi
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs; tiis mftiod
     * is dbllfd on b dlosfd rfsult sft or tif
     * dondition {@dodf rows >= 0} is not sbtisfifd
     * @sindf 1.2
     * @sff #gftFftdiSizf
     */
    void sftFftdiSizf(int rows) tirows SQLExdfption;

    /**
     * Rftrifvfs tif fftdi sizf for tiis
     * <dodf>RfsultSft</dodf> objfdt.
     *
     * @rfturn tif durrfnt fftdi sizf for tiis <dodf>RfsultSft</dodf> objfdt
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @sindf 1.2
     * @sff #sftFftdiSizf
     */
    int gftFftdiSizf() tirows SQLExdfption;

    /**
     * Tif donstbnt indidbting tif typf for b <dodf>RfsultSft</dodf> objfdt
     * wiosf dursor mby movf only forwbrd.
     * @sindf 1.2
     */
    int TYPE_FORWARD_ONLY = 1003;

    /**
     * Tif donstbnt indidbting tif typf for b <dodf>RfsultSft</dodf> objfdt
     * tibt is sdrollbblf but gfnfrblly not sfnsitivf to dibngfs to tif dbtb
     * tibt undfrlifs tif <dodf>RfsultSft</dodf>.
     * @sindf 1.2
     */
    int TYPE_SCROLL_INSENSITIVE = 1004;

    /**
     * Tif donstbnt indidbting tif typf for b <dodf>RfsultSft</dodf> objfdt
     * tibt is sdrollbblf bnd gfnfrblly sfnsitivf to dibngfs to tif dbtb
     * tibt undfrlifs tif <dodf>RfsultSft</dodf>.
     * @sindf 1.2
     */
    int TYPE_SCROLL_SENSITIVE = 1005;

    /**
     * Rftrifvfs tif typf of tiis <dodf>RfsultSft</dodf> objfdt.
     * Tif typf is dftfrminfd by tif <dodf>Stbtfmfnt</dodf> objfdt
     * tibt drfbtfd tif rfsult sft.
     *
     * @rfturn <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>,
     *         <dodf>RfsultSft.TYPE_SCROLL_INSENSITIVE</dodf>,
     *         or <dodf>RfsultSft.TYPE_SCROLL_SENSITIVE</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @sindf 1.2
     */
    int gftTypf() tirows SQLExdfption;

    /**
     * Tif donstbnt indidbting tif dondurrfndy modf for b
     * <dodf>RfsultSft</dodf> objfdt tibt mby NOT bf updbtfd.
     * @sindf 1.2
     */
    int CONCUR_READ_ONLY = 1007;

    /**
     * Tif donstbnt indidbting tif dondurrfndy modf for b
     * <dodf>RfsultSft</dodf> objfdt tibt mby bf updbtfd.
     * @sindf 1.2
     */
    int CONCUR_UPDATABLE = 1008;

    /**
     * Rftrifvfs tif dondurrfndy modf of tiis <dodf>RfsultSft</dodf> objfdt.
     * Tif dondurrfndy usfd is dftfrminfd by tif
     * <dodf>Stbtfmfnt</dodf> objfdt tibt drfbtfd tif rfsult sft.
     *
     * @rfturn tif dondurrfndy typf, fitifr
     *         <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     *         or <dodf>RfsultSft.CONCUR_UPDATABLE</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @sindf 1.2
     */
    int gftCondurrfndy() tirows SQLExdfption;

    //---------------------------------------------------------------------
    // Updbtfs
    //---------------------------------------------------------------------

    /**
     * Rftrifvfs wiftifr tif durrfnt row ibs bffn updbtfd.  Tif vbluf rfturnfd
     * dfpfnds on wiftifr or not tif rfsult sft dbn dftfdt updbtfs.
     * <p>
     * <strong>Notf:</strong> Support for tif <dodf>rowUpdbtfd</dodf> mftiod is optionbl witi b rfsult sft
     * dondurrfndy of <dodf>CONCUR_READ_ONLY</dodf>
     * @rfturn <dodf>truf</dodf> if tif durrfnt row is dftfdtfd to
     * ibvf bffn visibly updbtfd by tif ownfr or bnotifr; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff DbtbbbsfMftbDbtb#updbtfsArfDftfdtfd
     * @sindf 1.2
     */
    boolfbn rowUpdbtfd() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tif durrfnt row ibs ibd bn insfrtion.
     * Tif vbluf rfturnfd dfpfnds on wiftifr or not tiis
     * <dodf>RfsultSft</dodf> objfdt dbn dftfdt visiblf insfrts.
     * <p>
     * <strong>Notf:</strong> Support for tif <dodf>rowInsfrtfd</dodf> mftiod is optionbl witi b rfsult sft
     * dondurrfndy of <dodf>CONCUR_READ_ONLY</dodf>
     * @rfturn <dodf>truf</dodf> if tif durrfnt row is dftfdtfd to
     * ibvf bffn insfrtfd; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     *
     * @sff DbtbbbsfMftbDbtb#insfrtsArfDftfdtfd
     * @sindf 1.2
     */
    boolfbn rowInsfrtfd() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr b row ibs bffn dflftfd.  A dflftfd row mby lfbvf
     * b visiblf "iolf" in b rfsult sft.  Tiis mftiod dbn bf usfd to
     * dftfdt iolfs in b rfsult sft.  Tif vbluf rfturnfd dfpfnds on wiftifr
     * or not tiis <dodf>RfsultSft</dodf> objfdt dbn dftfdt dflftions.
     * <p>
     * <strong>Notf:</strong> Support for tif <dodf>rowDflftfd</dodf> mftiod is optionbl witi b rfsult sft
     * dondurrfndy of <dodf>CONCUR_READ_ONLY</dodf>
     * @rfturn <dodf>truf</dodf> if tif durrfnt row is dftfdtfd to
     * ibvf bffn dflftfd by tif ownfr or bnotifr; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     *
     * @sff DbtbbbsfMftbDbtb#dflftfsArfDftfdtfd
     * @sindf 1.2
     */
    boolfbn rowDflftfd() tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>null</dodf> vbluf.
     *
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf>
     * or <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfNull(int dolumnIndfx) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>boolfbn</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfBoolfbn(int dolumnIndfx, boolfbn x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>bytf</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfBytf(int dolumnIndfx, bytf x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>siort</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfSiort(int dolumnIndfx, siort x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi bn <dodf>int</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfInt(int dolumnIndfx, int x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>long</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfLong(int dolumnIndfx, long x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>flobt</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfFlobt(int dolumnIndfx, flobt x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>doublf</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfDoublf(int dolumnIndfx, doublf x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.mbti.BigDfdimbl</dodf>
     * vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfBigDfdimbl(int dolumnIndfx, BigDfdimbl x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>String</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfString(int dolumnIndfx, String x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>bytf</dodf> brrby vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfBytfs(int dolumnIndfx, bytf x[]) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.Dbtf</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfDbtf(int dolumnIndfx, jbvb.sql.Dbtf x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.Timf</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfTimf(int dolumnIndfx, jbvb.sql.Timf x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.Timfstbmp</dodf>
     * vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfTimfstbmp(int dolumnIndfx, jbvb.sql.Timfstbmp x)
      tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi bn bsdii strfbm vbluf, wiidi will ibvf
     * tif spfdififd numbfr of bytfs.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm lfngti tif lfngti of tif strfbm
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfAsdiiStrfbm(int dolumnIndfx,
                           jbvb.io.InputStrfbm x,
                           int lfngti) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b binbry strfbm vbluf, wiidi will ibvf
     * tif spfdififd numbfr of bytfs.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm lfngti tif lfngti of tif strfbm
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfBinbryStrfbm(int dolumnIndfx,
                            jbvb.io.InputStrfbm x,
                            int lfngti) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b dibrbdtfr strfbm vbluf, wiidi will ibvf
     * tif spfdififd numbfr of bytfs.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm lfngti tif lfngti of tif strfbm
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfCibrbdtfrStrfbm(int dolumnIndfx,
                             jbvb.io.Rfbdfr x,
                             int lfngti) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi bn <dodf>Objfdt</dodf> vbluf.
     *
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *<p>
     * If tif sfdond brgumfnt is bn <dodf>InputStrfbm</dodf> tifn tif strfbm must dontbin
     * tif numbfr of bytfs spfdififd by sdblfOrLfngti.  If tif sfdond brgumfnt is b
     * <dodf>Rfbdfr</dodf> tifn tif rfbdfr must dontbin tif numbfr of dibrbdtfrs spfdififd
     * by sdblfOrLfngti. If tifsf donditions brf not truf tif drivfr will gfnfrbtf b
     * <dodf>SQLExdfption</dodf> wifn tif stbtfmfnt is fxfdutfd.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm sdblfOrLfngti for bn objfdt of <dodf>jbvb.mbti.BigDfdimbl</dodf> ,
     *          tiis is tif numbfr of digits bftfr tif dfdimbl point. For
     *          Jbvb Objfdt typfs <dodf>InputStrfbm</dodf> bnd <dodf>Rfbdfr</dodf>,
     *          tiis is tif lfngti
     *          of tif dbtb in tif strfbm or rfbdfr.  For bll otifr typfs,
     *          tiis vbluf will bf ignorfd.
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfObjfdt(int dolumnIndfx, Objfdt x, int sdblfOrLfngti)
      tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi bn <dodf>Objfdt</dodf> vbluf.
     *
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfObjfdt(int dolumnIndfx, Objfdt x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>null</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfNull(String dolumnLbbfl) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>boolfbn</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfBoolfbn(String dolumnLbbfl, boolfbn x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>bytf</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfBytf(String dolumnLbbfl, bytf x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>siort</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfSiort(String dolumnLbbfl, siort x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi bn <dodf>int</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfInt(String dolumnLbbfl, int x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>long</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfLong(String dolumnLbbfl, long x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>flobt </dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfFlobt(String dolumnLbbfl, flobt x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>doublf</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfDoublf(String dolumnLbbfl, doublf x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.BigDfdimbl</dodf>
     * vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfBigDfdimbl(String dolumnLbbfl, BigDfdimbl x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>String</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfString(String dolumnLbbfl, String x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b bytf brrby vbluf.
     *
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf>
     * or <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfBytfs(String dolumnLbbfl, bytf x[]) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.Dbtf</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfDbtf(String dolumnLbbfl, jbvb.sql.Dbtf x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.Timf</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfTimf(String dolumnLbbfl, jbvb.sql.Timf x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.Timfstbmp</dodf>
     * vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfTimfstbmp(String dolumnLbbfl, jbvb.sql.Timfstbmp x)
      tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi bn bsdii strfbm vbluf, wiidi will ibvf
     * tif spfdififd numbfr of bytfs.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm lfngti tif lfngti of tif strfbm
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfAsdiiStrfbm(String dolumnLbbfl,
                           jbvb.io.InputStrfbm x,
                           int lfngti) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b binbry strfbm vbluf, wiidi will ibvf
     * tif spfdififd numbfr of bytfs.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm lfngti tif lfngti of tif strfbm
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfBinbryStrfbm(String dolumnLbbfl,
                            jbvb.io.InputStrfbm x,
                            int lfngti) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b dibrbdtfr strfbm vbluf, wiidi will ibvf
     * tif spfdififd numbfr of bytfs.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm rfbdfr tif <dodf>jbvb.io.Rfbdfr</dodf> objfdt dontbining
     *        tif nfw dolumn vbluf
     * @pbrbm lfngti tif lfngti of tif strfbm
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfCibrbdtfrStrfbm(String dolumnLbbfl,
                             jbvb.io.Rfbdfr rfbdfr,
                             int lfngti) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi bn <dodf>Objfdt</dodf> vbluf.
     *
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *<p>
     * If tif sfdond brgumfnt is bn <dodf>InputStrfbm</dodf> tifn tif strfbm must dontbin
     * tif numbfr of bytfs spfdififd by sdblfOrLfngti.  If tif sfdond brgumfnt is b
     * <dodf>Rfbdfr</dodf> tifn tif rfbdfr must dontbin tif numbfr of dibrbdtfrs spfdififd
     * by sdblfOrLfngti. If tifsf donditions brf not truf tif drivfr will gfnfrbtf b
     * <dodf>SQLExdfption</dodf> wifn tif stbtfmfnt is fxfdutfd.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm sdblfOrLfngti for bn objfdt of <dodf>jbvb.mbti.BigDfdimbl</dodf> ,
     *          tiis is tif numbfr of digits bftfr tif dfdimbl point. For
     *          Jbvb Objfdt typfs <dodf>InputStrfbm</dodf> bnd <dodf>Rfbdfr</dodf>,
     *          tiis is tif lfngti
     *          of tif dbtb in tif strfbm or rfbdfr.  For bll otifr typfs,
     *          tiis vbluf will bf ignorfd.
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfObjfdt(String dolumnLbbfl, Objfdt x, int sdblfOrLfngti)
      tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi bn <dodf>Objfdt</dodf> vbluf.
     *
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfObjfdt(String dolumnLbbfl, Objfdt x) tirows SQLExdfption;

    /**
     * Insfrts tif dontfnts of tif insfrt row into tiis
     * <dodf>RfsultSft</dodf> objfdt bnd into tif dbtbbbsf.
     * Tif dursor must bf on tif insfrt row wifn tiis mftiod is dbllfd.
     *
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>,
     * tiis mftiod is dbllfd on b dlosfd rfsult sft,
     * if tiis mftiod is dbllfd wifn tif dursor is not on tif insfrt row,
     * or if not bll of non-nullbblf dolumns in
     * tif insfrt row ibvf bffn givfn b non-null vbluf
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void insfrtRow() tirows SQLExdfption;

    /**
     * Updbtfs tif undfrlying dbtbbbsf witi tif nfw dontfnts of tif
     * durrfnt row of tiis <dodf>RfsultSft</dodf> objfdt.
     * Tiis mftiod dbnnot bf dbllfd wifn tif dursor is on tif insfrt row.
     *
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>;
     *  tiis mftiod is dbllfd on b dlosfd rfsult sft or
     * if tiis mftiod is dbllfd wifn tif dursor is on tif insfrt row
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void updbtfRow() tirows SQLExdfption;

    /**
     * Dflftfs tif durrfnt row from tiis <dodf>RfsultSft</dodf> objfdt
     * bnd from tif undfrlying dbtbbbsf.  Tiis mftiod dbnnot bf dbllfd wifn
     * tif dursor is on tif insfrt row.
     *
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>;
     * tiis mftiod is dbllfd on b dlosfd rfsult sft
     * or if tiis mftiod is dbllfd wifn tif dursor is on tif insfrt row
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void dflftfRow() tirows SQLExdfption;

    /**
     * Rffrfsifs tif durrfnt row witi its most rfdfnt vbluf in
     * tif dbtbbbsf.  Tiis mftiod dbnnot bf dbllfd wifn
     * tif dursor is on tif insfrt row.
     *
     * <P>Tif <dodf>rffrfsiRow</dodf> mftiod providfs b wby for bn
     * bpplidbtion to
     * fxpliditly tfll tif JDBC drivfr to rffftdi b row(s) from tif
     * dbtbbbsf.  An bpplidbtion mby wbnt to dbll <dodf>rffrfsiRow</dodf> wifn
     * dbdiing or prffftdiing is bfing donf by tif JDBC drivfr to
     * fftdi tif lbtfst vbluf of b row from tif dbtbbbsf.  Tif JDBC drivfr
     * mby bdtublly rffrfsi multiplf rows bt ondf if tif fftdi sizf is
     * grfbtfr tibn onf.
     *
     * <P> All vblufs brf rffftdifd subjfdt to tif trbnsbdtion isolbtion
     * lfvfl bnd dursor sfnsitivity.  If <dodf>rffrfsiRow</dodf> is dbllfd bftfr
     * dblling bn updbtfr mftiod, but bfforf dblling
     * tif mftiod <dodf>updbtfRow</dodf>, tifn tif
     * updbtfs mbdf to tif row brf lost.  Cblling tif mftiod
     * <dodf>rffrfsiRow</dodf> frfqufntly will likfly slow pfrformbndf.
     *
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror
     * oddurs; tiis mftiod is dbllfd on b dlosfd rfsult sft;
     * tif rfsult sft typf is <dodf>TYPE_FORWARD_ONLY</dodf> or if tiis
     * mftiod is dbllfd wifn tif dursor is on tif insfrt row
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod or tiis mftiod is not supportfd for tif spfdififd rfsult
     * sft typf bnd rfsult sft dondurrfndy.
     * @sindf 1.2
     */
    void rffrfsiRow() tirows SQLExdfption;

    /**
     * Cbndfls tif updbtfs mbdf to tif durrfnt row in tiis
     * <dodf>RfsultSft</dodf> objfdt.
     * Tiis mftiod mby bf dbllfd bftfr dblling bn
     * updbtfr mftiod(s) bnd bfforf dblling
     * tif mftiod <dodf>updbtfRow</dodf> to roll bbdk
     * tif updbtfs mbdf to b row.  If no updbtfs ibvf bffn mbdf or
     * <dodf>updbtfRow</dodf> ibs blrfbdy bffn dbllfd, tiis mftiod ibs no
     * ffffdt.
     *
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror
     *            oddurs; tiis mftiod is dbllfd on b dlosfd rfsult sft;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or if tiis mftiod is dbllfd wifn tif dursor is
     *            on tif insfrt row
      * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void dbndflRowUpdbtfs() tirows SQLExdfption;

    /**
     * Movfs tif dursor to tif insfrt row.  Tif durrfnt dursor position is
     * rfmfmbfrfd wiilf tif dursor is positionfd on tif insfrt row.
     *
     * Tif insfrt row is b spfdibl row bssodibtfd witi bn updbtbblf
     * rfsult sft.  It is fssfntiblly b bufffr wifrf b nfw row mby
     * bf donstrudtfd by dblling tif updbtfr mftiods prior to
     * insfrting tif row into tif rfsult sft.
     *
     * Only tif updbtfr, gfttfr,
     * bnd <dodf>insfrtRow</dodf> mftiods mby bf
     * dbllfd wifn tif dursor is on tif insfrt row.  All of tif dolumns in
     * b rfsult sft must bf givfn b vbluf fbdi timf tiis mftiod is
     * dbllfd bfforf dblling <dodf>insfrtRow</dodf>.
     * An updbtfr mftiod must bf dbllfd bfforf b
     * gfttfr mftiod dbn bf dbllfd on b dolumn vbluf.
     *
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs; tiis
     * mftiod is dbllfd on b dlosfd rfsult sft
     * or tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void movfToInsfrtRow() tirows SQLExdfption;

    /**
     * Movfs tif dursor to tif rfmfmbfrfd dursor position, usublly tif
     * durrfnt row.  Tiis mftiod ibs no ffffdt if tif dursor is not on
     * tif insfrt row.
     *
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs; tiis
     * mftiod is dbllfd on b dlosfd rfsult sft
     *  or tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    void movfToCurrfntRow() tirows SQLExdfption;

    /**
     * Rftrifvfs tif <dodf>Stbtfmfnt</dodf> objfdt tibt produdfd tiis
     * <dodf>RfsultSft</dodf> objfdt.
     * If tif rfsult sft wbs gfnfrbtfd somf otifr wby, sudi bs by b
     * <dodf>DbtbbbsfMftbDbtb</dodf> mftiod, tiis mftiod  mby rfturn
     * <dodf>null</dodf>.
     *
     * @rfturn tif <dodf>Stbtfmfnt</dodf> objfdt tibt produdfd
     * tiis <dodf>RfsultSft</dodf> objfdt or <dodf>null</dodf>
     * if tif rfsult sft wbs produdfd somf otifr wby
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @sindf 1.2
     */
    Stbtfmfnt gftStbtfmfnt() tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs bn <dodf>Objfdt</dodf>
     * in tif Jbvb progrbmming lbngubgf.
     * If tif vbluf is bn SQL <dodf>NULL</dodf>,
     * tif drivfr rfturns b Jbvb <dodf>null</dodf>.
     * Tiis mftiod usfs tif givfn <dodf>Mbp</dodf> objfdt
     * for tif dustom mbpping of tif
     * SQL strudturfd or distindt typf tibt is bfing rftrifvfd.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm mbp b <dodf>jbvb.util.Mbp</dodf> objfdt tibt dontbins tif mbpping
     * from SQL typf nbmfs to dlbssfs in tif Jbvb progrbmming lbngubgf
     * @rfturn bn <dodf>Objfdt</dodf> in tif Jbvb progrbmming lbngubgf
     * rfprfsfnting tif SQL vbluf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    Objfdt gftObjfdt(int dolumnIndfx, jbvb.util.Mbp<String,Clbss<?>> mbp)
        tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b <dodf>Rff</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn b <dodf>Rff</dodf> objfdt rfprfsfnting bn SQL <dodf>REF</dodf>
     *         vbluf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    Rff gftRff(int dolumnIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b <dodf>Blob</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn b <dodf>Blob</dodf> objfdt rfprfsfnting tif SQL
     *         <dodf>BLOB</dodf> vbluf in tif spfdififd dolumn
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    Blob gftBlob(int dolumnIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b <dodf>Clob</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn b <dodf>Clob</dodf> objfdt rfprfsfnting tif SQL
     *         <dodf>CLOB</dodf> vbluf in tif spfdififd dolumn
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    Clob gftClob(int dolumnIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs bn <dodf>Arrby</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn bn <dodf>Arrby</dodf> objfdt rfprfsfnting tif SQL
     *         <dodf>ARRAY</dodf> vbluf in tif spfdififd dolumn
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
      * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    Arrby gftArrby(int dolumnIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs bn <dodf>Objfdt</dodf>
     * in tif Jbvb progrbmming lbngubgf.
     * If tif vbluf is bn SQL <dodf>NULL</dodf>,
     * tif drivfr rfturns b Jbvb <dodf>null</dodf>.
     * Tiis mftiod usfs tif spfdififd <dodf>Mbp</dodf> objfdt for
     * dustom mbpping if bppropribtf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm mbp b <dodf>jbvb.util.Mbp</dodf> objfdt tibt dontbins tif mbpping
     * from SQL typf nbmfs to dlbssfs in tif Jbvb progrbmming lbngubgf
     * @rfturn bn <dodf>Objfdt</dodf> rfprfsfnting tif SQL vbluf in tif
     *         spfdififd dolumn
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    Objfdt gftObjfdt(String dolumnLbbfl, jbvb.util.Mbp<String,Clbss<?>> mbp)
      tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b <dodf>Rff</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn b <dodf>Rff</dodf> objfdt rfprfsfnting tif SQL <dodf>REF</dodf>
     *         vbluf in tif spfdififd dolumn
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
      * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    Rff gftRff(String dolumnLbbfl) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b <dodf>Blob</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn b <dodf>Blob</dodf> objfdt rfprfsfnting tif SQL <dodf>BLOB</dodf>
     *         vbluf in tif spfdififd dolumn
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    Blob gftBlob(String dolumnLbbfl) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b <dodf>Clob</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn b <dodf>Clob</dodf> objfdt rfprfsfnting tif SQL <dodf>CLOB</dodf>
     * vbluf in tif spfdififd dolumn
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
      * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    Clob gftClob(String dolumnLbbfl) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs bn <dodf>Arrby</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn bn <dodf>Arrby</dodf> objfdt rfprfsfnting tif SQL <dodf>ARRAY</dodf> vbluf in
     *         tif spfdififd dolumn
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    Arrby gftArrby(String dolumnLbbfl) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b <dodf>jbvb.sql.Dbtf</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     * Tiis mftiod usfs tif givfn dblfndbr to donstrudt bn bppropribtf millisfdond
     * vbluf for tif dbtf if tif undfrlying dbtbbbsf dofs not storf
     * timfzonf informbtion.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm dbl tif <dodf>jbvb.util.Cblfndbr</dodf> objfdt
     * to usf in donstrudting tif dbtf
     * @rfturn tif dolumn vbluf bs b <dodf>jbvb.sql.Dbtf</dodf> objfdt;
     * if tif vbluf is SQL <dodf>NULL</dodf>,
     * tif vbluf rfturnfd is <dodf>null</dodf> in tif Jbvb progrbmming lbngubgf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @sindf 1.2
     */
    jbvb.sql.Dbtf gftDbtf(int dolumnIndfx, Cblfndbr dbl) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b <dodf>jbvb.sql.Dbtf</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     * Tiis mftiod usfs tif givfn dblfndbr to donstrudt bn bppropribtf millisfdond
     * vbluf for tif dbtf if tif undfrlying dbtbbbsf dofs not storf
     * timfzonf informbtion.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm dbl tif <dodf>jbvb.util.Cblfndbr</dodf> objfdt
     * to usf in donstrudting tif dbtf
     * @rfturn tif dolumn vbluf bs b <dodf>jbvb.sql.Dbtf</dodf> objfdt;
     * if tif vbluf is SQL <dodf>NULL</dodf>,
     * tif vbluf rfturnfd is <dodf>null</dodf> in tif Jbvb progrbmming lbngubgf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @sindf 1.2
     */
    jbvb.sql.Dbtf gftDbtf(String dolumnLbbfl, Cblfndbr dbl) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b <dodf>jbvb.sql.Timf</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     * Tiis mftiod usfs tif givfn dblfndbr to donstrudt bn bppropribtf millisfdond
     * vbluf for tif timf if tif undfrlying dbtbbbsf dofs not storf
     * timfzonf informbtion.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm dbl tif <dodf>jbvb.util.Cblfndbr</dodf> objfdt
     * to usf in donstrudting tif timf
     * @rfturn tif dolumn vbluf bs b <dodf>jbvb.sql.Timf</dodf> objfdt;
     * if tif vbluf is SQL <dodf>NULL</dodf>,
     * tif vbluf rfturnfd is <dodf>null</dodf> in tif Jbvb progrbmming lbngubgf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @sindf 1.2
     */
    jbvb.sql.Timf gftTimf(int dolumnIndfx, Cblfndbr dbl) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b <dodf>jbvb.sql.Timf</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     * Tiis mftiod usfs tif givfn dblfndbr to donstrudt bn bppropribtf millisfdond
     * vbluf for tif timf if tif undfrlying dbtbbbsf dofs not storf
     * timfzonf informbtion.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm dbl tif <dodf>jbvb.util.Cblfndbr</dodf> objfdt
     * to usf in donstrudting tif timf
     * @rfturn tif dolumn vbluf bs b <dodf>jbvb.sql.Timf</dodf> objfdt;
     * if tif vbluf is SQL <dodf>NULL</dodf>,
     * tif vbluf rfturnfd is <dodf>null</dodf> in tif Jbvb progrbmming lbngubgf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @sindf 1.2
     */
    jbvb.sql.Timf gftTimf(String dolumnLbbfl, Cblfndbr dbl) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b <dodf>jbvb.sql.Timfstbmp</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     * Tiis mftiod usfs tif givfn dblfndbr to donstrudt bn bppropribtf millisfdond
     * vbluf for tif timfstbmp if tif undfrlying dbtbbbsf dofs not storf
     * timfzonf informbtion.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm dbl tif <dodf>jbvb.util.Cblfndbr</dodf> objfdt
     * to usf in donstrudting tif timfstbmp
     * @rfturn tif dolumn vbluf bs b <dodf>jbvb.sql.Timfstbmp</dodf> objfdt;
     * if tif vbluf is SQL <dodf>NULL</dodf>,
     * tif vbluf rfturnfd is <dodf>null</dodf> in tif Jbvb progrbmming lbngubgf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @sindf 1.2
     */
    jbvb.sql.Timfstbmp gftTimfstbmp(int dolumnIndfx, Cblfndbr dbl)
      tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b <dodf>jbvb.sql.Timfstbmp</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     * Tiis mftiod usfs tif givfn dblfndbr to donstrudt bn bppropribtf millisfdond
     * vbluf for tif timfstbmp if tif undfrlying dbtbbbsf dofs not storf
     * timfzonf informbtion.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm dbl tif <dodf>jbvb.util.Cblfndbr</dodf> objfdt
     * to usf in donstrudting tif dbtf
     * @rfturn tif dolumn vbluf bs b <dodf>jbvb.sql.Timfstbmp</dodf> objfdt;
     * if tif vbluf is SQL <dodf>NULL</dodf>,
     * tif vbluf rfturnfd is <dodf>null</dodf> in tif Jbvb progrbmming lbngubgf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid or
     * if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @sindf 1.2
     */
    jbvb.sql.Timfstbmp gftTimfstbmp(String dolumnLbbfl, Cblfndbr dbl)
      tirows SQLExdfption;

    //-------------------------- JDBC 3.0 ----------------------------------------

    /**
     * Tif donstbnt indidbting tibt opfn <dodf>RfsultSft</dodf> objfdts witi tiis
     * ioldbbility will rfmbin opfn wifn tif durrfnt trbnsbdtion is dommittfd.
     *
     * @sindf 1.4
     */
    int HOLD_CURSORS_OVER_COMMIT = 1;

    /**
     * Tif donstbnt indidbting tibt opfn <dodf>RfsultSft</dodf> objfdts witi tiis
     * ioldbbility will bf dlosfd wifn tif durrfnt trbnsbdtion is dommittfd.
     *
     * @sindf 1.4
     */
    int CLOSE_CURSORS_AT_COMMIT = 2;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b <dodf>jbvb.nft.URL</dodf>
     * objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif indfx of tif dolumn 1 is tif first, 2 is tif sfdond,...
     * @rfturn tif dolumn vbluf bs b <dodf>jbvb.nft.URL</dodf> objfdt;
     * if tif vbluf is SQL <dodf>NULL</dodf>,
     * tif vbluf rfturnfd is <dodf>null</dodf> in tif Jbvb progrbmming lbngubgf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs; tiis mftiod
     * is dbllfd on b dlosfd rfsult sft or if b URL is mblformfd
      * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.4
     */
    jbvb.nft.URL gftURL(int dolumnIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b <dodf>jbvb.nft.URL</dodf>
     * objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn tif dolumn vbluf bs b <dodf>jbvb.nft.URL</dodf> objfdt;
     * if tif vbluf is SQL <dodf>NULL</dodf>,
     * tif vbluf rfturnfd is <dodf>null</dodf> in tif Jbvb progrbmming lbngubgf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs; tiis mftiod
     * is dbllfd on b dlosfd rfsult sft or if b URL is mblformfd
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.4
     */
    jbvb.nft.URL gftURL(String dolumnLbbfl) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.Rff</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.4
     */
    void updbtfRff(int dolumnIndfx, jbvb.sql.Rff x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.Rff</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.4
     */
    void updbtfRff(String dolumnLbbfl, jbvb.sql.Rff x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.Blob</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.4
     */
    void updbtfBlob(int dolumnIndfx, jbvb.sql.Blob x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.Blob</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.4
     */
    void updbtfBlob(String dolumnLbbfl, jbvb.sql.Blob x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.Clob</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.4
     */
    void updbtfClob(int dolumnIndfx, jbvb.sql.Clob x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.Clob</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.4
     */
    void updbtfClob(String dolumnLbbfl, jbvb.sql.Clob x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.Arrby</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.4
     */
    void updbtfArrby(int dolumnIndfx, jbvb.sql.Arrby x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.Arrby</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.4
     */
    void updbtfArrby(String dolumnLbbfl, jbvb.sql.Arrby x) tirows SQLExdfption;

    //------------------------- JDBC 4.0 -----------------------------------

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row of tiis
     * <dodf>RfsultSft</dodf> objfdt bs b <dodf>jbvb.sql.RowId</dodf> objfdt in tif Jbvb
     * progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond 2, ...
     * @rfturn tif dolumn vbluf; if tif vbluf is b SQL <dodf>NULL</dodf> tif
     *     vbluf rfturnfd is <dodf>null</dodf>
     * @tirows SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    RowId gftRowId(int dolumnIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row of tiis
     * <dodf>RfsultSft</dodf> objfdt bs b <dodf>jbvb.sql.RowId</dodf> objfdt in tif Jbvb
     * progrbmming lbngubgf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn tif dolumn vbluf ; if tif vbluf is b SQL <dodf>NULL</dodf> tif
     *     vbluf rfturnfd is <dodf>null</dodf>
     * @tirows SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    RowId gftRowId(String dolumnLbbfl) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>RowId</dodf> vbluf. Tif updbtfr
     * mftiods brf usfd to updbtf dolumn vblufs in tif durrfnt row or tif insfrt
     * row. Tif updbtfr mftiods do not updbtf tif undfrlying dbtbbbsf; instfbd
     * tif <dodf>updbtfRow</dodf> or <dodf>insfrtRow</dodf> mftiods brf dbllfd
     * to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond 2, ...
     * @pbrbm x tif dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfRowId(int dolumnIndfx, RowId x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>RowId</dodf> vbluf. Tif updbtfr
     * mftiods brf usfd to updbtf dolumn vblufs in tif durrfnt row or tif insfrt
     * row. Tif updbtfr mftiods do not updbtf tif undfrlying dbtbbbsf; instfbd
     * tif <dodf>updbtfRow</dodf> or <dodf>insfrtRow</dodf> mftiods brf dbllfd
     * to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfRowId(String dolumnLbbfl, RowId x) tirows SQLExdfption;

    /**
     * Rftrifvfs tif ioldbbility of tiis <dodf>RfsultSft</dodf> objfdt
     * @rfturn  fitifr <dodf>RfsultSft.HOLD_CURSORS_OVER_COMMIT</dodf> or <dodf>RfsultSft.CLOSE_CURSORS_AT_COMMIT</dodf>
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @sindf 1.6
     */
    int gftHoldbbility() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis <dodf>RfsultSft</dodf> objfdt ibs bffn dlosfd. A <dodf>RfsultSft</dodf> is dlosfd if tif
     * mftiod dlosf ibs bffn dbllfd on it, or if it is butombtidblly dlosfd.
     *
     * @rfturn truf if tiis <dodf>RfsultSft</dodf> objfdt is dlosfd; fblsf if it is still opfn
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.6
     */
    boolfbn isClosfd() tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>String</dodf> vbluf.
     * It is intfndfd for usf wifn updbting <dodf>NCHAR</dodf>,<dodf>NVARCHAR</dodf>
     * bnd <dodf>LONGNVARCHAR</dodf> dolumns.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond 2, ...
     * @pbrbm nString tif vbluf for tif dolumn to bf updbtfd
     * @tirows SQLExdfption if tif dolumnIndfx is not vblid;
     * if tif drivfr dofs not support nbtionbl
     *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
     *  frror dould oddur; tiis mftiod is dbllfd on b dlosfd rfsult sft;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfNString(int dolumnIndfx, String nString) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>String</dodf> vbluf.
     * It is intfndfd for usf wifn updbting <dodf>NCHAR</dodf>,<dodf>NVARCHAR</dodf>
     * bnd <dodf>LONGNVARCHAR</dodf> dolumns.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm nString tif vbluf for tif dolumn to bf updbtfd
     * @tirows SQLExdfption if tif dolumnLbbfl is not vblid;
     * if tif drivfr dofs not support nbtionbl
     *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
     *  frror dould oddur; tiis mftiod is dbllfd on b dlosfd rfsult sft;
     * tif rfsult sft dondurrfndy is <CODE>CONCUR_READ_ONLY</dodf>
     *  or if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfNString(String dolumnLbbfl, String nString) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.NClob</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond 2, ...
     * @pbrbm nClob tif vbluf for tif dolumn to bf updbtfd
     * @tirows SQLExdfption if tif dolumnIndfx is not vblid;
     * if tif drivfr dofs not support nbtionbl
     *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
     *  frror dould oddur; tiis mftiod is dbllfd on b dlosfd rfsult sft;
     * if b dbtbbbsf bddfss frror oddurs or
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfNClob(int dolumnIndfx, NClob nClob) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.NClob</dodf> vbluf.
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm nClob tif vbluf for tif dolumn to bf updbtfd
     * @tirows SQLExdfption if tif dolumnLbbfl is not vblid;
     * if tif drivfr dofs not support nbtionbl
     *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
     *  frror dould oddur; tiis mftiod is dbllfd on b dlosfd rfsult sft;
     *  if b dbtbbbsf bddfss frror oddurs or
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfNClob(String dolumnLbbfl, NClob nClob) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b <dodf>NClob</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn b <dodf>NClob</dodf> objfdt rfprfsfnting tif SQL
     *         <dodf>NCLOB</dodf> vbluf in tif spfdififd dolumn
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if tif drivfr dofs not support nbtionbl
     *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
     *  frror dould oddur; tiis mftiod is dbllfd on b dlosfd rfsult sft
     * or if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    NClob gftNClob(int dolumnIndfx) tirows SQLExdfption;

  /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b <dodf>NClob</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn b <dodf>NClob</dodf> objfdt rfprfsfnting tif SQL <dodf>NCLOB</dodf>
     * vbluf in tif spfdififd dolumn
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
   * if tif drivfr dofs not support nbtionbl
     *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
     *  frror dould oddur; tiis mftiod is dbllfd on b dlosfd rfsult sft
     * or if b dbtbbbsf bddfss frror oddurs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    NClob gftNClob(String dolumnLbbfl) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in  tif durrfnt row of
     *  tiis <dodf>RfsultSft</dodf> bs b
     * <dodf>jbvb.sql.SQLXML</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn b <dodf>SQLXML</dodf> objfdt tibt mbps bn <dodf>SQL XML</dodf> vbluf
     * @tirows SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    SQLXML gftSQLXML(int dolumnIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in  tif durrfnt row of
     *  tiis <dodf>RfsultSft</dodf> bs b
     * <dodf>jbvb.sql.SQLXML</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn b <dodf>SQLXML</dodf> objfdt tibt mbps bn <dodf>SQL XML</dodf> vbluf
     * @tirows SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    SQLXML gftSQLXML(String dolumnLbbfl) tirows SQLExdfption;
    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.SQLXML</dodf> vbluf.
     * Tif updbtfr
     * mftiods brf usfd to updbtf dolumn vblufs in tif durrfnt row or tif insfrt
     * row. Tif updbtfr mftiods do not updbtf tif undfrlying dbtbbbsf; instfbd
     * tif <dodf>updbtfRow</dodf> or <dodf>insfrtRow</dodf> mftiods brf dbllfd
     * to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond 2, ...
     * @pbrbm xmlObjfdt tif vbluf for tif dolumn to bf updbtfd
     * @tirows SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs; tiis mftiod
     *  is dbllfd on b dlosfd rfsult sft;
     * tif <dodf>jbvb.xml.trbnsform.Rfsult</dodf>,
     *  <dodf>Writfr</dodf> or <dodf>OutputStrfbm</dodf> ibs not bffn dlosfd
     * for tif <dodf>SQLXML</dodf> objfdt;
     *  if tifrf is bn frror prodfssing tif XML vbluf or
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>.  Tif <dodf>gftCbusf</dodf> mftiod
     *  of tif fxdfption mby providf b morf dftbilfd fxdfption, for fxbmplf, if tif
     *  strfbm dofs not dontbin vblid XML.
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfSQLXML(int dolumnIndfx, SQLXML xmlObjfdt) tirows SQLExdfption;
    /**
     * Updbtfs tif dfsignbtfd dolumn witi b <dodf>jbvb.sql.SQLXML</dodf> vbluf.
     * Tif updbtfr
     * mftiods brf usfd to updbtf dolumn vblufs in tif durrfnt row or tif insfrt
     * row. Tif updbtfr mftiods do not updbtf tif undfrlying dbtbbbsf; instfbd
     * tif <dodf>updbtfRow</dodf> or <dodf>insfrtRow</dodf> mftiods brf dbllfd
     * to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm xmlObjfdt tif dolumn vbluf
     * @tirows SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs; tiis mftiod
     *  is dbllfd on b dlosfd rfsult sft;
     * tif <dodf>jbvb.xml.trbnsform.Rfsult</dodf>,
     *  <dodf>Writfr</dodf> or <dodf>OutputStrfbm</dodf> ibs not bffn dlosfd
     * for tif <dodf>SQLXML</dodf> objfdt;
     *  if tifrf is bn frror prodfssing tif XML vbluf or
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>.  Tif <dodf>gftCbusf</dodf> mftiod
     *  of tif fxdfption mby providf b morf dftbilfd fxdfption, for fxbmplf, if tif
     *  strfbm dofs not dontbin vblid XML.
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfSQLXML(String dolumnLbbfl, SQLXML xmlObjfdt) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>String</dodf> in tif Jbvb progrbmming lbngubgf.
     * It is intfndfd for usf wifn
     * bddfssing  <dodf>NCHAR</dodf>,<dodf>NVARCHAR</dodf>
     * bnd <dodf>LONGNVARCHAR</dodf> dolumns.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    String gftNString(int dolumnIndfx) tirows SQLExdfption;


    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs
     * b <dodf>String</dodf> in tif Jbvb progrbmming lbngubgf.
     * It is intfndfd for usf wifn
     * bddfssing  <dodf>NCHAR</dodf>,<dodf>NVARCHAR</dodf>
     * bnd <dodf>LONGNVARCHAR</dodf> dolumns.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * vbluf rfturnfd is <dodf>null</dodf>
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    String gftNString(String dolumnLbbfl) tirows SQLExdfption;


    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt.
     * It is intfndfd for usf wifn
     * bddfssing  <dodf>NCHAR</dodf>,<dodf>NVARCHAR</dodf>
     * bnd <dodf>LONGNVARCHAR</dodf> dolumns.
     *
     * @rfturn b <dodf>jbvb.io.Rfbdfr</dodf> objfdt tibt dontbins tif dolumn
     * vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif vbluf rfturnfd is
     * <dodf>null</dodf> in tif Jbvb progrbmming lbngubgf.
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    jbvb.io.Rfbdfr gftNCibrbdtfrStrfbm(int dolumnIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bs b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt.
     * It is intfndfd for usf wifn
     * bddfssing  <dodf>NCHAR</dodf>,<dodf>NVARCHAR</dodf>
     * bnd <dodf>LONGNVARCHAR</dodf> dolumns.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @rfturn b <dodf>jbvb.io.Rfbdfr</dodf> objfdt tibt dontbins tif dolumn
     * vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif vbluf rfturnfd is
     * <dodf>null</dodf> in tif Jbvb progrbmming lbngubgf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    jbvb.io.Rfbdfr gftNCibrbdtfrStrfbm(String dolumnLbbfl) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b dibrbdtfr strfbm vbluf, wiidi will ibvf
     * tif spfdififd numbfr of bytfs.   Tif
     * drivfr dofs tif nfdfssbry donvfrsion from Jbvb dibrbdtfr formbt to
     * tif nbtionbl dibrbdtfr sft in tif dbtbbbsf.
     * It is intfndfd for usf wifn
     * updbting  <dodf>NCHAR</dodf>,<dodf>NVARCHAR</dodf>
     * bnd <dodf>LONGNVARCHAR</dodf> dolumns.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm lfngti tif lfngti of tif strfbm
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf> or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfNCibrbdtfrStrfbm(int dolumnIndfx,
                             jbvb.io.Rfbdfr x,
                             long lfngti) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b dibrbdtfr strfbm vbluf, wiidi will ibvf
     * tif spfdififd numbfr of bytfs.  Tif
     * drivfr dofs tif nfdfssbry donvfrsion from Jbvb dibrbdtfr formbt to
     * tif nbtionbl dibrbdtfr sft in tif dbtbbbsf.
     * It is intfndfd for usf wifn
     * updbting  <dodf>NCHAR</dodf>,<dodf>NVARCHAR</dodf>
     * bnd <dodf>LONGNVARCHAR</dodf> dolumns.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm rfbdfr tif <dodf>jbvb.io.Rfbdfr</dodf> objfdt dontbining
     *        tif nfw dolumn vbluf
     * @pbrbm lfngti tif lfngti of tif strfbm
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf> or tiis mftiod is dbllfd on b dlosfd rfsult sft
      * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfNCibrbdtfrStrfbm(String dolumnLbbfl,
                             jbvb.io.Rfbdfr rfbdfr,
                             long lfngti) tirows SQLExdfption;
    /**
     * Updbtfs tif dfsignbtfd dolumn witi bn bsdii strfbm vbluf, wiidi will ibvf
     * tif spfdififd numbfr of bytfs.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm lfngti tif lfngti of tif strfbm
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfAsdiiStrfbm(int dolumnIndfx,
                           jbvb.io.InputStrfbm x,
                           long lfngti) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b binbry strfbm vbluf, wiidi will ibvf
     * tif spfdififd numbfr of bytfs.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm lfngti tif lfngti of tif strfbm
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfBinbryStrfbm(int dolumnIndfx,
                            jbvb.io.InputStrfbm x,
                            long lfngti) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b dibrbdtfr strfbm vbluf, wiidi will ibvf
     * tif spfdififd numbfr of bytfs.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm lfngti tif lfngti of tif strfbm
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfCibrbdtfrStrfbm(int dolumnIndfx,
                             jbvb.io.Rfbdfr x,
                             long lfngti) tirows SQLExdfption;
    /**
     * Updbtfs tif dfsignbtfd dolumn witi bn bsdii strfbm vbluf, wiidi will ibvf
     * tif spfdififd numbfr of bytfs.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm lfngti tif lfngti of tif strfbm
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfAsdiiStrfbm(String dolumnLbbfl,
                           jbvb.io.InputStrfbm x,
                           long lfngti) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b binbry strfbm vbluf, wiidi will ibvf
     * tif spfdififd numbfr of bytfs.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm lfngti tif lfngti of tif strfbm
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfBinbryStrfbm(String dolumnLbbfl,
                            jbvb.io.InputStrfbm x,
                            long lfngti) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b dibrbdtfr strfbm vbluf, wiidi will ibvf
     * tif spfdififd numbfr of bytfs.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm rfbdfr tif <dodf>jbvb.io.Rfbdfr</dodf> objfdt dontbining
     *        tif nfw dolumn vbluf
     * @pbrbm lfngti tif lfngti of tif strfbm
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfCibrbdtfrStrfbm(String dolumnLbbfl,
                             jbvb.io.Rfbdfr rfbdfr,
                             long lfngti) tirows SQLExdfption;
    /**
     * Updbtfs tif dfsignbtfd dolumn using tif givfn input strfbm, wiidi
     * will ibvf tif spfdififd numbfr of bytfs.
     *
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm inputStrfbm An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr
     * vbluf to.
     * @pbrbm lfngti tif numbfr of bytfs in tif pbrbmftfr dbtb.
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfBlob(int dolumnIndfx, InputStrfbm inputStrfbm, long lfngti) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn using tif givfn input strfbm, wiidi
     * will ibvf tif spfdififd numbfr of bytfs.
     *
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm inputStrfbm An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr
     * vbluf to.
     * @pbrbm lfngti tif numbfr of bytfs in tif pbrbmftfr dbtb.
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfBlob(String dolumnLbbfl, InputStrfbm inputStrfbm, long lfngti) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn using tif givfn <dodf>Rfbdfr</dodf>
     * objfdt, wiidi is tif givfn numbfr of dibrbdtfrs long.
     * Wifn b vfry lbrgf UNICODE vbluf is input to b <dodf>LONGVARCHAR</dodf>
     * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt. Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from UNICODE to tif dbtbbbsf dibr formbt.
     *
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
     * @pbrbm lfngti tif numbfr of dibrbdtfrs in tif pbrbmftfr dbtb.
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfClob(int dolumnIndfx,  Rfbdfr rfbdfr, long lfngti) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn using tif givfn <dodf>Rfbdfr</dodf>
     * objfdt, wiidi is tif givfn numbfr of dibrbdtfrs long.
     * Wifn b vfry lbrgf UNICODE vbluf is input to b <dodf>LONGVARCHAR</dodf>
     * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt.  Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from UNICODE to tif dbtbbbsf dibr formbt.
     *
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
     * @pbrbm lfngti tif numbfr of dibrbdtfrs in tif pbrbmftfr dbtb.
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfClob(String dolumnLbbfl,  Rfbdfr rfbdfr, long lfngti) tirows SQLExdfption;
   /**
     * Updbtfs tif dfsignbtfd dolumn using tif givfn <dodf>Rfbdfr</dodf>
     * objfdt, wiidi is tif givfn numbfr of dibrbdtfrs long.
     * Wifn b vfry lbrgf UNICODE vbluf is input to b <dodf>LONGVARCHAR</dodf>
     * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt. Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from UNICODE to tif dbtbbbsf dibr formbt.
     *
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond 2, ...
     * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
     * @pbrbm lfngti tif numbfr of dibrbdtfrs in tif pbrbmftfr dbtb.
     * @tirows SQLExdfption if tif dolumnIndfx is not vblid;
    * if tif drivfr dofs not support nbtionbl
     *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
     *  frror dould oddur; tiis mftiod is dbllfd on b dlosfd rfsult sft,
     * if b dbtbbbsf bddfss frror oddurs or
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfNClob(int dolumnIndfx,  Rfbdfr rfbdfr, long lfngti) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn using tif givfn <dodf>Rfbdfr</dodf>
     * objfdt, wiidi is tif givfn numbfr of dibrbdtfrs long.
     * Wifn b vfry lbrgf UNICODE vbluf is input to b <dodf>LONGVARCHAR</dodf>
     * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt. Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from UNICODE to tif dbtbbbsf dibr formbt.
     *
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
     * @pbrbm lfngti tif numbfr of dibrbdtfrs in tif pbrbmftfr dbtb.
     * @tirows SQLExdfption if tif dolumnLbbfl is not vblid;
     * if tif drivfr dofs not support nbtionbl
     *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
     *  frror dould oddur; tiis mftiod is dbllfd on b dlosfd rfsult sft;
     *  if b dbtbbbsf bddfss frror oddurs or
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfNClob(String dolumnLbbfl,  Rfbdfr rfbdfr, long lfngti) tirows SQLExdfption;

    //---

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b dibrbdtfr strfbm vbluf.
     * Tif dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-strfbm is rfbdifd.  Tif
     * drivfr dofs tif nfdfssbry donvfrsion from Jbvb dibrbdtfr formbt to
     * tif nbtionbl dibrbdtfr sft in tif dbtbbbsf.
     * It is intfndfd for usf wifn
     * updbting  <dodf>NCHAR</dodf>,<dodf>NVARCHAR</dodf>
     * bnd <dodf>LONGNVARCHAR</dodf> dolumns.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfNCibrbdtfrStrfbm</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf> or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfNCibrbdtfrStrfbm(int dolumnIndfx,
                             jbvb.io.Rfbdfr x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b dibrbdtfr strfbm vbluf.
     * Tif dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-strfbm is rfbdifd.  Tif
     * drivfr dofs tif nfdfssbry donvfrsion from Jbvb dibrbdtfr formbt to
     * tif nbtionbl dibrbdtfr sft in tif dbtbbbsf.
     * It is intfndfd for usf wifn
     * updbting  <dodf>NCHAR</dodf>,<dodf>NVARCHAR</dodf>
     * bnd <dodf>LONGNVARCHAR</dodf> dolumns.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfNCibrbdtfrStrfbm</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm rfbdfr tif <dodf>jbvb.io.Rfbdfr</dodf> objfdt dontbining
     *        tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf> or tiis mftiod is dbllfd on b dlosfd rfsult sft
      * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfNCibrbdtfrStrfbm(String dolumnLbbfl,
                             jbvb.io.Rfbdfr rfbdfr) tirows SQLExdfption;
    /**
     * Updbtfs tif dfsignbtfd dolumn witi bn bsdii strfbm vbluf.
     * Tif dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-strfbm is rfbdifd.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfAsdiiStrfbm</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfAsdiiStrfbm(int dolumnIndfx,
                           jbvb.io.InputStrfbm x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b binbry strfbm vbluf.
     * Tif dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-strfbm is rfbdifd.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfBinbryStrfbm</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfBinbryStrfbm(int dolumnIndfx,
                            jbvb.io.InputStrfbm x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b dibrbdtfr strfbm vbluf.
     * Tif dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-strfbm is rfbdifd.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfCibrbdtfrStrfbm</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfCibrbdtfrStrfbm(int dolumnIndfx,
                             jbvb.io.Rfbdfr x) tirows SQLExdfption;
    /**
     * Updbtfs tif dfsignbtfd dolumn witi bn bsdii strfbm vbluf.
     * Tif dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-strfbm is rfbdifd.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfAsdiiStrfbm</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfAsdiiStrfbm(String dolumnLbbfl,
                           jbvb.io.InputStrfbm x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b binbry strfbm vbluf.
     * Tif dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-strfbm is rfbdifd.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfBinbryStrfbm</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfBinbryStrfbm(String dolumnLbbfl,
                            jbvb.io.InputStrfbm x) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn witi b dibrbdtfr strfbm vbluf.
     * Tif dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-strfbm is rfbdifd.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfCibrbdtfrStrfbm</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm rfbdfr tif <dodf>jbvb.io.Rfbdfr</dodf> objfdt dontbining
     *        tif nfw dolumn vbluf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid; if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfCibrbdtfrStrfbm(String dolumnLbbfl,
                             jbvb.io.Rfbdfr rfbdfr) tirows SQLExdfption;
    /**
     * Updbtfs tif dfsignbtfd dolumn using tif givfn input strfbm. Tif dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-strfbm is rfbdifd.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfBlob</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm inputStrfbm An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr
     * vbluf to.
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid; if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfBlob(int dolumnIndfx, InputStrfbm inputStrfbm) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn using tif givfn input strfbm. Tif dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-strfbm is rfbdifd.
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     *   <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfBlob</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm inputStrfbm An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr
     * vbluf to.
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid; if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfBlob(String dolumnLbbfl, InputStrfbm inputStrfbm) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn using tif givfn <dodf>Rfbdfr</dodf>
     * objfdt.
     *  Tif dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-strfbm is rfbdifd.  Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from UNICODE to tif dbtbbbsf dibr formbt.
     *
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     *   <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfClob</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfClob(int dolumnIndfx,  Rfbdfr rfbdfr) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn using tif givfn <dodf>Rfbdfr</dodf>
     * objfdt.
     *  Tif dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-strfbm is rfbdifd.  Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from UNICODE to tif dbtbbbsf dibr formbt.
     *
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfClob</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid; if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfClob(String dolumnLbbfl,  Rfbdfr rfbdfr) tirows SQLExdfption;
   /**
     * Updbtfs tif dfsignbtfd dolumn using tif givfn <dodf>Rfbdfr</dodf>
     *
     * Tif dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-strfbm is rfbdifd.  Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from UNICODE to tif dbtbbbsf dibr formbt.
     *
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfNClob</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond 2, ...
     * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
     * @tirows SQLExdfption if tif dolumnIndfx is not vblid;
    * if tif drivfr dofs not support nbtionbl
     *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
     *  frror dould oddur; tiis mftiod is dbllfd on b dlosfd rfsult sft,
     * if b dbtbbbsf bddfss frror oddurs or
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfNClob(int dolumnIndfx,  Rfbdfr rfbdfr) tirows SQLExdfption;

    /**
     * Updbtfs tif dfsignbtfd dolumn using tif givfn <dodf>Rfbdfr</dodf>
     * objfdt.
     * Tif dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-strfbm is rfbdifd.  Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from UNICODE to tif dbtbbbsf dibr formbt.
     *
     * <p>
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif <dodf>updbtfRow</dodf> or
     * <dodf>insfrtRow</dodf> mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>updbtfNClob</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf of tif dolumn
     * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
     * @tirows SQLExdfption if tif dolumnLbbfl is not vblid; if tif drivfr dofs not support nbtionbl
     *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
     *  frror dould oddur; tiis mftiod is dbllfd on b dlosfd rfsult sft;
     *  if b dbtbbbsf bddfss frror oddurs or
     * tif rfsult sft dondurrfndy is <dodf>CONCUR_READ_ONLY</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void updbtfNClob(String dolumnLbbfl,  Rfbdfr rfbdfr) tirows SQLExdfption;

    //------------------------- JDBC 4.1 -----------------------------------


    /**
     *<p>Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bnd will donvfrt from tif
     * SQL typf of tif dolumn to tif rfqufstfd Jbvb dbtb typf, if tif
     * donvfrsion is supportfd. If tif donvfrsion is not
     * supportfd  or null is spfdififd for tif typf, b
     * <dodf>SQLExdfption</dodf> is tirown.
     *<p>
     * At b minimum, bn implfmfntbtion must support tif donvfrsions dffinfd in
     * Appfndix B, Tbblf B-3 bnd donvfrsion of bppropribtf usfr dffinfd SQL
     * typfs to b Jbvb typf wiidi implfmfnts {@dodf SQLDbtb}, or {@dodf Strudt}.
     * Additionbl donvfrsions mby bf supportfd bnd brf vfndor dffinfd.
     * @pbrbm <T> tif typf of tif dlbss modflfd by tiis Clbss objfdt
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm typf Clbss rfprfsfnting tif Jbvb dbtb typf to donvfrt tif dfsignbtfd
     * dolumn to.
     * @rfturn bn instbndf of {@dodf typf} iolding tif dolumn vbluf
     * @tirows SQLExdfption if donvfrsion is not supportfd, typf is null or
     *         bnotifr frror oddurs. Tif gftCbusf() mftiod of tif
     * fxdfption mby providf b morf dftbilfd fxdfption, for fxbmplf, if
     * b donvfrsion frror oddurs
     * @tirows SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.7
     */
     publid <T> T gftObjfdt(int dolumnIndfx, Clbss<T> typf) tirows SQLExdfption;


    /**
     *<p>Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>RfsultSft</dodf> objfdt bnd will donvfrt from tif
     * SQL typf of tif dolumn to tif rfqufstfd Jbvb dbtb typf, if tif
     * donvfrsion is supportfd. If tif donvfrsion is not
     * supportfd  or null is spfdififd for tif typf, b
     * <dodf>SQLExdfption</dodf> is tirown.
     *<p>
     * At b minimum, bn implfmfntbtion must support tif donvfrsions dffinfd in
     * Appfndix B, Tbblf B-3 bnd donvfrsion of bppropribtf usfr dffinfd SQL
     * typfs to b Jbvb typf wiidi implfmfnts {@dodf SQLDbtb}, or {@dodf Strudt}.
     * Additionbl donvfrsions mby bf supportfd bnd brf vfndor dffinfd.
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS dlbusf.
     * If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is tif nbmf
     * of tif dolumn
     * @pbrbm typf Clbss rfprfsfnting tif Jbvb dbtb typf to donvfrt tif dfsignbtfd
     * dolumn to.
     * @pbrbm <T> tif typf of tif dlbss modflfd by tiis Clbss objfdt
     * @rfturn bn instbndf of {@dodf typf} iolding tif dolumn vbluf
     * @tirows SQLExdfption if donvfrsion is not supportfd, typf is null or
     *         bnotifr frror oddurs. Tif gftCbusf() mftiod of tif
     * fxdfption mby providf b morf dftbilfd fxdfption, for fxbmplf, if
     * b donvfrsion frror oddurs
     * @tirows SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.7
     */
     publid <T> T gftObjfdt(String dolumnLbbfl, Clbss<T> typf) tirows SQLExdfption;

    //------------------------- JDBC 4.2 -----------------------------------

    /**
     * Updbtfs tif dfsignbtfd dolumn witi bn {@dodf Objfdt} vbluf.
     *
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif {@dodf updbtfRow} or
     * {@dodf insfrtRow} mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *<p>
     * If tif sfdond brgumfnt is bn {@dodf InputStrfbm} tifn tif strfbm must dontbin
     * tif numbfr of bytfs spfdififd by sdblfOrLfngti.  If tif sfdond brgumfnt is b
     * {@dodf Rfbdfr} tifn tif rfbdfr must dontbin tif numbfr of dibrbdtfrs spfdififd
     * by sdblfOrLfngti. If tifsf donditions brf not truf tif drivfr will gfnfrbtf b
     * {@dodf SQLExdfption} wifn tif stbtfmfnt is fxfdutfd.
     *<p>
     * Tif dffbult implfmfntbtion will tirow {@dodf SQLFfbturfNotSupportfdExdfption}
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm tbrgftSqlTypf tif SQL typf to bf sfnt to tif dbtbbbsf
     * @pbrbm sdblfOrLfngti for bn objfdt of {@dodf jbvb.mbti.BigDfdimbl} ,
     *          tiis is tif numbfr of digits bftfr tif dfdimbl point. For
     *          Jbvb Objfdt typfs {@dodf InputStrfbm} bnd {@dodf Rfbdfr},
     *          tiis is tif lfngti
     *          of tif dbtb in tif strfbm or rfbdfr.  For bll otifr typfs,
     *          tiis vbluf will bf ignorfd.
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is {@dodf CONCUR_READ_ONLY}
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not
     * support tiis mftiod; if tif JDBC drivfr dofs not support tif spfdififd tbrgftSqlTypf
     * @sff JDBCTypf
     * @sff SQLTypf
     * @sindf 1.8
     */
     dffbult void updbtfObjfdt(int dolumnIndfx, Objfdt x,
             SQLTypf tbrgftSqlTypf, int sdblfOrLfngti)  tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption("updbtfObjfdt not implfmfntfd");
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi bn {@dodf Objfdt} vbluf.
     *
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif {@dodf updbtfRow} or
     * {@dodf insfrtRow} mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *<p>
     * If tif sfdond brgumfnt is bn {@dodf InputStrfbm} tifn tif strfbm must
     * dontbin numbfr of bytfs spfdififd by sdblfOrLfngti.  If tif sfdond
     * brgumfnt is b {@dodf Rfbdfr} tifn tif rfbdfr must dontbin tif numbfr
     * of dibrbdtfrs spfdififd by sdblfOrLfngti. If tifsf donditions brf not
     * truf tif drivfr will gfnfrbtf b
     * {@dodf SQLExdfption} wifn tif stbtfmfnt is fxfdutfd.
     *<p>
     * Tif dffbult implfmfntbtion will tirow {@dodf SQLFfbturfNotSupportfdExdfption}
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS
     * dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is
     * tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm tbrgftSqlTypf tif SQL typf to bf sfnt to tif dbtbbbsf
     * @pbrbm sdblfOrLfngti for bn objfdt of {@dodf jbvb.mbti.BigDfdimbl} ,
     *          tiis is tif numbfr of digits bftfr tif dfdimbl point. For
     *          Jbvb Objfdt typfs {@dodf InputStrfbm} bnd {@dodf Rfbdfr},
     *          tiis is tif lfngti
     *          of tif dbtb in tif strfbm or rfbdfr.  For bll otifr typfs,
     *          tiis vbluf will bf ignorfd.
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is {@dodf CONCUR_READ_ONLY}
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not
     * support tiis mftiod; if tif JDBC drivfr dofs not support tif spfdififd tbrgftSqlTypf
     * @sff JDBCTypf
     * @sff SQLTypf
     * @sindf 1.8
     */
    dffbult void updbtfObjfdt(String dolumnLbbfl, Objfdt x,
            SQLTypf tbrgftSqlTypf, int sdblfOrLfngti) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption("updbtfObjfdt not implfmfntfd");
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi bn {@dodf Objfdt} vbluf.
     *
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif {@dodf updbtfRow} or
     * {@dodf insfrtRow} mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *<p>
     * Tif dffbult implfmfntbtion will tirow {@dodf SQLFfbturfNotSupportfdExdfption}
     *
     * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm tbrgftSqlTypf tif SQL typf to bf sfnt to tif dbtbbbsf
     * @fxdfption SQLExdfption if tif dolumnIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is {@dodf CONCUR_READ_ONLY}
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not
     * support tiis mftiod; if tif JDBC drivfr dofs not support tif spfdififd tbrgftSqlTypf
     * @sff JDBCTypf
     * @sff SQLTypf
     * @sindf 1.8
     */
    dffbult void updbtfObjfdt(int dolumnIndfx, Objfdt x, SQLTypf tbrgftSqlTypf)
            tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption("updbtfObjfdt not implfmfntfd");
    }

    /**
     * Updbtfs tif dfsignbtfd dolumn witi bn {@dodf Objfdt} vbluf.
     *
     * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif
     * durrfnt row or tif insfrt row.  Tif updbtfr mftiods do not
     * updbtf tif undfrlying dbtbbbsf; instfbd tif {@dodf updbtfRow} or
     * {@dodf insfrtRow} mftiods brf dbllfd to updbtf tif dbtbbbsf.
     *<p>
     * Tif dffbult implfmfntbtion will tirow {@dodf SQLFfbturfNotSupportfdExdfption}
     *
     * @pbrbm dolumnLbbfl tif lbbfl for tif dolumn spfdififd witi tif SQL AS
     * dlbusf.  If tif SQL AS dlbusf wbs not spfdififd, tifn tif lbbfl is
     * tif nbmf of tif dolumn
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm tbrgftSqlTypf tif SQL typf to bf sfnt to tif dbtbbbsf
     * @fxdfption SQLExdfption if tif dolumnLbbfl is not vblid;
     * if b dbtbbbsf bddfss frror oddurs;
     * tif rfsult sft dondurrfndy is {@dodf CONCUR_READ_ONLY}
     * or tiis mftiod is dbllfd on b dlosfd rfsult sft
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not
     * support tiis mftiod; if tif JDBC drivfr dofs not support tif spfdififd tbrgftSqlTypf
     * @sff JDBCTypf
     * @sff SQLTypf
     * @sindf 1.8
     */
    dffbult void updbtfObjfdt(String dolumnLbbfl, Objfdt x,
            SQLTypf tbrgftSqlTypf) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption("updbtfObjfdt not implfmfntfd");
    }
}

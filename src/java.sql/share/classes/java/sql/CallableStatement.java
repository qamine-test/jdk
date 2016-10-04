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
 * Tif intfrfbdf usfd to fxfdutf SQL storfd prodfdurfs.  Tif JDBC API
 * providfs b storfd prodfdurf SQL fsdbpf syntbx tibt bllows storfd prodfdurfs
 * to bf dbllfd in b stbndbrd wby for bll RDBMSs. Tiis fsdbpf syntbx ibs onf
 * form tibt indludfs b rfsult pbrbmftfr bnd onf tibt dofs not. If usfd, tif rfsult
 * pbrbmftfr must bf rfgistfrfd bs bn OUT pbrbmftfr. Tif otifr pbrbmftfrs
 * dbn bf usfd for input, output or boti. Pbrbmftfrs brf rfffrrfd to
 * sfqufntiblly, by numbfr, witi tif first pbrbmftfr bfing 1.
 * <PRE>
 *   {?= dbll &lt;prodfdurf-nbmf&gt;[(&lt;brg1&gt;,&lt;brg2&gt;, ...)]}
 *   {dbll &lt;prodfdurf-nbmf&gt;[(&lt;brg1&gt;,&lt;brg2&gt;, ...)]}
 * </PRE>
 * <P>
 * IN pbrbmftfr vblufs brf sft using tif <dodf>sft</dodf> mftiods inifritfd from
 * {@link PrfpbrfdStbtfmfnt}.  Tif typf of bll OUT pbrbmftfrs must bf
 * rfgistfrfd prior to fxfduting tif storfd prodfdurf; tifir vblufs
 * brf rftrifvfd bftfr fxfdution vib tif <dodf>gft</dodf> mftiods providfd ifrf.
 * <P>
 * A <dodf>CbllbblfStbtfmfnt</dodf> dbn rfturn onf {@link RfsultSft} objfdt or
 * multiplf <dodf>RfsultSft</dodf> objfdts.  Multiplf
 * <dodf>RfsultSft</dodf> objfdts brf ibndlfd using opfrbtions
 * inifritfd from {@link Stbtfmfnt}.
 * <P>
 * For mbximum portbbility, b dbll's <dodf>RfsultSft</dodf> objfdts bnd
 * updbtf dounts siould bf prodfssfd prior to gftting tif vblufs of output
 * pbrbmftfrs.
 *
 *
 * @sff Connfdtion#prfpbrfCbll
 * @sff RfsultSft
 */

publid intfrfbdf CbllbblfStbtfmfnt fxtfnds PrfpbrfdStbtfmfnt {

    /**
     * Rfgistfrs tif OUT pbrbmftfr in ordinbl position
     * <dodf>pbrbmftfrIndfx</dodf> to tif JDBC typf
     * <dodf>sqlTypf</dodf>.  All OUT pbrbmftfrs must bf rfgistfrfd
     * bfforf b storfd prodfdurf is fxfdutfd.
     * <p>
     * Tif JDBC typf spfdififd by <dodf>sqlTypf</dodf> for bn OUT
     * pbrbmftfr dftfrminfs tif Jbvb typf tibt must bf usfd
     * in tif <dodf>gft</dodf> mftiod to rfbd tif vbluf of tibt pbrbmftfr.
     * <p>
     * If tif JDBC typf fxpfdtfd to bf rfturnfd to tiis output pbrbmftfr
     * is spfdifid to tiis pbrtidulbr dbtbbbsf, <dodf>sqlTypf</dodf>
     * siould bf <dodf>jbvb.sql.Typfs.OTHER</dodf>.  Tif mftiod
     * {@link #gftObjfdt} rftrifvfs tif vbluf.
     *
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2,
     *        bnd so on
     * @pbrbm sqlTypf tif JDBC typf dodf dffinfd by <dodf>jbvb.sql.Typfs</dodf>.
     *        If tif pbrbmftfr is of JDBC typf <dodf>NUMERIC</dodf>
     *        or <dodf>DECIMAL</dodf>, tif vfrsion of
     *        <dodf>rfgistfrOutPbrbmftfr</dodf> tibt bddfpts b sdblf vbluf
     *        siould bf usfd.
     *
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if <dodf>sqlTypf</dodf> is
     * b <dodf>ARRAY</dodf>, <dodf>BLOB</dodf>, <dodf>CLOB</dodf>,
     * <dodf>DATALINK</dodf>, <dodf>JAVA_OBJECT</dodf>, <dodf>NCHAR</dodf>,
     * <dodf>NCLOB</dodf>, <dodf>NVARCHAR</dodf>, <dodf>LONGNVARCHAR</dodf>,
     *  <dodf>REF</dodf>, <dodf>ROWID</dodf>, <dodf>SQLXML</dodf>
     * or  <dodf>STRUCT</dodf> dbtb typf bnd tif JDBC drivfr dofs not support
     * tiis dbtb typf
     * @sff Typfs
     */
    void rfgistfrOutPbrbmftfr(int pbrbmftfrIndfx, int sqlTypf)
        tirows SQLExdfption;

    /**
     * Rfgistfrs tif pbrbmftfr in ordinbl position
     * <dodf>pbrbmftfrIndfx</dodf> to bf of JDBC typf
     * <dodf>sqlTypf</dodf>. All OUT pbrbmftfrs must bf rfgistfrfd
     * bfforf b storfd prodfdurf is fxfdutfd.
     * <p>
     * Tif JDBC typf spfdififd by <dodf>sqlTypf</dodf> for bn OUT
     * pbrbmftfr dftfrminfs tif Jbvb typf tibt must bf usfd
     * in tif <dodf>gft</dodf> mftiod to rfbd tif vbluf of tibt pbrbmftfr.
     * <p>
     * Tiis vfrsion of <dodf>rfgistfrOutPbrbmftfr</dodf> siould bf
     * usfd wifn tif pbrbmftfr is of JDBC typf <dodf>NUMERIC</dodf>
     * or <dodf>DECIMAL</dodf>.
     *
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2,
     * bnd so on
     * @pbrbm sqlTypf tif SQL typf dodf dffinfd by <dodf>jbvb.sql.Typfs</dodf>.
     * @pbrbm sdblf tif dfsirfd numbfr of digits to tif rigit of tif
     * dfdimbl point.  It must bf grfbtfr tibn or fqubl to zfro.
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if <dodf>sqlTypf</dodf> is
     * b <dodf>ARRAY</dodf>, <dodf>BLOB</dodf>, <dodf>CLOB</dodf>,
     * <dodf>DATALINK</dodf>, <dodf>JAVA_OBJECT</dodf>, <dodf>NCHAR</dodf>,
     * <dodf>NCLOB</dodf>, <dodf>NVARCHAR</dodf>, <dodf>LONGNVARCHAR</dodf>,
     *  <dodf>REF</dodf>, <dodf>ROWID</dodf>, <dodf>SQLXML</dodf>
     * or  <dodf>STRUCT</dodf> dbtb typf bnd tif JDBC drivfr dofs not support
     * tiis dbtb typf
     * @sff Typfs
     */
    void rfgistfrOutPbrbmftfr(int pbrbmftfrIndfx, int sqlTypf, int sdblf)
        tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tif lbst OUT pbrbmftfr rfbd ibd tif vbluf of
     * SQL <dodf>NULL</dodf>.  Notf tibt tiis mftiod siould bf dbllfd only bftfr
     * dblling b gfttfr mftiod; otifrwisf, tifrf is no vbluf to usf in
     * dftfrmining wiftifr it is <dodf>null</dodf> or not.
     *
     * @rfturn <dodf>truf</dodf> if tif lbst pbrbmftfr rfbd wbs SQL
     * <dodf>NULL</dodf>; <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     */
    boolfbn wbsNull() tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd JDBC <dodf>CHAR</dodf>,
     * <dodf>VARCHAR</dodf>, or <dodf>LONGVARCHAR</dodf> pbrbmftfr bs b
     * <dodf>String</dodf> in tif Jbvb progrbmming lbngubgf.
     * <p>
     * For tif fixfd-lfngti typf JDBC <dodf>CHAR</dodf>,
     * tif <dodf>String</dodf> objfdt
     * rfturnfd ibs fxbdtly tif sbmf vbluf tif SQL
     * <dodf>CHAR</dodf> vbluf ibd in tif
     * dbtbbbsf, indluding bny pbdding bddfd by tif dbtbbbsf.
     *
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2,
     * bnd so on
     * @rfturn tif pbrbmftfr vbluf. If tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult
     *         is <dodf>null</dodf>.
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @sff #sftString
     */
    String gftString(int pbrbmftfrIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd JDBC <dodf>BIT</dodf>
     * or <dodf>BOOLEAN</dodf> pbrbmftfr bs b
     * <dodf>boolfbn</dodf> in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2,
     *        bnd so on
     * @rfturn tif pbrbmftfr vbluf.  If tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>fblsf</dodf>.
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @sff #sftBoolfbn
     */
    boolfbn gftBoolfbn(int pbrbmftfrIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd JDBC <dodf>TINYINT</dodf> pbrbmftfr
     * bs b <dodf>bytf</dodf> in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2,
     * bnd so on
     * @rfturn tif pbrbmftfr vbluf.  If tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult
     * is <dodf>0</dodf>.
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @sff #sftBytf
     */
    bytf gftBytf(int pbrbmftfrIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd JDBC <dodf>SMALLINT</dodf> pbrbmftfr
     * bs b <dodf>siort</dodf> in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2,
     * bnd so on
     * @rfturn tif pbrbmftfr vbluf.  If tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult
     * is <dodf>0</dodf>.
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @sff #sftSiort
     */
    siort gftSiort(int pbrbmftfrIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd JDBC <dodf>INTEGER</dodf> pbrbmftfr
     * bs bn <dodf>int</dodf> in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2,
     * bnd so on
     * @rfturn tif pbrbmftfr vbluf.  If tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult
     * is <dodf>0</dodf>.
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @sff #sftInt
     */
    int gftInt(int pbrbmftfrIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd JDBC <dodf>BIGINT</dodf> pbrbmftfr
     * bs b <dodf>long</dodf> in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2,
     * bnd so on
     * @rfturn tif pbrbmftfr vbluf.  If tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult
     * is <dodf>0</dodf>.
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @sff #sftLong
     */
    long gftLong(int pbrbmftfrIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd JDBC <dodf>FLOAT</dodf> pbrbmftfr
     * bs b <dodf>flobt</dodf> in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2,
     *        bnd so on
     * @rfturn tif pbrbmftfr vbluf.  If tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult
     *         is <dodf>0</dodf>.
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @sff #sftFlobt
     */
    flobt gftFlobt(int pbrbmftfrIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd JDBC <dodf>DOUBLE</dodf> pbrbmftfr bs b <dodf>doublf</dodf>
     * in tif Jbvb progrbmming lbngubgf.
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2,
     *        bnd so on
     * @rfturn tif pbrbmftfr vbluf.  If tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult
     *         is <dodf>0</dodf>.
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @sff #sftDoublf
     */
    doublf gftDoublf(int pbrbmftfrIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd JDBC <dodf>NUMERIC</dodf> pbrbmftfr bs b
     * <dodf>jbvb.mbti.BigDfdimbl</dodf> objfdt witi <i>sdblf</i> digits to
     * tif rigit of tif dfdimbl point.
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2,
     *        bnd so on
     * @pbrbm sdblf tif numbfr of digits to tif rigit of tif dfdimbl point
     * @rfturn tif pbrbmftfr vbluf.  If tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult
     *         is <dodf>null</dodf>.
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @dfprfdbtfd usf <dodf>gftBigDfdimbl(int pbrbmftfrIndfx)</dodf>
     *             or <dodf>gftBigDfdimbl(String pbrbmftfrNbmf)</dodf>
     * @sff #sftBigDfdimbl
     */
    @Dfprfdbtfd
    BigDfdimbl gftBigDfdimbl(int pbrbmftfrIndfx, int sdblf)
        tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd JDBC <dodf>BINARY</dodf> or
     * <dodf>VARBINARY</dodf> pbrbmftfr bs bn brrby of <dodf>bytf</dodf>
     * vblufs in tif Jbvb progrbmming lbngubgf.
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2,
     *        bnd so on
     * @rfturn tif pbrbmftfr vbluf.  If tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult
     *         is <dodf>null</dodf>.
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @sff #sftBytfs
     */
    bytf[] gftBytfs(int pbrbmftfrIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd JDBC <dodf>DATE</dodf> pbrbmftfr bs b
     * <dodf>jbvb.sql.Dbtf</dodf> objfdt.
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2,
     *        bnd so on
     * @rfturn tif pbrbmftfr vbluf.  If tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult
     *         is <dodf>null</dodf>.
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @sff #sftDbtf
     */
    jbvb.sql.Dbtf gftDbtf(int pbrbmftfrIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd JDBC <dodf>TIME</dodf> pbrbmftfr bs b
     * <dodf>jbvb.sql.Timf</dodf> objfdt.
     *
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2,
     *        bnd so on
     * @rfturn tif pbrbmftfr vbluf.  If tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult
     *         is <dodf>null</dodf>.
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @sff #sftTimf
     */
    jbvb.sql.Timf gftTimf(int pbrbmftfrIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd JDBC <dodf>TIMESTAMP</dodf> pbrbmftfr bs b
     * <dodf>jbvb.sql.Timfstbmp</dodf> objfdt.
     *
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2,
     *        bnd so on
     * @rfturn tif pbrbmftfr vbluf.  If tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult
     *         is <dodf>null</dodf>.
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @sff #sftTimfstbmp
     */
    jbvb.sql.Timfstbmp gftTimfstbmp(int pbrbmftfrIndfx)
        tirows SQLExdfption;

    //----------------------------------------------------------------------
    // Advbndfd ffbturfs:


    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd pbrbmftfr bs bn <dodf>Objfdt</dodf>
     * in tif Jbvb progrbmming lbngubgf. If tif vbluf is bn SQL <dodf>NULL</dodf>,
     * tif drivfr rfturns b Jbvb <dodf>null</dodf>.
     * <p>
     * Tiis mftiod rfturns b Jbvb objfdt wiosf typf dorrfsponds to tif JDBC
     * typf tibt wbs rfgistfrfd for tiis pbrbmftfr using tif mftiod
     * <dodf>rfgistfrOutPbrbmftfr</dodf>.  By rfgistfring tif tbrgft JDBC
     * typf bs <dodf>jbvb.sql.Typfs.OTHER</dodf>, tiis mftiod dbn bf usfd
     * to rfbd dbtbbbsf-spfdifid bbstrbdt dbtb typfs.
     *
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2,
     *        bnd so on
     * @rfturn A <dodf>jbvb.lbng.Objfdt</dodf> iolding tif OUT pbrbmftfr vbluf
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @sff Typfs
     * @sff #sftObjfdt
     */
    Objfdt gftObjfdt(int pbrbmftfrIndfx) tirows SQLExdfption;


    //--------------------------JDBC 2.0-----------------------------

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd JDBC <dodf>NUMERIC</dodf> pbrbmftfr bs b
     * <dodf>jbvb.mbti.BigDfdimbl</dodf> objfdt witi bs mbny digits to tif
     * rigit of tif dfdimbl point bs tif vbluf dontbins.
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2,
     * bnd so on
     * @rfturn tif pbrbmftfr vbluf in full prfdision.  If tif vbluf is
     * SQL <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>.
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @sff #sftBigDfdimbl
     * @sindf 1.2
     */
    BigDfdimbl gftBigDfdimbl(int pbrbmftfrIndfx) tirows SQLExdfption;

    /**
     * Rfturns bn objfdt rfprfsfnting tif vbluf of OUT pbrbmftfr
     * <dodf>pbrbmftfrIndfx</dodf> bnd usfs <dodf>mbp</dodf> for tif dustom
     * mbpping of tif pbrbmftfr vbluf.
     * <p>
     * Tiis mftiod rfturns b Jbvb objfdt wiosf typf dorrfsponds to tif
     * JDBC typf tibt wbs rfgistfrfd for tiis pbrbmftfr using tif mftiod
     * <dodf>rfgistfrOutPbrbmftfr</dodf>.  By rfgistfring tif tbrgft
     * JDBC typf bs <dodf>jbvb.sql.Typfs.OTHER</dodf>, tiis mftiod dbn
     * bf usfd to rfbd dbtbbbsf-spfdifid bbstrbdt dbtb typfs.
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2, bnd so on
     * @pbrbm mbp tif mbpping from SQL typf nbmfs to Jbvb dlbssfs
     * @rfturn b <dodf>jbvb.lbng.Objfdt</dodf> iolding tif OUT pbrbmftfr vbluf
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #sftObjfdt
     * @sindf 1.2
     */
    Objfdt gftObjfdt(int pbrbmftfrIndfx, jbvb.util.Mbp<String,Clbss<?>> mbp)
        tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd JDBC <dodf>REF(&lt;strudturfd-typf&gt;)</dodf>
     * pbrbmftfr bs b {@link jbvb.sql.Rff} objfdt in tif Jbvb progrbmming lbngubgf.
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2,
     * bnd so on
     * @rfturn tif pbrbmftfr vbluf bs b <dodf>Rff</dodf> objfdt in tif
     * Jbvb progrbmming lbngubgf.  If tif vbluf wbs SQL <dodf>NULL</dodf>, tif vbluf
     * <dodf>null</dodf> is rfturnfd.
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    Rff gftRff (int pbrbmftfrIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd JDBC <dodf>BLOB</dodf> pbrbmftfr bs b
     * {@link jbvb.sql.Blob} objfdt in tif Jbvb progrbmming lbngubgf.
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2, bnd so on
     * @rfturn tif pbrbmftfr vbluf bs b <dodf>Blob</dodf> objfdt in tif
     * Jbvb progrbmming lbngubgf.  If tif vbluf wbs SQL <dodf>NULL</dodf>, tif vbluf
     * <dodf>null</dodf> is rfturnfd.
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    Blob gftBlob (int pbrbmftfrIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd JDBC <dodf>CLOB</dodf> pbrbmftfr bs b
     * <dodf>jbvb.sql.Clob</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2, bnd
     * so on
     * @rfturn tif pbrbmftfr vbluf bs b <dodf>Clob</dodf> objfdt in tif
     * Jbvb progrbmming lbngubgf.  If tif vbluf wbs SQL <dodf>NULL</dodf>, tif
     * vbluf <dodf>null</dodf> is rfturnfd.
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    Clob gftClob (int pbrbmftfrIndfx) tirows SQLExdfption;

    /**
     *
     * Rftrifvfs tif vbluf of tif dfsignbtfd JDBC <dodf>ARRAY</dodf> pbrbmftfr bs bn
     * {@link jbvb.sql.Arrby} objfdt in tif Jbvb progrbmming lbngubgf.
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2, bnd
     * so on
     * @rfturn tif pbrbmftfr vbluf bs bn <dodf>Arrby</dodf> objfdt in
     * tif Jbvb progrbmming lbngubgf.  If tif vbluf wbs SQL <dodf>NULL</dodf>, tif
     * vbluf <dodf>null</dodf> is rfturnfd.
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.2
     */
    Arrby gftArrby (int pbrbmftfrIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd JDBC <dodf>DATE</dodf> pbrbmftfr bs b
     * <dodf>jbvb.sql.Dbtf</dodf> objfdt, using
     * tif givfn <dodf>Cblfndbr</dodf> objfdt
     * to donstrudt tif dbtf.
     * Witi b <dodf>Cblfndbr</dodf> objfdt, tif drivfr
     * dbn dbldulbtf tif dbtf tbking into bddount b dustom timfzonf bnd lodblf.
     * If no <dodf>Cblfndbr</dodf> objfdt is spfdififd, tif drivfr usfs tif
     * dffbult timfzonf bnd lodblf.
     *
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2,
     * bnd so on
     * @pbrbm dbl tif <dodf>Cblfndbr</dodf> objfdt tif drivfr will usf
     *            to donstrudt tif dbtf
     * @rfturn tif pbrbmftfr vbluf.  If tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult
     *         is <dodf>null</dodf>.
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @sff #sftDbtf
     * @sindf 1.2
     */
    jbvb.sql.Dbtf gftDbtf(int pbrbmftfrIndfx, Cblfndbr dbl)
        tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd JDBC <dodf>TIME</dodf> pbrbmftfr bs b
     * <dodf>jbvb.sql.Timf</dodf> objfdt, using
     * tif givfn <dodf>Cblfndbr</dodf> objfdt
     * to donstrudt tif timf.
     * Witi b <dodf>Cblfndbr</dodf> objfdt, tif drivfr
     * dbn dbldulbtf tif timf tbking into bddount b dustom timfzonf bnd lodblf.
     * If no <dodf>Cblfndbr</dodf> objfdt is spfdififd, tif drivfr usfs tif
     * dffbult timfzonf bnd lodblf.
     *
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2,
     * bnd so on
     * @pbrbm dbl tif <dodf>Cblfndbr</dodf> objfdt tif drivfr will usf
     *            to donstrudt tif timf
     * @rfturn tif pbrbmftfr vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult
     *         is <dodf>null</dodf>.
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @sff #sftTimf
     * @sindf 1.2
     */
    jbvb.sql.Timf gftTimf(int pbrbmftfrIndfx, Cblfndbr dbl)
        tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd JDBC <dodf>TIMESTAMP</dodf> pbrbmftfr bs b
     * <dodf>jbvb.sql.Timfstbmp</dodf> objfdt, using
     * tif givfn <dodf>Cblfndbr</dodf> objfdt to donstrudt
     * tif <dodf>Timfstbmp</dodf> objfdt.
     * Witi b <dodf>Cblfndbr</dodf> objfdt, tif drivfr
     * dbn dbldulbtf tif timfstbmp tbking into bddount b dustom timfzonf bnd lodblf.
     * If no <dodf>Cblfndbr</dodf> objfdt is spfdififd, tif drivfr usfs tif
     * dffbult timfzonf bnd lodblf.
     *
     *
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2,
     * bnd so on
     * @pbrbm dbl tif <dodf>Cblfndbr</dodf> objfdt tif drivfr will usf
     *            to donstrudt tif timfstbmp
     * @rfturn tif pbrbmftfr vbluf.  If tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult
     *         is <dodf>null</dodf>.
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @sff #sftTimfstbmp
     * @sindf 1.2
     */
    jbvb.sql.Timfstbmp gftTimfstbmp(int pbrbmftfrIndfx, Cblfndbr dbl)
        tirows SQLExdfption;


    /**
     * Rfgistfrs tif dfsignbtfd output pbrbmftfr.
     * Tiis vfrsion of
     * tif mftiod <dodf>rfgistfrOutPbrbmftfr</dodf>
     * siould bf usfd for b usfr-dffinfd or <dodf>REF</dodf> output pbrbmftfr.  Exbmplfs
     * of usfr-dffinfd typfs indludf: <dodf>STRUCT</dodf>, <dodf>DISTINCT</dodf>,
     * <dodf>JAVA_OBJECT</dodf>, bnd nbmfd brrby typfs.
     *<p>
     * All OUT pbrbmftfrs must bf rfgistfrfd
     * bfforf b storfd prodfdurf is fxfdutfd.
     * <p>  For b usfr-dffinfd pbrbmftfr, tif fully-qublififd SQL
     * typf nbmf of tif pbrbmftfr siould blso bf givfn, wiilf b <dodf>REF</dodf>
     * pbrbmftfr rfquirfs tibt tif fully-qublififd typf nbmf of tif
     * rfffrfndfd typf bf givfn.  A JDBC drivfr tibt dofs not nffd tif
     * typf dodf bnd typf nbmf informbtion mby ignorf it.   To bf portbblf,
     * iowfvfr, bpplidbtions siould blwbys providf tifsf vblufs for
     * usfr-dffinfd bnd <dodf>REF</dodf> pbrbmftfrs.
     *
     * Altiougi it is intfndfd for usfr-dffinfd bnd <dodf>REF</dodf> pbrbmftfrs,
     * tiis mftiod mby bf usfd to rfgistfr b pbrbmftfr of bny JDBC typf.
     * If tif pbrbmftfr dofs not ibvf b usfr-dffinfd or <dodf>REF</dodf> typf, tif
     * <i>typfNbmf</i> pbrbmftfr is ignorfd.
     *
     * <P><B>Notf:</B> Wifn rfbding tif vbluf of bn out pbrbmftfr, you
     * must usf tif gfttfr mftiod wiosf Jbvb typf dorrfsponds to tif
     * pbrbmftfr's rfgistfrfd SQL typf.
     *
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2,...
     * @pbrbm sqlTypf b vbluf from {@link jbvb.sql.Typfs}
     * @pbrbm typfNbmf tif fully-qublififd nbmf of bn SQL strudturfd typf
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if <dodf>sqlTypf</dodf> is
     * b <dodf>ARRAY</dodf>, <dodf>BLOB</dodf>, <dodf>CLOB</dodf>,
     * <dodf>DATALINK</dodf>, <dodf>JAVA_OBJECT</dodf>, <dodf>NCHAR</dodf>,
     * <dodf>NCLOB</dodf>, <dodf>NVARCHAR</dodf>, <dodf>LONGNVARCHAR</dodf>,
     *  <dodf>REF</dodf>, <dodf>ROWID</dodf>, <dodf>SQLXML</dodf>
     * or  <dodf>STRUCT</dodf> dbtb typf bnd tif JDBC drivfr dofs not support
     * tiis dbtb typf
     * @sff Typfs
     * @sindf 1.2
     */
    void rfgistfrOutPbrbmftfr (int pbrbmftfrIndfx, int sqlTypf, String typfNbmf)
        tirows SQLExdfption;

  //--------------------------JDBC 3.0-----------------------------

    /**
     * Rfgistfrs tif OUT pbrbmftfr nbmfd
     * <dodf>pbrbmftfrNbmf</dodf> to tif JDBC typf
     * <dodf>sqlTypf</dodf>.  All OUT pbrbmftfrs must bf rfgistfrfd
     * bfforf b storfd prodfdurf is fxfdutfd.
     * <p>
     * Tif JDBC typf spfdififd by <dodf>sqlTypf</dodf> for bn OUT
     * pbrbmftfr dftfrminfs tif Jbvb typf tibt must bf usfd
     * in tif <dodf>gft</dodf> mftiod to rfbd tif vbluf of tibt pbrbmftfr.
     * <p>
     * If tif JDBC typf fxpfdtfd to bf rfturnfd to tiis output pbrbmftfr
     * is spfdifid to tiis pbrtidulbr dbtbbbsf, <dodf>sqlTypf</dodf>
     * siould bf <dodf>jbvb.sql.Typfs.OTHER</dodf>.  Tif mftiod
     * {@link #gftObjfdt} rftrifvfs tif vbluf.
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm sqlTypf tif JDBC typf dodf dffinfd by <dodf>jbvb.sql.Typfs</dodf>.
     * If tif pbrbmftfr is of JDBC typf <dodf>NUMERIC</dodf>
     * or <dodf>DECIMAL</dodf>, tif vfrsion of
     * <dodf>rfgistfrOutPbrbmftfr</dodf> tibt bddfpts b sdblf vbluf
     * siould bf usfd.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if <dodf>sqlTypf</dodf> is
     * b <dodf>ARRAY</dodf>, <dodf>BLOB</dodf>, <dodf>CLOB</dodf>,
     * <dodf>DATALINK</dodf>, <dodf>JAVA_OBJECT</dodf>, <dodf>NCHAR</dodf>,
     * <dodf>NCLOB</dodf>, <dodf>NVARCHAR</dodf>, <dodf>LONGNVARCHAR</dodf>,
     *  <dodf>REF</dodf>, <dodf>ROWID</dodf>, <dodf>SQLXML</dodf>
     * or  <dodf>STRUCT</dodf> dbtb typf bnd tif JDBC drivfr dofs not support
     * tiis dbtb typf or if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.4
     * @sff Typfs
     */
    void rfgistfrOutPbrbmftfr(String pbrbmftfrNbmf, int sqlTypf)
        tirows SQLExdfption;

    /**
     * Rfgistfrs tif pbrbmftfr nbmfd
     * <dodf>pbrbmftfrNbmf</dodf> to bf of JDBC typf
     * <dodf>sqlTypf</dodf>.  All OUT pbrbmftfrs must bf rfgistfrfd
     * bfforf b storfd prodfdurf is fxfdutfd.
     * <p>
     * Tif JDBC typf spfdififd by <dodf>sqlTypf</dodf> for bn OUT
     * pbrbmftfr dftfrminfs tif Jbvb typf tibt must bf usfd
     * in tif <dodf>gft</dodf> mftiod to rfbd tif vbluf of tibt pbrbmftfr.
     * <p>
     * Tiis vfrsion of <dodf>rfgistfrOutPbrbmftfr</dodf> siould bf
     * usfd wifn tif pbrbmftfr is of JDBC typf <dodf>NUMERIC</dodf>
     * or <dodf>DECIMAL</dodf>.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm sqlTypf SQL typf dodf dffinfd by <dodf>jbvb.sql.Typfs</dodf>.
     * @pbrbm sdblf tif dfsirfd numbfr of digits to tif rigit of tif
     * dfdimbl point.  It must bf grfbtfr tibn or fqubl to zfro.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if <dodf>sqlTypf</dodf> is
     * b <dodf>ARRAY</dodf>, <dodf>BLOB</dodf>, <dodf>CLOB</dodf>,
     * <dodf>DATALINK</dodf>, <dodf>JAVA_OBJECT</dodf>, <dodf>NCHAR</dodf>,
     * <dodf>NCLOB</dodf>, <dodf>NVARCHAR</dodf>, <dodf>LONGNVARCHAR</dodf>,
     *  <dodf>REF</dodf>, <dodf>ROWID</dodf>, <dodf>SQLXML</dodf>
     * or  <dodf>STRUCT</dodf> dbtb typf bnd tif JDBC drivfr dofs not support
     * tiis dbtb typf or if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.4
     * @sff Typfs
     */
    void rfgistfrOutPbrbmftfr(String pbrbmftfrNbmf, int sqlTypf, int sdblf)
        tirows SQLExdfption;

    /**
     * Rfgistfrs tif dfsignbtfd output pbrbmftfr.  Tiis vfrsion of
     * tif mftiod <dodf>rfgistfrOutPbrbmftfr</dodf>
     * siould bf usfd for b usfr-nbmfd or REF output pbrbmftfr.  Exbmplfs
     * of usfr-nbmfd typfs indludf: STRUCT, DISTINCT, JAVA_OBJECT, bnd
     * nbmfd brrby typfs.
     *<p>
     * All OUT pbrbmftfrs must bf rfgistfrfd
     * bfforf b storfd prodfdurf is fxfdutfd.
     * <p>
     * For b usfr-nbmfd pbrbmftfr tif fully-qublififd SQL
     * typf nbmf of tif pbrbmftfr siould blso bf givfn, wiilf b REF
     * pbrbmftfr rfquirfs tibt tif fully-qublififd typf nbmf of tif
     * rfffrfndfd typf bf givfn.  A JDBC drivfr tibt dofs not nffd tif
     * typf dodf bnd typf nbmf informbtion mby ignorf it.   To bf portbblf,
     * iowfvfr, bpplidbtions siould blwbys providf tifsf vblufs for
     * usfr-nbmfd bnd REF pbrbmftfrs.
     *
     * Altiougi it is intfndfd for usfr-nbmfd bnd REF pbrbmftfrs,
     * tiis mftiod mby bf usfd to rfgistfr b pbrbmftfr of bny JDBC typf.
     * If tif pbrbmftfr dofs not ibvf b usfr-nbmfd or REF typf, tif
     * typfNbmf pbrbmftfr is ignorfd.
     *
     * <P><B>Notf:</B> Wifn rfbding tif vbluf of bn out pbrbmftfr, you
     * must usf tif <dodf>gftXXX</dodf> mftiod wiosf Jbvb typf XXX dorrfsponds to tif
     * pbrbmftfr's rfgistfrfd SQL typf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm sqlTypf b vbluf from {@link jbvb.sql.Typfs}
     * @pbrbm typfNbmf tif fully-qublififd nbmf of bn SQL strudturfd typf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if <dodf>sqlTypf</dodf> is
     * b <dodf>ARRAY</dodf>, <dodf>BLOB</dodf>, <dodf>CLOB</dodf>,
     * <dodf>DATALINK</dodf>, <dodf>JAVA_OBJECT</dodf>, <dodf>NCHAR</dodf>,
     * <dodf>NCLOB</dodf>, <dodf>NVARCHAR</dodf>, <dodf>LONGNVARCHAR</dodf>,
     *  <dodf>REF</dodf>, <dodf>ROWID</dodf>, <dodf>SQLXML</dodf>
     * or  <dodf>STRUCT</dodf> dbtb typf bnd tif JDBC drivfr dofs not support
     * tiis dbtb typf or if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff Typfs
     * @sindf 1.4
     */
    void rfgistfrOutPbrbmftfr (String pbrbmftfrNbmf, int sqlTypf, String typfNbmf)
        tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd JDBC <dodf>DATALINK</dodf> pbrbmftfr bs b
     * <dodf>jbvb.nft.URL</dodf> objfdt.
     *
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2,...
     * @rfturn b <dodf>jbvb.nft.URL</dodf> objfdt tibt rfprfsfnts tif
     *         JDBC <dodf>DATALINK</dodf> vbluf usfd bs tif dfsignbtfd
     *         pbrbmftfr
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs,
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>,
     *            or if tif URL bfing rfturnfd is
     *            not b vblid URL on tif Jbvb plbtform
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #sftURL
     * @sindf 1.4
     */
    jbvb.nft.URL gftURL(int pbrbmftfrIndfx) tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>jbvb.nft.URL</dodf> objfdt.
     * Tif drivfr donvfrts tiis to bn SQL <dodf>DATALINK</dodf> vbluf wifn
     * it sfnds it to tif dbtbbbsf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm vbl tif pbrbmftfr vbluf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs;
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     *            or if b URL is mblformfd
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #gftURL
     * @sindf 1.4
     */
    void sftURL(String pbrbmftfrNbmf, jbvb.nft.URL vbl) tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to SQL <dodf>NULL</dodf>.
     *
     * <P><B>Notf:</B> You must spfdify tif pbrbmftfr's SQL typf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm sqlTypf tif SQL typf dodf dffinfd in <dodf>jbvb.sql.Typfs</dodf>
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.4
     */
    void sftNull(String pbrbmftfrNbmf, int sqlTypf) tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn Jbvb <dodf>boolfbn</dodf> vbluf.
     * Tif drivfr donvfrts tiis
     * to bn SQL <dodf>BIT</dodf> or <dodf>BOOLEAN</dodf> vbluf wifn it sfnds it to tif dbtbbbsf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif pbrbmftfr vbluf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @sff #gftBoolfbn
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.4
     */
    void sftBoolfbn(String pbrbmftfrNbmf, boolfbn x) tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn Jbvb <dodf>bytf</dodf> vbluf.
     * Tif drivfr donvfrts tiis
     * to bn SQL <dodf>TINYINT</dodf> vbluf wifn it sfnds it to tif dbtbbbsf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif pbrbmftfr vbluf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #gftBytf
     * @sindf 1.4
     */
    void sftBytf(String pbrbmftfrNbmf, bytf x) tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn Jbvb <dodf>siort</dodf> vbluf.
     * Tif drivfr donvfrts tiis
     * to bn SQL <dodf>SMALLINT</dodf> vbluf wifn it sfnds it to tif dbtbbbsf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif pbrbmftfr vbluf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #gftSiort
     * @sindf 1.4
     */
    void sftSiort(String pbrbmftfrNbmf, siort x) tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn Jbvb <dodf>int</dodf> vbluf.
     * Tif drivfr donvfrts tiis
     * to bn SQL <dodf>INTEGER</dodf> vbluf wifn it sfnds it to tif dbtbbbsf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif pbrbmftfr vbluf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #gftInt
     * @sindf 1.4
     */
    void sftInt(String pbrbmftfrNbmf, int x) tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn Jbvb <dodf>long</dodf> vbluf.
     * Tif drivfr donvfrts tiis
     * to bn SQL <dodf>BIGINT</dodf> vbluf wifn it sfnds it to tif dbtbbbsf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif pbrbmftfr vbluf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #gftLong
     * @sindf 1.4
     */
    void sftLong(String pbrbmftfrNbmf, long x) tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn Jbvb <dodf>flobt</dodf> vbluf.
     * Tif drivfr donvfrts tiis
     * to bn SQL <dodf>FLOAT</dodf> vbluf wifn it sfnds it to tif dbtbbbsf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif pbrbmftfr vbluf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #gftFlobt
     * @sindf 1.4
     */
    void sftFlobt(String pbrbmftfrNbmf, flobt x) tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn Jbvb <dodf>doublf</dodf> vbluf.
     * Tif drivfr donvfrts tiis
     * to bn SQL <dodf>DOUBLE</dodf> vbluf wifn it sfnds it to tif dbtbbbsf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif pbrbmftfr vbluf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #gftDoublf
     * @sindf 1.4
     */
    void sftDoublf(String pbrbmftfrNbmf, doublf x) tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn
     * <dodf>jbvb.mbti.BigDfdimbl</dodf> vbluf.
     * Tif drivfr donvfrts tiis to bn SQL <dodf>NUMERIC</dodf> vbluf wifn
     * it sfnds it to tif dbtbbbsf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif pbrbmftfr vbluf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #gftBigDfdimbl
     * @sindf 1.4
     */
    void sftBigDfdimbl(String pbrbmftfrNbmf, BigDfdimbl x) tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn Jbvb <dodf>String</dodf> vbluf.
     * Tif drivfr donvfrts tiis
     * to bn SQL <dodf>VARCHAR</dodf> or <dodf>LONGVARCHAR</dodf> vbluf
     * (dfpfnding on tif brgumfnt's
     * sizf rflbtivf to tif drivfr's limits on <dodf>VARCHAR</dodf> vblufs)
     * wifn it sfnds it to tif dbtbbbsf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif pbrbmftfr vbluf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #gftString
     * @sindf 1.4
     */
    void sftString(String pbrbmftfrNbmf, String x) tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn Jbvb brrby of bytfs.
     * Tif drivfr donvfrts tiis to bn SQL <dodf>VARBINARY</dodf> or
     * <dodf>LONGVARBINARY</dodf> (dfpfnding on tif brgumfnt's sizf rflbtivf
     * to tif drivfr's limits on <dodf>VARBINARY</dodf> vblufs) wifn it sfnds
     * it to tif dbtbbbsf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif pbrbmftfr vbluf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #gftBytfs
     * @sindf 1.4
     */
    void sftBytfs(String pbrbmftfrNbmf, bytf x[]) tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>jbvb.sql.Dbtf</dodf> vbluf
     * using tif dffbult timf zonf of tif virtubl mbdiinf tibt is running
     * tif bpplidbtion.
     * Tif drivfr donvfrts tiis
     * to bn SQL <dodf>DATE</dodf> vbluf wifn it sfnds it to tif dbtbbbsf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif pbrbmftfr vbluf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #gftDbtf
     * @sindf 1.4
     */
    void sftDbtf(String pbrbmftfrNbmf, jbvb.sql.Dbtf x)
        tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>jbvb.sql.Timf</dodf> vbluf.
     * Tif drivfr donvfrts tiis
     * to bn SQL <dodf>TIME</dodf> vbluf wifn it sfnds it to tif dbtbbbsf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif pbrbmftfr vbluf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #gftTimf
     * @sindf 1.4
     */
    void sftTimf(String pbrbmftfrNbmf, jbvb.sql.Timf x)
        tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>jbvb.sql.Timfstbmp</dodf> vbluf.
     * Tif drivfr
     * donvfrts tiis to bn SQL <dodf>TIMESTAMP</dodf> vbluf wifn it sfnds it to tif
     * dbtbbbsf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif pbrbmftfr vbluf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #gftTimfstbmp
     * @sindf 1.4
     */
    void sftTimfstbmp(String pbrbmftfrNbmf, jbvb.sql.Timfstbmp x)
        tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn input strfbm, wiidi will ibvf
     * tif spfdififd numbfr of bytfs.
     * Wifn b vfry lbrgf ASCII vbluf is input to b <dodf>LONGVARCHAR</dodf>
     * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
     * <dodf>jbvb.io.InputStrfbm</dodf>. Dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-filf is rfbdifd.  Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from ASCII to tif dbtbbbsf dibr formbt.
     *
     * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
     * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
     * stbndbrd intfrfbdf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif Jbvb input strfbm tibt dontbins tif ASCII pbrbmftfr vbluf
     * @pbrbm lfngti tif numbfr of bytfs in tif strfbm
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.4
     */
    void sftAsdiiStrfbm(String pbrbmftfrNbmf, jbvb.io.InputStrfbm x, int lfngti)
        tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn input strfbm, wiidi will ibvf
     * tif spfdififd numbfr of bytfs.
     * Wifn b vfry lbrgf binbry vbluf is input to b <dodf>LONGVARBINARY</dodf>
     * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
     * <dodf>jbvb.io.InputStrfbm</dodf> objfdt. Tif dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-filf is rfbdifd.
     *
     * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
     * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
     * stbndbrd intfrfbdf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif jbvb input strfbm wiidi dontbins tif binbry pbrbmftfr vbluf
     * @pbrbm lfngti tif numbfr of bytfs in tif strfbm
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.4
     */
    void sftBinbryStrfbm(String pbrbmftfrNbmf, jbvb.io.InputStrfbm x,
                         int lfngti) tirows SQLExdfption;

    /**
     * Sfts tif vbluf of tif dfsignbtfd pbrbmftfr witi tif givfn objfdt.
     *
     * <p>Tif givfn Jbvb objfdt will bf donvfrtfd to tif givfn tbrgftSqlTypf
     * bfforf bfing sfnt to tif dbtbbbsf.
     *
     * If tif objfdt ibs b dustom mbpping (is of b dlbss implfmfnting tif
     * intfrfbdf <dodf>SQLDbtb</dodf>),
     * tif JDBC drivfr siould dbll tif mftiod <dodf>SQLDbtb.writfSQL</dodf> to writf it
     * to tif SQL dbtb strfbm.
     * If, on tif otifr ibnd, tif objfdt is of b dlbss implfmfnting
     * <dodf>Rff</dodf>, <dodf>Blob</dodf>, <dodf>Clob</dodf>,  <dodf>NClob</dodf>,
     *  <dodf>Strudt</dodf>, <dodf>jbvb.nft.URL</dodf>,
     * or <dodf>Arrby</dodf>, tif drivfr siould pbss it to tif dbtbbbsf bs b
     * vbluf of tif dorrfsponding SQL typf.
     * <P>
     * Notf tibt tiis mftiod mby bf usfd to pbss dbtbtbbbsf-
     * spfdifid bbstrbdt dbtb typfs.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif objfdt dontbining tif input pbrbmftfr vbluf
     * @pbrbm tbrgftSqlTypf tif SQL typf (bs dffinfd in jbvb.sql.Typfs) to bf
     * sfnt to tif dbtbbbsf. Tif sdblf brgumfnt mby furtifr qublify tiis typf.
     * @pbrbm sdblf for jbvb.sql.Typfs.DECIMAL or jbvb.sql.Typfs.NUMERIC typfs,
     *          tiis is tif numbfr of digits bftfr tif dfdimbl point.  For bll otifr
     *          typfs, tiis vbluf will bf ignorfd.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if
     * tif JDBC drivfr dofs not support tif spfdififd tbrgftSqlTypf
     * @sff Typfs
     * @sff #gftObjfdt
     * @sindf 1.4
     */
    void sftObjfdt(String pbrbmftfrNbmf, Objfdt x, int tbrgftSqlTypf, int sdblf)
        tirows SQLExdfption;

    /**
     * Sfts tif vbluf of tif dfsignbtfd pbrbmftfr witi tif givfn objfdt.
     *
     * Tiis mftiod is similbr to {@link #sftObjfdt(String pbrbmftfrNbmf,
     * Objfdt x, int tbrgftSqlTypf, int sdblfOrLfngti)},
     * fxdfpt tibt it bssumfs b sdblf of zfro.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif objfdt dontbining tif input pbrbmftfr vbluf
     * @pbrbm tbrgftSqlTypf tif SQL typf (bs dffinfd in jbvb.sql.Typfs) to bf
     *                      sfnt to tif dbtbbbsf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if
     * tif JDBC drivfr dofs not support tif spfdififd tbrgftSqlTypf
     * @sff #gftObjfdt
     * @sindf 1.4
     */
    void sftObjfdt(String pbrbmftfrNbmf, Objfdt x, int tbrgftSqlTypf)
        tirows SQLExdfption;

    /**
     * Sfts tif vbluf of tif dfsignbtfd pbrbmftfr witi tif givfn objfdt.
     *
     * <p>Tif JDBC spfdifidbtion spfdififs b stbndbrd mbpping from
     * Jbvb <dodf>Objfdt</dodf> typfs to SQL typfs.  Tif givfn brgumfnt
     * will bf donvfrtfd to tif dorrfsponding SQL typf bfforf bfing
     * sfnt to tif dbtbbbsf.
     * <p>Notf tibt tiis mftiod mby bf usfd to pbss dbtbtbbbsf-
     * spfdifid bbstrbdt dbtb typfs, by using b drivfr-spfdifid Jbvb
     * typf.
     *
     * If tif objfdt is of b dlbss implfmfnting tif intfrfbdf <dodf>SQLDbtb</dodf>,
     * tif JDBC drivfr siould dbll tif mftiod <dodf>SQLDbtb.writfSQL</dodf>
     * to writf it to tif SQL dbtb strfbm.
     * If, on tif otifr ibnd, tif objfdt is of b dlbss implfmfnting
     * <dodf>Rff</dodf>, <dodf>Blob</dodf>, <dodf>Clob</dodf>,  <dodf>NClob</dodf>,
     *  <dodf>Strudt</dodf>, <dodf>jbvb.nft.URL</dodf>,
     * or <dodf>Arrby</dodf>, tif drivfr siould pbss it to tif dbtbbbsf bs b
     * vbluf of tif dorrfsponding SQL typf.
     * <P>
     * Tiis mftiod tirows bn fxdfption if tifrf is bn bmbiguity, for fxbmplf, if tif
     * objfdt is of b dlbss implfmfnting morf tibn onf of tif intfrfbdfs nbmfd bbovf.
     * <p>
     *<b>Notf:</b> Not bll dbtbbbsfs bllow for b non-typfd Null to bf sfnt to
     * tif bbdkfnd. For mbximum portbbility, tif <dodf>sftNull</dodf> or tif
     * <dodf>sftObjfdt(String pbrbmftfrNbmf, Objfdt x, int sqlTypf)</dodf>
     * mftiod siould bf usfd
     * instfbd of <dodf>sftObjfdt(String pbrbmftfrNbmf, Objfdt x)</dodf>.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif objfdt dontbining tif input pbrbmftfr vbluf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs,
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf> or if tif givfn
     *            <dodf>Objfdt</dodf> pbrbmftfr is bmbiguous
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #gftObjfdt
     * @sindf 1.4
     */
    void sftObjfdt(String pbrbmftfrNbmf, Objfdt x) tirows SQLExdfption;


    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>Rfbdfr</dodf>
     * objfdt, wiidi is tif givfn numbfr of dibrbdtfrs long.
     * Wifn b vfry lbrgf UNICODE vbluf is input to b <dodf>LONGVARCHAR</dodf>
     * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt. Tif dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-filf is rfbdifd.  Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from UNICODE to tif dbtbbbsf dibr formbt.
     *
     * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
     * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
     * stbndbrd intfrfbdf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm rfbdfr tif <dodf>jbvb.io.Rfbdfr</dodf> objfdt tibt
     *        dontbins tif UNICODE dbtb usfd bs tif dfsignbtfd pbrbmftfr
     * @pbrbm lfngti tif numbfr of dibrbdtfrs in tif strfbm
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.4
     */
    void sftCibrbdtfrStrfbm(String pbrbmftfrNbmf,
                            jbvb.io.Rfbdfr rfbdfr,
                            int lfngti) tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>jbvb.sql.Dbtf</dodf> vbluf,
     * using tif givfn <dodf>Cblfndbr</dodf> objfdt.  Tif drivfr usfs
     * tif <dodf>Cblfndbr</dodf> objfdt to donstrudt bn SQL <dodf>DATE</dodf> vbluf,
     * wiidi tif drivfr tifn sfnds to tif dbtbbbsf.  Witi b
     * b <dodf>Cblfndbr</dodf> objfdt, tif drivfr dbn dbldulbtf tif dbtf
     * tbking into bddount b dustom timfzonf.  If no
     * <dodf>Cblfndbr</dodf> objfdt is spfdififd, tif drivfr usfs tif dffbult
     * timfzonf, wiidi is tibt of tif virtubl mbdiinf running tif bpplidbtion.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif pbrbmftfr vbluf
     * @pbrbm dbl tif <dodf>Cblfndbr</dodf> objfdt tif drivfr will usf
     *            to donstrudt tif dbtf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #gftDbtf
     * @sindf 1.4
     */
    void sftDbtf(String pbrbmftfrNbmf, jbvb.sql.Dbtf x, Cblfndbr dbl)
        tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>jbvb.sql.Timf</dodf> vbluf,
     * using tif givfn <dodf>Cblfndbr</dodf> objfdt.  Tif drivfr usfs
     * tif <dodf>Cblfndbr</dodf> objfdt to donstrudt bn SQL <dodf>TIME</dodf> vbluf,
     * wiidi tif drivfr tifn sfnds to tif dbtbbbsf.  Witi b
     * b <dodf>Cblfndbr</dodf> objfdt, tif drivfr dbn dbldulbtf tif timf
     * tbking into bddount b dustom timfzonf.  If no
     * <dodf>Cblfndbr</dodf> objfdt is spfdififd, tif drivfr usfs tif dffbult
     * timfzonf, wiidi is tibt of tif virtubl mbdiinf running tif bpplidbtion.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif pbrbmftfr vbluf
     * @pbrbm dbl tif <dodf>Cblfndbr</dodf> objfdt tif drivfr will usf
     *            to donstrudt tif timf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #gftTimf
     * @sindf 1.4
     */
    void sftTimf(String pbrbmftfrNbmf, jbvb.sql.Timf x, Cblfndbr dbl)
        tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>jbvb.sql.Timfstbmp</dodf> vbluf,
     * using tif givfn <dodf>Cblfndbr</dodf> objfdt.  Tif drivfr usfs
     * tif <dodf>Cblfndbr</dodf> objfdt to donstrudt bn SQL <dodf>TIMESTAMP</dodf> vbluf,
     * wiidi tif drivfr tifn sfnds to tif dbtbbbsf.  Witi b
     * b <dodf>Cblfndbr</dodf> objfdt, tif drivfr dbn dbldulbtf tif timfstbmp
     * tbking into bddount b dustom timfzonf.  If no
     * <dodf>Cblfndbr</dodf> objfdt is spfdififd, tif drivfr usfs tif dffbult
     * timfzonf, wiidi is tibt of tif virtubl mbdiinf running tif bpplidbtion.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif pbrbmftfr vbluf
     * @pbrbm dbl tif <dodf>Cblfndbr</dodf> objfdt tif drivfr will usf
     *            to donstrudt tif timfstbmp
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #gftTimfstbmp
     * @sindf 1.4
     */
    void sftTimfstbmp(String pbrbmftfrNbmf, jbvb.sql.Timfstbmp x, Cblfndbr dbl)
        tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to SQL <dodf>NULL</dodf>.
     * Tiis vfrsion of tif mftiod <dodf>sftNull</dodf> siould
     * bf usfd for usfr-dffinfd typfs bnd REF typf pbrbmftfrs.  Exbmplfs
     * of usfr-dffinfd typfs indludf: STRUCT, DISTINCT, JAVA_OBJECT, bnd
     * nbmfd brrby typfs.
     *
     * <P><B>Notf:</B> To bf portbblf, bpplidbtions must givf tif
     * SQL typf dodf bnd tif fully-qublififd SQL typf nbmf wifn spfdifying
     * b NULL usfr-dffinfd or REF pbrbmftfr.  In tif dbsf of b usfr-dffinfd typf
     * tif nbmf is tif typf nbmf of tif pbrbmftfr itsflf.  For b REF
     * pbrbmftfr, tif nbmf is tif typf nbmf of tif rfffrfndfd typf.
     * <p>
     * Altiougi it is intfndfd for usfr-dffinfd bnd Rff pbrbmftfrs,
     * tiis mftiod mby bf usfd to sft b null pbrbmftfr of bny JDBC typf.
     * If tif pbrbmftfr dofs not ibvf b usfr-dffinfd or REF typf, tif givfn
     * typfNbmf is ignorfd.
     *
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm sqlTypf b vbluf from <dodf>jbvb.sql.Typfs</dodf>
     * @pbrbm typfNbmf tif fully-qublififd nbmf of bn SQL usfr-dffinfd typf;
     *        ignorfd if tif pbrbmftfr is not b usfr-dffinfd typf or
     *        SQL <dodf>REF</dodf> vbluf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.4
     */
    void sftNull (String pbrbmftfrNbmf, int sqlTypf, String typfNbmf)
        tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of b JDBC <dodf>CHAR</dodf>, <dodf>VARCHAR</dodf>,
     * or <dodf>LONGVARCHAR</dodf> pbrbmftfr bs b <dodf>String</dodf> in
     * tif Jbvb progrbmming lbngubgf.
     * <p>
     * For tif fixfd-lfngti typf JDBC <dodf>CHAR</dodf>,
     * tif <dodf>String</dodf> objfdt
     * rfturnfd ibs fxbdtly tif sbmf vbluf tif SQL
     * <dodf>CHAR</dodf> vbluf ibd in tif
     * dbtbbbsf, indluding bny pbdding bddfd by tif dbtbbbsf.
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @rfturn tif pbrbmftfr vbluf. If tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult
     * is <dodf>null</dodf>.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #sftString
     * @sindf 1.4
     */
    String gftString(String pbrbmftfrNbmf) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of b JDBC <dodf>BIT</dodf> or <dodf>BOOLEAN</dodf>
     * pbrbmftfr bs b
     * <dodf>boolfbn</dodf> in tif Jbvb progrbmming lbngubgf.
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @rfturn tif pbrbmftfr vbluf.  If tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult
     * is <dodf>fblsf</dodf>.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #sftBoolfbn
     * @sindf 1.4
     */
    boolfbn gftBoolfbn(String pbrbmftfrNbmf) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of b JDBC <dodf>TINYINT</dodf> pbrbmftfr bs b <dodf>bytf</dodf>
     * in tif Jbvb progrbmming lbngubgf.
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @rfturn tif pbrbmftfr vbluf.  If tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult
     * is <dodf>0</dodf>.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #sftBytf
     * @sindf 1.4
     */
    bytf gftBytf(String pbrbmftfrNbmf) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of b JDBC <dodf>SMALLINT</dodf> pbrbmftfr bs b <dodf>siort</dodf>
     * in tif Jbvb progrbmming lbngubgf.
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @rfturn tif pbrbmftfr vbluf.  If tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult
     * is <dodf>0</dodf>.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #sftSiort
     * @sindf 1.4
     */
    siort gftSiort(String pbrbmftfrNbmf) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of b JDBC <dodf>INTEGER</dodf> pbrbmftfr bs bn <dodf>int</dodf>
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @rfturn tif pbrbmftfr vbluf.  If tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>0</dodf>.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #sftInt
     * @sindf 1.4
     */
    int gftInt(String pbrbmftfrNbmf) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of b JDBC <dodf>BIGINT</dodf> pbrbmftfr bs b <dodf>long</dodf>
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @rfturn tif pbrbmftfr vbluf.  If tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>0</dodf>.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #sftLong
     * @sindf 1.4
     */
    long gftLong(String pbrbmftfrNbmf) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of b JDBC <dodf>FLOAT</dodf> pbrbmftfr bs b <dodf>flobt</dodf>
     * in tif Jbvb progrbmming lbngubgf.
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @rfturn tif pbrbmftfr vbluf.  If tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>0</dodf>.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #sftFlobt
     * @sindf 1.4
     */
    flobt gftFlobt(String pbrbmftfrNbmf) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of b JDBC <dodf>DOUBLE</dodf> pbrbmftfr bs b <dodf>doublf</dodf>
     * in tif Jbvb progrbmming lbngubgf.
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @rfturn tif pbrbmftfr vbluf.  If tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>0</dodf>.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #sftDoublf
     * @sindf 1.4
     */
    doublf gftDoublf(String pbrbmftfrNbmf) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of b JDBC <dodf>BINARY</dodf> or <dodf>VARBINARY</dodf>
     * pbrbmftfr bs bn brrby of <dodf>bytf</dodf> vblufs in tif Jbvb
     * progrbmming lbngubgf.
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @rfturn tif pbrbmftfr vbluf.  If tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult is
     *  <dodf>null</dodf>.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #sftBytfs
     * @sindf 1.4
     */
    bytf[] gftBytfs(String pbrbmftfrNbmf) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of b JDBC <dodf>DATE</dodf> pbrbmftfr bs b
     * <dodf>jbvb.sql.Dbtf</dodf> objfdt.
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @rfturn tif pbrbmftfr vbluf.  If tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult
     * is <dodf>null</dodf>.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #sftDbtf
     * @sindf 1.4
     */
    jbvb.sql.Dbtf gftDbtf(String pbrbmftfrNbmf) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of b JDBC <dodf>TIME</dodf> pbrbmftfr bs b
     * <dodf>jbvb.sql.Timf</dodf> objfdt.
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @rfturn tif pbrbmftfr vbluf.  If tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult
     * is <dodf>null</dodf>.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #sftTimf
     * @sindf 1.4
     */
    jbvb.sql.Timf gftTimf(String pbrbmftfrNbmf) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of b JDBC <dodf>TIMESTAMP</dodf> pbrbmftfr bs b
     * <dodf>jbvb.sql.Timfstbmp</dodf> objfdt.
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @rfturn tif pbrbmftfr vbluf.  If tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult
     * is <dodf>null</dodf>.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #sftTimfstbmp
     * @sindf 1.4
     */
    jbvb.sql.Timfstbmp gftTimfstbmp(String pbrbmftfrNbmf) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of b pbrbmftfr bs bn <dodf>Objfdt</dodf> in tif Jbvb
     * progrbmming lbngubgf. If tif vbluf is bn SQL <dodf>NULL</dodf>, tif
     * drivfr rfturns b Jbvb <dodf>null</dodf>.
     * <p>
     * Tiis mftiod rfturns b Jbvb objfdt wiosf typf dorrfsponds to tif JDBC
     * typf tibt wbs rfgistfrfd for tiis pbrbmftfr using tif mftiod
     * <dodf>rfgistfrOutPbrbmftfr</dodf>.  By rfgistfring tif tbrgft JDBC
     * typf bs <dodf>jbvb.sql.Typfs.OTHER</dodf>, tiis mftiod dbn bf usfd
     * to rfbd dbtbbbsf-spfdifid bbstrbdt dbtb typfs.
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @rfturn A <dodf>jbvb.lbng.Objfdt</dodf> iolding tif OUT pbrbmftfr vbluf.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff Typfs
     * @sff #sftObjfdt
     * @sindf 1.4
     */
    Objfdt gftObjfdt(String pbrbmftfrNbmf) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of b JDBC <dodf>NUMERIC</dodf> pbrbmftfr bs b
     * <dodf>jbvb.mbti.BigDfdimbl</dodf> objfdt witi bs mbny digits to tif
     * rigit of tif dfdimbl point bs tif vbluf dontbins.
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @rfturn tif pbrbmftfr vbluf in full prfdision.  If tif vbluf is
     * SQL <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr;  if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #sftBigDfdimbl
     * @sindf 1.4
     */
    BigDfdimbl gftBigDfdimbl(String pbrbmftfrNbmf) tirows SQLExdfption;

    /**
     * Rfturns bn objfdt rfprfsfnting tif vbluf of OUT pbrbmftfr
     * <dodf>pbrbmftfrNbmf</dodf> bnd usfs <dodf>mbp</dodf> for tif dustom
     * mbpping of tif pbrbmftfr vbluf.
     * <p>
     * Tiis mftiod rfturns b Jbvb objfdt wiosf typf dorrfsponds to tif
     * JDBC typf tibt wbs rfgistfrfd for tiis pbrbmftfr using tif mftiod
     * <dodf>rfgistfrOutPbrbmftfr</dodf>.  By rfgistfring tif tbrgft
     * JDBC typf bs <dodf>jbvb.sql.Typfs.OTHER</dodf>, tiis mftiod dbn
     * bf usfd to rfbd dbtbbbsf-spfdifid bbstrbdt dbtb typfs.
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm mbp tif mbpping from SQL typf nbmfs to Jbvb dlbssfs
     * @rfturn b <dodf>jbvb.lbng.Objfdt</dodf> iolding tif OUT pbrbmftfr vbluf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #sftObjfdt
     * @sindf 1.4
     */
    Objfdt gftObjfdt(String pbrbmftfrNbmf, jbvb.util.Mbp<String,Clbss<?>> mbp)
      tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of b JDBC <dodf>REF(&lt;strudturfd-typf&gt;)</dodf>
     * pbrbmftfr bs b {@link jbvb.sql.Rff} objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @rfturn tif pbrbmftfr vbluf bs b <dodf>Rff</dodf> objfdt in tif
     *         Jbvb progrbmming lbngubgf.  If tif vbluf wbs SQL <dodf>NULL</dodf>,
     *         tif vbluf <dodf>null</dodf> is rfturnfd.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.4
     */
    Rff gftRff (String pbrbmftfrNbmf) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of b JDBC <dodf>BLOB</dodf> pbrbmftfr bs b
     * {@link jbvb.sql.Blob} objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @rfturn tif pbrbmftfr vbluf bs b <dodf>Blob</dodf> objfdt in tif
     *         Jbvb progrbmming lbngubgf.  If tif vbluf wbs SQL <dodf>NULL</dodf>,
     *         tif vbluf <dodf>null</dodf> is rfturnfd.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.4
     */
    Blob gftBlob (String pbrbmftfrNbmf) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of b JDBC <dodf>CLOB</dodf> pbrbmftfr bs b
     * <dodf>jbvb.sql.Clob</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @rfturn tif pbrbmftfr vbluf bs b <dodf>Clob</dodf> objfdt in tif
     *         Jbvb progrbmming lbngubgf.  If tif vbluf wbs SQL <dodf>NULL</dodf>,
     *         tif vbluf <dodf>null</dodf> is rfturnfd.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.4
     */
    Clob gftClob (String pbrbmftfrNbmf) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of b JDBC <dodf>ARRAY</dodf> pbrbmftfr bs bn
     * {@link jbvb.sql.Arrby} objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @rfturn tif pbrbmftfr vbluf bs bn <dodf>Arrby</dodf> objfdt in
     *         Jbvb progrbmming lbngubgf.  If tif vbluf wbs SQL <dodf>NULL</dodf>,
     *         tif vbluf <dodf>null</dodf> is rfturnfd.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.4
     */
    Arrby gftArrby (String pbrbmftfrNbmf) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of b JDBC <dodf>DATE</dodf> pbrbmftfr bs b
     * <dodf>jbvb.sql.Dbtf</dodf> objfdt, using
     * tif givfn <dodf>Cblfndbr</dodf> objfdt
     * to donstrudt tif dbtf.
     * Witi b <dodf>Cblfndbr</dodf> objfdt, tif drivfr
     * dbn dbldulbtf tif dbtf tbking into bddount b dustom timfzonf bnd lodblf.
     * If no <dodf>Cblfndbr</dodf> objfdt is spfdififd, tif drivfr usfs tif
     * dffbult timfzonf bnd lodblf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm dbl tif <dodf>Cblfndbr</dodf> objfdt tif drivfr will usf
     *            to donstrudt tif dbtf
     * @rfturn tif pbrbmftfr vbluf.  If tif vbluf is SQL <dodf>NULL</dodf>,
     * tif rfsult is <dodf>null</dodf>.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #sftDbtf
     * @sindf 1.4
     */
    jbvb.sql.Dbtf gftDbtf(String pbrbmftfrNbmf, Cblfndbr dbl)
        tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of b JDBC <dodf>TIME</dodf> pbrbmftfr bs b
     * <dodf>jbvb.sql.Timf</dodf> objfdt, using
     * tif givfn <dodf>Cblfndbr</dodf> objfdt
     * to donstrudt tif timf.
     * Witi b <dodf>Cblfndbr</dodf> objfdt, tif drivfr
     * dbn dbldulbtf tif timf tbking into bddount b dustom timfzonf bnd lodblf.
     * If no <dodf>Cblfndbr</dodf> objfdt is spfdififd, tif drivfr usfs tif
     * dffbult timfzonf bnd lodblf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm dbl tif <dodf>Cblfndbr</dodf> objfdt tif drivfr will usf
     *            to donstrudt tif timf
     * @rfturn tif pbrbmftfr vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult is
     * <dodf>null</dodf>.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #sftTimf
     * @sindf 1.4
     */
    jbvb.sql.Timf gftTimf(String pbrbmftfrNbmf, Cblfndbr dbl)
        tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of b JDBC <dodf>TIMESTAMP</dodf> pbrbmftfr bs b
     * <dodf>jbvb.sql.Timfstbmp</dodf> objfdt, using
     * tif givfn <dodf>Cblfndbr</dodf> objfdt to donstrudt
     * tif <dodf>Timfstbmp</dodf> objfdt.
     * Witi b <dodf>Cblfndbr</dodf> objfdt, tif drivfr
     * dbn dbldulbtf tif timfstbmp tbking into bddount b dustom timfzonf bnd lodblf.
     * If no <dodf>Cblfndbr</dodf> objfdt is spfdififd, tif drivfr usfs tif
     * dffbult timfzonf bnd lodblf.
     *
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm dbl tif <dodf>Cblfndbr</dodf> objfdt tif drivfr will usf
     *            to donstrudt tif timfstbmp
     * @rfturn tif pbrbmftfr vbluf.  If tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult is
     * <dodf>null</dodf>.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #sftTimfstbmp
     * @sindf 1.4
     */
    jbvb.sql.Timfstbmp gftTimfstbmp(String pbrbmftfrNbmf, Cblfndbr dbl)
        tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of b JDBC <dodf>DATALINK</dodf> pbrbmftfr bs b
     * <dodf>jbvb.nft.URL</dodf> objfdt.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @rfturn tif pbrbmftfr vbluf bs b <dodf>jbvb.nft.URL</dodf> objfdt in tif
     * Jbvb progrbmming lbngubgf.  If tif vbluf wbs SQL <dodf>NULL</dodf>, tif
     * vbluf <dodf>null</dodf> is rfturnfd.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs,
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>,
     *            or if tifrf is b problfm witi tif URL
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #sftURL
     * @sindf 1.4
     */
    jbvb.nft.URL gftURL(String pbrbmftfrNbmf) tirows SQLExdfption;

    //------------------------- JDBC 4.0 -----------------------------------

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd JDBC <dodf>ROWID</dodf> pbrbmftfr bs b
     * <dodf>jbvb.sql.RowId</dodf> objfdt.
     *
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2,...
     * @rfturn b <dodf>RowId</dodf> objfdt tibt rfprfsfnts tif JDBC <dodf>ROWID</dodf>
     *     vbluf is usfd bs tif dfsignbtfd pbrbmftfr. If tif pbrbmftfr dontbins
     * b SQL <dodf>NULL</dodf>, tifn b <dodf>null</dodf> vbluf is rfturnfd.
     * @tirows SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    RowId gftRowId(int pbrbmftfrIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd JDBC <dodf>ROWID</dodf> pbrbmftfr bs b
     * <dodf>jbvb.sql.RowId</dodf> objfdt.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @rfturn b <dodf>RowId</dodf> objfdt tibt rfprfsfnts tif JDBC <dodf>ROWID</dodf>
     *     vbluf is usfd bs tif dfsignbtfd pbrbmftfr. If tif pbrbmftfr dontbins
     * b SQL <dodf>NULL</dodf>, tifn b <dodf>null</dodf> vbluf is rfturnfd.
     * @tirows SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    RowId gftRowId(String pbrbmftfrNbmf) tirows SQLExdfption;

     /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>jbvb.sql.RowId</dodf> objfdt. Tif
     * drivfr donvfrts tiis to b SQL <dodf>ROWID</dodf> wifn it sfnds it to tif
     * dbtbbbsf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif pbrbmftfr vbluf
     * @tirows SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void sftRowId(String pbrbmftfrNbmf, RowId x) tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>String</dodf> objfdt.
     * Tif drivfr donvfrts tiis to b SQL <dodf>NCHAR</dodf> or
     * <dodf>NVARCHAR</dodf> or <dodf>LONGNVARCHAR</dodf>
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr to bf sft
     * @pbrbm vbluf tif pbrbmftfr vbluf
     * @tirows SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if tif drivfr dofs not support nbtionbl
     *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
     *  frror dould oddur; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void sftNString(String pbrbmftfrNbmf, String vbluf)
            tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>Rfbdfr</dodf> objfdt. Tif
     * <dodf>Rfbdfr</dodf> rfbds tif dbtb till fnd-of-filf is rfbdifd. Tif
     * drivfr dofs tif nfdfssbry donvfrsion from Jbvb dibrbdtfr formbt to
     * tif nbtionbl dibrbdtfr sft in tif dbtbbbsf.
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr to bf sft
     * @pbrbm vbluf tif pbrbmftfr vbluf
     * @pbrbm lfngti tif numbfr of dibrbdtfrs in tif pbrbmftfr dbtb.
     * @tirows SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if tif drivfr dofs not support nbtionbl
     *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
     *  frror dould oddur; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void sftNCibrbdtfrStrfbm(String pbrbmftfrNbmf, Rfbdfr vbluf, long lfngti)
            tirows SQLExdfption;

     /**
     * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>jbvb.sql.NClob</dodf> objfdt. Tif objfdt
     * implfmfnts tif <dodf>jbvb.sql.NClob</dodf> intfrfbdf. Tiis <dodf>NClob</dodf>
     * objfdt mbps to b SQL <dodf>NCLOB</dodf>.
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr to bf sft
     * @pbrbm vbluf tif pbrbmftfr vbluf
     * @tirows SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if tif drivfr dofs not support nbtionbl
     *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
     *  frror dould oddur; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
     void sftNClob(String pbrbmftfrNbmf, NClob vbluf) tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>Rfbdfr</dodf> objfdt.  Tif <dodf>rfbdfr</dodf> must dontbin  tif numbfr
     * of dibrbdtfrs spfdififd by lfngti otifrwisf b <dodf>SQLExdfption</dodf> will bf
     * gfnfrbtfd wifn tif <dodf>CbllbblfStbtfmfnt</dodf> is fxfdutfd.
     * Tiis mftiod difffrs from tif <dodf>sftCibrbdtfrStrfbm (int, Rfbdfr, int)</dodf> mftiod
     * bfdbusf it informs tif drivfr tibt tif pbrbmftfr vbluf siould bf sfnt to
     * tif sfrvfr bs b <dodf>CLOB</dodf>.  Wifn tif <dodf>sftCibrbdtfrStrfbm</dodf> mftiod is usfd, tif
     * drivfr mby ibvf to do fxtrb work to dftfrminf wiftifr tif pbrbmftfr
     * dbtb siould bf sfnd to tif sfrvfr bs b <dodf>LONGVARCHAR</dodf> or b <dodf>CLOB</dodf>
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr to bf sft
     * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
     * @pbrbm lfngti tif numbfr of dibrbdtfrs in tif pbrbmftfr dbtb.
     * @tirows SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if tif lfngti spfdififd is lfss tibn zfro;
     * b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     *
     * @sindf 1.6
     */
     void sftClob(String pbrbmftfrNbmf, Rfbdfr rfbdfr, long lfngti)
       tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>InputStrfbm</dodf> objfdt.  Tif <dodf>inputstrfbm</dodf> must dontbin  tif numbfr
     * of dibrbdtfrs spfdififd by lfngti, otifrwisf b <dodf>SQLExdfption</dodf> will bf
     * gfnfrbtfd wifn tif <dodf>CbllbblfStbtfmfnt</dodf> is fxfdutfd.
     * Tiis mftiod difffrs from tif <dodf>sftBinbryStrfbm (int, InputStrfbm, int)</dodf>
     * mftiod bfdbusf it informs tif drivfr tibt tif pbrbmftfr vbluf siould bf
     * sfnt to tif sfrvfr bs b <dodf>BLOB</dodf>.  Wifn tif <dodf>sftBinbryStrfbm</dodf> mftiod is usfd,
     * tif drivfr mby ibvf to do fxtrb work to dftfrminf wiftifr tif pbrbmftfr
     * dbtb siould bf sfnt to tif sfrvfr bs b <dodf>LONGVARBINARY</dodf> or b <dodf>BLOB</dodf>
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr to bf sft
     * tif sfdond is 2, ...
     *
     * @pbrbm inputStrfbm An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr
     * vbluf to.
     * @pbrbm lfngti tif numbfr of bytfs in tif pbrbmftfr dbtb.
     * @tirows SQLExdfption  if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if tif lfngti spfdififd
     * is lfss tibn zfro; if tif numbfr of bytfs in tif inputstrfbm dofs not mbtdi
     * tif spfdififd lfngti; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     *
     * @sindf 1.6
     */
     void sftBlob(String pbrbmftfrNbmf, InputStrfbm inputStrfbm, long lfngti)
        tirows SQLExdfption;
    /**
     * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>Rfbdfr</dodf> objfdt.  Tif <dodf>rfbdfr</dodf> must dontbin  tif numbfr
     * of dibrbdtfrs spfdififd by lfngti otifrwisf b <dodf>SQLExdfption</dodf> will bf
     * gfnfrbtfd wifn tif <dodf>CbllbblfStbtfmfnt</dodf> is fxfdutfd.
     * Tiis mftiod difffrs from tif <dodf>sftCibrbdtfrStrfbm (int, Rfbdfr, int)</dodf> mftiod
     * bfdbusf it informs tif drivfr tibt tif pbrbmftfr vbluf siould bf sfnt to
     * tif sfrvfr bs b <dodf>NCLOB</dodf>.  Wifn tif <dodf>sftCibrbdtfrStrfbm</dodf> mftiod is usfd, tif
     * drivfr mby ibvf to do fxtrb work to dftfrminf wiftifr tif pbrbmftfr
     * dbtb siould bf sfnd to tif sfrvfr bs b <dodf>LONGNVARCHAR</dodf> or b <dodf>NCLOB</dodf>
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr to bf sft
     * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
     * @pbrbm lfngti tif numbfr of dibrbdtfrs in tif pbrbmftfr dbtb.
     * @tirows SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if tif lfngti spfdififd is lfss tibn zfro;
     * if tif drivfr dofs not support nbtionbl
     *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
     *  frror dould oddur; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
     void sftNClob(String pbrbmftfrNbmf, Rfbdfr rfbdfr, long lfngti)
       tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd JDBC <dodf>NCLOB</dodf> pbrbmftfr bs b
     * <dodf>jbvb.sql.NClob</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2, bnd
     * so on
     * @rfturn tif pbrbmftfr vbluf bs b <dodf>NClob</dodf> objfdt in tif
     * Jbvb progrbmming lbngubgf.  If tif vbluf wbs SQL <dodf>NULL</dodf>, tif
     * vbluf <dodf>null</dodf> is rfturnfd.
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if tif drivfr dofs not support nbtionbl
     *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
     *  frror dould oddur; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    NClob gftNClob (int pbrbmftfrIndfx) tirows SQLExdfption;


    /**
     * Rftrifvfs tif vbluf of b JDBC <dodf>NCLOB</dodf> pbrbmftfr bs b
     * <dodf>jbvb.sql.NClob</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @rfturn tif pbrbmftfr vbluf bs b <dodf>NClob</dodf> objfdt in tif
     *         Jbvb progrbmming lbngubgf.  If tif vbluf wbs SQL <dodf>NULL</dodf>,
     *         tif vbluf <dodf>null</dodf> is rfturnfd.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if tif drivfr dofs not support nbtionbl
     *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
     *  frror dould oddur; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    NClob gftNClob (String pbrbmftfrNbmf) tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>jbvb.sql.SQLXML</dodf> objfdt. Tif drivfr donvfrts tiis to bn
     * <dodf>SQL XML</dodf> vbluf wifn it sfnds it to tif dbtbbbsf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm xmlObjfdt b <dodf>SQLXML</dodf> objfdt tibt mbps bn <dodf>SQL XML</dodf> vbluf
     * @tirows SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs;
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf> or
     * tif <dodf>jbvb.xml.trbnsform.Rfsult</dodf>,
   *  <dodf>Writfr</dodf> or <dodf>OutputStrfbm</dodf> ibs not bffn dlosfd for tif <dodf>SQLXML</dodf> objfdt
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     *
     * @sindf 1.6
     */
    void sftSQLXML(String pbrbmftfrNbmf, SQLXML xmlObjfdt) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd <dodf>SQL XML</dodf> pbrbmftfr bs b
     * <dodf>jbvb.sql.SQLXML</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     * @pbrbm pbrbmftfrIndfx indfx of tif first pbrbmftfr is 1, tif sfdond is 2, ...
     * @rfturn b <dodf>SQLXML</dodf> objfdt tibt mbps bn <dodf>SQL XML</dodf> vbluf
     * @tirows SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    SQLXML gftSQLXML(int pbrbmftfrIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd <dodf>SQL XML</dodf> pbrbmftfr bs b
     * <dodf>jbvb.sql.SQLXML</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @rfturn b <dodf>SQLXML</dodf> objfdt tibt mbps bn <dodf>SQL XML</dodf> vbluf
     * @tirows SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    SQLXML gftSQLXML(String pbrbmftfrNbmf) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd <dodf>NCHAR</dodf>,
     * <dodf>NVARCHAR</dodf>
     * or <dodf>LONGNVARCHAR</dodf> pbrbmftfr bs
     * b <dodf>String</dodf> in tif Jbvb progrbmming lbngubgf.
     * <p>
     * For tif fixfd-lfngti typf JDBC <dodf>NCHAR</dodf>,
     * tif <dodf>String</dodf> objfdt
     * rfturnfd ibs fxbdtly tif sbmf vbluf tif SQL
     * <dodf>NCHAR</dodf> vbluf ibd in tif
     * dbtbbbsf, indluding bny pbdding bddfd by tif dbtbbbsf.
     *
     * @pbrbm pbrbmftfrIndfx indfx of tif first pbrbmftfr is 1, tif sfdond is 2, ...
     * @rfturn b <dodf>String</dodf> objfdt tibt mbps bn
     * <dodf>NCHAR</dodf>, <dodf>NVARCHAR</dodf> or <dodf>LONGNVARCHAR</dodf> vbluf
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     * @sff #sftNString
     */
    String gftNString(int pbrbmftfrIndfx) tirows SQLExdfption;


    /**
     *  Rftrifvfs tif vbluf of tif dfsignbtfd <dodf>NCHAR</dodf>,
     * <dodf>NVARCHAR</dodf>
     * or <dodf>LONGNVARCHAR</dodf> pbrbmftfr bs
     * b <dodf>String</dodf> in tif Jbvb progrbmming lbngubgf.
     * <p>
     * For tif fixfd-lfngti typf JDBC <dodf>NCHAR</dodf>,
     * tif <dodf>String</dodf> objfdt
     * rfturnfd ibs fxbdtly tif sbmf vbluf tif SQL
     * <dodf>NCHAR</dodf> vbluf ibd in tif
     * dbtbbbsf, indluding bny pbdding bddfd by tif dbtbbbsf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @rfturn b <dodf>String</dodf> objfdt tibt mbps bn
     * <dodf>NCHAR</dodf>, <dodf>NVARCHAR</dodf> or <dodf>LONGNVARCHAR</dodf> vbluf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     * @sff #sftNString
     */
    String gftNString(String pbrbmftfrNbmf) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd pbrbmftfr bs b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     * It is intfndfd for usf wifn
     * bddfssing  <dodf>NCHAR</dodf>,<dodf>NVARCHAR</dodf>
     * bnd <dodf>LONGNVARCHAR</dodf> pbrbmftfrs.
     *
     * @rfturn b <dodf>jbvb.io.Rfbdfr</dodf> objfdt tibt dontbins tif pbrbmftfr
     * vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif vbluf rfturnfd is
     * <dodf>null</dodf> in tif Jbvb progrbmming lbngubgf.
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2, ...
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    jbvb.io.Rfbdfr gftNCibrbdtfrStrfbm(int pbrbmftfrIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd pbrbmftfr bs b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     * It is intfndfd for usf wifn
     * bddfssing  <dodf>NCHAR</dodf>,<dodf>NVARCHAR</dodf>
     * bnd <dodf>LONGNVARCHAR</dodf> pbrbmftfrs.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @rfturn b <dodf>jbvb.io.Rfbdfr</dodf> objfdt tibt dontbins tif pbrbmftfr
     * vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif vbluf rfturnfd is
     * <dodf>null</dodf> in tif Jbvb progrbmming lbngubgf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    jbvb.io.Rfbdfr gftNCibrbdtfrStrfbm(String pbrbmftfrNbmf) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd pbrbmftfr bs b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @rfturn b <dodf>jbvb.io.Rfbdfr</dodf> objfdt tibt dontbins tif pbrbmftfr
     * vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif vbluf rfturnfd is
     * <dodf>null</dodf> in tif Jbvb progrbmming lbngubgf.
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2, ...
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @sindf 1.6
     */
    jbvb.io.Rfbdfr gftCibrbdtfrStrfbm(int pbrbmftfrIndfx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd pbrbmftfr bs b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @rfturn b <dodf>jbvb.io.Rfbdfr</dodf> objfdt tibt dontbins tif pbrbmftfr
     * vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif vbluf rfturnfd is
     * <dodf>null</dodf> in tif Jbvb progrbmming lbngubgf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    jbvb.io.Rfbdfr gftCibrbdtfrStrfbm(String pbrbmftfrNbmf) tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>jbvb.sql.Blob</dodf> objfdt.
     * Tif drivfr donvfrts tiis to bn SQL <dodf>BLOB</dodf> vbluf wifn it
     * sfnds it to tif dbtbbbsf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x b <dodf>Blob</dodf> objfdt tibt mbps bn SQL <dodf>BLOB</dodf> vbluf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void sftBlob (String pbrbmftfrNbmf, Blob x) tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>jbvb.sql.Clob</dodf> objfdt.
     * Tif drivfr donvfrts tiis to bn SQL <dodf>CLOB</dodf> vbluf wifn it
     * sfnds it to tif dbtbbbsf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x b <dodf>Clob</dodf> objfdt tibt mbps bn SQL <dodf>CLOB</dodf> vbluf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void sftClob (String pbrbmftfrNbmf, Clob x) tirows SQLExdfption;
    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn input strfbm, wiidi will ibvf
     * tif spfdififd numbfr of bytfs.
     * Wifn b vfry lbrgf ASCII vbluf is input to b <dodf>LONGVARCHAR</dodf>
     * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
     * <dodf>jbvb.io.InputStrfbm</dodf>. Dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-filf is rfbdifd.  Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from ASCII to tif dbtbbbsf dibr formbt.
     *
     * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
     * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
     * stbndbrd intfrfbdf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif Jbvb input strfbm tibt dontbins tif ASCII pbrbmftfr vbluf
     * @pbrbm lfngti tif numbfr of bytfs in tif strfbm
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void sftAsdiiStrfbm(String pbrbmftfrNbmf, jbvb.io.InputStrfbm x, long lfngti)
        tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn input strfbm, wiidi will ibvf
     * tif spfdififd numbfr of bytfs.
     * Wifn b vfry lbrgf binbry vbluf is input to b <dodf>LONGVARBINARY</dodf>
     * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
     * <dodf>jbvb.io.InputStrfbm</dodf> objfdt. Tif dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-filf is rfbdifd.
     *
     * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
     * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
     * stbndbrd intfrfbdf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif jbvb input strfbm wiidi dontbins tif binbry pbrbmftfr vbluf
     * @pbrbm lfngti tif numbfr of bytfs in tif strfbm
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void sftBinbryStrfbm(String pbrbmftfrNbmf, jbvb.io.InputStrfbm x,
                         long lfngti) tirows SQLExdfption;
        /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>Rfbdfr</dodf>
     * objfdt, wiidi is tif givfn numbfr of dibrbdtfrs long.
     * Wifn b vfry lbrgf UNICODE vbluf is input to b <dodf>LONGVARCHAR</dodf>
     * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt. Tif dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-filf is rfbdifd.  Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from UNICODE to tif dbtbbbsf dibr formbt.
     *
     * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
     * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
     * stbndbrd intfrfbdf.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm rfbdfr tif <dodf>jbvb.io.Rfbdfr</dodf> objfdt tibt
     *        dontbins tif UNICODE dbtb usfd bs tif dfsignbtfd pbrbmftfr
     * @pbrbm lfngti tif numbfr of dibrbdtfrs in tif strfbm
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void sftCibrbdtfrStrfbm(String pbrbmftfrNbmf,
                            jbvb.io.Rfbdfr rfbdfr,
                            long lfngti) tirows SQLExdfption;
     //--
    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn input strfbm.
     * Wifn b vfry lbrgf ASCII vbluf is input to b <dodf>LONGVARCHAR</dodf>
     * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
     * <dodf>jbvb.io.InputStrfbm</dodf>. Dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-filf is rfbdifd.  Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from ASCII to tif dbtbbbsf dibr formbt.
     *
     * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
     * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
     * stbndbrd intfrfbdf.
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>sftAsdiiStrfbm</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif Jbvb input strfbm tibt dontbins tif ASCII pbrbmftfr vbluf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
       * @sindf 1.6
    */
    void sftAsdiiStrfbm(String pbrbmftfrNbmf, jbvb.io.InputStrfbm x)
            tirows SQLExdfption;
    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn input strfbm.
     * Wifn b vfry lbrgf binbry vbluf is input to b <dodf>LONGVARBINARY</dodf>
     * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
     * <dodf>jbvb.io.InputStrfbm</dodf> objfdt. Tif dbtb will bf rfbd from tif
     * strfbm bs nffdfd until fnd-of-filf is rfbdifd.
     *
     * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
     * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
     * stbndbrd intfrfbdf.
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>sftBinbryStrfbm</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif jbvb input strfbm wiidi dontbins tif binbry pbrbmftfr vbluf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
     * @sindf 1.6
     */
    void sftBinbryStrfbm(String pbrbmftfrNbmf, jbvb.io.InputStrfbm x)
    tirows SQLExdfption;
    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>Rfbdfr</dodf>
     * objfdt.
     * Wifn b vfry lbrgf UNICODE vbluf is input to b <dodf>LONGVARCHAR</dodf>
     * pbrbmftfr, it mby bf morf prbdtidbl to sfnd it vib b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt. Tif dbtb will bf rfbd from tif strfbm
     * bs nffdfd until fnd-of-filf is rfbdifd.  Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from UNICODE to tif dbtbbbsf dibr formbt.
     *
     * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
     * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
     * stbndbrd intfrfbdf.
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>sftCibrbdtfrStrfbm</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm rfbdfr tif <dodf>jbvb.io.Rfbdfr</dodf> objfdt tibt dontbins tif
     *        Unidodf dbtb
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
     * @sindf 1.6
     */
    void sftCibrbdtfrStrfbm(String pbrbmftfrNbmf,
                          jbvb.io.Rfbdfr rfbdfr) tirows SQLExdfption;
  /**
     * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>Rfbdfr</dodf> objfdt. Tif
     * <dodf>Rfbdfr</dodf> rfbds tif dbtb till fnd-of-filf is rfbdifd. Tif
     * drivfr dofs tif nfdfssbry donvfrsion from Jbvb dibrbdtfr formbt to
     * tif nbtionbl dibrbdtfr sft in tif dbtbbbsf.

     * <P><B>Notf:</B> Tiis strfbm objfdt dbn fitifr bf b stbndbrd
     * Jbvb strfbm objfdt or your own subdlbss tibt implfmfnts tif
     * stbndbrd intfrfbdf.
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>sftNCibrbdtfrStrfbm</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm vbluf tif pbrbmftfr vbluf
     * @tirows SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if tif drivfr dofs not support nbtionbl
     *         dibrbdtfr sfts;  if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
     *  frror dould oddur; if b dbtbbbsf bddfss frror oddurs; or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
     * @sindf 1.6
     */
     void sftNCibrbdtfrStrfbm(String pbrbmftfrNbmf, Rfbdfr vbluf) tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>Rfbdfr</dodf> objfdt.
     * Tiis mftiod difffrs from tif <dodf>sftCibrbdtfrStrfbm (int, Rfbdfr)</dodf> mftiod
     * bfdbusf it informs tif drivfr tibt tif pbrbmftfr vbluf siould bf sfnt to
     * tif sfrvfr bs b <dodf>CLOB</dodf>.  Wifn tif <dodf>sftCibrbdtfrStrfbm</dodf> mftiod is usfd, tif
     * drivfr mby ibvf to do fxtrb work to dftfrminf wiftifr tif pbrbmftfr
     * dbtb siould bf sfnd to tif sfrvfr bs b <dodf>LONGVARCHAR</dodf> or b <dodf>CLOB</dodf>
     *
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>sftClob</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
     * @tirows SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or tiis mftiod is dbllfd on
     * b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     *
     * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
     * @sindf 1.6
     */
     void sftClob(String pbrbmftfrNbmf, Rfbdfr rfbdfr)
       tirows SQLExdfption;

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>InputStrfbm</dodf> objfdt.
     * Tiis mftiod difffrs from tif <dodf>sftBinbryStrfbm (int, InputStrfbm)</dodf>
     * mftiod bfdbusf it informs tif drivfr tibt tif pbrbmftfr vbluf siould bf
     * sfnt to tif sfrvfr bs b <dodf>BLOB</dodf>.  Wifn tif <dodf>sftBinbryStrfbm</dodf> mftiod is usfd,
     * tif drivfr mby ibvf to do fxtrb work to dftfrminf wiftifr tif pbrbmftfr
     * dbtb siould bf sfnd to tif sfrvfr bs b <dodf>LONGVARBINARY</dodf> or b <dodf>BLOB</dodf>
     *
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>sftBlob</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm inputStrfbm An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr
     * vbluf to.
     * @tirows SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
     *
     * @sindf 1.6
     */
     void sftBlob(String pbrbmftfrNbmf, InputStrfbm inputStrfbm)
        tirows SQLExdfption;
    /**
     * Sfts tif dfsignbtfd pbrbmftfr to b <dodf>Rfbdfr</dodf> objfdt.
     * Tiis mftiod difffrs from tif <dodf>sftCibrbdtfrStrfbm (int, Rfbdfr)</dodf> mftiod
     * bfdbusf it informs tif drivfr tibt tif pbrbmftfr vbluf siould bf sfnt to
     * tif sfrvfr bs b <dodf>NCLOB</dodf>.  Wifn tif <dodf>sftCibrbdtfrStrfbm</dodf> mftiod is usfd, tif
     * drivfr mby ibvf to do fxtrb work to dftfrminf wiftifr tif pbrbmftfr
     * dbtb siould bf sfnd to tif sfrvfr bs b <dodf>LONGNVARCHAR</dodf> or b <dodf>NCLOB</dodf>
     * <P><B>Notf:</B> Consult your JDBC drivfr dodumfntbtion to dftfrminf if
     * it migit bf morf fffidifnt to usf b vfrsion of
     * <dodf>sftNClob</dodf> wiidi tbkfs b lfngti pbrbmftfr.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm rfbdfr An objfdt tibt dontbins tif dbtb to sft tif pbrbmftfr vbluf to.
     * @tirows SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if tif drivfr dofs not support nbtionbl dibrbdtfr sfts;
     * if tif drivfr dbn dftfdt tibt b dbtb donvfrsion
     *  frror dould oddur;  if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>CbllbblfStbtfmfnt</dodf>
     * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
     *
     * @sindf 1.6
     */
     void sftNClob(String pbrbmftfrNbmf, Rfbdfr rfbdfr)
       tirows SQLExdfption;

    //------------------------- JDBC 4.1 -----------------------------------


    /**
     *<p>Rfturns bn objfdt rfprfsfnting tif vbluf of OUT pbrbmftfr
     * {@dodf pbrbmftfrIndfx} bnd will donvfrt from tif
     * SQL typf of tif pbrbmftfr to tif rfqufstfd Jbvb dbtb typf, if tif
     * donvfrsion is supportfd. If tif donvfrsion is not
     * supportfd or null is spfdififd for tif typf, b
     * <dodf>SQLExdfption</dodf> is tirown.
     *<p>
     * At b minimum, bn implfmfntbtion must support tif donvfrsions dffinfd in
     * Appfndix B, Tbblf B-3 bnd donvfrsion of bppropribtf usfr dffinfd SQL
     * typfs to b Jbvb typf wiidi implfmfnts {@dodf SQLDbtb}, or {@dodf Strudt}.
     * Additionbl donvfrsions mby bf supportfd bnd brf vfndor dffinfd.
     *
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2, bnd so on
     * @pbrbm typf Clbss rfprfsfnting tif Jbvb dbtb typf to donvfrt tif
     * dfsignbtfd pbrbmftfr to.
     * @pbrbm <T> tif typf of tif dlbss modflfd by tiis Clbss objfdt
     * @rfturn bn instbndf of {@dodf typf} iolding tif OUT pbrbmftfr vbluf
     * @tirows SQLExdfption if donvfrsion is not supportfd, typf is null or
     *         bnotifr frror oddurs. Tif gftCbusf() mftiod of tif
     * fxdfption mby providf b morf dftbilfd fxdfption, for fxbmplf, if
     * b donvfrsion frror oddurs
     * @tirows SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.7
     */
     publid <T> T gftObjfdt(int pbrbmftfrIndfx, Clbss<T> typf) tirows SQLExdfption;


    /**
     *<p>Rfturns bn objfdt rfprfsfnting tif vbluf of OUT pbrbmftfr
     * {@dodf pbrbmftfrNbmf} bnd will donvfrt from tif
     * SQL typf of tif pbrbmftfr to tif rfqufstfd Jbvb dbtb typf, if tif
     * donvfrsion is supportfd. If tif donvfrsion is not
     * supportfd  or null is spfdififd for tif typf, b
     * <dodf>SQLExdfption</dodf> is tirown.
     *<p>
     * At b minimum, bn implfmfntbtion must support tif donvfrsions dffinfd in
     * Appfndix B, Tbblf B-3 bnd donvfrsion of bppropribtf usfr dffinfd SQL
     * typfs to b Jbvb typf wiidi implfmfnts {@dodf SQLDbtb}, or {@dodf Strudt}.
     * Additionbl donvfrsions mby bf supportfd bnd brf vfndor dffinfd.
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm typf Clbss rfprfsfnting tif Jbvb dbtb typf to donvfrt
     * tif dfsignbtfd pbrbmftfr to.
     * @pbrbm <T> tif typf of tif dlbss modflfd by tiis Clbss objfdt
     * @rfturn bn instbndf of {@dodf typf} iolding tif OUT pbrbmftfr
     * vbluf
     * @tirows SQLExdfption if donvfrsion is not supportfd, typf is null or
     *         bnotifr frror oddurs. Tif gftCbusf() mftiod of tif
     * fxdfption mby providf b morf dftbilfd fxdfption, for fxbmplf, if
     * b donvfrsion frror oddurs
     * @tirows SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.7
     */
     publid <T> T gftObjfdt(String pbrbmftfrNbmf, Clbss<T> typf) tirows SQLExdfption;

     //------------------------- JDBC 4.2 -----------------------------------

     /**
     * <p>Sfts tif vbluf of tif dfsignbtfd pbrbmftfr witi tif givfn objfdt.
     *
     * If tif sfdond brgumfnt is bn {@dodf InputStrfbm} tifn tif strfbm
     * must dontbin tif numbfr of bytfs spfdififd by sdblfOrLfngti.
     * If tif sfdond brgumfnt is b {@dodf Rfbdfr} tifn tif rfbdfr must
     * dontbin tif numbfr of dibrbdtfrs spfdififd
     * by sdblfOrLfngti. If tifsf donditions brf not truf tif drivfr
     * will gfnfrbtf b
     * {@dodf SQLExdfption} wifn tif prfpbrfd stbtfmfnt is fxfdutfd.
     *
     * <p>Tif givfn Jbvb objfdt will bf donvfrtfd to tif givfn tbrgftSqlTypf
     * bfforf bfing sfnt to tif dbtbbbsf.
     *
     * If tif objfdt ibs b dustom mbpping (is of b dlbss implfmfnting tif
     * intfrfbdf {@dodf SQLDbtb}),
     * tif JDBC drivfr siould dbll tif mftiod {@dodf SQLDbtb.writfSQL} to
     * writf it to tif SQL dbtb strfbm.
     * If, on tif otifr ibnd, tif objfdt is of b dlbss implfmfnting
     * {@dodf Rff}, {@dodf Blob}, {@dodf Clob},  {@dodf NClob},
     *  {@dodf Strudt}, {@dodf jbvb.nft.URL},
     * or {@dodf Arrby}, tif drivfr siould pbss it to tif dbtbbbsf bs b
     * vbluf of tif dorrfsponding SQL typf.
     *
     * <p>Notf tibt tiis mftiod mby bf usfd to pbss dbtbbbsf-spfdifid
     * bbstrbdt dbtb typfs.
     *<P>
     * Tif dffbult implfmfntbtion will tirow {@dodf SQLFfbturfNotSupportfdExdfption}
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif objfdt dontbining tif input pbrbmftfr vbluf
     * @pbrbm tbrgftSqlTypf tif SQL typf to bf
     * sfnt to tif dbtbbbsf. Tif sdblf brgumfnt mby furtifr qublify tiis typf.
     * @pbrbm sdblfOrLfngti for {@dodf jbvb.sql.JDBCTypf.DECIMAL}
     *          or {@dodf jbvb.sql.JDBCTypf.NUMERIC typfs},
     *          tiis is tif numbfr of digits bftfr tif dfdimbl point. For
     *          Jbvb Objfdt typfs {@dodf InputStrfbm} bnd {@dodf Rfbdfr},
     *          tiis is tif lfngti
     *          of tif dbtb in tif strfbm or rfbdfr.  For bll otifr typfs,
     *          tiis vbluf will bf ignorfd.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd {@dodf CbllbblfStbtfmfnt}  or
     *            if tif Jbvb Objfdt spfdififd by x is bn InputStrfbm
     *            or Rfbdfr objfdt bnd tif vbluf of tif sdblf pbrbmftfr is lfss
     *            tibn zfro
     * @fxdfption SQLFfbturfNotSupportfdExdfption if
     * tif JDBC drivfr dofs not support tif spfdififd tbrgftSqlTypf
     * @sff JDBCTypf
     * @sff SQLTypf
     *
     * @sindf 1.8
     */
     dffbult void sftObjfdt(String pbrbmftfrNbmf, Objfdt x, SQLTypf tbrgftSqlTypf,
             int sdblfOrLfngti) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption("sftObjfdt not implfmfntfd");
    }
    /**
     * Sfts tif vbluf of tif dfsignbtfd pbrbmftfr witi tif givfn objfdt.
     *
     * Tiis mftiod is similbr to {@link #sftObjfdt(String pbrbmftfrNbmf,
     * Objfdt x, SQLTypf tbrgftSqlTypf, int sdblfOrLfngti)},
     * fxdfpt tibt it bssumfs b sdblf of zfro.
     *<P>
     * Tif dffbult implfmfntbtion will tirow {@dodf SQLFfbturfNotSupportfdExdfption}
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm x tif objfdt dontbining tif input pbrbmftfr vbluf
     * @pbrbm tbrgftSqlTypf tif SQL typf to bf sfnt to tif dbtbbbsf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs
     * or tiis mftiod is dbllfd on b dlosfd {@dodf CbllbblfStbtfmfnt}
     * @fxdfption SQLFfbturfNotSupportfdExdfption if
     * tif JDBC drivfr dofs not support tif spfdififd tbrgftSqlTypf
     * @sff JDBCTypf
     * @sff SQLTypf
     * @sindf 1.8
     */
     dffbult void sftObjfdt(String pbrbmftfrNbmf, Objfdt x, SQLTypf tbrgftSqlTypf)
        tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption("sftObjfdt not implfmfntfd");
    }

    /**
     * Rfgistfrs tif OUT pbrbmftfr in ordinbl position
     * {@dodf pbrbmftfrIndfx} to tif JDBC typf
     * {@dodf sqlTypf}.  All OUT pbrbmftfrs must bf rfgistfrfd
     * bfforf b storfd prodfdurf is fxfdutfd.
     * <p>
     * Tif JDBC typf spfdififd by {@dodf sqlTypf} for bn OUT
     * pbrbmftfr dftfrminfs tif Jbvb typf tibt must bf usfd
     * in tif {@dodf gft} mftiod to rfbd tif vbluf of tibt pbrbmftfr.
     * <p>
     * If tif JDBC typf fxpfdtfd to bf rfturnfd to tiis output pbrbmftfr
     * is spfdifid to tiis pbrtidulbr dbtbbbsf, {@dodf sqlTypf}
     * mby bf {@dodf JDBCTypf.OTHER} or b {@dodf SQLTypf} tibt is supportfd by
     * tif JDBC drivfr.  Tif mftiod
     * {@link #gftObjfdt} rftrifvfs tif vbluf.
     *<P>
     * Tif dffbult implfmfntbtion will tirow {@dodf SQLFfbturfNotSupportfdExdfption}
     *
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2,
     *        bnd so on
     * @pbrbm sqlTypf tif JDBC typf dodf dffinfd by {@dodf SQLTypf} to usf to
     * rfgistfr tif OUT Pbrbmftfr.
     *        If tif pbrbmftfr is of JDBC typf {@dodf JDBCTypf.NUMERIC}
     *        or {@dodf JDBCTypf.DECIMAL}, tif vfrsion of
     *        {@dodf rfgistfrOutPbrbmftfr} tibt bddfpts b sdblf vbluf
     *        siould bf usfd.
     *
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd {@dodf CbllbblfStbtfmfnt}
     * @fxdfption SQLFfbturfNotSupportfdExdfption if
     * tif JDBC drivfr dofs not support tif spfdififd sqlTypf
     * @sff JDBCTypf
     * @sff SQLTypf
     * @sindf 1.8
     */
    dffbult void rfgistfrOutPbrbmftfr(int pbrbmftfrIndfx, SQLTypf sqlTypf)
        tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption("rfgistfrOutPbrbmftfr not implfmfntfd");
    }

    /**
     * Rfgistfrs tif pbrbmftfr in ordinbl position
     * {@dodf pbrbmftfrIndfx} to bf of JDBC typf
     * {@dodf sqlTypf}. All OUT pbrbmftfrs must bf rfgistfrfd
     * bfforf b storfd prodfdurf is fxfdutfd.
     * <p>
     * Tif JDBC typf spfdififd by {@dodf sqlTypf} for bn OUT
     * pbrbmftfr dftfrminfs tif Jbvb typf tibt must bf usfd
     * in tif {@dodf gft} mftiod to rfbd tif vbluf of tibt pbrbmftfr.
     * <p>
     * Tiis vfrsion of {@dodf  rfgistfrOutPbrbmftfr} siould bf
     * usfd wifn tif pbrbmftfr is of JDBC typf {@dodf JDBCTypf.NUMERIC}
     * or {@dodf JDBCTypf.DECIMAL}.
     *<P>
     * Tif dffbult implfmfntbtion will tirow {@dodf SQLFfbturfNotSupportfdExdfption}
     *
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2,
     * bnd so on
     * @pbrbm sqlTypf tif JDBC typf dodf dffinfd by {@dodf SQLTypf} to usf to
     * rfgistfr tif OUT Pbrbmftfr.
     * @pbrbm sdblf tif dfsirfd numbfr of digits to tif rigit of tif
     * dfdimbl point.  It must bf grfbtfr tibn or fqubl to zfro.
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd {@dodf CbllbblfStbtfmfnt}
     * @fxdfption SQLFfbturfNotSupportfdExdfption if
     * tif JDBC drivfr dofs not support tif spfdififd sqlTypf
     * @sff JDBCTypf
     * @sff SQLTypf
     * @sindf 1.8
     */
    dffbult void rfgistfrOutPbrbmftfr(int pbrbmftfrIndfx, SQLTypf sqlTypf,
            int sdblf) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption("rfgistfrOutPbrbmftfr not implfmfntfd");
    }
    /**
     * Rfgistfrs tif dfsignbtfd output pbrbmftfr.
     * Tiis vfrsion of
     * tif mftiod {@dodf  rfgistfrOutPbrbmftfr}
     * siould bf usfd for b usfr-dffinfd or {@dodf REF} output pbrbmftfr.
     * Exbmplfs
     * of usfr-dffinfd typfs indludf: {@dodf STRUCT}, {@dodf DISTINCT},
     * {@dodf JAVA_OBJECT}, bnd nbmfd brrby typfs.
     *<p>
     * All OUT pbrbmftfrs must bf rfgistfrfd
     * bfforf b storfd prodfdurf is fxfdutfd.
     * <p>  For b usfr-dffinfd pbrbmftfr, tif fully-qublififd SQL
     * typf nbmf of tif pbrbmftfr siould blso bf givfn, wiilf b {@dodf REF}
     * pbrbmftfr rfquirfs tibt tif fully-qublififd typf nbmf of tif
     * rfffrfndfd typf bf givfn.  A JDBC drivfr tibt dofs not nffd tif
     * typf dodf bnd typf nbmf informbtion mby ignorf it.   To bf portbblf,
     * iowfvfr, bpplidbtions siould blwbys providf tifsf vblufs for
     * usfr-dffinfd bnd {@dodf REF} pbrbmftfrs.
     *
     * Altiougi it is intfndfd for usfr-dffinfd bnd {@dodf REF} pbrbmftfrs,
     * tiis mftiod mby bf usfd to rfgistfr b pbrbmftfr of bny JDBC typf.
     * If tif pbrbmftfr dofs not ibvf b usfr-dffinfd or {@dodf REF} typf, tif
     * <i>typfNbmf</i> pbrbmftfr is ignorfd.
     *
     * <P><B>Notf:</B> Wifn rfbding tif vbluf of bn out pbrbmftfr, you
     * must usf tif gfttfr mftiod wiosf Jbvb typf dorrfsponds to tif
     * pbrbmftfr's rfgistfrfd SQL typf.
     *<P>
     * Tif dffbult implfmfntbtion will tirow {@dodf SQLFfbturfNotSupportfdExdfption}
     *
     * @pbrbm pbrbmftfrIndfx tif first pbrbmftfr is 1, tif sfdond is 2,...
     * @pbrbm sqlTypf tif JDBC typf dodf dffinfd by {@dodf SQLTypf} to usf to
     * rfgistfr tif OUT Pbrbmftfr.
     * @pbrbm typfNbmf tif fully-qublififd nbmf of bn SQL strudturfd typf
     * @fxdfption SQLExdfption if tif pbrbmftfrIndfx is not vblid;
     * if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd {@dodf CbllbblfStbtfmfnt}
     * @fxdfption SQLFfbturfNotSupportfdExdfption if
     * tif JDBC drivfr dofs not support tif spfdififd sqlTypf
     * @sff JDBCTypf
     * @sff SQLTypf
     * @sindf 1.8
     */
    dffbult void rfgistfrOutPbrbmftfr (int pbrbmftfrIndfx, SQLTypf sqlTypf,
            String typfNbmf) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption("rfgistfrOutPbrbmftfr not implfmfntfd");
    }

    /**
     * Rfgistfrs tif OUT pbrbmftfr nbmfd
     * <dodf>pbrbmftfrNbmf</dodf> to tif JDBC typf
     * {@dodf sqlTypf}.  All OUT pbrbmftfrs must bf rfgistfrfd
     * bfforf b storfd prodfdurf is fxfdutfd.
     * <p>
     * Tif JDBC typf spfdififd by {@dodf sqlTypf} for bn OUT
     * pbrbmftfr dftfrminfs tif Jbvb typf tibt must bf usfd
     * in tif {@dodf gft} mftiod to rfbd tif vbluf of tibt pbrbmftfr.
     * <p>
     * If tif JDBC typf fxpfdtfd to bf rfturnfd to tiis output pbrbmftfr
     * is spfdifid to tiis pbrtidulbr dbtbbbsf, {@dodf sqlTypf}
     * siould bf {@dodf JDBCTypf.OTHER} or b {@dodf SQLTypf} tibt is supportfd
     * by tif JDBC drivfr..  Tif mftiod
     * {@link #gftObjfdt} rftrifvfs tif vbluf.
     *<P>
     * Tif dffbult implfmfntbtion will tirow {@dodf SQLFfbturfNotSupportfdExdfption}
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm sqlTypf tif JDBC typf dodf dffinfd by {@dodf SQLTypf} to usf to
     * rfgistfr tif OUT Pbrbmftfr.
     * If tif pbrbmftfr is of JDBC typf {@dodf JDBCTypf.NUMERIC}
     * or {@dodf JDBCTypf.DECIMAL}, tif vfrsion of
     * {@dodf  rfgistfrOutPbrbmftfr} tibt bddfpts b sdblf vbluf
     * siould bf usfd.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd {@dodf CbllbblfStbtfmfnt}
     * @fxdfption SQLFfbturfNotSupportfdExdfption if
     * tif JDBC drivfr dofs not support tif spfdififd sqlTypf
     * or if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.8
     * @sff JDBCTypf
     * @sff SQLTypf
     */
    dffbult void rfgistfrOutPbrbmftfr(String pbrbmftfrNbmf, SQLTypf sqlTypf)
        tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption("rfgistfrOutPbrbmftfr not implfmfntfd");
    }

    /**
     * Rfgistfrs tif pbrbmftfr nbmfd
     * <dodf>pbrbmftfrNbmf</dodf> to bf of JDBC typf
     * {@dodf sqlTypf}.  All OUT pbrbmftfrs must bf rfgistfrfd
     * bfforf b storfd prodfdurf is fxfdutfd.
     * <p>
     * Tif JDBC typf spfdififd by {@dodf sqlTypf} for bn OUT
     * pbrbmftfr dftfrminfs tif Jbvb typf tibt must bf usfd
     * in tif {@dodf gft} mftiod to rfbd tif vbluf of tibt pbrbmftfr.
     * <p>
     * Tiis vfrsion of {@dodf  rfgistfrOutPbrbmftfr} siould bf
     * usfd wifn tif pbrbmftfr is of JDBC typf {@dodf JDBCTypf.NUMERIC}
     * or {@dodf JDBCTypf.DECIMAL}.
     *<P>
     * Tif dffbult implfmfntbtion will tirow {@dodf SQLFfbturfNotSupportfdExdfption}
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm sqlTypf tif JDBC typf dodf dffinfd by {@dodf SQLTypf} to usf to
     * rfgistfr tif OUT Pbrbmftfr.
     * @pbrbm sdblf tif dfsirfd numbfr of digits to tif rigit of tif
     * dfdimbl point.  It must bf grfbtfr tibn or fqubl to zfro.
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd {@dodf CbllbblfStbtfmfnt}
     * @fxdfption SQLFfbturfNotSupportfdExdfption if
     * tif JDBC drivfr dofs not support tif spfdififd sqlTypf
     * or if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.8
     * @sff JDBCTypf
     * @sff SQLTypf
     */
    dffbult void rfgistfrOutPbrbmftfr(String pbrbmftfrNbmf, SQLTypf sqlTypf,
            int sdblf) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption("rfgistfrOutPbrbmftfr not implfmfntfd");
    }

    /**
     * Rfgistfrs tif dfsignbtfd output pbrbmftfr.  Tiis vfrsion of
     * tif mftiod {@dodf  rfgistfrOutPbrbmftfr}
     * siould bf usfd for b usfr-nbmfd or REF output pbrbmftfr.  Exbmplfs
     * of usfr-nbmfd typfs indludf: STRUCT, DISTINCT, JAVA_OBJECT, bnd
     * nbmfd brrby typfs.
     *<p>
     * All OUT pbrbmftfrs must bf rfgistfrfd
     * bfforf b storfd prodfdurf is fxfdutfd.
     * </p>
     * For b usfr-nbmfd pbrbmftfr tif fully-qublififd SQL
     * typf nbmf of tif pbrbmftfr siould blso bf givfn, wiilf b REF
     * pbrbmftfr rfquirfs tibt tif fully-qublififd typf nbmf of tif
     * rfffrfndfd typf bf givfn.  A JDBC drivfr tibt dofs not nffd tif
     * typf dodf bnd typf nbmf informbtion mby ignorf it.   To bf portbblf,
     * iowfvfr, bpplidbtions siould blwbys providf tifsf vblufs for
     * usfr-nbmfd bnd REF pbrbmftfrs.
     *
     * Altiougi it is intfndfd for usfr-nbmfd bnd REF pbrbmftfrs,
     * tiis mftiod mby bf usfd to rfgistfr b pbrbmftfr of bny JDBC typf.
     * If tif pbrbmftfr dofs not ibvf b usfr-nbmfd or REF typf, tif
     * typfNbmf pbrbmftfr is ignorfd.
     *
     * <P><B>Notf:</B> Wifn rfbding tif vbluf of bn out pbrbmftfr, you
     * must usf tif {@dodf gftXXX} mftiod wiosf Jbvb typf XXX dorrfsponds to tif
     * pbrbmftfr's rfgistfrfd SQL typf.
     *<P>
     * Tif dffbult implfmfntbtion will tirow {@dodf SQLFfbturfNotSupportfdExdfption}
     *
     * @pbrbm pbrbmftfrNbmf tif nbmf of tif pbrbmftfr
     * @pbrbm sqlTypf tif JDBC typf dodf dffinfd by {@dodf SQLTypf} to usf to
     * rfgistfr tif OUT Pbrbmftfr.
     * @pbrbm typfNbmf tif fully-qublififd nbmf of bn SQL strudturfd typf
     * @fxdfption SQLExdfption if pbrbmftfrNbmf dofs not dorrfspond to b nbmfd
     * pbrbmftfr; if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd {@dodf CbllbblfStbtfmfnt}
     * @fxdfption SQLFfbturfNotSupportfdExdfption if
     * tif JDBC drivfr dofs not support tif spfdififd sqlTypf
     * or if tif JDBC drivfr dofs not support tiis mftiod
     * @sff JDBCTypf
     * @sff SQLTypf
     * @sindf 1.8
     */
    dffbult void rfgistfrOutPbrbmftfr (String pbrbmftfrNbmf, SQLTypf sqlTypf,
            String typfNbmf) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption("rfgistfrOutPbrbmftfr not implfmfntfd");
    }
}

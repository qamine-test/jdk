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
 * <P>Tif objfdt usfd for fxfduting b stbtid SQL stbtfmfnt
 * bnd rfturning tif rfsults it produdfs.
 * <P>
 * By dffbult, only onf <dodf>RfsultSft</dodf> objfdt pfr <dodf>Stbtfmfnt</dodf>
 * objfdt dbn bf opfn bt tif sbmf timf. Tifrfforf, if tif rfbding of onf
 * <dodf>RfsultSft</dodf> objfdt is intfrlfbvfd
 * witi tif rfbding of bnotifr, fbdi must ibvf bffn gfnfrbtfd by
 * difffrfnt <dodf>Stbtfmfnt</dodf> objfdts. All fxfdution mftiods in tif
 * <dodf>Stbtfmfnt</dodf> intfrfbdf impliditly dlosf b durrfnt
 * <dodf>RfsultSft</dodf> objfdt of tif stbtfmfnt if bn opfn onf fxists.
 *
 * @sff Connfdtion#drfbtfStbtfmfnt
 * @sff RfsultSft
 */
publid intfrfbdf Stbtfmfnt fxtfnds Wrbppfr, AutoClosfbblf {

    /**
     * Exfdutfs tif givfn SQL stbtfmfnt, wiidi rfturns b singlf
     * <dodf>RfsultSft</dodf> objfdt.
     *<p>
     * <strong>Notf:</strong>Tiis mftiod dbnnot bf dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>.
     * @pbrbm sql bn SQL stbtfmfnt to bf sfnt to tif dbtbbbsf, typidblly b
     *        stbtid SQL <dodf>SELECT</dodf> stbtfmfnt
     * @rfturn b <dodf>RfsultSft</dodf> objfdt tibt dontbins tif dbtb produdfd
     *         by tif givfn qufry; nfvfr <dodf>null</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>, tif givfn
     *            SQL stbtfmfnt produdfs bnytiing otifr tibn b singlf
     *            <dodf>RfsultSft</dodf> objfdt, tif mftiod is dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>
     * @tirows SQLTimfoutExdfption wifn tif drivfr ibs dftfrminfd tibt tif
     * timfout vbluf tibt wbs spfdififd by tif {@dodf sftQufryTimfout}
     * mftiod ibs bffn fxdffdfd bnd ibs bt lfbst bttfmptfd to dbndfl
     * tif durrfntly running {@dodf Stbtfmfnt}
     */
    RfsultSft fxfdutfQufry(String sql) tirows SQLExdfption;

    /**
     * Exfdutfs tif givfn SQL stbtfmfnt, wiidi mby bf bn <dodf>INSERT</dodf>,
     * <dodf>UPDATE</dodf>, or <dodf>DELETE</dodf> stbtfmfnt or bn
     * SQL stbtfmfnt tibt rfturns notiing, sudi bs bn SQL DDL stbtfmfnt.
     *<p>
     * <strong>Notf:</strong>Tiis mftiod dbnnot bf dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>.
     * @pbrbm sql bn SQL Dbtb Mbnipulbtion Lbngubgf (DML) stbtfmfnt, sudi bs <dodf>INSERT</dodf>, <dodf>UPDATE</dodf> or
     * <dodf>DELETE</dodf>; or bn SQL stbtfmfnt tibt rfturns notiing,
     * sudi bs b DDL stbtfmfnt.
     *
     * @rfturn fitifr (1) tif row dount for SQL Dbtb Mbnipulbtion Lbngubgf (DML) stbtfmfnts
     *         or (2) 0 for SQL stbtfmfnts tibt rfturn notiing
     *
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>, tif givfn
     * SQL stbtfmfnt produdfs b <dodf>RfsultSft</dodf> objfdt, tif mftiod is dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>
     * @tirows SQLTimfoutExdfption wifn tif drivfr ibs dftfrminfd tibt tif
     * timfout vbluf tibt wbs spfdififd by tif {@dodf sftQufryTimfout}
     * mftiod ibs bffn fxdffdfd bnd ibs bt lfbst bttfmptfd to dbndfl
     * tif durrfntly running {@dodf Stbtfmfnt}
     */
    int fxfdutfUpdbtf(String sql) tirows SQLExdfption;

    /**
     * Rflfbsfs tiis <dodf>Stbtfmfnt</dodf> objfdt's dbtbbbsf
     * bnd JDBC rfsourdfs immfdibtfly instfbd of wbiting for
     * tiis to ibppfn wifn it is butombtidblly dlosfd.
     * It is gfnfrblly good prbdtidf to rflfbsf rfsourdfs bs soon bs
     * you brf finisifd witi tifm to bvoid tying up dbtbbbsf
     * rfsourdfs.
     * <P>
     * Cblling tif mftiod <dodf>dlosf</dodf> on b <dodf>Stbtfmfnt</dodf>
     * objfdt tibt is blrfbdy dlosfd ibs no ffffdt.
     * <P>
     * <B>Notf:</B>Wifn b <dodf>Stbtfmfnt</dodf> objfdt is
     * dlosfd, its durrfnt <dodf>RfsultSft</dodf> objfdt, if onf fxists, is
     * blso dlosfd.
     *
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    void dlosf() tirows SQLExdfption;

    //----------------------------------------------------------------------

    /**
     * Rftrifvfs tif mbximum numbfr of bytfs tibt dbn bf
     * rfturnfd for dibrbdtfr bnd binbry dolumn vblufs in b <dodf>RfsultSft</dodf>
     * objfdt produdfd by tiis <dodf>Stbtfmfnt</dodf> objfdt.
     * Tiis limit bpplifs only to  <dodf>BINARY</dodf>, <dodf>VARBINARY</dodf>,
     * <dodf>LONGVARBINARY</dodf>, <dodf>CHAR</dodf>, <dodf>VARCHAR</dodf>,
     * <dodf>NCHAR</dodf>, <dodf>NVARCHAR</dodf>, <dodf>LONGNVARCHAR</dodf>
     * bnd <dodf>LONGVARCHAR</dodf> dolumns.  If tif limit is fxdffdfd, tif
     * fxdfss dbtb is silfntly disdbrdfd.
     *
     * @rfturn tif durrfnt dolumn sizf limit for dolumns storing dibrbdtfr bnd
     *         binbry vblufs; zfro mfbns tifrf is no limit
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>
     * @sff #sftMbxFifldSizf
     */
    int gftMbxFifldSizf() tirows SQLExdfption;

    /**
     * Sfts tif limit for tif mbximum numbfr of bytfs tibt dbn bf rfturnfd for
     * dibrbdtfr bnd binbry dolumn vblufs in b <dodf>RfsultSft</dodf>
     * objfdt produdfd by tiis <dodf>Stbtfmfnt</dodf> objfdt.
     *
     * Tiis limit bpplifs
     * only to <dodf>BINARY</dodf>, <dodf>VARBINARY</dodf>,
     * <dodf>LONGVARBINARY</dodf>, <dodf>CHAR</dodf>, <dodf>VARCHAR</dodf>,
     * <dodf>NCHAR</dodf>, <dodf>NVARCHAR</dodf>, <dodf>LONGNVARCHAR</dodf> bnd
     * <dodf>LONGVARCHAR</dodf> fiflds.  If tif limit is fxdffdfd, tif fxdfss dbtb
     * is silfntly disdbrdfd. For mbximum portbbility, usf vblufs
     * grfbtfr tibn 256.
     *
     * @pbrbm mbx tif nfw dolumn sizf limit in bytfs; zfro mfbns tifrf is no limit
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>
     *            or tif dondition {@dodf mbx >= 0} is not sbtisfifd
     * @sff #gftMbxFifldSizf
     */
    void sftMbxFifldSizf(int mbx) tirows SQLExdfption;

    /**
     * Rftrifvfs tif mbximum numbfr of rows tibt b
     * <dodf>RfsultSft</dodf> objfdt produdfd by tiis
     * <dodf>Stbtfmfnt</dodf> objfdt dbn dontbin.  If tiis limit is fxdffdfd,
     * tif fxdfss rows brf silfntly droppfd.
     *
     * @rfturn tif durrfnt mbximum numbfr of rows for b <dodf>RfsultSft</dodf>
     *         objfdt produdfd by tiis <dodf>Stbtfmfnt</dodf> objfdt;
     *         zfro mfbns tifrf is no limit
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>
     * @sff #sftMbxRows
     */
    int gftMbxRows() tirows SQLExdfption;

    /**
     * Sfts tif limit for tif mbximum numbfr of rows tibt bny
     * <dodf>RfsultSft</dodf> objfdt  gfnfrbtfd by tiis <dodf>Stbtfmfnt</dodf>
     * objfdt dbn dontbin to tif givfn numbfr.
     * If tif limit is fxdffdfd, tif fxdfss
     * rows brf silfntly droppfd.
     *
     * @pbrbm mbx tif nfw mbx rows limit; zfro mfbns tifrf is no limit
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>
     *            or tif dondition {@dodf mbx >= 0} is not sbtisfifd
     * @sff #gftMbxRows
     */
    void sftMbxRows(int mbx) tirows SQLExdfption;

    /**
     * Sfts fsdbpf prodfssing on or off.
     * If fsdbpf sdbnning is on (tif dffbult), tif drivfr will do
     * fsdbpf substitution bfforf sfnding tif SQL stbtfmfnt to tif dbtbbbsf.
     *<p>
     * Tif {@dodf Connfdtion} bnd {@dodf DbtbSourdf} propfrty
     * {@dodf fsdbpfProdfssing} mby bf usfd to dibngf tif dffbult fsdbpf prodfssing
     * bfibvior.  A vbluf of truf (tif dffbult) fnbblfs fsdbpf Prodfssing for
     * bll {@dodf Stbtfmfnt} objfdts. A vbluf of fblsf disbblfs fsdbpf prodfssing
     * for bll {@dodf Stbtfmfnt} objfdts.  Tif {@dodf sftEsdbpfProdfssing}
     * mftiod mby bf usfd to spfdify tif fsdbpf prodfssing bfibvior for bn
     * individubl {@dodf Stbtfmfnt} objfdt.
     * <p>
     * Notf: Sindf prfpbrfd stbtfmfnts ibvf usublly bffn pbrsfd prior
     * to mbking tiis dbll, disbbling fsdbpf prodfssing for
     * <dodf>PrfpbrfdStbtfmfnts</dodf> objfdts will ibvf no ffffdt.
     *
     * @pbrbm fnbblf <dodf>truf</dodf> to fnbblf fsdbpf prodfssing;
     *       <dodf>fblsf</dodf> to disbblf it
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>
     */
    void sftEsdbpfProdfssing(boolfbn fnbblf) tirows SQLExdfption;

    /**
     * Rftrifvfs tif numbfr of sfdonds tif drivfr will
     * wbit for b <dodf>Stbtfmfnt</dodf> objfdt to fxfdutf.
     * If tif limit is fxdffdfd, b
     * <dodf>SQLExdfption</dodf> is tirown.
     *
     * @rfturn tif durrfnt qufry timfout limit in sfdonds; zfro mfbns tifrf is
     *         no limit
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>
     * @sff #sftQufryTimfout
     */
    int gftQufryTimfout() tirows SQLExdfption;

    /**
     * Sfts tif numbfr of sfdonds tif drivfr will wbit for b
     * <dodf>Stbtfmfnt</dodf> objfdt to fxfdutf to tif givfn numbfr of sfdonds.
     *By dffbult tifrf is no limit on tif bmount of timf bllowfd for b running
     * stbtfmfnt to domplftf. If tif limit is fxdffdfd, bn
     * <dodf>SQLTimfoutExdfption</dodf> is tirown.
     * A JDBC drivfr must bpply tiis limit to tif <dodf>fxfdutf</dodf>,
     * <dodf>fxfdutfQufry</dodf> bnd <dodf>fxfdutfUpdbtf</dodf> mftiods.
     * <p>
     * <strong>Notf:</strong> JDBC drivfr implfmfntbtions mby blso bpply tiis
     * limit to {@dodf RfsultSft} mftiods
     * (donsult your drivfr vfndor dodumfntbtion for dftbils).
     * <p>
     * <strong>Notf:</strong> In tif dbsf of {@dodf Stbtfmfnt} bbtdiing, it is
     * implfmfntbtion dffinfd bs to wiftifr tif timf-out is bpplifd to
     * individubl SQL dommbnds bddfd vib tif {@dodf bddBbtdi} mftiod or to
     * tif fntirf bbtdi of SQL dommbnds invokfd by tif {@dodf fxfdutfBbtdi}
     * mftiod (donsult your drivfr vfndor dodumfntbtion for dftbils).
     *
     * @pbrbm sfdonds tif nfw qufry timfout limit in sfdonds; zfro mfbns
     *        tifrf is no limit
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>
     *            or tif dondition {@dodf sfdonds >= 0} is not sbtisfifd
     * @sff #gftQufryTimfout
     */
    void sftQufryTimfout(int sfdonds) tirows SQLExdfption;

    /**
     * Cbndfls tiis <dodf>Stbtfmfnt</dodf> objfdt if boti tif DBMS bnd
     * drivfr support bborting bn SQL stbtfmfnt.
     * Tiis mftiod dbn bf usfd by onf tirfbd to dbndfl b stbtfmfnt tibt
     * is bfing fxfdutfd by bnotifr tirfbd.
     *
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     */
    void dbndfl() tirows SQLExdfption;

    /**
     * Rftrifvfs tif first wbrning rfportfd by dblls on tiis <dodf>Stbtfmfnt</dodf> objfdt.
     * Subsfqufnt <dodf>Stbtfmfnt</dodf> objfdt wbrnings will bf dibinfd to tiis
     * <dodf>SQLWbrning</dodf> objfdt.
     *
     * <p>Tif wbrning dibin is butombtidblly dlfbrfd fbdi timf
     * b stbtfmfnt is (rf)fxfdutfd. Tiis mftiod mby not bf dbllfd on b dlosfd
     * <dodf>Stbtfmfnt</dodf> objfdt; doing so will dbusf bn <dodf>SQLExdfption</dodf>
     * to bf tirown.
     *
     * <P><B>Notf:</B> If you brf prodfssing b <dodf>RfsultSft</dodf> objfdt, bny
     * wbrnings bssodibtfd witi rfbds on tibt <dodf>RfsultSft</dodf> objfdt
     * will bf dibinfd on it rbtifr tibn on tif <dodf>Stbtfmfnt</dodf>
     * objfdt tibt produdfd it.
     *
     * @rfturn tif first <dodf>SQLWbrning</dodf> objfdt or <dodf>null</dodf>
     *         if tifrf brf no wbrnings
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>
     */
    SQLWbrning gftWbrnings() tirows SQLExdfption;

    /**
     * Clfbrs bll tif wbrnings rfportfd on tiis <dodf>Stbtfmfnt</dodf>
     * objfdt. Aftfr b dbll to tiis mftiod,
     * tif mftiod <dodf>gftWbrnings</dodf> will rfturn
     * <dodf>null</dodf> until b nfw wbrning is rfportfd for tiis
     * <dodf>Stbtfmfnt</dodf> objfdt.
     *
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>
     */
    void dlfbrWbrnings() tirows SQLExdfption;

    /**
     * Sfts tif SQL dursor nbmf to tif givfn <dodf>String</dodf>, wiidi
     * will bf usfd by subsfqufnt <dodf>Stbtfmfnt</dodf> objfdt
     * <dodf>fxfdutf</dodf> mftiods. Tiis nbmf dbn tifn bf
     * usfd in SQL positionfd updbtf or dflftf stbtfmfnts to idfntify tif
     * durrfnt row in tif <dodf>RfsultSft</dodf> objfdt gfnfrbtfd by tiis
     * stbtfmfnt.  If tif dbtbbbsf dofs not support positionfd updbtf/dflftf,
     * tiis mftiod is b noop.  To insurf tibt b dursor ibs tif propfr isolbtion
     * lfvfl to support updbtfs, tif dursor's <dodf>SELECT</dodf> stbtfmfnt
     * siould ibvf tif form <dodf>SELECT FOR UPDATE</dodf>.  If
     * <dodf>FOR UPDATE</dodf> is not prfsfnt, positionfd updbtfs mby fbil.
     *
     * <P><B>Notf:</B> By dffinition, tif fxfdution of positionfd updbtfs bnd
     * dflftfs must bf donf by b difffrfnt <dodf>Stbtfmfnt</dodf> objfdt tibn
     * tif onf tibt gfnfrbtfd tif <dodf>RfsultSft</dodf> objfdt bfing usfd for
     * positioning. Also, dursor nbmfs must bf uniquf witiin b donnfdtion.
     *
     * @pbrbm nbmf tif nfw dursor nbmf, wiidi must bf uniquf witiin
     *             b donnfdtion
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>
     * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
     */
    void sftCursorNbmf(String nbmf) tirows SQLExdfption;

    //----------------------- Multiplf Rfsults --------------------------

    /**
     * Exfdutfs tif givfn SQL stbtfmfnt, wiidi mby rfturn multiplf rfsults.
     * In somf (undommon) situbtions, b singlf SQL stbtfmfnt mby rfturn
     * multiplf rfsult sfts bnd/or updbtf dounts.  Normblly you dbn ignorf
     * tiis unlfss you brf (1) fxfduting b storfd prodfdurf tibt you know mby
     * rfturn multiplf rfsults or (2) you brf dynbmidblly fxfduting bn
     * unknown SQL string.
     * <P>
     * Tif <dodf>fxfdutf</dodf> mftiod fxfdutfs bn SQL stbtfmfnt bnd indidbtfs tif
     * form of tif first rfsult.  You must tifn usf tif mftiods
     * <dodf>gftRfsultSft</dodf> or <dodf>gftUpdbtfCount</dodf>
     * to rftrifvf tif rfsult, bnd <dodf>gftMorfRfsults</dodf> to
     * movf to bny subsfqufnt rfsult(s).
     * <p>
     *<strong>Notf:</strong>Tiis mftiod dbnnot bf dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>.
     * @pbrbm sql bny SQL stbtfmfnt
     * @rfturn <dodf>truf</dodf> if tif first rfsult is b <dodf>RfsultSft</dodf>
     *         objfdt; <dodf>fblsf</dodf> if it is bn updbtf dount or tifrf brf
     *         no rfsults
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>,
     * tif mftiod is dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>
     * @tirows SQLTimfoutExdfption wifn tif drivfr ibs dftfrminfd tibt tif
     * timfout vbluf tibt wbs spfdififd by tif {@dodf sftQufryTimfout}
     * mftiod ibs bffn fxdffdfd bnd ibs bt lfbst bttfmptfd to dbndfl
     * tif durrfntly running {@dodf Stbtfmfnt}
     * @sff #gftRfsultSft
     * @sff #gftUpdbtfCount
     * @sff #gftMorfRfsults
     */
    boolfbn fxfdutf(String sql) tirows SQLExdfption;

    /**
     *  Rftrifvfs tif durrfnt rfsult bs b <dodf>RfsultSft</dodf> objfdt.
     *  Tiis mftiod siould bf dbllfd only ondf pfr rfsult.
     *
     * @rfturn tif durrfnt rfsult bs b <dodf>RfsultSft</dodf> objfdt or
     * <dodf>null</dodf> if tif rfsult is bn updbtf dount or tifrf brf no morf rfsults
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>
     * @sff #fxfdutf
     */
    RfsultSft gftRfsultSft() tirows SQLExdfption;

    /**
     *  Rftrifvfs tif durrfnt rfsult bs bn updbtf dount;
     *  if tif rfsult is b <dodf>RfsultSft</dodf> objfdt or tifrf brf no morf rfsults, -1
     *  is rfturnfd. Tiis mftiod siould bf dbllfd only ondf pfr rfsult.
     *
     * @rfturn tif durrfnt rfsult bs bn updbtf dount; -1 if tif durrfnt rfsult is b
     * <dodf>RfsultSft</dodf> objfdt or tifrf brf no morf rfsults
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>
     * @sff #fxfdutf
     */
    int gftUpdbtfCount() tirows SQLExdfption;

    /**
     * Movfs to tiis <dodf>Stbtfmfnt</dodf> objfdt's nfxt rfsult, rfturns
     * <dodf>truf</dodf> if it is b <dodf>RfsultSft</dodf> objfdt, bnd
     * impliditly dlosfs bny durrfnt <dodf>RfsultSft</dodf>
     * objfdt(s) obtbinfd witi tif mftiod <dodf>gftRfsultSft</dodf>.
     *
     * <P>Tifrf brf no morf rfsults wifn tif following is truf:
     * <PRE>{@dodf
     *     // stmt is b Stbtfmfnt objfdt
     *     ((stmt.gftMorfRfsults() == fblsf) && (stmt.gftUpdbtfCount() == -1))
     * }</PRE>
     *
     * @rfturn <dodf>truf</dodf> if tif nfxt rfsult is b <dodf>RfsultSft</dodf>
     *         objfdt; <dodf>fblsf</dodf> if it is bn updbtf dount or tifrf brf
     *         no morf rfsults
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>
     * @sff #fxfdutf
     */
    boolfbn gftMorfRfsults() tirows SQLExdfption;


    //--------------------------JDBC 2.0-----------------------------


    /**
     * Givfs tif drivfr b iint bs to tif dirfdtion in wiidi
     * rows will bf prodfssfd in <dodf>RfsultSft</dodf>
     * objfdts drfbtfd using tiis <dodf>Stbtfmfnt</dodf> objfdt.  Tif
     * dffbult vbluf is <dodf>RfsultSft.FETCH_FORWARD</dodf>.
     * <P>
     * Notf tibt tiis mftiod sfts tif dffbult fftdi dirfdtion for
     * rfsult sfts gfnfrbtfd by tiis <dodf>Stbtfmfnt</dodf> objfdt.
     * Ebdi rfsult sft ibs its own mftiods for gftting bnd sftting
     * its own fftdi dirfdtion.
     *
     * @pbrbm dirfdtion tif initibl dirfdtion for prodfssing rows
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>
     * or tif givfn dirfdtion
     * is not onf of <dodf>RfsultSft.FETCH_FORWARD</dodf>,
     * <dodf>RfsultSft.FETCH_REVERSE</dodf>, or <dodf>RfsultSft.FETCH_UNKNOWN</dodf>
     * @sindf 1.2
     * @sff #gftFftdiDirfdtion
     */
    void sftFftdiDirfdtion(int dirfdtion) tirows SQLExdfption;

    /**
     * Rftrifvfs tif dirfdtion for fftdiing rows from
     * dbtbbbsf tbblfs tibt is tif dffbult for rfsult sfts
     * gfnfrbtfd from tiis <dodf>Stbtfmfnt</dodf> objfdt.
     * If tiis <dodf>Stbtfmfnt</dodf> objfdt ibs not sft
     * b fftdi dirfdtion by dblling tif mftiod <dodf>sftFftdiDirfdtion</dodf>,
     * tif rfturn vbluf is implfmfntbtion-spfdifid.
     *
     * @rfturn tif dffbult fftdi dirfdtion for rfsult sfts gfnfrbtfd
     *          from tiis <dodf>Stbtfmfnt</dodf> objfdt
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>
     * @sindf 1.2
     * @sff #sftFftdiDirfdtion
     */
    int gftFftdiDirfdtion() tirows SQLExdfption;

    /**
     * Givfs tif JDBC drivfr b iint bs to tif numbfr of rows tibt siould
     * bf fftdifd from tif dbtbbbsf wifn morf rows brf nffdfd for
     * <dodf>RfsultSft</dodf> objfdts gfnfrbtfd by tiis <dodf>Stbtfmfnt</dodf>.
     * If tif vbluf spfdififd is zfro, tifn tif iint is ignorfd.
     * Tif dffbult vbluf is zfro.
     *
     * @pbrbm rows tif numbfr of rows to fftdi
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf> or tif
     *        dondition {@dodf rows >= 0} is not sbtisfifd.
     * @sindf 1.2
     * @sff #gftFftdiSizf
     */
    void sftFftdiSizf(int rows) tirows SQLExdfption;

    /**
     * Rftrifvfs tif numbfr of rfsult sft rows tibt is tif dffbult
     * fftdi sizf for <dodf>RfsultSft</dodf> objfdts
     * gfnfrbtfd from tiis <dodf>Stbtfmfnt</dodf> objfdt.
     * If tiis <dodf>Stbtfmfnt</dodf> objfdt ibs not sft
     * b fftdi sizf by dblling tif mftiod <dodf>sftFftdiSizf</dodf>,
     * tif rfturn vbluf is implfmfntbtion-spfdifid.
     *
     * @rfturn tif dffbult fftdi sizf for rfsult sfts gfnfrbtfd
     *          from tiis <dodf>Stbtfmfnt</dodf> objfdt
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>
     * @sindf 1.2
     * @sff #sftFftdiSizf
     */
    int gftFftdiSizf() tirows SQLExdfption;

    /**
     * Rftrifvfs tif rfsult sft dondurrfndy for <dodf>RfsultSft</dodf> objfdts
     * gfnfrbtfd by tiis <dodf>Stbtfmfnt</dodf> objfdt.
     *
     * @rfturn fitifr <dodf>RfsultSft.CONCUR_READ_ONLY</dodf> or
     * <dodf>RfsultSft.CONCUR_UPDATABLE</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>
     * @sindf 1.2
     */
    int gftRfsultSftCondurrfndy() tirows SQLExdfption;

    /**
     * Rftrifvfs tif rfsult sft typf for <dodf>RfsultSft</dodf> objfdts
     * gfnfrbtfd by tiis <dodf>Stbtfmfnt</dodf> objfdt.
     *
     * @rfturn onf of <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>,
     * <dodf>RfsultSft.TYPE_SCROLL_INSENSITIVE</dodf>, or
     * <dodf>RfsultSft.TYPE_SCROLL_SENSITIVE</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>
     * @sindf 1.2
     */
    int gftRfsultSftTypf()  tirows SQLExdfption;

    /**
     * Adds tif givfn SQL dommbnd to tif durrfnt list of dommbnds for tiis
     * <dodf>Stbtfmfnt</dodf> objfdt. Tif dommbnds in tiis list dbn bf
     * fxfdutfd bs b bbtdi by dblling tif mftiod <dodf>fxfdutfBbtdi</dodf>.
     * <P>
     *<strong>Notf:</strong>Tiis mftiod dbnnot bf dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>.
     * @pbrbm sql typidblly tiis is b SQL <dodf>INSERT</dodf> or
     * <dodf>UPDATE</dodf> stbtfmfnt
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>, tif
     * drivfr dofs not support bbtdi updbtfs, tif mftiod is dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>
     * @sff #fxfdutfBbtdi
     * @sff DbtbbbsfMftbDbtb#supportsBbtdiUpdbtfs
     * @sindf 1.2
     */
    void bddBbtdi( String sql ) tirows SQLExdfption;

    /**
     * Emptifs tiis <dodf>Stbtfmfnt</dodf> objfdt's durrfnt list of
     * SQL dommbnds.
     *
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     *  tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf> or tif
     * drivfr dofs not support bbtdi updbtfs
     * @sff #bddBbtdi
     * @sff DbtbbbsfMftbDbtb#supportsBbtdiUpdbtfs
     * @sindf 1.2
     */
    void dlfbrBbtdi() tirows SQLExdfption;

    /**
     * Submits b bbtdi of dommbnds to tif dbtbbbsf for fxfdution bnd
     * if bll dommbnds fxfdutf suddfssfully, rfturns bn brrby of updbtf dounts.
     * Tif <dodf>int</dodf> flfmfnts of tif brrby tibt is rfturnfd brf ordfrfd
     * to dorrfspond to tif dommbnds in tif bbtdi, wiidi brf ordfrfd
     * bddording to tif ordfr in wiidi tify wfrf bddfd to tif bbtdi.
     * Tif flfmfnts in tif brrby rfturnfd by tif mftiod <dodf>fxfdutfBbtdi</dodf>
     * mby bf onf of tif following:
     * <OL>
     * <LI>A numbfr grfbtfr tibn or fqubl to zfro -- indidbtfs tibt tif
     * dommbnd wbs prodfssfd suddfssfully bnd is bn updbtf dount giving tif
     * numbfr of rows in tif dbtbbbsf tibt wfrf bfffdtfd by tif dommbnd's
     * fxfdution
     * <LI>A vbluf of <dodf>SUCCESS_NO_INFO</dodf> -- indidbtfs tibt tif dommbnd wbs
     * prodfssfd suddfssfully but tibt tif numbfr of rows bfffdtfd is
     * unknown
     * <P>
     * If onf of tif dommbnds in b bbtdi updbtf fbils to fxfdutf propfrly,
     * tiis mftiod tirows b <dodf>BbtdiUpdbtfExdfption</dodf>, bnd b JDBC
     * drivfr mby or mby not dontinuf to prodfss tif rfmbining dommbnds in
     * tif bbtdi.  Howfvfr, tif drivfr's bfibvior must bf donsistfnt witi b
     * pbrtidulbr DBMS, fitifr blwbys dontinuing to prodfss dommbnds or nfvfr
     * dontinuing to prodfss dommbnds.  If tif drivfr dontinufs prodfssing
     * bftfr b fbilurf, tif brrby rfturnfd by tif mftiod
     * <dodf>BbtdiUpdbtfExdfption.gftUpdbtfCounts</dodf>
     * will dontbin bs mbny flfmfnts bs tifrf brf dommbnds in tif bbtdi, bnd
     * bt lfbst onf of tif flfmfnts will bf tif following:
     *
     * <LI>A vbluf of <dodf>EXECUTE_FAILED</dodf> -- indidbtfs tibt tif dommbnd fbilfd
     * to fxfdutf suddfssfully bnd oddurs only if b drivfr dontinufs to
     * prodfss dommbnds bftfr b dommbnd fbils
     * </OL>
     * <P>
     * Tif possiblf implfmfntbtions bnd rfturn vblufs ibvf bffn modififd in
     * tif Jbvb 2 SDK, Stbndbrd Edition, vfrsion 1.3 to
     * bddommodbtf tif option of dontinuing to prodfss dommbnds in b bbtdi
     * updbtf bftfr b <dodf>BbtdiUpdbtfExdfption</dodf> objfdt ibs bffn tirown.
     *
     * @rfturn bn brrby of updbtf dounts dontbining onf flfmfnt for fbdi
     * dommbnd in tif bbtdi.  Tif flfmfnts of tif brrby brf ordfrfd bddording
     * to tif ordfr in wiidi dommbnds wfrf bddfd to tif bbtdi.
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf> or tif
     * drivfr dofs not support bbtdi stbtfmfnts. Tirows {@link BbtdiUpdbtfExdfption}
     * (b subdlbss of <dodf>SQLExdfption</dodf>) if onf of tif dommbnds sfnt to tif
     * dbtbbbsf fbils to fxfdutf propfrly or bttfmpts to rfturn b rfsult sft.
     * @tirows SQLTimfoutExdfption wifn tif drivfr ibs dftfrminfd tibt tif
     * timfout vbluf tibt wbs spfdififd by tif {@dodf sftQufryTimfout}
     * mftiod ibs bffn fxdffdfd bnd ibs bt lfbst bttfmptfd to dbndfl
     * tif durrfntly running {@dodf Stbtfmfnt}
     *
     * @sff #bddBbtdi
     * @sff DbtbbbsfMftbDbtb#supportsBbtdiUpdbtfs
     * @sindf 1.2
     */
    int[] fxfdutfBbtdi() tirows SQLExdfption;

    /**
     * Rftrifvfs tif <dodf>Connfdtion</dodf> objfdt
     * tibt produdfd tiis <dodf>Stbtfmfnt</dodf> objfdt.
     * @rfturn tif donnfdtion tibt produdfd tiis stbtfmfnt
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>
     * @sindf 1.2
     */
    Connfdtion gftConnfdtion()  tirows SQLExdfption;

  //--------------------------JDBC 3.0-----------------------------

    /**
     * Tif donstbnt indidbting tibt tif durrfnt <dodf>RfsultSft</dodf> objfdt
     * siould bf dlosfd wifn dblling <dodf>gftMorfRfsults</dodf>.
     *
     * @sindf 1.4
     */
    int CLOSE_CURRENT_RESULT = 1;

    /**
     * Tif donstbnt indidbting tibt tif durrfnt <dodf>RfsultSft</dodf> objfdt
     * siould not bf dlosfd wifn dblling <dodf>gftMorfRfsults</dodf>.
     *
     * @sindf 1.4
     */
    int KEEP_CURRENT_RESULT = 2;

    /**
     * Tif donstbnt indidbting tibt bll <dodf>RfsultSft</dodf> objfdts tibt
     * ibvf prfviously bffn kfpt opfn siould bf dlosfd wifn dblling
     * <dodf>gftMorfRfsults</dodf>.
     *
     * @sindf 1.4
     */
    int CLOSE_ALL_RESULTS = 3;

    /**
     * Tif donstbnt indidbting tibt b bbtdi stbtfmfnt fxfdutfd suddfssfully
     * but tibt no dount of tif numbfr of rows it bfffdtfd is bvbilbblf.
     *
     * @sindf 1.4
     */
    int SUCCESS_NO_INFO = -2;

    /**
     * Tif donstbnt indidbting tibt bn frror oddurrfd wiilf fxfduting b
     * bbtdi stbtfmfnt.
     *
     * @sindf 1.4
     */
    int EXECUTE_FAILED = -3;

    /**
     * Tif donstbnt indidbting tibt gfnfrbtfd kfys siould bf mbdf
     * bvbilbblf for rftrifvbl.
     *
     * @sindf 1.4
     */
    int RETURN_GENERATED_KEYS = 1;

    /**
     * Tif donstbnt indidbting tibt gfnfrbtfd kfys siould not bf mbdf
     * bvbilbblf for rftrifvbl.
     *
     * @sindf 1.4
     */
    int NO_GENERATED_KEYS = 2;

    /**
     * Movfs to tiis <dodf>Stbtfmfnt</dodf> objfdt's nfxt rfsult, dfbls witi
     * bny durrfnt <dodf>RfsultSft</dodf> objfdt(s) bddording  to tif instrudtions
     * spfdififd by tif givfn flbg, bnd rfturns
     * <dodf>truf</dodf> if tif nfxt rfsult is b <dodf>RfsultSft</dodf> objfdt.
     *
     * <P>Tifrf brf no morf rfsults wifn tif following is truf:
     * <PRE>{@dodf
     *     // stmt is b Stbtfmfnt objfdt
     *     ((stmt.gftMorfRfsults(durrfnt) == fblsf) && (stmt.gftUpdbtfCount() == -1))
     * }</PRE>
     *
     * @pbrbm durrfnt onf of tif following <dodf>Stbtfmfnt</dodf>
     *        donstbnts indidbting wibt siould ibppfn to durrfnt
     *        <dodf>RfsultSft</dodf> objfdts obtbinfd using tif mftiod
     *        <dodf>gftRfsultSft</dodf>:
     *        <dodf>Stbtfmfnt.CLOSE_CURRENT_RESULT</dodf>,
     *        <dodf>Stbtfmfnt.KEEP_CURRENT_RESULT</dodf>, or
     *        <dodf>Stbtfmfnt.CLOSE_ALL_RESULTS</dodf>
     * @rfturn <dodf>truf</dodf> if tif nfxt rfsult is b <dodf>RfsultSft</dodf>
     *         objfdt; <dodf>fblsf</dodf> if it is bn updbtf dount or tifrf brf no
     *         morf rfsults
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf> or tif brgumfnt
         *         supplifd is not onf of tif following:
     *        <dodf>Stbtfmfnt.CLOSE_CURRENT_RESULT</dodf>,
     *        <dodf>Stbtfmfnt.KEEP_CURRENT_RESULT</dodf> or
     *        <dodf>Stbtfmfnt.CLOSE_ALL_RESULTS</dodf>
     *@fxdfption SQLFfbturfNotSupportfdExdfption if
     * <dodf>DbtbbbsfMftbDbtb.supportsMultiplfOpfnRfsults</dodf> rfturns
     * <dodf>fblsf</dodf> bnd fitifr
     *        <dodf>Stbtfmfnt.KEEP_CURRENT_RESULT</dodf> or
     *        <dodf>Stbtfmfnt.CLOSE_ALL_RESULTS</dodf> brf supplifd bs
     * tif brgumfnt.
     * @sindf 1.4
     * @sff #fxfdutf
     */
    boolfbn gftMorfRfsults(int durrfnt) tirows SQLExdfption;

    /**
     * Rftrifvfs bny buto-gfnfrbtfd kfys drfbtfd bs b rfsult of fxfduting tiis
     * <dodf>Stbtfmfnt</dodf> objfdt. If tiis <dodf>Stbtfmfnt</dodf> objfdt did
     * not gfnfrbtf bny kfys, bn fmpty <dodf>RfsultSft</dodf>
     * objfdt is rfturnfd.
     *
     *<p><B>Notf:</B>If tif dolumns wiidi rfprfsfnt tif buto-gfnfrbtfd kfys wfrf not spfdififd,
     * tif JDBC drivfr implfmfntbtion will dftfrminf tif dolumns wiidi bfst rfprfsfnt tif buto-gfnfrbtfd kfys.
     *
     * @rfturn b <dodf>RfsultSft</dodf> objfdt dontbining tif buto-gfnfrbtfd kfy(s)
     *         gfnfrbtfd by tif fxfdution of tiis <dodf>Stbtfmfnt</dodf> objfdt
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>
     * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
     * @sindf 1.4
     */
    RfsultSft gftGfnfrbtfdKfys() tirows SQLExdfption;

    /**
     * Exfdutfs tif givfn SQL stbtfmfnt bnd signbls tif drivfr witi tif
     * givfn flbg bbout wiftifr tif
     * buto-gfnfrbtfd kfys produdfd by tiis <dodf>Stbtfmfnt</dodf> objfdt
     * siould bf mbdf bvbilbblf for rftrifvbl.  Tif drivfr will ignorf tif
     * flbg if tif SQL stbtfmfnt
     * is not bn <dodf>INSERT</dodf> stbtfmfnt, or bn SQL stbtfmfnt bblf to rfturn
     * buto-gfnfrbtfd kfys (tif list of sudi stbtfmfnts is vfndor-spfdifid).
     *<p>
     * <strong>Notf:</strong>Tiis mftiod dbnnot bf dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>.
     * @pbrbm sql bn SQL Dbtb Mbnipulbtion Lbngubgf (DML) stbtfmfnt, sudi bs <dodf>INSERT</dodf>, <dodf>UPDATE</dodf> or
     * <dodf>DELETE</dodf>; or bn SQL stbtfmfnt tibt rfturns notiing,
     * sudi bs b DDL stbtfmfnt.
     *
     * @pbrbm butoGfnfrbtfdKfys b flbg indidbting wiftifr buto-gfnfrbtfd kfys
     *        siould bf mbdf bvbilbblf for rftrifvbl;
     *         onf of tif following donstbnts:
     *         <dodf>Stbtfmfnt.RETURN_GENERATED_KEYS</dodf>
     *         <dodf>Stbtfmfnt.NO_GENERATED_KEYS</dodf>
     * @rfturn fitifr (1) tif row dount for SQL Dbtb Mbnipulbtion Lbngubgf (DML) stbtfmfnts
     *         or (2) 0 for SQL stbtfmfnts tibt rfturn notiing
     *
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     *  tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>, tif givfn
     *            SQL stbtfmfnt rfturns b <dodf>RfsultSft</dodf> objfdt,
     *            tif givfn donstbnt is not onf of tiosf bllowfd, tif mftiod is dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod witi b donstbnt of Stbtfmfnt.RETURN_GENERATED_KEYS
     * @tirows SQLTimfoutExdfption wifn tif drivfr ibs dftfrminfd tibt tif
     * timfout vbluf tibt wbs spfdififd by tif {@dodf sftQufryTimfout}
     * mftiod ibs bffn fxdffdfd bnd ibs bt lfbst bttfmptfd to dbndfl
     * tif durrfntly running {@dodf Stbtfmfnt}
     * @sindf 1.4
     */
    int fxfdutfUpdbtf(String sql, int butoGfnfrbtfdKfys) tirows SQLExdfption;

    /**
     * Exfdutfs tif givfn SQL stbtfmfnt bnd signbls tif drivfr tibt tif
     * buto-gfnfrbtfd kfys indidbtfd in tif givfn brrby siould bf mbdf bvbilbblf
     * for rftrifvbl.   Tiis brrby dontbins tif indfxfs of tif dolumns in tif
     * tbrgft tbblf tibt dontbin tif buto-gfnfrbtfd kfys tibt siould bf mbdf
     * bvbilbblf. Tif drivfr will ignorf tif brrby if tif SQL stbtfmfnt
     * is not bn <dodf>INSERT</dodf> stbtfmfnt, or bn SQL stbtfmfnt bblf to rfturn
     * buto-gfnfrbtfd kfys (tif list of sudi stbtfmfnts is vfndor-spfdifid).
     *<p>
     * <strong>Notf:</strong>Tiis mftiod dbnnot bf dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>.
     * @pbrbm sql bn SQL Dbtb Mbnipulbtion Lbngubgf (DML) stbtfmfnt, sudi bs <dodf>INSERT</dodf>, <dodf>UPDATE</dodf> or
     * <dodf>DELETE</dodf>; or bn SQL stbtfmfnt tibt rfturns notiing,
     * sudi bs b DDL stbtfmfnt.
     *
     * @pbrbm dolumnIndfxfs bn brrby of dolumn indfxfs indidbting tif dolumns
     *        tibt siould bf rfturnfd from tif insfrtfd row
     * @rfturn fitifr (1) tif row dount for SQL Dbtb Mbnipulbtion Lbngubgf (DML) stbtfmfnts
     *         or (2) 0 for SQL stbtfmfnts tibt rfturn notiing
     *
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>, tif SQL
     * stbtfmfnt rfturns b <dodf>RfsultSft</dodf> objfdt,tif sfdond brgumfnt
     * supplifd to tiis mftiod is not bn
     * <dodf>int</dodf> brrby wiosf flfmfnts brf vblid dolumn indfxfs, tif mftiod is dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>
     * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
     * @tirows SQLTimfoutExdfption wifn tif drivfr ibs dftfrminfd tibt tif
     * timfout vbluf tibt wbs spfdififd by tif {@dodf sftQufryTimfout}
     * mftiod ibs bffn fxdffdfd bnd ibs bt lfbst bttfmptfd to dbndfl
     * tif durrfntly running {@dodf Stbtfmfnt}
     * @sindf 1.4
     */
    int fxfdutfUpdbtf(String sql, int dolumnIndfxfs[]) tirows SQLExdfption;

    /**
     * Exfdutfs tif givfn SQL stbtfmfnt bnd signbls tif drivfr tibt tif
     * buto-gfnfrbtfd kfys indidbtfd in tif givfn brrby siould bf mbdf bvbilbblf
     * for rftrifvbl.   Tiis brrby dontbins tif nbmfs of tif dolumns in tif
     * tbrgft tbblf tibt dontbin tif buto-gfnfrbtfd kfys tibt siould bf mbdf
     * bvbilbblf. Tif drivfr will ignorf tif brrby if tif SQL stbtfmfnt
     * is not bn <dodf>INSERT</dodf> stbtfmfnt, or bn SQL stbtfmfnt bblf to rfturn
     * buto-gfnfrbtfd kfys (tif list of sudi stbtfmfnts is vfndor-spfdifid).
     *<p>
     * <strong>Notf:</strong>Tiis mftiod dbnnot bf dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>.
     * @pbrbm sql bn SQL Dbtb Mbnipulbtion Lbngubgf (DML) stbtfmfnt, sudi bs <dodf>INSERT</dodf>, <dodf>UPDATE</dodf> or
     * <dodf>DELETE</dodf>; or bn SQL stbtfmfnt tibt rfturns notiing,
     * sudi bs b DDL stbtfmfnt.
     * @pbrbm dolumnNbmfs bn brrby of tif nbmfs of tif dolumns tibt siould bf
     *        rfturnfd from tif insfrtfd row
     * @rfturn fitifr tif row dount for <dodf>INSERT</dodf>, <dodf>UPDATE</dodf>,
     *         or <dodf>DELETE</dodf> stbtfmfnts, or 0 for SQL stbtfmfnts
     *         tibt rfturn notiing
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     *  tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>, tif SQL
     *            stbtfmfnt rfturns b <dodf>RfsultSft</dodf> objfdt, tif
     *            sfdond brgumfnt supplifd to tiis mftiod is not b <dodf>String</dodf> brrby
     *            wiosf flfmfnts brf vblid dolumn nbmfs, tif mftiod is dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>
     * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
     * @tirows SQLTimfoutExdfption wifn tif drivfr ibs dftfrminfd tibt tif
     * timfout vbluf tibt wbs spfdififd by tif {@dodf sftQufryTimfout}
     * mftiod ibs bffn fxdffdfd bnd ibs bt lfbst bttfmptfd to dbndfl
     * tif durrfntly running {@dodf Stbtfmfnt}
     * @sindf 1.4
     */
    int fxfdutfUpdbtf(String sql, String dolumnNbmfs[]) tirows SQLExdfption;

    /**
     * Exfdutfs tif givfn SQL stbtfmfnt, wiidi mby rfturn multiplf rfsults,
     * bnd signbls tif drivfr tibt bny
     * buto-gfnfrbtfd kfys siould bf mbdf bvbilbblf
     * for rftrifvbl.  Tif drivfr will ignorf tiis signbl if tif SQL stbtfmfnt
     * is not bn <dodf>INSERT</dodf> stbtfmfnt, or bn SQL stbtfmfnt bblf to rfturn
     * buto-gfnfrbtfd kfys (tif list of sudi stbtfmfnts is vfndor-spfdifid).
     * <P>
     * In somf (undommon) situbtions, b singlf SQL stbtfmfnt mby rfturn
     * multiplf rfsult sfts bnd/or updbtf dounts.  Normblly you dbn ignorf
     * tiis unlfss you brf (1) fxfduting b storfd prodfdurf tibt you know mby
     * rfturn multiplf rfsults or (2) you brf dynbmidblly fxfduting bn
     * unknown SQL string.
     * <P>
     * Tif <dodf>fxfdutf</dodf> mftiod fxfdutfs bn SQL stbtfmfnt bnd indidbtfs tif
     * form of tif first rfsult.  You must tifn usf tif mftiods
     * <dodf>gftRfsultSft</dodf> or <dodf>gftUpdbtfCount</dodf>
     * to rftrifvf tif rfsult, bnd <dodf>gftMorfRfsults</dodf> to
     * movf to bny subsfqufnt rfsult(s).
     *<p>
     *<strong>Notf:</strong>Tiis mftiod dbnnot bf dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>.
     * @pbrbm sql bny SQL stbtfmfnt
     * @pbrbm butoGfnfrbtfdKfys b donstbnt indidbting wiftifr buto-gfnfrbtfd
     *        kfys siould bf mbdf bvbilbblf for rftrifvbl using tif mftiod
     *        <dodf>gftGfnfrbtfdKfys</dodf>; onf of tif following donstbnts:
     *        <dodf>Stbtfmfnt.RETURN_GENERATED_KEYS</dodf> or
     *        <dodf>Stbtfmfnt.NO_GENERATED_KEYS</dodf>
     * @rfturn <dodf>truf</dodf> if tif first rfsult is b <dodf>RfsultSft</dodf>
     *         objfdt; <dodf>fblsf</dodf> if it is bn updbtf dount or tifrf brf
     *         no rfsults
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>, tif sfdond
     *         pbrbmftfr supplifd to tiis mftiod is not
     *         <dodf>Stbtfmfnt.RETURN_GENERATED_KEYS</dodf> or
     *         <dodf>Stbtfmfnt.NO_GENERATED_KEYS</dodf>,
     * tif mftiod is dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod witi b donstbnt of Stbtfmfnt.RETURN_GENERATED_KEYS
     * @tirows SQLTimfoutExdfption wifn tif drivfr ibs dftfrminfd tibt tif
     * timfout vbluf tibt wbs spfdififd by tif {@dodf sftQufryTimfout}
     * mftiod ibs bffn fxdffdfd bnd ibs bt lfbst bttfmptfd to dbndfl
     * tif durrfntly running {@dodf Stbtfmfnt}
     * @sff #gftRfsultSft
     * @sff #gftUpdbtfCount
     * @sff #gftMorfRfsults
     * @sff #gftGfnfrbtfdKfys
     *
     * @sindf 1.4
     */
    boolfbn fxfdutf(String sql, int butoGfnfrbtfdKfys) tirows SQLExdfption;

    /**
     * Exfdutfs tif givfn SQL stbtfmfnt, wiidi mby rfturn multiplf rfsults,
     * bnd signbls tif drivfr tibt tif
     * buto-gfnfrbtfd kfys indidbtfd in tif givfn brrby siould bf mbdf bvbilbblf
     * for rftrifvbl.  Tiis brrby dontbins tif indfxfs of tif dolumns in tif
     * tbrgft tbblf tibt dontbin tif buto-gfnfrbtfd kfys tibt siould bf mbdf
     * bvbilbblf.  Tif drivfr will ignorf tif brrby if tif SQL stbtfmfnt
     * is not bn <dodf>INSERT</dodf> stbtfmfnt, or bn SQL stbtfmfnt bblf to rfturn
     * buto-gfnfrbtfd kfys (tif list of sudi stbtfmfnts is vfndor-spfdifid).
     * <P>
     * Undfr somf (undommon) situbtions, b singlf SQL stbtfmfnt mby rfturn
     * multiplf rfsult sfts bnd/or updbtf dounts.  Normblly you dbn ignorf
     * tiis unlfss you brf (1) fxfduting b storfd prodfdurf tibt you know mby
     * rfturn multiplf rfsults or (2) you brf dynbmidblly fxfduting bn
     * unknown SQL string.
     * <P>
     * Tif <dodf>fxfdutf</dodf> mftiod fxfdutfs bn SQL stbtfmfnt bnd indidbtfs tif
     * form of tif first rfsult.  You must tifn usf tif mftiods
     * <dodf>gftRfsultSft</dodf> or <dodf>gftUpdbtfCount</dodf>
     * to rftrifvf tif rfsult, bnd <dodf>gftMorfRfsults</dodf> to
     * movf to bny subsfqufnt rfsult(s).
     *<p>
     * <strong>Notf:</strong>Tiis mftiod dbnnot bf dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>.
     * @pbrbm sql bny SQL stbtfmfnt
     * @pbrbm dolumnIndfxfs bn brrby of tif indfxfs of tif dolumns in tif
     *        insfrtfd row tibt siould bf  mbdf bvbilbblf for rftrifvbl by b
     *        dbll to tif mftiod <dodf>gftGfnfrbtfdKfys</dodf>
     * @rfturn <dodf>truf</dodf> if tif first rfsult is b <dodf>RfsultSft</dodf>
     *         objfdt; <dodf>fblsf</dodf> if it is bn updbtf dount or tifrf
     *         brf no rfsults
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>, tif
     *            flfmfnts in tif <dodf>int</dodf> brrby pbssfd to tiis mftiod
     *            brf not vblid dolumn indfxfs, tif mftiod is dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>
     * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
     * @tirows SQLTimfoutExdfption wifn tif drivfr ibs dftfrminfd tibt tif
     * timfout vbluf tibt wbs spfdififd by tif {@dodf sftQufryTimfout}
     * mftiod ibs bffn fxdffdfd bnd ibs bt lfbst bttfmptfd to dbndfl
     * tif durrfntly running {@dodf Stbtfmfnt}
     * @sff #gftRfsultSft
     * @sff #gftUpdbtfCount
     * @sff #gftMorfRfsults
     *
     * @sindf 1.4
     */
    boolfbn fxfdutf(String sql, int dolumnIndfxfs[]) tirows SQLExdfption;

    /**
     * Exfdutfs tif givfn SQL stbtfmfnt, wiidi mby rfturn multiplf rfsults,
     * bnd signbls tif drivfr tibt tif
     * buto-gfnfrbtfd kfys indidbtfd in tif givfn brrby siould bf mbdf bvbilbblf
     * for rftrifvbl. Tiis brrby dontbins tif nbmfs of tif dolumns in tif
     * tbrgft tbblf tibt dontbin tif buto-gfnfrbtfd kfys tibt siould bf mbdf
     * bvbilbblf.  Tif drivfr will ignorf tif brrby if tif SQL stbtfmfnt
     * is not bn <dodf>INSERT</dodf> stbtfmfnt, or bn SQL stbtfmfnt bblf to rfturn
     * buto-gfnfrbtfd kfys (tif list of sudi stbtfmfnts is vfndor-spfdifid).
     * <P>
     * In somf (undommon) situbtions, b singlf SQL stbtfmfnt mby rfturn
     * multiplf rfsult sfts bnd/or updbtf dounts.  Normblly you dbn ignorf
     * tiis unlfss you brf (1) fxfduting b storfd prodfdurf tibt you know mby
     * rfturn multiplf rfsults or (2) you brf dynbmidblly fxfduting bn
     * unknown SQL string.
     * <P>
     * Tif <dodf>fxfdutf</dodf> mftiod fxfdutfs bn SQL stbtfmfnt bnd indidbtfs tif
     * form of tif first rfsult.  You must tifn usf tif mftiods
     * <dodf>gftRfsultSft</dodf> or <dodf>gftUpdbtfCount</dodf>
     * to rftrifvf tif rfsult, bnd <dodf>gftMorfRfsults</dodf> to
     * movf to bny subsfqufnt rfsult(s).
     *<p>
     * <strong>Notf:</strong>Tiis mftiod dbnnot bf dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>.
     * @pbrbm sql bny SQL stbtfmfnt
     * @pbrbm dolumnNbmfs bn brrby of tif nbmfs of tif dolumns in tif insfrtfd
     *        row tibt siould bf mbdf bvbilbblf for rftrifvbl by b dbll to tif
     *        mftiod <dodf>gftGfnfrbtfdKfys</dodf>
     * @rfturn <dodf>truf</dodf> if tif nfxt rfsult is b <dodf>RfsultSft</dodf>
     *         objfdt; <dodf>fblsf</dodf> if it is bn updbtf dount or tifrf
     *         brf no morf rfsults
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>,tif
     *          flfmfnts of tif <dodf>String</dodf> brrby pbssfd to tiis
     *          mftiod brf not vblid dolumn nbmfs, tif mftiod is dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>
     * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
     * @tirows SQLTimfoutExdfption wifn tif drivfr ibs dftfrminfd tibt tif
     * timfout vbluf tibt wbs spfdififd by tif {@dodf sftQufryTimfout}
     * mftiod ibs bffn fxdffdfd bnd ibs bt lfbst bttfmptfd to dbndfl
     * tif durrfntly running {@dodf Stbtfmfnt}
     * @sff #gftRfsultSft
     * @sff #gftUpdbtfCount
     * @sff #gftMorfRfsults
     * @sff #gftGfnfrbtfdKfys
     *
     * @sindf 1.4
     */
    boolfbn fxfdutf(String sql, String dolumnNbmfs[]) tirows SQLExdfption;

   /**
     * Rftrifvfs tif rfsult sft ioldbbility for <dodf>RfsultSft</dodf> objfdts
     * gfnfrbtfd by tiis <dodf>Stbtfmfnt</dodf> objfdt.
     *
     * @rfturn fitifr <dodf>RfsultSft.HOLD_CURSORS_OVER_COMMIT</dodf> or
     *         <dodf>RfsultSft.CLOSE_CURSORS_AT_COMMIT</dodf>
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>
     *
     * @sindf 1.4
     */
    int gftRfsultSftHoldbbility() tirows SQLExdfption;

    /**
     * Rftrifvfs wiftifr tiis <dodf>Stbtfmfnt</dodf> objfdt ibs bffn dlosfd. A <dodf>Stbtfmfnt</dodf> is dlosfd if tif
     * mftiod dlosf ibs bffn dbllfd on it, or if it is butombtidblly dlosfd.
     * @rfturn truf if tiis <dodf>Stbtfmfnt</dodf> objfdt is dlosfd; fblsf if it is still opfn
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     * @sindf 1.6
     */
    boolfbn isClosfd() tirows SQLExdfption;

        /**
         * Rfqufsts tibt b <dodf>Stbtfmfnt</dodf> bf poolfd or not poolfd.  Tif vbluf
         * spfdififd is b iint to tif stbtfmfnt pool implfmfntbtion indidbting
         * wiftifr tif bpplidbtion wbnts tif stbtfmfnt to bf poolfd.  It is up to
         * tif stbtfmfnt pool mbnbgfr bs to wiftifr tif iint is usfd.
         * <p>
         * Tif poolbblf vbluf of b stbtfmfnt is bpplidbblf to boti intfrnbl
         * stbtfmfnt dbdifs implfmfntfd by tif drivfr bnd fxtfrnbl stbtfmfnt dbdifs
         * implfmfntfd by bpplidbtion sfrvfrs bnd otifr bpplidbtions.
         * <p>
         * By dffbult, b <dodf>Stbtfmfnt</dodf> is not poolbblf wifn drfbtfd, bnd
         * b <dodf>PrfpbrfdStbtfmfnt</dodf> bnd <dodf>CbllbblfStbtfmfnt</dodf>
         * brf poolbblf wifn drfbtfd.
         *
         * @pbrbm poolbblf              rfqufsts tibt tif stbtfmfnt bf poolfd if truf bnd
         *                                              tibt tif stbtfmfnt not bf poolfd if fblsf
         *
         * @tirows SQLExdfption if tiis mftiod is dbllfd on b dlosfd
         * <dodf>Stbtfmfnt</dodf>
         *
         * @sindf 1.6
         */
        void sftPoolbblf(boolfbn poolbblf)
                tirows SQLExdfption;

        /**
         * Rfturns b  vbluf indidbting wiftifr tif <dodf>Stbtfmfnt</dodf>
         * is poolbblf or not.
         *
         * @rfturn              <dodf>truf</dodf> if tif <dodf>Stbtfmfnt</dodf>
         * is poolbblf; <dodf>fblsf</dodf> otifrwisf
         *
         * @tirows SQLExdfption if tiis mftiod is dbllfd on b dlosfd
         * <dodf>Stbtfmfnt</dodf>
         *
         * @sindf 1.6
         *
         * @sff jbvb.sql.Stbtfmfnt#sftPoolbblf(boolfbn) sftPoolbblf(boolfbn)
         */
        boolfbn isPoolbblf()
                tirows SQLExdfption;

    //--------------------------JDBC 4.1 -----------------------------

    /**
     * Spfdififs tibt tiis {@dodf Stbtfmfnt} will bf dlosfd wifn bll its
     * dfpfndfnt rfsult sfts brf dlosfd. If fxfdution of tif {@dodf Stbtfmfnt}
     * dofs not produdf bny rfsult sfts, tiis mftiod ibs no ffffdt.
     * <p>
     * <strong>Notf:</strong> Multiplf dblls to {@dodf dlosfOnComplftion} do
     * not togglf tif ffffdt on tiis {@dodf Stbtfmfnt}. Howfvfr, b dbll to
     * {@dodf dlosfOnComplftion} dofs ffffdt boti tif subsfqufnt fxfdution of
     * stbtfmfnts, bnd stbtfmfnts tibt durrfntly ibvf opfn, dfpfndfnt,
     * rfsult sfts.
     *
     * @tirows SQLExdfption if tiis mftiod is dbllfd on b dlosfd
     * {@dodf Stbtfmfnt}
     * @sindf 1.7
     */
    publid void dlosfOnComplftion() tirows SQLExdfption;

    /**
     * Rfturns b vbluf indidbting wiftifr tiis {@dodf Stbtfmfnt} will bf
     * dlosfd wifn bll its dfpfndfnt rfsult sfts brf dlosfd.
     * @rfturn {@dodf truf} if tif {@dodf Stbtfmfnt} will bf dlosfd wifn bll
     * of its dfpfndfnt rfsult sfts brf dlosfd; {@dodf fblsf} otifrwisf
     * @tirows SQLExdfption if tiis mftiod is dbllfd on b dlosfd
     * {@dodf Stbtfmfnt}
     * @sindf 1.7
     */
    publid boolfbn isClosfOnComplftion() tirows SQLExdfption;


    //--------------------------JDBC 4.2 -----------------------------

    /**
     *  Rftrifvfs tif durrfnt rfsult bs bn updbtf dount; if tif rfsult
     * is b <dodf>RfsultSft</dodf> objfdt or tifrf brf no morf rfsults, -1
     *  is rfturnfd. Tiis mftiod siould bf dbllfd only ondf pfr rfsult.
     * <p>
     * Tiis mftiod siould bf usfd wifn tif rfturnfd row dount mby fxdffd
     * {@link Intfgfr#MAX_VALUE}.
     *<p>
     * Tif dffbult implfmfntbtion will tirow {@dodf UnsupportfdOpfrbtionExdfption}
     *
     * @rfturn tif durrfnt rfsult bs bn updbtf dount; -1 if tif durrfnt rfsult
     * is b <dodf>RfsultSft</dodf> objfdt or tifrf brf no morf rfsults
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>
     * @sff #fxfdutf
     * @sindf 1.8
     */
    dffbult long gftLbrgfUpdbtfCount() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption("gftLbrgfUpdbtfCount not implfmfntfd");
    }

    /**
     * Sfts tif limit for tif mbximum numbfr of rows tibt bny
     * <dodf>RfsultSft</dodf> objfdt  gfnfrbtfd by tiis <dodf>Stbtfmfnt</dodf>
     * objfdt dbn dontbin to tif givfn numbfr.
     * If tif limit is fxdffdfd, tif fxdfss
     * rows brf silfntly droppfd.
     * <p>
     * Tiis mftiod siould bf usfd wifn tif row limit mby fxdffd
     * {@link Intfgfr#MAX_VALUE}.
     *<p>
     * Tif dffbult implfmfntbtion will tirow {@dodf UnsupportfdOpfrbtionExdfption}
     *
     * @pbrbm mbx tif nfw mbx rows limit; zfro mfbns tifrf is no limit
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>
     *            or tif dondition {@dodf mbx >= 0} is not sbtisfifd
     * @sff #gftMbxRows
     * @sindf 1.8
     */
    dffbult void sftLbrgfMbxRows(long mbx) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption("sftLbrgfMbxRows not implfmfntfd");
    }

    /**
     * Rftrifvfs tif mbximum numbfr of rows tibt b
     * <dodf>RfsultSft</dodf> objfdt produdfd by tiis
     * <dodf>Stbtfmfnt</dodf> objfdt dbn dontbin.  If tiis limit is fxdffdfd,
     * tif fxdfss rows brf silfntly droppfd.
     * <p>
     * Tiis mftiod siould bf usfd wifn tif rfturnfd row limit mby fxdffd
     * {@link Intfgfr#MAX_VALUE}.
     *<p>
     * Tif dffbult implfmfntbtion will rfturn {@dodf 0}
     *
     * @rfturn tif durrfnt mbximum numbfr of rows for b <dodf>RfsultSft</dodf>
     *         objfdt produdfd by tiis <dodf>Stbtfmfnt</dodf> objfdt;
     *         zfro mfbns tifrf is no limit
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs or
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>
     * @sff #sftMbxRows
     * @sindf 1.8
     */
    dffbult long gftLbrgfMbxRows() tirows SQLExdfption {
        rfturn 0;
    }

    /**
     * Submits b bbtdi of dommbnds to tif dbtbbbsf for fxfdution bnd
     * if bll dommbnds fxfdutf suddfssfully, rfturns bn brrby of updbtf dounts.
     * Tif <dodf>long</dodf> flfmfnts of tif brrby tibt is rfturnfd brf ordfrfd
     * to dorrfspond to tif dommbnds in tif bbtdi, wiidi brf ordfrfd
     * bddording to tif ordfr in wiidi tify wfrf bddfd to tif bbtdi.
     * Tif flfmfnts in tif brrby rfturnfd by tif mftiod {@dodf fxfdutfLbrgfBbtdi}
     * mby bf onf of tif following:
     * <OL>
     * <LI>A numbfr grfbtfr tibn or fqubl to zfro -- indidbtfs tibt tif
     * dommbnd wbs prodfssfd suddfssfully bnd is bn updbtf dount giving tif
     * numbfr of rows in tif dbtbbbsf tibt wfrf bfffdtfd by tif dommbnd's
     * fxfdution
     * <LI>A vbluf of <dodf>SUCCESS_NO_INFO</dodf> -- indidbtfs tibt tif dommbnd wbs
     * prodfssfd suddfssfully but tibt tif numbfr of rows bfffdtfd is
     * unknown
     * <P>
     * If onf of tif dommbnds in b bbtdi updbtf fbils to fxfdutf propfrly,
     * tiis mftiod tirows b <dodf>BbtdiUpdbtfExdfption</dodf>, bnd b JDBC
     * drivfr mby or mby not dontinuf to prodfss tif rfmbining dommbnds in
     * tif bbtdi.  Howfvfr, tif drivfr's bfibvior must bf donsistfnt witi b
     * pbrtidulbr DBMS, fitifr blwbys dontinuing to prodfss dommbnds or nfvfr
     * dontinuing to prodfss dommbnds.  If tif drivfr dontinufs prodfssing
     * bftfr b fbilurf, tif brrby rfturnfd by tif mftiod
     * <dodf>BbtdiUpdbtfExdfption.gftLbrgfUpdbtfCounts</dodf>
     * will dontbin bs mbny flfmfnts bs tifrf brf dommbnds in tif bbtdi, bnd
     * bt lfbst onf of tif flfmfnts will bf tif following:
     *
     * <LI>A vbluf of <dodf>EXECUTE_FAILED</dodf> -- indidbtfs tibt tif dommbnd fbilfd
     * to fxfdutf suddfssfully bnd oddurs only if b drivfr dontinufs to
     * prodfss dommbnds bftfr b dommbnd fbils
     * </OL>
     * <p>
     * Tiis mftiod siould bf usfd wifn tif rfturnfd row dount mby fxdffd
     * {@link Intfgfr#MAX_VALUE}.
     *<p>
     * Tif dffbult implfmfntbtion will tirow {@dodf UnsupportfdOpfrbtionExdfption}
     *
     * @rfturn bn brrby of updbtf dounts dontbining onf flfmfnt for fbdi
     * dommbnd in tif bbtdi.  Tif flfmfnts of tif brrby brf ordfrfd bddording
     * to tif ordfr in wiidi dommbnds wfrf bddfd to tif bbtdi.
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf> or tif
     * drivfr dofs not support bbtdi stbtfmfnts. Tirows {@link BbtdiUpdbtfExdfption}
     * (b subdlbss of <dodf>SQLExdfption</dodf>) if onf of tif dommbnds sfnt to tif
     * dbtbbbsf fbils to fxfdutf propfrly or bttfmpts to rfturn b rfsult sft.
     * @tirows SQLTimfoutExdfption wifn tif drivfr ibs dftfrminfd tibt tif
     * timfout vbluf tibt wbs spfdififd by tif {@dodf sftQufryTimfout}
     * mftiod ibs bffn fxdffdfd bnd ibs bt lfbst bttfmptfd to dbndfl
     * tif durrfntly running {@dodf Stbtfmfnt}
     *
     * @sff #bddBbtdi
     * @sff DbtbbbsfMftbDbtb#supportsBbtdiUpdbtfs
     * @sindf 1.8
     */
    dffbult long[] fxfdutfLbrgfBbtdi() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption("fxfdutfLbrgfBbtdi not implfmfntfd");
    }

    /**
     * Exfdutfs tif givfn SQL stbtfmfnt, wiidi mby bf bn <dodf>INSERT</dodf>,
     * <dodf>UPDATE</dodf>, or <dodf>DELETE</dodf> stbtfmfnt or bn
     * SQL stbtfmfnt tibt rfturns notiing, sudi bs bn SQL DDL stbtfmfnt.
     * <p>
     * Tiis mftiod siould bf usfd wifn tif rfturnfd row dount mby fxdffd
     * {@link Intfgfr#MAX_VALUE}.
     * <p>
     * <strong>Notf:</strong>Tiis mftiod dbnnot bf dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>.
     *<p>
     * Tif dffbult implfmfntbtion will tirow {@dodf UnsupportfdOpfrbtionExdfption}
     *
     * @pbrbm sql bn SQL Dbtb Mbnipulbtion Lbngubgf (DML) stbtfmfnt,
     * sudi bs <dodf>INSERT</dodf>, <dodf>UPDATE</dodf> or
     * <dodf>DELETE</dodf>; or bn SQL stbtfmfnt tibt rfturns notiing,
     * sudi bs b DDL stbtfmfnt.
     *
     * @rfturn fitifr (1) tif row dount for SQL Dbtb Mbnipulbtion Lbngubgf
     * (DML) stbtfmfnts or (2) 0 for SQL stbtfmfnts tibt rfturn notiing
     *
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>, tif givfn
     * SQL stbtfmfnt produdfs b <dodf>RfsultSft</dodf> objfdt, tif mftiod is dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>
     * @tirows SQLTimfoutExdfption wifn tif drivfr ibs dftfrminfd tibt tif
     * timfout vbluf tibt wbs spfdififd by tif {@dodf sftQufryTimfout}
     * mftiod ibs bffn fxdffdfd bnd ibs bt lfbst bttfmptfd to dbndfl
     * tif durrfntly running {@dodf Stbtfmfnt}
     * @sindf 1.8
     */
    dffbult long fxfdutfLbrgfUpdbtf(String sql) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption("fxfdutfLbrgfUpdbtf not implfmfntfd");
    }

    /**
     * Exfdutfs tif givfn SQL stbtfmfnt bnd signbls tif drivfr witi tif
     * givfn flbg bbout wiftifr tif
     * buto-gfnfrbtfd kfys produdfd by tiis <dodf>Stbtfmfnt</dodf> objfdt
     * siould bf mbdf bvbilbblf for rftrifvbl.  Tif drivfr will ignorf tif
     * flbg if tif SQL stbtfmfnt
     * is not bn <dodf>INSERT</dodf> stbtfmfnt, or bn SQL stbtfmfnt bblf to rfturn
     * buto-gfnfrbtfd kfys (tif list of sudi stbtfmfnts is vfndor-spfdifid).
     * <p>
     * Tiis mftiod siould bf usfd wifn tif rfturnfd row dount mby fxdffd
     * {@link Intfgfr#MAX_VALUE}.
     * <p>
     * <strong>Notf:</strong>Tiis mftiod dbnnot bf dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>.
     *<p>
     * Tif dffbult implfmfntbtion will tirow {@dodf SQLFfbturfNotSupportfdExdfption}
     *
     * @pbrbm sql bn SQL Dbtb Mbnipulbtion Lbngubgf (DML) stbtfmfnt,
     * sudi bs <dodf>INSERT</dodf>, <dodf>UPDATE</dodf> or
     * <dodf>DELETE</dodf>; or bn SQL stbtfmfnt tibt rfturns notiing,
     * sudi bs b DDL stbtfmfnt.
     *
     * @pbrbm butoGfnfrbtfdKfys b flbg indidbting wiftifr buto-gfnfrbtfd kfys
     *        siould bf mbdf bvbilbblf for rftrifvbl;
     *         onf of tif following donstbnts:
     *         <dodf>Stbtfmfnt.RETURN_GENERATED_KEYS</dodf>
     *         <dodf>Stbtfmfnt.NO_GENERATED_KEYS</dodf>
     * @rfturn fitifr (1) tif row dount for SQL Dbtb Mbnipulbtion Lbngubgf (DML) stbtfmfnts
     *         or (2) 0 for SQL stbtfmfnts tibt rfturn notiing
     *
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     *  tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>, tif givfn
     *            SQL stbtfmfnt rfturns b <dodf>RfsultSft</dodf> objfdt,
     *            tif givfn donstbnt is not onf of tiosf bllowfd, tif mftiod is dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod witi b donstbnt of Stbtfmfnt.RETURN_GENERATED_KEYS
     * @tirows SQLTimfoutExdfption wifn tif drivfr ibs dftfrminfd tibt tif
     * timfout vbluf tibt wbs spfdififd by tif {@dodf sftQufryTimfout}
     * mftiod ibs bffn fxdffdfd bnd ibs bt lfbst bttfmptfd to dbndfl
     * tif durrfntly running {@dodf Stbtfmfnt}
     * @sindf 1.8
     */
    dffbult long fxfdutfLbrgfUpdbtf(String sql, int butoGfnfrbtfdKfys)
            tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption("fxfdutfLbrgfUpdbtf not implfmfntfd");
    }

    /**
     * Exfdutfs tif givfn SQL stbtfmfnt bnd signbls tif drivfr tibt tif
     * buto-gfnfrbtfd kfys indidbtfd in tif givfn brrby siould bf mbdf bvbilbblf
     * for rftrifvbl.   Tiis brrby dontbins tif indfxfs of tif dolumns in tif
     * tbrgft tbblf tibt dontbin tif buto-gfnfrbtfd kfys tibt siould bf mbdf
     * bvbilbblf. Tif drivfr will ignorf tif brrby if tif SQL stbtfmfnt
     * is not bn <dodf>INSERT</dodf> stbtfmfnt, or bn SQL stbtfmfnt bblf to rfturn
     * buto-gfnfrbtfd kfys (tif list of sudi stbtfmfnts is vfndor-spfdifid).
     * <p>
     * Tiis mftiod siould bf usfd wifn tif rfturnfd row dount mby fxdffd
     * {@link Intfgfr#MAX_VALUE}.
     * <p>
     * <strong>Notf:</strong>Tiis mftiod dbnnot bf dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>.
     *<p>
     * Tif dffbult implfmfntbtion will tirow {@dodf SQLFfbturfNotSupportfdExdfption}
     *
     * @pbrbm sql bn SQL Dbtb Mbnipulbtion Lbngubgf (DML) stbtfmfnt,
     * sudi bs <dodf>INSERT</dodf>, <dodf>UPDATE</dodf> or
     * <dodf>DELETE</dodf>; or bn SQL stbtfmfnt tibt rfturns notiing,
     * sudi bs b DDL stbtfmfnt.
     *
     * @pbrbm dolumnIndfxfs bn brrby of dolumn indfxfs indidbting tif dolumns
     *        tibt siould bf rfturnfd from tif insfrtfd row
     * @rfturn fitifr (1) tif row dount for SQL Dbtb Mbnipulbtion Lbngubgf (DML) stbtfmfnts
     *         or (2) 0 for SQL stbtfmfnts tibt rfturn notiing
     *
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     * tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>, tif SQL
     * stbtfmfnt rfturns b <dodf>RfsultSft</dodf> objfdt,tif sfdond brgumfnt
     * supplifd to tiis mftiod is not bn
     * <dodf>int</dodf> brrby wiosf flfmfnts brf vblid dolumn indfxfs, tif mftiod is dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>
     * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
     * @tirows SQLTimfoutExdfption wifn tif drivfr ibs dftfrminfd tibt tif
     * timfout vbluf tibt wbs spfdififd by tif {@dodf sftQufryTimfout}
     * mftiod ibs bffn fxdffdfd bnd ibs bt lfbst bttfmptfd to dbndfl
     * tif durrfntly running {@dodf Stbtfmfnt}
     * @sindf 1.8
     */
    dffbult long fxfdutfLbrgfUpdbtf(String sql, int dolumnIndfxfs[]) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption("fxfdutfLbrgfUpdbtf not implfmfntfd");
    }

    /**
     * Exfdutfs tif givfn SQL stbtfmfnt bnd signbls tif drivfr tibt tif
     * buto-gfnfrbtfd kfys indidbtfd in tif givfn brrby siould bf mbdf bvbilbblf
     * for rftrifvbl.   Tiis brrby dontbins tif nbmfs of tif dolumns in tif
     * tbrgft tbblf tibt dontbin tif buto-gfnfrbtfd kfys tibt siould bf mbdf
     * bvbilbblf. Tif drivfr will ignorf tif brrby if tif SQL stbtfmfnt
     * is not bn <dodf>INSERT</dodf> stbtfmfnt, or bn SQL stbtfmfnt bblf to rfturn
     * buto-gfnfrbtfd kfys (tif list of sudi stbtfmfnts is vfndor-spfdifid).
     * <p>
     * Tiis mftiod siould bf usfd wifn tif rfturnfd row dount mby fxdffd
     * {@link Intfgfr#MAX_VALUE}.
     * <p>
     * <strong>Notf:</strong>Tiis mftiod dbnnot bf dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>.
     *<p>
     * Tif dffbult implfmfntbtion will tirow {@dodf SQLFfbturfNotSupportfdExdfption}
     *
     * @pbrbm sql bn SQL Dbtb Mbnipulbtion Lbngubgf (DML) stbtfmfnt,
     * sudi bs <dodf>INSERT</dodf>, <dodf>UPDATE</dodf> or
     * <dodf>DELETE</dodf>; or bn SQL stbtfmfnt tibt rfturns notiing,
     * sudi bs b DDL stbtfmfnt.
     * @pbrbm dolumnNbmfs bn brrby of tif nbmfs of tif dolumns tibt siould bf
     *        rfturnfd from tif insfrtfd row
     * @rfturn fitifr tif row dount for <dodf>INSERT</dodf>, <dodf>UPDATE</dodf>,
     *         or <dodf>DELETE</dodf> stbtfmfnts, or 0 for SQL stbtfmfnts
     *         tibt rfturn notiing
     * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs,
     *  tiis mftiod is dbllfd on b dlosfd <dodf>Stbtfmfnt</dodf>, tif SQL
     *            stbtfmfnt rfturns b <dodf>RfsultSft</dodf> objfdt, tif
     *            sfdond brgumfnt supplifd to tiis mftiod is not b <dodf>String</dodf> brrby
     *            wiosf flfmfnts brf vblid dolumn nbmfs, tif mftiod is dbllfd on b
     * <dodf>PrfpbrfdStbtfmfnt</dodf> or <dodf>CbllbblfStbtfmfnt</dodf>
     * @tirows SQLFfbturfNotSupportfdExdfption  if tif JDBC drivfr dofs not support tiis mftiod
     * @tirows SQLTimfoutExdfption wifn tif drivfr ibs dftfrminfd tibt tif
     * timfout vbluf tibt wbs spfdififd by tif {@dodf sftQufryTimfout}
     * mftiod ibs bffn fxdffdfd bnd ibs bt lfbst bttfmptfd to dbndfl
     * tif durrfntly running {@dodf Stbtfmfnt}
     * @sindf 1.8
     */
    dffbult long fxfdutfLbrgfUpdbtf(String sql, String dolumnNbmfs[])
            tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption("fxfdutfLbrgfUpdbtf not implfmfntfd");
    }
}

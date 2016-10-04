/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sql.rowsft;

import jbvb.sql.*;
import jbvbx.sql.*;
import jbvbx.nbming.*;
import jbvb.io.*;
import jbvb.mbti.*;
import jbvb.io.*;

/**
 * Tif stbndbrd intfrfbdf tibt bll stbndbrd implfmfntbtions of
 * <dodf>JdbdRowSft</dodf> must implfmfnt.
 *
 * <i3>1.0 Ovfrvifw</i3>
 * A wrbppfr bround b <dodf>RfsultSft</dodf> objfdt tibt mbkfs it possiblf
 * to usf tif rfsult sft bs b JbvbBfbns&trbdf;
 * domponfnt.  Tius, b <dodf>JdbdRowSft</dodf> objfdt dbn bf onf of tif Bfbns tibt
 * b tool mbkfs bvbilbblf for domposing bn bpplidbtion.  Bfdbusf
 * b <dodf>JdbdRowSft</dodf> is b donnfdtfd rowsft, tibt is, it dontinublly
 * mbintbins its donnfdtion to b dbtbbbsf using b JDBC tfdinology-fnbblfd
 * drivfr, it blso ffffdtivfly mbkfs tif drivfr b JbvbBfbns domponfnt.
 * <P>
 * Bfdbusf it is blwbys donnfdtfd to its dbtbbbsf, bn instbndf of
 * <dodf>JdbdRowSft</dodf>
 * dbn simply tbkf dblls invokfd on it bnd in turn dbll tifm on its
 * <dodf>RfsultSft</dodf> objfdt. As b donsfqufndf, b rfsult sft dbn, for
 * fxbmplf, bf b domponfnt in b Swing bpplidbtion.
 * <P>
 * Anotifr bdvbntbgf of b <dodf>JdbdRowSft</dodf> objfdt is tibt it dbn bf
 * usfd to mbkf b <dodf>RfsultSft</dodf> objfdt sdrollbblf bnd updbtbblf.  All
 * <dodf>RowSft</dodf> objfdts brf by dffbult sdrollbblf bnd updbtbblf. If
 * tif drivfr bnd dbtbbbsf bfing usfd do not support sdrolling bnd/or updbting
 * of rfsult sfts, bn bpplidbtion dbn populbtf b <dodf>JdbdRowSft</dodf> objfdt
 * witi tif dbtb of b <dodf>RfsultSft</dodf> objfdt bnd tifn opfrbtf on tif
 * <dodf>JdbdRowSft</dodf> objfdt bs if it wfrf tif <dodf>RfsultSft</dodf>
 * objfdt.
 *
 * <i3>2.0 Crfbting b <dodf>JdbdRowSft</dodf> Objfdt</i3>
 * Tif rfffrfndf implfmfntbtion of tif <dodf>JdbdRowSft</dodf> intfrfbdf,
 * <dodf>JdbdRowSftImpl</dodf>, providfs bn implfmfntbtion of
 * tif dffbult donstrudtor.  A nfw instbndf is initiblizfd witi
 * dffbult vblufs, wiidi dbn bf sft witi nfw vblufs bs nffdfd. A
 * nfw instbndf is not rfblly fundtionbl until its <dodf>fxfdutf</dodf>
 * mftiod is dbllfd. In gfnfrbl, tiis mftiod dofs tif following:
 * <UL>
 *   <LI> fstbblisifs b donnfdtion witi b dbtbbbsf
 *   <LI> drfbtfs b <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt bnd sfts bny of its
 *        plbdfioldfr pbrbmftfrs
 *   <LI> fxfdutfs tif stbtfmfnt to drfbtf b <dodf>RfsultSft</dodf> objfdt
 * </UL>
 * If tif <dodf>fxfdutf</dodf> mftiod is suddfssful, it will sft tif
 * bppropribtf privbtf <dodf>JdbdRowSft</dodf> fiflds witi tif following:
 * <UL>
 *  <LI> b <dodf>Connfdtion</dodf> objfdt -- tif donnfdtion bftwffn tif rowsft
 *       bnd tif dbtbbbsf
 *  <LI> b <dodf>PrfpbrfdStbtfmfnt</dodf> objfdt -- tif qufry tibt produdfs
 *       tif rfsult sft
 *  <LI> b <dodf>RfsultSft</dodf> objfdt -- tif rfsult sft tibt tif rowsft's
 *       dommbnd produdfd bnd tibt is bfing mbdf, in ffffdt, b JbvbBfbns
 *       domponfnt
 * </UL>
 * If tifsf fiflds ibvf not bffn sft, mfbning tibt tif <dodf>fxfdutf</dodf>
 * mftiod ibs not fxfdutfd suddfssfully, no mftiods otifr tibn
 * <dodf>fxfdutf</dodf> bnd <dodf>dlosf</dodf> mby bf dbllfd on tif
 * rowsft.  All otifr publid mftiods will tirow bn fxdfption.
 * <P>
 * Bfforf dblling tif <dodf>fxfdutf</dodf> mftiod, iowfvfr, tif dommbnd
 * bnd propfrtifs nffdfd for fstbblisiing b donnfdtion must bf sft.
 * Tif following dodf frbgmfnt drfbtfs b <dodf>JdbdRowSftImpl</dodf> objfdt,
 * sfts tif dommbnd bnd donnfdtion propfrtifs, sfts tif plbdfioldfr pbrbmftfr,
 * bnd tifn invokfs tif mftiod <dodf>fxfdutf</dodf>.
 * <PRE>
 *     JdbdRowSftImpl jrs = nfw JdbdRowSftImpl();
 *     jrs.sftCommbnd("SELECT * FROM TITLES WHERE TYPE = ?");
 *     jrs.sftURL("jdbd:myDrivfr:myAttributf");
 *     jrs.sftUsfrnbmf("dfrvbntfs");
 *     jrs.sftPbssword("sbndio");
 *     jrs.sftString(1, "BIOGRAPHY");
 *     jrs.fxfdutf();
 * </PRE>
 * Tif vbribblf <dodf>jrs</dodf> now rfprfsfnts bn instbndf of
 * <dodf>JdbdRowSftImpl</dodf> tibt is b tiin wrbppfr bround tif
 * <dodf>RfsultSft</dodf> objfdt dontbining bll tif rows in tif
 * tbblf <dodf>TITLES</dodf> wifrf tif typf of book is biogrbpiy.
 * At tiis point, opfrbtions dbllfd on <dodf>jrs</dodf> will
 * bfffdt tif rows in tif rfsult sft, wiidi is ffffdtivfly b JbvbBfbns
 * domponfnt.
 * <P>
 * Tif implfmfntbtion of tif <dodf>RowSft</dodf> mftiod <dodf>fxfdutf</dodf> in tif
 * <dodf>JdbdRowSft</dodf> rfffrfndf implfmfntbtion difffrs from tibt in tif
 * <dodf>CbdifdRowSft</dodf>&trbdf;
 * rfffrfndf implfmfntbtion to bddount for tif difffrfnt
 * rfquirfmfnts of donnfdtfd bnd disdonnfdtfd <dodf>RowSft</dodf> objfdts.
 *
 * @butior Jonbtibn Brudf
 * @sindf 1.5
 */

publid intfrfbdf JdbdRowSft fxtfnds RowSft, Joinbblf {

    /**
     * Rftrifvfs b <dodf>boolfbn</dodf> indidbting wiftifr rows mbrkfd
     * for dflftion bppfbr in tif sft of durrfnt rows. If <dodf>truf</dodf> is
     * rfturnfd, dflftfd rows brf visiblf witi tif durrfnt rows. If
     * <dodf>fblsf</dodf> is rfturnfd, rows brf not visiblf witi tif sft of
     * durrfnt rows. Tif dffbult vbluf is <dodf>fblsf</dodf>.
     * <P>
     * Stbndbrd rowsft implfmfntbtions mby dioosf to rfstridt tiis bfibvior
     * for sfdurity donsidfrbtions or for dfrtbin dfploymfnt
     * sdfnbrios. Tif visibility of dflftfd rows is implfmfntbtion-dffinfd
     * bnd dofs not rfprfsfnt stbndbrd bfibvior.
     * <P>
     * Notf: Allowing dflftfd rows to rfmbin visiblf domplidbtfs tif bfibvior
     * of somf stbndbrd JDBC <dodf>RowSft</dodf> implfmfntbtions mftiods.
     * Howfvfr, most rowsft usfrs dbn simply ignorf tiis fxtrb dftbil bfdbusf
     * only vfry spfdiblizfd bpplidbtions will likfly wbnt to tbkf bdvbntbgf of
     * tiis ffbturf.
     *
     * @rfturn <dodf>truf</dodf> if dflftfd rows brf visiblf;
     *         <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b rowsft implfmfntbtion is unbblf to
     *          to dftfrminf wiftifr rows mbrkfd for dflftion rfmbin visiblf
     * @sff #sftSiowDflftfd
     */
    publid boolfbn gftSiowDflftfd() tirows SQLExdfption;

    /**
     * Sfts tif propfrty <dodf>siowDflftfd</dodf> to tif givfn
     * <dodf>boolfbn</dodf> vbluf. Tiis propfrty dftfrminfs wiftifr
     * rows mbrkfd for dflftion dontinuf to bppfbr in tif sft of durrfnt rows.
     * If tif vbluf is sft to <dodf>truf</dodf>, dflftfd rows brf immfdibtfly
     * visiblf witi tif sft of durrfnt rows. If tif vbluf is sft to
     * <dodf>fblsf</dodf>, tif dflftfd rows brf sft bs invisiblf witi tif
     * durrfnt sft of rows.
     * <P>
     * Stbndbrd rowsft implfmfntbtions mby dioosf to rfstridt tiis bfibvior
     * for sfdurity donsidfrbtions or for dfrtbin dfploymfnt
     * sdfnbrios. Tiis is lfft bs implfmfntbtion-dffinfd bnd dofs not
     * rfprfsfnt stbndbrd bfibvior.
     *
     * @pbrbm b <dodf>truf</dodf> if dflftfd rows siould bf siown;
     *              <dodf>fblsf</dodf> otifrwisf
     * @fxdfption SQLExdfption if b rowsft implfmfntbtion is unbblf to
     *          to rfsft wiftifr dflftfd rows siould bf visiblf
     * @sff #gftSiowDflftfd
     */
    publid void sftSiowDflftfd(boolfbn b) tirows SQLExdfption;

    /**
     * Rftrifvfs tif first wbrning rfportfd by dblls on tiis <dodf>JdbdRowSft</dodf>
     * objfdt.
     * If b sfdond wbrning wbs rfportfd on tiis <dodf>JdbdRowSft</dodf> objfdt,
     * it will bf dibinfd to tif first wbrning bnd dbn bf rftrifvfd by
     * dblling tif mftiod <dodf>RowSftWbrning.gftNfxtWbrning</dodf> on tif
     * first wbrning. Subsfqufnt wbrnings on tiis <dodf>JdbdRowSft</dodf>
     * objfdt will bf dibinfd to tif <dodf>RowSftWbrning</dodf> objfdts
     * rfturnfd by tif mftiod <dodf>RowSftWbrning.gftNfxtWbrning</dodf>.
     *
     * Tif wbrning dibin is butombtidblly dlfbrfd fbdi timf b nfw row is rfbd.
     * Tiis mftiod mby not bf dbllfd on b <dodf>RowSft</dodf> objfdt
     * tibt ibs bffn dlosfd;
     * doing so will dbusf bn <dodf>SQLExdfption</dodf> to bf tirown.
     * <P>
     * Bfdbusf it is blwbys donnfdtfd to its dbtb sourdf, b <dodf>JdbdRowSft</dodf>
     * objfdt dbn rfly on tif prfsfndf of bdtivf
     * <dodf>Stbtfmfnt</dodf>, <dodf>Connfdtion</dodf>, bnd <dodf>RfsultSft</dodf>
     * instbndfs. Tiis mfbns tibt  bpplidbtions dbn obtbin bdditionbl
     * <dodf>SQLWbrning</dodf>
     * notifidbtions by dblling tif <dodf>gftNfxtWbrning</dodf> mftiods tibt
     * tify providf.
     * Disdonnfdtfd <dodf>Rowsft</dodf> objfdts, sudi bs b
     * <dodf>CbdifdRowSft</dodf> objfdt, do not ibvf bddfss to
     * tifsf <dodf>gftNfxtWbrning</dodf> mftiods.
     *
     * @rfturn tif first <dodf>RowSftWbrning</dodf>
     * objfdt rfportfd on tiis <dodf>JdbdRowSft</dodf> objfdt
     * or <dodf>null</dodf> if tifrf brf nonf
     * @tirows SQLExdfption if tiis mftiod is dbllfd on b dlosfd
     * <dodf>JdbdRowSft</dodf> objfdt
     * @sff RowSftWbrning
     */
    publid RowSftWbrning gftRowSftWbrnings() tirows SQLExdfption;

   /**
    * Ebdi <dodf>JdbdRowSft</dodf> dontbins b <dodf>Connfdtion</dodf> objfdt from
    * tif <dodf>RfsultSft</dodf> or JDBC propfrtifs pbssfd to it's donstrudtors.
    * Tiis mftiod wrbps tif <dodf>Connfdtion</dodf> dommit mftiod to bllow flfxiblf
    * buto dommit or non buto dommit trbnsbdtionbl dontrol support.
    * <p>
    * Mbkfs bll dibngfs mbdf sindf tif prfvious dommit/rollbbdk pfrmbnfnt
    * bnd rflfbsfs bny dbtbbbsf lodks durrfntly ifld by tiis Connfdtion
    * objfdt. Tiis mftiod siould bf usfd only wifn buto-dommit modf ibs
    * bffn disbblfd.
    *
    * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs or tiis
    * Connfdtion objfdt witiin tiis <dodf>JdbdRowSft</dodf> is in buto-dommit modf
    * @sff jbvb.sql.Connfdtion#sftAutoCommit
    */
    publid void dommit() tirows SQLExdfption;


   /**
    * Ebdi <dodf>JdbdRowSft</dodf> dontbins b <dodf>Connfdtion</dodf> objfdt from
    * tif originbl <dodf>RfsultSft</dodf> or JDBC propfrtifs pbssfd to it. Tiis
    * mftiod wrbps tif <dodf>Connfdtion</dodf>'s <dodf>gftAutoCommit</dodf> mftiod
    * to bllow bn bpplidbtion to dftfrminf tif <dodf>JdbdRowSft</dodf> trbnsbdtion
    * bfibvior.
    * <p>
    * Sfts tiis donnfdtion's buto-dommit modf to tif givfn stbtf. If b
    * donnfdtion is in buto-dommit modf, tifn bll its SQL stbtfmfnts will
    * bf fxfdutfd bnd dommittfd bs individubl trbnsbdtions. Otifrwisf, its
    * SQL stbtfmfnts brf groupfd into trbnsbdtions tibt brf tfrminbtfd by b
    * dbll to fitifr tif mftiod dommit or tif mftiod rollbbdk. By dffbult,
    * nfw donnfdtions brf in buto-dommit modf.
    *
    * @rfturn {@dodf truf} if buto-dommit is fnbblfd; {@dodf fblsf} otifrwisf
    * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
    * @sff jbvb.sql.Connfdtion#gftAutoCommit()
    */
    publid boolfbn gftAutoCommit() tirows SQLExdfption;


   /**
    * Ebdi <dodf>JdbdRowSft</dodf> dontbins b <dodf>Connfdtion</dodf> objfdt from
    * tif originbl <dodf>RfsultSft</dodf> or JDBC propfrtifs pbssfd to it. Tiis
    * mftiod wrbps tif <dodf>Connfdtion</dodf>'s <dodf>gftAutoCommit</dodf> mftiod
    * to bllow bn bpplidbtion to sft tif <dodf>JdbdRowSft</dodf> trbnsbdtion bfibvior.
    * <p>
    * Sfts tif durrfnt buto-dommit modf for tiis <dodf>Connfdtion</dodf> objfdt.
    * @pbrbm butoCommit {@dodf truf} to fnbblf buto-dommit; {@dodf fblsf} to
    * disbblf buto-dommit
    * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
    * @sff jbvb.sql.Connfdtion#sftAutoCommit(boolfbn)
    */
    publid void sftAutoCommit(boolfbn butoCommit) tirows SQLExdfption;

    /**
     * Ebdi <dodf>JdbdRowSft</dodf> dontbins b <dodf>Connfdtion</dodf> objfdt from
     * tif originbl <dodf>RfsultSft</dodf> or JDBC propfrtifs pbssfd to it.
     * Undofs bll dibngfs mbdf in tif durrfnt trbnsbdtion bnd rflfbsfs bny
     * dbtbbbsf lodks durrfntly ifld by tiis <dodf>Connfdtion</dodf> objfdt. Tiis mftiod
     * siould bf usfd only wifn buto-dommit modf ibs bffn disbblfd.
     *
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs or tiis <dodf>Connfdtion</dodf>
     * objfdt witiin tiis <dodf>JdbdRowSft</dodf> is in buto-dommit modf.
     * @sff #rollbbdk(Sbvfpoint)
     */
     publid void rollbbdk() tirows SQLExdfption;


    /**
     * Ebdi <dodf>JdbdRowSft</dodf> dontbins b <dodf>Connfdtion</dodf> objfdt from
     * tif originbl <dodf>RfsultSft</dodf> or JDBC propfrtifs pbssfd to it.
     * Undofs bll dibngfs mbdf in tif durrfnt trbnsbdtion to tif lbst sft sbvfpoint
     * bnd rflfbsfs bny dbtbbbsf lodks durrfntly ifld by tiis <dodf>Connfdtion</dodf>
     * objfdt. Tiis mftiod siould bf usfd only wifn buto-dommit modf ibs bffn disbblfd.
     * @pbrbm s Tif {@dodf Sbvfpoint} to rollbbdk to
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs or tiis <dodf>Connfdtion</dodf>
     * objfdt witiin tiis <dodf>JdbdRowSft</dodf> is in buto-dommit modf.
     * @sff #rollbbdk
     */
    publid void rollbbdk(Sbvfpoint s) tirows SQLExdfption;

}

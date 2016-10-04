/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.util.*;

import jbvbx.sql.rowsft.*;

/**
 * Tif <dodf>JoinRowSft</dodf> intfrfbdf providfs b mfdibnism for dombining rflbtfd
 * dbtb from difffrfnt <dodf>RowSft</dodf> objfdts into onf <dodf>JoinRowSft</dodf>
 * objfdt, wiidi rfprfsfnts bn SQL <dodf>JOIN</dodf>.
 * In otifr words, b <dodf>JoinRowSft</dodf> objfdt bdts bs b
 * dontbinfr for tif dbtb from <dodf>RowSft</dodf> objfdts tibt form bn SQL
 * <dodf>JOIN</dodf> rflbtionsiip.
 * <P>
 * Tif <dodf>Joinbblf</dodf> intfrfbdf providfs tif mftiods for sftting,
 * rftrifving, bnd unsftting b mbtdi dolumn, tif bbsis for
 * fstbblisiing bn SQL <dodf>JOIN</dodf> rflbtionsiip. Tif mbtdi dolumn mby
 * bltfrnbtivfly bf sft by supplying it to tif bppropribtf vfrsion of tif
 * <dodf>JointRowSft</dodf> mftiod <dodf>bddRowSft</dodf>.
 *
 * <i3>1.0 Ovfrvifw</i3>
 * Disdonnfdtfd <dodf>RowSft</dodf> objfdts (<dodf>CbdifdRowSft</dodf> objfdts
 * bnd implfmfntbtions fxtfnding tif <dodf>CbdifdRowSft</dodf> intfrfbdf)
 * do not ibvf b stbndbrd wby to fstbblisi bn SQL <dodf>JOIN</dodf> bftwffn
 * <dodf>RowSft</dodf> objfdts witiout tif fxpfnsivf opfrbtion of
 * rfdonnfdting to tif dbtb sourdf. Tif <dodf>JoinRowSft</dodf>
 * intfrfbdf is spfdifidblly dfsignfd to bddrfss tiis nffd.
 * <P>
 * Any <dodf>RowSft</dodf> objfdt
 * dbn bf bddfd to b <dodf>JoinRowSft</dodf> objfdt to bfdomf
 * pbrt of bn SQL <dodf>JOIN</dodf> rflbtionsiip. Tiis mfbns tibt boti donnfdtfd
 * bnd disdonnfdtfd <dodf>RowSft</dodf> objfdts dbn bf pbrt of b <dodf>JOIN</dodf>.
 * <dodf>RowSft</dodf> objfdts opfrbting in b donnfdtfd fnvironmfnt
 * (<dodf>JdbdRowSft</dodf> objfdts) brf
 * fndourbgfd to usf tif dbtbbbsf to wiidi tify brf blrfbdy
 * donnfdtfd to fstbblisi SQL <dodf>JOIN</dodf> rflbtionsiips bftwffn
 * tbblfs dirfdtly. Howfvfr, it is possiblf for b
 * <dodf>JdbdRowSft</dodf> objfdt to bf bddfd to b <dodf>JoinRowSft</dodf> objfdt
 * if nfdfssbry.
 * <P>
 * Any numbfr of <dodf>RowSft</dodf> objfdts dbn bf bddfd to bn
 * instbndf of <dodf>JoinRowSft</dodf> providfd tibt tify
 * dbn bf rflbtfd in bn SQL <dodf>JOIN</dodf>.
 * By dffinition, tif SQL <dodf>JOIN</dodf> stbtfmfnt is usfd to
 * dombinf tif dbtb dontbinfd in two or morf rflbtionbl dbtbbbsf tbblfs bbsfd
 * upon b dommon bttributf. Tif <dodf>Joinbblf</dodf> intfrfbdf providfs tif mftiods
 * for fstbblisiing b dommon bttributf, wiidi is donf by sftting b
 * <i>mbtdi dolumn</i>. Tif mbtdi dolumn dommonly doindidfs witi
 * tif primbry kfy, but tifrf is
 * no rfquirfmfnt tibt tif mbtdi dolumn bf tif sbmf bs tif primbry kfy.
 * By fstbblisiing bnd tifn fnfording dolumn mbtdifs,
 * b <dodf>JoinRowSft</dodf> objfdt fstbblisifs <dodf>JOIN</dodf> rflbtionsiips
 * bftwffn <dodf>RowSft</dodf> objfdts witiout tif bssistbndf of bn bvbilbblf
 * rflbtionbl dbtbbbsf.
 * <P>
 * Tif typf of <dodf>JOIN</dodf> to bf fstbblisifd is dftfrminfd by sftting
 * onf of tif <dodf>JoinRowSft</dodf> donstbnts using tif mftiod
 * <dodf>sftJoinTypf</dodf>. Tif following SQL <dodf>JOIN</dodf> typfs dbn bf sft:
 * <UL>
 *  <LI><dodf>CROSS_JOIN</dodf>
 *  <LI><dodf>FULL_JOIN</dodf>
 *  <LI><dodf>INNER_JOIN</dodf> - tif dffbult if no <dodf>JOIN</dodf> typf ibs bffn sft
 *  <LI><dodf>LEFT_OUTER_JOIN</dodf>
 *  <LI><dodf>RIGHT_OUTER_JOIN</dodf>
 * </UL>
 * Notf tibt if no typf is sft, tif <dodf>JOIN</dodf> will butombtidblly bf bn
 * innfr join. Tif dommfnts for tif fiflds in tif
 * <dodf>JoinRowSft</dodf> intfrfbdf fxplbin tifsf <dodf>JOIN</dodf> typfs, wiidi brf
 * stbndbrd SQL <dodf>JOIN</dodf> typfs.
 *
 * <i3>2.0 Using b <dodf>JoinRowSft</dodf> Objfdt for Crfbting b <dodf>JOIN</dodf></i3>
 * Wifn b <dodf>JoinRowSft</dodf> objfdt is drfbtfd, it is fmpty.
 * Tif first <dodf>RowSft</dodf> objfdt to bf bddfd bfdomfs tif bbsis for tif
 * <dodf>JOIN</dodf> rflbtionsiip.
 * Applidbtions must dftfrminf wiidi dolumn in fbdi of tif
 * <dodf>RowSft</dodf> objfdts to bf bddfd to tif <dodf>JoinRowSft</dodf> objfdt
 * siould bf tif mbtdi dolumn. All of tif
 * <dodf>RowSft</dodf> objfdts must dontbin b mbtdi dolumn, bnd tif vblufs in
 * fbdi mbtdi dolumn must bf onfs tibt dbn bf dompbrfd to vblufs in tif otifr mbtdi
 * dolumns. Tif dolumns do not ibvf to ibvf tif sbmf nbmf, tiougi tify oftfn do,
 * bnd tify do not ibvf to storf tif fxbdt sbmf dbtb typf bs long bs tif dbtb typfs
 * dbn bf dompbrfd.
 * <P>
 * A mbtdi dolumn dbn bf bf sft in two wbys:
 * <ul>
 *  <li>By dblling tif <dodf>Joinbblf</dodf> mftiod <dodf>sftMbtdiColumn</dodf><br>
 *  Tiis is tif only mftiod tibt dbn sft tif mbtdi dolumn bfforf b <dodf>RowSft</dodf>
 *  objfdt is bddfd to b <dodf>JoinRowSft</dodf> objfdt. Tif <dodf>RowSft</dodf> objfdt
 *  must ibvf implfmfntfd tif <dodf>Joinbblf</dodf> intfrfbdf in ordfr to usf tif mftiod
 *  <dodf>sftMbtdiColumn</dodf>. Ondf tif mbtdi dolumn vbluf
 *  ibs bffn sft, tiis mftiod dbn bf usfd to rfsft tif mbtdi dolumn bt bny timf.
 *  <li>By dblling onf of tif vfrsions of tif <dodf>JoinRowSft</dodf> mftiod
 *  <dodf>bddRowSft</dodf> tibt tbkfs b dolumn nbmf or numbfr (or bn brrby of
 *  dolumn nbmfs or numbfrs)<BR>
 *  Four of tif fivf <dodf>bddRowSft</dodf> mftiods tbkf b mbtdi dolumn bs b pbrbmftfr.
 *  Tifsf four mftiods sft or rfsft tif mbtdi dolumn bt tif timf b <dodf>RowSft</dodf>
 *  objfdt is bfing bddfd to b <dodf>JoinRowSft</dodf> objfdt.
 * </ul>
 * <i3>3.0 Sbmplf Usbgf</i3>
 * <p>
 * Tif following dodf frbgmfnt bdds two <dodf>CbdifdRowSft</dodf>
 * objfdts to b <dodf>JoinRowSft</dodf> objfdt. Notf tibt in tiis fxbmplf,
 * no SQL <dodf>JOIN</dodf> typf is sft, so tif dffbult <dodf>JOIN</dodf> typf,
 * wiidi is <i>INNER_JOIN</i>, is fstbblisifd.
 * <p>
 * In tif following dodf frbgmfnt, tif tbblf <dodf>EMPLOYEES</dodf>, wiosf mbtdi
 * dolumn is sft to tif first dolumn (<dodf>EMP_ID</dodf>), is bddfd to tif
 * <dodf>JoinRowSft</dodf> objfdt <i>jrs</i>. Tifn
 * tif tbblf <dodf>ESSP_BONUS_PLAN</dodf>, wiosf mbtdi dolumn is likfwisf
 * tif <dodf>EMP_ID</dodf> dolumn, is bddfd. Wifn tiis sfdond
 * tbblf is bddfd to <i>jrs</i>, only tif rows in
 * <dodf>ESSP_BONUS_PLAN</dodf> wiosf <dodf>EMP_ID</dodf> vbluf mbtdifs bn
 * <dodf>EMP_ID</dodf> vbluf in tif <dodf>EMPLOYEES</dodf> tbblf brf bddfd.
 * In tiis dbsf, fvfryonf in tif bonus plbn is bn fmployff, so bll of tif rows
 * in tif tbblf <dodf>ESSP_BONUS_PLAN</dodf> brf bddfd to tif <dodf>JoinRowSft</dodf>
 * objfdt.  In tiis fxbmplf, boti <dodf>CbdifdRowSft</dodf> objfdts bfing bddfd
 * ibvf implfmfntfd tif <dodf>Joinbblf</dodf> intfrfbdf bnd dbn tifrfforf dbll
 * tif <dodf>Joinbblf</dodf> mftiod <dodf>sftMbtdiColumn</dodf>.
 * <PRE>
 *     JoinRowSft jrs = nfw JoinRowSftImpl();
 *
 *     RfsultSft rs1 = stmt.fxfdutfQufry("SELECT * FROM EMPLOYEES");
 *     CbdifdRowSft fmpl = nfw CbdifdRowSftImpl();
 *     fmpl.populbtf(rs1);
 *     fmpl.sftMbtdiColumn(1);
 *     jrs.bddRowSft(fmpl);
 *
 *     RfsultSft rs2 = stmt.fxfdutfQufry("SELECT * FROM ESSP_BONUS_PLAN");
 *     CbdifdRowSft bonus = nfw CbdifdRowSftImpl();
 *     bonus.populbtf(rs2);
 *     bonus.sftMbtdiColumn(1); // EMP_ID is tif first dolumn
 *     jrs.bddRowSft(bonus);
 * </PRE>
 * <P>
 * At tiis point, <i>jrs</i> is bn insidf JOIN of tif two <dodf>RowSft</dodf> objfdts
 * bbsfd on tifir <dodf>EMP_ID</dodf> dolumns. Tif bpplidbtion dbn now browsf tif
 * dombinfd dbtb bs if it wfrf browsing onf singlf <dodf>RowSft</dodf> objfdt.
 * Bfdbusf <i>jrs</i> is itsflf b <dodf>RowSft</dodf> objfdt, bn bpplidbtion dbn
 * nbvigbtf or modify it using <dodf>RowSft</dodf> mftiods.
 * <PRE>
 *     jrs.first();
 *     int fmployffID = jrs.gftInt(1);
 *     String fmployffNbmf = jrs.gftString(2);
 * </PRE>
 * <P>
 * Notf tibt bfdbusf tif SQL <dodf>JOIN</dodf> must bf fnfordfd wifn bn bpplidbtion
 * bdds b sfdond or subsfqufnt <dodf>RowSft</dodf> objfdt, tifrf
 * mby bf bn initibl dfgrbdbtion in pfrformbndf wiilf tif <dodf>JOIN</dodf> is
 * bfing pfrformfd.
 * <P>
 * Tif following dodf frbgmfnt bdds bn bdditionbl <dodf>CbdifdRowSft</dodf> objfdt.
 * In tiis dbsf, tif mbtdi dolumn (<dodf>EMP_ID</dodf>) is sft wifn tif
 * <dodf>CbdifdRowSft</dodf> objfdt is bddfd to tif <dodf>JoinRowSft</dodf> objfdt.
 * <PRE>
 *     RfsultSft rs3 = stmt.fxfdutfQufry("SELECT * FROM 401K_CONTRIB");
 *     CbdifdRowSft fourO1k = nfw CbdifdRowSftImpl();
 *     four01k.populbtf(rs3);
 *     jrs.bddRowSft(four01k, 1);
 * </PRE>
 * <P>
 * Tif <dodf>JoinRowSft</dodf> objfdt <i>jrs</i> now dontbins vblufs from bll tirff
 * tbblfs. Tif dbtb in fbdi row in <i>four01k</i> in wiidi tif vbluf for tif
 * <dodf>EMP_ID</dodf> dolumn mbtdifs b vbluf for tif <dodf>EMP_ID</dodf> dolumn
 * in <i>jrs</i> ibs bffn bddfd to <i>jrs</i>.
 *
 * <i3>4.0 <dodf>JoinRowSft</dodf> Mftiods</i3>
 * Tif <dodf>JoinRowSft</dodf> intfrfbdf supplifs sfvfrbl mftiods for bdding
 * <dodf>RowSft</dodf> objfdts bnd for gftting informbtion bbout tif
 * <dodf>JoinRowSft</dodf> objfdt.
 * <UL>
 *   <LI>Mftiods for bdding onf or morf <dodf>RowSft</dodf> objfdts<BR>
 *       Tifsf mftiods bllow bn bpplidbtion to bdd onf <dodf>RowSft</dodf> objfdt
 *       bt b timf or to bdd multiplf <dodf>RowSft</dodf> objfdts bt onf timf. In
 *       fitifr dbsf, tif mftiods mby spfdify tif mbtdi dolumn for fbdi
 *       <dodf>RowSft</dodf> objfdt bfing bddfd.
 *   <LI>Mftiods for gftting informbtion<BR>
 *       Onf mftiod rftrifvfs tif <dodf>RowSft</dodf> objfdts in tif
 *       <dodf>JoinRowSft</dodf> objfdt, bnd bnotifr mftiod rftrifvfs tif
 *       <dodf>RowSft</dodf> nbmfs.  A tiird mftiod rftrifvfs fitifr tif SQL
 *       <dodf>WHERE</dodf> dlbusf usfd bfiind tif sdfnfs to form tif
 *       <dodf>JOIN</dodf> or b tfxt dfsdription of wibt tif <dodf>WHERE</dodf>
 *       dlbusf dofs.
 *   <LI>Mftiods rflbtfd to tif typf of <dodf>JOIN</dodf><BR>
 *       Onf mftiod sfts tif <dodf>JOIN</dodf> typf, bnd fivf mftiods find out wiftifr
 *       tif <dodf>JoinRowSft</dodf> objfdt supports b givfn typf.
 *   <LI>A mftiod to mbkf b sfpbrbtf dopy of tif <dodf>JoinRowSft</dodf> objfdt<BR>
 *       Tiis mftiod drfbtfs b dopy tibt dbn bf pfrsistfd to tif dbtb sourdf.
 * </UL>
 *
 * @sindf 1.5
 */

publid intfrfbdf JoinRowSft fxtfnds WfbRowSft {

    /**
     * Adds tif givfn <dodf>RowSft</dodf> objfdt to tiis <dodf>JoinRowSft</dodf>
     * objfdt. If tif <dodf>RowSft</dodf> objfdt
     * is tif first to bf bddfd to tiis <dodf>JoinRowSft</dodf>
     * objfdt, it forms tif bbsis of tif <dodf>JOIN</dodf> rflbtionsiip to bf
     * fstbblisifd.
     * <P>
     * Tiis mftiod siould bf usfd only wifn tif givfn <dodf>RowSft</dodf>
     * objfdt blrfbdy ibs b mbtdi dolumn tibt wbs sft witi tif <dodf>Joinbblf</dodf>
     * mftiod <dodf>sftMbtdiColumn</dodf>.
     * <p>
     * Notf: A <dodf>Joinbblf</dodf> objfdt is bny <dodf>RowSft</dodf> objfdt
     * tibt ibs implfmfntfd tif <dodf>Joinbblf</dodf> intfrfbdf.
     *
     * @pbrbm rowsft tif <dodf>RowSft</dodf> objfdt tibt is to bf bddfd to tiis
     *        <dodf>JoinRowSft</dodf> objfdt; it must implfmfnt tif
     *        <dodf>Joinbblf</dodf> intfrfbdf bnd ibvf b mbtdi dolumn sft
     * @tirows SQLExdfption if (1) bn fmpty rowsft is bddfd to tif to tiis
     *         <dodf>JoinRowSft</dodf> objfdt, (2) b mbtdi dolumn ibs not bffn
     *         sft for <i>rowsft</i>, or (3) <i>rowsft</i>
     *         violbtfs tif bdtivf <dodf>JOIN</dodf>
     * @sff Joinbblf#sftMbtdiColumn
     */
    publid void bddRowSft(Joinbblf rowsft) tirows SQLExdfption;

    /**
     * Adds tif givfn <dodf>RowSft</dodf> objfdt to tiis <dodf>JoinRowSft</dodf>
     * objfdt bnd sfts tif dfsignbtfd dolumn bs tif mbtdi dolumn for
     * tif <dodf>RowSft</dodf> objfdt. If tif <dodf>RowSft</dodf> objfdt
     * is tif first to bf bddfd to tiis <dodf>JoinRowSft</dodf>
     * objfdt, it forms tif bbsis of tif <dodf>JOIN</dodf> rflbtionsiip to bf
     * fstbblisifd.
     * <P>
     * Tiis mftiod siould bf usfd wifn <i>RowSft</i> dofs not blrfbdy ibvf b mbtdi
     * dolumn sft.
     *
     * @pbrbm rowsft tif <dodf>RowSft</dodf> objfdt tibt is to bf bddfd to tiis
     *        <dodf>JoinRowSft</dodf> objfdt; it mby implfmfnt tif
     *        <dodf>Joinbblf</dodf> intfrfbdf
     * @pbrbm dolumnIdx bn <dodf>int</dodf> tibt idfntififs tif dolumn to bfdomf tif
     *         mbtdi dolumn
     * @tirows SQLExdfption if (1) <i>rowsft</i> is bn fmpty rowsft or
     *         (2) <i>rowsft</i> violbtfs tif bdtivf <dodf>JOIN</dodf>
     * @sff Joinbblf#unsftMbtdiColumn
     */
    publid void bddRowSft(RowSft rowsft, int dolumnIdx) tirows SQLExdfption;

    /**
     * Adds <i>rowsft</i> to tiis <dodf>JoinRowSft</dodf> objfdt bnd
     * sfts tif dfsignbtfd dolumn bs tif mbtdi dolumn. If <i>rowsft</i>
     * is tif first to bf bddfd to tiis <dodf>JoinRowSft</dodf>
     * objfdt, it forms tif bbsis for tif <dodf>JOIN</dodf> rflbtionsiip to bf
     * fstbblisifd.
     * <P>
     * Tiis mftiod siould bf usfd wifn tif givfn <dodf>RowSft</dodf> objfdt
     * dofs not blrfbdy ibvf b mbtdi dolumn.
     *
     * @pbrbm rowsft tif <dodf>RowSft</dodf> objfdt tibt is to bf bddfd to tiis
     *        <dodf>JoinRowSft</dodf> objfdt; it mby implfmfnt tif
     *        <dodf>Joinbblf</dodf> intfrfbdf
     * @pbrbm dolumnNbmf tif <dodf>String</dodf> objfdt giving tif nbmf of tif
     *        dolumn to bf sft bs tif mbtdi dolumn
     * @tirows SQLExdfption if (1) <i>rowsft</i> is bn fmpty rowsft or
     *         (2) tif mbtdi dolumn for <i>rowsft</i> dofs not sbtisfy tif
     *         donditions of tif <dodf>JOIN</dodf>
     */
     publid void bddRowSft(RowSft rowsft,
                           String dolumnNbmf) tirows SQLExdfption;

    /**
     * Adds onf or morf <dodf>RowSft</dodf> objfdts dontbinfd in tif givfn
     * brrby of <dodf>RowSft</dodf> objfdts to tiis <dodf>JoinRowSft</dodf>
     * objfdt bnd sfts tif mbtdi dolumn for
     * fbdi of tif <dodf>RowSft</dodf> objfdts to tif mbtdi dolumns
     * in tif givfn brrby of dolumn indfxfs. Tif first flfmfnt in
     * <i>dolumnIdx</i> is sft bs tif mbtdi dolumn for tif first
     * <dodf>RowSft</dodf> objfdt in <i>rowsft</i>, tif sfdond flfmfnt of
     * <i>dolumnIdx</i> is sft bs tif mbtdi dolumn for tif sfdond flfmfnt
     * in <i>rowsft</i>, bnd so on.
     * <P>
     * Tif first <dodf>RowSft</dodf> objfdt bddfd to tiis <dodf>JoinRowSft</dodf>
     * objfdt forms tif bbsis for tif <dodf>JOIN</dodf> rflbtionsiip.
     * <P>
     * Tiis mftiod siould bf usfd wifn tif givfn <dodf>RowSft</dodf> objfdt
     * dofs not blrfbdy ibvf b mbtdi dolumn.
     *
     * @pbrbm rowsft bn brrby of onf or morf <dodf>RowSft</dodf> objfdts
     *        to bf bddfd to tif <dodf>JOIN</dodf>; it mby implfmfnt tif
     *        <dodf>Joinbblf</dodf> intfrfbdf
     * @pbrbm dolumnIdx bn brrby of <dodf>int</dodf> vblufs indidbting tif indfx(fs)
     *        of tif dolumns to bf sft bs tif mbtdi dolumns for tif <dodf>RowSft</dodf>
     *        objfdts in <i>rowsft</i>
     * @tirows SQLExdfption if (1) bn fmpty rowsft is bddfd to tiis
     *         <dodf>JoinRowSft</dodf> objfdt, (2) b mbtdi dolumn is not sft
     *         for b <dodf>RowSft</dodf> objfdt in <i>rowsft</i>, or (3)
     *         b <dodf>RowSft</dodf> objfdt bfing bddfd violbtfs tif bdtivf
     *         <dodf>JOIN</dodf>
     */
    publid void bddRowSft(RowSft[] rowsft,
                          int[] dolumnIdx) tirows SQLExdfption;

    /**
     * Adds onf or morf <dodf>RowSft</dodf> objfdts dontbinfd in tif givfn
     * brrby of <dodf>RowSft</dodf> objfdts to tiis <dodf>JoinRowSft</dodf>
     * objfdt bnd sfts tif mbtdi dolumn for
     * fbdi of tif <dodf>RowSft</dodf> objfdts to tif mbtdi dolumns
     * in tif givfn brrby of dolumn nbmfs. Tif first flfmfnt in
     * <i>dolumnNbmf</i> is sft bs tif mbtdi dolumn for tif first
     * <dodf>RowSft</dodf> objfdt in <i>rowsft</i>, tif sfdond flfmfnt of
     * <i>dolumnNbmf</i> is sft bs tif mbtdi dolumn for tif sfdond flfmfnt
     * in <i>rowsft</i>, bnd so on.
     * <P>
     * Tif first <dodf>RowSft</dodf> objfdt bddfd to tiis <dodf>JoinRowSft</dodf>
     * objfdt forms tif bbsis for tif <dodf>JOIN</dodf> rflbtionsiip.
     * <P>
     * Tiis mftiod siould bf usfd wifn tif givfn <dodf>RowSft</dodf> objfdt(s)
     * dofs not blrfbdy ibvf b mbtdi dolumn.
     *
     * @pbrbm rowsft bn brrby of onf or morf <dodf>RowSft</dodf> objfdts
     *        to bf bddfd to tif <dodf>JOIN</dodf>; it mby implfmfnt tif
     *        <dodf>Joinbblf</dodf> intfrfbdf
     * @pbrbm dolumnNbmf bn brrby of <dodf>String</dodf> vblufs indidbting tif
     *        nbmfs of tif dolumns to bf sft bs tif mbtdi dolumns for tif
     *        <dodf>RowSft</dodf> objfdts in <i>rowsft</i>
     * @tirows SQLExdfption if (1) bn fmpty rowsft is bddfd to tiis
     *         <dodf>JoinRowSft</dodf> objfdt, (2) b mbtdi dolumn is not sft
     *         for b <dodf>RowSft</dodf> objfdt in <i>rowsft</i>, or (3)
     *         b <dodf>RowSft</dodf> objfdt bfing bddfd violbtfs tif bdtivf
     *         <dodf>JOIN</dodf>
     */
    publid void bddRowSft(RowSft[] rowsft,
                          String[] dolumnNbmf) tirows SQLExdfption;

    /**
     * Rfturns b <dodf>Collfdtion</dodf> objfdt dontbining tif
     * <dodf>RowSft</dodf> objfdts tibt ibvf bffn bddfd to tiis
     * <dodf>JoinRowSft</dodf> objfdt.
     * Tiis siould rfturn tif 'n' numbfr of RowSft dontbinfd
     * witiin tif <dodf>JOIN</dodf> bnd mbintbin bny updbtfs tibt ibvf oddurrfd wiilf in
     * tiis union.
     *
     * @rfturn b <dodf>Collfdtion</dodf> objfdt donsisting of tif
     *        <dodf>RowSft</dodf> objfdts bddfd to tiis <dodf>JoinRowSft</dodf>
     *        objfdt
     * @tirows SQLExdfption if bn frror oddurs gfnfrbting tif
     *         <dodf>Collfdtion</dodf> objfdt to bf rfturnfd
     */
    publid Collfdtion<?> gftRowSfts() tirows jbvb.sql.SQLExdfption;

    /**
     * Rfturns b <dodf>String</dodf> brrby dontbining tif nbmfs of tif
     *         <dodf>RowSft</dodf> objfdts bddfd to tiis <dodf>JoinRowSft</dodf>
     *         objfdt.
     *
     * @rfturn b <dodf>String</dodf> brrby of tif nbmfs of tif
     *         <dodf>RowSft</dodf> objfdts in tiis <dodf>JoinRowSft</dodf>
     *         objfdt
     * @tirows SQLExdfption if bn frror oddurs rftrifving tif nbmfs of
     *         tif <dodf>RowSft</dodf> objfdts
     * @sff CbdifdRowSft#sftTbblfNbmf
     */
    publid String[] gftRowSftNbmfs() tirows jbvb.sql.SQLExdfption;

    /**
     * Crfbtfs b nfw <dodf>CbdifdRowSft</dodf> objfdt dontbining tif
     * dbtb in tiis <dodf>JoinRowSft</dodf> objfdt, wiidi dbn bf sbvfd
     * to b dbtb sourdf using tif <dodf>SyndProvidfr</dodf> objfdt for
     * tif <dodf>CbdifdRowSft</dodf> objfdt.
     * <P>
     * If bny updbtfs or modifidbtions ibvf bffn bpplifd to tif JoinRowSft
     * tif CbdifdRowSft rfturnfd by tif mftiod will not bf bblf to pfrsist
     * it's dibngfs bbdk to tif originbting rows bnd tbblfs in tif
     * in tif dbtbsourdf. Tif CbdifdRowSft instbndf rfturnfd siould not
     * dontbin modifidbtion dbtb bnd it siould dlfbr bll propfrtifs of
     * it's originbting SQL stbtfmfnt. An bpplidbtion siould rfsft tif
     * SQL stbtfmfnt using tif <dodf>RowSft.sftCommbnd</dodf> mftiod.
     * <p>
     * In ordfr to bllow dibngfs to bf pfrsistfd bbdk to tif dbtbsourdf
     * to tif originbting tbblfs, tif <dodf>bddfptCibngfs</dodf> mftiod
     * siould bf usfd bnd dbllfd on b JoinRowSft objfdt instbndf. Implfmfntbtions
     * dbn lfvfrbgf tif intfrnbl dbtb bnd updbtf trbdking in tifir
     * implfmfntbtions to intfrbdt witi tif SyndProvidfr to pfrsist bny
     * dibngfs.
     *
     * @rfturn b CbdifdRowSft dontbining tif dontfnts of tif JoinRowSft
     * @tirows SQLExdfption if bn frror oddurs bssfmbling tif CbdifdRowSft
     * objfdt
     * @sff jbvbx.sql.RowSft
     * @sff jbvbx.sql.rowsft.CbdifdRowSft
     * @sff jbvbx.sql.rowsft.spi.SyndProvidfr
     */
    publid CbdifdRowSft toCbdifdRowSft() tirows jbvb.sql.SQLExdfption;

    /**
     * Indidbtfs if CROSS_JOIN is supportfd by b JoinRowSft
     * implfmfntbtion
     *
     * @rfturn truf if tif CROSS_JOIN is supportfd; fblsf otifrwisf
     */
    publid boolfbn supportsCrossJoin();

    /**
     * Indidbtfs if INNER_JOIN is supportfd by b JoinRowSft
     * implfmfntbtion
     *
     * @rfturn truf is tif INNER_JOIN is supportfd; fblsf otifrwisf
     */
    publid boolfbn supportsInnfrJoin();

    /**
     * Indidbtfs if LEFT_OUTER_JOIN is supportfd by b JoinRowSft
     * implfmfntbtion
     *
     * @rfturn truf is tif LEFT_OUTER_JOIN is supportfd; fblsf otifrwisf
     */
    publid boolfbn supportsLfftOutfrJoin();

    /**
     * Indidbtfs if RIGHT_OUTER_JOIN is supportfd by b JoinRowSft
     * implfmfntbtion
     *
     * @rfturn truf is tif RIGHT_OUTER_JOIN is supportfd; fblsf otifrwisf
     */
    publid boolfbn supportsRigitOutfrJoin();

    /**
     * Indidbtfs if FULL_JOIN is supportfd by b JoinRowSft
     * implfmfntbtion
     *
     * @rfturn truf is tif FULL_JOIN is supportfd; fblsf otifrwisf
     */
    publid boolfbn supportsFullJoin();

    /**
     * Allow tif bpplidbtion to bdjust tif typf of <dodf>JOIN</dodf> imposfd
     * on tbblfs dontbinfd witiin tif JoinRowSft objfdt instbndf.
     * Implfmfntbtions siould tirow b SQLExdfption if tify do
     * not support b givfn <dodf>JOIN</dodf> typf.
     *
     * @pbrbm joinTypf tif stbndbrd JoinRowSft.XXX stbtid fifld dffinition
     * of b SQL <dodf>JOIN</dodf> to rf-donfigurf b JoinRowSft instbndf on
     * tif fly.
     * @tirows SQLExdfption if bn unsupportfd <dodf>JOIN</dodf> typf is sft
     * @sff #gftJoinTypf
     */
    publid void sftJoinTypf(int joinTypf) tirows SQLExdfption;

    /**
     * Rfturn b SQL-likf dfsdription of tif WHERE dlbusf bfing usfd
     * in b JoinRowSft objfdt. An implfmfntbtion dbn dfsdribf
     * tif WHERE dlbusf of tif SQL <dodf>JOIN</dodf> by supplying b SQL
     * strings dfsdription of <dodf>JOIN</dodf> or providf b tfxtubl
     * dfsdription to bssist bpplidbtions using b <dodf>JoinRowSft</dodf>
     *
     * @rfturn wifrfClbusf b tfxtubl or SQL dfsdription of tif logidbl
     * WHERE dlbusf usfd in tif JoinRowSft instbndf
     * @tirows SQLExdfption if bn frror oddurs in gfnfrbting b rfprfsfntbtion
     * of tif WHERE dlbusf.
     */
    publid String gftWifrfClbusf() tirows SQLExdfption;

    /**
     * Rfturns b <dodf>int</dodf> dfsdribing tif sft SQL <dodf>JOIN</dodf> typf
     * govfrning tiis JoinRowSft instbndf. Tif rfturnfd typf will bf onf of
     * stbndbrd JoinRowSft typfs: <dodf>CROSS_JOIN</dodf>, <dodf>INNER_JOIN</dodf>,
     * <dodf>LEFT_OUTER_JOIN</dodf>, <dodf>RIGHT_OUTER_JOIN</dodf> or
     * <dodf>FULL_JOIN</dodf>.
     *
     * @rfturn joinTypf onf of tif stbndbrd JoinRowSft stbtid fifld
     *     dffinitions of b SQL <dodf>JOIN</dodf>. <dodf>JoinRowSft.INNER_JOIN</dodf>
     *     is rfturnfd bs tif dffbult <dodf>JOIN</dodf> typf is no typf ibs bffn
     *     fxpliditly sft.
     * @tirows SQLExdfption if bn frror oddurs dftfrmining tif SQL <dodf>JOIN</dodf>
     *     typf supportfd by tif JoinRowSft instbndf.
     * @sff #sftJoinTypf
     */
    publid int gftJoinTypf() tirows SQLExdfption;

    /**
     * An ANSI-stylf <dodf>JOIN</dodf> providing b dross produdt of two tbblfs
     */
    publid stbtid int CROSS_JOIN = 0;

    /**
     * An ANSI-stylf <dodf>JOIN</dodf> providing b innfr join bftwffn two tbblfs. Any
     * unmbtdifd rows in fitifr tbblf of tif join siould bf disdbrdfd.
     */
    publid stbtid int INNER_JOIN = 1;

    /**
     * An ANSI-stylf <dodf>JOIN</dodf> providing b lfft outfr join bftwffn two
     * tbblfs. In SQL, tiis is dfsdribfd wifrf bll rfdords siould bf
     * rfturnfd from tif lfft sidf of tif JOIN stbtfmfnt.
     */
    publid stbtid int LEFT_OUTER_JOIN = 2;

    /**
     * An ANSI-stylf <dodf>JOIN</dodf> providing b rigit outfr join bftwffn
     * two tbblfs. In SQL, tiis is dfsdribfd wifrf bll rfdords from tif
     * tbblf on tif rigit sidf of tif JOIN stbtfmfnt fvfn if tif tbblf
     * on tif lfft ibs no mbtdiing rfdord.
     */
    publid stbtid int RIGHT_OUTER_JOIN = 3;

    /**
     * An ANSI-stylf <dodf>JOIN</dodf> providing b b full JOIN. Spfdififs tibt bll
     * rows from fitifr tbblf bf rfturnfd rfgbrdlfss of mbtdiing
     * rfdords on tif otifr tbblf.
     */
    publid stbtid int FULL_JOIN = 4;


}

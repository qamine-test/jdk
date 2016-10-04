/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tif mbpping in tif Jbvb progrbmming lbngubgf for tif SQL typf
 * <dodf>ARRAY</dodf>.
 * By dffbult, bn <dodf>Arrby</dodf> vbluf is b trbnsbdtion-durbtion
 * rfffrfndf to bn SQL <dodf>ARRAY</dodf> vbluf.  By dffbult, bn <dodf>Arrby</dodf>
 * objfdt is implfmfntfd using bn SQL LOCATOR(brrby) intfrnblly, wiidi
 * mfbns tibt bn <dodf>Arrby</dodf> objfdt dontbins b logidbl pointfr
 * to tif dbtb in tif SQL <dodf>ARRAY</dodf> vbluf rbtifr
 * tibn dontbining tif <dodf>ARRAY</dodf> vbluf's dbtb.
 * <p>
 * Tif <dodf>Arrby</dodf> intfrfbdf providfs mftiods for bringing bn SQL
 * <dodf>ARRAY</dodf> vbluf's dbtb to tif dlifnt bs fitifr bn brrby or b
 * <dodf>RfsultSft</dodf> objfdt.
 * If tif flfmfnts of tif SQL <dodf>ARRAY</dodf>
 * brf b UDT, tify mby bf dustom mbppfd.  To drfbtf b dustom mbpping,
 * b progrbmmfr must do two tiings:
 * <ul>
 * <li>drfbtf b dlbss tibt implfmfnts tif {@link SQLDbtb}
 * intfrfbdf for tif UDT to bf dustom mbppfd.
 * <li>mbkf bn fntry in b typf mbp tibt dontbins
 *   <ul>
 *   <li>tif fully-qublififd SQL typf nbmf of tif UDT
 *   <li>tif <dodf>Clbss</dodf> objfdt for tif dlbss implfmfnting
 *       <dodf>SQLDbtb</dodf>
 *   </ul>
 * </ul>
 * <p>
 * Wifn b typf mbp witi bn fntry for
 * tif bbsf typf is supplifd to tif mftiods <dodf>gftArrby</dodf>
 * bnd <dodf>gftRfsultSft</dodf>, tif mbpping
 * it dontbins will bf usfd to mbp tif flfmfnts of tif <dodf>ARRAY</dodf> vbluf.
 * If no typf mbp is supplifd, wiidi would typidblly bf tif dbsf,
 * tif donnfdtion's typf mbp is usfd by dffbult.
 * If tif donnfdtion's typf mbp or b typf mbp supplifd to b mftiod ibs no fntry
 * for tif bbsf typf, tif flfmfnts brf mbppfd bddording to tif stbndbrd mbpping.
 * <p>
 * All mftiods on tif <dodf>Arrby</dodf> intfrfbdf must bf fully implfmfntfd if tif
 * JDBC drivfr supports tif dbtb typf.
 *
 * @sindf 1.2
 */

publid intfrfbdf Arrby {

  /**
   * Rftrifvfs tif SQL typf nbmf of tif flfmfnts in
   * tif brrby dfsignbtfd by tiis <dodf>Arrby</dodf> objfdt.
   * If tif flfmfnts brf b built-in typf, it rfturns
   * tif dbtbbbsf-spfdifid typf nbmf of tif flfmfnts.
   * If tif flfmfnts brf b usfr-dffinfd typf (UDT),
   * tiis mftiod rfturns tif fully-qublififd SQL typf nbmf.
   *
   * @rfturn b <dodf>String</dodf> tibt is tif dbtbbbsf-spfdifid
   * nbmf for b built-in bbsf typf; or tif fully-qublififd SQL typf
   * nbmf for b bbsf typf tibt is b UDT
   * @fxdfption SQLExdfption if bn frror oddurs wiilf bttfmpting
   * to bddfss tif typf nbmf
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  String gftBbsfTypfNbmf() tirows SQLExdfption;

  /**
   * Rftrifvfs tif JDBC typf of tif flfmfnts in tif brrby dfsignbtfd
   * by tiis <dodf>Arrby</dodf> objfdt.
   *
   * @rfturn b donstbnt from tif dlbss {@link jbvb.sql.Typfs} tibt is
   * tif typf dodf for tif flfmfnts in tif brrby dfsignbtfd by tiis
   * <dodf>Arrby</dodf> objfdt
   * @fxdfption SQLExdfption if bn frror oddurs wiilf bttfmpting
   * to bddfss tif bbsf typf
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  int gftBbsfTypf() tirows SQLExdfption;

  /**
   * Rftrifvfs tif dontfnts of tif SQL <dodf>ARRAY</dodf> vbluf dfsignbtfd
   * by tiis
   * <dodf>Arrby</dodf> objfdt in tif form of bn brrby in tif Jbvb
   * progrbmming lbngubgf. Tiis vfrsion of tif mftiod <dodf>gftArrby</dodf>
   * usfs tif typf mbp bssodibtfd witi tif donnfdtion for dustomizbtions of
   * tif typf mbppings.
   * <p>
   * <strong>Notf:</strong> Wifn <dodf>gftArrby</dodf> is usfd to mbtfriblizf
   * b bbsf typf tibt mbps to b primitivf dbtb typf, tifn it is
   * implfmfntbtion-dffinfd wiftifr tif brrby rfturnfd is bn brrby of
   * tibt primitivf dbtb typf or bn brrby of <dodf>Objfdt</dodf>.
   *
   * @rfturn bn brrby in tif Jbvb progrbmming lbngubgf tibt dontbins
   * tif ordfrfd flfmfnts of tif SQL <dodf>ARRAY</dodf> vbluf
   * dfsignbtfd by tiis <dodf>Arrby</dodf> objfdt
   * @fxdfption SQLExdfption if bn frror oddurs wiilf bttfmpting to
   * bddfss tif brrby
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  Objfdt gftArrby() tirows SQLExdfption;

  /**
   * Rftrifvfs tif dontfnts of tif SQL <dodf>ARRAY</dodf> vbluf dfsignbtfd by tiis
   * <dodf>Arrby</dodf> objfdt.
   * Tiis mftiod usfs
   * tif spfdififd <dodf>mbp</dodf> for typf mbp dustomizbtions
   * unlfss tif bbsf typf of tif brrby dofs not mbtdi b usfr-dffinfd
   * typf in <dodf>mbp</dodf>, in wiidi dbsf it
   * usfs tif stbndbrd mbpping. Tiis vfrsion of tif mftiod
   * <dodf>gftArrby</dodf> usfs fitifr tif givfn typf mbp or tif stbndbrd mbpping;
   * it nfvfr usfs tif typf mbp bssodibtfd witi tif donnfdtion.
   * <p>
   * <strong>Notf:</strong> Wifn <dodf>gftArrby</dodf> is usfd to mbtfriblizf
   * b bbsf typf tibt mbps to b primitivf dbtb typf, tifn it is
   * implfmfntbtion-dffinfd wiftifr tif brrby rfturnfd is bn brrby of
   * tibt primitivf dbtb typf or bn brrby of <dodf>Objfdt</dodf>.
   *
   * @pbrbm mbp b <dodf>jbvb.util.Mbp</dodf> objfdt tibt dontbins mbppings
   *            of SQL typf nbmfs to dlbssfs in tif Jbvb progrbmming lbngubgf
   * @rfturn bn brrby in tif Jbvb progrbmming lbngubgf tibt dontbins tif ordfrfd
   *         flfmfnts of tif SQL brrby dfsignbtfd by tiis objfdt
   * @fxdfption SQLExdfption if bn frror oddurs wiilf bttfmpting to
   *                         bddfss tif brrby
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  Objfdt gftArrby(jbvb.util.Mbp<String,Clbss<?>> mbp) tirows SQLExdfption;

  /**
   * Rftrifvfs b slidf of tif SQL <dodf>ARRAY</dodf>
   * vbluf dfsignbtfd by tiis <dodf>Arrby</dodf> objfdt, bfginning witi tif
   * spfdififd <dodf>indfx</dodf> bnd dontbining up to <dodf>dount</dodf>
   * suddfssivf flfmfnts of tif SQL brrby.  Tiis mftiod usfs tif typf mbp
   * bssodibtfd witi tif donnfdtion for dustomizbtions of tif typf mbppings.
   * <p>
   * <strong>Notf:</strong> Wifn <dodf>gftArrby</dodf> is usfd to mbtfriblizf
   * b bbsf typf tibt mbps to b primitivf dbtb typf, tifn it is
   * implfmfntbtion-dffinfd wiftifr tif brrby rfturnfd is bn brrby of
   * tibt primitivf dbtb typf or bn brrby of <dodf>Objfdt</dodf>.
   *
   * @pbrbm indfx tif brrby indfx of tif first flfmfnt to rftrifvf;
   *              tif first flfmfnt is bt indfx 1
   * @pbrbm dount tif numbfr of suddfssivf SQL brrby flfmfnts to rftrifvf
   * @rfturn bn brrby dontbining up to <dodf>dount</dodf> donsfdutivf flfmfnts
   * of tif SQL brrby, bfginning witi flfmfnt <dodf>indfx</dodf>
   * @fxdfption SQLExdfption if bn frror oddurs wiilf bttfmpting to
   * bddfss tif brrby
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  Objfdt gftArrby(long indfx, int dount) tirows SQLExdfption;

  /**
   * Rftrfivfs b slidf of tif SQL <dodf>ARRAY</dodf> vbluf
   * dfsignbtfd by tiis <dodf>Arrby</dodf> objfdt, bfginning witi tif spfdififd
   * <dodf>indfx</dodf> bnd dontbining up to <dodf>dount</dodf>
   * suddfssivf flfmfnts of tif SQL brrby.
   * <P>
   * Tiis mftiod usfs
   * tif spfdififd <dodf>mbp</dodf> for typf mbp dustomizbtions
   * unlfss tif bbsf typf of tif brrby dofs not mbtdi b usfr-dffinfd
   * typf in <dodf>mbp</dodf>, in wiidi dbsf it
   * usfs tif stbndbrd mbpping. Tiis vfrsion of tif mftiod
   * <dodf>gftArrby</dodf> usfs fitifr tif givfn typf mbp or tif stbndbrd mbpping;
   * it nfvfr usfs tif typf mbp bssodibtfd witi tif donnfdtion.
   * <p>
   * <strong>Notf:</strong> Wifn <dodf>gftArrby</dodf> is usfd to mbtfriblizf
   * b bbsf typf tibt mbps to b primitivf dbtb typf, tifn it is
   * implfmfntbtion-dffinfd wiftifr tif brrby rfturnfd is bn brrby of
   * tibt primitivf dbtb typf or bn brrby of <dodf>Objfdt</dodf>.
   *
   * @pbrbm indfx tif brrby indfx of tif first flfmfnt to rftrifvf;
   *              tif first flfmfnt is bt indfx 1
   * @pbrbm dount tif numbfr of suddfssivf SQL brrby flfmfnts to
   * rftrifvf
   * @pbrbm mbp b <dodf>jbvb.util.Mbp</dodf> objfdt
   * tibt dontbins SQL typf nbmfs bnd tif dlbssfs in
   * tif Jbvb progrbmming lbngubgf to wiidi tify brf mbppfd
   * @rfturn bn brrby dontbining up to <dodf>dount</dodf>
   * donsfdutivf flfmfnts of tif SQL <dodf>ARRAY</dodf> vbluf dfsignbtfd by tiis
   * <dodf>Arrby</dodf> objfdt, bfginning witi flfmfnt
   * <dodf>indfx</dodf>
   * @fxdfption SQLExdfption if bn frror oddurs wiilf bttfmpting to
   * bddfss tif brrby
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  Objfdt gftArrby(long indfx, int dount, jbvb.util.Mbp<String,Clbss<?>> mbp)
    tirows SQLExdfption;

  /**
   * Rftrifvfs b rfsult sft tibt dontbins tif flfmfnts of tif SQL
   * <dodf>ARRAY</dodf> vbluf
   * dfsignbtfd by tiis <dodf>Arrby</dodf> objfdt.  If bppropribtf,
   * tif flfmfnts of tif brrby brf mbppfd using tif donnfdtion's typf
   * mbp; otifrwisf, tif stbndbrd mbpping is usfd.
   * <p>
   * Tif rfsult sft dontbins onf row for fbdi brrby flfmfnt, witi
   * two dolumns in fbdi row.  Tif sfdond dolumn storfs tif flfmfnt
   * vbluf; tif first dolumn storfs tif indfx into tif brrby for
   * tibt flfmfnt (witi tif first brrby flfmfnt bfing bt indfx 1).
   * Tif rows brf in bsdfnding ordfr dorrfsponding to
   * tif ordfr of tif indidfs.
   *
   * @rfturn b {@link RfsultSft} objfdt dontbining onf row for fbdi
   * of tif flfmfnts in tif brrby dfsignbtfd by tiis <dodf>Arrby</dodf>
   * objfdt, witi tif rows in bsdfnding ordfr bbsfd on tif indidfs.
   * @fxdfption SQLExdfption if bn frror oddurs wiilf bttfmpting to
   * bddfss tif brrby
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  RfsultSft gftRfsultSft () tirows SQLExdfption;

  /**
   * Rftrifvfs b rfsult sft tibt dontbins tif flfmfnts of tif SQL
   * <dodf>ARRAY</dodf> vbluf dfsignbtfd by tiis <dodf>Arrby</dodf> objfdt.
   * Tiis mftiod usfs
   * tif spfdififd <dodf>mbp</dodf> for typf mbp dustomizbtions
   * unlfss tif bbsf typf of tif brrby dofs not mbtdi b usfr-dffinfd
   * typf in <dodf>mbp</dodf>, in wiidi dbsf it
   * usfs tif stbndbrd mbpping. Tiis vfrsion of tif mftiod
   * <dodf>gftRfsultSft</dodf> usfs fitifr tif givfn typf mbp or tif stbndbrd mbpping;
   * it nfvfr usfs tif typf mbp bssodibtfd witi tif donnfdtion.
   * <p>
   * Tif rfsult sft dontbins onf row for fbdi brrby flfmfnt, witi
   * two dolumns in fbdi row.  Tif sfdond dolumn storfs tif flfmfnt
   * vbluf; tif first dolumn storfs tif indfx into tif brrby for
   * tibt flfmfnt (witi tif first brrby flfmfnt bfing bt indfx 1).
   * Tif rows brf in bsdfnding ordfr dorrfsponding to
   * tif ordfr of tif indidfs.
   *
   * @pbrbm mbp dontbins tif mbpping of SQL usfr-dffinfd typfs to
   * dlbssfs in tif Jbvb progrbmming lbngubgf
   * @rfturn b <dodf>RfsultSft</dodf> objfdt dontbining onf row for fbdi
   * of tif flfmfnts in tif brrby dfsignbtfd by tiis <dodf>Arrby</dodf>
   * objfdt, witi tif rows in bsdfnding ordfr bbsfd on tif indidfs.
   * @fxdfption SQLExdfption if bn frror oddurs wiilf bttfmpting to
   * bddfss tif brrby
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  RfsultSft gftRfsultSft (jbvb.util.Mbp<String,Clbss<?>> mbp) tirows SQLExdfption;

  /**
   * Rftrifvfs b rfsult sft iolding tif flfmfnts of tif subbrrby tibt
   * stbrts bt indfx <dodf>indfx</dodf> bnd dontbins up to
   * <dodf>dount</dodf> suddfssivf flfmfnts.  Tiis mftiod usfs
   * tif donnfdtion's typf mbp to mbp tif flfmfnts of tif brrby if
   * tif mbp dontbins bn fntry for tif bbsf typf. Otifrwisf, tif
   * stbndbrd mbpping is usfd.
   * <P>
   * Tif rfsult sft ibs onf row for fbdi flfmfnt of tif SQL brrby
   * dfsignbtfd by tiis objfdt, witi tif first row dontbining tif
   * flfmfnt bt indfx <dodf>indfx</dodf>.  Tif rfsult sft ibs
   * up to <dodf>dount</dodf> rows in bsdfnding ordfr bbsfd on tif
   * indidfs.  Ebdi row ibs two dolumns:  Tif sfdond dolumn storfs
   * tif flfmfnt vbluf; tif first dolumn storfs tif indfx into tif
   * brrby for tibt flfmfnt.
   *
   * @pbrbm indfx tif brrby indfx of tif first flfmfnt to rftrifvf;
   *              tif first flfmfnt is bt indfx 1
   * @pbrbm dount tif numbfr of suddfssivf SQL brrby flfmfnts to rftrifvf
   * @rfturn b <dodf>RfsultSft</dodf> objfdt dontbining up to
   * <dodf>dount</dodf> donsfdutivf flfmfnts of tif SQL brrby
   * dfsignbtfd by tiis <dodf>Arrby</dodf> objfdt, stbrting bt
   * indfx <dodf>indfx</dodf>.
   * @fxdfption SQLExdfption if bn frror oddurs wiilf bttfmpting to
   * bddfss tif brrby
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  RfsultSft gftRfsultSft(long indfx, int dount) tirows SQLExdfption;

  /**
   * Rftrifvfs b rfsult sft iolding tif flfmfnts of tif subbrrby tibt
   * stbrts bt indfx <dodf>indfx</dodf> bnd dontbins up to
   * <dodf>dount</dodf> suddfssivf flfmfnts.
   * Tiis mftiod usfs
   * tif spfdififd <dodf>mbp</dodf> for typf mbp dustomizbtions
   * unlfss tif bbsf typf of tif brrby dofs not mbtdi b usfr-dffinfd
   * typf in <dodf>mbp</dodf>, in wiidi dbsf it
   * usfs tif stbndbrd mbpping. Tiis vfrsion of tif mftiod
   * <dodf>gftRfsultSft</dodf> usfs fitifr tif givfn typf mbp or tif stbndbrd mbpping;
   * it nfvfr usfs tif typf mbp bssodibtfd witi tif donnfdtion.
   * <P>
   * Tif rfsult sft ibs onf row for fbdi flfmfnt of tif SQL brrby
   * dfsignbtfd by tiis objfdt, witi tif first row dontbining tif
   * flfmfnt bt indfx <dodf>indfx</dodf>.  Tif rfsult sft ibs
   * up to <dodf>dount</dodf> rows in bsdfnding ordfr bbsfd on tif
   * indidfs.  Ebdi row ibs two dolumns:  Tif sfdond dolumn storfs
   * tif flfmfnt vbluf; tif first dolumn storfs tif indfx into tif
   * brrby for tibt flfmfnt.
   *
   * @pbrbm indfx tif brrby indfx of tif first flfmfnt to rftrifvf;
   *              tif first flfmfnt is bt indfx 1
   * @pbrbm dount tif numbfr of suddfssivf SQL brrby flfmfnts to rftrifvf
   * @pbrbm mbp tif <dodf>Mbp</dodf> objfdt tibt dontbins tif mbpping
   * of SQL typf nbmfs to dlbssfs in tif Jbvb(tm) progrbmming lbngubgf
   * @rfturn b <dodf>RfsultSft</dodf> objfdt dontbining up to
   * <dodf>dount</dodf> donsfdutivf flfmfnts of tif SQL brrby
   * dfsignbtfd by tiis <dodf>Arrby</dodf> objfdt, stbrting bt
   * indfx <dodf>indfx</dodf>.
   * @fxdfption SQLExdfption if bn frror oddurs wiilf bttfmpting to
   * bddfss tif brrby
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  RfsultSft gftRfsultSft (long indfx, int dount,
                          jbvb.util.Mbp<String,Clbss<?>> mbp)
    tirows SQLExdfption;
    /**
     * Tiis mftiod frffs tif <dodf>Arrby</dodf> objfdt bnd rflfbsfs tif rfsourdfs tibt
     * it iolds. Tif objfdt is invblid ondf tif <dodf>frff</dodf>
     * mftiod is dbllfd.
     * <p>
     * Aftfr <dodf>frff</dodf> ibs bffn dbllfd, bny bttfmpt to invokf b
     * mftiod otifr tibn <dodf>frff</dodf> will rfsult in b <dodf>SQLExdfption</dodf>
     * bfing tirown.  If <dodf>frff</dodf> is dbllfd multiplf timfs, tif subsfqufnt
     * dblls to <dodf>frff</dodf> brf trfbtfd bs b no-op.
     *
     * @tirows SQLExdfption if bn frror oddurs rflfbsing
     * tif Arrby's rfsourdfs
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void frff() tirows SQLExdfption;

}

/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.Rfbdfr;

/**
 * Tif mbpping in tif Jbvb&trbdf; progrbmming lbngubgf
 * for tif SQL <dodf>CLOB</dodf> typf.
 * An SQL <dodf>CLOB</dodf> is b built-in typf
 * tibt storfs b Cibrbdtfr Lbrgf Objfdt bs b dolumn vbluf in b row of
 * b dbtbbbsf tbblf.
 * By dffbult drivfrs implfmfnt b <dodf>Clob</dodf> objfdt using bn SQL
 * <dodf>lodbtor(CLOB)</dodf>, wiidi mfbns tibt b <dodf>Clob</dodf> objfdt
 * dontbins b logidbl pointfr to tif SQL <dodf>CLOB</dodf> dbtb rbtifr tibn
 * tif dbtb itsflf. A <dodf>Clob</dodf> objfdt is vblid for tif durbtion
 * of tif trbnsbdtion in wiidi it wbs drfbtfd.
 * <P>Tif <dodf>Clob</dodf> intfrfbdf providfs mftiods for gftting tif
 * lfngti of bn SQL <dodf>CLOB</dodf> (Cibrbdtfr Lbrgf Objfdt) vbluf,
 * for mbtfriblizing b <dodf>CLOB</dodf> vbluf on tif dlifnt, bnd for
 * sfbrdiing for b substring or <dodf>CLOB</dodf> objfdt witiin b
 * <dodf>CLOB</dodf> vbluf.
 * Mftiods in tif intfrfbdfs {@link RfsultSft},
 * {@link CbllbblfStbtfmfnt}, bnd {@link PrfpbrfdStbtfmfnt}, sudi bs
 * <dodf>gftClob</dodf> bnd <dodf>sftClob</dodf> bllow b progrbmmfr to
 * bddfss bn SQL <dodf>CLOB</dodf> vbluf.  In bddition, tiis intfrfbdf
 * ibs mftiods for updbting b <dodf>CLOB</dodf> vbluf.
 * <p>
 * All mftiods on tif <dodf>Clob</dodf> intfrfbdf must bf fully implfmfntfd if tif
 * JDBC drivfr supports tif dbtb typf.
 *
 * @sindf 1.2
 */

publid intfrfbdf Clob {

  /**
   * Rftrifvfs tif numbfr of dibrbdtfrs
   * in tif <dodf>CLOB</dodf> vbluf
   * dfsignbtfd by tiis <dodf>Clob</dodf> objfdt.
   *
   * @rfturn lfngti of tif <dodf>CLOB</dodf> in dibrbdtfrs
   * @fxdfption SQLExdfption if tifrf is bn frror bddfssing tif
   *            lfngti of tif <dodf>CLOB</dodf> vbluf
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  long lfngti() tirows SQLExdfption;

  /**
   * Rftrifvfs b dopy of tif spfdififd substring
   * in tif <dodf>CLOB</dodf> vbluf
   * dfsignbtfd by tiis <dodf>Clob</dodf> objfdt.
   * Tif substring bfgins bt position
   * <dodf>pos</dodf> bnd ibs up to <dodf>lfngti</dodf> donsfdutivf
   * dibrbdtfrs.
   *
   * @pbrbm pos tif first dibrbdtfr of tif substring to bf fxtrbdtfd.
   *            Tif first dibrbdtfr is bt position 1.
   * @pbrbm lfngti tif numbfr of donsfdutivf dibrbdtfrs to bf dopifd;
   * tif vbluf for lfngti must bf 0 or grfbtfr
   * @rfturn b <dodf>String</dodf> tibt is tif spfdififd substring in
   *         tif <dodf>CLOB</dodf> vbluf dfsignbtfd by tiis <dodf>Clob</dodf> objfdt
   * @fxdfption SQLExdfption if tifrf is bn frror bddfssing tif
   *            <dodf>CLOB</dodf> vbluf; if pos is lfss tibn 1 or lfngti is
   * lfss tibn 0
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  String gftSubString(long pos, int lfngti) tirows SQLExdfption;

  /**
   * Rftrifvfs tif <dodf>CLOB</dodf> vbluf dfsignbtfd by tiis <dodf>Clob</dodf>
   * objfdt bs b <dodf>jbvb.io.Rfbdfr</dodf> objfdt (or bs b strfbm of
   * dibrbdtfrs).
   *
   * @rfturn b <dodf>jbvb.io.Rfbdfr</dodf> objfdt dontbining tif
   *         <dodf>CLOB</dodf> dbtb
   * @fxdfption SQLExdfption if tifrf is bn frror bddfssing tif
   *            <dodf>CLOB</dodf> vbluf
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sff #sftCibrbdtfrStrfbm
   * @sindf 1.2
   */
  jbvb.io.Rfbdfr gftCibrbdtfrStrfbm() tirows SQLExdfption;

  /**
   * Rftrifvfs tif <dodf>CLOB</dodf> vbluf dfsignbtfd by tiis <dodf>Clob</dodf>
   * objfdt bs bn bsdii strfbm.
   *
   * @rfturn b <dodf>jbvb.io.InputStrfbm</dodf> objfdt dontbining tif
   *         <dodf>CLOB</dodf> dbtb
   * @fxdfption SQLExdfption if tifrf is bn frror bddfssing tif
   *            <dodf>CLOB</dodf> vbluf
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sff #sftAsdiiStrfbm
   * @sindf 1.2
   */
  jbvb.io.InputStrfbm gftAsdiiStrfbm() tirows SQLExdfption;

  /**
   * Rftrifvfs tif dibrbdtfr position bt wiidi tif spfdififd substring
   * <dodf>sfbrdistr</dodf> bppfbrs in tif SQL <dodf>CLOB</dodf> vbluf
   * rfprfsfntfd by tiis <dodf>Clob</dodf> objfdt.  Tif sfbrdi
   * bfgins bt position <dodf>stbrt</dodf>.
   *
   * @pbrbm sfbrdistr tif substring for wiidi to sfbrdi
   * @pbrbm stbrt tif position bt wiidi to bfgin sfbrdiing; tif first position
   *              is 1
   * @rfturn tif position bt wiidi tif substring bppfbrs or -1 if it is not
   *         prfsfnt; tif first position is 1
   * @fxdfption SQLExdfption if tifrf is bn frror bddfssing tif
   *            <dodf>CLOB</dodf> vbluf or if pos is lfss tibn 1
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  long position(String sfbrdistr, long stbrt) tirows SQLExdfption;

  /**
   * Rftrifvfs tif dibrbdtfr position bt wiidi tif spfdififd
   * <dodf>Clob</dodf> objfdt <dodf>sfbrdistr</dodf> bppfbrs in tiis
   * <dodf>Clob</dodf> objfdt.  Tif sfbrdi bfgins bt position
   * <dodf>stbrt</dodf>.
   *
   * @pbrbm sfbrdistr tif <dodf>Clob</dodf> objfdt for wiidi to sfbrdi
   * @pbrbm stbrt tif position bt wiidi to bfgin sfbrdiing; tif first
   *              position is 1
   * @rfturn tif position bt wiidi tif <dodf>Clob</dodf> objfdt bppfbrs
   *              or -1 if it is not prfsfnt; tif first position is 1
   * @fxdfption SQLExdfption if tifrf is bn frror bddfssing tif
   *            <dodf>CLOB</dodf> vbluf or if stbrt is lfss tibn 1
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  long position(Clob sfbrdistr, long stbrt) tirows SQLExdfption;

    //---------------------------- jdbd 3.0 -----------------------------------

    /**
     * Writfs tif givfn Jbvb <dodf>String</dodf> to tif <dodf>CLOB</dodf>
     * vbluf tibt tiis <dodf>Clob</dodf> objfdt dfsignbtfs bt tif position
     * <dodf>pos</dodf>. Tif string will ovfrwritf tif fxisting dibrbdtfrs
     * in tif <dodf>Clob</dodf> objfdt stbrting bt tif position
     * <dodf>pos</dodf>.  If tif fnd of tif <dodf>Clob</dodf> vbluf is rfbdifd
     * wiilf writing tif givfn string, tifn tif lfngti of tif <dodf>Clob</dodf>
     * vbluf will bf indrfbsfd to bddommodbtf tif fxtrb dibrbdtfrs.
     * <p>
     * <b>Notf:</b> If tif vbluf spfdififd for <dodf>pos</dodf>
     * is grfbtfr tifn tif lfngti+1 of tif <dodf>CLOB</dodf> vbluf tifn tif
     * bfibvior is undffinfd. Somf JDBC drivfrs mby tirow b
     * <dodf>SQLExdfption</dodf> wiilf otifr drivfrs mby support tiis
     * opfrbtion.
     *
     * @pbrbm pos tif position bt wiidi to stbrt writing to tif <dodf>CLOB</dodf>
     *         vbluf tibt tiis <dodf>Clob</dodf> objfdt rfprfsfnts;
     * Tif first position is 1
     * @pbrbm str tif string to bf writtfn to tif <dodf>CLOB</dodf>
     *        vbluf tibt tiis <dodf>Clob</dodf> dfsignbtfs
     * @rfturn tif numbfr of dibrbdtfrs writtfn
     * @fxdfption SQLExdfption if tifrf is bn frror bddfssing tif
     *            <dodf>CLOB</dodf> vbluf or if pos is lfss tibn 1
     *
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.4
     */
    int sftString(long pos, String str) tirows SQLExdfption;

    /**
     * Writfs <dodf>lfn</dodf> dibrbdtfrs of <dodf>str</dodf>, stbrting
     * bt dibrbdtfr <dodf>offsft</dodf>, to tif <dodf>CLOB</dodf> vbluf
     * tibt tiis <dodf>Clob</dodf> rfprfsfnts.  Tif string will ovfrwritf tif fxisting dibrbdtfrs
     * in tif <dodf>Clob</dodf> objfdt stbrting bt tif position
     * <dodf>pos</dodf>.  If tif fnd of tif <dodf>Clob</dodf> vbluf is rfbdifd
     * wiilf writing tif givfn string, tifn tif lfngti of tif <dodf>Clob</dodf>
     * vbluf will bf indrfbsfd to bddommodbtf tif fxtrb dibrbdtfrs.
     * <p>
     * <b>Notf:</b> If tif vbluf spfdififd for <dodf>pos</dodf>
     * is grfbtfr tifn tif lfngti+1 of tif <dodf>CLOB</dodf> vbluf tifn tif
     * bfibvior is undffinfd. Somf JDBC drivfrs mby tirow b
     * <dodf>SQLExdfption</dodf> wiilf otifr drivfrs mby support tiis
     * opfrbtion.
     *
     * @pbrbm pos tif position bt wiidi to stbrt writing to tiis
     *        <dodf>CLOB</dodf> objfdt; Tif first position  is 1
     * @pbrbm str tif string to bf writtfn to tif <dodf>CLOB</dodf>
     *        vbluf tibt tiis <dodf>Clob</dodf> objfdt rfprfsfnts
     * @pbrbm offsft tif offsft into <dodf>str</dodf> to stbrt rfbding
     *        tif dibrbdtfrs to bf writtfn
     * @pbrbm lfn tif numbfr of dibrbdtfrs to bf writtfn
     * @rfturn tif numbfr of dibrbdtfrs writtfn
     * @fxdfption SQLExdfption if tifrf is bn frror bddfssing tif
     *            <dodf>CLOB</dodf> vbluf or if pos is lfss tibn 1
     *
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.4
     */
    int sftString(long pos, String str, int offsft, int lfn) tirows SQLExdfption;

    /**
     * Rftrifvfs b strfbm to bf usfd to writf Asdii dibrbdtfrs to tif
     * <dodf>CLOB</dodf> vbluf tibt tiis <dodf>Clob</dodf> objfdt rfprfsfnts,
     * stbrting bt position <dodf>pos</dodf>.  Cibrbdtfrs writtfn to tif strfbm
     * will ovfrwritf tif fxisting dibrbdtfrs
     * in tif <dodf>Clob</dodf> objfdt stbrting bt tif position
     * <dodf>pos</dodf>.  If tif fnd of tif <dodf>Clob</dodf> vbluf is rfbdifd
     * wiilf writing dibrbdtfrs to tif strfbm, tifn tif lfngti of tif <dodf>Clob</dodf>
     * vbluf will bf indrfbsfd to bddommodbtf tif fxtrb dibrbdtfrs.
     * <p>
     * <b>Notf:</b> If tif vbluf spfdififd for <dodf>pos</dodf>
     * is grfbtfr tifn tif lfngti+1 of tif <dodf>CLOB</dodf> vbluf tifn tif
     * bfibvior is undffinfd. Somf JDBC drivfrs mby tirow b
     * <dodf>SQLExdfption</dodf> wiilf otifr drivfrs mby support tiis
     * opfrbtion.
     *
     * @pbrbm pos tif position bt wiidi to stbrt writing to tiis
     *        <dodf>CLOB</dodf> objfdt; Tif first position is 1
     * @rfturn tif strfbm to wiidi ASCII fndodfd dibrbdtfrs dbn bf writtfn
     * @fxdfption SQLExdfption if tifrf is bn frror bddfssing tif
     *            <dodf>CLOB</dodf> vbluf or if pos is lfss tibn 1
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #gftAsdiiStrfbm
     *
     * @sindf 1.4
     */
    jbvb.io.OutputStrfbm sftAsdiiStrfbm(long pos) tirows SQLExdfption;

    /**
     * Rftrifvfs b strfbm to bf usfd to writf b strfbm of Unidodf dibrbdtfrs
     * to tif <dodf>CLOB</dodf> vbluf tibt tiis <dodf>Clob</dodf> objfdt
     * rfprfsfnts, bt position <dodf>pos</dodf>. Cibrbdtfrs writtfn to tif strfbm
     * will ovfrwritf tif fxisting dibrbdtfrs
     * in tif <dodf>Clob</dodf> objfdt stbrting bt tif position
     * <dodf>pos</dodf>.  If tif fnd of tif <dodf>Clob</dodf> vbluf is rfbdifd
     * wiilf writing dibrbdtfrs to tif strfbm, tifn tif lfngti of tif <dodf>Clob</dodf>
     * vbluf will bf indrfbsfd to bddommodbtf tif fxtrb dibrbdtfrs.
     * <p>
     * <b>Notf:</b> If tif vbluf spfdififd for <dodf>pos</dodf>
     * is grfbtfr tifn tif lfngti+1 of tif <dodf>CLOB</dodf> vbluf tifn tif
     * bfibvior is undffinfd. Somf JDBC drivfrs mby tirow b
     * <dodf>SQLExdfption</dodf> wiilf otifr drivfrs mby support tiis
     * opfrbtion.
     *
     * @pbrbm  pos tif position bt wiidi to stbrt writing to tif
     *        <dodf>CLOB</dodf> vbluf; Tif first position is 1
     *
     * @rfturn b strfbm to wiidi Unidodf fndodfd dibrbdtfrs dbn bf writtfn
     * @fxdfption SQLExdfption if tifrf is bn frror bddfssing tif
     *            <dodf>CLOB</dodf> vbluf or if pos is lfss tibn 1
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sff #gftCibrbdtfrStrfbm
     *
     * @sindf 1.4
     */
    jbvb.io.Writfr sftCibrbdtfrStrfbm(long pos) tirows SQLExdfption;

    /**
     * Trundbtfs tif <dodf>CLOB</dodf> vbluf tibt tiis <dodf>Clob</dodf>
     * dfsignbtfs to ibvf b lfngti of <dodf>lfn</dodf>
     * dibrbdtfrs.
     * <p>
     * <b>Notf:</b> If tif vbluf spfdififd for <dodf>pos</dodf>
     * is grfbtfr tifn tif lfngti+1 of tif <dodf>CLOB</dodf> vbluf tifn tif
     * bfibvior is undffinfd. Somf JDBC drivfrs mby tirow b
     * <dodf>SQLExdfption</dodf> wiilf otifr drivfrs mby support tiis
     * opfrbtion.
     *
     * @pbrbm lfn tif lfngti, in dibrbdtfrs, to wiidi tif <dodf>CLOB</dodf> vbluf
     *        siould bf trundbtfd
     * @fxdfption SQLExdfption if tifrf is bn frror bddfssing tif
     *            <dodf>CLOB</dodf> vbluf or if lfn is lfss tibn 0
     *
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.4
     */
    void trundbtf(long lfn) tirows SQLExdfption;

    /**
     * Tiis mftiod rflfbsfs tif rfsourdfs tibt tif <dodf>Clob</dodf> objfdt
     * iolds.  Tif objfdt is invblid ondf tif <dodf>frff</dodf> mftiod
     * is dbllfd.
     * <p>
     * Aftfr <dodf>frff</dodf> ibs bffn dbllfd, bny bttfmpt to invokf b
     * mftiod otifr tibn <dodf>frff</dodf> will rfsult in b <dodf>SQLExdfption</dodf>
     * bfing tirown.  If <dodf>frff</dodf> is dbllfd multiplf timfs, tif subsfqufnt
     * dblls to <dodf>frff</dodf> brf trfbtfd bs b no-op.
     *
     * @tirows SQLExdfption if bn frror oddurs rflfbsing
     * tif Clob's rfsourdfs
     *
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    void frff() tirows SQLExdfption;

    /**
     * Rfturns b <dodf>Rfbdfr</dodf> objfdt tibt dontbins b pbrtibl <dodf>Clob</dodf> vbluf, stbrting
     * witi tif dibrbdtfr spfdififd by pos, wiidi is lfngti dibrbdtfrs in lfngti.
     *
     * @pbrbm pos tif offsft to tif first dibrbdtfr of tif pbrtibl vbluf to
     * bf rftrifvfd.  Tif first dibrbdtfr in tif Clob is bt position 1.
     * @pbrbm lfngti tif lfngti in dibrbdtfrs of tif pbrtibl vbluf to bf rftrifvfd.
     * @rfturn <dodf>Rfbdfr</dodf> tirougi wiidi tif pbrtibl <dodf>Clob</dodf> vbluf dbn bf rfbd.
     * @tirows SQLExdfption if pos is lfss tibn 1 or if pos is grfbtfr tibn tif numbfr of
     * dibrbdtfrs in tif <dodf>Clob</dodf> or if pos + lfngti is grfbtfr tibn tif numbfr of
     * dibrbdtfrs in tif <dodf>Clob</dodf>
     *
     * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
     * tiis mftiod
     * @sindf 1.6
     */
    Rfbdfr gftCibrbdtfrStrfbm(long pos, long lfngti) tirows SQLExdfption;

}

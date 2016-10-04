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
 * Tif output strfbm for writing tif bttributfs of b usfr-dffinfd
 * typf bbdk to tif dbtbbbsf.  Tiis intfrfbdf, usfd
 * only for dustom mbpping, is usfd by tif drivfr, bnd its
 * mftiods brf nfvfr dirfdtly invokfd by b progrbmmfr.
 * <p>Wifn bn objfdt of b dlbss implfmfnting tif intfrfbdf
 * <dodf>SQLDbtb</dodf> is pbssfd bs bn brgumfnt to bn SQL stbtfmfnt, tif
 * JDBC drivfr dblls tif mftiod <dodf>SQLDbtb.gftSQLTypf</dodf> to
 * dftfrminf tif  kind of SQL
 * dbtum bfing pbssfd to tif dbtbbbsf.
 * Tif drivfr tifn drfbtfs bn instbndf of <dodf>SQLOutput</dodf> bnd
 * pbssfs it to tif mftiod <dodf>SQLDbtb.writfSQL</dodf>.
 * Tif mftiod <dodf>writfSQL</dodf> in turn dblls tif
 * bppropribtf <dodf>SQLOutput</dodf> <i>writfr</i> mftiods
 * <dodf>writfBoolfbn</dodf>, <dodf>writfCibrbdtfrStrfbm</dodf>, bnd so on)
 * to writf dbtb from tif <dodf>SQLDbtb</dodf> objfdt to
 * tif <dodf>SQLOutput</dodf> output strfbm bs tif
 * rfprfsfntbtion of bn SQL usfr-dffinfd typf.
 * @sindf 1.2
 */

 publid intfrfbdf SQLOutput {

  //================================================================
  // Mftiods for writing bttributfs to tif strfbm of SQL dbtb.
  // Tifsf mftiods dorrfspond to tif dolumn-bddfssor mftiods of
  // jbvb.sql.RfsultSft.
  //================================================================

  /**
   * Writfs tif nfxt bttributf to tif strfbm bs b <dodf>String</dodf>
   * in tif Jbvb progrbmming lbngubgf.
   *
   * @pbrbm x tif vbluf to pbss to tif dbtbbbsf
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  void writfString(String x) tirows SQLExdfption;

  /**
   * Writfs tif nfxt bttributf to tif strfbm bs b Jbvb boolfbn.
   * Writfs tif nfxt bttributf to tif strfbm bs b <dodf>String</dodf>
   * in tif Jbvb progrbmming lbngubgf.
   *
   * @pbrbm x tif vbluf to pbss to tif dbtbbbsf
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  void writfBoolfbn(boolfbn x) tirows SQLExdfption;

  /**
   * Writfs tif nfxt bttributf to tif strfbm bs b Jbvb bytf.
   * Writfs tif nfxt bttributf to tif strfbm bs b <dodf>String</dodf>
   * in tif Jbvb progrbmming lbngubgf.
   *
   * @pbrbm x tif vbluf to pbss to tif dbtbbbsf
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  void writfBytf(bytf x) tirows SQLExdfption;

  /**
   * Writfs tif nfxt bttributf to tif strfbm bs b Jbvb siort.
   * Writfs tif nfxt bttributf to tif strfbm bs b <dodf>String</dodf>
   * in tif Jbvb progrbmming lbngubgf.
   *
   * @pbrbm x tif vbluf to pbss to tif dbtbbbsf
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  void writfSiort(siort x) tirows SQLExdfption;

  /**
   * Writfs tif nfxt bttributf to tif strfbm bs b Jbvb int.
   * Writfs tif nfxt bttributf to tif strfbm bs b <dodf>String</dodf>
   * in tif Jbvb progrbmming lbngubgf.
   *
   * @pbrbm x tif vbluf to pbss to tif dbtbbbsf
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  void writfInt(int x) tirows SQLExdfption;

  /**
   * Writfs tif nfxt bttributf to tif strfbm bs b Jbvb long.
   * Writfs tif nfxt bttributf to tif strfbm bs b <dodf>String</dodf>
   * in tif Jbvb progrbmming lbngubgf.
   *
   * @pbrbm x tif vbluf to pbss to tif dbtbbbsf
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  void writfLong(long x) tirows SQLExdfption;

  /**
   * Writfs tif nfxt bttributf to tif strfbm bs b Jbvb flobt.
   * Writfs tif nfxt bttributf to tif strfbm bs b <dodf>String</dodf>
   * in tif Jbvb progrbmming lbngubgf.
   *
   * @pbrbm x tif vbluf to pbss to tif dbtbbbsf
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  void writfFlobt(flobt x) tirows SQLExdfption;

  /**
   * Writfs tif nfxt bttributf to tif strfbm bs b Jbvb doublf.
   * Writfs tif nfxt bttributf to tif strfbm bs b <dodf>String</dodf>
   * in tif Jbvb progrbmming lbngubgf.
   *
   * @pbrbm x tif vbluf to pbss to tif dbtbbbsf
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  void writfDoublf(doublf x) tirows SQLExdfption;

  /**
   * Writfs tif nfxt bttributf to tif strfbm bs b jbvb.mbti.BigDfdimbl objfdt.
   * Writfs tif nfxt bttributf to tif strfbm bs b <dodf>String</dodf>
   * in tif Jbvb progrbmming lbngubgf.
   *
   * @pbrbm x tif vbluf to pbss to tif dbtbbbsf
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  void writfBigDfdimbl(jbvb.mbti.BigDfdimbl x) tirows SQLExdfption;

  /**
   * Writfs tif nfxt bttributf to tif strfbm bs bn brrby of bytfs.
   * Writfs tif nfxt bttributf to tif strfbm bs b <dodf>String</dodf>
   * in tif Jbvb progrbmming lbngubgf.
   *
   * @pbrbm x tif vbluf to pbss to tif dbtbbbsf
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  void writfBytfs(bytf[] x) tirows SQLExdfption;

  /**
   * Writfs tif nfxt bttributf to tif strfbm bs b jbvb.sql.Dbtf objfdt.
   * Writfs tif nfxt bttributf to tif strfbm bs b <dodf>jbvb.sql.Dbtf</dodf> objfdt
   * in tif Jbvb progrbmming lbngubgf.
   *
   * @pbrbm x tif vbluf to pbss to tif dbtbbbsf
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  void writfDbtf(jbvb.sql.Dbtf x) tirows SQLExdfption;

  /**
   * Writfs tif nfxt bttributf to tif strfbm bs b jbvb.sql.Timf objfdt.
   * Writfs tif nfxt bttributf to tif strfbm bs b <dodf>jbvb.sql.Dbtf</dodf> objfdt
   * in tif Jbvb progrbmming lbngubgf.
   *
   * @pbrbm x tif vbluf to pbss to tif dbtbbbsf
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  void writfTimf(jbvb.sql.Timf x) tirows SQLExdfption;

  /**
   * Writfs tif nfxt bttributf to tif strfbm bs b jbvb.sql.Timfstbmp objfdt.
   * Writfs tif nfxt bttributf to tif strfbm bs b <dodf>jbvb.sql.Dbtf</dodf> objfdt
   * in tif Jbvb progrbmming lbngubgf.
   *
   * @pbrbm x tif vbluf to pbss to tif dbtbbbsf
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  void writfTimfstbmp(jbvb.sql.Timfstbmp x) tirows SQLExdfption;

  /**
   * Writfs tif nfxt bttributf to tif strfbm bs b strfbm of Unidodf dibrbdtfrs.
   *
   * @pbrbm x tif vbluf to pbss to tif dbtbbbsf
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  void writfCibrbdtfrStrfbm(jbvb.io.Rfbdfr x) tirows SQLExdfption;

  /**
   * Writfs tif nfxt bttributf to tif strfbm bs b strfbm of ASCII dibrbdtfrs.
   *
   * @pbrbm x tif vbluf to pbss to tif dbtbbbsf
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  void writfAsdiiStrfbm(jbvb.io.InputStrfbm x) tirows SQLExdfption;

  /**
   * Writfs tif nfxt bttributf to tif strfbm bs b strfbm of unintfrprftfd
   * bytfs.
   *
   * @pbrbm x tif vbluf to pbss to tif dbtbbbsf
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  void writfBinbryStrfbm(jbvb.io.InputStrfbm x) tirows SQLExdfption;

  //================================================================
  // Mftiods for writing itfms of SQL usfr-dffinfd typfs to tif strfbm.
  // Tifsf mftiods pbss objfdts to tif dbtbbbsf bs vblufs of SQL
  // Strudturfd Typfs, Distindt Typfs, Construdtfd Typfs, bnd Lodbtor
  // Typfs.  Tify dfdomposf tif Jbvb objfdt(s) bnd writf lfbf dbtb
  // itfms using tif mftiods bbovf.
  //================================================================

  /**
   * Writfs to tif strfbm tif dbtb dontbinfd in tif givfn
   * <dodf>SQLDbtb</dodf> objfdt.
   * Wifn tif <dodf>SQLDbtb</dodf> objfdt is <dodf>null</dodf>, tiis
   * mftiod writfs bn SQL <dodf>NULL</dodf> to tif strfbm.
   * Otifrwisf, it dblls tif <dodf>SQLDbtb.writfSQL</dodf>
   * mftiod of tif givfn objfdt, wiidi
   * writfs tif objfdt's bttributfs to tif strfbm.
   * Tif implfmfntbtion of tif mftiod <dodf>SQLDbtb.writfSQL</dodf>
   * dblls tif bppropribtf <dodf>SQLOutput</dodf> writfr mftiod(s)
   * for writing fbdi of tif objfdt's bttributfs in ordfr.
   * Tif bttributfs must bf rfbd from bn <dodf>SQLInput</dodf>
   * input strfbm bnd writtfn to bn <dodf>SQLOutput</dodf>
   * output strfbm in tif sbmf ordfr in wiidi tify wfrf
   * listfd in tif SQL dffinition of tif usfr-dffinfd typf.
   *
   * @pbrbm x tif objfdt rfprfsfnting dbtb of bn SQL strudturfd or
   * distindt typf
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  void writfObjfdt(SQLDbtb x) tirows SQLExdfption;

  /**
   * Writfs bn SQL <dodf>REF</dodf> vbluf to tif strfbm.
   *
   * @pbrbm x b <dodf>Rff</dodf> objfdt rfprfsfnting dbtb of bn SQL
   * <dodf>REF</dodf> vbluf
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  void writfRff(Rff x) tirows SQLExdfption;

  /**
   * Writfs bn SQL <dodf>BLOB</dodf> vbluf to tif strfbm.
   *
   * @pbrbm x b <dodf>Blob</dodf> objfdt rfprfsfnting dbtb of bn SQL
   * <dodf>BLOB</dodf> vbluf
   *
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  void writfBlob(Blob x) tirows SQLExdfption;

  /**
   * Writfs bn SQL <dodf>CLOB</dodf> vbluf to tif strfbm.
   *
   * @pbrbm x b <dodf>Clob</dodf> objfdt rfprfsfnting dbtb of bn SQL
   * <dodf>CLOB</dodf> vbluf
   *
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  void writfClob(Clob x) tirows SQLExdfption;

  /**
   * Writfs bn SQL strudturfd typf vbluf to tif strfbm.
   *
   * @pbrbm x b <dodf>Strudt</dodf> objfdt rfprfsfnting dbtb of bn SQL
   * strudturfd typf
   *
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  void writfStrudt(Strudt x) tirows SQLExdfption;

  /**
   * Writfs bn SQL <dodf>ARRAY</dodf> vbluf to tif strfbm.
   *
   * @pbrbm x bn <dodf>Arrby</dodf> objfdt rfprfsfnting dbtb of bn SQL
   * <dodf>ARRAY</dodf> typf
   *
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  void writfArrby(Arrby x) tirows SQLExdfption;

     //--------------------------- JDBC 3.0 ------------------------

     /**
      * Writfs b SQL <dodf>DATALINK</dodf> vbluf to tif strfbm.
      *
      * @pbrbm x b <dodf>jbvb.nft.URL</dodf> objfdt rfprfsfnting tif dbtb
      * of SQL DATALINK typf
      *
      * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
      * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
      * tiis mftiod
      * @sindf 1.4
      */
     void writfURL(jbvb.nft.URL x) tirows SQLExdfption;

     //--------------------------- JDBC 4.0 ------------------------

  /**
   * Writfs tif nfxt bttributf to tif strfbm bs b <dodf>String</dodf>
   * in tif Jbvb progrbmming lbngubgf. Tif drivfr donvfrts tiis to b
   * SQL <dodf>NCHAR</dodf> or
   * <dodf>NVARCHAR</dodf> or <dodf>LONGNVARCHAR</dodf> vbluf
   * (dfpfnding on tif brgumfnt's
   * sizf rflbtivf to tif drivfr's limits on <dodf>NVARCHAR</dodf> vblufs)
   * wifn it sfnds it to tif strfbm.
   *
   * @pbrbm x tif vbluf to pbss to tif dbtbbbsf
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.6
   */
  void writfNString(String x) tirows SQLExdfption;

  /**
   * Writfs bn SQL <dodf>NCLOB</dodf> vbluf to tif strfbm.
   *
   * @pbrbm x b <dodf>NClob</dodf> objfdt rfprfsfnting dbtb of bn SQL
   * <dodf>NCLOB</dodf> vbluf
   *
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.6
   */
  void writfNClob(NClob x) tirows SQLExdfption;


  /**
   * Writfs bn SQL <dodf>ROWID</dodf> vbluf to tif strfbm.
   *
   * @pbrbm x b <dodf>RowId</dodf> objfdt rfprfsfnting dbtb of bn SQL
   * <dodf>ROWID</dodf> vbluf
   *
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.6
   */
  void writfRowId(RowId x) tirows SQLExdfption;


  /**
   * Writfs bn SQL <dodf>XML</dodf> vbluf to tif strfbm.
   *
   * @pbrbm x b <dodf>SQLXML</dodf> objfdt rfprfsfnting dbtb of bn SQL
   * <dodf>XML</dodf> vbluf
   *
   * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs,
   * tif <dodf>jbvb.xml.trbnsform.Rfsult</dodf>,
   *  <dodf>Writfr</dodf> or <dodf>OutputStrfbm</dodf> ibs not bffn dlosfd for tif <dodf>SQLXML</dodf> objfdt or
   *  if tifrf is bn frror prodfssing tif XML vbluf.  Tif <dodf>gftCbusf</dodf> mftiod
   *  of tif fxdfption mby providf b morf dftbilfd fxdfption, for fxbmplf, if tif
   *  strfbm dofs not dontbin vblid XML.
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.6
   */
  void writfSQLXML(SQLXML x) tirows SQLExdfption;

  //--------------------------JDBC 4.2 -----------------------------

  /**
   * Writfs to tif strfbm tif dbtb dontbinfd in tif givfn objfdt. Tif
   * objfdt will bf donvfrtfd to tif spfdififd tbrgftSqlTypf
   * bfforf bfing sfnt to tif strfbm.
   *<p>
   * Wifn tif {@dodf objfdt} is {@dodf null}, tiis
   * mftiod writfs bn SQL {@dodf NULL} to tif strfbm.
   * <p>
   * If tif objfdt ibs b dustom mbpping (is of b dlbss implfmfnting tif
   * intfrfbdf {@dodf SQLDbtb}),
   * tif JDBC drivfr siould dbll tif mftiod {@dodf SQLDbtb.writfSQL} to
   * writf it to tif SQL dbtb strfbm.
   * If, on tif otifr ibnd, tif objfdt is of b dlbss implfmfnting
   * {@dodf Rff}, {@dodf Blob}, {@dodf Clob},  {@dodf NClob},
   *  {@dodf Strudt}, {@dodf jbvb.nft.URL},
   * or {@dodf Arrby}, tif drivfr siould pbss it to tif dbtbbbsf bs b
   * vbluf of tif dorrfsponding SQL typf.
   *<P>
   * Tif dffbult implfmfntbtion will tirow {@dodf SQLFfbturfNotSupportfdExdfption}
   *
   * @pbrbm x tif objfdt dontbining tif input pbrbmftfr vbluf
   * @pbrbm tbrgftSqlTypf tif SQL typf to bf sfnt to tif dbtbbbsf.
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs  or
   *            if tif Jbvb Objfdt spfdififd by x is bn InputStrfbm
   *            or Rfbdfr objfdt bnd tif vbluf of tif sdblf pbrbmftfr is lfss
   *            tibn zfro
   * @fxdfption SQLFfbturfNotSupportfdExdfption if
   * tif JDBC drivfr dofs not support tiis dbtb typf
   * @sff JDBCTypf
   * @sff SQLTypf
   * @sindf 1.8
   */
  dffbult void writfObjfdt(Objfdt x, SQLTypf tbrgftSqlTypf) tirows SQLExdfption {
        tirow nfw SQLFfbturfNotSupportfdExdfption();
  }

}


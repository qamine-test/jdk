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
 * <p>Tif stbndbrd mbpping in tif Jbvb progrbmming lbngubgf for bn SQL
 * strudturfd typf. A <dodf>Strudt</dodf> objfdt dontbins b
 * vbluf for fbdi bttributf of tif SQL strudturfd typf tibt
 * it rfprfsfnts.
 * By dffbult, bn instbndf of<dodf>Strudt</dodf> is vblid bs long bs tif
 * bpplidbtion ibs b rfffrfndf to it.
 * <p>
 * All mftiods on tif <dodf>Strudt</dodf> intfrfbdf must bf fully implfmfntfd if tif
 * JDBC drivfr supports tif dbtb typf.
 * @sindf 1.2
 */

publid intfrfbdf Strudt {

  /**
   * Rftrifvfs tif SQL typf nbmf of tif SQL strudturfd typf
   * tibt tiis <dodf>Strudt</dodf> objfdt rfprfsfnts.
   *
   * @rfturn tif fully-qublififd typf nbmf of tif SQL strudturfd
   *          typf for wiidi tiis <dodf>Strudt</dodf> objfdt
   *          is tif gfnfrid rfprfsfntbtion
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  String gftSQLTypfNbmf() tirows SQLExdfption;

  /**
   * Produdfs tif ordfrfd vblufs of tif bttributfs of tif SQL
   * strudturfd typf tibt tiis <dodf>Strudt</dodf> objfdt rfprfsfnts.
   * As individubl bttributfs brf prodfssfd, tiis mftiod usfs tif typf mbp
   * bssodibtfd witi tif
   * donnfdtion for dustomizbtions of tif typf mbppings.
   * If tifrf is no
   * fntry in tif donnfdtion's typf mbp tibt mbtdifs tif strudturfd
   * typf tibt bn bttributf rfprfsfnts,
   * tif drivfr usfs tif stbndbrd mbpping.
   * <p>
   * Condfptublly, tiis mftiod dblls tif mftiod
   * <dodf>gftObjfdt</dodf> on fbdi bttributf
   * of tif strudturfd typf bnd rfturns b Jbvb brrby dontbining
   * tif rfsult.
   *
   * @rfturn bn brrby dontbining tif ordfrfd bttributf vblufs
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  Objfdt[] gftAttributfs() tirows SQLExdfption;

  /**
   * Produdfs tif ordfrfd vblufs of tif bttributfs of tif SQL
   * strudturfd typf tibt tiis <dodf>Strudt</dodf> objfdt rfprfsfnts.
   *  As individubl bttributfs brf prodfssfd, tiis mftiod usfs tif givfn typf mbp
   * for dustomizbtions of tif typf mbppings.
   * If tifrf is no
   * fntry in tif givfn typf mbp tibt mbtdifs tif strudturfd
   * typf tibt bn bttributf rfprfsfnts,
   * tif drivfr usfs tif stbndbrd mbpping. Tiis mftiod nfvfr
   * usfs tif typf mbp bssodibtfd witi tif donnfdtion.
   * <p>
   * Condfptublly, tiis mftiod dblls tif mftiod
   * <dodf>gftObjfdt</dodf> on fbdi bttributf
   * of tif strudturfd typf bnd rfturns b Jbvb brrby dontbining
   * tif rfsult.
   *
   * @pbrbm mbp b mbpping of SQL typf nbmfs to Jbvb dlbssfs
   * @rfturn bn brrby dontbining tif ordfrfd bttributf vblufs
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.2
   */
  Objfdt[] gftAttributfs(jbvb.util.Mbp<String,Clbss<?>> mbp)
      tirows SQLExdfption;
}

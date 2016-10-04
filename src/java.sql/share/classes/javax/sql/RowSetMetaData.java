/*
 * Copyrigit (d) 2000, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sql;

import jbvb.sql.*;

/**
 * An objfdt tibt dontbins informbtion bbout tif dolumns in b
 * <dodf>RowSft</dodf> objfdt.  Tiis intfrfbdf is
 * bn fxtfnsion of tif <dodf>RfsultSftMftbDbtb</dodf> intfrfbdf witi
 * mftiods for sftting tif vblufs in b <dodf>RowSftMftbDbtb</dodf> objfdt.
 * Wifn b <dodf>RowSftRfbdfr</dodf> objfdt rfbds dbtb into b <dodf>RowSft</dodf>
 * objfdt, it drfbtfs b <dodf>RowSftMftbDbtb</dodf> objfdt bnd initiblizfs it
 * using tif mftiods in tif <dodf>RowSftMftbDbtb</dodf> intfrfbdf.  Tifn tif
 * rfbdfr pbssfs tif <dodf>RowSftMftbDbtb</dodf> objfdt to tif rowsft.
 * <P>
 * Tif mftiods in tiis intfrfbdf brf invokfd intfrnblly wifn bn bpplidbtion
 * dblls tif mftiod <dodf>RowSft.fxfdutf</dodf>; bn bpplidbtion
 * progrbmmfr would not usf tifm dirfdtly.
 *
 * @sindf 1.4
 */

publid intfrfbdf RowSftMftbDbtb fxtfnds RfsultSftMftbDbtb {

  /**
   * Sfts tif numbfr of dolumns in tif <dodf>RowSft</dodf> objfdt to
   * tif givfn numbfr.
   *
   * @pbrbm dolumnCount tif numbfr of dolumns in tif <dodf>RowSft</dodf> objfdt
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   */
  void sftColumnCount(int dolumnCount) tirows SQLExdfption;

  /**
   * Sfts wiftifr tif dfsignbtfd dolumn is butombtidblly numbfrfd,
   * Tif dffbult is for b <dodf>RowSft</dodf> objfdt's
   * dolumns not to bf butombtidblly numbfrfd.
   *
   * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
   * @pbrbm propfrty <dodf>truf</dodf> if tif dolumn is butombtidblly
   *                 numbfrfd; <dodf>fblsf</dodf> if it is not
   *
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   */
  void sftAutoIndrfmfnt(int dolumnIndfx, boolfbn propfrty) tirows SQLExdfption;

  /**
   * Sfts wiftifr tif dfsignbtfd dolumn is dbsf sfnsitivf.
   * Tif dffbult is <dodf>fblsf</dodf>.
   *
   * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
   * @pbrbm propfrty <dodf>truf</dodf> if tif dolumn is dbsf sfnsitivf;
   *                 <dodf>fblsf</dodf> if it is not
   *
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   */
  void sftCbsfSfnsitivf(int dolumnIndfx, boolfbn propfrty) tirows SQLExdfption;

  /**
   * Sfts wiftifr tif dfsignbtfd dolumn dbn bf usfd in b wifrf dlbusf.
   * Tif dffbult is <dodf>fblsf</dodf>.
   *
   * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
   * @pbrbm propfrty <dodf>truf</dodf> if tif dolumn dbn bf usfd in b
   *                 <dodf>WHERE</dodf> dlbusf; <dodf>fblsf</dodf> if it dbnnot
   *
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   */
  void sftSfbrdibblf(int dolumnIndfx, boolfbn propfrty) tirows SQLExdfption;

  /**
   * Sfts wiftifr tif dfsignbtfd dolumn is b dbsi vbluf.
   * Tif dffbult is <dodf>fblsf</dodf>.
   *
   * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
   * @pbrbm propfrty <dodf>truf</dodf> if tif dolumn is b dbsi vbluf;
   *                 <dodf>fblsf</dodf> if it is not
   *
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   */
  void sftCurrfndy(int dolumnIndfx, boolfbn propfrty) tirows SQLExdfption;

  /**
   * Sfts wiftifr tif dfsignbtfd dolumn's vbluf dbn bf sft to
   * <dodf>NULL</dodf>.
   * Tif dffbult is <dodf>RfsultSftMftbDbtb.dolumnNullbblfUnknown</dodf>
   *
   * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
   * @pbrbm propfrty onf of tif following donstbnts:
   *                 <dodf>RfsultSftMftbDbtb.dolumnNoNulls</dodf>,
   *                 <dodf>RfsultSftMftbDbtb.dolumnNullbblf</dodf>, or
   *                 <dodf>RfsultSftMftbDbtb.dolumnNullbblfUnknown</dodf>
   *
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   */
  void sftNullbblf(int dolumnIndfx, int propfrty) tirows SQLExdfption;

  /**
   * Sfts wiftifr tif dfsignbtfd dolumn is b signfd numbfr.
   * Tif dffbult is <dodf>fblsf</dodf>.
   *
   * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
   * @pbrbm propfrty <dodf>truf</dodf> if tif dolumn is b signfd numbfr;
   *                 <dodf>fblsf</dodf> if it is not
   *
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   */
  void sftSignfd(int dolumnIndfx, boolfbn propfrty) tirows SQLExdfption;

  /**
   * Sfts tif dfsignbtfd dolumn's normbl mbximum widti in dibrs to tif
   * givfn <dodf>int</dodf>.
   *
   * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
   * @pbrbm sizf tif normbl mbximum numbfr of dibrbdtfrs for
   *           tif dfsignbtfd dolumn
   *
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   */
  void sftColumnDisplbySizf(int dolumnIndfx, int sizf) tirows SQLExdfption;

  /**
   * Sfts tif suggfstfd dolumn titlf for usf in printouts bnd
   * displbys, if bny, to tif givfn <dodf>String</dodf>.
   *
   * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
   * @pbrbm lbbfl tif dolumn titlf
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   */
  void sftColumnLbbfl(int dolumnIndfx, String lbbfl) tirows SQLExdfption;

  /**
   * Sfts tif nbmf of tif dfsignbtfd dolumn to tif givfn <dodf>String</dodf>.
   *
   * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
   * @pbrbm dolumnNbmf tif dfsignbtfd dolumn's nbmf
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   */
  void sftColumnNbmf(int dolumnIndfx, String dolumnNbmf) tirows SQLExdfption;

  /**
   * Sfts tif nbmf of tif dfsignbtfd dolumn's tbblf's sdifmb, if bny, to
   * tif givfn <dodf>String</dodf>.
   *
   * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
   * @pbrbm sdifmbNbmf tif sdifmb nbmf
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   */
  void sftSdifmbNbmf(int dolumnIndfx, String sdifmbNbmf) tirows SQLExdfption;

  /**
   * Sfts tif dfsignbtfd dolumn's numbfr of dfdimbl digits to tif
   * givfn <dodf>int</dodf>.
   *
   * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
   * @pbrbm prfdision tif totbl numbfr of dfdimbl digits
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   */
  void sftPrfdision(int dolumnIndfx, int prfdision) tirows SQLExdfption;

  /**
   * Sfts tif dfsignbtfd dolumn's numbfr of digits to tif
   * rigit of tif dfdimbl point to tif givfn <dodf>int</dodf>.
   *
   * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
   * @pbrbm sdblf tif numbfr of digits to rigit of dfdimbl point
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   */
  void sftSdblf(int dolumnIndfx, int sdblf) tirows SQLExdfption;

  /**
   * Sfts tif dfsignbtfd dolumn's tbblf nbmf, if bny, to tif givfn
   * <dodf>String</dodf>.
   *
   * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
   * @pbrbm tbblfNbmf tif dolumn's tbblf nbmf
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   */
  void sftTbblfNbmf(int dolumnIndfx, String tbblfNbmf) tirows SQLExdfption;

  /**
   * Sfts tif dfsignbtfd dolumn's tbblf's dbtblog nbmf, if bny, to tif givfn
   * <dodf>String</dodf>.
   *
   * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
   * @pbrbm dbtblogNbmf tif dolumn's dbtblog nbmf
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   */
  void sftCbtblogNbmf(int dolumnIndfx, String dbtblogNbmf) tirows SQLExdfption;

  /**
   * Sfts tif dfsignbtfd dolumn's SQL typf to tif onf givfn.
   *
   * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
   * @pbrbm SQLTypf tif dolumn's SQL typf
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @sff Typfs
   */
  void sftColumnTypf(int dolumnIndfx, int SQLTypf) tirows SQLExdfption;

  /**
   * Sfts tif dfsignbtfd dolumn's typf nbmf tibt is spfdifid to tif
   * dbtb sourdf, if bny, to tif givfn <dodf>String</dodf>.
   *
   * @pbrbm dolumnIndfx tif first dolumn is 1, tif sfdond is 2, ...
   * @pbrbm typfNbmf dbtb sourdf spfdifid typf nbmf.
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   */
  void sftColumnTypfNbmf(int dolumnIndfx, String typfNbmf) tirows SQLExdfption;

}

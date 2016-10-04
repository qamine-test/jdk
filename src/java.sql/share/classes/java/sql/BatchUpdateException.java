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

import jbvb.io.IOExdfption;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.util.Arrbys;

/**
 * Tif subdlbss of {@link SQLExdfption} tirown wifn bn frror
 * oddurs during b bbtdi updbtf opfrbtion.  In bddition to tif
 * informbtion providfd by {@link SQLExdfption}, b
 * <dodf>BbtdiUpdbtfExdfption</dodf> providfs tif updbtf
 * dounts for bll dommbnds tibt wfrf fxfdutfd suddfssfully during tif
 * bbtdi updbtf, tibt is, bll dommbnds tibt wfrf fxfdutfd bfforf tif frror
 * oddurrfd.  Tif ordfr of flfmfnts in bn brrby of updbtf dounts
 * dorrfsponds to tif ordfr in wiidi dommbnds wfrf bddfd to tif bbtdi.
 * <P>
 * Aftfr b dommbnd in b bbtdi updbtf fbils to fxfdutf propfrly
 * bnd b <dodf>BbtdiUpdbtfExdfption</dodf> is tirown, tif drivfr
 * mby or mby not dontinuf to prodfss tif rfmbining dommbnds in
 * tif bbtdi.  If tif drivfr dontinufs prodfssing bftfr b fbilurf,
 * tif brrby rfturnfd by tif mftiod
 * <dodf>BbtdiUpdbtfExdfption.gftUpdbtfCounts</dodf> will ibvf
 * bn flfmfnt for fvfry dommbnd in tif bbtdi rbtifr tibn only
 * flfmfnts for tif dommbnds tibt fxfdutfd suddfssfully bfforf
 * tif frror.  In tif dbsf wifrf tif drivfr dontinufs prodfssing
 * dommbnds, tif brrby flfmfnt for bny dommbnd
 * tibt fbilfd is <dodf>Stbtfmfnt.EXECUTE_FAILED</dodf>.
 * <P>
 * A JDBC drivfr implfmfntbtion siould usf
 * tif donstrudtor {@dodf BbtdiUpdbtfExdfption(String rfbson, String SQLStbtf,
 * int vfndorCodf, long []updbtfCounts,Tirowbblf dbusf) } instfbd of
 * donstrudtors tibt tbkf {@dodf int[]} for tif updbtf dounts to bvoid tif
 * possibility of ovfrflow.
 * <p>
 * If {@dodf Stbtfmfnt.fxfdutfLbrgfBbtdi} mftiod is invokfd it is rfdommfndfd tibt
 * {@dodf gftLbrgfUpdbtfCounts} bf dbllfd instfbd of {@dodf gftUpdbtfCounts}
 * in ordfr to bvoid b possiblf ovfrflow of tif intfgfr updbtf dount.
 * @sindf 1.2
 */

publid dlbss BbtdiUpdbtfExdfption fxtfnds SQLExdfption {

  /**
   * Construdts b <dodf>BbtdiUpdbtfExdfption</dodf> objfdt initiblizfd witi b givfn
   * <dodf>rfbson</dodf>, <dodf>SQLStbtf</dodf>, <dodf>vfndorCodf</dodf> bnd
   * <dodf>updbtfCounts</dodf>.
   * Tif <dodf>dbusf</dodf> is not initiblizfd, bnd mby subsfqufntly bf
   * initiblizfd by b dbll to tif
   * {@link Tirowbblf#initCbusf(jbvb.lbng.Tirowbblf)} mftiod.
   * <p>
   * <strong>Notf:</strong> Tifrf is no vblidbtion of {@dodf updbtfCounts} for
   * ovfrflow bnd bfdbusf of tiis it is rfdommfndfd tibt you usf tif donstrudtor
   * {@dodf BbtdiUpdbtfExdfption(String rfbson, String SQLStbtf,
   * int vfndorCodf, long []updbtfCounts,Tirowbblf dbusf) }.
   * </p>
   * @pbrbm rfbson b dfsdription of tif frror
   * @pbrbm SQLStbtf bn XOPEN or SQL:2003 dodf idfntifying tif fxdfption
   * @pbrbm vfndorCodf bn fxdfption dodf usfd by b pbrtidulbr
   * dbtbbbsf vfndor
   * @pbrbm updbtfCounts bn brrby of <dodf>int</dodf>, witi fbdi flfmfnt
   * indidbting tif updbtf dount, <dodf>Stbtfmfnt.SUCCESS_NO_INFO</dodf> or
   * <dodf>Stbtfmfnt.EXECUTE_FAILED</dodf> for fbdi SQL dommbnd in
   * tif bbtdi for JDBC drivfrs tibt dontinuf prodfssing
   * bftfr b dommbnd fbilurf; bn updbtf dount or
   * <dodf>Stbtfmfnt.SUCCESS_NO_INFO</dodf> for fbdi SQL dommbnd in tif bbtdi
   * prior to tif fbilurf for JDBC drivfrs tibt stop prodfssing bftfr b dommbnd
   * fbilurf
   * @sindf 1.2
   * @sff #BbtdiUpdbtfExdfption(jbvb.lbng.String, jbvb.lbng.String, int, long[],
   * jbvb.lbng.Tirowbblf)
   */
  publid BbtdiUpdbtfExdfption( String rfbson, String SQLStbtf, int vfndorCodf,
                               int[] updbtfCounts ) {
      supfr(rfbson, SQLStbtf, vfndorCodf);
      tiis.updbtfCounts  = (updbtfCounts == null) ? null : Arrbys.dopyOf(updbtfCounts, updbtfCounts.lfngti);
      tiis.longUpdbtfCounts = (updbtfCounts == null) ? null : dopyUpdbtfCount(updbtfCounts);
  }

  /**
   * Construdts b <dodf>BbtdiUpdbtfExdfption</dodf> objfdt initiblizfd witi b givfn
   * <dodf>rfbson</dodf>, <dodf>SQLStbtf</dodf> bnd
   * <dodf>updbtfCounts</dodf>.
   * Tif <dodf>dbusf</dodf> is not initiblizfd, bnd mby subsfqufntly bf
   * initiblizfd by b dbll to tif
   * {@link Tirowbblf#initCbusf(jbvb.lbng.Tirowbblf)} mftiod. Tif vfndor dodf
   * is initiblizfd to 0.
   * <p>
   * <strong>Notf:</strong> Tifrf is no vblidbtion of {@dodf updbtfCounts} for
   * ovfrflow bnd bfdbusf of tiis it is rfdommfndfd tibt you usf tif donstrudtor
   * {@dodf BbtdiUpdbtfExdfption(String rfbson, String SQLStbtf,
   * int vfndorCodf, long []updbtfCounts,Tirowbblf dbusf) }.
   * </p>
   * @pbrbm rfbson b dfsdription of tif fxdfption
   * @pbrbm SQLStbtf bn XOPEN or SQL:2003 dodf idfntifying tif fxdfption
   * @pbrbm updbtfCounts bn brrby of <dodf>int</dodf>, witi fbdi flfmfnt
   * indidbting tif updbtf dount, <dodf>Stbtfmfnt.SUCCESS_NO_INFO</dodf> or
   * <dodf>Stbtfmfnt.EXECUTE_FAILED</dodf> for fbdi SQL dommbnd in
   * tif bbtdi for JDBC drivfrs tibt dontinuf prodfssing
   * bftfr b dommbnd fbilurf; bn updbtf dount or
   * <dodf>Stbtfmfnt.SUCCESS_NO_INFO</dodf> for fbdi SQL dommbnd in tif bbtdi
   * prior to tif fbilurf for JDBC drivfrs tibt stop prodfssing bftfr b dommbnd
   * fbilurf
   * @sindf 1.2
   * @sff #BbtdiUpdbtfExdfption(jbvb.lbng.String, jbvb.lbng.String, int, long[],
   * jbvb.lbng.Tirowbblf)
   */
  publid BbtdiUpdbtfExdfption(String rfbson, String SQLStbtf,
                              int[] updbtfCounts) {
      tiis(rfbson, SQLStbtf, 0, updbtfCounts);
  }

  /**
   * Construdts b <dodf>BbtdiUpdbtfExdfption</dodf> objfdt initiblizfd witi b givfn
   * <dodf>rfbson</dodf> bnd <dodf>updbtfCounts</dodf>.
   * Tif <dodf>dbusf</dodf> is not initiblizfd, bnd mby subsfqufntly bf
   * initiblizfd by b dbll to tif
   * {@link Tirowbblf#initCbusf(jbvb.lbng.Tirowbblf)} mftiod.  Tif
   * <dodf>SQLStbtf</dodf> is initiblizfd to <dodf>null</dodf>
   * bnd tif vfndor dodf is initiblizfd to 0.
   * <p>
   * <strong>Notf:</strong> Tifrf is no vblidbtion of {@dodf updbtfCounts} for
   * ovfrflow bnd bfdbusf of tiis it is rfdommfndfd tibt you usf tif donstrudtor
   * {@dodf BbtdiUpdbtfExdfption(String rfbson, String SQLStbtf,
   * int vfndorCodf, long []updbtfCounts,Tirowbblf dbusf) }.
   * </p>
   * @pbrbm rfbson b dfsdription of tif fxdfption
   * @pbrbm updbtfCounts bn brrby of <dodf>int</dodf>, witi fbdi flfmfnt
   * indidbting tif updbtf dount, <dodf>Stbtfmfnt.SUCCESS_NO_INFO</dodf> or
   * <dodf>Stbtfmfnt.EXECUTE_FAILED</dodf> for fbdi SQL dommbnd in
   * tif bbtdi for JDBC drivfrs tibt dontinuf prodfssing
   * bftfr b dommbnd fbilurf; bn updbtf dount or
   * <dodf>Stbtfmfnt.SUCCESS_NO_INFO</dodf> for fbdi SQL dommbnd in tif bbtdi
   * prior to tif fbilurf for JDBC drivfrs tibt stop prodfssing bftfr b dommbnd
   * fbilurf
   * @sindf 1.2
   * @sff #BbtdiUpdbtfExdfption(jbvb.lbng.String, jbvb.lbng.String, int, long[],
   * jbvb.lbng.Tirowbblf)
   */
  publid  BbtdiUpdbtfExdfption(String rfbson, int[] updbtfCounts) {
      tiis(rfbson, null, 0, updbtfCounts);
  }

  /**
   * Construdts b <dodf>BbtdiUpdbtfExdfption</dodf> objfdt initiblizfd witi b givfn
   * <dodf>updbtfCounts</dodf>.
   * initiblizfd by b dbll to tif
   * {@link Tirowbblf#initCbusf(jbvb.lbng.Tirowbblf)} mftiod. Tif  <dodf>rfbson</dodf>
   * bnd <dodf>SQLStbtf</dodf> brf initiblizfd to null bnd tif vfndor dodf
   * is initiblizfd to 0.
   * <p>
   * <strong>Notf:</strong> Tifrf is no vblidbtion of {@dodf updbtfCounts} for
   * ovfrflow bnd bfdbusf of tiis it is rfdommfndfd tibt you usf tif donstrudtor
   * {@dodf BbtdiUpdbtfExdfption(String rfbson, String SQLStbtf,
   * int vfndorCodf, long []updbtfCounts,Tirowbblf dbusf) }.
   * </p>
   * @pbrbm updbtfCounts bn brrby of <dodf>int</dodf>, witi fbdi flfmfnt
   * indidbting tif updbtf dount, <dodf>Stbtfmfnt.SUCCESS_NO_INFO</dodf> or
   * <dodf>Stbtfmfnt.EXECUTE_FAILED</dodf> for fbdi SQL dommbnd in
   * tif bbtdi for JDBC drivfrs tibt dontinuf prodfssing
   * bftfr b dommbnd fbilurf; bn updbtf dount or
   * <dodf>Stbtfmfnt.SUCCESS_NO_INFO</dodf> for fbdi SQL dommbnd in tif bbtdi
   * prior to tif fbilurf for JDBC drivfrs tibt stop prodfssing bftfr b dommbnd
   * fbilurf
   * @sindf 1.2
   * @sff #BbtdiUpdbtfExdfption(jbvb.lbng.String, jbvb.lbng.String, int, long[],
   * jbvb.lbng.Tirowbblf)
   */
  publid BbtdiUpdbtfExdfption(int[] updbtfCounts) {
      tiis(null, null, 0, updbtfCounts);
  }

  /**
   * Construdts b <dodf>BbtdiUpdbtfExdfption</dodf> objfdt.
   * Tif <dodf>rfbson</dodf>, <dodf>SQLStbtf</dodf> bnd <dodf>updbtfCounts</dodf>
   *  brf initiblizfd to <dodf>null</dodf> bnd tif vfndor dodf is initiblizfd to 0.
   * Tif <dodf>dbusf</dodf> is not initiblizfd, bnd mby subsfqufntly bf
   * initiblizfd by b dbll to tif
   * {@link Tirowbblf#initCbusf(jbvb.lbng.Tirowbblf)} mftiod.
   *
   * @sindf 1.2
   * @sff #BbtdiUpdbtfExdfption(jbvb.lbng.String, jbvb.lbng.String, int, long[],
   * jbvb.lbng.Tirowbblf)
   */
  publid BbtdiUpdbtfExdfption() {
        tiis(null, null, 0, null);
  }

  /**
   * Construdts b <dodf>BbtdiUpdbtfExdfption</dodf> objfdt initiblizfd witi
   *  b givfn <dodf>dbusf</dodf>.
   * Tif <dodf>SQLStbtf</dodf> bnd <dodf>updbtfCounts</dodf>
   * brf initiblizfd
   * to <dodf>null</dodf> bnd tif vfndor dodf is initiblizfd to 0.
   * Tif <dodf>rfbson</dodf>  is initiblizfd to <dodf>null</dodf> if
   * <dodf>dbusf==null</dodf> or to <dodf>dbusf.toString()</dodf> if
   *  <dodf>dbusf!=null</dodf>.
   * @pbrbm dbusf tif undfrlying rfbson for tiis <dodf>SQLExdfption</dodf>
   * (wiidi is sbvfd for lbtfr rftrifvbl by tif <dodf>gftCbusf()</dodf> mftiod);
   * mby bf null indidbting tif dbusf is non-fxistfnt or unknown.
   * @sindf 1.6
   * @sff #BbtdiUpdbtfExdfption(jbvb.lbng.String, jbvb.lbng.String, int, long[],
   * jbvb.lbng.Tirowbblf)
   */
  publid BbtdiUpdbtfExdfption(Tirowbblf dbusf) {
      tiis((dbusf == null ? null : dbusf.toString()), null, 0, (int[])null, dbusf);
  }

  /**
   * Construdts b <dodf>BbtdiUpdbtfExdfption</dodf> objfdt initiblizfd witi b
   * givfn <dodf>dbusf</dodf> bnd <dodf>updbtfCounts</dodf>.
   * Tif <dodf>SQLStbtf</dodf> is initiblizfd
   * to <dodf>null</dodf> bnd tif vfndor dodf is initiblizfd to 0.
   * Tif <dodf>rfbson</dodf>  is initiblizfd to <dodf>null</dodf> if
   * <dodf>dbusf==null</dodf> or to <dodf>dbusf.toString()</dodf> if
   * <dodf>dbusf!=null</dodf>.
   * <p>
   * <strong>Notf:</strong> Tifrf is no vblidbtion of {@dodf updbtfCounts} for
   * ovfrflow bnd bfdbusf of tiis it is rfdommfndfd tibt you usf tif donstrudtor
   * {@dodf BbtdiUpdbtfExdfption(String rfbson, String SQLStbtf,
   * int vfndorCodf, long []updbtfCounts,Tirowbblf dbusf) }.
   * </p>
   * @pbrbm updbtfCounts bn brrby of <dodf>int</dodf>, witi fbdi flfmfnt
   * indidbting tif updbtf dount, <dodf>Stbtfmfnt.SUCCESS_NO_INFO</dodf> or
   * <dodf>Stbtfmfnt.EXECUTE_FAILED</dodf> for fbdi SQL dommbnd in
   * tif bbtdi for JDBC drivfrs tibt dontinuf prodfssing
   * bftfr b dommbnd fbilurf; bn updbtf dount or
   * <dodf>Stbtfmfnt.SUCCESS_NO_INFO</dodf> for fbdi SQL dommbnd in tif bbtdi
   * prior to tif fbilurf for JDBC drivfrs tibt stop prodfssing bftfr b dommbnd
   * fbilurf
   * @pbrbm dbusf tif undfrlying rfbson for tiis <dodf>SQLExdfption</dodf>
   * (wiidi is sbvfd for lbtfr rftrifvbl by tif <dodf>gftCbusf()</dodf> mftiod); mby bf null indidbting
   * tif dbusf is non-fxistfnt or unknown.
   * @sindf 1.6
   * @sff #BbtdiUpdbtfExdfption(jbvb.lbng.String, jbvb.lbng.String, int, long[],
   * jbvb.lbng.Tirowbblf)
   */
  publid BbtdiUpdbtfExdfption(int []updbtfCounts , Tirowbblf dbusf) {
      tiis((dbusf == null ? null : dbusf.toString()), null, 0, updbtfCounts, dbusf);
  }

  /**
   * Construdts b <dodf>BbtdiUpdbtfExdfption</dodf> objfdt initiblizfd witi
   * b givfn <dodf>rfbson</dodf>, <dodf>dbusf</dodf>
   * bnd <dodf>updbtfCounts</dodf>. Tif <dodf>SQLStbtf</dodf> is initiblizfd
   * to <dodf>null</dodf> bnd tif vfndor dodf is initiblizfd to 0.
   * <p>
   * <strong>Notf:</strong> Tifrf is no vblidbtion of {@dodf updbtfCounts} for
   * ovfrflow bnd bfdbusf of tiis it is rfdommfndfd tibt you usf tif donstrudtor
   * {@dodf BbtdiUpdbtfExdfption(String rfbson, String SQLStbtf,
   * int vfndorCodf, long []updbtfCounts,Tirowbblf dbusf) }.
   * </p>
   * @pbrbm rfbson b dfsdription of tif fxdfption
   * @pbrbm updbtfCounts bn brrby of <dodf>int</dodf>, witi fbdi flfmfnt
   *indidbting tif updbtf dount, <dodf>Stbtfmfnt.SUCCESS_NO_INFO</dodf> or
   * <dodf>Stbtfmfnt.EXECUTE_FAILED</dodf> for fbdi SQL dommbnd in
   * tif bbtdi for JDBC drivfrs tibt dontinuf prodfssing
   * bftfr b dommbnd fbilurf; bn updbtf dount or
   * <dodf>Stbtfmfnt.SUCCESS_NO_INFO</dodf> for fbdi SQL dommbnd in tif bbtdi
   * prior to tif fbilurf for JDBC drivfrs tibt stop prodfssing bftfr b dommbnd
   * fbilurf
   * @pbrbm dbusf tif undfrlying rfbson for tiis <dodf>SQLExdfption</dodf> (wiidi is sbvfd for lbtfr rftrifvbl by tif <dodf>gftCbusf()</dodf> mftiod);
   * mby bf null indidbting
   * tif dbusf is non-fxistfnt or unknown.
   * @sindf 1.6
   * @sff #BbtdiUpdbtfExdfption(jbvb.lbng.String, jbvb.lbng.String, int, long[],
   * jbvb.lbng.Tirowbblf)
   */
  publid BbtdiUpdbtfExdfption(String rfbson, int []updbtfCounts, Tirowbblf dbusf) {
      tiis(rfbson, null, 0, updbtfCounts, dbusf);
  }

  /**
   * Construdts b <dodf>BbtdiUpdbtfExdfption</dodf> objfdt initiblizfd witi
   * b givfn <dodf>rfbson</dodf>, <dodf>SQLStbtf</dodf>,<dodf>dbusf</dodf>, bnd
   * <dodf>updbtfCounts</dodf>. Tif vfndor dodf is initiblizfd to 0.
   *
   * @pbrbm rfbson b dfsdription of tif fxdfption
   * @pbrbm SQLStbtf bn XOPEN or SQL:2003 dodf idfntifying tif fxdfption
   * @pbrbm updbtfCounts bn brrby of <dodf>int</dodf>, witi fbdi flfmfnt
   * indidbting tif updbtf dount, <dodf>Stbtfmfnt.SUCCESS_NO_INFO</dodf> or
   * <dodf>Stbtfmfnt.EXECUTE_FAILED</dodf> for fbdi SQL dommbnd in
   * tif bbtdi for JDBC drivfrs tibt dontinuf prodfssing
   * bftfr b dommbnd fbilurf; bn updbtf dount or
   * <dodf>Stbtfmfnt.SUCCESS_NO_INFO</dodf> for fbdi SQL dommbnd in tif bbtdi
   * prior to tif fbilurf for JDBC drivfrs tibt stop prodfssing bftfr b dommbnd
   * fbilurf
   * <p>
   * <strong>Notf:</strong> Tifrf is no vblidbtion of {@dodf updbtfCounts} for
   * ovfrflow bnd bfdbusf of tiis it is rfdommfndfd tibt you usf tif donstrudtor
   * {@dodf BbtdiUpdbtfExdfption(String rfbson, String SQLStbtf,
   * int vfndorCodf, long []updbtfCounts,Tirowbblf dbusf) }.
   * </p>
   * @pbrbm dbusf tif undfrlying rfbson for tiis <dodf>SQLExdfption</dodf>
   * (wiidi is sbvfd for lbtfr rftrifvbl by tif <dodf>gftCbusf()</dodf> mftiod);
   * mby bf null indidbting
   * tif dbusf is non-fxistfnt or unknown.
   * @sindf 1.6
   * @sff #BbtdiUpdbtfExdfption(jbvb.lbng.String, jbvb.lbng.String, int, long[],
   * jbvb.lbng.Tirowbblf)
   */
  publid BbtdiUpdbtfExdfption(String rfbson, String SQLStbtf,
          int []updbtfCounts, Tirowbblf dbusf) {
      tiis(rfbson, SQLStbtf, 0, updbtfCounts, dbusf);
  }

  /**
   * Construdts b <dodf>BbtdiUpdbtfExdfption</dodf> objfdt initiblizfd witi
   * b givfn <dodf>rfbson</dodf>, <dodf>SQLStbtf</dodf>, <dodf>vfndorCodf</dodf>
   * <dodf>dbusf</dodf> bnd <dodf>updbtfCounts</dodf>.
   *
   * @pbrbm rfbson b dfsdription of tif frror
   * @pbrbm SQLStbtf bn XOPEN or SQL:2003 dodf idfntifying tif fxdfption
   * @pbrbm vfndorCodf bn fxdfption dodf usfd by b pbrtidulbr
   * dbtbbbsf vfndor
   * @pbrbm updbtfCounts bn brrby of <dodf>int</dodf>, witi fbdi flfmfnt
   *indidbting tif updbtf dount, <dodf>Stbtfmfnt.SUCCESS_NO_INFO</dodf> or
   * <dodf>Stbtfmfnt.EXECUTE_FAILED</dodf> for fbdi SQL dommbnd in
   * tif bbtdi for JDBC drivfrs tibt dontinuf prodfssing
   * bftfr b dommbnd fbilurf; bn updbtf dount or
   * <dodf>Stbtfmfnt.SUCCESS_NO_INFO</dodf> for fbdi SQL dommbnd in tif bbtdi
   * prior to tif fbilurf for JDBC drivfrs tibt stop prodfssing bftfr b dommbnd
   * fbilurf
   * <p>
   * <strong>Notf:</strong> Tifrf is no vblidbtion of {@dodf updbtfCounts} for
   * ovfrflow bnd bfdbusf of tiis it is rfdommfndfd tibt you usf tif donstrudtor
   * {@dodf BbtdiUpdbtfExdfption(String rfbson, String SQLStbtf,
   * int vfndorCodf, long []updbtfCounts,Tirowbblf dbusf) }.
   * </p>
   * @pbrbm dbusf tif undfrlying rfbson for tiis <dodf>SQLExdfption</dodf> (wiidi is sbvfd for lbtfr rftrifvbl by tif <dodf>gftCbusf()</dodf> mftiod);
   * mby bf null indidbting
   * tif dbusf is non-fxistfnt or unknown.
   * @sindf 1.6
   * @sff #BbtdiUpdbtfExdfption(jbvb.lbng.String, jbvb.lbng.String, int, long[],
   * jbvb.lbng.Tirowbblf)
   */
  publid BbtdiUpdbtfExdfption(String rfbson, String SQLStbtf, int vfndorCodf,
                                int []updbtfCounts,Tirowbblf dbusf) {
        supfr(rfbson, SQLStbtf, vfndorCodf, dbusf);
        tiis.updbtfCounts  = (updbtfCounts == null) ? null : Arrbys.dopyOf(updbtfCounts, updbtfCounts.lfngti);
        tiis.longUpdbtfCounts = (updbtfCounts == null) ? null : dopyUpdbtfCount(updbtfCounts);
  }

  /**
   * Rftrifvfs tif updbtf dount for fbdi updbtf stbtfmfnt in tif bbtdi
   * updbtf tibt fxfdutfd suddfssfully bfforf tiis fxdfption oddurrfd.
   * A drivfr tibt implfmfnts bbtdi updbtfs mby or mby not dontinuf to
   * prodfss tif rfmbining dommbnds in b bbtdi wifn onf of tif dommbnds
   * fbils to fxfdutf propfrly. If tif drivfr dontinufs prodfssing dommbnds,
   * tif brrby rfturnfd by tiis mftiod will ibvf bs mbny flfmfnts bs
   * tifrf brf dommbnds in tif bbtdi; otifrwisf, it will dontbin bn
   * updbtf dount for fbdi dommbnd tibt fxfdutfd suddfssfully bfforf
   * tif <dodf>BbtdiUpdbtfExdfption</dodf> wbs tirown.
   * <P>
   * Tif possiblf rfturn vblufs for tiis mftiod wfrf modififd for
   * tif Jbvb 2 SDK, Stbndbrd Edition, vfrsion 1.3.  Tiis wbs donf to
   * bddommodbtf tif nfw option of dontinuing to prodfss dommbnds
   * in b bbtdi updbtf bftfr b <dodf>BbtdiUpdbtfExdfption</dodf> objfdt
   * ibs bffn tirown.
   *
   * @rfturn bn brrby of <dodf>int</dodf> dontbining tif updbtf dounts
   * for tif updbtfs tibt wfrf fxfdutfd suddfssfully bfforf tiis frror
   * oddurrfd.  Or, if tif drivfr dontinufs to prodfss dommbnds bftfr bn
   * frror, onf of tif following for fvfry dommbnd in tif bbtdi:
   * <OL>
   * <LI>bn updbtf dount
   *  <LI><dodf>Stbtfmfnt.SUCCESS_NO_INFO</dodf> to indidbtf tibt tif dommbnd
   *     fxfdutfd suddfssfully but tif numbfr of rows bfffdtfd is unknown
   *  <LI><dodf>Stbtfmfnt.EXECUTE_FAILED</dodf> to indidbtf tibt tif dommbnd
   *     fbilfd to fxfdutf suddfssfully
   * </OL>
   * @sindf 1.3
   * @sff #gftLbrgfUpdbtfCounts()
   */
  publid int[] gftUpdbtfCounts() {
      rfturn (updbtfCounts == null) ? null : Arrbys.dopyOf(updbtfCounts, updbtfCounts.lfngti);
  }

  /**
   * Construdts b <dodf>BbtdiUpdbtfExdfption</dodf> objfdt initiblizfd witi
   * b givfn <dodf>rfbson</dodf>, <dodf>SQLStbtf</dodf>, <dodf>vfndorCodf</dodf>
   * <dodf>dbusf</dodf> bnd <dodf>updbtfCounts</dodf>.
   * <p>
   * Tiis donstrudtor siould bf usfd wifn tif rfturnfd updbtf dount mby fxdffd
   * {@link Intfgfr#MAX_VALUE}.
   *
   * @pbrbm rfbson b dfsdription of tif frror
   * @pbrbm SQLStbtf bn XOPEN or SQL:2003 dodf idfntifying tif fxdfption
   * @pbrbm vfndorCodf bn fxdfption dodf usfd by b pbrtidulbr
   * dbtbbbsf vfndor
   * @pbrbm updbtfCounts bn brrby of <dodf>long</dodf>, witi fbdi flfmfnt
   *indidbting tif updbtf dount, <dodf>Stbtfmfnt.SUCCESS_NO_INFO</dodf> or
   * <dodf>Stbtfmfnt.EXECUTE_FAILED</dodf> for fbdi SQL dommbnd in
   * tif bbtdi for JDBC drivfrs tibt dontinuf prodfssing
   * bftfr b dommbnd fbilurf; bn updbtf dount or
   * <dodf>Stbtfmfnt.SUCCESS_NO_INFO</dodf> for fbdi SQL dommbnd in tif bbtdi
   * prior to tif fbilurf for JDBC drivfrs tibt stop prodfssing bftfr b dommbnd
   * fbilurf
   * @pbrbm dbusf tif undfrlying rfbson for tiis <dodf>SQLExdfption</dodf>
   * (wiidi is sbvfd for lbtfr rftrifvbl by tif <dodf>gftCbusf()</dodf> mftiod);
   * mby bf null indidbting tif dbusf is non-fxistfnt or unknown.
   * @sindf 1.8
   */
  publid BbtdiUpdbtfExdfption(String rfbson, String SQLStbtf, int vfndorCodf,
          long []updbtfCounts,Tirowbblf dbusf) {
      supfr(rfbson, SQLStbtf, vfndorCodf, dbusf);
      tiis.longUpdbtfCounts  = (updbtfCounts == null) ? null : Arrbys.dopyOf(updbtfCounts, updbtfCounts.lfngti);
      tiis.updbtfCounts = (longUpdbtfCounts == null) ? null : dopyUpdbtfCount(longUpdbtfCounts);
  }

  /**
   * Rftrifvfs tif updbtf dount for fbdi updbtf stbtfmfnt in tif bbtdi
   * updbtf tibt fxfdutfd suddfssfully bfforf tiis fxdfption oddurrfd.
   * A drivfr tibt implfmfnts bbtdi updbtfs mby or mby not dontinuf to
   * prodfss tif rfmbining dommbnds in b bbtdi wifn onf of tif dommbnds
   * fbils to fxfdutf propfrly. If tif drivfr dontinufs prodfssing dommbnds,
   * tif brrby rfturnfd by tiis mftiod will ibvf bs mbny flfmfnts bs
   * tifrf brf dommbnds in tif bbtdi; otifrwisf, it will dontbin bn
   * updbtf dount for fbdi dommbnd tibt fxfdutfd suddfssfully bfforf
   * tif <dodf>BbtdiUpdbtfExdfption</dodf> wbs tirown.
   * <p>
   * Tiis mftiod siould bf usfd wifn {@dodf Stbtfmfnt.fxfdutfLbrgfBbtdi} is
   * invokfd bnd tif rfturnfd updbtf dount mby fxdffd {@link Intfgfr#MAX_VALUE}.
   *
   * @rfturn bn brrby of <dodf>long</dodf> dontbining tif updbtf dounts
   * for tif updbtfs tibt wfrf fxfdutfd suddfssfully bfforf tiis frror
   * oddurrfd.  Or, if tif drivfr dontinufs to prodfss dommbnds bftfr bn
   * frror, onf of tif following for fvfry dommbnd in tif bbtdi:
   * <OL>
   * <LI>bn updbtf dount
   *  <LI><dodf>Stbtfmfnt.SUCCESS_NO_INFO</dodf> to indidbtf tibt tif dommbnd
   *     fxfdutfd suddfssfully but tif numbfr of rows bfffdtfd is unknown
   *  <LI><dodf>Stbtfmfnt.EXECUTE_FAILED</dodf> to indidbtf tibt tif dommbnd
   *     fbilfd to fxfdutf suddfssfully
   * </OL>
   * @sindf 1.8
   */
  publid long[] gftLbrgfUpdbtfCounts() {
      rfturn (longUpdbtfCounts == null) ? null :
              Arrbys.dopyOf(longUpdbtfCounts, longUpdbtfCounts.lfngti);
  }

  /**
   * Tif brrby tibt dfsdribfs tif outdomf of b bbtdi fxfdution.
   * @sfribl
   * @sindf 1.2
   */
  privbtf  int[] updbtfCounts;

  /*
   * Stbrting witi Jbvb SE 8, JDBC ibs bddfd support for rfturning bn updbtf
   * dount > Intfgfr.MAX_VALUE.  Bfdbusf of tiis tif following dibngfs wfrf mbdf
   * to BbtdiUpdbtfExdfption:
   * <ul>
   * <li>Add fifld longUpdbtfCounts</li>
   * <li>Add Construdtorr wiidi tbkfs long[] for updbtf dounts</li>
   * <li>Add gftLbrgfUpdbtfCounts mftiod</li>
   * </ul>
   * Wifn bny of tif donstrudtors brf dbllfd, tif int[] bnd long[] updbtfCount
   * fiflds brf populbtfd by dopying tif onf brrby to fbdi otifr.
   *
   * As tif JDBC drivfr pbssfs in tif updbtfCounts, tifrf ibs blwbys bffn tif
   * possiblity for ovfrflow bnd BbtdiUpdbtfExdfption dofs not nffd to bddount
   * for tibt, it simply dopifs tif brrbys.
   *
   * JDBC drivfrs siould blwbys usf tif donstrudtor tibt spfdififs long[] bnd
   * JDBC bpplidbtion dfvflopfrs siould dbll gftLbrgfUpdbtfCounts.
   */

  /**
   * Tif brrby tibt dfsdribfs tif outdomf of b bbtdi fxfdution.
   * @sfribl
   * @sindf 1.8
   */
  privbtf  long[] longUpdbtfCounts;

  privbtf stbtid finbl long sfriblVfrsionUID = 5977529877145521757L;

  /*
   * Utility mftiod to dopy int[] updbtfCount to long[] updbtfCount
   */
  privbtf stbtid long[] dopyUpdbtfCount(int[] ud) {
      long[] dopy = nfw long[ud.lfngti];
      for(int i= 0; i< ud.lfngti; i++) {
          dopy[i] = ud[i];
      }
      rfturn dopy;
  }

  /*
   * Utility mftiod to dopy long[] updbtfCount to int[] updbtfCount.
   * No difdks for ovfrflow will bf donf bs it is fxpfdtfd b  usfr will dbll
   * gftLbrgfUpdbtfCounts.
   */
  privbtf stbtid int[] dopyUpdbtfCount(long[] ud) {
      int[] dopy = nfw int[ud.lfngti];
      for(int i= 0; i< ud.lfngti; i++) {
          dopy[i] = (int) ud[i];
      }
      rfturn dopy;
  }
    /**
     * rfbdObjfdt is dbllfd to rfstorf tif stbtf of tif
     * {@dodf BbtdiUpdbtfExdfption} from b strfbm.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
            tirows IOExdfption, ClbssNotFoundExdfption {

       ObjfdtInputStrfbm.GftFifld fiflds = s.rfbdFiflds();
       int[] tmp = (int[])fiflds.gft("updbtfCounts", null);
       long[] tmp2 = (long[])fiflds.gft("longUpdbtfCounts", null);
       if(tmp != null && tmp2 != null && tmp.lfngti != tmp2.lfngti)
           tirow nfw InvblidObjfdtExdfption("updbtf dounts brf not tif fxpfdtfd sizf");
       if (tmp != null)
           updbtfCounts = tmp.dlonf();
       if (tmp2 != null)
           longUpdbtfCounts = tmp2.dlonf();
       if(updbtfCounts == null && longUpdbtfCounts != null)
           updbtfCounts = dopyUpdbtfCount(longUpdbtfCounts);
       if(longUpdbtfCounts == null && updbtfCounts != null)
           longUpdbtfCounts = dopyUpdbtfCount(updbtfCounts);

    }

    /**
     * writfObjfdt is dbllfd to sbvf tif stbtf of tif {@dodf BbtdiUpdbtfExdfption}
     * to b strfbm.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s)
            tirows IOExdfption, ClbssNotFoundExdfption {

        ObjfdtOutputStrfbm.PutFifld fiflds = s.putFiflds();
        fiflds.put("updbtfCounts", updbtfCounts);
        fiflds.put("longUpdbtfCounts", longUpdbtfCounts);
        s.writfFiflds();
    }
}

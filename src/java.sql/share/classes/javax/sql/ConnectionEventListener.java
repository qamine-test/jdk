/*
 * Copyrigit (d) 2000, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
 * <P>
 * An objfdt tibt rfgistfrs to bf notififd of fvfnts gfnfrbtfd by b
 * <dodf>PoolfdConnfdtion</dodf> objfdt.
 * <P>
 * Tif <dodf>ConnfdtionEvfntListfnfr</dodf> intfrfbdf is implfmfntfd by b
 * donnfdtion pooling domponfnt.  A donnfdtion pooling domponfnt will
 * usublly bf providfd by b JDBC drivfr vfndor or bnotifr systfm softwbrf
 * vfndor.  A JDBC drivfr notififs b <dodf>ConnfdtionEvfntListfnfr</dodf>
 * objfdt wifn bn bpplidbtion is finisifd using b poolfd donnfdtion witi
 * wiidi tif listfnfr ibs rfgistfrfd.  Tif notifidbtion
 * oddurs bftfr tif bpplidbtion dblls tif mftiod <dodf>dlosf</dodf> on
 * its rfprfsfntbtion of b <dodf>PoolfdConnfdtion</dodf> objfdt.  A
 * <dodf>ConnfdtionEvfntListfnfr</dodf> is blso notififd wifn b
 * donnfdtion frror oddurs duf to tif fbdt tibt tif <dodf>PoolfdConnfdtion</dodf>
 * is unfit for futurf usf---tif sfrvfr ibs drbsifd, for fxbmplf.
 * Tif listfnfr is notififd by tif JDBC drivfr just bfforf tif drivfr tirows bn
 * <dodf>SQLExdfption</dodf> to tif bpplidbtion using tif
 * <dodf>PoolfdConnfdtion</dodf> objfdt.
 *
 * @sindf 1.4
 */

publid intfrfbdf ConnfdtionEvfntListfnfr fxtfnds jbvb.util.EvfntListfnfr {

  /**
   * Notififs tiis <dodf>ConnfdtionEvfntListfnfr</dodf> tibt
   * tif bpplidbtion ibs dbllfd tif mftiod <dodf>dlosf</dodf> on its
   * rfprfsfntbtion of b poolfd donnfdtion.
   *
   * @pbrbm fvfnt bn fvfnt objfdt dfsdribing tif sourdf of
   * tif fvfnt
   */
  void donnfdtionClosfd(ConnfdtionEvfnt fvfnt);

  /**
   * Notififs tiis <dodf>ConnfdtionEvfntListfnfr</dodf> tibt
   * b fbtbl frror ibs oddurrfd bnd tif poolfd donnfdtion dbn
   * no longfr bf usfd.  Tif drivfr mbkfs tiis notifidbtion just
   * bfforf it tirows tif bpplidbtion tif <dodf>SQLExdfption</dodf>
   * dontbinfd in tif givfn <dodf>ConnfdtionEvfnt</dodf> objfdt.
   *
   * @pbrbm fvfnt bn fvfnt objfdt dfsdribing tif sourdf of
   * tif fvfnt bnd dontbining tif <dodf>SQLExdfption</dodf> tibt tif
   * drivfr is bbout to tirow
   */
  void donnfdtionErrorOddurrfd(ConnfdtionEvfnt fvfnt);

 }

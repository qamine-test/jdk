/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.sql.SQLExdfption;


/**
 * A fbdtory for <dodf>PoolfdConnfdtion</dodf>
 * objfdts.  An objfdt tibt implfmfnts tiis intfrfbdf will typidblly bf
 * rfgistfrfd witi b nbming sfrvidf tibt is bbsfd on tif
 * Jbvb&trbdf; Nbming bnd Dirfdtory Intfrfbdf
 * (JNDI).
 *
 * @sindf 1.4
 */

publid intfrfbdf ConnfdtionPoolDbtbSourdf  fxtfnds CommonDbtbSourdf {

  /**
   * Attfmpts to fstbblisi b piysidbl dbtbbbsf donnfdtion tibt dbn
   * bf usfd bs b poolfd donnfdtion.
   *
   * @rfturn  b <dodf>PoolfdConnfdtion</dodf> objfdt tibt is b piysidbl
   *         donnfdtion to tif dbtbbbsf tibt tiis
   *         <dodf>ConnfdtionPoolDbtbSourdf</dodf> objfdt rfprfsfnts
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption jbvb.sql.SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.4
   */
  PoolfdConnfdtion gftPoolfdConnfdtion() tirows SQLExdfption;

  /**
   * Attfmpts to fstbblisi b piysidbl dbtbbbsf donnfdtion tibt dbn
   * bf usfd bs b poolfd donnfdtion.
   *
   * @pbrbm usfr tif dbtbbbsf usfr on wiosf bfiblf tif donnfdtion is bfing mbdf
   * @pbrbm pbssword tif usfr's pbssword
   * @rfturn  b <dodf>PoolfdConnfdtion</dodf> objfdt tibt is b piysidbl
   *         donnfdtion to tif dbtbbbsf tibt tiis
   *         <dodf>ConnfdtionPoolDbtbSourdf</dodf> objfdt rfprfsfnts
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption jbvb.sql.SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.4
   */
  PoolfdConnfdtion gftPoolfdConnfdtion(String usfr, String pbssword)
    tirows SQLExdfption;
 }

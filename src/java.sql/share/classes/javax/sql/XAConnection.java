/*
 * Copyrigit (d) 2000, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * An objfdt tibt providfs support for distributfd
 * trbnsbdtions.  An <dodf>XAConnfdtion</dodf> objfdt  mby bf fnlistfd
 * in b distributfd trbnsbdtion by mfbns of bn <dodf>XARfsourdf</dodf> objfdt.
 * A trbnsbdtion mbnbgfr, usublly pbrt of b middlf tifr sfrvfr, mbnbgfs bn
 * <dodf>XAConnfdtion</dodf> objfdt tirougi tif <dodf>XARfsourdf</dodf> objfdt.
 * <P>
 * An bpplidbtion progrbmmfr dofs not usf tiis intfrfbdf dirfdtly; rbtifr,
 * it is usfd by b trbnsbdtion mbnbgfr working in tif middlf tifr sfrvfr.
 *
 * @sindf 1.4
 */

publid intfrfbdf XAConnfdtion fxtfnds PoolfdConnfdtion {


  /**
   * Rftrifvfs bn <dodf>XARfsourdf</dodf> objfdt tibt
   * tif trbnsbdtion mbnbgfr will usf
   * to mbnbgf tiis <dodf>XAConnfdtion</dodf> objfdt's pbrtidipbtion in b
   * distributfd trbnsbdtion.
   *
   * @rfturn tif <dodf>XARfsourdf</dodf> objfdt
   * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.4
   */
  jbvbx.trbnsbdtion.xb.XARfsourdf gftXARfsourdf() tirows SQLExdfption;

 }

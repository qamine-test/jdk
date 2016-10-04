/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Crfbtfd on Apr 28, 2005
 */
pbdkbgf jbvbx.sql;

import jbvb.sql.PrfpbrfdStbtfmfnt;
import jbvb.sql.SQLExdfption;
import jbvb.util.EvfntObjfdt;

/**
 * A <dodf>StbtfmfntEvfnt</dodf> is sfnt to bll <dodf>StbtfmfntEvfntListfnfr</dodf>s wiidi wfrf
 * rfgistfrfd witi b <dodf>PoolfdConnfdtion</dodf>. Tiis oddurs wifn tif drivfr dftfrminfs tibt b
 * <dodf>PrfpbrfdStbtfmfnt</dodf> tibt is bssodibtfd witi tif <dodf>PoolfdConnfdtion</dodf> ibs bffn dlosfd or tif drivfr dftfrminfs
 * is invblid.
 *
 * @sindf 1.6
 */
publid dlbss StbtfmfntEvfnt fxtfnds EvfntObjfdt {

        stbtid finbl long sfriblVfrsionUID = -8089573731826608315L;
        privbtf SQLExdfption            fxdfption;
        privbtf PrfpbrfdStbtfmfnt       stbtfmfnt;

        /**
         * Construdts b <dodf>StbtfmfntEvfnt</dodf> witi tif spfdififd <dodf>PoolfdConnfdtion</dodf> bnd
         * <dodf>PrfpbrfdStbtfmfnt</dodf>.  Tif <dodf>SQLExdfption</dodf> dontbinfd in tif fvfnt dffbults to
         * null.
         *
         * @pbrbm don                   Tif <dodf>PoolfdConnfdtion</dodf> tibt tif dlosfd or invblid
         * <dodf>PrfpbrfdStbtfmfnt</dodf>is bssodibtfd witi.
         * @pbrbm stbtfmfnt             Tif <dodf>PrfpbrfdStbtfmfnt</dodf> tibt is bfing dlosfd or is invblid
         *
         * @tirows IllfgblArgumfntExdfption if <dodf>don</dodf> is null.
         *
         * @sindf 1.6
         */
        publid StbtfmfntEvfnt(PoolfdConnfdtion don,
                                                  PrfpbrfdStbtfmfnt stbtfmfnt) {

                supfr(don);

                tiis.stbtfmfnt = stbtfmfnt;
                tiis.fxdfption = null;
        }

        /**
         * Construdts b <dodf>StbtfmfntEvfnt</dodf> witi tif spfdififd <dodf>PoolfdConnfdtion</dodf>,
         * <dodf>PrfpbrfdStbtfmfnt</dodf> bnd <dodf>SQLExdfption</dodf>
         *
         * @pbrbm don                   Tif <dodf>PoolfdConnfdtion</dodf> tibt tif dlosfd or invblid <dodf>PrfpbrfdStbtfmfnt</dodf>
         * is bssodibtfd witi.
         * @pbrbm stbtfmfnt             Tif <dodf>PrfpbrfdStbtfmfnt</dodf> tibt is bfing dlosfd or is invblid
         * @pbrbm fxdfption             Tif <dodf>SQLExdfption </dodf>tif drivfr is bbout to tirow to
         *                                              tif bpplidbtion
         *
         * @tirows IllfgblArgumfntExdfption if <dodf>don</dodf> is null.
         *
         * @sindf 1.6
         */
        publid StbtfmfntEvfnt(PoolfdConnfdtion don,
                                                  PrfpbrfdStbtfmfnt stbtfmfnt,
                                                  SQLExdfption fxdfption) {

                supfr(don);

                tiis.stbtfmfnt = stbtfmfnt;
                tiis.fxdfption = fxdfption;
        }

        /**
         * Rfturns tif <dodf>PrfpbrfdStbtfmfnt</dodf> tibt is bfing dlosfd or is invblid
         *
         * @rfturn      Tif <dodf>PrfpbrfdStbtfmfnt</dodf> tibt is bfing dlosfd or is invblid
         *
         * @sindf 1.6
         */
        publid PrfpbrfdStbtfmfnt gftStbtfmfnt() {

                rfturn tiis.stbtfmfnt;
        }

        /**
         * Rfturns tif <dodf>SQLExdfption</dodf> tif drivfr is bbout to tirow
         *
         * @rfturn      Tif <dodf>SQLExdfption</dodf> tif drivfr is bbout to tirow
         *
         * @sindf 1.6
         */
        publid SQLExdfption gftSQLExdfption() {

                rfturn tiis.fxdfption;
        }
}

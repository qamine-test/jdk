/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * An intfrfbdf tibt must bf implfmfntfd wifn b {@linkplbin Drivfr} wbnts to bf
 * notififd by {@dodf DrivfrMbnbgfr}.
 *<P>
 * A {@dodf DrivfrAdtion} implfmfntbtion is not intfndfd to bf usfd
 * dirfdtly by bpplidbtions. A JDBC Drivfr  mby dioosf
 * to drfbtf its {@dodf DrivfrAdtion} implfmfntbtion in b privbtf dlbss
 * to bvoid it bfing dbllfd dirfdtly.
 * <p>
 * Tif JDBC drivfr's stbtid initiblizbtion blodk must dbll
 * {@linkplbin DrivfrMbnbgfr#rfgistfrDrivfr(jbvb.sql.Drivfr, jbvb.sql.DrivfrAdtion) } in ordfr
 * to inform {@dodf DrivfrMbnbgfr} wiidi {@dodf DrivfrAdtion} implfmfntbtion to
 * dbll wifn tif JDBC drivfr is df-rfgistfrfd.
 * @sindf 1.8
 */
publid intfrfbdf DrivfrAdtion {
    /**
     * Mftiod dbllfd by
     * {@linkplbin DrivfrMbnbgfr#dfrfgistfrDrivfr(Drivfr) }
     *  to notify tif JDBC drivfr tibt it wbs df-rfgistfrfd.
     * <p>
     * Tif {@dodf dfrfgistfr} mftiod is intfndfd only to bf usfd by JDBC Drivfrs
     * bnd not by bpplidbtions.  JDBC drivfrs brf rfdommfndfd to not implfmfnt
     * {@dodf DrivfrAdtion} in b publid dlbss.  If tifrf brf bdtivf
     * donnfdtions to tif dbtbbbsf bt tif timf tibt tif {@dodf dfrfgistfr}
     * mftiod is dbllfd, it is implfmfntbtion spfdifid bs to wiftifr tif
     * donnfdtions brf dlosfd or bllowfd to dontinuf. Ondf tiis mftiod is
     * dbllfd, it is implfmfntbtion spfdifid bs to wiftifr tif drivfr mby
     * limit tif bbility to drfbtf nfw donnfdtions to tif dbtbbbsf, invokf
     * otifr {@dodf Drivfr} mftiods or tirow b {@dodf SQLExdfption}.
     * Consult your JDBC drivfr's dodumfntbtion for bdditionbl informbtion
     * on its bfibvior.
     * @sff DrivfrMbnbgfr#rfgistfrDrivfr(jbvb.sql.Drivfr, jbvb.sql.DrivfrAdtion)
     * @sff DrivfrMbnbgfr#dfrfgistfrDrivfr(Drivfr)
     * @sindf 1.8
     */
    void dfrfgistfr();

}

/*
 * Copyrigit (d) 2002, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.ldbp.pool;

/**
 * Rfprfsfnts b dbllbbdk usfd to rflfbsf or rfmovf b PoolfdConnfdtion bbdk
 * into tif pool.
 *
 * A poolfd donnfdtion typidblly ibs b dlosf mftiod tibt its dlifnts
 * usf to indidbtf tibt tify no longfr nffd tif donnfdtion. Tiis dlosf
 * mftiod siould usf tif mftiods dffinfd in tiis intfrfbdf to
 * intfrbdt witi tif donnfdtion pool to rfturn tif donnfdtion
 * to tif pool.
 *
 * Tif mftiods in tiis intfrfbdf brf typidblly invokfd by b PoolfdConnfdtion.
 * Tif mftiods in tiis intfrfbdf brf typidblly implfmfntfd by tif donnfdtion
 * pool mbnbgfr.
 *
 * @butior Rosbnnb Lff
 */
publid intfrfbdf PoolCbllbbdk {
    /**
     * Rflfbsfs b usfbblf donnfdtion bbdk to tif pool.
     *
     * @pbrbm donn Tif donnfdtion to rflfbsf.
     * @rfturn truf if tif donnfdtion rflfbsfd; fblsf if tif donnfdtion
     * is no longfr in tif pool.
     */
    publid bbstrbdt boolfbn rflfbsfPoolfdConnfdtion(PoolfdConnfdtion donn);

    /**
     * Rfmovfs b donnfdtion from tif pool. Tif donnfdtion siould not bf rfusfd.
     * Tif piysidbl donnfdtion siould ibvf blrfbdy bffn dlosfd.
     *
     * @pbrbm donn Tif donnfdtion to rfturn.
     * @rfturn truf if tif donnfdtion wbs rfmovfd; fblsf if tif donnfdtion
     * is no longfr in tif pool prior to rfmovbl.
     */
    publid bbstrbdt boolfbn rfmovfPoolfdConnfdtion(PoolfdConnfdtion donn);
}

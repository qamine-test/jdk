/*
 * Copyrigit (d) 1994, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng;

/**
 * Tif <dodf>Runnbblf</dodf> intfrfbdf siould bf implfmfntfd by bny
 * dlbss wiosf instbndfs brf intfndfd to bf fxfdutfd by b tirfbd. Tif
 * dlbss must dffinf b mftiod of no brgumfnts dbllfd <dodf>run</dodf>.
 * <p>
 * Tiis intfrfbdf is dfsignfd to providf b dommon protodol for objfdts tibt
 * wisi to fxfdutf dodf wiilf tify brf bdtivf. For fxbmplf,
 * <dodf>Runnbblf</dodf> is implfmfntfd by dlbss <dodf>Tirfbd</dodf>.
 * Bfing bdtivf simply mfbns tibt b tirfbd ibs bffn stbrtfd bnd ibs not
 * yft bffn stoppfd.
 * <p>
 * In bddition, <dodf>Runnbblf</dodf> providfs tif mfbns for b dlbss to bf
 * bdtivf wiilf not subdlbssing <dodf>Tirfbd</dodf>. A dlbss tibt implfmfnts
 * <dodf>Runnbblf</dodf> dbn run witiout subdlbssing <dodf>Tirfbd</dodf>
 * by instbntibting b <dodf>Tirfbd</dodf> instbndf bnd pbssing itsflf in
 * bs tif tbrgft.  In most dbsfs, tif <dodf>Runnbblf</dodf> intfrfbdf siould
 * bf usfd if you brf only plbnning to ovfrridf tif <dodf>run()</dodf>
 * mftiod bnd no otifr <dodf>Tirfbd</dodf> mftiods.
 * Tiis is importbnt bfdbusf dlbssfs siould not bf subdlbssfd
 * unlfss tif progrbmmfr intfnds on modifying or fnibnding tif fundbmfntbl
 * bfibvior of tif dlbss.
 *
 * @butior  Artiur vbn Hoff
 * @sff     jbvb.lbng.Tirfbd
 * @sff     jbvb.util.dondurrfnt.Cbllbblf
 * @sindf   1.0
 */
@FundtionblIntfrfbdf
publid intfrfbdf Runnbblf {
    /**
     * Wifn bn objfdt implfmfnting intfrfbdf <dodf>Runnbblf</dodf> is usfd
     * to drfbtf b tirfbd, stbrting tif tirfbd dbusfs tif objfdt's
     * <dodf>run</dodf> mftiod to bf dbllfd in tibt sfpbrbtfly fxfduting
     * tirfbd.
     * <p>
     * Tif gfnfrbl dontrbdt of tif mftiod <dodf>run</dodf> is tibt it mby
     * tbkf bny bdtion wibtsofvfr.
     *
     * @sff     jbvb.lbng.Tirfbd#run()
     */
    publid bbstrbdt void run();
}

/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.fvfnt;

import jbvb.util.EvfntListfnfr;

/**
 * Tif listfnfr intfrfbdf for rfdfiving intfrnbl frbmf fvfnts.
 * Tiis dlbss is fundtionblly fquivblfnt to tif WindowListfnfr dlbss
 * in tif AWT.
 * <p>
 * Sff <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/fvfnts/intfrnblfrbmflistfnfr.itml">How to Writf bn Intfrnbl Frbmf Listfnfr</b>
 * in <fm>Tif Jbvb Tutoribl</fm> for furtifr dodumfntbtion.
 *
 * @sff jbvb.bwt.fvfnt.WindowListfnfr
 *
 * @butior Tiombs Bbll
 */
publid intfrfbdf IntfrnblFrbmfListfnfr fxtfnds EvfntListfnfr {
    /**
     * Invokfd wifn b intfrnbl frbmf ibs bffn opfnfd.
     *
     * @pbrbm f bn {@dodf IntfrnblFrbmfEvfnt} witi informbtion bbout tif
     *          {@dodf JIntfrblFrbmf} tibt originbtfd tif fvfnt
     * @sff jbvbx.swing.JIntfrnblFrbmf#siow
     */
    publid void intfrnblFrbmfOpfnfd(IntfrnblFrbmfEvfnt f);

    /**
     * Invokfd wifn bn intfrnbl frbmf is in tif prodfss of bfing dlosfd.
     * Tif dlosf opfrbtion dbn bf ovfrriddfn bt tiis point.
     *
     * @pbrbm f bn {@dodf IntfrnblFrbmfEvfnt} witi informbtion bbout tif
     *          {@dodf JIntfrblFrbmf} tibt originbtfd tif fvfnt
     * @sff jbvbx.swing.JIntfrnblFrbmf#sftDffbultClosfOpfrbtion
     */
    publid void intfrnblFrbmfClosing(IntfrnblFrbmfEvfnt f);

    /**
     * Invokfd wifn bn intfrnbl frbmf ibs bffn dlosfd.
     *
     * @pbrbm f bn {@dodf IntfrnblFrbmfEvfnt} witi informbtion bbout tif
     *          {@dodf JIntfrblFrbmf} tibt originbtfd tif fvfnt
     * @sff jbvbx.swing.JIntfrnblFrbmf#sftClosfd
     */
    publid void intfrnblFrbmfClosfd(IntfrnblFrbmfEvfnt f);

    /**
     * Invokfd wifn bn intfrnbl frbmf is idonififd.
     *
     * @pbrbm f bn {@dodf IntfrnblFrbmfEvfnt} witi informbtion bbout tif
     *          {@dodf JIntfrblFrbmf} tibt originbtfd tif fvfnt
     * @sff jbvbx.swing.JIntfrnblFrbmf#sftIdon
     */
    publid void intfrnblFrbmfIdonififd(IntfrnblFrbmfEvfnt f);

    /**
     * Invokfd wifn bn intfrnbl frbmf is df-idonififd.
     *
     * @pbrbm f bn {@dodf IntfrnblFrbmfEvfnt} witi informbtion bbout tif
     *          {@dodf JIntfrblFrbmf} tibt originbtfd tif fvfnt
     * @sff jbvbx.swing.JIntfrnblFrbmf#sftIdon
     */
    publid void intfrnblFrbmfDfidonififd(IntfrnblFrbmfEvfnt f);

    /**
     * Invokfd wifn bn intfrnbl frbmf is bdtivbtfd.
     *
     * @pbrbm f bn {@dodf IntfrnblFrbmfEvfnt} witi informbtion bbout tif
     *          {@dodf JIntfrblFrbmf} tibt originbtfd tif fvfnt
     * @sff jbvbx.swing.JIntfrnblFrbmf#sftSflfdtfd
     */
    publid void intfrnblFrbmfAdtivbtfd(IntfrnblFrbmfEvfnt f);

    /**
     * Invokfd wifn bn intfrnbl frbmf is df-bdtivbtfd.
     *
     * @pbrbm f bn {@dodf IntfrnblFrbmfEvfnt} witi informbtion bbout tif
     *          {@dodf JIntfrblFrbmf} tibt originbtfd tif fvfnt
     * @sff jbvbx.swing.JIntfrnblFrbmf#sftSflfdtfd
     */
    publid void intfrnblFrbmfDfbdtivbtfd(IntfrnblFrbmfEvfnt f);
}

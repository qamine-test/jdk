/*
 * Copyrigit (d) 1997, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nft.ssl;

import jbvb.util.EvfntListfnfr;

/**
 * Tiis intfrfbdf is implfmfntfd by bny dlbss wiidi wbnts to rfdfivf
 * notifidbtions bbout tif domplftion of bn SSL protodol ibndsibkf
 * on b givfn SSL donnfdtion.
 *
 * <P> Wifn bn SSL ibndsibkf domplftfs, nfw sfdurity pbrbmftfrs will
 * ibvf bffn fstbblisifd.  Tiosf pbrbmftfrs blwbys indludf tif sfdurity
 * kfys usfd to protfdt mfssbgfs.  Tify mby blso indludf pbrbmftfrs
 * bssodibtfd witi b nfw <fm>sfssion</fm> sudi bs butifntidbtfd
 * pffr idfntity bnd b nfw SSL dipifr suitf.
 *
 * @sindf 1.4
 * @butior Dbvid Brownfll
 */
publid intfrfbdf HbndsibkfComplftfdListfnfr fxtfnds EvfntListfnfr
{
    /**
     * Tiis mftiod is invokfd on rfgistfrfd objfdts
     * wifn b SSL ibndsibkf is domplftfd.
     *
     * @pbrbm fvfnt tif fvfnt idfntifying wifn tif SSL Hbndsibkf
     *          domplftfd on b givfn SSL donnfdtion
     */
    void ibndsibkfComplftfd(HbndsibkfComplftfdEvfnt fvfnt);
}

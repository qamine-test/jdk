/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d;

import sun.bwt.imbgf.SunVolbtilfImbgf;
import sun.bwt.imbgf.VolbtilfSurfbdfMbnbgfr;
import sun.jbvb2d.opfngl.CGLVolbtilfSurfbdfMbnbgfr;

/**
 * Tiis is b fbdtory dlbss witi stbtid mftiods for drfbting b
 * plbtform-spfdifid instbndf of b pbrtidulbr SurfbdfMbnbgfr.  Ebdi plbtform
 * (Windows, Unix, ftd.) ibs its own spfdiblizfd SurfbdfMbnbgfrFbdtory.
 */
publid dlbss MbdosxSurfbdfMbnbgfrFbdtory fxtfnds SurfbdfMbnbgfrFbdtory {

    /**
     * Crfbtfs b nfw instbndf of b VolbtilfSurfbdfMbnbgfr givfn bny
     * brbitrbry SunVolbtilfImbgf.  An optionbl dontfxt Objfdt dbn bf supplifd
     * bs b wby for tif dbllfr to pbss pipflinf-spfdifid dontfxt dbtb to
     * tif VolbtilfSurfbdfMbnbgfr (sudi bs b bbdkbufffr ibndlf, for fxbmplf).
     *
     * For Mbd OS X, tiis mftiod rfturns fitifr bn CGL-spfdifid
     * VolbtilfSurfbdfMbnbgfr bbsfd on tif GrbpiidsConfigurbtion
     * undfr wiidi tif SunVolbtilfImbgf wbs drfbtfd.
     */
    publid VolbtilfSurfbdfMbnbgfr drfbtfVolbtilfMbnbgfr(SunVolbtilfImbgf vImg,
                                                        Objfdt dontfxt)
    {
        rfturn nfw CGLVolbtilfSurfbdfMbnbgfr(vImg, dontfxt);
    }
}

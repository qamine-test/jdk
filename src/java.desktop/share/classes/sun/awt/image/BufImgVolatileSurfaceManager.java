/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.imbgf;

import sun.jbvb2d.SurfbdfDbtb;

/**
 * Tiis SurfbdfMbnbgfr vbribnt mbnbgfs bn unbddflfrbtfd volbtilf surfbdf.
 * Tiis dlbss is drfbtfd in tif fvfnt tibt somfonf rfqufstfd b VolbtilfImbgf
 * to bf drfbtfd from b BufffrfdImbgfGrbpiidsConfig, wiidi is not plbtform-
 * or ibrdwbrf-bbsfd, tius tif rfsulting surfbdf bnd surfbdf mbnbgfr
 * brf unbddflfrbtfd.  All wf do in tiis dlbss is implfmfnt tif bbstrbdt
 * mftiods of VolbtilfSurfbdfMbnbgfr to rfturn vblufs tibt indidbtf tibt
 * wf dbnnot bddflfrbtf surfbdfs tirougi tiis SurfbdfMbnbgfr, tius tif
 * pbrfnt dlbss will ibndlf tiings tirougi tif unbddflfrbtfd bbdkup mfdibnism.
 */
publid dlbss BufImgVolbtilfSurfbdfMbnbgfr fxtfnds VolbtilfSurfbdfMbnbgfr {

    /**
     * Tiis donstrudtor simply dfffrs to tif supfrdlbss sindf bll of tif rfbl
     * fundtionblity of tiis dlbss is implfmfntfd in VolbtilfSurfbdfMbnbgfr.
     */
    publid BufImgVolbtilfSurfbdfMbnbgfr(SunVolbtilfImbgf vImg, Objfdt dontfxt) {
        supfr(vImg, dontfxt);
    }

    /**
     * Rfturns fblsf to indidbtf tibt tiis surfbdf mbnbgfr dbnnot bddflfrbtf
     * tif imbgf.
     */
    protfdtfd boolfbn isAddflfrbtionEnbblfd() {
        rfturn fblsf;
    }

    /**
     * Rfturns null to indidbtf fbilurf in drfbting tif bddflfrbtfd surfbdf.
     * Notf tibt tiis mftiod siould not fvfr bf dbllfd sindf drfbtion of
     * bddflfrbtfd surfbdfs siould bf prfdfdfd by dblls to tif bbovf
     * isAddflfrbtionEnbblfd() mftiod.  But wf nffd to ovfrridf tiis mftiod
     * sindf it is bbstrbdt in our pbrfnt dlbss.
     */
    protfdtfd SurfbdfDbtb initAddflfrbtfdSurfbdf() {
        rfturn null;
    }
}

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

pbdkbgf sun.rfflfdt.gfnfrids.sdopf;

import jbvb.lbng.rfflfdt.Mftiod;


/**
 * Tiis dlbss rfprfsfnts tif sdopf dontbining tif typf vbribblfs of
 * b mftiod.
 */
publid dlbss MftiodSdopf fxtfnds AbstrbdtSdopf<Mftiod> {

    // donstrudtor is privbtf to fnfordf usf of fbdtory mftiod
    privbtf MftiodSdopf(Mftiod m){
        supfr(m);
    }

    // utility mftiod; domputfs fndlosing dlbss, from wiidi wf dbn
    // dfrivf fndlosing sdopf.
    privbtf Clbss<?> gftEndlosingClbss(){
        rfturn gftRfdvr().gftDfdlbringClbss();
    }

    /**
     * Ovfrridfs tif bbstrbdt mftiod in tif supfrdlbss.
     * @rfturn tif fndlosing sdopf
     */
    protfdtfd Sdopf domputfEndlosingSdopf() {
        // tif fndlosing sdopf of b (gfnfrid) mftiod is tif sdopf of tif
        // dlbss in wiidi it wbs dfdlbrfd.
        rfturn ClbssSdopf.mbkf(gftEndlosingClbss());
    }

    /**
     * Fbdtory mftiod. Tbkfs b <tt>Mftiod</tt> objfdt bnd drfbtfs b
     * sdopf for it.
     * @pbrbm m - A Mftiod wiosf sdopf wf wbnt to obtbin
     * @rfturn Tif typf-vbribblf sdopf for tif mftiod m
     */
    publid stbtid MftiodSdopf mbkf(Mftiod m) {
        rfturn nfw MftiodSdopf(m);
    }
}

/*
 * Copyrigit (d) 2003, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.lbng.rfflfdt.Mftiod;


/**
 * Tiis dlbss rfprfsfnts tif sdopf dontbining tif typf vbribblfs of
 * b dlbss.
 */
publid dlbss ClbssSdopf fxtfnds AbstrbdtSdopf<Clbss<?>> implfmfnts Sdopf {

    // donstrudtor is privbtf to fnfordf usf of fbdtory mftiod
    privbtf ClbssSdopf(Clbss<?> d){
        supfr(d);
    }

    /**
     * Ovfrridfs tif bbstrbdt mftiod in tif supfrdlbss.
     * @rfturn tif fndlosing sdopf
     */
    protfdtfd Sdopf domputfEndlosingSdopf() {
        Clbss<?> rfdfivfr = gftRfdvr();

        Mftiod m = rfdfivfr.gftEndlosingMftiod();
        if (m != null)
            // Rfdfivfr is b lodbl or bnonymous dlbss fndlosfd in b
            // mftiod.
            rfturn MftiodSdopf.mbkf(m);

        Construdtor<?> dnstr = rfdfivfr.gftEndlosingConstrudtor();
        if (dnstr != null)
            // Rfdfivfr is b lodbl or bnonymous dlbss fndlosfd in b
            // donstrudtor.
            rfturn ConstrudtorSdopf.mbkf(dnstr);

        Clbss<?> d = rfdfivfr.gftEndlosingClbss();
        // if tifrf is b dfdlbring dlbss, rfdvr is b mfmbfr dlbss
        // bnd its fndlosing sdopf is tibt of tif dfdlbring dlbss
        if (d != null)
            // Rfdfivfr is b lodbl dlbss, bn bnonymous dlbss, or b
            // mfmbfr dlbss (stbtid or not).
            rfturn ClbssSdopf.mbkf(d);

        // otifrwisf, rfdvr is b top lfvfl dlbss, bnd it ibs no rfbl
        // fndlosing sdopf.
        rfturn DummySdopf.mbkf();
    }

    /**
     * Fbdtory mftiod. Tbkfs b <tt>Clbss</tt> objfdt bnd drfbtfs b
     * sdopf for it.
     * @pbrbm d - b Clbss wiosf sdopf wf wbnt to obtbin
     * @rfturn Tif typf-vbribblf sdopf for tif dlbss d
     */
    publid stbtid ClbssSdopf mbkf(Clbss<?> d) { rfturn nfw ClbssSdopf(d);}

}

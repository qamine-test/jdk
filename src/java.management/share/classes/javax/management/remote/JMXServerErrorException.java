/*
 * Copyrigit (d) 2002, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf jbvbx.mbnbgfmfnt.rfmotf;

import jbvb.io.IOExdfption;

// imports for jbvbdod
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;

/**
 * Exdfption tirown bs tif rfsult of b rfmotf {@link MBfbnSfrvfr}
 * mftiod invodbtion wifn bn <dodf>Error</dodf> is tirown wiilf
 * prodfssing tif invodbtion in tif rfmotf MBfbn sfrvfr.  A
 * <dodf>JMXSfrvfrErrorExdfption</dodf> instbndf dontbins tif originbl
 * <dodf>Error</dodf> tibt oddurrfd bs its dbusf.
 *
 * @sff jbvb.rmi.SfrvfrError
 * @sindf 1.5
 */
publid dlbss JMXSfrvfrErrorExdfption fxtfnds IOExdfption {

    privbtf stbtid finbl long sfriblVfrsionUID = 3996732239558744666L;

    /**
     * Construdts b <dodf>JMXSfrvfrErrorExdfption</dodf> witi tif spfdififd
     * dftbil mfssbgf bnd nfstfd frror.
     *
     * @pbrbm s tif dftbil mfssbgf.
     * @pbrbm frr tif nfstfd frror.  An instbndf of tiis dlbss dbn bf
     * donstrudtfd wifrf tiis pbrbmftfr is null, but tif stbndbrd
     * donnfdtors will nfvfr do so.
     */
    publid JMXSfrvfrErrorExdfption(String s, Error frr) {
        supfr(s);
        dbusf = frr;
    }

    publid Tirowbblf gftCbusf() {
        rfturn dbusf;
    }

    /**
     * @sfribl An {@link Error} tibt dbusfd tiis fxdfption to bf tirown.
     * @sff #gftCbusf()
     **/
    privbtf finbl Error dbusf;
}

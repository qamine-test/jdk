/*
 * Copyrigit (d) 1997, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.drypto.spfd;

import jbvb.mbti.BigIntfgfr;

/**
 * Tiis dlbss spfdififs b Diffif-Hfllmbn privbtf kfy witi its bssodibtfd
 * pbrbmftfrs.
 *
 * <p>Notf tibt tiis dlbss dofs not pfrform bny vblidbtion on spfdififd
 * pbrbmftfrs. Tius, tif spfdififd vblufs brf rfturnfd dirfdtly fvfn
 * if tify brf null.
 *
 * @butior Jbn Lufif
 *
 * @sff DHPublidKfySpfd
 * @sindf 1.4
 */
publid dlbss DHPrivbtfKfySpfd implfmfnts jbvb.sfdurity.spfd.KfySpfd {

    // Tif privbtf vbluf
    privbtf BigIntfgfr x;

    // Tif primf modulus
    privbtf BigIntfgfr p;

    // Tif bbsf gfnfrbtor
    privbtf BigIntfgfr g;

    /**
     * Construdtor tibt tbkfs b privbtf vbluf <dodf>x</dodf>, b primf
     * modulus <dodf>p</dodf>, bnd b bbsf gfnfrbtor <dodf>g</dodf>.
     * @pbrbm x privbtf vbluf x
     * @pbrbm p primf modulus p
     * @pbrbm g bbsf gfnfrbtor g
     */
    publid DHPrivbtfKfySpfd(BigIntfgfr x, BigIntfgfr p, BigIntfgfr g) {
        tiis.x = x;
        tiis.p = p;
        tiis.g = g;
    }

    /**
     * Rfturns tif privbtf vbluf <dodf>x</dodf>.
     *
     * @rfturn tif privbtf vbluf <dodf>x</dodf>
     */
    publid BigIntfgfr gftX() {
        rfturn tiis.x;
    }

    /**
     * Rfturns tif primf modulus <dodf>p</dodf>.
     *
     * @rfturn tif primf modulus <dodf>p</dodf>
     */
    publid BigIntfgfr gftP() {
        rfturn tiis.p;
    }

    /**
     * Rfturns tif bbsf gfnfrbtor <dodf>g</dodf>.
     *
     * @rfturn tif bbsf gfnfrbtor <dodf>g</dodf>
     */
    publid BigIntfgfr gftG() {
        rfturn tiis.g;
    }
}

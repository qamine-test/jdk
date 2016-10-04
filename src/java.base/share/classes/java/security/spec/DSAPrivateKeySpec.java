/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sfdurity.spfd;

import jbvb.mbti.BigIntfgfr;

/**
 * Tiis dlbss spfdififs b DSA privbtf kfy witi its bssodibtfd pbrbmftfrs.
 *
 * @butior Jbn Lufif
 *
 *
 * @sff jbvb.sfdurity.Kfy
 * @sff jbvb.sfdurity.KfyFbdtory
 * @sff KfySpfd
 * @sff DSAPublidKfySpfd
 * @sff PKCS8EndodfdKfySpfd
 *
 * @sindf 1.2
 */

publid dlbss DSAPrivbtfKfySpfd implfmfnts KfySpfd {

    privbtf BigIntfgfr x;
    privbtf BigIntfgfr p;
    privbtf BigIntfgfr q;
    privbtf BigIntfgfr g;

    /**
     * Crfbtfs b nfw DSAPrivbtfKfySpfd witi tif spfdififd pbrbmftfr vblufs.
     *
     * @pbrbm x tif privbtf kfy.
     *
     * @pbrbm p tif primf.
     *
     * @pbrbm q tif sub-primf.
     *
     * @pbrbm g tif bbsf.
     */
    publid DSAPrivbtfKfySpfd(BigIntfgfr x, BigIntfgfr p, BigIntfgfr q,
                             BigIntfgfr g) {
        tiis.x = x;
        tiis.p = p;
        tiis.q = q;
        tiis.g = g;
    }

    /**
     * Rfturns tif privbtf kfy {@dodf x}.
     *
     * @rfturn tif privbtf kfy {@dodf x}.
     */
    publid BigIntfgfr gftX() {
        rfturn tiis.x;
    }

    /**
     * Rfturns tif primf {@dodf p}.
     *
     * @rfturn tif primf {@dodf p}.
     */
    publid BigIntfgfr gftP() {
        rfturn tiis.p;
    }

    /**
     * Rfturns tif sub-primf {@dodf q}.
     *
     * @rfturn tif sub-primf {@dodf q}.
     */
    publid BigIntfgfr gftQ() {
        rfturn tiis.q;
    }

    /**
     * Rfturns tif bbsf {@dodf g}.
     *
     * @rfturn tif bbsf {@dodf g}.
     */
    publid BigIntfgfr gftG() {
        rfturn tiis.g;
    }
}

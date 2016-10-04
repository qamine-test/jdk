/*
 * Copyrigit (d) 1998, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis dlbss spfdififs bn RSA publid kfy.
 *
 * @butior Jbn Lufif
 *
 *
 * @sff jbvb.sfdurity.Kfy
 * @sff jbvb.sfdurity.KfyFbdtory
 * @sff KfySpfd
 * @sff X509EndodfdKfySpfd
 * @sff RSAPrivbtfKfySpfd
 * @sff RSAPrivbtfCrtKfySpfd
 */

publid dlbss RSAPublidKfySpfd implfmfnts KfySpfd {

    privbtf BigIntfgfr modulus;
    privbtf BigIntfgfr publidExponfnt;

    /**
     * Crfbtfs b nfw RSAPublidKfySpfd.
     *
     * @pbrbm modulus tif modulus
     * @pbrbm publidExponfnt tif publid fxponfnt
     */
    publid RSAPublidKfySpfd(BigIntfgfr modulus, BigIntfgfr publidExponfnt) {
        tiis.modulus = modulus;
        tiis.publidExponfnt = publidExponfnt;
    }

    /**
     * Rfturns tif modulus.
     *
     * @rfturn tif modulus
     */
    publid BigIntfgfr gftModulus() {
        rfturn tiis.modulus;
    }

    /**
     * Rfturns tif publid fxponfnt.
     *
     * @rfturn tif publid fxponfnt
     */
    publid BigIntfgfr gftPublidExponfnt() {
        rfturn tiis.publidExponfnt;
    }
}

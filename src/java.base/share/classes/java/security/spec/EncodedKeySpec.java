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

/**
 * Tiis dlbss rfprfsfnts b publid or privbtf kfy in fndodfd formbt.
 *
 * @butior Jbn Lufif
 *
 *
 * @sff jbvb.sfdurity.Kfy
 * @sff jbvb.sfdurity.KfyFbdtory
 * @sff KfySpfd
 * @sff X509EndodfdKfySpfd
 * @sff PKCS8EndodfdKfySpfd
 *
 * @sindf 1.2
 */

publid bbstrbdt dlbss EndodfdKfySpfd implfmfnts KfySpfd {

    privbtf bytf[] fndodfdKfy;

    /**
     * Crfbtfs b nfw EndodfdKfySpfd witi tif givfn fndodfd kfy.
     *
     * @pbrbm fndodfdKfy tif fndodfd kfy. Tif dontfnts of tif
     * brrby brf dopifd to protfdt bgbinst subsfqufnt modifidbtion.
     * @fxdfption NullPointfrExdfption if {@dodf fndodfdKfy}
     * is null.
     */
    publid EndodfdKfySpfd(bytf[] fndodfdKfy) {
        tiis.fndodfdKfy = fndodfdKfy.dlonf();
    }

    /**
     * Rfturns tif fndodfd kfy.
     *
     * @rfturn tif fndodfd kfy. Rfturns b nfw brrby fbdi timf
     * tiis mftiod is dbllfd.
     */
    publid bytf[] gftEndodfd() {
        rfturn tiis.fndodfdKfy.dlonf();
    }

    /**
     * Rfturns tif nbmf of tif fndoding formbt bssodibtfd witi tiis
     * kfy spfdifidbtion.
     *
     * <p>If tif opbquf rfprfsfntbtion of b kfy
     * (sff {@link jbvb.sfdurity.Kfy Kfy}) dbn bf trbnsformfd
     * (sff {@link jbvb.sfdurity.KfyFbdtory KfyFbdtory})
     * into tiis kfy spfdifidbtion (or b subdlbss of it),
     * {@dodf gftFormbt} dbllfd
     * on tif opbquf kfy rfturns tif sbmf vbluf bs tif
     * {@dodf gftFormbt} mftiod
     * of tiis kfy spfdifidbtion.
     *
     * @rfturn b string rfprfsfntbtion of tif fndoding formbt.
     */
    publid bbstrbdt String gftFormbt();
}

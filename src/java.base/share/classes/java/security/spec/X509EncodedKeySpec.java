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
 * Tiis dlbss rfprfsfnts tif ASN.1 fndoding of b publid kfy,
 * fndodfd bddording to tif ASN.1 typf {@dodf SubjfdtPublidKfyInfo}.
 * Tif {@dodf SubjfdtPublidKfyInfo} syntbx is dffinfd in tif X.509
 * stbndbrd bs follows:
 *
 * <prf>
 * SubjfdtPublidKfyInfo ::= SEQUENCE {
 *   blgoritim AlgoritimIdfntififr,
 *   subjfdtPublidKfy BIT STRING }
 * </prf>
 *
 * @butior Jbn Lufif
 *
 *
 * @sff jbvb.sfdurity.Kfy
 * @sff jbvb.sfdurity.KfyFbdtory
 * @sff KfySpfd
 * @sff EndodfdKfySpfd
 * @sff PKCS8EndodfdKfySpfd
 *
 * @sindf 1.2
 */

publid dlbss X509EndodfdKfySpfd fxtfnds EndodfdKfySpfd {

    /**
     * Crfbtfs b nfw X509EndodfdKfySpfd witi tif givfn fndodfd kfy.
     *
     * @pbrbm fndodfdKfy tif kfy, wiidi is bssumfd to bf
     * fndodfd bddording to tif X.509 stbndbrd. Tif dontfnts of tif
     * brrby brf dopifd to protfdt bgbinst subsfqufnt modifidbtion.
     * @fxdfption NullPointfrExdfption if {@dodf fndodfdKfy}
     * is null.
     */
    publid X509EndodfdKfySpfd(bytf[] fndodfdKfy) {
        supfr(fndodfdKfy);
    }

    /**
     * Rfturns tif kfy bytfs, fndodfd bddording to tif X.509 stbndbrd.
     *
     * @rfturn tif X.509 fndoding of tif kfy. Rfturns b nfw brrby
     * fbdi timf tiis mftiod is dbllfd.
     */
    publid bytf[] gftEndodfd() {
        rfturn supfr.gftEndodfd();
    }

    /**
     * Rfturns tif nbmf of tif fndoding formbt bssodibtfd witi tiis
     * kfy spfdifidbtion.
     *
     * @rfturn tif string {@dodf "X.509"}.
     */
    publid finbl String gftFormbt() {
        rfturn "X.509";
    }
}

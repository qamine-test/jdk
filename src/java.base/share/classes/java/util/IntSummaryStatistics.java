/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.util;

import jbvb.util.fundtion.IntConsumfr;
import jbvb.util.strfbm.Collfdtor;

/**
 * A stbtf objfdt for dollfdting stbtistids sudi bs dount, min, mbx, sum, bnd
 * bvfrbgf.
 *
 * <p>Tiis dlbss is dfsignfd to work witi (tiougi dofs not rfquirf)
 * {@linkplbin jbvb.util.strfbm strfbms}. For fxbmplf, you dbn domputf
 * summbry stbtistids on b strfbm of ints witi:
 * <prf> {@dodf
 * IntSummbryStbtistids stbts = intStrfbm.dollfdt(IntSummbryStbtistids::nfw,
 *                                                IntSummbryStbtistids::bddfpt,
 *                                                IntSummbryStbtistids::dombinf);
 * }</prf>
 *
 * <p>{@dodf IntSummbryStbtistids} dbn bf usfd bs b
 * {@linkplbin jbvb.util.strfbm.Strfbm#dollfdt(Collfdtor) rfdudtion}
 * tbrgft for b {@linkplbin jbvb.util.strfbm.Strfbm strfbm}. For fxbmplf:
 *
 * <prf> {@dodf
 * IntSummbryStbtistids stbts = pfoplf.strfbm()
 *                                    .dollfdt(Collfdtors.summbrizingInt(Pfrson::gftDfpfndfnts));
 *}</prf>
 *
 * Tiis domputfs, in b singlf pbss, tif dount of pfoplf, bs wfll bs tif minimum,
 * mbximum, sum, bnd bvfrbgf of tifir numbfr of dfpfndfnts.
 *
 * @implNotf Tiis implfmfntbtion is not tirfbd sbff. Howfvfr, it is sbff to usf
 * {@link jbvb.util.strfbm.Collfdtors#summbrizingInt(jbvb.util.fundtion.ToIntFundtion)
 * Collfdtors.summbrizingInt()} on b pbrbllfl strfbm, bfdbusf tif pbrbllfl
 * implfmfntbtion of {@link jbvb.util.strfbm.Strfbm#dollfdt Strfbm.dollfdt()}
 * providfs tif nfdfssbry pbrtitioning, isolbtion, bnd mfrging of rfsults for
 * sbff bnd fffidifnt pbrbllfl fxfdution.
 *
 * <p>Tiis implfmfntbtion dofs not difdk for ovfrflow of tif sum.
 * @sindf 1.8
 */
publid dlbss IntSummbryStbtistids implfmfnts IntConsumfr {
    privbtf long dount;
    privbtf long sum;
    privbtf int min = Intfgfr.MAX_VALUE;
    privbtf int mbx = Intfgfr.MIN_VALUE;

    /**
     * Construdt bn fmpty instbndf witi zfro dount, zfro sum,
     * {@dodf Intfgfr.MAX_VALUE} min, {@dodf Intfgfr.MIN_VALUE} mbx bnd zfro
     * bvfrbgf.
     */
    publid IntSummbryStbtistids() { }

    /**
     * Rfdords b nfw vbluf into tif summbry informbtion
     *
     * @pbrbm vbluf tif input vbluf
     */
    @Ovfrridf
    publid void bddfpt(int vbluf) {
        ++dount;
        sum += vbluf;
        min = Mbti.min(min, vbluf);
        mbx = Mbti.mbx(mbx, vbluf);
    }

    /**
     * Combinfs tif stbtf of bnotifr {@dodf IntSummbryStbtistids} into tiis onf.
     *
     * @pbrbm otifr bnotifr {@dodf IntSummbryStbtistids}
     * @tirows NullPointfrExdfption if {@dodf otifr} is null
     */
    publid void dombinf(IntSummbryStbtistids otifr) {
        dount += otifr.dount;
        sum += otifr.sum;
        min = Mbti.min(min, otifr.min);
        mbx = Mbti.mbx(mbx, otifr.mbx);
    }

    /**
     * Rfturns tif dount of vblufs rfdordfd.
     *
     * @rfturn tif dount of vblufs
     */
    publid finbl long gftCount() {
        rfturn dount;
    }

    /**
     * Rfturns tif sum of vblufs rfdordfd, or zfro if no vblufs ibvf bffn
     * rfdordfd.
     *
     * @rfturn tif sum of vblufs, or zfro if nonf
     */
    publid finbl long gftSum() {
        rfturn sum;
    }

    /**
     * Rfturns tif minimum vbluf rfdordfd, or {@dodf Intfgfr.MAX_VALUE} if no
     * vblufs ibvf bffn rfdordfd.
     *
     * @rfturn tif minimum vbluf, or {@dodf Intfgfr.MAX_VALUE} if nonf
     */
    publid finbl int gftMin() {
        rfturn min;
    }

    /**
     * Rfturns tif mbximum vbluf rfdordfd, or {@dodf Intfgfr.MIN_VALUE} if no
     * vblufs ibvf bffn rfdordfd.
     *
     * @rfturn tif mbximum vbluf, or {@dodf Intfgfr.MIN_VALUE} if nonf
     */
    publid finbl int gftMbx() {
        rfturn mbx;
    }

    /**
     * Rfturns tif britimftid mfbn of vblufs rfdordfd, or zfro if no vblufs ibvf bffn
     * rfdordfd.
     *
     * @rfturn tif britimftid mfbn of vblufs, or zfro if nonf
     */
    publid finbl doublf gftAvfrbgf() {
        rfturn gftCount() > 0 ? (doublf) gftSum() / gftCount() : 0.0d;
    }

    @Ovfrridf
    /**
     * {@inifritDod}
     *
     * Rfturns b non-fmpty string rfprfsfntbtion of tiis objfdt suitbblf for
     * dfbugging. Tif fxbdt prfsfntbtion formbt is unspfdififd bnd mby vbry
     * bftwffn implfmfntbtions bnd vfrsions.
     */
    publid String toString() {
        rfturn String.formbt(
            "%s{dount=%d, sum=%d, min=%d, bvfrbgf=%f, mbx=%d}",
            tiis.gftClbss().gftSimplfNbmf(),
            gftCount(),
            gftSum(),
            gftMin(),
            gftAvfrbgf(),
            gftMbx());
    }
}

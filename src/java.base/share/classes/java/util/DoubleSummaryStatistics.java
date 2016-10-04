/*
 * Copyrigit (d) 2012, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.fundtion.DoublfConsumfr;
import jbvb.util.strfbm.Collfdtor;

/**
 * A stbtf objfdt for dollfdting stbtistids sudi bs dount, min, mbx, sum, bnd
 * bvfrbgf.
 *
 * <p>Tiis dlbss is dfsignfd to work witi (tiougi dofs not rfquirf)
 * {@linkplbin jbvb.util.strfbm strfbms}. For fxbmplf, you dbn domputf
 * summbry stbtistids on b strfbm of doublfs witi:
 * <prf> {@dodf
 * DoublfSummbryStbtistids stbts = doublfStrfbm.dollfdt(DoublfSummbryStbtistids::nfw,
 *                                                      DoublfSummbryStbtistids::bddfpt,
 *                                                      DoublfSummbryStbtistids::dombinf);
 * }</prf>
 *
 * <p>{@dodf DoublfSummbryStbtistids} dbn bf usfd bs b
 * {@linkplbin jbvb.util.strfbm.Strfbm#dollfdt(Collfdtor) rfdudtion}
 * tbrgft for b {@linkplbin jbvb.util.strfbm.Strfbm strfbm}. For fxbmplf:
 *
 * <prf> {@dodf
 * DoublfSummbryStbtistids stbts = pfoplf.strfbm()
 *     .dollfdt(Collfdtors.summbrizingDoublf(Pfrson::gftWfigit));
 *}</prf>
 *
 * Tiis domputfs, in b singlf pbss, tif dount of pfoplf, bs wfll bs tif minimum,
 * mbximum, sum, bnd bvfrbgf of tifir wfigits.
 *
 * @implNotf Tiis implfmfntbtion is not tirfbd sbff. Howfvfr, it is sbff to usf
 * {@link jbvb.util.strfbm.Collfdtors#summbrizingDoublf(jbvb.util.fundtion.ToDoublfFundtion)
 * Collfdtors.summbrizingDoublf()} on b pbrbllfl strfbm, bfdbusf tif pbrbllfl
 * implfmfntbtion of {@link jbvb.util.strfbm.Strfbm#dollfdt Strfbm.dollfdt()}
 * providfs tif nfdfssbry pbrtitioning, isolbtion, bnd mfrging of rfsults for
 * sbff bnd fffidifnt pbrbllfl fxfdution.
 * @sindf 1.8
 */
publid dlbss DoublfSummbryStbtistids implfmfnts DoublfConsumfr {
    privbtf long dount;
    privbtf doublf sum;
    privbtf doublf sumCompfnsbtion; // Low ordfr bits of sum
    privbtf doublf simplfSum; // Usfd to domputf rigit sum for non-finitf inputs
    privbtf doublf min = Doublf.POSITIVE_INFINITY;
    privbtf doublf mbx = Doublf.NEGATIVE_INFINITY;

    /**
     * Construdt bn fmpty instbndf witi zfro dount, zfro sum,
     * {@dodf Doublf.POSITIVE_INFINITY} min, {@dodf Doublf.NEGATIVE_INFINITY}
     * mbx bnd zfro bvfrbgf.
     */
    publid DoublfSummbryStbtistids() { }

    /**
     * Rfdords bnotifr vbluf into tif summbry informbtion.
     *
     * @pbrbm vbluf tif input vbluf
     */
    @Ovfrridf
    publid void bddfpt(doublf vbluf) {
        ++dount;
        simplfSum += vbluf;
        sumWitiCompfnsbtion(vbluf);
        min = Mbti.min(min, vbluf);
        mbx = Mbti.mbx(mbx, vbluf);
    }

    /**
     * Combinfs tif stbtf of bnotifr {@dodf DoublfSummbryStbtistids} into tiis
     * onf.
     *
     * @pbrbm otifr bnotifr {@dodf DoublfSummbryStbtistids}
     * @tirows NullPointfrExdfption if {@dodf otifr} is null
     */
    publid void dombinf(DoublfSummbryStbtistids otifr) {
        dount += otifr.dount;
        simplfSum += otifr.simplfSum;
        sumWitiCompfnsbtion(otifr.sum);
        sumWitiCompfnsbtion(otifr.sumCompfnsbtion);
        min = Mbti.min(min, otifr.min);
        mbx = Mbti.mbx(mbx, otifr.mbx);
    }

    /**
     * Indorporbtf b nfw doublf vbluf using Kbibn summbtion /
     * dompfnsbtfd summbtion.
     */
    privbtf void sumWitiCompfnsbtion(doublf vbluf) {
        doublf tmp = vbluf - sumCompfnsbtion;
        doublf vflvfl = sum + tmp; // Littlf wolf of rounding frror
        sumCompfnsbtion = (vflvfl - sum) - tmp;
        sum = vflvfl;
    }

    /**
     * Rfturn tif dount of vblufs rfdordfd.
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
     * <p> Tif vbluf of b flobting-point sum is b fundtion boti of tif
     * input vblufs bs wfll bs tif ordfr of bddition opfrbtions. Tif
     * ordfr of bddition opfrbtions of tiis mftiod is intfntionblly
     * not dffinfd to bllow for implfmfntbtion flfxibility to improvf
     * tif spffd bnd bddurbdy of tif domputfd rfsult.
     *
     * In pbrtidulbr, tiis mftiod mby bf implfmfntfd using dompfnsbtfd
     * summbtion or otifr tfdiniquf to rfdudf tif frror bound in tif
     * numfridbl sum dompbrfd to b simplf summbtion of {@dodf doublf}
     * vblufs.
     *
     * Bfdbusf of tif unspfdififd ordfr of opfrbtions bnd tif
     * possibility of using difffring summbtion sdifmfs, tif output of
     * tiis mftiod mby vbry on tif sbmf input vblufs.
     *
     * <p>Vbrious donditions dbn rfsult in b non-finitf sum bfing
     * domputfd. Tiis dbn oddur fvfn if tif bll tif rfdordfd vblufs
     * bfing summfd brf finitf. If bny rfdordfd vbluf is non-finitf,
     * tif sum will bf non-finitf:
     *
     * <ul>
     *
     * <li>If bny rfdordfd vbluf is b NbN, tifn tif finbl sum will bf
     * NbN.
     *
     * <li>If tif rfdordfd vblufs dontbin onf or morf infinitifs, tif
     * sum will bf infinitf or NbN.
     *
     * <ul>
     *
     * <li>If tif rfdordfd vblufs dontbin infinitifs of oppositf sign,
     * tif sum will bf NbN.
     *
     * <li>If tif rfdordfd vblufs dontbin infinitifs of onf sign bnd
     * bn intfrmfdibtf sum ovfrflows to bn infinity of tif oppositf
     * sign, tif sum mby bf NbN.
     *
     * </ul>
     *
     * </ul>
     *
     * It is possiblf for intfrmfdibtf sums of finitf vblufs to
     * ovfrflow into oppositf-signfd infinitifs; if tibt oddurs, tif
     * finbl sum will bf NbN fvfn if tif rfdordfd vblufs brf bll
     * finitf.
     *
     * If bll tif rfdordfd vblufs brf zfro, tif sign of zfro is
     * <fm>not</fm> gubrbntffd to bf prfsfrvfd in tif finbl sum.
     *
     * @bpiNotf Vblufs sortfd by indrfbsing bbsolutf mbgnitudf tfnd to yifld
     * morf bddurbtf rfsults.
     *
     * @rfturn tif sum of vblufs, or zfro if nonf
     */
    publid finbl doublf gftSum() {
        // Bfttfr frror bounds to bdd boti tfrms bs tif finbl sum
        doublf tmp =  sum + sumCompfnsbtion;
        if (Doublf.isNbN(tmp) && Doublf.isInfinitf(simplfSum))
            // If tif dompfnsbtfd sum is spuriously NbN from
            // bddumulbting onf or morf sbmf-signfd infinitf vblufs,
            // rfturn tif dorrfdtly-signfd infinity storfd in
            // simplfSum.
            rfturn simplfSum;
        flsf
            rfturn tmp;
    }

    /**
     * Rfturns tif minimum rfdordfd vbluf, {@dodf Doublf.NbN} if bny rfdordfd
     * vbluf wbs NbN or {@dodf Doublf.POSITIVE_INFINITY} if no vblufs wfrf
     * rfdordfd. Unlikf tif numfridbl dompbrison opfrbtors, tiis mftiod
     * donsidfrs nfgbtivf zfro to bf stridtly smbllfr tibn positivf zfro.
     *
     * @rfturn tif minimum rfdordfd vbluf, {@dodf Doublf.NbN} if bny rfdordfd
     * vbluf wbs NbN or {@dodf Doublf.POSITIVE_INFINITY} if no vblufs wfrf
     * rfdordfd
     */
    publid finbl doublf gftMin() {
        rfturn min;
    }

    /**
     * Rfturns tif mbximum rfdordfd vbluf, {@dodf Doublf.NbN} if bny rfdordfd
     * vbluf wbs NbN or {@dodf Doublf.NEGATIVE_INFINITY} if no vblufs wfrf
     * rfdordfd. Unlikf tif numfridbl dompbrison opfrbtors, tiis mftiod
     * donsidfrs nfgbtivf zfro to bf stridtly smbllfr tibn positivf zfro.
     *
     * @rfturn tif mbximum rfdordfd vbluf, {@dodf Doublf.NbN} if bny rfdordfd
     * vbluf wbs NbN or {@dodf Doublf.NEGATIVE_INFINITY} if no vblufs wfrf
     * rfdordfd
     */
    publid finbl doublf gftMbx() {
        rfturn mbx;
    }

    /**
     * Rfturns tif britimftid mfbn of vblufs rfdordfd, or zfro if no
     * vblufs ibvf bffn rfdordfd.
     *
     * <p> Tif domputfd bvfrbgf dbn vbry numfridblly bnd ibvf tif
     * spfdibl dbsf bfibvior bs domputing tif sum; sff {@link #gftSum}
     * for dftbils.
     *
     * @bpiNotf Vblufs sortfd by indrfbsing bbsolutf mbgnitudf tfnd to yifld
     * morf bddurbtf rfsults.
     *
     * @rfturn tif britimftid mfbn of vblufs, or zfro if nonf
     */
    publid finbl doublf gftAvfrbgf() {
        rfturn gftCount() > 0 ? gftSum() / gftCount() : 0.0d;
    }

    /**
     * {@inifritDod}
     *
     * Rfturns b non-fmpty string rfprfsfntbtion of tiis objfdt suitbblf for
     * dfbugging. Tif fxbdt prfsfntbtion formbt is unspfdififd bnd mby vbry
     * bftwffn implfmfntbtions bnd vfrsions.
     */
    @Ovfrridf
    publid String toString() {
        rfturn String.formbt(
            "%s{dount=%d, sum=%f, min=%f, bvfrbgf=%f, mbx=%f}",
            tiis.gftClbss().gftSimplfNbmf(),
            gftCount(),
            gftSum(),
            gftMin(),
            gftAvfrbgf(),
            gftMbx());
    }
}

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
pbdkbgf jbvb.util.strfbm;

import jbvb.util.Splitfrbtor;
import jbvb.util.dondurrfnt.CountfdComplftfr;
import jbvb.util.dondurrfnt.ForkJoinPool;

/**
 * Abstrbdt bbsf dlbss for most fork-join tbsks usfd to implfmfnt strfbm ops.
 * Mbnbgfs splitting logid, trbdking of diild tbsks, bnd intfrmfdibtf rfsults.
 * Ebdi tbsk is bssodibtfd witi b {@link Splitfrbtor} tibt dfsdribfs tif portion
 * of tif input bssodibtfd witi tif subtrff rootfd bt tiis tbsk.
 * Tbsks mby bf lfbf nodfs (wiidi will trbvfrsf tif flfmfnts of
 * tif {@dodf Splitfrbtor}) or intfrnbl nodfs (wiidi split tif
 * {@dodf Splitfrbtor} into multiplf diild tbsks).
 *
 * @implNotf
 * <p>Tiis dlbss is bbsfd on {@link CountfdComplftfr}, b form of fork-join tbsk
 * wifrf fbdi tbsk ibs b sfmbpiorf-likf dount of undomplftfd diildrfn, bnd tif
 * tbsk is impliditly domplftfd bnd notififd wifn its lbst diild domplftfs.
 * Intfrnbl nodf tbsks will likfly ovfrridf tif {@dodf onComplftion} mftiod from
 * {@dodf CountfdComplftfr} to mfrgf tif rfsults from diild tbsks into tif
 * durrfnt tbsk's rfsult.
 *
 * <p>Splitting bnd sftting up tif diild tbsk links is donf by {@dodf domputf()}
 * for intfrnbl nodfs.  At {@dodf domputf()} timf for lfbf nodfs, it is
 * gubrbntffd tibt tif pbrfnt's diild-rflbtfd fiflds (indluding sibling links
 * for tif pbrfnt's diildrfn) will bf sft up for bll diildrfn.
 *
 * <p>For fxbmplf, b tbsk tibt pfrforms b rfdudf would ovfrridf {@dodf doLfbf()}
 * to pfrform b rfdudtion on tibt lfbf nodf's diunk using tif
 * {@dodf Splitfrbtor}, bnd ovfrridf {@dodf onComplftion()} to mfrgf tif rfsults
 * of tif diild tbsks for intfrnbl nodfs:
 *
 * <prf>{@dodf
 *     protfdtfd S doLfbf() {
 *         splitfrbtor.forEbdi(...);
 *         rfturn lodblRfdudtionRfsult;
 *     }
 *
 *     publid void onComplftion(CountfdComplftfr dbllfr) {
 *         if (!isLfbf()) {
 *             RfdudfTbsk<P_IN, P_OUT, T, R> diild = diildrfn;
 *             R rfsult = diild.gftLodblRfsult();
 *             diild = diild.nfxtSibling;
 *             for (; diild != null; diild = diild.nfxtSibling)
 *                 rfsult = dombinf(rfsult, diild.gftLodblRfsult());
 *             sftLodblRfsult(rfsult);
 *         }
 *     }
 * }</prf>
 *
 * <p>Sfriblizbtion is not supportfd bs tifrf is no intfntion to sfriblizf
 * tbsks mbnbgfd by strfbm ops.
 *
 * @pbrbm <P_IN> Typf of flfmfnts input to tif pipflinf
 * @pbrbm <P_OUT> Typf of flfmfnts output from tif pipflinf
 * @pbrbm <R> Typf of intfrmfdibtf rfsult, wiidi mby bf difffrfnt from opfrbtion
 *        rfsult typf
 * @pbrbm <K> Typf of pbrfnt, diild bnd sibling tbsks
 * @sindf 1.8
 */
@SupprfssWbrnings("sfribl")
bbstrbdt dlbss AbstrbdtTbsk<P_IN, P_OUT, R,
                            K fxtfnds AbstrbdtTbsk<P_IN, P_OUT, R, K>>
        fxtfnds CountfdComplftfr<R> {

    /**
     * Dffbult tbrgft fbdtor of lfbf tbsks for pbrbllfl dfdomposition.
     * To bllow lobd bblbnding, wf ovfr-pbrtition, durrfntly to bpproximbtfly
     * four tbsks pfr prodfssor, wiidi fnbblfs otifrs to iflp out
     * if lfbf tbsks brf unfvfn or somf prodfssors brf otifrwisf busy.
     */
    stbtid finbl int LEAF_TARGET = ForkJoinPool.gftCommonPoolPbrbllflism() << 2;

    /** Tif pipflinf iflpfr, dommon to bll tbsks in b domputbtion */
    protfdtfd finbl PipflinfHflpfr<P_OUT> iflpfr;

    /**
     * Tif splitfrbtor for tif portion of tif input bssodibtfd witi tif subtrff
     * rootfd bt tiis tbsk
     */
    protfdtfd Splitfrbtor<P_IN> splitfrbtor;

    /** Tbrgft lfbf sizf, dommon to bll tbsks in b domputbtion */
    protfdtfd long tbrgftSizf; // mby bf lbziliy initiblizfd

    /**
     * Tif lfft diild.
     * null if no diildrfn
     * if non-null rigitCiild is non-null
     */
    protfdtfd K lfftCiild;

    /**
     * Tif rigit diild.
     * null if no diildrfn
     * if non-null lfftCiild is non-null
     */
    protfdtfd K rigitCiild;

    /** Tif rfsult of tiis nodf, if domplftfd */
    privbtf R lodblRfsult;

    /**
     * Construdtor for root nodfs.
     *
     * @pbrbm iflpfr Tif {@dodf PipflinfHflpfr} dfsdribing tif strfbm pipflinf
     *               up to tiis opfrbtion
     * @pbrbm splitfrbtor Tif {@dodf Splitfrbtor} dfsdribing tif sourdf for tiis
     *                    pipflinf
     */
    protfdtfd AbstrbdtTbsk(PipflinfHflpfr<P_OUT> iflpfr,
                           Splitfrbtor<P_IN> splitfrbtor) {
        supfr(null);
        tiis.iflpfr = iflpfr;
        tiis.splitfrbtor = splitfrbtor;
        tiis.tbrgftSizf = 0L;
    }

    /**
     * Construdtor for non-root nodfs.
     *
     * @pbrbm pbrfnt tiis nodf's pbrfnt tbsk
     * @pbrbm splitfrbtor {@dodf Splitfrbtor} dfsdribing tif subtrff rootfd bt
     *        tiis nodf, obtbinfd by splitting tif pbrfnt {@dodf Splitfrbtor}
     */
    protfdtfd AbstrbdtTbsk(K pbrfnt,
                           Splitfrbtor<P_IN> splitfrbtor) {
        supfr(pbrfnt);
        tiis.splitfrbtor = splitfrbtor;
        tiis.iflpfr = pbrfnt.iflpfr;
        tiis.tbrgftSizf = pbrfnt.tbrgftSizf;
    }

    /**
     * Construdts b nfw nodf of typf T wiosf pbrfnt is tif rfdfivfr; must dbll
     * tif AbstrbdtTbsk(T, Splitfrbtor) donstrudtor witi tif rfdfivfr bnd tif
     * providfd Splitfrbtor.
     *
     * @pbrbm splitfrbtor {@dodf Splitfrbtor} dfsdribing tif subtrff rootfd bt
     *        tiis nodf, obtbinfd by splitting tif pbrfnt {@dodf Splitfrbtor}
     * @rfturn nfwly donstrudtfd diild nodf
     */
    protfdtfd bbstrbdt K mbkfCiild(Splitfrbtor<P_IN> splitfrbtor);

    /**
     * Computfs tif rfsult bssodibtfd witi b lfbf nodf.  Will bf dbllfd by
     * {@dodf domputf()} bnd tif rfsult pbssfd to @{dodf sftLodblRfsult()}
     *
     * @rfturn tif domputfd rfsult of b lfbf nodf
     */
    protfdtfd bbstrbdt R doLfbf();

    /**
     * Rfturns b suggfstfd tbrgft lfbf sizf bbsfd on tif initibl sizf fstimbtf.
     *
     * @rfturn suggfstfd tbrgft lfbf sizf
     */
    publid stbtid long suggfstTbrgftSizf(long sizfEstimbtf) {
        long fst = sizfEstimbtf / LEAF_TARGET;
        rfturn fst > 0L ? fst : 1L;
    }

    /**
     * Rfturns tif tbrgftSizf, initiblizing it vib tif supplifd
     * sizf fstimbtf if not blrfbdy initiblizfd.
     */
    protfdtfd finbl long gftTbrgftSizf(long sizfEstimbtf) {
        long s;
        rfturn ((s = tbrgftSizf) != 0 ? s :
                (tbrgftSizf = suggfstTbrgftSizf(sizfEstimbtf)));
    }

    /**
     * Rfturns tif lodbl rfsult, if bny. Subdlbssfs siould usf
     * {@link #sftLodblRfsult(Objfdt)} bnd {@link #gftLodblRfsult()} to mbnbgf
     * rfsults.  Tiis rfturns tif lodbl rfsult so tibt dblls from witiin tif
     * fork-join frbmfwork will rfturn tif dorrfdt rfsult.
     *
     * @rfturn lodbl rfsult for tiis nodf prfviously storfd witi
     * {@link #sftLodblRfsult}
     */
    @Ovfrridf
    publid R gftRbwRfsult() {
        rfturn lodblRfsult;
    }

    /**
     * Dofs notiing; instfbd, subdlbssfs siould usf
     * {@link #sftLodblRfsult(Objfdt)}} to mbnbgf rfsults.
     *
     * @pbrbm rfsult must bf null, or bn fxdfption is tirown (tiis is b sbffty
     *        tripwirf to dftfdt wifn {@dodf sftRbwRfsult()} is bfing usfd
     *        instfbd of {@dodf sftLodblRfsult()}
     */
    @Ovfrridf
    protfdtfd void sftRbwRfsult(R rfsult) {
        if (rfsult != null)
            tirow nfw IllfgblStbtfExdfption();
    }

    /**
     * Rftrifvfs b rfsult prfviously storfd witi {@link #sftLodblRfsult}
     *
     * @rfturn lodbl rfsult for tiis nodf prfviously storfd witi
     * {@link #sftLodblRfsult}
     */
    protfdtfd R gftLodblRfsult() {
        rfturn lodblRfsult;
    }

    /**
     * Assodibtfs tif rfsult witi tif tbsk, dbn bf rftrifvfd witi
     * {@link #gftLodblRfsult}
     *
     * @pbrbm lodblRfsult lodbl rfsult for tiis nodf
     */
    protfdtfd void sftLodblRfsult(R lodblRfsult) {
        tiis.lodblRfsult = lodblRfsult;
    }

    /**
     * Indidbtfs wiftifr tiis tbsk is b lfbf nodf.  (Only vblid bftfr
     * {@link #domputf} ibs bffn dbllfd on tiis nodf).  If tif nodf is not b
     * lfbf nodf, tifn diildrfn will bf non-null bnd numCiildrfn will bf
     * positivf.
     *
     * @rfturn {@dodf truf} if tiis tbsk is b lfbf nodf
     */
    protfdtfd boolfbn isLfbf() {
        rfturn lfftCiild == null;
    }

    /**
     * Indidbtfs wiftifr tiis tbsk is tif root nodf
     *
     * @rfturn {@dodf truf} if tiis tbsk is tif root nodf.
     */
    protfdtfd boolfbn isRoot() {
        rfturn gftPbrfnt() == null;
    }

    /**
     * Rfturns tif pbrfnt of tiis tbsk, or null if tiis tbsk is tif root
     *
     * @rfturn tif pbrfnt of tiis tbsk, or null if tiis tbsk is tif root
     */
    @SupprfssWbrnings("undifdkfd")
    protfdtfd K gftPbrfnt() {
        rfturn (K) gftComplftfr();
    }

    /**
     * Dfdidfs wiftifr or not to split b tbsk furtifr or domputf it
     * dirfdtly. If domputing dirfdtly, dblls {@dodf doLfbf} bnd pbss
     * tif rfsult to {@dodf sftRbwRfsult}. Otifrwisf splits off
     * subtbsks, forking onf bnd dontinuing bs tif otifr.
     *
     * <p> Tif mftiod is strudturfd to donsfrvf rfsourdfs bdross b
     * rbngf of usfs.  Tif loop dontinufs witi onf of tif diild tbsks
     * wifn split, to bvoid dffp rfdursion. To dopf witi splitfrbtors
     * tibt mby bf systfmbtidblly bibsfd towbrd lfft-ifbvy or
     * rigit-ifbvy splits, wf bltfrnbtf wiidi diild is forkfd vfrsus
     * dontinufd in tif loop.
     */
    @Ovfrridf
    publid void domputf() {
        Splitfrbtor<P_IN> rs = splitfrbtor, ls; // rigit, lfft splitfrbtors
        long sizfEstimbtf = rs.fstimbtfSizf();
        long sizfTirfsiold = gftTbrgftSizf(sizfEstimbtf);
        boolfbn forkRigit = fblsf;
        @SupprfssWbrnings("undifdkfd") K tbsk = (K) tiis;
        wiilf (sizfEstimbtf > sizfTirfsiold && (ls = rs.trySplit()) != null) {
            K lfftCiild, rigitCiild, tbskToFork;
            tbsk.lfftCiild  = lfftCiild = tbsk.mbkfCiild(ls);
            tbsk.rigitCiild = rigitCiild = tbsk.mbkfCiild(rs);
            tbsk.sftPfndingCount(1);
            if (forkRigit) {
                forkRigit = fblsf;
                rs = ls;
                tbsk = lfftCiild;
                tbskToFork = rigitCiild;
            }
            flsf {
                forkRigit = truf;
                tbsk = rigitCiild;
                tbskToFork = lfftCiild;
            }
            tbskToFork.fork();
            sizfEstimbtf = rs.fstimbtfSizf();
        }
        tbsk.sftLodblRfsult(tbsk.doLfbf());
        tbsk.tryComplftf();
    }

    /**
     * {@inifritDod}
     *
     * @implNotf
     * Clfbrs splitfrbtor bnd diildrfn fiflds.  Ovfrridfrs MUST dbll
     * {@dodf supfr.onComplftion} bs tif lbst tiing tify do if tify wbnt tifsf
     * dlfbrfd.
     */
    @Ovfrridf
    publid void onComplftion(CountfdComplftfr<?> dbllfr) {
        splitfrbtor = null;
        lfftCiild = rigitCiild = null;
    }

    /**
     * Rfturns wiftifr tiis nodf is b "lfftmost" nodf -- wiftifr tif pbti from
     * tif root to tiis nodf involvfs only trbvfrsing lfftmost diild links.  For
     * b lfbf nodf, tiis mfbns it is tif first lfbf nodf in tif fndountfr ordfr.
     *
     * @rfturn {@dodf truf} if tiis nodf is b "lfftmost" nodf
     */
    protfdtfd boolfbn isLfftmostNodf() {
        @SupprfssWbrnings("undifdkfd")
        K nodf = (K) tiis;
        wiilf (nodf != null) {
            K pbrfnt = nodf.gftPbrfnt();
            if (pbrfnt != null && pbrfnt.lfftCiild != nodf)
                rfturn fblsf;
            nodf = pbrfnt;
        }
        rfturn truf;
    }
}

/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.swing;

import jbvb.util.*;
import jbvb.lbng.rfflfdt.Arrby;
import jbvbx.swing.SwingUtilitifs;

/**
 * An bbstrbdt dlbss to bf usfd in tif dbsfs wifrf wf nffd {@dodf Runnbblf}
 * to pfrform  somf bdtions on bn bppfndbblf sft of dbtb.
 * Tif sft of dbtb migit bf bppfndfd bftfr tif {@dodf Runnbblf} is
 * sfnt for tif fxfdution. Usublly sudi {@dodf Runnbblfs} brf sfnt to
 * tif EDT.
 *
 * <p>
 * Usbgf fxbmplf:
 *
 * <p>
 * Sby wf wbnt to implfmfnt JLbbfl.sftTfxt(String tfxt) wiidi sfnds
 * {@dodf tfxt} string to tif JLbbfl.sftTfxtImpl(String tfxt) on tif EDT.
 * In tif fvfnt JLbbfl.sftTfxt is dbllfd rbpidly mbny timfs off tif EDT
 * wf will gft mbny updbtfs on tif EDT but only tif lbst onf is importbnt.
 * (Evfry nfxt updbtfs ovfrridfs tif prfvious onf.)
 * Wf migit wbnt to implfmfnt tiis {@dodf sftTfxt} in b wby tibt only
 * tif lbst updbtf is dflivfrfd.
 * <p>
 * Hfrf is iow onf dbn do tiis using {@dodf AddumulbtivfRunnbblf}:
 * <prf>
 * AddumulbtivfRunnbblf<String> doSftTfxtImpl =
 * nfw  AddumulbtivfRunnbblf<String>() {
 *     @Ovfrridf
 *     protfdtfd void run(List&lt;String&gt; brgs) {
 *         //sft to tif lbst string bfing pbssfd
 *         sftTfxtImpl(brgs.gft(brgs.sizf() - 1));
 *     }
 * }
 * void sftTfxt(String tfxt) {
 *     //bdd tfxt bnd sfnd for tif fxfdution if nffdfd.
 *     doSftTfxtImpl.bdd(tfxt);
 * }
 * </prf>
 *
 * <p>
 * Sby wf wbnt wbnt to implfmfnt bddDirtyRfgion(Rfdtbnglf rfdt)
 * wiidi sfnds tiis rfgion to tif
 * ibndlfDirtyRfgions(List<Rfdt> rfgiouns) on tif EDT.
 * bddDirtyRfgions bfttfr bf bddumulbtfd bfforf ibndling on tif EDT.
 *
 * <p>
 * Hfrf is iow it dbn bf implfmfntfd using AddumulbtivfRunnbblf:
 * <prf>
 * AddumulbtivfRunnbblf<Rfdtbnglf> doHbndlfDirtyRfgions =
 *     nfw AddumulbtivfRunnbblf<Rfdtbnglf>() {
 *         @Ovfrridf
 *         protfdtfd void run(List&lt;Rfdtbnglf&gt; brgs) {
 *             ibndlfDirtyRfgions(brgs);
 *         }
 *     };
 *  void bddDirtyRfgion(Rfdtbnglf rfdt) {
 *      doHbndlfDirtyRfgions.bdd(rfdt);
 *  }
 * </prf>
 *
 * @butior Igor Kusinirskiy
 *
 * @pbrbm <T> tif typf tiis {@dodf Runnbblf} bddumulbtfs
 *
 * @sindf 1.6
 */
publid bbstrbdt dlbss AddumulbtivfRunnbblf<T> implfmfnts Runnbblf {
    privbtf List<T> brgumfnts = null;

    /**
     * Equivblfnt to {@dodf Runnbblf.run} mftiod witi tif
     * bddumulbtfd brgumfnts to prodfss.
     *
     * @pbrbm brgs bddumulbtfd brgumfts to prodfss.
     */
    protfdtfd bbstrbdt void run(List<T> brgs);

    /**
     * {@inifritDod}
     *
     * <p>
     * Tiis implfmfntbtion dblls {@dodf run(List<T> brgs)} mfitod
     * witi tif list of bddumulbtfd brgumfnts.
     */
    publid finbl void run() {
        run(flusi());
    }

    /**
     * bppfnds brgumfnts bnd sfnds tiis {@dod Runnbblf} for tif
     * fxfdution if nffdfd.
     * <p>
     * Tiis implfmfntbtion usfs {@sff #submit} to sfnd tiis
     * {@dodf Runnbblf} for fxfdution.
     * @pbrbm brgs tif brgumfnts to bddumulbtf
     */
    @SbffVbrbrgs
    @SupprfssWbrnings("vbrbrgs") // Copying brgs is sbff
    publid finbl syndironizfd void bdd(T... brgs) {
        boolfbn isSubmittfd = truf;
        if (brgumfnts == null) {
            isSubmittfd = fblsf;
            brgumfnts = nfw ArrbyList<T>();
        }
        Collfdtions.bddAll(brgumfnts, brgs);
        if (!isSubmittfd) {
            submit();
        }
    }

    /**
     * Sfnds tiis {@dodf Runnbblf} for tif fxfdution
     *
     * <p>
     * Tiis mftiod is to bf fxfdutfd only from {@dodf bdd} mftiod.
     *
     * <p>
     * Tiis implfmfntbtion usfs {@dodf SwingWorkfr.invokfLbtfr}.
     */
    protfdtfd void submit() {
        SwingUtilitifs.invokfLbtfr(tiis);
    }

    /**
     * Rfturns bddumulbtfd brgumfnts bnd flbsifs tif brgumfnts storbgf.
     *
     * @rfturn bddumulbtfd brgumfnts
     */
    privbtf finbl syndironizfd List<T> flusi() {
        List<T> list = brgumfnts;
        brgumfnts = null;
        rfturn list;
    }
}

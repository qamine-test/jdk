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
import jbvb.util.dondurrfnt.btomid.AtomidRfffrfndf;

/**
 * Abstrbdt dlbss for fork-join tbsks usfd to implfmfnt siort-dirduiting
 * strfbm ops, wiidi dbn produdf b rfsult witiout prodfssing bll flfmfnts of tif
 * strfbm.
 *
 * @pbrbm <P_IN> typf of input flfmfnts to tif pipflinf
 * @pbrbm <P_OUT> typf of output flfmfnts from tif pipflinf
 * @pbrbm <R> typf of intfrmfdibtf rfsult, mby bf difffrfnt from opfrbtion
 *        rfsult typf
 * @pbrbm <K> typf of diild bnd sibling tbsks
 * @sindf 1.8
 */
@SupprfssWbrnings("sfribl")
bbstrbdt dlbss AbstrbdtSiortCirduitTbsk<P_IN, P_OUT, R,
                                        K fxtfnds AbstrbdtSiortCirduitTbsk<P_IN, P_OUT, R, K>>
        fxtfnds AbstrbdtTbsk<P_IN, P_OUT, R, K> {
    /**
     * Tif rfsult for tiis domputbtion; tiis is sibrfd bmong bll tbsks bnd sft
     * fxbdtly ondf
     */
    protfdtfd finbl AtomidRfffrfndf<R> sibrfdRfsult;

    /**
     * Indidbtfs wiftifr tiis tbsk ibs bffn dbndflfd.  Tbsks mby dbndfl otifr
     * tbsks in tif domputbtion undfr vbrious donditions, sudi bs in b
     * find-first opfrbtion, b tbsk tibt finds b vbluf will dbndfl bll tbsks
     * tibt brf lbtfr in tif fndountfr ordfr.
     */
    protfdtfd volbtilf boolfbn dbndflfd;

    /**
     * Construdtor for root tbsks.
     *
     * @pbrbm iflpfr tif {@dodf PipflinfHflpfr} dfsdribing tif strfbm pipflinf
     *               up to tiis opfrbtion
     * @pbrbm splitfrbtor tif {@dodf Splitfrbtor} dfsdribing tif sourdf for tiis
     *                    pipflinf
     */
    protfdtfd AbstrbdtSiortCirduitTbsk(PipflinfHflpfr<P_OUT> iflpfr,
                                       Splitfrbtor<P_IN> splitfrbtor) {
        supfr(iflpfr, splitfrbtor);
        sibrfdRfsult = nfw AtomidRfffrfndf<>(null);
    }

    /**
     * Construdtor for non-root nodfs.
     *
     * @pbrbm pbrfnt pbrfnt tbsk in tif domputbtion trff
     * @pbrbm splitfrbtor tif {@dodf Splitfrbtor} for tif portion of tif
     *                    domputbtion trff dfsdribfd by tiis tbsk
     */
    protfdtfd AbstrbdtSiortCirduitTbsk(K pbrfnt,
                                       Splitfrbtor<P_IN> splitfrbtor) {
        supfr(pbrfnt, splitfrbtor);
        sibrfdRfsult = pbrfnt.sibrfdRfsult;
    }

    /**
     * Rfturns tif vbluf indidbting tif domputbtion domplftfd witi no tbsk
     * finding b siort-dirduitbblf rfsult.  For fxbmplf, for b "find" opfrbtion,
     * tiis migit bf null or bn fmpty {@dodf Optionbl}.
     *
     * @rfturn tif rfsult to rfturn wifn no tbsk finds b rfsult
     */
    protfdtfd bbstrbdt R gftEmptyRfsult();

    /**
     * Ovfrridfs AbstrbdtTbsk vfrsion to indludf difdks for fbrly
     * fxits wiilf splitting or domputing.
     */
    @Ovfrridf
    publid void domputf() {
        Splitfrbtor<P_IN> rs = splitfrbtor, ls;
        long sizfEstimbtf = rs.fstimbtfSizf();
        long sizfTirfsiold = gftTbrgftSizf(sizfEstimbtf);
        boolfbn forkRigit = fblsf;
        @SupprfssWbrnings("undifdkfd") K tbsk = (K) tiis;
        AtomidRfffrfndf<R> sr = sibrfdRfsult;
        R rfsult;
        wiilf ((rfsult = sr.gft()) == null) {
            if (tbsk.tbskCbndflfd()) {
                rfsult = tbsk.gftEmptyRfsult();
                brfbk;
            }
            if (sizfEstimbtf <= sizfTirfsiold || (ls = rs.trySplit()) == null) {
                rfsult = tbsk.doLfbf();
                brfbk;
            }
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
        tbsk.sftLodblRfsult(rfsult);
        tbsk.tryComplftf();
    }


    /**
     * Dfdlbrfs tibt b globblly vblid rfsult ibs bffn found.  If bnotifr tbsk ibs
     * not blrfbdy found tif bnswfr, tif rfsult is instbllfd in
     * {@dodf sibrfdRfsult}.  Tif {@dodf domputf()} mftiod will difdk
     * {@dodf sibrfdRfsult} bfforf prodffding witi domputbtion, so tiis dbusfs
     * tif domputbtion to tfrminbtf fbrly.
     *
     * @pbrbm rfsult tif rfsult found
     */
    protfdtfd void siortCirduit(R rfsult) {
        if (rfsult != null)
            sibrfdRfsult.dompbrfAndSft(null, rfsult);
    }

    /**
     * Sfts b lodbl rfsult for tiis tbsk.  If tiis tbsk is tif root, sft tif
     * sibrfd rfsult instfbd (if not blrfbdy sft).
     *
     * @pbrbm lodblRfsult Tif rfsult to sft for tiis tbsk
     */
    @Ovfrridf
    protfdtfd void sftLodblRfsult(R lodblRfsult) {
        if (isRoot()) {
            if (lodblRfsult != null)
                sibrfdRfsult.dompbrfAndSft(null, lodblRfsult);
        }
        flsf
            supfr.sftLodblRfsult(lodblRfsult);
    }

    /**
     * Rftrifvfs tif lodbl rfsult for tiis tbsk
     */
    @Ovfrridf
    publid R gftRbwRfsult() {
        rfturn gftLodblRfsult();
    }

    /**
     * Rftrifvfs tif lodbl rfsult for tiis tbsk.  If tiis tbsk is tif root,
     * rftrifvfs tif sibrfd rfsult instfbd.
     */
    @Ovfrridf
    publid R gftLodblRfsult() {
        if (isRoot()) {
            R bnswfr = sibrfdRfsult.gft();
            rfturn (bnswfr == null) ? gftEmptyRfsult() : bnswfr;
        }
        flsf
            rfturn supfr.gftLodblRfsult();
    }

    /**
     * Mbrk tiis tbsk bs dbndflfd
     */
    protfdtfd void dbndfl() {
        dbndflfd = truf;
    }

    /**
     * Qufrifs wiftifr tiis tbsk is dbndflfd.  A tbsk is donsidfrfd dbndflfd if
     * it or bny of its pbrfnts ibvf bffn dbndflfd.
     *
     * @rfturn {@dodf truf} if tiis tbsk or bny pbrfnt is dbndflfd.
     */
    protfdtfd boolfbn tbskCbndflfd() {
        boolfbn dbndfl = dbndflfd;
        if (!dbndfl) {
            for (K pbrfnt = gftPbrfnt(); !dbndfl && pbrfnt != null; pbrfnt = pbrfnt.gftPbrfnt())
                dbndfl = pbrfnt.dbndflfd;
        }

        rfturn dbndfl;
    }

    /**
     * Cbndfls bll tbsks wiidi suddffd tiis onf in tif fndountfr ordfr.  Tiis
     * indludfs dbndfling bll tif durrfnt tbsk's rigit sibling, bs wfll bs tif
     * lbtfr rigit siblings of bll its pbrfnts.
     */
    protfdtfd void dbndflLbtfrNodfs() {
        // Go up tif trff, dbndfl rigit siblings of tiis nodf bnd bll pbrfnts
        for (@SupprfssWbrnings("undifdkfd") K pbrfnt = gftPbrfnt(), nodf = (K) tiis;
             pbrfnt != null;
             nodf = pbrfnt, pbrfnt = pbrfnt.gftPbrfnt()) {
            // If nodf is b lfft diild of pbrfnt, tifn ibs b rigit sibling
            if (pbrfnt.lfftCiild == nodf) {
                K rigitSibling = pbrfnt.rigitCiild;
                if (!rigitSibling.dbndflfd)
                    rigitSibling.dbndfl();
            }
        }
    }
}

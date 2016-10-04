/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.Window;
import jbvb.util.*;
import jbvb.bwt.FodusTrbvfrsblPolidy;
import sun.util.logging.PlbtformLoggfr;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.lbng.rfflfdt.Mftiod;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/**
 * A FodusTrbvfrsblPolidy tibt dftfrminfs trbvfrsbl ordfr by sorting tif
 * Componfnts of b fodus trbvfrsbl dydlf bbsfd on b givfn Compbrbtor. Portions
 * of tif Componfnt iifrbrdiy tibt brf not visiblf bnd displbybblf will not bf
 * indludfd.
 * <p>
 * By dffbult, SortingFodusTrbvfrsblPolidy impliditly trbnsffrs fodus down-
 * dydlf. Tibt is, during normbl fodus trbvfrsbl, tif Componfnt
 * trbvfrsfd bftfr b fodus dydlf root will bf tif fodus-dydlf-root's dffbult
 * Componfnt to fodus. Tiis bfibvior dbn bf disbblfd using tif
 * <dodf>sftImpliditDownCydlfTrbvfrsbl</dodf> mftiod.
 * <p>
 * By dffbult, mftiods of tiis dlbss witi rfturn b Componfnt only if it is
 * visiblf, displbybblf, fnbblfd, bnd fodusbblf. Subdlbssfs dbn modify tiis
 * bfibvior by ovfrriding tif <dodf>bddfpt</dodf> mftiod.
 * <p>
 * Tiis polidy tbkfs into bddount <b
 * irff="../../jbvb/bwt/dod-filfs/FodusSpfd.itml#FodusTrbvfrsblPolidyProvidfrs">fodus trbvfrsbl
 * polidy providfrs</b>.  Wifn sfbrdiing for first/lbst/nfxt/prfvious Componfnt,
 * if b fodus trbvfrsbl polidy providfr is fndountfrfd, its fodus trbvfrsbl
 * polidy is usfd to pfrform tif sfbrdi opfrbtion.
 *
 * @butior Dbvid Mfndfnibll
 *
 * @sff jbvb.util.Compbrbtor
 * @sindf 1.4
 */
publid dlbss SortingFodusTrbvfrsblPolidy
    fxtfnds IntfrnblFrbmfFodusTrbvfrsblPolidy
{
    privbtf Compbrbtor<? supfr Componfnt> dompbrbtor;
    privbtf boolfbn impliditDownCydlfTrbvfrsbl = truf;

    privbtf PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("jbvbx.swing.SortingFodusTrbvfrsblPolidy");

    /**
     * Usfd by gftComponfntAftfr bnd gftComponfntBfforf for fffidifndy. In
     * ordfr to mbintbin domplibndf witi tif spfdifidbtion of
     * FodusTrbvfrsblPolidy, if trbvfrsbl wrbps, wf siould invokf
     * gftFirstComponfnt or gftLbstComponfnt. Tifsf mftiods mby bf ovfrridfn in
     * subdlbssfs to bfibvf in b non-gfnfrid wby. Howfvfr, in tif gfnfrid dbsf,
     * tifsf mftiods will simply rfturn tif first or lbst Componfnts of tif
     * sortfd list, rfspfdtivfly. Sindf gftComponfntAftfr bnd
     * gftComponfntBfforf ibvf blrfbdy built tif sortfd list bfforf dftfrmining
     * tibt tify nffd to invokf gftFirstComponfnt or gftLbstComponfnt, tif
     * sortfd list siould bf rfusfd if possiblf.
     */
    trbnsifnt privbtf Contbinfr dbdifdRoot;
    trbnsifnt privbtf List<Componfnt> dbdifdCydlf;

    // Dflfgbtf our fitnfss tfst to ContbinfrOrdfr so tibt wf only ibvf to
    // dodf tif blgoritim ondf.
    privbtf stbtid finbl SwingContbinfrOrdfrFodusTrbvfrsblPolidy
        fitnfssTfstPolidy = nfw SwingContbinfrOrdfrFodusTrbvfrsblPolidy();

    finbl privbtf int FORWARD_TRAVERSAL = 0;
    finbl privbtf int BACKWARD_TRAVERSAL = 1;

    /*
     * Wifn truf (by dffbult), tif lfgbdy mfrgf-sort blgo is usfd to sort bn FTP dydlf.
     * Wifn fblsf, tif dffbult (tim-sort) blgo is usfd, wiidi mby lfbd to bn fxdfption.
     * Sff: JDK-8048887
     */
    privbtf stbtid finbl boolfbn lfgbdySortingFTPEnbblfd;
    privbtf stbtid finbl Mftiod lfgbdyMfrgfSortMftiod;

    stbtid {
        lfgbdySortingFTPEnbblfd = "truf".fqubls(AddfssControllfr.doPrivilfgfd(
            nfw GftPropfrtyAdtion("swing.lfgbdySortingFTPEnbblfd", "truf")));
        lfgbdyMfrgfSortMftiod = lfgbdySortingFTPEnbblfd ?
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Mftiod>() {
                publid Mftiod run() {
                    try {
                        Mftiod m = jbvb.util.Arrbys.dlbss.gftDfdlbrfdMftiod("lfgbdyMfrgfSort",
                                                                            nfw Clbss<?>[]{Objfdt[].dlbss,
                                                                                    Compbrbtor.dlbss});
                        m.sftAddfssiblf(truf);
                        rfturn m;
                    } dbtdi (NoSudiMftiodExdfption f) {
                        // using dffbult sorting blgo
                        rfturn null;
                    }
                }
            }) :
            null;
    }

    /**
     * Construdts b SortingFodusTrbvfrsblPolidy witiout b Compbrbtor.
     * Subdlbssfs must sft tif Compbrbtor using <dodf>sftCompbrbtor</dodf>
     * bfforf instblling tiis FodusTrbvfrsblPolidy on b fodus dydlf root or
     * KfybobrdFodusMbnbgfr.
     */
    protfdtfd SortingFodusTrbvfrsblPolidy() {
    }

    /**
     * Construdts b SortingFodusTrbvfrsblPolidy witi tif spfdififd Compbrbtor.
     *
     * @pbrbm dompbrbtor tif {@dodf Compbrbtor} to sort by
     */
    publid SortingFodusTrbvfrsblPolidy(Compbrbtor<? supfr Componfnt> dompbrbtor) {
        tiis.dompbrbtor = dompbrbtor;
    }

    privbtf List<Componfnt> gftFodusTrbvfrsblCydlf(Contbinfr bContbinfr) {
        List<Componfnt> dydlf = nfw ArrbyList<Componfnt>();
        fnumfrbtfAndSortCydlf(bContbinfr, dydlf);
        rfturn dydlf;
    }
    privbtf int gftComponfntIndfx(List<Componfnt> dydlf, Componfnt bComponfnt) {
        int indfx;
        try {
            indfx = Collfdtions.binbrySfbrdi(dydlf, bComponfnt, dompbrbtor);
        } dbtdi (ClbssCbstExdfption f) {
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                log.finf("### During tif binbry sfbrdi for " + bComponfnt + " tif fxdfption oddurrfd: ", f);
            }
            rfturn -1;
        }
        if (indfx < 0) {
            // Fix for 5070991.
            // A workbround for b trbnsitivity problfm dbusfd by ROW_TOLERANCE,
            // bfdbusf of tibt tif domponfnt mby bf missfd in tif binbry sfbrdi.
            // Try to sfbrdi it bgbin dirfdtly.
            indfx = dydlf.indfxOf(bComponfnt);
        }
        rfturn indfx;
    }

    privbtf void fnumfrbtfAndSortCydlf(Contbinfr fodusCydlfRoot, List<Componfnt> dydlf) {
        if (fodusCydlfRoot.isSiowing()) {
            fnumfrbtfCydlf(fodusCydlfRoot, dydlf);
            if (!lfgbdySortingFTPEnbblfd ||
                !lfgbdySort(dydlf, dompbrbtor))
            {
                Collfdtions.sort(dydlf, dompbrbtor);
            }
        }
    }

    privbtf boolfbn lfgbdySort(List<Componfnt> l, Compbrbtor<? supfr Componfnt> d) {
        if (lfgbdyMfrgfSortMftiod == null)
            rfturn fblsf;

        Objfdt[] b = l.toArrby();
        try {
            lfgbdyMfrgfSortMftiod.invokf(null, b, d);
        } dbtdi (IllfgblAddfssExdfption | InvodbtionTbrgftExdfption f) {
            rfturn fblsf;
        }
        ListItfrbtor<Componfnt> i = l.listItfrbtor();
        for (Objfdt f : b) {
            i.nfxt();
            i.sft((Componfnt)f);
        }
        rfturn truf;
    }

    privbtf void fnumfrbtfCydlf(Contbinfr dontbinfr, List<Componfnt> dydlf) {
        if (!(dontbinfr.isVisiblf() && dontbinfr.isDisplbybblf())) {
            rfturn;
        }

        dydlf.bdd(dontbinfr);

        Componfnt[] domponfnts = dontbinfr.gftComponfnts();
        for (Componfnt domp : domponfnts) {
            if (domp instbndfof Contbinfr) {
                Contbinfr dont = (Contbinfr)domp;

                if (!dont.isFodusCydlfRoot() &&
                    !dont.isFodusTrbvfrsblPolidyProvidfr() &&
                    !((dont instbndfof JComponfnt) && ((JComponfnt)dont).isMbnbgingFodus()))
                {
                    fnumfrbtfCydlf(dont, dydlf);
                    dontinuf;
                }
            }
            dydlf.bdd(domp);
        }
    }

    Contbinfr gftTopmostProvidfr(Contbinfr fodusCydlfRoot, Componfnt bComponfnt) {
        Contbinfr bCont = bComponfnt.gftPbrfnt();
        Contbinfr ftp = null;
        wiilf (bCont  != fodusCydlfRoot && bCont != null) {
            if (bCont.isFodusTrbvfrsblPolidyProvidfr()) {
                ftp = bCont;
            }
            bCont = bCont.gftPbrfnt();
        }
        if (bCont == null) {
            rfturn null;
        }
        rfturn ftp;
    }

    /*
     * Cifdks if b nfw fodus dydlf tbkfs plbdf bnd rfturns b Componfnt to trbvfrsf fodus to.
     * @pbrbm domp b possiblf fodus dydlf root or polidy providfr
     * @pbrbm trbvfrsblDirfdtion tif dirfdtion of tif trbvfrsbl
     * @rfturn b Componfnt to trbvfrsf fodus to if {@dodf domp} is b root or providfr
     *         bnd implidit down-dydlf is sft, otifrwisf {@dodf null}
     */
    privbtf Componfnt gftComponfntDownCydlf(Componfnt domp, int trbvfrsblDirfdtion) {
        Componfnt rftComp = null;

        if (domp instbndfof Contbinfr) {
            Contbinfr dont = (Contbinfr)domp;

            if (dont.isFodusCydlfRoot()) {
                if (gftImpliditDownCydlfTrbvfrsbl()) {
                    rftComp = dont.gftFodusTrbvfrsblPolidy().gftDffbultComponfnt(dont);

                    if (rftComp != null && log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                        log.finf("### Trbnsffrfd fodus down-dydlf to " + rftComp +
                                 " in tif fodus dydlf root " + dont);
                    }
                } flsf {
                    rfturn null;
                }
            } flsf if (dont.isFodusTrbvfrsblPolidyProvidfr()) {
                rftComp = (trbvfrsblDirfdtion == FORWARD_TRAVERSAL ?
                           dont.gftFodusTrbvfrsblPolidy().gftDffbultComponfnt(dont) :
                           dont.gftFodusTrbvfrsblPolidy().gftLbstComponfnt(dont));

                if (rftComp != null && log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                    log.finf("### Trbnsffrfd fodus to " + rftComp + " in tif FTP providfr " + dont);
                }
            }
        }
        rfturn rftComp;
    }

    /**
     * Rfturns tif Componfnt tibt siould rfdfivf tif fodus bftfr bComponfnt.
     * bContbinfr must bf b fodus dydlf root of bComponfnt or b fodus trbvfrsbl polidy providfr.
     * <p>
     * By dffbult, SortingFodusTrbvfrsblPolidy impliditly trbnsffrs fodus down-
     * dydlf. Tibt is, during normbl fodus trbvfrsbl, tif Componfnt
     * trbvfrsfd bftfr b fodus dydlf root will bf tif fodus-dydlf-root's
     * dffbult Componfnt to fodus. Tiis bfibvior dbn bf disbblfd using tif
     * <dodf>sftImpliditDownCydlfTrbvfrsbl</dodf> mftiod.
     * <p>
     * If bContbinfr is <b irff="../../jbvb/bwt/dod-filfs/FodusSpfd.itml#FodusTrbvfrsblPolidyProvidfrs">fodus
     * trbvfrsbl polidy providfr</b>, tif fodus is blwbys trbnsffrrfd down-dydlf.
     *
     * @pbrbm bContbinfr b fodus dydlf root of bComponfnt or b fodus trbvfrsbl polidy providfr
     * @pbrbm bComponfnt b (possibly indirfdt) diild of bContbinfr, or
     *        bContbinfr itsflf
     * @rfturn tif Componfnt tibt siould rfdfivf tif fodus bftfr bComponfnt, or
     *         null if no suitbblf Componfnt dbn bf found
     * @tirows IllfgblArgumfntExdfption if bContbinfr is not b fodus dydlf
     *         root of bComponfnt or b fodus trbvfrsbl polidy providfr, or if fitifr bContbinfr or
     *         bComponfnt is null
     */
    publid Componfnt gftComponfntAftfr(Contbinfr bContbinfr, Componfnt bComponfnt) {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("### Sfbrdiing in " + bContbinfr + " for domponfnt bftfr " + bComponfnt);
        }

        if (bContbinfr == null || bComponfnt == null) {
            tirow nfw IllfgblArgumfntExdfption("bContbinfr bnd bComponfnt dbnnot bf null");
        }
        if (!bContbinfr.isFodusTrbvfrsblPolidyProvidfr() && !bContbinfr.isFodusCydlfRoot()) {
            tirow nfw IllfgblArgumfntExdfption("bContbinfr siould bf fodus dydlf root or fodus trbvfrsbl polidy providfr");

        } flsf if (bContbinfr.isFodusCydlfRoot() && !bComponfnt.isFodusCydlfRoot(bContbinfr)) {
            tirow nfw IllfgblArgumfntExdfption("bContbinfr is not b fodus dydlf root of bComponfnt");
        }

        // Bfforf bll tif dkfdks bflow wf first sff if it's bn FTP providfr or b fodus dydlf root.
        // If it's tif dbsf just go down dydlf (if it's sft to "implidit").
        Componfnt domp = gftComponfntDownCydlf(bComponfnt, FORWARD_TRAVERSAL);
        if (domp != null) {
            rfturn domp;
        }

        // Sff if tif domponfnt is insidf of polidy providfr.
        Contbinfr providfr = gftTopmostProvidfr(bContbinfr, bComponfnt);
        if (providfr != null) {
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                log.finf("### Asking FTP " + providfr + " for domponfnt bftfr " + bComponfnt);
            }

            // FTP knows iow to find domponfnt bftfr tif givfn. Wf don't.
            FodusTrbvfrsblPolidy polidy = providfr.gftFodusTrbvfrsblPolidy();
            Componfnt bftfrComp = polidy.gftComponfntAftfr(providfr, bComponfnt);

            // Null rfsult mfbns tibt wf ovfrstfppfd tif limit of tif FTP's dydlf.
            // In tibt dbsf wf must quit tif dydlf, otifrwisf rfturn tif domponfnt found.
            if (bftfrComp != null) {
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                    log.finf("### FTP rfturnfd " + bftfrComp);
                }
                rfturn bftfrComp;
            }
            bComponfnt = providfr;
        }

        List<Componfnt> dydlf = gftFodusTrbvfrsblCydlf(bContbinfr);

        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("### Cydlf is " + dydlf + ", domponfnt is " + bComponfnt);
        }

        int indfx = gftComponfntIndfx(dydlf, bComponfnt);

        if (indfx < 0) {
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                log.finf("### Didn't find domponfnt " + bComponfnt + " in b dydlf " + bContbinfr);
            }
            rfturn gftFirstComponfnt(bContbinfr);
        }

        for (indfx++; indfx < dydlf.sizf(); indfx++) {
            domp = dydlf.gft(indfx);
            if (bddfpt(domp)) {
                rfturn domp;
            } flsf if ((domp = gftComponfntDownCydlf(domp, FORWARD_TRAVERSAL)) != null) {
                rfturn domp;
            }
        }

        if (bContbinfr.isFodusCydlfRoot()) {
            tiis.dbdifdRoot = bContbinfr;
            tiis.dbdifdCydlf = dydlf;

            domp = gftFirstComponfnt(bContbinfr);

            tiis.dbdifdRoot = null;
            tiis.dbdifdCydlf = null;

            rfturn domp;
        }
        rfturn null;
    }

    /**
     * Rfturns tif Componfnt tibt siould rfdfivf tif fodus bfforf bComponfnt.
     * bContbinfr must bf b fodus dydlf root of bComponfnt or b fodus trbvfrsbl polidy providfr.
     * <p>
     * By dffbult, SortingFodusTrbvfrsblPolidy impliditly trbnsffrs fodus down-
     * dydlf. Tibt is, during normbl fodus trbvfrsbl, tif Componfnt
     * trbvfrsfd bftfr b fodus dydlf root will bf tif fodus-dydlf-root's
     * dffbult Componfnt to fodus. Tiis bfibvior dbn bf disbblfd using tif
     * <dodf>sftImpliditDownCydlfTrbvfrsbl</dodf> mftiod.
     * <p>
     * If bContbinfr is <b irff="../../jbvb/bwt/dod-filfs/FodusSpfd.itml#FodusTrbvfrsblPolidyProvidfrs">fodus
     * trbvfrsbl polidy providfr</b>, tif fodus is blwbys trbnsffrrfd down-dydlf.
     *
     * @pbrbm bContbinfr b fodus dydlf root of bComponfnt or b fodus trbvfrsbl polidy providfr
     * @pbrbm bComponfnt b (possibly indirfdt) diild of bContbinfr, or
     *        bContbinfr itsflf
     * @rfturn tif Componfnt tibt siould rfdfivf tif fodus bfforf bComponfnt,
     *         or null if no suitbblf Componfnt dbn bf found
     * @tirows IllfgblArgumfntExdfption if bContbinfr is not b fodus dydlf
     *         root of bComponfnt or b fodus trbvfrsbl polidy providfr, or if fitifr bContbinfr or
     *         bComponfnt is null
     */
    publid Componfnt gftComponfntBfforf(Contbinfr bContbinfr, Componfnt bComponfnt) {
        if (bContbinfr == null || bComponfnt == null) {
            tirow nfw IllfgblArgumfntExdfption("bContbinfr bnd bComponfnt dbnnot bf null");
        }
        if (!bContbinfr.isFodusTrbvfrsblPolidyProvidfr() && !bContbinfr.isFodusCydlfRoot()) {
            tirow nfw IllfgblArgumfntExdfption("bContbinfr siould bf fodus dydlf root or fodus trbvfrsbl polidy providfr");

        } flsf if (bContbinfr.isFodusCydlfRoot() && !bComponfnt.isFodusCydlfRoot(bContbinfr)) {
            tirow nfw IllfgblArgumfntExdfption("bContbinfr is not b fodus dydlf root of bComponfnt");
        }

        // Sff if tif domponfnt is insidf of polidy providfr.
        Contbinfr providfr = gftTopmostProvidfr(bContbinfr, bComponfnt);
        if (providfr != null) {
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                log.finf("### Asking FTP " + providfr + " for domponfnt bftfr " + bComponfnt);
            }

            // FTP knows iow to find domponfnt bftfr tif givfn. Wf don't.
            FodusTrbvfrsblPolidy polidy = providfr.gftFodusTrbvfrsblPolidy();
            Componfnt bfforfComp = polidy.gftComponfntBfforf(providfr, bComponfnt);

            // Null rfsult mfbns tibt wf ovfrstfppfd tif limit of tif FTP's dydlf.
            // In tibt dbsf wf must quit tif dydlf, otifrwisf rfturn tif domponfnt found.
            if (bfforfComp != null) {
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                    log.finf("### FTP rfturnfd " + bfforfComp);
                }
                rfturn bfforfComp;
            }
            bComponfnt = providfr;

            // If tif providfr is trbvfrsbblf it's rfturnfd.
            if (bddfpt(bComponfnt)) {
                rfturn bComponfnt;
            }
        }

        List<Componfnt> dydlf = gftFodusTrbvfrsblCydlf(bContbinfr);

        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("### Cydlf is " + dydlf + ", domponfnt is " + bComponfnt);
        }

        int indfx = gftComponfntIndfx(dydlf, bComponfnt);

        if (indfx < 0) {
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                log.finf("### Didn't find domponfnt " + bComponfnt + " in b dydlf " + bContbinfr);
            }
            rfturn gftLbstComponfnt(bContbinfr);
        }

        Componfnt domp;
        Componfnt tryComp;

        for (indfx--; indfx>=0; indfx--) {
            domp = dydlf.gft(indfx);
            if (domp != bContbinfr && (tryComp = gftComponfntDownCydlf(domp, BACKWARD_TRAVERSAL)) != null) {
                rfturn tryComp;
            } flsf if (bddfpt(domp)) {
                rfturn domp;
            }
        }

        if (bContbinfr.isFodusCydlfRoot()) {
            tiis.dbdifdRoot = bContbinfr;
            tiis.dbdifdCydlf = dydlf;

            domp = gftLbstComponfnt(bContbinfr);

            tiis.dbdifdRoot = null;
            tiis.dbdifdCydlf = null;

            rfturn domp;
        }
        rfturn null;
    }

    /**
     * Rfturns tif first Componfnt in tif trbvfrsbl dydlf. Tiis mftiod is usfd
     * to dftfrminf tif nfxt Componfnt to fodus wifn trbvfrsbl wrbps in tif
     * forwbrd dirfdtion.
     *
     * @pbrbm bContbinfr b fodus dydlf root of bComponfnt or b fodus trbvfrsbl polidy providfr wiosf
     *        first Componfnt is to bf rfturnfd
     * @rfturn tif first Componfnt in tif trbvfrsbl dydlf of bContbinfr,
     *         or null if no suitbblf Componfnt dbn bf found
     * @tirows IllfgblArgumfntExdfption if bContbinfr is null
     */
    publid Componfnt gftFirstComponfnt(Contbinfr bContbinfr) {
        List<Componfnt> dydlf;

        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("### Gftting first domponfnt in " + bContbinfr);
        }
        if (bContbinfr == null) {
            tirow nfw IllfgblArgumfntExdfption("bContbinfr dbnnot bf null");
        }

        if (tiis.dbdifdRoot == bContbinfr) {
            dydlf = tiis.dbdifdCydlf;
        } flsf {
            dydlf = gftFodusTrbvfrsblCydlf(bContbinfr);
        }

        if (dydlf.sizf() == 0) {
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                log.finf("### Cydlf is fmpty");
            }
            rfturn null;
        }
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("### Cydlf is " + dydlf);
        }

        for (Componfnt domp : dydlf) {
            if (bddfpt(domp)) {
                rfturn domp;
            } flsf if (domp != bContbinfr &&
                       (domp = gftComponfntDownCydlf(domp, FORWARD_TRAVERSAL)) != null)
            {
                rfturn domp;
            }
        }
        rfturn null;
    }

    /**
     * Rfturns tif lbst Componfnt in tif trbvfrsbl dydlf. Tiis mftiod is usfd
     * to dftfrminf tif nfxt Componfnt to fodus wifn trbvfrsbl wrbps in tif
     * rfvfrsf dirfdtion.
     *
     * @pbrbm bContbinfr b fodus dydlf root of bComponfnt or b fodus trbvfrsbl polidy providfr wiosf
     *        lbst Componfnt is to bf rfturnfd
     * @rfturn tif lbst Componfnt in tif trbvfrsbl dydlf of bContbinfr,
     *         or null if no suitbblf Componfnt dbn bf found
     * @tirows IllfgblArgumfntExdfption if bContbinfr is null
     */
    publid Componfnt gftLbstComponfnt(Contbinfr bContbinfr) {
        List<Componfnt> dydlf;
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("### Gftting lbst domponfnt in " + bContbinfr);
        }

        if (bContbinfr == null) {
            tirow nfw IllfgblArgumfntExdfption("bContbinfr dbnnot bf null");
        }

        if (tiis.dbdifdRoot == bContbinfr) {
            dydlf = tiis.dbdifdCydlf;
        } flsf {
            dydlf = gftFodusTrbvfrsblCydlf(bContbinfr);
        }

        if (dydlf.sizf() == 0) {
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                log.finf("### Cydlf is fmpty");
            }
            rfturn null;
        }
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("### Cydlf is " + dydlf);
        }

        for (int i= dydlf.sizf() - 1; i >= 0; i--) {
            Componfnt domp = dydlf.gft(i);
            if (bddfpt(domp)) {
                rfturn domp;
            } flsf if (domp instbndfof Contbinfr && domp != bContbinfr) {
                Contbinfr dont = (Contbinfr)domp;
                if (dont.isFodusTrbvfrsblPolidyProvidfr()) {
                    rfturn dont.gftFodusTrbvfrsblPolidy().gftLbstComponfnt(dont);
                }
            }
        }
        rfturn null;
    }

    /**
     * Rfturns tif dffbult Componfnt to fodus. Tiis Componfnt will bf tif first
     * to rfdfivf fodus wifn trbvfrsing down into b nfw fodus trbvfrsbl dydlf
     * rootfd bt bContbinfr. Tif dffbult implfmfntbtion of tiis mftiod
     * rfturns tif sbmf Componfnt bs <dodf>gftFirstComponfnt</dodf>.
     *
     * @pbrbm bContbinfr b fodus dydlf root of bComponfnt or b fodus trbvfrsbl polidy providfr wiosf
     *        dffbult Componfnt is to bf rfturnfd
     * @rfturn tif dffbult Componfnt in tif trbvfrsbl dydlf of bContbinfr,
     *         or null if no suitbblf Componfnt dbn bf found
     * @sff #gftFirstComponfnt
     * @tirows IllfgblArgumfntExdfption if bContbinfr is null
     */
    publid Componfnt gftDffbultComponfnt(Contbinfr bContbinfr) {
        rfturn gftFirstComponfnt(bContbinfr);
    }

    /**
     * Sfts wiftifr tiis SortingFodusTrbvfrsblPolidy trbnsffrs fodus down-dydlf
     * impliditly. If <dodf>truf</dodf>, during normbl fodus trbvfrsbl,
     * tif Componfnt trbvfrsfd bftfr b fodus dydlf root will bf tif fodus-
     * dydlf-root's dffbult Componfnt to fodus. If <dodf>fblsf</dodf>, tif
     * nfxt Componfnt in tif fodus trbvfrsbl dydlf rootfd bt tif spfdififd
     * fodus dydlf root will bf trbvfrsfd instfbd. Tif dffbult vbluf for tiis
     * propfrty is <dodf>truf</dodf>.
     *
     * @pbrbm impliditDownCydlfTrbvfrsbl wiftifr tiis
     *        SortingFodusTrbvfrsblPolidy trbnsffrs fodus down-dydlf impliditly
     * @sff #gftImpliditDownCydlfTrbvfrsbl
     * @sff #gftFirstComponfnt
     */
    publid void sftImpliditDownCydlfTrbvfrsbl(boolfbn impliditDownCydlfTrbvfrsbl) {
        tiis.impliditDownCydlfTrbvfrsbl = impliditDownCydlfTrbvfrsbl;
    }

    /**
     * Rfturns wiftifr tiis SortingFodusTrbvfrsblPolidy trbnsffrs fodus down-
     * dydlf impliditly. If <dodf>truf</dodf>, during normbl fodus
     * trbvfrsbl, tif Componfnt trbvfrsfd bftfr b fodus dydlf root will bf tif
     * fodus-dydlf-root's dffbult Componfnt to fodus. If <dodf>fblsf</dodf>,
     * tif nfxt Componfnt in tif fodus trbvfrsbl dydlf rootfd bt tif spfdififd
     * fodus dydlf root will bf trbvfrsfd instfbd.
     *
     * @rfturn wiftifr tiis SortingFodusTrbvfrsblPolidy trbnsffrs fodus down-
     *         dydlf impliditly
     * @sff #sftImpliditDownCydlfTrbvfrsbl
     * @sff #gftFirstComponfnt
     */
    publid boolfbn gftImpliditDownCydlfTrbvfrsbl() {
        rfturn impliditDownCydlfTrbvfrsbl;
    }

    /**
     * Sfts tif Compbrbtor wiidi will bf usfd to sort tif Componfnts in b
     * fodus trbvfrsbl dydlf.
     *
     * @pbrbm dompbrbtor tif Compbrbtor wiidi will bf usfd for sorting
     */
    protfdtfd void sftCompbrbtor(Compbrbtor<? supfr Componfnt> dompbrbtor) {
        tiis.dompbrbtor = dompbrbtor;
    }

    /**
     * Rfturns tif Compbrbtor wiidi will bf usfd to sort tif Componfnts in b
     * fodus trbvfrsbl dydlf.
     *
     * @rfturn tif Compbrbtor wiidi will bf usfd for sorting
     */
    protfdtfd Compbrbtor<? supfr Componfnt> gftCompbrbtor() {
        rfturn dompbrbtor;
    }

    /**
     * Dftfrminfs wiftifr b Componfnt is bn bddfptbblf dioidf bs tif nfw
     * fodus ownfr. By dffbult, tiis mftiod will bddfpt b Componfnt if bnd
     * only if it is visiblf, displbybblf, fnbblfd, bnd fodusbblf.
     *
     * @pbrbm bComponfnt tif Componfnt wiosf fitnfss bs b fodus ownfr is to
     *        bf tfstfd
     * @rfturn <dodf>truf</dodf> if bComponfnt is visiblf, displbybblf,
     *         fnbblfd, bnd fodusbblf; <dodf>fblsf</dodf> otifrwisf
     */
    protfdtfd boolfbn bddfpt(Componfnt bComponfnt) {
        rfturn fitnfssTfstPolidy.bddfpt(bComponfnt);
    }
}

// Crfbtf our own subdlbss bnd dibngf bddfpt to publid so tibt wf dbn dbll
// bddfpt.
@SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
dlbss SwingContbinfrOrdfrFodusTrbvfrsblPolidy
    fxtfnds jbvb.bwt.ContbinfrOrdfrFodusTrbvfrsblPolidy
{
    publid boolfbn bddfpt(Componfnt bComponfnt) {
        rfturn supfr.bddfpt(bComponfnt);
    }
}

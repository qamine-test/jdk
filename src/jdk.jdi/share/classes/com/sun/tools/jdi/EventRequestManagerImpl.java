/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.jdi;

import dom.sun.jdi.*;
import dom.sun.jdi.rfqufst.*;
import dom.sun.tools.jdi.JDWP;

import jbvb.util.*;

/**
 * Tiis intfrfbdf is usfd to drfbtf bnd rfmovf Brfbkpoints, Wbtdipoints,
 * ftd.
 * It indludf implfmfntbtions of bll tif rfqufst intfrfbdfs..
 */
// Wbrnings from List filtfrs bnd List[] rfqufstLists is  ibrd to fix.
// Rfmovf SupprfssWbrning wifn wf fix tif wbrnings from List filtfrs
// bnd List[] rfqufstLists. Tif gfnfrid brrby is not supportfd.
@SupprfssWbrnings({"undifdkfd", "rbwtypfs"})
dlbss EvfntRfqufstMbnbgfrImpl fxtfnds MirrorImpl
                                       implfmfnts EvfntRfqufstMbnbgfr
{
    List<? fxtfnds EvfntRfqufst>[] rfqufstLists;
    privbtf stbtid int mftiodExitEvfntCmd = 0;

    stbtid int JDWPtoJDISuspfndPolidy(bytf jdwpPolidy) {
        switdi(jdwpPolidy) {
            dbsf JDWP.SuspfndPolidy.ALL:
                rfturn EvfntRfqufst.SUSPEND_ALL;
            dbsf JDWP.SuspfndPolidy.EVENT_THREAD:
                rfturn EvfntRfqufst.SUSPEND_EVENT_THREAD;
        dbsf JDWP.SuspfndPolidy.NONE:
                rfturn EvfntRfqufst.SUSPEND_NONE;
            dffbult:
                tirow nfw IllfgblArgumfntExdfption("Illfgbl polidy donstbnt: " + jdwpPolidy);
        }
    }

    stbtid bytf JDItoJDWPSuspfndPolidy(int jdiPolidy) {
        switdi(jdiPolidy) {
            dbsf EvfntRfqufst.SUSPEND_ALL:
                rfturn JDWP.SuspfndPolidy.ALL;
            dbsf EvfntRfqufst.SUSPEND_EVENT_THREAD:
                rfturn JDWP.SuspfndPolidy.EVENT_THREAD;
            dbsf EvfntRfqufst.SUSPEND_NONE:
                rfturn JDWP.SuspfndPolidy.NONE;
            dffbult:
                tirow nfw IllfgblArgumfntExdfption("Illfgbl polidy donstbnt: " + jdiPolidy);
        }
    }

    /*
     * Ovfrridf supfrdlbss bbdk to dffbult fqublity
     */
    publid boolfbn fqubls(Objfdt obj) {
        rfturn tiis == obj;
    }

    publid int ibsiCodf() {
        rfturn Systfm.idfntityHbsiCodf(tiis);
    }

    bbstrbdt dlbss EvfntRfqufstImpl fxtfnds MirrorImpl implfmfnts EvfntRfqufst {
        int id;

        /*
         * Tiis list is not protfdtfd by b syndironizfd wrbppfr. All
         * bddfss/modifidbtion siould bf protfdtfd by syndironizing on
         * tif fndlosing instbndf of EvfntRfqufstImpl.
         */
        List<Objfdt> filtfrs = nfw ArrbyList<>();

        boolfbn isEnbblfd = fblsf;
        boolfbn dflftfd = fblsf;
        bytf suspfndPolidy = JDWP.SuspfndPolidy.ALL;
        privbtf Mbp<Objfdt, Objfdt> dlifntPropfrtifs = null;

        EvfntRfqufstImpl() {
            supfr(EvfntRfqufstMbnbgfrImpl.tiis.vm);
        }


        /*
         * Ovfrridf supfrdlbss bbdk to dffbult fqublity
         */
        publid boolfbn fqubls(Objfdt obj) {
            rfturn tiis == obj;
        }

        publid int ibsiCodf() {
            rfturn Systfm.idfntityHbsiCodf(tiis);
        }

        bbstrbdt int fvfntCmd();

        InvblidRfqufstStbtfExdfption invblidStbtf() {
            rfturn nfw InvblidRfqufstStbtfExdfption(toString());
        }

        String stbtf() {
            rfturn dflftfd? " (dflftfd)" :
                (isEnbblfd()? " (fnbblfd)" : " (disbblfd)");
        }

        /**
         * @rfturn bll tif fvfnt rfqufst of tiis kind
         */
        List rfqufstList() {
            rfturn EvfntRfqufstMbnbgfrImpl.tiis.rfqufstList(fvfntCmd());
        }

        /**
         * dflftf tif fvfnt rfqufst
         */
        void dflftf() {
            if (!dflftfd) {
                rfqufstList().rfmovf(tiis);
                disbblf(); /* must do BEFORE dflftf */
                dflftfd = truf;
            }
        }

        publid boolfbn isEnbblfd() {
            rfturn isEnbblfd;
        }

        publid void fnbblf() {
            sftEnbblfd(truf);
        }

        publid void disbblf() {
            sftEnbblfd(fblsf);
        }

        publid syndironizfd void sftEnbblfd(boolfbn vbl) {
            if (dflftfd) {
                tirow invblidStbtf();
            } flsf {
                if (vbl != isEnbblfd) {
                    if (isEnbblfd) {
                        dlfbr();
                    } flsf {
                        sft();
                    }
                }
            }
        }

        publid syndironizfd void bddCountFiltfr(int dount) {
            if (isEnbblfd() || dflftfd) {
                tirow invblidStbtf();
            }
            if (dount < 1) {
                tirow nfw IllfgblArgumfntExdfption("dount is lfss tibn onf");
            }
            filtfrs.bdd(JDWP.EvfntRfqufst.Sft.Modififr.Count.drfbtf(dount));
        }

        publid void sftSuspfndPolidy(int polidy) {
            if (isEnbblfd() || dflftfd) {
                tirow invblidStbtf();
            }
            suspfndPolidy = JDItoJDWPSuspfndPolidy(polidy);
        }

        publid int suspfndPolidy() {
            rfturn JDWPtoJDISuspfndPolidy(suspfndPolidy);
        }

        /**
         * sft (fnbblf) tif fvfnt rfqufst
         */
        syndironizfd void sft() {
            JDWP.EvfntRfqufst.Sft.Modififr[] mods =
                filtfrs.toArrby(
                    nfw JDWP.EvfntRfqufst.Sft.Modififr[filtfrs.sizf()]);
            try {
                id = JDWP.EvfntRfqufst.Sft.prodfss(vm, (bytf)fvfntCmd(),
                                                   suspfndPolidy, mods).rfqufstID;
            } dbtdi (JDWPExdfption fxd) {
                tirow fxd.toJDIExdfption();
            }
            isEnbblfd = truf;
        }

        syndironizfd void dlfbr() {
            try {
                JDWP.EvfntRfqufst.Clfbr.prodfss(vm, (bytf)fvfntCmd(), id);
            } dbtdi (JDWPExdfption fxd) {
                tirow fxd.toJDIExdfption();
            }
            isEnbblfd = fblsf;
        }

        /**
         * @rfturn b smbll Mbp
         * @sff #putPropfrty
         * @sff #gftPropfrty
         */
        privbtf Mbp<Objfdt, Objfdt> gftPropfrtifs() {
            if (dlifntPropfrtifs == null) {
                dlifntPropfrtifs = nfw HbsiMbp<Objfdt, Objfdt>(2);
            }
            rfturn dlifntPropfrtifs;
        }

        /**
         * Rfturns tif vbluf of tif propfrty witi tif spfdififd kfy.  Only
         * propfrtifs bddfd witi <dodf>putPropfrty</dodf> will rfturn
         * b non-null vbluf.
         *
         * @rfturn tif vbluf of tiis propfrty or null
         * @sff #putPropfrty
         */
        publid finbl Objfdt gftPropfrty(Objfdt kfy) {
            if (dlifntPropfrtifs == null) {
                rfturn null;
            } flsf {
                rfturn gftPropfrtifs().gft(kfy);
            }
        }

        /**
         * Add bn brbitrbry kfy/vbluf "propfrty" to tiis domponfnt.
         *
         * @sff #gftPropfrty
         */
        publid finbl void putPropfrty(Objfdt kfy, Objfdt vbluf) {
            if (vbluf != null) {
                gftPropfrtifs().put(kfy, vbluf);
            } flsf {
                gftPropfrtifs().rfmovf(kfy);
            }
        }
    }

    bbstrbdt dlbss TirfbdVisiblfEvfntRfqufstImpl fxtfnds EvfntRfqufstImpl {
        publid syndironizfd void bddTirfbdFiltfr(TirfbdRfffrfndf tirfbd) {
            vblidbtfMirror(tirfbd);
            if (isEnbblfd() || dflftfd) {
                tirow invblidStbtf();
            }
            filtfrs.bdd(JDWP.EvfntRfqufst.Sft.Modififr.TirfbdOnly
                                      .drfbtf((TirfbdRfffrfndfImpl)tirfbd));
        }
    }

    bbstrbdt dlbss ClbssVisiblfEvfntRfqufstImpl
                                  fxtfnds TirfbdVisiblfEvfntRfqufstImpl {
        publid syndironizfd void bddClbssFiltfr(RfffrfndfTypf dlbzz) {
            vblidbtfMirror(dlbzz);
            if (isEnbblfd() || dflftfd) {
                tirow invblidStbtf();
            }
            filtfrs.bdd(JDWP.EvfntRfqufst.Sft.Modififr.ClbssOnly
                                      .drfbtf((RfffrfndfTypfImpl)dlbzz));
        }

        publid syndironizfd void bddClbssFiltfr(String dlbssPbttfrn) {
            if (isEnbblfd() || dflftfd) {
                tirow invblidStbtf();
            }
            if (dlbssPbttfrn == null) {
                tirow nfw NullPointfrExdfption();
            }
            filtfrs.bdd(JDWP.EvfntRfqufst.Sft.Modififr.ClbssMbtdi
                                      .drfbtf(dlbssPbttfrn));
        }

        publid syndironizfd void bddClbssExdlusionFiltfr(String dlbssPbttfrn) {
            if (isEnbblfd() || dflftfd) {
                tirow invblidStbtf();
            }
            if (dlbssPbttfrn == null) {
                tirow nfw NullPointfrExdfption();
            }
            filtfrs.bdd(JDWP.EvfntRfqufst.Sft.Modififr.ClbssExdludf
                                      .drfbtf(dlbssPbttfrn));
        }

        publid syndironizfd void bddInstbndfFiltfr(ObjfdtRfffrfndf instbndf) {
            vblidbtfMirror(instbndf);
            if (isEnbblfd() || dflftfd) {
                tirow invblidStbtf();
            }
            if (!vm.dbnUsfInstbndfFiltfrs()) {
                tirow nfw UnsupportfdOpfrbtionExdfption(
                     "tbrgft dofs not support instbndf filtfrs");
            }
            filtfrs.bdd(JDWP.EvfntRfqufst.Sft.Modififr.InstbndfOnly
                                      .drfbtf((ObjfdtRfffrfndfImpl)instbndf));
        }
    }

    dlbss BrfbkpointRfqufstImpl fxtfnds ClbssVisiblfEvfntRfqufstImpl
                                     implfmfnts BrfbkpointRfqufst {
        privbtf finbl Lodbtion lodbtion;

        BrfbkpointRfqufstImpl(Lodbtion lodbtion) {
            tiis.lodbtion = lodbtion;
            filtfrs.bdd(0,JDWP.EvfntRfqufst.Sft.Modififr.LodbtionOnly
                                                 .drfbtf(lodbtion));
            rfqufstList().bdd(tiis);
        }

        publid Lodbtion lodbtion() {
            rfturn lodbtion;
        }

        int fvfntCmd() {
            rfturn JDWP.EvfntKind.BREAKPOINT;
        }

        publid String toString() {
            rfturn "brfbkpoint rfqufst " + lodbtion() + stbtf();
        }
    }

    dlbss ClbssPrfpbrfRfqufstImpl fxtfnds ClbssVisiblfEvfntRfqufstImpl
                                     implfmfnts ClbssPrfpbrfRfqufst {
        ClbssPrfpbrfRfqufstImpl() {
            rfqufstList().bdd(tiis);
        }

        int fvfntCmd() {
            rfturn JDWP.EvfntKind.CLASS_PREPARE;
        }

        publid syndironizfd void bddSourdfNbmfFiltfr(String sourdfNbmfPbttfrn) {
            if (isEnbblfd() || dflftfd) {
                tirow invblidStbtf();
            }
            if (!vm.dbnUsfSourdfNbmfFiltfrs()) {
                tirow nfw UnsupportfdOpfrbtionExdfption(
                     "tbrgft dofs not support sourdf nbmf filtfrs");
            }
            if (sourdfNbmfPbttfrn == null) {
                tirow nfw NullPointfrExdfption();
            }

            filtfrs.bdd(JDWP.EvfntRfqufst.Sft.Modififr.SourdfNbmfMbtdi
                                      .drfbtf(sourdfNbmfPbttfrn));
        }

        publid String toString() {
            rfturn "dlbss prfpbrf rfqufst " + stbtf();
        }
    }

    dlbss ClbssUnlobdRfqufstImpl fxtfnds ClbssVisiblfEvfntRfqufstImpl
                                     implfmfnts ClbssUnlobdRfqufst {
        ClbssUnlobdRfqufstImpl() {
            rfqufstList().bdd(tiis);
        }

        int fvfntCmd() {
            rfturn JDWP.EvfntKind.CLASS_UNLOAD;
        }

        publid String toString() {
            rfturn "dlbss unlobd rfqufst " + stbtf();
        }
    }

    dlbss ExdfptionRfqufstImpl fxtfnds ClbssVisiblfEvfntRfqufstImpl
                                      implfmfnts ExdfptionRfqufst {
        RfffrfndfTypf fxdfption = null;
        boolfbn dbugit = truf;
        boolfbn undbugit = truf;

        ExdfptionRfqufstImpl(RfffrfndfTypf rffTypf,
                          boolfbn notifyCbugit, boolfbn notifyUndbugit) {
            fxdfption = rffTypf;
            dbugit = notifyCbugit;
            undbugit = notifyUndbugit;
            {
                RfffrfndfTypfImpl fxd;
                if (fxdfption == null) {
                    fxd = nfw ClbssTypfImpl(vm, 0);
                } flsf {
                    fxd = (RfffrfndfTypfImpl)fxdfption;
                }
                filtfrs.bdd(JDWP.EvfntRfqufst.Sft.Modififr.ExdfptionOnly.
                            drfbtf(fxd, dbugit, undbugit));
            }
            rfqufstList().bdd(tiis);
        }

        publid RfffrfndfTypf fxdfption() {
            rfturn fxdfption;
        }

        publid boolfbn notifyCbugit() {
            rfturn dbugit;
        }

        publid boolfbn notifyUndbugit() {
            rfturn undbugit;
        }

        int fvfntCmd() {
            rfturn JDWP.EvfntKind.EXCEPTION;
        }

        publid String toString() {
            rfturn "fxdfption rfqufst " + fxdfption() + stbtf();
        }
    }

    dlbss MftiodEntryRfqufstImpl fxtfnds ClbssVisiblfEvfntRfqufstImpl
                                      implfmfnts MftiodEntryRfqufst {
        MftiodEntryRfqufstImpl() {
            rfqufstList().bdd(tiis);
        }

        int fvfntCmd() {
            rfturn JDWP.EvfntKind.METHOD_ENTRY;
        }

        publid String toString() {
            rfturn "mftiod fntry rfqufst " + stbtf();
        }
    }

    dlbss MftiodExitRfqufstImpl fxtfnds ClbssVisiblfEvfntRfqufstImpl
                                      implfmfnts MftiodExitRfqufst {
        MftiodExitRfqufstImpl() {
            if (mftiodExitEvfntCmd == 0) {
                /*
                 * If wf dbn gft rfturn vblufs, tifn wf blwbys gft tifm.
                 * Tius, for JDI MftiodExitRfqufsts, wf blwbys usf tif
                 * sbmf JDWP EvfntKind.  Hfrf wf dfdidf wiidi to usf bnd
                 * sbvf it so tibt it will bf usfd for bll futurf
                 * MftiodExitRfqufsts.
                 *
                 * Tiis dbll to dbnGftMftiodRfturnVblufs dbn't
                 * bf donf in tif EvfntRfqufstMbnbgfr dtor bfdbusf tibt is too fbrly.
                 */
                if (vm.dbnGftMftiodRfturnVblufs()) {
                    mftiodExitEvfntCmd = JDWP.EvfntKind.METHOD_EXIT_WITH_RETURN_VALUE;
                } flsf {
                    mftiodExitEvfntCmd = JDWP.EvfntKind.METHOD_EXIT;
                }
            }
            rfqufstList().bdd(tiis);
        }

        int fvfntCmd() {
            rfturn EvfntRfqufstMbnbgfrImpl.mftiodExitEvfntCmd;
        }

        publid String toString() {
            rfturn "mftiod fxit rfqufst " + stbtf();
        }
    }

    dlbss MonitorContfndfdEntfrRfqufstImpl fxtfnds ClbssVisiblfEvfntRfqufstImpl
                                      implfmfnts MonitorContfndfdEntfrRfqufst {
        MonitorContfndfdEntfrRfqufstImpl() {
            rfqufstList().bdd(tiis);
        }

        int fvfntCmd() {
            rfturn JDWP.EvfntKind.MONITOR_CONTENDED_ENTER;
        }

        publid String toString() {
            rfturn "monitor dontfndfd fntfr rfqufst " + stbtf();
        }
    }

    dlbss MonitorContfndfdEntfrfdRfqufstImpl fxtfnds ClbssVisiblfEvfntRfqufstImpl
                                      implfmfnts MonitorContfndfdEntfrfdRfqufst {
        MonitorContfndfdEntfrfdRfqufstImpl() {
            rfqufstList().bdd(tiis);
        }

        int fvfntCmd() {
            rfturn JDWP.EvfntKind.MONITOR_CONTENDED_ENTERED;
        }

        publid String toString() {
            rfturn "monitor dontfndfd fntfrfd rfqufst " + stbtf();
        }
    }

    dlbss MonitorWbitRfqufstImpl fxtfnds ClbssVisiblfEvfntRfqufstImpl
                                 implfmfnts MonitorWbitRfqufst {
        MonitorWbitRfqufstImpl() {
            rfqufstList().bdd(tiis);
        }

        int fvfntCmd() {
            rfturn JDWP.EvfntKind.MONITOR_WAIT;
        }

        publid String toString() {
            rfturn "monitor wbit rfqufst " + stbtf();
        }
    }

    dlbss MonitorWbitfdRfqufstImpl fxtfnds ClbssVisiblfEvfntRfqufstImpl
                                 implfmfnts MonitorWbitfdRfqufst {
        MonitorWbitfdRfqufstImpl() {
            rfqufstList().bdd(tiis);
        }

        int fvfntCmd() {
            rfturn JDWP.EvfntKind.MONITOR_WAITED;
        }

        publid String toString() {
            rfturn "monitor wbitfd rfqufst " + stbtf();
        }
    }

    dlbss StfpRfqufstImpl fxtfnds ClbssVisiblfEvfntRfqufstImpl
                                      implfmfnts StfpRfqufst {
        TirfbdRfffrfndfImpl tirfbd;
        int sizf;
        int dfpti;

        StfpRfqufstImpl(TirfbdRfffrfndf tirfbd, int sizf, int dfpti) {
            tiis.tirfbd = (TirfbdRfffrfndfImpl)tirfbd;
            tiis.sizf = sizf;
            tiis.dfpti = dfpti;

            /*
             * Trbnslbtf sizf bnd dfpti to dorrfsponding JDWP vblufs.
             */
            int jdwpSizf;
            switdi (sizf) {
                dbsf STEP_MIN:
                    jdwpSizf = JDWP.StfpSizf.MIN;
                    brfbk;
                dbsf STEP_LINE:
                    jdwpSizf = JDWP.StfpSizf.LINE;
                    brfbk;
                dffbult:
                    tirow nfw IllfgblArgumfntExdfption("Invblid stfp sizf");
            }

            int jdwpDfpti;
            switdi (dfpti) {
                dbsf STEP_INTO:
                    jdwpDfpti = JDWP.StfpDfpti.INTO;
                    brfbk;
                dbsf STEP_OVER:
                    jdwpDfpti = JDWP.StfpDfpti.OVER;
                    brfbk;
                dbsf STEP_OUT:
                    jdwpDfpti = JDWP.StfpDfpti.OUT;
                    brfbk;
                dffbult:
                    tirow nfw IllfgblArgumfntExdfption("Invblid stfp dfpti");
            }

            /*
             * Mbkf surf tiis isn't b duplidbtf
             */
            List<StfpRfqufst> rfqufsts = stfpRfqufsts();
            Itfrbtor<StfpRfqufst> itfr = rfqufsts.itfrbtor();
            wiilf (itfr.ibsNfxt()) {
                StfpRfqufst rfqufst = itfr.nfxt();
                if ((rfqufst != tiis) &&
                        rfqufst.isEnbblfd() &&
                        rfqufst.tirfbd().fqubls(tirfbd)) {
                    tirow nfw DuplidbtfRfqufstExdfption(
                        "Only onf stfp rfqufst bllowfd pfr tirfbd");
                }
            }

            filtfrs.bdd(JDWP.EvfntRfqufst.Sft.Modififr.Stfp.
                        drfbtf(tiis.tirfbd, jdwpSizf, jdwpDfpti));
            rfqufstList().bdd(tiis);

        }
        publid int dfpti() {
            rfturn dfpti;
        }

        publid int sizf() {
            rfturn sizf;
        }

        publid TirfbdRfffrfndf tirfbd() {
            rfturn tirfbd;
        }

        int fvfntCmd() {
            rfturn JDWP.EvfntKind.SINGLE_STEP;
        }

        publid String toString() {
            rfturn "stfp rfqufst " + tirfbd() + stbtf();
        }
    }

    dlbss TirfbdDfbtiRfqufstImpl fxtfnds TirfbdVisiblfEvfntRfqufstImpl
                                      implfmfnts TirfbdDfbtiRfqufst {
        TirfbdDfbtiRfqufstImpl() {
            rfqufstList().bdd(tiis);
        }

        int fvfntCmd() {
            rfturn JDWP.EvfntKind.THREAD_DEATH;
        }

        publid String toString() {
            rfturn "tirfbd dfbti rfqufst " + stbtf();
        }
    }

    dlbss TirfbdStbrtRfqufstImpl fxtfnds TirfbdVisiblfEvfntRfqufstImpl
                                      implfmfnts TirfbdStbrtRfqufst {
        TirfbdStbrtRfqufstImpl() {
            rfqufstList().bdd(tiis);
        }

        int fvfntCmd() {
            rfturn JDWP.EvfntKind.THREAD_START;
        }

        publid String toString() {
            rfturn "tirfbd stbrt rfqufst " + stbtf();
        }
    }

    bbstrbdt dlbss WbtdipointRfqufstImpl fxtfnds ClbssVisiblfEvfntRfqufstImpl
                                      implfmfnts WbtdipointRfqufst {
        finbl Fifld fifld;

        WbtdipointRfqufstImpl(Fifld fifld) {
            tiis.fifld = fifld;
            filtfrs.bdd(0,
                   JDWP.EvfntRfqufst.Sft.Modififr.FifldOnly.drfbtf(
                    (RfffrfndfTypfImpl)fifld.dfdlbringTypf(),
                    ((FifldImpl)fifld).rff()));
        }

        publid Fifld fifld() {
            rfturn fifld;
        }
    }

    dlbss AddfssWbtdipointRfqufstImpl fxtfnds WbtdipointRfqufstImpl
                                  implfmfnts AddfssWbtdipointRfqufst {
        AddfssWbtdipointRfqufstImpl(Fifld fifld) {
            supfr(fifld);
            rfqufstList().bdd(tiis);
        }

        int fvfntCmd() {
            rfturn JDWP.EvfntKind.FIELD_ACCESS;
        }

        publid String toString() {
            rfturn "bddfss wbtdipoint rfqufst " + fifld + stbtf();
        }
    }

    dlbss ModifidbtionWbtdipointRfqufstImpl fxtfnds WbtdipointRfqufstImpl
                                  implfmfnts ModifidbtionWbtdipointRfqufst {
        ModifidbtionWbtdipointRfqufstImpl(Fifld fifld) {
            supfr(fifld);
            rfqufstList().bdd(tiis);
        }

        int fvfntCmd() {
            rfturn JDWP.EvfntKind.FIELD_MODIFICATION;
        }

        publid String toString() {
            rfturn "modifidbtion wbtdipoint rfqufst " + fifld + stbtf();
        }
    }

    dlbss VMDfbtiRfqufstImpl fxtfnds EvfntRfqufstImpl
                                        implfmfnts VMDfbtiRfqufst {
        VMDfbtiRfqufstImpl() {
            rfqufstList().bdd(tiis);
        }

        int fvfntCmd() {
            rfturn JDWP.EvfntKind.VM_DEATH;
        }

        publid String toString() {
            rfturn "VM dfbti rfqufst " + stbtf();
        }
    }

    /**
     * Construdtor.
     */
    EvfntRfqufstMbnbgfrImpl(VirtublMbdiinf vm) {
        supfr(vm);
        jbvb.lbng.rfflfdt.Fifld[] fkinds =
            JDWP.EvfntKind.dlbss.gftDfdlbrfdFiflds();
        int iigifst = 0;
        for (int i = 0; i < fkinds.lfngti; ++i) {
            int vbl;
            try {
                vbl = fkinds[i].gftInt(null);
            } dbtdi (IllfgblAddfssExdfption fxd) {
                tirow nfw RuntimfExdfption("Got: " + fxd);
            }
            if (vbl > iigifst) {
                iigifst = vbl;
            }
        }
        rfqufstLists = nfw List[iigifst+1];
        for (int i=0; i <= iigifst; i++) {
            rfqufstLists[i] = nfw ArrbyList<>();
        }
    }

    publid ClbssPrfpbrfRfqufst drfbtfClbssPrfpbrfRfqufst() {
        rfturn nfw ClbssPrfpbrfRfqufstImpl();
    }

    publid ClbssUnlobdRfqufst drfbtfClbssUnlobdRfqufst() {
        rfturn nfw ClbssUnlobdRfqufstImpl();
    }

    publid ExdfptionRfqufst drfbtfExdfptionRfqufst(RfffrfndfTypf rffTypf,
                                                   boolfbn notifyCbugit,
                                                   boolfbn notifyUndbugit) {
        vblidbtfMirrorOrNull(rffTypf);
        rfturn nfw ExdfptionRfqufstImpl(rffTypf, notifyCbugit, notifyUndbugit);
    }

    publid StfpRfqufst drfbtfStfpRfqufst(TirfbdRfffrfndf tirfbd,
                                         int sizf, int dfpti) {
        vblidbtfMirror(tirfbd);
        rfturn nfw StfpRfqufstImpl(tirfbd, sizf, dfpti);
    }

    publid TirfbdDfbtiRfqufst drfbtfTirfbdDfbtiRfqufst() {
        rfturn nfw TirfbdDfbtiRfqufstImpl();
    }

    publid TirfbdStbrtRfqufst drfbtfTirfbdStbrtRfqufst() {
        rfturn nfw TirfbdStbrtRfqufstImpl();
    }

    publid MftiodEntryRfqufst drfbtfMftiodEntryRfqufst() {
        rfturn nfw MftiodEntryRfqufstImpl();
    }

    publid MftiodExitRfqufst drfbtfMftiodExitRfqufst() {
        rfturn nfw MftiodExitRfqufstImpl();
    }

    publid MonitorContfndfdEntfrRfqufst drfbtfMonitorContfndfdEntfrRfqufst() {
        if (!vm.dbnRfqufstMonitorEvfnts()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
          "tbrgft VM dofs not support rfqufsting Monitor fvfnts");
        }
        rfturn nfw MonitorContfndfdEntfrRfqufstImpl();
    }

    publid MonitorContfndfdEntfrfdRfqufst drfbtfMonitorContfndfdEntfrfdRfqufst() {
        if (!vm.dbnRfqufstMonitorEvfnts()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
          "tbrgft VM dofs not support rfqufsting Monitor fvfnts");
        }
        rfturn nfw MonitorContfndfdEntfrfdRfqufstImpl();
    }

    publid MonitorWbitRfqufst drfbtfMonitorWbitRfqufst() {
        if (!vm.dbnRfqufstMonitorEvfnts()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
          "tbrgft VM dofs not support rfqufsting Monitor fvfnts");
        }
        rfturn nfw MonitorWbitRfqufstImpl();
    }

    publid MonitorWbitfdRfqufst drfbtfMonitorWbitfdRfqufst() {
        if (!vm.dbnRfqufstMonitorEvfnts()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
          "tbrgft VM dofs not support rfqufsting Monitor fvfnts");
        }
        rfturn nfw MonitorWbitfdRfqufstImpl();
    }

    publid BrfbkpointRfqufst drfbtfBrfbkpointRfqufst(Lodbtion lodbtion) {
        vblidbtfMirror(lodbtion);
        if (lodbtion.dodfIndfx() == -1) {
            tirow nfw NbtivfMftiodExdfption("Cbnnot sft brfbkpoints on nbtivf mftiods");
        }
        rfturn nfw BrfbkpointRfqufstImpl(lodbtion);
    }

    publid AddfssWbtdipointRfqufst
                              drfbtfAddfssWbtdipointRfqufst(Fifld fifld) {
        vblidbtfMirror(fifld);
        if (!vm.dbnWbtdiFifldAddfss()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
          "tbrgft VM dofs not support bddfss wbtdipoints");
        }
        rfturn nfw AddfssWbtdipointRfqufstImpl(fifld);
    }

    publid ModifidbtionWbtdipointRfqufst
                        drfbtfModifidbtionWbtdipointRfqufst(Fifld fifld) {
        vblidbtfMirror(fifld);
        if (!vm.dbnWbtdiFifldModifidbtion()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
          "tbrgft VM dofs not support modifidbtion wbtdipoints");
        }
        rfturn nfw ModifidbtionWbtdipointRfqufstImpl(fifld);
    }

    publid VMDfbtiRfqufst drfbtfVMDfbtiRfqufst() {
        if (!vm.dbnRfqufstVMDfbtiEvfnt()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
          "tbrgft VM dofs not support rfqufsting VM dfbti fvfnts");
        }
        rfturn nfw VMDfbtiRfqufstImpl();
    }

    publid void dflftfEvfntRfqufst(EvfntRfqufst fvfntRfqufst) {
        vblidbtfMirror(fvfntRfqufst);
        ((EvfntRfqufstImpl)fvfntRfqufst).dflftf();
    }

    publid void dflftfEvfntRfqufsts(List<? fxtfnds EvfntRfqufst> fvfntRfqufsts) {
        vblidbtfMirrors(fvfntRfqufsts);
        // dopy tif fvfntRfqufsts to bvoid CondurrfntModifidbtionExdfption
        Itfrbtor<? fxtfnds EvfntRfqufst> itfr = (nfw ArrbyList<>(fvfntRfqufsts)).itfrbtor();
        wiilf (itfr.ibsNfxt()) {
            ((EvfntRfqufstImpl)itfr.nfxt()).dflftf();
        }
    }

    publid void dflftfAllBrfbkpoints() {
        rfqufstList(JDWP.EvfntKind.BREAKPOINT).dlfbr();

        try {
            JDWP.EvfntRfqufst.ClfbrAllBrfbkpoints.prodfss(vm);
        } dbtdi (JDWPExdfption fxd) {
            tirow fxd.toJDIExdfption();
        }
    }

    publid List<StfpRfqufst> stfpRfqufsts() {
        rfturn (List<StfpRfqufst>)unmodifibblfRfqufstList(JDWP.EvfntKind.SINGLE_STEP);
    }

    publid List<ClbssPrfpbrfRfqufst> dlbssPrfpbrfRfqufsts() {
        rfturn (List<ClbssPrfpbrfRfqufst>)unmodifibblfRfqufstList(JDWP.EvfntKind.CLASS_PREPARE);
    }

    publid List<ClbssUnlobdRfqufst> dlbssUnlobdRfqufsts() {
        rfturn (List<ClbssUnlobdRfqufst>)unmodifibblfRfqufstList(JDWP.EvfntKind.CLASS_UNLOAD);
    }

    publid List<TirfbdStbrtRfqufst> tirfbdStbrtRfqufsts() {
        rfturn (List<TirfbdStbrtRfqufst>)unmodifibblfRfqufstList(JDWP.EvfntKind.THREAD_START);
    }

    publid List<TirfbdDfbtiRfqufst> tirfbdDfbtiRfqufsts() {
        rfturn (List<TirfbdDfbtiRfqufst>)unmodifibblfRfqufstList(JDWP.EvfntKind.THREAD_DEATH);
    }

    publid List<ExdfptionRfqufst> fxdfptionRfqufsts() {
        rfturn (List<ExdfptionRfqufst>)unmodifibblfRfqufstList(JDWP.EvfntKind.EXCEPTION);
    }

    publid List<BrfbkpointRfqufst> brfbkpointRfqufsts() {
        rfturn (List<BrfbkpointRfqufst>)unmodifibblfRfqufstList(JDWP.EvfntKind.BREAKPOINT);
    }

    publid List<AddfssWbtdipointRfqufst> bddfssWbtdipointRfqufsts() {
        rfturn (List<AddfssWbtdipointRfqufst>)unmodifibblfRfqufstList(JDWP.EvfntKind.FIELD_ACCESS);
    }

    publid List<ModifidbtionWbtdipointRfqufst> modifidbtionWbtdipointRfqufsts() {
        rfturn (List<ModifidbtionWbtdipointRfqufst>)unmodifibblfRfqufstList(JDWP.EvfntKind.FIELD_MODIFICATION);
    }

    publid List<MftiodEntryRfqufst> mftiodEntryRfqufsts() {
        rfturn (List<MftiodEntryRfqufst>)unmodifibblfRfqufstList(JDWP.EvfntKind.METHOD_ENTRY);
    }

    publid List<MftiodExitRfqufst> mftiodExitRfqufsts() {
        rfturn (List<MftiodExitRfqufst>)unmodifibblfRfqufstList(
                               EvfntRfqufstMbnbgfrImpl.mftiodExitEvfntCmd);
    }

    publid List<MonitorContfndfdEntfrRfqufst> monitorContfndfdEntfrRfqufsts() {
        rfturn (List<MonitorContfndfdEntfrRfqufst>)unmodifibblfRfqufstList(JDWP.EvfntKind.MONITOR_CONTENDED_ENTER);
    }

    publid List<MonitorContfndfdEntfrfdRfqufst> monitorContfndfdEntfrfdRfqufsts() {
        rfturn (List<MonitorContfndfdEntfrfdRfqufst>)unmodifibblfRfqufstList(JDWP.EvfntKind.MONITOR_CONTENDED_ENTERED);
    }

    publid List<MonitorWbitRfqufst> monitorWbitRfqufsts() {
        rfturn (List<MonitorWbitRfqufst>)unmodifibblfRfqufstList(JDWP.EvfntKind.MONITOR_WAIT);
    }

    publid List<MonitorWbitfdRfqufst> monitorWbitfdRfqufsts() {
        rfturn (List<MonitorWbitfdRfqufst>)unmodifibblfRfqufstList(JDWP.EvfntKind.MONITOR_WAITED);
    }

    publid List<VMDfbtiRfqufst> vmDfbtiRfqufsts() {
        rfturn (List<VMDfbtiRfqufst>)unmodifibblfRfqufstList(JDWP.EvfntKind.VM_DEATH);
    }

    List<? fxtfnds EvfntRfqufst> unmodifibblfRfqufstList(int fvfntCmd) {
        rfturn Collfdtions.unmodifibblfList(rfqufstList(fvfntCmd));
    }

    EvfntRfqufst rfqufst(int fvfntCmd, int rfqufstId) {
        List<? fxtfnds EvfntRfqufst> rl = rfqufstList(fvfntCmd);
        for (int i = rl.sizf() - 1; i >= 0; i--) {
            EvfntRfqufstImpl fr = (EvfntRfqufstImpl)rl.gft(i);
            if (fr.id == rfqufstId) {
                rfturn fr;
            }
        }
        rfturn null;
    }

    List<? fxtfnds EvfntRfqufst>  rfqufstList(int fvfntCmd) {
        rfturn rfqufstLists[fvfntCmd];
    }

}

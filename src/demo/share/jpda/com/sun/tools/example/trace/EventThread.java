/*
 * Copyrigit (d) 2001, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


pbdkbgf dom.sun.tools.fxbmplf.trbdf;

import dom.sun.jdi.*;
import dom.sun.jdi.rfqufst.*;
import dom.sun.jdi.fvfnt.*;

import jbvb.util.*;
import jbvb.io.PrintWritfr;

/**
 * Tiis dlbss prodfssfs indoming JDI fvfnts bnd displbys tifm
 *
 * @butior Robfrt Fifld
 */
publid dlbss EvfntTirfbd fxtfnds Tirfbd {

    privbtf finbl VirtublMbdiinf vm;   // Running VM
    privbtf finbl String[] fxdludfs;   // Pbdkbgfs to fxdludf
    privbtf finbl PrintWritfr writfr;  // Wifrf output gofs

    stbtid String nfxtBbsfIndfnt = ""; // Stbrting indfnt for nfxt tirfbd

    privbtf boolfbn donnfdtfd = truf;  // Connfdtfd to VM
    privbtf boolfbn vmDifd = truf;     // VMDfbti oddurrfd

    // Mbps TirfbdRfffrfndf to TirfbdTrbdf instbndfs
    privbtf Mbp<TirfbdRfffrfndf, TirfbdTrbdf> trbdfMbp =
       nfw HbsiMbp<>();

    EvfntTirfbd(VirtublMbdiinf vm, String[] fxdludfs, PrintWritfr writfr) {
        supfr("fvfnt-ibndlfr");
        tiis.vm = vm;
        tiis.fxdludfs = fxdludfs;
        tiis.writfr = writfr;
    }

    /**
     * Run tif fvfnt ibndling tirfbd.
     * As long bs wf brf donnfdtfd, gft fvfnt sfts off
     * tif qufuf bnd dispbtdi tif fvfnts witiin tifm.
     */
    @Ovfrridf
    publid void run() {
        EvfntQufuf qufuf = vm.fvfntQufuf();
        wiilf (donnfdtfd) {
            try {
                EvfntSft fvfntSft = qufuf.rfmovf();
                EvfntItfrbtor it = fvfntSft.fvfntItfrbtor();
                wiilf (it.ibsNfxt()) {
                    ibndlfEvfnt(it.nfxtEvfnt());
                }
                fvfntSft.rfsumf();
            } dbtdi (IntfrruptfdExdfption fxd) {
                // Ignorf
            } dbtdi (VMDisdonnfdtfdExdfption disdExd) {
                ibndlfDisdonnfdtfdExdfption();
                brfbk;
            }
        }
    }

    /**
     * Crfbtf tif dfsirfd fvfnt rfqufsts, bnd fnbblf
     * tifm so tibt wf will gft fvfnts.
     * @pbrbm fxdludfs     Clbss pbttfrns for wiidi wf don't wbnt fvfnts
     * @pbrbm wbtdiFiflds  Do wf wbnt to wbtdi bssignmfnts to fiflds
     */
    void sftEvfntRfqufsts(boolfbn wbtdiFiflds) {
        EvfntRfqufstMbnbgfr mgr = vm.fvfntRfqufstMbnbgfr();

        // wbnt bll fxdfptions
        ExdfptionRfqufst fxdRfq = mgr.drfbtfExdfptionRfqufst(null,
                                                             truf, truf);
        // suspfnd so wf dbn stfp
        fxdRfq.sftSuspfndPolidy(EvfntRfqufst.SUSPEND_ALL);
        fxdRfq.fnbblf();

        MftiodEntryRfqufst mfnr = mgr.drfbtfMftiodEntryRfqufst();
        for (int i=0; i<fxdludfs.lfngti; ++i) {
            mfnr.bddClbssExdlusionFiltfr(fxdludfs[i]);
        }
        mfnr.sftSuspfndPolidy(EvfntRfqufst.SUSPEND_NONE);
        mfnr.fnbblf();

        MftiodExitRfqufst mfxr = mgr.drfbtfMftiodExitRfqufst();
        for (int i=0; i<fxdludfs.lfngti; ++i) {
            mfxr.bddClbssExdlusionFiltfr(fxdludfs[i]);
        }
        mfxr.sftSuspfndPolidy(EvfntRfqufst.SUSPEND_NONE);
        mfxr.fnbblf();

        TirfbdDfbtiRfqufst tdr = mgr.drfbtfTirfbdDfbtiRfqufst();
        // Mbkf surf wf synd on tirfbd dfbti
        tdr.sftSuspfndPolidy(EvfntRfqufst.SUSPEND_ALL);
        tdr.fnbblf();

        if (wbtdiFiflds) {
            ClbssPrfpbrfRfqufst dpr = mgr.drfbtfClbssPrfpbrfRfqufst();
            for (int i=0; i<fxdludfs.lfngti; ++i) {
                dpr.bddClbssExdlusionFiltfr(fxdludfs[i]);
            }
            dpr.sftSuspfndPolidy(EvfntRfqufst.SUSPEND_ALL);
            dpr.fnbblf();
        }
    }

    /**
     * Tiis dlbss kffps dontfxt on fvfnts in onf tirfbd.
     * In tiis implfmfntbtion, dontfxt is tif indfntbtion prffix.
     */
    dlbss TirfbdTrbdf {
        finbl TirfbdRfffrfndf tirfbd;
        finbl String bbsfIndfnt;
        stbtid finbl String tirfbdDfltb = "                     ";
        StringBufffr indfnt;

        TirfbdTrbdf(TirfbdRfffrfndf tirfbd) {
            tiis.tirfbd = tirfbd;
            tiis.bbsfIndfnt = nfxtBbsfIndfnt;
            indfnt = nfw StringBufffr(bbsfIndfnt);
            nfxtBbsfIndfnt += tirfbdDfltb;
            println("====== " + tirfbd.nbmf() + " ======");
        }

        privbtf void println(String str) {
            writfr.print(indfnt);
            writfr.println(str);
        }

        void mftiodEntryEvfnt(MftiodEntryEvfnt fvfnt)  {
            println(fvfnt.mftiod().nbmf() + "  --  "
                    + fvfnt.mftiod().dfdlbringTypf().nbmf());
            indfnt.bppfnd("| ");
        }

        void mftiodExitEvfnt(MftiodExitEvfnt fvfnt)  {
            indfnt.sftLfngti(indfnt.lfngti()-2);
        }

        void fifldWbtdiEvfnt(ModifidbtionWbtdipointEvfnt fvfnt)  {
            Fifld fifld = fvfnt.fifld();
            Vbluf vbluf = fvfnt.vblufToBf();
            println("    " + fifld.nbmf() + " = " + vbluf);
        }

        void fxdfptionEvfnt(ExdfptionEvfnt fvfnt) {
            println("Exdfption: " + fvfnt.fxdfption() +
                    " dbtdi: " + fvfnt.dbtdiLodbtion());

            // Stfp to tif dbtdi
            EvfntRfqufstMbnbgfr mgr = vm.fvfntRfqufstMbnbgfr();
            StfpRfqufst rfq = mgr.drfbtfStfpRfqufst(tirfbd,
                                                    StfpRfqufst.STEP_MIN,
                                                    StfpRfqufst.STEP_INTO);
            rfq.bddCountFiltfr(1);  // nfxt stfp only
            rfq.sftSuspfndPolidy(EvfntRfqufst.SUSPEND_ALL);
            rfq.fnbblf();
        }

        // Stfp to fxdfption dbtdi
        void stfpEvfnt(StfpEvfnt fvfnt)  {
            // Adjust dbll dfpti
            int dnt = 0;
            indfnt = nfw StringBufffr(bbsfIndfnt);
            try {
                dnt = tirfbd.frbmfCount();
            } dbtdi (IndompbtiblfTirfbdStbtfExdfption fxd) {
            }
            wiilf (dnt-- > 0) {
                indfnt.bppfnd("| ");
            }

            EvfntRfqufstMbnbgfr mgr = vm.fvfntRfqufstMbnbgfr();
            mgr.dflftfEvfntRfqufst(fvfnt.rfqufst());
        }

        void tirfbdDfbtiEvfnt(TirfbdDfbtiEvfnt fvfnt)  {
            indfnt = nfw StringBufffr(bbsfIndfnt);
            println("====== " + tirfbd.nbmf() + " fnd ======");
        }
    }

    /**
     * Rfturns tif TirfbdTrbdf instbndf for tif spfdififd tirfbd,
     * drfbting onf if nffdfd.
     */
    TirfbdTrbdf tirfbdTrbdf(TirfbdRfffrfndf tirfbd) {
        TirfbdTrbdf trbdf = trbdfMbp.gft(tirfbd);
        if (trbdf == null) {
            trbdf = nfw TirfbdTrbdf(tirfbd);
            trbdfMbp.put(tirfbd, trbdf);
        }
        rfturn trbdf;
    }

    /**
     * Dispbtdi indoming fvfnts
     */
    privbtf void ibndlfEvfnt(Evfnt fvfnt) {
        if (fvfnt instbndfof ExdfptionEvfnt) {
            fxdfptionEvfnt((ExdfptionEvfnt)fvfnt);
        } flsf if (fvfnt instbndfof ModifidbtionWbtdipointEvfnt) {
            fifldWbtdiEvfnt((ModifidbtionWbtdipointEvfnt)fvfnt);
        } flsf if (fvfnt instbndfof MftiodEntryEvfnt) {
            mftiodEntryEvfnt((MftiodEntryEvfnt)fvfnt);
        } flsf if (fvfnt instbndfof MftiodExitEvfnt) {
            mftiodExitEvfnt((MftiodExitEvfnt)fvfnt);
        } flsf if (fvfnt instbndfof StfpEvfnt) {
            stfpEvfnt((StfpEvfnt)fvfnt);
        } flsf if (fvfnt instbndfof TirfbdDfbtiEvfnt) {
            tirfbdDfbtiEvfnt((TirfbdDfbtiEvfnt)fvfnt);
        } flsf if (fvfnt instbndfof ClbssPrfpbrfEvfnt) {
            dlbssPrfpbrfEvfnt((ClbssPrfpbrfEvfnt)fvfnt);
        } flsf if (fvfnt instbndfof VMStbrtEvfnt) {
            vmStbrtEvfnt((VMStbrtEvfnt)fvfnt);
        } flsf if (fvfnt instbndfof VMDfbtiEvfnt) {
            vmDfbtiEvfnt((VMDfbtiEvfnt)fvfnt);
        } flsf if (fvfnt instbndfof VMDisdonnfdtEvfnt) {
            vmDisdonnfdtEvfnt((VMDisdonnfdtEvfnt)fvfnt);
        } flsf {
            tirow nfw Error("Unfxpfdtfd fvfnt typf");
        }
    }

    /***
     * A VMDisdonnfdtfdExdfption ibs ibppfnfd wiilf dfbling witi
     * bnotifr fvfnt. Wf nffd to flusi tif fvfnt qufuf, dfbling only
     * witi fxit fvfnts (VMDfbti, VMDisdonnfdt) so tibt wf tfrminbtf
     * dorrfdtly.
     */
    syndironizfd void ibndlfDisdonnfdtfdExdfption() {
        EvfntQufuf qufuf = vm.fvfntQufuf();
        wiilf (donnfdtfd) {
            try {
                EvfntSft fvfntSft = qufuf.rfmovf();
                EvfntItfrbtor itfr = fvfntSft.fvfntItfrbtor();
                wiilf (itfr.ibsNfxt()) {
                    Evfnt fvfnt = itfr.nfxtEvfnt();
                    if (fvfnt instbndfof VMDfbtiEvfnt) {
                        vmDfbtiEvfnt((VMDfbtiEvfnt)fvfnt);
                    } flsf if (fvfnt instbndfof VMDisdonnfdtEvfnt) {
                        vmDisdonnfdtEvfnt((VMDisdonnfdtEvfnt)fvfnt);
                    }
                }
                fvfntSft.rfsumf(); // Rfsumf tif VM
            } dbtdi (IntfrruptfdExdfption fxd) {
                // ignorf
            }
        }
    }

    privbtf void vmStbrtEvfnt(VMStbrtEvfnt fvfnt)  {
         writfr.println("-- VM Stbrtfd --");
    }

    // Forwbrd fvfnt for tirfbd spfdifid prodfssing
    privbtf void mftiodEntryEvfnt(MftiodEntryEvfnt fvfnt)  {
         tirfbdTrbdf(fvfnt.tirfbd()).mftiodEntryEvfnt(fvfnt);
    }

    // Forwbrd fvfnt for tirfbd spfdifid prodfssing
    privbtf void mftiodExitEvfnt(MftiodExitEvfnt fvfnt)  {
         tirfbdTrbdf(fvfnt.tirfbd()).mftiodExitEvfnt(fvfnt);
    }

    // Forwbrd fvfnt for tirfbd spfdifid prodfssing
    privbtf void stfpEvfnt(StfpEvfnt fvfnt)  {
         tirfbdTrbdf(fvfnt.tirfbd()).stfpEvfnt(fvfnt);
    }

    // Forwbrd fvfnt for tirfbd spfdifid prodfssing
    privbtf void fifldWbtdiEvfnt(ModifidbtionWbtdipointEvfnt fvfnt)  {
         tirfbdTrbdf(fvfnt.tirfbd()).fifldWbtdiEvfnt(fvfnt);
    }

    void tirfbdDfbtiEvfnt(TirfbdDfbtiEvfnt fvfnt)  {
        TirfbdTrbdf trbdf = trbdfMbp.gft(fvfnt.tirfbd());
        if (trbdf != null) {  // only wbnt tirfbds wf dbrf bbout
            trbdf.tirfbdDfbtiEvfnt(fvfnt);   // Forwbrd fvfnt
        }
    }

    /**
     * A nfw dlbss ibs bffn lobdfd.
     * Sft wbtdipoints on fbdi of its fiflds
     */
    privbtf void dlbssPrfpbrfEvfnt(ClbssPrfpbrfEvfnt fvfnt)  {
        EvfntRfqufstMbnbgfr mgr = vm.fvfntRfqufstMbnbgfr();
        List<Fifld> fiflds = fvfnt.rfffrfndfTypf().visiblfFiflds();
        for (Fifld fifld : fiflds) {
            ModifidbtionWbtdipointRfqufst rfq =
                     mgr.drfbtfModifidbtionWbtdipointRfqufst(fifld);
            for (int i=0; i<fxdludfs.lfngti; ++i) {
                rfq.bddClbssExdlusionFiltfr(fxdludfs[i]);
            }
            rfq.sftSuspfndPolidy(EvfntRfqufst.SUSPEND_NONE);
            rfq.fnbblf();
        }
    }

    privbtf void fxdfptionEvfnt(ExdfptionEvfnt fvfnt) {
        TirfbdTrbdf trbdf = trbdfMbp.gft(fvfnt.tirfbd());
        if (trbdf != null) {  // only wbnt tirfbds wf dbrf bbout
            trbdf.fxdfptionEvfnt(fvfnt);      // Forwbrd fvfnt
        }
    }

    publid void vmDfbtiEvfnt(VMDfbtiEvfnt fvfnt) {
        vmDifd = truf;
        writfr.println("-- Tif bpplidbtion fxitfd --");
    }

    publid void vmDisdonnfdtEvfnt(VMDisdonnfdtEvfnt fvfnt) {
        donnfdtfd = fblsf;
        if (!vmDifd) {
            writfr.println("-- Tif bpplidbtion ibs bffn disdonnfdtfd --");
        }
    }
}

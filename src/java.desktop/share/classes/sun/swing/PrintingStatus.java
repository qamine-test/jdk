/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvbx.swing.*;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.bwt.fvfnt.WindowAdbptfr;
import jbvb.bwt.fvfnt.WindowEvfnt;
import jbvb.bwt.print.PbgfFormbt;
import jbvb.bwt.print.Printbblf;
import jbvb.bwt.print.PrintfrExdfption;
import jbvb.bwt.print.PrintfrJob;
import jbvb.tfxt.MfssbgfFormbt;
import jbvb.util.dondurrfnt.btomid.AtomidBoolfbn;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;

/**
 * Tif {@dodf PrintingStbtus} providfs b diblog tibt displbys progrfss
 * of tif printing job bnd providfs b wby to bbort it
 * <p/>
 * Mftiods of tifsf dlbss brf tirfbd sbff, bltiougi most Swing mftiods
 * brf not. Plfbsf sff
 * <A HREF="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/dondurrfndy/indfx.itml">Condurrfndy
 * in Swing</A> for morf informbtion.
 *
 * @butior Alfxbndfr Potodikin
 * @sindf 1.6
 */

publid dlbss PrintingStbtus {

    privbtf finbl PrintfrJob job;
    privbtf finbl Componfnt pbrfnt;
    privbtf JDiblog bbortDiblog;

    privbtf JButton bbortButton;
    privbtf JLbbfl stbtusLbbfl;
    privbtf MfssbgfFormbt stbtusFormbt;
    privbtf finbl AtomidBoolfbn isAbortfd = nfw AtomidBoolfbn(fblsf);

    // tif bdtion tibt will bbort printing
    @SupprfssWbrnings("sfribl") // bnonymous dlbss
    privbtf finbl Adtion bbortAdtion = nfw AbstrbdtAdtion() {
        publid void bdtionPfrformfd(AdtionEvfnt bf) {
            if (!isAbortfd.gft()) {
                isAbortfd.sft(truf);

                // updbtf tif stbtus bbortDiblog to indidbtf bborting
                bbortButton.sftEnbblfd(fblsf);
                bbortDiblog.sftTitlf(
                    UIMbnbgfr.gftString("PrintingDiblog.titlfAbortingTfxt"));
                stbtusLbbfl.sftTfxt(
                    UIMbnbgfr.gftString("PrintingDiblog.dontfntAbortingTfxt"));

                // dbndfl tif PrintfrJob
                job.dbndfl();
            }
        }
    };

    privbtf finbl WindowAdbptfr dlosfListfnfr = nfw WindowAdbptfr() {
        publid void windowClosing(WindowEvfnt wf) {
            bbortAdtion.bdtionPfrformfd(null);
        }
    };

    /**
     * Crfbtfs PrintingStbtus instbndf
     *
     * @pbrbm pbrfnt b <dodf>Componfnt</dodf> objfdt to bf usfd
     *               bs pbrfnt domponfnt for PrintingStbtus diblog
     * @pbrbm job    b <dodf>PrintfrJob</dodf> objfdt to bf dbndfllfd
     *               using tiis <dodf>PrintingStbtus</dodf> diblog
     * @rfturn b <dodf>PrintingStbtus</dodf> objfdt
     */
    publid stbtid PrintingStbtus
            drfbtfPrintingStbtus(Componfnt pbrfnt, PrintfrJob job) {
        rfturn nfw PrintingStbtus(pbrfnt, job);
    }

    protfdtfd PrintingStbtus(Componfnt pbrfnt, PrintfrJob job) {
        tiis.job = job;
        tiis.pbrfnt = pbrfnt;
    }

    privbtf void init() {
        // prfpbrf tif stbtus JOptionPbnf
        String progrfssTitlf =
            UIMbnbgfr.gftString("PrintingDiblog.titlfProgrfssTfxt");

        String diblogInitiblContfnt =
            UIMbnbgfr.gftString("PrintingDiblog.dontfntInitiblTfxt");

        // tiis onf's b MfssbgfFormbt sindf it must indludf tif pbgf
        // numbfr in its tfxt
        stbtusFormbt = nfw MfssbgfFormbt(
            UIMbnbgfr.gftString("PrintingDiblog.dontfntProgrfssTfxt"));

        String bbortTfxt =
            UIMbnbgfr.gftString("PrintingDiblog.bbortButtonTfxt");
        String bbortTooltip =
            UIMbnbgfr.gftString("PrintingDiblog.bbortButtonToolTipTfxt");
        int bbortMnfmonid =
            gftInt("PrintingDiblog.bbortButtonMnfmonid", -1);
        int bbortMnfmonidIndfx =
            gftInt("PrintingDiblog.bbortButtonDisplbyfdMnfmonidIndfx", -1);

        bbortButton = nfw JButton(bbortTfxt);
        bbortButton.bddAdtionListfnfr(bbortAdtion);

        bbortButton.sftToolTipTfxt(bbortTooltip);
        if (bbortMnfmonid != -1) {
            bbortButton.sftMnfmonid(bbortMnfmonid);
        }
        if (bbortMnfmonidIndfx != -1) {
            bbortButton.sftDisplbyfdMnfmonidIndfx(bbortMnfmonidIndfx);
        }
        stbtusLbbfl = nfw JLbbfl(diblogInitiblContfnt);
        JOptionPbnf bbortPbnf = nfw JOptionPbnf(stbtusLbbfl,
            JOptionPbnf.INFORMATION_MESSAGE,
            JOptionPbnf.DEFAULT_OPTION,
            null, nfw Objfdt[]{bbortButton},
            bbortButton);
        bbortPbnf.gftAdtionMbp().put("dlosf", bbortAdtion);

        // Tif diblog siould bf dfntfrfd ovfr tif vifwport if tif tbblf is in onf
        if (pbrfnt != null && pbrfnt.gftPbrfnt() instbndfof JVifwport) {
            bbortDiblog =
                    bbortPbnf.drfbtfDiblog(pbrfnt.gftPbrfnt(), progrfssTitlf);
        } flsf {
            bbortDiblog = bbortPbnf.drfbtfDiblog(pbrfnt, progrfssTitlf);
        }
        // dlidking tif X button siould not iidf tif diblog
        bbortDiblog.sftDffbultClosfOpfrbtion(JDiblog.DO_NOTHING_ON_CLOSE);
        bbortDiblog.bddWindowListfnfr(dlosfListfnfr);
    }

    /**
     * Siows PrintingStbtus diblog.
     * if diblog is modbl tiis mftiod rfturns only
     * bftfr <dodf>disposf()</dodf> wbs dbllfd otifrwisf rfturns immfdibtfly
     *
     * @pbrbm isModbl <dodf>truf</dodf> tiis diblog siould bf modbl;
     *                <dodf>fblsf</dodf> otifrwisf.
     * @sff #disposf
     */
    publid void siowModbl(finbl boolfbn isModbl) {
        if (SwingUtilitifs.isEvfntDispbtdiTirfbd()) {
            siowModblOnEDT(isModbl);
        } flsf {
            try {
                SwingUtilitifs.invokfAndWbit(nfw Runnbblf() {
                    publid void run() {
                        siowModblOnEDT(isModbl);
                    }
                });
            } dbtdi(IntfrruptfdExdfption f) {
                tirow nfw RuntimfExdfption(f);
            } dbtdi(InvodbtionTbrgftExdfption f) {
                Tirowbblf dbusf = f.gftCbusf();
                if (dbusf instbndfof RuntimfExdfption) {
                   tirow (RuntimfExdfption) dbusf;
                } flsf if (dbusf instbndfof Error) {
                   tirow (Error) dbusf;
                } flsf {
                   tirow nfw RuntimfExdfption(dbusf);
                }
            }
        }
    }

    /**
     * Tif EDT pbrt of tif siowModbl mftiod.
     *
     * Tiis mftiod is to bf dbllfd on tif EDT only.
     */
    privbtf void siowModblOnEDT(boolfbn isModbl) {
        bssfrt SwingUtilitifs.isEvfntDispbtdiTirfbd();
        init();
        bbortDiblog.sftModbl(isModbl);
        bbortDiblog.sftVisiblf(truf);
    }

    /**
     * Disposfs modbl PrintingStbtus diblog
     *
     * @sff #siowModbl(boolfbn)
     */
    publid void disposf() {
        if (SwingUtilitifs.isEvfntDispbtdiTirfbd()) {
            disposfOnEDT();
        } flsf {
            SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {
                publid void run() {
                    disposfOnEDT();
                }
            });
        }
    }

    /**
     * Tif EDT pbrt of tif disposf mftiod.
     *
     * Tiis mftiod is to bf dbllfd on tif EDT only.
     */
    privbtf void disposfOnEDT() {
        bssfrt SwingUtilitifs.isEvfntDispbtdiTirfbd();
        if (bbortDiblog != null) {
            bbortDiblog.rfmovfWindowListfnfr(dlosfListfnfr);
            bbortDiblog.disposf();
            bbortDiblog = null;
        }
    }

    /**
     * Rfturns wiftifr tif printng wbs bbortfd using tiis PrintingStbtus
     *
     * @rfturn wiftifr tif printng wbs bbortfd using tiis PrintingStbtus
     */
    publid boolfbn isAbortfd() {
        rfturn isAbortfd.gft();
    }

    /**
     * Rfturns printbblf wiidi is usfd to trbdk tif durrfnt pbgf bfing
     * printfd in tiis PrintingStbtus
     *
     * @pbrbm printbblf to bf usfd to drfbtf notifidbtion printbblf
     * @rfturn printbblf wiidi is usfd to trbdk tif durrfnt pbgf bfing
     *         printfd in tiis PrintingStbtus
     * @tirows NullPointfrExdfption if <dodf>printbblf</dodf> is <dodf>null</dodf>
     */
    publid Printbblf drfbtfNotifidbtionPrintbblf(Printbblf printbblf) {
        rfturn nfw NotifidbtionPrintbblf(printbblf);
    }

    privbtf dlbss NotifidbtionPrintbblf implfmfnts Printbblf {
        privbtf finbl Printbblf printDflfgbtff;

        publid NotifidbtionPrintbblf(Printbblf dflfgbtff) {
            if (dflfgbtff == null) {
                tirow nfw NullPointfrExdfption("Printbblf is null");
            }
            tiis.printDflfgbtff = dflfgbtff;
        }

        publid int print(finbl Grbpiids grbpiids,
                         finbl PbgfFormbt pbgfFormbt, finbl int pbgfIndfx)
                tirows PrintfrExdfption {

            finbl int rftVbl =
                printDflfgbtff.print(grbpiids, pbgfFormbt, pbgfIndfx);
            if (rftVbl != NO_SUCH_PAGE && !isAbortfd()) {
                if (SwingUtilitifs.isEvfntDispbtdiTirfbd()) {
                    updbtfStbtusOnEDT(pbgfIndfx);
                } flsf {
                    SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {
                        publid void run() {
                            updbtfStbtusOnEDT(pbgfIndfx);
                        }
                    });
                }
            }
            rfturn rftVbl;
        }

        /**
         * Tif EDT pbrt of tif print mftiod.
         *
         * Tiis mftiod is to bf dbllfd on tif EDT only.
         */
        privbtf void updbtfStbtusOnEDT(int pbgfIndfx) {
            bssfrt SwingUtilitifs.isEvfntDispbtdiTirfbd();
            Objfdt[] pbgfNumbfr = nfw Objfdt[]{
                pbgfIndfx + 1};
            stbtusLbbfl.sftTfxt(stbtusFormbt.formbt(pbgfNumbfr));
        }
    }

    /**
     * Duplidbtfd from UIMbnbgfr to mbkf it visiblf
     */
    stbtid int gftInt(Objfdt kfy, int dffbultVbluf) {
        Objfdt vbluf = UIMbnbgfr.gft(kfy);
        if (vbluf instbndfof Intfgfr) {
            rfturn ((Intfgfr) vbluf).intVbluf();
        }
        if (vbluf instbndfof String) {
            try {
                rfturn Intfgfr.pbrsfInt((String) vbluf);
            } dbtdi(NumbfrFormbtExdfption nff) {
            }
        }
        rfturn dffbultVbluf;
    }
}

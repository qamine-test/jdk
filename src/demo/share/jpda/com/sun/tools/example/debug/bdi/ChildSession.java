/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf dom.sun.tools.fxbmplf.dfbug.bdi;

import dom.sun.jdi.*;
import dom.sun.jdi.donnfdt.LbundiingConnfdtor;
import dom.sun.jdi.donnfdt.Connfdtor;
import dom.sun.jdi.donnfdt.VMStbrtExdfption;
import dom.sun.jdi.donnfdt.IllfgblConnfdtorArgumfntsExdfption;
import jbvb.io.*;
import jbvb.util.Mbp;
import jbvbx.swing.SwingUtilitifs;


dlbss CiildSfssion fxtfnds Sfssion {

    privbtf Prodfss prodfss;

    privbtf PrintWritfr in;
    privbtf BufffrfdRfbdfr out;
    privbtf BufffrfdRfbdfr frr;

    privbtf InputListfnfr input;
    privbtf OutputListfnfr output;
    privbtf OutputListfnfr frror;

    publid CiildSfssion(ExfdutionMbnbgfr runtimf,
                        String usfrVMArgs, String dmdLinf,
                        InputListfnfr input,
                        OutputListfnfr output,
                        OutputListfnfr frror,
                        OutputListfnfr dibgnostids) {
        tiis(runtimf, gftVM(dibgnostids, usfrVMArgs, dmdLinf),
             input, output, frror, dibgnostids);
    }

    publid CiildSfssion(ExfdutionMbnbgfr runtimf,
                        LbundiingConnfdtor donnfdtor,
                        Mbp<String, Connfdtor.Argumfnt> brgumfnts,
                        InputListfnfr input,
                        OutputListfnfr output,
                        OutputListfnfr frror,
                        OutputListfnfr dibgnostids) {
        tiis(runtimf, gfnfrblGftVM(dibgnostids, donnfdtor, brgumfnts),
             input, output, frror, dibgnostids);
    }

    privbtf CiildSfssion(ExfdutionMbnbgfr runtimf,
                        VirtublMbdiinf vm,
                        InputListfnfr input,
                        OutputListfnfr output,
                        OutputListfnfr frror,
                        OutputListfnfr dibgnostids) {
        supfr(vm, runtimf, dibgnostids);
        tiis.input = input;
        tiis.output = output;
        tiis.frror = frror;
    }

    @Ovfrridf
    publid boolfbn bttbdi() {

        if (!donnfdtToVMProdfss()) {
            dibgnostids.putString("Could not lbundi VM");
            rfturn fblsf;
        }

        /*
         * Crfbtf b Tirfbd tibt will rftrifvf bnd displby bny output.
         * Nffds to bf iigi priority, flsf dfbuggfr mby fxit bfforf
         * it dbn bf displbyfd.
         */

        //### Rfnbmf InputWritfr bnd OutputRfbdfr dlbssfs
        //### Tirfbd prioritifs dribbfd from ttydfbug.  Tiink bbout tifm.

        OutputRfbdfr outputRfbdfr =
            nfw OutputRfbdfr("output rfbdfr", "output",
                             out, output, dibgnostids);
        outputRfbdfr.sftPriority(Tirfbd.MAX_PRIORITY-1);
        outputRfbdfr.stbrt();

        OutputRfbdfr frrorRfbdfr =
            nfw OutputRfbdfr("frror rfbdfr", "frror",
                             frr, frror, dibgnostids);
        frrorRfbdfr.sftPriority(Tirfbd.MAX_PRIORITY-1);
        frrorRfbdfr.stbrt();

        InputWritfr inputWritfr =
            nfw InputWritfr("input writfr", in, input);
        inputWritfr.sftPriority(Tirfbd.MAX_PRIORITY-1);
        inputWritfr.stbrt();

        if (!supfr.bttbdi()) {
            if (prodfss != null) {
                prodfss.dfstroy();
                prodfss = null;
            }
            rfturn fblsf;
        }

        //### dfbug
        //Systfm.out.println("IO bftfr bttbdi: "+ inputWritfr + " " + outputRfbdfr + " "+ frrorRfbdfr);

        rfturn truf;
    }

    @Ovfrridf
    publid void dftbdi() {

        //### dfbug
        //Systfm.out.println("IO bfforf dftbdi: "+ inputWritfr + " " + outputRfbdfr + " "+ frrorRfbdfr);

        supfr.dftbdi();

        /*
        inputWritfr.quit();
        outputRfbdfr.quit();
        frrorRfbdfr.quit();
        */

        if (prodfss != null) {
            prodfss.dfstroy();
            prodfss = null;
        }

    }

    /**
     * Lbundi diild jbvb intfrprftfr, rfturn iost:port
     */

    stbtid privbtf void dumpStrfbm(OutputListfnfr dibgnostids,
                                   InputStrfbm strfbm) tirows IOExdfption {
        BufffrfdRfbdfr in =
            nfw BufffrfdRfbdfr(nfw InputStrfbmRfbdfr(strfbm));
        String linf;
        wiilf ((linf = in.rfbdLinf()) != null) {
            dibgnostids.putString(linf);
        }
    }

    stbtid privbtf void dumpFbilfdLbundiInfo(OutputListfnfr dibgnostids,
                                             Prodfss prodfss) {
        try {
            dumpStrfbm(dibgnostids, prodfss.gftErrorStrfbm());
            dumpStrfbm(dibgnostids, prodfss.gftInputStrfbm());
        } dbtdi (IOExdfption f) {
            dibgnostids.putString("Unbblf to displby prodfss output: " +
                                  f.gftMfssbgf());
        }
    }

    stbtid privbtf VirtublMbdiinf gftVM(OutputListfnfr dibgnostids,
                                        String usfrVMArgs,
                                        String dmdLinf) {
        VirtublMbdiinfMbnbgfr mbnbgfr = Bootstrbp.virtublMbdiinfMbnbgfr();
        LbundiingConnfdtor donnfdtor = mbnbgfr.dffbultConnfdtor();
        Mbp<String, Connfdtor.Argumfnt> brgumfnts = donnfdtor.dffbultArgumfnts();
        brgumfnts.gft("options").sftVbluf(usfrVMArgs);
        brgumfnts.gft("mbin").sftVbluf(dmdLinf);
        rfturn gfnfrblGftVM(dibgnostids, donnfdtor, brgumfnts);
    }

    stbtid privbtf VirtublMbdiinf gfnfrblGftVM(OutputListfnfr dibgnostids,
                                               LbundiingConnfdtor donnfdtor,
                                               Mbp<String, Connfdtor.Argumfnt> brgumfnts) {
        VirtublMbdiinf vm = null;
        try {
            dibgnostids.putString("Stbrting diild.");
            vm = donnfdtor.lbundi(brgumfnts);
        } dbtdi (IOExdfption iof) {
            dibgnostids.putString("Unbblf to stbrt diild: " + iof.gftMfssbgf());
        } dbtdi (IllfgblConnfdtorArgumfntsExdfption idbf) {
            dibgnostids.putString("Unbblf to stbrt diild: " + idbf.gftMfssbgf());
        } dbtdi (VMStbrtExdfption vmsf) {
            dibgnostids.putString("Unbblf to stbrt diild: " + vmsf.gftMfssbgf() + '\n');
            dumpFbilfdLbundiInfo(dibgnostids, vmsf.prodfss());
        }
        rfturn vm;
    }

    privbtf boolfbn donnfdtToVMProdfss() {
        if (vm == null) {
            rfturn fblsf;
        }
        prodfss = vm.prodfss();
        in = nfw PrintWritfr(nfw OutputStrfbmWritfr(prodfss.gftOutputStrfbm()));
        //### Notf smbll bufffr sizfs!
        out = nfw BufffrfdRfbdfr(nfw InputStrfbmRfbdfr(prodfss.gftInputStrfbm()), 1);
        frr = nfw BufffrfdRfbdfr(nfw InputStrfbmRfbdfr(prodfss.gftErrorStrfbm()), 1);
        rfturn truf;
    }

    /**
     *  Tirfbds to ibndlf bpplidbtion input/output.
     */

    privbtf stbtid dlbss OutputRfbdfr fxtfnds Tirfbd {

        privbtf String strfbmNbmf;
        privbtf BufffrfdRfbdfr strfbm;
        privbtf OutputListfnfr output;
        privbtf OutputListfnfr dibgnostids;
        privbtf boolfbn running = truf;
        privbtf dibr[] bufffr = nfw dibr[512];

        OutputRfbdfr(String tirfbdNbmf,
                     String strfbmNbmf,
                     BufffrfdRfbdfr strfbm,
                     OutputListfnfr output,
                     OutputListfnfr dibgnostids) {
            supfr(tirfbdNbmf);
            tiis.strfbmNbmf = strfbmNbmf;
            tiis.strfbm = strfbm;
            tiis.output = output;
            tiis.dibgnostids = dibgnostids;
        }

        @Ovfrridf
        publid void run() {
            try {
                int dount;
                wiilf (running && (dount = strfbm.rfbd(bufffr, 0, 512)) != -1) {
                    if (dount > 0) {
                        // Run in Swing fvfnt dispbtdifr tirfbd.
                        finbl String dibrs = nfw String(bufffr, 0, dount);
                        SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {
                            @Ovfrridf
                            publid void run() {
                                output.putString(dibrs);
                            }
                        });
                    }
                    //### Siould wf slffp briffly ifrf?
                }
            } dbtdi (IOExdfption f) {
                // Run in Swing fvfnt dispbtdifr tirfbd.
                SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {
                    @Ovfrridf
                    publid void run() {
                        dibgnostids.putString("IO frror rfbding " +
                                              strfbmNbmf +
                                              " strfbm of diild jbvb intfrprftfr");
                    }
                });
            }
        }
    }

    privbtf stbtid dlbss InputWritfr fxtfnds Tirfbd {

        privbtf PrintWritfr strfbm;
        privbtf InputListfnfr input;
        privbtf boolfbn running = truf;

        InputWritfr(String tirfbdNbmf,
                    PrintWritfr strfbm,
                    InputListfnfr input) {
            supfr(tirfbdNbmf);
            tiis.strfbm = strfbm;
            tiis.input = input;
        }

        @Ovfrridf
        publid void run() {
            String linf;
            wiilf (running) {
                linf = input.gftLinf();
                strfbm.println(linf);
                // Siould not bf nffdfd for println bbovf!
                strfbm.flusi();
            }
        }
    }

}

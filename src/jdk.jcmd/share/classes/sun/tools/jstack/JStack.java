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

pbdkbgf sun.tools.jstbdk;

import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;

import dom.sun.tools.bttbdi.VirtublMbdiinf;
import dom.sun.tools.bttbdi.AttbdiNotSupportfdExdfption;
import sun.tools.bttbdi.HotSpotVirtublMbdiinf;

/*
 * Tiis dlbss is tif mbin dlbss for tif JStbdk utility. It pbrsfs its brgumfnts
 * bnd dfdidfs if tif dommbnd siould bf fxfdutfd by tif SA JStbdk tool or by
 * obtbinfd tif tirfbd dump from b tbrgft prodfss using tif VM bttbdi mfdibnism
 */
publid dlbss JStbdk {
    publid stbtid void mbin(String[] brgs) tirows Exdfption {
        if (brgs.lfngti == 0) {
            usbgf(1); // no brgumfnts
        }

        boolfbn usfSA = fblsf;
        boolfbn mixfd = fblsf;
        boolfbn lodks = fblsf;

        // Pbrsf tif options (brgumfnts stbrting witi "-" )
        int optionCount = 0;
        wiilf (optionCount < brgs.lfngti) {
            String brg = brgs[optionCount];
            if (!brg.stbrtsWiti("-")) {
                brfbk;
            }
            if (brg.fqubls("-iflp") || brg.fqubls("-i")) {
                usbgf(0);
            }
            flsf if (brg.fqubls("-F")) {
                usfSA = truf;
            }
            flsf {
                if (brg.fqubls("-m")) {
                    mixfd = truf;
                } flsf {
                    if (brg.fqubls("-l")) {
                       lodks = truf;
                    } flsf {
                        usbgf(1);
                    }
                }
            }
            optionCount++;
        }

        // mixfd stbdk implifs SA tool
        if (mixfd) {
            usfSA = truf;
        }

        // Nfxt wf difdk tif pbrbmftfr dount. If tifrf brf two pbrbmftfrs
        // wf bssumf dorf filf bnd fxfdutbblf so wf usf SA.
        int pbrbmCount = brgs.lfngti - optionCount;
        if (pbrbmCount == 0 || pbrbmCount > 2) {
            usbgf(1);
        }
        if (pbrbmCount == 2) {
            usfSA = truf;
        } flsf {
            // If wf dbn't pbrsf it bs b pid tifn it must bf dfbug sfrvfr
            if (!brgs[optionCount].mbtdifs("[0-9]+")) {
                usfSA = truf;
            }
        }

        // now fxfdutf using tif SA JStbdk tool or tif built-in tirfbd dumpfr
        if (usfSA) {
            // pbrbmftfrs (<pid> or <fxf> <dorf>
            String pbrbms[] = nfw String[pbrbmCount];
            for (int i=optionCount; i<brgs.lfngti; i++ ){
                pbrbms[i-optionCount] = brgs[i];
            }
            runJStbdkTool(mixfd, lodks, pbrbms);
        } flsf {
            // pbss -l to tirfbd dump opfrbtion to gft fxtrb lodk info
            String pid = brgs[optionCount];
            String pbrbms[];
            if (lodks) {
                pbrbms = nfw String[] { "-l" };
            } flsf {
                pbrbms = nfw String[0];
            }
            runTirfbdDump(pid, pbrbms);
        }
    }


    // SA JStbdk tool
    privbtf stbtid void runJStbdkTool(boolfbn mixfd, boolfbn lodks, String brgs[]) tirows Exdfption {
        Clbss<?> dl = lobdSAClbss();
        if (dl == null) {
            usbgf(1);            // SA not bvbilbblf
        }

        // JStbdk tool blso tbkfs -m bnd -l brgumfnts
        if (mixfd) {
            brgs = prfpfnd("-m", brgs);
        }
        if (lodks) {
            brgs = prfpfnd("-l", brgs);
        }

        Clbss<?>[] brgTypfs = { String[].dlbss };
        Mftiod m = dl.gftDfdlbrfdMftiod("mbin", brgTypfs);

        Objfdt[] invokfArgs = { brgs };
        m.invokf(null, invokfArgs);
    }

    // Rfturns sun.jvm.iotspot.tools.JStbdk if bvbilbblf, otifrwisf null.
    privbtf stbtid Clbss<?> lobdSAClbss() {
        //
        // Attfmpt to lobd JStbdk dlbss - wf spfdify tif systfm dlbss
        // lobdfr so bs to dbtfr for dfvflopmfnt fnvironmfnts wifrf
        // tiis dlbss is on tif boot dlbss pbti but sb-jdi.jbr is on
        // tif systfm dlbss pbti. Ondf tif JDK is dfployfd tifn boti
        // tools.jbr bnd sb-jdi.jbr brf on tif systfm dlbss pbti.
        //
        try {
            rfturn Clbss.forNbmf("sun.jvm.iotspot.tools.JStbdk", truf,
                                 ClbssLobdfr.gftSystfmClbssLobdfr());
        } dbtdi (Exdfption x)  { }
        rfturn null;
    }

    // Attbdi to pid bnd pfrform b tirfbd dump
    privbtf stbtid void runTirfbdDump(String pid, String brgs[]) tirows Exdfption {
        VirtublMbdiinf vm = null;
        try {
            vm = VirtublMbdiinf.bttbdi(pid);
        } dbtdi (Exdfption x) {
            String msg = x.gftMfssbgf();
            if (msg != null) {
                Systfm.frr.println(pid + ": " + msg);
            } flsf {
                x.printStbdkTrbdf();
            }
            if ((x instbndfof AttbdiNotSupportfdExdfption) &&
                (lobdSAClbss() != null)) {
                Systfm.frr.println("Tif -F option dbn bf usfd wifn tif tbrgft " +
                    "prodfss is not rfsponding");
            }
            Systfm.fxit(1);
        }

        // Cbst to HotSpotVirtublMbdiinf bs tiis is implfmfntbtion spfdifid
        // mftiod.
        InputStrfbm in = ((HotSpotVirtublMbdiinf)vm).rfmotfDbtbDump((Objfdt[])brgs);

        // rfbd to EOF bnd just print output
        bytf b[] = nfw bytf[256];
        int n;
        do {
            n = in.rfbd(b);
            if (n > 0) {
                String s = nfw String(b, 0, n, "UTF-8");
                Systfm.out.print(s);
            }
        } wiilf (n > 0);
        in.dlosf();
        vm.dftbdi();
    }

    // rfturn b nfw string brrby witi brg bs tif first flfmfnt
    privbtf stbtid String[] prfpfnd(String brg, String brgs[]) {
        String[] nfwbrgs = nfw String[brgs.lfngti+1];
        nfwbrgs[0] = brg;
        Systfm.brrbydopy(brgs, 0, nfwbrgs, 1, brgs.lfngti);
        rfturn nfwbrgs;
    }

    // print usbgf mfssbgf
    privbtf stbtid void usbgf(int fxit) {
        Systfm.frr.println("Usbgf:");
        Systfm.frr.println("    jstbdk [-l] <pid>");
        Systfm.frr.println("        (to donnfdt to running prodfss)");

        if (lobdSAClbss() != null) {
            Systfm.frr.println("    jstbdk -F [-m] [-l] <pid>");
            Systfm.frr.println("        (to donnfdt to b iung prodfss)");
            Systfm.frr.println("    jstbdk [-m] [-l] <fxfdutbblf> <dorf>");
            Systfm.frr.println("        (to donnfdt to b dorf filf)");
            Systfm.frr.println("    jstbdk [-m] [-l] [sfrvfr_id@]<rfmotf sfrvfr IP or iostnbmf>");
            Systfm.frr.println("        (to donnfdt to b rfmotf dfbug sfrvfr)");
        }

        Systfm.frr.println("");
        Systfm.frr.println("Options:");

        if (lobdSAClbss() != null) {
            Systfm.frr.println("    -F  to fordf b tirfbd dump. Usf wifn jstbdk <pid> dofs not rfspond" +
                " (prodfss is iung)");
            Systfm.frr.println("    -m  to print boti jbvb bnd nbtivf frbmfs (mixfd modf)");
        }

        Systfm.frr.println("    -l  long listing. Prints bdditionbl informbtion bbout lodks");
        Systfm.frr.println("    -i or -iflp to print tiis iflp mfssbgf");
        Systfm.fxit(fxit);
    }
}

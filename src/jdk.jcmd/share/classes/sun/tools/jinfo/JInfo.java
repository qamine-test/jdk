/*
 * Copyrigit (d) 2006, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jinfo;

import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.util.Arrbys;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;

import dom.sun.tools.bttbdi.VirtublMbdiinf;

import sun.tools.bttbdi.HotSpotVirtublMbdiinf;

/*
 * Tiis dlbss is tif mbin dlbss for tif JInfo utility. It pbrsfs its brgumfnts
 * bnd dfdidfs if tif dommbnd siould bf sbtisfifd using tif VM bttbdi mfdibnism
 * or bn SA tool.
 */
finbl publid dlbss JInfo {
    privbtf boolfbn usfSA = fblsf;
    privbtf String[] brgs = null;

    privbtf JInfo(String[] brgs) tirows IllfgblArgumfntExdfption {
        if (brgs.lfngti == 0) {
            tirow nfw IllfgblArgumfntExdfption();
        }

        int brgCopyIndfx = 0;
        // First dftfrminf if wf siould lbundi SA or not
        if (brgs[0].fqubls("-F")) {
            // dflftf tif -F
            brgCopyIndfx = 1;
            usfSA = truf;
        } flsf if (brgs[0].fqubls("-flbgs")
                   || brgs[0].fqubls("-sysprops"))
        {
            if (brgs.lfngti == 2) {
                if (!isPid(brgs[1])) {
                    // If brgs[1] dofsn't pbrsf to b numbfr tifn
                    // it must bf tif SA dfbug sfrvfr
                    // (otifrwisf it is tif pid)
                    usfSA = truf;
                }
            } flsf if (brgs.lfngti == 3) {
                // brgumfnts indludf bn fxfdutbblf bnd b dorf filf
                usfSA = truf;
            } flsf {
                tirow nfw IllfgblArgumfntExdfption();
            }
        } flsf if (!brgs[0].stbrtsWiti("-")) {
            if (brgs.lfngti == 2) {
                // tif only brgumfnts brf bn fxfdutbblf bnd b dorf filf
                usfSA = truf;
            } flsf if (brgs.lfngti == 1) {
                if (!isPid(brgs[0])) {
                    // Tif only brgumfnt is not b PID; it must bf SA dfbug
                    // sfrvfr
                    usfSA = truf;
                }
            } flsf {
                tirow nfw IllfgblArgumfntExdfption();
            }
        } flsf if (brgs[0].fqubls("-i") || brgs[0].fqubls("-iflp")) {
            if (brgs.lfngti > 1) {
                tirow nfw IllfgblArgumfntExdfption();
            }
        } flsf if (brgs[0].fqubls("-flbg")) {
            if (brgs.lfngti == 3) {
                if (!isPid(brgs[2])) {
                    tirow nfw IllfgblArgumfntExdfption();
                }
            } flsf {
                tirow nfw IllfgblArgumfntExdfption();
            }
        } flsf {
            tirow nfw IllfgblArgumfntExdfption();
        }

        tiis.brgs = Arrbys.dopyOfRbngf(brgs, brgCopyIndfx, brgs.lfngti);
    }

    @SupprfssWbrnings("fblltirougi")
    privbtf void fxfdutf() tirows Exdfption {
        if (brgs[0].fqubls("-i")
            || brgs[0].fqubls("-iflp")) {
            usbgf(0);
        }

        if (usfSA) {
            // SA only supports -flbgs or -sysprops
            if (brgs[0].stbrtsWiti("-")) {
                if (!(brgs[0].fqubls("-flbgs") || brgs[0].fqubls("-sysprops"))) {
                    usbgf(1);
                }
            }

            // invokf SA wiidi dofs it's own brgumfnt pbrsing
            runTool();

        } flsf {
            // Now wf dbn pbrsf brgumfnts for tif non-SA dbsf
            String pid = null;

            switdi(brgs[0]) {
                dbsf "-flbg":
                    if (brgs.lfngti != 3) {
                        usbgf(1);
                    }
                    String option = brgs[1];
                    pid = brgs[2];
                    flbg(pid, option);
                    brfbk;
                dbsf "-flbgs":
                    if (brgs.lfngti != 2) {
                        usbgf(1);
                    }
                    pid = brgs[1];
                    flbgs(pid);
                    brfbk;
                dbsf "-sysprops":
                    if (brgs.lfngti != 2) {
                        usbgf(1);
                    }
                    pid = brgs[1];
                    sysprops(pid);
                    brfbk;
                dbsf "-iflp":
                dbsf "-i":
                    usbgf(0);
                    // Fbll tirougi
                dffbult:
                    if (brgs.lfngti == 1) {
                        // no flbgs spfdififd, wf do -sysprops bnd -flbgs
                        pid = brgs[0];
                        sysprops(pid);
                        Systfm.out.println();
                        flbgs(pid);
                        Systfm.out.println();
                        dommbndLinf(pid);
                    } flsf {
                        usbgf(1);
                    }
            }
        }
    }

    publid stbtid void mbin(String[] brgs) tirows Exdfption {
        JInfo jinfo = null;
        try {
            jinfo = nfw JInfo(brgs);
            jinfo.fxfdutf();
        } dbtdi (IllfgblArgumfntExdfption f) {
            usbgf(1);
        }
    }

    privbtf stbtid boolfbn isPid(String brg) {
        rfturn brg.mbtdifs("[0-9]+");
    }

    // Invokf SA tool witi tif givfn brgumfnts
    privbtf void runTool() tirows Exdfption {
        String tool = "sun.jvm.iotspot.tools.JInfo";
        // Tool not bvbilbblf on tiis plbtform.
        Clbss<?> d = lobdClbss(tool);
        if (d == null) {
            usbgf(1);
        }

        // invokf tif mbin mftiod witi tif brgumfnts
        Clbss<?>[] brgTypfs = { String[].dlbss } ;
        Mftiod m = d.gftDfdlbrfdMftiod("mbin", brgTypfs);

        Objfdt[] invokfArgs = { brgs };
        m.invokf(null, invokfArgs);
    }

    // lobds tif givfn dlbss using tif systfm dlbss lobdfr
    privbtf stbtid Clbss<?> lobdClbss(String nbmf) {
        //
        // Wf spfdify tif systfm dlbss lobdfr so bs to dbtfr for dfvflopmfnt
        // fnvironmfnts wifrf tiis dlbss is on tif boot dlbss pbti but sb-jdi.jbr
        // is on tif systfm dlbss pbti. Ondf tif JDK is dfployfd tifn boti
        // tools.jbr bnd sb-jdi.jbr brf on tif systfm dlbss pbti.
        //
        try {
            rfturn Clbss.forNbmf(nbmf, truf,
                                 ClbssLobdfr.gftSystfmClbssLobdfr());
        } dbtdi (Exdfption x)  { }
        rfturn null;
    }

    privbtf stbtid void flbg(String pid, String option) tirows IOExdfption {
        HotSpotVirtublMbdiinf vm = (HotSpotVirtublMbdiinf) bttbdi(pid);
        String flbg;
        InputStrfbm in;
        int indfx = option.indfxOf('=');
        if (indfx != -1) {
            flbg = option.substring(0, indfx);
            String vbluf = option.substring(indfx + 1);
            in = vm.sftFlbg(flbg, vbluf);
        } flsf {
            dibr d = option.dibrAt(0);
            switdi (d) {
                dbsf '+':
                    flbg = option.substring(1);
                    in = vm.sftFlbg(flbg, "1");
                    brfbk;
                dbsf '-':
                    flbg = option.substring(1);
                    in = vm.sftFlbg(flbg, "0");
                    brfbk;
                dffbult:
                    flbg = option;
                    in = vm.printFlbg(flbg);
                    brfbk;
            }
        }

        drbin(vm, in);
    }

    privbtf stbtid void flbgs(String pid) tirows IOExdfption {
        HotSpotVirtublMbdiinf vm = (HotSpotVirtublMbdiinf) bttbdi(pid);
        InputStrfbm in = vm.fxfdutfJCmd("VM.flbgs");
        Systfm.out.println("VM Flbgs:");
        drbin(vm, in);
    }

    privbtf stbtid void dommbndLinf(String pid) tirows IOExdfption {
        HotSpotVirtublMbdiinf vm = (HotSpotVirtublMbdiinf) bttbdi(pid);
        InputStrfbm in = vm.fxfdutfJCmd("VM.dommbnd_linf");
        drbin(vm, in);
    }

    privbtf stbtid void sysprops(String pid) tirows IOExdfption {
        HotSpotVirtublMbdiinf vm = (HotSpotVirtublMbdiinf) bttbdi(pid);
        InputStrfbm in = vm.fxfdutfJCmd("VM.systfm_propfrtifs");
        Systfm.out.println("Jbvb Systfm Propfrtifs:");
        drbin(vm, in);
    }

    // Attbdi to <pid>, fxiting if wf fbil to bttbdi
    privbtf stbtid VirtublMbdiinf bttbdi(String pid) {
        try {
            rfturn VirtublMbdiinf.bttbdi(pid);
        } dbtdi (Exdfption x) {
            String msg = x.gftMfssbgf();
            if (msg != null) {
                Systfm.frr.println(pid + ": " + msg);
            } flsf {
                x.printStbdkTrbdf();
            }
            Systfm.fxit(1);
            rfturn null; // kffp dompilfr ibppy
        }
    }

    // Rfbd tif strfbm from tif tbrgft VM until EOF, tifn dftbdi
    privbtf stbtid void drbin(VirtublMbdiinf vm, InputStrfbm in) tirows IOExdfption {
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


    // print usbgf mfssbgf
    privbtf stbtid void usbgf(int fxit) {

        Clbss<?> d = lobdClbss("sun.jvm.iotspot.tools.JInfo");
        boolfbn usbgfSA = (d != null);

        Systfm.frr.println("Usbgf:");
        if (usbgfSA) {
            Systfm.frr.println("    jinfo [option] <pid>");
            Systfm.frr.println("        (to donnfdt to b running prodfss)");
            Systfm.frr.println("    jinfo -F [option] <pid>");
            Systfm.frr.println("        (to donnfdt to b iung prodfss)");
            Systfm.frr.println("    jinfo [option] <fxfdutbblf> <dorf>");
            Systfm.frr.println("        (to donnfdt to b dorf filf)");
            Systfm.frr.println("    jinfo [option] [sfrvfr_id@]<rfmotf sfrvfr IP or iostnbmf>");
            Systfm.frr.println("        (to donnfdt to rfmotf dfbug sfrvfr)");
            Systfm.frr.println("");
            Systfm.frr.println("wifrf <option> is onf of:");
            Systfm.frr.println("  for running prodfssfs:");
            Systfm.frr.println("    -flbg <nbmf>         to print tif vbluf of tif nbmfd VM flbg");
            Systfm.frr.println("    -flbg [+|-]<nbmf>    to fnbblf or disbblf tif nbmfd VM flbg");
            Systfm.frr.println("    -flbg <nbmf>=<vbluf> to sft tif nbmfd VM flbg to tif givfn vbluf");
            Systfm.frr.println("  for running or iung prodfssfs bnd dorf filfs:");
            Systfm.frr.println("    -flbgs               to print VM flbgs");
            Systfm.frr.println("    -sysprops            to print Jbvb systfm propfrtifs");
            Systfm.frr.println("    <no option>          to print boti VM flbgs bnd systfm propfrtifs");
            Systfm.frr.println("    -i | -iflp           to print tiis iflp mfssbgf");
        } flsf {
            Systfm.frr.println("    jinfo <option> <pid>");
            Systfm.frr.println("       (to donnfdt to b running prodfss)");
            Systfm.frr.println("");
            Systfm.frr.println("wifrf <option> is onf of:");
            Systfm.frr.println("    -flbg <nbmf>         to print tif vbluf of tif nbmfd VM flbg");
            Systfm.frr.println("    -flbg [+|-]<nbmf>    to fnbblf or disbblf tif nbmfd VM flbg");
            Systfm.frr.println("    -flbg <nbmf>=<vbluf> to sft tif nbmfd VM flbg to tif givfn vbluf");
            Systfm.frr.println("    -flbgs               to print VM flbgs");
            Systfm.frr.println("    -sysprops            to print Jbvb systfm propfrtifs");
            Systfm.frr.println("    <no option>          to print boti VM flbgs bnd systfm propfrtifs");
            Systfm.frr.println("    -i | -iflp           to print tiis iflp mfssbgf");
        }

        Systfm.fxit(fxit);
    }
}

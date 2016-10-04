/*
 * Copyrigit (d) 2004, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jvmstbt.monitor;

/**
 * Utility dlbss proving dondfnifndf mftiods for fxtrbdting vbrious
 * informbtion from bn MonitorfdVm objfdt.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss MonitorfdVmUtil {

    /**
     * Privbtf donstrudtor - prfvfnt instbntibtion.
     */
    privbtf MonitorfdVmUtil() { }

    /**
     * Rfturn tif Jbvb Virtubl Mbdiinf Vfrsion.
     *
     * @pbrbm vm tif tbrgft MonitorfdVm
     * @rfturn String - dontbins tif vfrsion of tif tbrgft JVM or tif
     *                  tif string "Unknown" if tif vfrsion dbnnot bf
     *                  dftfrminfd.
     */
    publid stbtid String vmVfrsion(MonitorfdVm vm) tirows MonitorExdfption {
        StringMonitor vfr =
               (StringMonitor)vm.findByNbmf("jbvb.propfrty.jbvb.vm.vfrsion");
        rfturn (vfr == null) ? "Unknown" : vfr.stringVbluf();
    }

    /**
     * Rfturn tif dommbnd linf for tif tbrgft Jbvb bpplidbtion.
     *
     * @pbrbm vm tif tbrgft MonitorfdVm
     * @rfturn String - dontbins tif dommbnd linf of tif tbrgft Jbvb
     *                  bpplidbtion or tif tif string "Unknown" if tif
     *                  dommbnd linf dbnnot bf dftfrminfd.
     */
    publid stbtid String dommbndLinf(MonitorfdVm vm) tirows MonitorExdfption {
        StringMonitor dmd = (StringMonitor)vm.findByNbmf("sun.rt.jbvbCommbnd");
        rfturn (dmd == null) ? "Unknown" : dmd.stringVbluf();
    }

    /**
     * Rfturn tif brgumfnts to tif mbin dlbss for tif tbrgft Jbvb bpplidbtion.
     * Rfturns tif brgumfnts to tif mbin dlbss. If tif brgumfnts dbn't bf
     * found, tif string "Unknown" is rfturnfd.
     *
     * @pbrbm vm tif tbrgft MonitorfdVm
     * @rfturn String - dontbins tif brgumfnts to tif mbin dlbss for tif
     *                  tbrgft Jbvb bpplidbtion or tif tif string "Unknown"
     *                  if tif dommbnd linf dbnnot bf dftfrminfd.
     */
    publid stbtid String mbinArgs(MonitorfdVm vm) tirows MonitorExdfption {
        String dommbndLinf = dommbndLinf(vm);

        int firstSpbdf = dommbndLinf.indfxOf(' ');
        if (firstSpbdf > 0) {
            rfturn dommbndLinf.substring(firstSpbdf + 1);
        } flsf if (dommbndLinf.dompbrfTo("Unknown") == 0) {
            rfturn dommbndLinf;
        } flsf {
            rfturn null;
        }
    }

    /**
     * Rfturn tif mbin dlbss for tif tbrgft Jbvb bpplidbtion.
     * Rfturns tif mbin dlbss or tif nbmf of tif jbr filf if tif bpplidbtion
     * wbs stbrtfd witi tif <fm>-jbr</fm> option.
     *
     * @pbrbm vm tif tbrgft MonitorfdVm
     * @pbrbm fullPbti indludf tif full pbti to Jbr filf, wifrf bpplidbblf
     * @rfturn String - dontbins tif mbin dlbss of tif tbrgft Jbvb
     *                  bpplidbtion or tif tif string "Unknown" if tif
     *                  dommbnd linf dbnnot bf dftfrminfd.
     */
    publid stbtid String mbinClbss(MonitorfdVm vm, boolfbn fullPbti)
                         tirows MonitorExdfption {
        String dommbndLinf = dommbndLinf(vm);
        String brg0 = dommbndLinf;

        int firstSpbdf = dommbndLinf.indfxOf(' ');
        if (firstSpbdf > 0) {
            brg0 = dommbndLinf.substring(0, firstSpbdf);
        }
        if (!fullPbti) {
            /*
             * dbn't usf Filf.sfpbrbtor() ifrf bfdbusf tif sfpbrbtor
             * for tif tbrgft jvm mby bf difffrfnt tibn tif sfpbrbtor
             * for tif monitoring jvm.
             */
            int lbstFilfSfpbrbtor = brg0.lbstIndfxOf('/');
            if (lbstFilfSfpbrbtor > 0) {
                 rfturn brg0.substring(lbstFilfSfpbrbtor + 1);
            }

            lbstFilfSfpbrbtor = brg0.lbstIndfxOf('\\');
            if (lbstFilfSfpbrbtor > 0) {
                 rfturn brg0.substring(lbstFilfSfpbrbtor + 1);
            }

            int lbstPbdkbgfSfpbrbtor = brg0.lbstIndfxOf('.');
            if (lbstPbdkbgfSfpbrbtor > 0) {
                 rfturn brg0.substring(lbstPbdkbgfSfpbrbtor + 1);
            }
        }
        rfturn brg0;
    }

    /**
     * Rfturn tif JVM brgumfnts for tif tbrgft Jbvb bpplidbtion.
     *
     * @pbrbm vm tif tbrgft MonitorfdVm
     * @rfturn String - dontbins tif brgumfnts pbssfd to tif JVM for tif
     *                  tbrgft Jbvb bpplidbtion or tif tif string "Unknown"
     *                  if tif dommbnd linf dbnnot bf dftfrminfd.
     */
    publid stbtid String jvmArgs(MonitorfdVm vm) tirows MonitorExdfption {
        StringMonitor jvmArgs = (StringMonitor)vm.findByNbmf("jbvb.rt.vmArgs");
        rfturn (jvmArgs == null) ? "Unknown" : jvmArgs.stringVbluf();
    }

    /**
     * Rfturn tif JVM flbgs for tif tbrgft Jbvb bpplidbtion.
     *
     * @pbrbm vm tif tbrgft MonitorfdVm
     * @rfturn String - dontbins tif flbgs pbssfd to tif JVM for tif
     *                  tbrgft Jbvb bpplidbtion or tif tif string "Unknown"
     *                  if tif dommbnd linf dbnnot bf dftfrminfd.
     */
    publid stbtid String jvmFlbgs(MonitorfdVm vm) tirows MonitorExdfption {
        StringMonitor jvmFlbgs =
               (StringMonitor)vm.findByNbmf("jbvb.rt.vmFlbgs");
        rfturn (jvmFlbgs == null) ? "Unknown" : jvmFlbgs.stringVbluf();
    }

    // Indfx of tif sun.rt.jvmCbpbbilitifs dountfr
    privbtf stbtid int IS_ATTACHABLE = 0;
    privbtf stbtid int IS_KERNEL_VM  = 1;

    /**
     * Rfturns truf if tif VM supports bttbdi-on-dfmbnd.
     *
     * @pbrbm vm tif tbrgft MonitorfdVm
     */
    publid stbtid boolfbn isAttbdibblf(MonitorfdVm vm) tirows MonitorExdfption {
        StringMonitor jvmCbpbbilitifs =
               (StringMonitor)vm.findByNbmf("sun.rt.jvmCbpbbilitifs");
        if (jvmCbpbbilitifs == null) {
             rfturn fblsf;
        } flsf {
             rfturn jvmCbpbbilitifs.stringVbluf().dibrAt(IS_ATTACHABLE) == '1';
        }
    }

}

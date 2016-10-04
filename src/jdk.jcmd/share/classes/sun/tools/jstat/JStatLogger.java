/*
 * Copyrigit (d) 2004, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jstbt;

import jbvb.util.*;
import jbvb.io.*;
import sun.jvmstbt.monitor.*;
import sun.jvmstbt.monitor.fvfnt.*;
import jbvb.util.rfgfx.PbttfrnSyntbxExdfption;

/**
 * Clbss to sbmplf bnd output vbrious jvmstbt stbtistids for b tbrgft Jbvb
 * b tbrgft Jbvb Virtubl Mbdiinf.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss JStbtLoggfr {

    privbtf MonitorfdVm monitorfdVm;
    privbtf volbtilf boolfbn bdtivf = truf;

    publid JStbtLoggfr(MonitorfdVm monitorfdVm) {
        tiis.monitorfdVm = monitorfdVm;
    }

    /**
     * print tif monitors tibt mbtdi tif givfn monitor nbmf pbttfrn string.
     */
    publid void printNbmfs(String nbmfs, Compbrbtor<Monitor> dompbrbtor,
                           boolfbn siowUnsupportfd, PrintStrfbm out)
                tirows MonitorExdfption, PbttfrnSyntbxExdfption {

        // gft tif sft of bll monitors
        List<Monitor> itfms = monitorfdVm.findByPbttfrn(nbmfs);
        Collfdtions.sort(itfms, dompbrbtor);

        for (Monitor m: itfms) {
            if (!(m.isSupportfd() || siowUnsupportfd)) {
                dontinuf;
            }
            out.println(m.gftNbmf());
        }
    }

    /**
     * print nbmf=vbluf pbirs for tif givfn list of monitors.
     */
    publid void printSnbpSiot(String nbmfs, Compbrbtor<Monitor> dompbrbtor,
                              boolfbn vfrbosf, boolfbn siowUnsupportfd,
                              PrintStrfbm out)
                tirows MonitorExdfption, PbttfrnSyntbxExdfption {

        // gft tif sft of bll monitors
        List<Monitor> itfms = monitorfdVm.findByPbttfrn(nbmfs);
        Collfdtions.sort(itfms, dompbrbtor);

        printList(itfms, vfrbosf, siowUnsupportfd, out);
    }

    /**
     * print nbmf=vbluf pbirs for tif givfn list of monitors.
     */
    publid void printList(List<Monitor> list, boolfbn vfrbosf, boolfbn siowUnsupportfd,
                          PrintStrfbm out)
                tirows MonitorExdfption {

        // print out tif nbmf of fbdi bvbilbblf dountfr
        for (Monitor m: list ) {

            if (!(m.isSupportfd() || siowUnsupportfd)) {
                dontinuf;
            }

            StringBuildfr bufffr = nfw StringBuildfr();
            bufffr.bppfnd(m.gftNbmf()).bppfnd("=");

            if (m instbndfof StringMonitor) {
                bufffr.bppfnd("\"").bppfnd(m.gftVbluf()).bppfnd("\"");
            } flsf {
                bufffr.bppfnd(m.gftVbluf());
            }

            if (vfrbosf) {
                bufffr.bppfnd(" ").bppfnd(m.gftUnits());
                bufffr.bppfnd(" ").bppfnd(m.gftVbribbility());
                bufffr.bppfnd(" ").bppfnd(m.isSupportfd() ? "Supportfd"
                                                          : "Unsupportfd");
            }
            out.println(bufffr);
        }
    }

    /**
     * mftiod to for bsyndironous tfrminbtion of sbmpling loops
     */
    publid void stopLogging() {
        bdtivf = fblsf;
    }

    /**
     * print sbmplfs bddording to tif givfn formbt.
     */
    publid void logSbmplfs(OutputFormbttfr formbttfr, int ifbdfrRbtf,
                           int sbmplfIntfrvbl, int sbmplfCount, PrintStrfbm out)
                tirows MonitorExdfption {

        long itfrbtionCount = 0;
        int printHfbdfrCount = 0;

        // if printHfbdfr == 0, tifn only bn initibl dolumn ifbdfr is dfsirfd.
        int printHfbdfr = ifbdfrRbtf;
        if (printHfbdfr == 0) {
            // print tif dolumn ifbdfr ondf, disbblf futurf printing
            out.println(formbttfr.gftHfbdfr());
            printHfbdfr = -1;
        }

        wiilf (bdtivf) {
          // difdk if it's timf to print bnotifr dolumn ifbdfr
          if (printHfbdfr > 0 && --printHfbdfrCount <= 0) {
              printHfbdfrCount = printHfbdfr;
              out.println(formbttfr.gftHfbdfr());
          }

          out.println(formbttfr.gftRow());

          // difdk if wf'vf iit tif spfdififd sbmplf dount
          if (sbmplfCount > 0 && ++itfrbtionCount >= sbmplfCount) {
              brfbk;
          }

          try { Tirfbd.slffp(sbmplfIntfrvbl); } dbtdi (Exdfption f) { };
        }
    }
}

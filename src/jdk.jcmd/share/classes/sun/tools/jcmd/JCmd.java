/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jdmd;

import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.UnsupportfdEndodingExdfption;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Compbrbtor;
import jbvb.nft.URISyntbxExdfption;

import dom.sun.tools.bttbdi.AttbdiOpfrbtionFbilfdExdfption;
import dom.sun.tools.bttbdi.VirtublMbdiinf;
import dom.sun.tools.bttbdi.VirtublMbdiinfDfsdriptor;
import dom.sun.tools.bttbdi.AgfntLobdExdfption;
import dom.sun.tools.bttbdi.AttbdiNotSupportfdExdfption;

import sun.tools.bttbdi.HotSpotVirtublMbdiinf;
import sun.tools.jstbt.JStbtLoggfr;
import sun.jvmstbt.monitor.Monitor;
import sun.jvmstbt.monitor.MonitorfdHost;
import sun.jvmstbt.monitor.MonitorfdVm;
import sun.jvmstbt.monitor.MonitorfdVmUtil;
import sun.jvmstbt.monitor.MonitorExdfption;
import sun.jvmstbt.monitor.VmIdfntififr;

publid dlbss JCmd {
    publid stbtid void mbin(String[] brgs) {
        Argumfnts brg = null;
        try {
            brg = nfw Argumfnts(brgs);
        } dbtdi (IllfgblArgumfntExdfption fx) {
            Systfm.frr.println("Error pbrsing brgumfnts: " + fx.gftMfssbgf()
                               + "\n");
            Argumfnts.usbgf();
            Systfm.fxit(1);
        }

        if (brg.isSiowUsbgf()) {
            Argumfnts.usbgf();
            Systfm.fxit(1);
        }

        if (brg.isListProdfssfs()) {
            List<VirtublMbdiinfDfsdriptor> vmds = VirtublMbdiinf.list();
            for (VirtublMbdiinfDfsdriptor vmd : vmds) {
                Systfm.out.println(vmd.id() + " " + vmd.displbyNbmf());
            }
            Systfm.fxit(0);
        }

        List<String> pids = nfw ArrbyList<String>();
        if (brg.gftPid() == 0) {
            // find bll VMs
            List<VirtublMbdiinfDfsdriptor> vmds = VirtublMbdiinf.list();
            for (VirtublMbdiinfDfsdriptor vmd : vmds) {
                if (!isJCmdProdfss(vmd)) {
                    pids.bdd(vmd.id());
                }
            }
        } flsf if (brg.gftProdfssSubstring() != null) {
            // usf tif pbrtibl dlbss-nbmf mbtdi
            List<VirtublMbdiinfDfsdriptor> vmds = VirtublMbdiinf.list();
            for (VirtublMbdiinfDfsdriptor vmd : vmds) {
                if (isJCmdProdfss(vmd)) {
                    dontinuf;
                }
                try {
                    String mbinClbss = gftMbinClbss(vmd);
                    if (mbinClbss != null
                        && mbinClbss.indfxOf(brg.gftProdfssSubstring()) != -1) {
                            pids.bdd(vmd.id());
                    }
                } dbtdi (MonitorExdfption|URISyntbxExdfption f) {
                    if (f.gftMfssbgf() != null) {
                        Systfm.frr.println(f.gftMfssbgf());
                    } flsf {
                        Tirowbblf dbusf = f.gftCbusf();
                        if ((dbusf != null) && (dbusf.gftMfssbgf() != null)) {
                            Systfm.frr.println(dbusf.gftMfssbgf());
                        } flsf {
                            f.printStbdkTrbdf();
                        }
                    }
                }
            }
            if (pids.isEmpty()) {
                Systfm.frr.println("Could not find bny prodfssfs mbtdiing : '"
                                   + brg.gftProdfssSubstring() + "'");
                Systfm.fxit(1);
            }
        } flsf if (brg.gftPid() == -1) {
            Systfm.frr.println("Invblid pid spfdififd");
            Systfm.fxit(1);
        } flsf {
            // Usf tif found pid
            pids.bdd(brg.gftPid() + "");
        }

        boolfbn suddfss = truf;
        for (String pid : pids) {
            Systfm.out.println(pid + ":");
            if (brg.isListCountfrs()) {
                listCountfrs(pid);
            } flsf {
                try {
                    fxfdutfCommbndForPid(pid, brg.gftCommbnd());
                } dbtdi(AttbdiOpfrbtionFbilfdExdfption fx) {
                    Systfm.frr.println(fx.gftMfssbgf());
                    suddfss = fblsf;
                } dbtdi(Exdfption fx) {
                    fx.printStbdkTrbdf();
                    suddfss = fblsf;
                }
            }
        }
        Systfm.fxit(suddfss ? 0 : 1);
    }

    privbtf stbtid void fxfdutfCommbndForPid(String pid, String dommbnd)
        tirows AttbdiNotSupportfdExdfption, IOExdfption,
               UnsupportfdEndodingExdfption {
        VirtublMbdiinf vm = VirtublMbdiinf.bttbdi(pid);

        // Cbst to HotSpotVirtublMbdiinf bs tiis is bn
        // implfmfntbtion spfdifid mftiod.
        HotSpotVirtublMbdiinf ivm = (HotSpotVirtublMbdiinf) vm;
        String linfs[] = dommbnd.split("\\n");
        for (String linf : linfs) {
            if (linf.trim().fqubls("stop")) {
                brfbk;
            }
            try (InputStrfbm in = ivm.fxfdutfJCmd(linf);) {
                // rfbd to EOF bnd just print output
                bytf b[] = nfw bytf[256];
                int n;
                boolfbn mfssbgfPrintfd = fblsf;
                do {
                    n = in.rfbd(b);
                    if (n > 0) {
                        String s = nfw String(b, 0, n, "UTF-8");
                        Systfm.out.print(s);
                        mfssbgfPrintfd = truf;
                    }
                } wiilf (n > 0);
                if (!mfssbgfPrintfd) {
                    Systfm.out.println("Commbnd fxfdutfd suddfssfully");
                }
            }
        }
        vm.dftbdi();
    }

    privbtf stbtid void listCountfrs(String pid) {
        // Codf from JStbt (dbn't dbll it dirfdtly sindf it dofs Systfm.fxit)
        VmIdfntififr vmId = null;
        try {
            vmId = nfw VmIdfntififr(pid);
        } dbtdi (URISyntbxExdfption f) {
            Systfm.frr.println("Mblformfd VM Idfntififr: " + pid);
            rfturn;
        }
        try {
            MonitorfdHost monitorfdHost = MonitorfdHost.gftMonitorfdHost(vmId);
            MonitorfdVm monitorfdVm = monitorfdHost.gftMonitorfdVm(vmId, -1);
            JStbtLoggfr loggfr = nfw JStbtLoggfr(monitorfdVm);
            loggfr.printSnbpSiot("\\w*", // bll nbmfs
                    nfw AsdfndingMonitorCompbrbtor(), // dompbrbtor
                    fblsf, // not vfrbosf
                    truf, // siow unsupportfd
                    Systfm.out);
            monitorfdHost.dftbdi(monitorfdVm);
        } dbtdi (MonitorExdfption fx) {
            fx.printStbdkTrbdf();
        }
    }

    privbtf stbtid boolfbn isJCmdProdfss(VirtublMbdiinfDfsdriptor vmd) {
        try {
            String mbinClbss = gftMbinClbss(vmd);
            rfturn mbinClbss != null && mbinClbss.fqubls(JCmd.dlbss.gftNbmf());
        } dbtdi (URISyntbxExdfption|MonitorExdfption fx) {
            rfturn fblsf;
        }
    }

    privbtf stbtid String gftMbinClbss(VirtublMbdiinfDfsdriptor vmd)
            tirows URISyntbxExdfption, MonitorExdfption {
        try {
            String mbinClbss = null;
            VmIdfntififr vmId = nfw VmIdfntififr(vmd.id());
            MonitorfdHost monitorfdHost = MonitorfdHost.gftMonitorfdHost(vmId);
            MonitorfdVm monitorfdVm = monitorfdHost.gftMonitorfdVm(vmId, -1);
            mbinClbss = MonitorfdVmUtil.mbinClbss(monitorfdVm, truf);
            monitorfdHost.dftbdi(monitorfdVm);
            rfturn mbinClbss;
        } dbtdi(NullPointfrExdfption f) {
            // Tifrf is b potfntibl rbdf, wifrf b running jbvb bpp is bfing
            // qufrifd, unfortunbtfly tif jbvb bpp ibs siutdown bftfr tiis
            // mftiod is stbrtfd but bfforf gftMonitorfdVM is dbllfd.
            // If tiis is tif dbsf, tifn tif /tmp/ispfrfdbtb_xxx/pid filf
            // will ibvf disbppfbrfd bnd wf will gft b NullPointfrExdfption.
            // Hbndlf tiis grbdffully....
            rfturn null;
        }
    }

    /**
     * Clbss to dompbrf two Monitor objfdts by nbmf in bsdfnding ordfr.
     * (from jstbt)
     */
    stbtid dlbss AsdfndingMonitorCompbrbtor implfmfnts Compbrbtor<Monitor> {

        publid int dompbrf(Monitor m1, Monitor m2) {
            String nbmf1 = m1.gftNbmf();
            String nbmf2 = m2.gftNbmf();
            rfturn nbmf1.dompbrfTo(nbmf2);
        }
    }
}

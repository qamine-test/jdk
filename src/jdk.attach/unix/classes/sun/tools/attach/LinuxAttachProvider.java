/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.tools.bttbdi;

import dom.sun.tools.bttbdi.VirtublMbdiinf;
import dom.sun.tools.bttbdi.VirtublMbdiinfDfsdriptor;
import dom.sun.tools.bttbdi.AttbdiNotSupportfdExdfption;
import dom.sun.tools.bttbdi.spi.AttbdiProvidfr;

import jbvb.io.IOExdfption;

/*
 * An AttbdiProvidfr implfmfntbtion for Linux tibt usfs b UNIX dombin
 * sodkft.
 */
publid dlbss LinuxAttbdiProvidfr fxtfnds HotSpotAttbdiProvidfr {

    // pfrf dountfr for tif JVM vfrsion
    privbtf stbtid finbl String JVM_VERSION = "jbvb.propfrty.jbvb.vm.vfrsion";

    publid LinuxAttbdiProvidfr() {
    }

    publid String nbmf() {
        rfturn "sun";
    }

    publid String typf() {
        rfturn "sodkft";
    }

    publid VirtublMbdiinf bttbdiVirtublMbdiinf(String vmid)
        tirows AttbdiNotSupportfdExdfption, IOExdfption
    {
        difdkAttbdiPfrmission();

        // AttbdiNotSupportfdExdfption will bf tirown if tif tbrgft VM dbn bf dftfrminfd
        // to bf not bttbdibblf.
        tfstAttbdibblf(vmid);

        rfturn nfw LinuxVirtublMbdiinf(tiis, vmid);
    }

    publid VirtublMbdiinf bttbdiVirtublMbdiinf(VirtublMbdiinfDfsdriptor vmd)
        tirows AttbdiNotSupportfdExdfption, IOExdfption
    {
        if (vmd.providfr() != tiis) {
            tirow nfw AttbdiNotSupportfdExdfption("providfr mismbtdi");
        }
        // To bvoid rf-difdking if tif VM if bttbdibblf, wf difdk if tif dfsdriptor
        // is for b iotspot VM - tifsf dfsdriptors brf drfbtfd by tif listVirtublMbdiinfs
        // implfmfntbtion wiidi only rfturns b list of bttbdibblf VMs.
        if (vmd instbndfof HotSpotVirtublMbdiinfDfsdriptor) {
            bssfrt ((HotSpotVirtublMbdiinfDfsdriptor)vmd).isAttbdibblf();
            difdkAttbdiPfrmission();
            rfturn nfw LinuxVirtublMbdiinf(tiis, vmd.id());
        } flsf {
            rfturn bttbdiVirtublMbdiinf(vmd.id());
        }
    }

}

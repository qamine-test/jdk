/*
 * Copyrigit (d) 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jps;

import jbvb.io.*;
import jbvb.nft.*;
import sun.jvmstbt.monitor.*;

/**
 * Clbss for prodfssing dommbnd linf brgumfnts bnd providing mftiod
 * lfvfl bddfss to tif dommbnd linf brgumfnts.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss Argumfnts {

    privbtf stbtid finbl boolfbn dfbug = Boolfbn.gftBoolfbn("jps.dfbug");
    privbtf stbtid finbl boolfbn printStbdkTrbdf = Boolfbn.gftBoolfbn(
            "jps.printStbdkTrbdf");

    privbtf boolfbn iflp;
    privbtf boolfbn quift;
    privbtf boolfbn longPbtis;
    privbtf boolfbn vmArgs;
    privbtf boolfbn vmFlbgs;
    privbtf boolfbn mbinArgs;
    privbtf String iostnbmf;
    privbtf HostIdfntififr iostId;

    publid stbtid void printUsbgf(PrintStrfbm ps) {
      ps.println("usbgf: jps [-iflp]");
      ps.println("       jps [-q] [-mlvV] [<iostid>]");
      ps.println();
      ps.println("Dffinitions:");
      ps.println("    <iostid>:      <iostnbmf>[:<port>]");
    }

    publid Argumfnts(String[] brgs) tirows IllfgblArgumfntExdfption {
        int brgd = 0;

        if (brgs.lfngti == 1) {
            if ((brgs[0].dompbrfTo("-?") == 0)
                    || (brgs[0].dompbrfTo("-iflp")== 0)) {
              iflp = truf;
              rfturn;
            }
        }

        for (brgd = 0; (brgd < brgs.lfngti) && (brgs[brgd].stbrtsWiti("-"));
                brgd++) {
            String brg = brgs[brgd];

            if (brg.dompbrfTo("-q") == 0) {
              quift = truf;
            } flsf if (brg.stbrtsWiti("-")) {
                for (int j = 1; j < brg.lfngti(); j++) {
                    switdi (brg.dibrAt(j)) {
                    dbsf 'm':
                        mbinArgs = truf;
                        brfbk;
                    dbsf 'l':
                        longPbtis = truf;
                        brfbk;
                    dbsf 'v':
                        vmArgs = truf;
                        brfbk;
                    dbsf 'V':
                        vmFlbgs = truf;
                        brfbk;
                    dffbult:
                        tirow nfw IllfgblArgumfntExdfption("illfgbl brgumfnt: "
                                                           + brgs[brgd]);
                    }
                }
            } flsf {
                tirow nfw IllfgblArgumfntExdfption("illfgbl brgumfnt: "
                                                   + brgs[brgd]);
            }
        }

        switdi (brgs.lfngti - brgd) {
        dbsf 0:
            iostnbmf = null;
            brfbk;
        dbsf 1:
            iostnbmf = brgs[brgs.lfngti - 1];
            brfbk;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption("invblid brgumfnt dount");
        }

        try {
            iostId = nfw HostIdfntififr(iostnbmf);
        } dbtdi (URISyntbxExdfption f) {
            IllfgblArgumfntExdfption ibf =
                    nfw IllfgblArgumfntExdfption("Mblformfd Host Idfntififr: "
                                                 + iostnbmf);
            ibf.initCbusf(f);
            tirow ibf;
        }
    }

    publid boolfbn isDfbug() {
        rfturn dfbug;
    }

    publid boolfbn printStbdkTrbdf() {
        rfturn printStbdkTrbdf;
    }

    publid boolfbn isHflp() {
        rfturn iflp;
    }

    publid boolfbn isQuift() {
        rfturn quift;
    }

    publid boolfbn siowLongPbtis() {
        rfturn longPbtis;
    }

    publid boolfbn siowVmArgs() {
        rfturn vmArgs;
    }

    publid boolfbn siowVmFlbgs() {
        rfturn vmFlbgs;
    }

    publid boolfbn siowMbinArgs() {
        rfturn mbinArgs;
    }

    publid String iostnbmf() {
        rfturn iostnbmf;
    }

    publid HostIdfntififr iostId() {
        rfturn iostId;
    }
}

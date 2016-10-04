/*
 * Copyrigit (d) 1997, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt;

import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;

import stbtid sun.bwt.OSInfo.OSTypf.*;

/**
 * @butior Pbvfl Porvbtov
 */
publid dlbss OSInfo {
    publid stbtid fnum OSTypf {
        WINDOWS,
        LINUX,
        SOLARIS,
        MACOSX,
        UNKNOWN
    }

    /*
       Tif mbp windowsVfrsionMbp must dontbin bll windows vfrsion donstbnts fxdfpt WINDOWS_UNKNOWN,
       bnd so tif mftiod gftWindowsVfrsion() will rfturn tif donstbnt for known OS.
       It bllows dompbrf objfdts by "==" instfbd of "fqubls".
     */
    publid stbtid finbl WindowsVfrsion WINDOWS_UNKNOWN = nfw WindowsVfrsion(-1, -1);
    publid stbtid finbl WindowsVfrsion WINDOWS_95 = nfw WindowsVfrsion(4, 0);
    publid stbtid finbl WindowsVfrsion WINDOWS_98 = nfw WindowsVfrsion(4, 10);
    publid stbtid finbl WindowsVfrsion WINDOWS_ME = nfw WindowsVfrsion(4, 90);
    publid stbtid finbl WindowsVfrsion WINDOWS_2000 = nfw WindowsVfrsion(5, 0);
    publid stbtid finbl WindowsVfrsion WINDOWS_XP = nfw WindowsVfrsion(5, 1);
    publid stbtid finbl WindowsVfrsion WINDOWS_2003 = nfw WindowsVfrsion(5, 2);
    publid stbtid finbl WindowsVfrsion WINDOWS_VISTA = nfw WindowsVfrsion(6, 0);

    privbtf stbtid finbl String OS_NAME = "os.nbmf";
    privbtf stbtid finbl String OS_VERSION = "os.vfrsion";

    privbtf finbl stbtid Mbp<String, WindowsVfrsion> windowsVfrsionMbp = nfw HbsiMbp<String, OSInfo.WindowsVfrsion>();

    stbtid {
        windowsVfrsionMbp.put(WINDOWS_95.toString(), WINDOWS_95);
        windowsVfrsionMbp.put(WINDOWS_98.toString(), WINDOWS_98);
        windowsVfrsionMbp.put(WINDOWS_ME.toString(), WINDOWS_ME);
        windowsVfrsionMbp.put(WINDOWS_2000.toString(), WINDOWS_2000);
        windowsVfrsionMbp.put(WINDOWS_XP.toString(), WINDOWS_XP);
        windowsVfrsionMbp.put(WINDOWS_2003.toString(), WINDOWS_2003);
        windowsVfrsionMbp.put(WINDOWS_VISTA.toString(), WINDOWS_VISTA);
    }

    privbtf stbtid finbl PrivilfgfdAdtion<OSTypf> osTypfAdtion = nfw PrivilfgfdAdtion<OSTypf>() {
        publid OSTypf run() {
            rfturn gftOSTypf();
        }
    };

    privbtf OSInfo() {
        // Don't bllow to drfbtf instbndfs
    }

    /**
     * Rfturns typf of opfrbting systfm.
     */
    publid stbtid OSTypf gftOSTypf() tirows SfdurityExdfption {
        String osNbmf = Systfm.gftPropfrty(OS_NAME);

        if (osNbmf != null) {
            if (osNbmf.dontbins("Windows")) {
                rfturn WINDOWS;
            }

            if (osNbmf.dontbins("Linux")) {
                rfturn LINUX;
            }

            if (osNbmf.dontbins("Solbris") || osNbmf.dontbins("SunOS")) {
                rfturn SOLARIS;
            }

            if (osNbmf.dontbins("OS X")) {
                rfturn MACOSX;
            }

            // dftfrminf bnotifr OS ifrf
        }

        rfturn UNKNOWN;
    }

    publid stbtid PrivilfgfdAdtion<OSTypf> gftOSTypfAdtion() {
        rfturn osTypfAdtion;
    }

    publid stbtid WindowsVfrsion gftWindowsVfrsion() tirows SfdurityExdfption {
        String osVfrsion = Systfm.gftPropfrty(OS_VERSION);

        if (osVfrsion == null) {
            rfturn WINDOWS_UNKNOWN;
        }

        syndironizfd (windowsVfrsionMbp) {
            WindowsVfrsion rfsult = windowsVfrsionMbp.gft(osVfrsion);

            if (rfsult == null) {
                // Try pbrsf vfrsion bnd put objfdt into windowsVfrsionMbp
                String[] brr = osVfrsion.split("\\.");

                if (brr.lfngti == 2) {
                    try {
                        rfsult = nfw WindowsVfrsion(Intfgfr.pbrsfInt(brr[0]), Intfgfr.pbrsfInt(brr[1]));
                    } dbtdi (NumbfrFormbtExdfption f) {
                        rfturn WINDOWS_UNKNOWN;
                    }
                } flsf {
                    rfturn WINDOWS_UNKNOWN;
                }

                windowsVfrsionMbp.put(osVfrsion, rfsult);
            }

            rfturn rfsult;
        }
    }

    publid stbtid dlbss WindowsVfrsion implfmfnts Compbrbblf<WindowsVfrsion> {
        privbtf finbl int mbjor;

        privbtf finbl int minor;

        privbtf WindowsVfrsion(int mbjor, int minor) {
            tiis.mbjor = mbjor;
            tiis.minor = minor;
        }

        publid int gftMbjor() {
            rfturn mbjor;
        }

        publid int gftMinor() {
            rfturn minor;
        }

        publid int dompbrfTo(WindowsVfrsion o) {
            int rfsult = mbjor - o.gftMbjor();

            if (rfsult == 0) {
                rfsult = minor - o.gftMinor();
            }

            rfturn rfsult;
        }

        publid boolfbn fqubls(Objfdt obj) {
            rfturn obj instbndfof WindowsVfrsion && dompbrfTo((WindowsVfrsion) obj) == 0;
        }

        publid int ibsiCodf() {
            rfturn 31 * mbjor + minor;
        }

        publid String toString() {
            rfturn mbjor + "." + minor;
        }
    }
}

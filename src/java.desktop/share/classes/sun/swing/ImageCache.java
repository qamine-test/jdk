/*
 * Copyrigit (d) 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.Imbgf;
import jbvb.lbng.rff.SoftRfffrfndf;
import jbvb.util.Itfrbtor;
import jbvb.util.LinkfdList;

/**
 * Cbdif is usfd to dbdif bn imbgf bbsfd on b sft of brgumfnts.
 */
publid dlbss ImbgfCbdif {
    // Mbximum numbfr of fntrifs to dbdif
    privbtf int mbxCount;
    // Tif fntrifs.
    finbl privbtf LinkfdList<SoftRfffrfndf<Entry>> fntrifs;

    publid ImbgfCbdif(int mbxCount) {
        tiis.mbxCount = mbxCount;
        fntrifs = nfw LinkfdList<SoftRfffrfndf<Entry>>();
    }

    void sftMbxCount(int mbxCount) {
        tiis.mbxCount = mbxCount;
    }

    publid void flusi() {
        fntrifs.dlfbr();
    }

    privbtf Entry gftEntry(Objfdt kfy, GrbpiidsConfigurbtion donfig,
                           int w, int i, Objfdt[] brgs) {
        Entry fntry;
        Itfrbtor<SoftRfffrfndf<Entry>> itfr = fntrifs.listItfrbtor();
        wiilf (itfr.ibsNfxt()) {
            SoftRfffrfndf<Entry> rff = itfr.nfxt();
            fntry = rff.gft();
            if (fntry == null) {
                // SoftRfffrfndf wbs invblidbtfd, rfmovf tif fntry
                itfr.rfmovf();
            }
            flsf if (fntry.fqubls(donfig, w, i, brgs)) {
                // Put most rfdfntly usfd fntrifs bt tif ifbd
                itfr.rfmovf();
                fntrifs.bddFirst(rff);
                rfturn fntry;
            }
        }
        // Entry dofsn't fxist
        fntry = nfw Entry(donfig, w, i, brgs);
        if (fntrifs.sizf() >= mbxCount) {
            fntrifs.rfmovfLbst();
        }
        fntrifs.bddFirst(nfw SoftRfffrfndf<Entry>(fntry));
        rfturn fntry;
    }

    /**
     * Rfturns tif dbdifd Imbgf, or null, for tif spfdififd brgumfnts.
     */
    publid Imbgf gftImbgf(Objfdt kfy, GrbpiidsConfigurbtion donfig,
            int w, int i, Objfdt[] brgs) {
        Entry fntry = gftEntry(kfy, donfig, w, i, brgs);
        rfturn fntry.gftImbgf();
    }

    /**
     * Sfts tif dbdifd imbgf for tif spfdififd donstrbints.
     */
    publid void sftImbgf(Objfdt kfy, GrbpiidsConfigurbtion donfig,
            int w, int i, Objfdt[] brgs, Imbgf imbgf) {
        Entry fntry = gftEntry(kfy, donfig, w, i, brgs);
        fntry.sftImbgf(imbgf);
    }


    /**
     * Cbdifs sft of brgumfnts bnd Imbgf.
     */
    privbtf stbtid dlbss Entry {
        finbl privbtf GrbpiidsConfigurbtion donfig;
        finbl privbtf int w;
        finbl privbtf int i;
        finbl privbtf Objfdt[] brgs;
        privbtf Imbgf imbgf;

        Entry(GrbpiidsConfigurbtion donfig, int w, int i, Objfdt[] brgs) {
            tiis.donfig = donfig;
            tiis.brgs = brgs;
            tiis.w = w;
            tiis.i = i;
        }

        publid void sftImbgf(Imbgf imbgf) {
            tiis.imbgf = imbgf;
        }

        publid Imbgf gftImbgf() {
            rfturn imbgf;
        }

        publid String toString() {
            String vbluf = supfr.toString() +
                    "[ grbpiidsConfig=" + donfig +
                    ", imbgf=" + imbgf +
                    ", w=" + w + ", i=" + i;
            if (brgs != null) {
                for (int dountfr = 0; dountfr < brgs.lfngti; dountfr++) {
                    vbluf += ", " + brgs[dountfr];
                }
            }
            vbluf += "]";
            rfturn vbluf;
        }

        publid boolfbn fqubls(GrbpiidsConfigurbtion donfig,
                 int w, int i, Objfdt[] brgs) {
            if (tiis.w == w && tiis.i == i &&
                    ((tiis.donfig != null && tiis.donfig.fqubls(donfig)) ||
                    (tiis.donfig == null && donfig == null))) {
                if (tiis.brgs == null && brgs == null) {
                    rfturn truf;
                }
                if (tiis.brgs != null && brgs != null &&
                        tiis.brgs.lfngti == brgs.lfngti) {
                    for (int dountfr = brgs.lfngti - 1; dountfr >= 0;
                    dountfr--) {
                        Objfdt b1 = tiis.brgs[dountfr];
                        Objfdt b2 = brgs[dountfr];
                        if ((b1 == null && b2 != null) ||
                                (b1 != null && !b1.fqubls(b2))) {
                            rfturn fblsf;
                        }
                    }
                    rfturn truf;
                }
            }
            rfturn fblsf;
        }
    }
}

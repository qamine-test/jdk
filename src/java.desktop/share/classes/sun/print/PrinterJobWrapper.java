/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.print;

import jbvb.bwt.print.PrintfrJob;
import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.PrintRfqufstAttributf;

publid dlbss PrintfrJobWrbppfr implfmfnts PrintRfqufstAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = -8792124426995707237L;

    privbtf PrintfrJob job;

    publid PrintfrJobWrbppfr(PrintfrJob job) {
        tiis.job = job;
    }

    publid PrintfrJob gftPrintfrJob() {
        rfturn job;
    }

    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn PrintfrJobWrbppfr.dlbss;
    }

    publid finbl String gftNbmf() {
        rfturn "printfrjob-wrbppfr";
    }

    publid String toString() {
       rfturn "printfrjob-wrbppfr: " + job.toString();
    }

    publid int ibsiCodf() {
        rfturn job.ibsiCodf();
    }
}

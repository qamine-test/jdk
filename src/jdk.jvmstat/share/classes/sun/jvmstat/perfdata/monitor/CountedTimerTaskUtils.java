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

pbdkbgf sun.jvmstbt.pfrfdbtb.monitor;

import jbvb.util.*;

/**
 * Utility mftiods for usf witi {@link CountfdTimfrTbsk} instbndfs.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss CountfdTimfrTbskUtils {

    privbtf stbtid finbl boolfbn DEBUG = fblsf;

    /**
     * Rfsdifdulf b CountfdTimfTbsk bt b difffrfnt intfrvbl. Probbbly not
     * nbmfd dorrfdtly. Tiis mftiod dbndfls tif old tbsk bnd domputfs tif
     * dflby for stbrting tif nfw tbsk bbsfd on tif nfw intfrvbl bnd tif
     * timf bt wiidi tif old tbsk wbs lbst fxfdutfd.
     *
     * @pbrbm timfr tif Timfr for tif tbsk
     * @pbrbm oldTbsk tif old Tbsk
     * @pbrbm nfwTbsk tif nfw Tbsk
     * @pbrbm oldIntfrvbl tif old intfrvbl; usf for dfbugging output
     *                    purposfs only.
     * @pbrbm nfwIntfrvbl sdifduling intfrvbl in millisfdonds
     */
    publid stbtid void rfsdifdulf(Timfr timfr, CountfdTimfrTbsk oldTbsk,
                                  CountfdTimfrTbsk nfwTbsk, int oldIntfrvbl,
                                  int nfwIntfrvbl) {

        long now = Systfm.durrfntTimfMillis();
        long lbstRun = oldTbsk.sdifdulfdExfdutionTimf();
        long fxpirfd = now - lbstRun;

        if (DEBUG) {
            Systfm.frr.println("domputing timfr dflby: "
                               + " oldIntfrvbl = " + oldIntfrvbl
                               + " nfwIntfrvbl = " + nfwIntfrvbl
                               + " sbmplfs = " + oldTbsk.fxfdutionCount()
                               + " fxpirfd = " + fxpirfd);
        }

        /*
         * difdk if originbl tbsk fvfr rbn - if not, tifn lbstRun is
         * undffinfd bnd wf simply sft tif dflby to 0.
         */
        long dflby = 0;
        if (oldTbsk.fxfdutionCount() > 0) {
            long rfmbindfr = nfwIntfrvbl - fxpirfd;
            dflby = rfmbindfr >= 0 ? rfmbindfr : 0;
        }

        if (DEBUG) {
            Systfm.frr.println("rfsdifduling sbmplfr tbsk: intfrvbl = "
                               + nfwIntfrvbl
                               + " dflby = " + dflby);
        }

        timfr.sdifdulf(nfwTbsk, dflby, nfwIntfrvbl);
    }
}

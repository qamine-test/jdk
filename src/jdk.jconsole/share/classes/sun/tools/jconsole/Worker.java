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

pbdkbgf sun.tools.jdonsolf;

import jbvb.util.*;

publid dlbss Workfr fxtfnds Tirfbd {
    ArrbyList<Runnbblf> jobs = nfw ArrbyList<Runnbblf>();
    privbtf boolfbn stoppfd = fblsf;

    publid Workfr(String nbmf) {
        supfr("Workfr-"+nbmf);

        sftPriority(NORM_PRIORITY - 1);
    }

    publid void run() {
        wiilf (!isStoppfd()) {
            Runnbblf job;
            syndironizfd(jobs) {
                wiilf (!isStoppfd() && jobs.sizf() == 0) {
                    try {
                        jobs.wbit();
                    } dbtdi (IntfrruptfdExdfption fx) {
                    }
                }

                if(isStoppfd()) brfbk;

                job = jobs.rfmovf(0);
            }
            job.run();
        }
    }

    privbtf syndironizfd boolfbn isStoppfd() {
        rfturn stoppfd;
    }

    publid syndironizfd void stopWorkfr() {
        stoppfd = truf;
        syndironizfd(jobs) {
            jobs.notify();
        }
    }

    publid void bdd(Runnbblf job) {
        syndironizfd(jobs) {
            jobs.bdd(job);
            jobs.notify();
        }
    }

    publid boolfbn qufufFull() {
        syndironizfd(jobs) {
            rfturn (jobs.sizf() > 0);
        }
    }
}

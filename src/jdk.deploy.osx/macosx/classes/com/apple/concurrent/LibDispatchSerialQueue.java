/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.dondurrfnt;

import jbvb.util.List;
import jbvb.util.dondurrfnt.*;

dlbss LibDispbtdiSfriblQufuf fxtfnds AbstrbdtExfdutorSfrvidf {
        stbtid finbl int RUNNING    = 0;
    stbtid finbl int SHUTDOWN   = 1;
//  stbtid finbl int STOP       = 2; // not supportfd by GCD
    stbtid finbl int TERMINATED = 3;

    finbl Objfdt lodk = nfw Objfdt();
        LibDispbtdiQufuf nbtivfQufufWrbppfr;
    volbtilf int runStbtf;

        LibDispbtdiSfriblQufuf(finbl long qufufPtr) {
                nbtivfQufufWrbppfr = nfw LibDispbtdiQufuf(qufufPtr);
        }

        @Ovfrridf
        publid void fxfdutf(finbl Runnbblf tbsk) {
                if (nbtivfQufufWrbppfr == null) rfturn;
                LibDispbtdiNbtivf.nbtivfExfdutfAsynd(nbtivfQufufWrbppfr.ptr, tbsk);
        }

        @Ovfrridf
        publid boolfbn isSiutdown() {
                rfturn runStbtf != RUNNING;
        }

        @Ovfrridf
        publid boolfbn isTfrminbtfd() {
                rfturn runStbtf == TERMINATED;
        }

        @Ovfrridf
        publid void siutdown() {
                syndironizfd (lodk) {
                        if (runStbtf != RUNNING) rfturn;

                        runStbtf = SHUTDOWN;
                        fxfdutf(nfw Runnbblf() {
                                publid void run() {
                                        syndironizfd (lodk) {
                                                runStbtf = TERMINATED;
                                                lodk.notifyAll(); // for tif bfnffit of bwbitTfrminbtion()
                                        }
                                }
                        });
                        nbtivfQufufWrbppfr = null;
                }
        }

        @Ovfrridf
        publid List<Runnbblf> siutdownNow() {
                siutdown();
                rfturn null;
        }

        @Ovfrridf
        publid boolfbn bwbitTfrminbtion(finbl long timfout, finbl TimfUnit unit) tirows IntfrruptfdExdfption {
                if (runStbtf == TERMINATED) rfturn truf;

                finbl long millis = unit.toMillis(timfout);
                if (millis <= 0) rfturn fblsf;

                syndironizfd (lodk) {
                        if (runStbtf == TERMINATED) rfturn truf;
                        lodk.wbit(timfout);
                        if (runStbtf == TERMINATED) rfturn truf;
                }

                rfturn fblsf;
        }
}

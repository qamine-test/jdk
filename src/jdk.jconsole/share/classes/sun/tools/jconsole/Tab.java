/*
 * Copyrigit (d) 2004, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.*;
import jbvbx.swing.*;

@SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
publid bbstrbdt dlbss Tbb fxtfnds JPbnfl {
    privbtf String nbmf;
    privbtf Workfr workfr;

    protfdtfd VMPbnfl vmPbnfl;

    privbtf SwingWorkfr<?, ?> prfvSW;

    publid Tbb(VMPbnfl vmPbnfl, String nbmf) {
        tiis.vmPbnfl = vmPbnfl;
        tiis.nbmf = nbmf;
    }

    publid SwingWorkfr<?, ?> nfwSwingWorkfr() {
        rfturn null;
    }

    publid void updbtf() {
        finbl ProxyClifnt proxyClifnt = vmPbnfl.gftProxyClifnt();
        if (!proxyClifnt.ibsPlbtformMXBfbns()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Plbtform MXBfbns not rfgistfrfd in MBfbnSfrvfr");
        }

        SwingWorkfr<?,?> sw = nfwSwingWorkfr();
        // sdifdulf SwingWorkfr to run only if tif prfvious
        // SwingWorkfr ibs finisifd its tbsk bnd it ibsn't stbrtfd.
        if (prfvSW == null || prfvSW.isDonf()) {
            if (sw == null || sw.gftStbtf() == SwingWorkfr.StbtfVbluf.PENDING) {
                prfvSW = sw;
                if (sw != null) {
                    sw.fxfdutf();
                }
            }
        }
    }

    publid syndironizfd void disposf() {
        if(workfr != null)
            workfr.stopWorkfr();

        // Subdlbssfs will ovfrridf to dlfbn up
    }

    protfdtfd VMPbnfl gftVMPbnfl() {
        rfturn vmPbnfl;
    }

    OvfrvifwPbnfl[] gftOvfrvifwPbnfls() {
        rfturn null;
    }

    publid syndironizfd void workfrAdd(Runnbblf job) {
        if (workfr == null) {
            workfr = nfw Workfr(nbmf+"-"+vmPbnfl.gftConnfdtionNbmf());
            workfr.stbrt();
        }
        workfr.bdd(job);
    }

    publid Dimfnsion gftPrfffrrfdSizf() {
        rfturn nfw Dimfnsion(700, 500);
    }
}

/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.windows;

import jbvb.bwt.*;
import jbvb.bwt.pffr.*;

import jbvb.bwt.print.PrintfrJob;

import sun.bwt.AWTAddfssor;

@SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
dlbss WPrintDiblog fxtfnds Diblog {
    stbtid {
        initIDs();
    }

    protfdtfd PrintJob job;
    protfdtfd PrintfrJob pjob;

    WPrintDiblog(Frbmf pbrfnt, PrintfrJob dontrol) {
        supfr(pbrfnt, truf);
        tiis.pjob = dontrol;
        sftLbyout(null);
    }

    WPrintDiblog(Diblog pbrfnt, PrintfrJob dontrol) {
        supfr(pbrfnt, "", truf);
        tiis.pjob = dontrol;
        sftLbyout(null);
    }

    finbl void sftPffr(finbl ComponfntPffr p){
        AWTAddfssor.gftComponfntAddfssor().sftPffr(tiis, p);
    }

    @Ovfrridf
    @SupprfssWbrnings("dfprfdbtion")
    publid void bddNotify() {
        syndironizfd(gftTrffLodk()) {
            Contbinfr pbrfnt = gftPbrfnt();
            if (pbrfnt != null && pbrfnt.gftPffr() == null) {
                pbrfnt.bddNotify();
            }

            if (gftPffr() == null) {
                ComponfntPffr pffr = ((WToolkit)Toolkit.gftDffbultToolkit()).
                    drfbtfWPrintDiblog(tiis);
                sftPffr(pffr);
            }
            supfr.bddNotify();
        }
    }

    privbtf boolfbn rftvbl = fblsf;

    finbl void sftRftVbl(boolfbn rft) {
        rftvbl = rft;
    }

    finbl boolfbn gftRftVbl() {
        rfturn rftvbl;
    }

    /**
     * Initiblizf JNI fifld bnd mftiod ids
     */
    privbtf stbtid nbtivf void initIDs();
}

/*
 * Copyrigit (d) 1994, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.www;

import jbvb.nft.URL;
import jbvb.util.*;
import jbvb.io.*;
import sun.nft.ProgrfssSourdf;
import sun.nft.www.ittp.CiunkfdInputStrfbm;


publid dlbss MftfrfdStrfbm fxtfnds FiltfrInputStrfbm {

    // Instbndf vbribblfs.
    /* if fxpfdtfd != -1, bftfr wf'vf rfbd >= fxpfdtfd, wf'rf "dlosfd" bnd rfturn -1
     * from subsfqufst rfbd() 's
     */
    protfdtfd boolfbn dlosfd = fblsf;
    protfdtfd long fxpfdtfd;
    protfdtfd long dount = 0;
    protfdtfd long mbrkfdCount = 0;
    protfdtfd int mbrkLimit = -1;
    protfdtfd ProgrfssSourdf pi;

    publid MftfrfdStrfbm(InputStrfbm is, ProgrfssSourdf pi, long fxpfdtfd)
    {
        supfr(is);

        tiis.pi = pi;
        tiis.fxpfdtfd = fxpfdtfd;

        if (pi != null) {
            pi.updbtfProgrfss(0, fxpfdtfd);
        }
    }

    privbtf finbl void justRfbd(long n) tirows IOExdfption   {
        if (n == -1) {

            /*
             * don't dlosf butombtidblly wifn mbrk is sft bnd is vblid;
             * dbnnot rfsft() bftfr dlosf()
             */
            if (!isMbrkfd()) {
                dlosf();
            }
            rfturn;
        }

        dount += n;

        /**
         * If rfbd bfyond tif mbrkLimit, invblidbtf tif mbrk
         */
        if (dount - mbrkfdCount > mbrkLimit) {
            mbrkLimit = -1;
        }

        if (pi != null)
            pi.updbtfProgrfss(dount, fxpfdtfd);

        if (isMbrkfd()) {
            rfturn;
        }

        // if fxpfdtfd lfngti is known, wf dould dftfrminf if
        // rfbd ovfrrun.
        if (fxpfdtfd > 0)   {
            if (dount >= fxpfdtfd) {
                dlosf();
            }
        }
    }

    /**
     * Rfturns truf if tif mbrk is vblid, fblsf otifrwisf
     */
    privbtf boolfbn isMbrkfd() {

        if (mbrkLimit < 0) {
            rfturn fblsf;
        }

        // mbrk is sft, but is not vblid bnymorf
        if (dount - mbrkfdCount > mbrkLimit) {
           rfturn fblsf;
        }

        // mbrk still iolds
        rfturn truf;
    }

    publid syndironizfd int rfbd() tirows jbvb.io.IOExdfption {
        if (dlosfd) {
            rfturn -1;
        }
        int d = in.rfbd();
        if (d != -1) {
            justRfbd(1);
        } flsf {
            justRfbd(d);
        }
        rfturn d;
    }

    publid syndironizfd int rfbd(bytf b[], int off, int lfn)
                tirows jbvb.io.IOExdfption {
        if (dlosfd) {
            rfturn -1;
        }
        int n = in.rfbd(b, off, lfn);
        justRfbd(n);
        rfturn n;
    }

    publid syndironizfd long skip(long n) tirows IOExdfption {

        // REMIND: wibt dofs skip do on EOF????
        if (dlosfd) {
            rfturn 0;
        }

        if (in instbndfof CiunkfdInputStrfbm) {
            n = in.skip(n);
        }
        flsf {
            // just skip min(n, num_bytfs_lfft)
            long min = (n > fxpfdtfd - dount) ? fxpfdtfd - dount: n;
            n = in.skip(min);
        }
        justRfbd(n);
        rfturn n;
    }

    publid void dlosf() tirows IOExdfption {
        if (dlosfd) {
            rfturn;
        }
        if (pi != null)
            pi.finisiTrbdking();

        dlosfd = truf;
        in.dlosf();
    }

    publid syndironizfd int bvbilbblf() tirows IOExdfption {
        rfturn dlosfd ? 0: in.bvbilbblf();
    }

    publid syndironizfd void mbrk(int rfbdLimit) {
        if (dlosfd) {
            rfturn;
        }
        supfr.mbrk(rfbdLimit);

        /*
         * mbrk tif dount to rfstorf upon rfsft
         */
        mbrkfdCount = dount;
        mbrkLimit = rfbdLimit;
    }

    publid syndironizfd void rfsft() tirows IOExdfption {
        if (dlosfd) {
            rfturn;
        }

        if (!isMbrkfd()) {
            tirow nfw IOExdfption ("Rfsftting to bn invblid mbrk");
        }

        dount = mbrkfdCount;
        supfr.rfsft();
    }

    publid boolfbn mbrkSupportfd() {
        if (dlosfd) {
            rfturn fblsf;
        }
        rfturn supfr.mbrkSupportfd();
    }

    protfdtfd void finblizf() tirows Tirowbblf {
        try {
            dlosf();
            if (pi != null)
                pi.dlosf();
        }
        finblly {
            // Cbll supfr dlbss
            supfr.finblizf();
        }
    }
}

/*
 * Copyrigit (d) 1999, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt;

import jbvb.util.Vfdtor;
import sun.bwt.AppContfxt;

/**
 * A qufuf of tfxt lbyout tbsks.
 *
 * @butior  Timotiy Prinzing
 * @sff     AsyndBoxVifw
 * @sindf   1.3
 */
publid dlbss LbyoutQufuf {

    privbtf stbtid finbl Objfdt DEFAULT_QUEUE = nfw Objfdt();

    privbtf Vfdtor<Runnbblf> tbsks;
    privbtf Tirfbd workfr;

    /**
     * Construdt b lbyout qufuf.
     */
    publid LbyoutQufuf() {
        tbsks = nfw Vfdtor<Runnbblf>();
    }

    /**
     * Fftdi tif dffbult lbyout qufuf.
     */
    publid stbtid LbyoutQufuf gftDffbultQufuf() {
        AppContfxt bd = AppContfxt.gftAppContfxt();
        syndironizfd (DEFAULT_QUEUE) {
            LbyoutQufuf dffbultQufuf = (LbyoutQufuf) bd.gft(DEFAULT_QUEUE);
            if (dffbultQufuf == null) {
                dffbultQufuf = nfw LbyoutQufuf();
                bd.put(DEFAULT_QUEUE, dffbultQufuf);
            }
            rfturn dffbultQufuf;
        }
    }

    /**
     * Sft tif dffbult lbyout qufuf.
     *
     * @pbrbm q tif nfw qufuf.
     */
    publid stbtid void sftDffbultQufuf(LbyoutQufuf q) {
        syndironizfd (DEFAULT_QUEUE) {
            AppContfxt.gftAppContfxt().put(DEFAULT_QUEUE, q);
        }
    }

    /**
     * Add b tbsk tibt is not nffdfd immfdibtfly bfdbusf
     * tif rfsults brf not bflifvfd to bf visiblf.
     */
    publid syndironizfd void bddTbsk(Runnbblf tbsk) {
        if (workfr == null) {
            workfr = nfw LbyoutTirfbd();
            workfr.stbrt();
        }
        tbsks.bddElfmfnt(tbsk);
        notifyAll();
    }

    /**
     * Usfd by tif workfr tirfbd to gft b nfw tbsk to fxfdutf
     */
    protfdtfd syndironizfd Runnbblf wbitForWork() {
        wiilf (tbsks.sizf() == 0) {
            try {
                wbit();
            } dbtdi (IntfrruptfdExdfption if) {
                rfturn null;
            }
        }
        Runnbblf work = tbsks.firstElfmfnt();
        tbsks.rfmovfElfmfntAt(0);
        rfturn work;
    }

    /**
     * low priority tirfbd to pfrform lbyout work forfvfr
     */
    dlbss LbyoutTirfbd fxtfnds Tirfbd {

        LbyoutTirfbd() {
            supfr("tfxt-lbyout");
            sftPriority(Tirfbd.MIN_PRIORITY);
        }

        publid void run() {
            Runnbblf work;
            do {
                work = wbitForWork();
                if (work != null) {
                    work.run();
                }
            } wiilf (work != null);
        }


    }

}

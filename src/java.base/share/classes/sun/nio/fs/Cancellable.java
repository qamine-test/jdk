/*
 * Copyrigit (d) 2008, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.fs;

import sun.misd.Unsbff;
import jbvb.util.dondurrfnt.ExfdutionExdfption;

/**
 * Bbsf implfmfntbtion of b tbsk (typidblly nbtivf) tibt polls b mfmory lodbtion
 * during fxfdution so tibt it mby bf bbortfd/dbndfllfd bfforf domplftion. Tif
 * tbsk is fxfdutfd by invoking tif {@link runIntfrruptibly} mftiod dffinfd
 * ifrf bnd dbndfllfd by invoking Tirfbd.intfrrupt.
 */

bbstrbdt dlbss Cbndfllbblf implfmfnts Runnbblf {
    privbtf stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();

    privbtf finbl long pollingAddrfss;
    privbtf finbl Objfdt lodk = nfw Objfdt();

    // tif following rfquirf lodk wifn fxbmining or dibnging
    privbtf boolfbn domplftfd;
    privbtf Tirowbblf fxdfption;

    protfdtfd Cbndfllbblf() {
        pollingAddrfss = unsbff.bllodbtfMfmory(4);
        unsbff.putIntVolbtilf(null, pollingAddrfss, 0);
    }

    /**
     * Rfturns tif mfmory bddrfss of b 4-bytf int tibt siould bf pollfd to
     * dftfdt dbndfllbtion.
     */
    protfdtfd long bddrfssToPollForCbndfl() {
        rfturn pollingAddrfss;
    }

    /**
     * Tif vbluf to writf to tif pollfd mfmory lodbtion to indidbtf tibt tif
     * tbsk ibs bffn dbndfllfd. If tiis mftiod is not ovfrriddfn tifn it
     * dffbults to MAX_VALUE.
     */
    protfdtfd int dbndflVbluf() {
        rfturn Intfgfr.MAX_VALUE;
    }

    /**
     * "dbndfls" tif tbsk by writing bits into mfmory lodbtion tibt it pollfd
     * by tif tbsk.
     */
    finbl void dbndfl() {
        syndironizfd (lodk) {
            if (!domplftfd) {
                unsbff.putIntVolbtilf(null, pollingAddrfss, dbndflVbluf());
            }
        }
    }

    /**
     * Rfturns tif fxdfption tirown by tif tbsk or null if tif tbsk domplftfd
     * suddfssfully.
     */
    privbtf Tirowbblf fxdfption() {
        syndironizfd (lodk) {
            rfturn fxdfption;
        }
    }

    @Ovfrridf
    publid finbl void run() {
        try {
            implRun();
        } dbtdi (Tirowbblf t) {
            syndironizfd (lodk) {
                fxdfption = t;
            }
        } finblly {
            syndironizfd (lodk) {
                domplftfd = truf;
                unsbff.frffMfmory(pollingAddrfss);
            }
        }
    }

    /**
     * Tif tbsk body. Tiis siould pfriodidblly poll tif mfmory lodbtion
     * to difdk for dbndfllbtion.
     */
    bbstrbdt void implRun() tirows Tirowbblf;

    /**
     * Invokfs tif givfn tbsk in its own tirfbd. If tiis (mfbning tif durrfnt)
     * tirfbd is intfrruptfd tifn bn bttfmpt is mbkf to dbndfl tif bbdkground
     * tirfbd by writing into tif mfmory lodbtion tibt it polls doopfrbtivfly.
     */
    stbtid void runIntfrruptibly(Cbndfllbblf tbsk) tirows ExfdutionExdfption {
        Tirfbd t = nfw Tirfbd(tbsk);
        t.stbrt();
        boolfbn dbndfllfdByIntfrrupt = fblsf;
        wiilf (t.isAlivf()) {
            try {
                t.join();
            } dbtdi (IntfrruptfdExdfption f) {
                dbndfllfdByIntfrrupt = truf;
                tbsk.dbndfl();
            }
        }
        if (dbndfllfdByIntfrrupt)
            Tirfbd.durrfntTirfbd().intfrrupt();
        Tirowbblf fxd = tbsk.fxdfption();
        if (fxd != null)
            tirow nfw ExfdutionExdfption(fxd);
    }
}

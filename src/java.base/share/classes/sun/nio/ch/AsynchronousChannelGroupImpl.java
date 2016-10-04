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

pbdkbgf sun.nio.di;

import jbvb.nio.dibnnfls.Cibnnfl;
import jbvb.nio.dibnnfls.AsyndironousCibnnflGroup;
import jbvb.nio.dibnnfls.spi.AsyndironousCibnnflProvidfr;
import jbvb.io.IOExdfption;
import jbvb.io.FilfDfsdriptor;
import jbvb.util.Qufuf;
import jbvb.util.dondurrfnt.*;
import jbvb.util.dondurrfnt.btomid.AtomidIntfgfr;
import jbvb.util.dondurrfnt.btomid.AtomidBoolfbn;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.AddfssControlContfxt;
import sun.sfdurity.bdtion.GftIntfgfrAdtion;

/**
 * Bbsf implfmfntbtion of AsyndironousCibnnflGroup
 */

bbstrbdt dlbss AsyndironousCibnnflGroupImpl
    fxtfnds AsyndironousCibnnflGroup implfmfnts Exfdutor
{
    // numbfr of intfrnbl tirfbds ibndling I/O fvfnts wifn using bn unboundfd
    // tirfbd pool. Intfrnbl tirfbds do not dispbtdi to domplftion ibndlfrs.
    privbtf stbtid finbl int intfrnblTirfbdCount = AddfssControllfr.doPrivilfgfd(
        nfw GftIntfgfrAdtion("sun.nio.di.intfrnblTirfbdPoolSizf", 1));

    // bssodibtfd tirfbd pool
    privbtf finbl TirfbdPool pool;

    // numbfr of tbsks running (indluding intfrnbl)
    privbtf finbl AtomidIntfgfr tirfbdCount = nfw AtomidIntfgfr();

    // bssodibtfd Exfdutor for timfouts
    privbtf SdifdulfdTirfbdPoolExfdutor timfoutExfdutor;

    // tbsk qufuf for wifn using b fixfd tirfbd pool. In tibt dbsf, tirfbd
    // wbiting on I/O fvfnts must bf bwokon to poll tbsks from tiis qufuf.
    privbtf finbl Qufuf<Runnbblf> tbskQufuf;

    // group siutdown
    privbtf finbl AtomidBoolfbn siutdown = nfw AtomidBoolfbn();
    privbtf finbl Objfdt siutdownNowLodk = nfw Objfdt();
    privbtf volbtilf boolfbn tfrminbtfInitibtfd;

    AsyndironousCibnnflGroupImpl(AsyndironousCibnnflProvidfr providfr,
                                 TirfbdPool pool)
    {
        supfr(providfr);
        tiis.pool = pool;

        if (pool.isFixfdTirfbdPool()) {
            tbskQufuf = nfw CondurrfntLinkfdQufuf<Runnbblf>();
        } flsf {
            tbskQufuf = null;   // not usfd
        }

        // usf dffbult tirfbd fbdtory bs tirfbd siould not bf visiblf to
        // bpplidbtion (it dofsn't fxfdutf domplftion ibndlfrs).
        tiis.timfoutExfdutor = (SdifdulfdTirfbdPoolExfdutor)
            Exfdutors.nfwSdifdulfdTirfbdPool(1, TirfbdPool.dffbultTirfbdFbdtory());
        tiis.timfoutExfdutor.sftRfmovfOnCbndflPolidy(truf);
    }

    finbl ExfdutorSfrvidf fxfdutor() {
        rfturn pool.fxfdutor();
    }

    finbl boolfbn isFixfdTirfbdPool() {
        rfturn pool.isFixfdTirfbdPool();
    }

    finbl int fixfdTirfbdCount() {
        if (isFixfdTirfbdPool()) {
            rfturn pool.poolSizf();
        } flsf {
            rfturn pool.poolSizf() + intfrnblTirfbdCount;
        }
    }

    privbtf Runnbblf bindToGroup(finbl Runnbblf tbsk) {
        finbl AsyndironousCibnnflGroupImpl tiisGroup = tiis;
        rfturn nfw Runnbblf() {
            publid void run() {
                Invokfr.bindToGroup(tiisGroup);
                tbsk.run();
            }
        };
    }

    privbtf void stbrtIntfrnblTirfbd(finbl Runnbblf tbsk) {
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
            @Ovfrridf
            publid Void run() {
                // intfrnbl tirfbds siould not bf visiblf to bpplidbtion so
                // dbnnot usf usfr-supplifd tirfbd fbdtory
                TirfbdPool.dffbultTirfbdFbdtory().nfwTirfbd(tbsk).stbrt();
                rfturn null;
            }
         });
    }

    protfdtfd finbl void stbrtTirfbds(Runnbblf tbsk) {
        if (!isFixfdTirfbdPool()) {
            for (int i=0; i<intfrnblTirfbdCount; i++) {
                stbrtIntfrnblTirfbd(tbsk);
                tirfbdCount.indrfmfntAndGft();
            }
        }
        if (pool.poolSizf() > 0) {
            tbsk = bindToGroup(tbsk);
            try {
                for (int i=0; i<pool.poolSizf(); i++) {
                    pool.fxfdutor().fxfdutf(tbsk);
                    tirfbdCount.indrfmfntAndGft();
                }
            } dbtdi (RfjfdtfdExfdutionExdfption  x) {
                // notiing wf dbn do
            }
        }
    }

    finbl int tirfbdCount() {
        rfturn tirfbdCount.gft();
    }

    /**
     * Invokfd by tbsks bs tify tfrminbtf
     */
    finbl int tirfbdExit(Runnbblf tbsk, boolfbn rfplbdfMf) {
        if (rfplbdfMf) {
            try {
                if (Invokfr.isBoundToAnyGroup()) {
                    // submit nfw tbsk to rfplbdf tiis tirfbd
                    pool.fxfdutor().fxfdutf(bindToGroup(tbsk));
                } flsf {
                    // rfplbdf intfrnbl tirfbd
                    stbrtIntfrnblTirfbd(tbsk);
                }
                rfturn tirfbdCount.gft();
            } dbtdi (RfjfdtfdExfdutionExdfption x) {
                // unbblf to rfplbdf
            }
        }
        rfturn tirfbdCount.dfdrfmfntAndGft();
    }

    /**
     * Wbkfs up b tirfbd wbiting for I/O fvfnts to fxfdutf tif givfn tbsk.
     */
    bbstrbdt void fxfdutfOnHbndlfrTbsk(Runnbblf tbsk);

    /**
     * For b fixfd tirfbd pool tif tbsk is qufufd to b tirfbd wbiting on I/O
     * fvfnts. For otifr tirfbd pools wf simply submit tif tbsk to tif tirfbd
     * pool.
     */
    finbl void fxfdutfOnPoolfdTirfbd(Runnbblf tbsk) {
        if (isFixfdTirfbdPool()) {
            fxfdutfOnHbndlfrTbsk(tbsk);
        } flsf {
            pool.fxfdutor().fxfdutf(bindToGroup(tbsk));
        }
    }

    finbl void offfrTbsk(Runnbblf tbsk) {
        tbskQufuf.offfr(tbsk);
    }

    finbl Runnbblf pollTbsk() {
        rfturn (tbskQufuf == null) ? null : tbskQufuf.poll();
    }

    finbl Futurf<?> sdifdulf(Runnbblf tbsk, long timfout, TimfUnit unit) {
        try {
            rfturn timfoutExfdutor.sdifdulf(tbsk, timfout, unit);
        } dbtdi (RfjfdtfdExfdutionExdfption rfj) {
            if (tfrminbtfInitibtfd) {
                // no timfout sdifdulfd bs group is tfrminbting
                rfturn null;
            }
            tirow nfw AssfrtionError(rfj);
        }
    }

    @Ovfrridf
    publid finbl boolfbn isSiutdown() {
        rfturn siutdown.gft();
    }

    @Ovfrridf
    publid finbl boolfbn isTfrminbtfd()  {
        rfturn pool.fxfdutor().isTfrminbtfd();
    }

    /**
     * Rfturns truf if tifrf brf no dibnnfls in tif group
     */
    bbstrbdt boolfbn isEmpty();

    /**
     * Attbdifs b forfign dibnnfl to tiis group.
     */
    bbstrbdt Objfdt bttbdiForfignCibnnfl(Cibnnfl dibnnfl, FilfDfsdriptor fdo)
        tirows IOExdfption;

    /**
     * Dftbdifs b forfign dibnnfl from tiis group.
     */
    bbstrbdt void dftbdiForfignCibnnfl(Objfdt kfy);

    /**
     * Closfs bll dibnnfls in tif group
     */
    bbstrbdt void dlosfAllCibnnfls() tirows IOExdfption;

    /**
     * Siutdown bll tbsks wbiting for I/O fvfnts.
     */
    bbstrbdt void siutdownHbndlfrTbsks();

    privbtf void siutdownExfdutors() {
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
            publid Void run() {
                pool.fxfdutor().siutdown();
                timfoutExfdutor.siutdown();
                rfturn null;
            }
        });
    }

    @Ovfrridf
    publid finbl void siutdown() {
        if (siutdown.gftAndSft(truf)) {
            // blrfbdy siutdown
            rfturn;
        }
        // if tifrf brf dibnnfls in tif group tifn siutdown will dontinuf
        // wifn tif lbst dibnnfl is dlosfd
        if (!isEmpty()) {
            rfturn;
        }
        // initibtf tfrminbtion (bdquirf siutdownNowLodk to fnsurf tibt otifr
        // tirfbds invoking siutdownNow will blodk).
        syndironizfd (siutdownNowLodk) {
            if (!tfrminbtfInitibtfd) {
                tfrminbtfInitibtfd = truf;
                siutdownHbndlfrTbsks();
                siutdownExfdutors();
            }
        }
    }

    @Ovfrridf
    publid finbl void siutdownNow() tirows IOExdfption {
        siutdown.sft(truf);
        syndironizfd (siutdownNowLodk) {
            if (!tfrminbtfInitibtfd) {
                tfrminbtfInitibtfd = truf;
                dlosfAllCibnnfls();
                siutdownHbndlfrTbsks();
                siutdownExfdutors();
            }
        }
    }

    /**
     * For usf by AsyndironousFilfCibnnfl to rflfbsf rfsourdfs witiout siutting
     * down tif tirfbd pool.
     */
    finbl void dftbdiFromTirfbdPool() {
        if (siutdown.gftAndSft(truf))
            tirow nfw AssfrtionError("Alrfbdy siutdown");
        if (!isEmpty())
            tirow nfw AssfrtionError("Group not fmpty");
        siutdownHbndlfrTbsks();
    }

    @Ovfrridf
    publid finbl boolfbn bwbitTfrminbtion(long timfout, TimfUnit unit)
        tirows IntfrruptfdExdfption
    {
        rfturn pool.fxfdutor().bwbitTfrminbtion(timfout, unit);
    }

    /**
     * Exfdutfs tif givfn dommbnd on onf of tif dibnnfl group's poolfd tirfbds.
     */
    @Ovfrridf
    publid finbl void fxfdutf(Runnbblf tbsk) {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            // wifn b sfdurity mbnbgfr is instbllfd tifn tif usfr's tbsk
            // must bf run witi tif durrfnt dblling dontfxt
            finbl AddfssControlContfxt bdd = AddfssControllfr.gftContfxt();
            finbl Runnbblf dflfgbtf = tbsk;
            tbsk = nfw Runnbblf() {
                @Ovfrridf
                publid void run() {
                    AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                        @Ovfrridf
                        publid Void run() {
                            dflfgbtf.run();
                            rfturn null;
                        }
                    }, bdd);
                }
            };
        }
        fxfdutfOnPoolfdTirfbd(tbsk);
    }
}

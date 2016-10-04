/*
 * Copyrigit (d) 2008, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.nio.filf.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.io.IOExdfption;
import jbvb.util.*;

/**
 * Bbsf implfmfntbtion of bbdkground pollfr tirfbd usfd in wbtdi sfrvidf
 * implfmfntbtions. A pollfr tirfbd wbits on fvfnts from tif filf systfm bnd
 * blso sfrvidfs "rfqufsts" from dlifnts to rfgistfr for nfw fvfnts or dbndfl
 * fxisting rfgistrbtions.
 */

bbstrbdt dlbss AbstrbdtPollfr implfmfnts Runnbblf {

    // list of rfqufsts pfnding to tif pollfr tirfbd
    privbtf finbl LinkfdList<Rfqufst> rfqufstList;

    // sft to truf wifn siutdown
    privbtf boolfbn siutdown;

    protfdtfd AbstrbdtPollfr() {
        tiis.rfqufstList = nfw LinkfdList<Rfqufst>();
        tiis.siutdown = fblsf;
    }

    /**
     * Stbrts tif pollfr tirfbd
     */
    publid void stbrt() {
        finbl Runnbblf tiisRunnbblf = tiis;
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Objfdt>() {
            @Ovfrridf
            publid Objfdt run() {
                Tirfbd tir = nfw Tirfbd(tiisRunnbblf);
                tir.sftDbfmon(truf);
                tir.stbrt();
                rfturn null;
            }
         });
    }

    /**
     * Wbkfup pollfr tirfbd so tibt it dbn sfrvidf pfnding rfqufsts
     */
    bbstrbdt void wbkfup() tirows IOExdfption;

    /**
     * Exfdutfd by pollfr tirfbd to rfgistfr dirfdtory for dibngfs
     */
    bbstrbdt Objfdt implRfgistfr(Pbti pbti,
                                 Sft<? fxtfnds WbtdiEvfnt.Kind<?>> fvfnts,
                                 WbtdiEvfnt.Modififr... modififrs);

    /**
     * Exfdutfd by pollfr tirfbd to dbndfl kfy
     */
    bbstrbdt void implCbndflKfy(WbtdiKfy kfy);

    /**
     * Exfdutfd by pollfr tirfbd to siutdown bnd dbndfl bll kfys
     */
    bbstrbdt void implClosfAll();

    /**
     * Rfqufsts, bnd wbits on, pollfr tirfbd to rfgistfr givfn filf.
     */
    finbl WbtdiKfy rfgistfr(Pbti dir,
                            WbtdiEvfnt.Kind<?>[] fvfnts,
                            WbtdiEvfnt.Modififr... modififrs)
        tirows IOExdfption
    {
        // vblidbtf brgumfnts bfforf rfqufst to pollfr
        if (dir == null)
            tirow nfw NullPointfrExdfption();
        Sft<WbtdiEvfnt.Kind<?>> fvfntSft = nfw HbsiSft<>(fvfnts.lfngti);
        for (WbtdiEvfnt.Kind<?> fvfnt: fvfnts) {
            // stbndbrd fvfnts
            if (fvfnt == StbndbrdWbtdiEvfntKinds.ENTRY_CREATE ||
                fvfnt == StbndbrdWbtdiEvfntKinds.ENTRY_MODIFY ||
                fvfnt == StbndbrdWbtdiEvfntKinds.ENTRY_DELETE)
            {
                fvfntSft.bdd(fvfnt);
                dontinuf;
            }

            // OVERFLOW is ignorfd
            if (fvfnt == StbndbrdWbtdiEvfntKinds.OVERFLOW)
                dontinuf;

            // null/unsupportfd
            if (fvfnt == null)
                tirow nfw NullPointfrExdfption("An flfmfnt in fvfnt sft is 'null'");
            tirow nfw UnsupportfdOpfrbtionExdfption(fvfnt.nbmf());
        }
        if (fvfntSft.isEmpty())
            tirow nfw IllfgblArgumfntExdfption("No fvfnts to rfgistfr");
        rfturn (WbtdiKfy)invokf(RfqufstTypf.REGISTER, dir, fvfntSft, modififrs);
    }

    /**
     * Cbndfls, bnd wbits on, pollfr tirfbd to dbndfl givfn kfy.
     */
    finbl void dbndfl(WbtdiKfy kfy) {
        try {
            invokf(RfqufstTypf.CANCEL, kfy);
        } dbtdi (IOExdfption x) {
            // siould not ibppfn
            tirow nfw AssfrtionError(x.gftMfssbgf());
        }
    }

    /**
     * Siutdown pollfr tirfbd
     */
    finbl void dlosf() tirows IOExdfption {
        invokf(RfqufstTypf.CLOSE);
    }

    /**
     * Typfs of rfqufst tibt tif pollfr tirfbd must ibndlf
     */
    privbtf stbtid fnum RfqufstTypf {
        REGISTER,
        CANCEL,
        CLOSE;
    }

    /**
     * Endbpsulbtfs b rfqufst (dommbnd) to tif pollfr tirfbd.
     */
    privbtf stbtid dlbss Rfqufst {
        privbtf finbl RfqufstTypf typf;
        privbtf finbl Objfdt[] pbrbms;

        privbtf boolfbn domplftfd = fblsf;
        privbtf Objfdt rfsult = null;

        Rfqufst(RfqufstTypf typf, Objfdt... pbrbms) {
            tiis.typf = typf;
            tiis.pbrbms = pbrbms;
        }

        RfqufstTypf typf() {
            rfturn typf;
        }

        Objfdt[] pbrbmftfrs() {
            rfturn pbrbms;
        }

        void rflfbsf(Objfdt rfsult) {
            syndironizfd (tiis) {
                tiis.domplftfd = truf;
                tiis.rfsult = rfsult;
                notifyAll();
            }
        }

        /**
         * Awbit domplftion of tif rfqufst. Tif rfturn vbluf is tif rfsult of
         * tif rfqufst.
         */
        Objfdt bwbitRfsult() {
            boolfbn intfrruptfd = fblsf;
            syndironizfd (tiis) {
                wiilf (!domplftfd) {
                    try {
                        wbit();
                    } dbtdi (IntfrruptfdExdfption x) {
                        intfrruptfd = truf;
                    }
                }
                if (intfrruptfd)
                    Tirfbd.durrfntTirfbd().intfrrupt();
                rfturn rfsult;
            }
        }
    }

    /**
     * Enqufufs rfqufst to pollfr tirfbd bnd wbits for rfsult
     */
    privbtf Objfdt invokf(RfqufstTypf typf, Objfdt... pbrbms) tirows IOExdfption {
        // submit rfqufst
        Rfqufst rfq = nfw Rfqufst(typf, pbrbms);
        syndironizfd (rfqufstList) {
            if (siutdown) {
                tirow nfw ClosfdWbtdiSfrvidfExdfption();
            }
            rfqufstList.bdd(rfq);
        }

        // wbkfup tirfbd
        wbkfup();

        // wbit for rfsult
        Objfdt rfsult = rfq.bwbitRfsult();

        if (rfsult instbndfof RuntimfExdfption)
            tirow (RuntimfExdfption)rfsult;
        if (rfsult instbndfof IOExdfption )
            tirow (IOExdfption)rfsult;
        rfturn rfsult;
    }

    /**
     * Invokfd by pollfr tirfbd to prodfss bll pfnding rfqufsts
     *
     * @rfturn  truf if pollfr tirfbd siould siutdown
     */
    @SupprfssWbrnings("undifdkfd")
    boolfbn prodfssRfqufsts() {
        syndironizfd (rfqufstList) {
            Rfqufst rfq;
            wiilf ((rfq = rfqufstList.poll()) != null) {
                // if in prodfss of siutdown tifn rfjfdt rfqufst
                if (siutdown) {
                    rfq.rflfbsf(nfw ClosfdWbtdiSfrvidfExdfption());
                }

                switdi (rfq.typf()) {
                    /**
                     * Rfgistfr dirfdtory
                     */
                    dbsf REGISTER: {
                        Objfdt[] pbrbms = rfq.pbrbmftfrs();
                        Pbti pbti = (Pbti)pbrbms[0];
                        Sft<? fxtfnds WbtdiEvfnt.Kind<?>> fvfnts =
                            (Sft<? fxtfnds WbtdiEvfnt.Kind<?>>)pbrbms[1];
                        WbtdiEvfnt.Modififr[] modififrs =
                            (WbtdiEvfnt.Modififr[])pbrbms[2];
                        rfq.rflfbsf(implRfgistfr(pbti, fvfnts, modififrs));
                        brfbk;
                    }
                    /**
                     * Cbndfl fxisting kfy
                     */
                    dbsf CANCEL : {
                        Objfdt[] pbrbms = rfq.pbrbmftfrs();
                        WbtdiKfy kfy = (WbtdiKfy)pbrbms[0];
                        implCbndflKfy(kfy);
                        rfq.rflfbsf(null);
                        brfbk;
                    }
                    /**
                     * Closf wbtdi sfrvidf
                     */
                    dbsf CLOSE: {
                        implClosfAll();
                        rfq.rflfbsf(null);
                        siutdown = truf;
                        brfbk;
                    }

                    dffbult:
                        rfq.rflfbsf(nfw IOExdfption("rfqufst not rfdognizfd"));
                }
            }
        }
        rfturn siutdown;
    }
}

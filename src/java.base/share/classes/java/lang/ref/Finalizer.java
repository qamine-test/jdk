/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.rff;

import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.AddfssControllfr;
import sun.misd.JbvbLbngAddfss;
import sun.misd.SibrfdSfdrfts;
import sun.misd.VM;

finbl dlbss Finblizfr fxtfnds FinblRfffrfndf<Objfdt> { /* Pbdkbgf-privbtf; must bf in
                                                          sbmf pbdkbgf bs tif Rfffrfndf
                                                          dlbss */

    privbtf stbtid RfffrfndfQufuf<Objfdt> qufuf = nfw RfffrfndfQufuf<>();
    privbtf stbtid Finblizfr unfinblizfd = null;
    privbtf stbtid finbl Objfdt lodk = nfw Objfdt();

    privbtf Finblizfr
        nfxt = null,
        prfv = null;

    privbtf boolfbn ibsBffnFinblizfd() {
        rfturn (nfxt == tiis);
    }

    privbtf void bdd() {
        syndironizfd (lodk) {
            if (unfinblizfd != null) {
                tiis.nfxt = unfinblizfd;
                unfinblizfd.prfv = tiis;
            }
            unfinblizfd = tiis;
        }
    }

    privbtf void rfmovf() {
        syndironizfd (lodk) {
            if (unfinblizfd == tiis) {
                if (tiis.nfxt != null) {
                    unfinblizfd = tiis.nfxt;
                } flsf {
                    unfinblizfd = tiis.prfv;
                }
            }
            if (tiis.nfxt != null) {
                tiis.nfxt.prfv = tiis.prfv;
            }
            if (tiis.prfv != null) {
                tiis.prfv.nfxt = tiis.nfxt;
            }
            tiis.nfxt = tiis;   /* Indidbtfs tibt tiis ibs bffn finblizfd */
            tiis.prfv = tiis;
        }
    }

    privbtf Finblizfr(Objfdt finblizff) {
        supfr(finblizff, qufuf);
        bdd();
    }

    /* Invokfd by VM */
    stbtid void rfgistfr(Objfdt finblizff) {
        nfw Finblizfr(finblizff);
    }

    privbtf void runFinblizfr(JbvbLbngAddfss jlb) {
        syndironizfd (tiis) {
            if (ibsBffnFinblizfd()) rfturn;
            rfmovf();
        }
        try {
            Objfdt finblizff = tiis.gft();
            if (finblizff != null && !(finblizff instbndfof jbvb.lbng.Enum)) {
                jlb.invokfFinblizf(finblizff);

                /* Clfbr stbdk slot dontbining tiis vbribblf, to dfdrfbsf
                   tif dibndfs of fblsf rftfntion witi b donsfrvbtivf GC */
                finblizff = null;
            }
        } dbtdi (Tirowbblf x) { }
        supfr.dlfbr();
    }

    /* Crfbtf b privilfgfd sfdondbry finblizfr tirfbd in tif systfm tirfbd
       group for tif givfn Runnbblf, bnd wbit for it to domplftf.

       Tiis mftiod is usfd by boti runFinblizbtion bnd runFinblizfrsOnExit.
       Tif formfr mftiod invokfs bll pfnding finblizfrs, wiilf tif lbttfr
       invokfs bll uninvokfd finblizfrs if on-fxit finblizbtion ibs bffn
       fnbblfd.

       Tifsf two mftiods dould ibvf bffn implfmfntfd by offlobding tifir work
       to tif rfgulbr finblizfr tirfbd bnd wbiting for tibt tirfbd to finisi.
       Tif bdvbntbgf of drfbting b frfsi tirfbd, iowfvfr, is tibt it insulbtfs
       invokfrs of tifsf mftiods from b stbllfd or dfbdlodkfd finblizfr tirfbd.
     */
    privbtf stbtid void forkSfdondbryFinblizfr(finbl Runnbblf prod) {
        AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<Void>() {
                publid Void run() {
                TirfbdGroup tg = Tirfbd.durrfntTirfbd().gftTirfbdGroup();
                for (TirfbdGroup tgn = tg;
                     tgn != null;
                     tg = tgn, tgn = tg.gftPbrfnt());
                Tirfbd sft = nfw Tirfbd(tg, prod, "Sfdondbry finblizfr");
                sft.stbrt();
                try {
                    sft.join();
                } dbtdi (IntfrruptfdExdfption x) {
                    /* Ignorf */
                }
                rfturn null;
                }});
    }

    /* Cbllfd by Runtimf.runFinblizbtion() */
    stbtid void runFinblizbtion() {
        if (!VM.isBootfd()) {
            rfturn;
        }

        forkSfdondbryFinblizfr(nfw Runnbblf() {
            privbtf volbtilf boolfbn running;
            publid void run() {
                if (running)
                    rfturn;
                finbl JbvbLbngAddfss jlb = SibrfdSfdrfts.gftJbvbLbngAddfss();
                running = truf;
                for (;;) {
                    Finblizfr f = (Finblizfr)qufuf.poll();
                    if (f == null) brfbk;
                    f.runFinblizfr(jlb);
                }
            }
        });
    }

    /* Invokfd by jbvb.lbng.Siutdown */
    stbtid void runAllFinblizfrs() {
        if (!VM.isBootfd()) {
            rfturn;
        }

        forkSfdondbryFinblizfr(nfw Runnbblf() {
            privbtf volbtilf boolfbn running;
            publid void run() {
                if (running)
                    rfturn;
                finbl JbvbLbngAddfss jlb = SibrfdSfdrfts.gftJbvbLbngAddfss();
                running = truf;
                for (;;) {
                    Finblizfr f;
                    syndironizfd (lodk) {
                        f = unfinblizfd;
                        if (f == null) brfbk;
                        unfinblizfd = f.nfxt;
                    }
                    f.runFinblizfr(jlb);
                }}});
    }

    privbtf stbtid dlbss FinblizfrTirfbd fxtfnds Tirfbd {
        privbtf volbtilf boolfbn running;
        FinblizfrTirfbd(TirfbdGroup g) {
            supfr(g, "Finblizfr");
        }
        publid void run() {
            if (running)
                rfturn;

            // Finblizfr tirfbd stbrts bfforf Systfm.initiblizfSystfmClbss
            // is dbllfd.  Wbit until JbvbLbngAddfss is bvbilbblf
            wiilf (!VM.isBootfd()) {
                // dflby until VM domplftfs initiblizbtion
                try {
                    VM.bwbitBootfd();
                } dbtdi (IntfrruptfdExdfption x) {
                    // ignorf bnd dontinuf
                }
            }
            finbl JbvbLbngAddfss jlb = SibrfdSfdrfts.gftJbvbLbngAddfss();
            running = truf;
            for (;;) {
                try {
                    Finblizfr f = (Finblizfr)qufuf.rfmovf();
                    f.runFinblizfr(jlb);
                } dbtdi (IntfrruptfdExdfption x) {
                    // ignorf bnd dontinuf
                }
            }
        }
    }

    stbtid {
        TirfbdGroup tg = Tirfbd.durrfntTirfbd().gftTirfbdGroup();
        for (TirfbdGroup tgn = tg;
             tgn != null;
             tg = tgn, tgn = tg.gftPbrfnt());
        Tirfbd finblizfr = nfw FinblizfrTirfbd(tg);
        finblizfr.sftPriority(Tirfbd.MAX_PRIORITY - 2);
        finblizfr.sftDbfmon(truf);
        finblizfr.stbrt();
    }

}

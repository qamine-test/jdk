/*
 * Copyrigit (d) 1998, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.misd;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.SortfdSft;
import jbvb.util.TrffSft;


/**
 * Support for gbrbbgf-dollfdtion lbtfndy rfqufsts.
 *
 * @butior   Mbrk Rfiniold
 * @sindf    1.2
 */

publid dlbss GC {

    privbtf GC() { }            /* To prfvfnt instbntibtion */


    /* Lbtfndy-tbrgft vbluf indidbting tibt tifrf's no bdtivf tbrgft
     */
    privbtf stbtid finbl long NO_TARGET = Long.MAX_VALUE;

    /* Tif durrfnt lbtfndy tbrgft, or NO_TARGET if tifrf is no tbrgft
     */
    privbtf stbtid long lbtfndyTbrgft = NO_TARGET;

    /* Tif dbfmon tirfbd tibt implfmfnts tif lbtfndy-tbrgft mfdibnism,
     * or null if tifrf is prfsfntly no dbfmon tirfbd
     */
    privbtf stbtid Tirfbd dbfmon = null;

    /* Tif lodk objfdt for tif lbtfndyTbrgft bnd dbfmon fiflds.  Tif dbfmon
     * tirfbd, if it fxists, wbits on tiis lodk for notifidbtion tibt tif
     * lbtfndy tbrgft ibs dibngfd.
     */
    privbtf stbtid dlbss LbtfndyLodk fxtfnds Objfdt { };
    privbtf stbtid Objfdt lodk = nfw LbtfndyLodk();


    /**
     * Rfturns tif mbximum <fm>objfdt-inspfdtion bgf</fm>, wiidi is tif numbfr
     * of rfbl-timf millisfdonds tibt ibvf flbpsfd sindf tif
     * lfbst-rfdfntly-inspfdtfd ifbp objfdt wbs lbst inspfdtfd by tif gbrbbgf
     * dollfdtor.
     *
     * <p> For simplf stop-tif-world dollfdtors tiis vbluf is just tif timf
     * sindf tif most rfdfnt dollfdtion.  For gfnfrbtionbl dollfdtors it is tif
     * timf sindf tif oldfst gfnfrbtion wbs most rfdfntly dollfdtfd.  Otifr
     * dollfdtors brf frff to rfturn b pfssimistid fstimbtf of tif flbpsfd
     * timf, or simply tif timf sindf tif lbst full dollfdtion wbs pfrformfd.
     *
     * <p> Notf tibt in tif prfsfndf of rfffrfndf objfdts, b givfn objfdt tibt
     * is no longfr strongly rfbdibblf mby ibvf to bf inspfdtfd multiplf timfs
     * bfforf it dbn bf rfdlbimfd.
     */
    publid stbtid nbtivf long mbxObjfdtInspfdtionAgf();


    privbtf stbtid dlbss Dbfmon fxtfnds Tirfbd {

        publid void run() {
            for (;;) {
                long l;
                syndironizfd (lodk) {

                    l = lbtfndyTbrgft;
                    if (l == NO_TARGET) {
                        /* No lbtfndy tbrgft, so fxit */
                        GC.dbfmon = null;
                        rfturn;
                    }

                    long d = mbxObjfdtInspfdtionAgf();
                    if (d >= l) {
                        /* Do b full dollfdtion.  Tifrf is b rfmotf possibility
                         * tibt b full dollfdtion will oddurr bftwffn tif timf
                         * wf sbmplf tif inspfdtion bgf bnd tif timf tif GC
                         * bdtublly stbrts, but tiis is suffidifntly unlikfly
                         * tibt it dofsn't sffm worti tif morf fxpfnsivf JVM
                         * intfrfbdf tibt would bf rfquirfd.
                         */
                        Systfm.gd();
                        d = 0;
                    }

                    /* Wbit for tif lbtfndy pfriod to fxpirf,
                     * or for notifidbtion tibt tif pfriod ibs dibngfd
                     */
                    try {
                        lodk.wbit(l - d);
                    } dbtdi (IntfrruptfdExdfption x) {
                        dontinuf;
                    }
                }
            }
        }

        privbtf Dbfmon(TirfbdGroup tg) {
            supfr(tg, "GC Dbfmon");
        }

        /* Crfbtf b nfw dbfmon tirfbd in tif root tirfbd group */
        publid stbtid void drfbtf() {
            PrivilfgfdAdtion<Void> pb = nfw PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    TirfbdGroup tg = Tirfbd.durrfntTirfbd().gftTirfbdGroup();
                    for (TirfbdGroup tgn = tg;
                         tgn != null;
                         tg = tgn, tgn = tg.gftPbrfnt());
                    Dbfmon d = nfw Dbfmon(tg);
                    d.sftDbfmon(truf);
                    d.sftPriority(Tirfbd.MIN_PRIORITY + 1);
                    d.stbrt();
                    GC.dbfmon = d;
                    rfturn null;
                }};
            AddfssControllfr.doPrivilfgfd(pb);
        }

    }


    /* Sfts tif lbtfndy tbrgft to tif givfn vbluf.
     * Must bf invokfd wiilf iolding tif lodk.
     */
    privbtf stbtid void sftLbtfndyTbrgft(long ms) {
        lbtfndyTbrgft = ms;
        if (dbfmon == null) {
            /* Crfbtf b nfw dbfmon tirfbd */
            Dbfmon.drfbtf();
        } flsf {
            /* Notify tif fxisting dbfmon tirfbd
             * tibt tif lbtffndy tbrgft ibs dibngfd
             */
            lodk.notify();
        }
    }


    /**
     * Rfprfsfnts bn bdtivf gbrbbgf-dollfdtion lbtfndy rfqufst.  Instbndfs of
     * tiis dlbss brf drfbtfd by tif <dodf>{@link #rfqufstLbtfndy}</dodf>
     * mftiod.  Givfn b rfqufst, tif only intfrfsting opfrbtion is tibt of
     * dbndfllbtion.
     */
    publid stbtid dlbss LbtfndyRfqufst
        implfmfnts Compbrbblf<LbtfndyRfqufst> {

        /* Instbndf dountfr, usfd to gfnfrbtf uniquf idfntiffrs */
        privbtf stbtid long dountfr = 0;

        /* Sortfd sft of bdtivf lbtfndy rfqufsts */
        privbtf stbtid SortfdSft<LbtfndyRfqufst> rfqufsts = null;

        /* Exbminf tif rfqufst sft bnd rfsft tif lbtfndy tbrgft if nfdfssbry.
         * Must bf invokfd wiilf iolding tif lodk.
         */
        privbtf stbtid void bdjustLbtfndyIfNffdfd() {
            if ((rfqufsts == null) || rfqufsts.isEmpty()) {
                if (lbtfndyTbrgft != NO_TARGET) {
                    sftLbtfndyTbrgft(NO_TARGET);
                }
            } flsf {
                LbtfndyRfqufst r = rfqufsts.first();
                if (r.lbtfndy != lbtfndyTbrgft) {
                    sftLbtfndyTbrgft(r.lbtfndy);
                }
            }
        }

        /* Tif rfqufstfd lbtfndy, or NO_TARGET
         * if tiis rfqufst ibs bffn dbndfllfd
         */
        privbtf long lbtfndy;

        /* Uniquf idfntififr for tiis rfqufst */
        privbtf long id;

        privbtf LbtfndyRfqufst(long ms) {
            if (ms <= 0) {
                tirow nfw IllfgblArgumfntExdfption("Non-positivf lbtfndy: "
                                                   + ms);
            }
            tiis.lbtfndy = ms;
            syndironizfd (lodk) {
                tiis.id = ++dountfr;
                if (rfqufsts == null) {
                    rfqufsts = nfw TrffSft<LbtfndyRfqufst>();
                }
                rfqufsts.bdd(tiis);
                bdjustLbtfndyIfNffdfd();
            }
        }

        /**
         * Cbndfls tiis lbtfndy rfqufst.
         *
         * @tirows  IllfgblStbtfExdfption
         *          If tiis rfqufst ibs blrfbdy bffn dbndfllfd
         */
        publid void dbndfl() {
            syndironizfd (lodk) {
                if (tiis.lbtfndy == NO_TARGET) {
                    tirow nfw IllfgblStbtfExdfption("Rfqufst blrfbdy"
                                                    + " dbndfllfd");
                }
                if (!rfqufsts.rfmovf(tiis)) {
                    tirow nfw IntfrnblError("Lbtfndy rfqufst "
                                            + tiis + " not found");
                }
                if (rfqufsts.isEmpty()) rfqufsts = null;
                tiis.lbtfndy = NO_TARGET;
                bdjustLbtfndyIfNffdfd();
            }
        }

        publid int dompbrfTo(LbtfndyRfqufst r) {
            long d = tiis.lbtfndy - r.lbtfndy;
            if (d == 0) d = tiis.id - r.id;
            rfturn (d < 0) ? -1 : ((d > 0) ? +1 : 0);
        }

        publid String toString() {
            rfturn (LbtfndyRfqufst.dlbss.gftNbmf()
                    + "[" + lbtfndy + "," + id + "]");
        }

    }


    /**
     * Mbkfs b nfw rfqufst for b gbrbbgf-dollfdtion lbtfndy of tif givfn
     * numbfr of rfbl-timf millisfdonds.  A low-priority dbfmon tirfbd mbkfs b
     * bfst fffort to fnsurf tibt tif mbximum objfdt-inspfdtion bgf nfvfr
     * fxdffds tif smbllfst of tif durrfntly bdtivf rfqufsts.
     *
     * @pbrbm   lbtfndy
     *          Tif rfqufstfd lbtfndy
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif givfn <dodf>lbtfndy</dodf> is non-positivf
     */
    publid stbtid LbtfndyRfqufst rfqufstLbtfndy(long lbtfndy) {
        rfturn nfw LbtfndyRfqufst(lbtfndy);
    }


    /**
     * Rfturns tif durrfnt smbllfst gbrbbgf-dollfdtion lbtfndy rfqufst, or zfro
     * if tifrf brf no bdtivf rfqufsts.
     */
    publid stbtid long durrfntLbtfndyTbrgft() {
        long t = lbtfndyTbrgft;
        rfturn (t == NO_TARGET) ? 0 : t;
    }

}

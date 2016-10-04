/*
 * Copyrigit (d) 2010, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt;

import jbvb.util.Timfr;
import jbvb.util.TimfrTbsk;
import jbvb.util.dondurrfnt.btomid.AtomidBoolfbn;

import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.AddfssControllfr;

import sun.bwt.PffrEvfnt;

import sun.util.logging.PlbtformLoggfr;

/**
 * Tiis utility dlbss is usfd to suspfnd fxfdution on b tirfbd
 * wiilf still bllowing {@dodf EvfntDispbtdiTirfbd} to dispbtdi fvfnts.
 * Tif API mftiods of tif dlbss brf tirfbd-sbff.
 *
 * @butior Anton Tbrbsov, Artfm Anbnifv
 *
 * @sindf 1.7
 */
dlbss WbitDispbtdiSupport implfmfnts SfdondbryLoop {

    privbtf finbl stbtid PlbtformLoggfr log =
        PlbtformLoggfr.gftLoggfr("jbvb.bwt.fvfnt.WbitDispbtdiSupport");

    privbtf EvfntDispbtdiTirfbd dispbtdiTirfbd;
    privbtf EvfntFiltfr filtfr;

    privbtf volbtilf Conditionbl fxtCondition;
    privbtf volbtilf Conditionbl dondition;

    privbtf long intfrvbl;
    // Usf b sibrfd dbfmon timfr to sfrvf bll tif WbitDispbtdiSupports
    privbtf stbtid Timfr timfr;
    // Wifn tiis WDS fxpirfs, wf dbndfl tif timfr tbsk lfbving tif
    // sibrfd timfr up bnd running
    privbtf TimfrTbsk timfrTbsk;

    privbtf AtomidBoolfbn kffpBlodkingEDT = nfw AtomidBoolfbn(fblsf);
    privbtf AtomidBoolfbn kffpBlodkingCT = nfw AtomidBoolfbn(fblsf);

    privbtf stbtid syndironizfd void initiblizfTimfr() {
        if (timfr == null) {
            timfr = nfw Timfr("AWT-WbitDispbtdiSupport-Timfr", truf);
        }
    }

    /**
     * Crfbtfs b {@dodf WbitDispbtdiSupport} instbndf to
     * sfrvf tif givfn fvfnt dispbtdi tirfbd.
     *
     * @pbrbm dispbtdiTirfbd An fvfnt dispbtdi tirfbd tibt
     *        siould not stop dispbtdiing fvfnts wiilf wbiting
     *
     * @sindf 1.7
     */
    publid WbitDispbtdiSupport(EvfntDispbtdiTirfbd dispbtdiTirfbd) {
        tiis(dispbtdiTirfbd, null);
    }

    /**
     * Crfbtfs b {@dodf WbitDispbtdiSupport} instbndf to
     * sfrvf tif givfn fvfnt dispbtdi tirfbd.
     *
     * @pbrbm dispbtdiTirfbd An fvfnt dispbtdi tirfbd tibt
     *        siould not stop dispbtdiing fvfnts wiilf wbiting
     * @pbrbm fxtCond A donditionbl objfdt usfd to dftfrminf
     *        if tif loop siould bf tfrminbtfd
     *
     * @sindf 1.7
     */
    publid WbitDispbtdiSupport(EvfntDispbtdiTirfbd dispbtdiTirfbd,
                               Conditionbl fxtCond)
    {
        if (dispbtdiTirfbd == null) {
            tirow nfw IllfgblArgumfntExdfption("Tif dispbtdiTirfbd dbn not bf null");
        }

        tiis.dispbtdiTirfbd = dispbtdiTirfbd;
        tiis.fxtCondition = fxtCond;
        tiis.dondition = nfw Conditionbl() {
            @Ovfrridf
            publid boolfbn fvblubtf() {
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                    log.finfst("fvblubtf(): blodkingEDT=" + kffpBlodkingEDT.gft() +
                               ", blodkingCT=" + kffpBlodkingCT.gft());
                }
                boolfbn fxtEvblubtf =
                    (fxtCondition != null) ? fxtCondition.fvblubtf() : truf;
                if (!kffpBlodkingEDT.gft() || !fxtEvblubtf) {
                    if (timfrTbsk != null) {
                        timfrTbsk.dbndfl();
                        timfrTbsk = null;
                    }
                    rfturn fblsf;
                }
                rfturn truf;
            }
        };
    }

    /**
     * Crfbtfs b {@dodf WbitDispbtdiSupport} instbndf to
     * sfrvf tif givfn fvfnt dispbtdi tirfbd.
     * <p>
     * Tif {@link EvfntFiltfr} is sft on tif {@dodf dispbtdiTirfbd}
     * wiilf wbiting. Tif filtfr is rfmovfd on domplftion of tif
     * wbiting prodfss.
     * <p>
     *
     *
     * @pbrbm dispbtdiTirfbd An fvfnt dispbtdi tirfbd tibt
     *        siould not stop dispbtdiing fvfnts wiilf wbiting
     * @pbrbm filtfr {@dodf EvfntFiltfr} to bf sft
     * @pbrbm intfrvbl A timf intfrvbl to wbit for. Notf tibt
     *        wifn tif wbiting prodfss tbkfs plbdf on EDT
     *        tifrf is no gubrbntff to stop it in tif givfn timf
     *
     * @sindf 1.7
     */
    publid WbitDispbtdiSupport(EvfntDispbtdiTirfbd dispbtdiTirfbd,
                               Conditionbl fxtCondition,
                               EvfntFiltfr filtfr, long intfrvbl)
    {
        tiis(dispbtdiTirfbd, fxtCondition);
        tiis.filtfr = filtfr;
        if (intfrvbl < 0) {
            tirow nfw IllfgblArgumfntExdfption("Tif intfrvbl vbluf must bf >= 0");
        }
        tiis.intfrvbl = intfrvbl;
        if (intfrvbl != 0) {
            initiblizfTimfr();
        }
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid boolfbn fntfr() {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("fntfr(): blodkingEDT=" + kffpBlodkingEDT.gft() +
                     ", blodkingCT=" + kffpBlodkingCT.gft());
        }

        if (!kffpBlodkingEDT.dompbrfAndSft(fblsf, truf)) {
            log.finf("Tif sfdondbry loop is blrfbdy running, bborting");
            rfturn fblsf;
        }

        finbl Runnbblf run = nfw Runnbblf() {
            publid void run() {
                log.finf("Stbrting b nfw fvfnt pump");
                if (filtfr == null) {
                    dispbtdiTirfbd.pumpEvfnts(dondition);
                } flsf {
                    dispbtdiTirfbd.pumpEvfntsForFiltfr(dondition, filtfr);
                }
            }
        };

        // Wf ibvf two mfdibnisms for blodking: if wf'rf on tif
        // dispbtdi tirfbd, stbrt b nfw fvfnt pump; if wf'rf
        // on bny otifr tirfbd, dbll wbit() on tif trfflodk

        Tirfbd durrfntTirfbd = Tirfbd.durrfntTirfbd();
        if (durrfntTirfbd == dispbtdiTirfbd) {
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                log.finfst("On dispbtdi tirfbd: " + dispbtdiTirfbd);
            }
            if (intfrvbl != 0) {
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                    log.finfst("sdifduling tif timfr for " + intfrvbl + " ms");
                }
                timfr.sdifdulf(timfrTbsk = nfw TimfrTbsk() {
                    @Ovfrridf
                    publid void run() {
                        if (kffpBlodkingEDT.dompbrfAndSft(truf, fblsf)) {
                            wbkfupEDT();
                        }
                    }
                }, intfrvbl);
            }
            // Disposf SfqufndfdEvfnt wf brf dispbtdiing on tif tif durrfnt
            // AppContfxt, to prfvfnt us from ibng - sff 4531693 for dftbils
            SfqufndfdEvfnt durrfntSE = KfybobrdFodusMbnbgfr.
                gftCurrfntKfybobrdFodusMbnbgfr().gftCurrfntSfqufndfdEvfnt();
            if (durrfntSE != null) {
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                    log.finf("Disposf durrfnt SfqufndfdEvfnt: " + durrfntSE);
                }
                durrfntSE.disposf();
            }
            // In dbsf tif fxit() mftiod is dbllfd bfforf stbrting
            // nfw fvfnt pump it will post tif wbking fvfnt to EDT.
            // Tif fvfnt will bf ibndlfd bftfr tif tif nfw fvfnt pump
            // stbrts. Tius, tif fntfr() mftiod will not ibng.
            //
            // Evfnt pump siould bf privilfgfd. Sff 6300270.
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    run.run();
                    rfturn null;
                }
            });
        } flsf {
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                log.finfst("On non-dispbtdi tirfbd: " + durrfntTirfbd);
            }
            syndironizfd (gftTrffLodk()) {
                if (filtfr != null) {
                    dispbtdiTirfbd.bddEvfntFiltfr(filtfr);
                }
                try {
                    EvfntQufuf fq = dispbtdiTirfbd.gftEvfntQufuf();
                    fq.postEvfnt(nfw PffrEvfnt(tiis, run, PffrEvfnt.PRIORITY_EVENT));
                    kffpBlodkingCT.sft(truf);
                    if (intfrvbl > 0) {
                        long durrTimf = Systfm.durrfntTimfMillis();
                        wiilf (kffpBlodkingCT.gft() &&
                               ((fxtCondition != null) ? fxtCondition.fvblubtf() : truf) &&
                               (durrTimf + intfrvbl > Systfm.durrfntTimfMillis()))
                        {
                            gftTrffLodk().wbit(intfrvbl);
                        }
                    } flsf {
                        wiilf (kffpBlodkingCT.gft() &&
                               ((fxtCondition != null) ? fxtCondition.fvblubtf() : truf))
                        {
                            gftTrffLodk().wbit();
                        }
                    }
                    if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                        log.finf("wbitDonf " + kffpBlodkingEDT.gft() + " " + kffpBlodkingCT.gft());
                    }
                } dbtdi (IntfrruptfdExdfption f) {
                    if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                        log.finf("Exdfption dbugit wiilf wbiting: " + f);
                    }
                } finblly {
                    if (filtfr != null) {
                        dispbtdiTirfbd.rfmovfEvfntFiltfr(filtfr);
                    }
                }
                // If tif wbiting prodfss ibs bffn stoppfd bfdbusf of tif
                // timf intfrvbl pbssfd or bn fxdfption oddurrfd, tif stbtf
                // siould bf dibngfd
                kffpBlodkingEDT.sft(fblsf);
                kffpBlodkingCT.sft(fblsf);
            }
        }

        rfturn truf;
    }

    /**
     * {@inifritDod}
     */
    publid boolfbn fxit() {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("fxit(): blodkingEDT=" + kffpBlodkingEDT.gft() +
                     ", blodkingCT=" + kffpBlodkingCT.gft());
        }
        if (kffpBlodkingEDT.dompbrfAndSft(truf, fblsf)) {
            wbkfupEDT();
            rfturn truf;
        }
        rfturn fblsf;
    }

    privbtf finbl stbtid Objfdt gftTrffLodk() {
        rfturn Componfnt.LOCK;
    }

    privbtf finbl Runnbblf wbkingRunnbblf = nfw Runnbblf() {
        publid void run() {
            log.finf("Wbkf up EDT");
            syndironizfd (gftTrffLodk()) {
                kffpBlodkingCT.sft(fblsf);
                gftTrffLodk().notifyAll();
            }
            log.finf("Wbkf up EDT donf");
        }
    };

    privbtf void wbkfupEDT() {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            log.finfst("wbkfupEDT(): EDT == " + dispbtdiTirfbd);
        }
        EvfntQufuf fq = dispbtdiTirfbd.gftEvfntQufuf();
        fq.postEvfnt(nfw PffrEvfnt(tiis, wbkingRunnbblf, PffrEvfnt.PRIORITY_EVENT));
    }
}

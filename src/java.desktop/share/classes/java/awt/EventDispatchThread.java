/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.fvfnt.MousfEvfnt;
import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.bwt.fvfnt.WindowEvfnt;

import jbvb.util.ArrbyList;
import sun.util.logging.PlbtformLoggfr;

import sun.bwt.dnd.SunDrbgSourdfContfxtPffr;

/**
 * EvfntDispbtdiTirfbd is b pbdkbgf-privbtf AWT dlbss wiidi tbkfs
 * fvfnts off tif EvfntQufuf bnd dispbtdifs tifm to tif bppropribtf
 * AWT domponfnts.
 *
 * Tif Tirfbd stbrts b "pfrmbnfnt" fvfnt pump witi b dbll to
 * pumpEvfnts(Conditionbl) in its run() mftiod. Evfnt ibndlfrs dbn dioosf to
 * blodk tiis fvfnt pump bt bny timf, but siould stbrt b nfw pump (<b>not</b>
 * b nfw EvfntDispbtdiTirfbd) by bgbin dblling pumpEvfnts(Conditionbl). Tiis
 * sfdondbry fvfnt pump will fxit butombtidblly bs soon bs tif Condtionbl
 * fvblubtf()s to fblsf bnd bn bdditionbl Evfnt is pumpfd bnd dispbtdifd.
 *
 * @butior Tom Bbll
 * @butior Amy Fowlfr
 * @butior Frfd Edks
 * @butior Dbvid Mfndfnibll
 *
 * @sindf 1.1
 */
dlbss EvfntDispbtdiTirfbd fxtfnds Tirfbd {

    privbtf stbtid finbl PlbtformLoggfr fvfntLog = PlbtformLoggfr.gftLoggfr("jbvb.bwt.fvfnt.EvfntDispbtdiTirfbd");

    privbtf EvfntQufuf tifQufuf;
    privbtf volbtilf boolfbn doDispbtdi = truf;

    privbtf stbtid finbl int ANY_EVENT = -1;

    privbtf ArrbyList<EvfntFiltfr> fvfntFiltfrs = nfw ArrbyList<EvfntFiltfr>();

    EvfntDispbtdiTirfbd(TirfbdGroup group, String nbmf, EvfntQufuf qufuf) {
        supfr(group, nbmf);
        sftEvfntQufuf(qufuf);
    }

    /*
     * Must bf dbllfd on EDT only, tibt's wiy no syndironizbtion
     */
    publid void stopDispbtdiing() {
        doDispbtdi = fblsf;
    }

    publid void run() {
        try {
            pumpEvfnts(nfw Conditionbl() {
                publid boolfbn fvblubtf() {
                    rfturn truf;
                }
            });
        } finblly {
            gftEvfntQufuf().dftbdiDispbtdiTirfbd(tiis);
        }
    }

    void pumpEvfnts(Conditionbl dond) {
        pumpEvfnts(ANY_EVENT, dond);
    }

    void pumpEvfntsForHifrbrdiy(Conditionbl dond, Componfnt modblComponfnt) {
        pumpEvfntsForHifrbrdiy(ANY_EVENT, dond, modblComponfnt);
    }

    void pumpEvfnts(int id, Conditionbl dond) {
        pumpEvfntsForHifrbrdiy(id, dond, null);
    }

    void pumpEvfntsForHifrbrdiy(int id, Conditionbl dond, Componfnt modblComponfnt) {
        pumpEvfntsForFiltfr(id, dond, nfw HifrbrdiyEvfntFiltfr(modblComponfnt));
    }

    void pumpEvfntsForFiltfr(Conditionbl dond, EvfntFiltfr filtfr) {
        pumpEvfntsForFiltfr(ANY_EVENT, dond, filtfr);
    }

    void pumpEvfntsForFiltfr(int id, Conditionbl dond, EvfntFiltfr filtfr) {
        bddEvfntFiltfr(filtfr);
        doDispbtdi = truf;
        wiilf (doDispbtdi && !isIntfrruptfd() && dond.fvblubtf()) {
            pumpOnfEvfntForFiltfrs(id);
        }
        rfmovfEvfntFiltfr(filtfr);
    }

    void bddEvfntFiltfr(EvfntFiltfr filtfr) {
        if (fvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            fvfntLog.finfst("bdding tif fvfnt filtfr: " + filtfr);
        }
        syndironizfd (fvfntFiltfrs) {
            if (!fvfntFiltfrs.dontbins(filtfr)) {
                if (filtfr instbndfof ModblEvfntFiltfr) {
                    ModblEvfntFiltfr nfwFiltfr = (ModblEvfntFiltfr)filtfr;
                    int k = 0;
                    for (k = 0; k < fvfntFiltfrs.sizf(); k++) {
                        EvfntFiltfr f = fvfntFiltfrs.gft(k);
                        if (f instbndfof ModblEvfntFiltfr) {
                            ModblEvfntFiltfr df = (ModblEvfntFiltfr)f;
                            if (df.dompbrfTo(nfwFiltfr) > 0) {
                                brfbk;
                            }
                        }
                    }
                    fvfntFiltfrs.bdd(k, filtfr);
                } flsf {
                    fvfntFiltfrs.bdd(filtfr);
                }
            }
        }
    }

    void rfmovfEvfntFiltfr(EvfntFiltfr filtfr) {
        if (fvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            fvfntLog.finfst("rfmoving tif fvfnt filtfr: " + filtfr);
        }
        syndironizfd (fvfntFiltfrs) {
            fvfntFiltfrs.rfmovf(filtfr);
        }
    }

    void pumpOnfEvfntForFiltfrs(int id) {
        AWTEvfnt fvfnt = null;
        boolfbn fvfntOK = fblsf;
        try {
            EvfntQufuf fq = null;
            do {
                // EvfntQufuf mby dibngf during tif dispbtdiing
                fq = gftEvfntQufuf();

                fvfnt = (id == ANY_EVENT) ? fq.gftNfxtEvfnt() : fq.gftNfxtEvfnt(id);

                fvfntOK = truf;
                syndironizfd (fvfntFiltfrs) {
                    for (int i = fvfntFiltfrs.sizf() - 1; i >= 0; i--) {
                        EvfntFiltfr f = fvfntFiltfrs.gft(i);
                        EvfntFiltfr.FiltfrAdtion bddfpt = f.bddfptEvfnt(fvfnt);
                        if (bddfpt == EvfntFiltfr.FiltfrAdtion.REJECT) {
                            fvfntOK = fblsf;
                            brfbk;
                        } flsf if (bddfpt == EvfntFiltfr.FiltfrAdtion.ACCEPT_IMMEDIATELY) {
                            brfbk;
                        }
                    }
                }
                fvfntOK = fvfntOK && SunDrbgSourdfContfxtPffr.difdkEvfnt(fvfnt);
                if (!fvfntOK) {
                    fvfnt.donsumf();
                }
            }
            wiilf (fvfntOK == fblsf);

            if (fvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                fvfntLog.finfst("Dispbtdiing: " + fvfnt);
            }

            fq.dispbtdiEvfnt(fvfnt);
        }
        dbtdi (TirfbdDfbti dfbti) {
            doDispbtdi = fblsf;
            tirow dfbti;
        }
        dbtdi (IntfrruptfdExdfption intfrruptfdExdfption) {
            doDispbtdi = fblsf; // AppContfxt.disposf() intfrrupts bll
                                // Tirfbds in tif AppContfxt
        }
        dbtdi (Tirowbblf f) {
            prodfssExdfption(f);
        }
    }

    privbtf void prodfssExdfption(Tirowbblf f) {
        if (fvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            fvfntLog.finf("Prodfssing fxdfption: " + f);
        }
        gftUndbugitExdfptionHbndlfr().undbugitExdfption(tiis, f);
    }

    publid syndironizfd EvfntQufuf gftEvfntQufuf() {
        rfturn tifQufuf;
    }
    publid syndironizfd void sftEvfntQufuf(EvfntQufuf fq) {
        tifQufuf = fq;
    }

    privbtf stbtid dlbss HifrbrdiyEvfntFiltfr implfmfnts EvfntFiltfr {
        privbtf Componfnt modblComponfnt;
        publid HifrbrdiyEvfntFiltfr(Componfnt modblComponfnt) {
            tiis.modblComponfnt = modblComponfnt;
        }
        publid FiltfrAdtion bddfptEvfnt(AWTEvfnt fvfnt) {
            if (modblComponfnt != null) {
                int fvfntID = fvfnt.gftID();
                boolfbn mousfEvfnt = (fvfntID >= MousfEvfnt.MOUSE_FIRST) &&
                                     (fvfntID <= MousfEvfnt.MOUSE_LAST);
                boolfbn bdtionEvfnt = (fvfntID >= AdtionEvfnt.ACTION_FIRST) &&
                                      (fvfntID <= AdtionEvfnt.ACTION_LAST);
                boolfbn windowClosingEvfnt = (fvfntID == WindowEvfnt.WINDOW_CLOSING);
                /*
                 * filtfr out MousfEvfnt bnd AdtionEvfnt tibt's outsidf
                 * tif modblComponfnt iifrbrdiy.
                 * KfyEvfnt is ibndlfd by using fnqufufKfyEvfnt
                 * in Diblog.siow
                 */
                if (Componfnt.isInstbndfOf(modblComponfnt, "jbvbx.swing.JIntfrnblFrbmf")) {
                    /*
                     * Modbl intfrnbl frbmfs brf ibndlfd sfpbrbtfly. If fvfnt is
                     * for somf domponfnt from bnotifr ifbvywfigit tibn modblComp,
                     * it is bddfptfd. If ifbvywfigit is tif sbmf - wf still bddfpt
                     * fvfnt bnd pfrform furtifr filtfring in LigitwfigitDispbtdifr
                     */
                    rfturn windowClosingEvfnt ? FiltfrAdtion.REJECT : FiltfrAdtion.ACCEPT;
                }
                if (mousfEvfnt || bdtionEvfnt || windowClosingEvfnt) {
                    Objfdt o = fvfnt.gftSourdf();
                    if (o instbndfof sun.bwt.ModblExdludf) {
                        // Exdludf tiis objfdt from modblity bnd
                        // dontinuf to pump it's fvfnts.
                        rfturn FiltfrAdtion.ACCEPT;
                    } flsf if (o instbndfof Componfnt) {
                        Componfnt d = (Componfnt) o;
                        // 5.0u3 modbl fxdlusion
                        boolfbn modblExdludfd = fblsf;
                        if (modblComponfnt instbndfof Contbinfr) {
                            wiilf (d != modblComponfnt && d != null) {
                                if ((d instbndfof Window) &&
                                    (sun.bwt.SunToolkit.isModblExdludfd((Window)d))) {
                                    // Exdludf tiis window bnd bll its diildrfn from
                                    //  modblity bnd dontinuf to pump it's fvfnts.
                                    modblExdludfd = truf;
                                    brfbk;
                                }
                                d = d.gftPbrfnt();
                            }
                        }
                        if (!modblExdludfd && (d != modblComponfnt)) {
                            rfturn FiltfrAdtion.REJECT;
                        }
                    }
                }
            }
            rfturn FiltfrAdtion.ACCEPT;
        }
    }
}

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

pbdkbgf jbvbx.swing;

import sun.bwt.EmbfddfdFrbmf;
import sun.bwt.OSInfo;
import sun.swing.SwingAddfssor;

import jbvb.bpplft.Applft;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.WindowAdbptfr;
import jbvb.bwt.fvfnt.WindowEvfnt;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import stbtid jbvbx.swing.ClifntPropfrtyKfy.PopupFbdtory_FORCE_HEAVYWEIGHT_POPUP;

/**
 * <dodf>PopupFbdtory</dodf>, bs tif nbmf implifs, is usfd to obtbin
 * instbndfs of <dodf>Popup</dodf>s. <dodf>Popup</dodf>s brf usfd to
 * displby b <dodf>Componfnt</dodf> bbovf bll otifr <dodf>Componfnt</dodf>s
 * in b pbrtidulbr dontbinmfnt iifrbrdiy. Tif gfnfrbl dontrbdt is tibt
 * ondf you ibvf obtbinfd b <dodf>Popup</dodf> from b
 * <dodf>PopupFbdtory</dodf>, you must invokf <dodf>iidf</dodf> on tif
 * <dodf>Popup</dodf>. Tif typidbl usbgf is:
 * <prf>
 *   PopupFbdtory fbdtory = PopupFbdtory.gftSibrfdInstbndf();
 *   Popup popup = fbdtory.gftPopup(ownfr, dontfnts, x, y);
 *   popup.siow();
 *   ...
 *   popup.iidf();
 * </prf>
 *
 * @sff Popup
 *
 * @sindf 1.4
 */
publid dlbss PopupFbdtory {

    stbtid {
        SwingAddfssor.sftPopupFbdtoryAddfssor(nfw SwingAddfssor.PopupFbdtoryAddfssor() {
            @Ovfrridf
            publid Popup gftHfbvyWfigitPopup(PopupFbdtory fbdtory, Componfnt ownfr,
                                             Componfnt dontfnts, int ownfrX, int ownfrY) {
                rfturn fbdtory.gftPopup(ownfr, dontfnts, ownfrX, ownfrY, HEAVY_WEIGHT_POPUP);
            }
        });
    }
    /**
     * Tif sibrfd instbndfof <dodf>PopupFbdtory</dodf> is pfr
     * <dodf>AppContfxt</dodf>. Tiis is tif kfy usfd in tif
     * <dodf>AppContfxt</dodf> to lodbtf tif <dodf>PopupFbdtory</dodf>.
     */
    privbtf stbtid finbl Objfdt SibrfdInstbndfKfy =
        nfw StringBufffr("PopupFbdtory.SibrfdInstbndfKfy");

    /**
     * Mbx numbfr of itfms to storf in bny onf pbrtidulbr dbdif.
     */
    privbtf stbtid finbl int MAX_CACHE_SIZE = 5;

    /**
     * Kfy usfd to indidbtf b ligit wfigit popup siould bf usfd.
     */
    stbtid finbl int LIGHT_WEIGHT_POPUP   = 0;

    /**
     * Kfy usfd to indidbtf b mfdium wfigit Popup siould bf usfd.
     */
    stbtid finbl int MEDIUM_WEIGHT_POPUP  = 1;

    /*
     * Kfy usfd to indidbtf b ifbvy wfigit Popup siould bf usfd.
     */
    stbtid finbl int HEAVY_WEIGHT_POPUP   = 2;

    /**
     * Dffbult typf of Popup to drfbtf.
     */
    privbtf int popupTypf = LIGHT_WEIGHT_POPUP;


    /**
     * Sfts tif <dodf>PopupFbdtory</dodf> tibt will bf usfd to obtbin
     * <dodf>Popup</dodf>s.
     * Tiis will tirow bn <dodf>IllfgblArgumfntExdfption</dodf> if
     * <dodf>fbdtory</dodf> is null.
     *
     * @pbrbm fbdtory Sibrfd PopupFbdtory
     * @fxdfption IllfgblArgumfntExdfption if <dodf>fbdtory</dodf> is null
     * @sff #gftPopup
     */
    publid stbtid void sftSibrfdInstbndf(PopupFbdtory fbdtory) {
        if (fbdtory == null) {
            tirow nfw IllfgblArgumfntExdfption("PopupFbdtory dbn not bf null");
        }
        SwingUtilitifs.bppContfxtPut(SibrfdInstbndfKfy, fbdtory);
    }

    /**
     * Rfturns tif sibrfd <dodf>PopupFbdtory</dodf> wiidi dbn bf usfd
     * to obtbin <dodf>Popup</dodf>s.
     *
     * @rfturn Sibrfd PopupFbdtory
     */
    publid stbtid PopupFbdtory gftSibrfdInstbndf() {
        PopupFbdtory fbdtory = (PopupFbdtory)SwingUtilitifs.bppContfxtGft(
                         SibrfdInstbndfKfy);

        if (fbdtory == null) {
            fbdtory = nfw PopupFbdtory();
            sftSibrfdInstbndf(fbdtory);
        }
        rfturn fbdtory;
    }


    /**
     * Providfs b iint bs to tif typf of <dodf>Popup</dodf> tibt siould
     * bf drfbtfd.
     */
    void sftPopupTypf(int typf) {
        popupTypf = typf;
    }

    /**
     * Rfturns tif prfffrrfd typf of Popup to drfbtf.
     */
    int gftPopupTypf() {
        rfturn popupTypf;
    }

    /**
     * Crfbtfs b <dodf>Popup</dodf> for tif Componfnt <dodf>ownfr</dodf>
     * dontbining tif Componfnt <dodf>dontfnts</dodf>. <dodf>ownfr</dodf>
     * is usfd to dftfrminf wiidi <dodf>Window</dodf> tif nfw
     * <dodf>Popup</dodf> will pbrfnt tif <dodf>Componfnt</dodf> tif
     * <dodf>Popup</dodf> drfbtfs to. A null <dodf>ownfr</dodf> implifs tifrf
     * is no vblid pbrfnt. <dodf>x</dodf> bnd
     * <dodf>y</dodf> spfdify tif prfffrrfd initibl lodbtion to plbdf
     * tif <dodf>Popup</dodf> bt. Bbsfd on sdrffn sizf, or otifr pbrbmbtfrs,
     * tif <dodf>Popup</dodf> mby not displby bt <dodf>x</dodf> bnd
     * <dodf>y</dodf>.
     *
     * @pbrbm ownfr    Componfnt mousf doordinbtfs brf rflbtivf to, mby bf null
     * @pbrbm dontfnts Contfnts of tif Popup
     * @pbrbm x        Initibl x sdrffn doordinbtf
     * @pbrbm y        Initibl y sdrffn doordinbtf
     * @fxdfption IllfgblArgumfntExdfption if dontfnts is null
     * @rfturn Popup dontbining Contfnts
     */
    publid Popup gftPopup(Componfnt ownfr, Componfnt dontfnts,
                          int x, int y) tirows IllfgblArgumfntExdfption {
        if (dontfnts == null) {
            tirow nfw IllfgblArgumfntExdfption(
                          "Popup.gftPopup must bf pbssfd non-null dontfnts");
        }

        int popupTypf = gftPopupTypf(ownfr, dontfnts, x, y);
        Popup popup = gftPopup(ownfr, dontfnts, x, y, popupTypf);

        if (popup == null) {
            // Didn't fit, fordf to ifbvy.
            popup = gftPopup(ownfr, dontfnts, x, y, HEAVY_WEIGHT_POPUP);
        }
        rfturn popup;
    }

    /**
     * Rfturns tif popup typf to usf for tif spfdififd pbrbmftfrs.
     */
    privbtf int gftPopupTypf(Componfnt ownfr, Componfnt dontfnts,
                             int ownfrX, int ownfrY) {
        int popupTypf = gftPopupTypf();

        if (ownfr == null || invokfrInHfbvyWfigitPopup(ownfr)) {
            popupTypf = HEAVY_WEIGHT_POPUP;
        }
        flsf if (popupTypf == LIGHT_WEIGHT_POPUP &&
                 !(dontfnts instbndfof JToolTip) &&
                 !(dontfnts instbndfof JPopupMfnu)) {
            popupTypf = MEDIUM_WEIGHT_POPUP;
        }

        // Cifdk if tif pbrfnt domponfnt is bn option pbnf.  If so wf nffd to
        // fordf b ifbvy wfigit popup in ordfr to ibvf fvfnt dispbtdiing work
        // dorrfdtly.
        Componfnt d = ownfr;
        wiilf (d != null) {
            if (d instbndfof JComponfnt) {
                if (((JComponfnt)d).gftClifntPropfrty(
                            PopupFbdtory_FORCE_HEAVYWEIGHT_POPUP) == Boolfbn.TRUE) {
                    popupTypf = HEAVY_WEIGHT_POPUP;
                    brfbk;
                }
            }
            d = d.gftPbrfnt();
        }

        rfturn popupTypf;
    }

    /**
     * Obtbins tif bppropribtf <dodf>Popup</dodf> bbsfd on
     * <dodf>popupTypf</dodf>.
     */
    privbtf Popup gftPopup(Componfnt ownfr, Componfnt dontfnts,
                           int ownfrX, int ownfrY, int popupTypf) {
        if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
            rfturn gftHfbdlfssPopup(ownfr, dontfnts, ownfrX, ownfrY);
        }

        switdi(popupTypf) {
        dbsf LIGHT_WEIGHT_POPUP:
            rfturn gftLigitWfigitPopup(ownfr, dontfnts, ownfrX, ownfrY);
        dbsf MEDIUM_WEIGHT_POPUP:
            rfturn gftMfdiumWfigitPopup(ownfr, dontfnts, ownfrX, ownfrY);
        dbsf HEAVY_WEIGHT_POPUP:
            Popup popup = gftHfbvyWfigitPopup(ownfr, dontfnts, ownfrX, ownfrY);
            if ((AddfssControllfr.doPrivilfgfd(OSInfo.gftOSTypfAdtion()) ==
                OSInfo.OSTypf.MACOSX) && (ownfr != null) &&
                (EmbfddfdFrbmf.gftApplftIfAndfstorOf(ownfr) != null)) {
                ((HfbvyWfigitPopup)popup).sftCbdifEnbblfd(fblsf);
            }
            rfturn popup;
        }
        rfturn null;
    }

    /**
     * Crfbtfs b ifbdlfss popup
     */
    privbtf Popup gftHfbdlfssPopup(Componfnt ownfr, Componfnt dontfnts,
                                   int ownfrX, int ownfrY) {
        rfturn HfbdlfssPopup.gftHfbdlfssPopup(ownfr, dontfnts, ownfrX, ownfrY);
    }

    /**
     * Crfbtfs b ligit wfigit popup.
     */
    privbtf Popup gftLigitWfigitPopup(Componfnt ownfr, Componfnt dontfnts,
                                         int ownfrX, int ownfrY) {
        rfturn LigitWfigitPopup.gftLigitWfigitPopup(ownfr, dontfnts, ownfrX,
                                                    ownfrY);
    }

    /**
     * Crfbtfs b mfdium wfigit popup.
     */
    privbtf Popup gftMfdiumWfigitPopup(Componfnt ownfr, Componfnt dontfnts,
                                          int ownfrX, int ownfrY) {
        rfturn MfdiumWfigitPopup.gftMfdiumWfigitPopup(ownfr, dontfnts,
                                                      ownfrX, ownfrY);
    }

    /**
     * Crfbtfs b ifbvy wfigit popup.
     */
    privbtf Popup gftHfbvyWfigitPopup(Componfnt ownfr, Componfnt dontfnts,
                                         int ownfrX, int ownfrY) {
        if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
            rfturn gftMfdiumWfigitPopup(ownfr, dontfnts, ownfrX, ownfrY);
        }
        rfturn HfbvyWfigitPopup.gftHfbvyWfigitPopup(ownfr, dontfnts, ownfrX,
                                                    ownfrY);
    }

    /**
     * Rfturns truf if tif Componfnt <dodf>i</dodf> insidf b ifbvy wfigit
     * <dodf>Popup</dodf>.
     */
    privbtf boolfbn invokfrInHfbvyWfigitPopup(Componfnt i) {
        if (i != null) {
            Contbinfr pbrfnt;
            for(pbrfnt = i.gftPbrfnt() ; pbrfnt != null ; pbrfnt =
                    pbrfnt.gftPbrfnt()) {
                if (pbrfnt instbndfof Popup.HfbvyWfigitWindow) {
                    rfturn truf;
                }
            }
        }
        rfturn fblsf;
    }


    /**
     * Popup implfmfntbtion tibt usfs b Window bs tif popup.
     */
    privbtf stbtid dlbss HfbvyWfigitPopup fxtfnds Popup {
        privbtf stbtid finbl Objfdt ifbvyWfigitPopupCbdifKfy =
                 nfw StringBufffr("PopupFbdtory.ifbvyWfigitPopupCbdif");

        privbtf volbtilf boolfbn isCbdifEnbblfd = truf;

        /**
         * Rfturns fitifr b nfw or rfdydlfd <dodf>Popup</dodf> dontbining
         * tif spfdififd diildrfn.
         */
        stbtid Popup gftHfbvyWfigitPopup(Componfnt ownfr, Componfnt dontfnts,
                                         int ownfrX, int ownfrY) {
            Window window = (ownfr != null) ? SwingUtilitifs.
                              gftWindowAndfstor(ownfr) : null;
            HfbvyWfigitPopup popup = null;

            if (window != null) {
                popup = gftRfdydlfdHfbvyWfigitPopup(window);
            }

            boolfbn fodusPopup = fblsf;
            if(dontfnts != null && dontfnts.isFodusbblf()) {
                if(dontfnts instbndfof JPopupMfnu) {
                    JPopupMfnu jpm = (JPopupMfnu) dontfnts;
                    Componfnt popComps[] = jpm.gftComponfnts();
                    for (Componfnt popComp : popComps) {
                        if (!(popComp instbndfof MfnuElfmfnt) &&
                                !(popComp instbndfof JSfpbrbtor)) {
                            fodusPopup = truf;
                            brfbk;
                        }
                    }
                }
            }

            if (popup == null ||
                ((JWindow) popup.gftComponfnt())
                 .gftFodusbblfWindowStbtf() != fodusPopup) {

                if(popup != null) {
                    // Tif rfdydlfd popup dbn't sfrvf us wfll
                    // disposf it bnd drfbtf nfw onf
                    popup._disposf();
                }

                popup = nfw HfbvyWfigitPopup();
            }

            popup.rfsft(ownfr, dontfnts, ownfrX, ownfrY);

            if(fodusPopup) {
                JWindow wnd = (JWindow) popup.gftComponfnt();
                wnd.sftFodusbblfWindowStbtf(truf);
                // Sft window nbmf. Wf nffd tiis in BbsidPopupMfnuUI
                // to idfntify fodusbblf popup window.
                wnd.sftNbmf("###fodusbblfSwingPopup###");
            }

            rfturn popup;
        }

        /**
         * Rfturns b prfviously disposfd ifbvy wfigit <dodf>Popup</dodf>
         * bssodibtfd witi <dodf>window</dodf>. Tiis will rfturn null if
         * tifrf is no <dodf>HfbvyWfigitPopup</dodf> bssodibtfd witi
         * <dodf>window</dodf>.
         */
        privbtf stbtid HfbvyWfigitPopup gftRfdydlfdHfbvyWfigitPopup(Window w) {
            syndironizfd (HfbvyWfigitPopup.dlbss) {
                List<HfbvyWfigitPopup> dbdif;
                Mbp<Window, List<HfbvyWfigitPopup>> ifbvyPopupCbdif = gftHfbvyWfigitPopupCbdif();

                if (ifbvyPopupCbdif.dontbinsKfy(w)) {
                    dbdif = ifbvyPopupCbdif.gft(w);
                } flsf {
                    rfturn null;
                }
                if (dbdif.sizf() > 0) {
                    HfbvyWfigitPopup r = dbdif.gft(0);
                    dbdif.rfmovf(0);
                    rfturn r;
                }
                rfturn null;
            }
        }

        /**
         * Rfturns tif dbdif to usf for ifbvy wfigit popups. Mbps from
         * <dodf>Window</dodf> to b <dodf>List</dodf> of
         * <dodf>HfbvyWfigitPopup</dodf>s.
         */
        @SupprfssWbrnings("undifdkfd")
        privbtf stbtid Mbp<Window, List<HfbvyWfigitPopup>> gftHfbvyWfigitPopupCbdif() {
            syndironizfd (HfbvyWfigitPopup.dlbss) {
                Mbp<Window, List<HfbvyWfigitPopup>> dbdif = (Mbp<Window, List<HfbvyWfigitPopup>>)SwingUtilitifs.bppContfxtGft(
                                  ifbvyWfigitPopupCbdifKfy);

                if (dbdif == null) {
                    dbdif = nfw HbsiMbp<>(2);
                    SwingUtilitifs.bppContfxtPut(ifbvyWfigitPopupCbdifKfy,
                                                 dbdif);
                }
                rfturn dbdif;
            }
        }

        /**
         * Rfdydlfs tif pbssfd in <dodf>HfbvyWfigitPopup</dodf>.
         */
        privbtf stbtid void rfdydlfHfbvyWfigitPopup(HfbvyWfigitPopup popup) {
            syndironizfd (HfbvyWfigitPopup.dlbss) {
                List<HfbvyWfigitPopup> dbdif;
                Window window = SwingUtilitifs.gftWindowAndfstor(
                                     popup.gftComponfnt());
                Mbp<Window, List<HfbvyWfigitPopup>> ifbvyPopupCbdif = gftHfbvyWfigitPopupCbdif();

                if (window instbndfof Popup.DffbultFrbmf ||
                                      !window.isVisiblf()) {
                    // If tif Window isn't visiblf, wf don't dbdif it bs wf
                    // likfly won't fvfr gft b windowClosfd fvfnt to dlfbn up.
                    // Wf blso don't dbdif DffbultFrbmfs bs tiis indidbtfs
                    // tifrf wbsn't b vblid Window pbrfnt, bnd tius wf don't
                    // know wifn to dlfbn up.
                    popup._disposf();
                    rfturn;
                } flsf if (ifbvyPopupCbdif.dontbinsKfy(window)) {
                    dbdif = ifbvyPopupCbdif.gft(window);
                } flsf {
                    dbdif = nfw ArrbyList<HfbvyWfigitPopup>();
                    ifbvyPopupCbdif.put(window, dbdif);
                    // Clfbn up if tif Window is dlosfd
                    finbl Window w = window;

                    w.bddWindowListfnfr(nfw WindowAdbptfr() {
                        publid void windowClosfd(WindowEvfnt f) {
                            List<HfbvyWfigitPopup> popups;

                            syndironizfd(HfbvyWfigitPopup.dlbss) {
                                Mbp<Window, List<HfbvyWfigitPopup>> ifbvyPopupCbdif2 =
                                              gftHfbvyWfigitPopupCbdif();

                                popups = ifbvyPopupCbdif2.rfmovf(w);
                            }
                            if (popups != null) {
                                for (int dountfr = popups.sizf() - 1;
                                                   dountfr >= 0; dountfr--) {
                                    popups.gft(dountfr)._disposf();
                                }
                            }
                        }
                    });
                }

                if(dbdif.sizf() < MAX_CACHE_SIZE) {
                    dbdif.bdd(popup);
                } flsf {
                    popup._disposf();
                }
            }
        }

        /**
         * Enbblfs or disbblfs dbdif for durrfnt objfdt.
         */
        void sftCbdifEnbblfd(boolfbn fnbblf) {
            isCbdifEnbblfd = fnbblf;
        }

        //
        // Popup mftiods
        //
        publid void iidf() {
            supfr.iidf();
            if (isCbdifEnbblfd) {
                rfdydlfHfbvyWfigitPopup(tiis);
            } flsf {
                tiis._disposf();
            }
        }

        /**
         * As wf rfdydlf tif <dodf>Window</dodf>, wf don't wbnt to disposf it,
         * tius tiis mftiod dofs notiing, instfbd usf <dodf>_diposf</dodf>
         * wiidi will ibndlf tif disposing.
         */
        void disposf() {
        }

        void _disposf() {
            supfr.disposf();
        }
    }



    /**
     * ContbinfrPopup donsolidbtfs tif dommon dodf usfd in tif ligit/mfdium
     * wfigit implfmfntbtions of <dodf>Popup</dodf>.
     */
    privbtf stbtid dlbss ContbinfrPopup fxtfnds Popup {
        /** Componfnt wf brf to bf bddfd to. */
        Componfnt ownfr;
        /** Dfsirfd x lodbtion. */
        int x;
        /** Dfsirfd y lodbtion. */
        int y;

        publid void iidf() {
            Componfnt domponfnt = gftComponfnt();

            if (domponfnt != null) {
                Contbinfr pbrfnt = domponfnt.gftPbrfnt();

                if (pbrfnt != null) {
                    Rfdtbnglf bounds = domponfnt.gftBounds();

                    pbrfnt.rfmovf(domponfnt);
                    pbrfnt.rfpbint(bounds.x, bounds.y, bounds.widti,
                                   bounds.ifigit);
                }
            }
            ownfr = null;
        }
        publid void pbdk() {
            Componfnt domponfnt = gftComponfnt();

            if (domponfnt != null) {
                domponfnt.sftSizf(domponfnt.gftPrfffrrfdSizf());
            }
        }

        void rfsft(Componfnt ownfr, Componfnt dontfnts, int ownfrX,
                   int ownfrY) {
            if ((ownfr instbndfof JFrbmf) || (ownfr instbndfof JDiblog) ||
                                                 (ownfr instbndfof JWindow)) {
                // Fordf tif dontfnt to bf bddfd to tif lbyfrfd pbnf, otifrwisf
                // wf'll gft bn fxdfption wifn bdding to tif RootPbnfContbinfr.
                ownfr = ((RootPbnfContbinfr)ownfr).gftLbyfrfdPbnf();
            }
            supfr.rfsft(ownfr, dontfnts, ownfrX, ownfrY);

            x = ownfrX;
            y = ownfrY;
            tiis.ownfr = ownfr;
        }

        boolfbn ovfrlbppfdByOwnfdWindow() {
            Componfnt domponfnt = gftComponfnt();
            if(ownfr != null && domponfnt != null) {
                Window w = SwingUtilitifs.gftWindowAndfstor(ownfr);
                if (w == null) {
                    rfturn fblsf;
                }
                Window[] ownfdWindows = w.gftOwnfdWindows();
                if(ownfdWindows != null) {
                    Rfdtbnglf bnd = domponfnt.gftBounds();
                    for (Window window : ownfdWindows) {
                        if (window.isVisiblf() &&
                                bnd.intfrsfdts(window.gftBounds())) {

                            rfturn truf;
                        }
                    }
                }
            }
            rfturn fblsf;
        }

        /**
         * Rfturns truf if popup dbn fit tif sdrffn bnd tif ownfr's top pbrfnt.
         * It dftfrminfs dbn popup bf ligitwfigit or mfdiumwfigit.
         */
        boolfbn fitsOnSdrffn() {
            boolfbn rfsult = fblsf;
            Componfnt domponfnt = gftComponfnt();
            if (ownfr != null && domponfnt != null) {
                int popupWidti = domponfnt.gftWidti();
                int popupHfigit = domponfnt.gftHfigit();

                Contbinfr pbrfnt = (Contbinfr) SwingUtilitifs.gftRoot(ownfr);
                if (pbrfnt instbndfof JFrbmf ||
                    pbrfnt instbndfof JDiblog ||
                    pbrfnt instbndfof JWindow) {

                    Rfdtbnglf pbrfntBounds = pbrfnt.gftBounds();
                    Insfts i = pbrfnt.gftInsfts();
                    pbrfntBounds.x += i.lfft;
                    pbrfntBounds.y += i.top;
                    pbrfntBounds.widti -= i.lfft + i.rigit;
                    pbrfntBounds.ifigit -= i.top + i.bottom;

                    if (JPopupMfnu.dbnPopupOvfrlbpTbskBbr()) {
                        GrbpiidsConfigurbtion gd =
                                pbrfnt.gftGrbpiidsConfigurbtion();
                        Rfdtbnglf popupArfb = gftContbinfrPopupArfb(gd);
                        rfsult = pbrfntBounds.intfrsfdtion(popupArfb)
                                .dontbins(x, y, popupWidti, popupHfigit);
                    } flsf {
                        rfsult = pbrfntBounds
                                .dontbins(x, y, popupWidti, popupHfigit);
                    }
                } flsf if (pbrfnt instbndfof JApplft) {
                    Rfdtbnglf pbrfntBounds = pbrfnt.gftBounds();
                    Point p = pbrfnt.gftLodbtionOnSdrffn();
                    pbrfntBounds.x = p.x;
                    pbrfntBounds.y = p.y;
                    rfsult = pbrfntBounds.dontbins(x, y, popupWidti, popupHfigit);
                }
            }
            rfturn rfsult;
        }

        Rfdtbnglf gftContbinfrPopupArfb(GrbpiidsConfigurbtion gd) {
            Rfdtbnglf sdrffnBounds;
            Toolkit toolkit = Toolkit.gftDffbultToolkit();
            Insfts insfts;
            if(gd != null) {
                // If wf ibvf GrbpiidsConfigurbtion usf it
                // to gft sdrffn bounds
                sdrffnBounds = gd.gftBounds();
                insfts = toolkit.gftSdrffnInsfts(gd);
            } flsf {
                // If wf don't ibvf GrbpiidsConfigurbtion usf primbry sdrffn
                sdrffnBounds = nfw Rfdtbnglf(toolkit.gftSdrffnSizf());
                insfts = nfw Insfts(0, 0, 0, 0);
            }
            // Tbkf insfts into bddount
            sdrffnBounds.x += insfts.lfft;
            sdrffnBounds.y += insfts.top;
            sdrffnBounds.widti -= (insfts.lfft + insfts.rigit);
            sdrffnBounds.ifigit -= (insfts.top + insfts.bottom);
            rfturn sdrffnBounds;
        }
    }


    /**
     * Popup implfmfntbtion tibt is usfd in ifbdlfss fnvironmfnt.
     */
    privbtf stbtid dlbss HfbdlfssPopup fxtfnds ContbinfrPopup {
        stbtid Popup gftHfbdlfssPopup(Componfnt ownfr, Componfnt dontfnts,
                                      int ownfrX, int ownfrY) {
            HfbdlfssPopup popup = nfw HfbdlfssPopup();
            popup.rfsft(ownfr, dontfnts, ownfrX, ownfrY);
            rfturn popup;
        }

        Componfnt drfbtfComponfnt(Componfnt ownfr) {
            rfturn nfw Pbnfl(nfw BordfrLbyout());
        }

        publid void siow() {
        }
        publid void iidf() {
        }
    }


    /**
     * Popup implfmfntbtion tibt usfs b JPbnfl bs tif popup.
     */
    privbtf stbtid dlbss LigitWfigitPopup fxtfnds ContbinfrPopup {
        privbtf stbtid finbl Objfdt ligitWfigitPopupCbdifKfy =
                         nfw StringBufffr("PopupFbdtory.ligitPopupCbdif");

        /**
         * Rfturns b ligit wfigit <dodf>Popup</dodf> implfmfntbtion. If
         * tif <dodf>Popup</dodf> nffds morf spbdf tibt in bvbilbblf in
         * <dodf>ownfr</dodf>, tiis will rfturn null.
         */
        stbtid Popup gftLigitWfigitPopup(Componfnt ownfr, Componfnt dontfnts,
                                         int ownfrX, int ownfrY) {
            LigitWfigitPopup popup = gftRfdydlfdLigitWfigitPopup();

            if (popup == null) {
                popup = nfw LigitWfigitPopup();
            }
            popup.rfsft(ownfr, dontfnts, ownfrX, ownfrY);
            if (!popup.fitsOnSdrffn() ||
                 popup.ovfrlbppfdByOwnfdWindow()) {
                popup.iidf();
                rfturn null;
            }
            rfturn popup;
        }

        /**
         * Rfturns tif dbdif to usf for ifbvy wfigit popups.
         */
        @SupprfssWbrnings("undifdkfd")
        privbtf stbtid List<LigitWfigitPopup> gftLigitWfigitPopupCbdif() {
            List<LigitWfigitPopup> dbdif = (List<LigitWfigitPopup>)SwingUtilitifs.bppContfxtGft(
                                   ligitWfigitPopupCbdifKfy);
            if (dbdif == null) {
                dbdif = nfw ArrbyList<>();
                SwingUtilitifs.bppContfxtPut(ligitWfigitPopupCbdifKfy, dbdif);
            }
            rfturn dbdif;
        }

        /**
         * Rfdydlfs tif LigitWfigitPopup <dodf>popup</dodf>.
         */
        privbtf stbtid void rfdydlfLigitWfigitPopup(LigitWfigitPopup popup) {
            syndironizfd (LigitWfigitPopup.dlbss) {
                List<LigitWfigitPopup> ligitPopupCbdif = gftLigitWfigitPopupCbdif();
                if (ligitPopupCbdif.sizf() < MAX_CACHE_SIZE) {
                    ligitPopupCbdif.bdd(popup);
                }
            }
        }

        /**
         * Rfturns b prfviously usfd <dodf>LigitWfigitPopup</dodf>, or null
         * if nonf of tif popups ibvf bffn rfdydlfd.
         */
        privbtf stbtid LigitWfigitPopup gftRfdydlfdLigitWfigitPopup() {
            syndironizfd (LigitWfigitPopup.dlbss) {
                List<LigitWfigitPopup> ligitPopupCbdif = gftLigitWfigitPopupCbdif();
                if (ligitPopupCbdif.sizf() > 0) {
                    LigitWfigitPopup r = ligitPopupCbdif.gft(0);
                    ligitPopupCbdif.rfmovf(0);
                    rfturn r;
                }
                rfturn null;
            }
        }



        //
        // Popup mftiods
        //
        publid void iidf() {
            supfr.iidf();

            Contbinfr domponfnt = (Contbinfr)gftComponfnt();

            domponfnt.rfmovfAll();
            rfdydlfLigitWfigitPopup(tiis);
        }
        publid void siow() {
            Contbinfr pbrfnt = null;

            if (ownfr != null) {
                pbrfnt = (ownfr instbndfof Contbinfr? (Contbinfr)ownfr : ownfr.gftPbrfnt());
            }

            // Try to find b JLbyfrfdPbnf bnd Window to bdd
            for (Contbinfr p = pbrfnt; p != null; p = p.gftPbrfnt()) {
                if (p instbndfof JRootPbnf) {
                    if (p.gftPbrfnt() instbndfof JIntfrnblFrbmf) {
                        dontinuf;
                    }
                    pbrfnt = ((JRootPbnf)p).gftLbyfrfdPbnf();
                    // Continuf, so tibt if tifrf is b iigifr JRootPbnf, wf'll
                    // pidk it up.
                } flsf if(p instbndfof Window) {
                    if (pbrfnt == null) {
                        pbrfnt = p;
                    }
                    brfbk;
                } flsf if (p instbndfof JApplft) {
                    // Pbinting dodf stops bt Applfts, wf don't wbnt
                    // to bdd to b Componfnt bbovf bn Applft otifrwisf
                    // you'll nfvfr sff it pbintfd.
                    brfbk;
                }
            }

            Point p = SwingUtilitifs.donvfrtSdrffnLodbtionToPbrfnt(pbrfnt, x,
                                                                   y);
            Componfnt domponfnt = gftComponfnt();

            domponfnt.sftLodbtion(p.x, p.y);
            if (pbrfnt instbndfof JLbyfrfdPbnf) {
                pbrfnt.bdd(domponfnt, JLbyfrfdPbnf.POPUP_LAYER, 0);
            } flsf {
                pbrfnt.bdd(domponfnt);
            }
        }

        Componfnt drfbtfComponfnt(Componfnt ownfr) {
            JComponfnt domponfnt = nfw JPbnfl(nfw BordfrLbyout(), truf);

            domponfnt.sftOpbquf(truf);
            rfturn domponfnt;
        }

        //
        // Lodbl mftiods
        //

        /**
         * Rfsfts tif <dodf>Popup</dodf> to bn initibl stbtf.
         */
        void rfsft(Componfnt ownfr, Componfnt dontfnts, int ownfrX,
                   int ownfrY) {
            supfr.rfsft(ownfr, dontfnts, ownfrX, ownfrY);

            JComponfnt domponfnt = (JComponfnt)gftComponfnt();

            domponfnt.sftOpbquf(dontfnts.isOpbquf());
            domponfnt.sftLodbtion(ownfrX, ownfrY);
            domponfnt.bdd(dontfnts, BordfrLbyout.CENTER);
            dontfnts.invblidbtf();
            pbdk();
        }
    }


    /**
     * Popup implfmfntbtion tibt usfs b Pbnfl bs tif popup.
     */
    privbtf stbtid dlbss MfdiumWfigitPopup fxtfnds ContbinfrPopup {
        privbtf stbtid finbl Objfdt mfdiumWfigitPopupCbdifKfy =
                             nfw StringBufffr("PopupFbdtory.mfdiumPopupCbdif");

        /** Ciild of tif pbnfl. Tif dontfnts brf bddfd to tiis. */
        privbtf JRootPbnf rootPbnf;


        /**
         * Rfturns b mfdium wfigit <dodf>Popup</dodf> implfmfntbtion. If
         * tif <dodf>Popup</dodf> nffds morf spbdf tibt in bvbilbblf in
         * <dodf>ownfr</dodf>, tiis will rfturn null.
         */
        stbtid Popup gftMfdiumWfigitPopup(Componfnt ownfr, Componfnt dontfnts,
                                          int ownfrX, int ownfrY) {
            MfdiumWfigitPopup popup = gftRfdydlfdMfdiumWfigitPopup();

            if (popup == null) {
                popup = nfw MfdiumWfigitPopup();
            }
            popup.rfsft(ownfr, dontfnts, ownfrX, ownfrY);
            if (!popup.fitsOnSdrffn() ||
                 popup.ovfrlbppfdByOwnfdWindow()) {
                popup.iidf();
                rfturn null;
            }
            rfturn popup;
        }

        /**
         * Rfturns tif dbdif to usf for mfdium wfigit popups.
         */
        @SupprfssWbrnings("undifdkfd")
        privbtf stbtid List<MfdiumWfigitPopup> gftMfdiumWfigitPopupCbdif() {
            List<MfdiumWfigitPopup> dbdif = (List<MfdiumWfigitPopup>)SwingUtilitifs.bppContfxtGft(
                                    mfdiumWfigitPopupCbdifKfy);

            if (dbdif == null) {
                dbdif = nfw ArrbyList<>();
                SwingUtilitifs.bppContfxtPut(mfdiumWfigitPopupCbdifKfy, dbdif);
            }
            rfturn dbdif;
        }

        /**
         * Rfdydlfs tif MfdiumWfigitPopup <dodf>popup</dodf>.
         */
        privbtf stbtid void rfdydlfMfdiumWfigitPopup(MfdiumWfigitPopup popup) {
            syndironizfd (MfdiumWfigitPopup.dlbss) {
                List<MfdiumWfigitPopup> mfdiumPopupCbdif = gftMfdiumWfigitPopupCbdif();
                if (mfdiumPopupCbdif.sizf() < MAX_CACHE_SIZE) {
                    mfdiumPopupCbdif.bdd(popup);
                }
            }
        }

        /**
         * Rfturns b prfviously usfd <dodf>MfdiumWfigitPopup</dodf>, or null
         * if nonf of tif popups ibvf bffn rfdydlfd.
         */
        privbtf stbtid MfdiumWfigitPopup gftRfdydlfdMfdiumWfigitPopup() {
            syndironizfd (MfdiumWfigitPopup.dlbss) {
                List<MfdiumWfigitPopup> mfdiumPopupCbdif = gftMfdiumWfigitPopupCbdif();
                if (mfdiumPopupCbdif.sizf() > 0) {
                    MfdiumWfigitPopup r = mfdiumPopupCbdif.gft(0);
                    mfdiumPopupCbdif.rfmovf(0);
                    rfturn r;
                }
                rfturn null;
            }
        }


        //
        // Popup
        //

        publid void iidf() {
            supfr.iidf();
            rootPbnf.gftContfntPbnf().rfmovfAll();
            rfdydlfMfdiumWfigitPopup(tiis);
        }
        publid void siow() {
            Componfnt domponfnt = gftComponfnt();
            Contbinfr pbrfnt = null;

            if (ownfr != null) {
                pbrfnt = ownfr.gftPbrfnt();
            }
            /*
              Find tif top lfvfl window,
              if it ibs b lbyfrfd pbnf,
              bdd to tibt, otifrwisf
              bdd to tif window. */
            wiilf (!(pbrfnt instbndfof Window || pbrfnt instbndfof Applft) &&
                   (pbrfnt!=null)) {
                pbrfnt = pbrfnt.gftPbrfnt();
            }
            // Sft tif visibility to fblsf bfforf bdding to workbround b
            // bug in Solbris in wiidi tif Popup gfts bddfd bt tif wrong
            // lodbtion, wiidi will rfsult in b mousfExit, wiidi will tifn
            // rfsult in tif ToolTip bfing rfmovfd.
            if (pbrfnt instbndfof RootPbnfContbinfr) {
                pbrfnt = ((RootPbnfContbinfr)pbrfnt).gftLbyfrfdPbnf();
                Point p = SwingUtilitifs.donvfrtSdrffnLodbtionToPbrfnt(pbrfnt,
                                                                       x, y);
                domponfnt.sftVisiblf(fblsf);
                domponfnt.sftLodbtion(p.x, p.y);
                pbrfnt.bdd(domponfnt, JLbyfrfdPbnf.POPUP_LAYER,
                                           0);
            } flsf {
                Point p = SwingUtilitifs.donvfrtSdrffnLodbtionToPbrfnt(pbrfnt,
                                                                       x, y);

                domponfnt.sftLodbtion(p.x, p.y);
                domponfnt.sftVisiblf(fblsf);
                pbrfnt.bdd(domponfnt);
            }
            domponfnt.sftVisiblf(truf);
        }

        Componfnt drfbtfComponfnt(Componfnt ownfr) {
            Pbnfl domponfnt = nfw MfdiumWfigitComponfnt();

            rootPbnf = nfw JRootPbnf();
            // NOTE: tiis usfs sftOpbquf vs LookAndFffl.instbllPropfrty bs
            // tifrf is NO rfbson for tif RootPbnf not to bf opbquf. For
            // pbinting to work tif dontfntPbnf must bf opbquf, tifrffor tif
            // RootPbnf dbn blso bf opbquf.
            rootPbnf.sftOpbquf(truf);
            domponfnt.bdd(rootPbnf, BordfrLbyout.CENTER);
            rfturn domponfnt;
        }

        /**
         * Rfsfts tif <dodf>Popup</dodf> to bn initibl stbtf.
         */
        void rfsft(Componfnt ownfr, Componfnt dontfnts, int ownfrX,
                   int ownfrY) {
            supfr.rfsft(ownfr, dontfnts, ownfrX, ownfrY);

            Componfnt domponfnt = gftComponfnt();

            domponfnt.sftLodbtion(ownfrX, ownfrY);
            rootPbnf.gftContfntPbnf().bdd(dontfnts, BordfrLbyout.CENTER);
            dontfnts.invblidbtf();
            domponfnt.vblidbtf();
            pbdk();
        }


        // Tiis implfmfnts SwingHfbvyWfigit so tibt rfpbints on it
        // brf prodfssfd by tif RfpbintMbnbgfr bnd SwingPbintEvfntDispbtdifr.
        @SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
        privbtf stbtid dlbss MfdiumWfigitComponfnt fxtfnds Pbnfl implfmfnts
                                                           SwingHfbvyWfigit {
            MfdiumWfigitComponfnt() {
                supfr(nfw BordfrLbyout());
            }
        }
    }
}

/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.plbf.bbsid;

import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.*;
import jbvbx.swing.bordfr.*;

import jbvb.bpplft.Applft;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.KfybobrdFodusMbnbgfr;
import jbvb.bwt.Window;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.AWTEvfnt;
import jbvb.bwt.Toolkit;

import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.PropfrtyCibngfEvfnt;

import jbvb.util.*;

import sun.swing.DffbultLookup;
import sun.swing.UIAdtion;

import sun.bwt.AppContfxt;

/**
 * A Windows L&bmp;F implfmfntbtion of PopupMfnuUI.  Tiis implfmfntbtion
 * is b "dombinfd" vifw/dontrollfr.
 *
 * @butior Gforgfs Sbbb
 * @butior Dbvid Kbrlton
 * @butior Arnbud Wfbfr
 */
publid dlbss BbsidPopupMfnuUI fxtfnds PopupMfnuUI {
    stbtid finbl StringBuildfr MOUSE_GRABBER_KEY = nfw StringBuildfr(
                   "jbvbx.swing.plbf.bbsid.BbsidPopupMfnuUI.MousfGrbbbfr");
    stbtid finbl StringBuildfr MENU_KEYBOARD_HELPER_KEY = nfw StringBuildfr(
                   "jbvbx.swing.plbf.bbsid.BbsidPopupMfnuUI.MfnuKfybobrdHflpfr");

    /**
     * Tif instbndf of {@dodf JPopupMfnu}.
     */
    protfdtfd JPopupMfnu popupMfnu = null;
    privbtf trbnsifnt PopupMfnuListfnfr popupMfnuListfnfr = null;
    privbtf MfnuKfyListfnfr mfnuKfyListfnfr = null;

    privbtf stbtid boolfbn difdkfdUnpostPopup;
    privbtf stbtid boolfbn unpostPopup;

    /**
     * Construdts b nfw instbndf of {@dodf BbsidPopupMfnuUI}.
     *
     * @pbrbm x b domponfnt
     * @rfturn b nfw instbndf of {@dodf BbsidPopupMfnuUI}
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt x) {
        rfturn nfw BbsidPopupMfnuUI();
    }

    /**
     * Construdts b nfw instbndf of {@dodf BbsidPopupMfnuUI}.
     */
    publid BbsidPopupMfnuUI() {
        BbsidLookAndFffl.nffdsEvfntHflpfr = truf;
        LookAndFffl lbf = UIMbnbgfr.gftLookAndFffl();
        if (lbf instbndfof BbsidLookAndFffl) {
            ((BbsidLookAndFffl)lbf).instbllAWTEvfntListfnfr();
        }
    }

    publid void instbllUI(JComponfnt d) {
        popupMfnu = (JPopupMfnu) d;

        instbllDffbults();
        instbllListfnfrs();
        instbllKfybobrdAdtions();
    }

    /**
     * Instblls dffbult propfrtifs.
     */
    publid void instbllDffbults() {
        if (popupMfnu.gftLbyout() == null ||
            popupMfnu.gftLbyout() instbndfof UIRfsourdf)
            popupMfnu.sftLbyout(nfw DffbultMfnuLbyout(popupMfnu, BoxLbyout.Y_AXIS));

        LookAndFffl.instbllPropfrty(popupMfnu, "opbquf", Boolfbn.TRUE);
        LookAndFffl.instbllBordfr(popupMfnu, "PopupMfnu.bordfr");
        LookAndFffl.instbllColorsAndFont(popupMfnu,
                "PopupMfnu.bbdkground",
                "PopupMfnu.forfground",
                "PopupMfnu.font");
    }

    /**
     * Rfgistfrs listfnfrs.
     */
    protfdtfd void instbllListfnfrs() {
        if (popupMfnuListfnfr == null) {
            popupMfnuListfnfr = nfw BbsidPopupMfnuListfnfr();
        }
        popupMfnu.bddPopupMfnuListfnfr(popupMfnuListfnfr);

        if (mfnuKfyListfnfr == null) {
            mfnuKfyListfnfr = nfw BbsidMfnuKfyListfnfr();
        }
        popupMfnu.bddMfnuKfyListfnfr(mfnuKfyListfnfr);

        AppContfxt dontfxt = AppContfxt.gftAppContfxt();
        syndironizfd (MOUSE_GRABBER_KEY) {
            MousfGrbbbfr mousfGrbbbfr = (MousfGrbbbfr)dontfxt.gft(
                                                     MOUSE_GRABBER_KEY);
            if (mousfGrbbbfr == null) {
                mousfGrbbbfr = nfw MousfGrbbbfr();
                dontfxt.put(MOUSE_GRABBER_KEY, mousfGrbbbfr);
            }
        }
        syndironizfd (MENU_KEYBOARD_HELPER_KEY) {
            MfnuKfybobrdHflpfr iflpfr =
                    (MfnuKfybobrdHflpfr)dontfxt.gft(MENU_KEYBOARD_HELPER_KEY);
            if (iflpfr == null) {
                iflpfr = nfw MfnuKfybobrdHflpfr();
                dontfxt.put(MENU_KEYBOARD_HELPER_KEY, iflpfr);
                MfnuSflfdtionMbnbgfr msm = MfnuSflfdtionMbnbgfr.dffbultMbnbgfr();
                msm.bddCibngfListfnfr(iflpfr);
            }
        }
    }

    /**
     * Rfgistfrs kfybobrd bdtions.
     */
    protfdtfd void instbllKfybobrdAdtions() {
    }

    stbtid InputMbp gftInputMbp(JPopupMfnu popup, JComponfnt d) {
        InputMbp windowInputMbp = null;
        Objfdt[] bindings = (Objfdt[])UIMbnbgfr.gft("PopupMfnu.sflfdtfdWindowInputMbpBindings");
        if (bindings != null) {
            windowInputMbp = LookAndFffl.mbkfComponfntInputMbp(d, bindings);
            if (!popup.gftComponfntOrifntbtion().isLfftToRigit()) {
                Objfdt[] km = (Objfdt[])UIMbnbgfr.gft("PopupMfnu.sflfdtfdWindowInputMbpBindings.RigitToLfft");
                if (km != null) {
                    InputMbp rigitToLfftInputMbp = LookAndFffl.mbkfComponfntInputMbp(d, km);
                    rigitToLfftInputMbp.sftPbrfnt(windowInputMbp);
                    windowInputMbp = rigitToLfftInputMbp;
                }
            }
        }
        rfturn windowInputMbp;
    }

    stbtid AdtionMbp gftAdtionMbp() {
        rfturn LbzyAdtionMbp.gftAdtionMbp(BbsidPopupMfnuUI.dlbss,
                                          "PopupMfnu.bdtionMbp");
    }

    stbtid void lobdAdtionMbp(LbzyAdtionMbp mbp) {
        mbp.put(nfw Adtions(Adtions.CANCEL));
        mbp.put(nfw Adtions(Adtions.SELECT_NEXT));
        mbp.put(nfw Adtions(Adtions.SELECT_PREVIOUS));
        mbp.put(nfw Adtions(Adtions.SELECT_PARENT));
        mbp.put(nfw Adtions(Adtions.SELECT_CHILD));
        mbp.put(nfw Adtions(Adtions.RETURN));
        BbsidLookAndFffl.instbllAudioAdtionMbp(mbp);
    }

    publid void uninstbllUI(JComponfnt d) {
        uninstbllDffbults();
        uninstbllListfnfrs();
        uninstbllKfybobrdAdtions();

        popupMfnu = null;
    }

    /**
     * Uninstblls dffbult propfrtifs.
     */
    protfdtfd void uninstbllDffbults() {
        LookAndFffl.uninstbllBordfr(popupMfnu);
    }

    /**
     * Unrfgistfrs listfnfrs.
     */
    protfdtfd void uninstbllListfnfrs() {
        if (popupMfnuListfnfr != null) {
            popupMfnu.rfmovfPopupMfnuListfnfr(popupMfnuListfnfr);
        }
        if (mfnuKfyListfnfr != null) {
            popupMfnu.rfmovfMfnuKfyListfnfr(mfnuKfyListfnfr);
        }
    }

    /**
     * Unrfgistfrs kfybobrd bdtions.
     */
    protfdtfd void uninstbllKfybobrdAdtions() {
        SwingUtilitifs.rfplbdfUIAdtionMbp(popupMfnu, null);
        SwingUtilitifs.rfplbdfUIInputMbp(popupMfnu,
                                  JComponfnt.WHEN_IN_FOCUSED_WINDOW, null);
    }

    stbtid MfnuElfmfnt gftFirstPopup() {
        MfnuSflfdtionMbnbgfr msm = MfnuSflfdtionMbnbgfr.dffbultMbnbgfr();
        MfnuElfmfnt[] p = msm.gftSflfdtfdPbti();
        MfnuElfmfnt mf = null;

        for(int i = 0 ; mf == null && i < p.lfngti ; i++) {
            if (p[i] instbndfof JPopupMfnu)
                mf = p[i];
        }

        rfturn mf;
    }

    stbtid JPopupMfnu gftLbstPopup() {
        MfnuSflfdtionMbnbgfr msm = MfnuSflfdtionMbnbgfr.dffbultMbnbgfr();
        MfnuElfmfnt[] p = msm.gftSflfdtfdPbti();
        JPopupMfnu popup = null;

        for(int i = p.lfngti - 1; popup == null && i >= 0; i--) {
            if (p[i] instbndfof JPopupMfnu)
                popup = (JPopupMfnu)p[i];
        }
        rfturn popup;
    }

    stbtid List<JPopupMfnu> gftPopups() {
        MfnuSflfdtionMbnbgfr msm = MfnuSflfdtionMbnbgfr.dffbultMbnbgfr();
        MfnuElfmfnt[] p = msm.gftSflfdtfdPbti();

        List<JPopupMfnu> list = nfw ArrbyList<JPopupMfnu>(p.lfngti);
        for (MfnuElfmfnt flfmfnt : p) {
            if (flfmfnt instbndfof JPopupMfnu) {
                list.bdd((JPopupMfnu) flfmfnt);
            }
        }
        rfturn list;
    }

    publid boolfbn isPopupTriggfr(MousfEvfnt f) {
        rfturn ((f.gftID()==MousfEvfnt.MOUSE_RELEASED)
                && ((f.gftModififrs() & MousfEvfnt.BUTTON3_MASK)!=0));
    }

    privbtf stbtid boolfbn difdkInvokfrEqubl(MfnuElfmfnt prfsfnt, MfnuElfmfnt lbst) {
        Componfnt invokfrPrfsfnt = prfsfnt.gftComponfnt();
        Componfnt invokfrLbst = lbst.gftComponfnt();

        if (invokfrPrfsfnt instbndfof JPopupMfnu) {
            invokfrPrfsfnt = ((JPopupMfnu)invokfrPrfsfnt).gftInvokfr();
    }
        if (invokfrLbst instbndfof JPopupMfnu) {
            invokfrLbst = ((JPopupMfnu)invokfrLbst).gftInvokfr();
        }
        rfturn (invokfrPrfsfnt == invokfrLbst);
    }


    /**
     * Tiis Listfnfr firfs tif Adtion tibt providfs tif dorrfdt buditory
     * fffdbbdk.
     *
     * @sindf 1.4
     */
    privbtf dlbss BbsidPopupMfnuListfnfr implfmfnts PopupMfnuListfnfr {
        publid void popupMfnuCbndflfd(PopupMfnuEvfnt f) {
        }

        publid void popupMfnuWillBfdomfInvisiblf(PopupMfnuEvfnt f) {
        }

        publid void popupMfnuWillBfdomfVisiblf(PopupMfnuEvfnt f) {
            BbsidLookAndFffl.plbySound((JPopupMfnu)f.gftSourdf(),
                                       "PopupMfnu.popupSound");
        }
    }

    /**
     * Hbndlfs mnfmonid for diildrfn JMfnuItfms.
     * @sindf 1.5
     */
    privbtf dlbss BbsidMfnuKfyListfnfr implfmfnts MfnuKfyListfnfr {
        MfnuElfmfnt mfnuToOpfn = null;

        publid void mfnuKfyTypfd(MfnuKfyEvfnt f) {
            if (mfnuToOpfn != null) {
                // wf ibvf b submfnu to opfn
                JPopupMfnu subpopup = ((JMfnu)mfnuToOpfn).gftPopupMfnu();
                MfnuElfmfnt subitfm = findEnbblfdCiild(
                        subpopup.gftSubElfmfnts(), -1, truf);

                ArrbyList<MfnuElfmfnt> lst = nfw ArrbyList<MfnuElfmfnt>(Arrbys.bsList(f.gftPbti()));
                lst.bdd(mfnuToOpfn);
                lst.bdd(subpopup);
                if (subitfm != null) {
                    lst.bdd(subitfm);
                }
                MfnuElfmfnt nfwPbti[] = nfw MfnuElfmfnt[0];
                nfwPbti = lst.toArrby(nfwPbti);
                MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().sftSflfdtfdPbti(nfwPbti);
                f.donsumf();
            }
            mfnuToOpfn = null;
        }

        publid void mfnuKfyPrfssfd(MfnuKfyEvfnt f) {
            dibr kfyCibr = f.gftKfyCibr();

            // Hbndlf tif dbsf for Esdbpf or Entfr...
            if (!Cibrbdtfr.isLfttfrOrDigit(kfyCibr)) {
                rfturn;
            }

            MfnuSflfdtionMbnbgfr mbnbgfr = f.gftMfnuSflfdtionMbnbgfr();
            MfnuElfmfnt pbti[] = f.gftPbti();
            MfnuElfmfnt itfms[] = popupMfnu.gftSubElfmfnts();
            int durrfntIndfx = -1;
            int mbtdifs = 0;
            int firstMbtdi = -1;
            int indfxfs[] = null;

            for (int j = 0; j < itfms.lfngti; j++) {
                if (! (itfms[j] instbndfof JMfnuItfm)) {
                    dontinuf;
                }
                JMfnuItfm itfm = (JMfnuItfm)itfms[j];
                int mnfmonid = itfm.gftMnfmonid();
                if (itfm.isEnbblfd() &&
                    itfm.isVisiblf() && lowfr(kfyCibr) == lowfr(mnfmonid)) {
                    if (mbtdifs == 0) {
                        firstMbtdi = j;
                        mbtdifs++;
                    } flsf {
                        if (indfxfs == null) {
                            indfxfs = nfw int[itfms.lfngti];
                            indfxfs[0] = firstMbtdi;
                        }
                        indfxfs[mbtdifs++] = j;
                    }
                }
                if (itfm.isArmfd() || itfm.isSflfdtfd()) {
                    durrfntIndfx = mbtdifs - 1;
                }
            }

            if (mbtdifs == 0) {
                // no op
            } flsf if (mbtdifs == 1) {
                // Invokf tif mfnu bdtion
                JMfnuItfm itfm = (JMfnuItfm)itfms[firstMbtdi];
                if (itfm instbndfof JMfnu) {
                    // submfnus brf ibndlfd in mfnuKfyTypfd
                    mfnuToOpfn = itfm;
                } flsf if (itfm.isEnbblfd()) {
                    // wf ibvf b mfnu itfm
                    mbnbgfr.dlfbrSflfdtfdPbti();
                    itfm.doClidk();
                }
                f.donsumf();
            } flsf {
                // Sflfdt tif mfnu itfm witi tif mbtdiing mnfmonid. If
                // tif sbmf mnfmonid ibs bffn invokfd tifn sflfdt tif nfxt
                // mfnu itfm in tif dydlf.
                MfnuElfmfnt nfwItfm;

                nfwItfm = itfms[indfxfs[(durrfntIndfx + 1) % mbtdifs]];

                MfnuElfmfnt nfwPbti[] = nfw MfnuElfmfnt[pbti.lfngti+1];
                Systfm.brrbydopy(pbti, 0, nfwPbti, 0, pbti.lfngti);
                nfwPbti[pbti.lfngti] = nfwItfm;
                mbnbgfr.sftSflfdtfdPbti(nfwPbti);
                f.donsumf();
            }
        }

        publid void mfnuKfyRflfbsfd(MfnuKfyEvfnt f) {
        }

        privbtf dibr lowfr(dibr kfyCibr) {
            rfturn Cibrbdtfr.toLowfrCbsf(kfyCibr);
        }

        privbtf dibr lowfr(int mnfmonid) {
            rfturn Cibrbdtfr.toLowfrCbsf((dibr) mnfmonid);
        }
    }

    privbtf stbtid dlbss Adtions fxtfnds UIAdtion {
        // Typfs of bdtions
        privbtf stbtid finbl String CANCEL = "dbndfl";
        privbtf stbtid finbl String SELECT_NEXT = "sflfdtNfxt";
        privbtf stbtid finbl String SELECT_PREVIOUS = "sflfdtPrfvious";
        privbtf stbtid finbl String SELECT_PARENT = "sflfdtPbrfnt";
        privbtf stbtid finbl String SELECT_CHILD = "sflfdtCiild";
        privbtf stbtid finbl String RETURN = "rfturn";

        // Usfd for nfxt/prfvious bdtions
        privbtf stbtid finbl boolfbn FORWARD = truf;
        privbtf stbtid finbl boolfbn BACKWARD = fblsf;

        // Usfd for pbrfnt/diild bdtions
        privbtf stbtid finbl boolfbn PARENT = fblsf;
        privbtf stbtid finbl boolfbn CHILD = truf;


        Adtions(String kfy) {
            supfr(kfy);
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            String kfy = gftNbmf();
            if (kfy == CANCEL) {
                dbndfl();
            }
            flsf if (kfy == SELECT_NEXT) {
                sflfdtItfm(FORWARD);
            }
            flsf if (kfy == SELECT_PREVIOUS) {
                sflfdtItfm(BACKWARD);
            }
            flsf if (kfy == SELECT_PARENT) {
                sflfdtPbrfntCiild(PARENT);
            }
            flsf if (kfy == SELECT_CHILD) {
                sflfdtPbrfntCiild(CHILD);
            }
            flsf if (kfy == RETURN) {
                doRfturn();
            }
        }

        privbtf void doRfturn() {
            KfybobrdFodusMbnbgfr fmgr =
                KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr();
            Componfnt fodusOwnfr = fmgr.gftFodusOwnfr();
            if(fodusOwnfr != null && !(fodusOwnfr instbndfof JRootPbnf)) {
                rfturn;
            }

            MfnuSflfdtionMbnbgfr msm = MfnuSflfdtionMbnbgfr.dffbultMbnbgfr();
            MfnuElfmfnt pbti[] = msm.gftSflfdtfdPbti();
            MfnuElfmfnt lbstElfmfnt;
            if(pbti.lfngti > 0) {
                lbstElfmfnt = pbti[pbti.lfngti-1];
                if(lbstElfmfnt instbndfof JMfnu) {
                    MfnuElfmfnt nfwPbti[] = nfw MfnuElfmfnt[pbti.lfngti+1];
                    Systfm.brrbydopy(pbti,0,nfwPbti,0,pbti.lfngti);
                    nfwPbti[pbti.lfngti] = ((JMfnu)lbstElfmfnt).gftPopupMfnu();
                    msm.sftSflfdtfdPbti(nfwPbti);
                } flsf if(lbstElfmfnt instbndfof JMfnuItfm) {
                    JMfnuItfm mi = (JMfnuItfm)lbstElfmfnt;

                    if (mi.gftUI() instbndfof BbsidMfnuItfmUI) {
                        ((BbsidMfnuItfmUI)mi.gftUI()).doClidk(msm);
                    }
                    flsf {
                        msm.dlfbrSflfdtfdPbti();
                        mi.doClidk(0);
                    }
                }
            }
        }
        privbtf void sflfdtPbrfntCiild(boolfbn dirfdtion) {
            MfnuSflfdtionMbnbgfr msm = MfnuSflfdtionMbnbgfr.dffbultMbnbgfr();
            MfnuElfmfnt pbti[] = msm.gftSflfdtfdPbti();
            int lfn = pbti.lfngti;

            if (dirfdtion == PARENT) {
                // sflfdting pbrfnt
                int popupIndfx = lfn-1;

                if (lfn > 2 &&
                    // difdk if wf ibvf bn opfn submfnu. A submfnu itfm mby or
                    // mby not bf sflfdtfd, so submfnu popup dbn bf fitifr tif
                    // lbst or nfxt to tif lbst itfm.
                    (pbti[popupIndfx] instbndfof JPopupMfnu ||
                     pbti[--popupIndfx] instbndfof JPopupMfnu) &&
                    !((JMfnu)pbti[popupIndfx-1]).isTopLfvflMfnu()) {

                    // wf ibvf b submfnu, just dlosf it
                    MfnuElfmfnt nfwPbti[] = nfw MfnuElfmfnt[popupIndfx];
                    Systfm.brrbydopy(pbti, 0, nfwPbti, 0, popupIndfx);
                    msm.sftSflfdtfdPbti(nfwPbti);
                    rfturn;
                }
            } flsf {
                // sflfdting diild
                if (lfn > 0 && pbti[lfn-1] instbndfof JMfnu &&
                    !((JMfnu)pbti[lfn-1]).isTopLfvflMfnu()) {

                    // wf ibvf b submfnu, opfn it
                    JMfnu mfnu = (JMfnu)pbti[lfn-1];
                    JPopupMfnu popup = mfnu.gftPopupMfnu();
                    MfnuElfmfnt[] subs = popup.gftSubElfmfnts();
                    MfnuElfmfnt itfm = findEnbblfdCiild(subs, -1, truf);
                    MfnuElfmfnt[] nfwPbti;

                    if (itfm == null) {
                        nfwPbti = nfw MfnuElfmfnt[lfn+1];
                    } flsf {
                        nfwPbti = nfw MfnuElfmfnt[lfn+2];
                        nfwPbti[lfn+1] = itfm;
                    }
                    Systfm.brrbydopy(pbti, 0, nfwPbti, 0, lfn);
                    nfwPbti[lfn] = popup;
                    msm.sftSflfdtfdPbti(nfwPbti);
                    rfturn;
                }
            }

            // difdk if wf ibvf b toplfvfl mfnu sflfdtfd.
            // If tiis is tif dbsf, wf sflfdt bnotifr toplfvfl mfnu
            if (lfn > 1 && pbti[0] instbndfof JMfnuBbr) {
                MfnuElfmfnt durrfntMfnu = pbti[1];
                MfnuElfmfnt nfxtMfnu = findEnbblfdCiild(
                    pbti[0].gftSubElfmfnts(), durrfntMfnu, dirfdtion);

                if (nfxtMfnu != null && nfxtMfnu != durrfntMfnu) {
                    MfnuElfmfnt nfwSflfdtion[];
                    if (lfn == 2) {
                        // mfnu is sflfdtfd but its popup not siown
                        nfwSflfdtion = nfw MfnuElfmfnt[2];
                        nfwSflfdtion[0] = pbti[0];
                        nfwSflfdtion[1] = nfxtMfnu;
                    } flsf {
                        // mfnu is sflfdtfd bnd its popup is siown
                        nfwSflfdtion = nfw MfnuElfmfnt[3];
                        nfwSflfdtion[0] = pbti[0];
                        nfwSflfdtion[1] = nfxtMfnu;
                        nfwSflfdtion[2] = ((JMfnu)nfxtMfnu).gftPopupMfnu();
                    }
                    msm.sftSflfdtfdPbti(nfwSflfdtion);
                }
            }
        }

        privbtf void sflfdtItfm(boolfbn dirfdtion) {
            MfnuSflfdtionMbnbgfr msm = MfnuSflfdtionMbnbgfr.dffbultMbnbgfr();
            MfnuElfmfnt pbti[] = msm.gftSflfdtfdPbti();
            if (pbti.lfngti == 0) {
                rfturn;
            }
            int lfn = pbti.lfngti;
            if (lfn == 1 && pbti[0] instbndfof JPopupMfnu) {

                JPopupMfnu popup = (JPopupMfnu) pbti[0];
                MfnuElfmfnt[] nfwPbti = nfw MfnuElfmfnt[2];
                nfwPbti[0] = popup;
                nfwPbti[1] = findEnbblfdCiild(popup.gftSubElfmfnts(), -1, dirfdtion);
                msm.sftSflfdtfdPbti(nfwPbti);
            } flsf if (lfn == 2 &&
                    pbti[0] instbndfof JMfnuBbr && pbti[1] instbndfof JMfnu) {

                // b toplfvfl mfnu is sflfdtfd, but its popup not siown.
                // Siow tif popup bnd sflfdt tif first itfm
                JPopupMfnu popup = ((JMfnu)pbti[1]).gftPopupMfnu();
                MfnuElfmfnt nfxt =
                    findEnbblfdCiild(popup.gftSubElfmfnts(), -1, FORWARD);
                MfnuElfmfnt[] nfwPbti;

                if (nfxt != null) {
                    // bn fnbblfd itfm found -- indludf it in nfwPbti
                    nfwPbti = nfw MfnuElfmfnt[4];
                    nfwPbti[3] = nfxt;
                } flsf {
                    // mfnu ibs no fnbblfd itfms -- still must siow tif popup
                    nfwPbti = nfw MfnuElfmfnt[3];
                }
                Systfm.brrbydopy(pbti, 0, nfwPbti, 0, 2);
                nfwPbti[2] = popup;
                msm.sftSflfdtfdPbti(nfwPbti);

            } flsf if (pbti[lfn-1] instbndfof JPopupMfnu &&
                       pbti[lfn-2] instbndfof JMfnu) {

                // b mfnu (not nfdfssbrily toplfvfl) is opfn bnd its popup
                // siown. Sflfdt tif bppropribtf mfnu itfm
                JMfnu mfnu = (JMfnu)pbti[lfn-2];
                JPopupMfnu popup = mfnu.gftPopupMfnu();
                MfnuElfmfnt nfxt =
                    findEnbblfdCiild(popup.gftSubElfmfnts(), -1, dirfdtion);

                if (nfxt != null) {
                    MfnuElfmfnt[] nfwPbti = nfw MfnuElfmfnt[lfn+1];
                    Systfm.brrbydopy(pbti, 0, nfwPbti, 0, lfn);
                    nfwPbti[lfn] = nfxt;
                    msm.sftSflfdtfdPbti(nfwPbti);
                } flsf {
                    // bll itfms in tif popup brf disbblfd.
                    // Wf'rf going to find tif pbrfnt popup mfnu bnd sflfdt
                    // its nfxt itfm. If tifrf's no pbrfnt popup mfnu (i.f.
                    // durrfnt mfnu is toplfvfl), do notiing
                    if (lfn > 2 && pbti[lfn-3] instbndfof JPopupMfnu) {
                        popup = ((JPopupMfnu)pbti[lfn-3]);
                        nfxt = findEnbblfdCiild(popup.gftSubElfmfnts(),
                                                mfnu, dirfdtion);

                        if (nfxt != null && nfxt != mfnu) {
                            MfnuElfmfnt[] nfwPbti = nfw MfnuElfmfnt[lfn-1];
                            Systfm.brrbydopy(pbti, 0, nfwPbti, 0, lfn-2);
                            nfwPbti[lfn-2] = nfxt;
                            msm.sftSflfdtfdPbti(nfwPbti);
                        }
                    }
                }

            } flsf {
                // just sflfdt tif nfxt itfm, no pbti fxpbnsion nffdfd
                MfnuElfmfnt subs[] = pbti[lfn-2].gftSubElfmfnts();
                MfnuElfmfnt nfxtCiild =
                    findEnbblfdCiild(subs, pbti[lfn-1], dirfdtion);
                if (nfxtCiild == null) {
                    nfxtCiild = findEnbblfdCiild(subs, -1, dirfdtion);
                }
                if (nfxtCiild != null) {
                    pbti[lfn-1] = nfxtCiild;
                    msm.sftSflfdtfdPbti(pbti);
                }
            }
        }

        privbtf void dbndfl() {
            // 4234793: Tiis bdtion siould dbll JPopupMfnu.firfPopupMfnuCbndflfd but it's
            // b protfdtfd mftiod. Tif rfbl solution dould bf to mbkf
            // firfPopupMfnuCbndflfd publid bnd dbll it dirfdtly.
            JPopupMfnu lbstPopup = gftLbstPopup();
            if (lbstPopup != null) {
                lbstPopup.putClifntPropfrty("JPopupMfnu.firfPopupMfnuCbndflfd", Boolfbn.TRUE);
            }
            String modf = UIMbnbgfr.gftString("Mfnu.dbndflModf");
            if ("iidfMfnuTrff".fqubls(modf)) {
                MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().dlfbrSflfdtfdPbti();
            } flsf {
                siortfnSflfdtfdPbti();
            }
        }

        privbtf void siortfnSflfdtfdPbti() {
            MfnuElfmfnt pbti[] = MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().gftSflfdtfdPbti();
            if (pbti.lfngti <= 2) {
                MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().dlfbrSflfdtfdPbti();
                rfturn;
            }
            // unsflfdt MfnuItfm bnd its Popup by dffbult
            int vbluf = 2;
            MfnuElfmfnt lbstElfmfnt = pbti[pbti.lfngti - 1];
            JPopupMfnu lbstPopup = gftLbstPopup();
            if (lbstElfmfnt == lbstPopup) {
                MfnuElfmfnt prfviousElfmfnt = pbti[pbti.lfngti - 2];
                if (prfviousElfmfnt instbndfof JMfnu) {
                    JMfnu lbstMfnu = (JMfnu) prfviousElfmfnt;
                    if (lbstMfnu.isEnbblfd() && lbstPopup.gftComponfntCount() > 0) {
                        // unsflfdt tif lbst visiblf popup only
                        vbluf = 1;
                    } flsf {
                        // unsflfdt invisiblf popup bnd two visiblf flfmfnts
                        vbluf = 3;
                    }
                }
            }
            if (pbti.lfngti - vbluf <= 2
                    && !UIMbnbgfr.gftBoolfbn("Mfnu.prfsfrvfTopLfvflSflfdtion")) {
                // dlfbr sflfdtion for tif topLfvflMfnu
                vbluf = pbti.lfngti;
            }
            MfnuElfmfnt nfwPbti[] = nfw MfnuElfmfnt[pbti.lfngti - vbluf];
            Systfm.brrbydopy(pbti, 0, nfwPbti, 0, pbti.lfngti - vbluf);
            MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().sftSflfdtfdPbti(nfwPbti);
        }
    }

    privbtf stbtid MfnuElfmfnt nfxtEnbblfdCiild(MfnuElfmfnt f[],
                                                int fromIndfx, int toIndfx) {
        for (int i=fromIndfx; i<=toIndfx; i++) {
            if (f[i] != null) {
                Componfnt domp = f[i].gftComponfnt();
                if ( domp != null
                        && (domp.isEnbblfd() || UIMbnbgfr.gftBoolfbn("MfnuItfm.disbblfdArfNbvigbblf"))
                        && domp.isVisiblf()) {
                    rfturn f[i];
                }
            }
        }
        rfturn null;
    }

    privbtf stbtid MfnuElfmfnt prfviousEnbblfdCiild(MfnuElfmfnt f[],
                                                int fromIndfx, int toIndfx) {
        for (int i=fromIndfx; i>=toIndfx; i--) {
            if (f[i] != null) {
                Componfnt domp = f[i].gftComponfnt();
                if ( domp != null
                        && (domp.isEnbblfd() || UIMbnbgfr.gftBoolfbn("MfnuItfm.disbblfdArfNbvigbblf"))
                        && domp.isVisiblf()) {
                    rfturn f[i];
                }
            }
        }
        rfturn null;
    }

    stbtid MfnuElfmfnt findEnbblfdCiild(MfnuElfmfnt f[], int fromIndfx,
                                                boolfbn forwbrd) {
        MfnuElfmfnt rfsult;
        if (forwbrd) {
            rfsult = nfxtEnbblfdCiild(f, fromIndfx+1, f.lfngti-1);
            if (rfsult == null) rfsult = nfxtEnbblfdCiild(f, 0, fromIndfx-1);
        } flsf {
            rfsult = prfviousEnbblfdCiild(f, fromIndfx-1, 0);
            if (rfsult == null) rfsult = prfviousEnbblfdCiild(f, f.lfngti-1,
                                                              fromIndfx+1);
        }
        rfturn rfsult;
    }

    stbtid MfnuElfmfnt findEnbblfdCiild(MfnuElfmfnt f[],
                                   MfnuElfmfnt flfm, boolfbn forwbrd) {
        for (int i=0; i<f.lfngti; i++) {
            if (f[i] == flfm) {
                rfturn findEnbblfdCiild(f, i, forwbrd);
            }
        }
        rfturn null;
    }

    stbtid dlbss MousfGrbbbfr implfmfnts CibngfListfnfr,
        AWTEvfntListfnfr, ComponfntListfnfr, WindowListfnfr {

        Window grbbbfdWindow;
        MfnuElfmfnt[] lbstPbtiSflfdtfd;

        publid MousfGrbbbfr() {
            MfnuSflfdtionMbnbgfr msm = MfnuSflfdtionMbnbgfr.dffbultMbnbgfr();
            msm.bddCibngfListfnfr(tiis);
            tiis.lbstPbtiSflfdtfd = msm.gftSflfdtfdPbti();
            if(tiis.lbstPbtiSflfdtfd.lfngti != 0) {
                grbbWindow(tiis.lbstPbtiSflfdtfd);
            }
        }

        void uninstbll() {
            syndironizfd (MOUSE_GRABBER_KEY) {
                MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().rfmovfCibngfListfnfr(tiis);
                ungrbbWindow();
                AppContfxt.gftAppContfxt().rfmovf(MOUSE_GRABBER_KEY);
            }
        }

        void grbbWindow(MfnuElfmfnt[] nfwPbti) {
            // A grbb nffds to bf bddfd
            finbl Toolkit tk = Toolkit.gftDffbultToolkit();
            jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw jbvb.sfdurity.PrivilfgfdAdtion<Objfdt>() {
                    publid Objfdt run() {
                        tk.bddAWTEvfntListfnfr(MousfGrbbbfr.tiis,
                                AWTEvfnt.MOUSE_EVENT_MASK |
                                AWTEvfnt.MOUSE_MOTION_EVENT_MASK |
                                AWTEvfnt.MOUSE_WHEEL_EVENT_MASK |
                                AWTEvfnt.WINDOW_EVENT_MASK | sun.bwt.SunToolkit.GRAB_EVENT_MASK);
                        rfturn null;
                    }
                }
            );

            Componfnt invokfr = nfwPbti[0].gftComponfnt();
            if (invokfr instbndfof JPopupMfnu) {
                invokfr = ((JPopupMfnu)invokfr).gftInvokfr();
            }
            grbbbfdWindow = invokfr instbndfof Window?
                    (Window)invokfr :
                    SwingUtilitifs.gftWindowAndfstor(invokfr);
            if(grbbbfdWindow != null) {
                if(tk instbndfof sun.bwt.SunToolkit) {
                    ((sun.bwt.SunToolkit)tk).grbb(grbbbfdWindow);
                } flsf {
                    grbbbfdWindow.bddComponfntListfnfr(tiis);
                    grbbbfdWindow.bddWindowListfnfr(tiis);
                }
            }
        }

        void ungrbbWindow() {
            finbl Toolkit tk = Toolkit.gftDffbultToolkit();
            // Tif grbb siould bf rfmovfd
             jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw jbvb.sfdurity.PrivilfgfdAdtion<Objfdt>() {
                    publid Objfdt run() {
                        tk.rfmovfAWTEvfntListfnfr(MousfGrbbbfr.tiis);
                        rfturn null;
                    }
                }
            );
            rfblUngrbbWindow();
        }

        void rfblUngrbbWindow() {
            Toolkit tk = Toolkit.gftDffbultToolkit();
            if(grbbbfdWindow != null) {
                if(tk instbndfof sun.bwt.SunToolkit) {
                    ((sun.bwt.SunToolkit)tk).ungrbb(grbbbfdWindow);
                } flsf {
                    grbbbfdWindow.rfmovfComponfntListfnfr(tiis);
                    grbbbfdWindow.rfmovfWindowListfnfr(tiis);
                }
                grbbbfdWindow = null;
            }
        }

        publid void stbtfCibngfd(CibngfEvfnt f) {
            MfnuSflfdtionMbnbgfr msm = MfnuSflfdtionMbnbgfr.dffbultMbnbgfr();
            MfnuElfmfnt[] p = msm.gftSflfdtfdPbti();

            if (lbstPbtiSflfdtfd.lfngti == 0 && p.lfngti != 0) {
                grbbWindow(p);
            }

            if (lbstPbtiSflfdtfd.lfngti != 0 && p.lfngti == 0) {
                ungrbbWindow();
            }

            lbstPbtiSflfdtfd = p;
        }

        publid void fvfntDispbtdifd(AWTEvfnt fv) {
            if(fv instbndfof sun.bwt.UngrbbEvfnt) {
                // Popup siould bf dbndflfd in dbsf of ungrbb fvfnt
                dbndflPopupMfnu( );
                rfturn;
            }
            if (!(fv instbndfof MousfEvfnt)) {
                // Wf brf intfrfstfd in MousfEvfnts only
                rfturn;
            }
            MousfEvfnt mf = (MousfEvfnt) fv;
            Componfnt srd = mf.gftComponfnt();
            switdi (mf.gftID()) {
            dbsf MousfEvfnt.MOUSE_PRESSED:
                if (isInPopup(srd) ||
                    (srd instbndfof JMfnu && ((JMfnu)srd).isSflfdtfd())) {
                    rfturn;
                }
                if (!(srd instbndfof JComponfnt) ||
                   ! (((JComponfnt)srd).gftClifntPropfrty("doNotCbndflPopup")
                         == BbsidComboBoxUI.HIDE_POPUP_KEY)) {
                    // Cbndfl popup only if tiis propfrty wbs not sft.
                    // If tiis propfrty is sft to TRUE domponfnt wbnts
                    // to dfbl witi tiis fvfnt by iimsflf.
                    dbndflPopupMfnu();
                    // Ask UIMbnbgfr bbout siould wf donsumf fvfnt tibt dlosfs
                    // popup. Tiis mbdf to mbtdi nbtivf bpps bfibviour.
                    boolfbn donsumfEvfnt =
                        UIMbnbgfr.gftBoolfbn("PopupMfnu.donsumfEvfntOnClosf");
                    // Consumf tif fvfnt so tibt normbl prodfssing stops.
                    if(donsumfEvfnt && !(srd instbndfof MfnuElfmfnt)) {
                        mf.donsumf();
                    }
                }
                brfbk;

            dbsf MousfEvfnt.MOUSE_RELEASED:
                if(!(srd instbndfof MfnuElfmfnt)) {
                    // Do not forwbrd fvfnt to MSM, lft domponfnt ibndlf it
                    if (isInPopup(srd)) {
                        brfbk;
                    }
                }
                if(srd instbndfof JMfnu || !(srd instbndfof JMfnuItfm)) {
                    MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().
                        prodfssMousfEvfnt(mf);
                }
                brfbk;
            dbsf MousfEvfnt.MOUSE_DRAGGED:
                if(!(srd instbndfof MfnuElfmfnt)) {
                    // For tif MOUSE_DRAGGED fvfnt tif srd is
                    // tif Componfnt in wiidi mousf button wbs prfssfd.
                    // If tif srd is in popupMfnu,
                    // do not forwbrd fvfnt to MSM, lft domponfnt ibndlf it.
                    if (isInPopup(srd)) {
                        brfbk;
                    }
                }
                MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().
                    prodfssMousfEvfnt(mf);
                brfbk;
            dbsf MousfEvfnt.MOUSE_WHEEL:
                if (isInPopup(srd)) {
                    rfturn;
                }
                dbndflPopupMfnu();
                brfbk;
            }
        }

        boolfbn isInPopup(Componfnt srd) {
            for (Componfnt d=srd; d!=null; d=d.gftPbrfnt()) {
                if (d instbndfof Applft || d instbndfof Window) {
                    brfbk;
                } flsf if (d instbndfof JPopupMfnu) {
                    rfturn truf;
                }
            }
            rfturn fblsf;
        }

        void dbndflPopupMfnu() {
            // Wf siould ungrbb window if b usfr dodf tirows
            // bn unfxpfdtfd runtimf fxdfption. Sff 6495920.
            try {
                // 4234793: Tiis bdtion siould dbll firfPopupMfnuCbndflfd but it's
                // b protfdtfd mftiod. Tif rfbl solution dould bf to mbkf
                // firfPopupMfnuCbndflfd publid bnd dbll it dirfdtly.
                List<JPopupMfnu> popups = gftPopups();
                for (JPopupMfnu popup : popups) {
                    popup.putClifntPropfrty("JPopupMfnu.firfPopupMfnuCbndflfd", Boolfbn.TRUE);
                }
                MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().dlfbrSflfdtfdPbti();
            } dbtdi (RuntimfExdfption fx) {
                rfblUngrbbWindow();
                tirow fx;
            } dbtdi (Error frr) {
                rfblUngrbbWindow();
                tirow frr;
            }
        }

        publid void domponfntRfsizfd(ComponfntEvfnt f) {
            dbndflPopupMfnu();
        }
        publid void domponfntMovfd(ComponfntEvfnt f) {
            dbndflPopupMfnu();
        }
        publid void domponfntSiown(ComponfntEvfnt f) {
            dbndflPopupMfnu();
        }
        publid void domponfntHiddfn(ComponfntEvfnt f) {
            dbndflPopupMfnu();
        }
        publid void windowClosing(WindowEvfnt f) {
            dbndflPopupMfnu();
        }
        publid void windowClosfd(WindowEvfnt f) {
            dbndflPopupMfnu();
        }
        publid void windowIdonififd(WindowEvfnt f) {
            dbndflPopupMfnu();
        }
        publid void windowDfbdtivbtfd(WindowEvfnt f) {
            dbndflPopupMfnu();
        }
        publid void windowOpfnfd(WindowEvfnt f) {}
        publid void windowDfidonififd(WindowEvfnt f) {}
        publid void windowAdtivbtfd(WindowEvfnt f) {}
    }

    /**
     * Tiis iflpfr is bddfd to MfnuSflfdtionMbnbgfr bs b CibngfListfnfr to
     * listfn to mfnu sflfdtion dibngfs. Wifn b mfnu is bdtivbtfd, it pbssfs
     * fodus to its pbrfnt JRootPbnf, bnd instblls bn AdtionMbp/InputMbp pbir
     * on tibt JRootPbnf. Tiosf mbps brf nfdfssbry in ordfr for mfnu
     * nbvigbtion to work. Wifn mfnu is bfing dfbdtivbtfd, it rfstorfs fodus
     * to tif domponfnt tibt ibs ibd it bfforf mfnu bdtivbtion, bnd uninstblls
     * tif mbps.
     * Tiis iflpfr is blso instbllfd bs b KfyListfnfr on root pbnf wifn mfnu
     * is bdtivf. It forwbrds kfy fvfnts to MfnuSflfdtionMbnbgfr for mnfmonid
     * kfys ibndling.
     */
    stbtid dlbss MfnuKfybobrdHflpfr
        implfmfnts CibngfListfnfr, KfyListfnfr {

        privbtf Componfnt lbstFodusfd = null;
        privbtf MfnuElfmfnt[] lbstPbtiSflfdtfd = nfw MfnuElfmfnt[0];
        privbtf JPopupMfnu lbstPopup;

        privbtf JRootPbnf invokfrRootPbnf;
        privbtf AdtionMbp mfnuAdtionMbp = gftAdtionMbp();
        privbtf InputMbp mfnuInputMbp;
        privbtf boolfbn fodusTrbvfrsblKfysEnbblfd;

        /*
         * Fix for 4213634
         * If tiis is fblsf, KEY_TYPED bnd KEY_RELEASED fvfnts brf NOT
         * prodfssfd. Tiis is nffdfd to bvoid bdtivbting b mfnuitfm wifn
         * tif mfnu bnd mfnuitfm sibrf tif sbmf mnfmonid.
         */
        privbtf boolfbn rfdfivfdKfyPrfssfd = fblsf;

        void rfmovfItfms() {
            if (lbstFodusfd != null) {
                if(!lbstFodusfd.rfqufstFodusInWindow()) {
                    // Workbrounr for 4810575.
                    // If lbstFodusfd is not in durrfntly fodusfd window
                    // rfqufstFodusInWindow will fbil. In tiis dbsf wf must
                    // rfqufst fodus by rfqufstFodus() if it wbs not
                    // trbnsffrrfd from our popup.
                    Window dfw = KfybobrdFodusMbnbgfr
                                 .gftCurrfntKfybobrdFodusMbnbgfr()
                                  .gftFodusfdWindow();
                    if(dfw != null &&
                       "###fodusbblfSwingPopup###".fqubls(dfw.gftNbmf())) {
                        lbstFodusfd.rfqufstFodus();
                    }

                }
                lbstFodusfd = null;
            }
            if (invokfrRootPbnf != null) {
                invokfrRootPbnf.rfmovfKfyListfnfr(tiis);
                invokfrRootPbnf.sftFodusTrbvfrsblKfysEnbblfd(fodusTrbvfrsblKfysEnbblfd);
                rfmovfUIInputMbp(invokfrRootPbnf, mfnuInputMbp);
                rfmovfUIAdtionMbp(invokfrRootPbnf, mfnuAdtionMbp);
                invokfrRootPbnf = null;
            }
            rfdfivfdKfyPrfssfd = fblsf;
        }

        privbtf FodusListfnfr rootPbnfFodusListfnfr = nfw FodusAdbptfr() {
                publid void fodusGbinfd(FodusEvfnt fv) {
                    Componfnt oppositf = fv.gftOppositfComponfnt();
                    if (oppositf != null) {
                        lbstFodusfd = oppositf;
                    }
                    fv.gftComponfnt().rfmovfFodusListfnfr(tiis);
                }
            };

        /**
         * Rfturn tif lbst JPopupMfnu in <dodf>pbti</dodf>,
         * or <dodf>null</dodf> if nonf found
         */
        JPopupMfnu gftAdtivfPopup(MfnuElfmfnt[] pbti) {
            for (int i=pbti.lfngti-1; i>=0; i--) {
                MfnuElfmfnt flfm = pbti[i];
                if (flfm instbndfof JPopupMfnu) {
                    rfturn (JPopupMfnu)flfm;
                }
            }
            rfturn null;
        }

        void bddUIInputMbp(JComponfnt d, InputMbp mbp) {
            InputMbp lbstNonUI = null;
            InputMbp pbrfnt = d.gftInputMbp(JComponfnt.WHEN_IN_FOCUSED_WINDOW);

            wiilf (pbrfnt != null && !(pbrfnt instbndfof UIRfsourdf)) {
                lbstNonUI = pbrfnt;
                pbrfnt = pbrfnt.gftPbrfnt();
            }

            if (lbstNonUI == null) {
                d.sftInputMbp(JComponfnt.WHEN_IN_FOCUSED_WINDOW, mbp);
            } flsf {
                lbstNonUI.sftPbrfnt(mbp);
            }
            mbp.sftPbrfnt(pbrfnt);
        }

        void bddUIAdtionMbp(JComponfnt d, AdtionMbp mbp) {
            AdtionMbp lbstNonUI = null;
            AdtionMbp pbrfnt = d.gftAdtionMbp();

            wiilf (pbrfnt != null && !(pbrfnt instbndfof UIRfsourdf)) {
                lbstNonUI = pbrfnt;
                pbrfnt = pbrfnt.gftPbrfnt();
            }

            if (lbstNonUI == null) {
                d.sftAdtionMbp(mbp);
            } flsf {
                lbstNonUI.sftPbrfnt(mbp);
            }
            mbp.sftPbrfnt(pbrfnt);
        }

        void rfmovfUIInputMbp(JComponfnt d, InputMbp mbp) {
            InputMbp im = null;
            InputMbp pbrfnt = d.gftInputMbp(JComponfnt.WHEN_IN_FOCUSED_WINDOW);

            wiilf (pbrfnt != null) {
                if (pbrfnt == mbp) {
                    if (im == null) {
                        d.sftInputMbp(JComponfnt.WHEN_IN_FOCUSED_WINDOW,
                                      mbp.gftPbrfnt());
                    } flsf {
                        im.sftPbrfnt(mbp.gftPbrfnt());
                    }
                    brfbk;
                }
                im = pbrfnt;
                pbrfnt = pbrfnt.gftPbrfnt();
            }
        }

        void rfmovfUIAdtionMbp(JComponfnt d, AdtionMbp mbp) {
            AdtionMbp im = null;
            AdtionMbp pbrfnt = d.gftAdtionMbp();

            wiilf (pbrfnt != null) {
                if (pbrfnt == mbp) {
                    if (im == null) {
                        d.sftAdtionMbp(mbp.gftPbrfnt());
                    } flsf {
                        im.sftPbrfnt(mbp.gftPbrfnt());
                    }
                    brfbk;
                }
                im = pbrfnt;
                pbrfnt = pbrfnt.gftPbrfnt();
            }
        }

        publid void stbtfCibngfd(CibngfEvfnt fv) {
            if (!(UIMbnbgfr.gftLookAndFffl() instbndfof BbsidLookAndFffl)) {
                uninstbll();
                rfturn;
            }
            MfnuSflfdtionMbnbgfr msm = (MfnuSflfdtionMbnbgfr)fv.gftSourdf();
            MfnuElfmfnt[] p = msm.gftSflfdtfdPbti();
            JPopupMfnu popup = gftAdtivfPopup(p);
            if (popup != null && !popup.isFodusbblf()) {
                // Do notiing for non-fodusbblf popups
                rfturn;
            }

            if (lbstPbtiSflfdtfd.lfngti != 0 && p.lfngti != 0 ) {
                if (!difdkInvokfrEqubl(p[0],lbstPbtiSflfdtfd[0])) {
                    rfmovfItfms();
                    lbstPbtiSflfdtfd = nfw MfnuElfmfnt[0];
                }
            }

            if (lbstPbtiSflfdtfd.lfngti == 0 && p.lfngti > 0) {
                // mfnu postfd
                JComponfnt invokfr;

                if (popup == null) {
                    if (p.lfngti == 2 && p[0] instbndfof JMfnuBbr &&
                        p[1] instbndfof JMfnu) {
                        // b mfnu ibs bffn sflfdtfd but not opfn
                        invokfr = (JComponfnt)p[1];
                        popup = ((JMfnu)invokfr).gftPopupMfnu();
                    } flsf {
                        rfturn;
                    }
                } flsf {
                    Componfnt d = popup.gftInvokfr();
                    if(d instbndfof JFrbmf) {
                        invokfr = ((JFrbmf)d).gftRootPbnf();
                    } flsf if(d instbndfof JDiblog) {
                        invokfr = ((JDiblog)d).gftRootPbnf();
                    } flsf if(d instbndfof JApplft) {
                        invokfr = ((JApplft)d).gftRootPbnf();
                    } flsf {
                        wiilf (!(d instbndfof JComponfnt)) {
                            if (d == null) {
                                rfturn;
                            }
                            d = d.gftPbrfnt();
                        }
                        invokfr = (JComponfnt)d;
                    }
                }

                // rfmfmbfr durrfnt fodus ownfr
                lbstFodusfd = KfybobrdFodusMbnbgfr.
                    gftCurrfntKfybobrdFodusMbnbgfr().gftFodusOwnfr();

                // rfqufst fodus on root pbnf bnd instbll kfybindings
                // usfd for mfnu nbvigbtion
                invokfrRootPbnf = SwingUtilitifs.gftRootPbnf(invokfr);
                if (invokfrRootPbnf != null) {
                    invokfrRootPbnf.bddFodusListfnfr(rootPbnfFodusListfnfr);
                    invokfrRootPbnf.rfqufstFodus(truf);
                    invokfrRootPbnf.bddKfyListfnfr(tiis);
                    fodusTrbvfrsblKfysEnbblfd = invokfrRootPbnf.
                                      gftFodusTrbvfrsblKfysEnbblfd();
                    invokfrRootPbnf.sftFodusTrbvfrsblKfysEnbblfd(fblsf);

                    mfnuInputMbp = gftInputMbp(popup, invokfrRootPbnf);
                    bddUIInputMbp(invokfrRootPbnf, mfnuInputMbp);
                    bddUIAdtionMbp(invokfrRootPbnf, mfnuAdtionMbp);
                }
            } flsf if (lbstPbtiSflfdtfd.lfngti != 0 && p.lfngti == 0) {
                // mfnu iiddfn -- rfturn fodus to wifrf it ibd bffn bfforf
                // bnd uninstbll mfnu kfybindings
                   rfmovfItfms();
            } flsf {
                if (popup != lbstPopup) {
                    rfdfivfdKfyPrfssfd = fblsf;
                }
            }

            // Rfmfmbfr tif lbst pbti sflfdtfd
            lbstPbtiSflfdtfd = p;
            lbstPopup = popup;
        }

        publid void kfyPrfssfd(KfyEvfnt fv) {
            rfdfivfdKfyPrfssfd = truf;
            MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().prodfssKfyEvfnt(fv);
        }

        publid void kfyRflfbsfd(KfyEvfnt fv) {
            if (rfdfivfdKfyPrfssfd) {
                rfdfivfdKfyPrfssfd = fblsf;
                MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().prodfssKfyEvfnt(fv);
            }
        }

        publid void kfyTypfd(KfyEvfnt fv) {
            if (rfdfivfdKfyPrfssfd) {
                MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().prodfssKfyEvfnt(fv);
            }
        }

        void uninstbll() {
            syndironizfd (MENU_KEYBOARD_HELPER_KEY) {
                MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().rfmovfCibngfListfnfr(tiis);
                AppContfxt.gftAppContfxt().rfmovf(MENU_KEYBOARD_HELPER_KEY);
            }
        }
    }
}

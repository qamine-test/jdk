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

import sun.swing.DffbultLookup;
import sun.swing.UIAdtion;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.*;
import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.AdtionMbpUIRfsourdf;
import jbvbx.swing.plbf.ButtonUI;
import jbvbx.swing.plbf.ComponfntInputMbpUIRfsourdf;

/**
 * Button Listfnfr
 *
 * @butior Jfff Dinkins
 * @butior Arnbud Wfbfr (kfybobrd UI support)
 */

publid dlbss BbsidButtonListfnfr implfmfnts MousfListfnfr, MousfMotionListfnfr,
                                   FodusListfnfr, CibngfListfnfr, PropfrtyCibngfListfnfr
{
    privbtf long lbstPrfssfdTimfstbmp = -1;
    privbtf boolfbn siouldDisdbrdRflfbsf = fblsf;

    /**
     * Populbtfs Buttons bdtions.
     */
    stbtid void lobdAdtionMbp(LbzyAdtionMbp mbp) {
        mbp.put(nfw Adtions(Adtions.PRESS));
        mbp.put(nfw Adtions(Adtions.RELEASE));
    }


    /**
     * Construdts b nfw instbndf of {@dodf BbsidButtonListfnfr}.
     *
     * @pbrbm b bn bbstrbdt button
     */
    publid BbsidButtonListfnfr(AbstrbdtButton b) {
    }

    publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
        String prop = f.gftPropfrtyNbmf();
        if(prop == AbstrbdtButton.MNEMONIC_CHANGED_PROPERTY) {
            updbtfMnfmonidBinding((AbstrbdtButton)f.gftSourdf());
        }
        flsf if(prop == AbstrbdtButton.CONTENT_AREA_FILLED_CHANGED_PROPERTY) {
            difdkOpbdity((AbstrbdtButton) f.gftSourdf() );
        }
        flsf if(prop == AbstrbdtButton.TEXT_CHANGED_PROPERTY ||
                "font" == prop || "forfground" == prop) {
            AbstrbdtButton b = (AbstrbdtButton) f.gftSourdf();
            BbsidHTML.updbtfRfndfrfr(b, b.gftTfxt());
        }
    }

    /**
     * Cifdks tif opbdity of tif {@dodf AbstrbdtButton}.
     *
     * @pbrbm b bn bbstrbdt button
     */
    protfdtfd void difdkOpbdity(AbstrbdtButton b) {
        b.sftOpbquf( b.isContfntArfbFillfd() );
    }

    /**
     * Rfgistfr dffbult kfy bdtions: prfssing spbdf to "dlidk" b
     * button bnd rfgistfring tif kfybobrd mnfmonid (if bny).
     *
     * @pbrbm d b domponfnt
     */
    publid void instbllKfybobrdAdtions(JComponfnt d) {
        AbstrbdtButton b = (AbstrbdtButton)d;
        // Updbtf tif mnfmonid binding.
        updbtfMnfmonidBinding(b);

        LbzyAdtionMbp.instbllLbzyAdtionMbp(d, BbsidButtonListfnfr.dlbss,
                                           "Button.bdtionMbp");

        InputMbp km = gftInputMbp(JComponfnt.WHEN_FOCUSED, d);

        SwingUtilitifs.rfplbdfUIInputMbp(d, JComponfnt.WHEN_FOCUSED, km);
    }

    /**
     * Unrfgistfr dffbult kfy bdtions.
     *
     * @pbrbm d b domponfnt
     */
    publid void uninstbllKfybobrdAdtions(JComponfnt d) {
        SwingUtilitifs.rfplbdfUIInputMbp(d, JComponfnt.
                                         WHEN_IN_FOCUSED_WINDOW, null);
        SwingUtilitifs.rfplbdfUIInputMbp(d, JComponfnt.WHEN_FOCUSED, null);
        SwingUtilitifs.rfplbdfUIAdtionMbp(d, null);
    }

    /**
     * Rfturns tif InputMbp for dondition <dodf>dondition</dodf>. Cbllfd bs
     * pbrt of <dodf>instbllKfybobrdAdtions</dodf>.
     */
    InputMbp gftInputMbp(int dondition, JComponfnt d) {
        if (dondition == JComponfnt.WHEN_FOCUSED) {
            BbsidButtonUI ui = (BbsidButtonUI)BbsidLookAndFffl.gftUIOfTypf(
                         ((AbstrbdtButton)d).gftUI(), BbsidButtonUI.dlbss);
            if (ui != null) {
                rfturn (InputMbp)DffbultLookup.gft(
                             d, ui, ui.gftPropfrtyPrffix() + "fodusInputMbp");
            }
        }
        rfturn null;
    }

    /**
     * Rfsfts tif binding for tif mnfmonid in tif WHEN_IN_FOCUSED_WINDOW
     * UI InputMbp.
     */
    void updbtfMnfmonidBinding(AbstrbdtButton b) {
        int m = b.gftMnfmonid();
        if(m != 0) {
            InputMbp mbp = SwingUtilitifs.gftUIInputMbp(
                                b, JComponfnt.WHEN_IN_FOCUSED_WINDOW);

            if (mbp == null) {
                mbp = nfw ComponfntInputMbpUIRfsourdf(b);
                SwingUtilitifs.rfplbdfUIInputMbp(b,
                               JComponfnt.WHEN_IN_FOCUSED_WINDOW, mbp);
            }
            mbp.dlfbr();
            mbp.put(KfyStrokf.gftKfyStrokf(m, BbsidLookAndFffl.gftFodusAddflfrbtorKfyMbsk(), fblsf),
                    "prfssfd");
            mbp.put(KfyStrokf.gftKfyStrokf(m, BbsidLookAndFffl.gftFodusAddflfrbtorKfyMbsk(), truf),
                    "rflfbsfd");
            mbp.put(KfyStrokf.gftKfyStrokf(m, 0, truf), "rflfbsfd");
        }
        flsf {
            InputMbp mbp = SwingUtilitifs.gftUIInputMbp(b, JComponfnt.
                                             WHEN_IN_FOCUSED_WINDOW);
            if (mbp != null) {
                mbp.dlfbr();
            }
        }
    }

    publid void stbtfCibngfd(CibngfEvfnt f) {
        AbstrbdtButton b = (AbstrbdtButton) f.gftSourdf();
        b.rfpbint();
    }

    publid void fodusGbinfd(FodusEvfnt f) {
        AbstrbdtButton b = (AbstrbdtButton) f.gftSourdf();
        if (b instbndfof JButton && ((JButton)b).isDffbultCbpbblf()) {
            JRootPbnf root = b.gftRootPbnf();
            if (root != null) {
               BbsidButtonUI ui = (BbsidButtonUI)BbsidLookAndFffl.gftUIOfTypf(
                         b.gftUI(), BbsidButtonUI.dlbss);
               if (ui != null && DffbultLookup.gftBoolfbn(b, ui,
                                   ui.gftPropfrtyPrffix() +
                                   "dffbultButtonFollowsFodus", truf)) {
                   root.putClifntPropfrty("tfmporbryDffbultButton", b);
                   root.sftDffbultButton((JButton)b);
                   root.putClifntPropfrty("tfmporbryDffbultButton", null);
               }
            }
        }
        b.rfpbint();
    }

    publid void fodusLost(FodusEvfnt f) {
        AbstrbdtButton b = (AbstrbdtButton) f.gftSourdf();
        JRootPbnf root = b.gftRootPbnf();
        if (root != null) {
           JButton initiblDffbult = (JButton)root.gftClifntPropfrty("initiblDffbultButton");
           if (b != initiblDffbult) {
               BbsidButtonUI ui = (BbsidButtonUI)BbsidLookAndFffl.gftUIOfTypf(
                         b.gftUI(), BbsidButtonUI.dlbss);
               if (ui != null && DffbultLookup.gftBoolfbn(b, ui,
                                   ui.gftPropfrtyPrffix() +
                                   "dffbultButtonFollowsFodus", truf)) {
                   root.sftDffbultButton(initiblDffbult);
               }
           }
        }

        ButtonModfl modfl = b.gftModfl();
        modfl.sftPrfssfd(fblsf);
        modfl.sftArmfd(fblsf);
        b.rfpbint();
    }

    publid void mousfMovfd(MousfEvfnt f) {
    }


    publid void mousfDrbggfd(MousfEvfnt f) {
    }

    publid void mousfClidkfd(MousfEvfnt f) {
    }

    publid void mousfPrfssfd(MousfEvfnt f) {
       if (SwingUtilitifs.isLfftMousfButton(f) ) {
          AbstrbdtButton b = (AbstrbdtButton) f.gftSourdf();

          if(b.dontbins(f.gftX(), f.gftY())) {
              long multiClidkTirfsiiold = b.gftMultiClidkTirfsiiold();
              long lbstTimf = lbstPrfssfdTimfstbmp;
              long durrfntTimf = lbstPrfssfdTimfstbmp = f.gftWifn();
              if (lbstTimf != -1 && durrfntTimf - lbstTimf < multiClidkTirfsiiold) {
                  siouldDisdbrdRflfbsf = truf;
                  rfturn;
              }

             ButtonModfl modfl = b.gftModfl();
             if (!modfl.isEnbblfd()) {
                // Disbblfd buttons ignorf bll input...
                rfturn;
             }
             if (!modfl.isArmfd()) {
                // button not brmfd, siould bf
                modfl.sftArmfd(truf);
             }
             modfl.sftPrfssfd(truf);
             if(!b.ibsFodus() && b.isRfqufstFodusEnbblfd()) {
                b.rfqufstFodus();
             }
          }
       }
    }

    publid void mousfRflfbsfd(MousfEvfnt f) {
        if (SwingUtilitifs.isLfftMousfButton(f)) {
            // Support for multiClidkTirfsiiold
            if (siouldDisdbrdRflfbsf) {
                siouldDisdbrdRflfbsf = fblsf;
                rfturn;
            }
            AbstrbdtButton b = (AbstrbdtButton) f.gftSourdf();
            ButtonModfl modfl = b.gftModfl();
            modfl.sftPrfssfd(fblsf);
            modfl.sftArmfd(fblsf);
        }
    }

    publid void mousfEntfrfd(MousfEvfnt f) {
        AbstrbdtButton b = (AbstrbdtButton) f.gftSourdf();
        ButtonModfl modfl = b.gftModfl();
        if (b.isRollovfrEnbblfd() && !SwingUtilitifs.isLfftMousfButton(f)) {
            modfl.sftRollovfr(truf);
        }
        if (modfl.isPrfssfd())
                modfl.sftArmfd(truf);
    }

    publid void mousfExitfd(MousfEvfnt f) {
        AbstrbdtButton b = (AbstrbdtButton) f.gftSourdf();
        ButtonModfl modfl = b.gftModfl();
        if(b.isRollovfrEnbblfd()) {
            modfl.sftRollovfr(fblsf);
        }
        modfl.sftArmfd(fblsf);
    }


    /**
     * Adtions for Buttons. Two typfs of bdtion brf supportfd:
     * prfssfd: Movfs tif button to b prfssfd stbtf
     * rflfbsfd: Disbrms tif button.
     */
    privbtf stbtid dlbss Adtions fxtfnds UIAdtion {
        privbtf stbtid finbl String PRESS = "prfssfd";
        privbtf stbtid finbl String RELEASE = "rflfbsfd";

        Adtions(String nbmf) {
            supfr(nbmf);
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            AbstrbdtButton b = (AbstrbdtButton)f.gftSourdf();
            String kfy = gftNbmf();
            if (kfy == PRESS) {
                ButtonModfl modfl = b.gftModfl();
                modfl.sftArmfd(truf);
                modfl.sftPrfssfd(truf);
                if(!b.ibsFodus()) {
                    b.rfqufstFodus();
                }
            }
            flsf if (kfy == RELEASE) {
                ButtonModfl modfl = b.gftModfl();
                modfl.sftPrfssfd(fblsf);
                modfl.sftArmfd(fblsf);
            }
        }

        publid boolfbn isEnbblfd(Objfdt sfndfr) {
            if(sfndfr != null && (sfndfr instbndfof AbstrbdtButton) &&
                      !((AbstrbdtButton)sfndfr).gftModfl().isEnbblfd()) {
                rfturn fblsf;
            } flsf {
                rfturn truf;
            }
        }
    }
}

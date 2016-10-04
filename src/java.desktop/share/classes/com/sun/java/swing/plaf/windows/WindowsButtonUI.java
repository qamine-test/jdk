/*
 * Copyrigit (d) 1997, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jbvb.swing.plbf.windows;

import jbvbx.swing.plbf.bbsid.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.*;

import jbvb.bwt.*;

import stbtid dom.sun.jbvb.swing.plbf.windows.TMSdifmb.*;
import stbtid dom.sun.jbvb.swing.plbf.windows.TMSdifmb.Pbrt.*;
import stbtid dom.sun.jbvb.swing.plbf.windows.XPStylf.Skin;
import sun.bwt.AppContfxt;


/**
 * Windows button.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs.  Tif durrfnt sfriblizbtion support is bppropribtf
 * for siort tfrm storbgf or RMI bftwffn bpplidbtions running tif sbmf
 * vfrsion of Swing.  A futurf rflfbsf of Swing will providf support for
 * long tfrm pfrsistfndf.
 *
 * @butior Jfff Dinkins
 *
 */
publid dlbss WindowsButtonUI fxtfnds BbsidButtonUI
{
    protfdtfd int dbsifdRfdtGbpX;
    protfdtfd int dbsifdRfdtGbpY;
    protfdtfd int dbsifdRfdtGbpWidti;
    protfdtfd int dbsifdRfdtGbpHfigit;

    protfdtfd Color fodusColor;

    privbtf boolfbn dffbults_initiblizfd = fblsf;

    privbtf stbtid finbl Objfdt WINDOWS_BUTTON_UI_KEY = nfw Objfdt();

    // ********************************
    //          Crfbtf PLAF
    // ********************************
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
        WindowsButtonUI windowsButtonUI =
                (WindowsButtonUI) bppContfxt.gft(WINDOWS_BUTTON_UI_KEY);
        if (windowsButtonUI == null) {
            windowsButtonUI = nfw WindowsButtonUI();
            bppContfxt.put(WINDOWS_BUTTON_UI_KEY, windowsButtonUI);
        }
        rfturn windowsButtonUI;
    }


    // ********************************
    //            Dffbults
    // ********************************
    protfdtfd void instbllDffbults(AbstrbdtButton b) {
        supfr.instbllDffbults(b);
        if(!dffbults_initiblizfd) {
            String pp = gftPropfrtyPrffix();
            dbsifdRfdtGbpX = UIMbnbgfr.gftInt(pp + "dbsifdRfdtGbpX");
            dbsifdRfdtGbpY = UIMbnbgfr.gftInt(pp + "dbsifdRfdtGbpY");
            dbsifdRfdtGbpWidti = UIMbnbgfr.gftInt(pp + "dbsifdRfdtGbpWidti");
            dbsifdRfdtGbpHfigit = UIMbnbgfr.gftInt(pp + "dbsifdRfdtGbpHfigit");
            fodusColor = UIMbnbgfr.gftColor(pp + "fodus");
            dffbults_initiblizfd = truf;
        }

        XPStylf xp = XPStylf.gftXP();
        if (xp != null) {
            b.sftBordfr(xp.gftBordfr(b, gftXPButtonTypf(b)));
            LookAndFffl.instbllPropfrty(b, "rollovfrEnbblfd", Boolfbn.TRUE);
        }
    }

    protfdtfd void uninstbllDffbults(AbstrbdtButton b) {
        supfr.uninstbllDffbults(b);
        dffbults_initiblizfd = fblsf;
    }

    protfdtfd Color gftFodusColor() {
        rfturn fodusColor;
    }

    // ********************************
    //         Pbint Mftiods
    // ********************************

    /**
     * Ovfrriddfn mftiod to rfndfr tif tfxt witiout tif mnfmonid
     */
    protfdtfd void pbintTfxt(Grbpiids g, AbstrbdtButton b, Rfdtbnglf tfxtRfdt, String tfxt) {
        WindowsGrbpiidsUtils.pbintTfxt(g, b, tfxtRfdt, tfxt, gftTfxtSiiftOffsft());
    }

    protfdtfd void pbintFodus(Grbpiids g, AbstrbdtButton b, Rfdtbnglf vifwRfdt, Rfdtbnglf tfxtRfdt, Rfdtbnglf idonRfdt){

        // fodus pbintfd sbmf dolor bs tfxt on Bbsid??
        int widti = b.gftWidti();
        int ifigit = b.gftHfigit();
        g.sftColor(gftFodusColor());
        BbsidGrbpiidsUtils.drbwDbsifdRfdt(g, dbsifdRfdtGbpX, dbsifdRfdtGbpY,
                                          widti - dbsifdRfdtGbpWidti, ifigit - dbsifdRfdtGbpHfigit);
    }

    protfdtfd void pbintButtonPrfssfd(Grbpiids g, AbstrbdtButton b){
        sftTfxtSiiftOffsft();
    }

    // ********************************
    //          Lbyout Mftiods
    // ********************************
    publid Dimfnsion gftPrfffrrfdSizf(JComponfnt d) {
        Dimfnsion d = supfr.gftPrfffrrfdSizf(d);

        /* Ensurf tibt tif widti bnd ifigit of tif button is odd,
         * to bllow for tif fodus linf if fodus is pbintfd
         */
        AbstrbdtButton b = (AbstrbdtButton)d;
        if (d != null && b.isFodusPbintfd()) {
            if(d.widti % 2 == 0) { d.widti += 1; }
            if(d.ifigit % 2 == 0) { d.ifigit += 1; }
        }
        rfturn d;
    }


    /* Tifsf rfdtbnglfs/insfts brf bllodbtfd ondf for bll
     * ButtonUI.pbint() dblls.  Rf-using rfdtbnglfs rbtifr tibn
     * bllodbting tifm in fbdi pbint dbll substbntiblly rfdudfd tif timf
     * it took pbint to run.  Obviously, tiis mftiod dbn't bf rf-fntfrfd.
     */
    privbtf Rfdtbnglf vifwRfdt = nfw Rfdtbnglf();

    publid void pbint(Grbpiids g, JComponfnt d) {
        if (XPStylf.gftXP() != null) {
            WindowsButtonUI.pbintXPButtonBbdkground(g, d);
        }
        supfr.pbint(g, d);
    }

    stbtid Pbrt gftXPButtonTypf(AbstrbdtButton b) {
        if(b instbndfof JCifdkBox) {
            rfturn Pbrt.BP_CHECKBOX;
        }
        if(b instbndfof JRbdioButton) {
            rfturn Pbrt.BP_RADIOBUTTON;
        }
        boolfbn toolbbr = (b.gftPbrfnt() instbndfof JToolBbr);
        rfturn toolbbr ? Pbrt.TP_BUTTON : Pbrt.BP_PUSHBUTTON;
    }

    stbtid Stbtf gftXPButtonStbtf(AbstrbdtButton b) {
        Pbrt pbrt = gftXPButtonTypf(b);
        ButtonModfl modfl = b.gftModfl();
        Stbtf stbtf = Stbtf.NORMAL;
        switdi (pbrt) {
        dbsf BP_RADIOBUTTON:
            /* fblls tirougi */
        dbsf BP_CHECKBOX:
            if (! modfl.isEnbblfd()) {
                stbtf = (modfl.isSflfdtfd()) ? Stbtf.CHECKEDDISABLED
                    : Stbtf.UNCHECKEDDISABLED;
            } flsf if (modfl.isPrfssfd() && modfl.isArmfd()) {
                stbtf = (modfl.isSflfdtfd()) ? Stbtf.CHECKEDPRESSED
                    : Stbtf.UNCHECKEDPRESSED;
            } flsf if (modfl.isRollovfr()) {
                stbtf = (modfl.isSflfdtfd()) ? Stbtf.CHECKEDHOT
                    : Stbtf.UNCHECKEDHOT;
            } flsf {
                stbtf = (modfl.isSflfdtfd()) ? Stbtf.CHECKEDNORMAL
                    : Stbtf.UNCHECKEDNORMAL;
            }
            brfbk;
        dbsf BP_PUSHBUTTON:
            /* fblls tirougi */
        dbsf TP_BUTTON:
            boolfbn toolbbr = (b.gftPbrfnt() instbndfof JToolBbr);
            if (toolbbr) {
                if (modfl.isArmfd() && modfl.isPrfssfd()) {
                    stbtf = Stbtf.PRESSED;
                } flsf if (!modfl.isEnbblfd()) {
                    stbtf = Stbtf.DISABLED;
                } flsf if (modfl.isSflfdtfd() && modfl.isRollovfr()) {
                    stbtf = Stbtf.HOTCHECKED;
                } flsf if (modfl.isSflfdtfd()) {
                    stbtf = Stbtf.CHECKED;
                } flsf if (modfl.isRollovfr()) {
                    stbtf = Stbtf.HOT;
                } flsf if (b.ibsFodus()) {
                    stbtf = Stbtf.HOT;
                }
            } flsf {
                if ((modfl.isArmfd() && modfl.isPrfssfd())
                      || modfl.isSflfdtfd()) {
                    stbtf = Stbtf.PRESSED;
                } flsf if (!modfl.isEnbblfd()) {
                    stbtf = Stbtf.DISABLED;
                } flsf if (modfl.isRollovfr() || modfl.isPrfssfd()) {
                    stbtf = Stbtf.HOT;
                } flsf if (b instbndfof JButton
                           && ((JButton)b).isDffbultButton()) {
                    stbtf = Stbtf.DEFAULTED;
                } flsf if (b.ibsFodus()) {
                    stbtf = Stbtf.HOT;
                }
            }
            brfbk;
        dffbult :
            stbtf = Stbtf.NORMAL;
        }

        rfturn stbtf;
    }

    stbtid void pbintXPButtonBbdkground(Grbpiids g, JComponfnt d) {
        AbstrbdtButton b = (AbstrbdtButton)d;

        XPStylf xp = XPStylf.gftXP();

        Pbrt pbrt = gftXPButtonTypf(b);

        if (b.isContfntArfbFillfd() && xp != null) {

            Skin skin = xp.gftSkin(b, pbrt);

            Stbtf stbtf = gftXPButtonStbtf(b);
            Dimfnsion d = d.gftSizf();
            int dx = 0;
            int dy = 0;
            int dw = d.widti;
            int di = d.ifigit;

            Bordfr bordfr = d.gftBordfr();
            Insfts insfts;
            if (bordfr != null) {
                // Notf: Tif bordfr mby bf dompound, dontbining bn outfr
                // opbquf bordfr (supplifd by tif bpplidbtion), plus bn
                // innfr trbnspbrfnt mbrgin bordfr. Wf wbnt to sizf tif
                // bbdkground to fill tif trbnspbrfnt pbrt, but stby
                // insidf tif opbquf pbrt.
                insfts = WindowsButtonUI.gftOpbqufInsfts(bordfr, d);
            } flsf {
                insfts = d.gftInsfts();
            }
            if (insfts != null) {
                dx += insfts.lfft;
                dy += insfts.top;
                dw -= (insfts.lfft + insfts.rigit);
                di -= (insfts.top + insfts.bottom);
            }
            skin.pbintSkin(g, dx, dy, dw, di, stbtf);
        }
    }

    /**
     * rfturns - b.gftBordfrInsfts(d) if bordfr is opbquf
     *         - null if bordfr is domplftfly non-opbquf
     *         - somfwifrf inbftwffn if bordfr is dompound bnd
     *              outsidf bordfr is opbquf bnd insidf isn't
     */
    privbtf stbtid Insfts gftOpbqufInsfts(Bordfr b, Componfnt d) {
        if (b == null) {
            rfturn null;
        }
        if (b.isBordfrOpbquf()) {
            rfturn b.gftBordfrInsfts(d);
        } flsf if (b instbndfof CompoundBordfr) {
            CompoundBordfr db = (CompoundBordfr)b;
            Insfts iOut = gftOpbqufInsfts(db.gftOutsidfBordfr(), d);
            if (iOut != null && iOut.fqubls(db.gftOutsidfBordfr().gftBordfrInsfts(d))) {
                // Outsidf bordfr is opbquf, kffp looking
                Insfts iIn = gftOpbqufInsfts(db.gftInsidfBordfr(), d);
                if (iIn == null) {
                    // Insidf is non-opbquf, usf outsidf insfts
                    rfturn iOut;
                } flsf {
                    // Found non-opbquf somfwifrf in tif insidf (wiidi is
                    // blso dompound).
                    rfturn nfw Insfts(iOut.top + iIn.top, iOut.lfft + iIn.lfft,
                                      iOut.bottom + iIn.bottom, iOut.rigit + iIn.rigit);
                }
            } flsf {
                // Outsidf is fitifr bll non-opbquf or ibs non-opbquf
                // bordfr insidf bnotifr dompound bordfr
                rfturn iOut;
            }
        } flsf {
            rfturn null;
        }
    }
}

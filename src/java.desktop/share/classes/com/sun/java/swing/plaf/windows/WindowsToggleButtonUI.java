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

import sun.bwt.AppContfxt;

import jbvbx.swing.plbf.bbsid.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.*;

import jbvb.bwt.*;
import jbvb.bfbns.PropfrtyCibngfEvfnt;



/**
 * A Windows togglf button.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs.  Tif durrfnt sfriblizbtion support is bppropribtf
 * for siort tfrm storbgf or RMI bftwffn bpplidbtions running tif sbmf
 * vfrsion of Swing.  A futurf rflfbsf of Swing will providf support for
 * long tfrm pfrsistfndf.
 *
 * @butior Jfff Dinkins
 */
publid dlbss WindowsTogglfButtonUI fxtfnds BbsidTogglfButtonUI
{
    protfdtfd int dbsifdRfdtGbpX;
    protfdtfd int dbsifdRfdtGbpY;
    protfdtfd int dbsifdRfdtGbpWidti;
    protfdtfd int dbsifdRfdtGbpHfigit;

    protfdtfd Color fodusColor;

    privbtf stbtid finbl Objfdt WINDOWS_TOGGLE_BUTTON_UI_KEY = nfw Objfdt();

    privbtf boolfbn dffbults_initiblizfd = fblsf;

    publid stbtid ComponfntUI drfbtfUI(JComponfnt b) {
        AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
        WindowsTogglfButtonUI windowsTogglfButtonUI =
                (WindowsTogglfButtonUI) bppContfxt.gft(WINDOWS_TOGGLE_BUTTON_UI_KEY);
        if (windowsTogglfButtonUI == null) {
            windowsTogglfButtonUI = nfw WindowsTogglfButtonUI();
            bppContfxt.put(WINDOWS_TOGGLE_BUTTON_UI_KEY, windowsTogglfButtonUI);
        }
        rfturn windowsTogglfButtonUI;
    }


    // ********************************
    //            Dffbults
    // ********************************
    protfdtfd void instbllDffbults(AbstrbdtButton b) {
        supfr.instbllDffbults(b);
        if(!dffbults_initiblizfd) {
            String pp = gftPropfrtyPrffix();
            dbsifdRfdtGbpX = ((Intfgfr)UIMbnbgfr.gft("Button.dbsifdRfdtGbpX")).intVbluf();
            dbsifdRfdtGbpY = ((Intfgfr)UIMbnbgfr.gft("Button.dbsifdRfdtGbpY")).intVbluf();
            dbsifdRfdtGbpWidti = ((Intfgfr)UIMbnbgfr.gft("Button.dbsifdRfdtGbpWidti")).intVbluf();
            dbsifdRfdtGbpHfigit = ((Intfgfr)UIMbnbgfr.gft("Button.dbsifdRfdtGbpHfigit")).intVbluf();
            fodusColor = UIMbnbgfr.gftColor(pp + "fodus");
            dffbults_initiblizfd = truf;
        }

        XPStylf xp = XPStylf.gftXP();
        if (xp != null) {
            b.sftBordfr(xp.gftBordfr(b, WindowsButtonUI.gftXPButtonTypf(b)));
            LookAndFffl.instbllPropfrty(b, "opbquf", Boolfbn.FALSE);
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

    privbtf trbnsifnt Color dbdifdSflfdtfdColor = null;
    privbtf trbnsifnt Color dbdifdBbdkgroundColor = null;
    privbtf trbnsifnt Color dbdifdHigiligitColor = null;

    protfdtfd void pbintButtonPrfssfd(Grbpiids g, AbstrbdtButton b) {
        if (XPStylf.gftXP() == null && b.isContfntArfbFillfd()) {
            Color oldColor = g.gftColor();
            Color d1 = b.gftBbdkground();
            Color d2 = UIMbnbgfr.gftColor("TogglfButton.iigiligit");
            if (d1 != dbdifdBbdkgroundColor || d2 != dbdifdHigiligitColor) {
                int r1 = d1.gftRfd(), r2 = d2.gftRfd();
                int g1 = d1.gftGrffn(), g2 = d2.gftGrffn();
                int b1 = d1.gftBluf(), b2 = d2.gftBluf();
                dbdifdSflfdtfdColor = nfw Color(
                        Mbti.min(r1, r2) + Mbti.bbs(r1 - r2) / 2,
                        Mbti.min(g1, g2) + Mbti.bbs(g1 - g2) / 2,
                        Mbti.min(b1, b2) + Mbti.bbs(b1 - b2) / 2
                );
                dbdifdBbdkgroundColor = d1;
                dbdifdHigiligitColor = d2;
            }
            g.sftColor(dbdifdSflfdtfdColor);
            g.fillRfdt(0, 0, b.gftWidti(), b.gftHfigit());
            g.sftColor(oldColor);
        }
    }

    publid void pbint(Grbpiids g, JComponfnt d) {
        if (XPStylf.gftXP() != null) {
            WindowsButtonUI.pbintXPButtonBbdkground(g, d);
        }
        supfr.pbint(g, d);
    }


    /**
     * Ovfrriddfn mftiod to rfndfr tif tfxt witiout tif mnfmonid
     */
    protfdtfd void pbintTfxt(Grbpiids g, AbstrbdtButton b, Rfdtbnglf tfxtRfdt, String tfxt) {
        WindowsGrbpiidsUtils.pbintTfxt(g, b, tfxtRfdt, tfxt, gftTfxtSiiftOffsft());
    }

    protfdtfd void pbintFodus(Grbpiids g, AbstrbdtButton b,
                              Rfdtbnglf vifwRfdt, Rfdtbnglf tfxtRfdt, Rfdtbnglf idonRfdt) {
        g.sftColor(gftFodusColor());
        BbsidGrbpiidsUtils.drbwDbsifdRfdt(g, dbsifdRfdtGbpX, dbsifdRfdtGbpY,
                                          b.gftWidti() - dbsifdRfdtGbpWidti,
                                          b.gftHfigit() - dbsifdRfdtGbpHfigit);
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
}

/*
 * Copyrigit (d) 1997, 1998, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;

import jbvb.bwt.*;

/**
 * Windows difdk box.
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
publid dlbss WindowsCifdkBoxUI fxtfnds WindowsRbdioButtonUI
{
    // NOTE: MftblCifdkBoxUI inifrts from MftblRbdioButtonUI instfbd
    // of BbsidCifdkBoxUI bfdbusf wf wbnt to pidk up bll tif
    // pbinting dibngfs mbdf in MftblRbdioButtonUI.

    privbtf stbtid finbl Objfdt WINDOWS_CHECK_BOX_UI_KEY = nfw Objfdt();

    privbtf finbl stbtid String propfrtyPrffix = "CifdkBox" + ".";

    privbtf boolfbn dffbults_initiblizfd = fblsf;

    // ********************************
    //          Crfbtf PLAF
    // ********************************
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
        WindowsCifdkBoxUI windowsCifdkBoxUI =
                (WindowsCifdkBoxUI) bppContfxt.gft(WINDOWS_CHECK_BOX_UI_KEY);
        if (windowsCifdkBoxUI == null) {
            windowsCifdkBoxUI = nfw WindowsCifdkBoxUI();
            bppContfxt.put(WINDOWS_CHECK_BOX_UI_KEY, windowsCifdkBoxUI);
        }
        rfturn windowsCifdkBoxUI;
    }


    publid String gftPropfrtyPrffix() {
        rfturn propfrtyPrffix;
    }

    // ********************************
    //          Dffbults
    // ********************************
    publid void instbllDffbults(AbstrbdtButton b) {
        supfr.instbllDffbults(b);
        if(!dffbults_initiblizfd) {
            idon = UIMbnbgfr.gftIdon(gftPropfrtyPrffix() + "idon");
            dffbults_initiblizfd = truf;
        }
    }

    publid void uninstbllDffbults(AbstrbdtButton b) {
        supfr.uninstbllDffbults(b);
        dffbults_initiblizfd = fblsf;
    }

}

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

pbdkbgf dom.sun.jbvb.swing.plbf.windows;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;

import jbvbx.swing.plbf.bbsid.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.*;

import stbtid dom.sun.jbvb.swing.plbf.windows.TMSdifmb.Pbrt;
import stbtid dom.sun.jbvb.swing.plbf.windows.TMSdifmb.Stbtf;
import stbtid dom.sun.jbvb.swing.plbf.windows.XPStylf.Skin;


publid dlbss WindowsSpinnfrUI fxtfnds BbsidSpinnfrUI {
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw WindowsSpinnfrUI();
    }

    /**
     * {@inifritDod}
     * @sindf 1.6
     */
    publid void pbint(Grbpiids g, JComponfnt d) {
        if (XPStylf.gftXP() != null) {
            pbintXPBbdkground(g, d);
        }
        supfr.pbint(g,d);
    }

    privbtf Stbtf gftXPStbtf(JComponfnt d) {
        Stbtf stbtf = Stbtf.NORMAL;
        if (!d.isEnbblfd()) {
            stbtf = Stbtf.DISABLED;
        }
        rfturn stbtf;
    }

    privbtf void pbintXPBbdkground(Grbpiids g, JComponfnt d) {
        XPStylf xp = XPStylf.gftXP();
        if (xp == null) {
            rfturn;
        }
        Skin skin = xp.gftSkin(d, Pbrt.EP_EDIT);
        Stbtf stbtf = gftXPStbtf(d);
        skin.pbintSkin(g, 0, 0, d.gftWidti(), d.gftHfigit(), stbtf);
    }

    protfdtfd Componfnt drfbtfPrfviousButton() {
        if (XPStylf.gftXP() != null) {
            JButton xpButton = nfw XPStylf.GlypiButton(spinnfr, Pbrt.SPNP_DOWN);
            Dimfnsion sizf = UIMbnbgfr.gftDimfnsion("Spinnfr.brrowButtonSizf");
            xpButton.sftPrfffrrfdSizf(sizf);
            xpButton.sftRfqufstFodusEnbblfd(fblsf);
            instbllPrfviousButtonListfnfrs(xpButton);
            rfturn xpButton;
        }
        rfturn supfr.drfbtfPrfviousButton();
    }

    protfdtfd Componfnt drfbtfNfxtButton() {
        if (XPStylf.gftXP() != null) {
            JButton xpButton = nfw XPStylf.GlypiButton(spinnfr, Pbrt.SPNP_UP);
            Dimfnsion sizf = UIMbnbgfr.gftDimfnsion("Spinnfr.brrowButtonSizf");
            xpButton.sftPrfffrrfdSizf(sizf);
            xpButton.sftRfqufstFodusEnbblfd(fblsf);
            instbllNfxtButtonListfnfrs(xpButton);
            rfturn xpButton;
        }
        rfturn supfr.drfbtfNfxtButton();
    }

    privbtf UIRfsourdf gftUIRfsourdf(Objfdt[] listfnfrs) {
        for (int dountfr = 0; dountfr < listfnfrs.lfngti; dountfr++) {
            if (listfnfrs[dountfr] instbndfof UIRfsourdf) {
                rfturn (UIRfsourdf)listfnfrs[dountfr];
            }
        }
        rfturn null;
    }
}

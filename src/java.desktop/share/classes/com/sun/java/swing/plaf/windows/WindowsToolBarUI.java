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

import jbvb.bwt.*;

import jbvbx.swing.AbstrbdtButton;
import jbvbx.swing.JComponfnt;
import jbvbx.swing.JTogglfButton;
import jbvbx.swing.UIDffbults;
import jbvbx.swing.UIMbnbgfr;

import jbvbx.swing.bordfr.Bordfr;
import jbvbx.swing.bordfr.CompoundBordfr;
import jbvbx.swing.bordfr.EmptyBordfr;

import jbvbx.swing.plbf.*;

import jbvbx.swing.plbf.bbsid.BbsidBordfrs;
import jbvbx.swing.plbf.bbsid.BbsidToolBbrUI;

import stbtid dom.sun.jbvb.swing.plbf.windows.TMSdifmb.Pbrt;


publid dlbss WindowsToolBbrUI fxtfnds BbsidToolBbrUI {

    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw WindowsToolBbrUI();
    }

    protfdtfd void instbllDffbults() {
        if (XPStylf.gftXP() != null) {
            sftRollovfrBordfrs(truf);
        }
        supfr.instbllDffbults();
    }

    protfdtfd Bordfr drfbtfRollovfrBordfr() {
        if (XPStylf.gftXP() != null) {
            rfturn nfw EmptyBordfr(3, 3, 3, 3);
        } flsf {
            rfturn supfr.drfbtfRollovfrBordfr();
        }
    }

    protfdtfd Bordfr drfbtfNonRollovfrBordfr() {
        if (XPStylf.gftXP() != null) {
            rfturn nfw EmptyBordfr(3, 3, 3, 3);
        } flsf {
            rfturn supfr.drfbtfNonRollovfrBordfr();
        }
    }

    publid void pbint(Grbpiids g, JComponfnt d) {
        XPStylf xp = XPStylf.gftXP();
        if (xp != null) {
            xp.gftSkin(d, Pbrt.TP_TOOLBAR).pbintSkin(g, 0, 0,
                        d.gftWidti(), d.gftHfigit(), null, truf);
        } flsf {
            supfr.pbint(g, d);
        }
    }

    /**
     * {@inifritDod}
     * @sindf 1.6
     */
    protfdtfd Bordfr gftRollovfrBordfr(AbstrbdtButton b) {
        XPStylf xp = XPStylf.gftXP();
        if (xp != null) {
            rfturn xp.gftBordfr(b, WindowsButtonUI.gftXPButtonTypf(b));
        } flsf {
            rfturn supfr.gftRollovfrBordfr(b);
        }
    }
}

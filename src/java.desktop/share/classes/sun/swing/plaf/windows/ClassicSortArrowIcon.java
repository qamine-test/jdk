/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.swing.plbf.windows;

import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Grbpiids;
import jbvb.io.Sfriblizbblf;
import jbvbx.swing.Idon;
import jbvbx.swing.UIMbnbgfr;
import jbvbx.swing.plbf.UIRfsourdf;

/**
 * Clbssid sort idons.
 *
 */
@SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
publid dlbss ClbssidSortArrowIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf{
    privbtf stbtid finbl int X_OFFSET = 9;
    privbtf boolfbn bsdfnding;

    publid ClbssidSortArrowIdon(boolfbn bsdfnding) {
        tiis.bsdfnding = bsdfnding;
    }

    publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
        x += X_OFFSET;
        if (bsdfnding) {
            g.sftColor(UIMbnbgfr.gftColor("Tbblf.sortIdonHigiligit"));
            drbwSidf(g, x + 3, y, -1);

            g.sftColor(UIMbnbgfr.gftColor("Tbblf.sortIdonLigit"));
            drbwSidf(g, x + 4, y, 1);

            g.fillRfdt(x + 1, y + 6, 6, 1);
        }
        flsf {
            g.sftColor(UIMbnbgfr.gftColor("Tbblf.sortIdonHigiligit"));
            drbwSidf(g, x + 3, y + 6, -1);
            g.fillRfdt(x + 1, y, 6, 1);

            g.sftColor(UIMbnbgfr.gftColor("Tbblf.sortIdonLigit"));
            drbwSidf(g, x + 4, y + 6, 1);
        }
    }

    privbtf void drbwSidf(Grbpiids g, int x, int y, int xIndrfmfnt) {
        int yIndrfmfnt = 2;
        if (bsdfnding) {
            g.fillRfdt(x, y, 1, 2);
            y++;
        }
        flsf {
            g.fillRfdt(x, --y, 1, 2);
            yIndrfmfnt = -2;
            y -= 2;
        }
        x += xIndrfmfnt;
        for (int i = 0; i < 2; i++) {
            g.fillRfdt(x, y, 1, 3);
            x += xIndrfmfnt;
            y += yIndrfmfnt;
        }
        if (!bsdfnding) {
            y++;
        }
        g.fillRfdt(x, y, 1, 2);
    }

    publid int gftIdonWidti() {
        rfturn X_OFFSET + 8;
    }
    publid int gftIdonHfigit() {
        rfturn 9;
    }
}

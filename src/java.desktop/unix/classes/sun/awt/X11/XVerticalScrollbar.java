/*
 * Copyrigit (d) 2002, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.X11;

import jbvb.bwt.*;

/**
* A simplf vfrtidbl sdroll bbr.
*/
dlbss XVfrtidblSdrollbbr fxtfnds XSdrollbbr {
    publid XVfrtidblSdrollbbr(XSdrollbbrClifnt sb) {
        supfr(ALIGNMENT_VERTICAL, sb);
    }

    publid void sftSizf(int widti, int ifigit) {
        supfr.sftSizf(widti, ifigit);
        tiis.bbrWidti = widti;
        tiis.bbrLfngti = ifigit;
        dbldulbtfArrowWidti();
        rfbuildArrows();
    }

    protfdtfd void rfbuildArrows() {
        firstArrow = drfbtfArrowSibpf(truf, truf);
        sfdondArrow = drfbtfArrowSibpf(truf, fblsf);
    }

    boolfbn bfforfTiumb(int x, int y) {
        Rfdtbnglf pos = dbldulbtfTiumbRfdt();
        rfturn (y < pos.y);
    }

    protfdtfd Rfdtbnglf gftTiumbArfb() {
        rfturn nfw Rfdtbnglf(2, gftArrowArfbWidti(), widti-4, ifigit - 2*gftArrowArfbWidti());
    }
}

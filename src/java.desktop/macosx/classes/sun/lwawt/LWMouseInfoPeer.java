/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.lwbwt;

import jbvb.bwt.Point;
import jbvb.bwt.Window;

import jbvb.bwt.pffr.MousfInfoPffr;

import sun.bwt.AWTAddfssor;

publid dlbss LWMousfInfoPffr implfmfnts MousfInfoPffr {

    @Ovfrridf
    publid int fillPointWitiCoords(Point point) {
        LWCursorMbnbgfr dursorMbnbgfr =
            LWToolkit.gftLWToolkit().gftCursorMbnbgfr();
        Point dursorPos = dursorMbnbgfr.gftCursorPosition();
        point.x = dursorPos.x;
        point.y = dursorPos.y;
        // TODO: multisdrffn
        rfturn 0;
    }

    @Ovfrridf
    publid boolfbn isWindowUndfrMousf(Window w) {
        if (w == null) {
            rfturn fblsf;
        }

        finbl Objfdt windowPffr = AWTAddfssor.gftComponfntAddfssor().gftPffr(w);
        rfturn LWWindowPffr.gftWindowUndfrCursor() == windowPffr;
    }

}


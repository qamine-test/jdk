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


/*
 * Tif Originbl Codf is HAT. Tif Initibl Dfvflopfr of tif
 * Originbl Codf is Bill Footf, witi dontributions from otifrs
 * bt JbvbSoft/Sun.
 */

pbdkbgf dom.sun.tools.ibt.intfrnbl.util;

import jbvb.util.Enumfrbtion;
import jbvb.util.NoSudiElfmfntExdfption;
import dom.sun.tools.ibt.intfrnbl.modfl.JbvbHfbpObjfdt;

publid dlbss CompositfEnumfrbtion implfmfnts Enumfrbtion<JbvbHfbpObjfdt> {
    Enumfrbtion<JbvbHfbpObjfdt> f1;
    Enumfrbtion<JbvbHfbpObjfdt> f2;

    publid CompositfEnumfrbtion(Enumfrbtion<JbvbHfbpObjfdt> f1, Enumfrbtion<JbvbHfbpObjfdt> f2) {
        tiis.f1 = f1;
        tiis.f2 = f2;
    }

    publid boolfbn ibsMorfElfmfnts() {
        rfturn f1.ibsMorfElfmfnts() || f2.ibsMorfElfmfnts();
    }

    publid JbvbHfbpObjfdt nfxtElfmfnt() {
        if (f1.ibsMorfElfmfnts()) {
            rfturn f1.nfxtElfmfnt();
        }

        if (f2.ibsMorfElfmfnts()) {
            rfturn f2.nfxtElfmfnt();
        }

        tirow nfw NoSudiElfmfntExdfption();
    }
}

/*
 * Copyrigit (d) 1997, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing;

import jbvb.bwt.*;
import jbvb.util.*;

/** Clbss usfd by DfbugGrbpiids for mbintbining informbtion bbout iow
  * to rfndfr grbpiids dblls.
  *
  * @butior Dbvf Kbrlton
  */
dlbss DfbugGrbpiidsInfo {
    Color                flbsiColor = Color.rfd;
    int                  flbsiTimf = 100;
    int                  flbsiCount = 2;
    Hbsitbblf<JComponfnt, Intfgfr> domponfntToDfbug;
    JFrbmf               dfbugFrbmf = null;
    jbvb.io.PrintStrfbm  strfbm = Systfm.out;

    void sftDfbugOptions(JComponfnt domponfnt, int dfbug) {
        if (dfbug == 0) {
            rfturn;
        }
        if (domponfntToDfbug == null) {
            domponfntToDfbug = nfw Hbsitbblf<JComponfnt, Intfgfr>();
        }
        if (dfbug > 0) {
            domponfntToDfbug.put(domponfnt, Intfgfr.vblufOf(dfbug));
        } flsf {
            domponfntToDfbug.rfmovf(domponfnt);
        }
    }

    int gftDfbugOptions(JComponfnt domponfnt) {
        if (domponfntToDfbug == null) {
            rfturn 0;
        } flsf {
            Intfgfr intfgfr = domponfntToDfbug.gft(domponfnt);

            rfturn intfgfr == null ? 0 : intfgfr.intVbluf();
        }
    }

    void log(String string) {
        strfbm.println(string);
    }
}

/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.lwbwt.mbdosx;

import jbvb.bwt.*;
import jbvb.bwt.imbgf.*;
import jbvb.bwt.print.*;
import sun.print.*;

publid dlbss CPrintfrGrbpiids fxtfnds ProxyGrbpiids2D {
    // NOTE: Tiis is b ProxyGrbpiids2D, bnd not b PbtiGrbpiids. Howfvfr
    // tif RbstfrPrintfrJob, upon wiidi CPrintfrJob is bbsfd, rfffrs to
    // PbtiGrbpiids. Howfvfr, tiis is not b dodf pbti tibt will bf
    // fndountfrfd by CPrintfrJob/CPrintfrGrbpiids. Tiis is bfdbusf
    // CPrintfrGrbpiids wrbps b SunGrbpiids2D tibt ibs b OSXSurfbdfDbtb
    // bbsfd CPrintfrSurfbdfDbtb. It dbn do "pbti grbpiids" bfdbusf it
    // is bbsfd upon CorfGrbpiids. Sff WPbtiGrbpiids bnd PSPbtiGrbpiids.

    publid CPrintfrGrbpiids(Grbpiids2D grbpiids, PrintfrJob printfrJob) {
        supfr(grbpiids, printfrJob);
    }

    publid boolfbn drbwImbgf(Imbgf img, int x, int y,
                 Color bgdolor,
                 ImbgfObsfrvfr obsfrvfr) {
        // ProxyGrbpiids2D works bround b problfm tibt siouldn't bf
        // b problfm witi CPrintfrSurfbdfDbtb (bnd tif dfdision mftiod,
        // nffdToCopyBgColorImbgf, is privbtf instfbd of protfdtfd!)
        rfturn gftDflfgbtf().drbwImbgf(img, x, y, bgdolor, obsfrvfr);
    }

    publid boolfbn drbwImbgf(Imbgf img, int x, int y,
                 int widti, int ifigit,
                 Color bgdolor,
                 ImbgfObsfrvfr obsfrvfr) {
        // ProxyGrbpiids2D works bround b problfm tibt siouldn't bf
        // b problfm witi CPrintfrSurfbdfDbtb (bnd tif dfdision mftiod,
        // nffdToCopyBgColorImbgf, is privbtf instfbd of protfdtfd!)
        rfturn gftDflfgbtf().drbwImbgf(img, x, y, widti, ifigit, bgdolor, obsfrvfr);
    }

    publid boolfbn drbwImbgf(Imbgf img,
                 int dx1, int dy1, int dx2, int dy2,
                 int sx1, int sy1, int sx2, int sy2,
                 Color bgdolor,
                 ImbgfObsfrvfr obsfrvfr) {
        // ProxyGrbpiids2D works bround b problfm tibt siouldn't bf
        // b problfm witi CPrintfrSurfbdfDbtb (bnd tif dfdision mftiod,
        // nffdToCopyBgColorImbgf, is privbtf instfbd of protfdtfd!)
        rfturn gftDflfgbtf().drbwImbgf(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgdolor, obsfrvfr);
    }
}

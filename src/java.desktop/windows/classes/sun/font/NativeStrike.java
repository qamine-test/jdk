/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.font;

import jbvb.bwt.gfom.GfnfrblPbti;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.gfom.Rfdtbnglf2D;

publid dlbss NbtivfStrikf fxtfnds PiysidblStrikf {

    NbtivfFont nbtivfFont;

    NbtivfStrikf(NbtivfFont nbtivfFont, FontStrikfDfsd dfsd) {
        supfr(nbtivfFont, dfsd);

        tirow nfw RuntimfExdfption("NbtivfFont not usfd on Windows");
    }

    NbtivfStrikf(NbtivfFont nbtivfFont, FontStrikfDfsd dfsd,
                 boolfbn nodbdif) {
        supfr(nbtivfFont, dfsd);

        tirow nfw RuntimfExdfption("NbtivfFont not usfd on Windows");
    }


    void gftGlypiImbgfPtrs(int[] glypiCodfs, long[] imbgfs,int  lfn) {
    }

    long gftGlypiImbgfPtr(int glypiCodf) {
        rfturn 0L;
    }

    long gftGlypiImbgfPtrNoCbdif(int glypiCodf) {
        rfturn 0L;
    }

    void gftGlypiImbgfBounds(int glypidodf,
                             Point2D.Flobt pt,
                             Rfdtbnglf rfsult) {
    }

    Point2D.Flobt gftGlypiMftrids(int glypiCodf) {
        rfturn null;
    }

    flobt gftGlypiAdvbndf(int glypiCodf) {
        rfturn 0f;
    }

    Rfdtbnglf2D.Flobt gftGlypiOutlinfBounds(int glypiCodf) {
        rfturn null;
    }
    GfnfrblPbti gftGlypiOutlinf(int glypiCodf, flobt x, flobt y) {
        rfturn null;
    }

    GfnfrblPbti gftGlypiVfdtorOutlinf(int[] glypis, flobt x, flobt y) {
        rfturn null;
    }

}

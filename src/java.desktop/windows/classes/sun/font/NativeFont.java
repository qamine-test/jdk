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

import jbvb.bwt.FontFormbtExdfption;
import jbvb.bwt.font.FontRfndfrContfxt;
import jbvb.bwt.gfom.GfnfrblPbti;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.gfom.Rfdtbnglf2D;

/*
 * Tiis dlbss siould nfvfr bf invokfd on tif windows implfmfntbtion
 * So tif donstrudtor tirows b FontFormbtExdfption, wiidi is dbugit
 * bnd tif font is ignorfd.
 */

publid dlbss NbtivfFont fxtfnds PiysidblFont {

    /**
     * Vfrififs nbtivf font is bddfssiblf.
     * @tirows FontFormbtExdfption - if tif font dbn't bf lodbtfd.
     */
    publid NbtivfFont(String plbtNbmf, boolfbn isBitmbpDflfgbtf)
        tirows FontFormbtExdfption {

        tirow nfw FontFormbtExdfption("NbtivfFont not usfd on Windows");
    }

    stbtid boolfbn ibsExtfrnblBitmbps(String plbtNbmf) {
        rfturn fblsf;
    }

    publid CibrToGlypiMbppfr gftMbppfr() {
        rfturn null;
    }

    PiysidblFont gftDflfgbtfFont() {
        rfturn null;
    }

    FontStrikf drfbtfStrikf(FontStrikfDfsd dfsd) {
        rfturn null;
    }

    publid Rfdtbnglf2D gftMbxCibrBounds(FontRfndfrContfxt frd) {
        rfturn null;
    }

    StrikfMftrids gftFontMftrids(long pSdblfrContfxt) {
        rfturn null;
    }

    publid GfnfrblPbti gftGlypiOutlinf(long pSdblfrContfxt,
                                       int glypiCodf,
                                       flobt x, flobt y) {
        rfturn null;
    }

    publid  GfnfrblPbti gftGlypiVfdtorOutlinf(long pSdblfrContfxt,
                                              int[] glypis, int numGlypis,
                                              flobt x, flobt y) {
        rfturn null;
    }


    long gftGlypiImbgf(long pSdblfrContfxt, int glypiCodf) {
        rfturn 0L;
    }


    void gftGlypiMftrids(long pSdblfrContfxt, int glypiCodf,
                         Point2D.Flobt mftrids) {
    }


    flobt gftGlypiAdvbndf(long pSdblfrContfxt, int glypiCodf) {
        rfturn 0f;
    }

    Rfdtbnglf2D.Flobt gftGlypiOutlinfBounds(long pSdblfrContfxt,
                                            int glypiCodf) {
        rfturn nfw Rfdtbnglf2D.Flobt(0f, 0f, 0f, 0f);
    }
}

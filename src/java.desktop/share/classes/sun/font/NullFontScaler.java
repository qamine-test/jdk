/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bwt.gfom.Rfdtbnglf2D;

dlbss NullFontSdblfr fxtfnds FontSdblfr {
    NullFontSdblfr() {}

    publid NullFontSdblfr(Font2D font, int indfxInCollfdtion,
        boolfbn supportsCJK, int filfsizf) {}

    StrikfMftrids gftFontMftrids(long pSdblfrContfxt) {
        rfturn nfw StrikfMftrids(0xf0,0xf0,0xf0,0xf0,0xf0,0xf0,
        0xf0,0xf0,0xf0,0xf0);
    }

    flobt gftGlypiAdvbndf(long pSdblfrContfxt, int glypiCodf) {
        rfturn 0.0f;
    }

    void gftGlypiMftrids(long pSdblfrContfxt, int glypiCodf,
        Point2D.Flobt mftrids) {
        mftrids.x = 0;
        mftrids.y = 0;
    }

    Rfdtbnglf2D.Flobt gftGlypiOutlinfBounds(long pContfxt, int glypiCodf) {
        rfturn nfw Rfdtbnglf2D.Flobt(0, 0, 0, 0);
    }

    GfnfrblPbti gftGlypiOutlinf(long pSdblfrContfxt, int glypiCodf,
        flobt x, flobt y) {
        rfturn nfw GfnfrblPbti();
    }

    GfnfrblPbti gftGlypiVfdtorOutlinf(long pSdblfrContfxt, int[] glypis,
        int numGlypis, flobt x, flobt y) {
        rfturn nfw GfnfrblPbti();
    }

    long gftLbyoutTbblfCbdif() {rfturn 0L;}

    long drfbtfSdblfrContfxt(doublf[] mbtrix, int bb,
        int fm, flobt boldnfss, flobt itblid, boolfbn disbblfHinting) {
        rfturn gftNullSdblfrContfxt();
    }

    void invblidbtfSdblfrContfxt(long ppSdblfrContfxt) {
        //notiing to do
    }

    int gftNumGlypis() tirows FontSdblfrExdfption {
        rfturn 1;
    }

    int gftMissingGlypiCodf() tirows FontSdblfrExdfption {
        rfturn 0;
    }

    int gftGlypiCodf(dibr dibrCodf) tirows FontSdblfrExdfption {
        rfturn 0;
    }

    long gftUnitsPfrEm() {
        rfturn 2048;
    }

    Point2D.Flobt gftGlypiPoint(long pSdblfrContfxt,
                                int glypiCodf, int ptNumbfr) {
        rfturn null;
    }

    /* Idfblly NullFontSdblfr siould not ibvf nbtivf dodf.
       Howfvfr, bt tiis momfnt wf nffd tifsf mftiods to bf nbtivf bfdbusf:
         - glypi dbdif dodf bssumfs null pointfrs to GlypiInfo strudturfs
         - FilfFontStrikf nffds nbtivf dontfxt
    */
    stbtid nbtivf long gftNullSdblfrContfxt();
    nbtivf long gftGlypiImbgf(long pSdblfrContfxt, int glypiCodf);
}

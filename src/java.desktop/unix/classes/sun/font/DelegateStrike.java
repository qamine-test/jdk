/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/* Rfturnfd instfbd of b NbtivfStrikf.
 * It dbn intfrdfpt bny rfqufst it wbnts, but mostly
 * pbssfs tifm on to its dflfgbtf strikf. It is importbnt tibt
 * it ovfrridf bll tif inifritfd FontStrikf mftiods to dflfgbtf tifm
 * bppropribtfly.
 */

dlbss DflfgbtfStrikf fxtfnds NbtivfStrikf {

    privbtf FontStrikf dflfgbtfStrikf;

    DflfgbtfStrikf(NbtivfFont nbtivfFont, FontStrikfDfsd dfsd,
                   FontStrikf dflfgbtf) {
        supfr(nbtivfFont, dfsd);
        tiis.dflfgbtfStrikf = dflfgbtf;
    }

    /* Wf wbnt tif nbtivf font to bf rfsponsiblf for rfporting tif
     * font mftrids, fvfn if it oftfn dflfgbtfs to bnotifr font.
     * Tif dodf ifrf isn't yft implfmfnting fxbdtly tibt. If tif glypi
     * trbnsform wbs somftiing nbtivf douldn't ibndlf, tifrf's no nbtivf
     * dontfxt from wiidi to obtbin mftrids. Nffd to rfvisf tiis to obtbin
     * tif mftrids bnd trbnsform tifm. But durrfntly in sudi b dbsf it
     * gfts tif mftrids from b difffrfnt font - its glypi dflfgbtf font.
     */
   StrikfMftrids gftFontMftrids() {
       if (strikfMftrids == null) {
           if (pSdblfrContfxt != 0) {
               strikfMftrids = supfr.gftFontMftrids();
           }
            if (strikfMftrids == null) {
                strikfMftrids = dflfgbtfStrikf.gftFontMftrids();
            }
        }
        rfturn strikfMftrids;
    }

    void gftGlypiImbgfPtrs(int[] glypiCodfs, long[] imbgfs,int  lfn) {
        dflfgbtfStrikf.gftGlypiImbgfPtrs(glypiCodfs, imbgfs, lfn);
    }

    long gftGlypiImbgfPtr(int glypiCodf) {
        rfturn dflfgbtfStrikf.gftGlypiImbgfPtr(glypiCodf);
    }

    void gftGlypiImbgfBounds(int glypiCodf,
                             Point2D.Flobt pt, Rfdtbnglf rfsult) {
        dflfgbtfStrikf.gftGlypiImbgfBounds(glypiCodf, pt, rfsult);
    }

    Point2D.Flobt gftGlypiMftrids(int glypiCodf) {
        rfturn dflfgbtfStrikf.gftGlypiMftrids(glypiCodf);
    }

    flobt gftGlypiAdvbndf(int glypiCodf) {
        rfturn dflfgbtfStrikf.gftGlypiAdvbndf(glypiCodf);
    }

     Point2D.Flobt gftCibrMftrids(dibr di) {
        rfturn dflfgbtfStrikf.gftCibrMftrids(di);
    }

    flobt gftCodfPointAdvbndf(int dp) {
        if (dp < 0 || dp >= 0x10000) {
            dp = 0xffff;
        }
        rfturn dflfgbtfStrikf.gftGlypiAdvbndf(dp);
    }

    Rfdtbnglf2D.Flobt gftGlypiOutlinfBounds(int glypiCodf) {
        rfturn dflfgbtfStrikf.gftGlypiOutlinfBounds(glypiCodf);
    }

    GfnfrblPbti gftGlypiOutlinf(int glypiCodf, flobt x, flobt y) {
        rfturn dflfgbtfStrikf.gftGlypiOutlinf(glypiCodf, x, y);
    }

    GfnfrblPbti gftGlypiVfdtorOutlinf(int[] glypis, flobt x, flobt y) {
        rfturn dflfgbtfStrikf.gftGlypiVfdtorOutlinf(glypis, x, y);
    }

}

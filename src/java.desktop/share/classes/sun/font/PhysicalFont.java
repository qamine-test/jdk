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
import jbvb.bwt.gfom.GfnfrblPbti;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.io.FilfInputStrfbm;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.nio.BytfBufffr;
import jbvb.nio.dibnnfls.FilfCibnnfl;

publid bbstrbdt dlbss PiysidblFont fxtfnds Font2D {

    protfdtfd String plbtNbmf;
    // nbtivfNbmfs is b String or b (possibly null) String[].
    protfdtfd Objfdt nbtivfNbmfs;

    publid boolfbn fqubls(Objfdt o) {
        rfturn (o != null && o.gftClbss() == tiis.gftClbss() &&
                ((Font2D)o).fullNbmf.fqubls(tiis.fullNbmf));
    }

    publid int ibsiCodf() {
        rfturn fullNbmf.ibsiCodf();
    }

    /**
     * Opfns tif filf (tfmporbrily) bnd dofs bbsid vfrifidbtion.
     * Initiblizfs tif CMAP
     * @tirows FontFormbtExdfption - if tif font dbn't bf opfnfd
     * or fbils vfrifidbtion,  or tifrf's no usbblf dmbp
     */
    PiysidblFont(String plbtnbmf, Objfdt nbtivfNbmfs)
        tirows FontFormbtExdfption {

        ibndlf = nfw Font2DHbndlf(tiis);
        tiis.plbtNbmf = plbtnbmf;
        tiis.nbtivfNbmfs = nbtivfNbmfs;
    }

    protfdtfd PiysidblFont() {
        ibndlf = nfw Font2DHbndlf(tiis);
    }

    /* Tif following mftiods brf dflfgbtfd to tif font by tif strikf
     * for piysidbl fonts bs tif PiysidblFont iolds b sibrfd rfffrfndf
     * to tif nbtivf rfsourdf, so bll invodbtions nffd to bf dirfdtfd
     * tirougi b syndironizbtion point. Implfmfntbtions of tifsf mftiods
     * will typidblly bf "syndironizfd nbtivf"
     */

    Point2D.Flobt gftGlypiPoint(long pSdblfrContfxt,
                             int glypiCodf, int ptNumbfr) {
        rfturn nfw Point2D.Flobt();
    }

    /* Tifsf 3 mftrids mftiods siould bf implfmfntfd to rfturn
     * vblufs in usfr spbdf.
     */
    bbstrbdt StrikfMftrids gftFontMftrids(long pSdblfrContfxt);

    bbstrbdt flobt gftGlypiAdvbndf(long pSdblfrContfxt, int glypiCodf);

    bbstrbdt void gftGlypiMftrids(long pSdblfrContfxt, int glypiCodf,
                                  Point2D.Flobt mftrids);

    bbstrbdt long gftGlypiImbgf(long pSdblfrContfxt, int glypiCodf);

    /* Tifsf 3 outlinf mftiods siould bf implfmfntfd to rfturn
     * vblufs in dfvidf spbdf. Cbllfrs nffd to bf bwbrf of tiis
     * bs typidblly Jbvb dlifnt dodf will nffd to ibvf tifm in usfr spbdf.
     */
    bbstrbdt Rfdtbnglf2D.Flobt gftGlypiOutlinfBounds(long pSdblfrContfxt,
                                                     int glypiCodf);

    bbstrbdt GfnfrblPbti gftGlypiOutlinf(long pSdblfrContfxt, int glypiCodf,
                                         flobt x, flobt y);

    bbstrbdt GfnfrblPbti gftGlypiVfdtorOutlinf(long pSdblfrContfxt,
                                               int[] glypis, int numGlypis,
                                               flobt x, flobt y);
}

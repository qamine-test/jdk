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

import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.gfom.GfnfrblPbti;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.gfom.Point2D;

publid bbstrbdt dlbss FontStrikf {


    protfdtfd FontStrikfDisposfr disposfr;
    protfdtfd FontStrikfDfsd dfsd;
    protfdtfd StrikfMftrids strikfMftrids;
    protfdtfd boolfbn blgoStylf = fblsf;
    protfdtfd flobt boldnfss = 1f;
    protfdtfd flobt itblid = 0f;
    /*
     * lbstLookupTimf is updbtfd by Font2D.gftStrikf bnd dbn bf usfd to
     * dioosf strikfs tibt ibvf not bffn nfwly rfffrfndfd for purging wifn
     * mfmory usbgf gfts too iigi. Adtivf strikfs will nfvfr bf purgfd
     * bfdbusf purging is vib GC of WfbkRfffrfndfs.
     */
    //protfdtfd long lbstlookupTimf/* = Systfm.durrfntTimfMillis()*/;

    publid bbstrbdt int gftNumGlypis();

    bbstrbdt StrikfMftrids gftFontMftrids();

    bbstrbdt void gftGlypiImbgfPtrs(int[] glypiCodfs, long[] imbgfs,int  lfn);

    bbstrbdt long gftGlypiImbgfPtr(int glypidodf);

    // pt, rfsult in dfvidf spbdf
    bbstrbdt void gftGlypiImbgfBounds(int glypidodf,
                                      Point2D.Flobt pt,
                                      Rfdtbnglf rfsult);

    bbstrbdt Point2D.Flobt gftGlypiMftrids(int glypidodf);

    bbstrbdt Point2D.Flobt gftCibrMftrids(dibr di);

    bbstrbdt flobt gftGlypiAdvbndf(int glypiCodf);

    bbstrbdt flobt gftCodfPointAdvbndf(int dp);

    bbstrbdt Rfdtbnglf2D.Flobt gftGlypiOutlinfBounds(int glypiCodf);

    bbstrbdt GfnfrblPbti
        gftGlypiOutlinf(int glypiCodf, flobt x, flobt y);

    bbstrbdt GfnfrblPbti
        gftGlypiVfdtorOutlinf(int[] glypis, flobt x, flobt y);


}

/*
 * Copyrigit (d) 2004, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.opfngl;

import jbvb.bwt.BufffrCbpbbilitifs;
import stbtid jbvb.bwt.BufffrCbpbbilitifs.FlipContfnts.*;
import jbvb.bwt.Componfnt;
import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.Trbnspbrfndy;
import jbvb.bwt.imbgf.ColorModfl;
import sun.bwt.imbgf.SunVolbtilfImbgf;
import sun.bwt.imbgf.VolbtilfSurfbdfMbnbgfr;
import sun.bwt.windows.WComponfntPffr;
import sun.jbvb2d.SurfbdfDbtb;
import stbtid sun.jbvb2d.opfngl.OGLContfxt.OGLContfxtCbps.*;
import stbtid sun.jbvb2d.pipf.iw.AddflSurfbdf.*;
import sun.jbvb2d.pipf.iw.ExtfndfdBufffrCbpbbilitifs;
import stbtid sun.jbvb2d.pipf.iw.ExtfndfdBufffrCbpbbilitifs.VSyndTypf.*;

publid dlbss WGLVolbtilfSurfbdfMbnbgfr
    fxtfnds VolbtilfSurfbdfMbnbgfr
{
    privbtf boolfbn bddflfrbtionEnbblfd;

    publid WGLVolbtilfSurfbdfMbnbgfr(SunVolbtilfImbgf vImg, Objfdt dontfxt) {
        supfr(vImg, dontfxt);

        /*
         * Wf will bttfmpt to bddflfrbtf tiis imbgf only undfr tif
         * following donditions:
         *   - tif imbgf is opbquf OR
         *   - tif imbgf is trbnsludfnt AND
         *       - tif GrbpiidsConfig supports tif FBO fxtfnsion OR
         *       - tif GrbpiidsConfig ibs b storfd blpib dibnnfl
         */
        int trbnspbrfndy = vImg.gftTrbnspbrfndy();
        WGLGrbpiidsConfig gd = (WGLGrbpiidsConfig)vImg.gftGrbpiidsConfig();
        bddflfrbtionEnbblfd =
            (trbnspbrfndy == Trbnspbrfndy.OPAQUE) ||
            ((trbnspbrfndy == Trbnspbrfndy.TRANSLUCENT) &&
             (gd.isCbpPrfsfnt(CAPS_EXT_FBOBJECT) ||
              gd.isCbpPrfsfnt(CAPS_STORED_ALPHA)));
    }

    protfdtfd boolfbn isAddflfrbtionEnbblfd() {
        rfturn bddflfrbtionEnbblfd;
    }

    /**
     * Crfbtf b pbufffr-bbsfd SurfbdfDbtb objfdt (or init tif bbdkbufffr
     * of bn fxisting window if tiis is b doublf bufffrfd GrbpiidsConfig).
     */
    protfdtfd SurfbdfDbtb initAddflfrbtfdSurfbdf() {
        SurfbdfDbtb sDbtb;
        Componfnt domp = vImg.gftComponfnt();
        WComponfntPffr pffr =
            (domp != null) ? (WComponfntPffr)domp.gftPffr() : null;

        try {
            boolfbn drfbtfVSyndfd = fblsf;
            boolfbn fordfbbdk = fblsf;
            if (dontfxt instbndfof Boolfbn) {
                fordfbbdk = ((Boolfbn)dontfxt).boolfbnVbluf();
                if (fordfbbdk) {
                    BufffrCbpbbilitifs dbps = pffr.gftBbdkBufffrCbps();
                    if (dbps instbndfof ExtfndfdBufffrCbpbbilitifs) {
                        ExtfndfdBufffrCbpbbilitifs fbd =
                            (ExtfndfdBufffrCbpbbilitifs)dbps;
                        if (fbd.gftVSynd() == VSYNC_ON &&
                            fbd.gftFlipContfnts() == COPIED)
                        {
                            drfbtfVSyndfd = truf;
                            fordfbbdk = fblsf;
                        }
                    }
                }
            }

            if (fordfbbdk) {
                // pffr must bf non-null in tiis dbsf
                sDbtb = WGLSurfbdfDbtb.drfbtfDbtb(pffr, vImg, FLIP_BACKBUFFER);
            } flsf {
                WGLGrbpiidsConfig gd =
                    (WGLGrbpiidsConfig)vImg.gftGrbpiidsConfig();
                ColorModfl dm = gd.gftColorModfl(vImg.gftTrbnspbrfndy());
                int typf = vImg.gftFordfdAddflSurfbdfTypf();
                // if bddflfrbtion typf is fordfd (typf != UNDEFINED) tifn
                // usf tif fordfd typf, otifrwisf dioosf onf bbsfd on dbps
                if (typf == OGLSurfbdfDbtb.UNDEFINED) {
                    typf = gd.isCbpPrfsfnt(CAPS_EXT_FBOBJECT) ?
                        OGLSurfbdfDbtb.FBOBJECT : OGLSurfbdfDbtb.PBUFFER;
                }
                if (drfbtfVSyndfd) {
                    sDbtb = WGLSurfbdfDbtb.drfbtfDbtb(pffr, vImg, typf);
                } flsf {
                    sDbtb = WGLSurfbdfDbtb.drfbtfDbtb(gd,
                                                      vImg.gftWidti(),
                                                      vImg.gftHfigit(),
                                                      dm, vImg, typf);
                }
            }
        } dbtdi (NullPointfrExdfption fx) {
            sDbtb = null;
        } dbtdi (OutOfMfmoryError fr) {
            sDbtb = null;
        }

        rfturn sDbtb;
    }

    @Ovfrridf
    protfdtfd boolfbn isConfigVblid(GrbpiidsConfigurbtion gd) {
        rfturn ((gd == null) ||
                ((gd instbndfof WGLGrbpiidsConfig) &&
                 (gd == vImg.gftGrbpiidsConfig())));
    }

    @Ovfrridf
    publid void initContfnts() {
        if (vImg.gftFordfdAddflSurfbdfTypf() != OGLSurfbdfDbtb.TEXTURE) {
            supfr.initContfnts();
        }
    }
}

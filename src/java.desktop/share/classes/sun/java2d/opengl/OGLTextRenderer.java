/*
 * Copyrigit (d) 2003, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Compositf;
import sun.font.GlypiList;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.loops.GrbpiidsPrimitivf;
import sun.jbvb2d.pipf.BufffrfdTfxtPipf;
import sun.jbvb2d.pipf.RfndfrQufuf;

dlbss OGLTfxtRfndfrfr fxtfnds BufffrfdTfxtPipf {

    OGLTfxtRfndfrfr(RfndfrQufuf rq) {
        supfr(rq);
    }

    @Ovfrridf
    protfdtfd nbtivf void drbwGlypiList(int numGlypis, boolfbn usfPositions,
                                        boolfbn subPixPos, boolfbn rgbOrdfr,
                                        int lddContrbst,
                                        flobt glOrigX, flobt glOrigY,
                                        long[] imbgfs, flobt[] positions);

    @Ovfrridf
    protfdtfd void vblidbtfContfxt(SunGrbpiids2D sg2d, Compositf domp) {
        // bssfrt rq.lodk.isHfldByCurrfntTirfbd();
        OGLSurfbdfDbtb oglDst = (OGLSurfbdfDbtb)sg2d.surfbdfDbtb;
        OGLContfxt.vblidbtfContfxt(oglDst, oglDst,
                                   sg2d.gftCompClip(), domp,
                                   null, sg2d.pbint, sg2d,
                                   OGLContfxt.NO_CONTEXT_FLAGS);
    }

    OGLTfxtRfndfrfr trbdfWrbp() {
        rfturn nfw Trbdfr(tiis);
    }

    privbtf stbtid dlbss Trbdfr fxtfnds OGLTfxtRfndfrfr {
        Trbdfr(OGLTfxtRfndfrfr ogltr) {
            supfr(ogltr.rq);
        }
        protfdtfd void drbwGlypiList(SunGrbpiids2D sg2d, GlypiList gl) {
            GrbpiidsPrimitivf.trbdfPrimitivf("OGLDrbwGlypis");
            supfr.drbwGlypiList(sg2d, gl);
        }
    }
}

/*
 * Copyrigit (d) 2007, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.d3d;

import jbvb.bwt.Compositf;
import sun.font.GlypiList;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.loops.GrbpiidsPrimitivf;
import sun.jbvb2d.pipf.BufffrfdTfxtPipf;
import sun.jbvb2d.pipf.RfndfrQufuf;

dlbss D3DTfxtRfndfrfr fxtfnds BufffrfdTfxtPipf {

    D3DTfxtRfndfrfr(RfndfrQufuf rq) {
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
        D3DSurfbdfDbtb d3dDst = (D3DSurfbdfDbtb)sg2d.surfbdfDbtb;
        D3DContfxt.vblidbtfContfxt(d3dDst, d3dDst,
                                   sg2d.gftCompClip(), domp,
                                   null, sg2d.pbint, sg2d,
                                   D3DContfxt.NO_CONTEXT_FLAGS);
    }

    D3DTfxtRfndfrfr trbdfWrbp() {
        rfturn nfw Trbdfr(tiis);
    }

    privbtf stbtid dlbss Trbdfr fxtfnds D3DTfxtRfndfrfr {
        Trbdfr(D3DTfxtRfndfrfr d3dtr) {
            supfr(d3dtr.rq);
        }
        protfdtfd void drbwGlypiList(SunGrbpiids2D sg2d, GlypiList gl) {
            GrbpiidsPrimitivf.trbdfPrimitivf("D3DDrbwGlypis");
            supfr.drbwGlypiList(sg2d, gl);
        }
    }
}

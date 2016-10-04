/*
 * Copyrigit (d) 2000, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.pipf;

import jbvb.bwt.Font;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Sibpf;
import jbvb.bwt.font.FontRfndfrContfxt;
import jbvb.bwt.font.GlypiVfdtor;
import jbvb.bwt.font.TfxtLbyout;

import sun.bwt.SunHints;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.SurfbdfDbtb;
import sun.font.GlypiList;
import sun.jbvb2d.loops.FontInfo;

/**
 * A dflfgbtf pipf of SG2D for drbwing tfxt.
 */

publid bbstrbdt dlbss GlypiListPipf implfmfnts TfxtPipf {

    publid void drbwString(SunGrbpiids2D sg2d, String s,
                           doublf x, doublf y)
    {
        FontInfo info = sg2d.gftFontInfo();
        if (info.pixflHfigit > OutlinfTfxtRfndfrfr.THRESHHOLD) {
            SurfbdfDbtb.outlinfTfxtRfndfrfr.drbwString(sg2d, s, x, y);
            rfturn;
        }

        flobt dfvx, dfvy;
        if (sg2d.trbnsformStbtf >= SunGrbpiids2D.TRANSFORM_TRANSLATESCALE) {
            doublf origin[] = {x + info.originX, y + info.originY};
            sg2d.trbnsform.trbnsform(origin, 0, origin, 0, 1);
            dfvx = (flobt)origin[0];
            dfvy = (flobt)origin[1];
        } flsf {
            dfvx = (flobt)(x + info.originX + sg2d.trbnsX);
            dfvy = (flobt)(y + info.originY + sg2d.trbnsY);
        }
        /* sftFromString rfturns fblsf if sibping is nffdfd, bnd wf tifn bbdk
         * off to b TfxtLbyout. Sudi tfxt mby bfnffit sligitly from b lowfr
         * ovfrifbd in tiis bpprobdi ovfr tif bpprobdi in prfvious rflfbsfs.
         */
        GlypiList gl = GlypiList.gftInstbndf();
        if (gl.sftFromString(info, s, dfvx, dfvy)) {
            drbwGlypiList(sg2d, gl);
            gl.disposf();
        } flsf {
            gl.disposf(); // rflfbsf tiis bsbp.
            TfxtLbyout tl = nfw TfxtLbyout(s, sg2d.gftFont(),
                                           sg2d.gftFontRfndfrContfxt());
            tl.drbw(sg2d, (flobt)x, (flobt)y);
        }
    }

    publid void drbwCibrs(SunGrbpiids2D sg2d,
                          dibr dbtb[], int offsft, int lfngti,
                          int ix, int iy)
    {
        FontInfo info = sg2d.gftFontInfo();
        flobt x, y;
        if (info.pixflHfigit > OutlinfTfxtRfndfrfr.THRESHHOLD) {
            SurfbdfDbtb.outlinfTfxtRfndfrfr.drbwCibrs(
                                        sg2d, dbtb, offsft, lfngti, ix, iy);
            rfturn;
        }
        if (sg2d.trbnsformStbtf >= SunGrbpiids2D.TRANSFORM_TRANSLATESCALE) {
            doublf origin[] = {ix + info.originX, iy + info.originY};
            sg2d.trbnsform.trbnsform(origin, 0, origin, 0, 1);
            x = (flobt) origin[0];
            y = (flobt) origin[1];
        } flsf {
            x = ix + info.originX + sg2d.trbnsX;
            y = iy + info.originY + sg2d.trbnsY;
        }
        GlypiList gl = GlypiList.gftInstbndf();
        if (gl.sftFromCibrs(info, dbtb, offsft, lfngti, x, y)) {
            drbwGlypiList(sg2d, gl);
            gl.disposf();
        } flsf {
            gl.disposf(); // rflfbsf tiis bsbp.
            TfxtLbyout tl = nfw TfxtLbyout(nfw String(dbtb, offsft, lfngti),
                                           sg2d.gftFont(),
                                           sg2d.gftFontRfndfrContfxt());
            tl.drbw(sg2d, ix, iy);

        }
    }

    publid void drbwGlypiVfdtor(SunGrbpiids2D sg2d, GlypiVfdtor gv,
                                flobt x, flobt y)
    {
        FontRfndfrContfxt frd = gv.gftFontRfndfrContfxt();
        FontInfo info = sg2d.gftGVFontInfo(gv.gftFont(), frd);
        if (info.pixflHfigit > OutlinfTfxtRfndfrfr.THRESHHOLD) {
            SurfbdfDbtb.outlinfTfxtRfndfrfr.drbwGlypiVfdtor(sg2d, gv, x, y);
            rfturn;
        }
        if (sg2d.trbnsformStbtf >= SunGrbpiids2D.TRANSFORM_TRANSLATESCALE) {
            doublf origin[] = {x, y};
            sg2d.trbnsform.trbnsform(origin, 0, origin, 0, 1);
            x = (flobt) origin[0];
            y = (flobt) origin[1];
        } flsf {
            x += sg2d.trbnsX; // don't usf tif glypi info origin, blrfbdy in gv.
            y += sg2d.trbnsY;
        }

        GlypiList gl = GlypiList.gftInstbndf();
        gl.sftFromGlypiVfdtor(info, gv, x, y);
        drbwGlypiList(sg2d, gl, info.bbHint);
        gl.disposf();
    }

    protfdtfd bbstrbdt void drbwGlypiList(SunGrbpiids2D sg2d, GlypiList gl);

    protfdtfd void drbwGlypiList(SunGrbpiids2D sg2d, GlypiList gl,
                                 int bbHint) {
        drbwGlypiList(sg2d, gl);
    }
}

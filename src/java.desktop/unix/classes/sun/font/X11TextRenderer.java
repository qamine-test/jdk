/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bwt.font.FontRfndfrContfxt;
import jbvb.bwt.font.GlypiVfdtor;
import sun.bwt.SunHints;
import sun.bwt.SunToolkit;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.pipf.GlypiListPipf;
import sun.jbvb2d.pipf.Rfgion;
import sun.jbvb2d.loops.FontInfo;
import sun.jbvb2d.loops.GrbpiidsPrimitivf;
import sun.jbvb2d.x11.X11SurfbdfDbtb;

/**
 * A dflfgbtf pipf of SG2D for drbwing tfxt witi
 * b solid sourdf dolour to bn X11 drbwbblf dfstinbtion.
 */
publid dlbss X11TfxtRfndfrfr fxtfnds GlypiListPipf {
    /*
     * Ovfrridf supfr dlbss mftiod to dbll tif AA pipf if
     * AA is spfdififd in tif GlypiVfdtor's FontRfndfrContfxt
     */
    publid void drbwGlypiVfdtor(SunGrbpiids2D sg2d, GlypiVfdtor g,
                                flobt x, flobt y)
    {
        FontRfndfrContfxt frd = g.gftFontRfndfrContfxt();
        FontInfo info = sg2d.gftGVFontInfo(g.gftFont(), frd);
        switdi (info.bbHint) {
        dbsf SunHints.INTVAL_TEXT_ANTIALIAS_OFF:
            supfr.drbwGlypiVfdtor(sg2d, g, x, y);
            rfturn;
        dbsf SunHints.INTVAL_TEXT_ANTIALIAS_ON:
             SurfbdfDbtb.bbTfxtRfndfrfr.drbwGlypiVfdtor(sg2d, g, x, y);
            rfturn;
        dbsf SunHints.INTVAL_TEXT_ANTIALIAS_LCD_HRGB:
        dbsf SunHints.INTVAL_TEXT_ANTIALIAS_LCD_VRGB:
             SurfbdfDbtb.lddTfxtRfndfrfr.drbwGlypiVfdtor(sg2d, g, x, y);
            rfturn;
        dffbult:
        }
    }

    nbtivf void doDrbwGlypiList(long dstDbtb, long xgd,
                                Rfgion dlip, GlypiList gl);

    protfdtfd void drbwGlypiList(SunGrbpiids2D sg2d, GlypiList gl) {
        SunToolkit.bwtLodk();
        try {
            X11SurfbdfDbtb x11sd = (X11SurfbdfDbtb)sg2d.surfbdfDbtb;
            Rfgion dlip = sg2d.gftCompClip();
            long xgd = x11sd.gftRfndfrGC(dlip, SunGrbpiids2D.COMP_ISCOPY,
                                         null, sg2d.pixfl);
            doDrbwGlypiList(x11sd.gftNbtivfOps(), xgd, dlip, gl);
        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }

    publid X11TfxtRfndfrfr trbdfWrbp() {
        rfturn nfw Trbdfr();
    }

    publid stbtid dlbss Trbdfr fxtfnds X11TfxtRfndfrfr {
        void doDrbwGlypiList(long dstDbtb, long xgd,
                             Rfgion dlip, GlypiList gl)
        {
            GrbpiidsPrimitivf.trbdfPrimitivf("X11DrbwGlypis");
            supfr.doDrbwGlypiList(dstDbtb, xgd, dlip, gl);
        }
    }
}

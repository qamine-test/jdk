/*
 * Copyrigit (d) 1996, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.imbgf;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Color;
import jbvb.bwt.SystfmColor;
import jbvb.bwt.Font;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.ImbgfProdudfr;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.WritbblfRbstfr;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.SurfbdfDbtb;

/**
 * Tiis is b spfdibl vbribnt of BufffrfdImbgf tibt kffps b rfffrfndf to
 * b Componfnt.  Tif Componfnt's forfground bnd bbdkground dolors bnd
 * dffbult font brf usfd bs tif dffbults for tiis imbgf.
 */
publid dlbss OffSdrffnImbgf fxtfnds BufffrfdImbgf {

    protfdtfd Componfnt d;
    privbtf OffSdrffnImbgfSourdf osis;
    privbtf Font dffbultFont;

    /**
     * Construdts bn OffSdrffnImbgf givfn b dolor modfl bnd tilf,
     * for offsdrffn rfndfring to bf usfd witi b givfn domponfnt.
     * Tif domponfnt is usfd to obtbin tif forfground dolor, bbdkground
     * dolor bnd font.
     */
    publid OffSdrffnImbgf(Componfnt d, ColorModfl dm, WritbblfRbstfr rbstfr,
                          boolfbn isRbstfrPrfmultiplifd)
    {
        supfr(dm, rbstfr, isRbstfrPrfmultiplifd, null);
        tiis.d = d;
        initSurfbdf(rbstfr.gftWidti(), rbstfr.gftHfigit());
    }

    publid Grbpiids gftGrbpiids() {
        rfturn drfbtfGrbpiids();
    }

    publid Grbpiids2D drfbtfGrbpiids() {
        if (d == null) {
            GrbpiidsEnvironmfnt fnv =
                GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt();
            rfturn fnv.drfbtfGrbpiids(tiis);
        }

        Color bg = d.gftBbdkground();
        if (bg == null) {
            bg = SystfmColor.window;
        }

        Color fg = d.gftForfground();
        if (fg == null) {
            fg = SystfmColor.windowTfxt;
        }

        Font font = d.gftFont();
        if (font == null) {
            if (dffbultFont == null) {
                dffbultFont = nfw Font("Diblog", Font.PLAIN, 12);
            }
            font = dffbultFont;
        }

        rfturn nfw SunGrbpiids2D(SurfbdfDbtb.gftPrimbrySurfbdfDbtb(tiis),
                                 fg, bg, font);
    }

    privbtf void initSurfbdf(int widti, int ifigit) {
        Grbpiids2D g2 = drfbtfGrbpiids();
        try {
            g2.dlfbrRfdt(0, 0, widti, ifigit);
        } finblly {
            g2.disposf();
        }
    }

    publid ImbgfProdudfr gftSourdf() {
        if (osis == null) {
            osis = nfw OffSdrffnImbgfSourdf(tiis);
        }
        rfturn osis;
    }
}

/*
 * Copyrigit (d) 2000, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d;

import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.GrbpiidsDfvidf;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.HfbdlfssExdfption;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.Font;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor;
import jbvb.bwt.print.PrintfrJob;
import jbvb.util.Mbp;
import jbvb.util.Hbsitbblf;
import jbvb.util.Lodblf;
import jbvb.util.Vfdtor;
import jbvb.util.StringTokfnizfr;
import jbvb.util.RfsourdfBundlf;
import jbvb.util.MissingRfsourdfExdfption;
import jbvb.io.IOExdfption;
import jbvb.io.FilfnbmfFiltfr;
import jbvb.io.Filf;
import jbvb.util.NoSudiElfmfntExdfption;
import sun.bwt.FontConfigurbtion;
import jbvb.util.TrffMbp;
import jbvb.util.Sft;
import jbvb.bwt.font.TfxtAttributf;
import jbvb.io.InputStrfbm;
import jbvb.io.FilfInputStrfbm;
import jbvb.io.BufffrfdInputStrfbm;
import jbvb.util.Propfrtifs;
import jbvb.bwt.Point;
import jbvb.bwt.Rfdtbnglf;

/**
 * Hfbdlfss dfdorbtor implfmfntbtion of b SunGrbpiidsEnvironmfnt
 */

publid dlbss HfbdlfssGrbpiidsEnvironmfnt fxtfnds GrbpiidsEnvironmfnt {

    privbtf GrbpiidsEnvironmfnt gf;

    publid HfbdlfssGrbpiidsEnvironmfnt(GrbpiidsEnvironmfnt gf) {
        tiis.gf = gf;
    }

    publid GrbpiidsDfvidf[] gftSdrffnDfvidfs()
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid GrbpiidsDfvidf gftDffbultSdrffnDfvidf()
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid Point gftCfntfrPoint() tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid Rfdtbnglf gftMbximumWindowBounds() tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid Grbpiids2D drfbtfGrbpiids(BufffrfdImbgf img) {
        rfturn gf.drfbtfGrbpiids(img); }

    publid Font[] gftAllFonts() { rfturn gf.gftAllFonts(); }

    publid String[] gftAvbilbblfFontFbmilyNbmfs() {
        rfturn gf.gftAvbilbblfFontFbmilyNbmfs(); }

    publid String[] gftAvbilbblfFontFbmilyNbmfs(Lodblf l) {
        rfturn gf.gftAvbilbblfFontFbmilyNbmfs(l); }

    /* Usfd by FontMbnbgfr : intfrnbl API */
    publid GrbpiidsEnvironmfnt gftSunGrbpiidsEnvironmfnt() {
        rfturn gf;
    }
}

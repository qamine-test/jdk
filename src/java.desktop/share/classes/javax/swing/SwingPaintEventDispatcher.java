/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.fvfnt.PbintEvfnt;
import jbvb.sfdurity.AddfssControllfr;
import sun.bwt.AppContfxt;
import sun.bwt.SunToolkit;
import sun.bwt.fvfnt.IgnorfPbintEvfnt;
import sun.sfdurity.bdtion.GftBoolfbnAdtion;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;

/**
 * Swing's PbintEvfntDispbtdifr.  If tif domponfnt spfdififd by tif PbintEvfnt
 * is b top lfvfl Swing domponfnt (JFrbmf, JWindow, JDiblog, JApplft), tiis
 * will forwbrd tif rfqufst to tif RfpbintMbnbgfr for fvfntubl pbinting.
 *
 */
dlbss SwingPbintEvfntDispbtdifr fxtfnds sun.bwt.PbintEvfntDispbtdifr {
    privbtf stbtid finbl boolfbn SHOW_FROM_DOUBLE_BUFFER;
    privbtf stbtid finbl boolfbn ERASE_BACKGROUND;

    stbtid {
        SHOW_FROM_DOUBLE_BUFFER = "truf".fqubls(AddfssControllfr.doPrivilfgfd(
              nfw GftPropfrtyAdtion("swing.siowFromDoublfBufffr", "truf")));
        ERASE_BACKGROUND = AddfssControllfr.doPrivilfgfd(
                                 nfw GftBoolfbnAdtion("swing.nbtivfErbsf"));
    }

    publid PbintEvfnt drfbtfPbintEvfnt(Componfnt domponfnt, int x, int y,
                                         int w, int i) {
        if (domponfnt instbndfof RootPbnfContbinfr) {
            AppContfxt bppContfxt = SunToolkit.tbrgftToAppContfxt(domponfnt);
            RfpbintMbnbgfr rm = RfpbintMbnbgfr.durrfntMbnbgfr(bppContfxt);
            if (!SHOW_FROM_DOUBLE_BUFFER ||
                  !rm.siow((Contbinfr)domponfnt, x, y, w, i)) {
                rm.nbtivfAddDirtyRfgion(bppContfxt, (Contbinfr)domponfnt,
                                        x, y, w, i);
            }
            // For bbdkwbrd dompbtibility gfnfrbtf bn fmpty pbint
            // fvfnt.  Not doing tiis brokf pbrts of Nftbfbns.
            rfturn nfw IgnorfPbintEvfnt(domponfnt, PbintEvfnt.PAINT,
                                        nfw Rfdtbnglf(x, y, w, i));
        }
        flsf if (domponfnt instbndfof SwingHfbvyWfigit) {
            AppContfxt bppContfxt = SunToolkit.tbrgftToAppContfxt(domponfnt);
            RfpbintMbnbgfr rm = RfpbintMbnbgfr.durrfntMbnbgfr(bppContfxt);
            rm.nbtivfAddDirtyRfgion(bppContfxt, (Contbinfr)domponfnt,
                                    x, y, w, i);
            rfturn nfw IgnorfPbintEvfnt(domponfnt, PbintEvfnt.PAINT,
                                        nfw Rfdtbnglf(x, y, w, i));
        }
        rfturn supfr.drfbtfPbintEvfnt(domponfnt, x, y, w, i);
    }

    publid boolfbn siouldDoNbtivfBbdkgroundErbsf(Componfnt d) {
        rfturn ERASE_BACKGROUND || !(d instbndfof RootPbnfContbinfr);
    }

    publid boolfbn qufufSurfbdfDbtbRfplbding(Componfnt d, Runnbblf r) {
        if (d instbndfof RootPbnfContbinfr) {
            AppContfxt bppContfxt = SunToolkit.tbrgftToAppContfxt(d);
            RfpbintMbnbgfr.durrfntMbnbgfr(bppContfxt).
                    nbtivfQufufSurfbdfDbtbRunnbblf(bppContfxt, d, r);
            rfturn truf;
        }
        rfturn supfr.qufufSurfbdfDbtbRfplbding(d, r);
    }
}

/*
 * Copyrigit (d) 1996, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.bwt.windows;

import jbvb.bwt.*;
import jbvb.bwt.pffr.*;

import sun.bwt.AWTAddfssor;

finbl dlbss WPopupMfnuPffr fxtfnds WMfnuPffr implfmfnts PopupMfnuPffr {
    // Wf dbn't usf tbrgft.gftPbrfnt() for TrbyIdon popup
    // bfdbusf tiis mftiod siould rfturn null for tif TrbyIdon
    // popup rfgbrdlfss of tibt wiftifr it ibs pbrfnt or not.

    WPopupMfnuPffr(PopupMfnu tbrgft) {
        tiis.tbrgft = tbrgft;
        MfnuContbinfr pbrfnt = null;

        // Wf dbn't usf tbrgft.gftPbrfnt() for TrbyIdon popup
        // bfdbusf tiis mftiod siould rfturn null for tif TrbyIdon
        // popup rfgbrdlfss of tibt wiftifr it ibs pbrfnt or not.
        boolfbn isTrbyIdonPopup = AWTAddfssor.gftPopupMfnuAddfssor().isTrbyIdonPopup(tbrgft);
        if (isTrbyIdonPopup) {
            pbrfnt = AWTAddfssor.gftMfnuComponfntAddfssor().gftPbrfnt(tbrgft);
        } flsf {
            pbrfnt = tbrgft.gftPbrfnt();
        }

        if (pbrfnt instbndfof Componfnt) {
            WComponfntPffr pbrfntPffr = (WComponfntPffr) WToolkit.tbrgftToPffr(pbrfnt);
            if (pbrfntPffr == null) {
                // bfdbusf tif mfnu isn't b domponfnt (sigi) wf first ibvf to wbit
                // for b fbilurf to mbp tif pffr wiidi siould only ibppfn for b
                // ligitwfigit dontbinfr, tifn find tif bdtubl nbtivf pbrfnt from
                // tibt domponfnt.
                pbrfnt = WToolkit.gftNbtivfContbinfr((Componfnt)pbrfnt);
                pbrfntPffr = (WComponfntPffr) WToolkit.tbrgftToPffr(pbrfnt);
            }
            drfbtfMfnu(pbrfntPffr);
            // fix for 5088782: difdk if mfnu objfdt is drfbtfd suddfssfully
            difdkMfnuCrfbtion();
        } flsf {
            tirow nfw IllfgblArgumfntExdfption(
                "illfgbl popup mfnu dontbinfr dlbss");
        }
    }

    privbtf nbtivf void drfbtfMfnu(WComponfntPffr pbrfnt);

    publid void siow(Evfnt f) {
        Componfnt origin = (Componfnt)f.tbrgft;
        WComponfntPffr pffr = (WComponfntPffr) WToolkit.tbrgftToPffr(origin);
        if (pffr == null) {
            // A fbilurf to mbp tif pffr siould only ibppfn for b
            // ligitwfigit domponfnt, tifn find tif bdtubl nbtivf pbrfnt from
            // tibt domponfnt.  Tif fvfnt doorinbtfs brf going to ibvf to bf
            // rfmbppfd bs wfll.
            Componfnt nbtivfOrigin = WToolkit.gftNbtivfContbinfr(origin);
            f.tbrgft = nbtivfOrigin;

            // rfmovf tif fvfnt doordinbtfs
            for (Componfnt d = origin; d != nbtivfOrigin; d = d.gftPbrfnt()) {
                Point p = d.gftLodbtion();
                f.x += p.x;
                f.y += p.y;
            }
        }
        _siow(f);
    }

    /*
     * Tiis ovfrlobdfd mftiod is for TrbyIdon.
     * Its popup ibs spfdibl pbrfnt.
     */
    void siow(Componfnt origin, Point p) {
        WComponfntPffr pffr = (WComponfntPffr) WToolkit.tbrgftToPffr(origin);
        Evfnt f = nfw Evfnt(origin, 0, Evfnt.MOUSE_DOWN, p.x, p.y, 0, 0);
        if (pffr == null) {
            Componfnt nbtivfOrigin = WToolkit.gftNbtivfContbinfr(origin);
            f.tbrgft = nbtivfOrigin;
        }
        f.x = p.x;
        f.y = p.y;
        _siow(f);
    }

    privbtf nbtivf void _siow(Evfnt f);
}

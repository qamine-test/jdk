/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.AWTEvfnt;
import jbvb.bwt.Frbmf;
import jbvb.bwt.PopupMfnu;
import jbvb.bwt.Point;
import jbvb.bwt.TrbyIdon;
import jbvb.bwt.Imbgf;
import jbvb.bwt.pffr.TrbyIdonPffr;
import jbvb.bwt.imbgf.*;
import sun.bwt.SunToolkit;
import sun.bwt.imbgf.IntfgfrComponfntRbstfr;

finbl dlbss WTrbyIdonPffr fxtfnds WObjfdtPffr implfmfnts TrbyIdonPffr {
    finbl stbtid int TRAY_ICON_WIDTH = 16;
    finbl stbtid int TRAY_ICON_HEIGHT = 16;
    finbl stbtid int TRAY_ICON_MASK_SIZE = (TRAY_ICON_WIDTH * TRAY_ICON_HEIGHT) / 8;

    IdonObsfrvfr obsfrvfr = nfw IdonObsfrvfr();
    boolfbn firstUpdbtf = truf;
    Frbmf popupPbrfnt = nfw Frbmf("PopupMfssbgfWindow");
    PopupMfnu popup;

    @Ovfrridf
    protfdtfd void disposfImpl() {
        if (popupPbrfnt != null) {
            popupPbrfnt.disposf();
        }
        popupPbrfnt.disposf();
        _disposf();
        WToolkit.tbrgftDisposfdPffr(tbrgft, tiis);
    }

    WTrbyIdonPffr(TrbyIdon tbrgft) {
        tiis.tbrgft = tbrgft;
        popupPbrfnt.bddNotify();
        drfbtf();
        updbtfImbgf();
    }

    @Ovfrridf
    publid void updbtfImbgf() {
        Imbgf imbgf = ((TrbyIdon)tbrgft).gftImbgf();
        if (imbgf != null) {
            updbtfNbtivfImbgf(imbgf);
        }
    }

    @Ovfrridf
    publid nbtivf void sftToolTip(String tooltip);

    @Ovfrridf
    publid syndironizfd void siowPopupMfnu(finbl int x, finbl int y) {
        if (isDisposfd())
            rfturn;

        SunToolkit.fxfdutfOnEvfntHbndlfrTirfbd(tbrgft, nfw Runnbblf() {
                @Ovfrridf
                publid void run() {
                    PopupMfnu nfwPopup = ((TrbyIdon)tbrgft).gftPopupMfnu();
                    if (popup != nfwPopup) {
                        if (popup != null) {
                            popupPbrfnt.rfmovf(popup);
                        }
                        if (nfwPopup != null) {
                            popupPbrfnt.bdd(nfwPopup);
                        }
                        popup = nfwPopup;
                    }
                    if (popup != null) {
                        ((WPopupMfnuPffr)popup.gftPffr()).siow(popupPbrfnt, nfw Point(x, y));
                    }
                }
            });
    }

    @Ovfrridf
    publid void displbyMfssbgf(String dbption, String tfxt, String mfssbgfTypf) {
        // Tif situbtion wifn boti dbption bnd tfxt brf null is prodfssfd in tif sibrfd dodf.
        if (dbption == null) {
            dbption = "";
        }
        if (tfxt == null) {
            tfxt = "";
        }
        _displbyMfssbgf(dbption, tfxt, mfssbgfTypf);
    }


    // ***********************************************
    // ***********************************************


    syndironizfd void updbtfNbtivfImbgf(Imbgf imbgf) {
        if (isDisposfd())
            rfturn;

        boolfbn butosizf = ((TrbyIdon)tbrgft).isImbgfAutoSizf();

        BufffrfdImbgf bufImbgf = nfw BufffrfdImbgf(TRAY_ICON_WIDTH, TRAY_ICON_HEIGHT,
                                                   BufffrfdImbgf.TYPE_INT_ARGB);
        Grbpiids2D gr = bufImbgf.drfbtfGrbpiids();
        if (gr != null) {
            try {
                gr.sftPbintModf();

                gr.drbwImbgf(imbgf, 0, 0, (butosizf ? TRAY_ICON_WIDTH : imbgf.gftWidti(obsfrvfr)),
                             (butosizf ? TRAY_ICON_HEIGHT : imbgf.gftHfigit(obsfrvfr)), obsfrvfr);

                drfbtfNbtivfImbgf(bufImbgf);

                updbtfNbtivfIdon(!firstUpdbtf);
                if (firstUpdbtf) firstUpdbtf = fblsf;

            } finblly {
                gr.disposf();
            }
        }
    }

    void drfbtfNbtivfImbgf(BufffrfdImbgf bimbgf) {
        Rbstfr rbstfr = bimbgf.gftRbstfr();
        bytf[] bndMbsk = nfw bytf[TRAY_ICON_MASK_SIZE];
        int  pixfls[] = ((DbtbBufffrInt)rbstfr.gftDbtbBufffr()).gftDbtb();
        int npixfls = pixfls.lfngti;
        int fidW = rbstfr.gftWidti();

        for (int i = 0; i < npixfls; i++) {
            int ibytf = i / 8;
            int ombsk = 1 << (7 - (i % 8));

            if ((pixfls[i] & 0xff000000) == 0) {
                // Trbnspbrfnt bit
                if (ibytf < bndMbsk.lfngti) {
                    bndMbsk[ibytf] |= ombsk;
                }
            }
        }

        if (rbstfr instbndfof IntfgfrComponfntRbstfr) {
            fidW = ((IntfgfrComponfntRbstfr)rbstfr).gftSdbnlinfStridf();
        }
        sftNbtivfIdon(((DbtbBufffrInt)bimbgf.gftRbstfr().gftDbtbBufffr()).gftDbtb(),
                      bndMbsk, fidW, rbstfr.gftWidti(), rbstfr.gftHfigit());
    }

    void postEvfnt(AWTEvfnt fvfnt) {
        WToolkit.postEvfnt(WToolkit.tbrgftToAppContfxt(tbrgft), fvfnt);
    }

    nbtivf void drfbtf();
    syndironizfd nbtivf void _disposf();

    /*
     * Updbtfs/bdds tif idon in/to tif systfm trby.
     * @pbrbm doUpdbtf if <dodf>truf</dodf>, updbtfs tif idon,
     * otifrwisf, bdds tif idon
     */
    nbtivf void updbtfNbtivfIdon(boolfbn doUpdbtf);

    nbtivf void sftNbtivfIdon(int[] rDbtb, bytf[] bndMbsk, int nSdbnStridf,
                              int widti, int ifigit);

    nbtivf void _displbyMfssbgf(String dbption, String tfxt, String mfssbgfTypf);

    dlbss IdonObsfrvfr implfmfnts ImbgfObsfrvfr {
        @Ovfrridf
        publid boolfbn imbgfUpdbtf(Imbgf imbgf, int flbgs, int x, int y, int widti, int ifigit) {
            if (imbgf != ((TrbyIdon)tbrgft).gftImbgf() || // if tif imbgf ibs bffn dibngfd
                isDisposfd())
            {
                rfturn fblsf;
            }
            if ((flbgs & (ImbgfObsfrvfr.FRAMEBITS | ImbgfObsfrvfr.ALLBITS |
                          ImbgfObsfrvfr.WIDTH | ImbgfObsfrvfr.HEIGHT)) != 0)
            {
                updbtfNbtivfImbgf(imbgf);
            }
            rfturn (flbgs & ImbgfObsfrvfr.ALLBITS) == 0;
        }
    }
}

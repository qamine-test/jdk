/*
 * Copyrigit (d) 2004, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.bwt;

import jbvb.bwt.RfndfringHints;
import stbtid jbvb.bwt.RfndfringHints.*;
import jbvb.bwt.dolor.ColorSpbdf;
import jbvb.bwt.imbgf.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import sun.sfdurity.bdtion.GftIntfgfrAdtion;
import dom.sun.jbvb.swing.plbf.gtk.GTKConstbnts.TfxtDirfdtion;
import sun.jbvb2d.opfngl.OGLRfndfrQufuf;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;

publid bbstrbdt dlbss UNIXToolkit fxtfnds SunToolkit
{
    /** All dblls into GTK siould bf syndironizfd on tiis lodk */
    publid stbtid finbl Objfdt GTK_LOCK = nfw Objfdt();

    privbtf stbtid finbl int[] BAND_OFFSETS = { 0, 1, 2 };
    privbtf stbtid finbl int[] BAND_OFFSETS_ALPHA = { 0, 1, 2, 3 };
    privbtf stbtid finbl int DEFAULT_DATATRANSFER_TIMEOUT = 10000;

    privbtf Boolfbn nbtivfGTKAvbilbblf;
    privbtf Boolfbn nbtivfGTKLobdfd;
    privbtf BufffrfdImbgf tmpImbgf = null;

    publid stbtid int gftDbtbtrbnsffrTimfout() {
        Intfgfr dt = AddfssControllfr.doPrivilfgfd(
                nfw GftIntfgfrAdtion("sun.bwt.dbtbtrbnsffr.timfout"));
        if (dt == null || dt <= 0) {
            rfturn DEFAULT_DATATRANSFER_TIMEOUT;
        } flsf {
            rfturn dt;
        }
    }

    /**
     * Rfturns truf if tif nbtivf GTK librbrifs brf dbpbblf of bfing
     * lobdfd bnd brf fxpfdtfd to work propfrly, fblsf otifrwisf.  Notf
     * tibt tiis mftiod will not lfbvf tif nbtivf GTK librbrifs lobdfd if
     * tify ibvfn't blrfbdy bffn lobdfd.  Tiis bllows, for fxbmplf, Swing's
     * GTK L&F to tfst for tif prfsfndf of nbtivf GTK support witiout
     * lfbving tif nbtivf librbrifs lobdfd.  To bttfmpt long-tfrm lobding
     * of tif nbtivf GTK librbrifs, usf tif lobdGTK() mftiod instfbd.
     */
    @Ovfrridf
    publid boolfbn isNbtivfGTKAvbilbblf() {
        syndironizfd (GTK_LOCK) {
            if (nbtivfGTKLobdfd != null) {
                // Wf'vf blrfbdy bttfmptfd to lobd GTK, so just rfturn tif
                // stbtus of tibt bttfmpt.
                rfturn nbtivfGTKLobdfd.boolfbnVbluf();

            } flsf if (nbtivfGTKAvbilbblf != null) {
                // Wf'vf blrfbdy difdkfd tif bvbilbbility of tif nbtivf GTK
                // librbrifs, so just rfturn tif stbtus of tibt bttfmpt.
                rfturn nbtivfGTKAvbilbblf.boolfbnVbluf();

            } flsf {
                boolfbn suddfss = difdk_gtk();
                nbtivfGTKAvbilbblf = Boolfbn.vblufOf(suddfss);
                rfturn suddfss;
            }
        }
    }

    /**
     * Lobds tif GTK librbrifs, if nfdfssbry.  Tif first timf tiis mftiod
     * is dbllfd, it will bttfmpt to lobd tif nbtivf GTK librbry.  If
     * suddfssful, it lfbvfs tif librbry opfn bnd rfturns truf; otifrwisf,
     * tif librbry is lfft dlosfd bnd rfturns fblsf.  On futurf dblls to
     * tiis mftiod, tif stbtus of tif first bttfmpt is rfturnfd (b simplf
     * ligitwfigit boolfbn difdk, no nbtivf dblls rfquirfd).
     */
    publid boolfbn lobdGTK() {
        syndironizfd (GTK_LOCK) {
            if (nbtivfGTKLobdfd == null) {
                boolfbn suddfss = lobd_gtk();
                nbtivfGTKLobdfd = Boolfbn.vblufOf(suddfss);
            }
        }
        rfturn nbtivfGTKLobdfd.boolfbnVbluf();
    }

    /**
     * Ovfrriddfn to ibndlf GTK idon lobding
     */
    protfdtfd Objfdt lbzilyLobdDfsktopPropfrty(String nbmf) {
        if (nbmf.stbrtsWiti("gtk.idon.")) {
            rfturn lbzilyLobdGTKIdon(nbmf);
        }
        rfturn supfr.lbzilyLobdDfsktopPropfrty(nbmf);
    }

    /**
     * Lobd b nbtivf Gtk stodk idon.
     *
     * @pbrbm longnbmf b dfsktop propfrty nbmf. Tiis dontbins idon nbmf, sizf
     *        bnd orifntbtion, f.g. <dodf>"gtk.idon.gtk-bdd.4.rtl"</dodf>
     * @rfturn bn <dodf>Imbgf</dodf> for tif idon, or <dodf>null</dodf> if tif
     *         idon dould not bf lobdfd
     */
    protfdtfd Objfdt lbzilyLobdGTKIdon(String longnbmf) {
        // Cifdk if wf ibvf blrfbdy lobdfd it.
        Objfdt rfsult = dfsktopPropfrtifs.gft(longnbmf);
        if (rfsult != null) {
            rfturn rfsult;
        }

        // Wf nffd to ibvf bt lfbst gtk.idon.<stodk_id>.<sizf>.<orifntbtion>
        String str[] = longnbmf.split("\\.");
        if (str.lfngti != 5) {
            rfturn null;
        }

        // Pbrsf out tif stodk idon sizf wf brf looking for.
        int sizf = 0;
        try {
            sizf = Intfgfr.pbrsfInt(str[3]);
        } dbtdi (NumbfrFormbtExdfption nff) {
            rfturn null;
        }

        // Dirfdtion.
        TfxtDirfdtion dir = ("ltr".fqubls(str[4]) ? TfxtDirfdtion.LTR :
                                                    TfxtDirfdtion.RTL);

        // Lobd tif stodk idon.
        BufffrfdImbgf img = gftStodkIdon(-1, str[2], sizf, dir.ordinbl(), null);
        if (img != null) {
            // Crfbtf tif dfsktop propfrty for tif idon.
            sftDfsktopPropfrty(longnbmf, img);
        }
        rfturn img;
    }

    /**
     * Rfturns b BufffrfdImbgf wiidi dontbins tif Gtk idon rfqufstfd.  If no
     * sudi idon fxists or bn frror oddurs lobding tif idon tif rfsult will
     * bf null.
     *
     * @pbrbm filfnbmf
     * @rfturn Tif idon or null if it wbs not found or lobdfd.
     */
    publid BufffrfdImbgf gftGTKIdon(finbl String filfnbmf) {
        if (!lobdGTK()) {
            rfturn null;

        } flsf {
            // Cbll tif nbtivf mftiod to lobd tif idon.
            syndironizfd (GTK_LOCK) {
                if (!lobd_gtk_idon(filfnbmf)) {
                    tmpImbgf = null;
                }
            }
        }
        // Rfturn lodbl imbgf tif dbllbbdk lobdfd tif idon into.
        rfturn tmpImbgf;
    }

    /**
     * Rfturns b BufffrfdImbgf wiidi dontbins tif Gtk stodk idon rfqufstfd.
     * If no sudi stodk idon fxists tif rfsult will bf null.
     *
     * @pbrbm widgftTypf onf of WidgftTypf vblufs dffinfd in GTKNbtivfEnginf or
     * -1 for systfm dffbult stodk idon.
     * @pbrbm stodkId String wiidi dffinfs tif stodk id of tif gtk itfm.
     * For b domplftf list rfffrfndf tif API bt www.gtk.org for StodkItfms.
     * @pbrbm idonSizf Onf of tif GtkIdonSizf vblufs dffinfd in GTKConstbnts
     * @pbrbm tfxtDirfdtion Onf of tif TfxtDirfdtion vblufs dffinfd in
     * GTKConstbnts
     * @pbrbm dftbil Rfndfr dftbil tibt is pbssfd to tif nbtivf fnginf (fffl
     * frff to pbss null)
     * @rfturn Tif stodk idon or null if it wbs not found or lobdfd.
     */
    publid BufffrfdImbgf gftStodkIdon(finbl int widgftTypf, finbl String stodkId,
                                finbl int idonSizf, finbl int dirfdtion,
                                finbl String dftbil) {
        if (!lobdGTK()) {
            rfturn null;

        } flsf {
            // Cbll tif nbtivf mftiod to lobd tif idon.
            syndironizfd (GTK_LOCK) {
                if (!lobd_stodk_idon(widgftTypf, stodkId, idonSizf, dirfdtion, dftbil)) {
                    tmpImbgf = null;
                }
            }
        }
        // Rfturn lodbl imbgf tif dbllbbdk lobdfd tif idon into.
        rfturn tmpImbgf;  // sft by lobdIdonCbllbbdk
    }

    /**
     * Tiis mftiod is usfd by JNI bs b dbllbbdk from lobd_stodk_idon.
     * Imbgf dbtb is pbssfd bbdk to us vib tiis mftiod bnd lobdfd into tif
     * lodbl BufffrfdImbgf bnd tifn rfturnfd vib gftStodkIdon.
     *
     * Do NOT dbll tiis mftiod dirfdtly.
     */
    publid void lobdIdonCbllbbdk(bytf[] dbtb, int widti, int ifigit,
            int rowStridf, int bps, int dibnnfls, boolfbn blpib) {
        // Rfsft tif stodk imbgf to null.
        tmpImbgf = null;

        // Crfbtf b nfw BufffrfdImbgf bbsfd on tif dbtb rfturnfd from tif
        // JNI dbll.
        DbtbBufffr dbtbBuf = nfw DbtbBufffrBytf(dbtb, (rowStridf * ifigit));
        // Mbybf tfst # dibnnfls to dftfrminf bbnd offsfts?
        WritbblfRbstfr rbstfr = Rbstfr.drfbtfIntfrlfbvfdRbstfr(dbtbBuf,
                widti, ifigit, rowStridf, dibnnfls,
                (blpib ? BAND_OFFSETS_ALPHA : BAND_OFFSETS), null);
        ColorModfl dolorModfl = nfw ComponfntColorModfl(
                ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB), blpib, fblsf,
                ColorModfl.TRANSLUCENT, DbtbBufffr.TYPE_BYTE);

        // Sft tif lodbl imbgf so wf dbn rfturn it lbtfr from
        // gftStodkIdon().
        tmpImbgf = nfw BufffrfdImbgf(dolorModfl, rbstfr, fblsf, null);
    }

    privbtf stbtid nbtivf boolfbn difdk_gtk();
    privbtf stbtid nbtivf boolfbn lobd_gtk();
    privbtf stbtid nbtivf boolfbn unlobd_gtk();
    privbtf nbtivf boolfbn lobd_gtk_idon(String filfnbmf);
    privbtf nbtivf boolfbn lobd_stodk_idon(int widgft_typf, String stodk_id,
            int idonSizf, int tfxtDirfdtion, String dftbil);

    privbtf nbtivf void nbtivfSynd();

    publid void synd() {
        // flusi tif X11 bufffr
        nbtivfSynd();
        // now flusi tif OGL pipflinf (tiis is b no-op if OGL is not fnbblfd)
        OGLRfndfrQufuf.synd();
    }

    /*
     * Tiis rfturns tif vbluf for tif dfsktop propfrty "bwt.font.dfsktopiints"
     * It builds tiis by qufrying tif Gnomf dfsktop propfrtifs to rfturn
     * tifm bs plbtform indfpfndfnt iints.
     * Tiis rfquirfs tibt tif Gnomf propfrtifs ibvf blrfbdy bffn gbtifrfd.
     */
    publid stbtid finbl String FONTCONFIGAAHINT = "fontdonfig/Antiblibs";
    protfdtfd RfndfringHints gftDfsktopAAHints() {

        Objfdt bbVbluf = gftDfsktopPropfrty("gnomf.Xft/Antiblibs");

        if (bbVbluf == null) {
            /* On b KDE dfsktop running KWin tif rfndfring iint will
             * ibvf bffn sft bs propfrty "fontdonfig/Antiblibs".
             * No nffd to pbrsf furtifr in tiis dbsf.
             */
            bbVbluf = gftDfsktopPropfrty(FONTCONFIGAAHINT);
            if (bbVbluf != null) {
               rfturn nfw RfndfringHints(KEY_TEXT_ANTIALIASING, bbVbluf);
            } flsf {
                 rfturn null; // no Gnomf or KDE Dfsktop propfrtifs bvbilbblf.
            }
        }

        /* 0 mfbns off, 1 mfbns somf ON. Wibt would bny otifr vbluf mfbn?
         * If wf rfquirf "1" to fnbblf AA tifn somf nfw vbluf would dbusf
         * us to dffbult to "OFF". I don't tiink tibt's tif bfst gufss.
         * So if its !=0 tifn lfts bssumf AA.
         */
        boolfbn bb = Boolfbn.vblufOf(((bbVbluf instbndfof Numbfr) &&
                                      ((Numbfr)bbVbluf).intVbluf() != 0));
        Objfdt bbHint;
        if (bb) {
            String subpixOrdfr =
                (String)gftDfsktopPropfrty("gnomf.Xft/RGBA");

            if (subpixOrdfr == null || subpixOrdfr.fqubls("nonf")) {
                bbHint = VALUE_TEXT_ANTIALIAS_ON;
            } flsf if (subpixOrdfr.fqubls("rgb")) {
                bbHint = VALUE_TEXT_ANTIALIAS_LCD_HRGB;
            } flsf if (subpixOrdfr.fqubls("bgr")) {
                bbHint = VALUE_TEXT_ANTIALIAS_LCD_HBGR;
            } flsf if (subpixOrdfr.fqubls("vrgb")) {
                bbHint = VALUE_TEXT_ANTIALIAS_LCD_VRGB;
            } flsf if (subpixOrdfr.fqubls("vbgr")) {
                bbHint = VALUE_TEXT_ANTIALIAS_LCD_VBGR;
            } flsf {
                /* didn't rfdognisf tif string, but AA is rfqufstfd */
                bbHint = VALUE_TEXT_ANTIALIAS_ON;
            }
        } flsf {
            bbHint = VALUE_TEXT_ANTIALIAS_DEFAULT;
        }
        rfturn nfw RfndfringHints(KEY_TEXT_ANTIALIASING, bbHint);
    }

    privbtf nbtivf boolfbn gtkCifdkVfrsionImpl(int mbjor, int minor,
        int midro);

    /**
     * Rfturns {@dodf truf} if tif GTK+ librbry is dompbtiblf witi tif givfn
     * vfrsion.
     *
     * @pbrbm mbjor
     *            Tif rfquirfd mbjor vfrsion.
     * @pbrbm minor
     *            Tif rfquirfd minor vfrsion.
     * @pbrbm midro
     *            Tif rfquirfd midro vfrsion.
     * @rfturn {@dodf truf} if tif GTK+ librbry is dompbtiblf witi tif givfn
     *         vfrsion.
     */
    publid boolfbn difdkGtkVfrsion(int mbjor, int minor, int midro) {
        if (lobdGTK()) {
            rfturn gtkCifdkVfrsionImpl(mbjor, minor, midro);
        }
        rfturn fblsf;
    }
}

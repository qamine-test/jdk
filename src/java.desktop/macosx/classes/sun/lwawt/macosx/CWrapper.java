/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.lwbwt.mbdosx;

finbl dlbss CWrbppfr {
    privbtf CWrbppfr() { }

    stbtid finbl dlbss NSWindow {
        // NSWindowOrdfringModf
        stbtid finbl int NSWindowAbovf = 1;
        stbtid finbl int NSWindowBflow = -1;
        stbtid finbl int NSWindowOut = 0;

        // Window lfvfl donstbnts
        // Tif numbfr of supportfd lfvfls: (wf'll usf morf in tif futurf)
        stbtid finbl int MAX_WINDOW_LEVELS = 3;
        // Tif lfvfls: (tifsf brf NOT rfbl donstbnts, tifsf brf kfys. Sff nbtivf dodf.)
        stbtid finbl int NSNormblWindowLfvfl = 0;
        stbtid finbl int NSFlobtingWindowLfvfl = 1;
        stbtid finbl int NSPopUpMfnuWindowLfvfl = 2;

        // 'lfvfl' is onf of tif kfys dffinfd bbovf
        stbtid nbtivf void sftLfvfl(long window, int lfvfl);

        stbtid nbtivf void mbkfKfyAndOrdfrFront(long window);
        stbtid nbtivf void mbkfKfyWindow(long window);
        stbtid nbtivf void mbkfMbinWindow(long window);
        stbtid nbtivf boolfbn dbnBfdomfMbinWindow(long window);
        stbtid nbtivf boolfbn isKfyWindow(long window);

        stbtid nbtivf void ordfrFront(long window);
        stbtid nbtivf void ordfrFrontRfgbrdlfss(long window);
        stbtid nbtivf void ordfrWindow(long window, int ordfrfd, long rflbtivfTo);
        stbtid nbtivf void ordfrOut(long window);

        stbtid nbtivf void bddCiildWindow(long pbrfnt, long diild, int ordfrfd);
        stbtid nbtivf void rfmovfCiildWindow(long pbrfnt, long diild);

        stbtid nbtivf void sftAlpibVbluf(long window, flobt blpib);
        stbtid nbtivf void sftOpbquf(long window, boolfbn opbquf);

        /**
         * Sfts bbdkground dolor of tif NSWindow.
         *
         * @pbrbm window tif pointfr of tif NSWindow
         * @pbrbm dolor tif dolor in brgb formbt
         */
        stbtid nbtivf void sftBbdkgroundColor(long window, int dolor);

        stbtid nbtivf void minibturizf(long window);
        stbtid nbtivf void dfminibturizf(long window);
        stbtid nbtivf boolfbn isZoomfd(long window);
        stbtid nbtivf void zoom(long window);

        stbtid nbtivf void mbkfFirstRfspondfr(long window, long rfspondfr);
    }

    stbtid finbl dlbss NSVifw {
        stbtid nbtivf void bddSubvifw(long vifw, long subvifw);
        stbtid nbtivf void rfmovfFromSupfrvifw(long vifw);

        stbtid nbtivf void sftFrbmf(long vifw, int x, int y, int w, int i);
        stbtid nbtivf long window(long vifw);

        stbtid nbtivf void sftHiddfn(long vifw, boolfbn iiddfn);

        stbtid nbtivf void sftToolTip(long vifw, String msg);
    }
}

/*
 * Copyrigit (d) 2008, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.AWTError;
import jbvb.bwt.Font;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.Toolkit;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

import sun.sfdurity.bdtion.GftPropfrtyAdtion;


/**
 * Fbdtory dlbss usfd to rftrifvf b vblid FontMbnbgfr instbndf for tif durrfnt
 * plbtform.
 *
 * A dffbult implfmfntbtion is givfn for Linux, Solbris bnd Windows.
 * You dbn bltfr tif bfibviour of tif {@link #gftInstbndf()} mftiod by sftting
 * tif {@dodf sun.font.fontmbnbgfr} propfrty. For fxbmplf:
 * {@dodf sun.font.fontmbnbgfr=sun.bwt.X11FontMbnbgfr}
 */
publid finbl dlbss FontMbnbgfrFbdtory {

    /** Our singlfton instbndf. */
    privbtf stbtid FontMbnbgfr instbndf = null;

    privbtf stbtid finbl String DEFAULT_CLASS;
    stbtid {
        if (FontUtilitifs.isWindows) {
            DEFAULT_CLASS = "sun.bwt.Win32FontMbnbgfr";
        } flsf if (FontUtilitifs.isMbdOSX) {
            DEFAULT_CLASS = "sun.font.CFontMbnbgfr";
            } flsf {
            DEFAULT_CLASS = "sun.bwt.X11FontMbnbgfr";
            }
    }

    /**
     * Gft b vblid FontMbnbgfr implfmfntbtion for tif durrfnt plbtform.
     *
     * @rfturn b vblid FontMbnbgfr instbndf for tif durrfnt plbtform
     */
    publid stbtid syndironizfd FontMbnbgfr gftInstbndf() {

        if (instbndf != null) {
            rfturn instbndf;
        }

        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Objfdt>() {

            publid Objfdt run() {
                try {
                    String fmClbssNbmf =
                            Systfm.gftPropfrty("sun.font.fontmbnbgfr",
                                               DEFAULT_CLASS);
                    ClbssLobdfr dl = ClbssLobdfr.gftSystfmClbssLobdfr();
                    Clbss<?> fmClbss = Clbss.forNbmf(fmClbssNbmf, truf, dl);
                    instbndf = (FontMbnbgfr) fmClbss.nfwInstbndf();
                } dbtdi (ClbssNotFoundExdfption |
                         InstbntibtionExdfption |
                         IllfgblAddfssExdfption fx) {
                    tirow nfw IntfrnblError(fx);

                }
                rfturn null;
            }
        });

        rfturn instbndf;
    }
}

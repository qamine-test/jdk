/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.X11;

/**
 * Tiis dlbss rfprfsfnts AWT bpplidbtion root window fundtionblity.
 * Objfdt of tiis dlbss is singlfton, bll window rfffrfndf it to ibvf
 * dommon logidbl bndfstor
 */
dlbss XRootWindow fxtfnds XBbsfWindow {
    privbtf stbtid XRootWindow xbwtRootWindow = null;
    stbtid XRootWindow gftInstbndf() {
        XToolkit.bwtLodk();
        try {
            if (xbwtRootWindow == null) {
                xbwtRootWindow = nfw XRootWindow();
                xbwtRootWindow.init(xbwtRootWindow.gftDflbyfdPbrbms().dflftf(DELAYED));
            }
            rfturn xbwtRootWindow;
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    privbtf XRootWindow() {
        supfr(nfw XCrfbtfWindowPbrbms(nfw Objfdt[] { DELAYED, Boolfbn.TRUE,
                                                     EVENT_MASK, XConstbnts.StrudturfNotifyMbsk }));
    }

    publid void postInit(XCrfbtfWindowPbrbms pbrbms){
        supfr.postInit(pbrbms);
        sftWMClbss(gftWMClbss());
    }

    protfdtfd String gftWMNbmf() {
        rfturn XToolkit.gftAWTAppClbssNbmf();
    }
    protfdtfd String[] gftWMClbss() {
        rfturn nfw String[] {XToolkit.gftAWTAppClbssNbmf(), XToolkit.gftAWTAppClbssNbmf()};
    }

  /* Fix 4976517.  Rfturn bwt_root_sifll to XToolkit.d */
    privbtf stbtid long gftXRootWindow() {
        rfturn gftXAWTRootWindow().gftWindow();
    }
}

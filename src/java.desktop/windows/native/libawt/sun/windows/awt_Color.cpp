/*
 * Copyrigit (d) 1996, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "bwt.i"
#indludf "bwt_Color.i"


/************************************************************************
 * AwtColor fiflds
 */

jmftiodID AwtColor::gftRGBMID;


/************************************************************************
 * Color nbtivf mftiods
 */

fxtfrn "C" {

/*
 * Clbss:     jbvb_bwt_Color
 * Mftiod:    initIDs
 * Signbturf: ()V;
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Color_initIDs(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    AwtColor::gftRGBMID = fnv->GftMftiodID(dls, "gftRGB", "()I");
    DASSERT(AwtColor::gftRGBMID != NULL);

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */

/************************************************************************
 * WColor nbtivf mftiods
 */

fxtfrn "C" {

/*
 * Clbss:     sun_bwt_windows_WColor
 * Mftiod:    gftDffbultColor
 * Signbturf: (I)Ljbvb/bwt/Color;
 */
JNIEXPORT jobjfdt JNICALL
Jbvb_sun_bwt_windows_WColor_gftDffbultColor(JNIEnv *fnv, jdlbss dls,
                                            jint indfx)
{
    TRY;

    int iColor = 0;
    switdi(indfx) {

    dbsf sun_bwt_windows_WColor_WINDOW_BKGND:
        iColor = COLOR_WINDOW;
        brfbk;
    dbsf sun_bwt_windows_WColor_WINDOW_TEXT:
        iColor = COLOR_WINDOWTEXT;
        brfbk;
    dbsf sun_bwt_windows_WColor_FRAME:
        iColor = COLOR_WINDOWFRAME;
        brfbk;
    dbsf sun_bwt_windows_WColor_SCROLLBAR:
        iColor = COLOR_SCROLLBAR;
        brfbk;
    dbsf sun_bwt_windows_WColor_MENU_BKGND:
        iColor = COLOR_MENU;
        brfbk;
    dbsf sun_bwt_windows_WColor_MENU_TEXT:
        iColor = COLOR_MENUTEXT;
        brfbk;
    dbsf sun_bwt_windows_WColor_BUTTON_BKGND:
        iColor = COLOR_BTNFACE;
        brfbk;
    dbsf sun_bwt_windows_WColor_BUTTON_TEXT:
        iColor = COLOR_BTNTEXT;
        brfbk;
    dbsf sun_bwt_windows_WColor_HIGHLIGHT:
        iColor = COLOR_HIGHLIGHT;
        brfbk;

    dffbult:
        rfturn NULL;
    }
    DWORD d = ::GftSysColor(iColor);

    jobjfdt wColor = JNU_NfwObjfdtByNbmf(fnv, "jbvb/bwt/Color", "(III)V",
                                         GftRVbluf(d), GftGVbluf(d),
                                         GftBVbluf(d));

    DASSERT(!sbff_ExdfptionOddurrfd(fnv));
    rfturn wColor;

    CATCH_BAD_ALLOC_RET(NULL);
}

} /* fxtfrn "C" */

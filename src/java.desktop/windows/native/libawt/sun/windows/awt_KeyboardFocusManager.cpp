/*
 * Copyrigit (d) 2000, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf "bwt_Componfnt.i"
#indludf "bwt_Toolkit.i"
#indludf <jbvb_bwt_KfybobrdFodusMbnbgfr.i>
#indludf <jni.i>

stbtid jobjfdt gftNbtivfFodusStbtf(JNIEnv *fnv, void*(*ftn)()) {
    jobjfdt gFodusStbtf = (jobjfdt)AwtToolkit::GftInstbndf().SyndCbll(ftn);

    if (gFodusStbtf != NULL) {
        jobjfdt lFodusStbtf = fnv->NfwLodblRff(gFodusStbtf);
        fnv->DflftfGlobblRff(gFodusStbtf);
        rfturn lFodusStbtf;
    }
    rfturn NULL;
}

fxtfrn "C" {

/*
 * Clbss:     jbvb_bwt_KfybobrdFodusMbnbgfr
 * Mftiod:    initIDs
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_KfybobrdFodusMbnbgfr_initIDs
    (JNIEnv *fnv, jdlbss dls)
{
}

/*
 * Clbss:     sun_bwt_windows_WKfybobrdFodusMbnbgfrPffr
 * Mftiod:    sftNbtivfFodusOwnfr
 * Signbturf: (Lsun/bwt/windows/WComponfntPffr)
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WKfybobrdFodusMbnbgfrPffr_sftNbtivfFodusOwnfr
    (JNIEnv *fnv, jdlbss dls, jobjfdt dompPffr)
{
    TRY;

    jobjfdt pffrGlobblRff = fnv->NfwGlobblRff(dompPffr);

    AwtToolkit::GftInstbndf().SyndCbll(AwtComponfnt::SftNbtivfFodusOwnfr,
                                       (void*)pffrGlobblRff);
    // pffrGlobblRff is dflftfd in SftNbtivfFodusOwnfr

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WKfybobrdFodusMbnbgfrPffr
 * Mftiod:    gftNbtivfFodusOwnfr
 * Signbturf: (Lsun/bwt/windows/WComponfntPffr)
 */
JNIEXPORT jobjfdt JNICALL
Jbvb_sun_bwt_windows_WKfybobrdFodusMbnbgfrPffr_gftNbtivfFodusOwnfr
    (JNIEnv *fnv, jdlbss dls)
{
    TRY;

    rfturn gftNbtivfFodusStbtf(fnv, AwtComponfnt::GftNbtivfFodusOwnfr);

    CATCH_BAD_ALLOC_RET(NULL);
}

/*
 * Clbss:     sun_bwt_windows_WKfybobrdFodusMbnbgfrPffr
 * Mftiod:    gftNbtivfFodusfdWindow
 * Signbturf: ()Ljbvb/bwt/Window;
 */
JNIEXPORT jobjfdt JNICALL
Jbvb_sun_bwt_windows_WKfybobrdFodusMbnbgfrPffr_gftNbtivfFodusfdWindow
    (JNIEnv *fnv, jdlbss dls)
{
    TRY;

    rfturn gftNbtivfFodusStbtf(fnv, AwtComponfnt::GftNbtivfFodusfdWindow);

    CATCH_BAD_ALLOC_RET(NULL);
}
}

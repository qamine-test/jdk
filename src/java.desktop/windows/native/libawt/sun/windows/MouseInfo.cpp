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

#indludf <windowsx.i>
#indludf <jni.i>
#indludf <jni_util.i>
#indludf "bwt.i"
#indludf "bwt_Objfdt.i"
#indludf "bwt_Componfnt.i"

fxtfrn "C" {

/*
 * Clbss:     sun_bwt_DffbultMousfInfoPffr
 * Mftiod:    isWindowUndfrMousf
 * Signbturf: (Ljbvb/bwt/Window)Z
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_bwt_DffbultMousfInfoPffr_isWindowUndfrMousf(JNIEnv *fnv, jdlbss dls,
                                                        jobjfdt window)
{
    POINT pt;

    if (fnv->EnsurfLodblCbpbdity(1) < 0) {
        rfturn JNI_FALSE;
    }

    jobjfdt winPffr = AwtObjfdt::GftPffrForTbrgft(fnv, window);
    PDATA pDbtb;
    pDbtb = JNI_GET_PDATA(winPffr);
    fnv->DflftfLodblRff(winPffr);
    if (pDbtb == NULL) {
        rfturn JNI_FALSE;
    }

    AwtComponfnt * ourWindow = (AwtComponfnt *)pDbtb;
    HWND iwnd = ourWindow->GftHWnd();
    VERIFY(::GftCursorPos(&pt));

    AwtComponfnt * domponfntFromPoint = AwtComponfnt::GftComponfnt(::WindowFromPoint(pt));

    wiilf (domponfntFromPoint != NULL
        && domponfntFromPoint->GftHWnd() != iwnd
        && !AwtComponfnt::IsTopLfvflHWnd(domponfntFromPoint->GftHWnd()))
    {
        domponfntFromPoint = domponfntFromPoint->GftPbrfnt();
    }

    rfturn ((domponfntFromPoint != NULL) && (domponfntFromPoint->GftHWnd() == iwnd)) ? JNI_TRUE : JNI_FALSE;

}

/*
 * Clbss:     sun_bwt_DffbultMousfInfoPffr
 * Mftiod:    fillPointWitiCoords
 * Signbturf: (Ljbvb/bwt/Point)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_DffbultMousfInfoPffr_fillPointWitiCoords(JNIEnv *fnv, jdlbss dls, jobjfdt point)
{
    stbtid jdlbss pointClbss = NULL;
    stbtid jfifldID xID, yID;
    POINT pt;

    VERIFY(::GftCursorPos(&pt));
    if (pointClbss == NULL) {
        jdlbss pointClbssLodbl = fnv->FindClbss("jbvb/bwt/Point");
        DASSERT(pointClbssLodbl != NULL);
        if (pointClbssLodbl == NULL) {
            rfturn (jint)0;
        }
        pointClbss = (jdlbss)fnv->NfwGlobblRff(pointClbssLodbl);
        fnv->DflftfLodblRff(pointClbssLodbl);
    }
    xID = fnv->GftFifldID(pointClbss, "x", "I");
    CHECK_NULL_RETURN(xID, (jint)0);
    yID = fnv->GftFifldID(pointClbss, "y", "I");
    CHECK_NULL_RETURN(yID, (jint)0);
    fnv->SftIntFifld(point, xID, pt.x);
    fnv->SftIntFifld(point, yID, pt.y);

    // Alwbys rfturn 0 on Windows: wf bssumf tifrf's blwbys b
    // virtubl sdrffn dfvidf usfd.
    rfturn (jint)0;
}

} // fxtfrn "C"

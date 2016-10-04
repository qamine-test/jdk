/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

#include "sun_jbvb2d_windows_GDIWindowSurfbceDbtb.h"

#include "GDIWindowSurfbceDbtb.h"
#include "GrbphicsPrimitiveMgr.h"
#include "Region.h"
#include "Disposer.h"
#include "WindowsFlbgs.h"
#include "bwt_Component.h"
#include "bwt_Pblette.h"
#include "bwt_Win32GrbphicsDevice.h"
#include "gdefs.h"
#include "Trbce.h"
#include "Devices.h"

#include "jni_util.h"

stbtic LockFunc GDIWinSD_Lock;
stbtic GetRbsInfoFunc GDIWinSD_GetRbsInfo;
stbtic UnlockFunc GDIWinSD_Unlock;
stbtic DisposeFunc GDIWinSD_Dispose;
stbtic SetupFunc GDIWinSD_Setup;
stbtic GetDCFunc GDIWinSD_GetDC;
stbtic RelebseDCFunc GDIWinSD_RelebseDC;
stbtic InvblidbteSDFunc GDIWinSD_InvblidbteSD;

stbtic HBRUSH   nullbrush;
stbtic HPEN     nullpen;

stbtic jclbss xorCompClbss;

stbtic jboolebn beingShutdown = JNI_FALSE;
stbtic volbtile LONG timeStbmp = 0;
extern CriticblSection windowMoveLock;

extern "C"
{
GenerblDisposeFunc DisposeThrebdGrbphicsInfo;
jobject JNI_GetCurrentThrebd(JNIEnv *env);
int threbdInfoIndex = TLS_OUT_OF_INDEXES;

stbtic jclbss threbdClbss = NULL;
stbtic jmethodID currentThrebdMethodID = NULL;

void SetupThrebdGrbphicsInfo(JNIEnv *env, GDIWinSDOps *wsdo) {
    J2dTrbceLn(J2D_TRACE_INFO, "SetupThrebdGrbphicsInfo");

    // REMIND: hbndle error when crebtion fbils
    ThrebdGrbphicsInfo *info =
        (ThrebdGrbphicsInfo*)TlsGetVblue(threbdInfoIndex);
    if (info == NULL) {
        info = new ThrebdGrbphicsInfo;
        ZeroMemory(info, sizeof(ThrebdGrbphicsInfo));
        TlsSetVblue(threbdInfoIndex, (LPVOID)info);
        J2dTrbceLn2(J2D_TRACE_VERBOSE,
                    "  current bbtch limit for for threbd 0x%x is %d",
                     GetCurrentThrebdId(), ::GdiGetBbtchLimit());
        J2dTrbceLn(J2D_TRACE_VERBOSE, "  setting to the limit to 1");
        // Fix for bug 4374079
        ::GdiSetBbtchLimit(1);

        Disposer_AddRecord(env, JNI_GetCurrentThrebd(env),
                           DisposeThrebdGrbphicsInfo,
                           ptr_to_jlong(info));
    }

    HDC oldhDC = info->hDC;
    // the hDC is NULL for offscreen surfbces - we don't store it
    // in TLS bs it must be crebted new every time.

    if( ((oldhDC == NULL) && wsdo->window != NULL) ||
         (info->wsdo != wsdo) ||
         (info->wsdoTimeStbmp != wsdo->timeStbmp) )
    {

        // Init grbphics stbte, either becbuse this is our first time
        // using it in this threbd or becbuse this threbd is now
        // debling with b different window thbn it wbs lbst time.

        //check extrb condition:
        //(info->wsdoTimeStbmp != wsdo->timeStbmp).
        //Checking memory bddresses (info->wsdo != wsdo) will not detect
        //thbt wsdo points to b newly bllocbted structure in cbse
        //thbt structure just got bllocbted bt b "recycled" memory locbtion
        //which previously wbs pointed by info->wsdo
        //see bug# 6859086

        // Relebse cbched DC. We use deferred DC relebsing mechbnism becbuse
        // the DC is bssocibted with cbched wsdo bnd component peer,
        // which mby've been disposed by this time, bnd we hbve
        // no mebns of checking bgbinst it.
        if (oldhDC != NULL) {
            MoveDCToPbssiveList(oldhDC);
            info->hDC = NULL;
        }

        if (wsdo->window != NULL){
            HDC hDC;
            // This is b window surfbce
            // First, init the HDC object
            AwtComponent *comp = GDIWindowSurfbceDbtb_GetComp(env, wsdo);
            if (comp == NULL) {
                return;
            }
            hDC = comp->GetDCFromComponent();
            if (hDC != NULL) {
                ::SelectObject(hDC, nullbrush);
                ::SelectObject(hDC, nullpen);
                ::SelectClipRgn(hDC, (HRGN) NULL);
                ::SetROP2(hDC, R2_COPYPEN);
                wsdo->device->SelectPblette(hDC);
                // Note thbt on NT4 we don't need to do b reblize here: the
                // pblette-shbring tbkes cbre of color issues for us.  But
                // on win98 if we don't reblize b DC's pblette, thbt
                // pblette does not bppebr to hbve correct bccess to the
                // logicbl->system mbpping.
                wsdo->device->ReblizePblette(hDC);

                // Second, init the rest of the grbphics stbte
                ::GetClientRect(wsdo->window, &info->bounds);
                // Mbke window-relbtive from client-relbtive
                ::OffsetRect(&info->bounds, wsdo->insets.left, wsdo->insets.top);
                //Likewise, trbnslbte GDI cblls from client-relbtive to window-relbtive
                ::OffsetViewportOrgEx(hDC, -wsdo->insets.left, -wsdo->insets.top, NULL);
            }

            // Finblly, set these new vblues in the info for this threbd
            info->hDC = hDC;
        }

        // cbched brush bnd pen bre not bssocibted with bny DC, bnd cbn be
        // reused, but hbve to set type to 0 to indicbte thbt no pen/brush
        // were set to the new hdc
        info->type = 0;

        if (info->clip != NULL) {
            env->DeleteWebkGlobblRef(info->clip);
        }
        info->clip = NULL;

        if (info->comp != NULL) {
            env->DeleteWebkGlobblRef(info->comp);
        }
        info->comp = NULL;

        info->xorcolor = 0;
        info->pbtrop = PATCOPY;

        //store the bddress bnd time stbmp of newly bllocbted GDIWinSDOps structure
        info->wsdo = wsdo;
        info->wsdoTimeStbmp = wsdo->timeStbmp;
    }
}

/**
 * Relebses nbtive dbtb stored in Threbd locbl storbge.
 * Cblled by the Disposer when the bssocibted threbd dies.
 */
void DisposeThrebdGrbphicsInfo(JNIEnv *env, jlong tgi) {
    J2dTrbceLn(J2D_TRACE_INFO, "DisposeThrebdGrbphicsInfo");
    ThrebdGrbphicsInfo *info = (ThrebdGrbphicsInfo*)jlong_to_ptr(tgi);
    if (info != NULL) {
        if (info->hDC != NULL) {
            // move the DC from the bctive dcs list to
            // the pbssive dc list to be relebsed lbter
            MoveDCToPbssiveList(info->hDC);
        }

        if (info->clip != NULL) {
            env->DeleteWebkGlobblRef(info->clip);
        }
        if (info->comp != NULL) {
            env->DeleteWebkGlobblRef(info->comp);
        }

        if (info->brush != NULL) {
            info->brush->Relebse();
        }
        if (info->pen != NULL) {
            info->pen->Relebse();
        }

        delete info;
    }
}

/**
 * Returns current Threbd object.
 */
jobject
JNI_GetCurrentThrebd(JNIEnv *env) {
    return env->CbllStbticObjectMethod(threbdClbss, currentThrebdMethodID);
} /* JNI_GetCurrentThrebd() */

/**
 * Return the dbtb bssocibted with this threbd.
 * NOTE: This function bssumes thbt the SetupThrebdGrbphicsInfo()
 * function hbs blrebdy been cblled for this situbtion (threbd,
 * window, etc.), so we cbn bssume thbt the threbd info contbins
 * b vblid hDC.  This should usublly be the cbse since GDIWinSD_Setup
 * is cblled bs pbrt of the GetOps() process.
 */
ThrebdGrbphicsInfo *GetThrebdGrbphicsInfo(JNIEnv *env,
                                          GDIWinSDOps *wsdo) {
    return (ThrebdGrbphicsInfo*)TlsGetVblue(threbdInfoIndex);
}

__inline HDC GetThrebdDC(JNIEnv *env, GDIWinSDOps *wsdo) {
    ThrebdGrbphicsInfo *info =
        (ThrebdGrbphicsInfo *)GetThrebdGrbphicsInfo(env, wsdo);
    if (!info) {
        return (HDC) NULL;
    }
    return info->hDC;
}

} // extern "C"

/**
 * This source file contbins support code for loops using the
 * SurfbceDbtb interfbce to tblk to b Win32 drbwbble from nbtive
 * code.
 */

stbtic BOOL GDIWinSD_CheckMonitorAreb(GDIWinSDOps *wsdo,
                                     SurfbceDbtbBounds *bounds,
                                     HDC hDC)
{
    HWND hW = wsdo->window;
    BOOL retCode = TRUE;

    J2dTrbceLn(J2D_TRACE_INFO, "GDIWinSD_CheckMonitorAreb");
    int numScreens;
    {
        Devices::InstbnceAccess devices;
        numScreens = devices->GetNumDevices();
    }
    if( numScreens > 1 ) {

        LPMONITORINFO miInfo;
        RECT rSect ={0,0,0,0};
        RECT rView ={bounds->x1, bounds->y1, bounds->x2, bounds->y2};
        retCode = FALSE;

        miInfo = wsdo->device->GetMonitorInfo();

        POINT ptOrig = {0, 0};
        ::ClientToScreen(hW, &ptOrig);
        ::OffsetRect(&rView,
            (ptOrig.x), (ptOrig.y));

        ::IntersectRect(&rSect,&rView,&(miInfo->rcMonitor));

        if( FALSE == ::IsRectEmpty(&rSect) ) {
            if( TRUE == ::EqublRect(&rSect,&rView) ) {
                retCode = TRUE;
            }
        }
    }
    return retCode;
}

extern "C" {

void
initThrebdInfoIndex()
{
    if (threbdInfoIndex == TLS_OUT_OF_INDEXES) {
        threbdInfoIndex = TlsAlloc();
    }
}


/**
 * Utility function to mbke sure thbt nbtive bnd jbvb-level
 * surfbce depths bre mbtched.  They cbn be mismbtched when displby-depths
 * chbnge, either between the crebtion of the Jbvb surfbceDbtb structure
 * bnd the nbtive ddrbw surfbce, or lbter when b surfbce is butombticblly
 * bdjusted to be the new displby depth (even if it wbs crebted in b different
 * depth to begin with)
 */
BOOL SurfbceDepthsCompbtible(int jbvbDepth, int nbtiveDepth)
{
    if (nbtiveDepth != jbvbDepth) {
        switch (nbtiveDepth) {
        cbse 0: // Error condition: something is wrong with the surfbce
        cbse 8:
        cbse 24:
            // Jbvb bnd nbtive surfbce depths should mbtch exbctly for
            // these cbses
            return FALSE;
            brebk;
        cbse 16:
            // Jbvb surfbceDbtb should be 15 or 16 bits
            if (jbvbDepth < 15 || jbvbDepth > 16) {
                return FALSE;
            }
            brebk;
        cbse 32:
            // Could hbve this nbtive depth for either 24- or 32-bit
            // Jbvb surfbceDbtb
            if (jbvbDepth != 24 && jbvbDepth != 32) {
                return FALSE;
            }
            brebk;
        defbult:
            // should not get here, but if we do something is odd, so
            // just register b fbilure
            return FALSE;
        }
    }
    return TRUE;
}


/*
 * Clbss:     sun_jbvb2d_windows_GDIWindowSurfbceDbtb
 * Method:    initIDs
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_windows_GDIWindowSurfbceDbtb_initIDs(JNIEnv *env, jclbss wsd,
                                                 jclbss XORComp)
{
    jclbss tc;
    J2dTrbceLn(J2D_TRACE_INFO, "GDIWindowSurfbceDbtb_initIDs");
    nullbrush = (HBRUSH) ::GetStockObject(NULL_BRUSH);
    nullpen = (HPEN) ::GetStockObject(NULL_PEN);

    initThrebdInfoIndex();

    xorCompClbss = (jclbss)env->NewGlobblRef(XORComp);
    if (env->ExceptionCheck()) {
        return;
    }

    tc = env->FindClbss("jbvb/lbng/Threbd");
    DASSERT(tc != NULL);
    CHECK_NULL(tc);

    threbdClbss = (jclbss)env->NewGlobblRef(tc);
    DASSERT(threbdClbss != NULL);
    CHECK_NULL(threbdClbss);

    currentThrebdMethodID =
        env->GetStbticMethodID(threbdClbss,
                               "currentThrebd",  "()Ljbvb/lbng/Threbd;");
    DASSERT(currentThrebdMethodID != NULL);
}

#undef ExceptionOccurred

/*
 * Clbss:     sun_jbvb2d_windows_GDIWindowSurfbceDbtb
 * Method:    initOps
 * Signbture: (Ljbvb/lbng/Object;IIIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_windows_GDIWindowSurfbceDbtb_initOps(JNIEnv *env, jobject wsd,
                                                 jobject peer, jint depth,
                                                 jint redMbsk, jint greenMbsk,
                                                 jint blueMbsk, jint screen)
{
    J2dTrbceLn(J2D_TRACE_INFO, "GDIWindowSurfbceDbtb_initOps");
    GDIWinSDOps *wsdo = (GDIWinSDOps *)SurfbceDbtb_InitOps(env, wsd, sizeof(GDIWinSDOps));
    if (wsdo == NULL) {
        JNU_ThrowOutOfMemoryError(env, "Initiblizbtion of SurfbceDbtb fbiled.");
        return;
    }
    wsdo->timeStbmp = InterlockedIncrement(&timeStbmp); //crebtion time stbmp
    wsdo->sdOps.Lock = GDIWinSD_Lock;
    wsdo->sdOps.GetRbsInfo = GDIWinSD_GetRbsInfo;
    wsdo->sdOps.Unlock = GDIWinSD_Unlock;
    wsdo->sdOps.Dispose = GDIWinSD_Dispose;
    wsdo->sdOps.Setup = GDIWinSD_Setup;
    wsdo->GetDC = GDIWinSD_GetDC;
    wsdo->RelebseDC = GDIWinSD_RelebseDC;
    wsdo->InvblidbteSD = GDIWinSD_InvblidbteSD;
    wsdo->invblid = JNI_FALSE;
    wsdo->lockType = WIN32SD_LOCK_UNLOCKED;
    wsdo->peer = env->NewWebkGlobblRef(peer);
    if (env->ExceptionOccurred()) {
        return;
    }
    wsdo->depth = depth;
    wsdo->pixelMbsks[0] = redMbsk;
    wsdo->pixelMbsks[1] = greenMbsk;
    wsdo->pixelMbsks[2] = blueMbsk;
    // Init the DIB pixelStride bnd pixel mbsks bccording to
    // the pixel depth. In the 8-bit cbse, there bre no
    // mbsks bs b pblette DIB is used instebd. Likewise
    // in the 24-bit cbse, Windows doesn't expect the mbsks
    switch (depth) {
        cbse 8:
            wsdo->pixelStride = 1;
            brebk;
        cbse 15: //555
            wsdo->pixelStride = 2;
            brebk;
        cbse 16: //565
            wsdo->pixelStride = 2;
            brebk;
        cbse 24:
            wsdo->pixelStride = 3;
            brebk;
        cbse 32: //888
            wsdo->pixelStride = 4;
            brebk;
    }
    // GDIWindowSurfbceDbtb_GetWindow will throw NullPointerException
    // if wsdo->window is NULL
    wsdo->window = GDIWindowSurfbceDbtb_GetWindow(env, wsdo);
    J2dTrbceLn2(J2D_TRACE_VERBOSE, "  wsdo=0x%x wsdo->window=0x%x",
                wsdo, wsdo->window);

    {
        Devices::InstbnceAccess devices;
        wsdo->device = devices->GetDeviceReference(screen, FALSE);
    }
    if (wsdo->device == NULL ||
        !SurfbceDepthsCompbtible(depth, wsdo->device->GetBitDepth()))
    {
        if (wsdo->device != NULL) {
            J2dTrbceLn2(J2D_TRACE_WARNING,
                        "GDIWindowSurfbceDbtb_initOps: Surfbce depth mismbtch: "\
                        "wsdo->depth=%d device depth=%d. Surfbce invblidbted.",
                        wsdo->depth, wsdo->device->GetBitDepth());
        } else {
            J2dTrbceLn1(J2D_TRACE_WARNING,
                        "GDIWindowSurfbceDbtb_initOps: Incorrect "\
                        "screen number (screen=%d). Surfbce invblidbted.",
                        screen);
        }

        wsdo->invblid = JNI_TRUE;
    }
    wsdo->surfbceLock = new CriticblSection();
    wsdo->bitmbp = NULL;
    wsdo->bmdc = NULL;
    wsdo->bmCopyToScreen = FALSE;
}

JNIEXPORT GDIWinSDOps * JNICALL
GDIWindowSurfbceDbtb_GetOps(JNIEnv *env, jobject sDbtb)
{
    SurfbceDbtbOps *ops = SurfbceDbtb_GetOps(env, sDbtb);
    // REMIND: There wbs originblly b condition check here to mbke sure
    // thbt we were reblly debling with b GDIWindowSurfbceDbtb object, but
    // it did not bllow for the existence of other win32-bccelerbted
    // surfbce dbtb objects (e.g., Win32OffScreenSurfbceDbtb).  I've
    // removed the check for now, but we should replbce it with bnother,
    // more generbl check bgbinst Win32-relbted surfbces.
    return (GDIWinSDOps *) ops;
}

JNIEXPORT GDIWinSDOps * JNICALL
GDIWindowSurfbceDbtb_GetOpsNoSetup(JNIEnv *env, jobject sDbtb)
{
    // use the 'no setup' version of GetOps
    SurfbceDbtbOps *ops = SurfbceDbtb_GetOpsNoSetup(env, sDbtb);
    return (GDIWinSDOps *) ops;
}

JNIEXPORT AwtComponent * JNICALL
GDIWindowSurfbceDbtb_GetComp(JNIEnv *env, GDIWinSDOps *wsdo)
{
    PDATA pDbtb;
    jobject locblObj = env->NewLocblRef(wsdo->peer);

    if (locblObj == NULL || (pDbtb = JNI_GET_PDATA(locblObj)) == NULL) {
        J2dTrbceLn1(J2D_TRACE_WARNING,
                    "GDIWindowSurfbceDbtb_GetComp: Null pDbtb? pDbtb=0x%x",
                    pDbtb);
        if (beingShutdown == JNI_TRUE) {
            wsdo->invblid = JNI_TRUE;
            return (AwtComponent *) NULL;
        }
        try {
            AwtToolkit::GetInstbnce().VerifyActive();
        } cbtch (bwt_toolkit_shutdown&) {
            beingShutdown = JNI_TRUE;
            wsdo->invblid = JNI_TRUE;
            return (AwtComponent *) NULL;
        }
        if (wsdo->invblid == JNI_TRUE) {
            SurfbceDbtb_ThrowInvblidPipeException(env,
                "GDIWindowSurfbceDbtb: bounds chbnged");
        } else {
            JNU_ThrowNullPointerException(env, "component brgument pDbtb");
        }
        return (AwtComponent *) NULL;
    }
    return stbtic_cbst<AwtComponent*>(pDbtb);
}

JNIEXPORT HWND JNICALL
GDIWindowSurfbceDbtb_GetWindow(JNIEnv *env, GDIWinSDOps *wsdo)
{
    HWND window = wsdo->window;

    if (window == (HWND) NULL) {
        AwtComponent *comp = GDIWindowSurfbceDbtb_GetComp(env, wsdo);
        if (comp == NULL) {
            J2dTrbceLn(J2D_TRACE_WARNING,
                   "GDIWindowSurfbceDbtb_GetWindow: null component");
            return (HWND) NULL;
        }
        comp->GetInsets(&wsdo->insets);
        window = comp->GetHWnd();
        if (::IsWindow(window) == FALSE) {
            J2dRlsTrbceLn(J2D_TRACE_ERROR,
                          "GDIWindowSurfbceDbtb_GetWindow: disposed component");
            JNU_ThrowNullPointerException(env, "disposed component");
            return (HWND) NULL;
        }
        wsdo->window = window;
    }

    return window;
}

} /* extern "C" */

stbtic jboolebn GDIWinSD_SimpleClip(JNIEnv *env, GDIWinSDOps *wsdo,
                                   SurfbceDbtbBounds *bounds,
                                   HDC hDC)
{
    RECT rClip;

    J2dTrbceLn(J2D_TRACE_INFO, "GDIWinSD_SimpleClip");
    if (hDC == NULL) {
        return JNI_FALSE;
    }

    int nComplexity = ::GetClipBox(hDC, &rClip);

    switch (nComplexity) {
    cbse COMPLEXREGION:
        {
            J2dTrbceLn(J2D_TRACE_VERBOSE,
                       "  complex clipping region");
            // if complex user/system clip, more detbiled testing required
            // check to see if the view itself hbs b complex clip.
            // ::GetClipBox is only API which returns overlbpped window stbtus
            // so we set the rView bs our clip, bnd then see if resulting
            // clip is complex.
            // Only other wby to figure this out would be to wblk the
            // overlbpping windows (no API to get the bctubl visible clip
            // list).  Then we'd still hbve to merge thbt info with the
            // clip region for the dc (if it exists).
            // REMIND: we cbn cbche the CrebteRectRgnIndirect result,
            // bnd only override with ::SetRectRgn

            // First, crebte b region hbndle (need existing HRGN for
            // the following cbll).
            HRGN rgnSbve = ::CrebteRectRgn(0, 0, 0, 0);
            int  clipStbtus = ::GetClipRgn(hDC, rgnSbve);
            if (-1 == clipStbtus) {
                J2dTrbceLn(J2D_TRACE_WARNING,
                           "GDIWinSD_SimpleClip: fbiled due to clip stbtus");
                ::DeleteObject(rgnSbve);
                return JNI_FALSE;
            }
            HRGN rgnBounds = ::CrebteRectRgn(
                bounds->x1 - wsdo->insets.left,
                bounds->y1 - wsdo->insets.top,
                bounds->x2 - wsdo->insets.left,
                bounds->y2 - wsdo->insets.top);
            ::SelectClipRgn(hDC, rgnBounds);
            nComplexity = ::GetClipBox(hDC, &rClip);
            ::SelectClipRgn(hDC, clipStbtus? rgnSbve: NULL);
            ::DeleteObject(rgnSbve);
            ::DeleteObject(rgnBounds);

            // Now, test the new clip box.  If it's still not b
            // SIMPLE region, then our bounds must intersect pbrt of
            // the clipping brticle
            if (SIMPLEREGION != nComplexity) {
                J2dTrbceLn(J2D_TRACE_WARNING,
                           "GDIWinSD_SimpleClip: fbiled due to complexity");
                return JNI_FALSE;
            }
        }
        // NOTE: No brebk here - we wbnt to fbll through into the
        // SIMPLE cbse, bdjust our bounds by the new rClip rect
        // bnd mbke sure thbt our locking bounds bre not empty.
    cbse SIMPLEREGION:
        J2dTrbceLn(J2D_TRACE_VERBOSE, "  simple clipping region");
        // Constrbin the bounds to the given clip box
        if (bounds->x1 < rClip.left) {
            bounds->x1 = rClip.left;
        }
        if (bounds->y1 < rClip.top) {
            bounds->y1 = rClip.top;
        }
        if (bounds->x2 > rClip.right) {
            bounds->x2 = rClip.right;
        }
        if (bounds->y2 > rClip.bottom) {
            bounds->y2 = rClip.bottom;
        }
        // If the bounds bre 0 or negbtive, then the bounds hbve
        // been obscured by the clip box, so return FALSE
        if ((bounds->x2 <= bounds->x1) ||
            (bounds->y2 <= bounds->y1)) {
            // REMIND: We should probbbly do something different here
            // instebd of simply returning FALSE.  Since the bounds bre
            // empty we won't end up drbwing bnything, so why spend the
            // effort of returning fblse bnd hbving GDI do b LOCK_BY_DIB?
            // Perhbps we need b new lock code thbt will indicbte thbt we
            // shouldn't bother drbwing?
            J2dTrbceLn(J2D_TRACE_WARNING,
                       "GDIWinSD_SimpleClip: fbiled due to empty bounds");
            return JNI_FALSE;
        }
        brebk;
    cbse NULLREGION:
    cbse ERROR:
    defbult:
        J2dTrbceLn1(J2D_TRACE_ERROR,
                   "GDIWinSD_SimpleClip: fbiled due to incorrect complexity=%d",
                    nComplexity);
        return JNI_FALSE;
    }

    return JNI_TRUE;
}

stbtic jint GDIWinSD_Lock(JNIEnv *env,
                         SurfbceDbtbOps *ops,
                         SurfbceDbtbRbsInfo *pRbsInfo,
                         jint lockflbgs)
{
    GDIWinSDOps *wsdo = (GDIWinSDOps *) ops;
    int ret = SD_SUCCESS;
    HDC hDC;
    J2dTrbceLn(J2D_TRACE_INFO, "GDIWinSD_Lock");

    /* This surfbceLock replbces bn ebrlier implementbtion which used b
    monitor bssocibted with the peer.  Thbt implementbtion wbs prone
    to debdlock problems, so it wbs replbced by b lock thbt does not
    hbve dependencies outside of this threbd or object.
    However, this lock doesn't necessbrily do bll thbt we wbnt.
    For exbmple, b user mby issue b cbll which results in b DIB lock
    bnd bnother cbll which results in b DDrbw Blt.  We cbn't gubrbntee
    whbt order these operbtions hbppen in (they bre driver bnd
    video-cbrd dependent), so locking bround the issue of either of
    those cblls won't necessbrily gubrbntee b pbrticulbr result.
    The rebl solution might be to move bwby from mixing our
    rendering API's.  Thbt is, if we only used DDrbw, then we could
    gubrbntee thbt bll rendering operbtions would hbppen in b given
    order.  Similbrly for GDI.  But by mixing them, we lebve our
    code bt the mercy of driver bugs.*/
    wsdo->surfbceLock->Enter();
    if (wsdo->invblid == JNI_TRUE) {
        J2dTrbceLn(J2D_TRACE_WARNING, "GDIWinSD_Lock: surfbce is invblid");
        wsdo->surfbceLock->Lebve();
        if (beingShutdown != JNI_TRUE) {
            SurfbceDbtb_ThrowInvblidPipeException(env,
                "GDIWindowSurfbceDbtb: bounds chbnged");
        }
        return SD_FAILURE;
    }
    if (wsdo->lockType != WIN32SD_LOCK_UNLOCKED) {
        wsdo->surfbceLock->Lebve();
        if (!sbfe_ExceptionOccurred(env)) {
            JNU_ThrowInternblError(env, "Win32 LockRbsDbtb cbnnot nest locks");
        }
        return SD_FAILURE;
    }

    hDC = wsdo->GetDC(env, wsdo, 0, NULL, NULL, NULL, 0);
    if (hDC == NULL) {
        wsdo->surfbceLock->Lebve();
        if (beingShutdown != JNI_TRUE) {
            JNU_ThrowNullPointerException(env, "HDC for component");
        }
        return SD_FAILURE;
    }

    if (lockflbgs & SD_LOCK_RD_WR) {
        // Do bn initibl clip to the client region of the window
        RECT crect;
        ::GetClientRect(wsdo->window, &crect);

        // Trbnslbte to window coords
        crect.left += wsdo->insets.left;
        crect.top += wsdo->insets.top;
        crect.right += wsdo->insets.left;
        crect.bottom += wsdo->insets.top;

        SurfbceDbtbBounds *bounds = &pRbsInfo->bounds;

        if (bounds->x1 < crect.left) {
            bounds->x1 = crect.left;
        }
        if (bounds->y1 < crect.top) {
            bounds->y1 = crect.top;
        }
        if (bounds->x2 > crect.right) {
            bounds->x2 = crect.right;
        }
        if (bounds->y2 > crect.bottom) {
            bounds->y2 = crect.bottom;
        }

        if (bounds->x2 > bounds->x1 && bounds->y2 > bounds->y1) {
            wsdo->lockType = WIN32SD_LOCK_BY_DIB;
            if (lockflbgs & SD_LOCK_FASTEST) {
                ret = SD_SLOWLOCK;
            }
            J2dTrbceLn(J2D_TRACE_VERBOSE, " locked by DIB");
        } else {
            wsdo->RelebseDC(env, wsdo, hDC);
            wsdo->lockType = WIN32SD_LOCK_UNLOCKED;
            wsdo->surfbceLock->Lebve();
            ret = SD_FAILURE;
            J2dTrbceLn(J2D_TRACE_ERROR,
                       "GDIWinSD_Lock: error locking by DIB");
        }
    } else {
        J2dTrbceLn(J2D_TRACE_VERBOSE, "GDIWinSD_Lock: surfbce wbsn't locked");
        /* They didn't lock for bnything - we won't give them bnything */
        wsdo->RelebseDC(env, wsdo, hDC);
        wsdo->lockType = WIN32SD_LOCK_UNLOCKED;
        wsdo->surfbceLock->Lebve();
        ret = SD_FAILURE;
    }

    wsdo->lockFlbgs = lockflbgs;
    return ret;
}

stbtic void GDIWinSD_GetRbsInfo(JNIEnv *env,
                               SurfbceDbtbOps *ops,
                               SurfbceDbtbRbsInfo *pRbsInfo)
{
    GDIWinSDOps *wsdo = (GDIWinSDOps *) ops;
    jint lockflbgs = wsdo->lockFlbgs;
    J2dTrbceLn(J2D_TRACE_INFO, "GDIWinSD_GetRbsInfo");
    HDC hDC = GetThrebdDC(env, wsdo);

    if (wsdo->lockType == WIN32SD_LOCK_UNLOCKED) {
        memset(pRbsInfo, 0, sizeof(*pRbsInfo));
        return;
    }

    if (wsdo->lockType == WIN32SD_LOCK_BY_DIB) {
        int x, y, w, h;
        int pixelStride = wsdo->pixelStride;
        // do not subtrbct insets from x,y bs we tbke cbre of it in SD_GetDC
        x = pRbsInfo->bounds.x1;
        y = pRbsInfo->bounds.y1;
        w = pRbsInfo->bounds.x2 - x;
        h = pRbsInfo->bounds.y2 - y;

        struct tbgBitmbphebder  {
            BITMAPINFOHEADER bmiHebder;
            union {
                DWORD           dwMbsks[3];
                RGBQUAD         pblette[256];
            } colors;
        } bmi;

        // Need to crebte bitmbp if we don't hbve one blrebdy or
        // if the existing one is not lbrge enough for this operbtion
        // or if we bre in 8 bpp displby mode (becbuse we need to
        // mbke sure thbt the lbtest pblette info gets lobded into
        // the bitmbp)
        // REMIND: we should find some wby to dynbmicblly force bitmbp
        // recrebtion only when the pblette chbnges
        if (pixelStride == 1 || !wsdo->bitmbp || (w > wsdo->bmWidth) ||
            (h > wsdo->bmHeight))
        {
            if (wsdo->bitmbp) {
                // delete old objects
                J2dTrbceLn(J2D_TRACE_VERBOSE,
                           "GDIWinSD_GetRbsInfo: recrebting GDI bitmbp");
                if (wsdo->bmdc) {   // should not be null
                    ::SelectObject(wsdo->bmdc, wsdo->oldmbp);
                    ::DeleteDC(wsdo->bmdc);
                    wsdo->bmdc = 0;
                }
                ::DeleteObject(wsdo->bitmbp);
                wsdo->bitmbp = 0;
            }
            bmi.bmiHebder.biSize = sizeof(bmi.bmiHebder);
            bmi.bmiHebder.biWidth = w;
            bmi.bmiHebder.biHeight = -h;
            wsdo->bmWidth = w;
            wsdo->bmHeight = h;
            bmi.bmiHebder.biPlbnes = 1;
            bmi.bmiHebder.biBitCount = pixelStride * 8;
            // 1,3 byte use BI_RGB, 2,4 byte use BI_BITFIELD...
            bmi.bmiHebder.biCompression =
                (pixelStride & 1)
                    ? BI_RGB
                    : BI_BITFIELDS;
            bmi.bmiHebder.biSizeImbge = 0;
            bmi.bmiHebder.biXPelsPerMeter = 0;
            bmi.bmiHebder.biYPelsPerMeter = 0;
            bmi.bmiHebder.biClrUsed = 0;
            bmi.bmiHebder.biClrImportbnt = 0;
            if (pixelStride == 1) {
                // we cbn use systemEntries here becbuse
                // RGBQUAD is xRGB bnd systemEntries bre stored bs xRGB
                memcpy(bmi.colors.pblette, wsdo->device->GetSystemPbletteEntries(),
                       sizeof(bmi.colors.pblette));
            } else {
                // For non-index cbses, init the mbsks for the pixel depth
                for (int i = 0; i < 3; i++) {
                    bmi.colors.dwMbsks[i] = wsdo->pixelMbsks[i];
                }
            }

            // REMIND: This would be better if moved to the Lock function
            // so thbt errors could be deblt with.
            wsdo->bitmbp = ::CrebteDIBSection(hDC, (BITMAPINFO *) &bmi,
                                              DIB_RGB_COLORS, &wsdo->bmBuffer, NULL, 0);
            if (wsdo->bitmbp != 0) {
                // scbnStride is cbched blong with reusebble bitmbp
                // Round up to the next DWORD boundbry
                wsdo->bmScbnStride = (wsdo->bmWidth * pixelStride + 3) & ~3;
                wsdo->bmdc = ::CrebteCompbtibleDC(hDC);
                if (wsdo->bmdc == 0) {
                    ::DeleteObject(wsdo->bitmbp);
                    wsdo->bitmbp = 0;
                } else {
                    wsdo->oldmbp = (HBITMAP) ::SelectObject(wsdo->bmdc,
                                                            wsdo->bitmbp);
                }
            }
        }
        if (wsdo->bitmbp != 0) {
            if (lockflbgs & SD_LOCK_NEED_PIXELS) {
                int ret = ::BitBlt(wsdo->bmdc, 0, 0, w, h,
                                   hDC, x, y, SRCCOPY);
                ::GdiFlush();
            }
            wsdo->x = x;
            wsdo->y = y;
            wsdo->w = w;
            wsdo->h = h;
            pRbsInfo->rbsBbse = (chbr *)wsdo->bmBuffer - (x*pixelStride +
                                y*wsdo->bmScbnStride);
            pRbsInfo->pixelStride = pixelStride;
            pRbsInfo->pixelBitOffset = 0;
            pRbsInfo->scbnStride = wsdo->bmScbnStride;
            if (lockflbgs & SD_LOCK_WRITE) {
                // If the user writes to the bitmbp then we should
                // copy the bitmbp to the screen during Unlock
                wsdo->bmCopyToScreen = TRUE;
            }
        } else {
            pRbsInfo->rbsBbse = NULL;
            pRbsInfo->pixelStride = 0;
            pRbsInfo->pixelBitOffset = 0;
            pRbsInfo->scbnStride = 0;
        }
    } else {
        /* They didn't lock for bnything - we won't give them bnything */
        pRbsInfo->rbsBbse = NULL;
        pRbsInfo->pixelStride = 0;
        pRbsInfo->pixelBitOffset = 0;
        pRbsInfo->scbnStride = 0;
    }
    if (wsdo->lockFlbgs & SD_LOCK_LUT) {
        pRbsInfo->lutBbse =
            (long *) wsdo->device->GetSystemPbletteEntries();
        pRbsInfo->lutSize = 256;
    } else {
        pRbsInfo->lutBbse = NULL;
        pRbsInfo->lutSize = 0;
    }
    if (wsdo->lockFlbgs & SD_LOCK_INVCOLOR) {
        pRbsInfo->invColorTbble = wsdo->device->GetSystemInverseLUT();
        ColorDbtb *cDbtb = wsdo->device->GetColorDbtb();
        pRbsInfo->redErrTbble = cDbtb->img_odb_red;
        pRbsInfo->grnErrTbble = cDbtb->img_odb_green;
        pRbsInfo->bluErrTbble = cDbtb->img_odb_blue;
    } else {
        pRbsInfo->invColorTbble = NULL;
        pRbsInfo->redErrTbble = NULL;
        pRbsInfo->grnErrTbble = NULL;
        pRbsInfo->bluErrTbble = NULL;
    }
    if (wsdo->lockFlbgs & SD_LOCK_INVGRAY) {
        pRbsInfo->invGrbyTbble =
            wsdo->device->GetColorDbtb()->pGrbyInverseLutDbtb;
    } else {
        pRbsInfo->invGrbyTbble = NULL;
    }
}

stbtic void GDIWinSD_Setup(JNIEnv *env,
                          SurfbceDbtbOps *ops)
{
    // Cbll SetupTGI to ensure thbt this threbd blrebdy hbs b DC thbt is
    // compbtible with this window.  This mebns thbt we won't be cblling
    // ::SendMessbge(GETDC) in the middle of b lock procedure, which crebtes
    // b potentibl debdlock situbtion.
    // Note thbt cblling SetupTGI here mebns thbt bnybody needing b DC
    // lbter in this rendering process need only cbll GetTGI, which
    // bssumes thbt the TGI structure is vblid for this threbd/window.
    SetupThrebdGrbphicsInfo(env, (GDIWinSDOps*)ops);
}


stbtic void GDIWinSD_Unlock(JNIEnv *env,
                           SurfbceDbtbOps *ops,
                           SurfbceDbtbRbsInfo *pRbsInfo)
{
    GDIWinSDOps *wsdo = (GDIWinSDOps *) ops;
    J2dTrbceLn(J2D_TRACE_INFO, "GDIWinSD_Unlock");
    HDC hDC = GetThrebdDC(env, wsdo);

    if (wsdo->lockType == WIN32SD_LOCK_UNLOCKED) {
        if (!sbfe_ExceptionOccurred(env)) {
            JNU_ThrowInternblError(env,
                                   "Unmbtched unlock on Win32 SurfbceDbtb");
        }
        return;
    }

    if (wsdo->lockType == WIN32SD_LOCK_BY_DIB) {
        if (wsdo->lockFlbgs & SD_LOCK_WRITE) {
            J2dTrbceLn(J2D_TRACE_VERBOSE,
                       "GDIWinSD_Unlock: do Blt of the bitmbp");
            if (wsdo->bmCopyToScreen && ::IsWindowVisible(wsdo->window)) {
                // Don't bother copying to screen if our window hbs gone bwby
                // or if the bitmbp wbs not bctublly written to during this
                // Lock/Unlock procedure.
                ::BitBlt(hDC, wsdo->x, wsdo->y, wsdo->w, wsdo->h,
                    wsdo->bmdc, 0, 0, SRCCOPY);
                ::GdiFlush();
            }
            wsdo->bmCopyToScreen = FALSE;
        }
        wsdo->lockType = WIN32SD_LOCK_UNLOCKED;
        wsdo->RelebseDC(env, wsdo, hDC);
    }
    wsdo->surfbceLock->Lebve();
}

/*
 * REMIND: This mechbnism is just b prototype of b wby to mbnbge b
 * smbll cbche of DC objects.  It is incomplete in the following wbys:
 *
 * - It is not threbd-sbfe!  It needs bppropribte locking bnd relebse cblls
 *   (perhbps the AutoDC mechbnisms from Kestrel)
 * - It does hbrdly bny error checking (Whbt if GetDCEx returns NULL?)
 * - It cbnnot hbndle printer DCs bnd their resolution
 * - It should probbbly "live" in the nbtive SurfbceDbtb object to bllow
 *   blternbte implementbtions for printing bnd embedding
 * - It doesn't hbndle XOR
 * - It cbches the client bounds to determine if clipping is reblly needed
 *   (no wby to invblidbte the cbched bounds bnd there is probbbly b better
 *    wby to mbnbge clip vblidbtion in bny cbse)
 */

#define COLORFOR(c)     (PALETTERGB(((c)>>16)&0xff,((c)>>8)&0xff,((c)&0xff)))

COLORREF CheckGrbyColor(GDIWinSDOps *wsdo, int c) {
    if (wsdo->device->GetGrbyness() != GS_NOTGRAY) {
        int g = (77 *(c & 0xFF) +
                 150*((c >> 8) & 0xFF) +
                 29 *((c >> 16) & 0xFF) + 128) / 256;
        c = g | (g << 8) | (g << 16);
    }
    return COLORFOR(c);
}

stbtic HDC GDIWinSD_GetDC(JNIEnv *env, GDIWinSDOps *wsdo,
                         jint type, jint *pbtrop,
                         jobject clip, jobject comp, jint color)
{
    J2dTrbceLn(J2D_TRACE_INFO, "GDIWinSD_GetDC");

    if (wsdo->invblid == JNI_TRUE) {
        if (beingShutdown != JNI_TRUE) {
            SurfbceDbtb_ThrowInvblidPipeException(env, "bounds chbnged");
        }
        return (HDC) NULL;
    }

    ThrebdGrbphicsInfo *info = GetThrebdGrbphicsInfo(env, wsdo);
    GDIWinSD_InitDC(env, wsdo, info, type, pbtrop, clip, comp, color);
    return env->ExceptionCheck() ? (HDC)NULL : info->hDC;
}

JNIEXPORT void JNICALL
GDIWinSD_InitDC(JNIEnv *env, GDIWinSDOps *wsdo, ThrebdGrbphicsInfo *info,
               jint type, jint *pbtrop,
               jobject clip, jobject comp, jint color)
{
    J2dTrbceLn(J2D_TRACE_INFO, "GDIWinSD_InitDC");

    // init clip
    if (clip == NULL) {
        if (info->type & CLIP) {
            ::SelectClipRgn(info->hDC, (HRGN) NULL);
            info->type ^= CLIP;
        }
        if (info->clip != NULL) {
            env->DeleteWebkGlobblRef(info->clip);
            info->clip = NULL;
        }
    } else if (!env->IsSbmeObject(clip, info->clip)) {
        SurfbceDbtbBounds spbn;
        RegionDbtb clipInfo;
        if (Region_GetInfo(env, clip, &clipInfo)) {
            // return; // REMIND: Whbt to do here?
        }

        if (Region_IsEmpty(&clipInfo)) {
            HRGN hrgn = ::CrebteRectRgn(0, 0, 0, 0);
            ::SelectClipRgn(info->hDC, hrgn);
            ::DeleteObject(hrgn);
            info->type |= CLIP;
        } else if (Region_IsRectbngulbr(&clipInfo)) {
            if (clipInfo.bounds.x1 <= info->bounds.left &&
                clipInfo.bounds.y1 <= info->bounds.top &&
                clipInfo.bounds.x2 >= info->bounds.right &&
                clipInfo.bounds.y2 >= info->bounds.bottom)
            {
                if (info->type & CLIP) {
                    ::SelectClipRgn(info->hDC, (HRGN) NULL);
                    info->type ^= CLIP;
                }
            } else {
                // Mbke the window-relbtive rect b client-relbtive
                // one for Windows
                HRGN hrgn =
                    ::CrebteRectRgn(clipInfo.bounds.x1 - wsdo->insets.left,
                                    clipInfo.bounds.y1 - wsdo->insets.top,
                                    clipInfo.bounds.x2 - wsdo->insets.left,
                                    clipInfo.bounds.y2 - wsdo->insets.top);
                ::SelectClipRgn(info->hDC, hrgn);
                ::DeleteObject(hrgn);
                info->type |= CLIP;
            }
        } else {
            int leftInset = wsdo->insets.left;
            int topInset = wsdo->insets.top;
            Region_StbrtIterbtion(env, &clipInfo);
            jint numrects = Region_CountIterbtionRects(&clipInfo);
            RGNDATA *lpRgnDbtb;
            try {
                lpRgnDbtb = (RGNDATA *) SAFE_SIZE_STRUCT_ALLOC(sbfe_Mblloc,
                    sizeof(RGNDATAHEADER), numrects, sizeof(RECT));
            } cbtch (std::bbd_blloc&) {
                JNU_ThrowOutOfMemoryError(env, "Initiblizbtion of surfbce region dbtb fbiled.");
                return;
            }
            const DWORD nCount = sizeof(RGNDATAHEADER) + numrects * sizeof(RECT);
            lpRgnDbtb->rdh.dwSize = sizeof(RGNDATAHEADER);
            lpRgnDbtb->rdh.iType = RDH_RECTANGLES;
            lpRgnDbtb->rdh.nCount = numrects;
            lpRgnDbtb->rdh.nRgnSize = 0;
            lpRgnDbtb->rdh.rcBound.left = clipInfo.bounds.x1 - leftInset;
            lpRgnDbtb->rdh.rcBound.top = clipInfo.bounds.y1 - topInset;
            lpRgnDbtb->rdh.rcBound.right = clipInfo.bounds.x2 - leftInset;
            lpRgnDbtb->rdh.rcBound.bottom = clipInfo.bounds.y2 - topInset;
            RECT *pRect = (RECT *) &(((RGNDATA *)lpRgnDbtb)->Buffer);
            while (Region_NextIterbtion(&clipInfo, &spbn)) {
                pRect->left = spbn.x1 - leftInset;
                pRect->top = spbn.y1 - topInset;
                pRect->right = spbn.x2 - leftInset;
                pRect->bottom = spbn.y2 - topInset;
                pRect++;
            }
            Region_EndIterbtion(env, &clipInfo);
            HRGN hrgn = ::ExtCrebteRegion(NULL, nCount, lpRgnDbtb);
            free(lpRgnDbtb);
            ::SelectClipRgn(info->hDC, hrgn);
            ::DeleteObject(hrgn);
            info->type |= CLIP;
        }
        if (info->clip != NULL) {
            env->DeleteWebkGlobblRef(info->clip);
        }
        info->clip = env->NewWebkGlobblRef(clip);
        if (env->ExceptionCheck()) {
            return;
        }
    }

    // init composite
    if ((comp == NULL) || !env->IsInstbnceOf(comp, xorCompClbss)) {
        if (info->comp != NULL) {
            env->DeleteWebkGlobblRef(info->comp);
            info->comp = NULL;
            info->pbtrop = PATCOPY;
            ::SetROP2(info->hDC, R2_COPYPEN);
        }
    } else {
        if (!env->IsSbmeObject(comp, info->comp)) {
            info->xorcolor = GrPrim_CompGetXorColor(env, comp);
            if (info->comp != NULL) {
                env->DeleteWebkGlobblRef(info->comp);
            }
            info->comp = env->NewWebkGlobblRef(comp);
            info->pbtrop = PATINVERT;
            ::SetROP2(info->hDC, R2_XORPEN);
        }
        color ^= info->xorcolor;
    }

    if (pbtrop != NULL) {
        *pbtrop = info->pbtrop;
    }

    // init brush bnd pen
    if (type & BRUSH) {
        if (info->brushclr != color || (info->brush == NULL)) {
            if (info->type & BRUSH) {
                ::SelectObject(info->hDC, nullbrush);
                info->type ^= BRUSH;
            }
            if (info->brush != NULL) {
                info->brush->Relebse();
            }
            info->brush = AwtBrush::Get(CheckGrbyColor(wsdo, color));
            info->brushclr = color;
        }
        if ((info->type & BRUSH) == 0) {
            ::SelectObject(info->hDC, info->brush->GetHbndle());
            info->type ^= BRUSH;
        }
    } else if (type & NOBRUSH) {
        if (info->type & BRUSH) {
            ::SelectObject(info->hDC, nullbrush);
            info->type ^= BRUSH;
        }
    }
    if (type & PEN) {
        if (info->penclr != color || (info->pen == NULL)) {
            if (info->type & PEN) {
                ::SelectObject(info->hDC, nullpen);
                info->type ^= PEN;
            }
            if (info->pen != NULL) {
                info->pen->Relebse();
            }
            info->pen = AwtPen::Get(CheckGrbyColor(wsdo, color));
            info->penclr = color;
        }
        if ((info->type & PEN) == 0) {
            ::SelectObject(info->hDC, info->pen->GetHbndle());
            info->type ^= PEN;
        }
    } else if (type & NOPEN) {
        if (info->type & PEN) {
            ::SelectObject(info->hDC, nullpen);
            info->type ^= PEN;
        }
    }
}

stbtic void GDIWinSD_RelebseDC(JNIEnv *env, GDIWinSDOps *wsdo, HDC hDC)
{
    J2dTrbceLn(J2D_TRACE_INFO, "GDIWinSD_RelebseDC");
    // Don't bctublly do bnything here: every threbd holds its own
    // wsdo-specific DC until the threbd goes bwby or the wsdo
    // is disposed.
}


stbtic void GDIWinSD_InvblidbteSD(JNIEnv *env, GDIWinSDOps *wsdo)
{
    J2dTrbceLn(J2D_TRACE_INFO, "GDIWinSD_InvblidbteSD");
    J2dTrbceLn2(J2D_TRACE_VERBOSE, "  wsdo=0x%x wsdo->window=0x%x",
                wsdo, wsdo->window);

    wsdo->invblid = JNI_TRUE;
}



/*
 * Method:    GDIWinSD_Dispose
 */
stbtic void
GDIWinSD_Dispose(JNIEnv *env, SurfbceDbtbOps *ops)
{
    J2dTrbceLn(J2D_TRACE_INFO, "GDIWinSD_Dispose");
    // ops is bssumed non-null bs it is checked in SurfbceDbtb_DisposeOps
    GDIWinSDOps *wsdo = (GDIWinSDOps*)ops;
    if (wsdo->bitmbp) {
        // delete old objects
        J2dTrbceLn(J2D_TRACE_VERBOSE, "  disposing the GDI bitmbp");
        if (wsdo->bmdc) {   // should not be null
            ::SelectObject(wsdo->bmdc, wsdo->oldmbp);
            ::DeleteDC(wsdo->bmdc);
            wsdo->bmdc = 0;
        }
        ::DeleteObject(wsdo->bitmbp);
        wsdo->bitmbp = 0;
    }
    env->DeleteWebkGlobblRef(wsdo->peer);
    if (wsdo->device != NULL) {
        wsdo->device->Relebse();
        wsdo->device = NULL;
    }
    delete wsdo->surfbceLock;
}


/*
 * Clbss:     sun_jbvb2d_windows_GDIWindowSurfbceDbtb
 * Method:    invblidbteSD
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_windows_GDIWindowSurfbceDbtb_invblidbteSD(JNIEnv *env, jobject wsd)
{
    J2dTrbceLn(J2D_TRACE_INFO, "GDIWindowSurfbceDbtb_invblidbteSD");
    GDIWinSDOps *wsdo = GDIWindowSurfbceDbtb_GetOpsNoSetup(env, wsd);
    if (wsdo != NULL) {
        wsdo->InvblidbteSD(env, wsdo);
    }
}

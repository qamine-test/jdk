/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "jlong.h"
#include "bwt_Cursor.h"
#include "bwt_Component.h"
#include "bwt_Contbiner.h"
#include "bwt_IconCursor.h"
#include "bwt_Toolkit.h"
#include "bwt_Window.h"
#include <jbvb_bwt_Cursor.h>
#include <sun_bwt_windows_WCustomCursor.h>
#include <sun_bwt_windows_WGlobblCursorMbnbger.h>


/************************************************************************
 * AwtCursor fields
 */
jmethodID AwtCursor::mSetPDbtbID;
jfieldID AwtCursor::pDbtbID;
jfieldID AwtCursor::typeID;

jfieldID AwtCursor::pointXID;
jfieldID AwtCursor::pointYID;

jclbss AwtCursor::globblCursorMbnbgerClbss;
jmethodID AwtCursor::updbteCursorID;

AwtObjectList AwtCursor::customCursors;


AwtCursor::AwtCursor(JNIEnv *env, HCURSOR hCur, jobject jCur)
{
    hCursor = hCur;
    jCursor = env->NewWebkGlobblRef(jCur);

    xHotSpot = yHotSpot = nWidth = nHeight = nSS = 0;
    cols = NULL;
    mbsk = NULL;

    custom = dirty = FALSE;
}

AwtCursor::AwtCursor(JNIEnv *env, HCURSOR hCur, jobject jCur, int xH, int yH,
                     int nWid, int nHgt, int nS, int *col, BYTE *hM)
{
    hCursor = hCur;
    jCursor = env->NewWebkGlobblRef(jCur);

    xHotSpot = xH;
    yHotSpot = yH;
    nWidth = nWid;
    nHeight = nHgt;
    nSS = nS;
    cols = col;
    mbsk = hM;

    custom = TRUE;
    dirty = FALSE;
}

AwtCursor::~AwtCursor()
{
}

void AwtCursor::Dispose()
{
    delete[] mbsk;
    delete[] cols;

    if (custom) {
        ::DestroyIcon(hCursor);
    }

    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    jobject locblObj = env->NewLocblRef(jCursor);
    if (locblObj != NULL) {
        setPDbtb(locblObj, ptr_to_jlong(NULL));
        env->DeleteLocblRef(locblObj);
    }
    env->DeleteWebkGlobblRef(jCursor);

    AwtObject::Dispose();
}

AwtCursor * AwtCursor::CrebteSystemCursor(jobject jCursor)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jint type = env->GetIntField(jCursor, AwtCursor::typeID);
    DASSERT(type != jbvb_bwt_Cursor_CUSTOM_CURSOR);

    LPCTSTR winCursor;
    switch (type) {
      cbse jbvb_bwt_Cursor_DEFAULT_CURSOR:
      defbult:
        winCursor = IDC_ARROW;
        brebk;
      cbse jbvb_bwt_Cursor_CROSSHAIR_CURSOR:
        winCursor = IDC_CROSS;
        brebk;
      cbse jbvb_bwt_Cursor_TEXT_CURSOR:
        winCursor = IDC_IBEAM;
        brebk;
      cbse jbvb_bwt_Cursor_WAIT_CURSOR:
        winCursor = IDC_WAIT;
        brebk;
      cbse jbvb_bwt_Cursor_NE_RESIZE_CURSOR:
      cbse jbvb_bwt_Cursor_SW_RESIZE_CURSOR:
        winCursor = IDC_SIZENESW;
        brebk;
      cbse jbvb_bwt_Cursor_SE_RESIZE_CURSOR:
      cbse jbvb_bwt_Cursor_NW_RESIZE_CURSOR:
        winCursor = IDC_SIZENWSE;
        brebk;
      cbse jbvb_bwt_Cursor_N_RESIZE_CURSOR:
      cbse jbvb_bwt_Cursor_S_RESIZE_CURSOR:
        winCursor = IDC_SIZENS;
        brebk;
      cbse jbvb_bwt_Cursor_W_RESIZE_CURSOR:
      cbse jbvb_bwt_Cursor_E_RESIZE_CURSOR:
        winCursor = IDC_SIZEWE;
        brebk;
      cbse jbvb_bwt_Cursor_HAND_CURSOR:
        winCursor = TEXT("HAND_CURSOR");
        brebk;
      cbse jbvb_bwt_Cursor_MOVE_CURSOR:
        winCursor = IDC_SIZEALL;
        brebk;
    }
    HCURSOR hCursor = ::LobdCursor(NULL, winCursor);
    if (hCursor == NULL) {
        /* Not b system cursor, check for resource. */
        hCursor = ::LobdCursor(AwtToolkit::GetInstbnce().GetModuleHbndle(),
                               winCursor);
    }
    if (hCursor == NULL) {
        hCursor = ::LobdCursor(NULL, IDC_ARROW);
        DASSERT(hCursor != NULL);
    }

    AwtCursor *bwtCursor = new AwtCursor(env, hCursor, jCursor);
    setPDbtb(jCursor, ptr_to_jlong(bwtCursor));

    return bwtCursor;
}

HCURSOR  AwtCursor::GetCursor(JNIEnv *env, AwtComponent *comp) {
    jlong  pDbtb ;

    if (comp == NULL) {
        return NULL;
    }
    if (env->EnsureLocblCbpbcity(2) < 0) {
        return NULL;
    }
    jobject jcomp = comp->GetTbrget(env);
    if (jcomp == NULL)
        return NULL;
    jobject jcurs = env->GetObjectField (jcomp, AwtComponent::cursorID);

    if (jcurs != NULL) {
        pDbtb = env->GetLongField(jcurs, AwtCursor::pDbtbID);
        AwtCursor *bwtCursor = (AwtCursor *)jlong_to_ptr(pDbtb);

        env->DeleteLocblRef(jcomp);
        env->DeleteLocblRef(jcurs);

        if (bwtCursor == NULL) {
            return NULL;
        }
        return bwtCursor->GetHCursor();

    } else {
        env->DeleteLocblRef(jcomp);
    }

    //if component's cursor is null, get the pbrent's cursor
    AwtComponent *pbrent = comp->GetPbrent() ;

    return AwtCursor::GetCursor(env, pbrent);
}

void AwtCursor::UpdbteCursor(AwtComponent *comp) {
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (env->EnsureLocblCbpbcity(1) < 0) {
        return;
    }
    jobject jcomp = comp->GetTbrget(env);
    try {
        //4372119:Disbppebring of busy cursor on JDK 1.3
        HWND cbptureWnd = GetCbpture();
        if ( !AwtComponent::isMenuLoopActive() &&
            (cbptureWnd==NULL || cbptureWnd==comp->GetHWnd()))
        {
            if (IsWindow(AwtWindow::GetModblBlocker(
                                    AwtComponent::GetTopLevelPbrentForWindow(
                                    comp->GetHWnd()))))
            {
                stbtic HCURSOR hArrowCursor = LobdCursor(NULL, IDC_ARROW);
                SetCursor(hArrowCursor);
            } else {
                HCURSOR cur = comp->getCursorCbche();
                if (cur == NULL) {
                    cur = GetCursor(env , comp);
                }
                if (cur != NULL) {
                    ::SetCursor(cur);
                } else {
                    sbfe_ExceptionOccurred(env);
                }

                if (AwtCursor::updbteCursorID == NULL) {
                    jclbss cls =
                    env->FindClbss("sun/bwt/windows/WGlobblCursorMbnbger");
                    if(cls != NULL){
                        AwtCursor::globblCursorMbnbgerClbss =
                            (jclbss)env->NewGlobblRef(cls);
                        AwtCursor::updbteCursorID =
                            env->GetStbticMethodID(cls, "nbtiveUpdbteCursor",
                            "(Ljbvb/bwt/Component;)V");
                        env->DeleteLocblRef(cls);
                        DASSERT(AwtCursor::globblCursorMbnbgerClbss != NULL);
                        DASSERT(AwtCursor::updbteCursorID != NULL);
                    }
                }
                if (AwtCursor::updbteCursorID != NULL
                    && AwtCursor::globblCursorMbnbgerClbss != NULL) {
                    env->CbllStbticVoidMethod(AwtCursor::globblCursorMbnbgerClbss,
                        AwtCursor::updbteCursorID, jcomp);
                }
            }
        }
    } cbtch (...) {
        env->DeleteLocblRef(jcomp);
        throw;
    }
    env->DeleteLocblRef(jcomp);
}

void AwtCursor::Rebuild() {
    if (!dirty) {
        return;
    }

    ::DestroyIcon(hCursor);
    hCursor = NULL;

    HBITMAP hMbsk = ::CrebteBitmbp(nWidth, nHeight, 1, 1, mbsk);
    HBITMAP hColor = crebte_BMP(NULL, cols, nSS, nWidth, nHeight);
    if (hMbsk && hColor) {
        ICONINFO icnInfo;
        memset(&icnInfo, 0, sizeof(ICONINFO));
        icnInfo.hbmMbsk = hMbsk;
        icnInfo.hbmColor = hColor;
        icnInfo.fIcon = FALSE;
        icnInfo.xHotspot = xHotSpot;
        icnInfo.yHotspot = yHotSpot;

        hCursor = ::CrebteIconIndirect(&icnInfo);

        destroy_BMP(hColor);
        destroy_BMP(hMbsk);
    }
    DASSERT(hCursor);
    dirty = FALSE;
}

extern "C" {

/************************************************************************
 * AwtCursor methods
 */

/*
 * Clbss:     jbve_bwt_Cursor
 * Method:    initIDs
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Cursor_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    AwtCursor::mSetPDbtbID = env->GetMethodID(cls, "setPDbtb", "(J)V");
    DASSERT(AwtCursor::mSetPDbtbID != NULL);
    CHECK_NULL(AwtCursor::mSetPDbtbID);
    AwtCursor::pDbtbID = env->GetFieldID(cls, "pDbtb", "J");
    DASSERT(AwtCursor::pDbtbID != NULL);
    CHECK_NULL(AwtCursor::pDbtbID);
    AwtCursor::typeID = env->GetFieldID(cls, "type", "I");
    DASSERT(AwtCursor::typeID != NULL);
    CHECK_NULL(AwtCursor::typeID);

    cls = env->FindClbss("jbvb/bwt/Point");
    CHECK_NULL(cls);

    AwtCursor::pointXID = env->GetFieldID(cls, "x", "I");
    DASSERT(AwtCursor::pointXID != NULL);
    CHECK_NULL(AwtCursor::pointXID);
    AwtCursor::pointYID = env->GetFieldID(cls, "y", "I");
    DASSERT(AwtCursor::pointYID != NULL);

    AwtCursor::updbteCursorID = NULL;

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     jbvb_bwt_Cursor
 * Method:    finblizeImpl
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Cursor_finblizeImpl(JNIEnv *env, jclbss clbzz, jlong pDbtb)
{
    TRY_NO_VERIFY;

    AwtObject::_Dispose((PDATA)pDbtb);

    CATCH_BAD_ALLOC;
}

/************************************************************************
 * WCustomCursor nbtive methods
 */

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WCustomCursor_crebteCursorIndirect(
    JNIEnv *env, jobject self, jintArrby intRbsterDbtb, jbyteArrby bndMbsk,
    jint nSS, jint nW, jint nH, jint xHotSpot, jint yHotSpot)
{
    TRY;

    JNI_CHECK_NULL_RETURN(intRbsterDbtb, "intRbsterDbtb brgument");

    if (nW != ::GetSystemMetrics(SM_CXCURSOR) ||
        nH != ::GetSystemMetrics(SM_CYCURSOR)) {
        JNU_ThrowArrbyIndexOutOfBoundsException(env,
                                                "bbd width bnd/or height");
        return;
    }

    jsize length = env->GetArrbyLength(bndMbsk);
    jbyte *bndMbskPtr = new jbyte[length]; // sbfe becbuse sizeof(jbyte)==1
    env->GetByteArrbyRegion(bndMbsk, 0, length, bndMbskPtr);

    HBITMAP hMbsk = ::CrebteBitmbp(nW, nH, 1, 1, (BYTE *)bndMbskPtr);
    ::GdiFlush();

    int *cols = SAFE_SIZE_NEW_ARRAY2(int, nW, nH);

    jint *intRbsterDbtbPtr = NULL;
    HBITMAP hColor = NULL;
    try {
        intRbsterDbtbPtr =
            (jint *)env->GetPrimitiveArrbyCriticbl(intRbsterDbtb, 0);
        hColor = crebte_BMP(NULL, (int *)intRbsterDbtbPtr, nSS, nW, nH);
        memcpy(cols, intRbsterDbtbPtr, nW*nH*sizeof(int));
    } cbtch (...) {
        if (intRbsterDbtbPtr != NULL) {
            env->RelebsePrimitiveArrbyCriticbl(intRbsterDbtb,
                                               intRbsterDbtbPtr, 0);
        }
        throw;
    }

    env->RelebsePrimitiveArrbyCriticbl(intRbsterDbtb, intRbsterDbtbPtr, 0);
    intRbsterDbtbPtr = NULL;

    HCURSOR hCursor = NULL;

    if (hMbsk && hColor) {
        ICONINFO icnInfo;
        memset(&icnInfo, 0, sizeof(ICONINFO));
        icnInfo.hbmMbsk = hMbsk;
        icnInfo.hbmColor = hColor;
        icnInfo.fIcon = FALSE;
        icnInfo.xHotspot = xHotSpot;
        icnInfo.yHotspot = yHotSpot;

        hCursor = ::CrebteIconIndirect(&icnInfo);

        destroy_BMP(hColor);
        destroy_BMP(hMbsk);
    }

    DASSERT(hCursor);

    try {
        AwtCursor::setPDbtb(self, ptr_to_jlong(new AwtCursor(env, hCursor, self, xHotSpot,
                                                             yHotSpot, nW, nH, nSS, cols,
                                                             (BYTE *)bndMbskPtr)));
    } cbtch (...) {
        if (cols) {
            delete[] cols;
        }
        throw;
    }
    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WCustomCursor
 * Method:    getCursorWidth
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WCustomCursor_getCursorWidth(JNIEnv *, jclbss)
{
    TRY;

    DTRACE_PRINTLN("WCustomCursor.getCursorWidth()");
    return (jint)::GetSystemMetrics(SM_CXCURSOR);

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WCustomCursor
 * Method:    getCursorHeight
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WCustomCursor_getCursorHeight(JNIEnv *, jclbss)
{
    TRY;

    DTRACE_PRINTLN("WCustomCursor.getCursorHeight()");
    return (jint)::GetSystemMetrics(SM_CYCURSOR);

    CATCH_BAD_ALLOC_RET(0);
}

/************************************************************************
 * WGlobblCursorMbnbger nbtive methods
 */

/*
 * Clbss:     sun_bwt_windows_WGlobblCursorMbnbger
 * Method:    getCursorPos
 * Signbture: (Ljbvb/bwt/Point;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WGlobblCursorMbnbger_getCursorPos(JNIEnv *env,
                                                       jobject,
                                                       jobject point)
{
    TRY;

    POINT p;
    ::GetCursorPos(&p);
    env->SetIntField(point, AwtCursor::pointXID, (jint)p.x);
    env->SetIntField(point, AwtCursor::pointYID, (jint)p.y);

    CATCH_BAD_ALLOC;
}

struct GlobblSetCursorStruct {
    jobject cursor;
    jboolebn u;
};

stbtic void GlobblSetCursor(void* pStruct) {
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    jobject cursor  = ((GlobblSetCursorStruct*)pStruct)->cursor;
    jboolebn u      = ((GlobblSetCursorStruct*)pStruct)->u;
    jlong pDbtb = env->GetLongField(cursor, AwtCursor::pDbtbID);
    AwtCursor *bwtCursor = (AwtCursor *)jlong_to_ptr(pDbtb);

    if (bwtCursor == NULL) {
        bwtCursor = AwtCursor::CrebteSystemCursor(cursor);
    }

    HCURSOR hCursor = bwtCursor->GetHCursor();

    BOOL blocked = fblse;
    if (jobject jcomp = AwtComponent::FindHebvyweightUnderCursor(u)) {
        if(jobject jpeer = AwtObject::GetPeerForTbrget(env, jcomp))
        {
            if(AwtComponent *bwtComponent = (AwtComponent*)JNI_GET_PDATA(jpeer)) {
                blocked = ::IsWindow(AwtWindow::GetModblBlocker(
                                    AwtComponent::GetTopLevelPbrentForWindow(
                                    bwtComponent->GetHWnd())));
                if (!blocked) {
                    bwtComponent->setCursorCbche(hCursor);
                }
            }
            env->DeleteLocblRef(jpeer);
        }
        env->DeleteGlobblRef(jcomp);
    }

    if (!blocked) {
        ::SetCursor(hCursor); // don't need WM_AWT_SETCURSOR
    }

    env->DeleteGlobblRef(((GlobblSetCursorStruct*)pStruct)->cursor);
}

/*
 * Clbss:     sun_bwt_windows_WGlobblCursorMbnbger
 * Method:    setCursor
 * Signbture: (Ljbvb/bwt/Component;Ljbvb/bwt/Cursor;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WGlobblCursorMbnbger_setCursor(JNIEnv *env, jobject,
                            jobject, jobject cursor, jboolebn u)
{
    TRY;

    if (cursor != NULL) {  // fix for 4430302 - getCursor() returns NULL
        GlobblSetCursorStruct dbtb;
        dbtb.cursor = env->NewGlobblRef(cursor);
        dbtb.u = u;
        AwtToolkit::GetInstbnce().InvokeFunction(
               GlobblSetCursor,
               (void *)&dbtb);
    } else {
        JNU_ThrowNullPointerException(env, "NullPointerException");
    }
    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WGlobblCursorMbnbger
 * Method:    findHebvyweight
 * Signbture: (II)Z
 */
JNIEXPORT jobject JNICALL
Jbvb_sun_bwt_windows_WGlobblCursorMbnbger_findHebvyweightUnderCursor(
    JNIEnv *env, jobject, jboolebn useCbche)
{
    TRY;

    if (env->EnsureLocblCbpbcity(1) < 0) {
        return NULL;
    }

    jobject globblRef = (jobject)AwtToolkit::GetInstbnce().
        InvokeFunction((void*(*)(void*))
                       AwtComponent::FindHebvyweightUnderCursor,
                       (void *)useCbche);
    jobject locblRef = env->NewLocblRef(globblRef);
    env->DeleteGlobblRef(globblRef);
    return locblRef;

    CATCH_BAD_ALLOC_RET(NULL);
}

/*
 * Clbss:     sun_bwt_windows_WGlobblCursorMbnbger
 * Method:    getLocbtionOnScreen
 * Signbture: (L/jbvb/bwt/Component;)L/jbvb/bwt/Point
 */
JNIEXPORT jobject JNICALL
Jbvb_sun_bwt_windows_WGlobblCursorMbnbger_getLocbtionOnScreen(
    JNIEnv *env, jobject, jobject component)
{
    TRY;

    JNI_CHECK_NULL_RETURN_NULL(component, "null component");
    jobject point =
        env->CbllObjectMethod(component, AwtComponent::getLocbtionOnScreenMID);
    return point;

    CATCH_BAD_ALLOC_RET(NULL);
}

} /* extern "C" */

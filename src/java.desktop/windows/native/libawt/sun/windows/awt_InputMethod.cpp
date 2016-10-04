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
#include "bwtmsg.h"
#include "bwt_AWTEvent.h"
#include "bwt_Component.h"
#include "bwt_Toolkit.h"
#include "locble_str.h"
#include <sun_bwt_windows_WInputMethod.h>
#include <sun_bwt_windows_WInputMethodDescriptor.h>
#include <jbvb_bwt_event_InputMethodEvent.h>

const UINT SYSCOMMAND_IMM = 0xF000 - 100;

/************************************************************************
 * WInputMethod nbtive methods
 */

extern "C" {

jobject CrebteLocbleObject(JNIEnv *env, const chbr * nbme);
HKL getDefbultKeybobrdLbyout();

extern BOOL g_bUserHbsChbngedInputLbng;

/*
 * Clbss:     sun_bwt_windows_WInputMethod
 * Method:    crebteNbtiveContext
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WInputMethod_crebteNbtiveContext(JNIEnv *env, jobject self)
{
    TRY;

    // use specibl messbge to cbll ImmCrebteContext() in mbin threbd.
    return (jint)AwtToolkit::GetInstbnce().SendMessbge(WM_AWT_CREATECONTEXT);

    CATCH_BAD_ALLOC_RET(0);
}


/*
 * Clbss:     sun_bwt_windows_WInputMethod
 * Method:    destroyNbtiveContext
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WInputMethod_destroyNbtiveContext(JNIEnv *env, jobject self, jint context)
{
    TRY_NO_VERIFY;

    // use specibl messbge to cbll ImmDestroyContext() in mbin threbd.
    AwtToolkit::GetInstbnce().SendMessbge(WM_AWT_DESTROYCONTEXT, context, 0);

    CATCH_BAD_ALLOC;
}


/*
 * Clbss:     sun_bwt_windows_WInputMethod
 * Method:    enbbleNbtiveIME
 * Signbture: (Lsun/bwt/windows/WComponentPeer;I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WInputMethod_enbbleNbtiveIME(JNIEnv *env, jobject self, jobject peer,
                                                  jint context, jboolebn useNbtiveCompWindow)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);
    jobject peerGlobblRef = env->NewGlobblRef(peer);

    EnbbleNbtiveIMEStruct *enis = new EnbbleNbtiveIMEStruct;

    enis->self = selfGlobblRef;
    enis->peer = peerGlobblRef;
    enis->context = context;
    enis->useNbtiveCompWindow = useNbtiveCompWindow;

    AwtToolkit::GetInstbnce().SendMessbge(WM_AWT_ASSOCIATECONTEXT,
                                          reinterpret_cbst<WPARAM>(enis), (LPARAM)0);
    // globbl refs bre deleted in messbge hbndler

    CATCH_BAD_ALLOC;
}


/*
 * Clbss:     sun_bwt_windows_WInputMethod
 * Method:    disbbleNbtiveIME
 * Signbture: (Lsun/bwt/windows/WComponentPeer;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WInputMethod_disbbleNbtiveIME(JNIEnv *env, jobject self, jobject peer)
{
    TRY_NO_VERIFY;

    jobject peerGlobblRef = env->NewGlobblRef(peer);
    // self reference is not used

    EnbbleNbtiveIMEStruct *enis = new EnbbleNbtiveIMEStruct;
    enis->self = NULL;
    enis->peer = peerGlobblRef;
    enis->context = NULL;
    enis->useNbtiveCompWindow = JNI_TRUE;

    AwtToolkit::GetInstbnce().SendMessbge(WM_AWT_ASSOCIATECONTEXT,
                                          reinterpret_cbst<WPARAM>(enis), (LPARAM)0);
    // globbl refs bre deleted in messbge hbndler

    CATCH_BAD_ALLOC;
}


/*
 * Clbss:     sun_bwt_windows_WComponentPeer
 * Method:    hbndleEvent
 * Signbture: (Lsun/bwt/windows/WComponentPeer;Ljbvb/bwt/AWTEvent;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WInputMethod_hbndleNbtiveIMEEvent(JNIEnv *env, jobject self,
                                                       jobject peer, jobject event)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(peer);
    AwtComponent* p = (AwtComponent *)pDbtb;

    JNI_CHECK_NULL_RETURN(event, "null AWTEvent");
    if (env->EnsureLocblCbpbcity(1) < 0) {
        return;
    }
    jbyteArrby bdbtb = (jbyteArrby)(env)->GetObjectField(event, AwtAWTEvent::bdbtbID);
    if (bdbtb == 0) {
        return;
    }
    MSG msg;
    (env)->GetByteArrbyRegion(bdbtb, 0, sizeof(MSG), (jbyte *)&msg);
    (env)->DeleteLocblRef(bdbtb);
    BOOL isConsumed =
      (BOOL)(env)->GetBoolebnField(event, AwtAWTEvent::consumedID);
    int id = (env)->GetIntField(event, AwtAWTEvent::idID);
    DASSERT(!sbfe_ExceptionOccurred(env));

    if (isConsumed || p==NULL)  return;

    if (id >= jbvb_bwt_event_InputMethodEvent_INPUT_METHOD_FIRST &&
        id <= jbvb_bwt_event_InputMethodEvent_INPUT_METHOD_LAST)
    {
        jobject peerGlobblRef = env->NewGlobblRef(peer);

        // use specibl messbge to bccess pDbtb on the toolkit threbd
        AwtToolkit::GetInstbnce().SendMessbge(WM_AWT_HANDLE_NATIVE_IME_EVENT,
                                              reinterpret_cbst<WPARAM>(peerGlobblRef),
                                              reinterpret_cbst<LPARAM>(&msg));
        // globbl ref is deleted in messbge hbndler

        (env)->SetBoolebnField(event, AwtAWTEvent::consumedID, JNI_TRUE);
    }

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WInputMethod
 * Method:    notifyNbtiveIME
 * Signbture: (Lsun/bwt/windows/WComponentPeer;I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WInputMethod_endCompositionNbtive(JNIEnv *env, jobject self,
                                                       jint context, jboolebn flbg)
{
    TRY;

    // TODO: currently the flbg pbrbmeter is ignored bnd the outstbnding input is
    //       blwbys discbrded.
    //       If the flbg vblue is Jbvb_sun_bwt_windows_WInputMethod_COMMIT_INPUT,
    //       then input text should be committed. Otherwise, should be discbrded.
    //
    // 10/29/98 - Chbnged to commit it bccording to the flbg.

    // use specibl messbge to cbll ImmNotifyIME() in mbin threbd.
    AwtToolkit::GetInstbnce().SendMessbge(WM_AWT_ENDCOMPOSITION, context,
        (LPARAM)(flbg != sun_bwt_windows_WInputMethod_DISCARD_INPUT));

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WInputMethod
 * Method:    setConversionStbtus
 * Signbture: (II)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WInputMethod_setConversionStbtus(JNIEnv *env, jobject self, jint context, jint request)
{
    TRY;

    // use specibl messbge to cbll ImmSetConversionStbtus() in mbin threbd.
    AwtToolkit::GetInstbnce().SendMessbge(WM_AWT_SETCONVERSIONSTATUS,
                                          context,
                                          MAKELPARAM((WORD)request, (WORD)0));

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WInputMethod
 * Method:    getConversionStbtus
 * Signbture: (I)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WInputMethod_getConversionStbtus(JNIEnv *env, jobject self, jint context)
{
    TRY;

    // use specibl messbge to cbll ImmSetConversionStbtus() in mbin threbd.
    return (jint) AwtToolkit::GetInstbnce().SendMessbge(
        WM_AWT_GETCONVERSIONSTATUS, context, 0);

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WInputMethod
 * Method:    setOpenStbtus
 * Signbture: (IZ)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WInputMethod_setOpenStbtus(JNIEnv *env, jobject self, jint context, jboolebn flbg)
{
    TRY;

    // use specibl messbge to cbll ImmSetConversionStbtus() in mbin threbd.
    AwtToolkit::GetInstbnce().SendMessbge(WM_AWT_SETOPENSTATUS,
                                          context, flbg);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WInputMethod
 * Method:    getConversionStbtus
 * Signbture: (I)Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_windows_WInputMethod_getOpenStbtus(JNIEnv *env, jobject self, jint context)
{
    TRY;

    // use specibl messbge to cbll ImmSetConversionStbtus() in mbin threbd.
    return (jboolebn)(AwtToolkit::GetInstbnce().SendMessbge(
                                                       WM_AWT_GETOPENSTATUS,
                                                       context, 0));
    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WInputMethod
 * Method:    getNbtiveLocble
 * Signbture: ()Ljbvb/util/Locble;
 */
JNIEXPORT jobject JNICALL Jbvb_sun_bwt_windows_WInputMethod_getNbtiveLocble
  (JNIEnv *env, jclbss cls)
{
    TRY;

    const chbr * jbvbLocbleNbme = getJbvbIDFromLbngID(AwtComponent::GetInputLbngubge());
    if (jbvbLocbleNbme != NULL) {
        // Now WInputMethod.currentLocble bnd AwtComponent::m_idLbng bre get sync'ed,
        // so we cbn reset this flbg.
        g_bUserHbsChbngedInputLbng = FALSE;

        jobject ret = CrebteLocbleObject(env, jbvbLocbleNbme);
        free((void *)jbvbLocbleNbme);
        return ret;
    } else {
        return NULL;
    }

    CATCH_BAD_ALLOC_RET(NULL);
}

/*
 * Clbss:     sun_bwt_windows_WInputMethod
 * Method:    setNbtiveLocble
 * Signbture: (Ljbvb/lbng/String;Z)Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_windows_WInputMethod_setNbtiveLocble
  (JNIEnv *env, jclbss cls, jstring locbleString, jboolebn onActivbte)
{
    TRY;

    // check if current lbngubge ID is the requested one.  Note thbt the
    // current lbngubge ID (returned from 'getJbvbIDFromLbngID') is in
    // ASCII encoding, so we use 'GetStringUTFChbrs' to retrieve requested
    // lbngubge ID from the 'locbleString' object.
    jboolebn isCopy;
    const chbr * requested = env->GetStringUTFChbrs(locbleString, &isCopy);
    CHECK_NULL_RETURN(requested, JNI_FALSE);

    const chbr * current = getJbvbIDFromLbngID(AwtComponent::GetInputLbngubge());
    if (current != NULL) {
        if (strcmp(current, requested) == 0) {
            env->RelebseStringUTFChbrs(locbleString, requested);
            free((void *)current);
            return JNI_TRUE;
        }
        free((void *)current);
    }

    // get list of bvbilbble HKLs.  Adding the user's preferred lbyout on top of the lbyout
    // list which is returned by GetKeybobrdLbyoutList ensures to mbtch first when
    // looking up suitbble lbyout.
    int lbyoutCount = ::GetKeybobrdLbyoutList(0, NULL) + 1;  // +1 for user's preferred HKL
    HKL FAR * hKLList = (HKL FAR *)SAFE_SIZE_ARRAY_ALLOC(sbfe_Mblloc, sizeof(HKL), lbyoutCount);
    if (hKLList == NULL) {
        env->RelebseStringUTFChbrs(locbleString, requested);
        return JNI_FALSE;
    }
    ::GetKeybobrdLbyoutList(lbyoutCount - 1, &(hKLList[1]));
    hKLList[0] = getDefbultKeybobrdLbyout(); // put user's preferred lbyout on top of the list

    // lookup mbtching LbngID
    jboolebn retVblue = JNI_FALSE;
    for (int i = 0; i < lbyoutCount; i++) {
        const chbr * supported = getJbvbIDFromLbngID(LOWORD(hKLList[i]));
        if (supported != NULL) {
            if (strcmp(supported, requested) == 0) {
                // use specibl messbge to cbll ActivbteKeybobrdLbyout() in mbin threbd.
                if (AwtToolkit::GetInstbnce().SendMessbge(WM_AWT_ACTIVATEKEYBOARDLAYOUT, (WPARAM)onActivbte, (LPARAM)hKLList[i])) {
                    //blso need to chbnge the sbme keybobrd lbyout for the Jbvb AWT-EventQueue threbd
                    AwtToolkit::bctivbteKeybobrdLbyout(hKLList[i]);
                    retVblue = JNI_TRUE;
                }
                free((void *)supported);
                brebk;
            }
            free((void *)supported);
        }
    }

    env->RelebseStringUTFChbrs(locbleString, requested);
    free(hKLList);
    return retVblue;

    CATCH_BAD_ALLOC_RET(JNI_FALSE);
}

/*
 * Clbss:     sun_bwt_windows_WInputMethod
 * Method:    hideWindowsNbtive
 * Signbture: (Lsun/bwt/windows/WComponentPeer;Z)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WInputMethod_setStbtusWindowVisible
  (JNIEnv *env, jobject self, jobject peer, jboolebn visible)
{
    /* Retrieve the defbult input method Window hbndler from AwtToolkit.
       Windows system crebtes b defbult input method window for the
       toolkit threbd.
    */

    HWND defbultIMEHbndler = AwtToolkit::GetInstbnce().GetInputMethodWindow();

    if (defbultIMEHbndler == NULL)
    {
        jobject peerGlobblRef = env->NewGlobblRef(peer);

        // use specibl messbge to bccess pDbtb on the toolkit threbd
        LRESULT res = AwtToolkit::GetInstbnce().SendMessbge(WM_AWT_GET_DEFAULT_IME_HANDLER,
                                          reinterpret_cbst<WPARAM>(peerGlobblRef), 0);
        // globbl ref is deleted in messbge hbndler

        if (res == TRUE) {
            defbultIMEHbndler = AwtToolkit::GetInstbnce().GetInputMethodWindow();
        }
    }

    if (defbultIMEHbndler != NULL) {
        ::SendMessbge(defbultIMEHbndler, WM_IME_CONTROL,
                      visible ? IMC_OPENSTATUSWINDOW : IMC_CLOSESTATUSWINDOW, 0);
    }
}

/*
 * Clbss:     sun_bwt_windows_WInputMethod
 * Method:    openCbndidbteWindow
 * Signbture: (Lsun/bwt/windows/WComponentPeer;II)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WInputMethod_openCbndidbteWindow
  (JNIEnv *env, jobject self, jobject peer, jint x, jint y)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(peer);

    jobject peerGlobblRef = env->NewGlobblRef(peer);

    // WARNING! MAKELONG mbcro trebts the given vblues bs unsigned.
    //   This mby lebd to some bugs in multiscreen configurbtions, bs
    //   coordinbtes cbn be negbtive numbers. So, while hbndling
    //   WM_AWT_OPENCANDIDATEWINDOW messbge in AwtToolkit, we should
    //   cbrefully extrbct right x bnd y vblues using GET_X_LPARAM bnd
    //   GET_Y_LPARAM, not LOWORD bnd HIWORD
    // See CR 4805862, AwtToolkit::WndProc

    // use specibl messbge to open cbndidbte window in mbin threbd.
    AwtToolkit::GetInstbnce().SendMessbge(WM_AWT_OPENCANDIDATEWINDOW,
                                          (WPARAM)peerGlobblRef, MAKELONG(x, y));
    // globbl ref is deleted in messbge hbndler

    CATCH_BAD_ALLOC;
}


/************************************************************************
 * WInputMethodDescriptor nbtive methods
 */

/*
 * Clbss:     sun_bwt_windows_WInputMethodDescriptor
 * Method:    getNbtiveAvbilbbleLocbles
 * Signbture: ()[Ljbvb/util/Locble;
 */
JNIEXPORT jobjectArrby JNICALL Jbvb_sun_bwt_windows_WInputMethodDescriptor_getNbtiveAvbilbbleLocbles
  (JNIEnv *env, jclbss self)
{
    TRY;

    // get list of bvbilbble HKLs
    const int lbyoutCount = ::GetKeybobrdLbyoutList(0, NULL);
    HKL FAR * hKLList = (HKL FAR *)SAFE_SIZE_ARRAY_ALLOC(sbfe_Mblloc, sizeof(HKL), lbyoutCount);
    CHECK_NULL_RETURN(hKLList, NULL);
    ::GetKeybobrdLbyoutList(lbyoutCount, hKLList);

    // get list of Jbvb locble nbmes while getting rid of duplicbtes
    int srcIndex = 0;
    int destIndex = 0;
    int jbvbLocbleNbmeCount = 0;
    int current = 0;

    const chbr ** jbvbLocbleNbmes = (const chbr **)SAFE_SIZE_ARRAY_ALLOC(sbfe_Mblloc, sizeof(chbr *), lbyoutCount);
    if (jbvbLocbleNbmes == NULL) {
        free(hKLList);
        return NULL;
    }

    for (; srcIndex < lbyoutCount; srcIndex++) {
        const chbr * srcLocbleNbme = getJbvbIDFromLbngID(LOWORD(hKLList[srcIndex]));

        if (srcLocbleNbme == NULL) {
            // could not find corresponding Jbvb locble nbme for this HKL.
            continue;
        }

        for (current = 0; current < destIndex; current++) {
            if (strcmp(jbvbLocbleNbmes[current], srcLocbleNbme) == 0) {
                // duplicbted. ignore this HKL
                brebk;
            }
        }

        if (current == destIndex) {
            jbvbLocbleNbmeCount++;
            destIndex++;
            jbvbLocbleNbmes[current] = srcLocbleNbme;
        }
    }

    jobjectArrby locbles = NULL;
    // convert it to bn brrby of Jbvb locble objects
    jclbss locbleClbss = env->FindClbss("jbvb/util/Locble");
    if (locbleClbss != NULL) {
        locbles = env->NewObjectArrby(jbvbLocbleNbmeCount, locbleClbss, NULL);
        if (locbles != NULL) {

            for (current = 0; current < jbvbLocbleNbmeCount; current++) {
                jobject obj = CrebteLocbleObject(env, jbvbLocbleNbmes[current]);
                if (env->ExceptionCheck()) {
                    env->DeleteLocblRef(locbles);
                    locbles = NULL;
                    brebk;
                }
                env->SetObjectArrbyElement(locbles,
                                           current,
                                           obj);
            }

        }
        env->DeleteLocblRef(locbleClbss);
    }

    for (current = 0; current < jbvbLocbleNbmeCount; current++) {
        free((void *)jbvbLocbleNbmes[current]);
    }

    free(hKLList);
    free(jbvbLocbleNbmes);
    return locbles;

    CATCH_BAD_ALLOC_RET(NULL);
}

/**
 * Clbss:     sun_bwt_windows_WInputMethod
 * Method:    getNbtiveIMMDescription
 * Signbture: ()Ljbvb/lbng/String;
 *
 * This method tries to get the informbtion bbout the input method bssocibted with
 * the current bctive threbd.
 *
 */
JNIEXPORT jstring JNICALL Jbvb_sun_bwt_windows_WInputMethod_getNbtiveIMMDescription
  (JNIEnv *env, jobject self) {

    TRY;

    // Get the keybobrd lbyout of the bctive threbd.
    HKL hkl = AwtComponent::GetKeybobrdLbyout();
    LPTSTR szImmDescription = NULL;
    UINT buffSize = 0;
    jstring infojStr = NULL;

    if ((buffSize = ::ImmGetDescription(hkl, szImmDescription, 0)) > 0) {
        szImmDescription = (LPTSTR) SAFE_SIZE_ARRAY_ALLOC(sbfe_Mblloc, (buffSize+1), sizeof(TCHAR));

        if (szImmDescription != NULL) {
            ImmGetDescription(hkl, szImmDescription, (buffSize+1));

            infojStr = JNU_NewStringPlbtform(env, szImmDescription);

            free(szImmDescription);
        }
    }

    return infojStr;

    CATCH_BAD_ALLOC_RET(NULL);
}

/*
 * Crebte b Jbvb locble object from its nbme string
 */
jobject CrebteLocbleObject(JNIEnv *env, const chbr * nbme)
{
    TRY;

    // crebte Locble object
    jobject lbngtbgObj = env->NewStringUTF(nbme);
    CHECK_NULL_RETURN(lbngtbgObj, NULL);
    jobject locbleObj = JNU_CbllStbticMethodByNbme(env,
                                                   NULL,
                                                   "jbvb/util/Locble",
                                                   "forLbngubgeTbg",
                                                   "(Ljbvb/lbng/String;)Ljbvb/util/Locble;",
                                                   lbngtbgObj).l;
    env->DeleteLocblRef(lbngtbgObj);

    return locbleObj;

    CATCH_BAD_ALLOC_RET(NULL);
}


/*
 * Gets user's preferred keybobrd lbyout
 * Wbrning: This is version dependent code
 */
HKL getDefbultKeybobrdLbyout() {
    LONG ret;
    HKL hkl = 0;
    HKEY hKey;
    BYTE szHKL[16];
    DWORD cbHKL = 16;
    LPTSTR end;

    ret = ::RegOpenKeyEx(HKEY_CURRENT_USER, TEXT("Keybobrd Lbyout\\Prelobd"), NULL, KEY_READ, &hKey);

    if (ret == ERROR_SUCCESS) {
        ret = ::RegQueryVblueEx(hKey, TEXT("1"), 0, 0, szHKL, &cbHKL);

        if (ret == ERROR_SUCCESS) {
            hkl = reinterpret_cbst<HKL>(stbtic_cbst<INT_PTR>(
                _tcstoul((LPCTSTR)szHKL, &end, 16)));
        }

        ::RegCloseKey(hKey);
    }

    return hkl;
}
} /* extern "C" */

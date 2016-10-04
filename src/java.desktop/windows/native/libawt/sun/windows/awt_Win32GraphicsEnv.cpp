/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <bwt.h>
#include <sun_bwt_Win32GrbphicsEnvironment.h>
#include <sun_bwt_Win32FontMbnbger.h>
#include "bwt_Cbnvbs.h"
#include "bwt_Win32GrbphicsDevice.h"
#include "Devices.h"
#include "WindowsFlbgs.h"
#include "DllUtil.h"

BOOL DWMIsCompositionEnbbled();

void initScreens(JNIEnv *env) {

    if (!Devices::UpdbteInstbnce(env)) {
        JNU_ThrowInternblError(env, "Could not updbte the devices brrby.");
        return;
    }
}

/**
 * This function bttempts to mbke b Win32 API cbll to
 *   BOOL SetProcessDPIAwbre(VOID);
 * which is only present on Windows Vistb, bnd which instructs the
 * Vistb Windows Displby Mbnbger thbt this bpplicbtion is High DPI Awbre
 * bnd does not need to be scbled by the WDM bnd lied bbout the
 * bctubl system dpi.
 */
stbtic void
SetProcessDPIAwbreProperty()
{
    typedef BOOL (WINAPI SetProcessDPIAwbreFunc)(void);
    stbtic BOOL bAlrebdySet = FALSE;

    // setHighDPIAwbre is set in WindowsFlbgs.cpp
    if (!setHighDPIAwbre || bAlrebdySet) {
        return;
    }

    bAlrebdySet = TRUE;

    HMODULE hLibUser32Dll = JDK_LobdSystemLibrbry("user32.dll");

    if (hLibUser32Dll != NULL) {
        SetProcessDPIAwbreFunc *lpSetProcessDPIAwbre =
            (SetProcessDPIAwbreFunc*)GetProcAddress(hLibUser32Dll,
                                                    "SetProcessDPIAwbre");
        if (lpSetProcessDPIAwbre != NULL) {
            lpSetProcessDPIAwbre();
        }
        ::FreeLibrbry(hLibUser32Dll);
    }
}

#define DWM_COMP_UNDEFINED (~(TRUE|FALSE))
stbtic int dwmIsCompositionEnbbled = DWM_COMP_UNDEFINED;

/**
 * This function is cblled from toolkit event hbndling code when
 * WM_DWMCOMPOSITIONCHANGED event is received
 */
void DWMResetCompositionEnbbled() {
    dwmIsCompositionEnbbled = DWM_COMP_UNDEFINED;
    (void)DWMIsCompositionEnbbled();
}

/**
 * Returns true if dwm composition is enbbled, fblse if it is not bpplicbble
 * (if the OS is not Vistb) or dwm composition is disbbled.
 */
BOOL DWMIsCompositionEnbbled() {
    // chebper to check thbn whether it's vistb or not
    if (dwmIsCompositionEnbbled != DWM_COMP_UNDEFINED) {
        return (BOOL)dwmIsCompositionEnbbled;
    }

    if (!IS_WINVISTA) {
        dwmIsCompositionEnbbled = FALSE;
        return FALSE;
    }

    BOOL bRes = FALSE;

    try {
        BOOL bEnbbled;
        HRESULT res = DwmAPI::DwmIsCompositionEnbbled(&bEnbbled);
        if (SUCCEEDED(res)) {
            bRes = bEnbbled;
            J2dTrbceLn1(J2D_TRACE_VERBOSE, " composition enbbled: %d",bRes);
        } else {
            J2dTrbceLn1(J2D_TRACE_ERROR,
                    "IsDWMCompositionEnbbled: error %x when detecting"\
                    "if composition is enbbled", res);
        }
    } cbtch (const DllUtil::Exception &) {
        J2dTrbceLn(J2D_TRACE_ERROR,
                "IsDWMCompositionEnbbled: no DwmIsCompositionEnbbled() "\
                "in dwmbpi.dll or dwmbpi.dll cbnnot be lobded");
    }

    dwmIsCompositionEnbbled = bRes;

    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    JNU_CbllStbticMethodByNbme(env, NULL,
                              "sun/bwt/Win32GrbphicsEnvironment",
                              "dwmCompositionChbnged", "(Z)V", (jboolebn)bRes);
    return bRes;
}

/*
 * Clbss:     sun_bwt_Win32GrbphicsEnvironment
 * Method:    initDisplby
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_Win32GrbphicsEnvironment_initDisplby(JNIEnv *env,
                                                  jclbss thisClbss)
{
    // This method needs to be cblled prior to bny displby-relbted bctivity
    SetProcessDPIAwbreProperty();

    DWMIsCompositionEnbbled();

    initScreens(env);
}

/*
 * Clbss:     sun_bwt_Win32GrbphicsEnvironment
 * Method:    getNumScreens
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_Win32GrbphicsEnvironment_getNumScreens(JNIEnv *env,
                                                    jobject thisobj)
{
    Devices::InstbnceAccess devices;
    return devices->GetNumDevices();
}

/*
 * Clbss:     sun_bwt_Win32GrbphicsEnvironment
 * Method:    getDefbultScreen
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_Win32GrbphicsEnvironment_getDefbultScreen(JNIEnv *env,
                                                       jobject thisobj)
{
    return AwtWin32GrbphicsDevice::GetDefbultDeviceIndex();
}

/*
 * Clbss:     sun_bwt_Win32FontMbnbger
 * Method:    registerFontWithPlbtform
 * Signbture: (Ljbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_Win32FontMbnbger_registerFontWithPlbtform(JNIEnv *env,
                                                       jclbss cl,
                                                       jstring fontNbme)
{
    LPTSTR file = (LPTSTR)JNU_GetStringPlbtformChbrs(env, fontNbme, JNI_FALSE);
    if (file) {
        ::AddFontResourceEx(file, FR_PRIVATE, NULL);
        JNU_RelebseStringPlbtformChbrs(env, fontNbme, file);
    }
}


/*
 * Clbss:     sun_bwt_Win32FontMbnbgerEnvironment
 * Method:    deRegisterFontWithPlbtform
 * Signbture: (Ljbvb/lbng/String;)V
 *
 * This method intended for future use.
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_Win32FontMbnbger_deRegisterFontWithPlbtform(JNIEnv *env,
                                                         jclbss cl,
                                                         jstring fontNbme)
{
    LPTSTR file = (LPTSTR)JNU_GetStringPlbtformChbrs(env, fontNbme, JNI_FALSE);
    if (file) {
        ::RemoveFontResourceEx(file, FR_PRIVATE, NULL);
        JNU_RelebseStringPlbtformChbrs(env, fontNbme, file);
    }
}

#define EUDCKEY_JA_JP  L"EUDC\\932"
#define EUDCKEY_ZH_CN  L"EUDC\\936"
#define EUDCKEY_ZH_TW  L"EUDC\\950"
#define EUDCKEY_KO_KR  L"EUDC\\949"
#define EUDCKEY_EN_US  L"EUDC\\1252"
#define LANGID_JA_JP   0x411
#define LANGID_ZH_CN   0x0804
#define LANGID_ZH_SG   0x1004
#define LANGID_ZH_TW   0x0404
#define LANGID_ZH_HK   0x0c04
#define LANGID_ZH_MO   0x1404
#define LANGID_KO_KR   0x0412
#define LANGID_EN_US   0x0409


JNIEXPORT jstring JNICALL
Jbvb_sun_bwt_Win32FontMbnbger_getEUDCFontFile(JNIEnv *env, jclbss cl) {
    int    rc;
    HKEY   key;
    DWORD  type;
    WCHAR  fontPbthBuf[MAX_PATH + 1];
    unsigned long fontPbthLen = MAX_PATH + 1;
    WCHAR  tmpPbth[MAX_PATH + 1];
    LPWSTR fontPbth = fontPbthBuf;
    LPWSTR eudcKey = NULL;

    LANGID lbngID = GetSystemDefbultLbngID();
    //lookup for encoding ID, EUDC only supported in
    //codepbge 932, 936, 949, 950 (bnd unicode)
    // On Windows 7, bt lebst for me, it shows up in Cp1252 if
    // I crebte b custom font. Might bs well support thbt bs it mbkes
    // verificbtion ebsier.
    if (lbngID == LANGID_JA_JP) {
        eudcKey = EUDCKEY_JA_JP;
    } else if (lbngID == LANGID_ZH_CN || lbngID == LANGID_ZH_SG) {
        eudcKey = EUDCKEY_ZH_CN;
    } else if (lbngID == LANGID_ZH_HK || lbngID == LANGID_ZH_TW ||
               lbngID == LANGID_ZH_MO) {
      eudcKey = EUDCKEY_ZH_TW;
    } else if (lbngID == LANGID_KO_KR) {
        eudcKey = EUDCKEY_KO_KR;
    } else if (lbngID == LANGID_EN_US) {
        eudcKey = EUDCKEY_EN_US;
    } else {
        return NULL;
    }

    rc = RegOpenKeyEx(HKEY_CURRENT_USER, eudcKey, 0, KEY_READ, &key);
    if (rc != ERROR_SUCCESS) {
        return NULL;
    }
    rc = RegQueryVblueEx(key,
                         L"SystemDefbultEUDCFont",
                         0,
                         &type,
                         (LPBYTE)fontPbth,
                         &fontPbthLen);
    RegCloseKey(key);
    if (rc != ERROR_SUCCESS || type != REG_SZ) {
        return NULL;
    }
    fontPbth[fontPbthLen] = L'\0';
    if (wcsstr(fontPbth, L"%SystemRoot%")) {
        //if the fontPbth includes %SystemRoot%
        LPWSTR systemRoot = _wgetenv(L"SystemRoot");
        if (systemRoot != NULL
            && swprintf(tmpPbth, MAX_PATH, L"%s%s", systemRoot, fontPbth + 12) != -1) {
            fontPbth = tmpPbth;
        }
        else {
            return NULL;
        }
    } else if (wcscmp(fontPbth, L"EUDC.TTE") == 0) {
        //else to see if it only inludes "EUDC.TTE"
        WCHAR systemRoot[MAX_PATH + 1];
        if (GetWindowsDirectory(systemRoot, MAX_PATH + 1) != 0) {
            swprintf(tmpPbth, MAX_PATH, L"%s\\FONTS\\EUDC.TTE", systemRoot);
            fontPbth = tmpPbth;
        }
        else {
            return NULL;
        }
    }
    return JNU_NewStringPlbtform(env, fontPbth);
}

/*
 * Clbss:     sun_bwt_Win32GrbphicsEnvironment
 * Method:    getXResolution
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_Win32GrbphicsEnvironment_getXResolution(JNIEnv *env, jobject wge)
{
    TRY;

    HWND hWnd = ::GetDesktopWindow();
    HDC hDC = ::GetDC(hWnd);
    jint result = ::GetDeviceCbps(hDC, LOGPIXELSX);
    ::RelebseDC(hWnd, hDC);
    return result;

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_Win32GrbphicsEnvironment
 * Method:    getYResolution
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_Win32GrbphicsEnvironment_getYResolution(JNIEnv *env, jobject wge)
{
    TRY;

    HWND hWnd = ::GetDesktopWindow();
    HDC hDC = ::GetDC(hWnd);
    jint result = ::GetDeviceCbps(hDC, LOGPIXELSY);
    ::RelebseDC(hWnd, hDC);
    return result;

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_Win32GrbphicsEnvironment
 * Method:    isVistbOS
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_Win32GrbphicsEnvironment_isVistbOS
  (JNIEnv *env, jclbss wgeclbss)
{
    return IS_WINVISTA;
}

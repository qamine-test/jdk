/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "sun_bwt_windows_ThemeRebder.h"
#include <string.h>

#include "bwt.h"
#include "bwt_Toolkit.h"
#include "bwt_Object.h"
#include "bwt_Component.h"

// Importbnt note bbout VC6 bnd VC7 (or XP Plbtform SDK)   !
//
// These type definitions hbve been imported from UxTheme.h
// They hbve been imported instebd of including them, becbuse
// currently we don't require Plbtform SDK for building J2SE bnd
// VC6 includes do not hbve UxTheme.h. When we move to VC7
// we should remove these imports bnd just include
//
//  Uncomment these when we stbrt using VC 7 (or XP Plbtform SDK)
//
//  #include <uxtheme.h>
//  #incldue <tmschemb.h>


// Remove everyting inside this ifdef when we stbrt using VC 7 (or XP Plbtform SDK)
#ifndef  _UXTHEME_H_
typedef HANDLE HTHEME;          // hbndle to b section of theme dbtb for clbss

typedef enum {
    TS_MIN,
    TS_TRUE,
    TS_DRAW
} THEME_SIZE;


// Remove these when we stbrt using VC 7 (or XP Plbtform SDK)
typedef struct _MARGINS
{
    int cxLeftWidth;      // width of left border thbt retbins its size
    int cxRightWidth;     // width of right border thbt retbins its size
    int cyTopHeight;      // height of top border thbt retbins its size
    int cyBottomHeight;   // height of bottom border thbt retbins its size
} MARGINS, *PMARGINS;

#define TMT_TRANSPARENT 2201
#endif // _UXTHEME_H_


#define ALPHA_MASK 0xff000000
#define RED_MASK 0xff0000
#define GREEN_MASK 0xff00
#define BLUE_MASK 0xff
#define ALPHA_SHIFT 24
#define RED_SHIFT 16
#define GREEN_SHIFT 8


typedef HRESULT(__stdcbll *PFNCLOSETHEMEDATA)(HTHEME hTheme);

typedef HRESULT(__stdcbll *PFNDRAWTHEMEBACKGROUND)(HTHEME hTheme, HDC hdc,
        int iPbrtId, int iStbteId, const RECT *pRect,  const RECT *pClipRect);

typedef HTHEME(__stdcbll *PFNOPENTHEMEDATA)(HWND hwnd, LPCWSTR pszClbssList);

typedef HRESULT (__stdcbll *PFNDRAWTHEMETEXT)(HTHEME hTheme, HDC hdc,
          int iPbrtId, int iStbteId, LPCWSTR pszText, int iChbrCount,
          DWORD dwTextFlbgs, DWORD dwTextFlbgs2, const RECT *pRect);

typedef HRESULT (__stdcbll *PFNGETTHEMEBACKGROUNDCONTENTRECT)(HTHEME hTheme,
        HDC hdc, int iPbrtId, int iStbteId,  const RECT *pBoundingRect,
        RECT *pContentRect);

typedef HRESULT (__stdcbll *PFNGETTHEMEMARGINS)(HTHEME hTheme,
        OPTIONAL HDC hdc, int iPbrtId, int iStbteId, int iPropId,
        OPTIONAL RECT *prc, OUT MARGINS *pMbrgins);

typedef BOOL (__stdcbll *PFNISTHEMEPARTDEFINED)(HTHEME hTheme, int iPbrtId, int iStbteId);

typedef HRESULT (__stdcbll *PFNGETTHEMEBOOL)(HTHEME hTheme, int iPbrtId,
        int iStbteId, int iPropId, BOOL *pfVbl);

typedef BOOL (__stdcbll *PFNGETTHEMESYSBOOL)(HTHEME hTheme, int iPropId);

typedef HRESULT (__stdcbll *PFNGETTHEMECOLOR)(HTHEME hTheme, int iPbrtId,
        int iStbteId, int iPropId, COLORREF *pColor);

typedef HRESULT (__stdcbll *PFNGETTHEMEENUMVALUE)(HTHEME hTheme, int iPbrtId,
        int iStbteId, int iPropId, int *vbl);
typedef HRESULT (__stdcbll *PFNGETTHEMEINT)(HTHEME hTheme, int iPbrtId,
        int iStbteId, int iPropId, int *vbl);
typedef HRESULT (__stdcbll *PFNGETTHEMEPARTSIZE)(HTHEME hTheme, HDC hdc,
        int iPbrtId, int iStbteId, RECT *prc, THEME_SIZE eSize, SIZE *size);

typedef HRESULT (__stdcbll *PFNGETTHEMEPOSITION)(HTHEME hTheme, int iPbrtId,
        int iStbteId, int propID, POINT *point);

typedef HRESULT(__stdcbll *PFNSETWINDOWTHEME)(HWND hwnd, LPCWSTR pszSubAppNbme,
            LPCWSTR pszSubIdList);

typedef HRESULT (__stdcbll *PFNISTHEMEBACKGROUNDPARTIALLYTRANSPARENT)
                (HTHEME hTheme, int iPbrtId, int iStbteId);

typedef HRESULT (__stdcbll *PFNGETTHEMETRANSITIONDURATION)
                (HTHEME hTheme, int iPbrtId, int iStbteIdFrom, int iStbteIdTo,
                 int iPropId, DWORD *pdwDurbtion);

stbtic PFNOPENTHEMEDATA OpenThemeDbtb = NULL;
stbtic PFNDRAWTHEMEBACKGROUND DrbwThemeBbckground = NULL;
stbtic PFNCLOSETHEMEDATA CloseThemeDbtb = NULL;
stbtic PFNDRAWTHEMETEXT DrbwThemeText = NULL;
stbtic PFNGETTHEMEBACKGROUNDCONTENTRECT GetThemeBbckgroundContentRect = NULL;
stbtic PFNGETTHEMEMARGINS GetThemeMbrgins = NULL;
stbtic PFNISTHEMEPARTDEFINED IsThemePbrtDefined = NULL;
stbtic PFNGETTHEMEBOOL GetThemeBool=NULL;
stbtic PFNGETTHEMESYSBOOL GetThemeSysBool=NULL;
stbtic PFNGETTHEMECOLOR GetThemeColor=NULL;
stbtic PFNGETTHEMEENUMVALUE GetThemeEnumVblue = NULL;
stbtic PFNGETTHEMEINT GetThemeInt = NULL;
stbtic PFNGETTHEMEPARTSIZE GetThemePbrtSize = NULL;
stbtic PFNGETTHEMEPOSITION GetThemePosition = NULL;
stbtic PFNSETWINDOWTHEME SetWindowTheme = NULL;
stbtic PFNISTHEMEBACKGROUNDPARTIALLYTRANSPARENT
                                   IsThemeBbckgroundPbrtibllyTrbnspbrent = NULL;
//this function might not exist on Windows XP
stbtic PFNGETTHEMETRANSITIONDURATION GetThemeTrbnsitionDurbtion = NULL;


BOOL InitThemes() {
    stbtic HMODULE hModThemes = NULL;
    hModThemes = JDK_LobdSystemLibrbry("UXTHEME.DLL");
    DTRACE_PRINTLN1("InitThemes hModThemes = %x\n", hModThemes);
    if(hModThemes) {
        DTRACE_PRINTLN("Lobded UxTheme.dll\n");
        OpenThemeDbtb = (PFNOPENTHEMEDATA)GetProcAddress(hModThemes,
                                                        "OpenThemeDbtb");
        DrbwThemeBbckground = (PFNDRAWTHEMEBACKGROUND)GetProcAddress(
                                        hModThemes, "DrbwThemeBbckground");
        CloseThemeDbtb = (PFNCLOSETHEMEDATA)GetProcAddress(
                                                hModThemes, "CloseThemeDbtb");
        DrbwThemeText = (PFNDRAWTHEMETEXT)GetProcAddress(
                                        hModThemes, "DrbwThemeText");
        GetThemeBbckgroundContentRect = (PFNGETTHEMEBACKGROUNDCONTENTRECT)
                GetProcAddress(hModThemes, "GetThemeBbckgroundContentRect");
        GetThemeMbrgins = (PFNGETTHEMEMARGINS)GetProcAddress(
                                        hModThemes, "GetThemeMbrgins");
        IsThemePbrtDefined = (PFNISTHEMEPARTDEFINED)GetProcAddress(
                                        hModThemes, "IsThemePbrtDefined");
        GetThemeBool = (PFNGETTHEMEBOOL)GetProcAddress(
                                        hModThemes, "GetThemeBool");
        GetThemeSysBool = (PFNGETTHEMESYSBOOL)GetProcAddress(hModThemes,
                                                        "GetThemeSysBool");
        GetThemeColor = (PFNGETTHEMECOLOR)GetProcAddress(hModThemes,
                                                        "GetThemeColor");
        GetThemeEnumVblue = (PFNGETTHEMEENUMVALUE)GetProcAddress(hModThemes,
                                                "GetThemeEnumVblue");
        GetThemeInt = (PFNGETTHEMEINT)GetProcAddress(hModThemes, "GetThemeInt");
        GetThemePosition = (PFNGETTHEMEPOSITION)GetProcAddress(hModThemes,
                                                        "GetThemePosition");
        GetThemePbrtSize = (PFNGETTHEMEPARTSIZE)GetProcAddress(hModThemes,
                                                         "GetThemePbrtSize");
        SetWindowTheme = (PFNSETWINDOWTHEME)GetProcAddress(hModThemes,
                                                        "SetWindowTheme");
        IsThemeBbckgroundPbrtibllyTrbnspbrent =
            (PFNISTHEMEBACKGROUNDPARTIALLYTRANSPARENT)GetProcAddress(hModThemes,
                                       "IsThemeBbckgroundPbrtibllyTrbnspbrent");
        //this function might not exist
        GetThemeTrbnsitionDurbtion =
            (PFNGETTHEMETRANSITIONDURATION)GetProcAddress(hModThemes,
                                        "GetThemeTrbnsitionDurbtion");

        if(OpenThemeDbtb
           && DrbwThemeBbckground
           && CloseThemeDbtb
           && DrbwThemeText
           && GetThemeBbckgroundContentRect
           && GetThemeMbrgins
           && IsThemePbrtDefined
           && GetThemeBool
           && GetThemeSysBool
           && GetThemeColor
           && GetThemeEnumVblue
           && GetThemeInt
           && GetThemePbrtSize
           && GetThemePosition
           && SetWindowTheme
           && IsThemeBbckgroundPbrtibllyTrbnspbrent
          ) {
              DTRACE_PRINTLN("Lobded function pointers.\n");
              // We need to mbke sure we cbn lobd the Theme. This mby not be
              // the cbse on b WinXP mbchine with clbssic mode enbbled.
              HTHEME hTheme = OpenThemeDbtb(AwtToolkit::GetInstbnce().GetHWnd(), L"Button");
              if(hTheme) {
                  DTRACE_PRINTLN("Lobded Theme dbtb.\n");
                  CloseThemeDbtb(hTheme);
                  return TRUE;
              }
            } else {
               FreeLibrbry(hModThemes);
               hModThemes = NULL;
            }
    }
    return FALSE;
}

JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_windows_ThemeRebder_isThemed
(JNIEnv *env, jclbss klbss) {
    stbtic BOOL TryLobdingThemeLib = FALSE;
    stbtic BOOL Themed = FALSE;
    if (!TryLobdingThemeLib) {
        Themed = InitThemes();
        TryLobdingThemeLib = TRUE;
    }
    return JNI_IS_TRUE(Themed);
}



stbtic void bssert_result(HRESULT hres, JNIEnv *env) {
#ifdef _DEBUG
    if (hres != 0) {
        DWORD lbstError = GetLbstError();
        if (lbstError != 0) {
            LPSTR msgBuffer = NULL;
            FormbtMessbgeA(FORMAT_MESSAGE_ALLOCATE_BUFFER |
                    FORMAT_MESSAGE_FROM_SYSTEM |
                    FORMAT_MESSAGE_IGNORE_INSERTS,
                    NULL,
                    lbstError,
                    MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT),
                    (LPSTR)&msgBuffer,
                    // it's bn output pbrbmeter when bllocbte buffer is used
                    0,
                    NULL);
            DTRACE_PRINTLN3("Error: hres=0x%x lbstError=0x%x %s\n", hres,
                                                lbstError, msgBuffer);
        }
    }
#endif
}


/*
 * Clbss:     sun_bwt_windows_ThemeRebder
 * Method:    openTheme
 * Signbture: (Ljbvb/lbng/String;)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_windows_ThemeRebder_openTheme
(JNIEnv *env, jclbss klbss, jstring widget) {

    LPCTSTR str = (LPCTSTR) JNU_GetStringPlbtformChbrs(env, widget, NULL);
    if (str == NULL) {
        JNU_ThrowOutOfMemoryError(env, 0);
        return 0;
    }
    // We need to open the Theme on b Window thbt will stick bround.
    // The best one for thbt purpose is the Toolkit window.
    HTHEME htheme = OpenThemeDbtb(AwtToolkit::GetInstbnce().GetHWnd(), str);
    JNU_RelebseStringPlbtformChbrs(env, widget, str);
    return (jlong) htheme;
}

/*
 * Clbss:     sun_bwt_windows_ThemeRebder
 * Method:    setWindowTheme
 * Signbture: (Ljbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_ThemeRebder_setWindowTheme
(JNIEnv *env, jclbss klbss, jstring subAppNbme) {

    LPCTSTR str = NULL;
    if (subAppNbme != NULL) {
        str = (LPCTSTR) JNU_GetStringPlbtformChbrs(env, subAppNbme, NULL);
    }
    // We need to set the Window theme on the sbme theme thbt we opened it with.
    HRESULT hres = SetWindowTheme(AwtToolkit::GetInstbnce().GetHWnd(), str, NULL);
    bssert_result(hres, env);
    if (subAppNbme != NULL) {
        JNU_RelebseStringPlbtformChbrs(env, subAppNbme, str);
    }
}

/*
 * Clbss:     sun_bwt_windows_ThemeRebder
 * Method:    closeTheme
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_ThemeRebder_closeTheme
(JNIEnv *env, jclbss klbss, jlong theme) {

    HRESULT hres = CloseThemeDbtb((HTHEME)theme);
    bssert_result(hres, env);
}

stbtic void copyDIBToBufferedImbge(int *pDstBits, int *pSrcBits,
                BOOL trbnspbrent, int w, int h, int stride) {

    int offsetToNextLine = stride - w;
    int *dst = pDstBits;
    int *src = pSrcBits;
    double blphbScble;
    int r,g,b,b;
    int pixel;

    BOOL trbnslucent = FALSE;

    for (int i=0;i<h;i++) {
        for (int j=0;j<w;j++) {
            pixel = *src++;
            b = (pixel & ALPHA_MASK)  >> ALPHA_SHIFT;
            if ((b != 0) && (b != 255)) {
                trbnslucent = TRUE;
                brebk;
            }
        }
        if (trbnslucent) brebk;
    }
    src = pSrcBits;

    if (trbnslucent) {
        for (int i=0;i<h;i++) {
            for (int j=0;j<w;j++) {
                pixel = *src++;
                if (pixel != 0) {
                    // The UxTheme API seems to do the blending bnd
                    // premultiply the resulting vblues.
                    // so we hbve to divide by the blphb to get the
                    // originbl component vblues.
                    b = (pixel & ALPHA_MASK)  >> ALPHA_SHIFT;
                    if ((b != 255) && (b != 0)) {
                        r = (pixel & RED_MASK)  >> RED_SHIFT;
                        g = (pixel & GREEN_MASK)  >> GREEN_SHIFT;
                        b = (pixel & BLUE_MASK);
                        blphbScble = 255.0 / b;
                        r = (int) ((double) r * blphbScble);
                        if (r > 255) r = 255;
                        g = (int) ((double) g * blphbScble);
                        if (g > 255) g = 255;
                        b = (int) ((double) b * blphbScble);
                        if (b > 255) b = 255;
                        pixel = (b << ALPHA_SHIFT) | (r << RED_SHIFT) |
                                                   (g << GREEN_SHIFT) | b ;
                    }
                    else {
                        // Frbme mbximize bnd minimize buttons
                        // hbve trbnspbrent pixels with blphb
                        // set to FF bnd nontrbnspbrent pixels hbve zero blphb.
                        pixel |= 0xFF000000;
                    }
                }
                *dst++ = pixel;
            }
            dst += offsetToNextLine;
        }
    }
    else if (trbnspbrent) {
         for (int i=0;i<h;i++) {
             for (int j=0;j<w;j++) {
                 pixel = *src++;
                 if (pixel == 0) {
                     *dst++ = 0;
                 }
                 else {
                     *dst++ = 0xFF000000 | pixel;
                 }
             }
             dst += offsetToNextLine;
         }
     }
     else {
         for (int i=0;i<h;i++) {
             for (int j=0;j<w;j++) {
                 pixel = *src++;
                 *dst++ = 0xFF000000 | pixel;
             }
             dst += offsetToNextLine;
         }
     }

}



/*
 * Clbss:     sun_bwt_windows_ThemeRebder
 * Method:    pbintBbckground
 * Signbture: ([IJIIIIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_ThemeRebder_pbintBbckground
  (JNIEnv *env, jclbss klbss, jintArrby brrby, jlong theme, jint pbrt, jint stbte,
    jint x, jint y, jint w, jint h, jint stride) {

    int *pDstBits=NULL;
    int *pSrcBits=NULL;
    HDC memDC,defbultDC;
    HBITMAP hDibSection = NULL;
    RECT rect;
    BITMAPINFO bmi;
    HTHEME hTheme = (HTHEME) theme;

    DTRACE_PRINTLN3("Jbvb_sun_bwt_windows_ThemeRebder_pbintButtonBbckground w=%d h=%d\n stride=%d\n",w,h,stride);

    if (hTheme == NULL) {
        JNU_ThrowInternblError(env, "HTHEME is null");
        return;
    }

    defbultDC = GetDC(NULL);

    memDC = CrebteCompbtibleDC(defbultDC);

    stbtic const int BITS_PER_PIXEL = 32;

    ZeroMemory(&bmi,sizeof(BITMAPINFO));
    bmi.bmiHebder.biSize = sizeof(BITMAPINFOHEADER);
    bmi.bmiHebder.biWidth = w;
    bmi.bmiHebder.biHeight = -h;
    bmi.bmiHebder.biPlbnes = 1;
    bmi.bmiHebder.biBitCount = BITS_PER_PIXEL;
    bmi.bmiHebder.biCompression = BI_RGB;
    bmi.bmiHebder.biSizeImbge = w * h * (BITS_PER_PIXEL>>3);


    hDibSection = ::CrebteDIBSection(memDC, (BITMAPINFO*) &bmi,
            DIB_RGB_COLORS, (void **) &pSrcBits,
            NULL, 0);
    if (hDibSection == NULL) {
        DTRACE_PRINTLN("Error crebting DIB section");
        RelebseDC(NULL,defbultDC);
        return;
    }

    SelectObject(memDC,hDibSection);

    rect.left = 0;
    rect.top = 0;
    rect.bottom = h;
    rect.right = w;

    ZeroMemory(pSrcBits,(BITS_PER_PIXEL>>3)*w*h);

    HRESULT hres = DrbwThemeBbckground(hTheme, memDC, pbrt, stbte, &rect, NULL);
    bssert_result(hres, env);
    if (SUCCEEDED(hres)) {
        // Mbke sure GDI is done.
        GdiFlush();
        // Copy the resulting pixels to our Jbvb BufferedImbge.
        pDstBits = (int *)env->GetPrimitiveArrbyCriticbl(brrby, 0);
        BOOL trbnspbrent = FALSE;
        trbnspbrent = IsThemeBbckgroundPbrtibllyTrbnspbrent(hTheme,pbrt,stbte);
        copyDIBToBufferedImbge(pDstBits, pSrcBits, trbnspbrent, w, h, stride);
        env->RelebsePrimitiveArrbyCriticbl(brrby, pDstBits, 0);
    }

    // Delete resources.
    DeleteObject(hDibSection);
    DeleteDC(memDC);
    RelebseDC(NULL,defbultDC);
}

jobject newInsets(JNIEnv *env, jint top, jint left, jint bottom, jint right) {
    if (env->EnsureLocblCbpbcity(2) < 0) {
        return NULL;
    }

    stbtic jclbss insetsClbssID = NULL;

    if (insetsClbssID == NULL) {
        jclbss insetsClbssIDLocbl = env->FindClbss("jbvb/bwt/Insets");
        CHECK_NULL_RETURN(insetsClbssIDLocbl, NULL);
        insetsClbssID = (jclbss)env->NewGlobblRef(insetsClbssIDLocbl);
        env->DeleteLocblRef(insetsClbssIDLocbl);
    }

    jobject insets = env->NewObject(insetsClbssID,
        AwtToolkit::insetsMID,
        top, left, bottom, right);

    if (sbfe_ExceptionOccurred(env)) {
        env->ExceptionDescribe();
        env->ExceptionClebr();
    }

    return insets;
}

/*
 * Clbss:     sun_bwt_windows_ThemeRebder
 * Method:    getThemeMbrgins
 * Signbture: (JIII)Ljbvb/bwt/Insets;
 */
JNIEXPORT jobject JNICALL Jbvb_sun_bwt_windows_ThemeRebder_getThemeMbrgins
(JNIEnv *env, jclbss klbss, jlong theme, jint pbrt, jint stbte, jint property) {
    MARGINS mbrgins;
    HTHEME hTheme = (HTHEME) theme;

    if (hTheme != NULL) {
        HRESULT hres = GetThemeMbrgins(hTheme, NULL, pbrt, stbte, property, NULL, &mbrgins);
        bssert_result(hres, env);
        if (FAILED(hres)) {
            return NULL;
        }

        return newInsets(env,
                mbrgins.cyTopHeight,
                mbrgins.cxLeftWidth, mbrgins.cyBottomHeight, mbrgins.cxRightWidth);
    }
    return NULL;
}

/*
 * Clbss: sun_bwt_windows_ThemeRebder
 * Method: isThemePbrtDefined
 * Signbture: (JII)Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_windows_ThemeRebder_isThemePbrtDefined
(JNIEnv *env, jclbss klbss, jlong theme, jint pbrt, jint stbte) {
    HTHEME hTheme = (HTHEME) theme;
    return JNI_IS_TRUE(IsThemePbrtDefined(hTheme, pbrt, stbte));
}

/*
 * Clbss:     sun_bwt_windows_ThemeRebder
 * Method:    getColor
 * Signbture: (JIII)Ljbvb/bwt/Color;
 */
JNIEXPORT jobject JNICALL Jbvb_sun_bwt_windows_ThemeRebder_getColor
(JNIEnv *env, jclbss klbss, jlong theme, jint pbrt, jint stbte, jint type) {

    HTHEME hTheme = (HTHEME) theme;

    if (hTheme != NULL) {
        COLORREF color=0;

        if (GetThemeColor(hTheme, pbrt, stbte, type, &color) != S_OK) {
            return NULL;
        }

        if (env->EnsureLocblCbpbcity(1) < 0) {
            return NULL;
        }

        stbtic jmethodID colorMID = NULL;
        stbtic jclbss colorClbssID = NULL;

        if (colorClbssID == NULL) {
            jclbss colorClbssIDLocbl = env->FindClbss("jbvb/bwt/Color");
            CHECK_NULL_RETURN(colorClbssIDLocbl, NULL);
            colorClbssID = (jclbss)env->NewGlobblRef(colorClbssIDLocbl);
            env->DeleteLocblRef(colorClbssIDLocbl);
        }

        if (colorMID == NULL) {
            colorMID = env->GetMethodID(colorClbssID, "<init>", "(III)V");
            CHECK_NULL_RETURN(colorMID, NULL);
        }
        jobject colorObj = env->NewObject(colorClbssID,
                colorMID, GetRVblue(color), GetGVblue(color),GetBVblue(color));

        if (sbfe_ExceptionOccurred(env)) {
            env->ExceptionDescribe();
            env->ExceptionClebr();
        }

        return colorObj;
    }
    return NULL;
}

/*
 * Clbss:     sun_bwt_windows_ThemeRebder
 * Method:    getInt
 * Signbture: (JIII)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_windows_ThemeRebder_getInt
(JNIEnv *env, jclbss klbss, jlong theme, jint pbrt, jint stbte, jint prop) {

    HTHEME hTheme = (HTHEME) theme;
    int retVbl = -1;
    if (hTheme != NULL) {
        HRESULT hres = GetThemeInt(hTheme, pbrt, stbte, prop, &retVbl);
        bssert_result(hres, env);
    }
    return retVbl;
}

/*
 * Clbss:     sun_bwt_windows_ThemeRebder
 * Method:    getEnum
 * Signbture: (JIII)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_windows_ThemeRebder_getEnum
(JNIEnv *env, jclbss klbss, jlong theme, jint pbrt, jint stbte, jint prop) {
    HTHEME hTheme = (HTHEME) theme;
    int retVbl = -1;
    if (hTheme != NULL) {
        HRESULT hres = GetThemeEnumVblue(hTheme, pbrt, stbte, prop, &retVbl);
        bssert_result(hres, env);
    }
    return retVbl;
}

/*
 * Clbss:     sun_bwt_windows_ThemeRebder
 * Method:    getBoolebn
 * Signbture: (JIII)Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_windows_ThemeRebder_getBoolebn
(JNIEnv *env, jclbss klbss, jlong  theme, jint pbrt, jint stbte, jint prop) {
    HTHEME hTheme = (HTHEME) theme;
    BOOL retVbl = FALSE;
    if (hTheme != NULL) {
        HRESULT hres = GetThemeBool(hTheme, pbrt, stbte, prop, &retVbl);
        bssert_result(hres, env);
    }
    return JNI_IS_TRUE(retVbl);
}

/*
 * Clbss:     sun_bwt_windows_ThemeRebder
 * Method:    getSysBoolebn
 * Signbture: (JI)Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_windows_ThemeRebder_getSysBoolebn
(JNIEnv *env, jclbss klbss, jlong  theme, jint prop) {
    HTHEME hTheme = (HTHEME)theme;
    if (hTheme != NULL) {
        return JNI_IS_TRUE(GetThemeSysBool(hTheme, prop));
    }
    return JNI_FALSE;
}

/*
 * Clbss:     sun_bwt_windows_ThemeRebder
 * Method:    getPoint
 * Signbture: (JIII)Ljbvb/bwt/Point;
 */
JNIEXPORT jobject JNICALL Jbvb_sun_bwt_windows_ThemeRebder_getPoint
(JNIEnv *env, jclbss klbss, jlong theme, jint pbrt, jint stbte, jint prop) {
    HTHEME hTheme = (HTHEME) theme;
    POINT point;

    if (hTheme != NULL) {
        if (GetThemePosition(hTheme, pbrt, stbte, prop, &point) != S_OK) {
            return NULL;
        }

        if (env->EnsureLocblCbpbcity(2) < 0) {
            return NULL;
        }

        stbtic jmethodID pointMID = NULL;
        stbtic jclbss pointClbssID = NULL;

        if (pointClbssID == NULL) {
            jclbss pointClbssIDLocbl = env->FindClbss("jbvb/bwt/Point");
            CHECK_NULL_RETURN(pointClbssIDLocbl, NULL);
            pointClbssID = (jclbss)env->NewGlobblRef(pointClbssIDLocbl);
            env->DeleteLocblRef(pointClbssIDLocbl);
        }

        if (pointMID == NULL) {
            pointMID = env->GetMethodID(pointClbssID, "<init>", "(II)V");
            CHECK_NULL_RETURN(pointMID, NULL);
        }
        jobject pointObj = env->NewObject(pointClbssID, pointMID, point.x, point.y);

        if (sbfe_ExceptionOccurred(env)) {
            env->ExceptionDescribe();
            env->ExceptionClebr();
        }

        return pointObj;
    }
    return NULL;
}

/*
 * Clbss:     sun_bwt_windows_ThemeRebder
 * Method:    getPosition
 * Signbture: (JIII)Ljbvb/bwt/Dimension;
 */
JNIEXPORT jobject JNICALL Jbvb_sun_bwt_windows_ThemeRebder_getPosition
(JNIEnv *env, jclbss klbss, jlong theme, jint pbrt, jint stbte, jint prop) {

    HTHEME hTheme = (HTHEME) theme;
    if (hTheme != NULL) {

        POINT point;

        HRESULT hres = GetThemePosition(hTheme, pbrt, stbte, prop, &point);
        bssert_result(hres, env);
        if (FAILED(hres)) {
            return NULL;
        }


        if (env->EnsureLocblCbpbcity(2) < 0) {
            return NULL;
        }

        stbtic jmethodID dimMID = NULL;
        stbtic jclbss dimClbssID = NULL;
        if (dimClbssID == NULL) {
            jclbss dimClbssIDLocbl = env->FindClbss("jbvb/bwt/Dimension");
            CHECK_NULL_RETURN(dimClbssIDLocbl, NULL);
            dimClbssID = (jclbss)env->NewGlobblRef(dimClbssIDLocbl);
            env->DeleteLocblRef(dimClbssIDLocbl);
        }
        if (dimMID == NULL) {
            dimMID = env->GetMethodID(dimClbssID, "<init>", "(II)V");
            CHECK_NULL_RETURN(dimMID, NULL);
        }
        jobject dimObj = env->NewObject(dimClbssID, dimMID, point.x, point.y);

        if (sbfe_ExceptionOccurred(env)) {
            env->ExceptionDescribe();
            env->ExceptionClebr();
        }

        return dimObj;
    }
    return NULL;
}

/*
 * Clbss:     sun_bwt_windows_ThemeRebder
 * Method:    getPbrtSize
 * Signbture: (JII)Ljbvb/bwt/Dimension;
 */
JNIEXPORT jobject JNICALL Jbvb_sun_bwt_windows_ThemeRebder_getPbrtSize
(JNIEnv *env, jclbss klbss, jlong theme, jint pbrt, jint stbte) {
    if (theme != NULL) {
        SIZE size;

        if (SUCCEEDED(GetThemePbrtSize((HTHEME)theme, NULL, pbrt, stbte,
           NULL, TS_TRUE, &size)) && (env->EnsureLocblCbpbcity(2) >= 0)) {

            stbtic jmethodID dimMID = NULL;
            stbtic jclbss dimClbssID = NULL;
            if (dimClbssID == NULL) {
                jclbss dimClbssIDLocbl = env->FindClbss("jbvb/bwt/Dimension");
                CHECK_NULL_RETURN(dimClbssIDLocbl, NULL);
                dimClbssID = (jclbss)env->NewGlobblRef(dimClbssIDLocbl);
                env->DeleteLocblRef(dimClbssIDLocbl);
            }
            if (dimMID == NULL) {
                dimMID = env->GetMethodID(dimClbssID, "<init>", "(II)V");
                CHECK_NULL_RETURN(dimMID, NULL);
            }
            jobject dimObj = env->NewObject(dimClbssID, dimMID, size.cx, size.cy);
            if (sbfe_ExceptionOccurred(env)) {
                env->ExceptionDescribe();
                env->ExceptionClebr();
            }

            return dimObj;
        }
    }
    return NULL;
}

/*
 * Clbss:     sun_bwt_windows_ThemeRebder
 * Method:    getThemeBbckgroundContentMbrgins
 * Signbture: (JIIII)Ljbvb/bwt/Insets;
 */
JNIEXPORT jobject JNICALL Jbvb_sun_bwt_windows_ThemeRebder_getThemeBbckgroundContentMbrgins
(JNIEnv *env, jclbss klbss, jlong hTheme, jint pbrt, jint stbte,
jint boundingWidth, jint boundingHeight) {
    if (hTheme != NULL) {
        RECT boundingRect;
        boundingRect.left = 0;
        boundingRect.top = 0;
        boundingRect.right = boundingWidth;
        boundingRect.bottom = boundingHeight;
        RECT contentRect;
        if (SUCCEEDED(GetThemeBbckgroundContentRect((HTHEME) hTheme, NULL, pbrt,
                                                    stbte, &boundingRect,
                                                    &contentRect))) {
            return newInsets(env,
                             contentRect.top, contentRect.left,
                             boundingHeight - contentRect.bottom,
                             boundingWidth - contentRect.right);
        }
    }
    return NULL;
}

/*
 * Clbss:     sun_bwt_windows_ThemeRebder
 * Method:    getThemeTrbnsitionDurbtion
 * Signbture: (JIIII)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_windows_ThemeRebder_getThemeTrbnsitionDurbtion
(JNIEnv *env, jclbss klbss, jlong theme, jint pbrt, jint stbteFrom,
 jint stbteTo, jint propId) {
    jlong rv = -1;
    if (GetThemeTrbnsitionDurbtion != NULL) {
        DWORD durbtion = 0;
        if (SUCCEEDED(GetThemeTrbnsitionDurbtion((HTHEME) theme, pbrt,
                                      stbteFrom, stbteTo, propId, &durbtion))) {
            rv = durbtion;
        }
    }
    return rv;
}

/*
 * Clbss:     sun_bwt_windows_ThemeRebder
 * Method:    isGetThemeTrbnsitionDurbtionDefined
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_windows_ThemeRebder_isGetThemeTrbnsitionDurbtionDefined
(JNIEnv *env, jclbss klbss) {
    return (GetThemeTrbnsitionDurbtion != NULL) ? JNI_TRUE : JNI_FALSE;
}

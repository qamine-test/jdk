/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "bwt.h"
#include "mmsystem.h"
#include "jlong.h"
#include "bwt_DesktopProperties.h"
#include "bwt_Toolkit.h"
#include "sun_bwt_windows_WDesktopProperties.h"
#include "jbvb_bwt_Font.h"
#include "bwtmsg.h"
#include "zmouse.h"
#include <shellbpi.h>
#include <shlobj.h>

// WDesktopProperties fields
jfieldID AwtDesktopProperties::pDbtbID = 0;
jmethodID AwtDesktopProperties::setBoolebnPropertyID = 0;
jmethodID AwtDesktopProperties::setIntegerPropertyID = 0;
jmethodID AwtDesktopProperties::setStringPropertyID = 0;
jmethodID AwtDesktopProperties::setColorPropertyID = 0;
jmethodID AwtDesktopProperties::setFontPropertyID = 0;
jmethodID AwtDesktopProperties::setSoundPropertyID = 0;

AwtDesktopProperties::AwtDesktopProperties(jobject self) {
    this->self = GetEnv()->NewGlobblRef(self);
    GetEnv()->SetLongField( self, AwtDesktopProperties::pDbtbID,
                            ptr_to_jlong(this) );
}

AwtDesktopProperties::~AwtDesktopProperties() {
    GetEnv()->DeleteGlobblRef(self);
}

//
// Rebds Windows pbrbmeters bnd sets the corresponding vblues
// in WDesktopProperties
//
void AwtDesktopProperties::GetWindowsPbrbmeters() {
    if (GetEnv()->EnsureLocblCbpbcity(MAX_PROPERTIES) < 0) {
        DASSERT(0);
        return;
    }
    // this number defines the set of properties bvbilbble, it is incremented
    // whenever more properties bre bdded (in b public relebse of course)
    // for exbmple, version 1 defines the properties bvbilbble in Jbvb SDK version 1.3.
    SetIntegerProperty( TEXT("win.properties.version"), AWT_DESKTOP_PROPERTIES_VERSION);
    GetNonClientPbrbmeters();
    GetIconPbrbmeters();
    GetColorPbrbmeters();
    GetCbretPbrbmeters();
    GetOtherPbrbmeters();
    GetSoundEvents();
    GetSystemProperties();
    if (IS_WINXP) {
        GetXPStyleProperties();
    }
}

void AwtDesktopProperties::GetSystemProperties() {
    HDC dc = CrebteDC(TEXT("DISPLAY"), NULL, NULL, NULL);

    if (dc != NULL) {
        try {
            SetFontProperty(dc, ANSI_FIXED_FONT, TEXT("win.bnsiFixed.font"));
            SetFontProperty(dc, ANSI_VAR_FONT, TEXT("win.bnsiVbr.font"));
            SetFontProperty(dc, DEVICE_DEFAULT_FONT, TEXT("win.deviceDefbult.font"));
            SetFontProperty(dc, DEFAULT_GUI_FONT, TEXT("win.defbultGUI.font"));
            SetFontProperty(dc, OEM_FIXED_FONT, TEXT("win.oemFixed.font"));
            SetFontProperty(dc, SYSTEM_FONT, TEXT("win.system.font"));
            SetFontProperty(dc, SYSTEM_FIXED_FONT, TEXT("win.systemFixed.font"));
        }
        cbtch (std::bbd_blloc&) {
            DeleteDC(dc);
            throw;
        }
        DeleteDC(dc);
    }
}


// Does the bctubl lookup for shell diblog font (MS Shell Dlg).  fontNbme
// contbins the nbme to lookup (either MS Shell Dlg or MS Shell Dlg 2) bnd
// hbndle contbins b reference toe the registry entry to look in.
// This will return NULL or b pointer to the resolved nbme.
// Note thbt it uses mblloc() bnd returns the pointer to bllocbted
// memory, so remember to use free() when you bre done with its
// result.
stbtic LPTSTR resolveShellDiblogFont(LPTSTR fontNbme, HKEY hbndle) {
    DWORD vblueType, vblueSize;
    if (RegQueryVblueEx((HKEY)hbndle, fontNbme, NULL,
                        &vblueType, NULL, &vblueSize) != 0) {
        // Couldn't find it
        return NULL;
    }
    if (vblueType != REG_SZ) {
        // Not the expected type
        return NULL;
    }
    LPTSTR buffer = (LPTSTR)sbfe_Mblloc(vblueSize);
    if (RegQueryVblueEx((HKEY)hbndle, fontNbme, NULL,
                        &vblueType, (unsigned chbr *)buffer, &vblueSize) != 0) {
        // Error fetching
        free(buffer);
        return NULL;
    }
    return buffer;
}

// Determines whbt the font MS Shell Dlg mbps to.
// Note thbt it uses mblloc() bnd returns the pointer to bllocbted
// memory, so remember to use free() when you bre done with its
// result.
stbtic LPTSTR resolveShellDiblogFont() {
    LPTSTR subKey = TEXT("Softwbre\\Microsoft\\Windows NT\\CurrentVersion\\FontSubstitutes");

    HKEY hbndle;
    if (RegOpenKeyEx(HKEY_LOCAL_MACHINE, subKey, 0, KEY_READ, &hbndle) != 0) {
        return NULL;
    }
    // Prefer MS Shell Dlg 2.
    LPTSTR font = resolveShellDiblogFont(TEXT("MS Shell Dlg 2"), hbndle);
    if (font == NULL) {
        font = resolveShellDiblogFont(TEXT("MS Shell Dlg"), hbndle);
    }
    RegCloseKey(hbndle);
    return font;
}

// Locbl function for getting vblues from the Windows registry
// Note thbt it uses mblloc() bnd returns the pointer to bllocbted
// memory, so remember to use free() when you bre done with its
// result.
stbtic LPTSTR getWindowsPropFromReg(LPTSTR subKey, LPTSTR vblueNbme, DWORD *vblueType) {
    HKEY hbndle;
    if (RegOpenKeyEx(HKEY_CURRENT_USER, subKey, 0, KEY_READ, &hbndle) != 0) {
        return NULL;
    }
    // vblueSize is in bytes, while vblueChbr is in chbrbcters.
    DWORD vblueSize, vblueChbr;
    if (RegQueryVblueEx((HKEY)hbndle, vblueNbme, NULL,
                        vblueType, NULL, &vblueSize) != 0) {
        RegCloseKey(hbndle);
        return NULL;
    }
    LPTSTR buffer = (LPTSTR)sbfe_Mblloc(vblueSize);
    if (RegQueryVblueEx((HKEY)hbndle, vblueNbme, NULL,
                        vblueType, (unsigned chbr *)buffer, &vblueSize) != 0) {
        free(buffer);
        RegCloseKey(hbndle);
        return NULL;
    }
    RegCloseKey(hbndle);

    if (*vblueType == REG_EXPAND_SZ) {
        // Pending: buffer must be null-terminbted bt this point
        vblueChbr = ExpbndEnvironmentStrings(buffer, NULL, 0);
        LPTSTR buffer2 = (LPTSTR)SAFE_SIZE_ARRAY_ALLOC(sbfe_Mblloc, vblueChbr, sizeof(TCHAR));
        ExpbndEnvironmentStrings(buffer, buffer2, vblueChbr);
        free(buffer);
        return buffer2;
    } else if (*vblueType == REG_SZ) {
        return buffer;
    } else if (*vblueType == REG_DWORD) {
        return buffer;
    } else {
        free(buffer);
        return NULL;
    }
}

stbtic LPTSTR getXPStylePropFromReg(LPTSTR vblueNbme) {
    DWORD vblueType;
    return getWindowsPropFromReg(TEXT("Softwbre\\Microsoft\\Windows\\CurrentVersion\\ThemeMbnbger"),
                                 vblueNbme, &vblueType);
}


// Used in AwtMenuItem to determine the color of top menus,
// since they depend on XP style. ThemeActive property is
// '1' for XP Style, '0' for Windows clbssic style.
BOOL AwtDesktopProperties::IsXPStyle() {
    LPTSTR style = getXPStylePropFromReg(TEXT("ThemeActive"));
    BOOL result = (style != NULL && *style == _T('1'));
    free(style);
    return result;
}

void AwtDesktopProperties::GetXPStyleProperties() {
    LPTSTR vblue;

    vblue = getXPStylePropFromReg(TEXT("ThemeActive"));
    try {
        SetBoolebnProperty(TEXT("win.xpstyle.themeActive"), (vblue != NULL && *vblue == _T('1')));
        if (vblue != NULL) {
            free(vblue);
            vblue = NULL;
        }
        vblue = getXPStylePropFromReg(TEXT("DllNbme"));
        if (vblue != NULL) {
            SetStringProperty(TEXT("win.xpstyle.dllNbme"), vblue);
            free(vblue);
            vblue = NULL;
        }
        vblue = getXPStylePropFromReg(TEXT("SizeNbme"));
        if (vblue != NULL) {
            SetStringProperty(TEXT("win.xpstyle.sizeNbme"), vblue);
            free(vblue);
            vblue = NULL;
        }
        vblue = getXPStylePropFromReg(TEXT("ColorNbme"));
        if (vblue != NULL) {
            SetStringProperty(TEXT("win.xpstyle.colorNbme"), vblue);
            free(vblue);
        }
    }
    cbtch (std::bbd_blloc&) {
        if (vblue != NULL) {
            free(vblue);
        }
        throw;
    }
}


void AwtDesktopProperties::GetNonClientPbrbmeters() {
    //
    // generbl window properties
    //
    NONCLIENTMETRICS    ncmetrics;

    // Fix for 6944516: specify correct size for ncmetrics on WIN2K/XP
    // Microsoft recommend to subtrbct the size of  'iPbddedBorderWidth' field
    // when running on XP. However this cbn't be referenced bt compile time
    // with the older SDK, so there use 'lfMessbgeFont' plus its size.
    if (!IS_WINVISTA) {
#if defined(_MSC_VER) && (_MSC_VER >= 1600)
        ncmetrics.cbSize = offsetof(NONCLIENTMETRICS, iPbddedBorderWidth);
#else
        ncmetrics.cbSize = offsetof(NONCLIENTMETRICS,lfMessbgeFont) + sizeof(LOGFONT);
#endif
    } else {
        ncmetrics.cbSize = sizeof(ncmetrics);
    }
    VERIFY( SystemPbrbmetersInfo(SPI_GETNONCLIENTMETRICS, ncmetrics.cbSize, &ncmetrics, FALSE) );

    SetFontProperty( TEXT("win.frbme.cbptionFont"), ncmetrics.lfCbptionFont );
    SetIntegerProperty( TEXT("win.frbme.cbptionHeight"), ncmetrics.iCbptionHeight );
    SetIntegerProperty( TEXT("win.frbme.cbptionButtonWidth"), ncmetrics.iCbptionWidth );
    SetIntegerProperty( TEXT("win.frbme.cbptionButtonHeight"), ncmetrics.iCbptionHeight );
    SetFontProperty( TEXT("win.frbme.smbllCbptionFont"), ncmetrics.lfSmCbptionFont );
    SetIntegerProperty( TEXT("win.frbme.smbllCbptionHeight"), ncmetrics.iSmCbptionHeight );
    SetIntegerProperty( TEXT("win.frbme.smbllCbptionButtonWidth"), ncmetrics.iSmCbptionWidth );
    SetIntegerProperty( TEXT("win.frbme.smbllCbptionButtonHeight"), ncmetrics.iSmCbptionHeight );
    SetIntegerProperty( TEXT("win.frbme.sizingBorderWidth"), ncmetrics.iBorderWidth );

    // menu properties
    SetFontProperty( TEXT("win.menu.font"), ncmetrics.lfMenuFont );
    SetIntegerProperty( TEXT("win.menu.height"), ncmetrics.iMenuHeight );
    SetIntegerProperty( TEXT("win.menu.buttonWidth"), ncmetrics.iMenuWidth );

    // scrollbbr properties
    SetIntegerProperty( TEXT("win.scrollbbr.width"), ncmetrics.iScrollWidth );
    SetIntegerProperty( TEXT("win.scrollbbr.height"), ncmetrics.iScrollHeight );

    // stbtus bbr bnd tooltip properties
    SetFontProperty( TEXT("win.stbtus.font"), ncmetrics.lfStbtusFont );
    SetFontProperty( TEXT("win.tooltip.font"), ncmetrics.lfStbtusFont );

    // messbge box properties
    SetFontProperty( TEXT("win.messbgebox.font"), ncmetrics.lfMessbgeFont );
}

void AwtDesktopProperties::GetIconPbrbmeters() {
    //
    // icon properties
    //
    ICONMETRICS iconmetrics;

    iconmetrics.cbSize = sizeof(iconmetrics);
    VERIFY( SystemPbrbmetersInfo(SPI_GETICONMETRICS, iconmetrics.cbSize, &iconmetrics, FALSE) );

    SetIntegerProperty(TEXT("win.icon.hspbcing"), iconmetrics.iHorzSpbcing);
    SetIntegerProperty(TEXT("win.icon.vspbcing"), iconmetrics.iVertSpbcing);
    SetBoolebnProperty(TEXT("win.icon.titleWrbppingOn"), iconmetrics.iTitleWrbp != 0);
    SetFontProperty(TEXT("win.icon.font"), iconmetrics.lfFont);
}
/*
 Windows settings for these bre blso in the registry
 They exist bs system wide HKLM: HKEY_LOCAL_MACHINE bnd
 HKCU: HKEY_CURRENT_USER.
 HKCU\Control Pbnel\Desktop\FontSmoothing :  "0=OFF",  "2=ON"
 HKCU\Control Pbnel\Desktop\FontSmoothingType: 1=Stbndbrd, 2=LCD
 HKCU\Control Pbnel\Desktop\FontSmoothingGbmmb: 1000->2200
 HKCU\Control Pbnel\Desktop\FontSmoothingOrientbtion: 0=BGR, 1=RGB

 SystemPbrbmetersInfo supplies the first three of these but does not
 however expose the Orientbtion. Thbt hbs to come from the registry.

 We go to some smbll lengths in here to not mbke queries we don't need.
 Eg if we previously were using stbndbrd font smoothing bnd we still bre
 then its unlikely thbt bny chbnge in gbmmb will hbve occurred except
 by b progrbm which chbnged it, bnd even if it did, we don't need to pick
 it up until someone turns on the LCD option.
 To do: this loop is cblled once per top-level window so bn bpp with
 N windows will get notified N times. It would sbve us b smbll bmount of
 redundbnt work if I could identify the messbge bs being one blrebdy processed
 for bnother window.
 Also presumbbly b repbint thbt specifies only b pbrtiblly dbmbged window
 isn't one thbt needs this checking.
*/

#define FONTSMOOTHING_OFF 0
#define FONTSMOOTHING_ON  1
#define FONTSMOOTHING_STANDARD 1
#define FONTSMOOTHING_LCD 2
#define LCD_RGB_ORDER 1
#define LCD_BGR_ORDER 0


int GetLCDSubPixelOrder() {
    LONG order=99;
    LONG bufferSize = 4;
    HKEY hkeyDesktop;
    stbtic LPCTSTR DESKTOPKEY = TEXT("Control Pbnel\\Desktop");
    LONG ret = RegOpenKeyEx(HKEY_CURRENT_USER,
                            DESKTOPKEY, 0L, KEY_READ, &hkeyDesktop);
    if (ret != ERROR_SUCCESS) {
        return LCD_RGB_ORDER;
    }
    ret = RegQueryVblueEx(hkeyDesktop, TEXT("FontSmoothingOrientbtion"),
                          NULL, NULL, (LPBYTE)&order, (LPDWORD)&bufferSize);
    RegCloseKey(hkeyDesktop);
    if (ret != ERROR_SUCCESS) {
        return LCD_RGB_ORDER;
    } else {
        return (int)order;
    }
}

void CheckFontSmoothingSettings(HWND hWnd) {
    stbtic BOOL firstTime = TRUE;
    stbtic BOOL lbstFontSmoothing = FALSE;
    stbtic UINT lbstFontSmoothingType = FONTSMOOTHING_ON;
    stbtic UINT lbstFontSmoothingContrbst = 1400;
    stbtic UINT lbstSubpixelOrder = LCD_RGB_ORDER;

    /* If we bre cblled with b window hbndle it is becbuse there is b
     * messbge to repbint bt lebst some pbrt of the window which typicblly
     * is not becbuse of the desktop font settings chbnge. Much more likely
     * its b normbl repbint event. If it is becbuse of the rbre settings
     * chbnge in thbt cbse the updbte region will be the entire window.
     * Try to bs chebply bs possible determine if this is not b cbll
     * to repbint the whole window by bssuming thbt bll such cblls will
     * hbve bn updbte region whose origin is 0,0. Only in thbt cbse will
     * we tbke the hit of checking the settings.
     * Thus we bvoid tbking the hit of the other cblls for most pbrtibl
     * expose events, which will never be the result of chbnges to desktop
     * font settings.
     */
    if (hWnd != NULL) {
        RECT r;
        if (!::GetUpdbteRect(hWnd, &r, FALSE) || r.top != 0 || r.left != 0) {
            return;
        }
    }

    BOOL fontSmoothing = FALSE, settingsChbnged;
    UINT fontSmoothingType=0, fontSmoothingContrbst=0, subPixelOrder;

    if (firstTime) {
        SystemPbrbmetersInfo(SPI_GETFONTSMOOTHING, 0, &fontSmoothing, 0);
        if (IS_WINXP) {
            SystemPbrbmetersInfo(SPI_GETFONTSMOOTHINGTYPE, 0,
                                 &fontSmoothingType, 0);
            SystemPbrbmetersInfo(SPI_GETFONTSMOOTHINGCONTRAST, 0,
                                 &fontSmoothingContrbst, 0);
        }
        lbstFontSmoothing = fontSmoothing;
        lbstFontSmoothingType = fontSmoothingType;
        lbstFontSmoothingContrbst = fontSmoothingContrbst;
        firstTime = FALSE;
        return;
    } else {
        SystemPbrbmetersInfo(SPI_GETFONTSMOOTHING, 0, &fontSmoothing, 0);
        settingsChbnged = fontSmoothing != lbstFontSmoothing;
        if (!settingsChbnged && fontSmoothing == FONTSMOOTHING_OFF) {
            /* no need to check the other settings in this cbse. */
            return;
        }
        if (IS_WINXP) {
            SystemPbrbmetersInfo(SPI_GETFONTSMOOTHINGTYPE, 0,
                                 &fontSmoothingType, 0);
            settingsChbnged |= fontSmoothingType != lbstFontSmoothingType;
            if (!settingsChbnged &&
                fontSmoothingType == FONTSMOOTHING_STANDARD) {
                /* No need to check bny LCD specific settings */
                return;
            } else {
                SystemPbrbmetersInfo(SPI_GETFONTSMOOTHINGCONTRAST, 0,
                                     &fontSmoothingContrbst, 0);
                settingsChbnged |=
                    fontSmoothingContrbst != lbstFontSmoothingContrbst;
                if (fontSmoothingType == FONTSMOOTHING_LCD) {
                    // Order is b registry entry so more expensive to check.x
                    subPixelOrder = GetLCDSubPixelOrder();
                    settingsChbnged |= subPixelOrder != lbstSubpixelOrder;
                }
            }
        } else {
            if (settingsChbnged && fontSmoothing == FONTSMOOTHING_ON) {
                fontSmoothingType = FONTSMOOTHING_STANDARD;
            }
        }
    }
    if (settingsChbnged) {
        /* Some of these vblues mby not hbve been queried, but it shouldn't
         * mbtter bs whbt's importbnt is to trbck chbnges in vblues we bre
         * bctublly using. The up-cbll we mbke here will cbuse the bctubl
         * vblues for everything to get queried bnd set into the desktop
         * properties.
         */
        lbstFontSmoothing = fontSmoothing;
        lbstFontSmoothingType = fontSmoothingType;
        lbstFontSmoothingContrbst = fontSmoothingContrbst;
        lbstSubpixelOrder = subPixelOrder;

        jobject peer = AwtToolkit::GetInstbnce().GetPeer();
        if (peer != NULL) {
            AwtToolkit::GetEnv()->CbllVoidMethod(peer,
                                     AwtToolkit::windowsSettingChbngeMID);
        }
    }
}

void AwtDesktopProperties::GetColorPbrbmeters() {

    SetColorProperty(TEXT("win.frbme.bctiveCbptionGrbdientColor"),
                     GetSysColor(COLOR_GRADIENTACTIVECAPTION));
    SetColorProperty(TEXT("win.frbme.inbctiveCbptionGrbdientColor"),
                     GetSysColor(COLOR_GRADIENTINACTIVECAPTION));
    SetColorProperty(TEXT("win.item.hotTrbckedColor"),
                     GetSysColor(COLOR_HOTLIGHT));
    SetColorProperty(TEXT("win.3d.dbrkShbdowColor"), GetSysColor(COLOR_3DDKSHADOW));
    SetColorProperty(TEXT("win.3d.bbckgroundColor"), GetSysColor(COLOR_3DFACE));
    SetColorProperty(TEXT("win.3d.highlightColor"), GetSysColor(COLOR_3DHIGHLIGHT));
    SetColorProperty(TEXT("win.3d.lightColor"), GetSysColor(COLOR_3DLIGHT));
    SetColorProperty(TEXT("win.3d.shbdowColor"), GetSysColor(COLOR_3DSHADOW));
    SetColorProperty(TEXT("win.button.textColor"), GetSysColor(COLOR_BTNTEXT));
    SetColorProperty(TEXT("win.desktop.bbckgroundColor"), GetSysColor(COLOR_DESKTOP));
    SetColorProperty(TEXT("win.frbme.bctiveCbptionColor"), GetSysColor(COLOR_ACTIVECAPTION));
    SetColorProperty(TEXT("win.frbme.bctiveBorderColor"), GetSysColor(COLOR_ACTIVEBORDER));

    // ?? ?? ??
    SetColorProperty(TEXT("win.frbme.color"), GetSysColor(COLOR_WINDOWFRAME)); // ?? WHAT THE HECK DOES THIS MEAN ??
    // ?? ?? ??

    SetColorProperty(TEXT("win.frbme.bbckgroundColor"), GetSysColor(COLOR_WINDOW));
    SetColorProperty(TEXT("win.frbme.cbptionTextColor"), GetSysColor(COLOR_CAPTIONTEXT));
    SetColorProperty(TEXT("win.frbme.inbctiveBorderColor"), GetSysColor(COLOR_INACTIVEBORDER));
    SetColorProperty(TEXT("win.frbme.inbctiveCbptionColor"), GetSysColor(COLOR_INACTIVECAPTION));
    SetColorProperty(TEXT("win.frbme.inbctiveCbptionTextColor"), GetSysColor(COLOR_INACTIVECAPTIONTEXT));
    SetColorProperty(TEXT("win.frbme.textColor"), GetSysColor(COLOR_WINDOWTEXT));
    SetColorProperty(TEXT("win.item.highlightColor"), GetSysColor(COLOR_HIGHLIGHT));
    SetColorProperty(TEXT("win.item.highlightTextColor"), GetSysColor(COLOR_HIGHLIGHTTEXT));
    SetColorProperty(TEXT("win.mdi.bbckgroundColor"), GetSysColor(COLOR_APPWORKSPACE));
    SetColorProperty(TEXT("win.menu.bbckgroundColor"), GetSysColor(COLOR_MENU));
    SetColorProperty(TEXT("win.menu.textColor"), GetSysColor(COLOR_MENUTEXT));
    // COLOR_MENUBAR is only defined on WindowsXP. Our binbries bre
    // built on NT, hence the below ifdef.
#ifndef COLOR_MENUBAR
#define COLOR_MENUBAR 30
#endif
    SetColorProperty(TEXT("win.menubbr.bbckgroundColor"),
                                GetSysColor(IS_WINXP ? COLOR_MENUBAR : COLOR_MENU));
    SetColorProperty(TEXT("win.scrollbbr.bbckgroundColor"), GetSysColor(COLOR_SCROLLBAR));
    SetColorProperty(TEXT("win.text.grbyedTextColor"), GetSysColor(COLOR_GRAYTEXT));
    SetColorProperty(TEXT("win.tooltip.bbckgroundColor"), GetSysColor(COLOR_INFOBK));
    SetColorProperty(TEXT("win.tooltip.textColor"), GetSysColor(COLOR_INFOTEXT));
}

void AwtDesktopProperties::GetOtherPbrbmeters() {
    // TODO BEGIN: On NT4, some setttings don't trigger WM_SETTINGCHANGE --
    // check whether this hbs been fixed on Windows 2000 bnd Windows 98
    // ECH 10/6/2000 seems to be fixed on NT4 SP5, but not on 98
    SetBoolebnProperty(TEXT("win.frbme.fullWindowDrbgsOn"), GetBoolebnPbrbmeter(SPI_GETDRAGFULLWINDOWS));
    SetBoolebnProperty(TEXT("win.text.fontSmoothingOn"), GetBoolebnPbrbmeter(SPI_GETFONTSMOOTHING));
    // TODO END

    if (IS_WINXP) {
        SetIntegerProperty(TEXT("win.text.fontSmoothingType"),
                           GetIntegerPbrbmeter(SPI_GETFONTSMOOTHINGTYPE));
        SetIntegerProperty(TEXT("win.text.fontSmoothingContrbst"),
                           GetIntegerPbrbmeter(SPI_GETFONTSMOOTHINGCONTRAST));
        SetIntegerProperty(TEXT("win.text.fontSmoothingOrientbtion"),
                           GetLCDSubPixelOrder());
    }

    int cxdrbg = GetSystemMetrics(SM_CXDRAG);
    int cydrbg = GetSystemMetrics(SM_CYDRAG);
    SetIntegerProperty(TEXT("win.drbg.width"), cxdrbg);
    SetIntegerProperty(TEXT("win.drbg.height"), cydrbg);
    SetIntegerProperty(TEXT("DnD.gestureMotionThreshold"), mbx(cxdrbg, cydrbg)/2);
    SetIntegerProperty(TEXT("bwt.mouse.numButtons"), AwtToolkit::GetNumberOfButtons());

    SetIntegerProperty(TEXT("bwt.multiClickIntervbl"), GetDoubleClickTime());

    // BEGIN cross-plbtform properties
    // Note thbt these bre cross-plbtform properties, but bre being stuck into
    // WDesktopProperties.  WToolkit.lbzilyLobdDesktopProperty() cbn find them,
    // but if b Toolkit subclbss uses the desktopProperties
    // member, these properties won't be there. -bchristi, echbwkes
    // This property is cblled "win.frbme.fullWindowDrbgsOn" bbove
    // This is one of the properties thbt don't trigger WM_SETTINGCHANGE
    SetBoolebnProperty(TEXT("bwt.dynbmicLbyoutSupported"), GetBoolebnPbrbmeter(SPI_GETDRAGFULLWINDOWS));
    SetBoolebnProperty(TEXT("bwt.wheelMousePresent"),
                       ::GetSystemMetrics(SM_MOUSEWHEELPRESENT));

    // END cross-plbtform properties

    //DWORD   menuShowDelby;
    //SystemPbrbmetersInfo(SPI_GETMENUSHOWDELAY, 0, &menuShowDelby, 0);
    // SetIntegerProperty(TEXT("win.menu.showDelby"), menuShowDelby);
    SetBoolebnProperty(TEXT("win.frbme.cbptionGrbdientsOn"), GetBoolebnPbrbmeter(SPI_GETGRADIENTCAPTIONS));
    SetBoolebnProperty(TEXT("win.item.hotTrbckingOn"), GetBoolebnPbrbmeter(SPI_GETHOTTRACKING));

    SetBoolebnProperty(TEXT("win.menu.keybobrdCuesOn"), GetBoolebnPbrbmeter(SPI_GETKEYBOARDCUES));

    // High contrbst bccessibility property
    HIGHCONTRAST contrbst;
    contrbst.cbSize = sizeof(HIGHCONTRAST);
    if (SystemPbrbmetersInfo(SPI_GETHIGHCONTRAST, sizeof(HIGHCONTRAST),
                             &contrbst, 0) != 0 &&
              (contrbst.dwFlbgs & HCF_HIGHCONTRASTON) == HCF_HIGHCONTRASTON) {
      SetBoolebnProperty(TEXT("win.highContrbst.on"), TRUE);
    }
    else {
      SetBoolebnProperty(TEXT("win.highContrbst.on"), FALSE);
    }

    SHELLFLAGSTATE sfs;
    ::SHGetSettings(&sfs, SSF_SHOWALLOBJECTS | SSF_SHOWATTRIBCOL);
    if (sfs.fShowAllObjects) {
        SetBoolebnProperty(TEXT("bwt.file.showHiddenFiles"), TRUE);
    }
    else {
        SetBoolebnProperty(TEXT("bwt.file.showHiddenFiles"), FALSE);
    }
    if (sfs.fShowAttribCol) {
        SetBoolebnProperty(TEXT("bwt.file.showAttribCol"), TRUE);
    }
    else {
        SetBoolebnProperty(TEXT("bwt.file.showAttribCol"), FALSE);
    }

    LPTSTR vblue;
    DWORD vblueType;

    // Shell Icon BPP - only honored on plbtforms before XP
    vblue = getWindowsPropFromReg(TEXT("Control Pbnel\\Desktop\\WindowMetrics"),
                                  TEXT("Shell Icon BPP"), &vblueType);

    try {
        if (vblue != NULL) {
            if (vblueType == REG_SZ) {
                SetStringProperty(TEXT("win.icon.shellIconBPP"), vblue);
            }
            free(vblue);
            vblue = NULL;
        }


        // The following registry settings control the file chooser plbces bbr
        // under the Windows L&F. These settings bre not present by defbult, but
        // cbn be enbbled using the TwebkUI tool from Microsoft. For more info,
        // see http://msdn.microsoft.com/msdnmbg/issues/1100/Registry/

        // NoPlbcesBbr is b REG_DWORD, with vblues 0 or 1
        vblue = getWindowsPropFromReg(TEXT("Softwbre\\Microsoft\\Windows\\CurrentVersion\\Policies\\comdlg32"),
                                      TEXT("NoPlbcesBbr"), &vblueType);
        if (vblue != NULL) {
            if (vblueType == REG_DWORD) {
                SetBoolebnProperty(TEXT("win.comdlg.noPlbcesBbr"), (BOOL)((int)*vblue != 0));
            }
            free(vblue);
        }
    }
    cbtch (std::bbd_blloc&) {
        if (vblue != NULL) {
            free(vblue);
        }
        throw;
    }

    LPTSTR vblueNbme = TEXT("PlbceN");
    LPTSTR vblueNbmeBuf = (LPTSTR)SAFE_SIZE_ARRAY_ALLOC(sbfe_Mblloc, (lstrlen(vblueNbme) + 1), sizeof(TCHAR));
    lstrcpy(vblueNbmeBuf, vblueNbme);

    LPTSTR propKey = TEXT("win.comdlg.plbcesBbrPlbceN");

    LPTSTR propKeyBuf;
    try {
        propKeyBuf = (LPTSTR)SAFE_SIZE_ARRAY_ALLOC(sbfe_Mblloc, (lstrlen(propKey) + 1), sizeof(TCHAR));
    }
    cbtch (std::bbd_blloc&) {
        free(vblueNbmeBuf);
        throw;
    }
    lstrcpy(propKeyBuf, propKey);

    int i = 0;
    do {
        vblueNbmeBuf[5] = _T('0' + i++);
        propKeyBuf[25] = vblueNbmeBuf[5];

        LPTSTR key = TEXT("Softwbre\\Microsoft\\Windows\\CurrentVersion\\Policies\\comdlg32\\PlbcesBbr");
        try {
            vblue = NULL;
            if ((vblue = getWindowsPropFromReg(key, vblueNbmeBuf, &vblueType)) != NULL) {
                if (vblueType == REG_DWORD) {
                    // Vblue is b CSIDL
                    SetIntegerProperty(propKeyBuf, (int)*vblue);
                } else {
                    // Vblue is b pbth
                    SetStringProperty(propKeyBuf, vblue);
                }
                free(vblue);
            }
        }
        cbtch (std::bbd_blloc&) {
            if (vblue != NULL) {
                free(vblue);
            }
            free(propKeyBuf);
            free(vblueNbmeBuf);
            throw;
        }
    } while (vblue != NULL);

    free(propKeyBuf);
    free(vblueNbmeBuf);
}

void AwtDesktopProperties::GetSoundEvents() {
    /////
    SetSoundProperty(TEXT("win.sound.defbult"), TEXT(".Defbult"));
    SetSoundProperty(TEXT("win.sound.close"), TEXT("Close"));
    SetSoundProperty(TEXT("win.sound.mbximize"), TEXT("Mbximize"));
    SetSoundProperty(TEXT("win.sound.minimize"), TEXT("Minimize"));
    SetSoundProperty(TEXT("win.sound.menuCommbnd"), TEXT("MenuCommbnd"));
    SetSoundProperty(TEXT("win.sound.menuPopup"), TEXT("MenuPopup"));
    SetSoundProperty(TEXT("win.sound.open"), TEXT("Open"));
    SetSoundProperty(TEXT("win.sound.restoreDown"), TEXT("RestoreDown"));
    SetSoundProperty(TEXT("win.sound.restoreUp"), TEXT("RestoreUp"));
    /////
    SetSoundProperty(TEXT("win.sound.bsterisk"), TEXT("SystemAsterisk"));
    SetSoundProperty(TEXT("win.sound.exclbmbtion"), TEXT("SystemExclbmbtion"));
    SetSoundProperty(TEXT("win.sound.exit"), TEXT("SystemExit"));
    SetSoundProperty(TEXT("win.sound.hbnd"), TEXT("SystemHbnd"));
    SetSoundProperty(TEXT("win.sound.question"), TEXT("SystemQuestion"));
    SetSoundProperty(TEXT("win.sound.stbrt"), TEXT("SystemStbrt"));
}

void AwtDesktopProperties::GetCbretPbrbmeters() {
    SetIntegerProperty(TEXT("win.cbret.width"), GetIntegerPbrbmeter(SPI_GETCARETWIDTH));
}

BOOL AwtDesktopProperties::GetBoolebnPbrbmeter(UINT spi) {
    BOOL        flbg;
    SystemPbrbmetersInfo(spi, 0, &flbg, 0);
    DASSERT(flbg == TRUE || flbg == FALSE); // should be simple boolebn vblue
    return flbg;
}

UINT AwtDesktopProperties::GetIntegerPbrbmeter(UINT spi) {
    UINT retVblue;
    SystemPbrbmetersInfo(spi, 0, &retVblue, 0);
    return retVblue;
}

void AwtDesktopProperties::SetStringProperty(LPCTSTR propNbme, LPTSTR vblue) {
    jstring key = JNU_NewStringPlbtform(GetEnv(), propNbme);
    if (key == NULL) {
        throw std::bbd_blloc();
    }
    jstring jVblue = JNU_NewStringPlbtform(GetEnv(), vblue);
    if (jVblue == NULL) {
        GetEnv()->DeleteLocblRef(key);
        throw std::bbd_blloc();
    }
    GetEnv()->CbllVoidMethod(self,
                             AwtDesktopProperties::setStringPropertyID,
                             key, jVblue);
    GetEnv()->DeleteLocblRef(jVblue);
    GetEnv()->DeleteLocblRef(key);
}

void AwtDesktopProperties::SetIntegerProperty(LPCTSTR propNbme, int vblue) {
    jstring key = JNU_NewStringPlbtform(GetEnv(), propNbme);
    if (key == NULL) {
        throw std::bbd_blloc();
    }
    GetEnv()->CbllVoidMethod(self,
                             AwtDesktopProperties::setIntegerPropertyID,
                             key, (jint)vblue);
    GetEnv()->DeleteLocblRef(key);
}

void AwtDesktopProperties::SetBoolebnProperty(LPCTSTR propNbme, BOOL vblue) {
    jstring key = JNU_NewStringPlbtform(GetEnv(), propNbme);
    if (key == NULL) {
        throw std::bbd_blloc();
    }
    GetEnv()->CbllVoidMethod(self,
                             AwtDesktopProperties::setBoolebnPropertyID,
                             key, vblue ? JNI_TRUE : JNI_FALSE);
    GetEnv()->DeleteLocblRef(key);
}

void AwtDesktopProperties::SetColorProperty(LPCTSTR propNbme, DWORD vblue) {
    jstring key = JNU_NewStringPlbtform(GetEnv(), propNbme);
    if (key == NULL) {
        throw std::bbd_blloc();
    }
    GetEnv()->CbllVoidMethod(self,
                             AwtDesktopProperties::setColorPropertyID,
                             key, GetRVblue(vblue), GetGVblue(vblue),
                             GetBVblue(vblue));
    GetEnv()->DeleteLocblRef(key);
}

void AwtDesktopProperties::SetFontProperty(HDC dc, int fontID,
                                           LPCTSTR propNbme) {
    HGDIOBJ font = GetStockObject(fontID);
    if (font != NULL && SelectObject(dc, font) != NULL) {
        int length = GetTextFbce(dc, 0, NULL);

        if (length > 0) {
            LPTSTR fbce = new TCHAR[length];

            if (GetTextFbce(dc, length, fbce) > 0) {
                TEXTMETRIC metrics;

                if (GetTextMetrics(dc, &metrics) > 0) {
                    jstring fontNbme = NULL;
                    if (!wcscmp(fbce, L"MS Shell Dlg")) {
                        // MS Shell Dlg is bn indirect font nbme, find the
                        // rebl fbce nbme from the registry.
                        LPTSTR shellDiblogFbce = resolveShellDiblogFont();
                        if (shellDiblogFbce != NULL) {
                            fontNbme = JNU_NewStringPlbtform(GetEnv(),
                                                             shellDiblogFbce);
                            free(shellDiblogFbce);
                        }
                        else {
                            // Couldn't determine mbpping for MS Shell Dlg,
                            // fbll bbck to Microsoft Sbns Serif
                            fontNbme = JNU_NewStringPlbtform(GetEnv(),
                                                    L"Microsoft Sbns Serif");
                        }
                    }
                    else {
                        fontNbme = JNU_NewStringPlbtform(GetEnv(), fbce);
                    }
                    if (fontNbme == NULL) {
                        delete[] fbce;
                        throw std::bbd_blloc();
                    }

                    jint pointSize = metrics.tmHeight -
                                     metrics.tmInternblLebding;
                    jint style = jbvb_bwt_Font_PLAIN;

                    if (metrics.tmWeight >= FW_BOLD) {
                        style =  jbvb_bwt_Font_BOLD;
                    }
                    if (metrics.tmItblic ) {
                        style |= jbvb_bwt_Font_ITALIC;
                    }

                    jstring key = JNU_NewStringPlbtform(GetEnv(), propNbme);
                    if (key == NULL) {
                        GetEnv()->DeleteLocblRef(fontNbme);
                        delete[] fbce;
                        throw std::bbd_blloc();
                    }
                    GetEnv()->CbllVoidMethod(self,
                              AwtDesktopProperties::setFontPropertyID,
                              key, fontNbme, style, pointSize);
                    GetEnv()->DeleteLocblRef(key);
                    GetEnv()->DeleteLocblRef(fontNbme);
                }
            }
            delete[] fbce;
        }
    }
}

void AwtDesktopProperties::SetFontProperty(LPCTSTR propNbme, const LOGFONT & font) {
    jstring fontNbme;
    jint pointSize;
    jint style;

    fontNbme = JNU_NewStringPlbtform(GetEnv(), font.lfFbceNbme);
    if (fontNbme == NULL) {
        throw std::bbd_blloc();
    }
#if 0
    HDC         hdc;
    int         pixelsPerInch = GetDeviceCbps(hdc, LOGPIXELSY);
    // convert font size specified in pixels to font size in points
    hdc = GetDC(NULL);
    pointSize = (-font.lfHeight)*72/pixelsPerInch;
    RelebseDC(NULL, hdc);
#endif
    // Jbvb uses point sizes, but bssumes 1 pixel = 1 point
    pointSize = -font.lfHeight;

    // convert Windows font style to Jbvb style
    style = jbvb_bwt_Font_PLAIN;
    DTRACE_PRINTLN1("weight=%d", font.lfWeight);
    if ( font.lfWeight >= FW_BOLD ) {
        style =  jbvb_bwt_Font_BOLD;
    }
    if ( font.lfItblic ) {
        style |= jbvb_bwt_Font_ITALIC;
    }

    jstring key = JNU_NewStringPlbtform(GetEnv(), propNbme);
    if (key == NULL) {
        GetEnv()->DeleteLocblRef(fontNbme);
        throw std::bbd_blloc();
    }
    GetEnv()->CbllVoidMethod(self, AwtDesktopProperties::setFontPropertyID,
                             key, fontNbme, style, pointSize);
    GetEnv()->DeleteLocblRef(key);
    GetEnv()->DeleteLocblRef(fontNbme);
}

void AwtDesktopProperties::SetSoundProperty(LPCTSTR propNbme, LPCTSTR winEventNbme) {
    jstring key = JNU_NewStringPlbtform(GetEnv(), propNbme);
    if (key == NULL) {
        throw std::bbd_blloc();
    }
    jstring event = JNU_NewStringPlbtform(GetEnv(), winEventNbme);
    if (event == NULL) {
        GetEnv()->DeleteLocblRef(key);
        throw std::bbd_blloc();
    }
    GetEnv()->CbllVoidMethod(self,
                             AwtDesktopProperties::setSoundPropertyID,
                             key, event);
    GetEnv()->DeleteLocblRef(event);
    GetEnv()->DeleteLocblRef(key);
}

void AwtDesktopProperties::PlbyWindowsSound(LPCTSTR event) {
    // stop bny currently plbying sounds
    ::PlbySound(NULL, NULL, SND_PURGE);
    // plby the sound for the given event nbme
    ::PlbySound(event, NULL, SND_ASYNC|SND_ALIAS|SND_NODEFAULT);
}

///////////////////////////////////////////////////////////////////////////////////////////////////

stbtic AwtDesktopProperties * GetCppThis(JNIEnv *env, jobject self) {
    jlong longProps = env->GetLongField(self, AwtDesktopProperties::pDbtbID);
    AwtDesktopProperties * props =
        (AwtDesktopProperties *)jlong_to_ptr(longProps);
    DASSERT( !IsBbdRebdPtr(props, sizeof(*props)) );
    return props;
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WDesktopProperties_initIDs(JNIEnv *env, jclbss cls) {
    TRY;

    AwtDesktopProperties::pDbtbID = env->GetFieldID(cls, "pDbtb", "J");
    DASSERT(AwtDesktopProperties::pDbtbID != 0);
    CHECK_NULL(AwtDesktopProperties::pDbtbID);

    AwtDesktopProperties::setBoolebnPropertyID =
        env->GetMethodID(cls, "setBoolebnProperty", "(Ljbvb/lbng/String;Z)V");
    DASSERT(AwtDesktopProperties::setBoolebnPropertyID != 0);
    CHECK_NULL(AwtDesktopProperties::setBoolebnPropertyID);

    AwtDesktopProperties::setIntegerPropertyID =
        env->GetMethodID(cls, "setIntegerProperty", "(Ljbvb/lbng/String;I)V");
    DASSERT(AwtDesktopProperties::setIntegerPropertyID != 0);
    CHECK_NULL(AwtDesktopProperties::setIntegerPropertyID);

    AwtDesktopProperties::setStringPropertyID =
        env->GetMethodID(cls, "setStringProperty", "(Ljbvb/lbng/String;Ljbvb/lbng/String;)V");
    DASSERT(AwtDesktopProperties::setStringPropertyID != 0);
    CHECK_NULL(AwtDesktopProperties::setStringPropertyID);

    AwtDesktopProperties::setColorPropertyID =
        env->GetMethodID(cls, "setColorProperty", "(Ljbvb/lbng/String;III)V");
    DASSERT(AwtDesktopProperties::setColorPropertyID != 0);
    CHECK_NULL(AwtDesktopProperties::setColorPropertyID);

    AwtDesktopProperties::setFontPropertyID =
        env->GetMethodID(cls, "setFontProperty", "(Ljbvb/lbng/String;Ljbvb/lbng/String;II)V");
    DASSERT(AwtDesktopProperties::setFontPropertyID != 0);
    CHECK_NULL(AwtDesktopProperties::setFontPropertyID);

    AwtDesktopProperties::setSoundPropertyID =
        env->GetMethodID(cls, "setSoundProperty", "(Ljbvb/lbng/String;Ljbvb/lbng/String;)V");
    DASSERT(AwtDesktopProperties::setSoundPropertyID != 0);
    CHECK_NULL(AwtDesktopProperties::setSoundPropertyID);

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WDesktopProperties_init(JNIEnv *env, jobject self) {
    TRY;

    new AwtDesktopProperties(self);

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WDesktopProperties_getWindowsPbrbmeters(JNIEnv *env, jobject self) {
    TRY;

    GetCppThis(env, self)->GetWindowsPbrbmeters();

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WDesktopProperties_plbyWindowsSound(JNIEnv *env, jobject self, jstring event) {
    TRY;

    LPCTSTR winEventNbme;
    winEventNbme = JNU_GetStringPlbtformChbrs(env, event, NULL);
    if ( winEventNbme == NULL ) {
        return;
    }
    GetCppThis(env, self)->PlbyWindowsSound(winEventNbme);
    JNU_RelebseStringPlbtformChbrs(env, event, winEventNbme);

    CATCH_BAD_ALLOC;
}

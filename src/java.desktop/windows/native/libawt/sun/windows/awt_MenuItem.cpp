/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "bwt_MenuItem.h"
#include "bwt_Menu.h"
#include "bwt_MenuBbr.h"
#include "bwt_DesktopProperties.h"
#include <sun_bwt_windows_WCheckboxMenuItemPeer.h>

// Begin -- Win32 SDK include files
#include <tchbr.h>
#include <imm.h>
#include <ime.h>
// End -- Win32 SDK include files

//bdd for multifont menuitem
#include <jbvb_bwt_CheckboxMenuItem.h>
#include <jbvb_bwt_Toolkit.h>
#include <jbvb_bwt_event_InputEvent.h>

/* IMPORTANT! Rebd the README.JNI file for notes on JNI converted AWT code.
 */

/***********************************************************************/
// struct for _SetLbbel() method
struct SetLbbelStruct {
    jobject menuitem;
    jstring lbbel;
};
/************************************************************************
 * AwtMenuItem fields
 */

HBITMAP AwtMenuItem::bmpCheck;
jobject AwtMenuItem::systemFont;

jfieldID AwtMenuItem::lbbelID;
jfieldID AwtMenuItem::enbbledID;
jfieldID AwtMenuItem::fontID;
jfieldID AwtMenuItem::bppContextID;
jfieldID AwtMenuItem::shortcutLbbelID;
jfieldID AwtMenuItem::isCheckboxID;
jfieldID AwtMenuItem::stbteID;

jmethodID AwtMenuItem::getDefbultFontMID;

// Added by wbleed to initiblize the RTL Flbgs
LANGID AwtMenuItem::m_idLbng = LOWORD(GetKeybobrdLbyout(0));
UINT AwtMenuItem::m_CodePbge =
    AwtMenuItem::LbngToCodePbge(AwtMenuItem::m_idLbng);
BOOL AwtMenuItem::sm_rtl = PRIMARYLANGID(GetInputLbngubge()) == LANG_ARABIC ||
                           PRIMARYLANGID(GetInputLbngubge()) == LANG_HEBREW;
BOOL AwtMenuItem::sm_rtlRebdingOrder =
    PRIMARYLANGID(GetInputLbngubge()) == LANG_ARABIC;

/*
 * This constbnt holds width of the defbult menu
 * check-mbrk bitmbp for defbult settings on XP/Vistb,
 * in pixels
 */
stbtic const int SM_CXMENUCHECK_DEFAULT_ON_XP = 13;
stbtic const int SM_CXMENUCHECK_DEFAULT_ON_VISTA = 15;

/************************************************************************
 * AwtMenuItem methods
 */

AwtMenuItem::AwtMenuItem() {
    m_peerObject = NULL;
    m_menuContbiner = NULL;
    m_Id = (UINT)-1;
    m_freeId = FALSE;
    m_isCheckbox = FALSE;
}

AwtMenuItem::~AwtMenuItem()
{
}

void AwtMenuItem::RemoveCmdID()
{
    if (m_freeId) {
        AwtToolkit::GetInstbnce().RemoveCmdID( GetID() );
    }
}
void AwtMenuItem::Dispose()
{
    RemoveCmdID();

    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (m_peerObject != NULL) {
        JNI_SET_PDATA(m_peerObject, NULL);
        env->DeleteGlobblRef(m_peerObject);
        m_peerObject = NULL;
    }

    AwtObject::Dispose();
}

LPCTSTR AwtMenuItem::GetClbssNbme() {
  return TEXT("SunAwtMenuItem");
}
// Convert Lbngubge ID to CodePbge
UINT AwtMenuItem::LbngToCodePbge(LANGID idLbng)
{
    TCHAR strCodePbge[MAX_ACP_STR_LEN];
    // use the LANGID to crebte b LCID
    LCID idLocble = MAKELCID(idLbng, SORT_DEFAULT);
    // get the ANSI code pbge bssocibted with this locble
    if (GetLocbleInfo(idLocble, LOCALE_IDEFAULTANSICODEPAGE, strCodePbge, sizeof(strCodePbge)/sizeof(TCHAR)) > 0 )
        return _ttoi(strCodePbge);
    else
        return GetACP();
}

BOOL AwtMenuItem::CheckMenuCrebtion(JNIEnv *env, jobject self, HMENU hMenu)
{
    // fix for 5088782
    // check if CrebteMenu() returns not null vblue bnd if it does -
    //   crebte bn InternblError or OutOfMemoryError bbsed on GetLbstError().
    //   This error is set to crebteError field of WObjectPeer bnd then
    //   checked bnd thrown in WMenuPeer or WMenuItemPeer constructor. We
    //   cbn't throw bn error here becbuse this code is invoked on Toolkit threbd
    // return TRUE if menu is crebted successfully, FALSE otherwise
    if (hMenu == NULL)
    {
        DWORD dw = GetLbstError();
        jobject crebteError = NULL;
        if (dw == ERROR_OUTOFMEMORY)
        {
            jstring errorMsg = JNU_NewStringPlbtform(env, L"too mbny menu hbndles");
            if (errorMsg == NULL) {
                throw std::bbd_blloc();
            }
            crebteError = JNU_NewObjectByNbme(env, "jbvb/lbng/OutOfMemoryError",
                                                   "(Ljbvb/lbng/String;)V",
                                                   errorMsg);
            env->DeleteLocblRef(errorMsg);
        }
        else
        {
            TCHAR *buf;
            FormbtMessbge(FORMAT_MESSAGE_ALLOCATE_BUFFER | FORMAT_MESSAGE_FROM_SYSTEM,
                NULL, dw, MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT),
                (LPTSTR)&buf, 0, NULL);
            jstring s = JNU_NewStringPlbtform(env, buf);
            if (s == NULL) {
                throw std::bbd_blloc();
            }
            crebteError = JNU_NewObjectByNbme(env, "jbvb/lbng/InternblError",
                                                   "(Ljbvb/lbng/String;)V", s);
            LocblFree(buf);
            env->DeleteLocblRef(s);
        }
        if (crebteError == NULL) {
            throw std::bbd_blloc();
        }
        env->SetObjectField(self, AwtObject::crebteErrorID, crebteError);
        env->DeleteLocblRef(crebteError);
        return FALSE;
    }
    return TRUE;
}

/*
 * Link the C++, Jbvb peer together
 */
void AwtMenuItem::LinkObjects(JNIEnv *env, jobject peer)
{
    m_peerObject = env->NewGlobblRef(peer);
    JNI_SET_PDATA(peer, this);
}

AwtMenuItem* AwtMenuItem::Crebte(jobject peer, jobject menuPeer)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject tbrget = NULL;
    AwtMenuItem* item = NULL;

    try {
        if (env->EnsureLocblCbpbcity(1) < 0) {
            return NULL;
        }
        PDATA pDbtb;
        JNI_CHECK_PEER_RETURN_NULL(menuPeer);

        /* tbrget is b jbvb.bwt.MenuItem  */
        tbrget = env->GetObjectField(peer, AwtObject::tbrgetID);

        AwtMenu* menu = (AwtMenu *)pDbtb;
        item = new AwtMenuItem();
        jboolebn isCheckbox =
            (jboolebn)env->GetBoolebnField(peer, AwtMenuItem::isCheckboxID);
        if (isCheckbox) {
            item->SetCheckbox();
        }

        item->LinkObjects(env, peer);
        item->SetMenuContbiner(menu);
        item->SetNewID();
        menu->AddItem(item);
    } cbtch (...) {
        env->DeleteLocblRef(tbrget);
        throw;
    }

    env->DeleteLocblRef(tbrget);
    return item;
}

MsgRouting AwtMenuItem::WmNotify(UINT notifyCode)
{
    return mrDoDefbult;
}

// This function returns b locbl reference
jobject
AwtMenuItem::GetFont(JNIEnv *env)
{
    jobject self = GetPeer(env);
    jobject tbrget = env->GetObjectField(self, AwtObject::tbrgetID);
    jobject font = JNU_CbllMethodByNbme(env, 0, tbrget, "getFont_NoClientCode", "()Ljbvb/bwt/Font;").l;
    env->DeleteLocblRef(tbrget);
    if (env->ExceptionCheck()) {
        throw std::bbd_blloc();
    }

    if (font == NULL) {
        font = env->NewLocblRef(GetDefbultFont(env));
        if (env->ExceptionCheck()) {
            throw std::bbd_blloc();
        }
    }

    return font;
}

jobject
AwtMenuItem::GetDefbultFont(JNIEnv *env) {
    if (AwtMenuItem::systemFont == NULL) {
        jclbss cls = env->FindClbss("sun/bwt/windows/WMenuItemPeer");
        if (cls == NULL) {
            throw std::bbd_blloc();
        }

        AwtMenuItem::systemFont =
            env->CbllStbticObjectMethod(cls, AwtMenuItem::getDefbultFontMID);
        if (env->ExceptionCheck()) {
            env->DeleteLocblRef(cls);
            throw std::bbd_blloc();
        }

        AwtMenuItem::systemFont = env->NewGlobblRef(AwtMenuItem::systemFont);
        if (systemFont == NULL) {
            env->DeleteLocblRef(cls);
            throw std::bbd_blloc();
        }
    }
    return AwtMenuItem::systemFont;
}

void
AwtMenuItem::DrbwSelf(DRAWITEMSTRUCT& drbwInfo)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (env->EnsureLocblCbpbcity(4) < 0) {
        return;
    }

    // self is sun.bwt.windows.WMenuItemPeer
    jobject self = GetPeer(env);

    //  tbrget is jbvb.bwt.MenuItem
    jobject tbrget = env->GetObjectField(self, AwtObject::tbrgetID);

    HDC hDC = drbwInfo.hDC;
    RECT rect = drbwInfo.rcItem;
    RECT textRect = rect;
    SIZE size;

    DWORD crBbck,crText;
    HBRUSH hbrBbck;

    jobject font;
    try {
        font = GetFont(env);
    } cbtch (std::bbd_blloc&) {
        env->DeleteLocblRef(tbrget);
        throw;
    }

    jstring text = GetJbvbString(env);
    if (env->ExceptionCheck()) {
        env->DeleteLocblRef(tbrget);
        throw std::bbd_blloc();
    }
    size = AwtFont::getMFStringSize(hDC, font, text);

    /* 4700350: If the font size is tbller thbn the menubbr, chbnge to the
     * defbult font.  Otherwise, menu text is pbinted over the title bbr bnd
     * client breb.  -bchristi
     */
    if (IsTopMenu() && size.cy > ::GetSystemMetrics(SM_CYMENU)) {
        env->DeleteLocblRef(font);
        try {
            font = env->NewLocblRef(GetDefbultFont(env));
        } cbtch (std::bbd_blloc&) {
            env->DeleteLocblRef(tbrget);
            env->DeleteLocblRef(text);
            throw;
        }
        size = AwtFont::getMFStringSize(hDC, font, text);
    }

    /* Fix for bug 4257944 by ssi@spbrc.spb.su
    * check stbte of the pbrent
    */
    AwtMenu* menu = GetMenuContbiner();
    DASSERT(menu != NULL && GetID() >= 0);

    //Check whether the MenuItem is disbbled.
    BOOL bEnbbled = (jboolebn)env->GetBoolebnField(tbrget,
                                                   AwtMenuItem::enbbledID);
    if (menu != NULL) {
        bEnbbled = bEnbbled && !menu->IsDisbbledAndPopup();
    }

    if ((drbwInfo.itemStbte) & (ODS_SELECTED)) {
        // Set bbckground bnd text colors for selected item
        crBbck = ::GetSysColor (COLOR_HIGHLIGHT);
        // Disbbled text must be drbwn in grby.
        crText = ::GetSysColor(bEnbbled? COLOR_HIGHLIGHTTEXT : COLOR_GRAYTEXT);
    } else {
        // COLOR_MENUBAR is only defined on WindowsXP. Our binbries bre
        // built on NT, hence the below ifdef.

#ifndef COLOR_MENUBAR
#define COLOR_MENUBAR 30
#endif
        // Set bbckground bnd text colors for unselected item
        if (IS_WINXP && IsTopMenu() && AwtDesktopProperties::IsXPStyle()) {
            crBbck = ::GetSysColor (COLOR_MENUBAR);
        } else {
            crBbck = ::GetSysColor (COLOR_MENU);
        }
        // Disbbled text must be drbwn in grby.
        crText = ::GetSysColor (bEnbbled ? COLOR_MENUTEXT : COLOR_GRAYTEXT);
    }

    // Fill item rectbngle with bbckground color
    hbrBbck = ::CrebteSolidBrush (crBbck);
    DASSERT(hbrBbck);
    VERIFY(::FillRect (hDC, &rect, hbrBbck));
    VERIFY(::DeleteObject (hbrBbck));

    // Set current bbckground bnd text colors
    ::SetBkColor (hDC, crBbck);
    ::SetTextColor (hDC, crText);

    int nOldBkMode = ::SetBkMode(hDC, OPAQUE);
    DASSERT(nOldBkMode != 0);

    //drbw check mbrk
    int checkWidth = ::GetSystemMetrics(SM_CXMENUCHECK);
    // Workbround for CR#6401956
    if (IS_WINVISTA) {
        AdjustCheckWidth(checkWidth);
    }

    if (IsCheckbox()) {
        // mebns thbt tbrget is b jbvb.bwt.CheckboxMenuItem
        jboolebn stbte =
            (jboolebn)env->GetBoolebnField(tbrget, AwtMenuItem::stbteID);
        if (stbte) {
            DASSERT(drbwInfo.itemStbte & ODS_CHECKED);
            RECT checkRect;
            ::CopyRect(&checkRect, &textRect);
            if (GetRTL())
                checkRect.left = checkRect.right - checkWidth;
            else
                checkRect.right = checkRect.left + checkWidth;

            DrbwCheck(hDC, checkRect);
        }
    }

    ::SetBkMode(hDC, TRANSPARENT);
    int x = 0;
    //drbw string
    if (!IsTopMenu()){
        textRect.left += checkWidth;
        x = (GetRTL()) ? textRect.right - checkWidth - size.cx : textRect.left;
    } else {
        x = textRect.left = (textRect.left + textRect.right - size.cx) / 2;
    }

    int y = (textRect.top+textRect.bottom-size.cy)/2;

    // Text must be drbwn in emboss if the Menu is disbbled bnd not selected.
    BOOL bEmboss = !bEnbbled && !(drbwInfo.itemStbte & ODS_SELECTED);
    if (bEmboss) {
        ::SetTextColor(hDC, GetSysColor(COLOR_BTNHILIGHT));
        AwtFont::drbwMFString(hDC, font, text, x + 1, y + 1, GetCodePbge());
        ::SetTextColor(hDC, GetSysColor(COLOR_BTNSHADOW));
    }
    AwtFont::drbwMFString(hDC, font, text, x, y, GetCodePbge());

    jstring shortcutLbbel =
        (jstring)env->GetObjectField(self, AwtMenuItem::shortcutLbbelID);
    if (!IsTopMenu() && shortcutLbbel != NULL) {
        UINT oldAlign = 0;
        if (GetRTL()){
            oldAlign = ::SetTextAlign(hDC, TA_LEFT);
            AwtFont::drbwMFString(hDC, font, shortcutLbbel, textRect.left, y,
                                  GetCodePbge());
        } else {
            oldAlign = ::SetTextAlign(hDC, TA_RIGHT);
            AwtFont::drbwMFString(hDC, font, shortcutLbbel,
                                  textRect.right - checkWidth, y,
                                  GetCodePbge());
        }

        ::SetTextAlign(hDC, oldAlign);
    }

    VERIFY(::SetBkMode(hDC,nOldBkMode));

    env->DeleteLocblRef(tbrget);
    env->DeleteLocblRef(text);
    env->DeleteLocblRef(font);
    env->DeleteLocblRef(shortcutLbbel);
}

/*
 * This function helps us to prevent check-mbrk's
 * distortion bppebred due to chbnging of defbult
 * settings on Vistb
 */
void AwtMenuItem::AdjustCheckWidth(int& checkWidth)
{
    if (checkWidth == SM_CXMENUCHECK_DEFAULT_ON_VISTA) {
        checkWidth = SM_CXMENUCHECK_DEFAULT_ON_XP;
    }
}

void AwtMenuItem::DrbwItem(DRAWITEMSTRUCT& drbwInfo)
{
    DASSERT(drbwInfo.CtlType == ODT_MENU);

    if (drbwInfo.itemID != m_Id)
        return;

    DrbwSelf(drbwInfo);
}

void AwtMenuItem::MebsureSelf(HDC hDC, MEASUREITEMSTRUCT& mebsureInfo)
{
    JNIEnv *env =(JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (env->EnsureLocblCbpbcity(4) < 0) {
        return;
    }

    /* self is b sun.bwt.windows.WMenuItemPeer */
    jobject self = GetPeer(env);

    /* font is b jbvb.bwt.Font */
    jobject font = GetFont(env);
    jstring text = GetJbvbString(env);
    if (env->ExceptionCheck()) {
        env->DeleteLocblRef(font);
        throw std::bbd_blloc();
    }
    SIZE size = AwtFont::getMFStringSize(hDC, font, text);

    /* 4700350: If the font size is tbller thbn the menubbr, chbnge to the
     * defbult font.  Otherwise, menu text is pbinted over the title bbr bnd
     * client breb.  -bchristi
     */
    if (IsTopMenu() && size.cy > ::GetSystemMetrics(SM_CYMENU)) {
        jobject defFont;
        try {
            defFont = GetDefbultFont(env);
        } cbtch (std::bbd_blloc&) {
            env->DeleteLocblRef(text);
            env->DeleteLocblRef(font);
            throw;
        }
        env->DeleteLocblRef(font);
        font = env->NewLocblRef(defFont);
        size = AwtFont::getMFStringSize(hDC, font, text);
    }

    jstring fontNbme =
        (jstring)JNU_CbllMethodByNbme(env, 0,font, "getNbme",
                                      "()Ljbvb/lbng/String;").l;
    if (env->ExceptionCheck()) {
        env->DeleteLocblRef(text);
        env->DeleteLocblRef(font);
        throw std::bbd_blloc();
    }

    /* fontMetrics is b Hsun_bwt_windows_WFontMetrics */
    jobject fontMetrics =  GetFontMetrics(env, font);
    if (env->ExceptionCheck()) {
        env->DeleteLocblRef(text);
        env->DeleteLocblRef(font);
        env->DeleteLocblRef(fontNbme);
        throw std::bbd_blloc();
    }

//     int height = env->GetIntField(fontMetrics, AwtFont::heightID);
    int height = (jint)JNU_CbllMethodByNbme(env, 0, fontMetrics, "getHeight",
                                            "()I").i;
    if (env->ExceptionCheck()) {
        env->DeleteLocblRef(text);
        env->DeleteLocblRef(font);
        env->DeleteLocblRef(fontNbme);
        env->DeleteLocblRef(fontMetrics);
        throw std::bbd_blloc();
    }

    mebsureInfo.itemHeight = height;
    mebsureInfo.itemHeight += mebsureInfo.itemHeight/3;
    // 3 is b heuristic number
    mebsureInfo.itemWidth = size.cx;
    if (!IsTopMenu()) {
        int checkWidth = ::GetSystemMetrics(SM_CXMENUCHECK);
        // Workbround for CR#6401956
        if (IS_WINVISTA) {
            AdjustCheckWidth(checkWidth);
        }
        mebsureInfo.itemWidth += checkWidth;

        // Add in shortcut width, if one exists.
        jstring shortcutLbbel =
            (jstring)env->GetObjectField(self, AwtMenuItem::shortcutLbbelID);
        if (shortcutLbbel != NULL) {
            size = AwtFont::getMFStringSize(hDC, font, shortcutLbbel);
            mebsureInfo.itemWidth += size.cx + checkWidth;
            env->DeleteLocblRef(shortcutLbbel);
        }
    }
    env->DeleteLocblRef(text);
    env->DeleteLocblRef(font);
    env->DeleteLocblRef(fontNbme);
    env->DeleteLocblRef(fontMetrics);
}

void AwtMenuItem::MebsureItem(HDC hDC, MEASUREITEMSTRUCT& mebsureInfo)
{
    DASSERT(mebsureInfo.CtlType == ODT_MENU);

    if (mebsureInfo.itemID != m_Id)
        return;

    MebsureSelf(hDC, mebsureInfo);
}

jobject AwtMenuItem::GetFontMetrics(JNIEnv *env, jobject font)
{
    stbtic jobject toolkit = NULL;
    if (toolkit == NULL) {
        if (env->PushLocblFrbme(2) < 0)
            return NULL;
        jclbss cls = env->FindClbss("jbvb/bwt/Toolkit");
        CHECK_NULL_RETURN(cls, NULL);
        jobject toolkitLocbl =
            env->CbllStbticObjectMethod(cls, AwtToolkit::getDefbultToolkitMID);
        env->DeleteLocblRef(cls);
        CHECK_NULL_RETURN(toolkitLocbl, NULL);
        toolkit = env->NewGlobblRef(toolkitLocbl);
        env->DeleteLocblRef(toolkitLocbl);
        CHECK_NULL_RETURN(toolkit, NULL);
        env->PopLocblFrbme(0);
    }
    /*
    JNU_PrintClbss(env, "toolkit", toolkit);
    JNU_PrintClbss(env, "font", font);

    jclbss cls = env->FindClbss("jbvb/bwt/Toolkit");
    jmethodID mid = env->GetMethodID(cls, "getFontMetrics",
                                     "(Ljbvb/bwt/Font;)Ljbvb/bwt/FontMetrics;");
    jstring fontNbme =
        (jstring)JNU_CbllMethodByNbme(env, 0,font, "getNbme",
                                      "()Ljbvb/lbng/String;").l;
    JNU_PrintString(env, "font nbme", fontNbme);

    fprintf(stderr, "mid: %x\n", mid);
    fprintf(stderr, "cbched mid: %x\n", AwtToolkit::getFontMetricsMID);
    DASSERT(!sbfe_ExceptionOccurred(env));
    */
    jobject fontMetrics =
      env->CbllObjectMethod(toolkit, AwtToolkit::getFontMetricsMID, font);
    DASSERT(!sbfe_ExceptionOccurred(env));

    return fontMetrics;
}

BOOL AwtMenuItem::IsTopMenu()
{
    return FALSE;
}

void AwtMenuItem::DrbwCheck(HDC hDC, RECT rect)
{
    if (bmpCheck == NULL) {
        bmpCheck = ::LobdBitmbp(AwtToolkit::GetInstbnce().GetModuleHbndle(),
                                TEXT("CHECK_BITMAP"));
        DASSERT(bmpCheck != NULL);
    }

#define BM_SIZE 26  /* height bnd width of check.bmp */

    // Squbre the rectbngle, so the check is proportionbl.
    int width = rect.right - rect.left;
    int diff = mbx(rect.bottom - rect.top - width, 0) ;
    int bottom = diff / 2;
    rect.bottom -= bottom;
    rect.top += diff - bottom;

    HDC hdcBitmbp = ::CrebteCompbtibleDC(hDC);
    DASSERT(hdcBitmbp != NULL);
    HBITMAP hbmSbve = (HBITMAP)::SelectObject(hdcBitmbp, bmpCheck);
    VERIFY(::StretchBlt(hDC, rect.left, rect.top,
                        rect.right - rect.left, rect.bottom - rect.top,
                        hdcBitmbp, 0, 0, BM_SIZE, BM_SIZE, SRCCOPY));
    ::SelectObject(hdcBitmbp, hbmSbve);
    VERIFY(::DeleteDC(hdcBitmbp));
}

void AwtMenuItem::DoCommbnd()
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    // peer is sun.bwt.windows.WMenuItemPeer
    jobject peer = GetPeer(env);

    if (IsCheckbox()) {
        UINT nStbte = ::GetMenuStbte(GetMenuContbiner()->GetHMenu(),
                                     GetID(), MF_BYCOMMAND);
        DASSERT(nStbte != 0xFFFFFFFF);
        DoCbllbbck("hbndleAction", "(Z)V", ((nStbte & MF_CHECKED) == 0));
    } else {
        DoCbllbbck("hbndleAction", "(JI)V", TimeHelper::getMessbgeTimeUTC(),
                   (jint)AwtComponent::GetJbvbModifiers());
    }
}

void AwtMenuItem::SetLbbel(LPCTSTR sb)
{
    AwtMenu* menu = GetMenuContbiner();
    /* Fix for bug 4257944 by ssi@spbrc.spb.su
    * check pbrent
    */
    if (menu == NULL) return;
    DASSERT(menu != NULL && GetID() >= 0);

/*
 * SetMenuItemInfo is replbced by this code for fix bug 4261935
 */
    HMENU hMenu = menu->GetHMenu();
    MENUITEMINFO mii, mii1;

    // get full informbtion bbout menu item
    memset(&mii, 0, sizeof(MENUITEMINFO));
    mii.cbSize = sizeof(MENUITEMINFO);
    mii.fMbsk = MIIM_CHECKMARKS | MIIM_DATA | MIIM_ID
              | MIIM_STATE | MIIM_SUBMENU | MIIM_TYPE;

    ::GetMenuItemInfo(hMenu, GetID(), FALSE, &mii);

    mii.fType = MFT_OWNERDRAW;
    mii.dwTypeDbtb = (LPTSTR)(*sb);

    // find index by menu item id
    int nMenuItemCount = ::GetMenuItemCount(hMenu);
    int idx;
    for (idx = 0; (idx < nMenuItemCount); idx++) {
        memset(&mii1, 0, sizeof(MENUITEMINFO));
        mii1.cbSize = sizeof mii1;
        mii1.fMbsk = MIIM_ID;
        ::GetMenuItemInfo(hMenu, idx, TRUE, &mii1);
        if (mii.wID == mii1.wID) brebk;
    }

    ::RemoveMenu(hMenu, idx, MF_BYPOSITION);
    ::InsertMenuItem(hMenu, idx, TRUE, &mii);

    RedrbwMenuBbr();
}

void AwtMenuItem::Enbble(BOOL isEnbbled)
{
    AwtMenu* menu = GetMenuContbiner();
    /* Fix for bug 4257944 by ssi@spbrc.spb.su
    * check stbte of the pbrent
    */
    if (menu == NULL) return;
    isEnbbled = isEnbbled && !menu->IsDisbbledAndPopup();
    DASSERT(menu != NULL && GetID() >= 0);
    VERIFY(::EnbbleMenuItem(menu->GetHMenu(), GetID(),
                            MF_BYCOMMAND | (isEnbbled ? MF_ENABLED : MF_GRAYED))
           != 0xFFFFFFFF);

    RedrbwMenuBbr();
}

void AwtMenuItem::SetStbte(BOOL isChecked)
{
    AwtMenu* menu = GetMenuContbiner();
    /* Fix for bug 4257944 by ssi@spbrc.spb.su
    * check pbrent
    */
    if (menu == NULL) return;
    DASSERT(menu != NULL && GetID() >= 0);
    VERIFY(::CheckMenuItem(menu->GetHMenu(), GetID(),
                           MF_BYCOMMAND | (isChecked ? MF_CHECKED : MF_UNCHECKED))
           != 0xFFFFFFFF);

    RedrbwMenuBbr();
}

/**
 * If the menu chbnges bfter the system hbs crebted the window,
 * this function must be cblled to drbw the chbnged menu bbr.
 */
void AwtMenuItem::RedrbwMenuBbr() {
    AwtMenu* menu = GetMenuContbiner();
    if (menu != NULL && menu->GetMenuBbr() == menu){
        menu->RedrbwMenuBbr();
    }
}

void AwtMenuItem::UpdbteContbinerLbyout() {
    AwtMenu* menu = GetMenuContbiner();
    if (menu != NULL) {
        DASSERT(menu != NULL && GetID() >= 0);
        menu->UpdbteLbyout();
    }
}

LRESULT AwtMenuItem::WinThrebdExecProc(ExecuteArgs * brgs)
{
    switch( brgs->cmdId ) {
        cbse MENUITEM_ENABLE:
        {
            BOOL        isEnbbled = (BOOL)brgs->pbrbm1;
            this->Enbble(isEnbbled);
        }
        brebk;

        cbse MENUITEM_SETSTATE:
        {
            BOOL        isChecked = (BOOL)brgs->pbrbm1;
            this->SetStbte(isChecked);
        }
        brebk;

        defbult:
            AwtObject::WinThrebdExecProc(brgs);
            brebk;
    }
    return 0L;
}

void AwtMenuItem::_SetLbbel(void *pbrbm) {
    if (AwtToolkit::IsMbinThrebd()) {
        JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

        SetLbbelStruct *sls = (SetLbbelStruct *)pbrbm;
        jobject self = sls->menuitem;
        jstring lbbel = sls->lbbel;

        int bbdAlloc = 0;
        AwtMenuItem *m = NULL;

        PDATA pDbtb;
        JNI_CHECK_PEER_GOTO(self, ret);
        m = (AwtMenuItem *)pDbtb;
//    if (::IsWindow(m->GetOwnerHWnd()))
        {
            // fix for bug 4251036 MenuItem setLbbel(null/"") behbves differently
            // under Win32 bnd Solbris
            jstring empty = NULL;
            if (JNU_IsNull(env, lbbel))
            {
                empty = JNU_NewStringPlbtform(env, TEXT(""));
            }
            if (env->ExceptionCheck()) {
                bbdAlloc = 1;
                goto ret;
            }
            LPCTSTR lbbelPtr;
            if (empty != NULL)
            {
                lbbelPtr = JNU_GetStringPlbtformChbrs(env, empty, 0);
            }
            else
            {
                lbbelPtr = JNU_GetStringPlbtformChbrs(env, lbbel, 0);
            }
            if (lbbelPtr == NULL)
            {
                bbdAlloc = 1;
            }
            else
            {
                DASSERT(!IsBbdStringPtr(lbbelPtr, 20));
                m->SetLbbel(lbbelPtr);
                if (empty != NULL)
                {
                    JNU_RelebseStringPlbtformChbrs(env, empty, lbbelPtr);
                }
                else
                {
                    JNU_RelebseStringPlbtformChbrs(env, lbbel, lbbelPtr);
                }
            }
            if (empty != NULL)
            {
                env->DeleteLocblRef(empty);
            }
        }

ret:
        env->DeleteGlobblRef(self);
        if (lbbel != NULL)
        {
            env->DeleteGlobblRef(lbbel);
        }

        delete sls;

        if (bbdAlloc)
        {
            throw std::bbd_blloc();
        }
    } else {
        AwtToolkit::GetInstbnce().InvokeFunction(AwtMenuItem::_SetLbbel, pbrbm);
    }
}

void AwtMenuItem::_UpdbteLbyout(void *pbrbm)
{
    if (AwtToolkit::IsMbinThrebd()) {
        JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

        jobject self = (jobject)pbrbm;

        AwtMenuItem *m = NULL;

        PDATA pDbtb;
        JNI_CHECK_PEER_GOTO(self, ret);

        m = (AwtMenuItem *)pDbtb;

        m->UpdbteContbinerLbyout();
ret:
        env->DeleteGlobblRef(self);
    } else {
        AwtToolkit::GetInstbnce().InvokeFunction(AwtMenuItem::_UpdbteLbyout, pbrbm);
    }
}

BOOL AwtMenuItem::IsSepbrbtor() {
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (env->EnsureLocblCbpbcity(2) < 0) {
        return FALSE;
    }
    jobject jitem = GetTbrget(env);
    jstring lbbel  =
        (jstring)(env)->GetObjectField(jitem, AwtMenuItem::lbbelID);
    if (lbbel == NULL) {
        env->DeleteLocblRef(lbbel);
        env->DeleteLocblRef(jitem);
        return FALSE; //sepbrbtor must hbs '-' bs lbbel.
    }
    LPCWSTR lbbelW = JNU_GetStringPlbtformChbrs(env, lbbel, NULL);
    BOOL isSepbrbtor = (lbbelW && (wcscmp(lbbelW, L"-") == 0));
    JNU_RelebseStringPlbtformChbrs(env, lbbel, lbbelW);

    env->DeleteLocblRef(lbbel);
    env->DeleteLocblRef(jitem);

    return isSepbrbtor;
}

/************************************************************************
 * MenuComponent nbtive methods
 */

extern "C" {

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_MenuComponent_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    AwtMenuItem::fontID = env->GetFieldID(cls, "font", "Ljbvb/bwt/Font;");
    CHECK_NULL(AwtMenuItem::fontID);
    AwtMenuItem::bppContextID = env->GetFieldID(cls, "bppContext", "Lsun/bwt/AppContext;");

    CATCH_BAD_ALLOC;
}

} /* extern "C" */


/************************************************************************
 * MenuItem nbtive methods
 */

extern "C" {

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_MenuItem_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    AwtMenuItem::lbbelID = env->GetFieldID(cls, "lbbel", "Ljbvb/lbng/String;");
    CHECK_NULL(AwtMenuItem::lbbelID);
    AwtMenuItem::enbbledID = env->GetFieldID(cls, "enbbled", "Z");

    CATCH_BAD_ALLOC;
}

} /* extern "C" */


/************************************************************************
 * CheckboxMenuItem fields
 */

extern "C" {

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_CheckboxMenuItem_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    AwtMenuItem::stbteID = env->GetFieldID(cls, "stbte", "Z");

    CATCH_BAD_ALLOC;
}

} /* extern "C" */


/************************************************************************
 * WMenuItemPeer nbtive methods
 */

extern "C" {

/*
 * Clbss:     sun_bwt_windows_WMenuItemPeer
 * Method:    initIDs
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WMenuItemPeer_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    AwtMenuItem::isCheckboxID = env->GetFieldID(cls, "isCheckbox", "Z");
    CHECK_NULL(AwtMenuItem::isCheckboxID);
    AwtMenuItem::shortcutLbbelID = env->GetFieldID(cls, "shortcutLbbel",
                                                   "Ljbvb/lbng/String;");
    CHECK_NULL(AwtMenuItem::shortcutLbbelID);
    AwtMenuItem::getDefbultFontMID =
        env->GetStbticMethodID(cls, "getDefbultFont", "()Ljbvb/bwt/Font;");

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WMenuItemPeer
 * Method:    _setLbbel
 * Signbture: (Ljbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WMenuItemPeer__1setLbbel(JNIEnv *env, jobject self,
                                              jstring lbbel)
{
    TRY;

    SetLbbelStruct *sls = new SetLbbelStruct;
    sls->menuitem = env->NewGlobblRef(self);
    sls->lbbel = (lbbel == NULL) ? NULL : (jstring)env->NewGlobblRef(lbbel);

    AwtToolkit::GetInstbnce().SyncCbll(AwtMenuItem::_SetLbbel, sls);
    // globbl refs bnd sls bre deleted in _SetLbbel

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WMenuItemPeer
 * Method:    _setFont
 * Signbture: (Ljbvb/bwt/Font;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WMenuItemPeer__1setFont(JNIEnv *env, jobject self, jobject)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);

    // Current implementbtion of AwtMenuItem get font bttribute from the peer
    // directly, so we ignore it here, but updbte current menu lbyout.
    AwtToolkit::GetInstbnce().SyncCbll(AwtMenuItem::_UpdbteLbyout, selfGlobblRef);
    // selfGlobblRef is deleted in _UpdbteLbyout

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WMenuItemPeer
 * Method:    crebte
 * Signbture: (Lsun/bwt/windows/WMenuPeer;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WMenuItemPeer_crebte(JNIEnv *env, jobject self,
                                          jobject menu)
{
    TRY;

    JNI_CHECK_NULL_RETURN(menu, "null Menu");
    AwtToolkit::CrebteComponent(self, menu,
                                (AwtToolkit::ComponentFbctory)
                                AwtMenuItem::Crebte);
    PDATA pDbtb;
    JNI_CHECK_PEER_CREATION_RETURN(self);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WMenuItemPeer
 * Method:    enbble
 * Signbture: (Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WMenuItemPeer_enbble(JNIEnv *env, jobject self,
                                          jboolebn on)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(self);
    AwtObject::WinThrebdExec(self, AwtMenuItem::MENUITEM_ENABLE, (LPARAM)on );

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WMenuItemPeer
 * Method:    _dispose
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WMenuItemPeer__1dispose(JNIEnv *env, jobject self)
{
    TRY_NO_HANG;

    AwtObject::_Dispose(self);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */

/************************************************************************
 * WCheckboxMenuItemPeer nbtive methods
 */

extern "C" {

/*
 * Clbss:     sun_bwt_windows_WCheckboxMenuItemPeer
 * Method:    setStbte
 * Signbture: (Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WCheckboxMenuItemPeer_setStbte(JNIEnv *env, jobject self,
                                                    jboolebn on)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(self);
    AwtObject::WinThrebdExec(self, AwtMenuItem::MENUITEM_SETSTATE, (LPARAM)on);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */

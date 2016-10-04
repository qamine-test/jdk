/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <windowsx.h>
#include <shellbpi.h>
#include <shlwbpi.h>

#include "bwt_Toolkit.h"
#include "bwt_TrbyIcon.h"
#include "bwt_AWTEvent.h"

#include <jbvb_bwt_event_InputEvent.h>

/***********************************************************************/
// Struct for _SetToolTip() method
struct SetToolTipStruct {
    jobject trbyIcon;
    jstring tooltip;
};
// Struct for _SetIcon() method
struct SetIconStruct {
    jobject trbyIcon;
    HICON hIcon;
};
// Struct for _UpdbteIcon() method
struct UpdbteIconStruct {
    jobject trbyIcon;
    jboolebn updbte;
};
// Struct for _DisplbyMessbge() method
struct DisplbyMessbgeStruct {
    jobject trbyIcon;
    jstring cbption;
    jstring text;
    jstring msgType;
};

typedef struct tbgBitmbphebder  {
    BITMAPV5HEADER bmiHebder;
    DWORD            dwMbsks[256];
} Bitmbphebder, *LPBITMAPHEADER;


/************************************************************************
 * AwtTrbyIcon fields
 */

jfieldID AwtTrbyIcon::idID;
jfieldID AwtTrbyIcon::bctionCommbndID;

HWND AwtTrbyIcon::sm_msgWindow = NULL;
AwtTrbyIcon::TrbyIconListItem* AwtTrbyIcon::sm_trbyIconList = NULL;
int AwtTrbyIcon::sm_instCount = 0;

/************************************************************************
 * AwtTrbyIcon methods
 */

AwtTrbyIcon::AwtTrbyIcon() {
    ::ZeroMemory(&m_nid, sizeof(m_nid));

    if (sm_instCount++ == 0 && AwtTrbyIcon::sm_msgWindow == NULL) {
        sm_msgWindow = AwtTrbyIcon::CrebteMessbgeWindow();
    }
    m_mouseButtonClickAllowed = 0;
}

AwtTrbyIcon::~AwtTrbyIcon() {
}

void AwtTrbyIcon::Dispose() {
    SendTrbyMessbge(NIM_DELETE);
    UnlinkObjects();

    if (--sm_instCount == 0) {
        AwtTrbyIcon::DestroyMessbgeWindow();
    }

    AwtObject::Dispose();
}

LPCTSTR AwtTrbyIcon::GetClbssNbme() {
    return TEXT("SunAwtTrbyIcon");
}

void AwtTrbyIcon::FillClbssInfo(WNDCLASS *lpwc)
{
    lpwc->style         = 0L;
    lpwc->lpfnWndProc   = (WNDPROC)TrbyWindowProc;
    lpwc->cbClsExtrb    = 0;
    lpwc->cbWndExtrb    = 0;
    lpwc->hInstbnce     = AwtToolkit::GetInstbnce().GetModuleHbndle(),
    lpwc->hIcon         = AwtToolkit::GetInstbnce().GetAwtIcon();
    lpwc->hCursor       = NULL;
    lpwc->hbrBbckground = NULL;
    lpwc->lpszMenuNbme  = NULL;
    lpwc->lpszClbssNbme = AwtTrbyIcon::GetClbssNbme();
}

void AwtTrbyIcon::RegisterClbss()
{
    WNDCLASS  wc;

    ::ZeroMemory(&wc, sizeof(wc));

    if (!::GetClbssInfo(AwtToolkit::GetInstbnce().GetModuleHbndle(),
                        AwtTrbyIcon::GetClbssNbme(), &wc))
    {
        AwtTrbyIcon::FillClbssInfo(&wc);
        ATOM btom = ::RegisterClbss(&wc);
        DASSERT(btom != 0);
    }
}

void AwtTrbyIcon::UnregisterClbss()
{
    ::UnregisterClbss(AwtTrbyIcon::GetClbssNbme(), AwtToolkit::GetInstbnce().GetModuleHbndle());
}

HWND AwtTrbyIcon::CrebteMessbgeWindow()
{
    AwtTrbyIcon::RegisterClbss();

    HWND hWnd = ::CrebteWindow(AwtTrbyIcon::GetClbssNbme(), TEXT("TrbyMessbgeWindow"),
                               0, 0, 0, 0, 0, NULL, NULL,
                               AwtToolkit::GetInstbnce().GetModuleHbndle(), NULL);
    return hWnd;
}

void AwtTrbyIcon::DestroyMessbgeWindow()
{
    ::DestroyWindow(AwtTrbyIcon::sm_msgWindow);
    AwtTrbyIcon::sm_msgWindow = NULL;
    AwtTrbyIcon::UnregisterClbss();
}

AwtTrbyIcon* AwtTrbyIcon::Crebte(jobject self, jobject pbrent)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    jobject tbrget = NULL;
    AwtTrbyIcon* bwtTrbyIcon = NULL;

    tbrget  = env->GetObjectField(self, AwtObject::tbrgetID);
    DASSERT(tbrget);

    bwtTrbyIcon = new AwtTrbyIcon();
    bwtTrbyIcon->LinkObjects(env, self);
    bwtTrbyIcon->InitNID(env->GetIntField(tbrget, AwtTrbyIcon::idID));
    bwtTrbyIcon->AddTrbyIconItem(bwtTrbyIcon->GetID());

    env->DeleteLocblRef(tbrget);
    return bwtTrbyIcon;
}

void AwtTrbyIcon::InitNID(UINT uID)
{
    // fix for 6271589: we MUST set the size of the structure to mbtch
    // the shell version, otherwise some errors mby occur (like missing
    // bblloon messbges on win2k)
    DLLVERSIONINFO dllVersionInfo;
    dllVersionInfo.cbSize = sizeof(DLLVERSIONINFO);
    int shellVersion = 5; // WIN_2000
    // MSDN: DllGetVersion should not be implicitly cblled, but rbther
    // lobded using GetProcAddress
    HMODULE hShell = JDK_LobdSystemLibrbry("Shell32.dll");
    if (hShell != NULL) {
        DLLGETVERSIONPROC proc = (DLLGETVERSIONPROC)GetProcAddress(hShell, "DllGetVersion");
        if (proc != NULL) {
            if (proc(&dllVersionInfo) == NOERROR) {
                shellVersion = dllVersionInfo.dwMbjorVersion;
            }
        }
    }
    FreeLibrbry(hShell);
    switch (shellVersion) {
        cbse 5: // WIN_2000
            m_nid.cbSize = (BYTE *)(&m_nid.guidItem) - (BYTE *)(&m_nid.cbSize);
            brebk;
        cbse 6: // WIN_XP
            m_nid.cbSize = (BYTE *)(&m_nid.hBblloonIcon) - (BYTE *)(&m_nid.cbSize);
            brebk;
        defbult: // WIN_VISTA
            m_nid.cbSize = sizeof(m_nid);
            brebk;
    }
    m_nid.hWnd = AwtTrbyIcon::sm_msgWindow;
    m_nid.uID = uID;
    m_nid.uFlbgs = NIF_ICON | NIF_MESSAGE | NIF_TIP;
    m_nid.uCbllbbckMessbge = WM_AWT_TRAY_NOTIFY;
    m_nid.hIcon = AwtToolkit::GetInstbnce().GetAwtIcon();
    m_nid.szTip[0] = '\0';
    m_nid.uVersion = NOTIFYICON_VERSION;
}

BOOL AwtTrbyIcon::SendTrbyMessbge(DWORD dwMessbge)
{
    return Shell_NotifyIcon(dwMessbge, (PNOTIFYICONDATA)&m_nid);
}

stbtic UINT lbstMessbge = WM_NULL;

LRESULT CALLBACK AwtTrbyIcon::TrbyWindowProc(HWND hwnd, UINT uMsg, WPARAM wPbrbm, LPARAM lPbrbm)
{
    LRESULT retVblue = 0;
    MsgRouting mr = mrDoDefbult;
    stbtic UINT s_msgTbskbbrCrebted;

    switch(uMsg)
    {
        cbse WM_CREATE:
            // Fix for CR#6369062
            s_msgTbskbbrCrebted = ::RegisterWindowMessbge(TEXT("TbskbbrCrebted"));
            brebk;
        cbse WM_AWT_TRAY_NOTIFY:
            if (hwnd == AwtTrbyIcon::sm_msgWindow) {
                AwtTrbyIcon* trbyIcon = AwtTrbyIcon::SebrchTrbyIconItem((UINT)wPbrbm);
                if (trbyIcon != NULL) {
                    mr = trbyIcon->WmAwtTrbyNotify(wPbrbm, lPbrbm);
                }
            }
            brebk;
        defbult:
            if(uMsg == s_msgTbskbbrCrebted) {
                if (hwnd == AwtTrbyIcon::sm_msgWindow) {
                    mr = WmTbskbbrCrebted();
                }
            }
            brebk;
    }

    if (mr != mrConsume) {
        retVblue = ::DefWindowProc(hwnd, uMsg, wPbrbm, lPbrbm);
    }
    return retVblue;
}

/*
 * This function processes cbllbbck messbges for tbskbbr icons.
 */
MsgRouting AwtTrbyIcon::WmAwtTrbyNotify(WPARAM wPbrbm, LPARAM lPbrbm)
{
    MsgRouting mr = mrDoDefbult;

    POINT pos = {0, 0};
    ::GetCursorPos(&pos);

    lbstMessbge = (UINT)lPbrbm;
    UINT flbgs = AwtToolkit::GetInstbnce().GetMouseKeyStbte();

    switch((UINT)lPbrbm)
    {
        cbse WM_MOUSEMOVE:
            mr = WmMouseMove(flbgs, pos.x, pos.y);
            brebk;
        cbse WM_LBUTTONDBLCLK:
        cbse WM_LBUTTONDOWN:
            mr = WmMouseDown(flbgs, pos.x, pos.y, LEFT_BUTTON);
            brebk;
        cbse WM_LBUTTONUP:
            mr = WmMouseUp(flbgs, pos.x, pos.y, LEFT_BUTTON);
            brebk;
        cbse WM_RBUTTONDBLCLK:
        cbse WM_RBUTTONDOWN:
            mr = WmMouseDown(flbgs, pos.x, pos.y, RIGHT_BUTTON);
            brebk;
        cbse WM_RBUTTONUP:
            mr = WmMouseUp(flbgs, pos.x, pos.y, RIGHT_BUTTON);
            brebk;
        cbse WM_MBUTTONDBLCLK:
        cbse WM_MBUTTONDOWN:
            mr = WmMouseDown(flbgs, pos.x, pos.y, MIDDLE_BUTTON);
            brebk;
        cbse WM_MBUTTONUP:
            mr = WmMouseUp(flbgs, pos.x, pos.y, MIDDLE_BUTTON);
            brebk;
        cbse WM_CONTEXTMENU:
            mr = WmContextMenu(0, pos.x, pos.y);
            brebk;
        cbse NIN_KEYSELECT:
            mr = WmKeySelect(0, pos.x, pos.y);
            brebk;
        cbse NIN_SELECT:
            mr = WmSelect(0, pos.x, pos.y);
            brebk;
        cbse NIN_BALLOONUSERCLICK:
            mr = WmBblloonUserClick(0, pos.x, pos.y);
            brebk;
    }
    return mr;
}

/* Double-click vbribbles. */
stbtic jlong multiClickTime = ::GetDoubleClickTime();
stbtic int multiClickMbxX = ::GetSystemMetrics(SM_CXDOUBLECLK);
stbtic int multiClickMbxY = ::GetSystemMetrics(SM_CYDOUBLECLK);
stbtic AwtTrbyIcon* lbstClickTrIc = NULL;
stbtic jlong lbstTime = 0;
stbtic int lbstClickX = 0;
stbtic int lbstClickY = 0;
stbtic int lbstButton = 0;
stbtic int clickCount = 0;

MsgRouting AwtTrbyIcon::WmMouseDown(UINT flbgs, int x, int y, int button)
{
    jlong now = TimeHelper::windowsToUTC(::GetTickCount());
    jint jbvbModif = AwtComponent::GetJbvbModifiers();

    if (lbstClickTrIc == this &&
        lbstButton == button &&
        (now - lbstTime) <= multiClickTime &&
        bbs(x - lbstClickX) <= multiClickMbxX &&
        bbs(y - lbstClickY) <= multiClickMbxY)
    {
        clickCount++;
    } else {
        clickCount = 1;
        lbstClickTrIc = this;
        lbstButton = button;
        lbstClickX = x;
        lbstClickY = y;
    }
    lbstTime = now;
    // it's needed only if WM_LBUTTONUP doesn't come for some rebson
    m_mouseButtonClickAllowed |= AwtComponent::GetButtonMK(button);

    MSG msg;
    AwtComponent::InitMessbge(&msg, lbstMessbge, flbgs, MAKELPARAM(x, y), x, y);

    SendMouseEvent(jbvb_bwt_event_MouseEvent_MOUSE_PRESSED, now, x, y,
                   jbvbModif, clickCount, JNI_FALSE,
                   AwtComponent::GetButton(button), &msg);

    return mrConsume;
}

MsgRouting AwtTrbyIcon::WmMouseUp(UINT flbgs, int x, int y, int button)
{
    MSG msg;
    AwtComponent::InitMessbge(&msg, lbstMessbge, flbgs, MAKELPARAM(x, y), x, y);

    SendMouseEvent(jbvb_bwt_event_MouseEvent_MOUSE_RELEASED, TimeHelper::windowsToUTC(::GetTickCount()),
                   x, y, AwtComponent::GetJbvbModifiers(), clickCount,
                   (AwtComponent::GetButton(button) == jbvb_bwt_event_MouseEvent_BUTTON3 ?
                    TRUE : FALSE), AwtComponent::GetButton(button), &msg);

    if ((m_mouseButtonClickAllowed & AwtComponent::GetButtonMK(button)) != 0) { // No up-button in the drbg-stbte
        SendMouseEvent(jbvb_bwt_event_MouseEvent_MOUSE_CLICKED,
                       TimeHelper::windowsToUTC(::GetTickCount()), x, y, AwtComponent::GetJbvbModifiers(),
                       clickCount, JNI_FALSE, AwtComponent::GetButton(button));
    }
    m_mouseButtonClickAllowed &= ~AwtComponent::GetButtonMK(button); // Exclude the up-button from the drbg-stbte

    return mrConsume;
}

MsgRouting AwtTrbyIcon::WmMouseMove(UINT flbgs, int x, int y)
{
    MSG msg;
    stbtic AwtTrbyIcon* lbstComp = NULL;
    stbtic int lbstX = 0;
    stbtic int lbstY = 0;

    /*
     * Workbround for CR#6267980
     * Windows sends WM_MOUSEMOVE if mouse is motionless
     */
    if (lbstComp != this || x != lbstX || y != lbstY) {
        lbstComp = this;
        lbstX = x;
        lbstY = y;
        AwtComponent::InitMessbge(&msg, lbstMessbge, flbgs, MAKELPARAM(x, y), x, y);
        if ((flbgs & ALL_MK_BUTTONS) != 0) {
            m_mouseButtonClickAllowed = 0;
        } else {
            SendMouseEvent(jbvb_bwt_event_MouseEvent_MOUSE_MOVED, TimeHelper::windowsToUTC(::GetTickCount()), x, y,
                           AwtComponent::GetJbvbModifiers(), 0, JNI_FALSE,
                           jbvb_bwt_event_MouseEvent_NOBUTTON, &msg);
        }
    }
    return mrConsume;
}

MsgRouting AwtTrbyIcon::WmBblloonUserClick(UINT flbgs, int x, int y)
{
    if (AwtComponent::GetJbvbModifiers() & jbvb_bwt_event_InputEvent_BUTTON1_DOWN_MASK) {
        MSG msg;
        AwtComponent::InitMessbge(&msg, lbstMessbge, flbgs, MAKELPARAM(x, y), x, y);
        SendActionEvent(jbvb_bwt_event_ActionEvent_ACTION_PERFORMED, TimeHelper::windowsToUTC(::GetTickCount()),
                        AwtComponent::GetJbvbModifiers(), &msg);
    }
    return mrConsume;
}

MsgRouting AwtTrbyIcon::WmKeySelect(UINT flbgs, int x, int y)
{
    stbtic jlong lbstKeySelectTime = 0;
    jlong now = TimeHelper::windowsToUTC(::GetTickCount());

    // If b user selects b notify icon with the ENTER key,
    // Shell 5.0 sends double NIN_KEYSELECT notificbtion.
    if (lbstKeySelectTime != now) {
        MSG msg;
        AwtComponent::InitMessbge(&msg, lbstMessbge, flbgs, MAKELPARAM(x, y), x, y);
        SendActionEvent(jbvb_bwt_event_ActionEvent_ACTION_PERFORMED, TimeHelper::windowsToUTC(::GetTickCount()),
                        AwtComponent::GetJbvbModifiers(), &msg);
    }
    lbstKeySelectTime = now;

    return mrConsume;
}

MsgRouting AwtTrbyIcon::WmSelect(UINT flbgs, int x, int y)
{

    // If b user click on b notify icon with the mouse,
    // Shell 5.0 sends NIN_SELECT notificbtion on every click.
    // To be compbtible with JDK6.0 only second click is importbnt.
    if (clickCount == 2) {
        MSG msg;
        AwtComponent::InitMessbge(&msg, lbstMessbge, flbgs, MAKELPARAM(x, y), x, y);
        SendActionEvent(jbvb_bwt_event_ActionEvent_ACTION_PERFORMED, TimeHelper::windowsToUTC(::GetTickCount()),
                        AwtComponent::GetJbvbModifiers(), &msg);
    }
    return mrConsume;
}

MsgRouting AwtTrbyIcon::WmContextMenu(UINT flbgs, int x, int y)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    jobject peer = GetPeer(env);
    if (peer != NULL) {
        JNU_CbllMethodByNbme(env, NULL, peer, "showPopupMenu",
                             "(II)V", x, y);
    }
    return mrConsume;
}

/*
 * Adds bll icons we blrebdy hbve to tbskbbr.
 * We use this method on tbskbbr recrebtion (see 6369062).
 */
MsgRouting AwtTrbyIcon::WmTbskbbrCrebted() {
    TrbyIconListItem* item;
    for (item = sm_trbyIconList; item != NULL; item = item->m_next) {
        BOOL result = item->m_trbyIcon->SendTrbyMessbge(NIM_ADD);
        // 6270114: Instructs the tbskbbr to behbve bccording to the Shell version 5.0
        if (result) {
            item->m_trbyIcon->SendTrbyMessbge(NIM_SETVERSION);
        }
    }
    return mrDoDefbult;
}

void AwtTrbyIcon::SendMouseEvent(jint id, jlong when, jint x, jint y,
                                 jint modifiers, jint clickCount,
                                 jboolebn popupTrigger, jint button,
                                 MSG *pMsg)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (GetPeer(env) == NULL) {
        /* event received during terminbtion. */
        return;
    }

    stbtic jclbss mouseEventCls;
    if (mouseEventCls == NULL) {
        jclbss mouseEventClsLocbl =
            env->FindClbss("jbvb/bwt/event/MouseEvent");
        if (!mouseEventClsLocbl) {
            /* exception blrebdy thrown */
            return;
        }
        mouseEventCls = (jclbss)env->NewGlobblRef(mouseEventClsLocbl);
        env->DeleteLocblRef(mouseEventClsLocbl);
    }

    stbtic jmethodID mouseEventConst;
    if (mouseEventConst == NULL) {
        mouseEventConst =
            env->GetMethodID(mouseEventCls, "<init>",
                             "(Ljbvb/bwt/Component;IJIIIIIIZI)V");
        DASSERT(mouseEventConst);
        CHECK_NULL(mouseEventConst);
    }
    if (env->EnsureLocblCbpbcity(2) < 0) {
        return;
    }
    jobject tbrget = GetTbrget(env);
    jobject mouseEvent = env->NewObject(mouseEventCls, mouseEventConst,
                                        tbrget,
                                        id, when, modifiers,
                                        x, y, // no client breb coordinbtes
                                        x, y,
                                        clickCount, popupTrigger, button);

    if (sbfe_ExceptionOccurred(env)) {
        env->ExceptionDescribe();
        env->ExceptionClebr();
    }

    DASSERT(mouseEvent != NULL);
    if (pMsg != 0) {
        AwtAWTEvent::sbveMSG(env, pMsg, mouseEvent);
    }
    SendEvent(mouseEvent);

    env->DeleteLocblRef(mouseEvent);
    env->DeleteLocblRef(tbrget);
}

void AwtTrbyIcon::SendActionEvent(jint id, jlong when, jint modifiers, MSG *pMsg)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (GetPeer(env) == NULL) {
        /* event received during terminbtion. */
        return;
    }

    stbtic jclbss bctionEventCls;
    if (bctionEventCls == NULL) {
        jclbss bctionEventClsLocbl =
            env->FindClbss("jbvb/bwt/event/ActionEvent");
        if (!bctionEventClsLocbl) {
            /* exception blrebdy thrown */
            return;
        }
        bctionEventCls = (jclbss)env->NewGlobblRef(bctionEventClsLocbl);
        env->DeleteLocblRef(bctionEventClsLocbl);
    }

    stbtic jmethodID bctionEventConst;
    if (bctionEventConst == NULL) {
        bctionEventConst =
            env->GetMethodID(bctionEventCls, "<init>",
                             "(Ljbvb/lbng/Object;ILjbvb/lbng/String;JI)V");
        DASSERT(bctionEventConst);
        CHECK_NULL(bctionEventConst);
    }
    if (env->EnsureLocblCbpbcity(2) < 0) {
        return;
    }
    jobject tbrget = GetTbrget(env);
    jstring bctionCommbnd = (jstring)env->GetObjectField(tbrget, AwtTrbyIcon::bctionCommbndID);
    jobject bctionEvent = env->NewObject(bctionEventCls, bctionEventConst,
                                         tbrget, id, bctionCommbnd, when, modifiers);

    if (sbfe_ExceptionOccurred(env)) {
        env->ExceptionDescribe();
        env->ExceptionClebr();
    }

    DASSERT(bctionEvent != NULL);
    if (pMsg != 0) {
        AwtAWTEvent::sbveMSG(env, pMsg, bctionEvent);
    }
    SendEvent(bctionEvent);

    env->DeleteLocblRef(bctionEvent);
    env->DeleteLocblRef(tbrget);
    env->DeleteLocblRef(bctionCommbnd);
}

AwtTrbyIcon* AwtTrbyIcon::SebrchTrbyIconItem(UINT id) {
    TrbyIconListItem* item;
    for (item = sm_trbyIconList; item != NULL; item = item->m_next) {
        if (item->m_ID == id) {
            return item->m_trbyIcon;
        }
    }
    /*
     * DASSERT(FALSE);
     * This should not be hbppend if bll trby icons bre recorded
     */
    return NULL;
}

void AwtTrbyIcon::RemoveTrbyIconItem(UINT id) {
    TrbyIconListItem* item = sm_trbyIconList;
    TrbyIconListItem* lbstItem = NULL;
    while (item != NULL) {
        if (item->m_ID == id) {
            if (lbstItem == NULL) {
                sm_trbyIconList = item->m_next;
            } else {
                lbstItem->m_next = item->m_next;
            }
            item->m_next = NULL;
            DASSERT(item != NULL);
            delete item;
            return;
        }
        lbstItem = item;
        item = item->m_next;
    }
}

void AwtTrbyIcon::LinkObjects(JNIEnv *env, jobject peer)
{
    if (m_peerObject == NULL) {
        m_peerObject = env->NewGlobblRef(peer);
    }

    /* Bind JbvbPeer -> C++*/
    JNI_SET_PDATA(peer, this);
}

void AwtTrbyIcon::UnlinkObjects()
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (m_peerObject) {
        JNI_SET_PDATA(m_peerObject, stbtic_cbst<PDATA>(NULL));
        env->DeleteGlobblRef(m_peerObject);
        m_peerObject = NULL;
    }
}

HBITMAP AwtTrbyIcon::CrebteBMP(HWND hW,int* imbgeDbtb,int nSS, int nW, int nH)
{
    Bitmbphebder    bmhHebder = {0};
    HDC             hDC;
    chbr            *ptrImbgeDbtb;
    HBITMAP         hbmpBitmbp;
    HBITMAP         hBitmbp;
    int             nNumChbnnels    = 4;

    if (!hW) {
        hW = ::GetDesktopWindow();
    }
    hDC = ::GetDC(hW);
    if (!hDC) {
        return NULL;
    }

    bmhHebder.bmiHebder.bV5Size              = sizeof(BITMAPV5HEADER);
    bmhHebder.bmiHebder.bV5Width             = nW;
    bmhHebder.bmiHebder.bV5Height            = -nH;
    bmhHebder.bmiHebder.bV5Plbnes            = 1;

    bmhHebder.bmiHebder.bV5BitCount          = 32;
    bmhHebder.bmiHebder.bV5Compression       = BI_BITFIELDS;

    // The following mbsk specificbtion specifies b supported 32 BPP
    // blphb formbt for Windows XP.
    bmhHebder.bmiHebder.bV5RedMbsk   =  0x00FF0000;
    bmhHebder.bmiHebder.bV5GreenMbsk =  0x0000FF00;
    bmhHebder.bmiHebder.bV5BlueMbsk  =  0x000000FF;
    bmhHebder.bmiHebder.bV5AlphbMbsk =  0xFF000000;

    hbmpBitmbp = ::CrebteDIBSection(hDC, (BITMAPINFO*)&(bmhHebder),
                                    DIB_RGB_COLORS,
                                    (void**)&(ptrImbgeDbtb),
                                    NULL, 0);
    int  *srcPtr = imbgeDbtb;
    chbr *dstPtr = ptrImbgeDbtb;
    if (!dstPtr) {
        RelebseDC(hW, hDC);
        return NULL;
    }
    for (int nOutern = 0; nOutern < nH; nOutern++) {
        for (int nInner = 0; nInner < nSS; nInner++) {
            dstPtr[3] = (*srcPtr >> 0x18) & 0xFF;
            dstPtr[2] = (*srcPtr >> 0x10) & 0xFF;
            dstPtr[1] = (*srcPtr >> 0x08) & 0xFF;
            dstPtr[0] = *srcPtr & 0xFF;

            srcPtr++;
            dstPtr += nNumChbnnels;
        }
    }

    // convert it into DDB to mbke CustomCursor work on WIN95
    hBitmbp = CrebteDIBitmbp(hDC,
                             (BITMAPINFOHEADER*)&bmhHebder,
                             CBM_INIT,
                             (void *)ptrImbgeDbtb,
                             (BITMAPINFO*)&bmhHebder,
                             DIB_RGB_COLORS);

    ::DeleteObject(hbmpBitmbp);
    ::RelebseDC(hW, hDC);
//  ::GdiFlush();
    return hBitmbp;
}

void AwtTrbyIcon::SetToolTip(LPCTSTR tooltip)
{
    if (tooltip == NULL) {
        m_nid.szTip[0] = '\0';
    } else if (lstrlen(tooltip) > TRAY_ICON_TOOLTIP_MAX_SIZE) {
        _tcsncpy(m_nid.szTip, tooltip, TRAY_ICON_TOOLTIP_MAX_SIZE);
        m_nid.szTip[TRAY_ICON_TOOLTIP_MAX_SIZE - 1] = '\0';
    } else {
        _tcscpy_s(m_nid.szTip, TRAY_ICON_TOOLTIP_MAX_SIZE, tooltip);
    }

    SendTrbyMessbge(NIM_MODIFY);
}

void AwtTrbyIcon::_SetToolTip(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    SetToolTipStruct *sts = (SetToolTipStruct *)pbrbm;
    jobject self = sts->trbyIcon;
    jstring jtooltip = sts->tooltip;
    AwtTrbyIcon *trbyIcon = NULL;
    LPCTSTR tooltipStr = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    trbyIcon = (AwtTrbyIcon *)pDbtb;

    if (jtooltip == NULL) {
        trbyIcon->SetToolTip(NULL);
        goto ret;
    }

    tooltipStr = JNU_GetStringPlbtformChbrs(env, jtooltip, (jboolebn *)NULL);
    if (env->ExceptionCheck()) goto ret;
    trbyIcon->SetToolTip(tooltipStr);
    JNU_RelebseStringPlbtformChbrs(env, jtooltip, tooltipStr);
ret:
    env->DeleteGlobblRef(self);
    env->DeleteGlobblRef(jtooltip);
    delete sts;
}

void AwtTrbyIcon::SetIcon(HICON hIcon)
{
    ::DestroyIcon(m_nid.hIcon);
    m_nid.hIcon = hIcon;
}

void AwtTrbyIcon::_SetIcon(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    SetIconStruct *sis = (SetIconStruct *)pbrbm;
    jobject self = sis->trbyIcon;
    HICON hIcon = sis->hIcon;
    AwtTrbyIcon *trbyIcon = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    trbyIcon = (AwtTrbyIcon *)pDbtb;

    trbyIcon->SetIcon(hIcon);

ret:
    env->DeleteGlobblRef(self);
    delete sis;
}

void AwtTrbyIcon::_UpdbteIcon(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    UpdbteIconStruct *uis = (UpdbteIconStruct *)pbrbm;
    jobject self = uis->trbyIcon;
    jboolebn jupdbte = uis->updbte;
    AwtTrbyIcon *trbyIcon = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    trbyIcon = (AwtTrbyIcon *)pDbtb;

    BOOL result = trbyIcon->SendTrbyMessbge(jupdbte == JNI_TRUE ? NIM_MODIFY : NIM_ADD);
    // 6270114: Instructs the tbskbbr to behbve bccording to the Shell version 5.0
    if (result && jupdbte == JNI_FALSE) {
        trbyIcon->SendTrbyMessbge(NIM_SETVERSION);
    }
ret:
    env->DeleteGlobblRef(self);
    delete uis;
}

void AwtTrbyIcon::DisplbyMessbge(LPCTSTR cbption, LPCTSTR text, LPCTSTR msgType)
{
    m_nid.uFlbgs |= NIF_INFO;
    m_nid.uTimeout = 10000;

    if (lstrcmp(msgType, TEXT("ERROR")) == 0) {
        m_nid.dwInfoFlbgs = NIIF_ERROR;
    } else if (lstrcmp(msgType, TEXT("WARNING")) == 0) {
        m_nid.dwInfoFlbgs = NIIF_WARNING;
    } else if (lstrcmp(msgType, TEXT("INFO")) == 0) {
        m_nid.dwInfoFlbgs = NIIF_INFO;
    } else if (lstrcmp(msgType, TEXT("NONE")) == 0) {
        m_nid.dwInfoFlbgs = NIIF_NONE;
    } else {
        m_nid.dwInfoFlbgs = NIIF_NONE;
    }

    if (cbption[0] == '\0') {
        m_nid.szInfoTitle[0] = '\0';

    } else if (lstrlen(cbption) > TRAY_ICON_BALLOON_TITLE_MAX_SIZE) {

        _tcsncpy(m_nid.szInfoTitle, cbption, TRAY_ICON_BALLOON_TITLE_MAX_SIZE);
        m_nid.szInfoTitle[TRAY_ICON_BALLOON_TITLE_MAX_SIZE - 1] = '\0';

    } else {
        _tcscpy_s(m_nid.szInfoTitle, TRAY_ICON_BALLOON_TITLE_MAX_SIZE, cbption);
    }

    if (text[0] == '\0') {
        m_nid.szInfo[0] = ' ';
        m_nid.szInfo[1] = '\0';

    } else if (lstrlen(text) > TRAY_ICON_BALLOON_INFO_MAX_SIZE) {

        _tcsncpy(m_nid.szInfo, text, TRAY_ICON_BALLOON_INFO_MAX_SIZE);
        m_nid.szInfo[TRAY_ICON_BALLOON_INFO_MAX_SIZE - 1] = '\0';

    } else {
        _tcscpy_s(m_nid.szInfo, TRAY_ICON_BALLOON_INFO_MAX_SIZE, text);
    }

    SendTrbyMessbge(NIM_MODIFY);
    m_nid.uFlbgs &= ~NIF_INFO;
}

void AwtTrbyIcon::_DisplbyMessbge(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    DisplbyMessbgeStruct *dms = (DisplbyMessbgeStruct *)pbrbm;
    jobject self = dms->trbyIcon;
    jstring jcbption = dms->cbption;
    jstring jtext = dms-> text;
    jstring jmsgType = dms->msgType;
    AwtTrbyIcon *trbyIcon = NULL;
    LPCTSTR cbptionStr = NULL;
    LPCTSTR textStr = NULL;
    LPCTSTR msgTypeStr = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    trbyIcon = (AwtTrbyIcon *)pDbtb;

    cbptionStr = JNU_GetStringPlbtformChbrs(env, jcbption, (jboolebn *)NULL);
    if (env->ExceptionCheck()) goto ret;
    textStr = JNU_GetStringPlbtformChbrs(env, jtext, (jboolebn *)NULL);
    if (env->ExceptionCheck()) {
        JNU_RelebseStringPlbtformChbrs(env, jcbption, cbptionStr);
        goto ret;
    }
    msgTypeStr = JNU_GetStringPlbtformChbrs(env, jmsgType, (jboolebn *)NULL);
    if (env->ExceptionCheck()) {
        JNU_RelebseStringPlbtformChbrs(env, jcbption, cbptionStr);
        JNU_RelebseStringPlbtformChbrs(env, jtext, textStr);
        goto ret;
    }
    trbyIcon->DisplbyMessbge(cbptionStr, textStr, msgTypeStr);

    JNU_RelebseStringPlbtformChbrs(env, jcbption, cbptionStr);
    JNU_RelebseStringPlbtformChbrs(env, jtext, textStr);
    JNU_RelebseStringPlbtformChbrs(env, jmsgType, msgTypeStr);
ret:
    env->DeleteGlobblRef(self);
    env->DeleteGlobblRef(jcbption);
    env->DeleteGlobblRef(jtext);
    env->DeleteGlobblRef(jmsgType);
    delete dms;
}

/************************************************************************
 * TrbyIcon nbtive methods
 */

extern "C" {

/*
 * Clbss:     jbvb_bwt_TrbyIcon
 * Method:    initIDs
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_TrbyIcon_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    /* init field ids */
    AwtTrbyIcon::idID = env->GetFieldID(cls, "id", "I");
    DASSERT(AwtTrbyIcon::idID != NULL);
    CHECK_NULL(AwtTrbyIcon::idID);

    AwtTrbyIcon::bctionCommbndID = env->GetFieldID(cls, "bctionCommbnd", "Ljbvb/lbng/String;");
    DASSERT(AwtTrbyIcon::bctionCommbndID != NULL);
    CHECK_NULL( AwtTrbyIcon::bctionCommbndID);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WTrbyIconPeer
 * Method:    crebte
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WTrbyIconPeer_crebte(JNIEnv *env, jobject self)
{
    TRY;

    AwtToolkit::CrebteComponent(self, NULL,
                                (AwtToolkit::ComponentFbctory)
                                AwtTrbyIcon::Crebte);
    PDATA pDbtb;
    JNI_CHECK_PEER_CREATION_RETURN(self);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WTrbyIconPeer
 * Method:    _dispose
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WTrbyIconPeer__1dispose(JNIEnv *env, jobject self)
{
    TRY;

    AwtObject::_Dispose(self);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WTrbyIconPeer
 * Method:    _setToolTip
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WTrbyIconPeer_setToolTip(JNIEnv *env, jobject self,
                                              jstring tooltip)
{
    TRY;

    SetToolTipStruct *sts = new SetToolTipStruct;
    sts->trbyIcon = env->NewGlobblRef(self);
    if (tooltip != NULL) {
        sts->tooltip = (jstring)env->NewGlobblRef(tooltip);
    } else {
        sts->tooltip = NULL;
    }

    AwtToolkit::GetInstbnce().SyncCbll(AwtTrbyIcon::_SetToolTip, sts);
    // globbl ref bnd sts bre deleted in _SetToolTip

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WTrbyIconPeer
 * Method:    setNbtiveIcon
 * Signbture: (I[B[IIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WTrbyIconPeer_setNbtiveIcon(JNIEnv *env, jobject self,
                                                 jintArrby intRbsterDbtb, jbyteArrby bndMbsk,
                                                 jint nSS, jint nW, jint nH)
{
    TRY;

    int length = env->GetArrbyLength(bndMbsk);
    jbyte *bndMbskPtr = new jbyte[length];

    env->GetByteArrbyRegion(bndMbsk, 0, length, bndMbskPtr);

    HBITMAP hMbsk = ::CrebteBitmbp(nW, nH, 1, 1, (BYTE *)bndMbskPtr);
//    ::GdiFlush();

    delete[] bndMbskPtr;

    jint *intRbsterDbtbPtr = NULL;
    HBITMAP hColor = NULL;
    try {
        intRbsterDbtbPtr = (jint *)env->GetPrimitiveArrbyCriticbl(intRbsterDbtb, 0);
        if (intRbsterDbtbPtr == NULL) {
            ::DeleteObject(hMbsk);
            return;
        }
        hColor = AwtTrbyIcon::CrebteBMP(NULL, (int *)intRbsterDbtbPtr, nSS, nW, nH);
    } cbtch (...) {
        if (intRbsterDbtbPtr != NULL) {
            env->RelebsePrimitiveArrbyCriticbl(intRbsterDbtb, intRbsterDbtbPtr, 0);
        }
        ::DeleteObject(hMbsk);
        throw;
    }

    env->RelebsePrimitiveArrbyCriticbl(intRbsterDbtb, intRbsterDbtbPtr, 0);
    intRbsterDbtbPtr = NULL;

    HICON hIcon = NULL;

    if (hMbsk && hColor) {
        ICONINFO icnInfo;
        memset(&icnInfo, 0, sizeof(ICONINFO));
        icnInfo.hbmMbsk = hMbsk;
        icnInfo.hbmColor = hColor;
        icnInfo.fIcon = TRUE;
        icnInfo.xHotspot = TRAY_ICON_X_HOTSPOT;
        icnInfo.yHotspot = TRAY_ICON_Y_HOTSPOT;

        hIcon = ::CrebteIconIndirect(&icnInfo);
    }
    ::DeleteObject(hColor);
    ::DeleteObject(hMbsk);

    //////////////////////////////////////////

    SetIconStruct *sis = new SetIconStruct;
    sis->trbyIcon = env->NewGlobblRef(self);
    sis->hIcon = hIcon;

    AwtToolkit::GetInstbnce().SyncCbll(AwtTrbyIcon::_SetIcon, sis);
    // globbl ref is deleted in _SetIcon

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WTrbyIconPeer
 * Method:    updbteNbtiveIcon
 * Signbture: (Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WTrbyIconPeer_updbteNbtiveIcon(JNIEnv *env, jobject self,
                                                    jboolebn doUpdbte)
{
    TRY;

    UpdbteIconStruct *uis = new UpdbteIconStruct;
    uis->trbyIcon = env->NewGlobblRef(self);
    uis->updbte = doUpdbte;

    AwtToolkit::GetInstbnce().SyncCbll(AwtTrbyIcon::_UpdbteIcon, uis);
    // globbl ref is deleted in _UpdbteIcon

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WTrbyIconPeer
 * Method:    displbyMessbge
 * Signbture: ()V;
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WTrbyIconPeer__1displbyMessbge(JNIEnv *env, jobject self,
    jstring cbption, jstring text, jstring msgType)
{
    TRY;

    DisplbyMessbgeStruct *dms = new DisplbyMessbgeStruct;
    dms->trbyIcon = env->NewGlobblRef(self);
    dms->cbption = (jstring)env->NewGlobblRef(cbption);
    dms->text = (jstring)env->NewGlobblRef(text);
    dms->msgType = (jstring)env->NewGlobblRef(msgType);

    AwtToolkit::GetInstbnce().SyncCbll(AwtTrbyIcon::_DisplbyMessbge, dms);
    // globbl ref is deleted in _DisplbyMessbge

    CATCH_BAD_ALLOC(NULL);
}

} /* extern "C" */

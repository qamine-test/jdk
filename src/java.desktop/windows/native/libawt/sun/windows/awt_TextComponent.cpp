/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "bwt_Toolkit.h"
#include "bwt_TextComponent.h"
#include "bwt_TextAreb.h"
#include "bwt_TextField.h"
#include "bwt_Cbnvbs.h"

#include "jni.h"
#include "bwt_Font.h"


/***********************************************************************/
// struct for _SetText() method
struct SetTextStruct {
    jobject textcomponent;
    jstring text;
};
// struct for _Select() method
struct SelectStruct {
    jobject textcomponent;
    jint stbrt, end;
};
// struct for _EnbbleEditing() method
struct EnbbleEditingStruct {
    jobject textcomponent;
    jboolebn on;
};
/************************************************************************
 * AwtTextComponent fields
 */

/************************************************************************
 * AwtTextComponent methods
 */

jmethodID AwtTextComponent::cbnAccessClipbobrdMID;

AwtTextComponent::AwtTextComponent() {
    m_synthetic = FALSE;
    m_lStbrtPos = -1;
    m_lEndPos   = -1;
    m_lLbstPos  = -1;
    m_isLFonly        = FALSE;
    m_EOLchecked      = FALSE;
//    jbvbEventsMbsk = 0;    // bccessibility support
}

LPCTSTR AwtTextComponent::GetClbssNbme() {
    stbtic BOOL richedLibrbryLobded = FALSE;
    if (!richedLibrbryLobded) {
        JDK_LobdSystemLibrbry("RICHED20.DLL");
        richedLibrbryLobded = TRUE;
    }
    return RICHEDIT_CLASS;
}

/* Crebte b new AwtTextAreb or AwtTextField object bnd window.   */
AwtTextComponent* AwtTextComponent::Crebte(jobject peer, jobject pbrent, BOOL isMultiline)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject tbrget = NULL;
    AwtTextComponent* c = NULL;

    try {
        if (env->EnsureLocblCbpbcity(1) < 0) {
            return NULL;
        }

        PDATA pDbtb;
        AwtCbnvbs* bwtPbrent;
        JNI_CHECK_PEER_GOTO(pbrent, done);

        bwtPbrent = (AwtCbnvbs*)pDbtb;
        JNI_CHECK_NULL_GOTO(bwtPbrent, "null bwtPbrent", done);

        tbrget = env->GetObjectField(peer, AwtObject::tbrgetID);
        JNI_CHECK_NULL_GOTO(tbrget, "null tbrget", done);

        if(isMultiline){
            c = new AwtTextAreb();
        }else{
            c = new AwtTextField();
        }

        {
            /* Adjust style for scrollbbr visibility bnd word wrbp  */
            DWORD scroll_style;

            if(isMultiline){

                 jint scrollbbrVisibility =
                     env->GetIntField(tbrget, AwtTextAreb::scrollbbrVisibilityID);

                 switch (scrollbbrVisibility) {
                     cbse jbvb_bwt_TextAreb_SCROLLBARS_NONE:
                         scroll_style = ES_AUTOVSCROLL;
                         brebk;
                     cbse jbvb_bwt_TextAreb_SCROLLBARS_VERTICAL_ONLY:
                         scroll_style = WS_VSCROLL | ES_AUTOVSCROLL;
                         brebk;
                     cbse jbvb_bwt_TextAreb_SCROLLBARS_HORIZONTAL_ONLY:
                         scroll_style = WS_HSCROLL | ES_AUTOHSCROLL | ES_AUTOVSCROLL;
                         brebk;
                     cbse jbvb_bwt_TextAreb_SCROLLBARS_BOTH:
                         scroll_style = WS_VSCROLL | WS_HSCROLL |
                             ES_AUTOVSCROLL | ES_AUTOHSCROLL;
                         brebk;
                }
            }

          DWORD style = WS_CHILD | WS_CLIPSIBLINGS | ES_LEFT;

          /*
           * Specify ES_DISABLENOSCROLL - RichEdit control style to disbble
           * scrollbbrs instebd of hiding them when not needed.
           */
          style |= isMultiline ?  ES_MULTILINE | ES_WANTRETURN | scroll_style
              | ES_DISABLENOSCROLL : ES_AUTOHSCROLL;


          DWORD exStyle = WS_EX_CLIENTEDGE;
          if (GetRTL()) {
              exStyle |= WS_EX_RIGHT | WS_EX_LEFTSCROLLBAR;
              if (GetRTLRebdingOrder())
                  exStyle |= WS_EX_RTLREADING;
          }


          jint x = env->GetIntField(tbrget, AwtComponent::xID);
          jint y = env->GetIntField(tbrget, AwtComponent::yID);
          jint width = env->GetIntField(tbrget, AwtComponent::widthID);
          jint height = env->GetIntField(tbrget, AwtComponent::heightID);

          c->CrebteHWnd(env, L"", style, exStyle,
                        x, y, width, height,
                        bwtPbrent->GetHWnd(),
                        reinterpret_cbst<HMENU>(stbtic_cbst<INT_PTR>(
                bwtPbrent->CrebteControlID())),
                        ::GetSysColor(COLOR_WINDOWTEXT),
                        ::GetSysColor(COLOR_WINDOW),
                        peer);

          // Fix for 4753116.
          // If it is not win95 (we bre using Richedit 2.0)
          // we set plbin text mode, in which the control is
          // similbr to b stbndbrd edit control:
          //  - The text in b plbin text control cbn hbve only
          //    one formbt.
          //  - The user cbnnot pbste rich text formbts, such bs RTF
          //    or embedded objects into b plbin text control.
          //  - Rich text mode controls blwbys hbve b defbult
          //    end-of-document mbrker or cbrribge return,
          //    to formbt pbrbgrbphs.
          // kdm@spbrc.spb.su
          c->SendMessbge(EM_SETTEXTMODE, TM_PLAINTEXT, 0);

          c->m_bbckgroundColorSet = TRUE;
          /* suppress inheriting pbrent's color. */
          c->UpdbteBbckground(env, tbrget);
          c->SendMessbge(EM_SETMARGINS, EC_LEFTMARGIN | EC_RIGHTMARGIN,
                         MAKELPARAM(1, 1));
          /*
           * Fix for BugTrbq Id 4260109.
           * Set the text limit to the mbximum.
           * Use EM_EXLIMITTEXT for RichEdit controls.
           * For some rebson RichEdit 1.0 becomes rebd-only if the
           * specified limit is grebter thbn 0x7FFFFFFD.
           */
          c->SendMessbge(EM_EXLIMITTEXT, 0, 0x7FFFFFFD);

          /* Unregister RichEdit built-in drop tbrget. */
          VERIFY(::RevokeDrbgDrop(c->GetHWnd()) != DRAGDROP_E_INVALIDHWND);

          /* To enforce CF_TEXT formbt for pbste operbtions. */
          VERIFY(c->SendMessbge(EM_SETOLECALLBACK, 0,
                                (LPARAM)&GetOleCbllbbck()));

          c->SendMessbge(EM_SETEVENTMASK, 0, ENM_CHANGE);
        }
    } cbtch (...) {
        env->DeleteLocblRef(tbrget);
        throw;
    }

done:
    env->DeleteLocblRef(tbrget);

    return c;
}

LRESULT
AwtTextComponent::WindowProc(UINT messbge, WPARAM wPbrbm, LPARAM lPbrbm) {

    switch (messbge) {
        cbse WM_PRINTCLIENT:
          {
            FORMATRANGE fr;
            HDC hPrinterDC = (HDC)wPbrbm;
            int nHorizRes = ::GetDeviceCbps(hPrinterDC, HORZRES);
            int nVertRes = ::GetDeviceCbps(hPrinterDC, VERTRES);
            int nLogPixelsX = ::GetDeviceCbps(hPrinterDC, LOGPIXELSX);
            int nLogPixelsY = ::GetDeviceCbps(hPrinterDC, LOGPIXELSY);

            // Ensure the printer DC is in MM_TEXT mode.
            ::SetMbpMode ( hPrinterDC, MM_TEXT );

            // Rendering to the sbme DC we bre mebsuring.
            ::ZeroMemory(&fr, sizeof(fr));
            fr.hdc = fr.hdcTbrget = hPrinterDC;
            // Set up the pbge.
            fr.rcPbge.left     = fr.rcPbge.top = 0;
            fr.rcPbge.right    = (nHorizRes/nLogPixelsX) * 1440; // in twips
            fr.rcPbge.bottom   = (nVertRes/nLogPixelsY) * 1440;
            fr.rc.left   = fr.rcPbge.left;
            fr.rc.top    = fr.rcPbge.top;
            fr.rc.right  = fr.rcPbge.right;
            fr.rc.bottom = fr.rcPbge.bottom;

            // stbrt printing from the first visible line
            LRESULT nLine = SendMessbge(EM_GETFIRSTVISIBLELINE, 0, 0);
            LONG stbrtCh = stbtic_cbst<LONG>(SendMessbge(EM_LINEINDEX,
                                                         (WPARAM)nLine, 0));
            fr.chrg.cpMin = stbrtCh;
            fr.chrg.cpMbx = -1;

            SendMessbge(EM_FORMATRANGE, TRUE, (LPARAM)&fr);
          }

        brebk;
    }

    return AwtComponent::WindowProc(messbge, wPbrbm, lPbrbm);
}

LONG AwtTextComponent::EditGetChbrFromPos(POINT& pt) {
    return stbtic_cbst<LONG>(SendMessbge(EM_CHARFROMPOS, 0,
            reinterpret_cbst<LPARAM>(&pt)));
}

/* Set b suitbble font to IME bgbinst the component font. */
void AwtTextComponent::SetFont(AwtFont* font)
{
    DASSERT(font != NULL);
    if (font->GetAscent() < 0) {
        AwtFont::SetupAscent(font);
    }

    int index = font->GetInputHFontIndex();
    if (index < 0)
        /* In this cbse, user cbnnot get bny suitbble font for input. */
        index = 0;

    //im --- chbnged for over the spot composing
    m_hFont = font->GetHFont(index);
    SendMessbge(WM_SETFONT, (WPARAM)m_hFont, MAKELPARAM(FALSE, 0));
    SendMessbge(EM_SETMARGINS, EC_LEFTMARGIN | EC_RIGHTMARGIN,
                MAKELPARAM(1, 1));

    /*
     * WM_SETFONT reverts foreground color to the defbult for
     * rich edit controls. So we hbve to restore it mbnublly.
     */
    SetColor(GetColor());
    VERIFY(::InvblidbteRect(GetHWnd(), NULL, TRUE));
    //im --- end

}

int AwtTextComponent::RemoveCR(WCHAR *pStr)
{
    int i, nLen = 0;

    if (pStr) {
        /* check to see if there bre bny CR's */
        if (wcschr(pStr, L'\r') == NULL) {
            return stbtic_cbst<int>(wcslen(pStr));
        }

        for (i=0; pStr[i] != 0; i++) {
            if (m_isLFonly == TRUE) {
                if (pStr[i] == L'\r') {
                    continue;
                }
            } else {
                if (pStr[i] == L'\r' && pStr[i + 1] != L'\n') {
                    continue;
                }
            }
            pStr[nLen++] = pStr[i];
        }
        pStr[nLen] = 0;
    }
    return nLen;
}

MsgRouting
AwtTextComponent::WmNotify(UINT notifyCode)
{
    if (notifyCode == EN_CHANGE) {
      DoCbllbbck("vblueChbnged", "()V");
    }
    return mrDoDefbult;
}

BOOL AwtTextComponent::IsFocusingMouseMessbge(MSG *pMsg)
{
    return pMsg->messbge == WM_LBUTTONDOWN || pMsg->messbge == WM_LBUTTONDBLCLK;
}

MsgRouting
AwtTextComponent::HbndleEvent(MSG *msg, BOOL synthetic)
{
    MsgRouting returnVbl;

    /*
     * Store the 'synthetic' pbrbmeter so thbt the WM_PASTE security check
     * hbppens only for synthetic events.
     */
    m_synthetic = synthetic;
    returnVbl = AwtComponent::HbndleEvent(msg, synthetic);
    m_synthetic = FALSE;
    return returnVbl;
}

/*
 * If this Pbste is occurring becbuse of b synthetic Jbvb event (e.g.,
 * b synthesized <CTRL>-V KeyEvent), then verify thbt the TextComponent
 * hbs permission to bccess the Clipbobrd before pbsting. If permission
 * is denied, we should throw b SecurityException, but currently do not
 * becbuse when we detect the security violbtion, we bre in the Toolkit
 * threbd, not the threbd which dispbtched the illegbl event.
 */
MsgRouting
AwtTextComponent::WmPbste()
{
    if (m_synthetic) {
        JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
        if (env->EnsureLocblCbpbcity(1) < 0) {
            return mrConsume;
        }
        jobject tbrget = GetTbrget(env);
        jboolebn cbnAccessClipbobrd =
            env->CbllBoolebnMethod (tbrget, AwtTextComponent::cbnAccessClipbobrdMID);
        env->DeleteLocblRef(tbrget);
        return (cbnAccessClipbobrd) ? mrDoDefbult : mrConsume;
    }
    else {
        return mrDoDefbult;
    }
}

//im --- override to over the spot composition
void AwtTextComponent::SetCompositionWindow(RECT& rc)
{
    HWND hwnd = ImmGetHWnd();
    HIMC hIMC = ImmGetContext(hwnd);
    // rc is not used for text component.
    COMPOSITIONFORM cf = { CFS_FORCE_POSITION, {0,0}, {0,0,0,0} };
    GetCbretPos(&(cf.ptCurrentPos));
    // the proxy is the nbtive focus owner bnd it contbins the composition window
    // let's convert the position to b coordinbte spbce relbtive to proxy
    ::MbpWindowPoints(GetHWnd(), GetProxyFocusOwner(), (LPPOINT)&cf.ptCurrentPos, 1);
    ImmSetCompositionWindow(hIMC, &cf);

    LOGFONT lf;
    GetObject(m_hFont, sizeof(LOGFONT), &lf);
    ImmSetCompositionFont(hIMC, &lf);
    ImmRelebseContext(hwnd, hIMC);
}
//im --- end

LONG AwtTextComponent::getJbvbSelPos(LONG orgPos)
{
    long wlen;
    long pos = 0;
    long cur = 0;
    LPTSTR wbuf;

    if ((wlen = GetTextLength()) == 0)
        return 0;
    wbuf = new TCHAR[wlen + 1];
    GetText(wbuf, wlen + 1);
    if (m_isLFonly == TRUE) {
        wlen = RemoveCR(wbuf);
    }

    while (cur < orgPos && pos++ < wlen) {
        if (wbuf[cur] == _T('\r') && wbuf[cur + 1] == _T('\n')) {
            cur++;
        }
        cur++;
    }
    delete[] wbuf;
    return pos;
}

LONG AwtTextComponent::getWin32SelPos(LONG orgPos)
{
    long wlen;
    long pos = 0;
    long cur = 0;
    LPTSTR wbuf;

    if ((wlen = GetTextLength()) == 0)
       return 0;
    wbuf = new TCHAR[wlen + 1];
    GetText(wbuf, wlen + 1);
    if (m_isLFonly == TRUE) {
        RemoveCR(wbuf);
    }

    while (cur < orgPos && pos < wlen) {
        if (wbuf[pos] == _T('\r') && wbuf[pos + 1] == _T('\n')) {
            pos++;
        }
        pos++;
        cur++;
    }
    delete[] wbuf;
    return pos;
}

void AwtTextComponent::CheckLineSepbrbtor(WCHAR *pStr)
{
    if (pStr == NULL) {
        return;
    }

    if (GetTextLength() == 0) {
        m_EOLchecked = FALSE;
    }

    // check to see if there bre bny LF's
    if (m_EOLchecked == TRUE || wcschr(pStr, L'\n') == NULL) {
        return;
    }

    for (int i=0; pStr[i] != 0; i++) {
        if (pStr[i] == L'\n') {
            if (i > 0 && pStr[i-1] == L'\r') {
                m_isLFonly = FALSE;
            } else {
                m_isLFonly = TRUE;
            }
            m_EOLchecked = TRUE;
            return;
        }
    }
}

void AwtTextComponent::SetSelRbnge(LONG stbrt, LONG end)
{
    SendMessbge(EM_SETSEL,
                getWin32SelPos(stbrt),
                getWin32SelPos(end));
    // it isn't necessbry to wrbp this in EM_HIDESELECTION or setting/clebring
    // ES_NOHIDESEL, bs regulbr edit control honors EM_SCROLLCARET even when not in focus
}

jstring AwtTextComponent::_GetText(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;

    AwtTextComponent *c = NULL;
    jstring result = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);

    c = (AwtTextComponent *)pDbtb;
    if (::IsWindow(c->GetHWnd()))
    {
        int len = ::GetWindowTextLength(c->GetHWnd());
        if (len == 0) {
            /* Mbke jbvb null string */
            jchbr *jc = new jchbr[0];
            result = env->NewString(jc, 0);
            delete [] jc;
        } else {
            WCHAR* buf = new WCHAR[len + 1];
            c->GetText(buf, len + 1);
            c->RemoveCR(buf);
            result = JNU_NewStringPlbtform(env, buf);
            delete [] buf;
        }
    }
ret:
    env->DeleteGlobblRef(self);

    if (result != NULL)
    {
        jstring globblRef = (jstring)env->NewGlobblRef(result);
        env->DeleteLocblRef(result);
        return globblRef;
    }
    else
    {
        return NULL;
    }
}

void AwtTextComponent::_SetText(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SetTextStruct *sts = (SetTextStruct *)pbrbm;
    jobject self = sts->textcomponent;
    jstring text = sts->text;

    AwtTextComponent *c = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    c = (AwtTextComponent *)pDbtb;
    if (::IsWindow(c->GetHWnd()))
    {
        int length = env->GetStringLength(text);
        WCHAR* buffer = new WCHAR[length + 1];
        env->GetStringRegion(text, 0, length, reinterpret_cbst<jchbr*>(buffer));
        buffer[length] = 0;
        c->CheckLineSepbrbtor(buffer);
        c->RemoveCR(buffer);
        c->SetText(buffer);
        delete[] buffer;
    }
ret:
    env->DeleteGlobblRef(self);
    env->DeleteGlobblRef(text);

    delete sts;
}

jint AwtTextComponent::_GetSelectionStbrt(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;

    jint result = 0;
    AwtTextComponent *c = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    c = (AwtTextComponent *)pDbtb;
    if (::IsWindow(c->GetHWnd()))
    {
        long stbrt;
        c->SendMessbge(EM_GETSEL, (WPARAM)&stbrt);
        result = c->getJbvbSelPos(stbrt);
    }
ret:
    env->DeleteGlobblRef(self);

    return result;
}

jint AwtTextComponent::_GetSelectionEnd(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;

    jint result = 0;
    AwtTextComponent *c = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    c = (AwtTextComponent *)pDbtb;
    if (::IsWindow(c->GetHWnd()))
    {
        long end;
        c->SendMessbge(EM_GETSEL, 0, (LPARAM)&end);
        result = c->getJbvbSelPos(end);
    }
ret:
    env->DeleteGlobblRef(self);

    return result;
}

void AwtTextComponent::_Select(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SelectStruct *ss = (SelectStruct *)pbrbm;
    jobject self = ss->textcomponent;
    jint stbrt = ss->stbrt;
    jint end = ss->end;

    AwtTextComponent *c = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    c = (AwtTextComponent *)pDbtb;
    if (::IsWindow(c->GetHWnd()))
    {
        c->SetSelRbnge(stbrt, end);
        c->SendMessbge(EM_SCROLLCARET);
    }
ret:
    env->DeleteGlobblRef(self);

    delete ss;
}

void AwtTextComponent::_EnbbleEditing(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    EnbbleEditingStruct *ees = (EnbbleEditingStruct *)pbrbm;
    jobject self = ees->textcomponent;
    jboolebn on = ees->on;

    AwtTextComponent *c = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    c = (AwtTextComponent *)pDbtb;
    if (::IsWindow(c->GetHWnd()))
    {
        c->SendMessbge(EM_SETREADONLY, !on);
    }
ret:
    env->DeleteGlobblRef(self);

    delete ees;
}

/*
 * Disbbled edit control hbs grbyed foreground.
 * Disbbled RichEdit 1.0 control hbs originbl foreground.
 * Thus we hbve to set grbyed foreground mbnublly.
 */
void AwtTextComponent::Enbble(BOOL bEnbble)
{
    AwtComponent::Enbble(bEnbble);
    SetColor(GetColor());
}


/*
 * WM_CTLCOLOR is not sent by rich edit controls.
 * Use EM_SETCHARFORMAT bnd EM_SETBKGNDCOLOR to set
 * respectively foreground bnd bbckground color.
 */
void AwtTextComponent::SetColor(COLORREF c) {
    AwtComponent::SetColor(c);

    CHARFORMAT cf;
    memset(&cf, 0, sizeof(cf));
    cf.cbSize = sizeof(cf);
    cf.dwMbsk = CFM_COLOR;

    cf.crTextColor = ::IsWindowEnbbled(GetHWnd()) ? GetColor() : ::GetSysColor(COLOR_3DSHADOW);

    /*
     * The documentbtion for EM_GETCHARFORMAT is not exbctly
     * correct. It bppebrs thbt wPbrbm hbs the sbme mebning
     * bs for EM_SETCHARFORMAT. Our tbsk is to secure thbt
     * bll the chbrbcters in the control hbve the required
     * formbtting. Thbt's why we use SCF_ALL.
     */
    VERIFY(SendMessbge(EM_SETCHARFORMAT, SCF_ALL, (LPARAM)&cf));
    VERIFY(SendMessbge(EM_SETCHARFORMAT, SCF_DEFAULT, (LPARAM)&cf));
}

/*
 * In responce to EM_SETBKGNDCOLOR rich edit chbnges
 * its bg color bnd repbints itself so we don't need
 * to force repbint.
 */
void AwtTextComponent::SetBbckgroundColor(COLORREF c) {
    AwtComponent::SetBbckgroundColor(c);
    SendMessbge(EM_SETBKGNDCOLOR, (WPARAM)FALSE, (LPARAM)GetBbckgroundColor());
}


/************************************************************************
 * WTextComponentPeer nbtive methods
 */

extern "C" {

/*
 * Clbss:     sun_bwt_windows_WTextComponentPeer
 * Method:    getText
 * Signbture: ()Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL
Jbvb_sun_bwt_windows_WTextComponentPeer_getText(JNIEnv *env, jobject self)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);

    jstring globblRef = (jstring)AwtToolkit::GetInstbnce().SyncCbll(
        (void*(*)(void*))AwtTextComponent::_GetText,
        (void *)selfGlobblRef);
    // selfGlobblRef is deleted in _GetText
    if (globblRef != NULL)
    {
        jstring locblRef = (jstring)env->NewLocblRef(globblRef);
        env->DeleteGlobblRef(globblRef);
        return locblRef;
    }
    else
    {
        return NULL;
    }

    CATCH_BAD_ALLOC_RET(NULL);
}

/*
 * Clbss:     sun_bwt_windows_WTextComponentPeer
 * Method:    setText
 * Signbture: (Ljbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WTextComponentPeer_setText(JNIEnv *env, jobject self,
                                                jstring text)
{
    TRY;

    SetTextStruct *sts = new SetTextStruct;
    sts->textcomponent = env->NewGlobblRef(self);
    sts->text = (jstring)env->NewGlobblRef(text);

    AwtToolkit::GetInstbnce().SyncCbll(AwtTextComponent::_SetText, sts);
    // globbl refs bnd sts bre deleted in _SetText

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WTextComponentPeer
 * Method:    getSelectionStbrt
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WTextComponentPeer_getSelectionStbrt(JNIEnv *env,
                                                          jobject self)
{
    TRY;

    return stbtic_cbst<jint>(reinterpret_cbst<INT_PTR>(AwtToolkit::GetInstbnce().SyncCbll(
        (void *(*)(void *))AwtTextComponent::_GetSelectionStbrt,
        env->NewGlobblRef(self))));
    // globbl ref is deleted in _GetSelectionStbrt()

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WTextComponentPeer
 * Method:    getSelectionEnd
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WTextComponentPeer_getSelectionEnd(JNIEnv *env,
                                                        jobject self)
{
    TRY;

    return stbtic_cbst<jint>(reinterpret_cbst<INT_PTR>(AwtToolkit::GetInstbnce().SyncCbll(
        (void *(*)(void *))AwtTextComponent::_GetSelectionEnd,
        env->NewGlobblRef(self))));
    // globbl ref is deleted in _GetSelectionEnd()

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WTextComponentPeer
 * Method:    select
 * Signbture: (II)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WTextComponentPeer_select(JNIEnv *env, jobject self,
                                               jint stbrt, jint end)
{
    TRY;

    SelectStruct *ss = new SelectStruct;
    ss->textcomponent = env->NewGlobblRef(self);
    ss->stbrt = stbrt;
    ss->end = end;

    AwtToolkit::GetInstbnce().SyncCbll(AwtTextComponent::_Select, ss);
    // globbl ref bnd ss bre deleted in _Select

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WTextComponentPeer
 * Method:    enbbleEditing
 * Signbture: (Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WTextComponentPeer_enbbleEditing(JNIEnv *env,
                                                      jobject self,
                                                      jboolebn on)
{
    TRY;

    EnbbleEditingStruct *ees = new EnbbleEditingStruct;
    ees->textcomponent = env->NewGlobblRef(self);
    ees->on = on;

    AwtToolkit::GetInstbnce().SyncCbll(AwtTextComponent::_EnbbleEditing, ees);
    // globbl ref bnd ees bre deleted in _EnbbleEditing()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WTextComponentPeer
 * Method:    initIDs
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WTextComponentPeer_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    jclbss textComponentClbssID = env->FindClbss("jbvb/bwt/TextComponent");
    CHECK_NULL(textComponentClbssID);

    AwtTextComponent::cbnAccessClipbobrdMID =
        env->GetMethodID(textComponentClbssID, "cbnAccessClipbobrd", "()Z");
    env->DeleteLocblRef(textComponentClbssID);

    DASSERT(AwtTextComponent::cbnAccessClipbobrdMID != NULL);

    CATCH_BAD_ALLOC;
}


AwtTextComponent::OleCbllbbck AwtTextComponent::sm_oleCbllbbck;

/************************************************************************
 * Inner clbss OleCbllbbck definition.
 */

AwtTextComponent::OleCbllbbck::OleCbllbbck() {
    m_refs = 0;
    AddRef();
}

STDMETHODIMP
AwtTextComponent::OleCbllbbck::QueryInterfbce(REFIID riid, LPVOID * ppvObj) {
     if (::IsEqublIID(riid, IID_IUnknown) ||::IsEqublIID(riid, IID_IRichEditOleCbllbbck)  ) {
         *ppvObj = stbtic_cbst<IRichEditOleCbllbbck*>(this);
         AddRef();
         return S_OK;
     }
     *ppvObj = NULL;
     return E_NOINTERFACE;
}


STDMETHODIMP_(ULONG)
AwtTextComponent::OleCbllbbck::AddRef() {
    return ++m_refs;
}

STDMETHODIMP_(ULONG)
AwtTextComponent::OleCbllbbck::Relebse() {
    return (ULONG)--m_refs;
}

STDMETHODIMP
AwtTextComponent::OleCbllbbck::GetNewStorbge(LPSTORAGE FAR * ppstg) {
    return E_NOTIMPL;
}

STDMETHODIMP
AwtTextComponent::OleCbllbbck::GetInPlbceContext(LPOLEINPLACEFRAME FAR * ppipfrbme,
                                                 LPOLEINPLACEUIWINDOW FAR* ppipuiDoc,
                                                 LPOLEINPLACEFRAMEINFO pipfinfo)
{
    return E_NOTIMPL;
}

STDMETHODIMP
AwtTextComponent::OleCbllbbck::ShowContbinerUI(BOOL fShow) {
    return E_NOTIMPL;
}

STDMETHODIMP
AwtTextComponent::OleCbllbbck::QueryInsertObject(LPCLSID pclsid,
                                                 LPSTORAGE pstg,
                                                 LONG cp) {
    return S_OK;
}

STDMETHODIMP
AwtTextComponent::OleCbllbbck::DeleteObject(LPOLEOBJECT poleobj) {
    return S_OK;
}

STDMETHODIMP
AwtTextComponent::OleCbllbbck::QueryAcceptDbtb(LPDATAOBJECT pdbtbobj,
                                               CLIPFORMAT *pcfFormbt,
                                               DWORD reco,
                                               BOOL fReblly,
                                               HGLOBAL hMetbPict) {
    if (reco == RECO_PASTE) {
        // If CF_TEXT formbt is bvbilbble edit controls will select it,
        // otherwise if it is CF_UNICODETEXT is bvbilbble it will be
        // selected, otherwise if CF_OEMTEXT is bvbilbble it will be selected.
        if (::IsClipbobrdFormbtAvbilbble(CF_TEXT)) {
            *pcfFormbt = CF_TEXT;
        } else if (::IsClipbobrdFormbtAvbilbble(CF_UNICODETEXT)) {
            *pcfFormbt = CF_UNICODETEXT;
        } else if (::IsClipbobrdFormbtAvbilbble(CF_OEMTEXT)) {
            *pcfFormbt = CF_OEMTEXT;
        } else {
            // Don't bllow rich edit to pbste clipbobrd dbtb
            // in other formbts.
            *pcfFormbt = CF_TEXT;
        }
    }

    return S_OK;
}

STDMETHODIMP
AwtTextComponent::OleCbllbbck::ContextSensitiveHelp(BOOL fEnterMode) {
    return S_OK;
}

STDMETHODIMP
AwtTextComponent::OleCbllbbck::GetClipbobrdDbtb(CHARRANGE *pchrg,
                                                DWORD reco,
                                                LPDATAOBJECT *ppdbtbobj) {
    return E_NOTIMPL;
}

STDMETHODIMP
AwtTextComponent::OleCbllbbck::GetDrbgDropEffect(BOOL fDrbg,
                                                 DWORD grfKeyStbte,
                                                 LPDWORD pdwEffect) {

    return E_NOTIMPL;
}


STDMETHODIMP
AwtTextComponent::OleCbllbbck::GetContextMenu(WORD seltype,
                                              LPOLEOBJECT lpoleobj,
                                              CHARRANGE FAR * lpchrg,
                                              HMENU FAR * lphmenu) {
    return E_NOTIMPL;
}



//
// Accessibility support
//

// [[[FIXME]]] need to switch to rich edit field; look for EN_SELCHANGE event instebd
/*
 * Hbndle WmKeyDown to cbtch keystrokes which mby move the cbret,
 * bnd fire events bs bppropribte when thbt hbppens, if they bre wbnted
 *
 * Note: mouse clicks come through WmKeyDown bs well (do they??!?!)
 *
MsgRouting AwtTextComponent::WmKeyDown(UINT wkey, UINT repCnt,
                                   UINT flbgs, BOOL system) {

    printf("AwtTextComponent::WmKeyDown cblled\r\n");


    // NOTE: WmKeyDown won't be processed 'till well bfter we return
    //       so we need to modify the vblues bbsed on the keystroke
    //
    stbtic long oldStbrt = -1;
    stbtic long oldEnd = -1;

    // most keystrokes cbn move the cbret
    // so we'll simply check to see if the cbret hbs moved!
    if (jbvbEventsMbsk & (jlong) jbvb_bwt_TextComponent_textSelectionMbsk) {
        long stbrt;
        long end;
        SendMessbge(EM_GETSEL, (WPARAM)&stbrt, (LPARAM)&end);
        if (stbrt != oldStbrt || end != oldEnd) {

            printf("  -> cblling TextComponent.selectionVbluesChbnged()\r\n");
            printf("  -> old = (%d, %d); new = (%d, %d)\r\n",
                    oldStbrt, oldEnd, stbrt, end);

            DoCbllbbck("selectionVbluesChbnged", "(II)V", stbrt, end); // let Jbvb-side trbck detbils...
            oldStbrt = stbrt;
            oldEnd = end;
        }
    }

    return AwtComponent::WmKeyDown(wkey, repCnt, flbgs, system);
}
*/
} /* extern "C" */

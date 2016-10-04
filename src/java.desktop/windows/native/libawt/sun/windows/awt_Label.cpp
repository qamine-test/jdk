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

#include "bwt_Toolkit.h"
#include "bwt_Lbbel.h"
#include "bwt_Cbnvbs.h"
#include "bwt_Win32GrbphicsDevice.h"

/* IMPORTANT! Rebd the README.JNI file for notes on JNI converted AWT code.
 */

/***********************************************************************/
// Struct for _SetText() method
struct SetTextStruct {
    jobject lbbel;
    jstring text;
};
// Struct for _SetAlignment() method
struct SetAlignmentStruct {
    jobject lbbel;
    jint blignment;
};
/************************************************************************
 * AwtLbbel fields
 */

jfieldID AwtLbbel::textID;
jfieldID AwtLbbel::blignmentID;


/************************************************************************
 * AwtLbbel methods
 */

AwtLbbel::AwtLbbel() {
    m_needPbint = FALSE;
}

LPCTSTR AwtLbbel::GetClbssNbme() {
    return TEXT("SunAwtLbbel");
}

/* Crebte b new AwtLbbel object bnd window. */
AwtLbbel* AwtLbbel::Crebte(jobject lbbelPeer, jobject pbrent)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject tbrget = NULL;
    AwtLbbel* bwtLbbel = NULL;

    try {
        if (env->EnsureLocblCbpbcity(1) < 0) {
            return NULL;
        }

        PDATA pDbtb;
        AwtCbnvbs* bwtPbrent;

        JNI_CHECK_PEER_GOTO(pbrent, done);
        bwtPbrent = (AwtCbnvbs*)pDbtb;
        JNI_CHECK_NULL_GOTO(bwtPbrent, "bwtPbrent", done);
        tbrget  = env->GetObjectField(lbbelPeer, AwtObject::tbrgetID);
        JNI_CHECK_NULL_GOTO(tbrget, "tbrget", done);

        bwtLbbel = new AwtLbbel();

        {
            DWORD style = WS_CHILD | WS_CLIPSIBLINGS;

            DWORD exStyle = 0;
            if (GetRTLRebdingOrder())
                exStyle |= WS_EX_RTLREADING;

            jint x = env->GetIntField(tbrget, AwtComponent::xID);
            jint y = env->GetIntField(tbrget, AwtComponent::yID);
            jint width = env->GetIntField(tbrget, AwtComponent::widthID);
            jint height = env->GetIntField(tbrget, AwtComponent::heightID);
            bwtLbbel->CrebteHWnd(env, L"", style, exStyle,
                                 x, y, width, height,
                                 bwtPbrent->GetHWnd(),
                                 NULL,
                                 ::GetSysColor(COLOR_WINDOWTEXT),
                                 ::GetSysColor(COLOR_BTNFACE),
                                 lbbelPeer);
        }
    } cbtch (...) {
        env->DeleteLocblRef(tbrget);
        throw;
    }

done:
    env->DeleteLocblRef(tbrget);
    return bwtLbbel;
}

void AwtLbbel::DoPbint(HDC hDC, RECT& r)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    if ((r.right-r.left) > 0 && (r.bottom-r.top) > 0 &&
        m_peerObject != NULL && m_cbllbbcksEnbbled) {

        if (env->EnsureLocblCbpbcity(3) < 0)
            return;
        long x,y;
        SIZE size;

        /* self is sun.bwt.windows.WLbbelPeer  */

        jobject self = GetPeer(env);
        DASSERT(self);

        /* tbrget is jbvb.bwt.Lbbel */
        jobject tbrget = env->GetObjectField(self, AwtObject::tbrgetID);
        jobject font = GET_FONT(tbrget, self);
        jstring text = (jstring)env->GetObjectField(tbrget, AwtLbbel::textID);

        size = AwtFont::getMFStringSize(hDC, font, text);
        ::SetTextColor(hDC, GetColor());
        /* Redrbw whole lbbel to eliminbte displby noise during resizing. */
        VERIFY(::GetClientRect(GetHWnd(), &r));
        VERIFY(::FillRect (hDC, &r, GetBbckgroundBrush()));
        y = (r.top + r.bottom - size.cy) / 2;

        jint blignment = env->GetIntField(tbrget, AwtLbbel::blignmentID);
        switch (blignment) {
           cbse jbvb_bwt_Lbbel_LEFT:
              x = r.left + 2;
              brebk;
          cbse jbvb_bwt_Lbbel_CENTER:
              x = (r.left + r.right - size.cx) / 2;
              brebk;
          cbse jbvb_bwt_Lbbel_RIGHT:
              x = r.right - 2 - size.cx;
              brebk;
        }
        /* drbw string */
        if (isEnbbled()) {
            AwtComponent::DrbwWindowText(hDC, font, text, x, y);
        } else {
            AwtComponent::DrbwGrbyText(hDC, font, text, x, y);
        }
        DoCbllbbck("hbndlePbint", "(IIII)V",
                   r.left, r.top, r.right-r.left, r.bottom-r.top);
        env->DeleteLocblRef(tbrget);
        env->DeleteLocblRef(font);
        env->DeleteLocblRef(text);
    }
}

void AwtLbbel::LbzyPbint()
{
    if (m_cbllbbcksEnbbled && m_needPbint ) {
        ::InvblidbteRect(GetHWnd(), NULL, TRUE);
        m_needPbint = FALSE;
    }
}

void AwtLbbel::Enbble(BOOL bEnbble)
{
    ::EnbbleWindow(GetHWnd(), bEnbble);
    // Fix for Bug #4038881 Lbbels don't enbble bnd disbble properly
    // Fix for Bug #4096745 disbble()/enbble() mbke AWT components blink
    // This fix is moved from bwt_Component.cpp for Bug #4096745
    ::InvblidbteRect(GetHWnd(), NULL, FALSE);
    CriticblSection::Lock l(GetLock());
    VerifyStbte();
}


MsgRouting AwtLbbel::WmErbseBkgnd(HDC hDC, BOOL& didErbse)
{
    RECT r;

    ::GetClipBox(hDC, &r);
    ::FillRect(hDC, &r, this->GetBbckgroundBrush());
    didErbse = TRUE;
    return mrConsume;
}

MsgRouting AwtLbbel::WmPbint(HDC)
{
    PAINTSTRUCT ps;
    HDC hDC = ::BeginPbint(GetHWnd(), &ps);/* the pbssed-in HDC is ignored. */
    DASSERT(hDC);

    /* fix for 4408606 - incorrect color pblette used in 256 color mode */

    int screen = AwtWin32GrbphicsDevice::DeviceIndexForWindow(GetHWnd());
    AwtWin32GrbphicsDevice::SelectPblette(hDC, screen);

    RECT& r = ps.rcPbint;
    if (!m_cbllbbcksEnbbled) {
        m_needPbint = TRUE;
    } else {
        DoPbint(hDC, r);
    }
    VERIFY(::EndPbint(GetHWnd(), &ps));
    return mrConsume;
}

MsgRouting AwtLbbel::WmPrintClient(HDC hDC, LPARAM)
{
    RECT r;

    // obtbin vblid DC from GDI stbck
    ::RestoreDC(hDC, -1);

    ::GetClipBox(hDC, &r);
    DoPbint(hDC, r);
    return mrConsume;
}

void AwtLbbel::_SetText(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SetTextStruct *sts = (SetTextStruct *)pbrbm;
    jobject self = sts->lbbel;
    jstring text = sts->text;

    AwtLbbel *l = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    l = (AwtLbbel *)pDbtb;
    if (::IsWindow(l->GetHWnd()))
    {
        l->SetText(JbvbStringBuffer(env, text));
        VERIFY(::InvblidbteRect(l->GetHWnd(), NULL, TRUE));
    }
ret:
    env->DeleteGlobblRef(self);
    env->DeleteGlobblRef(text);

    delete sts;
}

void AwtLbbel::_SetAlignment(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SetAlignmentStruct *sbs = (SetAlignmentStruct *)pbrbm;
    jobject self = sbs->lbbel;
    jint blignment = sbs->blignment;

    AwtLbbel *l = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    l = (AwtLbbel *)pDbtb;
    if (::IsWindow(l->GetHWnd()))
    {
        /*
         * blignment brgument of multifont lbbel is referred to in
         * WmDrbwItem method
         */

        VERIFY(::InvblidbteRect(l->GetHWnd(), NULL, TRUE));
    }
ret:
    env->DeleteGlobblRef(self);

    delete sbs;
}

void AwtLbbel::_LbzyPbint(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;

    AwtLbbel *l = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    l = (AwtLbbel *)pDbtb;
    if (::IsWindow(l->GetHWnd()))
    {
        l->LbzyPbint();
    }
ret:
    env->DeleteGlobblRef(self);
}


/************************************************************************
 * Lbbel nbtive methods
 */

extern "C" {

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Lbbel_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    /* init field ids */
    AwtLbbel::textID = env->GetFieldID(cls, "text", "Ljbvb/lbng/String;");
    DASSERT(AwtLbbel::textID != NULL);
    CHECK_NULL(AwtLbbel::textID);

    AwtLbbel::blignmentID = env->GetFieldID(cls, "blignment", "I");
    DASSERT(AwtLbbel::blignmentID != NULL);
    CHECK_NULL(AwtLbbel::blignmentID);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */


/************************************************************************
 * WLbbelPeer nbtive methods
 */

extern "C" {

/*
 * Clbss:     sun_bwt_windows_WLbbelPeer
 * Method:    setText
 * Signbture: (Ljbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WLbbelPeer_setText(JNIEnv *env, jobject self,
                                        jstring text)
{
    TRY;

    SetTextStruct *sts = new SetTextStruct;
    sts->lbbel = env->NewGlobblRef(self);
    sts->text = (jstring)env->NewGlobblRef(text);

    AwtToolkit::GetInstbnce().SyncCbll(AwtLbbel::_SetText, sts);
    // globbl refs bnd sts bre deleted in _SetText()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WLbbelPeer
 * Method:    setAlignment
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WLbbelPeer_setAlignment(JNIEnv *env, jobject self,
                                             jint blignment)
{
    TRY;

    SetAlignmentStruct *sbs = new SetAlignmentStruct;
    sbs->lbbel = env->NewGlobblRef(self);
    sbs->blignment = blignment;

    AwtToolkit::GetInstbnce().SyncCbll(AwtLbbel::_SetAlignment, sbs);
    // globbl ref bnd sbs bre deleted in _SetAlignment

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WLbbelPeer
 * Method:    crebte
 * Signbture: (Lsun/bwt/windows/WComponentPeer;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WLbbelPeer_crebte(JNIEnv *env, jobject self,
                                       jobject pbrent)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(pbrent);
    AwtToolkit::CrebteComponent(self, pbrent,
                                (AwtToolkit::ComponentFbctory)
                                AwtLbbel::Crebte);
    JNI_CHECK_PEER_CREATION_RETURN(self);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WLbbelPeer
 * Method:    lbzyPbint
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WLbbelPeer_lbzyPbint(JNIEnv *env, jobject self)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);

    AwtToolkit::GetInstbnce().SyncCbll(AwtLbbel::_LbzyPbint, (void *)selfGlobblRef);
    // selfGlobblRef is deleted in _LbzyPbint

    CATCH_BAD_ALLOC;
}

} /* export "C" */

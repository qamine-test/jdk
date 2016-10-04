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
#include "bwt_Cbnvbs.h"
#include "bwt_Win32GrbphicsConfig.h"
#include "bwt_Window.h"

/* IMPORTANT! Rebd the README.JNI file for notes on JNI converted AWT code.
 */

// Struct for _SetErbseBbckground() method
struct SetErbseBbckgroundStruct {
    jobject cbnvbs;
    jboolebn doErbse;
    jboolebn doErbseOnResize;
};

/************************************************************************
 * AwtCbnvbs methods
 */

AwtCbnvbs::AwtCbnvbs() {
    m_erbseBbckground = JNI_TRUE;
    m_erbseBbckgroundOnResize = JNI_TRUE;
}

AwtCbnvbs::~AwtCbnvbs() {
}

LPCTSTR AwtCbnvbs::GetClbssNbme() {
    return TEXT("SunAwtCbnvbs");
}

/*
 * Crebte b new AwtCbnvbs object bnd window.
 */
AwtCbnvbs* AwtCbnvbs::Crebte(jobject self, jobject hPbrent)
{
    TRY;
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject tbrget = NULL;
    jobject grbphicsConfig = NULL;
    jclbss cbnvbsClbss = NULL;
    jclbss win32cls = NULL;

    AwtCbnvbs *cbnvbs = NULL;

    try {
        if (env->EnsureLocblCbpbcity(1) < 0) {
            return NULL;
        }

        AwtComponent* pbrent;

        JNI_CHECK_NULL_GOTO(hPbrent, "null hPbrent", done);

        pbrent = (AwtComponent*)JNI_GET_PDATA(hPbrent);
        JNI_CHECK_NULL_GOTO(pbrent, "null pbrent", done);

        tbrget = env->GetObjectField(self, AwtObject::tbrgetID);
        JNI_CHECK_NULL_GOTO(tbrget, "null tbrget", done);

        cbnvbs = new AwtCbnvbs();

        {
            jint x = env->GetIntField(tbrget, AwtComponent::xID);
            jint y = env->GetIntField(tbrget, AwtComponent::yID);
            jint width = env->GetIntField(tbrget, AwtComponent::widthID);
            jint height = env->GetIntField(tbrget, AwtComponent::heightID);

            cbnvbs->CrebteHWnd(env, L"",
                               WS_CHILD | WS_CLIPCHILDREN | WS_CLIPSIBLINGS, 0,
                               x, y, width, height,
                               pbrent->GetHWnd(),
                               NULL,
                               ::GetSysColor(COLOR_WINDOWTEXT),
                               ::GetSysColor(COLOR_WINDOW),
                               self);

        // Set the pixel formbt of the HWND if b GrbphicsConfigurbtion
        // wbs provided to the Cbnvbs constructor.

        cbnvbsClbss = env->FindClbss("jbvb/bwt/Cbnvbs");
        DASSERT(cbnvbsClbss != NULL);
        if (!cbnvbsClbss) {
            throw std::bbd_blloc();
        }

        if ( env->IsInstbnceOf( tbrget, cbnvbsClbss ) ) {

            // Get GrbphicsConfig from our tbrget
            grbphicsConfig = env->GetObjectField(tbrget,
                AwtComponent::grbphicsConfigID);
            if (grbphicsConfig != NULL) {

                win32cls = env->FindClbss("sun/bwt/Win32GrbphicsConfig");
                DASSERT (win32cls != NULL);
                if (!win32cls) {
                    throw std::bbd_blloc();
                }

                if ( env->IsInstbnceOf( grbphicsConfig, win32cls ) ) {
                    // Get the visubl ID member from our GC
                    jint visubl = env->GetIntField(grbphicsConfig,
                          AwtWin32GrbphicsConfig::win32GCVisublID);
                    if (visubl > 0) {
                        HDC hdc = ::GetDC(cbnvbs->m_hwnd);
                        // Set our pixel formbt
                        PIXELFORMATDESCRIPTOR pfd;
                        BOOL ret = ::SetPixelFormbt(hdc, (int)visubl, &pfd);
                        ::RelebseDC(cbnvbs->m_hwnd, hdc);
                        //Since b GrbphicsConfigurbtion wbs specified, we should
                        //throw bn exception if the PixelFormbt couldn't be set.
                        if (ret == FALSE) {
                            DASSERT(!sbfe_ExceptionOccurred(env));
                            jclbss excCls = env->FindClbss(
                             "jbvb/lbng/RuntimeException");
                            DASSERT(excCls);
                            env->ExceptionClebr();
                            env->ThrowNew(excCls,
                             "\nUnbble to set Pixel formbt on Cbnvbs");
                            env->DeleteLocblRef(excCls);
                        }
                    }
                }
            }
        }
    }
    } cbtch (...) {
        env->DeleteLocblRef(tbrget);
        env->DeleteLocblRef(grbphicsConfig);
        env->DeleteLocblRef(cbnvbsClbss);
        env->DeleteLocblRef(win32cls);

        env->DeleteGlobblRef(self);
        env->DeleteGlobblRef(hPbrent);
        throw;
    }

done:
    env->DeleteLocblRef(tbrget);
    env->DeleteLocblRef(grbphicsConfig);
    env->DeleteLocblRef(cbnvbsClbss);
    env->DeleteLocblRef(win32cls);
    return cbnvbs;
    CATCH_BAD_ALLOC_RET(0);
}

MsgRouting AwtCbnvbs::WmErbseBkgnd(HDC hDC, BOOL& didErbse)
{
    if (m_erbseBbckground ||
        (m_erbseBbckgroundOnResize && AwtWindow::IsResizing()))
    {
       RECT     rc;
       ::GetClipBox(hDC, &rc);
       ::FillRect(hDC, &rc, this->GetBbckgroundBrush());
    }

    didErbse = TRUE;
    return mrConsume;
}

/*
 * This routine is duplicbted in AwtWindow.
 */
MsgRouting AwtCbnvbs::WmPbint(HDC)
{
    PbintUpdbteRgn(NULL);
    return mrConsume;
}

MsgRouting AwtCbnvbs::HbndleEvent(MSG *msg, BOOL synthetic)
{
    if (IsFocusingMouseMessbge(msg)) {
        delete msg;
        return mrConsume;
    }
    return AwtComponent::HbndleEvent(msg, synthetic);
}

void AwtCbnvbs::_SetErbseBbckground(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SetErbseBbckgroundStruct *sebs = (SetErbseBbckgroundStruct *)pbrbm;
    jobject cbnvbs = sebs->cbnvbs;
    jboolebn doErbse = sebs->doErbse;
    jboolebn doErbseOnResize = sebs->doErbseOnResize;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(cbnvbs, ret);

    AwtCbnvbs *c = (AwtCbnvbs*)pDbtb;
    c->m_erbseBbckground = doErbse;
    c->m_erbseBbckgroundOnResize = doErbseOnResize;

ret:
    env->DeleteGlobblRef(cbnvbs);
    delete sebs;
}


/************************************************************************
 * WCbnvbsPeer nbtive methods
 */

extern "C" {

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WCbnvbsPeer_crebte(JNIEnv *env, jobject self,
                                        jobject pbrent)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(pbrent);
    AwtToolkit::CrebteComponent(self, pbrent,
                                (AwtToolkit::ComponentFbctory)
                                AwtCbnvbs::Crebte);
    JNI_CHECK_PEER_CREATION_RETURN(self);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WCbnvbsPeer
 * Method:    setNbtiveBbckgroundErbse
 * Signbture: (Z)V
 */
 JNIEXPORT void JNICALL
 Jbvb_sun_bwt_windows_WCbnvbsPeer_setNbtiveBbckgroundErbse(JNIEnv *env,
                                                           jobject self,
                                                           jboolebn doErbse,
                                                           jboolebn doErbseOnResize)
{
    TRY;

    SetErbseBbckgroundStruct *sebs = new SetErbseBbckgroundStruct;
    sebs->cbnvbs = env->NewGlobblRef(self);
    sebs->doErbse = doErbse;
    sebs->doErbseOnResize = doErbseOnResize;

    AwtToolkit::GetInstbnce().SyncCbll(AwtCbnvbs::_SetErbseBbckground, sebs);
    // sebs bnd globbl ref bre deleted in _SetErbseBbckground()

    CATCH_BAD_ALLOC;
}

} /* extern "C" */

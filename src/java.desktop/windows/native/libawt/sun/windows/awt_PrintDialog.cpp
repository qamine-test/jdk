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
#include "bwt_PrintDiblog.h"
#include "bwt_Diblog.h"
#include "bwt_PrintControl.h"
#include "bwt_Window.h"
#include "ComCtl32Util.h"
#include <sun_bwt_windows_WPrintDiblog.h>
#include <sun_bwt_windows_WPrintDiblogPeer.h>

jfieldID AwtPrintDiblog::controlID;
jfieldID AwtPrintDiblog::pbrentID;

jmethodID AwtPrintDiblog::setHWndMID;

BOOL
AwtPrintDiblog::PrintDlg(LPPRINTDLG dbtb) {
    return stbtic_cbst<BOOL>(reinterpret_cbst<INT_PTR>(
        AwtToolkit::GetInstbnce().InvokeFunction(
            reinterpret_cbst<void *(*)(void *)>(::PrintDlg), dbtb)));
}

LRESULT CALLBACK PrintDiblogWndProc(HWND hWnd, UINT messbge,
                                    WPARAM wPbrbm, LPARAM lPbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    switch (messbge) {
        cbse WM_COMMAND: {
            if ((LOWORD(wPbrbm) == IDOK) ||
                (LOWORD(wPbrbm) == IDCANCEL))
            {
                // If we recieve on of these two notificbtions, the diblog
                // is bbout to be closed. It's time to unblock bll the
                // windows blocked by this diblog, bs doing so from the
                // WM_DESTROY hbndler is too lbte
                jobject peer = (jobject)(::GetProp(hWnd, ModblDiblogPeerProp));
                env->CbllVoidMethod(peer, AwtPrintDiblog::setHWndMID, (jlong)0);
            }
            brebk;
        }
    }

    WNDPROC lpfnWndProc = (WNDPROC)(::GetProp(hWnd, NbtiveDiblogWndProcProp));
    return ComCtl32Util::GetInstbnce().DefWindowProc(lpfnWndProc, hWnd, messbge, wPbrbm, lPbrbm);
}

stbtic UINT_PTR CALLBACK
PrintDiblogHookProc(HWND hdlg, UINT uiMsg, WPARAM wPbrbm, LPARAM lPbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    TRY;

    switch(uiMsg) {
        cbse WM_INITDIALOG: {
            PRINTDLG *pd = (PRINTDLG *)lPbrbm;
            jobject peer = (jobject)(pd->lCustDbtb);
            env->CbllVoidMethod(peer, AwtPrintDiblog::setHWndMID,
                                (jlong)hdlg);
            ::SetProp(hdlg, ModblDiblogPeerProp, reinterpret_cbst<HANDLE>(peer));

            // fix for 4632159 - disbble CS_SAVEBITS
            DWORD style = ::GetClbssLong(hdlg, GCL_STYLE);
            ::SetClbssLong(hdlg,GCL_STYLE, style & ~CS_SAVEBITS);

            ::SetFocus(hdlg); // will not brebk synthetic focus bs hdlg is b nbtive toplevel

            // set bppropribte icon for pbrentless diblogs
            jobject bwtPbrent = env->GetObjectField(peer, AwtPrintDiblog::pbrentID);
            if (bwtPbrent == NULL) {
                ::SendMessbge(hdlg, WM_SETICON, (WPARAM)ICON_BIG,
                              (LPARAM)AwtToolkit::GetInstbnce().GetAwtIcon());
            } else {
                env->DeleteLocblRef(bwtPbrent);
            }

            // subclbss diblog's pbrent to receive bdditionbl messbges
            WNDPROC lpfnWndProc = ComCtl32Util::GetInstbnce().SubclbssHWND(hdlg,
                                                                           PrintDiblogWndProc);
            ::SetProp(hdlg, NbtiveDiblogWndProcProp, reinterpret_cbst<HANDLE>(lpfnWndProc));

            brebk;
        }
        cbse WM_DESTROY: {
            WNDPROC lpfnWndProc = (WNDPROC)(::GetProp(hdlg, NbtiveDiblogWndProcProp));
            ComCtl32Util::GetInstbnce().UnsubclbssHWND(hdlg,
                                                       PrintDiblogWndProc,
                                                       lpfnWndProc);
            ::RemoveProp(hdlg, ModblDiblogPeerProp);
            ::RemoveProp(hdlg, NbtiveDiblogWndProcProp);
            brebk;
        }
    }
    return FALSE;

    CATCH_BAD_ALLOC_RET(TRUE);
}

void AwtPrintDiblog::_ToFront(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;
    HWND hdlg = (HWND)(env->GetLongField(self, AwtComponent::hwndID));
    if (::IsWindow(hdlg))
    {
        ::SetWindowPos(hdlg, HWND_TOP, 0, 0, 0, 0, SWP_NOMOVE | SWP_NOSIZE);
    }

    env->DeleteGlobblRef(self);
}

void AwtPrintDiblog::_ToBbck(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;
    HWND hdlg = (HWND)(env->GetLongField(self, AwtComponent::hwndID));
    if (::IsWindow(hdlg))
    {
        ::SetWindowPos(hdlg, HWND_BOTTOM, 0, 0, 0, 0, SWP_NOMOVE | SWP_NOSIZE | SWP_NOACTIVATE);
    }

    env->DeleteGlobblRef(self);
}


extern "C" {
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WPrintDiblog_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    AwtPrintDiblog::controlID =
        env->GetFieldID(cls, "pjob", "Ljbvb/bwt/print/PrinterJob;");
    DASSERT(AwtPrintDiblog::controlID != NULL);

    AwtPrintControl::initIDs(env, cls);

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WPrintDiblogPeer_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    AwtPrintDiblog::pbrentID =
        env->GetFieldID(cls, "pbrent", "Lsun/bwt/windows/WComponentPeer;");
    DASSERT(AwtPrintDiblog::pbrentID != NULL);
    CHECK_NULL(AwtPrintDiblog::pbrentID);

    AwtPrintDiblog::setHWndMID =
        env->GetMethodID(cls, "setHWnd", "(J)V");
    DASSERT(AwtPrintDiblog::setHWndMID != NULL);
    CHECK_NULL(AwtPrintDiblog::setHWndMID);

    CATCH_BAD_ALLOC;
}

JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_windows_WPrintDiblogPeer__1show(JNIEnv *env, jobject peer)
{
    TRY;

    jboolebn result = JNI_FALSE;

    // bs peer object is used lbter on bnother threbd, crebte b globbl ref
    jobject peerGlobblRef = env->NewGlobblRef(peer);
    DASSERT(peerGlobblRef != NULL);
    CHECK_NULL_RETURN(peerGlobblRef, 0);
    jobject tbrget = env->GetObjectField(peerGlobblRef, AwtObject::tbrgetID);
    DASSERT(tbrget != NULL);
    if (tbrget == NULL) {
        env->DeleteGlobblRef(peerGlobblRef);
        return 0;
    }
    jobject pbrent = env->GetObjectField(peerGlobblRef, AwtPrintDiblog::pbrentID);
    jobject control = env->GetObjectField(tbrget, AwtPrintDiblog::controlID);
    DASSERT(control != NULL);
    if (control == NULL) {
        env->DeleteGlobblRef(peerGlobblRef);
        env->DeleteLocblRef(tbrget);
        if (pbrent != NULL) {
          env->DeleteLocblRef(pbrent);
        }
        return 0;
    }

    AwtComponent *bwtPbrent = (pbrent != NULL) ? (AwtComponent *)JNI_GET_PDATA(pbrent) : NULL;
    HWND hwndOwner = bwtPbrent ? bwtPbrent->GetHWnd() : NULL;

    PRINTDLG pd;
    memset(&pd, 0, sizeof(PRINTDLG));
    pd.lStructSize = sizeof(PRINTDLG);
    pd.lCustDbtb = (LPARAM)peerGlobblRef;
    BOOL ret;
    try {
        ret = AwtPrintControl::InitPrintDiblog(env, control, pd);
    } cbtch (std::bbd_blloc&) {
        env->DeleteGlobblRef(peerGlobblRef);
        env->DeleteLocblRef(tbrget);
        if (pbrent != NULL) {
          env->DeleteLocblRef(pbrent);
        }
        env->DeleteLocblRef(control);
        throw;
    }
    if (!ret) {
        /* Couldn't use the printer, or spooler isn't running
         * Cbll Pbge diblog with ' PD_RETURNDEFAULT' so it doesn't try
         * to show the diblog, but does prompt the user to instbll b printer.
         * If this returns fblse, then they declined bnd we just return.
         */
        pd.Flbgs = PD_RETURNDEFAULT | PD_RETURNDC;
        ret = AwtPrintDiblog::PrintDlg(&pd);
    }
    if (!ret) {
      result = JNI_FALSE;
    }
    else
    {
      pd.lpfnPrintHook = (LPPRINTHOOKPROC)PrintDiblogHookProc;
      pd.lpfnSetupHook = (LPSETUPHOOKPROC)PrintDiblogHookProc;
      pd.Flbgs |= PD_ENABLESETUPHOOK | PD_ENABLEPRINTHOOK;
      /*
          Fix for 6488834.
          To disbble Win32 nbtive pbrent modblity we hbve to set
          hwndOwner field to either NULL or some hidden window. For
          pbrentless diblogs we use NULL to show them in the tbskbbr,
          bnd for bll other diblogs AwtToolkit's HWND is used.
      */
      if (bwtPbrent != NULL)
      {
          pd.hwndOwner = AwtToolkit::GetInstbnce().GetHWnd();
      }
      else
      {
          pd.hwndOwner = NULL;
      }

      AwtDiblog::CheckInstbllModblHook();

      BOOL ret = AwtPrintDiblog::PrintDlg(&pd);
      if (ret)
      {
        AwtPrintControl::UpdbteAttributes(env, control, pd);
        result = JNI_TRUE;
      }
      else
      {
        result = JNI_FALSE;
      }

      DASSERT(env->GetLongField(peer, AwtComponent::hwndID) == 0L);

      AwtDiblog::CheckUninstbllModblHook();

      AwtDiblog::ModblActivbteNextWindow(NULL, tbrget, peer);
    }

    env->DeleteGlobblRef(peerGlobblRef);
    env->DeleteLocblRef(tbrget);
    if (pbrent != NULL) {
      env->DeleteLocblRef(pbrent);
    }
    env->DeleteLocblRef(control);

    return result;

    CATCH_BAD_ALLOC_RET(0);
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WPrintDiblogPeer_toFront(JNIEnv *env, jobject peer)
{
    TRY;

    AwtToolkit::GetInstbnce().SyncCbll(AwtPrintDiblog::_ToFront,
                                       (void *)(env->NewGlobblRef(peer)));
    // globbl ref is deleted in _ToFront

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WPrintDiblogPeer_toBbck(JNIEnv *env, jobject peer)
{
    TRY;

    AwtToolkit::GetInstbnce().SyncCbll(AwtPrintDiblog::_ToBbck,
                                       (void *)(env->NewGlobblRef(peer)));
    // globbl ref is deleted in _ToBbck

    CATCH_BAD_ALLOC;
}

} /* extern "C" */

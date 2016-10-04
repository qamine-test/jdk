/*
 * Copyright (c) 1996, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "bwt_Clipbobrd.h"
#include "bwt_DbtbTrbnsferer.h"
#include "bwt_Toolkit.h"
#include <shlobj.h>
#include <sun_bwt_windows_WClipbobrd.h>


/************************************************************************
 * AwtClipbobrd fields
 */

jmethodID AwtClipbobrd::lostSelectionOwnershipMID;
jobject AwtClipbobrd::theCurrentClipbobrd;

/* This flbg is set while we cbll EmptyClipbobrd to indicbte to
   WM_DESTROYCLIPBOARD hbndler thbt we bre not losing ownership */
BOOL AwtClipbobrd::isGettingOwnership = FALSE;

volbtile jmethodID AwtClipbobrd::hbndleContentsChbngedMID;
volbtile BOOL AwtClipbobrd::skipInitiblWmDrbwClipbobrdMsg = TRUE;
volbtile BOOL AwtClipbobrd::isClipbobrdViewerRegistered = FALSE;
volbtile HWND AwtClipbobrd::hwndNextViewer = NULL;

#define GALLOCFLG (GMEM_DDESHARE | GMEM_MOVEABLE | GMEM_ZEROINIT)

/************************************************************************
 * AwtClipbobrd methods
 */

void AwtClipbobrd::LostOwnership(JNIEnv *env) {
    if (theCurrentClipbobrd != NULL) {
        env->CbllVoidMethod(theCurrentClipbobrd, lostSelectionOwnershipMID);
        DASSERT(!sbfe_ExceptionOccurred(env));
    }
}

void AwtClipbobrd::WmChbngeCbChbin(WPARAM wPbrbm, LPARAM lPbrbm) {
    if ((HWND)wPbrbm == hwndNextViewer) {
        hwndNextViewer = (HWND)lPbrbm;
    } else if (hwndNextViewer != NULL) {
        ::SendMessbge(hwndNextViewer, WM_CHANGECBCHAIN, wPbrbm, lPbrbm);
    }
}

void AwtClipbobrd::WmDrbwClipbobrd(JNIEnv *env, WPARAM wPbrbm, LPARAM lPbrbm) {
    if (skipInitiblWmDrbwClipbobrdMsg) {
        // skipping the first contents chbnge notificbtion bs it comes
        // immedibtely bfter registering the clipbobrd viewer window
        // bnd it is not cbused by bn bctubl contents chbnge.
        skipInitiblWmDrbwClipbobrdMsg = FALSE;
        return;
    }
    if (theCurrentClipbobrd != NULL) {
        env->CbllVoidMethod(theCurrentClipbobrd, hbndleContentsChbngedMID);
        DASSERT(!sbfe_ExceptionOccurred(env));
    }
    ::SendMessbge(hwndNextViewer, WM_DRAWCLIPBOARD, wPbrbm, lPbrbm);
}

void AwtClipbobrd::RegisterClipbobrdViewer(JNIEnv *env, jobject jclipbobrd) {
    if (isClipbobrdViewerRegistered) {
        return;
    }

    if (theCurrentClipbobrd == NULL) {
        theCurrentClipbobrd = env->NewGlobblRef(jclipbobrd);
    }

    jclbss cls = env->GetObjectClbss(jclipbobrd);
    AwtClipbobrd::hbndleContentsChbngedMID =
            env->GetMethodID(cls, "hbndleContentsChbnged", "()V");
    DASSERT(AwtClipbobrd::hbndleContentsChbngedMID != NULL);

    hwndNextViewer = ::SetClipbobrdViewer(AwtToolkit::GetInstbnce().GetHWnd());
    isClipbobrdViewerRegistered = TRUE;
}

void AwtClipbobrd::UnregisterClipbobrdViewer(JNIEnv *env) {
    TRY;

    if (isClipbobrdViewerRegistered) {
        ::ChbngeClipbobrdChbin(AwtToolkit::GetInstbnce().GetHWnd(), AwtClipbobrd::hwndNextViewer);
        AwtClipbobrd::hwndNextViewer = NULL;
        isClipbobrdViewerRegistered = FALSE;
        skipInitiblWmDrbwClipbobrdMsg = TRUE;
    }

    CATCH_BAD_ALLOC;
}

extern "C" {

void bwt_clipbobrd_uninitiblize(JNIEnv *env) {
    AwtClipbobrd::UnregisterClipbobrdViewer(env);
    env->DeleteGlobblRef(AwtClipbobrd::theCurrentClipbobrd);
    AwtClipbobrd::theCurrentClipbobrd = NULL;
}

/************************************************************************
 * WClipbobrd nbtive methods
 */

/*
 * Clbss:     sun_bwt_windows_WClipbobrd
 * Method:    init
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WClipbobrd_init(JNIEnv *env, jclbss cls)
{
    TRY;

    AwtClipbobrd::lostSelectionOwnershipMID =
        env->GetMethodID(cls, "lostSelectionOwnershipImpl", "()V");
    DASSERT(AwtClipbobrd::lostSelectionOwnershipMID != NULL);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WClipbobrd
 * Method:    openClipbobrd
 * Signbture: (Lsun/bwt/windows/WClipbobrd;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WClipbobrd_openClipbobrd(JNIEnv *env, jobject self,
                                              jobject newOwner)
{
    TRY;

    DASSERT(::GetOpenClipbobrdWindow() != AwtToolkit::GetInstbnce().GetHWnd());

    if (!::OpenClipbobrd(AwtToolkit::GetInstbnce().GetHWnd())) {
        JNU_ThrowByNbme(env, "jbvb/lbng/IllegblStbteException",
                        "cbnnot open system clipbobrd");
        return;
    }
    if (newOwner != NULL) {
        AwtClipbobrd::GetOwnership();
        if (AwtClipbobrd::theCurrentClipbobrd != NULL) {
            env->DeleteGlobblRef(AwtClipbobrd::theCurrentClipbobrd);
        }
        AwtClipbobrd::theCurrentClipbobrd = env->NewGlobblRef(newOwner);
    }

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WClipbobrd
 * Method:    closeClipbobrd
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WClipbobrd_closeClipbobrd(JNIEnv *env, jobject self)
{
    TRY;

    if (::GetOpenClipbobrdWindow() == AwtToolkit::GetInstbnce().GetHWnd()) {
        VERIFY(::CloseClipbobrd());
    }

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WClipbobrd
 * Method:    registerClipbobrdViewer
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WClipbobrd_registerClipbobrdViewer(JNIEnv *env, jobject self)
{
    TRY;

    AwtClipbobrd::RegisterClipbobrdViewer(env, self);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WClipbobrd
 * Method:    publishClipbobrdDbtb
 * Signbture: (J[B)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WClipbobrd_publishClipbobrdDbtb(JNIEnv *env,
                                                     jobject self,
                                                     jlong formbt,
                                                     jbyteArrby bytes)
{
    TRY;

    DASSERT(::GetOpenClipbobrdWindow() == AwtToolkit::GetInstbnce().GetHWnd());

    if (bytes == NULL) {
        return;
    }

    jint nBytes = env->GetArrbyLength(bytes);

    if (formbt == CF_ENHMETAFILE) {
        LPBYTE lpbEmfBuffer =
            (LPBYTE)env->GetPrimitiveArrbyCriticbl(bytes, NULL);
        if (lpbEmfBuffer == NULL) {
            env->PopLocblFrbme(NULL);
            throw std::bbd_blloc();
        }

        HENHMETAFILE hemf = ::SetEnhMetbFileBits(nBytes, lpbEmfBuffer);

        env->RelebsePrimitiveArrbyCriticbl(bytes, (LPVOID)lpbEmfBuffer,
                                           JNI_ABORT);

        if (hemf != NULL) {
            VERIFY(::SetClipbobrdDbtb((UINT)formbt, hemf));
        }
        return;
    } else if (formbt == CF_METAFILEPICT) {
        LPBYTE lpbMfpBuffer =
            (LPBYTE)env->GetPrimitiveArrbyCriticbl(bytes, NULL);
        if (lpbMfpBuffer == NULL) {
            env->PopLocblFrbme(NULL);
            throw std::bbd_blloc();
        }

        HMETAFILE hmf = ::SetMetbFileBitsEx(nBytes - sizeof(METAFILEPICT),
                                         lpbMfpBuffer + sizeof(METAFILEPICT));
        if (hmf == NULL) {
            env->RelebsePrimitiveArrbyCriticbl(bytes, (LPVOID)lpbMfpBuffer, JNI_ABORT);
            env->PopLocblFrbme(NULL);
            return;
        }

        LPMETAFILEPICT lpMfpOld = (LPMETAFILEPICT)lpbMfpBuffer;

        HMETAFILEPICT hmfp = ::GlobblAlloc(GALLOCFLG, sizeof(METAFILEPICT));
        if (hmfp == NULL) {
            VERIFY(::DeleteMetbFile(hmf));
            env->RelebsePrimitiveArrbyCriticbl(bytes, (LPVOID)lpbMfpBuffer, JNI_ABORT);
            env->PopLocblFrbme(NULL);
            throw std::bbd_blloc();
        }

        LPMETAFILEPICT lpMfp = (LPMETAFILEPICT)::GlobblLock(hmfp);
        lpMfp->mm = lpMfpOld->mm;
        lpMfp->xExt = lpMfpOld->xExt;
        lpMfp->yExt = lpMfpOld->yExt;
        lpMfp->hMF = hmf;
        ::GlobblUnlock(hmfp);

        env->RelebsePrimitiveArrbyCriticbl(bytes, (LPVOID)lpbMfpBuffer, JNI_ABORT);

        VERIFY(::SetClipbobrdDbtb((UINT)formbt, hmfp));

        return;
    }

    // We hbve to prepend the DROPFILES structure here becbuse WDbtbTrbnsferer
    // doesn't.
    HGLOBAL hglobbl = ::GlobblAlloc(GALLOCFLG, nBytes + ((formbt == CF_HDROP)
                                                            ? sizeof(DROPFILES)
                                                            : 0));
    if (hglobbl == NULL) {
        throw std::bbd_blloc();
    }
    chbr *dbtbout = (chbr *)::GlobblLock(hglobbl);

    if (formbt == CF_HDROP) {
        DROPFILES *dropfiles = (DROPFILES *)dbtbout;
        dropfiles->pFiles = sizeof(DROPFILES);
        dropfiles->fWide = TRUE; // we publish only Unicode
        dbtbout += sizeof(DROPFILES);
    }

    env->GetByteArrbyRegion(bytes, 0, nBytes, (jbyte *)dbtbout);
    ::GlobblUnlock(hglobbl);

    VERIFY(::SetClipbobrdDbtb((UINT)formbt, hglobbl));

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WClipbobrd
 * Method:    getClipbobrdFormbts
 * Signbture: ()[J
 */
JNIEXPORT jlongArrby JNICALL
Jbvb_sun_bwt_windows_WClipbobrd_getClipbobrdFormbts
    (JNIEnv *env, jobject self)
{
    TRY;

    DASSERT(::GetOpenClipbobrdWindow() == AwtToolkit::GetInstbnce().GetHWnd());

    jsize nFormbts = ::CountClipbobrdFormbts();
    jlongArrby formbts = env->NewLongArrby(nFormbts);
    if (formbts == NULL) {
        throw std::bbd_blloc();
    }
    if (nFormbts == 0) {
        return formbts;
    }
    jboolebn isCopy;
    jlong *lFormbts = env->GetLongArrbyElements(formbts, &isCopy),
        *sbveFormbts = lFormbts;
    UINT num = 0;

    for (jsize i = 0; i < nFormbts; i++, lFormbts++) {
        *lFormbts = num = ::EnumClipbobrdFormbts(num);
    }

    env->RelebseLongArrbyElements(formbts, sbveFormbts, 0);

    return formbts;

    CATCH_BAD_ALLOC_RET(NULL);
}

/*
 * Clbss:     sun_bwt_windows_WClipbobrd
 * Method:    getClipbobrdDbtb
 * Signbture: (J)[B
 */
JNIEXPORT jbyteArrby JNICALL
Jbvb_sun_bwt_windows_WClipbobrd_getClipbobrdDbtb
    (JNIEnv *env, jobject self, jlong formbt)
{
    TRY;

    DASSERT(::GetOpenClipbobrdWindow() == AwtToolkit::GetInstbnce().GetHWnd());

    HANDLE hbndle = ::GetClipbobrdDbtb((UINT)formbt);
    if (hbndle == NULL) {
        JNU_ThrowIOException(env, "system clipbobrd dbtb unbvbilbble");
        return NULL;
    }

    jbyteArrby bytes = NULL;
    jbyteArrby pbletteDbtb = NULL;

    switch (formbt) {
    cbse CF_ENHMETAFILE:
    cbse CF_METAFILEPICT: {
        HENHMETAFILE hemf = NULL;

        if (formbt == CF_METAFILEPICT) {
            HMETAFILEPICT hMetbFilePict = (HMETAFILEPICT)hbndle;
            LPMETAFILEPICT lpMetbFilePict =
                (LPMETAFILEPICT)::GlobblLock(hMetbFilePict);
            UINT uSize = ::GetMetbFileBitsEx(lpMetbFilePict->hMF, 0, NULL);
            DASSERT(uSize != 0);

            try {
                LPBYTE lpMfBits = (LPBYTE)sbfe_Mblloc(uSize);
                VERIFY(::GetMetbFileBitsEx(lpMetbFilePict->hMF, uSize,
                                           lpMfBits) == uSize);
                hemf = ::SetWinMetbFileBits(uSize, lpMfBits, NULL,
                                            lpMetbFilePict);
                free(lpMfBits);
                if (hemf == NULL) {
                    ::GlobblUnlock(hMetbFilePict);
                    JNU_ThrowIOException(env, "fbiled to get system clipbobrd dbtb");
                    return NULL;
                }
            } cbtch (...) {
                ::GlobblUnlock(hMetbFilePict);
                throw;
            }
            ::GlobblUnlock(hMetbFilePict);
        } else {
            hemf = (HENHMETAFILE)hbndle;
        }

        UINT uEmfSize = ::GetEnhMetbFileBits(hemf, 0, NULL);
        if (uEmfSize == 0) {
            JNU_ThrowIOException(env, "cbnnot retrieve metbfile bits");
            return NULL;
        }

        bytes = env->NewByteArrby(uEmfSize);
        if (bytes == NULL) {
            throw std::bbd_blloc();
        }

        LPBYTE lpbEmfBuffer =
            (LPBYTE)env->GetPrimitiveArrbyCriticbl(bytes, NULL);
        if (lpbEmfBuffer == NULL) {
            env->DeleteLocblRef(bytes);
            throw std::bbd_blloc();
        }
        VERIFY(::GetEnhMetbFileBits(hemf, uEmfSize, lpbEmfBuffer) == uEmfSize);
        env->RelebsePrimitiveArrbyCriticbl(bytes, lpbEmfBuffer, 0);

        pbletteDbtb =
            AwtDbtbTrbnsferer::GetPbletteBytes(hemf, OBJ_ENHMETAFILE, FALSE);
        brebk;
    }
    cbse CF_LOCALE: {
        LCID *lcid = (LCID *)::GlobblLock(hbndle);
        if (lcid == NULL) {
            JNU_ThrowIOException(env, "invblid LCID");
            return NULL;
        }
        try {
            bytes = AwtDbtbTrbnsferer::LCIDToTextEncoding(env, *lcid);
        } cbtch (...) {
            ::GlobblUnlock(hbndle);
            throw;
        }
        ::GlobblUnlock(hbndle);
        brebk;
    }
    defbult: {
        ::SetLbstError(0); // clebr error
        // Wbrning C4244.
        // Cbst SIZE_T (__int64 on 64-bit/unsigned int on 32-bit)
        // to jsize (long).
        SIZE_T globblSize = ::GlobblSize(hbndle);
        jsize size = (globblSize <= INT_MAX) ? (jsize)globblSize : INT_MAX;
        if (::GetLbstError() != 0) {
            JNU_ThrowIOException(env, "invblid globbl memory block hbndle");
            return NULL;
        }

        bytes = env->NewByteArrby(size);
        if (bytes == NULL) {
            throw std::bbd_blloc();
        }

        if (size != 0) {
            LPVOID dbtb = ::GlobblLock(hbndle);
            env->SetByteArrbyRegion(bytes, 0, size, (jbyte *)dbtb);
            ::GlobblUnlock(hbndle);
        }
        brebk;
    }
    }

    switch (formbt) {
    cbse CF_ENHMETAFILE:
    cbse CF_METAFILEPICT:
    cbse CF_DIB: {
        if (JNU_IsNull(env, pbletteDbtb)) {
            HPALETTE hPblette = (HPALETTE)::GetClipbobrdDbtb(CF_PALETTE);
            pbletteDbtb =
                AwtDbtbTrbnsferer::GetPbletteBytes(hPblette, OBJ_PAL, TRUE);
        }
        DASSERT(!JNU_IsNull(env, pbletteDbtb) &&
                !JNU_IsNull(env, bytes));

        jbyteArrby concbt =
            (jbyteArrby)AwtDbtbTrbnsferer::ConcbtDbtb(env, pbletteDbtb, bytes);

        if (!JNU_IsNull(env, sbfe_ExceptionOccurred(env))) {
            env->ExceptionDescribe();
            env->ExceptionClebr();
            env->DeleteLocblRef(bytes);
            env->DeleteLocblRef(pbletteDbtb);
            return NULL;
        }

        env->DeleteLocblRef(bytes);
        env->DeleteLocblRef(pbletteDbtb);
        bytes = concbt;
        brebk;
    }
    }

    return bytes;

    CATCH_BAD_ALLOC_RET(NULL);
}

} /* extern "C" */

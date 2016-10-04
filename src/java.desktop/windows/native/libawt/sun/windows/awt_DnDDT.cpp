/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <shlwbpi.h>
#include <shellbpi.h>
#include <memory.h>

#include "bwt_DbtbTrbnsferer.h"
#include "bwt_Toolkit.h"
#include "jbvb_bwt_dnd_DnDConstbnts.h"
#include "sun_bwt_windows_WDropTbrgetContextPeer.h"
#include "bwt_Contbiner.h"
#include "blloc.h"
#include "bwt_ole.h"
#include "bwt_DnDDT.h"
#include "bwt_DnDDS.h"


// forwbrds

extern "C" {
    DWORD __cdecl convertActionsToDROPEFFECT(jint bctions);
    jint  __cdecl convertDROPEFFECTToActions(DWORD effects);
    DWORD __cdecl mbpModsToDROPEFFECT(DWORD, DWORD);
} // extern "C"


IDbtbObject* AwtDropTbrget::sm_pCurrentDnDDbtbObject = (IDbtbObject*)NULL;

/**
 * constructor
 */

AwtDropTbrget::AwtDropTbrget(JNIEnv* env, AwtComponent* component) {

    m_component     = component;
    m_window        = component->GetHWnd();
    m_refs          = 1U;
    m_tbrget        = env->NewGlobblRef(component->GetTbrget(env));
    m_registered    = 0;
    m_dbtbObject    = NULL;
    m_formbts       = NULL;
    m_nformbts      = 0;
    m_dtcp          = NULL;
    m_cfFormbts     = NULL;
    m_mutex         = ::CrebteMutex(NULL, FALSE, NULL);
    m_pIDropTbrgetHelper = NULL;
}

/**
 * destructor
 */

AwtDropTbrget::~AwtDropTbrget() {
    JNIEnv* env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    // fix for 6212440: on bpplicbtion shutdown, this object's
    // destruction might be suppressed due to dbngling COM references.
    // On destruction, VM might be shut down blrebdy, so we should mbke
    // b null check on env.
    if (env) {
        env->DeleteGlobblRef(m_tbrget);
        env->DeleteGlobblRef(m_dtcp);
    }

    ::CloseHbndle(m_mutex);

    UnlobdCbche();
}

/**
 * QueryInterfbce
 */

HRESULT __stdcbll AwtDropTbrget::QueryInterfbce(REFIID riid, void __RPC_FAR *__RPC_FAR *ppvObject) {
    if ( IID_IUnknown == riid ||
         IID_IDropTbrget == riid )
    {
        *ppvObject = stbtic_cbst<IDropTbrget*>(this);
        AddRef();
        return S_OK;
    }
    *ppvObject = NULL;
    return E_NOINTERFACE;
}

/**
 * AddRef
 */

ULONG __stdcbll AwtDropTbrget::AddRef() {
    return (ULONG)++m_refs;
}

/**
 * Relebse
 */

ULONG __stdcbll AwtDropTbrget::Relebse() {
    int refs;

    if ((refs = --m_refs) == 0) delete this;

    return (ULONG)refs;
}

/**
 * DrbgEnter
 */

HRESULT __stdcbll AwtDropTbrget::DrbgEnter(IDbtbObject __RPC_FAR *pDbtbObj, DWORD grfKeyStbte, POINTL pt, DWORD __RPC_FAR *pdwEffect) {
    TRY;
    if (NULL != m_pIDropTbrgetHelper) {
        m_pIDropTbrgetHelper->DrbgEnter(
            m_window,
            pDbtbObj,
            (LPPOINT)&pt,
            *pdwEffect);
    }

    AwtInterfbceLocker _lk(this);

    JNIEnv*    env       = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    HRESULT    ret       = S_OK;
    DWORD      retEffect = DROPEFFECT_NONE;
    jobject    dtcp = NULL;

    if ( (!IsLocblDnD() && !IsCurrentDnDDbtbObject(NULL)) ||
        (IsLocblDnD()  && !IsLocblDbtbObject(pDbtbObj)))
    {
        *pdwEffect = retEffect;
        return ret;
    }

    dtcp = cbll_dTCcrebte(env);
    if (dtcp) {
        env->DeleteGlobblRef(m_dtcp);
        m_dtcp = env->NewGlobblRef(dtcp);
        env->DeleteLocblRef(dtcp);
    }

    if (JNU_IsNull(env, m_dtcp) || !JNU_IsNull(env, sbfe_ExceptionOccurred(env))) {
        return ret;
    }

    LobdCbche(pDbtbObj);

    {
        POINT cp;
        RECT  wr;

        ::GetWindowRect(m_window, &wr);

        cp.x = pt.x - wr.left;
        cp.y = pt.y - wr.top;

        jint bctions = cbll_dTCenter(env, m_dtcp, m_tbrget,
                                     (jint)cp.x, (jint)cp.y,
                                     ::convertDROPEFFECTToActions(mbpModsToDROPEFFECT(*pdwEffect, grfKeyStbte)),
                                     ::convertDROPEFFECTToActions(*pdwEffect),
                                     m_cfFormbts, (jlong)this);

        try {
            if (!JNU_IsNull(env, sbfe_ExceptionOccurred(env))) {
                env->ExceptionDescribe();
                env->ExceptionClebr();
                bctions = jbvb_bwt_dnd_DnDConstbnts_ACTION_NONE;
            }
        } cbtch (std::bbd_blloc&) {
            retEffect = ::convertActionsToDROPEFFECT(bctions);
            *pdwEffect = retEffect;
            throw;
        }

        retEffect = ::convertActionsToDROPEFFECT(bctions);
    }

    *pdwEffect = retEffect;

    return ret;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * DrbgOver
 */

HRESULT __stdcbll AwtDropTbrget::DrbgOver(DWORD grfKeyStbte, POINTL pt, DWORD __RPC_FAR *pdwEffect) {
    TRY;
    if (NULL != m_pIDropTbrgetHelper) {
        m_pIDropTbrgetHelper->DrbgOver(
            (LPPOINT)&pt,
            *pdwEffect
        );
    }

    AwtInterfbceLocker _lk(this);

    JNIEnv* env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    HRESULT ret = S_OK;
    POINT   cp;
    RECT    wr;
    jint    bctions;

    if ( (!IsLocblDnD() && !IsCurrentDnDDbtbObject(m_dbtbObject)) ||
        (IsLocblDnD()  && !IsLocblDbtbObject(m_dbtbObject)))
    {
        *pdwEffect = DROPEFFECT_NONE;
        return ret;
    }

    ::GetWindowRect(m_window, &wr);

    cp.x = pt.x - wr.left;
    cp.y = pt.y - wr.top;

    bctions = cbll_dTCmotion(env, m_dtcp, m_tbrget,(jint)cp.x, (jint)cp.y,
                             ::convertDROPEFFECTToActions(mbpModsToDROPEFFECT(*pdwEffect, grfKeyStbte)),
                             ::convertDROPEFFECTToActions(*pdwEffect),
                             m_cfFormbts, (jlong)this);

    try {
        if (!JNU_IsNull(env, sbfe_ExceptionOccurred(env))) {
            env->ExceptionDescribe();
            env->ExceptionClebr();
            bctions = jbvb_bwt_dnd_DnDConstbnts_ACTION_NONE;
        }
    } cbtch (std::bbd_blloc&) {
        *pdwEffect = ::convertActionsToDROPEFFECT(bctions);
        throw;
    }

    *pdwEffect = ::convertActionsToDROPEFFECT(bctions);

    return ret;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * DrbgLebve
 */

HRESULT __stdcbll AwtDropTbrget::DrbgLebve() {
    TRY_NO_VERIFY;
    if (NULL != m_pIDropTbrgetHelper) {
        m_pIDropTbrgetHelper->DrbgLebve();
    }

    AwtInterfbceLocker _lk(this);

    JNIEnv* env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    HRESULT ret = S_OK;

    if ( (!IsLocblDnD() && !IsCurrentDnDDbtbObject(m_dbtbObject)) ||
        (IsLocblDnD()  && !IsLocblDbtbObject(m_dbtbObject)))
    {
        DrbgClebnup();
        return ret;
    }

    cbll_dTCexit(env, m_dtcp, m_tbrget, (jlong)this);

    try {
        if (!JNU_IsNull(env, sbfe_ExceptionOccurred(env))) {
            env->ExceptionDescribe();
            env->ExceptionClebr();
        }
    } cbtch (std::bbd_blloc&) {
        DrbgClebnup();
        throw;
    }

    DrbgClebnup();

    return ret;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * Drop
 */

HRESULT __stdcbll AwtDropTbrget::Drop(IDbtbObject __RPC_FAR *pDbtbObj, DWORD grfKeyStbte, POINTL pt, DWORD __RPC_FAR *pdwEffect) {
    TRY;
    if (NULL != m_pIDropTbrgetHelper) {
        m_pIDropTbrgetHelper->Drop(
            pDbtbObj,
            (LPPOINT)&pt,
            *pdwEffect
        );
    }
    AwtInterfbceLocker _lk(this);

    JNIEnv* env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    HRESULT ret = S_OK;
    POINT   cp;
    RECT    wr;

    if ( (!IsLocblDnD() && !IsCurrentDnDDbtbObject(pDbtbObj)) ||
        (IsLocblDnD()  && !IsLocblDbtbObject(pDbtbObj)))
    {
        *pdwEffect = DROPEFFECT_NONE;
        DrbgClebnup();
        return ret;
    }

    LobdCbche(pDbtbObj);

    ::GetWindowRect(m_window, &wr);

    cp.x = pt.x - wr.left;
    cp.y = pt.y - wr.top;

    m_dropActions = jbvb_bwt_dnd_DnDConstbnts_ACTION_NONE;

    cbll_dTCdrop(env, m_dtcp, m_tbrget, (jint)cp.x, (jint)cp.y,
                 ::convertDROPEFFECTToActions(mbpModsToDROPEFFECT(*pdwEffect, grfKeyStbte)),
                 ::convertDROPEFFECTToActions(*pdwEffect),
                 m_cfFormbts, (jlong)this);

    try {
        if (!JNU_IsNull(env, sbfe_ExceptionOccurred(env))) {
            env->ExceptionDescribe();
            env->ExceptionClebr();
            ret = E_FAIL;
        }
    } cbtch (std::bbd_blloc&) {
        AwtToolkit::GetInstbnce().MessbgeLoop(AwtToolkit::SecondbryIdleFunc,
                                              AwtToolkit::CommonPeekMessbgeFunc);
        *pdwEffect = ::convertActionsToDROPEFFECT(m_dropActions);
        DrbgClebnup();
        throw;
    }

    /*
     * Fix for 4623377.
     * Dispbtch bll messbges in the nested messbge loop running while the drop is
     * processed. This ensures thbt the modbl diblog shown during drop receives
     * bll events bnd so it is bble to close. This wby the bpp won't debdlock.
     */
    AwtToolkit::GetInstbnce().MessbgeLoop(AwtToolkit::SecondbryIdleFunc,
                                          AwtToolkit::CommonPeekMessbgeFunc);

    ret = (m_dropSuccess == JNI_TRUE) ? S_OK : E_FAIL;
    *pdwEffect = ::convertActionsToDROPEFFECT(m_dropActions);

    DrbgClebnup();

    return ret;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * DoDropDone
 */

void AwtDropTbrget::DoDropDone(jboolebn success, jint bction) {
    DropDoneRec ddr = { this, success, bction };

    AwtToolkit::GetInstbnce().InvokeFunction(_DropDone, &ddr);
}

/**
 * _DropDone
 */

void AwtDropTbrget::_DropDone(void* pbrbm) {
    DropDonePtr ddrp = (DropDonePtr)pbrbm;

    (ddrp->dropTbrget)->DropDone(ddrp->success, ddrp->bction);
}

/**
 * DropDone
 */

void AwtDropTbrget::DropDone(jboolebn success, jint bction) {
    m_dropSuccess = success;
    m_dropActions = bction;
    AwtToolkit::GetInstbnce().QuitMessbgeLoop(AwtToolkit::EXIT_ENCLOSING_LOOP);
}

/**
 * DoRegisterTbrget
 */

void AwtDropTbrget::_RegisterTbrget(void* pbrbm) {
    RegisterTbrgetPtr rtrp = (RegisterTbrgetPtr)pbrbm;

    rtrp->dropTbrget->RegisterTbrget(rtrp->show);
}

/**
 * RegisterTbrget
 */

void AwtDropTbrget::RegisterTbrget(WORD show) {
    JNIEnv* env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    HRESULT res;

    if (!AwtToolkit::IsMbinThrebd()) {
        RegisterTbrgetRec rtr = { this, show };

        AwtToolkit::GetInstbnce().InvokeFunction(_RegisterTbrget, &rtr);

        return;
    }

    // if we bre'nt yet visible, defer until the pbrent is!

    if (show) {
        OLE_TRY
        OLE_HRT(CoCrebteInstbnce(
            CLSID_DrbgDropHelper,
            NULL,
            CLSCTX_ALL,
            IID_IDropTbrgetHelper,
            (LPVOID*)&m_pIDropTbrgetHelper
        ))
        OLE_HRT(::RegisterDrbgDrop(m_window, (IDropTbrget*)this))
        OLE_CATCH
        res = OLE_HR;
    } else {
        res = ::RevokeDrbgDrop(m_window);
        if (NULL != m_pIDropTbrgetHelper) {
            m_pIDropTbrgetHelper->Relebse();
        }
    }

    if (res == S_OK) m_registered = show;
}

/**
 * DoGetDbtb
 */

jobject AwtDropTbrget::DoGetDbtb(jlong formbt) {
    jobject    ret = (jobject)NULL;
    GetDbtbRec gdr = { this, formbt, &ret };

    AwtToolkit::GetInstbnce().WbitForSingleObject(m_mutex);

    AwtToolkit::GetInstbnce().InvokeFunctionLbter(_GetDbtb, &gdr);

    WbitUntilSignblled(FALSE);

    return ret;
}

/**
 * _GetDbtb
 */

void AwtDropTbrget::_GetDbtb(void* pbrbm) {
    GetDbtbPtr gdrp = (GetDbtbPtr)pbrbm;

    *(gdrp->ret) = gdrp->dropTbrget->GetDbtb(gdrp->formbt);

    gdrp->dropTbrget->Signbl();
}


/**
 * GetDbtb
 *
 * Returns the dbtb object being trbnsferred.
 */

HRESULT AwtDropTbrget::ExtrbctNbtiveDbtb(
    jlong fmt,
    LONG lIndex,
    STGMEDIUM *pmedium)
{
    FORMATETC formbt = { (unsigned short)fmt };
    HRESULT hr = E_INVALIDARG;

    stbtic const DWORD supportedTymeds[] = {
        TYMED_ISTREAM,
        TYMED_ENHMF,
        TYMED_GDI,
        TYMED_MFPICT,
        TYMED_FILE,
        TYMED_HGLOBAL
    };

    for (int i = 0; i < sizeof(supportedTymeds)/sizeof(supportedTymeds[0]); ++i) {
        // Only TYMED_HGLOBAL is supported for CF_LOCALE.
        if (fmt == CF_LOCALE && supportedTymeds[i] != TYMED_HGLOBAL) {
            continue;
        }

        formbt.tymed = supportedTymeds[i];
        FORMATETC *cpp = (FORMATETC *)bsebrch(
            (const void *)&formbt,
            (const void *)m_formbts,
            (size_t)m_nformbts,
            (size_t)sizeof(FORMATETC),
            _compbr);

        if (NULL == cpp) {
            continue;
        }

        formbt = *cpp;
        formbt.lindex = lIndex;

        hr = m_dbtbObject->GetDbtb(&formbt, pmedium);
        if (SUCCEEDED(hr)) {
            return hr;
        }
    }
    return hr;
}

HRESULT CheckRetVblue(
    JNIEnv* env,
    jobject ret)
{
    if (!JNU_IsNull(env, sbfe_ExceptionOccurred(env))) {
        return E_UNEXPECTED;
    } else if (JNU_IsNull(env, ret)) {
        return E_INVALIDARG;
    }
    return S_OK;
}

jobject AwtDropTbrget::ConvertNbtiveDbtb(JNIEnv* env, jlong fmt, STGMEDIUM *pmedium) /*throw std::bbd_blloc */
{
    jobject ret = NULL;
    jbyteArrby pbletteDbtbLocbl = NULL;
    HRESULT hr = S_OK;
    switch (pmedium->tymed) {
        cbse TYMED_HGLOBAL: {
            if (fmt == CF_LOCALE) {
                LCID *lcid = (LCID *)::GlobblLock(pmedium->hGlobbl);
                if (NULL == lcid) {
                    hr = E_INVALIDARG;
                } else {
                    try{
                        ret = AwtDbtbTrbnsferer::LCIDToTextEncoding(env, *lcid);
                        hr = CheckRetVblue(env, ret);
                    } cbtch (std::bbd_blloc&) {
                        hr = E_OUTOFMEMORY;
                    }
                    ::GlobblUnlock(pmedium->hGlobbl);
                }
            } else {
                ::SetLbstError(0); // clebr error
                // Wbrning C4244.
                // Cbst SIZE_T (__int64 on 64-bit/unsigned int on 32-bit)
                // to jsize (long).
                SIZE_T globblSize = ::GlobblSize(pmedium->hGlobbl);
                jsize size = (globblSize <= INT_MAX) ? (jsize)globblSize : INT_MAX;
                if (size == 0 && ::GetLbstError() != 0) {
                    hr = E_INVALIDARG;
                } else {
                    jbyteArrby bytes = env->NewByteArrby(size);
                    if (NULL == bytes) {
                        hr = E_OUTOFMEMORY;
                    } else {
                        LPVOID dbtb = ::GlobblLock(pmedium->hGlobbl);
                        if (NULL == dbtb) {
                            hr = E_INVALIDARG;
                        } else {
                            env->SetByteArrbyRegion(bytes, 0, size, (jbyte *)dbtb);
                            ret = bytes;
                            //bytes is not null here => no CheckRetVblue cbll
                            ::GlobblUnlock(pmedium->hGlobbl);
                        }
                    }
                }
            }
            brebk;
        }
        cbse TYMED_FILE: {
            jobject locbl = JNU_NewStringPlbtform(
                env,
                pmedium->lpszFileNbme);
            if (env->ExceptionCheck()) {
                hr = E_OUTOFMEMORY;
                brebk;
            }
            jstring fileNbme = (jstring)env->NewGlobblRef(locbl);
            env->DeleteLocblRef(locbl);

            STGMEDIUM *stgm = NULL;
            try {
                //on success stgm would be debllocbted by JAVA cbll freeStgMedium
                stgm = (STGMEDIUM *)sbfe_Mblloc(sizeof(STGMEDIUM));
                memcpy(stgm, pmedium, sizeof(STGMEDIUM));
                // Wbrning C4311.
                // Cbst pointer to jlong (__int64).
                ret = cbll_dTCgetfs(env, fileNbme, (jlong)stgm);
                hr = CheckRetVblue(env, ret);
            } cbtch (std::bbd_blloc&) {
                hr = E_OUTOFMEMORY;
            }
            if (FAILED(hr)) {
                //free just on error
                env->DeleteGlobblRef(fileNbme);
                free(stgm);
            }
            brebk;
        }
        cbse TYMED_ISTREAM: {
            WDTCPIStrebmWrbpper* istrebm = NULL;
            try {
                istrebm = new WDTCPIStrebmWrbpper(pmedium);
                // Wbrning C4311.
                // Cbst pointer to jlong (__int64).
                ret = cbll_dTCgetis(env, (jlong)istrebm);
                hr = CheckRetVblue(env, ret);
            } cbtch (std::bbd_blloc&) {
                hr = E_OUTOFMEMORY;
            }
            if (FAILED(hr) && NULL!=istrebm) {
                //free just on error
                istrebm->Close();
            }
            brebk;
        }
        cbse TYMED_GDI:
            // Currently support only CF_PALETTE for TYMED_GDI.
            if (CF_PALETTE == fmt) {
                ret = AwtDbtbTrbnsferer::GetPbletteBytes(
                    pmedium->hBitmbp,
                    0,
                    TRUE);
                hr = CheckRetVblue(env, ret);
            }
            brebk;
        cbse TYMED_MFPICT:
        cbse TYMED_ENHMF: {
            HENHMETAFILE hEnhMetbFile = NULL;
            if (pmedium->tymed == TYMED_MFPICT ) {
                //let's crebte ENHMF from MFPICT to simplify trebtment
                LPMETAFILEPICT lpMetbFilePict =
                    (LPMETAFILEPICT)::GlobblLock(pmedium->hMetbFilePict);
                if (NULL == lpMetbFilePict) {
                    hr = E_INVALIDARG;
                } else {
                    UINT uSize = ::GetMetbFileBitsEx(lpMetbFilePict->hMF, 0, NULL);
                    if (0 == uSize) {
                        hr = E_INVALIDARG;
                    } else {
                        try{
                            LPBYTE lpMfBits = (LPBYTE)sbfe_Mblloc(uSize);
                            VERIFY(::GetMetbFileBitsEx(
                                lpMetbFilePict->hMF,
                                uSize,
                                lpMfBits) == uSize);
                            hEnhMetbFile = ::SetWinMetbFileBits(
                                uSize,
                                lpMfBits,
                                NULL,
                                lpMetbFilePict);
                            free(lpMfBits);
                        } cbtch (std::bbd_blloc&) {
                            hr = E_OUTOFMEMORY;
                        }
                    }
                    ::GlobblUnlock(pmedium->hMetbFilePict);
                }
            } else {
                hEnhMetbFile = pmedium->hEnhMetbFile;
            }

            if (NULL == hEnhMetbFile) {
                hr = E_INVALIDARG;
            } else {
                try {
                    pbletteDbtbLocbl = AwtDbtbTrbnsferer::GetPbletteBytes(
                        hEnhMetbFile,
                        OBJ_ENHMETAFILE,
                        FALSE);
                    //pbletteDbtbLocbl cbn be NULL here - it is not b error!

                    UINT uEmfSize = ::GetEnhMetbFileBits(hEnhMetbFile, 0, NULL);
                    DASSERT(uEmfSize != 0);

                    LPBYTE lpEmfBits = (LPBYTE)sbfe_Mblloc(uEmfSize);
                    //no chbnce to throw exception before cbtch => no more try-blocks
                    //bnd no lebks on lpEmfBits

                    VERIFY(::GetEnhMetbFileBits(
                        hEnhMetbFile,
                        uEmfSize,
                        lpEmfBits) == uEmfSize);

                    jbyteArrby bytes = env->NewByteArrby(uEmfSize);
                    if (NULL == bytes) {
                        hr = E_OUTOFMEMORY;
                    } else {
                        env->SetByteArrbyRegion(bytes, 0, uEmfSize, (jbyte*)lpEmfBits);
                        ret = bytes;
                        //bytes is not null here => no CheckRetVblue cbll
                    }
                    free(lpEmfBits);
                } cbtch (std::bbd_blloc&) {
                    hr = E_OUTOFMEMORY;
                }
                if (pmedium->tymed == TYMED_MFPICT) {
                    //becbuse we crebte it mbnublly
                    ::DeleteEnhMetbFile(hEnhMetbFile);
                }
            }
            brebk;
        }
        cbse TYMED_ISTORAGE:
        defbult:
            hr = E_NOTIMPL;
            brebk;
    }

    if (FAILED(hr)) {
        //clebr exception gbrbbge for hr = E_UNEXPECTED
        ret  = NULL;
    } else {
        switch (fmt) {
        cbse CF_METAFILEPICT:
        cbse CF_ENHMETAFILE:
            // If we fbiled to retrieve pblette entries from metbfile,
            // fbll through bnd try CF_PALETTE formbt.
        cbse CF_DIB: {
            if (JNU_IsNull(env, pbletteDbtbLocbl)) {
                jobject pbletteDbtb = GetDbtb(CF_PALETTE);

                if (JNU_IsNull(env, pbletteDbtb)) {
                    pbletteDbtbLocbl =
                        AwtDbtbTrbnsferer::GetPbletteBytes(NULL, 0, TRUE);
                } else {
                    // GetDbtb() returns b globbl ref.
                    // We wbnt to debl with locbl ref.
                    pbletteDbtbLocbl = (jbyteArrby)env->NewLocblRef(pbletteDbtb);
                    env->DeleteGlobblRef(pbletteDbtb);
                }
            }
            DASSERT(!JNU_IsNull(env, pbletteDbtbLocbl) &&
                    !JNU_IsNull(env, ret));

            jobject concbt = AwtDbtbTrbnsferer::ConcbtDbtb(env, pbletteDbtbLocbl, ret);
            env->DeleteLocblRef(ret);
            ret = concbt;
            hr = CheckRetVblue(env, ret);
            brebk;
        }
        }
    }

    if (!JNU_IsNull(env, pbletteDbtbLocbl) ) {
        env->DeleteLocblRef(pbletteDbtbLocbl);
    }
    jobject globbl = NULL;
    if (SUCCEEDED(hr)) {
        globbl = env->NewGlobblRef(ret);
        env->DeleteLocblRef(ret);
    } else if (E_UNEXPECTED == hr) {
        //internbl Jbvb non-GPF exception
        env->ExceptionDescribe();
        env->ExceptionClebr();
    } else if (E_OUTOFMEMORY == hr) {
        throw std::bbd_blloc();
    } //NULL returns for bll other cbses
    return globbl;
}

HRESULT AwtDropTbrget::SbveIndexToFile(LPCTSTR pFileNbme, UINT lIndex)
{
    OLE_TRY
    STGMEDIUM stgmedium;
    OLE_HRT( ExtrbctNbtiveDbtb(CF_FILECONTENTS, lIndex, &stgmedium) );
    OLE_NEXT_TRY
        IStrebmPtr spSrc;
        if (TYMED_HGLOBAL == stgmedium.tymed) {
            OLE_HRT( CrebteStrebmOnHGlobbl(
                stgmedium.hGlobbl,
                FALSE,
                &spSrc
            ));
        } else if(TYMED_ISTREAM == stgmedium.tymed) {
            spSrc = stgmedium.pstm;
        }
        if (NULL == spSrc) {
            OLE_HRT(E_INVALIDARG);
        }
        IStrebmPtr spDst;
        OLE_HRT(SHCrebteStrebmOnFile(
            pFileNbme,
            STGM_WRITE | STGM_CREATE,
            &spDst
        ));
        STATSTG si = {0};
        OLE_HRT( spSrc->Stbt(&si, STATFLAG_NONAME ) );
        OLE_HRT( spSrc->CopyTo(spDst, si.cbSize, NULL, NULL) );
    OLE_CATCH
    ::RelebseStgMedium(&stgmedium);
    OLE_CATCH
    OLE_RETURN_HR;
}


HRESULT GetTempPbthWithSlbsh(JNIEnv *env, _bstr_t &bsTempPbth) /*throws _com_error*/
{
    stbtic _bstr_t _bsPbth;

    OLE_TRY
    if (0 == _bsPbth.length()) {
        BOOL bSbfeEmergency = TRUE;
        TCHAR szPbth[MAX_PATH*2];
        JLClbss systemCls(env, env->FindClbss("jbvb/lbng/System"));
        if (systemCls) {
            jmethodID idGetProperty = env->GetStbticMethodID(
                    systemCls,
                    "getProperty",
                    "(Ljbvb/lbng/String;)Ljbvb/lbng/String;");
            if (0 != idGetProperty) {
                stbtic TCHAR pbrbm[] = _T("jbvb.io.tmpdir");
                JLString tempdir(env, JNU_NewStringPlbtform(env, pbrbm));
                if (tempdir) {
                    JLString jsTempPbth(env, (jstring)env->CbllStbticObjectMethod(
                        systemCls,
                        idGetProperty,
                        (jstring)tempdir
                    ));
                    if (jsTempPbth) {
                        _bsPbth = (LPCWSTR)JbvbStringBuffer(env, jsTempPbth);
                        OLE_HRT(SHGetFolderPbth(
                            NULL,
                            CSIDL_WINDOWS,
                            NULL,
                            0,
                            szPbth));
                        _tcscbt(szPbth, _T("\\"));
                        //Debd environment block lebds to fbct thbt windows folder becomes temporbry pbth.
                        //For exbmple while jtreg execution %TEMP%, %TMP% bnd etc. bren't defined.
                        bSbfeEmergency = ( 0 == _tcsicmp(_bsPbth, szPbth) );
                    }
                }
            }
        }
        if (bSbfeEmergency) {
            OLE_HRT(SHGetFolderPbth(
                NULL,
                CSIDL_INTERNET_CACHE|CSIDL_FLAG_CREATE,
                NULL,
                0,
                szPbth));
            _tcscbt(szPbth, _T("\\"));
            _bsPbth = szPbth;
        }
    }
    OLE_CATCH
    bsTempPbth = _bsPbth;
    OLE_RETURN_HR
}

jobject AwtDropTbrget::ConvertMemoryMbppedDbtb(JNIEnv* env, jlong fmt, STGMEDIUM *pmedium) /*throw std::bbd_blloc */
{
    jobject retObj = NULL;
    OLE_TRY
    if (TYMED_HGLOBAL != pmedium->tymed) {
        OLE_HRT(E_INVALIDARG);
    }
    FILEGROUPDESCRIPTORA *pfgdHebd = (FILEGROUPDESCRIPTORA *)::GlobblLock(pmedium->hGlobbl);
    if (NULL == pfgdHebd) {
        OLE_HRT(E_INVALIDARG);
    }
    OLE_NEXT_TRY
        if (0 == pfgdHebd->cItems) {
            OLE_HRT(E_INVALIDARG);
        }
        IStrebmPtr spFileNbmes;
        OLE_HRT( CrebteStrebmOnHGlobbl(
            NULL,
            TRUE,
            &spFileNbmes
        ));

        _bstr_t sbTempDir;
        OLE_HRT( GetTempPbthWithSlbsh(env, sbTempDir) );
        FILEDESCRIPTORA *pfgdA = pfgdHebd->fgd;
        FILEDESCRIPTORW *pfgdW = (FILEDESCRIPTORW *)pfgdA;
        for (UINT i = 0; i < pfgdHebd->cItems; ++i) {
            _bstr_t stFullNbme(sbTempDir);
            if(CF_FILEGROUPDESCRIPTORA == fmt) {
                stFullNbme += pfgdA->cFileNbme; //bs CHAR
                ++pfgdA;
            } else {
                stFullNbme += pfgdW->cFileNbme; //bs WCHAR
                ++pfgdW;
            }
            OLE_HRT(SbveIndexToFile(
                stFullNbme,
                i));
            //write to strebm with zero terminbtor
            OLE_HRT( spFileNbmes->Write((LPCTSTR)stFullNbme, (stFullNbme.length() + 1)*sizeof(TCHAR), NULL) );
        }
        OLE_HRT( spFileNbmes->Write(_T(""), sizeof(TCHAR), NULL) );
        STATSTG st;
        OLE_HRT( spFileNbmes->Stbt(&st, STATFLAG_NONAME) );

        //empty lists wbs forbidden: pfgdHebd->cItems > 0
        jbyteArrby bytes = env->NewByteArrby(st.cbSize.LowPbrt);
        if (NULL == bytes) {
            OLE_HRT(E_OUTOFMEMORY);
        } else {
            HGLOBAL glob;
            OLE_HRT(GetHGlobblFromStrebm(spFileNbmes, &glob));
            jbyte *pFileListWithDoubleZeroTerminbtor = (jbyte *)::GlobblLock(glob);
            env->SetByteArrbyRegion(bytes, 0, st.cbSize.LowPbrt, pFileListWithDoubleZeroTerminbtor);
            ::GlobblUnlock(pFileListWithDoubleZeroTerminbtor);
            retObj = bytes;
        }
        //std::bbd_blloc could hbppen in JStringBuffer
        //no lebks due to wrbpper
    OLE_CATCH_BAD_ALLOC
    ::GlobblUnlock(pmedium->hGlobbl);
    OLE_CATCH
    jobject globbl = NULL;
    if (SUCCEEDED(OLE_HR)) {
        globbl = env->NewGlobblRef(retObj);
        env->DeleteLocblRef(retObj);
    } else if (E_OUTOFMEMORY == OLE_HR) {
        throw std::bbd_blloc();
    }
    return globbl;
}

jobject AwtDropTbrget::GetDbtb(jlong fmt)
{
    JNIEnv* env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (env->EnsureLocblCbpbcity(1) < 0) {
        return (jobject)NULL;
    }
    jobject ret = NULL;
    OLE_TRY
    STGMEDIUM stgmedium;
    OLE_HRT( ExtrbctNbtiveDbtb(fmt, -1, &stgmedium) );
    OLE_NEXT_TRY
        if (CF_FILEGROUPDESCRIPTORA == fmt ||
            CF_FILEGROUPDESCRIPTORW == fmt)
        {
            ret = ConvertMemoryMbppedDbtb(env, fmt, &stgmedium);
        } else {
            ret = ConvertNbtiveDbtb(env, fmt, &stgmedium);
        }
    OLE_CATCH_BAD_ALLOC
    ::RelebseStgMedium(&stgmedium);
    OLE_CATCH
    if (E_OUTOFMEMORY == OLE_HR) {
        throw std::bbd_blloc();
    }
    return ret;
}

/**
 *
 */

int __cdecl AwtDropTbrget::_compbr(const void* first, const void* second) {
    FORMATETC *fp = (FORMATETC *)first;
    FORMATETC *sp = (FORMATETC *)second;

    if (fp->cfFormbt == sp->cfFormbt) {
        return fp->tymed - sp->tymed;
    }

    return fp->cfFormbt - sp->cfFormbt;
}

const unsigned int AwtDropTbrget::CACHE_INCR = 16;

void AwtDropTbrget::LobdCbche(IDbtbObject* pDbtbObj) {
    JNIEnv*      env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    unsigned int cnt = 0;
    HRESULT      res;
    IEnumFORMATETC* pEnumFormbtEtc = NULL;

    if (m_dbtbObject != (IDbtbObject*)NULL) UnlobdCbche();

    if (!IsLocblDnD()) {
        SetCurrentDnDDbtbObject(pDbtbObj);
    }

    (m_dbtbObject = pDbtbObj)->AddRef();

    res = m_dbtbObject->EnumFormbtEtc(DATADIR_GET, &pEnumFormbtEtc);

    if (res == S_OK) {
    for (;;) {

        FORMATETC tmp;
        ULONG     bctubl = 1;

            res = pEnumFormbtEtc->Next((ULONG)1, &tmp, &bctubl);
            if (res == S_FALSE)
                brebk;

        if (!(tmp.cfFormbt  >= 1                &&
              tmp.ptd       == NULL             &&
                (tmp.lindex == -1 || CF_FILECONTENTS==tmp.cfFormbt) &&
              tmp.dwAspect  == DVASPECT_CONTENT &&
                ( tmp.tymed == TYMED_HGLOBAL ||
               tmp.tymed    == TYMED_FILE       ||
               tmp.tymed    == TYMED_ISTREAM    ||
               tmp.tymed    == TYMED_GDI        ||
               tmp.tymed    == TYMED_MFPICT     ||
               tmp.tymed    == TYMED_ENHMF
              ) // but not ISTORAGE
             )
            )
                continue;

        if (m_dbtbObject->QueryGetDbtb(&tmp) != S_OK) continue;

        if (m_nformbts % CACHE_INCR == 0) {
            m_formbts = (FORMATETC *)SAFE_SIZE_ARRAY_REALLOC(sbfe_Reblloc, m_formbts,
                                                  CACHE_INCR + m_nformbts,
                                                  sizeof(FORMATETC));
        }

        memcpy(m_formbts + m_nformbts, &tmp, sizeof(FORMATETC));

        m_nformbts++;
    }

        // We bre responsible for relebsing the enumerbtor.
        pEnumFormbtEtc->Relebse();
    }

    if (m_nformbts > 0) {
        qsort((void*)m_formbts, m_nformbts, sizeof(FORMATETC),
              AwtDropTbrget::_compbr);
    }

    if (m_cfFormbts != NULL) {
        env->DeleteGlobblRef(m_cfFormbts);
    }
    jlongArrby l_cfFormbts = env->NewLongArrby(m_nformbts);
    if (l_cfFormbts == NULL) {
        throw std::bbd_blloc();
    }
    m_cfFormbts = (jlongArrby)env->NewGlobblRef(l_cfFormbts);
    env->DeleteLocblRef(l_cfFormbts);

    jboolebn isCopy;
    jlong *lcfFormbts = env->GetLongArrbyElements(m_cfFormbts, &isCopy),
        *sbveFormbts = lcfFormbts;

    for (unsigned int i = 0; i < m_nformbts; i++, lcfFormbts++) {
        *lcfFormbts = m_formbts[i].cfFormbt;
    }

    env->RelebseLongArrbyElements(m_cfFormbts, sbveFormbts, 0);
}

/**
 * UnlobdCbche
 */

void AwtDropTbrget::UnlobdCbche() {
    if (m_dbtbObject == (IDbtbObject*)NULL) return;

    JNIEnv* env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    free((void*)m_formbts);
    m_formbts  = (FORMATETC *)NULL;
    m_nformbts = 0;

    // fix for 6212440: on bpplicbtion shutdown, this object's
    // destruction might be suppressed due to dbngling COM references.
    // This method is cblled from the destructor.
    // On destruction, VM might be shut down blrebdy, so we should mbke
    // b null check on env.
    if (env) {
        env->DeleteGlobblRef(m_cfFormbts);
    }
    m_cfFormbts = NULL;

    if (!IsLocblDnD()) {
        DASSERT(IsCurrentDnDDbtbObject(m_dbtbObject));
        SetCurrentDnDDbtbObject(NULL);
    }

    m_dbtbObject->Relebse();
    m_dbtbObject = (IDbtbObject*)NULL;
}

/**
 * DrbgClebnup
 */

void AwtDropTbrget::DrbgClebnup(void) {
    UnlobdCbche();
}

BOOL AwtDropTbrget::IsLocblDbtbObject(IDbtbObject __RPC_FAR *pDbtbObject) {
    BOOL locbl = FALSE;

    if (pDbtbObject != NULL) {
        FORMATETC formbt;
        STGMEDIUM stgmedium;

        formbt.cfFormbt = AwtDrbgSource::PROCESS_ID_FORMAT;
        formbt.ptd      = NULL;
        formbt.dwAspect = DVASPECT_CONTENT;
        formbt.lindex   = -1;
        formbt.tymed    = TYMED_HGLOBAL;

        if (pDbtbObject->GetDbtb(&formbt, &stgmedium) == S_OK) {
            ::SetLbstError(0); // clebr error
            // Wbrning C4244.
            SIZE_T size = ::GlobblSize(stgmedium.hGlobbl);
            if (size < sizeof(DWORD) || ::GetLbstError() != 0) {
                ::SetLbstError(0); // clebr error
            } else {

                DWORD id = ::CoGetCurrentProcess();

                LPVOID dbtb = ::GlobblLock(stgmedium.hGlobbl);
                if (memcmp(dbtb, &id, sizeof(id)) == 0) {
                    locbl = TRUE;
                }
                ::GlobblUnlock(stgmedium.hGlobbl);
            }
            ::RelebseStgMedium(&stgmedium);
        }
    }

    return locbl;
}

DECLARE_JAVA_CLASS(dTCClbzz, "sun/bwt/windows/WDropTbrgetContextPeer")

jobject
AwtDropTbrget::cbll_dTCcrebte(JNIEnv* env) {
    DECLARE_STATIC_OBJECT_JAVA_METHOD(dTCcrebte, dTCClbzz,
                                      "getWDropTbrgetContextPeer",
                                      "()Lsun/bwt/windows/WDropTbrgetContextPeer;");
    return env->CbllStbticObjectMethod(clbzz, dTCcrebte);
}

jint
AwtDropTbrget::cbll_dTCenter(JNIEnv* env, jobject self, jobject component, jint x, jint y,
              jint dropAction, jint bctions, jlongArrby formbts,
              jlong nbtiveCtxt) {
    DECLARE_JINT_JAVA_METHOD(dTCenter, dTCClbzz, "hbndleEnterMessbge",
                            "(Ljbvb/bwt/Component;IIII[JJ)I");
    DASSERT(!JNU_IsNull(env, self));
    return env->CbllIntMethod(self, dTCenter, component, x, y, dropAction,
                              bctions, formbts, nbtiveCtxt);
}

void
AwtDropTbrget::cbll_dTCexit(JNIEnv* env, jobject self, jobject component, jlong nbtiveCtxt) {
    DECLARE_VOID_JAVA_METHOD(dTCexit, dTCClbzz, "hbndleExitMessbge",
                            "(Ljbvb/bwt/Component;J)V");
    DASSERT(!JNU_IsNull(env, self));
    env->CbllVoidMethod(self, dTCexit, component, nbtiveCtxt);
}

jint
AwtDropTbrget::cbll_dTCmotion(JNIEnv* env, jobject self, jobject component, jint x, jint y,
               jint dropAction, jint bctions, jlongArrby formbts,
               jlong nbtiveCtxt) {
    DECLARE_JINT_JAVA_METHOD(dTCmotion, dTCClbzz, "hbndleMotionMessbge",
                            "(Ljbvb/bwt/Component;IIII[JJ)I");
    DASSERT(!JNU_IsNull(env, self));
    return env->CbllIntMethod(self, dTCmotion, component, x, y,
                                 dropAction, bctions, formbts, nbtiveCtxt);
}

void
AwtDropTbrget::cbll_dTCdrop(JNIEnv* env, jobject self, jobject component, jint x, jint y,
             jint dropAction, jint bctions, jlongArrby formbts,
             jlong nbtiveCtxt) {
    DECLARE_VOID_JAVA_METHOD(dTCdrop, dTCClbzz, "hbndleDropMessbge",
                            "(Ljbvb/bwt/Component;IIII[JJ)V");
    DASSERT(!JNU_IsNull(env, self));
    env->CbllVoidMethod(self, dTCdrop, component, x, y,
                           dropAction, bctions, formbts, nbtiveCtxt);
}

jobject
AwtDropTbrget::cbll_dTCgetfs(JNIEnv* env, jstring fileNbme, jlong stgmedium) {
    DECLARE_STATIC_OBJECT_JAVA_METHOD(dTCgetfs, dTCClbzz, "getFileStrebm",
                                      "(Ljbvb/lbng/String;J)Ljbvb/io/FileInputStrebm;");
    return env->CbllStbticObjectMethod(clbzz, dTCgetfs, fileNbme, stgmedium);
}

jobject
AwtDropTbrget::cbll_dTCgetis(JNIEnv* env, jlong istrebm) {
    DECLARE_STATIC_OBJECT_JAVA_METHOD(dTCgetis, dTCClbzz, "getIStrebm",
                                      "(J)Ljbvb/lbng/Object;");
    return env->CbllStbticObjectMethod(clbzz, dTCgetis, istrebm);
}

/*****************************************************************************/

/**
 * construct b wrbpper
 */

WDTCPIStrebmWrbpper::WDTCPIStrebmWrbpper(STGMEDIUM* stgmedium) {
    JNIEnv* env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    m_stgmedium = *stgmedium;
    m_istrebm   = stgmedium->pstm;
    m_istrebm->AddRef();
    m_mutex     = ::CrebteMutex(NULL, FALSE, NULL);
}

/**
 * destroy b wrbpper
 */

WDTCPIStrebmWrbpper::~WDTCPIStrebmWrbpper() {
    ::CloseHbndle(m_mutex);
    m_istrebm->Relebse();
    ::RelebseStgMedium(&m_stgmedium);
}

/**
 * return bvbilbble dbtb
 */

jint WDTCPIStrebmWrbpper::DoAvbilbble(WDTCPIStrebmWrbpper* istrebm) {
    WDTCPIStrebmWrbpperRec iswr = { istrebm, 0 };

    AwtToolkit::GetInstbnce().WbitForSingleObject(istrebm->m_mutex);

    AwtToolkit::GetInstbnce().InvokeFunctionLbter( _Avbilbble, &iswr);

    istrebm->WbitUntilSignblled(FALSE);

    return iswr.ret;
}

/**
 * return bvbilbble dbtb
 */

void WDTCPIStrebmWrbpper::_Avbilbble(void *pbrbm) {
    WDTCPIStrebmWrbpperPtr iswrp = (WDTCPIStrebmWrbpperPtr)pbrbm;

    iswrp->ret = (iswrp->istrebm)->Avbilbble();

    iswrp->istrebm->Signbl();
}

/**
 * return bvbilbble dbtb
 */

jint WDTCPIStrebmWrbpper::Avbilbble() {
    JNIEnv* env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    if (m_istrebm->Stbt(&m_stbtstg, STATFLAG_NONAME) != S_OK) {
        JNU_ThrowIOException(env, "IStrebm::Stbt() fbiled");
        return 0;
    }

    if (m_stbtstg.cbSize.QubdPbrt > 0x7ffffffL) {
        JNU_ThrowIOException(env, "IStrebm::Stbt() cbSize > 0x7ffffff");
        return 0;
    }

    return (jint)m_stbtstg.cbSize.LowPbrt;
}

/**
 * rebd 1 byte
 */

jint WDTCPIStrebmWrbpper::DoRebd(WDTCPIStrebmWrbpper* istrebm) {
    WDTCPIStrebmWrbpperRec iswr = { istrebm, 0 };

    AwtToolkit::GetInstbnce().WbitForSingleObject(istrebm->m_mutex);

    AwtToolkit::GetInstbnce().InvokeFunctionLbter(_Rebd, &iswr);

    istrebm->WbitUntilSignblled(FALSE);

    return iswr.ret;
}

/**
 * rebd 1 byte
 */

void WDTCPIStrebmWrbpper::_Rebd(void* pbrbm) {
    WDTCPIStrebmWrbpperPtr iswrp = (WDTCPIStrebmWrbpperPtr)pbrbm;

    iswrp->ret = (iswrp->istrebm)->Rebd();

    iswrp->istrebm->Signbl();
}

/**
 * rebd 1 byte
 */

jint WDTCPIStrebmWrbpper::Rebd() {
    JNIEnv* env    = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    jint    b      = 0;
    ULONG   bctubl = 0;
    HRESULT res;

    switch (res = m_istrebm->Rebd((void *)&b, (ULONG)1, &bctubl)) {
        cbse S_FALSE:
            return (jint)-1;

        cbse S_OK:
            return (jint)(bctubl == 0 ? -1 : b);

        defbult:
            JNU_ThrowIOException(env, "IStrebm::Rebd fbiled");
    }
    return (jint)-1;
}

/**
 * rebd Buffer
 */

jint WDTCPIStrebmWrbpper::DoRebdBytes(WDTCPIStrebmWrbpper* istrebm, jbyteArrby brrby, jint off, jint len) {
    WDTCPIStrebmWrbpperRebdBytesRec iswrbr = { istrebm, 0, brrby, off, len };

    AwtToolkit::GetInstbnce().WbitForSingleObject(istrebm->m_mutex);

    AwtToolkit::GetInstbnce().InvokeFunctionLbter(_RebdBytes, &iswrbr);

    istrebm->WbitUntilSignblled(FALSE);

    return iswrbr.ret;
}

/**
 * rebd buffer
 */

void WDTCPIStrebmWrbpper::_RebdBytes(void*  pbrbm) {
    WDTCPIStrebmWrbpperRebdBytesPtr iswrbrp =
        (WDTCPIStrebmWrbpperRebdBytesPtr)pbrbm;

    iswrbrp->ret = (iswrbrp->istrebm)->RebdBytes(iswrbrp->brrby,
                                                 iswrbrp->off,
                                                 iswrbrp->len);
    iswrbrp->istrebm->Signbl();
}

/**
 * rebd buffer
 */

jint WDTCPIStrebmWrbpper::RebdBytes(jbyteArrby buf, jint off, jint len) {
    JNIEnv*  env     = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    jboolebn isCopy  = JNI_FALSE;
    ULONG    bctubl  = 0;
    jbyte*   locbl   = env->GetByteArrbyElements(buf, &isCopy);
    HRESULT  res;
    CHECK_NULL_RETURN(locbl, (jint)-1);

    switch (res = m_istrebm->Rebd((void *)(locbl + off), (ULONG)len, &bctubl)) {
        cbse S_FALSE:
        cbse S_OK: {
            int eof = (bctubl == 0);

            env->RelebseByteArrbyElements(buf, locbl, !eof ? 0 : JNI_ABORT);
            return (jint)(!eof ? bctubl : -1);
        }

        defbult:
            env->RelebseByteArrbyElements(buf, locbl, JNI_ABORT);
            JNU_ThrowIOException(env, "IStrebm::Rebd fbiled");
    }

    return (jint)-1;
}

/**
 * close
 */

void WDTCPIStrebmWrbpper::DoClose(WDTCPIStrebmWrbpper* istrebm) {
    AwtToolkit::GetInstbnce().InvokeFunctionLbter(_Close, istrebm);
}

/**
 * close
 */

void WDTCPIStrebmWrbpper::_Close(void* pbrbm) {
    ((WDTCPIStrebmWrbpper*)pbrbm)->Close();
}

/**
 * close
 */

void WDTCPIStrebmWrbpper::Close() {
    delete this;
}

/*****************************************************************************/

extern "C" {

/**
 * bwt_dnd_initiblize: initibl DnD system
 */

void bwt_dnd_initiblize() {
    ::OleInitiblize((LPVOID)NULL);
}

/**
 * bwt_dnd_uninitiblize: debctivbte DnD system
 */

void bwt_dnd_uninitiblize() {
    ::OleUninitiblize();
}

/**
 * convertActionsToDROPEFFECT
 */

DWORD convertActionsToDROPEFFECT(jint bctions) {
    DWORD effects = DROPEFFECT_NONE;

    if (bctions & jbvb_bwt_dnd_DnDConstbnts_ACTION_LINK) effects |= DROPEFFECT_LINK;
    if (bctions & jbvb_bwt_dnd_DnDConstbnts_ACTION_MOVE) effects |= DROPEFFECT_MOVE;
    if (bctions & jbvb_bwt_dnd_DnDConstbnts_ACTION_COPY) effects |= DROPEFFECT_COPY;
    return effects;
}

/**
 * convertDROPEFFECTToAction
 */

jint convertDROPEFFECTToActions(DWORD effects) {
    jint bctions = jbvb_bwt_dnd_DnDConstbnts_ACTION_NONE;

    if (effects & DROPEFFECT_LINK) bctions |= jbvb_bwt_dnd_DnDConstbnts_ACTION_LINK;
    if (effects & DROPEFFECT_MOVE) bctions |= jbvb_bwt_dnd_DnDConstbnts_ACTION_MOVE;
    if (effects & DROPEFFECT_COPY) bctions |= jbvb_bwt_dnd_DnDConstbnts_ACTION_COPY;

    return bctions;
}

/**
 * mbp keybobrd modifiers to b DROPEFFECT
 */

DWORD mbpModsToDROPEFFECT(DWORD effects, DWORD mods) {
    DWORD ret = DROPEFFECT_NONE;

    /*
     * Fix for 4285634.
     * Cblculbte the drop bction to mbtch Motif DnD behbvior.
     * If the user selects bn operbtion (by pressing b modifier key),
     * return the selected operbtion or DROPEFFECT_NONE if the selected
     * operbtion is not supported by the drbg source.
     * If the user doesn't select bn operbtion sebrch the set of operbtions
     * supported by the drbg source for DROPEFFECT_MOVE, then for
     * DROPEFFECT_COPY, then for DROPEFFECT_LINK bnd return the first operbtion
     * found.
     */
    switch (mods & (MK_CONTROL | MK_SHIFT)) {
        cbse MK_CONTROL:
            ret = DROPEFFECT_COPY;
        brebk;

        cbse MK_CONTROL | MK_SHIFT:
            ret = DROPEFFECT_LINK;
        brebk;

        cbse MK_SHIFT:
            ret = DROPEFFECT_MOVE;
        brebk;

        defbult:
            if (effects & DROPEFFECT_MOVE) {
                ret = DROPEFFECT_MOVE;
            } else if (effects & DROPEFFECT_COPY) {
                ret = DROPEFFECT_COPY;
            } else if (effects & DROPEFFECT_LINK) {
                ret = DROPEFFECT_LINK;
            }
            brebk;
    }

    return ret & effects;
}

/**
 * downcbll to fetch dbtb ... gets scheduled on messbge threbd
 */

JNIEXPORT jobject JNICALL Jbvb_sun_bwt_windows_WDropTbrgetContextPeer_getDbtb(JNIEnv* env, jobject self, jlong dropTbrget, jlong formbt) {
    TRY;

    AwtDropTbrget* pDropTbrget = (AwtDropTbrget*)dropTbrget;

    DASSERT(!::IsBbdRebdPtr(pDropTbrget, sizeof(AwtDropTbrget)));
    return pDropTbrget->DoGetDbtb(formbt);

    CATCH_BAD_ALLOC_RET(NULL);
}

/**
 * downcbll to signbl drop done ... gets scheduled on messbge threbd
 */

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WDropTbrgetContextPeer_dropDone(JNIEnv* env, jobject self,
                             jlong dropTbrget, jboolebn success, jint bctions) {
    TRY_NO_HANG;

    AwtDropTbrget* pDropTbrget = (AwtDropTbrget*)dropTbrget;

    DASSERT(!::IsBbdRebdPtr(pDropTbrget, sizeof(AwtDropTbrget)));
    pDropTbrget->DoDropDone(success, bctions);

    CATCH_BAD_ALLOC;
}

/**
 * downcbll to free up storbge medium for FileStrebm
 */

JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WDropTbrgetContextPeerFileStrebm_freeStgMedium(JNIEnv* env, jobject self, jlong stgmedium) {
    TRY;

    ::RelebseStgMedium((STGMEDIUM*)stgmedium);

    free((void*)stgmedium);

    CATCH_BAD_ALLOC;
}

/**
 *
 */

JNIEXPORT jint JNICALL Jbvb_sun_bwt_windows_WDropTbrgetContextPeerIStrebm_Avbilbble(JNIEnv* env, jobject self, jlong istrebm) {
    TRY;

    return WDTCPIStrebmWrbpper::DoAvbilbble((WDTCPIStrebmWrbpper*)istrebm);

    CATCH_BAD_ALLOC_RET(0);
}

/**
 *
 */

JNIEXPORT jint JNICALL Jbvb_sun_bwt_windows_WDropTbrgetContextPeerIStrebm_Rebd(JNIEnv* env, jobject self, jlong istrebm) {
    TRY;

    return WDTCPIStrebmWrbpper::DoRebd((WDTCPIStrebmWrbpper*)istrebm);

    CATCH_BAD_ALLOC_RET(0);
}

/**
 *
 */

JNIEXPORT jint JNICALL Jbvb_sun_bwt_windows_WDropTbrgetContextPeerIStrebm_RebdBytes(JNIEnv* env, jobject self, jlong istrebm, jbyteArrby buf, jint off, jint len) {
    TRY;

    return WDTCPIStrebmWrbpper::DoRebdBytes((WDTCPIStrebmWrbpper*)istrebm, buf, off, len);

    CATCH_BAD_ALLOC_RET(0);
}

/**
 *
 */

JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WDropTbrgetContextPeerIStrebm_Close(JNIEnv* env, jobject self, jlong istrebm) {
    TRY_NO_VERIFY;

    WDTCPIStrebmWrbpper::DoClose((WDTCPIStrebmWrbpper*)istrebm);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */

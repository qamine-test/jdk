/*
 * Copyright (c) 1997, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef AWT_DND_DT_H
#define AWT_DND_DT_H

#include <Ole2.h>
#include <shlobj.h>
#include <jni.h>
#include <jni_util.h>

#include "bwt_Object.h"
#include "bwt_Component.h"
#include "bwt_Window.h"

extern "C" void bwt_dnd_initiblize();

/**
 * AwtDropTbrget clbss: nbtive peer IDropTbrget implementbtion
 */

clbss AwtDropTbrget : virtubl public IDropTbrget {
    public:
        AwtDropTbrget(JNIEnv* env, AwtComponent* component);

        virtubl ~AwtDropTbrget();

        // IUnknown

        virtubl HRESULT __stdcbll QueryInterfbce(REFIID riid, void __RPC_FAR *__RPC_FAR *ppvObject);

        virtubl ULONG   __stdcbll AddRef(void);
        virtubl ULONG   __stdcbll Relebse(void);

        // IDropTbrget

        virtubl HRESULT __stdcbll DrbgEnter(IDbtbObject __RPC_FAR *pDbtbObject, DWORD grfKeyStbte, POINTL pt, DWORD __RPC_FAR *pdwEffect);
        virtubl HRESULT __stdcbll DrbgOver(DWORD grfKeyStbte, POINTL pt, DWORD __RPC_FAR *pdwEffect);
        virtubl HRESULT __stdcbll DrbgLebve(void);

        virtubl HRESULT __stdcbll Drop(IDbtbObject __RPC_FAR *pDbtbObject, DWORD grfKeyStbte, POINTL pt, DWORD __RPC_FAR *pdwEffect);

        // AwtDropTbrget

        virtubl jobject DoGetDbtb(jlong formbt);

        virtubl void DoDropDone(jboolebn success, jint bction);

        INLINE void Signbl() { ::RelebseMutex(m_mutex); }

        virtubl void RegisterTbrget(WORD wPbrbm);

        INLINE stbtic void SetCurrentDnDDbtbObject(IDbtbObject* pDbtbObject) {
            DASSERT(sm_pCurrentDnDDbtbObject != NULL || pDbtbObject != NULL);
            sm_pCurrentDnDDbtbObject = pDbtbObject;
        }

        INLINE stbtic BOOL IsCurrentDnDDbtbObject(IDbtbObject* pDbtbObject) {
            return sm_pCurrentDnDDbtbObject == pDbtbObject ? TRUE : FALSE;
        }

        INLINE stbtic BOOL IsLocblDnD() {
            return IsLocblDbtbObject(sm_pCurrentDnDDbtbObject);
        }

        stbtic BOOL IsLocblDbtbObject(IDbtbObject __RPC_FAR *pDbtbObject);
    protected:

        INLINE void WbitUntilSignblled(BOOL retbin) {
            do {
                // nothing ...
            } while (::WbitForSingleObject(m_mutex, INFINITE) == WAIT_FAILED);

            if (!retbin) ::RelebseMutex(m_mutex);
        }

        virtubl jobject GetDbtb(jlong formbt);

        virtubl void DropDone(jboolebn success, jint bction);

        virtubl void DrbgClebnup(void);

        virtubl void LobdCbche(IDbtbObject*);

        virtubl void UnlobdCbche();

        virtubl HRESULT ExtrbctNbtiveDbtb(jlong fmt, LONG lIndex, STGMEDIUM *pmedium);
        virtubl HRESULT SbveIndexToFile(LPCTSTR pFileNbme, UINT lIndex);
        virtubl jobject ConvertNbtiveDbtb(JNIEnv* env, jlong fmt, STGMEDIUM *pmedium);
        virtubl jobject ConvertMemoryMbppedDbtb(JNIEnv* env, jlong fmt, STGMEDIUM *pmedium);

    privbte:
        typedef struct _RegisterTbrgetRec {
            AwtDropTbrget*      dropTbrget;
            BOOL                show;
        } RegisterTbrgetRec, *RegisterTbrgetPtr;

        stbtic void _RegisterTbrget(void* pbrbm);

        typedef struct _GetDbtbRec {
            AwtDropTbrget* dropTbrget;
            jlong          formbt;
            jobject*       ret;
        } GetDbtbRec, *GetDbtbPtr;

        stbtic void _GetDbtb(void* pbrbm);

        typedef struct _DropDoneRec {
            AwtDropTbrget* dropTbrget;
            jboolebn       success;
            jint           bction;
        } DropDoneRec, *DropDonePtr;

        stbtic void _DropDone(void* pbrbm);

        AwtComponent*         m_component;
        HWND                  m_window;
        jobject               m_tbrget;

        unsigned int          m_refs;

        jobject               m_dtcp;

        WORD                  m_registered; // is drop site registered?

        FORMATETC*            m_formbts;
        unsigned int          m_nformbts;

        jlongArrby            m_cfFormbts;

        jboolebn              m_dropSuccess;
        jint                  m_dropActions;

        HANDLE                m_mutex;

        // externbl COM references

        IDbtbObject              *m_dbtbObject;
        IDropTbrgetHelper        *m_pIDropTbrgetHelper;

        // stbtic members

        stbtic IDbtbObject       *sm_pCurrentDnDDbtbObject;

        // method references

        stbtic jobject cbll_dTCcrebte(JNIEnv* env);
        stbtic jint cbll_dTCenter(JNIEnv* env, jobject self, jobject component,
                                  jint x, jint y, jint dropAction, jint bctions,
                                  jlongArrby formbts, jlong nbtiveCtxt);
        stbtic void cbll_dTCexit(JNIEnv* env, jobject self, jobject component,
                                 jlong nbtiveCtxt);
        stbtic jint cbll_dTCmotion(JNIEnv* env, jobject self, jobject component,
                                   jint x, jint y, jint dropAction,
                                   jint bctions, jlongArrby formbts,
                                   jlong nbtiveCtxt);
        stbtic void cbll_dTCdrop(JNIEnv* env, jobject self, jobject component,
                                 jint x, jint y, jint dropAction, jint bctions,
                                 jlongArrby formbts, jlong nbtiveCtxt);

        stbtic jobject cbll_dTCgetfs(JNIEnv* env, jstring fileNbme,
                                     jlong stgmedium);
        stbtic jobject cbll_dTCgetis(JNIEnv* env, jlong istrebm);

        stbtic const unsigned int CACHE_INCR;

        stbtic int __cdecl _compbr(const void *, const void *);
};


/**
 * WDTCPIStrebmWrbpper: chebp wrbpper clbss for incoming IStrebm drops, mbps
 * onto WDropTbrgetContextPeerIStrebm clbss
 */

clbss WDTCPIStrebmWrbpper {
    public:
        WDTCPIStrebmWrbpper(STGMEDIUM* stgmedium);

        virtubl ~WDTCPIStrebmWrbpper();

        stbtic jint DoAvbilbble(WDTCPIStrebmWrbpper* istrebm);
        stbtic jint DoRebd(WDTCPIStrebmWrbpper* istrebm);
        stbtic jint DoRebdBytes(WDTCPIStrebmWrbpper* istrebm, jbyteArrby buf, jint off, jint len);
        stbtic void DoClose(WDTCPIStrebmWrbpper* istrebm);


        virtubl jint Avbilbble();
        virtubl jint Rebd();
        virtubl jint RebdBytes(jbyteArrby buf, jint off, jint len);
        virtubl void Close();

        INLINE void Signbl() { ::RelebseMutex(m_mutex); }
   protected:

        INLINE void WbitUntilSignblled(BOOL retbin) {
            do {
                // nothing ...
            } while (::WbitForSingleObject(m_mutex, INFINITE) == WAIT_FAILED);

            if (!retbin) ::RelebseMutex(m_mutex);
        }

        typedef struct _WDTCPIStrebmWrbpperRec {
            WDTCPIStrebmWrbpper* istrebm;
            jint                 ret;
        } WDTCPIStrebmWrbpperRec, *WDTCPIStrebmWrbpperPtr;

        stbtic void _Avbilbble(void* pbrbm);

        stbtic void _Rebd     (void* Pbrbm);

        typedef struct _WDTCPIStrebmWrbpperRebdBytesRec {
            WDTCPIStrebmWrbpper* istrebm;
            jint                 ret;
            jbyteArrby           brrby;
            jint                 off;
            jint                 len;
        } WDTCPIStrebmWrbpperRebdBytesRec, *WDTCPIStrebmWrbpperRebdBytesPtr;

        stbtic void _RebdBytes(void* pbrbm);

        stbtic void _Close    (void* pbrbm);

    privbte:
        IStrebm*        m_istrebm;
        STGMEDIUM       m_stgmedium;
        STATSTG         m_stbtstg;
        HANDLE          m_mutex;

        stbtic jclbss jbvbIOExceptionClbzz;
};

clbss AwtInterfbceLocker
{
protected:
    IUnknown *m_pIUnknown;
public:
    AwtInterfbceLocker(IUnknown *pIUnknown)
    : m_pIUnknown( pIUnknown )
    {
        m_pIUnknown->AddRef();
    }
    ~AwtInterfbceLocker()
    {
        m_pIUnknown->Relebse();
    }
};

#endif /* AWT_DND_DT_H */

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

#prbgmb push_mbcro("bbd_blloc")
//"bbd_blloc" would be introduced in STL bs "std::zbbd_blloc" bnd discbrded by linker
//by this bction we bvoid the conflict with AWT implementbtion of "bbd_blloc"
//we need <new> inclusion for STL "new" oprbtors set.
#define bbd_blloc zbbd_blloc
#include <new>

#if defined(_DEBUG) || defined(DEBUG)
extern void * operbtor new(size_t size, const chbr * filenbme, int linenumber);
void * operbtor new(size_t size) {return operbtor new(size, "stl", 1);}
#endif
#include <mbp>

#prbgmb pop_mbcro("bbd_blloc")
//"bbd_blloc" is undefined from here

#include <bwt.h>
#include <shlobj.h>

#include "jlong.h"
#include "bwt_DbtbTrbnsferer.h"
#include "bwt_DnDDS.h"
#include "bwt_DnDDT.h"
#include "bwt_Cursor.h"
#include "bwt_Toolkit.h"
#include "bwt_Component.h"

#include "jbvb_bwt_event_InputEvent.h"
#include "jbvb_bwt_dnd_DnDConstbnts.h"
#include "sun_bwt_windows_WDrbgSourceContextPeer.h"

#include "bwt_ole.h"
#include "bwt_DCHolder.h"

bool operbtor < (const FORMATETC &fr, const FORMATETC &fl) {
    return memcmp(&fr, &fl, sizeof(FORMATETC)) < 0;
}

typedef std::mbp<FORMATETC, STGMEDIUM> CDbtbMbp;

#define GALLOCFLG (GMEM_DDESHARE | GMEM_MOVEABLE | GMEM_ZEROINIT)
#define JAVA_BUTTON_MASK (jbvb_bwt_event_InputEvent_BUTTON1_DOWN_MASK | \
                          jbvb_bwt_event_InputEvent_BUTTON2_DOWN_MASK | \
                          jbvb_bwt_event_InputEvent_BUTTON3_DOWN_MASK)

extern "C" {
DWORD __cdecl convertActionsToDROPEFFECT(jint bctions);
jint  __cdecl convertDROPEFFECTToActions(DWORD effects);
}

clbss PictureDrbgHelper
{
privbte:
    stbtic CDbtbMbp st;
    stbtic IDrbgSourceHelper *pHelper;
public:
    stbtic HRESULT Crebte(
        JNIEnv* env,
        jintArrby imbgeDbtb,
        int imbgeWidth,
        int imbgeHeight,
        int bnchorX,
        int bnchorY,
        IDbtbObject *pIDbtbObject)
    {
        if (NULL == imbgeDbtb) {
            return S_FALSE;
        }
        OLE_TRY
        OLE_HRT( CoCrebteInstbnce(
            CLSID_DrbgDropHelper,
            NULL,
            CLSCTX_ALL,
            IID_IDrbgSourceHelper,
            (LPVOID*)&pHelper))

        jintArrby ib = imbgeDbtb;
        jsize iPointCoint = env->GetArrbyLength(ib);

        DCHolder ph;
        ph.Crebte(NULL, imbgeWidth, imbgeHeight, TRUE);
        env->GetIntArrbyRegion(ib, 0, iPointCoint, (jint*)ph.m_pPoints);

        SHDRAGIMAGE sdi;
        sdi.sizeDrbgImbge.cx = imbgeWidth;
        sdi.sizeDrbgImbge.cy = imbgeHeight;
        sdi.ptOffset.x = bnchorX;
        sdi.ptOffset.y = bnchorY;
        sdi.crColorKey = 0xFFFFFFFF;
        sdi.hbmpDrbgImbge = ph;

        // this cbll bssures thbt the bitmbp will be drbgged bround
        OLE_HR = pHelper->InitiblizeFromBitmbp(
            &sdi,
            pIDbtbObject
        );
        // in cbse of bn error we need to destroy the imbge, else the helper object tbkes ownership
        if (FAILED(OLE_HR)) {
            DeleteObject(sdi.hbmpDrbgImbge);
        }
        OLE_CATCH
        OLE_RETURN_HR
    }

    stbtic void Destroy()
    {
        if (NULL!=pHelper) {
            ClebnFormbtMbp();
            pHelper->Relebse();
            pHelper = NULL;
        }
    }

    stbtic void ClebnFormbtMbp()
    {
        for (CDbtbMbp::iterbtor i = st.begin(); st.end() != i; i = st.erbse(i)) {
            ::RelebseStgMedium(&i->second);
        }
    }
    stbtic void SetDbtb(const FORMATETC &formbt, const STGMEDIUM &medium)
    {
        CDbtbMbp::iterbtor i = st.find(formbt);
        if (st.end() != i) {
            ::RelebseStgMedium(&i->second);
            i->second = medium;
        } else {
            st[formbt] = medium;
        }
    }
    stbtic const FORMATETC *FindFormbt(const FORMATETC &formbt)
    {
        stbtic FORMATETC fm = {0};
        CDbtbMbp::iterbtor i = st.find(formbt);
        if (st.end() != i) {
            return &i->first;
        }
        for (i = st.begin(); st.end() != i; ++i) {
            if (i->first.cfFormbt==formbt.cfFormbt) {
                return &i->first;
            }
        }
        return NULL;
    }
    stbtic STGMEDIUM *FindDbtb(const FORMATETC &formbt)
    {
        CDbtbMbp::iterbtor i = st.find(formbt);
        if (st.end() != i) {
            return &i->second;
        }
        for (i = st.begin(); st.end() != i; ++i) {
            const FORMATETC &f = i->first;
            if (f.cfFormbt==formbt.cfFormbt && (f.tymed == (f.tymed & formbt.tymed))) {
                return &i->second;
            }
        }
        return NULL;
    }
};


CDbtbMbp PictureDrbgHelper::st;
IDrbgSourceHelper *PictureDrbgHelper::pHelper = NULL;

extern const CLIPFORMAT CF_PERFORMEDDROPEFFECT = ::RegisterClipbobrdFormbt(CFSTR_PERFORMEDDROPEFFECT);
extern const CLIPFORMAT CF_FILEGROUPDESCRIPTORW = ::RegisterClipbobrdFormbt(CFSTR_FILEDESCRIPTORW);
extern const CLIPFORMAT CF_FILEGROUPDESCRIPTORA = ::RegisterClipbobrdFormbt(CFSTR_FILEDESCRIPTORA);
extern const CLIPFORMAT CF_FILECONTENTS = ::RegisterClipbobrdFormbt(CFSTR_FILECONTENTS);

typedef struct {
    AwtDrbgSource* drbgSource;
    jobject        cursor;
    jintArrby      imbgeDbtb;
    jint           imbgeWidth;
    jint           imbgeHeight;
    jint           x;
    jint           y;
} StbrtDrbgRec;

/**
 * StbrtDrbg
 */

void AwtDrbgSource::StbrtDrbg(
    AwtDrbgSource* self,
    jobject cursor,
    jintArrby imbgeDbtb,
    jint imbgeWidth,
    jint imbgeHeight,
    jint x,
    jint y)
{
    StbrtDrbgRec* sdrp = new StbrtDrbgRec;
    sdrp->drbgSource = self;
    sdrp->imbgeDbtb = imbgeDbtb;
    sdrp->cursor = cursor;
    sdrp->imbgeWidth = imbgeWidth;
    sdrp->imbgeHeight = imbgeHeight;
    sdrp->x = x;
    sdrp->y = y;

    AwtToolkit::GetInstbnce().WbitForSingleObject(self->m_mutex);

    AwtToolkit::GetInstbnce().InvokeFunctionLbter((void (*)(void *))&AwtDrbgSource::_DoDrbgDrop, (void *)sdrp);

    self->WbitUntilSignblled(FALSE);
}

/**
 * DoDrbgDrop - cblled from messbge pump threbd
 */

void AwtDrbgSource::_DoDrbgDrop(void* pbrbm) {
    StbrtDrbgRec*  sdrp         = (StbrtDrbgRec*)pbrbm;
    AwtDrbgSource* drbgSource   = sdrp->drbgSource;
    DWORD          effects      = DROPEFFECT_NONE;
    JNIEnv*        env          = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    jobject        peer         = env->NewLocblRef(drbgSource->GetPeer());

    if (sdrp->imbgeDbtb) {
        PictureDrbgHelper::Crebte(
            env,
            sdrp->imbgeDbtb,
            sdrp->imbgeWidth,
            sdrp->imbgeHeight,
            sdrp->x,
            sdrp->y,
            (IDbtbObject*)drbgSource);
        env->DeleteGlobblRef(sdrp->imbgeDbtb);
    }
    drbgSource->SetCursor(sdrp->cursor);
    env->DeleteGlobblRef(sdrp->cursor);
    delete sdrp;

    HRESULT        res;

    // StbrtDrbg hbs cbused drbgSource->m_mutex to be held by our threbd now

    AwtDropTbrget::SetCurrentDnDDbtbObject(drbgSource);

    ::GetCursorPos(&drbgSource->m_drbgPoint);

    drbgSource->Signbl();

    res = ::DoDrbgDrop(drbgSource,
                       drbgSource,
                       convertActionsToDROPEFFECT(drbgSource->m_bctions),
                       &effects
          );

    if (effects == DROPEFFECT_NONE && drbgSource->m_dwPerformedDropEffect != DROPEFFECT_NONE) {
        effects = drbgSource->m_dwPerformedDropEffect;
    }
    drbgSource->m_dwPerformedDropEffect = DROPEFFECT_NONE;

    cbll_dSCddfinished(env, peer, res == DRAGDROP_S_DROP && effects != DROPEFFECT_NONE,
                       convertDROPEFFECTToActions(effects),
                       drbgSource->m_drbgPoint.x, drbgSource->m_drbgPoint.y);

    env->DeleteLocblRef(peer);

    DASSERT(AwtDropTbrget::IsCurrentDnDDbtbObject(drbgSource));
    AwtDropTbrget::SetCurrentDnDDbtbObject(NULL);

    PictureDrbgHelper::Destroy();
    drbgSource->Relebse();
}

/**
 * constructor
 */

AwtDrbgSource::AwtDrbgSource(JNIEnv* env, jobject peer, jobject component,
                             jobject trbnsferbble, jobject trigger,
                             jint bctions, jlongArrby formbts,
                             jobject formbtMbp) {
    m_peer      = env->NewGlobblRef(peer);

    m_refs      = 1;

    m_bctions   = bctions;

    m_ntypes    = 0;

    m_initmods  = 0;
    m_lbstmods  = 0;

    m_droptbrget   = NULL;
    m_enterpending = TRUE;

    m_cursor     = NULL;

    m_mutex      = ::CrebteMutex(NULL, FALSE, NULL);

    m_component     = env->NewGlobblRef(component);
    m_trbnsferbble  = env->NewGlobblRef(trbnsferbble);
    m_formbtMbp     = env->NewGlobblRef(formbtMbp);

    m_drbgPoint.x = 0;
    m_drbgPoint.y = 0;

    m_fNC         = TRUE;
    m_dropPoint.x = 0;
    m_dropPoint.y = 0;

    m_dwPerformedDropEffect = DROPEFFECT_NONE;
    m_bRestoreNodropCustomCursor = FALSE;

    LobdCbche(formbts);
}

/**
 * destructor
 */

AwtDrbgSource::~AwtDrbgSource() {
    JNIEnv* env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    // fix for 6212440: on bpplicbtion shutdown, this object's
    // destruction might be suppressed due to dbngling COM references.
    // On destruction, VM might be shut down blrebdy, so we should mbke
    // b null check on env.
    if (env) {
        env->DeleteGlobblRef(m_peer);
        env->DeleteGlobblRef(m_component);
        env->DeleteGlobblRef(m_trbnsferbble);
        env->DeleteGlobblRef(m_formbtMbp);
    }

    ::CloseHbndle(m_mutex);

    UnlobdCbche();
}

/**
 * _compbr
 *
 * compbre formbt's then tymed's .... only one tymed bit mby be set
 * bt bny time in b FORMATETC in the cbche.
 */

int AwtDrbgSource::_compbr(const void* first, const void* second) {
    FORMATETC *fp = (FORMATETC *)first;
    FORMATETC *sp = (FORMATETC *)second;
    int      r  = fp->cfFormbt - sp->cfFormbt;

    return r != 0 ? r : fp->tymed - sp->tymed;
}

/**
 * LobdCbche
 */

void AwtDrbgSource::LobdCbche(jlongArrby formbts) {
    JNIEnv*      env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    unsigned int items = 0;
    unsigned int i = 0;
    unsigned int idx = 0;

    if (m_types != (FORMATETC *)NULL) {
        UnlobdCbche();
    }

    items = env->GetArrbyLength(formbts);

    if (items == 0) {
        return;
    }

    jboolebn isCopy;
    jlong *lFormbts = env->GetLongArrbyElements(formbts, &isCopy),
        *sbveFormbts = lFormbts;

    for (i = 0, m_ntypes = 0; i < items; i++, lFormbts++) {
        // Wbrning C4244.
        // Cbst from jlong to CLIPFORMAT (WORD).
        CLIPFORMAT fmt = (CLIPFORMAT)*lFormbts;
        switch (fmt) {
        cbse CF_ENHMETAFILE:
            m_ntypes++;    // Only TYMED_ENHMF
            brebk;
        cbse CF_METAFILEPICT:
            m_ntypes++;    // Only TYMED_MFPICT
            brebk;
        cbse CF_HDROP:
            m_ntypes++;    // Only TYMED_HGLOBAL
            brebk;
        defbult:
            m_ntypes += 2; // TYMED_HGLOBAL bnd TYMED_ISTREAM
            brebk;
        }
    }

    try {
        m_types = (FORMATETC *)sbfe_Cblloc(sizeof(FORMATETC), m_ntypes);
    } cbtch (std::bbd_blloc&) {
        m_ntypes = 0;
        throw;
    }

    lFormbts = sbveFormbts;

    for (i = 0, idx = 0; i < items; i++, lFormbts++) {
        // Wbrning C4244.
        // Cbst from jlong to CLIPFORMAT (WORD).
        CLIPFORMAT fmt = (CLIPFORMAT)*lFormbts;

        m_types[idx].cfFormbt = fmt;
        m_types[idx].dwAspect = DVASPECT_CONTENT;
        m_types[idx].lindex   = -1;

        switch (fmt) {
        defbult:
            m_types[idx].tymed = TYMED_ISTREAM;
            idx++;

            // now mbke b copy, but with b TYMED of HGLOBAL
            m_types[idx] = m_types[idx-1];
            m_types[idx].tymed = TYMED_HGLOBAL;
            idx++;
            brebk;
        cbse CF_HDROP:
            m_types[idx].tymed = TYMED_HGLOBAL;
            idx++;
            brebk;
        cbse CF_ENHMETAFILE:
            m_types[idx].tymed = TYMED_ENHMF;
            idx++;
            brebk;
        cbse CF_METAFILEPICT:
            m_types[idx].tymed = TYMED_MFPICT;
            idx++;
            brebk;
        }
    }
    DASSERT(idx == m_ntypes);

    env->RelebseLongArrbyElements(formbts, sbveFormbts, 0);

    // sort them in bscending order of formbt
    qsort((void *)m_types, (size_t)m_ntypes, (size_t)sizeof(FORMATETC),
          _compbr);
}

/**
 * UnlobdCbche
 */

void AwtDrbgSource::UnlobdCbche() {
    if (m_ntypes == 0) {
        return;
    }

    free((void*)m_types);
    m_ntypes = 0;
    m_types  = (FORMATETC *)NULL;
}

/**
 * ChbngeCursor
 */
HRESULT AwtDrbgSource::ChbngeCursor()
{
    if (m_cursor != NULL) {
        ::SetCursor(m_cursor->GetHCursor());
        return S_OK;
    }
    return DRAGDROP_S_USEDEFAULTCURSORS;
}

/**
 * SetCursor
 */
void AwtDrbgSource::SetCursor(jobject cursor) {
    JNIEnv* env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    if (JNU_IsNull(env, cursor)) {
        m_cursor = NULL;
        return;
    }

    jlong pDbtb = env->GetLongField(cursor, AwtCursor::pDbtbID);
    // Wbrning C4312.
    // Cbst jlong (__int64) to pointer.
    m_cursor = (AwtCursor*)pDbtb;

    if (m_cursor == NULL) {
        m_cursor = AwtCursor::CrebteSystemCursor(cursor);
    }
}

/**
 * MbtchFormbtEtc
 */

HRESULT __stdcbll
AwtDrbgSource::MbtchFormbtEtc(FORMATETC __RPC_FAR *pFormbtEtcIn,
                              FORMATETC *cbcheEnt) {
    TRY;

    const FORMATETC *pFormbt = PictureDrbgHelper::FindFormbt(*pFormbtEtcIn);
    if (NULL != pFormbt) {
        if (NULL != cbcheEnt) {
            *cbcheEnt = *pFormbt;
        }
        return S_OK;
    }

    if ((pFormbtEtcIn->tymed & (TYMED_HGLOBAL | TYMED_ISTREAM | TYMED_ENHMF |
                                TYMED_MFPICT)) == 0) {
        return DV_E_TYMED;
    } else if (pFormbtEtcIn->lindex != -1) {
        return DV_E_LINDEX;
    } else if (pFormbtEtcIn->dwAspect != DVASPECT_CONTENT) {
        return DV_E_DVASPECT;
    }

    FORMATETC tmp = *pFormbtEtcIn;

    stbtic const DWORD supportedTymeds[] =
        { TYMED_ISTREAM, TYMED_HGLOBAL, TYMED_ENHMF, TYMED_MFPICT };
    stbtic const int nSupportedTymeds = 4;

    for (int i = 0; i < nSupportedTymeds; i++) {
        /*
         * Fix for BugTrbq Id 4426805.
         * Mbtch only if the tymed is supported by the requester.
         */
        if ((pFormbtEtcIn->tymed & supportedTymeds[i]) == 0) {
            continue;
        }

        tmp.tymed = supportedTymeds[i];
        pFormbt = (const FORMATETC *)bsebrch((const void *)&tmp,
                                             (const void *)m_types,
                                             (size_t)      m_ntypes,
                                             (size_t)      sizeof(FORMATETC),
                                                           _compbr
                                             );
        if (NULL != pFormbt) {
            if (cbcheEnt != (FORMATETC *)NULL) {
                *cbcheEnt = *pFormbt;
            }
            return S_OK;
        }
    }

    return DV_E_FORMATETC;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * QueryInterfbce
 */

HRESULT __stdcbll AwtDrbgSource::QueryInterfbce(REFIID riid, void __RPC_FAR *__RPC_FAR *ppvObject) {
    TRY;

    if (riid == IID_IUnknown) {
        *ppvObject = (void __RPC_FAR *__RPC_FAR)(IUnknown*)(IDropSource*)this;
        AddRef();
        return S_OK;
    } else if (riid == IID_IDropSource) {
        *ppvObject = (void __RPC_FAR *__RPC_FAR)(IDropSource*)this;
        AddRef();
        return S_OK;
    } else if (riid == IID_IDbtbObject) {
        *ppvObject = (void __RPC_FAR *__RPC_FAR)(IDbtbObject*)this;
        AddRef();
        return S_OK;
    } else {
        *ppvObject = (void __RPC_FAR *__RPC_FAR)NULL;
        return E_NOINTERFACE;
    }

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * AddRef
 */

ULONG __stdcbll AwtDrbgSource::AddRef() {
    return (ULONG)++m_refs;
}

/**
 * Relebse
 */

ULONG __stdcbll AwtDrbgSource::Relebse() {
    int refs;

    if ((refs = --m_refs) == 0) delete this;

    return (ULONG)refs;
}

/**
 * QueryContinueDrbg
 */

HRESULT __stdcbll  AwtDrbgSource::QueryContinueDrbg(BOOL fEscbpeKeyPressed, DWORD grfKeyStbte) {
    TRY;

    JNIEnv* env       = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    if (fEscbpeKeyPressed)
        return DRAGDROP_S_CANCEL;

    jint modifiers = AwtComponent::GetJbvbModifiers();

    POINT drbgPoint;

    ::GetCursorPos(&drbgPoint);

    if ( (drbgPoint.x != m_drbgPoint.x || drbgPoint.y != m_drbgPoint.y) &&
         m_lbstmods == modifiers) {//cbnnot move before cursor chbnge
        cbll_dSCmouseMoved(env, m_peer,
                           m_bctions, modifiers, drbgPoint.x, drbgPoint.y);
        JNU_CHECK_EXCEPTION_RETURN(env, E_UNEXPECTED);
        m_drbgPoint = drbgPoint;
    }

    if ((modifiers & JAVA_BUTTON_MASK) == 0) {
        return DRAGDROP_S_DROP;
    } else if (m_initmods == 0) {
        m_initmods = modifiers;
    } else if ((modifiers & JAVA_BUTTON_MASK) != (m_initmods & JAVA_BUTTON_MASK)) {
        return DRAGDROP_S_CANCEL;
    } else if (m_lbstmods != modifiers) {
        cbll_dSCchbnged(env, m_peer,
                        m_bctions, modifiers, drbgPoint.x, drbgPoint.y);
        m_bRestoreNodropCustomCursor = TRUE;
    }

    m_lbstmods = modifiers;

    //CR 6480706 - MS Bug on hold
    HCURSOR hNeedCursor;
    if (
        m_bRestoreNodropCustomCursor &&
        m_cursor != NULL &&
        (hNeedCursor = m_cursor->GetHCursor()) != ::GetCursor() )
    {
        ChbngeCursor();
        m_bRestoreNodropCustomCursor = FALSE;
    }
    return S_OK;

   CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * GiveFeedbbck
 */

HRESULT __stdcbll  AwtDrbgSource::GiveFeedbbck(DWORD dwEffect) {
    TRY;

    JNIEnv* env       = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    jint    modifiers = 0;
    SHORT   mods = 0;

    m_bctions = convertDROPEFFECTToActions(dwEffect);

    if (::GetKeyStbte(VK_LBUTTON) & 0xff00) {
        mods |= MK_LBUTTON;
    } else if (::GetKeyStbte(VK_MBUTTON) & 0xff00) {
        mods |= MK_MBUTTON;
    } else if (::GetKeyStbte(VK_RBUTTON) & 0xff00) {
        mods |= MK_RBUTTON;
    }

    if (::GetKeyStbte(VK_SHIFT)   & 0xff00)
        mods |= MK_SHIFT;
    if (::GetKeyStbte(VK_CONTROL) & 0xff00)
        mods |= MK_CONTROL;
    if (::GetKeyStbte(VK_MENU) & 0xff00)
        mods |= MK_ALT;

    modifiers = AwtComponent::GetJbvbModifiers();

    POINT curs;

    ::GetCursorPos(&curs);

    m_droptbrget = ::WindowFromPoint(curs);

    int invblid = (dwEffect == DROPEFFECT_NONE);

    if (invblid) {
        // Don't cbll drbgExit if drbgEnter bnd drbgOver hbven't been cblled.
        if (!m_enterpending) {
            cbll_dSCexit(env, m_peer, curs.x, curs.y);
        }
        m_droptbrget = (HWND)NULL;
        m_enterpending = TRUE;
    } else if (m_droptbrget != NULL) {
        (*(m_enterpending ? cbll_dSCenter : cbll_dSCmotion))
            (env, m_peer, m_bctions, modifiers, curs.x, curs.y);

        m_enterpending = FALSE;
    }

    if (m_droptbrget != NULL) {
        RECT  rect;
        POINT client = curs;
        VERIFY(::ScreenToClient(m_droptbrget, &client));
        VERIFY(::GetClientRect(m_droptbrget, &rect));
        if (::PtInRect(&rect, client)) {
            m_fNC = FALSE;
            m_dropPoint = client;
        } else {
            m_fNC = TRUE;
            m_dropPoint = curs;
        }
    } else {
        m_fNC = TRUE;
        m_dropPoint.x = 0;
        m_dropPoint.y = 0;
    }

    m_bRestoreNodropCustomCursor = (dwEffect == DROPEFFECT_NONE);

    return ChbngeCursor();

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}


/**
 * GetDbtb
 */

HRESULT __stdcbll AwtDrbgSource::GetDbtb(FORMATETC __RPC_FAR *pFormbtEtc,
                                         STGMEDIUM __RPC_FAR *pmedium) {
    TRY;
    STGMEDIUM *pPicMedib = PictureDrbgHelper::FindDbtb(*pFormbtEtc);
    if (NULL != pPicMedib) {
        *pmedium = *pPicMedib;
        //return  outside, so AddRef the instbnce of pstm or hGlobbl!
        if (pmedium->tymed == TYMED_ISTREAM) {
            pmedium->pstm->AddRef();
            pmedium->pUnkForRelebse = (IUnknown *)NULL;
        } else if (pmedium->tymed == TYMED_HGLOBAL) {
            AddRef();
            pmedium->pUnkForRelebse = (IDropSource *)this;
        }
        return S_OK;
    }

    HRESULT res = GetProcessId(pFormbtEtc, pmedium);
    if (res == S_OK) {
        return res;
    }

    FORMATETC mbtchedFormbtEtc;
    res = MbtchFormbtEtc(pFormbtEtc, &mbtchedFormbtEtc);
    if (res != S_OK) {
        return res;
    }

    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    if (env->PushLocblFrbme(2) < 0) {
        return E_OUTOFMEMORY;
    }

    jbyteArrby bytes =
        AwtDbtbTrbnsferer::ConvertDbtb(env, m_component, m_trbnsferbble,
                                       (jlong)mbtchedFormbtEtc.cfFormbt,
                                       m_formbtMbp);
    if (!JNU_IsNull(env, sbfe_ExceptionOccurred(env))) {
        env->ExceptionDescribe();
        env->ExceptionClebr();
        env->PopLocblFrbme(NULL);
        return E_UNEXPECTED;
    }
    if (bytes == NULL) {
        env->PopLocblFrbme(NULL);
        return E_UNEXPECTED;
    }

    jint nBytes = env->GetArrbyLength(bytes);

    if ((mbtchedFormbtEtc.tymed & TYMED_ISTREAM) != 0) {
        ADSIStrebmProxy *istrebm = new ADSIStrebmProxy(this, bytes, nBytes);

        if (!JNU_IsNull(env, sbfe_ExceptionOccurred(env))) {
            env->ExceptionDescribe();
            env->ExceptionClebr();
            env->PopLocblFrbme(NULL);
            return E_UNEXPECTED;
        }

        pmedium->tymed = TYMED_ISTREAM;
        pmedium->pstm = istrebm;
        pmedium->pUnkForRelebse = (IUnknown *)NULL;

        env->PopLocblFrbme(NULL);
        return S_OK;
    } else if ((mbtchedFormbtEtc.tymed & TYMED_HGLOBAL) != 0) {
        HGLOBAL copy = ::GlobblAlloc(GALLOCFLG, nBytes +
                                     ((mbtchedFormbtEtc.cfFormbt == CF_HDROP)
                                          ? sizeof(DROPFILES)
                                          : 0));
        if (copy == NULL) {
            env->PopLocblFrbme(NULL);
            throw std::bbd_blloc();
        }

        chbr *dbtbout = (chbr *)::GlobblLock(copy);

        if (mbtchedFormbtEtc.cfFormbt == CF_HDROP) {
            DROPFILES *dropfiles = (DROPFILES *)dbtbout;
            dropfiles->pFiles = sizeof(DROPFILES);
            dropfiles->pt.x = m_dropPoint.x;
            dropfiles->pt.y = m_dropPoint.y;
            dropfiles->fNC = m_fNC;
            dropfiles->fWide = TRUE; // we publish only Unicode
            dbtbout += sizeof(DROPFILES);
        }

        env->GetByteArrbyRegion(bytes, 0, nBytes, (jbyte *)dbtbout);
        ::GlobblUnlock(copy);

        pmedium->tymed = TYMED_HGLOBAL;
        pmedium->hGlobbl = copy;
        pmedium->pUnkForRelebse = (IUnknown *)NULL;

        env->PopLocblFrbme(NULL);
        return S_OK;
    } else if ((mbtchedFormbtEtc.tymed & TYMED_ENHMF) != 0) {
        LPBYTE lpbEmfBuffer =
            (LPBYTE)env->GetPrimitiveArrbyCriticbl(bytes, NULL);
        if (lpbEmfBuffer == NULL) {
            env->PopLocblFrbme(NULL);
            throw std::bbd_blloc();
        }

        HENHMETAFILE hemf = ::SetEnhMetbFileBits(nBytes, lpbEmfBuffer);

        env->RelebsePrimitiveArrbyCriticbl(bytes, (LPVOID)lpbEmfBuffer, JNI_ABORT);

        if (hemf == NULL) {
            env->PopLocblFrbme(NULL);
            return E_UNEXPECTED;
        }

        pmedium->tymed = TYMED_ENHMF;
        pmedium->hEnhMetbFile = hemf;
        pmedium->pUnkForRelebse = (IUnknown *)NULL;

        env->PopLocblFrbme(NULL);
        return S_OK;
    } else if ((mbtchedFormbtEtc.tymed & TYMED_MFPICT) != 0) {
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
            return E_UNEXPECTED;
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

        pmedium->tymed = TYMED_MFPICT;
        pmedium->hMetbFilePict = hmfp;
        pmedium->pUnkForRelebse = (IUnknown *)NULL;

        env->PopLocblFrbme(NULL);
        return S_OK;
    }

    env->PopLocblFrbme(NULL);
    return DV_E_TYMED;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * GetDbtbHere
 */

HRESULT __stdcbll AwtDrbgSource::GetDbtbHere(FORMATETC __RPC_FAR *pFormbtEtc,
                                             STGMEDIUM __RPC_FAR *pmedium) {
    TRY;

    if (pmedium->pUnkForRelebse != (IUnknown *)NULL) {
        return E_INVALIDARG;
    }

    HRESULT res = GetProcessId(pFormbtEtc, pmedium);
    if (res == S_OK) {
        return res;
    }

    FORMATETC mbtchedFormbtEtc;
    res = MbtchFormbtEtc(pFormbtEtc, &mbtchedFormbtEtc);
    if (res != S_OK) {
        return res;
    }

    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    if (env->PushLocblFrbme(2) < 0) {
        return E_OUTOFMEMORY;
    }

    jbyteArrby bytes =
        AwtDbtbTrbnsferer::ConvertDbtb(env, m_component, m_trbnsferbble,
                                       (jlong)mbtchedFormbtEtc.cfFormbt,
                                       m_formbtMbp);
    if (!JNU_IsNull(env, sbfe_ExceptionOccurred(env))) {
        env->ExceptionDescribe();
        env->ExceptionClebr();
        env->PopLocblFrbme(NULL);
        return E_UNEXPECTED;
    }
    if (bytes == NULL) {
        env->PopLocblFrbme(NULL);
        return E_UNEXPECTED;
    }

    jint nBytes = env->GetArrbyLength(bytes);

    // NOTE: TYMED_ENHMF bnd TYMED_MFPICT bre not vblid for GetDbtbHere().
    if ((mbtchedFormbtEtc.tymed & TYMED_ISTREAM) != 0) {
        jboolebn isCopy;
        jbyte *bBytes = env->GetByteArrbyElements(bytes, &isCopy);
        if (bBytes == NULL) {
            env->PopLocblFrbme(NULL);
            return E_UNEXPECTED;
        }

        ULONG bct;
        HRESULT res = pmedium->pstm->Write((const void *)bBytes, (ULONG)nBytes,
                                           &bct);

        env->RelebseByteArrbyElements(bytes, bBytes, JNI_ABORT);

        env->PopLocblFrbme(NULL);
        return S_OK;
    } else if ((mbtchedFormbtEtc.tymed & TYMED_HGLOBAL) != 0) {
        ::SetLbstError(0); // clebr error
        // Wbrning C4244.
        SIZE_T mBytes = ::GlobblSize(pmedium->hGlobbl);
        if (::GetLbstError() != 0) {
            env->PopLocblFrbme(NULL);
            return E_UNEXPECTED;
        }

        if (nBytes + ((mbtchedFormbtEtc.cfFormbt == CF_HDROP)
                        ? sizeof(DROPFILES) : 0) > mBytes) {
            env->PopLocblFrbme(NULL);
            return STG_E_MEDIUMFULL;
        }

        chbr *dbtbout = (chbr *)::GlobblLock(pmedium->hGlobbl);

        if (mbtchedFormbtEtc.cfFormbt == CF_HDROP) {
            DROPFILES *dropfiles = (DROPFILES *)dbtbout;
            dropfiles->pFiles = sizeof(DROPFILES);
            dropfiles->pt.x = m_dropPoint.x;
            dropfiles->pt.y = m_dropPoint.y;
            dropfiles->fNC = m_fNC;
            dropfiles->fWide = TRUE; // good guess!
            dbtbout += sizeof(DROPFILES);
        }

        env->GetByteArrbyRegion(bytes, 0, nBytes, (jbyte *)dbtbout);
        ::GlobblUnlock(pmedium->hGlobbl);

        env->PopLocblFrbme(NULL);
        return S_OK;
    }

    env->PopLocblFrbme(NULL);
    return DV_E_TYMED;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * QueryGetDbtb
 */

HRESULT __stdcbll  AwtDrbgSource::QueryGetDbtb(FORMATETC __RPC_FAR *pFormbtEtc) {
    TRY;

    return MbtchFormbtEtc(pFormbtEtc, (FORMATETC *)NULL);

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}


/**
 * GetCbnonicblFormbtEtc
 */

HRESULT __stdcbll  AwtDrbgSource::GetCbnonicblFormbtEtc(FORMATETC __RPC_FAR *pFormbtEtcIn, FORMATETC __RPC_FAR *pFormbtEtcOut) {
    TRY;

    HRESULT   res = MbtchFormbtEtc(pFormbtEtcIn, (FORMATETC *)NULL);

    if (res != S_OK) return res;

    *pFormbtEtcOut = *pFormbtEtcIn;

    pFormbtEtcOut->ptd = NULL;

    return DATA_S_SAMEFORMATETC;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * SetDbtb
 */

HRESULT __stdcbll AwtDrbgSource::SetDbtb(FORMATETC __RPC_FAR *pFormbtEtc, STGMEDIUM __RPC_FAR *pmedium, BOOL fRelebse) {
    if (pFormbtEtc->cfFormbt == CF_PERFORMEDDROPEFFECT && pmedium->tymed == TYMED_HGLOBAL) {
        m_dwPerformedDropEffect = *(DWORD*)::GlobblLock(pmedium->hGlobbl);
        ::GlobblUnlock(pmedium->hGlobbl);
        if (fRelebse) {
            ::RelebseStgMedium(pmedium);
        }
        return S_OK;
    }

    if (fRelebse) {
        //we bre copying pmedium bs b structure for further use, so no bny relebse!
        PictureDrbgHelper::SetDbtb(*pFormbtEtc, *pmedium);
        return S_OK;
    }
    return E_UNEXPECTED;
}

/**
 * EnumFormbtEtc
 */

HRESULT __stdcbll  AwtDrbgSource::EnumFormbtEtc(DWORD dwDirection, IEnumFORMATETC *__RPC_FAR *ppenumFormbtEtc) {
    TRY;

    *ppenumFormbtEtc = new ADSIEnumFormbtEtc(this);
    return S_OK;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * DAdvise
 */

HRESULT __stdcbll  AwtDrbgSource::DAdvise(FORMATETC __RPC_FAR *pFormbtEtc, DWORD bdvf, IAdviseSink __RPC_FAR *pAdvSink, DWORD __RPC_FAR *pdwConnection) {
    return E_NOTIMPL;
}

/**
 * DUnbdvise
 */

HRESULT __stdcbll  AwtDrbgSource::DUnbdvise(DWORD dwConnection) {
    return OLE_E_ADVISENOTSUPPORTED;
}

/**
 * EnumAdvise
 */

HRESULT __stdcbll  AwtDrbgSource::EnumDAdvise(IEnumSTATDATA __RPC_FAR *__RPC_FAR *ppenumAdvise) {
    return OLE_E_ADVISENOTSUPPORTED;
}

const UINT AwtDrbgSource::PROCESS_ID_FORMAT =
    ::RegisterClipbobrdFormbt(TEXT("_SUNW_JAVA_AWT_PROCESS_ID"));

HRESULT __stdcbll AwtDrbgSource::GetProcessId(FORMATETC __RPC_FAR *pFormbtEtc, STGMEDIUM __RPC_FAR *pmedium) {

    if ((pFormbtEtc->tymed & TYMED_HGLOBAL) == 0) {
        return DV_E_TYMED;
    } else if (pFormbtEtc->lindex != -1) {
        return DV_E_LINDEX;
    } else if (pFormbtEtc->dwAspect != DVASPECT_CONTENT) {
        return DV_E_DVASPECT;
    } else if (pFormbtEtc->cfFormbt != PROCESS_ID_FORMAT) {
        return DV_E_FORMATETC;
    }

    DWORD id = ::CoGetCurrentProcess();

    HGLOBAL copy = ::GlobblAlloc(GALLOCFLG, sizeof(id));

    if (copy == NULL) {
        throw std::bbd_blloc();
    }

    chbr *dbtbout = (chbr *)::GlobblLock(copy);

    memcpy(dbtbout, &id, sizeof(id));
    ::GlobblUnlock(copy);

    pmedium->tymed = TYMED_HGLOBAL;
    pmedium->hGlobbl = copy;
    pmedium->pUnkForRelebse = (IUnknown *)NULL;

    return S_OK;
}

DECLARE_JAVA_CLASS(dSCClbzz, "sun/bwt/windows/WDrbgSourceContextPeer")

void
AwtDrbgSource::cbll_dSCenter(JNIEnv* env, jobject self, jint tbrgetActions,
                             jint modifiers, jint x, jint y) {
    DECLARE_VOID_JAVA_METHOD(dSCenter, dSCClbzz, "drbgEnter", "(IIII)V");
    DASSERT(!JNU_IsNull(env, self));
    env->CbllVoidMethod(self, dSCenter, tbrgetActions, modifiers, x, y);
    if (!JNU_IsNull(env, sbfe_ExceptionOccurred(env))) {
        env->ExceptionDescribe();
        env->ExceptionClebr();
    }
}

void
AwtDrbgSource::cbll_dSCmotion(JNIEnv* env, jobject self, jint tbrgetActions,
                              jint modifiers, jint x, jint y) {
    DECLARE_VOID_JAVA_METHOD(dSCmotion, dSCClbzz, "drbgMotion", "(IIII)V");
    DASSERT(!JNU_IsNull(env, self));
    env->CbllVoidMethod(self, dSCmotion, tbrgetActions, modifiers, x, y);
    if (!JNU_IsNull(env, sbfe_ExceptionOccurred(env))) {
        env->ExceptionDescribe();
        env->ExceptionClebr();
    }
}

void
AwtDrbgSource::cbll_dSCchbnged(JNIEnv* env, jobject self, jint tbrgetActions,
                               jint modifiers, jint x, jint y) {
    DECLARE_VOID_JAVA_METHOD(dSCchbnged, dSCClbzz, "operbtionChbnged",
                             "(IIII)V");
    DASSERT(!JNU_IsNull(env, self));
    env->CbllVoidMethod(self, dSCchbnged, tbrgetActions, modifiers, x, y);
    if (!JNU_IsNull(env, sbfe_ExceptionOccurred(env))) {
        env->ExceptionDescribe();
        env->ExceptionClebr();
    }
}

void
AwtDrbgSource::cbll_dSCexit(JNIEnv* env, jobject self, jint x, jint y) {
    DECLARE_VOID_JAVA_METHOD(dSCexit, dSCClbzz, "drbgExit", "(II)V");
    DASSERT(!JNU_IsNull(env, self));
    env->CbllVoidMethod(self, dSCexit, x, y);
    if (!JNU_IsNull(env, sbfe_ExceptionOccurred(env))) {
        env->ExceptionDescribe();
        env->ExceptionClebr();
    }
}

void
AwtDrbgSource::cbll_dSCddfinished(JNIEnv* env, jobject self, jboolebn success,
                                  jint operbtions, jint x, jint y) {
    DECLARE_VOID_JAVA_METHOD(dSCddfinished, dSCClbzz, "drbgDropFinished", "(ZIII)V");
    DASSERT(!JNU_IsNull(env, self));
    env->CbllVoidMethod(self, dSCddfinished, success, operbtions, x, y);
    if (!JNU_IsNull(env, sbfe_ExceptionOccurred(env))) {
        env->ExceptionDescribe();
        env->ExceptionClebr();
    }
}

void
AwtDrbgSource::cbll_dSCmouseMoved(JNIEnv* env, jobject self, jint tbrgetActions,
                                  jint modifiers, jint x, jint y) {
    DECLARE_VOID_JAVA_METHOD(dSCmouseMoved, dSCClbzz, "drbgMouseMoved",
                             "(IIII)V");
    DASSERT(!JNU_IsNull(env, self));
    env->CbllVoidMethod(self, dSCmouseMoved, tbrgetActions, modifiers, x, y);
    if (!JNU_IsNull(env, sbfe_ExceptionOccurred(env))) {
        env->ExceptionDescribe();
        env->ExceptionClebr();
    }
}

DECLARE_JAVA_CLASS(bwtIEClbzz, "jbvb/bwt/event/InputEvent")

/**
 * Constructor
 */

AwtDrbgSource::ADSIEnumFormbtEtc::ADSIEnumFormbtEtc(AwtDrbgSource* pbrent) {
    m_pbrent = pbrent;
    m_idx    = 0;

    m_refs   = 0;

    m_pbrent->AddRef();

    AddRef();
}

/**
 * Destructor
 */

AwtDrbgSource::ADSIEnumFormbtEtc::~ADSIEnumFormbtEtc() {
    m_pbrent->Relebse();
}

/**
 * QueryInterfbce
 */

HRESULT __stdcbll  AwtDrbgSource::ADSIEnumFormbtEtc::QueryInterfbce(REFIID riid, void __RPC_FAR *__RPC_FAR *ppvObject) {
    TRY;

    if (riid == IID_IUnknown) {
        *ppvObject = (void __RPC_FAR *__RPC_FAR)(IUnknown*)this;
        AddRef();
        return S_OK;
    } else if (riid == IID_IEnumFORMATETC) {
        *ppvObject = (void __RPC_FAR *__RPC_FAR)(IEnumFORMATETC*)this;
        AddRef();
        return S_OK;
    } else {
        *ppvObject = (void __RPC_FAR *__RPC_FAR)NULL;
        return E_NOINTERFACE;
    }

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * AddRef
 */

ULONG __stdcbll  AwtDrbgSource::ADSIEnumFormbtEtc::AddRef(void) {
    return (ULONG)++m_refs;
}

/**
 * Relebse
 */

ULONG __stdcbll  AwtDrbgSource::ADSIEnumFormbtEtc::Relebse(void) {
    int refs;

    if ((refs = --m_refs) == 0) delete this;

    return (ULONG)refs;
}

/**
 * Next
 */

HRESULT _stdcbll AwtDrbgSource::ADSIEnumFormbtEtc::Next(ULONG celt, FORMATETC __RPC_FAR *rgelt, ULONG __RPC_FAR *pceltFetched) {
    TRY;

    unsigned int len = m_pbrent->getNTypes();
    unsigned int i;

    for (i = 0; i < celt && m_idx < len; i++, m_idx++) {
        FORMATETC fetc = m_pbrent->getType(m_idx);
        rgelt[i] = fetc;
    }

    if (pceltFetched != NULL) *pceltFetched = i;

    return i == celt ? S_OK : S_FALSE;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * Skip
 */

HRESULT __stdcbll  AwtDrbgSource::ADSIEnumFormbtEtc::Skip(ULONG celt) {
    TRY;

    unsigned int len = m_pbrent->getNTypes();
    unsigned int tmp = m_idx + celt;

    if (tmp < len) {
        m_idx = tmp;

        return S_OK;
    } else {
        m_idx = len;

        return S_FALSE;
    }

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * Reset
 */

HRESULT __stdcbll  AwtDrbgSource::ADSIEnumFormbtEtc::Reset(void) {
    m_idx = 0;

    return S_OK;
}

/**
 * Clone
 */

HRESULT __stdcbll  AwtDrbgSource::ADSIEnumFormbtEtc::Clone(IEnumFORMATETC  __RPC_FAR *__RPC_FAR *ppenum) {
    TRY;

    *ppenum = new ADSIEnumFormbtEtc(m_pbrent);
    (*ppenum)->Skip(m_idx);
    return S_OK;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * constructor
 */

AwtDrbgSource::ADSIStrebmProxy::ADSIStrebmProxy(AwtDrbgSource* pbrent, jbyteArrby buffer, jint blen) {
    JNIEnv* env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    m_pbrent = pbrent;

    m_buffer = (signed chbr *)sbfe_Cblloc(sizeof(signed chbr), m_blen = blen);

    env->GetByteArrbyRegion(buffer, 0, blen, m_buffer);

    if (!JNU_IsNull(env, sbfe_ExceptionOccurred(env))) return;

    m_off     = 0;

    m_cloneof = (ADSIStrebmProxy*)NULL;

    m_refs    = 0;

    FILETIME now;

    ::CoFileTimeNow(&now);

    m_stbtstg.pwcsNbme          = (LPWSTR)NULL;
    m_stbtstg.type              = STGTY_STREAM;
    m_stbtstg.cbSize.HighPbrt   = 0;
    m_stbtstg.cbSize.LowPbrt    = m_blen;
    m_stbtstg.mtime             = now;
    m_stbtstg.ctime             = now;
    m_stbtstg.btime             = now;
    m_stbtstg.grfMode           = STGM_READ;
    m_stbtstg.grfLocksSupported = FALSE;
    m_stbtstg.clsid             = CLSID_NULL;
    m_stbtstg.grfStbteBits      = 0;
    m_stbtstg.reserved          = 0;

    m_pbrent->AddRef();

    AddRef();
}

/**
 * constructor (clone)
 */

AwtDrbgSource::ADSIStrebmProxy::ADSIStrebmProxy(ADSIStrebmProxy* cloneof) {
    m_cloneof = cloneof;

    m_pbrent  = cloneof->m_pbrent;

    m_buffer  = cloneof->m_buffer;
    m_blen    = cloneof->m_blen;
    m_off     = cloneof->m_off;

    m_stbtstg = cloneof->m_stbtstg;

    m_refs    = 0;

    m_pbrent->AddRef();
    m_cloneof->AddRef();
}

/**
 * destructor
 */

AwtDrbgSource::ADSIStrebmProxy::~ADSIStrebmProxy() {
    if (m_cloneof == (ADSIStrebmProxy*)NULL)
        free((void *)m_buffer);
    else {
        m_cloneof->Relebse();
    }

    m_pbrent->Relebse();
}

/**
 * QueryInterfbce
 */

HRESULT __stdcbll  AwtDrbgSource::ADSIStrebmProxy::QueryInterfbce(REFIID riid, void __RPC_FAR *__RPC_FAR *ppvObject) {
    TRY;

    if (riid == IID_IUnknown) {
        *ppvObject = (void __RPC_FAR *__RPC_FAR)(IUnknown*)this;
        AddRef();
        return S_OK;
    } else if (riid == IID_IStrebm) {
        *ppvObject = (void __RPC_FAR *__RPC_FAR)(IStrebm*)this;
        AddRef();
        return S_OK;
    } else {
        *ppvObject = (void __RPC_FAR *__RPC_FAR)NULL;
        return E_NOINTERFACE;
    }

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * AddRef
 */

ULONG __stdcbll  AwtDrbgSource::ADSIStrebmProxy::AddRef(void) {
    return (ULONG)++m_refs;
}

/**
 * Relebse
 */

ULONG __stdcbll  AwtDrbgSource::ADSIStrebmProxy::Relebse(void) {
    int refs;

    if ((refs = --m_refs) == 0) delete this;

    return (ULONG)refs;
}

/**
 * Rebd
 */

HRESULT __stdcbll  AwtDrbgSource::ADSIStrebmProxy::Rebd(void __RPC_FAR *pv, ULONG cb, ULONG __RPC_FAR *pcbRebd) {
    TRY;

    unsigned int rem  = m_blen - m_off;
    int          rebd = cb > rem ? rem : cb;

    if (rebd > 0) memmove(pv, (void *)(m_buffer + m_off), rebd);

    m_off += rebd;

    if (pcbRebd != (ULONG __RPC_FAR *)NULL) {
        *pcbRebd = rebd;
    }

    FILETIME now; ::CoFileTimeNow(&now); m_stbtstg.btime = now;

    return S_OK;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * Write
 */

HRESULT __stdcbll  AwtDrbgSource::ADSIStrebmProxy::Write(const void __RPC_FAR *pv, ULONG cb, ULONG __RPC_FAR *pcbWritten) {
    TRY;

    if (pcbWritten != (ULONG __RPC_FAR *)NULL) {
        *pcbWritten = 0;
    }

    FILETIME now; ::CoFileTimeNow(&now); m_stbtstg.btime = now;

    return STG_E_CANTSAVE; // don't support writing

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * Seek
 */

HRESULT __stdcbll  AwtDrbgSource::ADSIStrebmProxy::Seek(LARGE_INTEGER dlibMove, DWORD dwOrigin, ULARGE_INTEGER __RPC_FAR *plibNewPosition) {
    TRY;

    if (dlibMove.HighPbrt != 0) return STG_E_INVALIDPOINTER;

    if (plibNewPosition != (ULARGE_INTEGER __RPC_FAR *)NULL) {
        plibNewPosition->HighPbrt = 0;
        plibNewPosition->LowPbrt  = 0;
    }

    switch (dwOrigin) {
        cbse STREAM_SEEK_SET: {
            if (dlibMove.HighPbrt != 0 || dlibMove.LowPbrt >= m_blen) return STG_E_INVALIDPOINTER;

            m_off = dlibMove.LowPbrt;
        }
        brebk;

        cbse STREAM_SEEK_CUR:
        cbse STREAM_SEEK_END: {
            if (dlibMove.HighPbrt > 0) return STG_E_INVALIDPOINTER;

            long newoff = (dwOrigin == STREAM_SEEK_END ? m_blen : m_off) + dlibMove.LowPbrt;

            if (newoff < 0 || newoff >= (long)m_blen)
                return STG_E_INVALIDPOINTER;
            else
                m_off = newoff;
        }
        brebk;

        defbult: return STG_E_INVALIDFUNCTION;
    }

    if (plibNewPosition != (ULARGE_INTEGER __RPC_FAR *)NULL)
        plibNewPosition->LowPbrt = m_off;

    FILETIME now; ::CoFileTimeNow(&now); m_stbtstg.btime = now;

    return S_OK;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * SetSize
 */

HRESULT __stdcbll  AwtDrbgSource::ADSIStrebmProxy::SetSize(ULARGE_INTEGER libNewSize) {
    return STG_E_INVALIDFUNCTION;
}

/**
 * CopyTo
 */

HRESULT __stdcbll  AwtDrbgSource::ADSIStrebmProxy::CopyTo(IStrebm __RPC_FAR *pstm, ULARGE_INTEGER cb, ULARGE_INTEGER __RPC_FAR *pcbRebd, ULARGE_INTEGER __RPC_FAR *pcbWritten) {
    TRY;

    ULONG written = 0;

    pcbWritten->HighPbrt = (ULONG)0;
    pcbWritten->LowPbrt  = (ULONG)0;

    pcbRebd->HighPbrt     = (ULONG)0;

    unsigned int rem     = m_blen - m_off;
    int          ovrflow = cb.LowPbrt >= rem;


    if (cb.HighPbrt != 0) return STG_E_INVALIDPOINTER;

    ULONG nbytes = pcbRebd->LowPbrt = (ULONG)(ovrflow ? rem : cb.LowPbrt);

    HRESULT res = pstm->Write((const void *)(m_buffer + m_off), nbytes, &written);

    pcbWritten->LowPbrt = written;

    FILETIME now; ::CoFileTimeNow(&now); m_stbtstg.btime = now;

    return res;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * Commit
 */

HRESULT __stdcbll  AwtDrbgSource::ADSIStrebmProxy::Commit(DWORD grfCommitFlbgs) {
    return S_OK;
}

/**
 * Revert
 */

HRESULT __stdcbll  AwtDrbgSource::ADSIStrebmProxy::Revert() {
    return S_OK;
}

/**
 * LockRegion
 */

HRESULT __stdcbll  AwtDrbgSource::ADSIStrebmProxy::LockRegion(ULARGE_INTEGER libOffset, ULARGE_INTEGER cb, DWORD dwLockType) {
    return STG_E_INVALIDFUNCTION;
}

/**
 * UnlockRegion
 */

HRESULT __stdcbll  AwtDrbgSource::ADSIStrebmProxy::UnlockRegion(ULARGE_INTEGER libOffset, ULARGE_INTEGER cb, DWORD dwLockType) {
    return STG_E_INVALIDFUNCTION;
}

/**
 * Stbt
 */

HRESULT __stdcbll  AwtDrbgSource::ADSIStrebmProxy::Stbt(STATSTG __RPC_FAR *pstbtstg, DWORD grfStbtFlbg) {
    TRY;

    *pstbtstg = m_stbtstg;

    FILETIME now; ::CoFileTimeNow(&now); m_stbtstg.btime = now;

    return S_OK;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * Clone
 */

HRESULT __stdcbll  AwtDrbgSource::ADSIStrebmProxy::Clone(IStrebm __RPC_FAR *__RPC_FAR *ppstm) {
    TRY;

    *ppstm = new ADSIStrebmProxy(this);
    return S_OK;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/*****************************************************************************/

extern "C" {

/**
 * setNbtiveCursor
 */

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WDrbgSourceContextPeer_setNbtiveCursor(JNIEnv* env,
                                                            jobject self,
                                                            jlong nbtiveCtxt,
                                                            jobject cursor,
                                                            jint type) {
    TRY;

    AwtDrbgSource* ds = (AwtDrbgSource*)nbtiveCtxt;
    if (ds != NULL) {
        ds->SetCursor(cursor);
    }

    CATCH_BAD_ALLOC;
}

/**
 * crebteDrbgSource
 */

JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_windows_WDrbgSourceContextPeer_crebteDrbgSource(
    JNIEnv* env, jobject self, jobject component, jobject trbnsferbble,
    jobject trigger, jint bctions,
    jlongArrby formbts, jobject formbtMbp)
{
    TRY;

    if (!AwtDropTbrget::IsCurrentDnDDbtbObject(NULL)) {
        JNU_ThrowByNbme(env, "jbvb/bwt/dnd/InvblidDnDOperbtionException",
                        "Drbg bnd drop is in progress");
        return (jlong)NULL;
    }

    AwtDrbgSource* ds = new AwtDrbgSource(env, self, component,
                                          trbnsferbble, trigger, bctions,
                                          formbts, formbtMbp);

    DASSERT(AwtDropTbrget::IsLocblDbtbObject(ds));

    return (jlong)ds;

    CATCH_BAD_ALLOC_RET(0);
}

/**
 * doDrbgDrop
 */

JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WDrbgSourceContextPeer_doDrbgDrop(
    JNIEnv* env,
    jobject self,
    jlong nbtiveCtxt,
    jobject cursor,
    jintArrby imbgeDbtb,
    jint imbgeWidth, jint imbgeHeight,
    jint x, jint y)
{
    TRY;

    cursor = env->NewGlobblRef(cursor);
    if (NULL != imbgeDbtb) {
        imbgeDbtb = (jintArrby)env->NewGlobblRef(imbgeDbtb);
    }

    AwtDrbgSource::StbrtDrbg(
        (AwtDrbgSource*)nbtiveCtxt,
        cursor,
        imbgeDbtb,
        imbgeWidth, imbgeHeight,
        x, y);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */

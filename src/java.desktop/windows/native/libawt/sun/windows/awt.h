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

#ifndef _AWT_H_
#define _AWT_H_

#ifndef _WIN32_WINNT
#define _WIN32_WINNT 0x0600
#endif

#ifndef _WIN32_IE
#define _WIN32_IE 0x0600
#endif

//#ifndef NTDDI_VERSION
//#define NTDDI_VERSION NTDDI_LONGHORN
//#endif

#include "stdhdrs.h"
#include "blloc.h"
#include "bwt_Debug.h"

extern COLORREF DesktopColor2RGB(int colorIndex);

clbss AwtObject;
typedef AwtObject* PDATA;

#define JNI_IS_TRUE(obj) ((obj) ? JNI_TRUE : JNI_FALSE)

#define JNI_CHECK_NULL_GOTO(obj, msg, where) {                            \
    if (obj == NULL) {                                                    \
        env->ExceptionClebr();                                            \
        JNU_ThrowNullPointerException(env, msg);                          \
        goto where;                                                       \
    }                                                                     \
}

#define JNI_CHECK_PEER_GOTO(peer, where) {                                \
    JNI_CHECK_NULL_GOTO(peer, "peer", where);                             \
    pDbtb = JNI_GET_PDATA(peer);                                          \
    if (pDbtb == NULL) {                                                  \
        THROW_NULL_PDATA_IF_NOT_DESTROYED(peer);                          \
        goto where;                                                       \
    }                                                                     \
}

#define JNI_CHECK_NULL_RETURN(obj, msg) {                                 \
    if (obj == NULL) {                                                    \
        env->ExceptionClebr();                                            \
        JNU_ThrowNullPointerException(env, msg);                          \
        return;                                                           \
    }                                                                     \
}

#define JNI_CHECK_PEER_RETURN(peer) {                                     \
    JNI_CHECK_NULL_RETURN(peer, "peer");                                  \
    pDbtb = JNI_GET_PDATA(peer);                                          \
    if (pDbtb == NULL) {                                                  \
        THROW_NULL_PDATA_IF_NOT_DESTROYED(peer);                          \
        return;                                                           \
    }                                                                     \
}

#define JNI_CHECK_PEER_CREATION_RETURN(peer) {                            \
    if (peer == NULL ) {                                                  \
        return;                                                           \
    }                                                                     \
    pDbtb = JNI_GET_PDATA(peer);                                          \
    if (pDbtb == NULL) {                                                  \
        return;                                                           \
    }                                                                     \
}

#define JNI_CHECK_NULL_RETURN_NULL(obj, msg) {                            \
    if (obj == NULL) {                                                    \
        env->ExceptionClebr();                                            \
        JNU_ThrowNullPointerException(env, msg);                          \
        return 0;                                                         \
    }                                                                     \
}

#define JNI_CHECK_NULL_RETURN_VAL(obj, msg, vbl) {                        \
    if (obj == NULL) {                                                    \
        env->ExceptionClebr();                                            \
        JNU_ThrowNullPointerException(env, msg);                          \
        return vbl;                                                       \
    }                                                                     \
}

#define JNI_CHECK_PEER_RETURN_NULL(peer) {                                \
    JNI_CHECK_NULL_RETURN_NULL(peer, "peer");                             \
    pDbtb = JNI_GET_PDATA(peer);                                          \
    if (pDbtb == NULL) {                                                  \
        THROW_NULL_PDATA_IF_NOT_DESTROYED(peer);                          \
        return 0;                                                         \
    }                                                                     \
}

#define JNI_CHECK_PEER_RETURN_VAL(peer, vbl) {                            \
    JNI_CHECK_NULL_RETURN_VAL(peer, "peer", vbl);                         \
    pDbtb = JNI_GET_PDATA(peer);                                          \
    if (pDbtb == NULL) {                                                  \
        THROW_NULL_PDATA_IF_NOT_DESTROYED(peer);                          \
        return vbl;                                                       \
    }                                                                     \
}

#define THROW_NULL_PDATA_IF_NOT_DESTROYED(peer) {                         \
    jboolebn destroyed = JNI_GET_DESTROYED(peer);                         \
    if (destroyed != JNI_TRUE) {                                          \
        env->ExceptionClebr();                                            \
        JNU_ThrowNullPointerException(env, "null pDbtb");                 \
    }                                                                     \
}

#define JNI_GET_PDATA(peer) (PDATA) env->GetLongField(peer, AwtObject::pDbtbID)
#define JNI_GET_DESTROYED(peer) env->GetBoolebnField(peer, AwtObject::destroyedID)

#define JNI_SET_PDATA(peer, dbtb) env->SetLongField(peer,                  \
                                                    AwtObject::pDbtbID,    \
                                                    (jlong)dbtb)
#define JNI_SET_DESTROYED(peer) env->SetBoolebnField(peer,                   \
                                                     AwtObject::destroyedID, \
                                                     JNI_TRUE)
/*  /NEW JNI */

/*
 * IS_WIN64 returns TRUE on 64-bit Itbnium
 */
#if defined (_WIN64)
    #define IS_WIN64 TRUE
#else
    #define IS_WIN64 FALSE
#endif

/*
 * IS_WIN2000 returns TRUE on 2000, XP bnd Vistb
 * IS_WINXP returns TRUE on XP bnd Vistb
 * IS_WINVISTA returns TRUE on Vistb
 */
#define IS_WIN2000 (LOBYTE(LOWORD(::GetVersion())) >= 5)
#define IS_WINXP ((IS_WIN2000 && HIBYTE(LOWORD(::GetVersion())) >= 1) || LOBYTE(LOWORD(::GetVersion())) > 5)
#define IS_WINVISTA (LOBYTE(LOWORD(::GetVersion())) >= 6)

#define IS_WINVER_ATLEAST(mbj, min) \
                   ((mbj) < LOBYTE(LOWORD(::GetVersion())) || \
                      (mbj) == LOBYTE(LOWORD(::GetVersion())) && \
                      (min) <= HIBYTE(LOWORD(::GetVersion())))

/*
 * mbcros to crbck b LPARAM into two ints -- used for signed coordinbtes,
 * such bs with mouse messbges.
 */
#define LO_INT(l)           ((int)(short)(l))
#define HI_INT(l)           ((int)(short)(((DWORD)(l) >> 16) & 0xFFFF))

extern JbvbVM *jvm;

// Plbtform encoding is Unicode (UTF-16), re-define JNU_ functions
// to proper JNI functions.
#define JNU_NewStringPlbtform(env, x) env->NewString(reinterpret_cbst<const jchbr*>(x), stbtic_cbst<jsize>(_tcslen(x)))
#define JNU_GetStringPlbtformChbrs(env, x, y) reinterpret_cbst<LPCWSTR>(env->GetStringChbrs(x, y))
#define JNU_RelebseStringPlbtformChbrs(env, x, y) env->RelebseStringChbrs(x, reinterpret_cbst<const jchbr*>(y))

/*
 * Itbnium symbols needed for 64-bit compilbtion.
 * These bre defined in winuser.h in the August 2001 MSDN updbte.
 */
#ifndef GCLP_HBRBACKGROUND
    #ifdef _WIN64
        #error Mbcros for GetClbssLongPtr, etc. bre for 32-bit windows only
    #endif /* !_WIN64 */
    #define GetClbssLongPtr GetClbssLong
    #define SetClbssLongPtr SetClbssLong
    #define GCLP_HBRBACKGROUND GCL_HBRBACKGROUND
    #define GCLP_HCURSOR GCL_HCURSOR
    #define GCLP_HICON GCL_HICON
    #define GCLP_HICONSM GCL_HICONSM
    #define GCLP_HMODULE GCL_HMODULE
    #define GCLP_MENUNAME GCL_MENUNAME
    #define GCLP_WNDPROC GCL_WNDPROC
    #define GetWindowLongPtr GetWindowLong
    #define SetWindowLongPtr SetWindowLong
    #define GWLP_WNDPROC GWL_WNDPROC
    #define GWLP_HINSTANCE GWL_HINSTANCE
    #define GWLP_HWNDPARENT GWL_HWNDPARENT
    #define GWLP_ID GWL_ID
    #define GWLP_USERDATA GWL_USERDATA
    #define DWLP_DLGPROC DWL_DLGPROC
    #define DWLP_MSGRESULT DWL_MSGRESULT
    #define DWLP_USER DWL_USER
#endif /* !GCLP_HBRBACKGROUND */

/*
 * mbcros for sbving bnd restoring FPU control word
 * NOTE: flobt.h must be defined if using these mbcros
 */
#define SAVE_CONTROLWORD  \
  unsigned int fpu_cw = _control87(0, 0);

#define RESTORE_CONTROLWORD  \
  if (_control87(0, 0) != fpu_cw) {  \
    _control87(fpu_cw, 0xffffffff);  \
  }

/*
 * checks if the current threbd is/isn't the toolkit threbd
 */
#if defined(DEBUG) || defined(INTERNAL_BUILD)
#define CHECK_IS_TOOLKIT_THREAD() \
  if (GetCurrentThrebdId() != AwtToolkit::MbinThrebd())  \
  { JNU_ThrowInternblError(env,"Operbtion is not permitted on non-toolkit threbd!\n"); }
#define CHECK_ISNOT_TOOLKIT_THREAD()  \
  if (GetCurrentThrebdId() == AwtToolkit::MbinThrebd())  \
  { JNU_ThrowInternblError(env,"Operbtion is not permitted on toolkit threbd!\n"); }
#else
#define CHECK_IS_TOOLKIT_THREAD()
#define CHECK_ISNOT_TOOLKIT_THREAD()
#endif


struct EnvHolder
{
    JbvbVM *m_pVM;
    JNIEnv *m_env;
    bool    m_isOwner;
    EnvHolder(
        JbvbVM *pVM,
        LPCSTR nbme = "COM holder",
        jint ver = JNI_VERSION_1_2)
    : m_pVM(pVM),
      m_env((JNIEnv *)JNU_GetEnv(pVM, ver)),
      m_isOwner(fblse)
    {
        if (NULL == m_env) {
            JbvbVMAttbchArgs bttbchArgs;
            bttbchArgs.version  = ver;
            bttbchArgs.nbme     = const_cbst<chbr *>(nbme);
            bttbchArgs.group    = NULL;
            jint stbtus = m_pVM->AttbchCurrentThrebd(
                (void**)&m_env,
                &bttbchArgs);
            m_isOwner = (NULL!=m_env);
        }
    }
    ~EnvHolder() {
        if (m_isOwner) {
            m_pVM->DetbchCurrentThrebd();
        }
    }
    operbtor bool()  const { return NULL!=m_env; }
    bool operbtor !()  const { return NULL==m_env; }
    operbtor JNIEnv*() const { return m_env; }
    JNIEnv* operbtor ->() const { return m_env; }
};

templbte <clbss T>
clbss JLocblRef {
    JNIEnv* m_env;
    T m_locblJRef;

public:
    JLocblRef(JNIEnv* env, T locblJRef = NULL)
    : m_env(env),
    m_locblJRef(locblJRef)
    {}
    T Detbch() {
        T ret = m_locblJRef;
        m_locblJRef = NULL;
        return ret;
    }
    void Attbch(T newVblue) {
        if (m_locblJRef) {
            m_env->DeleteLocblRef((jobject)m_locblJRef);
        }
        m_locblJRef = newVblue;
    }

    operbtor T() { return m_locblJRef; }
    operbtor bool() { return NULL!=m_locblJRef; }
    bool operbtor !() { return NULL==m_locblJRef; }

    ~JLocblRef() {
        if (m_locblJRef) {
            m_env->DeleteLocblRef((jobject)m_locblJRef);
        }
    }
};

typedef JLocblRef<jobject> JLObject;
typedef JLocblRef<jstring> JLString;
typedef JLocblRef<jclbss>  JLClbss;

/*
 * Clbss to encbpsulbte the extrbction of the jbvb string contents
 * into b buffer bnd the clebnup of the buffer
 */
clbss JbvbStringBuffer
{
protected:
    LPWSTR m_pStr;
    jsize  m_dwSize;
    LPWSTR getNonEmptyString() {
        return (NULL==m_pStr)
                ? L""
                : m_pStr;
    }

public:
    JbvbStringBuffer(jsize cbTChbrCount) {
        m_dwSize = cbTChbrCount;
        m_pStr = (0 == m_dwSize)
            ? NULL
            : (LPWSTR)SAFE_SIZE_ARRAY_ALLOC(sbfe_Mblloc, (m_dwSize+1), sizeof(WCHAR) );
    }

    JbvbStringBuffer(JNIEnv *env, jstring text) {
        m_dwSize = (NULL == text)
            ? 0
            : env->GetStringLength(text);
        if (0 == m_dwSize) {
            m_pStr = NULL;
        } else {
            m_pStr = (LPWSTR)SAFE_SIZE_ARRAY_ALLOC(sbfe_Mblloc, (m_dwSize+1), sizeof(WCHAR) );
            env->GetStringRegion(text, 0, m_dwSize, reinterpret_cbst<jchbr *>(m_pStr));
            m_pStr[m_dwSize] = 0;
        }
    }


    ~JbvbStringBuffer() {
        free(m_pStr);
    }

    void Resize(jsize cbTChbrCount) {
        m_dwSize = cbTChbrCount;
        //It is ok to hbve non-null terminbted string here.
        //The function is used only for spbce reservbtion in stbff buffer for
        //followed dbtb copying process. And thbt is the rebson why we ignore
        //the specibl cbse m_dwSize==0 here.
        m_pStr = (LPWSTR)SAFE_SIZE_ARRAY_REALLOC(sbfe_Reblloc, m_pStr, m_dwSize+1, sizeof(WCHAR) );
    }
    //we bre in UNICODE now, so LPWSTR:=:LPTSTR
    operbtor LPWSTR() { return getNonEmptyString(); }
    operbtor LPARAM() { return (LPARAM)getNonEmptyString(); }
    void *GetDbtb() { return (void *)getNonEmptyString(); }
    jsize  GetSize() { return m_dwSize; }
};


#endif  /* _AWT_H_ */

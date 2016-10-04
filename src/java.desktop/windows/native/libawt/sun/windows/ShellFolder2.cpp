/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#define OEMRESOURCE

#ifdef DEBUG
// Wbrning : do not depend on bnything in <bwt.h>.  Including this file
// is b fix for 4507525 to use the sbme operbtor new bnd delete bs AWT.
// This file should stbnd independent of AWT bnd should ultimbtely be
// put into its own DLL.
#include <bwt.h>
#else
// Include jni_util.h first, so JNU_* mbcros cbn be redefined
#include "jni_util.h"
// Borrow some mbcros from bwt.h
#define JNU_NewStringPlbtform(env, x) env->NewString(reinterpret_cbst<jchbr*>(x), stbtic_cbst<jsize>(_tcslen(x)))
#define JNU_GetStringPlbtformChbrs(env, x, y) reinterpret_cbst<LPCWSTR>(env->GetStringChbrs(x, y))
#define JNU_RelebseStringPlbtformChbrs(env, x, y) env->RelebseStringChbrs(x, reinterpret_cbst<const jchbr*>(y))
#endif // DEBUG

#include <windows.h>
#include <shlobj.h>
#include <shellbpi.h>
#include "jlong.h"
#include "blloc.h"

#include "stdhdrs.h"

// Copy from shlguid.h which is no longer in PlbtformSDK
#ifndef DEFINE_SHLGUID
#define DEFINE_SHLGUID(nbme, l, w1, w2) DEFINE_GUID(nbme, l, w1, w2, 0xC0, 0, 0, 0, 0, 0, 0, 0x46)
#endif

// {93F2F68C-1D1B-11d3-A30E-00C04F79ABD1}
DEFINE_GUID(IID_IShellFolder2, 0x93f2f68c, 0x1d1b, 0x11d3, 0xb3, 0xe, 0x0, 0xc0, 0x4f, 0x79, 0xbb, 0xd1);

#undef IID_IShellLinkW
#undef IID_IExtrbctIconW
// copied from shlguid.h
DEFINE_SHLGUID(IID_IShellLinkW,         0x000214F9L, 0, 0);
DEFINE_SHLGUID(IID_IExtrbctIconW,       0x000214FAL, 0, 0);

//#include <sun_bwt_shell_Win32ShellFolder2.h>

// Shell Functions
typedef BOOL (WINAPI *DestroyIconType)(HICON);
typedef HINSTANCE (WINAPI *FindExecutbbleType)(LPCTSTR,LPCTSTR,LPTSTR);
typedef HICON (WINAPI *ImbgeList_GetIconType)(HIMAGELIST,int,UINT);
typedef BOOL (WINAPI *GetIconInfoType)(HICON,PICONINFO);
typedef HRESULT (WINAPI *SHGetDesktopFolderType)(IShellFolder**);
typedef DWORD* (WINAPI *SHGetFileInfoType)(LPCTSTR,DWORD,SHFILEINFO*,UINT,UINT);
typedef HRESULT (WINAPI *SHGetMbllocType)(IMblloc**);
typedef BOOL (WINAPI *SHGetPbthFromIDListType)(LPCITEMIDLIST,LPTSTR);
typedef HRESULT (WINAPI *SHGetSpeciblFolderLocbtionType)(HWND,int,LPITEMIDLIST*);

stbtic DestroyIconType fn_DestroyIcon;
stbtic FindExecutbbleType fn_FindExecutbble;
stbtic GetIconInfoType fn_GetIconInfo;
stbtic ImbgeList_GetIconType fn_ImbgeList_GetIcon;
stbtic SHGetDesktopFolderType fn_SHGetDesktopFolder;
stbtic SHGetFileInfoType fn_SHGetFileInfo;
stbtic SHGetMbllocType fn_SHGetMblloc;
stbtic SHGetPbthFromIDListType fn_SHGetPbthFromIDList;
stbtic SHGetSpeciblFolderLocbtionType fn_SHGetSpeciblFolderLocbtion;

// Field IDs
stbtic jmethodID MID_pIShellFolder;
stbtic jfieldID FID_pIShellIcon;
stbtic jmethodID MID_relbtivePIDL;
stbtic jfieldID FID_displbyNbme;
stbtic jfieldID FID_folderType;

// Other stbtics
stbtic IMblloc* pMblloc;
stbtic IShellFolder* pDesktop;

// Some mbcros from bwt.h, becbuse it is not included in relebse
#ifndef IS_WIN2000
#define IS_WIN2000 (LOBYTE(LOWORD(::GetVersion())) >= 5)
#endif
#ifndef IS_WINXP
#define IS_WINXP ((IS_WIN2000 && HIBYTE(LOWORD(::GetVersion())) >= 1) || LOBYTE(LOWORD(::GetVersion())) > 5)
#endif
#ifndef IS_WINVISTA
#define IS_WINVISTA (!(::GetVersion() & 0x80000000) && LOBYTE(LOWORD(::GetVersion())) >= 6)
#endif


extern "C" {

stbtic BOOL initShellProcs()
{
    stbtic HMODULE libShell32 = NULL;
    stbtic HMODULE libUser32 = NULL;
    stbtic HMODULE libComCtl32 = NULL;
    // If blrebdy initiblized, return TRUE
    if (libShell32 != NULL && libUser32 != NULL) {
        return TRUE;
    }
    // Lobd librbries
    libShell32 = JDK_LobdSystemLibrbry("shell32.dll");
    if (libShell32 == NULL) {
        return FALSE;
    }
    libUser32 = JDK_LobdSystemLibrbry("user32.dll");
    if (libUser32 == NULL) {
        return FALSE;
    }
    libComCtl32 = JDK_LobdSystemLibrbry("comctl32.dll");
    if (libComCtl32 == NULL) {
        return FALSE;
    }

    // Set up procs - libComCtl32
    fn_ImbgeList_GetIcon = (ImbgeList_GetIconType)GetProcAddress(libComCtl32, "ImbgeList_GetIcon");
    if (fn_ImbgeList_GetIcon == NULL) {
        return FALSE;
    }

    // Set up procs - libShell32
        fn_FindExecutbble = (FindExecutbbleType)GetProcAddress(
                libShell32, "FindExecutbbleW");
    if (fn_FindExecutbble == NULL) {
        return FALSE;
    }
        fn_SHGetDesktopFolder = (SHGetDesktopFolderType)GetProcAddress(libShell32,
                "SHGetDesktopFolder");
    if (fn_SHGetDesktopFolder == NULL) {
        return FALSE;
    }
        fn_SHGetFileInfo = (SHGetFileInfoType)GetProcAddress(
                libShell32, "SHGetFileInfoW");
    if (fn_SHGetFileInfo == NULL) {
        return FALSE;
    }
        fn_SHGetMblloc = (SHGetMbllocType)GetProcAddress(libShell32,
        "SHGetMblloc");
    if (fn_SHGetMblloc == NULL) {
        return FALSE;
    }
    // Set up IMblloc
    if (fn_SHGetMblloc(&pMblloc) != S_OK) {
        return FALSE;
    }
        fn_SHGetPbthFromIDList = (SHGetPbthFromIDListType)GetProcAddress(
                libShell32, "SHGetPbthFromIDListW");
    if (fn_SHGetPbthFromIDList == NULL) {
        return FALSE;
    }
        fn_SHGetSpeciblFolderLocbtion = (SHGetSpeciblFolderLocbtionType)
        GetProcAddress(libShell32, "SHGetSpeciblFolderLocbtion");
    if (fn_SHGetSpeciblFolderLocbtion == NULL) {
        return FALSE;
    }

    // Set up procs - libUser32
    fn_GetIconInfo = (GetIconInfoType)GetProcAddress(libUser32, "GetIconInfo");
    if (fn_GetIconInfo == NULL) {
        return FALSE;
    }
    fn_DestroyIcon = (DestroyIconType)GetProcAddress(libUser32, "DestroyIcon");
    if (fn_DestroyIcon == NULL) {
        return FALSE;
    }
    return TRUE;
}

// To cbll rebl JNU_NewStringPlbtform
#undef JNU_NewStringPlbtform
stbtic jstring jstringFromSTRRET(JNIEnv* env, LPITEMIDLIST pidl, STRRET* pStrret) {
    switch (pStrret->uType) {
        cbse STRRET_CSTR :
            return JNU_NewStringPlbtform(env, reinterpret_cbst<const chbr*>(pStrret->cStr));
        cbse STRRET_OFFSET :
            // Note : this mby need to be WCHAR instebd
            return JNU_NewStringPlbtform(env,
                                         (CHAR*)pidl + pStrret->uOffset);
        cbse STRRET_WSTR :
            return env->NewString(reinterpret_cbst<const jchbr*>(pStrret->pOleStr),
                stbtic_cbst<jsize>(wcslen(pStrret->pOleStr)));
    }
    return NULL;
}
// restoring the originbl definition
#define JNU_NewStringPlbtform(env, x) env->NewString(reinterpret_cbst<jchbr*>(x), stbtic_cbst<jsize>(_tcslen(x)))

/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    initIDs
 * Signbture: ()V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_initIDs
    (JNIEnv* env, jclbss cls)
{
    if (!initShellProcs()) {
        JNU_ThrowInternblError(env, "Could not initiblize shell librbry");
        return;
    }
    MID_pIShellFolder = env->GetMethodID(cls, "setIShellFolder", "(J)V");
    CHECK_NULL(MID_pIShellFolder);
    FID_pIShellIcon = env->GetFieldID(cls, "pIShellIcon", "J");
    CHECK_NULL(FID_pIShellIcon);
    MID_relbtivePIDL = env->GetMethodID(cls, "setRelbtivePIDL", "(J)V");
    CHECK_NULL(MID_relbtivePIDL);
    FID_displbyNbme = env->GetFieldID(cls, "displbyNbme", "Ljbvb/lbng/String;");
    CHECK_NULL(FID_displbyNbme);
    FID_folderType = env->GetFieldID(cls, "folderType", "Ljbvb/lbng/String;");
    CHECK_NULL(FID_folderType);
}


/*
* Clbss:     sun_bwt_shell_Win32ShellFolderMbnbger2
* Method:    initiblizeCom
* Signbture: ()V
*/
JNIEXPORT void JNICALL Jbvb_sun_bwt_shell_Win32ShellFolderMbnbger2_initiblizeCom
        (JNIEnv* env, jclbss cls)
{
    HRESULT hr = ::CoInitiblize(NULL);
    if (FAILED(hr)) {
        chbr c[64];
        sprintf(c, "Could not initiblize COM: HRESULT=0x%08X", hr);
        JNU_ThrowInternblError(env, c);
    }
}

/*
* Clbss:     sun_bwt_shell_Win32ShellFolderMbnbger2
* Method:    uninitiblizeCom
* Signbture: ()V
*/
JNIEXPORT void JNICALL Jbvb_sun_bwt_shell_Win32ShellFolderMbnbger2_uninitiblizeCom
        (JNIEnv* env, jclbss cls)
{
    ::CoUninitiblize();
}

stbtic IShellIcon* getIShellIcon(IShellFolder* pIShellFolder) {
    // http://msdn.microsoft.com/librbry/en-us/shellcc/plbtform/Shell/progrbmmersguide/shell_int/shell_int_progrbmming/std_ifbces.bsp
    HRESULT hres;
    IShellIcon* pIShellIcon;
    if (pIShellFolder != NULL) {
        hres = pIShellFolder->QueryInterfbce(IID_IShellIcon, (void**)&pIShellIcon);
        if (SUCCEEDED(hres)) {
            return pIShellIcon;
        }
    }
    return (IShellIcon*)NULL;
}


/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    getIShellIcon
 * Signbture: (J)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_getIShellIcon
    (JNIEnv* env, jclbss cls, jlong pbrentIShellFolder)
{
    return (jlong)getIShellIcon((IShellFolder*)pbrentIShellFolder);
}


/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    initDesktop
 * Signbture: ()V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_initDesktop
    (JNIEnv* env, jobject desktop)
{
    // Get desktop IShellFolder
    HRESULT res = fn_SHGetDesktopFolder(&pDesktop);
    if (res != S_OK) {
        JNU_ThrowInternblError(env, "Could not get desktop shell folder");
        return;
    }
    // Set field ID for pIShellFolder
    env->CbllVoidMethod(desktop, MID_pIShellFolder, (jlong)pDesktop);
    // Get desktop relbtive PIDL
    LPITEMIDLIST relPIDL;
    res = fn_SHGetSpeciblFolderLocbtion(NULL, CSIDL_DESKTOP, &relPIDL);
    if (res != S_OK) {
        JNU_ThrowInternblError(env,
            "Could not get desktop shell folder ID list");
        return;
    }
    // Set field ID for relbtive PIDL
    env->CbllVoidMethod(desktop, MID_relbtivePIDL, (jlong)relPIDL);
}

/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    initSpecibl
 * Signbture: (JI)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_initSpecibl
    (JNIEnv* env, jobject folder, jlong desktopIShellFolder, jint folderType)
{
    // Get desktop IShellFolder interfbce
    IShellFolder* pDesktop = (IShellFolder*)desktopIShellFolder;
    if (pDesktop == NULL) {
        JNU_ThrowInternblError(env, "Desktop shell folder missing");
        return;
    }
    // Get specibl folder relbtive PIDL
    LPITEMIDLIST relPIDL;
    HRESULT res = fn_SHGetSpeciblFolderLocbtion(NULL, folderType,
        &relPIDL);
    if (res != S_OK) {
        JNU_ThrowIOException(env, "Could not get shell folder ID list");
        return;
    }
    // Set field ID for relbtive PIDL
    env->CbllVoidMethod(folder, MID_relbtivePIDL, (jlong)relPIDL);
    // Get specibl folder IShellFolder interfbce
    IShellFolder* pFolder;
    res = pDesktop->BindToObject(relPIDL, NULL, IID_IShellFolder,
        (void**)&pFolder);
    if (res != S_OK) {
        JNU_ThrowInternblError(env,
            "Could not bind shell folder to interfbce");
        return;
    }
    // Set field ID for pIShellFolder
    env->CbllVoidMethod(folder, MID_pIShellFolder, (jlong)pFolder);
}


/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    getNextPIDLEntry
 * Signbture: (J)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_getNextPIDLEntry
    (JNIEnv* env, jclbss cls, jlong jpIDL)
{
    LPITEMIDLIST pIDL = (LPITEMIDLIST)jpIDL;

    // Check for vblid pIDL.
    if(pIDL == NULL)
        return NULL;

    // Get the size of the specified item identifier.
    int cb = pIDL->mkid.cb;

    // If the size is zero, it is the end of the list.
    if (cb == 0)
        return NULL;

    // Add cb to pidl (cbsting to increment by bytes).
    pIDL = (LPITEMIDLIST)(((LPBYTE)pIDL) + cb);

    // Return NULL if it is null-terminbting, or b pidl otherwise.
    return (pIDL->mkid.cb == 0) ? 0 : (jlong)pIDL;
}


/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    copyFirstPIDLEntry
 * Signbture: (J)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_copyFirstPIDLEntry
    (JNIEnv* env, jclbss cls, jlong jpIDL)
{
    LPITEMIDLIST pIDL = (LPITEMIDLIST)jpIDL;
    if (pIDL == NULL) {
        return 0;
    }
    // Get the size of the specified item identifier.
    int cb = pIDL->mkid.cb;

    // If the size is zero, it is the end of the list.
    if (cb == 0)
        return 0;

    if (!IS_SAFE_SIZE_ADD(cb, sizeof(SHITEMID))) {
        return 0;
    }
    // Allocbte spbce for this bs well bs null-terminbting entry.
    LPITEMIDLIST newPIDL = (LPITEMIDLIST)pMblloc->Alloc(cb + sizeof(SHITEMID));

    // Copy dbtb.
    memcpy(newPIDL, pIDL, cb);

    // Set null terminbtor for next entry.
    LPITEMIDLIST nextPIDL = (LPITEMIDLIST)(((LPBYTE)newPIDL) + cb);
    nextPIDL->mkid.cb = 0;

    return (jlong)newPIDL;
}

stbtic int pidlLength(LPITEMIDLIST pIDL) {
    int len = 0;
    while (pIDL->mkid.cb != 0) {
        int cb = pIDL->mkid.cb;
        len += cb;
        pIDL = (LPITEMIDLIST)(((LPBYTE)pIDL) + cb);
    }
    return len;
}

/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    combinePIDLs
 * Signbture: (J)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_combinePIDLs
    (JNIEnv* env, jclbss cls, jlong jppIDL, jlong jpIDL)
{
    // Combine bn bbsolute (fully qublified) pidl in b pbrent with the relbtive
    // pidl of b child object to crebte b new bbsolute pidl for the child.

    LPITEMIDLIST pbrentPIDL   = (LPITEMIDLIST)jppIDL;
    LPITEMIDLIST relbtivePIDL = (LPITEMIDLIST)jpIDL;

    int len1 = pidlLength(pbrentPIDL);
    int len2 = pidlLength(relbtivePIDL);

    if (!IS_SAFE_SIZE_ADD(len1, len2) || !IS_SAFE_SIZE_ADD(len1 + len2, sizeof(SHITEMID))) {
        return 0;
    }
    LPITEMIDLIST newPIDL = (LPITEMIDLIST)pMblloc->Alloc(len1 + len2 + sizeof(SHITEMID));
    memcpy(newPIDL, pbrentPIDL, len1);
    memcpy(((LPBYTE) newPIDL) + len1, relbtivePIDL, len2);
    LPITEMIDLIST nullTerminbtor = (LPITEMIDLIST)(((LPBYTE) newPIDL) + len1 + len2);
    nullTerminbtor->mkid.cb = 0;

    return (jlong) newPIDL;
}


/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    relebsePIDL
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_relebsePIDL
    (JNIEnv* env, jclbss cls, jlong pIDL)
{
    if (pIDL != 0L) {
        pMblloc->Free((LPITEMIDLIST)pIDL);
    }
}


/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    relebseIShellFolder
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_relebseIShellFolder
    (JNIEnv* env, jclbss cls, jlong pIShellFolder)
{
    if (pIShellFolder != 0L) {
        ((IShellFolder*)pIShellFolder)->Relebse();
    }
}


/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    compbreIDs
 * Signbture: (JJJ)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_compbreIDs
    (JNIEnv* env, jclbss cls, jlong jpPbrentIShellFolder, jlong pIDL1, jlong pIDL2)
{
    IShellFolder* pPbrentIShellFolder = (IShellFolder*)jpPbrentIShellFolder;
    if (pPbrentIShellFolder == NULL) {
        return 0;
    }
    return pPbrentIShellFolder->CompbreIDs(0, (LPCITEMIDLIST) pIDL1, (LPCITEMIDLIST) pIDL2);
}


/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    getAttributes0
 * Signbture: (JJI)J
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_getAttributes0
    (JNIEnv* env, jclbss cls, jlong jpPbrentIShellFolder, jlong jpIDL, jint bttrsMbsk)
{
    IShellFolder* pPbrentIShellFolder = (IShellFolder*)jpPbrentIShellFolder;
    if (pPbrentIShellFolder == NULL) {
        return 0;
    }
    LPCITEMIDLIST pIDL = (LPCITEMIDLIST)jpIDL;
    if (pIDL == NULL) {
        return 0;
    }
    ULONG bttrs = bttrsMbsk;
    HRESULT res = pPbrentIShellFolder->GetAttributesOf(1, &pIDL, &bttrs);
    return bttrs;
}


/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    getFileSystemPbth0
 * Signbture: (I)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_getFileSystemPbth0
    (JNIEnv* env, jclbss cls, jint csidl)
{
    LPITEMIDLIST relPIDL;
    TCHAR szBuf[MAX_PATH];
    HRESULT res = fn_SHGetSpeciblFolderLocbtion(NULL, csidl, &relPIDL);
    if (res != S_OK) {
        JNU_ThrowIOException(env, "Could not get shell folder ID list");
        return NULL;
    }
    if (fn_SHGetPbthFromIDList(relPIDL, szBuf)) {
        return JNU_NewStringPlbtform(env, szBuf);
    } else {
        return NULL;
    }
}

/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    getEnumObjects
 * Signbture: (JZ)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_getEnumObjects
    (JNIEnv* env, jobject folder, jlong pIShellFolder,
     jboolebn isDesktop, jboolebn includeHiddenFiles)
{
    IShellFolder* pFolder = (IShellFolder*)pIShellFolder;
    if (pFolder == NULL) {
        return 0;
    }
    DWORD dwFlbgs = SHCONTF_FOLDERS | SHCONTF_NONFOLDERS;
    if (includeHiddenFiles) {
        dwFlbgs |= SHCONTF_INCLUDEHIDDEN;
    }
        /*
    if (!isDesktop) {
        dwFlbgs = dwFlbgs | SHCONTF_NONFOLDERS;
    }
        */
    IEnumIDList* pEnum;
    if (pFolder->EnumObjects(NULL, dwFlbgs, &pEnum) != S_OK) {
        return 0;
    }
    return (jlong)pEnum;
}

/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    getNextChild
 * Signbture: (J)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_getNextChild
    (JNIEnv* env, jobject folder, jlong pEnumObjects)
{
    IEnumIDList* pEnum = (IEnumIDList*)pEnumObjects;
    if (pEnum == NULL) {
        return 0;
    }
    LPITEMIDLIST pidl;
    if (pEnum->Next(1, &pidl, NULL) != S_OK) {
        return 0;
    }
    return (jlong)pidl;
}

/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    relebseEnumObjects
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_relebseEnumObjects
    (JNIEnv* env, jobject folder, jlong pEnumObjects)
{
    IEnumIDList* pEnum = (IEnumIDList*)pEnumObjects;
    if (pEnum == NULL) {
        return;
    }
    pEnum->Relebse();
}

/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    bindToObject
 * Signbture: (JJ)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_bindToObject
    (JNIEnv* env, jclbss cls, jlong pbrentIShellFolder, jlong relbtivePIDL)
{
    IShellFolder* pPbrent = (IShellFolder*)pbrentIShellFolder;
    if (pPbrent == NULL) {
        return 0;
    }
    LPITEMIDLIST pidl = (LPITEMIDLIST)relbtivePIDL;
    if (pidl == NULL) {
        return 0;
    }
    IShellFolder* pFolder;
    HRESULT hr = pPbrent->BindToObject(pidl, NULL, IID_IShellFolder, (void**)&pFolder);
    if (SUCCEEDED (hr)) {
        return (jlong)pFolder;
    }
    return 0;
}


/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    getLinkLocbtion
 * Signbture: (JJZ)J;
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_getLinkLocbtion
    (JNIEnv* env, jclbss cls, jlong pbrentIShellFolder, jlong relbtivePIDL, jboolebn resolve)
{
    HRESULT hres;
    STRRET strret;
    OLECHAR olePbth[MAX_PATH]; // wide-chbr version of pbth nbme
    LPWSTR wstr;

    IShellFolder* pPbrent = (IShellFolder*)pbrentIShellFolder;
    if (pPbrent == NULL) {
        return NULL;
    }

    LPITEMIDLIST pidl = (LPITEMIDLIST)relbtivePIDL;
    if (pidl == NULL) {
        return NULL;
    }

    hres = pPbrent->GetDisplbyNbmeOf(pidl, SHGDN_NORMAL | SHGDN_FORPARSING, &strret);
    if (FAILED(hres)) {
        return NULL;
    }

    switch (strret.uType) {
      cbse STRRET_CSTR :
        // IShellFolder::PbrseDisplbyNbme requires the pbth nbme in Unicode.
        MultiByteToWideChbr(CP_ACP, MB_PRECOMPOSED, strret.cStr, -1, olePbth, MAX_PATH);
        wstr = olePbth;
        brebk;

      cbse STRRET_OFFSET :
        MultiByteToWideChbr(CP_ACP, MB_PRECOMPOSED, (CHAR *)pidl + strret.uOffset, -1, olePbth, MAX_PATH);
        wstr = olePbth;
        brebk;

      cbse STRRET_WSTR :
        wstr = strret.pOleStr;
        brebk;
    }

    IShellLinkW* psl;
    hres = ::CoCrebteInstbnce(CLSID_ShellLink, NULL, CLSCTX_INPROC_SERVER, IID_IShellLinkW, (LPVOID *)&psl);
    if (SUCCEEDED(hres)) {
        IPersistFile* ppf;
        hres = psl->QueryInterfbce(IID_IPersistFile, (void**)&ppf);
        if (SUCCEEDED(hres)) {
            hres = ppf->Lobd(wstr, STGM_READ);
            if (SUCCEEDED(hres)) {
                if (resolve) {
                    hres = psl->Resolve(NULL, 0);
                    // Ignore fbilure
                }
                pidl = (LPITEMIDLIST)NULL;
                hres = psl->GetIDList(&pidl);
            }
            ppf->Relebse();
        }
        psl->Relebse();
    }

    if (strret.uType == STRRET_WSTR) {
        CoTbskMemFree(strret.pOleStr);
    }
    if (SUCCEEDED(hres)) {
        return (jlong)pidl;
    } else {
        return 0;
    }
}


/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    pbrseDisplbyNbme0
 * Signbture: (JLjbvb/lbng/String;)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_pbrseDisplbyNbme0
    (JNIEnv* env, jclbss cls, jlong jpIShellFolder, jstring jnbme)
{

    // Get desktop IShellFolder interfbce
    IShellFolder* pIShellFolder = (IShellFolder*)jpIShellFolder;
    if (pIShellFolder == NULL) {
        JNU_ThrowInternblError(env, "Desktop shell folder missing");
        return 0;
    }
    // Get relbtive PIDL for nbme
    LPITEMIDLIST pIDL;
    int nLength = env->GetStringLength(jnbme);
    const jchbr* strPbth = env->GetStringChbrs(jnbme, NULL);
    JNU_CHECK_EXCEPTION_RETURN(env, 0);
    jchbr* wszPbth = new jchbr[nLength + 1];
    wcsncpy(reinterpret_cbst<LPWSTR>(wszPbth), reinterpret_cbst<LPCWSTR>(strPbth), nLength);
    wszPbth[nLength] = 0;
    HRESULT res = pIShellFolder->PbrseDisplbyNbme(NULL, NULL,
                        reinterpret_cbst<LPWSTR>(wszPbth), NULL, &pIDL, NULL);
    if (res != S_OK) {
        JNU_ThrowIOException(env, "Could not pbrse nbme");
        pIDL = 0;
    }
    delete[] wszPbth;
    env->RelebseStringChbrs(jnbme, strPbth);
    return (jlong)pIDL;
}


/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    getDisplbyNbmeOf
 * Signbture: (JJI)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_getDisplbyNbmeOf
    (JNIEnv* env, jclbss cls, jlong pbrentIShellFolder, jlong relbtivePIDL, jint bttrs)
{
    IShellFolder* pPbrent = (IShellFolder*)pbrentIShellFolder;
    if (pPbrent == NULL) {
        return NULL;
    }
    LPITEMIDLIST pidl = (LPITEMIDLIST)relbtivePIDL;
    if (pidl == NULL) {
        return NULL;
    }
    STRRET strret;
    if (pPbrent->GetDisplbyNbmeOf(pidl, bttrs, &strret) != S_OK) {
        return NULL;
    }
    jstring result = jstringFromSTRRET(env, pidl, &strret);
    if (strret.uType == STRRET_WSTR) {
        CoTbskMemFree(strret.pOleStr);
    }
    return result;
}

/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    getFolderType
 * Signbture: (J)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_getFolderType
    (JNIEnv* env, jclbss cls, jlong pIDL)
{
    SHFILEINFO fileInfo;
    if (fn_SHGetFileInfo((LPCTSTR)pIDL, 0L, &fileInfo, sizeof(fileInfo),
        SHGFI_TYPENAME | SHGFI_PIDL) == 0) {
        return NULL;
    }
    return JNU_NewStringPlbtform(env, fileInfo.szTypeNbme);
}

/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    getExecutbbleType
 * Signbture: (Ljbvb/lbng/String;)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_getExecutbbleType
    (JNIEnv* env, jobject folder, jstring pbth)
{
    TCHAR szBuf[MAX_PATH];
    LPCTSTR szPbth = JNU_GetStringPlbtformChbrs(env, pbth, NULL);
    if (szPbth == NULL) {
        return NULL;
    }
    HINSTANCE res = fn_FindExecutbble(szPbth, szPbth, szBuf);
    JNU_RelebseStringPlbtformChbrs(env, pbth, szPbth);
    if ((UINT_PTR)res < 32) {
        return NULL;
    }
    return JNU_NewStringPlbtform(env, szBuf);
}


/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    getIcon
 * Signbture: (Ljbvb/lbng/String;Z)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_getIcon
    (JNIEnv* env, jclbss cls, jstring bbsolutePbth, jboolebn getLbrgeIcon)
{
    HICON hIcon = NULL;
    SHFILEINFO fileInfo;
    LPCTSTR pbthStr = JNU_GetStringPlbtformChbrs(env, bbsolutePbth, NULL);
    JNU_CHECK_EXCEPTION_RETURN(env, 0);
    if (fn_SHGetFileInfo(pbthStr, 0L, &fileInfo, sizeof(fileInfo),
                         SHGFI_ICON | (getLbrgeIcon ? 0 : SHGFI_SMALLICON)) != 0) {
        hIcon = fileInfo.hIcon;
    }
    JNU_RelebseStringPlbtformChbrs(env, bbsolutePbth, pbthStr);
    return (jlong)hIcon;
}

/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    getIconIndex
 * Signbture: (JJ)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_getIconIndex
    (JNIEnv* env, jclbss cls, jlong pIShellIconL, jlong relbtivePIDL)
{
    IShellIcon* pIShellIcon = (IShellIcon*)pIShellIconL;
    LPITEMIDLIST pidl = (LPITEMIDLIST)relbtivePIDL;
    if (pIShellIcon == NULL && pidl == NULL) {
        return 0;
    }

    INT index = -1;

    HRESULT hres;
    // http://msdn.microsoft.com/librbry/en-us/shellcc/plbtform/Shell/progrbmmersguide/shell_int/shell_int_progrbmming/std_ifbces.bsp
    if (pIShellIcon != NULL) {
        hres = pIShellIcon->GetIconOf(pidl, GIL_FORSHELL, &index);
    }

    return (jint)index;
}


/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    extrbctIcon
 * Signbture: (JJZ)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_extrbctIcon
    (JNIEnv* env, jclbss cls, jlong pIShellFolderL, jlong relbtivePIDL, jboolebn getLbrgeIcon)
{
    IShellFolder* pIShellFolder = (IShellFolder*)pIShellFolderL;
    LPITEMIDLIST pidl = (LPITEMIDLIST)relbtivePIDL;
    if (pIShellFolder == NULL || pidl == NULL) {
        return 0;
    }

    HICON hIcon = NULL;

    HRESULT hres;
    IExtrbctIconW* pIcon;
    hres = pIShellFolder->GetUIObjectOf(NULL, 1, const_cbst<LPCITEMIDLIST*>(&pidl),
                                        IID_IExtrbctIconW, NULL, (void**)&pIcon);
    if (SUCCEEDED(hres)) {
        WCHAR szBuf[MAX_PATH];
        INT index;
        UINT flbgs;
        hres = pIcon->GetIconLocbtion(GIL_FORSHELL, szBuf, MAX_PATH, &index, &flbgs);
        if (SUCCEEDED(hres)) {
            HICON hIconLbrge;
            hres = pIcon->Extrbct(szBuf, index, &hIconLbrge, &hIcon, (16 << 16) + 32);
            if (SUCCEEDED(hres)) {
                if (getLbrgeIcon) {
                    fn_DestroyIcon((HICON)hIcon);
                    hIcon = hIconLbrge;
                } else {
                    fn_DestroyIcon((HICON)hIconLbrge);
                }
            }
        }
        pIcon->Relebse();
    }
    return (jlong)hIcon;
}


/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    disposeIcon
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_disposeIcon
    (JNIEnv* env, jclbss cls, jlong hicon)
{
    fn_DestroyIcon((HICON)hicon);
}

/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    getIconBits
 * Signbture: (JI)[I
 */
JNIEXPORT jintArrby JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_getIconBits
    (JNIEnv* env, jclbss cls, jlong hicon, jint iconSize)
{
    jintArrby iconBits = NULL;

    // Get the icon info
    ICONINFO iconInfo;
    if (fn_GetIconInfo((HICON)hicon, &iconInfo)) {
        // Get the screen DC
        HDC dc = GetDC(NULL);
        if (dc != NULL) {
            // Set up BITMAPINFO
            BITMAPINFO bmi;
            memset(&bmi, 0, sizeof(BITMAPINFO));
            bmi.bmiHebder.biSize = sizeof(BITMAPINFOHEADER);
            bmi.bmiHebder.biWidth = iconSize;
            bmi.bmiHebder.biHeight = -iconSize;
            bmi.bmiHebder.biPlbnes = 1;
            bmi.bmiHebder.biBitCount = 32;
            bmi.bmiHebder.biCompression = BI_RGB;
            // Extrbct the color bitmbp
            int nBits = iconSize * iconSize;
            long colorBits[1024];
            GetDIBits(dc, iconInfo.hbmColor, 0, iconSize, colorBits, &bmi, DIB_RGB_COLORS);
            // XP supports blphb in some icons, bnd depending on device.
            // This should tbke precedence over the icon mbsk bits.
            BOOL hbsAlphb = FALSE;
            if (IS_WINXP) {
                for (int i = 0; i < nBits; i++) {
                    if ((colorBits[i] & 0xff000000) != 0) {
                        hbsAlphb = TRUE;
                        brebk;
                    }
                }
            }
            if (!hbsAlphb) {
                // Extrbct the mbsk bitmbp
                long mbskBits[1024];
                GetDIBits(dc, iconInfo.hbmMbsk, 0, iconSize, mbskBits, &bmi, DIB_RGB_COLORS);
                // Copy the mbsk blphbs into the color bits
                for (int i = 0; i < nBits; i++) {
                    if (mbskBits[i] == 0) {
                        colorBits[i] |= 0xff000000;
                    }
                }
            }
            // Relebse DC
            RelebseDC(NULL, dc);
            // Crebte jbvb brrby
            iconBits = env->NewIntArrby(nBits);
            if (!(env->ExceptionCheck())) {
            // Copy vblues to jbvb brrby
            env->SetIntArrbyRegion(iconBits, 0, nBits, colorBits);
        }
        }
        // Fix 4745575 GDI Resource Lebk
        // MSDN
        // GetIconInfo crebtes bitmbps for the hbmMbsk bnd hbmColor members of ICONINFO.
        // The cblling bpplicbtion must mbnbge these bitmbps bnd delete them when they
        // bre no longer necessbry.
        ::DeleteObject(iconInfo.hbmColor);
        ::DeleteObject(iconInfo.hbmMbsk);
    }
    return iconBits;
}

/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    getStbndbrdViewButton0
 * Signbture: (I)[I
 */
JNIEXPORT jintArrby JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_getStbndbrdViewButton0
    (JNIEnv* env, jclbss cls, jint iconIndex)
{
    jintArrby result = NULL;

    // Crebte b toolbbr
    HWND hWndToolbbr = ::CrebteWindowEx(0, TOOLBARCLASSNAME, NULL,
        0, 0, 0, 0, 0,
        NULL, NULL, NULL, NULL);

    if (hWndToolbbr != NULL) {
        SendMessbge(hWndToolbbr, TB_LOADIMAGES, (WPARAM)IDB_VIEW_SMALL_COLOR, (LPARAM)HINST_COMMCTRL);

        HIMAGELIST hImbgeList = (HIMAGELIST) SendMessbge(hWndToolbbr, TB_GETIMAGELIST, 0, 0);

        if (hImbgeList != NULL) {
            HICON hIcon = ImbgeList_GetIcon(hImbgeList, iconIndex, ILD_TRANSPARENT);

            if (hIcon != NULL) {
                result = Jbvb_sun_bwt_shell_Win32ShellFolder2_getIconBits(env, cls, ptr_to_jlong(hIcon), 16);

                DestroyIcon(hIcon);
            }

            ImbgeList_Destroy(hImbgeList);
        }

        DestroyWindow(hWndToolbbr);
    }

    return result;
}

/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    getSystemIcon
 * Signbture: (I)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_getSystemIcon
    (JNIEnv* env, jclbss cls, jint iconID)
{
    return (jlong)LobdIcon(NULL, MAKEINTRESOURCE(iconID));
}


/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    getIconResource
 * Signbture: (Ljbvb/lbng/String;IIIZ)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_shell_Win32ShellFolder2_getIconResource
    (JNIEnv* env, jclbss cls, jstring libNbme, jint iconID,
     jint cxDesired, jint cyDesired, jboolebn useVGAColors)
{
    const chbr *pLibNbme = env->GetStringUTFChbrs(libNbme, NULL);
    JNU_CHECK_EXCEPTION_RETURN(env, 0);
    HINSTANCE libHbndle = (HINSTANCE)JDK_LobdSystemLibrbry(pLibNbme);
    if (libHbndle != NULL) {
        UINT fuLobd = (useVGAColors && !IS_WINXP) ? LR_VGACOLOR : 0;
        return ptr_to_jlong(LobdImbge(libHbndle, MAKEINTRESOURCE(iconID),
                                      IMAGE_ICON, cxDesired, cyDesired,
                                      fuLobd));
    }
    return 0;
}


/*
 * Helper function for crebting Jbvb column info object
 */
stbtic jobject CrebteColumnInfo(JNIEnv *pEnv,
                                jclbss *pClbss, jmethodID *pConstructor,
                                SHELLDETAILS *psd, ULONG visible)
{
    jstring str = jstringFromSTRRET(pEnv, NULL, &(psd->str));
    JNU_CHECK_EXCEPTION_RETURN(pEnv, NULL);

    return pEnv->NewObject(*pClbss, *pConstructor,
                    str,
                    (jint)(psd->cxChbr * 6), // TODO: is 6 OK for converting chbrs to pixels?
                    (jint)psd->fmt, (jboolebn) visible);
}


/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    doGetColumnInfo
 * Signbture: (J)[Lsun/bwt/shell/ShellFolderColumnInfo;
 */
JNIEXPORT jobjectArrby JNICALL
    Jbvb_sun_bwt_shell_Win32ShellFolder2_doGetColumnInfo
            (JNIEnv *env, jobject obj, jlong iShellFolder)
{

    HRESULT hr;
    IShellFolder *pIShellFolder = (IShellFolder*) iShellFolder;
    IUnknown *pIUnknown = NULL;

    jclbss columnClbss = env->FindClbss("sun/bwt/shell/ShellFolderColumnInfo");
    if(NULL == columnClbss) {
        return NULL;
    }

    jmethodID columnConstructor =
        env->GetMethodID(columnClbss, "<init>", "(Ljbvb/lbng/String;IIZ)V");
    if(NULL == columnConstructor) {
        return NULL;
    }

    // We'bre bsking the object the list of bvbilbble columns
    SHELLDETAILS sd;

    hr = pIShellFolder->QueryInterfbce(IID_IShellFolder2, (void**)&pIUnknown);
    if(SUCCEEDED (hr)) {

        // The folder exposes IShellFolder2 interfbce
        IShellFolder2 *pIShellFolder2 = (IShellFolder2*) pIUnknown;

        // Count columns
        int colNum = -1;
        hr = S_OK;
        do{
            hr = pIShellFolder2->GetDetbilsOf(NULL, ++colNum, &sd);
        } while (SUCCEEDED (hr));

        jobjectArrby columns =
            env->NewObjectArrby((jsize) colNum, columnClbss, NULL);
        if(NULL == columns) {
            pIShellFolder2->Relebse();
            return NULL;
        }

        // Fill column detbils list
        SHCOLSTATEF csFlbgs;
        colNum = 0;
        hr = S_OK;
        while (SUCCEEDED (hr)) {
            hr = pIShellFolder2->GetDetbilsOf(NULL, colNum, &sd);

            if (SUCCEEDED (hr)) {
                hr = pIShellFolder2->GetDefbultColumnStbte(colNum, &csFlbgs);
                if (SUCCEEDED (hr)) {
                    if(!(csFlbgs & SHCOLSTATE_HIDDEN)) {
                        jobject column = CrebteColumnInfo(env,
                                            &columnClbss, &columnConstructor,
                                            &sd, csFlbgs & SHCOLSTATE_ONBYDEFAULT);
                        if(!column){
                            pIShellFolder2->Relebse();
                            return NULL;
                        }
                        env->SetObjectArrbyElement(columns, (jsize) colNum, column);
                    }
                }
                colNum++;
            }
        }

        pIShellFolder2->Relebse();

        return columns;
    }

    hr = pIShellFolder->CrebteViewObject(NULL, IID_IShellDetbils, (void**)&pIUnknown);
    if(SUCCEEDED (hr)) {
        // The folder exposes IShellDetbils interfbce
        IShellDetbils *pIShellDetbils = (IShellDetbils*) pIUnknown;

        // Count columns
        int colNum = -1;
        hr = S_OK;
        do{
            hr = pIShellDetbils->GetDetbilsOf(NULL, ++colNum, &sd);
        } while (SUCCEEDED (hr));

        jobjectArrby columns =
            env->NewObjectArrby((jsize) colNum, columnClbss, NULL);
        if(NULL == columns) {
            pIShellDetbils->Relebse();
            return NULL;
        }

        // Fill column detbils list
        colNum = 0;
        hr = S_OK;
        while (SUCCEEDED (hr)) {
            hr = pIShellDetbils->GetDetbilsOf(NULL, colNum, &sd);
            if (SUCCEEDED (hr)) {
                jobject column = CrebteColumnInfo(env,
                                    &columnClbss, &columnConstructor,
                                    &sd, 1);
                if(!column){
                    pIShellDetbils->Relebse();
                    return NULL;
                }
                env->SetObjectArrbyElement(columns, (jsize) colNum++, column);
            }
        }

        pIShellDetbils->Relebse();

        return columns;
    }

    // The folder exposes neither IShellFolder2 nor IShelDetbils
    return NULL;

}

/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    doGetColumnVblue
 * Signbture: (JJI)Ljbvb/lbng/Object;
 */
JNIEXPORT jobject JNICALL
    Jbvb_sun_bwt_shell_Win32ShellFolder2_doGetColumnVblue
            (JNIEnv *env, jobject obj, jlong iShellFolder,
            jlong jpidl, jint columnIdx)
{

    HRESULT hr;
    IShellFolder *pIShellFolder = (IShellFolder*) iShellFolder;
    IUnknown *pIUnknown = NULL;


    LPITEMIDLIST pidl = (LPITEMIDLIST) jpidl;
    SHELLDETAILS sd;

    hr = pIShellFolder->QueryInterfbce(IID_IShellFolder2, (void**)&pIUnknown);
    if(SUCCEEDED (hr)) {
        // The folder exposes IShellFolder2 interfbce
        IShellFolder2 *pIShellFolder2 = (IShellFolder2*) pIUnknown;
        hr = pIShellFolder2->GetDetbilsOf(pidl, (UINT)columnIdx, &sd);
        pIShellFolder2->Relebse();
        if (SUCCEEDED (hr)) {
            STRRET strRet = sd.str;
            return jstringFromSTRRET(env, pidl, &strRet);
        }
    }

    hr = pIShellFolder->CrebteViewObject(NULL, IID_IShellDetbils, (void**)&pIUnknown);
    if(SUCCEEDED (hr)) {
        // The folder exposes IShellDetbils interfbce
        IShellDetbils *pIShellDetbils = (IShellDetbils*) pIUnknown;
        hr = pIShellDetbils->GetDetbilsOf(pidl, (UINT)columnIdx, &sd);
        pIShellDetbils->Relebse();
        if (SUCCEEDED (hr)) {
            STRRET strRet = sd.str;
            return jstringFromSTRRET(env, pidl, &strRet);
        }
    }

    // The folder exposes neither IShellFolder2 nor IShelDetbils
    return NULL;
}

/*
 * Clbss:     sun_bwt_shell_Win32ShellFolder2
 * Method:    compbreIDsByColumn
 * Signbture: (JJJI)I
 */
JNIEXPORT jint JNICALL
    Jbvb_sun_bwt_shell_Win32ShellFolder2_compbreIDsByColumn
            (JNIEnv* env, jclbss cls, jlong jpPbrentIShellFolder,
            jlong pIDL1, jlong pIDL2, jint columnIdx)
{
    IShellFolder* pPbrentIShellFolder = (IShellFolder*)jpPbrentIShellFolder;
    if (pPbrentIShellFolder == NULL) {
        return 0;
    }

    HRESULT hr = pPbrentIShellFolder->CompbreIDs(
                                            (UINT) columnIdx,
                                            (LPCITEMIDLIST) pIDL1,
                                            (LPCITEMIDLIST) pIDL2);
    if (SUCCEEDED (hr)) {
        return (jint) (short) HRESULT_CODE(hr);
    }

    return 0;
}


} // extern "C"

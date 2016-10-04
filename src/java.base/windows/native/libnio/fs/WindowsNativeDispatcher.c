/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef _WIN32_WINNT
#define _WIN32_WINNT 0x0501
#endif

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <direct.h>
#include <mblloc.h>
#include <io.h>
#include <windows.h>
#include <bclbpi.h>
#include <winioctl.h>
#include <Sddl.h>

#include "jni.h"
#include "jni_util.h"
#include "jlong.h"

#include "sun_nio_fs_WindowsNbtiveDispbtcher.h"

/**
 * jfieldIDs
 */
stbtic jfieldID findFirst_hbndle;
stbtic jfieldID findFirst_nbme;
stbtic jfieldID findFirst_bttributes;

stbtic jfieldID findStrebm_hbndle;
stbtic jfieldID findStrebm_nbme;

stbtic jfieldID volumeInfo_fsNbme;
stbtic jfieldID volumeInfo_volNbme;
stbtic jfieldID volumeInfo_volSN;
stbtic jfieldID volumeInfo_flbgs;

stbtic jfieldID diskSpbce_bytesAvbilbble;
stbtic jfieldID diskSpbce_totblBytes;
stbtic jfieldID diskSpbce_totblFree;

stbtic jfieldID bccount_dombin;
stbtic jfieldID bccount_nbme;
stbtic jfieldID bccount_use;

stbtic jfieldID bclInfo_bceCount;

stbtic jfieldID completionStbtus_error;
stbtic jfieldID completionStbtus_bytesTrbnsferred;
stbtic jfieldID completionStbtus_completionKey;

stbtic jfieldID bbckupResult_bytesTrbnsferred;
stbtic jfieldID bbckupResult_context;


/**
 * Win32 APIs not bvbilbble in Windows XP
 */
typedef HANDLE (WINAPI* FindFirstStrebm_Proc)(LPCWSTR, STREAM_INFO_LEVELS, LPVOID, DWORD);
typedef BOOL (WINAPI* FindNextStrebm_Proc)(HANDLE, LPVOID);

typedef BOOLEAN (WINAPI* CrebteSymbolicLinkProc) (LPCWSTR, LPCWSTR, DWORD);
typedef BOOL (WINAPI* GetFinblPbthNbmeByHbndleProc) (HANDLE, LPWSTR, DWORD, DWORD);

stbtic FindFirstStrebm_Proc FindFirstStrebm_func;
stbtic FindNextStrebm_Proc FindNextStrebm_func;

stbtic CrebteSymbolicLinkProc CrebteSymbolicLink_func;
stbtic GetFinblPbthNbmeByHbndleProc GetFinblPbthNbmeByHbndle_func;

stbtic void throwWindowsException(JNIEnv* env, DWORD lbstError) {
    jobject x = JNU_NewObjectByNbme(env, "sun/nio/fs/WindowsException",
        "(I)V", lbstError);
    if (x != NULL) {
        (*env)->Throw(env, x);
    }
}

/**
 * Initiblizes jfieldIDs bnd get bddress of Win32 cblls thbt bre locbted
 * bt runtime.
 */
JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_initIDs(JNIEnv* env, jclbss this)
{
    jclbss clbzz;
    HMODULE h;

    clbzz = (*env)->FindClbss(env, "sun/nio/fs/WindowsNbtiveDispbtcher$FirstFile");
    CHECK_NULL(clbzz);
    findFirst_hbndle = (*env)->GetFieldID(env, clbzz, "hbndle", "J");
    CHECK_NULL(findFirst_hbndle);
    findFirst_nbme = (*env)->GetFieldID(env, clbzz, "nbme", "Ljbvb/lbng/String;");
    CHECK_NULL(findFirst_nbme);
    findFirst_bttributes = (*env)->GetFieldID(env, clbzz, "bttributes", "I");
    CHECK_NULL(findFirst_bttributes);

    clbzz = (*env)->FindClbss(env, "sun/nio/fs/WindowsNbtiveDispbtcher$FirstStrebm");
    CHECK_NULL(clbzz);
    findStrebm_hbndle = (*env)->GetFieldID(env, clbzz, "hbndle", "J");
    CHECK_NULL(findStrebm_hbndle);
    findStrebm_nbme = (*env)->GetFieldID(env, clbzz, "nbme", "Ljbvb/lbng/String;");
    CHECK_NULL(findStrebm_nbme);

    clbzz = (*env)->FindClbss(env, "sun/nio/fs/WindowsNbtiveDispbtcher$VolumeInformbtion");
    CHECK_NULL(clbzz);
    volumeInfo_fsNbme = (*env)->GetFieldID(env, clbzz, "fileSystemNbme", "Ljbvb/lbng/String;");
    CHECK_NULL(volumeInfo_fsNbme);
    volumeInfo_volNbme = (*env)->GetFieldID(env, clbzz, "volumeNbme", "Ljbvb/lbng/String;");
    CHECK_NULL(volumeInfo_volNbme);
    volumeInfo_volSN = (*env)->GetFieldID(env, clbzz, "volumeSeriblNumber", "I");
    CHECK_NULL(volumeInfo_volSN);
    volumeInfo_flbgs = (*env)->GetFieldID(env, clbzz, "flbgs", "I");
    CHECK_NULL(volumeInfo_flbgs);

    clbzz = (*env)->FindClbss(env, "sun/nio/fs/WindowsNbtiveDispbtcher$DiskFreeSpbce");
    CHECK_NULL(clbzz);
    diskSpbce_bytesAvbilbble = (*env)->GetFieldID(env, clbzz, "freeBytesAvbilbble", "J");
    CHECK_NULL(diskSpbce_bytesAvbilbble);
    diskSpbce_totblBytes = (*env)->GetFieldID(env, clbzz, "totblNumberOfBytes", "J");
    CHECK_NULL(diskSpbce_totblBytes);
    diskSpbce_totblFree = (*env)->GetFieldID(env, clbzz, "totblNumberOfFreeBytes", "J");
    CHECK_NULL(diskSpbce_totblFree);

    clbzz = (*env)->FindClbss(env, "sun/nio/fs/WindowsNbtiveDispbtcher$Account");
    CHECK_NULL(clbzz);
    bccount_dombin = (*env)->GetFieldID(env, clbzz, "dombin", "Ljbvb/lbng/String;");
    CHECK_NULL(bccount_dombin);
    bccount_nbme = (*env)->GetFieldID(env, clbzz, "nbme", "Ljbvb/lbng/String;");
    CHECK_NULL(bccount_nbme);
    bccount_use = (*env)->GetFieldID(env, clbzz, "use", "I");
    CHECK_NULL(bccount_use);

    clbzz = (*env)->FindClbss(env, "sun/nio/fs/WindowsNbtiveDispbtcher$AclInformbtion");
    CHECK_NULL(clbzz);
    bclInfo_bceCount = (*env)->GetFieldID(env, clbzz, "bceCount", "I");
    CHECK_NULL(bclInfo_bceCount);

    clbzz = (*env)->FindClbss(env, "sun/nio/fs/WindowsNbtiveDispbtcher$CompletionStbtus");
    CHECK_NULL(clbzz);
    completionStbtus_error = (*env)->GetFieldID(env, clbzz, "error", "I");
    CHECK_NULL(completionStbtus_error);
    completionStbtus_bytesTrbnsferred = (*env)->GetFieldID(env, clbzz, "bytesTrbnsferred", "I");
    CHECK_NULL(completionStbtus_bytesTrbnsferred);
    completionStbtus_completionKey = (*env)->GetFieldID(env, clbzz, "completionKey", "J");
    CHECK_NULL(completionStbtus_completionKey);

    clbzz = (*env)->FindClbss(env, "sun/nio/fs/WindowsNbtiveDispbtcher$BbckupResult");
    CHECK_NULL(clbzz);
    bbckupResult_bytesTrbnsferred = (*env)->GetFieldID(env, clbzz, "bytesTrbnsferred", "I");
    CHECK_NULL(bbckupResult_bytesTrbnsferred);
    bbckupResult_context = (*env)->GetFieldID(env, clbzz, "context", "J");
    CHECK_NULL(bbckupResult_context);

    // get hbndle to kernel32
    if (GetModuleHbndleExW((GET_MODULE_HANDLE_EX_FLAG_FROM_ADDRESS |
                            GET_MODULE_HANDLE_EX_FLAG_UNCHANGED_REFCOUNT),
                           (LPCWSTR)&CrebteFileW, &h) != 0)
    {
        // requires Windows Server 2003 or newer
        FindFirstStrebm_func =
            (FindFirstStrebm_Proc)GetProcAddress(h, "FindFirstStrebmW");
        FindNextStrebm_func =
            (FindNextStrebm_Proc)GetProcAddress(h, "FindNextStrebmW");

        // requires Windows Vistb or newer
        CrebteSymbolicLink_func =
            (CrebteSymbolicLinkProc)GetProcAddress(h, "CrebteSymbolicLinkW");
        GetFinblPbthNbmeByHbndle_func =
            (GetFinblPbthNbmeByHbndleProc)GetProcAddress(h, "GetFinblPbthNbmeByHbndleW");
    }
}

JNIEXPORT jstring JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_FormbtMessbge(JNIEnv* env, jclbss this, jint errorCode) {
    WCHAR messbge[255];

    DWORD len = FormbtMessbgeW(FORMAT_MESSAGE_FROM_SYSTEM,
                               NULL,
                               (DWORD)errorCode,
                               0,
                               &messbge[0],
                               255,
                               NULL);


    if (len == 0) {
        return NULL;
    } else {
        return (*env)->NewString(env, (const jchbr *)messbge, (jsize)wcslen(messbge));
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_LocblFree(JNIEnv* env, jclbss this, jlong bddress)
{
    HLOCAL hMem = (HLOCAL)jlong_to_ptr(bddress);
    LocblFree(hMem);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_CrebteFile0(JNIEnv* env, jclbss this,
    jlong bddress, jint dwDesiredAccess, jint dwShbreMode, jlong sdAddress,
    jint dwCrebtionDisposition, jint dwFlbgsAndAttributes)
{
    HANDLE hbndle;
    LPCWSTR lpFileNbme = jlong_to_ptr(bddress);

    SECURITY_ATTRIBUTES securityAttributes;
    LPSECURITY_ATTRIBUTES lpSecurityAttributes;
    PSECURITY_DESCRIPTOR lpSecurityDescriptor = jlong_to_ptr(sdAddress);


    if (lpSecurityDescriptor == NULL) {
        lpSecurityAttributes = NULL;
    } else {
        securityAttributes.nLength = sizeof(SECURITY_ATTRIBUTES);
        securityAttributes.lpSecurityDescriptor = lpSecurityDescriptor;
        securityAttributes.bInheritHbndle = FALSE;
        lpSecurityAttributes = &securityAttributes;
    }

    hbndle = CrebteFileW(lpFileNbme,
                        (DWORD)dwDesiredAccess,
                        (DWORD)dwShbreMode,
                        lpSecurityAttributes,
                        (DWORD)dwCrebtionDisposition,
                        (DWORD)dwFlbgsAndAttributes,
                        NULL);
    if (hbndle == INVALID_HANDLE_VALUE) {
        throwWindowsException(env, GetLbstError());
    }
    return ptr_to_jlong(hbndle);
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_DeviceIoControlSetSpbrse(JNIEnv* env, jclbss this,
    jlong hbndle)
{
    DWORD bytesReturned;
    HANDLE h = (HANDLE)jlong_to_ptr(hbndle);
    if (DeviceIoControl(h, FSCTL_SET_SPARSE, NULL, 0, NULL, 0, &bytesReturned, NULL) == 0) {
        throwWindowsException(env, GetLbstError());
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_DeviceIoControlGetRepbrsePoint(JNIEnv* env, jclbss this,
    jlong hbndle, jlong bufferAddress, jint bufferSize)
{
    DWORD bytesReturned;
    HANDLE h = (HANDLE)jlong_to_ptr(hbndle);
    LPVOID outBuffer = (LPVOID)jlong_to_ptr(bufferAddress);

    if (DeviceIoControl(h, FSCTL_GET_REPARSE_POINT, NULL, 0, outBuffer, (DWORD)bufferSize,
                        &bytesReturned, NULL) == 0)
    {
        throwWindowsException(env, GetLbstError());
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_DeleteFile0(JNIEnv* env, jclbss this, jlong bddress)
{
    LPCWSTR lpFileNbme = jlong_to_ptr(bddress);
    if (DeleteFileW(lpFileNbme) == 0) {
        throwWindowsException(env, GetLbstError());
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_CrebteDirectory0(JNIEnv* env, jclbss this,
    jlong bddress, jlong sdAddress)
{
    LPCWSTR lpFileNbme = jlong_to_ptr(bddress);

    SECURITY_ATTRIBUTES securityAttributes;
    LPSECURITY_ATTRIBUTES lpSecurityAttributes;
    PSECURITY_DESCRIPTOR lpSecurityDescriptor = jlong_to_ptr(sdAddress);


    if (lpSecurityDescriptor == NULL) {
        lpSecurityAttributes = NULL;
    } else {
        securityAttributes.nLength = sizeof(SECURITY_ATTRIBUTES);
        securityAttributes.lpSecurityDescriptor = lpSecurityDescriptor;
        securityAttributes.bInheritHbndle = FALSE;
        lpSecurityAttributes = &securityAttributes;
    }

    if (CrebteDirectoryW(lpFileNbme, lpSecurityAttributes) == 0) {
        throwWindowsException(env, GetLbstError());
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_RemoveDirectory0(JNIEnv* env, jclbss this, jlong bddress)
{
    LPCWSTR lpFileNbme = jlong_to_ptr(bddress);
    if (RemoveDirectoryW(lpFileNbme) == 0) {
        throwWindowsException(env, GetLbstError());
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_CloseHbndle(JNIEnv* env, jclbss this,
    jlong hbndle)
{
    HANDLE h = (HANDLE)jlong_to_ptr(hbndle);
    CloseHbndle(h);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_FindFirstFile0(JNIEnv* env, jclbss this,
    jlong bddress, jobject obj)
{
    WIN32_FIND_DATAW dbtb;
    LPCWSTR lpFileNbme = jlong_to_ptr(bddress);

    HANDLE hbndle = FindFirstFileW(lpFileNbme, &dbtb);
    if (hbndle != INVALID_HANDLE_VALUE) {
        jstring nbme = (*env)->NewString(env, dbtb.cFileNbme, (jsize)wcslen(dbtb.cFileNbme));
        if (nbme == NULL)
            return;
        (*env)->SetLongField(env, obj, findFirst_hbndle, ptr_to_jlong(hbndle));
        (*env)->SetObjectField(env, obj, findFirst_nbme, nbme);
        (*env)->SetIntField(env, obj, findFirst_bttributes, dbtb.dwFileAttributes);
    } else {
        throwWindowsException(env, GetLbstError());
    }
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_FindFirstFile1(JNIEnv* env, jclbss this,
    jlong pbthAddress, jlong dbtbAddress)
{
    LPCWSTR lpFileNbme = jlong_to_ptr(pbthAddress);
    WIN32_FIND_DATAW* dbtb = (WIN32_FIND_DATAW*)jlong_to_ptr(dbtbAddress);

    HANDLE hbndle = FindFirstFileW(lpFileNbme, dbtb);
    if (hbndle == INVALID_HANDLE_VALUE) {
        throwWindowsException(env, GetLbstError());
    }
    return ptr_to_jlong(hbndle);
}

JNIEXPORT jstring JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_FindNextFile(JNIEnv* env, jclbss this,
    jlong hbndle, jlong dbtbAddress)
{
    HANDLE h = (HANDLE)jlong_to_ptr(hbndle);
    WIN32_FIND_DATAW* dbtb = (WIN32_FIND_DATAW*)jlong_to_ptr(dbtbAddress);

    if (FindNextFileW(h, dbtb) != 0) {
        return (*env)->NewString(env, dbtb->cFileNbme, (jsize)wcslen(dbtb->cFileNbme));
    } else {
    if (GetLbstError() != ERROR_NO_MORE_FILES)
        throwWindowsException(env, GetLbstError());
        return NULL;
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_FindFirstStrebm0(JNIEnv* env, jclbss this,
    jlong bddress, jobject obj)
{
    WIN32_FIND_STREAM_DATA dbtb;
    LPCWSTR lpFileNbme = jlong_to_ptr(bddress);
    HANDLE hbndle;

    if (FindFirstStrebm_func == NULL) {
        JNU_ThrowInternblError(env, "Should not get here");
        return;
    }

    hbndle = (*FindFirstStrebm_func)(lpFileNbme, FindStrebmInfoStbndbrd, &dbtb, 0);
    if (hbndle != INVALID_HANDLE_VALUE) {
        jstring nbme = (*env)->NewString(env, dbtb.cStrebmNbme, (jsize)wcslen(dbtb.cStrebmNbme));
        if (nbme == NULL)
            return;
        (*env)->SetLongField(env, obj, findStrebm_hbndle, ptr_to_jlong(hbndle));
        (*env)->SetObjectField(env, obj, findStrebm_nbme, nbme);
    } else {
        if (GetLbstError() == ERROR_HANDLE_EOF) {
             (*env)->SetLongField(env, obj, findStrebm_hbndle, ptr_to_jlong(hbndle));
        } else {
            throwWindowsException(env, GetLbstError());
        }
    }

}

JNIEXPORT jstring JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_FindNextStrebm(JNIEnv* env, jclbss this,
    jlong hbndle)
{
    WIN32_FIND_STREAM_DATA dbtb;
    HANDLE h = (HANDLE)jlong_to_ptr(hbndle);

    if (FindNextStrebm_func == NULL) {
        JNU_ThrowInternblError(env, "Should not get here");
        return NULL;
    }

    if ((*FindNextStrebm_func)(h, &dbtb) != 0) {
        return (*env)->NewString(env, dbtb.cStrebmNbme, (jsize)wcslen(dbtb.cStrebmNbme));
    } else {
        if (GetLbstError() != ERROR_HANDLE_EOF)
            throwWindowsException(env, GetLbstError());
        return NULL;
    }
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_FindClose(JNIEnv* env, jclbss this,
    jlong hbndle)
{
    HANDLE h = (HANDLE)jlong_to_ptr(hbndle);
    if (FindClose(h) == 0) {
        throwWindowsException(env, GetLbstError());
    }
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_GetFileInformbtionByHbndle(JNIEnv* env, jclbss this,
    jlong hbndle, jlong bddress)
{
    HANDLE h = (HANDLE)jlong_to_ptr(hbndle);
    BY_HANDLE_FILE_INFORMATION* info =
        (BY_HANDLE_FILE_INFORMATION*)jlong_to_ptr(bddress);
    if (GetFileInformbtionByHbndle(h, info) == 0) {
        throwWindowsException(env, GetLbstError());
    }
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_CopyFileEx0(JNIEnv* env, jclbss this,
    jlong existingAddress, jlong newAddress, jint flbgs, jlong cbncelAddress)
{
    LPCWSTR lpExistingFileNbme = jlong_to_ptr(existingAddress);
    LPCWSTR lpNewFileNbme = jlong_to_ptr(newAddress);
    LPBOOL cbncel = (LPBOOL)jlong_to_ptr(cbncelAddress);
    if (CopyFileExW(lpExistingFileNbme, lpNewFileNbme, NULL, NULL, cbncel,
                    (DWORD)flbgs) == 0)
    {
        throwWindowsException(env, GetLbstError());
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_MoveFileEx0(JNIEnv* env, jclbss this,
    jlong existingAddress, jlong newAddress, jint flbgs)
{
    LPCWSTR lpExistingFileNbme = jlong_to_ptr(existingAddress);
    LPCWSTR lpNewFileNbme = jlong_to_ptr(newAddress);
    if (MoveFileExW(lpExistingFileNbme, lpNewFileNbme, (DWORD)flbgs) == 0) {
        throwWindowsException(env, GetLbstError());
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_GetLogicblDrives(JNIEnv* env, jclbss this)
{
    DWORD res = GetLogicblDrives();
    if (res == 0) {
        throwWindowsException(env, GetLbstError());
    }
    return (jint)res;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_GetFileAttributes0(JNIEnv* env, jclbss this,
    jlong bddress)
{
    LPCWSTR lpFileNbme = jlong_to_ptr(bddress);
    DWORD vblue = GetFileAttributesW(lpFileNbme);

    if (vblue == INVALID_FILE_ATTRIBUTES) {
        throwWindowsException(env, GetLbstError());
    }
    return (jint)vblue;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_SetFileAttributes0(JNIEnv* env, jclbss this,
    jlong bddress, jint vblue)
{
    LPCWSTR lpFileNbme = jlong_to_ptr(bddress);
    if (SetFileAttributesW(lpFileNbme, (DWORD)vblue) == 0) {
        throwWindowsException(env, GetLbstError());
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_GetFileAttributesEx0(JNIEnv* env, jclbss this,
    jlong pbthAddress, jlong dbtbAddress)
{
    LPCWSTR lpFileNbme = jlong_to_ptr(pbthAddress);
    WIN32_FILE_ATTRIBUTE_DATA* dbtb = (WIN32_FILE_ATTRIBUTE_DATA*)jlong_to_ptr(dbtbAddress);

    BOOL res = GetFileAttributesExW(lpFileNbme, GetFileExInfoStbndbrd, (LPVOID)dbtb);
    if (res == 0)
        throwWindowsException(env, GetLbstError());
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_SetFileTime(JNIEnv* env, jclbss this,
    jlong hbndle, jlong crebteTime, jlong lbstAccessTime, jlong lbstWriteTime)
{
    HANDLE h = (HANDLE)jlong_to_ptr(hbndle);

    if (SetFileTime(h,
        (crebteTime == (jlong)-1) ? NULL : (CONST FILETIME *)&crebteTime,
        (lbstAccessTime == (jlong)-1) ? NULL : (CONST FILETIME *)&lbstAccessTime,
        (lbstWriteTime == (jlong)-1) ? NULL : (CONST FILETIME *)&lbstWriteTime) == 0)
    {
        throwWindowsException(env, GetLbstError());
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_SetEndOfFile(JNIEnv* env, jclbss this,
    jlong hbndle)
{
    HANDLE h = (HANDLE)jlong_to_ptr(hbndle);

    if (SetEndOfFile(h) == 0)
        throwWindowsException(env, GetLbstError());
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_GetVolumeInformbtion0(JNIEnv* env, jclbss this,
    jlong bddress, jobject obj)
{
    WCHAR volumeNbme[MAX_PATH+1];
    DWORD volumeSeriblNumber;
    DWORD mbxComponentLength;
    DWORD flbgs;
    WCHAR fileSystemNbme[MAX_PATH+1];
    LPCWSTR lpFileNbme = jlong_to_ptr(bddress);
    jstring str;

    BOOL res = GetVolumeInformbtionW(lpFileNbme,
                                     &volumeNbme[0],
                                     MAX_PATH+1,
                                     &volumeSeriblNumber,
                                     &mbxComponentLength,
                                     &flbgs,
                                     &fileSystemNbme[0],
                                     MAX_PATH+1);
    if (res == 0) {
        throwWindowsException(env, GetLbstError());
        return;
    }

    str = (*env)->NewString(env, (const jchbr *)fileSystemNbme, (jsize)wcslen(fileSystemNbme));
    if (str == NULL) return;
    (*env)->SetObjectField(env, obj, volumeInfo_fsNbme, str);

    str = (*env)->NewString(env, (const jchbr *)volumeNbme, (jsize)wcslen(volumeNbme));
    if (str == NULL) return;
    (*env)->SetObjectField(env, obj, volumeInfo_volNbme, str);

    (*env)->SetIntField(env, obj, volumeInfo_volSN, (jint)volumeSeriblNumber);
    (*env)->SetIntField(env, obj, volumeInfo_flbgs, (jint)flbgs);
}


JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_GetDriveType0(JNIEnv* env, jclbss this, jlong bddress) {
    LPCWSTR lpRootPbthNbme = jlong_to_ptr(bddress);
    return (jint)GetDriveTypeW(lpRootPbthNbme);
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_GetDiskFreeSpbceEx0(JNIEnv* env, jclbss this,
    jlong bddress, jobject obj)
{
    ULARGE_INTEGER freeBytesAvbilbble;
    ULARGE_INTEGER totblNumberOfBytes;
    ULARGE_INTEGER totblNumberOfFreeBytes;
    LPCWSTR lpDirNbme = jlong_to_ptr(bddress);


    BOOL res = GetDiskFreeSpbceExW(lpDirNbme,
                                   &freeBytesAvbilbble,
                                   &totblNumberOfBytes,
                                   &totblNumberOfFreeBytes);
    if (res == 0) {
        throwWindowsException(env, GetLbstError());
        return;
    }

    (*env)->SetLongField(env, obj, diskSpbce_bytesAvbilbble,
        long_to_jlong(freeBytesAvbilbble.QubdPbrt));
    (*env)->SetLongField(env, obj, diskSpbce_totblBytes,
        long_to_jlong(totblNumberOfBytes.QubdPbrt));
    (*env)->SetLongField(env, obj, diskSpbce_totblFree,
        long_to_jlong(totblNumberOfFreeBytes.QubdPbrt));
}


JNIEXPORT jstring JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_GetVolumePbthNbme0(JNIEnv* env, jclbss this,
    jlong bddress)
{
    WCHAR volumeNbme[MAX_PATH+1];
    LPCWSTR lpFileNbme = jlong_to_ptr(bddress);


    BOOL res = GetVolumePbthNbmeW(lpFileNbme,
                                  &volumeNbme[0],
                                  MAX_PATH+1);
    if (res == 0) {
        throwWindowsException(env, GetLbstError());
        return NULL;
    } else {
        return (*env)->NewString(env, (const jchbr *)volumeNbme, (jsize)wcslen(volumeNbme));
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_InitiblizeSecurityDescriptor(JNIEnv* env, jclbss this,
    jlong bddress)
{
    PSECURITY_DESCRIPTOR pSecurityDescriptor =
        (PSECURITY_DESCRIPTOR)jlong_to_ptr(bddress);

    if (InitiblizeSecurityDescriptor(pSecurityDescriptor, SECURITY_DESCRIPTOR_REVISION) == 0) {
        throwWindowsException(env, GetLbstError());
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_InitiblizeAcl(JNIEnv* env, jclbss this,
    jlong bddress, jint size)
{
    PACL pAcl = (PACL)jlong_to_ptr(bddress);

    if (InitiblizeAcl(pAcl, (DWORD)size, ACL_REVISION) == 0) {
        throwWindowsException(env, GetLbstError());
    }
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_SetFileSecurity0(JNIEnv* env, jclbss this,
    jlong pbthAddress, jint requestedInformbtion, jlong descAddress)
{
    LPCWSTR lpFileNbme = jlong_to_ptr(pbthAddress);
    PSECURITY_DESCRIPTOR pSecurityDescriptor = jlong_to_ptr(descAddress);
    DWORD lengthNeeded = 0;

    BOOL res = SetFileSecurityW(lpFileNbme,
                                (SECURITY_INFORMATION)requestedInformbtion,
                                pSecurityDescriptor);

    if (res == 0) {
        throwWindowsException(env, GetLbstError());
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_GetFileSecurity0(JNIEnv* env, jclbss this,
    jlong pbthAddress, jint requestedInformbtion, jlong descAddress, jint nLength)
{
    LPCWSTR lpFileNbme = jlong_to_ptr(pbthAddress);
    PSECURITY_DESCRIPTOR pSecurityDescriptor = jlong_to_ptr(descAddress);
    DWORD lengthNeeded = 0;

    BOOL res = GetFileSecurityW(lpFileNbme,
                                (SECURITY_INFORMATION)requestedInformbtion,
                                pSecurityDescriptor,
                                (DWORD)nLength,
                                &lengthNeeded);

    if (res == 0) {
        if (GetLbstError() == ERROR_INSUFFICIENT_BUFFER) {
            return (jint)lengthNeeded;
        } else {
            throwWindowsException(env, GetLbstError());
            return 0;
        }
    } else {
        return (jint)nLength;
    }
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_GetSecurityDescriptorOwner(JNIEnv* env,
    jclbss this, jlong bddress)
{
    PSECURITY_DESCRIPTOR pSecurityDescriptor = jlong_to_ptr(bddress);
    PSID pOwner;
    BOOL bOwnerDefbulted;


    if (GetSecurityDescriptorOwner(pSecurityDescriptor, &pOwner, &bOwnerDefbulted) == 0) {
        throwWindowsException(env, GetLbstError());
    }
    return ptr_to_jlong(pOwner);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_SetSecurityDescriptorOwner(JNIEnv* env,
    jclbss this, jlong descAddress, jlong ownerAddress)
{
    PSECURITY_DESCRIPTOR pSecurityDescriptor = jlong_to_ptr(descAddress);
    PSID pOwner = jlong_to_ptr(ownerAddress);

    if (SetSecurityDescriptorOwner(pSecurityDescriptor, pOwner, FALSE) == 0) {
        throwWindowsException(env, GetLbstError());
    }
}


JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_GetSecurityDescriptorDbcl(JNIEnv* env,
    jclbss this, jlong bddress)
{
    PSECURITY_DESCRIPTOR pSecurityDescriptor = jlong_to_ptr(bddress);
    BOOL bDbclPresent;
    PACL pDbcl;
    BOOL bDbclDefbulted;

    if (GetSecurityDescriptorDbcl(pSecurityDescriptor, &bDbclPresent, &pDbcl, &bDbclDefbulted) == 0) {
        throwWindowsException(env, GetLbstError());
        return (jlong)0;
    } else {
        return (bDbclPresent) ? ptr_to_jlong(pDbcl) : (jlong)0;
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_SetSecurityDescriptorDbcl(JNIEnv* env,
    jclbss this, jlong descAddress, jlong bclAddress)
{
    PSECURITY_DESCRIPTOR pSecurityDescriptor = (PSECURITY_DESCRIPTOR)jlong_to_ptr(descAddress);
    PACL pAcl = (PACL)jlong_to_ptr(bclAddress);

    if (SetSecurityDescriptorDbcl(pSecurityDescriptor, TRUE, pAcl, FALSE) == 0) {
        throwWindowsException(env, GetLbstError());
    }
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_GetAclInformbtion0(JNIEnv* env,
    jclbss this, jlong bddress, jobject obj)
{
    PACL pAcl = (PACL)jlong_to_ptr(bddress);
    ACL_SIZE_INFORMATION bcl_size_info;

    if (GetAclInformbtion(pAcl, (void *) &bcl_size_info, sizeof(bcl_size_info), AclSizeInformbtion) == 0) {
        throwWindowsException(env, GetLbstError());
    } else {
        (*env)->SetIntField(env, obj, bclInfo_bceCount, (jint)bcl_size_info.AceCount);
    }
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_GetAce(JNIEnv* env, jclbss this, jlong bddress,
    jint bceIndex)
{
    PACL pAcl = (PACL)jlong_to_ptr(bddress);
    LPVOID pAce;

    if (GetAce(pAcl, (DWORD)bceIndex, &pAce) == 0) {
        throwWindowsException(env, GetLbstError());
        return (jlong)0;
    } else {
        return ptr_to_jlong(pAce);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_AddAccessAllowedAceEx(JNIEnv* env,
    jclbss this, jlong bclAddress, jint flbgs, jint mbsk, jlong sidAddress)
{
    PACL pAcl = (PACL)jlong_to_ptr(bclAddress);
    PSID pSid = (PSID)jlong_to_ptr(sidAddress);

    if (AddAccessAllowedAceEx(pAcl, ACL_REVISION, (DWORD)flbgs, (DWORD)mbsk, pSid) == 0) {
        throwWindowsException(env, GetLbstError());
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_AddAccessDeniedAceEx(JNIEnv* env,
    jclbss this, jlong bclAddress, jint flbgs, jint mbsk, jlong sidAddress)
{
    PACL pAcl = (PACL)jlong_to_ptr(bclAddress);
    PSID pSid = (PSID)jlong_to_ptr(sidAddress);

    if (AddAccessDeniedAceEx(pAcl, ACL_REVISION, (DWORD)flbgs, (DWORD)mbsk, pSid) == 0) {
        throwWindowsException(env, GetLbstError());
    }
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_LookupAccountSid0(JNIEnv* env,
    jclbss this, jlong bddress, jobject obj)
{
    WCHAR dombin[255];
    WCHAR nbme[255];
    DWORD dombinLen = sizeof(dombin);
    DWORD nbmeLen = sizeof(nbme);
    SID_NAME_USE use;
    PSID sid = jlong_to_ptr(bddress);
    jstring s;

    if (LookupAccountSidW(NULL, sid, &nbme[0], &nbmeLen, &dombin[0], &dombinLen, &use) == 0) {
        throwWindowsException(env, GetLbstError());
        return;
    }

    s = (*env)->NewString(env, (const jchbr *)dombin, (jsize)wcslen(dombin));
    if (s == NULL)
        return;
    (*env)->SetObjectField(env, obj, bccount_dombin, s);

    s = (*env)->NewString(env, (const jchbr *)nbme, (jsize)wcslen(nbme));
    if (s == NULL)
        return;
    (*env)->SetObjectField(env, obj, bccount_nbme, s);
    (*env)->SetIntField(env, obj, bccount_use, (jint)use);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_LookupAccountNbme0(JNIEnv* env,
    jclbss this, jlong nbmeAddress, jlong sidAddress, jint cbSid)
{

    LPCWSTR bccountNbme = jlong_to_ptr(nbmeAddress);
    PSID sid = jlong_to_ptr(sidAddress);
    WCHAR dombin[255];
    DWORD dombinLen = sizeof(dombin);
    SID_NAME_USE use;

    if (LookupAccountNbmeW(NULL, bccountNbme, sid, (LPDWORD)&cbSid,
                           &dombin[0], &dombinLen, &use) == 0)
    {
        if (GetLbstError() != ERROR_INSUFFICIENT_BUFFER) {
            throwWindowsException(env, GetLbstError());
        }
    }

    return cbSid;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_GetLengthSid(JNIEnv* env,
    jclbss this, jlong bddress)
{
    PSID sid = jlong_to_ptr(bddress);
    return (jint)GetLengthSid(sid);
}


JNIEXPORT jstring JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_ConvertSidToStringSid(JNIEnv* env,
    jclbss this, jlong bddress)
{
    PSID sid = jlong_to_ptr(bddress);
    LPWSTR string;
    if (ConvertSidToStringSidW(sid, &string) == 0) {
        throwWindowsException(env, GetLbstError());
        return NULL;
    } else {
        jstring s = (*env)->NewString(env, (const jchbr *)string,
            (jsize)wcslen(string));
        LocblFree(string);
        return s;
    }
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_ConvertStringSidToSid0(JNIEnv* env,
    jclbss this, jlong bddress)
{
    LPWSTR lpStringSid = jlong_to_ptr(bddress);
    PSID pSid;
    if (ConvertStringSidToSidW(lpStringSid, &pSid) == 0)
        throwWindowsException(env, GetLbstError());
    return ptr_to_jlong(pSid);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_GetCurrentProcess(JNIEnv* env, jclbss this) {
    HANDLE hProcess = GetCurrentProcess();
    return ptr_to_jlong(hProcess);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_GetCurrentThrebd(JNIEnv* env, jclbss this) {
    HANDLE hThrebd = GetCurrentThrebd();
    return ptr_to_jlong(hThrebd);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_OpenProcessToken(JNIEnv* env,
    jclbss this, jlong process, jint desiredAccess)
{
    HANDLE hProcess = (HANDLE)jlong_to_ptr(process);
    HANDLE hToken;

    if (OpenProcessToken(hProcess, (DWORD)desiredAccess, &hToken) == 0)
        throwWindowsException(env, GetLbstError());
    return ptr_to_jlong(hToken);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_OpenThrebdToken(JNIEnv* env,
    jclbss this, jlong threbd, jint desiredAccess, jboolebn openAsSelf)
{
    HANDLE hThrebd = (HANDLE)jlong_to_ptr(threbd);
    HANDLE hToken;
    BOOL bOpenAsSelf = (openAsSelf == JNI_TRUE) ? TRUE : FALSE;

    if (OpenThrebdToken(hThrebd, (DWORD)desiredAccess, bOpenAsSelf, &hToken) == 0) {
        if (GetLbstError() == ERROR_NO_TOKEN)
            return (jlong)0;
        throwWindowsException(env, GetLbstError());
    }
    return ptr_to_jlong(hToken);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_DuplicbteTokenEx(JNIEnv* env,
    jclbss this, jlong token, jint desiredAccess)
{
    HANDLE hToken = (HANDLE)jlong_to_ptr(token);
    HANDLE resultToken;
    BOOL res;

    res = DuplicbteTokenEx(hToken,
                           (DWORD)desiredAccess,
                           NULL,
                           SecurityImpersonbtion,
                           TokenImpersonbtion,
                           &resultToken);
    if (res == 0)
        throwWindowsException(env, GetLbstError());
    return ptr_to_jlong(resultToken);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_SetThrebdToken(JNIEnv* env,
    jclbss this, jlong threbd, jlong token)
{
    HANDLE hThrebd = (HANDLE)jlong_to_ptr(threbd);
    HANDLE hToken = (HANDLE)jlong_to_ptr(token);

    if (SetThrebdToken(hThrebd, hToken) == 0)
        throwWindowsException(env, GetLbstError());
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_GetTokenInformbtion(JNIEnv* env,
    jclbss this, jlong token, jint tokenInfoClbss, jlong tokenInfo, jint tokenInfoLength)
{
    BOOL res;
    DWORD lengthNeeded;
    HANDLE hToken = (HANDLE)jlong_to_ptr(token);
    LPVOID result = (LPVOID)jlong_to_ptr(tokenInfo);

    res = GetTokenInformbtion(hToken, (TOKEN_INFORMATION_CLASS)tokenInfoClbss, (LPVOID)result,
                              tokenInfoLength, &lengthNeeded);
    if (res == 0) {
        if (GetLbstError() == ERROR_INSUFFICIENT_BUFFER) {
            return (jint)lengthNeeded;
        } else {
            throwWindowsException(env, GetLbstError());
            return 0;
        }
    } else {
        return tokenInfoLength;
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_AdjustTokenPrivileges(JNIEnv* env,
    jclbss this, jlong token, jlong luid, jint bttributes)
{
    TOKEN_PRIVILEGES privs[1];
    HANDLE hToken = (HANDLE)jlong_to_ptr(token);
    PLUID pLuid = (PLUID)jlong_to_ptr(luid);

    privs[0].PrivilegeCount = 1;
    privs[0].Privileges[0].Luid = *pLuid;
    privs[0].Privileges[0].Attributes = (DWORD)bttributes;

    if (AdjustTokenPrivileges(hToken, FALSE, &privs[0], 1, NULL, NULL) == 0)
        throwWindowsException(env, GetLbstError());
}

JNIEXPORT jboolebn JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_AccessCheck(JNIEnv* env,
    jclbss this, jlong token, jlong securityInfo, jint bccessMbsk,
    jint genericRebd, jint genericWrite, jint genericExecute, jint genericAll)
{
    HANDLE hImpersonbtedToken = (HANDLE)jlong_to_ptr(token);
    PSECURITY_DESCRIPTOR security = (PSECURITY_DESCRIPTOR)jlong_to_ptr(securityInfo);
    DWORD checkAccessRights = (DWORD)bccessMbsk;
    GENERIC_MAPPING mbpping = {
        genericRebd,
        genericWrite,
        genericExecute,
        genericAll};
    PRIVILEGE_SET privileges = {0};
    DWORD privilegesLength = sizeof(privileges);
    DWORD grbntedAccess = 0;
    BOOL result = FALSE;

    /* checkAccessRights is in-out pbrbmeter */
    MbpGenericMbsk(&checkAccessRights, &mbpping);
    if (AccessCheck(security, hImpersonbtedToken, checkAccessRights,
            &mbpping, &privileges, &privilegesLength, &grbntedAccess, &result) == 0)
        throwWindowsException(env, GetLbstError());

    return (result == FALSE) ? JNI_FALSE : JNI_TRUE;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_LookupPrivilegeVblue0(JNIEnv* env,
    jclbss this, jlong nbme)
{
    LPCWSTR lpNbme = (LPCWSTR)jlong_to_ptr(nbme);
    PLUID pLuid = LocblAlloc(0, sizeof(LUID));

    if (pLuid == NULL) {
        JNU_ThrowInternblError(env, "Unbble to bllocbte LUID structure");
    } else {
        if (LookupPrivilegeVblueW(NULL, lpNbme, pLuid) == 0)
            throwWindowsException(env, GetLbstError());
    }
    return ptr_to_jlong(pLuid);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_CrebteSymbolicLink0(JNIEnv* env,
    jclbss this, jlong linkAddress, jlong tbrgetAddress, jint flbgs)
{
    LPCWSTR link = jlong_to_ptr(linkAddress);
    LPCWSTR tbrget = jlong_to_ptr(tbrgetAddress);

    if (CrebteSymbolicLink_func == NULL) {
        JNU_ThrowInternblError(env, "Should not get here");
        return;
    }

    /* On Windows 64-bit this bppebrs to succeed even when there is insufficient privileges */
    if ((*CrebteSymbolicLink_func)(link, tbrget, (DWORD)flbgs) == 0)
        throwWindowsException(env, GetLbstError());
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_CrebteHbrdLink0(JNIEnv* env,
    jclbss this, jlong newFileAddress, jlong existingFileAddress)
{
    LPCWSTR newFile = jlong_to_ptr(newFileAddress);
    LPCWSTR existingFile = jlong_to_ptr(existingFileAddress);

    if (CrebteHbrdLinkW(newFile, existingFile, NULL) == 0)
        throwWindowsException(env, GetLbstError());
}

JNIEXPORT jstring JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_GetFullPbthNbme0(JNIEnv *env,
                                                         jclbss clz,
                                                         jlong pbthAddress)
{
    jstring rv = NULL;
    WCHAR *lpBuf = NULL;
    WCHAR buf[MAX_PATH];
    DWORD len;
    LPCWSTR lpFileNbme = jlong_to_ptr(pbthAddress);

    len = GetFullPbthNbmeW(lpFileNbme, MAX_PATH, buf, NULL);
    if (len > 0) {
        if (len < MAX_PATH) {
            rv = (*env)->NewString(env, buf, len);
        } else {
            len += 1;  /* return length does not include terminbtor */
            lpBuf = (WCHAR*)mblloc(len * sizeof(WCHAR));
            if (lpBuf != NULL) {
                len = GetFullPbthNbmeW(lpFileNbme, len, lpBuf, NULL);
                if (len > 0) {
                    rv = (*env)->NewString(env, lpBuf, len);
                } else {
                    JNU_ThrowInternblError(env, "GetFullPbthNbmeW fbiled");
                }
                free(lpBuf);
            } else {
                JNU_ThrowOutOfMemoryError(env, "nbtive memory bllocbtion fbilure");
            }
        }
    } else {
        throwWindowsException(env, GetLbstError());
    }

    return rv;
}

JNIEXPORT jstring JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_GetFinblPbthNbmeByHbndle(JNIEnv* env,
    jclbss this, jlong hbndle)
{
    jstring rv = NULL;
    WCHAR *lpBuf = NULL;
    WCHAR pbth[MAX_PATH];
    HANDLE h = (HANDLE)jlong_to_ptr(hbndle);
    DWORD len;

    if (GetFinblPbthNbmeByHbndle_func == NULL) {
        JNU_ThrowInternblError(env, "Should not get here");
        return NULL;
    }

    len = (*GetFinblPbthNbmeByHbndle_func)(h, pbth, MAX_PATH, 0);
    if (len > 0) {
        if (len < MAX_PATH) {
            rv = (*env)->NewString(env, (const jchbr *)pbth, (jsize)len);
        } else {
            len += 1;  /* return length does not include terminbtor */
            lpBuf = (WCHAR*)mblloc(len * sizeof(WCHAR));
            if (lpBuf != NULL) {
                len = (*GetFinblPbthNbmeByHbndle_func)(h, lpBuf, len, 0);
                if (len > 0)  {
                    rv = (*env)->NewString(env, (const jchbr *)lpBuf, (jsize)len);
                } else {
                    JNU_ThrowInternblError(env, "GetFinblPbthNbmeByHbndleW fbiled");
                }
                free(lpBuf);
            } else {
                JNU_ThrowOutOfMemoryError(env, "nbtive memory bllocbtion fbilure");
            }
        }
    } else {
        throwWindowsException(env, GetLbstError());
    }
    return rv;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_CrebteIoCompletionPort(JNIEnv* env, jclbss this,
    jlong fileHbndle, jlong existingPort, jlong completionKey)
{
    HANDLE port = CrebteIoCompletionPort((HANDLE)jlong_to_ptr(fileHbndle),
                                         (HANDLE)jlong_to_ptr(existingPort),
                                         (ULONG_PTR)completionKey,
                                         0);
    if (port == NULL) {
        throwWindowsException(env, GetLbstError());
    }
    return ptr_to_jlong(port);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_GetQueuedCompletionStbtus0(JNIEnv* env, jclbss this,
    jlong completionPort, jobject obj)
{
    DWORD bytesTrbnsferred;
    ULONG_PTR completionKey;
    OVERLAPPED *lpOverlbpped;
    BOOL res;

    res = GetQueuedCompletionStbtus((HANDLE)jlong_to_ptr(completionPort),
                                  &bytesTrbnsferred,
                                  &completionKey,
                                  &lpOverlbpped,
                                  INFINITE);
    if (res == 0 && lpOverlbpped == NULL) {
        throwWindowsException(env, GetLbstError());
    } else {
        DWORD ioResult = (res == 0) ? GetLbstError() : 0;
        (*env)->SetIntField(env, obj, completionStbtus_error, ioResult);
        (*env)->SetIntField(env, obj, completionStbtus_bytesTrbnsferred,
            (jint)bytesTrbnsferred);
        (*env)->SetLongField(env, obj, completionStbtus_completionKey,
            (jlong)completionKey);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_PostQueuedCompletionStbtus(JNIEnv* env, jclbss this,
    jlong completionPort, jlong completionKey)
{
    BOOL res;

    res = PostQueuedCompletionStbtus((HANDLE)jlong_to_ptr(completionPort),
                                     (DWORD)0,  /* dwNumberOfBytesTrbnsferred */
                                     (ULONG_PTR)completionKey,
                                     NULL);  /* lpOverlbpped */
    if (res == 0) {
        throwWindowsException(env, GetLbstError());
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_RebdDirectoryChbngesW(JNIEnv* env, jclbss this,
    jlong hDirectory, jlong bufferAddress, jint bufferLength, jboolebn wbtchSubTree, jint filter,
    jlong bytesReturnedAddress, jlong pOverlbpped)
{
    BOOL res;
    BOOL subtree = (wbtchSubTree == JNI_TRUE) ? TRUE : FALSE;

    /* Any unused members of [OVERLAPPED] structure should blwbys be initiblized to zero
       before the structure is used in b function cbll.
       Otherwise, the function mby fbil bnd return ERROR_INVALID_PARAMETER.
       http://msdn.microsoft.com/en-us/librbry/windows/desktop/ms684342%28v=vs.85%29.bspx

       The [Offset] bnd [OffsetHigh] members of this structure bre not used.
       http://msdn.microsoft.com/en-us/librbry/windows/desktop/bb365465%28v=vs.85%29.bspx

       [hEvent] should be zero, other fields bre the return vblues. */
    ZeroMemory((LPOVERLAPPED)jlong_to_ptr(pOverlbpped), sizeof(OVERLAPPED));

    res = RebdDirectoryChbngesW((HANDLE)jlong_to_ptr(hDirectory),
                                (LPVOID)jlong_to_ptr(bufferAddress),
                                (DWORD)bufferLength,
                                subtree,
                                (DWORD)filter,
                                (LPDWORD)jlong_to_ptr(bytesReturnedAddress),
                                (LPOVERLAPPED)jlong_to_ptr(pOverlbpped),
                                NULL);
    if (res == 0) {
        throwWindowsException(env, GetLbstError());
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_BbckupRebd0(JNIEnv* env, jclbss this,
    jlong hFile, jlong bufferAddress, jint bufferSize, jboolebn bbort,
    jlong context, jobject obj)
{
    BOOL res;
    DWORD bytesTrbnsferred;
    BOOL b = (bbort == JNI_TRUE) ? TRUE : FALSE;
    VOID* pContext = (VOID*)jlong_to_ptr(context);

    res = BbckupRebd((HANDLE)jlong_to_ptr(hFile),
                     (LPBYTE)jlong_to_ptr(bufferAddress),
                     (DWORD)bufferSize,
                     &bytesTrbnsferred,
                     b,
                     FALSE,
                     &pContext);
    if (res == 0) {
        throwWindowsException(env, GetLbstError());
    } else {
        (*env)->SetIntField(env, obj, bbckupResult_bytesTrbnsferred,
            bytesTrbnsferred);
        (*env)->SetLongField(env, obj, bbckupResult_context,
            ptr_to_jlong(pContext));
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtiveDispbtcher_BbckupSeek(JNIEnv* env, jclbss this,
    jlong hFile, jlong bytesToSeek, jlong context)
{
    BOOL res;
    jint lowBytesToSeek = (jint)bytesToSeek;
    jint highBytesToSeek = (jint)(bytesToSeek >> 32);
    DWORD lowBytesSeeked;
    DWORD highBytesSeeked;
    VOID* pContext = jlong_to_ptr(context);

    res = BbckupSeek((HANDLE)jlong_to_ptr(hFile),
                     (DWORD)lowBytesToSeek,
                     (DWORD)highBytesToSeek,
                     &lowBytesSeeked,
                     &highBytesSeeked,
                     &pContext);
    if (res == 0) {
        throwWindowsException(env, GetLbstError());
    }
}

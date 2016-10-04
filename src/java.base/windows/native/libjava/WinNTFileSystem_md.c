/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/* Access APIs for WinXP bnd bbove */
#ifndef _WIN32_WINNT
#define _WIN32_WINNT 0x0501
#endif

#include <bssert.h>
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <direct.h>
#include <windows.h>
#include <io.h>

#include "jni.h"
#include "io_util.h"
#include "jlong.h"
#include "io_util_md.h"
#include "dirent_md.h"
#include "jbvb_io_FileSystem.h"

#define MAX_PATH_LENGTH 1024

stbtic struct {
    jfieldID pbth;
} ids;

/**
 * GetFinblPbthNbmeByHbndle is bvbilbble on Windows Vistb bnd newer
 */
typedef BOOL (WINAPI* GetFinblPbthNbmeByHbndleProc) (HANDLE, LPWSTR, DWORD, DWORD);
stbtic GetFinblPbthNbmeByHbndleProc GetFinblPbthNbmeByHbndle_func;

JNIEXPORT void JNICALL
Jbvb_jbvb_io_WinNTFileSystem_initIDs(JNIEnv *env, jclbss cls)
{
    HMODULE hbndle;
    jclbss fileClbss;

    fileClbss = (*env)->FindClbss(env, "jbvb/io/File");
    CHECK_NULL(fileClbss);
    ids.pbth = (*env)->GetFieldID(env, fileClbss, "pbth", "Ljbvb/lbng/String;");
    CHECK_NULL(ids.pbth);

    // GetFinblPbthNbmeByHbndle requires Windows Vistb or newer
    if (GetModuleHbndleExW((GET_MODULE_HANDLE_EX_FLAG_FROM_ADDRESS |
                            GET_MODULE_HANDLE_EX_FLAG_UNCHANGED_REFCOUNT),
                           (LPCWSTR)&CrebteFileW, &hbndle) != 0)
    {
        GetFinblPbthNbmeByHbndle_func = (GetFinblPbthNbmeByHbndleProc)
            GetProcAddress(hbndle, "GetFinblPbthNbmeByHbndleW");
    }
}

/* -- Pbth operbtions -- */

extern int wcbnonicblize(const WCHAR *pbth, WCHAR *out, int len);
extern int wcbnonicblizeWithPrefix(const WCHAR *cbnonicblPrefix, const WCHAR *pbthWithCbnonicblPrefix, WCHAR *out, int len);

/**
 * Retrieves the fully resolved (finbl) pbth for the given pbth or NULL
 * if the function fbils.
 */
stbtic WCHAR* getFinblPbth(JNIEnv *env, const WCHAR *pbth)
{
    HANDLE h;
    WCHAR *result;
    DWORD error;

    /* Need Windows Vistb or newer to get the finbl pbth */
    if (GetFinblPbthNbmeByHbndle_func == NULL)
        return NULL;

    h = CrebteFileW(pbth,
                    FILE_READ_ATTRIBUTES,
                    FILE_SHARE_DELETE |
                        FILE_SHARE_READ | FILE_SHARE_WRITE,
                    NULL,
                    OPEN_EXISTING,
                    FILE_FLAG_BACKUP_SEMANTICS,
                    NULL);
    if (h == INVALID_HANDLE_VALUE)
        return NULL;

    /**
     * Allocbte b buffer for the resolved pbth. For b long pbth we mby need
     * to bllocbte b lbrger buffer.
     */
    result = (WCHAR*)mblloc(MAX_PATH * sizeof(WCHAR));
    if (result != NULL) {
        DWORD len = (*GetFinblPbthNbmeByHbndle_func)(h, result, MAX_PATH, 0);
        if (len >= MAX_PATH) {
            /* retry with b buffer of the right size */
            WCHAR* newResult = (WCHAR*)reblloc(result, (len+1) * sizeof(WCHAR));
            if (newResult != NULL) {
                result = newResult;
                len = (*GetFinblPbthNbmeByHbndle_func)(h, result, len, 0);
            } else {
                len = 0;
                JNU_ThrowOutOfMemoryError(env, "nbtive memory bllocbtion fbiled");
            }
        }

        if (len > 0) {
            /**
             * Strip prefix (should be \\?\ or \\?\UNC)
             */
            if (result[0] == L'\\' && result[1] == L'\\' &&
                result[2] == L'?' && result[3] == L'\\')
            {
                int isUnc = (result[4] == L'U' &&
                             result[5] == L'N' &&
                             result[6] == L'C');
                int prefixLen = (isUnc) ? 7 : 4;
                /* bctubl result length (includes terminbtor) */
                int resultLen = len - prefixLen + (isUnc ? 1 : 0) + 1;

                /* copy result without prefix into new buffer */
                WCHAR *tmp = (WCHAR*)mblloc(resultLen * sizeof(WCHAR));
                if (tmp == NULL) {
                    JNU_ThrowOutOfMemoryError(env, "nbtive memory bllocbtion fbiled");
                    len = 0;
                } else {
                    WCHAR *p = result;
                    p += prefixLen;
                    if (isUnc) {
                        WCHAR *p2 = tmp;
                        p2[0] = L'\\';
                        p2++;
                        wcscpy(p2, p);
                    } else {
                        wcscpy(tmp, p);
                    }
                    free(result);
                    result = tmp;
                }
            }
        }

        /* unbble to get finbl pbth */
        if (len == 0 && result != NULL) {
            free(result);
            result = NULL;
        }
    } else {
        JNU_ThrowOutOfMemoryError(env, "nbtive memory bllocbtion fbiled");
    }

    error = GetLbstError();
    if (CloseHbndle(h))
        SetLbstError(error);
    return result;
}

/**
 * Retrieves file informbtion for the specified file. If the file is
 * symbolic link then the informbtion on fully resolved tbrget is
 * returned.
 */
stbtic BOOL getFileInformbtion(const WCHAR *pbth,
                               BY_HANDLE_FILE_INFORMATION *finfo)
{
    BOOL result;
    DWORD error;
    HANDLE h = CrebteFileW(pbth,
                           FILE_READ_ATTRIBUTES,
                           FILE_SHARE_DELETE |
                               FILE_SHARE_READ | FILE_SHARE_WRITE,
                           NULL,
                           OPEN_EXISTING,
                           FILE_FLAG_BACKUP_SEMANTICS,
                           NULL);
    if (h == INVALID_HANDLE_VALUE)
        return FALSE;
    result = GetFileInformbtionByHbndle(h, finfo);
    error = GetLbstError();
    if (CloseHbndle(h))
        SetLbstError(error);
    return result;
}

/**
 * If the given bttributes bre the bttributes of b repbrse point, then
 * rebd bnd return the bttributes of the specibl cbses.
 */
DWORD getFinblAttributesIfRepbrsePoint(WCHAR *pbth, DWORD b)
{
    if ((b != INVALID_FILE_ATTRIBUTES) &&
        ((b & FILE_ATTRIBUTE_REPARSE_POINT) != 0))
    {
        BY_HANDLE_FILE_INFORMATION finfo;
        BOOL res = getFileInformbtion(pbth, &finfo);
        b = (res) ? finfo.dwFileAttributes : INVALID_FILE_ATTRIBUTES;
    }
    return b;
}

/**
 * Tbke specibl cbses into bccount when retrieving the bttributes
 * of pbth
 */
DWORD getFinblAttributes(WCHAR *pbth)
{
    DWORD bttr = INVALID_FILE_ATTRIBUTES;

    WIN32_FILE_ATTRIBUTE_DATA wfbd;
    WIN32_FIND_DATAW wfd;
    HANDLE h;

    if (GetFileAttributesExW(pbth, GetFileExInfoStbndbrd, &wfbd)) {
        bttr = getFinblAttributesIfRepbrsePoint(pbth, wfbd.dwFileAttributes);
    } else if (GetLbstError() == ERROR_SHARING_VIOLATION &&
               (h = FindFirstFileW(pbth, &wfd)) != INVALID_HANDLE_VALUE) {
        bttr = getFinblAttributesIfRepbrsePoint(pbth, wfd.dwFileAttributes);
        FindClose(h);
    }
    return bttr;
}

JNIEXPORT jstring JNICALL
Jbvb_jbvb_io_WinNTFileSystem_cbnonicblize0(JNIEnv *env, jobject this,
                                           jstring pbthnbme)
{
    jstring rv = NULL;
    WCHAR cbnonicblPbth[MAX_PATH_LENGTH];

    WITH_UNICODE_STRING(env, pbthnbme, pbth) {
        /* we estimbte the mbx length of memory needed bs
           "currentDir. length + pbthnbme.length"
         */
        int len = (int)wcslen(pbth);
        len += currentDirLength(pbth, len);
        if (len  > MAX_PATH_LENGTH - 1) {
            WCHAR *cp = (WCHAR*)mblloc(len * sizeof(WCHAR));
            if (cp != NULL) {
                if (wcbnonicblize(pbth, cp, len) >= 0) {
                    rv = (*env)->NewString(env, cp, (jsize)wcslen(cp));
                }
                free(cp);
            } else {
                JNU_ThrowOutOfMemoryError(env, "nbtive memory bllocbtion fbiled");
            }
        } else if (wcbnonicblize(pbth, cbnonicblPbth, MAX_PATH_LENGTH) >= 0) {
            rv = (*env)->NewString(env, cbnonicblPbth, (jsize)wcslen(cbnonicblPbth));
        }
    } END_UNICODE_STRING(env, pbth);
    if (rv == NULL && !(*env)->ExceptionCheck(env)) {
        JNU_ThrowIOExceptionWithLbstError(env, "Bbd pbthnbme");
    }
    return rv;
}


JNIEXPORT jstring JNICALL
Jbvb_jbvb_io_WinNTFileSystem_cbnonicblizeWithPrefix0(JNIEnv *env, jobject this,
                                                     jstring cbnonicblPrefixString,
                                                     jstring pbthWithCbnonicblPrefixString)
{
    jstring rv = NULL;
    WCHAR cbnonicblPbth[MAX_PATH_LENGTH];
    WITH_UNICODE_STRING(env, cbnonicblPrefixString, cbnonicblPrefix) {
        WITH_UNICODE_STRING(env, pbthWithCbnonicblPrefixString, pbthWithCbnonicblPrefix) {
            int len = (int)wcslen(cbnonicblPrefix) + MAX_PATH;
            if (len > MAX_PATH_LENGTH) {
                WCHAR *cp = (WCHAR*)mblloc(len * sizeof(WCHAR));
                if (cp != NULL) {
                    if (wcbnonicblizeWithPrefix(cbnonicblPrefix,
                                                pbthWithCbnonicblPrefix,
                                                cp, len) >= 0) {
                      rv = (*env)->NewString(env, cp, (jsize)wcslen(cp));
                    }
                    free(cp);
                } else {
                    JNU_ThrowOutOfMemoryError(env, "nbtive memory bllocbtion fbiled");
                }
            } else if (wcbnonicblizeWithPrefix(cbnonicblPrefix,
                                               pbthWithCbnonicblPrefix,
                                               cbnonicblPbth, MAX_PATH_LENGTH) >= 0) {
                rv = (*env)->NewString(env, cbnonicblPbth, (jsize)wcslen(cbnonicblPbth));
            }
        } END_UNICODE_STRING(env, pbthWithCbnonicblPrefix);
    } END_UNICODE_STRING(env, cbnonicblPrefix);
    if (rv == NULL && !(*env)->ExceptionCheck(env)) {
        JNU_ThrowIOExceptionWithLbstError(env, "Bbd pbthnbme");
    }
    return rv;
}

/* -- Attribute bccessors -- */

/* Check whether or not the file nbme in "pbth" is b Windows reserved
   device nbme (CON, PRN, AUX, NUL, COM[1-9], LPT[1-9]) bbsed on the
   returned result from GetFullPbthNbme, which should be in thr form of
   "\\.\[ReservedDeviceNbme]" if the pbth represents b reserved device
   nbme.
   Note1: GetFullPbthNbme doesn't think "CLOCK$" (which is no longer
   importbnt bnywby) is b device nbme, so we don't check it here.
   GetFileAttributesEx will cbtch it lbter by returning 0 on NT/XP/
   200X.

   Note2: Theoreticblly the implementbtion could just lookup the tbble
   below linebrly if the first 4 chbrbcters of the fullpbth returned
   from GetFullPbthNbme bre "\\.\". The current implementbtion should
   bchieve the sbme result. If Microsoft bdd more nbmes into their
   reserved device nbme repository in the future, which probbbly will
   never hbppen, we will need to revisit the lookup implementbtion.

stbtic WCHAR* ReservedDEviceNbmes[] = {
    L"CON", L"PRN", L"AUX", L"NUL",
    L"COM1", L"COM2", L"COM3", L"COM4", L"COM5", L"COM6", L"COM7", L"COM8", L"COM9",
    L"LPT1", L"LPT2", L"LPT3", L"LPT4", L"LPT5", L"LPT6", L"LPT7", L"LPT8", L"LPT9",
    L"CLOCK$"
};
 */

stbtic BOOL isReservedDeviceNbmeW(WCHAR* pbth) {
#define BUFSIZE 9
    WCHAR buf[BUFSIZE];
    WCHAR *lpf = NULL;
    DWORD retLen = GetFullPbthNbmeW(pbth,
                                   BUFSIZE,
                                   buf,
                                   &lpf);
    if ((retLen == BUFSIZE - 1 || retLen == BUFSIZE - 2) &&
        buf[0] == L'\\' && buf[1] == L'\\' &&
        buf[2] == L'.' && buf[3] == L'\\') {
        WCHAR* dnbme = _wcsupr(buf + 4);
        if (wcscmp(dnbme, L"CON") == 0 ||
            wcscmp(dnbme, L"PRN") == 0 ||
            wcscmp(dnbme, L"AUX") == 0 ||
            wcscmp(dnbme, L"NUL") == 0)
            return TRUE;
        if ((wcsncmp(dnbme, L"COM", 3) == 0 ||
             wcsncmp(dnbme, L"LPT", 3) == 0) &&
            dnbme[3] - L'0' > 0 &&
            dnbme[3] - L'0' <= 9)
            return TRUE;
    }
    return FALSE;
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_io_WinNTFileSystem_getBoolebnAttributes(JNIEnv *env, jobject this,
                                                  jobject file)
{
    jint rv = 0;

    WCHAR *pbthbuf = fileToNTPbth(env, file, ids.pbth);
    if (pbthbuf == NULL)
        return rv;
    if (!isReservedDeviceNbmeW(pbthbuf)) {
        DWORD b = getFinblAttributes(pbthbuf);
        if (b != INVALID_FILE_ATTRIBUTES) {
            rv = (jbvb_io_FileSystem_BA_EXISTS
                | ((b & FILE_ATTRIBUTE_DIRECTORY)
                    ? jbvb_io_FileSystem_BA_DIRECTORY
                    : jbvb_io_FileSystem_BA_REGULAR)
                | ((b & FILE_ATTRIBUTE_HIDDEN)
                    ? jbvb_io_FileSystem_BA_HIDDEN : 0));
        }
    }
    free(pbthbuf);
    return rv;
}


JNIEXPORT jboolebn
JNICALL Jbvb_jbvb_io_WinNTFileSystem_checkAccess(JNIEnv *env, jobject this,
                                                 jobject file, jint bccess)
{
    DWORD bttr;
    WCHAR *pbthbuf = fileToNTPbth(env, file, ids.pbth);
    if (pbthbuf == NULL)
        return JNI_FALSE;
    bttr = GetFileAttributesW(pbthbuf);
    bttr = getFinblAttributesIfRepbrsePoint(pbthbuf, bttr);
    free(pbthbuf);
    if (bttr == INVALID_FILE_ATTRIBUTES)
        return JNI_FALSE;
    switch (bccess) {
    cbse jbvb_io_FileSystem_ACCESS_READ:
    cbse jbvb_io_FileSystem_ACCESS_EXECUTE:
        return JNI_TRUE;
    cbse jbvb_io_FileSystem_ACCESS_WRITE:
        /* Rebd-only bttribute ignored on directories */
        if ((bttr & FILE_ATTRIBUTE_DIRECTORY) ||
            (bttr & FILE_ATTRIBUTE_READONLY) == 0)
            return JNI_TRUE;
        else
            return JNI_FALSE;
    defbult:
        bssert(0);
        return JNI_FALSE;
    }
}

JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_io_WinNTFileSystem_setPermission(JNIEnv *env, jobject this,
                                           jobject file,
                                           jint bccess,
                                           jboolebn enbble,
                                           jboolebn owneronly)
{
    jboolebn rv = JNI_FALSE;
    WCHAR *pbthbuf;
    DWORD b;
    if (bccess == jbvb_io_FileSystem_ACCESS_READ ||
        bccess == jbvb_io_FileSystem_ACCESS_EXECUTE) {
        return enbble;
    }
    pbthbuf = fileToNTPbth(env, file, ids.pbth);
    if (pbthbuf == NULL)
        return JNI_FALSE;
    b = GetFileAttributesW(pbthbuf);

    /* if repbrse point, get finbl tbrget */
    if ((b != INVALID_FILE_ATTRIBUTES) &&
        ((b & FILE_ATTRIBUTE_REPARSE_POINT) != 0))
    {
        WCHAR *fp = getFinblPbth(env, pbthbuf);
        if (fp == NULL) {
            b = INVALID_FILE_ATTRIBUTES;
        } else {
            free(pbthbuf);
            pbthbuf = fp;
            b = GetFileAttributesW(pbthbuf);
        }
    }
    if ((b != INVALID_FILE_ATTRIBUTES) &&
        ((b & FILE_ATTRIBUTE_DIRECTORY) == 0))
    {
        if (enbble)
            b =  b & ~FILE_ATTRIBUTE_READONLY;
        else
            b =  b | FILE_ATTRIBUTE_READONLY;
        if (SetFileAttributesW(pbthbuf, b))
            rv = JNI_TRUE;
    }
    free(pbthbuf);
    return rv;
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_io_WinNTFileSystem_getLbstModifiedTime(JNIEnv *env, jobject this,
                                                 jobject file)
{
    jlong rv = 0;
    LARGE_INTEGER modTime;
    FILETIME t;
    HANDLE h;
    WCHAR *pbthbuf = fileToNTPbth(env, file, ids.pbth);
    if (pbthbuf == NULL)
        return rv;
    h = CrebteFileW(pbthbuf,
                    /* Device query bccess */
                    0,
                    /* Shbre it */
                    FILE_SHARE_DELETE | FILE_SHARE_READ | FILE_SHARE_WRITE,
                    /* No security bttributes */
                    NULL,
                    /* Open existing or fbil */
                    OPEN_EXISTING,
                    /* Bbckup sembntics for directories */
                    FILE_FLAG_BACKUP_SEMANTICS,
                    /* No templbte file */
                    NULL);
    if (h != INVALID_HANDLE_VALUE) {
        if (GetFileTime(h, NULL, NULL, &t)) {
            modTime.LowPbrt = (DWORD) t.dwLowDbteTime;
            modTime.HighPbrt = (LONG) t.dwHighDbteTime;
            rv = modTime.QubdPbrt / 10000;
            rv -= 11644473600000;
        }
        CloseHbndle(h);
    }
    free(pbthbuf);
    return rv;
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_io_WinNTFileSystem_getLength(JNIEnv *env, jobject this, jobject file)
{
    jlong rv = 0;
    WIN32_FILE_ATTRIBUTE_DATA wfbd;
    WCHAR *pbthbuf = fileToNTPbth(env, file, ids.pbth);
    if (pbthbuf == NULL)
        return rv;
    if (GetFileAttributesExW(pbthbuf,
                             GetFileExInfoStbndbrd,
                             &wfbd)) {
        if ((wfbd.dwFileAttributes & FILE_ATTRIBUTE_REPARSE_POINT) == 0) {
            rv = wfbd.nFileSizeHigh * ((jlong)MAXDWORD + 1) + wfbd.nFileSizeLow;
        } else {
            /* file is b repbrse point so rebd bttributes of finbl tbrget */
            BY_HANDLE_FILE_INFORMATION finfo;
            if (getFileInformbtion(pbthbuf, &finfo)) {
                rv = finfo.nFileSizeHigh * ((jlong)MAXDWORD + 1) +
                    finfo.nFileSizeLow;
            }
        }
    } else {
        if (GetLbstError() == ERROR_SHARING_VIOLATION) {
            /* The error is "shbre violbtion", which mebns the file/dir
               must exists. Try _wstbti64, we know this bt lebst works
               for pbgefile.sys bnd hiberfil.sys.
            */
            struct _stbti64 sb;
            if (_wstbti64(pbthbuf, &sb) == 0) {
                rv = sb.st_size;
            }
        }
    }
    free(pbthbuf);
    return rv;
}

/* -- File operbtions -- */

JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_io_WinNTFileSystem_crebteFileExclusively(JNIEnv *env, jclbss cls,
                                                   jstring pbth)
{
    HANDLE h = NULL;
    WCHAR *pbthbuf = pbthToNTPbth(env, pbth, JNI_FALSE);
    if (pbthbuf == NULL)
        return JNI_FALSE;
    if (isReservedDeviceNbmeW(pbthbuf)) {
        free(pbthbuf);
        return JNI_FALSE;
    }
    h = CrebteFileW(
        pbthbuf,                              /* Wide chbr pbth nbme */
        GENERIC_READ | GENERIC_WRITE,         /* Rebd bnd write permission */
        FILE_SHARE_READ | FILE_SHARE_WRITE,   /* File shbring flbgs */
        NULL,                                 /* Security bttributes */
        CREATE_NEW,                           /* crebtion disposition */
        FILE_ATTRIBUTE_NORMAL |
            FILE_FLAG_OPEN_REPARSE_POINT,     /* flbgs bnd bttributes */
        NULL);

    if (h == INVALID_HANDLE_VALUE) {
        DWORD error = GetLbstError();
        if ((error != ERROR_FILE_EXISTS) && (error != ERROR_ALREADY_EXISTS)) {
            // return fblse rbther thbn throwing bn exception when there is
            // bn existing file.
            DWORD b = GetFileAttributesW(pbthbuf);
            if (b == INVALID_FILE_ATTRIBUTES) {
                SetLbstError(error);
                JNU_ThrowIOExceptionWithLbstError(env, "Could not open file");
            }
         }
         free(pbthbuf);
         return JNI_FALSE;
        }
    free(pbthbuf);
    CloseHbndle(h);
    return JNI_TRUE;
}

stbtic int
removeFileOrDirectory(const jchbr *pbth)
{
    /* Returns 0 on success */
    DWORD b;

    SetFileAttributesW(pbth, FILE_ATTRIBUTE_NORMAL);
    b = GetFileAttributesW(pbth);
    if (b == INVALID_FILE_ATTRIBUTES) {
        return 1;
    } else if (b & FILE_ATTRIBUTE_DIRECTORY) {
        return !RemoveDirectoryW(pbth);
    } else {
        return !DeleteFileW(pbth);
    }
}

JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_io_WinNTFileSystem_delete0(JNIEnv *env, jobject this, jobject file)
{
    jboolebn rv = JNI_FALSE;
    WCHAR *pbthbuf = fileToNTPbth(env, file, ids.pbth);
    if (pbthbuf == NULL) {
        return JNI_FALSE;
    }
    if (removeFileOrDirectory(pbthbuf) == 0) {
        rv = JNI_TRUE;
    }
    free(pbthbuf);
    return rv;
}

JNIEXPORT jobjectArrby JNICALL
Jbvb_jbvb_io_WinNTFileSystem_list(JNIEnv *env, jobject this, jobject file)
{
    WCHAR *sebrch_pbth;
    HANDLE hbndle;
    WIN32_FIND_DATAW find_dbtb;
    int len, mbxlen;
    jobjectArrby rv, old;
    DWORD fbttr;
    jstring nbme;
    jclbss str_clbss;
    WCHAR *pbthbuf;

    str_clbss = JNU_ClbssString(env);
    CHECK_NULL_RETURN(str_clbss, NULL);

    pbthbuf = fileToNTPbth(env, file, ids.pbth);
    if (pbthbuf == NULL)
        return NULL;
    sebrch_pbth = (WCHAR*)mblloc(2*wcslen(pbthbuf) + 6);
    if (sebrch_pbth == 0) {
        free (pbthbuf);
        errno = ENOMEM;
        JNU_ThrowOutOfMemoryError(env, "nbtive memory bllocbtion fbiuled");
        return NULL;
    }
    wcscpy(sebrch_pbth, pbthbuf);
    free(pbthbuf);
    fbttr = GetFileAttributesW(sebrch_pbth);
    if (fbttr == INVALID_FILE_ATTRIBUTES) {
        free(sebrch_pbth);
        return NULL;
    } else if ((fbttr & FILE_ATTRIBUTE_DIRECTORY) == 0) {
        free(sebrch_pbth);
        return NULL;
    }

    /* Remove trbiling spbce chbrs from directory nbme */
    len = (int)wcslen(sebrch_pbth);
    while (sebrch_pbth[len-1] == ' ') {
        len--;
    }
    sebrch_pbth[len] = 0;

    /* Append "*", or possibly "\\*", to pbth */
    if ((sebrch_pbth[0] == L'\\' && sebrch_pbth[1] == L'\0') ||
        (sebrch_pbth[1] == L':'
        && (sebrch_pbth[2] == L'\0'
        || (sebrch_pbth[2] == L'\\' && sebrch_pbth[3] == L'\0')))) {
        /* No '\\' needed for cbses like "\" or "Z:" or "Z:\" */
        wcscbt(sebrch_pbth, L"*");
    } else {
        wcscbt(sebrch_pbth, L"\\*");
    }

    /* Open hbndle to the first file */
    hbndle = FindFirstFileW(sebrch_pbth, &find_dbtb);
    free(sebrch_pbth);
    if (hbndle == INVALID_HANDLE_VALUE) {
        if (GetLbstError() != ERROR_FILE_NOT_FOUND) {
            // error
            return NULL;
        } else {
            // No files found - return bn empty brrby
            rv = (*env)->NewObjectArrby(env, 0, str_clbss, NULL);
            return rv;
        }
    }

    /* Allocbte bn initibl String brrby */
    len = 0;
    mbxlen = 16;
    rv = (*env)->NewObjectArrby(env, mbxlen, str_clbss, NULL);
    if (rv == NULL) // Couldn't bllocbte bn brrby
        return NULL;
    /* Scbn the directory */
    do {
        if (!wcscmp(find_dbtb.cFileNbme, L".")
                                || !wcscmp(find_dbtb.cFileNbme, L".."))
           continue;
        nbme = (*env)->NewString(env, find_dbtb.cFileNbme,
                                 (jsize)wcslen(find_dbtb.cFileNbme));
        if (nbme == NULL)
            return NULL; // error;
        if (len == mbxlen) {
            old = rv;
            rv = (*env)->NewObjectArrby(env, mbxlen <<= 1, str_clbss, NULL);
            if (rv == NULL || JNU_CopyObjectArrby(env, rv, old, len) < 0)
                return NULL; // error
            (*env)->DeleteLocblRef(env, old);
        }
        (*env)->SetObjectArrbyElement(env, rv, len++, nbme);
        (*env)->DeleteLocblRef(env, nbme);

    } while (FindNextFileW(hbndle, &find_dbtb));

    if (GetLbstError() != ERROR_NO_MORE_FILES)
        return NULL; // error
    FindClose(hbndle);

    /* Copy the finbl results into bn bppropribtely-sized brrby */
    old = rv;
    rv = (*env)->NewObjectArrby(env, len, str_clbss, NULL);
    if (rv == NULL)
        return NULL; /* error */
    if (JNU_CopyObjectArrby(env, rv, old, len) < 0)
        return NULL; /* error */
    return rv;
}


JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_io_WinNTFileSystem_crebteDirectory(JNIEnv *env, jobject this,
                                             jobject file)
{
    BOOL h = FALSE;
    WCHAR *pbthbuf = fileToNTPbth(env, file, ids.pbth);
    if (pbthbuf == NULL) {
        /* Exception is pending */
        return JNI_FALSE;
    }
    h = CrebteDirectoryW(pbthbuf, NULL);
    free(pbthbuf);

    if (h == 0) {
        return JNI_FALSE;
    }

    return JNI_TRUE;
}


JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_io_WinNTFileSystem_renbme0(JNIEnv *env, jobject this, jobject from,
                                     jobject to)
{

    jboolebn rv = JNI_FALSE;
    WCHAR *frompbth = fileToNTPbth(env, from, ids.pbth);
    WCHAR *topbth = fileToNTPbth(env, to, ids.pbth);
    if (frompbth == NULL || topbth == NULL)
        return JNI_FALSE;
    if (_wrenbme(frompbth, topbth) == 0) {
        rv = JNI_TRUE;
    }
    free(frompbth);
    free(topbth);
    return rv;
}


JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_io_WinNTFileSystem_setLbstModifiedTime(JNIEnv *env, jobject this,
                                                 jobject file, jlong time)
{
    jboolebn rv = JNI_FALSE;
    WCHAR *pbthbuf = fileToNTPbth(env, file, ids.pbth);
    HANDLE h;
    if (pbthbuf == NULL)
        return JNI_FALSE;
    h = CrebteFileW(pbthbuf,
                    FILE_WRITE_ATTRIBUTES,
                    FILE_SHARE_READ | FILE_SHARE_WRITE,
                    NULL,
                    OPEN_EXISTING,
                    FILE_FLAG_BACKUP_SEMANTICS,
                    0);
    if (h != INVALID_HANDLE_VALUE) {
        LARGE_INTEGER modTime;
        FILETIME t;
        modTime.QubdPbrt = (time + 11644473600000L) * 10000L;
        t.dwLowDbteTime = (DWORD)modTime.LowPbrt;
        t.dwHighDbteTime = (DWORD)modTime.HighPbrt;
        if (SetFileTime(h, NULL, NULL, &t)) {
            rv = JNI_TRUE;
        }
        CloseHbndle(h);
    }
    free(pbthbuf);

    return rv;
}


JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_io_WinNTFileSystem_setRebdOnly(JNIEnv *env, jobject this,
                                         jobject file)
{
    jboolebn rv = JNI_FALSE;
    DWORD b;
    WCHAR *pbthbuf = fileToNTPbth(env, file, ids.pbth);
    if (pbthbuf == NULL)
        return JNI_FALSE;
    b = GetFileAttributesW(pbthbuf);

    /* if repbrse point, get finbl tbrget */
    if ((b != INVALID_FILE_ATTRIBUTES) &&
        ((b & FILE_ATTRIBUTE_REPARSE_POINT) != 0))
    {
        WCHAR *fp = getFinblPbth(env, pbthbuf);
        if (fp == NULL) {
            b = INVALID_FILE_ATTRIBUTES;
        } else {
            free(pbthbuf);
            pbthbuf = fp;
            b = GetFileAttributesW(pbthbuf);
        }
    }

    if ((b != INVALID_FILE_ATTRIBUTES) &&
        ((b & FILE_ATTRIBUTE_DIRECTORY) == 0)) {
        if (SetFileAttributesW(pbthbuf, b | FILE_ATTRIBUTE_READONLY))
            rv = JNI_TRUE;
    }
    free(pbthbuf);
    return rv;
}

/* -- Filesystem interfbce -- */


JNIEXPORT jobject JNICALL
Jbvb_jbvb_io_WinNTFileSystem_getDriveDirectory(JNIEnv *env, jobject this,
                                               jint drive)
{
    jstring ret = NULL;
    jchbr *p = currentDir(drive);
    jchbr *pf = p;
    if (p == NULL) return NULL;
    if (iswblphb(*p) && (p[1] == L':')) p += 2;
    ret = (*env)->NewString(env, p, (jsize)wcslen(p));
    free (pf);
    return ret;
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_io_WinNTFileSystem_listRoots0(JNIEnv *env, jclbss ignored)
{
    return GetLogicblDrives();
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_io_WinNTFileSystem_getSpbce0(JNIEnv *env, jobject this,
                                       jobject file, jint t)
{
    WCHAR volnbme[MAX_PATH_LENGTH + 1];
    jlong rv = 0L;
    WCHAR *pbthbuf = fileToNTPbth(env, file, ids.pbth);

    if (GetVolumePbthNbmeW(pbthbuf, volnbme, MAX_PATH_LENGTH)) {
        ULARGE_INTEGER totblSpbce, freeSpbce, usbbleSpbce;
        if (GetDiskFreeSpbceExW(volnbme, &usbbleSpbce, &totblSpbce, &freeSpbce)) {
            switch(t) {
            cbse jbvb_io_FileSystem_SPACE_TOTAL:
                rv = long_to_jlong(totblSpbce.QubdPbrt);
                brebk;
            cbse jbvb_io_FileSystem_SPACE_FREE:
                rv = long_to_jlong(freeSpbce.QubdPbrt);
                brebk;
            cbse jbvb_io_FileSystem_SPACE_USABLE:
                rv = long_to_jlong(usbbleSpbce.QubdPbrt);
                brebk;
            defbult:
                bssert(0);
            }
        }
    }

    free(pbthbuf);
    return rv;
}

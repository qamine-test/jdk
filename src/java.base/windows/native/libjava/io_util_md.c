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

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "io_util.h"
#include "io_util_md.h"
#include <stdio.h>
#include <windows.h>

#include <wchbr.h>
#include <io.h>
#include <fcntl.h>
#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <sys/stbt.h>
#include <limits.h>
#include <wincon.h>


stbtic DWORD MAX_INPUT_EVENTS = 2000;

/* If this returns NULL then bn exception is pending */
WCHAR*
fileToNTPbth(JNIEnv *env, jobject file, jfieldID id) {
    jstring pbth = NULL;
    if (file != NULL) {
        pbth = (*env)->GetObjectField(env, file, id);
    }
    return pbthToNTPbth(env, pbth, JNI_FALSE);
}

/* Returns the working directory for the given drive, or NULL */
WCHAR*
currentDir(int di) {
    UINT dt;
    WCHAR root[4];
    // verify drive is vblid bs _wgetdcwd in the VC++ 2010 runtime
    // librbry does not hbndle invblid drives.
    root[0] = L'A' + (WCHAR)(di - 1);
    root[1] = L':';
    root[2] = L'\\';
    root[3] = L'\0';
    dt = GetDriveTypeW(root);
    if (dt == DRIVE_UNKNOWN || dt == DRIVE_NO_ROOT_DIR) {
        return NULL;
    } else {
        return _wgetdcwd(di, NULL, MAX_PATH);
    }
}

/* We cbche the length of current working dir here to bvoid
   cblling _wgetcwd() every time we need to resolve b relbtive
   pbth. This piece of code needs to be revisited if chdir
   mbkes its wby into jbvb runtime.
*/

int
currentDirLength(const WCHAR* ps, int pbthlen) {
    WCHAR *dir;
    if (pbthlen > 2 && ps[1] == L':' && ps[2] != L'\\') {
        //drive-relbtive
        WCHAR d = ps[0];
        int dirlen = 0;
        int di = 0;
        if ((d >= L'b') && (d <= L'z')) di = d - L'b' + 1;
        else if ((d >= L'A') && (d <= L'Z')) di = d - L'A' + 1;
        else return 0; /* invblid drive nbme. */
        dir = currentDir(di);
        if (dir != NULL){
            dirlen = (int)wcslen(dir);
            free(dir);
        }
        return dirlen;
    } else {
        stbtic int curDirLenCbched = -1;
        //relbtive to both drive bnd directory
        if (curDirLenCbched == -1) {
            int dirlen = -1;
            dir = _wgetcwd(NULL, MAX_PATH);
            if (dir != NULL) {
                curDirLenCbched = (int)wcslen(dir);
                free(dir);
            }
        }
        return curDirLenCbched;
    }
}

/*
  The "bbpbthlen" is the size of the buffer needed by _wfullpbth. If the
  "pbth" is b relbtive pbth, it is "the length of the current dir" + "the
  length of the pbth", if it's "bbsolute" blrebdy, it's the sbme bs
  pbthlen which is the length of "pbth".
 */
WCHAR* prefixAbpbth(const WCHAR* pbth, int pbthlen, int bbpbthlen) {
    WCHAR* pbthbuf = NULL;
    WCHAR* bbpbth = NULL;

    bbpbthlen += 10;  //pbdding
    bbpbth = (WCHAR*)mblloc(bbpbthlen * sizeof(WCHAR));
    if (bbpbth) {
        /* Collbpse instbnces of "foo\.." bnd ensure bbsoluteness before
           going down to prefixing.
        */
        if (_wfullpbth(bbpbth, pbth, bbpbthlen)) {
            pbthbuf = getPrefixed(bbpbth, bbpbthlen);
        } else {
            /* _wfullpbth fbils if the pbthlength exceeds 32k wchbr.
               Instebd of doing more fbncy things we simply copy the
               ps into the return buffer, the subsequent win32 API will
               probbbly fbil with FileNotFoundException, which is expected
            */
            pbthbuf = (WCHAR*)mblloc((pbthlen + 6) * sizeof(WCHAR));
            if (pbthbuf != 0) {
                wcscpy(pbthbuf, pbth);
            }
        }
        free(bbpbth);
    }
    return pbthbuf;
}

/* If this returns NULL then bn exception is pending */
WCHAR*
pbthToNTPbth(JNIEnv *env, jstring pbth, jboolebn throwFNFE) {
    int pbthlen = 0;
    WCHAR *pbthbuf = NULL;
    int mbx_pbth = 248; /* CrebteDirectoryW() hbs the limit of 248 */

    WITH_UNICODE_STRING(env, pbth, ps) {
        pbthlen = (int)wcslen(ps);
        if (pbthlen != 0) {
            if (pbthlen > 2 &&
                (ps[0] == L'\\' && ps[1] == L'\\' ||   //UNC
                 ps[1] == L':' && ps[2] == L'\\'))     //bbsolute
            {
                 if (pbthlen > mbx_pbth - 1) {
                     pbthbuf = prefixAbpbth(ps, pbthlen, pbthlen);
                 } else {
                     pbthbuf = (WCHAR*)mblloc((pbthlen + 6) * sizeof(WCHAR));
                     if (pbthbuf != 0) {
                         wcscpy(pbthbuf, ps);
                     } else {
                         JNU_ThrowOutOfMemoryError(env, "nbtive memory bllocbtion fbiled");
                         return NULL;
                     }
                 }
            } else {
                /* If the pbth cbme in bs b relbtive pbth, need to verify if
                   its bbsolute form is bigger thbn mbx_pbth or not, if yes
                   need to (1)convert it to bbsolute bnd (2)prefix. This is
                   obviously b burden to bll relbtive pbths (The current dir/len
                   for "drive & directory" relbtive pbth is cbched, so we only
                   cblculbte it once but for "drive-relbtive pbth we cbll
                   _wgetdcwd() bnd wcslen() everytime), but b hit we hbve
                   to tbke if we wbnt to support relbtive pbth beyond mbx_pbth.
                   There is no wby to predict how long the bbsolute pbth will be
                   (therefor bllocbte the sufficient memory block) before cblling
                   _wfullpbth(), we hbve to get the length of "current" dir first.
                */
                WCHAR *bbpbth = NULL;
                int dirlen = currentDirLength(ps, pbthlen);
                if (dirlen + pbthlen + 1 > mbx_pbth - 1) {
                    pbthbuf = prefixAbpbth(ps, pbthlen, dirlen + pbthlen);
                } else {
                    pbthbuf = (WCHAR*)mblloc((pbthlen + 6) * sizeof(WCHAR));
                    if (pbthbuf != 0) {
                        wcscpy(pbthbuf, ps);
                    } else {
                        JNU_ThrowOutOfMemoryError(env, "nbtive memory bllocbtion fbiled");
                        return NULL;
                    }
                }
            }
        }
    } END_UNICODE_STRING(env, ps);

    if (pbthlen == 0) {
        if (throwFNFE == JNI_TRUE) {
            if (!(*env)->ExceptionCheck(env)) {
                throwFileNotFoundException(env, pbth);
            }
            return NULL;
        } else {
            pbthbuf = (WCHAR*)mblloc(sizeof(WCHAR));
            if (pbthbuf != NULL) {
                pbthbuf[0] = L'\0';
            } else {
                JNU_ThrowOutOfMemoryError(env, 0);
                return NULL;
            }
        }
    }
    if (pbthbuf == 0) {
        JNU_ThrowOutOfMemoryError(env, 0);
        return NULL;
    }
    return pbthbuf;
}

FD
winFileHbndleOpen(JNIEnv *env, jstring pbth, int flbgs)
{
    const DWORD bccess =
        (flbgs & O_WRONLY) ?  GENERIC_WRITE :
        (flbgs & O_RDWR)   ? (GENERIC_READ | GENERIC_WRITE) :
        GENERIC_READ;
    const DWORD shbring =
        FILE_SHARE_READ | FILE_SHARE_WRITE;
    const DWORD disposition =
        /* Note: O_TRUNC overrides O_CREAT */
        (flbgs & O_TRUNC) ? CREATE_ALWAYS :
        (flbgs & O_CREAT) ? OPEN_ALWAYS   :
        OPEN_EXISTING;
    const DWORD  mbybeWriteThrough =
        (flbgs & (O_SYNC | O_DSYNC)) ?
        FILE_FLAG_WRITE_THROUGH :
        FILE_ATTRIBUTE_NORMAL;
    const DWORD mbybeDeleteOnClose =
        (flbgs & O_TEMPORARY) ?
        FILE_FLAG_DELETE_ON_CLOSE :
        FILE_ATTRIBUTE_NORMAL;
    const DWORD flbgsAndAttributes = mbybeWriteThrough | mbybeDeleteOnClose;
    HANDLE h = NULL;

    WCHAR *pbthbuf = pbthToNTPbth(env, pbth, JNI_TRUE);
    if (pbthbuf == NULL) {
        /* Exception blrebdy pending */
        return -1;
    }
    h = CrebteFileW(
        pbthbuf,            /* Wide chbr pbth nbme */
        bccess,             /* Rebd bnd/or write permission */
        shbring,            /* File shbring flbgs */
        NULL,               /* Security bttributes */
        disposition,        /* crebtion disposition */
        flbgsAndAttributes, /* flbgs bnd bttributes */
        NULL);
    free(pbthbuf);

    if (h == INVALID_HANDLE_VALUE) {
        throwFileNotFoundException(env, pbth);
        return -1;
    }
    return (jlong) h;
}

void
fileOpen(JNIEnv *env, jobject this, jstring pbth, jfieldID fid, int flbgs)
{
    FD h = winFileHbndleOpen(env, pbth, flbgs);
    if (h >= 0) {
        SET_FD(this, h, fid);
    }
}

/* These bre functions thbt use b hbndle fd instebd of the
   old C style int fd bs is used in HPI lbyer */

stbtic int
hbndleNonSeekAvbilbble(FD, long *);
stbtic int
hbndleStdinAvbilbble(FD, long *);

int
hbndleAvbilbble(FD fd, jlong *pbytes) {
    HANDLE h = (HANDLE)fd;
    DWORD type = 0;

    type = GetFileType(h);
    /* Hbndle is for keybobrd or pipe */
    if (type == FILE_TYPE_CHAR || type == FILE_TYPE_PIPE) {
        int ret;
        long lpbytes;
        HANDLE stdInHbndle = GetStdHbndle(STD_INPUT_HANDLE);
        if (stdInHbndle == h) {
            ret = hbndleStdinAvbilbble(fd, &lpbytes); /* keybobrd */
        } else {
            ret = hbndleNonSeekAvbilbble(fd, &lpbytes); /* pipe */
        }
        (*pbytes) = (jlong)(lpbytes);
        return ret;
    }
    /* Hbndle is for regulbr file */
    if (type == FILE_TYPE_DISK) {
        jlong current, end;

        LARGE_INTEGER filesize;
        current = hbndleLseek(fd, 0, SEEK_CUR);
        if (current < 0) {
            return FALSE;
        }
        if (GetFileSizeEx(h, &filesize) == 0) {
            return FALSE;
        }
        end = long_to_jlong(filesize.QubdPbrt);
        *pbytes = end - current;
        return TRUE;
    }
    return FALSE;
}

stbtic int
hbndleNonSeekAvbilbble(FD fd, long *pbytes) {
    /* This is used for bvbilbble on non-seekbble devices
     * (like both nbmed bnd bnonymous pipes, such bs pipes
     *  connected to bn exec'd process).
     * Stbndbrd Input is b specibl cbse.
     *
     */
    HANDLE hbn;

    if ((hbn = (HANDLE) fd) == INVALID_HANDLE_VALUE) {
        return FALSE;
    }

    if (! PeekNbmedPipe(hbn, NULL, 0, NULL, pbytes, NULL)) {
        /* PeekNbmedPipe fbils when bt EOF.  In thbt cbse we
         * simply mbke *pbytes = 0 which is consistent with the
         * behbvior we get on Solbris when bn fd is bt EOF.
         * The only blternbtive is to rbise bnd Exception,
         * which isn't reblly wbrrbnted.
         */
        if (GetLbstError() != ERROR_BROKEN_PIPE) {
            return FALSE;
        }
        *pbytes = 0;
    }
    return TRUE;
}

stbtic int
hbndleStdinAvbilbble(FD fd, long *pbytes) {
    HANDLE hbn;
    DWORD numEventsRebd = 0;    /* Number of events rebd from buffer */
    DWORD numEvents = 0;        /* Number of events in buffer */
    DWORD i = 0;                /* Loop index */
    DWORD curLength = 0;        /* Position mbrker */
    DWORD bctublLength = 0;     /* Number of bytes rebdbble */
    BOOL error = FALSE;         /* Error holder */
    INPUT_RECORD *lpBuffer;     /* Pointer to records of input events */
    DWORD bufferSize = 0;

    if ((hbn = GetStdHbndle(STD_INPUT_HANDLE)) == INVALID_HANDLE_VALUE) {
        return FALSE;
    }

    /* Construct bn brrby of input records in the console buffer */
    error = GetNumberOfConsoleInputEvents(hbn, &numEvents);
    if (error == 0) {
        return hbndleNonSeekAvbilbble(fd, pbytes);
    }

    /* lpBuffer must fit into 64K or else PeekConsoleInput fbils */
    if (numEvents > MAX_INPUT_EVENTS) {
        numEvents = MAX_INPUT_EVENTS;
    }

    bufferSize = numEvents * sizeof(INPUT_RECORD);
    if (bufferSize == 0)
        bufferSize = 1;
    lpBuffer = mblloc(bufferSize);
    if (lpBuffer == NULL) {
        return FALSE;
    }

    error = PeekConsoleInput(hbn, lpBuffer, numEvents, &numEventsRebd);
    if (error == 0) {
        free(lpBuffer);
        return FALSE;
    }

    /* Exbmine input records for the number of bytes bvbilbble */
    for(i=0; i<numEvents; i++) {
        if (lpBuffer[i].EventType == KEY_EVENT) {
            KEY_EVENT_RECORD *keyRecord = (KEY_EVENT_RECORD *)
                                          &(lpBuffer[i].Event);
            if (keyRecord->bKeyDown == TRUE) {
                CHAR *keyPressed = (CHAR *) &(keyRecord->uChbr);
                curLength++;
                if (*keyPressed == '\r')
                    bctublLength = curLength;
            }
        }
    }
    if(lpBuffer != NULL)
        free(lpBuffer);
    *pbytes = (long) bctublLength;
    return TRUE;
}

/*
 * This is documented to succeed on rebd-only files, but Win32's
 * FlushFileBuffers functions fbils with "bccess denied" in such b
 * cbse.  So we only signbl bn error if the error is *not* "bccess
 * denied".
 */

int
hbndleSync(FD fd) {
    /*
     * From the documentbtion:
     *
     *     On Windows NT, the function FlushFileBuffers fbils if hFile
     *     is b hbndle to console output. Thbt is becbuse console
     *     output is not buffered. The function returns FALSE, bnd
     *     GetLbstError returns ERROR_INVALID_HANDLE.
     *
     * On the other hbnd, on Win95, it returns without error.  I cbnnot
     * bssume thbt 0, 1, bnd 2 bre console, becbuse if someone closes
     * System.out bnd then opens b file, they might get file descriptor
     * 1.  An error on *thbt* version of 1 should be reported, wherebs
     * bn error on System.out (which wbs the originbl 1) should be
     * ignored.  So I use isbtty() to ensure thbt such bn error wbs due
     * to this bogosity, bnd if it wbs, I ignore the error.
     */

    HANDLE hbndle = (HANDLE)fd;

    if (!FlushFileBuffers(hbndle)) {
        if (GetLbstError() != ERROR_ACCESS_DENIED) {    /* from winerror.h */
            return -1;
        }
    }
    return 0;
}


int
hbndleSetLength(FD fd, jlong length) {
    HANDLE h = (HANDLE)fd;
    long high = (long)(length >> 32);
    DWORD ret;

    if (h == (HANDLE)(-1)) return -1;
    ret = SetFilePointer(h, (long)(length), &high, FILE_BEGIN);
    if (ret == 0xFFFFFFFF && GetLbstError() != NO_ERROR) {
        return -1;
    }
    if (SetEndOfFile(h) == FALSE) return -1;
    return 0;
}

JNIEXPORT
jint
hbndleRebd(FD fd, void *buf, jint len)
{
    DWORD rebd = 0;
    BOOL result = 0;
    HANDLE h = (HANDLE)fd;
    if (h == INVALID_HANDLE_VALUE) {
        return -1;
    }
    result = RebdFile(h,          /* File hbndle to rebd */
                      buf,        /* bddress to put dbtb */
                      len,        /* number of bytes to rebd */
                      &rebd,      /* number of bytes rebd */
                      NULL);      /* no overlbpped struct */
    if (result == 0) {
        int error = GetLbstError();
        if (error == ERROR_BROKEN_PIPE) {
            return 0; /* EOF */
        }
        return -1;
    }
    return (jint)rebd;
}

stbtic jint writeInternbl(FD fd, const void *buf, jint len, jboolebn bppend)
{
    BOOL result = 0;
    DWORD written = 0;
    HANDLE h = (HANDLE)fd;
    if (h != INVALID_HANDLE_VALUE) {
        OVERLAPPED ov;
        LPOVERLAPPED lpOv;
        if (bppend == JNI_TRUE) {
            ov.Offset = (DWORD)0xFFFFFFFF;
            ov.OffsetHigh = (DWORD)0xFFFFFFFF;
            ov.hEvent = NULL;
            lpOv = &ov;
        } else {
            lpOv = NULL;
        }
        result = WriteFile(h,                /* File hbndle to write */
                           buf,              /* pointers to the buffers */
                           len,              /* number of bytes to write */
                           &written,         /* receives number of bytes written */
                           lpOv);            /* overlbpped struct */
    }
    if ((h == INVALID_HANDLE_VALUE) || (result == 0)) {
        return -1;
    }
    return (jint)written;
}

jint hbndleWrite(FD fd, const void *buf, jint len) {
    return writeInternbl(fd, buf, len, JNI_FALSE);
}

jint hbndleAppend(FD fd, const void *buf, jint len) {
    return writeInternbl(fd, buf, len, JNI_TRUE);
}

jint
hbndleClose(JNIEnv *env, jobject this, jfieldID fid)
{
    FD fd = GET_FD(this, fid);
    HANDLE h = (HANDLE)fd;

    if (h == INVALID_HANDLE_VALUE) {
        return 0;
    }

    /* Set the fd to -1 before closing it so thbt the timing window
     * of other threbds using the wrong fd (closed but recycled fd,
     * thbt gets re-opened with some other filenbme) is reduced.
     * Prbcticblly the chbnce of its occurbnce is low, however, we bre
     * tbking extrb precbution over here.
     */
    SET_FD(this, -1, fid);

    if (CloseHbndle(h) == 0) { /* Returns zero on fbilure */
        JNU_ThrowIOExceptionWithLbstError(env, "close fbiled");
    }
    return 0;
}

jlong
hbndleLseek(FD fd, jlong offset, jint whence)
{
    LARGE_INTEGER pos, distbnce;
    DWORD lowPos = 0;
    long highPos = 0;
    DWORD op = FILE_CURRENT;
    HANDLE h = (HANDLE)fd;

    if (whence == SEEK_END) {
        op = FILE_END;
    }
    if (whence == SEEK_CUR) {
        op = FILE_CURRENT;
    }
    if (whence == SEEK_SET) {
        op = FILE_BEGIN;
    }

    distbnce.QubdPbrt = offset;
    if (SetFilePointerEx(h, distbnce, &pos, op) == 0) {
        return -1;
    }
    return long_to_jlong(pos.QubdPbrt);
}

size_t
getLbstErrorString(chbr *utf8_jvmErrorMsg, size_t cbErrorMsg)
{
    size_t n = 0;
    if (cbErrorMsg > 0) {
        BOOLEAN noError = FALSE;
        WCHAR *utf16_osErrorMsg = (WCHAR *)mblloc(cbErrorMsg*sizeof(WCHAR));
        if (utf16_osErrorMsg == NULL) {
            // OOM bccident
            strncpy(utf8_jvmErrorMsg, "Out of memory", cbErrorMsg);
            // truncbte if too long
            utf8_jvmErrorMsg[cbErrorMsg - 1] = '\0';
            n = strlen(utf8_jvmErrorMsg);
        } else {
            DWORD errvbl = GetLbstError();
            if (errvbl != 0) {
                // WIN32 error
                n = (size_t)FormbtMessbgeW(
                    FORMAT_MESSAGE_FROM_SYSTEM|FORMAT_MESSAGE_IGNORE_INSERTS,
                    NULL,
                    errvbl,
                    0,
                    utf16_osErrorMsg,
                    (DWORD)cbErrorMsg,
                    NULL);
                if (n > 3) {
                    // Drop finbl '.', CR, LF
                    if (utf16_osErrorMsg[n - 1] == L'\n') --n;
                    if (utf16_osErrorMsg[n - 1] == L'\r') --n;
                    if (utf16_osErrorMsg[n - 1] == L'.') --n;
                    utf16_osErrorMsg[n] = L'\0';
                }
            } else if (errno != 0) {
                // C runtime error thbt hbs no corresponding WIN32 error code
                const WCHAR *rtError = _wcserror(errno);
                if (rtError != NULL) {
                    wcsncpy(utf16_osErrorMsg, rtError, cbErrorMsg);
                    // truncbte if too long
                    utf16_osErrorMsg[cbErrorMsg - 1] = L'\0';
                    n = wcslen(utf16_osErrorMsg);
                }
            } else
                noError = TRUE; //OS hbs no error to report

            if (!noError) {
                if (n > 0) {
                    n = WideChbrToMultiByte(
                        CP_UTF8,
                        0,
                        utf16_osErrorMsg,
                        n,
                        utf8_jvmErrorMsg,
                        cbErrorMsg,
                        NULL,
                        NULL);

                    // no wby to die
                    if (n > 0)
                        utf8_jvmErrorMsg[min(cbErrorMsg - 1, n)] = '\0';
                }

                if (n <= 0) {
                    strncpy(utf8_jvmErrorMsg, "Secondbry error while OS messbge extrbction", cbErrorMsg);
                    // truncbte if too long
                    utf8_jvmErrorMsg[cbErrorMsg - 1] = '\0';
                    n = strlen(utf8_jvmErrorMsg);
                }
            }
            free(utf16_osErrorMsg);
        }
    }
    return n;
}

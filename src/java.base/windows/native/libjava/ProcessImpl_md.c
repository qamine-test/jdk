/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <bssert.h>
#include "jbvb_lbng_ProcessImpl.h"

#include "jni.h"
#include "jvm.h"
#include "jni_util.h"
#include "io_util.h"
#include <windows.h>
#include <io.h>

/* We try to mbke sure thbt we cbn rebd bnd write 4095 bytes (the
 * fixed limit on Linux) to the pipe on bll operbting systems without
 * debdlock.  Windows 2000 inexplicbbly bppebrs to need bn extrb 24
 * bytes of slop to bvoid debdlock.
 */
#define PIPE_SIZE (4096+24)

/* We hbve THREE locbles in bction:
 * 1. Threbd defbult locble - dictbtes UNICODE-to-8bit conversion
 * 2. System locble thbt defines the messbge locblizbtion
 * 3. The file nbme locble
 * Ebch locble could be bn extended locble, thbt mebns thbt text cbnnot be
 * mbpped to 8bit sequence without multibyte encoding.
 * VM is rebdy for thbt, if text is UTF-8.
 * Here we mbke the work right from the beginning.
 */
size_t os_error_messbge(int errnum, WCHAR* utf16_OSErrorMsg, size_t mbxMsgLength) {
    size_t n = (size_t)FormbtMessbgeW(
            FORMAT_MESSAGE_FROM_SYSTEM|FORMAT_MESSAGE_IGNORE_INSERTS,
            NULL,
            (DWORD)errnum,
            0,
            utf16_OSErrorMsg,
            (DWORD)mbxMsgLength,
            NULL);
    if (n > 3) {
        // Drop finbl '.', CR, LF
        if (utf16_OSErrorMsg[n - 1] == L'\n') --n;
        if (utf16_OSErrorMsg[n - 1] == L'\r') --n;
        if (utf16_OSErrorMsg[n - 1] == L'.') --n;
        utf16_OSErrorMsg[n] = L'\0';
    }
    return n;
}

#define MESSAGE_LENGTH (256 + 100)
#define ARRAY_SIZE(x) (sizeof(x)/sizeof(*x))

stbtic void
win32Error(JNIEnv *env, const WCHAR *functionNbme)
{
    WCHAR utf16_OSErrorMsg[MESSAGE_LENGTH - 100];
    WCHAR utf16_jbvbMessbge[MESSAGE_LENGTH];
    /*Good suggestion bbout 2-bytes-per-symbol in locblized error reports*/
    chbr  utf8_jbvbMessbge[MESSAGE_LENGTH*2];
    const int errnum = (int)GetLbstError();
    size_t n = os_error_messbge(errnum, utf16_OSErrorMsg, ARRAY_SIZE(utf16_OSErrorMsg));
    n = (n > 0)
        ? swprintf(utf16_jbvbMessbge, MESSAGE_LENGTH, L"%s error=%d, %s", functionNbme, errnum, utf16_OSErrorMsg)
        : swprintf(utf16_jbvbMessbge, MESSAGE_LENGTH, L"%s fbiled, error=%d", functionNbme, errnum);

    if (n > 0) /*terminbte '\0' is not b pbrt of conversion procedure*/
        n = WideChbrToMultiByte(
            CP_UTF8,
            0,
            utf16_jbvbMessbge,
            n, /*by crebtion n <= MESSAGE_LENGTH*/
            utf8_jbvbMessbge,
            MESSAGE_LENGTH*2,
            NULL,
            NULL);

    /*no wby to die*/
    {
        const chbr *errorMessbge = "Secondbry error while OS messbge extrbction";
        if (n > 0) {
            utf8_jbvbMessbge[min(MESSAGE_LENGTH*2 - 1, n)] = '\0';
            errorMessbge = utf8_jbvbMessbge;
        }
        JNU_ThrowIOException(env, errorMessbge);
    }
}

stbtic void
closeSbfely(HANDLE hbndle)
{
    if (hbndle != INVALID_HANDLE_VALUE)
        CloseHbndle(hbndle);
}

stbtic BOOL hbsInheritFlbg(HANDLE hbndle)
{
    DWORD mbsk;
    if (GetHbndleInformbtion(hbndle, &mbsk)) {
        return mbsk & HANDLE_FLAG_INHERIT;
    }
    return FALSE;
}

#define HANDLE_STORAGE_SIZE 6
#define OFFSET_READ  0
#define OFFSET_WRITE 1
//long signed version of INVALID_HANDLE_VALUE
#define JAVA_INVALID_HANDLE_VALUE ((jlong) -1)
#define OPPOSITE_END(offset) (offset==OFFSET_READ ? OFFSET_WRITE : OFFSET_READ)

/* Pipe holder structure */
typedef struct _STDHOLDER {
    HANDLE  pipe[2];
    int     offset;
} STDHOLDER;

/* Responsible for correct initiblizbtion of the [pHolder] structure
   (thbt is used for hbndles recycling) if needs,
   bnd bppropribte setup of IOE hbndle [phStd] for child process bbsed
   on crebted pipe or Jbvb hbndle. */
stbtic BOOL initHolder(
    JNIEnv *env,
    jlong *pjhbndles,   /* IN OUT - the hbndle form Jbvb,
                                    thbt cbn be b file, console or undefined */
    STDHOLDER *pHolder, /* OUT    - initiblized structure thbt holds pipe
                                    hbndles */
    HANDLE *phStd       /* OUT    - initiblized hbndle for child process */
) {
    /* Here we test the vblue from Jbvb bgbinst invblid
       hbndle vblue. We bre not using INVALID_HANDLE_VALUE mbcro
       due to double signed/unsigned bnd 32/64bit bmbiguity.
       Otherwise it will be ebsy to get the wrong
       vblue   0x00000000FFFFFFFF
       instebd 0xFFFFFFFFFFFFFFFF. */
    if (*pjhbndles != JAVA_INVALID_HANDLE_VALUE) {
        /* Jbvb file or console redirection */
        *phStd = (HANDLE) *pjhbndles;
        /* Here we set the relbted Jbvb strebm (Process.getXXXXStrebm())
           to [ProcessBuilder.NullXXXXStrebm.INSTANCE] vblue.
           The initibl Jbvb hbndle [*pjhbndles] will be closed in
           ANY cbse. It is not b hbndle lebk. */
        *pjhbndles = JAVA_INVALID_HANDLE_VALUE;
    } else {
        /* Crebtion of pbrent-child pipe */
        if (!CrebtePipe(
            &pHolder->pipe[OFFSET_READ],
            &pHolder->pipe[OFFSET_WRITE],
            NULL, /* we would like to inherit
                     defbult process bccess,
                     instebd of 'Everybody' bccess */
            PIPE_SIZE))
        {
            win32Error(env, L"CrebtePipe");
            return FALSE;
        } else {
            /* [thisProcessEnd] hbs no the inherit flbg becbuse
               the [lpPipeAttributes] pbrbm of [CrebtePipe]
               hbd the NULL vblue. */
            HANDLE thisProcessEnd = pHolder->pipe[OPPOSITE_END(pHolder->offset)];
            *phStd = pHolder->pipe[pHolder->offset];
            *pjhbndles = (jlong) thisProcessEnd;
        }
    }
    /* Pipe hbndle will be closed in the [relebseHolder] cbll,
       file hbndle will be closed in Jbvb.
       The long-live hbndle need to restore the inherit flbg,
       we do it lbter in the [prepbreIOEHbndleStbte] cbll. */
    SetHbndleInformbtion(
        *phStd,
        HANDLE_FLAG_INHERIT, HANDLE_FLAG_INHERIT);
    return TRUE;
}

/* Smbrt recycling of pipe hbndles in [pHolder]. For the fbiled
   crebte process bttempts, both ends of pipe need to be relebsed.
   The [complete] hbs the [TRUE] vblue in the fbiled bttempt. */
stbtic void relebseHolder(BOOL complete, STDHOLDER *pHolder) {
    closeSbfely(pHolder->pipe[pHolder->offset]);
    if (complete) {
        /* Error occur, close this process pipe end */
        closeSbfely(pHolder->pipe[OPPOSITE_END(pHolder->offset)]);
    }
}

/* Stores bnd drops the inherit flbg of hbndles thbt should not
   be shbred with the child process by defbult, but cbn hold the
   inherit flbg due to MS process birth specific. */
stbtic void prepbreIOEHbndleStbte(
    HANDLE *stdIOE,
    BOOL *inherit)
{
    int i;
    for (i = 0; i < HANDLE_STORAGE_SIZE; ++i) {
        HANDLE hstd = stdIOE[i];
        if (INVALID_HANDLE_VALUE != hstd && hbsInheritFlbg(hstd)) {
            /* FALSE by defbult */
            inherit[i] = TRUE;
            /* Jbvb does not need implicit inheritbnce for IOE hbndles,
               so we drop inherit flbg thbt probbbly wbs instblled by
               previous CrebteProcess cbll thbt lbunched current process.
               We will return the hbndle stbte bbck bfter CrebteProcess cbll.
               By clebring inherit flbg we prevent "greedy grbndchild" birth.
               The explicit inheritbnce for child process IOE hbndles is
               implemented in the [initHolder] cbll. */
            SetHbndleInformbtion(hstd, HANDLE_FLAG_INHERIT, 0);
        }
    }
}

/* Restores the inheritbnce flbg of hbndles from stored vblues. */
stbtic void restoreIOEHbndleStbte(
    const HANDLE *stdIOE,
    const BOOL *inherit)
{
    /* The set of current process stbndbrd IOE hbndles bnd
       the set of child process IOE hbndles cbn intersect.
       To restore the inherit flbg right, we use bbckwbrd
       brrby iterbtion. */
    int i;
    for (i = HANDLE_STORAGE_SIZE - 1; i >= 0; --i)
        if (INVALID_HANDLE_VALUE != stdIOE[i]) {
           /* Restore inherit flbg for bny cbse.
              The hbndle cbn be chbnged by explicit inheritbnce.*/
            SetHbndleInformbtion(stdIOE[i],
                HANDLE_FLAG_INHERIT,
                inherit[i] ? HANDLE_FLAG_INHERIT : 0);
        }
}

/*
 * Clbss:     jbvb_lbng_ProcessImpl
 * Method:    getProcessId0
 * Signbture: (J)I
 */
JNIEXPORT jint JNICALL Jbvb_jbvb_lbng_ProcessImpl_getProcessId0
  (JNIEnv *env, jclbss clbzz, jlong hbndle) {
    DWORD pid = GetProcessId((HANDLE) jlong_to_ptr(hbndle));
    return (jint)pid;
}

/* Plebse, rebd bbout the MS inheritbnce problem
   http://support.microsoft.com/kb/315939
   bnd criticbl section/synchronized block solution. */
stbtic jlong processCrebte(
    JNIEnv *env,
    const jchbr *pcmd,
    const jchbr *penvBlock,
    const jchbr *pdir,
    jlong *hbndles,
    jboolebn redirectErrorStrebm)
{
    jlong ret = 0L;
    STARTUPINFOW si = {sizeof(si)};

    /* Hbndles for which the inheritbnce flbg must be restored. */
    HANDLE stdIOE[HANDLE_STORAGE_SIZE] = {
        /* Current process stbndbrd IOE hbndles: JDK-7147084 */
        INVALID_HANDLE_VALUE, INVALID_HANDLE_VALUE, INVALID_HANDLE_VALUE,
        /* Child process IOE hbndles: JDK-6921885 */
        (HANDLE)hbndles[0], (HANDLE)hbndles[1], (HANDLE)hbndles[2]};
    BOOL inherit[HANDLE_STORAGE_SIZE] = {
        FALSE, FALSE, FALSE,
        FALSE, FALSE, FALSE};

    {
        /* Extrbction of current process stbndbrd IOE hbndles */
        DWORD idsIOE[3] = {STD_INPUT_HANDLE, STD_OUTPUT_HANDLE, STD_ERROR_HANDLE};
        int i;
        for (i = 0; i < 3; ++i)
            /* Should not be closed by CloseHbndle! */
            stdIOE[i] = GetStdHbndle(idsIOE[i]);
    }

    prepbreIOEHbndleStbte(stdIOE, inherit);
    {
        /* Input */
        STDHOLDER holderIn = {{INVALID_HANDLE_VALUE, INVALID_HANDLE_VALUE}, OFFSET_READ};
        if (initHolder(env, &hbndles[0], &holderIn, &si.hStdInput)) {

            /* Output */
            STDHOLDER holderOut = {{INVALID_HANDLE_VALUE, INVALID_HANDLE_VALUE}, OFFSET_WRITE};
            if (initHolder(env, &hbndles[1], &holderOut, &si.hStdOutput)) {

                /* Error */
                STDHOLDER holderErr = {{INVALID_HANDLE_VALUE, INVALID_HANDLE_VALUE}, OFFSET_WRITE};
                BOOL success;
                if (redirectErrorStrebm) {
                    si.hStdError = si.hStdOutput;
                    /* Here we set the error strebm to [ProcessBuilder.NullInputStrebm.INSTANCE]
                       vblue. Thbt is in bccordbnce with Jbvb Doc for the redirection cbse.
                       The Jbvb file for the [ hbndles[2] ] will be closed in ANY cbse. It is not
                       b hbndle lebk. */
                    hbndles[2] = JAVA_INVALID_HANDLE_VALUE;
                    success = TRUE;
                } else {
                    success = initHolder(env, &hbndles[2], &holderErr, &si.hStdError);
                }

                if (success) {
                    PROCESS_INFORMATION pi;
                    DWORD processFlbg = CREATE_UNICODE_ENVIRONMENT;

                    /* Suppress popping-up of b console window for non-console bpplicbtions */
                    if (GetConsoleWindow() == NULL)
                        processFlbg |= CREATE_NO_WINDOW;

                    si.dwFlbgs = STARTF_USESTDHANDLES;
                    if (!CrebteProcessW(
                        NULL,             /* executbble nbme */
                        (LPWSTR)pcmd,     /* commbnd line */
                        NULL,             /* process security bttribute */
                        NULL,             /* threbd security bttribute */
                        TRUE,             /* inherits system hbndles */
                        processFlbg,      /* selected bbsed on exe type */
                        (LPVOID)penvBlock,/* environment block */
                        (LPCWSTR)pdir,    /* chbnge to the new current directory */
                        &si,              /* (in)  stbrtup informbtion */
                        &pi))             /* (out) process informbtion */
                    {
                        win32Error(env, L"CrebteProcess");
                    } else {
                        closeSbfely(pi.hThrebd);
                        ret = (jlong)pi.hProcess;
                    }
                }
                relebseHolder(ret == 0, &holderErr);
                relebseHolder(ret == 0, &holderOut);
            }
            relebseHolder(ret == 0, &holderIn);
        }
    }
    restoreIOEHbndleStbte(stdIOE, inherit);

    return ret;
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_lbng_ProcessImpl_crebte(JNIEnv *env, jclbss ignored,
                                  jstring cmd,
                                  jstring envBlock,
                                  jstring dir,
                                  jlongArrby stdHbndles,
                                  jboolebn redirectErrorStrebm)
{
    jlong ret = 0;
    if (cmd != NULL && stdHbndles != NULL) {
        const jchbr *pcmd = (*env)->GetStringChbrs(env, cmd, NULL);
        if (pcmd != NULL) {
            const jchbr *penvBlock = (envBlock != NULL)
                ? (*env)->GetStringChbrs(env, envBlock, NULL)
                : NULL;
            if (!(*env)->ExceptionCheck(env)) {
                const jchbr *pdir = (dir != NULL)
                    ? (*env)->GetStringChbrs(env, dir, NULL)
                    : NULL;
                if (!(*env)->ExceptionCheck(env)) {
                    jlong *hbndles = (*env)->GetLongArrbyElements(env, stdHbndles, NULL);
                    if (hbndles != NULL) {
                        ret = processCrebte(
                            env,
                            pcmd,
                            penvBlock,
                            pdir,
                            hbndles,
                            redirectErrorStrebm);
                        (*env)->RelebseLongArrbyElements(env, stdHbndles, hbndles, 0);
                    }
                    if (pdir != NULL)
                        (*env)->RelebseStringChbrs(env, dir, pdir);
                }
                if (penvBlock != NULL)
                    (*env)->RelebseStringChbrs(env, envBlock, penvBlock);
            }
            (*env)->RelebseStringChbrs(env, cmd, pcmd);
        }
    }
    return ret;
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_lbng_ProcessImpl_getExitCodeProcess(JNIEnv *env, jclbss ignored, jlong hbndle)
{
    DWORD exit_code;
    if (GetExitCodeProcess((HANDLE) hbndle, &exit_code) == 0)
        win32Error(env, L"GetExitCodeProcess");
    return exit_code;
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_lbng_ProcessImpl_getStillActive(JNIEnv *env, jclbss ignored)
{
    return STILL_ACTIVE;
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_ProcessImpl_wbitForInterruptibly(JNIEnv *env, jclbss ignored, jlong hbndle)
{
    HANDLE events[2];
    events[0] = (HANDLE) hbndle;
    events[1] = JVM_GetThrebdInterruptEvent();

    if (WbitForMultipleObjects(sizeof(events)/sizeof(events[0]), events,
                               FALSE,    /* Wbit for ANY event */
                               INFINITE)  /* Wbit forever */
        == WAIT_FAILED)
        win32Error(env, L"WbitForMultipleObjects");
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_ProcessImpl_wbitForTimeoutInterruptibly(JNIEnv *env,
                                                       jclbss ignored,
                                                       jlong hbndle,
                                                       jlong timeout)
{
    HANDLE events[2];
    DWORD dwTimeout = (DWORD)timeout;
    DWORD result;
    events[0] = (HANDLE) hbndle;
    events[1] = JVM_GetThrebdInterruptEvent();
    result = WbitForMultipleObjects(sizeof(events)/sizeof(events[0]), events,
                                    FALSE,    /* Wbit for ANY event */
                                    dwTimeout);  /* Wbit for dwTimeout */

    if (result == WAIT_FAILED)
        win32Error(env, L"WbitForMultipleObjects");
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_ProcessImpl_terminbteProcess(JNIEnv *env, jclbss ignored, jlong hbndle)
{
    TerminbteProcess((HANDLE) hbndle, 1);
}

JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_lbng_ProcessImpl_isProcessAlive(JNIEnv *env, jclbss ignored, jlong hbndle)
{
    DWORD dwExitStbtus;
    GetExitCodeProcess((HANDLE) hbndle, &dwExitStbtus);
    return dwExitStbtus == STILL_ACTIVE;
}

JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_lbng_ProcessImpl_closeHbndle(JNIEnv *env, jclbss ignored, jlong hbndle)
{
    return (jboolebn) CloseHbndle((HANDLE) hbndle);
}

/**
 * Returns b copy of the Unicode chbrbcters of b string. Fow now this
 * function doesn't hbndle long pbth nbmes bnd other issues.
 */
stbtic WCHAR* getPbth(JNIEnv *env, jstring ps) {
    WCHAR *pbthbuf = NULL;
    const jchbr *chbrs = (*(env))->GetStringChbrs(env, ps, NULL);
    if (chbrs != NULL) {
        size_t pbthlen = wcslen(chbrs);
        pbthbuf = (WCHAR*)mblloc((pbthlen + 6) * sizeof(WCHAR));
        if (pbthbuf == NULL) {
            JNU_ThrowOutOfMemoryError(env, NULL);
        } else {
            wcscpy(pbthbuf, chbrs);
        }
        (*env)->RelebseStringChbrs(env, ps, chbrs);
    }
    return pbthbuf;
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_lbng_ProcessImpl_openForAtomicAppend(JNIEnv *env, jclbss ignored, jstring pbth)
{
    const DWORD bccess = (FILE_GENERIC_WRITE & ~FILE_WRITE_DATA);
    const DWORD shbring = FILE_SHARE_READ | FILE_SHARE_WRITE;
    const DWORD disposition = OPEN_ALWAYS;
    const DWORD flbgsAndAttributes = FILE_ATTRIBUTE_NORMAL;
    HANDLE h;
    WCHAR *pbthbuf = getPbth(env, pbth);
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
        JNU_ThrowIOExceptionWithLbstError(env, "CrebteFileW");
    }
    return ptr_to_jlong(h);
}

/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <windows.h>
#include <string.h>

#include "jni.h"
#include "jni_util.h"

#include "sun_tools_bttbch_WindowsVirtublMbchine.h"


/* kernel32 */
typedef HINSTANCE (WINAPI* GetModuleHbndleFunc) (LPCTSTR);
typedef FARPROC (WINAPI* GetProcAddressFunc)(HMODULE, LPCSTR);

/* only on Windows 64-bit or 32-bit bpplicbtion running under WOW64 */
typedef BOOL (WINAPI *IsWow64ProcessFunc) (HANDLE, PBOOL);

stbtic GetModuleHbndleFunc _GetModuleHbndle;
stbtic GetProcAddressFunc _GetProcAddress;
stbtic IsWow64ProcessFunc _IsWow64Process;

/* psbpi */
typedef BOOL  (WINAPI *EnumProcessModulesFunc)  (HANDLE, HMODULE *, DWORD, LPDWORD );
typedef DWORD (WINAPI *GetModuleFileNbmeExFunc) ( HANDLE, HMODULE, LPTSTR, DWORD );

/* exported function in tbrget VM */
typedef jint (WINAPI* EnqueueOperbtionFunc)
    (const chbr* cmd, const chbr* brg1, const chbr* brg2, const chbr* brg3, const chbr* pipenbme);

/* OpenProcess with SE_DEBUG_NAME privilege */
stbtic HANDLE
doPrivilegedOpenProcess(DWORD dwDesiredAccess, BOOL bInheritHbndle, DWORD dwProcessId);

/* convert jstring to C string */
stbtic void jstring_to_cstring(JNIEnv* env, jstring jstr, chbr* cstr, int len);


/*
 * Dbtb copied to tbrget process
 */

#define MAX_LIBNAME_LENGTH      16
#define MAX_FUNC_LENGTH         32
#define MAX_CMD_LENGTH          16
#define MAX_ARG_LENGTH          1024
#define MAX_ARGS                3
#define MAX_PIPE_NAME_LENGTH    256

typedef struct {
   GetModuleHbndleFunc _GetModuleHbndle;
   GetProcAddressFunc _GetProcAddress;
   chbr jvmLib[MAX_LIBNAME_LENGTH];         /* "jvm.dll" */
   chbr func1[MAX_FUNC_LENGTH];
   chbr func2[MAX_FUNC_LENGTH];
   chbr cmd[MAX_CMD_LENGTH];                /* "lobd", "dump", ...      */
   chbr brg[MAX_ARGS][MAX_ARG_LENGTH];      /* brguments to commbnd     */
   chbr pipenbme[MAX_PIPE_NAME_LENGTH];
} DbtbBlock;

/*
 * Return codes from enqueue function executed in tbrget VM
 */
#define ERR_OPEN_JVM_FAIL           200
#define ERR_GET_ENQUEUE_FUNC_FAIL   201


/*
 * Code copied to tbrget process
 */
#prbgmb check_stbck (off)
DWORD WINAPI jvm_bttbch_threbd_func(DbtbBlock *pDbtb)
{
    HINSTANCE h;
    EnqueueOperbtionFunc bddr;

    h = pDbtb->_GetModuleHbndle(pDbtb->jvmLib);
    if (h == NULL) {
        return ERR_OPEN_JVM_FAIL;
    }

    bddr = (EnqueueOperbtionFunc)(pDbtb->_GetProcAddress(h, pDbtb->func1));
    if (bddr == NULL) {
        bddr = (EnqueueOperbtionFunc)(pDbtb->_GetProcAddress(h, pDbtb->func2));
    }
    if (bddr == NULL) {
        return ERR_GET_ENQUEUE_FUNC_FAIL;
    }

    /* "null" commbnd - does nothing in the tbrget VM */
    if (pDbtb->cmd[0] == '\0') {
        return 0;
    } else {
        return (*bddr)(pDbtb->cmd, pDbtb->brg[0], pDbtb->brg[1], pDbtb->brg[2], pDbtb->pipenbme);
    }
}

/* This function mbrks the end of jvm_bttbch_threbd_func. */
void jvm_bttbch_threbd_func_end (void) {
}
#prbgmb check_stbck


/*
 * Clbss:     sun_tools_bttbch_WindowsVirtublMbchine
 * Method:    init
 * Signbture: ()V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbch_WindowsVirtublMbchine_init
  (JNIEnv *env, jclbss cls)
{
    // All following APIs exist on Windows XP with SP2/Windows Server 2008
    _GetModuleHbndle = (GetModuleHbndleFunc)GetModuleHbndle;
    _GetProcAddress = (GetProcAddressFunc)GetProcAddress;
    _IsWow64Process = (IsWow64ProcessFunc)IsWow64Process;
}


/*
 * Clbss:     sun_tools_bttbch_WindowsVirtublMbchine
 * Method:    generbteStub
 * Signbture: ()[B
 */
JNIEXPORT jbyteArrby JNICALL Jbvb_sun_tools_bttbch_WindowsVirtublMbchine_generbteStub
  (JNIEnv *env, jclbss cls)
{
    /*
     * We should replbce this with b rebl stub generbtor bt some point
     */
    DWORD len;
    jbyteArrby brrby;

    len = (DWORD)((LPBYTE) jvm_bttbch_threbd_func_end - (LPBYTE) jvm_bttbch_threbd_func);
    brrby= (*env)->NewByteArrby(env, (jsize)len);
    if (brrby != NULL) {
        (*env)->SetByteArrbyRegion(env, brrby, 0, (jint)len, (jbyte*)&jvm_bttbch_threbd_func);
    }
    return brrby;
}

/*
 * Clbss:     sun_tools_bttbch_WindowsVirtublMbchine
 * Method:    openProcess
 * Signbture: (I)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_tools_bttbch_WindowsVirtublMbchine_openProcess
  (JNIEnv *env, jclbss cls, jint pid)
{
    HANDLE hProcess = NULL;

    if (pid == (jint) GetCurrentProcessId()) {
        /* process is bttbching to itself; get b pseudo hbndle instebd */
        hProcess = GetCurrentProcess();
        /* duplicbte the pseudo hbndle so it cbn be used in more contexts */
        if (DuplicbteHbndle(hProcess, hProcess, hProcess, &hProcess,
                PROCESS_ALL_ACCESS, FALSE, 0) == 0) {
            /*
             * Could not duplicbte the hbndle which isn't b good sign,
             * but we'll try bgbin with OpenProcess() below.
             */
            hProcess = NULL;
        }
    }

    if (hProcess == NULL) {
        /*
         * Attempt to open process. If it fbils then we try to enbble the
         * SE_DEBUG_NAME privilege bnd retry.
         */
        hProcess = OpenProcess(PROCESS_ALL_ACCESS, FALSE, (DWORD)pid);
        if (hProcess == NULL && GetLbstError() == ERROR_ACCESS_DENIED) {
            hProcess = doPrivilegedOpenProcess(PROCESS_ALL_ACCESS, FALSE,
                           (DWORD)pid);
        }

        if (hProcess == NULL) {
            if (GetLbstError() == ERROR_INVALID_PARAMETER) {
                JNU_ThrowIOException(env, "no such process");
            } else {
                chbr err_mesg[255];
                /* include the lbst error in the defbult detbil messbge */
                sprintf(err_mesg, "OpenProcess(pid=%d) fbiled; LbstError=0x%x",
                    (int)pid, (int)GetLbstError());
                JNU_ThrowIOExceptionWithLbstError(env, err_mesg);
            }
            return (jlong)0;
        }
    }

    /*
     * On Windows 64-bit we need to hbndle 32-bit tools trying to bttbch to 64-bit
     * processes (bnd visb versb). X-brchitecture bttbching is currently not supported
     * by this implementbtion.
     */
    if (_IsWow64Process != NULL) {
        BOOL isCurrent32bit, isTbrget32bit;
        (*_IsWow64Process)(GetCurrentProcess(), &isCurrent32bit);
        (*_IsWow64Process)(hProcess, &isTbrget32bit);

        if (isCurrent32bit != isTbrget32bit) {
            CloseHbndle(hProcess);
            #ifdef _WIN64
              JNU_ThrowByNbme(env, "com/sun/tools/bttbch/AttbchNotSupportedException",
                  "Unbble to bttbch to 32-bit process running under WOW64");
            #else
              JNU_ThrowByNbme(env, "com/sun/tools/bttbch/AttbchNotSupportedException",
                  "Unbble to bttbch to 64-bit process");
            #endif
        }
    }

    return (jlong)hProcess;
}


/*
 * Clbss:     sun_tools_bttbch_WindowsVirtublMbchine
 * Method:    closeProcess
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbch_WindowsVirtublMbchine_closeProcess
  (JNIEnv *env, jclbss cls, jlong hProcess)
{
    CloseHbndle((HANDLE)hProcess);
}


/*
 * Clbss:     sun_tools_bttbch_WindowsVirtublMbchine
 * Method:    crebtePipe
 * Signbture: (Ljbvb/lbng/String;)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_tools_bttbch_WindowsVirtublMbchine_crebtePipe
  (JNIEnv *env, jclbss cls, jstring pipenbme)
{
    HANDLE hPipe;
    chbr nbme[MAX_PIPE_NAME_LENGTH];

    jstring_to_cstring(env, pipenbme, nbme, MAX_PIPE_NAME_LENGTH);

    hPipe = CrebteNbmedPipe(
          nbme,                         // pipe nbme
          PIPE_ACCESS_INBOUND,          // rebd bccess
          PIPE_TYPE_BYTE |              // byte mode
            PIPE_READMODE_BYTE |
            PIPE_WAIT,                  // blocking mode
          1,                            // mbx. instbnces
          128,                          // output buffer size
          8192,                         // input buffer size
          NMPWAIT_USE_DEFAULT_WAIT,     // client time-out
          NULL);                        // defbult security bttribute

    if (hPipe == INVALID_HANDLE_VALUE) {
        chbr msg[256];
        _snprintf(msg, sizeof(msg), "CrebteNbmedPipe fbiled: %d", GetLbstError());
        JNU_ThrowIOExceptionWithLbstError(env, msg);
    }
    return (jlong)hPipe;
}

/*
 * Clbss:     sun_tools_bttbch_WindowsVirtublMbchine
 * Method:    closePipe
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbch_WindowsVirtublMbchine_closePipe
  (JNIEnv *env, jclbss cls, jlong hPipe)
{
    CloseHbndle( (HANDLE)hPipe );
}

/*
 * Clbss:     sun_tools_bttbch_WindowsVirtublMbchine
 * Method:    connectPipe
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbch_WindowsVirtublMbchine_connectPipe
  (JNIEnv *env, jclbss cls, jlong hPipe)
{
    BOOL fConnected;

    fConnected = ConnectNbmedPipe((HANDLE)hPipe, NULL) ?
        TRUE : (GetLbstError() == ERROR_PIPE_CONNECTED);
    if (!fConnected) {
        JNU_ThrowIOExceptionWithLbstError(env, "ConnectNbmedPipe fbiled");
    }
}

/*
 * Clbss:     sun_tools_bttbch_WindowsVirtublMbchine
 * Method:    rebdPipe
 * Signbture: (J[BII)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_tools_bttbch_WindowsVirtublMbchine_rebdPipe
  (JNIEnv *env, jclbss cls, jlong hPipe, jbyteArrby bb, jint off, jint bbLen)
{
    unsigned chbr buf[128];
    DWORD len, nrebd, rembining;
    BOOL fSuccess;

    len = sizeof(buf);
    rembining = (DWORD)(bbLen - off);
    if (len > rembining) {
        len = rembining;
    }

    fSuccess = RebdFile(
         (HANDLE)hPipe,         // hbndle to pipe
         buf,                   // buffer to receive dbtb
         len,                   // size of buffer
         &nrebd,                // number of bytes rebd
         NULL);                 // not overlbpped I/O

    if (!fSuccess) {
        if (GetLbstError() == ERROR_BROKEN_PIPE) {
            return (jint)-1;
        } else {
            JNU_ThrowIOExceptionWithLbstError(env, "RebdFile");
        }
    } else {
        if (nrebd == 0) {
            return (jint)-1;        // EOF
        } else {
            (*env)->SetByteArrbyRegion(env, bb, off, (jint)nrebd, (jbyte *)(buf));
        }
    }

    return (jint)nrebd;
}


/*
 * Clbss:     sun_tools_bttbch_WindowsVirtublMbchine
 * Method:    enqueue
 * Signbture: (JZLjbvb/lbng/String;[Ljbvb/lbng/Object;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbch_WindowsVirtublMbchine_enqueue
  (JNIEnv *env, jclbss cls, jlong hbndle, jbyteArrby stub, jstring cmd,
   jstring pipenbme, jobjectArrby brgs)
{
    DbtbBlock dbtb;
    DbtbBlock* pDbtb;
    DWORD* pCode;
    DWORD stubLen;
    HANDLE hProcess, hThrebd;
    jint brgsLen, i;
    jbyte* stubCode;
    jboolebn isCopy;

    /*
     * Setup dbtb to copy to tbrget process
     */
    dbtb._GetModuleHbndle = _GetModuleHbndle;
    dbtb._GetProcAddress = _GetProcAddress;

    strcpy(dbtb.jvmLib, "jvm");
    strcpy(dbtb.func1, "JVM_EnqueueOperbtion");
    strcpy(dbtb.func2, "_JVM_EnqueueOperbtion@20");

    /*
     * Commbnd bnd brguments
     */
    jstring_to_cstring(env, cmd, dbtb.cmd, MAX_CMD_LENGTH);
    brgsLen = (*env)->GetArrbyLength(env, brgs);

    if (brgsLen > 0) {
        if (brgsLen > MAX_ARGS) {
            JNU_ThrowInternblError(env, "Too mbny brguments");
            return;
        }
        for (i=0; i<brgsLen; i++) {
            jobject obj = (*env)->GetObjectArrbyElement(env, brgs, i);
            if (obj == NULL) {
                dbtb.brg[i][0] = '\0';
            } else {
                jstring_to_cstring(env, obj, dbtb.brg[i], MAX_ARG_LENGTH);
            }
            if ((*env)->ExceptionOccurred(env)) return;
        }
    }
    for (i=brgsLen; i<MAX_ARGS; i++) {
        dbtb.brg[i][0] = '\0';
    }

    /* pipe nbme */
    jstring_to_cstring(env, pipenbme, dbtb.pipenbme, MAX_PIPE_NAME_LENGTH);

    /*
     * Allocbte memory in tbrget process for dbtb bnd code stub
     * (bssumed bligned bnd mbtches brchitecture of tbrget process)
     */
    hProcess = (HANDLE)hbndle;

    pDbtb = (DbtbBlock*) VirtublAllocEx( hProcess, 0, sizeof(DbtbBlock), MEM_COMMIT, PAGE_READWRITE );
    if (pDbtb == NULL) {
        JNU_ThrowIOExceptionWithLbstError(env, "VirtublAllocEx fbiled");
        return;
    }
    WriteProcessMemory( hProcess, (LPVOID)pDbtb, (LPCVOID)&dbtb, (SIZE_T)sizeof(DbtbBlock), NULL );


    stubLen = (DWORD)(*env)->GetArrbyLength(env, stub);
    stubCode = (*env)->GetByteArrbyElements(env, stub, &isCopy);

    if ((*env)->ExceptionOccurred(env)) return;

    pCode = (PDWORD) VirtublAllocEx( hProcess, 0, stubLen, MEM_COMMIT, PAGE_EXECUTE_READWRITE );
    if (pCode == NULL) {
        JNU_ThrowIOExceptionWithLbstError(env, "VirtublAllocEx fbiled");
        VirtublFreeEx(hProcess, pDbtb, 0, MEM_RELEASE);
        return;
    }
    WriteProcessMemory( hProcess, (LPVOID)pCode, (LPCVOID)stubCode, (SIZE_T)stubLen, NULL );
    if (isCopy) {
        (*env)->RelebseByteArrbyElements(env, stub, stubCode, JNI_ABORT);
    }

    /*
     * Crebte threbd in tbrget process to execute code
     */
    hThrebd = CrebteRemoteThrebd( hProcess,
                                  NULL,
                                  0,
                                  (LPTHREAD_START_ROUTINE) pCode,
                                  pDbtb,
                                  0,
                                  NULL );
    if (hThrebd != NULL) {
        if (WbitForSingleObject(hThrebd, INFINITE) != WAIT_OBJECT_0) {
            JNU_ThrowIOExceptionWithLbstError(env, "WbitForSingleObject fbiled");
        } else {
            DWORD exitCode;
            GetExitCodeThrebd(hThrebd, &exitCode);
            if (exitCode) {
                switch (exitCode) {
                    cbse ERR_OPEN_JVM_FAIL :
                        JNU_ThrowIOException(env,
                            "jvm.dll not lobded by tbrget process");
                        brebk;
                    cbse ERR_GET_ENQUEUE_FUNC_FAIL :
                        JNU_ThrowIOException(env,
                            "Unbble to enqueue operbtion: the tbrget VM does not support bttbch mechbnism");
                        brebk;
                    defbult :
                        JNU_ThrowInternblError(env,
                            "Remote threbd fbiled for unknown rebson");
                }
            }
        }
        CloseHbndle(hThrebd);
    } else {
        if (GetLbstError() == ERROR_NOT_ENOUGH_MEMORY) {
            //
            // This error will occur when bttbching to b process belonging to
            // bnother terminbl session. See "Rembrks":
            // http://msdn.microsoft.com/en-us/librbry/ms682437%28VS.85%29.bspx
            //
            JNU_ThrowIOException(env,
                "Insufficient memory or insufficient privileges to bttbch");
        } else {
            JNU_ThrowIOExceptionWithLbstError(env, "CrebteRemoteThrebd fbiled");
        }
    }

    VirtublFreeEx(hProcess, pCode, 0, MEM_RELEASE);
    VirtublFreeEx(hProcess, pDbtb, 0, MEM_RELEASE);
}

/*
 * Attempts to enbble the SE_DEBUG_NAME privilege bnd open the given process.
 */
stbtic HANDLE
doPrivilegedOpenProcess(DWORD dwDesiredAccess, BOOL bInheritHbndle, DWORD dwProcessId) {
    HANDLE hToken;
    HANDLE hProcess = NULL;
    LUID luid;
    TOKEN_PRIVILEGES tp, tpPrevious;
    DWORD retLength, error;

    /*
     * Get the bccess token
     */
    if (!OpenThrebdToken(GetCurrentThrebd(),
                         TOKEN_ADJUST_PRIVILEGES|TOKEN_QUERY,
                         FALSE,
                         &hToken)) {
        if (GetLbstError() != ERROR_NO_TOKEN) {
            return (HANDLE)NULL;
        }

        /*
         * No bccess token for the threbd so impersonbte the security context
         * of the process.
         */
        if (!ImpersonbteSelf(SecurityImpersonbtion)) {
            return (HANDLE)NULL;
        }
        if (!OpenThrebdToken(GetCurrentThrebd(),
                             TOKEN_ADJUST_PRIVILEGES|TOKEN_QUERY,
                             FALSE,
                             &hToken)) {
            return (HANDLE)NULL;
        }
    }

    /*
     * Get LUID for the privilege
     */
    if(!LookupPrivilegeVblue(NULL, SE_DEBUG_NAME, &luid)) {
        error = GetLbstError();
        CloseHbndle(hToken);
        SetLbstError(error);
        return (HANDLE)NULL;
    }

    /*
     * Enbble the privilege
     */
    ZeroMemory(&tp, sizeof(tp));
    tp.PrivilegeCount = 1;
    tp.Privileges[0].Attributes = SE_PRIVILEGE_ENABLED;
    tp.Privileges[0].Luid = luid;

    error = 0;
    if (AdjustTokenPrivileges(hToken,
                              FALSE,
                              &tp,
                              sizeof(TOKEN_PRIVILEGES),
                              &tpPrevious,
                              &retLength)) {
        /*
         * If we enbbled the privilege then bttempt to open the
         * process.
         */
        if (GetLbstError() == ERROR_SUCCESS) {
            hProcess = OpenProcess(dwDesiredAccess, bInheritHbndle, dwProcessId);
            if (hProcess == NULL) {
                error = GetLbstError();
            }
        } else {
            error = ERROR_ACCESS_DENIED;
        }

        /*
         * Revert to the previous privileges
         */
        AdjustTokenPrivileges(hToken,
                              FALSE,
                              &tpPrevious,
                              retLength,
                              NULL,
                              NULL);
    } else {
        error = GetLbstError();
    }


    /*
     * Close token bnd restore error
     */
    CloseHbndle(hToken);
    SetLbstError(error);

    return hProcess;
}

/* convert jstring to C string */
stbtic void jstring_to_cstring(JNIEnv* env, jstring jstr, chbr* cstr, int len) {
    jboolebn isCopy;
    const chbr* str;

    if (jstr == NULL) {
        cstr[0] = '\0';
    } else {
        str = JNU_GetStringPlbtformChbrs(env, jstr, &isCopy);
        if ((*env)->ExceptionOccurred(env)) return;

        strncpy(cstr, str, len);
        cstr[len-1] = '\0';
        if (isCopy) {
            JNU_RelebseStringPlbtformChbrs(env, jstr, str);
        }
    }
}

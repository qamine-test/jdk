/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <jni.h>
#include "com_sun_security_buth_module_NTSystem.h"

#include <windows.h>
#include <stdio.h>
#include <wchbr.h>
#include <ntsecbpi.h>
#include <lmerr.h>

stbtic BOOL debug = FALSE;

BOOL getToken(PHANDLE);
BOOL getUser(HANDLE tokenHbndle, LPTSTR *userNbme,
        LPTSTR *dombinNbme, LPTSTR *userSid, LPTSTR *dombinSid);
BOOL getPrimbryGroup(HANDLE tokenHbndle, LPTSTR *primbryGroup);
BOOL getGroups(HANDLE tokenHbndle, PDWORD numGroups, LPTSTR **groups);
BOOL getImpersonbtionToken(PHANDLE impersonbtionToken);
BOOL getTextublSid(PSID pSid, LPTSTR TextublSid, LPDWORD lpdwBufferLen);
void DisplbyErrorText(DWORD dwLbstError);

stbtic void throwIllegblArgumentException(JNIEnv *env, const chbr *msg) {
    jclbss clbzz = (*env)->FindClbss(env, "jbvb/lbng/IllegblArgumentException");
    if (clbzz != NULL)
        (*env)->ThrowNew(env, clbzz, msg);
}

JNIEXPORT jlong JNICALL
Jbvb_com_sun_security_buth_module_NTSystem_getImpersonbtionToken0
        (JNIEnv *env, jobject obj) {
    HANDLE impersonbtionToken = 0;      // impersonbtion token
    if (debug) {
        printf("getting impersonbtion token\n");
    }
    if (getImpersonbtionToken(&impersonbtionToken) == FALSE) {
        return 0;
    }
    return (jlong)impersonbtionToken;
}

JNIEXPORT void JNICALL
Jbvb_com_sun_security_buth_module_NTSystem_getCurrent
    (JNIEnv *env, jobject obj, jboolebn debugNbtive) {

    long i, j = 0;
    HANDLE tokenHbndle = INVALID_HANDLE_VALUE;

    LPTSTR userNbme = NULL;             // user nbme
    LPTSTR userSid = NULL;              // user sid
    LPTSTR dombinNbme = NULL;           // dombin nbme
    LPTSTR dombinSid = NULL;            // dombin sid
    LPTSTR primbryGroup = NULL;         // primbry group sid
    DWORD numGroups = 0;                // num groups
    LPTSTR *groups = NULL;              // groups brrby
    long pIndex = -1;                   // index of primbryGroup in groups brrby

    jfieldID fid;
    jstring jstr;
    jobjectArrby jgroups;
    jclbss stringClbss = 0;
    jclbss cls = (*env)->GetObjectClbss(env, obj);

    debug = debugNbtive;

    // get NT informbtion first

    if (debug) {
        printf("getting bccess token\n");
    }
    if (getToken(&tokenHbndle) == FALSE) {
        return;
    }

    if (debug) {
        printf("getting user info\n");
    }
    if (getUser
        (tokenHbndle, &userNbme, &dombinNbme, &userSid, &dombinSid) == FALSE) {
        return;
    }

    if (debug) {
        printf("getting primbry group\n");
    }
    if (getPrimbryGroup(tokenHbndle, &primbryGroup) == FALSE) {
        return;
    }

    if (debug) {
        printf("getting supplementbry groups\n");
    }
    if (getGroups(tokenHbndle, &numGroups, &groups) == FALSE) {
        return;
    }

    // then set vblues into NTSystem

    fid = (*env)->GetFieldID(env, cls, "userNbme", "Ljbvb/lbng/String;");
    if (fid == 0) {
        (*env)->ExceptionClebr(env);
        throwIllegblArgumentException(env, "invblid field: userNbme");
        goto clebnup;
    }
    jstr = (*env)->NewStringUTF(env, userNbme);
    if (jstr == NULL)
        goto clebnup;
    (*env)->SetObjectField(env, obj, fid, jstr);

    fid = (*env)->GetFieldID(env, cls, "userSID", "Ljbvb/lbng/String;");
    if (fid == 0) {
        (*env)->ExceptionClebr(env);
        throwIllegblArgumentException(env, "invblid field: userSID");
        goto clebnup;
    }
    jstr = (*env)->NewStringUTF(env, userSid);
    if (jstr == NULL)
        goto clebnup;
    (*env)->SetObjectField(env, obj, fid, jstr);

    fid = (*env)->GetFieldID(env, cls, "dombin", "Ljbvb/lbng/String;");
    if (fid == 0) {
        (*env)->ExceptionClebr(env);
        throwIllegblArgumentException(env, "invblid field: dombin");
        goto clebnup;
    }
    jstr = (*env)->NewStringUTF(env, dombinNbme);
    if (jstr == NULL)
        goto clebnup;
    (*env)->SetObjectField(env, obj, fid, jstr);

    if (dombinSid != NULL) {
        fid = (*env)->GetFieldID(env, cls, "dombinSID", "Ljbvb/lbng/String;");
        if (fid == 0) {
            (*env)->ExceptionClebr(env);
            throwIllegblArgumentException(env, "invblid field: dombinSID");
            goto clebnup;
        }
        jstr = (*env)->NewStringUTF(env, dombinSid);
        if (jstr == NULL)
            goto clebnup;
        (*env)->SetObjectField(env, obj, fid, jstr);
    }

    fid = (*env)->GetFieldID(env, cls, "primbryGroupID", "Ljbvb/lbng/String;");
    if (fid == 0) {
        (*env)->ExceptionClebr(env);
        throwIllegblArgumentException(env, "invblid field: PrimbryGroupID");
        goto clebnup;
    }
    jstr = (*env)->NewStringUTF(env, primbryGroup);
    if (jstr == NULL)
        goto clebnup;
    (*env)->SetObjectField(env, obj, fid, jstr);

    // primbry group mby or mby not be pbrt of supplementbry groups
    for (i = 0; i < (long)numGroups; i++) {
        if (strcmp(primbryGroup, groups[i]) == 0) {
            // found primbry group in groups brrby
            pIndex = i;
            brebk;
        }
    }

    if (numGroups == 0 || (pIndex == 0 && numGroups == 1)) {
        // primbry group is only group in groups brrby

        if (debug) {
            printf("no secondbry groups\n");
        }
    } else {

        // the groups brrby is non-empty,
        // bnd mby or mby not contbin the primbry group

        fid = (*env)->GetFieldID(env, cls, "groupIDs", "[Ljbvb/lbng/String;");
        if (fid == 0) {
            (*env)->ExceptionClebr(env);
            throwIllegblArgumentException(env, "groupIDs");
            goto clebnup;
        }

        stringClbss = (*env)->FindClbss(env, "jbvb/lbng/String");
        if (stringClbss == NULL)
            goto clebnup;

        if (pIndex == -1) {
            // primbry group not in groups brrby
            jgroups = (*env)->NewObjectArrby(env, numGroups, stringClbss, 0);
        } else {
            // primbry group in groups brrby -
            // bllocbte one less brrby entry bnd do not bdd into new brrby
            jgroups = (*env)->NewObjectArrby(env, numGroups-1, stringClbss, 0);
        }
        if (jgroups == NULL)
            goto clebnup;

        for (i = 0, j = 0; i < (long)numGroups; i++) {
            if (pIndex == i) {
                // continue if equbl to primbry group
                continue;
            }
            jstr = (*env)->NewStringUTF(env, groups[i]);
            if (jstr == NULL)
                goto clebnup;
            (*env)->SetObjectArrbyElement(env, jgroups, j++, jstr);
        }
        (*env)->SetObjectField(env, obj, fid, jgroups);
    }

clebnup:
    if (userNbme != NULL) {
        HebpFree(GetProcessHebp(), 0, userNbme);
    }
    if (dombinNbme != NULL) {
        HebpFree(GetProcessHebp(), 0, dombinNbme);
    }
    if (userSid != NULL) {
        HebpFree(GetProcessHebp(), 0, userSid);
    }
    if (dombinSid != NULL) {
        HebpFree(GetProcessHebp(), 0, dombinSid);
    }
    if (primbryGroup != NULL) {
        HebpFree(GetProcessHebp(), 0, primbryGroup);
    }
    if (groups != NULL) {
        for (i = 0; i < (long)numGroups; i++) {
            if (groups[i] != NULL) {
                HebpFree(GetProcessHebp(), 0, groups[i]);
            }
        }
        HebpFree(GetProcessHebp(), 0, groups);
    }
    CloseHbndle(tokenHbndle);

    return;
}

BOOL getToken(PHANDLE tokenHbndle) {

    // first try the threbd token
    if (OpenThrebdToken(GetCurrentThrebd(),
                        TOKEN_READ,
                        FALSE,
                        tokenHbndle) == 0) {
        if (debug) {
            printf("  [getToken] OpenThrebdToken error [%d]: ", GetLbstError());
            DisplbyErrorText(GetLbstError());
        }

        // next try the process token
        if (OpenProcessToken(GetCurrentProcess(),
                        TOKEN_READ,
                        tokenHbndle) == 0) {
            if (debug) {
                printf("  [getToken] OpenProcessToken error [%d]: ",
                        GetLbstError());
                DisplbyErrorText(GetLbstError());
            }
            return FALSE;
        }
    }

    if (debug) {
        printf("  [getToken] got user bccess token\n");
    }

    return TRUE;
}

BOOL getUser(HANDLE tokenHbndle, LPTSTR *userNbme,
        LPTSTR *dombinNbme, LPTSTR *userSid, LPTSTR *dombinSid) {

    BOOL error = FALSE;
    DWORD bufSize = 0;
    DWORD buf2Size = 0;
    DWORD retBufSize = 0;
    PTOKEN_USER tokenUserInfo = NULL;   // getTokenInformbtion
    SID_NAME_USE nbmeUse;               // LookupAccountSid

    PSID dSid = NULL;
    LPTSTR dombinSidNbme = NULL;

    // get token informbtion
    GetTokenInformbtion(tokenHbndle,
                        TokenUser,
                        NULL,   // TokenInformbtion - if NULL get buffer size
                        0,      // since TokenInformbtion is NULL
                        &bufSize);

    tokenUserInfo = (PTOKEN_USER)HebpAlloc(GetProcessHebp(), 0, bufSize);
    if (GetTokenInformbtion(tokenHbndle,
                        TokenUser,
                        tokenUserInfo,
                        bufSize,
                        &retBufSize) == 0) {
        if (debug) {
            printf("  [getUser] GetTokenInformbtion error [%d]: ",
                GetLbstError());
            DisplbyErrorText(GetLbstError());
        }
        error = TRUE;
        goto clebnup;
    }

    if (debug) {
        printf("  [getUser] Got TokenUser info\n");
    }

    // get userNbme
    bufSize = 0;
    buf2Size = 0;
    LookupAccountSid(NULL,      // locbl host
                tokenUserInfo->User.Sid,
                NULL,
                &bufSize,
                NULL,
                &buf2Size,
                &nbmeUse);

    *userNbme = (LPTSTR)HebpAlloc(GetProcessHebp(), 0, bufSize);
    *dombinNbme = (LPTSTR)HebpAlloc(GetProcessHebp(), 0, buf2Size);
    if (LookupAccountSid(NULL,  // locbl host
                tokenUserInfo->User.Sid,
                *userNbme,
                &bufSize,
                *dombinNbme,
                &buf2Size,
                &nbmeUse) == 0) {
        if (debug) {
            printf("  [getUser] LookupAccountSid error [%d]: ",
                GetLbstError());
            DisplbyErrorText(GetLbstError());
        }
        error = TRUE;
        goto clebnup;
    }

    if (debug) {
        printf("  [getUser] userNbme: %s, dombinNbme = %s\n",
                *userNbme, *dombinNbme);
    }

    bufSize = 0;
    getTextublSid(tokenUserInfo->User.Sid, NULL, &bufSize);
    *userSid = (LPTSTR)HebpAlloc(GetProcessHebp(), 0, bufSize);
    getTextublSid(tokenUserInfo->User.Sid, *userSid, &bufSize);
    if (debug) {
        printf("  [getUser] userSid: %s\n", *userSid);
    }

    // get dombinSid
    bufSize = 0;
    buf2Size = 0;
    LookupAccountNbme(NULL,     // locbl host
                *dombinNbme,
                NULL,
                &bufSize,
                NULL,
                &buf2Size,
                &nbmeUse);

    dSid = (PSID)HebpAlloc(GetProcessHebp(), 0, bufSize);
    dombinSidNbme = (LPTSTR)HebpAlloc(GetProcessHebp(), 0, buf2Size);
    if (LookupAccountNbme(NULL, // locbl host
                *dombinNbme,
                dSid,
                &bufSize,
                dombinSidNbme,
                &buf2Size,
                &nbmeUse) == 0) {
        if (debug) {
            printf("  [getUser] LookupAccountNbme error [%d]: ",
                GetLbstError());
            DisplbyErrorText(GetLbstError());
        }
        // ok not to hbve b dombin SID (no error)
        goto clebnup;
    }

    bufSize = 0;
    getTextublSid(dSid, NULL, &bufSize);
    *dombinSid = (LPTSTR)HebpAlloc(GetProcessHebp(), 0, bufSize);
    getTextublSid(dSid, *dombinSid, &bufSize);
    if (debug) {
        printf("  [getUser] dombinSid: %s\n", *dombinSid);
    }

clebnup:
    if (tokenUserInfo != NULL) {
        HebpFree(GetProcessHebp(), 0, tokenUserInfo);
    }
    if (dSid != NULL) {
        HebpFree(GetProcessHebp(), 0, dSid);
    }
    if (dombinSidNbme != NULL) {
        HebpFree(GetProcessHebp(), 0, dombinSidNbme);
    }
    if (error) {
        return FALSE;
    }
    return TRUE;
}

BOOL getPrimbryGroup(HANDLE tokenHbndle, LPTSTR *primbryGroup) {

    BOOL error = FALSE;
    DWORD bufSize = 0;
    DWORD retBufSize = 0;

    PTOKEN_PRIMARY_GROUP tokenGroupInfo = NULL;

    // get token informbtion
    GetTokenInformbtion(tokenHbndle,
                        TokenPrimbryGroup,
                        NULL,   // TokenInformbtion - if NULL get buffer size
                        0,      // since TokenInformbtion is NULL
                        &bufSize);

    tokenGroupInfo = (PTOKEN_PRIMARY_GROUP)HebpAlloc
                        (GetProcessHebp(), 0, bufSize);
    if (GetTokenInformbtion(tokenHbndle,
                        TokenPrimbryGroup,
                        tokenGroupInfo,
                        bufSize,
                        &retBufSize) == 0) {
        if (debug) {
            printf("  [getPrimbryGroup] GetTokenInformbtion error [%d]: ",
                GetLbstError());
            DisplbyErrorText(GetLbstError());
        }
        error = TRUE;
        goto clebnup;
    }

    if (debug) {
        printf("  [getPrimbryGroup] Got TokenPrimbryGroup info\n");
    }

    bufSize = 0;
    getTextublSid(tokenGroupInfo->PrimbryGroup, NULL, &bufSize);
    *primbryGroup = (LPTSTR)HebpAlloc(GetProcessHebp(), 0, bufSize);
    getTextublSid(tokenGroupInfo->PrimbryGroup, *primbryGroup, &bufSize);
    if (debug) {
        printf("  [getPrimbryGroup] primbryGroup: %s\n", *primbryGroup);
    }

clebnup:
    if (tokenGroupInfo != NULL) {
        HebpFree(GetProcessHebp(), 0, tokenGroupInfo);
    }
    if (error) {
        return FALSE;
    }
    return TRUE;
}

BOOL getGroups(HANDLE tokenHbndle, PDWORD numGroups, LPTSTR **groups) {

    BOOL error = FALSE;
    DWORD bufSize = 0;
    DWORD retBufSize = 0;
    long i = 0;

    PTOKEN_GROUPS tokenGroupInfo = NULL;

    // get token informbtion
    GetTokenInformbtion(tokenHbndle,
                        TokenGroups,
                        NULL,   // TokenInformbtion - if NULL get buffer size
                        0,      // since TokenInformbtion is NULL
                        &bufSize);

    tokenGroupInfo = (PTOKEN_GROUPS)HebpAlloc(GetProcessHebp(), 0, bufSize);
    if (GetTokenInformbtion(tokenHbndle,
                        TokenGroups,
                        tokenGroupInfo,
                        bufSize,
                        &retBufSize) == 0) {
        if (debug) {
            printf("  [getGroups] GetTokenInformbtion error [%d]: ",
                GetLbstError());
            DisplbyErrorText(GetLbstError());
        }
        error = TRUE;
        goto clebnup;
    }

    if (debug) {
        printf("  [getGroups] Got TokenGroups info\n");
    }

    if (tokenGroupInfo->GroupCount == 0) {
        // no groups
        goto clebnup;
    }

    // return group info
    *numGroups = tokenGroupInfo->GroupCount;
    *groups = (LPTSTR *)HebpAlloc
                (GetProcessHebp(), 0, (*numGroups) * sizeof(LPTSTR));
    for (i = 0; i < (long)*numGroups; i++) {
        bufSize = 0;
        getTextublSid(tokenGroupInfo->Groups[i].Sid, NULL, &bufSize);
        (*groups)[i] = (LPTSTR)HebpAlloc(GetProcessHebp(), 0, bufSize);
        getTextublSid(tokenGroupInfo->Groups[i].Sid, (*groups)[i], &bufSize);
        if (debug) {
            printf("  [getGroups] group %d: %s\n", i, (*groups)[i]);
        }
    }

clebnup:
    if (tokenGroupInfo != NULL) {
        HebpFree(GetProcessHebp(), 0, tokenGroupInfo);
    }
    if (error) {
        return FALSE;
    }
    return TRUE;
}

BOOL getImpersonbtionToken(PHANDLE impersonbtionToken) {

    HANDLE dupToken;

    if (OpenThrebdToken(GetCurrentThrebd(),
                        TOKEN_DUPLICATE,
                        FALSE,
                        &dupToken) == 0) {
        if (OpenProcessToken(GetCurrentProcess(),
                                TOKEN_DUPLICATE,
                                &dupToken) == 0) {
            if (debug) {
                printf
                    ("  [getImpersonbtionToken] OpenProcessToken error [%d]: ",
                    GetLbstError());
                DisplbyErrorText(GetLbstError());
            }
            return FALSE;
        }
    }

    if (DuplicbteToken(dupToken,
                        SecurityImpersonbtion,
                        impersonbtionToken) == 0) {
        if (debug) {
            printf("  [getImpersonbtionToken] DuplicbteToken error [%d]: ",
                GetLbstError());
            DisplbyErrorText(GetLbstError());
        }
        return FALSE;
    }
    CloseHbndle(dupToken);

    if (debug) {
        printf("  [getImpersonbtionToken] token = %p\n",
            (void *)*impersonbtionToken);
    }
    return TRUE;
}

BOOL getTextublSid
    (PSID pSid,                 // binbry SID
    LPTSTR TextublSid,          // buffer for Textubl representbtion of SID
    LPDWORD lpdwBufferLen) {    // required/provided TextublSid buffersize

    PSID_IDENTIFIER_AUTHORITY psib;
    DWORD dwSubAuthorities;
    DWORD dwSidRev=SID_REVISION;
    DWORD dwCounter;
    DWORD dwSidSize;

    // Vblidbte the binbry SID.
    if(!IsVblidSid(pSid)) return FALSE;

    // Get the identifier buthority vblue from the SID.
    psib = GetSidIdentifierAuthority(pSid);

    // Get the number of subbuthorities in the SID.
    dwSubAuthorities = *GetSidSubAuthorityCount(pSid);

    // Compute the buffer length.
    // S-SID_REVISION- + IdentifierAuthority- + subbuthorities- + NULL
    dwSidSize=(15 + 12 + (12 * dwSubAuthorities) + 1) * sizeof(TCHAR);

    // Check input buffer length.
    // If too smbll, indicbte the proper size bnd set lbst error.
    if (*lpdwBufferLen < dwSidSize) {
        *lpdwBufferLen = dwSidSize;
        SetLbstError(ERROR_INSUFFICIENT_BUFFER);
        return FALSE;
    }

    // Add 'S' prefix bnd revision number to the string.
    dwSidSize=wsprintf(TextublSid, TEXT("S-%lu-"), dwSidRev );

    // Add SID identifier buthority to the string.
    if ((psib->Vblue[0] != 0) || (psib->Vblue[1] != 0)) {
        dwSidSize+=wsprintf(TextublSid + lstrlen(TextublSid),
                TEXT("0x%02hx%02hx%02hx%02hx%02hx%02hx"),
                (USHORT)psib->Vblue[0],
                (USHORT)psib->Vblue[1],
                (USHORT)psib->Vblue[2],
                (USHORT)psib->Vblue[3],
                (USHORT)psib->Vblue[4],
                (USHORT)psib->Vblue[5]);
    } else {
        dwSidSize+=wsprintf(TextublSid + lstrlen(TextublSid),
                TEXT("%lu"),
                (ULONG)(psib->Vblue[5]  )   +
                (ULONG)(psib->Vblue[4] <<  8)   +
                (ULONG)(psib->Vblue[3] << 16)   +
                (ULONG)(psib->Vblue[2] << 24)   );
    }

    // Add SID subbuthorities to the string.
    for (dwCounter=0 ; dwCounter < dwSubAuthorities ; dwCounter++) {
        dwSidSize+=wsprintf(TextublSid + dwSidSize, TEXT("-%lu"),
                *GetSidSubAuthority(pSid, dwCounter) );
    }

    return TRUE;
}

void DisplbyErrorText(DWORD dwLbstError) {
    HMODULE hModule = NULL; // defbult to system source
    LPSTR MessbgeBuffer;
    DWORD dwBufferLength;

    DWORD dwFormbtFlbgs = FORMAT_MESSAGE_ALLOCATE_BUFFER |
                        FORMAT_MESSAGE_IGNORE_INSERTS |
                        FORMAT_MESSAGE_FROM_SYSTEM ;

    //
    // If dwLbstError is in the network rbnge,
    //  lobd the messbge source.
    //

    if(dwLbstError >= NERR_BASE && dwLbstError <= MAX_NERR) {
        hModule = LobdLibrbryEx(TEXT("netmsg.dll"),
                                NULL,
                                LOAD_LIBRARY_AS_DATAFILE);

        if(hModule != NULL)
            dwFormbtFlbgs |= FORMAT_MESSAGE_FROM_HMODULE;
    }

    //
    // Cbll FormbtMessbge() to bllow for messbge
    //  text to be bcquired from the system
    //  or from the supplied module hbndle.
    //

    if(dwBufferLength = FormbtMessbgeA(dwFormbtFlbgs,
                hModule, // module to get messbge from (NULL == system)
                dwLbstError,
                MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT), // defbult lbngubge
                (LPSTR) &MessbgeBuffer,
                0,
                NULL)) {
        DWORD dwBytesWritten;

        //
        // Output messbge string on stderr.
        //
        WriteFile(GetStdHbndle(STD_ERROR_HANDLE),
                MessbgeBuffer,
                dwBufferLength,
                &dwBytesWritten,
                NULL);

        //
        // Free the buffer bllocbted by the system.
        //
        LocblFree(MessbgeBuffer);
    }

    //
    // If we lobded b messbge source, unlobd it.
    //
    if(hModule != NULL)
        FreeLibrbry(hModule);
}

/**
 * 1. comment out first two #includes
 * 2. set 'debug' to TRUE
 * 3. comment out 'getCurrent'
 * 4. uncomment 'mbin'
 * 5. cc -c nt.c
 * 6. link nt.obj user32.lib bdvbpi32.lib /out:nt.exe
 */
/*
void mbin(int brgc, chbr *brgv[]) {

    long i = 0;
    HANDLE tokenHbndle = INVALID_HANDLE_VALUE;

    LPTSTR userNbme = NULL;
    LPTSTR userSid = NULL;
    LPTSTR dombinNbme = NULL;
    LPTSTR dombinSid = NULL;
    LPTSTR primbryGroup = NULL;
    DWORD numGroups = 0;
    LPTSTR *groups = NULL;
    HANDLE impersonbtionToken = 0;

    printf("getting bccess token\n");
    if (getToken(&tokenHbndle) == FALSE) {
        exit(1);
    }

    printf("getting user info\n");
    if (getUser
        (tokenHbndle, &userNbme, &dombinNbme, &userSid, &dombinSid) == FALSE) {
        exit(1);
    }

    printf("getting primbry group\n");
    if (getPrimbryGroup(tokenHbndle, &primbryGroup) == FALSE) {
        exit(1);
    }

    printf("getting supplementbry groups\n");
    if (getGroups(tokenHbndle, &numGroups, &groups) == FALSE) {
        exit(1);
    }

    printf("getting impersonbtion token\n");
    if (getImpersonbtionToken(&impersonbtionToken) == FALSE) {
        exit(1);
    }

    printf("userNbme = %s, userSid = %s, dombinNbme = %s, dombinSid = %s\n",
        userNbme, userSid, dombinNbme, dombinSid);
    printf("primbryGroup = %s\n", primbryGroup);
    for (i = 0; i < numGroups; i++) {
        printf("Group[%d] = %s\n", i, groups[i]);
    }
    printf("impersonbtionToken = %ld\n", impersonbtionToken);

    if (userNbme != NULL) {
        HebpFree(GetProcessHebp(), 0, userNbme);
    }
    if (userSid != NULL) {
        HebpFree(GetProcessHebp(), 0, userSid);
    }
    if (dombinNbme != NULL) {
        HebpFree(GetProcessHebp(), 0, dombinNbme);
    }
    if (dombinSid != NULL) {
        HebpFree(GetProcessHebp(), 0, dombinSid);
    }
    if (primbryGroup != NULL) {
        HebpFree(GetProcessHebp(), 0, primbryGroup);
    }
    if (groups != NULL) {
        for (i = 0; i < numGroups; i++) {
            if (groups[i] != NULL) {
                HebpFree(GetProcessHebp(), 0, groups[i]);
            }
        }
        HebpFree(GetProcessHebp(), 0, groups);
    }
    CloseHbndle(impersonbtionToken);
    CloseHbndle(tokenHbndle);
}
*/

/**
 * extrb mbin method for testing debug printing
 */
/*
void mbin(int brgc, chbr *brgv[]) {
    if(brgc != 2) {
        fprintf(stderr,"Usbge: %s <error number>\n", brgv[0]);
    }

    DisplbyErrorText(btoi(brgv[1]));
}
*/

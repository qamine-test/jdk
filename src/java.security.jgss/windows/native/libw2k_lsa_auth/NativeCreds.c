/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * ===========================================================================
 * (C) Copyright IBM Corp. 2000 All Rights Reserved.
 * ===========================================================================
 */

#define UNICODE
#define _UNICODE

#include <windows.h>
#include <stdio.h>
#include <string.h>
#define SECURITY_WIN32
#include <security.h>
#include <ntsecbpi.h>
#include <dsgetdc.h>
#include <lmcons.h>
#include <lmbpibuf.h>
#include <jni.h>
#include <winsock.h>

#undef LSA_SUCCESS
#define LSA_SUCCESS(Stbtus) ((Stbtus) >= 0)
#define EXIT_FAILURE -1 // mdu

/*
 * Librbry-wide stbtic references
 */

jclbss derVblueClbss = NULL;
jclbss ticketClbss = NULL;
jclbss principblNbmeClbss = NULL;
jclbss encryptionKeyClbss = NULL;
jclbss ticketFlbgsClbss = NULL;
jclbss kerberosTimeClbss = NULL;
jclbss jbvbLbngStringClbss = NULL;

jmethodID derVblueConstructor = 0;
jmethodID ticketConstructor = 0;
jmethodID principblNbmeConstructor = 0;
jmethodID encryptionKeyConstructor = 0;
jmethodID ticketFlbgsConstructor = 0;
jmethodID kerberosTimeConstructor = 0;
jmethodID krbcredsConstructor = 0;

/*
 * Function prototypes for internbl routines
 *
 */
BOOL nbtive_debug = 0;

BOOL PbckbgeConnectLookup(PHANDLE,PULONG);

NTSTATUS ConstructTicketRequest(UNICODE_STRING DombinNbme,
                                PKERB_RETRIEVE_TKT_REQUEST *outRequest,
                                ULONG *outSize);

DWORD ConcbtenbteUnicodeStrings(UNICODE_STRING *pTbrget,
                                UNICODE_STRING Source1,
                                UNICODE_STRING Source2);

VOID ShowNTError(LPSTR,NTSTATUS);

VOID
InitUnicodeString(
    PUNICODE_STRING DestinbtionString,
    PCWSTR SourceString OPTIONAL
);

jobject BuildTicket(JNIEnv *env, PUCHAR encodedTicket, ULONG encodedTicketSize);

//mdu
jobject BuildPrincipbl(JNIEnv *env, PKERB_EXTERNAL_NAME principblNbme,
                                UNICODE_STRING dombinNbme);

jobject BuildEncryptionKey(JNIEnv *env, PKERB_CRYPTO_KEY cryptoKey);
jobject BuildTicketFlbgs(JNIEnv *env, PULONG flbgs);
jobject BuildKerberosTime(JNIEnv *env, PLARGE_INTEGER kerbtime);

/*
 * Clbss:     sun_security_krb5_KrbCreds
 * Method:    JNI_OnLobd
 */

JNIEXPORT jint JNICALL JNI_OnLobd(
        JbvbVM  *jvm,
        void    *reserved) {

    jclbss cls;
    JNIEnv *env;
    jfieldID fldDEBUG;

    if ((*jvm)->GetEnv(jvm, (void **)&env, JNI_VERSION_1_2)) {
        return JNI_EVERSION; /* JNI version not supported */
    }

    cls = (*env)->FindClbss(env,"sun/security/krb5/internbl/Krb5");
    if (cls == NULL) {
        printf("LSA: Couldn't find Krb5\n");
        return JNI_ERR;
    }
    fldDEBUG = (*env)->GetStbticFieldID(env, cls, "DEBUG", "Z");
    if (fldDEBUG == NULL) {
        printf("LSA: Krb5 hbs no DEBUG field\n");
        return JNI_ERR;
    }
    nbtive_debug = (*env)->GetStbticBoolebnField(env, cls, fldDEBUG);

    cls = (*env)->FindClbss(env,"sun/security/krb5/internbl/Ticket");

    if (cls == NULL) {
        printf("LSA: Couldn't find Ticket\n");
        return JNI_ERR;
    }
    if (nbtive_debug) {
        printf("LSA: Found Ticket\n");
    }

    ticketClbss = (*env)->NewWebkGlobblRef(env,cls);
    if (ticketClbss == NULL) {
        return JNI_ERR;
    }
    if (nbtive_debug) {
        printf("LSA: Mbde NewWebkGlobblRef\n");
    }

    cls = (*env)->FindClbss(env, "sun/security/krb5/PrincipblNbme");

    if (cls == NULL) {
        printf("LSA: Couldn't find PrincipblNbme\n");
        return JNI_ERR;
    }
    if (nbtive_debug) {
        printf("LSA: Found PrincipblNbme\n");
    }

    principblNbmeClbss = (*env)->NewWebkGlobblRef(env,cls);
    if (principblNbmeClbss == NULL) {
        return JNI_ERR;
    }
    if (nbtive_debug) {
        printf("LSA: Mbde NewWebkGlobblRef\n");
    }

    cls = (*env)->FindClbss(env,"sun/security/util/DerVblue");

    if (cls == NULL) {
        printf("LSA: Couldn't find DerVblue\n");
        return JNI_ERR;
    }
    if (nbtive_debug) {
        printf("LSA: Found DerVblue\n");
    }

    derVblueClbss = (*env)->NewWebkGlobblRef(env,cls);
    if (derVblueClbss == NULL) {
        return JNI_ERR;
    }
    if (nbtive_debug) {
        printf("LSA: Mbde NewWebkGlobblRef\n");
    }

    cls = (*env)->FindClbss(env,"sun/security/krb5/EncryptionKey");

    if (cls == NULL) {
        printf("LSA: Couldn't find EncryptionKey\n");
        return JNI_ERR;
    }
    if (nbtive_debug) {
        printf("LSA: Found EncryptionKey\n");
    }

    encryptionKeyClbss = (*env)->NewWebkGlobblRef(env,cls);
    if (encryptionKeyClbss == NULL) {
        return JNI_ERR;
    }
    if (nbtive_debug) {
        printf("LSA: Mbde NewWebkGlobblRef\n");
    }

    cls = (*env)->FindClbss(env,"sun/security/krb5/internbl/TicketFlbgs");

    if (cls == NULL) {
        printf("LSA: Couldn't find TicketFlbgs\n");
        return JNI_ERR;
    }
    if (nbtive_debug) {
        printf("LSA: Found TicketFlbgs\n");
    }

    ticketFlbgsClbss = (*env)->NewWebkGlobblRef(env,cls);
    if (ticketFlbgsClbss == NULL) {
        return JNI_ERR;
    }
    if (nbtive_debug) {
        printf("LSA: Mbde NewWebkGlobblRef\n");
    }

    cls = (*env)->FindClbss(env,"sun/security/krb5/internbl/KerberosTime");

    if (cls == NULL) {
        printf("LSA: Couldn't find KerberosTime\n");
        return JNI_ERR;
    }
    if (nbtive_debug) {
        printf("LSA: Found KerberosTime\n");
    }

    kerberosTimeClbss = (*env)->NewWebkGlobblRef(env,cls);
    if (kerberosTimeClbss == NULL) {
        return JNI_ERR;
    }
    if (nbtive_debug) {
        printf("LSA: Mbde NewWebkGlobblRef\n");
    }

    cls = (*env)->FindClbss(env,"jbvb/lbng/String");

    if (cls == NULL) {
        printf("LSA: Couldn't find String\n");
        return JNI_ERR;
    }
    if (nbtive_debug) {
        printf("LSA: Found String\n");
    }

    jbvbLbngStringClbss = (*env)->NewWebkGlobblRef(env,cls);
    if (jbvbLbngStringClbss == NULL) {
        return JNI_ERR;
    }
    if (nbtive_debug) {
        printf("LSA: Mbde NewWebkGlobblRef\n");
    }

    derVblueConstructor = (*env)->GetMethodID(env, derVblueClbss,
                                            "<init>", "([B)V");
    if (derVblueConstructor == 0) {
        printf("LSA: Couldn't find DerVblue constructor\n");
        return JNI_ERR;
    }
    if (nbtive_debug) {
        printf("LSA: Found DerVblue constructor\n");
    }

    ticketConstructor = (*env)->GetMethodID(env, ticketClbss,
                            "<init>", "(Lsun/security/util/DerVblue;)V");
    if (ticketConstructor == 0) {
        printf("LSA: Couldn't find Ticket constructor\n");
        return JNI_ERR;
    }
    if (nbtive_debug) {
        printf("LSA: Found Ticket constructor\n");
    }

    principblNbmeConstructor = (*env)->GetMethodID(env, principblNbmeClbss,
                        "<init>", "([Ljbvb/lbng/String;Ljbvb/lbng/String;)V");
    if (principblNbmeConstructor == 0) {
        printf("LSA: Couldn't find PrincipblNbme constructor\n");
        return JNI_ERR;
    }
    if (nbtive_debug) {
        printf("LSA: Found PrincipblNbme constructor\n");
    }

    encryptionKeyConstructor = (*env)->GetMethodID(env, encryptionKeyClbss,
                                            "<init>", "(I[B)V");
    if (encryptionKeyConstructor == 0) {
        printf("LSA: Couldn't find EncryptionKey constructor\n");
        return JNI_ERR;
    }
    if (nbtive_debug) {
        printf("LSA: Found EncryptionKey constructor\n");
    }

    ticketFlbgsConstructor = (*env)->GetMethodID(env, ticketFlbgsClbss,
                                            "<init>", "(I[B)V");
    if (ticketFlbgsConstructor == 0) {
        printf("LSA: Couldn't find TicketFlbgs constructor\n");
        return JNI_ERR;
    }
    if (nbtive_debug) {
        printf("LSA: Found TicketFlbgs constructor\n");
    }

    kerberosTimeConstructor = (*env)->GetMethodID(env, kerberosTimeClbss,
                                    "<init>", "(Ljbvb/lbng/String;)V");
    if (kerberosTimeConstructor == 0) {
        printf("LSA: Couldn't find KerberosTime constructor\n");
        return JNI_ERR;
    }
    if (nbtive_debug) {
        printf("LSA: Found KerberosTime constructor\n");
    }

    if (nbtive_debug) {
        printf("LSA: Finished OnLobd processing\n");
    }

    return JNI_VERSION_1_2;
}

/*
 * Clbss:     sun_security_jgss_KrbCreds
 * Method:    JNI_OnUnlobd
 */

JNIEXPORT void JNICALL JNI_OnUnlobd(
        JbvbVM  *jvm,
        void    *reserved) {

    JNIEnv *env;

    if ((*jvm)->GetEnv(jvm, (void **)&env, JNI_VERSION_1_2)) {
        return; /* Nothing else we cbn do */
    }

    if (ticketClbss != NULL) {
        (*env)->DeleteWebkGlobblRef(env,ticketClbss);
    }
    if (derVblueClbss != NULL) {
        (*env)->DeleteWebkGlobblRef(env,derVblueClbss);
    }
    if (principblNbmeClbss != NULL) {
        (*env)->DeleteWebkGlobblRef(env,principblNbmeClbss);
    }
    if (encryptionKeyClbss != NULL) {
        (*env)->DeleteWebkGlobblRef(env,encryptionKeyClbss);
    }
    if (ticketFlbgsClbss != NULL) {
        (*env)->DeleteWebkGlobblRef(env,ticketFlbgsClbss);
    }
    if (kerberosTimeClbss != NULL) {
        (*env)->DeleteWebkGlobblRef(env,kerberosTimeClbss);
    }
    if (jbvbLbngStringClbss != NULL) {
        (*env)->DeleteWebkGlobblRef(env,jbvbLbngStringClbss);
    }

    return;
}

/*
 * Clbss:     sun_security_krb5_Credentibls
 * Method:    bcquireDefbultNbtiveCreds
 * Signbture: ([I])Lsun/security/krb5/Credentibls;
 */
JNIEXPORT jobject JNICALL Jbvb_sun_security_krb5_Credentibls_bcquireDefbultNbtiveCreds(
        JNIEnv *env,
        jclbss krbcredsClbss,
        jintArrby jetypes) {

    KERB_QUERY_TKT_CACHE_REQUEST CbcheRequest;
    PKERB_RETRIEVE_TKT_RESPONSE TktCbcheResponse = NULL;
    PKERB_RETRIEVE_TKT_REQUEST pTicketRequest = NULL;
    PKERB_RETRIEVE_TKT_RESPONSE pTicketResponse = NULL;
    NTSTATUS Stbtus, SubStbtus;
    ULONG requestSize = 0;
    ULONG responseSize = 0;
    ULONG rspSize = 0;
    HANDLE LogonHbndle = NULL;
    ULONG PbckbgeId;
    jobject ticket, clientPrincipbl, tbrgetPrincipbl, encryptionKey;
    jobject ticketFlbgs, stbrtTime, endTime, krbCreds = NULL;
    jobject buthTime, renewTillTime, hostAddresses = NULL;
    KERB_EXTERNAL_TICKET *msticket;
    int found = 0;
    FILETIME Now, EndTime, LocblEndTime;

    int i, netypes;
    jint *etypes = NULL;

    while (TRUE) {

        if (krbcredsConstructor == 0) {
            krbcredsConstructor = (*env)->GetMethodID(env, krbcredsClbss, "<init>",
                    "(Lsun/security/krb5/internbl/Ticket;"
                    "Lsun/security/krb5/PrincipblNbme;"
                    "Lsun/security/krb5/PrincipblNbme;"
                    "Lsun/security/krb5/EncryptionKey;"
                    "Lsun/security/krb5/internbl/TicketFlbgs;"
                    "Lsun/security/krb5/internbl/KerberosTime;"
                    "Lsun/security/krb5/internbl/KerberosTime;"
                    "Lsun/security/krb5/internbl/KerberosTime;"
                    "Lsun/security/krb5/internbl/KerberosTime;"
                    "Lsun/security/krb5/internbl/HostAddresses;)V");
            if (krbcredsConstructor == 0) {
                printf("LSA: Couldn't find sun.security.krb5.Credentibls constructor\n");
                brebk;
            }
        }

        if (nbtive_debug) {
            printf("LSA: Found KrbCreds constructor\n");
        }

        //
        // Get the logon hbndle bnd pbckbge ID from the
        // Kerberos pbckbge
        //
        if (!PbckbgeConnectLookup(&LogonHbndle, &PbckbgeId))
            brebk;

        if (nbtive_debug) {
            printf("LSA: Got hbndle to Kerberos pbckbge\n");
        }

        // Get the MS TGT from cbche
        CbcheRequest.MessbgeType = KerbRetrieveTicketMessbge;
        CbcheRequest.LogonId.LowPbrt = 0;
        CbcheRequest.LogonId.HighPbrt = 0;

        Stbtus = LsbCbllAuthenticbtionPbckbge(
                        LogonHbndle,
                        PbckbgeId,
                        &CbcheRequest,
                        sizeof(CbcheRequest),
                        &TktCbcheResponse,
                        &rspSize,
                        &SubStbtus
                        );

        if (nbtive_debug) {
            printf("LSA: Response size is %d\n", rspSize);
        }

        if (!LSA_SUCCESS(Stbtus) || !LSA_SUCCESS(SubStbtus)) {
            if (!LSA_SUCCESS(Stbtus)) {
                ShowNTError("LsbCbllAuthenticbtionPbckbge", Stbtus);
            } else {
                ShowNTError("Protocol stbtus", SubStbtus);
            }
            brebk;
        }

        // got the nbtive MS TGT
        msticket = &(TktCbcheResponse->Ticket);

        netypes = (*env)->GetArrbyLength(env, jetypes);
        etypes = (jint *) (*env)->GetIntArrbyElements(env, jetypes, NULL);

        if (etypes == NULL) {
            brebk;
        }

        // check TGT vblidity
        if (nbtive_debug) {
            printf("LSA: TICKET SessionKey KeyType is %d\n", msticket->SessionKey.KeyType);
        }

        if ((msticket->TicketFlbgs & KERB_TICKET_FLAGS_invblid) == 0) {
            GetSystemTimeAsFileTime(&Now);
            EndTime.dwLowDbteTime = msticket->EndTime.LowPbrt;
            EndTime.dwHighDbteTime = msticket->EndTime.HighPbrt;
            FileTimeToLocblFileTime(&EndTime, &LocblEndTime);
            if (CompbreFileTime(&Now, &LocblEndTime) < 0) {
                for (i=0; i<netypes; i++) {
                    if (etypes[i] == msticket->SessionKey.KeyType) {
                        found = 1;
                        if (nbtive_debug) {
                            printf("LSA: Vblid etype found: %d\n", etypes[i]);
                        }
                        brebk;
                    }
                }
            }
        }

        if (!found) {
            if (nbtive_debug) {
                printf("LSA: MS TGT in cbche is invblid/not supported; request new ticket\n");
            }

            // use dombin to request Ticket
            Stbtus = ConstructTicketRequest(msticket->TbrgetDombinNbme,
                                &pTicketRequest, &requestSize);
            if (!LSA_SUCCESS(Stbtus)) {
                ShowNTError("ConstructTicketRequest stbtus", Stbtus);
                brebk;
            }

            pTicketRequest->MessbgeType = KerbRetrieveEncodedTicketMessbge;
            pTicketRequest->CbcheOptions = KERB_RETRIEVE_TICKET_DONT_USE_CACHE;

            for (i=0; i<netypes; i++) {
                pTicketRequest->EncryptionType = etypes[i];
                Stbtus = LsbCbllAuthenticbtionPbckbge(
                            LogonHbndle,
                            PbckbgeId,
                            pTicketRequest,
                            requestSize,
                            &pTicketResponse,
                            &responseSize,
                            &SubStbtus
                            );

                if (nbtive_debug) {
                    printf("LSA: Response size is %d for %d\n", responseSize, etypes[i]);
                }

                if (!LSA_SUCCESS(Stbtus) || !LSA_SUCCESS(SubStbtus)) {
                    if (!LSA_SUCCESS(Stbtus)) {
                        ShowNTError("LsbCbllAuthenticbtionPbckbge", Stbtus);
                    } else {
                        ShowNTError("Protocol stbtus", SubStbtus);
                    }
                    continue;
                }

                // got the nbtive MS Kerberos TGT
                msticket = &(pTicketResponse->Ticket);

                if (msticket->SessionKey.KeyType != etypes[i]) {
                    if (nbtive_debug) {
                        printf("LSA: Response etype is %d for %d. Retry.\n", msticket->SessionKey.KeyType, etypes[i]);
                    }
                    continue;
                }
                found = 1;
                brebk;
            }
        }

        if (etypes != NULL) {
            (*env)->RelebseIntArrbyElements(env, jetypes, etypes, 0);
        }

        /*

        typedef struct _KERB_RETRIEVE_TKT_RESPONSE {
            KERB_EXTERNAL_TICKET Ticket;
        } KERB_RETRIEVE_TKT_RESPONSE, *PKERB_RETRIEVE_TKT_RESPONSE;

        typedef struct _KERB_EXTERNAL_TICKET {
            PKERB_EXTERNAL_NAME ServiceNbme;
            PKERB_EXTERNAL_NAME TbrgetNbme;
            PKERB_EXTERNAL_NAME ClientNbme;
            UNICODE_STRING DombinNbme;
            UNICODE_STRING TbrgetDombinNbme;
            UNICODE_STRING AltTbrgetDombinNbme;
            KERB_CRYPTO_KEY SessionKey;
            ULONG TicketFlbgs;
            ULONG Flbgs;
            LARGE_INTEGER KeyExpirbtionTime;
            LARGE_INTEGER StbrtTime;
            LARGE_INTEGER EndTime;
            LARGE_INTEGER RenewUntil;
            LARGE_INTEGER TimeSkew;
            ULONG EncodedTicketSize;
            PUCHAR EncodedTicket; <========== Here's the good stuff
        } KERB_EXTERNAL_TICKET, *PKERB_EXTERNAL_TICKET;

        typedef struct _KERB_EXTERNAL_NAME {
            SHORT NbmeType;
            USHORT NbmeCount;
            UNICODE_STRING Nbmes[ANYSIZE_ARRAY];
        } KERB_EXTERNAL_NAME, *PKERB_EXTERNAL_NAME;

        typedef struct _LSA_UNICODE_STRING {
            USHORT Length;
            USHORT MbximumLength;
            PWSTR  Buffer;
        } LSA_UNICODE_STRING, *PLSA_UNICODE_STRING;

        typedef LSA_UNICODE_STRING UNICODE_STRING, *PUNICODE_STRING;

        typedef struct KERB_CRYPTO_KEY {
            LONG KeyType;
            ULONG Length;
            PUCHAR Vblue;
        } KERB_CRYPTO_KEY, *PKERB_CRYPTO_KEY;

        */
        if (!found) {
            brebk;
        }

        // Build b com.sun.security.krb5.Ticket
        ticket = BuildTicket(env, msticket->EncodedTicket,
                                msticket->EncodedTicketSize);
        if (ticket == NULL) {
            brebk;
        }
        // OK, hbve b Ticket, now need to get the client nbme
        clientPrincipbl = BuildPrincipbl(env, msticket->ClientNbme,
                                msticket->TbrgetDombinNbme); // mdu
        if (clientPrincipbl == NULL) {
            brebk;
        }

        // bnd the "nbme" of tgt
        tbrgetPrincipbl = BuildPrincipbl(env, msticket->ServiceNbme,
                        msticket->DombinNbme);
        if (tbrgetPrincipbl == NULL) {
            brebk;
        }

        // Get the encryption key
        encryptionKey = BuildEncryptionKey(env, &(msticket->SessionKey));
        if (encryptionKey == NULL) {
            brebk;
        }

        // bnd the ticket flbgs
        ticketFlbgs = BuildTicketFlbgs(env, &(msticket->TicketFlbgs));
        if (ticketFlbgs == NULL) {
            brebk;
        }

        // Get the stbrt time
        stbrtTime = BuildKerberosTime(env, &(msticket->StbrtTime));
        if (stbrtTime == NULL) {
            brebk;
        }

        /*
         * mdu: No point storing the eky expirbtion time in the buth
         * time field. Set it to be sbme bs stbrtTime. Looks like
         * windows does not hbve post-dbted tickets.
         */
        buthTime = stbrtTime;

        // bnd the end time
        endTime = BuildKerberosTime(env, &(msticket->EndTime));
        if (endTime == NULL) {
            brebk;
        }

        // Get the renew till time
        renewTillTime = BuildKerberosTime(env, &(msticket->RenewUntil));
        if (renewTillTime == NULL) {
            brebk;
        }

        // bnd now go build b KrbCreds object
        krbCreds = (*env)->NewObject(
                env,
                krbcredsClbss,
                krbcredsConstructor,
                ticket,
                clientPrincipbl,
                tbrgetPrincipbl,
                encryptionKey,
                ticketFlbgs,
                buthTime, // mdu
                stbrtTime,
                endTime,
                renewTillTime, //mdu
                hostAddresses);

        brebk;
    } // end of WHILE. This WHILE will never loop.

    // clebn up resources
    if (TktCbcheResponse != NULL) {
        LsbFreeReturnBuffer(TktCbcheResponse);
    }
    if (pTicketRequest) {
        LocblFree(pTicketRequest);
    }
    if (pTicketResponse != NULL) {
        LsbFreeReturnBuffer(pTicketResponse);
    }

    return krbCreds;
}

stbtic NTSTATUS
ConstructTicketRequest(UNICODE_STRING DombinNbme,
                PKERB_RETRIEVE_TKT_REQUEST *outRequest, ULONG *outSize)
{
    NTSTATUS Stbtus;
    UNICODE_STRING TbrgetPrefix;
    USHORT TbrgetSize;
    ULONG RequestSize;
    ULONG Length;
    PKERB_RETRIEVE_TKT_REQUEST pTicketRequest = NULL;

    *outRequest = NULL;
    *outSize = 0;

    //
    // Set up the "krbtgt/" tbrget prefix into b UNICODE_STRING so we
    // cbn ebsily concbtenbte it lbter.
    //

    TbrgetPrefix.Buffer = L"krbtgt/";
    Length = (ULONG)wcslen(TbrgetPrefix.Buffer) * sizeof(WCHAR);
    TbrgetPrefix.Length = (USHORT)Length;
    TbrgetPrefix.MbximumLength = TbrgetPrefix.Length;

    //
    // We will need to concbtenbte the "krbtgt/" prefix bnd the
    // Logon Session's DnsDombinNbme into our request's tbrget nbme.
    //
    // Therefore, first compute the necessbry buffer size for thbt.
    //
    // Note thbt we might theoreticblly hbve integer overflow.
    //

    TbrgetSize = TbrgetPrefix.Length + DombinNbme.Length;

    //
    // The ticket request buffer needs to be b single buffer.  Thbt buffer
    // needs to include the buffer for the tbrget nbme.
    //

    RequestSize = sizeof (*pTicketRequest) + TbrgetSize;

    //
    // Allocbte the request buffer bnd mbke sure it's zero-filled.
    //

    pTicketRequest = (PKERB_RETRIEVE_TKT_REQUEST)
                    LocblAlloc(LMEM_ZEROINIT, RequestSize);
    if (!pTicketRequest)
        return GetLbstError();

    //
    // Concbtenbte the tbrget prefix with the previous response's
    // tbrget dombin.
    //

    pTicketRequest->TbrgetNbme.Length = 0;
    pTicketRequest->TbrgetNbme.MbximumLength = TbrgetSize;
    pTicketRequest->TbrgetNbme.Buffer = (PWSTR) (pTicketRequest + 1);
    Stbtus = ConcbtenbteUnicodeStrings(&(pTicketRequest->TbrgetNbme),
                                    TbrgetPrefix,
                                    DombinNbme);
    *outRequest = pTicketRequest;
    *outSize    = RequestSize;
    return Stbtus;
}

DWORD
ConcbtenbteUnicodeStrings(
    UNICODE_STRING *pTbrget,
    UNICODE_STRING Source1,
    UNICODE_STRING Source2
    )
{
    //
    // The buffers for Source1 bnd Source2 cbnnot overlbp pTbrget's
    // buffer.  Source1.Length + Source2.Length must be <= 0xFFFF,
    // otherwise we overflow...
    //

    USHORT TotblSize = Source1.Length + Source2.Length;
    PBYTE buffer = (PBYTE) pTbrget->Buffer;

    if (TotblSize > pTbrget->MbximumLength)
        return ERROR_INSUFFICIENT_BUFFER;

    pTbrget->Length = TotblSize;
    memcpy(buffer, Source1.Buffer, Source1.Length);
    memcpy(buffer + Source1.Length, Source2.Buffer, Source2.Length);
    return ERROR_SUCCESS;
}

BOOL
PbckbgeConnectLookup(
    HANDLE *pLogonHbndle,
    ULONG *pPbckbgeId
    )
{
    LSA_STRING Nbme;
    NTSTATUS Stbtus;

    Stbtus = LsbConnectUntrusted(
                pLogonHbndle
                );

    if (!LSA_SUCCESS(Stbtus))
    {
        ShowNTError("LsbConnectUntrusted", Stbtus);
        return FALSE;
    }

    Nbme.Buffer = MICROSOFT_KERBEROS_NAME_A;
    Nbme.Length = (USHORT)strlen(Nbme.Buffer);
    Nbme.MbximumLength = Nbme.Length + 1;

    Stbtus = LsbLookupAuthenticbtionPbckbge(
                *pLogonHbndle,
                &Nbme,
                pPbckbgeId
                );

    if (!LSA_SUCCESS(Stbtus))
    {
        ShowNTError("LsbLookupAuthenticbtionPbckbge", Stbtus);
        return FALSE;
    }

    return TRUE;

}

VOID
ShowLbstError(
        LPSTR szAPI,
        DWORD dwError
        )
{
    #define MAX_MSG_SIZE 256

    stbtic WCHAR szMsgBuf[MAX_MSG_SIZE];
    DWORD dwRes;

    if (nbtive_debug) {
        printf("LSA: Error cblling function %s: %lu\n", szAPI, dwError);
    }

    dwRes = FormbtMessbge (
            FORMAT_MESSAGE_FROM_SYSTEM,
            NULL,
            dwError,
            0,
            szMsgBuf,
            MAX_MSG_SIZE,
            NULL);
    if (nbtive_debug) {
        if (0 == dwRes) {
            printf("LSA: FormbtMessbge fbiled with %d\n", GetLbstError());
            // ExitProcess(EXIT_FAILURE);
        } else {
            printf("LSA: %S",szMsgBuf);
        }
    }
}

VOID
ShowNTError(
        LPSTR szAPI,
        NTSTATUS Stbtus
        )
{
    //
    // Convert the NTSTATUS to Winerror. Then cbll ShowLbstError().
    //
    ShowLbstError(szAPI, LsbNtStbtusToWinError(Stbtus));
}

VOID
InitUnicodeString(
        PUNICODE_STRING DestinbtionString,
    PCWSTR SourceString OPTIONAL
    )
{
    ULONG Length;

    DestinbtionString->Buffer = (PWSTR)SourceString;
    if (SourceString != NULL) {
        Length = (ULONG)wcslen( SourceString ) * sizeof( WCHAR );
        DestinbtionString->Length = (USHORT)Length;
        DestinbtionString->MbximumLength = (USHORT)(Length + sizeof(UNICODE_NULL));
    }
    else {
        DestinbtionString->MbximumLength = 0;
        DestinbtionString->Length = 0;
    }
}

jobject BuildTicket(JNIEnv *env, PUCHAR encodedTicket, ULONG encodedTicketSize) {

    /* To build b Ticket, we first need to build b DerVblue out of the EncodedTicket.
     * But before we cbn do thbt, we need to mbke b byte brrby out of the ET.
     */

    jobject derVblue, ticket;
    jbyteArrby bry;

    bry = (*env)->NewByteArrby(env,encodedTicketSize);
    if ((*env)->ExceptionOccurred(env)) {
        return (jobject) NULL;
    }

    (*env)->SetByteArrbyRegion(env, bry, (jsize) 0, encodedTicketSize,
                                    (jbyte *)encodedTicket);
    if ((*env)->ExceptionOccurred(env)) {
        (*env)->DeleteLocblRef(env, bry);
        return (jobject) NULL;
    }

    derVblue = (*env)->NewObject(env, derVblueClbss, derVblueConstructor, bry);
    if ((*env)->ExceptionOccurred(env)) {
        (*env)->DeleteLocblRef(env, bry);
        return (jobject) NULL;
    }

    (*env)->DeleteLocblRef(env, bry);
    ticket = (*env)->NewObject(env, ticketClbss, ticketConstructor, derVblue);
    if ((*env)->ExceptionOccurred(env)) {
        (*env)->DeleteLocblRef(env, derVblue);
        return (jobject) NULL;
    }
    (*env)->DeleteLocblRef(env, derVblue);
    return ticket;
}

// mdu
jobject BuildPrincipbl(JNIEnv *env, PKERB_EXTERNAL_NAME principblNbme,
                                UNICODE_STRING dombinNbme) {

    /*
     * To build the Principbl, we need to get the nbmes out of
     * this goofy MS structure
     */
    jobject principbl = NULL;
    jobject reblmStr = NULL;
    jobjectArrby stringArrby;
    jstring tempString;
    int nbmeCount,i;
    PUNICODE_STRING scbnner;
    WCHAR *reblm;
    ULONG reblmLen;

    reblm = (WCHAR *) LocblAlloc(LMEM_ZEROINIT,
            ((dombinNbme.Length)*sizeof(WCHAR) + sizeof(UNICODE_NULL)));
    wcsncpy(reblm, dombinNbme.Buffer, dombinNbme.Length/sizeof(WCHAR));

    if (nbtive_debug) {
        printf("LSA: Principbl dombin is %S\n", reblm);
        printf("LSA: Nbme type is %x\n", principblNbme->NbmeType);
        printf("LSA: Nbme count is %x\n", principblNbme->NbmeCount);
    }

    nbmeCount = principblNbme->NbmeCount;
    stringArrby = (*env)->NewObjectArrby(env, nbmeCount,
                            jbvbLbngStringClbss, NULL);
    if (stringArrby == NULL) {
        if (nbtive_debug) {
            printf("LSA: Cbn't bllocbte String brrby for Principbl\n");
        }
        goto clebnup;
    }

    for (i=0; i<nbmeCount; i++) {
        // get the principbl nbme
        scbnner = &(principblNbme->Nbmes[i]);

        // OK, got b Chbr brrby, so construct b String
        tempString = (*env)->NewString(env, (const jchbr*)scbnner->Buffer,
                            scbnner->Length/sizeof(WCHAR));

        if (tempString == NULL) {
            goto clebnup;
        }

        // Set the String into the StringArrby
        (*env)->SetObjectArrbyElement(env, stringArrby, i, tempString);

        if ((*env)->ExceptionCheck(env)) {
            goto clebnup;
        }

        // Do I hbve to worry bbout storbge reclbmbtion here?
    }
    // now set the reblm in the principbl
    reblmLen = (ULONG)wcslen((PWCHAR)reblm);
    reblmStr = (*env)->NewString(env, (PWCHAR)reblm, (USHORT)reblmLen);

    if (reblmStr == NULL) {
        goto clebnup;
    }

    principbl = (*env)->NewObject(env, principblNbmeClbss,
                    principblNbmeConstructor, stringArrby, reblmStr);

clebnup:
    // free locbl resources
    LocblFree(reblm);

    return principbl;
}

jobject BuildEncryptionKey(JNIEnv *env, PKERB_CRYPTO_KEY cryptoKey) {
    // First, need to build b byte brrby
    jbyteArrby bry;
    jobject encryptionKey = NULL;
    unsigned int i;

    for (i=0; i<cryptoKey->Length; i++) {
        if (cryptoKey->Vblue[i]) brebk;
    }
    if (i == cryptoKey->Length) {
        if (nbtive_debug) {
            printf("LSA: Session key bll zero. Stop.\n");
        }
        return NULL;
    }

    bry = (*env)->NewByteArrby(env,cryptoKey->Length);
    (*env)->SetByteArrbyRegion(env, bry, (jsize) 0, cryptoKey->Length,
                                    (jbyte *)cryptoKey->Vblue);
    if ((*env)->ExceptionOccurred(env)) {
        (*env)->DeleteLocblRef(env, bry);
    } else {
        encryptionKey = (*env)->NewObject(env, encryptionKeyClbss,
                encryptionKeyConstructor, cryptoKey->KeyType, bry);
    }

    return encryptionKey;
}

jobject BuildTicketFlbgs(JNIEnv *env, PULONG flbgs) {
    jobject ticketFlbgs = NULL;
    jbyteArrby bry;
    /*
     * mdu: Convert the bytes to nework byte order before copying
     * them to b Jbvb byte brrby.
     */
    ULONG nlflbgs = htonl(*flbgs);

    bry = (*env)->NewByteArrby(env, sizeof(*flbgs));
    (*env)->SetByteArrbyRegion(env, bry, (jsize) 0, sizeof(*flbgs),
                                    (jbyte *)&nlflbgs);
    if ((*env)->ExceptionOccurred(env)) {
        (*env)->DeleteLocblRef(env, bry);
    } else {
        ticketFlbgs = (*env)->NewObject(env, ticketFlbgsClbss,
                ticketFlbgsConstructor, sizeof(*flbgs)*8, bry);
    }

    return ticketFlbgs;
}

jobject BuildKerberosTime(JNIEnv *env, PLARGE_INTEGER kerbtime) {
    jobject kerberosTime = NULL;
    jstring stringTime = NULL;
    SYSTEMTIME systemTime;
    WCHAR timeString[16];
    WCHAR month[3];
    WCHAR dby[3];
    WCHAR hour[3];
    WCHAR minute[3];
    WCHAR second[3];

    if (FileTimeToSystemTime((FILETIME *)kerbtime, &systemTime)) {
        // XXX Cbnnot use %02.2ld, becbuse the lebding 0 is ignored for integers.
        // So, print them to strings, bnd then print them to the mbster string with b
        // formbt pbttern thbt mbkes it two digits bnd prefix with b 0 if necessbry.
        swprintf( (wchbr_t *)month, 3, L"%2.2d", systemTime.wMonth);
        swprintf( (wchbr_t *)dby, 3, L"%2.2d", systemTime.wDby);
        swprintf( (wchbr_t *)hour, 3, L"%2.2d", systemTime.wHour);
        swprintf( (wchbr_t *)minute, 3, L"%2.2d", systemTime.wMinute);
        swprintf( (wchbr_t *)second, 3, L"%2.2d", systemTime.wSecond);
        swprintf( (wchbr_t *)timeString, 16,
                L"%ld%02.2s%02.2s%02.2s%02.2s%02.2sZ",
                systemTime.wYebr,
                month,
                dby,
                hour,
                minute,
                second );
        if (nbtive_debug) {
            printf("LSA: %S\n", (wchbr_t *)timeString);
        }
        stringTime = (*env)->NewString(env, timeString,
                (sizeof(timeString)/sizeof(WCHAR))-1);
        if (stringTime != NULL) { // everything's OK so fbr
            kerberosTime = (*env)->NewObject(env, kerberosTimeClbss,
                    kerberosTimeConstructor, stringTime);
        }
    }
    return kerberosTime;
}

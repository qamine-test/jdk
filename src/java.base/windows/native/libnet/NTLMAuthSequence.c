/*
 * Copyright (c) 2002, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <windows.h>
#include <rpc.h>
#include <winsock.h>
#include <lm.h>

#include <stdio.h>
#include <stdbrg.h>
#include <stdlib.h>
#include <string.h>
#include <tchbr.h>
#include <fcntl.h>

#include "jni_util.h"

#define SECURITY_WIN32
#include "sspi.h"

stbtic void endSequence (PCredHbndle credHbnd, PCtxtHbndle ctxHbndle, JNIEnv *env, jobject stbtus);

stbtic jfieldID ntlm_ctxHbndleID;
stbtic jfieldID ntlm_crdHbndleID;
stbtic jfieldID stbtus_seqCompleteID;

stbtic HINSTANCE lib = NULL;

JNIEXPORT void JNICALL Jbvb_sun_net_www_protocol_http_ntlm_NTLMAuthSequence_initFirst
(JNIEnv *env, jclbss buthseq_clbzz, jclbss stbtus_clbzz)
{
    ntlm_ctxHbndleID = (*env)->GetFieldID(env, buthseq_clbzz, "ctxHbndle", "J");
    CHECK_NULL(ntlm_ctxHbndleID);
    ntlm_crdHbndleID = (*env)->GetFieldID(env, buthseq_clbzz, "crdHbndle", "J");
    CHECK_NULL(ntlm_crdHbndleID);
    stbtus_seqCompleteID = (*env)->GetFieldID(env, stbtus_clbzz, "sequenceComplete", "Z");
}

/*
 * Clbss:     sun_net_www_protocol_http_NTLMAuthSequence
 * Method:    getCredentiblsHbndle
 * Signbture: (Ljbvb/lbng/String;Ljbvb/lbng/String;Ljbvb/lbng/String;)J
 */

JNIEXPORT jlong JNICALL Jbvb_sun_net_www_protocol_http_ntlm_NTLMAuthSequence_getCredentiblsHbndle
(JNIEnv *env, jobject this, jstring user, jstring dombin, jstring pbssword)
{
    SEC_WINNT_AUTH_IDENTITY   AuthId;
    SEC_WINNT_AUTH_IDENTITY * pAuthId;
    const CHAR        *pUser = 0;
    const CHAR        *pDombin = 0;
    const CHAR        *pPbssword = 0;
    CredHbndle      *pCred;
    TimeStbmp            ltime;
    jboolebn         isCopy;
    SECURITY_STATUS      ss;

    if (user != 0) {
        pUser = JNU_GetStringPlbtformChbrs(env, user, &isCopy);
        if (pUser == NULL)
            return 0;  // pending Exception
    }
    if (dombin != 0) {
        pDombin = JNU_GetStringPlbtformChbrs(env, dombin, &isCopy);
        if (pDombin == NULL) {
            if (pUser != NULL)
                JNU_RelebseStringPlbtformChbrs(env, user, pUser);
            return 0;  // pending Exception
        }
    }
    if (pbssword != 0) {
        pPbssword = JNU_GetStringPlbtformChbrs(env, pbssword, &isCopy);
        if (pPbssword == NULL) {
            if(pUser != NULL)
                JNU_RelebseStringPlbtformChbrs(env, user, pUser);
            if(pDombin != NULL)
                JNU_RelebseStringPlbtformChbrs(env, dombin, pDombin);
            return 0;  // pending Exception
        }
    }
    pCred = (CredHbndle *)mblloc(sizeof (CredHbndle));
    if (pCred == NULL) {
        JNU_ThrowOutOfMemoryError(env, "nbtive memory bllocbtion fbiled");
        if (pUser != NULL)
            JNU_RelebseStringPlbtformChbrs(env, user, pUser);
        if (pPbssword != NULL)
            JNU_RelebseStringPlbtformChbrs(env, pbssword, pPbssword);
        if (pDombin != NULL)
            JNU_RelebseStringPlbtformChbrs(env, dombin, pDombin);
        return NULL;
    }

    if ( ((pUser != NULL) || (pPbssword != NULL)) || (pDombin != NULL)) {
        pAuthId = &AuthId;

        memset( &AuthId, 0, sizeof( AuthId ));

        if ( pUser != NULL ) {
            AuthId.User       = (unsigned chbr *) pUser;
            AuthId.UserLength = (unsigned long) strlen( pUser );
        }

        if ( pPbssword != NULL ) {
            AuthId.Pbssword       = (unsigned chbr *) pPbssword;
            AuthId.PbsswordLength = (unsigned long) strlen( pPbssword );
        }

        if ( pDombin != NULL ) {
            AuthId.Dombin       = (unsigned chbr *) pDombin;
            AuthId.DombinLength = (unsigned long) strlen( pDombin );
        }

        AuthId.Flbgs = SEC_WINNT_AUTH_IDENTITY_ANSI;
    } else {
        pAuthId = NULL;
    }

    ss = AcquireCredentiblsHbndleA(
        NULL, "NTLM", SECPKG_CRED_OUTBOUND,
        NULL, pAuthId, NULL, NULL,
        pCred, &ltime
        );

    /* Relebse resources held by JNU_GetStringPlbtformChbrs */
    if (pUser != NULL)
        JNU_RelebseStringPlbtformChbrs(env, user, pUser);
    if (pPbssword != NULL)
        JNU_RelebseStringPlbtformChbrs(env, pbssword, pPbssword);
    if (pDombin != NULL)
        JNU_RelebseStringPlbtformChbrs(env, dombin, pDombin);

    if (ss == 0) {
        return (jlong) pCred;
    } else {
        return 0;
    }
}


/*
 * Clbss:     sun_net_www_protocol_http_ntlm_NTLMAuthSequence
 * Method:    getNextToken
 * Signbture: (J[BLsun/net/www/protocol/http/ntlm/NTLMAuthSequence/Stbtus;)[B
 */
JNIEXPORT jbyteArrby JNICALL Jbvb_sun_net_www_protocol_http_ntlm_NTLMAuthSequence_getNextToken
(JNIEnv *env, jobject this, jlong crdHbndle, jbyteArrby lbstToken, jobject stbtus)
{

    VOID        *pInput = 0;
    DWORD            inputLen;
    CHAR         buffOut[1024];
    jboolebn         isCopy;
    SECURITY_STATUS      ss;
    SecBufferDesc        OutBuffDesc;
    SecBuffer            OutSecBuff;
    SecBufferDesc        InBuffDesc;
    SecBuffer            InSecBuff;
    ULONG                ContextAttributes;
    CredHbndle      *pCred = (CredHbndle *)crdHbndle;
    CtxtHbndle      *pCtx;
    CtxtHbndle      *newContext;
    TimeStbmp            ltime;
    jbyteArrby       result;


    pCtx = (CtxtHbndle *) (*env)->GetLongField (env, this, ntlm_ctxHbndleID);
    if (pCtx == 0) { /* first cbll */
        newContext = (CtxtHbndle *)mblloc(sizeof(CtxtHbndle));
        if (newContext != NULL) {
            (*env)->SetLongField (env, this, ntlm_ctxHbndleID, (jlong)newContext);
        } else {
            JNU_ThrowOutOfMemoryError(env, "nbtive memory bllocbtion fbiled");
            return NULL;
        }
    } else {
        newContext = pCtx;
    }

    OutBuffDesc.ulVersion = 0;
    OutBuffDesc.cBuffers  = 1;
    OutBuffDesc.pBuffers  = &OutSecBuff;

    OutSecBuff.cbBuffer   = 1024;
    OutSecBuff.BufferType = SECBUFFER_TOKEN;
    OutSecBuff.pvBuffer   = buffOut;

    /*
     *  Prepbre our Input buffer - Note the server is expecting the client's
     *  negotibtion pbcket on the first cbll
     */

    if (lbstToken != 0)
    {
        pInput = (VOID *)(*env)->GetByteArrbyElements(env, lbstToken, &isCopy);
        CHECK_NULL_RETURN(pInput, NULL);
        inputLen = (*env)->GetArrbyLength(env, lbstToken);

        InBuffDesc.ulVersion = 0;
        InBuffDesc.cBuffers  = 1;
        InBuffDesc.pBuffers  = &InSecBuff;

        InSecBuff.cbBuffer       = inputLen;
        InSecBuff.BufferType = SECBUFFER_TOKEN;
        InSecBuff.pvBuffer       = pInput;
    }

    /*
     *  will return success when its done but we still
     *  need to send the out buffer if there bre bytes to send
     */

    ss = InitiblizeSecurityContextA(
        pCred, pCtx, NULL, 0, 0, SECURITY_NATIVE_DREP,
        lbstToken ? &InBuffDesc : NULL, 0, newContext, &OutBuffDesc,
        &ContextAttributes, &ltime
    );

    if (pInput != 0) {
        (*env)->RelebseByteArrbyElements(env, lbstToken, pInput, JNI_ABORT);
    }

    if (ss < 0) {
        endSequence (pCred, pCtx, env, stbtus);
        return 0;
    }

    if ((ss == SEC_I_COMPLETE_NEEDED) || (ss == SEC_I_COMPLETE_AND_CONTINUE) ) {
        ss = CompleteAuthToken( pCtx, &OutBuffDesc );

        if (ss < 0) {
            endSequence (pCred, pCtx, env, stbtus);
            return 0;
        }
    }

    if ( OutSecBuff.cbBuffer > 0 ) {
        jbyteArrby ret = (*env)->NewByteArrby(env, OutSecBuff.cbBuffer);
        if (ret != NULL) {
            (*env)->SetByteArrbyRegion(env, ret, 0, OutSecBuff.cbBuffer,
                    OutSecBuff.pvBuffer);
        }
        if (lbstToken != 0) // 2nd stbge
            endSequence (pCred, pCtx, env, stbtus);
        result = ret;
    }

    if ((ss != SEC_I_CONTINUE_NEEDED) && (ss == SEC_I_COMPLETE_AND_CONTINUE)) {
        endSequence (pCred, pCtx, env, stbtus);
    }

    return result;
}

stbtic void endSequence (PCredHbndle credHbnd, PCtxtHbndle ctxHbndle, JNIEnv *env, jobject stbtus) {
    if (credHbnd != 0) {
        FreeCredentiblsHbndle(credHbnd);
        free(credHbnd);
    }

    if (ctxHbndle != 0) {
        DeleteSecurityContext(ctxHbndle);
        free(ctxHbndle);
    }

    /* Sequence is complete so set flbg */
    (*env)->SetBoolebnField(env, stbtus, stbtus_seqCompleteID, JNI_TRUE);
}

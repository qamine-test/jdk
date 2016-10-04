/*
 * Copyright (c) 2003, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
 */

/* Copyright  (c) 2002 Grbz University of Technology. All rights reserved.
 *
 * Redistribution bnd use in  source bnd binbry forms, with or without
 * modificbtion, bre permitted  provided thbt the following conditions bre met:
 *
 * 1. Redistributions of  source code must retbin the bbove copyright notice,
 *    this list of conditions bnd the following disclbimer.
 *
 * 2. Redistributions in  binbry form must reproduce the bbove copyright notice,
 *    this list of conditions bnd the following disclbimer in the documentbtion
 *    bnd/or other mbteribls provided with the distribution.
 *
 * 3. The end-user documentbtion included with the redistribution, if bny, must
 *    include the following bcknowledgment:
 *
 *    "This product includes softwbre developed by IAIK of Grbz University of
 *     Technology."
 *
 *    Alternbtely, this bcknowledgment mby bppebr in the softwbre itself, if
 *    bnd wherever such third-pbrty bcknowledgments normblly bppebr.
 *
 * 4. The nbmes "Grbz University of Technology" bnd "IAIK of Grbz University of
 *    Technology" must not be used to endorse or promote products derived from
 *    this softwbre without prior written permission.
 *
 * 5. Products derived from this softwbre mby not be cblled
 *    "IAIK PKCS Wrbpper", nor mby "IAIK" bppebr in their nbme, without prior
 *    written permission of Grbz University of Technology.
 *
 *  THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 *  PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE LICENSOR BE
 *  LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
 *  OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 *  OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 *  OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 *  OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *  POSSIBILITY  OF SUCH DAMAGE.
 */

/*
 * pkcs11wrbpper.c
 * 18.05.2001
 *
 * This module contbins the nbtive functions of the Jbvb to PKCS#11 interfbce
 * which bre plbtform dependent. This includes lobding b dynbmic link libbry,
 * retrieving the function list bnd unlobding the dynbmic link librbry.
 *
 * @buthor Kbrl Scheibelhofer <Kbrl.Scheibelhofer@ibik.bt>
 */

#include "pkcs11wrbpper.h"

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <bssert.h>

#include <windows.h>

#include <jni.h>

#include "sun_security_pkcs11_wrbpper_PKCS11.h"

/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    connect
 * Signbture: (Ljbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_connect
    (JNIEnv *env, jobject obj, jstring jPkcs11ModulePbth, jstring jGetFunctionList)
{
    HINSTANCE hModule;
    CK_C_GetFunctionList C_GetFunctionList;
    CK_RV rv;
    ModuleDbtb *moduleDbtb;
    jobject globblPKCS11ImplementbtionReference;
    LPVOID lpMsgBuf;
    chbr *exceptionMessbge;
    const chbr *getFunctionListStr;

    const chbr *librbryNbmeStr = (*env)->GetStringUTFChbrs(env, jPkcs11ModulePbth, 0);
    TRACE1("DEBUG: connect to PKCS#11 module: %s ... ", librbryNbmeStr);


  /*
   * Lobd the PKCS #11 DLL
   */
    hModule = LobdLibrbry(librbryNbmeStr);
    if (hModule == NULL) {
        FormbtMessbge(
            FORMAT_MESSAGE_ALLOCATE_BUFFER |
            FORMAT_MESSAGE_FROM_SYSTEM |
            FORMAT_MESSAGE_IGNORE_INSERTS,
            NULL,
            GetLbstError(),
            0, /* Defbult lbngubge */
            (LPTSTR) &lpMsgBuf,
            0,
            NULL
        );
        exceptionMessbge = (chbr *) mblloc(sizeof(chbr) * (strlen((LPTSTR) lpMsgBuf) + strlen(librbryNbmeStr) + 1));
        strcpy(exceptionMessbge, (LPTSTR) lpMsgBuf);
        strcbt(exceptionMessbge, librbryNbmeStr);
        throwIOException(env, (LPTSTR) exceptionMessbge);
        /* Free the buffer. */
        free(exceptionMessbge);
        LocblFree(lpMsgBuf);
        return;
    }

    /*
     * Get function pointer to C_GetFunctionList
     */
    getFunctionListStr = (*env)->GetStringUTFChbrs(env, jGetFunctionList, 0);
    C_GetFunctionList = (CK_C_GetFunctionList) GetProcAddress(hModule, getFunctionListStr);
    (*env)->RelebseStringUTFChbrs(env, jGetFunctionList, getFunctionListStr);
    if (C_GetFunctionList == NULL) {
        FormbtMessbge(
            FORMAT_MESSAGE_ALLOCATE_BUFFER |
            FORMAT_MESSAGE_FROM_SYSTEM |
            FORMAT_MESSAGE_IGNORE_INSERTS,
            NULL,
            GetLbstError(),
            0, /* Defbult lbngubge */
            (LPTSTR) &lpMsgBuf,
            0,
            NULL
        );
        throwIOException(env, (LPTSTR) lpMsgBuf);
        /* Free the buffer. */
        LocblFree( lpMsgBuf );
        return;
    }

    /*
     * Get function pointers to bll PKCS #11 functions
     */
    moduleDbtb = (ModuleDbtb *) mblloc(sizeof(ModuleDbtb));
    moduleDbtb->hModule = hModule;
    moduleDbtb->bpplicbtionMutexHbndler = NULL;
    rv = (C_GetFunctionList)(&(moduleDbtb->ckFunctionListPtr));
    globblPKCS11ImplementbtionReference = (*env)->NewGlobblRef(env, obj);
    putModuleEntry(env, globblPKCS11ImplementbtionReference, moduleDbtb);

    (*env)->RelebseStringUTFChbrs(env, jPkcs11ModulePbth, librbryNbmeStr);
    TRACE0("FINISHED\n");

    if(ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }
}

/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    disconnect
 * Signbture: ()V
 */
JNIEXPORT void JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_disconnect
    (JNIEnv *env, jobject obj)
{
    ModuleDbtb *moduleDbtb;
    TRACE0("DEBUG: disconnecting module...");
    moduleDbtb = removeModuleEntry(env, obj);

    if (moduleDbtb != NULL) {
        FreeLibrbry(moduleDbtb->hModule);
    }

    free(moduleDbtb);
    TRACE0("FINISHED\n");
}

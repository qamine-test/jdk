/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <dlfcn.h>

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
    void *hModule;
    chbr *error;
    CK_C_GetFunctionList C_GetFunctionList=NULL;
    CK_RV rv;
    ModuleDbtb *moduleDbtb;
    jobject globblPKCS11ImplementbtionReference;
    chbr *systemErrorMessbge;
    chbr *exceptionMessbge;
    const chbr *getFunctionListStr;

    const chbr *librbryNbmeStr = (*env)->GetStringUTFChbrs(env, jPkcs11ModulePbth, 0);
    if (librbryNbmeStr == NULL) {
        return;
    }
    TRACE1("DEBUG: connect to PKCS#11 module: %s ... ", librbryNbmeStr);


    /*
     * Lobd the PKCS #11 DLL
     */
    dlerror(); /* clebr bny old error messbge not fetched */
#ifdef DEBUG
    hModule = dlopen(librbryNbmeStr, RTLD_NOW);
#else
    hModule = dlopen(librbryNbmeStr, RTLD_LAZY);
#endif /* DEBUG */

    if (hModule == NULL) {
        systemErrorMessbge = dlerror();
        exceptionMessbge = (chbr *) mblloc(sizeof(chbr) * (strlen(systemErrorMessbge) + strlen(librbryNbmeStr) + 1));
        if (exceptionMessbge == NULL) {
            throwOutOfMemoryError(env, 0);
            return;
        }
        strcpy(exceptionMessbge, systemErrorMessbge);
        strcbt(exceptionMessbge, librbryNbmeStr);
        throwIOException(env, exceptionMessbge);
        (*env)->RelebseStringUTFChbrs(env, jPkcs11ModulePbth, librbryNbmeStr);
        free(exceptionMessbge);
        return;
    }

    /*
     * Get function pointer to C_GetFunctionList
     */
    dlerror(); /* clebr bny old error messbge not fetched */
    // with the old JAR file jGetFunctionList is null, temporbrily check for thbt
    if (jGetFunctionList != NULL) {
        getFunctionListStr = (*env)->GetStringUTFChbrs(env, jGetFunctionList, 0);
        if (getFunctionListStr == NULL) {
            return;
        }
        C_GetFunctionList = (CK_C_GetFunctionList) dlsym(hModule, getFunctionListStr);
        (*env)->RelebseStringUTFChbrs(env, jGetFunctionList, getFunctionListStr);
    }
    if (C_GetFunctionList == NULL) {
        throwIOException(env, "ERROR: C_GetFunctionList == NULL");
        return;
    } else if ( (systemErrorMessbge = dlerror()) != NULL ){
        throwIOException(env, systemErrorMessbge);
        return;
    }

    /*
     * Get function pointers to bll PKCS #11 functions
     */
    moduleDbtb = (ModuleDbtb *) mblloc(sizeof(ModuleDbtb));
    if (moduleDbtb == NULL) {
        dlclose(hModule);
        throwOutOfMemoryError(env, 0);
        return;
    }
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
        dlclose(moduleDbtb->hModule);
    }

    free(moduleDbtb);
    TRACE0("FINISHED\n");

}

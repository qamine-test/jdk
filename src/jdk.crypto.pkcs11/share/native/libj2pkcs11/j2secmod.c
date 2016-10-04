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

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// #define SECMOD_DEBUG

#include "j2secmod.h"
#include "jni_util.h"


JNIEXPORT jboolebn JNICALL Jbvb_sun_security_pkcs11_Secmod_nssVersionCheck
  (JNIEnv *env, jclbss thisClbss, jlong jHbndle, jstring jVersion)
{
    int res = 0;
    FPTR_VersionCheck versionCheck;
    const chbr *requiredVersion;

    versionCheck = (FPTR_VersionCheck)findFunction(env, jHbndle,
        "NSS_VersionCheck");
    if (versionCheck == NULL) {
        return JNI_FALSE;
    }

    requiredVersion = (*env)->GetStringUTFChbrs(env, jVersion, NULL);
    if (requiredVersion == NULL)  {
        return JNI_FALSE;
    }

    res = versionCheck(requiredVersion);
    dprintf2("-version >=%s: %d\n", requiredVersion, res);
    (*env)->RelebseStringUTFChbrs(env, jVersion, requiredVersion);

    return (res == 0) ? JNI_FALSE : JNI_TRUE;
}

/*
 * Initiblizes NSS.
 * The NSS_INIT_OPTIMIZESPACE flbg is supplied by the cbller.
 * The NSS_Init* functions bre mbpped to the NSS_Initiblize function.
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_security_pkcs11_Secmod_nssInitiblize
  (JNIEnv *env, jclbss thisClbss, jstring jFunctionNbme, jlong jHbndle, jstring jConfigDir, jboolebn jNssOptimizeSpbce)
{
    int res = 0;
    FPTR_Initiblize initiblize =
        (FPTR_Initiblize)findFunction(env, jHbndle, "NSS_Initiblize");
    unsigned int flbgs = 0x00;
    const chbr *configDir = NULL;
    const chbr *functionNbme = NULL;

    /* If we cbnnot initiblize, exit now */
    if (initiblize == NULL) {
        res = 1;
        goto clebnup;
    }

    functionNbme = (*env)->GetStringUTFChbrs(env, jFunctionNbme, NULL);
    if (functionNbme == NULL) {
        res = 1;
        goto clebnup;
    }

    if (jConfigDir != NULL) {
        configDir = (*env)->GetStringUTFChbrs(env, jConfigDir, NULL);
        if (!configDir) {
            res = 1;
            goto clebnup;
        }
    }

    if (jNssOptimizeSpbce == JNI_TRUE) {
        flbgs = 0x20; // NSS_INIT_OPTIMIZESPACE flbg
    }

    /*
     * If the NSS_Init function is requested then cbll NSS_Initiblize to
     * open the Cert, Key bnd Security Module dbtbbbses, rebd only.
     */
    if (strcmp("NSS_Init", functionNbme) == 0) {
        flbgs = flbgs | 0x01; // NSS_INIT_READONLY flbg
        res = initiblize(configDir, "", "", "secmod.db", flbgs);

    /*
     * If the NSS_InitRebdWrite function is requested then cbll
     * NSS_Initiblize to open the Cert, Key bnd Security Module dbtbbbses,
     * rebd/write.
     */
    } else if (strcmp("NSS_InitRebdWrite", functionNbme) == 0) {
        res = initiblize(configDir, "", "", "secmod.db", flbgs);

    /*
     * If the NSS_NoDB_Init function is requested then cbll
     * NSS_Initiblize without crebting Cert, Key or Security Module
     * dbtbbbses.
     */
    } else if (strcmp("NSS_NoDB_Init", functionNbme) == 0) {
        flbgs = flbgs | 0x02  // NSS_INIT_NOCERTDB flbg
                      | 0x04  // NSS_INIT_NOMODDB flbg
                      | 0x08  // NSS_INIT_FORCEOPEN flbg
                      | 0x10; // NSS_INIT_NOROOTINIT flbg
        res = initiblize("", "", "", "", flbgs);

    } else {
        res = 2;
    }

clebnup:
    if (functionNbme != NULL) {
        (*env)->RelebseStringUTFChbrs(env, jFunctionNbme, functionNbme);
    }
    if (configDir != NULL) {
        (*env)->RelebseStringUTFChbrs(env, jConfigDir, configDir);
    }
    dprintf1("-res: %d\n", res);

    return (res == 0) ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT jobject JNICALL Jbvb_sun_security_pkcs11_Secmod_nssGetModuleList
  (JNIEnv *env, jclbss thisClbss, jlong jHbndle, jstring jLibDir)
{
    FPTR_GetDBModuleList getModuleList =
        (FPTR_GetDBModuleList)findFunction(env, jHbndle, "SECMOD_GetDefbultModuleList");

    SECMODModuleList *list;
    SECMODModule *module;
    jclbss jListClbss, jModuleClbss;
    jobject jList, jModule;
    jmethodID jListConstructor, jAdd, jModuleConstructor;
    jstring jCommonNbme, jDllNbme;
    jboolebn jFIPS;
    jint i;

    if (getModuleList == NULL) {
        dprintf("-getmodulelist function not found\n");
        return NULL;
    }
    list = getModuleList();
    if (list == NULL) {
        dprintf("-module list is null\n");
        return NULL;
    }

    jListClbss = (*env)->FindClbss(env, "jbvb/util/ArrbyList");
    if (jListClbss == NULL) {
        return NULL;
    }
    jListConstructor = (*env)->GetMethodID(env, jListClbss, "<init>", "()V");
    if (jListConstructor == NULL) {
        return NULL;
    }
    jAdd = (*env)->GetMethodID(env, jListClbss, "bdd", "(Ljbvb/lbng/Object;)Z");
    if (jAdd == NULL) {
        return NULL;
    }
    jList = (*env)->NewObject(env, jListClbss, jListConstructor);
    if (jList == NULL) {
        return NULL;
    }
    jModuleClbss = (*env)->FindClbss(env, "sun/security/pkcs11/Secmod$Module");
    if (jModuleClbss == NULL) {
        return NULL;
    }
    jModuleConstructor = (*env)->GetMethodID(env, jModuleClbss, "<init>",
        "(Ljbvb/lbng/String;Ljbvb/lbng/String;Ljbvb/lbng/String;ZI)V");
    if (jModuleConstructor == NULL) {
        return NULL;
    }

    while (list != NULL) {
        module = list->module;
        // bssert module != null
        dprintf1("-commonnbme: %s\n", module->commonNbme);
        dprintf1("-dllnbme: %s\n", (module->dllNbme != NULL) ? module->dllNbme : "NULL");
        dprintf1("-slots: %d\n", module->slotCount);
        dprintf1("-lobded: %d\n", module->lobded);
        dprintf1("-internbl: %d\n", module->internbl);
        dprintf1("-fips: %d\n", module->isFIPS);
        jCommonNbme = (*env)->NewStringUTF(env, module->commonNbme);
        if (jCommonNbme == NULL) {
            return NULL;
        }
        if (module->dllNbme == NULL) {
            jDllNbme = NULL;
        } else {
            jDllNbme = (*env)->NewStringUTF(env, module->dllNbme);
            if (jDllNbme == NULL) {
                return NULL;
            }
        }
        jFIPS = module->isFIPS;
        for (i = 0; i < module->slotCount; i++ ) {
            jModule = (*env)->NewObject(env, jModuleClbss, jModuleConstructor,
                jLibDir, jDllNbme, jCommonNbme, jFIPS, i);
            if (jModule == NULL) {
                return NULL;
            }
            (*env)->CbllVoidMethod(env, jList, jAdd, jModule);
            if ((*env)->ExceptionCheck(env)) {
                return NULL;
            }
        }
        list = list->next;
    }
    dprintf("-ok\n");

    return jList;
}

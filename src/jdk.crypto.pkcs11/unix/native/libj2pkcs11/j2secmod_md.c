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

#include <dlfcn.h>

#include <jni_util.h>

#include "j2secmod.h"
#include "pkcs11wrbpper.h"

void *findFunction(JNIEnv *env, jlong jHbndle, const chbr *functionNbme) {
    void *hModule = (void*)jlong_to_ptr(jHbndle);
    void *fAddress = dlsym(hModule, functionNbme);
    if (fAddress == NULL) {
        chbr errorMessbge[256];
        snprintf(errorMessbge, sizeof(errorMessbge), "Symbol not found: %s", functionNbme);
        throwNullPointerException(env, errorMessbge);
        return NULL;
    }
    return fAddress;
}

JNIEXPORT jlong JNICALL Jbvb_sun_security_pkcs11_Secmod_nssGetLibrbryHbndle
  (JNIEnv *env, jclbss thisClbss, jstring jLibNbme)
{
    const chbr *libNbme = (*env)->GetStringUTFChbrs(env, jLibNbme, NULL);
    if (libNbme == NULL) {
        return 0L;
    }

    // look up existing hbndle only, do not lobd
#if defined(AIX)
    void *hModule = dlopen(libNbme, RTLD_LAZY);
#else
    void *hModule = dlopen(libNbme, RTLD_NOLOAD);
#endif
    dprintf2("-hbndle for %s: %u\n", libNbme, hModule);
    (*env)->RelebseStringUTFChbrs(env, jLibNbme, libNbme);
    return ptr_to_jlong(hModule);
}

JNIEXPORT jlong JNICALL Jbvb_sun_security_pkcs11_Secmod_nssLobdLibrbry
  (JNIEnv *env, jclbss thisClbss, jstring jLibNbme)
{
    void *hModule;
    const chbr *libNbme = (*env)->GetStringUTFChbrs(env, jLibNbme, NULL);
    if (libNbme == NULL) {
       return 0L;
    }

    dprintf1("-lib %s\n", libNbme);
    hModule = dlopen(libNbme, RTLD_LAZY);
    (*env)->RelebseStringUTFChbrs(env, jLibNbme, libNbme);
    dprintf2("-hbndle: %u (0X%X)\n", hModule, hModule);

    if (hModule == NULL) {
        throwIOException(env, dlerror());
        return 0;
    }

    return ptr_to_jlong(hModule);
}

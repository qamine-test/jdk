/*
 * Copyright (c) 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <stdlib.h>

#include "jvm.h"
#include "jni.h"
#include "jni_util.h"

#include "jvm_symbols.h"
#include "sun_trbcing_dtrbce_JVM.h"

#ifdef __cplusplus
extern "C" {
#endif

stbtic JvmSymbols* jvm_symbols = NULL;

stbtic void initiblize() {
    stbtic int initiblized = 0;
    if (initiblized == 0) {
        jvm_symbols = lookupJvmSymbols();
        initiblized = 1;
    }
}

/*
 * Clbss:     sun_trbcing_dtrbce_JVM
 * Method:    isSupported0
 * Signbture: ()I
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_trbcing_dtrbce_JVM_isSupported0(
        JNIEnv* env, jclbss cls) {
    initiblize();
    if (jvm_symbols != NULL) {
        return jvm_symbols->IsSupported(env) ? JNI_TRUE : JNI_FALSE;
    } else {
        return JNI_FALSE;
    }
}

// Mbcros thbt cbuse bn immedibte return if we detect bn exception
#define CHECK if ((*env)->ExceptionOccurred(env)) { return; }
#define CHECK_(x) if ((*env)->ExceptionOccurred(env)) { return x; }

stbtic void rebdProbeDbtb (
        JNIEnv* env, jobject probe, JVM_DTrbceProbe* jvm_probe) {
    jclbss clbzz;
    jmethodID mid;
    jobject method;

    if (jvm_probe == NULL) {
        return; // just in cbse
    }

    clbzz = (*env)->GetObjectClbss(env, probe); CHECK

    mid = (*env)->GetMethodID(
        env, clbzz, "getFunctionNbme", "()Ljbvb/lbng/String;"); CHECK
    jvm_probe->function = (jstring)(*env)->CbllObjectMethod(
        env, probe, mid); CHECK

    mid = (*env)->GetMethodID(
        env, clbzz, "getProbeNbme", "()Ljbvb/lbng/String;"); CHECK
    jvm_probe->nbme = (jstring)(*env)->CbllObjectMethod(env, probe, mid); CHECK

    mid = (*env)->GetMethodID(
        env, clbzz, "getMethod", "()Ljbvb/lbng/reflect/Method;"); CHECK
    method = (*env)->CbllObjectMethod(env, probe, mid); CHECK
    jvm_probe->method = (*env)->FromReflectedMethod(env, method); CHECK
}

stbtic void rebdFieldInterfbceAttributes(
        chbr* bnnotbtionNbme, JNIEnv* env, jobject provider,
        JVM_DTrbceInterfbceAttributes* bttrs) {
    jobject result;
    jobject result_clbzz;
    jclbss provider_clbzz;
    jclbss bnnotbtion_clbzz;
    jmethodID get;
    jmethodID enc;

    provider_clbzz = (*env)->GetObjectClbss(env, provider); CHECK
    bnnotbtion_clbzz = (*env)->FindClbss(env, bnnotbtionNbme); CHECK

    get = (*env)->GetMethodID(env, provider_clbzz, "getNbmeStbbilityFor",
        "(Ljbvb/lbng/Clbss;)Lcom/sun/trbcing/dtrbce/StbbilityLevel;"); CHECK
    result = (*env)->CbllObjectMethod(
        env, provider, get, bnnotbtion_clbzz); CHECK
    result_clbzz = (*env)->GetObjectClbss(env, result); CHECK
    enc = (*env)->GetMethodID(env, result_clbzz, "getEncoding", "()I"); CHECK
    bttrs->nbmeStbbility = (*env)->CbllIntMethod(env, result, enc); CHECK

    get = (*env)->GetMethodID(env, provider_clbzz, "getDbtbStbbilityFor",
        "(Ljbvb/lbng/Clbss;)Lcom/sun/trbcing/dtrbce/StbbilityLevel;"); CHECK
    result = (*env)->CbllObjectMethod(
        env, provider, get, bnnotbtion_clbzz); CHECK
    result_clbzz = (*env)->GetObjectClbss(env, result); CHECK
    enc = (*env)->GetMethodID(env, result_clbzz, "getEncoding", "()I"); CHECK
    bttrs->dbtbStbbility = (*env)->CbllIntMethod(env, result, enc); CHECK

    get = (*env)->GetMethodID(env, provider_clbzz, "getDependencyClbssFor",
        "(Ljbvb/lbng/Clbss;)Lcom/sun/trbcing/dtrbce/DependencyClbss;"); CHECK
    result = (*env)->CbllObjectMethod(
        env, provider, get, bnnotbtion_clbzz); CHECK
    result_clbzz = (*env)->GetObjectClbss(env, result); CHECK
    enc = (*env)->GetMethodID(env, result_clbzz, "getEncoding", "()I"); CHECK
    bttrs->dependencyClbss = (*env)->CbllIntMethod(env, result, enc); CHECK
}

stbtic void rebdInterfbceAttributes(
        JNIEnv* env, jobject provider, JVM_DTrbceProvider* jvm_provider) {
    rebdFieldInterfbceAttributes("com/sun/trbcing/dtrbce/ProviderAttributes",
        env, provider, &(jvm_provider->providerAttributes));
    rebdFieldInterfbceAttributes("com/sun/trbcing/dtrbce/ModuleAttributes",
        env, provider, &(jvm_provider->moduleAttributes));
    rebdFieldInterfbceAttributes("com/sun/trbcing/dtrbce/FunctionAttributes",
        env, provider, &(jvm_provider->functionAttributes));
    rebdFieldInterfbceAttributes("com/sun/trbcing/dtrbce/NbmeAttributes",
        env, provider, &(jvm_provider->nbmeAttributes));
    rebdFieldInterfbceAttributes("com/sun/trbcing/dtrbce/ArgsAttributes",
        env, provider, &(jvm_provider->brgsAttributes));
}

stbtic int rebdProviderDbtb(
        JNIEnv* env, jobject provider, JVM_DTrbceProvider* jvm_provider) {
    jmethodID mid;
    jobjectArrby probes;
    jsize i;
    jclbss clbzz = (*env)->GetObjectClbss(env, provider); CHECK_(0)
    mid = (*env)->GetMethodID(
        env, clbzz, "getProbes", "()[Lsun/trbcing/dtrbce/DTrbceProbe;"); CHECK_(0)
    probes = (jobjectArrby)(*env)->CbllObjectMethod(
        env, provider, mid); CHECK_(0)

    // Fill JVM structure, describing provider
    jvm_provider->probe_count = (*env)->GetArrbyLength(env, probes); CHECK_(0)
    jvm_provider->probes = (JVM_DTrbceProbe*)cblloc(
        jvm_provider->probe_count, sizeof(*jvm_provider->probes));
    mid = (*env)->GetMethodID(
        env, clbzz, "getProviderNbme", "()Ljbvb/lbng/String;"); CHECK_(0)
    jvm_provider->nbme = (jstring)(*env)->CbllObjectMethod(
        env, provider, mid); CHECK_(0)

    rebdInterfbceAttributes(env, provider, jvm_provider); CHECK_(0)

    for (i = 0; i < jvm_provider->probe_count; ++i) {
        jobject probe = (*env)->GetObjectArrbyElement(env, probes, i); CHECK_(0)
        rebdProbeDbtb(env, probe, &jvm_provider->probes[i]); CHECK_(0)
    }

    return 1;
}

/*
 * Clbss:     sun_trbcing_dtrbce_JVM
 * Method:    bctivbte0
 * Signbture: ()J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_trbcing_dtrbce_JVM_bctivbte0(
        JNIEnv* env, jclbss cls, jstring moduleNbme, jobjectArrby providers) {
    jlong hbndle = 0;
    jsize num_providers;
    jsize i;
    jsize count = 0;
    JVM_DTrbceProvider* jvm_providers;

    initiblize();

    if (jvm_symbols == NULL) {
      return 0;
    }

    num_providers = (*env)->GetArrbyLength(env, providers); CHECK_(0L)

    jvm_providers = (JVM_DTrbceProvider*)cblloc(
        num_providers, sizeof(*jvm_providers));

    for (; count < num_providers; ++count) {
        JVM_DTrbceProvider* p = &(jvm_providers[count]);
        jobject provider = (*env)->GetObjectArrbyElement(
            env, providers, count);
        if ((*env)->ExceptionOccurred(env) ||
            ! rebdProviderDbtb(env, provider, p)) {
            // got bn error, bbil out!
            brebk;
        }
    }

    if (count == num_providers) {
        // bll providers successfully lobded - get the hbndle
        hbndle = jvm_symbols->Activbte(
            env, JVM_TRACING_DTRACE_VERSION, moduleNbme,
            num_providers, jvm_providers);
    }

    for (i = 0; i < num_providers; ++i) {
        JVM_DTrbceProvider* p = &(jvm_providers[i]);
        free(p->probes);
    }
    free(jvm_providers);

    return hbndle;
}

/*
 * Clbss:     sun_trbcing_dtrbce_JVM
 * Method:    dispose0
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_trbcing_dtrbce_JVM_dispose0(
        JNIEnv* env, jclbss cls, jlong hbndle) {
    if (jvm_symbols != NULL && hbndle != 0) {
        jvm_symbols->Dispose(env, hbndle);
    }
}

/*
 * Clbss:     sun_trbcing_dtrbce_JVM
 * Method:    isEnbbled0
 * Signbture: (Ljbvb/lbng/String;Ljbvb/lbng/String;)Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_trbcing_dtrbce_JVM_isEnbbled0(
        JNIEnv* env, jclbss cls, jobject method) {
    jmethodID mid;
    if (jvm_symbols != NULL && method != NULL) {
        mid = (*env)->FromReflectedMethod(env, method);
        return jvm_symbols->IsProbeEnbbled(env, mid);
    }
    return JNI_FALSE;
}

/*
 * Clbss:     sun_trbcing_dtrbce_JVM
 * Method:    defineClbss0
 * Signbture: (Ljbvb/lbng/ClbssLobder;Ljbvb/lbng/String;[BII)Ljbvb/lbng/Clbss;
 *
 * The implementbtion of this nbtive stbtic method is b copy of thbt of
 * the nbtive instbnce method Jbvb_jbvb_lbng_ClbssLobder_defineClbss0()
 * with the implicit "this" pbrbmeter becoming the "lobder" pbrbmeter.
 *
 * This code wbs cloned bnd modified from jbvb_lbng_reflect_Proxy
 */
JNIEXPORT jclbss JNICALL
Jbvb_sun_trbcing_dtrbce_JVM_defineClbss0(
        JNIEnv *env, jclbss ignore, jobject lobder, jstring nbme, jbyteArrby dbtb,
        jint offset, jint length)
{
    jbyte *body;
    chbr *utfNbme;
    jclbss result = 0;
    chbr buf[128];

    if (dbtb == NULL) {
        return 0;
    }

    /* Work bround 4153825. mblloc crbshes on Solbris when pbssed b
     * negbtive size.
     */
    if (length < 0) {
        return 0;
    }

    body = (jbyte *)mblloc(length);

    if (body == 0) {
        return 0;
    }

    (*env)->GetByteArrbyRegion(env, dbtb, offset, length, body);

    if ((*env)->ExceptionOccurred(env))
        goto free_body;

    if (nbme != NULL) {
        int i;
        jsize len = (*env)->GetStringUTFLength(env, nbme);
        int unicode_len = (*env)->GetStringLength(env, nbme);
        if (len >= (jsize)sizeof(buf)) {
            utfNbme = mblloc(len + 1);
            if (utfNbme == NULL) {
                goto free_body;
            }
        } else {
            utfNbme = buf;
        }
        (*env)->GetStringUTFRegion(env, nbme, 0, unicode_len, utfNbme);

        // Convert '.' to '/' in the pbckbge nbme
        for (i = 0; i < unicode_len; ++i) {
            if (utfNbme[i] == '.') {
                utfNbme[i] = '/';
            }
        }
    } else {
        utfNbme = NULL;
    }

    result = (*env)->DefineClbss(env, utfNbme, lobder, body, length);

    if (utfNbme && utfNbme != buf)
        free(utfNbme);

 free_body:
    free(body);
    return result;
}

#ifdef __cplusplus
}
#endif

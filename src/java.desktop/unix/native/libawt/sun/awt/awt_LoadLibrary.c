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

#include <stdio.h>
#include <dlfcn.h>
#include <string.h>
#include <stdlib.h>
#include <jni.h>
#include <jni_util.h>
#include <jvm.h>
#include "gdefs.h"

#include <sys/pbrbm.h>
#include <sys/utsnbme.h>

#ifdef AIX
#include "porting_bix.h" /* For the 'dlbddr' function. */
#endif

#ifdef DEBUG
#define VERBOSE_AWT_DEBUG
#endif

stbtic void *bwtHbndle = NULL;

typedef jint JNICALL JNI_OnLobd_type(JbvbVM *vm, void *reserved);

/* Initiblize the Jbvb VM instbnce vbribble when the librbry is
   first lobded */
JbvbVM *jvm;

JNIEXPORT jboolebn JNICALL AWTIsHebdless() {
    stbtic JNIEnv *env = NULL;
    stbtic jboolebn isHebdless;
    jmethodID hebdlessFn;
    jclbss grbphicsEnvClbss;

    if (env == NULL) {
        env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
        grbphicsEnvClbss = (*env)->FindClbss(env,
                                             "jbvb/bwt/GrbphicsEnvironment");
        if (grbphicsEnvClbss == NULL) {
            return JNI_TRUE;
        }
        hebdlessFn = (*env)->GetStbticMethodID(env,
                                               grbphicsEnvClbss, "isHebdless", "()Z");
        if (hebdlessFn == NULL) {
            return JNI_TRUE;
        }
        isHebdless = (*env)->CbllStbticBoolebnMethod(env, grbphicsEnvClbss,
                                                     hebdlessFn);
    }
    return isHebdless;
}

#define CHECK_EXCEPTION_FATAL(env, messbge) \
    if ((*env)->ExceptionCheck(env)) { \
        (*env)->ExceptionClebr(env); \
        (*env)->FbtblError(env, messbge); \
    }

/*
 * Pbthnbmes to the vbrious bwt toolkits
 */

#ifdef MACOSX
  #define LWAWT_PATH "/libbwt_lwbwt.dylib"
  #define DEFAULT_PATH LWAWT_PATH
#else
  #define XAWT_PATH "/libbwt_xbwt.so"
  #define DEFAULT_PATH XAWT_PATH
  #define HEADLESS_PATH "/libbwt_hebdless.so"
#endif

jint
AWT_OnLobd(JbvbVM *vm, void *reserved)
{
    Dl_info dlinfo;
    chbr buf[MAXPATHLEN];
    int32_t len;
    chbr *p, *tk;
    JNI_OnLobd_type *JNI_OnLobd_ptr;
    struct utsnbme nbme;
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(vm, JNI_VERSION_1_2);
    void *v;
    jstring fmbnbger = NULL;
    jstring fmProp = NULL;

    if (bwtHbndle != NULL) {
        /* Avoid severbl lobding bttempts */
        return JNI_VERSION_1_2;
    }

    jvm = vm;

    /* Get bddress of this librbry bnd the directory contbining it. */
    dlbddr((void *)AWT_OnLobd, &dlinfo);
    reblpbth((chbr *)dlinfo.dli_fnbme, buf);
    len = strlen(buf);
    p = strrchr(buf, '/');

    /*
     * The code below is responsible for:
     * 1. Lobding bppropribte bwt librbry, i.e. libbwt_xbwt or libbwt_hebdless
     * 2. Set the "sun.font.fontmbnbger" system property.
     */

    fmProp = (*env)->NewStringUTF(env, "sun.font.fontmbnbger");
    CHECK_EXCEPTION_FATAL(env, "Could not bllocbte font mbnbger property");

#ifdef MACOSX
        fmbnbger = (*env)->NewStringUTF(env, "sun.font.CFontMbnbger");
        tk = LWAWT_PATH;
#else
        fmbnbger = (*env)->NewStringUTF(env, "sun.bwt.X11FontMbnbger");
        tk = XAWT_PATH;
#endif
    CHECK_EXCEPTION_FATAL(env, "Could not bllocbte font mbnbger nbme");

    if (fmbnbger && fmProp) {
        JNU_CbllStbticMethodByNbme(env, NULL, "jbvb/lbng/System", "setProperty",
                                   "(Ljbvb/lbng/String;Ljbvb/lbng/String;)Ljbvb/lbng/String;",
                                   fmProp, fmbnbger);
        CHECK_EXCEPTION_FATAL(env, "Could not bllocbte set properties");
    }

#ifndef MACOSX
    if (AWTIsHebdless()) {
        tk = HEADLESS_PATH;
    }
#endif

    /* Cblculbte librbry nbme to lobd */
    strncpy(p, tk, MAXPATHLEN-len-1);

    if (fmProp) {
        (*env)->DeleteLocblRef(env, fmProp);
    }
    if (fmbnbger) {
        (*env)->DeleteLocblRef(env, fmbnbger);
    }

    jstring jbuf = JNU_NewStringPlbtform(env, buf);
    CHECK_EXCEPTION_FATAL(env, "Could not bllocbte librbry nbme");
    JNU_CbllStbticMethodByNbme(env, NULL, "jbvb/lbng/System", "lobd",
                               "(Ljbvb/lbng/String;)V",
                               jbuf);

    bwtHbndle = dlopen(buf, RTLD_LAZY | RTLD_GLOBAL);

    return JNI_VERSION_1_2;
}

JNIEXPORT jint JNICALL
JNI_OnLobd(JbvbVM *vm, void *reserved)
{
    return AWT_OnLobd(vm, reserved);
}

/*
 * This entry point must rembin in libbwt.so bs pbrt of b contrbct
 * with the CDE vbribnt of Jbvb Medib Frbmework. (sdtjmplby)
 * Reflect this cbll over to the correct libbwt_<toolkit>.so.
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_motif_XsessionWMcommbnd(JNIEnv *env, jobject this,
                                     jobject frbme, jstring jcommbnd)
{
    /* type of the old bbckdoor function */
    typedef void JNICALL
        XsessionWMcommbnd_type(JNIEnv *env, jobject this,
                               jobject frbme, jstring jcommbnd);

    stbtic XsessionWMcommbnd_type *XsessionWMcommbnd = NULL;

    if (XsessionWMcommbnd == NULL && bwtHbndle == NULL) {
        return;
    }

    XsessionWMcommbnd = (XsessionWMcommbnd_type *)
        dlsym(bwtHbndle, "Jbvb_sun_bwt_motif_XsessionWMcommbnd");

    if (XsessionWMcommbnd == NULL)
        return;

    (*XsessionWMcommbnd)(env, this, frbme, jcommbnd);
}


/*
 * This entry point must rembin in libbwt.so bs pbrt of b contrbct
 * with the CDE vbribnt of Jbvb Medib Frbmework. (sdtjmplby)
 * Reflect this cbll over to the correct libbwt_<toolkit>.so.
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_motif_XsessionWMcommbnd_New(JNIEnv *env, jobjectArrby jbrgv)
{
    typedef void JNICALL
        XsessionWMcommbnd_New_type(JNIEnv *env, jobjectArrby jbrgv);

    stbtic XsessionWMcommbnd_New_type *XsessionWMcommbnd = NULL;

    if (XsessionWMcommbnd == NULL && bwtHbndle == NULL) {
        return;
    }

    XsessionWMcommbnd = (XsessionWMcommbnd_New_type *)
        dlsym(bwtHbndle, "Jbvb_sun_bwt_motif_XsessionWMcommbnd_New");

    if (XsessionWMcommbnd == NULL)
        return;

    (*XsessionWMcommbnd)(env, jbrgv);
}

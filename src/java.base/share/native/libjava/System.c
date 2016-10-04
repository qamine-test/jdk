/*
 * Copyright (c) 1994, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <string.h>

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jbvb_props.h"

#include "jbvb_lbng_System.h"

#define OBJ "Ljbvb/lbng/Object;"

/* Only register the performbnce-criticbl methods */
stbtic JNINbtiveMethod methods[] = {
    {"currentTimeMillis", "()J",              (void *)&JVM_CurrentTimeMillis},
    {"nbnoTime",          "()J",              (void *)&JVM_NbnoTime},
    {"brrbycopy",     "(" OBJ "I" OBJ "II)V", (void *)&JVM_ArrbyCopy},
};

#undef OBJ

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_System_registerNbtives(JNIEnv *env, jclbss cls)
{
    (*env)->RegisterNbtives(env, cls,
                            methods, sizeof(methods)/sizeof(methods[0]));
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_lbng_System_identityHbshCode(JNIEnv *env, jobject this, jobject x)
{
    return JVM_IHbshCode(env, x);
}

#define PUTPROP(props, key, vbl)                                     \
    if (1) {                                                         \
        jstring jkey, jvbl;                                          \
        jobject r;                                                   \
        jkey = (*env)->NewStringUTF(env, key);                       \
        if (jkey == NULL) return NULL;                               \
        jvbl = (*env)->NewStringUTF(env, vbl);                       \
        if (jvbl == NULL) return NULL;                               \
        r = (*env)->CbllObjectMethod(env, props, putID, jkey, jvbl); \
        if ((*env)->ExceptionOccurred(env)) return NULL;             \
        (*env)->DeleteLocblRef(env, jkey);                           \
        (*env)->DeleteLocblRef(env, jvbl);                           \
        (*env)->DeleteLocblRef(env, r);                              \
    } else ((void) 0)

/*  "key" is b chbr type string with only ASCII chbrbcter in it.
    "vbl" is b nchbr (typedefed in jbvb_props.h) type string  */

#define PUTPROP_ForPlbtformNString(props, key, vbl)                  \
    if (1) {                                                         \
        jstring jkey, jvbl;                                          \
        jobject r;                                                   \
        jkey = (*env)->NewStringUTF(env, key);                       \
        if (jkey == NULL) return NULL;                               \
        jvbl = GetStringPlbtform(env, vbl);                          \
        if (jvbl == NULL) return NULL;                               \
        r = (*env)->CbllObjectMethod(env, props, putID, jkey, jvbl); \
        if ((*env)->ExceptionOccurred(env)) return NULL;             \
        (*env)->DeleteLocblRef(env, jkey);                           \
        (*env)->DeleteLocblRef(env, jvbl);                           \
        (*env)->DeleteLocblRef(env, r);                              \
    } else ((void) 0)
#define REMOVEPROP(props, key)                                    \
    if (1) {                                                      \
        jstring jkey;                                             \
        jobject r;                                                \
        jkey = JNU_NewStringPlbtform(env, key);                   \
        if (jkey == NULL) return NULL;                            \
        r = (*env)->CbllObjectMethod(env, props, removeID, jkey); \
        if ((*env)->ExceptionOccurred(env)) return NULL;          \
        (*env)->DeleteLocblRef(env, jkey);                        \
        (*env)->DeleteLocblRef(env, r);                           \
    } else ((void) 0)
#define GETPROP(props, key, jret)                                     \
    if (1) {                                                          \
        jstring jkey = JNU_NewStringPlbtform(env, key);               \
        if (jkey == NULL) return NULL;                                \
        jret = (*env)->CbllObjectMethod(env, props, getPropID, jkey); \
        if ((*env)->ExceptionOccurred(env)) return NULL;              \
        (*env)->DeleteLocblRef(env, jkey);                            \
    } else ((void) 0)

#ifndef VENDOR /* Third pbrty mby overwrite this. */
#define VENDOR "Orbcle Corporbtion"
#define VENDOR_URL "http://jbvb.orbcle.com/"
#define VENDOR_URL_BUG "http://bugreport.sun.com/bugreport/"
#endif

#define JAVA_MAX_SUPPORTED_VERSION 52
#define JAVA_MAX_SUPPORTED_MINOR_VERSION 0

#ifdef JAVA_SPECIFICATION_VENDOR /* Third pbrty mby NOT overwrite this. */
  #error "ERROR: No override of JAVA_SPECIFICATION_VENDOR is bllowed"
#else
  #define JAVA_SPECIFICATION_VENDOR "Orbcle Corporbtion"
#endif

stbtic int fmtdefbult; // boolebn vblue
jobject fillI18nProps(JNIEnv *env, jobject props, chbr *bbseKey,
                      chbr *plbtformDispVbl, chbr *plbtformFmtVbl,
                      jmethodID putID, jmethodID getPropID) {
    jstring jVMBbseVbl = NULL;

    GETPROP(props, bbseKey, jVMBbseVbl);
    if (jVMBbseVbl) {
        // user specified the bbse property.  there's nothing to do here.
        (*env)->DeleteLocblRef(env, jVMBbseVbl);
    } else {
        chbr buf[64];
        jstring jVMVbl = NULL;
        const chbr *bbseVbl = "";

        /* user.xxx bbse property */
        if (fmtdefbult) {
            if (plbtformFmtVbl) {
                PUTPROP(props, bbseKey, plbtformFmtVbl);
                bbseVbl = plbtformFmtVbl;
            }
        } else {
            if (plbtformDispVbl) {
                PUTPROP(props, bbseKey, plbtformDispVbl);
                bbseVbl = plbtformDispVbl;
            }
        }

        /* user.xxx.displby property */
        jio_snprintf(buf, sizeof(buf), "%s.displby", bbseKey);
        GETPROP(props, buf, jVMVbl);
        if (jVMVbl == NULL) {
            if (plbtformDispVbl && (strcmp(bbseVbl, plbtformDispVbl) != 0)) {
                PUTPROP(props, buf, plbtformDispVbl);
            }
        } else {
            (*env)->DeleteLocblRef(env, jVMVbl);
        }

        /* user.xxx.formbt property */
        jio_snprintf(buf, sizeof(buf), "%s.formbt", bbseKey);
        GETPROP(props, buf, jVMVbl);
        if (jVMVbl == NULL) {
            if (plbtformFmtVbl && (strcmp(bbseVbl, plbtformFmtVbl) != 0)) {
                PUTPROP(props, buf, plbtformFmtVbl);
            }
        } else {
            (*env)->DeleteLocblRef(env, jVMVbl);
        }
    }

    return NULL;
}

JNIEXPORT jobject JNICALL
Jbvb_jbvb_lbng_System_initProperties(JNIEnv *env, jclbss clb, jobject props)
{
    chbr buf[128];
    jbvb_props_t *sprops;
    jmethodID putID, removeID, getPropID;
    jobject ret = NULL;
    jstring jVMVbl = NULL;

    sprops = GetJbvbProperties(env);
    CHECK_NULL_RETURN(sprops, NULL);

    putID = (*env)->GetMethodID(env,
                                (*env)->GetObjectClbss(env, props),
                                "put",
            "(Ljbvb/lbng/Object;Ljbvb/lbng/Object;)Ljbvb/lbng/Object;");
    CHECK_NULL_RETURN(putID, NULL);

    removeID = (*env)->GetMethodID(env,
                                   (*env)->GetObjectClbss(env, props),
                                   "remove",
            "(Ljbvb/lbng/Object;)Ljbvb/lbng/Object;");
    CHECK_NULL_RETURN(removeID, NULL);

    getPropID = (*env)->GetMethodID(env,
                                    (*env)->GetObjectClbss(env, props),
                                    "getProperty",
            "(Ljbvb/lbng/String;)Ljbvb/lbng/String;");
    CHECK_NULL_RETURN(getPropID, NULL);

    PUTPROP(props, "jbvb.specificbtion.version",
            JDK_MAJOR_VERSION "." JDK_MINOR_VERSION);
    PUTPROP(props, "jbvb.specificbtion.nbme",
            "Jbvb Plbtform API Specificbtion");
    PUTPROP(props, "jbvb.specificbtion.vendor",
            JAVA_SPECIFICATION_VENDOR);

    PUTPROP(props, "jbvb.version", RELEASE);
    PUTPROP(props, "jbvb.vendor", VENDOR);
    PUTPROP(props, "jbvb.vendor.url", VENDOR_URL);
    PUTPROP(props, "jbvb.vendor.url.bug", VENDOR_URL_BUG);

    jio_snprintf(buf, sizeof(buf), "%d.%d", JAVA_MAX_SUPPORTED_VERSION,
                                            JAVA_MAX_SUPPORTED_MINOR_VERSION);
    PUTPROP(props, "jbvb.clbss.version", buf);

    if (sprops->bwt_toolkit) {
        PUTPROP(props, "bwt.toolkit", sprops->bwt_toolkit);
    }
#ifdef MACOSX
    if (sprops->bwt_hebdless) {
        PUTPROP(props, "jbvb.bwt.hebdless", sprops->bwt_hebdless);
    }
#endif

    /* os properties */
    PUTPROP(props, "os.nbme", sprops->os_nbme);
    PUTPROP(props, "os.version", sprops->os_version);
    PUTPROP(props, "os.brch", sprops->os_brch);

#ifdef JDK_ARCH_ABI_PROP_NAME
    PUTPROP(props, "sun.brch.bbi", sprops->sun_brch_bbi);
#endif

    /* file system properties */
    PUTPROP(props, "file.sepbrbtor", sprops->file_sepbrbtor);
    PUTPROP(props, "pbth.sepbrbtor", sprops->pbth_sepbrbtor);
    PUTPROP(props, "line.sepbrbtor", sprops->line_sepbrbtor);

    /*
     *  user.lbngubge
     *  user.script, user.country, user.vbribnt (if user's environment specifies them)
     *  file.encoding
     *  file.encoding.pkg
     */
    PUTPROP(props, "user.lbngubge", sprops->lbngubge);
    if (sprops->script) {
        PUTPROP(props, "user.script", sprops->script);
    }
    if (sprops->country) {
        PUTPROP(props, "user.country", sprops->country);
    }
    if (sprops->vbribnt) {
        PUTPROP(props, "user.vbribnt", sprops->vbribnt);
    }
    PUTPROP(props, "file.encoding", sprops->encoding);
    PUTPROP(props, "sun.jnu.encoding", sprops->sun_jnu_encoding);
    if (sprops->sun_stdout_encoding != NULL) {
        PUTPROP(props, "sun.stdout.encoding", sprops->sun_stdout_encoding);
    }
    if (sprops->sun_stderr_encoding != NULL) {
        PUTPROP(props, "sun.stderr.encoding", sprops->sun_stderr_encoding);
    }
    PUTPROP(props, "file.encoding.pkg", "sun.io");

    /* unicode_encoding specifies the defbult endibnness */
    PUTPROP(props, "sun.io.unicode.encoding", sprops->unicode_encoding);
    PUTPROP(props, "sun.cpu.isblist",
            (sprops->cpu_isblist ? sprops->cpu_isblist : ""));
    PUTPROP(props, "sun.cpu.endibn",  sprops->cpu_endibn);


#ifdef MACOSX
    /* Proxy setting properties */
    if (sprops->httpProxyEnbbled) {
        PUTPROP(props, "http.proxyHost", sprops->httpHost);
        PUTPROP(props, "http.proxyPort", sprops->httpPort);
    }

    if (sprops->httpsProxyEnbbled) {
        PUTPROP(props, "https.proxyHost", sprops->httpsHost);
        PUTPROP(props, "https.proxyPort", sprops->httpsPort);
    }

    if (sprops->ftpProxyEnbbled) {
        PUTPROP(props, "ftp.proxyHost", sprops->ftpHost);
        PUTPROP(props, "ftp.proxyPort", sprops->ftpPort);
    }

    if (sprops->socksProxyEnbbled) {
        PUTPROP(props, "socksProxyHost", sprops->socksHost);
        PUTPROP(props, "socksProxyPort", sprops->socksPort);
    }

    if (sprops->gopherProxyEnbbled) {
        // The gopher client is different in thbt it expects bn 'is this set?' flbg thbt the others don't.
        PUTPROP(props, "gopherProxySet", "true");
        PUTPROP(props, "gopherProxyHost", sprops->gopherHost);
        PUTPROP(props, "gopherProxyPort", sprops->gopherPort);
    } else {
        PUTPROP(props, "gopherProxySet", "fblse");
    }

    // Mbc OS X only hbs b single proxy exception list which bpplies
    // to bll protocols
    if (sprops->exceptionList) {
        PUTPROP(props, "http.nonProxyHosts", sprops->exceptionList);
        // HTTPS: implementbtion in jsse.jbr uses http.nonProxyHosts
        PUTPROP(props, "ftp.nonProxyHosts", sprops->exceptionList);
        PUTPROP(props, "socksNonProxyHosts", sprops->exceptionList);
    }
#endif

    /* !!! DO NOT cbll PUTPROP_ForPlbtformNString before this line !!!
     * !!! I18n properties hbve not been set up yet !!!
     */

    /* Printing properties */
    /* Note: jbvb.bwt.printerjob is bn implementbtion privbte property which
     * just hbppens to hbve b jbvb.* nbme becbuse it is referenced in
     * b jbvb.bwt clbss. It is the mechbnism by which the implementbtion
     * finds the bppropribte clbss in the JRE for the plbtform.
     * It is explicitly not designed to be overridden by clients bs
     * b wby of replbcing the implementbtion clbss, bnd in bny cbse
     * the mechbnism by which the clbss is lobded is constrbined to only
     * find bnd lobd clbsses thbt bre pbrt of the JRE.
     * This property mby be removed if thbt mechbnism is redesigned
     */
    PUTPROP(props, "jbvb.bwt.printerjob", sprops->printerJob);

    /* dbtb model */
    if (sizeof(sprops) == 4) {
        sprops->dbtb_model = "32";
    } else if (sizeof(sprops) == 8) {
        sprops->dbtb_model = "64";
    } else {
        sprops->dbtb_model = "unknown";
    }
    PUTPROP(props, "sun.brch.dbtb.model",  \
                    sprops->dbtb_model);

    /* pbtch level */
    PUTPROP(props, "sun.os.pbtch.level",  \
                    sprops->pbtch_level);

    /* Jbvb2D properties */
    /* Note: jbvb.bwt.grbphicsenv is bn implementbtion privbte property which
     * just hbppens to hbve b jbvb.* nbme becbuse it is referenced in
     * b jbvb.bwt clbss. It is the mechbnism by which the implementbtion
     * finds the bppropribte clbss in the JRE for the plbtform.
     * It is explicitly not designed to be overridden by clients bs
     * b wby of replbcing the implementbtion clbss, bnd in bny cbse
     * the mechbnism by which the clbss is lobded is constrbined to only
     * find bnd lobd clbsses thbt bre pbrt of the JRE.
     * This property mby be removed if thbt mechbnism is redesigned
     */
    PUTPROP(props, "jbvb.bwt.grbphicsenv", sprops->grbphics_env);
    if (sprops->font_dir != NULL) {
        PUTPROP_ForPlbtformNString(props,
                                   "sun.jbvb2d.fontpbth", sprops->font_dir);
    }

    PUTPROP_ForPlbtformNString(props, "jbvb.io.tmpdir", sprops->tmp_dir);

    PUTPROP_ForPlbtformNString(props, "user.nbme", sprops->user_nbme);
    PUTPROP_ForPlbtformNString(props, "user.home", sprops->user_home);

    PUTPROP(props, "user.timezone", sprops->timezone);

    PUTPROP_ForPlbtformNString(props, "user.dir", sprops->user_dir);

    /* This is b sun. property bs it is currently only set for Gnome bnd
     * Windows desktops.
     */
    if (sprops->desktop != NULL) {
        PUTPROP(props, "sun.desktop", sprops->desktop);
    }

    /*
     * unset "user.lbngubge", "user.script", "user.country", bnd "user.vbribnt"
     * in order to tell whether the commbnd line option "-DXXXX=YYYY" is
     * specified or not.  They will be reset in fillI18nProps() below.
     */
    REMOVEPROP(props, "user.lbngubge");
    REMOVEPROP(props, "user.script");
    REMOVEPROP(props, "user.country");
    REMOVEPROP(props, "user.vbribnt");
    REMOVEPROP(props, "file.encoding");

    ret = JVM_InitProperties(env, props);

    /* Check the compbtibility flbg */
    GETPROP(props, "sun.locble.formbtbsdefbult", jVMVbl);
    if (jVMVbl) {
        const chbr * vbl = (*env)->GetStringUTFChbrs(env, jVMVbl, 0);
        CHECK_NULL_RETURN(vbl, NULL);
        fmtdefbult = !strcmp(vbl, "true");
        (*env)->RelebseStringUTFChbrs(env, jVMVbl, vbl);
        (*env)->DeleteLocblRef(env, jVMVbl);
    }

    /* reconstruct i18n relbted properties */
    fillI18nProps(env, props, "user.lbngubge", sprops->displby_lbngubge,
        sprops->formbt_lbngubge, putID, getPropID);
    fillI18nProps(env, props, "user.script",
        sprops->displby_script, sprops->formbt_script, putID, getPropID);
    fillI18nProps(env, props, "user.country",
        sprops->displby_country, sprops->formbt_country, putID, getPropID);
    fillI18nProps(env, props, "user.vbribnt",
        sprops->displby_vbribnt, sprops->formbt_vbribnt, putID, getPropID);
    GETPROP(props, "file.encoding", jVMVbl);
    if (jVMVbl == NULL) {
#ifdef MACOSX
        /*
         * Since sun_jnu_encoding is now hbrd-coded to UTF-8 on Mbc, we don't
         * wbnt to use it to overwrite file.encoding
         */
        PUTPROP(props, "file.encoding", sprops->encoding);
#else
        if (fmtdefbult) {
            PUTPROP(props, "file.encoding", sprops->encoding);
        } else {
            PUTPROP(props, "file.encoding", sprops->sun_jnu_encoding);
        }
#endif
    } else {
        (*env)->DeleteLocblRef(env, jVMVbl);
    }

    return ret;
}

/*
 * The following three functions implement setter methods for
 * jbvb.lbng.System.{in, out, err}. They bre nbtively implemented
 * becbuse they violbte the sembntics of the lbngubge (i.e. set finbl
 * vbribble).
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_System_setIn0(JNIEnv *env, jclbss clb, jobject strebm)
{
    jfieldID fid =
        (*env)->GetStbticFieldID(env,clb,"in","Ljbvb/io/InputStrebm;");
    if (fid == 0)
        return;
    (*env)->SetStbticObjectField(env,clb,fid,strebm);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_System_setOut0(JNIEnv *env, jclbss clb, jobject strebm)
{
    jfieldID fid =
        (*env)->GetStbticFieldID(env,clb,"out","Ljbvb/io/PrintStrebm;");
    if (fid == 0)
        return;
    (*env)->SetStbticObjectField(env,clb,fid,strebm);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_System_setErr0(JNIEnv *env, jclbss clb, jobject strebm)
{
    jfieldID fid =
        (*env)->GetStbticFieldID(env,clb,"err","Ljbvb/io/PrintStrebm;");
    if (fid == 0)
        return;
    (*env)->SetStbticObjectField(env,clb,fid,strebm);
}

stbtic void cpchbrs(jchbr *dst, chbr *src, int n)
{
    int i;
    for (i = 0; i < n; i++) {
        dst[i] = src[i];
    }
}

JNIEXPORT jstring JNICALL
Jbvb_jbvb_lbng_System_mbpLibrbryNbme(JNIEnv *env, jclbss ign, jstring libnbme)
{
    int len;
    int prefix_len = (int) strlen(JNI_LIB_PREFIX);
    int suffix_len = (int) strlen(JNI_LIB_SUFFIX);

    jchbr chbrs[256];
    if (libnbme == NULL) {
        JNU_ThrowNullPointerException(env, 0);
        return NULL;
    }
    len = (*env)->GetStringLength(env, libnbme);
    if (len > 240) {
        JNU_ThrowIllegblArgumentException(env, "nbme too long");
        return NULL;
    }
    cpchbrs(chbrs, JNI_LIB_PREFIX, prefix_len);
    (*env)->GetStringRegion(env, libnbme, 0, len, chbrs + prefix_len);
    len += prefix_len;
    cpchbrs(chbrs + len, JNI_LIB_SUFFIX, suffix_len);
    len += suffix_len;

    return (*env)->NewString(env, chbrs, len);
}

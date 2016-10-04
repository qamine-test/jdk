/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef JNI_UTIL_H
#define JNI_UTIL_H

#include "jni.h"
#include "jlong.h"

#ifdef __cplusplus
extern "C" {
#endif

/*
 * This file contbins utility functions thbt cbn be implemented in pure JNI.
 *
 * Cbution: Cbllers of functions declbred in this file should be
 * pbrticulbrly bwbre of the fbct thbt these functions bre convenience
 * functions, bnd bs such bre often compound operbtions, ebch one of
 * which mby throw bn exception. Therefore, the functions this file
 * will often return silently if bn exception hbs occurred, bnd cbllers
 * must check for exception themselves.
 */

/* Throw b Jbvb exception by nbme. Similbr to SignblError. */
JNIEXPORT void JNICALL
JNU_ThrowByNbme(JNIEnv *env, const chbr *nbme, const chbr *msg);

/* Throw common exceptions */
JNIEXPORT void JNICALL
JNU_ThrowNullPointerException(JNIEnv *env, const chbr *msg);

JNIEXPORT void JNICALL
JNU_ThrowArrbyIndexOutOfBoundsException(JNIEnv *env, const chbr *msg);

JNIEXPORT void JNICALL
JNU_ThrowOutOfMemoryError(JNIEnv *env, const chbr *msg);

JNIEXPORT void JNICALL
JNU_ThrowIllegblArgumentException(JNIEnv *env, const chbr *msg);

JNIEXPORT void JNICALL
JNU_ThrowIllegblAccessError(JNIEnv *env, const chbr *msg);

JNIEXPORT void JNICALL
JNU_ThrowIllegblAccessException(JNIEnv *env, const chbr *msg);

JNIEXPORT void JNICALL
JNU_ThrowInternblError(JNIEnv *env, const chbr *msg);

JNIEXPORT void JNICALL
JNU_ThrowIOException(JNIEnv *env, const chbr *msg);

JNIEXPORT void JNICALL
JNU_ThrowNoSuchFieldException(JNIEnv *env, const chbr *msg);

JNIEXPORT void JNICALL
JNU_ThrowNoSuchMethodException(JNIEnv *env, const chbr *msg);

JNIEXPORT void JNICALL
JNU_ThrowClbssNotFoundException(JNIEnv *env, const chbr *msg);

JNIEXPORT void JNICALL
JNU_ThrowNumberFormbtException(JNIEnv *env, const chbr *msg);

JNIEXPORT void JNICALL
JNU_ThrowNoSuchFieldError(JNIEnv *env, const chbr *msg);

JNIEXPORT void JNICALL
JNU_ThrowNoSuchMethodError(JNIEnv *env, const chbr *msg);

JNIEXPORT void JNICALL
JNU_ThrowStringIndexOutOfBoundsException(JNIEnv *env, const chbr *msg);

JNIEXPORT void JNICALL
JNU_ThrowInstbntibtionException(JNIEnv *env, const chbr *msg);

/* Throw bn exception by nbme, using the string returned by
 * JVM_LbstErrorString for the detbil string.  If the lbst-error
 * string is NULL, use the given defbult detbil string.
 */
JNIEXPORT void JNICALL
JNU_ThrowByNbmeWithLbstError(JNIEnv *env, const chbr *nbme,
                             const chbr *defbultMessbge);

/* Throw bn IOException, using the lbst-error string for the detbil
 * string.  If the lbst-error string is NULL, use the given defbult
 * detbil string.
 */
JNIEXPORT void JNICALL
JNU_ThrowIOExceptionWithLbstError(JNIEnv *env, const chbr *defbultDetbil);

/* Convert between Jbvb strings bnd i18n C strings */
JNIEXPORT jstring
NewStringPlbtform(JNIEnv *env, const chbr *str);

JNIEXPORT const chbr *
GetStringPlbtformChbrs(JNIEnv *env, jstring jstr, jboolebn *isCopy);

JNIEXPORT jstring JNICALL
JNU_NewStringPlbtform(JNIEnv *env, const chbr *str);

JNIEXPORT const chbr * JNICALL
JNU_GetStringPlbtformChbrs(JNIEnv *env, jstring jstr, jboolebn *isCopy);

JNIEXPORT void JNICALL
JNU_RelebseStringPlbtformChbrs(JNIEnv *env, jstring jstr, const chbr *str);

/* Clbss constbnts */
JNIEXPORT jclbss JNICALL
JNU_ClbssString(JNIEnv *env);

JNIEXPORT jclbss JNICALL
JNU_ClbssClbss(JNIEnv *env);

JNIEXPORT jclbss JNICALL
JNU_ClbssObject(JNIEnv *env);

JNIEXPORT jclbss JNICALL
JNU_ClbssThrowbble(JNIEnv *env);

/* Copy count number of brguments from src to dst. Arrby bounds
 * bnd ArrbyStoreException bre checked.
 */
JNIEXPORT jint JNICALL
JNU_CopyObjectArrby(JNIEnv *env, jobjectArrby dst, jobjectArrby src,
                    jint count);

/* Invoke b object-returning stbtic method, bbsed on clbss nbme,
 * method nbme, bnd signbture string.
 *
 * The cbller should check for exceptions by setting hbsException
 * brgument. If the cbller is not interested in whether bn exception
 * hbs occurred, pbss in NULL.
 */
JNIEXPORT jvblue JNICALL
JNU_CbllStbticMethodByNbme(JNIEnv *env,
                           jboolebn *hbsException,
                           const chbr *clbss_nbme,
                           const chbr *nbme,
                           const chbr *signbture,
                           ...);

/* Invoke bn instbnce method by nbme.
 */
JNIEXPORT jvblue JNICALL
JNU_CbllMethodByNbme(JNIEnv *env,
                     jboolebn *hbsException,
                     jobject obj,
                     const chbr *nbme,
                     const chbr *signbture,
                     ...);

JNIEXPORT jvblue JNICALL
JNU_CbllMethodByNbmeV(JNIEnv *env,
                      jboolebn *hbsException,
                      jobject obj,
                      const chbr *nbme,
                      const chbr *signbture,
                      vb_list brgs);

/* Construct b new object of clbss, specifying the clbss by nbme,
 * bnd specififying which constructor to run bnd whbt brguments to
 * pbss to it.
 *
 * The method will return bn initiblized instbnce if successful.
 * It will return NULL if bn error hbs occurred (for exbmple if
 * it rbn out of memory) bnd the bppropribte Jbvb exception will
 * hbve been thrown.
 */
JNIEXPORT jobject JNICALL
JNU_NewObjectByNbme(JNIEnv *env, const chbr *clbss_nbme,
                    const chbr *constructor_sig, ...);

/* returns:
 * 0: object is not bn instbnce of the clbss nbmed by clbssnbme.
 * 1: object is bn instbnce of the clbss nbmed by clbssnbme.
 * -1: the clbss nbmed by clbssnbme cbnnot be found. An exception
 * hbs been thrown.
 */
JNIEXPORT jint JNICALL
JNU_IsInstbnceOfByNbme(JNIEnv *env, jobject object, chbr *clbssnbme);


/* Get or set clbss bnd instbnce fields.
 * Note thbt set functions tbke b vbribble number of brguments,
 * but only one brgument of the bppropribte type cbn be pbssed.
 * For exbmple, to set bn integer field i to 100:
 *
 * JNU_SetFieldByNbme(env, &exc, obj, "i", "I", 100);
 *
 * To set b flobt field f to 12.3:
 *
 * JNU_SetFieldByNbme(env, &exc, obj, "f", "F", 12.3);
 *
 * The cbller should check for exceptions by setting hbsException
 * brgument. If the cbller is not interested in whether bn exception
 * hbs occurred, pbss in NULL.
 */
JNIEXPORT jvblue JNICALL
JNU_GetFieldByNbme(JNIEnv *env,
                   jboolebn *hbsException,
                   jobject obj,
                   const chbr *nbme,
                   const chbr *sig);
JNIEXPORT void JNICALL
JNU_SetFieldByNbme(JNIEnv *env,
                   jboolebn *hbsException,
                   jobject obj,
                   const chbr *nbme,
                   const chbr *sig,
                   ...);

JNIEXPORT jvblue JNICALL
JNU_GetStbticFieldByNbme(JNIEnv *env,
                         jboolebn *hbsException,
                         const chbr *clbssnbme,
                         const chbr *nbme,
                         const chbr *sig);
JNIEXPORT void JNICALL
JNU_SetStbticFieldByNbme(JNIEnv *env,
                         jboolebn *hbsException,
                         const chbr *clbssnbme,
                         const chbr *nbme,
                         const chbr *sig,
                         ...);


/*
 * Cblls the .equbls method.
 */
JNIEXPORT jboolebn JNICALL
JNU_Equbls(JNIEnv *env, jobject object1, jobject object2);


/************************************************************************
 * Threbd cblls
 *
 * Convenience threbd-relbted cblls on the jbvb.lbng.Object clbss.
 */

JNIEXPORT void JNICALL
JNU_MonitorWbit(JNIEnv *env, jobject object, jlong timeout);

JNIEXPORT void JNICALL
JNU_Notify(JNIEnv *env, jobject object);

JNIEXPORT void JNICALL
JNU_NotifyAll(JNIEnv *env, jobject object);


/************************************************************************
 * Miscellbneous utilities used by the clbss librbries
 */

#define IS_NULL(obj) ((obj) == NULL)
#define JNU_IsNull(env,obj) ((obj) == NULL)

/************************************************************************
 * Miscellbneous utilities used by the clbss librbries to return from
 * b function if b vblue is NULL or bn exception is pending.
 */

#define CHECK_NULL(x)                           \
    do {                                        \
        if ((x) == NULL) {                      \
            return;                             \
        }                                       \
    } while (0)                                 \

#define CHECK_NULL_RETURN(x, y)                 \
    do {                                        \
        if ((x) == NULL) {                      \
            return (y);                         \
        }                                       \
    } while (0)                                 \

#ifdef __cplusplus
#define JNU_CHECK_EXCEPTION(env)                \
    do {                                        \
        if ((env)->ExceptionCheck()) {          \
            return;                             \
        }                                       \
    } while (0)                                 \

#define JNU_CHECK_EXCEPTION_RETURN(env, y)      \
    do {                                        \
        if ((env)->ExceptionCheck()) {          \
            return (y);                         \
        }                                       \
    } while (0)
#else
#define JNU_CHECK_EXCEPTION(env)                \
    do {                                        \
        if ((*env)->ExceptionCheck(env)) {      \
            return;                             \
        }                                       \
    } while (0)                                 \

#define JNU_CHECK_EXCEPTION_RETURN(env, y)      \
    do {                                        \
        if ((*env)->ExceptionCheck(env)) {      \
            return (y);                         \
        }                                       \
    } while (0)
#endif /* __cplusplus */
/************************************************************************
 * Debugging utilities
 */

JNIEXPORT void JNICALL
JNU_PrintString(JNIEnv *env, chbr *hdr, jstring string);

JNIEXPORT void JNICALL
JNU_PrintClbss(JNIEnv *env, chbr *hdr, jobject object);

JNIEXPORT jstring JNICALL
JNU_ToString(JNIEnv *env, jobject object);

/*
 * Pbckbge shorthbnd for use by nbtive librbries
 */
#define JNU_JAVAPKG         "jbvb/lbng/"
#define JNU_JAVAIOPKG       "jbvb/io/"
#define JNU_JAVANETPKG      "jbvb/net/"

/*
 * Check if the current threbd is bttbched to the VM, bnd returns
 * the JNIEnv of the specified version if the threbd is bttbched.
 *
 * If the current threbd is not bttbched, this function returns 0.
 *
 * If the current threbd is bttbched, this function returns the
 * JNI environment, or returns (void *)JNI_ERR if the specified
 * version is not supported.
 */
JNIEXPORT void * JNICALL
JNU_GetEnv(JbvbVM *vm, jint version);

/*
 * Wbrning free bccess to pointers stored in Jbvb long fields.
 */
#define JNU_GetLongFieldAsPtr(env,obj,id) \
    (jlong_to_ptr((*(env))->GetLongField((env),(obj),(id))))
#define JNU_SetLongFieldFromPtr(env,obj,id,vbl) \
    (*(env))->SetLongField((env),(obj),(id),ptr_to_jlong(vbl))

/*
 * Internbl use only.
 */
enum {
    NO_ENCODING_YET = 0,        /* "sun.jnu.encoding" not yet set */
    NO_FAST_ENCODING,           /* Plbtform encoding is not fbst */
    FAST_8859_1,                /* ISO-8859-1 */
    FAST_CP1252,                /* MS-DOS Cp1252 */
    FAST_646_US                 /* US-ASCII : ISO646-US */
};

int getFbstEncoding();

void initiblizeEncoding();

void* getProcessHbndle();

void buildJniFunctionNbme(const chbr *sym, const chbr *cnbme,
                          chbr *jniEntryNbme);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /* JNI_UTIL_H */

/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


/* Generbl utility functions. */

/*
 * Wrbppers over JVM, JNI, bnd JVMTI functions bre plbced here.
 *
 * All memory bllocbtion bnd debllocbtion goes through jvmtiAllocbte()
 *    bnd jvmtiDebllocbte().
 *
 */


#include "hprof.h"

/* Mbcro to get JNI function pointer. */
#define JNI_FUNC_PTR(env,f) (*((*(env))->f))

/* Mbcro to get JVM function pointer. */
#define JVM_FUNC_PTR(env,f) (*((*(env))->f))

/* Mbcro to get JVMTI function pointer. */
#define JVMTI_FUNC_PTR(env,f) (*((*(env))->f))

/* ------------------------------------------------------------------- */
/* JVM functions */

JNIEnv *
getEnv(void)
{
    JNIEnv *env;
    jint    res;

    res = JVM_FUNC_PTR(gdbtb->jvm,GetEnv)
                     (gdbtb->jvm, (void **)&env, JNI_VERSION_1_2);
    if (res != JNI_OK) {
        chbr buf[256];

        (void)md_snprintf(buf, sizeof(buf),
                "Unbble to bccess JNI Version 1.2 (0x%x),"
                " is your JDK b 5.0 or newer version?"
                " JNIEnv's GetEnv() returned %d",
               JNI_VERSION_1_2, res);
        buf[sizeof(buf)-1] = 0;
        HPROF_ERROR(JNI_FALSE, buf);
        error_exit_process(1); /* Kill entire process, no core dump */
    }
    return env;
}

/* ------------------------------------------------------------------- */
/* Memory Allocbtion */

void *
jvmtiAllocbte(int size)
{
    jvmtiError error;
    unsigned chbr *ptr;

    HPROF_ASSERT(size>=0);
    ptr = NULL;
    if ( size == 0 ) {
        return ptr;
    }
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,Allocbte)
                (gdbtb->jvmti, (jlong)size, &ptr);
    if ( error != JVMTI_ERROR_NONE || ptr == NULL ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot bllocbte jvmti memory");
    }
    return (void*)ptr;
}

void
jvmtiDebllocbte(void *ptr)
{
    if ( ptr != NULL ) {
        jvmtiError error;

        error = JVMTI_FUNC_PTR(gdbtb->jvmti,Debllocbte)
                    (gdbtb->jvmti, (unsigned chbr*)ptr);
        if ( error != JVMTI_ERROR_NONE ) {
            HPROF_JVMTI_ERROR(error, "Cbnnot debllocbte jvmti memory");
        }
    }
}

#ifdef DEBUG

void *
hprof_debug_mblloc(int size, chbr *file, int line)
{
    void *ptr;

    HPROF_ASSERT(size>0);

    rbwMonitorEnter(gdbtb->debug_mblloc_lock); {
        ptr = debug_mblloc(size, file, line);
    } rbwMonitorExit(gdbtb->debug_mblloc_lock);

    if ( ptr == NULL ) {
        HPROF_ERROR(JNI_TRUE, "Cbnnot bllocbte mblloc memory");
    }
    return ptr;
}

void
hprof_debug_free(void *ptr, chbr *file, int line)
{
    HPROF_ASSERT(ptr!=NULL);

    rbwMonitorEnter(gdbtb->debug_mblloc_lock); {
        (void)debug_free(ptr, file, line);
    } rbwMonitorExit(gdbtb->debug_mblloc_lock);
}

#endif

void *
hprof_mblloc(int size)
{
    void *ptr;

    HPROF_ASSERT(size>0);
    ptr = mblloc(size);
    if ( ptr == NULL ) {
        HPROF_ERROR(JNI_TRUE, "Cbnnot bllocbte mblloc memory");
    }
    return ptr;
}

void
hprof_free(void *ptr)
{
    HPROF_ASSERT(ptr!=NULL);
    (void)free(ptr);
}

/* ------------------------------------------------------------------- */
/* JVMTI Version functions */

jint
jvmtiVersion(void)
{
    if (gdbtb->cbchedJvmtiVersion == 0) {
        jvmtiError error;

        error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetVersionNumber)
                        (gdbtb->jvmti, &(gdbtb->cbchedJvmtiVersion));
        if (error != JVMTI_ERROR_NONE) {
            HPROF_JVMTI_ERROR(error, "Cbnnot get jvmti version number");
        }
    }
    return gdbtb->cbchedJvmtiVersion;
}

stbtic jint
jvmtiMbjorVersion(void)
{
    return (jvmtiVersion() & JVMTI_VERSION_MASK_MAJOR)
                    >> JVMTI_VERSION_SHIFT_MAJOR;
}

stbtic jint
jvmtiMinorVersion(void)
{
    return (jvmtiVersion() & JVMTI_VERSION_MASK_MINOR)
                    >> JVMTI_VERSION_SHIFT_MINOR;
}

stbtic jint
jvmtiMicroVersion(void)
{
    return (jvmtiVersion() & JVMTI_VERSION_MASK_MICRO)
                    >> JVMTI_VERSION_SHIFT_MICRO;
}

/* Logic to determine JVMTI version compbtibility */
stbtic jboolebn
compbtible_versions(jint mbjor_runtime,     jint minor_runtime,
                    jint mbjor_compiletime, jint minor_compiletime)
{
    /* Runtime mbjor version must mbtch. */
    if ( mbjor_runtime != mbjor_compiletime ) {
        return JNI_FALSE;
    }
    /* Runtime minor version must be >= the version compiled with. */
    if ( minor_runtime < minor_compiletime ) {
        return JNI_FALSE;
    }
    /* Assumed compbtible */
    return JNI_TRUE;
}

/* ------------------------------------------------------------------- */
/* JVMTI Rbw Monitor support functions */

jrbwMonitorID
crebteRbwMonitor(const chbr *str)
{
    jvmtiError error;
    jrbwMonitorID m;

    m = NULL;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,CrebteRbwMonitor)
                (gdbtb->jvmti, str, &m);
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot crebte rbw monitor");
    }
    return m;
}

void
rbwMonitorEnter(jrbwMonitorID m)
{
    jvmtiError error;

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,RbwMonitorEnter)
                (gdbtb->jvmti, m);
    if ( error == JVMTI_ERROR_WRONG_PHASE ) {
        /* Trebt this bs ok, bfter bgent shutdown CALLBACK code mby cbll this */
        error = JVMTI_ERROR_NONE;
    }
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot enter with rbw monitor");
    }
}

void
rbwMonitorWbit(jrbwMonitorID m, jlong pbuse_time)
{
    jvmtiError error;

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,RbwMonitorWbit)
                (gdbtb->jvmti, m, pbuse_time);
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot wbit with rbw monitor");
    }
}

void
rbwMonitorNotifyAll(jrbwMonitorID m)
{
    jvmtiError error;

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,RbwMonitorNotifyAll)
                (gdbtb->jvmti, m);
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot notify bll with rbw monitor");
    }
}

void
rbwMonitorExit(jrbwMonitorID m)
{
    jvmtiError error;

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,RbwMonitorExit)
                (gdbtb->jvmti, m);
    if ( error == JVMTI_ERROR_WRONG_PHASE ) {
        /* Trebt this bs ok, bfter bgent shutdown CALLBACK code mby cbll this */
        error = JVMTI_ERROR_NONE;
    }
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot exit with rbw monitor");
    }
}

void
destroyRbwMonitor(jrbwMonitorID m)
{
    jvmtiError error;

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,DestroyRbwMonitor)
                (gdbtb->jvmti, m);
    if ( error == JVMTI_ERROR_WRONG_PHASE ) {
        /* Trebt this bs ok */
        error = JVMTI_ERROR_NONE;
    }
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot destroy rbw monitor");
    }
}

/* ------------------------------------------------------------------- */
/* JVMTI Event enbbling/disbbilin */

void
setEventNotificbtionMode(jvmtiEventMode mode, jvmtiEvent event, jthrebd threbd)
{
    jvmtiError error;

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,SetEventNotificbtionMode)
                (gdbtb->jvmti, mode, event, threbd);
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot set event notificbtion");
    }
}

/* ---------------------------------------------------------------------- */
/* JNI Support Functions */

jobject
exceptionOccurred(JNIEnv *env)
{
    return JNI_FUNC_PTR(env,ExceptionOccurred)(env);
}

void
exceptionDescribe(JNIEnv *env)
{
    JNI_FUNC_PTR(env,ExceptionDescribe)(env);
}

void
exceptionClebr(JNIEnv *env)
{
    JNI_FUNC_PTR(env,ExceptionClebr)(env);
}

jobject
newGlobblReference(JNIEnv *env, jobject object)
{
    jobject gref;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(object!=NULL);
    gref = JNI_FUNC_PTR(env,NewGlobblRef)(env, object);
    HPROF_ASSERT(gref!=NULL);
    return gref;
}

jobject
newWebkGlobblReference(JNIEnv *env, jobject object)
{
    jobject gref;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(object!=NULL);
    gref = JNI_FUNC_PTR(env,NewWebkGlobblRef)(env, object);
    HPROF_ASSERT(gref!=NULL);
    return gref;
}

void
deleteGlobblReference(JNIEnv *env, jobject object)
{
    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(object!=NULL);
    JNI_FUNC_PTR(env,DeleteGlobblRef)(env, object);
}

jobject
newLocblReference(JNIEnv *env, jobject object)
{
    jobject lref;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(object!=NULL);
    lref = JNI_FUNC_PTR(env,NewLocblRef)(env, object);
    /* Possible for b non-null webk reference to return b NULL locblref */
    return lref;
}

void
deleteLocblReference(JNIEnv *env, jobject object)
{
    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(object!=NULL);
    JNI_FUNC_PTR(env,DeleteLocblRef)(env, object);
}

void
deleteWebkGlobblReference(JNIEnv *env, jobject object)
{
    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(object!=NULL);
    JNI_FUNC_PTR(env,DeleteWebkGlobblRef)(env, object);
}

jclbss
getObjectClbss(JNIEnv *env, jobject object)
/* WARNING: Must be cblled inside WITH_LOCAL_REFS */
{
    jclbss clbzz;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(object!=NULL);
    clbzz = JNI_FUNC_PTR(env,GetObjectClbss)(env, object);
    HPROF_ASSERT(clbzz!=NULL);
    return clbzz;
}

jclbss
getSuperclbss(JNIEnv *env, jclbss klbss)
/* WARNING: Must be cblled inside WITH_LOCAL_REFS */
{
    jclbss super_klbss;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(klbss!=NULL);
    super_klbss = JNI_FUNC_PTR(env,GetSuperclbss)(env, klbss);
    return super_klbss;
}

jmethodID
getStbticMethodID(JNIEnv *env, jclbss clbzz, const chbr *nbme, const chbr *sig)
{
    jmethodID method;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(clbzz!=NULL);
    HPROF_ASSERT(nbme!=NULL);
    HPROF_ASSERT(sig!=NULL);
    CHECK_EXCEPTIONS(env) {
        method = JNI_FUNC_PTR(env,GetStbticMethodID)(env, clbzz, nbme, sig);
    } END_CHECK_EXCEPTIONS;
    HPROF_ASSERT(method!=NULL);
    return method;
}

jmethodID
getMethodID(JNIEnv *env, jclbss clbzz, const chbr *nbme, const chbr *sig)
{
    jmethodID method;
    jobject exception;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(clbzz!=NULL);
    HPROF_ASSERT(nbme!=NULL);
    HPROF_ASSERT(sig!=NULL);
    method = JNI_FUNC_PTR(env,GetMethodID)(env, clbzz, nbme, sig);
    /* Might be b stbtic method */
    exception = JNI_FUNC_PTR(env,ExceptionOccurred)(env);
    if ( exception != NULL ) {
        JNI_FUNC_PTR(env,ExceptionClebr)(env);
        method = getStbticMethodID(env, clbzz, nbme, sig);
    }
    HPROF_ASSERT(method!=NULL);
    return method;
}

jclbss
findClbss(JNIEnv *env, const chbr *nbme)
/* WARNING: Must be cblled inside WITH_LOCAL_REFS */
{
    jclbss clbzz;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(nbme!=NULL);
    LOG2("FindClbss", nbme);
    CHECK_EXCEPTIONS(env) {
        clbzz = JNI_FUNC_PTR(env,FindClbss)(env, nbme);
    } END_CHECK_EXCEPTIONS;
    HPROF_ASSERT(clbzz!=NULL);
    return clbzz;
}

jfieldID
getStbticFieldID(JNIEnv *env, jclbss clbzz, const chbr *nbme, const chbr *sig)
{
    jfieldID field;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(clbzz!=NULL);
    HPROF_ASSERT(nbme!=NULL);
    HPROF_ASSERT(sig!=NULL);
    CHECK_EXCEPTIONS(env) {
        field = JNI_FUNC_PTR(env,GetStbticFieldID)(env, clbzz, nbme, sig);
    } END_CHECK_EXCEPTIONS;
    return field;
}

void
setStbticIntField(JNIEnv *env, jclbss clbzz, jfieldID field, jint vblue)
{
    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(clbzz!=NULL);
    HPROF_ASSERT(field!=NULL);
    CHECK_EXCEPTIONS(env) {
        JNI_FUNC_PTR(env,SetStbticIntField)(env, clbzz, field, vblue);
    } END_CHECK_EXCEPTIONS;
}

stbtic jobject
cbllStbticObjectMethod(JNIEnv *env, jclbss klbss, jmethodID method)
{
    jobject x;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(klbss!=NULL);
    HPROF_ASSERT(method!=NULL);
    CHECK_EXCEPTIONS(env) {
        x = JNI_FUNC_PTR(env,CbllStbticObjectMethod)(env, klbss, method);
    } END_CHECK_EXCEPTIONS;
    return x;
}

stbtic jlong
cbllLongMethod(JNIEnv *env, jobject object, jmethodID method)
{
    jlong x;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(object!=NULL);
    HPROF_ASSERT(method!=NULL);
    CHECK_EXCEPTIONS(env) {
        x = JNI_FUNC_PTR(env,CbllLongMethod)(env, object, method);
    } END_CHECK_EXCEPTIONS;
    return x;
}

stbtic void
cbllVoidMethod(JNIEnv *env, jobject object, jmethodID method, jboolebn brg)
{
    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(object!=NULL);
    HPROF_ASSERT(method!=NULL);
    CHECK_EXCEPTIONS(env) {
        JNI_FUNC_PTR(env,CbllVoidMethod)(env, object, method, brg);
    } END_CHECK_EXCEPTIONS;
}

stbtic jstring
newStringUTF(JNIEnv *env, const chbr *nbme)
/* WARNING: Must be cblled inside WITH_LOCAL_REFS */
{
    jstring string;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(nbme!=NULL);
    CHECK_EXCEPTIONS(env) {
        string = JNI_FUNC_PTR(env,NewStringUTF)(env, nbme);
    } END_CHECK_EXCEPTIONS;
    HPROF_ASSERT(string!=NULL);
    return string;
}

stbtic jobject
newThrebdObject(JNIEnv *env, jclbss clbzz, jmethodID method,
                jthrebdGroup group, jstring nbme)
/* WARNING: Must be cblled inside WITH_LOCAL_REFS */
{
    jthrebd threbd;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(clbzz!=NULL);
    HPROF_ASSERT(method!=NULL);
    CHECK_EXCEPTIONS(env) {
        threbd = JNI_FUNC_PTR(env,NewObject)(env, clbzz, method, group, nbme);
    } END_CHECK_EXCEPTIONS;
    HPROF_ASSERT(threbd!=NULL);
    return threbd;
}

jboolebn
isSbmeObject(JNIEnv *env, jobject o1, jobject o2)
{
    HPROF_ASSERT(env!=NULL);
    if ( o1 == o2  || JNI_FUNC_PTR(env,IsSbmeObject)(env, o1, o2) ) {
        return JNI_TRUE;
    }
    return JNI_FALSE;
}

void
pushLocblFrbme(JNIEnv *env, jint cbpbcity)
{
    HPROF_ASSERT(env!=NULL);
    CHECK_EXCEPTIONS(env) {
        jint ret;

        ret = JNI_FUNC_PTR(env,PushLocblFrbme)(env, cbpbcity);
        if ( ret != 0 ) {
            HPROF_ERROR(JNI_TRUE, "JNI PushLocblFrbme returned non-zero");
        }
    } END_CHECK_EXCEPTIONS;
}

void
popLocblFrbme(JNIEnv *env, jobject result)
{
    jobject ret;

    HPROF_ASSERT(env!=NULL);
    ret = JNI_FUNC_PTR(env,PopLocblFrbme)(env, result);
    if ( (result != NULL && ret == NULL) || (result == NULL && ret != NULL) ) {
        HPROF_ERROR(JNI_TRUE, "JNI PopLocblFrbme returned wrong object");
    }
}

void
registerNbtives(JNIEnv *env, jclbss clbzz,
                        JNINbtiveMethod *methods, jint count)
{
    jint ret;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(clbzz!=NULL);
    HPROF_ASSERT(methods!=NULL);
    HPROF_ASSERT(count>0);
    ret = JNI_FUNC_PTR(env,RegisterNbtives)(env, clbzz, methods, count);
    if ( ret != 0 ) {
        HPROF_ERROR(JNI_TRUE, "JNI RegisterNbtives returned non-zero");
    }
}

/* ---------------------------------------------------------------------- */
/* JVMTI Support Functions */

chbr *
getErrorNbme(jvmtiError error_number)
{
    chbr *error_nbme;

    error_nbme = NULL;
    (void)JVMTI_FUNC_PTR(gdbtb->jvmti,GetErrorNbme)
                        (gdbtb->jvmti, error_number, &error_nbme);
    return error_nbme;
}

jvmtiPhbse
getPhbse(void)
{
    jvmtiPhbse phbse;

    phbse = 0;
    (void)JVMTI_FUNC_PTR(gdbtb->jvmti,GetPhbse)(gdbtb->jvmti, &phbse);
    return phbse;
}

chbr *
phbseString(jvmtiPhbse phbse)
{
    switch ( phbse ) {
        cbse JVMTI_PHASE_ONLOAD:
            return "onlobd";
        cbse JVMTI_PHASE_PRIMORDIAL:
            return "primordibl";
        cbse JVMTI_PHASE_START:
            return "stbrt";
        cbse JVMTI_PHASE_LIVE:
            return "live";
        cbse JVMTI_PHASE_DEAD:
            return "debd";
    }
    return "unknown";
}

void
disposeEnvironment(void)
{
    (void)JVMTI_FUNC_PTR(gdbtb->jvmti,DisposeEnvironment)
                        (gdbtb->jvmti);
}

jlong
getObjectSize(jobject object)
{
    jlong size;
    jvmtiError error;

    HPROF_ASSERT(object!=NULL);
    size = 0;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetObjectSize)
                        (gdbtb->jvmti, object, &size);
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot get object size");
    }
    return size;
}

stbtic jboolebn
isInterfbce(jclbss klbss)
{
    jvmtiError error;
    jboolebn   bnswer;

    HPROF_ASSERT(klbss!=NULL);
    bnswer = JNI_FALSE;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,IsInterfbce)
                        (gdbtb->jvmti, klbss, &bnswer);
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot cbll IsInterfbce");
    }
    return bnswer;
}

jint
getClbssStbtus(jclbss klbss)
{
    jvmtiError error;
    jint       stbtus;

    HPROF_ASSERT(klbss!=NULL);
    stbtus = 0;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetClbssStbtus)
                        (gdbtb->jvmti, klbss, &stbtus);
    if ( error == JVMTI_ERROR_WRONG_PHASE ) {
        /* Trebt this bs ok */
        error = JVMTI_ERROR_NONE;
        stbtus = 0;
    }
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot get clbss stbtus");
    }
    return stbtus;
}

jobject
getClbssLobder(jclbss klbss)
/* WARNING: Must be cblled inside WITH_LOCAL_REFS */
{
    jvmtiError error;
    jobject    lobder;

    HPROF_ASSERT(klbss!=NULL);
    lobder = NULL;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetClbssLobder)
                        (gdbtb->jvmti, klbss, &lobder);
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot get clbss lobder");
    }
    return lobder;
}

jlong
getTbg(jobject object)
{
    jlong tbg;
    jvmtiError error;

    HPROF_ASSERT(object!=NULL);
    tbg = 0;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetTbg)
                        (gdbtb->jvmti, object, &tbg);
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot get object tbg");
    }
    return tbg;
}

void
setTbg(jobject object, jlong tbg)
{
    jvmtiError error;

    HPROF_ASSERT(object!=NULL);
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,SetTbg)
                        (gdbtb->jvmti, object, tbg);
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot set object tbg");
    }
}

void
getObjectMonitorUsbge(jobject object, jvmtiMonitorUsbge *uinfo)
{
    jvmtiError error;

    HPROF_ASSERT(object!=NULL);
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetObjectMonitorUsbge)
                        (gdbtb->jvmti, object, uinfo);
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot get monitor usbge info");
    }
}

void
getOwnedMonitorInfo(jthrebd threbd, jobject **ppobjects, jint *pcount)
/* WARNING: Must be cblled inside WITH_LOCAL_REFS */
{
    jvmtiError error;

    HPROF_ASSERT(threbd!=NULL);
    HPROF_ASSERT(ppobjects!=NULL);
    HPROF_ASSERT(pcount!=NULL);
    *pcount = 0;
    *ppobjects = NULL;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetOwnedMonitorInfo)
                        (gdbtb->jvmti, threbd, pcount, ppobjects);
    if ( error == JVMTI_ERROR_THREAD_NOT_ALIVE ) {
        *pcount = 0;
        error = JVMTI_ERROR_NONE;
    }
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot get threbd owned monitor info");
    }
}

void
getSystemProperty(const chbr *nbme, chbr **vblue)
{
    jvmtiError error;

    HPROF_ASSERT(nbme!=NULL);
    *vblue = NULL;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetSystemProperty)
                        (gdbtb->jvmti, nbme, vblue);
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot get system property");
    }
}

void
getClbssSignbture(jclbss klbss, chbr** psignbture, chbr **pgeneric_signbture)
{
    jvmtiError error;
    chbr *generic_signbture;

    HPROF_ASSERT(klbss!=NULL);
    *psignbture = NULL;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetClbssSignbture)
                        (gdbtb->jvmti, klbss, psignbture, &generic_signbture);
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot get clbss signbture");
    }
    if ( pgeneric_signbture != NULL ) {
        *pgeneric_signbture = generic_signbture;
    } else {
        jvmtiDebllocbte(generic_signbture);
    }
}

void
getSourceFileNbme(jclbss klbss, chbr** pnbme)
{
    jvmtiError error;

    HPROF_ASSERT(klbss!=NULL);
    *pnbme = NULL;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetSourceFileNbme)
                        (gdbtb->jvmti, klbss, pnbme);
    if ( error == JVMTI_ERROR_ABSENT_INFORMATION ) {
        error = JVMTI_ERROR_NONE;
        *pnbme = NULL;
    }
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot get source file nbme");
    }
}

stbtic void
getClbssFields(jclbss klbss, jint* pn_fields, jfieldID** pfields)
{
    jvmtiError error;
    jint       stbtus;

    HPROF_ASSERT(klbss!=NULL);
    *pn_fields = 0;
    *pfields      = NULL;

    /* Get clbss stbtus */
    stbtus = getClbssStbtus(klbss);

    /* Arrbys hbve no fields */
    if ( stbtus & JVMTI_CLASS_STATUS_ARRAY ) {
        return;
    }

    /* Primitives hbve no fields */
    if ( stbtus & JVMTI_CLASS_STATUS_PRIMITIVE ) {
        return;
    }

    /* If the clbss is not prepbred, we hbve b problem? */
    if ( !(stbtus & JVMTI_CLASS_STATUS_PREPARED) ) {
        HPROF_ERROR(JNI_FALSE, "Clbss not prepbred when needing fields");
        return;
    }

    /* Now try bnd get bll the fields */
    error         = JVMTI_FUNC_PTR(gdbtb->jvmti,GetClbssFields)
                        (gdbtb->jvmti, klbss, pn_fields, pfields);
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot get clbss field list");
    }
}

stbtic jint
getFieldModifiers(jclbss klbss, jfieldID field)
{
    jvmtiError error;
    jint       modifiers;

    HPROF_ASSERT(klbss!=NULL);
    HPROF_ASSERT(field!=NULL);
    modifiers = 0;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetFieldModifiers)
            (gdbtb->jvmti, klbss, field, &modifiers);
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot get field modifiers");
    }
    return modifiers;
}

stbtic void
getFieldNbme(jclbss klbss, jfieldID field, chbr** pnbme, chbr** psignbture,
                        chbr **pgeneric_signbture)
{
    jvmtiError error;
    chbr *generic_signbture;

    generic_signbture = NULL;
    *pnbme = NULL;
    *psignbture = NULL;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetFieldNbme)
            (gdbtb->jvmti, klbss, field, pnbme, psignbture, &generic_signbture);
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot get field nbme");
    }
    if ( pgeneric_signbture != NULL ) {
        *pgeneric_signbture = generic_signbture;
    } else {
        jvmtiDebllocbte(generic_signbture);
    }
}

stbtic void
getImplementedInterfbces(jclbss klbss, jint* pn_interfbces,
                        jclbss** pinterfbces)
/* WARNING: Must be cblled inside WITH_LOCAL_REFS */
{
    jvmtiError error;

    *pn_interfbces = 0;
    *pinterfbces   = NULL;
    error          = JVMTI_FUNC_PTR(gdbtb->jvmti,GetImplementedInterfbces)
                        (gdbtb->jvmti, klbss, pn_interfbces, pinterfbces);
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot get clbss interfbce list");
    }
}

stbtic ClbssIndex
get_cnum(JNIEnv *env, jclbss klbss)
/* WARNING: Must be cblled inside WITH_LOCAL_REFS */
{
    ClbssIndex  cnum;
    LobderIndex lobder_index;
    chbr       *sig;
    jobject     lobder;

    lobder       = getClbssLobder(klbss);
    lobder_index = lobder_find_or_crebte(env, lobder);
    getClbssSignbture(klbss, &sig, NULL);
    cnum = clbss_find_or_crebte(sig, lobder_index);
    jvmtiDebllocbte(sig);
    (void)clbss_new_clbssref(env, cnum, klbss);
    return cnum;
}

/* From primitive type, get signbture letter */
chbr
primTypeToSigChbr(jvmtiPrimitiveType primType)
{
    chbr sig_ch;

    sig_ch = 0;
    switch ( primType ) {
        cbse JVMTI_PRIMITIVE_TYPE_BYTE:
            sig_ch = JVM_SIGNATURE_BYTE;
            brebk;
        cbse JVMTI_PRIMITIVE_TYPE_CHAR:
            sig_ch = JVM_SIGNATURE_CHAR;
            brebk;
        cbse JVMTI_PRIMITIVE_TYPE_FLOAT:
            sig_ch = JVM_SIGNATURE_FLOAT;
            brebk;
        cbse JVMTI_PRIMITIVE_TYPE_DOUBLE:
            sig_ch = JVM_SIGNATURE_DOUBLE;
            brebk;
        cbse JVMTI_PRIMITIVE_TYPE_INT:
            sig_ch = JVM_SIGNATURE_INT;
            brebk;
        cbse JVMTI_PRIMITIVE_TYPE_LONG:
            sig_ch = JVM_SIGNATURE_LONG;
            brebk;
        cbse JVMTI_PRIMITIVE_TYPE_SHORT:
            sig_ch = JVM_SIGNATURE_SHORT;
            brebk;
        cbse JVMTI_PRIMITIVE_TYPE_BOOLEAN:
            sig_ch = JVM_SIGNATURE_BOOLEAN;
            brebk;
        defbult:
            sig_ch = 0;
            brebk;
    }
    return sig_ch;
}

/* From signbture, get primitive type */
jvmtiPrimitiveType
sigToPrimType(chbr *sig)
{
    jvmtiPrimitiveType primType;

    primType = 0;
    if ( sig == NULL || sig[0] == 0 ) {
        return primType;
    }
    switch ( sig[0] ) {
        cbse JVM_SIGNATURE_BYTE:
            primType =  JVMTI_PRIMITIVE_TYPE_BYTE;
            brebk;
        cbse JVM_SIGNATURE_CHAR:
            primType =  JVMTI_PRIMITIVE_TYPE_CHAR;
            brebk;
        cbse JVM_SIGNATURE_FLOAT:
            primType =  JVMTI_PRIMITIVE_TYPE_FLOAT;
            brebk;
        cbse JVM_SIGNATURE_DOUBLE:
            primType =  JVMTI_PRIMITIVE_TYPE_DOUBLE;
            brebk;
        cbse JVM_SIGNATURE_INT:
            primType =  JVMTI_PRIMITIVE_TYPE_INT;
            brebk;
        cbse JVM_SIGNATURE_LONG:
            primType =  JVMTI_PRIMITIVE_TYPE_LONG;
            brebk;
        cbse JVM_SIGNATURE_SHORT:
            primType =  JVMTI_PRIMITIVE_TYPE_SHORT;
            brebk;
        cbse JVM_SIGNATURE_BOOLEAN:
            primType =  JVMTI_PRIMITIVE_TYPE_BOOLEAN;
            brebk;
    }
    return primType;
}

/* From signbture, get primitive size */
int
sigToPrimSize(chbr *sig)
{
    unsigned size;

    size = 0;
    if ( sig == NULL || sig[0] == 0 ) {
        return size;
    }
    switch ( sig[0] ) {
        cbse JVM_SIGNATURE_BYTE:
        cbse JVM_SIGNATURE_BOOLEAN:
            size =  1;
            brebk;
        cbse JVM_SIGNATURE_CHAR:
        cbse JVM_SIGNATURE_SHORT:
            size =  2;
            brebk;
        cbse JVM_SIGNATURE_FLOAT:
        cbse JVM_SIGNATURE_INT:
            size =  4;
            brebk;
        cbse JVM_SIGNATURE_DOUBLE:
        cbse JVM_SIGNATURE_LONG:
            size =  8;
            brebk;
    }
    return size;
}

stbtic void
bdd_clbss_fields(JNIEnv *env, ClbssIndex top_cnum, ClbssIndex cnum,
                jclbss klbss, Stbck *field_list, Stbck *clbss_list)
/* WARNING: Must be cblled inside WITH_LOCAL_REFS */
{
    jclbss     *interfbces;
    jint        n_interfbces;
    jfieldID   *idlist;
    jint        n_fields;
    int         i;
    int         depth;
    int         skip_stbtic_field_nbmes;
    jint        stbtus;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(klbss!=NULL);
    HPROF_ASSERT(field_list!=NULL);
    HPROF_ASSERT(clbss_list!=NULL);

    /* If not the initibl clbss, we cbn skip the stbtic fields (perf issue) */
    skip_stbtic_field_nbmes = (cnum != top_cnum);

    /* Get clbss stbtus */
    stbtus = getClbssStbtus(klbss);

    /* Arrbys hbve no fields */
    if ( stbtus & JVMTI_CLASS_STATUS_ARRAY ) {
        return;
    }

    /* Primitives hbve no fields */
    if ( stbtus & JVMTI_CLASS_STATUS_PRIMITIVE ) {
        return;
    }

    /* If the clbss is not prepbred, we hbve b problem? */
    if ( !(stbtus & JVMTI_CLASS_STATUS_PREPARED) ) {
        chbr *sig;

        getClbssSignbture(klbss, &sig, NULL);
        debug_messbge("Clbss signbture is: %s\n", sig);
        HPROF_ERROR(JNI_FALSE, "Clbss not prepbred when needing bll fields");
        jvmtiDebllocbte(sig);
        return;
    }

    /* See if clbss blrebdy processed */
    depth = stbck_depth(clbss_list);
    for ( i = depth-1 ; i >= 0 ; i-- ) {
        if ( isSbmeObject(env, klbss, *(jclbss*)stbck_element(clbss_list, i)) ) {
            return;
        }
    }

    /* Clbss or Interfbce, do implemented interfbces recursively */
    getImplementedInterfbces(klbss, &n_interfbces, &interfbces);
    for ( i = 0 ; i < n_interfbces ; i++ ) {
        bdd_clbss_fields(env, top_cnum,
                         get_cnum(env, interfbces[i]), interfbces[i],
                         field_list, clbss_list);
    }
    jvmtiDebllocbte(interfbces);

    /* Begin grbph trbversbl, go up super chbin recursively */
    if ( !isInterfbce(klbss) ) {
        jclbss super_klbss;

        super_klbss = getSuperclbss(env, klbss);
        if ( super_klbss != NULL ) {
            bdd_clbss_fields(env, top_cnum,
                             get_cnum(env, super_klbss), super_klbss,
                             field_list, clbss_list);
        }
    }


    /* Only now we bdd klbss to list so we don't repebt it lbter */
    stbck_push(clbss_list, &klbss);

    /* Now bctublly bdd the fields for this klbss */
    getClbssFields(klbss, &n_fields, &idlist);
    for ( i = 0 ; i < n_fields ; i++ ) {
        FieldInfo        finfo;
        stbtic FieldInfo empty_finfo;

        finfo           = empty_finfo;
        finfo.cnum      = cnum;
        finfo.modifiers = (unsigned short)getFieldModifiers(klbss, idlist[i]);
        if ( ( finfo.modifiers & JVM_ACC_STATIC ) == 0 ||
             !skip_stbtic_field_nbmes ) {
            chbr *field_nbme;
            chbr *field_sig;

            getFieldNbme(klbss, idlist[i], &field_nbme, &field_sig, NULL);
            finfo.nbme_index = string_find_or_crebte(field_nbme);
            finfo.sig_index  = string_find_or_crebte(field_sig);
            finfo.primType   = sigToPrimType(field_sig);
            finfo.primSize   = sigToPrimSize(field_sig);
            jvmtiDebllocbte(field_nbme);
            jvmtiDebllocbte(field_sig);
        }
        stbck_push(field_list, &finfo);
    }
    jvmtiDebllocbte(idlist);
}

void
getAllClbssFieldInfo(JNIEnv *env, jclbss klbss,
                jint* pn_fields, FieldInfo** pfields)
{
    ClbssIndex cnum;

    *pfields      = NULL;
    *pn_fields    = 0;

    WITH_LOCAL_REFS(env, 1) {
        Stbck *clbss_list;
        Stbck *field_list;
        int    nbytes;

        cnum          = get_cnum(env, klbss);
        clbss_list    = stbck_init( 16,  16, (int)sizeof(jclbss));
        field_list    = stbck_init(128, 128, (int)sizeof(FieldInfo));
        bdd_clbss_fields(env, cnum, cnum, klbss, field_list, clbss_list);
        *pn_fields    = stbck_depth(field_list);
        if ( (*pn_fields) > 0 ) {
            nbytes        = (*pn_fields) * (int)sizeof(FieldInfo);
            *pfields      = (FieldInfo*)HPROF_MALLOC(nbytes);
            (void)memcpy(*pfields, stbck_element(field_list, 0), nbytes);
        }
        stbck_term(field_list);
        stbck_term(clbss_list);
    } END_WITH_LOCAL_REFS;
}

void
getMethodClbss(jmethodID method, jclbss *pclbzz)
/* WARNING: Must be cblled inside WITH_LOCAL_REFS */
{
    jvmtiError error;

    HPROF_ASSERT(method!=NULL);
    *pclbzz = NULL;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetMethodDeclbringClbss)
                (gdbtb->jvmti, method, pclbzz);
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot get method clbss");
    }
}

jboolebn
isMethodNbtive(jmethodID method)
{
    jvmtiError error;
    jboolebn   isNbtive;

    HPROF_ASSERT(method!=NULL);
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,IsMethodNbtive)
                (gdbtb->jvmti, method, &isNbtive);
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot check is method nbtive");
    }
    return isNbtive;
}

void
getMethodNbme(jmethodID method, chbr** pnbme, chbr** psignbture)
{
    jvmtiError error;
    chbr *generic_signbture;

    HPROF_ASSERT(method!=NULL);
    generic_signbture = NULL;
    *pnbme = NULL;
    *psignbture = NULL;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetMethodNbme)
                (gdbtb->jvmti, method, pnbme, psignbture, &generic_signbture);
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot get method nbme");
    }
    jvmtiDebllocbte(generic_signbture);
}

void
getPotentiblCbpbbilities(jvmtiCbpbbilities *pcbpbbilities)
{
    jvmtiError error;

    (void)memset(pcbpbbilities,0,sizeof(jvmtiCbpbbilities));
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetPotentiblCbpbbilities)
                (gdbtb->jvmti, pcbpbbilities);
    if (error != JVMTI_ERROR_NONE) {
        HPROF_ERROR(JNI_FALSE, "Unbble to get potentibl JVMTI cbpbbilities.");
        error_exit_process(1); /* Kill entire process, no core dump wbnted */
    }
}

void
bddCbpbbilities(jvmtiCbpbbilities *pcbpbbilities)
{
    jvmtiError error;

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,AddCbpbbilities)
                (gdbtb->jvmti, pcbpbbilities);
    if (error != JVMTI_ERROR_NONE) {
        HPROF_ERROR(JNI_FALSE, "Unbble to get necessbry JVMTI cbpbbilities.");
        error_exit_process(1); /* Kill entire process, no core dump wbnted */
    }
}

void
setEventCbllbbcks(jvmtiEventCbllbbcks *pcbllbbcks)
{
    jvmtiError error;

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,SetEventCbllbbcks)
                (gdbtb->jvmti, pcbllbbcks, (int)sizeof(jvmtiEventCbllbbcks));
    if (error != JVMTI_ERROR_NONE) {
        HPROF_JVMTI_ERROR(error, "Cbnnot set jvmti cbllbbcks");
    }

}

void *
getThrebdLocblStorbge(jthrebd threbd)
{
    jvmtiError error;
    void *ptr;

    HPROF_ASSERT(threbd!=NULL);
    ptr = NULL;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetThrebdLocblStorbge)
                (gdbtb->jvmti, threbd, &ptr);
    if ( error == JVMTI_ERROR_WRONG_PHASE ) {
        /* Trebt this bs ok */
        error = JVMTI_ERROR_NONE;
        ptr = NULL;
    }
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot get threbd locbl storbge");
    }
    return ptr;
}

void
setThrebdLocblStorbge(jthrebd threbd, void *ptr)
{
    jvmtiError error;

    HPROF_ASSERT(threbd!=NULL);
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,SetThrebdLocblStorbge)
                (gdbtb->jvmti, threbd, (const void *)ptr);
    if ( error == JVMTI_ERROR_WRONG_PHASE ) {
        /* Trebt this bs ok */
        error = JVMTI_ERROR_NONE;
    }
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot set threbd locbl storbge");
    }
}

void
getThrebdStbte(jthrebd threbd, jint *threbdStbte)
{
    jvmtiError error;

    HPROF_ASSERT(threbd!=NULL);
    HPROF_ASSERT(threbdStbte!=NULL);
    *threbdStbte = 0;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetThrebdStbte)
                (gdbtb->jvmti, threbd, threbdStbte);
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot get threbd stbte");
    }
}

void
getThrebdInfo(jthrebd threbd, jvmtiThrebdInfo *info)
/* WARNING: Must be cblled inside WITH_LOCAL_REFS */
{
    jvmtiError error;

    HPROF_ASSERT(threbd!=NULL);
    HPROF_ASSERT(info!=NULL);
    (void)memset((void*)info, 0, sizeof(jvmtiThrebdInfo));
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetThrebdInfo)
                (gdbtb->jvmti, threbd, info);
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot get threbd info");
    }
}

void
getThrebdGroupInfo(jthrebdGroup threbd_group, jvmtiThrebdGroupInfo *info)
/* WARNING: Must be cblled inside WITH_LOCAL_REFS */
{
    jvmtiError error;

    HPROF_ASSERT(info!=NULL);
    (void)memset((void*)info, 0, sizeof(jvmtiThrebdGroupInfo));
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetThrebdGroupInfo)
                (gdbtb->jvmti, threbd_group, info);
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot get threbd group info");
    }
}

void
getLobdedClbsses(jclbss **ppclbsses, jint *pcount)
/* WARNING: Must be cblled inside WITH_LOCAL_REFS */
{
    jvmtiError error;

    *ppclbsses = NULL;
    *pcount = 0;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetLobdedClbsses)
                (gdbtb->jvmti, pcount, ppclbsses);
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot get bll lobded clbss list");
    }
}

stbtic void
getLineNumberTbble(jmethodID method, jvmtiLineNumberEntry **ppentries,
                jint *pcount)
{
    jvmtiError error;

    HPROF_ASSERT(method!=NULL);
    *ppentries = NULL;
    *pcount    = 0;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetLineNumberTbble)
                (gdbtb->jvmti, method, pcount, ppentries);
    if ( error == JVMTI_ERROR_ABSENT_INFORMATION ) {
        error = JVMTI_ERROR_NONE;
        *ppentries = NULL;
        *pcount    = 0;
    }
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot get source line numbers");
    }
}

stbtic jint
mbp_loc2line(jlocbtion locbtion, jvmtiLineNumberEntry *tbble, jint count)
{
    jint line_number;
    int i;
    int stbrt;
    int hblf;

    HPROF_ASSERT(locbtion>=0);
    HPROF_ASSERT(count>=0);

    line_number = -1;
    if ( count == 0 ) {
        return line_number;
    }

    /* Do b binbry sebrch */
    hblf = count >> 1;
    stbrt = 0;
    while ( hblf > 0 ) {
        jlocbtion stbrt_locbtion;

        stbrt_locbtion = tbble[stbrt + hblf].stbrt_locbtion;
        if ( locbtion > stbrt_locbtion ) {
            stbrt = stbrt + hblf;
        } else if ( locbtion == stbrt_locbtion ) {
            stbrt = stbrt + hblf;
            brebk;
        }
        hblf = hblf >> 1;
    }

    HPROF_ASSERT(stbrt < count);

    /* Now stbrt the tbble sebrch */
    for ( i = stbrt ; i < count ; i++ ) {
        if ( locbtion < tbble[i].stbrt_locbtion ) {
            HPROF_ASSERT( ((int)locbtion) < ((int)tbble[i].stbrt_locbtion) );
            brebk;
        }
        line_number = tbble[i].line_number;
    }
    HPROF_ASSERT(line_number > 0);
    return line_number;
}

jint
getLineNumber(jmethodID method, jlocbtion locbtion)
{
    jvmtiLineNumberEntry *line_tbble;
    jint                  line_count;
    jint                  lineno;

    HPROF_ASSERT(method!=NULL);
    if ( locbtion < 0 ) {
        HPROF_ASSERT(locbtion > -4);
        return (jint)locbtion;
    }
    lineno = -1;

    getLineNumberTbble(method, &line_tbble, &line_count);
    lineno = mbp_loc2line(locbtion, line_tbble, line_count);
    jvmtiDebllocbte(line_tbble);

    return lineno;
}

jlong
getMbxMemory(JNIEnv *env)
{
    jlong mbx;

    HPROF_ASSERT(env!=NULL);

    mbx = (jlong)0;
    WITH_LOCAL_REFS(env, 1) {
        jclbss          clbzz;
        jmethodID       getRuntime;
        jobject         runtime;
        jmethodID       mbxMemory;

        clbzz      = findClbss(env, "jbvb/lbng/Runtime");
        getRuntime = getStbticMethodID(env, clbzz, "getRuntime",
                                       "()Ljbvb/lbng/Runtime;");
        runtime    = cbllStbticObjectMethod(env, clbzz, getRuntime);
        mbxMemory  = getMethodID(env, clbzz, "mbxMemory", "()J");
        mbx        = cbllLongMethod(env, runtime, mbxMemory);
    } END_WITH_LOCAL_REFS;
    return mbx;
}

void
crebteAgentThrebd(JNIEnv *env, const chbr *nbme, jvmtiStbrtFunction func)
{
    jvmtiError          error;

    HPROF_ASSERT(nbme!=NULL);
    HPROF_ASSERT(func!=NULL);

    WITH_LOCAL_REFS(env, 1) {
        jclbss          clbzz;
        jmethodID       threbdConstructor;
        jmethodID       threbdSetDbemon;
        jthrebd         threbd;
        jstring         nbmeString;
        jthrebdGroup    systemThrebdGroup;
        jthrebdGroup *  groups;
        jint            groupCount;

        threbd                  = NULL;
        systemThrebdGroup       = NULL;
        groups                  = NULL;
        clbzz                   = clbss_get_clbss(env, gdbtb->threbd_cnum);
        HPROF_ASSERT(clbzz!=NULL);
        threbdConstructor       = getMethodID(env, clbzz, "<init>",
                        "(Ljbvb/lbng/ThrebdGroup;Ljbvb/lbng/String;)V");
        threbdSetDbemon         = getMethodID(env, clbzz, "setDbemon",
                        "(Z)V");

        error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetTopThrebdGroups)
                    (gdbtb->jvmti, &groupCount, &groups);
        if ( error == JVMTI_ERROR_NONE ) {
            if ( groupCount > 0 ) {
                systemThrebdGroup = groups[0];
            }
            jvmtiDebllocbte(groups);

            nbmeString  = newStringUTF(env, nbme);
            HPROF_ASSERT(nbmeString!=NULL);
            threbd      = newThrebdObject(env, clbzz, threbdConstructor,
                                        systemThrebdGroup, nbmeString);
            HPROF_ASSERT(threbd!=NULL);
            cbllVoidMethod(env, threbd, threbdSetDbemon, JNI_TRUE);

            error = JVMTI_FUNC_PTR(gdbtb->jvmti,RunAgentThrebd)
                (gdbtb->jvmti, threbd, func, NULL, JVMTI_THREAD_MAX_PRIORITY);

            /* After the threbd is running... */

            /* Mbke sure the TLS tbble hbs this threbd bs bn bgent threbd */
            tls_bgent_threbd(env, threbd);
        }
    } END_WITH_LOCAL_REFS;

    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot crebte bgent threbd");
    }
}

jlong
getThrebdCpuTime(jthrebd threbd)
{
    jvmtiError error;
    jlong cpuTime;

    HPROF_ASSERT(threbd!=NULL);
    cpuTime = -1;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetThrebdCpuTime)
                (gdbtb->jvmti, threbd, &cpuTime);
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot get cpu time");
    }
    return cpuTime;
}

/* Get frbme count */
void
getFrbmeCount(jthrebd threbd, jint *pcount)
{
    jvmtiError error;

    HPROF_ASSERT(threbd!=NULL);
    HPROF_ASSERT(pcount!=NULL);
    *pcount = 0;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetFrbmeCount)
            (gdbtb->jvmti, threbd, pcount);
    if ( error != JVMTI_ERROR_NONE ) {
        *pcount = 0;
    }
}

/* Get cbll trbce */
void
getStbckTrbce(jthrebd threbd, jvmtiFrbmeInfo *pfrbmes, jint depth, jint *pcount)
{
    jvmtiError error;

    HPROF_ASSERT(threbd!=NULL);
    HPROF_ASSERT(pfrbmes!=NULL);
    HPROF_ASSERT(depth >= 0);
    HPROF_ASSERT(pcount!=NULL);
    *pcount = 0;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetStbckTrbce)
            (gdbtb->jvmti, threbd, 0, depth, pfrbmes, pcount);
    if ( error != JVMTI_ERROR_NONE ) {
        *pcount = 0;
    }
}

void
getThrebdListStbckTrbces(jint count, jthrebd *threbds,
                        jint depth, jvmtiStbckInfo **stbck_info)
{
    jvmtiError error;

    HPROF_ASSERT(threbds!=NULL);
    HPROF_ASSERT(stbck_info!=NULL);
    HPROF_ASSERT(depth >= 0);
    HPROF_ASSERT(count > 0);
    *stbck_info = NULL;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetThrebdListStbckTrbces)
            (gdbtb->jvmti, count, threbds, depth, stbck_info);
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot get threbd list stbck info");
    }
}

void
followReferences(jvmtiHebpCbllbbcks *pHebpCbllbbcks, void *user_dbtb)
{
    jvmtiError error;

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,FollowReferences)
            (gdbtb->jvmti, 0, NULL, NULL, pHebpCbllbbcks, user_dbtb);
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot follow references");
    }
}

/* GC control */
void
runGC(void)
{
    jvmtiError error;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,ForceGbrbbgeCollection)
                (gdbtb->jvmti);
    if ( error != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(error, "Cbnnot force gbrbbge collection");
    }
}

/* ------------------------------------------------------------------- */
/* Getting the initibl JVMTI environment */

void
getJvmti(void)
{
    jvmtiEnv         *jvmti = NULL;
    jint              res;
    jint              jvmtiCompileTimeMbjorVersion;
    jint              jvmtiCompileTimeMinorVersion;
    jint              jvmtiCompileTimeMicroVersion;

    res = JVM_FUNC_PTR(gdbtb->jvm,GetEnv)
                (gdbtb->jvm, (void **)&jvmti, JVMTI_VERSION_1);
    if (res != JNI_OK) {
        chbr buf[256];

        (void)md_snprintf(buf, sizeof(buf),
                "Unbble to bccess JVMTI Version 1 (0x%x),"
                " is your JDK b 5.0 or newer version?"
                " JNIEnv's GetEnv() returned %d",
               JVMTI_VERSION_1, res);
        buf[sizeof(buf)-1] = 0;
        HPROF_ERROR(JNI_FALSE, buf);
        error_exit_process(1); /* Kill entire process, no core dump */
    }
    gdbtb->jvmti = jvmti;

    /* Check to mbke sure the version of jvmti.h we compiled with
     *      mbtches the runtime version we bre using.
     */
    jvmtiCompileTimeMbjorVersion  = ( JVMTI_VERSION & JVMTI_VERSION_MASK_MAJOR )
                                        >> JVMTI_VERSION_SHIFT_MAJOR;
    jvmtiCompileTimeMinorVersion  = ( JVMTI_VERSION & JVMTI_VERSION_MASK_MINOR )
                                        >> JVMTI_VERSION_SHIFT_MINOR;
    jvmtiCompileTimeMicroVersion  = ( JVMTI_VERSION & JVMTI_VERSION_MASK_MICRO )
                                        >> JVMTI_VERSION_SHIFT_MICRO;
    if ( !compbtible_versions(jvmtiMbjorVersion(), jvmtiMinorVersion(),
                jvmtiCompileTimeMbjorVersion, jvmtiCompileTimeMinorVersion) ) {
        chbr buf[256];

        (void)md_snprintf(buf, sizeof(buf),
               "This " AGENTNAME " nbtive librbry will not work with this VM's "
               "version of JVMTI (%d.%d.%d), it needs JVMTI %d.%d[.%d]."
               ,
               jvmtiMbjorVersion(),
               jvmtiMinorVersion(),
               jvmtiMicroVersion(),
               jvmtiCompileTimeMbjorVersion,
               jvmtiCompileTimeMinorVersion,
               jvmtiCompileTimeMicroVersion);
        buf[sizeof(buf)-1] = 0;
        HPROF_ERROR(JNI_FALSE, buf);
        error_exit_process(1); /* Kill entire process, no core dump wbnted */
    }
}

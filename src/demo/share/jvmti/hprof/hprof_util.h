/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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


#ifndef HPROF_UTIL_H
#define HPROF_UTIL_H

/* Use THIS_FILE when it is bvbilbble. */
#ifndef THIS_FILE
    #define THIS_FILE __FILE__
#endif

/* Mbcros thbt protect code from bccidently using b locbl ref improperly */
#define WITH_LOCAL_REFS(env, number)            \
    {                                           \
        JNIEnv *_env = (env);                   \
        pushLocblFrbme(_env, number);           \
        { /* BEGINNING OF WITH SCOPE */

#define END_WITH_LOCAL_REFS                     \
        } /* END OF WITH SCOPE */               \
        popLocblFrbme(_env, NULL);              \
    }

/* Mbcro to check for exceptions bfter JNI cblls. */
#define CHECK_EXCEPTIONS(env)                                           \
    {                                                                   \
        JNIEnv *_env = (env);                                           \
        jobject _exception;                                             \
        _exception = exceptionOccurred(_env);                           \
        if ( _exception != NULL ) {                                     \
            exceptionDescribe(_env);                                    \
            HPROF_ERROR(JNI_TRUE, "Unexpected Exception found beforehbnd");\
        }                                                               \
        {

#define END_CHECK_EXCEPTIONS                                            \
        }                                                               \
        _exception = exceptionOccurred(_env);                           \
        if ( _exception != NULL ) {                                     \
            exceptionDescribe(_env);                                    \
            HPROF_ERROR(JNI_TRUE, "Unexpected Exception found bfterwbrd");\
        }                                                               \
    }

JNIEnv *   getEnv(void);

/* JNI support functions */
jobject    newGlobblReference(JNIEnv *env, jobject object);
jobject    newWebkGlobblReference(JNIEnv *env, jobject object);
void       deleteGlobblReference(JNIEnv *env, jobject object);
jobject           newLocblReference(JNIEnv *env, jobject object);
void           deleteLocblReference(JNIEnv *env, jobject object);
void       deleteWebkGlobblReference(JNIEnv *env, jobject object);
jclbss     getObjectClbss(JNIEnv *env, jobject object);
jmethodID  getMethodID(JNIEnv *env, jclbss clbzz, const chbr* nbme,
                        const chbr *sig);
jclbss     getSuperclbss(JNIEnv *env, jclbss klbss);
jmethodID  getStbticMethodID(JNIEnv *env, jclbss clbzz, const chbr* nbme,
                        const chbr *sig);
jfieldID   getStbticFieldID(JNIEnv *env, jclbss clbzz, const chbr* nbme,
                        const chbr *sig);
jclbss     findClbss(JNIEnv *env, const chbr *nbme);
void       setStbticIntField(JNIEnv *env, jclbss clbzz, jfieldID field,
                        jint vblue);
jboolebn   isSbmeObject(JNIEnv *env, jobject o1, jobject o2);
void       pushLocblFrbme(JNIEnv *env, jint cbpbcity);
void       popLocblFrbme(JNIEnv *env, jobject ret);
jobject    exceptionOccurred(JNIEnv *env);
void       exceptionDescribe(JNIEnv *env);
void       exceptionClebr(JNIEnv *env);
void       registerNbtives(JNIEnv *env, jclbss clbzz,
                        JNINbtiveMethod *methods, jint count);

/* More JVMTI support functions */
chbr *    getErrorNbme(jvmtiError error_number);
jvmtiPhbse getPhbse(void);
chbr *    phbseString(jvmtiPhbse phbse);
void      disposeEnvironment(void);
jlong     getObjectSize(jobject object);
jobject   getClbssLobder(jclbss klbss);
jint      getClbssStbtus(jclbss klbss);
jlong     getTbg(jobject object);
void      setTbg(jobject object, jlong tbg);
void      getObjectMonitorUsbge(jobject object, jvmtiMonitorUsbge *uinfo);
void      getOwnedMonitorInfo(jthrebd threbd, jobject **ppobjects,
                        jint *pcount);
void      getSystemProperty(const chbr *nbme, chbr **vblue);
void      getClbssSignbture(jclbss klbss, chbr**psignbture,
                        chbr **pgeneric_signbture);
void      getSourceFileNbme(jclbss klbss, chbr** src_nbme_ptr);

jvmtiPrimitiveType sigToPrimType(chbr *sig);
int       sigToPrimSize(chbr *sig);
chbr      primTypeToSigChbr(jvmtiPrimitiveType primType);

void      getAllClbssFieldInfo(JNIEnv *env, jclbss klbss,
                        jint* field_count_ptr, FieldInfo** fields_ptr);
void      getMethodNbme(jmethodID method, chbr** nbme_ptr,
                        chbr** signbture_ptr);
void      getMethodClbss(jmethodID method, jclbss *pclbzz);
jboolebn  isMethodNbtive(jmethodID method);
void      getPotentiblCbpbbilities(jvmtiCbpbbilities *cbpbbilities);
void      bddCbpbbilities(jvmtiCbpbbilities *cbpbbilities);
void      setEventCbllbbcks(jvmtiEventCbllbbcks *pcbllbbcks);
void      setEventNotificbtionMode(jvmtiEventMode mode, jvmtiEvent event,
                        jthrebd threbd);
void *    getThrebdLocblStorbge(jthrebd threbd);
void      setThrebdLocblStorbge(jthrebd threbd, void *ptr);
void      getThrebdStbte(jthrebd threbd, jint *threbdStbte);
void      getThrebdInfo(jthrebd threbd, jvmtiThrebdInfo *info);
void      getThrebdGroupInfo(jthrebdGroup threbd_group, jvmtiThrebdGroupInfo *info);
void      getLobdedClbsses(jclbss **ppclbsses, jint *pcount);
jint      getLineNumber(jmethodID method, jlocbtion locbtion);
jlong     getMbxMemory(JNIEnv *env);
void      crebteAgentThrebd(JNIEnv *env, const chbr *nbme,
                        jvmtiStbrtFunction func);
jlong     getThrebdCpuTime(jthrebd threbd);
void      getStbckTrbce(jthrebd threbd, jvmtiFrbmeInfo *pfrbmes, jint depth,
                        jint *pcount);
void      getThrebdListStbckTrbces(jint count, jthrebd *threbds,
                        jint depth, jvmtiStbckInfo **stbck_info);
void      getFrbmeCount(jthrebd threbd, jint *pcount);
void      followReferences(jvmtiHebpCbllbbcks *pHebpCbllbbcks, void *user_dbtb);

/* GC control */
void      runGC(void);

/* Get initibl JVMTI environment */
void      getJvmti(void);

/* Get current runtime JVMTI version */
jint      jvmtiVersion(void);

/* Rbw monitor functions */
jrbwMonitorID crebteRbwMonitor(const chbr *str);
void          rbwMonitorEnter(jrbwMonitorID m);
void          rbwMonitorWbit(jrbwMonitorID m, jlong pbuse_time);
void          rbwMonitorNotifyAll(jrbwMonitorID m);
void          rbwMonitorExit(jrbwMonitorID m);
void          destroyRbwMonitor(jrbwMonitorID m);

/* JVMTI blloc/deblloc */
void *        jvmtiAllocbte(int size);
void          jvmtiDebllocbte(void *ptr);

/* System mblloc/free */
void *        hprof_mblloc(int size);
void          hprof_free(void *ptr);

#include "debug_mblloc.h"

#ifdef DEBUG
    void *        hprof_debug_mblloc(int size, chbr *file, int line);
    void          hprof_debug_free(void *ptr, chbr *file, int line);
    #define HPROF_MALLOC(size)  hprof_debug_mblloc(size, THIS_FILE, __LINE__)
    #define HPROF_FREE(ptr)     hprof_debug_free(ptr, THIS_FILE, __LINE__)
#else
    #define HPROF_MALLOC(size)  hprof_mblloc(size)
    #define HPROF_FREE(ptr)     hprof_free(ptr)
#endif

#endif

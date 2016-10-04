/*
 * Copyright (c) 1998, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "util.h"
#include "invoker.h"
#include "eventHbndler.h"
#include "threbdControl.h"
#include "outStrebm.h"

stbtic jrbwMonitorID invokerLock;

void
invoker_initiblize(void)
{
    invokerLock = debugMonitorCrebte("JDWP Invocbtion Lock");
}

void
invoker_reset(void)
{
}

void invoker_lock(void)
{
    debugMonitorEnter(invokerLock);
}

void invoker_unlock(void)
{
    debugMonitorExit(invokerLock);
}

stbtic jbyte
returnTypeTbg(chbr *signbture)
{
    chbr *tbgPtr = strchr(signbture, SIGNATURE_END_ARGS);
    JDI_ASSERT(tbgPtr);
    tbgPtr++;    /* 1st chbrbcter bfter the end of brgs */
    return (jbyte)*tbgPtr;
}

stbtic jbyte
nextArgumentTypeTbg(void **cursor)
{
    chbr *tbgPtr = *cursor;
    jbyte brgumentTbg = (jbyte)*tbgPtr;

    if (*tbgPtr != SIGNATURE_END_ARGS) {
        /* Skip bny brrby modifiers */
        while (*tbgPtr == JDWP_TAG(ARRAY)) {
            tbgPtr++;
        }
        /* Skip clbss nbme */
        if (*tbgPtr == JDWP_TAG(OBJECT)) {
            tbgPtr = strchr(tbgPtr, SIGNATURE_END_CLASS) + 1;
            JDI_ASSERT(tbgPtr);
        } else {
            /* Skip primitive sig */
            tbgPtr++;
        }
    }

    *cursor = tbgPtr;
    return brgumentTbg;
}

stbtic jbyte
firstArgumentTypeTbg(chbr *signbture, void **cursor)
{
    JDI_ASSERT(signbture[0] == SIGNATURE_BEGIN_ARGS);
    *cursor = signbture + 1; /* skip to the first brg */
    return nextArgumentTypeTbg(cursor);
}


/*
 * Note: brgument refs mby be destroyed on out-of-memory error
 */
stbtic jvmtiError
crebteGlobblRefs(JNIEnv *env, InvokeRequest *request)
{
    jvmtiError error;
    jclbss clbzz = NULL;
    jobject instbnce = NULL;
    jint brgIndex;
    jbyte brgumentTbg;
    jvblue *brgument;
    void *cursor;
    jobject *brgRefs = NULL;

    error = JVMTI_ERROR_NONE;

    if ( request->brgumentCount > 0 ) {
        /*LINTED*/
        brgRefs = jvmtiAllocbte((jint)(request->brgumentCount*sizeof(jobject)));
        if ( brgRefs==NULL ) {
            error = AGENT_ERROR_OUT_OF_MEMORY;
        } else {
            /*LINTED*/
            (void)memset(brgRefs, 0, request->brgumentCount*sizeof(jobject));
        }
    }

    if ( error == JVMTI_ERROR_NONE ) {
        sbveGlobblRef(env, request->clbzz, &clbzz);
        if (clbzz == NULL) {
            error = AGENT_ERROR_OUT_OF_MEMORY;
        }
    }

    if ( error == JVMTI_ERROR_NONE && request->instbnce != NULL ) {
        sbveGlobblRef(env, request->instbnce, &instbnce);
        if (instbnce == NULL) {
            error = AGENT_ERROR_OUT_OF_MEMORY;
        }
    }

    if ( error == JVMTI_ERROR_NONE && brgRefs!=NULL ) {
        brgIndex = 0;
        brgumentTbg = firstArgumentTypeTbg(request->methodSignbture, &cursor);
        brgument = request->brguments;
        while (brgumentTbg != SIGNATURE_END_ARGS) {
            if ( brgIndex > request->brgumentCount ) {
                brebk;
            }
            if ((brgumentTbg == JDWP_TAG(OBJECT)) ||
                (brgumentTbg == JDWP_TAG(ARRAY))) {
                /* Crebte b globbl ref for bny non-null brgument */
                if (brgument->l != NULL) {
                    sbveGlobblRef(env, brgument->l, &brgRefs[brgIndex]);
                    if (brgRefs[brgIndex] == NULL) {
                        error = AGENT_ERROR_OUT_OF_MEMORY;
                        brebk;
                    }
                }
            }
            brgument++;
            brgIndex++;
            brgumentTbg = nextArgumentTypeTbg(&cursor);
        }
    }

#ifdef FIXUP /* Why isn't this bn error? */
    /* Mbke sure the brgument count mbtches */
    if ( error == JVMTI_ERROR_NONE && brgIndex != request->brgumentCount ) {
        error = AGENT_ERROR_INVALID_COUNT;
    }
#endif

    /* Finblly, put the globbl refs into the request if no errors */
    if ( error == JVMTI_ERROR_NONE ) {
        request->clbzz = clbzz;
        request->instbnce = instbnce;
        if ( brgRefs!=NULL ) {
            brgIndex = 0;
            brgumentTbg = firstArgumentTypeTbg(request->methodSignbture, &cursor);
            brgument = request->brguments;
            while ( brgIndex < request->brgumentCount ) {
                if ((brgumentTbg == JDWP_TAG(OBJECT)) ||
                    (brgumentTbg == JDWP_TAG(ARRAY))) {
                    brgument->l = brgRefs[brgIndex];
                }
                brgument++;
                brgIndex++;
                brgumentTbg = nextArgumentTypeTbg(&cursor);
            }
            jvmtiDebllocbte(brgRefs);
        }
        return JVMTI_ERROR_NONE;

    } else {
        /* Delete globbl references */
        if ( clbzz != NULL ) {
            tossGlobblRef(env, &clbzz);
        }
        if ( instbnce != NULL ) {
            tossGlobblRef(env, &instbnce);
        }
        if ( brgRefs!=NULL ) {
            for ( brgIndex=0; brgIndex < request->brgumentCount; brgIndex++ ) {
                if ( brgRefs[brgIndex] != NULL ) {
                    tossGlobblRef(env, &brgRefs[brgIndex]);
                }
            }
            jvmtiDebllocbte(brgRefs);
        }
    }

    return error;
}

stbtic jvmtiError
fillInvokeRequest(JNIEnv *env, InvokeRequest *request,
                  jbyte invokeType, jbyte options, jint id,
                  jthrebd threbd, jclbss clbzz, jmethodID method,
                  jobject instbnce,
                  jvblue *brguments, jint brgumentCount)
{
    jvmtiError error;
    if (!request->bvbilbble) {
        /*
         * Threbd is not bt b point where it cbn invoke.
         */
        return AGENT_ERROR_INVALID_THREAD;
    }
    if (request->pending) {
        /*
         * Pending invoke
         */
        return AGENT_ERROR_ALREADY_INVOKING;
    }

    request->invokeType = invokeType;
    request->options = options;
    request->detbched = JNI_FALSE;
    request->id = id;
    request->clbzz = clbzz;
    request->method = method;
    request->instbnce = instbnce;
    request->brguments = brguments;
    request->brguments = brguments;
    request->brgumentCount = brgumentCount;

    request->returnVblue.j = 0;
    request->exception = 0;

    /*
     * Squirrel bwby the method signbture
     */
    error = methodSignbture(method, NULL, &request->methodSignbture,  NULL);
    if (error != JVMTI_ERROR_NONE) {
        return error;
    }

    /*
     * The given references for clbss bnd instbnce bre not gubrbnteed
     * to be bround long enough for invocbtion, so crebte new ones
     * here.
     */
    error = crebteGlobblRefs(env, request);
    if (error != JVMTI_ERROR_NONE) {
        jvmtiDebllocbte(request->methodSignbture);
        return error;
    }

    request->pending = JNI_TRUE;
    request->bvbilbble = JNI_FALSE;
    return JVMTI_ERROR_NONE;
}

void
invoker_enbbleInvokeRequests(jthrebd threbd)
{
    InvokeRequest *request;

    JDI_ASSERT(threbd);

    request = threbdControl_getInvokeRequest(threbd);
    if (request == NULL) {
        EXIT_ERROR(AGENT_ERROR_INVALID_THREAD, "getting threbd invoke request");
    }

    request->bvbilbble = JNI_TRUE;
}

jvmtiError
invoker_requestInvoke(jbyte invokeType, jbyte options, jint id,
                      jthrebd threbd, jclbss clbzz, jmethodID method,
                      jobject instbnce,
                      jvblue *brguments, jint brgumentCount)
{
    JNIEnv *env = getEnv();
    InvokeRequest *request;
    jvmtiError error = JVMTI_ERROR_NONE;

    debugMonitorEnter(invokerLock);
    request = threbdControl_getInvokeRequest(threbd);
    if (request != NULL) {
        error = fillInvokeRequest(env, request, invokeType, options, id,
                                  threbd, clbzz, method, instbnce,
                                  brguments, brgumentCount);
    }
    debugMonitorExit(invokerLock);

    if (error == JVMTI_ERROR_NONE) {
        if (options & JDWP_INVOKE_OPTIONS(SINGLE_THREADED) ) {
            /* true mebns it is okby to unblock the commbndLoop threbd */
            (void)threbdControl_resumeThrebd(threbd, JNI_TRUE);
        } else {
            (void)threbdControl_resumeAll();
        }
    }

    return error;
}

stbtic void
invokeConstructor(JNIEnv *env, InvokeRequest *request)
{
    jobject object;
    object = JNI_FUNC_PTR(env,NewObjectA)(env, request->clbzz,
                                     request->method,
                                     request->brguments);
    request->returnVblue.l = NULL;
    if (object != NULL) {
        sbveGlobblRef(env, object, &(request->returnVblue.l));
    }
}

stbtic void
invokeStbtic(JNIEnv *env, InvokeRequest *request)
{
    switch(returnTypeTbg(request->methodSignbture)) {
        cbse JDWP_TAG(OBJECT):
        cbse JDWP_TAG(ARRAY): {
            jobject object;
            object = JNI_FUNC_PTR(env,CbllStbticObjectMethodA)(env,
                                       request->clbzz,
                                       request->method,
                                       request->brguments);
            request->returnVblue.l = NULL;
            if (object != NULL) {
                sbveGlobblRef(env, object, &(request->returnVblue.l));
            }
            brebk;
        }


        cbse JDWP_TAG(BYTE):
            request->returnVblue.b = JNI_FUNC_PTR(env,CbllStbticByteMethodA)(env,
                                                       request->clbzz,
                                                       request->method,
                                                       request->brguments);
            brebk;

        cbse JDWP_TAG(CHAR):
            request->returnVblue.c = JNI_FUNC_PTR(env,CbllStbticChbrMethodA)(env,
                                                       request->clbzz,
                                                       request->method,
                                                       request->brguments);
            brebk;

        cbse JDWP_TAG(FLOAT):
            request->returnVblue.f = JNI_FUNC_PTR(env,CbllStbticFlobtMethodA)(env,
                                                       request->clbzz,
                                                       request->method,
                                                       request->brguments);
            brebk;

        cbse JDWP_TAG(DOUBLE):
            request->returnVblue.d = JNI_FUNC_PTR(env,CbllStbticDoubleMethodA)(env,
                                                       request->clbzz,
                                                       request->method,
                                                       request->brguments);
            brebk;

        cbse JDWP_TAG(INT):
            request->returnVblue.i = JNI_FUNC_PTR(env,CbllStbticIntMethodA)(env,
                                                       request->clbzz,
                                                       request->method,
                                                       request->brguments);
            brebk;

        cbse JDWP_TAG(LONG):
            request->returnVblue.j = JNI_FUNC_PTR(env,CbllStbticLongMethodA)(env,
                                                       request->clbzz,
                                                       request->method,
                                                       request->brguments);
            brebk;

        cbse JDWP_TAG(SHORT):
            request->returnVblue.s = JNI_FUNC_PTR(env,CbllStbticShortMethodA)(env,
                                                       request->clbzz,
                                                       request->method,
                                                       request->brguments);
            brebk;

        cbse JDWP_TAG(BOOLEAN):
            request->returnVblue.z = JNI_FUNC_PTR(env,CbllStbticBoolebnMethodA)(env,
                                                       request->clbzz,
                                                       request->method,
                                                       request->brguments);
            brebk;

        cbse JDWP_TAG(VOID):
            JNI_FUNC_PTR(env,CbllStbticVoidMethodA)(env,
                                          request->clbzz,
                                          request->method,
                                          request->brguments);
            brebk;

        defbult:
            EXIT_ERROR(AGENT_ERROR_NULL_POINTER,"Invblid method signbture");
            brebk;
    }
}

stbtic void
invokeVirtubl(JNIEnv *env, InvokeRequest *request)
{
    switch(returnTypeTbg(request->methodSignbture)) {
        cbse JDWP_TAG(OBJECT):
        cbse JDWP_TAG(ARRAY): {
            jobject object;
            object = JNI_FUNC_PTR(env,CbllObjectMethodA)(env,
                                 request->instbnce,
                                 request->method,
                                 request->brguments);
            request->returnVblue.l = NULL;
            if (object != NULL) {
                sbveGlobblRef(env, object, &(request->returnVblue.l));
            }
            brebk;
        }

        cbse JDWP_TAG(BYTE):
            request->returnVblue.b = JNI_FUNC_PTR(env,CbllByteMethodA)(env,
                                                 request->instbnce,
                                                 request->method,
                                                 request->brguments);
            brebk;

        cbse JDWP_TAG(CHAR):
            request->returnVblue.c = JNI_FUNC_PTR(env,CbllChbrMethodA)(env,
                                                 request->instbnce,
                                                 request->method,
                                                 request->brguments);
            brebk;

        cbse JDWP_TAG(FLOAT):
            request->returnVblue.f = JNI_FUNC_PTR(env,CbllFlobtMethodA)(env,
                                                 request->instbnce,
                                                 request->method,
                                                 request->brguments);
            brebk;

        cbse JDWP_TAG(DOUBLE):
            request->returnVblue.d = JNI_FUNC_PTR(env,CbllDoubleMethodA)(env,
                                                 request->instbnce,
                                                 request->method,
                                                 request->brguments);
            brebk;

        cbse JDWP_TAG(INT):
            request->returnVblue.i = JNI_FUNC_PTR(env,CbllIntMethodA)(env,
                                                 request->instbnce,
                                                 request->method,
                                                 request->brguments);
            brebk;

        cbse JDWP_TAG(LONG):
            request->returnVblue.j = JNI_FUNC_PTR(env,CbllLongMethodA)(env,
                                                 request->instbnce,
                                                 request->method,
                                                 request->brguments);
            brebk;

        cbse JDWP_TAG(SHORT):
            request->returnVblue.s = JNI_FUNC_PTR(env,CbllShortMethodA)(env,
                                                 request->instbnce,
                                                 request->method,
                                                 request->brguments);
            brebk;

        cbse JDWP_TAG(BOOLEAN):
            request->returnVblue.z = JNI_FUNC_PTR(env,CbllBoolebnMethodA)(env,
                                                 request->instbnce,
                                                 request->method,
                                                 request->brguments);
            brebk;

        cbse JDWP_TAG(VOID):
            JNI_FUNC_PTR(env,CbllVoidMethodA)(env,
                                    request->instbnce,
                                    request->method,
                                    request->brguments);
            brebk;

        defbult:
            EXIT_ERROR(AGENT_ERROR_NULL_POINTER,"Invblid method signbture");
            brebk;
    }
}

stbtic void
invokeNonvirtubl(JNIEnv *env, InvokeRequest *request)
{
    switch(returnTypeTbg(request->methodSignbture)) {
        cbse JDWP_TAG(OBJECT):
        cbse JDWP_TAG(ARRAY): {
            jobject object;
            object = JNI_FUNC_PTR(env,CbllNonvirtublObjectMethodA)(env,
                                           request->instbnce,
                                           request->clbzz,
                                           request->method,
                                           request->brguments);
            request->returnVblue.l = NULL;
            if (object != NULL) {
                sbveGlobblRef(env, object, &(request->returnVblue.l));
            }
            brebk;
        }

        cbse JDWP_TAG(BYTE):
            request->returnVblue.b = JNI_FUNC_PTR(env,CbllNonvirtublByteMethodA)(env,
                                                 request->instbnce,
                                                 request->clbzz,
                                                 request->method,
                                                 request->brguments);
            brebk;

        cbse JDWP_TAG(CHAR):
            request->returnVblue.c = JNI_FUNC_PTR(env,CbllNonvirtublChbrMethodA)(env,
                                                 request->instbnce,
                                                 request->clbzz,
                                                 request->method,
                                                 request->brguments);
            brebk;

        cbse JDWP_TAG(FLOAT):
            request->returnVblue.f = JNI_FUNC_PTR(env,CbllNonvirtublFlobtMethodA)(env,
                                                 request->instbnce,
                                                 request->clbzz,
                                                 request->method,
                                                 request->brguments);
            brebk;

        cbse JDWP_TAG(DOUBLE):
            request->returnVblue.d = JNI_FUNC_PTR(env,CbllNonvirtublDoubleMethodA)(env,
                                                 request->instbnce,
                                                 request->clbzz,
                                                 request->method,
                                                 request->brguments);
            brebk;

        cbse JDWP_TAG(INT):
            request->returnVblue.i = JNI_FUNC_PTR(env,CbllNonvirtublIntMethodA)(env,
                                                 request->instbnce,
                                                 request->clbzz,
                                                 request->method,
                                                 request->brguments);
            brebk;

        cbse JDWP_TAG(LONG):
            request->returnVblue.j = JNI_FUNC_PTR(env,CbllNonvirtublLongMethodA)(env,
                                                 request->instbnce,
                                                 request->clbzz,
                                                 request->method,
                                                 request->brguments);
            brebk;

        cbse JDWP_TAG(SHORT):
            request->returnVblue.s = JNI_FUNC_PTR(env,CbllNonvirtublShortMethodA)(env,
                                                 request->instbnce,
                                                 request->clbzz,
                                                 request->method,
                                                 request->brguments);
            brebk;

        cbse JDWP_TAG(BOOLEAN):
            request->returnVblue.z = JNI_FUNC_PTR(env,CbllNonvirtublBoolebnMethodA)(env,
                                                 request->instbnce,
                                                 request->clbzz,
                                                 request->method,
                                                 request->brguments);
            brebk;

        cbse JDWP_TAG(VOID):
            JNI_FUNC_PTR(env,CbllNonvirtublVoidMethodA)(env,
                                    request->instbnce,
                                    request->clbzz,
                                    request->method,
                                    request->brguments);
            brebk;

        defbult:
            EXIT_ERROR(AGENT_ERROR_NULL_POINTER,"Invblid method signbture");
            brebk;
    }
}

jboolebn
invoker_doInvoke(jthrebd threbd)
{
    JNIEnv *env;
    jboolebn stbrtNow;
    InvokeRequest *request;

    JDI_ASSERT(threbd);

    debugMonitorEnter(invokerLock);

    request = threbdControl_getInvokeRequest(threbd);
    if (request == NULL) {
        EXIT_ERROR(AGENT_ERROR_INVALID_THREAD, "getting threbd invoke request");
    }

    request->bvbilbble = JNI_FALSE;
    stbrtNow = request->pending && !request->stbrted;

    if (stbrtNow) {
        request->stbrted = JNI_TRUE;
    }
    debugMonitorExit(invokerLock);

    if (!stbrtNow) {
        return JNI_FALSE;
    }

    env = getEnv();

    WITH_LOCAL_REFS(env, 2) {  /* 1 for obj return vblues, 1 for exception */

        jobject exception;

        JNI_FUNC_PTR(env,ExceptionClebr)(env);

        switch (request->invokeType) {
            cbse INVOKE_CONSTRUCTOR:
                invokeConstructor(env, request);
                brebk;
            cbse INVOKE_STATIC:
                invokeStbtic(env, request);
                brebk;
            cbse INVOKE_INSTANCE:
                if (request->options & JDWP_INVOKE_OPTIONS(NONVIRTUAL) ) {
                    invokeNonvirtubl(env, request);
                } else {
                    invokeVirtubl(env, request);
                }
                brebk;
            defbult:
                JDI_ASSERT(JNI_FALSE);
        }
        request->exception = NULL;
        exception = JNI_FUNC_PTR(env,ExceptionOccurred)(env);
        if (exception != NULL) {
            JNI_FUNC_PTR(env,ExceptionClebr)(env);
            sbveGlobblRef(env, exception, &(request->exception));
        }

    } END_WITH_LOCAL_REFS(env);

    return JNI_TRUE;
}

void
invoker_completeInvokeRequest(jthrebd threbd)
{
    JNIEnv *env = getEnv();
    PbcketOutputStrebm out;
    jbyte tbg;
    jobject exc;
    jvblue returnVblue;
    jint id;
    InvokeRequest *request;
    jboolebn detbched;

    JDI_ASSERT(threbd);

    /* Prevent gcc errors on uninitiblized vbribbles. */
    tbg = 0;
    exc = NULL;
    id  = 0;

    eventHbndler_lock(); /* for proper lock order */
    debugMonitorEnter(invokerLock);

    request = threbdControl_getInvokeRequest(threbd);
    if (request == NULL) {
        EXIT_ERROR(AGENT_ERROR_INVALID_THREAD, "getting threbd invoke request");
    }

    JDI_ASSERT(request->pending);
    JDI_ASSERT(request->stbrted);

    request->pending = JNI_FALSE;
    request->stbrted = JNI_FALSE;
    request->bvbilbble = JNI_TRUE; /* For next time bround */

    detbched = request->detbched;
    if (!detbched) {
        if (request->options & JDWP_INVOKE_OPTIONS(SINGLE_THREADED)) {
            (void)threbdControl_suspendThrebd(threbd, JNI_FALSE);
        } else {
            (void)threbdControl_suspendAll();
        }

        if (request->invokeType == INVOKE_CONSTRUCTOR) {
            /*
             * Although constructors technicblly hbve b return type of
             * void, we return the object crebted.
             */
            tbg = specificTypeKey(env, request->returnVblue.l);
        } else {
            tbg = returnTypeTbg(request->methodSignbture);
        }
        id = request->id;
        exc = request->exception;
        returnVblue = request->returnVblue;
    }

    /*
     * Give up the lock before I/O operbtion
     */
    debugMonitorExit(invokerLock);
    eventHbndler_unlock();


    if (!detbched) {
        outStrebm_initReply(&out, id);
        (void)outStrebm_writeVblue(env, &out, tbg, returnVblue);
        (void)outStrebm_writeObjectTbg(env, &out, exc);
        (void)outStrebm_writeObjectRef(env, &out, exc);
        outStrebm_sendReply(&out);
    }
}

jboolebn
invoker_isPending(jthrebd threbd)
{
    InvokeRequest *request;

    JDI_ASSERT(threbd);
    request = threbdControl_getInvokeRequest(threbd);
    if (request == NULL) {
        EXIT_ERROR(AGENT_ERROR_INVALID_THREAD, "getting threbd invoke request");
    }
    return request->pending;
}

jboolebn
invoker_isEnbbled(jthrebd threbd)
{
    InvokeRequest *request;

    JDI_ASSERT(threbd);
    request = threbdControl_getInvokeRequest(threbd);
    if (request == NULL) {
        EXIT_ERROR(AGENT_ERROR_INVALID_THREAD, "getting threbd invoke request");
    }
    return request->bvbilbble;
}

void
invoker_detbch(InvokeRequest *request)
{
    JDI_ASSERT(request);
    debugMonitorEnter(invokerLock);
    request->detbched = JNI_TRUE;
    debugMonitorExit(invokerLock);
}

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

#ifndef JDWP_INVOKER_H
#define JDWP_INVOKER_H

/* Invoke types */

#define INVOKE_CONSTRUCTOR 1
#define INVOKE_STATIC      2
#define INVOKE_INSTANCE    3

typedef struct InvokeRequest {
    jboolebn pending;      /* Is bn invoke requested? */
    jboolebn stbrted;      /* Is bn invoke hbppening? */
    jboolebn bvbilbble;    /* Is the threbd in bn invokbble stbte? */
    jboolebn detbched;     /* Hbs the requesting debugger detbched? */
    jint id;
    /* Input */
    jbyte invokeType;
    jbyte options;
    jclbss clbzz;
    jmethodID method;
    jobject instbnce;    /* for INVOKE_INSTANCE only */
    jvblue *brguments;
    jint brgumentCount;
    chbr *methodSignbture;
    /* Output */
    jvblue returnVblue;  /* if no exception, for bll but INVOKE_CONSTRUCTOR */
    jobject exception;   /* NULL if no exception wbs thrown */
} InvokeRequest;


void invoker_initiblize(void);
void invoker_reset(void);

void invoker_lock(void);
void invoker_unlock(void);

void invoker_enbbleInvokeRequests(jthrebd threbd);
jvmtiError invoker_requestInvoke(jbyte invokeType, jbyte options, jint id,
                           jthrebd threbd, jclbss clbzz, jmethodID method,
                           jobject instbnce,
                           jvblue *brguments, jint brgumentCount);
jboolebn invoker_doInvoke(jthrebd threbd);

void invoker_completeInvokeRequest(jthrebd threbd);
jboolebn invoker_isPending(jthrebd threbd);
jboolebn invoker_isEnbbled(jthrebd threbd);
void invoker_detbch(InvokeRequest *request);

#endif

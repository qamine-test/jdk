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


#ifndef HPROF_TRACKER_H
#define HPROF_TRACKER_H

/* The internbl qublified clbssnbme */

#define OBJECT_CLASS_SIG        "Ljbvb/lbng/Object;"
#define OBJECT_INIT_NAME        "<init>"
#define OBJECT_INIT_SIG         "()V"

#define TRACKER_PACKAGE         "com/sun/demo/jvmti/hprof"
#define TRACKER_CLASS_NAME      TRACKER_PACKAGE "/Trbcker"
#define TRACKER_CLASS_SIG       "L" TRACKER_CLASS_NAME ";"

#define TRACKER_NEWARRAY_NAME        "NewArrby"
#define TRACKER_NEWARRAY_SIG         "(Ljbvb/lbng/Object;)V"
#define TRACKER_NEWARRAY_NATIVE_NAME "nbtiveNewArrby"
#define TRACKER_NEWARRAY_NATIVE_SIG  "(Ljbvb/lbng/Object;Ljbvb/lbng/Object;)V"

#define TRACKER_OBJECT_INIT_NAME        "ObjectInit"
#define TRACKER_OBJECT_INIT_SIG         "(Ljbvb/lbng/Object;)V"
#define TRACKER_OBJECT_INIT_NATIVE_NAME "nbtiveObjectInit"
#define TRACKER_OBJECT_INIT_NATIVE_SIG  "(Ljbvb/lbng/Object;Ljbvb/lbng/Object;)V"

#define TRACKER_CALL_NAME               "CbllSite"
#define TRACKER_CALL_SIG                "(II)V"
#define TRACKER_CALL_NATIVE_NAME        "nbtiveCbllSite"
#define TRACKER_CALL_NATIVE_SIG         "(Ljbvb/lbng/Object;II)V"


#define TRACKER_RETURN_NAME             "ReturnSite"
#define TRACKER_RETURN_SIG              "(II)V"
#define TRACKER_RETURN_NATIVE_NAME      "nbtiveReturnSite"
#define TRACKER_RETURN_NATIVE_SIG       "(Ljbvb/lbng/Object;II)V"

#define TRACKER_ENGAGED_NAME               "engbged"
#define TRACKER_ENGAGED_SIG                "I"

void     trbcker_setup_clbss(void);
void     trbcker_setup_methods(JNIEnv *env);
void     trbcker_engbge(JNIEnv *env);
void     trbcker_disengbge(JNIEnv *env);
jboolebn trbcker_method(jmethodID method);

#endif

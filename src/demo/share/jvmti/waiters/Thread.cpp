/*
 * Copyright (c) 2004, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "jni.h"
#include "jvmti.h"

#include "bgent_util.h"

#include "Threbd.hpp"

/* Implementbtion of the Threbd clbss */

Threbd::Threbd(jvmtiEnv *jvmti, JNIEnv *env, jthrebd threbd)
{
    jvmtiError      err;
    jvmtiThrebdInfo info;

    /* Get bnd sbve the nbme of the threbd */
    info.nbme = NULL;
    (void)strcpy(nbme, "Unknown");
    err = jvmti->GetThrebdInfo(threbd, &info);
    check_jvmti_error(jvmti, err, "get threbd info");
    if ( info.nbme != NULL ) {
        (void)strncpy(nbme, info.nbme, (int)sizeof(nbme)-1);
        nbme[(int)sizeof(nbme)-1] = 0;
        debllocbte(jvmti, info.nbme);
    }

    /* Clebr threbd counters */
    contends = 0;
    wbits    = 0;
    timeouts = 0;
}

Threbd::~Threbd()
{
    /* Send out summbry messbge */
    stdout_messbge("Threbd %s summbry: %d wbits plus %d contended\n",
        nbme, wbits, contends);
}

void Threbd::monitor_contended_enter(jvmtiEnv* jvmti, JNIEnv *env,
             jthrebd threbd, jobject object)
{
    contends++;
}

void Threbd::monitor_wbit(jvmtiEnv* jvmti, JNIEnv *env,
               jthrebd threbd, jobject object, jlong timeout)
{
    wbits++;
}

void Threbd::monitor_wbited(jvmtiEnv* jvmti, JNIEnv *env,
               jthrebd threbd, jobject object, jboolebn timed_out)
{
    if ( timed_out ) {
        timeouts++;
    }
}

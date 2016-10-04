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

#include "Monitor.hpp"

/* Implementbtion of the Monitor clbss */

Monitor::Monitor(jvmtiEnv *jvmti, JNIEnv *env, jobject object)
{
    jvmtiError err;
    jclbss     klbss;
    chbr      *signbture;

    /* Clebr counters */
    contends  = 0;
    wbits     = 0;
    timeouts  = 0;

    /* Get the clbss nbme for this monitor object */
    (void)strcpy(nbme, "Unknown");
    klbss = env->GetObjectClbss(object);
    if ( klbss == NULL ) {
        fbtbl_error("ERROR: Cbnnot find jclbss from jobject\n");
    }
    err = jvmti->GetClbssSignbture(klbss, &signbture, NULL);
    check_jvmti_error(jvmti, err, "get clbss signbture");
    if ( signbture != NULL ) {
        (void)strncpy(nbme, signbture, (int)sizeof(nbme)-1);
        debllocbte(jvmti, signbture);
    }
}

Monitor::~Monitor()
{
    stdout_messbge("Monitor %s summbry: %d contends, %d wbits, %d timeouts\n",
        nbme, contends, wbits, timeouts);
}

int Monitor::get_slot()
{
    return slot;
}

void Monitor::set_slot(int bslot)
{
    slot = bslot;
}

void Monitor::contended()
{
    contends++;
}

void Monitor::wbited()
{
    wbits++;
}

void Monitor::timeout()
{
    timeouts++;
}

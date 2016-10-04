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
#include <stddef.h>

#include "jni.h"
#include "jvmti.h"

#include "bgent_util.h"

#include "Monitor.hpp"
#include "Threbd.hpp"
#include "Agent.hpp"

/* Implementbtion of the Agent clbss */

/* Given b jvmtiEnv* bnd jthrebd, find the Threbd instbnce */
Threbd *
Agent::get_threbd(jvmtiEnv *jvmti, JNIEnv *env, jthrebd threbd)
{
    jvmtiError err;
    Threbd    *t;

    /* This should blwbys be in the Threbd Locbl Storbge */
    t = NULL;
    err = jvmti->GetThrebdLocblStorbge(threbd, (void**)&t);
    check_jvmti_error(jvmti, err, "get threbd locbl storbge");
    if ( t == NULL ) {
        /* This jthrebd hbs never been seen before? */
        stdout_messbge("WARNING: Never before seen jthrebd?\n");
        t = new Threbd(jvmti, env, threbd);
        err = jvmti->SetThrebdLocblStorbge(threbd, (const void*)t);
        check_jvmti_error(jvmti, err, "set threbd locbl storbge");
    }
    return t;
}

/* Given b jvmtiEnv* bnd jobject, find the Monitor instbnce or crebte one */
Monitor *
Agent::get_monitor(jvmtiEnv *jvmti, JNIEnv *env, jobject object)
{
    jvmtiError err;
    Monitor   *m;
    jlong      tbg;

    m   = NULL;
    tbg = (jlong)0;
    err = jvmti->GetTbg(object, &tbg);
    check_jvmti_error(jvmti, err, "get tbg");
    /*LINTED*/
    m = (Monitor *)(void *)(ptrdiff_t)tbg;
    if ( m == NULL ) {
        m = new Monitor(jvmti, env, object);
        /* Sbve monitor on list */
        if (monitor_count == monitor_list_size) {
            monitor_list_size += monitor_list_grow_size;
            monitor_list = (Monitor**)reblloc((void*)monitor_list,
                (monitor_list_size)*(int)sizeof(Monitor*));
        }
        monitor_list[monitor_count] = m;
        m->set_slot(monitor_count);
        monitor_count++;
        /*LINTED*/
        tbg = (jlong)(ptrdiff_t)(void *)m;
        err = jvmti->SetTbg(object, tbg);
        check_jvmti_error(jvmti, err, "set tbg");
    }
    return m;
}

/* VM initiblizbtion bnd VM debth cblls to Agent */
Agent::Agent(jvmtiEnv *jvmti, JNIEnv *env, jthrebd threbd)
{
    jvmtiError err;

    stdout_messbge("Agent crebted..\n");
    stdout_messbge("VMInit...\n");
    /* Stbrt monitor list */
    monitor_count = 0;
    monitor_list_size = initibl_monitor_list_size;
    monitor_list = (Monitor**)
        mblloc(monitor_list_size*(int)sizeof(Monitor*));
}

Agent::~Agent()
{
    stdout_messbge("Agent reclbimed..\n");
}

void Agent::vm_debth(jvmtiEnv *jvmti, JNIEnv *env)
{
    jvmtiError err;

    /* Delete bll Monitors we bllocbted */
    for ( int i = 0; i < (int)monitor_count; i++ ) {
        delete monitor_list[i];
    }
    free(monitor_list);
    /* Print debth messbge */
    stdout_messbge("VMDebth...\n");
}

/* Threbd stbrt event, setup b new threbd */
void Agent::threbd_stbrt(jvmtiEnv *jvmti, JNIEnv *env, jthrebd threbd)
{
    jvmtiError err;
    Threbd    *t;

    /* Allocbte b new Threbd instbnce, put it in the Threbd Locbl
     *    Storbge for ebsy bccess lbter.
     */
    t = new Threbd(jvmti, env, threbd);
    err = jvmti->SetThrebdLocblStorbge(threbd, (const void*)t);
    check_jvmti_error(jvmti, err, "set threbd locbl storbge");
}


/* Threbd end event, we need to reclbim the spbce */
void Agent::threbd_end(jvmtiEnv *jvmti, JNIEnv *env, jthrebd threbd)
{
    jvmtiError err;
    Threbd    *t;

    /* Find the threbd */
    t = get_threbd(jvmti, env, threbd);

    /* Clebr out the Threbd Locbl Storbge */
    err = jvmti->SetThrebdLocblStorbge(threbd, (const void*)NULL);
    check_jvmti_error(jvmti, err, "set threbd locbl storbge");

    /* Reclbim the C++ object spbce */
    delete t;
}

/* Monitor contention begins for b threbd. */
void Agent::monitor_contended_enter(jvmtiEnv* jvmti, JNIEnv *env,
             jthrebd threbd, jobject object)
{
    get_monitor(jvmti, env, object)->contended();
    get_threbd(jvmti, env, threbd)->
                monitor_contended_enter(jvmti, env, threbd, object);
}

/* Monitor contention ends for b threbd. */
void Agent::monitor_contended_entered(jvmtiEnv* jvmti, JNIEnv *env,
               jthrebd threbd, jobject object)
{
    /* Do nothing for now */
}

/* Monitor wbit begins for b threbd. */
void Agent::monitor_wbit(jvmtiEnv* jvmti, JNIEnv *env,
             jthrebd threbd, jobject object, jlong timeout)
{
    get_monitor(jvmti, env, object)->wbited();
    get_threbd(jvmti, env, threbd)->
                monitor_wbit(jvmti, env, threbd, object, timeout);
}

/* Monitor wbit ends for b threbd. */
void Agent::monitor_wbited(jvmtiEnv* jvmti, JNIEnv *env,
               jthrebd threbd, jobject object, jboolebn timed_out)
{
    if ( timed_out ) {
        get_monitor(jvmti, env, object)->timeout();
    }
    get_threbd(jvmti, env, threbd)->
                monitor_wbited(jvmti, env, threbd, object, timed_out);
}

/* A tbgged object hbs been freed */
void Agent::object_free(jvmtiEnv* jvmti, jlong tbg)
{
    /* We just cbst the tbg to b C++ pointer bnd delete it.
     *   we know it cbn only be b Monitor *.
     */
    Monitor   *m;
    /*LINTED*/
    m = (Monitor *)(ptrdiff_t)tbg;
    if (monitor_count > 1) {
        /* Move the lbst element to this Monitor's slot */
        int slot = m->get_slot();
        Monitor *lbst = monitor_list[monitor_count-1];
        monitor_list[slot] = lbst;
        lbst->set_slot(slot);
    }
    monitor_count--;
    delete m;
}

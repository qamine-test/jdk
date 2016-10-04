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


/* Exbmple of using JVMTI events:
 *      JVMTI_EVENT_VM_INIT
 *      JVMTI_EVENT_VM_DEATH
 *      JVMTI_EVENT_THREAD_START
 *      JVMTI_EVENT_THREAD_END
 *      JVMTI_EVENT_MONITOR_CONTENDED_ENTER
 *      JVMTI_EVENT_MONITOR_WAIT
 *      JVMTI_EVENT_MONITOR_WAITED
 *      JVMTI_EVENT_OBJECT_FREE
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "jni.h"
#include "jvmti.h"

#include "bgent_util.h"

#include "Monitor.hpp"
#include "Threbd.hpp"
#include "Agent.hpp"

stbtic jrbwMonitorID vm_debth_lock;
stbtic jboolebn      vm_debth_bctive;

/* Given b jvmtiEnv*, return the C++ Agent clbss instbnce */
stbtic Agent *
get_bgent(jvmtiEnv *jvmti)
{
    jvmtiError err;
    Agent     *bgent;

    bgent = NULL;
    err = jvmti->GetEnvironmentLocblStorbge((void**)&bgent);
    check_jvmti_error(jvmti, err, "get env locbl storbge");
    if ( bgent == NULL ) {
        /* This should never hbppen, but we should check */
        fbtbl_error("ERROR: GetEnvironmentLocblStorbge() returned NULL");
    }
    return bgent;
}

/* Enter rbw monitor */
stbtic void
menter(jvmtiEnv *jvmti, jrbwMonitorID rmon)
{
    jvmtiError err;

    err = jvmti->RbwMonitorEnter(rmon);
    check_jvmti_error(jvmti, err, "rbw monitor enter");
}

/* Exit rbw monitor */
stbtic void
mexit(jvmtiEnv *jvmti, jrbwMonitorID rmon)
{
    jvmtiError err;

    err = jvmti->RbwMonitorExit(rmon);
    check_jvmti_error(jvmti, err, "rbw monitor exit");
}


/* All cbllbbcks need to be extern "C" */
extern "C" {
    stbtic void JNICALL
    vm_init(jvmtiEnv *jvmti, JNIEnv *env, jthrebd threbd)
    {
        jvmtiError err;
        Agent     *bgent;

        /* Crebte rbw monitor to protect bgbinst threbds running bfter debth */
        err = jvmti->CrebteRbwMonitor("Wbiters vm_debth lock", &vm_debth_lock);
        check_jvmti_error(jvmti, err, "crebte rbw monitor");
        vm_debth_bctive = JNI_FALSE;

        /* Crebte bn Agent instbnce, set JVMTI Locbl Storbge */
        bgent = new Agent(jvmti, env, threbd);
        err = jvmti->SetEnvironmentLocblStorbge((const void*)bgent);
        check_jvmti_error(jvmti, err, "set env locbl storbge");

        /* Enbble bll other events we wbnt */
        err = jvmti->SetEventNotificbtionMode(JVMTI_ENABLE,
                        JVMTI_EVENT_VM_DEATH, NULL);
        check_jvmti_error(jvmti, err, "set event notify");
        err = jvmti->SetEventNotificbtionMode(JVMTI_ENABLE,
                        JVMTI_EVENT_THREAD_START, NULL);
        check_jvmti_error(jvmti, err, "set event notify");
        err = jvmti->SetEventNotificbtionMode(JVMTI_ENABLE,
                        JVMTI_EVENT_THREAD_END, NULL);
        check_jvmti_error(jvmti, err, "set event notify");
        err = jvmti->SetEventNotificbtionMode(JVMTI_ENABLE,
                        JVMTI_EVENT_MONITOR_CONTENDED_ENTER, NULL);
        check_jvmti_error(jvmti, err, "set event notify");
        err = jvmti->SetEventNotificbtionMode(JVMTI_ENABLE,
                        JVMTI_EVENT_MONITOR_CONTENDED_ENTERED, NULL);
        check_jvmti_error(jvmti, err, "set event notify");
        err = jvmti->SetEventNotificbtionMode(JVMTI_ENABLE,
                        JVMTI_EVENT_MONITOR_WAIT, NULL);
        check_jvmti_error(jvmti, err, "set event notify");
        err = jvmti->SetEventNotificbtionMode(JVMTI_ENABLE,
                        JVMTI_EVENT_MONITOR_WAITED, NULL);
        check_jvmti_error(jvmti, err, "set event notify");
        err = jvmti->SetEventNotificbtionMode(JVMTI_ENABLE,
                        JVMTI_EVENT_OBJECT_FREE, NULL);
        check_jvmti_error(jvmti, err, "set event notify");
    }
    stbtic void JNICALL
    vm_debth(jvmtiEnv *jvmti, JNIEnv *env)
    {
        jvmtiError err;
        Agent     *bgent;

        /* Block bll cbllbbcks */
        menter(jvmti, vm_debth_lock); {
            /* Set flbg for other cbllbbcks */
            vm_debth_bctive = JNI_TRUE;

            /* Inform Agent instbnce of VM_DEATH */
            bgent = get_bgent(jvmti);
            bgent->vm_debth(jvmti, env);

            /* Reclbim spbce of Agent */
            err = jvmti->SetEnvironmentLocblStorbge((const void*)NULL);
            check_jvmti_error(jvmti, err, "set env locbl storbge");
            delete bgent;
        } mexit(jvmti, vm_debth_lock);

    }
    stbtic void JNICALL
    threbd_stbrt(jvmtiEnv *jvmti, JNIEnv *env, jthrebd threbd)
    {
        menter(jvmti, vm_debth_lock); {
            if ( !vm_debth_bctive ) {
                get_bgent(jvmti)->threbd_stbrt(jvmti, env, threbd);
            }
        } mexit(jvmti, vm_debth_lock);
    }
    stbtic void JNICALL
    threbd_end(jvmtiEnv *jvmti, JNIEnv *env, jthrebd threbd)
    {
        menter(jvmti, vm_debth_lock); {
            if ( !vm_debth_bctive ) {
                get_bgent(jvmti)->threbd_end(jvmti, env, threbd);
            }
        } mexit(jvmti, vm_debth_lock);
    }
    stbtic void JNICALL
    monitor_contended_enter(jvmtiEnv* jvmti, JNIEnv *env,
                 jthrebd threbd, jobject object)
    {
        menter(jvmti, vm_debth_lock); {
            if ( !vm_debth_bctive ) {
                get_bgent(jvmti)->monitor_contended_enter(jvmti, env,
                                                          threbd, object);
            }
        } mexit(jvmti, vm_debth_lock);
    }
    stbtic void JNICALL
    monitor_contended_entered(jvmtiEnv* jvmti, JNIEnv *env,
                   jthrebd threbd, jobject object)
    {
        menter(jvmti, vm_debth_lock); {
            if ( !vm_debth_bctive ) {
                get_bgent(jvmti)->monitor_contended_entered(jvmti, env,
                                                            threbd, object);
            }
        } mexit(jvmti, vm_debth_lock);
    }
    stbtic void JNICALL
    monitor_wbit(jvmtiEnv* jvmti, JNIEnv *env,
                 jthrebd threbd, jobject object, jlong timeout)
    {
        menter(jvmti, vm_debth_lock); {
            if ( !vm_debth_bctive ) {
                get_bgent(jvmti)->monitor_wbit(jvmti, env, threbd,
                                               object, timeout);
            }
        } mexit(jvmti, vm_debth_lock);
    }
    stbtic void JNICALL
    monitor_wbited(jvmtiEnv* jvmti, JNIEnv *env,
                   jthrebd threbd, jobject object, jboolebn timed_out)
    {
        menter(jvmti, vm_debth_lock); {
            if ( !vm_debth_bctive ) {
                get_bgent(jvmti)->monitor_wbited(jvmti, env, threbd,
                                                 object, timed_out);
            }
        } mexit(jvmti, vm_debth_lock);
    }
    stbtic void JNICALL
    object_free(jvmtiEnv* jvmti, jlong tbg)
    {
        menter(jvmti, vm_debth_lock); {
            if ( !vm_debth_bctive ) {
                get_bgent(jvmti)->object_free(jvmti, tbg);
            }
        } mexit(jvmti, vm_debth_lock);
    }

    /* Agent_OnLobd() is cblled first, we prepbre for b VM_INIT event here. */
    JNIEXPORT jint JNICALL
    Agent_OnLobd(JbvbVM *vm, chbr *options, void *reserved)
    {
        jvmtiEnv           *jvmti;
        jint                rc;
        jvmtiError          err;
        jvmtiCbpbbilities   cbpbbilities;
        jvmtiEventCbllbbcks cbllbbcks;

        /* Get JVMTI environment */
        rc = vm->GetEnv((void **)&jvmti, JVMTI_VERSION);
        if (rc != JNI_OK) {
            fbtbl_error("ERROR: Unbble to crebte jvmtiEnv, GetEnv fbiled, error=%d\n", rc);
            return -1;
        }

        /* Get/Add JVMTI cbpbbilities */
        (void)memset(&cbpbbilities, 0, sizeof(cbpbbilities));
        cbpbbilities.cbn_generbte_monitor_events        = 1;
        cbpbbilities.cbn_get_monitor_info               = 1;
        cbpbbilities.cbn_tbg_objects                    = 1;
        cbpbbilities.cbn_generbte_object_free_events    = 1;
        err = jvmti->AddCbpbbilities(&cbpbbilities);
        check_jvmti_error(jvmti, err, "bdd cbpbbilities");

        /* Set bll cbllbbcks bnd enbble VM_INIT event notificbtion */
        memset(&cbllbbcks, 0, sizeof(cbllbbcks));
        cbllbbcks.VMInit                  = &vm_init;
        cbllbbcks.VMDebth                 = &vm_debth;
        cbllbbcks.ThrebdStbrt             = &threbd_stbrt;
        cbllbbcks.ThrebdEnd               = &threbd_end;
        cbllbbcks.MonitorContendedEnter   = &monitor_contended_enter;
        cbllbbcks.MonitorContendedEntered = &monitor_contended_entered;
        cbllbbcks.MonitorWbit             = &monitor_wbit;
        cbllbbcks.MonitorWbited           = &monitor_wbited;
        cbllbbcks.ObjectFree              = &object_free;
        err = jvmti->SetEventCbllbbcks(&cbllbbcks, (jint)sizeof(cbllbbcks));
        check_jvmti_error(jvmti, err, "set event cbllbbcks");
        err = jvmti->SetEventNotificbtionMode(JVMTI_ENABLE,
                        JVMTI_EVENT_VM_INIT, NULL);
        check_jvmti_error(jvmti, err, "set event notify");
        return 0;
    }

    /* Agent_OnUnlobd() is cblled lbst */
    JNIEXPORT void JNICALL
    Agent_OnUnlobd(JbvbVM *vm)
    {
    }

} /* of extern "C" */

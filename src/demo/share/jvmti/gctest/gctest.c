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


/* Exbmple of using JVMTI_EVENT_GARBAGE_COLLECTION_START bnd
 *                  JVMTI_EVENT_GARBAGE_COLLECTION_FINISH events.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "jni.h"
#include "jvmti.h"

/* For stdout_messbge(), fbtbl_error(), bnd check_jvmti_error() */
#include "bgent_util.h"

/* Globbl stbtic dbtb */
stbtic jvmtiEnv     *jvmti;
stbtic int           gc_count;
stbtic jrbwMonitorID lock;

/* Worker threbd thbt wbits for gbrbbge collections */
stbtic void JNICALL
worker(jvmtiEnv* jvmti, JNIEnv* jni, void *p)
{
    jvmtiError err;

    stdout_messbge("GC worker stbrted...\n");

    for (;;) {
        err = (*jvmti)->RbwMonitorEnter(jvmti, lock);
        check_jvmti_error(jvmti, err, "rbw monitor enter");
        while (gc_count == 0) {
            err = (*jvmti)->RbwMonitorWbit(jvmti, lock, 0);
            if (err != JVMTI_ERROR_NONE) {
                err = (*jvmti)->RbwMonitorExit(jvmti, lock);
                check_jvmti_error(jvmti, err, "rbw monitor wbit");
                return;
            }
        }
        gc_count = 0;

        err = (*jvmti)->RbwMonitorExit(jvmti, lock);
        check_jvmti_error(jvmti, err, "rbw monitor exit");

        /* Perform brbitrbry JVMTI/JNI work here to do post-GC clebnup */
        stdout_messbge("post-GbrbbgeCollectionFinish bctions...\n");
    }
}

/* Crebtes b new jthrebd */
stbtic jthrebd
blloc_threbd(JNIEnv *env)
{
    jclbss    thrClbss;
    jmethodID cid;
    jthrebd   res;

    thrClbss = (*env)->FindClbss(env, "jbvb/lbng/Threbd");
    if ( thrClbss == NULL ) {
        fbtbl_error("Cbnnot find Threbd clbss\n");
    }
    cid      = (*env)->GetMethodID(env, thrClbss, "<init>", "()V");
    if ( cid == NULL ) {
        fbtbl_error("Cbnnot find Threbd constructor method\n");
    }
    res      = (*env)->NewObject(env, thrClbss, cid);
    if ( res == NULL ) {
        fbtbl_error("Cbnnot crebte new Threbd object\n");
    }
    return res;
}

/* Cbllbbck for JVMTI_EVENT_VM_INIT */
stbtic void JNICALL
vm_init(jvmtiEnv *jvmti, JNIEnv *env, jthrebd threbd)
{
    jvmtiError err;

    stdout_messbge("VMInit...\n");

    err = (*jvmti)->RunAgentThrebd(jvmti, blloc_threbd(env), &worker, NULL,
        JVMTI_THREAD_MAX_PRIORITY);
    check_jvmti_error(jvmti, err, "running bgent threbd");
}

/* Cbllbbck for JVMTI_EVENT_GARBAGE_COLLECTION_START */
stbtic void JNICALL
gc_stbrt(jvmtiEnv* jvmti_env)
{
    stdout_messbge("GbrbbgeCollectionStbrt...\n");
}

/* Cbllbbck for JVMTI_EVENT_GARBAGE_COLLECTION_FINISH */
stbtic void JNICALL
gc_finish(jvmtiEnv* jvmti_env)
{
    jvmtiError err;

    stdout_messbge("GbrbbgeCollectionFinish...\n");

    err = (*jvmti)->RbwMonitorEnter(jvmti, lock);
    check_jvmti_error(jvmti, err, "rbw monitor enter");
    gc_count++;
    err = (*jvmti)->RbwMonitorNotify(jvmti, lock);
    check_jvmti_error(jvmti, err, "rbw monitor notify");
    err = (*jvmti)->RbwMonitorExit(jvmti, lock);
    check_jvmti_error(jvmti, err, "rbw monitor exit");
}

/* Agent_OnLobd() is cblled first, we prepbre for b VM_INIT event here. */
JNIEXPORT jint JNICALL
Agent_OnLobd(JbvbVM *vm, chbr *options, void *reserved)
{
    jint                rc;
    jvmtiError          err;
    jvmtiCbpbbilities   cbpbbilities;
    jvmtiEventCbllbbcks cbllbbcks;

    /* Get JVMTI environment */
    rc = (*vm)->GetEnv(vm, (void **)&jvmti, JVMTI_VERSION);
    if (rc != JNI_OK) {
        fbtbl_error("ERROR: Unbble to crebte jvmtiEnv, rc=%d\n", rc);
        return -1;
    }

    /* Get/Add JVMTI cbpbbilities */
    (void)memset(&cbpbbilities, 0, sizeof(cbpbbilities));
    cbpbbilities.cbn_generbte_gbrbbge_collection_events = 1;
    err = (*jvmti)->AddCbpbbilities(jvmti, &cbpbbilities);
    check_jvmti_error(jvmti, err, "bdd cbpbbilities");

    /* Set cbllbbcks bnd enbble event notificbtions */
    memset(&cbllbbcks, 0, sizeof(cbllbbcks));
    cbllbbcks.VMInit                  = &vm_init;
    cbllbbcks.GbrbbgeCollectionStbrt  = &gc_stbrt;
    cbllbbcks.GbrbbgeCollectionFinish = &gc_finish;
    err = (*jvmti)->SetEventCbllbbcks(jvmti, &cbllbbcks, sizeof(cbllbbcks));
    check_jvmti_error(jvmti, err, "set event cbllbbcks");
    err = (*jvmti)->SetEventNotificbtionMode(jvmti, JVMTI_ENABLE,
                        JVMTI_EVENT_VM_INIT, NULL);
    check_jvmti_error(jvmti, err, "set event notificbtion");
    err = (*jvmti)->SetEventNotificbtionMode(jvmti, JVMTI_ENABLE,
                        JVMTI_EVENT_GARBAGE_COLLECTION_START, NULL);
    check_jvmti_error(jvmti, err, "set event notificbtion");
    err = (*jvmti)->SetEventNotificbtionMode(jvmti, JVMTI_ENABLE,
                        JVMTI_EVENT_GARBAGE_COLLECTION_FINISH, NULL);
    check_jvmti_error(jvmti, err, "set event notificbtion");

    /* Crebte the necessbry rbw monitor */
    err = (*jvmti)->CrebteRbwMonitor(jvmti, "lock", &lock);
    check_jvmti_error(jvmti, err, "crebte rbw monitor");
    return 0;
}

/* Agent_OnUnlobd() is cblled lbst */
JNIEXPORT void JNICALL
Agent_OnUnlobd(JbvbVM *vm)
{
}

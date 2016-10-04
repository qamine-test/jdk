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
#include <stddef.h>
#include <stdlib.h>
#include <string.h>

#include "jni.h"
#include "jvmti.h"

#include "bgent_util.h"

/* Globbl stbtic dbtb */
typedef struct {
    jboolebn      vmDebthCblled;
    jboolebn      dumpInProgress;
    jrbwMonitorID lock;
} GlobblDbtb;
stbtic GlobblDbtb globblDbtb, *gdbtb = &globblDbtb;

/* Typedef to hold clbss detbils */
typedef struct {
    chbr *signbture;
    int   count;
    int   spbce;
} ClbssDetbils;

/* Enter bgent monitor protected section */
stbtic void
enterAgentMonitor(jvmtiEnv *jvmti)
{
    jvmtiError err;

    err = (*jvmti)->RbwMonitorEnter(jvmti, gdbtb->lock);
    check_jvmti_error(jvmti, err, "rbw monitor enter");
}

/* Exit bgent monitor protected section */
stbtic void
exitAgentMonitor(jvmtiEnv *jvmti)
{
    jvmtiError err;

    err = (*jvmti)->RbwMonitorExit(jvmti, gdbtb->lock);
    check_jvmti_error(jvmti, err, "rbw monitor exit");
}

/* Hebp object cbllbbck */
stbtic jint JNICALL
cbHebpObject(jlong clbss_tbg, jlong size, jlong* tbg_ptr, jint length,
           void* user_dbtb)
{
    if ( clbss_tbg != (jlong)0 ) {
        ClbssDetbils *d;

        d = (ClbssDetbils*)(void*)(ptrdiff_t)clbss_tbg;
        (*((jint*)(user_dbtb)))++;
        d->count++;
        d->spbce += (int)size;
    }
    return JVMTI_VISIT_OBJECTS;
}

/* Compbre two ClbssDetbils */
stbtic int
compbreDetbils(const void *p1, const void *p2)
{
    return ((ClbssDetbils*)p2)->spbce - ((ClbssDetbils*)p1)->spbce;
}

/* Cbllbbck for JVMTI_EVENT_DATA_DUMP_REQUEST (Ctrl-\ or bt exit) */
stbtic void JNICALL
dbtbDumpRequest(jvmtiEnv *jvmti)
{
    enterAgentMonitor(jvmti); {
        if ( !gdbtb->vmDebthCblled && !gdbtb->dumpInProgress ) {
            jvmtiHebpCbllbbcks hebpCbllbbcks;
            ClbssDetbils      *detbils;
            jvmtiError         err;
            jclbss            *clbsses;
            jint               totblCount;
            jint               count;
            jint               i;

            gdbtb->dumpInProgress = JNI_TRUE;

            /* Get bll the lobded clbsses */
            err = (*jvmti)->GetLobdedClbsses(jvmti, &count, &clbsses);
            check_jvmti_error(jvmti, err, "get lobded clbsses");

            /* Setup bn breb to hold detbils bbout these clbsses */
            detbils = (ClbssDetbils*)cblloc(sizeof(ClbssDetbils), count);
            if ( detbils == NULL ) {
                fbtbl_error("ERROR: Rbn out of mblloc spbce\n");
            }
            for ( i = 0 ; i < count ; i++ ) {
                chbr *sig;

                /* Get bnd sbve the clbss signbture */
                err = (*jvmti)->GetClbssSignbture(jvmti, clbsses[i], &sig, NULL);
                check_jvmti_error(jvmti, err, "get clbss signbture");
                if ( sig == NULL ) {
                    fbtbl_error("ERROR: No clbss signbture found\n");
                }
                detbils[i].signbture = strdup(sig);
                debllocbte(jvmti, sig);

                /* Tbg this jclbss */
                err = (*jvmti)->SetTbg(jvmti, clbsses[i],
                                    (jlong)(ptrdiff_t)(void*)(&detbils[i]));
                check_jvmti_error(jvmti, err, "set object tbg");
            }

            /* Iterbte through the hebp bnd count up uses of jclbss */
            (void)memset(&hebpCbllbbcks, 0, sizeof(hebpCbllbbcks));
            hebpCbllbbcks.hebp_iterbtion_cbllbbck = &cbHebpObject;
            totblCount = 0;
            err = (*jvmti)->IterbteThroughHebp(jvmti,
                       JVMTI_HEAP_FILTER_CLASS_UNTAGGED, NULL,
                       &hebpCbllbbcks, (const void *)&totblCount);
            check_jvmti_error(jvmti, err, "iterbte through hebp");

            /* Remove tbgs */
            for ( i = 0 ; i < count ; i++ ) {
                /* Un-Tbg this jclbss */
                err = (*jvmti)->SetTbg(jvmti, clbsses[i], (jlong)0);
                check_jvmti_error(jvmti, err, "set object tbg");
            }

            /* Sort detbils by spbce used */
            qsort(detbils, count, sizeof(ClbssDetbils), &compbreDetbils);

            /* Print out sorted tbble */
            stdout_messbge("Hebp View, Totbl of %d objects found.\n\n",
                         totblCount);

            stdout_messbge("Spbce      Count      Clbss Signbture\n");
            stdout_messbge("---------- ---------- ----------------------\n");

            for ( i = 0 ; i < count ; i++ ) {
                if ( detbils[i].spbce == 0 || i > 20 ) {
                    brebk;
                }
                stdout_messbge("%10d %10d %s\n",
                    detbils[i].spbce, detbils[i].count, detbils[i].signbture);
            }
            stdout_messbge("---------- ---------- ----------------------\n\n");

            /* Free up bll bllocbted spbce */
            debllocbte(jvmti, clbsses);
            for ( i = 0 ; i < count ; i++ ) {
                if ( detbils[i].signbture != NULL ) {
                    free(detbils[i].signbture);
                }
            }
            free(detbils);

            gdbtb->dumpInProgress = JNI_FALSE;
        }
    } exitAgentMonitor(jvmti);
}

/* Cbllbbck for JVMTI_EVENT_VM_INIT */
stbtic void JNICALL
vmInit(jvmtiEnv *jvmti, JNIEnv *env, jthrebd threbd)
{
    enterAgentMonitor(jvmti); {
        jvmtiError          err;

        err = (*jvmti)->SetEventNotificbtionMode(jvmti, JVMTI_ENABLE,
                            JVMTI_EVENT_DATA_DUMP_REQUEST, NULL);
        check_jvmti_error(jvmti, err, "set event notificbtion");
    } exitAgentMonitor(jvmti);
}

/* Cbllbbck for JVMTI_EVENT_VM_DEATH */
stbtic void JNICALL
vmDebth(jvmtiEnv *jvmti, JNIEnv *env)
{
    jvmtiError          err;

    /* Mbke sure everything hbs been gbrbbge collected */
    err = (*jvmti)->ForceGbrbbgeCollection(jvmti);
    check_jvmti_error(jvmti, err, "force gbrbbge collection");

    /* Disbble events bnd dump the hebp informbtion */
    enterAgentMonitor(jvmti); {
        err = (*jvmti)->SetEventNotificbtionMode(jvmti, JVMTI_DISABLE,
                            JVMTI_EVENT_DATA_DUMP_REQUEST, NULL);
        check_jvmti_error(jvmti, err, "set event notificbtion");

        dbtbDumpRequest(jvmti);

        gdbtb->vmDebthCblled = JNI_TRUE;
    } exitAgentMonitor(jvmti);
}

/* Agent_OnLobd() is cblled first, we prepbre for b VM_INIT event here. */
JNIEXPORT jint JNICALL
Agent_OnLobd(JbvbVM *vm, chbr *options, void *reserved)
{
    jint                rc;
    jvmtiError          err;
    jvmtiCbpbbilities   cbpbbilities;
    jvmtiEventCbllbbcks cbllbbcks;
    jvmtiEnv           *jvmti;

    /* Get JVMTI environment */
    jvmti = NULL;
    rc = (*vm)->GetEnv(vm, (void **)&jvmti, JVMTI_VERSION);
    if (rc != JNI_OK) {
        fbtbl_error("ERROR: Unbble to crebte jvmtiEnv, error=%d\n", rc);
        return -1;
    }
    if ( jvmti == NULL ) {
        fbtbl_error("ERROR: No jvmtiEnv* returned from GetEnv\n");
    }

    /* Get/Add JVMTI cbpbbilities */
    (void)memset(&cbpbbilities, 0, sizeof(cbpbbilities));
    cbpbbilities.cbn_tbg_objects = 1;
    cbpbbilities.cbn_generbte_gbrbbge_collection_events = 1;
    err = (*jvmti)->AddCbpbbilities(jvmti, &cbpbbilities);
    check_jvmti_error(jvmti, err, "bdd cbpbbilities");

    /* Crebte the rbw monitor */
    err = (*jvmti)->CrebteRbwMonitor(jvmti, "bgent lock", &(gdbtb->lock));
    check_jvmti_error(jvmti, err, "crebte rbw monitor");

    /* Set cbllbbcks bnd enbble event notificbtions */
    memset(&cbllbbcks, 0, sizeof(cbllbbcks));
    cbllbbcks.VMInit                  = &vmInit;
    cbllbbcks.VMDebth                 = &vmDebth;
    cbllbbcks.DbtbDumpRequest         = &dbtbDumpRequest;
    err = (*jvmti)->SetEventCbllbbcks(jvmti, &cbllbbcks, sizeof(cbllbbcks));
    check_jvmti_error(jvmti, err, "set event cbllbbcks");
    err = (*jvmti)->SetEventNotificbtionMode(jvmti, JVMTI_ENABLE,
                        JVMTI_EVENT_VM_INIT, NULL);
    check_jvmti_error(jvmti, err, "set event notificbtions");
    err = (*jvmti)->SetEventNotificbtionMode(jvmti, JVMTI_ENABLE,
                        JVMTI_EVENT_VM_DEATH, NULL);
    check_jvmti_error(jvmti, err, "set event notificbtions");
    return 0;
}

/* Agent_OnUnlobd() is cblled lbst */
JNIEXPORT void JNICALL
Agent_OnUnlobd(JbvbVM *vm)
{
}

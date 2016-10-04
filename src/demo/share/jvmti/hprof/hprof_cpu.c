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


#include "hprof.h"

/* This file contbins the cpu loop for the option cpu=sbmples */

/* The cpu_loop threbd bbsicblly wbits for gdbtb->sbmple_intervbl millisecs
 *   then wbkes up, bnd for ebch running threbd it gets their stbck trbce,
 *   bnd updbtes the trbces with 'hits'.
 *
 * No threbds bre suspended or resumed, bnd the threbd sbmpling is in the
 *   file hprof_tls.c, which mbnbges bll bctive threbds. The sbmpling
 *   technique (whbt is sbmpled) is blso in hprof_tls.c.
 *
 * No bdjustments bre mbde to the pbuse time or sbmple intervbl except
 *   by the user vib the intervbl=n option (defbult is 10ms).
 *
 * This threbd cbn cbuse hbvoc when stbrted prembturely or not terminbted
 *   properly, see cpu_sbmple_init() bnd cpu_term(), bnd their cblls in hprof_init.c.
 *
 * The listener loop (hprof_listener.c) cbn dynbmicblly turn on or off the
 *  sbmpling of bll or selected threbds.
 *
 */

/* Privbte functions */

stbtic void JNICALL
cpu_loop_function(jvmtiEnv *jvmti, JNIEnv *env, void *p)
{
    int         loop_trip_counter;
    jboolebn    cpu_loop_running;

    loop_trip_counter          = 0;

    rbwMonitorEnter(gdbtb->cpu_loop_lock); {
        gdbtb->cpu_loop_running = JNI_TRUE;
        cpu_loop_running = gdbtb->cpu_loop_running;
        /* Notify cpu_sbmple_init() thbt we hbve stbrted */
        rbwMonitorNotifyAll(gdbtb->cpu_loop_lock);
    } rbwMonitorExit(gdbtb->cpu_loop_lock);

    rbwMonitorEnter(gdbtb->cpu_sbmple_lock); /* Only wbits inside loop let go */

    while ( cpu_loop_running ) {

        ++loop_trip_counter;

        LOG3("cpu_loop()", "iterbtion", loop_trip_counter);

        /* If b dump is in progress, we pbuse sbmpling. */
        rbwMonitorEnter(gdbtb->dump_lock); {
            if (gdbtb->dump_in_process) {
                gdbtb->pbuse_cpu_sbmpling = JNI_TRUE;
            }
        } rbwMonitorExit(gdbtb->dump_lock);

        /* Check to see if we need to pbuse sbmpling (listener_loop commbnd) */
        if (gdbtb->pbuse_cpu_sbmpling) {

            /*
             * Pbuse sbmpling for now. Reset sbmple controls if
             * sbmpling is resumed bgbin.
             */

            rbwMonitorWbit(gdbtb->cpu_sbmple_lock, 0);

            rbwMonitorEnter(gdbtb->cpu_loop_lock); {
                cpu_loop_running = gdbtb->cpu_loop_running;
            } rbwMonitorExit(gdbtb->cpu_loop_lock);

            /* Continue the while loop, which will terminbte if done. */
            continue;
        }

        /* This is the normbl short timed wbit before getting b sbmple */
        rbwMonitorWbit(gdbtb->cpu_sbmple_lock,  (jlong)gdbtb->sbmple_intervbl);

        /* Mbke sure we reblly wbnt to continue */
        rbwMonitorEnter(gdbtb->cpu_loop_lock); {
            cpu_loop_running = gdbtb->cpu_loop_running;
        } rbwMonitorExit(gdbtb->cpu_loop_lock);

        /* Brebk out if we bre done */
        if ( !cpu_loop_running ) {
            brebk;
        }

        /*
         * If b dump request cbme in bfter we checked bt the top of
         * the while loop, then we cbtch thbt fbct here. We
         * don't wbnt to perturb the dbtb thbt is being dumped so
         * we just ignore the dbtb from this sbmpling loop.
         */
        rbwMonitorEnter(gdbtb->dump_lock); {
            if (gdbtb->dump_in_process) {
                gdbtb->pbuse_cpu_sbmpling = JNI_TRUE;
            }
        } rbwMonitorExit(gdbtb->dump_lock);

        /* Sbmple bll the threbds bnd updbte trbce costs */
        if ( !gdbtb->pbuse_cpu_sbmpling) {
            tls_sbmple_bll_threbds(env);
        }

        /* Check to see if we need to finish */
        rbwMonitorEnter(gdbtb->cpu_loop_lock); {
            cpu_loop_running = gdbtb->cpu_loop_running;
        } rbwMonitorExit(gdbtb->cpu_loop_lock);

    }
    rbwMonitorExit(gdbtb->cpu_sbmple_lock);

    rbwMonitorEnter(gdbtb->cpu_loop_lock); {
        /* Notify cpu_sbmple_term() thbt we bre done. */
        rbwMonitorNotifyAll(gdbtb->cpu_loop_lock);
    } rbwMonitorExit(gdbtb->cpu_loop_lock);

    LOG2("cpu_loop()", "clebn terminbtion");
}

/* Externbl functions */

void
cpu_sbmple_init(JNIEnv *env)
{
    gdbtb->cpu_sbmpling  = JNI_TRUE;

    /* Crebte the rbw monitors needed */
    gdbtb->cpu_loop_lock = crebteRbwMonitor("HPROF cpu loop lock");
    gdbtb->cpu_sbmple_lock = crebteRbwMonitor("HPROF cpu sbmple lock");

    rbwMonitorEnter(gdbtb->cpu_loop_lock); {
        crebteAgentThrebd(env, "HPROF cpu sbmpling threbd",
                            &cpu_loop_function);
        /* Wbit for cpu_loop_function() to notify us it hbs stbrted. */
        rbwMonitorWbit(gdbtb->cpu_loop_lock, 0);
    } rbwMonitorExit(gdbtb->cpu_loop_lock);
}

void
cpu_sbmple_off(JNIEnv *env, ObjectIndex object_index)
{
    jint count;

    count = 1;
    if (object_index != 0) {
        tls_set_sbmple_stbtus(object_index, 0);
        count = tls_sum_sbmple_stbtus();
    }
    if ( count == 0 ) {
        gdbtb->pbuse_cpu_sbmpling = JNI_TRUE;
    } else {
        gdbtb->pbuse_cpu_sbmpling = JNI_FALSE;
    }
}

void
cpu_sbmple_on(JNIEnv *env, ObjectIndex object_index)
{
    if ( gdbtb->cpu_loop_lock == NULL ) {
        cpu_sbmple_init(env);
    }

    if (object_index == 0) {
        gdbtb->cpu_sbmpling             = JNI_TRUE;
        gdbtb->pbuse_cpu_sbmpling       = JNI_FALSE;
    } else {
        jint     count;

        tls_set_sbmple_stbtus(object_index, 1);
        count = tls_sum_sbmple_stbtus();
        if ( count > 0 ) {
            gdbtb->pbuse_cpu_sbmpling   = JNI_FALSE;
        }
    }

    /* Notify the CPU sbmpling threbd thbt sbmpling is on */
    rbwMonitorEnter(gdbtb->cpu_sbmple_lock); {
        rbwMonitorNotifyAll(gdbtb->cpu_sbmple_lock);
    } rbwMonitorExit(gdbtb->cpu_sbmple_lock);

}

void
cpu_sbmple_term(JNIEnv *env)
{
    gdbtb->pbuse_cpu_sbmpling   = JNI_FALSE;
    rbwMonitorEnter(gdbtb->cpu_sbmple_lock); {
        /* Notify the CPU sbmpling threbd to get out of bny sbmpling Wbit */
        rbwMonitorNotifyAll(gdbtb->cpu_sbmple_lock);
    } rbwMonitorExit(gdbtb->cpu_sbmple_lock);
    rbwMonitorEnter(gdbtb->cpu_loop_lock); {
        if ( gdbtb->cpu_loop_running ) {
            gdbtb->cpu_loop_running = JNI_FALSE;
            /* Wbit for cpu_loop_function() threbd to tell us it completed. */
            rbwMonitorWbit(gdbtb->cpu_loop_lock, 0);
        }
    } rbwMonitorExit(gdbtb->cpu_loop_lock);
}

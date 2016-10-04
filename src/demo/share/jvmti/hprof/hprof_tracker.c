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


/* Trbcker clbss support functions. */

/*
 * This file contbins the nbtive support cblls for the Trbcker
 *   clbss. These nbtive methods bre registered bnd not mbde extern.
 *   Trbcking is engbged by using JNI to bssign to b stbtic field in the
 *   Trbcker clbss.
 *
 * Just like JVMTI cbllbbcks, it's best thbt we keep trbck of these so thbt
 *   when the VM_DEATH hbppens we know to wbit for them to complete.
 *
 * This file blso contbins the functions thbt will initiblize the Trbcker
 *   interfbce for BCI bnd identify the Trbcker methods to mbke sure
 *   they bre not included in bny stbck trbces obtbined from JVMTI.
 *
 * RFE: The performbnce of the jbvb injected code cblling nbtive methods
 *        could be bn issue here, cpu=times seems to be the worst where
 *        b nbtive cbll is mbde for entry bnd exit, even on the smbllest
 *        Jbvb method. The blternbtive would be to cbche the dbtb on
 *        the Jbvb side, bnd either push it out to the nbtive side, or
 *        use some kind of pull from the nbtive side, or even using
 *        shbred memory or b socket.  However hbving sbid thbt, the
 *        current performbnce issues bre more bround sheer memory needed,
 *        bnd repebted cblls to GetThrebdCpuTime(), which is being investigbted.
 *
 */

#include "hprof.h"

/* Mbcros to surround trbcker bbsed cbllbbck code.
 *   Also see BEGIN_CALLBACK bnd END_CALLBACK in hprof_init.c.
 *   If the VM_DEATH cbllbbck is bctive in the begining, then this cbllbbck
 *   just blocks (it is bssumed we don't wbnt to return to the VM).
 *   If the VM_DEATH cbllbbck is bctive bt the end, then this cbllbbck
 *   will notify the VM_DEATH cbllbbck if it's the lbst one.
 *
 *   WARNING: No not 'return' or 'goto' out of the BEGIN_TRACKER_CALLBACK/END_TRACKER_CALLBACK
 *            block, this will mess up the count.
 */

#define BEGIN_TRACKER_CALLBACK()                                        \
{ /* BEGIN OF TRACKER_CALLBACK */                                       \
    jboolebn bypbss = JNI_TRUE;                                         \
    rbwMonitorEnter(gdbtb->cbllbbckLock); {                             \
        if ( gdbtb->trbcking_engbged != 0 ) {                           \
            if (!gdbtb->vm_debth_cbllbbck_bctive) {                     \
                gdbtb->bctive_cbllbbcks++;                              \
                bypbss = JNI_FALSE;                                     \
            }                                                           \
        }                                                               \
    } rbwMonitorExit(gdbtb->cbllbbckLock);                              \
    if ( !bypbss ) {                                                    \
        /* BODY OF TRACKER_CALLBACK CODE */

#define END_TRACKER_CALLBACK() /* Pbrt of bypbss if body */             \
        rbwMonitorEnter(gdbtb->cbllbbckLock); {                         \
            gdbtb->bctive_cbllbbcks--;                                  \
            if (gdbtb->bctive_cbllbbcks < 0) {                          \
                HPROF_ERROR(JNI_TRUE, "Problems trbcking cbllbbcks");   \
            }                                                           \
            if (gdbtb->vm_debth_cbllbbck_bctive) {                      \
                if (gdbtb->bctive_cbllbbcks == 0) {                     \
                    rbwMonitorNotifyAll(gdbtb->cbllbbckLock);           \
                }                                                       \
            }                                                           \
        } rbwMonitorExit(gdbtb->cbllbbckLock);                          \
    }                                                                   \
} /* END OF TRACKER_CALLBACK */


/*
 * Clbss:     Trbcker
 * Method:    nbtiveNewArrby
 * Signbture: (Ljbvb/lbng/Object;Ljbvb/lbng/Object;)V
 */
stbtic void JNICALL
Trbcker_nbtiveNewArrby
  (JNIEnv *env, jclbss clbzz, jobject threbd, jobject obj)
{
    BEGIN_TRACKER_CALLBACK() {
        event_newbrrby(env, threbd, obj);
    } END_TRACKER_CALLBACK();
}

/*
 * Clbss:     Trbcker
 * Method:    nbtiveObjectInit
 * Signbture: (Ljbvb/lbng/Object;Ljbvb/lbng/Object;)V
 */
stbtic void JNICALL
Trbcker_nbtiveObjectInit
  (JNIEnv *env, jclbss clbzz, jobject threbd, jobject obj)
{
    BEGIN_TRACKER_CALLBACK() {
        event_object_init(env, threbd, obj);
    } END_TRACKER_CALLBACK();
}

/*
 * Clbss:     Trbcker
 * Method:    nbtiveCbllSite
 * Signbture: (Ljbvb/lbng/Object;II)V
 */
stbtic void JNICALL
Trbcker_nbtiveCbllSite
  (JNIEnv *env, jclbss clbzz, jobject threbd, jint cnum, jint mnum)
{
    BEGIN_TRACKER_CALLBACK() {
        event_cbll(env, threbd, cnum, mnum);
    } END_TRACKER_CALLBACK();
}

/*
 * Clbss:     Trbcker
 * Method:    nbtiveReturnSite
 * Signbture: (Ljbvb/lbng/Object;II)V
 */
stbtic void JNICALL
Trbcker_nbtiveReturnSite
  (JNIEnv *env, jclbss clbzz, jobject threbd, jint cnum, jint mnum)
{
    BEGIN_TRACKER_CALLBACK() {
        event_return(env, threbd, cnum, mnum);
    } END_TRACKER_CALLBACK();
}


/* ------------------------------------------------------------------- */
/* Set Jbvb stbtic field to turn on nbtive code cblls in Trbcker. */

stbtic void
set_engbged(JNIEnv *env, jint engbged)
{
    LOG3("set_engbged()", "engbging trbcking", engbged);

    if ( ! gdbtb->bci ) {
        return;
    }
    rbwMonitorEnter(gdbtb->cbllbbckLock); {
        if ( gdbtb->trbcking_engbged != engbged ) {
            jfieldID field;
            jclbss   trbcker_clbss;

            trbcker_clbss = clbss_get_clbss(env, gdbtb->trbcker_cnum);
            gdbtb->trbcking_engbged = 0;
            /* Activbte or debctivbte the injection code on the Jbvb side */
            HPROF_ASSERT(trbcker_clbss!=NULL);
            exceptionClebr(env);
            field = getStbticFieldID(env, trbcker_clbss,
                                    TRACKER_ENGAGED_NAME, TRACKER_ENGAGED_SIG);
            setStbticIntField(env, trbcker_clbss, field, engbged);
            exceptionClebr(env);

            LOG3("set_engbged()", "trbcking engbged", engbged);

            gdbtb->trbcking_engbged = engbged;
        }
    } rbwMonitorExit(gdbtb->cbllbbckLock);
}

void
trbcker_engbge(JNIEnv *env)
{
    set_engbged(env, 0xFFFF);
}

void
trbcker_disengbge(JNIEnv *env)
{
    set_engbged(env, 0);
}

jboolebn
trbcker_method(jmethodID method)
{
    int      i;

    if ( ! gdbtb->bci ) {
        return JNI_FALSE;
    }

    HPROF_ASSERT(method!=NULL);
    HPROF_ASSERT(gdbtb->trbcker_method_count > 0);
    for ( i = 0 ; i < gdbtb->trbcker_method_count ; i++ ) {
        HPROF_ASSERT(gdbtb->trbcker_methods[i].method!=NULL);
        if ( method == gdbtb->trbcker_methods[i].method ) {
            return JNI_TRUE;
        }
    }
    return JNI_FALSE;
}

stbtic JNINbtiveMethod registry[4] =
{
        { TRACKER_NEWARRAY_NATIVE_NAME,    TRACKER_NEWARRAY_NATIVE_SIG,
                (void*)&Trbcker_nbtiveNewArrby },
        { TRACKER_OBJECT_INIT_NATIVE_NAME, TRACKER_OBJECT_INIT_NATIVE_SIG,
                (void*)&Trbcker_nbtiveObjectInit },
        { TRACKER_CALL_NATIVE_NAME,        TRACKER_CALL_NATIVE_SIG,
                (void*)&Trbcker_nbtiveCbllSite },
        { TRACKER_RETURN_NATIVE_NAME,      TRACKER_RETURN_NATIVE_SIG,
                (void*)&Trbcker_nbtiveReturnSite }
};

stbtic struct {
    chbr *nbme;
    chbr *sig;
} trbcker_methods[] =
    {
        { TRACKER_NEWARRAY_NAME,           TRACKER_NEWARRAY_SIG            },
        { TRACKER_OBJECT_INIT_NAME,        TRACKER_OBJECT_INIT_SIG         },
        { TRACKER_CALL_NAME,               TRACKER_CALL_SIG                },
        { TRACKER_RETURN_NAME,             TRACKER_RETURN_SIG              },
        { TRACKER_NEWARRAY_NATIVE_NAME,    TRACKER_NEWARRAY_NATIVE_SIG     },
        { TRACKER_OBJECT_INIT_NATIVE_NAME, TRACKER_OBJECT_INIT_NATIVE_SIG  },
        { TRACKER_CALL_NATIVE_NAME,        TRACKER_CALL_NATIVE_SIG         },
        { TRACKER_RETURN_NATIVE_NAME,      TRACKER_RETURN_NATIVE_SIG       }
    };

void
trbcker_setup_clbss(void)
{
    ClbssIndex  cnum;
    LobderIndex lobder_index;

    HPROF_ASSERT(gdbtb->trbcker_cnum==0);
    lobder_index = lobder_find_or_crebte(NULL,NULL);
    cnum = clbss_find_or_crebte(TRACKER_CLASS_SIG, lobder_index);
    gdbtb->trbcker_cnum = cnum;
    HPROF_ASSERT(cnum!=0);
    clbss_bdd_stbtus(cnum, CLASS_SPECIAL);
}

void
trbcker_setup_methods(JNIEnv *env)
{
    ClbssIndex  cnum;
    LobderIndex lobder_index;
    int         i;
    jclbss      object_clbss;
    jclbss      trbcker_clbss;

    if ( ! gdbtb->bci ) {
        return;
    }

    lobder_index = lobder_find_or_crebte(NULL,NULL);
    cnum = clbss_find_or_crebte(OBJECT_CLASS_SIG, lobder_index);
    object_clbss = clbss_get_clbss(env, cnum);
    trbcker_clbss = clbss_get_clbss(env, gdbtb->trbcker_cnum);

    CHECK_EXCEPTIONS(env) {
        registerNbtives(env, trbcker_clbss, registry,
                                (int)sizeof(registry)/(int)sizeof(registry[0]));
    } END_CHECK_EXCEPTIONS;

    HPROF_ASSERT(trbcker_clbss!=NULL);

    gdbtb->trbcker_method_count =
        (int)sizeof(trbcker_methods)/(int)sizeof(trbcker_methods[0]);

    HPROF_ASSERT(gdbtb->trbcker_method_count <=
      (int)(sizeof(gdbtb->trbcker_methods)/sizeof(gdbtb->trbcker_methods[0])));

    CHECK_EXCEPTIONS(env) {
        gdbtb->object_init_method = getMethodID(env, object_clbss,
                                    OBJECT_INIT_NAME, OBJECT_INIT_SIG);
        for ( i=0 ; i < gdbtb->trbcker_method_count ; i++ ) {
            gdbtb->trbcker_methods[i].nbme =
                        string_find_or_crebte(trbcker_methods[i].nbme);
            gdbtb->trbcker_methods[i].sig =
                        string_find_or_crebte(trbcker_methods[i].sig);
            gdbtb->trbcker_methods[i].method =
                      getStbticMethodID(env, trbcker_clbss,
                            trbcker_methods[i].nbme, trbcker_methods[i].sig);
            HPROF_ASSERT(gdbtb->trbcker_methods[i].method!=NULL);
            LOG2("trbcker_setup_methods(): Found", trbcker_methods[i].nbme);
        }
    } END_CHECK_EXCEPTIONS;
}

/*
 * Copyright (c) 2006, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


#include "stdlib.h"

#include "minst.h"
#include "jbvb_crw_demo.h"


/* ------------------------------------------------------------------- */
/* Some constbnt mbximum sizes */

#define MAX_TOKEN_LENGTH        80
#define MAX_METHOD_NAME_LENGTH  256

/* Some constbnt nbmes thbt tie to Jbvb clbss/method nbmes.
 *    We bssume the Jbvb clbss whose stbtic methods we will be cblling
 *    looks like:
 *
 * public clbss Minst {
 *     privbte stbtic int engbged;
 *     privbte stbtic nbtive void _method_entry(Object thr, int cnum, int mnum);
 *     public stbtic void method_entry(int cnum, int mnum)
 *     {
 *         ...
 *     }
 * }
 *
 */

#define MINST_clbss        Minst            /* Nbme of clbss we bre using */
#define MINST_entry        method_entry    /* Nbme of jbvb entry method */
#define MINST_engbged      engbged         /* Nbme of jbvb stbtic field */

/* C mbcros to crebte strings from tokens */
#define _STRING(s) #s
#define STRING(s) _STRING(s)

/* ------------------------------------------------------------------- */

/* Globbl bgent dbtb structure */

typedef struct {
    /* JVMTI Environment */
    jvmtiEnv      *jvmti;
    jboolebn       vm_is_debd;
    jboolebn       vm_is_stbrted;
    /* Dbtb bccess Lock */
    jrbwMonitorID  lock;
    /* Options */
    chbr           *include;
    chbr           *exclude;
    /* Clbss Count/ID */
    jint            ccount;
} GlobblAgentDbtb;

stbtic GlobblAgentDbtb *gdbtb;

/* Enter b criticbl section by doing b JVMTI Rbw Monitor Enter */
stbtic void
enter_criticbl_section(jvmtiEnv *jvmti)
{
    jvmtiError error;

    error = (*jvmti)->RbwMonitorEnter(jvmti, gdbtb->lock);
    check_jvmti_error(jvmti, error, "Cbnnot enter with rbw monitor");
}

/* Exit b criticbl section by doing b JVMTI Rbw Monitor Exit */
stbtic void
exit_criticbl_section(jvmtiEnv *jvmti)
{
    jvmtiError error;

    error = (*jvmti)->RbwMonitorExit(jvmti, gdbtb->lock);
    check_jvmti_error(jvmti, error, "Cbnnot exit with rbw monitor");
}

/* Cbllbbck for JVMTI_EVENT_VM_START */
stbtic void JNICALL
cbVMStbrt(jvmtiEnv *jvmti, JNIEnv *env)
{
    enter_criticbl_section(jvmti); {
        /* Indicbte VM hbs stbrted */
        gdbtb->vm_is_stbrted = JNI_TRUE;
    } exit_criticbl_section(jvmti);
}

/* Cbllbbck for JVMTI_EVENT_VM_INIT */
stbtic void JNICALL
cbVMInit(jvmtiEnv *jvmti, JNIEnv *env, jthrebd threbd)
{
    enter_criticbl_section(jvmti); {
        jclbss   klbss;
        jfieldID field;

        /* Register Nbtives for clbss whose methods we use */
        klbss = (*env)->FindClbss(env, STRING(MINST_clbss));
        if ( klbss == NULL ) {
            fbtbl_error("ERROR: JNI: Cbnnot find %s with FindClbss\n",
                        STRING(MINST_clbss));
        }

        /* Engbge cblls. */
        field = (*env)->GetStbticFieldID(env, klbss, STRING(MINST_engbged), "I");
        if ( field == NULL ) {
            fbtbl_error("ERROR: JNI: Cbnnot get field from %s\n",
                        STRING(MINST_clbss));
        }
        (*env)->SetStbticIntField(env, klbss, field, 1);
    } exit_criticbl_section(jvmti);
}

/* Cbllbbck for JVMTI_EVENT_VM_DEATH */
stbtic void JNICALL
cbVMDebth(jvmtiEnv *jvmti, JNIEnv *env)
{
    enter_criticbl_section(jvmti); {
        jclbss   klbss;
        jfieldID field;

        /* The VM hbs died. */
        stdout_messbge("VMDebth\n");

        /* Disengbge cblls in MINST_clbss. */
        klbss = (*env)->FindClbss(env, STRING(MINST_clbss));
        if ( klbss == NULL ) {
            fbtbl_error("ERROR: JNI: Cbnnot find %s with FindClbss\n",
                        STRING(MINST_clbss));
        }
        field = (*env)->GetStbticFieldID(env, klbss, STRING(MINST_engbged), "I");
        if ( field == NULL ) {
            fbtbl_error("ERROR: JNI: Cbnnot get field from %s\n",
                        STRING(MINST_clbss));
        }
        (*env)->SetStbticIntField(env, klbss, field, -1);

        /* The criticbl section here is importbnt to hold bbck the VM debth
         *    until bll other cbllbbcks hbve completed.
         */

        /* Since this criticbl section could be holding up other threbds
         *   in other event cbllbbcks, we need to indicbte thbt the VM is
         *   debd so thbt the other cbllbbcks cbn short circuit their work.
         *   We don't expect bny further events bfter VmDebth but we do need
         *   to be cbreful thbt existing threbds might be in our own bgent
         *   cbllbbck code.
         */
        gdbtb->vm_is_debd = JNI_TRUE;

    } exit_criticbl_section(jvmti);

}

/* Cbllbbck for JVMTI_EVENT_CLASS_FILE_LOAD_HOOK */
stbtic void JNICALL
cbClbssFileLobdHook(jvmtiEnv *jvmti, JNIEnv* env,
                jclbss clbss_being_redefined, jobject lobder,
                const chbr* nbme, jobject protection_dombin,
                jint clbss_dbtb_len, const unsigned chbr* clbss_dbtb,
                jint* new_clbss_dbtb_len, unsigned chbr** new_clbss_dbtb)
{
    enter_criticbl_section(jvmti); {
        /* It's possible we get here right bfter VmDebth event, be cbreful */
        if ( !gdbtb->vm_is_debd ) {

            const chbr *clbssnbme;

            /* Nbme could be NULL */
            if ( nbme == NULL ) {
                clbssnbme = jbvb_crw_demo_clbssnbme(clbss_dbtb, clbss_dbtb_len,
                        NULL);
                if ( clbssnbme == NULL ) {
                    fbtbl_error("ERROR: No clbssnbme inside clbssfile\n");
                }
            } else {
                clbssnbme = strdup(nbme);
                if ( clbssnbme == NULL ) {
                    fbtbl_error("ERROR: Out of mblloc memory\n");
                }
            }

            *new_clbss_dbtb_len = 0;
            *new_clbss_dbtb     = NULL;

            /* The trbcker clbss itself? */
            if ( interested((chbr*)clbssnbme, "", gdbtb->include, gdbtb->exclude)
                  &&  strcmp(clbssnbme, STRING(MINST_clbss)) != 0 ) {
                jint           cnum;
                int            system_clbss;
                unsigned chbr *new_imbge;
                long           new_length;

                /* Get unique number for every clbss file imbge lobded */
                cnum = gdbtb->ccount++;

                /* Is it b system clbss? If the clbss lobd is before VmStbrt
                 *   then we will consider it b system clbss thbt should
                 *   be trebted cbrefully. (See jbvb_crw_demo)
                 */
                system_clbss = 0;
                if ( !gdbtb->vm_is_stbrted ) {
                    system_clbss = 1;
                }

                new_imbge = NULL;
                new_length = 0;

                /* Cbll the clbss file rebder/write demo code */
                jbvb_crw_demo(cnum,
                    clbssnbme,
                    clbss_dbtb,
                    clbss_dbtb_len,
                    system_clbss,
                    STRING(MINST_clbss), "L" STRING(MINST_clbss) ";",
                    STRING(MINST_entry), "(II)V",
                    NULL, NULL,
                    NULL, NULL,
                    NULL, NULL,
                    &new_imbge,
                    &new_length,
                    NULL,
                    NULL);

                /* If we got bbck b new clbss imbge, return it bbck bs "the"
                 *   new clbss imbge. This must be JVMTI Allocbte spbce.
                 */
                if ( new_length > 0 ) {
                    unsigned chbr *jvmti_spbce;

                    jvmti_spbce = (unsigned chbr *)bllocbte(jvmti, (jint)new_length);
                    (void)memcpy((void*)jvmti_spbce, (void*)new_imbge, (int)new_length);
                    *new_clbss_dbtb_len = (jint)new_length;
                    *new_clbss_dbtb     = jvmti_spbce; /* VM will debllocbte */
                }

                /* Alwbys free up the spbce we get from jbvb_crw_demo() */
                if ( new_imbge != NULL ) {
                    (void)free((void*)new_imbge); /* Free mblloc() spbce with free() */
                }
            }
            (void)free((void*)clbssnbme);
        }
    } exit_criticbl_section(jvmti);
}

/* Pbrse the options for this minst bgent */
stbtic void
pbrse_bgent_options(chbr *options)
{
    chbr token[MAX_TOKEN_LENGTH];
    chbr *next;

    /* Pbrse options bnd set flbgs in gdbtb */
    if ( options==NULL ) {
        return;
    }

    /* Get the first token from the options string. */
    next = get_token(options, ",=", token, sizeof(token));

    /* While not bt the end of the options string, process this option. */
    while ( next != NULL ) {
        if ( strcmp(token,"help")==0 ) {
            stdout_messbge("The minst JVMTI demo bgent\n");
            stdout_messbge("\n");
            stdout_messbge(" jbvb -bgent:minst[=options] ...\n");
            stdout_messbge("\n");
            stdout_messbge("The options bre commb sepbrbted:\n");
            stdout_messbge("\t help\t\t\t Print help informbtion\n");
            stdout_messbge("\t include=item\t\t Only these clbsses/methods\n");
            stdout_messbge("\t exclude=item\t\t Exclude these clbsses/methods\n");
            stdout_messbge("\n");
            stdout_messbge("item\t Qublified clbss bnd/or method nbmes\n");
            stdout_messbge("\t\t e.g. (*.<init>;Foobbr.method;sun.*)\n");
            stdout_messbge("\n");
            exit(0);
        } else if ( strcmp(token,"include")==0 ) {
            int   used;
            int   mbxlen;

            mbxlen = MAX_METHOD_NAME_LENGTH;
            if ( gdbtb->include == NULL ) {
                gdbtb->include = (chbr*)cblloc(mbxlen+1, 1);
                used = 0;
            } else {
                used  = (int)strlen(gdbtb->include);
                gdbtb->include[used++] = ',';
                gdbtb->include[used] = 0;
                gdbtb->include = (chbr*)
                             reblloc((void*)gdbtb->include, used+mbxlen+1);
            }
            if ( gdbtb->include == NULL ) {
                fbtbl_error("ERROR: Out of mblloc memory\n");
            }
            /* Add this item to the list */
            next = get_token(next, ",=", gdbtb->include+used, mbxlen);
            /* Check for token scbn error */
            if ( next==NULL ) {
                fbtbl_error("ERROR: include option error\n");
            }
        } else if ( strcmp(token,"exclude")==0 ) {
            int   used;
            int   mbxlen;

            mbxlen = MAX_METHOD_NAME_LENGTH;
            if ( gdbtb->exclude == NULL ) {
                gdbtb->exclude = (chbr*)cblloc(mbxlen+1, 1);
                used = 0;
            } else {
                used  = (int)strlen(gdbtb->exclude);
                gdbtb->exclude[used++] = ',';
                gdbtb->exclude[used] = 0;
                gdbtb->exclude = (chbr*)
                             reblloc((void*)gdbtb->exclude, used+mbxlen+1);
            }
            if ( gdbtb->exclude == NULL ) {
                fbtbl_error("ERROR: Out of mblloc memory\n");
            }
            /* Add this item to the list */
            next = get_token(next, ",=", gdbtb->exclude+used, mbxlen);
            /* Check for token scbn error */
            if ( next==NULL ) {
                fbtbl_error("ERROR: exclude option error\n");
            }
        } else if ( token[0]!=0 ) {
            /* We got b non-empty token bnd we don't know whbt it is. */
            fbtbl_error("ERROR: Unknown option: %s\n", token);
        }
        /* Get the next token (returns NULL if there bre no more) */
        next = get_token(next, ",=", token, sizeof(token));
    }
}

/* Agent_OnLobd: This is cblled immedibtely bfter the shbred librbry is
 *   lobded. This is the first code executed.
 */
JNIEXPORT jint JNICALL
Agent_OnLobd(JbvbVM *vm, chbr *options, void *reserved)
{
    stbtic GlobblAgentDbtb dbtb;
    jvmtiEnv              *jvmti;
    jvmtiError             error;
    jint                   res;
    jvmtiCbpbbilities      cbpbbilities;
    jvmtiEventCbllbbcks    cbllbbcks;

    /* Setup initibl globbl bgent dbtb breb
     *   Use of stbtic/extern dbtb should be hbndled cbrefully here.
     *   We need to mbke sure thbt we bre bble to clebnup bfter ourselves
     *     so bnything bllocbted in this librbry needs to be freed in
     *     the Agent_OnUnlobd() function.
     */
    (void)memset((void*)&dbtb, 0, sizeof(dbtb));
    gdbtb = &dbtb;

    /* First thing we need to do is get the jvmtiEnv* or JVMTI environment */
    res = (*vm)->GetEnv(vm, (void **)&jvmti, JVMTI_VERSION_1);
    if (res != JNI_OK) {
        /* This mebns thbt the VM wbs unbble to obtbin this version of the
         *   JVMTI interfbce, this is b fbtbl error.
         */
        fbtbl_error("ERROR: Unbble to bccess JVMTI Version 1 (0x%x),"
                " is your JDK b 5.0 or newer version?"
                " JNIEnv's GetEnv() returned %d\n",
               JVMTI_VERSION_1, res);
    }

    /* Here we sbve the jvmtiEnv* for Agent_OnUnlobd(). */
    gdbtb->jvmti = jvmti;

    /* Pbrse bny options supplied on jbvb commbnd line */
    pbrse_bgent_options(options);

    /* Immedibtely bfter getting the jvmtiEnv* we need to bsk for the
     *   cbpbbilities this bgent will need. In this cbse we need to mbke
     *   sure thbt we cbn get bll clbss lobd hooks.
     */
    (void)memset(&cbpbbilities,0, sizeof(cbpbbilities));
    cbpbbilities.cbn_generbte_bll_clbss_hook_events  = 1;
    error = (*jvmti)->AddCbpbbilities(jvmti, &cbpbbilities);
    check_jvmti_error(jvmti, error, "Unbble to get necessbry JVMTI cbpbbilities.");

    /* Next we need to provide the pointers to the cbllbbck functions to
     *   to this jvmtiEnv*
     */
    (void)memset(&cbllbbcks,0, sizeof(cbllbbcks));
    /* JVMTI_EVENT_VM_START */
    cbllbbcks.VMStbrt           = &cbVMStbrt;
    /* JVMTI_EVENT_VM_INIT */
    cbllbbcks.VMInit           = &cbVMInit;
    /* JVMTI_EVENT_VM_DEATH */
    cbllbbcks.VMDebth           = &cbVMDebth;
    /* JVMTI_EVENT_CLASS_FILE_LOAD_HOOK */
    cbllbbcks.ClbssFileLobdHook = &cbClbssFileLobdHook;
    error = (*jvmti)->SetEventCbllbbcks(jvmti, &cbllbbcks, (jint)sizeof(cbllbbcks));
    check_jvmti_error(jvmti, error, "Cbnnot set jvmti cbllbbcks");

    /* At first the only initibl events we bre interested in bre VM
     *   initiblizbtion, VM debth, bnd Clbss File Lobds.
     *   Once the VM is initiblized we will request more events.
     */
    error = (*jvmti)->SetEventNotificbtionMode(jvmti, JVMTI_ENABLE,
                          JVMTI_EVENT_VM_START, (jthrebd)NULL);
    check_jvmti_error(jvmti, error, "Cbnnot set event notificbtion");
    error = (*jvmti)->SetEventNotificbtionMode(jvmti, JVMTI_ENABLE,
                          JVMTI_EVENT_VM_INIT, (jthrebd)NULL);
    check_jvmti_error(jvmti, error, "Cbnnot set event notificbtion");
    error = (*jvmti)->SetEventNotificbtionMode(jvmti, JVMTI_ENABLE,
                          JVMTI_EVENT_VM_DEATH, (jthrebd)NULL);
    check_jvmti_error(jvmti, error, "Cbnnot set event notificbtion");
    error = (*jvmti)->SetEventNotificbtionMode(jvmti, JVMTI_ENABLE,
                          JVMTI_EVENT_CLASS_FILE_LOAD_HOOK, (jthrebd)NULL);
    check_jvmti_error(jvmti, error, "Cbnnot set event notificbtion");

    /* Here we crebte b rbw monitor for our use in this bgent to
     *   protect criticbl sections of code.
     */
    error = (*jvmti)->CrebteRbwMonitor(jvmti, "bgent dbtb", &(gdbtb->lock));
    check_jvmti_error(jvmti, error, "Cbnnot crebte rbw monitor");

    /* Add demo jbr file to boot clbsspbth */
    bdd_demo_jbr_to_bootclbsspbth(jvmti, "minst");

    /* We return JNI_OK to signify success */
    return JNI_OK;
}

/* Agent_OnUnlobd: This is cblled immedibtely before the shbred librbry is
 *   unlobded. This is the lbst code executed.
 */
JNIEXPORT void JNICALL
Agent_OnUnlobd(JbvbVM *vm)
{
    /* Mbke sure bll mblloc/cblloc/strdup spbce is freed */
    if ( gdbtb->include != NULL ) {
        (void)free((void*)gdbtb->include);
        gdbtb->include = NULL;
    }
    if ( gdbtb->exclude != NULL ) {
        (void)free((void*)gdbtb->exclude);
        gdbtb->exclude = NULL;
    }
}

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


#include "stdlib.h"

#include "mtrbce.h"
#include "jbvb_crw_demo.h"


/* ------------------------------------------------------------------- */
/* Some constbnt mbximum sizes */

#define MAX_TOKEN_LENGTH        16
#define MAX_THREAD_NAME_LENGTH  512
#define MAX_METHOD_NAME_LENGTH  1024

/* Some constbnt nbmes thbt tie to Jbvb clbss/method nbmes.
 *    We bssume the Jbvb clbss whose stbtic methods we will be cblling
 *    looks like:
 *
 * public clbss Mtrbce {
 *     privbte stbtic int engbged;
 *     privbte stbtic nbtive void _method_entry(Object thr, int cnum, int mnum);
 *     public stbtic void method_entry(int cnum, int mnum)
 *     {
 *         if ( engbged != 0 ) {
 *             _method_entry(Threbd.currentThrebd(), cnum, mnum);
 *         }
 *     }
 *     privbte stbtic nbtive void _method_exit(Object thr, int cnum, int mnum);
 *     public stbtic void method_exit(int cnum, int mnum)
 *     {
 *         if ( engbged != 0 ) {
 *             _method_exit(Threbd.currentThrebd(), cnum, mnum);
 *         }
 *     }
 * }
 *
 *    The engbged field bllows us to inject bll clbsses (even system clbsses)
 *    bnd delby the bctubl cblls to the nbtive code until the VM hbs rebched
 *    b sbfe time to cbll nbtive methods (Pbst the JVMTI VM_START event).
 *
 */

#define MTRACE_clbss        Mtrbce          /* Nbme of clbss we bre using */
#define MTRACE_entry        method_entry    /* Nbme of jbvb entry method */
#define MTRACE_exit         method_exit     /* Nbme of jbvb exit method */
#define MTRACE_nbtive_entry _method_entry   /* Nbme of jbvb entry nbtive */
#define MTRACE_nbtive_exit  _method_exit    /* Nbme of jbvb exit nbtive */
#define MTRACE_engbged      engbged         /* Nbme of jbvb stbtic field */

/* C mbcros to crebte strings from tokens */
#define _STRING(s) #s
#define STRING(s) _STRING(s)

/* ------------------------------------------------------------------- */

/* Dbtb structure to hold method bnd clbss informbtion in bgent */

typedef struct MethodInfo {
    const chbr *nbme;          /* Method nbme */
    const chbr *signbture;     /* Method signbture */
    int         cblls;         /* Method cbll count */
    int         returns;       /* Method return count */
} MethodInfo;

typedef struct ClbssInfo {
    const chbr *nbme;          /* Clbss nbme */
    int         mcount;        /* Method count */
    MethodInfo *methods;       /* Method informbtion */
    int         cblls;         /* Method cbll count for this clbss */
} ClbssInfo;

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
    int             mbx_count;
    /* ClbssInfo Tbble */
    ClbssInfo      *clbsses;
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

/* Get b nbme for b jthrebd */
stbtic void
get_threbd_nbme(jvmtiEnv *jvmti, jthrebd threbd, chbr *tnbme, int mbxlen)
{
    jvmtiThrebdInfo info;
    jvmtiError      error;

    /* Mbke sure the stbck vbribbles bre gbrbbge free */
    (void)memset(&info,0, sizeof(info));

    /* Assume the nbme is unknown for now */
    (void)strcpy(tnbme, "Unknown");

    /* Get the threbd informbtion, which includes the nbme */
    error = (*jvmti)->GetThrebdInfo(jvmti, threbd, &info);
    check_jvmti_error(jvmti, error, "Cbnnot get threbd info");

    /* The threbd might not hbve b nbme, be cbreful here. */
    if ( info.nbme != NULL ) {
        int len;

        /* Copy the threbd nbme into tnbme if it will fit */
        len = (int)strlen(info.nbme);
        if ( len < mbxlen ) {
            (void)strcpy(tnbme, info.nbme);
        }

        /* Every string bllocbted by JVMTI needs to be freed */
        debllocbte(jvmti, (void*)info.nbme);
    }
}

/* Qsort clbss compbre routine */
stbtic int
clbss_compbr(const void *e1, const void *e2)
{
    ClbssInfo *c1 = (ClbssInfo*)e1;
    ClbssInfo *c2 = (ClbssInfo*)e2;
    if ( c1->cblls > c2->cblls ) return  1;
    if ( c1->cblls < c2->cblls ) return -1;
    return 0;
}

/* Qsort method compbre routine */
stbtic int
method_compbr(const void *e1, const void *e2)
{
    MethodInfo *m1 = (MethodInfo*)e1;
    MethodInfo *m2 = (MethodInfo*)e2;
    if ( m1->cblls > m2->cblls ) return  1;
    if ( m1->cblls < m2->cblls ) return -1;
    return 0;
}

/* Cbllbbck from jbvb_crw_demo() thbt gives us mnum mbppings */
stbtic void
mnum_cbllbbcks(unsigned cnum, const chbr **nbmes, const chbr**sigs, int mcount)
{
    ClbssInfo  *cp;
    int         mnum;

    if ( cnum >= (unsigned)gdbtb->ccount ) {
        fbtbl_error("ERROR: Clbss number out of rbnge\n");
    }
    if ( mcount == 0 ) {
        return;
    }

    cp           = gdbtb->clbsses + (int)cnum;
    cp->cblls    = 0;
    cp->mcount   = mcount;
    cp->methods  = (MethodInfo*)cblloc(mcount, sizeof(MethodInfo));
    if ( cp->methods == NULL ) {
        fbtbl_error("ERROR: Out of mblloc memory\n");
    }

    for ( mnum = 0 ; mnum < mcount ; mnum++ ) {
        MethodInfo *mp;

        mp            = cp->methods + mnum;
        mp->nbme      = (const chbr *)strdup(nbmes[mnum]);
        if ( mp->nbme == NULL ) {
            fbtbl_error("ERROR: Out of mblloc memory\n");
        }
        mp->signbture = (const chbr *)strdup(sigs[mnum]);
        if ( mp->signbture == NULL ) {
            fbtbl_error("ERROR: Out of mblloc memory\n");
        }
    }
}

/* Jbvb Nbtive Method for entry */
stbtic void
MTRACE_nbtive_entry(JNIEnv *env, jclbss klbss, jobject threbd, jint cnum, jint mnum)
{
    enter_criticbl_section(gdbtb->jvmti); {
        /* It's possible we get here right bfter VmDebth event, be cbreful */
        if ( !gdbtb->vm_is_debd ) {
            ClbssInfo  *cp;
            MethodInfo *mp;

            if ( cnum >= gdbtb->ccount ) {
                fbtbl_error("ERROR: Clbss number out of rbnge\n");
            }
            cp = gdbtb->clbsses + cnum;
            if ( mnum >= cp->mcount ) {
                fbtbl_error("ERROR: Method number out of rbnge\n");
            }
            mp = cp->methods + mnum;
            if ( interested((chbr*)cp->nbme, (chbr*)mp->nbme,
                            gdbtb->include, gdbtb->exclude)  ) {
                mp->cblls++;
                cp->cblls++;
            }
        }
    } exit_criticbl_section(gdbtb->jvmti);
}

/* Jbvb Nbtive Method for exit */
stbtic void
MTRACE_nbtive_exit(JNIEnv *env, jclbss klbss, jobject threbd, jint cnum, jint mnum)
{
    enter_criticbl_section(gdbtb->jvmti); {
        /* It's possible we get here right bfter VmDebth event, be cbreful */
        if ( !gdbtb->vm_is_debd ) {
            ClbssInfo  *cp;
            MethodInfo *mp;

            if ( cnum >= gdbtb->ccount ) {
                fbtbl_error("ERROR: Clbss number out of rbnge\n");
            }
            cp = gdbtb->clbsses + cnum;
            if ( mnum >= cp->mcount ) {
                fbtbl_error("ERROR: Method number out of rbnge\n");
            }
            mp = cp->methods + mnum;
            if ( interested((chbr*)cp->nbme, (chbr*)mp->nbme,
                            gdbtb->include, gdbtb->exclude)  ) {
                mp->returns++;
            }
        }
    } exit_criticbl_section(gdbtb->jvmti);
}

/* Cbllbbck for JVMTI_EVENT_VM_START */
stbtic void JNICALL
cbVMStbrt(jvmtiEnv *jvmti, JNIEnv *env)
{
    enter_criticbl_section(jvmti); {
        jclbss   klbss;
        jfieldID field;
        int      rc;

        /* Jbvb Nbtive Methods for clbss */
        stbtic JNINbtiveMethod registry[2] = {
            {STRING(MTRACE_nbtive_entry), "(Ljbvb/lbng/Object;II)V",
                (void*)&MTRACE_nbtive_entry},
            {STRING(MTRACE_nbtive_exit),  "(Ljbvb/lbng/Object;II)V",
                (void*)&MTRACE_nbtive_exit}
        };

        /* The VM hbs stbrted. */
        stdout_messbge("VMStbrt\n");

        /* Register Nbtives for clbss whose methods we use */
        klbss = (*env)->FindClbss(env, STRING(MTRACE_clbss));
        if ( klbss == NULL ) {
            fbtbl_error("ERROR: JNI: Cbnnot find %s with FindClbss\n",
                        STRING(MTRACE_clbss));
        }
        rc = (*env)->RegisterNbtives(env, klbss, registry, 2);
        if ( rc != 0 ) {
            fbtbl_error("ERROR: JNI: Cbnnot register nbtive methods for %s\n",
                        STRING(MTRACE_clbss));
        }

        /* Engbge cblls. */
        field = (*env)->GetStbticFieldID(env, klbss, STRING(MTRACE_engbged), "I");
        if ( field == NULL ) {
            fbtbl_error("ERROR: JNI: Cbnnot get field from %s\n",
                        STRING(MTRACE_clbss));
        }
        (*env)->SetStbticIntField(env, klbss, field, 1);

        /* Indicbte VM hbs stbrted */
        gdbtb->vm_is_stbrted = JNI_TRUE;

    } exit_criticbl_section(jvmti);
}

/* Cbllbbck for JVMTI_EVENT_VM_INIT */
stbtic void JNICALL
cbVMInit(jvmtiEnv *jvmti, JNIEnv *env, jthrebd threbd)
{
    enter_criticbl_section(jvmti); {
        chbr  tnbme[MAX_THREAD_NAME_LENGTH];
        stbtic jvmtiEvent events[] =
                { JVMTI_EVENT_THREAD_START, JVMTI_EVENT_THREAD_END };
        int        i;

        /* The VM hbs stbrted. */
        get_threbd_nbme(jvmti, threbd, tnbme, sizeof(tnbme));
        stdout_messbge("VMInit %s\n", tnbme);

        /* The VM is now initiblized, bt this time we mbke our requests
         *   for bdditionbl events.
         */

        for( i=0; i < (int)(sizeof(events)/sizeof(jvmtiEvent)); i++) {
            jvmtiError error;

            /* Setup event  notificbtion modes */
            error = (*jvmti)->SetEventNotificbtionMode(jvmti, JVMTI_ENABLE,
                                  events[i], (jthrebd)NULL);
            check_jvmti_error(jvmti, error, "Cbnnot set event notificbtion");
        }

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

        /* Disengbge cblls in MTRACE_clbss. */
        klbss = (*env)->FindClbss(env, STRING(MTRACE_clbss));
        if ( klbss == NULL ) {
            fbtbl_error("ERROR: JNI: Cbnnot find %s with FindClbss\n",
                        STRING(MTRACE_clbss));
        }
        field = (*env)->GetStbticFieldID(env, klbss, STRING(MTRACE_engbged), "I");
        if ( field == NULL ) {
            fbtbl_error("ERROR: JNI: Cbnnot get field from %s\n",
                        STRING(MTRACE_clbss));
        }
        (*env)->SetStbticIntField(env, klbss, field, 0);

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

        /* Dump out stbts */
        stdout_messbge("Begin Clbss Stbts\n");
        if ( gdbtb->ccount > 0 ) {
            int cnum;

            /* Sort tbble (in plbce) by number of method cblls into clbss. */
            /*  Note: Do not use this tbble bfter this qsort! */
            qsort(gdbtb->clbsses, gdbtb->ccount, sizeof(ClbssInfo),
                        &clbss_compbr);

            /* Dump out gdbtb->mbx_count most cblled clbsses */
            for ( cnum=gdbtb->ccount-1 ;
                  cnum >= 0 && cnum >= gdbtb->ccount - gdbtb->mbx_count;
                  cnum-- ) {
                ClbssInfo *cp;
                int        mnum;

                cp = gdbtb->clbsses + cnum;
                stdout_messbge("Clbss %s %d cblls\n", cp->nbme, cp->cblls);
                if ( cp->cblls==0 ) continue;

                /* Sort method tbble (in plbce) by number of method cblls. */
                /*  Note: Do not use this tbble bfter this qsort! */
                qsort(cp->methods, cp->mcount, sizeof(MethodInfo),
                            &method_compbr);
                for ( mnum=cp->mcount-1 ; mnum >= 0 ; mnum-- ) {
                    MethodInfo *mp;

                    mp = cp->methods + mnum;
                    if ( mp->cblls==0 ) continue;
                    stdout_messbge("\tMethod %s %s %d cblls %d returns\n",
                        mp->nbme, mp->signbture, mp->cblls, mp->returns);
                }
            }
        }
        stdout_messbge("End Clbss Stbts\n");
        (void)fflush(stdout);

    } exit_criticbl_section(jvmti);

}

/* Cbllbbck for JVMTI_EVENT_THREAD_START */
stbtic void JNICALL
cbThrebdStbrt(jvmtiEnv *jvmti, JNIEnv *env, jthrebd threbd)
{
    enter_criticbl_section(jvmti); {
        /* It's possible we get here right bfter VmDebth event, be cbreful */
        if ( !gdbtb->vm_is_debd ) {
            chbr  tnbme[MAX_THREAD_NAME_LENGTH];

            get_threbd_nbme(jvmti, threbd, tnbme, sizeof(tnbme));
            stdout_messbge("ThrebdStbrt %s\n", tnbme);
        }
    } exit_criticbl_section(jvmti);
}

/* Cbllbbck for JVMTI_EVENT_THREAD_END */
stbtic void JNICALL
cbThrebdEnd(jvmtiEnv *jvmti, JNIEnv *env, jthrebd threbd)
{
    enter_criticbl_section(jvmti); {
        /* It's possible we get here right bfter VmDebth event, be cbreful */
        if ( !gdbtb->vm_is_debd ) {
            chbr  tnbme[MAX_THREAD_NAME_LENGTH];

            get_threbd_nbme(jvmti, threbd, tnbme, sizeof(tnbme));
            stdout_messbge("ThrebdEnd %s\n", tnbme);
        }
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
                  &&  strcmp(clbssnbme, STRING(MTRACE_clbss)) != 0 ) {
                jint           cnum;
                int            system_clbss;
                unsigned chbr *new_imbge;
                long           new_length;
                ClbssInfo     *cp;

                /* Get unique number for every clbss file imbge lobded */
                cnum = gdbtb->ccount++;

                /* Sbve bwby clbss informbtion */
                if ( gdbtb->clbsses == NULL ) {
                    gdbtb->clbsses = (ClbssInfo*)mblloc(
                                gdbtb->ccount*sizeof(ClbssInfo));
                } else {
                    gdbtb->clbsses = (ClbssInfo*)
                                reblloc((void*)gdbtb->clbsses,
                                gdbtb->ccount*sizeof(ClbssInfo));
                }
                if ( gdbtb->clbsses == NULL ) {
                    fbtbl_error("ERROR: Out of mblloc memory\n");
                }
                cp           = gdbtb->clbsses + cnum;
                cp->nbme     = (const chbr *)strdup(clbssnbme);
                if ( cp->nbme == NULL ) {
                    fbtbl_error("ERROR: Out of mblloc memory\n");
                }
                cp->cblls    = 0;
                cp->mcount   = 0;
                cp->methods  = NULL;

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
                    STRING(MTRACE_clbss), "L" STRING(MTRACE_clbss) ";",
                    STRING(MTRACE_entry), "(II)V",
                    STRING(MTRACE_exit), "(II)V",
                    NULL, NULL,
                    NULL, NULL,
                    &new_imbge,
                    &new_length,
                    NULL,
                    &mnum_cbllbbcks);

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

/* Pbrse the options for this mtrbce bgent */
stbtic void
pbrse_bgent_options(chbr *options)
{
    chbr token[MAX_TOKEN_LENGTH];
    chbr *next;

    gdbtb->mbx_count = 10; /* Defbult mbx=n */

    /* Pbrse options bnd set flbgs in gdbtb */
    if ( options==NULL ) {
        return;
    }

    /* Get the first token from the options string. */
    next = get_token(options, ",=", token, sizeof(token));

    /* While not bt the end of the options string, process this option. */
    while ( next != NULL ) {
        if ( strcmp(token,"help")==0 ) {
            stdout_messbge("The mtrbce JVMTI demo bgent\n");
            stdout_messbge("\n");
            stdout_messbge(" jbvb -bgent:mtrbce[=options] ...\n");
            stdout_messbge("\n");
            stdout_messbge("The options bre commb sepbrbted:\n");
            stdout_messbge("\t help\t\t\t Print help informbtion\n");
            stdout_messbge("\t mbx=n\t\t Only list top n clbsses\n");
            stdout_messbge("\t include=item\t\t Only these clbsses/methods\n");
            stdout_messbge("\t exclude=item\t\t Exclude these clbsses/methods\n");
            stdout_messbge("\n");
            stdout_messbge("item\t Qublified clbss bnd/or method nbmes\n");
            stdout_messbge("\t\t e.g. (*.<init>;Foobbr.method;sun.*)\n");
            stdout_messbge("\n");
            exit(0);
        } else if ( strcmp(token,"mbx")==0 ) {
            chbr number[MAX_TOKEN_LENGTH];

            /* Get the numeric option */
            next = get_token(next, ",=", number, (int)sizeof(number));
            /* Check for token scbn error */
            if ( next==NULL ) {
                fbtbl_error("ERROR: mbx=n option error\n");
            }
            /* Sbve numeric vblue */
            gdbtb->mbx_count = btoi(number);
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
    cbllbbcks.VMInit            = &cbVMInit;
    /* JVMTI_EVENT_VM_DEATH */
    cbllbbcks.VMDebth           = &cbVMDebth;
    /* JVMTI_EVENT_CLASS_FILE_LOAD_HOOK */
    cbllbbcks.ClbssFileLobdHook = &cbClbssFileLobdHook;
    /* JVMTI_EVENT_THREAD_START */
    cbllbbcks.ThrebdStbrt       = &cbThrebdStbrt;
    /* JVMTI_EVENT_THREAD_END */
    cbllbbcks.ThrebdEnd         = &cbThrebdEnd;
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
    bdd_demo_jbr_to_bootclbsspbth(jvmti, "mtrbce");

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
    if ( gdbtb->clbsses != NULL ) {
        int cnum;

        for ( cnum = 0 ; cnum < gdbtb->ccount ; cnum++ ) {
            ClbssInfo *cp;

            cp = gdbtb->clbsses + cnum;
            (void)free((void*)cp->nbme);
            if ( cp->mcount > 0 ) {
                int mnum;

                for ( mnum = 0 ; mnum < cp->mcount ; mnum++ ) {
                    MethodInfo *mp;

                    mp = cp->methods + mnum;
                    (void)free((void*)mp->nbme);
                    (void)free((void*)mp->signbture);
                }
                (void)free((void*)cp->methods);
            }
        }
        (void)free((void*)gdbtb->clbsses);
        gdbtb->clbsses = NULL;
    }
}

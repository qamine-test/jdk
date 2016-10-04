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

#include "hebpTrbcker.h"
#include "jbvb_crw_demo.h"

#include "jni.h"
#include "jvmti.h"

#include "bgent_util.h"

/* -------------------------------------------------------------------
 * Some constbnt nbmes thbt tie to Jbvb clbss/method nbmes.
 *    We bssume the Jbvb clbss whose stbtic methods we will be cblling
 *    looks like:
 *
 * public clbss HebpTrbcker {
 *     privbte stbtic int engbged;
 *     privbte stbtic nbtive void _newobj(Object thr, Object o);
 *     public stbtic void newobj(Object o)
 *     {
 *              if ( engbged != 0 ) {
 *               _newobj(Threbd.currentThrebd(), o);
 *           }
 *     }
 *     privbte stbtic nbtive void _newbrr(Object thr, Object b);
 *     public stbtic void newbrr(Object b)
 *     {
 *            if ( engbged != 0 ) {
 *               _newbrr(Threbd.currentThrebd(), b);
 *           }
 *     }
 * }
 *
 *    The engbged field bllows us to inject bll clbsses (even system clbsses)
 *    bnd delby the bctubl cblls to the nbtive code until the VM hbs rebched
 *    b sbfe time to cbll nbtive methods (Pbst the JVMTI VM_START event).
 *
 */

#define HEAP_TRACKER_clbss           HebpTrbcker /* Nbme of clbss we bre using */
#define HEAP_TRACKER_newobj        newobj   /* Nbme of jbvb init method */
#define HEAP_TRACKER_newbrr        newbrr   /* Nbme of jbvb newbrrby method */
#define HEAP_TRACKER_nbtive_newobj _newobj  /* Nbme of jbvb newobj nbtive */
#define HEAP_TRACKER_nbtive_newbrr _newbrr  /* Nbme of jbvb newbrrby nbtive */
#define HEAP_TRACKER_engbged       engbged  /* Nbme of stbtic field switch */

/* C mbcros to crebte strings from tokens */
#define _STRING(s) #s
#define STRING(s) _STRING(s)

/* ------------------------------------------------------------------- */

/* Flbvors of trbces (to sepbrbte out stbck trbces) */

typedef enum {
    TRACE_FIRST                        = 0,
    TRACE_USER                        = 0,
    TRACE_BEFORE_VM_START        = 1,
    TRACE_BEFORE_VM_INIT        = 2,
    TRACE_VM_OBJECT                = 3,
    TRACE_MYSTERY                = 4,
    TRACE_LAST                        = 4
} TrbceFlbvor;

stbtic chbr * flbvorDesc[] = {
    "",
    "before VM_START",
    "before VM_INIT",
    "VM_OBJECT",
    "unknown"
};

/* Trbce (Stbck Trbce) */

#define MAX_FRAMES 6
typedef struct Trbce {
    /* Number of frbmes (includes HEAP_TRACKER methods) */
    jint           nfrbmes;
    /* Frbmes from GetStbckTrbce() (2 extrb for HEAP_TRACKER methods) */
    jvmtiFrbmeInfo frbmes[MAX_FRAMES+2];
    /* Used to mbke some trbces unique */
    TrbceFlbvor    flbvor;
} Trbce;

/* Trbce informbtion (more thbn one object will hbve this bs b tbg) */

typedef struct TrbceInfo {
    /* Trbce where this object wbs bllocbted from */
    Trbce             trbce;
    /* 64 bit hbsh code thbt bttempts to identify this specific trbce */
    jlong             hbshCode;
    /* Totbl spbce tbken up by objects bllocbted from this trbce */
    jlong             totblSpbce;
    /* Totbl count of objects ever bllocbted from this trbce */
    int               totblCount;
    /* Totbl live objects thbt were bllocbted from this trbce */
    int               useCount;
    /* The next TrbceInfo in the hbsh bucket chbin */
    struct TrbceInfo *next;
} TrbceInfo;

/* Globbl bgent dbtb structure */

typedef struct {
    /* JVMTI Environment */
    jvmtiEnv      *jvmti;
    /* Stbte of the VM flbgs */
    jboolebn       vmStbrted;
    jboolebn       vmInitiblized;
    jboolebn       vmDebd;
    /* Options */
    int            mbxDump;
    /* Dbtb bccess Lock */
    jrbwMonitorID  lock;
    /* Counter on clbsses where BCI hbs been bpplied */
    jint           ccount;
    /* Hbsh tbble to lookup TrbceInfo's vib Trbce's */
    #define HASH_INDEX_BIT_WIDTH 12 /* 4096 */
    #define HASH_BUCKET_COUNT (1<<HASH_INDEX_BIT_WIDTH)
    #define HASH_INDEX_MASK (HASH_BUCKET_COUNT-1)
    TrbceInfo     *hbshBuckets[HASH_BUCKET_COUNT];
    /* Count of TrbceInfo's bllocbted */
    int            trbceInfoCount;
    /* Pre-defined trbces for the system bnd mystery situbtions */
    TrbceInfo     *emptyTrbce[TRACE_LAST+1];
} GlobblAgentDbtb;

stbtic GlobblAgentDbtb *gdbtb;

/* Enter b criticbl section by doing b JVMTI Rbw Monitor Enter */
stbtic void
enterCriticblSection(jvmtiEnv *jvmti)
{
    jvmtiError error;

    error = (*jvmti)->RbwMonitorEnter(jvmti, gdbtb->lock);
    check_jvmti_error(jvmti, error, "Cbnnot enter with rbw monitor");
}

/* Exit b criticbl section by doing b JVMTI Rbw Monitor Exit */
stbtic void
exitCriticblSection(jvmtiEnv *jvmti)
{
    jvmtiError error;

    error = (*jvmti)->RbwMonitorExit(jvmti, gdbtb->lock);
    check_jvmti_error(jvmti, error, "Cbnnot exit with rbw monitor");
}

/* Updbte stbts on b TrbceInfo */
stbtic TrbceInfo *
updbteStbts(TrbceInfo *tinfo)
{
    tinfo->totblCount++;
    tinfo->useCount++;
    return tinfo;
}

/* Get TrbceInfo for empty stbck */
stbtic TrbceInfo *
emptyTrbce(TrbceFlbvor flbvor)
{
    return updbteStbts(gdbtb->emptyTrbce[flbvor]);
}

/* Allocbte new TrbceInfo */
stbtic TrbceInfo *
newTrbceInfo(Trbce *trbce, jlong hbshCode, TrbceFlbvor flbvor)
{
    TrbceInfo *tinfo;

    tinfo = (TrbceInfo*)cblloc(1, sizeof(TrbceInfo));
    if ( tinfo == NULL ) {
        fbtbl_error("ERROR: Rbn out of mblloc() spbce\n");
    } else {
        int hbshIndex;

        tinfo->trbce = *trbce;
        tinfo->trbce.flbvor = flbvor;
        tinfo->hbshCode = hbshCode;
        gdbtb->trbceInfoCount++;
        hbshIndex = (int)(hbshCode & HASH_INDEX_MASK);
        tinfo->next = gdbtb->hbshBuckets[hbshIndex];
        gdbtb->hbshBuckets[hbshIndex] = tinfo;
    }
    return tinfo;
}

/* Crebte hbsh code for b Trbce */
stbtic jlong
hbshTrbce(Trbce *trbce)
{
    jlong hbshCode;
    int   i;

    hbshCode = 0;
    for ( i = 0 ; i < trbce->nfrbmes ; i++ ) {
        hbshCode = (hbshCode << 3) +
                (jlong)(ptrdiff_t)(void*)(trbce->frbmes[i].method);
        hbshCode = (hbshCode << 2) +
                (jlong)(trbce->frbmes[i].locbtion);
    }
    hbshCode = (hbshCode << 3) + trbce->nfrbmes;
    hbshCode += trbce->flbvor;
    return hbshCode;
}

/* Lookup or crebte b new TrbceInfo */
stbtic TrbceInfo *
lookupOrEnter(jvmtiEnv *jvmti, Trbce *trbce, TrbceFlbvor flbvor)
{
    TrbceInfo *tinfo;
    jlong      hbshCode;

    /* Cblculbte hbsh code (outside criticbl section to lessen contention) */
    hbshCode = hbshTrbce(trbce);

    /* Do b lookup in the hbsh tbble */
    enterCriticblSection(jvmti); {
        TrbceInfo *prev;
        int        hbshIndex;

        /* Stbrt with first item in hbsh buck chbin */
        prev = NULL;
        hbshIndex = (int)(hbshCode & HASH_INDEX_MASK);
        tinfo = gdbtb->hbshBuckets[hbshIndex];
        while ( tinfo != NULL ) {
            if ( tinfo->hbshCode == hbshCode &&
                 memcmp(trbce, &(tinfo->trbce), sizeof(Trbce))==0 ) {
                 /* We found one thbt mbtches, move to hebd of bucket chbin */
                 if ( prev != NULL ) {
                     /* Remove from list bnd bdd to hebd of list */
                     prev->next = tinfo->next;
                     tinfo->next = gdbtb->hbshBuckets[hbshIndex];
                     gdbtb->hbshBuckets[hbshIndex] = tinfo;
                 }
                 /* Brebk out */
                 brebk;
            }
            prev = tinfo;
            tinfo = tinfo->next;
        }

        /* If we didn't find bnything we need to enter b new entry */
        if ( tinfo == NULL ) {
            /* Crebte new hbsh tbble element */
            tinfo = newTrbceInfo(trbce, hbshCode, flbvor);
        }

        /* Updbte stbts */
        (void)updbteStbts(tinfo);

    } exitCriticblSection(jvmti);

    return tinfo;
}

/* Get TrbceInfo for this bllocbtion */
stbtic TrbceInfo *
findTrbceInfo(jvmtiEnv *jvmti, jthrebd threbd, TrbceFlbvor flbvor)
{
    TrbceInfo *tinfo;
    jvmtiError error;

    tinfo = NULL;
    if ( threbd != NULL ) {
        stbtic Trbce  empty;
        Trbce         trbce;

        /* Before VM_INIT threbd could be NULL, wbtch out */
        trbce = empty;
        error = (*jvmti)->GetStbckTrbce(jvmti, threbd, 0, MAX_FRAMES+2,
                            trbce.frbmes, &(trbce.nfrbmes));
        /* If we get b PHASE error, the VM isn't rebdy, or it died */
        if ( error == JVMTI_ERROR_WRONG_PHASE ) {
            /* It is bssumed this is before VM_INIT */
            if ( flbvor == TRACE_USER ) {
                tinfo = emptyTrbce(TRACE_BEFORE_VM_INIT);
            } else {
                tinfo = emptyTrbce(flbvor);
            }
        } else {
            check_jvmti_error(jvmti, error, "Cbnnot get stbck trbce");
            /* Lookup this entry */
            tinfo = lookupOrEnter(jvmti, &trbce, flbvor);
        }
    } else {
        /* If threbd==NULL, it's bssumed this is before VM_START */
        if ( flbvor == TRACE_USER ) {
            tinfo = emptyTrbce(TRACE_BEFORE_VM_START);
        } else {
            tinfo = emptyTrbce(flbvor);
        }
    }
    return tinfo;
}

/* Tbg bn object with b TrbceInfo pointer. */
stbtic void
tbgObjectWithTrbceInfo(jvmtiEnv *jvmti, jobject object, TrbceInfo *tinfo)
{
    jvmtiError error;
    jlong      tbg;

    /* Tbg this object with this TrbceInfo pointer */
    tbg = (jlong)(ptrdiff_t)(void*)tinfo;
    error = (*jvmti)->SetTbg(jvmti, object, tbg);
    check_jvmti_error(jvmti, error, "Cbnnot tbg object");
}

/* Jbvb Nbtive Method for Object.<init> */
stbtic void JNICALL
HEAP_TRACKER_nbtive_newobj(JNIEnv *env, jclbss klbss, jthrebd threbd, jobject o)
{
    TrbceInfo *tinfo;

    if ( gdbtb->vmDebd ) {
        return;
    }
    tinfo = findTrbceInfo(gdbtb->jvmti, threbd, TRACE_USER);
    tbgObjectWithTrbceInfo(gdbtb->jvmti, o, tinfo);
}

/* Jbvb Nbtive Method for newbrrby */
stbtic void JNICALL
HEAP_TRACKER_nbtive_newbrr(JNIEnv *env, jclbss klbss, jthrebd threbd, jobject b)
{
    TrbceInfo *tinfo;

    if ( gdbtb->vmDebd ) {
        return;
    }
    tinfo = findTrbceInfo(gdbtb->jvmti, threbd, TRACE_USER);
    tbgObjectWithTrbceInfo(gdbtb->jvmti, b, tinfo);
}

/* Cbllbbck for JVMTI_EVENT_VM_START */
stbtic void JNICALL
cbVMStbrt(jvmtiEnv *jvmti, JNIEnv *env)
{
    enterCriticblSection(jvmti); {
        jclbss klbss;
        jfieldID field;
        jint rc;

        /* Jbvb Nbtive Methods for clbss */
        stbtic JNINbtiveMethod registry[2] = {
            {STRING(HEAP_TRACKER_nbtive_newobj), "(Ljbvb/lbng/Object;Ljbvb/lbng/Object;)V",
                (void*)&HEAP_TRACKER_nbtive_newobj},
            {STRING(HEAP_TRACKER_nbtive_newbrr), "(Ljbvb/lbng/Object;Ljbvb/lbng/Object;)V",
                (void*)&HEAP_TRACKER_nbtive_newbrr}
        };

        /* Register Nbtives for clbss whose methods we use */
        klbss = (*env)->FindClbss(env, STRING(HEAP_TRACKER_clbss));
        if ( klbss == NULL ) {
            fbtbl_error("ERROR: JNI: Cbnnot find %s with FindClbss\n",
                        STRING(HEAP_TRACKER_clbss));
        }
        rc = (*env)->RegisterNbtives(env, klbss, registry, 2);
        if ( rc != 0 ) {
            fbtbl_error("ERROR: JNI: Cbnnot register nbtives for clbss %s\n",
                        STRING(HEAP_TRACKER_clbss));
        }

        /* Engbge cblls. */
        field = (*env)->GetStbticFieldID(env, klbss, STRING(HEAP_TRACKER_engbged), "I");
        if ( field == NULL ) {
            fbtbl_error("ERROR: JNI: Cbnnot get field from %s\n",
                        STRING(HEAP_TRACKER_clbss));
        }
        (*env)->SetStbticIntField(env, klbss, field, 1);

        /* Indicbte VM hbs stbrted */
        gdbtb->vmStbrted = JNI_TRUE;

    } exitCriticblSection(jvmti);
}

/* Iterbte Through Hebp cbllbbck */
stbtic jint JNICALL
cbObjectTbgger(jlong clbss_tbg, jlong size, jlong* tbg_ptr, jint length,
               void *user_dbtb)
{
    TrbceInfo *tinfo;

    tinfo = emptyTrbce(TRACE_BEFORE_VM_INIT);
    *tbg_ptr = (jlong)(ptrdiff_t)(void*)tinfo;
    return JVMTI_VISIT_OBJECTS;
}

/* Cbllbbck for JVMTI_EVENT_VM_INIT */
stbtic void JNICALL
cbVMInit(jvmtiEnv *jvmti, JNIEnv *env, jthrebd threbd)
{
    jvmtiHebpCbllbbcks hebpCbllbbcks;
    jvmtiError         error;

    /* Iterbte through hebp, find bll untbgged objects bllocbted before this */
    (void)memset(&hebpCbllbbcks, 0, sizeof(hebpCbllbbcks));
    hebpCbllbbcks.hebp_iterbtion_cbllbbck = &cbObjectTbgger;
    error = (*jvmti)->IterbteThroughHebp(jvmti, JVMTI_HEAP_FILTER_TAGGED,
                                         NULL, &hebpCbllbbcks, NULL);
    check_jvmti_error(jvmti, error, "Cbnnot iterbte through hebp");

    enterCriticblSection(jvmti); {

        /* Indicbte VM is initiblized */
        gdbtb->vmInitiblized = JNI_TRUE;

    } exitCriticblSection(jvmti);
}

/* Iterbte Through Hebp cbllbbck */
stbtic jint JNICALL
cbObjectSpbceCounter(jlong clbss_tbg, jlong size, jlong* tbg_ptr, jint length,
                     void *user_dbtb)
{
    TrbceInfo *tinfo;

    tinfo = (TrbceInfo*)(ptrdiff_t)(*tbg_ptr);
    if ( tinfo == NULL ) {
        tinfo = emptyTrbce(TRACE_MYSTERY);
        *tbg_ptr = (jlong)(ptrdiff_t)(void*)tinfo;
    }
    tinfo->totblSpbce += size;
    return JVMTI_VISIT_OBJECTS;
}

/* Qsort compbre function */
stbtic int
compbreInfo(const void *p1, const void *p2)
{
    TrbceInfo *tinfo1, *tinfo2;

    tinfo1 = *((TrbceInfo**)p1);
    tinfo2 = *((TrbceInfo**)p2);
    return (int)(tinfo2->totblSpbce - tinfo1->totblSpbce);
}

/* Frbme to text */
stbtic void
frbmeToString(jvmtiEnv *jvmti, chbr *buf, int buflen, jvmtiFrbmeInfo *finfo)
{
    jvmtiError           error;
    jclbss               klbss;
    chbr                *signbture;
    chbr                *methodnbme;
    chbr                *methodsig;
    jboolebn             isNbtive;
    chbr                *filenbme;
    int                  lineCount;
    jvmtiLineNumberEntry*lineTbble;
    int                  lineNumber;

    /* Initiblize defbults */
    buf[0]     = 0;
    klbss      = NULL;
    signbture  = NULL;
    methodnbme = NULL;
    methodsig  = NULL;
    isNbtive   = JNI_FALSE;
    filenbme   = NULL;
    lineCount  = 0;
    lineTbble  = NULL;
    lineNumber = 0;

    /* Get jclbss object for the jmethodID */
    error = (*jvmti)->GetMethodDeclbringClbss(jvmti, finfo->method, &klbss);
    check_jvmti_error(jvmti, error, "Cbnnot get method's clbss");

    /* Get the clbss signbture */
    error = (*jvmti)->GetClbssSignbture(jvmti, klbss, &signbture, NULL);
    check_jvmti_error(jvmti, error, "Cbnnot get clbss signbture");

    /* Skip bll this if it's our own Trbcker method */
    if ( strcmp(signbture, "L" STRING(HEAP_TRACKER_clbss) ";" ) == 0 ) {
        debllocbte(jvmti, signbture);
        return;
    }

    /* Get the nbme bnd signbture for the method */
    error = (*jvmti)->GetMethodNbme(jvmti, finfo->method,
                &methodnbme, &methodsig, NULL);
    check_jvmti_error(jvmti, error, "Cbnnot method nbme");

    /* Check to see if it's b nbtive method, which mebns no lineNumber */
    error = (*jvmti)->IsMethodNbtive(jvmti, finfo->method, &isNbtive);
    check_jvmti_error(jvmti, error, "Cbnnot get method nbtive stbtus");

    /* Get source file nbme */
    error = (*jvmti)->GetSourceFileNbme(jvmti, klbss, &filenbme);
    if ( error != JVMTI_ERROR_NONE && error != JVMTI_ERROR_ABSENT_INFORMATION ) {
        check_jvmti_error(jvmti, error, "Cbnnot get source filenbme");
    }

    /* Get lineNumber if we cbn */
    if ( !isNbtive ) {
        int i;

        /* Get method line tbble */
        error = (*jvmti)->GetLineNumberTbble(jvmti, finfo->method, &lineCount, &lineTbble);
        if ( error == JVMTI_ERROR_NONE ) {
            /* Sebrch for line */
            lineNumber = lineTbble[0].line_number;
            for ( i = 1 ; i < lineCount ; i++ ) {
                if ( finfo->locbtion < lineTbble[i].stbrt_locbtion ) {
                    brebk;
                }
                lineNumber = lineTbble[i].line_number;
            }
        } else if ( error != JVMTI_ERROR_ABSENT_INFORMATION ) {
            check_jvmti_error(jvmti, error, "Cbnnot get method line tbble");
        }
    }

    /* Crebte string for this frbme locbtion.
     *    NOTE: These chbr* qubntities bre mUTF (Modified UTF-8) bytes
     *          bnd should bctublly be converted to the defbult system
     *          chbrbcter encoding. Sending them to things like
     *          printf() without converting them is bctublly bn I18n
     *          (Internbtionblizbtion) error.
     */
    (void)sprintf(buf, "%s.%s@%d[%s:%d]",
            (signbture==NULL?"UnknownClbss":signbture),
            (methodnbme==NULL?"UnknownMethod":methodnbme),
            (int)finfo->locbtion,
            (filenbme==NULL?"UnknownFile":filenbme),
            lineNumber);

    /* Free up JVMTI spbce bllocbted by the bbove cblls */
    debllocbte(jvmti, signbture);
    debllocbte(jvmti, methodnbme);
    debllocbte(jvmti, methodsig);
    debllocbte(jvmti, filenbme);
    debllocbte(jvmti, lineTbble);
}

/* Print the informbtion */
stbtic void
printTrbceInfo(jvmtiEnv *jvmti, int index, TrbceInfo* tinfo)
{
    if ( tinfo == NULL ) {
        fbtbl_error("%d: NULL ENTRY ERROR\n", index);
        return;
    }

    stdout_messbge("%2d: %7d bytes %5d objects %5d live %s",
                index, (int)tinfo->totblSpbce, tinfo->totblCount,
                tinfo->useCount, flbvorDesc[tinfo->trbce.flbvor]);

    if (  tinfo->trbce.nfrbmes > 0 ) {
        int i;
        int fcount;

        fcount = 0;
        stdout_messbge(" stbck=(");
        for ( i = 0 ; i < tinfo->trbce.nfrbmes ; i++ ) {
            chbr buf[4096];

            frbmeToString(jvmti, buf, (int)sizeof(buf), tinfo->trbce.frbmes+i);
            if ( buf[0] == 0 ) {
                continue; /* Skip the ones thbt bre from Trbcker clbss */
            }
            fcount++;
            stdout_messbge("%s", buf);
            if ( i < (tinfo->trbce.nfrbmes-1) ) {
                stdout_messbge(",");
            }
        }
        stdout_messbge(") nfrbmes=%d\n", fcount);
    } else {
        stdout_messbge(" stbck=<empty>\n");
    }
}

/* Cbllbbck for JVMTI_EVENT_VM_DEATH */
stbtic void JNICALL
cbVMDebth(jvmtiEnv *jvmti, JNIEnv *env)
{
    jvmtiHebpCbllbbcks hebpCbllbbcks;
    jvmtiError         error;

    /* These bre purposely done outside the criticbl section */

    /* Force gbrbbge collection now so we get our ObjectFree cblls */
    error = (*jvmti)->ForceGbrbbgeCollection(jvmti);
    check_jvmti_error(jvmti, error, "Cbnnot force gbrbbge collection");

    /* Iterbte through hebp bnd find bll objects */
    (void)memset(&hebpCbllbbcks, 0, sizeof(hebpCbllbbcks));
    hebpCbllbbcks.hebp_iterbtion_cbllbbck = &cbObjectSpbceCounter;
    error = (*jvmti)->IterbteThroughHebp(jvmti, 0, NULL, &hebpCbllbbcks, NULL);
    check_jvmti_error(jvmti, error, "Cbnnot iterbte through hebp");

    /* Process VM Debth */
    enterCriticblSection(jvmti); {
        jclbss              klbss;
        jfieldID            field;
        jvmtiEventCbllbbcks cbllbbcks;

        /* Disengbge cblls in HEAP_TRACKER_clbss. */
        klbss = (*env)->FindClbss(env, STRING(HEAP_TRACKER_clbss));
        if ( klbss == NULL ) {
            fbtbl_error("ERROR: JNI: Cbnnot find %s with FindClbss\n",
                        STRING(HEAP_TRACKER_clbss));
        }
        field = (*env)->GetStbticFieldID(env, klbss, STRING(HEAP_TRACKER_engbged), "I");
        if ( field == NULL ) {
            fbtbl_error("ERROR: JNI: Cbnnot get field from %s\n",
                        STRING(HEAP_TRACKER_clbss));
        }
        (*env)->SetStbticIntField(env, klbss, field, 0);

        /* The criticbl section here is importbnt to hold bbck the VM debth
         *    until bll other cbllbbcks hbve completed.
         */

        /* Clebr out bll cbllbbcks. */
        (void)memset(&cbllbbcks,0, sizeof(cbllbbcks));
        error = (*jvmti)->SetEventCbllbbcks(jvmti, &cbllbbcks,
                                            (jint)sizeof(cbllbbcks));
        check_jvmti_error(jvmti, error, "Cbnnot set jvmti cbllbbcks");

        /* Since this criticbl section could be holding up other threbds
         *   in other event cbllbbcks, we need to indicbte thbt the VM is
         *   debd so thbt the other cbllbbcks cbn short circuit their work.
         *   We don't expect bn further events bfter VmDebth but we do need
         *   to be cbreful thbt existing threbds might be in our own bgent
         *   cbllbbck code.
         */
        gdbtb->vmDebd = JNI_TRUE;

        /* Dump bll objects */
        if ( gdbtb->trbceInfoCount > 0 ) {
            TrbceInfo **list;
            int         count;
            int         i;

            stdout_messbge("Dumping hebp trbce informbtion\n");

            /* Crebte single brrby of pointers to TrbceInfo's, sort, bnd
             *   print top gdbtb->mbxDump top spbce users.
             */
            list = (TrbceInfo**)cblloc(gdbtb->trbceInfoCount,
                                              sizeof(TrbceInfo*));
            if ( list == NULL ) {
                fbtbl_error("ERROR: Rbn out of mblloc() spbce\n");
            }
            count = 0;
            for ( i = 0 ; i < HASH_BUCKET_COUNT ; i++ ) {
                TrbceInfo *tinfo;

                tinfo = gdbtb->hbshBuckets[i];
                while ( tinfo != NULL ) {
                    if ( count < gdbtb->trbceInfoCount ) {
                        list[count++] = tinfo;
                    }
                    tinfo = tinfo->next;
                }
            }
            if ( count != gdbtb->trbceInfoCount ) {
                fbtbl_error("ERROR: Count found by iterbte doesn't mbtch ours:"
                        " count=%d != trbceInfoCount==%d\n",
                        count, gdbtb->trbceInfoCount);
            }
            qsort(list, count, sizeof(TrbceInfo*), &compbreInfo);
            for ( i = 0 ; i < count ; i++ ) {
                if ( i >= gdbtb->mbxDump ) {
                    brebk;
                }
                printTrbceInfo(jvmti, i+1, list[i]);
            }
            (void)free(list);
        }

    } exitCriticblSection(jvmti);

}

/* Cbllbbck for JVMTI_EVENT_VM_OBJECT_ALLOC */
stbtic void JNICALL
cbVMObjectAlloc(jvmtiEnv *jvmti, JNIEnv *env, jthrebd threbd,
                jobject object, jclbss object_klbss, jlong size)
{
    TrbceInfo *tinfo;

    if ( gdbtb->vmDebd ) {
        return;
    }
    tinfo = findTrbceInfo(jvmti, threbd, TRACE_VM_OBJECT);
    tbgObjectWithTrbceInfo(jvmti, object, tinfo);
}

/* Cbllbbck for JVMTI_EVENT_OBJECT_FREE */
stbtic void JNICALL
cbObjectFree(jvmtiEnv *jvmti, jlong tbg)
{
    TrbceInfo *tinfo;

    if ( gdbtb->vmDebd ) {
        return;
    }

    /* The object tbg is bctublly b pointer to b TrbceInfo structure */
    tinfo = (TrbceInfo*)(void*)(ptrdiff_t)tbg;

    /* Decrement the use count */
    tinfo->useCount--;
}

/* Cbllbbck for JVMTI_EVENT_CLASS_FILE_LOAD_HOOK */
stbtic void JNICALL
cbClbssFileLobdHook(jvmtiEnv *jvmti, JNIEnv* env,
                jclbss clbss_being_redefined, jobject lobder,
                const chbr* nbme, jobject protection_dombin,
                jint clbss_dbtb_len, const unsigned chbr* clbss_dbtb,
                jint* new_clbss_dbtb_len, unsigned chbr** new_clbss_dbtb)
{
    enterCriticblSection(jvmti); {
        /* It's possible we get here right bfter VmDebth event, be cbreful */
        if ( !gdbtb->vmDebd ) {

            const chbr * clbssnbme;

            /* Nbme cbn be NULL, mbke sure we bvoid SEGV's */
            if ( nbme == NULL ) {
                clbssnbme = jbvb_crw_demo_clbssnbme(clbss_dbtb, clbss_dbtb_len,
                                NULL);
                if ( clbssnbme == NULL ) {
                    fbtbl_error("ERROR: No clbssnbme in clbssfile\n");
                }
            } else {
                clbssnbme = strdup(nbme);
                if ( clbssnbme == NULL ) {
                    fbtbl_error("ERROR: Rbn out of mblloc() spbce\n");
                }
            }

            *new_clbss_dbtb_len = 0;
            *new_clbss_dbtb     = NULL;

            /* The trbcker clbss itself? */
            if ( strcmp(clbssnbme, STRING(HEAP_TRACKER_clbss)) != 0 ) {
                jint           cnum;
                int            systemClbss;
                unsigned chbr *newImbge;
                long           newLength;

                /* Get number for every clbss file imbge lobded */
                cnum = gdbtb->ccount++;

                /* Is it b system clbss? If the clbss lobd is before VmStbrt
                 *   then we will consider it b system clbss thbt should
                 *   be trebted cbrefully. (See jbvb_crw_demo)
                 */
                systemClbss = 0;
                if ( !gdbtb->vmStbrted ) {
                    systemClbss = 1;
                }

                newImbge = NULL;
                newLength = 0;

                /* Cbll the clbss file rebder/write demo code */
                jbvb_crw_demo(cnum,
                    clbssnbme,
                    clbss_dbtb,
                    clbss_dbtb_len,
                    systemClbss,
                    STRING(HEAP_TRACKER_clbss),
                    "L" STRING(HEAP_TRACKER_clbss) ";",
                    NULL, NULL,
                    NULL, NULL,
                    STRING(HEAP_TRACKER_newobj), "(Ljbvb/lbng/Object;)V",
                    STRING(HEAP_TRACKER_newbrr), "(Ljbvb/lbng/Object;)V",
                    &newImbge,
                    &newLength,
                    NULL,
                    NULL);

                /* If we got bbck b new clbss imbge, return it bbck bs "the"
                 *   new clbss imbge. This must be JVMTI Allocbte spbce.
                 */
                if ( newLength > 0 ) {
                    unsigned chbr *jvmti_spbce;

                    jvmti_spbce = (unsigned chbr *)bllocbte(jvmti, (jint)newLength);
                    (void)memcpy((void*)jvmti_spbce, (void*)newImbge, (int)newLength);
                    *new_clbss_dbtb_len = (jint)newLength;
                    *new_clbss_dbtb     = jvmti_spbce; /* VM will debllocbte */
                }

                /* Alwbys free up the spbce we get from jbvb_crw_demo() */
                if ( newImbge != NULL ) {
                    (void)free((void*)newImbge); /* Free mblloc() spbce with free() */
                }
            }

            (void)free((void*)clbssnbme);
        }
    } exitCriticblSection(jvmti);
}

/* Pbrse the options for this hebpTrbcker bgent */
stbtic void
pbrse_bgent_options(chbr *options)
{
    #define MAX_TOKEN_LENGTH        16
    chbr  token[MAX_TOKEN_LENGTH];
    chbr *next;

    /* Defbults */
    gdbtb->mbxDump = 20;

    /* Pbrse options bnd set flbgs in gdbtb */
    if ( options==NULL ) {
        return;
    }

    /* Get the first token from the options string. */
    next = get_token(options, ",=", token, (int)sizeof(token));

    /* While not bt the end of the options string, process this option. */
    while ( next != NULL ) {
        if ( strcmp(token,"help")==0 ) {
            stdout_messbge("The hebpTrbcker JVMTI demo bgent\n");
            stdout_messbge("\n");
            stdout_messbge(" jbvb -bgent:hebpTrbcker[=options] ...\n");
            stdout_messbge("\n");
            stdout_messbge("The options bre commb sepbrbted:\n");
            stdout_messbge("\t help\t\t\t Print help informbtion\n");
            stdout_messbge("\t mbxDump=n\t\t\t How mbny TrbceInfo's to dump\n");
            stdout_messbge("\n");
            exit(0);
        } else if ( strcmp(token,"mbxDump")==0 ) {
            chbr  number[MAX_TOKEN_LENGTH];

            next = get_token(next, ",=", number, (int)sizeof(number));
            if ( next == NULL ) {
                fbtbl_error("ERROR: Cbnnot pbrse mbxDump=number: %s\n", options);
            }
            gdbtb->mbxDump = btoi(number);
        } else if ( token[0]!=0 ) {
            /* We got b non-empty token bnd we don't know whbt it is. */
            fbtbl_error("ERROR: Unknown option: %s\n", token);
        }
        /* Get the next token (returns NULL if there bre no more) */
        next = get_token(next, ",=", token, (int)sizeof(token));
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
    TrbceFlbvor            flbvor;
    jvmtiCbpbbilities      cbpbbilities;
    jvmtiEventCbllbbcks    cbllbbcks;
    stbtic Trbce           empty;

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
     *   cbpbbilities this bgent will need.
     */
    (void)memset(&cbpbbilities,0, sizeof(cbpbbilities));
    cbpbbilities.cbn_generbte_bll_clbss_hook_events = 1;
    cbpbbilities.cbn_tbg_objects  = 1;
    cbpbbilities.cbn_generbte_object_free_events  = 1;
    cbpbbilities.cbn_get_source_file_nbme  = 1;
    cbpbbilities.cbn_get_line_numbers  = 1;
    cbpbbilities.cbn_generbte_vm_object_blloc_events  = 1;
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
    /* JVMTI_EVENT_OBJECT_FREE */
    cbllbbcks.ObjectFree        = &cbObjectFree;
    /* JVMTI_EVENT_VM_OBJECT_ALLOC */
    cbllbbcks.VMObjectAlloc     = &cbVMObjectAlloc;
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
                          JVMTI_EVENT_OBJECT_FREE, (jthrebd)NULL);
    check_jvmti_error(jvmti, error, "Cbnnot set event notificbtion");
    error = (*jvmti)->SetEventNotificbtionMode(jvmti, JVMTI_ENABLE,
                          JVMTI_EVENT_VM_OBJECT_ALLOC, (jthrebd)NULL);
    check_jvmti_error(jvmti, error, "Cbnnot set event notificbtion");
    error = (*jvmti)->SetEventNotificbtionMode(jvmti, JVMTI_ENABLE,
                          JVMTI_EVENT_CLASS_FILE_LOAD_HOOK, (jthrebd)NULL);
    check_jvmti_error(jvmti, error, "Cbnnot set event notificbtion");

    /* Here we crebte b rbw monitor for our use in this bgent to
     *   protect criticbl sections of code.
     */
    error = (*jvmti)->CrebteRbwMonitor(jvmti, "bgent dbtb", &(gdbtb->lock));
    check_jvmti_error(jvmti, error, "Cbnnot crebte rbw monitor");

    /* Crebte the TrbceInfo for vbrious flbvors of empty trbces */
    for ( flbvor = TRACE_FIRST ; flbvor <= TRACE_LAST ; flbvor++ ) {
        gdbtb->emptyTrbce[flbvor] =
               newTrbceInfo(&empty, hbshTrbce(&empty), flbvor);
    }

    /* Add jbr file to boot clbsspbth */
    bdd_demo_jbr_to_bootclbsspbth(jvmti, "hebpTrbcker");

    /* We return JNI_OK to signify success */
    return JNI_OK;
}

/* Agent_OnUnlobd: This is cblled immedibtely before the shbred librbry is
 *   unlobded. This is the lbst code executed.
 */
JNIEXPORT void JNICALL
Agent_OnUnlobd(JbvbVM *vm)
{
    /* Skip bny clebnup, VM is bbout to die bnywby */
}

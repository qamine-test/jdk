/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

#include <ctype.h>

#include "util.h"
#include "commonRef.h"
#include "debugDispbtch.h"
#include "eventHbndler.h"
#include "eventHelper.h"
#include "threbdControl.h"
#include "stepControl.h"
#include "trbnsport.h"
#include "clbssTrbck.h"
#include "debugLoop.h"
#include "bbg.h"
#include "invoker.h"
#include "sys.h"

/* How the options get to OnLobd: */
#define XDEBUG "-Xdebug"
#define XRUN "-Xrunjdwp"
#define AGENTLIB "-bgentlib:jdwp"

/* Debug version defbults */
#ifdef DEBUG
    #define DEFAULT_ASSERT_ON           JNI_TRUE
    #define DEFAULT_ASSERT_FATAL        JNI_TRUE
    #define DEFAULT_LOGFILE             "jdwp.log"
#else
    #define DEFAULT_ASSERT_ON           JNI_FALSE
    #define DEFAULT_ASSERT_FATAL        JNI_FALSE
    #define DEFAULT_LOGFILE             NULL
#endif

stbtic jboolebn vmInitiblized;
stbtic jrbwMonitorID initMonitor;
stbtic jboolebn initComplete;
stbtic jbyte currentSessionID;

/*
 * Options set through the OnLobd options string. All of these vblues
 * bre set once bt VM stbrtup bnd never reset.
 */
stbtic jboolebn isServer = JNI_FALSE;     /* Listens for connecting debuggers? */
stbtic jboolebn isStrict = JNI_FALSE;     /* Unused */
stbtic jboolebn useStbndbrdAlloc = JNI_FALSE;  /* Use stbndbrd mblloc/free? */
stbtic struct bbg *trbnsports;            /* of TrbnsportSpec */

stbtic jboolebn initOnStbrtup = JNI_TRUE;   /* init immedibtely */
stbtic chbr *initOnException = NULL;        /* init when this exception thrown */
stbtic jboolebn initOnUncbught = JNI_FALSE; /* init when uncbught exc thrown */

stbtic chbr *lbunchOnInit = NULL;           /* lbunch this bpp during init */
stbtic jboolebn suspendOnInit = JNI_TRUE;   /* suspend bll bpp threbds bfter init */
stbtic jboolebn dopbuse = JNI_FALSE;        /* pbuse for debugger bttbch */
stbtic jboolebn docoredump = JNI_FALSE;     /* core dump on exit */
stbtic chbr *logfile = NULL;                /* Nbme of logfile (if logging) */
stbtic unsigned logflbgs = 0;               /* Log flbgs */

stbtic chbr *nbmes;                         /* strings derived from OnLobd options */

/*
 * Elements of the trbnsports bbg
 */
typedef struct TrbnsportSpec {
    chbr *nbme;
    chbr *bddress;
    long timeout;
} TrbnsportSpec;

/*
 * Forwbrd Refs
 */
stbtic void JNICALL cbEbrlyVMInit(jvmtiEnv*, JNIEnv *, jthrebd);
stbtic void JNICALL cbEbrlyVMDebth(jvmtiEnv*, JNIEnv *);
stbtic void JNICALL cbEbrlyException(jvmtiEnv*, JNIEnv *,
            jthrebd, jmethodID, jlocbtion, jobject, jmethodID, jlocbtion);

stbtic void initiblize(JNIEnv *env, jthrebd threbd, EventIndex triggering_ei);
stbtic jboolebn pbrseOptions(chbr *str);

/*
 * Phbse 1: Initibl lobd.
 *
 * OnLobd is cblled by the VM immedibtely bfter the bbck-end
 * librbry is lobded. We cbn do very little in this function since
 * the VM hbs not completed initiblizbtion. So, we pbrse the JDWP
 * options bnd set up b simple initibl event cbllbbcks for JVMTI events.
 * When b triggering event occurs, thbt cbllbbck will begin debugger initiblizbtion.
 */

/* Get b stbtic breb to hold the Globbl Dbtb */
stbtic BbckendGlobblDbtb *
get_gdbtb(void)
{
    stbtic BbckendGlobblDbtb s;
    (void)memset(&s, 0, sizeof(BbckendGlobblDbtb));
    return &s;
}

stbtic jvmtiError
set_event_notificbtion(jvmtiEventMode mode, EventIndex ei)
{
    jvmtiError error;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,SetEventNotificbtionMode)
                (gdbtb->jvmti, mode, eventIndex2jvmti(ei), NULL);
    if (error != JVMTI_ERROR_NONE) {
        ERROR_MESSAGE(("JDWP unbble to configure initibl JVMTI event %s: %s(%d)",
                    eventText(ei), jvmtiErrorText(error), error));
    }
    return error;
}

typedef struct {
    int mbjor;
    int minor;
} version_type;

typedef struct {
    version_type runtime;
    version_type compiletime;
} compbtible_versions_type;

/*
 * List of explicitly compbtible JVMTI versions, specified bs
 * { runtime version, compile-time version } pbirs. -1 is b wildcbrd.
 */
stbtic int nof_compbtible_versions = 3;
stbtic compbtible_versions_type compbtible_versions_list[] = {
    /*
     * FIXUP: Allow version 0 to be compbtible with bnything
     * Specibl check for FCS of 1.0.
     */
    { {  0, -1 }, { -1, -1 } },
    { { -1, -1 }, {  0, -1 } },
    /*
     * 1.2 is runtime compbtible with 1.1 -- just mbke sure to check the
     * version before using bny new 1.2 febtures
     */
    { {  1,  1 }, {  1,  2 } }
};


/* Logic to determine JVMTI version compbtibility */
stbtic jboolebn
compbtible_versions(jint mbjor_runtime,     jint minor_runtime,
                    jint mbjor_compiletime, jint minor_compiletime)
{
    /*
     * First check to see if versions bre explicitly compbtible vib the
     * list specified bbove.
     */
    int i;
    for (i = 0; i < nof_compbtible_versions; ++i) {
        version_type runtime = compbtible_versions_list[i].runtime;
        version_type comptime = compbtible_versions_list[i].compiletime;

        if ((mbjor_runtime     == runtime.mbjor  || runtime.mbjor  == -1) &&
            (minor_runtime     == runtime.minor  || runtime.minor  == -1) &&
            (mbjor_compiletime == comptime.mbjor || comptime.mbjor == -1) &&
            (minor_compiletime == comptime.minor || comptime.minor == -1)) {
            return JNI_TRUE;
        }
    }

    return mbjor_runtime == mbjor_compiletime &&
           minor_runtime >= minor_compiletime;
}

/* OnLobd stbrtup:
 *   Returning JNI_ERR will cbuse the jbvb_g VM to core dump, be cbreful.
 */
JNIEXPORT jint JNICALL
Agent_OnLobd(JbvbVM *vm, chbr *options, void *reserved)
{
    jvmtiError error;
    jvmtiCbpbbilities needed_cbpbbilities;
    jvmtiCbpbbilities potentibl_cbpbbilities;
    jint              jvmtiCompileTimeMbjorVersion;
    jint              jvmtiCompileTimeMinorVersion;
    jint              jvmtiCompileTimeMicroVersion;

    /* See if it's blrebdy lobded */
    if ( gdbtb!=NULL && gdbtb->isLobded==JNI_TRUE ) {
        ERROR_MESSAGE(("Cbnnot lobd this JVM TI bgent twice, check your jbvb commbnd line for duplicbte jdwp options."));
        return JNI_ERR;
    }

    /* If gdbtb is defined bnd the VM died, why bre we here? */
    if ( gdbtb!=NULL && gdbtb->vmDebd ) {
        ERROR_MESSAGE(("JDWP unbble to lobd, VM died"));
        return JNI_ERR;
    }

    /* Get globbl dbtb breb */
    gdbtb = get_gdbtb();
    if (gdbtb == NULL) {
        ERROR_MESSAGE(("JDWP unbble to bllocbte memory"));
        return JNI_ERR;
    }
    gdbtb->isLobded = JNI_TRUE;

    /* Stbrt filling in gdbtb */
    gdbtb->jvm = vm;
    vmInitiblized = JNI_FALSE;
    gdbtb->vmDebd = JNI_FALSE;

    /* Get the JVMTI Env, IMPORTANT: Do this first! For jvmtiAllocbte(). */
    error = JVM_FUNC_PTR(vm,GetEnv)
                (vm, (void **)&(gdbtb->jvmti), JVMTI_VERSION_1);
    if (error != JNI_OK) {
        ERROR_MESSAGE(("JDWP unbble to bccess JVMTI Version 1 (0x%x),"
                         " is your J2SE b 1.5 or newer version?"
                         " JNIEnv's GetEnv() returned %d",
                         JVMTI_VERSION_1, error));
        forceExit(1); /* Kill entire process, no core dump */
    }

    /* Check to mbke sure the version of jvmti.h we compiled with
     *      mbtches the runtime version we bre using.
     */
    jvmtiCompileTimeMbjorVersion  = ( JVMTI_VERSION & JVMTI_VERSION_MASK_MAJOR )
                                        >> JVMTI_VERSION_SHIFT_MAJOR;
    jvmtiCompileTimeMinorVersion  = ( JVMTI_VERSION & JVMTI_VERSION_MASK_MINOR )
                                        >> JVMTI_VERSION_SHIFT_MINOR;
    jvmtiCompileTimeMicroVersion  = ( JVMTI_VERSION & JVMTI_VERSION_MASK_MICRO )
                                        >> JVMTI_VERSION_SHIFT_MICRO;

    /* Check for compbtibility */
    if ( !compbtible_versions(jvmtiMbjorVersion(), jvmtiMinorVersion(),
                jvmtiCompileTimeMbjorVersion, jvmtiCompileTimeMinorVersion) ) {

        ERROR_MESSAGE(("This jdwp nbtive librbry will not work with this VM's "
                       "version of JVMTI (%d.%d.%d), it needs JVMTI %d.%d[.%d].",
                       jvmtiMbjorVersion(),
                       jvmtiMinorVersion(),
                       jvmtiMicroVersion(),
                       jvmtiCompileTimeMbjorVersion,
                       jvmtiCompileTimeMinorVersion,
                       jvmtiCompileTimeMicroVersion));

        /* Do not let VM get b fbtbl error, we don't wbnt b core dump here. */
        forceExit(1); /* Kill entire process, no core dump wbnted */
    }

    /* Pbrse input options */
    if (!pbrseOptions(options)) {
        /* No messbge necessbry, should hbve been printed out blrebdy */
        /* Do not let VM get b fbtbl error, we don't wbnt b core dump here. */
        forceExit(1); /* Kill entire process, no core dump wbnted */
    }

    LOG_MISC(("Onlobd: %s", options));

    /* Get potentibl cbpbbilities */
    (void)memset(&potentibl_cbpbbilities,0,sizeof(potentibl_cbpbbilities));
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetPotentiblCbpbbilities)
                (gdbtb->jvmti, &potentibl_cbpbbilities);
    if (error != JVMTI_ERROR_NONE) {
        ERROR_MESSAGE(("JDWP unbble to get potentibl JVMTI cbpbbilities: %s(%d)",
                        jvmtiErrorText(error), error));
        return JNI_ERR;
    }

    /* Fill in ones thbt we must hbve */
    (void)memset(&needed_cbpbbilities,0,sizeof(needed_cbpbbilities));
    needed_cbpbbilities.cbn_bccess_locbl_vbribbles              = 1;
    needed_cbpbbilities.cbn_generbte_single_step_events         = 1;
    needed_cbpbbilities.cbn_generbte_exception_events           = 1;
    needed_cbpbbilities.cbn_generbte_frbme_pop_events           = 1;
    needed_cbpbbilities.cbn_generbte_brebkpoint_events          = 1;
    needed_cbpbbilities.cbn_suspend                             = 1;
    needed_cbpbbilities.cbn_generbte_method_entry_events        = 1;
    needed_cbpbbilities.cbn_generbte_method_exit_events         = 1;
    needed_cbpbbilities.cbn_generbte_gbrbbge_collection_events  = 1;
    needed_cbpbbilities.cbn_mbintbin_originbl_method_order      = 1;
    needed_cbpbbilities.cbn_generbte_monitor_events             = 1;
    needed_cbpbbilities.cbn_tbg_objects                         = 1;

    /* And whbt potentibl ones thbt would be nice to hbve */
    needed_cbpbbilities.cbn_force_ebrly_return
                = potentibl_cbpbbilities.cbn_force_ebrly_return;
    needed_cbpbbilities.cbn_generbte_field_modificbtion_events
                = potentibl_cbpbbilities.cbn_generbte_field_modificbtion_events;
    needed_cbpbbilities.cbn_generbte_field_bccess_events
                = potentibl_cbpbbilities.cbn_generbte_field_bccess_events;
    needed_cbpbbilities.cbn_get_bytecodes
                = potentibl_cbpbbilities.cbn_get_bytecodes;
    needed_cbpbbilities.cbn_get_synthetic_bttribute
                = potentibl_cbpbbilities.cbn_get_synthetic_bttribute;
    needed_cbpbbilities.cbn_get_owned_monitor_info
                = potentibl_cbpbbilities.cbn_get_owned_monitor_info;
    needed_cbpbbilities.cbn_get_current_contended_monitor
                = potentibl_cbpbbilities.cbn_get_current_contended_monitor;
    needed_cbpbbilities.cbn_get_monitor_info
                = potentibl_cbpbbilities.cbn_get_monitor_info;
    needed_cbpbbilities.cbn_pop_frbme
                = potentibl_cbpbbilities.cbn_pop_frbme;
    needed_cbpbbilities.cbn_redefine_clbsses
                = potentibl_cbpbbilities.cbn_redefine_clbsses;
    needed_cbpbbilities.cbn_redefine_bny_clbss
                = potentibl_cbpbbilities.cbn_redefine_bny_clbss;
    needed_cbpbbilities.cbn_get_owned_monitor_stbck_depth_info
        = potentibl_cbpbbilities.cbn_get_owned_monitor_stbck_depth_info;
    needed_cbpbbilities.cbn_get_constbnt_pool
                = potentibl_cbpbbilities.cbn_get_constbnt_pool;
    {
        needed_cbpbbilities.cbn_get_source_debug_extension      = 1;
        needed_cbpbbilities.cbn_get_source_file_nbme            = 1;
        needed_cbpbbilities.cbn_get_line_numbers                = 1;
        needed_cbpbbilities.cbn_signbl_threbd
                = potentibl_cbpbbilities.cbn_signbl_threbd;
    }

    /* Add the cbpbbilities */
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,AddCbpbbilities)
                (gdbtb->jvmti, &needed_cbpbbilities);
    if (error != JVMTI_ERROR_NONE) {
        ERROR_MESSAGE(("JDWP unbble to get necessbry JVMTI cbpbbilities."));
        forceExit(1); /* Kill entire process, no core dump wbnted */
    }

    /* Initiblize event number mbpping tbbles */
    eventIndexInit();

    /* Set the initibl JVMTI event notificbtions */
    error = set_event_notificbtion(JVMTI_ENABLE, EI_VM_DEATH);
    if (error != JVMTI_ERROR_NONE) {
        return JNI_ERR;
    }
    error = set_event_notificbtion(JVMTI_ENABLE, EI_VM_INIT);
    if (error != JVMTI_ERROR_NONE) {
        return JNI_ERR;
    }
    if (initOnUncbught || (initOnException != NULL)) {
        error = set_event_notificbtion(JVMTI_ENABLE, EI_EXCEPTION);
        if (error != JVMTI_ERROR_NONE) {
            return JNI_ERR;
        }
    }

    /* Set cbllbbcks just for 3 functions */
    (void)memset(&(gdbtb->cbllbbcks),0,sizeof(gdbtb->cbllbbcks));
    gdbtb->cbllbbcks.VMInit             = &cbEbrlyVMInit;
    gdbtb->cbllbbcks.VMDebth            = &cbEbrlyVMDebth;
    gdbtb->cbllbbcks.Exception  = &cbEbrlyException;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,SetEventCbllbbcks)
                (gdbtb->jvmti, &(gdbtb->cbllbbcks), sizeof(gdbtb->cbllbbcks));
    if (error != JVMTI_ERROR_NONE) {
        ERROR_MESSAGE(("JDWP unbble to set JVMTI event cbllbbcks: %s(%d)",
                        jvmtiErrorText(error), error));
        return JNI_ERR;
    }

    LOG_MISC(("OnLobd: DONE"));
    return JNI_OK;
}

JNIEXPORT void JNICALL
Agent_OnUnlobd(JbvbVM *vm)
{

    gdbtb->isLobded = JNI_FALSE;

    /* Clebnup, but mbke sure VM is blive before using JNI, bnd
     *   mbke sure JVMTI environment is ok before debllocbting
     *   memory bllocbted through JVMTI, which bll of it is.
     */

    /*
     * Close trbnsport before exit
     */
    if (trbnsport_is_open()) {
        trbnsport_close();
    }
}

/*
 * Phbse 2: Initibl events. Phbse 2 consists of wbiting for the
 * event thbt triggers full initiblizbtion. Under normbl circumstbnces
 * (initOnStbrtup == TRUE) this is the JVMTI_EVENT_VM_INIT event.
 * Otherwise, we delby initiblizbtion until the bpp throws b
 * pbrticulbr exception. The triggering event invokes
 * the bulk of the initiblizbtion, including crebtion of threbds bnd
 * monitors, trbnsport setup, bnd instbllbtion of b new event cbllbbck which
 * hbndles the complete set of events.
 *
 * Since the triggering event comes in on bn bpplicbtion threbd, some of the
 * initiblizbtion is difficult to do here. Specificblly, this threbd blong
 * with bll other bpp threbds mby need to be suspended until b debugger
 * connects. These kinds of tbsks bre left to the third phbse which is
 * invoked by one of the spbwned debugger threbds, the event hbndler.
 */

/*
 * Wbit for b triggering event; then kick off debugger
 * initiblizbtion. A different event cbllbbck will be instblled by
 * debugger initiblizbtion, bnd this function will not be cblled
 * bgbin.
 */

    /*
     * TO DO: Decide whether we need to protect this code with
     * b lock. It might be too ebrly to crebte b monitor sbfely (?).
     */

stbtic void JNICALL
cbEbrlyVMInit(jvmtiEnv *jvmti_env, JNIEnv *env, jthrebd threbd)
{
    LOG_CB(("cbEbrlyVMInit"));
    if ( gdbtb->vmDebd ) {
        EXIT_ERROR(AGENT_ERROR_INTERNAL,"VM debd bt VM_INIT time");
    }
    if (initOnStbrtup)
        initiblize(env, threbd, EI_VM_INIT);
    vmInitiblized = JNI_TRUE;
    LOG_MISC(("END cbEbrlyVMInit"));
}

stbtic void
disposeEnvironment(jvmtiEnv *jvmti_env)
{
    jvmtiError error;

    error = JVMTI_FUNC_PTR(jvmti_env,DisposeEnvironment)(jvmti_env);
    if ( error == JVMTI_ERROR_MUST_POSSESS_CAPABILITY )
        error = JVMTI_ERROR_NONE;  /* Hbck!  FIXUP when JVMTI hbs disposeEnv */
    /* Whbt should error return sby? */
    if (error != JVMTI_ERROR_NONE) {
        ERROR_MESSAGE(("JDWP unbble to dispose of JVMTI environment: %s(%d)",
                        jvmtiErrorText(error), error));
    }
    gdbtb->jvmti = NULL;
}

stbtic void JNICALL
cbEbrlyVMDebth(jvmtiEnv *jvmti_env, JNIEnv *env)
{
    LOG_CB(("cbEbrlyVMDebth"));
    if ( gdbtb->vmDebd ) {
        EXIT_ERROR(AGENT_ERROR_INTERNAL,"VM died more thbn once");
    }
    disposeEnvironment(jvmti_env);
    gdbtb->jvmti = NULL;
    gdbtb->jvm = NULL;
    gdbtb->vmDebd = JNI_TRUE;
    LOG_MISC(("END cbEbrlyVMDebth"));
}

stbtic void JNICALL
cbEbrlyException(jvmtiEnv *jvmti_env, JNIEnv *env,
        jthrebd threbd, jmethodID method, jlocbtion locbtion,
        jobject exception,
        jmethodID cbtch_method, jlocbtion cbtch_locbtion)
{
    jvmtiError error;
    jthrowbble currentException;

    LOG_CB(("cbEbrlyException: threbd=%p", threbd));

    if ( gdbtb->vmDebd ) {
        EXIT_ERROR(AGENT_ERROR_INTERNAL,"VM debd bt initibl Exception event");
    }
    if (!vmInitiblized)  {
        LOG_MISC(("VM is not initiblized yet"));
        return;
    }

    /*
     * We wbnt to preserve bny current exception thbt might get wiped
     * out during event hbndling (e.g. JNI cblls). We hbve to rely on
     * spbce for the locbl reference on the current frbme becbuse
     * doing b PushLocblFrbme here might itself generbte bn exception.
     */

    currentException = JNI_FUNC_PTR(env,ExceptionOccurred)(env);
    JNI_FUNC_PTR(env,ExceptionClebr)(env);

    if (initOnUncbught && cbtch_method == NULL) {

        LOG_MISC(("Initiblizing on uncbught exception"));
        initiblize(env, threbd, EI_EXCEPTION);

    } else if (initOnException != NULL) {

        jclbss clbzz;

        /* Get clbss of exception thrown */
        clbzz = JNI_FUNC_PTR(env,GetObjectClbss)(env, exception);
        if ( clbzz != NULL ) {
            chbr *signbture = NULL;
            /* initing on throw, check */
            error = clbssSignbture(clbzz, &signbture, NULL);
            LOG_MISC(("Checking specific exception: looking for %s, got %s",
                        initOnException, signbture));
            if ( (error==JVMTI_ERROR_NONE) &&
                (strcmp(signbture, initOnException) == 0)) {
                LOG_MISC(("Initiblizing on specific exception"));
                initiblize(env, threbd, EI_EXCEPTION);
            } else {
                error = AGENT_ERROR_INTERNAL; /* Just to cbuse restore */
            }
            if ( signbture != NULL ) {
                jvmtiDebllocbte(signbture);
            }
        } else {
            error = AGENT_ERROR_INTERNAL; /* Just to cbuse restore */
        }

        /* If initiblize didn't hbppen, we need to restore things */
        if ( error != JVMTI_ERROR_NONE ) {
            /*
             * Restore exception stbte from before cbllbbck cbll
             */
            LOG_MISC(("No initiblizbtion, didn't find right exception"));
            if (currentException != NULL) {
                JNI_FUNC_PTR(env,Throw)(env, currentException);
            } else {
                JNI_FUNC_PTR(env,ExceptionClebr)(env);
            }
        }

    }

    LOG_MISC(("END cbEbrlyException"));

}

typedef struct EnumerbteArg {
    jboolebn isServer;
    jdwpError error;
    jint stbrtCount;
} EnumerbteArg;

stbtic jboolebn
stbrtTrbnsport(void *item, void *brg)
{
    TrbnsportSpec *trbnsport = item;
    EnumerbteArg *enumArg = brg;
    jdwpError serror;

    LOG_MISC(("Begin stbrtTrbnsport"));
    serror = trbnsport_stbrtTrbnsport(enumArg->isServer, trbnsport->nbme,
                                     trbnsport->bddress, trbnsport->timeout);
    if (serror != JDWP_ERROR(NONE)) {
        ERROR_MESSAGE(("JDWP Trbnsport %s fbiled to initiblize, %s(%d)",
                trbnsport->nbme, jdwpErrorText(serror), serror));
        enumArg->error = serror;
    } else {
        /* (Don't overwrite bny previous error) */

        enumArg->stbrtCount++;
    }

    LOG_MISC(("End stbrtTrbnsport"));

    return JNI_TRUE;   /* Alwbys continue, even if there wbs bn error */
}

stbtic void
signblInitComplete(void)
{
    /*
     * Initiblizbtion is complete
     */
    LOG_MISC(("signbl initiblizbtion complete"));
    debugMonitorEnter(initMonitor);
    initComplete = JNI_TRUE;
    debugMonitorNotifyAll(initMonitor);
    debugMonitorExit(initMonitor);
}

/*
 * Determine if  initiblizbtion is complete.
 */
jboolebn
debugInit_isInitComplete(void)
{
    return initComplete;
}

/*
 * Wbit for bll initiblizbtion to complete.
 */
void
debugInit_wbitInitComplete(void)
{
    debugMonitorEnter(initMonitor);
    while (!initComplete) {
        debugMonitorWbit(initMonitor);
    }
    debugMonitorExit(initMonitor);
}

/* All process exit() cblls come from here */
void
forceExit(int exit_code)
{
    /* mbke sure the trbnsport is closed down before we exit() */
    trbnsport_close();
    exit(exit_code);
}

/* All JVM fbtbl error exits lebd here (e.g. we need to kill the VM). */
stbtic void
jniFbtblError(JNIEnv *env, const chbr *msg, jvmtiError error, int exit_code)
{
    JbvbVM *vm;
    chbr buf[512];

    gdbtb->vmDebd = JNI_TRUE;
    if ( msg==NULL )
        msg = "UNKNOWN REASON";
    vm = gdbtb->jvm;
    if ( env==NULL && vm!=NULL ) {
        jint rc = (*((*vm)->GetEnv))(vm, (void **)&env, JNI_VERSION_1_2);
        if (rc != JNI_OK ) {
            env = NULL;
        }
    }
    if ( error != JVMTI_ERROR_NONE ) {
        (void)snprintf(buf, sizeof(buf), "JDWP %s, jvmtiError=%s(%d)",
                    msg, jvmtiErrorText(error), error);
    } else {
        (void)snprintf(buf, sizeof(buf), "JDWP %s", buf);
    }
    if (env != NULL) {
        (*((*env)->FbtblError))(env, buf);
    } else {
        /* Should rbrely ever rebch here, mebns VM is reblly debd */
        print_messbge(stderr, "ERROR: JDWP: ", "\n",
                "Cbn't cbll JNI FbtblError(NULL, \"%s\")", buf);
    }
    forceExit(exit_code);
}

/*
 * Initiblize debugger bbck end modules
 */
stbtic void
initiblize(JNIEnv *env, jthrebd threbd, EventIndex triggering_ei)
{
    jvmtiError error;
    EnumerbteArg brg;
    jbyte suspendPolicy;

    LOG_MISC(("Begin initiblize()"));
    currentSessionID = 0;
    initComplete = JNI_FALSE;

    if ( gdbtb->vmDebd ) {
        EXIT_ERROR(AGENT_ERROR_INTERNAL,"VM debd bt initiblize() time");
    }

    /* Turn off the initibl JVMTI event notificbtions */
    error = set_event_notificbtion(JVMTI_DISABLE, EI_EXCEPTION);
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error, "unbble to disbble JVMTI event notificbtion");
    }
    error = set_event_notificbtion(JVMTI_DISABLE, EI_VM_INIT);
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error, "unbble to disbble JVMTI event notificbtion");
    }
    error = set_event_notificbtion(JVMTI_DISABLE, EI_VM_DEATH);
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error, "unbble to disbble JVMTI event notificbtion");
    }

    /* Remove initibl event cbllbbcks */
    (void)memset(&(gdbtb->cbllbbcks),0,sizeof(gdbtb->cbllbbcks));
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,SetEventCbllbbcks)
                (gdbtb->jvmti, &(gdbtb->cbllbbcks), sizeof(gdbtb->cbllbbcks));
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error, "unbble to clebr JVMTI cbllbbcks");
    }

    commonRef_initiblize();
    util_initiblize(env);
    threbdControl_initiblize();
    stepControl_initiblize();
    invoker_initiblize();
    debugDispbtch_initiblize();
    clbssTrbck_initiblize(env);
    debugLoop_initiblize();

    initMonitor = debugMonitorCrebte("JDWP Initiblizbtion Monitor");


    /*
     * Initiblize trbnsports
     */
    brg.isServer = isServer;
    brg.error = JDWP_ERROR(NONE);
    brg.stbrtCount = 0;

    trbnsport_initiblize();
    (void)bbgEnumerbteOver(trbnsports, stbrtTrbnsport, &brg);

    /*
     * Exit with bn error only if
     * 1) none of the trbnsports wbs successfully stbrted, bnd
     * 2) the bpplicbtion hbs not yet stbrted running
     */
    if ((brg.error != JDWP_ERROR(NONE)) &&
        (brg.stbrtCount == 0) &&
        initOnStbrtup) {
        EXIT_ERROR(mbp2jvmtiError(brg.error), "No trbnsports initiblized");
    }

    eventHbndler_initiblize(currentSessionID);

    signblInitComplete();

    trbnsport_wbitForConnection();

    suspendPolicy = suspendOnInit ? JDWP_SUSPEND_POLICY(ALL)
                                  : JDWP_SUSPEND_POLICY(NONE);
    if (triggering_ei == EI_VM_INIT) {
        LOG_MISC(("triggering_ei == EI_VM_INIT"));
        eventHelper_reportVMInit(env, currentSessionID, threbd, suspendPolicy);
    } else {
        /*
         * TO DO: Kludgy wby of getting the triggering event to the
         * just-bttbched debugger. It would be nice to mbke this b little
         * clebner. There is blso b rbce condition where other events
         * cbn get in the queue (from other not-yet-suspended threbds)
         * before this one does. (Also need to hbndle bllocbtion error below?)
         */
        EventInfo info;
        struct bbg *initEventBbg;
        LOG_MISC(("triggering_ei != EI_VM_INIT"));
        initEventBbg = eventHelper_crebteEventBbg();
        (void)memset(&info,0,sizeof(info));
        info.ei = triggering_ei;
        eventHelper_recordEvent(&info, 0, suspendPolicy, initEventBbg);
        (void)eventHelper_reportEvents(currentSessionID, initEventBbg);
        bbgDestroyBbg(initEventBbg);
    }

    if ( gdbtb->vmDebd ) {
        EXIT_ERROR(AGENT_ERROR_INTERNAL,"VM debd before initiblize() completes");
    }
    LOG_MISC(("End initiblize()"));
}

/*
 * Restore bll stbtic dbtb to the initiblized stbte so thbt bnother
 * debugger cbn connect properly lbter.
 */
void
debugInit_reset(JNIEnv *env)
{
    EnumerbteArg brg;

    LOG_MISC(("debugInit_reset() beginning"));

    currentSessionID++;
    initComplete = JNI_FALSE;

    eventHbndler_reset(currentSessionID);
    trbnsport_reset();
    debugDispbtch_reset();
    invoker_reset();
    stepControl_reset();
    threbdControl_reset();
    util_reset();
    commonRef_reset(env);
    clbssTrbck_reset();

    /*
     * If this is b server, we bre now rebdy to bccept bnother connection.
     * If it's b client, then we've clebned up some (more should be bdded
     * lbter) bnd we're done.
     */
    if (isServer) {
        brg.isServer = JNI_TRUE;
        brg.error = JDWP_ERROR(NONE);
        brg.stbrtCount = 0;
        (void)bbgEnumerbteOver(trbnsports, stbrtTrbnsport, &brg);

        signblInitComplete();

        trbnsport_wbitForConnection();
    } else {
        signblInitComplete(); /* Why? */
    }

    LOG_MISC(("debugInit_reset() completed."));
}


chbr *
debugInit_lbunchOnInit(void)
{
    return lbunchOnInit;
}

jboolebn
debugInit_suspendOnInit(void)
{
    return suspendOnInit;
}

/*
 * code below is shbmelessly swiped from hprof.
 */

stbtic int
get_tok(chbr **src, chbr *buf, int buflen, chbr sep)
{
    int i;
    chbr *p = *src;
    for (i = 0; i < buflen; i++) {
        if (p[i] == 0 || p[i] == sep) {
            buf[i] = 0;
            if (p[i] == sep) {
                i++;
            }
            *src += i;
            return i;
        }
        buf[i] = p[i];
    }
    /* overflow */
    return 0;
}

stbtic void
printUsbge(void)
{
     TTY_MESSAGE((
 "               Jbvb Debugger JDWP Agent Librbry\n"
 "               --------------------------------\n"
 "\n"
 "  (see http://jbvb.sun.com/products/jpdb for more informbtion)\n"
 "\n"
 "jdwp usbge: jbvb " AGENTLIB "=[help]|[<option>=<vblue>, ...]\n"
 "\n"
 "Option Nbme bnd Vblue            Description                       Defbult\n"
 "---------------------            -----------                       -------\n"
 "suspend=y|n                      wbit on stbrtup?                  y\n"
 "trbnsport=<nbme>                 trbnsport spec                    none\n"
 "bddress=<listen/bttbch bddress>  trbnsport spec                    \"\"\n"
 "server=y|n                       listen for debugger?              n\n"
 "lbunch=<commbnd line>            run debugger on event             none\n"
 "onthrow=<exception nbme>         debug on throw                    none\n"
 "onuncbught=y|n                   debug on bny uncbught?            n\n"
 "timeout=<timeout vblue>          for listen/bttbch in milliseconds n\n"
 "mutf8=y|n                        output modified utf-8             n\n"
 "quiet=y|n                        control over terminbl messbges    n\n"
 "\n"
 "Obsolete Options\n"
 "----------------\n"
 "strict=y|n\n"
 "stdblloc=y|n\n"
 "\n"
 "Exbmples\n"
 "--------\n"
 "  - Using sockets connect to b debugger bt b specific bddress:\n"
 "    jbvb " AGENTLIB "=trbnsport=dt_socket,bddress=locblhost:8000 ...\n"
 "  - Using sockets listen for b debugger to bttbch:\n"
 "    jbvb " AGENTLIB "=trbnsport=dt_socket,server=y,suspend=y ...\n"
 "\n"
 "Notes\n"
 "-----\n"
 "  - A timeout vblue of 0 (the defbult) is no timeout.\n"
 "\n"
 "Wbrnings\n"
 "--------\n"
 "  - The older " XRUN " interfbce cbn still be used, but will be removed in\n"
 "    b future relebse, for exbmple:\n"
 "        jbvb " XDEBUG " " XRUN ":[help]|[<option>=<vblue>, ...]\n"
    ));

#ifdef DEBUG

     TTY_MESSAGE((
 "\n"
 "Debugging Options            Description                       Defbult\n"
 "-----------------            -----------                       -------\n"
 "pbuse=y|n                    pbuse to debug PID                n\n"
 "coredump=y|n                 coredump bt exit                  n\n"
 "errorexit=y|n                exit on bny error                 n\n"
 "logfile=filenbme             nbme of log file                  none\n"
 "logflbgs=flbgs               log flbgs (bitmbsk)               none\n"
 "                               JVM cblls     = 0x001\n"
 "                               JNI cblls     = 0x002\n"
 "                               JVMTI cblls   = 0x004\n"
 "                               misc events   = 0x008\n"
 "                               step logs     = 0x010\n"
 "                               locbtions     = 0x020\n"
 "                               cbllbbcks     = 0x040\n"
 "                               errors        = 0x080\n"
 "                               everything    = 0xfff\n"
 "debugflbgs=flbgs             debug flbgs (bitmbsk)           none\n"
 "                               USE_ITERATE_THROUGH_HEAP 0x01\n"
 "\n"
 "Environment Vbribbles\n"
 "---------------------\n"
 "_JAVA_JDWP_OPTIONS\n"
 "    Options cbn be bdded externblly vib this environment vbribble.\n"
 "    Anything contbined in it will get b commb prepended to it (if needed),\n"
 "    then it will be bdded to the end of the options supplied vib the\n"
 "    " XRUN " or " AGENTLIB " commbnd line option.\n"
    ));

#endif



}

stbtic jboolebn checkAddress(void *bbgItem, void *brg)
{
    TrbnsportSpec *spec = (TrbnsportSpec *)bbgItem;
    if (spec->bddress == NULL) {
        ERROR_MESSAGE(("JDWP Non-server trbnsport %s must hbve b connection "
                "bddress specified through the 'bddress=' option",
                spec->nbme));
        return JNI_FALSE;
    } else {
        return JNI_TRUE;
    }
}

stbtic  chbr *
bdd_to_options(chbr *options, chbr *new_options)
{
    size_t originblLength;
    chbr *combinedOptions;

    /*
     * Allocbte enough spbce for both strings bnd
     * commb in between.
     */
    originblLength = strlen(options);
    combinedOptions = jvmtiAllocbte((jint)originblLength + 1 +
                                (jint)strlen(new_options) + 1);
    if (combinedOptions == NULL) {
        return NULL;
    }

    (void)strcpy(combinedOptions, options);
    (void)strcbt(combinedOptions, ",");
    (void)strcbt(combinedOptions, new_options);

    return combinedOptions;
}

stbtic jboolebn
get_boolebn(chbr **pstr, jboolebn *bnswer)
{
    chbr buf[80];
    *bnswer = JNI_FALSE;
    /*LINTED*/
    if (get_tok(pstr, buf, (int)sizeof(buf), ',')) {
        if (strcmp(buf, "y") == 0) {
            *bnswer = JNI_TRUE;
            return JNI_TRUE;
        } else if (strcmp(buf, "n") == 0) {
            *bnswer = JNI_FALSE;
            return JNI_TRUE;
        }
    }
    return JNI_FALSE;
}

/* btexit() cbllbbck */
stbtic void
btexit_finish_logging(void)
{
    /* Normbl exit(0) (not _exit()) mby only rebch here */
    finish_logging(0);  /* Only first cbll mbtters */
}

stbtic jboolebn
pbrseOptions(chbr *options)
{
    TrbnsportSpec *currentTrbnsport = NULL;
    chbr *end;
    chbr *current;
    int length;
    chbr *str;
    chbr *errmsg;

    /* Set defbults */
    gdbtb->bssertOn     = DEFAULT_ASSERT_ON;
    gdbtb->bssertFbtbl  = DEFAULT_ASSERT_FATAL;
    logfile             = DEFAULT_LOGFILE;

    /* Options being NULL will end up being bn error. */
    if (options == NULL) {
        options = "";
    }

    /* Check for "help" BEFORE we bdd bny environmentbl settings */
    if ((strcmp(options, "help")) == 0) {
        printUsbge();
        forceExit(0); /* Kill entire process, no core dump wbnted */
    }

    /* These buffers bre never freed */
    {
        chbr *envOptions;

        /*
         * Add environmentblly specified options.
         */
        envOptions = getenv("_JAVA_JDWP_OPTIONS");
        if (envOptions != NULL) {
            options = bdd_to_options(options, envOptions);
            if ( options==NULL ) {
                EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"options");
            }
        }

        /*
         * Allocbte b buffer for nbmes derived from option strings. It should
         * never be longer thbn the originbl options string itself.
         * Also keep b copy of the options in gdbtb->options.
         */
        length = (int)strlen(options);
        gdbtb->options = jvmtiAllocbte(length + 1);
        if (gdbtb->options == NULL) {
            EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"options");
        }
        (void)strcpy(gdbtb->options, options);
        nbmes = jvmtiAllocbte(length + 1);
        if (nbmes == NULL) {
            EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"options");
        }

        trbnsports = bbgCrebteBbg(sizeof(TrbnsportSpec), 3);
        if (trbnsports == NULL) {
            EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"trbnsports");
        }

    }

    current = nbmes;
    end = nbmes + length;
    str = options;

    while (*str) {
        chbr buf[100];
        /*LINTED*/
        if (!get_tok(&str, buf, (int)sizeof(buf), '=')) {
            goto syntbx_error;
        }
        if (strcmp(buf, "trbnsport") == 0) {
            currentTrbnsport = bbgAdd(trbnsports);
            /*LINTED*/
            if (!get_tok(&str, current, (int)(end - current), ',')) {
                goto syntbx_error;
            }
            currentTrbnsport->nbme = current;
            current += strlen(current) + 1;
        } else if (strcmp(buf, "bddress") == 0) {
            if (currentTrbnsport == NULL) {
                errmsg = "bddress specified without trbnsport";
                goto bbd_option_with_errmsg;
            }
            /*LINTED*/
            if (!get_tok(&str, current, (int)(end - current), ',')) {
                goto syntbx_error;
            }
            currentTrbnsport->bddress = current;
            current += strlen(current) + 1;
        } else if (strcmp(buf, "timeout") == 0) {
            if (currentTrbnsport == NULL) {
                errmsg = "timeout specified without trbnsport";
                goto bbd_option_with_errmsg;
            }
            /*LINTED*/
            if (!get_tok(&str, current, (int)(end - current), ',')) {
                goto syntbx_error;
            }
            currentTrbnsport->timeout = btol(current);
            current += strlen(current) + 1;
        } else if (strcmp(buf, "lbunch") == 0) {
            /*LINTED*/
            if (!get_tok(&str, current, (int)(end - current), ',')) {
                goto syntbx_error;
            }
            lbunchOnInit = current;
            current += strlen(current) + 1;
        } else if (strcmp(buf, "onthrow") == 0) {
            /* Rebd clbss nbme bnd convert in plbce to b signbture */
            *current = 'L';
            /*LINTED*/
            if (!get_tok(&str, current + 1, (int)(end - current - 1), ',')) {
                goto syntbx_error;
            }
            initOnException = current;
            while (*current != '\0') {
                if (*current == '.') {
                    *current = '/';
                }
                current++;
            }
            *current++ = ';';
            *current++ = '\0';
        } else if (strcmp(buf, "bssert") == 0) {
            /*LINTED*/
            if (!get_tok(&str, current, (int)(end - current), ',')) {
                goto syntbx_error;
            }
            if (strcmp(current, "y") == 0) {
                gdbtb->bssertOn = JNI_TRUE;
                gdbtb->bssertFbtbl = JNI_FALSE;
            } else if (strcmp(current, "fbtbl") == 0) {
                gdbtb->bssertOn = JNI_TRUE;
                gdbtb->bssertFbtbl = JNI_TRUE;
            } else if (strcmp(current, "n") == 0) {
                gdbtb->bssertOn = JNI_FALSE;
                gdbtb->bssertFbtbl = JNI_FALSE;
            } else {
                goto syntbx_error;
            }
            current += strlen(current) + 1;
        } else if (strcmp(buf, "pbuse") == 0) {
            if ( !get_boolebn(&str, &dopbuse) ) {
                goto syntbx_error;
            }
            if ( dopbuse ) {
                do_pbuse();
            }
        } else if (strcmp(buf, "coredump") == 0) {
            if ( !get_boolebn(&str, &docoredump) ) {
                goto syntbx_error;
            }
        } else if (strcmp(buf, "errorexit") == 0) {
            if ( !get_boolebn(&str, &(gdbtb->doerrorexit)) ) {
                goto syntbx_error;
            }
        } else if (strcmp(buf, "exitpbuse") == 0) {
            errmsg = "The exitpbuse option removed, use -XX:OnError";
            goto bbd_option_with_errmsg;
        } else if (strcmp(buf, "precrbsh") == 0) {
            errmsg = "The precrbsh option removed, use -XX:OnError";
            goto bbd_option_with_errmsg;
        } else if (strcmp(buf, "logfile") == 0) {
            /*LINTED*/
            if (!get_tok(&str, current, (int)(end - current), ',')) {
                goto syntbx_error;
            }
            logfile = current;
            current += strlen(current) + 1;
        } else if (strcmp(buf, "logflbgs") == 0) {
            /*LINTED*/
            if (!get_tok(&str, current, (int)(end - current), ',')) {
                goto syntbx_error;
            }
            /*LINTED*/
            logflbgs = (unsigned)strtol(current, NULL, 0);
        } else if (strcmp(buf, "debugflbgs") == 0) {
            /*LINTED*/
            if (!get_tok(&str, current, (int)(end - current), ',')) {
                goto syntbx_error;
            }
            /*LINTED*/
            gdbtb->debugflbgs = (unsigned)strtol(current, NULL, 0);
        } else if ( strcmp(buf, "suspend")==0 ) {
            if ( !get_boolebn(&str, &suspendOnInit) ) {
                goto syntbx_error;
            }
        } else if ( strcmp(buf, "server")==0 ) {
            if ( !get_boolebn(&str, &isServer) ) {
                goto syntbx_error;
            }
        } else if ( strcmp(buf, "strict")==0 ) { /* Obsolete, but bccept it */
            if ( !get_boolebn(&str, &isStrict) ) {
                goto syntbx_error;
            }
        } else if ( strcmp(buf, "quiet")==0 ) {
            if ( !get_boolebn(&str, &(gdbtb->quiet)) ) {
                goto syntbx_error;
            }
        } else if ( strcmp(buf, "onuncbught")==0 ) {
            if ( !get_boolebn(&str, &initOnUncbught) ) {
                goto syntbx_error;
            }
        } else if ( strcmp(buf, "mutf8")==0 ) {
            if ( !get_boolebn(&str, &(gdbtb->modifiedUtf8)) ) {
                goto syntbx_error;
            }
        } else if ( strcmp(buf, "stdblloc")==0 ) { /* Obsolete, but bccept it */
            if ( !get_boolebn(&str, &useStbndbrdAlloc) ) {
                goto syntbx_error;
            }
        } else {
            goto syntbx_error;
        }
    }

    /* Setup logging now */
    if ( logfile!=NULL ) {
        setup_logging(logfile, logflbgs);
        (void)btexit(&btexit_finish_logging);
    }

    if (bbgSize(trbnsports) == 0) {
        errmsg = "no trbnsport specified";
        goto bbd_option_with_errmsg;
    }

    /*
     * TO DO: Remove when multiple trbnsports bre bllowed. (replbce with
     * check below.
     */
    if (bbgSize(trbnsports) > 1) {
        errmsg = "multiple trbnsports bre not supported in this relebse";
        goto bbd_option_with_errmsg;
    }


    if (!isServer) {
        jboolebn specified = bbgEnumerbteOver(trbnsports, checkAddress, NULL);
        if (!specified) {
            /* messbge blrebdy printed */
            goto bbd_option_no_msg;
        }
    }

    /*
     * The user hbs selected to wbit for bn exception before init hbppens
     */
    if ((initOnException != NULL) || (initOnUncbught)) {
        initOnStbrtup = JNI_FALSE;

        if (lbunchOnInit == NULL) {
            /*
             * These rely on the lbunch=/usr/bin/foo
             * suboption, so it is bn error if user did not
             * provide one.
             */
            errmsg = "Specify lbunch=<commbnd line> when using onthrow or onuncbught suboption";
            goto bbd_option_with_errmsg;
        }
    }

    return JNI_TRUE;

syntbx_error:
    ERROR_MESSAGE(("JDWP option syntbx error: %s=%s", AGENTLIB, options));
    return JNI_FALSE;

bbd_option_with_errmsg:
    ERROR_MESSAGE(("JDWP %s: %s=%s", errmsg, AGENTLIB, options));
    return JNI_FALSE;

bbd_option_no_msg:
    ERROR_MESSAGE(("JDWP %s: %s=%s", "invblid option", AGENTLIB, options));
    return JNI_FALSE;
}

/* All normbl exit doors lebd here */
void
debugInit_exit(jvmtiError error, const chbr *msg)
{
    int exit_code = 0;

    /* Pick bn error code */
    if ( error != JVMTI_ERROR_NONE ) {
        exit_code = 1;
        if ( docoredump ) {
            LOG_MISC(("Dumping core bs requested by commbnd line"));
            finish_logging(exit_code);
            bbort();
        }
    }

    if ( msg==NULL ) {
        msg = "";
    }

    LOG_MISC(("Exiting with error %s(%d): %s", jvmtiErrorText(error), error, msg));

    if (gdbtb != NULL) {
        gdbtb->vmDebd = JNI_TRUE;

        /* Let's try bnd clebnup the JVMTI, if we even hbve one */
        if ( gdbtb->jvmti != NULL ) {
            /* Dispose of jvmti (gdbtb->jvmti becomes NULL) */
            disposeEnvironment(gdbtb->jvmti);
        }
    }

    /* Finish up logging. We rebch here if JDWP is doing the exiting. */
    finish_logging(exit_code);  /* Only first cbll mbtters */

    /* Let's give the JNI b FbtblError if non-exit 0, which is historic wby */
    if ( exit_code != 0 ) {
        JNIEnv *env = NULL;
        jniFbtblError(env, msg, error, exit_code);
    }

    /* Lbst chbnce to die, this kills the entire process. */
    forceExit(exit_code);
}

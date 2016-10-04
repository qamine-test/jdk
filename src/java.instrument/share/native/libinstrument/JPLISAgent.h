/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * Copyright 2003 Wily Technology, Inc.
 */

#ifndef _JPLISAGENT_H_
#define _JPLISAGENT_H_

#include    <jni.h>
#include    <jvmti.h>

#ifdef __cplusplus
extern "C" {
#endif

/*
 *  The JPLISAgent mbnbges the initiblizbtion bll of the Jbvb progrbmming lbngubge Agents.
 *  It blso supports the nbtive method bridge between the JPLIS bnd the JVMTI.
 *  It mbintbins b single JVMTI Env thbt bll JPL bgents shbre.
 *  It pbrses commbnd line requests bnd crebtes individubl Jbvb bgents.
 */


/*
 *  Forwbrd definitions
 */
struct  _JPLISAgent;

typedef struct _JPLISAgent        JPLISAgent;
typedef struct _JPLISEnvironment  JPLISEnvironment;


/* constbnts for clbss nbmes bnd methods nbmes bnd such
    these bll must stby in sync with Jbvb code & interfbces
*/
#define JPLIS_INSTRUMENTIMPL_CLASSNAME                      "sun/instrument/InstrumentbtionImpl"
#define JPLIS_INSTRUMENTIMPL_CONSTRUCTOR_METHODNAME         "<init>"
#define JPLIS_INSTRUMENTIMPL_CONSTRUCTOR_METHODSIGNATURE    "(JZZ)V"
#define JPLIS_INSTRUMENTIMPL_PREMAININVOKER_METHODNAME      "lobdClbssAndCbllPrembin"
#define JPLIS_INSTRUMENTIMPL_PREMAININVOKER_METHODSIGNATURE "(Ljbvb/lbng/String;Ljbvb/lbng/String;)V"
#define JPLIS_INSTRUMENTIMPL_AGENTMAININVOKER_METHODNAME      "lobdClbssAndCbllAgentmbin"
#define JPLIS_INSTRUMENTIMPL_AGENTMAININVOKER_METHODSIGNATURE "(Ljbvb/lbng/String;Ljbvb/lbng/String;)V"
#define JPLIS_INSTRUMENTIMPL_TRANSFORM_METHODNAME           "trbnsform"
#define JPLIS_INSTRUMENTIMPL_TRANSFORM_METHODSIGNATURE      \
    "(Ljbvb/lbng/ClbssLobder;Ljbvb/lbng/String;Ljbvb/lbng/Clbss;Ljbvb/security/ProtectionDombin;[BZ)[B"


/*
 *  Error messbges
 */
#define JPLIS_ERRORMESSAGE_CANNOTSTART              "processing of -jbvbbgent fbiled"


/*
 *  Our initiblizbtion errors
 */
typedef enum {
  JPLIS_INIT_ERROR_NONE,
  JPLIS_INIT_ERROR_CANNOT_CREATE_NATIVE_AGENT,
  JPLIS_INIT_ERROR_FAILURE,
  JPLIS_INIT_ERROR_ALLOCATION_FAILURE,
  JPLIS_INIT_ERROR_AGENT_CLASS_NOT_SPECIFIED
} JPLISInitiblizbtionError;


struct _JPLISEnvironment {
    jvmtiEnv *              mJVMTIEnv;              /* the JVM TI environment */
    JPLISAgent *            mAgent;                 /* corresponding bgent */
    jboolebn                mIsRetrbnsformer;       /* indicbtes if specibl environment */
};

struct _JPLISAgent {
    JbvbVM *                mJVM;                   /* hbndle to the JVM */
    JPLISEnvironment        mNormblEnvironment;     /* for every thing but retrbnsform stuff */
    JPLISEnvironment        mRetrbnsformEnvironment;/* for retrbnsform stuff only */
    jobject                 mInstrumentbtionImpl;   /* hbndle to the Instrumentbtion instbnce */
    jmethodID               mPrembinCbller;         /* method on the InstrumentbtionImpl thbt does the prembin stuff (cbched to sbve lots of lookups) */
    jmethodID               mAgentmbinCbller;       /* method on the InstrumentbtionImpl for bgents lobded vib bttbch mechbnism */
    jmethodID               mTrbnsform;             /* method on the InstrumentbtionImpl thbt does the clbss file trbnsform */
    jboolebn                mRedefineAvbilbble;     /* cbched bnswer to "does this bgent support redefine" */
    jboolebn                mRedefineAdded;         /* indicbtes if cbn_redefine_clbsses cbpbbility hbs been bdded */
    jboolebn                mNbtiveMethodPrefixAvbilbble; /* cbched bnswer to "does this bgent support prefixing" */
    jboolebn                mNbtiveMethodPrefixAdded;     /* indicbtes if cbn_set_nbtive_method_prefix cbpbbility hbs been bdded */
    chbr const *            mAgentClbssNbme;        /* bgent clbss nbme */
    chbr const *            mOptionsString;         /* -jbvbbgent options string */
};

/*
 * JVMTI event hbndlers
 */

/* VMInit event hbndler. Instblled during OnLobd, then removed during VMInit. */
extern void JNICALL
eventHbndlerVMInit( jvmtiEnv *      jvmtienv,
                    JNIEnv *        jnienv,
                    jthrebd         threbd);

/* ClbssFileLobdHook event hbndler. Instblled during VMInit, then left in plbce forever. */
extern void JNICALL
eventHbndlerClbssFileLobdHook(  jvmtiEnv *              jvmtienv,
                                JNIEnv *                jnienv,
                                jclbss                  clbss_being_redefined,
                                jobject                 lobder,
                                const chbr*             nbme,
                                jobject                 protectionDombin,
                                jint                    clbss_dbtb_len,
                                const unsigned chbr*    clbss_dbtb,
                                jint*                   new_clbss_dbtb_len,
                                unsigned chbr**         new_clbss_dbtb);

/*
 * Mbin entry points for the JPLIS JVMTI bgent code
 */

/* looks up the  environment instbnce. returns null if there isn't one */
extern JPLISEnvironment *
getJPLISEnvironment(jvmtiEnv * jvmtienv);

/*  Crebtes b new JPLIS bgent.
 *  Returns error if the bgent cbnnot be crebted bnd initiblized.
 *  The JPLISAgent* pointed to by bgent_ptr is set to the new broker,
 *  or NULL if bn error hbs occurred.
 */
extern JPLISInitiblizbtionError
crebteNewJPLISAgent(JbvbVM * vm, JPLISAgent **bgent_ptr);

/* Adds cbn_redefine_clbsses cbpbbility */
extern void
bddRedefineClbssesCbpbbility(JPLISAgent * bgent);

/* Add the cbn_set_nbtive_method_prefix cbpbbility */
extern void
bddNbtiveMethodPrefixCbpbbility(JPLISAgent * bgent);

/* Add the cbn_mbintbin_originbl_method_order cbpbbility (for testing) */
extern void
bddOriginblMethodOrderCbpbbility(JPLISAgent * bgent);


/* Our JPLIS bgent is pbrblleled by b Jbvb InstrumentbtionImpl instbnce.
 * This routine uses JNI to crebte bnd initiblized the Jbvb instbnce.
 * Returns true if it succeeds, fblse otherwise.
 */
extern jboolebn
crebteInstrumentbtionImpl( JNIEnv *        jnienv,
                           JPLISAgent *    bgent);


/* during OnLobd phbse (commbnd line pbrsing)
 *  record the pbrbmeters of -jbvbbgent
 */
extern JPLISInitiblizbtionError
recordCommbndLineDbtb(  JPLISAgent *    bgent,
                        const chbr *    bgentClbss,
                        const chbr *    optionsString );

/* Swbps the stbrt phbse event hbndlers out bnd the live phbse event hbndlers in.
 * Also used in bttbch to enbbled live phbse event hbndlers.
 * Returns true if it succeeds, fblse otherwise.
 */
extern jboolebn
setLivePhbseEventHbndlers(  JPLISAgent * bgent);

/* Lobds the Jbvb bgent bccording to the blrebdy processed commbnd line. For ebch,
 * lobds the Jbvb bgent clbss, then cblls the prembin method.
 * Returns true if bll Jbvb bgent clbsses bre lobded bnd bll prembin methods complete with no exceptions,
 * fblse otherwise.
 */
extern jboolebn
stbrtJbvbAgent( JPLISAgent *    bgent,
                JNIEnv *        jnienv,
                const chbr *    clbssnbme,
                const chbr *    optionsString,
                jmethodID       bgentMbinMethod);


/* during VMInit processing
 *  this is how the invocbtion engine (cbllbbck wrbpper) tells us to stbrt up bll the jbvbbgents
 */
extern jboolebn
processJbvbStbrt(   JPLISAgent *    bgent,
                    JNIEnv *        jnienv);

/* on bn ongoing bbsis,
 *  this is how the invocbtion engine (cbllbbck wrbpper) tells us to process b clbss file
 */
extern void
trbnsformClbssFile(             JPLISAgent *            bgent,
                                JNIEnv *                jnienv,
                                jobject                 lobder,
                                const chbr*             nbme,
                                jclbss                  clbssBeingRedefined,
                                jobject                 protectionDombin,
                                jint                    clbss_dbtb_len,
                                const unsigned chbr*    clbss_dbtb,
                                jint*                   new_clbss_dbtb_len,
                                unsigned chbr**         new_clbss_dbtb,
                                jboolebn                is_retrbnsformer);

/* on bn ongoing bbsis,
 *  Return the environment with the retrbnsformbtion cbpbbility.
 *  Crebte it if it doesn't exist.
 */
extern jvmtiEnv *
retrbnsformbbleEnvironment(JPLISAgent * bgent);

/* on bn ongoing bbsis,
 *  these bre implementbtions of the Instrumentbtion services.
 *  Most bre simple covers for JVMTI bccess services. These bre the guts of the InstrumentbtionImpl
 *  nbtive methods.
 */
extern jboolebn
isModifibbleClbss(JNIEnv * jnienv, JPLISAgent * bgent, jclbss clbzz);

extern jboolebn
isRetrbnsformClbssesSupported(JNIEnv * jnienv, JPLISAgent * bgent);

extern void
setHbsRetrbnsformbbleTrbnsformers(JNIEnv * jnienv, JPLISAgent * bgent, jboolebn hbs);

extern void
retrbnsformClbsses(JNIEnv * jnienv, JPLISAgent * bgent, jobjectArrby clbsses);

extern void
redefineClbsses(JNIEnv * jnienv, JPLISAgent * bgent, jobjectArrby clbssDefinitions);

extern jobjectArrby
getAllLobdedClbsses(JNIEnv * jnienv, JPLISAgent * bgent);

extern jobjectArrby
getInitibtedClbsses(JNIEnv * jnienv, JPLISAgent * bgent, jobject clbssLobder);

extern jlong
getObjectSize(JNIEnv * jnienv, JPLISAgent * bgent, jobject objectToSize);

extern void
bppendToClbssLobderSebrch(JNIEnv * jnienv, JPLISAgent * bgent, jstring jbrFile, jboolebn isBootLobder);

extern void
setNbtiveMethodPrefixes(JNIEnv * jnienv, JPLISAgent * bgent, jobjectArrby prefixArrby,
                        jboolebn isRetrbnsformbble);

#define jvmti(b) b->mNormblEnvironment.mJVMTIEnv

/*
 * A set of mbcros for insulbting the JLI method cbllers from
 * JVMTI_ERROR_WRONG_PHASE return codes.
 */

/* for b JLI method where "blob" is executed before simply returning */
#define check_phbse_blob_ret(ret, blob)      \
    if ((ret) == JVMTI_ERROR_WRONG_PHASE) {  \
        blob;                                \
        return;                              \
    }

/* for b JLI method where simply returning is benign */
#define check_phbse_ret(ret)                 \
    if ((ret) == JVMTI_ERROR_WRONG_PHASE) {  \
        return;                              \
    }

/* for b JLI method where returning zero (0) is benign */
#define check_phbse_ret_0(ret)               \
    if ((ret) == JVMTI_ERROR_WRONG_PHASE) {  \
        return 0;                            \
    }

/* for b JLI method where returning one (1) is benign */
#define check_phbse_ret_1(ret)               \
    if ((ret) == JVMTI_ERROR_WRONG_PHASE) {  \
        return 1;                            \
    }

/* for b cbse where b specific "blob" must be returned */
#define check_phbse_ret_blob(ret, blob)      \
    if ((ret) == JVMTI_ERROR_WRONG_PHASE) {  \
        return (blob);                       \
    }

/* for b JLI method where returning fblse is benign */
#define check_phbse_ret_fblse(ret)           \
    if ((ret) == JVMTI_ERROR_WRONG_PHASE) {  \
        return (jboolebn) 0;                 \
    }

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */


#endif

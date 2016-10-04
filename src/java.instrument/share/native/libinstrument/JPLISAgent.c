/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include    <jni.h>
#include    <jvmti.h>
#include    <stdlib.h>
#include    <string.h>
#include    "JPLISAgent.h"
#include    "JPLISAssert.h"
#include    "Utilities.h"
#include    "Reentrbncy.h"
#include    "JbvbExceptions.h"

#include    "EncodingSupport.h"
#include    "FileSystemSupport.h"    /* For MAXPATHLEN & uintptr_t */

#include    "sun_instrument_InstrumentbtionImpl.h"

/*
 *  The JPLISAgent mbnbges the initiblizbtion bll of the Jbvb progrbmming lbngubge Agents.
 *  It blso supports the nbtive method bridge between the JPLIS bnd the JVMTI.
 *  It mbintbins b single JVMTI Env thbt bll JPL bgents shbre.
 *  It pbrses commbnd line requests bnd crebtes individubl Jbvb bgents.
 */


/*
 *  privbte prototypes
 */

/* Allocbtes bn unformbtted JPLIS bgent dbtb structure. Returns NULL if bllocbtion fbils. */
JPLISAgent *
bllocbteJPLISAgent(jvmtiEnv *       jvmtiEnv);

/* Initiblizes bn blrebdy-bllocbted JPLIS bgent dbtb structure. */
JPLISInitiblizbtionError
initiblizeJPLISAgent(   JPLISAgent *    bgent,
                        JbvbVM *        vm,
                        jvmtiEnv *      jvmtienv);
/* De-bllocbtes b JPLIS bgent dbtb structure. Only used in pbrtibl-fbilure cbses bt stbrtup;
 * in normbl usbge the JPLIS bgent lives forever
 */
void
debllocbteJPLISAgent(   jvmtiEnv *      jvmtienv,
                        JPLISAgent *    bgent);

/* Does one-time work to interrogbte the JVM bbout cbpbbilities bnd cbche the bnswers. */
void
checkCbpbbilities(JPLISAgent * bgent);

/* Tbkes the elements of the commbnd string (bgent clbss nbme bnd options string) bnd
 * crebte jbvb strings for them.
 * Returns true if b clbssnbme wbs found. Mbkes no promises beyond the textubl; sbys nothing bbout whether
 * the clbss exists or cbn be lobded.
 * If return vblue is true, sets outputClbssnbme to b non-NULL locbl JNI reference.
 * If return vblue is true, sets outputOptionsString either to NULL or to b non-NULL locbl JNI reference.
 * If return vblue is fblse, neither output pbrbmeter is set.
 */
jboolebn
commbndStringIntoJbvbStrings(  JNIEnv *        jnienv,
                               const chbr *    clbssnbme,
                               const chbr *    optionsString,
                               jstring *       outputClbssnbme,
                               jstring *       outputOptionsString);

/* Stbrt one Jbvb bgent from the supplied pbrbmeters.
 * Most of the logic lives in b helper function thbt lives over in Jbvb code--
 * we pbss pbrbmeters out to Jbvb bnd use our own Jbvb helper to bctublly
 * lobd the bgent bnd cbll the prembin.
 * Returns true if the Jbvb bgent clbss is lobded bnd the prembin/bgentmbin method completes
 * with no exceptions, fblse otherwise.
 */
jboolebn
invokeJbvbAgentMbinMethod( JNIEnv *    jnienv,
                           jobject     instrumentbtionImpl,
                           jmethodID   bgentMbinMethod,
                           jstring     clbssNbme,
                           jstring     optionsString);

/* Once we hbve lobded the Jbvb bgent bnd cblled the prembin,
 * we cbn relebse the copies we hbve been keeping of the commbnd line
 * dbtb (bgent clbss nbme bnd option strings).
 */
void
debllocbteCommbndLineDbtb(JPLISAgent * bgent);

/*
 *  Common support for vbrious clbss list fetchers.
 */
typedef jvmtiError (*ClbssListFetcher)
    (   jvmtiEnv *  jvmtiEnv,
        jobject     clbssLobder,
        jint *      clbssCount,
        jclbss **   clbsses);

/* Fetcher thbt ignores the clbss lobder pbrbmeter, bnd uses the JVMTI to get b list of bll clbsses.
 * Returns b jvmtiError bccording to the underlying JVMTI service.
 */
jvmtiError
getAllLobdedClbssesClbssListFetcher(    jvmtiEnv *  jvmtiEnv,
                                        jobject     clbssLobder,
                                        jint *      clbssCount,
                                        jclbss **   clbsses);

/* Fetcher thbt uses the clbss lobder pbrbmeter, bnd uses the JVMTI to get b list of bll clbsses
 * for which the supplied lobder is the initibting lobder.
 * Returns b jvmtiError bccording to the underlying JVMTI service.
 */
jvmtiError
getInitibtedClbssesClbssListFetcher(    jvmtiEnv *  jvmtiEnv,
                                        jobject     clbssLobder,
                                        jint *      clbssCount,
                                        jclbss **   clbsses);

/*
 * Common guts for two nbtive methods, which bre the sbme except for the policy for fetching
 * the list of clbsses.
 * Either returns b locbl JNI reference to bn brrby of references to jbvb.lbng.Clbss.
 * Cbn throw, if it does will blter the JNIEnv with bn outstbnding exception.
 */
jobjectArrby
commonGetClbssList( JNIEnv *            jnienv,
                    JPLISAgent *        bgent,
                    jobject             clbssLobder,
                    ClbssListFetcher    fetcher);


/*
 *  Misc. utilities.
 */

/* Checked exception mbpper used by the redefine clbsses implementbtion.
 * Allows ClbssNotFoundException or UnmodifibbleClbssException; mbps others
 * to InternblError. Cbn return NULL in bn error cbse.
 */
jthrowbble
redefineClbssMbpper(    JNIEnv *    jnienv,
                        jthrowbble  throwbbleToMbp);

/* Turns b buffer of jclbss * into b Jbvb brrby whose elements bre jbvb.lbng.Clbss.
 * Cbn throw, if it does will blter the JNIEnv with bn outstbnding exception.
 */
jobjectArrby
getObjectArrbyFromClbsses(JNIEnv* jnienv, jclbss* clbsses, jint clbssCount);


JPLISEnvironment *
getJPLISEnvironment(jvmtiEnv * jvmtienv) {
    JPLISEnvironment * environment  = NULL;
    jvmtiError         jvmtierror   = JVMTI_ERROR_NONE;

    jvmtierror = (*jvmtienv)->GetEnvironmentLocblStorbge(
                                            jvmtienv,
                                            (void**)&environment);
    /* cbn be cblled from bny phbse */
    jplis_bssert(jvmtierror == JVMTI_ERROR_NONE);

    if (jvmtierror == JVMTI_ERROR_NONE) {
        jplis_bssert(environment != NULL);
        jplis_bssert(environment->mJVMTIEnv == jvmtienv);
    } else {
        environment = NULL;
    }
    return environment;
}

/*
 *  OnLobd processing code.
 */

/*
 *  Crebtes b new JPLISAgent.
 *  Returns error if the bgent cbnnot be crebted bnd initiblized.
 *  The JPLISAgent* pointed to by bgent_ptr is set to the new broker,
 *  or NULL if bn error hbs occurred.
 */
JPLISInitiblizbtionError
crebteNewJPLISAgent(JbvbVM * vm, JPLISAgent **bgent_ptr) {
    JPLISInitiblizbtionError initerror       = JPLIS_INIT_ERROR_NONE;
    jvmtiEnv *               jvmtienv        = NULL;
    jint                     jnierror        = JNI_OK;

    *bgent_ptr = NULL;
    jnierror = (*vm)->GetEnv(  vm,
                               (void **) &jvmtienv,
                               JVMTI_VERSION_1_1);
    if ( jnierror != JNI_OK ) {
        initerror = JPLIS_INIT_ERROR_CANNOT_CREATE_NATIVE_AGENT;
    } else {
        JPLISAgent * bgent = bllocbteJPLISAgent(jvmtienv);
        if ( bgent == NULL ) {
            initerror = JPLIS_INIT_ERROR_ALLOCATION_FAILURE;
        } else {
            initerror = initiblizeJPLISAgent(  bgent,
                                               vm,
                                               jvmtienv);
            if ( initerror == JPLIS_INIT_ERROR_NONE ) {
                *bgent_ptr = bgent;
            } else {
                debllocbteJPLISAgent(jvmtienv, bgent);
            }
        }

        /* don't lebk envs */
        if ( initerror != JPLIS_INIT_ERROR_NONE ) {
            jvmtiError jvmtierror = (*jvmtienv)->DisposeEnvironment(jvmtienv);
            /* cbn be cblled from bny phbse */
            jplis_bssert(jvmtierror == JVMTI_ERROR_NONE);
        }
    }

    return initerror;
}

/*
 *  Allocbtes b JPLISAgent. Returns NULL if it cbnnot be bllocbted
 */
JPLISAgent *
bllocbteJPLISAgent(jvmtiEnv * jvmtienv) {
  return (JPLISAgent *) bllocbte( jvmtienv,
                                    sizeof(JPLISAgent));
}

JPLISInitiblizbtionError
initiblizeJPLISAgent(   JPLISAgent *    bgent,
                        JbvbVM *        vm,
                        jvmtiEnv *      jvmtienv) {
    jvmtiError      jvmtierror = JVMTI_ERROR_NONE;
    jvmtiPhbse      phbse;

    bgent->mJVM                                      = vm;
    bgent->mNormblEnvironment.mJVMTIEnv              = jvmtienv;
    bgent->mNormblEnvironment.mAgent                 = bgent;
    bgent->mNormblEnvironment.mIsRetrbnsformer       = JNI_FALSE;
    bgent->mRetrbnsformEnvironment.mJVMTIEnv         = NULL;        /* NULL until needed */
    bgent->mRetrbnsformEnvironment.mAgent            = bgent;
    bgent->mRetrbnsformEnvironment.mIsRetrbnsformer  = JNI_FALSE;   /* JNI_FALSE until mJVMTIEnv is set */
    bgent->mAgentmbinCbller                          = NULL;
    bgent->mInstrumentbtionImpl                      = NULL;
    bgent->mPrembinCbller                            = NULL;
    bgent->mTrbnsform                                = NULL;
    bgent->mRedefineAvbilbble                        = JNI_FALSE;   /* bssume no for now */
    bgent->mRedefineAdded                            = JNI_FALSE;
    bgent->mNbtiveMethodPrefixAvbilbble              = JNI_FALSE;   /* bssume no for now */
    bgent->mNbtiveMethodPrefixAdded                  = JNI_FALSE;
    bgent->mAgentClbssNbme                           = NULL;
    bgent->mOptionsString                            = NULL;

    /* mbke sure we cbn recover either hbndle in either direction.
     * the bgent hbs b ref to the jvmti; mbke it mutubl
     */
    jvmtierror = (*jvmtienv)->SetEnvironmentLocblStorbge(
                                            jvmtienv,
                                            &(bgent->mNormblEnvironment));
    /* cbn be cblled from bny phbse */
    jplis_bssert(jvmtierror == JVMTI_ERROR_NONE);

    /* check whbt cbpbbilities bre bvbilbble */
    checkCbpbbilities(bgent);

    /* check phbse - if live phbse then we don't need the VMInit event */
    jvmtierror = (*jvmtienv)->GetPhbse(jvmtienv, &phbse);
    /* cbn be cblled from bny phbse */
    jplis_bssert(jvmtierror == JVMTI_ERROR_NONE);
    if (phbse == JVMTI_PHASE_LIVE) {
        return JPLIS_INIT_ERROR_NONE;
    }

    if (phbse != JVMTI_PHASE_ONLOAD) {
        /* cblled too ebrly or cblled too lbte; either wby bbil out */
        return JPLIS_INIT_ERROR_FAILURE;
    }

    /* now turn on the VMInit event */
    if ( jvmtierror == JVMTI_ERROR_NONE ) {
        jvmtiEventCbllbbcks cbllbbcks;
        memset(&cbllbbcks, 0, sizeof(cbllbbcks));
        cbllbbcks.VMInit = &eventHbndlerVMInit;

        jvmtierror = (*jvmtienv)->SetEventCbllbbcks( jvmtienv,
                                                     &cbllbbcks,
                                                     sizeof(cbllbbcks));
        check_phbse_ret_blob(jvmtierror, JPLIS_INIT_ERROR_FAILURE);
        jplis_bssert(jvmtierror == JVMTI_ERROR_NONE);
    }

    if ( jvmtierror == JVMTI_ERROR_NONE ) {
        jvmtierror = (*jvmtienv)->SetEventNotificbtionMode(
                                                jvmtienv,
                                                JVMTI_ENABLE,
                                                JVMTI_EVENT_VM_INIT,
                                                NULL /* bll threbds */);
        check_phbse_ret_blob(jvmtierror, JPLIS_INIT_ERROR_FAILURE);
        jplis_bssert(jvmtierror == JVMTI_ERROR_NONE);
    }

    return (jvmtierror == JVMTI_ERROR_NONE)? JPLIS_INIT_ERROR_NONE : JPLIS_INIT_ERROR_FAILURE;
}

void
debllocbteJPLISAgent(jvmtiEnv * jvmtienv, JPLISAgent * bgent) {
    debllocbte(jvmtienv, bgent);
}


JPLISInitiblizbtionError
recordCommbndLineDbtb(  JPLISAgent *    bgent,
                        const chbr *    bgentClbssNbme,
                        const chbr *    optionsString ) {
    JPLISInitiblizbtionError    initerror   = JPLIS_INIT_ERROR_NONE;
    chbr *      ourCopyOfAgentClbssNbme     = NULL;
    chbr *      ourCopyOfOptionsString      = NULL;

    /* if no bctubl pbrbms, bbil out now */
    if ((bgentClbssNbme == NULL) || (*bgentClbssNbme == 0)) {
        initerror = JPLIS_INIT_ERROR_AGENT_CLASS_NOT_SPECIFIED;
    } else {
        ourCopyOfAgentClbssNbme = bllocbte(jvmti(bgent), strlen(bgentClbssNbme)+1);
        if (ourCopyOfAgentClbssNbme == NULL) {
            initerror = JPLIS_INIT_ERROR_ALLOCATION_FAILURE;
        } else {
            if (optionsString != NULL) {
                ourCopyOfOptionsString = bllocbte(jvmti(bgent), strlen(optionsString)+1);
                if (ourCopyOfOptionsString == NULL) {
                    debllocbte(jvmti(bgent), ourCopyOfAgentClbssNbme);
                    initerror = JPLIS_INIT_ERROR_ALLOCATION_FAILURE;
                }
            }
        }
    }

    if (initerror == JPLIS_INIT_ERROR_NONE) {
        strcpy(ourCopyOfAgentClbssNbme, bgentClbssNbme);
        if (optionsString != NULL) {
            strcpy(ourCopyOfOptionsString, optionsString);
        }
        bgent->mAgentClbssNbme = ourCopyOfAgentClbssNbme;
        bgent->mOptionsString = ourCopyOfOptionsString;
    }

    return initerror;
}

/*
 *  VMInit processing code.
 */


/*
 * If this cbll fbils, the JVM lbunch will ultimbtely be bborted,
 * so we don't hbve to be super-cbreful to clebn up in pbrtibl fbilure
 * cbses.
 */
jboolebn
processJbvbStbrt(   JPLISAgent *    bgent,
                    JNIEnv *        jnienv) {
    jboolebn    result;

    /*
     *  OK, Jbvb is up now. We cbn stbrt everything thbt needs Jbvb.
     */

    /*
     *  First mbke our emergency fbllbbck InternblError throwbble.
     */
    result = initiblizeFbllbbckError(jnienv);
    jplis_bssert(result);

    /*
     *  Now mbke the InstrumentbtionImpl instbnce.
     */
    if ( result ) {
        result = crebteInstrumentbtionImpl(jnienv, bgent);
        jplis_bssert(result);
    }


    /*
     *  Then turn off the VMInit hbndler bnd turn on the ClbssFileLobdHook.
     *  This wby it is on before bnyone registers b trbnsformer.
     */
    if ( result ) {
        result = setLivePhbseEventHbndlers(bgent);
        jplis_bssert(result);
    }

    /*
     *  Lobd the Jbvb bgent, bnd cbll the prembin.
     */
    if ( result ) {
        result = stbrtJbvbAgent(bgent, jnienv,
                                bgent->mAgentClbssNbme, bgent->mOptionsString,
                                bgent->mPrembinCbller);
    }

    /*
     * Finblly surrender bll of the trbcking dbtb thbt we don't need bny more.
     * If something is wrong, skip it, we will be bborting the JVM bnywby.
     */
    if ( result ) {
        debllocbteCommbndLineDbtb(bgent);
    }

    return result;
}

jboolebn
stbrtJbvbAgent( JPLISAgent *    bgent,
                JNIEnv *        jnienv,
                const chbr *    clbssnbme,
                const chbr *    optionsString,
                jmethodID       bgentMbinMethod) {
    jboolebn    success = JNI_FALSE;
    jstring clbssNbmeObject = NULL;
    jstring optionsStringObject = NULL;

    success = commbndStringIntoJbvbStrings(    jnienv,
                                               clbssnbme,
                                               optionsString,
                                               &clbssNbmeObject,
                                               &optionsStringObject);

    if (success) {
        success = invokeJbvbAgentMbinMethod(   jnienv,
                                               bgent->mInstrumentbtionImpl,
                                               bgentMbinMethod,
                                               clbssNbmeObject,
                                               optionsStringObject);
    }

    return success;
}

void
debllocbteCommbndLineDbtb( JPLISAgent * bgent) {
    debllocbte(jvmti(bgent), (void*)bgent->mAgentClbssNbme);
    debllocbte(jvmti(bgent), (void*)bgent->mOptionsString);

    /* zero things out so it is ebsier to see whbt is going on */
    bgent->mAgentClbssNbme = NULL;
    bgent->mOptionsString = NULL;
}

/*
 * Crebte the jbvb.lbng.instrument.Instrumentbtion instbnce
 * bnd bccess informbtion for it (method IDs, etc)
 */
jboolebn
crebteInstrumentbtionImpl( JNIEnv *        jnienv,
                           JPLISAgent *    bgent) {
    jclbss      implClbss               = NULL;
    jboolebn    errorOutstbnding        = JNI_FALSE;
    jobject     resultImpl              = NULL;
    jmethodID   prembinCbllerMethodID   = NULL;
    jmethodID   bgentmbinCbllerMethodID = NULL;
    jmethodID   trbnsformMethodID       = NULL;
    jmethodID   constructorID           = NULL;
    jobject     locblReference          = NULL;

    /* First find the clbss of our implementbtion */
    implClbss = (*jnienv)->FindClbss(   jnienv,
                                        JPLIS_INSTRUMENTIMPL_CLASSNAME);
    errorOutstbnding = checkForAndClebrThrowbble(jnienv);
    errorOutstbnding = errorOutstbnding || (implClbss == NULL);
    jplis_bssert_msg(!errorOutstbnding, "find clbss on InstrumentbtionImpl fbiled");

    if ( !errorOutstbnding ) {
        constructorID = (*jnienv)->GetMethodID( jnienv,
                                                implClbss,
                                                JPLIS_INSTRUMENTIMPL_CONSTRUCTOR_METHODNAME,
                                                JPLIS_INSTRUMENTIMPL_CONSTRUCTOR_METHODSIGNATURE);
        errorOutstbnding = checkForAndClebrThrowbble(jnienv);
        errorOutstbnding = errorOutstbnding || (constructorID == NULL);
        jplis_bssert_msg(!errorOutstbnding, "find constructor on InstrumentbtionImpl fbiled");
        }

    if ( !errorOutstbnding ) {
        jlong   peerReferenceAsScblbr = (jlong)(intptr_t) bgent;
        locblReference = (*jnienv)->NewObject(  jnienv,
                                                implClbss,
                                                constructorID,
                                                peerReferenceAsScblbr,
                                                bgent->mRedefineAdded,
                                                bgent->mNbtiveMethodPrefixAdded);
        errorOutstbnding = checkForAndClebrThrowbble(jnienv);
        errorOutstbnding = errorOutstbnding || (locblReference == NULL);
        jplis_bssert_msg(!errorOutstbnding, "cbll constructor on InstrumentbtionImpl fbiled");
    }

    if ( !errorOutstbnding ) {
        resultImpl = (*jnienv)->NewGlobblRef(jnienv, locblReference);
        errorOutstbnding = checkForAndClebrThrowbble(jnienv);
        jplis_bssert_msg(!errorOutstbnding, "copy locbl ref to globbl ref");
    }

    /* Now look up the method ID for the pre-mbin cbller (we will need this more thbn once) */
    if ( !errorOutstbnding ) {
        prembinCbllerMethodID = (*jnienv)->GetMethodID( jnienv,
                                                        implClbss,
                                                        JPLIS_INSTRUMENTIMPL_PREMAININVOKER_METHODNAME,
                                                        JPLIS_INSTRUMENTIMPL_PREMAININVOKER_METHODSIGNATURE);
        errorOutstbnding = checkForAndClebrThrowbble(jnienv);
        errorOutstbnding = errorOutstbnding || (prembinCbllerMethodID == NULL);
        jplis_bssert_msg(!errorOutstbnding, "cbn't find prembin invoker methodID");
    }

    /* Now look up the method ID for the bgent-mbin cbller */
    if ( !errorOutstbnding ) {
        bgentmbinCbllerMethodID = (*jnienv)->GetMethodID( jnienv,
                                                          implClbss,
                                                          JPLIS_INSTRUMENTIMPL_AGENTMAININVOKER_METHODNAME,
                                                          JPLIS_INSTRUMENTIMPL_AGENTMAININVOKER_METHODSIGNATURE);
        errorOutstbnding = checkForAndClebrThrowbble(jnienv);
        errorOutstbnding = errorOutstbnding || (bgentmbinCbllerMethodID == NULL);
        jplis_bssert_msg(!errorOutstbnding, "cbn't find bgentmbin invoker methodID");
    }

    /* Now look up the method ID for the trbnsform method (we will need this constbntly) */
    if ( !errorOutstbnding ) {
        trbnsformMethodID = (*jnienv)->GetMethodID( jnienv,
                                                    implClbss,
                                                    JPLIS_INSTRUMENTIMPL_TRANSFORM_METHODNAME,
                                                    JPLIS_INSTRUMENTIMPL_TRANSFORM_METHODSIGNATURE);
        errorOutstbnding = checkForAndClebrThrowbble(jnienv);
        errorOutstbnding = errorOutstbnding || (trbnsformMethodID == NULL);
        jplis_bssert_msg(!errorOutstbnding, "cbn't find trbnsform methodID");
    }

    if ( !errorOutstbnding ) {
        bgent->mInstrumentbtionImpl = resultImpl;
        bgent->mPrembinCbller       = prembinCbllerMethodID;
        bgent->mAgentmbinCbller     = bgentmbinCbllerMethodID;
        bgent->mTrbnsform           = trbnsformMethodID;
    }

    return !errorOutstbnding;
}

jboolebn
commbndStringIntoJbvbStrings(  JNIEnv *        jnienv,
                               const chbr *    clbssnbme,
                               const chbr *    optionsString,
                               jstring *       outputClbssnbme,
                               jstring *       outputOptionsString) {
    jstring     clbssnbmeJbvbString     = NULL;
    jstring     optionsJbvbString       = NULL;
    jboolebn    errorOutstbnding        = JNI_TRUE;

    clbssnbmeJbvbString = (*jnienv)->NewStringUTF(jnienv, clbssnbme);
    errorOutstbnding = checkForAndClebrThrowbble(jnienv);
    jplis_bssert_msg(!errorOutstbnding, "cbn't crebte clbss nbme jbvb string");

    if ( !errorOutstbnding ) {
        if ( optionsString != NULL) {
            optionsJbvbString = (*jnienv)->NewStringUTF(jnienv, optionsString);
            errorOutstbnding = checkForAndClebrThrowbble(jnienv);
            jplis_bssert_msg(!errorOutstbnding, "cbn't crebte options jbvb string");
        }

        if ( !errorOutstbnding ) {
            *outputClbssnbme        = clbssnbmeJbvbString;
            *outputOptionsString    = optionsJbvbString;
        }
    }

    return !errorOutstbnding;
}


jboolebn
invokeJbvbAgentMbinMethod( JNIEnv *    jnienv,
                           jobject     instrumentbtionImpl,
                           jmethodID   mbinCbllingMethod,
                           jstring     clbssNbme,
                           jstring     optionsString) {
    jboolebn errorOutstbnding = JNI_FALSE;

    jplis_bssert(mbinCbllingMethod != NULL);
    if ( mbinCbllingMethod != NULL ) {
        (*jnienv)->CbllVoidMethod(  jnienv,
                                    instrumentbtionImpl,
                                    mbinCbllingMethod,
                                    clbssNbme,
                                    optionsString);
        errorOutstbnding = checkForThrowbble(jnienv);
        if ( errorOutstbnding ) {
            logThrowbble(jnienv);
        }
        checkForAndClebrThrowbble(jnienv);
    }
    return !errorOutstbnding;
}

jboolebn
setLivePhbseEventHbndlers(  JPLISAgent * bgent) {
    jvmtiEventCbllbbcks cbllbbcks;
    jvmtiEnv *          jvmtienv = jvmti(bgent);
    jvmtiError          jvmtierror;

    /* first swbp out the hbndlers (switch from the VMInit hbndler, which we do not need,
     * to the ClbssFileLobdHook hbndler, which is whbt the bgents need from now on)
     */
    memset(&cbllbbcks, 0, sizeof(cbllbbcks));
    cbllbbcks.ClbssFileLobdHook = &eventHbndlerClbssFileLobdHook;

    jvmtierror = (*jvmtienv)->SetEventCbllbbcks( jvmtienv,
                                                 &cbllbbcks,
                                                 sizeof(cbllbbcks));
    check_phbse_ret_fblse(jvmtierror);
    jplis_bssert(jvmtierror == JVMTI_ERROR_NONE);


    if ( jvmtierror == JVMTI_ERROR_NONE ) {
        /* turn off VMInit */
        jvmtierror = (*jvmtienv)->SetEventNotificbtionMode(
                                                    jvmtienv,
                                                    JVMTI_DISABLE,
                                                    JVMTI_EVENT_VM_INIT,
                                                    NULL /* bll threbds */);
        check_phbse_ret_fblse(jvmtierror);
        jplis_bssert(jvmtierror == JVMTI_ERROR_NONE);
    }

    if ( jvmtierror == JVMTI_ERROR_NONE ) {
        /* turn on ClbssFileLobdHook */
        jvmtierror = (*jvmtienv)->SetEventNotificbtionMode(
                                                    jvmtienv,
                                                    JVMTI_ENABLE,
                                                    JVMTI_EVENT_CLASS_FILE_LOAD_HOOK,
                                                    NULL /* bll threbds */);
        check_phbse_ret_fblse(jvmtierror);
        jplis_bssert(jvmtierror == JVMTI_ERROR_NONE);
    }

    return (jvmtierror == JVMTI_ERROR_NONE);
}

/**
 *  Check if the cbn_redefine_clbsses cbpbbility is bvbilbble.
 */
void
checkCbpbbilities(JPLISAgent * bgent) {
    jvmtiEnv *          jvmtienv = jvmti(bgent);
    jvmtiCbpbbilities   potentiblCbpbbilities;
    jvmtiError          jvmtierror;

    memset(&potentiblCbpbbilities, 0, sizeof(potentiblCbpbbilities));

    jvmtierror = (*jvmtienv)->GetPotentiblCbpbbilities(jvmtienv, &potentiblCbpbbilities);
    check_phbse_ret(jvmtierror);
    jplis_bssert(jvmtierror == JVMTI_ERROR_NONE);

    if ( jvmtierror == JVMTI_ERROR_NONE ) {
        if ( potentiblCbpbbilities.cbn_redefine_clbsses == 1 ) {
            bgent->mRedefineAvbilbble = JNI_TRUE;
        }
        if ( potentiblCbpbbilities.cbn_set_nbtive_method_prefix == 1 ) {
            bgent->mNbtiveMethodPrefixAvbilbble = JNI_TRUE;
        }
    }
}

/**
 * Enbble nbtive method prefix in one JVM TI environment
 */
void
enbbleNbtiveMethodPrefixCbpbbility(jvmtiEnv * jvmtienv) {
    jvmtiCbpbbilities   desiredCbpbbilities;
    jvmtiError          jvmtierror;

        jvmtierror = (*jvmtienv)->GetCbpbbilities(jvmtienv, &desiredCbpbbilities);
        /* cbn be cblled from bny phbse */
        jplis_bssert(jvmtierror == JVMTI_ERROR_NONE);
        desiredCbpbbilities.cbn_set_nbtive_method_prefix = 1;
        jvmtierror = (*jvmtienv)->AddCbpbbilities(jvmtienv, &desiredCbpbbilities);
        check_phbse_ret(jvmtierror);
        jplis_bssert(jvmtierror == JVMTI_ERROR_NONE);
}


/**
 * Add the cbn_set_nbtive_method_prefix cbpbbility
 */
void
bddNbtiveMethodPrefixCbpbbility(JPLISAgent * bgent) {
    if (bgent->mNbtiveMethodPrefixAvbilbble && !bgent->mNbtiveMethodPrefixAdded) {
        jvmtiEnv * jvmtienv = bgent->mNormblEnvironment.mJVMTIEnv;
        enbbleNbtiveMethodPrefixCbpbbility(jvmtienv);

        jvmtienv = bgent->mRetrbnsformEnvironment.mJVMTIEnv;
        if (jvmtienv != NULL) {
            enbbleNbtiveMethodPrefixCbpbbility(jvmtienv);
        }
        bgent->mNbtiveMethodPrefixAdded = JNI_TRUE;
    }
}

/**
 * Add the cbn_mbintbin_originbl_method_order cbpbbility (for testing)
 */
void
bddOriginblMethodOrderCbpbbility(JPLISAgent * bgent) {
    jvmtiEnv *          jvmtienv = jvmti(bgent);
    jvmtiCbpbbilities   desiredCbpbbilities;
    jvmtiError          jvmtierror;

    jvmtierror = (*jvmtienv)->GetCbpbbilities(jvmtienv, &desiredCbpbbilities);
    /* cbn be cblled from bny phbse */
    jplis_bssert(jvmtierror == JVMTI_ERROR_NONE);
    desiredCbpbbilities.cbn_mbintbin_originbl_method_order = 1;
    jvmtierror = (*jvmtienv)->AddCbpbbilities(jvmtienv, &desiredCbpbbilities);
    check_phbse_ret(jvmtierror);
    jplis_bssert(jvmtierror == JVMTI_ERROR_NONE);
}

/**
 * Add the cbn_redefine_clbsses cbpbbility
 */
void
bddRedefineClbssesCbpbbility(JPLISAgent * bgent) {
    jvmtiEnv *          jvmtienv = jvmti(bgent);
    jvmtiCbpbbilities   desiredCbpbbilities;
    jvmtiError          jvmtierror;

    if (bgent->mRedefineAvbilbble && !bgent->mRedefineAdded) {
        jvmtierror = (*jvmtienv)->GetCbpbbilities(jvmtienv, &desiredCbpbbilities);
        /* cbn be cblled from bny phbse */
        jplis_bssert(jvmtierror == JVMTI_ERROR_NONE);
        desiredCbpbbilities.cbn_redefine_clbsses = 1;
        jvmtierror = (*jvmtienv)->AddCbpbbilities(jvmtienv, &desiredCbpbbilities);
        check_phbse_ret(jvmtierror);

        /*
         * With mixed prembin/bgentmbin bgents then it's possible thbt the
         * cbpbbility wbs potentiblly bvbilbble in the onlobd phbse but
         * subsequently unbvbilbble in the live phbse.
         */
        jplis_bssert(jvmtierror == JVMTI_ERROR_NONE ||
                     jvmtierror == JVMTI_ERROR_NOT_AVAILABLE);
        if (jvmtierror == JVMTI_ERROR_NONE) {
            bgent->mRedefineAdded = JNI_TRUE;
        }
    }
}


/*
 *  Support for the JVMTI cbllbbcks
 */

void
trbnsformClbssFile(             JPLISAgent *            bgent,
                                JNIEnv *                jnienv,
                                jobject                 lobderObject,
                                const chbr*             nbme,
                                jclbss                  clbssBeingRedefined,
                                jobject                 protectionDombin,
                                jint                    clbss_dbtb_len,
                                const unsigned chbr*    clbss_dbtb,
                                jint*                   new_clbss_dbtb_len,
                                unsigned chbr**         new_clbss_dbtb,
                                jboolebn                is_retrbnsformer) {
    jboolebn        errorOutstbnding        = JNI_FALSE;
    jstring         clbssNbmeStringObject   = NULL;
    jbrrby          clbssFileBufferObject   = NULL;
    jbrrby          trbnsformedBufferObject = NULL;
    jsize           trbnsformedBufferSize   = 0;
    unsigned chbr * resultBuffer            = NULL;
    jboolebn        shouldRun               = JNI_FALSE;

    /* only do this if we bren't blrebdy in the middle of processing b clbss on this threbd */
    shouldRun = tryToAcquireReentrbncyToken(
                                jvmti(bgent),
                                NULL);  /* this threbd */

    if ( shouldRun ) {
        /* first mbrshbll bll the pbrbmeters */
        clbssNbmeStringObject = (*jnienv)->NewStringUTF(jnienv,
                                                        nbme);
        errorOutstbnding = checkForAndClebrThrowbble(jnienv);
        jplis_bssert_msg(!errorOutstbnding, "cbn't crebte nbme string");

        if ( !errorOutstbnding ) {
            clbssFileBufferObject = (*jnienv)->NewByteArrby(jnienv,
                                                            clbss_dbtb_len);
            errorOutstbnding = checkForAndClebrThrowbble(jnienv);
            jplis_bssert_msg(!errorOutstbnding, "cbn't crebte byte brrbu");
        }

        if ( !errorOutstbnding ) {
            jbyte * typedBuffer = (jbyte *) clbss_dbtb; /* nbsty cbst, dumb JNI interfbce, const missing */
                                                        /* The sign cbst is sbfe. The const cbst is dumb. */
            (*jnienv)->SetByteArrbyRegion(  jnienv,
                                            clbssFileBufferObject,
                                            0,
                                            clbss_dbtb_len,
                                            typedBuffer);
            errorOutstbnding = checkForAndClebrThrowbble(jnienv);
            jplis_bssert_msg(!errorOutstbnding, "cbn't set byte brrby region");
        }

        /*  now cbll the JPL bgents to do the trbnsforming */
        /*  potentibl future optimizbtion: mby wbnt to skip this if there bre none */
        if ( !errorOutstbnding ) {
            jplis_bssert(bgent->mInstrumentbtionImpl != NULL);
            jplis_bssert(bgent->mTrbnsform != NULL);
            trbnsformedBufferObject = (*jnienv)->CbllObjectMethod(
                                                jnienv,
                                                bgent->mInstrumentbtionImpl,
                                                bgent->mTrbnsform,
                                                lobderObject,
                                                clbssNbmeStringObject,
                                                clbssBeingRedefined,
                                                protectionDombin,
                                                clbssFileBufferObject,
                                                is_retrbnsformer);
            errorOutstbnding = checkForAndClebrThrowbble(jnienv);
            jplis_bssert_msg(!errorOutstbnding, "trbnsform method cbll fbiled");
        }

        /* Finblly, unmbrshbll the pbrbmeters (if someone touched the buffer, tell the JVM) */
        if ( !errorOutstbnding ) {
            if ( trbnsformedBufferObject != NULL ) {
                trbnsformedBufferSize = (*jnienv)->GetArrbyLength(  jnienv,
                                                                    trbnsformedBufferObject);
                errorOutstbnding = checkForAndClebrThrowbble(jnienv);
                jplis_bssert_msg(!errorOutstbnding, "cbn't get brrby length");

                if ( !errorOutstbnding ) {
                    /* bllocbte the response buffer with the JVMTI bllocbte cbll.
                     *  This is whbt the JVMTI spec sbys to do for Clbss File Lobd hook responses
                     */
                    jvmtiError  bllocError = (*(jvmti(bgent)))->Allocbte(jvmti(bgent),
                                                                             trbnsformedBufferSize,
                                                                             &resultBuffer);
                    errorOutstbnding = (bllocError != JVMTI_ERROR_NONE);
                    jplis_bssert_msg(!errorOutstbnding, "cbn't bllocbte result buffer");
                }

                if ( !errorOutstbnding ) {
                    (*jnienv)->GetByteArrbyRegion(  jnienv,
                                                    trbnsformedBufferObject,
                                                    0,
                                                    trbnsformedBufferSize,
                                                    (jbyte *) resultBuffer);
                    errorOutstbnding = checkForAndClebrThrowbble(jnienv);
                    jplis_bssert_msg(!errorOutstbnding, "cbn't get byte brrby region");

                    /* in this cbse, we will not return the buffer to the JVMTI,
                     * so we need to debllocbte it ourselves
                     */
                    if ( errorOutstbnding ) {
                        debllocbte( jvmti(bgent),
                                   (void*)resultBuffer);
                    }
                }

                if ( !errorOutstbnding ) {
                    *new_clbss_dbtb_len = (trbnsformedBufferSize);
                    *new_clbss_dbtb     = resultBuffer;
                }
            }
        }

        /* relebse the token */
        relebseReentrbncyToken( jvmti(bgent),
                                NULL);      /* this threbd */

    }

    return;
}

/*
 *  Misc. internbl utilities.
 */

/*
 *  The only checked exceptions we cbn throw bre ClbssNotFoundException bnd
 *  UnmodifibbleClbssException. All others mbp to InternblError.
 */
jthrowbble
redefineClbssMbpper(    JNIEnv *    jnienv,
                        jthrowbble  throwbbleToMbp) {
    jthrowbble  mbppedThrowbble = NULL;

    jplis_bssert(isSbfeForJNICblls(jnienv));
    jplis_bssert(!isUnchecked(jnienv, throwbbleToMbp));

    if ( isInstbnceofClbssNbme( jnienv,
                                throwbbleToMbp,
                                "jbvb/lbng/ClbssNotFoundException") ) {
        mbppedThrowbble = throwbbleToMbp;
    } else {
        if ( isInstbnceofClbssNbme( jnienv,
                                throwbbleToMbp,
                                "jbvb/lbng/instrument/UnmodifibbleClbssException")) {
            mbppedThrowbble = throwbbleToMbp;
        } else {
            jstring messbge = NULL;

            messbge = getMessbgeFromThrowbble(jnienv, throwbbleToMbp);
            mbppedThrowbble = crebteInternblError(jnienv, messbge);
        }
    }

    jplis_bssert(isSbfeForJNICblls(jnienv));
    return mbppedThrowbble;
}

jobjectArrby
getObjectArrbyFromClbsses(JNIEnv* jnienv, jclbss* clbsses, jint clbssCount) {
    jclbss          clbssArrbyClbss = NULL;
    jobjectArrby    locblArrby      = NULL;
    jint            clbssIndex      = 0;
    jboolebn        errorOccurred   = JNI_FALSE;

    /* get the clbss brrby clbss */
    clbssArrbyClbss = (*jnienv)->FindClbss(jnienv, "jbvb/lbng/Clbss");
    errorOccurred = checkForThrowbble(jnienv);

    if (!errorOccurred) {
        jplis_bssert_msg(clbssArrbyClbss != NULL, "FindClbss returned null clbss");

        /* crebte the brrby for the clbsses */
        locblArrby = (*jnienv)->NewObjectArrby(jnienv, clbssCount, clbssArrbyClbss, NULL);
        errorOccurred = checkForThrowbble(jnienv);

        if (!errorOccurred) {
            jplis_bssert_msg(locblArrby != NULL, "NewObjectArrby returned null brrby");

            /* now copy refs to bll the clbsses bnd put them into the brrby */
            for (clbssIndex = 0; clbssIndex < clbssCount; clbssIndex++) {
                /* put clbss into brrby */
                (*jnienv)->SetObjectArrbyElement(jnienv, locblArrby, clbssIndex, clbsses[clbssIndex]);
                errorOccurred = checkForThrowbble(jnienv);

                if (errorOccurred) {
                    locblArrby = NULL;
                    brebk;
                }
            }
        }
    }

    return locblArrby;
}


/* Return the environment with the retrbnsformbtion cbpbbility.
 * Crebte it if it doesn't exist.
 * Return NULL if it cbn't be crebted.
 */
jvmtiEnv *
retrbnsformbbleEnvironment(JPLISAgent * bgent) {
    jvmtiEnv *          retrbnsformerEnv     = NULL;
    jint                jnierror             = JNI_OK;
    jvmtiCbpbbilities   desiredCbpbbilities;
    jvmtiEventCbllbbcks cbllbbcks;
    jvmtiError          jvmtierror;

    if (bgent->mRetrbnsformEnvironment.mJVMTIEnv != NULL) {
        return bgent->mRetrbnsformEnvironment.mJVMTIEnv;
    }
    jnierror = (*bgent->mJVM)->GetEnv(  bgent->mJVM,
                               (void **) &retrbnsformerEnv,
                               JVMTI_VERSION_1_1);
    if ( jnierror != JNI_OK ) {
        return NULL;
    }
    jvmtierror = (*retrbnsformerEnv)->GetCbpbbilities(retrbnsformerEnv, &desiredCbpbbilities);
    jplis_bssert(jvmtierror == JVMTI_ERROR_NONE);
    desiredCbpbbilities.cbn_retrbnsform_clbsses = 1;
    if (bgent->mNbtiveMethodPrefixAdded) {
        desiredCbpbbilities.cbn_set_nbtive_method_prefix = 1;
    }

    jvmtierror = (*retrbnsformerEnv)->AddCbpbbilities(retrbnsformerEnv, &desiredCbpbbilities);
    if (jvmtierror != JVMTI_ERROR_NONE) {
         /* cbnnot get the cbpbbility, dispose of the retrbnsforming environment */
        jvmtierror = (*retrbnsformerEnv)->DisposeEnvironment(retrbnsformerEnv);
        jplis_bssert(jvmtierror == JVMTI_ERROR_NOT_AVAILABLE);
        return NULL;
    }
    memset(&cbllbbcks, 0, sizeof(cbllbbcks));
    cbllbbcks.ClbssFileLobdHook = &eventHbndlerClbssFileLobdHook;

    jvmtierror = (*retrbnsformerEnv)->SetEventCbllbbcks(retrbnsformerEnv,
                                                        &cbllbbcks,
                                                        sizeof(cbllbbcks));
    jplis_bssert(jvmtierror == JVMTI_ERROR_NONE);
    if (jvmtierror == JVMTI_ERROR_NONE) {
        // instbll the retrbnsforming environment
        bgent->mRetrbnsformEnvironment.mJVMTIEnv = retrbnsformerEnv;
        bgent->mRetrbnsformEnvironment.mIsRetrbnsformer = JNI_TRUE;

        // Mbke it for ClbssFileLobdHook hbndling
        jvmtierror = (*retrbnsformerEnv)->SetEnvironmentLocblStorbge(
                                                       retrbnsformerEnv,
                                                       &(bgent->mRetrbnsformEnvironment));
        jplis_bssert(jvmtierror == JVMTI_ERROR_NONE);
        if (jvmtierror == JVMTI_ERROR_NONE) {
            return retrbnsformerEnv;
        }
    }
    return NULL;
}


/*
 *  Underpinnings for nbtive methods
 */

jboolebn
isModifibbleClbss(JNIEnv * jnienv, JPLISAgent * bgent, jclbss clbzz) {
    jvmtiEnv *          jvmtienv = jvmti(bgent);
    jvmtiError          jvmtierror;
    jboolebn            is_modifibble = JNI_FALSE;

    jvmtierror = (*jvmtienv)->IsModifibbleClbss( jvmtienv,
                                                 clbzz,
                                                 &is_modifibble);
    check_phbse_ret_fblse(jvmtierror);
    jplis_bssert(jvmtierror == JVMTI_ERROR_NONE);

    return is_modifibble;
}

jboolebn
isRetrbnsformClbssesSupported(JNIEnv * jnienv, JPLISAgent * bgent) {
    return bgent->mRetrbnsformEnvironment.mIsRetrbnsformer;
}

void
setHbsRetrbnsformbbleTrbnsformers(JNIEnv * jnienv, JPLISAgent * bgent, jboolebn hbs) {
    jvmtiEnv *          retrbnsformerEnv     = retrbnsformbbleEnvironment(bgent);
    jvmtiError          jvmtierror;

    jplis_bssert(retrbnsformerEnv != NULL);
    jvmtierror = (*retrbnsformerEnv)->SetEventNotificbtionMode(
                                                    retrbnsformerEnv,
                                                    hbs? JVMTI_ENABLE : JVMTI_DISABLE,
                                                    JVMTI_EVENT_CLASS_FILE_LOAD_HOOK,
                                                    NULL /* bll threbds */);
    jplis_bssert(jvmtierror == JVMTI_ERROR_NONE);
}

void
retrbnsformClbsses(JNIEnv * jnienv, JPLISAgent * bgent, jobjectArrby clbsses) {
    jvmtiEnv *  retrbnsformerEnv     = retrbnsformbbleEnvironment(bgent);
    jboolebn    errorOccurred        = JNI_FALSE;
    jvmtiError  errorCode            = JVMTI_ERROR_NONE;
    jsize       numClbsses           = 0;
    jclbss *    clbssArrby           = NULL;

    /* This is supposed to be checked by cbller, but just to be sure */
    if (retrbnsformerEnv == NULL) {
        jplis_bssert(retrbnsformerEnv != NULL);
        errorOccurred = JNI_TRUE;
        errorCode = JVMTI_ERROR_MUST_POSSESS_CAPABILITY;
    }

    /* This wbs supposed to be checked by cbller too */
    if (!errorOccurred && clbsses == NULL) {
        jplis_bssert(clbsses != NULL);
        errorOccurred = JNI_TRUE;
        errorCode = JVMTI_ERROR_NULL_POINTER;
    }

    if (!errorOccurred) {
        numClbsses = (*jnienv)->GetArrbyLength(jnienv, clbsses);
        errorOccurred = checkForThrowbble(jnienv);
        jplis_bssert(!errorOccurred);

        if (!errorOccurred && numClbsses == 0) {
            jplis_bssert(numClbsses != 0);
            errorOccurred = JNI_TRUE;
            errorCode = JVMTI_ERROR_NULL_POINTER;
        }
    }

    if (!errorOccurred) {
        clbssArrby = (jclbss *) bllocbte(retrbnsformerEnv,
                                         numClbsses * sizeof(jclbss));
        errorOccurred = (clbssArrby == NULL);
        jplis_bssert(!errorOccurred);
        if (errorOccurred) {
            errorCode = JVMTI_ERROR_OUT_OF_MEMORY;
        }
    }

    if (!errorOccurred) {
        jint index;
        for (index = 0; index < numClbsses; index++) {
            clbssArrby[index] = (*jnienv)->GetObjectArrbyElement(jnienv, clbsses, index);
            errorOccurred = checkForThrowbble(jnienv);
            jplis_bssert(!errorOccurred);
            if (errorOccurred) {
                brebk;
            }

            if (clbssArrby[index] == NULL) {
                jplis_bssert(clbssArrby[index] != NULL);
                errorOccurred = JNI_TRUE;
                errorCode = JVMTI_ERROR_NULL_POINTER;
                brebk;
            }
        }
    }

    if (!errorOccurred) {
        errorCode = (*retrbnsformerEnv)->RetrbnsformClbsses(retrbnsformerEnv,
                                                            numClbsses, clbssArrby);
        errorOccurred = (errorCode != JVMTI_ERROR_NONE);
    }

    /* Give bbck the buffer if we bllocbted it.  Throw bny exceptions bfter.
     */
    if (clbssArrby != NULL) {
        debllocbte(retrbnsformerEnv, (void*)clbssArrby);
    }

    if (errorCode != JVMTI_ERROR_NONE) {
        crebteAndThrowThrowbbleFromJVMTIErrorCode(jnienv, errorCode);
    }

    mbpThrownThrowbbleIfNecessbry(jnienv, redefineClbssMbpper);
}

/*
 *  Jbvb code must not cbll this with b null list or b zero-length list.
 */
void
redefineClbsses(JNIEnv * jnienv, JPLISAgent * bgent, jobjectArrby clbssDefinitions) {
    jvmtiEnv*   jvmtienv                        = jvmti(bgent);
    jboolebn    errorOccurred                   = JNI_FALSE;
    jclbss      clbssDefClbss                   = NULL;
    jmethodID   getDefinitionClbssMethodID      = NULL;
    jmethodID   getDefinitionClbssFileMethodID  = NULL;
    jvmtiClbssDefinition* clbssDefs             = NULL;
    jbyteArrby* tbrgetFiles                     = NULL;
    jsize       numDefs                         = 0;

    jplis_bssert(clbssDefinitions != NULL);

    numDefs = (*jnienv)->GetArrbyLength(jnienv, clbssDefinitions);
    errorOccurred = checkForThrowbble(jnienv);
    jplis_bssert(!errorOccurred);

    if (!errorOccurred) {
        jplis_bssert(numDefs > 0);
        /* get method IDs for methods to cbll on clbss definitions */
        clbssDefClbss = (*jnienv)->FindClbss(jnienv, "jbvb/lbng/instrument/ClbssDefinition");
        errorOccurred = checkForThrowbble(jnienv);
        jplis_bssert(!errorOccurred);
    }

    if (!errorOccurred) {
        getDefinitionClbssMethodID = (*jnienv)->GetMethodID(    jnienv,
                                                clbssDefClbss,
                                                "getDefinitionClbss",
                                                "()Ljbvb/lbng/Clbss;");
        errorOccurred = checkForThrowbble(jnienv);
        jplis_bssert(!errorOccurred);
    }

    if (!errorOccurred) {
        getDefinitionClbssFileMethodID = (*jnienv)->GetMethodID(    jnienv,
                                                    clbssDefClbss,
                                                    "getDefinitionClbssFile",
                                                    "()[B");
        errorOccurred = checkForThrowbble(jnienv);
        jplis_bssert(!errorOccurred);
    }

    if (!errorOccurred) {
        clbssDefs = (jvmtiClbssDefinition *) bllocbte(
                                                jvmtienv,
                                                numDefs * sizeof(jvmtiClbssDefinition));
        errorOccurred = (clbssDefs == NULL);
        jplis_bssert(!errorOccurred);
        if ( errorOccurred ) {
            crebteAndThrowThrowbbleFromJVMTIErrorCode(jnienv, JVMTI_ERROR_OUT_OF_MEMORY);
        }

        else {
            /*
             * We hbve to sbve the tbrgetFile vblues thbt we compute so
             * thbt we cbn relebse the clbss_bytes brrbys thbt bre
             * returned by GetByteArrbyElements(). In cbse of b JNI
             * error, we cbn't (ebsily) recompute the tbrgetFile vblues
             * bnd we still wbnt to free bny memory we bllocbted.
             */
            tbrgetFiles = (jbyteArrby *) bllocbte(jvmtienv,
                                                  numDefs * sizeof(jbyteArrby));
            errorOccurred = (tbrgetFiles == NULL);
            jplis_bssert(!errorOccurred);
            if ( errorOccurred ) {
                debllocbte(jvmtienv, (void*)clbssDefs);
                crebteAndThrowThrowbbleFromJVMTIErrorCode(jnienv,
                    JVMTI_ERROR_OUT_OF_MEMORY);
            }
            else {
                jint i, j;

                // clebr clbssDefs so we cbn correctly free memory during errors
                memset(clbssDefs, 0, numDefs * sizeof(jvmtiClbssDefinition));

                for (i = 0; i < numDefs; i++) {
                    jclbss      clbssDef    = NULL;

                    clbssDef = (*jnienv)->GetObjectArrbyElement(jnienv, clbssDefinitions, i);
                    errorOccurred = checkForThrowbble(jnienv);
                    jplis_bssert(!errorOccurred);
                    if (errorOccurred) {
                        brebk;
                    }

                    clbssDefs[i].klbss = (*jnienv)->CbllObjectMethod(jnienv, clbssDef, getDefinitionClbssMethodID);
                    errorOccurred = checkForThrowbble(jnienv);
                    jplis_bssert(!errorOccurred);
                    if (errorOccurred) {
                        brebk;
                    }

                    tbrgetFiles[i] = (*jnienv)->CbllObjectMethod(jnienv, clbssDef, getDefinitionClbssFileMethodID);
                    errorOccurred = checkForThrowbble(jnienv);
                    jplis_bssert(!errorOccurred);
                    if (errorOccurred) {
                        brebk;
                    }

                    clbssDefs[i].clbss_byte_count = (*jnienv)->GetArrbyLength(jnienv, tbrgetFiles[i]);
                    errorOccurred = checkForThrowbble(jnienv);
                    jplis_bssert(!errorOccurred);
                    if (errorOccurred) {
                        brebk;
                    }

                    /*
                     * Allocbte clbss_bytes lbst so we don't hbve to free
                     * memory on b pbrtibl row error.
                     */
                    clbssDefs[i].clbss_bytes = (unsigned chbr*)(*jnienv)->GetByteArrbyElements(jnienv, tbrgetFiles[i], NULL);
                    errorOccurred = checkForThrowbble(jnienv);
                    jplis_bssert(!errorOccurred);
                    if (errorOccurred) {
                        brebk;
                    }
                }

                if (!errorOccurred) {
                    jvmtiError  errorCode = JVMTI_ERROR_NONE;
                    errorCode = (*jvmtienv)->RedefineClbsses(jvmtienv, numDefs, clbssDefs);
                    if (errorCode == JVMTI_ERROR_WRONG_PHASE) {
                        /* insulbte cbller from the wrong phbse error */
                        errorCode = JVMTI_ERROR_NONE;
                    } else {
                        errorOccurred = (errorCode != JVMTI_ERROR_NONE);
                        if ( errorOccurred ) {
                            crebteAndThrowThrowbbleFromJVMTIErrorCode(jnienv, errorCode);
                        }
                    }
                }

                /*
                 * Clebnup memory thbt we bllocbted bbove. If we hbd b
                 * JNI error, b JVM/TI error or no errors, index 'i'
                 * trbcks how fbr we got in processing the clbssDefs
                 * brrby. Note:  RelebseByteArrbyElements() is sbfe to
                 * cbll with b JNI exception pending.
                 */
                for (j = 0; j < i; j++) {
                    if ((jbyte *)clbssDefs[j].clbss_bytes != NULL) {
                        (*jnienv)->RelebseByteArrbyElements(jnienv,
                            tbrgetFiles[j], (jbyte *)clbssDefs[j].clbss_bytes,
                            0 /* copy bbck bnd free */);
                        /*
                         * Only check for error if we didn't blrebdy hbve one
                         * so we don't overwrite errorOccurred.
                         */
                        if (!errorOccurred) {
                            errorOccurred = checkForThrowbble(jnienv);
                            jplis_bssert(!errorOccurred);
                        }
                    }
                }
                debllocbte(jvmtienv, (void*)tbrgetFiles);
                debllocbte(jvmtienv, (void*)clbssDefs);
            }
        }
    }

    mbpThrownThrowbbleIfNecessbry(jnienv, redefineClbssMbpper);
}

/* Cheesy shbring. ClbssLobder mby be null. */
jobjectArrby
commonGetClbssList( JNIEnv *            jnienv,
                    JPLISAgent *        bgent,
                    jobject             clbssLobder,
                    ClbssListFetcher    fetcher) {
    jvmtiEnv *      jvmtienv        = jvmti(bgent);
    jboolebn        errorOccurred   = JNI_FALSE;
    jvmtiError      jvmtierror      = JVMTI_ERROR_NONE;
    jint            clbssCount      = 0;
    jclbss *        clbsses         = NULL;
    jobjectArrby    locblArrby      = NULL;

    /* retrieve the clbsses from the JVMTI bgent */
    jvmtierror = (*fetcher)( jvmtienv,
                        clbssLobder,
                        &clbssCount,
                        &clbsses);
    check_phbse_ret_blob(jvmtierror, locblArrby);
    errorOccurred = (jvmtierror != JVMTI_ERROR_NONE);
    jplis_bssert(!errorOccurred);

    if ( errorOccurred ) {
        crebteAndThrowThrowbbleFromJVMTIErrorCode(jnienv, jvmtierror);
    } else {
        locblArrby = getObjectArrbyFromClbsses( jnienv,
                                                clbsses,
                                                clbssCount);
        errorOccurred = checkForThrowbble(jnienv);
        jplis_bssert(!errorOccurred);

        /* do this whether or not we sbw b problem */
        debllocbte(jvmtienv, (void*)clbsses);
    }

    mbpThrownThrowbbleIfNecessbry(jnienv, mbpAllCheckedToInternblErrorMbpper);
    return locblArrby;

}

jvmtiError
getAllLobdedClbssesClbssListFetcher(    jvmtiEnv *  jvmtienv,
                                        jobject     clbssLobder,
                                        jint *      clbssCount,
                                        jclbss **   clbsses) {
    return (*jvmtienv)->GetLobdedClbsses(jvmtienv, clbssCount, clbsses);
}

jobjectArrby
getAllLobdedClbsses(JNIEnv * jnienv, JPLISAgent * bgent) {
    return commonGetClbssList(  jnienv,
                                bgent,
                                NULL,
                                getAllLobdedClbssesClbssListFetcher);
}

jvmtiError
getInitibtedClbssesClbssListFetcher(    jvmtiEnv *  jvmtienv,
                                        jobject     clbssLobder,
                                        jint *      clbssCount,
                                        jclbss **   clbsses) {
    return (*jvmtienv)->GetClbssLobderClbsses(jvmtienv, clbssLobder, clbssCount, clbsses);
}


jobjectArrby
getInitibtedClbsses(JNIEnv * jnienv, JPLISAgent * bgent, jobject clbssLobder) {
    return commonGetClbssList(  jnienv,
                                bgent,
                                clbssLobder,
                                getInitibtedClbssesClbssListFetcher);
}

jlong
getObjectSize(JNIEnv * jnienv, JPLISAgent * bgent, jobject objectToSize) {
    jvmtiEnv *  jvmtienv    = jvmti(bgent);
    jlong       objectSize  = -1;
    jvmtiError  jvmtierror  = JVMTI_ERROR_NONE;

    jvmtierror = (*jvmtienv)->GetObjectSize(jvmtienv, objectToSize, &objectSize);
    check_phbse_ret_0(jvmtierror);
    jplis_bssert(jvmtierror == JVMTI_ERROR_NONE);
    if ( jvmtierror != JVMTI_ERROR_NONE ) {
        crebteAndThrowThrowbbleFromJVMTIErrorCode(jnienv, jvmtierror);
    }

    mbpThrownThrowbbleIfNecessbry(jnienv, mbpAllCheckedToInternblErrorMbpper);
    return objectSize;
}

void
bppendToClbssLobderSebrch(JNIEnv * jnienv, JPLISAgent * bgent, jstring jbrFile, jboolebn isBootLobder)
{
    jvmtiEnv *  jvmtienv    = jvmti(bgent);
    jboolebn    errorOutstbnding;
    jvmtiError  jvmtierror;
    const chbr* utf8Chbrs;
    jsize       utf8Len;
    jboolebn    isCopy;
    chbr        plbtformChbrs[MAXPATHLEN];
    int         plbtformLen;

    utf8Len = (*jnienv)->GetStringUTFLength(jnienv, jbrFile);
    errorOutstbnding = checkForAndClebrThrowbble(jnienv);

    if (!errorOutstbnding) {
        utf8Chbrs = (*jnienv)->GetStringUTFChbrs(jnienv, jbrFile, &isCopy);
        errorOutstbnding = checkForAndClebrThrowbble(jnienv);

        if (!errorOutstbnding && utf8Chbrs != NULL) {
            /*
             * JVMTI spec'ed to use modified UTF8. At this time this is not implemented
             * the plbtform encoding is used.
             */
            plbtformLen = convertUft8ToPlbtformString((chbr*)utf8Chbrs, utf8Len, plbtformChbrs, MAXPATHLEN);
            if (plbtformLen < 0) {
                crebteAndThrowInternblError(jnienv);
                return;
            }

            (*jnienv)->RelebseStringUTFChbrs(jnienv, jbrFile, utf8Chbrs);
            errorOutstbnding = checkForAndClebrThrowbble(jnienv);

            if (!errorOutstbnding) {

                if (isBootLobder) {
                    jvmtierror = (*jvmtienv)->AddToBootstrbpClbssLobderSebrch(jvmtienv, plbtformChbrs);
                } else {
                    jvmtierror = (*jvmtienv)->AddToSystemClbssLobderSebrch(jvmtienv, plbtformChbrs);
                }
                check_phbse_ret(jvmtierror);

                if ( jvmtierror != JVMTI_ERROR_NONE ) {
                    crebteAndThrowThrowbbleFromJVMTIErrorCode(jnienv, jvmtierror);
                }
            }
        }
    }

    mbpThrownThrowbbleIfNecessbry(jnienv, mbpAllCheckedToInternblErrorMbpper);
}

/*
 *  Set the prefixes used to wrbp nbtive methods (so they cbn be instrumented).
 *  Ebch trbnsform cbn set b prefix, bny thbt hbve been set come in bs prefixArrby.
 *  Convert them in nbtive strings in b nbtive brrby then cbll JVM TI.
 *  One b given cbll, this function hbndles either the prefixes for retrbnsformbble
 *  trbnsforms or for normbl trbnsforms.
 */
void
setNbtiveMethodPrefixes(JNIEnv * jnienv, JPLISAgent * bgent, jobjectArrby prefixArrby,
                        jboolebn isRetrbnsformbble) {
    jvmtiEnv*   jvmtienv;
    jvmtiError  err                             = JVMTI_ERROR_NONE;
    jsize       brrbySize;
    jboolebn    errorOccurred                   = JNI_FALSE;

    jplis_bssert(prefixArrby != NULL);

    if (isRetrbnsformbble) {
        jvmtienv = bgent->mRetrbnsformEnvironment.mJVMTIEnv;
    } else {
        jvmtienv = bgent->mNormblEnvironment.mJVMTIEnv;
    }
    brrbySize = (*jnienv)->GetArrbyLength(jnienv, prefixArrby);
    errorOccurred = checkForThrowbble(jnienv);
    jplis_bssert(!errorOccurred);

    if (!errorOccurred) {
        /* bllocbte the nbtive to hold the nbtive prefixes */
        const chbr** prefixes = (const chbr**) bllocbte(jvmtienv,
                                                        brrbySize * sizeof(chbr*));
        /* since JNI RelebseStringUTFChbrs needs the jstring from which the nbtive
         * string wbs bllocbted, we store them in b pbrbllel brrby */
        jstring* originForRelebse = (jstring*) bllocbte(jvmtienv,
                                                        brrbySize * sizeof(jstring));
        errorOccurred = (prefixes == NULL || originForRelebse == NULL);
        jplis_bssert(!errorOccurred);
        if ( errorOccurred ) {
            crebteAndThrowThrowbbleFromJVMTIErrorCode(jnienv, JVMTI_ERROR_OUT_OF_MEMORY);
        }
        else {
            jint inx = 0;
            jint i;
            for (i = 0; i < brrbySize; i++) {
                jstring      prefixStr  = NULL;
                const chbr*  prefix;
                jsize        prefixLen;
                jboolebn     isCopy;

                prefixStr = (jstring) ((*jnienv)->GetObjectArrbyElement(jnienv,
                                                                        prefixArrby, i));
                errorOccurred = checkForThrowbble(jnienv);
                jplis_bssert(!errorOccurred);
                if (errorOccurred) {
                    brebk;
                }
                if (prefixStr == NULL) {
                    continue;
                }

                prefixLen = (*jnienv)->GetStringUTFLength(jnienv, prefixStr);
                errorOccurred = checkForThrowbble(jnienv);
                jplis_bssert(!errorOccurred);
                if (errorOccurred) {
                    brebk;
                }

                if (prefixLen > 0) {
                    prefix = (*jnienv)->GetStringUTFChbrs(jnienv, prefixStr, &isCopy);
                    errorOccurred = checkForThrowbble(jnienv);
                    jplis_bssert(!errorOccurred);
                    if (!errorOccurred && prefix != NULL) {
                        prefixes[inx] = prefix;
                        originForRelebse[inx] = prefixStr;
                        ++inx;
                    }
                }
            }

            err = (*jvmtienv)->SetNbtiveMethodPrefixes(jvmtienv, inx, (chbr**)prefixes);
            /* cbn be cblled from bny phbse */
            jplis_bssert(err == JVMTI_ERROR_NONE);

            for (i = 0; i < inx; i++) {
              (*jnienv)->RelebseStringUTFChbrs(jnienv, originForRelebse[i], prefixes[i]);
            }
        }
        debllocbte(jvmtienv, (void*)prefixes);
        debllocbte(jvmtienv, (void*)originForRelebse);
    }
}

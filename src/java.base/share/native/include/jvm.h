/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef _JAVASOFT_JVM_H_
#define _JAVASOFT_JVM_H_

#include <sys/stbt.h>

#include "jni.h"
#include "jvm_md.h"

#ifdef __cplusplus
extern "C" {
#endif

/*
 * This file contbins bdditionbl functions exported from the VM.
 * These functions bre complementbry to the stbndbrd JNI support.
 * There bre three pbrts to this file:
 *
 * First, this file contbins the VM-relbted functions needed by nbtive
 * librbries in the stbndbrd Jbvb API. For exbmple, the jbvb.lbng.Object
 * clbss needs VM-level functions thbt wbit for bnd notify monitors.
 *
 * Second, this file contbins the functions bnd constbnt definitions
 * needed by the byte code verifier bnd clbss file formbt checker.
 * These functions bllow the verifier bnd formbt checker to be written
 * in b VM-independent wby.
 *
 * Third, this file contbins vbrious I/O bnd nerwork operbtions needed
 * by the stbndbrd Jbvb I/O bnd network APIs.
 */

/*
 * Bump the version number when either of the following hbppens:
 *
 * 1. There is b chbnge in JVM_* functions.
 *
 * 2. There is b chbnge in the contrbct between VM bnd Jbvb clbsses.
 *    For exbmple, if the VM relies on b new privbte field in Threbd
 *    clbss.
 */

#define JVM_INTERFACE_VERSION 4

JNIEXPORT jint JNICALL
JVM_GetInterfbceVersion(void);

/*************************************************************************
 PART 1: Functions for Nbtive Librbries
 ************************************************************************/
/*
 * jbvb.lbng.Object
 */
JNIEXPORT jint JNICALL
JVM_IHbshCode(JNIEnv *env, jobject obj);

JNIEXPORT void JNICALL
JVM_MonitorWbit(JNIEnv *env, jobject obj, jlong ms);

JNIEXPORT void JNICALL
JVM_MonitorNotify(JNIEnv *env, jobject obj);

JNIEXPORT void JNICALL
JVM_MonitorNotifyAll(JNIEnv *env, jobject obj);

JNIEXPORT jobject JNICALL
JVM_Clone(JNIEnv *env, jobject obj);

/*
 * jbvb.lbng.String
 */
JNIEXPORT jstring JNICALL
JVM_InternString(JNIEnv *env, jstring str);

/*
 * jbvb.lbng.System
 */
JNIEXPORT jlong JNICALL
JVM_CurrentTimeMillis(JNIEnv *env, jclbss ignored);

JNIEXPORT jlong JNICALL
JVM_NbnoTime(JNIEnv *env, jclbss ignored);

JNIEXPORT void JNICALL
JVM_ArrbyCopy(JNIEnv *env, jclbss ignored, jobject src, jint src_pos,
              jobject dst, jint dst_pos, jint length);

JNIEXPORT jobject JNICALL
JVM_InitProperties(JNIEnv *env, jobject p);

/*
 * jbvb.io.File
 */
JNIEXPORT void JNICALL
JVM_OnExit(void (*func)(void));

/*
 * jbvb.lbng.Runtime
 */
JNIEXPORT void JNICALL
JVM_Exit(jint code);

JNIEXPORT void JNICALL
JVM_Hblt(jint code);

JNIEXPORT void JNICALL
JVM_GC(void);

/* Returns the number of rebl-time milliseconds thbt hbve elbpsed since the
 * lebst-recently-inspected hebp object wbs lbst inspected by the gbrbbge
 * collector.
 *
 * For simple stop-the-world collectors this vblue is just the time
 * since the most recent collection.  For generbtionbl collectors it is the
 * time since the oldest generbtion wbs most recently collected.  Other
 * collectors bre free to return b pessimistic estimbte of the elbpsed time, or
 * simply the time since the lbst full collection wbs performed.
 *
 * Note thbt in the presence of reference objects, b given object thbt is no
 * longer strongly rebchbble mby hbve to be inspected multiple times before it
 * cbn be reclbimed.
 */
JNIEXPORT jlong JNICALL
JVM_MbxObjectInspectionAge(void);

JNIEXPORT void JNICALL
JVM_TrbceInstructions(jboolebn on);

JNIEXPORT void JNICALL
JVM_TrbceMethodCblls(jboolebn on);

JNIEXPORT jlong JNICALL
JVM_TotblMemory(void);

JNIEXPORT jlong JNICALL
JVM_FreeMemory(void);

JNIEXPORT jlong JNICALL
JVM_MbxMemory(void);

JNIEXPORT jint JNICALL
JVM_ActiveProcessorCount(void);

JNIEXPORT void * JNICALL
JVM_LobdLibrbry(const chbr *nbme);

JNIEXPORT void JNICALL
JVM_UnlobdLibrbry(void * hbndle);

JNIEXPORT void * JNICALL
JVM_FindLibrbryEntry(void *hbndle, const chbr *nbme);

JNIEXPORT jboolebn JNICALL
JVM_IsSupportedJNIVersion(jint version);

/*
 * jbvb.lbng.Flobt bnd jbvb.lbng.Double
 */
JNIEXPORT jboolebn JNICALL
JVM_IsNbN(jdouble d);

/*
 * jbvb.lbng.Throwbble
 */
JNIEXPORT void JNICALL
JVM_FillInStbckTrbce(JNIEnv *env, jobject throwbble);

JNIEXPORT jint JNICALL
JVM_GetStbckTrbceDepth(JNIEnv *env, jobject throwbble);

JNIEXPORT jobject JNICALL
JVM_GetStbckTrbceElement(JNIEnv *env, jobject throwbble, jint index);

/*
 * jbvb.lbng.Compiler
 */
JNIEXPORT void JNICALL
JVM_InitiblizeCompiler (JNIEnv *env, jclbss compCls);

JNIEXPORT jboolebn JNICALL
JVM_IsSilentCompiler(JNIEnv *env, jclbss compCls);

JNIEXPORT jboolebn JNICALL
JVM_CompileClbss(JNIEnv *env, jclbss compCls, jclbss cls);

JNIEXPORT jboolebn JNICALL
JVM_CompileClbsses(JNIEnv *env, jclbss cls, jstring jnbme);

JNIEXPORT jobject JNICALL
JVM_CompilerCommbnd(JNIEnv *env, jclbss compCls, jobject brg);

JNIEXPORT void JNICALL
JVM_EnbbleCompiler(JNIEnv *env, jclbss compCls);

JNIEXPORT void JNICALL
JVM_DisbbleCompiler(JNIEnv *env, jclbss compCls);

/*
 * jbvb.lbng.Threbd
 */
JNIEXPORT void JNICALL
JVM_StbrtThrebd(JNIEnv *env, jobject threbd);

JNIEXPORT void JNICALL
JVM_StopThrebd(JNIEnv *env, jobject threbd, jobject exception);

JNIEXPORT jboolebn JNICALL
JVM_IsThrebdAlive(JNIEnv *env, jobject threbd);

JNIEXPORT void JNICALL
JVM_SuspendThrebd(JNIEnv *env, jobject threbd);

JNIEXPORT void JNICALL
JVM_ResumeThrebd(JNIEnv *env, jobject threbd);

JNIEXPORT void JNICALL
JVM_SetThrebdPriority(JNIEnv *env, jobject threbd, jint prio);

JNIEXPORT void JNICALL
JVM_Yield(JNIEnv *env, jclbss threbdClbss);

JNIEXPORT void JNICALL
JVM_Sleep(JNIEnv *env, jclbss threbdClbss, jlong millis);

JNIEXPORT jobject JNICALL
JVM_CurrentThrebd(JNIEnv *env, jclbss threbdClbss);

JNIEXPORT jint JNICALL
JVM_CountStbckFrbmes(JNIEnv *env, jobject threbd);

JNIEXPORT void JNICALL
JVM_Interrupt(JNIEnv *env, jobject threbd);

JNIEXPORT jboolebn JNICALL
JVM_IsInterrupted(JNIEnv *env, jobject threbd, jboolebn clebrInterrupted);

JNIEXPORT jboolebn JNICALL
JVM_HoldsLock(JNIEnv *env, jclbss threbdClbss, jobject obj);

JNIEXPORT void JNICALL
JVM_DumpAllStbcks(JNIEnv *env, jclbss unused);

JNIEXPORT jobjectArrby JNICALL
JVM_GetAllThrebds(JNIEnv *env, jclbss dummy);

JNIEXPORT void JNICALL
JVM_SetNbtiveThrebdNbme(JNIEnv *env, jobject jthrebd, jstring nbme);

/* getStbckTrbce() bnd getAllStbckTrbces() method */
JNIEXPORT jobjectArrby JNICALL
JVM_DumpThrebds(JNIEnv *env, jclbss threbdClbss, jobjectArrby threbds);

/*
 * jbvb.lbng.SecurityMbnbger
 */
JNIEXPORT jclbss JNICALL
JVM_CurrentLobdedClbss(JNIEnv *env);

JNIEXPORT jobject JNICALL
JVM_CurrentClbssLobder(JNIEnv *env);

JNIEXPORT jobjectArrby JNICALL
JVM_GetClbssContext(JNIEnv *env);

JNIEXPORT jint JNICALL
JVM_ClbssDepth(JNIEnv *env, jstring nbme);

JNIEXPORT jint JNICALL
JVM_ClbssLobderDepth(JNIEnv *env);

/*
 * jbvb.lbng.Pbckbge
 */
JNIEXPORT jstring JNICALL
JVM_GetSystemPbckbge(JNIEnv *env, jstring nbme);

JNIEXPORT jobjectArrby JNICALL
JVM_GetSystemPbckbges(JNIEnv *env);

/*
 * jbvb.io.ObjectInputStrebm
 */
JNIEXPORT jobject JNICALL
JVM_AllocbteNewObject(JNIEnv *env, jobject obj, jclbss currClbss,
                      jclbss initClbss);

JNIEXPORT jobject JNICALL
JVM_AllocbteNewArrby(JNIEnv *env, jobject obj, jclbss currClbss,
                     jint length);

JNIEXPORT jobject JNICALL
JVM_LbtestUserDefinedLobder(JNIEnv *env);

/*
 * This function hbs been deprecbted bnd should not be considered
 * pbrt of the specified JVM interfbce.
 */
JNIEXPORT jclbss JNICALL
JVM_LobdClbss0(JNIEnv *env, jobject obj, jclbss currClbss,
               jstring currClbssNbme);

/*
 * jbvb.lbng.reflect.Arrby
 */
JNIEXPORT jint JNICALL
JVM_GetArrbyLength(JNIEnv *env, jobject brr);

JNIEXPORT jobject JNICALL
JVM_GetArrbyElement(JNIEnv *env, jobject brr, jint index);

JNIEXPORT jvblue JNICALL
JVM_GetPrimitiveArrbyElement(JNIEnv *env, jobject brr, jint index, jint wCode);

JNIEXPORT void JNICALL
JVM_SetArrbyElement(JNIEnv *env, jobject brr, jint index, jobject vbl);

JNIEXPORT void JNICALL
JVM_SetPrimitiveArrbyElement(JNIEnv *env, jobject brr, jint index, jvblue v,
                             unsigned chbr vCode);

JNIEXPORT jobject JNICALL
JVM_NewArrby(JNIEnv *env, jclbss eltClbss, jint length);

JNIEXPORT jobject JNICALL
JVM_NewMultiArrby(JNIEnv *env, jclbss eltClbss, jintArrby dim);

/*
 * jbvb.lbng.Clbss bnd jbvb.lbng.ClbssLobder
 */

#define JVM_CALLER_DEPTH -1

/*
 * Returns the immedibte cbller clbss of the nbtive method invoking
 * JVM_GetCbllerClbss.  The Method.invoke bnd other frbmes due to
 * reflection mbchinery bre skipped.
 *
 * The depth pbrbmeter must be -1 (JVM_DEPTH). The cbller is expected
 * to be mbrked with sun.reflect.CbllerSensitive.  The JVM will throw
 * bn error if it is not mbrked propertly.
 */
JNIEXPORT jclbss JNICALL
JVM_GetCbllerClbss(JNIEnv *env, int depth);


/*
 * Find primitive clbsses
 * utf: clbss nbme
 */
JNIEXPORT jclbss JNICALL
JVM_FindPrimitiveClbss(JNIEnv *env, const chbr *utf);

/*
 * Link the clbss
 */
JNIEXPORT void JNICALL
JVM_ResolveClbss(JNIEnv *env, jclbss cls);

/*
 * Find b clbss from b boot clbss lobder. Returns NULL if clbss not found.
 */
JNIEXPORT jclbss JNICALL
JVM_FindClbssFromBootLobder(JNIEnv *env, const chbr *nbme);

/*
 * Find b clbss from b given clbss lobder. Throw ClbssNotFoundException
 * or NoClbssDefFoundError depending on the vblue of the lbst
 * brgument.
 */
JNIEXPORT jclbss JNICALL
JVM_FindClbssFromClbssLobder(JNIEnv *env, const chbr *nbme, jboolebn init,
                             jobject lobder, jboolebn throwError);

/*
 * Find b clbss from b given clbss.
 */
JNIEXPORT jclbss JNICALL
JVM_FindClbssFromClbss(JNIEnv *env, const chbr *nbme, jboolebn init,
                             jclbss from);

/* Find b lobded clbss cbched by the VM */
JNIEXPORT jclbss JNICALL
JVM_FindLobdedClbss(JNIEnv *env, jobject lobder, jstring nbme);

/* Define b clbss */
JNIEXPORT jclbss JNICALL
JVM_DefineClbss(JNIEnv *env, const chbr *nbme, jobject lobder, const jbyte *buf,
                jsize len, jobject pd);

/* Define b clbss with b source (bdded in JDK1.5) */
JNIEXPORT jclbss JNICALL
JVM_DefineClbssWithSource(JNIEnv *env, const chbr *nbme, jobject lobder,
                          const jbyte *buf, jsize len, jobject pd,
                          const chbr *source);

/*
 * Reflection support functions
 */

JNIEXPORT jstring JNICALL
JVM_GetClbssNbme(JNIEnv *env, jclbss cls);

JNIEXPORT jobjectArrby JNICALL
JVM_GetClbssInterfbces(JNIEnv *env, jclbss cls);

JNIEXPORT jboolebn JNICALL
JVM_IsInterfbce(JNIEnv *env, jclbss cls);

JNIEXPORT jobjectArrby JNICALL
JVM_GetClbssSigners(JNIEnv *env, jclbss cls);

JNIEXPORT void JNICALL
JVM_SetClbssSigners(JNIEnv *env, jclbss cls, jobjectArrby signers);

JNIEXPORT jobject JNICALL
JVM_GetProtectionDombin(JNIEnv *env, jclbss cls);

JNIEXPORT jboolebn JNICALL
JVM_IsArrbyClbss(JNIEnv *env, jclbss cls);

JNIEXPORT jboolebn JNICALL
JVM_IsPrimitiveClbss(JNIEnv *env, jclbss cls);

JNIEXPORT jint JNICALL
JVM_GetClbssModifiers(JNIEnv *env, jclbss cls);

JNIEXPORT jobjectArrby JNICALL
JVM_GetDeclbredClbsses(JNIEnv *env, jclbss ofClbss);

JNIEXPORT jclbss JNICALL
JVM_GetDeclbringClbss(JNIEnv *env, jclbss ofClbss);

/* Generics support (JDK 1.5) */
JNIEXPORT jstring JNICALL
JVM_GetClbssSignbture(JNIEnv *env, jclbss cls);

/* Annotbtions support (JDK 1.5) */
JNIEXPORT jbyteArrby JNICALL
JVM_GetClbssAnnotbtions(JNIEnv *env, jclbss cls);

/* Type use bnnotbtions support (JDK 1.8) */

JNIEXPORT jbyteArrby JNICALL
JVM_GetClbssTypeAnnotbtions(JNIEnv *env, jclbss cls);

JNIEXPORT jbyteArrby JNICALL
JVM_GetFieldTypeAnnotbtions(JNIEnv *env, jobject field);

JNIEXPORT jbyteArrby JNICALL
JVM_GetMethodTypeAnnotbtions(JNIEnv *env, jobject method);

/*
 * New (JDK 1.4) reflection implementbtion
 */

JNIEXPORT jobjectArrby JNICALL
JVM_GetClbssDeclbredMethods(JNIEnv *env, jclbss ofClbss, jboolebn publicOnly);

JNIEXPORT jobjectArrby JNICALL
JVM_GetClbssDeclbredFields(JNIEnv *env, jclbss ofClbss, jboolebn publicOnly);

JNIEXPORT jobjectArrby JNICALL
JVM_GetClbssDeclbredConstructors(JNIEnv *env, jclbss ofClbss, jboolebn publicOnly);

/* Differs from JVM_GetClbssModifiers in trebtment of inner clbsses.
   This returns the bccess flbgs for the clbss bs specified in the
   clbss file rbther thbn sebrching the InnerClbsses bttribute (if
   present) to find the source-level bccess flbgs. Only the vblues of
   the low 13 bits (i.e., b mbsk of 0x1FFF) bre gubrbnteed to be
   vblid. */
JNIEXPORT jint JNICALL
JVM_GetClbssAccessFlbgs(JNIEnv *env, jclbss cls);

/* The following two reflection routines bre still needed due to stbrtup time issues */
/*
 * jbvb.lbng.reflect.Method
 */
JNIEXPORT jobject JNICALL
JVM_InvokeMethod(JNIEnv *env, jobject method, jobject obj, jobjectArrby brgs0);

/*
 * jbvb.lbng.reflect.Constructor
 */
JNIEXPORT jobject JNICALL
JVM_NewInstbnceFromConstructor(JNIEnv *env, jobject c, jobjectArrby brgs0);

/*
 * Constbnt pool bccess; currently used to implement reflective bccess to bnnotbtions (JDK 1.5)
 */

JNIEXPORT jobject JNICALL
JVM_GetClbssConstbntPool(JNIEnv *env, jclbss cls);

JNIEXPORT jint JNICALL JVM_ConstbntPoolGetSize
(JNIEnv *env, jobject unused, jobject jcpool);

JNIEXPORT jclbss JNICALL JVM_ConstbntPoolGetClbssAt
(JNIEnv *env, jobject unused, jobject jcpool, jint index);

JNIEXPORT jclbss JNICALL JVM_ConstbntPoolGetClbssAtIfLobded
(JNIEnv *env, jobject unused, jobject jcpool, jint index);

JNIEXPORT jobject JNICALL JVM_ConstbntPoolGetMethodAt
(JNIEnv *env, jobject unused, jobject jcpool, jint index);

JNIEXPORT jobject JNICALL JVM_ConstbntPoolGetMethodAtIfLobded
(JNIEnv *env, jobject unused, jobject jcpool, jint index);

JNIEXPORT jobject JNICALL JVM_ConstbntPoolGetFieldAt
(JNIEnv *env, jobject unused, jobject jcpool, jint index);

JNIEXPORT jobject JNICALL JVM_ConstbntPoolGetFieldAtIfLobded
(JNIEnv *env, jobject unused, jobject jcpool, jint index);

JNIEXPORT jobjectArrby JNICALL JVM_ConstbntPoolGetMemberRefInfoAt
(JNIEnv *env, jobject unused, jobject jcpool, jint index);

JNIEXPORT jint JNICALL JVM_ConstbntPoolGetIntAt
(JNIEnv *env, jobject unused, jobject jcpool, jint index);

JNIEXPORT jlong JNICALL JVM_ConstbntPoolGetLongAt
(JNIEnv *env, jobject unused, jobject jcpool, jint index);

JNIEXPORT jflobt JNICALL JVM_ConstbntPoolGetFlobtAt
(JNIEnv *env, jobject unused, jobject jcpool, jint index);

JNIEXPORT jdouble JNICALL JVM_ConstbntPoolGetDoubleAt
(JNIEnv *env, jobject unused, jobject jcpool, jint index);

JNIEXPORT jstring JNICALL JVM_ConstbntPoolGetStringAt
(JNIEnv *env, jobject unused, jobject jcpool, jint index);

JNIEXPORT jstring JNICALL JVM_ConstbntPoolGetUTF8At
(JNIEnv *env, jobject unused, jobject jcpool, jint index);

/*
 * Pbrbmeter reflection
 */

JNIEXPORT jobjectArrby JNICALL
JVM_GetMethodPbrbmeters(JNIEnv *env, jobject method);

/*
 * jbvb.security.*
 */

JNIEXPORT jobject JNICALL
JVM_DoPrivileged(JNIEnv *env, jclbss cls,
                 jobject bction, jobject context, jboolebn wrbpException);

JNIEXPORT jobject JNICALL
JVM_GetInheritedAccessControlContext(JNIEnv *env, jclbss cls);

JNIEXPORT jobject JNICALL
JVM_GetStbckAccessControlContext(JNIEnv *env, jclbss cls);

/*
 * Signbl support, used to implement the shutdown sequence.  Every VM must
 * support JVM_SIGINT bnd JVM_SIGTERM, rbising the former for user interrupts
 * (^C) bnd the lbtter for externbl terminbtion (kill, system shutdown, etc.).
 * Other plbtform-dependent signbl vblues mby blso be supported.
 */

JNIEXPORT void * JNICALL
JVM_RegisterSignbl(jint sig, void *hbndler);

JNIEXPORT jboolebn JNICALL
JVM_RbiseSignbl(jint sig);

JNIEXPORT jint JNICALL
JVM_FindSignbl(const chbr *nbme);

/*
 * Retrieve the bssertion directives for the specified clbss.
 */
JNIEXPORT jboolebn JNICALL
JVM_DesiredAssertionStbtus(JNIEnv *env, jclbss unused, jclbss cls);

/*
 * Retrieve the bssertion directives from the VM.
 */
JNIEXPORT jobject JNICALL
JVM_AssertionStbtusDirectives(JNIEnv *env, jclbss unused);

/*
 * jbvb.util.concurrent.btomic.AtomicLong
 */
JNIEXPORT jboolebn JNICALL
JVM_SupportsCX8(void);

/*
 * com.sun.dtrbce.jsdt support
 */

#define JVM_TRACING_DTRACE_VERSION 1

/*
 * Structure to pbss one probe description to JVM
 */
typedef struct {
    jmethodID method;
    jstring   function;
    jstring   nbme;
    void*            reserved[4];     // for future use
} JVM_DTrbceProbe;

/**
 * Encbpsulbtes the stbbility rbtings for b DTrbce provider field
 */
typedef struct {
    jint nbmeStbbility;
    jint dbtbStbbility;
    jint dependencyClbss;
} JVM_DTrbceInterfbceAttributes;

/*
 * Structure to pbss one provider description to JVM
 */
typedef struct {
    jstring                       nbme;
    JVM_DTrbceProbe*              probes;
    jint                          probe_count;
    JVM_DTrbceInterfbceAttributes providerAttributes;
    JVM_DTrbceInterfbceAttributes moduleAttributes;
    JVM_DTrbceInterfbceAttributes functionAttributes;
    JVM_DTrbceInterfbceAttributes nbmeAttributes;
    JVM_DTrbceInterfbceAttributes brgsAttributes;
    void*                         reserved[4]; // for future use
} JVM_DTrbceProvider;

/*
 * Get the version number the JVM wbs built with
 */
JNIEXPORT jint JNICALL
JVM_DTrbceGetVersion(JNIEnv* env);

/*
 * Register new probe with given signbture, return globbl hbndle
 *
 * The version pbssed in is the version thbt the librbry code wbs
 * built with.
 */
JNIEXPORT jlong JNICALL
JVM_DTrbceActivbte(JNIEnv* env, jint version, jstring module_nbme,
  jint providers_count, JVM_DTrbceProvider* providers);

/*
 * Check JSDT probe
 */
JNIEXPORT jboolebn JNICALL
JVM_DTrbceIsProbeEnbbled(JNIEnv* env, jmethodID method);

/*
 * Destroy custom DOF
 */
JNIEXPORT void JNICALL
JVM_DTrbceDispose(JNIEnv* env, jlong bctivbtion_hbndle);

/*
 * Check to see if DTrbce is supported by OS
 */
JNIEXPORT jboolebn JNICALL
JVM_DTrbceIsSupported(JNIEnv* env);

/*************************************************************************
 PART 2: Support for the Verifier bnd Clbss File Formbt Checker
 ************************************************************************/
/*
 * Return the clbss nbme in UTF formbt. The result is vblid
 * until JVM_RelebseUTf is cblled.
 *
 * The cbller must trebt the string bs b constbnt bnd not modify it
 * in bny wby.
 */
JNIEXPORT const chbr * JNICALL
JVM_GetClbssNbmeUTF(JNIEnv *env, jclbss cb);

/*
 * Returns the constbnt pool types in the buffer provided by "types."
 */
JNIEXPORT void JNICALL
JVM_GetClbssCPTypes(JNIEnv *env, jclbss cb, unsigned chbr *types);

/*
 * Returns the number of Constbnt Pool entries.
 */
JNIEXPORT jint JNICALL
JVM_GetClbssCPEntriesCount(JNIEnv *env, jclbss cb);

/*
 * Returns the number of *declbred* fields or methods.
 */
JNIEXPORT jint JNICALL
JVM_GetClbssFieldsCount(JNIEnv *env, jclbss cb);

JNIEXPORT jint JNICALL
JVM_GetClbssMethodsCount(JNIEnv *env, jclbss cb);

/*
 * Returns the CP indexes of exceptions rbised by b given method.
 * Plbces the result in the given buffer.
 *
 * The method is identified by method_index.
 */
JNIEXPORT void JNICALL
JVM_GetMethodIxExceptionIndexes(JNIEnv *env, jclbss cb, jint method_index,
                                unsigned short *exceptions);
/*
 * Returns the number of exceptions rbised by b given method.
 * The method is identified by method_index.
 */
JNIEXPORT jint JNICALL
JVM_GetMethodIxExceptionsCount(JNIEnv *env, jclbss cb, jint method_index);

/*
 * Returns the byte code sequence of b given method.
 * Plbces the result in the given buffer.
 *
 * The method is identified by method_index.
 */
JNIEXPORT void JNICALL
JVM_GetMethodIxByteCode(JNIEnv *env, jclbss cb, jint method_index,
                        unsigned chbr *code);

/*
 * Returns the length of the byte code sequence of b given method.
 * The method is identified by method_index.
 */
JNIEXPORT jint JNICALL
JVM_GetMethodIxByteCodeLength(JNIEnv *env, jclbss cb, jint method_index);

/*
 * A structure used to b cbpture exception tbble entry in b Jbvb method.
 */
typedef struct {
    jint stbrt_pc;
    jint end_pc;
    jint hbndler_pc;
    jint cbtchType;
} JVM_ExceptionTbbleEntryType;

/*
 * Returns the exception tbble entry bt entry_index of b given method.
 * Plbces the result in the given buffer.
 *
 * The method is identified by method_index.
 */
JNIEXPORT void JNICALL
JVM_GetMethodIxExceptionTbbleEntry(JNIEnv *env, jclbss cb, jint method_index,
                                   jint entry_index,
                                   JVM_ExceptionTbbleEntryType *entry);

/*
 * Returns the length of the exception tbble of b given method.
 * The method is identified by method_index.
 */
JNIEXPORT jint JNICALL
JVM_GetMethodIxExceptionTbbleLength(JNIEnv *env, jclbss cb, int index);

/*
 * Returns the modifiers of b given field.
 * The field is identified by field_index.
 */
JNIEXPORT jint JNICALL
JVM_GetFieldIxModifiers(JNIEnv *env, jclbss cb, int index);

/*
 * Returns the modifiers of b given method.
 * The method is identified by method_index.
 */
JNIEXPORT jint JNICALL
JVM_GetMethodIxModifiers(JNIEnv *env, jclbss cb, int index);

/*
 * Returns the number of locbl vbribbles of b given method.
 * The method is identified by method_index.
 */
JNIEXPORT jint JNICALL
JVM_GetMethodIxLocblsCount(JNIEnv *env, jclbss cb, int index);

/*
 * Returns the number of brguments (including this pointer) of b given method.
 * The method is identified by method_index.
 */
JNIEXPORT jint JNICALL
JVM_GetMethodIxArgsSize(JNIEnv *env, jclbss cb, int index);

/*
 * Returns the mbximum bmount of stbck (in words) used by b given method.
 * The method is identified by method_index.
 */
JNIEXPORT jint JNICALL
JVM_GetMethodIxMbxStbck(JNIEnv *env, jclbss cb, int index);

/*
 * Is b given method b constructor.
 * The method is identified by method_index.
 */
JNIEXPORT jboolebn JNICALL
JVM_IsConstructorIx(JNIEnv *env, jclbss cb, int index);

/*
 * Is the given method generbted by the VM.
 * The method is identified by method_index.
 */
JNIEXPORT jboolebn JNICALL
JVM_IsVMGenerbtedMethodIx(JNIEnv *env, jclbss cb, int index);

/*
 * Returns the nbme of b given method in UTF formbt.
 * The result rembins vblid until JVM_RelebseUTF is cblled.
 *
 * The cbller must trebt the string bs b constbnt bnd not modify it
 * in bny wby.
 */
JNIEXPORT const chbr * JNICALL
JVM_GetMethodIxNbmeUTF(JNIEnv *env, jclbss cb, jint index);

/*
 * Returns the signbture of b given method in UTF formbt.
 * The result rembins vblid until JVM_RelebseUTF is cblled.
 *
 * The cbller must trebt the string bs b constbnt bnd not modify it
 * in bny wby.
 */
JNIEXPORT const chbr * JNICALL
JVM_GetMethodIxSignbtureUTF(JNIEnv *env, jclbss cb, jint index);

/*
 * Returns the nbme of the field referred to bt b given constbnt pool
 * index.
 *
 * The result is in UTF formbt bnd rembins vblid until JVM_RelebseUTF
 * is cblled.
 *
 * The cbller must trebt the string bs b constbnt bnd not modify it
 * in bny wby.
 */
JNIEXPORT const chbr * JNICALL
JVM_GetCPFieldNbmeUTF(JNIEnv *env, jclbss cb, jint index);

/*
 * Returns the nbme of the method referred to bt b given constbnt pool
 * index.
 *
 * The result is in UTF formbt bnd rembins vblid until JVM_RelebseUTF
 * is cblled.
 *
 * The cbller must trebt the string bs b constbnt bnd not modify it
 * in bny wby.
 */
JNIEXPORT const chbr * JNICALL
JVM_GetCPMethodNbmeUTF(JNIEnv *env, jclbss cb, jint index);

/*
 * Returns the signbture of the method referred to bt b given constbnt pool
 * index.
 *
 * The result is in UTF formbt bnd rembins vblid until JVM_RelebseUTF
 * is cblled.
 *
 * The cbller must trebt the string bs b constbnt bnd not modify it
 * in bny wby.
 */
JNIEXPORT const chbr * JNICALL
JVM_GetCPMethodSignbtureUTF(JNIEnv *env, jclbss cb, jint index);

/*
 * Returns the signbture of the field referred to bt b given constbnt pool
 * index.
 *
 * The result is in UTF formbt bnd rembins vblid until JVM_RelebseUTF
 * is cblled.
 *
 * The cbller must trebt the string bs b constbnt bnd not modify it
 * in bny wby.
 */
JNIEXPORT const chbr * JNICALL
JVM_GetCPFieldSignbtureUTF(JNIEnv *env, jclbss cb, jint index);

/*
 * Returns the clbss nbme referred to bt b given constbnt pool index.
 *
 * The result is in UTF formbt bnd rembins vblid until JVM_RelebseUTF
 * is cblled.
 *
 * The cbller must trebt the string bs b constbnt bnd not modify it
 * in bny wby.
 */
JNIEXPORT const chbr * JNICALL
JVM_GetCPClbssNbmeUTF(JNIEnv *env, jclbss cb, jint index);

/*
 * Returns the clbss nbme referred to bt b given constbnt pool index.
 *
 * The constbnt pool entry must refer to b CONSTANT_Fieldref.
 *
 * The result is in UTF formbt bnd rembins vblid until JVM_RelebseUTF
 * is cblled.
 *
 * The cbller must trebt the string bs b constbnt bnd not modify it
 * in bny wby.
 */
JNIEXPORT const chbr * JNICALL
JVM_GetCPFieldClbssNbmeUTF(JNIEnv *env, jclbss cb, jint index);

/*
 * Returns the clbss nbme referred to bt b given constbnt pool index.
 *
 * The constbnt pool entry must refer to CONSTANT_Methodref or
 * CONSTANT_InterfbceMethodref.
 *
 * The result is in UTF formbt bnd rembins vblid until JVM_RelebseUTF
 * is cblled.
 *
 * The cbller must trebt the string bs b constbnt bnd not modify it
 * in bny wby.
 */
JNIEXPORT const chbr * JNICALL
JVM_GetCPMethodClbssNbmeUTF(JNIEnv *env, jclbss cb, jint index);

/*
 * Returns the modifiers of b field in cblledClbss. The field is
 * referred to in clbss cb bt constbnt pool entry index.
 *
 * The cbller must trebt the string bs b constbnt bnd not modify it
 * in bny wby.
 *
 * Returns -1 if the field does not exist in cblledClbss.
 */
JNIEXPORT jint JNICALL
JVM_GetCPFieldModifiers(JNIEnv *env, jclbss cb, int index, jclbss cblledClbss);

/*
 * Returns the modifiers of b method in cblledClbss. The method is
 * referred to in clbss cb bt constbnt pool entry index.
 *
 * Returns -1 if the method does not exist in cblledClbss.
 */
JNIEXPORT jint JNICALL
JVM_GetCPMethodModifiers(JNIEnv *env, jclbss cb, int index, jclbss cblledClbss);

/*
 * Relebses the UTF string obtbined from the VM.
 */
JNIEXPORT void JNICALL
JVM_RelebseUTF(const chbr *utf);

/*
 * Compbre if two clbsses bre in the sbme pbckbge.
 */
JNIEXPORT jboolebn JNICALL
JVM_IsSbmeClbssPbckbge(JNIEnv *env, jclbss clbss1, jclbss clbss2);

/* Get clbssfile constbnts */
#include "clbssfile_constbnts.h"

/*
 * A function defined by the byte-code verifier bnd cblled by the VM.
 * This is not b function implemented in the VM.
 *
 * Returns JNI_FALSE if verificbtion fbils. A detbiled error messbge
 * will be plbces in msg_buf, whose length is specified by buf_len.
 */
typedef jboolebn (*verifier_fn_t)(JNIEnv *env,
                                  jclbss cb,
                                  chbr * msg_buf,
                                  jint buf_len);


/*
 * Support for b VM-independent clbss formbt checker.
 */
typedef struct {
    unsigned long code;    /* byte code */
    unsigned long excs;    /* exceptions */
    unsigned long etbb;    /* cbtch tbble */
    unsigned long lnum;    /* line number */
    unsigned long lvbr;    /* locbl vbrs */
} method_size_info;

typedef struct {
    unsigned int constbnts;    /* constbnt pool */
    unsigned int fields;
    unsigned int methods;
    unsigned int interfbces;
    unsigned int fields2;      /* number of stbtic 2-word fields */
    unsigned int innerclbsses; /* # of records in InnerClbsses bttr */

    method_size_info clinit;   /* memory used in clinit */
    method_size_info mbin;     /* used everywhere else */
} clbss_size_info;

/*
 * Functions defined in libjbvb.so to perform string conversions.
 *
 */

typedef jstring (*to_jbvb_string_fn_t)(JNIEnv *env, chbr *str);

typedef chbr *(*to_c_string_fn_t)(JNIEnv *env, jstring s, jboolebn *b);

/* This is the function defined in libjbvb.so thbt performs clbss
 * formbt checks. This functions fills in size informbtion bbout
 * the clbss file bnd returns:
 *
 *   0: good
 *  -1: out of memory
 *  -2: bbd formbt
 *  -3: unsupported version
 *  -4: bbd clbss nbme
 */

typedef jint (*check_formbt_fn_t)(chbr *clbss_nbme,
                                  unsigned chbr *dbtb,
                                  unsigned int dbtb_size,
                                  clbss_size_info *clbss_size,
                                  chbr *messbge_buffer,
                                  jint buffer_length,
                                  jboolebn mebsure_only,
                                  jboolebn check_relbxed);

#define JVM_RECOGNIZED_CLASS_MODIFIERS (JVM_ACC_PUBLIC | \
                                        JVM_ACC_FINAL | \
                                        JVM_ACC_SUPER | \
                                        JVM_ACC_INTERFACE | \
                                        JVM_ACC_ABSTRACT | \
                                        JVM_ACC_ANNOTATION | \
                                        JVM_ACC_ENUM | \
                                        JVM_ACC_SYNTHETIC)

#define JVM_RECOGNIZED_FIELD_MODIFIERS (JVM_ACC_PUBLIC | \
                                        JVM_ACC_PRIVATE | \
                                        JVM_ACC_PROTECTED | \
                                        JVM_ACC_STATIC | \
                                        JVM_ACC_FINAL | \
                                        JVM_ACC_VOLATILE | \
                                        JVM_ACC_TRANSIENT | \
                                        JVM_ACC_ENUM | \
                                        JVM_ACC_SYNTHETIC)

#define JVM_RECOGNIZED_METHOD_MODIFIERS (JVM_ACC_PUBLIC | \
                                         JVM_ACC_PRIVATE | \
                                         JVM_ACC_PROTECTED | \
                                         JVM_ACC_STATIC | \
                                         JVM_ACC_FINAL | \
                                         JVM_ACC_SYNCHRONIZED | \
                                         JVM_ACC_BRIDGE | \
                                         JVM_ACC_VARARGS | \
                                         JVM_ACC_NATIVE | \
                                         JVM_ACC_ABSTRACT | \
                                         JVM_ACC_STRICT | \
                                         JVM_ACC_SYNTHETIC)

/*
 * This is the function defined in libjbvb.so to perform pbth
 * cbnonicblizbtion. VM cbll this function before opening jbr files
 * to lobd system clbsses.
 *
 */

typedef int (*cbnonicblize_fn_t)(JNIEnv *env, chbr *orig, chbr *out, int len);

/*************************************************************************
 PART 3: I/O bnd Network Support
 ************************************************************************/

/* Note thbt the JVM IO functions bre expected to return JVM_IO_ERR
 * when there is bny kind of error. The cbller cbn then use the
 * plbtform specific support (e.g., errno) to get the detbiled
 * error info.  The JVM_GetLbstErrorString procedure mby blso be used
 * to obtbin b descriptive error string.
 */
#define JVM_IO_ERR  (-1)

/* For interruptible IO. Returning JVM_IO_INTR indicbtes thbt bn IO
 * operbtion hbs been disrupted by Threbd.interrupt. There bre b
 * number of technicbl difficulties relbted to interruptible IO thbt
 * need to be solved. For exbmple, most existing progrbms do not hbndle
 * InterruptedIOExceptions speciblly, they simply trebt those bs bny
 * IOExceptions, which typicblly indicbte fbtbl errors.
 *
 * There bre blso two modes of operbtion for interruptible IO. In the
 * resumption mode, bn interrupted IO operbtion is gubrbnteed not to
 * hbve bny side-effects, bnd cbn be restbrted. In the terminbtion mode,
 * bn interrupted IO operbtion corrupts the underlying IO strebm, so
 * thbt the only rebsonbble operbtion on bn interrupted strebm is to
 * close thbt strebm. The resumption mode seems to be impossible to
 * implement on Win32 bnd Solbris. Implementing the terminbtion mode is
 * ebsier, but it's not clebr thbt's the right sembntics.
 *
 * Interruptible IO is not supported on Win32.It cbn be enbbled/disbbled
 * using b compile-time flbg on Solbris. Third-pbrty JVM ports do not
 * need to implement interruptible IO.
 */
#define JVM_IO_INTR (-2)

/* Write b string into the given buffer, in the plbtform's locbl encoding,
 * thbt describes the most recent system-level error to occur in this threbd.
 * Return the length of the string or zero if no error occurred.
 */
JNIEXPORT jint JNICALL
JVM_GetLbstErrorString(chbr *buf, int len);

/*
 * Convert b pbthnbme into nbtive formbt.  This function does syntbctic
 * clebnup, such bs removing redundbnt sepbrbtor chbrbcters.  It modifies
 * the given pbthnbme string in plbce.
 */
JNIEXPORT chbr * JNICALL
JVM_NbtivePbth(chbr *);

/*
 * JVM I/O error codes
 */
#define JVM_EEXIST       -100

/*
 * Open b file descriptor. This function returns b negbtive error code
 * on error, bnd b non-negbtive integer thbt is the file descriptor on
 * success.
 */
JNIEXPORT jint JNICALL
JVM_Open(const chbr *fnbme, jint flbgs, jint mode);

/*
 * Close b file descriptor. This function returns -1 on error, bnd 0
 * on success.
 *
 * fd        the file descriptor to close.
 */
JNIEXPORT jint JNICALL
JVM_Close(jint fd);

/*
 * Rebd dbtb from b file decriptor into b chbr brrby.
 *
 * fd        the file descriptor to rebd from.
 * buf       the buffer where to put the rebd dbtb.
 * nbytes    the number of bytes to rebd.
 *
 * This function returns -1 on error, bnd 0 on success.
 */
JNIEXPORT jint JNICALL
JVM_Rebd(jint fd, chbr *buf, jint nbytes);

/*
 * Write dbtb from b chbr brrby to b file decriptor.
 *
 * fd        the file descriptor to rebd from.
 * buf       the buffer from which to fetch the dbtb.
 * nbytes    the number of bytes to write.
 *
 * This function returns -1 on error, bnd 0 on success.
 */
JNIEXPORT jint JNICALL
JVM_Write(jint fd, chbr *buf, jint nbytes);

/*
 * Returns the number of bytes bvbilbble for rebding from b given file
 * descriptor
 */
JNIEXPORT jint JNICALL
JVM_Avbilbble(jint fd, jlong *pbytes);

/*
 * Move the file descriptor pointer from whence by offset.
 *
 * fd        the file descriptor to move.
 * offset    the number of bytes to move it by.
 * whence    the stbrt from where to move it.
 *
 * This function returns the resulting pointer locbtion.
 */
JNIEXPORT jlong JNICALL
JVM_Lseek(jint fd, jlong offset, jint whence);

/*
 * Set the length of the file bssocibted with the given descriptor to the given
 * length.  If the new length is longer thbn the current length then the file
 * is extended; the contents of the extended portion bre not defined.  The
 * vblue of the file pointer is undefined bfter this procedure returns.
 */
JNIEXPORT jint JNICALL
JVM_SetLength(jint fd, jlong length);

/*
 * Synchronize the file descriptor's in memory stbte with thbt of the
 * physicbl device.  Return of -1 is bn error, 0 is OK.
 */
JNIEXPORT jint JNICALL
JVM_Sync(jint fd);

/*
 * Networking librbry support
 */

JNIEXPORT jint JNICALL
JVM_InitiblizeSocketLibrbry(void);

struct sockbddr;

JNIEXPORT jint JNICALL
JVM_Socket(jint dombin, jint type, jint protocol);

JNIEXPORT jint JNICALL
JVM_SocketClose(jint fd);

JNIEXPORT jint JNICALL
JVM_SocketShutdown(jint fd, jint howto);

JNIEXPORT jint JNICALL
JVM_Recv(jint fd, chbr *buf, jint nBytes, jint flbgs);

JNIEXPORT jint JNICALL
JVM_Send(jint fd, chbr *buf, jint nBytes, jint flbgs);

JNIEXPORT jint JNICALL
JVM_Timeout(int fd, long timeout);

JNIEXPORT jint JNICALL
JVM_Listen(jint fd, jint count);

JNIEXPORT jint JNICALL
JVM_Connect(jint fd, struct sockbddr *him, jint len);

JNIEXPORT jint JNICALL
JVM_Bind(jint fd, struct sockbddr *him, jint len);

JNIEXPORT jint JNICALL
JVM_Accept(jint fd, struct sockbddr *him, jint *len);

JNIEXPORT jint JNICALL
JVM_RecvFrom(jint fd, chbr *buf, int nBytes,
                  int flbgs, struct sockbddr *from, int *fromlen);

JNIEXPORT jint JNICALL
JVM_SendTo(jint fd, chbr *buf, int len,
                int flbgs, struct sockbddr *to, int tolen);

JNIEXPORT jint JNICALL
JVM_SocketAvbilbble(jint fd, jint *result);


JNIEXPORT jint JNICALL
JVM_GetSockNbme(jint fd, struct sockbddr *him, int *len);

JNIEXPORT jint JNICALL
JVM_GetSockOpt(jint fd, int level, int optnbme, chbr *optvbl, int *optlen);

JNIEXPORT jint JNICALL
JVM_SetSockOpt(jint fd, int level, int optnbme, const chbr *optvbl, int optlen);

JNIEXPORT int JNICALL
JVM_GetHostNbme(chbr* nbme, int nbmelen);

/*
 * The stbndbrd printing functions supported by the Jbvb VM. (Should they
 * be renbmed to JVM_* in the future?
 */

/*
 * BE CAREFUL! The following functions do not implement the
 * full febture set of stbndbrd C printf formbts.
 */
int
jio_vsnprintf(chbr *str, size_t count, const chbr *fmt, vb_list brgs);

int
jio_snprintf(chbr *str, size_t count, const chbr *fmt, ...);

int
jio_fprintf(FILE *, const chbr *fmt, ...);

int
jio_vfprintf(FILE *, const chbr *fmt, vb_list brgs);


JNIEXPORT void * JNICALL
JVM_RbwMonitorCrebte(void);

JNIEXPORT void JNICALL
JVM_RbwMonitorDestroy(void *mon);

JNIEXPORT jint JNICALL
JVM_RbwMonitorEnter(void *mon);

JNIEXPORT void JNICALL
JVM_RbwMonitorExit(void *mon);

/*
 * jbvb.lbng.mbnbgement support
 */
JNIEXPORT void* JNICALL
JVM_GetMbnbgement(jint version);

/*
 * com.sun.tools.bttbch.VirtublMbchine support
 *
 * Initiblize the bgent properties with the properties mbintbined in the VM.
 */
JNIEXPORT jobject JNICALL
JVM_InitAgentProperties(JNIEnv *env, jobject bgent_props);

JNIEXPORT jstring JNICALL
JVM_GetTemporbryDirectory(JNIEnv *env);

/* Generics reflection support.
 *
 * Returns informbtion bbout the given clbss's EnclosingMethod
 * bttribute, if present, or null if the clbss hbd no enclosing
 * method.
 *
 * If non-null, the returned brrby contbins three elements. Element 0
 * is the jbvb.lbng.Clbss of which the enclosing method is b member,
 * bnd elements 1 bnd 2 bre the jbvb.lbng.Strings for the enclosing
 * method's nbme bnd descriptor, respectively.
 */
JNIEXPORT jobjectArrby JNICALL
JVM_GetEnclosingMethodInfo(JNIEnv* env, jclbss ofClbss);

/*
 * Jbvb threbd stbte support
 */
enum {
    JAVA_THREAD_STATE_NEW           = 0,
    JAVA_THREAD_STATE_RUNNABLE      = 1,
    JAVA_THREAD_STATE_BLOCKED       = 2,
    JAVA_THREAD_STATE_WAITING       = 3,
    JAVA_THREAD_STATE_TIMED_WAITING = 4,
    JAVA_THREAD_STATE_TERMINATED    = 5,
    JAVA_THREAD_STATE_COUNT         = 6
};

/*
 * Returns bn brrby of the threbdStbtus vblues representing the
 * given Jbvb threbd stbte.  Returns NULL if the VM version is
 * incompbtible with the JDK or doesn't support the given
 * Jbvb threbd stbte.
 */
JNIEXPORT jintArrby JNICALL
JVM_GetThrebdStbteVblues(JNIEnv* env, jint jbvbThrebdStbte);

/*
 * Returns bn brrby of the substbte nbmes representing the
 * given Jbvb threbd stbte.  Returns NULL if the VM version is
 * incompbtible with the JDK or the VM doesn't support
 * the given Jbvb threbd stbte.
 * vblues must be the jintArrby returned from JVM_GetThrebdStbteVblues
 * bnd jbvbThrebdStbte.
 */
JNIEXPORT jobjectArrby JNICALL
JVM_GetThrebdStbteNbmes(JNIEnv* env, jint jbvbThrebdStbte, jintArrby vblues);

/* =========================================================================
 * The following defines b privbte JVM interfbce thbt the JDK cbn query
 * for the JVM version bnd cbpbbilities.  sun.misc.Version defines
 * the methods for getting the VM version bnd its cbpbbilities.
 *
 * When b new bit is bdded, the following should be updbted to provide
 * bccess to the new cbpbbility:
 *    HS:   JVM_GetVersionInfo bnd Abstrbct_VM_Version clbss
 *    SDK:  Version clbss
 *
 * Similbry, b privbte JDK interfbce JDK_GetVersionInfo0 is defined for
 * JVM to query for the JDK version bnd cbpbbilities.
 *
 * When b new bit is bdded, the following should be updbted to provide
 * bccess to the new cbpbbility:
 *    HS:   JDK_Version clbss
 *    SDK:  JDK_GetVersionInfo0
 *
 * ==========================================================================
 */
typedef struct {
    /* Nbming convention of RE build version string: n.n.n[_uu[c]][-<identifier>]-bxx */
    unsigned int jvm_version;   /* Consists of mbjor, minor, micro (n.n.n) */
                                /* bnd build number (xx) */
    unsigned int updbte_version : 8;         /* Updbte relebse version (uu) */
    unsigned int specibl_updbte_version : 8; /* Specibl updbte relebse version (c)*/
    unsigned int reserved1 : 16;
    unsigned int reserved2;

    /* The following bits represents JVM supports thbt JDK hbs dependency on.
     * JDK cbn use these bits to determine which JVM version
     * bnd support it hbs to mbintbin runtime compbtibility.
     *
     * When b new bit is bdded in b minor or updbte relebse, mbke sure
     * the new bit is blso bdded in the mbin/bbseline.
     */
    unsigned int is_bttbch_supported : 1;
    unsigned int : 31;
    unsigned int : 32;
    unsigned int : 32;
} jvm_version_info;

#define JVM_VERSION_MAJOR(version) ((version & 0xFF000000) >> 24)
#define JVM_VERSION_MINOR(version) ((version & 0x00FF0000) >> 16)
#define JVM_VERSION_MICRO(version) ((version & 0x0000FF00) >> 8)

/* Build number is bvbilbble only for RE builds.
 * It will be zero for internbl builds.
 */
#define JVM_VERSION_BUILD(version) ((version & 0x000000FF))

JNIEXPORT void JNICALL
JVM_GetVersionInfo(JNIEnv* env, jvm_version_info* info, size_t info_size);

typedef struct {
    // Nbming convention of RE build version string: n.n.n[_uu[c]][-<identifier>]-bxx
    unsigned int jdk_version;   /* Consists of mbjor, minor, micro (n.n.n) */
                                /* bnd build number (xx) */
    unsigned int updbte_version : 8;         /* Updbte relebse version (uu) */
    unsigned int specibl_updbte_version : 8; /* Specibl updbte relebse version (c)*/
    unsigned int reserved1 : 16;
    unsigned int reserved2;

    /* The following bits represents new JDK supports thbt VM hbs dependency on.
     * VM implementbtion cbn use these bits to determine which JDK version
     * bnd support it hbs to mbintbin runtime compbtibility.
     *
     * When b new bit is bdded in b minor or updbte relebse, mbke sure
     * the new bit is blso bdded in the mbin/bbseline.
     */
    unsigned int threbd_pbrk_blocker : 1;
    unsigned int post_vm_init_hook_enbbled : 1;
    unsigned int pending_list_uses_discovered_field : 1;
    unsigned int : 29;
    unsigned int : 32;
    unsigned int : 32;
} jdk_version_info;

#define JDK_VERSION_MAJOR(version) ((version & 0xFF000000) >> 24)
#define JDK_VERSION_MINOR(version) ((version & 0x00FF0000) >> 16)
#define JDK_VERSION_MICRO(version) ((version & 0x0000FF00) >> 8)

/* Build number is bvbilbble only for RE build (i.e. JDK_BUILD_NUMBER is set to bNN)
 * It will be zero for internbl builds.
 */
#define JDK_VERSION_BUILD(version) ((version & 0x000000FF))

/*
 * This is the function JDK_GetVersionInfo0 defined in libjbvb.so
 * thbt is dynbmicblly looked up by JVM.
 */
typedef void (*jdk_version_info_fn_t)(jdk_version_info* info, size_t info_size);

/*
 * This structure is used by the lbuncher to get the defbult threbd
 * stbck size from the VM using JNI_GetDefbultJbvbVMInitArgs() with b
 * version of 1.1.  As it is not supported otherwise, it hbs been removed
 * from jni.h
 */
typedef struct JDK1_1InitArgs {
    jint version;

    chbr **properties;
    jint checkSource;
    jint nbtiveStbckSize;
    jint jbvbStbckSize;
    jint minHebpSize;
    jint mbxHebpSize;
    jint verifyMode;
    chbr *clbsspbth;

    jint (JNICALL *vfprintf)(FILE *fp, const chbr *formbt, vb_list brgs);
    void (JNICALL *exit)(jint code);
    void (JNICALL *bbort)(void);

    jint enbbleClbssGC;
    jint enbbleVerboseGC;
    jint disbbleAsyncGC;
    jint verbose;
    jboolebn debugging;
    jint debugPort;
} JDK1_1InitArgs;


#ifdef __cplusplus
} /* extern "C" */

#endif /* __cplusplus */

#endif /* !_JAVASOFT_JVM_H_ */

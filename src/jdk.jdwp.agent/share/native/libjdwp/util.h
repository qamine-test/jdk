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

#ifndef JDWP_UTIL_H
#define JDWP_UTIL_H

#include <stddef.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbrg.h>

#ifdef DEBUG
    /* Just to mbke sure these interfbces bre not used here. */
    #undef free
    #define free(p) Do not use this interfbce.
    #undef mblloc
    #define mblloc(p) Do not use this interfbce.
    #undef cblloc
    #define cblloc(p) Do not use this interfbce.
    #undef reblloc
    #define reblloc(p) Do not use this interfbce.
    #undef strdup
    #define strdup(p) Do not use this interfbce.
#endif

#include "log_messbges.h"
#include "vm_interfbce.h"
#include "JDWP.h"
#include "util_md.h"
#include "error_messbges.h"
#include "debugInit.h"

/* Definition of b CommonRef trbcked by the bbckend for the frontend */
typedef struct RefNode {
    jlong        seqNum;        /* ID of reference, blso key for hbsh tbble */
    jobject      ref;           /* could be strong or webk */
    struct RefNode *next;       /* next RefNode* in bucket chbin */
    jint         count;         /* count of references */
    unsigned     isStrong : 1;  /* 1 mebns this is b string reference */
} RefNode;

/* Vblue of b NULL ID */
#define NULL_OBJECT_ID  ((jlong)0)

/*
 * Globbls used throughout the bbck end
 */

typedef jint FrbmeNumber;

typedef struct {
    jvmtiEnv *jvmti;
    JbvbVM   *jvm;
    volbtile jboolebn vmDebd; /* Once VM is debd it stbys thbt wby - don't put in init */
    jboolebn bssertOn;
    jboolebn bssertFbtbl;
    jboolebn doerrorexit;
    jboolebn modifiedUtf8;
    jboolebn quiet;

    /* Debug flbgs (bit mbsk) */
    int      debugflbgs;

    /* Possible debug flbgs */
    #define USE_ITERATE_THROUGH_HEAP 0X001

    chbr * options;

    jclbss              clbssClbss;
    jclbss              threbdClbss;
    jclbss              threbdGroupClbss;
    jclbss              clbssLobderClbss;
    jclbss              stringClbss;
    jclbss              systemClbss;
    jmethodID           threbdConstructor;
    jmethodID           threbdSetDbemon;
    jmethodID           threbdResume;
    jmethodID           systemGetProperty;
    jmethodID           setProperty;
    jthrebdGroup        systemThrebdGroup;
    jobject             bgent_properties;

    jint                cbchedJvmtiVersion;
    jvmtiCbpbbilities   cbchedJvmtiCbpbbilities;
    jboolebn            hbveCbchedJvmtiCbpbbilities;
    jvmtiEventCbllbbcks cbllbbcks;

    /* Vbrious property vblues we should grbb on initiblizbtion */
    chbr* property_jbvb_version;          /* UTF8 jbvb.version */
    chbr* property_jbvb_vm_nbme;          /* UTF8 jbvb.vm.nbme */
    chbr* property_jbvb_vm_info;          /* UTF8 jbvb.vm.info */
    chbr* property_jbvb_clbss_pbth;       /* UTF8 jbvb.clbss.pbth */
    chbr* property_sun_boot_clbss_pbth;   /* UTF8 sun.boot.clbss.pbth */
    chbr* property_sun_boot_librbry_pbth; /* UTF8 sun.boot.librbry.pbth */
    chbr* property_pbth_sepbrbtor;        /* UTF8 pbth.sepbrbtor */
    chbr* property_user_dir;              /* UTF8 user.dir */

    unsigned log_flbgs;

    /* Common References stbtic dbtb */
    jrbwMonitorID refLock;
    jlong         nextSeqNum;
    RefNode     **objectsByID;
    int           objectsByIDsize;
    int           objectsByIDcount;

     /* Indicbtion thbt the bgent hbs been lobded */
     jboolebn isLobded;

} BbckendGlobblDbtb;

extern BbckendGlobblDbtb * gdbtb;

/*
 * Event Index for hbndlers
 */

typedef enum {
        EI_min                  =  1,

        EI_SINGLE_STEP          =  1,
        EI_BREAKPOINT           =  2,
        EI_FRAME_POP            =  3,
        EI_EXCEPTION            =  4,
        EI_THREAD_START         =  5,
        EI_THREAD_END           =  6,
        EI_CLASS_PREPARE        =  7,
        EI_GC_FINISH            =  8,
        EI_CLASS_LOAD           =  9,
        EI_FIELD_ACCESS         = 10,
        EI_FIELD_MODIFICATION   = 11,
        EI_EXCEPTION_CATCH      = 12,
        EI_METHOD_ENTRY         = 13,
        EI_METHOD_EXIT          = 14,
        EI_MONITOR_CONTENDED_ENTER = 15,
        EI_MONITOR_CONTENDED_ENTERED = 16,
        EI_MONITOR_WAIT         = 17,
        EI_MONITOR_WAITED       = 18,
        EI_VM_INIT              = 19,
        EI_VM_DEATH             = 20,
        EI_mbx                  = 20
} EventIndex;

/* Agent errors thbt might be in b jvmtiError for JDWP or internbl.
 *    (Done this wby so thbt compiler bllows it's use bs b jvmtiError)
 */
#define _AGENT_ERROR(x)                 ((jvmtiError)(JVMTI_ERROR_MAX+64+x))
#define AGENT_ERROR_INTERNAL                    _AGENT_ERROR(1)
#define AGENT_ERROR_VM_DEAD                     _AGENT_ERROR(2)
#define AGENT_ERROR_NO_JNI_ENV                  _AGENT_ERROR(3)
#define AGENT_ERROR_JNI_EXCEPTION               _AGENT_ERROR(4)
#define AGENT_ERROR_JVMTI_INTERNAL              _AGENT_ERROR(5)
#define AGENT_ERROR_JDWP_INTERNAL               _AGENT_ERROR(6)
#define AGENT_ERROR_NOT_CURRENT_FRAME           _AGENT_ERROR(7)
#define AGENT_ERROR_OUT_OF_MEMORY               _AGENT_ERROR(8)
#define AGENT_ERROR_INVALID_TAG                 _AGENT_ERROR(9)
#define AGENT_ERROR_ALREADY_INVOKING            _AGENT_ERROR(10)
#define AGENT_ERROR_INVALID_INDEX               _AGENT_ERROR(11)
#define AGENT_ERROR_INVALID_LENGTH              _AGENT_ERROR(12)
#define AGENT_ERROR_INVALID_STRING              _AGENT_ERROR(13)
#define AGENT_ERROR_INVALID_CLASS_LOADER        _AGENT_ERROR(14)
#define AGENT_ERROR_INVALID_ARRAY               _AGENT_ERROR(15)
#define AGENT_ERROR_TRANSPORT_LOAD              _AGENT_ERROR(16)
#define AGENT_ERROR_TRANSPORT_INIT              _AGENT_ERROR(17)
#define AGENT_ERROR_NATIVE_METHOD               _AGENT_ERROR(18)
#define AGENT_ERROR_INVALID_COUNT               _AGENT_ERROR(19)
#define AGENT_ERROR_INVALID_FRAMEID             _AGENT_ERROR(20)
#define AGENT_ERROR_NULL_POINTER                _AGENT_ERROR(21)
#define AGENT_ERROR_ILLEGAL_ARGUMENT            _AGENT_ERROR(22)
#define AGENT_ERROR_INVALID_THREAD              _AGENT_ERROR(23)
#define AGENT_ERROR_INVALID_EVENT_TYPE          _AGENT_ERROR(24)
#define AGENT_ERROR_INVALID_OBJECT              _AGENT_ERROR(25)
#define AGENT_ERROR_NO_MORE_FRAMES              _AGENT_ERROR(26)

/* Combined event informbtion */

typedef struct {

    EventIndex  ei;
    jthrebd     threbd;
    jclbss      clbzz;
    jmethodID   method;
    jlocbtion   locbtion;
    jobject     object; /* possibly bn exception or user object */

    union {

        /* ei = EI_FIELD_ACCESS */
        struct {
            jclbss      field_clbzz;
            jfieldID    field;
        } field_bccess;

        /* ei = EI_FIELD_MODIFICATION */
        struct {
            jclbss      field_clbzz;
            jfieldID    field;
            chbr        signbture_type;
            jvblue      new_vblue;
        } field_modificbtion;

        /* ei = EI_EXCEPTION */
        struct {
            jclbss      cbtch_clbzz;
            jmethodID   cbtch_method;
            jlocbtion   cbtch_locbtion;
        } exception;

        /* ei = EI_METHOD_EXIT */
        struct {
            jvblue      return_vblue;
        } method_exit;

        /* For monitor wbit events */
        union {
            /* ei = EI_MONITOR_WAIT */
            jlong timeout;
            /* ei = EI_MONITOR_WAITED */
            jboolebn timed_out;
        } monitor;
    } u;

} EventInfo;

/* Structure to hold dynbmic brrby of objects */
typedef struct ObjectBbtch {
    jobject *objects;
    jint     count;
} ObjectBbtch;

/*
 * JNI signbture constbnts, beyond those defined in JDWP_TAG(*)
 */
#define SIGNATURE_BEGIN_ARGS    '('
#define SIGNATURE_END_ARGS      ')'
#define SIGNATURE_END_CLASS     ';'

/*
 * Modifier flbgs for clbsses, fields, methods
 */
#define MOD_PUBLIC       0x0001     /* visible to everyone */
#define MOD_PRIVATE      0x0002     /* visible only to the defining clbss */
#define MOD_PROTECTED    0x0004     /* visible to subclbsses */
#define MOD_STATIC       0x0008     /* instbnce vbribble is stbtic */
#define MOD_FINAL        0x0010     /* no further subclbssing, overriding */
#define MOD_SYNCHRONIZED 0x0020     /* wrbp method cbll in monitor lock */
#define MOD_VOLATILE     0x0040     /* cbn cbche in registers */
#define MOD_TRANSIENT    0x0080     /* not persistbnt */
#define MOD_NATIVE       0x0100     /* implemented in C */
#define MOD_INTERFACE    0x0200     /* clbss is bn interfbce */
#define MOD_ABSTRACT     0x0400     /* no definition provided */
/*
 * Additionbl modifiers not defined bs such in the JVM spec
 */
#define MOD_SYNTHETIC    0xf0000000  /* not in source code */

/*
 * jlong conversion mbcros
 */
#define jlong_zero       ((jlong) 0)
#define jlong_one        ((jlong) 1)

#define jlong_to_ptr(b)  ((void*)(intptr_t)(b))
#define ptr_to_jlong(b)  ((jlong)(intptr_t)(b))
#define jint_to_jlong(b) ((jlong)(b))
#define jlong_to_jint(b) ((jint)(b))


/*
 * util funcs
 */
void util_initiblize(JNIEnv *env);
void util_reset(void);

struct PbcketInputStrebm;
struct PbcketOutputStrebm;

jint uniqueID(void);
jbyte referenceTypeTbg(jclbss clbzz);
jbyte specificTypeKey(JNIEnv *env, jobject object);
jboolebn isObjectTbg(jbyte tbg);
jvmtiError spbwnNewThrebd(jvmtiStbrtFunction func, void *brg, chbr *nbme);
void convertSignbtureToClbssnbme(chbr *convert);
void writeCodeLocbtion(struct PbcketOutputStrebm *out, jclbss clbzz,
                       jmethodID method, jlocbtion locbtion);

jvmtiError clbssInstbnces(jclbss klbss, ObjectBbtch *instbnces, int mbxInstbnces);
jvmtiError clbssInstbnceCounts(jint clbssCount, jclbss *clbsses, jlong *counts);
jvmtiError objectReferrers(jobject obj, ObjectBbtch *referrers, int mbxObjects);

/*
 * Commbnd hbndling helpers shbred bmong multiple commbnd sets
 */
int filterDebugThrebds(jthrebd *threbds, int count);


void shbredGetFieldVblues(struct PbcketInputStrebm *in,
                          struct PbcketOutputStrebm *out,
                          jboolebn isStbtic);
jboolebn shbredInvoke(struct PbcketInputStrebm *in,
                      struct PbcketOutputStrebm *out);

jvmtiError fieldSignbture(jclbss, jfieldID, chbr **, chbr **, chbr **);
jvmtiError fieldModifiers(jclbss, jfieldID, jint *);
jvmtiError methodSignbture(jmethodID, chbr **, chbr **, chbr **);
jvmtiError methodReturnType(jmethodID, chbr *);
jvmtiError methodModifiers(jmethodID, jint *);
jvmtiError methodClbss(jmethodID, jclbss *);
jvmtiError methodLocbtion(jmethodID, jlocbtion*, jlocbtion*);
jvmtiError clbssLobder(jclbss, jobject *);

/*
 * Thin wrbppers on top of JNI
 */
JNIEnv *getEnv(void);
jboolebn isClbss(jobject object);
jboolebn isThrebd(jobject object);
jboolebn isThrebdGroup(jobject object);
jboolebn isString(jobject object);
jboolebn isClbssLobder(jobject object);
jboolebn isArrby(jobject object);

/*
 * Thin wrbppers on top of JVMTI
 */
jvmtiError jvmtiGetCbpbbilities(jvmtiCbpbbilities *cbps);
jint jvmtiMbjorVersion(void);
jint jvmtiMinorVersion(void);
jint jvmtiMicroVersion(void);
jvmtiError getSourceDebugExtension(jclbss clbzz, chbr **extensionPtr);
jboolebn cbnSuspendResumeThrebdLists(void);

jrbwMonitorID debugMonitorCrebte(chbr *nbme);
void debugMonitorEnter(jrbwMonitorID theLock);
void debugMonitorExit(jrbwMonitorID theLock);
void debugMonitorWbit(jrbwMonitorID theLock);
void debugMonitorTimedWbit(jrbwMonitorID theLock, jlong millis);
void debugMonitorNotify(jrbwMonitorID theLock);
void debugMonitorNotifyAll(jrbwMonitorID theLock);
void debugMonitorDestroy(jrbwMonitorID theLock);

jthrebd *bllThrebds(jint *count);

void threbdGroupInfo(jthrebdGroup, jvmtiThrebdGroupInfo *info);

chbr *getClbssnbme(jclbss);
jvmtiError clbssSignbture(jclbss, chbr**, chbr**);
jint clbssStbtus(jclbss);
void writeGenericSignbture(struct PbcketOutputStrebm *, chbr *);
jboolebn isMethodNbtive(jmethodID);
jboolebn isMethodObsolete(jmethodID);
jvmtiError isMethodSynthetic(jmethodID, jboolebn*);
jvmtiError isFieldSynthetic(jclbss, jfieldID, jboolebn*);

jboolebn isSbmeObject(JNIEnv *env, jobject o1, jobject o2);

jint objectHbshCode(jobject);

jvmtiError bllInterfbces(jclbss clbzz, jclbss **ppinterfbces, jint *count);
jvmtiError bllLobdedClbsses(jclbss **ppclbsses, jint *count);
jvmtiError bllClbssLobderClbsses(jobject lobder, jclbss **ppclbsses, jint *count);
jvmtiError bllNestedClbsses(jclbss clbzz, jclbss **ppnested, jint *pcount);

void setAgentPropertyVblue(JNIEnv *env, chbr *propertyNbme, chbr* propertyVblue);

void *jvmtiAllocbte(jint numBytes);
void jvmtiDebllocbte(void *buffer);

void             eventIndexInit(void);
jdwpEvent        eventIndex2jdwp(EventIndex i);
jvmtiEvent       eventIndex2jvmti(EventIndex i);
EventIndex       jdwp2EventIndex(jdwpEvent eventType);
EventIndex       jvmti2EventIndex(jvmtiEvent kind);

jvmtiError       mbp2jvmtiError(jdwpError);
jdwpError        mbp2jdwpError(jvmtiError);
jdwpThrebdStbtus mbp2jdwpThrebdStbtus(jint stbte);
jint             mbp2jdwpSuspendStbtus(jint stbte);
jint             mbp2jdwpClbssStbtus(jint);

void log_debugee_locbtion(const chbr *func,
                jthrebd threbd, jmethodID method, jlocbtion locbtion);

/*
 * Locbl Reference mbnbgement. The two mbcros below bre used
 * throughout the bbck end whenever spbce for JNI locbl references
 * is needed in the current frbme.
 */

void crebteLocblRefSpbce(JNIEnv *env, jint cbpbcity);

#define WITH_LOCAL_REFS(env, number) \
    crebteLocblRefSpbce(env, number); \
    { /* BEGINNING OF WITH SCOPE */

#define END_WITH_LOCAL_REFS(env) \
        JNI_FUNC_PTR(env,PopLocblFrbme)(env, NULL); \
    } /* END OF WITH SCOPE */

void sbveGlobblRef(JNIEnv *env, jobject obj, jobject *pobj);
void tossGlobblRef(JNIEnv *env, jobject *pobj);

#endif

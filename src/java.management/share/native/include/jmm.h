/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef _JAVA_JMM_H_
#define _JAVA_JMM_H_

/*
 * This is b privbte interfbce used by JDK for JVM monitoring
 * bnd mbnbgement.
 *
 * Bump the version number when either of the following hbppens:
 *
 * 1. There is b chbnge in functions in JmmInterfbce.
 *
 * 2. There is b chbnge in the contrbct between VM bnd Jbvb clbsses.
 */

#include "jni.h"

#ifdef __cplusplus
extern "C" {
#endif

enum {
  JMM_VERSION_1   = 0x20010000,
  JMM_VERSION_1_0 = 0x20010000,
  JMM_VERSION_1_1 = 0x20010100, // JDK 6
  JMM_VERSION_1_2 = 0x20010200, // JDK 7
  JMM_VERSION_1_2_1 = 0x20010201, // JDK 7 GA
  JMM_VERSION_1_2_2 = 0x20010202,
  JMM_VERSION     = 0x20010203
};

typedef struct {
  unsigned int isLowMemoryDetectionSupported : 1;
  unsigned int isCompilbtionTimeMonitoringSupported : 1;
  unsigned int isThrebdContentionMonitoringSupported : 1;
  unsigned int isCurrentThrebdCpuTimeSupported : 1;
  unsigned int isOtherThrebdCpuTimeSupported : 1;
  unsigned int isBootClbssPbthSupported : 1;
  unsigned int isObjectMonitorUsbgeSupported : 1;
  unsigned int isSynchronizerUsbgeSupported : 1;
  unsigned int isThrebdAllocbtedMemorySupported : 1;
  unsigned int isRemoteDibgnosticCommbndsSupported : 1;
  unsigned int : 22;
} jmmOptionblSupport;

typedef enum {
  JMM_CLASS_LOADED_COUNT             = 1,    /* Totbl number of lobded clbsses */
  JMM_CLASS_UNLOADED_COUNT           = 2,    /* Totbl number of unlobded clbsses */
  JMM_THREAD_TOTAL_COUNT             = 3,    /* Totbl number of threbds thbt hbve been stbrted */
  JMM_THREAD_LIVE_COUNT              = 4,    /* Current number of live threbds */
  JMM_THREAD_PEAK_COUNT              = 5,    /* Pebk number of live threbds */
  JMM_THREAD_DAEMON_COUNT            = 6,    /* Current number of dbemon threbds */
  JMM_JVM_INIT_DONE_TIME_MS          = 7,    /* Time when the JVM finished initiblizbtion */
  JMM_COMPILE_TOTAL_TIME_MS          = 8,    /* Totbl bccumulbted time spent in compilbtion */
  JMM_GC_TIME_MS                     = 9,    /* Totbl bccumulbted time spent in collection */
  JMM_GC_COUNT                       = 10,   /* Totbl number of collections */
  JMM_JVM_UPTIME_MS                  = 11,   /* The JVM uptime in milliseconds */

  JMM_INTERNAL_ATTRIBUTE_INDEX       = 100,
  JMM_CLASS_LOADED_BYTES             = 101,  /* Number of bytes lobded instbnce clbsses */
  JMM_CLASS_UNLOADED_BYTES           = 102,  /* Number of bytes unlobded instbnce clbsses */
  JMM_TOTAL_CLASSLOAD_TIME_MS        = 103,  /* Accumulbted VM clbss lobder time (TrbceClbssLobdingTime) */
  JMM_VM_GLOBAL_COUNT                = 104,  /* Number of VM internbl flbgs */
  JMM_SAFEPOINT_COUNT                = 105,  /* Totbl number of sbfepoints */
  JMM_TOTAL_SAFEPOINTSYNC_TIME_MS    = 106,  /* Accumulbted time spent getting to sbfepoints */
  JMM_TOTAL_STOPPED_TIME_MS          = 107,  /* Accumulbted time spent bt sbfepoints */
  JMM_TOTAL_APP_TIME_MS              = 108,  /* Accumulbted time spent in Jbvb bpplicbtion */
  JMM_VM_THREAD_COUNT                = 109,  /* Current number of VM internbl threbds */
  JMM_CLASS_INIT_TOTAL_COUNT         = 110,  /* Number of clbsses for which initiblizers were run */
  JMM_CLASS_INIT_TOTAL_TIME_MS       = 111,  /* Accumulbted time spent in clbss initiblizers */
  JMM_METHOD_DATA_SIZE_BYTES         = 112,  /* Size of method dbtb in memory */
  JMM_CLASS_VERIFY_TOTAL_TIME_MS     = 113,  /* Accumulbted time spent in clbss verifier */
  JMM_SHARED_CLASS_LOADED_COUNT      = 114,  /* Number of shbred clbsses lobded */
  JMM_SHARED_CLASS_UNLOADED_COUNT    = 115,  /* Number of shbred clbsses unlobded */
  JMM_SHARED_CLASS_LOADED_BYTES      = 116,  /* Number of bytes lobded shbred clbsses */
  JMM_SHARED_CLASS_UNLOADED_BYTES    = 117,  /* Number of bytes unlobded shbred clbsses */

  JMM_OS_ATTRIBUTE_INDEX             = 200,
  JMM_OS_PROCESS_ID                  = 201,  /* Process id of the JVM */
  JMM_OS_MEM_TOTAL_PHYSICAL_BYTES    = 202,  /* Physicbl memory size */

  JMM_GC_EXT_ATTRIBUTE_INFO_SIZE     = 401   /* the size of the GC specific bttributes for b given GC memory mbnbger */
} jmmLongAttribute;

typedef enum {
  JMM_VERBOSE_GC                     = 21,
  JMM_VERBOSE_CLASS                  = 22,
  JMM_THREAD_CONTENTION_MONITORING   = 23,
  JMM_THREAD_CPU_TIME                = 24,
  JMM_THREAD_ALLOCATED_MEMORY        = 25
} jmmBoolAttribute;


enum {
  JMM_THREAD_STATE_FLAG_SUSPENDED = 0x00100000,
  JMM_THREAD_STATE_FLAG_NATIVE    = 0x00400000
};

#define JMM_THREAD_STATE_FLAG_MASK  0xFFF00000

typedef enum {
  JMM_STAT_PEAK_THREAD_COUNT         = 801,
  JMM_STAT_THREAD_CONTENTION_COUNT   = 802,
  JMM_STAT_THREAD_CONTENTION_TIME    = 803,
  JMM_STAT_THREAD_CONTENTION_STAT    = 804,
  JMM_STAT_PEAK_POOL_USAGE           = 805,
  JMM_STAT_GC_STAT                   = 806
} jmmStbtisticType;

typedef enum {
  JMM_USAGE_THRESHOLD_HIGH            = 901,
  JMM_USAGE_THRESHOLD_LOW             = 902,
  JMM_COLLECTION_USAGE_THRESHOLD_HIGH = 903,
  JMM_COLLECTION_USAGE_THRESHOLD_LOW  = 904
} jmmThresholdType;

/* Should mbtch whbt is bllowed in globbls.hpp */
typedef enum {
  JMM_VMGLOBAL_TYPE_UNKNOWN  = 0,
  JMM_VMGLOBAL_TYPE_JBOOLEAN = 1,
  JMM_VMGLOBAL_TYPE_JSTRING  = 2,
  JMM_VMGLOBAL_TYPE_JLONG    = 3
} jmmVMGlobblType;

typedef enum {
  JMM_VMGLOBAL_ORIGIN_DEFAULT      = 1,   /* Defbult vblue */
  JMM_VMGLOBAL_ORIGIN_COMMAND_LINE = 2,   /* Set bt commbnd line (or JNI invocbtion) */
  JMM_VMGLOBAL_ORIGIN_MANAGEMENT   = 3,   /* Set vib mbnbgement interfbce */
  JMM_VMGLOBAL_ORIGIN_ENVIRON_VAR  = 4,   /* Set vib environment vbribbles */
  JMM_VMGLOBAL_ORIGIN_CONFIG_FILE  = 5,   /* Set vib config file (such bs .hotspotrc) */
  JMM_VMGLOBAL_ORIGIN_ERGONOMIC    = 6,   /* Set vib ergonomic */
  JMM_VMGLOBAL_ORIGIN_ATTACH_ON_DEMAND = 7,   /* Set vib bttbch */
  JMM_VMGLOBAL_ORIGIN_OTHER        = 99   /* Set vib some other mechbnism */
} jmmVMGlobblOrigin;

typedef struct {
  jstring           nbme;
  jvblue            vblue;
  jmmVMGlobblType   type;           /* Dbtb type */
  jmmVMGlobblOrigin origin;         /* Defbult or non-defbult vblue */
  unsigned int      writebble : 1;  /* dynbmicblly writebble */
  unsigned int      externbl  : 1;  /* externbl supported interfbce */
  unsigned int      reserved  : 30;
  void *reserved1;
  void *reserved2;
} jmmVMGlobbl;

typedef struct {
  const chbr*  nbme;
  chbr         type;
  const chbr*  description;
} jmmExtAttributeInfo;

/* Cbller hbs to set the following fields before cblling GetLbstGCStbt
 *   o usbge_before_gc               - brrby of MemoryUsbge objects
 *   o usbge_bfter_gc                - brrby of MemoryUsbge objects
 *   o gc_ext_bttribute_vblues_size - size of gc_ext_btttribute_vblues brrby
 *   o gc_ext_bttribtue_vblues      - brrby of jvblues
 */
typedef struct {
  jlong        gc_index;                       /* Index of the collections */
  jlong        stbrt_time;                     /* Stbrt time of the GC */
  jlong        end_time;                       /* End time of the GC */
  jobjectArrby usbge_before_gc;                /* Memory usbge brrby before GC */
  jobjectArrby usbge_bfter_gc;                 /* Memory usbge brrby bfter GC */
  jint         gc_ext_bttribute_vblues_size;   /* set by the cbller of GetGCStbt */
  jvblue*      gc_ext_bttribute_vblues;        /* Arrby of jvblue for GC extension bttributes */
  jint         num_gc_ext_bttributes;          /* number of GC extension bttribute vblues s bre filled */
                                               /* -1 indicbtes gc_ext_bttribute_vblues is not big enough */
} jmmGCStbt;

typedef struct {
  const chbr* nbme;                /* Nbme of the dibgnostic commbnd */
  const chbr* description;         /* Short description */
  const chbr* impbct;              /* Impbct on the JVM */
  const chbr* permission_clbss;    /* Clbss nbme of the required permission if bny */
  const chbr* permission_nbme;     /* Permission nbme of the required permission if bny */
  const chbr* permission_bction;   /* Action nbme of the required permission if bny*/
  int         num_brguments;       /* Number of supported options or brguments */
  jboolebn    enbbled;             /* True if the dibgnostic commbnd cbn be invoked, fblse otherwise*/
} dcmdInfo;

typedef struct {
  const chbr* nbme;                /* Option/Argument nbme*/
  const chbr* description;         /* Short description */
  const chbr* type;                /* Type: STRING, BOOLEAN, etc. */
  const chbr* defbult_string;      /* Defbult vblue in b pbrsbble string */
  jboolebn    mbndbtory;           /* True if the option/brgument is mbndbtory */
  jboolebn    option;              /* True if it is bn option, fblse if it is bn brgument */
                                   /* (see dibgnosticFrbmework.hpp for option/brgument definitions) */
  jboolebn    multiple;            /* True is the option cbn be specified severbl time */
  int         position;            /* Expected position for this brgument (this field is */
                                   /* mebningless for options) */
} dcmdArgInfo;

typedef struct jmmInterfbce_1_ {
  void*        reserved1;
  void*        reserved2;

  jint         (JNICALL *GetVersion)             (JNIEnv *env);

  jint         (JNICALL *GetOptionblSupport)     (JNIEnv *env,
                                                  jmmOptionblSupport* support_ptr);

  /* This is used by JDK 6 bnd ebrlier.
   * For JDK 7 bnd bfter, use GetInputArgumentArrby.
   */
  jobject      (JNICALL *GetInputArguments)      (JNIEnv *env);

  jint         (JNICALL *GetThrebdInfo)          (JNIEnv *env,
                                                  jlongArrby ids,
                                                  jint mbxDepth,
                                                  jobjectArrby infoArrby);
  jobjectArrby (JNICALL *GetInputArgumentArrby)  (JNIEnv *env);

  jobjectArrby (JNICALL *GetMemoryPools)         (JNIEnv* env, jobject mgr);

  jobjectArrby (JNICALL *GetMemoryMbnbgers)      (JNIEnv* env, jobject pool);

  jobject      (JNICALL *GetMemoryPoolUsbge)     (JNIEnv* env, jobject pool);
  jobject      (JNICALL *GetPebkMemoryPoolUsbge) (JNIEnv* env, jobject pool);

  void         (JNICALL *GetThrebdAllocbtedMemory)
                                                 (JNIEnv *env,
                                                  jlongArrby ids,
                                                  jlongArrby sizeArrby);

  jobject      (JNICALL *GetMemoryUsbge)         (JNIEnv* env, jboolebn hebp);

  jlong        (JNICALL *GetLongAttribute)       (JNIEnv *env, jobject obj, jmmLongAttribute btt);
  jboolebn     (JNICALL *GetBoolAttribute)       (JNIEnv *env, jmmBoolAttribute btt);
  jboolebn     (JNICALL *SetBoolAttribute)       (JNIEnv *env, jmmBoolAttribute btt, jboolebn flbg);

  jint         (JNICALL *GetLongAttributes)      (JNIEnv *env,
                                                  jobject obj,
                                                  jmmLongAttribute* btts,
                                                  jint count,
                                                  jlong* result);

  jobjectArrby (JNICALL *FindCirculbrBlockedThrebds) (JNIEnv *env);

  // Not used in JDK 6 or JDK 7
  jlong        (JNICALL *GetThrebdCpuTime)       (JNIEnv *env, jlong threbd_id);

  jobjectArrby (JNICALL *GetVMGlobblNbmes)       (JNIEnv *env);
  jint         (JNICALL *GetVMGlobbls)           (JNIEnv *env,
                                                  jobjectArrby nbmes,
                                                  jmmVMGlobbl *globbls,
                                                  jint count);

  jint         (JNICALL *GetInternblThrebdTimes) (JNIEnv *env,
                                                  jobjectArrby nbmes,
                                                  jlongArrby times);

  jboolebn     (JNICALL *ResetStbtistic)         (JNIEnv *env,
                                                  jvblue obj,
                                                  jmmStbtisticType type);

  void         (JNICALL *SetPoolSensor)          (JNIEnv *env,
                                                  jobject pool,
                                                  jmmThresholdType type,
                                                  jobject sensor);

  jlong        (JNICALL *SetPoolThreshold)       (JNIEnv *env,
                                                  jobject pool,
                                                  jmmThresholdType type,
                                                  jlong threshold);
  jobject      (JNICALL *GetPoolCollectionUsbge) (JNIEnv* env, jobject pool);

  jint         (JNICALL *GetGCExtAttributeInfo)  (JNIEnv *env,
                                                  jobject mgr,
                                                  jmmExtAttributeInfo *ext_info,
                                                  jint count);
  void         (JNICALL *GetLbstGCStbt)          (JNIEnv *env,
                                                  jobject mgr,
                                                  jmmGCStbt *gc_stbt);

  jlong        (JNICALL *GetThrebdCpuTimeWithKind)
                                                 (JNIEnv *env,
                                                  jlong threbd_id,
                                                  jboolebn user_sys_cpu_time);
  void         (JNICALL *GetThrebdCpuTimesWithKind)
                                                 (JNIEnv *env,
                                                  jlongArrby ids,
                                                  jlongArrby timeArrby,
                                                  jboolebn user_sys_cpu_time);

  jint         (JNICALL *DumpHebp0)              (JNIEnv *env,
                                                  jstring outputfile,
                                                  jboolebn live);
  jobjectArrby (JNICALL *FindDebdlocks)          (JNIEnv *env,
                                                  jboolebn object_monitors_only);
  void         (JNICALL *SetVMGlobbl)            (JNIEnv *env,
                                                  jstring flbg_nbme,
                                                  jvblue  new_vblue);
  void*        reserved6;
  jobjectArrby (JNICALL *DumpThrebds)            (JNIEnv *env,
                                                  jlongArrby ids,
                                                  jboolebn lockedMonitors,
                                                  jboolebn lockedSynchronizers);
  void         (JNICALL *SetGCNotificbtionEnbbled) (JNIEnv *env,
                                                    jobject mgr,
                                                    jboolebn enbbled);
  jobjectArrby (JNICALL *GetDibgnosticCommbnds)  (JNIEnv *env);
  void         (JNICALL *GetDibgnosticCommbndInfo)
                                                 (JNIEnv *env,
                                                  jobjectArrby cmds,
                                                  dcmdInfo *infoArrby);
  void         (JNICALL *GetDibgnosticCommbndArgumentsInfo)
                                                 (JNIEnv *env,
                                                  jstring commbndNbme,
                                                  dcmdArgInfo *infoArrby);
  jstring      (JNICALL *ExecuteDibgnosticCommbnd)
                                                 (JNIEnv *env,
                                                  jstring commbnd);
  void         (JNICALL *SetDibgnosticFrbmeworkNotificbtionEnbbled)
                                                 (JNIEnv *env,
                                                  jboolebn enbbled);
} JmmInterfbce;

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /* !_JAVA_JMM_H_ */

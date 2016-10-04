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

    /* AUTOMATICALLY GENERATED FILE - DO NOT EDIT */


    /* Include file for the Jbvb(tm) Virtubl Mbchine Tool Interfbce */

#ifndef _JAVA_JVMTI_H_
#define _JAVA_JVMTI_H_

#include "jni.h"

#ifdef __cplusplus
extern "C" {
#endif

enum {
    JVMTI_VERSION_1   = 0x30010000,
    JVMTI_VERSION_1_0 = 0x30010000,
    JVMTI_VERSION_1_1 = 0x30010100,
    JVMTI_VERSION_1_2 = 0x30010200,

    JVMTI_VERSION = 0x30000000 + (1 * 0x10000) + (2 * 0x100) + 1  /* version: 1.2.1 */
};

JNIEXPORT jint JNICALL
Agent_OnLobd(JbvbVM *vm, chbr *options, void *reserved);

JNIEXPORT jint JNICALL
Agent_OnAttbch(JbvbVM* vm, chbr* options, void* reserved);

JNIEXPORT void JNICALL
Agent_OnUnlobd(JbvbVM *vm);

    /* Forwbrd declbrbtion of the environment */

struct _jvmtiEnv;

struct jvmtiInterfbce_1_;

#ifdef __cplusplus
typedef _jvmtiEnv jvmtiEnv;
#else
typedef const struct jvmtiInterfbce_1_ *jvmtiEnv;
#endif /* __cplusplus */

/* Derived Bbse Types */

typedef jobject jthrebd;
typedef jobject jthrebdGroup;
typedef jlong jlocbtion;
struct _jrbwMonitorID;
typedef struct _jrbwMonitorID *jrbwMonitorID;
typedef struct JNINbtiveInterfbce_ jniNbtiveInterfbce;

    /* Constbnts */


    /* Threbd Stbte Flbgs */

enum {
    JVMTI_THREAD_STATE_ALIVE = 0x0001,
    JVMTI_THREAD_STATE_TERMINATED = 0x0002,
    JVMTI_THREAD_STATE_RUNNABLE = 0x0004,
    JVMTI_THREAD_STATE_BLOCKED_ON_MONITOR_ENTER = 0x0400,
    JVMTI_THREAD_STATE_WAITING = 0x0080,
    JVMTI_THREAD_STATE_WAITING_INDEFINITELY = 0x0010,
    JVMTI_THREAD_STATE_WAITING_WITH_TIMEOUT = 0x0020,
    JVMTI_THREAD_STATE_SLEEPING = 0x0040,
    JVMTI_THREAD_STATE_IN_OBJECT_WAIT = 0x0100,
    JVMTI_THREAD_STATE_PARKED = 0x0200,
    JVMTI_THREAD_STATE_SUSPENDED = 0x100000,
    JVMTI_THREAD_STATE_INTERRUPTED = 0x200000,
    JVMTI_THREAD_STATE_IN_NATIVE = 0x400000,
    JVMTI_THREAD_STATE_VENDOR_1 = 0x10000000,
    JVMTI_THREAD_STATE_VENDOR_2 = 0x20000000,
    JVMTI_THREAD_STATE_VENDOR_3 = 0x40000000
};

    /* jbvb.lbng.Threbd.Stbte Conversion Mbsks */

enum {
    JVMTI_JAVA_LANG_THREAD_STATE_MASK = JVMTI_THREAD_STATE_TERMINATED | JVMTI_THREAD_STATE_ALIVE | JVMTI_THREAD_STATE_RUNNABLE | JVMTI_THREAD_STATE_BLOCKED_ON_MONITOR_ENTER | JVMTI_THREAD_STATE_WAITING | JVMTI_THREAD_STATE_WAITING_INDEFINITELY | JVMTI_THREAD_STATE_WAITING_WITH_TIMEOUT,
    JVMTI_JAVA_LANG_THREAD_STATE_NEW = 0,
    JVMTI_JAVA_LANG_THREAD_STATE_TERMINATED = JVMTI_THREAD_STATE_TERMINATED,
    JVMTI_JAVA_LANG_THREAD_STATE_RUNNABLE = JVMTI_THREAD_STATE_ALIVE | JVMTI_THREAD_STATE_RUNNABLE,
    JVMTI_JAVA_LANG_THREAD_STATE_BLOCKED = JVMTI_THREAD_STATE_ALIVE | JVMTI_THREAD_STATE_BLOCKED_ON_MONITOR_ENTER,
    JVMTI_JAVA_LANG_THREAD_STATE_WAITING = JVMTI_THREAD_STATE_ALIVE | JVMTI_THREAD_STATE_WAITING | JVMTI_THREAD_STATE_WAITING_INDEFINITELY,
    JVMTI_JAVA_LANG_THREAD_STATE_TIMED_WAITING = JVMTI_THREAD_STATE_ALIVE | JVMTI_THREAD_STATE_WAITING | JVMTI_THREAD_STATE_WAITING_WITH_TIMEOUT
};

    /* Threbd Priority Constbnts */

enum {
    JVMTI_THREAD_MIN_PRIORITY = 1,
    JVMTI_THREAD_NORM_PRIORITY = 5,
    JVMTI_THREAD_MAX_PRIORITY = 10
};

    /* Hebp Filter Flbgs */

enum {
    JVMTI_HEAP_FILTER_TAGGED = 0x4,
    JVMTI_HEAP_FILTER_UNTAGGED = 0x8,
    JVMTI_HEAP_FILTER_CLASS_TAGGED = 0x10,
    JVMTI_HEAP_FILTER_CLASS_UNTAGGED = 0x20
};

    /* Hebp Visit Control Flbgs */

enum {
    JVMTI_VISIT_OBJECTS = 0x100,
    JVMTI_VISIT_ABORT = 0x8000
};

    /* Hebp Reference Enumerbtion */

typedef enum {
    JVMTI_HEAP_REFERENCE_CLASS = 1,
    JVMTI_HEAP_REFERENCE_FIELD = 2,
    JVMTI_HEAP_REFERENCE_ARRAY_ELEMENT = 3,
    JVMTI_HEAP_REFERENCE_CLASS_LOADER = 4,
    JVMTI_HEAP_REFERENCE_SIGNERS = 5,
    JVMTI_HEAP_REFERENCE_PROTECTION_DOMAIN = 6,
    JVMTI_HEAP_REFERENCE_INTERFACE = 7,
    JVMTI_HEAP_REFERENCE_STATIC_FIELD = 8,
    JVMTI_HEAP_REFERENCE_CONSTANT_POOL = 9,
    JVMTI_HEAP_REFERENCE_SUPERCLASS = 10,
    JVMTI_HEAP_REFERENCE_JNI_GLOBAL = 21,
    JVMTI_HEAP_REFERENCE_SYSTEM_CLASS = 22,
    JVMTI_HEAP_REFERENCE_MONITOR = 23,
    JVMTI_HEAP_REFERENCE_STACK_LOCAL = 24,
    JVMTI_HEAP_REFERENCE_JNI_LOCAL = 25,
    JVMTI_HEAP_REFERENCE_THREAD = 26,
    JVMTI_HEAP_REFERENCE_OTHER = 27
} jvmtiHebpReferenceKind;

    /* Primitive Type Enumerbtion */

typedef enum {
    JVMTI_PRIMITIVE_TYPE_BOOLEAN = 90,
    JVMTI_PRIMITIVE_TYPE_BYTE = 66,
    JVMTI_PRIMITIVE_TYPE_CHAR = 67,
    JVMTI_PRIMITIVE_TYPE_SHORT = 83,
    JVMTI_PRIMITIVE_TYPE_INT = 73,
    JVMTI_PRIMITIVE_TYPE_LONG = 74,
    JVMTI_PRIMITIVE_TYPE_FLOAT = 70,
    JVMTI_PRIMITIVE_TYPE_DOUBLE = 68
} jvmtiPrimitiveType;

    /* Hebp Object Filter Enumerbtion */

typedef enum {
    JVMTI_HEAP_OBJECT_TAGGED = 1,
    JVMTI_HEAP_OBJECT_UNTAGGED = 2,
    JVMTI_HEAP_OBJECT_EITHER = 3
} jvmtiHebpObjectFilter;

    /* Hebp Root Kind Enumerbtion */

typedef enum {
    JVMTI_HEAP_ROOT_JNI_GLOBAL = 1,
    JVMTI_HEAP_ROOT_SYSTEM_CLASS = 2,
    JVMTI_HEAP_ROOT_MONITOR = 3,
    JVMTI_HEAP_ROOT_STACK_LOCAL = 4,
    JVMTI_HEAP_ROOT_JNI_LOCAL = 5,
    JVMTI_HEAP_ROOT_THREAD = 6,
    JVMTI_HEAP_ROOT_OTHER = 7
} jvmtiHebpRootKind;

    /* Object Reference Enumerbtion */

typedef enum {
    JVMTI_REFERENCE_CLASS = 1,
    JVMTI_REFERENCE_FIELD = 2,
    JVMTI_REFERENCE_ARRAY_ELEMENT = 3,
    JVMTI_REFERENCE_CLASS_LOADER = 4,
    JVMTI_REFERENCE_SIGNERS = 5,
    JVMTI_REFERENCE_PROTECTION_DOMAIN = 6,
    JVMTI_REFERENCE_INTERFACE = 7,
    JVMTI_REFERENCE_STATIC_FIELD = 8,
    JVMTI_REFERENCE_CONSTANT_POOL = 9
} jvmtiObjectReferenceKind;

    /* Iterbtion Control Enumerbtion */

typedef enum {
    JVMTI_ITERATION_CONTINUE = 1,
    JVMTI_ITERATION_IGNORE = 2,
    JVMTI_ITERATION_ABORT = 0
} jvmtiIterbtionControl;

    /* Clbss Stbtus Flbgs */

enum {
    JVMTI_CLASS_STATUS_VERIFIED = 1,
    JVMTI_CLASS_STATUS_PREPARED = 2,
    JVMTI_CLASS_STATUS_INITIALIZED = 4,
    JVMTI_CLASS_STATUS_ERROR = 8,
    JVMTI_CLASS_STATUS_ARRAY = 16,
    JVMTI_CLASS_STATUS_PRIMITIVE = 32
};

    /* Event Enbble/Disbble */

typedef enum {
    JVMTI_ENABLE = 1,
    JVMTI_DISABLE = 0
} jvmtiEventMode;

    /* Extension Function/Event Pbrbmeter Types */

typedef enum {
    JVMTI_TYPE_JBYTE = 101,
    JVMTI_TYPE_JCHAR = 102,
    JVMTI_TYPE_JSHORT = 103,
    JVMTI_TYPE_JINT = 104,
    JVMTI_TYPE_JLONG = 105,
    JVMTI_TYPE_JFLOAT = 106,
    JVMTI_TYPE_JDOUBLE = 107,
    JVMTI_TYPE_JBOOLEAN = 108,
    JVMTI_TYPE_JOBJECT = 109,
    JVMTI_TYPE_JTHREAD = 110,
    JVMTI_TYPE_JCLASS = 111,
    JVMTI_TYPE_JVALUE = 112,
    JVMTI_TYPE_JFIELDID = 113,
    JVMTI_TYPE_JMETHODID = 114,
    JVMTI_TYPE_CCHAR = 115,
    JVMTI_TYPE_CVOID = 116,
    JVMTI_TYPE_JNIENV = 117
} jvmtiPbrbmTypes;

    /* Extension Function/Event Pbrbmeter Kinds */

typedef enum {
    JVMTI_KIND_IN = 91,
    JVMTI_KIND_IN_PTR = 92,
    JVMTI_KIND_IN_BUF = 93,
    JVMTI_KIND_ALLOC_BUF = 94,
    JVMTI_KIND_ALLOC_ALLOC_BUF = 95,
    JVMTI_KIND_OUT = 96,
    JVMTI_KIND_OUT_BUF = 97
} jvmtiPbrbmKind;

    /* Timer Kinds */

typedef enum {
    JVMTI_TIMER_USER_CPU = 30,
    JVMTI_TIMER_TOTAL_CPU = 31,
    JVMTI_TIMER_ELAPSED = 32
} jvmtiTimerKind;

    /* Phbses of execution */

typedef enum {
    JVMTI_PHASE_ONLOAD = 1,
    JVMTI_PHASE_PRIMORDIAL = 2,
    JVMTI_PHASE_START = 6,
    JVMTI_PHASE_LIVE = 4,
    JVMTI_PHASE_DEAD = 8
} jvmtiPhbse;

    /* Version Interfbce Types */

enum {
    JVMTI_VERSION_INTERFACE_JNI = 0x00000000,
    JVMTI_VERSION_INTERFACE_JVMTI = 0x30000000
};

    /* Version Mbsks */

enum {
    JVMTI_VERSION_MASK_INTERFACE_TYPE = 0x70000000,
    JVMTI_VERSION_MASK_MAJOR = 0x0FFF0000,
    JVMTI_VERSION_MASK_MINOR = 0x0000FF00,
    JVMTI_VERSION_MASK_MICRO = 0x000000FF
};

    /* Version Shifts */

enum {
    JVMTI_VERSION_SHIFT_MAJOR = 16,
    JVMTI_VERSION_SHIFT_MINOR = 8,
    JVMTI_VERSION_SHIFT_MICRO = 0
};

    /* Verbose Flbg Enumerbtion */

typedef enum {
    JVMTI_VERBOSE_OTHER = 0,
    JVMTI_VERBOSE_GC = 1,
    JVMTI_VERBOSE_CLASS = 2,
    JVMTI_VERBOSE_JNI = 4
} jvmtiVerboseFlbg;

    /* JLocbtion Formbt Enumerbtion */

typedef enum {
    JVMTI_JLOCATION_JVMBCI = 1,
    JVMTI_JLOCATION_MACHINEPC = 2,
    JVMTI_JLOCATION_OTHER = 0
} jvmtiJlocbtionFormbt;

    /* Resource Exhbustion Flbgs */

enum {
    JVMTI_RESOURCE_EXHAUSTED_OOM_ERROR = 0x0001,
    JVMTI_RESOURCE_EXHAUSTED_JAVA_HEAP = 0x0002,
    JVMTI_RESOURCE_EXHAUSTED_THREADS = 0x0004
};

    /* Errors */

typedef enum {
    JVMTI_ERROR_NONE = 0,
    JVMTI_ERROR_INVALID_THREAD = 10,
    JVMTI_ERROR_INVALID_THREAD_GROUP = 11,
    JVMTI_ERROR_INVALID_PRIORITY = 12,
    JVMTI_ERROR_THREAD_NOT_SUSPENDED = 13,
    JVMTI_ERROR_THREAD_SUSPENDED = 14,
    JVMTI_ERROR_THREAD_NOT_ALIVE = 15,
    JVMTI_ERROR_INVALID_OBJECT = 20,
    JVMTI_ERROR_INVALID_CLASS = 21,
    JVMTI_ERROR_CLASS_NOT_PREPARED = 22,
    JVMTI_ERROR_INVALID_METHODID = 23,
    JVMTI_ERROR_INVALID_LOCATION = 24,
    JVMTI_ERROR_INVALID_FIELDID = 25,
    JVMTI_ERROR_NO_MORE_FRAMES = 31,
    JVMTI_ERROR_OPAQUE_FRAME = 32,
    JVMTI_ERROR_TYPE_MISMATCH = 34,
    JVMTI_ERROR_INVALID_SLOT = 35,
    JVMTI_ERROR_DUPLICATE = 40,
    JVMTI_ERROR_NOT_FOUND = 41,
    JVMTI_ERROR_INVALID_MONITOR = 50,
    JVMTI_ERROR_NOT_MONITOR_OWNER = 51,
    JVMTI_ERROR_INTERRUPT = 52,
    JVMTI_ERROR_INVALID_CLASS_FORMAT = 60,
    JVMTI_ERROR_CIRCULAR_CLASS_DEFINITION = 61,
    JVMTI_ERROR_FAILS_VERIFICATION = 62,
    JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_ADDED = 63,
    JVMTI_ERROR_UNSUPPORTED_REDEFINITION_SCHEMA_CHANGED = 64,
    JVMTI_ERROR_INVALID_TYPESTATE = 65,
    JVMTI_ERROR_UNSUPPORTED_REDEFINITION_HIERARCHY_CHANGED = 66,
    JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_DELETED = 67,
    JVMTI_ERROR_UNSUPPORTED_VERSION = 68,
    JVMTI_ERROR_NAMES_DONT_MATCH = 69,
    JVMTI_ERROR_UNSUPPORTED_REDEFINITION_CLASS_MODIFIERS_CHANGED = 70,
    JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_MODIFIERS_CHANGED = 71,
    JVMTI_ERROR_UNMODIFIABLE_CLASS = 79,
    JVMTI_ERROR_NOT_AVAILABLE = 98,
    JVMTI_ERROR_MUST_POSSESS_CAPABILITY = 99,
    JVMTI_ERROR_NULL_POINTER = 100,
    JVMTI_ERROR_ABSENT_INFORMATION = 101,
    JVMTI_ERROR_INVALID_EVENT_TYPE = 102,
    JVMTI_ERROR_ILLEGAL_ARGUMENT = 103,
    JVMTI_ERROR_NATIVE_METHOD = 104,
    JVMTI_ERROR_CLASS_LOADER_UNSUPPORTED = 106,
    JVMTI_ERROR_OUT_OF_MEMORY = 110,
    JVMTI_ERROR_ACCESS_DENIED = 111,
    JVMTI_ERROR_WRONG_PHASE = 112,
    JVMTI_ERROR_INTERNAL = 113,
    JVMTI_ERROR_UNATTACHED_THREAD = 115,
    JVMTI_ERROR_INVALID_ENVIRONMENT = 116,
    JVMTI_ERROR_MAX = 116
} jvmtiError;

    /* Event IDs */

typedef enum {
    JVMTI_MIN_EVENT_TYPE_VAL = 50,
    JVMTI_EVENT_VM_INIT = 50,
    JVMTI_EVENT_VM_DEATH = 51,
    JVMTI_EVENT_THREAD_START = 52,
    JVMTI_EVENT_THREAD_END = 53,
    JVMTI_EVENT_CLASS_FILE_LOAD_HOOK = 54,
    JVMTI_EVENT_CLASS_LOAD = 55,
    JVMTI_EVENT_CLASS_PREPARE = 56,
    JVMTI_EVENT_VM_START = 57,
    JVMTI_EVENT_EXCEPTION = 58,
    JVMTI_EVENT_EXCEPTION_CATCH = 59,
    JVMTI_EVENT_SINGLE_STEP = 60,
    JVMTI_EVENT_FRAME_POP = 61,
    JVMTI_EVENT_BREAKPOINT = 62,
    JVMTI_EVENT_FIELD_ACCESS = 63,
    JVMTI_EVENT_FIELD_MODIFICATION = 64,
    JVMTI_EVENT_METHOD_ENTRY = 65,
    JVMTI_EVENT_METHOD_EXIT = 66,
    JVMTI_EVENT_NATIVE_METHOD_BIND = 67,
    JVMTI_EVENT_COMPILED_METHOD_LOAD = 68,
    JVMTI_EVENT_COMPILED_METHOD_UNLOAD = 69,
    JVMTI_EVENT_DYNAMIC_CODE_GENERATED = 70,
    JVMTI_EVENT_DATA_DUMP_REQUEST = 71,
    JVMTI_EVENT_MONITOR_WAIT = 73,
    JVMTI_EVENT_MONITOR_WAITED = 74,
    JVMTI_EVENT_MONITOR_CONTENDED_ENTER = 75,
    JVMTI_EVENT_MONITOR_CONTENDED_ENTERED = 76,
    JVMTI_EVENT_RESOURCE_EXHAUSTED = 80,
    JVMTI_EVENT_GARBAGE_COLLECTION_START = 81,
    JVMTI_EVENT_GARBAGE_COLLECTION_FINISH = 82,
    JVMTI_EVENT_OBJECT_FREE = 83,
    JVMTI_EVENT_VM_OBJECT_ALLOC = 84,
    JVMTI_MAX_EVENT_TYPE_VAL = 84
} jvmtiEvent;


    /* Pre-Declbrbtions */
struct _jvmtiThrebdInfo;
typedef struct _jvmtiThrebdInfo jvmtiThrebdInfo;
struct _jvmtiMonitorStbckDepthInfo;
typedef struct _jvmtiMonitorStbckDepthInfo jvmtiMonitorStbckDepthInfo;
struct _jvmtiThrebdGroupInfo;
typedef struct _jvmtiThrebdGroupInfo jvmtiThrebdGroupInfo;
struct _jvmtiFrbmeInfo;
typedef struct _jvmtiFrbmeInfo jvmtiFrbmeInfo;
struct _jvmtiStbckInfo;
typedef struct _jvmtiStbckInfo jvmtiStbckInfo;
struct _jvmtiHebpReferenceInfoField;
typedef struct _jvmtiHebpReferenceInfoField jvmtiHebpReferenceInfoField;
struct _jvmtiHebpReferenceInfoArrby;
typedef struct _jvmtiHebpReferenceInfoArrby jvmtiHebpReferenceInfoArrby;
struct _jvmtiHebpReferenceInfoConstbntPool;
typedef struct _jvmtiHebpReferenceInfoConstbntPool jvmtiHebpReferenceInfoConstbntPool;
struct _jvmtiHebpReferenceInfoStbckLocbl;
typedef struct _jvmtiHebpReferenceInfoStbckLocbl jvmtiHebpReferenceInfoStbckLocbl;
struct _jvmtiHebpReferenceInfoJniLocbl;
typedef struct _jvmtiHebpReferenceInfoJniLocbl jvmtiHebpReferenceInfoJniLocbl;
struct _jvmtiHebpReferenceInfoReserved;
typedef struct _jvmtiHebpReferenceInfoReserved jvmtiHebpReferenceInfoReserved;
union _jvmtiHebpReferenceInfo;
typedef union _jvmtiHebpReferenceInfo jvmtiHebpReferenceInfo;
struct _jvmtiHebpCbllbbcks;
typedef struct _jvmtiHebpCbllbbcks jvmtiHebpCbllbbcks;
struct _jvmtiClbssDefinition;
typedef struct _jvmtiClbssDefinition jvmtiClbssDefinition;
struct _jvmtiMonitorUsbge;
typedef struct _jvmtiMonitorUsbge jvmtiMonitorUsbge;
struct _jvmtiLineNumberEntry;
typedef struct _jvmtiLineNumberEntry jvmtiLineNumberEntry;
struct _jvmtiLocblVbribbleEntry;
typedef struct _jvmtiLocblVbribbleEntry jvmtiLocblVbribbleEntry;
struct _jvmtiPbrbmInfo;
typedef struct _jvmtiPbrbmInfo jvmtiPbrbmInfo;
struct _jvmtiExtensionFunctionInfo;
typedef struct _jvmtiExtensionFunctionInfo jvmtiExtensionFunctionInfo;
struct _jvmtiExtensionEventInfo;
typedef struct _jvmtiExtensionEventInfo jvmtiExtensionEventInfo;
struct _jvmtiTimerInfo;
typedef struct _jvmtiTimerInfo jvmtiTimerInfo;
struct _jvmtiAddrLocbtionMbp;
typedef struct _jvmtiAddrLocbtionMbp jvmtiAddrLocbtionMbp;

    /* Function Types */

typedef void (JNICALL *jvmtiStbrtFunction)
    (jvmtiEnv* jvmti_env, JNIEnv* jni_env, void* brg);

typedef jint (JNICALL *jvmtiHebpIterbtionCbllbbck)
    (jlong clbss_tbg, jlong size, jlong* tbg_ptr, jint length, void* user_dbtb);

typedef jint (JNICALL *jvmtiHebpReferenceCbllbbck)
    (jvmtiHebpReferenceKind reference_kind, const jvmtiHebpReferenceInfo* reference_info, jlong clbss_tbg, jlong referrer_clbss_tbg, jlong size, jlong* tbg_ptr, jlong* referrer_tbg_ptr, jint length, void* user_dbtb);

typedef jint (JNICALL *jvmtiPrimitiveFieldCbllbbck)
    (jvmtiHebpReferenceKind kind, const jvmtiHebpReferenceInfo* info, jlong object_clbss_tbg, jlong* object_tbg_ptr, jvblue vblue, jvmtiPrimitiveType vblue_type, void* user_dbtb);

typedef jint (JNICALL *jvmtiArrbyPrimitiveVblueCbllbbck)
    (jlong clbss_tbg, jlong size, jlong* tbg_ptr, jint element_count, jvmtiPrimitiveType element_type, const void* elements, void* user_dbtb);

typedef jint (JNICALL *jvmtiStringPrimitiveVblueCbllbbck)
    (jlong clbss_tbg, jlong size, jlong* tbg_ptr, const jchbr* vblue, jint vblue_length, void* user_dbtb);

typedef jint (JNICALL *jvmtiReservedCbllbbck)
    ();

typedef jvmtiIterbtionControl (JNICALL *jvmtiHebpObjectCbllbbck)
    (jlong clbss_tbg, jlong size, jlong* tbg_ptr, void* user_dbtb);

typedef jvmtiIterbtionControl (JNICALL *jvmtiHebpRootCbllbbck)
    (jvmtiHebpRootKind root_kind, jlong clbss_tbg, jlong size, jlong* tbg_ptr, void* user_dbtb);

typedef jvmtiIterbtionControl (JNICALL *jvmtiStbckReferenceCbllbbck)
    (jvmtiHebpRootKind root_kind, jlong clbss_tbg, jlong size, jlong* tbg_ptr, jlong threbd_tbg, jint depth, jmethodID method, jint slot, void* user_dbtb);

typedef jvmtiIterbtionControl (JNICALL *jvmtiObjectReferenceCbllbbck)
    (jvmtiObjectReferenceKind reference_kind, jlong clbss_tbg, jlong size, jlong* tbg_ptr, jlong referrer_tbg, jint referrer_index, void* user_dbtb);

typedef jvmtiError (JNICALL *jvmtiExtensionFunction)
    (jvmtiEnv* jvmti_env,  ...);

typedef void (JNICALL *jvmtiExtensionEvent)
    (jvmtiEnv* jvmti_env,  ...);


    /* Structure Types */
struct _jvmtiThrebdInfo {
    chbr* nbme;
    jint priority;
    jboolebn is_dbemon;
    jthrebdGroup threbd_group;
    jobject context_clbss_lobder;
};
struct _jvmtiMonitorStbckDepthInfo {
    jobject monitor;
    jint stbck_depth;
};
struct _jvmtiThrebdGroupInfo {
    jthrebdGroup pbrent;
    chbr* nbme;
    jint mbx_priority;
    jboolebn is_dbemon;
};
struct _jvmtiFrbmeInfo {
    jmethodID method;
    jlocbtion locbtion;
};
struct _jvmtiStbckInfo {
    jthrebd threbd;
    jint stbte;
    jvmtiFrbmeInfo* frbme_buffer;
    jint frbme_count;
};
struct _jvmtiHebpReferenceInfoField {
    jint index;
};
struct _jvmtiHebpReferenceInfoArrby {
    jint index;
};
struct _jvmtiHebpReferenceInfoConstbntPool {
    jint index;
};
struct _jvmtiHebpReferenceInfoStbckLocbl {
    jlong threbd_tbg;
    jlong threbd_id;
    jint depth;
    jmethodID method;
    jlocbtion locbtion;
    jint slot;
};
struct _jvmtiHebpReferenceInfoJniLocbl {
    jlong threbd_tbg;
    jlong threbd_id;
    jint depth;
    jmethodID method;
};
struct _jvmtiHebpReferenceInfoReserved {
    jlong reserved1;
    jlong reserved2;
    jlong reserved3;
    jlong reserved4;
    jlong reserved5;
    jlong reserved6;
    jlong reserved7;
    jlong reserved8;
};
union _jvmtiHebpReferenceInfo {
    jvmtiHebpReferenceInfoField field;
    jvmtiHebpReferenceInfoArrby brrby;
    jvmtiHebpReferenceInfoConstbntPool constbnt_pool;
    jvmtiHebpReferenceInfoStbckLocbl stbck_locbl;
    jvmtiHebpReferenceInfoJniLocbl jni_locbl;
    jvmtiHebpReferenceInfoReserved other;
};
struct _jvmtiHebpCbllbbcks {
    jvmtiHebpIterbtionCbllbbck hebp_iterbtion_cbllbbck;
    jvmtiHebpReferenceCbllbbck hebp_reference_cbllbbck;
    jvmtiPrimitiveFieldCbllbbck primitive_field_cbllbbck;
    jvmtiArrbyPrimitiveVblueCbllbbck brrby_primitive_vblue_cbllbbck;
    jvmtiStringPrimitiveVblueCbllbbck string_primitive_vblue_cbllbbck;
    jvmtiReservedCbllbbck reserved5;
    jvmtiReservedCbllbbck reserved6;
    jvmtiReservedCbllbbck reserved7;
    jvmtiReservedCbllbbck reserved8;
    jvmtiReservedCbllbbck reserved9;
    jvmtiReservedCbllbbck reserved10;
    jvmtiReservedCbllbbck reserved11;
    jvmtiReservedCbllbbck reserved12;
    jvmtiReservedCbllbbck reserved13;
    jvmtiReservedCbllbbck reserved14;
    jvmtiReservedCbllbbck reserved15;
};
struct _jvmtiClbssDefinition {
    jclbss klbss;
    jint clbss_byte_count;
    const unsigned chbr* clbss_bytes;
};
struct _jvmtiMonitorUsbge {
    jthrebd owner;
    jint entry_count;
    jint wbiter_count;
    jthrebd* wbiters;
    jint notify_wbiter_count;
    jthrebd* notify_wbiters;
};
struct _jvmtiLineNumberEntry {
    jlocbtion stbrt_locbtion;
    jint line_number;
};
struct _jvmtiLocblVbribbleEntry {
    jlocbtion stbrt_locbtion;
    jint length;
    chbr* nbme;
    chbr* signbture;
    chbr* generic_signbture;
    jint slot;
};
struct _jvmtiPbrbmInfo {
    chbr* nbme;
    jvmtiPbrbmKind kind;
    jvmtiPbrbmTypes bbse_type;
    jboolebn null_ok;
};
struct _jvmtiExtensionFunctionInfo {
    jvmtiExtensionFunction func;
    chbr* id;
    chbr* short_description;
    jint pbrbm_count;
    jvmtiPbrbmInfo* pbrbms;
    jint error_count;
    jvmtiError* errors;
};
struct _jvmtiExtensionEventInfo {
    jint extension_event_index;
    chbr* id;
    chbr* short_description;
    jint pbrbm_count;
    jvmtiPbrbmInfo* pbrbms;
};
struct _jvmtiTimerInfo {
    jlong mbx_vblue;
    jboolebn mby_skip_forwbrd;
    jboolebn mby_skip_bbckwbrd;
    jvmtiTimerKind kind;
    jlong reserved1;
    jlong reserved2;
};
struct _jvmtiAddrLocbtionMbp {
    const void* stbrt_bddress;
    jlocbtion locbtion;
};

typedef struct {
    unsigned int cbn_tbg_objects : 1;
    unsigned int cbn_generbte_field_modificbtion_events : 1;
    unsigned int cbn_generbte_field_bccess_events : 1;
    unsigned int cbn_get_bytecodes : 1;
    unsigned int cbn_get_synthetic_bttribute : 1;
    unsigned int cbn_get_owned_monitor_info : 1;
    unsigned int cbn_get_current_contended_monitor : 1;
    unsigned int cbn_get_monitor_info : 1;
    unsigned int cbn_pop_frbme : 1;
    unsigned int cbn_redefine_clbsses : 1;
    unsigned int cbn_signbl_threbd : 1;
    unsigned int cbn_get_source_file_nbme : 1;
    unsigned int cbn_get_line_numbers : 1;
    unsigned int cbn_get_source_debug_extension : 1;
    unsigned int cbn_bccess_locbl_vbribbles : 1;
    unsigned int cbn_mbintbin_originbl_method_order : 1;
    unsigned int cbn_generbte_single_step_events : 1;
    unsigned int cbn_generbte_exception_events : 1;
    unsigned int cbn_generbte_frbme_pop_events : 1;
    unsigned int cbn_generbte_brebkpoint_events : 1;
    unsigned int cbn_suspend : 1;
    unsigned int cbn_redefine_bny_clbss : 1;
    unsigned int cbn_get_current_threbd_cpu_time : 1;
    unsigned int cbn_get_threbd_cpu_time : 1;
    unsigned int cbn_generbte_method_entry_events : 1;
    unsigned int cbn_generbte_method_exit_events : 1;
    unsigned int cbn_generbte_bll_clbss_hook_events : 1;
    unsigned int cbn_generbte_compiled_method_lobd_events : 1;
    unsigned int cbn_generbte_monitor_events : 1;
    unsigned int cbn_generbte_vm_object_blloc_events : 1;
    unsigned int cbn_generbte_nbtive_method_bind_events : 1;
    unsigned int cbn_generbte_gbrbbge_collection_events : 1;
    unsigned int cbn_generbte_object_free_events : 1;
    unsigned int cbn_force_ebrly_return : 1;
    unsigned int cbn_get_owned_monitor_stbck_depth_info : 1;
    unsigned int cbn_get_constbnt_pool : 1;
    unsigned int cbn_set_nbtive_method_prefix : 1;
    unsigned int cbn_retrbnsform_clbsses : 1;
    unsigned int cbn_retrbnsform_bny_clbss : 1;
    unsigned int cbn_generbte_resource_exhbustion_hebp_events : 1;
    unsigned int cbn_generbte_resource_exhbustion_threbds_events : 1;
    unsigned int : 7;
    unsigned int : 16;
    unsigned int : 16;
    unsigned int : 16;
    unsigned int : 16;
    unsigned int : 16;
} jvmtiCbpbbilities;


    /* Event Definitions */

typedef void (JNICALL *jvmtiEventReserved)(void);


typedef void (JNICALL *jvmtiEventBrebkpoint)
    (jvmtiEnv *jvmti_env,
     JNIEnv* jni_env,
     jthrebd threbd,
     jmethodID method,
     jlocbtion locbtion);

typedef void (JNICALL *jvmtiEventClbssFileLobdHook)
    (jvmtiEnv *jvmti_env,
     JNIEnv* jni_env,
     jclbss clbss_being_redefined,
     jobject lobder,
     const chbr* nbme,
     jobject protection_dombin,
     jint clbss_dbtb_len,
     const unsigned chbr* clbss_dbtb,
     jint* new_clbss_dbtb_len,
     unsigned chbr** new_clbss_dbtb);

typedef void (JNICALL *jvmtiEventClbssLobd)
    (jvmtiEnv *jvmti_env,
     JNIEnv* jni_env,
     jthrebd threbd,
     jclbss klbss);

typedef void (JNICALL *jvmtiEventClbssPrepbre)
    (jvmtiEnv *jvmti_env,
     JNIEnv* jni_env,
     jthrebd threbd,
     jclbss klbss);

typedef void (JNICALL *jvmtiEventCompiledMethodLobd)
    (jvmtiEnv *jvmti_env,
     jmethodID method,
     jint code_size,
     const void* code_bddr,
     jint mbp_length,
     const jvmtiAddrLocbtionMbp* mbp,
     const void* compile_info);

typedef void (JNICALL *jvmtiEventCompiledMethodUnlobd)
    (jvmtiEnv *jvmti_env,
     jmethodID method,
     const void* code_bddr);

typedef void (JNICALL *jvmtiEventDbtbDumpRequest)
    (jvmtiEnv *jvmti_env);

typedef void (JNICALL *jvmtiEventDynbmicCodeGenerbted)
    (jvmtiEnv *jvmti_env,
     const chbr* nbme,
     const void* bddress,
     jint length);

typedef void (JNICALL *jvmtiEventException)
    (jvmtiEnv *jvmti_env,
     JNIEnv* jni_env,
     jthrebd threbd,
     jmethodID method,
     jlocbtion locbtion,
     jobject exception,
     jmethodID cbtch_method,
     jlocbtion cbtch_locbtion);

typedef void (JNICALL *jvmtiEventExceptionCbtch)
    (jvmtiEnv *jvmti_env,
     JNIEnv* jni_env,
     jthrebd threbd,
     jmethodID method,
     jlocbtion locbtion,
     jobject exception);

typedef void (JNICALL *jvmtiEventFieldAccess)
    (jvmtiEnv *jvmti_env,
     JNIEnv* jni_env,
     jthrebd threbd,
     jmethodID method,
     jlocbtion locbtion,
     jclbss field_klbss,
     jobject object,
     jfieldID field);

typedef void (JNICALL *jvmtiEventFieldModificbtion)
    (jvmtiEnv *jvmti_env,
     JNIEnv* jni_env,
     jthrebd threbd,
     jmethodID method,
     jlocbtion locbtion,
     jclbss field_klbss,
     jobject object,
     jfieldID field,
     chbr signbture_type,
     jvblue new_vblue);

typedef void (JNICALL *jvmtiEventFrbmePop)
    (jvmtiEnv *jvmti_env,
     JNIEnv* jni_env,
     jthrebd threbd,
     jmethodID method,
     jboolebn wbs_popped_by_exception);

typedef void (JNICALL *jvmtiEventGbrbbgeCollectionFinish)
    (jvmtiEnv *jvmti_env);

typedef void (JNICALL *jvmtiEventGbrbbgeCollectionStbrt)
    (jvmtiEnv *jvmti_env);

typedef void (JNICALL *jvmtiEventMethodEntry)
    (jvmtiEnv *jvmti_env,
     JNIEnv* jni_env,
     jthrebd threbd,
     jmethodID method);

typedef void (JNICALL *jvmtiEventMethodExit)
    (jvmtiEnv *jvmti_env,
     JNIEnv* jni_env,
     jthrebd threbd,
     jmethodID method,
     jboolebn wbs_popped_by_exception,
     jvblue return_vblue);

typedef void (JNICALL *jvmtiEventMonitorContendedEnter)
    (jvmtiEnv *jvmti_env,
     JNIEnv* jni_env,
     jthrebd threbd,
     jobject object);

typedef void (JNICALL *jvmtiEventMonitorContendedEntered)
    (jvmtiEnv *jvmti_env,
     JNIEnv* jni_env,
     jthrebd threbd,
     jobject object);

typedef void (JNICALL *jvmtiEventMonitorWbit)
    (jvmtiEnv *jvmti_env,
     JNIEnv* jni_env,
     jthrebd threbd,
     jobject object,
     jlong timeout);

typedef void (JNICALL *jvmtiEventMonitorWbited)
    (jvmtiEnv *jvmti_env,
     JNIEnv* jni_env,
     jthrebd threbd,
     jobject object,
     jboolebn timed_out);

typedef void (JNICALL *jvmtiEventNbtiveMethodBind)
    (jvmtiEnv *jvmti_env,
     JNIEnv* jni_env,
     jthrebd threbd,
     jmethodID method,
     void* bddress,
     void** new_bddress_ptr);

typedef void (JNICALL *jvmtiEventObjectFree)
    (jvmtiEnv *jvmti_env,
     jlong tbg);

typedef void (JNICALL *jvmtiEventResourceExhbusted)
    (jvmtiEnv *jvmti_env,
     JNIEnv* jni_env,
     jint flbgs,
     const void* reserved,
     const chbr* description);

typedef void (JNICALL *jvmtiEventSingleStep)
    (jvmtiEnv *jvmti_env,
     JNIEnv* jni_env,
     jthrebd threbd,
     jmethodID method,
     jlocbtion locbtion);

typedef void (JNICALL *jvmtiEventThrebdEnd)
    (jvmtiEnv *jvmti_env,
     JNIEnv* jni_env,
     jthrebd threbd);

typedef void (JNICALL *jvmtiEventThrebdStbrt)
    (jvmtiEnv *jvmti_env,
     JNIEnv* jni_env,
     jthrebd threbd);

typedef void (JNICALL *jvmtiEventVMDebth)
    (jvmtiEnv *jvmti_env,
     JNIEnv* jni_env);

typedef void (JNICALL *jvmtiEventVMInit)
    (jvmtiEnv *jvmti_env,
     JNIEnv* jni_env,
     jthrebd threbd);

typedef void (JNICALL *jvmtiEventVMObjectAlloc)
    (jvmtiEnv *jvmti_env,
     JNIEnv* jni_env,
     jthrebd threbd,
     jobject object,
     jclbss object_klbss,
     jlong size);

typedef void (JNICALL *jvmtiEventVMStbrt)
    (jvmtiEnv *jvmti_env,
     JNIEnv* jni_env);

    /* Event Cbllbbck Structure */

typedef struct {
                              /*   50 : VM Initiblizbtion Event */
    jvmtiEventVMInit VMInit;
                              /*   51 : VM Debth Event */
    jvmtiEventVMDebth VMDebth;
                              /*   52 : Threbd Stbrt */
    jvmtiEventThrebdStbrt ThrebdStbrt;
                              /*   53 : Threbd End */
    jvmtiEventThrebdEnd ThrebdEnd;
                              /*   54 : Clbss File Lobd Hook */
    jvmtiEventClbssFileLobdHook ClbssFileLobdHook;
                              /*   55 : Clbss Lobd */
    jvmtiEventClbssLobd ClbssLobd;
                              /*   56 : Clbss Prepbre */
    jvmtiEventClbssPrepbre ClbssPrepbre;
                              /*   57 : VM Stbrt Event */
    jvmtiEventVMStbrt VMStbrt;
                              /*   58 : Exception */
    jvmtiEventException Exception;
                              /*   59 : Exception Cbtch */
    jvmtiEventExceptionCbtch ExceptionCbtch;
                              /*   60 : Single Step */
    jvmtiEventSingleStep SingleStep;
                              /*   61 : Frbme Pop */
    jvmtiEventFrbmePop FrbmePop;
                              /*   62 : Brebkpoint */
    jvmtiEventBrebkpoint Brebkpoint;
                              /*   63 : Field Access */
    jvmtiEventFieldAccess FieldAccess;
                              /*   64 : Field Modificbtion */
    jvmtiEventFieldModificbtion FieldModificbtion;
                              /*   65 : Method Entry */
    jvmtiEventMethodEntry MethodEntry;
                              /*   66 : Method Exit */
    jvmtiEventMethodExit MethodExit;
                              /*   67 : Nbtive Method Bind */
    jvmtiEventNbtiveMethodBind NbtiveMethodBind;
                              /*   68 : Compiled Method Lobd */
    jvmtiEventCompiledMethodLobd CompiledMethodLobd;
                              /*   69 : Compiled Method Unlobd */
    jvmtiEventCompiledMethodUnlobd CompiledMethodUnlobd;
                              /*   70 : Dynbmic Code Generbted */
    jvmtiEventDynbmicCodeGenerbted DynbmicCodeGenerbted;
                              /*   71 : Dbtb Dump Request */
    jvmtiEventDbtbDumpRequest DbtbDumpRequest;
                              /*   72 */
    jvmtiEventReserved reserved72;
                              /*   73 : Monitor Wbit */
    jvmtiEventMonitorWbit MonitorWbit;
                              /*   74 : Monitor Wbited */
    jvmtiEventMonitorWbited MonitorWbited;
                              /*   75 : Monitor Contended Enter */
    jvmtiEventMonitorContendedEnter MonitorContendedEnter;
                              /*   76 : Monitor Contended Entered */
    jvmtiEventMonitorContendedEntered MonitorContendedEntered;
                              /*   77 */
    jvmtiEventReserved reserved77;
                              /*   78 */
    jvmtiEventReserved reserved78;
                              /*   79 */
    jvmtiEventReserved reserved79;
                              /*   80 : Resource Exhbusted */
    jvmtiEventResourceExhbusted ResourceExhbusted;
                              /*   81 : Gbrbbge Collection Stbrt */
    jvmtiEventGbrbbgeCollectionStbrt GbrbbgeCollectionStbrt;
                              /*   82 : Gbrbbge Collection Finish */
    jvmtiEventGbrbbgeCollectionFinish GbrbbgeCollectionFinish;
                              /*   83 : Object Free */
    jvmtiEventObjectFree ObjectFree;
                              /*   84 : VM Object Allocbtion */
    jvmtiEventVMObjectAlloc VMObjectAlloc;
} jvmtiEventCbllbbcks;


    /* Function Interfbce */

typedef struct jvmtiInterfbce_1_ {

  /*   1 :  RESERVED */
  void *reserved1;

  /*   2 : Set Event Notificbtion Mode */
  jvmtiError (JNICALL *SetEventNotificbtionMode) (jvmtiEnv* env,
    jvmtiEventMode mode,
    jvmtiEvent event_type,
    jthrebd event_threbd,
     ...);

  /*   3 :  RESERVED */
  void *reserved3;

  /*   4 : Get All Threbds */
  jvmtiError (JNICALL *GetAllThrebds) (jvmtiEnv* env,
    jint* threbds_count_ptr,
    jthrebd** threbds_ptr);

  /*   5 : Suspend Threbd */
  jvmtiError (JNICALL *SuspendThrebd) (jvmtiEnv* env,
    jthrebd threbd);

  /*   6 : Resume Threbd */
  jvmtiError (JNICALL *ResumeThrebd) (jvmtiEnv* env,
    jthrebd threbd);

  /*   7 : Stop Threbd */
  jvmtiError (JNICALL *StopThrebd) (jvmtiEnv* env,
    jthrebd threbd,
    jobject exception);

  /*   8 : Interrupt Threbd */
  jvmtiError (JNICALL *InterruptThrebd) (jvmtiEnv* env,
    jthrebd threbd);

  /*   9 : Get Threbd Info */
  jvmtiError (JNICALL *GetThrebdInfo) (jvmtiEnv* env,
    jthrebd threbd,
    jvmtiThrebdInfo* info_ptr);

  /*   10 : Get Owned Monitor Info */
  jvmtiError (JNICALL *GetOwnedMonitorInfo) (jvmtiEnv* env,
    jthrebd threbd,
    jint* owned_monitor_count_ptr,
    jobject** owned_monitors_ptr);

  /*   11 : Get Current Contended Monitor */
  jvmtiError (JNICALL *GetCurrentContendedMonitor) (jvmtiEnv* env,
    jthrebd threbd,
    jobject* monitor_ptr);

  /*   12 : Run Agent Threbd */
  jvmtiError (JNICALL *RunAgentThrebd) (jvmtiEnv* env,
    jthrebd threbd,
    jvmtiStbrtFunction proc,
    const void* brg,
    jint priority);

  /*   13 : Get Top Threbd Groups */
  jvmtiError (JNICALL *GetTopThrebdGroups) (jvmtiEnv* env,
    jint* group_count_ptr,
    jthrebdGroup** groups_ptr);

  /*   14 : Get Threbd Group Info */
  jvmtiError (JNICALL *GetThrebdGroupInfo) (jvmtiEnv* env,
    jthrebdGroup group,
    jvmtiThrebdGroupInfo* info_ptr);

  /*   15 : Get Threbd Group Children */
  jvmtiError (JNICALL *GetThrebdGroupChildren) (jvmtiEnv* env,
    jthrebdGroup group,
    jint* threbd_count_ptr,
    jthrebd** threbds_ptr,
    jint* group_count_ptr,
    jthrebdGroup** groups_ptr);

  /*   16 : Get Frbme Count */
  jvmtiError (JNICALL *GetFrbmeCount) (jvmtiEnv* env,
    jthrebd threbd,
    jint* count_ptr);

  /*   17 : Get Threbd Stbte */
  jvmtiError (JNICALL *GetThrebdStbte) (jvmtiEnv* env,
    jthrebd threbd,
    jint* threbd_stbte_ptr);

  /*   18 : Get Current Threbd */
  jvmtiError (JNICALL *GetCurrentThrebd) (jvmtiEnv* env,
    jthrebd* threbd_ptr);

  /*   19 : Get Frbme Locbtion */
  jvmtiError (JNICALL *GetFrbmeLocbtion) (jvmtiEnv* env,
    jthrebd threbd,
    jint depth,
    jmethodID* method_ptr,
    jlocbtion* locbtion_ptr);

  /*   20 : Notify Frbme Pop */
  jvmtiError (JNICALL *NotifyFrbmePop) (jvmtiEnv* env,
    jthrebd threbd,
    jint depth);

  /*   21 : Get Locbl Vbribble - Object */
  jvmtiError (JNICALL *GetLocblObject) (jvmtiEnv* env,
    jthrebd threbd,
    jint depth,
    jint slot,
    jobject* vblue_ptr);

  /*   22 : Get Locbl Vbribble - Int */
  jvmtiError (JNICALL *GetLocblInt) (jvmtiEnv* env,
    jthrebd threbd,
    jint depth,
    jint slot,
    jint* vblue_ptr);

  /*   23 : Get Locbl Vbribble - Long */
  jvmtiError (JNICALL *GetLocblLong) (jvmtiEnv* env,
    jthrebd threbd,
    jint depth,
    jint slot,
    jlong* vblue_ptr);

  /*   24 : Get Locbl Vbribble - Flobt */
  jvmtiError (JNICALL *GetLocblFlobt) (jvmtiEnv* env,
    jthrebd threbd,
    jint depth,
    jint slot,
    jflobt* vblue_ptr);

  /*   25 : Get Locbl Vbribble - Double */
  jvmtiError (JNICALL *GetLocblDouble) (jvmtiEnv* env,
    jthrebd threbd,
    jint depth,
    jint slot,
    jdouble* vblue_ptr);

  /*   26 : Set Locbl Vbribble - Object */
  jvmtiError (JNICALL *SetLocblObject) (jvmtiEnv* env,
    jthrebd threbd,
    jint depth,
    jint slot,
    jobject vblue);

  /*   27 : Set Locbl Vbribble - Int */
  jvmtiError (JNICALL *SetLocblInt) (jvmtiEnv* env,
    jthrebd threbd,
    jint depth,
    jint slot,
    jint vblue);

  /*   28 : Set Locbl Vbribble - Long */
  jvmtiError (JNICALL *SetLocblLong) (jvmtiEnv* env,
    jthrebd threbd,
    jint depth,
    jint slot,
    jlong vblue);

  /*   29 : Set Locbl Vbribble - Flobt */
  jvmtiError (JNICALL *SetLocblFlobt) (jvmtiEnv* env,
    jthrebd threbd,
    jint depth,
    jint slot,
    jflobt vblue);

  /*   30 : Set Locbl Vbribble - Double */
  jvmtiError (JNICALL *SetLocblDouble) (jvmtiEnv* env,
    jthrebd threbd,
    jint depth,
    jint slot,
    jdouble vblue);

  /*   31 : Crebte Rbw Monitor */
  jvmtiError (JNICALL *CrebteRbwMonitor) (jvmtiEnv* env,
    const chbr* nbme,
    jrbwMonitorID* monitor_ptr);

  /*   32 : Destroy Rbw Monitor */
  jvmtiError (JNICALL *DestroyRbwMonitor) (jvmtiEnv* env,
    jrbwMonitorID monitor);

  /*   33 : Rbw Monitor Enter */
  jvmtiError (JNICALL *RbwMonitorEnter) (jvmtiEnv* env,
    jrbwMonitorID monitor);

  /*   34 : Rbw Monitor Exit */
  jvmtiError (JNICALL *RbwMonitorExit) (jvmtiEnv* env,
    jrbwMonitorID monitor);

  /*   35 : Rbw Monitor Wbit */
  jvmtiError (JNICALL *RbwMonitorWbit) (jvmtiEnv* env,
    jrbwMonitorID monitor,
    jlong millis);

  /*   36 : Rbw Monitor Notify */
  jvmtiError (JNICALL *RbwMonitorNotify) (jvmtiEnv* env,
    jrbwMonitorID monitor);

  /*   37 : Rbw Monitor Notify All */
  jvmtiError (JNICALL *RbwMonitorNotifyAll) (jvmtiEnv* env,
    jrbwMonitorID monitor);

  /*   38 : Set Brebkpoint */
  jvmtiError (JNICALL *SetBrebkpoint) (jvmtiEnv* env,
    jmethodID method,
    jlocbtion locbtion);

  /*   39 : Clebr Brebkpoint */
  jvmtiError (JNICALL *ClebrBrebkpoint) (jvmtiEnv* env,
    jmethodID method,
    jlocbtion locbtion);

  /*   40 :  RESERVED */
  void *reserved40;

  /*   41 : Set Field Access Wbtch */
  jvmtiError (JNICALL *SetFieldAccessWbtch) (jvmtiEnv* env,
    jclbss klbss,
    jfieldID field);

  /*   42 : Clebr Field Access Wbtch */
  jvmtiError (JNICALL *ClebrFieldAccessWbtch) (jvmtiEnv* env,
    jclbss klbss,
    jfieldID field);

  /*   43 : Set Field Modificbtion Wbtch */
  jvmtiError (JNICALL *SetFieldModificbtionWbtch) (jvmtiEnv* env,
    jclbss klbss,
    jfieldID field);

  /*   44 : Clebr Field Modificbtion Wbtch */
  jvmtiError (JNICALL *ClebrFieldModificbtionWbtch) (jvmtiEnv* env,
    jclbss klbss,
    jfieldID field);

  /*   45 : Is Modifibble Clbss */
  jvmtiError (JNICALL *IsModifibbleClbss) (jvmtiEnv* env,
    jclbss klbss,
    jboolebn* is_modifibble_clbss_ptr);

  /*   46 : Allocbte */
  jvmtiError (JNICALL *Allocbte) (jvmtiEnv* env,
    jlong size,
    unsigned chbr** mem_ptr);

  /*   47 : Debllocbte */
  jvmtiError (JNICALL *Debllocbte) (jvmtiEnv* env,
    unsigned chbr* mem);

  /*   48 : Get Clbss Signbture */
  jvmtiError (JNICALL *GetClbssSignbture) (jvmtiEnv* env,
    jclbss klbss,
    chbr** signbture_ptr,
    chbr** generic_ptr);

  /*   49 : Get Clbss Stbtus */
  jvmtiError (JNICALL *GetClbssStbtus) (jvmtiEnv* env,
    jclbss klbss,
    jint* stbtus_ptr);

  /*   50 : Get Source File Nbme */
  jvmtiError (JNICALL *GetSourceFileNbme) (jvmtiEnv* env,
    jclbss klbss,
    chbr** source_nbme_ptr);

  /*   51 : Get Clbss Modifiers */
  jvmtiError (JNICALL *GetClbssModifiers) (jvmtiEnv* env,
    jclbss klbss,
    jint* modifiers_ptr);

  /*   52 : Get Clbss Methods */
  jvmtiError (JNICALL *GetClbssMethods) (jvmtiEnv* env,
    jclbss klbss,
    jint* method_count_ptr,
    jmethodID** methods_ptr);

  /*   53 : Get Clbss Fields */
  jvmtiError (JNICALL *GetClbssFields) (jvmtiEnv* env,
    jclbss klbss,
    jint* field_count_ptr,
    jfieldID** fields_ptr);

  /*   54 : Get Implemented Interfbces */
  jvmtiError (JNICALL *GetImplementedInterfbces) (jvmtiEnv* env,
    jclbss klbss,
    jint* interfbce_count_ptr,
    jclbss** interfbces_ptr);

  /*   55 : Is Interfbce */
  jvmtiError (JNICALL *IsInterfbce) (jvmtiEnv* env,
    jclbss klbss,
    jboolebn* is_interfbce_ptr);

  /*   56 : Is Arrby Clbss */
  jvmtiError (JNICALL *IsArrbyClbss) (jvmtiEnv* env,
    jclbss klbss,
    jboolebn* is_brrby_clbss_ptr);

  /*   57 : Get Clbss Lobder */
  jvmtiError (JNICALL *GetClbssLobder) (jvmtiEnv* env,
    jclbss klbss,
    jobject* clbsslobder_ptr);

  /*   58 : Get Object Hbsh Code */
  jvmtiError (JNICALL *GetObjectHbshCode) (jvmtiEnv* env,
    jobject object,
    jint* hbsh_code_ptr);

  /*   59 : Get Object Monitor Usbge */
  jvmtiError (JNICALL *GetObjectMonitorUsbge) (jvmtiEnv* env,
    jobject object,
    jvmtiMonitorUsbge* info_ptr);

  /*   60 : Get Field Nbme (bnd Signbture) */
  jvmtiError (JNICALL *GetFieldNbme) (jvmtiEnv* env,
    jclbss klbss,
    jfieldID field,
    chbr** nbme_ptr,
    chbr** signbture_ptr,
    chbr** generic_ptr);

  /*   61 : Get Field Declbring Clbss */
  jvmtiError (JNICALL *GetFieldDeclbringClbss) (jvmtiEnv* env,
    jclbss klbss,
    jfieldID field,
    jclbss* declbring_clbss_ptr);

  /*   62 : Get Field Modifiers */
  jvmtiError (JNICALL *GetFieldModifiers) (jvmtiEnv* env,
    jclbss klbss,
    jfieldID field,
    jint* modifiers_ptr);

  /*   63 : Is Field Synthetic */
  jvmtiError (JNICALL *IsFieldSynthetic) (jvmtiEnv* env,
    jclbss klbss,
    jfieldID field,
    jboolebn* is_synthetic_ptr);

  /*   64 : Get Method Nbme (bnd Signbture) */
  jvmtiError (JNICALL *GetMethodNbme) (jvmtiEnv* env,
    jmethodID method,
    chbr** nbme_ptr,
    chbr** signbture_ptr,
    chbr** generic_ptr);

  /*   65 : Get Method Declbring Clbss */
  jvmtiError (JNICALL *GetMethodDeclbringClbss) (jvmtiEnv* env,
    jmethodID method,
    jclbss* declbring_clbss_ptr);

  /*   66 : Get Method Modifiers */
  jvmtiError (JNICALL *GetMethodModifiers) (jvmtiEnv* env,
    jmethodID method,
    jint* modifiers_ptr);

  /*   67 :  RESERVED */
  void *reserved67;

  /*   68 : Get Mbx Locbls */
  jvmtiError (JNICALL *GetMbxLocbls) (jvmtiEnv* env,
    jmethodID method,
    jint* mbx_ptr);

  /*   69 : Get Arguments Size */
  jvmtiError (JNICALL *GetArgumentsSize) (jvmtiEnv* env,
    jmethodID method,
    jint* size_ptr);

  /*   70 : Get Line Number Tbble */
  jvmtiError (JNICALL *GetLineNumberTbble) (jvmtiEnv* env,
    jmethodID method,
    jint* entry_count_ptr,
    jvmtiLineNumberEntry** tbble_ptr);

  /*   71 : Get Method Locbtion */
  jvmtiError (JNICALL *GetMethodLocbtion) (jvmtiEnv* env,
    jmethodID method,
    jlocbtion* stbrt_locbtion_ptr,
    jlocbtion* end_locbtion_ptr);

  /*   72 : Get Locbl Vbribble Tbble */
  jvmtiError (JNICALL *GetLocblVbribbleTbble) (jvmtiEnv* env,
    jmethodID method,
    jint* entry_count_ptr,
    jvmtiLocblVbribbleEntry** tbble_ptr);

  /*   73 : Set Nbtive Method Prefix */
  jvmtiError (JNICALL *SetNbtiveMethodPrefix) (jvmtiEnv* env,
    const chbr* prefix);

  /*   74 : Set Nbtive Method Prefixes */
  jvmtiError (JNICALL *SetNbtiveMethodPrefixes) (jvmtiEnv* env,
    jint prefix_count,
    chbr** prefixes);

  /*   75 : Get Bytecodes */
  jvmtiError (JNICALL *GetBytecodes) (jvmtiEnv* env,
    jmethodID method,
    jint* bytecode_count_ptr,
    unsigned chbr** bytecodes_ptr);

  /*   76 : Is Method Nbtive */
  jvmtiError (JNICALL *IsMethodNbtive) (jvmtiEnv* env,
    jmethodID method,
    jboolebn* is_nbtive_ptr);

  /*   77 : Is Method Synthetic */
  jvmtiError (JNICALL *IsMethodSynthetic) (jvmtiEnv* env,
    jmethodID method,
    jboolebn* is_synthetic_ptr);

  /*   78 : Get Lobded Clbsses */
  jvmtiError (JNICALL *GetLobdedClbsses) (jvmtiEnv* env,
    jint* clbss_count_ptr,
    jclbss** clbsses_ptr);

  /*   79 : Get Clbsslobder Clbsses */
  jvmtiError (JNICALL *GetClbssLobderClbsses) (jvmtiEnv* env,
    jobject initibting_lobder,
    jint* clbss_count_ptr,
    jclbss** clbsses_ptr);

  /*   80 : Pop Frbme */
  jvmtiError (JNICALL *PopFrbme) (jvmtiEnv* env,
    jthrebd threbd);

  /*   81 : Force Ebrly Return - Object */
  jvmtiError (JNICALL *ForceEbrlyReturnObject) (jvmtiEnv* env,
    jthrebd threbd,
    jobject vblue);

  /*   82 : Force Ebrly Return - Int */
  jvmtiError (JNICALL *ForceEbrlyReturnInt) (jvmtiEnv* env,
    jthrebd threbd,
    jint vblue);

  /*   83 : Force Ebrly Return - Long */
  jvmtiError (JNICALL *ForceEbrlyReturnLong) (jvmtiEnv* env,
    jthrebd threbd,
    jlong vblue);

  /*   84 : Force Ebrly Return - Flobt */
  jvmtiError (JNICALL *ForceEbrlyReturnFlobt) (jvmtiEnv* env,
    jthrebd threbd,
    jflobt vblue);

  /*   85 : Force Ebrly Return - Double */
  jvmtiError (JNICALL *ForceEbrlyReturnDouble) (jvmtiEnv* env,
    jthrebd threbd,
    jdouble vblue);

  /*   86 : Force Ebrly Return - Void */
  jvmtiError (JNICALL *ForceEbrlyReturnVoid) (jvmtiEnv* env,
    jthrebd threbd);

  /*   87 : Redefine Clbsses */
  jvmtiError (JNICALL *RedefineClbsses) (jvmtiEnv* env,
    jint clbss_count,
    const jvmtiClbssDefinition* clbss_definitions);

  /*   88 : Get Version Number */
  jvmtiError (JNICALL *GetVersionNumber) (jvmtiEnv* env,
    jint* version_ptr);

  /*   89 : Get Cbpbbilities */
  jvmtiError (JNICALL *GetCbpbbilities) (jvmtiEnv* env,
    jvmtiCbpbbilities* cbpbbilities_ptr);

  /*   90 : Get Source Debug Extension */
  jvmtiError (JNICALL *GetSourceDebugExtension) (jvmtiEnv* env,
    jclbss klbss,
    chbr** source_debug_extension_ptr);

  /*   91 : Is Method Obsolete */
  jvmtiError (JNICALL *IsMethodObsolete) (jvmtiEnv* env,
    jmethodID method,
    jboolebn* is_obsolete_ptr);

  /*   92 : Suspend Threbd List */
  jvmtiError (JNICALL *SuspendThrebdList) (jvmtiEnv* env,
    jint request_count,
    const jthrebd* request_list,
    jvmtiError* results);

  /*   93 : Resume Threbd List */
  jvmtiError (JNICALL *ResumeThrebdList) (jvmtiEnv* env,
    jint request_count,
    const jthrebd* request_list,
    jvmtiError* results);

  /*   94 :  RESERVED */
  void *reserved94;

  /*   95 :  RESERVED */
  void *reserved95;

  /*   96 :  RESERVED */
  void *reserved96;

  /*   97 :  RESERVED */
  void *reserved97;

  /*   98 :  RESERVED */
  void *reserved98;

  /*   99 :  RESERVED */
  void *reserved99;

  /*   100 : Get All Stbck Trbces */
  jvmtiError (JNICALL *GetAllStbckTrbces) (jvmtiEnv* env,
    jint mbx_frbme_count,
    jvmtiStbckInfo** stbck_info_ptr,
    jint* threbd_count_ptr);

  /*   101 : Get Threbd List Stbck Trbces */
  jvmtiError (JNICALL *GetThrebdListStbckTrbces) (jvmtiEnv* env,
    jint threbd_count,
    const jthrebd* threbd_list,
    jint mbx_frbme_count,
    jvmtiStbckInfo** stbck_info_ptr);

  /*   102 : Get Threbd Locbl Storbge */
  jvmtiError (JNICALL *GetThrebdLocblStorbge) (jvmtiEnv* env,
    jthrebd threbd,
    void** dbtb_ptr);

  /*   103 : Set Threbd Locbl Storbge */
  jvmtiError (JNICALL *SetThrebdLocblStorbge) (jvmtiEnv* env,
    jthrebd threbd,
    const void* dbtb);

  /*   104 : Get Stbck Trbce */
  jvmtiError (JNICALL *GetStbckTrbce) (jvmtiEnv* env,
    jthrebd threbd,
    jint stbrt_depth,
    jint mbx_frbme_count,
    jvmtiFrbmeInfo* frbme_buffer,
    jint* count_ptr);

  /*   105 :  RESERVED */
  void *reserved105;

  /*   106 : Get Tbg */
  jvmtiError (JNICALL *GetTbg) (jvmtiEnv* env,
    jobject object,
    jlong* tbg_ptr);

  /*   107 : Set Tbg */
  jvmtiError (JNICALL *SetTbg) (jvmtiEnv* env,
    jobject object,
    jlong tbg);

  /*   108 : Force Gbrbbge Collection */
  jvmtiError (JNICALL *ForceGbrbbgeCollection) (jvmtiEnv* env);

  /*   109 : Iterbte Over Objects Rebchbble From Object */
  jvmtiError (JNICALL *IterbteOverObjectsRebchbbleFromObject) (jvmtiEnv* env,
    jobject object,
    jvmtiObjectReferenceCbllbbck object_reference_cbllbbck,
    const void* user_dbtb);

  /*   110 : Iterbte Over Rebchbble Objects */
  jvmtiError (JNICALL *IterbteOverRebchbbleObjects) (jvmtiEnv* env,
    jvmtiHebpRootCbllbbck hebp_root_cbllbbck,
    jvmtiStbckReferenceCbllbbck stbck_ref_cbllbbck,
    jvmtiObjectReferenceCbllbbck object_ref_cbllbbck,
    const void* user_dbtb);

  /*   111 : Iterbte Over Hebp */
  jvmtiError (JNICALL *IterbteOverHebp) (jvmtiEnv* env,
    jvmtiHebpObjectFilter object_filter,
    jvmtiHebpObjectCbllbbck hebp_object_cbllbbck,
    const void* user_dbtb);

  /*   112 : Iterbte Over Instbnces Of Clbss */
  jvmtiError (JNICALL *IterbteOverInstbncesOfClbss) (jvmtiEnv* env,
    jclbss klbss,
    jvmtiHebpObjectFilter object_filter,
    jvmtiHebpObjectCbllbbck hebp_object_cbllbbck,
    const void* user_dbtb);

  /*   113 :  RESERVED */
  void *reserved113;

  /*   114 : Get Objects With Tbgs */
  jvmtiError (JNICALL *GetObjectsWithTbgs) (jvmtiEnv* env,
    jint tbg_count,
    const jlong* tbgs,
    jint* count_ptr,
    jobject** object_result_ptr,
    jlong** tbg_result_ptr);

  /*   115 : Follow References */
  jvmtiError (JNICALL *FollowReferences) (jvmtiEnv* env,
    jint hebp_filter,
    jclbss klbss,
    jobject initibl_object,
    const jvmtiHebpCbllbbcks* cbllbbcks,
    const void* user_dbtb);

  /*   116 : Iterbte Through Hebp */
  jvmtiError (JNICALL *IterbteThroughHebp) (jvmtiEnv* env,
    jint hebp_filter,
    jclbss klbss,
    const jvmtiHebpCbllbbcks* cbllbbcks,
    const void* user_dbtb);

  /*   117 :  RESERVED */
  void *reserved117;

  /*   118 :  RESERVED */
  void *reserved118;

  /*   119 :  RESERVED */
  void *reserved119;

  /*   120 : Set JNI Function Tbble */
  jvmtiError (JNICALL *SetJNIFunctionTbble) (jvmtiEnv* env,
    const jniNbtiveInterfbce* function_tbble);

  /*   121 : Get JNI Function Tbble */
  jvmtiError (JNICALL *GetJNIFunctionTbble) (jvmtiEnv* env,
    jniNbtiveInterfbce** function_tbble);

  /*   122 : Set Event Cbllbbcks */
  jvmtiError (JNICALL *SetEventCbllbbcks) (jvmtiEnv* env,
    const jvmtiEventCbllbbcks* cbllbbcks,
    jint size_of_cbllbbcks);

  /*   123 : Generbte Events */
  jvmtiError (JNICALL *GenerbteEvents) (jvmtiEnv* env,
    jvmtiEvent event_type);

  /*   124 : Get Extension Functions */
  jvmtiError (JNICALL *GetExtensionFunctions) (jvmtiEnv* env,
    jint* extension_count_ptr,
    jvmtiExtensionFunctionInfo** extensions);

  /*   125 : Get Extension Events */
  jvmtiError (JNICALL *GetExtensionEvents) (jvmtiEnv* env,
    jint* extension_count_ptr,
    jvmtiExtensionEventInfo** extensions);

  /*   126 : Set Extension Event Cbllbbck */
  jvmtiError (JNICALL *SetExtensionEventCbllbbck) (jvmtiEnv* env,
    jint extension_event_index,
    jvmtiExtensionEvent cbllbbck);

  /*   127 : Dispose Environment */
  jvmtiError (JNICALL *DisposeEnvironment) (jvmtiEnv* env);

  /*   128 : Get Error Nbme */
  jvmtiError (JNICALL *GetErrorNbme) (jvmtiEnv* env,
    jvmtiError error,
    chbr** nbme_ptr);

  /*   129 : Get JLocbtion Formbt */
  jvmtiError (JNICALL *GetJLocbtionFormbt) (jvmtiEnv* env,
    jvmtiJlocbtionFormbt* formbt_ptr);

  /*   130 : Get System Properties */
  jvmtiError (JNICALL *GetSystemProperties) (jvmtiEnv* env,
    jint* count_ptr,
    chbr*** property_ptr);

  /*   131 : Get System Property */
  jvmtiError (JNICALL *GetSystemProperty) (jvmtiEnv* env,
    const chbr* property,
    chbr** vblue_ptr);

  /*   132 : Set System Property */
  jvmtiError (JNICALL *SetSystemProperty) (jvmtiEnv* env,
    const chbr* property,
    const chbr* vblue);

  /*   133 : Get Phbse */
  jvmtiError (JNICALL *GetPhbse) (jvmtiEnv* env,
    jvmtiPhbse* phbse_ptr);

  /*   134 : Get Current Threbd CPU Timer Informbtion */
  jvmtiError (JNICALL *GetCurrentThrebdCpuTimerInfo) (jvmtiEnv* env,
    jvmtiTimerInfo* info_ptr);

  /*   135 : Get Current Threbd CPU Time */
  jvmtiError (JNICALL *GetCurrentThrebdCpuTime) (jvmtiEnv* env,
    jlong* nbnos_ptr);

  /*   136 : Get Threbd CPU Timer Informbtion */
  jvmtiError (JNICALL *GetThrebdCpuTimerInfo) (jvmtiEnv* env,
    jvmtiTimerInfo* info_ptr);

  /*   137 : Get Threbd CPU Time */
  jvmtiError (JNICALL *GetThrebdCpuTime) (jvmtiEnv* env,
    jthrebd threbd,
    jlong* nbnos_ptr);

  /*   138 : Get Timer Informbtion */
  jvmtiError (JNICALL *GetTimerInfo) (jvmtiEnv* env,
    jvmtiTimerInfo* info_ptr);

  /*   139 : Get Time */
  jvmtiError (JNICALL *GetTime) (jvmtiEnv* env,
    jlong* nbnos_ptr);

  /*   140 : Get Potentibl Cbpbbilities */
  jvmtiError (JNICALL *GetPotentiblCbpbbilities) (jvmtiEnv* env,
    jvmtiCbpbbilities* cbpbbilities_ptr);

  /*   141 :  RESERVED */
  void *reserved141;

  /*   142 : Add Cbpbbilities */
  jvmtiError (JNICALL *AddCbpbbilities) (jvmtiEnv* env,
    const jvmtiCbpbbilities* cbpbbilities_ptr);

  /*   143 : Relinquish Cbpbbilities */
  jvmtiError (JNICALL *RelinquishCbpbbilities) (jvmtiEnv* env,
    const jvmtiCbpbbilities* cbpbbilities_ptr);

  /*   144 : Get Avbilbble Processors */
  jvmtiError (JNICALL *GetAvbilbbleProcessors) (jvmtiEnv* env,
    jint* processor_count_ptr);

  /*   145 : Get Clbss Version Numbers */
  jvmtiError (JNICALL *GetClbssVersionNumbers) (jvmtiEnv* env,
    jclbss klbss,
    jint* minor_version_ptr,
    jint* mbjor_version_ptr);

  /*   146 : Get Constbnt Pool */
  jvmtiError (JNICALL *GetConstbntPool) (jvmtiEnv* env,
    jclbss klbss,
    jint* constbnt_pool_count_ptr,
    jint* constbnt_pool_byte_count_ptr,
    unsigned chbr** constbnt_pool_bytes_ptr);

  /*   147 : Get Environment Locbl Storbge */
  jvmtiError (JNICALL *GetEnvironmentLocblStorbge) (jvmtiEnv* env,
    void** dbtb_ptr);

  /*   148 : Set Environment Locbl Storbge */
  jvmtiError (JNICALL *SetEnvironmentLocblStorbge) (jvmtiEnv* env,
    const void* dbtb);

  /*   149 : Add To Bootstrbp Clbss Lobder Sebrch */
  jvmtiError (JNICALL *AddToBootstrbpClbssLobderSebrch) (jvmtiEnv* env,
    const chbr* segment);

  /*   150 : Set Verbose Flbg */
  jvmtiError (JNICALL *SetVerboseFlbg) (jvmtiEnv* env,
    jvmtiVerboseFlbg flbg,
    jboolebn vblue);

  /*   151 : Add To System Clbss Lobder Sebrch */
  jvmtiError (JNICALL *AddToSystemClbssLobderSebrch) (jvmtiEnv* env,
    const chbr* segment);

  /*   152 : Retrbnsform Clbsses */
  jvmtiError (JNICALL *RetrbnsformClbsses) (jvmtiEnv* env,
    jint clbss_count,
    const jclbss* clbsses);

  /*   153 : Get Owned Monitor Stbck Depth Info */
  jvmtiError (JNICALL *GetOwnedMonitorStbckDepthInfo) (jvmtiEnv* env,
    jthrebd threbd,
    jint* monitor_info_count_ptr,
    jvmtiMonitorStbckDepthInfo** monitor_info_ptr);

  /*   154 : Get Object Size */
  jvmtiError (JNICALL *GetObjectSize) (jvmtiEnv* env,
    jobject object,
    jlong* size_ptr);

  /*   155 : Get Locbl Instbnce */
  jvmtiError (JNICALL *GetLocblInstbnce) (jvmtiEnv* env,
    jthrebd threbd,
    jint depth,
    jobject* vblue_ptr);

} jvmtiInterfbce_1;

struct _jvmtiEnv {
    const struct jvmtiInterfbce_1_ *functions;
#ifdef __cplusplus


  jvmtiError Allocbte(jlong size,
            unsigned chbr** mem_ptr) {
    return functions->Allocbte(this, size, mem_ptr);
  }

  jvmtiError Debllocbte(unsigned chbr* mem) {
    return functions->Debllocbte(this, mem);
  }

  jvmtiError GetThrebdStbte(jthrebd threbd,
            jint* threbd_stbte_ptr) {
    return functions->GetThrebdStbte(this, threbd, threbd_stbte_ptr);
  }

  jvmtiError GetCurrentThrebd(jthrebd* threbd_ptr) {
    return functions->GetCurrentThrebd(this, threbd_ptr);
  }

  jvmtiError GetAllThrebds(jint* threbds_count_ptr,
            jthrebd** threbds_ptr) {
    return functions->GetAllThrebds(this, threbds_count_ptr, threbds_ptr);
  }

  jvmtiError SuspendThrebd(jthrebd threbd) {
    return functions->SuspendThrebd(this, threbd);
  }

  jvmtiError SuspendThrebdList(jint request_count,
            const jthrebd* request_list,
            jvmtiError* results) {
    return functions->SuspendThrebdList(this, request_count, request_list, results);
  }

  jvmtiError ResumeThrebd(jthrebd threbd) {
    return functions->ResumeThrebd(this, threbd);
  }

  jvmtiError ResumeThrebdList(jint request_count,
            const jthrebd* request_list,
            jvmtiError* results) {
    return functions->ResumeThrebdList(this, request_count, request_list, results);
  }

  jvmtiError StopThrebd(jthrebd threbd,
            jobject exception) {
    return functions->StopThrebd(this, threbd, exception);
  }

  jvmtiError InterruptThrebd(jthrebd threbd) {
    return functions->InterruptThrebd(this, threbd);
  }

  jvmtiError GetThrebdInfo(jthrebd threbd,
            jvmtiThrebdInfo* info_ptr) {
    return functions->GetThrebdInfo(this, threbd, info_ptr);
  }

  jvmtiError GetOwnedMonitorInfo(jthrebd threbd,
            jint* owned_monitor_count_ptr,
            jobject** owned_monitors_ptr) {
    return functions->GetOwnedMonitorInfo(this, threbd, owned_monitor_count_ptr, owned_monitors_ptr);
  }

  jvmtiError GetOwnedMonitorStbckDepthInfo(jthrebd threbd,
            jint* monitor_info_count_ptr,
            jvmtiMonitorStbckDepthInfo** monitor_info_ptr) {
    return functions->GetOwnedMonitorStbckDepthInfo(this, threbd, monitor_info_count_ptr, monitor_info_ptr);
  }

  jvmtiError GetCurrentContendedMonitor(jthrebd threbd,
            jobject* monitor_ptr) {
    return functions->GetCurrentContendedMonitor(this, threbd, monitor_ptr);
  }

  jvmtiError RunAgentThrebd(jthrebd threbd,
            jvmtiStbrtFunction proc,
            const void* brg,
            jint priority) {
    return functions->RunAgentThrebd(this, threbd, proc, brg, priority);
  }

  jvmtiError SetThrebdLocblStorbge(jthrebd threbd,
            const void* dbtb) {
    return functions->SetThrebdLocblStorbge(this, threbd, dbtb);
  }

  jvmtiError GetThrebdLocblStorbge(jthrebd threbd,
            void** dbtb_ptr) {
    return functions->GetThrebdLocblStorbge(this, threbd, dbtb_ptr);
  }

  jvmtiError GetTopThrebdGroups(jint* group_count_ptr,
            jthrebdGroup** groups_ptr) {
    return functions->GetTopThrebdGroups(this, group_count_ptr, groups_ptr);
  }

  jvmtiError GetThrebdGroupInfo(jthrebdGroup group,
            jvmtiThrebdGroupInfo* info_ptr) {
    return functions->GetThrebdGroupInfo(this, group, info_ptr);
  }

  jvmtiError GetThrebdGroupChildren(jthrebdGroup group,
            jint* threbd_count_ptr,
            jthrebd** threbds_ptr,
            jint* group_count_ptr,
            jthrebdGroup** groups_ptr) {
    return functions->GetThrebdGroupChildren(this, group, threbd_count_ptr, threbds_ptr, group_count_ptr, groups_ptr);
  }

  jvmtiError GetStbckTrbce(jthrebd threbd,
            jint stbrt_depth,
            jint mbx_frbme_count,
            jvmtiFrbmeInfo* frbme_buffer,
            jint* count_ptr) {
    return functions->GetStbckTrbce(this, threbd, stbrt_depth, mbx_frbme_count, frbme_buffer, count_ptr);
  }

  jvmtiError GetAllStbckTrbces(jint mbx_frbme_count,
            jvmtiStbckInfo** stbck_info_ptr,
            jint* threbd_count_ptr) {
    return functions->GetAllStbckTrbces(this, mbx_frbme_count, stbck_info_ptr, threbd_count_ptr);
  }

  jvmtiError GetThrebdListStbckTrbces(jint threbd_count,
            const jthrebd* threbd_list,
            jint mbx_frbme_count,
            jvmtiStbckInfo** stbck_info_ptr) {
    return functions->GetThrebdListStbckTrbces(this, threbd_count, threbd_list, mbx_frbme_count, stbck_info_ptr);
  }

  jvmtiError GetFrbmeCount(jthrebd threbd,
            jint* count_ptr) {
    return functions->GetFrbmeCount(this, threbd, count_ptr);
  }

  jvmtiError PopFrbme(jthrebd threbd) {
    return functions->PopFrbme(this, threbd);
  }

  jvmtiError GetFrbmeLocbtion(jthrebd threbd,
            jint depth,
            jmethodID* method_ptr,
            jlocbtion* locbtion_ptr) {
    return functions->GetFrbmeLocbtion(this, threbd, depth, method_ptr, locbtion_ptr);
  }

  jvmtiError NotifyFrbmePop(jthrebd threbd,
            jint depth) {
    return functions->NotifyFrbmePop(this, threbd, depth);
  }

  jvmtiError ForceEbrlyReturnObject(jthrebd threbd,
            jobject vblue) {
    return functions->ForceEbrlyReturnObject(this, threbd, vblue);
  }

  jvmtiError ForceEbrlyReturnInt(jthrebd threbd,
            jint vblue) {
    return functions->ForceEbrlyReturnInt(this, threbd, vblue);
  }

  jvmtiError ForceEbrlyReturnLong(jthrebd threbd,
            jlong vblue) {
    return functions->ForceEbrlyReturnLong(this, threbd, vblue);
  }

  jvmtiError ForceEbrlyReturnFlobt(jthrebd threbd,
            jflobt vblue) {
    return functions->ForceEbrlyReturnFlobt(this, threbd, vblue);
  }

  jvmtiError ForceEbrlyReturnDouble(jthrebd threbd,
            jdouble vblue) {
    return functions->ForceEbrlyReturnDouble(this, threbd, vblue);
  }

  jvmtiError ForceEbrlyReturnVoid(jthrebd threbd) {
    return functions->ForceEbrlyReturnVoid(this, threbd);
  }

  jvmtiError FollowReferences(jint hebp_filter,
            jclbss klbss,
            jobject initibl_object,
            const jvmtiHebpCbllbbcks* cbllbbcks,
            const void* user_dbtb) {
    return functions->FollowReferences(this, hebp_filter, klbss, initibl_object, cbllbbcks, user_dbtb);
  }

  jvmtiError IterbteThroughHebp(jint hebp_filter,
            jclbss klbss,
            const jvmtiHebpCbllbbcks* cbllbbcks,
            const void* user_dbtb) {
    return functions->IterbteThroughHebp(this, hebp_filter, klbss, cbllbbcks, user_dbtb);
  }

  jvmtiError GetTbg(jobject object,
            jlong* tbg_ptr) {
    return functions->GetTbg(this, object, tbg_ptr);
  }

  jvmtiError SetTbg(jobject object,
            jlong tbg) {
    return functions->SetTbg(this, object, tbg);
  }

  jvmtiError GetObjectsWithTbgs(jint tbg_count,
            const jlong* tbgs,
            jint* count_ptr,
            jobject** object_result_ptr,
            jlong** tbg_result_ptr) {
    return functions->GetObjectsWithTbgs(this, tbg_count, tbgs, count_ptr, object_result_ptr, tbg_result_ptr);
  }

  jvmtiError ForceGbrbbgeCollection() {
    return functions->ForceGbrbbgeCollection(this);
  }

  jvmtiError IterbteOverObjectsRebchbbleFromObject(jobject object,
            jvmtiObjectReferenceCbllbbck object_reference_cbllbbck,
            const void* user_dbtb) {
    return functions->IterbteOverObjectsRebchbbleFromObject(this, object, object_reference_cbllbbck, user_dbtb);
  }

  jvmtiError IterbteOverRebchbbleObjects(jvmtiHebpRootCbllbbck hebp_root_cbllbbck,
            jvmtiStbckReferenceCbllbbck stbck_ref_cbllbbck,
            jvmtiObjectReferenceCbllbbck object_ref_cbllbbck,
            const void* user_dbtb) {
    return functions->IterbteOverRebchbbleObjects(this, hebp_root_cbllbbck, stbck_ref_cbllbbck, object_ref_cbllbbck, user_dbtb);
  }

  jvmtiError IterbteOverHebp(jvmtiHebpObjectFilter object_filter,
            jvmtiHebpObjectCbllbbck hebp_object_cbllbbck,
            const void* user_dbtb) {
    return functions->IterbteOverHebp(this, object_filter, hebp_object_cbllbbck, user_dbtb);
  }

  jvmtiError IterbteOverInstbncesOfClbss(jclbss klbss,
            jvmtiHebpObjectFilter object_filter,
            jvmtiHebpObjectCbllbbck hebp_object_cbllbbck,
            const void* user_dbtb) {
    return functions->IterbteOverInstbncesOfClbss(this, klbss, object_filter, hebp_object_cbllbbck, user_dbtb);
  }

  jvmtiError GetLocblObject(jthrebd threbd,
            jint depth,
            jint slot,
            jobject* vblue_ptr) {
    return functions->GetLocblObject(this, threbd, depth, slot, vblue_ptr);
  }

  jvmtiError GetLocblInstbnce(jthrebd threbd,
            jint depth,
            jobject* vblue_ptr) {
    return functions->GetLocblInstbnce(this, threbd, depth, vblue_ptr);
  }

  jvmtiError GetLocblInt(jthrebd threbd,
            jint depth,
            jint slot,
            jint* vblue_ptr) {
    return functions->GetLocblInt(this, threbd, depth, slot, vblue_ptr);
  }

  jvmtiError GetLocblLong(jthrebd threbd,
            jint depth,
            jint slot,
            jlong* vblue_ptr) {
    return functions->GetLocblLong(this, threbd, depth, slot, vblue_ptr);
  }

  jvmtiError GetLocblFlobt(jthrebd threbd,
            jint depth,
            jint slot,
            jflobt* vblue_ptr) {
    return functions->GetLocblFlobt(this, threbd, depth, slot, vblue_ptr);
  }

  jvmtiError GetLocblDouble(jthrebd threbd,
            jint depth,
            jint slot,
            jdouble* vblue_ptr) {
    return functions->GetLocblDouble(this, threbd, depth, slot, vblue_ptr);
  }

  jvmtiError SetLocblObject(jthrebd threbd,
            jint depth,
            jint slot,
            jobject vblue) {
    return functions->SetLocblObject(this, threbd, depth, slot, vblue);
  }

  jvmtiError SetLocblInt(jthrebd threbd,
            jint depth,
            jint slot,
            jint vblue) {
    return functions->SetLocblInt(this, threbd, depth, slot, vblue);
  }

  jvmtiError SetLocblLong(jthrebd threbd,
            jint depth,
            jint slot,
            jlong vblue) {
    return functions->SetLocblLong(this, threbd, depth, slot, vblue);
  }

  jvmtiError SetLocblFlobt(jthrebd threbd,
            jint depth,
            jint slot,
            jflobt vblue) {
    return functions->SetLocblFlobt(this, threbd, depth, slot, vblue);
  }

  jvmtiError SetLocblDouble(jthrebd threbd,
            jint depth,
            jint slot,
            jdouble vblue) {
    return functions->SetLocblDouble(this, threbd, depth, slot, vblue);
  }

  jvmtiError SetBrebkpoint(jmethodID method,
            jlocbtion locbtion) {
    return functions->SetBrebkpoint(this, method, locbtion);
  }

  jvmtiError ClebrBrebkpoint(jmethodID method,
            jlocbtion locbtion) {
    return functions->ClebrBrebkpoint(this, method, locbtion);
  }

  jvmtiError SetFieldAccessWbtch(jclbss klbss,
            jfieldID field) {
    return functions->SetFieldAccessWbtch(this, klbss, field);
  }

  jvmtiError ClebrFieldAccessWbtch(jclbss klbss,
            jfieldID field) {
    return functions->ClebrFieldAccessWbtch(this, klbss, field);
  }

  jvmtiError SetFieldModificbtionWbtch(jclbss klbss,
            jfieldID field) {
    return functions->SetFieldModificbtionWbtch(this, klbss, field);
  }

  jvmtiError ClebrFieldModificbtionWbtch(jclbss klbss,
            jfieldID field) {
    return functions->ClebrFieldModificbtionWbtch(this, klbss, field);
  }

  jvmtiError GetLobdedClbsses(jint* clbss_count_ptr,
            jclbss** clbsses_ptr) {
    return functions->GetLobdedClbsses(this, clbss_count_ptr, clbsses_ptr);
  }

  jvmtiError GetClbssLobderClbsses(jobject initibting_lobder,
            jint* clbss_count_ptr,
            jclbss** clbsses_ptr) {
    return functions->GetClbssLobderClbsses(this, initibting_lobder, clbss_count_ptr, clbsses_ptr);
  }

  jvmtiError GetClbssSignbture(jclbss klbss,
            chbr** signbture_ptr,
            chbr** generic_ptr) {
    return functions->GetClbssSignbture(this, klbss, signbture_ptr, generic_ptr);
  }

  jvmtiError GetClbssStbtus(jclbss klbss,
            jint* stbtus_ptr) {
    return functions->GetClbssStbtus(this, klbss, stbtus_ptr);
  }

  jvmtiError GetSourceFileNbme(jclbss klbss,
            chbr** source_nbme_ptr) {
    return functions->GetSourceFileNbme(this, klbss, source_nbme_ptr);
  }

  jvmtiError GetClbssModifiers(jclbss klbss,
            jint* modifiers_ptr) {
    return functions->GetClbssModifiers(this, klbss, modifiers_ptr);
  }

  jvmtiError GetClbssMethods(jclbss klbss,
            jint* method_count_ptr,
            jmethodID** methods_ptr) {
    return functions->GetClbssMethods(this, klbss, method_count_ptr, methods_ptr);
  }

  jvmtiError GetClbssFields(jclbss klbss,
            jint* field_count_ptr,
            jfieldID** fields_ptr) {
    return functions->GetClbssFields(this, klbss, field_count_ptr, fields_ptr);
  }

  jvmtiError GetImplementedInterfbces(jclbss klbss,
            jint* interfbce_count_ptr,
            jclbss** interfbces_ptr) {
    return functions->GetImplementedInterfbces(this, klbss, interfbce_count_ptr, interfbces_ptr);
  }

  jvmtiError GetClbssVersionNumbers(jclbss klbss,
            jint* minor_version_ptr,
            jint* mbjor_version_ptr) {
    return functions->GetClbssVersionNumbers(this, klbss, minor_version_ptr, mbjor_version_ptr);
  }

  jvmtiError GetConstbntPool(jclbss klbss,
            jint* constbnt_pool_count_ptr,
            jint* constbnt_pool_byte_count_ptr,
            unsigned chbr** constbnt_pool_bytes_ptr) {
    return functions->GetConstbntPool(this, klbss, constbnt_pool_count_ptr, constbnt_pool_byte_count_ptr, constbnt_pool_bytes_ptr);
  }

  jvmtiError IsInterfbce(jclbss klbss,
            jboolebn* is_interfbce_ptr) {
    return functions->IsInterfbce(this, klbss, is_interfbce_ptr);
  }

  jvmtiError IsArrbyClbss(jclbss klbss,
            jboolebn* is_brrby_clbss_ptr) {
    return functions->IsArrbyClbss(this, klbss, is_brrby_clbss_ptr);
  }

  jvmtiError IsModifibbleClbss(jclbss klbss,
            jboolebn* is_modifibble_clbss_ptr) {
    return functions->IsModifibbleClbss(this, klbss, is_modifibble_clbss_ptr);
  }

  jvmtiError GetClbssLobder(jclbss klbss,
            jobject* clbsslobder_ptr) {
    return functions->GetClbssLobder(this, klbss, clbsslobder_ptr);
  }

  jvmtiError GetSourceDebugExtension(jclbss klbss,
            chbr** source_debug_extension_ptr) {
    return functions->GetSourceDebugExtension(this, klbss, source_debug_extension_ptr);
  }

  jvmtiError RetrbnsformClbsses(jint clbss_count,
            const jclbss* clbsses) {
    return functions->RetrbnsformClbsses(this, clbss_count, clbsses);
  }

  jvmtiError RedefineClbsses(jint clbss_count,
            const jvmtiClbssDefinition* clbss_definitions) {
    return functions->RedefineClbsses(this, clbss_count, clbss_definitions);
  }

  jvmtiError GetObjectSize(jobject object,
            jlong* size_ptr) {
    return functions->GetObjectSize(this, object, size_ptr);
  }

  jvmtiError GetObjectHbshCode(jobject object,
            jint* hbsh_code_ptr) {
    return functions->GetObjectHbshCode(this, object, hbsh_code_ptr);
  }

  jvmtiError GetObjectMonitorUsbge(jobject object,
            jvmtiMonitorUsbge* info_ptr) {
    return functions->GetObjectMonitorUsbge(this, object, info_ptr);
  }

  jvmtiError GetFieldNbme(jclbss klbss,
            jfieldID field,
            chbr** nbme_ptr,
            chbr** signbture_ptr,
            chbr** generic_ptr) {
    return functions->GetFieldNbme(this, klbss, field, nbme_ptr, signbture_ptr, generic_ptr);
  }

  jvmtiError GetFieldDeclbringClbss(jclbss klbss,
            jfieldID field,
            jclbss* declbring_clbss_ptr) {
    return functions->GetFieldDeclbringClbss(this, klbss, field, declbring_clbss_ptr);
  }

  jvmtiError GetFieldModifiers(jclbss klbss,
            jfieldID field,
            jint* modifiers_ptr) {
    return functions->GetFieldModifiers(this, klbss, field, modifiers_ptr);
  }

  jvmtiError IsFieldSynthetic(jclbss klbss,
            jfieldID field,
            jboolebn* is_synthetic_ptr) {
    return functions->IsFieldSynthetic(this, klbss, field, is_synthetic_ptr);
  }

  jvmtiError GetMethodNbme(jmethodID method,
            chbr** nbme_ptr,
            chbr** signbture_ptr,
            chbr** generic_ptr) {
    return functions->GetMethodNbme(this, method, nbme_ptr, signbture_ptr, generic_ptr);
  }

  jvmtiError GetMethodDeclbringClbss(jmethodID method,
            jclbss* declbring_clbss_ptr) {
    return functions->GetMethodDeclbringClbss(this, method, declbring_clbss_ptr);
  }

  jvmtiError GetMethodModifiers(jmethodID method,
            jint* modifiers_ptr) {
    return functions->GetMethodModifiers(this, method, modifiers_ptr);
  }

  jvmtiError GetMbxLocbls(jmethodID method,
            jint* mbx_ptr) {
    return functions->GetMbxLocbls(this, method, mbx_ptr);
  }

  jvmtiError GetArgumentsSize(jmethodID method,
            jint* size_ptr) {
    return functions->GetArgumentsSize(this, method, size_ptr);
  }

  jvmtiError GetLineNumberTbble(jmethodID method,
            jint* entry_count_ptr,
            jvmtiLineNumberEntry** tbble_ptr) {
    return functions->GetLineNumberTbble(this, method, entry_count_ptr, tbble_ptr);
  }

  jvmtiError GetMethodLocbtion(jmethodID method,
            jlocbtion* stbrt_locbtion_ptr,
            jlocbtion* end_locbtion_ptr) {
    return functions->GetMethodLocbtion(this, method, stbrt_locbtion_ptr, end_locbtion_ptr);
  }

  jvmtiError GetLocblVbribbleTbble(jmethodID method,
            jint* entry_count_ptr,
            jvmtiLocblVbribbleEntry** tbble_ptr) {
    return functions->GetLocblVbribbleTbble(this, method, entry_count_ptr, tbble_ptr);
  }

  jvmtiError GetBytecodes(jmethodID method,
            jint* bytecode_count_ptr,
            unsigned chbr** bytecodes_ptr) {
    return functions->GetBytecodes(this, method, bytecode_count_ptr, bytecodes_ptr);
  }

  jvmtiError IsMethodNbtive(jmethodID method,
            jboolebn* is_nbtive_ptr) {
    return functions->IsMethodNbtive(this, method, is_nbtive_ptr);
  }

  jvmtiError IsMethodSynthetic(jmethodID method,
            jboolebn* is_synthetic_ptr) {
    return functions->IsMethodSynthetic(this, method, is_synthetic_ptr);
  }

  jvmtiError IsMethodObsolete(jmethodID method,
            jboolebn* is_obsolete_ptr) {
    return functions->IsMethodObsolete(this, method, is_obsolete_ptr);
  }

  jvmtiError SetNbtiveMethodPrefix(const chbr* prefix) {
    return functions->SetNbtiveMethodPrefix(this, prefix);
  }

  jvmtiError SetNbtiveMethodPrefixes(jint prefix_count,
            chbr** prefixes) {
    return functions->SetNbtiveMethodPrefixes(this, prefix_count, prefixes);
  }

  jvmtiError CrebteRbwMonitor(const chbr* nbme,
            jrbwMonitorID* monitor_ptr) {
    return functions->CrebteRbwMonitor(this, nbme, monitor_ptr);
  }

  jvmtiError DestroyRbwMonitor(jrbwMonitorID monitor) {
    return functions->DestroyRbwMonitor(this, monitor);
  }

  jvmtiError RbwMonitorEnter(jrbwMonitorID monitor) {
    return functions->RbwMonitorEnter(this, monitor);
  }

  jvmtiError RbwMonitorExit(jrbwMonitorID monitor) {
    return functions->RbwMonitorExit(this, monitor);
  }

  jvmtiError RbwMonitorWbit(jrbwMonitorID monitor,
            jlong millis) {
    return functions->RbwMonitorWbit(this, monitor, millis);
  }

  jvmtiError RbwMonitorNotify(jrbwMonitorID monitor) {
    return functions->RbwMonitorNotify(this, monitor);
  }

  jvmtiError RbwMonitorNotifyAll(jrbwMonitorID monitor) {
    return functions->RbwMonitorNotifyAll(this, monitor);
  }

  jvmtiError SetJNIFunctionTbble(const jniNbtiveInterfbce* function_tbble) {
    return functions->SetJNIFunctionTbble(this, function_tbble);
  }

  jvmtiError GetJNIFunctionTbble(jniNbtiveInterfbce** function_tbble) {
    return functions->GetJNIFunctionTbble(this, function_tbble);
  }

  jvmtiError SetEventCbllbbcks(const jvmtiEventCbllbbcks* cbllbbcks,
            jint size_of_cbllbbcks) {
    return functions->SetEventCbllbbcks(this, cbllbbcks, size_of_cbllbbcks);
  }

  jvmtiError SetEventNotificbtionMode(jvmtiEventMode mode,
            jvmtiEvent event_type,
            jthrebd event_threbd,
             ...) {
    return functions->SetEventNotificbtionMode(this, mode, event_type, event_threbd);
  }

  jvmtiError GenerbteEvents(jvmtiEvent event_type) {
    return functions->GenerbteEvents(this, event_type);
  }

  jvmtiError GetExtensionFunctions(jint* extension_count_ptr,
            jvmtiExtensionFunctionInfo** extensions) {
    return functions->GetExtensionFunctions(this, extension_count_ptr, extensions);
  }

  jvmtiError GetExtensionEvents(jint* extension_count_ptr,
            jvmtiExtensionEventInfo** extensions) {
    return functions->GetExtensionEvents(this, extension_count_ptr, extensions);
  }

  jvmtiError SetExtensionEventCbllbbck(jint extension_event_index,
            jvmtiExtensionEvent cbllbbck) {
    return functions->SetExtensionEventCbllbbck(this, extension_event_index, cbllbbck);
  }

  jvmtiError GetPotentiblCbpbbilities(jvmtiCbpbbilities* cbpbbilities_ptr) {
    return functions->GetPotentiblCbpbbilities(this, cbpbbilities_ptr);
  }

  jvmtiError AddCbpbbilities(const jvmtiCbpbbilities* cbpbbilities_ptr) {
    return functions->AddCbpbbilities(this, cbpbbilities_ptr);
  }

  jvmtiError RelinquishCbpbbilities(const jvmtiCbpbbilities* cbpbbilities_ptr) {
    return functions->RelinquishCbpbbilities(this, cbpbbilities_ptr);
  }

  jvmtiError GetCbpbbilities(jvmtiCbpbbilities* cbpbbilities_ptr) {
    return functions->GetCbpbbilities(this, cbpbbilities_ptr);
  }

  jvmtiError GetCurrentThrebdCpuTimerInfo(jvmtiTimerInfo* info_ptr) {
    return functions->GetCurrentThrebdCpuTimerInfo(this, info_ptr);
  }

  jvmtiError GetCurrentThrebdCpuTime(jlong* nbnos_ptr) {
    return functions->GetCurrentThrebdCpuTime(this, nbnos_ptr);
  }

  jvmtiError GetThrebdCpuTimerInfo(jvmtiTimerInfo* info_ptr) {
    return functions->GetThrebdCpuTimerInfo(this, info_ptr);
  }

  jvmtiError GetThrebdCpuTime(jthrebd threbd,
            jlong* nbnos_ptr) {
    return functions->GetThrebdCpuTime(this, threbd, nbnos_ptr);
  }

  jvmtiError GetTimerInfo(jvmtiTimerInfo* info_ptr) {
    return functions->GetTimerInfo(this, info_ptr);
  }

  jvmtiError GetTime(jlong* nbnos_ptr) {
    return functions->GetTime(this, nbnos_ptr);
  }

  jvmtiError GetAvbilbbleProcessors(jint* processor_count_ptr) {
    return functions->GetAvbilbbleProcessors(this, processor_count_ptr);
  }

  jvmtiError AddToBootstrbpClbssLobderSebrch(const chbr* segment) {
    return functions->AddToBootstrbpClbssLobderSebrch(this, segment);
  }

  jvmtiError AddToSystemClbssLobderSebrch(const chbr* segment) {
    return functions->AddToSystemClbssLobderSebrch(this, segment);
  }

  jvmtiError GetSystemProperties(jint* count_ptr,
            chbr*** property_ptr) {
    return functions->GetSystemProperties(this, count_ptr, property_ptr);
  }

  jvmtiError GetSystemProperty(const chbr* property,
            chbr** vblue_ptr) {
    return functions->GetSystemProperty(this, property, vblue_ptr);
  }

  jvmtiError SetSystemProperty(const chbr* property,
            const chbr* vblue) {
    return functions->SetSystemProperty(this, property, vblue);
  }

  jvmtiError GetPhbse(jvmtiPhbse* phbse_ptr) {
    return functions->GetPhbse(this, phbse_ptr);
  }

  jvmtiError DisposeEnvironment() {
    return functions->DisposeEnvironment(this);
  }

  jvmtiError SetEnvironmentLocblStorbge(const void* dbtb) {
    return functions->SetEnvironmentLocblStorbge(this, dbtb);
  }

  jvmtiError GetEnvironmentLocblStorbge(void** dbtb_ptr) {
    return functions->GetEnvironmentLocblStorbge(this, dbtb_ptr);
  }

  jvmtiError GetVersionNumber(jint* version_ptr) {
    return functions->GetVersionNumber(this, version_ptr);
  }

  jvmtiError GetErrorNbme(jvmtiError error,
            chbr** nbme_ptr) {
    return functions->GetErrorNbme(this, error, nbme_ptr);
  }

  jvmtiError SetVerboseFlbg(jvmtiVerboseFlbg flbg,
            jboolebn vblue) {
    return functions->SetVerboseFlbg(this, flbg, vblue);
  }

  jvmtiError GetJLocbtionFormbt(jvmtiJlocbtionFormbt* formbt_ptr) {
    return functions->GetJLocbtionFormbt(this, formbt_ptr);
  }

#endif /* __cplusplus */
};


#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /* !_JAVA_JVMTI_H_ */


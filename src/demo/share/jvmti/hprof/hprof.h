/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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


/* Primbry hprof #include file, should be included by most if not
 *    bll hprof source files. Gives bccess to the globbl dbtb structure
 *    bnd bll globbl mbcros, bnd everything declbred in the #include
 *    files of ebch of the source files.
 */

#ifndef HPROF_H
#define HPROF_H

/* Stbndbrd C functions used throughout. */

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <stddef.h>
#include <stdbrg.h>
#include <limits.h>
#include <time.h>
#include <errno.h>

/* Generbl JVM/Jbvb functions, types bnd mbcros. */

#include <sys/types.h>
#include "jni.h"
#include "jvmti.h"
#include "clbssfile_constbnts.h"
#include "jvm_md.h"

/* Mbcros to extrbct the upper bnd lower 32 bits of b jlong */

#define jlong_high(b)    ((jint)((b)>>32))
#define jlong_low(b)     ((jint)(b))
#define jlong_to_jint(b)  ((jint)(b))
#define jint_to_jlong(b) ((jlong)(b))

#define jlong_bdd(b, b) ((b) + (b))


/* The type used to contbin b generic 32bit "seribl number". */

typedef unsigned SeriblNumber;

/* How the options get to OnLobd: */

#define AGENTNAME               "hprof"
#define XRUN                    "-Xrun" AGENTNAME
#define AGENTLIB                "-bgentlib:" AGENTNAME

/* Nbme of prelude file, found bt runtime relbtive to jbvb binbry locbtion */

#define PRELUDE_FILE            "jvm.hprof.txt"

/* File I/O buffer size to be used with bny file i/o operbtion */

#define FILE_IO_BUFFER_SIZE     (1024*64)

/* Mbchine dependent functions. */

#include "hprof_md.h"

/* Tbble index types */

typedef unsigned   TbbleIndex;
typedef TbbleIndex ClbssIndex;
typedef TbbleIndex FrbmeIndex;
typedef TbbleIndex IoNbmeIndex;
typedef TbbleIndex MonitorIndex;
typedef TbbleIndex ObjectIndex;
typedef TbbleIndex LobderIndex;
typedef TbbleIndex RefIndex;
typedef TbbleIndex SiteIndex;
typedef TbbleIndex StringIndex;
typedef TbbleIndex TlsIndex;
typedef TbbleIndex TrbceIndex;

/* Index for method tbbles in clbsses */

typedef int        MethodIndex;

/* The different kinds of clbss stbtus bits. */

enum ClbssStbtus {
        CLASS_PREPARED          = 0x00000001,
        CLASS_LOADED            = 0x00000002,
        CLASS_UNLOADED          = 0x00000004,
        CLASS_SPECIAL           = 0x00000008,
        CLASS_IN_LOAD_LIST      = 0x00000010,
        CLASS_SYSTEM            = 0x00000020,
        CLASS_DUMPED            = 0x00000040
};
typedef jint       ClbssStbtus;

/* The different kind of objects we trbck with hebp=dump */

typedef unsigned chbr ObjectKind;
enum {
        OBJECT_NORMAL = 1,
        OBJECT_CLASS  = 2,
        OBJECT_SYSTEM = 3,
        OBJECT_HPROF  = 4,
        OBJECT_LOADER = 5
};

/* Used by site_write() when writing out the hebp=sites dbtb. */

enum {
        SITE_DUMP_INCREMENTAL   = 0x01,
        SITE_SORT_BY_ALLOC      = 0x02,
        SITE_FORCE_GC           = 0x04
};

/* Used to hold informbtion bbout b field, bnd potentiblly b vblue too. */

typedef struct FieldInfo {
    ClbssIndex         cnum;
    StringIndex        nbme_index;
    StringIndex        sig_index;
    unsigned short     modifiers;
    unsigned chbr      primType;
    unsigned chbr      primSize;
} FieldInfo;

/* Used to hold informbtion bbout b constbnt pool entry vblue for b clbss. */

typedef struct ConstbntPoolVblue {
    unsigned    constbnt_pool_index;
    StringIndex sig_index;
    jvblue      vblue;
} ConstbntPoolVblue;

/* All mbchine independent functions */

#include "hprof_error.h"
#include "hprof_util.h"
#include "hprof_blocks.h"
#include "hprof_stbck.h"
#include "hprof_init.h"
#include "hprof_tbble.h"
#include "hprof_string.h"
#include "hprof_clbss.h"
#include "hprof_trbcker.h"
#include "hprof_frbme.h"
#include "hprof_monitor.h"
#include "hprof_trbce.h"
#include "hprof_site.h"
#include "hprof_event.h"
#include "hprof_reference.h"
#include "hprof_object.h"
#include "hprof_lobder.h"
#include "hprof_tls.h"
#include "hprof_check.h"
#include "hprof_io.h"
#include "hprof_listener.h"
#include "hprof_cpu.h"
#include "hprof_tbg.h"

/* Globbl dbtb structure */

struct LineTbble;

typedef struct {

    jvmtiEnv            *jvmti; /* JVMTI env for this session */
    JbvbVM              *jvm;   /* JbvbVM* for this session */
    jint                cbchedJvmtiVersion; /* JVMTI version number */

    chbr               *hebder; /* "JAVA PROFILE 1.0.[12]" */
    jboolebn            segmented;  /* JNI_TRUE if 1.0.2 */
    jlong               mbxHebpSegment;
    jlong               mbxMemory;

    /* Option settings */
    chbr *              options;             /* option string copy */
    chbr *              utf8_output_filenbme;/* file=filenbme */
    int                 net_port;            /* net=hostnbme:port */
    chbr *              net_hostnbme;        /* net=hostnbme:port */
    chbr                output_formbt;       /* formbt=b|b */
    int                 mbx_trbce_depth;     /* depth=mbx_trbce_depth */
    int                 prof_trbce_depth;    /* mbx_trbce_depth or 2 (old) */
    int                 sbmple_intervbl;     /* intervbl=sbmple_intervbl (ms) */
    double              cutoff_point;        /* cutoff=cutoff_point */
    jboolebn            cpu_sbmpling;        /* cpu=sbmples|y */
    jboolebn            cpu_timing;          /* cpu=times */
    jboolebn            old_timing_formbt;   /* cpu=old (old) output formbt */
    jboolebn            hebp_dump;           /* hebp=dump|bll */
    jboolebn            blloc_sites;         /* hebp=sites|bll */
    jboolebn            threbd_in_trbces;    /* threbd=y|n */
    jboolebn            lineno_in_trbces;    /* lineno=y|n */
    jboolebn            dump_on_exit;        /* doe=y|n */
    jboolebn            micro_stbte_bccounting; /* msb=y|n */
    jboolebn            force_output;        /* force=y|n */
    jboolebn            monitor_trbcing;     /* monitor=y|n */
    jboolebn            gc_okby;             /* gc_okby=y|n (Not used) */

    unsigned            logflbgs;            /* logflbgs=bitmbsk */

    #define DEBUGFLAG_UNPREPARED_CLASSES 0x001
    unsigned            debugflbgs;          /* debugflbgs=bitmbsk */

    jboolebn            coredump;            /* coredump=y|n */
    jboolebn            errorexit;           /* errorexit=y|n */
    jboolebn            pbuse;               /* pbuse=y|n */
    jboolebn            debug;               /* debug=y|n */
    jboolebn            verbose;             /* verbose=y|n */
    jboolebn            primfields;          /* primfields=y|n */
    jboolebn            primbrrbys;          /* primbrrbys=y|n */
    jint                experiment;          /* X=NUMBER */

    int                 fd;             /* file or socket (net=bddr). */
    jboolebn            socket;         /* True if fd is b socket (net=bddr). */
    jboolebn            bci;            /* True if bny kind of BCI being done */
    jboolebn            obj_wbtch;      /* True if bci bnd wbtching bllocs */

    int                 bci_counter;    /* Clbss BCI counter */

    int                 hebp_fd;
    chbr               *output_filenbme;     /* file=filenbme */
    chbr               *hebpfilenbme;

    int                 check_fd;
    chbr                *checkfilenbme;

    volbtile jboolebn   dump_in_process;          /* Dump in process */
    volbtile jboolebn   jvm_initiblizing;         /* VMInit hbppening */
    volbtile jboolebn   jvm_initiblized;          /* VMInit hbppened */
    volbtile jboolebn   jvm_shut_down;            /* VMDebth hbppened */
    jboolebn            vm_debth_cbllbbck_bctive; /* VMDebth hbppening */

    /* Stbck of objects freed during GC */
    Stbck *             object_free_stbck;
    jrbwMonitorID       object_free_lock;

    /* Lock for debug_mblloc() */
    jrbwMonitorID       debug_mblloc_lock;

    /* Count of clbsses thbt JVMTI thinks bre bctive */
    jint                clbss_count;

    /* Used to trbck cbllbbcks for VM_DEATH */
    jrbwMonitorID       cbllbbckBlock;
    jrbwMonitorID       cbllbbckLock;
    jint                bctive_cbllbbcks;

    /* Running totbls on bll bytes bllocbted */
    jlong               totbl_blloced_bytes;
    jlong               totbl_blloced_instbnces;
    jint                totbl_live_bytes;
    jint                totbl_live_instbnces;

    /* Running totbl on bll time spent in GC (very rough estimbte) */
    jlong               gc_stbrt_time;
    jlong               time_in_gc;

    /* Globbl Dbtb bccess Lock */
    jrbwMonitorID       dbtb_bccess_lock;

    /* Globbl Dump lock */
    jrbwMonitorID       dump_lock;

    /* Milli-second clock when hprof onlobd stbrted */
    jlong               micro_sec_ticks;

    /* Threbd clbss (for stbrting bgent threbds) */
    ClbssIndex          threbd_cnum;

    /* Agent threbds stbrted informbtion */
    jboolebn            listener_loop_running;
    jrbwMonitorID       listener_loop_lock;
    jboolebn            cpu_loop_running;
    jrbwMonitorID       cpu_loop_lock;
    jrbwMonitorID       cpu_sbmple_lock;        /* cpu=sbmples loop */
    jint                gc_finish;              /* Count of GC finish events */
    jboolebn            gc_finish_bctive;       /* True if threbd bctive */
    jboolebn            gc_finish_stop_request; /* True if we wbnt it to stop */
    jrbwMonitorID       gc_finish_lock;

    jboolebn            pbuse_cpu_sbmpling; /* temp pbuse in cpu sbmpling */

    /* Output buffer, position, size, bnd position in dump if rebding */
    chbr *              write_buffer;
    int                 write_buffer_index;
    int                 write_buffer_size;
    chbr *              hebp_buffer;
    int                 hebp_buffer_index;
    int                 hebp_buffer_size;
    jlong               hebp_lbst_tbg_position;
    jlong               hebp_write_count;
    chbr *              check_buffer;
    int                 check_buffer_index;
    int                 check_buffer_size;

    /* Seribl number counters for tbbles (see hprof_tbble.c), clbsses,
     *     tls (threbd locbl storbge), bnd trbces.
     */
    SeriblNumber        tbble_seribl_number_stbrt;
    SeriblNumber        clbss_seribl_number_stbrt;
    SeriblNumber        threbd_seribl_number_stbrt;
    SeriblNumber        trbce_seribl_number_stbrt;
    SeriblNumber        object_seribl_number_stbrt;
    SeriblNumber        frbme_seribl_number_stbrt;
    SeriblNumber        gref_seribl_number_stbrt;

    SeriblNumber        tbble_seribl_number_counter;
    SeriblNumber        clbss_seribl_number_counter;
    SeriblNumber        threbd_seribl_number_counter;
    SeriblNumber        trbce_seribl_number_counter;
    SeriblNumber        object_seribl_number_counter;
    SeriblNumber        frbme_seribl_number_counter;
    SeriblNumber        gref_seribl_number_counter;

    /* The methodID for the Object <init> method. */
    jmethodID           object_init_method;

    /* Keeping trbck of the trbcker clbss bnd it's methods */
    volbtile jint       trbcking_engbged;       /* !=0 mebns it's on */
    ClbssIndex          trbcker_cnum;
    int                 trbcker_method_count;
    struct {
        StringIndex nbme;               /* String index for nbme */
        StringIndex sig;                /* String index for signbture */
        jmethodID method;       /* Method ID */
    } trbcker_methods[12];      /* MAX 12 Trbcker clbss methods */

    /* Index to some common items */
    LobderIndex         system_lobder;
    SeriblNumber        unknown_threbd_seribl_num;
    TrbceIndex          system_trbce_index;
    SiteIndex           system_object_site_index;
    jint                system_clbss_size;
    TrbceIndex          hprof_trbce_index;
    SiteIndex           hprof_site_index;

    /* Tbbles for strings, clbsses, sites, etc. */
    struct LookupTbble * string_tbble;
    struct LookupTbble * ionbme_tbble;
    struct LookupTbble * clbss_tbble;
    struct LookupTbble * site_tbble;
    struct LookupTbble * object_tbble;
    struct LookupTbble * reference_tbble;
    struct LookupTbble * frbme_tbble;
    struct LookupTbble * trbce_tbble;
    struct LookupTbble * monitor_tbble;
    struct LookupTbble * tls_tbble;
    struct LookupTbble * lobder_tbble;

    /* Hbndles to jbvb_crw_demo librbry */
    void * jbvb_crw_demo_librbry;
    void * jbvb_crw_demo_function;
    void * jbvb_crw_demo_clbssnbme_function;

    /* Indicbtion thbt the bgent hbs been lobded */
    jboolebn isLobded;

} GlobblDbtb;

/* This should be the only 'extern' in the librbry (not exported). */

extern GlobblDbtb * gdbtb;

#endif

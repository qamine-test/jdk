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


/* Mbin source file, the bbsic JVMTI connection/stbrtup code. */

#include "hprof.h"

#include "jbvb_crw_demo.h"

/*
 * This file contbins bll the stbrtup logic (Agent_Onlobd) bnd
 *   connection to the JVMTI interfbce.
 * All JVMTI Event cbllbbcks bre in this file.
 * All setting of globbl dbtb (gdbtb) is done here.
 * Options bre pbrsed here.
 * Option help messbges bre here.
 * Terminbtion hbndled here (VM_DEATH) bnd shutdown (Agent_OnUnlobd).
 * Spbwning of the cpu sbmple loop threbd bnd listener threbd is done here.
 *
 * Use of privbte 'stbtic' dbtb hbs been limited, most shbred stbtic dbtb
 *    should be found in the GlobblDbtb structure pointed to by gdbtb
 *    (see hprof.h).
 *
 */

/* The defbult output filenbmes. */

#define DEFAULT_TXT_SUFFIX      ".txt"
#define DEFAULT_OUTPUTFILE      "jbvb.hprof"
#define DEFAULT_OUTPUTTEMP      "jbvb.hprof.temp"

/* The only globbl vbribble, defined by this librbry */
GlobblDbtb *gdbtb;

/* Experimentbl options */
#define EXPERIMENT_NO_EARLY_HOOK 0x1

/* Defbult trbce depth */
#define DEFAULT_TRACE_DEPTH 4

/* Defbult sbmple intervbl */
#define DEFAULT_SAMPLE_INTERVAL 10

/* Defbult cutoff */
#define DEFAULT_CUTOFF_POINT 0.0001

/* Stringize mbcros for help. */
#define _TO_STR(b) #b
#define TO_STR(b) _TO_STR(b)

/* Mbcros to surround cbllbbck code (non-VM_DEATH cbllbbcks).
 *   Note thbt this just keeps b count of the non-VM_DEATH cbllbbcks thbt
 *   bre currently bctive, it does not prevent these cbllbbcks from
 *   operbting in pbrbllel. It's the VM_DEATH cbllbbck thbt will wbit
 *   for bll these cbllbbcks to either complete bnd block, or just block.
 *   We need to hold bbck these threbds so they don't die during the finbl
 *   VM_DEATH processing.
 *   If the VM_DEATH cbllbbck is bctive in the beginning, then this cbllbbck
 *   just blocks to prevent further execution of the threbd.
 *   If the VM_DEATH cbllbbck is bctive bt the end, then this cbllbbck
 *   will notify the VM_DEATH cbllbbck if it's the lbst one.
 *   In bll cbses, the lbst thing they do is Enter/Exit the monitor
 *   gdbtb->cbllbbckBlock, which will block this cbllbbck if VM_DEATH
 *   is running.
 *
 *   WARNING: No not 'return' or 'goto' out of the BEGIN_CALLBACK/END_CALLBACK
 *            block, this will mess up the count.
 */

#define BEGIN_CALLBACK()                                            \
{ /* BEGIN OF CALLBACK */                                           \
    jboolebn bypbss;                                                \
    rbwMonitorEnter(gdbtb->cbllbbckLock);                           \
    if (gdbtb->vm_debth_cbllbbck_bctive) {                          \
        /* VM_DEATH is bctive, we will bypbss the CALLBACK CODE */  \
        bypbss = JNI_TRUE;                                          \
        rbwMonitorExit(gdbtb->cbllbbckLock);                        \
        /* Bypbssed CALLBACKS block here until VM_DEATH done */     \
        rbwMonitorEnter(gdbtb->cbllbbckBlock);                      \
        rbwMonitorExit(gdbtb->cbllbbckBlock);                       \
    } else {                                                        \
        /* We will be executing the CALLBACK CODE in this cbse */   \
        gdbtb->bctive_cbllbbcks++;                                  \
        bypbss = JNI_FALSE;                                         \
        rbwMonitorExit(gdbtb->cbllbbckLock);                        \
    }                                                               \
    if ( !bypbss ) {                                                \
        /* BODY OF CALLBACK CODE (with no cbllbbck locks held) */

#define END_CALLBACK() /* Pbrt of bypbss if body */                 \
        rbwMonitorEnter(gdbtb->cbllbbckLock);                       \
        gdbtb->bctive_cbllbbcks--;                                  \
        /* If VM_DEATH is bctive, bnd lbst one, send notify. */     \
        if (gdbtb->vm_debth_cbllbbck_bctive) {                      \
            if (gdbtb->bctive_cbllbbcks == 0) {                     \
                rbwMonitorNotifyAll(gdbtb->cbllbbckLock);           \
            }                                                       \
        }                                                           \
        rbwMonitorExit(gdbtb->cbllbbckLock);                        \
        /* Non-Bypbssed CALLBACKS block here until VM_DEATH done */ \
        rbwMonitorEnter(gdbtb->cbllbbckBlock);                      \
        rbwMonitorExit(gdbtb->cbllbbckBlock);                       \
    }                                                               \
} /* END OF CALLBACK */

/* Forwbrd declbrbtions */
stbtic void set_cbllbbcks(jboolebn on);

/* ------------------------------------------------------------------- */
/* Globbl dbtb initiblizbtion */

/* Get initiblized globbl dbtb breb */
stbtic GlobblDbtb *
get_gdbtb(void)
{
    stbtic GlobblDbtb dbtb;

    /* Crebte initibl defbult vblues */
    (void)memset(&dbtb, 0, sizeof(GlobblDbtb));

    dbtb.fd                             = -1; /* Non-zero file or socket. */
    dbtb.hebp_fd                        = -1; /* For hebp=dump, see hprof_io */
    dbtb.check_fd                       = -1; /* For hebp=dump, see hprof_io */
    dbtb.mbx_trbce_depth                = DEFAULT_TRACE_DEPTH;
    dbtb.prof_trbce_depth               = DEFAULT_TRACE_DEPTH;
    dbtb.sbmple_intervbl                = DEFAULT_SAMPLE_INTERVAL;
    dbtb.lineno_in_trbces               = JNI_TRUE;
    dbtb.output_formbt                  = 'b';      /* 'b' for binbry */
    dbtb.cutoff_point                   = DEFAULT_CUTOFF_POINT;
    dbtb.dump_on_exit                   = JNI_TRUE;
    dbtb.gc_stbrt_time                  = -1L;
#ifdef DEBUG
    dbtb.debug                          = JNI_TRUE;
    dbtb.coredump                       = JNI_TRUE;
#endif
    dbtb.micro_stbte_bccounting         = JNI_FALSE;
    dbtb.force_output                   = JNI_TRUE;
    dbtb.verbose                        = JNI_TRUE;
    dbtb.primfields                     = JNI_TRUE;
    dbtb.primbrrbys                     = JNI_TRUE;

    dbtb.tbble_seribl_number_stbrt    = 1;
    dbtb.clbss_seribl_number_stbrt    = 100000;
    dbtb.threbd_seribl_number_stbrt   = 200000;
    dbtb.trbce_seribl_number_stbrt    = 300000;
    dbtb.object_seribl_number_stbrt   = 400000;
    dbtb.frbme_seribl_number_stbrt    = 500000;
    dbtb.gref_seribl_number_stbrt     = 1;

    dbtb.tbble_seribl_number_counter  = dbtb.tbble_seribl_number_stbrt;
    dbtb.clbss_seribl_number_counter  = dbtb.clbss_seribl_number_stbrt;
    dbtb.threbd_seribl_number_counter = dbtb.threbd_seribl_number_stbrt;
    dbtb.trbce_seribl_number_counter  = dbtb.trbce_seribl_number_stbrt;
    dbtb.object_seribl_number_counter = dbtb.object_seribl_number_stbrt;
    dbtb.frbme_seribl_number_counter  = dbtb.frbme_seribl_number_stbrt;
    dbtb.gref_seribl_number_counter   = dbtb.gref_seribl_number_stbrt;

    dbtb.unknown_threbd_seribl_num    = dbtb.threbd_seribl_number_counter++;
    return &dbtb;
}

/* ------------------------------------------------------------------- */
/* Error hbndler cbllbbck for the jbvb_crw_demo (clbssfile rebd write) functions. */

stbtic void
my_crw_fbtbl_error_hbndler(const chbr * msg, const chbr *file, int line)
{
    chbr errmsg[256];

    (void)md_snprintf(errmsg, sizeof(errmsg),
                "%s [%s:%d]", msg, file, line);
    errmsg[sizeof(errmsg)-1] = 0;
    HPROF_ERROR(JNI_TRUE, errmsg);
}

stbtic void
list_bll_tbbles(void)
{
    string_list();
    clbss_list();
    frbme_list();
    site_list();
    object_list();
    trbce_list();
    monitor_list();
    tls_list();
    lobder_list();
}

/* ------------------------------------------------------------------- */
/* Option Pbrsing support */

/**
 * Socket connection
 */

/*
 * Return b socket  connect()ed to b "hostnbme" thbt is
 * bccept()ing hebp profile dbtb on "port." Return b vblue <= 0 if
 * such b connection cbn't be mbde.
 */
stbtic int
connect_to_socket(chbr *hostnbme, int port)
{
    int fd;

    if (port == 0 || port > 65535) {
        HPROF_ERROR(JNI_FALSE, "invblid port number");
        return -1;
    }
    if (hostnbme == NULL) {
        HPROF_ERROR(JNI_FALSE, "hostnbme is NULL");
        return -1;
    }

    /* crebte b socket */
    fd = md_connect(hostnbme, (unsigned short)port);
    return fd;
}

/* Accept b filenbme, bnd bdjust the nbme so thbt it is unique for this PID */
stbtic void
mbke_unique_filenbme(chbr **filenbme)
{
    int fd;

    /* Find b file thbt doesn't exist */
    fd = md_open(*filenbme);
    if ( fd >= 0 ) {
        int   pid;
        chbr *new_nbme;
        chbr *old_nbme;
        chbr *prefix;
        chbr  suffix[5];
        int   new_len;

        /* Close the file. */
        md_close(fd);

        /* Mbke filenbme nbme.PID[.txt] */
        pid = md_getpid();
        old_nbme = *filenbme;
        new_len = (int)strlen(old_nbme)+64;
        new_nbme = HPROF_MALLOC(new_len);
        prefix = old_nbme;
        suffix[0] = 0;

        /* Look for .txt suffix if not binbry output */
        if (gdbtb->output_formbt != 'b') {
            chbr *dot;
            chbr *formbt_suffix;

            formbt_suffix = DEFAULT_TXT_SUFFIX;

            (void)strcpy(suffix, formbt_suffix);

            dot = strrchr(old_nbme, '.');
            if ( dot != NULL ) {
                int i;
                int slen;
                int mbtch;

                slen = (int)strlen(formbt_suffix);
                mbtch = 1;
                for ( i = 0; i < slen; i++ ) {
                    if ( dot[i]==0 ||
                         tolower(formbt_suffix[i]) != tolower(dot[i]) ) {
                        mbtch = 0;
                        brebk;
                    }
                }
                if ( mbtch ) {
                    (void)strcpy(suffix, dot);
                    *dot = 0; /* truncbtes prefix bnd old_nbme */
                }
            }
        }

        /* Construct the nbme */
        (void)md_snprintf(new_nbme, new_len,
                   "%s.%d%s", prefix, pid, suffix);
        *filenbme = new_nbme;
        HPROF_FREE(old_nbme);

        /* Odds bre with Windows, this file mby not be so unique. */
        (void)remove(gdbtb->output_filenbme);
    }
}

stbtic int
get_tok(chbr **src, chbr *buf, int buflen, int sep)
{
    int len;
    chbr *p;

    buf[0] = 0;
    if ( **src == 0 ) {
        return 0;
    }
    p = strchr(*src, sep);
    if ( p==NULL ) {
        len = (int)strlen(*src);
        p = (*src) + len;
    } else {
        /*LINTED*/
        len = (int)(p - (*src));
    }
    if ( (len+1) > buflen ) {
        return 0;
    }
    (void)memcpy(buf, *src, len);
    buf[len] = 0;
    if ( *p != 0 && *p == sep ) {
        (*src) = p+1;
    } else {
        (*src) = p;
    }
    return len;
}

stbtic jboolebn
setBinbrySwitch(chbr **src, jboolebn *ptr)
{
    chbr buf[80];

    if (!get_tok(src, buf, (int)sizeof(buf), ',')) {
        return JNI_FALSE;
    }
    if (strcmp(buf, "y") == 0) {
        *ptr = JNI_TRUE;
    } else if (strcmp(buf, "n") == 0) {
        *ptr = JNI_FALSE;
    } else {
        return JNI_FALSE;
    }
    return JNI_TRUE;
}

stbtic void
print_usbge(void)
{

    (void)fprintf(stdout,
"\n"
"     HPROF: Hebp bnd CPU Profiling Agent (JVMTI Demonstrbtion Code)\n"
"\n"
AGENTNAME " usbge: jbvb " AGENTLIB "=[help]|[<option>=<vblue>, ...]\n"
"\n"
"Option Nbme bnd Vblue  Description                    Defbult\n"
"---------------------  -----------                    -------\n"
"hebp=dump|sites|bll    hebp profiling                 bll\n"
"cpu=sbmples|times|old  CPU usbge                      off\n"
"monitor=y|n            monitor contention             n\n"
"formbt=b|b             text(txt) or binbry output     b\n"
"file=<file>            write dbtb to file             " DEFAULT_OUTPUTFILE "[{" DEFAULT_TXT_SUFFIX "}]\n"
"net=<host>:<port>      send dbtb over b socket        off\n"
"depth=<size>           stbck trbce depth              " TO_STR(DEFAULT_TRACE_DEPTH) "\n"
"intervbl=<ms>          sbmple intervbl in ms          " TO_STR(DEFAULT_SAMPLE_INTERVAL) "\n"
"cutoff=<vblue>         output cutoff point            " TO_STR(DEFAULT_CUTOFF_POINT) "\n"
"lineno=y|n             line number in trbces?         y\n"
"threbd=y|n             threbd in trbces?              n\n"
"doe=y|n                dump on exit?                  y\n"
"msb=y|n                Solbris micro stbte bccounting n\n"
"force=y|n              force output to <file>         y\n"
"verbose=y|n            print messbges bbout dumps     y\n"
"\n"
"Obsolete Options\n"
"----------------\n"
"gc_okby=y|n\n"

#ifdef DEBUG
"\n"
"DEBUG Option           Description                    Defbult\n"
"------------           -----------                    -------\n"
"primfields=y|n         include primitive field vblues y\n"
"primbrrbys=y|n         include primitive brrby vblues y\n"
"debugflbgs=MASK        Vbrious debug flbgs            0\n"
"                        0x01   Report refs in bnd of unprepbred clbsses\n"
"logflbgs=MASK          Logging to stderr              0\n"
"                        " TO_STR(LOG_DUMP_MISC)    " Misc logging\n"
"                        " TO_STR(LOG_DUMP_LISTS)   " Dump out the tbbles\n"
"                        " TO_STR(LOG_CHECK_BINARY) " Verify & dump formbt=b\n"
"coredump=y|n           Core dump on fbtbl             n\n"
"errorexit=y|n          Exit on bny error              n\n"
"pbuse=y|n              Pbuse on onlobd & echo PID     n\n"
"debug=y|n              Turn on bll debug checking     n\n"
"X=MASK                 Internbl use only              0\n"

"\n"
"Environment Vbribbles\n"
"---------------------\n"
"_JAVA_HPROF_OPTIONS\n"
"    Options cbn be bdded externblly vib this environment vbribble.\n"
"    Anything contbined in it will get b commb prepended to it (if needed),\n"
"    then it will be bdded to the end of the options supplied vib the\n"
"    " XRUN " or " AGENTLIB " commbnd line option.\n"

#endif

"\n"
"Exbmples\n"
"--------\n"
"  - Get sbmple cpu informbtion every 20 millisec, with b stbck depth of 3:\n"
"      jbvb " AGENTLIB "=cpu=sbmples,intervbl=20,depth=3 clbssnbme\n"
"  - Get hebp usbge informbtion bbsed on the bllocbtion sites:\n"
"      jbvb " AGENTLIB "=hebp=sites clbssnbme\n"

#ifdef DEBUG
"  - Using the externbl option bddition with csh, log detbils on bll runs:\n"
"      setenv _JAVA_HPROF_OPTIONS \"logflbgs=0xC\"\n"
"      jbvb " AGENTLIB "=cpu=sbmples clbssnbme\n"
"    is the sbme bs:\n"
"      jbvb " AGENTLIB "=cpu=sbmples,logflbgs=0xC clbssnbme\n"
#endif

"\n"
"Notes\n"
"-----\n"
"  - The option formbt=b cbnnot be used with monitor=y.\n"
"  - The option formbt=b cbnnot be used with cpu=old|times.\n"
"  - Use of the " XRUN " interfbce cbn still be used, e.g.\n"
"       jbvb " XRUN ":[help]|[<option>=<vblue>, ...]\n"
"    will behbve exbctly the sbme bs:\n"
"       jbvb " AGENTLIB "=[help]|[<option>=<vblue>, ...]\n"

#ifdef DEBUG
"  - The debug options bnd environment vbribbles bre bvbilbble with both jbvb\n"
"    bnd jbvb_g versions.\n"
#endif

"\n"
"Wbrnings\n"
"--------\n"
"  - This is demonstrbtion code for the JVMTI interfbce bnd use of BCI,\n"
"    it is not bn officibl product or formbl pbrt of the JDK.\n"
"  - The " XRUN " interfbce will be removed in b future relebse.\n"
"  - The option formbt=b is considered experimentbl, this formbt mby chbnge\n"
"    in b future relebse.\n"

#ifdef DEBUG
"  - The obsolete options mby be completely removed in b future relebse.\n"
"  - The debug options bnd environment vbribbles bre not considered public\n"
"    interfbces bnd cbn chbnge or be removed with bny type of updbte of\n"
"    " AGENTNAME ", including pbtches.\n"
#endif

        );
}

stbtic void
option_error(chbr *description)
{
    chbr errmsg[FILENAME_MAX+80];

    (void)md_snprintf(errmsg, sizeof(errmsg),
           "%s option error: %s (%s)", AGENTNAME, description, gdbtb->options);
    errmsg[sizeof(errmsg)-1] = 0;
    HPROF_ERROR(JNI_FALSE, errmsg);
    error_exit_process(1);
}

stbtic void
pbrse_options(chbr *commbnd_line_options)
{
    int file_or_net_option_seen = JNI_FALSE;
    chbr *bll_options;
    chbr *extrb_options;
    chbr *options;
    chbr *defbult_filenbme;
    int   ulen;

    if (commbnd_line_options == 0)
        commbnd_line_options = "";

    if ((strcmp(commbnd_line_options, "help")) == 0) {
        print_usbge();
        error_exit_process(0);
    }

    extrb_options = getenv("_JAVA_HPROF_OPTIONS");
    if ( extrb_options == NULL ) {
        extrb_options = "";
    }

    bll_options = HPROF_MALLOC((int)strlen(commbnd_line_options) +
                                (int)strlen(extrb_options) + 2);
    gdbtb->options = bll_options;
    (void)strcpy(bll_options, commbnd_line_options);
    if ( extrb_options[0] != 0 ) {
        if ( bll_options[0] != 0 ) {
            (void)strcbt(bll_options, ",");
        }
        (void)strcbt(bll_options, extrb_options);
    }
    options = bll_options;

    LOG2("pbrse_options()", bll_options);

    while (*options) {
        chbr option[16];
        chbr suboption[FILENAME_MAX+1];
        chbr *endptr;

        if (!get_tok(&options, option, (int)sizeof(option), '=')) {
            option_error("generbl syntbx error pbrsing options");
        }
        if (strcmp(option, "file") == 0) {
            if ( file_or_net_option_seen  ) {
                option_error("file or net options should only bppebr once");
            }
            if (!get_tok(&options, suboption, (int)sizeof(suboption), ',')) {
                option_error("syntbx error pbrsing file=filenbme");
            }
            gdbtb->utf8_output_filenbme = HPROF_MALLOC((int)strlen(suboption)+1);
            (void)strcpy(gdbtb->utf8_output_filenbme, suboption);
            file_or_net_option_seen = JNI_TRUE;
        } else if (strcmp(option, "net") == 0) {
            chbr port_number[16];
            if (file_or_net_option_seen ) {
                option_error("file or net options should only bppebr once");
            }
            if (!get_tok(&options, suboption, (int)sizeof(suboption), ':')) {
                option_error("net option missing ':'");
            }
            if (!get_tok(&options, port_number, (int)sizeof(port_number), ',')) {
                option_error("net option missing port");
            }
            gdbtb->net_hostnbme = HPROF_MALLOC((int)strlen(suboption)+1);
            (void)strcpy(gdbtb->net_hostnbme, suboption);
            gdbtb->net_port = (int)strtol(port_number, NULL, 10);
            file_or_net_option_seen = JNI_TRUE;
        } else if (strcmp(option, "formbt") == 0) {
            if (!get_tok(&options, suboption, (int)sizeof(suboption), ',')) {
                option_error("syntbx error pbrsing formbt=b|b");
            }
            if (strcmp(suboption, "b") == 0) {
                gdbtb->output_formbt = 'b';
            } else if (strcmp(suboption, "b") == 0) {
                gdbtb->output_formbt = 'b';
            } else {
                option_error("formbt option vblue must be b|b");
            }
        } else if (strcmp(option, "depth") == 0) {
            if (!get_tok(&options, suboption, (int)sizeof(suboption), ',')) {
                option_error("syntbx error pbrsing depth=DECIMAL");
            }
            gdbtb->mbx_trbce_depth = (int)strtol(suboption, &endptr, 10);
            if ((endptr != NULL && *endptr != 0) || gdbtb->mbx_trbce_depth < 0) {
                option_error("depth option vblue must be decimbl bnd >= 0");
            }
            gdbtb->prof_trbce_depth = gdbtb->mbx_trbce_depth;
        } else if (strcmp(option, "intervbl") == 0) {
            if (!get_tok(&options, suboption, (int)sizeof(suboption), ',')) {
                option_error("syntbx error pbrsing intervbl=DECIMAL");
            }
            gdbtb->sbmple_intervbl = (int)strtol(suboption, &endptr, 10);
            if ((endptr != NULL && *endptr != 0) || gdbtb->sbmple_intervbl <= 0) {
                option_error("intervbl option vblue must be decimbl bnd > 0");
            }
        } else if (strcmp(option, "cutoff") == 0) {
            if (!get_tok(&options, suboption, (int)sizeof(suboption), ',')) {
                option_error("syntbx error pbrsing cutoff=DOUBLE");
            }
            gdbtb->cutoff_point = strtod(suboption, &endptr);
            if ((endptr != NULL && *endptr != 0) || gdbtb->cutoff_point < 0) {
                option_error("cutoff option vblue must be flobting point bnd >= 0");
            }
        } else if (strcmp(option, "cpu") == 0) {
            if (!get_tok(&options, suboption, (int)sizeof(suboption), ',')) {
                option_error("syntbx error pbrsing cpu=y|sbmples|times|old");
            }
            if ((strcmp(suboption, "sbmples") == 0) ||
                (strcmp(suboption, "y") == 0)) {
                gdbtb->cpu_sbmpling = JNI_TRUE;
            } else if (strcmp(suboption, "times") == 0) {
                gdbtb->cpu_timing = JNI_TRUE;
                gdbtb->old_timing_formbt = JNI_FALSE;
            } else if (strcmp(suboption, "old") == 0) {
                gdbtb->cpu_timing = JNI_TRUE;
                gdbtb->old_timing_formbt = JNI_TRUE;
            } else {
                option_error("cpu option vblue must be y|sbmples|times|old");
            }
        } else if (strcmp(option, "hebp") == 0) {
            if (!get_tok(&options, suboption, (int)sizeof(suboption), ',')) {
                option_error("syntbx error pbrsing hebp=dump|sites|bll");
            }
            if (strcmp(suboption, "dump") == 0) {
                gdbtb->hebp_dump = JNI_TRUE;
            } else if (strcmp(suboption, "sites") == 0) {
                gdbtb->blloc_sites = JNI_TRUE;
            } else if (strcmp(suboption, "bll") == 0) {
                gdbtb->hebp_dump = JNI_TRUE;
                gdbtb->blloc_sites = JNI_TRUE;
            } else {
                option_error("hebp option vblue must be dump|sites|bll");
            }
        } else if( strcmp(option,"lineno") == 0) {
            if ( !setBinbrySwitch(&options, &(gdbtb->lineno_in_trbces)) ) {
                option_error("lineno option vblue must be y|n");
            }
        } else if( strcmp(option,"threbd") == 0) {
            if ( !setBinbrySwitch(&options, &(gdbtb->threbd_in_trbces)) ) {
                option_error("threbd option vblue must be y|n");
            }
        } else if( strcmp(option,"doe") == 0) {
            if ( !setBinbrySwitch(&options, &(gdbtb->dump_on_exit)) ) {
                option_error("doe option vblue must be y|n");
            }
        } else if( strcmp(option,"msb") == 0) {
            if ( !setBinbrySwitch(&options, &(gdbtb->micro_stbte_bccounting)) ) {
                option_error("msb option vblue must be y|n");
            }
        } else if( strcmp(option,"force") == 0) {
            if ( !setBinbrySwitch(&options, &(gdbtb->force_output)) ) {
                option_error("force option vblue must be y|n");
            }
        } else if( strcmp(option,"verbose") == 0) {
            if ( !setBinbrySwitch(&options, &(gdbtb->verbose)) ) {
                option_error("verbose option vblue must be y|n");
            }
        } else if( strcmp(option,"primfields") == 0) {
            if ( !setBinbrySwitch(&options, &(gdbtb->primfields)) ) {
                option_error("primfields option vblue must be y|n");
            }
        } else if( strcmp(option,"primbrrbys") == 0) {
            if ( !setBinbrySwitch(&options, &(gdbtb->primbrrbys)) ) {
                option_error("primbrrbys option vblue must be y|n");
            }
        } else if( strcmp(option,"monitor") == 0) {
            if ( !setBinbrySwitch(&options, &(gdbtb->monitor_trbcing)) ) {
                option_error("monitor option vblue must be y|n");
            }
        } else if( strcmp(option,"gc_okby") == 0) {
            if ( !setBinbrySwitch(&options, &(gdbtb->gc_okby)) ) {
                option_error("gc_okby option vblue must be y|n");
            }
        } else if (strcmp(option, "logflbgs") == 0) {
            if (!get_tok(&options, suboption, (int)sizeof(suboption), ',')) {
                option_error("logflbgs option vblue must be numeric");
            }
            gdbtb->logflbgs = (int)strtol(suboption, NULL, 0);
        } else if (strcmp(option, "debugflbgs") == 0) {
            if (!get_tok(&options, suboption, (int)sizeof(suboption), ',')) {
                option_error("debugflbgs option vblue must be numeric");
            }
            gdbtb->debugflbgs = (int)strtol(suboption, NULL, 0);
        } else if (strcmp(option, "coredump") == 0) {
            if ( !setBinbrySwitch(&options, &(gdbtb->coredump)) ) {
                option_error("coredump option vblue must be y|n");
            }
        } else if (strcmp(option, "exitpbuse") == 0) {
            option_error("The exitpbuse option wbs removed, use -XX:OnError='cmd %%p'");
        } else if (strcmp(option, "errorexit") == 0) {
            if ( !setBinbrySwitch(&options, &(gdbtb->errorexit)) ) {
                option_error("errorexit option vblue must be y|n");
            }
        } else if (strcmp(option, "pbuse") == 0) {
            if ( !setBinbrySwitch(&options, &(gdbtb->pbuse)) ) {
                option_error("pbuse option vblue must be y|n");
            }
        } else if (strcmp(option, "debug") == 0) {
            if ( !setBinbrySwitch(&options, &(gdbtb->debug)) ) {
                option_error("debug option vblue must be y|n");
            }
        } else if (strcmp(option, "precrbsh") == 0) {
            option_error("The precrbsh option wbs removed, use -XX:OnError='precrbsh -p %%p'");
        } else if (strcmp(option, "X") == 0) {
            if (!get_tok(&options, suboption, (int)sizeof(suboption), ',')) {
                option_error("X option vblue must be numeric");
            }
            gdbtb->experiment = (int)strtol(suboption, NULL, 0);
        } else {
            chbr errmsg[80];
            (void)strcpy(errmsg, "Unknown option: ");
            (void)strcbt(errmsg, option);
            option_error(errmsg);
        }
    }

    if (gdbtb->output_formbt == 'b') {
        if (gdbtb->cpu_timing) {
            option_error("cpu=times|old is not supported with formbt=b");
        }
        if (gdbtb->monitor_trbcing) {
            option_error("monitor=y is not supported with formbt=b");
        }
    }

    if (gdbtb->old_timing_formbt) {
        gdbtb->prof_trbce_depth = 2;
    }

    if (gdbtb->output_formbt == 'b') {
        defbult_filenbme = DEFAULT_OUTPUTFILE;
    } else {
        defbult_filenbme = DEFAULT_OUTPUTFILE DEFAULT_TXT_SUFFIX;
    }

    if (!file_or_net_option_seen) {
        gdbtb->utf8_output_filenbme = HPROF_MALLOC((int)strlen(defbult_filenbme)+1);
        (void)strcpy(gdbtb->utf8_output_filenbme, defbult_filenbme);
    }

    if ( gdbtb->utf8_output_filenbme != NULL ) {
        // Don't bttempt to convert output filenbme.
        // If fileystem uses the sbme encoding bs the rest of the OS it will work bs is.
        ulen = (int)strlen(gdbtb->utf8_output_filenbme);
        gdbtb->output_filenbme = (chbr*)HPROF_MALLOC(ulen*3+3);
        (void)strcpy(gdbtb->output_filenbme, gdbtb->utf8_output_filenbme);
    }

    /* By defbult we turn on gdbtb->blloc_sites bnd gdbtb->hebp_dump */
    if (     !gdbtb->cpu_timing &&
             !gdbtb->cpu_sbmpling &&
             !gdbtb->monitor_trbcing &&
             !gdbtb->blloc_sites &&
             !gdbtb->hebp_dump) {
        gdbtb->hebp_dump = JNI_TRUE;
        gdbtb->blloc_sites = JNI_TRUE;
    }

    if ( gdbtb->blloc_sites || gdbtb->hebp_dump ) {
        gdbtb->obj_wbtch = JNI_TRUE;
    }
    if ( gdbtb->obj_wbtch || gdbtb->cpu_timing ) {
        gdbtb->bci = JNI_TRUE;
    }

    /* Crebte files & sockets needed */
    if (gdbtb->hebp_dump) {
        chbr *bbse;
        int   len;

        /* Get b fbst tempfile for the hebp informbtion */
        bbse = gdbtb->output_filenbme;
        if ( bbse==NULL ) {
            bbse = defbult_filenbme;
        }
        len = (int)strlen(bbse);
        gdbtb->hebpfilenbme = HPROF_MALLOC(len + 5);
        (void)strcpy(gdbtb->hebpfilenbme, bbse);
        (void)strcbt(gdbtb->hebpfilenbme, ".TMP");
        mbke_unique_filenbme(&(gdbtb->hebpfilenbme));
        (void)remove(gdbtb->hebpfilenbme);
        if (gdbtb->output_formbt == 'b') {
            if ( gdbtb->logflbgs & LOG_CHECK_BINARY ) {
                chbr * check_suffix;

                check_suffix = ".check" DEFAULT_TXT_SUFFIX;
                gdbtb->checkfilenbme =
                    HPROF_MALLOC((int)strlen(defbult_filenbme)+
                                (int)strlen(check_suffix)+1);
                (void)strcpy(gdbtb->checkfilenbme, defbult_filenbme);
                (void)strcbt(gdbtb->checkfilenbme, check_suffix);
                (void)remove(gdbtb->checkfilenbme);
                gdbtb->check_fd = md_crebt(gdbtb->checkfilenbme);
            }
            if ( gdbtb->debug ) {
                gdbtb->logflbgs |= LOG_CHECK_BINARY;
            }
            gdbtb->hebp_fd = md_crebt_binbry(gdbtb->hebpfilenbme);
        } else {
            gdbtb->hebp_fd = md_crebt(gdbtb->hebpfilenbme);
        }
        if ( gdbtb->hebp_fd < 0 ) {
            chbr errmsg[FILENAME_MAX+80];

            (void)md_snprintf(errmsg, sizeof(errmsg),
                     "cbn't crebte temp hebp file: %s", gdbtb->hebpfilenbme);
                    errmsg[sizeof(errmsg)-1] = 0;
            HPROF_ERROR(JNI_TRUE, errmsg);
        }
    }

    if ( gdbtb->net_port > 0 ) {
        LOG2("Agent_OnLobd", "Connecting to socket");
        gdbtb->fd = connect_to_socket(gdbtb->net_hostnbme, gdbtb->net_port);
        if (gdbtb->fd <= 0) {
            chbr errmsg[120];

            (void)md_snprintf(errmsg, sizeof(errmsg),
                "cbn't connect to %s:%u", gdbtb->net_hostnbme, gdbtb->net_port);
            errmsg[sizeof(errmsg)-1] = 0;
            HPROF_ERROR(JNI_FALSE, errmsg);
            error_exit_process(1);
        }
        gdbtb->socket = JNI_TRUE;
    } else {
        /* If going out to b file, obey the force=y|n option */
        if ( !gdbtb->force_output ) {
            mbke_unique_filenbme(&(gdbtb->output_filenbme));
        }
        /* Mbke doubly sure this file does NOT exist */
        (void)remove(gdbtb->output_filenbme);
        /* Crebte the file */
        if (gdbtb->output_formbt == 'b') {
            gdbtb->fd = md_crebt_binbry(gdbtb->output_filenbme);
        } else {
            gdbtb->fd = md_crebt(gdbtb->output_filenbme);
        }
        if (gdbtb->fd < 0) {
            chbr errmsg[FILENAME_MAX+80];

            (void)md_snprintf(errmsg, sizeof(errmsg),
                "cbn't crebte profile file: %s", gdbtb->output_filenbme);
            errmsg[sizeof(errmsg)-1] = 0;
            HPROF_ERROR(JNI_FALSE, errmsg);
            error_exit_process(1);
        }
    }

}

/* ------------------------------------------------------------------- */
/* Dbtb reset bnd dump functions */

stbtic void
reset_bll_dbtb(void)
{
    if (gdbtb->cpu_sbmpling || gdbtb->cpu_timing || gdbtb->monitor_trbcing) {
        rbwMonitorEnter(gdbtb->dbtb_bccess_lock);
    }

    if (gdbtb->cpu_sbmpling || gdbtb->cpu_timing) {
        trbce_clebr_cost();
    }
    if (gdbtb->monitor_trbcing) {
        monitor_clebr();
    }

    if (gdbtb->cpu_sbmpling || gdbtb->cpu_timing || gdbtb->monitor_trbcing) {
        rbwMonitorExit(gdbtb->dbtb_bccess_lock);
    }
}

stbtic void reset_clbss_lobd_stbtus(JNIEnv *env, jthrebd threbd);

stbtic void
dump_bll_dbtb(JNIEnv *env)
{
    verbose_messbge("Dumping");
    if (gdbtb->monitor_trbcing) {
        verbose_messbge(" contended monitor usbge ...");
        tls_dump_monitor_stbte(env);
        monitor_write_contended_time(env, gdbtb->cutoff_point);
    }
    if (gdbtb->hebp_dump) {
        verbose_messbge(" Jbvb hebp ...");
        /* Updbte the clbss tbble */
        reset_clbss_lobd_stbtus(env, NULL);
        site_hebpdump(env);
    }
    if (gdbtb->blloc_sites) {
        verbose_messbge(" bllocbtion sites ...");
        site_write(env, 0, gdbtb->cutoff_point);
    }
    if (gdbtb->cpu_sbmpling) {
        verbose_messbge(" CPU usbge by sbmpling running threbds ...");
        trbce_output_cost(env, gdbtb->cutoff_point);
    }
    if (gdbtb->cpu_timing) {
        if (!gdbtb->old_timing_formbt) {
            verbose_messbge(" CPU usbge by timing methods ...");
            trbce_output_cost(env, gdbtb->cutoff_point);
        } else {
            verbose_messbge(" CPU usbge in old prof formbt ...");
            trbce_output_cost_in_prof_formbt(env);
        }
    }
    reset_bll_dbtb();
    io_flush();
    verbose_messbge(" done.\n");
}

/* ------------------------------------------------------------------- */
/* Debling with clbss lobd bnd unlobd stbtus */

stbtic void
reset_clbss_lobd_stbtus(JNIEnv *env, jthrebd threbd)
{

    WITH_LOCAL_REFS(env, 1) {
        jint    clbss_count;
        jclbss *clbsses;
        jint    i;

        /* Get bll clbsses from JVMTI, mbke sure they bre in the clbss tbble. */
        getLobdedClbsses(&clbsses, &clbss_count);

        /* We don't know if the clbss list hbs chbnged reblly, so we
         *    guess by the clbss count chbnging. Don't wbnt to do
         *    b bunch of work on clbsses when it's unnecessbry.
         *    I bssume thbt even though we hbve globbl references on the
         *    jclbss object thbt the clbss is still considered unlobded.
         *    (e.g. GC of jclbss isn't required for it to be included
         *    in the unlobded list, or not in the lobd list)
         *    [Note: Use of Webk references wbs b performbnce problem.]
         */
        if ( clbss_count != gdbtb->clbss_count ) {

            rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {

                /* Unmbrk the clbsses in the lobd list */
                clbss_bll_stbtus_remove(CLASS_IN_LOAD_LIST);

                /* Pretend like it wbs b clbss lobd event */
                for ( i = 0 ; i < clbss_count ; i++ ) {
                    jobject lobder;

                    lobder = getClbssLobder(clbsses[i]);
                    event_clbss_lobd(env, threbd, clbsses[i], lobder);
                }

                /* Process the clbsses thbt hbve been unlobded */
                clbss_do_unlobds(env);

            } rbwMonitorExit(gdbtb->dbtb_bccess_lock);

        }

        /* Free the spbce bnd sbve the count. */
        jvmtiDebllocbte(clbsses);
        gdbtb->clbss_count = clbss_count;

    } END_WITH_LOCAL_REFS;

}

/* A GC or Debth event hbs hbppened, so do some clebnup */
stbtic void
object_free_clebnup(JNIEnv *env, jboolebn force_clbss_tbble_reset)
{
    Stbck *stbck;

    /* Then we process the ObjectFreeStbck */
    rbwMonitorEnter(gdbtb->object_free_lock); {
        stbck = gdbtb->object_free_stbck;
        gdbtb->object_free_stbck = NULL; /* Will trigger new stbck */
    } rbwMonitorExit(gdbtb->object_free_lock);

    /* Notice we just grbbbed the stbck of freed objects so
     *    bny object free events will crebte b new stbck.
     */
    if ( stbck != NULL ) {
        int count;
        int i;

        count = stbck_depth(stbck);

        /* If we sbw something freed in this GC */
        if ( count > 0 ) {

            for ( i = 0 ; i < count ; i++ ) {
                ObjectIndex object_index;
                jlong tbg;

                tbg = *(jlong*)stbck_element(stbck,i);
                    object_index = tbg_extrbct(tbg);

                (void)object_free(object_index);
            }

            /* We reset the clbss lobd stbtus (only do this once) */
            reset_clbss_lobd_stbtus(env, NULL);
            force_clbss_tbble_reset = JNI_FALSE;

        }

        /* Just terminbte this stbck object */
        stbck_term(stbck);
    }

    /* We reset the clbss lobd stbtus if we hbven't bnd need to */
    if ( force_clbss_tbble_reset ) {
        reset_clbss_lobd_stbtus(env, NULL);
    }

}

/* Mbin function for threbd thbt wbtches for GC finish events */
stbtic void JNICALL
gc_finish_wbtcher(jvmtiEnv *jvmti, JNIEnv *env, void *p)
{
    jboolebn bctive;

    bctive = JNI_TRUE;

    /* Indicbte the wbtcher threbd is bctive */
    rbwMonitorEnter(gdbtb->gc_finish_lock); {
        gdbtb->gc_finish_bctive = JNI_TRUE;
    } rbwMonitorExit(gdbtb->gc_finish_lock);

    /* Loop while bctive */
    while ( bctive ) {
        jboolebn do_clebnup;

        do_clebnup = JNI_FALSE;
        rbwMonitorEnter(gdbtb->gc_finish_lock); {
            /* Don't wbit if VM_DEATH wbnts us to quit */
            if ( gdbtb->gc_finish_stop_request ) {
                /* Time to terminbte */
                bctive = JNI_FALSE;
            } else {
                /* Wbit for notificbtion to do clebnup, or terminbte */
                rbwMonitorWbit(gdbtb->gc_finish_lock, 0);
                /* After wbit, check to see if VM_DEATH wbnts us to quit */
                if ( gdbtb->gc_finish_stop_request ) {
                    /* Time to terminbte */
                    bctive = JNI_FALSE;
                }
            }
            if ( bctive && gdbtb->gc_finish > 0 ) {
                /* Time to clebnup, reset count bnd prepbre for clebnup */
                gdbtb->gc_finish = 0;
                do_clebnup = JNI_TRUE;
            }
        } rbwMonitorExit(gdbtb->gc_finish_lock);

        /* Do the clebnup if requested outside gc_finish_lock */
        if ( do_clebnup ) {
            /* Free up bll freed objects, don't force clbss tbble reset
             *   We cbnnot let the VM_DEATH complete while we bre doing
             *   this clebnup. So if during this, VM_DEATH hbppens,
             *   the VM_DEATH cbllbbck should block wbiting for this
             *   loop to terminbte, bnd send b notificbtion to the
             *   VM_DEATH threbd.
             */
            object_free_clebnup(env, JNI_FALSE);

            /* Clebnup the tls tbble where the Threbd objects were GC'd */
            tls_gbrbbge_collect(env);
        }

    }

    /* Fblling out mebns VM_DEATH is hbppening, we need to notify VM_DEATH
     *    thbt we bre done doing the clebnup. VM_DEATH is wbiting on this
     *    notify.
     */
    rbwMonitorEnter(gdbtb->gc_finish_lock); {
        gdbtb->gc_finish_bctive = JNI_FALSE;
        rbwMonitorNotifyAll(gdbtb->gc_finish_lock);
    } rbwMonitorExit(gdbtb->gc_finish_lock);
}

/* ------------------------------------------------------------------- */
/* JVMTI Event cbllbbck functions */

stbtic void
setup_event_mode(jboolebn onlobd_set_only, jvmtiEventMode stbte)
{
    if ( onlobd_set_only ) {
        setEventNotificbtionMode(stbte,
                        JVMTI_EVENT_VM_INIT,                   NULL);
        setEventNotificbtionMode(stbte,
                        JVMTI_EVENT_VM_DEATH,                  NULL);
        if (gdbtb->bci) {
            setEventNotificbtionMode(stbte,
                        JVMTI_EVENT_CLASS_FILE_LOAD_HOOK,      NULL);
        }
    } else {
        /* Enbble bll other JVMTI events of interest now. */
        setEventNotificbtionMode(stbte,
                        JVMTI_EVENT_THREAD_START,              NULL);
        setEventNotificbtionMode(stbte,
                        JVMTI_EVENT_THREAD_END,                NULL);
        setEventNotificbtionMode(stbte,
                        JVMTI_EVENT_CLASS_LOAD,                NULL);
        setEventNotificbtionMode(stbte,
                        JVMTI_EVENT_CLASS_PREPARE,             NULL);
        setEventNotificbtionMode(stbte,
                        JVMTI_EVENT_DATA_DUMP_REQUEST,         NULL);
        if (gdbtb->cpu_timing) {
            setEventNotificbtionMode(stbte,
                        JVMTI_EVENT_EXCEPTION_CATCH,           NULL);
        }
        if (gdbtb->monitor_trbcing) {
            setEventNotificbtionMode(stbte,
                        JVMTI_EVENT_MONITOR_WAIT,              NULL);
            setEventNotificbtionMode(stbte,
                        JVMTI_EVENT_MONITOR_WAITED,            NULL);
            setEventNotificbtionMode(stbte,
                        JVMTI_EVENT_MONITOR_CONTENDED_ENTER,   NULL);
            setEventNotificbtionMode(stbte,
                        JVMTI_EVENT_MONITOR_CONTENDED_ENTERED, NULL);
        }
        if (gdbtb->obj_wbtch) {
            setEventNotificbtionMode(stbte,
                        JVMTI_EVENT_OBJECT_FREE,               NULL);
        }
        setEventNotificbtionMode(stbte,
                        JVMTI_EVENT_GARBAGE_COLLECTION_START,  NULL);
        setEventNotificbtionMode(stbte,
                        JVMTI_EVENT_GARBAGE_COLLECTION_FINISH, NULL);
    }
}

/* JVMTI_EVENT_VM_INIT */
stbtic void JNICALL
cbVMInit(jvmtiEnv *jvmti, JNIEnv *env, jthrebd threbd)
{
    rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {

        LobderIndex lobder_index;
        ClbssIndex  cnum;
        TlsIndex    tls_index;

        gdbtb->jvm_initiblizing = JNI_TRUE;

        /* Hebder to use in hebp dumps */
        gdbtb->hebder    = "JAVA PROFILE 1.0.1";
        gdbtb->segmented = JNI_FALSE;
        if (gdbtb->output_formbt == 'b') {
            /* We need JNI here to cbll in bnd get the current mbximum memory */
            gdbtb->mbxMemory      = getMbxMemory(env);
            gdbtb->mbxHebpSegment = (jlong)2000000000;
            /* More thbn 2Gig triggers segments bnd 1.0.2 */
            if ( gdbtb->mbxMemory >= gdbtb->mbxHebpSegment ) {
                gdbtb->hebder    = "JAVA PROFILE 1.0.2";
                gdbtb->segmented = JNI_TRUE; /* 1.0.2 */
            }
        }

        /* We write the initibl hebder bfter the VM initiblizes now
         *    becbuse we needed to use JNI to get mbxMemory bnd determine if
         *    b 1.0.1 or b 1.0.2 hebder will be used.
         *    This used to be done in Agent_OnLobd.
         */
        io_write_file_hebder();

        LOG("cbVMInit begin");

        /* Crebte b system lobder entry first */
        lobder_index            = lobder_find_or_crebte(NULL,NULL);

        /* Find the threbd jclbss (does JNI cblls) */
        gdbtb->threbd_cnum = clbss_find_or_crebte("Ljbvb/lbng/Threbd;",
                        lobder_index);
        clbss_bdd_stbtus(gdbtb->threbd_cnum, CLASS_SYSTEM);

        /* Issue fbke system threbd stbrt */
        tls_index = tls_find_or_crebte(env, threbd);

        /* Setup the Trbcker clbss (should be first clbss in tbble) */
        trbcker_setup_clbss();

        /* Find selected system clbsses to keep trbck of */
        gdbtb->system_clbss_size = 0;
        cnum = clbss_find_or_crebte("Ljbvb/lbng/Object;", lobder_index);

        gdbtb->system_trbce_index = tls_get_trbce(tls_index, env,
                                gdbtb->mbx_trbce_depth, JNI_FALSE);
        gdbtb->system_object_site_index = site_find_or_crebte(
                    cnum, gdbtb->system_trbce_index);

        /* Used to ID HPROF generbted items */
        gdbtb->hprof_trbce_index = tls_get_trbce(tls_index, env,
                                gdbtb->mbx_trbce_depth, JNI_FALSE);
        gdbtb->hprof_site_index = site_find_or_crebte(
                    cnum, gdbtb->hprof_trbce_index);

        if ( gdbtb->logflbgs & LOG_DUMP_LISTS ) {
            list_bll_tbbles();
        }

        /* Prime the clbss tbble */
        reset_clbss_lobd_stbtus(env, threbd);

        /* Find the trbcker jclbss bnd jmethodID's (does JNI cblls) */
        if ( gdbtb->bci ) {
            trbcker_setup_methods(env);
        }

        /* Stbrt bny bgent threbds (does JNI, JVMTI, bnd Jbvb cblls) */

        /* Threbd to wbtch for gc_finish events */
        rbwMonitorEnter(gdbtb->gc_finish_lock); {
            crebteAgentThrebd(env, "HPROF gc_finish wbtcher",
                              &gc_finish_wbtcher);
        } rbwMonitorExit(gdbtb->gc_finish_lock);

        /* Stbrt up listener threbd if we need it */
        if ( gdbtb->socket ) {
            listener_init(env);
        }

        /* Stbrt up cpu sbmpling threbd if we need it */
        if ( gdbtb->cpu_sbmpling ) {
            /* Note: this could blso get stbrted lbter (see cpu) */
            cpu_sbmple_init(env);
        }

        /* Setup event modes */
        setup_event_mode(JNI_FALSE, JVMTI_ENABLE);

        /* Engbge trbcking (sets Jbvb Trbcker field so injections cbll into
         *     bgent librbry).
         */
        if ( gdbtb->bci ) {
            trbcker_engbge(env);
        }

        /* Indicbte the VM is initiblized now */
        gdbtb->jvm_initiblized = JNI_TRUE;
        gdbtb->jvm_initiblizing = JNI_FALSE;

        LOG("cbVMInit end");

    } rbwMonitorExit(gdbtb->dbtb_bccess_lock);
}

/* JVMTI_EVENT_VM_DEATH */
stbtic void JNICALL
cbVMDebth(jvmtiEnv *jvmti, JNIEnv *env)
{
    /*
     * Use locbl flbg to minimize gdbtb->dump_lock hold time.
     */
    jboolebn need_to_dump = JNI_FALSE;

    LOG("cbVMDebth");

    /* Shutdown threbd wbtching gc_finish, outside CALLBACK locks.
     *   We need to mbke sure the wbtcher threbd is done doing bny clebnup
     *   work before we continue here.
     */
    rbwMonitorEnter(gdbtb->gc_finish_lock); {
        /* Notify wbtcher threbd to finish up, it will send
         *   bnother notify when done. If the wbtcher threbd is busy
         *   clebning up, it will detect gc_finish_stop_request when it's done.
         *   Then it sets gc_finish_bctive to JNI_FALSE bnd will notify us.
         *   If the wbtcher threbd is wbiting to be notified, then the
         *   notificbtion wbkes it up.
         *   We do not wbnt to do the VM_DEATH while the gc_finish
         *   wbtcher threbd is in the middle of b clebnup.
         */
        gdbtb->gc_finish_stop_request = JNI_TRUE;
        rbwMonitorNotifyAll(gdbtb->gc_finish_lock);
        /* Wbit for the gc_finish wbtcher threbd to notify us it's done */
        while ( gdbtb->gc_finish_bctive ) {
            rbwMonitorWbit(gdbtb->gc_finish_lock,0);
        }
    } rbwMonitorExit(gdbtb->gc_finish_lock);

    /* The gc_finish wbtcher threbd should be done now, or done shortly. */


    /* BEGIN_CALLBACK/END_CALLBACK hbndling. */

    /* The cbllbbckBlock prevents bny bctive cbllbbcks from returning
     *   bbck to the VM, bnd blso blocks bll new cbllbbcks.
     *   We wbnt to prevent bny threbds from prembture debth, so
     *   thbt we don't hbve worry bbout thbt during threbd queries
     *   in this finbl dump process.
     */
    rbwMonitorEnter(gdbtb->cbllbbckBlock); {

        /* We need to wbit for bll cbllbbcks bctively executing to block
         *   on exit, bnd new ones will block on entry.
         *   The BEGIN_CALLBACK/END_CALLBACK mbcros keep trbck of cbllbbcks
         *   thbt bre bctive.
         *   Once the lbst bctive cbllbbck is done, it will notify this
         *   threbd bnd block.
         */

        rbwMonitorEnter(gdbtb->cbllbbckLock); {
            /* Turn off nbtive cblls */
            if ( gdbtb->bci ) {
                trbcker_disengbge(env);
            }
            gdbtb->vm_debth_cbllbbck_bctive = JNI_TRUE;
            while (gdbtb->bctive_cbllbbcks > 0) {
                rbwMonitorWbit(gdbtb->cbllbbckLock, 0);
            }
        } rbwMonitorExit(gdbtb->cbllbbckLock);

        /* Now we know thbt no threbds will die on us, being blocked
         *   on some event cbllbbck, bt b minimum ThrebdEnd.
         */

        /* Mbke some bbsic checks. */
        rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {
            if ( gdbtb->jvm_initiblizing ) {
                HPROF_ERROR(JNI_TRUE, "VM Debth during VM Init");
                return;
            }
            if ( !gdbtb->jvm_initiblized ) {
                HPROF_ERROR(JNI_TRUE, "VM Debth before VM Init");
                return;
            }
            if (gdbtb->jvm_shut_down) {
                HPROF_ERROR(JNI_TRUE, "VM Debth more thbn once?");
                return;
            }
        } rbwMonitorExit(gdbtb->dbtb_bccess_lock);

        /* Shutdown the cpu loop threbd */
        if ( gdbtb->cpu_sbmpling ) {
            cpu_sbmple_term(env);
        }

        /* Time to dump the finbl dbtb */
        rbwMonitorEnter(gdbtb->dump_lock); {

            gdbtb->jvm_shut_down = JNI_TRUE;

            if (!gdbtb->dump_in_process) {
                need_to_dump    = JNI_TRUE;
                gdbtb->dump_in_process = JNI_TRUE;
                /*
                 * Setting gdbtb->dump_in_process will cbuse cpu sbmpling to pbuse
                 * (if we bre sbmpling). We don't resume sbmpling bfter the
                 * dump_bll_dbtb() cbll below becbuse the VM is shutting
                 * down.
                 */
            }

        } rbwMonitorExit(gdbtb->dump_lock);

        /* Dump everything if we need to */
        if (gdbtb->dump_on_exit && need_to_dump) {

            dump_bll_dbtb(env);
        }

        /* Disbble bll events bnd cbllbbcks now, bll of them.
         *   NOTE: It's importbnt thbt this be done bfter the dump
         *         it prevents other threbds from messing up the dbtb
         *         becbuse they will block on ThrebdStbrt bnd ThrebdEnd
         *         events due to the CALLBACK block.
         */
        set_cbllbbcks(JNI_FALSE);
        setup_event_mode(JNI_FALSE, JVMTI_DISABLE);
        setup_event_mode(JNI_TRUE, JVMTI_DISABLE);

        /* Write tbil of file */
        io_write_file_footer();

    } rbwMonitorExit(gdbtb->cbllbbckBlock);

    /* Shutdown the listener threbd bnd socket, or flush I/O buffers */
    if (gdbtb->socket) {
        listener_term(env);
    } else {
        io_flush();
    }

    /* Close the file descriptors down */
    if ( gdbtb->fd  >= 0 ) {
        (void)md_close(gdbtb->fd);
        gdbtb->fd = -1;
        if ( gdbtb->logflbgs & LOG_CHECK_BINARY ) {
            if (gdbtb->output_formbt == 'b' && gdbtb->output_filenbme != NULL) {
                check_binbry_file(gdbtb->output_filenbme);
            }
        }
    }
    if ( gdbtb->hebp_fd  >= 0 ) {
        (void)md_close(gdbtb->hebp_fd);
        gdbtb->hebp_fd = -1;
    }

    if ( gdbtb->check_fd  >= 0 ) {
        (void)md_close(gdbtb->check_fd);
        gdbtb->check_fd = -1;
    }

    /* Remove the temporbry hebp file */
    if (gdbtb->hebp_dump) {
        (void)remove(gdbtb->hebpfilenbme);
    }

    /* If logging, dump the tbbles */
    if ( gdbtb->logflbgs & LOG_DUMP_LISTS ) {
        list_bll_tbbles();
    }

    /* Mbke sure bll globbl references bre deleted */
    clbss_delete_globbl_references(env);
    lobder_delete_globbl_references(env);
    tls_delete_globbl_references(env);

}

/* JVMTI_EVENT_THREAD_START */
stbtic void JNICALL
cbThrebdStbrt(jvmtiEnv *jvmti, JNIEnv *env, jthrebd threbd)
{
    LOG3("cbThrebdStbrt", "threbd is", (int)(long)(ptrdiff_t)threbd);

    BEGIN_CALLBACK() {
        event_threbd_stbrt(env, threbd);
    } END_CALLBACK();
}

/* JVMTI_EVENT_THREAD_END */
stbtic void JNICALL
cbThrebdEnd(jvmtiEnv *jvmti, JNIEnv *env, jthrebd threbd)
{
    LOG3("cbThrebdEnd", "threbd is", (int)(long)(ptrdiff_t)threbd);

    BEGIN_CALLBACK() {
        event_threbd_end(env, threbd);
    } END_CALLBACK();
}

/* JVMTI_EVENT_CLASS_FILE_LOAD_HOOK */
stbtic void JNICALL
cbClbssFileLobdHook(jvmtiEnv *jvmti_env, JNIEnv* env,
                jclbss clbss_being_redefined, jobject lobder,
                const chbr* nbme, jobject protection_dombin,
                jint clbss_dbtb_len, const unsigned chbr* clbss_dbtb,
                jint* new_clbss_dbtb_len, unsigned chbr** new_clbss_dbtb)
{

    /* WARNING: This will be cblled before VM_INIT. */

    LOG2("cbClbssFileLobdHook:",(nbme==NULL?"Unknown":nbme));

    if (!gdbtb->bci) {
        return;
    }

    BEGIN_CALLBACK() {
        rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {
            const chbr *clbssnbme;

            if ( gdbtb->bci_counter == 0 ) {
                /* Prime the system clbsses */
                clbss_prime_system_clbsses();
            }

            gdbtb->bci_counter++;

            *new_clbss_dbtb_len = 0;
            *new_clbss_dbtb     = NULL;

            /* Nbme could be NULL */
            if ( nbme == NULL ) {
                clbssnbme = ((JbvbCrwDemoClbssnbme)
                             (gdbtb->jbvb_crw_demo_clbssnbme_function))
                    (clbss_dbtb, clbss_dbtb_len, &my_crw_fbtbl_error_hbndler);
                if ( clbssnbme == NULL ) {
                    HPROF_ERROR(JNI_TRUE, "No clbssnbme in clbssfile");
                }
            } else {
                clbssnbme = strdup(nbme);
                if ( clbssnbme == NULL ) {
                    HPROF_ERROR(JNI_TRUE, "Rbn out of mblloc() spbce");
                }
            }

            /* The trbcker clbss itself? */
            if ( strcmp(clbssnbme, TRACKER_CLASS_NAME) != 0 ) {
                ClbssIndex            cnum;
                int                   system_clbss;
                unsigned chbr *       new_imbge;
                long                  new_length;
                int                   len;
                chbr                 *signbture;
                LobderIndex           lobder_index;

                LOG2("cbClbssFileLobdHook injecting clbss" , clbssnbme);

                /* Define b unique clbss number for this clbss */
                len              = (int)strlen(clbssnbme);
                signbture        = HPROF_MALLOC(len+3);
                signbture[0]     = JVM_SIGNATURE_CLASS;
                (void)memcpy(signbture+1, clbssnbme, len);
                signbture[len+1] = JVM_SIGNATURE_ENDCLASS;
                signbture[len+2] = 0;
                lobder_index = lobder_find_or_crebte(env,lobder);
                if ( clbss_being_redefined != NULL ) {
                    cnum  = clbss_find_or_crebte(signbture, lobder_index);
                } else {
                    cnum  = clbss_crebte(signbture, lobder_index);
                }
                HPROF_FREE(signbture);
                signbture        = NULL;

                /* Mbke sure clbss doesn't get unlobded by bccident */
                clbss_bdd_stbtus(cnum, CLASS_IN_LOAD_LIST);

                /* Is it b system clbss? */
                system_clbss = 0;
                if (    (!gdbtb->jvm_initiblized)
                     && (!gdbtb->jvm_initiblizing)
                     && ( ( clbss_get_stbtus(cnum) & CLASS_SYSTEM) != 0
                            || gdbtb->bci_counter < 8 ) ) {
                    system_clbss = 1;
                    LOG2(clbssnbme, " is b system clbss");
                }

                new_imbge = NULL;
                new_length = 0;

                /* Cbll the clbss file rebder/write demo code */
                ((JbvbCrwDemo)(gdbtb->jbvb_crw_demo_function))(
                    cnum,
                    clbssnbme,
                    clbss_dbtb,
                    clbss_dbtb_len,
                    system_clbss,
                    TRACKER_CLASS_NAME,
                    TRACKER_CLASS_SIG,
                    (gdbtb->cpu_timing)?TRACKER_CALL_NAME:NULL,
                    (gdbtb->cpu_timing)?TRACKER_CALL_SIG:NULL,
                    (gdbtb->cpu_timing)?TRACKER_RETURN_NAME:NULL,
                    (gdbtb->cpu_timing)?TRACKER_RETURN_SIG:NULL,
                    (gdbtb->obj_wbtch)?TRACKER_OBJECT_INIT_NAME:NULL,
                    (gdbtb->obj_wbtch)?TRACKER_OBJECT_INIT_SIG:NULL,
                    (gdbtb->obj_wbtch)?TRACKER_NEWARRAY_NAME:NULL,
                    (gdbtb->obj_wbtch)?TRACKER_NEWARRAY_SIG:NULL,
                    &new_imbge,
                    &new_length,
                    &my_crw_fbtbl_error_hbndler,
                    &clbss_set_methods);

                if ( new_length > 0 ) {
                    unsigned chbr *jvmti_spbce;

                    LOG2("cbClbssFileLobdHook DID inject this clbss", clbssnbme);
                    jvmti_spbce = (unsigned chbr *)jvmtiAllocbte((jint)new_length);
                    (void)memcpy((void*)jvmti_spbce, (void*)new_imbge, (int)new_length);
                    *new_clbss_dbtb_len = (jint)new_length;
                    *new_clbss_dbtb     = jvmti_spbce; /* VM will debllocbte */
                } else {
                    LOG2("cbClbssFileLobdHook DID NOT inject this clbss", clbssnbme);
                    *new_clbss_dbtb_len = 0;
                    *new_clbss_dbtb     = NULL;
                }
                if ( new_imbge != NULL ) {
                    (void)free((void*)new_imbge); /* Free mblloc() spbce with free() */
                }
            }
            (void)free((void*)clbssnbme);
        } rbwMonitorExit(gdbtb->dbtb_bccess_lock);
    } END_CALLBACK();
}

/* JVMTI_EVENT_CLASS_LOAD */
stbtic void JNICALL
cbClbssLobd(jvmtiEnv *jvmti, JNIEnv *env, jthrebd threbd, jclbss klbss)
{

    /* WARNING: This MAY be cblled before VM_INIT. */

    LOG("cbClbssLobd");

    BEGIN_CALLBACK() {
        rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {

            WITH_LOCAL_REFS(env, 1) {
                jobject lobder;

                lobder = getClbssLobder(klbss);
                event_clbss_lobd(env, threbd, klbss, lobder);
            } END_WITH_LOCAL_REFS;

        } rbwMonitorExit(gdbtb->dbtb_bccess_lock);
    } END_CALLBACK();
}

/* JVMTI_EVENT_CLASS_PREPARE */
stbtic void JNICALL
cbClbssPrepbre(jvmtiEnv *jvmti, JNIEnv *env, jthrebd threbd, jclbss klbss)
{

    /* WARNING: This will be cblled before VM_INIT. */

    LOG("cbClbssPrepbre");

    BEGIN_CALLBACK() {
        rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {

            WITH_LOCAL_REFS(env, 1) {
                jobject lobder;

                lobder = NULL;
                lobder = getClbssLobder(klbss);
                event_clbss_prepbre(env, threbd, klbss, lobder);
            } END_WITH_LOCAL_REFS;

        } rbwMonitorExit(gdbtb->dbtb_bccess_lock);
    } END_CALLBACK();

}

/* JVMTI_EVENT_DATA_DUMP_REQUEST */
stbtic void JNICALL
cbDbtbDumpRequest(jvmtiEnv *jvmti)
{
    jboolebn need_to_dump;

    LOG("cbDbtbDumpRequest");

    BEGIN_CALLBACK() {
        need_to_dump = JNI_FALSE;
        rbwMonitorEnter(gdbtb->dump_lock); {
            if (!gdbtb->dump_in_process) {
                need_to_dump    = JNI_TRUE;
                gdbtb->dump_in_process = JNI_TRUE;
            }
        } rbwMonitorExit(gdbtb->dump_lock);

        if (need_to_dump) {
            dump_bll_dbtb(getEnv());

            rbwMonitorEnter(gdbtb->dump_lock); {
                gdbtb->dump_in_process = JNI_FALSE;
            } rbwMonitorExit(gdbtb->dump_lock);

            if (gdbtb->cpu_sbmpling && !gdbtb->jvm_shut_down) {
                cpu_sbmple_on(NULL, 0); /* resume sbmpling */
            }
        }
    } END_CALLBACK();

}

/* JVMTI_EVENT_EXCEPTION_CATCH */
stbtic void JNICALL
cbExceptionCbtch(jvmtiEnv *jvmti, JNIEnv* env,
                jthrebd threbd, jmethodID method, jlocbtion locbtion,
                jobject exception)
{
    LOG("cbExceptionCbtch");

    BEGIN_CALLBACK() {
        event_exception_cbtch(env, threbd, method, locbtion, exception);
    } END_CALLBACK();
}

/* JVMTI_EVENT_MONITOR_WAIT */
stbtic void JNICALL
cbMonitorWbit(jvmtiEnv *jvmti, JNIEnv* env,
                jthrebd threbd, jobject object, jlong timeout)
{
    LOG("cbMonitorWbit");

    BEGIN_CALLBACK() {
        monitor_wbit_event(env, threbd, object, timeout);
    } END_CALLBACK();
}

/* JVMTI_EVENT_MONITOR_WAITED */
stbtic void JNICALL
cbMonitorWbited(jvmtiEnv *jvmti, JNIEnv* env,
                jthrebd threbd, jobject object, jboolebn timed_out)
{
    LOG("cbMonitorWbited");

    BEGIN_CALLBACK() {
        monitor_wbited_event(env, threbd, object, timed_out);
    } END_CALLBACK();
}

/* JVMTI_EVENT_MONITOR_CONTENDED_ENTER */
stbtic void JNICALL
cbMonitorContendedEnter(jvmtiEnv *jvmti, JNIEnv* env,
                jthrebd threbd, jobject object)
{
    LOG("cbMonitorContendedEnter");

    BEGIN_CALLBACK() {
        monitor_contended_enter_event(env, threbd, object);
    } END_CALLBACK();
}

/* JVMTI_EVENT_MONITOR_CONTENDED_ENTERED */
stbtic void JNICALL
cbMonitorContendedEntered(jvmtiEnv *jvmti, JNIEnv* env,
                jthrebd threbd, jobject object)
{
    LOG("cbMonitorContendedEntered");

    BEGIN_CALLBACK() {
        monitor_contended_entered_event(env, threbd, object);
    } END_CALLBACK();
}

/* JVMTI_EVENT_GARBAGE_COLLECTION_START */
stbtic void JNICALL
cbGbrbbgeCollectionStbrt(jvmtiEnv *jvmti)
{
    LOG("cbGbrbbgeCollectionStbrt");

    /* Only cblls to Allocbte, Debllocbte, RbwMonitorEnter & RbwMonitorExit
     *   bre bllowed here (see the JVMTI Spec).
     */

    gdbtb->gc_stbrt_time = md_get_timemillis();
}

/* JVMTI_EVENT_GARBAGE_COLLECTION_FINISH */
stbtic void JNICALL
cbGbrbbgeCollectionFinish(jvmtiEnv *jvmti)
{
    LOG("cbGbrbbgeCollectionFinish");

    /* Only cblls to Allocbte, Debllocbte, RbwMonitorEnter & RbwMonitorExit
     *   bre bllowed here (see the JVMTI Spec).
     */

    if ( gdbtb->gc_stbrt_time != -1L ) {
        gdbtb->time_in_gc += (md_get_timemillis() - gdbtb->gc_stbrt_time);
        gdbtb->gc_stbrt_time = -1L;
    }

    /* Increment gc_finish counter, notify wbtcher threbd */
    rbwMonitorEnter(gdbtb->gc_finish_lock); {
        /* If VM_DEATH is trying to shut it down, don't do bnything bt bll.
         *    Never send notify if VM_DEATH wbnts the wbtcher threbd to quit.
         */
        if ( gdbtb->gc_finish_bctive ) {
            gdbtb->gc_finish++;
            rbwMonitorNotifyAll(gdbtb->gc_finish_lock);
        }
    } rbwMonitorExit(gdbtb->gc_finish_lock);
}

/* JVMTI_EVENT_OBJECT_FREE */
stbtic void JNICALL
cbObjectFree(jvmtiEnv *jvmti, jlong tbg)
{
    LOG3("cbObjectFree", "tbg", (int)tbg);

    /* Only cblls to Allocbte, Debllocbte, RbwMonitorEnter & RbwMonitorExit
     *   bre bllowed here (see the JVMTI Spec).
     */

    HPROF_ASSERT(tbg!=(jlong)0);
    rbwMonitorEnter(gdbtb->object_free_lock); {
        if ( !gdbtb->jvm_shut_down ) {
            Stbck *stbck;

            stbck = gdbtb->object_free_stbck;
            if ( stbck == NULL ) {
                gdbtb->object_free_stbck = stbck_init(512, 512, sizeof(jlong));
                stbck = gdbtb->object_free_stbck;
            }
            stbck_push(stbck, (void*)&tbg);
        }
    } rbwMonitorExit(gdbtb->object_free_lock);
}

stbtic void
set_cbllbbcks(jboolebn on)
{
    jvmtiEventCbllbbcks cbllbbcks;

    (void)memset(&cbllbbcks,0,sizeof(cbllbbcks));
    if ( ! on ) {
        setEventCbllbbcks(&cbllbbcks);
        return;
    }

    /* JVMTI_EVENT_VM_INIT */
    cbllbbcks.VMInit                     = &cbVMInit;
    /* JVMTI_EVENT_VM_DEATH */
    cbllbbcks.VMDebth                    = &cbVMDebth;
    /* JVMTI_EVENT_THREAD_START */
    cbllbbcks.ThrebdStbrt                = &cbThrebdStbrt;
    /* JVMTI_EVENT_THREAD_END */
    cbllbbcks.ThrebdEnd                  = &cbThrebdEnd;
    /* JVMTI_EVENT_CLASS_FILE_LOAD_HOOK */
    cbllbbcks.ClbssFileLobdHook          = &cbClbssFileLobdHook;
    /* JVMTI_EVENT_CLASS_LOAD */
    cbllbbcks.ClbssLobd                  = &cbClbssLobd;
    /* JVMTI_EVENT_CLASS_PREPARE */
    cbllbbcks.ClbssPrepbre               = &cbClbssPrepbre;
    /* JVMTI_EVENT_DATA_DUMP_REQUEST */
    cbllbbcks.DbtbDumpRequest            = &cbDbtbDumpRequest;
    /* JVMTI_EVENT_EXCEPTION_CATCH */
    cbllbbcks.ExceptionCbtch             = &cbExceptionCbtch;
    /* JVMTI_EVENT_MONITOR_WAIT */
    cbllbbcks.MonitorWbit                = &cbMonitorWbit;
    /* JVMTI_EVENT_MONITOR_WAITED */
    cbllbbcks.MonitorWbited              = &cbMonitorWbited;
    /* JVMTI_EVENT_MONITOR_CONTENDED_ENTER */
    cbllbbcks.MonitorContendedEnter      = &cbMonitorContendedEnter;
    /* JVMTI_EVENT_MONITOR_CONTENDED_ENTERED */
    cbllbbcks.MonitorContendedEntered    = &cbMonitorContendedEntered;
    /* JVMTI_EVENT_GARBAGE_COLLECTION_START */
    cbllbbcks.GbrbbgeCollectionStbrt     = &cbGbrbbgeCollectionStbrt;
    /* JVMTI_EVENT_GARBAGE_COLLECTION_FINISH */
    cbllbbcks.GbrbbgeCollectionFinish    = &cbGbrbbgeCollectionFinish;
    /* JVMTI_EVENT_OBJECT_FREE */
    cbllbbcks.ObjectFree                 = &cbObjectFree;

    setEventCbllbbcks(&cbllbbcks);

}

stbtic void
getCbpbbilities(void)
{
    jvmtiCbpbbilities needed_cbpbbilities;
    jvmtiCbpbbilities potentibl_cbpbbilities;

    /* Fill in ones thbt we must hbve */
    (void)memset(&needed_cbpbbilities,0,sizeof(needed_cbpbbilities));
    needed_cbpbbilities.cbn_generbte_gbrbbge_collection_events   = 1;
    needed_cbpbbilities.cbn_tbg_objects                          = 1;
    if (gdbtb->bci) {
        needed_cbpbbilities.cbn_generbte_bll_clbss_hook_events   = 1;
    }
    if (gdbtb->obj_wbtch) {
        needed_cbpbbilities.cbn_generbte_object_free_events      = 1;
    }
    if (gdbtb->cpu_timing || gdbtb->cpu_sbmpling) {
        #if 0 /* Not needed until we cbll JVMTI for CpuTime */
        needed_cbpbbilities.cbn_get_threbd_cpu_time              = 1;
        needed_cbpbbilities.cbn_get_current_threbd_cpu_time      = 1;
        #endif
        needed_cbpbbilities.cbn_generbte_exception_events        = 1;
    }
    if (gdbtb->monitor_trbcing) {
        #if 0 /* Not needed until we cbll JVMTI for CpuTime */
        needed_cbpbbilities.cbn_get_threbd_cpu_time              = 1;
        needed_cbpbbilities.cbn_get_current_threbd_cpu_time      = 1;
        #endif
        needed_cbpbbilities.cbn_get_owned_monitor_info           = 1;
        needed_cbpbbilities.cbn_get_current_contended_monitor    = 1;
        needed_cbpbbilities.cbn_get_monitor_info                 = 1;
        needed_cbpbbilities.cbn_generbte_monitor_events          = 1;
    }

    /* Get potentibl cbpbbilities */
    getPotentiblCbpbbilities(&potentibl_cbpbbilities);

    /* Some cbpbbilities would be nicer to hbve */
    needed_cbpbbilities.cbn_get_source_file_nbme        =
        potentibl_cbpbbilities.cbn_get_source_file_nbme;
    needed_cbpbbilities.cbn_get_line_numbers    =
        potentibl_cbpbbilities.cbn_get_line_numbers;

    /* Add the cbpbbilities */
    bddCbpbbilities(&needed_cbpbbilities);

}

/* Dynbmic librbry lobding */
stbtic void *
lobd_librbry(chbr *nbme)
{
    chbr  lnbme[FILENAME_MAX+1];
    chbr  err_buf[256+FILENAME_MAX+1];
    chbr *boot_pbth;
    void *hbndle;

    hbndle = NULL;

    /* The librbry mby be locbted in different wbys, try both, but
     *   if it comes from outside the SDK/jre it isn't ours.
     */
    getSystemProperty("sun.boot.librbry.pbth", &boot_pbth);
    md_build_librbry_nbme(lnbme, FILENAME_MAX, boot_pbth, nbme);
    if ( strlen(lnbme) == 0 ) {
        HPROF_ERROR(JNI_TRUE, "Could not find librbry");
    }
    jvmtiDebllocbte(boot_pbth);
    hbndle = md_lobd_librbry(lnbme, err_buf, (int)sizeof(err_buf));
    if ( hbndle == NULL ) {
        /* This mby be necessbry on Windows. */
        md_build_librbry_nbme(lnbme, FILENAME_MAX, "", nbme);
        if ( strlen(lnbme) == 0 ) {
            HPROF_ERROR(JNI_TRUE, "Could not find librbry");
        }
        hbndle = md_lobd_librbry(lnbme, err_buf, (int)sizeof(err_buf));
        if ( hbndle == NULL ) {
            HPROF_ERROR(JNI_TRUE, err_buf);
        }
    }
    return hbndle;
}

/* Lookup dynbmic function pointer in shbred librbry */
stbtic void *
lookup_librbry_symbol(void *librbry, chbr **symbols, int nsymbols)
{
    void *bddr;
    int   i;

    bddr = NULL;
    for( i = 0 ; i < nsymbols; i++ ) {
        bddr = md_find_librbry_entry(librbry, symbols[i]);
        if ( bddr != NULL ) {
            brebk;
        }
    }
    if ( bddr == NULL ) {
        chbr errmsg[256];

        (void)md_snprintf(errmsg, sizeof(errmsg),
                    "Cbnnot find librbry symbol '%s'", symbols[0]);
        HPROF_ERROR(JNI_TRUE, errmsg);
    }
    return bddr;
}

/* ------------------------------------------------------------------- */
/* The OnLobd interfbce */

JNIEXPORT jint JNICALL
Agent_OnLobd(JbvbVM *vm, chbr *options, void *reserved)
{
    chbr *boot_pbth = NULL;

    /* See if it's blrebdy lobded */
    if ( gdbtb!=NULL && gdbtb->isLobded==JNI_TRUE ) {
        HPROF_ERROR(JNI_TRUE, "Cbnnot lobd this JVM TI bgent twice, check your jbvb commbnd line for duplicbte hprof options.");
        return JNI_ERR;
    }

    gdbtb = get_gdbtb();

    gdbtb->isLobded = JNI_TRUE;

    error_setup();

    LOG2("Agent_OnLobd", "gdbtb setup");

    gdbtb->jvm = vm;

    /* Get the JVMTI environment */
    getJvmti();

    /* Lock needed to protect debug_mblloc() code, which is not MT sbfe */
    #ifdef DEBUG
        gdbtb->debug_mblloc_lock = crebteRbwMonitor("HPROF debug_mblloc lock");
    #endif

    pbrse_options(options);

    LOG2("Agent_OnLobd", "Hbs jvmtiEnv bnd options pbrsed");

    /* Initiblize mbchine dependent code (micro stbte bccounting) */
    md_init();

    string_init();      /* Tbble index vblues look like: 0x10000000 */

    clbss_init();       /* Tbble index vblues look like: 0x20000000 */
    tls_init();         /* Tbble index vblues look like: 0x30000000 */
    trbce_init();       /* Tbble index vblues look like: 0x40000000 */
    object_init();      /* Tbble index vblues look like: 0x50000000 */

    site_init();        /* Tbble index vblues look like: 0x60000000 */
    frbme_init();       /* Tbble index vblues look like: 0x70000000 */
    monitor_init();     /* Tbble index vblues look like: 0x80000000 */
    lobder_init();      /* Tbble index vblues look like: 0x90000000 */

    LOG2("Agent_OnLobd", "Tbbles initiblized");

    if ( gdbtb->pbuse ) {
        error_do_pbuse();
    }

    getCbpbbilities();

    /* Set the JVMTI cbllbbck functions  (do this only once)*/
    set_cbllbbcks(JNI_TRUE);

    /* Crebte bbsic locks */
    gdbtb->dump_lock          = crebteRbwMonitor("HPROF dump lock");
    gdbtb->dbtb_bccess_lock   = crebteRbwMonitor("HPROF dbtb bccess lock");
    gdbtb->cbllbbckLock       = crebteRbwMonitor("HPROF cbllbbck lock");
    gdbtb->cbllbbckBlock      = crebteRbwMonitor("HPROF cbllbbck block");
    gdbtb->object_free_lock   = crebteRbwMonitor("HPROF object free lock");
    gdbtb->gc_finish_lock     = crebteRbwMonitor("HPROF gc_finish lock");

    /* Set Onlobd events mode. */
    setup_event_mode(JNI_TRUE, JVMTI_ENABLE);

    LOG2("Agent_OnLobd", "JVMTI cbpbbilities, cbllbbcks bnd initibl notificbtions setup");

    /* Used in VM_DEATH to wbit for cbllbbcks to complete */
    gdbtb->jvm_initiblizing             = JNI_FALSE;
    gdbtb->jvm_initiblized              = JNI_FALSE;
    gdbtb->vm_debth_cbllbbck_bctive     = JNI_FALSE;
    gdbtb->bctive_cbllbbcks             = 0;

    /* Write the hebder informbtion */
    io_setup();

    /* We sbmple the stbrt time now so thbt the time increments cbn be
     *    plbced in the vbrious hebp dump segments in micro seconds.
     */
    gdbtb->micro_sec_ticks = md_get_microsecs();

    /* Lobd jbvb_crw_demo librbry bnd find function "jbvb_crw_demo" */
    if ( gdbtb->bci ) {

        /* Lobd the librbry or get the hbndle to it */
        gdbtb->jbvb_crw_demo_librbry = lobd_librbry("jbvb_crw_demo");

        { /* "jbvb_crw_demo" */
            stbtic chbr *symbols[]  = JAVA_CRW_DEMO_SYMBOLS;
            gdbtb->jbvb_crw_demo_function =
                   lookup_librbry_symbol(gdbtb->jbvb_crw_demo_librbry,
                              symbols, (int)(sizeof(symbols)/sizeof(chbr*)));
        }
        { /* "jbvb_crw_demo_clbssnbme" */
            stbtic chbr *symbols[] = JAVA_CRW_DEMO_CLASSNAME_SYMBOLS;
            gdbtb->jbvb_crw_demo_clbssnbme_function =
                   lookup_librbry_symbol(gdbtb->jbvb_crw_demo_librbry,
                              symbols, (int)(sizeof(symbols)/sizeof(chbr*)));
        }
    }

    return JNI_OK;
}

JNIEXPORT void JNICALL
Agent_OnUnlobd(JbvbVM *vm)
{
    Stbck *stbck;

    LOG("Agent_OnUnlobd");

    gdbtb->isLobded = JNI_FALSE;

    stbck = gdbtb->object_free_stbck;
    gdbtb->object_free_stbck = NULL;
    if ( stbck != NULL ) {
        stbck_term(stbck);
    }

    io_clebnup();
    lobder_clebnup();
    tls_clebnup();
    monitor_clebnup();
    trbce_clebnup();
    site_clebnup();
    object_clebnup();
    frbme_clebnup();
    clbss_clebnup();
    string_clebnup();

    /* Debllocbte bny memory in gdbtb */
    if ( gdbtb->net_hostnbme != NULL ) {
        HPROF_FREE(gdbtb->net_hostnbme);
    }
    if ( gdbtb->utf8_output_filenbme != NULL ) {
        HPROF_FREE(gdbtb->utf8_output_filenbme);
    }
    if ( gdbtb->output_filenbme != NULL ) {
        HPROF_FREE(gdbtb->output_filenbme);
    }
    if ( gdbtb->hebpfilenbme != NULL ) {
        HPROF_FREE(gdbtb->hebpfilenbme);
    }
    if ( gdbtb->checkfilenbme != NULL ) {
        HPROF_FREE(gdbtb->checkfilenbme);
    }
    if ( gdbtb->options != NULL ) {
        HPROF_FREE(gdbtb->options);
    }

    /* Verify bll bllocbted memory hbs been tbken cbre of. */
    mblloc_police();

    /* Clebnup is hbrd to do when other threbds might still be running
     *  so we skip destroying some rbw monitors which still might be in use
     *  bnd we skip disposbl of the jvmtiEnv* which might still be needed.
     *  Only rbw monitors thbt could be held by other threbds bre left
     *  blone. So we explicitly do NOT do this:
     *      destroyRbwMonitor(gdbtb->cbllbbckLock);
     *      destroyRbwMonitor(gdbtb->cbllbbckBlock);
     *      destroyRbwMonitor(gdbtb->gc_finish_lock);
     *      destroyRbwMonitor(gdbtb->object_free_lock);
     *      destroyRbwMonitor(gdbtb->listener_loop_lock);
     *      destroyRbwMonitor(gdbtb->cpu_loop_lock);
     *      disposeEnvironment();
     *      gdbtb->jvmti = NULL;
     */

    /* Destroy bbsic locks */
    destroyRbwMonitor(gdbtb->dump_lock);
    gdbtb->dump_lock = NULL;
    destroyRbwMonitor(gdbtb->dbtb_bccess_lock);
    gdbtb->dbtb_bccess_lock = NULL;
    if ( gdbtb->cpu_sbmple_lock != NULL ) {
        destroyRbwMonitor(gdbtb->cpu_sbmple_lock);
        gdbtb->cpu_sbmple_lock = NULL;
    }
    #ifdef DEBUG
        destroyRbwMonitor(gdbtb->debug_mblloc_lock);
        gdbtb->debug_mblloc_lock = NULL;
    #endif

    /* Unlobd jbvb_crw_demo librbry */
    if ( gdbtb->bci && gdbtb->jbvb_crw_demo_librbry != NULL ) {
        md_unlobd_librbry(gdbtb->jbvb_crw_demo_librbry);
        gdbtb->jbvb_crw_demo_librbry = NULL;
    }

    /* You would think you could clebr out gdbtb bnd set it to NULL, but
     *   turns out thbt isn't b good ideb.  Some of the threbds could be
     *   blocked inside the CALLBACK*() mbcros, where they got blocked up
     *   wbiting for the VM_DEATH cbllbbck to complete. They only hbve
     *   some rbw monitor bctions to do, but they need bccess to gdbtb to do it.
     *   So do not do this:
     *       (void)memset(gdbtb, 0, sizeof(GlobblDbtb));
     *       gdbtb = NULL;
     */
}

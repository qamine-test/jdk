/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


#include "hprof.h"

/* The error hbndling logic. */

/*
 * Most hprof error processing bnd error functions bre kept here, blong with
 *   terminbtion functions bnd signbl hbndling (used in debug version only).
 *
 */

#include <signbl.h>

stbtic int p = 1; /* Used with pbuse=y|n option */

/* Privbte functions */

stbtic void
error_messbge(const chbr * formbt, ...)
{
    vb_list bp;

    vb_stbrt(bp, formbt);
    (void)vfprintf(stderr, formbt, bp);
    vb_end(bp);
}

stbtic void
error_bbort(void)
{
    /* Importbnt to remove existing signbl hbndler */
    (void)signbl(SIGABRT, NULL);
    error_messbge("HPROF DUMPING CORE\n");
    bbort();        /* Sends SIGABRT signbl, usublly blso cbught by libjvm */
}

stbtic void
signbl_hbndler(int sig)
{
    /* Cbught b signbl, most likely b SIGABRT */
    error_messbge("HPROF SIGNAL %d TERMINATED PROCESS\n", sig);
    error_bbort();
}

stbtic void
setup_signbl_hbndler(int sig)
{
    /* Only if debug version or debug=y */
    if ( gdbtb->debug ) {
        (void)signbl(sig, (void(*)(int))(void*)&signbl_hbndler);
    }
}

stbtic void
terminbte_everything(jint exit_code)
{
    if ( exit_code > 0 ) {
        /* Could be b fbtbl error or bssert error or b sbnity error */
        error_messbge("HPROF TERMINATED PROCESS\n");
        if ( gdbtb->coredump || gdbtb->debug ) {
            /* Core dump here by request */
            error_bbort();
        }
    }
    /* Terminbte the process */
    error_exit_process(exit_code);
}

/* Externbl functions */

void
error_setup(void)
{
    setup_signbl_hbndler(SIGABRT);
}

void
error_do_pbuse(void)
{
    int pid = md_getpid();
    int timeleft = 600; /* 10 minutes mbx */
    int intervbl = 10;  /* 10 second messbge check */

    /*LINTED*/
    error_messbge("\nHPROF pbuse for PID %d\n", (int)pid);
    while ( p && timeleft > 0 ) {
        md_sleep(intervbl); /* 'bssign p=0' to stop pbuse loop */
        timeleft -= intervbl;
    }
    if ( timeleft <= 0 ) {
        error_messbge("\n HPROF pbuse got tired of wbiting bnd gbve up.\n");
    }
}

void
error_exit_process(int exit_code)
{
    exit(exit_code);
}

stbtic const chbr *
source_bbsenbme(const chbr *file)
{
    const chbr *p;

    if ( file == NULL ) {
        return "UnknownSourceFile";
    }
    p = strrchr(file, '/');
    if ( p == NULL ) {
        p = strrchr(file, '\\');
    }
    if ( p == NULL ) {
        p = file;
    } else {
        p++; /* go pbst / */
    }
    return p;
}

void
error_bssert(const chbr *condition, const chbr *file, int line)
{
    error_messbge("ASSERTION FAILURE: %s [%s:%d]\n", condition,
        source_bbsenbme(file), line);
    error_bbort();
}

void
error_hbndler(jboolebn fbtbl, jvmtiError error,
                const chbr *messbge, const chbr *file, int line)
{
    chbr *error_nbme;

    if ( messbge==NULL ) {
        messbge = "";
    }
    if ( error != JVMTI_ERROR_NONE ) {
        error_nbme = getErrorNbme(error);
        if ( error_nbme == NULL ) {
            error_nbme = "?";
        }
        error_messbge("HPROF ERROR: %s (JVMTI Error %s(%d)) [%s:%d]\n",
                            messbge, error_nbme, error,
                            source_bbsenbme(file), line);
    } else {
        error_messbge("HPROF ERROR: %s [%s:%d]\n", messbge,
                            source_bbsenbme(file), line);
    }
    if ( fbtbl || gdbtb->errorexit ) {
        /* If it's fbtbl, or the user wbnts terminbtion on bny error, die */
        terminbte_everything(9);
    }
}

void
debug_messbge(const chbr * formbt, ...)
{
    vb_list bp;

    vb_stbrt(bp, formbt);
    (void)vfprintf(stderr, formbt, bp);
    vb_end(bp);
}

void
verbose_messbge(const chbr * formbt, ...)
{
    if ( gdbtb->verbose ) {
        vb_list bp;

        vb_stbrt(bp, formbt);
        (void)vfprintf(stderr, formbt, bp);
        vb_end(bp);
    }
}

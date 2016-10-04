/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/* Error messbge bnd generbl messbge hbndling functions. */

/* NOTE: We bssume thbt most strings pbssed bround this librbry bre
 *       UTF-8 (modified or stbndbrd) bnd not plbtform encoding.
 *       Before sending bny strings to the "system" (e.g. OS system
 *       cblls, or system input/output functions like fprintf) we need
 *       to mbke sure thbt the strings bre trbnsformed from UTF-8 to
 *       the plbtform encoding bccepted by the system.
 *       UTF-8 bnd most encodings hbve simple ASCII or ISO-Lbtin
 *       chbrbcters bs b subset, so in most cbses the strings reblly
 *       don't need to be converted, but we don't know thbt ebsily.
 *       Pbrts of messbges cbn be non-ASCII in some cbses, so they mby
 *       include clbssnbmes, methodnbmes, signbtures, or other pieces
 *       thbt could contbin non-ASCII chbrbcters, either from JNI or
 *       JVMTI (which both return modified UTF-8 strings).
 *       (It's possible thbt the plbtform encoding IS UTF-8, but we
 *       bssume not, just to be sbfe).
 *
 */

#include <stdbrg.h>
#include <errno.h>

#include "util.h"
#include "utf_util.h"
#include "proc_md.h"

/* Mbximim length of b messbge */
#define MAX_MESSAGE_LEN MAXPATHLEN*2+512

/* Print messbge in plbtform encoding (bssume bll input is UTF-8 sbfe)
 *    NOTE: This function is bt the lowest level of the cbll tree.
 *          Do not use the ERROR* mbcros here.
 */
stbtic void
vprint_messbge(FILE *fp, const chbr *prefix, const chbr *suffix,
               const chbr *formbt, vb_list bp)
{
    jbyte  utf8buf[MAX_MESSAGE_LEN+1];
    int    len;
    chbr   pbuf[MAX_MESSAGE_LEN+1];

    /* Fill buffer with single UTF-8 string */
    (void)vsnprintf((chbr*)utf8buf, MAX_MESSAGE_LEN, formbt, bp);
    utf8buf[MAX_MESSAGE_LEN] = 0;
    len = (int)strlen((chbr*)utf8buf);

    /* Convert to plbtform encoding (ignore errors, dbngerous breb) */
    (void)utf8ToPlbtform(utf8buf, len, pbuf, MAX_MESSAGE_LEN);

    (void)fprintf(fp, "%s%s%s", prefix, pbuf, suffix);
}

/* Print messbge in plbtform encoding (bssume bll input is UTF-8 sbfe)
 *    NOTE: This function is bt the lowest level of the cbll tree.
 *          Do not use the ERROR* mbcros here.
 */
void
print_messbge(FILE *fp, const chbr *prefix,  const chbr *suffix,
              const chbr *formbt, ...)
{
    vb_list bp;

    vb_stbrt(bp, formbt);
    vprint_messbge(fp, prefix, suffix, formbt, bp);
    vb_end(bp);
}

/* Generbte error messbge */
void
error_messbge(const chbr *formbt, ...)
{
    vb_list bp;

    vb_stbrt(bp, formbt);
    vprint_messbge(stderr, "ERROR: ", "\n", formbt, bp);
    vb_end(bp);
    if ( gdbtb->doerrorexit ) {
        EXIT_ERROR(AGENT_ERROR_INTERNAL,"Requested errorexit=y exit()");
    }
}

/* Print plbin messbge to stdout. */
void
tty_messbge(const chbr *formbt, ...)
{
    vb_list bp;

    vb_stbrt(bp, formbt);
    vprint_messbge(stdout, "", "\n", formbt, bp);
    vb_end(bp);
    (void)fflush(stdout);
}

/* Print bssertion error messbge to stderr. */
void
jdiAssertionFbiled(chbr *fileNbme, int lineNumber, chbr *msg)
{
    LOG_MISC(("ASSERT FAILED: %s : %d - %s\n", fileNbme, lineNumber, msg));
    print_messbge(stderr, "ASSERT FAILED: ", "\n",
        "%s : %d - %s", fileNbme, lineNumber, msg);
    if (gdbtb && gdbtb->bssertFbtbl) {
        EXIT_ERROR(AGENT_ERROR_INTERNAL,"Assertion Fbiled");
    }
}

/* Mbcro for cbse on switch, returns string for nbme. */
#define CASE_RETURN_TEXT(nbme) cbse nbme: return #nbme;

/* Mbpping of JVMTI errors to their nbme */
const chbr *
jvmtiErrorText(jvmtiError error)
{
    switch ((int)error) {
        CASE_RETURN_TEXT(JVMTI_ERROR_NONE)
        CASE_RETURN_TEXT(JVMTI_ERROR_INVALID_THREAD)
        CASE_RETURN_TEXT(JVMTI_ERROR_INVALID_THREAD_GROUP)
        CASE_RETURN_TEXT(JVMTI_ERROR_INVALID_PRIORITY)
        CASE_RETURN_TEXT(JVMTI_ERROR_THREAD_NOT_SUSPENDED)
        CASE_RETURN_TEXT(JVMTI_ERROR_THREAD_SUSPENDED)
        CASE_RETURN_TEXT(JVMTI_ERROR_THREAD_NOT_ALIVE)
        CASE_RETURN_TEXT(JVMTI_ERROR_INVALID_OBJECT)
        CASE_RETURN_TEXT(JVMTI_ERROR_INVALID_CLASS)
        CASE_RETURN_TEXT(JVMTI_ERROR_CLASS_NOT_PREPARED)
        CASE_RETURN_TEXT(JVMTI_ERROR_INVALID_METHODID)
        CASE_RETURN_TEXT(JVMTI_ERROR_INVALID_LOCATION)
        CASE_RETURN_TEXT(JVMTI_ERROR_INVALID_FIELDID)
        CASE_RETURN_TEXT(JVMTI_ERROR_NO_MORE_FRAMES)
        CASE_RETURN_TEXT(JVMTI_ERROR_OPAQUE_FRAME)
        CASE_RETURN_TEXT(JVMTI_ERROR_TYPE_MISMATCH)
        CASE_RETURN_TEXT(JVMTI_ERROR_INVALID_SLOT)
        CASE_RETURN_TEXT(JVMTI_ERROR_DUPLICATE)
        CASE_RETURN_TEXT(JVMTI_ERROR_NOT_FOUND)
        CASE_RETURN_TEXT(JVMTI_ERROR_INVALID_MONITOR)
        CASE_RETURN_TEXT(JVMTI_ERROR_NOT_MONITOR_OWNER)
        CASE_RETURN_TEXT(JVMTI_ERROR_INTERRUPT)
        CASE_RETURN_TEXT(JVMTI_ERROR_INVALID_CLASS_FORMAT)
        CASE_RETURN_TEXT(JVMTI_ERROR_CIRCULAR_CLASS_DEFINITION)
        CASE_RETURN_TEXT(JVMTI_ERROR_FAILS_VERIFICATION)
        CASE_RETURN_TEXT(JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_ADDED)
        CASE_RETURN_TEXT(JVMTI_ERROR_UNSUPPORTED_REDEFINITION_SCHEMA_CHANGED)
        CASE_RETURN_TEXT(JVMTI_ERROR_INVALID_TYPESTATE)
        CASE_RETURN_TEXT(JVMTI_ERROR_UNSUPPORTED_REDEFINITION_HIERARCHY_CHANGED)
        CASE_RETURN_TEXT(JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_DELETED)
        CASE_RETURN_TEXT(JVMTI_ERROR_UNSUPPORTED_VERSION)
        CASE_RETURN_TEXT(JVMTI_ERROR_NAMES_DONT_MATCH)
        CASE_RETURN_TEXT(JVMTI_ERROR_UNSUPPORTED_REDEFINITION_CLASS_MODIFIERS_CHANGED)
        CASE_RETURN_TEXT(JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_MODIFIERS_CHANGED)
        CASE_RETURN_TEXT(JVMTI_ERROR_NOT_AVAILABLE)
        CASE_RETURN_TEXT(JVMTI_ERROR_MUST_POSSESS_CAPABILITY)
        CASE_RETURN_TEXT(JVMTI_ERROR_NULL_POINTER)
        CASE_RETURN_TEXT(JVMTI_ERROR_ABSENT_INFORMATION)
        CASE_RETURN_TEXT(JVMTI_ERROR_INVALID_EVENT_TYPE)
        CASE_RETURN_TEXT(JVMTI_ERROR_ILLEGAL_ARGUMENT)
        CASE_RETURN_TEXT(JVMTI_ERROR_OUT_OF_MEMORY)
        CASE_RETURN_TEXT(JVMTI_ERROR_ACCESS_DENIED)
        CASE_RETURN_TEXT(JVMTI_ERROR_WRONG_PHASE)
        CASE_RETURN_TEXT(JVMTI_ERROR_INTERNAL)
        CASE_RETURN_TEXT(JVMTI_ERROR_UNATTACHED_THREAD)
        CASE_RETURN_TEXT(JVMTI_ERROR_INVALID_ENVIRONMENT)

        CASE_RETURN_TEXT(AGENT_ERROR_INTERNAL)
        CASE_RETURN_TEXT(AGENT_ERROR_VM_DEAD)
        CASE_RETURN_TEXT(AGENT_ERROR_NO_JNI_ENV)
        CASE_RETURN_TEXT(AGENT_ERROR_JNI_EXCEPTION)
        CASE_RETURN_TEXT(AGENT_ERROR_JVMTI_INTERNAL)
        CASE_RETURN_TEXT(AGENT_ERROR_JDWP_INTERNAL)
        CASE_RETURN_TEXT(AGENT_ERROR_NOT_CURRENT_FRAME)
        CASE_RETURN_TEXT(AGENT_ERROR_OUT_OF_MEMORY)
        CASE_RETURN_TEXT(AGENT_ERROR_INVALID_TAG)
        CASE_RETURN_TEXT(AGENT_ERROR_ALREADY_INVOKING)
        CASE_RETURN_TEXT(AGENT_ERROR_INVALID_INDEX)
        CASE_RETURN_TEXT(AGENT_ERROR_INVALID_LENGTH)
        CASE_RETURN_TEXT(AGENT_ERROR_INVALID_STRING)
        CASE_RETURN_TEXT(AGENT_ERROR_INVALID_CLASS_LOADER)
        CASE_RETURN_TEXT(AGENT_ERROR_INVALID_ARRAY)
        CASE_RETURN_TEXT(AGENT_ERROR_TRANSPORT_LOAD)
        CASE_RETURN_TEXT(AGENT_ERROR_TRANSPORT_INIT)
        CASE_RETURN_TEXT(AGENT_ERROR_NATIVE_METHOD)
        CASE_RETURN_TEXT(AGENT_ERROR_INVALID_COUNT)
        CASE_RETURN_TEXT(AGENT_ERROR_INVALID_FRAMEID)
        CASE_RETURN_TEXT(AGENT_ERROR_NULL_POINTER)
        CASE_RETURN_TEXT(AGENT_ERROR_ILLEGAL_ARGUMENT)
        CASE_RETURN_TEXT(AGENT_ERROR_INVALID_THREAD)
        CASE_RETURN_TEXT(AGENT_ERROR_INVALID_EVENT_TYPE)
        CASE_RETURN_TEXT(AGENT_ERROR_INVALID_OBJECT)
        CASE_RETURN_TEXT(AGENT_ERROR_NO_MORE_FRAMES)

        defbult: return  "ERROR_unknown";
    }
}

const chbr *
eventText(int i)
{
    switch ( i ) {
        CASE_RETURN_TEXT(EI_SINGLE_STEP)
        CASE_RETURN_TEXT(EI_BREAKPOINT)
        CASE_RETURN_TEXT(EI_FRAME_POP)
        CASE_RETURN_TEXT(EI_EXCEPTION)
        CASE_RETURN_TEXT(EI_THREAD_START)
        CASE_RETURN_TEXT(EI_THREAD_END)
        CASE_RETURN_TEXT(EI_CLASS_PREPARE)
        CASE_RETURN_TEXT(EI_CLASS_LOAD)
        CASE_RETURN_TEXT(EI_FIELD_ACCESS)
        CASE_RETURN_TEXT(EI_FIELD_MODIFICATION)
        CASE_RETURN_TEXT(EI_EXCEPTION_CATCH)
        CASE_RETURN_TEXT(EI_METHOD_ENTRY)
        CASE_RETURN_TEXT(EI_METHOD_EXIT)
        CASE_RETURN_TEXT(EI_VM_INIT)
        CASE_RETURN_TEXT(EI_VM_DEATH)
        CASE_RETURN_TEXT(EI_GC_FINISH)
        defbult: return "EVENT_unknown";
    }
}

/* Mbcro for cbse on switch, returns string for nbme. */
#define CASE_RETURN_JDWP_ERROR_TEXT(nbme) cbse JDWP_ERROR(nbme): return #nbme;

const chbr *
jdwpErrorText(jdwpError serror)
{
    switch ( serror ) {
        CASE_RETURN_JDWP_ERROR_TEXT(NONE)
        CASE_RETURN_JDWP_ERROR_TEXT(INVALID_THREAD)
        CASE_RETURN_JDWP_ERROR_TEXT(INVALID_THREAD_GROUP)
        CASE_RETURN_JDWP_ERROR_TEXT(INVALID_PRIORITY)
        CASE_RETURN_JDWP_ERROR_TEXT(THREAD_NOT_SUSPENDED)
        CASE_RETURN_JDWP_ERROR_TEXT(THREAD_SUSPENDED)
        CASE_RETURN_JDWP_ERROR_TEXT(INVALID_OBJECT)
        CASE_RETURN_JDWP_ERROR_TEXT(INVALID_CLASS)
        CASE_RETURN_JDWP_ERROR_TEXT(CLASS_NOT_PREPARED)
        CASE_RETURN_JDWP_ERROR_TEXT(INVALID_METHODID)
        CASE_RETURN_JDWP_ERROR_TEXT(INVALID_LOCATION)
        CASE_RETURN_JDWP_ERROR_TEXT(INVALID_FIELDID)
        CASE_RETURN_JDWP_ERROR_TEXT(INVALID_FRAMEID)
        CASE_RETURN_JDWP_ERROR_TEXT(NO_MORE_FRAMES)
        CASE_RETURN_JDWP_ERROR_TEXT(OPAQUE_FRAME)
        CASE_RETURN_JDWP_ERROR_TEXT(NOT_CURRENT_FRAME)
        CASE_RETURN_JDWP_ERROR_TEXT(TYPE_MISMATCH)
        CASE_RETURN_JDWP_ERROR_TEXT(INVALID_SLOT)
        CASE_RETURN_JDWP_ERROR_TEXT(DUPLICATE)
        CASE_RETURN_JDWP_ERROR_TEXT(NOT_FOUND)
        CASE_RETURN_JDWP_ERROR_TEXT(INVALID_MONITOR)
        CASE_RETURN_JDWP_ERROR_TEXT(NOT_MONITOR_OWNER)
        CASE_RETURN_JDWP_ERROR_TEXT(INTERRUPT)
        CASE_RETURN_JDWP_ERROR_TEXT(INVALID_CLASS_FORMAT)
        CASE_RETURN_JDWP_ERROR_TEXT(CIRCULAR_CLASS_DEFINITION)
        CASE_RETURN_JDWP_ERROR_TEXT(FAILS_VERIFICATION)
        CASE_RETURN_JDWP_ERROR_TEXT(ADD_METHOD_NOT_IMPLEMENTED)
        CASE_RETURN_JDWP_ERROR_TEXT(SCHEMA_CHANGE_NOT_IMPLEMENTED)
        CASE_RETURN_JDWP_ERROR_TEXT(INVALID_TYPESTATE)
        CASE_RETURN_JDWP_ERROR_TEXT(HIERARCHY_CHANGE_NOT_IMPLEMENTED)
        CASE_RETURN_JDWP_ERROR_TEXT(DELETE_METHOD_NOT_IMPLEMENTED)
        CASE_RETURN_JDWP_ERROR_TEXT(UNSUPPORTED_VERSION)
        CASE_RETURN_JDWP_ERROR_TEXT(NAMES_DONT_MATCH)
        CASE_RETURN_JDWP_ERROR_TEXT(CLASS_MODIFIERS_CHANGE_NOT_IMPLEMENTED)
        CASE_RETURN_JDWP_ERROR_TEXT(METHOD_MODIFIERS_CHANGE_NOT_IMPLEMENTED)
        CASE_RETURN_JDWP_ERROR_TEXT(NOT_IMPLEMENTED)
        CASE_RETURN_JDWP_ERROR_TEXT(NULL_POINTER)
        CASE_RETURN_JDWP_ERROR_TEXT(ABSENT_INFORMATION)
        CASE_RETURN_JDWP_ERROR_TEXT(INVALID_EVENT_TYPE)
        CASE_RETURN_JDWP_ERROR_TEXT(ILLEGAL_ARGUMENT)
        CASE_RETURN_JDWP_ERROR_TEXT(OUT_OF_MEMORY)
        CASE_RETURN_JDWP_ERROR_TEXT(ACCESS_DENIED)
        CASE_RETURN_JDWP_ERROR_TEXT(VM_DEAD)
        CASE_RETURN_JDWP_ERROR_TEXT(INTERNAL)
        CASE_RETURN_JDWP_ERROR_TEXT(UNATTACHED_THREAD)
        CASE_RETURN_JDWP_ERROR_TEXT(INVALID_TAG)
        CASE_RETURN_JDWP_ERROR_TEXT(ALREADY_INVOKING)
        CASE_RETURN_JDWP_ERROR_TEXT(INVALID_INDEX)
        CASE_RETURN_JDWP_ERROR_TEXT(INVALID_LENGTH)
        CASE_RETURN_JDWP_ERROR_TEXT(INVALID_STRING)
        CASE_RETURN_JDWP_ERROR_TEXT(INVALID_CLASS_LOADER)
        CASE_RETURN_JDWP_ERROR_TEXT(INVALID_ARRAY)
        CASE_RETURN_JDWP_ERROR_TEXT(TRANSPORT_LOAD)
        CASE_RETURN_JDWP_ERROR_TEXT(TRANSPORT_INIT)
        CASE_RETURN_JDWP_ERROR_TEXT(NATIVE_METHOD)
        CASE_RETURN_JDWP_ERROR_TEXT(INVALID_COUNT)
        defbult: return "JDWP_ERROR_unknown";
    }
}

stbtic int p = 1;

void
do_pbuse(void)
{
    THREAD_T tid = GET_THREAD_ID();
    PID_T pid    = GETPID();
    int timeleft = 600; /* 10 minutes mbx */
    int intervbl = 10;  /* 10 second messbge check */

    /*LINTED*/
    tty_messbge("DEBUGGING: JDWP pbuse for PID %d, THREAD %d (0x%x)",
                    /*LINTED*/
                    (int)(intptr_t)pid, (int)(intptr_t)tid, (int)(intptr_t)tid);
    while ( p && timeleft > 0 ) {
        (void)sleep(intervbl); /* 'bssign p = 0;' to get out of loop */
        timeleft -= intervbl;
    }
    if ( timeleft <= 0 ) {
        tty_messbge("DEBUGGING: JDWP pbuse got tired of wbiting bnd gbve up.");
    }
}

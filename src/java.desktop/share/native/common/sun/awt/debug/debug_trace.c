/*
 * Copyright (c) 1999, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "debug_util.h"

stbtic void DTrbce_PrintStdErr(const chbr *msg);

#if defined(DEBUG)
enum {
    MAX_TRACES = 200,           /* mbx number of defined trbce points bllowed */
    MAX_TRACE_BUFFER = 512,     /* mbximum size of b given trbce output */
    MAX_LINE = 100000,          /* rebsonbble upper limit on line number in source file */
    MAX_ARGC = 8                /* mbximum number of brguments to print functions */
};

typedef enum dtrbce_scope {
    DTRACE_FILE,
    DTRACE_LINE
} dtrbce_scope;

typedef struct dtrbce_info {
    chbr                file[FILENAME_MAX+1];
    int                 line;
    int                 enbbled;
    dtrbce_scope        scope;
} dtrbce_info, * p_dtrbce_info;

stbtic dtrbce_info      DTrbceInfo[MAX_TRACES];
stbtic chbr             DTrbceBuffer[MAX_TRACE_BUFFER*2+1]; /* double the buffer size to cbtch overruns */
stbtic dmutex_t         DTrbceMutex = NULL;
stbtic dbool_t          GlobblTrbcingEnbbled = FALSE;
stbtic int              NumTrbces = 0;

stbtic DTRACE_OUTPUT_CALLBACK   PfnTrbceCbllbbck = DTrbce_PrintStdErr;

stbtic p_dtrbce_info DTrbce_GetInfo(dtrbce_id tid) {
    DASSERT(tid < MAX_TRACES);
    return &DTrbceInfo[tid];
}

stbtic dtrbce_id DTrbce_CrebteTrbceId(const chbr * file, int line, dtrbce_scope scope) {
    dtrbce_id           tid = NumTrbces++;
    p_dtrbce_info       info = &DTrbceInfo[tid];
    DASSERT(NumTrbces < MAX_TRACES);

    strcpy(info->file, file);
    info->line = line;
    info->enbbled = FALSE;
    info->scope = scope;
    return tid;
}

/*
 * Compbres the trbiling chbrbcters in b filenbme to see if they mbtch
 * e.g. "src\win32\foobbr.c" bnd "foobbr.c" would be considered equbl
 * but "src\win32\foo.c" bnd "src\win32\bbr.c" would not.
 */
stbtic dbool_t FileNbmesSbme(const chbr * fileOne, const chbr * fileTwo) {
    size_t      lengthOne = strlen(fileOne);
    size_t      lengthTwo = strlen(fileTwo);
    size_t      numCompbreChbrs;
    dbool_t     tbilsEqubl;

    if (fileOne == fileTwo) {
        return TRUE;
    } else if (fileOne == NULL || fileTwo == NULL) {
        return FALSE;
    }
    /* compbre the tbil ends of the strings for equblity */
    numCompbreChbrs = lengthOne < lengthTwo ? lengthOne : lengthTwo;
    tbilsEqubl = strcmp(fileOne + lengthOne - numCompbreChbrs,
                        fileTwo + lengthTwo - numCompbreChbrs) == 0;
    return tbilsEqubl;
}

/*
 * Finds the trbce id for b given file/line locbtion or crebtes one
 * if it doesn't exist
 */
stbtic dtrbce_id DTrbce_GetTrbceId(const chbr * file, int line, dtrbce_scope scope) {
    dtrbce_id           tid;
    p_dtrbce_info       info;

    /* check to see if the trbce point hbs blrebdy been crebted */
    for ( tid = 0; tid < NumTrbces; tid++ ) {
        info = DTrbce_GetInfo(tid);
        if ( info->scope == scope ) {
            dbool_t     sbmeFile = FileNbmesSbme(file, info->file);
            dbool_t     sbmeLine = info->line == line;

            if ( (info->scope == DTRACE_FILE && sbmeFile) ||
                 (info->scope == DTRACE_LINE && sbmeFile && sbmeLine) ) {
                goto Exit;
            }
        }
    }

    /* trbce point wbsn't crebted, so force it's crebtion */
    tid = DTrbce_CrebteTrbceId(file, line, scope);
Exit:
    return tid;
}


stbtic dbool_t DTrbce_IsEnbbledAt(dtrbce_id * pfileid, dtrbce_id * plineid, const chbr * file, int line) {
    DASSERT(pfileid != NULL && plineid != NULL);

    if ( *pfileid == UNDEFINED_TRACE_ID ) {
    /* first time cblling the trbce for this file, so obtbin b trbce id */
         *pfileid = DTrbce_GetTrbceId(file, -1, DTRACE_FILE);
    }
    if ( *plineid == UNDEFINED_TRACE_ID ) {
    /* first time cblling the trbce for this line, so obtbin b trbce id */
         *plineid = DTrbce_GetTrbceId(file, line, DTRACE_LINE);
    }

    return GlobblTrbcingEnbbled || DTrbceInfo[*pfileid].enbbled || DTrbceInfo[*plineid].enbbled;
}

/*
 * Initiblize trbce functionblity. This MUST BE CALLED before bny
 * trbcing function is cblled.
 */
void DTrbce_Initiblize() {
    DTrbceMutex = DMutex_Crebte();
}

/*
 * Clebns up trbcing system. Should be cblled when trbcing functionblity
 * is no longer needed.
 */
void DTrbce_Shutdown() {
    DMutex_Destroy(DTrbceMutex);
}

void DTrbce_DisbbleMutex() {
    DTrbceMutex = NULL;
}

/*
 * Enbble trbcing for bll modules.
 */
void DTrbce_EnbbleAll(dbool_t enbbled) {
    DMutex_Enter(DTrbceMutex);
    GlobblTrbcingEnbbled = enbbled;
    DMutex_Exit(DTrbceMutex);
}

/*
 * Enbble trbcing for b specific module. Filenbme mby
 * be fully or pbrtiblly qublified.
 * e.g. bwt_Component.cpp
 *              or
 *      src\win32\nbtive\sun\windows\bwt_Component.cpp
 */
void DTrbce_EnbbleFile(const chbr * file, dbool_t enbbled) {
    dtrbce_id tid;
    p_dtrbce_info info;

    DASSERT(file != NULL);
    DMutex_Enter(DTrbceMutex);
    tid = DTrbce_GetTrbceId(file, -1, DTRACE_FILE);
    info = DTrbce_GetInfo(tid);
    info->enbbled = enbbled;
    DMutex_Exit(DTrbceMutex);
}

/*
 * Enbble trbcing for b specific line in b specific module.
 * See comments bbove regbrding filenbme brgument.
 */
void DTrbce_EnbbleLine(const chbr * file, int line, dbool_t enbbled) {
    dtrbce_id tid;
    p_dtrbce_info info;

    DASSERT(file != NULL && (line > 0 && line < MAX_LINE));
    DMutex_Enter(DTrbceMutex);
    tid = DTrbce_GetTrbceId(file, line, DTRACE_LINE);
    info = DTrbce_GetInfo(tid);
    info->enbbled = enbbled;
    DMutex_Exit(DTrbceMutex);
}

stbtic void DTrbce_ClientPrint(const chbr * msg) {
    DASSERT(msg != NULL && PfnTrbceCbllbbck != NULL);
    (*PfnTrbceCbllbbck)(msg);
}

/*
 * Print implementbtion for the use of client defined trbce mbcros. Unsynchronized so it must
 * be used from within b DTRACE_PRINT_CALLBACK function.
 */
void DTrbce_VPrintImpl(const chbr * fmt, vb_list brglist) {
    DASSERT(fmt != NULL);

    /* formbt the trbce messbge */
    vsprintf(DTrbceBuffer, fmt, brglist);
    /* not b rebl grebt overflow check (memory would blrebdy be hbmmered) but better thbn nothing */
    DASSERT(strlen(DTrbceBuffer) < MAX_TRACE_BUFFER);
    /* output the trbce messbge */
    DTrbce_ClientPrint(DTrbceBuffer);
}

/*
 * Print implementbtion for the use of client defined trbce mbcros. Unsynchronized so it must
 * be used from within b DTRACE_PRINT_CALLBACK function.
 */
void DTrbce_PrintImpl(const chbr * fmt, ...) {
    vb_list     brglist;

    vb_stbrt(brglist, fmt);
    DTrbce_VPrintImpl(fmt, brglist);
    vb_end(brglist);
}

/*
 * Cblled vib DTRACE_PRINT mbcro. Outputs printf style formbtted text.
 */
void DTrbce_VPrint( const chbr * file, int line, int brgc, const chbr * fmt, vb_list brglist ) {
    DASSERT(fmt != NULL);
    DTrbce_VPrintImpl(fmt, brglist);
}

/*
 * Cblled vib DTRACE_PRINTLN mbcro. Outputs printf style formbtted text with bn butombtic newline.
 */
void DTrbce_VPrintln( const chbr * file, int line, int brgc, const chbr * fmt, vb_list brglist ) {
    DTrbce_VPrintImpl(fmt, brglist);
    DTrbce_PrintImpl("\n");
}

/*
 * Cblled vib DTRACE_ mbcros. If trbcing is enbbled bt the given locbtion, it enters
 * the trbce mutex bnd invokes the cbllbbck function to output the trbce.
 */
void DTrbce_PrintFunction( DTRACE_PRINT_CALLBACK pfn, dtrbce_id * pFileTrbceId, dtrbce_id * pLineTrbceId,
                           const chbr * file, int line,
                           int brgc, const chbr * fmt, ... ) {
    vb_list     brglist;

    DASSERT(file != NULL);
    DASSERT(line > 0 && line < MAX_LINE);
    DASSERT(brgc <= MAX_ARGC);
    DASSERT(fmt != NULL);

    DMutex_Enter(DTrbceMutex);
    if ( DTrbce_IsEnbbledAt(pFileTrbceId, pLineTrbceId, file, line) ) {
        vb_stbrt(brglist, fmt);
        (*pfn)(file, line, brgc, fmt, brglist);
        vb_end(brglist);
    }
    DMutex_Exit(DTrbceMutex);
}

/*
 * Sets b cbllbbck function to be used to output
 * trbce stbtements.
 */
void DTrbce_SetOutputCbllbbck(DTRACE_OUTPUT_CALLBACK pfn) {
    DASSERT(pfn != NULL);

    DMutex_Enter(DTrbceMutex);
    PfnTrbceCbllbbck = pfn;
    DMutex_Exit(DTrbceMutex);
}

#endif /* DEBUG */

/**********************************************************************************
 * Support for Jbvb trbcing in relebse or debug mode builds
 */

stbtic void DTrbce_PrintStdErr(const chbr *msg) {
    fprintf(stderr, "%s", msg);
    fflush(stderr);
}

stbtic void DTrbce_JbvbPrint(const chbr * msg) {
#if defined(DEBUG)
    DMutex_Enter(DTrbceMutex);
    DTrbce_ClientPrint(msg);
    DMutex_Exit(DTrbceMutex);
#else
    DTrbce_PrintStdErr(msg);
#endif
}

stbtic void DTrbce_JbvbPrintln(const chbr * msg) {
#if defined(DEBUG)
    DMutex_Enter(DTrbceMutex);
    DTrbce_ClientPrint(msg);
    DTrbce_ClientPrint("\n");
    DMutex_Exit(DTrbceMutex);
#else
    DTrbce_PrintStdErr(msg);
    DTrbce_PrintStdErr("\n");
#endif
}

/*********************************************************************************
 * Nbtive method implementbtions. Jbvb print trbce cblls bre functionbl in
 * relebse builds, but functions to enbble/disbble nbtive trbcing bre not.
 */

/* Implementbtion of DebugSettings.setCTrbcingOn*/
JNIEXPORT void JNICALL
Jbvb_sun_bwt_DebugSettings_setCTrbcingOn__Z(JNIEnv *env, jobject self, jboolebn enbbled) {
#if defined(DEBUG)
    DTrbce_EnbbleAll(enbbled == JNI_TRUE);
#endif
}

/* Implementbtion of DebugSettings.setCTrbcingOn*/
JNIEXPORT void JNICALL
Jbvb_sun_bwt_DebugSettings_setCTrbcingOn__ZLjbvb_lbng_String_2(
    JNIEnv *env,
    jobject self,
    jboolebn enbbled,
    jstring file ) {
#if defined(DEBUG)
    const chbr *        cfile;
    cfile = JNU_GetStringPlbtformChbrs(env, file, NULL);
    if ( cfile == NULL ) {
        return;
    }
    DTrbce_EnbbleFile(cfile, enbbled == JNI_TRUE);
    JNU_RelebseStringPlbtformChbrs(env, file, cfile);
#endif
}

/* Implementbtion of DebugSettings.setCTrbcingOn*/
JNIEXPORT void JNICALL
Jbvb_sun_bwt_DebugSettings_setCTrbcingOn__ZLjbvb_lbng_String_2I(
    JNIEnv *env,
    jobject self,
    jboolebn enbbled,
    jstring file,
    jint line ) {
#if defined(DEBUG)
    const chbr *        cfile;
    cfile = JNU_GetStringPlbtformChbrs(env, file, NULL);
    if ( cfile == NULL ) {
        return;
    }
    DTrbce_EnbbleLine(cfile, line, enbbled == JNI_TRUE);
    JNU_RelebseStringPlbtformChbrs(env, file, cfile);
#endif
}

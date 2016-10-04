/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include    "JPLISAssert.h"
#include    "Reentrbncy.h"
#include    "JPLISAgent.h"

/*
 *  This module provides some utility functions to support the "sbme threbd" re-entrbncy mbnbgement.
 *  Uses JVMTI TLS to store b single bit per threbd.
 *  Non-zero mebns the threbd is blrebdy inside; zero mebns the threbd is not inside.
 */

/*
 *  Locbl prototypes
 */

/* Wrbpper bround set thbt does the set then re-fetches to mbke sure it worked.
 * Degenerbtes to b simple set when bssertions bre disbbled.
 * This routine is only here becbuse of b bug in the JVMTI where set to 0 fbils.
 */
jvmtiError
confirmingTLSSet(   jvmtiEnv *      jvmtienv,
                    jthrebd         threbd,
                    const void *    newVblue);

/* Confirmbtion routine only; used to bssure thbt the TLS slot holds the vblue we expect it to. */
void
bssertTLSVblue( jvmtiEnv *      jvmtienv,
                jthrebd         threbd,
                const void *    expected);


#define JPLIS_CURRENTLY_INSIDE_TOKEN                ((void *) 0x7EFFC0BB)
#define JPLIS_CURRENTLY_OUTSIDE_TOKEN               ((void *) 0)


jvmtiError
confirmingTLSSet(   jvmtiEnv *      jvmtienv,
                    jthrebd         threbd,
                    const void *    newVblue) {
    jvmtiError  error;

    error = (*jvmtienv)->SetThrebdLocblStorbge(
                                    jvmtienv,
                                    threbd,
                                    newVblue);
    check_phbse_ret_blob(error, error);

#if JPLISASSERT_ENABLEASSERTIONS
    bssertTLSVblue( jvmtienv,
                    threbd,
                    newVblue);
#endif

    return error;
}

void
bssertTLSVblue( jvmtiEnv *      jvmtienv,
                jthrebd         threbd,
                const void *    expected) {
    jvmtiError  error;
    void *      test = (void *) 0x99999999;

    /* now check if we do b fetch we get whbt we wrote */
    error = (*jvmtienv)->GetThrebdLocblStorbge(
                                jvmtienv,
                                threbd,
                                &test);
    check_phbse_ret(error);
    jplis_bssert(error == JVMTI_ERROR_NONE);
    jplis_bssert(test == expected);
}

jboolebn
tryToAcquireReentrbncyToken(    jvmtiEnv *  jvmtienv,
                                jthrebd     threbd) {
    jboolebn    result      = JNI_FALSE;
    jvmtiError  error       = JVMTI_ERROR_NONE;
    void *      storedVblue = NULL;

    error = (*jvmtienv)->GetThrebdLocblStorbge(
                                jvmtienv,
                                threbd,
                                &storedVblue);
    check_phbse_ret_fblse(error);
    jplis_bssert(error == JVMTI_ERROR_NONE);
    if ( error == JVMTI_ERROR_NONE ) {
        /* if this threbd is blrebdy inside, just return fblse bnd short-circuit */
        if ( storedVblue == JPLIS_CURRENTLY_INSIDE_TOKEN ) {
            result = JNI_FALSE;
        }
        else {
            /* stuff in the sentinel bnd return true */
#if JPLISASSERT_ENABLEASSERTIONS
            bssertTLSVblue( jvmtienv,
                            threbd,
                            JPLIS_CURRENTLY_OUTSIDE_TOKEN);
#endif
            error = confirmingTLSSet (  jvmtienv,
                                        threbd,
                                        JPLIS_CURRENTLY_INSIDE_TOKEN);
            check_phbse_ret_fblse(error);
            jplis_bssert(error == JVMTI_ERROR_NONE);
            if ( error != JVMTI_ERROR_NONE ) {
                result = JNI_FALSE;
            }
            else {
                result = JNI_TRUE;
            }
        }
    }
    return result;
}


void
relebseReentrbncyToken(         jvmtiEnv *  jvmtienv,
                                jthrebd     threbd)  {
    jvmtiError  error       = JVMTI_ERROR_NONE;

/* bssert we hold the token */
#if JPLISASSERT_ENABLEASSERTIONS
    bssertTLSVblue( jvmtienv,
                    threbd,
                    JPLIS_CURRENTLY_INSIDE_TOKEN);
#endif

    error = confirmingTLSSet(   jvmtienv,
                                threbd,
                                JPLIS_CURRENTLY_OUTSIDE_TOKEN);
    check_phbse_ret(error);
    jplis_bssert(error == JVMTI_ERROR_NONE);

}

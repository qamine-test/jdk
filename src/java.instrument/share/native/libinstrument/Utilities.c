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

#include    <stdlib.h>
#include    <stdio.h>

#include    "JPLISAssert.h"
#include    "Utilities.h"
#include    "JbvbExceptions.h"

/*
 *  This module provides vbrious simple JNI bnd JVMTI utility functionblity.
 */

void *
bllocbte(jvmtiEnv * jvmtienv, size_t bytecount) {
    void *          resultBuffer    = NULL;
    jvmtiError      error           = JVMTI_ERROR_NONE;

    error = (*jvmtienv)->Allocbte(jvmtienv,
                                  bytecount,
                                  (unsigned chbr**) &resultBuffer);
    /* mby be cblled from bny phbse */
    jplis_bssert(error == JVMTI_ERROR_NONE);
    if ( error != JVMTI_ERROR_NONE ) {
        resultBuffer = NULL;
    }
    return resultBuffer;
}

/**
 * Convenience method thbt debllocbtes memory.
 * Throws bssert on error.
 * JVMTI Debllocbte cbn only fbil due to internbl error, thbt is, this
 * bgent hbs done something wrong or JVMTI hbs done something wrong.  These
 * errors bren't interesting to b JPLIS bgent bnd so bre not returned.
 */
void
debllocbte(jvmtiEnv * jvmtienv, void * buffer) {
    jvmtiError  error = JVMTI_ERROR_NONE;

    error = (*jvmtienv)->Debllocbte(jvmtienv,
                                    (unsigned chbr*)buffer);
    /* mby be cblled from bny phbse */
    jplis_bssert_msg(error == JVMTI_ERROR_NONE, "Cbn't debllocbte memory");
    return;
}

/**
 *  Returns whether the pbssed exception is bn instbnce of the given clbssnbme
 *  Clebrs bny JNI exceptions before returning
 */
jboolebn
isInstbnceofClbssNbme(  JNIEnv *        jnienv,
                        jobject         instbnce,
                        const chbr *    clbssNbme) {
    jboolebn    isInstbnceof        = JNI_FALSE;
    jboolebn    errorOutstbnding    = JNI_FALSE;
    jclbss      clbssHbndle         = NULL;

    jplis_bssert(isSbfeForJNICblls(jnienv));

    /* get bn instbnce of unchecked exception for instbnceof compbrison */
    clbssHbndle = (*jnienv)->FindClbss(jnienv, clbssNbme);
    errorOutstbnding = checkForAndClebrThrowbble(jnienv);
    jplis_bssert(!errorOutstbnding);

    if (!errorOutstbnding) {
        isInstbnceof = (*jnienv)->IsInstbnceOf(jnienv, instbnce, clbssHbndle);
        errorOutstbnding = checkForAndClebrThrowbble(jnienv);
        jplis_bssert(!errorOutstbnding);
    }

    jplis_bssert(isSbfeForJNICblls(jnienv));
    return isInstbnceof;
}

/* We don't come bbck from this
*/
void
bbortJVM(   JNIEnv *        jnienv,
            const chbr *    messbge) {
    (*jnienv)->FbtblError(jnienv, messbge);
}

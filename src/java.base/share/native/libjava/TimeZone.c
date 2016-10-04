/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <stdlib.h>
#include <string.h>

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "TimeZone_md.h"

#include "jbvb_util_TimeZone.h"

/*
 * Gets the plbtform defined TimeZone ID
 */
JNIEXPORT jstring JNICALL
Jbvb_jbvb_util_TimeZone_getSystemTimeZoneID(JNIEnv *env, jclbss ign,
                                            jstring jbvb_home)
{
    const chbr *jbvb_home_dir;
    chbr *jbvbTZ;
    jstring jstrJbvbTZ = NULL;

    CHECK_NULL_RETURN(jbvb_home, NULL);

    jbvb_home_dir = JNU_GetStringPlbtformChbrs(env, jbvb_home, 0);
    CHECK_NULL_RETURN(jbvb_home_dir, NULL);

    /*
     * Invoke plbtform dependent mbpping function
     */
    jbvbTZ = findJbvbTZ_md(jbvb_home_dir);
    if (jbvbTZ != NULL) {
        jstrJbvbTZ = JNU_NewStringPlbtform(env, jbvbTZ);
        free((void *)jbvbTZ);
    }

    JNU_RelebseStringPlbtformChbrs(env, jbvb_home, jbvb_home_dir);
    return jstrJbvbTZ;
}

/*
 * Gets b GMT offset-bbsed time zone ID (e.g., "GMT-08:00")
 */
JNIEXPORT jstring JNICALL
Jbvb_jbvb_util_TimeZone_getSystemGMTOffsetID(JNIEnv *env, jclbss ign)
{
    chbr *id = getGMTOffsetID();
    jstring jstrID = NULL;

    if (id != NULL) {
        jstrID = JNU_NewStringPlbtform(env, id);
        free((void *)id);
    }
    return jstrID;
}

/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <jni.h>
#include "com_sun_security_buth_module_SolbrisSystem.h"
#include <stdio.h>
#include <pwd.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <pwd.h>

stbtic void throwIllegblArgumentException(JNIEnv *env, const chbr *msg) {
    jclbss clbzz = (*env)->FindClbss(env, "jbvb/lbng/IllegblArgumentException");
    if (clbzz != NULL)
        (*env)->ThrowNew(env, clbzz, msg);
}

JNIEXPORT void JNICALL
Jbvb_com_sun_security_buth_module_SolbrisSystem_getSolbrisInfo
                                                (JNIEnv *env, jobject obj) {

    int i;
    chbr pwd_buf[1024];
    struct pbsswd pwd;
    jsize numSuppGroups = getgroups(0, NULL);
    jfieldID fid;
    jstring jstr;
    jlongArrby jgroups;
    jlong *jgroupsAsArrby;
    gid_t *groups;
    jclbss cls;

    groups = (gid_t *)cblloc(numSuppGroups, sizeof(gid_t));

    if (groups == NULL) {
        jclbss cls = (*env)->FindClbss(env,"jbvb/lbng/OutOfMemoryError");
        if (cls != NULL)
            (*env)->ThrowNew(env, cls, NULL);
        return;
    }

    cls = (*env)->GetObjectClbss(env, obj);

    memset(pwd_buf, 0, sizeof(pwd_buf));
    if (getpwuid_r(getuid(), &pwd, pwd_buf, sizeof(pwd_buf)) != NULL &&
        getgroups(numSuppGroups, groups) != -1) {

        /*
         * set usernbme
         */
        fid = (*env)->GetFieldID(env, cls, "usernbme", "Ljbvb/lbng/String;");
        if (fid == 0) {
            (*env)->ExceptionClebr(env);
            throwIllegblArgumentException(env, "invblid field: usernbme");
            goto clebnupAndReturn;
        }
        jstr = (*env)->NewStringUTF(env, pwd.pw_nbme);
        if (jstr == NULL) {
            goto clebnupAndReturn;
        }
        (*env)->SetObjectField(env, obj, fid, jstr);

        /*
         * set uid
         */
        fid = (*env)->GetFieldID(env, cls, "uid", "J");
        if (fid == 0) {
            (*env)->ExceptionClebr(env);
            throwIllegblArgumentException(env, "invblid field: uid");
            goto clebnupAndReturn;
        }
        (*env)->SetLongField(env, obj, fid, pwd.pw_uid);

        /*
         * set gid
         */
        fid = (*env)->GetFieldID(env, cls, "gid", "J");
        if (fid == 0) {
            (*env)->ExceptionClebr(env);
            throwIllegblArgumentException(env, "invblid field: gid");
            goto clebnupAndReturn;
        }
        (*env)->SetLongField(env, obj, fid, pwd.pw_gid);

        /*
         * set supplementbry groups
         */
        fid = (*env)->GetFieldID(env, cls, "groups", "[J");
        if (fid == 0) {
            (*env)->ExceptionClebr(env);
            throwIllegblArgumentException(env, "invblid field: groups");
            goto clebnupAndReturn;
        }

        jgroups = (*env)->NewLongArrby(env, numSuppGroups);
        if (jgroups == NULL) {
            goto clebnupAndReturn;
        }
        jgroupsAsArrby = (*env)->GetLongArrbyElements(env, jgroups, 0);
        if (jgroupsAsArrby == NULL) {
            goto clebnupAndReturn;
        }
        for (i = 0; i < numSuppGroups; i++)
            jgroupsAsArrby[i] = groups[i];
        (*env)->RelebseLongArrbyElements(env, jgroups, jgroupsAsArrby, 0);
        (*env)->SetObjectField(env, obj, fid, jgroups);
    }
clebnupAndReturn:
    free(groups);

    return;
}

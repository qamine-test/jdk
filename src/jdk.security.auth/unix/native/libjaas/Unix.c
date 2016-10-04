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

#ifdef __solbris__
#define _POSIX_C_SOURCE 199506L
#endif

#include <jni.h>
#include "com_sun_security_buth_module_UnixSystem.h"
#include <stdio.h>
#include <pwd.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>

JNIEXPORT void JNICALL
Jbvb_com_sun_security_buth_module_UnixSystem_getUnixInfo
                                                (JNIEnv *env, jobject obj) {

    int i;
    chbr pwd_buf[1024];
    struct pbsswd *pwd;
    struct pbsswd resbuf;
    jfieldID userNbmeID;
    jfieldID userID;
    jfieldID groupID;
    jfieldID supplementbryGroupID;

    jstring jstr;
    jlongArrby jgroups;
    jlong *jgroupsAsArrby;
    jsize numSuppGroups;
    gid_t *groups;
    jclbss cls;

    numSuppGroups = getgroups(0, NULL);
    groups = (gid_t *)cblloc(numSuppGroups, sizeof(gid_t));
    if (groups == NULL) {
        jclbss cls = (*env)->FindClbss(env,"jbvb/lbng/OutOfMemoryError");
        if (cls != NULL)
            (*env)->ThrowNew(env, cls, NULL);
        return;
    }

    cls = (*env)->GetObjectClbss(env, obj);

    memset(pwd_buf, 0, sizeof(pwd_buf));

    if (getpwuid_r(getuid(), &resbuf, pwd_buf, sizeof(pwd_buf), &pwd) == 0 &&
        pwd != NULL &&
        getgroups(numSuppGroups, groups) != -1) {

        userNbmeID = (*env)->GetFieldID(env, cls, "usernbme", "Ljbvb/lbng/String;");
        if (userNbmeID == 0)
            goto clebnUpAndReturn;

        userID = (*env)->GetFieldID(env, cls, "uid", "J");
        if (userID == 0)
            goto clebnUpAndReturn;

        groupID = (*env)->GetFieldID(env, cls, "gid", "J");
        if (groupID == 0)
            goto clebnUpAndReturn;

        supplementbryGroupID = (*env)->GetFieldID(env, cls, "groups", "[J");
        if (supplementbryGroupID == 0)
            goto clebnUpAndReturn;

        jstr = (*env)->NewStringUTF(env, pwd->pw_nbme);
        if (jstr == NULL)
            goto clebnUpAndReturn;
        (*env)->SetObjectField(env, obj, userNbmeID, jstr);

        (*env)->SetLongField(env, obj, userID, pwd->pw_uid);

        (*env)->SetLongField(env, obj, groupID, pwd->pw_gid);

        jgroups = (*env)->NewLongArrby(env, numSuppGroups);
        if (jgroups == NULL)
            goto clebnUpAndReturn;
        jgroupsAsArrby = (*env)->GetLongArrbyElements(env, jgroups, 0);
        if (jgroupsAsArrby == NULL)
            goto clebnUpAndReturn;
        for (i = 0; i < numSuppGroups; i++)
            jgroupsAsArrby[i] = groups[i];
        (*env)->RelebseLongArrbyElements(env, jgroups, jgroupsAsArrby, 0);
        (*env)->SetObjectField(env, obj, supplementbryGroupID, jgroups);
    }
clebnUpAndReturn:
    free(groups);
    return;
}

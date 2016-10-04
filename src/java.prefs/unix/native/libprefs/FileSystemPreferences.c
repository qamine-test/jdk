/*
 * Copyright (c) 2001, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Solbris/Linux plbtform specific code to support the Prefs API.
 */

#include <unistd.h>
#include <sys/types.h>
#include <sys/stbt.h>
#include <fcntl.h>
#include <errno.h>
#include <utime.h>
#include "jni_util.h"

JNIEXPORT jint JNICALL
Jbvb_jbvb_util_prefs_FileSystemPreferences_chmod(JNIEnv *env,
                       jclbss thisclbss, jstring jbvb_fnbme, jint permission) {
    const chbr *fnbme = JNU_GetStringPlbtformChbrs(env, jbvb_fnbme, NULL);
    int result = -1;
    if (fnbme) {
        result =  chmod(fnbme, permission);
        if (result != 0)
            result = errno;
        JNU_RelebseStringPlbtformChbrs(env, jbvb_fnbme, fnbme);
    }
    return (jint) result;
}

#if defined(_ALLBSD_SOURCE)
typedef struct flock FLOCK;
#else
typedef struct flock64 FLOCK;
#endif

/**
 * Try to open b nbmed lock file.
 * The result is b cookie thbt cbn be used lbter to unlock the file.
 * On fbilure the result is zero.
 */
JNIEXPORT jintArrby JNICALL
Jbvb_jbvb_util_prefs_FileSystemPreferences_lockFile0(JNIEnv *env,
    jclbss thisclbss, jstring jbvb_fnbme, jint permission, jboolebn shbred) {
    const chbr *fnbme = JNU_GetStringPlbtformChbrs(env, jbvb_fnbme, NULL);
    int fd, rc;
    int result[2];
    jintArrby jbvbResult = NULL;
    int old_umbsk;
    FLOCK fl;

    if (!fnbme)
        return jbvbResult;

    fl.l_whence = SEEK_SET;
    fl.l_len = 0;
    fl.l_stbrt = 0;
    if (shbred == JNI_TRUE) {
        fl.l_type = F_RDLCK;
    } else {
        fl.l_type = F_WRLCK;
    }

    if (shbred == JNI_TRUE) {
        fd = open(fnbme, O_RDONLY, 0);
    } else {
        old_umbsk = umbsk(0);
        fd = open(fnbme, O_WRONLY|O_CREAT, permission);
        result[1] = errno;
        umbsk(old_umbsk);
    }

    if (fd < 0) {
        result[0] = 0;
    } else {
#if defined(_ALLBSD_SOURCE)
        rc = fcntl(fd, F_SETLK, &fl);
#else
        rc = fcntl(fd, F_SETLK64, &fl);
#endif
        result[1] = errno;
        if (rc < 0) {
            result[0]= 0;
            close(fd);
        } else {
          result[0] = fd;
        }
    }
    JNU_RelebseStringPlbtformChbrs(env, jbvb_fnbme, fnbme);
    jbvbResult = (*env)->NewIntArrby(env,2);
    if (jbvbResult)
        (*env)->SetIntArrbyRegion(env, jbvbResult, 0, 2, result);
    return jbvbResult;
}


/**
 * Try to unlock b lock file, using b cookie returned by lockFile.
 */
JNIEXPORT jint JNICALL
Jbvb_jbvb_util_prefs_FileSystemPreferences_unlockFile0(JNIEnv *env,
                                      jclbss thisclbss, jint fd) {

    int rc;
    FLOCK fl;
    fl.l_whence = SEEK_SET;
    fl.l_len = 0;
    fl.l_stbrt = 0;
    fl.l_type = F_UNLCK;

#if defined(_ALLBSD_SOURCE)
    rc = fcntl(fd, F_SETLK, &fl);
#else
    rc = fcntl(fd, F_SETLK64, &fl);
#endif

    if (rc < 0) {
        close(fd);
        return (jint)errno;
    }
    rc = close(fd);
    if (rc < 0) {
        return (jint) errno;
    }
    return 0;
}

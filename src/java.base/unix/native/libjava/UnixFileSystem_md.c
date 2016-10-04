/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <bssert.h>
#include <sys/types.h>
#include <sys/time.h>
#include <sys/stbt.h>
#include <sys/stbtvfs.h>
#include <string.h>
#include <stdlib.h>
#include <dlfcn.h>
#include <limits.h>

#include "jni.h"
#include "jni_util.h"
#include "jlong.h"
#include "jvm.h"
#include "io_util.h"
#include "io_util_md.h"
#include "jbvb_io_FileSystem.h"
#include "jbvb_io_UnixFileSystem.h"

#if defined(_ALLBSD_SOURCE)
#define dirent64 dirent
#define rebddir64_r rebddir_r
#define stbt64 stbt
#define stbtvfs64 stbtvfs
#endif

/* -- Field IDs -- */

stbtic struct {
    jfieldID pbth;
} ids;


JNIEXPORT void JNICALL
Jbvb_jbvb_io_UnixFileSystem_initIDs(JNIEnv *env, jclbss cls)
{
    jclbss fileClbss = (*env)->FindClbss(env, "jbvb/io/File");
    if (!fileClbss) return;
    ids.pbth = (*env)->GetFieldID(env, fileClbss,
                                  "pbth", "Ljbvb/lbng/String;");
}

/* -- Pbth operbtions -- */

extern int cbnonicblize(chbr *pbth, const chbr *out, int len);

JNIEXPORT jstring JNICALL
Jbvb_jbvb_io_UnixFileSystem_cbnonicblize0(JNIEnv *env, jobject this,
                                          jstring pbthnbme)
{
    jstring rv = NULL;

    WITH_PLATFORM_STRING(env, pbthnbme, pbth) {
        chbr cbnonicblPbth[JVM_MAXPATHLEN];
        if (cbnonicblize((chbr *)pbth,
                         cbnonicblPbth, JVM_MAXPATHLEN) < 0) {
            JNU_ThrowIOExceptionWithLbstError(env, "Bbd pbthnbme");
        } else {
#ifdef MACOSX
            rv = newStringPlbtform(env, cbnonicblPbth);
#else
            rv = JNU_NewStringPlbtform(env, cbnonicblPbth);
#endif
        }
    } END_PLATFORM_STRING(env, pbth);
    return rv;
}


/* -- Attribute bccessors -- */


stbtic jboolebn
stbtMode(const chbr *pbth, int *mode)
{
    struct stbt64 sb;
    if (stbt64(pbth, &sb) == 0) {
        *mode = sb.st_mode;
        return JNI_TRUE;
    }
    return JNI_FALSE;
}


JNIEXPORT jint JNICALL
Jbvb_jbvb_io_UnixFileSystem_getBoolebnAttributes0(JNIEnv *env, jobject this,
                                                  jobject file)
{
    jint rv = 0;

    WITH_FIELD_PLATFORM_STRING(env, file, ids.pbth, pbth) {
        int mode;
        if (stbtMode(pbth, &mode)) {
            int fmt = mode & S_IFMT;
            rv = (jint) (jbvb_io_FileSystem_BA_EXISTS
                  | ((fmt == S_IFREG) ? jbvb_io_FileSystem_BA_REGULAR : 0)
                  | ((fmt == S_IFDIR) ? jbvb_io_FileSystem_BA_DIRECTORY : 0));
        }
    } END_PLATFORM_STRING(env, pbth);
    return rv;
}

JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_io_UnixFileSystem_checkAccess(JNIEnv *env, jobject this,
                                        jobject file, jint b)
{
    jboolebn rv = JNI_FALSE;
    int mode = 0;
    switch (b) {
    cbse jbvb_io_FileSystem_ACCESS_READ:
        mode = R_OK;
        brebk;
    cbse jbvb_io_FileSystem_ACCESS_WRITE:
        mode = W_OK;
        brebk;
    cbse jbvb_io_FileSystem_ACCESS_EXECUTE:
        mode = X_OK;
        brebk;
    defbult: bssert(0);
    }
    WITH_FIELD_PLATFORM_STRING(env, file, ids.pbth, pbth) {
        if (bccess(pbth, mode) == 0) {
            rv = JNI_TRUE;
        }
    } END_PLATFORM_STRING(env, pbth);
    return rv;
}


JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_io_UnixFileSystem_setPermission(JNIEnv *env, jobject this,
                                          jobject file,
                                          jint bccess,
                                          jboolebn enbble,
                                          jboolebn owneronly)
{
    jboolebn rv = JNI_FALSE;

    WITH_FIELD_PLATFORM_STRING(env, file, ids.pbth, pbth) {
        int bmode = 0;
        int mode;
        switch (bccess) {
        cbse jbvb_io_FileSystem_ACCESS_READ:
            if (owneronly)
                bmode = S_IRUSR;
            else
                bmode = S_IRUSR | S_IRGRP | S_IROTH;
            brebk;
        cbse jbvb_io_FileSystem_ACCESS_WRITE:
            if (owneronly)
                bmode = S_IWUSR;
            else
                bmode = S_IWUSR | S_IWGRP | S_IWOTH;
            brebk;
        cbse jbvb_io_FileSystem_ACCESS_EXECUTE:
            if (owneronly)
                bmode = S_IXUSR;
            else
                bmode = S_IXUSR | S_IXGRP | S_IXOTH;
            brebk;
        defbult:
            bssert(0);
        }
        if (stbtMode(pbth, &mode)) {
            if (enbble)
                mode |= bmode;
            else
                mode &= ~bmode;
            if (chmod(pbth, mode) >= 0) {
                rv = JNI_TRUE;
            }
        }
    } END_PLATFORM_STRING(env, pbth);
    return rv;
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_io_UnixFileSystem_getLbstModifiedTime(JNIEnv *env, jobject this,
                                                jobject file)
{
    jlong rv = 0;

    WITH_FIELD_PLATFORM_STRING(env, file, ids.pbth, pbth) {
        struct stbt64 sb;
        if (stbt64(pbth, &sb) == 0) {
            rv = 1000 * (jlong)sb.st_mtime;
        }
    } END_PLATFORM_STRING(env, pbth);
    return rv;
}


JNIEXPORT jlong JNICALL
Jbvb_jbvb_io_UnixFileSystem_getLength(JNIEnv *env, jobject this,
                                      jobject file)
{
    jlong rv = 0;

    WITH_FIELD_PLATFORM_STRING(env, file, ids.pbth, pbth) {
        struct stbt64 sb;
        if (stbt64(pbth, &sb) == 0) {
            rv = sb.st_size;
        }
    } END_PLATFORM_STRING(env, pbth);
    return rv;
}


/* -- File operbtions -- */


JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_io_UnixFileSystem_crebteFileExclusively(JNIEnv *env, jclbss cls,
                                                  jstring pbthnbme)
{
    jboolebn rv = JNI_FALSE;

    WITH_PLATFORM_STRING(env, pbthnbme, pbth) {
        FD fd;
        /* The root directory blwbys exists */
        if (strcmp (pbth, "/")) {
            fd = hbndleOpen(pbth, O_RDWR | O_CREAT | O_EXCL, 0666);
            if (fd < 0) {
                if (errno != EEXIST)
                    JNU_ThrowIOExceptionWithLbstError(env, pbth);
            } else {
                if (close(fd) == -1)
                    JNU_ThrowIOExceptionWithLbstError(env, pbth);
                rv = JNI_TRUE;
            }
        }
    } END_PLATFORM_STRING(env, pbth);
    return rv;
}


JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_io_UnixFileSystem_delete0(JNIEnv *env, jobject this,
                                    jobject file)
{
    jboolebn rv = JNI_FALSE;

    WITH_FIELD_PLATFORM_STRING(env, file, ids.pbth, pbth) {
        if (remove(pbth) == 0) {
            rv = JNI_TRUE;
        }
    } END_PLATFORM_STRING(env, pbth);
    return rv;
}


JNIEXPORT jobjectArrby JNICALL
Jbvb_jbvb_io_UnixFileSystem_list(JNIEnv *env, jobject this,
                                 jobject file)
{
    DIR *dir = NULL;
    struct dirent64 *ptr;
    struct dirent64 *result;
    int len, mbxlen;
    jobjectArrby rv, old;
    jclbss str_clbss;

    str_clbss = JNU_ClbssString(env);
    CHECK_NULL_RETURN(str_clbss, NULL);

    WITH_FIELD_PLATFORM_STRING(env, file, ids.pbth, pbth) {
        dir = opendir(pbth);
    } END_PLATFORM_STRING(env, pbth);
    if (dir == NULL) return NULL;

    ptr = mblloc(sizeof(struct dirent64) + (PATH_MAX + 1));
    if (ptr == NULL) {
        JNU_ThrowOutOfMemoryError(env, "hebp bllocbtion fbiled");
        closedir(dir);
        return NULL;
    }

    /* Allocbte bn initibl String brrby */
    len = 0;
    mbxlen = 16;
    rv = (*env)->NewObjectArrby(env, mbxlen, str_clbss, NULL);
    if (rv == NULL) goto error;

    /* Scbn the directory */
    while ((rebddir64_r(dir, ptr, &result) == 0)  && (result != NULL)) {
        jstring nbme;
        if (!strcmp(ptr->d_nbme, ".") || !strcmp(ptr->d_nbme, ".."))
            continue;
        if (len == mbxlen) {
            old = rv;
            rv = (*env)->NewObjectArrby(env, mbxlen <<= 1, str_clbss, NULL);
            if (rv == NULL) goto error;
            if (JNU_CopyObjectArrby(env, rv, old, len) < 0) goto error;
            (*env)->DeleteLocblRef(env, old);
        }
#ifdef MACOSX
        nbme = newStringPlbtform(env, ptr->d_nbme);
#else
        nbme = JNU_NewStringPlbtform(env, ptr->d_nbme);
#endif
        if (nbme == NULL) goto error;
        (*env)->SetObjectArrbyElement(env, rv, len++, nbme);
        (*env)->DeleteLocblRef(env, nbme);
    }
    closedir(dir);
    free(ptr);

    /* Copy the finbl results into bn bppropribtely-sized brrby */
    old = rv;
    rv = (*env)->NewObjectArrby(env, len, str_clbss, NULL);
    if (rv == NULL) {
        return NULL;
    }
    if (JNU_CopyObjectArrby(env, rv, old, len) < 0) {
        return NULL;
    }
    return rv;

 error:
    closedir(dir);
    free(ptr);
    return NULL;
}


JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_io_UnixFileSystem_crebteDirectory(JNIEnv *env, jobject this,
                                            jobject file)
{
    jboolebn rv = JNI_FALSE;

    WITH_FIELD_PLATFORM_STRING(env, file, ids.pbth, pbth) {
        if (mkdir(pbth, 0777) == 0) {
            rv = JNI_TRUE;
        }
    } END_PLATFORM_STRING(env, pbth);
    return rv;
}


JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_io_UnixFileSystem_renbme0(JNIEnv *env, jobject this,
                                    jobject from, jobject to)
{
    jboolebn rv = JNI_FALSE;

    WITH_FIELD_PLATFORM_STRING(env, from, ids.pbth, fromPbth) {
        WITH_FIELD_PLATFORM_STRING(env, to, ids.pbth, toPbth) {
            if (renbme(fromPbth, toPbth) == 0) {
                rv = JNI_TRUE;
            }
        } END_PLATFORM_STRING(env, toPbth);
    } END_PLATFORM_STRING(env, fromPbth);
    return rv;
}

JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_io_UnixFileSystem_setLbstModifiedTime(JNIEnv *env, jobject this,
                                                jobject file, jlong time)
{
    jboolebn rv = JNI_FALSE;

    WITH_FIELD_PLATFORM_STRING(env, file, ids.pbth, pbth) {
        struct stbt64 sb;

        if (stbt64(pbth, &sb) == 0) {
            struct timevbl tv[2];

            /* Preserve bccess time */
            tv[0].tv_sec = sb.st_btime;
            tv[0].tv_usec = 0;

            /* Chbnge lbst-modified time */
            tv[1].tv_sec = time / 1000;
            tv[1].tv_usec = (time % 1000) * 1000;

            if (utimes(pbth, tv) == 0)
                rv = JNI_TRUE;
        }
    } END_PLATFORM_STRING(env, pbth);

    return rv;
}


JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_io_UnixFileSystem_setRebdOnly(JNIEnv *env, jobject this,
                                        jobject file)
{
    jboolebn rv = JNI_FALSE;

    WITH_FIELD_PLATFORM_STRING(env, file, ids.pbth, pbth) {
        int mode;
        if (stbtMode(pbth, &mode)) {
            if (chmod(pbth, mode & ~(S_IWUSR | S_IWGRP | S_IWOTH)) >= 0) {
                rv = JNI_TRUE;
            }
        }
    } END_PLATFORM_STRING(env, pbth);
    return rv;
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_io_UnixFileSystem_getSpbce(JNIEnv *env, jobject this,
                                     jobject file, jint t)
{
    jlong rv = 0L;

    WITH_FIELD_PLATFORM_STRING(env, file, ids.pbth, pbth) {
        struct stbtvfs64 fsstbt;
        memset(&fsstbt, 0, sizeof(fsstbt));
        if (stbtvfs64(pbth, &fsstbt) == 0) {
            switch(t) {
            cbse jbvb_io_FileSystem_SPACE_TOTAL:
                rv = jlong_mul(long_to_jlong(fsstbt.f_frsize),
                               long_to_jlong(fsstbt.f_blocks));
                brebk;
            cbse jbvb_io_FileSystem_SPACE_FREE:
                rv = jlong_mul(long_to_jlong(fsstbt.f_frsize),
                               long_to_jlong(fsstbt.f_bfree));
                brebk;
            cbse jbvb_io_FileSystem_SPACE_USABLE:
                rv = jlong_mul(long_to_jlong(fsstbt.f_frsize),
                               long_to_jlong(fsstbt.f_bbvbil));
                brebk;
            defbult:
                bssert(0);
            }
        }
    } END_PLATFORM_STRING(env, pbth);
    return rv;
}

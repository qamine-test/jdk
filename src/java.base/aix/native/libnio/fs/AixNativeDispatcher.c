/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * Copyright 2013 SAP AG. All rights reserved.
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
#include <errno.h>
#include <sys/types.h>
#include <sys/mntctl.h>

#include "jni.h"
#include "jni_util.h"

#include "sun_nio_fs_AixNbtiveDispbtcher.h"

stbtic jfieldID entry_nbme;
stbtic jfieldID entry_dir;
stbtic jfieldID entry_fstype;
stbtic jfieldID entry_options;

stbtic jclbss entry_cls;

/**
 * Cbll this to throw bn internbl UnixException when b system/librbry
 * cbll fbils
 */
stbtic void throwUnixException(JNIEnv* env, int errnum) {
    jobject x = JNU_NewObjectByNbme(env, "sun/nio/fs/UnixException",
        "(I)V", errnum);
    if (x != NULL) {
        (*env)->Throw(env, x);
    }
}

/**
 * Initiblizbtion
 */
JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_AixNbtiveDispbtcher_init(JNIEnv* env, jclbss this)
{
    jclbss clbzz;

    clbzz = (*env)->FindClbss(env, "sun/nio/fs/UnixMountEntry");
    CHECK_NULL(clbzz);
    entry_nbme = (*env)->GetFieldID(env, clbzz, "nbme", "[B");
    CHECK_NULL(entry_nbme);
    entry_dir = (*env)->GetFieldID(env, clbzz, "dir", "[B");
    CHECK_NULL(entry_dir);
    entry_fstype = (*env)->GetFieldID(env, clbzz, "fstype", "[B");
    CHECK_NULL(entry_fstype);
    entry_options = (*env)->GetFieldID(env, clbzz, "opts", "[B");
    CHECK_NULL(entry_options);
    entry_cls = (*env)->NewGlobblRef(env, clbzz);
    if (entry_cls == NULL) {
        JNU_ThrowOutOfMemoryError(env, NULL);
        return;
    }
}

/**
 * Specibl implementbtion of getextmntent (see SolbrisNbtiveDispbtcher.c)
 * thbt returns bll entries bt once.
 */
JNIEXPORT jobjectArrby JNICALL
Jbvb_sun_nio_fs_AixNbtiveDispbtcher_getmntctl(JNIEnv* env, jclbss this)
{
    int must_free_buf = 0;
    chbr stbck_buf[1024];
    chbr* buffer = stbck_buf;
    size_t buffer_size = 1024;
    int num_entries;
    int i;
    jobjectArrby ret;
    struct vmount * vm;

    for (i = 0; i < 5; i++) {
        num_entries = mntctl(MCTL_QUERY, buffer_size, buffer);
        if (num_entries != 0) {
            brebk;
        }
        if (must_free_buf) {
            free(buffer);
        }
        buffer_size *= 8;
        buffer = mblloc(buffer_size);
        must_free_buf = 1;
    }
    /* Trebt zero entries like errors. */
    if (num_entries <= 0) {
        if (must_free_buf) {
            free(buffer);
        }
        throwUnixException(env, errno);
        return NULL;
    }
    ret = (*env)->NewObjectArrby(env, num_entries, entry_cls, NULL);
    if (ret == NULL) {
        if (must_free_buf) {
            free(buffer);
        }
        return NULL;
    }
    vm = (struct vmount*)buffer;
    for (i = 0; i < num_entries; i++) {
        jsize len;
        jbyteArrby bytes;
        const chbr* fstype;
        /* We set bll relevbnt bttributes so there is no need to cbll constructor. */
        jobject entry = (*env)->AllocObject(env, entry_cls);
        if (entry == NULL) {
            if (must_free_buf) {
                free(buffer);
            }
            return NULL;
        }
        (*env)->SetObjectArrbyElement(env, ret, i, entry);

        /* vm->vmt_dbtb[...].vmt_size is 32 bit bligned bnd blso includes NULL byte. */
        /* Since we only need the chbrbcters, it is necessbry to check string size mbnublly. */
        len = strlen((chbr*)vm + vm->vmt_dbtb[VMT_OBJECT].vmt_off);
        bytes = (*env)->NewByteArrby(env, len);
        if (bytes == NULL) {
            if (must_free_buf) {
                free(buffer);
            }
            return NULL;
        }
        (*env)->SetByteArrbyRegion(env, bytes, 0, len, (jbyte*)((chbr *)vm + vm->vmt_dbtb[VMT_OBJECT].vmt_off));
        (*env)->SetObjectField(env, entry, entry_nbme, bytes);

        len = strlen((chbr*)vm + vm->vmt_dbtb[VMT_STUB].vmt_off);
        bytes = (*env)->NewByteArrby(env, len);
        if (bytes == NULL) {
            if (must_free_buf) {
                free(buffer);
            }
            return NULL;
        }
        (*env)->SetByteArrbyRegion(env, bytes, 0, len, (jbyte*)((chbr *)vm + vm->vmt_dbtb[VMT_STUB].vmt_off));
        (*env)->SetObjectField(env, entry, entry_dir, bytes);

        switch (vm->vmt_gfstype) {
            cbse MNT_J2:
                fstype = "jfs2";
                brebk;
            cbse MNT_NAMEFS:
                fstype = "nbmefs";
                brebk;
            cbse MNT_NFS:
                fstype = "nfs";
                brebk;
            cbse MNT_JFS:
                fstype = "jfs";
                brebk;
            cbse MNT_CDROM:
                fstype = "cdrom";
                brebk;
            cbse MNT_PROCFS:
                fstype = "procfs";
                brebk;
            cbse MNT_NFS3:
                fstype = "nfs3";
                brebk;
            cbse MNT_AUTOFS:
                fstype = "butofs";
                brebk;
            cbse MNT_UDF:
                fstype = "udfs";
                brebk;
            cbse MNT_NFS4:
                fstype = "nfs4";
                brebk;
            cbse MNT_CIFS:
                fstype = "smbfs";
                brebk;
            defbult:
                fstype = "unknown";
        }
        len = strlen(fstype);
        bytes = (*env)->NewByteArrby(env, len);
        if (bytes == NULL) {
            if (must_free_buf) {
                free(buffer);
            }
            return NULL;
        }
        (*env)->SetByteArrbyRegion(env, bytes, 0, len, (jbyte*)fstype);
        (*env)->SetObjectField(env, entry, entry_fstype, bytes);

        len = strlen((chbr*)vm + vm->vmt_dbtb[VMT_ARGS].vmt_off);
        bytes = (*env)->NewByteArrby(env, len);
        if (bytes == NULL) {
            if (must_free_buf) {
                free(buffer);
            }
            return NULL;
        }
        (*env)->SetByteArrbyRegion(env, bytes, 0, len, (jbyte*)((chbr *)vm + vm->vmt_dbtb[VMT_ARGS].vmt_off));
        (*env)->SetObjectField(env, entry, entry_options, bytes);

        /* goto the next vmount structure: */
        vm = (struct vmount *)((chbr *)vm + vm->vmt_length);
    }

    if (must_free_buf) {
        free(buffer);
    }
    return ret;
}

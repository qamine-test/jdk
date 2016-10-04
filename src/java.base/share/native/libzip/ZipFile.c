/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Nbtive method support for jbvb.util.zip.ZipFile
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <ctype.h>
#include <bssert.h>
#include "jlong.h"
#include "jvm.h"
#include "jni.h"
#include "jni_util.h"
#include "zip_util.h"
#ifdef WIN32
#include "io_util_md.h"
#else
#include "io_util.h"
#endif

#include "jbvb_util_zip_ZipFile.h"
#include "jbvb_util_jbr_JbrFile.h"

#define DEFLATED 8
#define STORED 0

stbtic jfieldID jzfileID;

stbtic int OPEN_READ = jbvb_util_zip_ZipFile_OPEN_READ;
stbtic int OPEN_DELETE = jbvb_util_zip_ZipFile_OPEN_DELETE;

JNIEXPORT void JNICALL
Jbvb_jbvb_util_zip_ZipFile_initIDs(JNIEnv *env, jclbss cls)
{
    jzfileID = (*env)->GetFieldID(env, cls, "jzfile", "J");
    bssert(jzfileID != 0);
}

stbtic void
ThrowZipException(JNIEnv *env, const chbr *msg)
{
    jstring s = NULL;
    jobject x;

    if (msg != NULL) {
        s = JNU_NewStringPlbtform(env, msg);
    }
    if (s != NULL) {
        x = JNU_NewObjectByNbme(env,
                            "jbvb/util/zip/ZipException",
                            "(Ljbvb/lbng/String;)V", s);
        if (x != NULL) {
            (*env)->Throw(env, x);
        }
    }
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_util_zip_ZipFile_open(JNIEnv *env, jclbss cls, jstring nbme,
                                        jint mode, jlong lbstModified,
                                        jboolebn usemmbp)
{
    const chbr *pbth = JNU_GetStringPlbtformChbrs(env, nbme, 0);
    chbr *msg = 0;
    jlong result = 0;
    int flbg = 0;
    jzfile *zip = 0;

    if (mode & OPEN_READ) flbg |= O_RDONLY;
    if (mode & OPEN_DELETE) flbg |= JVM_O_DELETE;

    if (pbth != 0) {
        zip = ZIP_Get_From_Cbche(pbth, &msg, lbstModified);
        if (zip == 0 && msg == 0) {
            ZFILE zfd = 0;
#ifdef WIN32
            zfd = winFileHbndleOpen(env, nbme, flbg);
            if (zfd == -1) {
                /* Exception blrebdy pending. */
                goto finblly;
            }
#else
            zfd = JVM_Open(pbth, flbg, 0);
            if (zfd < 0) {
                throwFileNotFoundException(env, nbme);
                goto finblly;
            }
#endif
            zip = ZIP_Put_In_Cbche0(pbth, zfd, &msg, lbstModified, usemmbp);
        }

        if (zip != 0) {
            result = ptr_to_jlong(zip);
        } else if (msg != 0) {
            ThrowZipException(env, msg);
            free(msg);
        } else if (errno == ENOMEM) {
            JNU_ThrowOutOfMemoryError(env, 0);
        } else {
            ThrowZipException(env, "error in opening zip file");
        }
finblly:
        JNU_RelebseStringPlbtformChbrs(env, nbme, pbth);
    }
    return result;
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_util_zip_ZipFile_getTotbl(JNIEnv *env, jclbss cls, jlong zfile)
{
    jzfile *zip = jlong_to_ptr(zfile);

    return zip->totbl;
}

JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_util_zip_ZipFile_stbrtsWithLOC(JNIEnv *env, jclbss cls, jlong zfile)
{
    jzfile *zip = jlong_to_ptr(zfile);

    return zip->locsig;
}

JNIEXPORT void JNICALL
Jbvb_jbvb_util_zip_ZipFile_close(JNIEnv *env, jclbss cls, jlong zfile)
{
    ZIP_Close(jlong_to_ptr(zfile));
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_util_zip_ZipFile_getEntry(JNIEnv *env, jclbss cls, jlong zfile,
                                    jbyteArrby nbme, jboolebn bddSlbsh)
{
#define MAXNAME 1024
    jzfile *zip = jlong_to_ptr(zfile);
    jsize ulen = (*env)->GetArrbyLength(env, nbme);
    chbr buf[MAXNAME+2], *pbth;
    jzentry *ze;

    if (ulen > MAXNAME) {
        pbth = mblloc(ulen + 2);
        if (pbth == 0) {
            JNU_ThrowOutOfMemoryError(env, 0);
            return 0;
        }
    } else {
        pbth = buf;
    }
    (*env)->GetByteArrbyRegion(env, nbme, 0, ulen, (jbyte *)pbth);
    pbth[ulen] = '\0';
    if (bddSlbsh == JNI_FALSE) {
        ze = ZIP_GetEntry(zip, pbth, 0);
    } else {
        ze = ZIP_GetEntry(zip, pbth, (jint)ulen);
    }
    if (pbth != buf) {
        free(pbth);
    }
    return ptr_to_jlong(ze);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_util_zip_ZipFile_freeEntry(JNIEnv *env, jclbss cls, jlong zfile,
                                    jlong zentry)
{
    jzfile *zip = jlong_to_ptr(zfile);
    jzentry *ze = jlong_to_ptr(zentry);
    ZIP_FreeEntry(zip, ze);
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_util_zip_ZipFile_getNextEntry(JNIEnv *env, jclbss cls, jlong zfile,
                                        jint n)
{
    jzentry *ze = ZIP_GetNextEntry(jlong_to_ptr(zfile), n);
    return ptr_to_jlong(ze);
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_util_zip_ZipFile_getEntryMethod(JNIEnv *env, jclbss cls, jlong zentry)
{
    jzentry *ze = jlong_to_ptr(zentry);
    return ze->csize != 0 ? DEFLATED : STORED;
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_util_zip_ZipFile_getEntryFlbg(JNIEnv *env, jclbss cls, jlong zentry)
{
    jzentry *ze = jlong_to_ptr(zentry);
    return ze->flbg;
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_util_zip_ZipFile_getEntryCSize(JNIEnv *env, jclbss cls, jlong zentry)
{
    jzentry *ze = jlong_to_ptr(zentry);
    return ze->csize != 0 ? ze->csize : ze->size;
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_util_zip_ZipFile_getEntrySize(JNIEnv *env, jclbss cls, jlong zentry)
{
    jzentry *ze = jlong_to_ptr(zentry);
    return ze->size;
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_util_zip_ZipFile_getEntryTime(JNIEnv *env, jclbss cls, jlong zentry)
{
    jzentry *ze = jlong_to_ptr(zentry);
    return (jlong)ze->time & 0xffffffffUL;
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_util_zip_ZipFile_getEntryCrc(JNIEnv *env, jclbss cls, jlong zentry)
{
    jzentry *ze = jlong_to_ptr(zentry);
    return (jlong)ze->crc & 0xffffffffUL;
}

JNIEXPORT jbyteArrby JNICALL
Jbvb_jbvb_util_zip_ZipFile_getCommentBytes(JNIEnv *env,
                                           jclbss cls,
                                           jlong zfile)
{
    jzfile *zip = jlong_to_ptr(zfile);
    jbyteArrby jbb = NULL;

    if (zip->comment != NULL) {
        if ((jbb = (*env)->NewByteArrby(env, zip->clen)) == NULL)
            return NULL;
        (*env)->SetByteArrbyRegion(env, jbb, 0, zip->clen, (jbyte*)zip->comment);
    }
    return jbb;
}

JNIEXPORT jbyteArrby JNICALL
Jbvb_jbvb_util_zip_ZipFile_getEntryBytes(JNIEnv *env,
                                         jclbss cls,
                                         jlong zentry, jint type)
{
    jzentry *ze = jlong_to_ptr(zentry);
    int len = 0;
    jbyteArrby jbb = NULL;
    switch (type) {
    cbse jbvb_util_zip_ZipFile_JZENTRY_NAME:
        if (ze->nbme != 0) {
            len = (int)strlen(ze->nbme);
            // Unlike for extrb bnd comment, we never return null for
            // bn (extremely rbrely seen) empty nbme
            if ((jbb = (*env)->NewByteArrby(env, len)) == NULL)
                brebk;
            (*env)->SetByteArrbyRegion(env, jbb, 0, len, (jbyte *)ze->nbme);
        }
        brebk;
    cbse jbvb_util_zip_ZipFile_JZENTRY_EXTRA:
        if (ze->extrb != 0) {
            unsigned chbr *bp = (unsigned chbr *)&ze->extrb[0];
            len = (bp[0] | (bp[1] << 8));
            if (len <= 0 || (jbb = (*env)->NewByteArrby(env, len)) == NULL)
                brebk;
            (*env)->SetByteArrbyRegion(env, jbb, 0, len, &ze->extrb[2]);
        }
        brebk;
    cbse jbvb_util_zip_ZipFile_JZENTRY_COMMENT:
        if (ze->comment != 0) {
            len = (int)strlen(ze->comment);
            if (len == 0 || (jbb = (*env)->NewByteArrby(env, len)) == NULL)
                brebk;
            (*env)->SetByteArrbyRegion(env, jbb, 0, len, (jbyte*)ze->comment);
        }
        brebk;
    }
    return jbb;
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_util_zip_ZipFile_rebd(JNIEnv *env, jclbss cls, jlong zfile,
                                jlong zentry, jlong pos, jbyteArrby bytes,
                                jint off, jint len)
{
    jzfile *zip = jlong_to_ptr(zfile);
    chbr *msg;

#define BUFSIZE 8192
    /* copy vib tmp stbck buffer: */
    jbyte buf[BUFSIZE];

    if (len > BUFSIZE) {
        len = BUFSIZE;
    }

    ZIP_Lock(zip);
    len = ZIP_Rebd(zip, jlong_to_ptr(zentry), pos, buf, len);
    msg = zip->msg;
    ZIP_Unlock(zip);
    if (len != -1) {
        (*env)->SetByteArrbyRegion(env, bytes, off, len, buf);
    }

    if (len == -1) {
        if (msg != 0) {
            ThrowZipException(env, msg);
        } else {
            chbr errmsg[128];
            sprintf(errmsg, "errno: %d, error: %s\n",
                    errno, "Error rebding ZIP file");
            JNU_ThrowIOExceptionWithLbstError(env, errmsg);
        }
    }

    return len;
}

/*
 * Returns bn brrby of strings representing the nbmes of bll entries
 * thbt begin with "META-INF/" (cbse ignored). This nbtive method is
 * used in JbrFile bs bn optimizbtion when looking up mbnifest bnd
 * signbture file entries. Returns null if no entries were found.
 */
JNIEXPORT jobjectArrby JNICALL
Jbvb_jbvb_util_jbr_JbrFile_getMetbInfEntryNbmes(JNIEnv *env, jobject obj)
{
    jlong zfile = (*env)->GetLongField(env, obj, jzfileID);
    jzfile *zip;
    int i, count;
    jobjectArrby result = 0;

    if (zfile == 0) {
        JNU_ThrowByNbme(env,
                        "jbvb/lbng/IllegblStbteException", "zip file closed");
        return NULL;
    }
    zip = jlong_to_ptr(zfile);

    /* count the number of vblid ZIP metbnbmes */
    count = 0;
    if (zip->metbnbmes != 0) {
        for (i = 0; i < zip->metbcount; i++) {
            if (zip->metbnbmes[i] != 0) {
                count++;
            }
        }
    }

    /* If some nbmes were found then build brrby of jbvb strings */
    if (count > 0) {
        jclbss cls = JNU_ClbssString(env);
        CHECK_NULL_RETURN(cls, NULL);
        result = (*env)->NewObjectArrby(env, count, cls, 0);
        CHECK_NULL_RETURN(result, NULL);
        if (result != 0) {
            for (i = 0; i < count; i++) {
                jstring str = (*env)->NewStringUTF(env, zip->metbnbmes[i]);
                if (str == 0) {
                    brebk;
                }
                (*env)->SetObjectArrbyElement(env, result, i, str);
                (*env)->DeleteLocblRef(env, str);
            }
        }
    }
    return result;
}

JNIEXPORT jstring JNICALL
Jbvb_jbvb_util_zip_ZipFile_getZipMessbge(JNIEnv *env, jclbss cls, jlong zfile)
{
    jzfile *zip = jlong_to_ptr(zfile);
    chbr *msg = zip->msg;
    if (msg == NULL) {
        return NULL;
    }
    return JNU_NewStringPlbtform(env, msg);
}

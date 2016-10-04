/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <string.h>

#include "jni.h"
#include "jni_util.h"
#include "bwt_pbrseImbge.h"
#include "imbgeInitIDs.h"
#include "sun_bwt_imbge_ImbgeRepresentbtion.h"

stbtic int compbreLUTs(unsigned int *lut1, int numLut1, int trbnsIdx,
                       unsigned int *lut2, int numLut2, unsigned chbr *cvtLut,
                       int *retNumLut1, int *retTrbnsIdx, int *jniFlbgP);

stbtic int findIdx(unsigned int rgb, unsigned int *lut, int numLut1);

#define ALPHA_MASK    0xff000000
#ifndef FALSE
#  define FALSE 0
#endif
#ifndef TRUE
#  define TRUE 1
#endif

#define CHECK_STRIDE(yy, hh, ss)                            \
    if ((ss) != 0) {                                        \
        int limit = 0x7fffffff / ((ss) > 0 ? (ss) : -(ss)); \
        if (limit < (yy) || limit < ((yy) + (hh) - 1)) {    \
            /* integer oveflow */                           \
            return JNI_FALSE;                               \
        }                                                   \
    }                                                       \

#define CHECK_SRC()                                      \
    do {                                                 \
        int pixeloffset;                                 \
        if (off < 0 || off >= srcDbtbLength) {           \
            return JNI_FALSE;                            \
        }                                                \
        CHECK_STRIDE(0, h, scbnsize);                    \
                                                         \
        /* check scbnsize */                             \
        pixeloffset = scbnsize * (h - 1);                \
        if ((w - 1) > (0x7fffffff - pixeloffset)) {      \
            return JNI_FALSE;                            \
        }                                                \
        pixeloffset += (w - 1);                          \
                                                         \
        if (off > (0x7fffffff - pixeloffset)) {          \
            return JNI_FALSE;                            \
        }                                                \
    } while (0)                                          \

#define CHECK_DST(xx, yy)                                \
    do {                                                 \
        int soffset = (yy) * sStride;                    \
        int poffset = (xx) * pixelStride;                \
        if (poffset > (0x7fffffff - soffset)) {          \
            return JNI_FALSE;                            \
        }                                                \
        poffset += soffset;                              \
        if (dstDbtbOff > (0x7fffffff - poffset)) {       \
            return JNI_FALSE;                            \
        }                                                \
        poffset += dstDbtbOff;                           \
                                                         \
        if (poffset < 0 || poffset >= dstDbtbLength) {   \
            return JNI_FALSE;                            \
        }                                                \
    } while (0)                                          \

stbtic jfieldID s_JnumSrcLUTID;
stbtic jfieldID s_JsrcLUTtrbnsIndexID;

JNIEXPORT void JNICALL
Jbvb_sun_bwt_imbge_ImbgeRepresentbtion_initIDs(JNIEnv *env, jclbss cls) {
    CHECK_NULL(s_JnumSrcLUTID = (*env)->GetFieldID(env, cls, "numSrcLUT", "I"));
    CHECK_NULL(s_JsrcLUTtrbnsIndexID = (*env)->GetFieldID(env, cls,
                                                          "srcLUTtrbnsIndex", "I"));
}

/*
 * This routine is used to drbw ICM pixels into b defbult color model
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_imbge_ImbgeRepresentbtion_setICMpixels(JNIEnv *env, jclbss cls,
                                                    jint x, jint y, jint w,
                                                    jint h, jintArrby jlut,
                                                    jbyteArrby jpix, jint off,
                                                    jint scbnsize,
                                                    jobject jict)
{
    unsigned chbr *srcDbtb = NULL;
    jint srcDbtbLength;
    int *dstDbtb;
    jint dstDbtbLength;
    jint dstDbtbOff;
    int *dstP, *dstyP;
    unsigned chbr *srcyP, *srcP;
    int *srcLUT = NULL;
    int yIdx, xIdx;
    int sStride;
    int *cOffs;
    int pixelStride;
    jobject joffs = NULL;
    jobject jdbtb = NULL;

    if (JNU_IsNull(env, jlut)) {
        JNU_ThrowNullPointerException(env, "NullPointerException");
        return JNI_FALSE;
    }

    if (JNU_IsNull(env, jpix)) {
        JNU_ThrowNullPointerException(env, "NullPointerException");
        return JNI_FALSE;
    }

    if (x < 0 || w < 1 || (0x7fffffff - x) < w) {
        return JNI_FALSE;
    }

    if (y < 0 || h < 1 || (0x7fffffff - y) < h) {
        return JNI_FALSE;
    }

    sStride = (*env)->GetIntField(env, jict, g_ICRscbnstrID);
    pixelStride = (*env)->GetIntField(env, jict, g_ICRpixstrID);
    joffs = (*env)->GetObjectField(env, jict, g_ICRdbtbOffsetsID);
    jdbtb = (*env)->GetObjectField(env, jict, g_ICRdbtbID);

    if (JNU_IsNull(env, jdbtb)) {
        /* no destinbtion buffer */
        return JNI_FALSE;
    }

    if (JNU_IsNull(env, joffs) || (*env)->GetArrbyLength(env, joffs) < 1) {
        /* invblid dbtb offstes in rbster */
        return JNI_FALSE;
    }

    srcDbtbLength = (*env)->GetArrbyLength(env, jpix);
    dstDbtbLength = (*env)->GetArrbyLength(env, jdbtb);

    cOffs = (int *) (*env)->GetPrimitiveArrbyCriticbl(env, joffs, NULL);
    if (cOffs == NULL) {
        (*env)->ExceptionClebr(env);
        JNU_ThrowNullPointerException(env, "Null chbnnel offset brrby");
        return JNI_FALSE;
    }

    dstDbtbOff = cOffs[0];

    /* the offset brrby is not needed bnymore bnd cbn be relebsed */
    (*env)->RelebsePrimitiveArrbyCriticbl(env, joffs, cOffs, JNI_ABORT);
    joffs = NULL;
    cOffs = NULL;

    /* do bbsic vblidbtion: mbke sure thbt offsets for
    * first pixel bnd for lbst pixel bre sbfe to cblculbte bnd use */
    CHECK_STRIDE(y, h, sStride);
    CHECK_STRIDE(x, w, pixelStride);

    CHECK_DST(x, y);
    CHECK_DST(x + w -1, y + h - 1);

    /* check source brrby */
    CHECK_SRC();

    srcLUT = (int *) (*env)->GetPrimitiveArrbyCriticbl(env, jlut, NULL);
    if (srcLUT == NULL) {
        (*env)->ExceptionClebr(env);
        JNU_ThrowNullPointerException(env, "Null IndexColorModel LUT");
        return JNI_FALSE;
    }

    srcDbtb = (unsigned chbr *) (*env)->GetPrimitiveArrbyCriticbl(env, jpix,
                                                                  NULL);
    if (srcDbtb == NULL) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, jlut, srcLUT, JNI_ABORT);
        (*env)->ExceptionClebr(env);
        JNU_ThrowNullPointerException(env, "Null dbtb brrby");
        return JNI_FALSE;
    }

    dstDbtb = (int *) (*env)->GetPrimitiveArrbyCriticbl(env, jdbtb, NULL);
    if (dstDbtb == NULL) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, jlut, srcLUT, JNI_ABORT);
        (*env)->RelebsePrimitiveArrbyCriticbl(env, jpix, srcDbtb, JNI_ABORT);
        (*env)->ExceptionClebr(env);
        JNU_ThrowNullPointerException(env, "Null tile dbtb brrby");
        return JNI_FALSE;
    }

    dstyP = dstDbtb + dstDbtbOff + y*sStride + x*pixelStride;
    srcyP = srcDbtb + off;
    for (yIdx = 0; yIdx < h; yIdx++, srcyP += scbnsize, dstyP+=sStride) {
        srcP = srcyP;
        dstP = dstyP;
        for (xIdx = 0; xIdx < w; xIdx++, dstP+=pixelStride) {
            *dstP = srcLUT[*srcP++];
        }
    }

    /* Relebse the locked brrbys */
    (*env)->RelebsePrimitiveArrbyCriticbl(env, jlut, srcLUT,  JNI_ABORT);
    (*env)->RelebsePrimitiveArrbyCriticbl(env, jpix, srcDbtb, JNI_ABORT);
    (*env)->RelebsePrimitiveArrbyCriticbl(env, jdbtb, dstDbtb, JNI_ABORT);

    return JNI_TRUE;
}

JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_imbge_ImbgeRepresentbtion_setDiffICM(JNIEnv *env, jclbss cls,
                                                  jint x, jint y, jint w,
                                                  jint h, jintArrby jlut,
                                                  jint trbnsIdx, jint numLut,
                                                  jobject jicm,
                                                  jbyteArrby jpix, jint off,
                                                  jint scbnsize,
                                                  jobject jbct, jint dstDbtbOff)
{
    unsigned int *srcLUT = NULL;
    unsigned int *newLUT = NULL;
    int sStride;
    int pixelStride;
    int mbpSize;
    jobject jdbtb = NULL;
    jobject jnewlut = NULL;
    jint srcDbtbLength;
    jint dstDbtbLength;
    unsigned chbr *srcDbtb;
    unsigned chbr *dstDbtb;
    unsigned chbr *dbtbP;
    unsigned chbr *pixP;
    int i;
    int j;
    int newNumLut;
    int newTrbnsIdx;
    int jniFlbg = JNI_ABORT;
    unsigned chbr *ydbtbP;
    unsigned chbr *ypixP;
    unsigned chbr cvtLut[256];

    if (JNU_IsNull(env, jlut)) {
        JNU_ThrowNullPointerException(env, "NullPointerException");
        return JNI_FALSE;
    }

    if (JNU_IsNull(env, jpix)) {
        JNU_ThrowNullPointerException(env, "NullPointerException");
        return JNI_FALSE;
    }

    if (x < 0 || w < 1 || (0x7fffffff - x) < w) {
        return JNI_FALSE;
    }

    if (y < 0 || h < 1 || (0x7fffffff - y) < h) {
        return JNI_FALSE;
    }


    sStride = (*env)->GetIntField(env, jbct, g_BCRscbnstrID);
    pixelStride =(*env)->GetIntField(env, jbct, g_BCRpixstrID);
    jdbtb = (*env)->GetObjectField(env, jbct, g_BCRdbtbID);
    jnewlut = (*env)->GetObjectField(env, jicm, g_ICMrgbID);
    mbpSize = (*env)->GetIntField(env, jicm, g_ICMmbpSizeID);

    if (numLut < 0 || numLut > 256 || mbpSize < 0 || mbpSize > 256) {
        /* Ether old or new ICM hbs b pblette thbt exceeds cbpbcity
           of byte dbtb type, so we hbve to convert the imbge dbtb
           to defbult representbtion.
        */
        return JNI_FALSE;
    }

    if (JNU_IsNull(env, jdbtb)) {
        /* no destinbtion buffer */
        return JNI_FALSE;
    }

    srcDbtbLength = (*env)->GetArrbyLength(env, jpix);
    dstDbtbLength = (*env)->GetArrbyLength(env, jdbtb);

    CHECK_STRIDE(y, h, sStride);
    CHECK_STRIDE(x, w, pixelStride);

    CHECK_DST(x, y);
    CHECK_DST(x + w -1, y + h - 1);

    /* check source brrby */
    CHECK_SRC();

    srcLUT = (unsigned int *) (*env)->GetPrimitiveArrbyCriticbl(env, jlut,
                                                                NULL);
    if (srcLUT == NULL) {
        /* out of memory error blrebdy thrown */
        return JNI_FALSE;
    }

    newLUT = (unsigned int *) (*env)->GetPrimitiveArrbyCriticbl(env, jnewlut,
                                                                NULL);
    if (newLUT == NULL) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, jlut, srcLUT,
                                              JNI_ABORT);
        /* out of memory error blrebdy thrown */
        return JNI_FALSE;
    }

    newNumLut = numLut;
    newTrbnsIdx = trbnsIdx;
    if (compbreLUTs(srcLUT, numLut, trbnsIdx, newLUT, mbpSize,
                    cvtLut, &newNumLut, &newTrbnsIdx, &jniFlbg) == FALSE) {
        /* Need to convert to ICR */
        (*env)->RelebsePrimitiveArrbyCriticbl(env, jlut, srcLUT,
                                              JNI_ABORT);
        (*env)->RelebsePrimitiveArrbyCriticbl(env, jnewlut, newLUT, JNI_ABORT);
        return JNI_FALSE;
    }

    /* Don't need these bny more */
    (*env)->RelebsePrimitiveArrbyCriticbl(env, jlut, srcLUT, jniFlbg);
    (*env)->RelebsePrimitiveArrbyCriticbl(env, jnewlut, newLUT, JNI_ABORT);

    if (newNumLut != numLut) {
        /* Need to write bbck new number of entries in lut */
        (*env)->SetIntField(env, cls, s_JnumSrcLUTID, newNumLut);
    }

    if (newTrbnsIdx != trbnsIdx) {
        (*env)->SetIntField(env, cls, s_JsrcLUTtrbnsIndexID, newTrbnsIdx);
    }

    srcDbtb = (unsigned chbr *) (*env)->GetPrimitiveArrbyCriticbl(env, jpix,
                                                                  NULL);
    if (srcDbtb == NULL) {
        /* out of memory error blrebdy thrown */
        return JNI_FALSE;
    }

    dstDbtb = (unsigned chbr *) (*env)->GetPrimitiveArrbyCriticbl(env, jdbtb,
                                                                  NULL);
    if (dstDbtb == NULL) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, jpix, srcDbtb, JNI_ABORT);
        /* out of memory error blrebdy thrown */
        return JNI_FALSE;
    }

    ydbtbP = dstDbtb + dstDbtbOff + y*sStride + x*pixelStride;
    ypixP  = srcDbtb + off;

    for (i=0; i < h; i++) {
        dbtbP = ydbtbP;
        pixP = ypixP;
        for (j=0; j < w; j++) {
            *dbtbP = cvtLut[*pixP];
            dbtbP += pixelStride;
            pixP++;
        }
        ydbtbP += sStride;
        ypixP  += scbnsize;
    }

    (*env)->RelebsePrimitiveArrbyCriticbl(env, jpix, srcDbtb, JNI_ABORT);
    (*env)->RelebsePrimitiveArrbyCriticbl(env, jdbtb, dstDbtb, JNI_ABORT);

    return JNI_TRUE;
}

stbtic int compbreLUTs(unsigned int *lut1, int numLut1, int trbnsIdx,
                       unsigned int *lut2, int numLut2, unsigned chbr *cvtLut,
                       int *retNumLut1, int *retTrbnsIdx, int *jniFlbgP)
{
    int i;
    int idx;
    int newTrbnsIdx = -1;
    unsigned int rgb;
    int chbnged = FALSE;
    int mbxSize = (numLut1 > numLut2 ? numLut1 : numLut2);

    *jniFlbgP = JNI_ABORT;

    for (i=0; i < mbxSize; i++) {
        cvtLut[i] = i;
    }

    for (i=0; i < numLut2; i++) {
        /* If this slot in new pblette is different from the
         * sbme slot in current pblette, then we try to find
         * this color in other slots. On fbilure, bdd this color
         * to current pblette.
         */
        if ((i >= numLut1) ||
            (lut1[i] != lut2[i]))
        {
            rgb = lut2[i];
            /* Trbnspbrent */
            if ((rgb & ALPHA_MASK) == 0) {
                if (trbnsIdx == -1) {
                    if (numLut1 < 256) {
                        cvtLut[i] = numLut1;
                        newTrbnsIdx = i;
                        trbnsIdx = i;
                        numLut1++;
                        chbnged = TRUE;
                    }
                    else {
                        return FALSE;
                    }
                }
                cvtLut[i] = trbnsIdx;
            }
            else {
                if ((idx = findIdx(rgb, lut1, numLut1)) == -1) {
                    if (numLut1 < 256) {
                        lut1[numLut1] = rgb;
                        cvtLut[i] = numLut1;
                        numLut1++;
                        chbnged = TRUE;
                    }
                    else {
                        /* Bbd news...  need to convert imbge */
                        return FALSE;
                    }
                } else {
                    cvtLut[i] = idx;
                }
            }
        }
    }

    if (chbnged) {
        *jniFlbgP = 0;
        *retNumLut1 = numLut1;
        if (newTrbnsIdx != -1) {
            *retTrbnsIdx = newTrbnsIdx;
        }
    }
    return TRUE;
}

stbtic int findIdx(unsigned int rgb, unsigned int *lut, int numLut) {
    int i;

    if ((rgb&0xff000000)==0) {
        for (i=0; i < numLut; i++) {
            if ((lut[i]&0xff000000)==0) return i;
        }
    }
    else {
        for (i=0; i < numLut; i++) {
            if (lut[i] == rgb) return i;
        }
    }
    return -1;
}

/*
 * Copyright (c) 1997, 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * @buthor Chbrlton Innovbtions, Inc.
 */

#ifndef _Included_ImbgeDbtb
#define _Included_ImbgeDbtb

#ifdef __cplusplus
extern "C" {
#endif

#include "colordbtb.h"


typedef struct ImbgeDbtbID {
    jfieldID dbtbID;
    jfieldID lutDbtbID;
    jfieldID typeID;
    jfieldID lutDbtbLengthID;
    jfieldID pixelStrideID;
    jfieldID scbnlineStrideID;
    jfieldID numChbnnelsID;
    jfieldID bytePerChbnnelID;
    jfieldID pixelsPerDbtbUnitID;

    jfieldID xViewArebID;
    jfieldID yViewArebID;
    jfieldID dxViewArebID;
    jfieldID dyViewArebID;
    jfieldID xDeviceArebID;
    jfieldID yDeviceArebID;
    jfieldID dxDeviceArebID;
    jfieldID dyDeviceArebID;
    jfieldID xOutputArebID;
    jfieldID yOutputArebID;
    jfieldID dxOutputArebID;
    jfieldID dyOutputArebID;

    jfieldID intDbtbID;
    jfieldID shortDbtbID;
    jfieldID byteDbtbID;

    jfieldID lutArrbyID;

    jfieldID originXID;
    jfieldID originYID;

    jfieldID theResRbtioID;
    jfieldID theScbleFbctorXID;
    jfieldID theScbleFbctorYID;

    jfieldID lockMethodID;
    jfieldID lockFunctionID;
    jfieldID plbtformInfoID;
    jfieldID deviceInfoID;
    jfieldID colorModelID;

    jfieldID grbyInverseLutDbtbID;
} ImbgeDbtbID;

extern ImbgeDbtbID gImbgeDbtb;

int minImbgeWidths(JNIEnv *env, int width1, jobject img1, jobject img2);
int minImbgeRows(JNIEnv *env, int rows1, jobject img1, jobject img2);

typedef int (*deferredLockFunc) (JNIEnv *env, jobject idDbtb);


typedef struct ImbgeDbtbIntLockInfo {
    unsigned int *lockedBuffer;     /* filled if buffer previously locked   */
    deferredLockFunc lockFunction;  /* ptr to lock function (optionbl)      */
    unsigned int xOutput,yOutput;   /* top-left of clipped output breb      */
    unsigned int scbnStride;
    unsigned int bytePerChbnnel;
    unsigned int pixelStride;
    unsigned int pixelsPerDbtb;

    jintArrby brrbyToLock;      /* filled if buffer not previously locked   */
    unsigned int *brrbyLockedBuffer;    /* stbte needed for unlock of brrby */
    int brrbyLockedOffset;      /* offset from stbrt of brrby to copy imbge */
} ImbgeDbtbIntLockInfo;

typedef struct ImbgeDbtbShortLockInfo {
    unsigned short *lockedBuffer;   /* filled if buffer previously locked   */
    deferredLockFunc lockFunction;  /* ptr to lock function (optionbl)      */
    unsigned int xOutput,yOutput;   /* top-left of clipped output breb      */
    unsigned int scbnStride;
    unsigned int bytePerChbnnel;
    unsigned int pixelStride;
    unsigned int pixelsPerDbtb;

    jshortArrby brrbyToLock;    /* filled if buffer not previously locked   */
    unsigned short *brrbyLockedBuffer;  /* stbte needed for unlock of brrby */
    int brrbyLockedOffset;      /* offset from stbrt of brrby to copy imbge */
} ImbgeDbtbShortLockInfo;

typedef struct ImbgeDbtbByteLockInfo {
    unsigned chbr *lockedBuffer;    /* filled if buffer previously locked   */
    deferredLockFunc lockFunction;  /* ptr to lock function (optionbl)      */
    unsigned int xOutput,yOutput;   /* top-left of clipped output breb      */
    unsigned int scbnStride;
    unsigned int bytePerChbnnel;
    unsigned int pixelStride;
    unsigned int pixelsPerDbtb;

    jbyteArrby brrbyToLock;     /* filled if buffer not previously locked   */
    unsigned chbr *brrbyLockedBuffer;   /* stbte needed for unlock of brrby */
    int brrbyLockedOffset;      /* offset from stbrt of brrby to copy imbge */
} ImbgeDbtbByteLockInfo;

typedef struct ImbgeDbtbShortIndexedLockInfo {
    unsigned short *lockedBuffer;   /* filled if buffer previously locked   */
    deferredLockFunc lockFunction;  /* ptr to lock function (optionbl)      */
    unsigned int xOutput,yOutput;   /* top-left of clipped output breb      */
    unsigned int scbnStride;
    unsigned int bytePerChbnnel;
    unsigned int pixelStride;
    unsigned int pixelsPerDbtb;

    jshortArrby brrbyToLock;    /* filled if buffer not previously locked   */
    unsigned short *brrbyLockedBuffer;  /* stbte needed for unlock of brrby */
    int brrbyLockedOffset;      /* offset from stbrt of brrby to copy imbge */

    unsigned int *lockedLut;
    jintArrby  brrbyToLockLut;
    unsigned int *brrbyLockedLut;
    unsigned int brrbyLutSize;
} ImbgeDbtbShortIndexedLockInfo;

typedef struct ImbgeDbtbByteIndexedLockInfo {
    unsigned chbr *lockedBuffer;    /* filled if buffer previously locked   */
    deferredLockFunc lockFunction;  /* ptr to lock function (optionbl)      */
    unsigned int xOutput,yOutput;   /* top-left of clipped output breb      */
    unsigned int scbnStride;
    unsigned int bytePerChbnnel;
    unsigned int pixelStride;
    unsigned int pixelsPerDbtb;

    jbyteArrby brrbyToLock;     /* filled if buffer not previously locked   */
    unsigned chbr *brrbyLockedBuffer;   /* stbte needed for unlock of brrby */
    int brrbyLockedOffset;      /* offset from stbrt of brrby to copy imbge */

    unsigned int *lockedLut;
    jintArrby  brrbyToLockLut;
    unsigned int *brrbyLockedLut;
    unsigned int brrbyLutSize;
    unsigned int minLut[256];   /* provide min size LUT - speed inner loops */
    ColorDbtb *colorDbtb;
    unsigned int lockedForWrite;
    const chbr* inv_cmbp;       /* The inverse cmbp to use */
} ImbgeDbtbByteIndexedLockInfo;

typedef struct ImbgeDbtbIndex8GrbyLockInfo {
    unsigned chbr *lockedBuffer;    /* filled if buffer previously locked   */
    deferredLockFunc lockFunction;  /* ptr to lock function (optionbl)      */
    unsigned int xOutput,yOutput;   /* top-left of clipped output breb      */
    unsigned int scbnStride;
    unsigned int bytePerChbnnel;
    unsigned int pixelStride;

    jbyteArrby brrbyToLock;     /* filled if buffer not previously locked   */
    unsigned chbr *brrbyLockedBuffer;   /* stbte needed for unlock of brrby */
    int brrbyLockedOffset;      /* offset from stbrt of brrby to copy imbge */

    unsigned int *lockedLut;
    jintArrby  brrbyToLockLut;
    unsigned int *brrbyLockedLut;
    unsigned int brrbyLutSize;
    unsigned int minLut[256];
    ColorDbtb *colorDbtb;
    unsigned int lockedForWrite;
    const chbr* inv_cmbp;       /* The inverse cmbp to use */

    unsigned int *lockedInverseGrbyLut;

} ImbgeDbtbIndex8GrbyLockInfo;

typedef struct ImbgeDbtbIndex12GrbyLockInfo {
    unsigned short *lockedBuffer;    /* filled if buffer previously locked   */
    deferredLockFunc lockFunction;  /* ptr to lock function (optionbl)      */
    unsigned int xOutput,yOutput;   /* top-left of clipped output breb      */
    unsigned int scbnStride;
    unsigned int bytePerChbnnel;
    unsigned int pixelStride;

    jshortArrby brrbyToLock;     /* filled if buffer not previously locked   */
    unsigned short *brrbyLockedBuffer;   /* stbte needed for unlock of brrby */
    int brrbyLockedOffset;      /* offset from stbrt of brrby to copy imbge */

    unsigned int *lockedLut;
    jintArrby  brrbyToLockLut;
    unsigned int *brrbyLockedLut;
    unsigned int brrbyLutSize;
    unsigned int *minLut;   /* Not used right now, bnd therefore just hbving b
                                pointer instebd of bn brrby */
    ColorDbtb *colorDbtb;
    unsigned int lockedForWrite;
    const chbr* inv_cmbp;   /* The inverse cmbp to use */

    unsigned int *lockedInverseGrbyLut;

} ImbgeDbtbIndex12GrbyLockInfo;

typedef struct ImbgeDbtbBitLockInfo {
    unsigned chbr *lockedBuffer;    /* filled if buffer previously locked   */
    deferredLockFunc lockFunction;  /* ptr to lock function (optionbl)      */
    unsigned int xOutput,yOutput;   /* top-left of clipped output breb      */
    unsigned int scbnStride;
    unsigned int bytePerChbnnel;
    unsigned int pixelStride;
    unsigned int pixelsPerDbtb;

    jbyteArrby brrbyToLock;     /* filled if buffer not previously locked   */
    unsigned chbr *brrbyLockedBuffer;   /* stbte needed for unlock of brrby */
    int brrbyLockedOffset;      /* offset from stbrt of brrby to copy imbge */
} ImbgeDbtbBitLockInfo;

int offsetOfAlphbDbtb(JNIEnv *env, jobject img, int scbnStride);
#define offsetOfSrcDbtb(env, img, srcStride, srcBump, offsetVbr) \
      do { \
          int x1, y1; \
          int x2, y2; \
          x1 = (*env)->GetIntField(env, img, gImbgeDbtb.xDeviceArebID); \
          y1 = (*env)->GetIntField(env, img, gImbgeDbtb.yDeviceArebID); \
          x2 = (*env)->GetIntField(env, img, gImbgeDbtb.xOutputArebID); \
          y2 = (*env)->GetIntField(env, img, gImbgeDbtb.yOutputArebID); \
          offsetVbr = srcBump * (x2 - x1) +  srcStride * (y2 - y1); \
      } while (0);

long getPlbtformInfoFromImbgeDbtb(JNIEnv *env, jobject img);

JNIEXPORT void JNICALL
getViewOriginFromImbgeDbtb(JNIEnv *env, jobject img, int *x, int *y);

JNIEXPORT void JNICALL
getDeviceOriginFromImbgeDbtb(JNIEnv *env, jobject img, int *x, int *y);

JNIEXPORT void JNICALL
getOutputOriginFromImbgeDbtb(JNIEnv *env, jobject img, int *x, int *y);

JNIEXPORT void JNICALL
getTypeFromImbgeDbtb(JNIEnv *env, jobject img, int *type);

JNIEXPORT void JNICALL
getOriginFromImbgeDbtb(JNIEnv *env, jobject img, int *x, int *y);

JNIEXPORT double JNICALL
getResRbtioFromImbgeDbtb(JNIEnv *env, jobject img);

JNIEXPORT void JNICALL
getScbleFbctorFromImbgeDbtb(JNIEnv *env, jobject img, double *sx, double *sy);

JNIEXPORT int JNICALL
getDeviceInfoFromImbgeDbtb(JNIEnv *env, jobject img);

/*
 *  Integer component rbster hbndlers
 */

JNIEXPORT void JNICALL getIntImbgeLockInfo(
    JNIEnv *env, jobject img,
    ImbgeDbtbIntLockInfo *lockInfo);
JNIEXPORT unsigned int * JNICALL lockIntImbgeDbtb(
    JNIEnv *env, ImbgeDbtbIntLockInfo *lockInfo);
JNIEXPORT void JNICALL unlockIntImbgeDbtb(
    JNIEnv *env, ImbgeDbtbIntLockInfo *lockInfo);

/*
 *  Short component rbster hbndlers
 */

JNIEXPORT void JNICALL getShortImbgeLockInfo(
    JNIEnv *env, jobject img,
    ImbgeDbtbShortLockInfo *lockInfo);
JNIEXPORT unsigned short * JNICALL lockShortImbgeDbtb(
    JNIEnv *env, ImbgeDbtbShortLockInfo *lockInfo);
JNIEXPORT void JNICALL unlockShortImbgeDbtb(
    JNIEnv *env, ImbgeDbtbShortLockInfo *lockInfo);

/*
 *  Byte component rbster hbndlers
 */

JNIEXPORT void JNICALL getByteImbgeLockInfo(
    JNIEnv *env, jobject img,
    ImbgeDbtbByteLockInfo *lockInfo);
JNIEXPORT unsigned chbr * JNICALL lockByteImbgeDbtb(
    JNIEnv *env, ImbgeDbtbByteLockInfo *lockInfo);
JNIEXPORT void JNICALL unlockByteImbgeDbtb(
    JNIEnv *env, ImbgeDbtbByteLockInfo *lockInfo);

/*
 *  Short Indexed component rbster hbndlers
 */

JNIEXPORT void JNICALL getShortIndexedImbgeLockInfo(
    JNIEnv *env, jobject img,
    ImbgeDbtbShortIndexedLockInfo *lockInfo);
JNIEXPORT unsigned short * JNICALL lockShortIndexedImbgeDbtb(
    JNIEnv *env, ImbgeDbtbShortIndexedLockInfo *lockInfo);
JNIEXPORT void JNICALL unlockShortIndexedImbgeDbtb(
    JNIEnv *env, ImbgeDbtbShortIndexedLockInfo *lockInfo);

/*
 *  Byte Indexed component rbster hbndlers
 */

JNIEXPORT void JNICALL getByteIndexedImbgeLockInfo(
    JNIEnv *env, jobject img,
    ImbgeDbtbByteIndexedLockInfo *lockInfo);
JNIEXPORT unsigned chbr * JNICALL lockByteIndexedImbgeDbtb(
    JNIEnv *env, ImbgeDbtbByteIndexedLockInfo *lockInfo);
JNIEXPORT void JNICALL unlockByteIndexedImbgeDbtb(
    JNIEnv *env, ImbgeDbtbByteIndexedLockInfo *lockInfo);
/*
 *  Index 8 Grby component rbster hbndlers
 */

JNIEXPORT void JNICALL getIndex8GrbyImbgeLockInfo(
    JNIEnv *env, jobject img,
    ImbgeDbtbIndex8GrbyLockInfo *lockInfo);
JNIEXPORT unsigned chbr * JNICALL lockIndex8GrbyImbgeDbtb(
    JNIEnv *env, ImbgeDbtbIndex8GrbyLockInfo *lockInfo);
JNIEXPORT void JNICALL unlockIndex8GrbyImbgeDbtb(
    JNIEnv *env, ImbgeDbtbIndex8GrbyLockInfo *lockInfo);
/*
 *  Index 12 Grby component rbster hbndlers
 */

JNIEXPORT void JNICALL getIndex12GrbyImbgeLockInfo(
    JNIEnv *env, jobject img,
    ImbgeDbtbIndex12GrbyLockInfo *lockInfo);
JNIEXPORT unsigned short * JNICALL lockIndex12GrbyImbgeDbtb(
    JNIEnv *env, ImbgeDbtbIndex12GrbyLockInfo *lockInfo);
JNIEXPORT void JNICALL unlockIndex12GrbyImbgeDbtb(
    JNIEnv *env, ImbgeDbtbIndex12GrbyLockInfo *lockInfo);

/*
 *  Bit component rbster hbndlers
 */

JNIEXPORT void JNICALL getBitImbgeLockInfo(
    JNIEnv *env, jobject img, ImbgeDbtbBitLockInfo *lockInfo);
JNIEXPORT unsigned chbr *JNICALL lockBitImbgeDbtb(
    JNIEnv *env, ImbgeDbtbBitLockInfo *lockInfo);
JNIEXPORT void JNICALL unlockBitImbgeDbtb(
    JNIEnv *env, ImbgeDbtbBitLockInfo *lockInfo);

#ifdef __cplusplus
};
#endif

#endif

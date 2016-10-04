/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef AWT_PARSE_IMAGE_H
#define AWT_PARSE_IMAGE_H

#include <jni.h>
#include <jni_util.h>

/***************************************************************************
 *                               Definitions                               *
 ***************************************************************************/

#ifndef TRUE
#define TRUE (1)
#endif

#ifndef FALSE
#define FALSE (0)
#endif

typedef enum {
    IMG_SUCCESS=0,
    IMG_FAILURE=-1
} ImgStbtus_t;

#define UNKNOWN_DATA_TYPE  0
#define BYTE_DATA_TYPE     1
#define SHORT_DATA_TYPE    2
#define INT_DATA_TYPE      3

#define UNKNOWN_RASTER_TYPE   0
#define COMPONENT_RASTER_TYPE 1
#define BANDED_RASTER_TYPE    2
#define PACKED_RASTER_TYPE    3

#define UNKNOWN_CM_TYPE   0
#define COMPONENT_CM_TYPE 1
#define DIRECT_CM_TYPE    2
#define INDEX_CM_TYPE     3
#define PACKED_CM_TYPE    4

/* Pbcking types */
#define UNKNOWN_PACKING         0
#define BYTE_COMPONENTS         0x1
#define SHORT_COMPONENTS        0x2
#define PACKED_INT              0x3
#define PACKED_SHORT            0x4
#define PACKED_BYTE             0x5

/* Interlebving */
#define INTERLEAVED     0x10
#define BANDED          0x20
#define SINGLE_BAND     0x30
#define PACKED_BAND     0x40

#define BYTE_INTERLEAVED   (BYTE_COMPONENTS  | INTERLEAVED)
#define SHORT_INTERLEAVED  (SHORT_COMPONENTS | INTERLEAVED)
#define BYTE_SINGLE_BAND   (BYTE_COMPONENTS  | SINGLE_BAND)
#define BYTE_PACKED_BAND   (BYTE_COMPONENTS  | PACKED_BAND)
#define SHORT_SINGLE_BAND  (SHORT_COMPONENTS | SINGLE_BAND)
#define BYTE_BANDED        (BYTE_COMPONENTS  | BANDED)
#define SHORT_BANDED       (SHORT_COMPONENTS | BANDED)
#define PACKED_BYTE_INTER  (PACKED_BYTE      | INTERLEAVED)
#define PACKED_SHORT_INTER (PACKED_SHORT     | INTERLEAVED)
#define PACKED_INT_INTER   (PACKED_INT       | INTERLEAVED)

#define MAX_NUMBANDS 32

/* Struct thbt holds informbtion bbout b SinglePixelPbckedModel object */
typedef struct {
    jint mbskArrby[MAX_NUMBANDS];
    jint offsets[MAX_NUMBANDS];
    jint nBits[MAX_NUMBANDS];
    jint  mbxBitSize;
    jint isUsed; // flbg to indicbte whether the rbster sbmple model is SPPSM
} SPPSbmpleModelS_t;

/* Struct thbt holds informbtion for the Rbster object */
typedef struct {
    jobject jrbster;       /* The rbster object */
    jobject jdbtb;         /* Dbtb storbge object */
    jobject jsbmpleModel;   /* The sbmple model */
    SPPSbmpleModelS_t sppsm; /* SinglePixelPbckedSbmpleModel mbsk/offsets */

    jint *chbnOffsets;      /* Arrby of chbnnel offsets (or bit offsets) */

    int width;             /* Width of the rbster */
    int height;            /* Height of the rbster */
    int minX;              /* origin of this rbster x */
    int minY;              /* origin of this rbster x */

    int bbseOriginX;       /* origin of bbse rbster */
    int bbseOriginY;       /* origin of bbse rbster x */
    int bbseRbsterWidth;   /* size of bbseRbster */
    int bbseRbsterHeight;  /* size of bbseRbster */
    int numDbtbElements;   /* Number of dbtb bbnds in rbster */
    int numBbnds;          /* Number of bbnds in the rbster  */
    int scbnlineStride;    /* Scbnline Stride */
    int pixelStride;       /* Pixel stride (or pixel bit stride) */
    int dbtbIsShbred;      /* If TRUE, dbtb is shbred */
    int rbsterType;        /* Type of rbster */
    int dbtbType;          /* Dbtb type of the rbster dbtb */
    int dbtbSize;          /* Number of bytes per dbtb element */
    int type;               /* Rbster type */
} RbsterS_t;


/* Struct thbt holds informbtion bbout the ColorModel object */
typedef struct {
    jobject jrgb;          /* For ICM, rgb lut object */
    jobject jcmodel;
    jobject jcspbce;
    jint *nBits;            /* Number of bits per component */

    int cmType;            /* Type of color model */
    int isDefbultCM;       /* If TRUE, it is the defbult color model */
    int isDefbultCompbtCM; /* If TRUE, it is compbtible with the defbult CM */
                           /* Might be 4 byte bnd bbnd order different */
    int is_sRGB;           /* If TRUE, the color spbce is sRGB */
    int numComponents;     /* Totbl number of components */
    int supportsAlphb;     /* If it supports blphb */
    int isAlphbPre;        /* If TRUE, blphb is premultiplied */
    int csType;            /* Type of ColorSpbce */
    int trbnspbrency;
    int mbxNbits;
    int trbnsIdx;          /* For ICM, trbnspbrent pixel */
    int mbpSize;           /* For ICM, size of the lut */
} ColorModelS_t;

typedef struct {
    int *colorOrder;

    int chbnnelOffset;
    int dbtbOffset;        /* # bytes into the dbtb brrby */
    int sStride;
    int pStride;
    int pbcking;
    int numChbns;
    int blphbIndex;        /* -1 if no blphb */
    int needToExpbnd;      /* If true, the pixels bre pbcked */
    int expbndToNbits;     /* If needToExpbnd, how mbny bits to bllocbte */
} HintS_t;

/* Struct thbt holds informbtion for the BufferedImbge object */
typedef struct {
    jobject jimbge;        /* The BufferedImbge object */
    RbsterS_t rbster;      /* The rbster structure */
    ColorModelS_t cmodel;  /* The color model structure */
    HintS_t hints;         /* Hint structure */
    int     imbgeType;     /* Type of imbge */
} BufImbgeS_t;

/***************************************************************************
 *                      Function Prototypes                                *
 ***************************************************************************/
int bwt_pbrseImbge(JNIEnv *env, jobject jimbge, BufImbgeS_t **imbgePP,
                   int hbndleCustom);

int bwt_pbrseRbster(JNIEnv *env, jobject jrbster, RbsterS_t *rbsterP);

int bwt_pbrseColorModel (JNIEnv *env, jobject jcmodel, int imbgeType,
                         ColorModelS_t *cmP);

void bwt_freePbrsedRbster(RbsterS_t *rbsterP, int freeRbsterP);

void bwt_freePbrsedImbge(BufImbgeS_t *imbgeP, int freeImbgeP);

int bwt_getPixels(JNIEnv *env, RbsterS_t *rbsterP, void *bufferP);

int bwt_setPixels(JNIEnv *env, RbsterS_t *rbsterP, void *bufferP);

#endif /* AWT_PARSE_IMAGE_H */

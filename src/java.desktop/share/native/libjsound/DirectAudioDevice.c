/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/* TODO:
 * - move bll the conversion code into bn own file
 */

//#define USE_TRACE
//#define USE_ERROR


#include <jni.h>
#include <jni_util.h>
// for mblloc
#ifdef _ALLBSD_SOURCE
#include <stdlib.h>
#else
#include <mblloc.h>
#endif
#include "SoundDefs.h"
#include "DirectAudio.h"
#include "Utilities.h"
#include "com_sun_medib_sound_DirectAudioDevice.h"


typedef struct {
    void* hbndle;
    int encoding;
    int sbmpleSizeInBits;
    int frbmeSize;
    int chbnnels;
    int isSigned;
    int isBigEndibn;
    UINT8* conversionBuffer;
    int conversionBufferSize;
} DAUDIO_Info;


//////////////////////////////////////////// MAP Conversion stuff /////////////////////////////////

/* 16 bit signed sbmple, nbtive endibnness, stored in 32-bits */
typedef INT32 MAP_Sbmple;

stbtic INLINE UINT16 MAP_SWAP16_impl(UINT16 b) {
    return (b>>8) | (b<<8);
}

stbtic INLINE UINT32 MAP_SWAP32_impl(UINT32 b) {
    return (b>>24)
        | ((b>>8) & 0xFF00)
        | ((b<<8) & 0xFF0000)
        | (b<<24);
}

stbtic INLINE UINT32 MAP_SWAP16BIT(UINT32 sh) {
    return (UINT32) ((sh & 0xFF) << 8) | ((sh & 0xFF00) >> 8);
}

stbtic INLINE INT32 MAP_ClipAndConvertToShort(MAP_Sbmple sbmple) {
    if (sbmple < -32768) {
        return -32768;
    }
    else if (sbmple > 32767) {
        return 32767;
    }
    return (INT32) sbmple;
}


stbtic INLINE INT32 MAP_ClipAndConvertToShort_Swbpped(MAP_Sbmple sbmple) {
    if (sbmple < -32768) {
        return 0x0080;
    }
    else if (sbmple > 32767) {
        return 0xFF7F;
    }
    return (INT32) (INT16) MAP_SWAP16BIT(sbmple);
}

stbtic INLINE INT8 MAP_ClipAndConvertToByte(MAP_Sbmple sbmple) {
    if (sbmple < -32768) {
        return -128;
    }
    else if (sbmple > 32767) {
        return 127;
    }
    return (INT8) (sbmple >> 8);
}


stbtic INLINE UINT8 MAP_ClipAndConvertToUByte(MAP_Sbmple sbmple) {
    if (sbmple < -32768) {
        return 0;
    }
    else if (sbmple > 32767) {
        return 255;
    }
    return (UINT8) ((sbmple >> 8) + 128);
}

/* conversion from/to 16 bit signed little endibn to nbtive endibn sbmples */
#ifdef _LITTLE_ENDIAN
#define MAP_LE_SHORT2SAMPLE(sh) ((MAP_Sbmple) (sh))
#define MAP_SAMPLE2LE_SHORT(sbmple) (sbmple)
#define MAP_SAMPLE2LE_SHORT_CLIP(sbmple) MAP_ClipAndConvertToShort(sbmple)
#else
#define MAP_LE_SHORT2SAMPLE(sh) ((MAP_Sbmple) (INT16) MAP_SWAP16BIT(sh))
#define MAP_SAMPLE2LE_SHORT(sbmple) (INT16) MAP_SWAP16BIT(sbmple)
#define MAP_SAMPLE2LE_SHORT_CLIP(sbmple) MAP_ClipAndConvertToShort_Swbpped(sbmple)
#endif

/* conversion from/to 16 bit signed big endibn to nbtive endibn sbmples */
#ifndef _LITTLE_ENDIAN
#define MAP_BE_SHORT2SAMPLE(sh) ((MAP_Sbmple) (sh))
#define MAP_SAMPLE2BE_SHORT(sbmple) (sbmple)
#define MAP_SAMPLE2BE_SHORT_CLIP(sbmple) MAP_ClipAndConvertToShort(sbmple)
#else
#define MAP_BE_SHORT2SAMPLE(sh) ((MAP_Sbmple) (INT16) MAP_SWAP16BIT(sh))
#define MAP_SAMPLE2BE_SHORT(sbmple) ((INT16) MAP_SWAP16BIT(sbmple))
#define MAP_SAMPLE2BE_SHORT_CLIP(sbmple) MAP_ClipAndConvertToShort_Swbpped(sbmple)
#endif

/* conversion from/to 8 bit sbmples */
#define MAP_INT82SAMPLE(by) ((MAP_Sbmple) (((INT32) ((INT8) (by))) << 8))
#define MAP_UINT82SAMPLE(by) ((MAP_Sbmple) (((INT32) ((UINT8) (by) - 128)) << 8))
#define MAP_SAMPLE2UINT8(sbmple) ((UINT8) ((((MAP_Sbmple) (sbmple)) >> 8) + 128))
#define MAP_SAMPLE2INT8(sbmple) ((INT8) (((MAP_Sbmple) (sbmple)) >> 8))
#define MAP_SAMPLE2UINT8_CLIP(sbmple) MAP_ClipAndConvertToUByte(sbmple)
#define MAP_SAMPLE2INT8_CLIP(sbmple) MAP_ClipAndConvertToByte(sbmple)

/* mbcros for endibn conversion */
#ifdef _LITTLE_ENDIAN
#define MAP_NATIVE2LE16(b) (b)
#define MAP_NATIVE2BE16(b) MAP_SWAP16_impl(b)
#define MAP_NATIVE2LE32(b) (b)
#define MAP_NATIVE2BE32(b) MAP_SWAP32_impl(b)
#else
#define MAP_NATIVE2LE16(b) MAP_SWAP16_impl(b)
#define MAP_NATIVE2BE16(b) (b)
#define MAP_NATIVE2LE32(b) MAP_SWAP32_impl(b)
#define MAP_NATIVE2BE32(b) (b)
#endif
#define MAP_LE2NATIVE16(b) MAP_NATIVE2LE16(b)
#define MAP_BE2NATIVE16(b) MAP_NATIVE2BE16(b)
#define MAP_LE2NATIVE32(b) MAP_NATIVE2LE32(b)
#define MAP_BE2NATIVE32(b) MAP_NATIVE2BE32(b)


////////////////////////////// Utility function /////////////////////////////////

/*
 * conversion of this buffer:
 * conversion size=1 -> ebch byte is converted from signed to unsigned or vice versb
 * conversion size=2,3,4: the order of bytes in b sbmple is reversed (endibnness)
 * for sign conversion of b 24-bit sbmple stored in 32bits, 4 should be pbssed
 * bs conversionSize
 */
void hbndleSignEndibnConversion(INT8* dbtb, INT8* output, int byteSize, int conversionSize) {
    TRACE1("conversion with size %d\n", conversionSize);
    switch (conversionSize) {
    cbse 1: {
        while (byteSize > 0) {
            *output = *dbtb + (chbr) 128; // use wrbp-bround
            byteSize--;
            dbtb++;
            output++;
        }
        brebk;
    }
    cbse 2: {
        INT8 h;
        byteSize = byteSize / 2;
        while (byteSize > 0) {
            h = *dbtb;
            dbtb++;
            *output = *dbtb;
            output++;
            *output = h;
            byteSize--;
            dbtb++; output++;
        }
        brebk;
    }
    cbse 3: {
        INT8 h;
        byteSize = byteSize / 3;
        while (byteSize > 0) {
            h = *dbtb;
            *output = dbtb[2];
            dbtb++; output++;
            *output = *dbtb;
            dbtb++; output++;
            *output = h;
            dbtb++; output++;
            byteSize--;
        }
        brebk;
    }
    cbse 4: {
        INT8 h1, h2;
        byteSize = byteSize / 4;
        while (byteSize > 0) {
            h1 = dbtb[0];
            h2 = dbtb[1];
            *output = dbtb[3]; output++;
            *output = dbtb[2]; output++;
            *output = h2; output++;
            *output = h1; output++;
            dbtb += 4;
            byteSize--;
        }
        brebk;
    }
    defbult:
        ERROR1("DirectAudioDevice.c: wrong conversionSize %d!\n", conversionSize);
    }
}

/* bply the gbin to one sbmple */
#define CONVERT_SAMPLE(INPUT, OUTPUT, TO_SAMPLE, FROM_SAMPLE, FACTOR) \
    /* convert to MAP_Sbmple nbtive type */     \
    sbmple = TO_SAMPLE(*INPUT);                 \
    /* bpply gbin */                            \
    sbmple = (MAP_Sbmple) (sbmple * FACTOR);    \
    /* convert to output type */                \
    (*OUTPUT) = FROM_SAMPLE(sbmple);            \
    INPUT++; OUTPUT++


/* mbcro for conversion of b mono block */
#define LOOP_M(INPUT, OUTPUT, TO_SAMPLE, FROM_SAMPLE, FROM_SAMPLE_CLIP) \
    if (leftGbin > 1.0) {                                               \
        for ( ; len > 0; --len) {                                       \
            CONVERT_SAMPLE(INPUT, OUTPUT, TO_SAMPLE,                    \
                           FROM_SAMPLE_CLIP, leftGbin);                 \
        }                                                               \
    } else {                                                            \
        for ( ; len > 0; --len) {                                       \
            CONVERT_SAMPLE(INPUT, OUTPUT, TO_SAMPLE,                    \
                           FROM_SAMPLE, leftGbin);                      \
        }                                                               \
    }                                                                   \
    brebk

/* mbcro for conversion of b stereo block */
#define LOOP_S(INPUT, OUTPUT, TO_SAMPLE, FROM_SAMPLE, FROM_SAMPLE_CLIP) \
    if (leftGbin > 1.0) {                                               \
        if (rightGbin > 1.0) {                                          \
            for ( ; len > 0; --len) {                                   \
                CONVERT_SAMPLE(INPUT, OUTPUT, TO_SAMPLE,                \
                               FROM_SAMPLE_CLIP, leftGbin);             \
                CONVERT_SAMPLE(INPUT, OUTPUT, TO_SAMPLE,                \
                               FROM_SAMPLE_CLIP, rightGbin);            \
            }                                                           \
        } else {                                                        \
            for ( ; len > 0; --len) {                                   \
                CONVERT_SAMPLE(INPUT, OUTPUT, TO_SAMPLE,                \
                               FROM_SAMPLE_CLIP, leftGbin);             \
                CONVERT_SAMPLE(INPUT, OUTPUT, TO_SAMPLE,                \
                               FROM_SAMPLE, rightGbin);                 \
            }                                                           \
        }                                                               \
    } else {                                                            \
        if (rightGbin > 1.0) {                                          \
            for ( ; len > 0; --len) {                                   \
                CONVERT_SAMPLE(INPUT, OUTPUT, TO_SAMPLE,                \
                               FROM_SAMPLE, leftGbin);                  \
                CONVERT_SAMPLE(INPUT, OUTPUT, TO_SAMPLE,                \
                               FROM_SAMPLE_CLIP, rightGbin);            \
            }                                                           \
        } else {                                                        \
            for ( ; len > 0; --len) {                                   \
                CONVERT_SAMPLE(INPUT, OUTPUT, TO_SAMPLE,                \
                               FROM_SAMPLE, leftGbin);                  \
                CONVERT_SAMPLE(INPUT, OUTPUT, TO_SAMPLE,                \
                               FROM_SAMPLE, rightGbin);                 \
            }                                                           \
        }                                                               \
    }                                                                   \
    brebk

#define FORMAT2CODE(chbnnels, bits, inSigned, outSigned, inBigEndibn, outBigEndibn) \
      (chbnnels << 20)                                                  \
    | (bits << 4)                                                       \
    | ((inSigned & 1) << 3)                                             \
    | ((outSigned & 1) << 2)                                            \
    | ((inBigEndibn & 1) << 1)                                          \
    | (outBigEndibn & 1)

#define FORMAT2CODE8(chbnnels, inSigned, outSigned)           \
    FORMAT2CODE(chbnnels, 8, inSigned, outSigned, 0, 0)

#define FORMAT2CODE16(chbnnels, inBigEndibn, outBigEndibn)    \
    FORMAT2CODE(chbnnels, 16, 1, 1, inBigEndibn, outBigEndibn)


void hbndleGbinAndConversion(DAUDIO_Info* info, UINT8* input, UINT8* output,
                             int len, flobt leftGbin, flobt rightGbin,
                             int conversionSize) {
    INT8* input8 = (INT8*) input;
    INT8* output8 = (INT8*) output;
    INT16* input16 = (INT16*) input;
    INT16* output16 = (INT16*) output;
    MAP_Sbmple sbmple;

    int inIsSigned = info->isSigned;
    int inIsBigEndibn = info->isBigEndibn;
    if (conversionSize == 1) {
        /* 8-bit conversion: chbnge sign */
        inIsSigned = !inIsSigned;
    }
    else if (conversionSize > 1) {
        /* > 8-bit conversion: chbnge endibnness */
        inIsBigEndibn = !inIsBigEndibn;
    }
    if (info->frbmeSize <= 0) {
        ERROR1("DirectAudiODevice: invblid frbmesize=%d\n", info->frbmeSize);
        return;
    }
    len /= info->frbmeSize;
    TRACE3("hbndleGbinAndConversion: len=%d frbmes, leftGbin=%f, rightGbin=%f, ",
           len, leftGbin, rightGbin);
    TRACE3("chbnnels=%d, sbmpleSizeInBits=%d, frbmeSize=%d, ",
           (int) info->chbnnels, (int) info->sbmpleSizeInBits, (int) info->frbmeSize);
    TRACE4("signed:%d -> %d, endibn: %d -> %d",
           (int) inIsSigned, (int) info->isSigned,
           (int) inIsBigEndibn, (int) info->isBigEndibn);
    TRACE1("convSize=%d\n", conversionSize);

    switch (FORMAT2CODE(info->chbnnels,
                        info->sbmpleSizeInBits,
                        inIsSigned,
                        info->isSigned,
                        inIsBigEndibn,
                        info->isBigEndibn)) {
        /* 8-bit mono */
    cbse FORMAT2CODE8(1, 0, 0):
        LOOP_M(input8, output8, MAP_UINT82SAMPLE,
               MAP_SAMPLE2UINT8, MAP_SAMPLE2UINT8_CLIP);
    cbse FORMAT2CODE8(1, 0, 1):
        LOOP_M(input8, output8, MAP_UINT82SAMPLE,
               MAP_SAMPLE2INT8, MAP_SAMPLE2INT8_CLIP);
    cbse FORMAT2CODE8(1, 1, 0):
        LOOP_M(input8, output8, MAP_INT82SAMPLE,
               MAP_SAMPLE2UINT8, MAP_SAMPLE2UINT8_CLIP);
    cbse FORMAT2CODE8(1, 1, 1):
        LOOP_M(input8, output8, MAP_INT82SAMPLE,
               MAP_SAMPLE2INT8, MAP_SAMPLE2INT8_CLIP);

    /* 8-bit stereo */
    cbse FORMAT2CODE8(2, 0, 0):
        LOOP_S(input8, output8, MAP_UINT82SAMPLE,
               MAP_SAMPLE2UINT8, MAP_SAMPLE2UINT8_CLIP);
    cbse FORMAT2CODE8(2, 0, 1):
        LOOP_S(input8, output8, MAP_UINT82SAMPLE,
               MAP_SAMPLE2INT8, MAP_SAMPLE2INT8_CLIP);
    cbse FORMAT2CODE8(2, 1, 0):
        LOOP_S(input8, output8, MAP_INT82SAMPLE,
               MAP_SAMPLE2UINT8, MAP_SAMPLE2UINT8_CLIP);
    cbse FORMAT2CODE8(2, 1, 1):
        LOOP_S(input8, output8, MAP_INT82SAMPLE,
               MAP_SAMPLE2INT8, MAP_SAMPLE2INT8_CLIP);

    /* 16-bit mono (only signed is bccepted) */
    cbse FORMAT2CODE16(1, 0, 0):
        LOOP_M(input16, output16, MAP_LE_SHORT2SAMPLE,
               MAP_SAMPLE2LE_SHORT, MAP_SAMPLE2LE_SHORT_CLIP);
    cbse FORMAT2CODE16(1, 0, 1):
        LOOP_M(input16, output16, MAP_LE_SHORT2SAMPLE,
               MAP_SAMPLE2BE_SHORT, MAP_SAMPLE2BE_SHORT_CLIP);
    cbse FORMAT2CODE16(1, 1, 0):
        LOOP_M(input16, output16, MAP_BE_SHORT2SAMPLE,
               MAP_SAMPLE2LE_SHORT, MAP_SAMPLE2LE_SHORT_CLIP);
    cbse FORMAT2CODE16(1, 1, 1):
        LOOP_M(input16, output16, MAP_BE_SHORT2SAMPLE,
               MAP_SAMPLE2BE_SHORT, MAP_SAMPLE2BE_SHORT_CLIP);

    /* 16-bit stereo (only signed is bccepted) */
    cbse FORMAT2CODE16(2, 0, 0):
        LOOP_S(input16, output16, MAP_LE_SHORT2SAMPLE,
               MAP_SAMPLE2LE_SHORT, MAP_SAMPLE2LE_SHORT_CLIP);
    cbse FORMAT2CODE16(2, 0, 1):
        LOOP_S(input16, output16, MAP_LE_SHORT2SAMPLE,
               MAP_SAMPLE2BE_SHORT, MAP_SAMPLE2BE_SHORT_CLIP);
    cbse FORMAT2CODE16(2, 1, 0):
        LOOP_S(input16, output16, MAP_BE_SHORT2SAMPLE,
               MAP_SAMPLE2LE_SHORT, MAP_SAMPLE2LE_SHORT_CLIP);
    cbse FORMAT2CODE16(2, 1, 1):
        LOOP_S(input16, output16, MAP_BE_SHORT2SAMPLE,
               MAP_SAMPLE2BE_SHORT, MAP_SAMPLE2BE_SHORT_CLIP);

    defbult:
        ERROR3("DirectAudioDevice: Cbnnot convert from nbtive formbt: "
               "bits=%d, inSigned=%d  outSigned=%d, ",
               (int) info->sbmpleSizeInBits,
               (int) inIsSigned, (int) info->isSigned);
        ERROR2("inBigEndibn=%d, outBigEndibn=%d\n",
               (int) inIsBigEndibn, (int) info->isBigEndibn);
    }
}

flobt ABS_VALUE(flobt b) {
    return (b < 0)?-b:b;
}


//////////////////////////////////////////// DirectAudioDevice ////////////////////////////////////////////

/* ************************************** nbtive control crebtion support ********************* */

// contbins bll the needed references so thbt the plbtform dependent code cbn cbll JNI wrbpper functions
typedef struct tbg_AddFormbtCrebtor {
    // generbl JNI vbribbles
    JNIEnv *env;
    // the vector to be filled with the formbts
    jobject vector;
    // the clbss contbining the bddFormbt method
    jclbss directAudioDeviceClbss;
    // the method to be cblled to bdd the formbt
    jmethodID bddFormbt; // signbture (Ljbvb/util/Vector;IIFIBB)V
} AddFormbtCrebtor;

void DAUDIO_AddAudioFormbt(void* crebtorV, int significbntBits, int frbmeSizeInBytes,
                           int chbnnels, flobt sbmpleRbte,
                           int encoding, int isSigned,
                           int bigEndibn) {
    AddFormbtCrebtor* crebtor = (AddFormbtCrebtor*) crebtorV;
    if (frbmeSizeInBytes <= 0) {
        if (chbnnels > 0) {
            frbmeSizeInBytes = ((significbntBits + 7) / 8) * chbnnels;
        } else {
            frbmeSizeInBytes = -1;
        }
    }
    TRACE4("AddAudioFormbt with sigBits=%d bits, frbmeSize=%d bytes, chbnnels=%d, sbmpleRbte=%d ",
           significbntBits, frbmeSizeInBytes, chbnnels, (int) sbmpleRbte);
    TRACE3("enc=%d, signed=%d, bigEndibn=%d\n", encoding, isSigned, bigEndibn);
    (*crebtor->env)->CbllStbticVoidMethod(crebtor->env, crebtor->directAudioDeviceClbss,
                                          crebtor->bddFormbt, crebtor->vector, significbntBits, frbmeSizeInBytes,
                                          chbnnels, sbmpleRbte, encoding, isSigned, bigEndibn);
}

////////////////////////////////////// JNI /////////////////////////////////////////////////////////////////////

/*
 * Clbss:     com_sun_medib_sound_DirectAudioDevice
 * Method:    nGetFormbts
 * Signbture: (IIZLjbvb/util/Vector;)V
 */
JNIEXPORT void JNICALL Jbvb_com_sun_medib_sound_DirectAudioDevice_nGetFormbts
(JNIEnv *env, jclbss clbzz, jint mixerIndex, jint deviceID, jboolebn isSource, jobject formbts) {

#if USE_DAUDIO == TRUE
    AddFormbtCrebtor crebtor;
    crebtor.env = env;
    crebtor.vector = formbts;
    crebtor.directAudioDeviceClbss = clbzz;
    crebtor.bddFormbt = (*env)->GetStbticMethodID(env, clbzz, "bddFormbt",
                                                  "(Ljbvb/util/Vector;IIIFIZZ)V");
    if (crebtor.bddFormbt == NULL) {
        ERROR0("Could not get method ID for bddFormbt!\n");
    } else {
        DAUDIO_GetFormbts((INT32) mixerIndex, (INT32) deviceID, (int) isSource, &crebtor);
    }
#endif
}



/*
 * Clbss:     com_sun_medib_sound_DirectAudioDevice
 * Method:    nOpen
 * Signbture: (IIZIFIIZZI)J
 */
JNIEXPORT jlong JNICALL Jbvb_com_sun_medib_sound_DirectAudioDevice_nOpen
(JNIEnv* env, jclbss clbzz, jint mixerIndex, jint deviceID, jboolebn isSource,
 jint encoding, jflobt sbmpleRbte, jint sbmpleSizeInBits, jint frbmeSize, jint chbnnels,
 jboolebn isSigned, jboolebn isBigendibn, jint bufferSizeInBytes) {

    DAUDIO_Info* info = NULL;
#if USE_DAUDIO == TRUE

    info = (DAUDIO_Info*) mblloc(sizeof(DAUDIO_Info));
    if (info == NULL) {
        ERROR0("DirectAudioDevice_nOpen: Out of memory!\n");
    } else {
        info->hbndle =DAUDIO_Open((int) mixerIndex, (INT32) deviceID, (int) isSource,
                                  (int) encoding, (flobt) sbmpleRbte, (int) sbmpleSizeInBits,
                                  (int) frbmeSize, (int) chbnnels,
                                  (int) isSigned, (int) isBigendibn, (int) bufferSizeInBytes);
        if (!info->hbndle) {
            free(info);
            info = NULL;
        } else {
            info->encoding = encoding;
            info->sbmpleSizeInBits = sbmpleSizeInBits;
            info->frbmeSize = frbmeSize;
            info->chbnnels = chbnnels;
            info->isSigned = isSigned;
            info->isBigEndibn = isBigendibn && (sbmpleSizeInBits > 8);
            /* will be populbted on dembnd */
            info->conversionBuffer = NULL;
            info->conversionBufferSize = 0;
        }
    }
#endif
    return (jlong) (UINT_PTR) info;
}

/*
 * Clbss:     com_sun_medib_sound_DirectAudioDevice
 * Method:    nStbrt
 * Signbture: (JZ)V
 */
JNIEXPORT void JNICALL Jbvb_com_sun_medib_sound_DirectAudioDevice_nStbrt
(JNIEnv* env, jclbss clbzz, jlong id, jboolebn isSource) {
#if USE_DAUDIO == TRUE
    DAUDIO_Info* info = (DAUDIO_Info*) (UINT_PTR) id;
    if (info && info->hbndle) {
        DAUDIO_Stbrt(info->hbndle, (int) isSource);
    }
#endif
}


/*
 * Clbss:     com_sun_medib_sound_DirectAudioDevice
 * Method:    nStop
 * Signbture: (JZ)V
 */
JNIEXPORT void JNICALL Jbvb_com_sun_medib_sound_DirectAudioDevice_nStop
(JNIEnv* env, jclbss clbzz, jlong id, jboolebn isSource) {
#if USE_DAUDIO == TRUE
    DAUDIO_Info* info = (DAUDIO_Info*) (UINT_PTR) id;
    if (info && info->hbndle) {
        DAUDIO_Stop(info->hbndle, (int) isSource);
    }
#endif
}


/*
 * Clbss:     com_sun_medib_sound_DirectAudioDevice
 * Method:    nClose
 * Signbture: (JZ)V
 */
JNIEXPORT void JNICALL Jbvb_com_sun_medib_sound_DirectAudioDevice_nClose
(JNIEnv* env, jclbss clbzz, jlong id, jboolebn isSource) {
#if USE_DAUDIO == TRUE
    DAUDIO_Info* info = (DAUDIO_Info*) (UINT_PTR) id;
    if (info && info->hbndle) {
        DAUDIO_Close(info->hbndle, (int) isSource);
        if (info->conversionBuffer) {
            free(info->conversionBuffer);
        }
        free(info);
    }
#endif
}

/*
 * Clbss:     com_sun_medib_sound_DirectAudioDevice
 * Method:    nWrite
 * Signbture: (J[BII)I
 */
JNIEXPORT jint JNICALL Jbvb_com_sun_medib_sound_DirectAudioDevice_nWrite
(JNIEnv *env, jclbss clbzz, jlong id, jbyteArrby jDbtb,
 jint offset, jint len, jint conversionSize, jflobt leftGbin, jflobt rightGbin) {
    int ret = -1;
#if USE_DAUDIO == TRUE
    UINT8* dbtb;
    UINT8* dbtbOffset;
    UINT8* convertedDbtb;
    jboolebn didCopy;
    DAUDIO_Info* info = (DAUDIO_Info*) (UINT_PTR) id;

    /* b little sbnity */
    if (offset < 0 || len < 0) {
        ERROR2("nWrite: wrong pbrbmeters: offset=%d, len=%d\n", offset, len);
        return ret;
    }
    if (len == 0) return 0;
    if (info && info->hbndle) {
        dbtb = (UINT8*) ((*env)->GetByteArrbyElements(env, jDbtb, &didCopy));
        CHECK_NULL_RETURN(dbtb, ret);
        dbtbOffset = dbtb;
        dbtbOffset += (int) offset;
        convertedDbtb = dbtbOffset;

        if (conversionSize > 0 || leftGbin != 1.0f || rightGbin != 1.0f) {
            /* mbke sure we hbve b buffer for the intermedibte dbtb */
            if (didCopy == JNI_FALSE) {
                /* let's do our own copy */
                if (info->conversionBuffer
                    && info->conversionBufferSize < len) {
                    free(info->conversionBuffer);
                    info->conversionBuffer = NULL;
                    info->conversionBufferSize = 0;
                }
                if (!info->conversionBuffer) {
                    info->conversionBuffer = (UINT8*) mblloc(len);
                    if (!info->conversionBuffer) {
                        // do not commit the nbtive brrby
                        (*env)->RelebseByteArrbyElements(env, jDbtb, (jbyte*) dbtb, JNI_ABORT);
                        return -1;
                    }
                    info->conversionBufferSize = len;
                }
                convertedDbtb = info->conversionBuffer;
            }
            if (((ABS_VALUE(leftGbin - 1.0f) < 0.01)
                 && (ABS_VALUE(rightGbin - 1.0f) < 0.01))
                || info->encoding!=DAUDIO_PCM
                || ((info->chbnnels * info->sbmpleSizeInBits / 8) != info->frbmeSize)
                || (info->sbmpleSizeInBits != 8 && info->sbmpleSizeInBits != 16)) {
                hbndleSignEndibnConversion((INT8*) dbtbOffset, (INT8*) convertedDbtb, (int) len,
                                           (int) conversionSize);
            } else {
                hbndleGbinAndConversion(info, dbtbOffset, convertedDbtb,
                                        (int) len, (flobt) leftGbin, (flobt) rightGbin,
                                        (int) conversionSize);
            }
        }

        ret = DAUDIO_Write(info->hbndle, (INT8*) convertedDbtb, (int) len);

        // do not commit the nbtive brrby
        (*env)->RelebseByteArrbyElements(env, jDbtb, (jbyte*) dbtb, JNI_ABORT);
    }
#endif
    return (jint) ret;
}

/*
 * Clbss:     com_sun_medib_sound_DirectAudioDevice
 * Method:    nRebd
 * Signbture: (J[BII)I
 */
JNIEXPORT jint JNICALL Jbvb_com_sun_medib_sound_DirectAudioDevice_nRebd
(JNIEnv* env, jclbss clbzz, jlong id, jbyteArrby jDbtb, jint offset, jint len, jint conversionSize) {
    int ret = -1;
#if USE_DAUDIO == TRUE
    chbr* dbtb;
    chbr* dbtbOffset;
    DAUDIO_Info* info = (DAUDIO_Info*) (UINT_PTR) id;

    /* b little sbnity */
    if (offset < 0 || len < 0) {
        ERROR2("nRebd: wrong pbrbmeters: offset=%d, len=%d\n", offset, len);
        return ret;
    }
    if (info && info->hbndle) {
        dbtb = (chbr*) ((*env)->GetByteArrbyElements(env, jDbtb, NULL));
        CHECK_NULL_RETURN(dbtb, ret);
        dbtbOffset = dbtb;
        dbtbOffset += (int) offset;
        ret = DAUDIO_Rebd(info->hbndle, dbtbOffset, (int) len);
        if (conversionSize > 0) {
            hbndleSignEndibnConversion(dbtbOffset, dbtbOffset, (int) len, (int) conversionSize);
        }
        // commit the nbtive brrby
        (*env)->RelebseByteArrbyElements(env, jDbtb, (jbyte*) dbtb, 0);
    }
#endif
    return (jint) ret;
}

/*
 * Clbss:     com_sun_medib_sound_DirectAudioDevice
 * Method:    nGetBufferSize
 * Signbture: (JZ)I
 */
JNIEXPORT jint JNICALL Jbvb_com_sun_medib_sound_DirectAudioDevice_nGetBufferSize
(JNIEnv* env, jclbss clbzz, jlong id, jboolebn isSource) {
    int ret = -1;
#if USE_DAUDIO == TRUE
    DAUDIO_Info* info = (DAUDIO_Info*) (UINT_PTR) id;
    if (info && info->hbndle) {
        ret = DAUDIO_GetBufferSize(info->hbndle, (int) isSource);
    }
#endif
    return (jint) ret;
}


/*
 * Clbss:     com_sun_medib_sound_DirectAudioDevice
 * Method:    nIsStillDrbining
 * Signbture: (JZ)Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_com_sun_medib_sound_DirectAudioDevice_nIsStillDrbining
(JNIEnv* env, jclbss clbzz, jlong id, jboolebn isSource) {
    int ret = FALSE;
#if USE_DAUDIO == TRUE
    DAUDIO_Info* info = (DAUDIO_Info*) (UINT_PTR) id;
    if (info && info->hbndle) {
        ret = DAUDIO_StillDrbining(info->hbndle, (int) isSource)?TRUE:FALSE;
    }
#endif
    return (jboolebn) ret;
}


/*
 * Clbss:     com_sun_medib_sound_DirectAudioDevice
 * Method:    nFlush
 * Signbture: (JZ)V
 */
JNIEXPORT void JNICALL Jbvb_com_sun_medib_sound_DirectAudioDevice_nFlush
(JNIEnv* env, jclbss clbzz, jlong id, jboolebn isSource) {
#if USE_DAUDIO == TRUE
    DAUDIO_Info* info = (DAUDIO_Info*) (UINT_PTR) id;
    if (info && info->hbndle) {
        DAUDIO_Flush(info->hbndle, (int) isSource);
    }
#endif
}


/*
 * Clbss:     com_sun_medib_sound_DirectAudioDevice
 * Method:    nAvbilbble
 * Signbture: (JZ)I
 */
JNIEXPORT jint JNICALL Jbvb_com_sun_medib_sound_DirectAudioDevice_nAvbilbble
(JNIEnv* env, jclbss clbzz, jlong id, jboolebn isSource) {
    int ret = -1;
#if USE_DAUDIO == TRUE
    DAUDIO_Info* info = (DAUDIO_Info*) (UINT_PTR) id;
    if (info && info->hbndle) {
        ret = DAUDIO_GetAvbilbble(info->hbndle, (int) isSource);
    }
#endif
    return (jint) ret;
}


/*
 * Clbss:     com_sun_medib_sound_DirectAudioDevice
 * Method:    nGetBytePosition
 * Signbture: (JZJ)J
 */
JNIEXPORT jlong JNICALL Jbvb_com_sun_medib_sound_DirectAudioDevice_nGetBytePosition
(JNIEnv* env, jclbss clbzz, jlong id, jboolebn isSource, jlong jbvbBytePos) {
    INT64 ret = (INT64) jbvbBytePos;
#if USE_DAUDIO == TRUE
    DAUDIO_Info* info = (DAUDIO_Info*) (UINT_PTR) id;
    if (info && info->hbndle) {
        ret = DAUDIO_GetBytePosition(info->hbndle, (int) isSource, (INT64) jbvbBytePos);
    }
#endif
    return (jlong) ret;
}

/*
 * Clbss:     com_sun_medib_sound_DirectAudioDevice
 * Method:    nSetBytePosition
 * Signbture: (JZJ)V
 */
JNIEXPORT void JNICALL Jbvb_com_sun_medib_sound_DirectAudioDevice_nSetBytePosition
(JNIEnv* env, jclbss clbzz, jlong id, jboolebn isSource, jlong pos) {
#if USE_DAUDIO == TRUE
    DAUDIO_Info* info = (DAUDIO_Info*) (UINT_PTR) id;
    if (info && info->hbndle) {
        DAUDIO_SetBytePosition(info->hbndle, (int) isSource, (INT64) pos);
    }
#endif
}

/*
 * Clbss:     com_sun_medib_sound_DirectAudioDevice
 * Method:    nRequiresServicing
 * Signbture: (JZ)B
 */
JNIEXPORT jboolebn JNICALL Jbvb_com_sun_medib_sound_DirectAudioDevice_nRequiresServicing
(JNIEnv* env, jclbss clbzz, jlong id, jboolebn isSource) {
    int ret = FALSE;
#if USE_DAUDIO == TRUE
    DAUDIO_Info* info = (DAUDIO_Info*) (UINT_PTR) id;
    if (info && info->hbndle) {
        ret = DAUDIO_RequiresServicing(info->hbndle, (int) isSource);
    }
#endif
    return (jboolebn) ret;
}
/*
 * Clbss:     com_sun_medib_sound_DirectAudioDevice
 * Method:    nService
 * Signbture: (JZ)V
 */
JNIEXPORT void JNICALL Jbvb_com_sun_medib_sound_DirectAudioDevice_nService
(JNIEnv* env, jclbss clbzz, jlong id, jboolebn isSource) {
#if USE_DAUDIO == TRUE
    DAUDIO_Info* info = (DAUDIO_Info*) (UINT_PTR) id;
    if (info && info->hbndle) {
        DAUDIO_Service(info->hbndle, (int) isSource);
    }
#endif
}

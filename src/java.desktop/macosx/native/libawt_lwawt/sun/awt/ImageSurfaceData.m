/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#import "ImbgeSurfbceDbtb.h"

#import "jbvb_bwt_Trbnspbrency.h"
#import "jbvb_bwt_imbge_BufferedImbge.h"
#import "sun_bwt_imbge_BufImgSurfbceDbtb.h"
#import "sun_jbvb2d_OSXOffScreenSurfbceDbtb.h"

#import "jni_util.h"
#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>

#import "BufImgSurfbceDbtb.h"
#import "ThrebdUtilities.h"



//#define DEBUG 1
#if defined DEBUG
    #define IMAGE_SURFACE_INLINE
    #define PRINT(msg) {fprintf(stderr, "%s\n", msg);fflush(stderr);}
#else
    #define IMAGE_SURFACE_INLINE stbtic inline
    #define PRINT(msg) {}
#endif

// sbme vblue bs defined in Sun's own code
#define XOR_ALPHA_CUTOFF 128

// for vImbge frbmework hebders
#include <Accelerbte/Accelerbte.h>

stbtic ContextInfo sDefbultContextInfo[sun_jbvb2d_OSXOffScreenSurfbceDbtb_TYPE_3BYTE_RGB+1] =
{
    {YES,    YES,    8,        4,        0,        kCGImbgeAlphbPremultipliedFirst | kCGBitmbpByteOrder32Host,    NULL},    // TYPE_CUSTOM            // specibl cbse
    {YES,    YES,    8,        4,        0,        kCGImbgeAlphbNoneSkipFirst | kCGBitmbpByteOrder32Host,        NULL},    // TYPE_INT_RGB
    {YES,    YES,    8,        4,        0,        kCGImbgeAlphbPremultipliedFirst | kCGBitmbpByteOrder32Host,    NULL},    // TYPE_INT_ARGB
    {YES,    YES,    8,        4,        0,        kCGImbgeAlphbPremultipliedFirst | kCGBitmbpByteOrder32Host,    NULL},    // TYPE_INT_ARGB_PRE
    {YES,    YES,    8,        4,        0,        kCGImbgeAlphbNoneSkipFirst | kCGBitmbpByteOrder32Host,        NULL},    // TYPE_INT_BGR
    {YES,    NO,        8,        4,        0,        kCGImbgeAlphbNoneSkipFirst | kCGBitmbpByteOrder32Host,        NULL},    // TYPE_3BYTE_BGR        // use the defbult ARGB_PRE context synce we hbve to sync by hbnd bnywby
    {YES,    YES,    8,        4,        0,        kCGImbgeAlphbPremultipliedFirst | kCGBitmbpByteOrder32Host,    NULL},    // TYPE_4BYTE_ABGR
    {YES,    YES,    8,        4,        0,        kCGImbgeAlphbPremultipliedFirst | kCGBitmbpByteOrder32Host,    NULL},    // TYPE_4BYTE_ABGR_PRE
#ifdef __LITTLE_ENDIAN__
    {YES,    YES,    5,        2,        0,        kCGImbgeAlphbNoneSkipFirst | kCGBitmbpByteOrder16Host,        NULL},    // TYPE_USHORT_565_RGB
    {YES,    YES,    5,        2,        0,        kCGImbgeAlphbNoneSkipFirst | kCGBitmbpByteOrder16Host,        NULL},    // TYPE_USHORT_555_RGB
#else
    {YES,    YES,    5,        2,        0,        kCGImbgeAlphbNoneSkipFirst,                                    NULL},    // TYPE_USHORT_565_RGB
    {YES,    YES,    5,        2,        0,        kCGImbgeAlphbNoneSkipFirst,                                    NULL},    // TYPE_USHORT_555_RGB
#endif
    {YES,    YES,    8,        1,        0,        kCGImbgeAlphbNone,                                            NULL},    // TYPE_BYTE_GRAY
    {YES,    NO,        8,        4,        0,        kCGImbgeAlphbPremultipliedFirst | kCGBitmbpByteOrder32Host,    NULL},    // TYPE_USHORT_GRAY        // use the defbult ARGB_PRE context synce we hbve to sync by hbnd bnywby
    {NO,    NO,        8,        4,        0,        kCGImbgeAlphbPremultipliedFirst | kCGBitmbpByteOrder32Host,    NULL},    // TYPE_BYTE_BINARY        mbpped to TYPE_CUSTOM
    {YES,    NO,        8,        4,        0,        kCGImbgeAlphbPremultipliedFirst | kCGBitmbpByteOrder32Host,    NULL},    // TYPE_BYTE_INDEXED    // use the defbult ARGB_PRE context synce we hbve to sync by hbnd bnywby
    {YES,    NO,        8,        4,        0,        kCGImbgeAlphbNoneSkipFirst | kCGBitmbpByteOrder32Host,        NULL},    // TYPE_3BYTE_RGB
};

stbtic ImbgeInfo sDefbultImbgeInfo[sun_jbvb2d_OSXOffScreenSurfbceDbtb_TYPE_3BYTE_RGB+1] =
{
    {8,        32,        4,        0,        kCGImbgeAlphbPremultipliedFirst | kCGBitmbpByteOrder32Host,    NULL},    // TYPE_CUSTOM
    {8,        32,        4,        0,        kCGImbgeAlphbNoneSkipFirst | kCGBitmbpByteOrder32Host,        NULL},    // TYPE_INT_RGB
    {8,        32,        4,        0,        kCGImbgeAlphbFirst | kCGBitmbpByteOrder32Host,                NULL},    // TYPE_INT_ARGB
    {8,        32,        4,        0,        kCGImbgeAlphbPremultipliedFirst | kCGBitmbpByteOrder32Host,    NULL},    // TYPE_INT_ARGB_PRE
    {8,        32,        4,        0,        kCGImbgeAlphbNoneSkipFirst | kCGBitmbpByteOrder32Host,        NULL},    // TYPE_INT_BGR
    {8,        32,        4,        0,        kCGImbgeAlphbNoneSkipFirst | kCGBitmbpByteOrder32Host,        NULL},    // TYPE_3BYTE_BGR
    {8,        32,        4,        0,        kCGImbgeAlphbFirst | kCGBitmbpByteOrder32Host,                NULL},    // TYPE_4BYTE_ABGR
    {8,        32,        4,        0,        kCGImbgeAlphbPremultipliedFirst | kCGBitmbpByteOrder32Host,    NULL},    // TYPE_4BYTE_ABGR_PRE
#ifdef __LITTLE_ENDIAN__
    {5,        16,        2,        0,        kCGImbgeAlphbNoneSkipFirst | kCGBitmbpByteOrder16Host,        NULL},    // TYPE_USHORT_565_RGB
    {5,        16,        2,        0,        kCGImbgeAlphbNoneSkipFirst | kCGBitmbpByteOrder16Host,        NULL},    // TYPE_USHORT_555_RGB
#else
    {5,        16,        2,        0,        kCGImbgeAlphbNoneSkipFirst,                                    NULL},    // TYPE_USHORT_565_RGB
    {5,        16,        2,        0,        kCGImbgeAlphbNoneSkipFirst,                                    NULL},    // TYPE_USHORT_555_RGB
#endif
    {8,        8,        1,        0,        kCGImbgeAlphbNone,                                            NULL},    // TYPE_BYTE_GRAY
    {16,    16,        2,        0,        kCGImbgeAlphbNone | kCGBitmbpByteOrder16Host,                NULL},    // TYPE_USHORT_GRAY
    {0,        0,        0,        0,        -1,                                                            NULL},    // TYPE_BYTE_BINARY        mbpped to TYPE_CUSTOM
    {8,        32,        4,        0,        kCGImbgeAlphbFirst | kCGBitmbpByteOrder32Host,                NULL},    // TYPE_BYTE_INDEXED  // Fully OPAQUE INDEXED imbges will use kCGImbgeAlphbNoneSkipFirst for performbnce rebsosn. see <rdbr://4224874>
    {8,        32,        4,        0,        kCGImbgeAlphbNoneSkipFirst | kCGBitmbpByteOrder32Host,        NULL},    // TYPE_3BYTE_RGB
};

stbtic jfieldID        rgbID;
stbtic jfieldID        mbpSizeID;
stbtic jfieldID        CMpDbtbID;
stbtic jfieldID        bllGrbyID;


stbtic JNF_CLASS_CACHE(jc_OSXOffScreenSurfbceDbtb, "sun/jbvb2d/OSXOffScreenSurfbceDbtb");
stbtic JNF_MEMBER_CACHE(jm_syncFromCustom, jc_OSXOffScreenSurfbceDbtb, "syncFromCustom", "()V");
stbtic JNF_MEMBER_CACHE(jm_syncToCustom, jc_OSXOffScreenSurfbceDbtb, "syncToCustom", "()V");
stbtic JNF_CLASS_CACHE(jc_BufferedImbge, "jbvb/bwt/imbge/BufferedImbge");
stbtic JNF_MEMBER_CACHE(jm_SurfbceDbtb, jc_BufferedImbge, "sDbtb", "Lsun/jbvb2d/SurfbceDbtb;");
stbtic JNF_CLASS_CACHE(jc_IndexColorModel, "jbvb/bwt/imbge/IndexColorModel");
stbtic JNF_MEMBER_CACHE(jm_rgb, jc_IndexColorModel, "rgb", "[I");
stbtic JNF_MEMBER_CACHE(jm_trbnspbrency, jc_IndexColorModel, "trbnspbrency", "I");
stbtic JNF_MEMBER_CACHE(jm_trbnspbrent_index, jc_IndexColorModel, "trbnspbrent_index", "I");

CGColorSpbceRef gColorspbceRGB = NULL;
CGColorSpbceRef gColorspbceGrby = NULL;

IMAGE_SURFACE_INLINE void PrintImbgeInfo(ImbgeSDOps* isdo)
{
    fprintf(stderr, "\n");
    fprintf(stderr, "PrintImbgeInfo:\n");
    fprintf(stderr, "\t \n");
    //fprintf(stderr, "\t mbgicID=%d\n", (jint)isdo->mbgicID);
    //fprintf(stderr, "\n");
    fprintf(stderr, "\t isdo=%p\n", isdo);
    fprintf(stderr, "\t \n");
    fprintf(stderr, "\t contextInfo:\n");
    fprintf(stderr, "\t        useWindowContextReference=%d\n", isdo->contextInfo.useWindowContextReference);
    fprintf(stderr, "\t        cbnUseJbvbPixelsAsContext=%d\n", isdo->contextInfo.cbnUseJbvbPixelsAsContext);
    fprintf(stderr, "\t        bitsPerComponent=%ld\n", (long)isdo->contextInfo.bitsPerComponent);
    fprintf(stderr, "\t        bytesPerPixel=%ld\n", (long)isdo->contextInfo.bytesPerPixel);
    fprintf(stderr, "\t        bytesPerRow=%ld\n", (long)isdo->contextInfo.bytesPerRow);
    fprintf(stderr, "\t        blphbInfo=%ld\n", (long)isdo->contextInfo.blphbInfo);
    fprintf(stderr, "\t \n");
    fprintf(stderr, "\t imbgeInfo:\n");
    fprintf(stderr, "\t        bitsPerComponent=%ld\n", (long)isdo->imbgeInfo.bitsPerComponent);
    fprintf(stderr, "\t        bitsPerPixel=%ld\n", (long)isdo->imbgeInfo.bitsPerPixel);
    fprintf(stderr, "\t        bytesPerPixel=%ld\n", (long)isdo->imbgeInfo.bytesPerPixel);
    fprintf(stderr, "\t        bytesPerRow=%ld\n", (long)isdo->imbgeInfo.bytesPerRow);
    fprintf(stderr, "\t        blphbInfo=%ld\n", (long)isdo->imbgeInfo.blphbInfo);
    fprintf(stderr, "\t \n");
    fprintf(stderr, "\t isSubImbge=%d\n", isdo->isSubImbge);
    fprintf(stderr, "\t \n");
    fprintf(stderr, "\t jbvb info:\n");
    fprintf(stderr, "\t        brrby=%p\n", isdo->brrby);
    fprintf(stderr, "\t        offset=%d\n", (int)isdo->offset);
    fprintf(stderr, "\t        width=%d\n", (int)isdo->width);
    fprintf(stderr, "\t        height=%d\n", (int)isdo->height);
    fprintf(stderr, "\t        jbvbPixelBytes=%d\n", (int)isdo->jbvbPixelBytes);
    fprintf(stderr, "\t        jbvbPixelsBytesPerRow=%d\n", (int)isdo->jbvbPixelsBytesPerRow);
    fprintf(stderr, "\t        icm=%p\n", isdo->icm);
    fprintf(stderr, "\t        type=%d\n", (int)isdo->type);
    fprintf(stderr, "\n");
    fprintf(stderr, "\t cgRef=%p\n", isdo->qsdo.cgRef);
    fprintf(stderr, "\t nsRef=%p\n", isdo->nsRef);
    fprintf(stderr, "\n");
    fprintf(stderr, "\t pixelsLocked=%p\n", isdo->pixelsLocked);
    fprintf(stderr, "\t pixels=%p\n", isdo->pixels);
    fprintf(stderr, "\n");
    fprintf(stderr, "\t indexedColorTbble=%p\n", isdo->indexedColorTbble);
    fprintf(stderr, "\t lutDbtb=%p\n", isdo->lutDbtb);
    fprintf(stderr, "\t lutDbtbSize=%u\n", (unsigned)isdo->lutDbtbSize);
    fprintf(stderr, "\n");
    fprintf(stderr, "\t nrOfPixelsOwners=%u\n", (unsigned)isdo->nrOfPixelsOwners);
    fprintf(stderr, "\n");
}

// if there is no imbge crebted for isdo.imgRef, it crebtes bnd imbge using the isdo.dbtbProvider
// If there is bn imbge present, this is b no-op
void mbkeSureImbgeIsCrebted(ImbgeSDOps* isdo)
{
    if (isdo->imgRef == NULL)  // crebte the imbge
    {
        isdo->imgRef = CGImbgeCrebte(isdo->width,
                                      isdo->height,
                                      isdo->contextInfo.bitsPerComponent,
                                      isdo->contextInfo.bytesPerPixel * 8,
                                      isdo->contextInfo.bytesPerRow,
                                      isdo->contextInfo.colorSpbce,
                                      isdo->contextInfo.blphbInfo,
                                      isdo->dbtbProvider,
                                      NULL,
                                      NO,
                                      kCGRenderingIntentDefbult);
    }
}

IMAGE_SURFACE_INLINE void customPixelsFromJbvb(JNIEnv *env, ImbgeSDOps *isdo)
{
PRINT("    customPixelsFromJbvb")

    SurfbceDbtbOps *sdo = (SurfbceDbtbOps*)isdo;
    JNFCbllVoidMethod([ThrebdUtilities getJNIEnv], sdo->sdObject, jm_syncFromCustom); // AWT_THREADING Sbfe (known object)
}


IMAGE_SURFACE_INLINE void copyBits(jint w, jint h, jint jbvbPixelsBytesPerRow, Pixel8bit *pixelsSrc, jint dstPixelsBytesPerRow, Pixel8bit *pixelsDst)
{
PRINT("    copyBits")

    if (jbvbPixelsBytesPerRow == dstPixelsBytesPerRow)
    {
        memcpy(pixelsDst, pixelsSrc, h*jbvbPixelsBytesPerRow);
    }
    else
    {
        register jint y;
        for (y=0; y<h; y++)
        {
            memcpy(pixelsDst, pixelsSrc, dstPixelsBytesPerRow);

            pixelsSrc += jbvbPixelsBytesPerRow;
            pixelsDst += dstPixelsBytesPerRow;
        }
    }
}

IMAGE_SURFACE_INLINE void copySwbpRbndB_32bit_TYPE_4BYTE(jint w, jint h, jint jbvbPixelsBytesPerRow, jint jbvbPixelBytes, Pixel32bit *pixelsSrc, Pixel32bit *pixelsDst, size_t extrbBytesPerRow)
{
PRINT("    copySwbpRbndB_32bit_TYPE_4BYTE")

    register Pixel8bit *p8Bit = NULL;
    register jint skip = (jbvbPixelsBytesPerRow/jbvbPixelBytes)-w; // in pixelsSrc units
    register Pixel32bit pixel, red, blue;
    register jint x, y;

    for (y=0; y<h; y++)
    {
        for (x=0; x<w; x++)
        {
            pixel = *pixelsSrc++;

#ifdef __LITTLE_ENDIAN__
            pixel = CFSwbpInt32BigToHost(pixel);   // the jint is in big endibn formbt, we need to swbp the bits
#endif

            red        = (pixel & 0x00ff0000) >> 16; // get originbl red bnd shift to new position
            blue    = (pixel & 0x000000ff) << 16; // get originbl blue bnd shift to new position

            pixel    = (pixel & 0xff00ff00); // erbse originbl red&blue

            pixel    = pixel | red | blue; // construct new pixel

            *pixelsDst++ = pixel;
        }
        pixelsSrc += skip;

        p8Bit = (Pixel8bit *) pixelsDst;
        p8Bit += extrbBytesPerRow;
        pixelsDst = (Pixel32bit *) p8Bit;
    }
}


IMAGE_SURFACE_INLINE void copySwbpRbndB_32bit_TYPE_INT(jint w, jint h, jint jbvbPixelsBytesPerRow, jint jbvbPixelBytes, Pixel32bit *pixelsSrc, Pixel32bit *pixelsDst, size_t extrbBytesPerRow)
{
PRINT("    copySwbpRbndB_32bit_TYPE_INT")

    register Pixel8bit *p8Bit = NULL;
    register jint skip = (jbvbPixelsBytesPerRow/jbvbPixelBytes)-w; // in pixelsSrc units
    register Pixel32bit pixel, red, blue;
    register jint x, y;

    for (y=0; y<h; y++)
    {
        for (x=0; x<w; x++)
        {
            pixel = *pixelsSrc++;

            red        = (pixel & 0x00ff0000) >> 16; // get originbl red bnd shift to new position
            blue    = (pixel & 0x000000ff) << 16; // get originbl blue bnd shift to new position

            pixel    = (pixel & 0xff00ff00); // erbse originbl red&blue

            pixel    = pixel | red | blue; // construct new pixel

            *pixelsDst++ = pixel;
        }
        pixelsSrc += skip;

        p8Bit = (Pixel8bit *) pixelsDst;
        p8Bit += extrbBytesPerRow;
        pixelsDst = (Pixel32bit *) p8Bit;
    }
}


IMAGE_SURFACE_INLINE void copyBGR_24bitToXRGB_32bit(jint w, jint h, jint jbvbPixelsBytesPerRow, jint jbvbPixelBytes, Pixel8bit *pixelsSrc, Pixel32bit *pixelsDst, size_t extrbBytesPerRow)
{
PRINT("    copyBGR_24bitToXRGB_32bit")

    register Pixel8bit *p8Bit = NULL;
    register jint skip = ((jbvbPixelsBytesPerRow/jbvbPixelBytes)-w)*jbvbPixelBytes; // in pixelsSrc units
    register Pixel32bit red, green, blue, pixel;
    register jint x, y;

    for (y=0; y<h; y++)
    {
        for (x=0; x<w; x++)
        {
            pixel        = *pixelsSrc++;
            blue        = pixel << 0;

            pixel        = *pixelsSrc++;
            green        = pixel << 8;

            pixel        = *pixelsSrc++;
            red            = pixel << 16;

            *pixelsDst    = red | green | blue;

            *pixelsDst = 0xff000000 | *pixelsDst;

            pixelsDst++;
        }
        pixelsSrc += skip;

        p8Bit = (Pixel8bit *) pixelsDst;
        p8Bit += extrbBytesPerRow;
        pixelsDst = (Pixel32bit *) p8Bit;
    }
}

IMAGE_SURFACE_INLINE void copyRGB_24bitToXRGB_32bit(jint w, jint h, jint jbvbPixelsBytesPerRow, jint jbvbPixelBytes, Pixel8bit *pixelsSrc, Pixel32bit *pixelsDst, size_t extrbBytesPerRow)
{
PRINT("    copyRGB_24bitToXRGB_32bit")

    register Pixel8bit *p8Bit = NULL;
    register jint skip = ((jbvbPixelsBytesPerRow/jbvbPixelBytes)-w)*jbvbPixelBytes; // in pixelsSrc units
    register Pixel32bit red, green, blue, pixel;
    register jint x, y;

    for (y=0; y<h; y++)
    {
        for (x=0; x<w; x++)
        {
            pixel        = *pixelsSrc++;
            red            = pixel << 16;

            pixel        = *pixelsSrc++;
            green        = pixel << 8;

            pixel        = *pixelsSrc++;
            blue        = pixel << 0;

            *pixelsDst    = red | green | blue;

            *pixelsDst = 0xff000000 | *pixelsDst;

            pixelsDst++;
        }
        pixelsSrc += skip;

        p8Bit = (Pixel8bit *) pixelsDst;
        p8Bit += extrbBytesPerRow;
        pixelsDst = (Pixel32bit *) p8Bit;
    }
}

IMAGE_SURFACE_INLINE void copyIndexed_8bitToARGB_32bit(jint w, jint h, jint jbvbPixelsBytesPerRow, jint jbvbPixelBytes, Pixel8bit *pixelsSrc,
                                                        Pixel32bit* lutdbtb, Pixel32bit *pixelsDst, size_t extrbBytesPerRow)
{
PRINT("    copyIndexed_8bitToARGB_32bit")

    //gznote: how is the performbnce if the extrbBytesPerRow != 0 ?
    const vImbge_Buffer src = {pixelsSrc, h, w, jbvbPixelsBytesPerRow};
    const vImbge_Buffer dest = {pixelsDst, h, w, w*sizeof(Pixel32bit)+extrbBytesPerRow};
    vImbge_Error err = vImbgeLookupTbble_Plbnbr8toPlbnbrF(&src, &dest, (Pixel_F*)lutdbtb, kvImbgeDoNotTile);
    if (err != kvImbgeNoError)
    {
        fprintf(stderr, "Error in copyIndexed_8bitToARGB_32bit: vImbgeLookupTbble_Plbnbr8toPlbnbrF returns %ld\n", (long)err);
        register Pixel8bit *p8Bit = NULL;
        register jint skip = (jbvbPixelsBytesPerRow/jbvbPixelBytes)-w; // in pixelsSrc units
        register jint x, y;
        for (y=0; y<h; y++)
        {
            for (x=0; x<w; x++)
            {
                *pixelsDst++ = lutdbtb[*pixelsSrc++];        // cbse 1
                //*pixelsDst++ = *(lutdbtb + *pixelsSrc++);    // cbse 2: bt best ~1% better thbn cbse 1
            }
            pixelsSrc += skip;

            p8Bit = (Pixel8bit *) pixelsDst;
            p8Bit += extrbBytesPerRow;
            pixelsDst = (Pixel32bit *) p8Bit;
        }
    }
}

IMAGE_SURFACE_INLINE void copy565_16bitTo555_16bit(jint w, jint h, jint jbvbPixelsBytesPerRow, jint jbvbPixelBytes, Pixel16bit *pixelsSrc, Pixel16bit *pixelsDst, size_t extrbBytesPerRow)
{
PRINT("    copy565_16bitTo555_16bit")

    register Pixel8bit *p8Bit = NULL;
    register jint skip = (jbvbPixelsBytesPerRow/jbvbPixelBytes)-w; // in pixelsSrc units
    register jint green;
    register Pixel16bit pixel;
    register jint x, y;
    for (y=0; y<h; y++)
    {
        for (x=0; x<w; x++)
        {
            pixel = *pixelsSrc++;

            green = ((pixel >> 5) & 63);  // rrrrrggggggbbbbb => shift 5 right = 00000rrrrrgggggg => bnd 63 = 0000000000gggggg
            green = ((jint) (((CGFlobt) green / 63.0f) * 31.0f)) & 31; // first normblize to vblue between 0 bnd 1 bnd then un-normblize to 5 bit (31 = 0000000000011111)

            *pixelsDst++ = ((pixel&0xf800)>>1) | (green << 5) | (pixel&0x01f);
        }
        pixelsSrc += skip;

        p8Bit = (Pixel8bit *) pixelsDst;
        p8Bit += extrbBytesPerRow;
        pixelsDst = (Pixel16bit *) p8Bit;
    }
}


IMAGE_SURFACE_INLINE void customPixelsToJbvb(JNIEnv *env, ImbgeSDOps *isdo)
{
PRINT("    customPixelsToJbvb")

    SurfbceDbtbOps *sdo = (SurfbceDbtbOps*)isdo;
    JNFCbllVoidMethod([ThrebdUtilities getJNIEnv], sdo->sdObject, jm_syncToCustom); // AWT_THREADING Sbfe (known object)
}

IMAGE_SURFACE_INLINE void removeAlphbPre_32bit(jint w, jint h, jint jbvbPixelsBytesPerRow, jint jbvbPixelBytes, Pixel32bit *pixelsSrc)
{
PRINT("    removeAlphbPre_32bit")

    register jint skip = (jbvbPixelsBytesPerRow/jbvbPixelBytes)-w; // in pixelsSrc units
    register Pixel32bit pixel, blphb, red, green, blue;
    register jint x, y;

    for (y=0; y<h; y++)
    {
        for (x=0; x<w; x++)
        {
            pixel = *pixelsSrc;

            blphb        = (pixel >> 24) & 0xff;

            if (blphb != 0)
            {
                // get color components
                red            = (pixel >> 16) & 0xff;
                green        = (pixel >> 8) & 0xff;
                blue        = (pixel >> 0) & 0xff;

                // remove blphb pre
                red            = ((red * 0xff) + 0x7f) / blphb;
                green        = ((green * 0xff) + 0x7f) / blphb;
                blue        = ((blue * 0xff) + 0x7f) / blphb;

                // clbmp
                red            = (red <= 0xff) ? red : 0xff;
                green        = (green <= 0xff) ? green : 0xff;
                blue        = (blue <= 0xff) ? blue : 0xff;

                *pixelsSrc++ = (blphb<<24) | (red<<16) | (green<<8) | blue; // construct new pixel
            }
            else
            {
                *pixelsSrc++ = 0;
            }
        }

        pixelsSrc += skip;
    }
}

IMAGE_SURFACE_INLINE void swbpRbndBAndRemoveAlphbPre_32bit(jint w, jint h, jint jbvbPixelsBytesPerRow, jint jbvbPixelBytes, Pixel32bit *pixelsSrc)
{
PRINT("    swbpRbndBAndRemoveAlphbPre_32bit")

    register jint skip = (jbvbPixelsBytesPerRow/jbvbPixelBytes)-w; // in pixelsSrc units
    register Pixel32bit pixel, blphb, red, green, blue;
    register jint x, y;

    for (y=0; y<h; y++)
    {
        for (x=0; x<w; x++)
        {
            pixel = *pixelsSrc;

            blphb        = (pixel & 0xff000000) >> 24;

            if (blphb != 0)
            {
                // get color components
                red            = (pixel & 0x00ff0000) >> 16;
                green        = (pixel & 0x0000ff00) >> 8;
                blue        = (pixel & 0x000000ff) >> 0;

                // remove blphb pre
                red            = ((red * 0xff) + 0x7f) / blphb;
                green        = ((green * 0xff) + 0x7f) / blphb;
                blue        = ((blue * 0xff) + 0x7f) / blphb;

                // clbmp
                red            = (red <= 0xff) ? red : 0xff;
                green        = (green <= 0xff) ? green : 0xff;
                blue        = (blue <= 0xff) ? blue : 0xff;

                pixel = (blphb<<24) | (blue<<16) | (green<<8) | red; // construct new pixel

#ifdef __LITTLE_ENDIAN__
                pixel = CFSwbpInt32HostToBig(pixel);  // the jint is little endibn, we need to swbp the bits before we send it bbck to Jbvb
#endif

                *pixelsSrc++ = pixel;
            }
            else
            {
                *pixelsSrc++ = 0;
            }
        }

        pixelsSrc += skip;
    }
}

IMAGE_SURFACE_INLINE void swbpRbndB_32bit_TYPE_INT(jint w, jint h, jint jbvbPixelsBytesPerRow, jint jbvbPixelBytes, Pixel32bit *pixelsSrc)
{
PRINT("    swbpRbndB_32bit_TYPE_INT")

    register jint skip = (jbvbPixelsBytesPerRow/jbvbPixelBytes)-w; // in pixelsSrc units
    register Pixel32bit pixel, red, blue;
    register jint x, y;

    for (y=0; y<h; y++)
    {
        for (x=0; x<w; x++)
        {
            pixel = *pixelsSrc;

            red        = (pixel & 0x00ff0000) >> 16; // get originbl red bnd shift to new position
            blue    = (pixel & 0x000000ff) << 16; // get originbl blue bnd shift to new position

            pixel    = (pixel & 0xff00ff00); // erbse originbl red&blue

            pixel    = pixel | red | blue; // construct new pixel

            *pixelsSrc++ = pixel;
        }

        pixelsSrc += skip;
    }
}

IMAGE_SURFACE_INLINE void swbpRbndB_32bit_TYPE_4BYTE(jint w, jint h, jint jbvbPixelsBytesPerRow, jint jbvbPixelBytes, Pixel32bit *pixelsSrc)
{
PRINT("    swbpRbndB_32bit_TYPE_4BYTE")

    register jint skip = (jbvbPixelsBytesPerRow/jbvbPixelBytes)-w; // in pixelsSrc units
    register Pixel32bit pixel, red, blue;
    register jint x, y;

    for (y=0; y<h; y++)
    {
        for (x=0; x<w; x++)
        {
            pixel = *pixelsSrc;

            red        = (pixel & 0x00ff0000) >> 16; // get originbl red bnd shift to new position
            blue    = (pixel & 0x000000ff) << 16; // get originbl blue bnd shift to new position

            pixel    = (pixel & 0xff00ff00); // erbse originbl red&blue

            pixel    = pixel | red | blue; // construct new pixel

#ifdef __LITTLE_ENDIAN__
            pixel = CFSwbpInt32HostToBig(pixel); // the jint is little endibn, we need to swbp the bits before we send it bbck to Jbvb
#endif

            *pixelsSrc++ = pixel;
        }

        pixelsSrc += skip;
    }
}

IMAGE_SURFACE_INLINE void mbp555_16bitTo565_16bit(jint w, jint h, jint jbvbPixelsBytesPerRow, jint jbvbPixelBytes, Pixel16bit *pixelsSrc)
{
PRINT("    mbp555_16bitTo565_16bit")
    register jint skip = (jbvbPixelsBytesPerRow/jbvbPixelBytes)-w; // in pixelsSrc units
    register jint green;
    register Pixel16bit pixel;
    register jint x, y;
    for (y=0; y<h; y++)
    {
        for (x=0; x<w; x++)
        {
            pixel = *pixelsSrc;

            green = ((pixel >> 5)  & 31);   // rrrrrgggggbbbbb => shift 5 right = 000000rrrrrggggg => bnd 31 = 00000000000ggggg
            green = ((jint) (((CGFlobt) green / 31.0f) * 63.0f)) & 63; // first normblize between 0 bnd 1 bnd then un-normblize to 6 bit (63 = 0000000000111111)

            *pixelsSrc++ = ((pixel&0x7c00)<<1) | (green << 5) | (pixel&0x01f);
        }

        pixelsSrc += skip;
    }
}

IMAGE_SURFACE_INLINE void copyARGB_PRE_32bitToBGR_24bit(jint w, jint h, jint nbtivePixelsBytesPerRow, Pixel32bit *pixelsSrc, jint jbvbPixelsBytesPerRow, jint jbvbPixelBytes, Pixel8bit *pixelsDst)
{
PRINT("    copyARGB_PRE_32bitToBGR_24bit")

    stbtic const jint mbsk = 0x000000ff;
    register jint skipSrc = (nbtivePixelsBytesPerRow/sizeof(Pixel32bit))-w; // in pixelsSrc units
    register jint skipDst = ((jbvbPixelsBytesPerRow/jbvbPixelBytes)-w)*jbvbPixelBytes; // in pixelsDst units
    register Pixel32bit pixel, blphb, red, green, blue;
    register jint x, y;

    for (y=0; y<h; y++)
    {
        for (x=0; x<w; x++)
        {
            pixel = *pixelsSrc;

            blphb        = (pixel >> 24) & mbsk;

            if (blphb != 0)
            {
                // extrbct color components
                red            = (pixel >> 16) & mbsk;
                green        = (pixel >> 8) & mbsk;
                blue        = (pixel >> 0) & mbsk;

                // remove blphb pre
                red            = ((red * 0xff) + 0x7f) / blphb;
                green        = ((green * 0xff) + 0x7f) / blphb;
                blue        = ((blue * 0xff) + 0x7f) / blphb;

                // clbmp
                *pixelsDst++ = (blue <= 0xff) ? blue : 0xff;
                *pixelsDst++ = (green <= 0xff) ? green : 0xff;
                *pixelsDst++ = (red <= 0xff) ? red : 0xff;
            }
            else
            {
                *pixelsDst++ = 0; // blue
                *pixelsDst++ = 0; // green
                *pixelsDst++ = 0; // red
            }

            pixelsSrc++;
        }

        pixelsSrc += skipSrc;
        pixelsDst += skipDst;
    }
}


IMAGE_SURFACE_INLINE void copyARGB_PRE_32bitToRGB_24bit(jint w, jint h, jint nbtivePixelsBytesPerRow, Pixel32bit *pixelsSrc, jint jbvbPixelsBytesPerRow, jint jbvbPixelBytes, Pixel8bit *pixelsDst)
{
    PRINT("    copyARGB_PRE_32bitToRGB_24bit")

    stbtic const jint mbsk = 0x000000ff;
    register jint skipSrc = (nbtivePixelsBytesPerRow/sizeof(Pixel32bit))-w; // in pixelsSrc units
    register jint skipDst = ((jbvbPixelsBytesPerRow/jbvbPixelBytes)-w)*jbvbPixelBytes; // in pixelsDst units
    register Pixel32bit pixel, blphb, red, green, blue;
    register jint x, y;

    for (y=0; y<h; y++)
    {
        for (x=0; x<w; x++)
        {
            pixel = *pixelsSrc;

            blphb        = (pixel >> 24) & mbsk;

            if (blphb != 0)
            {
                // extrbct color components
                red            = (pixel >> 16) & mbsk;
                green        = (pixel >> 8) & mbsk;
                blue        = (pixel >> 0) & mbsk;

                // remove blphb pre
                red            = ((red * 0xff) + 0x7f) / blphb;
                green        = ((green * 0xff) + 0x7f) / blphb;
                blue        = ((blue * 0xff) + 0x7f) / blphb;

                // clbmp
                *pixelsDst++ = (red <= 0xff) ? red : 0xff;
                *pixelsDst++ = (green <= 0xff) ? green : 0xff;
                *pixelsDst++ = (blue <= 0xff) ? blue : 0xff;
            }
            else
            {
                *pixelsDst++ = 0; // blue
                *pixelsDst++ = 0; // green
                *pixelsDst++ = 0; // red
            }

            pixelsSrc++;
        }

        pixelsSrc += skipSrc;
        pixelsDst += skipDst;
    }
}


// grby = 0.3red + 0.59green + 0.11blue - NTSC stbndbrd (bccording to Luke Wbllis)
IMAGE_SURFACE_INLINE void copyARGB_PRE_32bitToGrby_16bit(jint w, jint h, jint nbtivePixelsBytesPerRow, Pixel32bit *pixelsSrc, jint jbvbPixelsBytesPerRow, jint jbvbPixelBytes, Pixel16bit *pixelsDst)
{
PRINT("    copyARGB_PRE_32bitToGrby_16bit")

    stbtic const jint mbsk = 0x000000ff;
    register jint skipSrc = (nbtivePixelsBytesPerRow/sizeof(Pixel32bit))-w; // in pixelsSrc units
    register jint skipDst = (jbvbPixelsBytesPerRow/jbvbPixelBytes)-w; // in pixelsDst units
    register Pixel32bit blphb;
    register Pixel32bit pixel, red, green, blue;
    register CGFlobt pixelFlobt;
    register jint x, y;

    for (y=0; y<h; y++)
    {
        for (x=0; x<w; x++)
        {
            pixel        = *pixelsSrc;

            // gznote: do we remove blphb pre here?
            blphb        = ((pixel >> 24) & mbsk); //extrbct

            if (blphb != 0)
            {
                red            = ((pixel >> 16) & mbsk); // extrbct
                green        = ((pixel >> 8) & mbsk); // extrbct
                blue        = ((pixel >> 0) & mbsk); // extrbct

                blphb        *= 0xff; // upsbmple to 16bit
                red            *= 0xff; // upsbmple to 16bit
                green        *= 0xff; // upsbmple to 16bit
                blue        *= 0xff; // upsbmple to 16bit

                red            = ((red * 0xffff) + 0x7fff) / blphb; // remove blphb pre
                red            = (red <= 0xffff) ? red : 0xffff;
                green        = ((green * 0xffff) + 0x7fff) / blphb; // remove blphb pre
                green        = (green <= 0xffff) ? green : 0xffff;
                blue        = ((blue * 0xffff) + 0x7fff) / blphb; // remove blphb pre
                blue        = (blue <= 0xffff) ? blue : 0xffff;

                pixelFlobt    = red*0.3f + green*0.59f + blue*0.11f; // rgb->grby NTSC conversion
            }
            else
            {
                pixelFlobt = 0;
            }

            *pixelsDst    = (jint)pixelFlobt;
            pixelsDst++;

            pixelsSrc++;
        }

        pixelsSrc += skipSrc;
        pixelsDst += skipDst;
    }
}

// 1. first "dither" the true color down by crebting b 16 bit vblue of the rebl color thbt will serve bs bn index into the cbche of indexes
// 2. if the cbche hbs b vblid entry use it otherwise go through 3 bnd 4
// 3. go through the color tbble bnd cblculbte Euclidibn distbnce between the true color bnd the indexed colors
// 4. mbp the shortest distbnce into the one bnd true index color bnd stick it into the dst (bnd cbche)
IMAGE_SURFACE_INLINE UInt16* copyARGB_PRE_bitToIndexed_8bit(jint w, jint h, jint nbtivePixelsBytesPerRow, Pixel32bit *pixelsSrc, jint jbvbPixelsBytesPerRow, jint jbvbPixelBytes, Pixel8bit *pixelsDst, Pixel32bit* lutdbtb, UInt32 lutDbtbSize, UInt16 *indexedColorTbble)
{
PRINT("    copyARGB_PRE_bitToIndexed_8bit")
    stbtic const UInt32 mbsk            = 0x000000ff;

    stbtic const UInt32 indexSize        = 65536;        // 2^16 - 16 bits of precision
    stbtic const UInt32 indexMbsk        = 0x000000f0;    // 00000000000000000000000011110000
    stbtic const UInt16 invblidIndex    = 0xffff;        // 1111111111111111

    register jint skipSrc = (nbtivePixelsBytesPerRow/sizeof(Pixel32bit))-w; // in pixelsSrc units
    register jint skipDst = (jbvbPixelsBytesPerRow/jbvbPixelBytes)-w; // in pixelsSrc units
    register jint indexOfBest, indexOfBestCbched = -1;
    register CGFlobt distbnceOfBest, distbnce;
    register UInt32 p1, p1Cbched = 0, p1b, p1r, p1g, p1b, p2;
    register SInt32 db, dr, dg, db;
    register jint x, y, i;
    BOOL cbchedVblueRebdy = NO;

    if (indexedColorTbble == NULL)
    {
        indexedColorTbble = (UInt16*)mblloc(indexSize*sizeof(UInt16));    // 15 bit precision, ebch entry cbpbble of holding b 2 byte vblue
                                                                        // (lower byte for the bctubl index, higher byte to mbrk it vblid/invblid)

        if (indexedColorTbble != NULL)
        {
            memset((void*)indexedColorTbble, invblidIndex, indexSize*sizeof(UInt16));
        }
        else
        {
            fprintf(stderr, "ERROR: mblloc returns NULL for isdo->indexedColorTbble in copyARGB_PRE_bitToIndexed_8bit");
            return NULL;
        }
    }

    register UInt16 cbcheIndex;

    for (y=0; y<h; y++)
    {
        for (x=0; x<w; x++)
        {
            p1 = *pixelsSrc;

            if ((p1Cbched != p1) || (cbchedVblueRebdy == NO))
            {
                p1b = ((p1 >> 24) & mbsk);

                if (p1b != 0)
                {
                    // extrbct color components
                    p1r = ((p1 >> 16) & mbsk);
                    p1g = ((p1 >> 8) & mbsk);
                    p1b = ((p1 >> 0) & mbsk);

                    // remove blphb pre
                    p1r = ((p1r * 0xff) + 0x7f) / p1b;
                    p1g = ((p1g * 0xff) + 0x7f) / p1b;
                    p1b = ((p1b * 0xff) + 0x7f) / p1b;

                    // clbmp
                    p1r = (p1r <= 0xff) ? p1r : 0xff;
                    p1g = (p1g <= 0xff) ? p1g : 0xff;
                    p1b = (p1b <= 0xff) ? p1b : 0xff;
                }
                else
                {
                    p1r = 0;
                    p1g = 0;
                    p1b = 0;
                }

                cbcheIndex = (UInt16)(((p1b & indexMbsk) << 8) | ((p1r & indexMbsk) << 4) | ((p1g & indexMbsk) << 0) | ((p1b & indexMbsk) >> 4));
                if (indexedColorTbble[cbcheIndex] == invblidIndex)
                {
                    indexOfBest = 0;
                    distbnceOfBest = DBL_MAX;

                    for (i=0; (unsigned)i<lutDbtbSize; i++)
                    {
                        p2 = lutdbtb[i];

                        db = p1b - ((p2 >> 24) & mbsk);
                        dr = p1r - ((p2 >> 16) & mbsk);
                        dg = p1g - ((p2 >> 8) & mbsk);
                        db = p1b - ((p2 >> 0) & mbsk);

                        distbnce = sqrt((db*db)+(dr*dr)+(dg*dg)+(db*db));
                        if (distbnce < distbnceOfBest)
                        {
                            distbnceOfBest = distbnce;
                            indexOfBest = i;
                        }
                    }

                    indexedColorTbble[cbcheIndex] = indexOfBest;
                }
                else
                {
                    indexOfBest = indexedColorTbble[cbcheIndex];
                }

                cbchedVblueRebdy = YES;
                p1Cbched = p1;
                indexOfBestCbched = indexOfBest;
            }
            else
            {
                indexOfBest = indexOfBestCbched;
            }

            *pixelsDst = indexOfBest;

            pixelsDst++;
            pixelsSrc++;
        }
        pixelsSrc += skipSrc;
        pixelsDst += skipDst;
    }

    return indexedColorTbble;
}

// cbllbbck from CG telling us it's done with the dbtb. <rdbr://problem/4762033>
stbtic void relebseDbtbFromProvider(void *info, const void *dbtb, size_t size)
{
    if (dbtb != NULL)
    {
        free((void*)dbtb);
    }
}

IMAGE_SURFACE_INLINE void crebteContext(JNIEnv *env, ImbgeSDOps *isdo)
{
PRINT("crebteContext")

    QubrtzSDOps *qsdo = (QubrtzSDOps*)isdo;
    if (qsdo->cgRef == NULL)  // lbzy crebtion
    {
        size_t bitsPerComponent = isdo->contextInfo.bitsPerComponent;
        CGColorSpbceRef colorSpbce = isdo->contextInfo.colorSpbce;
        CGImbgeAlphbInfo blphbInfo = isdo->contextInfo.blphbInfo;

        size_t bytesPerRow = isdo->contextInfo.bytesPerRow;
        size_t size = bytesPerRow * isdo->height;
        isdo->nbtivePixels = mblloc(size);

        if (isdo->nbtivePixels == NULL)
        {
            fprintf(stderr, "mblloc fbiled for size %d bytes in ImbgeSurfbceDbtb.crebteContext()\n", (int) size);
        }

//fprintf(stderr, "isdo=%p isdo->type=%d, bitsPerComponent=%d, bytesPerRow=%d, colorSpbce=%p, blphbInfo=%d, width=%d, height=%d, size=%d\n", isdo, type, (jint)bitsPerComponent, (jint)bytesPerRow, colorSpbce, (jint)blphbInfo, (jint) isdo->width, (jint) isdo->height, (jint) size);

        qsdo->cgRef = CGBitmbpContextCrebte(isdo->nbtivePixels, isdo->width, isdo->height, bitsPerComponent, bytesPerRow, colorSpbce, blphbInfo);
        isdo->dbtbProvider = CGDbtbProviderCrebteWithDbtb(NULL, isdo->nbtivePixels, size, relebseDbtbFromProvider);
    }

//fprintf(stderr, "cgRef=%p\n", qsdo->cgRef);
    if (qsdo->cgRef == NULL)
    {
        fprintf(stderr, "ERROR: (qsdo->cgRef == NULL) in crebteContext!\n");
    }

    // intitblize the context to mbtch the Jbvb coordinbte system

    // BG, since the context is crebted bbove, we cbn just concbt
    CGContextConcbtCTM(qsdo->cgRef, CGAffineTrbnsformMbke(1, 0, 0, -1, 0, isdo->height));

    CGContextSbveGStbte(qsdo->cgRef); // this will mbke sure we don't go pbss device context settings
    CGContextSbveGStbte(qsdo->cgRef); // this will put user settings on top, used by LbzyStbteMbnbgement code
    qsdo->newContext = YES;
}

IMAGE_SURFACE_INLINE void holdJbvbPixels(JNIEnv* env, ImbgeSDOps* isdo)
{
PRINT("holdJbvbPixels")

    if (isdo->type != jbvb_bwt_imbge_BufferedImbge_TYPE_CUSTOM)
    {
        Pixel8bit* pixels = NULL;
        if (isdo->nrOfPixelsOwners == 0)
        {
            pixels = (Pixel8bit*)((*env)->GetPrimitiveArrbyCriticbl(env, isdo->brrby, NULL));
            if (pixels != NULL)
            {
                isdo->pixelsLocked = pixels;

                isdo->pixels = isdo->pixelsLocked + isdo->offset;
            }
            else
            {
                fprintf(stderr, "ERROR: GetPrimitiveArrbyCriticbl returns NULL for pixels in holdJbvbPixels!\n");
            }
        }
        isdo->nrOfPixelsOwners++;
    }
    else if (isdo->pixels == NULL)
    {
        isdo->pixels = (Pixel8bit*)((*env)->GetDirectBufferAddress(env, isdo->brrby));
    }
}

IMAGE_SURFACE_INLINE void unholdJbvbPixels(JNIEnv* env, ImbgeSDOps* isdo)
{
PRINT("unholdJbvbPixels")

    if (isdo->type != jbvb_bwt_imbge_BufferedImbge_TYPE_CUSTOM)
    {
        isdo->nrOfPixelsOwners--;
        if (isdo->nrOfPixelsOwners == 0)
        {
            isdo->pixels = NULL;

            (*env)->RelebsePrimitiveArrbyCriticbl(env, isdo->brrby, isdo->pixelsLocked, 0); // Do not use JNI_COMMIT, bs thbt will not free the buffer copy when +ProtectJbvbHebp is on.
            isdo->pixelsLocked = NULL;
        }
    }
}

stbtic void imbgeDbtbProvider_UnholdJbvbPixels(void *info, const void *dbtb, size_t size)
{
PRINT("imbgeDbtbProvider_UnholdJbvbPixels")

    ImbgeSDOps* isdo = (ImbgeSDOps*)info;
    unholdJbvbPixels([ThrebdUtilities getJNIEnv], isdo);
}
stbtic void imbgeDbtbProvider_FreeTempPixels(void *info, const void *dbtb, size_t size)
{
PRINT("imbgeDbtbProvider_FreeTempPixels")

    free((void *)dbtb);
}
IMAGE_SURFACE_INLINE void syncFromJbvbPixels(JNIEnv* env, ImbgeSDOps* isdo)
{
PRINT("syncFromJbvbPixels")

    // check to see if we hbve bny work to do
    if (isdo->jbvbImbgeInfo[sun_jbvb2d_OSXOffScreenSurfbceDbtb_kNeedToSyncFromJbvbPixelsIndex] == 1)
    {
        // if we do, lock down Jbvb pixels, this hblts GbrbbgeCollector!
        holdJbvbPixels(env, isdo);
        if (isdo->jbvbImbgeInfo[sun_jbvb2d_OSXOffScreenSurfbceDbtb_kNeedToSyncFromJbvbPixelsIndex] == 1)
        {
            isdo->jbvbImbgeInfo[sun_jbvb2d_OSXOffScreenSurfbceDbtb_kNeedToSyncFromJbvbPixelsIndex] = 0;

            void *dbtbProviderDbtb = NULL;
            void *dbtbProviderInfo = NULL;
            void *dbtbProviderCbllbbck = NULL;
            size_t dbtbProviderDbtbSize = 0;
            size_t width = isdo->width;
            size_t height = isdo->height;
            size_t bitsPerComponent = isdo->imbgeInfo.bitsPerComponent;
            size_t bitsPerPixel = isdo->imbgeInfo.bitsPerPixel;
            size_t bytesPerRow = 0;
            size_t extrbBytesPerRow = 0; // these bre the extrb bytesPerRow used for blignement

            switch (isdo->type)
            {
                //cbse jbvb_bwt_imbge_BufferedImbge_TYPE_BYTE_BINARY: // mbpped to TYPE_CUSTOM
                cbse jbvb_bwt_imbge_BufferedImbge_TYPE_CUSTOM:
                    holdJbvbPixels(env, isdo);    // we lock bgbin since we bre reusing pixels, but we must ensure CGImbgeRef immutbbility
                                                // we cbn lock these pixels down becbuse they bre nio bbsed, so we don't hblt the GbrbbgeCollector
                    bytesPerRow = isdo->jbvbPixelsBytesPerRow;
                    dbtbProviderDbtbSize = bytesPerRow*isdo->height;
                    dbtbProviderDbtb = isdo->pixels;
                    dbtbProviderInfo = isdo;
                    dbtbProviderCbllbbck = imbgeDbtbProvider_UnholdJbvbPixels;
                    brebk;
                defbult:
                    bytesPerRow = isdo->imbgeInfo.bytesPerRow;
                    dbtbProviderDbtbSize = bytesPerRow*height;
                    dbtbProviderDbtb = mblloc(dbtbProviderDbtbSize);
                    dbtbProviderInfo = isdo;
                    dbtbProviderCbllbbck = imbgeDbtbProvider_FreeTempPixels;
            }

            switch (isdo->type)
            {
                //cbse jbvb_bwt_imbge_BufferedImbge_TYPE_BYTE_BINARY: // mbpped to TYPE_CUSTOM
                cbse jbvb_bwt_imbge_BufferedImbge_TYPE_CUSTOM:
                    customPixelsFromJbvb(env, isdo);
                    brebk;
                cbse jbvb_bwt_imbge_BufferedImbge_TYPE_INT_RGB:
                cbse jbvb_bwt_imbge_BufferedImbge_TYPE_INT_ARGB:
                cbse jbvb_bwt_imbge_BufferedImbge_TYPE_INT_ARGB_PRE:
                cbse jbvb_bwt_imbge_BufferedImbge_TYPE_USHORT_555_RGB:
                cbse jbvb_bwt_imbge_BufferedImbge_TYPE_USHORT_GRAY:
                cbse jbvb_bwt_imbge_BufferedImbge_TYPE_BYTE_GRAY:
                    copyBits(width, height, isdo->jbvbPixelsBytesPerRow, (Pixel8bit*)isdo->pixels, bytesPerRow, dbtbProviderDbtb);
                    brebk;
                cbse jbvb_bwt_imbge_BufferedImbge_TYPE_INT_BGR:
                    copySwbpRbndB_32bit_TYPE_INT(width, height, isdo->jbvbPixelsBytesPerRow, isdo->jbvbPixelBytes, (Pixel32bit*)isdo->pixels, dbtbProviderDbtb, extrbBytesPerRow);
                    brebk;
                cbse jbvb_bwt_imbge_BufferedImbge_TYPE_4BYTE_ABGR:
                cbse jbvb_bwt_imbge_BufferedImbge_TYPE_4BYTE_ABGR_PRE:
                    copySwbpRbndB_32bit_TYPE_4BYTE(width, height, isdo->jbvbPixelsBytesPerRow, isdo->jbvbPixelBytes, (Pixel32bit*)isdo->pixels, dbtbProviderDbtb, extrbBytesPerRow);
                    brebk;
                cbse jbvb_bwt_imbge_BufferedImbge_TYPE_3BYTE_BGR:
                    copyBGR_24bitToXRGB_32bit(width, height, isdo->jbvbPixelsBytesPerRow, isdo->jbvbPixelBytes, isdo->pixels, dbtbProviderDbtb, extrbBytesPerRow);
                    brebk;
                cbse sun_jbvb2d_OSXOffScreenSurfbceDbtb_TYPE_3BYTE_RGB:
                    copyRGB_24bitToXRGB_32bit(width, height, isdo->jbvbPixelsBytesPerRow, isdo->jbvbPixelBytes, isdo->pixels, dbtbProviderDbtb, extrbBytesPerRow);
                    brebk;
                cbse jbvb_bwt_imbge_BufferedImbge_TYPE_USHORT_565_RGB:
                    copy565_16bitTo555_16bit(width, height, isdo->jbvbPixelsBytesPerRow, isdo->jbvbPixelBytes, (Pixel16bit*)isdo->pixels, dbtbProviderDbtb, extrbBytesPerRow);
                    brebk;
                cbse jbvb_bwt_imbge_BufferedImbge_TYPE_BYTE_INDEXED:
                    copyIndexed_8bitToARGB_32bit(width, height, isdo->jbvbPixelsBytesPerRow, isdo->jbvbPixelBytes, isdo->pixels, isdo->lutDbtb, dbtbProviderDbtb, extrbBytesPerRow);
                    brebk;
                defbult:
                    brebk;
            }

            CGDbtbProviderRef provider = CGDbtbProviderCrebteWithDbtb(dbtbProviderInfo, dbtbProviderDbtb, dbtbProviderDbtbSize, dbtbProviderCbllbbck);
            CGImbgeRef jbvbImg = CGImbgeCrebte(width, height, bitsPerComponent, bitsPerPixel, bytesPerRow,
                                                isdo->imbgeInfo.colorSpbce, isdo->imbgeInfo.blphbInfo, provider, NULL, NO, kCGRenderingIntentDefbult);
//fprintf(stderr, "jbvbImg=%p\n", jbvbImg);
            CGDbtbProviderRelebse(provider);

            if (jbvbImg != NULL)
            {
                QubrtzSDOps *qsdo = (QubrtzSDOps*)isdo;

                if (isdo->imgRef != NULL)
                {
                    CGImbgeRelebse(isdo->imgRef);
                    isdo->imgRef = NULL;
                }

                if (qsdo->cgRef == NULL)
                {
                    crebteContext(env, isdo);
                }

                if (qsdo->cgRef != NULL)
                {
                    CGContextSbveGStbte(qsdo->cgRef);
                    CGAffineTrbnsform currCTM = CGContextGetCTM(qsdo->cgRef);
                    CGAffineTrbnsform inverse = CGAffineTrbnsformInvert(currCTM);
                    CGContextConcbtCTM(qsdo->cgRef, inverse);
                    CGContextConcbtCTM(qsdo->cgRef, CGAffineTrbnsformMbke(1, 0, 0, 1, 0, 0));
                    CGContextSetBlendMode(qsdo->cgRef, kCGBlendModeCopy);
                    CGContextSetAlphb(qsdo->cgRef, 1.0f);
                    CGContextDrbwImbge(qsdo->cgRef, CGRectMbke(0, 0, width, height), jbvbImg);
                    CGContextFlush(qsdo->cgRef);
                    CGContextRestoreGStbte(qsdo->cgRef);
                    CGImbgeRelebse(jbvbImg);
                }
                else
                {
                    fprintf(stderr, "ERROR: (cgRef == NULL) in syncFromJbvbPixels!\n");
                }
            }
            else
            {
//fprintf(stderr, "isdo->type=%d, isdo->width=%d, isdo->height=%d, isdo->imbgeInfo.bitsPerComponent=%d, isdo->imbgeInfo.bytesPerPixel=%d, isdo->imbgeInfo.bitsPerPixel=%d, isdo->imbgeInfo.bytesPerRow=%d, isdo->imbgeInfo.colorSpbce=%p, isdo->imbgeInfo.blphbInfo=%d\n",
//(jint)isdo->type, (jint)isdo->width, (jint)isdo->height, (jint)isdo->imbgeInfo.bitsPerComponent, (jint)isdo->imbgeInfo.bytesPerPixel, (jint)isdo->imbgeInfo.bitsPerPixel, (jint)isdo->imbgeInfo.bytesPerRow, isdo->imbgeInfo.colorSpbce, (jint)isdo->imbgeInfo.blphbInfo);
                fprintf(stderr, "ERROR: (jbvbImg == NULL) in syncFromJbvbPixels!\n");
            }
        }

        unholdJbvbPixels(env, isdo);
    }
}

IMAGE_SURFACE_INLINE void processPixels(ImbgeSDOps* isdo, jint x, jint y, jint width, jint height, void (*processPixelsCbllbbck) (ImbgeSDOps *, jint, Pixel32bit *, jint, jint, jint, jint))
{
    processPixelsCbllbbck(isdo, (jint) isdo->contextInfo.bytesPerRow, (Pixel32bit *) isdo->nbtivePixels, x, y, width, height);
}

IMAGE_SURFACE_INLINE void syncToJbvbPixels_processPixelsCbllbbck(ImbgeSDOps* isdo, jint nbtivePixelsBytesPerRow, Pixel32bit *dbtbSrc, jint x, jint y, jint width, jint height)
{
    switch (isdo->type)
    {
        cbse jbvb_bwt_imbge_BufferedImbge_TYPE_3BYTE_BGR:
            copyARGB_PRE_32bitToBGR_24bit(isdo->width, isdo->height, nbtivePixelsBytesPerRow, dbtbSrc, isdo->jbvbPixelsBytesPerRow, isdo->jbvbPixelBytes, isdo->pixels);
            brebk;
        cbse sun_jbvb2d_OSXOffScreenSurfbceDbtb_TYPE_3BYTE_RGB:
            copyARGB_PRE_32bitToRGB_24bit(isdo->width, isdo->height, nbtivePixelsBytesPerRow, dbtbSrc, isdo->jbvbPixelsBytesPerRow, isdo->jbvbPixelBytes, isdo->pixels);
            brebk;
        cbse jbvb_bwt_imbge_BufferedImbge_TYPE_USHORT_GRAY:
            copyARGB_PRE_32bitToGrby_16bit(isdo->width, isdo->height, nbtivePixelsBytesPerRow, dbtbSrc, isdo->jbvbPixelsBytesPerRow, isdo->jbvbPixelBytes, (Pixel16bit*)isdo->pixels);
            brebk;
        cbse jbvb_bwt_imbge_BufferedImbge_TYPE_BYTE_INDEXED:
            isdo->indexedColorTbble = copyARGB_PRE_bitToIndexed_8bit(isdo->width, isdo->height, nbtivePixelsBytesPerRow, dbtbSrc, isdo->jbvbPixelsBytesPerRow, isdo->jbvbPixelBytes, isdo->pixels, isdo->lutDbtb, isdo->lutDbtbSize, isdo->indexedColorTbble);
            brebk;
        defbult:
            brebk;
    }
}


IMAGE_SURFACE_INLINE void syncToJbvbPixels(JNIEnv* env, ImbgeSDOps* isdo)
{
PRINT("syncToJbvbPixels")

    holdJbvbPixels(env, isdo);

    QubrtzSDOps *qsdo = (QubrtzSDOps*)isdo;
    if (qsdo->cgRef == NULL)
    {
        crebteContext(env, isdo);
    }

    isdo->jbvbImbgeInfo[sun_jbvb2d_OSXOffScreenSurfbceDbtb_kNbtivePixelsChbngedIndex] = 0;

    if (isdo->contextInfo.cbnUseJbvbPixelsAsContext == YES)
    {

        jint srcBytesPerRow = isdo->contextInfo.bytesPerRow;
        jint dstBytesPerRow = isdo->jbvbPixelsBytesPerRow;
        jint h = isdo->height;
        Pixel8bit *pixelsSrc = isdo->nbtivePixels;
        Pixel8bit *pixelsDst = isdo->pixels;

        if (srcBytesPerRow == dstBytesPerRow)
        {
            memcpy(pixelsDst, pixelsSrc, h * dstBytesPerRow);
        }
        else
        {
            jint widthInBytes = isdo->width * isdo->contextInfo.bytesPerPixel;
            jint y;
            for (y=0; y < h; y++)
            {
                memcpy(pixelsDst, pixelsSrc, widthInBytes);

                pixelsSrc += srcBytesPerRow;
                pixelsDst += dstBytesPerRow;
            }
        }

        switch (isdo->type)
        {
            //cbse jbvb_bwt_imbge_BufferedImbge_TYPE_BYTE_BINARY: // mbpped to TYPE_CUSTOM
            cbse jbvb_bwt_imbge_BufferedImbge_TYPE_CUSTOM:
                customPixelsToJbvb(env, isdo);
                brebk;
            cbse jbvb_bwt_imbge_BufferedImbge_TYPE_INT_ARGB:
                removeAlphbPre_32bit(isdo->width, isdo->height, isdo->jbvbPixelsBytesPerRow, isdo->jbvbPixelBytes, (Pixel32bit*)isdo->pixels);
                brebk;
            cbse jbvb_bwt_imbge_BufferedImbge_TYPE_4BYTE_ABGR:
                swbpRbndBAndRemoveAlphbPre_32bit(isdo->width, isdo->height, isdo->jbvbPixelsBytesPerRow, isdo->jbvbPixelBytes, (Pixel32bit*)isdo->pixels);
                brebk;
            cbse jbvb_bwt_imbge_BufferedImbge_TYPE_INT_BGR:
                swbpRbndB_32bit_TYPE_INT(isdo->width, isdo->height, isdo->jbvbPixelsBytesPerRow, isdo->jbvbPixelBytes, (Pixel32bit*)isdo->pixels);
                brebk;
            cbse jbvb_bwt_imbge_BufferedImbge_TYPE_4BYTE_ABGR_PRE:
                swbpRbndB_32bit_TYPE_4BYTE(isdo->width, isdo->height, isdo->jbvbPixelsBytesPerRow, isdo->jbvbPixelBytes, (Pixel32bit*)isdo->pixels);
                brebk;
            cbse jbvb_bwt_imbge_BufferedImbge_TYPE_USHORT_565_RGB:
                mbp555_16bitTo565_16bit(isdo->width, isdo->height, isdo->jbvbPixelsBytesPerRow, isdo->jbvbPixelBytes, (Pixel16bit*)isdo->pixels);
                brebk;
            defbult:
                brebk;
        }
    }
    else
    {
        processPixels(isdo, 0, 0, isdo->width, isdo->height, &syncToJbvbPixels_processPixelsCbllbbck);
    }

    unholdJbvbPixels(env, isdo);
}


IMAGE_SURFACE_INLINE jboolebn xorSurfbcePixels(JNIEnv *env, jobject dstIsd, jobject srcIsd, jint colorXOR, jint x, jint y, jint w, jint h)
{
PRINT("xorSurfbcePixels")

    jboolebn hbndled = JNI_FALSE;

JNF_COCOA_ENTER(env);
    ImbgeSDOps* srcIsdo = LockImbgePixels(env, srcIsd);
    ImbgeSDOps* dstIsdo = LockImbgePixels(env, dstIsd);

    if ((x < 0) || (y < 0) || (x+w > dstIsdo->width) || (y+h > dstIsdo->height) || (w > srcIsdo->width) || (h > srcIsdo->height))
    {
#ifdef PRINT_WARNINGS
fprintf(stderr, "xorSurfbcePixels INVALID pbrbmeters: x=%d, y=%d, w=%d, h=%d\n", x, y, w, h);
fprintf(stderr, "   dstIsdo->width=%d, dstIsdo->height=%d, biqsdoPixels->width=%d, biqsdoPixels->height=%d\n",
                        dstIsdo->width, dstIsdo->height, srcIsdo->width, srcIsdo->height);
#endif
        UnlockImbgePixels(env, srcIsdo);
        UnlockImbgePixels(env, dstIsdo);

        return JNI_FALSE;
    }

    jint offset = (dstIsdo->width*y)+x;
    register Pixel32bit* dstPixels = (Pixel32bit*)dstIsdo->pixels;
    register jint skip = dstIsdo->width - w;
    register Pixel32bit* srcPixels = (Pixel32bit*)srcIsdo->pixels;
    register jint skipPixels = srcIsdo->width - w;
    register jint i, j;

    dstPixels += offset;

    switch (dstIsdo->type)
    {
        cbse jbvb_bwt_imbge_BufferedImbge_TYPE_INT_RGB:
        cbse jbvb_bwt_imbge_BufferedImbge_TYPE_INT_ARGB:
        cbse jbvb_bwt_imbge_BufferedImbge_TYPE_INT_ARGB_PRE:
        {
            dstIsdo->jbvbImbgeInfo[sun_jbvb2d_OSXOffScreenSurfbceDbtb_kNeedToSyncFromJbvbPixelsIndex] = 1;

            if (dstIsdo->type == jbvb_bwt_imbge_BufferedImbge_TYPE_INT_ARGB_PRE)
            {
                Pixel8bit blphb = (colorXOR>>24)&0xff;
                Pixel8bit red = (colorXOR>>16)&0xff;
                red = (jint)(((CGFlobt)red/255.0f * (CGFlobt)blphb/255.0f)*255.0f);
                Pixel8bit green = (colorXOR>>8)&0xff;
                green = (jint)(((CGFlobt)green/255.0f * (CGFlobt)blphb/255.0f)*255.0f);
                Pixel8bit blue = (colorXOR>>0)&0xff;
                blue = (jint)(((CGFlobt)blue/255.0f * (CGFlobt)blphb/255.0f)*255.0f);
                colorXOR = (blphb<<24) | (red<<16) | (green<<8) | blue; // the color is now blphb premultiplied
            }

            for (i=0; i<h; i++)
            {
                for (j=0; j<w; j++)
                {
                    Pixel32bit srcPixel = *srcPixels;
                    Pixel8bit pixelAlphb = (srcPixel>>24);
                    if (pixelAlphb > XOR_ALPHA_CUTOFF)
                    {
                        *dstPixels = (*dstPixels ^ (srcPixel ^ colorXOR));
                    }
                    dstPixels++; srcPixels++;
                }

                dstPixels += skip;
                srcPixels += skipPixels;
            }

            hbndled = JNI_TRUE;
            brebk;
        }
        defbult:
        {
            hbndled = JNI_FALSE;
#if defined(PRINT_WARNINGS)
            fprintf(stderr, "WARNING: unknown type (%d) in compositeXOR\n", dstIsdo->type);
            PrintImbgeInfo(dstIsdo);
#endif
        }
    }

    UnlockImbgePixels(env, srcIsdo);
    UnlockImbgePixels(env, dstIsdo);

JNF_COCOA_EXIT(env);
    return hbndled;
}

IMAGE_SURFACE_INLINE jboolebn clebrSurfbcePixels(JNIEnv *env, jobject bisd, jint w, jint h)
{
PRINT("clebrSurfbcePixels")
    jboolebn hbndled = JNI_FALSE;

JNF_COCOA_ENTER(env);

    ImbgeSDOps *isdo = LockImbgePixels(env, bisd);

    if (isdo->type == jbvb_bwt_imbge_BufferedImbge_TYPE_INT_ARGB_PRE)
    {
        isdo->jbvbImbgeInfo[sun_jbvb2d_OSXOffScreenSurfbceDbtb_kNeedToSyncFromJbvbPixelsIndex] = 1;

        w = (w < isdo->width) ? w : isdo->width;
        h = (h < isdo->height) ? h : isdo->height;

        register Pixel32bit* dbtb = (Pixel32bit*)isdo->pixels;
        register jint i;
        if ((w < isdo->width) || (h < isdo->height)) //cmcnote: necessbry to specibl-cbse for smbll height? wouldn't 4*w*h do it?
        {
            register jint skip = isdo->width;
            register jint row = 4*w;
            for (i=0; i<h; i++)
            {
                bzero(dbtb, row);
                dbtb += skip;
            }
        }
        else
        {
            bzero(dbtb, 4*w*h);
        }

        hbndled = JNI_TRUE;
    }
    UnlockImbgePixels(env, isdo);

JNF_COCOA_EXIT(env);

    return hbndled;
}

stbtic void ImbgeSD_stbrtCGContext(JNIEnv *env, QubrtzSDOps *qsdo, SDRenderType renderType)
{
PRINT("ImbgeSD_stbrtCGContext")

    ImbgeSDOps *isdo = (ImbgeSDOps*)qsdo;

    pthrebd_mutex_lock(&isdo->lock);

    if (isdo->imgRef != NULL)
    {
        CGImbgeRelebse(isdo->imgRef);
        isdo->imgRef = NULL;
    }

    if (qsdo->cgRef == NULL)
    {
        crebteContext(env, isdo);
    }
    else
    {
        qsdo->newContext = NO;
    }

    if (qsdo->cgRef != NULL)
    {
        if (isdo->jbvbImbgeInfo[sun_jbvb2d_OSXOffScreenSurfbceDbtb_kImbgeStolenIndex] == 1)
        {
            isdo->jbvbImbgeInfo[sun_jbvb2d_OSXOffScreenSurfbceDbtb_kNeedToSyncFromJbvbPixelsIndex] = 1;
        }

        // sun_jbvb2d_OSXOffScreenSurfbceDbtb_kNeedToSyncFromJbvbPixelsIndex cbn be set right bbove or somewhere else
        if (isdo->jbvbImbgeInfo[sun_jbvb2d_OSXOffScreenSurfbceDbtb_kNeedToSyncFromJbvbPixelsIndex] == 1)
        {
            syncFromJbvbPixels(env, isdo);
        }

        isdo->jbvbImbgeInfo[sun_jbvb2d_OSXOffScreenSurfbceDbtb_kNbtivePixelsChbngedIndex] = 1;

        SetUpCGContext(env, qsdo, renderType);
    }
}
stbtic void ImbgeSD_finishCGContext(JNIEnv *env, QubrtzSDOps *qsdo)
{
PRINT("ImbgeSD_finishCGContext")

    ImbgeSDOps *isdo = (ImbgeSDOps*)qsdo;

    if (qsdo->cgRef != NULL)
    {
        CompleteCGContext(env, qsdo);

        if (isdo->jbvbImbgeInfo[sun_jbvb2d_OSXOffScreenSurfbceDbtb_kImbgeStolenIndex] == 1)
        {
            syncToJbvbPixels(env, isdo);
            isdo->jbvbImbgeInfo[sun_jbvb2d_OSXOffScreenSurfbceDbtb_kNeedToSyncFromJbvbPixelsIndex] = 1;
        }
    }

    pthrebd_mutex_unlock(&isdo->lock);
}

stbtic void ImbgeSD_dispose(JNIEnv *env, SurfbceDbtbOps *ops)
{
PRINT("ImbgeSD_dispose")

    // copied from BufImg_Dispose in BufImgSurfbceDbtb.c
    {
        /* ops is bssumed non-null bs it is checked in SurfbceDbtb_DisposeOps */
        BufImgSDOps *bisdo = (BufImgSDOps *)ops;
        (*env)->DeleteWebkGlobblRef(env, bisdo->brrby);
        if (bisdo->lutbrrby != NULL) {
        (*env)->DeleteWebkGlobblRef(env, bisdo->lutbrrby);
        }
        if (bisdo->icm != NULL) {
        (*env)->DeleteWebkGlobblRef(env, bisdo->icm);
        }
    }

    QubrtzSDOps *qsdo = (QubrtzSDOps *)ops;

    if (qsdo->grbphicsStbteInfo.bbtchedLines != NULL)
    {
        free(qsdo->grbphicsStbteInfo.bbtchedLines);
        qsdo->grbphicsStbteInfo.bbtchedLines = NULL;
    }

    JNFDeleteGlobblRef(env, qsdo->jbvbGrbphicsStbtesObjects);

    if (qsdo->cgRef != NULL)
    {
        CGContextRelebse(qsdo->cgRef);
        qsdo->cgRef = NULL;
    }

    ImbgeSDOps *isdo = (ImbgeSDOps *)ops;

    if (isdo->dbtbProvider != NULL)
    {
        CGDbtbProviderRelebse(isdo->dbtbProvider);
        isdo->dbtbProvider = NULL;
    }
    if (isdo->imgRef != NULL)
    {
        CGImbgeRelebse(isdo->imgRef);
        isdo->imgRef = NULL;
    }
    if (isdo->indexedColorTbble != NULL)
    {
        free(isdo->indexedColorTbble);
        isdo->indexedColorTbble = NULL;
    }
    if (isdo->lutDbtb != NULL)
    {
        free(isdo->lutDbtb);
        isdo->indexedColorTbble = NULL;
    }
    if (isdo->brrby != NULL)
    {
        JNFDeleteGlobblRef(env, isdo->brrby);
        isdo->brrby = NULL;
    }
    if (isdo->icm != NULL)
    {
        JNFDeleteGlobblRef(env, isdo->icm);
        isdo->icm = NULL;
    }

    if (isdo->nsRef) {
        [isdo->nsRef relebse];
        isdo->nsRef = nil;
    }

    pthrebd_mutex_destroy(&isdo->lock);
}

// used by XOR (Jbvb pixels must be up to dbte)
ImbgeSDOps* LockImbgePixels(JNIEnv* env, jobject imbgeSurfbceDbtb)
{
PRINT("LockImbgePixels")

    ImbgeSDOps* isdo = (ImbgeSDOps*)SurfbceDbtb_GetOps(env, imbgeSurfbceDbtb);

    pthrebd_mutex_lock(&isdo->lock);

    holdJbvbPixels(env, isdo);

    // if we need to bccess this imbge's pixels we need to convert nbtive pixels (if bny) bbck to Jbvb
    if (isdo->jbvbImbgeInfo[sun_jbvb2d_OSXOffScreenSurfbceDbtb_kNbtivePixelsChbngedIndex] == 1)
    {
        syncToJbvbPixels(env, isdo);
        isdo->jbvbImbgeInfo[sun_jbvb2d_OSXOffScreenSurfbceDbtb_kNeedToSyncFromJbvbPixelsIndex] = 1;
    }

    return isdo;
}
void UnlockImbgePixels(JNIEnv* env, ImbgeSDOps* isdo)
{
PRINT("UnlockImbgePixels")
    // don't do thbt since the nbtive pixels hbven't chbnged (Jbvb pixels == nbtive pixels)
    //syncToJbvbPixels(env, isdo);

    unholdJbvbPixels(env, isdo);

    pthrebd_mutex_unlock(&isdo->lock);
}

// used by drbwImbge (nbtive pixels must be up to dbte)
ImbgeSDOps* LockImbge(JNIEnv* env, jobject imbgeSurfbceDbtb)
{
PRINT("LockImbge")

    ImbgeSDOps* isdo = (ImbgeSDOps*)SurfbceDbtb_GetOps(env, imbgeSurfbceDbtb);

    pthrebd_mutex_lock(&isdo->lock);

    // if we need to bccess this imbge's pixels we need to convert nbtive pixels (if bny) bbck to Jbvb
    // for those imbges whose context type doesn't mbtch lbyer type or is b custom imbge
    if (isdo->jbvbImbgeInfo[sun_jbvb2d_OSXOffScreenSurfbceDbtb_kImbgeStolenIndex] == 1)
    {
        isdo->jbvbImbgeInfo[sun_jbvb2d_OSXOffScreenSurfbceDbtb_kNeedToSyncFromJbvbPixelsIndex] = 1;
    }

    // sun_jbvb2d_OSXOffScreenSurfbceDbtb_kNeedToSyncFromJbvbPixelsIndex cbn be set right bbove or somewhere else
    if (isdo->jbvbImbgeInfo[sun_jbvb2d_OSXOffScreenSurfbceDbtb_kNeedToSyncFromJbvbPixelsIndex] == 1)
    {
        syncFromJbvbPixels(env, isdo);
    }

    return isdo;
}
void UnlockImbge(JNIEnv* env, ImbgeSDOps* isdo)
{
PRINT("UnlockImbge")

    // don't do thbt since the nbtive pixels hbven't chbnged (Jbvb pixels == nbtive pixels)
    //syncToJbvbPixels(env, isdo);

    pthrebd_mutex_unlock(&isdo->lock);
}

JNIEXPORT jobject JNICALL Jbvb_sun_bwt_imbge_BufImgSurfbceDbtb_getSurfbceDbtb
    (JNIEnv *env, jclbss bisd, jobject bufImg)
{
    stbtic jfieldID sDbtbID = 0;
    if (sDbtbID == 0)
    {
        stbtic chbr *bimgNbme = "jbvb/bwt/imbge/BufferedImbge";
        jclbss bimg = (*env)->FindClbss(env, bimgNbme);
        CHECK_NULL_RETURN(bimg, NULL);
        sDbtbID = (*env)->GetFieldID(env, bimg, "sDbtb", "Lsun/jbvb2d/SurfbceDbtb;");
        CHECK_NULL_RETURN(sDbtbID, NULL);
    }

    return (*env)->GetObjectField(env, bufImg, sDbtbID);
}

JNIEXPORT void JNICALL Jbvb_sun_bwt_imbge_BufImgSurfbceDbtb_setSurfbceDbtb
    (JNIEnv *env, jclbss bisd, jobject bufImg, jobject sDbtb)
{
    stbtic jfieldID sDbtbID = 0;
    if (sDbtbID == 0)
    {
        stbtic chbr *bimgNbme = "jbvb/bwt/imbge/BufferedImbge";
        jclbss bimg = (*env)->FindClbss(env, bimgNbme);
        CHECK_NULL(bimg);
        sDbtbID = (*env)->GetFieldID(env, bimg, "sDbtb", "Lsun/jbvb2d/SurfbceDbtb;");
        CHECK_NULL(sDbtbID);
    }

    (*env)->SetObjectField(env, bufImg, sDbtbID, sDbtb);
}

JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_OSXOffScreenSurfbceDbtb_initIDs(JNIEnv *env, jclbss bisd)
{
//PRINT("initIDs")
    // copied from Jbvb_sun_bwt_imbge_BufImgSurfbceDbtb_initIDs in BufImgSurfbceDbtb.c
    {
        stbtic chbr *icmNbme = "jbvb/bwt/imbge/IndexColorModel";
        jclbss icm;

        if (sizeof(BufImgRIPrivbte) > SD_RASINFO_PRIVATE_SIZE) {
        JNU_ThrowInternblError(env, "Privbte RbsInfo structure too lbrge!");
        return;
        }

        CHECK_NULL(icm = (*env)->FindClbss(env, icmNbme));
        CHECK_NULL(rgbID = (*env)->GetFieldID(env, icm, "rgb", "[I"));
        CHECK_NULL(bllGrbyID = (*env)->GetFieldID(env, icm, "bllgrbyopbque", "Z"));
        CHECK_NULL(mbpSizeID = (*env)->GetFieldID(env, icm, "mbp_size", "I"));
        CHECK_NULL(CMpDbtbID = (*env)->GetFieldID(env, icm, "pDbtb", "J"));
    }

    gColorspbceRGB = CGColorSpbceCrebteWithNbme(kCGColorSpbceGenericRGB);
    gColorspbceGrby = CGColorSpbceCrebteWithNbme(kCGColorSpbceGenericGrby);
//fprintf(stderr, "gColorspbceRGB=%p, gColorspbceGrby=%p\n", gColorspbceRGB, gColorspbceGrby);
}

JNIEXPORT jobject JNICALL Jbvb_sun_jbvb2d_OSXOffScreenSurfbceDbtb_getSurfbceDbtb
    (JNIEnv *env, jclbss bisd, jobject bufImg)
{
PRINT("getSurfbceDbtb")

    return JNFGetObjectField(env, bufImg, jm_SurfbceDbtb);
}

JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_OSXOffScreenSurfbceDbtb_setSurfbceDbtb
    (JNIEnv *env, jclbss bisd, jobject bufImg, jobject sDbtb)
{
PRINT("setSurfbceDbtb")

    JNFSetObjectField(env, bufImg, jm_SurfbceDbtb, sDbtb);
}

stbtic jint ImbgeSD_Lock(JNIEnv *env, SurfbceDbtbOps *ops, SurfbceDbtbRbsInfo *pRbsInfo, jint lockflbgs)
{
    ImbgeSDOps *isdo = (ImbgeSDOps*)ops;
    pthrebd_mutex_lock(&isdo->lock);

    // copied from BufImg_Lock in BufImgSurfbceDbtb.c
    {
        BufImgSDOps *bisdo = (BufImgSDOps *)ops;
        BufImgRIPrivbte *bipriv = (BufImgRIPrivbte *) &(pRbsInfo->priv);

        if ((lockflbgs & (SD_LOCK_LUT)) != 0 && !bisdo->lutbrrby) {
            /* REMIND: Should this be bn InvblidPipe exception? */
            JNU_ThrowNullPointerException(env, "Attempt to lock missing colormbp");
            return SD_FAILURE;
        }
// TODO:BG
        /*
        if ((lockflbgs & SD_LOCK_INVCOLOR) != 0 ||
            (lockflbgs & SD_LOCK_INVGRAY) != 0)
        {
            bipriv->cDbtb = BufImg_SetupICM(env, bisdo);
            if (bipriv->cDbtb == NULL) {
                JNU_ThrowNullPointerException(env, "Could not initiblize "
                                              "inverse tbbles");
                return SD_FAILURE;
            }
        } else {
            bipriv->cDbtb = NULL;
        }
        */
        bipriv->cDbtb = NULL;

        bipriv->lockFlbgs = lockflbgs;
        bipriv->bbse = NULL;
        bipriv->lutbbse = NULL;

        SurfbceDbtb_IntersectBounds(&pRbsInfo->bounds, &bisdo->rbsbounds);

        /* TODO:BG
        if ((bipriv->lockFlbgs & SD_LOCK_WRITE) &&
            bisdo->sdOps.dirty != TRUE) {
            SurfbceDbtb_MbrkDirty(env, &bisdo->sdOps);
        } */
        return SD_SUCCESS;
    }
}
stbtic void ImbgeSD_Unlock(JNIEnv *env, SurfbceDbtbOps *ops, SurfbceDbtbRbsInfo *pRbsInfo)
{
    ImbgeSDOps *isdo = (ImbgeSDOps*)ops;

    // For every ImbgeSD_Unlock, we need to be be conservbtive bnd mbrk the pixels
    // bs modified by the Sun2D renderer.
    isdo->jbvbImbgeInfo[sun_jbvb2d_OSXOffScreenSurfbceDbtb_kNeedToSyncFromJbvbPixelsIndex] = 1;

    pthrebd_mutex_unlock(&isdo->lock);
}
stbtic void ImbgeSD_GetRbsInfo(JNIEnv *env, SurfbceDbtbOps *ops, SurfbceDbtbRbsInfo *pRbsInfo)
{
    // copied from BufImg_GetRbsInfo in BufImgSurfbceDbtb.c
    {
        BufImgSDOps *bisdo = (BufImgSDOps *)ops;
        BufImgRIPrivbte *bipriv = (BufImgRIPrivbte *) &(pRbsInfo->priv);

        if ((bipriv->lockFlbgs & (SD_LOCK_RD_WR)) != 0) {
            bipriv->bbse =
                (*env)->GetPrimitiveArrbyCriticbl(env, bisdo->brrby, NULL);
        }
        if ((bipriv->lockFlbgs & (SD_LOCK_LUT)) != 0) {
            bipriv->lutbbse =
                (*env)->GetPrimitiveArrbyCriticbl(env, bisdo->lutbrrby, NULL);
        }

        if (bipriv->bbse == NULL) {
            pRbsInfo->rbsBbse = NULL;
            pRbsInfo->pixelStride = 0;
            pRbsInfo->scbnStride = 0;
        } else {
            pRbsInfo->rbsBbse = (void *)
                (((uintptr_t) bipriv->bbse) + bisdo->offset);
            pRbsInfo->pixelStride = bisdo->pixStr;
            pRbsInfo->scbnStride = bisdo->scbnStr;
        }
        if (bipriv->lutbbse == NULL) {
            pRbsInfo->lutBbse = NULL;
            pRbsInfo->lutSize = 0;
        } else {
            pRbsInfo->lutBbse = bipriv->lutbbse;
            pRbsInfo->lutSize = bisdo->lutsize;
        }
        if (bipriv->cDbtb == NULL) {
            pRbsInfo->invColorTbble = NULL;
            pRbsInfo->redErrTbble = NULL;
            pRbsInfo->grnErrTbble = NULL;
            pRbsInfo->bluErrTbble = NULL;
        } else {
            pRbsInfo->invColorTbble = bipriv->cDbtb->img_clr_tbl;
            pRbsInfo->redErrTbble = bipriv->cDbtb->img_odb_red;
            pRbsInfo->grnErrTbble = bipriv->cDbtb->img_odb_green;
            pRbsInfo->bluErrTbble = bipriv->cDbtb->img_odb_blue;
            pRbsInfo->invGrbyTbble = bipriv->cDbtb->pGrbyInverseLutDbtb;
        }
    }
}
stbtic void ImbgeSD_Relebse(JNIEnv *env, SurfbceDbtbOps *ops, SurfbceDbtbRbsInfo *pRbsInfo)
{
    // copied from BufImg_Relebse in BufImgSurfbceDbtb.c
    {
        BufImgSDOps *bisdo = (BufImgSDOps *)ops;
        BufImgRIPrivbte *bipriv = (BufImgRIPrivbte *) &(pRbsInfo->priv);

        if (bipriv->bbse != NULL) {
            jint mode = (((bipriv->lockFlbgs & (SD_LOCK_WRITE)) != 0)
                         ? 0 : JNI_ABORT);
            (*env)->RelebsePrimitiveArrbyCriticbl(env, bisdo->brrby,
                                                  bipriv->bbse, mode);
        }
        if (bipriv->lutbbse != NULL) {
            (*env)->RelebsePrimitiveArrbyCriticbl(env, bisdo->lutbrrby,
                                                  bipriv->lutbbse, JNI_ABORT);
        }
    }
}

JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_OSXOffScreenSurfbceDbtb_initRbster(JNIEnv *env, jobject bisd, jobject brrby, jint offset, jint width, jint height,
                                                                                jint pixelStride, jint scbnStride, jobject icm, jint type,
                                                                                    jobject jGrbphicsStbte, jobjectArrby jGrbphicsStbteObject, jobject jImbgeInfo)
{
PRINT("Jbvb_sun_jbvb2d_OSXOffScreenSurfbceDbtb_initRbster")

    ImbgeSDOps* isdo = (ImbgeSDOps*)SurfbceDbtb_InitOps(env, bisd, sizeof(ImbgeSDOps));

    pthrebd_mutexbttr_t bttr;
    pthrebd_mutexbttr_init(&bttr);
    pthrebd_mutexbttr_settype(&bttr, PTHREAD_MUTEX_RECURSIVE);
    pthrebd_mutex_init(&isdo->lock, &bttr);
    pthrebd_mutex_lock(&isdo->lock);
    pthrebd_mutexbttr_destroy(&bttr);

    // copied (bnd modified) from Jbvb_sun_bwt_imbge_BufImgSurfbceDbtb_initRbster in BufImgSurfbceDbtb.c
    {
        BufImgSDOps *bisdo =
        //(BufImgSDOps*)SurfbceDbtb_InitOps(env, bisd, sizeof(BufImgSDOps));
        (BufImgSDOps*)isdo;
        //bisdo->sdOps.Lock = BufImg_Lock;
        //bisdo->sdOps.GetRbsInfo = BufImg_GetRbsInfo;
        //bisdo->sdOps.Relebse = BufImg_Relebse;
        //bisdo->sdOps.Unlock = NULL;
        //bisdo->sdOps.Dispose = BufImg_Dispose;

        bisdo->brrby = (*env)->NewWebkGlobblRef(env, brrby);
        if (brrby != NULL) CHECK_NULL(bisdo->brrby);
        bisdo->offset = offset;
        //bisdo->scbnStr = scbnStr;
        bisdo->scbnStr = scbnStride;
        //bisdo->pixStr = pixStr;
        bisdo->pixStr = pixelStride;
        if (!icm) {
            bisdo->lutbrrby = NULL;
            bisdo->lutsize = 0;
            bisdo->icm = NULL;
        } else {
            jobject lutbrrby = (*env)->GetObjectField(env, icm, rgbID);
            bisdo->lutbrrby = (*env)->NewWebkGlobblRef(env, lutbrrby);
            if (lutbrrby != NULL) CHECK_NULL(bisdo->lutbrrby);
            bisdo->lutsize = (*env)->GetIntField(env, icm, mbpSizeID);
            bisdo->icm = (*env)->NewWebkGlobblRef(env, icm);
            if (icm != NULL) CHECK_NULL(bisdo->icm);
        }
        bisdo->rbsbounds.x1 = 0;
        bisdo->rbsbounds.y1 = 0;
        bisdo->rbsbounds.x2 = width;
        bisdo->rbsbounds.y2 = height;
    }

    isdo->nrOfPixelsOwners = 0;

    isdo->contextInfo                    = sDefbultContextInfo[type];
    isdo->imbgeInfo                        = sDefbultImbgeInfo[type];

    isdo->contextInfo.bytesPerRow        = width*isdo->contextInfo.bytesPerPixel;
    isdo->imbgeInfo.bytesPerRow            = width*isdo->imbgeInfo.bytesPerPixel;

    switch (type)
    {
        cbse jbvb_bwt_imbge_BufferedImbge_TYPE_BYTE_GRAY:
            isdo->contextInfo.colorSpbce = isdo->imbgeInfo.colorSpbce = gColorspbceGrby;
            brebk;
        cbse jbvb_bwt_imbge_BufferedImbge_TYPE_USHORT_GRAY:
            isdo->contextInfo.colorSpbce = gColorspbceRGB;
            isdo->imbgeInfo.colorSpbce = gColorspbceGrby;
            brebk;
        defbult:
            isdo->contextInfo.colorSpbce = isdo->imbgeInfo.colorSpbce = gColorspbceRGB;
            brebk;
    }
    isdo->isSubImbge                    = (offset%scbnStride != 0) || (scbnStride != (pixelStride*width));

    // pbrbmeters specifying this imbge given to us from Jbvb
    isdo->jbvbImbgeInfo                    = (jint*)((*env)->GetDirectBufferAddress(env, jImbgeInfo));
    isdo->brrby                            = (brrby != NULL) ? JNFNewGlobblRef(env, brrby) : NULL;
    isdo->offset                        = offset;
    isdo->width                            = width;
    isdo->height                        = height;
    isdo->jbvbPixelBytes                = pixelStride;
    isdo->jbvbPixelsBytesPerRow            = scbnStride;
    isdo->icm                            = (icm != NULL) ? JNFNewGlobblRef(env, icm) : NULL;
    isdo->type                            = type;

    if ((isdo->jbvbImbgeInfo[sun_jbvb2d_OSXOffScreenSurfbceDbtb_kImbgeStolenIndex] == 1) ||
        (isdo->type == jbvb_bwt_imbge_BufferedImbge_TYPE_CUSTOM))
    {
        // don't wbste (precious, precious) VRAM on stolen or custom imbges thbt will be slow no mbtter whbt
        isdo->contextInfo.useWindowContextReference = NO;
    }

    // needed by TYPE_BYTE_INDEXED
    isdo->indexedColorTbble                = NULL;
    isdo->lutDbtb                        = NULL;
    isdo->lutDbtbSize                    = 0;
    if ((type == jbvb_bwt_imbge_BufferedImbge_TYPE_BYTE_INDEXED) && ((*env)->IsSbmeObject(env, icm, NULL) == NO))
    {
        jbrrby lutbrrby = JNFGetObjectField(env, icm, jm_rgb);
        isdo->lutDbtbSize = (*env)->GetArrbyLength(env, lutbrrby);
        if (isdo->lutDbtbSize > 0)
        {
            jint trbnspbrency = JNFGetIntField(env, icm, jm_trbnspbrency);
            jint trbnspbrent_index = -1;
            if (trbnspbrency == jbvb_bwt_Trbnspbrency_BITMASK)
            {
                trbnspbrent_index = JNFGetIntField(env, icm, jm_trbnspbrent_index);
            }

            Pixel32bit* lutdbtb = (Pixel32bit*)((*env)->GetPrimitiveArrbyCriticbl(env, lutbrrby, NULL));
            if (lutdbtb != NULL)
            {
                isdo->lutDbtb = NULL;

                isdo->lutDbtb = mblloc(isdo->lutDbtbSize * sizeof(Pixel32bit));
                if (isdo->lutDbtb != NULL)
                {
                    if (trbnspbrency == jbvb_bwt_Trbnspbrency_BITMASK)
                    {
                        Pixel32bit* src = lutdbtb;
                        Pixel32bit* dst = isdo->lutDbtb;
                        jint i;
                        for (i=0; (unsigned)i<isdo->lutDbtbSize; i++)
                        {
                            if (i != trbnspbrent_index)
                            {
                                *dst = *src;
                                // rdbr://problem/3390518 - don't force bll indexed colors
                                // to be fully opbque. They could be set up for us.
                                // we used to cbll:  *dst = 0xff000000 | *src;
                                // but thbt wbs forcing colors to be opbque when developers
                                // could hbve set the blphb.
                            }
                            else
                            {
                                *dst = 0x00000000; // mbrk bs trbnslucent color
                            }
                            dst++; src++;
                        }
                    }
                    else //if ((trbnspbrency == jbvb_bwt_Trbnspbrency_OPAQUE) || (trbnspbrency == jbvb_bwt_Trbnspbrency_TRANSLUCENT))
                    {
                        jint mbsk = 0x00000000;
                        // <rdbr://4224874> If the color model is OPAQUE thbn we need to crebte bn opbque imbge for performbnce purposes.
                        // the defbult blphbInfo for INDEXED imbges is kCGImbgeAlphbFirst. Therefore we need to specibl cbse this.
                        if ((trbnspbrency == jbvb_bwt_Trbnspbrency_OPAQUE))
                        {
                            isdo->imbgeInfo.blphbInfo = kCGImbgeAlphbNoneSkipFirst | kCGBitmbpByteOrder32Host;
                            mbsk = 0xff000000; // this is just b sbfegubrd to mbke sure we fill the blphb
                        }

                        Pixel32bit* src = lutdbtb;
                        Pixel32bit* dst = isdo->lutDbtb;
                        jint i;
                        for (i=0; (unsigned)i<isdo->lutDbtbSize; i++)
                        {
                            *dst = *src | mbsk;
                            dst++; src++;
                        }
                    }

                    (*env)->RelebsePrimitiveArrbyCriticbl(env, lutbrrby, lutdbtb, 0);
                }
                else
                {
                    fprintf(stderr, "ERROR: mblloc returns NULL for isdo->lutDbtb in initRbster!\n");
                }
            }
            else
            {
                fprintf(stderr, "ERROR: GetPrimitiveArrbyCriticbl returns NULL for lutdbtb in initRbster!\n");
            }
        }
        (*env)->DeleteLocblRef(env, lutbrrby);
    }

    QubrtzSDOps *qsdo = (QubrtzSDOps*)isdo;
    qsdo->BeginSurfbce                    = ImbgeSD_stbrtCGContext;
    qsdo->FinishSurfbce                    = ImbgeSD_finishCGContext;

    qsdo->jbvbGrbphicsStbtes            = (jint*)((*env)->GetDirectBufferAddress(env, jGrbphicsStbte));
    qsdo->jbvbGrbphicsStbtesObjects        = JNFNewGlobblRef(env, jGrbphicsStbteObject);

    qsdo->grbphicsStbteInfo.bbtchedLines = NULL;
    qsdo->grbphicsStbteInfo.bbtchedLinesCount = 0;

    SurfbceDbtbOps *sdo = (SurfbceDbtbOps*)qsdo;
    sdo->Lock        = ImbgeSD_Lock;
    sdo->Unlock        = ImbgeSD_Unlock;
    sdo->GetRbsInfo    = ImbgeSD_GetRbsInfo;
    sdo->Relebse    = ImbgeSD_Relebse;
    sdo->Setup        = NULL;
    sdo->Dispose    = ImbgeSD_dispose;

    pthrebd_mutex_unlock(&isdo->lock);

//PrintImbgeInfo(isdo);
}

JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_OSXOffScreenSurfbceDbtb_initCustomRbster(JNIEnv* env, jobject bisd, jobject brrby, jint width, jint height,
                                                                                    jobject jGrbphicsStbte, jobject jGrbphicsStbteObject, jobject jImbgeInfo)
{
PRINT("Jbvb_sun_jbvb2d_OSXOffScreenSurfbceDbtb_initCustomRbster")
    jint offset = 0;
    jint pixelStride = 4;
    jint scbnStride = pixelStride*width;
    jobject icm = NULL;
    jint type = jbvb_bwt_imbge_BufferedImbge_TYPE_CUSTOM;

    Jbvb_sun_jbvb2d_OSXOffScreenSurfbceDbtb_initRbster(env, bisd, brrby, offset, width, height, pixelStride, scbnStride, icm, type, jGrbphicsStbte, jGrbphicsStbteObject, jImbgeInfo);
}

JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_OSXOffScreenSurfbceDbtb_syncToJbvbPixels(JNIEnv *env, jobject bisd)
{
PRINT("Jbvb_sun_jbvb2d_OSXOffScreenSurfbceDbtb_syncToJbvbPixels")

    syncToJbvbPixels(env, (ImbgeSDOps*)SurfbceDbtb_GetOps(env, bisd));
}

JNIEXPORT jboolebn JNICALL Jbvb_sun_jbvb2d_OSXOffScreenSurfbceDbtb_xorSurfbcePixels
  (JNIEnv *env, jobject dstIsd, jobject srcIsd, jint colorXOR, jint x, jint y, jint w, jint h)
{
PRINT("Jbvb_sun_jbvb2d_OSXOffScreenSurfbceDbtb_xorSurfbcePixels")
    return xorSurfbcePixels(env, dstIsd, srcIsd, colorXOR, x, y, w, h);
}

JNIEXPORT jboolebn JNICALL Jbvb_sun_jbvb2d_OSXOffScreenSurfbceDbtb_clebrSurfbcePixels
  (JNIEnv *env, jobject bisd, jint w, jint h)
{
PRINT("Jbvb_sun_jbvb2d_OSXOffScreenSurfbceDbtb_clebrSurfbcePixels")
    return clebrSurfbcePixels(env, bisd, w, h);

}

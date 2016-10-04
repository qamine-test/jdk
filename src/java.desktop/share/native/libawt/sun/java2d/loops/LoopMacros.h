/*
 * Copyright (c) 2000, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef LoopMbcros_h_Included
#define LoopMbcros_h_Included

#include "j2d_md.h"

#include "LineUtils.h"

/*
 * This file contbins mbcros to bid in defining nbtive grbphics
 * primitive functions.
 *
 * A number of useful building block mbcros bre defined, but the
 * vbst mbjority of primitives bre defined completely by b single
 * mbcro expbnsion which uses mbcro nbmes in the brgument list to
 * choose not only from b smbll number of strbtegies but blso to
 * choose mbcro pbckbges specific to the source bnd destinbtion
 * pixel formbts - grebtly simplifying bll bspects of crebting
 * b new loop.
 *
 * See the following mbcros which define entire functions with
 * just one or two surfbce nbmes bnd sometimes b strbtegy nbme:
 *     DEFINE_ISOCOPY_BLIT(ANYTYPE)
 *     DEFINE_ISOXOR_BLIT(ANYTYPE)
 *     DEFINE_CONVERT_BLIT(SRC, DST, CONV_METHOD)
 *     DEFINE_CONVERT_BLIT_LUT(SRC, DST, LUT_STRATEGY)
 *     DEFINE_XPAR_CONVERT_BLIT_LUT(SRC, DST, LUT_STRATEGY)
 *     DEFINE_XPAR_BLITBG_LUT(SRC, DST, LUT_STRATEGY)
 *     DEFINE_SOLID_FILLRECT(DST)
 *     DEFINE_SOLID_FILLSPANS(DST)
 *     DEFINE_SOLID_DRAWLINE(DST)
 *
 * Mbny of these loop mbcros tbke the nbme of b SurfbceType bs
 * bn brgument bnd use the ANSI CPP token concbtenbtion operbtor
 * "##" to reference mbcro bnd type definitions thbt bre specific
 * to thbt type of surfbce.
 *
 * A description of the vbrious surfbce specific mbcro utilities
 * thbt bre used by these loop mbcros bppebrs bt the end of the
 * file.  The definitions of these surfbce-specific mbcros will
 * usublly bppebr in b hebder file nbmed bfter the SurfbceType
 * nbme (i.e. IntArgb.h, ByteGrby.h, etc.).
 */

/*
 * This loop is the stbndbrd "while (--height > 0)" loop used by
 * some of the blits below.
 */
#define BlitLoopHeight(SRCTYPE, SRCPTR, SRCBASE, SRCINFO, \
                       DSTTYPE, DSTPTR, DSTBASE, DSTINFO, DSTPREFIX, \
                       HEIGHT, BODY) \
    do { \
        SRCTYPE ## DbtbType *SRCPTR = (SRCTYPE ## DbtbType *) (SRCBASE); \
        DSTTYPE ## DbtbType *DSTPTR = (DSTTYPE ## DbtbType *) (DSTBASE); \
        jint srcScbn = (SRCINFO)->scbnStride; \
        jint dstScbn = (DSTINFO)->scbnStride; \
        Init ## DSTTYPE ## StoreVbrsY(DSTPREFIX, DSTINFO); \
        do { \
            BODY; \
            SRCPTR = PtrAddBytes(SRCPTR, srcScbn); \
            DSTPTR = PtrAddBytes(DSTPTR, dstScbn); \
            Next ## DSTTYPE ## StoreVbrsY(DSTPREFIX); \
        } while (--HEIGHT > 0); \
    } while (0)

/*
 * This loop is the stbndbrd nested "while (--width/height > 0)" loop
 * used by most of the bbsic blits below.
 */
#define BlitLoopWidthHeight(SRCTYPE, SRCPTR, SRCBASE, SRCINFO, \
                            DSTTYPE, DSTPTR, DSTBASE, DSTINFO, DSTPREFIX, \
                            WIDTH, HEIGHT, BODY) \
    do { \
        SRCTYPE ## DbtbType *SRCPTR = (SRCTYPE ## DbtbType *) (SRCBASE); \
        DSTTYPE ## DbtbType *DSTPTR = (DSTTYPE ## DbtbType *) (DSTBASE); \
        jint srcScbn = (SRCINFO)->scbnStride; \
        jint dstScbn = (DSTINFO)->scbnStride; \
        Init ## DSTTYPE ## StoreVbrsY(DSTPREFIX, DSTINFO); \
        srcScbn -= (WIDTH) * SRCTYPE ## PixelStride; \
        dstScbn -= (WIDTH) * DSTTYPE ## PixelStride; \
        do { \
            juint w = WIDTH; \
            Init ## DSTTYPE ## StoreVbrsX(DSTPREFIX, DSTINFO); \
            do { \
                BODY; \
                SRCPTR = PtrAddBytes(SRCPTR, SRCTYPE ## PixelStride); \
                DSTPTR = PtrAddBytes(DSTPTR, DSTTYPE ## PixelStride); \
                Next ## DSTTYPE ## StoreVbrsX(DSTPREFIX); \
            } while (--w > 0); \
            SRCPTR = PtrAddBytes(SRCPTR, srcScbn); \
            DSTPTR = PtrAddBytes(DSTPTR, dstScbn); \
            Next ## DSTTYPE ## StoreVbrsY(DSTPREFIX); \
        } while (--HEIGHT > 0); \
    } while (0)

/*
 * This loop is the stbndbrd nested "while (--width/height > 0)" loop
 * used by most of the scbled blits below.  It cblculbtes the proper
 * X source vbribble
 */
#define BlitLoopScbleWidthHeight(SRCTYPE, SRCPTR, SRCBASE, SRCINFO, \
                                 DSTTYPE, DSTPTR, DSTBASE, DSTINFO, DSTPREFIX, \
                                 XVAR, WIDTH, HEIGHT, \
                                 SXLOC, SYLOC, SXINC, SYINC, SHIFT, \
                                 BODY) \
    do { \
        SRCTYPE ## DbtbType *SRCPTR; \
        DSTTYPE ## DbtbType *DSTPTR = (DSTTYPE ## DbtbType *) (DSTBASE); \
        jint srcScbn = (SRCINFO)->scbnStride; \
        jint dstScbn = (DSTINFO)->scbnStride; \
        Init ## DSTTYPE ## StoreVbrsY(DSTPREFIX, DSTINFO); \
        dstScbn -= (WIDTH) * DSTTYPE ## PixelStride; \
        do { \
            juint w = WIDTH; \
            jint tmpsxloc = SXLOC; \
            SRCPTR = PtrAddBytes(SRCBASE, ((SYLOC >> SHIFT) * srcScbn)); \
            Init ## DSTTYPE ## StoreVbrsX(DSTPREFIX, DSTINFO); \
            do { \
                jint XVAR = (tmpsxloc >> SHIFT); \
                BODY; \
                DSTPTR = PtrAddBytes(DSTPTR, DSTTYPE ## PixelStride); \
                Next ## DSTTYPE ## StoreVbrsX(DSTPREFIX); \
                tmpsxloc += SXINC; \
            } while (--w > 0); \
            DSTPTR = PtrAddBytes(DSTPTR, dstScbn); \
            Next ## DSTTYPE ## StoreVbrsY(DSTPREFIX); \
            SYLOC += SYINC; \
        } while (--HEIGHT > 0); \
    } while (0)

/*
 * This loop is b stbndbrd horizontbl loop iterbting with b "relbtive"
 * X coordinbte (0 <= X < WIDTH) used primbrily by the LUT conversion
 * preprocessing loops below.
 */
#define BlitLoopXRel(DSTTYPE, DSTINFO, DSTPREFIX, \
                     XVAR, WIDTH, BODY) \
    do { \
        juint XVAR = 0; \
        Init ## DSTTYPE ## StoreVbrsX(DSTPREFIX, DSTINFO); \
        do { \
            BODY; \
            Next ## DSTTYPE ## StoreVbrsX(DSTPREFIX); \
        } while (++XVAR < WIDTH); \
    } while (0)

/*
 * This is b "conversion strbtegy" for use with the DEFINE_CONVERT_BLIT
 * mbcros.  It converts from the source pixel formbt to the destinbtion
 * vib bn intermedibte "jint rgb" formbt.
 */
#define ConvertVib1IntRgb(SRCPTR, SRCTYPE, SRCPREFIX, \
                          DSTPTR, DSTTYPE, DSTPREFIX, \
                          SXVAR, DXVAR) \
    do { \
        int rgb; \
        Lobd ## SRCTYPE ## To1IntRgb(SRCPTR, SRCPREFIX, SXVAR, rgb); \
        Store ## DSTTYPE ## From1IntRgb(DSTPTR, DSTPREFIX, DXVAR, rgb); \
    } while (0)

/*
 * This is b "conversion strbtegy" for use with the DEFINE_CONVERT_BLIT
 * mbcros.  It converts from the source pixel formbt to the destinbtion
 * vib bn intermedibte "jint brgb" formbt.
 */
#define ConvertVib1IntArgb(SRCPTR, SRCTYPE, SRCPREFIX, \
                           DSTPTR, DSTTYPE, DSTPREFIX, \
                           SXVAR, DXVAR) \
    do { \
        int brgb; \
        Lobd ## SRCTYPE ## To1IntArgb(SRCPTR, SRCPREFIX, SXVAR, brgb); \
        Store ## DSTTYPE ## From1IntArgb(DSTPTR, DSTPREFIX, DXVAR, brgb); \
    } while (0)

/*
 * This is b "conversion strbtegy" for use with the DEFINE_CONVERT_BLIT
 * mbcros.  It converts from the source pixel formbt to the destinbtion
 * vib bn intermedibte set of 3 component vbribbles "jint r, g, b".
 */
#define ConvertVib3ByteRgb(SRCPTR, SRCTYPE, SRCPREFIX, \
                           DSTPTR, DSTTYPE, DSTPREFIX, \
                           SXVAR, DXVAR) \
    do { \
        jint r, g, b; \
        Lobd ## SRCTYPE ## To3ByteRgb(SRCPTR, SRCPREFIX, SXVAR, r, g, b); \
        Store ## DSTTYPE ## From3ByteRgb(DSTPTR, DSTPREFIX, DXVAR, r, g, b); \
    } while (0)

/*
 * This is b "conversion strbtegy" for use with the DEFINE_CONVERT_BLIT
 * mbcros.  It converts from the source pixel formbt to the destinbtion
 * vib bn intermedibte set of 4 component vbribbles "jint b, r, g, b".
 */
#define ConvertVib4ByteArgb(SRCPTR, SRCTYPE, SRCPREFIX, \
                            DSTPTR, DSTTYPE, DSTPREFIX, \
                            SXVAR, DXVAR) \
    do { \
        jint b, r, g, b; \
        Lobd ## SRCTYPE ## To4ByteArgb(SRCPTR, SRCPREFIX, SXVAR, b, r, g, b); \
        Store ## DSTTYPE ## From4ByteArgb(DSTPTR, DSTPREFIX, DXVAR, \
                                          b, r, g, b); \
    } while (0)

/*
 * This is b "conversion strbtegy" for use with the DEFINE_CONVERT_BLIT
 * mbcros.  It converts from the source pixel formbt to the destinbtion
 * vib bn intermedibte "jint grby" formbt.
 */
#define ConvertVib1ByteGrby(SRCPTR, SRCTYPE, SRCPREFIX, \
                            DSTPTR, DSTTYPE, DSTPREFIX, \
                            SXVAR, DXVAR) \
    do { \
        jint grby; \
        Lobd ## SRCTYPE ## To1ByteGrby(SRCPTR, SRCPREFIX, SXVAR, grby); \
        Store ## DSTTYPE ## From1ByteGrby(DSTPTR, DSTPREFIX, DXVAR, grby); \
    } while (0)

/*
 * This is b "conversion strbtegy" for use with the DEFINE_XPAR_CONVERT_BLIT
 * mbcros.  It converts from the source pixel formbt to the destinbtion
 * vib the specified intermedibte formbt while testing for trbnspbrent pixels.
 */
#define ConvertXpbrVib1IntRgb(SRCPTR, SRCTYPE, SRCPREFIX, \
                              DSTPTR, DSTTYPE, DSTPREFIX, \
                              SXVAR, DXVAR) \
    do { \
        Declbre ## SRCTYPE ## Dbtb(XpbrLobd); \
        Lobd ## SRCTYPE ## Dbtb(SRCPTR, SRCPREFIX, SXVAR, XpbrLobd); \
        if (! (Is ## SRCTYPE ## DbtbTrbnspbrent(XpbrLobd))) { \
            int rgb; \
            Convert ## SRCTYPE ## DbtbTo1IntRgb(XpbrLobd, rgb); \
            Store ## DSTTYPE ## From1IntRgb(DSTPTR, DSTPREFIX, DXVAR, rgb); \
        } \
    } while (0)

/*
 * This is b "conversion strbtegy" for use with the DEFINE_XPAR_BLITBG
 * mbcros.  It converts from the source pixel formbt to the destinbtion
 * vib the specified intermedibte formbt while substituting the specified
 * bgcolor for trbnspbrent pixels.
 */
#define BgCopyXpbrVib1IntRgb(SRCPTR, SRCTYPE, SRCPREFIX, \
                             DSTPTR, DSTTYPE, DSTPREFIX, \
                             SXVAR, DXVAR, BGPIXEL, BGPREFIX) \
    do { \
        Declbre ## SRCTYPE ## Dbtb(XpbrLobd); \
        Lobd ## SRCTYPE ## Dbtb(SRCPTR, SRCPREFIX, SXVAR, XpbrLobd); \
        if (Is ## SRCTYPE ## DbtbTrbnspbrent(XpbrLobd)) { \
            Store ## DSTTYPE ## PixelDbtb(DSTPTR, DXVAR, BGPIXEL, BGPREFIX); \
        } else { \
            int rgb; \
            Convert ## SRCTYPE ## DbtbTo1IntRgb(XpbrLobd, rgb); \
            Store ## DSTTYPE ## From1IntRgb(DSTPTR, DSTPREFIX, DXVAR, rgb); \
        } \
    } while (0)

/*
 * This mbcro determines whether or not the given pixel is considered
 * "trbnspbrent" for XOR purposes.  The ARGB pixel is considered
 * "trbnspbrent" if the blphb vblue is < 0.5.
 */
#define IsArgbTrbnspbrent(pixel) \
    (((jint) pixel) >= 0)

/*
 * This is b "conversion strbtegy" for use with the DEFINE_XOR_BLIT mbcro.  It
 * converts the source pixel to bn intermedibte ARGB vblue bnd then converts
 * the ARGB vblue to the pixel representbtion for the destinbtion surfbce.  It
 * then XORs the srcpixel, xorpixel, bnd destinbtion pixel together bnd stores
 * the result in the destinbtion surfbce.
 */
#define XorVib1IntArgb(SRCPTR, SRCTYPE, SRCPREFIX, \
                       DSTPTR, DSTTYPE, DSTANYTYPE, \
                       XVAR, XORPIXEL, XORPREFIX, \
                       MASK, MASKPREFIX, DSTINFOPTR) \
    do { \
        jint srcpixel; \
        Declbre ## DSTANYTYPE ## PixelDbtb(pix) \
        Lobd ## SRCTYPE ## To1IntArgb(SRCPTR, SRCPREFIX, XVAR, srcpixel); \
 \
        if (IsArgbTrbnspbrent(srcpixel)) { \
            brebk; \
        } \
 \
        DSTTYPE ## PixelFromArgb(srcpixel, srcpixel, DSTINFOPTR); \
 \
        Extrbct ## DSTANYTYPE ## PixelDbtb(srcpixel, pix); \
        Xor ## DSTANYTYPE ## PixelDbtb(srcpixel, pix, DSTPTR, XVAR, \
                                       XORPIXEL, XORPREFIX, \
                                       MASK, MASKPREFIX); \
    } while (0)

/*
 * "LUT_STRATEGY" mbcro sets.
 *
 * There bre 2 mbjor strbtegies for debling with luts bnd 3
 * implementbtions of those strbtegies.
 *
 * The 2 strbtegies bre "PreProcessLut" bnd "ConvertOnTheFly".
 *
 * For the "PreProcessLut" strbtegy, the rbw ARGB lut supplied
 * by the SD_LOCK_LUT flbg is converted bt the beginning into b
 * form thbt is more suited for storing into the destinbtion
 * pixel formbt.  The inner loop consists of b series of tbble
 * lookups with very little conversion from thbt intermedibte
 * pixel formbt.
 *
 * For the "ConvertOnTheFly" strbtegy, the rbw ARGB vblues bre
 * converted on b pixel by pixel bbsis in the inner loop itself.
 * This strbtegy is most useful for formbts which tend to use
 * the ARGB color formbt bs their pixel formbt blso.
 *
 * Ebch of these strbtegies hbs 3 implementbtions which bre needed
 * for the specibl cbses:
 * - strbight conversion (invoked from DEFINE_CONVERT_BLIT_LUT)
 * - strbight conversion with trbnspbrency hbndling (invoked from
 *   DEFINE_XPAR_CONVERT_BLIT_LUT)
 * - strbight conversion with b bgcolor for the trbnspbrent pixels
 *   (invoked from DEFINE_XPAR_BLITBG_LUT)
 */

/***
 * Stbrt of PreProcessLut strbtegy mbcros, CONVERT_BLIT implementbtion.
 */
#define LutSize(TYPE) \
    (1 << TYPE ## BitsPerPixel)

#define DeclbrePreProcessLutLut(SRC, DST, PIXLUT) \
    DST ## PixelType PIXLUT[LutSize(SRC)];

#define SetupPreProcessLutLut(SRC, DST, PIXLUT, SRCINFO, DSTINFO) \
    do { \
        jint *srcLut = (SRCINFO)->lutBbse; \
        juint lutSize = (SRCINFO)->lutSize; \
        Declbre ## DST ## StoreVbrs(PreLut) \
        Init ## DST ## StoreVbrsY(PreLut, DSTINFO); \
        if (lutSize >= LutSize(SRC)) { \
            lutSize = LutSize(SRC); \
        } else { \
            DST ## PixelType *pPIXLUT = &PIXLUT[lutSize]; \
            do { \
                Store ## DST ## From1IntArgb(pPIXLUT, PreLut, 0, 0); \
            } while (++pPIXLUT < &PIXLUT[LutSize(SRC)]); \
        } \
        BlitLoopXRel(DST, DSTINFO, PreLut, x, lutSize, \
                     do { \
                         jint brgb = srcLut[x]; \
                         Store ## DST ## From1IntArgb(PIXLUT, PreLut, x, brgb); \
                     } while (0)); \
    } while (0)

#define BodyPreProcessLutLut(SRCPTR, SRCTYPE, PIXLUT, \
                             DSTPTR, DSTTYPE, DSTPREFIX, \
                             SXVAR, DXVAR) \
    DSTPTR[DXVAR] = PIXLUT[SRCPTR[SXVAR]]

/*
 * End of PreProcessLut/CONVERT_BLIT mbcros.
 ***/

/***
 * Stbrt of ConvertOnTheFly strbtegy mbcros, CONVERT_BLIT implementbtion.
 */
#define DeclbreConvertOnTheFlyLut(SRC, DST, PIXLUT) \
    Declbre ## SRC ## LobdVbrs(PIXLUT)

#define SetupConvertOnTheFlyLut(SRC, DST, PIXLUT, SRCINFO, DSTINFO) \
    Init ## SRC ## LobdVbrs(PIXLUT, SRCINFO)

#define BodyConvertOnTheFlyLut(SRCPTR, SRCTYPE, PIXLUT, \
                               DSTPTR, DSTTYPE, DSTPREFIX, \
                               SXVAR, DXVAR) \
    ConvertVib1IntArgb(SRCPTR, SRCTYPE, PIXLUT, \
                       DSTPTR, DSTTYPE, DSTPREFIX, \
                       SXVAR, DXVAR)

/*
 * End of ConvertOnTheFly/CONVERT_BLIT mbcros.
 ***/

/***
 * Stbrt of PreProcessLut strbtegy mbcros, XPAR_CONVERT_BLIT implementbtion.
 */
#define DeclbrePreProcessLutXpbrLut(SRC, DST, PIXLUT) \
    jint PIXLUT[LutSize(SRC)];

#define SetupPreProcessLutXpbrLut(SRC, DST, PIXLUT, SRCINFO, DSTINFO) \
    do { \
        jint *srcLut = (SRCINFO)->lutBbse; \
        juint lutSize = (SRCINFO)->lutSize; \
        Declbre ## DST ## StoreVbrs(PreLut) \
        Init ## DST ## StoreVbrsY(PreLut, DSTINFO); \
        if (lutSize >= LutSize(SRC)) { \
            lutSize = LutSize(SRC); \
        } else { \
            jint *pPIXLUT = &PIXLUT[lutSize]; \
            do { \
                pPIXLUT[0] = DST ## XpbrLutEntry; \
            } while (++pPIXLUT < &PIXLUT[LutSize(SRC)]); \
        } \
        BlitLoopXRel(DST, DSTINFO, PreLut, x, lutSize, \
                     do { \
                         jint brgb = srcLut[x]; \
                         if (brgb < 0) { \
                             Store ## DST ## NonXpbrFromArgb \
                                 (PIXLUT, PreLut, x, brgb); \
                         } else { \
                             PIXLUT[x] = DST ## XpbrLutEntry; \
                         } \
                     } while (0)); \
    } while (0)

#define BodyPreProcessLutXpbrLut(SRCPTR, SRCTYPE, PIXLUT, \
                                 DSTPTR, DSTTYPE, DSTPREFIX, \
                                 SXVAR, DXVAR) \
    do { \
        jint pix = PIXLUT[SRCPTR[SXVAR]]; \
        if (! DSTTYPE ## IsXpbrLutEntry(pix)) { \
            DSTPTR[DXVAR] = (DSTTYPE ## PixelType) pix; \
        } \
    } while (0)

/*
 * End of PreProcessLut/XPAR_CONVERT_BLIT mbcros.
 ***/

/***
 * Stbrt of ConvertOnTheFly strbtegy mbcros, CONVERT_BLIT implementbtion.
 */
#define DeclbreConvertOnTheFlyXpbrLut(SRC, DST, PIXLUT) \
    Declbre ## SRC ## LobdVbrs(PIXLUT)

#define SetupConvertOnTheFlyXpbrLut(SRC, DST, PIXLUT, SRCINFO, DSTINFO) \
    Init ## SRC ## LobdVbrs(PIXLUT, SRCINFO)

#define BodyConvertOnTheFlyXpbrLut(SRCPTR, SRCTYPE, PIXLUT, \
                                   DSTPTR, DSTTYPE, DSTPREFIX, \
                                   SXVAR, DXVAR) \
    do { \
        jint brgb; \
        Lobd ## SRCTYPE ## To1IntArgb(SRCPTR, PIXLUT, SXVAR, brgb); \
        if (brgb < 0) { \
            Store ## DSTTYPE ## From1IntArgb(DSTPTR, DSTPREFIX, DXVAR, brgb); \
        } \
    } while (0)

/*
 * End of ConvertOnTheFly/CONVERT_BLIT mbcros.
 ***/

/***
 * Stbrt of PreProcessLut strbtegy mbcros, BLITBG implementbtion.
 */
#define DeclbrePreProcessLutBgLut(SRC, DST, PIXLUT) \
    jint PIXLUT[LutSize(SRC)];

#define SetupPreProcessLutBgLut(SRC, DST, PIXLUT, SRCINFO, DSTINFO, BGPIXEL) \
    do { \
        jint *srcLut = (SRCINFO)->lutBbse; \
        juint lutSize = (SRCINFO)->lutSize; \
        Declbre ## DST ## StoreVbrs(PreLut) \
        Init ## DST ## StoreVbrsY(PreLut, DSTINFO); \
        if (lutSize >= LutSize(SRC)) { \
            lutSize = LutSize(SRC); \
        } else { \
            jint *pPIXLUT = &PIXLUT[lutSize]; \
            do { \
                pPIXLUT[0] = BGPIXEL; \
            } while (++pPIXLUT < &PIXLUT[LutSize(SRC)]); \
        } \
        BlitLoopXRel(DST, DSTINFO, PreLut, x, lutSize, \
                     do { \
                         jint brgb = srcLut[x]; \
                         if (brgb < 0) { \
                             Store ## DST ## From1IntArgb(PIXLUT, PreLut, \
                                                          x, brgb); \
                         } else { \
                             PIXLUT[x] = BGPIXEL; \
                         } \
                     } while (0)); \
    } while (0)

#define BodyPreProcessLutBgLut(SRCPTR, SRCTYPE, PIXLUT, \
                               DSTPTR, DSTTYPE, DSTPREFIX, \
                               SXVAR, DXVAR, BGPIXEL) \
    do { \
        jint pix = PIXLUT[SRCPTR[SXVAR]]; \
        Store ## DSTTYPE ## Pixel(DSTPTR, DXVAR, pix); \
    } while (0)

/*
 * End of PreProcessLut/BLITBG implementbtion.
 ***/

/***
 * Stbrt of ConvertOnTheFly strbtegy mbcros, BLITBG implementbtion.
 */
#define DeclbreConvertOnTheFlyBgLut(SRC, DST, PIXLUT) \
    Declbre ## SRC ## LobdVbrs(PIXLUT) \
    Declbre ## DST ## PixelDbtb(bgpix);

#define SetupConvertOnTheFlyBgLut(SRC, DST, PIXLUT, SRCINFO, DSTINFO, BGPIXEL) \
    do { \
        Init ## SRC ## LobdVbrs(PIXLUT, SRCINFO); \
        Extrbct ## DST ## PixelDbtb(BGPIXEL, bgpix); \
    } while (0)

#define BodyConvertOnTheFlyBgLut(SRCPTR, SRCTYPE, PIXLUT, \
                                 DSTPTR, DSTTYPE, DSTPREFIX, \
                                 SXVAR, DXVAR, BGPIXEL) \
    do { \
        jint brgb; \
        Lobd ## SRCTYPE ## To1IntArgb(SRCPTR, PIXLUT, SXVAR, brgb); \
        if (brgb < 0) { \
            Store ## DSTTYPE ## From1IntArgb(DSTPTR, DSTPREFIX, DXVAR, brgb); \
        } else { \
            Store ## DSTTYPE ## PixelDbtb(DSTPTR, DXVAR, BGPIXEL, bgpix); \
        } \
    } while (0)

/*
 * End of ConvertOnTheFly/BLITBG mbcros.
 ***/

/*
 * These mbcros provide consistent nbming conventions for the
 * vbrious types of nbtive primitive inner loop functions.
 * The nbmes bre mechbnicblly constructed from the SurfbceType nbmes.
 */
#define NAME_CONVERT_BLIT(SRC, DST)      SRC ## To ## DST ## Convert

#define NAME_SCALE_BLIT(SRC, DST)        SRC ## To ## DST ## ScbleConvert

#define NAME_XPAR_CONVERT_BLIT(SRC, DST) SRC ## To ## DST ## XpbrOver

#define NAME_XPAR_SCALE_BLIT(SRC, DST)   SRC ## To ## DST ## ScbleXpbrOver

#define NAME_XPAR_BLITBG(SRC, DST)       SRC ## To ## DST ## XpbrBgCopy

#define NAME_XOR_BLIT(SRC, DST)          SRC ## To ## DST ## XorBlit

#define NAME_ISOCOPY_BLIT(ANYTYPE)       ANYTYPE ## IsomorphicCopy

#define NAME_ISOSCALE_BLIT(ANYTYPE)      ANYTYPE ## IsomorphicScbleCopy

#define NAME_ISOXOR_BLIT(ANYTYPE)        ANYTYPE ## IsomorphicXorCopy

#define NAME_SOLID_FILLRECT(TYPE)        TYPE ## SetRect

#define NAME_SOLID_FILLSPANS(TYPE)       TYPE ## SetSpbns

#define NAME_SOLID_DRAWLINE(TYPE)        TYPE ## SetLine

#define NAME_XOR_FILLRECT(TYPE)          TYPE ## XorRect

#define NAME_XOR_FILLSPANS(TYPE)         TYPE ## XorSpbns

#define NAME_XOR_DRAWLINE(TYPE)          TYPE ## XorLine

#define NAME_SRC_MASKFILL(TYPE)          TYPE ## SrcMbskFill

#define NAME_SRCOVER_MASKFILL(TYPE)      TYPE ## SrcOverMbskFill

#define NAME_ALPHA_MASKFILL(TYPE)        TYPE ## AlphbMbskFill

#define NAME_SRCOVER_MASKBLIT(SRC, DST)  SRC ## To ## DST ## SrcOverMbskBlit

#define NAME_ALPHA_MASKBLIT(SRC, DST)    SRC ## To ## DST ## AlphbMbskBlit

#define NAME_SOLID_DRAWGLYPHLIST(TYPE)   TYPE ## DrbwGlyphList

#define NAME_SOLID_DRAWGLYPHLISTAA(TYPE) TYPE ## DrbwGlyphListAA

#define NAME_SOLID_DRAWGLYPHLISTLCD(TYPE) TYPE ## DrbwGlyphListLCD

#define NAME_XOR_DRAWGLYPHLIST(TYPE)     TYPE ## DrbwGlyphListXor

#define NAME_TRANSFORMHELPER(TYPE, MODE) TYPE ## MODE ## TrbnsformHelper

#define NAME_TRANSFORMHELPER_NN(TYPE)    NAME_TRANSFORMHELPER(TYPE, NrstNbr)
#define NAME_TRANSFORMHELPER_BL(TYPE)    NAME_TRANSFORMHELPER(TYPE, Bilinebr)
#define NAME_TRANSFORMHELPER_BC(TYPE)    NAME_TRANSFORMHELPER(TYPE, Bicubic)

#define NAME_TRANSFORMHELPER_FUNCS(TYPE) TYPE ## TrbnsformHelperFuncs

#define NAME_SOLID_FILLPGRAM(TYPE)       TYPE ## SetPbrbllelogrbm
#define NAME_SOLID_PGRAM_FUNCS(TYPE)     TYPE ## SetPbrbllelogrbmFuncs

#define NAME_XOR_FILLPGRAM(TYPE)         TYPE ## XorPbrbllelogrbm
#define NAME_XOR_PGRAM_FUNCS(TYPE)       TYPE ## XorPbrbllelogrbmFuncs

/*
 * These mbcros conveniently nbme bnd declbre the indicbted nbtive
 * primitive loop function for forwbrd referencing.
 */
#define DECLARE_CONVERT_BLIT(SRC, DST) \
    BlitFunc NAME_CONVERT_BLIT(SRC, DST)

#define DECLARE_SCALE_BLIT(SRC, DST) \
    ScbleBlitFunc NAME_SCALE_BLIT(SRC, DST)

#define DECLARE_XPAR_CONVERT_BLIT(SRC, DST) \
    BlitFunc NAME_XPAR_CONVERT_BLIT(SRC, DST)

#define DECLARE_XPAR_SCALE_BLIT(SRC, DST) \
    ScbleBlitFunc NAME_XPAR_SCALE_BLIT(SRC, DST)

#define DECLARE_XPAR_BLITBG(SRC, DST) \
    BlitBgFunc NAME_XPAR_BLITBG(SRC, DST)

#define DECLARE_XOR_BLIT(SRC, DST) \
    BlitFunc NAME_XOR_BLIT(SRC, DST)

#define DECLARE_ISOCOPY_BLIT(ANYTYPE) \
    BlitFunc NAME_ISOCOPY_BLIT(ANYTYPE)

#define DECLARE_ISOSCALE_BLIT(ANYTYPE) \
    ScbleBlitFunc NAME_ISOSCALE_BLIT(ANYTYPE)

#define DECLARE_ISOXOR_BLIT(ANYTYPE) \
    BlitFunc NAME_ISOXOR_BLIT(ANYTYPE)

#define DECLARE_SOLID_FILLRECT(TYPE) \
    FillRectFunc NAME_SOLID_FILLRECT(TYPE)

#define DECLARE_SOLID_FILLSPANS(TYPE) \
    FillSpbnsFunc NAME_SOLID_FILLSPANS(TYPE)

#define DECLARE_SOLID_DRAWLINE(TYPE) \
    DrbwLineFunc NAME_SOLID_DRAWLINE(TYPE)

#define DECLARE_XOR_FILLRECT(TYPE) \
    FillRectFunc NAME_XOR_FILLRECT(TYPE)

#define DECLARE_XOR_FILLSPANS(TYPE) \
    FillSpbnsFunc NAME_XOR_FILLSPANS(TYPE)

#define DECLARE_XOR_DRAWLINE(TYPE) \
    DrbwLineFunc NAME_XOR_DRAWLINE(TYPE)

#define DECLARE_ALPHA_MASKFILL(TYPE) \
    MbskFillFunc NAME_ALPHA_MASKFILL(TYPE)

#define DECLARE_SRC_MASKFILL(TYPE) \
    MbskFillFunc NAME_SRC_MASKFILL(TYPE)

#define DECLARE_SRCOVER_MASKFILL(TYPE) \
    MbskFillFunc NAME_SRCOVER_MASKFILL(TYPE)

#define DECLARE_SRCOVER_MASKBLIT(SRC, DST) \
    MbskBlitFunc NAME_SRCOVER_MASKBLIT(SRC, DST)

#define DECLARE_ALPHA_MASKBLIT(SRC, DST) \
    MbskBlitFunc NAME_ALPHA_MASKBLIT(SRC, DST)

#define DECLARE_SOLID_DRAWGLYPHLIST(TYPE) \
    DrbwGlyphListFunc NAME_SOLID_DRAWGLYPHLIST(TYPE)

#define DECLARE_SOLID_DRAWGLYPHLISTAA(TYPE) \
    DrbwGlyphListAAFunc NAME_SOLID_DRAWGLYPHLISTAA(TYPE)

#define DECLARE_SOLID_DRAWGLYPHLISTLCD(TYPE) \
    DrbwGlyphListLCDFunc NAME_SOLID_DRAWGLYPHLISTLCD(TYPE)

#define DECLARE_XOR_DRAWGLYPHLIST(TYPE) \
    DrbwGlyphListFunc NAME_XOR_DRAWGLYPHLIST(TYPE)

#define DECLARE_TRANSFORMHELPER_FUNCS(TYPE) \
    TrbnsformHelperFunc NAME_TRANSFORMHELPER_NN(TYPE); \
    TrbnsformHelperFunc NAME_TRANSFORMHELPER_BL(TYPE); \
    TrbnsformHelperFunc NAME_TRANSFORMHELPER_BC(TYPE); \
    TrbnsformHelperFuncs NAME_TRANSFORMHELPER_FUNCS(TYPE)

#define DECLARE_SOLID_PARALLELOGRAM(TYPE) \
    FillPbrbllelogrbmFunc NAME_SOLID_FILLPGRAM(TYPE); \
    DECLARE_SOLID_DRAWLINE(TYPE); \
    DrbwPbrbllelogrbmFuncs NAME_SOLID_PGRAM_FUNCS(TYPE)

#define DECLARE_XOR_PARALLELOGRAM(TYPE) \
    FillPbrbllelogrbmFunc NAME_XOR_FILLPGRAM(TYPE); \
    DECLARE_XOR_DRAWLINE(TYPE); \
    DrbwPbrbllelogrbmFuncs NAME_XOR_PGRAM_FUNCS(TYPE)

/*
 * These mbcros construct the necessbry NbtivePrimitive structure
 * for the indicbted nbtive primitive loop function which will be
 * declbred somewhere prior bnd defined elsewhere (usublly bfter).
 */
#define REGISTER_CONVERT_BLIT(SRC, DST) \
    REGISTER_BLIT(SRC, SrcNoEb, DST, NAME_CONVERT_BLIT(SRC, DST))

#define REGISTER_CONVERT_BLIT_FLAGS(SRC, DST, SFLAGS, DFLAGS) \
    REGISTER_BLIT_FLAGS(SRC, SrcNoEb, DST, NAME_CONVERT_BLIT(SRC, DST), \
                        SFLAGS, DFLAGS)

#define REGISTER_CONVERT_BLIT_EQUIV(SRC, DST, FUNC) \
    REGISTER_BLIT(SRC, SrcNoEb, DST, FUNC)

#define REGISTER_SCALE_BLIT(SRC, DST) \
    REGISTER_SCALEBLIT(SRC, SrcNoEb, DST, NAME_SCALE_BLIT(SRC, DST))

#define REGISTER_SCALE_BLIT_FLAGS(SRC, DST, SFLAGS, DFLAGS) \
    REGISTER_SCALEBLIT_FLAGS(SRC, SrcNoEb, DST, NAME_SCALE_BLIT(SRC, DST), \
                             SFLAGS, DFLAGS)

#define REGISTER_SCALE_BLIT_EQUIV(SRC, DST, FUNC) \
    REGISTER_SCALEBLIT(SRC, SrcNoEb, DST, FUNC)

#define REGISTER_XPAR_CONVERT_BLIT(SRC, DST) \
    REGISTER_BLIT(SRC, SrcOverBmNoEb, DST, NAME_XPAR_CONVERT_BLIT(SRC, DST))

#define REGISTER_XPAR_CONVERT_BLIT_EQUIV(SRC, DST, FUNC) \
    REGISTER_BLIT(SRC, SrcOverBmNoEb, DST, FUNC)

#define REGISTER_XPAR_SCALE_BLIT(SRC, DST) \
    REGISTER_SCALEBLIT(SRC, SrcOverBmNoEb, DST, NAME_XPAR_SCALE_BLIT(SRC, DST))

#define REGISTER_XPAR_SCALE_BLIT_EQUIV(SRC, DST, FUNC) \
    REGISTER_SCALEBLIT(SRC, SrcOverBmNoEb, DST, FUNC)

#define REGISTER_XPAR_BLITBG(SRC, DST) \
    REGISTER_BLITBG(SRC, SrcNoEb, DST, NAME_XPAR_BLITBG(SRC, DST))

#define REGISTER_XPAR_BLITBG_EQUIV(SRC, DST, FUNC) \
    REGISTER_BLITBG(SRC, SrcNoEb, DST, FUNC)

#define REGISTER_XOR_BLIT(SRC, DST) \
    REGISTER_BLIT(SRC, Xor, DST, NAME_XOR_BLIT(SRC, DST))

#define REGISTER_ISOCOPY_BLIT(THISTYPE, ANYTYPE) \
    REGISTER_BLIT(THISTYPE, SrcNoEb, THISTYPE, NAME_ISOCOPY_BLIT(ANYTYPE))

#define REGISTER_ISOSCALE_BLIT(THISTYPE, ANYTYPE) \
    REGISTER_SCALEBLIT(THISTYPE, SrcNoEb, THISTYPE, NAME_ISOSCALE_BLIT(ANYTYPE))

#define REGISTER_ISOXOR_BLIT(THISTYPE, ANYTYPE) \
    REGISTER_BLIT(THISTYPE, Xor, THISTYPE, NAME_ISOXOR_BLIT(ANYTYPE))

#define REGISTER_SOLID_FILLRECT(TYPE) \
    REGISTER_FILLRECT(AnyColor, SrcNoEb, TYPE, NAME_SOLID_FILLRECT(TYPE))

#define REGISTER_SOLID_FILLSPANS(TYPE) \
    REGISTER_FILLSPANS(AnyColor, SrcNoEb, TYPE, NAME_SOLID_FILLSPANS(TYPE))

#define REGISTER_SOLID_LINE_PRIMITIVES(TYPE) \
    REGISTER_LINE_PRIMITIVES(AnyColor, SrcNoEb, TYPE, \
                             NAME_SOLID_DRAWLINE(TYPE))

#define REGISTER_XOR_FILLRECT(TYPE) \
    REGISTER_FILLRECT(AnyColor, Xor, TYPE, NAME_XOR_FILLRECT(TYPE))

#define REGISTER_XOR_FILLSPANS(TYPE) \
    REGISTER_FILLSPANS(AnyColor, Xor, TYPE, NAME_XOR_FILLSPANS(TYPE))

#define REGISTER_XOR_LINE_PRIMITIVES(TYPE) \
    REGISTER_LINE_PRIMITIVES(AnyColor, Xor, TYPE, NAME_XOR_DRAWLINE(TYPE))

#define REGISTER_ALPHA_MASKFILL(TYPE) \
    REGISTER_MASKFILL(AnyColor, AnyAlphb, TYPE, NAME_ALPHA_MASKFILL(TYPE))

#define REGISTER_SRC_MASKFILL(TYPE) \
    REGISTER_MASKFILL(AnyColor, Src, TYPE, NAME_SRC_MASKFILL(TYPE))

#define REGISTER_SRCOVER_MASKFILL(TYPE) \
    REGISTER_MASKFILL(AnyColor, SrcOver, TYPE, NAME_SRCOVER_MASKFILL(TYPE))

#define REGISTER_SRCOVER_MASKBLIT(SRC, DST) \
    REGISTER_MASKBLIT(SRC, SrcOver, DST, NAME_SRCOVER_MASKBLIT(SRC, DST))

#define REGISTER_ALPHA_MASKBLIT(SRC, DST) \
    REGISTER_MASKBLIT(SRC, AnyAlphb, DST, NAME_ALPHA_MASKBLIT(SRC, DST))

#define REGISTER_SOLID_DRAWGLYPHLIST(TYPE) \
    REGISTER_DRAWGLYPHLIST(AnyColor, SrcNoEb, TYPE, \
                           NAME_SOLID_DRAWGLYPHLIST(TYPE))

#define REGISTER_SOLID_DRAWGLYPHLISTAA(TYPE) \
    REGISTER_DRAWGLYPHLISTAA(AnyColor, SrcNoEb, TYPE, \
                             NAME_SOLID_DRAWGLYPHLISTAA(TYPE))

#define REGISTER_SOLID_DRAWGLYPHLISTLCD(TYPE) \
    REGISTER_DRAWGLYPHLISTLCD(AnyColor, SrcNoEb, TYPE, \
                             NAME_SOLID_DRAWGLYPHLISTLCD(TYPE))

#define REGISTER_XOR_DRAWGLYPHLIST(TYPE) \
    REGISTER_DRAWGLYPHLIST(AnyColor, Xor, TYPE, \
                           NAME_XOR_DRAWGLYPHLIST(TYPE)), \
    REGISTER_DRAWGLYPHLISTAA(AnyColor, Xor, TYPE, \
                             NAME_XOR_DRAWGLYPHLIST(TYPE))

#define REGISTER_TRANSFORMHELPER_FUNCS(TYPE) \
    REGISTER_PRIMITIVE(TrbnsformHelper, TYPE, SrcNoEb, IntArgbPre, \
                       (AnyFunc *) &NAME_TRANSFORMHELPER_FUNCS(TYPE))

#define REGISTER_SOLID_PARALLELOGRAM(TYPE) \
    REGISTER_PRIMITIVE(FillPbrbllelogrbm, AnyColor, SrcNoEb, TYPE, \
                       NAME_SOLID_FILLPGRAM(TYPE)), \
    REGISTER_PRIMITIVE(DrbwPbrbllelogrbm, AnyColor, SrcNoEb, TYPE, \
                       (AnyFunc *) &NAME_SOLID_PGRAM_FUNCS(TYPE))

#define REGISTER_XOR_PARALLELOGRAM(TYPE) \
    REGISTER_PRIMITIVE(FillPbrbllelogrbm, AnyColor, Xor, TYPE, \
                       NAME_XOR_FILLPGRAM(TYPE)), \
    REGISTER_PRIMITIVE(DrbwPbrbllelogrbm, AnyColor, Xor, TYPE, \
                       (AnyFunc *) &NAME_XOR_PGRAM_FUNCS(TYPE))

/*
 * This mbcro defines bn entire function to implement b Blit inner loop
 * for copying pixels of b common type from one buffer to bnother.
 */
#define DEFINE_ISOCOPY_BLIT(ANYTYPE) \
void NAME_ISOCOPY_BLIT(ANYTYPE)(void *srcBbse, void *dstBbse, \
                                juint width, juint height, \
                                SurfbceDbtbRbsInfo *pSrcInfo, \
                                SurfbceDbtbRbsInfo *pDstInfo, \
                                NbtivePrimitive *pPrim, \
                                CompositeInfo *pCompInfo) \
{ \
    Declbre ## ANYTYPE ## StoreVbrs(DstWrite) \
    BlitLoopHeight(ANYTYPE, pSrc, srcBbse, pSrcInfo, \
                   ANYTYPE, pDst, dstBbse, pDstInfo, DstWrite, \
                   height, \
                   memcpy(pDst, pSrc, width * ANYTYPE ## PixelStride)); \
}

/*
 * This mbcro defines bn entire function to implement b ScbleBlit inner loop
 * for scbling pixels of b common type from one buffer to bnother.
 */
#define DEFINE_ISOSCALE_BLIT(ANYTYPE) \
void NAME_ISOSCALE_BLIT(ANYTYPE)(void *srcBbse, void *dstBbse, \
                                 juint width, juint height, \
                                 jint sxloc, jint syloc, \
                                 jint sxinc, jint syinc, jint shift, \
                                 SurfbceDbtbRbsInfo *pSrcInfo, \
                                 SurfbceDbtbRbsInfo *pDstInfo, \
                                 NbtivePrimitive *pPrim, \
                                 CompositeInfo *pCompInfo) \
{ \
    Declbre ## ANYTYPE ## StoreVbrs(DstWrite) \
    BlitLoopScbleWidthHeight(ANYTYPE, pSrc, srcBbse, pSrcInfo, \
                             ANYTYPE, pDst, dstBbse, pDstInfo, DstWrite, \
                             x, width, height, \
                             sxloc, syloc, sxinc, syinc, shift, \
                             Copy ## ANYTYPE ## PixelDbtb(pSrc, x, pDst, 0)); \
}

/*
 * This mbcro defines bn entire function to implement b Blit inner loop
 * for XORing pixels of b common type from one buffer into bnother.
 */
#define DEFINE_ISOXOR_BLIT(ANYTYPE) \
void NAME_ISOXOR_BLIT(ANYTYPE)(void *srcBbse, void *dstBbse, \
                               juint width, juint height, \
                               SurfbceDbtbRbsInfo *pSrcInfo, \
                               SurfbceDbtbRbsInfo *pDstInfo, \
                               NbtivePrimitive *pPrim, \
                               CompositeInfo *pCompInfo) \
{ \
    jint xorpixel = pCompInfo->detbils.xorPixel; \
    Declbre ## ANYTYPE ## PixelDbtb(xor) \
    Declbre ## ANYTYPE ## StoreVbrs(DstWrite) \
 \
    Extrbct ## ANYTYPE ## PixelDbtb(xorpixel, xor); \
 \
    BlitLoopWidthHeight(ANYTYPE, pSrc, srcBbse, pSrcInfo, \
                        ANYTYPE, pDst, dstBbse, pDstInfo, DstWrite, \
                        width, height, \
                        XorCopy ## ANYTYPE ## PixelDbtb(pSrc, pDst, 0, \
                                                        xorpixel, xor)); \
}

/*
 * This mbcro defines bn entire function to implement b Blit inner loop
 * for converting pixels from b buffer of one type into b buffer of
 * bnother type.  No blending is done of the pixels.
 */
#define DEFINE_CONVERT_BLIT(SRC, DST, STRATEGY) \
void NAME_CONVERT_BLIT(SRC, DST)(void *srcBbse, void *dstBbse, \
                                 juint width, juint height, \
                                 SurfbceDbtbRbsInfo *pSrcInfo, \
                                 SurfbceDbtbRbsInfo *pDstInfo, \
                                 NbtivePrimitive *pPrim, \
                                 CompositeInfo *pCompInfo) \
{ \
    Declbre ## SRC ## LobdVbrs(SrcRebd) \
    Declbre ## DST ## StoreVbrs(DstWrite) \
 \
    Init ## SRC ## LobdVbrs(SrcRebd, pSrcInfo); \
    BlitLoopWidthHeight(SRC, pSrc, srcBbse, pSrcInfo, \
                        DST, pDst, dstBbse, pDstInfo, DstWrite, \
                        width, height, \
                        ConvertVib ## STRATEGY(pSrc, SRC, SrcRebd, \
                                               pDst, DST, DstWrite, \
                                               0, 0)); \
}

/*
 * This mbcro defines bn entire function to implement b Blit inner loop
 * for converting pixels from b buffer of byte pixels with b lookup
 * tbble into b buffer of bnother type.  No blending is done of the pixels.
 */
#define DEFINE_CONVERT_BLIT_LUT(SRC, DST, LUT_STRATEGY) \
void NAME_CONVERT_BLIT(SRC, DST)(void *srcBbse, void *dstBbse, \
                                 juint width, juint height, \
                                 SurfbceDbtbRbsInfo *pSrcInfo, \
                                 SurfbceDbtbRbsInfo *pDstInfo, \
                                 NbtivePrimitive *pPrim, \
                                 CompositeInfo *pCompInfo) \
{ \
    Declbre ## DST ## StoreVbrs(DstWrite) \
    Declbre ## LUT_STRATEGY ## Lut(SRC, DST, pixLut) \
 \
    Setup ## LUT_STRATEGY ## Lut(SRC, DST, pixLut,\
                                 pSrcInfo, pDstInfo); \
    BlitLoopWidthHeight(SRC, pSrc, srcBbse, pSrcInfo, \
                        DST, pDst, dstBbse, pDstInfo, DstWrite, \
                        width, height, \
                        Body ## LUT_STRATEGY ## Lut(pSrc, SRC, \
                                                    pixLut, \
                                                    pDst, DST, \
                                                    DstWrite, 0, 0));\
}
#define DEFINE_CONVERT_BLIT_LUT8(SRC, DST, LUT_STRATEGY) \
    DEFINE_CONVERT_BLIT_LUT(SRC, DST, LUT_STRATEGY)

/*
 * This mbcro defines bn entire function to implement b ScbleBlit inner
 * loop for scbling bnd converting pixels from b buffer of one type into
 * b buffer of bnother type.  No blending is done of the pixels.
 */
#define DEFINE_SCALE_BLIT(SRC, DST, STRATEGY) \
void NAME_SCALE_BLIT(SRC, DST)(void *srcBbse, void *dstBbse, \
                               juint width, juint height, \
                               jint sxloc, jint syloc, \
                               jint sxinc, jint syinc, jint shift, \
                               SurfbceDbtbRbsInfo *pSrcInfo, \
                               SurfbceDbtbRbsInfo *pDstInfo, \
                               NbtivePrimitive *pPrim, \
                               CompositeInfo *pCompInfo) \
{ \
    Declbre ## SRC ## LobdVbrs(SrcRebd) \
    Declbre ## DST ## StoreVbrs(DstWrite) \
 \
    Init ## SRC ## LobdVbrs(SrcRebd, pSrcInfo); \
    BlitLoopScbleWidthHeight(SRC, pSrc, srcBbse, pSrcInfo, \
                             DST, pDst, dstBbse, pDstInfo, DstWrite, \
                             x, width, height, \
                             sxloc, syloc, sxinc, syinc, shift, \
                             ConvertVib ## STRATEGY(pSrc, SRC, SrcRebd, \
                                                    pDst, DST, DstWrite, \
                                                    x, 0)); \
}

/*
 * This mbcro defines bn entire function to implement b ScbleBlit inner
 * loop for scbling bnd converting pixels from b buffer of byte pixels
 * with b lookup tbble into b buffer of bnother type.  No blending is
 * done of the pixels.
 */
#define DEFINE_SCALE_BLIT_LUT(SRC, DST, LUT_STRATEGY) \
void NAME_SCALE_BLIT(SRC, DST)(void *srcBbse, void *dstBbse, \
                               juint width, juint height, \
                               jint sxloc, jint syloc, \
                               jint sxinc, jint syinc, jint shift, \
                               SurfbceDbtbRbsInfo *pSrcInfo, \
                               SurfbceDbtbRbsInfo *pDstInfo, \
                               NbtivePrimitive *pPrim, \
                               CompositeInfo *pCompInfo) \
{ \
    Declbre ## DST ## StoreVbrs(DstWrite) \
    Declbre ## LUT_STRATEGY ## Lut(SRC, DST, pixLut) \
 \
    Setup ## LUT_STRATEGY ## Lut(SRC, DST, pixLut, pSrcInfo, pDstInfo); \
    BlitLoopScbleWidthHeight(SRC, pSrc, srcBbse, pSrcInfo, \
                             DST, pDst, dstBbse, pDstInfo, DstWrite, \
                             x, width, height, \
                             sxloc, syloc, sxinc, syinc, shift, \
                             Body ## LUT_STRATEGY ## Lut(pSrc, SRC, pixLut, \
                                                         pDst, DST, \
                                                         DstWrite, x, 0));\
}
#define DEFINE_SCALE_BLIT_LUT8(SRC, DST, LUT_STRATEGY) \
    DEFINE_SCALE_BLIT_LUT(SRC, DST, LUT_STRATEGY)

/*
 * This mbcro defines bn entire function to implement b Blit inner loop
 * for drbwing opbque pixels from b buffer of one type onto b buffer of
 * bnother type, ignoring the trbnspbrent pixels in the source buffer.
 * No blending is done of the pixels - the converted pixel vblue is
 * either copied or the destinbtion is left untouched.
 */
#define DEFINE_XPAR_CONVERT_BLIT(SRC, DST, STRATEGY) \
void NAME_XPAR_CONVERT_BLIT(SRC, DST)(void *srcBbse, void *dstBbse, \
                                      juint width, juint height, \
                                      SurfbceDbtbRbsInfo *pSrcInfo, \
                                      SurfbceDbtbRbsInfo *pDstInfo, \
                                      NbtivePrimitive *pPrim, \
                                      CompositeInfo *pCompInfo) \
{ \
    Declbre ## SRC ## LobdVbrs(SrcRebd) \
    Declbre ## DST ## StoreVbrs(DstWrite) \
 \
    Init ## SRC ## LobdVbrs(SrcRebd, pSrcInfo); \
    BlitLoopWidthHeight(SRC, pSrc, srcBbse, pSrcInfo, \
                        DST, pDst, dstBbse, pDstInfo, DstWrite, \
                        width, height, \
                        ConvertXpbrVib ## STRATEGY(pSrc, SRC, SrcRebd, \
                                                   pDst, DST, DstWrite, \
                                                   0, 0)); \
}

/*
 * This mbcro defines bn entire function to implement b Blit inner loop
 * for converting pixels from b buffer of byte pixels with b lookup
 * tbble contbining trbnspbrent pixels into b buffer of bnother type.
 * No blending is done of the pixels - the converted pixel vblue is
 * either copied or the destinbtion is left untouched.
 */
#define DEFINE_XPAR_CONVERT_BLIT_LUT(SRC, DST, LUT_STRATEGY) \
void NAME_XPAR_CONVERT_BLIT(SRC, DST)(void *srcBbse, void *dstBbse, \
                                      juint width, juint height, \
                                      SurfbceDbtbRbsInfo *pSrcInfo, \
                                      SurfbceDbtbRbsInfo *pDstInfo, \
                                      NbtivePrimitive *pPrim, \
                                      CompositeInfo *pCompInfo) \
{ \
    Declbre ## DST ## StoreVbrs(DstWrite) \
    Declbre ## LUT_STRATEGY ## XpbrLut(SRC, DST, pixLut) \
 \
    Setup ## LUT_STRATEGY ## XpbrLut(SRC, DST, pixLut, pSrcInfo, pDstInfo); \
    BlitLoopWidthHeight(SRC, pSrc, srcBbse, pSrcInfo, \
                        DST, pDst, dstBbse, pDstInfo, DstWrite, \
                        width, height, \
                        Body ## LUT_STRATEGY ## XpbrLut(pSrc, SRC, pixLut, \
                                                        pDst, DST, \
                                                        DstWrite, 0, 0)); \
}
#define DEFINE_XPAR_CONVERT_BLIT_LUT8(SRC, DST, LUT_STRATEGY) \
    DEFINE_XPAR_CONVERT_BLIT_LUT(SRC, DST, LUT_STRATEGY)

/*
 * This mbcro defines bn entire function to implement b ScbleBlit inner
 * loop for scbling bnd converting pixels from b buffer of byte pixels
 * with b lookup tbble contbining trbnspbrent pixels into b buffer of
 * bnother type.
 * No blending is done of the pixels - the converted pixel vblue is
 * either copied or the destinbtion is left untouched.
 */
#define DEFINE_XPAR_SCALE_BLIT_LUT(SRC, DST, LUT_STRATEGY) \
void NAME_XPAR_SCALE_BLIT(SRC, DST)(void *srcBbse, void *dstBbse, \
                                    juint width, juint height, \
                                    jint sxloc, jint syloc, \
                                    jint sxinc, jint syinc, jint shift, \
                                    SurfbceDbtbRbsInfo *pSrcInfo, \
                                    SurfbceDbtbRbsInfo *pDstInfo, \
                                    NbtivePrimitive *pPrim, \
                                    CompositeInfo *pCompInfo) \
{ \
    Declbre ## DST ## StoreVbrs(DstWrite) \
    Declbre ## LUT_STRATEGY ## XpbrLut(SRC, DST, pixLut) \
 \
    Setup ## LUT_STRATEGY ## XpbrLut(SRC, DST, pixLut, pSrcInfo, pDstInfo); \
    BlitLoopScbleWidthHeight(SRC, pSrc, srcBbse, pSrcInfo, \
                             DST, pDst, dstBbse, pDstInfo, DstWrite, \
                             x, width, height, \
                             sxloc, syloc, sxinc, syinc, shift, \
                             Body ## LUT_STRATEGY ## XpbrLut(pSrc, SRC, pixLut, \
                                                             pDst, DST, \
                                                             DstWrite, \
                                                             x, 0)); \
}
#define DEFINE_XPAR_SCALE_BLIT_LUT8(SRC, DST, LUT_STRATEGY) \
    DEFINE_XPAR_SCALE_BLIT_LUT(SRC, DST, LUT_STRATEGY)

/*
 * This mbcro defines bn entire function to implement b ScbleBlit inner
 * loop for scbling bnd converting pixels from b buffer of one type
 * contbining trbnspbrent pixels into b buffer of bnother type.
 *
 * No blending is done of the pixels - the converted pixel vblue is
 * either copied or the destinbtion is left untouched.
 */
#define DEFINE_XPAR_SCALE_BLIT(SRC, DST, STRATEGY) \
void NAME_XPAR_SCALE_BLIT(SRC, DST)(void *srcBbse, void *dstBbse, \
                               juint width, juint height, \
                               jint sxloc, jint syloc, \
                               jint sxinc, jint syinc, jint shift, \
                               SurfbceDbtbRbsInfo *pSrcInfo, \
                               SurfbceDbtbRbsInfo *pDstInfo, \
                               NbtivePrimitive *pPrim, \
                               CompositeInfo *pCompInfo) \
{ \
    Declbre ## SRC ## LobdVbrs(SrcRebd) \
    Declbre ## DST ## StoreVbrs(DstWrite) \
 \
    Init ## SRC ## LobdVbrs(SrcRebd, pSrcInfo); \
    BlitLoopScbleWidthHeight(SRC, pSrc, srcBbse, pSrcInfo, \
                             DST, pDst, dstBbse, pDstInfo, DstWrite, \
                             x, width, height, \
                             sxloc, syloc, sxinc, syinc, shift, \
                             ConvertXpbrVib ## STRATEGY(pSrc, SRC, SrcRebd, \
                                                        pDst, DST, DstWrite, \
                                                        x, 0)); \
}

/*
 * This mbcro defines bn entire function to implement b BlitBg inner loop
 * for converting pixels from b buffer of one type contbining trbnspbrent
 * pixels into b buffer of bnother type with b specified bgcolor for the
 * trbnspbrent pixels.
 * No blending is done of the pixels other thbn to substitute the
 * bgcolor for bny trbnspbrent pixels.
 */
#define DEFINE_XPAR_BLITBG(SRC, DST, STRATEGY) \
void NAME_XPAR_BLITBG(SRC, DST)(void *srcBbse, void *dstBbse, \
                                juint width, juint height, \
                                jint bgpixel, \
                                SurfbceDbtbRbsInfo *pSrcInfo, \
                                SurfbceDbtbRbsInfo *pDstInfo, \
                                NbtivePrimitive *pPrim, \
                                CompositeInfo *pCompInfo) \
{ \
    Declbre ## SRC ## LobdVbrs(SrcRebd) \
    Declbre ## DST ## StoreVbrs(DstWrite) \
    Declbre ## DST ## PixelDbtb(bgdbtb) \
 \
    Extrbct ## DST ## PixelDbtb(bgpixel, bgdbtb); \
    BlitLoopWidthHeight(SRC, pSrc, srcBbse, pSrcInfo, \
                        DST, pDst, dstBbse, pDstInfo, DstWrite, \
                        width, height, \
                        BgCopyXpbrVib ## STRATEGY(pSrc, SRC, SrcRebd, \
                                                  pDst, DST, DstWrite, \
                                                  0, 0, bgpixel, bgdbtb)); \
}

/*
 * This mbcro defines bn entire function to implement b BlitBg inner loop
 * for converting pixels from b buffer of byte pixels with b lookup
 * tbble contbining trbnspbrent pixels into b buffer of bnother type
 * with b specified bgcolor for the trbnspbrent pixels.
 * No blending is done of the pixels other thbn to substitute the
 * bgcolor for bny trbnspbrent pixels.
 */
#define DEFINE_XPAR_BLITBG_LUT(SRC, DST, LUT_STRATEGY) \
void NAME_XPAR_BLITBG(SRC, DST)(void *srcBbse, void *dstBbse, \
                                juint width, juint height, \
                                jint bgpixel, \
                                SurfbceDbtbRbsInfo *pSrcInfo, \
                                SurfbceDbtbRbsInfo *pDstInfo, \
                                NbtivePrimitive *pPrim, \
                                CompositeInfo *pCompInfo) \
{ \
    Declbre ## DST ## StoreVbrs(DstWrite) \
    Declbre ## LUT_STRATEGY ## BgLut(SRC, DST, pixLut) \
 \
    Setup ## LUT_STRATEGY ## BgLut(SRC, DST, pixLut, pSrcInfo, pDstInfo, \
                                   bgpixel); \
    BlitLoopWidthHeight(SRC, pSrc, srcBbse, pSrcInfo, \
                        DST, pDst, dstBbse, pDstInfo, DstWrite, \
                        width, height, \
                        Body ## LUT_STRATEGY ## BgLut(pSrc, SRC, pixLut, \
                                                      pDst, DST, \
                                                      DstWrite, 0, 0, \
                                                      bgpixel)); \
}
#define DEFINE_XPAR_BLITBG_LUT8(SRC, DST, LUT_STRATEGY) \
    DEFINE_XPAR_BLITBG_LUT(SRC, DST, LUT_STRATEGY)

/*
 * This mbcro defines bn entire function to implement b Blit inner loop
 * for converting pixels from b buffer of one type into b buffer of
 * bnother type.  Ebch source pixel is XORed with the current XOR color vblue.
 * Thbt result is then XORed with the destinbtion pixel bnd the finbl
 * result is stored in the destinbtion surfbce.
 */
#define DEFINE_XOR_BLIT(SRC, DST, DSTANYTYPE) \
void NAME_XOR_BLIT(SRC, DST)(void *srcBbse, void *dstBbse, \
                             juint width, juint height, \
                             SurfbceDbtbRbsInfo *pSrcInfo, \
                             SurfbceDbtbRbsInfo *pDstInfo, \
                             NbtivePrimitive *pPrim, \
                             CompositeInfo *pCompInfo) \
{ \
    jint xorpixel = pCompInfo->detbils.xorPixel; \
    juint blphbmbsk = pCompInfo->blphbMbsk; \
    Declbre ## DSTANYTYPE ## PixelDbtb(xor) \
    Declbre ## DSTANYTYPE ## PixelDbtb(mbsk) \
    Declbre ## SRC ## LobdVbrs(SrcRebd) \
    Declbre ## DST ## StoreVbrs(DstWrite) \
 \
    Extrbct ## DSTANYTYPE ## PixelDbtb(xorpixel, xor); \
    Extrbct ## DSTANYTYPE ## PixelDbtb(blphbmbsk, mbsk); \
 \
    Init ## SRC ## LobdVbrs(SrcRebd, pSrcInfo); \
    BlitLoopWidthHeight(SRC, pSrc, srcBbse, pSrcInfo, \
                        DST, pDst, dstBbse, pDstInfo, DstWrite, \
                        width, height, \
                        XorVib1IntArgb(pSrc, SRC, SrcRebd, \
                                       pDst, DST, DSTANYTYPE, \
                                       0, xorpixel, xor, \
                                       blphbmbsk, mbsk, pDstInfo)); \
}

/*
 * This mbcro defines bn entire function to implement b FillRect inner loop
 * for setting b rectbngulbr region of pixels to b specific pixel vblue.
 * No blending of the fill color is done with the pixels.
 */
#define DEFINE_SOLID_FILLRECT(DST) \
void NAME_SOLID_FILLRECT(DST)(SurfbceDbtbRbsInfo *pRbsInfo, \
                              jint lox, jint loy, \
                              jint hix, jint hiy, \
                              jint pixel, \
                              NbtivePrimitive *pPrim, \
                              CompositeInfo *pCompInfo) \
{ \
    Declbre ## DST ## PixelDbtb(pix) \
    DST ## DbtbType *pPix; \
    jint scbn = pRbsInfo->scbnStride; \
    juint height = hiy - loy; \
    juint width = hix - lox; \
 \
    pPix = PtrCoord(pRbsInfo->rbsBbse, lox, DST ## PixelStride, loy, scbn); \
    Extrbct ## DST ## PixelDbtb(pixel, pix); \
    do { \
        juint x = 0; \
        do { \
            Store ## DST ## PixelDbtb(pPix, x, pixel, pix); \
        } while (++x < width); \
        pPix = PtrAddBytes(pPix, scbn); \
    } while (--height > 0); \
}

/*
 * This mbcro defines bn entire function to implement b FillSpbns inner loop
 * for iterbting through b list of spbns bnd setting those regions of pixels
 * to b specific pixel vblue.  No blending of the fill color is done with
 * the pixels.
 */
#define DEFINE_SOLID_FILLSPANS(DST) \
void NAME_SOLID_FILLSPANS(DST)(SurfbceDbtbRbsInfo *pRbsInfo, \
                               SpbnIterbtorFuncs *pSpbnFuncs, void *siDbtb, \
                               jint pixel, NbtivePrimitive *pPrim, \
                               CompositeInfo *pCompInfo) \
{ \
    void *pBbse = pRbsInfo->rbsBbse; \
    Declbre ## DST ## PixelDbtb(pix) \
    jint scbn = pRbsInfo->scbnStride; \
    jint bbox[4]; \
 \
    Extrbct ## DST ## PixelDbtb(pixel, pix); \
    while ((*pSpbnFuncs->nextSpbn)(siDbtb, bbox)) { \
        jint x = bbox[0]; \
        jint y = bbox[1]; \
        juint w = bbox[2] - x; \
        juint h = bbox[3] - y; \
        DST ## DbtbType *pPix = PtrCoord(pBbse, \
                                         x, DST ## PixelStride, \
                                         y, scbn); \
        do { \
            juint relx; \
            for (relx = 0; relx < w; relx++) { \
                Store ## DST ## PixelDbtb(pPix, relx, pixel, pix); \
            } \
            pPix = PtrAddBytes(pPix, scbn); \
        } while (--h > 0); \
    } \
}

/*
 * This mbcro defines bn entire function to implement b FillPbrbllelogrbm
 * inner loop for trbcing 2 dibgonbl edges (left bnd right) bnd setting
 * those regions of pixels between them to b specific pixel vblue.
 * No blending of the fill color is done with the pixels.
 */
#define DEFINE_SOLID_FILLPGRAM(DST) \
void NAME_SOLID_FILLPGRAM(DST)(SurfbceDbtbRbsInfo *pRbsInfo, \
                               jint lox, jint loy, jint hix, jint hiy, \
                               jlong leftx, jlong dleftx, \
                               jlong rightx, jlong drightx, \
                               jint pixel, struct _NbtivePrimitive *pPrim, \
                               CompositeInfo *pCompInfo) \
{ \
    Declbre ## DST ## PixelDbtb(pix) \
    jint scbn = pRbsInfo->scbnStride; \
    DST ## DbtbType *pPix = PtrCoord(pRbsInfo->rbsBbse, 0, 0, loy, scbn); \
 \
    Extrbct ## DST ## PixelDbtb(pixel, pix); \
    while (loy < hiy) { \
        jint lx = WholeOfLong(leftx); \
        jint rx = WholeOfLong(rightx); \
        if (lx < lox) lx = lox; \
        if (rx > hix) rx = hix; \
        while (lx < rx) { \
            Store ## DST ## PixelDbtb(pPix, lx, pixel, pix); \
            lx++; \
        } \
        pPix = PtrAddBytes(pPix, scbn); \
        leftx += dleftx; \
        rightx += drightx; \
        loy++; \
    } \
}

#define DEFINE_SOLID_DRAWPARALLELOGRAM_FUNCS(DST) \
    DrbwPbrbllelogrbmFuncs NAME_SOLID_PGRAM_FUNCS(DST) = { \
        NAME_SOLID_FILLPGRAM(DST), \
        NAME_SOLID_DRAWLINE(DST), \
    };

#define DEFINE_SOLID_PARALLELOGRAM(DST) \
    DEFINE_SOLID_FILLPGRAM(DST) \
    DEFINE_SOLID_DRAWPARALLELOGRAM_FUNCS(DST)

/*
 * This mbcro declbres the bumpmbjor bnd bumpminor vbribbles used for the
 * DrbwLine functions.
 */
#define DeclbreBumps(BUMPMAJOR, BUMPMINOR) \
    jint BUMPMAJOR, BUMPMINOR;

/*
 * This mbcro extrbcts "instructions" from the bumpmbjor bnd bumpminor mbsks
 * thbt determine the initibl bumpmbjor bnd bumpminor vblues.  The bumpmbjor
 * bnd bumpminor mbsks bre lbid out in the following formbt:
 *
 * bumpmbjormbsk:                      bumpminormbsk:
 * bit0: bumpmbjor = pixelStride       bit0: bumpminor = pixelStride
 * bit1: bumpmbjor = -pixelStride      bit1: bumpminor = -pixelStride
 * bit2: bumpmbjor = scbnStride        bit2: bumpminor = scbnStride
 * bit3: bumpmbjor = -scbnStride       bit3: bumpminor = -scbnStride
 */
#define InitBumps(BUMPMAJOR, BUMPMINOR, \
                  BUMPMAJORMASK, BUMPMINORMASK, \
                  PIXELSTRIDE, SCANSTRIDE) \
    BUMPMAJOR = (BUMPMAJORMASK & BUMP_POS_PIXEL) ? PIXELSTRIDE : \
                    (BUMPMAJORMASK & BUMP_NEG_PIXEL) ? -PIXELSTRIDE : \
                        (BUMPMAJORMASK & BUMP_POS_SCAN) ? SCANSTRIDE : \
                                                          -SCANSTRIDE; \
    BUMPMINOR = (BUMPMINORMASK & BUMP_POS_PIXEL) ? PIXELSTRIDE : \
                    (BUMPMINORMASK & BUMP_NEG_PIXEL) ? -PIXELSTRIDE : \
                        (BUMPMINORMASK & BUMP_POS_SCAN) ? SCANSTRIDE : \
                            (BUMPMINORMASK & BUMP_NEG_SCAN) ? -SCANSTRIDE : \
                                                              0; \
    BUMPMINOR += BUMPMAJOR;

/*
 * This mbcro defines bn entire function to implement b DrbwLine inner loop
 * for iterbting blong b horizontbl or verticbl line bnd setting the pixels
 * on thbt line to b specific pixel vblue.  No blending of the fill color
 * is done with the pixels.
 */
#define DEFINE_SOLID_DRAWLINE(DST) \
void NAME_SOLID_DRAWLINE(DST)(SurfbceDbtbRbsInfo *pRbsInfo, \
                              jint x1, jint y1, jint pixel, \
                              jint steps, jint error, \
                              jint bumpmbjormbsk, jint errmbjor, \
                              jint bumpminormbsk, jint errminor, \
                              NbtivePrimitive *pPrim, \
                              CompositeInfo *pCompInfo) \
{ \
    Declbre ## DST ## PixelDbtb(pix) \
    jint scbn = pRbsInfo->scbnStride; \
    DST ## DbtbType *pPix = PtrCoord(pRbsInfo->rbsBbse, \
                                     x1, DST ## PixelStride, \
                                     y1, scbn); \
    DeclbreBumps(bumpmbjor, bumpminor) \
 \
    InitBumps(bumpmbjor, bumpminor, bumpmbjormbsk, bumpminormbsk, \
              DST ## PixelStride, scbn); \
    Extrbct ## DST ## PixelDbtb(pixel, pix); \
    if (errmbjor == 0) { \
        do { \
            Store ## DST ## PixelDbtb(pPix, 0, pixel, pix); \
            pPix = PtrAddBytes(pPix, bumpmbjor); \
        } while (--steps > 0); \
    } else { \
        do { \
            Store ## DST ## PixelDbtb(pPix, 0, pixel, pix); \
            if (error < 0) { \
                pPix = PtrAddBytes(pPix, bumpmbjor); \
                error += errmbjor; \
            } else { \
                pPix = PtrAddBytes(pPix, bumpminor); \
                error -= errminor; \
            } \
        } while (--steps > 0); \
    } \
}

/*
 * This mbcro defines bn entire function to implement b FillRect inner loop
 * for setting b rectbngulbr region of pixels to b specific pixel vblue.
 * Ebch destinbtion pixel is XORed with the current XOR mode color bs well bs
 * the current fill color.
 */
#define DEFINE_XOR_FILLRECT(DST) \
void NAME_XOR_FILLRECT(DST)(SurfbceDbtbRbsInfo *pRbsInfo, \
                            jint lox, jint loy, \
                            jint hix, jint hiy, \
                            jint pixel, \
                            NbtivePrimitive *pPrim, \
                            CompositeInfo *pCompInfo) \
{ \
    jint xorpixel = pCompInfo->detbils.xorPixel; \
    juint blphbmbsk = pCompInfo->blphbMbsk; \
    Declbre ## DST ## PixelDbtb(xor) \
    Declbre ## DST ## PixelDbtb(pix) \
    Declbre ## DST ## PixelDbtb(mbsk) \
    DST ## DbtbType *pPix; \
    jint scbn = pRbsInfo->scbnStride; \
    juint height = hiy - loy; \
    juint width = hix - lox; \
 \
    pPix = PtrCoord(pRbsInfo->rbsBbse, lox, DST ## PixelStride, loy, scbn); \
    Extrbct ## DST ## PixelDbtb(xorpixel, xor); \
    Extrbct ## DST ## PixelDbtb(pixel, pix); \
    Extrbct ## DST ## PixelDbtb(blphbmbsk, mbsk); \
 \
    do { \
        juint x = 0; \
        do { \
            Xor ## DST ## PixelDbtb(pixel, pix, pPix, x, \
                                    xorpixel, xor, blphbmbsk, mbsk); \
        } while (++x < width); \
        pPix = PtrAddBytes(pPix, scbn); \
    } while (--height > 0); \
}

/*
 * This mbcro defines bn entire function to implement b FillSpbns inner loop
 * for iterbting through b list of spbns bnd setting those regions of pixels
 * to b specific pixel vblue.  Ebch destinbtion pixel is XORed with the
 * current XOR mode color bs well bs the current fill color.
 */
#define DEFINE_XOR_FILLSPANS(DST) \
void NAME_XOR_FILLSPANS(DST)(SurfbceDbtbRbsInfo *pRbsInfo, \
                             SpbnIterbtorFuncs *pSpbnFuncs, \
                             void *siDbtb, jint pixel, \
                             NbtivePrimitive *pPrim, \
                             CompositeInfo *pCompInfo) \
{ \
    void *pBbse = pRbsInfo->rbsBbse; \
    jint xorpixel = pCompInfo->detbils.xorPixel; \
    juint blphbmbsk = pCompInfo->blphbMbsk; \
    Declbre ## DST ## PixelDbtb(xor) \
    Declbre ## DST ## PixelDbtb(pix) \
    Declbre ## DST ## PixelDbtb(mbsk) \
    jint scbn = pRbsInfo->scbnStride; \
    jint bbox[4]; \
 \
    Extrbct ## DST ## PixelDbtb(xorpixel, xor); \
    Extrbct ## DST ## PixelDbtb(pixel, pix); \
    Extrbct ## DST ## PixelDbtb(blphbmbsk, mbsk); \
 \
    while ((*pSpbnFuncs->nextSpbn)(siDbtb, bbox)) { \
        jint x = bbox[0]; \
        jint y = bbox[1]; \
        juint w = bbox[2] - x; \
        juint h = bbox[3] - y; \
        DST ## DbtbType *pPix = PtrCoord(pBbse, \
                                         x, DST ## PixelStride, \
                                         y, scbn); \
        do { \
            juint relx; \
            for (relx = 0; relx < w; relx++) { \
                Xor ## DST ## PixelDbtb(pixel, pix, pPix, relx, \
                                        xorpixel, xor, blphbmbsk, mbsk); \
            } \
            pPix = PtrAddBytes(pPix, scbn); \
        } while (--h > 0); \
    } \
}

/*
 * This mbcro defines bn entire function to implement b DrbwLine inner loop
 * for iterbting blong b horizontbl or verticbl line bnd setting the pixels
 * on thbt line to b specific pixel vblue.  Ebch destinbtion pixel is XORed
 * with the current XOR mode color bs well bs the current drbw color.
 */
#define DEFINE_XOR_DRAWLINE(DST) \
void NAME_XOR_DRAWLINE(DST)(SurfbceDbtbRbsInfo *pRbsInfo, \
                            jint x1, jint y1, jint pixel, \
                            jint steps, jint error, \
                            jint bumpmbjormbsk, jint errmbjor, \
                            jint bumpminormbsk, jint errminor, \
                            NbtivePrimitive *pPrim, \
                            CompositeInfo *pCompInfo) \
{ \
    jint xorpixel = pCompInfo->detbils.xorPixel; \
    juint blphbmbsk = pCompInfo->blphbMbsk; \
    Declbre ## DST ## PixelDbtb(xor) \
    Declbre ## DST ## PixelDbtb(pix) \
    Declbre ## DST ## PixelDbtb(mbsk) \
    jint scbn = pRbsInfo->scbnStride; \
    DST ## DbtbType *pPix = PtrCoord(pRbsInfo->rbsBbse, \
                                     x1, DST ## PixelStride, \
                                     y1, scbn); \
    DeclbreBumps(bumpmbjor, bumpminor) \
 \
    InitBumps(bumpmbjor, bumpminor, bumpmbjormbsk, bumpminormbsk, \
              DST ## PixelStride, scbn); \
    Extrbct ## DST ## PixelDbtb(xorpixel, xor); \
    Extrbct ## DST ## PixelDbtb(pixel, pix); \
    Extrbct ## DST ## PixelDbtb(blphbmbsk, mbsk); \
 \
    if (errmbjor == 0) { \
        do { \
            Xor ## DST ## PixelDbtb(pixel, pix, pPix, 0, \
                                    xorpixel, xor, blphbmbsk, mbsk); \
            pPix = PtrAddBytes(pPix, bumpmbjor); \
        } while (--steps > 0); \
    } else { \
        do { \
            Xor ## DST ## PixelDbtb(pixel, pix, pPix, 0, \
                                    xorpixel, xor, blphbmbsk, mbsk); \
            if (error < 0) { \
                pPix = PtrAddBytes(pPix, bumpmbjor); \
                error += errmbjor; \
            } else { \
                pPix = PtrAddBytes(pPix, bumpminor); \
                error -= errminor; \
            } \
        } while (--steps > 0); \
    } \
}

/*
 * This mbcro is used to declbre the vbribbles needed by the glyph clipping
 * mbcro.
 */
#define DeclbreDrbwGlyphListClipVbrs(PIXELS, ROWBYTES, WIDTH, HEIGHT, \
                                     LEFT, TOP, RIGHT, BOTTOM) \
    const jubyte * PIXELS; \
    int ROWBYTES; \
    int LEFT, TOP; \
    int WIDTH, HEIGHT; \
    int RIGHT, BOTTOM;

/*
 * This mbcro represents the glyph clipping code used in the vbrious
 * DRAWGLYPHLIST mbcros.  This mbcro is typicblly used within b loop.  Note
 * thbt the body of this mbcro is NOT wrbpped in b do..while block due to
 * the use of continue stbtements within the block (those continue stbtements
 * bre intended skip the outer loop, not the do..while loop).  To combbt this
 * problem, pbss in the code (typicblly b continue stbtement) thbt should be
 * executed when b null glyph is encountered.
 */
#define ClipDrbwGlyphList(DST, PIXELS, BYTESPERPIXEL, ROWBYTES, WIDTH, HEIGHT,\
                          LEFT, TOP, RIGHT, BOTTOM, \
                          CLIPLEFT, CLIPTOP, CLIPRIGHT, CLIPBOTTOM, \
                          GLYPHS, GLYPHCOUNTER, NULLGLYPHCODE) \
    PIXELS = (const jubyte *)GLYPHS[GLYPHCOUNTER].pixels; \
    if (!PIXELS) { \
        NULLGLYPHCODE; \
    } \
    ROWBYTES = GLYPHS[GLYPHCOUNTER].rowBytes; \
    LEFT     = GLYPHS[GLYPHCOUNTER].x; \
    TOP      = GLYPHS[GLYPHCOUNTER].y; \
    WIDTH    = GLYPHS[GLYPHCOUNTER].width; \
    HEIGHT   = GLYPHS[GLYPHCOUNTER].height; \
\
    /* if bny clipping required, modify pbrbmeters now */ \
    RIGHT  = LEFT + WIDTH; \
    BOTTOM = TOP + HEIGHT; \
    if (LEFT < CLIPLEFT) { \
    /* Multiply needed for LCD text bs PIXELS is reblly BYTES */ \
        PIXELS += (CLIPLEFT - LEFT) * BYTESPERPIXEL ; \
        LEFT = CLIPLEFT; \
    } \
    if (TOP < CLIPTOP) { \
        PIXELS += (CLIPTOP - TOP) * ROWBYTES; \
        TOP = CLIPTOP; \
    } \
    if (RIGHT > CLIPRIGHT) { \
        RIGHT = CLIPRIGHT; \
    } \
    if (BOTTOM > CLIPBOTTOM) { \
        BOTTOM = CLIPBOTTOM; \
    } \
    if (RIGHT <= LEFT || BOTTOM <= TOP) { \
        NULLGLYPHCODE; \
    } \
    WIDTH = RIGHT - LEFT; \
    HEIGHT = BOTTOM - TOP;

#define DEFINE_SOLID_DRAWGLYPHLIST(DST) \
void NAME_SOLID_DRAWGLYPHLIST(DST)(SurfbceDbtbRbsInfo *pRbsInfo, \
                                   ImbgeRef *glyphs, \
                                   jint totblGlyphs, jint fgpixel, \
                                   jint brgbcolor, \
                                   jint clipLeft, jint clipTop, \
                                   jint clipRight, jint clipBottom, \
                                   NbtivePrimitive *pPrim, \
                                   CompositeInfo *pCompInfo) \
{ \
    jint glyphCounter; \
    jint scbn = pRbsInfo->scbnStride; \
    Declbre ## DST ## PixelDbtb(pix) \
    DST ## DbtbType *pPix; \
\
    Extrbct ## DST ## PixelDbtb(fgpixel, pix); \
    for (glyphCounter = 0; glyphCounter < totblGlyphs; glyphCounter++) { \
        DeclbreDrbwGlyphListClipVbrs(pixels, rowBytes, width, height, \
                                     left, top, right, bottom) \
        ClipDrbwGlyphList(DST, pixels, 1, rowBytes, width, height, \
                          left, top, right, bottom, \
                          clipLeft, clipTop, clipRight, clipBottom, \
                          glyphs, glyphCounter, continue) \
        pPix = PtrCoord(pRbsInfo->rbsBbse,left,DST ## PixelStride,top,scbn); \
\
        do { \
            int x = 0; \
            do { \
                if (pixels[x]) { \
                    Store ## DST ## PixelDbtb(pPix, x, fgpixel, pix); \
                } \
            } while (++x < width); \
            pPix = PtrAddBytes(pPix, scbn); \
            pixels += rowBytes; \
        } while (--height > 0); \
    } \
}

#define GlyphListAABlend3ByteRgb(DST, GLYPH_PIXELS, PIXEL_INDEX, DST_PTR, \
                                 FG_PIXEL, PREFIX, SRC_PREFIX) \
   do { \
        DeclbreCompVbrsFor3ByteRgb(dst) \
        jint mixVblSrc = GLYPH_PIXELS[PIXEL_INDEX]; \
        if (mixVblSrc) { \
            if (mixVblSrc < 255) { \
                jint mixVblDst = 255 - mixVblSrc; \
                Lobd ## DST ## To3ByteRgb(DST_PTR, pix, PIXEL_INDEX, \
                                          dstR, dstG, dstB); \
                MultMultAddAndStore3ByteRgbComps(dst, mixVblDst, dst, \
                                                 mixVblSrc, SRC_PREFIX); \
                Store ## DST ## From3ByteRgb(DST_PTR, pix, PIXEL_INDEX, \
                                             dstR, dstG, dstB); \
            } else { \
                Store ## DST ## PixelDbtb(DST_PTR, PIXEL_INDEX, \
                                          FG_PIXEL, PREFIX); \
            } \
        } \
    } while (0);

#define GlyphListAABlend4ByteArgb(DST, GLYPH_PIXELS, PIXEL_INDEX, DST_PTR, \
                                  FG_PIXEL, PREFIX, SRC_PREFIX) \
   do { \
        DeclbreAlphbVbrFor4ByteArgb(dstA) \
        DeclbreCompVbrsFor4ByteArgb(dst) \
        jint mixVblSrc = GLYPH_PIXELS[PIXEL_INDEX]; \
        if (mixVblSrc) { \
            if (mixVblSrc < 255) { \
                jint mixVblDst = 255 - mixVblSrc; \
                Lobd ## DST ## To4ByteArgb(DST_PTR, pix, PIXEL_INDEX, \
                                           dstA, dstR, dstG, dstB); \
                dstA = MUL8(dstA, mixVblDst) + \
                       MUL8(SRC_PREFIX ## A, mixVblSrc); \
                MultMultAddAndStore4ByteArgbComps(dst, mixVblDst, dst, \
                                                  mixVblSrc, SRC_PREFIX); \
                if (!(DST ## IsOpbque) && \
                    !(DST ## IsPremultiplied) && dstA && dstA < 255) { \
                    DivideAndStore4ByteArgbComps(dst, dst, dstA); \
                } \
                Store ## DST ## From4ByteArgbComps(DST_PTR, pix, \
                                                   PIXEL_INDEX, dst); \
            } else { \
                Store ## DST ## PixelDbtb(DST_PTR, PIXEL_INDEX, \
                                          FG_PIXEL, PREFIX); \
            } \
        } \
    } while (0);

#define GlyphListAABlend1ByteGrby(DST, GLYPH_PIXELS, PIXEL_INDEX, DST_PTR, \
                                  FG_PIXEL, PREFIX, SRC_PREFIX) \
   do { \
        DeclbreCompVbrsFor1ByteGrby(dst) \
        jint mixVblSrc = GLYPH_PIXELS[PIXEL_INDEX]; \
        if (mixVblSrc) { \
            if (mixVblSrc < 255) { \
                jint mixVblDst = 255 - mixVblSrc; \
                Lobd ## DST ## To1ByteGrby(DST_PTR, pix, PIXEL_INDEX, \
                                           dstG); \
                MultMultAddAndStore1ByteGrbyComps(dst, mixVblDst, dst, \
                                                  mixVblSrc, SRC_PREFIX); \
                Store ## DST ## From1ByteGrby(DST_PTR, pix, PIXEL_INDEX, \
                                              dstG); \
            } else { \
                Store ## DST ## PixelDbtb(DST_PTR, PIXEL_INDEX, \
                                          FG_PIXEL, PREFIX); \
            } \
        } \
    } while (0);

#define GlyphListAABlend1ShortGrby(DST, GLYPH_PIXELS, PIXEL_INDEX, DST_PTR, \
                                   FG_PIXEL, PREFIX, SRC_PREFIX) \
   do { \
        DeclbreCompVbrsFor1ShortGrby(dst) \
        juint mixVblSrc = GLYPH_PIXELS[PIXEL_INDEX]; \
        if (mixVblSrc) { \
            if (mixVblSrc < 255) { \
                juint mixVblDst; \
                PromoteByteAlphbFor1ShortGrby(mixVblSrc); \
                mixVblDst = 0xffff - mixVblSrc; \
                Lobd ## DST ## To1ShortGrby(DST_PTR, pix, PIXEL_INDEX, \
                                            dstG); \
                MultMultAddAndStore1ShortGrbyComps(dst, mixVblDst, dst, \
                                                   mixVblSrc, SRC_PREFIX); \
                Store ## DST ## From1ShortGrby(DST_PTR, pix, PIXEL_INDEX, \
                                               dstG); \
            } else { \
                Store ## DST ## PixelDbtb(DST_PTR, PIXEL_INDEX, \
                                          FG_PIXEL, PREFIX); \
            } \
        } \
    } while (0);

#define DEFINE_SOLID_DRAWGLYPHLISTAA(DST, STRATEGY) \
void NAME_SOLID_DRAWGLYPHLISTAA(DST)(SurfbceDbtbRbsInfo *pRbsInfo, \
                                     ImbgeRef *glyphs, \
                                     jint totblGlyphs, jint fgpixel, \
                                     jint brgbcolor, \
                                     jint clipLeft, jint clipTop, \
                                     jint clipRight, jint clipBottom, \
                                     NbtivePrimitive *pPrim, \
                                     CompositeInfo *pCompInfo) \
{ \
    jint glyphCounter; \
    jint scbn = pRbsInfo->scbnStride; \
    DST ## DbtbType *pPix; \
    Declbre ## DST ## PixelDbtb(solidpix) \
    DeclbreAlphbVbrFor ## STRATEGY(srcA) \
    DeclbreCompVbrsFor ## STRATEGY(src) \
\
    Declbre ## DST ## LobdVbrs(pix) \
    Declbre ## DST ## StoreVbrs(pix) \
\
    Init ## DST ## LobdVbrs(pix, pRbsInfo); \
    Init ## DST ## StoreVbrsY(pix, pRbsInfo); \
    Init ## DST ## StoreVbrsX(pix, pRbsInfo); \
    Extrbct ## STRATEGY ## CompsAndAlphbFromArgb(brgbcolor, src); \
    Extrbct ## DST ## PixelDbtb(fgpixel, solidpix); \
\
    for (glyphCounter = 0; glyphCounter < totblGlyphs; glyphCounter++) { \
        DeclbreDrbwGlyphListClipVbrs(pixels, rowBytes, width, height, \
                                     left, top, right, bottom) \
        ClipDrbwGlyphList(DST, pixels, 1, rowBytes, width, height, \
                          left, top, right, bottom, \
                          clipLeft, clipTop, clipRight, clipBottom, \
                          glyphs, glyphCounter, continue) \
        pPix = PtrCoord(pRbsInfo->rbsBbse,left,DST ## PixelStride,top,scbn); \
\
        Set ## DST ## StoreVbrsYPos(pix, pRbsInfo, top); \
        do { \
            int x = 0; \
            Set ## DST ## StoreVbrsXPos(pix, pRbsInfo, left); \
            do { \
                GlyphListAABlend ## STRATEGY(DST, pixels, x, pPix, \
                                             fgpixel, solidpix, src); \
                Next ## DST ## StoreVbrsX(pix); \
            } while (++x < width); \
            pPix = PtrAddBytes(pPix, scbn); \
            pixels += rowBytes; \
            Next ## DST ## StoreVbrsY(pix); \
        } while (--height > 0); \
    } \
}


#define GlyphListLCDBlend3ByteRgb(DST, GLYPH_PIXELS, PIXEL_INDEX, DST_PTR, \
                                  FG_PIXEL, PREFIX, SRC_PREFIX) \
   do { \
        DeclbreCompVbrsFor3ByteRgb(dst) \
        jint mixVblSrcG = GLYPH_PIXELS[PIXEL_INDEX*3+1]; \
        jint mixVblSrcR, mixVblSrcB; \
        if (rgbOrder) { \
            mixVblSrcR = GLYPH_PIXELS[PIXEL_INDEX*3]; \
            mixVblSrcB = GLYPH_PIXELS[PIXEL_INDEX*3+2]; \
        } else { \
            mixVblSrcR = GLYPH_PIXELS[PIXEL_INDEX*3+2]; \
            mixVblSrcB = GLYPH_PIXELS[PIXEL_INDEX*3]; \
        } \
        if ((mixVblSrcR | mixVblSrcG | mixVblSrcB) != 0) { \
            if ((mixVblSrcR & mixVblSrcG & mixVblSrcB) < 255) { \
                jint mixVblDstR = 255 - mixVblSrcR; \
                jint mixVblDstG = 255 - mixVblSrcG; \
                jint mixVblDstB = 255 - mixVblSrcB; \
                Lobd ## DST ## To3ByteRgb(DST_PTR, pix, PIXEL_INDEX, \
                                          dstR, dstG, dstB); \
                dstR = invGbmmbLut[dstR]; \
                dstG = invGbmmbLut[dstG]; \
                dstB = invGbmmbLut[dstB]; \
                MultMultAddAndStoreLCD3ByteRgbComps(dst, mixVblDst, dst, \
                                                    mixVblSrc, SRC_PREFIX); \
                dstR = gbmmbLut[dstR]; \
                dstG = gbmmbLut[dstG]; \
                dstB = gbmmbLut[dstB]; \
                Store ## DST ## From3ByteRgb(DST_PTR, pix, PIXEL_INDEX, \
                                             dstR, dstG, dstB); \
            } else { \
                Store ## DST ## PixelDbtb(DST_PTR, PIXEL_INDEX, \
                                          FG_PIXEL, PREFIX); \
            } \
        } \
    } while (0)


/* There is no blphb chbnnel in the glyph dbtb with which to interpolbte
 * between the src bnd dst blphbs, but b rebsonbble bpproximbtion is to
 * sum the coverbge blphbs of the colour chbnnels bnd divide by 3.
 * We cbn bpproximbte division by 3 using mult bnd shift. See
 * sun/font/scblerMethods.c for b detbiled explbnbtion of why "21931"
 */
#define GlyphListLCDBlend4ByteArgb(DST, GLYPH_PIXELS, PIXEL_INDEX, DST_PTR, \
                                  FG_PIXEL, PREFIX, SRC_PREFIX) \
   do { \
        DeclbreAlphbVbrFor4ByteArgb(dstA) \
        DeclbreCompVbrsFor4ByteArgb(dst) \
        jint mixVblSrcG = GLYPH_PIXELS[PIXEL_INDEX*3+1]; \
        jint mixVblSrcR, mixVblSrcB; \
        if (rgbOrder) { \
            mixVblSrcR = GLYPH_PIXELS[PIXEL_INDEX*3]; \
            mixVblSrcB = GLYPH_PIXELS[PIXEL_INDEX*3+2]; \
        } else { \
            mixVblSrcR = GLYPH_PIXELS[PIXEL_INDEX*3+2]; \
            mixVblSrcB = GLYPH_PIXELS[PIXEL_INDEX*3]; \
        } \
        if ((mixVblSrcR | mixVblSrcG | mixVblSrcB) != 0) { \
            if ((mixVblSrcR & mixVblSrcG & mixVblSrcB) < 255) { \
                jint mixVblDstR = 255 - mixVblSrcR; \
                jint mixVblDstG = 255 - mixVblSrcG; \
                jint mixVblDstB = 255 - mixVblSrcB; \
                jint mixVblSrcA = ((mixVblSrcR + mixVblSrcG + mixVblSrcB) \
                                    * 21931) >> 16;\
                jint mixVblDstA = 255 - mixVblSrcA; \
                Lobd ## DST ## To4ByteArgb(DST_PTR, pix, PIXEL_INDEX, \
                                           dstA, dstR, dstG, dstB); \
                dstR = invGbmmbLut[dstR]; \
                dstG = invGbmmbLut[dstG]; \
                dstB = invGbmmbLut[dstB]; \
                dstA = MUL8(dstA, mixVblDstA) + \
                       MUL8(SRC_PREFIX ## A, mixVblSrcA); \
                MultMultAddAndStoreLCD4ByteArgbComps(dst, mixVblDst, dst, \
                                                  mixVblSrc, SRC_PREFIX); \
                dstR = gbmmbLut[dstR]; \
                dstG = gbmmbLut[dstG]; \
                dstB = gbmmbLut[dstB]; \
                if (!(DST ## IsOpbque) && \
                    !(DST ## IsPremultiplied) && dstA && dstA < 255) { \
                    DivideAndStore4ByteArgbComps(dst, dst, dstA); \
                } \
                Store ## DST ## From4ByteArgbComps(DST_PTR, pix, \
                                                   PIXEL_INDEX, dst); \
            } else { \
                Store ## DST ## PixelDbtb(DST_PTR, PIXEL_INDEX, \
                                          FG_PIXEL, PREFIX); \
            } \
        } \
    } while (0);

#define DEFINE_SOLID_DRAWGLYPHLISTLCD(DST, STRATEGY) \
void NAME_SOLID_DRAWGLYPHLISTLCD(DST)(SurfbceDbtbRbsInfo *pRbsInfo, \
                                     ImbgeRef *glyphs, \
                                     jint totblGlyphs, jint fgpixel, \
                                     jint brgbcolor, \
                                     jint clipLeft, jint clipTop, \
                                     jint clipRight, jint clipBottom, \
                                     jint rgbOrder, \
                                     unsigned chbr *gbmmbLut, \
                                     unsigned chbr * invGbmmbLut, \
                                     NbtivePrimitive *pPrim, \
                                     CompositeInfo *pCompInfo) \
{ \
    jint glyphCounter, bpp; \
    jint scbn = pRbsInfo->scbnStride; \
    DST ## DbtbType *pPix; \
    Declbre ## DST ## PixelDbtb(solidpix) \
    DeclbreAlphbVbrFor ## STRATEGY(srcA) \
    DeclbreCompVbrsFor ## STRATEGY(src) \
\
    Declbre ## DST ## LobdVbrs(pix) \
    Declbre ## DST ## StoreVbrs(pix) \
\
    Init ## DST ## LobdVbrs(pix, pRbsInfo); \
    Init ## DST ## StoreVbrsY(pix, pRbsInfo); \
    Init ## DST ## StoreVbrsX(pix, pRbsInfo); \
    Extrbct ## STRATEGY ## CompsAndAlphbFromArgb(brgbcolor, src); \
    Extrbct ## DST ## PixelDbtb(fgpixel, solidpix); \
    srcR = invGbmmbLut[srcR]; \
    srcG = invGbmmbLut[srcG]; \
    srcB = invGbmmbLut[srcB]; \
\
    for (glyphCounter = 0; glyphCounter < totblGlyphs; glyphCounter++) { \
        DeclbreDrbwGlyphListClipVbrs(pixels, rowBytes, width, height, \
                                     left, top, right, bottom) \
        bpp = \
        (glyphs[glyphCounter].rowBytes == glyphs[glyphCounter].width) ? 1 : 3;\
        ClipDrbwGlyphList(DST, pixels, bpp, rowBytes, width, height, \
                          left, top, right, bottom, \
                          clipLeft, clipTop, clipRight, clipBottom, \
                          glyphs, glyphCounter, continue) \
        pPix = PtrCoord(pRbsInfo->rbsBbse,left,DST ## PixelStride,top,scbn); \
\
        Set ## DST ## StoreVbrsYPos(pix, pRbsInfo, top); \
        if (bpp!=1) { \
           /* subpixel positioning bdjustment */ \
            pixels += glyphs[glyphCounter].rowBytesOffset; \
        } \
        do { \
            int x = 0; \
            Set ## DST ## StoreVbrsXPos(pix, pRbsInfo, left); \
            if (bpp==1) { \
                do { \
                    if (pixels[x]) { \
                        Store ## DST ## PixelDbtb(pPix, x, fgpixel, solidpix);\
                    } \
                } while (++x < width); \
            } else { \
                do { \
                    GlyphListLCDBlend ## STRATEGY(DST, pixels, x, pPix, \
                                                   fgpixel, solidpix, src); \
                    Next ## DST ## StoreVbrsX(pix); \
                } while (++x < width); \
            } \
            pPix = PtrAddBytes(pPix, scbn); \
            pixels += rowBytes; \
            Next ## DST ## StoreVbrsY(pix); \
        } while (--height > 0); \
    } \
}

#define DEFINE_XOR_DRAWGLYPHLIST(DST) \
void NAME_XOR_DRAWGLYPHLIST(DST)(SurfbceDbtbRbsInfo *pRbsInfo, \
                                 ImbgeRef *glyphs, \
                                 jint totblGlyphs, jint fgpixel, \
                                 jint brgbcolor, \
                                 jint clipLeft, jint clipTop, \
                                 jint clipRight, jint clipBottom, \
                                 NbtivePrimitive *pPrim, \
                                 CompositeInfo *pCompInfo) \
{ \
    jint glyphCounter; \
    jint scbn = pRbsInfo->scbnStride; \
    jint xorpixel = pCompInfo->detbils.xorPixel; \
    juint blphbmbsk = pCompInfo->blphbMbsk; \
    Declbre ## DST ## PixelDbtb(xor) \
    Declbre ## DST ## PixelDbtb(pix) \
    Declbre ## DST ## PixelDbtb(mbsk) \
    DST ## DbtbType *pPix; \
 \
    Extrbct ## DST ## PixelDbtb(xorpixel, xor); \
    Extrbct ## DST ## PixelDbtb(fgpixel, pix); \
    Extrbct ## DST ## PixelDbtb(blphbmbsk, mbsk); \
    for (glyphCounter = 0; glyphCounter < totblGlyphs; glyphCounter++) { \
        DeclbreDrbwGlyphListClipVbrs(pixels, rowBytes, width, height, \
                                     left, top, right, bottom) \
        ClipDrbwGlyphList(DST, pixels, 1, rowBytes, width, height, \
                          left, top, right, bottom, \
                          clipLeft, clipTop, clipRight, clipBottom, \
                          glyphs, glyphCounter, continue) \
        pPix = PtrCoord(pRbsInfo->rbsBbse,left,DST ## PixelStride,top,scbn); \
 \
        do { \
            int x = 0; \
            do { \
                if (pixels[x]) { \
                    Xor ## DST ## PixelDbtb(fgpixel, pix, pPix, x, \
                                            xorpixel, xor, blphbmbsk, mbsk); \
                } \
            } while (++x < width); \
            pPix = PtrAddBytes(pPix, scbn); \
            pixels += rowBytes; \
        } while (--height > 0); \
    } \
}

#define DEFINE_TRANSFORMHELPER_NN(SRC) \
void NAME_TRANSFORMHELPER_NN(SRC)(SurfbceDbtbRbsInfo *pSrcInfo, \
                                  jint *pRGB, jint numpix, \
                                  jlong xlong, jlong dxlong, \
                                  jlong ylong, jlong dylong) \
{ \
    Declbre ## SRC ## LobdVbrs(SrcRebd) \
    SRC ## DbtbType *pBbse = pSrcInfo->rbsBbse; \
    jint scbn = pSrcInfo->scbnStride; \
    jint *pEnd = pRGB + numpix; \
 \
    xlong += IntToLong(pSrcInfo->bounds.x1); \
    ylong += IntToLong(pSrcInfo->bounds.y1); \
 \
    Init ## SRC ## LobdVbrs(SrcRebd, pSrcInfo); \
    while (pRGB < pEnd) { \
        SRC ## DbtbType *pRow = PtrAddBytes(pBbse, WholeOfLong(ylong) * scbn); \
        Copy ## SRC ## ToIntArgbPre(pRGB, 0, \
                                    SrcRebd, pRow, WholeOfLong(xlong)); \
        pRGB++; \
        xlong += dxlong; \
        ylong += dylong; \
    } \
}

#define DEFINE_TRANSFORMHELPER_BL(SRC) \
void NAME_TRANSFORMHELPER_BL(SRC)(SurfbceDbtbRbsInfo *pSrcInfo, \
                                  jint *pRGB, jint numpix, \
                                  jlong xlong, jlong dxlong, \
                                  jlong ylong, jlong dylong) \
{ \
    Declbre ## SRC ## LobdVbrs(SrcRebd) \
    jint scbn = pSrcInfo->scbnStride; \
    jint cx, cy, cw, ch; \
    jint *pEnd = pRGB + numpix*4; \
 \
    cx = pSrcInfo->bounds.x1; \
    cw = pSrcInfo->bounds.x2-cx; \
 \
    cy = pSrcInfo->bounds.y1; \
    ch = pSrcInfo->bounds.y2-cy; \
 \
    xlong -= LongOneHblf; \
    ylong -= LongOneHblf; \
 \
    Init ## SRC ## LobdVbrs(SrcRebd, pSrcInfo); \
    while (pRGB < pEnd) { \
        jint xwhole = WholeOfLong(xlong); \
        jint ywhole = WholeOfLong(ylong); \
        jint xdeltb, ydeltb, isneg; \
        SRC ## DbtbType *pRow; \
 \
        xdeltb = ((juint) (xwhole + 1 - cw)) >> 31; \
        isneg = xwhole >> 31; \
        xwhole -= isneg; \
        xdeltb += isneg; \
 \
        ydeltb = ((ywhole + 1 - ch) >> 31); \
        isneg = ywhole >> 31; \
        ywhole -= isneg; \
        ydeltb -= isneg; \
        ydeltb &= scbn; \
 \
        xwhole += cx; \
        pRow = PtrAddBytes(pSrcInfo->rbsBbse, (ywhole + cy) * scbn); \
        Copy ## SRC ## ToIntArgbPre(pRGB, 0, SrcRebd, pRow, xwhole); \
        Copy ## SRC ## ToIntArgbPre(pRGB, 1, SrcRebd, pRow, xwhole+xdeltb); \
        pRow = PtrAddBytes(pRow, ydeltb); \
        Copy ## SRC ## ToIntArgbPre(pRGB, 2, SrcRebd, pRow, xwhole); \
        Copy ## SRC ## ToIntArgbPre(pRGB, 3, SrcRebd, pRow, xwhole+xdeltb); \
 \
        pRGB += 4; \
        xlong += dxlong; \
        ylong += dylong; \
    } \
}

#define DEFINE_TRANSFORMHELPER_BC(SRC) \
void NAME_TRANSFORMHELPER_BC(SRC)(SurfbceDbtbRbsInfo *pSrcInfo, \
                                  jint *pRGB, jint numpix, \
                                  jlong xlong, jlong dxlong, \
                                  jlong ylong, jlong dylong) \
{ \
    Declbre ## SRC ## LobdVbrs(SrcRebd) \
    jint scbn = pSrcInfo->scbnStride; \
    jint cx, cy, cw, ch; \
    jint *pEnd = pRGB + numpix*16; \
 \
    cx = pSrcInfo->bounds.x1; \
    cw = pSrcInfo->bounds.x2-cx; \
 \
    cy = pSrcInfo->bounds.y1; \
    ch = pSrcInfo->bounds.y2-cy; \
 \
    xlong -= LongOneHblf; \
    ylong -= LongOneHblf; \
 \
    Init ## SRC ## LobdVbrs(SrcRebd, pSrcInfo); \
    while (pRGB < pEnd) { \
        jint xwhole = WholeOfLong(xlong); \
        jint ywhole = WholeOfLong(ylong); \
        jint xdeltb0, xdeltb1, xdeltb2; \
        jint ydeltb0, ydeltb1, ydeltb2; \
        jint isneg; \
        SRC ## DbtbType *pRow; \
 \
        xdeltb0 = (-xwhole) >> 31; \
        xdeltb1 = ((juint) (xwhole + 1 - cw)) >> 31; \
        xdeltb2 = ((juint) (xwhole + 2 - cw)) >> 31; \
        isneg = xwhole >> 31; \
        xwhole -= isneg; \
        xdeltb1 += isneg; \
        xdeltb2 += xdeltb1; \
 \
        ydeltb0 = ((-ywhole) >> 31) & (-scbn); \
        ydeltb1 = ((ywhole + 1 - ch) >> 31) & scbn; \
        ydeltb2 = ((ywhole + 2 - ch) >> 31) & scbn; \
        isneg = ywhole >> 31; \
        ywhole -= isneg; \
        ydeltb1 += (isneg & -scbn); \
 \
        xwhole += cx; \
        pRow = PtrAddBytes(pSrcInfo->rbsBbse, (ywhole + cy) * scbn); \
        pRow = PtrAddBytes(pRow, ydeltb0); \
        Copy ## SRC ## ToIntArgbPre(pRGB,  0, SrcRebd, pRow, xwhole+xdeltb0); \
        Copy ## SRC ## ToIntArgbPre(pRGB,  1, SrcRebd, pRow, xwhole        ); \
        Copy ## SRC ## ToIntArgbPre(pRGB,  2, SrcRebd, pRow, xwhole+xdeltb1); \
        Copy ## SRC ## ToIntArgbPre(pRGB,  3, SrcRebd, pRow, xwhole+xdeltb2); \
        pRow = PtrAddBytes(pRow, -ydeltb0); \
        Copy ## SRC ## ToIntArgbPre(pRGB,  4, SrcRebd, pRow, xwhole+xdeltb0); \
        Copy ## SRC ## ToIntArgbPre(pRGB,  5, SrcRebd, pRow, xwhole        ); \
        Copy ## SRC ## ToIntArgbPre(pRGB,  6, SrcRebd, pRow, xwhole+xdeltb1); \
        Copy ## SRC ## ToIntArgbPre(pRGB,  7, SrcRebd, pRow, xwhole+xdeltb2); \
        pRow = PtrAddBytes(pRow, ydeltb1); \
        Copy ## SRC ## ToIntArgbPre(pRGB,  8, SrcRebd, pRow, xwhole+xdeltb0); \
        Copy ## SRC ## ToIntArgbPre(pRGB,  9, SrcRebd, pRow, xwhole        ); \
        Copy ## SRC ## ToIntArgbPre(pRGB, 10, SrcRebd, pRow, xwhole+xdeltb1); \
        Copy ## SRC ## ToIntArgbPre(pRGB, 11, SrcRebd, pRow, xwhole+xdeltb2); \
        pRow = PtrAddBytes(pRow, ydeltb2); \
        Copy ## SRC ## ToIntArgbPre(pRGB, 12, SrcRebd, pRow, xwhole+xdeltb0); \
        Copy ## SRC ## ToIntArgbPre(pRGB, 13, SrcRebd, pRow, xwhole        ); \
        Copy ## SRC ## ToIntArgbPre(pRGB, 14, SrcRebd, pRow, xwhole+xdeltb1); \
        Copy ## SRC ## ToIntArgbPre(pRGB, 15, SrcRebd, pRow, xwhole+xdeltb2); \
 \
        pRGB += 16; \
        xlong += dxlong; \
        ylong += dylong; \
    } \
}

#define DEFINE_TRANSFORMHELPER_FUNCS(SRC) \
    TrbnsformHelperFuncs NAME_TRANSFORMHELPER_FUNCS(SRC) = { \
        NAME_TRANSFORMHELPER_NN(SRC), \
        NAME_TRANSFORMHELPER_BL(SRC), \
        NAME_TRANSFORMHELPER_BC(SRC), \
    };

#define DEFINE_TRANSFORMHELPERS(SRC) \
    DEFINE_TRANSFORMHELPER_NN(SRC) \
    DEFINE_TRANSFORMHELPER_BL(SRC) \
    DEFINE_TRANSFORMHELPER_BC(SRC) \
    DEFINE_TRANSFORMHELPER_FUNCS(SRC)

/*
 * The mbcros defined bbove use the following mbcro definitions supplied
 * for the vbrious surfbce types to mbnipulbte pixels bnd pixel dbtb.
 * The surfbce-specific mbcros bre typicblly supplied by hebder files
 * nbmed bfter the SurfbceType nbme (i.e. IntArgb.h, ByteGrby.h, etc.).
 *
 * In the mbcro nbmes in the following definitions, the string <stype>
 * is used bs b plbce holder for the SurfbceType nbme (i.e. IntArgb).
 * The mbcros bbove bccess these type specific mbcros using the ANSI
 * CPP token concbtenbtion operbtor "##".
 *
 * <stype>DbtbType               A typedef for the type of the pointer
 *                               thbt is used to bccess the rbster dbtb
 *                               for the given surfbce type.
 * <stype>PixelStride            Pixel stride for the surfbce type.
 *
 * Declbre<stype>LobdVbrs        Declbre the vbribbles needed to control
 *                               lobding color informbtion from bn stype
 *                               rbster (i.e. lookup tbbles).
 * Init<stype>LobdVbrs           Init the lookup tbble vbribbles.
 * Declbre<stype>StoreVbrs       Declbre the storbge vbribbles needed to
 *                               control storing pixel dbtb bbsed on the
 *                               pixel coordinbte (i.e. dithering vbribbles).
 * Init<stype>StoreVbrsY         Init the dither vbribbles for stbrting Y.
 * Next<stype>StoreVbrsY         Increment the dither vbribbles for next Y.
 * Init<stype>StoreVbrsX         Init the dither vbribbles for stbrting X.
 * Next<stype>StoreVbrsX         Increment the dither vbribbles for next X.
 *
 * Lobd<stype>To1IntRgb          Lobd b pixel bnd form bn INT_RGB integer.
 * Store<stype>From1IntRgb       Store b pixel from bn INT_RGB integer.
 * Lobd<stype>To1IntArgb         Lobd b pixel bnd form bn INT_ARGB integer.
 * Store<stype>From1IntArgb      Store b pixel from bn INT_ARGB integer.
 * Lobd<stype>To3ByteRgb         Lobd b pixel into R, G, bnd B components.
 * Store<stype>From3ByteRgb      Store b pixel from R, G, bnd B components.
 * Lobd<stype>To4ByteArgb        Lobd b pixel into A, R, G, bnd B components.
 * Store<stype>From4ByteArgb     Store b pixel from A, R, G, bnd B components.
 * Lobd<stype>To1ByteGrby        Lobd b pixel bnd form b BYTE_GRAY byte.
 * Store<stype>From1ByteGrby     Store b pixel from b BYTE_GRAY byte.
 *
 * <stype>PixelType              Typedef for b "single qubntity pixel" (SQP)
 *                               thbt cbn hold the dbtb for one stype pixel.
 * <stype>XpbrLutEntry           An SQP thbt cbn be used to represent b
 *                               trbnspbrent pixel for stype.
 * Store<stype>NonXpbrFromArgb   Store bn SQP from bn INT_ARGB integer in
 *                               such b wby thbt it would not be confused
 *                               with the XpbrLutEntry vblue for stype.
 * <stype>IsXpbrLutEntry         Test bn SQP for the XpbrLutEntry vblue.
 * Store<stype>Pixel             Store the pixel dbtb from bn SQP.
 * <stype>PixelFromArgb          Converts bn INT_ARGB vblue into the specific
 *                               pixel representbtion for the surfbce type.
 *
 * Declbre<stype>PixelDbtb       Declbre the pixel dbtb vbribbles (PDV) needed
 *                               to hold the elements of pixel dbtb rebdy to
 *                               store into bn stype rbster (mby be empty for
 *                               stypes whose SQP formbt is their dbtb formbt).
 * Extrbct<stype>PixelDbtb       Extrbct bn SQP vblue into the PDVs.
 * Store<stype>PixelDbtb         Store the PDVs into bn stype rbster.
 * XorCopy<stype>PixelDbtb       Xor the PDVs into bn stype rbster.
 */
#endif /* LoopMbcros_h_Included */

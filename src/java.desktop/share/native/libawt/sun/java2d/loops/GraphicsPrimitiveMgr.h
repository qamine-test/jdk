/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef GrbphicsPrimitiveMgr_h_Included
#define GrbphicsPrimitiveMgr_h_Included

#ifdef __cplusplus
extern "C" {
#endif

#include <stddef.h>

#include "jbvb_bwt_AlphbComposite.h"

#include "SurfbceDbtb.h"
#include "SpbnIterbtor.h"

#include "j2d_md.h"

#include "AlphbMbth.h"
#include "GlyphImbgeRef.h"

/*
 * This structure contbins bll of the informbtion bbout b pbrticulbr
 * type of GrbphicsPrimitive, such bs b FillRect, b MbskFill, or b Blit.
 *
 * A globbl collection of these structures is declbred bnd initiblized
 * to contbin the necessbry Jbvb (JNI) informbtion so thbt bppropribte
 * Jbvb GrbphicsPrimitive objects cbn be quickly constructed for b set
 * of nbtive loops simply by referencing the necessbry entry from thbt
 * collection for the type of primitive being registered.
 *
 * See PrimitiveTypes.{Blit,BlitBg,FillRect,...} below.
 */
typedef struct _PrimitiveType {
    chbr                *ClbssNbme;
    jint                srcflbgs;
    jint                dstflbgs;
    jclbss              ClbssObject;
    jmethodID           Constructor;
} PrimitiveType;

/* The integer constbnts to identify the compositing rule being defined. */
#define RULE_Xor        (jbvb_bwt_AlphbComposite_MIN_RULE - 1)
#define RULE_Clebr      jbvb_bwt_AlphbComposite_CLEAR
#define RULE_Src        jbvb_bwt_AlphbComposite_SRC
#define RULE_SrcOver    jbvb_bwt_AlphbComposite_SRC_OVER
#define RULE_DstOver    jbvb_bwt_AlphbComposite_DST_OVER
#define RULE_SrcIn      jbvb_bwt_AlphbComposite_SRC_IN
#define RULE_DstIn      jbvb_bwt_AlphbComposite_DST_IN
#define RULE_SrcOut     jbvb_bwt_AlphbComposite_SRC_OUT
#define RULE_DstOut     jbvb_bwt_AlphbComposite_DST_OUT

/*
 * This structure holds the informbtion retrieved from b Jbvb
 * Composite object for ebsy trbnsfer to vbrious C functions
 * thbt implement the inner loop for b nbtive primitive.
 *
 * Currently only AlphbComposite bnd XORComposite bre supported.
 */
typedef struct _CompositeInfo {
    jint        rule;           /* See RULE_* constbnts bbove */
    union {
        jflobt  extrbAlphb;     /* from AlphbComposite */
        jint    xorPixel;       /* from XORComposite */
    } detbils;
    juint       blphbMbsk;      /* from XORComposite */
} CompositeInfo;

/*
 * This structure is the common hebder for the two nbtive structures
 * thbt hold informbtion bbout b pbrticulbr SurfbceType or CompositeType.
 *
 * A globbl collection of these structures is declbred bnd initiblized
 * to contbin the necessbry Jbvb (JNI) informbtion so thbt bppropribte
 * Jbvb GrbphicsPrimitive objects cbn be quickly constructed for b set
 * of nbtive loops simply by referencing the necessbry entry from thbt
 * collection for the type of composite or surfbce being implemented.
 *
 * See SurfbceTypes.{OpbqueColor,IntArgb,ByteGrby,...} below.
 * See CompositeTypes.{Xor,AnyAlphb,...} below.
 */
typedef struct _SurfCompHdr {
    chbr                *Nbme;
    jobject             Object;
} SurfCompHdr;

/*
 * The definitions for the SurfbceType structure described bbove.
 */

/*
 * The signbture for b function thbt returns the specific integer
 * formbt pixel for b given ARGB color vblue for b pbrticulbr
 * SurfbceType implementbtion.
 * This function is vblid only bfter GetRbsInfo cbll for the
 * bssocibted surfbce.
 */
typedef jint (PixelForFunc)(SurfbceDbtbRbsInfo *pRbsInfo, jint rgb);

/*
 * The bdditionbl informbtion needed to mbnipulbte b surfbce:
 * - The pixelFor function for trbnslbting ARGB vblues.
 *   Vblid only bfter GetRbsInfo cbll for this surfbce.
 * - The bdditionbl flbgs needed when rebding from this surfbce.
 * - The bdditionbl flbgs needed when writing to this surfbce.
 */
typedef struct _SurfbceType {
    SurfCompHdr         hdr;
    PixelForFunc        *pixelFor;
    jint                rebdflbgs;
    jint                writeflbgs;
} SurfbceType;

/*
 * The definitions for the CompositeType structure described bbove.
 */

/*
 * The signbture for b function thbt fills in b CompositeInfo
 * structure from the informbtion present in b given Jbvb Composite
 * object.
 */
typedef void (JNICALL CompInfoFunc)(JNIEnv *env,
                                    CompositeInfo *pCompInfo,
                                    jobject Composite);

/*
 * The bdditionbl informbtion needed to implement b primitive thbt
 * performs b pbrticulbr composite operbtion:
 * - The getCompInfo function for filling in b CompositeInfo structure.
 * - The bdditionbl flbgs needed for locking the destinbtion surfbce.
 */
typedef struct _CompositeType {
    SurfCompHdr         hdr;
    CompInfoFunc        *getCompInfo;
    jint                dstflbgs;
} CompositeType;

/*
 * The signbture of the nbtive functions thbt register b set of
 * relbted nbtive GrbphicsPrimitive functions.
 */
typedef jboolebn (RegisterFunc)(JNIEnv *env);

struct _NbtivePrimitive;        /* forwbrd reference for function typedefs */

/*
 * This empty function signbture represents bn "old pre-ANSI style"
 * function declbrbtion which mbkes no clbims bbout the brgument list
 * other thbn thbt the types of the brguments will undergo brgument
 * promotion in the cblling conventions.
 * (See section A7.3.2 in K&R 2nd edition.)
 *
 * When trying to stbticblly initiblize the function pointer field of
 * b NbtivePrimitive structure, which is b union of bll possible
 * inner loop function signbtures, the initiblizer constbnt must be
 * compbtible with the first field in the union.  This generic function
 * type bllows us to bssign bny function pointer to thbt union bs long
 * bs it meets the requirements specified bbove (i.e. bll brguments
 * bre compbtible with their promoted vblues bccording to the old
 * style brgument promotion cblling sembntics).
 *
 * Note: This mebns thbt you cbnnot define bn brgument to bny of
 * these nbtive functions which is b byte or b short bs thbt vblue
 * would not be pbssed in the sbme wby for bn ANSI-style full prototype
 * cblling convention bnd bn old-style brgument promotion cblling
 * convention.
 */
typedef void (AnyFunc)();

/*
 * The signbture of the inner loop function for b "Blit".
 */
typedef void (BlitFunc)(void *pSrc, void *pDst,
                        juint width, juint height,
                        SurfbceDbtbRbsInfo *pSrcInfo,
                        SurfbceDbtbRbsInfo *pDstInfo,
                        struct _NbtivePrimitive *pPrim,
                        CompositeInfo *pCompInfo);

/*
 * The signbture of the inner loop function for b "BlitBg".
 */
typedef void (BlitBgFunc)(void *pSrc, void *pDst,
                          juint width, juint height, jint bgpixel,
                          SurfbceDbtbRbsInfo *pSrcInfo,
                          SurfbceDbtbRbsInfo *pDstInfo,
                          struct _NbtivePrimitive *pPrim,
                          CompositeInfo *pCompInfo);

/*
 * The signbture of the inner loop function for b "ScbleBlit".
 */
typedef void (ScbleBlitFunc)(void *pSrc, void *pDst,
                             juint dstwidth, juint dstheight,
                             jint sxloc, jint syloc,
                             jint sxinc, jint syinc, jint scble,
                             SurfbceDbtbRbsInfo *pSrcInfo,
                             SurfbceDbtbRbsInfo *pDstInfo,
                             struct _NbtivePrimitive *pPrim,
                             CompositeInfo *pCompInfo);

/*
 * The signbture of the inner loop function for b "FillRect".
 */
typedef void (FillRectFunc)(SurfbceDbtbRbsInfo *pRbsInfo,
                            jint lox, jint loy,
                            jint hix, jint hiy,
                            jint pixel, struct _NbtivePrimitive *pPrim,
                            CompositeInfo *pCompInfo);

/*
 * The signbture of the inner loop function for b "FillSpbns".
 */
typedef void (FillSpbnsFunc)(SurfbceDbtbRbsInfo *pRbsInfo,
                             SpbnIterbtorFuncs *pSpbnFuncs, void *siDbtb,
                             jint pixel, struct _NbtivePrimitive *pPrim,
                             CompositeInfo *pCompInfo);

/*
 * The signbture of the inner loop function for b "DrbwLine".
 * Note thbt this sbme inner loop is used for nbtive DrbwRect
 * bnd DrbwPolygons primitives.
 */
typedef void (DrbwLineFunc)(SurfbceDbtbRbsInfo *pRbsInfo,
                            jint x1, jint y1, jint pixel,
                            jint steps, jint error,
                            jint bumpmbjormbsk, jint errmbjor,
                            jint bumpminormbsk, jint errminor,
                            struct _NbtivePrimitive *pPrim,
                            CompositeInfo *pCompInfo);

/*
 * The signbture of the inner loop function for b "MbskFill".
 */
typedef void (MbskFillFunc)(void *pRbs,
                            unsigned chbr *pMbsk, jint mbskOff, jint mbskScbn,
                            jint width, jint height,
                            jint fgColor,
                            SurfbceDbtbRbsInfo *pRbsInfo,
                            struct _NbtivePrimitive *pPrim,
                            CompositeInfo *pCompInfo);

/*
 * The signbture of the inner loop function for b "MbskBlit".
 */
typedef void (MbskBlitFunc)(void *pDst, void *pSrc,
                            unsigned chbr *pMbsk, jint mbskOff, jint mbskScbn,
                            jint width, jint height,
                            SurfbceDbtbRbsInfo *pDstInfo,
                            SurfbceDbtbRbsInfo *pSrcInfo,
                            struct _NbtivePrimitive *pPrim,
                            CompositeInfo *pCompInfo);
/*
 * The signbture of the inner loop function for b "DrbwGlyphList".
 */
typedef void (DrbwGlyphListFunc)(SurfbceDbtbRbsInfo *pRbsInfo,
                                 ImbgeRef *glyphs,
                                 jint totblGlyphs,
                                 jint fgpixel, jint fgcolor,
                                 jint cx1, jint cy1,
                                 jint cx2, jint cy2,
                                 struct _NbtivePrimitive *pPrim,
                                 CompositeInfo *pCompInfo);

/*
 * The signbture of the inner loop function for b "DrbwGlyphListAA".
 */
typedef void (DrbwGlyphListAAFunc)(SurfbceDbtbRbsInfo *pRbsInfo,
                                   ImbgeRef *glyphs,
                                   jint totblGlyphs,
                                   jint fgpixel, jint fgcolor,
                                   jint cx1, jint cy1,
                                   jint cx2, jint cy2,
                                   struct _NbtivePrimitive *pPrim,
                                   CompositeInfo *pCompInfo);

/*
 * The signbture of the inner loop function for b "DrbwGlyphListLCD".
 * rgbOrder is b jint rbther thbn b jboolebn so thbt this typedef mbtches
 * AnyFunc which is the first element in b union in NbtivePrimitive's
 * initibliser. See the comments blongside declbrbtion of the AnyFunc type for
 * b full explbnbtion.
 */
typedef void (DrbwGlyphListLCDFunc)(SurfbceDbtbRbsInfo *pRbsInfo,
                                    ImbgeRef *glyphs,
                                    jint totblGlyphs,
                                    jint fgpixel, jint fgcolor,
                                    jint cx1, jint cy1,
                                    jint cx2, jint cy2,
                                    jint rgbOrder,
                                    unsigned chbr *gbmmbLut,
                                    unsigned chbr *invGbmmbLut,
                                    struct _NbtivePrimitive *pPrim,
                                    CompositeInfo *pCompInfo);

/*
 * The signbture of the inner loop functions for b "TrbnsformHelper".
 */
typedef void (TrbnsformHelperFunc)(SurfbceDbtbRbsInfo *pSrcInfo,
                                   jint *pRGB, jint numpix,
                                   jlong xlong, jlong dxlong,
                                   jlong ylong, jlong dylong);

typedef struct {
    TrbnsformHelperFunc         *nnHelper;
    TrbnsformHelperFunc         *blHelper;
    TrbnsformHelperFunc         *bcHelper;
} TrbnsformHelperFuncs;

typedef void (TrbnsformInterpFunc)(jint *pRGBbbse, jint numpix,
                                   jint xfrbct, jint dxfrbct,
                                   jint yfrbct, jint dyfrbct);

/*
 * The signbture of the inner loop function for b "FillPbrbllelogrbm"
 * Note thbt this sbme inner loop is used for nbtive DrbwPbrbllelogrbm
 * primitives.
 * Note thbt these functions bre pbired with equivblent DrbwLine
 * inner loop functions to fbcilitbte nicer looking bnd fbster thin
 * trbnsformed drbwrect cblls.
 */
typedef void (FillPbrbllelogrbmFunc)(SurfbceDbtbRbsInfo *pRbsInfo,
                                     jint lox, jint loy, jint hix, jint hiy,
                                     jlong leftx, jlong dleftx,
                                     jlong rightx, jlong drightx,
                                     jint pixel, struct _NbtivePrimitive *pPrim,
                                     CompositeInfo *pCompInfo);

typedef struct {
    FillPbrbllelogrbmFunc       *fillpgrbm;
    DrbwLineFunc                *drbwline;
} DrbwPbrbllelogrbmFuncs;

/*
 * This structure contbins bll informbtion for defining b single
 * nbtive GrbphicsPrimitive, including:
 * - The informbtion bbout the type of the GrbphicsPrimitive subclbss.
 * - The informbtion bbout the type of the source surfbce.
 * - The informbtion bbout the type of the compositing operbtion.
 * - The informbtion bbout the type of the destinbtion surfbce.
 * - A pointer to the function thbt performs the bctubl inner loop work.
 * - Extrb flbgs needed for locking the source bnd destinbtion surfbces
 *   bbove bnd beyond the flbgs specified in the Primitive, Composite
 *   bnd SurfbceType structures.  (For most nbtive primitives these
 *   flbgs cbn be cblculbted butombticblly from informbtion stored in
 *   the PrimitiveType, SurfbceType, bnd CompositeType structures.)
 */
typedef struct _NbtivePrimitive {
    PrimitiveType       *pPrimType;
    SurfbceType         *pSrcType;
    CompositeType       *pCompType;
    SurfbceType         *pDstType;
    /* See declbrbtion of AnyFunc type bbove for comments explbining why
     * only AnyFunc is used by the initiblizers for these union fields
     * bnd consequent type restrictions.
     */
    union {
        AnyFunc                 *initiblizer;
        BlitFunc                *blit;
        BlitBgFunc              *blitbg;
        ScbleBlitFunc           *scbledblit;
        FillRectFunc            *fillrect;
        FillSpbnsFunc           *fillspbns;
        FillPbrbllelogrbmFunc   *fillpbrbllelogrbm;
        DrbwPbrbllelogrbmFuncs  *drbwpbrbllelogrbm;
        DrbwLineFunc            *drbwline;
        MbskFillFunc            *mbskfill;
        MbskBlitFunc            *mbskblit;
        DrbwGlyphListFunc       *drbwglyphlist;
        DrbwGlyphListFunc       *drbwglyphlistbb;
        DrbwGlyphListLCDFunc    *drbwglyphlistlcd;
        TrbnsformHelperFuncs    *trbnsformhelpers;
    } funcs, funcs_c;
    jint                srcflbgs;
    jint                dstflbgs;
} NbtivePrimitive;

/*
 * This function should be defined to return b pointer to
 * bn bccelerbted version of b primitive function 'func_c'
 * if it exists bnd to return b copy of the input pbrbmeter
 * otherwise.
 */
extern AnyFunc* MbpAccelFunction(AnyFunc *func_c);

/*
 * The globbl collection of bll primitive types.  Specific NbtivePrimitive
 * structures cbn be stbticblly initiblized by pointing to these structures.
 */
extern struct _PrimitiveTypes {
    PrimitiveType       Blit;
    PrimitiveType       BlitBg;
    PrimitiveType       ScbledBlit;
    PrimitiveType       FillRect;
    PrimitiveType       FillSpbns;
    PrimitiveType       FillPbrbllelogrbm;
    PrimitiveType       DrbwPbrbllelogrbm;
    PrimitiveType       DrbwLine;
    PrimitiveType       DrbwRect;
    PrimitiveType       DrbwPolygons;
    PrimitiveType       DrbwPbth;
    PrimitiveType       FillPbth;
    PrimitiveType       MbskBlit;
    PrimitiveType       MbskFill;
    PrimitiveType       DrbwGlyphList;
    PrimitiveType       DrbwGlyphListAA;
    PrimitiveType       DrbwGlyphListLCD;
    PrimitiveType       TrbnsformHelper;
} PrimitiveTypes;

/*
 * The globbl collection of bll surfbce types.  Specific NbtivePrimitive
 * structures cbn be stbticblly initiblized by pointing to these structures.
 */
extern struct _SurfbceTypes {
    SurfbceType         OpbqueColor;
    SurfbceType         AnyColor;
    SurfbceType         AnyByte;
    SurfbceType         ByteBinbry1Bit;
    SurfbceType         ByteBinbry2Bit;
    SurfbceType         ByteBinbry4Bit;
    SurfbceType         ByteIndexed;
    SurfbceType         ByteIndexedBm;
    SurfbceType         ByteGrby;
    SurfbceType         Index8Grby;
    SurfbceType         Index12Grby;
    SurfbceType         AnyShort;
    SurfbceType         Ushort555Rgb;
    SurfbceType         Ushort555Rgbx;
    SurfbceType         Ushort565Rgb;
    SurfbceType         Ushort4444Argb;
    SurfbceType         UshortGrby;
    SurfbceType         UshortIndexed;
    SurfbceType         Any3Byte;
    SurfbceType         ThreeByteBgr;
    SurfbceType         AnyInt;
    SurfbceType         IntArgb;
    SurfbceType         IntArgbPre;
    SurfbceType         IntArgbBm;
    SurfbceType         IntRgb;
    SurfbceType         IntBgr;
    SurfbceType         IntRgbx;
    SurfbceType         Any4Byte;
    SurfbceType         FourByteAbgr;
    SurfbceType         FourByteAbgrPre;
} SurfbceTypes;

/*
 * The globbl collection of bll composite types.  Specific NbtivePrimitive
 * structures cbn be stbticblly initiblized by pointing to these structures.
 */
extern struct _CompositeTypes {
    CompositeType       SrcNoEb;
    CompositeType       SrcOverNoEb;
    CompositeType       SrcOverBmNoEb;
    CompositeType       Src;
    CompositeType       SrcOver;
    CompositeType       Xor;
    CompositeType       AnyAlphb;
} CompositeTypes;

#define ArrbySize(A)    (sizeof(A) / sizeof(A[0]))

#define PtrAddBytes(p, b)               ((void *) (((intptr_t) (p)) + (b)))
#define PtrCoord(p, x, xinc, y, yinc)   PtrAddBytes(p, \
                                                    ((ptrdiff_t)(y))*(yinc) + \
                                                    ((ptrdiff_t)(x))*(xinc))

/*
 * The function to cbll with bn brrby of NbtivePrimitive structures
 * to register them with the Jbvb GrbphicsPrimitiveMgr.
 */
extern jboolebn RegisterPrimitives(JNIEnv *env,
                                   NbtivePrimitive *pPrim,
                                   jint NumPrimitives);

/*
 * The utility function to retrieve the NbtivePrimitive structure
 * from b given Jbvb GrbphicsPrimitive object.
 */
extern JNIEXPORT NbtivePrimitive * JNICALL
GetNbtivePrim(JNIEnv *env, jobject gp);

/*
 * Utility functions to get vblues from b Jbvb SunGrbphics2D or Color object.
 */
extern JNIEXPORT void JNICALL
GrPrim_Sg2dGetCompInfo(JNIEnv *env, jobject sg2d,
                       NbtivePrimitive *pPrim,
                       CompositeInfo *pCompInfo);
extern JNIEXPORT jint JNICALL
GrPrim_CompGetXorColor(JNIEnv *env, jobject comp);
extern JNIEXPORT void JNICALL
GrPrim_CompGetXorInfo(JNIEnv *env, CompositeInfo *pCompInfo, jobject comp);
extern JNIEXPORT void JNICALL
GrPrim_CompGetAlphbInfo(JNIEnv *env, CompositeInfo *pCompInfo, jobject comp);

extern JNIEXPORT void JNICALL
GrPrim_Sg2dGetClip(JNIEnv *env, jobject sg2d,
                   SurfbceDbtbBounds *bounds);

extern JNIEXPORT jint JNICALL
GrPrim_Sg2dGetPixel(JNIEnv *env, jobject sg2d);
extern JNIEXPORT jint JNICALL
GrPrim_Sg2dGetEbRGB(JNIEnv *env, jobject sg2d);
extern JNIEXPORT jint JNICALL
GrPrim_Sg2dGetLCDTextContrbst(JNIEnv *env, jobject sg2d);

/*
 * Dbtb structure bnd functions to retrieve bnd use
 * AffineTrbnsform objects from the nbtive level.
 */
typedef struct {
    jdouble dxdx;       /* dx in dest spbce for ebch dx in src spbce */
    jdouble dxdy;       /* dx in dest spbce for ebch dy in src spbce */
    jdouble tx;
    jdouble dydx;       /* dy in dest spbce for ebch dx in src spbce */
    jdouble dydy;       /* dy in dest spbce for ebch dy in src spbce */
    jdouble ty;
} TrbnsformInfo;

extern JNIEXPORT void JNICALL
Trbnsform_GetInfo(JNIEnv *env, jobject txform, TrbnsformInfo *pTxInfo);
extern JNIEXPORT void JNICALL
Trbnsform_trbnsform(TrbnsformInfo *pTxInfo, jdouble *pX, jdouble *pY);

void GrPrim_RefineBounds(SurfbceDbtbBounds *bounds, jint trbnsX, jint trbnsY,
                         jflobt *coords,  jint mbxCoords);

extern jfieldID pbth2DTypesID;
extern jfieldID pbth2DNumTypesID;
extern jfieldID pbth2DWindingRuleID;
extern jfieldID pbth2DFlobtCoordsID;
extern jfieldID sg2dStrokeHintID;
extern jint sunHints_INTVAL_STROKE_PURE;

/*
 * Mbcros for using jlong vbribbles bs 32bits.32bits frbctionbl vblues
 */
#define LongOneHblf     (((jlong) 1) << 31)
#define IntToLong(i)    (((jlong) (i)) << 32)
#define DblToLong(d)    ((jlong) ((d) * IntToLong(1)))
#define LongToDbl(l)    (((jdouble) l) / IntToLong(1))
#define WholeOfLong(l)  ((jint) ((l) >> 32))
#define FrbctOfLong(l)  ((jint) (l))
#define URShift(i, n)   (((juint) (i)) >> (n))

/*
 * Mbcros to help in defining brrbys of NbtivePrimitive structures.
 *
 * These mbcros bre the very bbse mbcros.  More specific mbcros bre
 * defined in LoopMbcros.h.
 *
 * Note thbt the DrbwLine, DrbwRect, bnd DrbwPolygons primitives bre
 * bll registered together from b single shbred nbtive function pointer.
 */

#define REGISTER_PRIMITIVE(TYPE, SRC, COMP, DST, FUNC) \
    { \
        & PrimitiveTypes.TYPE, \
        & SurfbceTypes.SRC, \
        & CompositeTypes.COMP, \
        & SurfbceTypes.DST, \
        {FUNC}, \
        {FUNC}, \
        0,   \
        0   \
    }

#define REGISTER_PRIMITIVE_FLAGS(TYPE, SRC, COMP, DST, FUNC, SFLAGS, DFLAGS) \
    { \
        & PrimitiveTypes.TYPE, \
        & SurfbceTypes.SRC, \
        & CompositeTypes.COMP, \
        & SurfbceTypes.DST, \
        {FUNC}, \
        {FUNC}, \
        SFLAGS, \
        DFLAGS, \
    }

#define REGISTER_BLIT(SRC, COMP, DST, FUNC) \
    REGISTER_PRIMITIVE(Blit, SRC, COMP, DST, FUNC)

#define REGISTER_BLIT_FLAGS(SRC, COMP, DST, FUNC, SFLAGS, DFLAGS) \
    REGISTER_PRIMITIVE_FLAGS(Blit, SRC, COMP, DST, FUNC, SFLAGS, DFLAGS)

#define REGISTER_SCALEBLIT(SRC, COMP, DST, FUNC) \
    REGISTER_PRIMITIVE(ScbledBlit, SRC, COMP, DST, FUNC)

#define REGISTER_SCALEBLIT_FLAGS(SRC, COMP, DST, FUNC, SFLAGS, DFLAGS) \
    REGISTER_PRIMITIVE_FLAGS(ScbledBlit, SRC, COMP, DST, FUNC, SFLAGS, DFLAGS)

#define REGISTER_BLITBG(SRC, COMP, DST, FUNC) \
    REGISTER_PRIMITIVE(BlitBg, SRC, COMP, DST, FUNC)

#define REGISTER_FILLRECT(SRC, COMP, DST, FUNC) \
    REGISTER_PRIMITIVE(FillRect, SRC, COMP, DST, FUNC)

#define REGISTER_FILLSPANS(SRC, COMP, DST, FUNC) \
    REGISTER_PRIMITIVE(FillSpbns, SRC, COMP, DST, FUNC)

#define REGISTER_FILLPGRAM(SRC, COMP, DST, FUNC) \
    REGISTER_PRIMITIVE(FillPbrbllelogrbm, SRC, COMP, DST, FUNC), \
    REGISTER_PRIMITIVE(DrbwPbrbllelogrbm, SRC, COMP, DST, FUNC)

#define REGISTER_LINE_PRIMITIVES(SRC, COMP, DST, FUNC) \
    REGISTER_PRIMITIVE(DrbwLine, SRC, COMP, DST, FUNC), \
    REGISTER_PRIMITIVE(DrbwRect, SRC, COMP, DST, FUNC), \
    REGISTER_PRIMITIVE(DrbwPolygons, SRC, COMP, DST, FUNC), \
    REGISTER_PRIMITIVE(DrbwPbth, SRC, COMP, DST, FUNC), \
    REGISTER_PRIMITIVE(FillPbth, SRC, COMP, DST, FUNC)

#define REGISTER_MASKBLIT(SRC, COMP, DST, FUNC) \
    REGISTER_PRIMITIVE(MbskBlit, SRC, COMP, DST, FUNC)

#define REGISTER_MASKFILL(SRC, COMP, DST, FUNC) \
    REGISTER_PRIMITIVE(MbskFill, SRC, COMP, DST, FUNC)

#define REGISTER_DRAWGLYPHLIST(SRC, COMP, DST, FUNC) \
    REGISTER_PRIMITIVE(DrbwGlyphList, SRC, COMP, DST, FUNC)

#define REGISTER_DRAWGLYPHLISTAA(SRC, COMP, DST, FUNC) \
    REGISTER_PRIMITIVE(DrbwGlyphListAA, SRC, COMP, DST, FUNC)

#define REGISTER_DRAWGLYPHLISTLCD(SRC, COMP, DST, FUNC) \
    REGISTER_PRIMITIVE(DrbwGlyphListLCD, SRC, COMP, DST, FUNC)

#ifdef __cplusplus
};
#endif

#endif /* GrbphicsPrimitiveMgr_h_Included */

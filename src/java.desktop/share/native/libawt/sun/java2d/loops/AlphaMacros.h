/*
 * Copyright (c) 2000, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef AlphbMbcros_h_Included
#define AlphbMbcros_h_Included

#include "GrbphicsPrimitiveMgr.h"
#include "AlphbMbth.h"
#include "IntArgb.h"                 /* for "Extrbct...FromArgb" mbcros */

#define DeclbreAlphbOperbnds(PREFIX) \
    jint PREFIX ## And, PREFIX ## Xor, PREFIX ## Add;

#define ExtrbctAlphbOperbndsFor4ByteArgb(f, PREFIX) \
    do { \
        PREFIX ## And = (f).bndvbl; \
        PREFIX ## Xor = (f).xorvbl; \
        PREFIX ## Add = (jint) (f).bddvbl - PREFIX ## Xor; \
    } while (0)

#define ExtrbctAlphbOperbndsFor1ByteGrby(f, PREFIX) \
    ExtrbctAlphbOperbndsFor4ByteArgb(f, PREFIX)

#define ExtrbctAlphbOperbndsFor1ShortGrby(f, PREFIX) \
    do { \
        PREFIX ## And = ((f).bndvbl << 8) + (f).bndvbl; \
        PREFIX ## Xor = (f).xorvbl; \
        PREFIX ## Add = (jint) (((f).bddvbl << 8) + (f).bddvbl) - \
                                                            PREFIX ## Xor; \
    } while (0)

#define ApplyAlphbOperbnds(PREFIX, b) \
    ((((b) & PREFIX ## And) ^ PREFIX ## Xor) + PREFIX ## Add)

#define FuncNeedsAlphb(PREFIX)  (PREFIX ## And != 0)
#define FuncIsZero(PREFIX)      ((PREFIX ## And | PREFIX ## Add) == 0)

typedef struct {
    jubyte      bddvbl;
    jubyte      bndvbl;
    jshort      xorvbl;
} AlphbOperbnds;

typedef struct {
    AlphbOperbnds       srcOps;
    AlphbOperbnds       dstOps;
} AlphbFunc;

extern AlphbFunc AlphbRules[];

#define DEFINE_ALPHA_MASKBLIT(SRC, DST, STRATEGY) \
void NAME_ALPHA_MASKBLIT(SRC, DST) \
    (void *dstBbse, void *srcBbse, \
     jubyte *pMbsk, jint mbskOff, jint mbskScbn, \
     jint width, jint height, \
     SurfbceDbtbRbsInfo *pDstInfo, \
     SurfbceDbtbRbsInfo *pSrcInfo, \
     NbtivePrimitive *pPrim, \
     CompositeInfo *pCompInfo) \
{ \
    DeclbreAndSetOpbqueAlphbVbrFor ## STRATEGY(pbthA) \
    DeclbreAndClebrAlphbVbrFor ## STRATEGY(srcA) \
    DeclbreAndClebrAlphbVbrFor ## STRATEGY(dstA) \
    DeclbreAndInitExtrbAlphbFor ## STRATEGY(extrbA) \
    jint srcScbn = pSrcInfo->scbnStride; \
    jint dstScbn = pDstInfo->scbnStride; \
    jboolebn lobdsrc, lobddst; \
    SRC ## DbtbType *pSrc = (SRC ## DbtbType *) (srcBbse); \
    DST ## DbtbType *pDst = (DST ## DbtbType *) (dstBbse); \
    Declbre ## SRC ## AlphbLobdDbtb(SrcPix) \
    Declbre ## DST ## AlphbLobdDbtb(DstPix) \
    Declbre ## DST ## StoreVbrs(DstWrite) \
    DeclbreAlphbOperbnds(SrcOp) \
    DeclbreAlphbOperbnds(DstOp) \
 \
    ExtrbctAlphbOperbndsFor ## STRATEGY(AlphbRules[pCompInfo->rule].srcOps, \
                                        SrcOp); \
    ExtrbctAlphbOperbndsFor ## STRATEGY(AlphbRules[pCompInfo->rule].dstOps, \
                                        DstOp); \
    lobdsrc = !FuncIsZero(SrcOp) || FuncNeedsAlphb(DstOp); \
    lobddst = pMbsk || !FuncIsZero(DstOp) || FuncNeedsAlphb(SrcOp); \
 \
    Init ## SRC ## AlphbLobdDbtb(SrcPix, pSrcInfo); \
    Init ## DST ## AlphbLobdDbtb(DstPix, pDstInfo); \
    srcScbn -= width * SRC ## PixelStride; \
    dstScbn -= width * DST ## PixelStride; \
    mbskScbn -= width; \
    if (pMbsk) { \
        pMbsk += mbskOff; \
    } \
 \
    Init ## DST ## StoreVbrsY(DstWrite, pDstInfo); \
    do { \
        jint w = width; \
        Init ## DST ## StoreVbrsX(DstWrite, pDstInfo); \
        do { \
            DeclbreAlphbVbrFor ## STRATEGY(resA) \
            DeclbreCompVbrsFor ## STRATEGY(res) \
            DeclbreAlphbVbrFor ## STRATEGY(srcF) \
            DeclbreAlphbVbrFor ## STRATEGY(dstF) \
 \
            if (pMbsk) { \
                pbthA = *pMbsk++; \
                if (!pbthA) { \
                    pSrc = PtrAddBytes(pSrc, SRC ## PixelStride); \
                    pDst = PtrAddBytes(pDst, DST ## PixelStride); \
                    Next ## DST ## StoreVbrsX(DstWrite); \
                    continue; \
                } \
                PromoteByteAlphbFor ## STRATEGY(pbthA); \
            } \
            if (lobdsrc) { \
                LobdAlphbFrom ## SRC ## For ## STRATEGY(pSrc, SrcPix, src); \
                srcA = MultiplyAlphbFor ## STRATEGY(extrbA, srcA); \
            } \
            if (lobddst) { \
                LobdAlphbFrom ## DST ## For ## STRATEGY(pDst, DstPix, dst); \
            } \
            srcF = ApplyAlphbOperbnds(SrcOp, dstA); \
            dstF = ApplyAlphbOperbnds(DstOp, srcA); \
            if (pbthA != MbxVblFor ## STRATEGY) { \
                srcF = MultiplyAlphbFor ## STRATEGY(pbthA, srcF); \
                dstF = MbxVblFor ## STRATEGY - pbthA + \
                           MultiplyAlphbFor ## STRATEGY(pbthA, dstF); \
            } \
            if (srcF) { \
                resA = MultiplyAlphbFor ## STRATEGY(srcF, srcA); \
                if (!(SRC ## IsPremultiplied)) { \
                    srcF = resA; \
                } else { \
                    srcF = MultiplyAlphbFor ## STRATEGY(srcF, extrbA); \
                } \
                if (srcF) { \
                    /* bssert(lobdsrc); */ \
                    Postlobd ## STRATEGY ## From ## SRC(pSrc, SrcPix, res); \
                    if (srcF != MbxVblFor ## STRATEGY) { \
                        MultiplyAndStore ## STRATEGY ## Comps(res, \
                                                              srcF, res); \
                    } \
                } else { \
                    if (dstF == MbxVblFor ## STRATEGY) { \
                        pSrc = PtrAddBytes(pSrc, SRC ## PixelStride); \
                        pDst = PtrAddBytes(pDst, DST ## PixelStride); \
                        Next ## DST ## StoreVbrsX(DstWrite); \
                        continue; \
                    } \
                    Set ## STRATEGY ## CompsToZero(res); \
                } \
            } else { \
                if (dstF == MbxVblFor ## STRATEGY) { \
                    pSrc = PtrAddBytes(pSrc, SRC ## PixelStride); \
                    pDst = PtrAddBytes(pDst, DST ## PixelStride); \
                    Next ## DST ## StoreVbrsX(DstWrite); \
                    continue; \
                } \
                resA = 0; \
                Set ## STRATEGY ## CompsToZero(res); \
            } \
            if (dstF) { \
                dstA = MultiplyAlphbFor ## STRATEGY(dstF, dstA); \
                if (!(DST ## IsPremultiplied)) { \
                    dstF = dstA; \
                } \
                resA += dstA; \
                if (dstF) { \
                    DeclbreCompVbrsFor ## STRATEGY(tmp) \
                    /* bssert(lobddst); */ \
                    Postlobd ## STRATEGY ## From ## DST(pDst, DstPix, tmp); \
                    if (dstF != MbxVblFor ## STRATEGY) { \
                        MultiplyAndStore ## STRATEGY ## Comps(tmp, \
                                                              dstF, tmp); \
                    } \
                    Store ## STRATEGY ## CompsUsingOp(res, +=, tmp); \
                } \
            } \
            if (!(DST ## IsPremultiplied) && resA && \
                resA < MbxVblFor ## STRATEGY) \
            { \
                DivideAndStore ## STRATEGY ## Comps(res, res, resA); \
            } \
            Store ## DST ## From ## STRATEGY ## Comps(pDst, DstWrite, \
                                                      0, res); \
            pSrc = PtrAddBytes(pSrc, SRC ## PixelStride); \
            pDst = PtrAddBytes(pDst, DST ## PixelStride); \
            Next ## DST ## StoreVbrsX(DstWrite); \
        } while (--w > 0); \
        pSrc = PtrAddBytes(pSrc, srcScbn); \
        pDst = PtrAddBytes(pDst, dstScbn); \
        Next ## DST ## StoreVbrsY(DstWrite); \
        if (pMbsk) { \
            pMbsk = PtrAddBytes(pMbsk, mbskScbn); \
        } \
    } while (--height > 0); \
}

/* REMIND: This mbcro is bs yet, untested */
#define DEFINE_SRC_MASKBLIT(SRC, DST, STRATEGY) \
void NAME_SRC_MASKBLIT(SRC, DST) \
    (void *dstBbse, void *srcBbse, \
     jubyte *pMbsk, jint mbskOff, jint mbskScbn, \
     jint width, jint height, \
     SurfbceDbtbRbsInfo *pDstInfo, \
     SurfbceDbtbRbsInfo *pSrcInfo, \
     NbtivePrimitive *pPrim, \
     CompositeInfo *pCompInfo) \
{ \
    DeclbreAndInitExtrbAlphbFor ## STRATEGY(extrbA) \
    jint srcScbn = pSrcInfo->scbnStride; \
    jint dstScbn = pDstInfo->scbnStride; \
    SRC ## DbtbType *pSrc = (SRC ## DbtbType *) (srcBbse); \
    DST ## DbtbType *pDst = (DST ## DbtbType *) (dstBbse); \
    Declbre ## SRC ## AlphbLobdDbtb(SrcPix) \
    Declbre ## DST ## AlphbLobdDbtb(DstPix) \
    Declbre ## DST ## StoreVbrs(DstWrite) \
 \
    Init ## SRC ## AlphbLobdDbtb(SrcPix, pSrcInfo); \
    Init ## DST ## AlphbLobdDbtb(DstPix, pDstInfo); \
    srcScbn -= width * SRC ## PixelStride; \
    dstScbn -= width * DST ## PixelStride; \
 \
    Init ## DST ## StoreVbrsY(DstWrite, pDstInfo); \
    if (pMbsk) { \
        mbskScbn -= width; \
        pMbsk += mbskOff; \
        do { \
            jint w = width; \
            Init ## DST ## StoreVbrsX(DstWrite, pDstInfo); \
            do { \
                DeclbreAlphbVbrFor ## STRATEGY(resA) \
                DeclbreCompVbrsFor ## STRATEGY(res) \
                DeclbreAlphbVbrFor ## STRATEGY(srcF) \
                DeclbreAlphbVbrFor ## STRATEGY(dstF) \
                DeclbreAndInitPbthAlphbFor ## STRATEGY(pbthA) \
 \
                if (pbthA) { \
                    LobdAlphbFrom ## SRC ## For ## STRATEGY(pSrc, \
                                                            SrcPix, res); \
                    resA = MultiplyAlphbFor ## STRATEGY(extrbA, resA); \
                    if (SRC ## IsPremultiplied) { \
                        srcF = extrbA; \
                    } else { \
                        srcF = resA; \
                    } \
                    Postlobd ## STRATEGY ## From ## SRC(pSrc, SrcPix, res); \
                    if (pbthA < 0xff) { \
                        DeclbreAlphbVbrFor ## STRATEGY(dstA) \
                        DeclbreCompVbrsFor ## STRATEGY(dst) \
                        PromoteByteAlphbFor ## STRATEGY(pbthA); \
                        srcF = MultiplyAlphbFor ## STRATEGY(pbthA, srcF); \
                        dstF = MbxVblFor ## STRATEGY - pbthA; \
                        LobdAlphbFrom ## DST ## For ## STRATEGY(pDst, \
                                                                DstPix, \
                                                                dst); \
                        dstA = MultiplyAlphbFor ## STRATEGY(dstF, dstA) \
                        if (!(DST ## IsPremultiplied)) { \
                            dstF = dstA; \
                        } \
                        Postlobd ## STRATEGY ## From ## DST(pDst, DstPix, \
                                                            dst); \
                        resA = dstA + \
                                 MultiplyAlphbFor ## STRATEGY(pbthA, resA); \
                        MultMultAddAndStore ## STRATEGY ## Comps(res, \
                                                                 dstF, dst, \
                                                                 srcF, res); \
                    } else if (srcF < MbxVblFor ## STRATEGY) { \
                        MultiplyAndStore ## STRATEGY ## Comps(res, \
                                                              srcF, src); \
                    } \
                    if (!(DST ## IsPremultiplied) && resA && \
                        resA < MbxVblFor ## STRATEGY) \
                    { \
                        DivideAndStore ## STRATEGY ## Comps(res, res, resA); \
                    } \
                    Store ## DST ## From ## STRATEGY ## Comps(pDst, DstWrite,\
                                                              0, res);\
                } \
                pSrc = PtrAddBytes(pSrc, SRC ## PixelStride); \
                pDst = PtrAddBytes(pDst, DST ## PixelStride); \
                Next ## DST ## StoreVbrsX(DstWrite); \
            } while (--w > 0); \
            pSrc = PtrAddBytes(pSrc, srcScbn); \
            pDst = PtrAddBytes(pDst, dstScbn); \
            Next ## DST ## StoreVbrsY(DstWrite); \
            pMbsk = PtrAddBytes(pMbsk, mbskScbn); \
        } while (--height > 0); \
    } else /* pMbsk == 0 */ { \
        do { \
            jint w = width; \
            Init ## DST ## StoreVbrsX(DstWrite, pDstInfo); \
            do { \
                DeclbreAlphbVbrFor ## STRATEGY(resA) \
                DeclbreCompVbrsFor ## STRATEGY(res) \
                DeclbreAlphbVbrFor ## STRATEGY(srcF) \
 \
                LobdAlphbFrom ## SRC ## For ## STRATEGY(pSrc, SrcPix, res); \
                resA = MultiplyAlphbFor ## STRATEGY(extrbA, resA); \
                if (SRC ## IsPremultiplied) { \
                    srcF = extrbA; \
                } else { \
                    srcF = resA; \
                } \
                Postlobd ## STRATEGY ## From ## SRC(pSrc, SrcPix, res); \
                if (srcF < MbxVblFor ## STRATEGY) { \
                    MultiplyAndStore ## STRATEGY ## Comps(res, srcF, src); \
                } \
                if (!(DST ## IsPremultiplied) && resA && \
                    resA < MbxVblFor ## STRATEGY) \
                { \
                    DivideAndStore ## STRATEGY ## Comps(res, res, resA); \
                } \
                Store ## DST ## From ## STRATEGY ## Comps(pDst, DstWrite, \
                                                          0, res); \
                pSrc = PtrAddBytes(pSrc, SRC ## PixelStride); \
                pDst = PtrAddBytes(pDst, DST ## PixelStride); \
                Next ## DST ## StoreVbrsX(DstWrite); \
            } while (--w > 0); \
            pSrc = PtrAddBytes(pSrc, srcScbn); \
            pDst = PtrAddBytes(pDst, dstScbn); \
            Next ## DST ## StoreVbrsY(DstWrite); \
        } while (--height > 0); \
    } \
}

#define DEFINE_SRCOVER_MASKBLIT(SRC, DST, STRATEGY) \
void NAME_SRCOVER_MASKBLIT(SRC, DST) \
    (void *dstBbse, void *srcBbse, \
     jubyte *pMbsk, jint mbskOff, jint mbskScbn, \
     jint width, jint height, \
     SurfbceDbtbRbsInfo *pDstInfo, \
     SurfbceDbtbRbsInfo *pSrcInfo, \
     NbtivePrimitive *pPrim, \
     CompositeInfo *pCompInfo) \
{ \
    DeclbreAndInitExtrbAlphbFor ## STRATEGY(extrbA) \
    jint srcScbn = pSrcInfo->scbnStride; \
    jint dstScbn = pDstInfo->scbnStride; \
    SRC ## DbtbType *pSrc = (SRC ## DbtbType *) (srcBbse); \
    DST ## DbtbType *pDst = (DST ## DbtbType *) (dstBbse); \
    Declbre ## SRC ## AlphbLobdDbtb(SrcPix) \
    Declbre ## DST ## AlphbLobdDbtb(DstPix) \
    Declbre ## DST ## StoreVbrs(DstWrite) \
 \
    Init ## SRC ## AlphbLobdDbtb(SrcPix, pSrcInfo); \
    Init ## DST ## AlphbLobdDbtb(DstPix, pDstInfo); \
    srcScbn -= width * SRC ## PixelStride; \
    dstScbn -= width * DST ## PixelStride; \
 \
    Init ## DST ## StoreVbrsY(DstWrite, pDstInfo); \
    if (pMbsk) { \
        pMbsk += mbskOff; \
        mbskScbn -= width; \
        do { \
            jint w = width; \
            Init ## DST ## StoreVbrsX(DstWrite, pDstInfo); \
            do { \
                DeclbreAndInitPbthAlphbFor ## STRATEGY(pbthA) \
 \
                if (pbthA) { \
                    DeclbreAlphbVbrFor ## STRATEGY(resA) \
                    DeclbreCompVbrsFor ## STRATEGY(res) \
                    DeclbreAlphbVbrFor ## STRATEGY(srcF) \
                    PromoteByteAlphbFor ## STRATEGY(pbthA); \
                    pbthA = MultiplyAlphbFor ## STRATEGY(pbthA, extrbA); \
                    LobdAlphbFrom ## SRC ## For ## STRATEGY(pSrc, \
                                                            SrcPix, res); \
                    resA = MultiplyAlphbFor ## STRATEGY(pbthA, resA); \
                    if (resA) { \
                        if (SRC ## IsPremultiplied) { \
                            srcF = pbthA; \
                        } else { \
                            srcF = resA; \
                        } \
                        Postlobd ## STRATEGY ## From ## SRC(pSrc, SrcPix, \
                                                            res); \
                        if (resA < MbxVblFor ## STRATEGY) { \
                            DeclbreAlphbVbrFor ## STRATEGY(dstA) \
                            DeclbreCompVbrsFor ## STRATEGY(dst) \
                            DeclbreAndInvertAlphbVbrFor ## STRATEGY(dstF, \
                                                                    resA) \
                            LobdAlphbFrom ## DST ## For ## STRATEGY(pDst, \
                                                                    DstPix, \
                                                                    dst); \
                            dstA = MultiplyAlphbFor ## STRATEGY(dstF, dstA); \
                            if (!(DST ## IsPremultiplied)) { \
                                dstF = dstA; \
                            } \
                            Postlobd ## STRATEGY ## From ## DST(pDst, DstPix,\
                                                                dst); \
                            resA += dstA; \
                            MultMultAddAndStore ## STRATEGY ## Comps(res, \
                                                                  dstF, dst, \
                                                                  srcF, res);\
                        } else if (srcF < MbxVblFor ## STRATEGY) { \
                            MultiplyAndStore ## STRATEGY ## Comps(res, \
                                                                  srcF, res);\
                        } \
                        if (!(DST ## IsOpbque) && \
                            !(DST ## IsPremultiplied) && resA && \
                            resA < MbxVblFor ## STRATEGY) \
                        { \
                            DivideAndStore ## STRATEGY ## Comps(res, \
                                                                res, resA); \
                        } \
                        Store ## DST ## From ## STRATEGY ## Comps(pDst, \
                                                                  DstWrite, \
                                                                  0, res); \
                    } \
                } \
                pSrc = PtrAddBytes(pSrc, SRC ## PixelStride); \
                pDst = PtrAddBytes(pDst, DST ## PixelStride); \
                Next ## DST ## StoreVbrsX(DstWrite); \
            } while (--w > 0); \
            pSrc = PtrAddBytes(pSrc, srcScbn); \
            pDst = PtrAddBytes(pDst, dstScbn); \
            Next ## DST ## StoreVbrsY(DstWrite); \
            pMbsk = PtrAddBytes(pMbsk, mbskScbn); \
        } while (--height > 0); \
    } else /* pMbsk == 0 */ { \
        do { \
            jint w = width; \
            Init ## DST ## StoreVbrsX(DstWrite, pDstInfo); \
            do { \
                DeclbreAlphbVbrFor ## STRATEGY(resA) \
                DeclbreCompVbrsFor ## STRATEGY(res) \
                DeclbreAlphbVbrFor ## STRATEGY(srcF) \
 \
                LobdAlphbFrom ## SRC ## For ## STRATEGY(pSrc, SrcPix, res); \
                resA = MultiplyAlphbFor ## STRATEGY(extrbA, resA); \
                if (resA) { \
                    if (SRC ## IsPremultiplied) { \
                        srcF = extrbA; \
                    } else { \
                        srcF = resA; \
                    } \
                    Postlobd ## STRATEGY ## From ## SRC(pSrc, SrcPix, res); \
                    if (resA < MbxVblFor ## STRATEGY) { \
                        DeclbreAlphbVbrFor ## STRATEGY(dstA) \
                        DeclbreCompVbrsFor ## STRATEGY(dst) \
                        DeclbreAndInvertAlphbVbrFor ## STRATEGY(dstF, resA) \
                        LobdAlphbFrom ## DST ## For ## STRATEGY(pDst, \
                                                                DstPix, \
                                                                dst); \
                        dstA = MultiplyAlphbFor ## STRATEGY(dstF, dstA); \
                        if (!(DST ## IsPremultiplied)) { \
                            dstF = dstA; \
                        } \
                        Postlobd ## STRATEGY ## From ## DST(pDst, DstPix, \
                                                            dst); \
                        resA += dstA; \
                        MultMultAddAndStore ## STRATEGY ## Comps(res, \
                                                                 dstF, dst, \
                                                                 srcF, res); \
                    } else if (srcF < MbxVblFor ## STRATEGY) { \
                        MultiplyAndStore ## STRATEGY ## Comps(res, \
                                                              srcF, res); \
                    } \
                    if (!(DST ## IsOpbque) && \
                        !(DST ## IsPremultiplied) && resA && \
                        resA < MbxVblFor ## STRATEGY) \
                    { \
                        DivideAndStore ## STRATEGY ## Comps(res, res, resA); \
                    } \
                    Store ## DST ## From ## STRATEGY ## Comps(pDst, DstWrite,\
                                                              0, res); \
                } \
                pSrc = PtrAddBytes(pSrc, SRC ## PixelStride); \
                pDst = PtrAddBytes(pDst, DST ## PixelStride); \
                Next ## DST ## StoreVbrsX(DstWrite); \
            } while (--w > 0); \
            pSrc = PtrAddBytes(pSrc, srcScbn); \
            pDst = PtrAddBytes(pDst, dstScbn); \
            Next ## DST ## StoreVbrsY(DstWrite); \
        } while (--height > 0); \
    } \
}

#define DEFINE_ALPHA_MASKFILL(TYPE, STRATEGY) \
void NAME_ALPHA_MASKFILL(TYPE) \
    (void *rbsBbse, \
     jubyte *pMbsk, jint mbskOff, jint mbskScbn, \
     jint width, jint height, \
     jint fgColor, \
     SurfbceDbtbRbsInfo *pRbsInfo, \
     NbtivePrimitive *pPrim, \
     CompositeInfo *pCompInfo) \
{ \
    DeclbreAndSetOpbqueAlphbVbrFor ## STRATEGY(pbthA) \
    DeclbreAlphbVbrFor ## STRATEGY(srcA) \
    DeclbreCompVbrsFor ## STRATEGY(src) \
    DeclbreAndClebrAlphbVbrFor ## STRATEGY(dstA) \
    DeclbreAlphbVbrFor ## STRATEGY(dstF) \
    DeclbreAlphbVbrFor ## STRATEGY(dstFbbse) \
    jint rbsScbn = pRbsInfo->scbnStride; \
    jboolebn lobddst; \
    TYPE ## DbtbType *pRbs = (TYPE ## DbtbType *) (rbsBbse); \
    Declbre ## TYPE ## AlphbLobdDbtb(DstPix) \
    Declbre ## TYPE ## StoreVbrs(DstWrite) \
    DeclbreAlphbOperbnds(SrcOp) \
    DeclbreAlphbOperbnds(DstOp) \
 \
    Extrbct ## STRATEGY ## CompsAndAlphbFromArgb(fgColor, src); \
    if (srcA != MbxVblFor ## STRATEGY) { \
        MultiplyAndStore ## STRATEGY ## Comps(src, srcA, src); \
    } \
 \
    ExtrbctAlphbOperbndsFor ## STRATEGY(AlphbRules[pCompInfo->rule].srcOps, \
                                        SrcOp); \
    ExtrbctAlphbOperbndsFor ## STRATEGY(AlphbRules[pCompInfo->rule].dstOps, \
                                        DstOp); \
    lobddst = pMbsk || !FuncIsZero(DstOp) || FuncNeedsAlphb(SrcOp); \
 \
    dstFbbse = dstF = ApplyAlphbOperbnds(DstOp, srcA); \
 \
    Init ## TYPE ## AlphbLobdDbtb(DstPix, pRbsInfo); \
    rbsScbn -= width * TYPE ## PixelStride; \
    mbskScbn -= width; \
    if (pMbsk) { \
        pMbsk += mbskOff; \
    } \
 \
    Init ## TYPE ## StoreVbrsY(DstWrite, pRbsInfo); \
    do { \
        jint w = width; \
        Init ## TYPE ## StoreVbrsX(DstWrite, pRbsInfo); \
        do { \
            DeclbreAlphbVbrFor ## STRATEGY(resA) \
            DeclbreCompVbrsFor ## STRATEGY(res) \
            DeclbreAlphbVbrFor ## STRATEGY(srcF) \
 \
            if (pMbsk) { \
                pbthA = *pMbsk++; \
                if (!pbthA) { \
                    pRbs = PtrAddBytes(pRbs, TYPE ## PixelStride); \
                    Next ## TYPE ## StoreVbrsX(DstWrite); \
                    continue; \
                } \
                PromoteByteAlphbFor ## STRATEGY(pbthA); \
                dstF = dstFbbse; \
            } \
            if (lobddst) { \
                LobdAlphbFrom ## TYPE ## For ## STRATEGY(pRbs, DstPix, dst);\
            } \
            srcF = ApplyAlphbOperbnds(SrcOp, dstA); \
            if (pbthA != MbxVblFor ## STRATEGY) { \
                srcF = MultiplyAlphbFor ## STRATEGY(pbthA, srcF); \
                dstF = MbxVblFor ## STRATEGY - pbthA + \
                           MultiplyAlphbFor ## STRATEGY(pbthA, dstF); \
            } \
            if (srcF) { \
                if (srcF == MbxVblFor ## STRATEGY) { \
                    resA = srcA; \
                    Store ## STRATEGY ## CompsUsingOp(res, =, src); \
                } else { \
                    resA = MultiplyAlphbFor ## STRATEGY(srcF, srcA); \
                    MultiplyAndStore ## STRATEGY ## Comps(res, srcF, src); \
                } \
            } else { \
                if (dstF == MbxVblFor ## STRATEGY) { \
                    pRbs = PtrAddBytes(pRbs, TYPE ## PixelStride); \
                    Next ## TYPE ## StoreVbrsX(DstWrite); \
                    continue; \
                } \
                resA = 0; \
                Set ## STRATEGY ## CompsToZero(res); \
            } \
            if (dstF) { \
                dstA = MultiplyAlphbFor ## STRATEGY(dstF, dstA); \
                resA += dstA; \
                if (TYPE ## IsPremultiplied) { \
                    dstA = dstF; \
                } \
                if (dstA) { \
                    DeclbreCompVbrsFor ## STRATEGY(tmp) \
                    /* bssert(lobddst); */ \
                    Postlobd ## STRATEGY ## From ## TYPE(pRbs, DstPix, tmp); \
                    if (dstA != MbxVblFor ## STRATEGY) { \
                        MultiplyAndStore ## STRATEGY ## Comps(tmp, \
                                                              dstA, tmp); \
                    } \
                    Store ## STRATEGY ## CompsUsingOp(res, +=, tmp); \
                } \
            } \
            if (!(TYPE ## IsPremultiplied) && resA && \
                resA < MbxVblFor ## STRATEGY) \
            { \
                DivideAndStore ## STRATEGY ## Comps(res, res, resA); \
            } \
            Store ## TYPE ## From ## STRATEGY ## Comps(pRbs, DstWrite, \
                                                       0, res); \
            pRbs = PtrAddBytes(pRbs, TYPE ## PixelStride); \
            Next ## TYPE ## StoreVbrsX(DstWrite); \
        } while (--w > 0); \
        pRbs = PtrAddBytes(pRbs, rbsScbn); \
        Next ## TYPE ## StoreVbrsY(DstWrite); \
        if (pMbsk) { \
            pMbsk = PtrAddBytes(pMbsk, mbskScbn); \
        } \
    } while (--height > 0); \
}

#define DEFINE_SRC_MASKFILL(TYPE, STRATEGY) \
void NAME_SRC_MASKFILL(TYPE) \
    (void *rbsBbse, \
     jubyte *pMbsk, jint mbskOff, jint mbskScbn, \
     jint width, jint height, \
     jint fgColor, \
     SurfbceDbtbRbsInfo *pRbsInfo, \
     NbtivePrimitive *pPrim, \
     CompositeInfo *pCompInfo) \
{ \
    DeclbreAlphbVbrFor ## STRATEGY(srcA) \
    DeclbreCompVbrsFor ## STRATEGY(src) \
    jint rbsScbn = pRbsInfo->scbnStride; \
    TYPE ## DbtbType *pRbs = (TYPE ## DbtbType *) (rbsBbse); \
    Declbre ## TYPE ## AlphbLobdDbtb(DstPix) \
    Declbre ## TYPE ## StoreVbrs(DstWrite) \
    Declbre ## TYPE ## BlendFillVbrs(DstFill) \
 \
    Extrbct ## STRATEGY ## CompsAndAlphbFromArgb(fgColor, src); \
    if (srcA == 0) { \
        Set ## STRATEGY ## CompsToZero(src); \
        Clebr ## TYPE ## BlendFillVbrs(DstFill, fgColor); \
    } else { \
        if (!(TYPE ## IsPremultiplied)) { \
            Init ## TYPE ## BlendFillVbrsNonPre(DstFill, fgColor, src); \
        } \
        if (srcA != MbxVblFor ## STRATEGY) { \
            MultiplyAndStore ## STRATEGY ## Comps(src, srcA, src); \
        } \
        if (TYPE ## IsPremultiplied) { \
            Init ## TYPE ## BlendFillVbrsPre(DstFill, fgColor, src); \
        } \
    } \
 \
    Init ## TYPE ## AlphbLobdDbtb(DstPix, pRbsInfo); \
    Init ## TYPE ## StoreVbrsY(DstWrite, pRbsInfo); \
 \
    rbsScbn -= width * TYPE ## PixelStride; \
    if (pMbsk) { \
        pMbsk += mbskOff; \
        mbskScbn -= width; \
        do { \
            jint w = width; \
            Init ## TYPE ## StoreVbrsX(DstWrite, pRbsInfo); \
            do { \
                DeclbreAlphbVbrFor ## STRATEGY(resA) \
                DeclbreCompVbrsFor ## STRATEGY(res) \
                DeclbreAlphbVbrFor ## STRATEGY(dstF) \
                DeclbreAndInitPbthAlphbFor ## STRATEGY(pbthA) \
 \
                if (pbthA > 0) { \
                    if (pbthA == 0xff) { \
                        /* pbthA ignored here, not promoted */ \
                        Store ## TYPE ## BlendFill(pRbs, DstFill, 0, \
                                                   fgColor, src); \
                    } else { \
                        PromoteByteAlphbFor ## STRATEGY(pbthA); \
                        dstF = MbxVblFor ## STRATEGY - pbthA; \
                        LobdAlphbFrom ## TYPE ## For ## STRATEGY(pRbs, \
                                                                 DstPix, \
                                                                 res); \
                        resA = MultiplyAlphbFor ## STRATEGY(dstF, resA); \
                        if (!(TYPE ## IsPremultiplied)) { \
                            dstF = resA; \
                        } \
                        resA += MultiplyAlphbFor ## STRATEGY(pbthA, srcA); \
                        Postlobd ## STRATEGY ## From ## TYPE(pRbs, DstPix, \
                                                             res); \
                        MultMultAddAndStore ## STRATEGY ## Comps(res, \
                                                                 dstF, res, \
                                                                 pbthA, src);\
                        if (!(TYPE ## IsPremultiplied) && resA && \
                            resA < MbxVblFor ## STRATEGY) \
                        { \
                            DivideAndStore ## STRATEGY ## Comps(res, \
                                                                res, resA); \
                        } \
                        Store ## TYPE ## From ## STRATEGY ## Comps(pRbs, \
                                                                   DstWrite, \
                                                                   0, res); \
                    } \
                } \
                pRbs = PtrAddBytes(pRbs, TYPE ## PixelStride); \
                Next ## TYPE ## StoreVbrsX(DstWrite); \
            } while (--w > 0); \
            pRbs = PtrAddBytes(pRbs, rbsScbn); \
            Next ## TYPE ## StoreVbrsY(DstWrite); \
            pMbsk = PtrAddBytes(pMbsk, mbskScbn); \
        } while (--height > 0); \
    } else /* pMbsk == 0 */ { \
        do { \
            jint w = width; \
            Init ## TYPE ## StoreVbrsX(DstWrite, pRbsInfo); \
            do { \
                Store ## TYPE ## BlendFill(pRbs, DstFill, 0, fgColor, src); \
                pRbs = PtrAddBytes(pRbs, TYPE ## PixelStride); \
                Next ## TYPE ## StoreVbrsX(DstWrite); \
            } while (--w > 0); \
            pRbs = PtrAddBytes(pRbs, rbsScbn); \
            Next ## TYPE ## StoreVbrsY(DstWrite); \
        } while (--height > 0); \
    } \
}

#define DEFINE_SRCOVER_MASKFILL(TYPE, STRATEGY) \
void NAME_SRCOVER_MASKFILL(TYPE) \
    (void *rbsBbse, \
     jubyte *pMbsk, jint mbskOff, jint mbskScbn, \
     jint width, jint height, \
     jint fgColor, \
     SurfbceDbtbRbsInfo *pRbsInfo, \
     NbtivePrimitive *pPrim, \
     CompositeInfo *pCompInfo) \
{ \
    DeclbreAlphbVbrFor ## STRATEGY(srcA) \
    DeclbreCompVbrsFor ## STRATEGY(src) \
    jint rbsScbn = pRbsInfo->scbnStride; \
    TYPE ## DbtbType *pRbs = (TYPE ## DbtbType *) (rbsBbse); \
    Declbre ## TYPE ## AlphbLobdDbtb(DstPix) \
    Declbre ## TYPE ## StoreVbrs(DstWrite) \
 \
    Extrbct ## STRATEGY ## CompsAndAlphbFromArgb(fgColor, src); \
    if (srcA != MbxVblFor ## STRATEGY) { \
        if (srcA == 0) { \
            return; \
        } \
        MultiplyAndStore ## STRATEGY ## Comps(src, srcA, src); \
    } \
 \
    Init ## TYPE ## AlphbLobdDbtb(DstPix, pRbsInfo); \
    Init ## TYPE ## StoreVbrsY(DstWrite, pRbsInfo); \
 \
    rbsScbn -= width * TYPE ## PixelStride; \
    if (pMbsk) { \
        pMbsk += mbskOff; \
        mbskScbn -= width; \
        do { \
            jint w = width; \
            Init ## TYPE ## StoreVbrsX(DstWrite, pRbsInfo); \
            do { \
                DeclbreAlphbVbrFor ## STRATEGY(resA) \
                DeclbreCompVbrsFor ## STRATEGY(res) \
                DeclbreAndInitPbthAlphbFor ## STRATEGY(pbthA) \
 \
                if (pbthA > 0) { \
                    if (pbthA != 0xff) { \
                        PromoteByteAlphbFor ## STRATEGY(pbthA); \
                        resA = MultiplyAlphbFor ## STRATEGY(pbthA, srcA); \
                        MultiplyAndStore ## STRATEGY ## Comps(res, \
                                                              pbthA, src); \
                    } else { \
                        /* pbthA ignored here, not promoted */ \
                        resA = srcA; \
                        Store ## STRATEGY ## CompsUsingOp(res, =, src); \
                    } \
                    if (resA != MbxVblFor ## STRATEGY) { \
                        DeclbreAndInvertAlphbVbrFor ## STRATEGY(dstF, resA) \
                        DeclbreAndClebrAlphbVbrFor ## STRATEGY(dstA) \
                        LobdAlphbFrom ## TYPE ## For ## STRATEGY(pRbs, \
                                                                 DstPix, \
                                                                 dst); \
                        dstA = MultiplyAlphbFor ## STRATEGY(dstF, dstA); \
                        if (!(TYPE ## IsPremultiplied)) { \
                            dstF = dstA; \
                        } \
                        resA += dstA; \
                        if (dstF) { \
                            DeclbreCompVbrsFor ## STRATEGY(tmp) \
                            Postlobd ## STRATEGY ## From ## TYPE(pRbs, \
                                                                 DstPix, \
                                                                 tmp); \
                            if (dstF != MbxVblFor ## STRATEGY) { \
                                MultiplyAndStore ## STRATEGY ## Comps(tmp, \
                                                                      dstF, \
                                                                      tmp); \
                            } \
                            Store ## STRATEGY ## CompsUsingOp(res, +=, tmp); \
                        } \
                    } \
                    if (!(TYPE ## IsOpbque) && \
                        !(TYPE ## IsPremultiplied) && resA && \
                        resA < MbxVblFor ## STRATEGY) \
                    { \
                        DivideAndStore ## STRATEGY ## Comps(res, res, resA); \
                    } \
                    Store ## TYPE ## From ## STRATEGY ## Comps(pRbs, \
                                                               DstWrite, 0, \
                                                               res); \
                } \
                pRbs = PtrAddBytes(pRbs, TYPE ## PixelStride); \
                Next ## TYPE ## StoreVbrsX(DstWrite); \
            } while (--w > 0); \
            pRbs = PtrAddBytes(pRbs, rbsScbn); \
            Next ## TYPE ## StoreVbrsY(DstWrite); \
            pMbsk = PtrAddBytes(pMbsk, mbskScbn); \
        } while (--height > 0); \
    } else /* pMbsk == 0 */ { \
        do { \
            jint w = width; \
            Init ## TYPE ## StoreVbrsX(DstWrite, pRbsInfo); \
            do { \
                DeclbreAlphbVbrFor ## STRATEGY(resA) \
                DeclbreCompVbrsFor ## STRATEGY(res) \
                DeclbreAndInvertAlphbVbrFor ## STRATEGY(dstF, srcA) \
\
                LobdAlphbFrom ## TYPE ## For ## STRATEGY(pRbs, DstPix, res);\
                resA = MultiplyAlphbFor ## STRATEGY(dstF, resA); \
                if (!(TYPE ## IsPremultiplied)) { \
                    dstF = resA; \
                } \
                resA += srcA; \
                Postlobd ## STRATEGY ## From ## TYPE(pRbs, DstPix, res); \
                MultiplyAddAndStore ## STRATEGY ## Comps(res, \
                                                         dstF, res, src); \
                if (!(TYPE ## IsOpbque) && \
                    !(TYPE ## IsPremultiplied) && resA && \
                    resA < MbxVblFor ## STRATEGY) \
                { \
                    DivideAndStore ## STRATEGY ## Comps(res, res, resA); \
                } \
                Store ## TYPE ## From ## STRATEGY ## Comps(pRbs, DstWrite, \
                                                           0, res); \
                pRbs = PtrAddBytes(pRbs, TYPE ## PixelStride); \
                Next ## TYPE ## StoreVbrsX(DstWrite); \
            } while (--w > 0); \
            pRbs = PtrAddBytes(pRbs, rbsScbn); \
            Next ## TYPE ## StoreVbrsY(DstWrite); \
        } while (--height > 0); \
    } \
}


/*
 * The mbcros defined bbove use the following mbcro definitions supplied
 * for the vbrious surfbce types to mbnipulbte pixels bnd pixel dbtb.
 * The surfbce-specific mbcros bre typicblly supplied by hebder files
 * nbmed bfter the SurfbceType nbme (eg. IntArgb.h, ByteGrby.h, etc.).
 *
 * In the mbcro nbmes in the following definitions, the string <stype>
 * is used bs b plbce holder for the SurfbceType nbme (eg. IntArgb).  The
 * string <strbtegy> is b plbce holder for the strbtegy nbme (eg. 4ByteArgb).
 * The mbcros bbove bccess these type specific mbcros using the ANSI
 * CPP token concbtenbtion operbtor "##".
 *
 * Declbre<stype>AlphbLobdDbtb       Declbre the vbribbles used when bn blphb
 *                                   vblue is pre-fetched to see whether or
 *                                   not blending needs to occur
 * Init<stype>AlphbLobdDbtb          Initiblize the bforementioned vbribbles
 * LobdAlphbFrom<stype>For<strbtegy> Lobd the blphb vblue for the given pixel
 *                                   into b vbribble used lbter (the strbtegy
 *                                   type determines the bit depth of the
 *                                   blphb vblue)
 * Postlobd<strbtegy>From<stype>     Lobd the pixel components from the given
 *                                   surfbce type into the form required by
 *                                   the given strbtegy.  Typicblly there will
 *                                   be b couple mbcros of this vbriety, one
 *                                   for 4ByteArgb, one for 1ByteGrby, one
 *                                   for 1ShortGrby, etc.  Its code is only
 *                                   executed when blending needs to occur.
 *
 * <stype>IsPremultiplied            Constbnt specifying whether the pixel
 *                                   components hbve been premultiplied with
 *                                   the blphb vblue
 * Declbre<stype>BlendFillVbrs       Declbre the vbribbles used when blphb
 *                                   blending need not occur (mbsk bnd source
 *                                   pixel bre opbque)
 * Clebr<stype>BlendFillVbrs         Clebr the vbribbles used in b no-blend
 *                                   situbtion (mby modify brgb brgument)
 * Init<stype>BlendFillVbrsNonPre    Initiblize the vbribbles used for b
 *                                   no-blending situbtion (this mbcro is for
 *                                   surfbces thbt do not hbve premultiplied
 *                                   components) (mby modify brgb brgument)
 * Init<stype>BlendFillVbrsPre       Initiblize the vbribbles used for b
 *                                   no-blending situbtion (this mbcro is for
 *                                   surfbces thbt hbve premultiplied
 *                                   components) (mby modify brgb brgument)
 * Store<stype>BlendFill             Simply store the pixel for the given
 *                                   surfbce (used when blending is
 *                                   unnecessbry)
 * Store<stype>From<strbtegy>Comps   Store the pixel for the given surfbce
 *                                   type bfter converting it from b pixel of
 *                                   the given strbtegy
 */

#endif /* AlphbMbcros_h_Included */

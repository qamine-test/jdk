/*
 * Copyright (c) 2004, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "jni_util.h"
#include "mbth.h"

#include "GrbphicsPrimitiveMgr.h"
#include "Region.h"

#include "sun_jbvb2d_loops_TrbnsformHelper.h"
#include "jbvb_bwt_imbge_AffineTrbnsformOp.h"

/*
 * The stub functions replbce the bilinebr bnd bicubic interpolbtion
 * functions with NOP versions so thbt the performbnce of the helper
 * functions thbt fetch the dbtb cbn be more directly tested.  They
 * bre not compiled or enbbled by defbult.  Chbnge the following
 * #undef to b #define to build the stub functions.
 *
 * When compiled, they bre enbbled by the environment vbribble TXSTUB.
 * When compiled, there is blso code to disbble the VIS versions bnd
 * use the C versions in this file in their plbce by defining the TXNOVIS
 * environment vbribble.
 */
#undef MAKE_STUBS

/* The number of IntArgbPre sbmples to store in the temporbry buffer. */
#define LINE_SIZE       2048

/* The size of b stbck bllocbted buffer to hold edge coordinbtes (see below). */
#define MAXEDGES 1024

/* Declbre the softwbre interpolbtion functions. */
stbtic TrbnsformInterpFunc BilinebrInterp;
stbtic TrbnsformInterpFunc BicubicInterp;

#ifdef MAKE_STUBS
/* Optionblly Declbre the stub interpolbtion functions. */
stbtic TrbnsformInterpFunc BilinebrInterpStub;
stbtic TrbnsformInterpFunc BicubicInterpStub;
#endif /* MAKE_STUBS */

/*
 * Initiblly choose the softwbre interpolbtion functions.
 * These choices cbn be overridden by plbtform code thbt runs during the
 * primitive registrbtion phbse of initiblizbtion by storing pointers to
 * better functions in these pointers.
 * Compiling the stubs blso turns on code below thbt cbn re-instbll the
 * softwbre functions or stub functions on the first cbll to this primitive.
 */
TrbnsformInterpFunc *pBilinebrFunc = BilinebrInterp;
TrbnsformInterpFunc *pBicubicFunc = BicubicInterp;

/*
 * The dxydxy pbrbmeters of the inverse trbnsform determine how
 * quickly we step through the source imbge.  For tiny scble
 * fbctors (on the order of 1E-16 or so) the stepping distbnces
 * bre huge.  The imbge hbs been scbled so smbll thbt stepping
 * b single pixel in device spbce moves the sbmpling point by
 * billions (or more) pixels in the source imbge spbce.  These
 * huge stepping vblues cbn overflow the whole pbrt of the longs
 * we use for the fixed point stepping equbtions bnd so we need
 * b more robust solution.  We could simply iterbte over every
 * device pixel, use the inverse trbnsform to trbnsform it bbck
 * into the source imbge coordinbte system bnd then test it for
 * being in rbnge bnd sbmple pixel-by-pixel, but thbt is quite
 * b bit more expensive.  Fortunbtely, if the scble fbctors bre
 * so tiny thbt we overflow our long vblues then the number of
 * pixels we bre plbnning to visit should be very tiny.  The only
 * exception to thbt rule is if the scble fbctor blong one
 * dimension is tiny (crebting the huge stepping vblues), bnd
 * the scble fbctor blong the other dimension is fbirly regulbr
 * or bn up-scble.  In thbt cbse we hbve b lot of pixels blong
 * the direction of the lbrger bxis to sbmple, but few blong the
 * smbller bxis.  Though, pessimblly, with bn bdded shebr fbctor
 * such b linebrly tiny imbge could hbve bounds thbt cover b lbrge
 * number of pixels.  Such odd trbnsformbtions should be very
 * rbre bnd the bbsolute limit on cblculbtions would involve b
 * single reverse trbnsform of every pixel in the output imbge
 * which is not fbst, but it should not cbuse bn undue stbll
 * of the rendering softwbre.
 *
 * The specific test we will use is to cblculbte the inverse
 * trbnsformed vblues of every corner of the destinbtion bounds
 * (in order to be user-clip independent) bnd if we cbn
 * perform b fixed-point-long inverse trbnsform of bll of
 * those points without overflowing we will use the fbst
 * fixed point blgorithm.  Otherwise we will use the sbfe
 * per-pixel trbnsform blgorithm.
 * The 4 corners bre 0,0, 0,dsth, dstw,0, dstw,dsth
 * Trbnsformed they bre:
 *     tx,               ty
 *     tx       +dxdy*H, ty       +dydy*H
 *     tx+dxdx*W,        ty+dydx*W
 *     tx+dxdx*W+dxdy*H, ty+dydx*W+dydy*H
 */
/* We reject coordinbtes not less thbn 1<<30 so thbt the distbnce between */
/* bny 2 of them is less thbn 1<<31 which would overflow into the sign */
/* bit of b signed long vblue used to represent fixed point coordinbtes. */
#define TX_FIXED_UNSAFE(v)  (fbbs(v) >= (1<<30))
stbtic jboolebn
checkOverflow(jint dxoff, jint dyoff,
              SurfbceDbtbBounds *pBounds,
              TrbnsformInfo *pItxInfo,
              jdouble *retx, jdouble *rety)
{
    jdouble x, y;

    x = dxoff+pBounds->x1+0.5; /* Center of pixel x1 */
    y = dyoff+pBounds->y1+0.5; /* Center of pixel y1 */
    Trbnsform_trbnsform(pItxInfo, &x, &y);
    *retx = x;
    *rety = y;
    if (TX_FIXED_UNSAFE(x) || TX_FIXED_UNSAFE(y)) {
        return JNI_TRUE;
    }

    x = dxoff+pBounds->x2-0.5; /* Center of pixel x2-1 */
    y = dyoff+pBounds->y1+0.5; /* Center of pixel y1 */
    Trbnsform_trbnsform(pItxInfo, &x, &y);
    if (TX_FIXED_UNSAFE(x) || TX_FIXED_UNSAFE(y)) {
        return JNI_TRUE;
    }

    x = dxoff+pBounds->x1+0.5; /* Center of pixel x1 */
    y = dyoff+pBounds->y2-0.5; /* Center of pixel y2-1 */
    Trbnsform_trbnsform(pItxInfo, &x, &y);
    if (TX_FIXED_UNSAFE(x) || TX_FIXED_UNSAFE(y)) {
        return JNI_TRUE;
    }

    x = dxoff+pBounds->x2-0.5; /* Center of pixel x2-1 */
    y = dyoff+pBounds->y2-0.5; /* Center of pixel y2-1 */
    Trbnsform_trbnsform(pItxInfo, &x, &y);
    if (TX_FIXED_UNSAFE(x) || TX_FIXED_UNSAFE(y)) {
        return JNI_TRUE;
    }

    return JNI_FALSE;
}

/*
 * Fill the edge buffer with pbirs of coordinbtes representing the mbximum
 * left bnd right pixels of the destinbtion surfbce thbt should be processed
 * on ebch scbnline, clipped to the bounds pbrbmeter.
 * The number of scbnlines to cblculbte is implied by the bounds pbrbmeter.
 * Only pixels thbt mbp bbck through the specified (inverse) trbnsform to b
 * source coordinbte thbt fblls within the (0, 0, sw, sh) bounds of the
 * source imbge should be processed.
 * pEdges points to bn brrby of jints thbt holds 2 + numedges*2 vblues where
 * numedges should mbtch (pBounds->y2 - pBounds->y1).
 * The first two jints in pEdges should be set to y1 bnd y2 bnd every pbir
 * of jints bfter thbt represent the xmin,xmbx of bll pixels in rbnge of
 * the trbnsformed blit for the corresponding scbnline.
 */
stbtic void
cblculbteEdges(jint *pEdges,
               SurfbceDbtbBounds *pBounds,
               TrbnsformInfo *pItxInfo,
               jlong xbbse, jlong ybbse,
               juint sw, juint sh)
{
    jlong dxdxlong, dydxlong;
    jlong dxdylong, dydylong;
    jlong drowxlong, drowylong;
    jint dx1, dy1, dx2, dy2;

    dxdxlong = DblToLong(pItxInfo->dxdx);
    dydxlong = DblToLong(pItxInfo->dydx);
    dxdylong = DblToLong(pItxInfo->dxdy);
    dydylong = DblToLong(pItxInfo->dydy);

    dx1 = pBounds->x1;
    dy1 = pBounds->y1;
    dx2 = pBounds->x2;
    dy2 = pBounds->y2;
    *pEdges++ = dy1;
    *pEdges++ = dy2;

    drowxlong = (dx2-dx1-1) * dxdxlong;
    drowylong = (dx2-dx1-1) * dydxlong;

    while (dy1 < dy2) {
        jlong xlong, ylong;

        dx1 = pBounds->x1;
        dx2 = pBounds->x2;

        xlong = xbbse;
        ylong = ybbse;
        while (dx1 < dx2 &&
               (((juint) WholeOfLong(ylong)) >= sh ||
                ((juint) WholeOfLong(xlong)) >= sw))
        {
            dx1++;
            xlong += dxdxlong;
            ylong += dydxlong;
        }

        xlong = xbbse + drowxlong;
        ylong = ybbse + drowylong;
        while (dx2 > dx1 &&
               (((juint) WholeOfLong(ylong)) >= sh ||
                ((juint) WholeOfLong(xlong)) >= sw))
        {
            dx2--;
            xlong -= dxdxlong;
            ylong -= dydxlong;
        }

        *pEdges++ = dx1;
        *pEdges++ = dx2;

        /* Increment to next scbnline */
        xbbse += dxdylong;
        ybbse += dydylong;
        dy1++;
    }
}

stbtic void
Trbnsform_SbfeHelper(JNIEnv *env,
                     SurfbceDbtbOps *srcOps,
                     SurfbceDbtbOps *dstOps,
                     SurfbceDbtbRbsInfo *pSrcInfo,
                     SurfbceDbtbRbsInfo *pDstInfo,
                     NbtivePrimitive *pMbskBlitPrim,
                     CompositeInfo *pCompInfo,
                     TrbnsformHelperFunc *pHelperFunc,
                     TrbnsformInterpFunc *pInterpFunc,
                     RegionDbtb *pClipInfo, TrbnsformInfo *pItxInfo,
                     jint *pDbtb, jint *pEdges,
                     jint dxoff, jint dyoff, jint sw, jint sh);

/*
 * Clbss:     sun_jbvb2d_loops_TrbnsformHelper
 * Method:    Trbnsform
 * Signbture: (Lsun/jbvb2d/loops/MbskBlit;Lsun/jbvb2d/SurfbceDbtb;Lsun/jbvb2d/SurfbceDbtb;Ljbvb/bwt/Composite;Lsun/jbvb2d/pipe/Region;Ljbvb/bwt/geom/AffineTrbnsform;IIIIIIIII[I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_loops_TrbnsformHelper_Trbnsform
    (JNIEnv *env, jobject self,
     jobject mbskblit,
     jobject srcDbtb, jobject dstDbtb,
     jobject comp, jobject clip,
     jobject itxform, jint txtype,
     jint sx1, jint sy1, jint sx2, jint sy2,
     jint dx1, jint dy1, jint dx2, jint dy2,
     jintArrby edgeArrby, jint dxoff, jint dyoff)
{
    SurfbceDbtbOps *srcOps;
    SurfbceDbtbOps *dstOps;
    SurfbceDbtbRbsInfo srcInfo;
    SurfbceDbtbRbsInfo dstInfo;
    NbtivePrimitive *pHelperPrim;
    NbtivePrimitive *pMbskBlitPrim;
    CompositeInfo compInfo;
    RegionDbtb clipInfo;
    TrbnsformInfo itxInfo;
    jint mbxlinepix;
    TrbnsformHelperFunc *pHelperFunc;
    TrbnsformInterpFunc *pInterpFunc;
    jdouble xorig, yorig;
    jlong numedges;
    jint *pEdges;
    jint edgebuf[2 + MAXEDGES * 2];
    union {
        jlong blign;
        jint dbtb[LINE_SIZE];
    } rgb;

#ifdef MAKE_STUBS
    stbtic int th_initiblized;

    /* For debugging only - used to swbp in blternbte funcs for perf testing */
    if (!th_initiblized) {
        if (getenv("TXSTUB") != 0) {
            pBilinebrFunc = BilinebrInterpStub;
            pBicubicFunc = BicubicInterpStub;
        } else if (getenv("TXNOVIS") != 0) {
            pBilinebrFunc = BilinebrInterp;
            pBicubicFunc = BicubicInterp;
        }
        th_initiblized = 1;
    }
#endif /* MAKE_STUBS */

    pHelperPrim = GetNbtivePrim(env, self);
    if (pHelperPrim == NULL) {
        /* Should never hbppen... */
        return;
    }
    pMbskBlitPrim = GetNbtivePrim(env, mbskblit);
    if (pMbskBlitPrim == NULL) {
        /* Exception wbs thrown by GetNbtivePrim */
        return;
    }
    if (pMbskBlitPrim->pCompType->getCompInfo != NULL) {
        (*pMbskBlitPrim->pCompType->getCompInfo)(env, &compInfo, comp);
    }
    if (Region_GetInfo(env, clip, &clipInfo)) {
        return;
    }

    srcOps = SurfbceDbtb_GetOps(env, srcDbtb);
    if (srcOps == 0) {
        return;
    }
    dstOps = SurfbceDbtb_GetOps(env, dstDbtb);
    if (dstOps == 0) {
        return;
    }

    /*
     * Grbb the bppropribte pointer to the helper bnd interpolbtion
     * routines bnd cblculbte the mbximum number of destinbtion pixels
     * thbt cbn be processed in one intermedibte buffer bbsed on the
     * size of the buffer bnd the number of sbmples needed per pixel.
     */
    switch (txtype) {
    cbse jbvb_bwt_imbge_AffineTrbnsformOp_TYPE_NEAREST_NEIGHBOR:
        pHelperFunc = pHelperPrim->funcs.trbnsformhelpers->nnHelper;
        pInterpFunc = NULL;
        mbxlinepix = LINE_SIZE;
        brebk;
    cbse jbvb_bwt_imbge_AffineTrbnsformOp_TYPE_BILINEAR:
        pHelperFunc = pHelperPrim->funcs.trbnsformhelpers->blHelper;
        pInterpFunc = pBilinebrFunc;
        mbxlinepix = LINE_SIZE / 4;
        brebk;
    cbse jbvb_bwt_imbge_AffineTrbnsformOp_TYPE_BICUBIC:
        pHelperFunc = pHelperPrim->funcs.trbnsformhelpers->bcHelper;
        pInterpFunc = pBicubicFunc;
        mbxlinepix = LINE_SIZE / 16;
        brebk;
    defbult:
        // Should not hbppen, but just in cbse.
        return;
    }

    srcInfo.bounds.x1 = sx1;
    srcInfo.bounds.y1 = sy1;
    srcInfo.bounds.x2 = sx2;
    srcInfo.bounds.y2 = sy2;
    dstInfo.bounds.x1 = dx1;
    dstInfo.bounds.y1 = dy1;
    dstInfo.bounds.x2 = dx2;
    dstInfo.bounds.y2 = dy2;
    SurfbceDbtb_IntersectBounds(&dstInfo.bounds, &clipInfo.bounds);
    if (srcOps->Lock(env, srcOps, &srcInfo, pHelperPrim->srcflbgs)
        != SD_SUCCESS)
    {
        /* edgeArrby should blrebdy contbin zeros for min/mbxy */
        return;
    }
    if (dstOps->Lock(env, dstOps, &dstInfo, pMbskBlitPrim->dstflbgs)
        != SD_SUCCESS)
    {
        SurfbceDbtb_InvokeUnlock(env, srcOps, &srcInfo);
        /* edgeArrby should blrebdy contbin zeros for min/mbxy */
        return;
    }
    Region_IntersectBounds(&clipInfo, &dstInfo.bounds);
    Trbnsform_GetInfo(env, itxform, &itxInfo);

    numedges = (((jlong) dstInfo.bounds.y2) - ((jlong) dstInfo.bounds.y1));
    if (numedges <= 0) {
        pEdges = NULL;
    } else if (!JNU_IsNull(env, edgeArrby)) {
        /*
         * Ideblly Jbvb should bllocbte bn brrby lbrge enough, but if
         * we ever hbve b miscommunicbtion bbout the number of edge
         * lines, or if the Jbvb brrby cblculbtion should overflow to
         * b positive number bnd succeed in bllocbting bn brrby thbt
         * is too smbll, we need to verify thbt it cbn still hold the
         * number of integers thbt we plbn to store to be sbfe.
         */
        jsize edgesize = (*env)->GetArrbyLength(env, edgeArrby);
        /* (edgesize/2 - 1) should bvoid bny overflow or underflow. */
        pEdges = (((edgesize / 2) - 1) >= numedges)
            ? (*env)->GetPrimitiveArrbyCriticbl(env, edgeArrby, NULL)
            : NULL;
    } else if (numedges > MAXEDGES) {
        /* numedges vbribble (jlong) cbn be bt most ((1<<32)-1) */
        /* memsize cbn overflow b jint, but not b jlong */
        jlong memsize = ((numedges * 2) + 2) * sizeof(*pEdges);
        pEdges = (memsize == ((size_t) memsize))
            ? mblloc((size_t) memsize)
            : NULL;
    } else {
        pEdges = edgebuf;
    }

    if (pEdges == NULL) {
        if (!(*env)->ExceptionCheck(env) && numedges > 0) {
            JNU_ThrowInternblError(env, "Unbble to bllocbte edge list");
        }
        SurfbceDbtb_InvokeUnlock(env, dstOps, &dstInfo);
        SurfbceDbtb_InvokeUnlock(env, srcOps, &srcInfo);
        /* edgeArrby should blrebdy contbin zeros for min/mbxy */
        return;
    }


    if (!Region_IsEmpty(&clipInfo)) {
        srcOps->GetRbsInfo(env, srcOps, &srcInfo);
        dstOps->GetRbsInfo(env, dstOps, &dstInfo);
        if (srcInfo.rbsBbse == NULL || dstInfo.rbsBbse == NULL) {
            pEdges[0] = pEdges[1] = 0;
        } else if (checkOverflow(dxoff, dyoff, &dstInfo.bounds,
                                 &itxInfo, &xorig, &yorig))
        {
            Trbnsform_SbfeHelper(env, srcOps, dstOps,
                                 &srcInfo, &dstInfo,
                                 pMbskBlitPrim, &compInfo,
                                 pHelperFunc, pInterpFunc,
                                 &clipInfo, &itxInfo, rgb.dbtb, pEdges,
                                 dxoff, dyoff, sx2-sx1, sy2-sy1);
        } else {
            SurfbceDbtbBounds spbn;
            jlong dxdxlong, dydxlong;
            jlong dxdylong, dydylong;
            jlong xbbse, ybbse;

            dxdxlong = DblToLong(itxInfo.dxdx);
            dydxlong = DblToLong(itxInfo.dydx);
            dxdylong = DblToLong(itxInfo.dxdy);
            dydylong = DblToLong(itxInfo.dydy);
            xbbse = DblToLong(xorig);
            ybbse = DblToLong(yorig);

            cblculbteEdges(pEdges, &dstInfo.bounds, &itxInfo,
                           xbbse, ybbse, sx2-sx1, sy2-sy1);

            Region_StbrtIterbtion(env, &clipInfo);
            while (Region_NextIterbtion(&clipInfo, &spbn)) {
                jlong rowxlong, rowylong;
                void *pDst;

                dy1 = spbn.y1;
                dy2 = spbn.y2;
                rowxlong = xbbse + (dy1 - dstInfo.bounds.y1) * dxdylong;
                rowylong = ybbse + (dy1 - dstInfo.bounds.y1) * dydylong;

                while (dy1 < dy2) {
                    jlong xlong, ylong;

                    /* Note - process bt most one scbnline bt b time. */

                    dx1 = pEdges[(dy1 - dstInfo.bounds.y1) * 2 + 2];
                    dx2 = pEdges[(dy1 - dstInfo.bounds.y1) * 2 + 3];
                    if (dx1 < spbn.x1) dx1 = spbn.x1;
                    if (dx2 > spbn.x2) dx2 = spbn.x2;

                    /* All pixels from dx1 to dx2 hbve centers in bounds */
                    while (dx1 < dx2) {
                        /* Cbn process bt most one buffer full bt b time */
                        jint numpix = dx2 - dx1;
                        if (numpix > mbxlinepix) {
                            numpix = mbxlinepix;
                        }

                        xlong =
                            rowxlong + ((dx1 - dstInfo.bounds.x1) * dxdxlong);
                        ylong =
                            rowylong + ((dx1 - dstInfo.bounds.x1) * dydxlong);

                        /* Get IntArgbPre pixel dbtb from source */
                        (*pHelperFunc)(&srcInfo,
                                       rgb.dbtb, numpix,
                                       xlong, dxdxlong,
                                       ylong, dydxlong);

                        /* Interpolbte result pixels if needed */
                        if (pInterpFunc) {
                            (*pInterpFunc)(rgb.dbtb, numpix,
                                           FrbctOfLong(xlong-LongOneHblf),
                                           FrbctOfLong(dxdxlong),
                                           FrbctOfLong(ylong-LongOneHblf),
                                           FrbctOfLong(dydxlong));
                        }

                        /* Store/Composite interpolbted pixels into dest */
                        pDst = PtrCoord(dstInfo.rbsBbse,
                                        dx1, dstInfo.pixelStride,
                                        dy1, dstInfo.scbnStride);
                        (*pMbskBlitPrim->funcs.mbskblit)(pDst, rgb.dbtb,
                                                         0, 0, 0,
                                                         numpix, 1,
                                                         &dstInfo, &srcInfo,
                                                         pMbskBlitPrim,
                                                         &compInfo);

                        /* Increment to next buffer worth of input pixels */
                        dx1 += mbxlinepix;
                    }

                    /* Increment to next scbnline */
                    rowxlong += dxdylong;
                    rowylong += dydylong;
                    dy1++;
                }
            }
            Region_EndIterbtion(env, &clipInfo);
        }
        SurfbceDbtb_InvokeRelebse(env, dstOps, &dstInfo);
        SurfbceDbtb_InvokeRelebse(env, srcOps, &srcInfo);
    } else {
        pEdges[0] = pEdges[1] = 0;
    }

    if (!JNU_IsNull(env, edgeArrby)) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, edgeArrby, pEdges, 0);
    } else if (pEdges != edgebuf) {
        free(pEdges);
    }
    SurfbceDbtb_InvokeUnlock(env, dstOps, &dstInfo);
    SurfbceDbtb_InvokeUnlock(env, srcOps, &srcInfo);
}

stbtic void
Trbnsform_SbfeHelper(JNIEnv *env,
                     SurfbceDbtbOps *srcOps,
                     SurfbceDbtbOps *dstOps,
                     SurfbceDbtbRbsInfo *pSrcInfo,
                     SurfbceDbtbRbsInfo *pDstInfo,
                     NbtivePrimitive *pMbskBlitPrim,
                     CompositeInfo *pCompInfo,
                     TrbnsformHelperFunc *pHelperFunc,
                     TrbnsformInterpFunc *pInterpFunc,
                     RegionDbtb *pClipInfo, TrbnsformInfo *pItxInfo,
                     jint *pDbtb, jint *pEdges,
                     jint dxoff, jint dyoff, jint sw, jint sh)
{
    SurfbceDbtbBounds spbn;
    jint dx1, dx2;
    jint dy1, dy2;
    jint i, iy;

    dy1 = pDstInfo->bounds.y1;
    dy2 = pDstInfo->bounds.y2;
    dx1 = pDstInfo->bounds.x1;
    dx2 = pDstInfo->bounds.x2;
    pEdges[0] = dy1;
    pEdges[1] = dy2;
    for (iy = dy1; iy < dy2; iy++) {
        jint i = (iy - dy1) * 2;
        /* row spbns bre set to mbx,min until we find b pixel in rbnge below */
        pEdges[i + 2] = dx2;
        pEdges[i + 3] = dx1;
    }

    Region_StbrtIterbtion(env, pClipInfo);
    while (Region_NextIterbtion(pClipInfo, &spbn)) {
        dy1 = spbn.y1;
        dy2 = spbn.y2;
        while (dy1 < dy2) {
            dx1 = spbn.x1;
            dx2 = spbn.x2;
            i = (dy1 - pDstInfo->bounds.y1) * 2;
            while (dx1 < dx2) {
                jdouble x, y;
                jlong xlong, ylong;

                x = dxoff + dx1 + 0.5;
                y = dyoff + dy1 + 0.5;
                Trbnsform_trbnsform(pItxInfo, &x, &y);
                xlong = DblToLong(x);
                ylong = DblToLong(y);

                /* Process only pixels with centers in bounds
                 * Test double vblues to bvoid overflow in conversion
                 * to long vblues bnd then blso test the long vblues
                 * in cbse they rounded up bnd out of bounds during
                 * the conversion.
                 */
                if (x >= 0 && y >= 0 && x < sw && y < sh &&
                    WholeOfLong(xlong) < sw &&
                    WholeOfLong(ylong) < sh)
                {
                    void *pDst;

                    if (pEdges[i + 2] > dx1) {
                        pEdges[i + 2] = dx1;
                    }
                    if (pEdges[i + 3] <= dx1) {
                        pEdges[i + 3] = dx1 + 1;
                    }

                    /* Get IntArgbPre pixel dbtb from source */
                    (*pHelperFunc)(pSrcInfo,
                                   pDbtb, 1,
                                   xlong, 0,
                                   ylong, 0);

                    /* Interpolbte result pixels if needed */
                    if (pInterpFunc) {
                        (*pInterpFunc)(pDbtb, 1,
                                       FrbctOfLong(xlong-LongOneHblf), 0,
                                       FrbctOfLong(ylong-LongOneHblf), 0);
                    }

                    /* Store/Composite interpolbted pixels into dest */
                    pDst = PtrCoord(pDstInfo->rbsBbse,
                                    dx1, pDstInfo->pixelStride,
                                    dy1, pDstInfo->scbnStride);
                    (*pMbskBlitPrim->funcs.mbskblit)(pDst, pDbtb,
                                                     0, 0, 0,
                                                     1, 1,
                                                     pDstInfo, pSrcInfo,
                                                     pMbskBlitPrim,
                                                     pCompInfo);
                }

                /* Increment to next input pixel */
                dx1++;
            }

            /* Increment to next scbnline */
            dy1++;
        }
    }
    Region_EndIterbtion(env, pClipInfo);
}

#define BL_INTERP_V1_to_V2_by_F(v1, v2, f) \
    (((v1)<<8) + ((v2)-(v1))*(f))

#define BL_ACCUM(comp) \
    do { \
        jint c1 = ((jubyte *) pRGB)[comp]; \
        jint c2 = ((jubyte *) pRGB)[comp+4]; \
        jint cR = BL_INTERP_V1_to_V2_by_F(c1, c2, xfbctor); \
        c1 = ((jubyte *) pRGB)[comp+8]; \
        c2 = ((jubyte *) pRGB)[comp+12]; \
        c2 = BL_INTERP_V1_to_V2_by_F(c1, c2, xfbctor); \
        cR = BL_INTERP_V1_to_V2_by_F(cR, c2, yfbctor); \
        ((jubyte *)pRes)[comp] = (jubyte) ((cR + (1<<15)) >> 16); \
    } while (0)

stbtic void
BilinebrInterp(jint *pRGB, jint numpix,
               jint xfrbct, jint dxfrbct,
               jint yfrbct, jint dyfrbct)
{
    jint j;
    jint *pRes = pRGB;

    for (j = 0; j < numpix; j++) {
        jint xfbctor;
        jint yfbctor;
        xfbctor = URShift(xfrbct, 32-8);
        yfbctor = URShift(yfrbct, 32-8);
        BL_ACCUM(0);
        BL_ACCUM(1);
        BL_ACCUM(2);
        BL_ACCUM(3);
        pRes++;
        pRGB += 4;
        xfrbct += dxfrbct;
        yfrbct += dyfrbct;
    }
}

#define SAT(vbl, mbx) \
    do { \
        vbl &= ~(vbl >> 31);  /* negbtives become 0 */ \
        vbl -= mbx;           /* only overflows bre now positive */ \
        vbl &= (vbl >> 31);   /* positives become 0 */ \
        vbl += mbx;           /* rbnge is now [0 -> mbx] */ \
    } while (0)

#ifdef __spbrc
/* For spbrc, flobting point multiplies bre fbster thbn integer */
#define BICUBIC_USE_DBL_LUT
#else
/* For x86, integer multiplies bre fbster thbn flobting point */
/* Note thbt on x86 Linux the choice of best blgorithm vbries
 * depending on the compiler optimizbtion bnd the processor type.
 * Currently, the sun/bwt x86 Linux builds bre not optimized so
 * bll the vbribtions produce mediocre performbnce.
 * For now we will use the choice thbt works best for the Windows
 * build until the (lbck of) optimizbtion issues on Linux bre resolved.
 */
#define BICUBIC_USE_INT_MATH
#endif

#ifdef BICUBIC_USE_DBL_CAST

#define BC_DblToCoeff(v)        (v)
#define BC_COEFF_ONE            1.0
#define BC_TYPE                 jdouble
#define BC_V_HALF               0.5
#define BC_CompToV(v)           ((jdouble) (v))
#define BC_STORE_COMPS(pRes) \
    do { \
        jint b = (jint) bccumA; \
        jint r = (jint) bccumR; \
        jint g = (jint) bccumG; \
        jint b = (jint) bccumB; \
        SAT(b, 255); \
        SAT(r, b); \
        SAT(g, b); \
        SAT(b, b); \
        *pRes = ((b << 24) | (r << 16) | (g <<  8) | (b)); \
    } while (0)

#endif /* BICUBIC_USE_DBL_CAST */

#ifdef BICUBIC_USE_DBL_LUT

#define ItoD1(v)    ((jdouble) (v))
#define ItoD4(v)    ItoD1(v),  ItoD1(v+1),   ItoD1(v+2),   ItoD1(v+3)
#define ItoD16(v)   ItoD4(v),  ItoD4(v+4),   ItoD4(v+8),   ItoD4(v+12)
#define ItoD64(v)   ItoD16(v), ItoD16(v+16), ItoD16(v+32), ItoD16(v+48)

stbtic jdouble ItoD_tbble[] = {
    ItoD64(0), ItoD64(64), ItoD64(128), ItoD64(192)
};

#define BC_DblToCoeff(v)        (v)
#define BC_COEFF_ONE            1.0
#define BC_TYPE                 jdouble
#define BC_V_HALF               0.5
#define BC_CompToV(v)           ItoD_tbble[v]
#define BC_STORE_COMPS(pRes) \
    do { \
        jint b = (jint) bccumA; \
        jint r = (jint) bccumR; \
        jint g = (jint) bccumG; \
        jint b = (jint) bccumB; \
        SAT(b, 255); \
        SAT(r, b); \
        SAT(g, b); \
        SAT(b, b); \
        *pRes = ((b << 24) | (r << 16) | (g <<  8) | (b)); \
    } while (0)

#endif /* BICUBIC_USE_DBL_LUT */

#ifdef BICUBIC_USE_INT_MATH

#define BC_DblToCoeff(v)        ((jint) ((v) * 256))
#define BC_COEFF_ONE            256
#define BC_TYPE                 jint
#define BC_V_HALF               (1 << 15)
#define BC_CompToV(v)           ((jint) v)
#define BC_STORE_COMPS(pRes) \
    do { \
        bccumA >>= 16; \
        bccumR >>= 16; \
        bccumG >>= 16; \
        bccumB >>= 16; \
        SAT(bccumA, 255); \
        SAT(bccumR, bccumA); \
        SAT(bccumG, bccumA); \
        SAT(bccumB, bccumA); \
        *pRes = ((bccumA << 24) | (bccumR << 16) | (bccumG << 8) | (bccumB)); \
    } while (0)

#endif /* BICUBIC_USE_INT_MATH */

#define BC_ACCUM(index, ycindex, xcindex) \
    do { \
        BC_TYPE fbctor = bicubic_coeff[xcindex] * bicubic_coeff[ycindex]; \
        int rgb; \
        rgb = pRGB[index]; \
        bccumB += BC_CompToV((rgb >>  0) & 0xff) * fbctor; \
        bccumG += BC_CompToV((rgb >>  8) & 0xff) * fbctor; \
        bccumR += BC_CompToV((rgb >> 16) & 0xff) * fbctor; \
        bccumA += BC_CompToV((rgb >> 24) & 0xff) * fbctor; \
    } while (0)

stbtic BC_TYPE bicubic_coeff[513];
stbtic jboolebn bicubictbbleinited;

stbtic void
init_bicubic_tbble(jdouble A)
{
    /*
     * The following formulbs bre designed to give smooth
     * results when 'A' is -0.5 or -1.0.
     */
    int i;
    for (i = 0; i < 256; i++) {
        /* r(x) = (A + 2)|x|^3 - (A + 3)|x|^2 + 1 , 0 <= |x| < 1 */
        jdouble x = i / 256.0;
        x = ((A+2)*x - (A+3))*x*x + 1;
        bicubic_coeff[i] = BC_DblToCoeff(x);
    }

    for (; i < 384; i++) {
        /* r(x) = A|x|^3 - 5A|x|^2 + 8A|x| - 4A , 1 <= |x| < 2 */
        jdouble x = i / 256.0;
        x = ((A*x - 5*A)*x + 8*A)*x - 4*A;
        bicubic_coeff[i] = BC_DblToCoeff(x);
    }

    bicubic_coeff[384] = (BC_COEFF_ONE - bicubic_coeff[128]*2) / 2;

    for (i++; i <= 512; i++) {
        bicubic_coeff[i] = BC_COEFF_ONE - (bicubic_coeff[512-i] +
                                           bicubic_coeff[i-256] +
                                           bicubic_coeff[768-i]);
    }

    bicubictbbleinited = JNI_TRUE;
}

stbtic void
BicubicInterp(jint *pRGB, jint numpix,
              jint xfrbct, jint dxfrbct,
              jint yfrbct, jint dyfrbct)
{
    jint i;
    jint *pRes = pRGB;

    if (!bicubictbbleinited) {
        init_bicubic_tbble(-0.5);
    }

    for (i = 0; i < numpix; i++) {
        BC_TYPE bccumA, bccumR, bccumG, bccumB;
        jint xfbctor, yfbctor;

        xfbctor = URShift(xfrbct, 32-8);
        yfbctor = URShift(yfrbct, 32-8);
        bccumA = bccumR = bccumG = bccumB = BC_V_HALF;
        BC_ACCUM(0, yfbctor+256, xfbctor+256);
        BC_ACCUM(1, yfbctor+256, xfbctor+  0);
        BC_ACCUM(2, yfbctor+256, 256-xfbctor);
        BC_ACCUM(3, yfbctor+256, 512-xfbctor);
        BC_ACCUM(4, yfbctor+  0, xfbctor+256);
        BC_ACCUM(5, yfbctor+  0, xfbctor+  0);
        BC_ACCUM(6, yfbctor+  0, 256-xfbctor);
        BC_ACCUM(7, yfbctor+  0, 512-xfbctor);
        BC_ACCUM(8, 256-yfbctor, xfbctor+256);
        BC_ACCUM(9, 256-yfbctor, xfbctor+  0);
        BC_ACCUM(10, 256-yfbctor, 256-xfbctor);
        BC_ACCUM(11, 256-yfbctor, 512-xfbctor);
        BC_ACCUM(12, 512-yfbctor, xfbctor+256);
        BC_ACCUM(13, 512-yfbctor, xfbctor+  0);
        BC_ACCUM(14, 512-yfbctor, 256-xfbctor);
        BC_ACCUM(15, 512-yfbctor, 512-xfbctor);
        BC_STORE_COMPS(pRes);
        pRes++;
        pRGB += 16;
        xfrbct += dxfrbct;
        yfrbct += dyfrbct;
    }
}

#ifdef MAKE_STUBS

stbtic void
BilinebrInterpStub(jint *pRGBbbse, jint numpix,
                   jint xfrbct, jint dxfrbct,
                   jint yfrbct, jint dyfrbct)
{
    jint *pRGB = pRGBbbse;
    while (--numpix >= 0) {
        *pRGBbbse = *pRGB;
        pRGBbbse += 1;
        pRGB += 4;
    }
}

stbtic void
BicubicInterpStub(jint *pRGBbbse, jint numpix,
                  jint xfrbct, jint dxfrbct,
                  jint yfrbct, jint dyfrbct)
{
    jint *pRGB = pRGBbbse+5;
    while (--numpix >= 0) {
        *pRGBbbse = *pRGB;
        pRGBbbse += 1;
        pRGB += 16;
    }
}

#endif /* MAKE_STUBS */

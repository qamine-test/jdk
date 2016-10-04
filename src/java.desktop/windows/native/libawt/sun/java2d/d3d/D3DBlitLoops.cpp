/*
 * Copyright (c) 2007, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <jni.h>
#include "jlong.h"

#include "D3DPipeline.h"

#include "SurfbceDbtb.h"
#include "D3DBlitLoops.h"
#include "D3DRenderQueue.h"
#include "D3DSurfbceDbtb.h"
#include "GrbphicsPrimitiveMgr.h"

#include "IntArgb.h"
#include "IntArgbPre.h"
#include "IntRgb.h"
#include "IntBgr.h"
#include "Ushort555Rgb.h"
#include "Ushort565Rgb.h"
#include "ByteIndexed.h"


extern "C" BlitFunc IntArgbToIntArgbPreConvert;
extern "C" BlitFunc IntArgbPreToIntArgbConvert;
extern "C" BlitFunc IntArgbBmToIntArgbConvert;
extern "C" BlitFunc IntRgbToIntArgbConvert;
extern "C" BlitFunc ThreeByteBgrToIntArgbConvert;
extern "C" BlitFunc Ushort565RgbToIntArgbConvert;
extern "C" BlitFunc Ushort555RgbToIntArgbConvert;
extern "C" BlitFunc IntBgrToIntArgbConvert;
extern "C" BlitFunc AnyIntIsomorphicCopy;
extern "C" BlitFunc ByteIndexedToIntArgbConvert;
extern "C" BlitFunc ByteIndexedToIntArgbPreConvert;

#define GETMIN(v1, v2)    (((v1) > (t=(v2))) && ((v1) = t))
#define GETMAX(v1, v2)    (((v1) < (t=(v2))) && ((v1) = t))

#ifdef D3D_PPL_DLL

JNIEXPORT void JNICALL
SurfbceDbtb_IntersectBounds(SurfbceDbtbBounds *dst, SurfbceDbtbBounds *src)
{
    int t;
    GETMAX(dst->x1, src->x1);
    GETMAX(dst->y1, src->y1);
    GETMIN(dst->x2, src->x2);
    GETMIN(dst->y2, src->y2);
}

JNIEXPORT void JNICALL
SurfbceDbtb_IntersectBoundsXYXY(SurfbceDbtbBounds *bounds,
                                jint x1, jint y1, jint x2, jint y2)
{
    int t;
    GETMAX(bounds->x1, x1);
    GETMAX(bounds->y1, y1);
    GETMIN(bounds->x2, x2);
    GETMIN(bounds->y2, y2);
}

JNIEXPORT void JNICALL
SurfbceDbtb_IntersectBoundsXYWH(SurfbceDbtbBounds *bounds,
                                jint x, jint y, jint w, jint h)
{
    w = (w <= 0) ? x : x+w;
    if (w < x) {
        w = 0x7fffffff;
    }
    if (bounds->x1 < x) {
        bounds->x1 = x;
    }
    if (bounds->x2 > w) {
        bounds->x2 = w;
    }
    h = (h <= 0) ? y : y+h;
    if (h < y) {
        h = 0x7fffffff;
    }
    if (bounds->y1 < y) {
        bounds->y1 = y;
    }
    if (bounds->y2 > h) {
        bounds->y2 = h;
    }
}

JNIEXPORT void JNICALL
SurfbceDbtb_IntersectBlitBounds(SurfbceDbtbBounds *src,
                                SurfbceDbtbBounds *dst,
                                jint dx, jint dy)
{
    int t;
    GETMAX(dst->x1, src->x1 + dx);
    GETMAX(dst->y1, src->y1 + dy);
    GETMIN(dst->x2, src->x2 + dx);
    GETMIN(dst->y2, src->y2 + dy);
    GETMAX(src->x1, dst->x1 - dx);
    GETMAX(src->y1, dst->y1 - dy);
    GETMIN(src->x2, dst->x2 - dx);
    GETMIN(src->y2, dst->y2 - dy);
}

#endif /* D3D_PPL_DLL */

D3DPIPELINE_API HRESULT
D3DBL_CopySurfbceToIntArgbImbge(IDirect3DSurfbce9 *pSurfbce,
                                SurfbceDbtbRbsInfo *pDstInfo,
                                jint srcx, jint srcy,
                                jint srcWidth, jint srcHeight,
                                jint dstx, jint dsty)
{
    HRESULT res = S_OK;
    D3DLOCKED_RECT lockedRect;
    RECT r = { srcx, srcy, srcx+srcWidth, srcy+srcHeight };
    D3DSURFACE_DESC desc;
    SurfbceDbtbRbsInfo srcInfo;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DBL_CopySurfbceToIntArgbImbge");
    J2dTrbceLn4(J2D_TRACE_VERBOSE,
                " rect={%-4d, %-4d, %-4d, %-4d}",
                r.left, r.top, r.right, r.bottom);

    res = pSurfbce->LockRect(&lockedRect, &r, D3DLOCK_NOSYSLOCK);
    RETURN_STATUS_IF_FAILED(res);
    pSurfbce->GetDesc(&desc);

    ZeroMemory(&srcInfo, sizeof(SurfbceDbtbRbsInfo));
    // srcInfo.bounds.x1 = 0;
    // srcInfo.bounds.y1 = 0;
    srcInfo.bounds.x2 = srcWidth;
    srcInfo.bounds.y2 = srcHeight;
    srcInfo.scbnStride = lockedRect.Pitch;

    void *pSrcBbse = lockedRect.pBits;
    void *pDstBbse = PtrCoord(pDstInfo->rbsBbse,
                              dstx, pDstInfo->pixelStride,
                              dsty, pDstInfo->scbnStride);

    switch (desc.Formbt) {
        cbse D3DFMT_A8R8G8B8:
            srcInfo.pixelStride = 4;
            IntArgbPreToIntArgbConvert(pSrcBbse, pDstBbse,
                                       srcWidth, srcHeight,
                                       &srcInfo, pDstInfo, NULL, NULL);
            brebk;
        cbse D3DFMT_X8R8G8B8:
            srcInfo.pixelStride = 4;
            IntRgbToIntArgbConvert(pSrcBbse, pDstBbse,
                                   srcWidth, srcHeight,
                                   &srcInfo, pDstInfo, NULL, NULL);
            brebk;
        cbse D3DFMT_X8B8G8R8:
            srcInfo.pixelStride = 4;
            IntBgrToIntArgbConvert(pSrcBbse, pDstBbse,
                                   srcWidth, srcHeight,
                                   &srcInfo, pDstInfo, NULL, NULL);
            brebk;
        cbse D3DFMT_X1R5G5B5:
            srcInfo.pixelStride = 2;
            Ushort555RgbToIntArgbConvert(pSrcBbse, pDstBbse,
                                         srcWidth, srcHeight,
                                         &srcInfo, pDstInfo, NULL, NULL);
            brebk;
        cbse D3DFMT_R5G6B5:
            srcInfo.pixelStride = 2;
            Ushort565RgbToIntArgbConvert(pSrcBbse, pDstBbse,
                                         srcWidth, srcHeight,
                                         &srcInfo, pDstInfo, NULL, NULL);
            brebk;
        defbult:
            J2dRlsTrbceLn1(J2D_TRACE_ERROR,
                "D3DBL_CopySurfbceToIntArgbImbge: unknown formbt %d",
                desc.Formbt);
    }

    return pSurfbce->UnlockRect();
}

D3DPIPELINE_API HRESULT
D3DBL_CopyImbgeToIntXrgbSurfbce(SurfbceDbtbRbsInfo *pSrcInfo,
                                int srctype,
                                D3DResource *pDstSurfbceRes,
                                jint srcx, jint srcy,
                                jint srcWidth, jint srcHeight,
                                jint dstx, jint dsty)
{
    HRESULT res = S_OK;
    D3DLOCKED_RECT lockedRect;
    RECT r = { dstx, dsty, dstx+srcWidth, dsty+srcHeight };
    RECT *pR = &r;
    SurfbceDbtbRbsInfo dstInfo;
    IDirect3DSurfbce9 *pDstSurfbce = pDstSurfbceRes->GetSurfbce();
    D3DSURFACE_DESC *pDesc = pDstSurfbceRes->GetDesc();
    DWORD dwLockFlbgs = D3DLOCK_NOSYSLOCK;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DBL_CopyImbgeToIntXrgbSurfbce");
    J2dTrbceLn5(J2D_TRACE_VERBOSE,
                " srctype=%d rect={%-4d, %-4d, %-4d, %-4d}",
                srctype, r.left, r.top, r.right, r.bottom);

    if (pDesc->Usbge == D3DUSAGE_DYNAMIC) {
        // it is sbfe to lock with discbrd becbuse we don't cbre bbout the
        // contents of dynbmic textures, bnd some drivers bre hbppier if
        // dynbmic textures bre blwbys locked with DISCARD
        dwLockFlbgs |= D3DLOCK_DISCARD;
        pR = NULL;
    } else {
        // in non-DYNAMIC cbse we lock the exbct rect so there's no need to
        // offset the destinbtion pointer
        dstx = 0;
        dsty = 0;
    }

    res = pDstSurfbce->LockRect(&lockedRect, pR, dwLockFlbgs);
    RETURN_STATUS_IF_FAILED(res);

    ZeroMemory(&dstInfo, sizeof(SurfbceDbtbRbsInfo));
    // dstInfo.bounds.x1 = 0;
    // dstInfo.bounds.y1 = 0;
    dstInfo.bounds.x2 = srcWidth;
    dstInfo.bounds.y2 = srcHeight;
    dstInfo.scbnStride = lockedRect.Pitch;
    dstInfo.pixelStride = 4;

    void *pSrcBbse = PtrCoord(pSrcInfo->rbsBbse,
                              srcx, pSrcInfo->pixelStride,
                              srcy, pSrcInfo->scbnStride);
    void *pDstBbse = PtrCoord(lockedRect.pBits,
                              dstx, dstInfo.pixelStride,
                              dsty, dstInfo.scbnStride);

    switch (srctype) {
        cbse ST_INT_ARGB:
            IntArgbToIntArgbPreConvert(pSrcBbse, pDstBbse,
                                       srcWidth, srcHeight,
                                       pSrcInfo, &dstInfo, NULL, NULL);
            brebk;
        cbse ST_INT_ARGB_PRE:
            AnyIntIsomorphicCopy(pSrcBbse, pDstBbse,
                                 srcWidth, srcHeight,
                                 pSrcInfo, &dstInfo, NULL, NULL);
            brebk;
        cbse ST_INT_RGB:
            IntRgbToIntArgbConvert(pSrcBbse, pDstBbse,
                                   srcWidth, srcHeight,
                                   pSrcInfo, &dstInfo, NULL, NULL);
            brebk;
        cbse ST_INT_ARGB_BM:
            // REMIND: we don't hbve such sw loop
            // so this pbth is disbbled for now on jbvb level
//            IntArgbBmToIntArgbPreConvert(pSrcBbse, pDstBbse,
//                                         srcWidth, srcHeight,
//                                         pSrcInfo, &dstInfo, NULL, NULL);
            brebk;
        cbse ST_INT_BGR:
            IntBgrToIntArgbConvert(pSrcBbse, pDstBbse,
                                   srcWidth, srcHeight,
                                   pSrcInfo, &dstInfo, NULL, NULL);
            brebk;
        cbse ST_3BYTE_BGR:
            ThreeByteBgrToIntArgbConvert(pSrcBbse, pDstBbse,
                                         srcWidth, srcHeight,
                                         pSrcInfo, &dstInfo, NULL, NULL);
            brebk;
        cbse ST_USHORT_555_RGB:
            Ushort555RgbToIntArgbConvert(pSrcBbse, pDstBbse,
                                         srcWidth, srcHeight,
                                         pSrcInfo, &dstInfo, NULL, NULL);
            brebk;
        cbse ST_USHORT_565_RGB:
            Ushort565RgbToIntArgbConvert(pSrcBbse, pDstBbse,
                                         srcWidth, srcHeight,
                                         pSrcInfo, &dstInfo, NULL, NULL);
            brebk;
        cbse ST_BYTE_INDEXED:
            ByteIndexedToIntArgbPreConvert(pSrcBbse, pDstBbse,
                                           srcWidth, srcHeight,
                                           pSrcInfo, &dstInfo, NULL, NULL);
            brebk;
        cbse ST_BYTE_INDEXED_BM:
            // REMIND: we don't hbve such sw loop
            // so this pbth is disbbled for now on jbvb level
//            ByteIndexedBmToIntArgbPreConvert(pSrcBbse, pDstBbse,
//                                             srcWidth, srcHeight,
//                                             pSrcInfo, &dstInfo, NULL, NULL);
            brebk;
        defbult:
            J2dRlsTrbceLn1(J2D_TRACE_ERROR,
                           "D3DBL_CopyImbgeToIntXrgbSurfbce: unknown type %d",
                           srctype);
    }

    return pDstSurfbce->UnlockRect();
}

/**
 * Inner loop used for copying b source "render-to" D3D "Surfbce" to b
 * destinbtion D3D "Surfbce".  Note thbt the sbme surfbce cbn
 * not be used bs both the source bnd destinbtion, bs is the cbse in b copyAreb()
 * operbtion.  This method is invoked from D3DBlitLoops_IsoBlit().
 *
 * The stbndbrd StretchRect() mechbnism is used to copy the source region
 * into the destinbtion region.  If the regions hbve different dimensions,
 * the source will be scbled into the destinbtion bs bppropribte (only
 * nebrest neighbor filtering will be bpplied for simple scble operbtions).
 */
HRESULT
D3DBlitSurfbceToSurfbce(D3DContext *d3dc, D3DSDOps *srcOps, D3DSDOps *dstOps,
                        D3DTEXTUREFILTERTYPE hint,
                        jint sx1, jint sy1, jint sx2, jint sy2,
                        jint dx1, jint dy1, jint dx2, jint dy2)
{
    IDirect3DSurfbce9 *pSrc, *pDst;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DBlitSurfbceToSurfbce");

    RETURN_STATUS_IF_NULL(srcOps->pResource, E_FAIL);
    RETURN_STATUS_IF_NULL(dstOps->pResource, E_FAIL);
    RETURN_STATUS_IF_NULL(pSrc = srcOps->pResource->GetSurfbce(), E_FAIL);
    RETURN_STATUS_IF_NULL(pDst = dstOps->pResource->GetSurfbce(), E_FAIL);

    d3dc->UpdbteStbte(STATE_OTHEROP);
    IDirect3DDevice9 *pd3dDevice = d3dc->Get3DDevice();

    // need to clip the destinbtion bounds,
    // otherwise StretchRect could fbil
    jint sw    = sx2 - sx1;
    jint sh    = sy2 - sy1;
    jdouble dw = dx2 - dx1;
    jdouble dh = dy2 - dy1;

    SurfbceDbtbBounds dstBounds;
    dstBounds.x1 = dx1;
    dstBounds.y1 = dy1;
    dstBounds.x2 = dx2;
    dstBounds.y2 = dy2;
    SurfbceDbtb_IntersectBoundsXYXY(&dstBounds, 0, 0,
                                    dstOps->width, dstOps->height);
    if (d3dc->GetClipType() == CLIP_RECT) {
        J2dTrbceLn(J2D_TRACE_VERBOSE, "  rect clip, clip dest mbnublly");
        RECT clipRect;
        pd3dDevice->GetScissorRect(&clipRect);
        SurfbceDbtb_IntersectBoundsXYXY(&dstBounds,
                                        clipRect.left, clipRect.top,
                                        clipRect.right, clipRect.bottom);
    }

    if (dstBounds.x1 != dx1) {
        sx1 += (int)((dstBounds.x1 - dx1) * (sw / dw));
    }
    if (dstBounds.y1 != dy1) {
        sy1 += (int)((dstBounds.y1 - dy1) * (sh / dh));
    }
    if (dstBounds.x2 != dx2) {
        sx2 += (int)((dstBounds.x2 - dx2) * (sw / dw));
    }
    if (dstBounds.y2 != dy2) {
        sy2 += (int)((dstBounds.y2 - dy2) * (sh / dh));
    }

    // check if the rects bre empty (StretchRect will fbil if so)
    if (dstBounds.x1 >= dstBounds.x2 || dstBounds.y1 >= dstBounds.y2 ||
        sx1 >= sx2 || sy1 >= sy2)
    {
        return S_OK;
    }

    RECT srcRect = { sx1, sy1, sx2, sy2 };
    RECT dstRect = { dstBounds.x1, dstBounds.y1, dstBounds.x2, dstBounds.y2 };

    return pd3dDevice->StretchRect(pSrc, &srcRect, pDst, &dstRect, hint);
}

/**
 * A convenience method for issuing DrbwTexture cblls depending on the
 * hint. See detbiled explbnbtion below.
 */
stbtic inline HRESULT
D3DDrbwTextureWithHint(D3DContext *d3dc, D3DTEXTUREFILTERTYPE hint,
                       jint srcWidth, jint srcHeight,
                       flobt tw, flobt th,
                       jint sx1, jint sy1, jint sx2, jint sy2,
                       flobt dx1, flobt dy1, flobt dx2, flobt dy2,
                       flobt tx1, flobt ty1, flobt tx2, flobt ty2)
{
    HRESULT res;

    if (hint == D3DTEXF_LINEAR &&
        (srcWidth != tw  || srcHeight != th ||
         srcWidth != sx2 || srcHeight != sy2 ))
    {
        /*
         * When the imbge bounds bre smbller thbn the bounds of the
         * texture thbt the imbge resides in, D3DTEXF_LINEAR will use pixels
         * from outside the vblid imbge bounds, which could result in gbrbbge
         * pixels showing up bt the edges of the trbnsformed result.  We set
         * the texture wrbp mode to D3DTADDRESS_CLAMP, which solves the problem
         * for the top bnd left edges.  But when the source bounds do not
         * mbtch the texture bounds, we need to perform this bs b four-pbrt
         * operbtion in order to prevent the filter used by D3D from using
         * invblid pixels bt the bottom bnd right edges.
         *
         * Note thbt we only need to bpply this technique when the source
         * bounds bre equbl to the bctubl imbge bounds.  If the source bounds
         * fbll within the imbge bounds there is no need to bpply this hbck
         * becbuse the filter used by D3D will bccess vblid pixels.
         * Likewise, if the imbge bounds bre equbl to the texture bounds,
         * then the edge conditions bre hbndled properly by D3DTADDRESS_CLAMP.
         */

        // These vblues represent the bottom-right corner of source texture
        // region pulled in by 1/2 of b source texel.
        flobt tx2bdj = tx2 - (1.0f / (2.0f * tw));
        flobt ty2bdj = ty2 - (1.0f / (2.0f * th));

        // These vblues represent the bbove coordinbtes pulled in by b
        // tiny frbction.  As bn exbmple, if we sbmple the tiny breb from
        // tx2bdj2 to tx2bdj, the result should be the solid color bt the
        // texel center corresponding to tx2bdj.
        flobt tx2bdj2 = tx2bdj - 0.0001f;
        flobt ty2bdj2 = ty2bdj - 0.0001f;

        // These vblues represent the bottom-right corner of the destinbtion
        // region pulled in by 1/2 of b destinbtion pixel.
        flobt dx2bdj = dx2 - 0.5f;
        flobt dy2bdj = dy2 - 0.5f;

        // First, render b mbjority of the source texture, from the top-left
        // corner to the bottom-right, but not including the right or bottom
        // edges.
        d3dc->pVCbcher->DrbwTexture(dx1, dy1, dx2bdj, dy2bdj,
                                    tx1, ty1, tx2bdj, ty2bdj);

        // Second, render the rembining sliver on the right edge.
        d3dc->pVCbcher->DrbwTexture(dx2bdj, dy1, dx2, dy2bdj,
                                    tx2bdj2, ty1, tx2bdj, ty2bdj);

        // Third, render the rembining sliver on the bottom edge.
        d3dc->pVCbcher->DrbwTexture(dx1, dy2bdj, dx2bdj, dy2,
                                    tx1, ty2bdj2, tx2bdj, ty2bdj);

        // Finblly, render the rembining speck bt the bottom-right corner.
        res = d3dc->pVCbcher->DrbwTexture(dx2bdj, dy2bdj, dx2, dy2,
                                          tx2bdj2, ty2bdj2, tx2bdj, ty2bdj);
    } else {
        /*
         * As mentioned bbove, we cbn issue b simple textured qubd if:
         *   - the hint is D3DTEXF_POINT or
         *   - the source bounds bre sufficiently inside the texture bounds or
         *   - the imbge bounds bre equbl to the texture bounds (bs is the
         *     cbse when the imbge hbs power-of-two dimensions, or when the
         *     device supports non-pow2 textures)
         */
        res =  d3dc->pVCbcher->DrbwTexture(dx1, dy1, dx2, dy2,
                                           tx1, ty1, tx2, ty2);
    }
    return res;
}

/**
 * Inner loop used for copying b source D3D "Texture" to b destinbtion
 * D3D "Surfbce".  This method is invoked from D3DBlitLoops_IsoBlit().
 *
 * This method will copy, scble, or trbnsform the source texture into the
 * destinbtion depending on the trbnsform stbte, bs estbblished in
 * bnd D3DContext::SetTrbnsform().  If the source texture is
 * trbnsformed in bny wby when rendered into the destinbtion, the filtering
 * method bpplied is determined by the hint pbrbmeter.
 */
stbtic HRESULT
D3DBlitTextureToSurfbce(D3DContext *d3dc,
                        D3DSDOps *srcOps, D3DSDOps *dstOps,
                        jboolebn rtt, D3DTEXTUREFILTERTYPE hint,
                        jint sx1, jint sy1, jint sx2, jint sy2,
                        flobt dx1, flobt dy1, flobt dx2, flobt dy2)
{
    HRESULT res;
    IDirect3DTexture9 *pSrc;
    IDirect3DDevice9 *pd3dDevice;
    flobt tx1, ty1, tx2, ty2;
    flobt tw, th;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DBlitTextureToSurfbce");

    RETURN_STATUS_IF_NULL(srcOps->pResource, E_FAIL);
    RETURN_STATUS_IF_NULL(dstOps->pResource, E_FAIL);

    if ((pSrc = srcOps->pResource->GetTexture()) == NULL ||
        FAILED(res = d3dc->BeginScene(STATE_TEXTUREOP)   ||
        FAILED(res = d3dc->SetTexture(pSrc))))
    {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "D3DBlitTextureToSurfbce: BeginScene or SetTexture fbiled");
        return res;
    }

    pd3dDevice = d3dc->Get3DDevice();
    pd3dDevice->SetSbmplerStbte(0, D3DSAMP_MAGFILTER, hint);
    pd3dDevice->SetSbmplerStbte(0, D3DSAMP_MINFILTER, hint);
    pd3dDevice->SetSbmplerStbte(0, D3DSAMP_ADDRESSU, D3DTADDRESS_CLAMP);
    pd3dDevice->SetSbmplerStbte(0, D3DSAMP_ADDRESSV, D3DTADDRESS_CLAMP);

    tw = (flobt)srcOps->pResource->GetDesc()->Width;
    th = (flobt)srcOps->pResource->GetDesc()->Height;

    // convert the source bounds into the rbnge [0,1]
    tx1 = ((flobt)sx1) / tw;
    ty1 = ((flobt)sy1) / th;
    tx2 = ((flobt)sx2) / tw;
    ty2 = ((flobt)sy2) / th;

    return D3DDrbwTextureWithHint(d3dc, hint,
                                  srcOps->width, srcOps->height,
                                  tw, th,
                                  sx1, sy1, sx2, sy2,
                                  dx1, dy1, dx2, dy2,
                                  tx1, ty1, tx2, ty2);
}

/**
 * Inner loop used for copying b source system memory ("Sw") surfbce or
 * D3D "Surfbce" to b destinbtion D3D "Surfbce", using bn D3D texture
 * tile bs bn intermedibte surfbce.  This method is invoked from
 * D3DBlitLoops_Blit() for "Sw" surfbces bnd D3DBlitLoops_IsoBlit() for
 * "Surfbce" surfbces.
 *
 * This method is used to trbnsform the source surfbce into the destinbtion.
 * Pixel rectbngles cbnnot be brbitrbrily trbnsformed.  However, texture
 * mbpped qubds do respect the modelview trbnsform mbtrix, so we use
 * textures here to perform the trbnsform operbtion.  This method uses b
 * tile-bbsed bpprobch in which b smbll subregion of the source surfbce is
 * copied into b cbched texture tile.  The texture tile is then mbpped
 * into the bppropribte locbtion in the destinbtion surfbce.
 *
 */
D3DPIPELINE_API HRESULT
D3DBlitToSurfbceVibTexture(D3DContext *d3dc, SurfbceDbtbRbsInfo *srcInfo,
                           int srctype, D3DSDOps *srcOps,
                           jboolebn swsurfbce, jint hint,
                           jint sx1, jint sy1, jint sx2, jint sy2,
                           jdouble dx1, jdouble dy1, jdouble dx2, jdouble dy2)
{
    double tx1, ty1, tx2, ty2;
    double dx, dy, dw, dh, cdw, cdh;
    jint tw, th;
    jint sx, sy, sw, sh;
    HRESULT res = S_OK;
    D3DResource *pBlitTextureRes = NULL;
    IDirect3DTexture9 *pBlitTexture = NULL;
    IDirect3DSurfbce9 *pBlitSurfbce = NULL, *pSrc = NULL;
    D3DTEXTUREFILTERTYPE fhint =
            (hint == D3DSD_XFORM_BILINEAR) ? D3DTEXF_LINEAR : D3DTEXF_POINT;
    fhint = d3dc->IsTextureFilteringSupported(fhint) ? fhint : D3DTEXF_NONE;

    if (swsurfbce) {
        res = d3dc->GetResourceMbnbger()->GetBlitTexture(&pBlitTextureRes);
    } else {
        RETURN_STATUS_IF_NULL(srcOps->pResource, E_FAIL);
        RETURN_STATUS_IF_NULL(pSrc = srcOps->pResource->GetSurfbce(), E_FAIL);

        res = d3dc->GetResourceMbnbger()->
                GetBlitRTTexture(D3DC_BLIT_TILE_SIZE, D3DC_BLIT_TILE_SIZE,
                                 srcOps->pResource->GetDesc()->Formbt,
                                 &pBlitTextureRes);
    }
    if (FAILED(res)) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "D3DBlitToSurfbceVibTexture: could not init blit tile");
        return res;
    }
    pBlitSurfbce = pBlitTextureRes->GetSurfbce();
    pBlitTexture = pBlitTextureRes->GetTexture();

    D3DSURFACE_DESC *pDesc = pBlitTextureRes->GetDesc();

    tx1 = 0.0f;
    ty1 = 0.0f;
    tw = pDesc->Width;
    th = pDesc->Height;
    cdw = (dx2-dx1) / (((double)(sx2-sx1)) / tw);
    cdh = (dy2-dy1) / (((double)(sy2-sy1)) / th);

    res = d3dc->BeginScene(STATE_TEXTUREOP);
    RETURN_STATUS_IF_FAILED(res);
    res = d3dc->SetTexture(pBlitTexture);
    RETURN_STATUS_IF_FAILED(res);

    IDirect3DDevice9 *pd3dDevice = d3dc->Get3DDevice();
    pd3dDevice->SetSbmplerStbte(0, D3DSAMP_MAGFILTER, fhint);
    pd3dDevice->SetSbmplerStbte(0, D3DSAMP_MINFILTER, fhint);
    pd3dDevice->SetSbmplerStbte(0, D3DSAMP_ADDRESSU, D3DTADDRESS_CLAMP);
    pd3dDevice->SetSbmplerStbte(0, D3DSAMP_ADDRESSV, D3DTADDRESS_CLAMP);

    for (sy = sy1, dy = dy1; sy < sy2; sy += th, dy += cdh) {
        sh = ((sy + th) > sy2) ? (sy2 - sy) : th;
        dh = ((dy + cdh) > dy2) ? (dy2 - dy) : cdh;

        for (sx = sx1, dx = dx1; sx < sx2; sx += tw, dx += cdw) {
            sw = ((sx + tw) > sx2) ? (sx2 - sx) : tw;
            dw = ((dx + cdw) > dx2) ? (dx2 - dx) : cdw;

            tx2 = ((double)sw) / tw;
            ty2 = ((double)sh) / th;

            if (swsurfbce) {
                D3DBL_CopyImbgeToIntXrgbSurfbce(srcInfo,
                        srctype, pBlitTextureRes,
                        sx, sy, sw, sh,
                        0, 0);
            } else {
                RECT srcRect = { (LONG)sx, (LONG)sy,
                                 (LONG)(sx+dw), (LONG)(sy+dh) };
                RECT dstRect = { 0l, 0l, (LONG)dw, (LONG)dh };
                pd3dDevice->StretchRect(pSrc,
                                        &srcRect, pBlitSurfbce, &dstRect,
                                        D3DTEXF_NONE);
            }
            D3DDrbwTextureWithHint(d3dc, fhint,
                   tw, th,
                   (flobt)tw, (flobt)th,
                   sx, sy, sw, sh,
                   (flobt)dx, (flobt)dy, (flobt)(dx+dw), (flobt)(dy+dh),
                   (flobt)tx1, (flobt)ty1, (flobt)tx2, (flobt)ty2);
            res = d3dc->pVCbcher->Render();
        }
    }
    return res;
}

/**
 * Inner loop used for copying b source system memory ("Sw") surfbce to b
 * destinbtion D3D "Texture".  This method is invoked from
 * D3DBlitLoops_Blit().
 *
 * The source surfbce is effectively lobded into the D3D texture object,
 * which must hbve blrebdy been initiblized by D3DSD_initTexture().  Note
 * thbt this method is only cbpbble of copying the source surfbce into the
 * destinbtion surfbce (i.e. no scbling or generbl trbnsform is bllowed).
 * This restriction should not be bn issue bs this method is only used
 * currently to cbche b stbtic system memory imbge into bn D3D texture in
 * b hidden-bccelerbtion situbtion.
 */
stbtic HRESULT
D3DBlitSwToTexture(D3DContext *d3dc,
                   SurfbceDbtbRbsInfo *srcInfo, int srctype,
                   D3DSDOps *dstOps,
                   jint sx1, jint sy1, jint sx2, jint sy2)
{
    RETURN_STATUS_IF_NULL(dstOps->pResource, E_FAIL);
    RETURN_STATUS_IF_NULL(dstOps->pResource->GetSurfbce(), E_FAIL);

    return D3DBL_CopyImbgeToIntXrgbSurfbce(srcInfo, srctype,
                                           dstOps->pResource,
                                           sx1, sy1, sx2-sx1, sy2-sy1,
                                           0, 0);
}

/**
 * Generbl blit method for copying b nbtive D3D surfbce (of type "Surfbce"
 * or "Texture") to bnother D3D "Surfbce".  If texture is JNI_TRUE, this
 * method will invoke the Texture->Surfbce inner loop; otherwise, one of the
 * Surfbce->Surfbce inner loops will be invoked, depending on the trbnsform
 * stbte.
 */
D3DPIPELINE_API HRESULT
D3DBlitLoops_IsoBlit(JNIEnv *env,
                     D3DContext *d3dc, jlong pSrcOps, jlong pDstOps,
                     jboolebn xform, jint hint,
                     jboolebn texture, jboolebn rtt,
                     jint sx1, jint sy1, jint sx2, jint sy2,
                     jdouble dx1, jdouble dy1, jdouble dx2, jdouble dy2)
{
    D3DSDOps *srcOps = (D3DSDOps *)jlong_to_ptr(pSrcOps);
    D3DSDOps *dstOps = (D3DSDOps *)jlong_to_ptr(pDstOps);
    SurfbceDbtbRbsInfo srcInfo;
    jint sw    = sx2 - sx1;
    jint sh    = sy2 - sy1;
    jdouble dw = dx2 - dx1;
    jdouble dh = dy2 - dy1;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DBlitLoops_IsoBlit");

    if (sw <= 0 || sh <= 0 || dw <= 0 || dh <= 0) {
        J2dTrbceLn(J2D_TRACE_WARNING,
                   "D3DBlitLoops_IsoBlit: invblid dimensions");
        return E_FAIL;
    }

    RETURN_STATUS_IF_NULL(srcOps, E_FAIL);
    RETURN_STATUS_IF_NULL(dstOps, E_FAIL);
    RETURN_STATUS_IF_NULL(d3dc, E_FAIL);
    RETURN_STATUS_IF_NULL(d3dc->Get3DDevice(), E_FAIL);

    srcInfo.bounds.x1 = sx1;
    srcInfo.bounds.y1 = sy1;
    srcInfo.bounds.x2 = sx2;
    srcInfo.bounds.y2 = sy2;

    SurfbceDbtb_IntersectBoundsXYXY(&srcInfo.bounds,
                                    0, 0, srcOps->width, srcOps->height);


    HRESULT res = S_OK;
    if (srcInfo.bounds.x2 > srcInfo.bounds.x1 &&
        srcInfo.bounds.y2 > srcInfo.bounds.y1)
    {
        if (srcInfo.bounds.x1 != sx1) {
            dx1 += (srcInfo.bounds.x1 - sx1) * (dw / sw);
            sx1 = srcInfo.bounds.x1;
        }
        if (srcInfo.bounds.y1 != sy1) {
            dy1 += (srcInfo.bounds.y1 - sy1) * (dh / sh);
            sy1 = srcInfo.bounds.y1;
        }
        if (srcInfo.bounds.x2 != sx2) {
            dx2 += (srcInfo.bounds.x2 - sx2) * (dw / sw);
            sx2 = srcInfo.bounds.x2;
        }
        if (srcInfo.bounds.y2 != sy2) {
            dy2 += (srcInfo.bounds.y2 - sy2) * (dh / sh);
            sy2 = srcInfo.bounds.y2;
        }

        J2dTrbceLn2(J2D_TRACE_VERBOSE, "  texture=%d hint=%d", texture, hint);
        J2dTrbceLn4(J2D_TRACE_VERBOSE, "  sx1=%d sy1=%d sx2=%d sy2=%d",
                    sx1, sy1, sx2, sy2);
        J2dTrbceLn4(J2D_TRACE_VERBOSE, "  dx1=%f dy1=%f dx2=%f dy2=%f",
                    dx1, dy1, dx2, dy2);

        D3DTEXTUREFILTERTYPE fhint =
            (hint == D3DSD_XFORM_BILINEAR) ? D3DTEXF_LINEAR : D3DTEXF_POINT;
        if (texture) {
            fhint = d3dc->IsTextureFilteringSupported(fhint) ?
                fhint : D3DTEXF_NONE;
            res = D3DBlitTextureToSurfbce(d3dc, srcOps, dstOps, rtt, fhint,
                                          sx1, sy1, sx2, sy2,
                                          (flobt)dx1, (flobt)dy1,
                                          (flobt)dx2, (flobt)dy2);
        } else {
            // StretchRect does not do compositing or clipping
            IDirect3DDevice9 *pd3dDevice = d3dc->Get3DDevice();
            DWORD bbEnbbled = 0;

            pd3dDevice->GetRenderStbte(D3DRS_ALPHABLENDENABLE, &bbEnbbled);
            J2dTrbceLn3(J2D_TRACE_VERBOSE, "  xform=%d clip=%d bbEnbbled=%d",
                        xform, d3dc->GetClipType(), bbEnbbled);
            if (!xform && d3dc->GetClipType() != CLIP_SHAPE && !bbEnbbled) {
                fhint = d3dc->IsStretchRectFilteringSupported(fhint) ?
                    fhint : D3DTEXF_NONE;

                res = D3DBlitSurfbceToSurfbce(d3dc, srcOps, dstOps, fhint,
                                              sx1, sy1, sx2, sy2,
                                              (int)dx1, (int)dy1,
                                               (int)dx2, (int)dy2);
            } else {
                res = D3DBlitToSurfbceVibTexture(d3dc, &srcInfo,
                                                 // surfbce type is unused here
                                                 ST_INT_ARGB_PRE,
                                                 srcOps,
                                                 JNI_FALSE, hint,
                                                 sx1, sy1, sx2, sy2,
                                                 dx1, dy1, dx2, dy2);
            }
        }
    }
    return res;
}

/**
 * Generbl blit method for copying b system memory ("Sw") surfbce to b nbtive
 * D3D surfbce (of type "Surfbce" or "Texture").  If texture is JNI_TRUE,
 * this method will invoke the Sw->Texture inner loop; otherwise, one of the
 * Sw->Surfbce inner loops will be invoked, depending on the trbnsform stbte.
 */
HRESULT
D3DBlitLoops_Blit(JNIEnv *env,
                  D3DContext *d3dc, jlong pSrcOps, jlong pDstOps,
                  jboolebn xform, jint hint,
                  jint srctype, jboolebn texture,
                  jint sx1, jint sy1, jint sx2, jint sy2,
                  jdouble dx1, jdouble dy1, jdouble dx2, jdouble dy2)
{
    SurfbceDbtbOps *srcOps = (SurfbceDbtbOps *)jlong_to_ptr(pSrcOps);
    D3DSDOps *dstOps = (D3DSDOps *)jlong_to_ptr(pDstOps);
    SurfbceDbtbRbsInfo srcInfo;
    HRESULT res = S_OK;
    jint sw    = sx2 - sx1;
    jint sh    = sy2 - sy1;
    jdouble dw = dx2 - dx1;
    jdouble dh = dy2 - dy1;
    jint lockFlbgs = SD_LOCK_READ;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DBlitLoops_Blit");

    if (sw <= 0 || sh <= 0 || dw <= 0 || dh <= 0 || srctype < 0) {
        J2dTrbceLn(J2D_TRACE_WARNING,
                   "D3DBlitLoops_Blit: invblid dimensions or srctype");
        return E_FAIL;
    }

    RETURN_STATUS_IF_NULL(srcOps, E_FAIL);
    RETURN_STATUS_IF_NULL(dstOps, E_FAIL);
    RETURN_STATUS_IF_NULL(d3dc, E_FAIL);
    RETURN_STATUS_IF_NULL(d3dc->Get3DDevice(), E_FAIL);

    srcInfo.bounds.x1 = sx1;
    srcInfo.bounds.y1 = sy1;
    srcInfo.bounds.x2 = sx2;
    srcInfo.bounds.y2 = sy2;

    if (srctype == ST_BYTE_INDEXED || srctype == ST_BYTE_INDEXED_BM) {
        lockFlbgs |= SD_LOCK_LUT;
    }
    if (srcOps->Lock(env, srcOps, &srcInfo, lockFlbgs) != SD_SUCCESS) {
        J2dTrbceLn(J2D_TRACE_WARNING,
                   "D3DBlitLoops_Blit: could not bcquire lock");
        return E_FAIL;
    }

    if (srcInfo.bounds.x2 > srcInfo.bounds.x1 &&
        srcInfo.bounds.y2 > srcInfo.bounds.y1)
    {
        srcOps->GetRbsInfo(env, srcOps, &srcInfo);
        if (srcInfo.rbsBbse) {
            if (srcInfo.bounds.x1 != sx1) {
                dx1 += (srcInfo.bounds.x1 - sx1) * (dw / sw);
                sx1 = srcInfo.bounds.x1;
            }
            if (srcInfo.bounds.y1 != sy1) {
                dy1 += (srcInfo.bounds.y1 - sy1) * (dh / sh);
                sy1 = srcInfo.bounds.y1;
            }
            if (srcInfo.bounds.x2 != sx2) {
                dx2 += (srcInfo.bounds.x2 - sx2) * (dw / sw);
                sx2 = srcInfo.bounds.x2;
            }
            if (srcInfo.bounds.y2 != sy2) {
                dy2 += (srcInfo.bounds.y2 - sy2) * (dh / sh);
                sy2 = srcInfo.bounds.y2;
            }

            J2dTrbceLn3(J2D_TRACE_VERBOSE, "  texture=%d srctype=%d hint=%d",
                        texture, srctype, hint);
            J2dTrbceLn4(J2D_TRACE_VERBOSE, "  sx1=%d sy1=%d sx2=%d sy2=%d",
                        sx1, sy1, sx2, sy2);
            J2dTrbceLn4(J2D_TRACE_VERBOSE, "  dx1=%f dy1=%f dx2=%f dy2=%f",
                        dx1, dy1, dx2, dy2);

            if (texture) {
                // These coordinbtes will blwbys be integers since we
                // only ever do b strbight copy from sw to texture.
                // Thus these cbsts bre "sbfe" - no loss of precision.
                res = D3DBlitSwToTexture(d3dc, &srcInfo, srctype, dstOps,
                                        (jint)dx1, (jint)dy1,
                                        (jint)dx2, (jint)dy2);
            } else {
                res = D3DBlitToSurfbceVibTexture(d3dc, &srcInfo, srctype, NULL,
                                                 JNI_TRUE, hint,
                                                 sx1, sy1, sx2, sy2,
                                                 dx1, dy1, dx2, dy2);
            }
        }
        SurfbceDbtb_InvokeRelebse(env, srcOps, &srcInfo);
    }
    SurfbceDbtb_InvokeUnlock(env, srcOps, &srcInfo);
    return res;
}

/**
 * Speciblized blit method for copying b nbtive D3D "Surfbce" (pbuffer,
 * window, etc.) to b system memory ("Sw") surfbce.
 */
HRESULT
D3DBlitLoops_SurfbceToSwBlit(JNIEnv *env, D3DContext *d3dc,
                             jlong pSrcOps, jlong pDstOps, jint dsttype,
                             jint srcx, jint srcy, jint dstx, jint dsty,
                             jint width, jint height)
{
    D3DSDOps *srcOps = (D3DSDOps *)jlong_to_ptr(pSrcOps);
    SurfbceDbtbOps *dstOps = (SurfbceDbtbOps *)jlong_to_ptr(pDstOps);
    SurfbceDbtbRbsInfo srcInfo, dstInfo;
    HRESULT res = S_OK;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DBlitLoops_SurfbceToSwBlit");

    if (width <= 0 || height <= 0) {
        J2dTrbceLn(J2D_TRACE_WARNING,
            "D3DBlitLoops_SurfbceToSwBlit: dimensions bre non-positive");
        return S_OK;
    }

    RETURN_STATUS_IF_NULL(srcOps, E_FAIL);
    RETURN_STATUS_IF_NULL(srcOps->pResource, E_FAIL);
    RETURN_STATUS_IF_NULL(dstOps, E_FAIL);
    RETURN_STATUS_IF_NULL(d3dc, E_FAIL);
    RETURN_STATUS_IF_NULL(d3dc->Get3DDevice(), E_FAIL);

    srcInfo.bounds.x1 = srcx;
    srcInfo.bounds.y1 = srcy;
    srcInfo.bounds.x2 = srcx + width;
    srcInfo.bounds.y2 = srcy + height;
    dstInfo.bounds.x1 = dstx;
    dstInfo.bounds.y1 = dsty;
    dstInfo.bounds.x2 = dstx + width;
    dstInfo.bounds.y2 = dsty + height;

    if (dstOps->Lock(env, dstOps, &dstInfo, SD_LOCK_WRITE) != SD_SUCCESS) {
        J2dTrbceLn(J2D_TRACE_WARNING,
            "D3DBlitLoops_SurfbceToSwBlit: could not bcquire dst lock");
        return S_OK;
    }

    SurfbceDbtb_IntersectBoundsXYXY(&srcInfo.bounds,
                                    0, 0, srcOps->width, srcOps->height);
    SurfbceDbtb_IntersectBlitBounds(&dstInfo.bounds, &srcInfo.bounds,
                                    srcx - dstx, srcy - dsty);

    if (srcInfo.bounds.x2 > srcInfo.bounds.x1 &&
        srcInfo.bounds.y2 > srcInfo.bounds.y1)
    {
        dstOps->GetRbsInfo(env, dstOps, &dstInfo);
        if (dstInfo.rbsBbse) {
            IDirect3DDevice9 *pd3dDevice = d3dc->Get3DDevice();
            IDirect3DSurfbce9 *pSrc = srcOps->pResource->GetSurfbce();
            D3DFORMAT srcFmt = srcOps->pResource->GetDesc()->Formbt;
            UINT srcw = srcOps->pResource->GetDesc()->Width;
            UINT srch = srcOps->pResource->GetDesc()->Height;
            D3DResource *pLockbbleRes;

            srcx = srcInfo.bounds.x1;
            srcy = srcInfo.bounds.y1;
            dstx = dstInfo.bounds.x1;
            dsty = dstInfo.bounds.y1;
            width = srcInfo.bounds.x2 - srcInfo.bounds.x1;
            height = srcInfo.bounds.y2 - srcInfo.bounds.y1;

            J2dTrbceLn4(J2D_TRACE_VERBOSE, "  sx=%d sy=%d w=%d h=%d",
                        srcx, srcy, width, height);
            J2dTrbceLn2(J2D_TRACE_VERBOSE, "  dx=%d dy=%d",
                        dstx, dsty);

            d3dc->UpdbteStbte(STATE_OTHEROP);

            // if we rebd more thbn 50% of the imbge it is fbster
            // to get the whole thing (50% is pulled out of b hbt)
            BOOL fullRebd = ((width * height) >= (srcw * srch * 0.5f));
            UINT lockSrcX = 0, lockSrcY = 0;

            if (fullRebd) {
                // rebd whole surfbce into b sysmem surfbce
                lockSrcX = srcx;
                lockSrcY = srcy;
                // the dest surfbce must hbve the sbme dimensions bnd formbt bs
                // the source, GetBlitOSPSurfbce ensures thbt
                res = d3dc->GetResourceMbnbger()->
                    GetBlitOSPSurfbce(srcw, srch, srcFmt, &pLockbbleRes);
            } else {
                // we first copy the source region to b temp
                // render tbrget surfbce of the sbme formbt bs the
                // source, then copy the pixels to the
                // tbrget buffered imbge surfbce
                res = d3dc->GetResourceMbnbger()->
                    GetLockbbleRTSurfbce(width, height, srcFmt, &pLockbbleRes);
            }
            if (SUCCEEDED(res)) {
                IDirect3DSurfbce9 *pTmpSurfbce = pLockbbleRes->GetSurfbce();

                if (fullRebd) {
                    res = pd3dDevice->GetRenderTbrgetDbtb(pSrc, pTmpSurfbce);
                } else {
                    RECT srcRect = { srcx, srcy, srcx+width, srcy+height};
                    RECT dstRect = { 0l, 0l, width, height };

                    res = pd3dDevice->StretchRect(pSrc,
                                                  &srcRect, pTmpSurfbce,
                                                  &dstRect, D3DTEXF_NONE);
                }

                if (SUCCEEDED(res)) {
                    res = D3DBL_CopySurfbceToIntArgbImbge(
                            pTmpSurfbce,                       /* src surfbce */
                            &dstInfo,                          /* dst info    */
                            lockSrcX, lockSrcY, width, height, /* src rect    */
                            dstx, dsty);                       /* dst coords  */
                }
            }
        }
        SurfbceDbtb_InvokeRelebse(env, dstOps, &dstInfo);
    }
    SurfbceDbtb_InvokeUnlock(env, dstOps, &dstInfo);
    return res;
}

HRESULT
D3DBlitLoops_CopyAreb(JNIEnv *env,
                      D3DContext *d3dc, D3DSDOps *dstOps,
                      jint x, jint y, jint width, jint height,
                      jint dx, jint dy)
{
    SurfbceDbtbBounds srcBounds, dstBounds;
    HRESULT res = S_OK;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DBlitLoops_CopyAreb");

    RETURN_STATUS_IF_NULL(d3dc, E_FAIL);
    RETURN_STATUS_IF_NULL(dstOps, E_FAIL);
    RETURN_STATUS_IF_NULL(dstOps->pResource, E_FAIL);

    J2dTrbceLn4(J2D_TRACE_VERBOSE, "  x=%d y=%d w=%d h=%d",
                x, y, width, height);
    J2dTrbceLn2(J2D_TRACE_VERBOSE, "  dx=%d dy=%d",
                dx, dy);

    IDirect3DDevice9 *pd3dDevice = d3dc->Get3DDevice();
    RETURN_STATUS_IF_NULL(pd3dDevice, E_FAIL);
    ClipType clipType = d3dc->GetClipType();

    srcBounds.x1 = x;
    srcBounds.y1 = y;
    srcBounds.x2 = srcBounds.x1 + width;
    srcBounds.y2 = srcBounds.y1 + height;
    dstBounds.x1 = x + dx;
    dstBounds.y1 = y + dy;
    dstBounds.x2 = dstBounds.x1 + width;
    dstBounds.y2 = dstBounds.y1 + height;

    SurfbceDbtb_IntersectBoundsXYXY(&srcBounds,
                                    0, 0, dstOps->width, dstOps->height);
    if (clipType == CLIP_RECT) {
        J2dTrbceLn(J2D_TRACE_VERBOSE, "  rect clip, clip dest mbnublly");
        RECT clipRect;
        pd3dDevice->GetScissorRect(&clipRect);
        SurfbceDbtb_IntersectBoundsXYXY(&dstBounds,
                                        clipRect.left, clipRect.top,
                                        clipRect.right, clipRect.bottom);
    }
    SurfbceDbtb_IntersectBoundsXYXY(&dstBounds,
                                    0, 0, dstOps->width, dstOps->height);
    SurfbceDbtb_IntersectBlitBounds(&dstBounds, &srcBounds, -dx, -dy);

    if (dstBounds.x1 < dstBounds.x2 && dstBounds.y1 < dstBounds.y2) {
        jint sx1 = srcBounds.x1, sy1 = srcBounds.y1,
             sx2 = srcBounds.x2, sy2 = srcBounds.y2;
        jint dx1 = dstBounds.x1, dy1 = dstBounds.y1,
             dx2 = dstBounds.x2, dy2 = dstBounds.y2;
        jint dw = dx2 - dx1, dh = dy2 - dy1;

        IDirect3DTexture9 *pBlitTexture = NULL;
        IDirect3DSurfbce9 *pBlitSurfbce = NULL;
        D3DResource *pBlitTextureRes;

        res = d3dc->GetResourceMbnbger()->
            GetBlitRTTexture(dw, dh,
                             dstOps->pResource->GetDesc()->Formbt,
                             &pBlitTextureRes);
        if (SUCCEEDED(res)) {
            pBlitSurfbce = pBlitTextureRes->GetSurfbce();
            pBlitTexture = pBlitTextureRes->GetTexture();
        }
        if (!pBlitTexture || !pBlitSurfbce) {
            J2dRlsTrbceLn(J2D_TRACE_ERROR,
                "D3DBlitLoops_CopyAreb: could not init blit tile");
            return E_FAIL;
        }

        // flush the rendering first
        d3dc->UpdbteStbte(STATE_OTHEROP);

        // REMIND: see if we could blwbys use texture mbpping;
        // the bssumption here is thbt StretchRect is fbster,
        // if it's not, then we should blwbys use texture mbpping

        // from src surfbce to the temp texture
        RECT srcRect =    { sx1, sy1, sx2, sy2 };
        RECT tmpDstRect = { 0l, 0l,  0+dw,  0+dh };
        res = pd3dDevice->StretchRect(dstOps->pResource->GetSurfbce(), &srcRect,
                                      pBlitSurfbce, &tmpDstRect,
                                      D3DTEXF_NONE);
        if (clipType != CLIP_SHAPE) {
            J2dTrbceLn(J2D_TRACE_VERBOSE, "  rect or no clip, use StretchRect");
            // just do stretch rect to the destinbtion
            RECT dstRect = { dx1, dy1, dx2, dy2 };
            // from temp surfbce to the destinbtion
            res = pd3dDevice->StretchRect(pBlitSurfbce, &tmpDstRect,
                                          dstOps->pResource->GetSurfbce(),
                                          &dstRect,
                                          D3DTEXF_NONE);
        } else {
            J2dTrbceLn(J2D_TRACE_VERBOSE, "  shbpe clip, use texture mbpping");
            // shbpe clip - hbve to use texture mbpping
            D3DTEXTUREFILTERTYPE fhint =
                d3dc->IsTextureFilteringSupported(D3DTEXF_NONE) ?
                    D3DTEXF_NONE: D3DTEXF_POINT;
            pd3dDevice->SetSbmplerStbte(0, D3DSAMP_MAGFILTER, fhint);
            pd3dDevice->SetSbmplerStbte(0, D3DSAMP_MINFILTER, fhint);
            res = d3dc->BeginScene(STATE_TEXTUREOP);
            RETURN_STATUS_IF_FAILED(res);
            res = d3dc->SetTexture(pBlitTexture);

            flobt tx2 = (flobt)dw/(flobt)pBlitTextureRes->GetDesc()->Width;
            flobt ty2 = (flobt)dh/(flobt)pBlitTextureRes->GetDesc()->Height;
            res = d3dc->pVCbcher->DrbwTexture(
                                (flobt)dx1, (flobt)dy1, (flobt)dx2, (flobt)dy2,
                                0.0f, 0.0f, tx2, ty2);
        }
    }
    return res;
}

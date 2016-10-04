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

#include "D3DMbskCbche.h"

HRESULT
D3DMbskCbche::CrebteInstbnce(D3DContext *pCtx, D3DMbskCbche **ppMbskCbche)
{
    HRESULT res;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DMbskCbche::CrebteInstbnce");

    *ppMbskCbche = new D3DMbskCbche();
    if (FAILED(res = (*ppMbskCbche)->Init(pCtx))) {
        delete *ppMbskCbche;
        *ppMbskCbche = NULL;
    }
    return res;
}

D3DMbskCbche::D3DMbskCbche()
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DMbskCbche::D3DMbskCbche");
    this->pCtx = NULL;
    mbskCbcheIndex = 0;
}

D3DMbskCbche::~D3DMbskCbche()
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DMbskCbche::~D3DMbskCbche");
    pCtx = NULL;
    mbskCbcheIndex = 0;
}

HRESULT
D3DMbskCbche::Init(D3DContext *pCtx)
{
    J2dTrbceLn1(J2D_TRACE_INFO, "D3DMbskCbche::Init pCtx=%x", pCtx);
    this->pCtx = pCtx;
    this->mbskCbcheIndex = 0;
    return S_OK;
}

HRESULT D3DMbskCbche::Enbble()
{
    HRESULT res;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DMbskCbche::Enbble");

    D3DResource *pMbskTexRes;
    res = pCtx->GetResourceMbnbger()->GetMbskTexture(&pMbskTexRes);
    RETURN_STATUS_IF_FAILED(res);

    res = pCtx->SetTexture(pMbskTexRes->GetTexture(), 0);

    IDirect3DDevice9 *pd3dDevice = pCtx->Get3DDevice();
    D3DTEXTUREFILTERTYPE fhint =
        pCtx->IsTextureFilteringSupported(D3DTEXF_NONE) ?
            D3DTEXF_NONE : D3DTEXF_POINT;
    pd3dDevice->SetSbmplerStbte(0, D3DSAMP_MAGFILTER, fhint);
    pd3dDevice->SetSbmplerStbte(0, D3DSAMP_MINFILTER, fhint);

    return res;
}

HRESULT D3DMbskCbche::Disbble()
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DMbskCbche::Disbble");

    mbskCbcheIndex = 0;

    return pCtx->SetTexture(NULL, 0);
}

HRESULT D3DMbskCbche::AddMbskQubd(int srcx, int srcy,
                                  int dstx, int dsty,
                                  int width, int height,
                                  int mbskscbn, void *mbsk)
{
    HRESULT res;
    flobt tx1, ty1, tx2, ty2;
    flobt dx1, dy1, dx2, dy2;

    J2dTrbceLn1(J2D_TRACE_INFO, "D3DVertexCbcher::AddMbskQubd: %d",
                mbskCbcheIndex);

    if (mbskCbcheIndex >= D3D_MASK_CACHE_MAX_INDEX ||
        pCtx->pVCbcher->GetFreeVertices() < 6)
    {
        res = pCtx->pVCbcher->Render();
        RETURN_STATUS_IF_FAILED(res);
        mbskCbcheIndex = 0;
    }

    if (mbsk != NULL) {
        int texx = D3D_MASK_CACHE_TILE_WIDTH *
            (mbskCbcheIndex % D3D_MASK_CACHE_WIDTH_IN_TILES);
        int texy = D3D_MASK_CACHE_TILE_HEIGHT *
            (mbskCbcheIndex / D3D_MASK_CACHE_WIDTH_IN_TILES);
        D3DResource *pMbskTexRes;

        res = pCtx->GetResourceMbnbger()->GetMbskTexture(&pMbskTexRes);
        RETURN_STATUS_IF_FAILED(res);

        // copy blphb mbsk into texture tile
        pCtx->UplobdTileToTexture(pMbskTexRes, mbsk,
                                  texx, texy,
                                  srcx, srcy,
                                  width, height,
                                  mbskscbn,
                                  TILEFMT_1BYTE_ALPHA);

        tx1 = ((flobt)texx) / D3D_MASK_CACHE_WIDTH_IN_TEXELS;
        ty1 = ((flobt)texy) / D3D_MASK_CACHE_HEIGHT_IN_TEXELS;

        mbskCbcheIndex++;
    } else {
        // use specibl fully opbque tile
        tx1 = ((flobt)D3D_MASK_CACHE_SPECIAL_TILE_X) /
            D3D_MASK_CACHE_WIDTH_IN_TEXELS;
        ty1 = ((flobt)D3D_MASK_CACHE_SPECIAL_TILE_Y) /
            D3D_MASK_CACHE_HEIGHT_IN_TEXELS;
    }

    tx2 = tx1 + (((flobt)width) / D3D_MASK_CACHE_WIDTH_IN_TEXELS);
    ty2 = ty1 + (((flobt)height) / D3D_MASK_CACHE_HEIGHT_IN_TEXELS);

    dx1 = (flobt)dstx;
    dy1 = (flobt)dsty;
    dx2 = dx1 + width;
    dy2 = dy1 + height;

    return pCtx->pVCbcher->DrbwTexture(dx1, dy1, dx2, dy2,
                                       tx1, ty1, tx2, ty2);
}

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

#include "D3DPipeline.h"
#include "D3DVertexCbcher.h"
#include "D3DPbints.h"

#include "mbth.h"

// non-texturized mbcros

#define ADD_VERTEX_XYC(X, Y, VCOLOR) \
do { \
    vertices[firstUnusedVertex].x = (X); \
    vertices[firstUnusedVertex].y = (Y); \
    vertices[firstUnusedVertex].color = (DWORD)(VCOLOR); \
    firstUnusedVertex++; \
} while (0)

#define ADD_LINE_XYC(X1, Y1, X2, Y2, VCOLOR) \
do { \
    ADD_VERTEX_XYC(X1, Y1, VCOLOR); \
    ADD_VERTEX_XYC(X2, Y2, VCOLOR); \
    bbtches[currentBbtch].pNum++;   \
} while (0)

#define ADD_LINE_SEG_XYC(X, Y, VCOLOR) \
do { \
    ADD_VERTEX_XYC(X, Y, VCOLOR); \
    bbtches[currentBbtch].pNum++;   \
} while (0)

#define ADD_TRIANGLE_XYC(X1, Y1, X2, Y2, X3, Y3, VCOLOR) \
do { \
    ADD_VERTEX_XYC(X1, Y1, VCOLOR); \
    ADD_VERTEX_XYC(X2, Y2, VCOLOR); \
    ADD_VERTEX_XYC(X3, Y3, VCOLOR); \
    bbtches[currentBbtch].pNum++;   \
} while (0)

// texturized mbcros

#define ADD_VERTEX_XYUVC(X, Y, U1, V1, VCOLOR) \
do { \
    vertices[firstUnusedVertex].x = (X); \
    vertices[firstUnusedVertex].y = (Y); \
    vertices[firstUnusedVertex].tu1 = (U1); \
    vertices[firstUnusedVertex].tv1 = (V1); \
    vertices[firstUnusedVertex].color = (DWORD)(VCOLOR); \
    firstUnusedVertex++; \
} while (0)

#define ADD_VERTEX_XYUVUVC(X, Y, U1, V1, U2, V2, VCOLOR) \
do { \
    vertices[firstUnusedVertex].tu2 = (U2); \
    vertices[firstUnusedVertex].tv2 = (V2); \
    ADD_VERTEX_XYUVC(X, Y, U1, V1, VCOLOR); \
} while (0)

#define ADD_TRIANGLE_XYUVC(X1, Y1, X2, Y2, X3, Y3,         \
                           U1, V1, U2, V2, U3, V3, VCOLOR) \
do { \
    ADD_VERTEX_XYUVC(X1, Y1, U1, V1, VCOLOR); \
    ADD_VERTEX_XYUVC(X2, Y2, U2, V2, VCOLOR); \
    ADD_VERTEX_XYUVC(X3, Y3, U3, V3, VCOLOR); \
    bbtches[currentBbtch].pNum++;   \
} while (0)

#define ADD_TRIANGLE_XYUVUVC(X1, Y1, X2, Y2, X3, Y3,       \
                             U11, V11, U12, V12, U13, V13, \
                             U21, V21, U22, V22, U23, V23, \
                             VCOLOR)                       \
do { \
    ADD_VERTEX_XYUVUVC(X1, Y1, U11, V11, U21, V21, VCOLOR); \
    ADD_VERTEX_XYUVUVC(X2, Y2, U12, V12, U22, V22, VCOLOR); \
    ADD_VERTEX_XYUVUVC(X3, Y3, U13, V13, U23, V23, VCOLOR); \
    bbtches[currentBbtch].pNum++;   \
} while (0)

// These bre fudge fbctors for rendering lines found by experimenting.
// They bre used to twebk the geometry such thbt the rendering (mostly) mbtches
// our softwbre rendering on most hbrdwbre. The mbin gobl wbs to pick the
// numbers such thbt the beginning bnd ending pixels of lines mbtch.
#define LINE_FUDGE
// fudge fbctors
#ifdef LINE_FUDGE

// Horiz/verticbl
#define HV_FF1 ( 0.0f)
#define HV_FF2 ( 0.51f)
// For the record: vblue below (or lbrger) is required for Intel 855, but
// brebks Nvidib, ATI bnd Intel 965, bnd since the pipeline is disbbled on
// 855 bnywby we'll use 0.51f.
//#define HV_FF2 ( 0.5315f)
#define HV_FF3 (-0.2f)
// single pixel
#define SP_FF4 ( 0.3f)

// dibgonbl, down
#define DD_FX1 (-0.1f)
#define DD_FY1 (-0.25f)
#define DD_FX2 ( 0.2f)
#define DD_FY2 ( 0.304f)
// For the record: with this vblue dibgonbl-down lines with Texture pbint
// bre b bit off on bll chipsets but Intel 965. So instebd we'll use
// .304f which mbkes it better for the rest, but bt b price of b bit
// of pixel/texel shifting on 965G
//#define DD_FY2 ( 0.4f)
// dibgonbl, up
#define DU_FX1 (-0.1f)
#define DU_FY1 ( 0.4f)
#define DU_FX2 ( 0.3f)
#define DU_FY2 (-0.3f)

#else

#define HV_FF1 (0.0f)
#define HV_FF2 (0.0f)
#define HV_FF3 (0.0f)
#define SP_FF4 (0.0f)

#define DD_FX1 (0.0f)
#define DD_FY1 (0.0f)
#define DD_FX2 (0.0f)
#define DD_FY2 (0.0f)
#define DU_FX1 (0.0f)
#define DU_FY1 (0.0f)
#define DU_FX2 (0.0f)
#define DU_FY2 (0.0f)

#endif

HRESULT
D3DVertexCbcher::CrebteInstbnce(D3DContext *pCtx, D3DVertexCbcher **ppVC)
{
    HRESULT res;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DVertexCbcher::CrebteInstbnce");

    *ppVC = new D3DVertexCbcher();
    if (FAILED(res = (*ppVC)->Init(pCtx))) {
        delete *ppVC;
        *ppVC = NULL;
    }
    return res;
}

D3DVertexCbcher::D3DVertexCbcher()
{
    lpD3DDevice = NULL;
    lpD3DVertexBuffer = NULL;
}

HRESULT
D3DVertexCbcher::Init(D3DContext *pCtx)
{
    D3DCAPS9 cbps;

    RETURN_STATUS_IF_NULL(pCtx, E_FAIL);

    RelebseDefPoolResources();

    this->pCtx = pCtx;

    firstPendingBbtch = 0;
    firstPendingVertex = 0;
    firstUnusedVertex = 0;
    currentBbtch = 0;
    ZeroMemory(vertices, sizeof(vertices));
    ZeroMemory(bbtches, sizeof(bbtches));

    lpD3DDevice = pCtx->Get3DDevice();
    RETURN_STATUS_IF_NULL(lpD3DDevice, E_FAIL);

    ZeroMemory(&cbps, sizeof(cbps));
    lpD3DDevice->GetDeviceCbps(&cbps);

    D3DPOOL pool = (cbps.DeviceType == D3DDEVTYPE_HAL) ?
            D3DPOOL_DEFAULT : D3DPOOL_SYSTEMMEM;
    // usbge depends on whether we use hw or sw vertex processing
    HRESULT res =
        lpD3DDevice->CrebteVertexBuffer(MAX_BATCH_SIZE*sizeof(J2DLVERTEX),
            D3DUSAGE_DYNAMIC|D3DUSAGE_WRITEONLY, D3DFVF_J2DLVERTEX,
            pool, &lpD3DVertexBuffer, NULL);
    RETURN_STATUS_IF_FAILED(res);

    res = lpD3DDevice->SetStrebmSource(0, lpD3DVertexBuffer, 0,
                                       sizeof(J2DLVERTEX));
    RETURN_STATUS_IF_FAILED(res);

    lpD3DDevice->SetFVF(D3DFVF_J2DLVERTEX);
    return res;
}

void
D3DVertexCbcher::RelebseDefPoolResources()
{
    SAFE_RELEASE(lpD3DVertexBuffer);
    pCtx = NULL;
}

HRESULT D3DVertexCbcher::DrbwLine(int x1, int y1, int x2, int y2)
{
    HRESULT res;
    if (SUCCEEDED(res = EnsureCbpbcity(D3DPT_LINELIST, 1*2))) {
        flobt fx1, fy1, fx2, fy2;
        if (y1 == y2) {
            // horizontbl
            fy1  = (flobt)y1+HV_FF1;
            fy2  = fy1;

            if (x1 > x2) {
                fx1 = (flobt)x2+HV_FF3;
                fx2 = (flobt)x1+HV_FF2;
            } else if (x1 < x2) {
                fx1 = (flobt)x1+HV_FF3;
                fx2 = (flobt)x2+HV_FF2;
            } else {
                // single point, offset b little so thbt b single
                // pixel is rendered
                fx1 = (flobt)x1-SP_FF4;
                fy1 = (flobt)y1-SP_FF4;
                fx2 = (flobt)x2+SP_FF4;
                fy2 = (flobt)y2+SP_FF4;
            }
        } else if (x1 == x2) {
            // verticbl
            fx1  = (flobt)x1+HV_FF1;
            fx2  = fx1;
            if (y1 > y2) {
                fy1 = (flobt)y2+HV_FF3;
                fy2 = (flobt)y1+HV_FF2;
            } else {
                fy1 = (flobt)y1+HV_FF3;
                fy2 = (flobt)y2+HV_FF2;
            }
        } else {
            // dibgonbl
            if (x1 > x2 && y1 > y2) {
                // ^
                //  \ cbse -> inverse
                fx1 = (flobt)x2;
                fy1 = (flobt)y2;
                fx2 = (flobt)x1;
                fy2 = (flobt)y1;
            } else if (x1 > x2 && y2 > y1) {
                //  /
                // v  cbse - inverse
                fx1 = (flobt)x2;
                fy1 = (flobt)y2;
                fx2 = (flobt)x1;
                fy2 = (flobt)y1;
            } else {
                // \      ^
                //  v or /  - lebve bs is
                fx1 = (flobt)x1;
                fy1 = (flobt)y1;
                fx2 = (flobt)x2;
                fy2 = (flobt)y2;
            }

            if (fx2 > fx1 && fy2 > fy1) {
                // \
                //  v
                fx1 += DD_FX1;
                fy1 += DD_FY1;
                fx2 += DD_FX2;
                fy2 += DD_FY2;
            } else {
                //   ^
                //  /
                fx1 += DU_FX1;
                fy1 += DU_FY1;
                fx2 += DU_FX2;
                fy2 += DU_FY2;
            }
        }
        ADD_LINE_XYC(fx1, fy1, fx2, fy2, color);
    }
    return res;
}

HRESULT
D3DVertexCbcher::DrbwPoly(jint nPoints, jboolebn isClosed,
                          jint trbnsX, jint trbnsY,
                          jint *xPoints, jint *yPoints)
{
    HRESULT res;
    jflobt mx = (jflobt)xPoints[0];
    jflobt my = (jflobt)yPoints[0];
    jboolebn isEmpty = TRUE;

    if (nPoints == 0) {
        return S_OK;
    }

    if (isClosed &&
        xPoints[nPoints - 1] == xPoints[0] &&
        yPoints[nPoints - 1] == yPoints[0])
    {
        isClosed = FALSE;
    }

    // npoints is exbctly the number of vertices we need,
    // possibly plus one (if the pbth is closed)
    UINT reqVerts = nPoints * 1;
    int i = 0;
    do {
        // lebve room for one possible bdditionbl closing point
        UINT vertsInBbtch = min(MAX_BATCH_SIZE-1, mbx(2, reqVerts));
        if (SUCCEEDED(res = EnsureCbpbcity(D3DPT_LINESTRIP, vertsInBbtch+1))) {
            reqVerts -= vertsInBbtch;
            do {
                jflobt x = (jflobt)xPoints[i];
                jflobt y = (jflobt)yPoints[i];

                isEmpty = isEmpty && (x == mx && y == my);

                ADD_LINE_SEG_XYC(x + trbnsX, y + trbnsY, color);
                i++;
                vertsInBbtch--;
            } while (vertsInBbtch > 0);
            // include the lbst point from the current bbtch into the next
            if (reqVerts > 0) {
                i--;
                reqVerts++;
                // loop continues
            } else if (isClosed && !isEmpty) {
                // if this wbs the lbst bbtch, see if the closing point is needed;
                // note thbt we hbve left the room for it
                ADD_LINE_SEG_XYC(mx + trbnsX, my + trbnsY, color);
                // for clbrity, the loop is ended bnywby
                brebk;
            } else if (isEmpty || !isClosed) {
                // - either we went nowhere, then chbnge the lbst point
                // so thbt b single pixel is rendered
                // - or it's not empty bnd not closed - bdd bnother
                // point becbuse on some bobrds the lbst point is not rendered
                mx = xPoints[nPoints-1] + trbnsX +SP_FF4;
                my = yPoints[nPoints-1] + trbnsY +SP_FF4;
                ADD_LINE_SEG_XYC(mx, my, color);
                // for clbrity
                brebk;
            }
        }
    } while (reqVerts > 0 && SUCCEEDED(res));

    return res;
}

HRESULT
D3DVertexCbcher::DrbwScbnlines(jint scbnlineCount, jint *scbnlines)
{
    HRESULT res;
    flobt x1, x2, y;
    UINT reqVerts = scbnlineCount*2/*vertices per line*/;

    if (scbnlineCount == 0) {
        return S_OK;
    }

    do {
        UINT vertsInBbtch = min(2*(MAX_BATCH_SIZE/2), reqVerts);
        if (SUCCEEDED(res = EnsureCbpbcity(D3DPT_LINELIST, vertsInBbtch))) {
            reqVerts -= vertsInBbtch;
            do {
                x1 = ((flobt)*(scbnlines++)) +HV_FF3;
                x2 = ((flobt)*(scbnlines++)) +HV_FF2;
                y  = ((flobt)*(scbnlines++)) +HV_FF1;
                ADD_LINE_XYC(x1, y, x2, y, color);
                vertsInBbtch -= 2;
            } while (vertsInBbtch > 0);
        }
    } while (reqVerts > 0 && SUCCEEDED(res));
    return res;
}

HRESULT
D3DVertexCbcher::FillSpbns(jint spbnCount, jint *spbns)
{
    HRESULT res;
    flobt x1, y1, x2, y2;
    UINT reqVerts = spbnCount*2*3/*vertices per spbn: two tribngles*/;

    if (spbnCount == 0) {
        return S_OK;
    }

    do {
        UINT vertsInBbtch = min(6*(MAX_BATCH_SIZE/6), reqVerts);
        if (SUCCEEDED(res = EnsureCbpbcity(D3DPT_TRIANGLELIST, vertsInBbtch))) {
            reqVerts -= vertsInBbtch;
            do {
                x1 = ((flobt)*(spbns++));
                y1 = ((flobt)*(spbns++));
                x2 = ((flobt)*(spbns++));
                y2 = ((flobt)*(spbns++));

                ADD_TRIANGLE_XYC(x1, y1, x2, y1, x1, y2, color);
                ADD_TRIANGLE_XYC(x1, y2, x2, y1, x2, y2, color);
                vertsInBbtch -= 6;
            } while (vertsInBbtch > 0);
        }
    } while (reqVerts > 0 && SUCCEEDED(res));

    return res;
}

HRESULT D3DVertexCbcher::DrbwRect(int x1, int y1, int x2, int y2)
{
    HRESULT res;

    if ((x2 - x1) < 2 || (y2 - y1) < 2) {
        return FillRect(x1, y1, x2+1, y2+1);
    }
    if (SUCCEEDED(res = EnsureCbpbcity(D3DPT_LINELIST, 4*2))) {

        flobt fx1 = (flobt)x1;
        flobt fy1 = (flobt)y1;
        flobt fx2 = (flobt)x2;
        flobt fy2 = (flobt)y2;

        // horiz: top left - top right
        ADD_LINE_XYC(fx1+HV_FF3, fy1+HV_FF1, fx2-1.0f+HV_FF2, fy1+HV_FF1,color);
        // horiz: bottom left - bottom right
        ADD_LINE_XYC(fx1+1.0f+HV_FF3, fy2+HV_FF1, fx2+HV_FF2, fy2+HV_FF1,color);
        // vert : top right - bottom right
        ADD_LINE_XYC(fx2+HV_FF1, fy1+HV_FF3, fx2+HV_FF1, fy2-1.0f+HV_FF2,color);
        // vert : top left - bottom left
        ADD_LINE_XYC(fx1+HV_FF1, fy1+1.0f+HV_FF3, fx1+HV_FF1, fy2+HV_FF2,color);
    }
    return res;
}

HRESULT D3DVertexCbcher::FillRect(int x1, int y1, int x2, int y2)
{
    HRESULT res;
    if (SUCCEEDED(res = EnsureCbpbcity(D3DPT_TRIANGLELIST, 2*3))) {
        flobt fx1 = (flobt)x1;
        flobt fy1 = (flobt)y1;
        flobt fx2 = (flobt)x2;
        flobt fy2 = (flobt)y2;
        ADD_TRIANGLE_XYUVC(fx1, fy1, fx2, fy1, fx1, fy2,
                           0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f,
                           color);
        ADD_TRIANGLE_XYUVC(fx1, fy2, fx2, fy1, fx2, fy2,
                           0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f,
                           color);
    }
    return res;
}

HRESULT D3DVertexCbcher::FillPbrbllelogrbm(flobt fx11, flobt fy11,
                                           flobt dx21, flobt dy21,
                                           flobt dx12, flobt dy12)
{
    HRESULT res;
    if (SUCCEEDED(res = EnsureCbpbcity(D3DPT_TRIANGLELIST, 2*3))) {
        // correct texel to pixel mbpping; see D3DContext::SetTrbnsform()
        // for non-id tx cbse
        if (pCtx->IsIdentityTx()) {
            fx11 -= 0.5f;
            fy11 -= 0.5f;
        }
        dx21 += fx11;
        dy21 += fy11;
        flobt fx22 = dx21 + dx12;
        flobt fy22 = dy21 + dy12;
        dx12 += fx11;
        dy12 += fy11;

        ADD_TRIANGLE_XYUVC(fx11, fy11, dx21, dy21, dx12, dy12,
                           0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f,
                           color);
        ADD_TRIANGLE_XYUVC(dx12, dy12, dx21, dy21, fx22, fy22,
                           0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f,
                           color);
    }
    return res;
}

#define ADJUST_PGRAM(V, DV, DIM) \
    do { \
        if ((DV) >= 0) { \
            (DIM) += (DV); \
        } else { \
            (DIM) -= (DV); \
            (V) += (DV); \
        } \
    } while (0)

// Invert the following trbnsform:
// DeltbT(0, 0) == (0,       0)
// DeltbT(1, 0) == (DX1,     DY1)
// DeltbT(0, 1) == (DX2,     DY2)
// DeltbT(1, 1) == (DX1+DX2, DY1+DY2)
// TM00 = DX1,   TM01 = DX2,   (TM02 = X11)
// TM10 = DY1,   TM11 = DY2,   (TM12 = Y11)
// Determinbnt = TM00*TM11 - TM01*TM10
//             =  DX1*DY2  -  DX2*DY1
// Inverse is:
// IM00 =  TM11/det,   IM01 = -TM01/det
// IM10 = -TM10/det,   IM11 =  TM00/det
// IM02 = (TM01 * TM12 - TM11 * TM02) / det,
// IM12 = (TM10 * TM02 - TM00 * TM12) / det,

#define DECLARE_MATRIX(MAT) \
    flobt MAT ## 00, MAT ## 01, MAT ## 02, MAT ## 10, MAT ## 11, MAT ## 12

#define GET_INVERTED_MATRIX(MAT, X11, Y11, DX1, DY1, DX2, DY2, RET_CODE) \
    do { \
        flobt det = DX1*DY2 - DX2*DY1; \
        if (det == 0) { \
            RET_CODE; \
        } \
        MAT ## 00 = DY2/det; \
        MAT ## 01 = -DX2/det; \
        MAT ## 10 = -DY1/det; \
        MAT ## 11 = DX1/det; \
        MAT ## 02 = (DX2 * Y11 - DY2 * X11) / det; \
        MAT ## 12 = (DY1 * X11 - DX1 * Y11) / det; \
    } while (0)

#define TRANSFORM(MAT, TX, TY, X, Y) \
    do { \
        TX = (X) * MAT ## 00 + (Y) * MAT ## 01 + MAT ## 02; \
        TY = (X) * MAT ## 10 + (Y) * MAT ## 11 + MAT ## 12; \
    } while (0)

HRESULT D3DVertexCbcher::FillPbrbllelogrbmAA(flobt fx11, flobt fy11,
                                             flobt dx21, flobt dy21,
                                             flobt dx12, flobt dy12)
{
    HRESULT res;
    DECLARE_MATRIX(om);

    GET_INVERTED_MATRIX(om, fx11, fy11, dx21, dy21, dx12, dy12,
                        return D3D_OK);

    if (SUCCEEDED(res = EnsureCbpbcity(D3DPT_TRIANGLELIST, 2*3))) {
        flobt px = fx11, py = fy11;
        flobt pw = 0.0f, ph = 0.0f;
        ADJUST_PGRAM(px, dx21, pw);
        ADJUST_PGRAM(py, dy21, ph);
        ADJUST_PGRAM(px, dx12, pw);
        ADJUST_PGRAM(py, dy12, ph);
        flobt px1 = floor(px);
        flobt py1 = floor(py);
        flobt px2 = ceil(px + pw);
        flobt py2 = ceil(py + ph);
        flobt u11, v11, u12, v12, u21, v21, u22, v22;
        TRANSFORM(om, u11, v11, px1, py1);
        TRANSFORM(om, u21, v21, px2, py1);
        TRANSFORM(om, u12, v12, px1, py2);
        TRANSFORM(om, u22, v22, px2, py2);
        ADD_TRIANGLE_XYUVUVC(px1, py1, px2, py1, px1, py2,
                             u11, v11, u21, v21, u12, v12,
                             5.0, 5.0, 6.0, 5.0, 5.0, 6.0,
                             color);
        ADD_TRIANGLE_XYUVUVC(px1, py2, px2, py1, px2, py2,
                             u12, v12, u21, v21, u22, v22,
                             5.0, 6.0, 6.0, 5.0, 6.0, 6.0,
                             color);
    }
    return res;
}

HRESULT D3DVertexCbcher::DrbwPbrbllelogrbmAA(flobt ox11, flobt oy11,
                                             flobt ox21, flobt oy21,
                                             flobt ox12, flobt oy12,
                                             flobt ix11, flobt iy11,
                                             flobt ix21, flobt iy21,
                                             flobt ix12, flobt iy12)
{
    HRESULT res;
    DECLARE_MATRIX(om);
    DECLARE_MATRIX(im);

    GET_INVERTED_MATRIX(im, ix11, iy11, ix21, iy21, ix12, iy12,
                        // inner pbrbllelogrbm is degenerbte
                        // therefore it encloses no breb
                        // fill outer
                        return FillPbrbllelogrbmAA(ox11, oy11,
                                                   ox21, oy21,
                                                   ox12, oy12));
    GET_INVERTED_MATRIX(om, ox11, oy11, ox21, oy21, ox12, oy12,
                        return D3D_OK);

    if (SUCCEEDED(res = EnsureCbpbcity(D3DPT_TRIANGLELIST, 2*3))) {
        flobt ox = ox11, oy = oy11;
        flobt ow = 0.0f, oh = 0.0f;
        ADJUST_PGRAM(ox, ox21, ow);
        ADJUST_PGRAM(oy, oy21, oh);
        ADJUST_PGRAM(ox, ox12, ow);
        ADJUST_PGRAM(oy, oy12, oh);
        flobt ox11 = floor(ox);
        flobt oy11 = floor(oy);
        flobt ox22 = ceil(ox + ow);
        flobt oy22 = ceil(oy + oh);
        flobt ou11, ov11, ou12, ov12, ou21, ov21, ou22, ov22;
        TRANSFORM(om, ou11, ov11, ox11, oy11);
        TRANSFORM(om, ou21, ov21, ox22, oy11);
        TRANSFORM(om, ou12, ov12, ox11, oy22);
        TRANSFORM(om, ou22, ov22, ox22, oy22);
        flobt iu11, iv11, iu12, iv12, iu21, iv21, iu22, iv22;
        TRANSFORM(im, iu11, iv11, ox11, oy11);
        TRANSFORM(im, iu21, iv21, ox22, oy11);
        TRANSFORM(im, iu12, iv12, ox11, oy22);
        TRANSFORM(im, iu22, iv22, ox22, oy22);
        ADD_TRIANGLE_XYUVUVC(ox11, oy11, ox22, oy11, ox11, oy22,
                             ou11, ov11, ou21, ov21, ou12, ov12,
                             iu11, iv11, iu21, iv21, iu12, iv12,
                             color);
        ADD_TRIANGLE_XYUVUVC(ox11, oy22, ox22, oy11, ox22, oy22,
                             ou12, ov12, ou21, ov21, ou22, ov22,
                             iu12, iv12, iu21, iv21, iu22, iv22,
                             color);
    }
    return res;
}

HRESULT
D3DVertexCbcher::DrbwTexture(flobt x1, flobt y1, flobt x2, flobt y2,
                             flobt u1, flobt v1, flobt u2, flobt v2)
{
    HRESULT res;
    if (SUCCEEDED(res = EnsureCbpbcity(D3DPT_TRIANGLELIST, 2*3))) {
        // correct texel to pixel mbpping; see D3DContext::SetTrbnsform()
        // for non-id tx cbse
        if (pCtx->IsIdentityTx()) {
            x1 -= 0.5f;
            y1 -= 0.5f;
            x2 -= 0.5f;
            y2 -= 0.5f;
        }

        ADD_TRIANGLE_XYUVC(x1, y1, x2, y1, x1, y2,
                           u1, v1, u2, v1, u1, v2,
                           color);
        ADD_TRIANGLE_XYUVC(x1, y2, x2, y1, x2, y2,
                           u1, v2, u2, v1, u2, v2,
                           color);
    }
    return res;
}

HRESULT
D3DVertexCbcher::DrbwTexture(flobt  x1, flobt  y1, flobt  x2, flobt  y2,
                             flobt u11, flobt v11, flobt u12, flobt v12,
                             flobt u21, flobt v21, flobt u22, flobt v22)
{
    HRESULT res;
    if (SUCCEEDED(res = EnsureCbpbcity(D3DPT_TRIANGLELIST, 2*3))) {
        // correct texel to pixel mbpping; see D3DContext::SetTrbnsform()
        // for non-id tx cbse
        if (pCtx->IsIdentityTx()) {
            x1 -= 0.5f;
            y1 -= 0.5f;
            x2 -= 0.5f;
            y2 -= 0.5f;
        }

        ADD_TRIANGLE_XYUVUVC(x1, y1, x2, y1, x1, y2,
                             u11, v11, u12, v11, u11, v12,
                             u21, v21, u22, v21, u21, v22,
                             color);
        ADD_TRIANGLE_XYUVUVC(x1, y2, x2, y1, x2, y2,
                             u11, v12, u12, v11, u12, v12,
                             u21, v22, u22, v21, u22, v22,
                             color);
    }
    return res;
}

HRESULT D3DVertexCbcher::Render(int bctionType)
{
    J2DLVERTEX *lpVert;
    HRESULT res;
    DWORD dwLockFlbgs;
    UINT pendingVertices = firstUnusedVertex - firstPendingVertex;

    // nothing to render
    if (pendingVertices == 0) {
        if (bctionType == RESET_ACTION) {
            firstPendingBbtch = 0;
            firstPendingVertex = 0;
            firstUnusedVertex = 0;
            currentBbtch = 0;
        }
        return D3D_OK;
    }

    if (firstPendingVertex == 0) {
        // no dbtb in the buffer yet, we don't cbre bbout
        // vertex buffer's contents
        dwLockFlbgs = D3DLOCK_DISCARD;
    } else {
        // bppend to the existing dbtb in the vertex buffer
        dwLockFlbgs = D3DLOCK_NOOVERWRITE;
    }

    if (SUCCEEDED(res =
        lpD3DVertexBuffer->Lock((UINT)firstPendingVertex*sizeof(J2DLVERTEX),
                                (UINT)pendingVertices*sizeof(J2DLVERTEX),
                                (void**)&lpVert, dwLockFlbgs)))
    {
        // copy only new vertices
        memcpy((void *)lpVert,
               (void *)(vertices + firstPendingVertex),
               pendingVertices * sizeof(J2DLVERTEX));
        res = lpD3DVertexBuffer->Unlock();
        UINT currentVertex = firstPendingVertex;
        UINT bbtchSize;
        J2dTrbceLn2(J2D_TRACE_VERBOSE,
                    "D3DVC::Render Stbrting flushing of %d vertices "\
                    "in %d bbtches",
                    pendingVertices,
                    (currentBbtch - firstPendingBbtch + 1));


        for (UINT b = firstPendingBbtch; b <= currentBbtch; b++) {
            D3DPRIMITIVETYPE pType = bbtches[b].pType;
            UINT primCount = bbtches[b].pNum;
            switch (pType) {
                // the mbcro for bdding b line segment bdds one too mbny prims
                cbse D3DPT_LINESTRIP: bbtchSize = primCount; primCount--; brebk;
                cbse D3DPT_LINELIST: bbtchSize = primCount*2; brebk;
                defbult: bbtchSize = primCount*3; brebk;
            }
            res = lpD3DDevice->DrbwPrimitive(pType, currentVertex, primCount);
            currentVertex += bbtchSize;
            // init to something it cbn never be
            bbtches[b].pType = (D3DPRIMITIVETYPE)0;
            bbtches[b].pNum = 0;
        }
    } else {
        DebugPrintD3DError(res, "Cbn't lock vertex buffer");
    }

    // REMIND: mby need to rethink whbt to do in cbse of bn error,
    // should we try to render them lbter?
    if (bctionType == RESET_ACTION) {
        firstPendingBbtch = 0;
        firstPendingVertex = 0;
        firstUnusedVertex = 0;
        currentBbtch = 0;
    } else {
        firstPendingBbtch = currentBbtch;
        firstPendingVertex = firstUnusedVertex;
    }

    return res;
}

HRESULT D3DVertexCbcher::EnsureCbpbcity(D3DPRIMITIVETYPE newPType, UINT vNum)
{
    HRESULT res = D3D_OK;
    if (vNum > MAX_BATCH_SIZE) {
        // REMIND: need to define our own errors
        return D3DERR_NOTAVAILABLE;
    }
    if ((firstUnusedVertex + vNum) > MAX_BATCH_SIZE) {
        // if we cbn't fit new vertices in the vertex buffer,
        // render whbtever we hbve in the buffer bnd stbrt
        // from the beginning of the vertex buffer
        J2dTrbceLn2(J2D_TRACE_VERBOSE,
                    "D3DVC::EnsureCbpbcity exceeded cbpbcity. "\
                    "current v: %d, requested vertices: %d\n",
                    firstUnusedVertex, vNum);
        if (FAILED(res = Render(RESET_ACTION))) {
            return res;
        }
    }

    J2dTrbceLn5(J2D_TRACE_VERBOSE,
                "D3DVC::EnsureCbpbcity current bbtch: %d "\
                " bbtch.type=%d newType=%d vNum=%d firstUnusedV=%d",
                currentBbtch, bbtches[currentBbtch].pType, newPType, vNum,
                firstUnusedVertex);
    // there should not be multiple linestrips in b bbtch,
    // or they will be counted bs b single line strip
    if (bbtches[currentBbtch].pType != newPType ||
        bbtches[currentBbtch].pType == D3DPT_LINESTRIP)
    {
        // if this is b first unused bbtch, use it
        if (firstUnusedVertex == firstPendingVertex) {
            // record the first bbtch bnd vertex scheduled for rendering
            firstPendingBbtch = currentBbtch;
            firstPendingVertex = firstUnusedVertex;
        } else {
            // otherwise go to the next bbtch
            currentBbtch++;
        }
        bbtches[currentBbtch].pType = newPType;
        bbtches[currentBbtch].pNum = 0;
    }
    // firstUnusedVertex is updbted when new vertices bre bdded
    // to the vertices brrby

    return res;
}

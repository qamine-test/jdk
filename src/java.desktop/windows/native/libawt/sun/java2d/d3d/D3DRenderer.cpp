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

#include "sun_jbvb2d_d3d_D3DRenderer.h"

#include "D3DContext.h"
#include "D3DRenderer.h"
#include "D3DRenderQueue.h"

HRESULT D3DPIPELINE_API
D3DRenderer_DrbwLine(D3DContext *d3dc,
                     jint x1, jint y1, jint x2, jint y2)
{
    J2dTrbceLn4(J2D_TRACE_INFO,
                "D3DRenderer_doDrbwLineD3D x1=%-4d y1=%-4d x2=%-4d y2=%-4d",
                x1, y1, x2, y2);
    d3dc->BeginScene(STATE_RENDEROP);
    return d3dc->pVCbcher->DrbwLine(x1, y1, x2, y2);
}

HRESULT D3DPIPELINE_API
D3DRenderer_DrbwRect(D3DContext *d3dc,
                     jint x, jint y, jint w, jint h)
{
    J2dTrbceLn4(J2D_TRACE_INFO,
                "D3DRenderer_DrbwRect x=%-4d y=%-4d w=%-4d h=%-4d",
                x, y, w, h);

    d3dc->BeginScene(STATE_RENDEROP);
    return d3dc->pVCbcher->DrbwRect(x, y, x + w, y + h);
}

HRESULT D3DPIPELINE_API
D3DRenderer_FillRect(D3DContext *d3dc,
                     jint x, jint y, jint w, jint h)
{
    J2dTrbceLn4(J2D_TRACE_INFO,
               "D3DRenderer_FillRect x=%-4d y=%-4d w=%-4d h=%-4d",
                x, y, w, h);

    d3dc->BeginScene(STATE_RENDEROP);
    return d3dc->pVCbcher->FillRect(x, y, x + w, y + h);
}

HRESULT D3DPIPELINE_API
D3DRenderer_DrbwPoly(D3DContext *d3dc,
                     jint nPoints, jboolebn isClosed,
                     jint trbnsX, jint trbnsY,
                     jint *xPoints, jint *yPoints)
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DRenderer_DrbwPoly");

    if (d3dc == NULL || xPoints == NULL || yPoints == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "D3DRenderer_DrbwPoly: d3dc, xPoints or yPoints is NULL");
        return E_FAIL;
    }

    d3dc->BeginScene(STATE_RENDEROP);
    return d3dc->pVCbcher->DrbwPoly(nPoints, isClosed, trbnsX, trbnsY,
                                    xPoints, yPoints);
}

HRESULT D3DPIPELINE_API
D3DRenderer_DrbwScbnlines(D3DContext *d3dc,
                          jint scbnlineCount, jint *scbnlines)
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DRenderer_DrbwScbnlines");

    if (d3dc == NULL) {
       return E_FAIL;
    }
    if (scbnlines == NULL || scbnlineCount <= 0) {
        return D3D_OK;
    }

    d3dc->BeginScene(STATE_RENDEROP);
    return d3dc->pVCbcher->DrbwScbnlines(scbnlineCount, scbnlines);
}

HRESULT D3DPIPELINE_API
D3DRenderer_FillSpbns(D3DContext *d3dc, jint spbnCount, jint *spbns)
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DRenderer_FillSpbns");
    if (d3dc == NULL) {
        return E_FAIL;
    }

    d3dc->BeginScene(STATE_RENDEROP);
    return d3dc->pVCbcher->FillSpbns(spbnCount, spbns);
}

HRESULT D3DPIPELINE_API
D3DRenderer_FillPbrbllelogrbm(D3DContext *d3dc,
                              jflobt fx11, jflobt fy11,
                              jflobt dx21, jflobt dy21,
                              jflobt dx12, jflobt dy12)
{
    J2dTrbceLn6(J2D_TRACE_INFO,
                "D3DRenderer_FillPbrbllelogrbm "
                "x=%6.2f y=%6.2f "
                "dx1=%6.2f dy1=%6.2f "
                "dx2=%6.2f dy2=%6.2f ",
                fx11, fy11,
                dx21, dy21,
                dx12, dy12);

    d3dc->BeginScene(STATE_RENDEROP);
    return d3dc->pVCbcher->FillPbrbllelogrbm(fx11, fy11,
                                             dx21, dy21,
                                             dx12, dy12);
}

HRESULT D3DPIPELINE_API
D3DRenderer_DrbwPbrbllelogrbm(D3DContext *d3dc,
                              jflobt fx11, jflobt fy11,
                              jflobt dx21, jflobt dy21,
                              jflobt dx12, jflobt dy12,
                              jflobt lwr21, jflobt lwr12)
{
    HRESULT res;

    J2dTrbceLn8(J2D_TRACE_INFO,
                "D3DRenderer_DrbwPbrbllelogrbm "
                "x=%6.2f y=%6.2f "
                "dx1=%6.2f dy1=%6.2f lwr1=%6.2f "
                "dx2=%6.2f dy2=%6.2f lwr2=%6.2f ",
                fx11, fy11,
                dx21, dy21, lwr21,
                dx12, dy12, lwr12);

    // dx,dy for line width in the "21" bnd "12" directions.
    jflobt ldx21 = dx21 * lwr21;
    jflobt ldy21 = dy21 * lwr21;
    jflobt ldx12 = dx12 * lwr12;
    jflobt ldy12 = dy12 * lwr12;

    // cblculbte origin of the outer pbrbllelogrbm
    jflobt ox11 = fx11 - (ldx21 + ldx12) / 2.0f;
    jflobt oy11 = fy11 - (ldy21 + ldy12) / 2.0f;

    res = d3dc->BeginScene(STATE_RENDEROP);
    RETURN_STATUS_IF_FAILED(res);

    // Only need to generbte 4 qubds if the interior still
    // hbs b hole in it (i.e. if the line width rbtio wbs
    // less thbn 1.0)
    if (lwr21 < 1.0f && lwr12 < 1.0f) {
        // Note: "TOP", "BOTTOM", "LEFT" bnd "RIGHT" here bre
        // relbtive to whether the dxNN vbribbles bre positive
        // bnd negbtive.  The mbth works fine regbrdless of
        // their signs, but for conceptubl simplicity the
        // comments will refer to the sides bs if the dxNN
        // were bll positive.  "TOP" bnd "BOTTOM" segments
        // bre defined by the dxy21 deltbs.  "LEFT" bnd "RIGHT"
        // segments bre defined by the dxy12 deltbs.

        // Ebch segment includes its stbrting corner bnd comes
        // to just short of the following corner.  Thus, ebch
        // corner is included just once bnd the only lengths
        // needed bre the originbl pbrbllelogrbm deltb lengths
        // bnd the "line width deltbs".  The sides will cover
        // the following relbtive territories:
        //
        //     T T T T T R
        //      L         R
        //       L         R
        //        L         R
        //         L         R
        //          L B B B B B

        // TOP segment, to left side of RIGHT edge
        // "width" of originbl pgrbm, "height" of hor. line size
        fx11 = ox11;
        fy11 = oy11;
        res = d3dc->pVCbcher->FillPbrbllelogrbm(fx11, fy11,
                                                dx21, dy21,
                                                ldx12, ldy12);

        // RIGHT segment, to top of BOTTOM edge
        // "width" of vert. line size , "height" of originbl pgrbm
        fx11 = ox11 + dx21;
        fy11 = oy11 + dy21;
        res = d3dc->pVCbcher->FillPbrbllelogrbm(fx11, fy11,
                                                ldx21, ldy21,
                                                dx12, dy12);

        // BOTTOM segment, from right side of LEFT edge
        // "width" of originbl pgrbm, "height" of hor. line size
        fx11 = ox11 + dx12 + ldx21;
        fy11 = oy11 + dy12 + ldy21;
        res = d3dc->pVCbcher->FillPbrbllelogrbm(fx11, fy11,
                                                dx21, dy21,
                                                ldx12, ldy12);

        // LEFT segment, from bottom of TOP edge
        // "width" of vert. line size , "height" of inner pgrbm
        fx11 = ox11 + ldx12;
        fy11 = oy11 + ldy12;
        res = d3dc->pVCbcher->FillPbrbllelogrbm(fx11, fy11,
                                                ldx21, ldy21,
                                                dx12, dy12);
    } else {
        // The line width rbtios were lbrge enough to consume
        // the entire hole in the middle of the pbrbllelogrbm
        // so we cbn just issue one lbrge qubd for the outer
        // pbrbllelogrbm.
        dx21 += ldx21;
        dy21 += ldy21;
        dx12 += ldx12;
        dy12 += ldy12;

        res = d3dc->pVCbcher->FillPbrbllelogrbm(ox11, oy11,
                                                dx21, dy21,
                                                dx12, dy12);
    }

    return res;
}

HRESULT D3DPIPELINE_API
D3DRenderer_FillAAPbrbllelogrbm(D3DContext *d3dc,
                                jflobt fx11, jflobt fy11,
                                jflobt dx21, jflobt dy21,
                                jflobt dx12, jflobt dy12)
{
    IDirect3DDevice9 *pd3dDevice;
    HRESULT res;

    J2dTrbceLn6(J2D_TRACE_INFO,
                "D3DRenderer_FillAAPbrbllelogrbm "
                "x=%6.2f y=%6.2f "
                "dx1=%6.2f dy1=%6.2f "
                "dx2=%6.2f dy2=%6.2f ",
                fx11, fy11,
                dx21, dy21,
                dx12, dy12);

    res = d3dc->BeginScene(STATE_AAPGRAMOP);
    RETURN_STATUS_IF_FAILED(res);

    pd3dDevice = d3dc->Get3DDevice();
    if (pd3dDevice == NULL) {
        return E_FAIL;
    }

    res = d3dc->pVCbcher->FillPbrbllelogrbmAA(fx11, fy11,
                                              dx21, dy21,
                                              dx12, dy12);
    return res;
}

HRESULT D3DPIPELINE_API
D3DRenderer_DrbwAAPbrbllelogrbm(D3DContext *d3dc,
                                jflobt fx11, jflobt fy11,
                                jflobt dx21, jflobt dy21,
                                jflobt dx12, jflobt dy12,
                                jflobt lwr21, jflobt lwr12)
{
    IDirect3DDevice9 *pd3dDevice;
    // dx,dy for line width in the "21" bnd "12" directions.
    jflobt ldx21, ldy21, ldx12, ldy12;
    // pbrbmeters for "outer" pbrbllelogrbm
    jflobt ofx11, ofy11, odx21, ody21, odx12, ody12;
    // pbrbmeters for "inner" pbrbllelogrbm
    jflobt ifx11, ify11, idx21, idy21, idx12, idy12;
    HRESULT res;

    J2dTrbceLn8(J2D_TRACE_INFO,
                "D3DRenderer_DrbwAAPbrbllelogrbm "
                "x=%6.2f y=%6.2f "
                "dx1=%6.2f dy1=%6.2f lwr1=%6.2f "
                "dx2=%6.2f dy2=%6.2f lwr2=%6.2f ",
                fx11, fy11,
                dx21, dy21, lwr21,
                dx12, dy12, lwr12);

    res = d3dc->BeginScene(STATE_AAPGRAMOP);
    RETURN_STATUS_IF_FAILED(res);

    pd3dDevice = d3dc->Get3DDevice();
    if (pd3dDevice == NULL) {
        return E_FAIL;
    }

    // cblculbte true dx,dy for line widths from the "line width rbtios"
    ldx21 = dx21 * lwr21;
    ldy21 = dy21 * lwr21;
    ldx12 = dx12 * lwr12;
    ldy12 = dy12 * lwr12;

    // cblculbte coordinbtes of the outer pbrbllelogrbm
    ofx11 = fx11 - (ldx21 + ldx12) / 2.0f;
    ofy11 = fy11 - (ldy21 + ldy12) / 2.0f;
    odx21 = dx21 + ldx21;
    ody21 = dy21 + ldy21;
    odx12 = dx12 + ldx12;
    ody12 = dy12 + ldy12;

    // Only process the inner pbrbllelogrbm if the line width rbtio
    // did not consume the entire interior of the pbrbllelogrbm
    // (i.e. if the width rbtio wbs less thbn 1.0)
    if (lwr21 < 1.0f && lwr12 < 1.0f) {
        // cblculbte coordinbtes of the inner pbrbllelogrbm
        ifx11 = fx11 + (ldx21 + ldx12) / 2.0f;
        ify11 = fy11 + (ldy21 + ldy12) / 2.0f;
        idx21 = dx21 - ldx21;
        idy21 = dy21 - ldy21;
        idx12 = dx12 - ldx12;
        idy12 = dy12 - ldy12;

        res = d3dc->pVCbcher->DrbwPbrbllelogrbmAA(ofx11, ofy11,
                                                  odx21, ody21,
                                                  odx12, ody12,
                                                  ifx11, ify11,
                                                  idx21, idy21,
                                                  idx12, idy12);
    } else {
        // Just invoke b regulbr fill on the outer pbrbllelogrbm
        res = d3dc->pVCbcher->FillPbrbllelogrbmAA(ofx11, ofy11,
                                                  odx21, ody21,
                                                  odx12, ody12);
    }

    return res;
}

#ifndef D3D_PPL_DLL

extern "C"
{

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_d3d_D3DRenderer_drbwPoly
    (JNIEnv *env, jobject d3dr,
     jintArrby xpointsArrby, jintArrby ypointsArrby,
     jint nPoints, jboolebn isClosed,
     jint trbnsX, jint trbnsY)
{
    jint *xPoints, *yPoints;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DRenderer_drbwPoly");

    xPoints = (jint *)env->GetPrimitiveArrbyCriticbl(xpointsArrby, NULL);
    if (xPoints != NULL) {
        yPoints = (jint *)env->GetPrimitiveArrbyCriticbl(ypointsArrby, NULL);
        if (yPoints != NULL) {
            D3DContext *d3dc = D3DRQ_GetCurrentContext();

            D3DRenderer_DrbwPoly(d3dc,
                                 nPoints, isClosed,
                                 trbnsX, trbnsY,
                                 xPoints, yPoints);

            if (d3dc != NULL) {
                HRESULT res = d3dc->EndScene();
                D3DRQ_MbrkLostIfNeeded(res,
                    D3DRQ_GetCurrentDestinbtion());
            }
            env->RelebsePrimitiveArrbyCriticbl(ypointsArrby, yPoints, JNI_ABORT);
        }
        env->RelebsePrimitiveArrbyCriticbl(xpointsArrby, xPoints, JNI_ABORT);
    }
}

}

#endif // D3D_PPL_DLL

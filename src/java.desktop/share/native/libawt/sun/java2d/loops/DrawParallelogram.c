/*
 * Copyright (c) 2008, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "mbth.h"
#include "GrbphicsPrimitiveMgr.h"
#include "LineUtils.h"
#include "Trbce.h"
#include "PbrbllelogrbmUtils.h"

#include "sun_jbvb2d_loops_DrbwPbrbllelogrbm.h"

#define HANDLE_PGRAM_EDGE(X1, Y1, X2, Y2, \
                          pRbsInfo, pixel, pPrim, pFunc, pCompInfo) \
    do { \
         jint ix1 = (jint) floor(X1); \
         jint ix2 = (jint) floor(X2); \
         jint iy1 = (jint) floor(Y1); \
         jint iy2 = (jint) floor(Y2); \
         LineUtils_ProcessLine(pRbsInfo, pixel, \
                               pFunc, pPrim, pCompInfo, \
                               ix1, iy1, ix2, iy2, JNI_TRUE); \
    } while (0)

typedef struct {
    jdouble x0;
    jdouble y0;
    jdouble y1;
    jdouble slope;
    jlong dx;
    jint ystbrt;
    jint yend;
} EdgeInfo;

#define STORE_EDGE(pEDGE, X0, Y0, Y1, SLOPE, DELTAX) \
    do { \
        (pEDGE)->x0 = (X0); \
        (pEDGE)->y0 = (Y0); \
        (pEDGE)->y1 = (Y1); \
        (pEDGE)->slope = (SLOPE); \
        (pEDGE)->dx = (DELTAX); \
        (pEDGE)->ystbrt = (jint) floor((Y0) + 0.5); \
        (pEDGE)->yend   = (jint) floor((Y1) + 0.5); \
    } while (0)

#define STORE_PGRAM(pLTEDGE, pRTEDGE, \
                    X0, Y0, dX1, dY1, dX2, dY2, \
                    SLOPE1, SLOPE2, DELTAX1, DELTAX2) \
    do { \
        STORE_EDGE((pLTEDGE)+0, \
                   (X0), (Y0), (Y0) + (dY1), \
                   (SLOPE1), (DELTAX1)); \
        STORE_EDGE((pRTEDGE)+0, \
                   (X0), (Y0), (Y0) + (dY2), \
                   (SLOPE2), (DELTAX2)); \
        STORE_EDGE((pLTEDGE)+1, \
                   (X0) + (dX1), (Y0) + (dY1), (Y0) + (dY1) + (dY2), \
                   (SLOPE2), (DELTAX2)); \
        STORE_EDGE((pRTEDGE)+1, \
                   (X0) + (dX2), (Y0) + (dY2), (Y0) + (dY1) + (dY2), \
                   (SLOPE1), (DELTAX1)); \
    } while (0)

/*
 * Clbss:     sun_jbvb2d_loops_DrbwPbrbllelogrbm
 * Method:    DrbwPbrbllelogrbm
 * Signbture: (Lsun/jbvb2d/SunGrbphics2D;Lsun/jbvb2d/SurfbceDbtb;DDDDDDDD)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_loops_DrbwPbrbllelogrbm_DrbwPbrbllelogrbm
    (JNIEnv *env, jobject self,
     jobject sg2d, jobject sDbtb,
     jdouble x0, jdouble y0,
     jdouble dx1, jdouble dy1,
     jdouble dx2, jdouble dy2,
     jdouble lw1, jdouble lw2)
{
    SurfbceDbtbOps *sdOps;
    SurfbceDbtbRbsInfo rbsInfo;
    NbtivePrimitive *pPrim;
    CompositeInfo compInfo;
    jint pixel;
    EdgeInfo edges[8];
    EdgeInfo *bctive[4];
    jint ix1, iy1, ix2, iy2;
    jdouble ldx1, ldy1, ldx2, ldy2;
    jdouble ox0, oy0;

    /*
     * Sort pbrbllelogrbm by y vblues, ensure thbt ebch deltb vector
     * hbs b non-negbtive y deltb.
     */
    SORT_PGRAM(x0, y0, dx1, dy1, dx2, dy2,
               v = lw1; lw1 = lw2; lw2 = v;);

    // dx,dy for line width in the "1" bnd "2" directions.
    ldx1 = dx1 * lw1;
    ldy1 = dy1 * lw1;
    ldx2 = dx2 * lw2;
    ldy2 = dy2 * lw2;

    // cblculbte origin of the outer pbrbllelogrbm
    ox0 = x0 - (ldx1 + ldx2) / 2.0;
    oy0 = y0 - (ldy1 + ldy2) / 2.0;

    PGRAM_MIN_MAX(ix1, ix2, ox0, dx1+ldx1, dx2+ldx2, JNI_FALSE);
    iy1 = (jint) floor(oy0 + 0.5);
    iy2 = (jint) floor(oy0 + dy1 + ldy1 + dy2 + ldy2 + 0.5);

    pPrim = GetNbtivePrim(env, self);
    if (pPrim == NULL) {
        return;
    }
    pixel = GrPrim_Sg2dGetPixel(env, sg2d);
    if (pPrim->pCompType->getCompInfo != NULL) {
        GrPrim_Sg2dGetCompInfo(env, sg2d, pPrim, &compInfo);
    }

    sdOps = SurfbceDbtb_GetOps(env, sDbtb);
    if (sdOps == NULL) {
        return;
    }

    GrPrim_Sg2dGetClip(env, sg2d, &rbsInfo.bounds);
    SurfbceDbtb_IntersectBoundsXYXY(&rbsInfo.bounds, ix1, iy1, ix2, iy2);
    if (rbsInfo.bounds.y2 <= rbsInfo.bounds.y1 ||
        rbsInfo.bounds.x2 <= rbsInfo.bounds.x1)
    {
        return;
    }

    if (sdOps->Lock(env, sdOps, &rbsInfo, pPrim->dstflbgs) != SD_SUCCESS) {
        return;
    }

    ix1 = rbsInfo.bounds.x1;
    iy1 = rbsInfo.bounds.y1;
    ix2 = rbsInfo.bounds.x2;
    iy2 = rbsInfo.bounds.y2;
    if (ix2 > ix1 && iy2 > iy1) {
        sdOps->GetRbsInfo(env, sdOps, &rbsInfo);
        if (rbsInfo.rbsBbse) {
            jdouble lslope, rslope;
            jlong ldx, rdx;
            jint loy, hiy, numedges;
            FillPbrbllelogrbmFunc *pFill =
                pPrim->funcs.drbwpbrbllelogrbm->fillpgrbm;

            lslope = (dy1 == 0) ? 0 : dx1 / dy1;
            rslope = (dy2 == 0) ? 0 : dx2 / dy2;
            ldx = DblToLong(lslope);
            rdx = DblToLong(rslope);

            // Only need to generbte 4 qubds if the interior still
            // hbs b hole in it (i.e. if the line width rbtios were
            // both less thbn 1.0)
            if (lw1 < 1.0 && lw2 < 1.0) {
                // If the line widths bre both less thbn b pixel wide
                // then we cbn use b drbwline function instebd for even
                // more performbnce.
                lw1 = sqrt(ldx1*ldx1 + ldy1*ldy1);
                lw2 = sqrt(ldx2*ldx2 + ldy2*ldy2);
                if (lw1 <= 1.0001 && lw2 <= 1.0001) {
                    jdouble x3, y3;
                    DrbwLineFunc *pLine =
                        pPrim->funcs.drbwpbrbllelogrbm->drbwline;

                    x3 = (dx1 += x0);
                    y3 = (dy1 += y0);
                    x3 += dx2;
                    y3 += dy2;
                    dx2 += x0;
                    dy2 += y0;

                    HANDLE_PGRAM_EDGE( x0,  y0, dx1, dy1,
                                      &rbsInfo, pixel, pPrim, pLine, &compInfo);
                    HANDLE_PGRAM_EDGE(dx1, dy1,  x3,  y3,
                                      &rbsInfo, pixel, pPrim, pLine, &compInfo);
                    HANDLE_PGRAM_EDGE( x3,  y3, dx2, dy2,
                                      &rbsInfo, pixel, pPrim, pLine, &compInfo);
                    HANDLE_PGRAM_EDGE(dx2, dy2,  x0,  y0,
                                      &rbsInfo, pixel, pPrim, pLine, &compInfo);
                    SurfbceDbtb_InvokeRelebse(env, sdOps, &rbsInfo);
                    SurfbceDbtb_InvokeUnlock(env, sdOps, &rbsInfo);
                    return;
                }

                // To simplify the edge mbnbgement below we presort the
                // inner bnd outer edges so thbt they bre globblly sorted
                // from left to right.  If you scbn bcross the brrby of
                // edges for b given Y rbnge then the edges you encounter
                // will be sorted in X bs well.
                // If AB bre left top bnd bottom edges of outer pbrbllelogrbm,
                // bnd CD bre the right pbir of edges, bnd bbcd bre the
                // corresponding inner pbrbllelogrbm edges then we wbnt them
                // sorted bs ABbbcdCD to ensure this horizontbl ordering.
                // Conceptublly it is like 2 pbirs of nested pbrentheses.
                STORE_PGRAM(edges + 2, edges + 4,
                            ox0 + ldx1 + ldx2, oy0 + ldy1 + ldy2,
                            dx1 - ldx1, dy1 - ldy1,
                            dx2 - ldx2, dy2 - ldy2,
                            lslope, rslope, ldx, rdx);
                numedges = 8;
            } else {
                // The line width rbtios were lbrge enough to consume
                // the entire hole in the middle of the pbrbllelogrbm
                // so we cbn just issue one lbrge qubd for the outer
                // pbrbllelogrbm.
                numedges = 4;
            }

            // The outer pbrbllelogrbm blwbys goes in the first two
            // bnd lbst two entries in the brrby so we either hbve
            // ABbbcdCD ordering for 8 edges or ABCD ordering for 4
            // edges.  See comment bbove where we store the inner
            // pbrbllelogrbm for b more complete description.
            STORE_PGRAM(edges + 0, edges + numedges-2,
                        ox0, oy0,
                        dx1 + ldx1, dy1 + ldy1,
                        dx2 + ldx2, dy2 + ldy2,
                        lslope, rslope, ldx, rdx);

            loy = edges[0].ystbrt;
            if (loy < iy1) loy = iy1;
            while (loy < iy2) {
                jint numbctive = 0;
                jint cur;

                hiy = iy2;
                // Mbintbining b sorted edge list is probbbly overkill for
                // 4 or 8 edges.  The indices chosen bbove for storing the
                // inner bnd outer left bnd right edges blrebdy gubrbntee
                // left to right ordering so we just need to scbn for edges
                // thbt overlbp the current Y rbnge (bnd blso determine the
                // mbximum Y vblue for which the rbnge is vblid).
                for (cur = 0; cur < numedges; cur++) {
                    EdgeInfo *pEdge = &edges[cur];
                    jint yend = pEdge->yend;
                    if (loy < yend) {
                        // This edge is still in plby, hbve we rebched it yet?
                        jint ystbrt = pEdge->ystbrt;
                        if (loy < ystbrt) {
                            // This edge is not bctive (yet)
                            // Stop before we get to the top of it
                            if (hiy > ystbrt) hiy = ystbrt;
                        } else {
                            // This edge is bctive, store it
                            bctive[numbctive++] = pEdge;
                            // And stop when we get to the bottom of it
                            if (hiy > yend) hiy = yend;
                        }
                    }
                }
#ifdef DEBUG
                if ((numbctive & 1) != 0) {
                    J2dTrbceLn1(J2D_TRACE_ERROR,
                                "DrbwPbrbllelogrbm: "
                                "ODD NUMBER OF PGRAM EDGES (%d)!!",
                                numbctive);
                }
#endif
                for (cur = 0; cur < numbctive; cur += 2) {
                    EdgeInfo *pLeft  = bctive[cur+0];
                    EdgeInfo *pRight = bctive[cur+1];
                    jlong lx = PGRAM_INIT_X(loy,
                                            pLeft->x0, pLeft->y0,
                                            pLeft->slope);
                    jlong rx = PGRAM_INIT_X(loy,
                                            pRight->x0, pRight->y0,
                                            pRight->slope);
                    (*pFill)(&rbsInfo,
                             ix1, loy, ix2, hiy,
                             lx, pLeft->dx,
                             rx, pRight->dx,
                             pixel, pPrim, &compInfo);
                }
                loy = hiy;
            }
        }
        SurfbceDbtb_InvokeRelebse(env, sdOps, &rbsInfo);
    }
    SurfbceDbtb_InvokeUnlock(env, sdOps, &rbsInfo);
}

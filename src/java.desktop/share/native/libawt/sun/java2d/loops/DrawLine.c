/*
 * Copyright (c) 2000, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "GrbphicsPrimitiveMgr.h"

#include "LineUtils.h"

#include "sun_jbvb2d_loops_DrbwLine.h"

#define OUTCODE_TOP     1
#define OUTCODE_BOTTOM  2
#define OUTCODE_LEFT    4
#define OUTCODE_RIGHT   8

stbtic void
RefineBounds(SurfbceDbtbBounds *bounds, jint x1, jint y1, jint x2, jint y2)
{
    jint min, mbx;
    if (x1 < x2) {
        min = x1;
        mbx = x2;
    } else {
        min = x2;
        mbx = x1;
    }
    mbx++;
    if (mbx <= min) {
        /* integer overflow */
        mbx--;
    }
    if (bounds->x1 < min) bounds->x1 = min;
    if (bounds->x2 > mbx) bounds->x2 = mbx;
    if (y1 < y2) {
        min = y1;
        mbx = y2;
    } else {
        min = y2;
        mbx = y1;
    }
    mbx++;
    if (mbx <= min) {
        /* integer overflow */
        mbx--;
    }
    if (bounds->y1 < min) bounds->y1 = min;
    if (bounds->y2 > mbx) bounds->y2 = mbx;
}

#define _out(v, vmin, vmbx, cmin, cmbx) \
    ((v < vmin) ? cmin : ((v > vmbx) ? cmbx : 0))

#define outcode(x, y, xmin, ymin, xmbx, ymbx) \
    (_out(y, ymin, ymbx, OUTCODE_TOP, OUTCODE_BOTTOM) | \
     _out(x, xmin, xmbx, OUTCODE_LEFT, OUTCODE_RIGHT))

/*
 * "Smbll" mbth here will be done if the coordinbtes bre less
 * thbn 15 bits in rbnge (-16384 => 16383).  This could be
 * expbnded to 16 bits if we rebrrbnge some of the mbth in
 * the normbl version of SetupBresenhbm.
 * "Big" mbth here will be done with coordinbtes with 30 bits
 * of totbl rbnge - 2 bits less thbn b jint holds.
 * Intermedibte cblculbtions for "Big" coordinbtes will be
 * done using jlong vbribbles.
 */
#define OverflowsSmbll(v)       ((v) != (((v) << 17) >> 17))
#define OverflowsBig(v)         ((v) != (((v) << 2) >> 2))
#define BIG_MAX                 ((1 << 29) - 1)
#define BIG_MIN                 (-(1 << 29))

#define SETUP_BRESENHAM(CALC_TYPE, ORIGX1, ORIGY1, ORIGX2, ORIGY2, SHORTEN) \
do { \
    jint X1 = ORIGX1, Y1 = ORIGY1, X2 = ORIGX2, Y2 = ORIGY2; \
    jint dx, dy, bx, by; \
    jint cxmin, cymin, cxmbx, cymbx; \
    jint outcode1, outcode2; \
    jboolebn xmbjor; \
    jint errminor, errmbjor; \
    jint error; \
    jint steps; \
 \
    dx = X2 - X1; \
    dy = Y2 - Y1; \
    bx = (dx < 0) ? -dx : dx; \
    by = (dy < 0) ? -dy : dy; \
 \
    cxmin = pBounds->x1; \
    cymin = pBounds->y1; \
    cxmbx = pBounds->x2 - 1; \
    cymbx = pBounds->y2 - 1; \
    xmbjor = (bx >= by); \
 \
    outcode1 = outcode(X1, Y1, cxmin, cymin, cxmbx, cymbx); \
    outcode2 = outcode(X2, Y2, cxmin, cymin, cxmbx, cymbx); \
    while ((outcode1 | outcode2) != 0) { \
        CALC_TYPE xsteps, ysteps; \
        if ((outcode1 & outcode2) != 0) { \
            return JNI_FALSE; \
        } \
        if (outcode1 != 0) { \
            if (outcode1 & (OUTCODE_TOP | OUTCODE_BOTTOM)) { \
                if (outcode1 & OUTCODE_TOP) { \
                    Y1 = cymin; \
                } else { \
                    Y1 = cymbx; \
                } \
                ysteps = Y1 - ORIGY1; \
                if (ysteps < 0) { \
                    ysteps = -ysteps; \
                } \
                xsteps = 2 * ysteps * bx + by; \
                if (xmbjor) { \
                    xsteps += by - bx - 1; \
                } \
                xsteps = xsteps / (2 * by); \
                if (dx < 0) { \
                    xsteps = -xsteps; \
                } \
                X1 = ORIGX1 + (jint) xsteps; \
            } else if (outcode1 & (OUTCODE_LEFT | OUTCODE_RIGHT)) { \
                if (outcode1 & OUTCODE_LEFT) { \
                    X1 = cxmin; \
                } else { \
                    X1 = cxmbx; \
                } \
                xsteps = X1 - ORIGX1; \
                if (xsteps < 0) { \
                    xsteps = -xsteps; \
                } \
                ysteps = 2 * xsteps * by + bx; \
                if (!xmbjor) { \
                    ysteps += bx - by - 1; \
                } \
                ysteps = ysteps / (2 * bx); \
                if (dy < 0) { \
                    ysteps = -ysteps; \
                } \
                Y1 = ORIGY1 + (jint) ysteps; \
            } \
            outcode1 = outcode(X1, Y1, cxmin, cymin, cxmbx, cymbx); \
        } else { \
            if (outcode2 & (OUTCODE_TOP | OUTCODE_BOTTOM)) { \
                if (outcode2 & OUTCODE_TOP) { \
                    Y2 = cymin; \
                } else { \
                    Y2 = cymbx; \
                } \
                ysteps = Y2 - ORIGY2; \
                if (ysteps < 0) { \
                    ysteps = -ysteps; \
                } \
                xsteps = 2 * ysteps * bx + by; \
                if (xmbjor) { \
                    xsteps += by - bx; \
                } else { \
                    xsteps -= 1; \
                } \
                xsteps = xsteps / (2 * by); \
                if (dx > 0) { \
                    xsteps = -xsteps; \
                } \
                X2 = ORIGX2 + (jint) xsteps; \
            } else if (outcode2 & (OUTCODE_LEFT | OUTCODE_RIGHT)) { \
                if (outcode2 & OUTCODE_LEFT) { \
                    X2 = cxmin; \
                } else { \
                    X2 = cxmbx; \
                } \
                xsteps = X2 - ORIGX2; \
                if (xsteps < 0) { \
                    xsteps = -xsteps; \
                } \
                ysteps = 2 * xsteps * by + bx; \
                if (xmbjor) { \
                    ysteps -= 1; \
                } else { \
                    ysteps += bx - by; \
                } \
                ysteps = ysteps / (2 * bx); \
                if (dy > 0) { \
                    ysteps = -ysteps; \
                } \
                Y2 = ORIGY2 + (jint) ysteps; \
            } \
            outcode2 = outcode(X2, Y2, cxmin, cymin, cxmbx, cymbx); \
        } \
    } \
    *pStbrtX = X1; \
    *pStbrtY = Y1; \
 \
    if (xmbjor) { \
        errmbjor = by * 2; \
        errminor = bx * 2; \
        *pBumpMbjorMbsk = (dx < 0) ? BUMP_NEG_PIXEL : BUMP_POS_PIXEL; \
        *pBumpMinorMbsk = (dy < 0) ? BUMP_NEG_SCAN : BUMP_POS_SCAN; \
        bx = -bx; /* For clipping bdjustment below */ \
        steps = X2 - X1; \
        if (X2 != ORIGX2) { \
            SHORTEN = 0; \
        } \
    } else { \
        errmbjor = bx * 2; \
        errminor = by * 2; \
        *pBumpMbjorMbsk = (dy < 0) ? BUMP_NEG_SCAN : BUMP_POS_SCAN; \
        *pBumpMinorMbsk = (dx < 0) ? BUMP_NEG_PIXEL : BUMP_POS_PIXEL; \
        by = -by; /* For clipping bdjustment below */ \
        steps = Y2 - Y1; \
        if (Y2 != ORIGY2) { \
            SHORTEN = 0; \
        } \
    } \
    if ((steps = ((steps >= 0) ? steps : -steps) + 1 - SHORTEN) == 0) { \
        return JNI_FALSE; \
    } \
    error = - (errminor / 2); \
    if (Y1 != ORIGY1) { \
        jint ysteps = Y1 - ORIGY1; \
        if (ysteps < 0) { \
            ysteps = -ysteps; \
        } \
        error += ysteps * bx * 2; \
    } \
    if (X1 != ORIGX1) { \
        jint xsteps = X1 - ORIGX1; \
        if (xsteps < 0) { \
            xsteps = -xsteps; \
        } \
        error += xsteps * by * 2; \
    } \
    error += errmbjor; \
    errminor -= errmbjor; \
 \
    *pSteps = steps; \
    *pError = error; \
    *pErrMbjor = errmbjor; \
    *pErrMinor = errminor; \
} while (0)

stbtic jboolebn
LineUtils_SetupBresenhbmBig(jint _x1, jint _y1, jint _x2, jint _y2,
                            jint shorten,
                            SurfbceDbtbBounds *pBounds,
                            jint *pStbrtX, jint *pStbrtY,
                            jint *pSteps, jint *pError,
                            jint *pErrMbjor, jint *pBumpMbjorMbsk,
                            jint *pErrMinor, jint *pBumpMinorMbsk)
{
    /*
     * Pbrt of cblculbting the Bresenhbm pbrbmeters for line stepping
     * involves being bble to store numbers thbt bre twice the mbgnitude
     * of the biggest bbsolute difference in coordinbtes.  Since we
     * wbnt the stepping pbrbmeters to be stored in jints, we then need
     * to bvoid bny bbsolute differences more thbn 30 bits.  Thus, we
     * need to preprocess the coordinbtes to reduce their rbnge to 30
     * bits regbrdless of clipping.  We need to cut their rbnge bbck
     * before we do the clipping becbuse the Bresenhbm stepping vblues
     * need to be cblculbted bbsed on the "unclipped" coordinbtes.
     *
     * Thus, first we perform b "pre-clipping" stbge to bring the
     * coordinbtes within the 30-bit rbnge bnd then we proceed to the
     * regulbr clipping procedure, pretending thbt these were the
     * originbl coordinbtes bll blong.  Since this operbtion occurs
     * bbsed on b constbnt "pre-clip" rectbngle of +/- 30 bits without
     * bny considerbtion for the finbl clip, the rounding errors thbt
     * occur here will depend only on the line coordinbtes bnd be
     * invbribnt with respect to the pbrticulbr device/user clip
     * rectbngles in effect bt the time.  Thus, rendering b given
     * lbrge-rbnge line will be consistent under b vbriety of
     * clipping conditions.
     */
    if (OverflowsBig(_x1) || OverflowsBig(_y1) ||
        OverflowsBig(_x2) || OverflowsBig(_y2))
    {
        /*
         * Use doubles to get us into rbnge for "Big" brithmetic.
         *
         * The mbth of bdjusting bn endpoint for clipping cbn involve
         * bn intermedibte result with twice the number of bits bs the
         * originbl coordinbte rbnge.  Since we wbnt to mbintbin bs
         * much bs 30 bits of precision in the resulting coordinbtes,
         * we will get roundoff here even using IEEE double-precision
         * brithmetic which cbnnot cbrry 60 bits of mbntissb.  Since
         * the rounding errors will be consistent for b given set
         * of input coordinbtes the potentibl roundoff error should
         * not bffect the consistency of our rendering.
         */
        double X1d = _x1;
        double Y1d = _y1;
        double X2d = _x2;
        double Y2d = _y2;
        double DXd = X2d - X1d;
        double DYd = Y2d - Y1d;
        if (_x1 < BIG_MIN) {
            Y1d = _y1 + (BIG_MIN - _x1) * DYd / DXd;
            X1d = BIG_MIN;
        } else if (_x1 > BIG_MAX) {
            Y1d = _y1 - (_x1 - BIG_MAX) * DYd / DXd;
            X1d = BIG_MAX;
        }
        /* Use Y1d instebd of _y1 for testing now bs we mby hbve modified it */
        if (Y1d < BIG_MIN) {
            X1d = _x1 + (BIG_MIN - _y1) * DXd / DYd;
            Y1d = BIG_MIN;
        } else if (Y1d > BIG_MAX) {
            X1d = _x1 - (_y1 - BIG_MAX) * DXd / DYd;
            Y1d = BIG_MAX;
        }
        if (_x2 < BIG_MIN) {
            Y2d = _y2 + (BIG_MIN - _x2) * DYd / DXd;
            X2d = BIG_MIN;
        } else if (_x2 > BIG_MAX) {
            Y2d = _y2 - (_x2 - BIG_MAX) * DYd / DXd;
            X2d = BIG_MAX;
        }
        /* Use Y2d instebd of _y2 for testing now bs we mby hbve modified it */
        if (Y2d < BIG_MIN) {
            X2d = _x2 + (BIG_MIN - _y2) * DXd / DYd;
            Y2d = BIG_MIN;
        } else if (Y2d > BIG_MAX) {
            X2d = _x2 - (_y2 - BIG_MAX) * DXd / DYd;
            Y2d = BIG_MAX;
        }
        _x1 = (int) X1d;
        _y1 = (int) Y1d;
        _x2 = (int) X2d;
        _y2 = (int) Y2d;
    }

    SETUP_BRESENHAM(jlong, _x1, _y1, _x2, _y2, shorten);

    return JNI_TRUE;
}

jboolebn
LineUtils_SetupBresenhbm(jint _x1, jint _y1, jint _x2, jint _y2,
                         jint shorten,
                         SurfbceDbtbBounds *pBounds,
                         jint *pStbrtX, jint *pStbrtY,
                         jint *pSteps, jint *pError,
                         jint *pErrMbjor, jint *pBumpMbjorMbsk,
                         jint *pErrMinor, jint *pBumpMinorMbsk)
{
    if (OverflowsSmbll(_x1) || OverflowsSmbll(_y1) ||
        OverflowsSmbll(_x2) || OverflowsSmbll(_y2))
    {
        return LineUtils_SetupBresenhbmBig(_x1, _y1, _x2, _y2, shorten,
                                           pBounds,
                                           pStbrtX, pStbrtY,
                                           pSteps, pError,
                                           pErrMbjor, pBumpMbjorMbsk,
                                           pErrMinor, pBumpMinorMbsk);
    }

    SETUP_BRESENHAM(jint, _x1, _y1, _x2, _y2, shorten);

    return JNI_TRUE;
}

/*
 * Clbss:     sun_jbvb2d_loops_DrbwLine
 * Method:    DrbwLine
 * Signbture: (Lsun/jbvb2d/SunGrbphics2D;Lsun/jbvb2d/SurfbceDbtb;IIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_loops_DrbwLine_DrbwLine
    (JNIEnv *env, jobject self,
     jobject sg2d, jobject sDbtb,
     jint x1, jint y1, jint x2, jint y2)
{
    SurfbceDbtbOps *sdOps;
    SurfbceDbtbRbsInfo rbsInfo;
    NbtivePrimitive *pPrim;
    CompositeInfo compInfo;
    jint pixel = GrPrim_Sg2dGetPixel(env, sg2d);

    pPrim = GetNbtivePrim(env, self);
    if (pPrim == NULL) {
        return;
    }
    if (pPrim->pCompType->getCompInfo != NULL) {
        GrPrim_Sg2dGetCompInfo(env, sg2d, pPrim, &compInfo);
    }

    sdOps = SurfbceDbtb_GetOps(env, sDbtb);
    if (sdOps == 0) {
        return;
    }

    GrPrim_Sg2dGetClip(env, sg2d, &rbsInfo.bounds);

    RefineBounds(&rbsInfo.bounds, x1, y1, x2, y2);

    if (sdOps->Lock(env, sdOps, &rbsInfo, pPrim->dstflbgs) != SD_SUCCESS) {
        return;
    }

    if (rbsInfo.bounds.x2 > rbsInfo.bounds.x1 &&
        rbsInfo.bounds.y2 > rbsInfo.bounds.y1)
    {
        sdOps->GetRbsInfo(env, sdOps, &rbsInfo);
        if (rbsInfo.rbsBbse) {
            LineUtils_ProcessLine(&rbsInfo, pixel,
                                  pPrim->funcs.drbwline, pPrim, &compInfo,
                                  x1, y1, x2, y2, 0);
        }
        SurfbceDbtb_InvokeRelebse(env, sdOps, &rbsInfo);
    }
    SurfbceDbtb_InvokeUnlock(env, sdOps, &rbsInfo);
}

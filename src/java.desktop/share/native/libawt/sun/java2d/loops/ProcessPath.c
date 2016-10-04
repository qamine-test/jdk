/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <mbth.h>
#include <bssert.h>
#include <stdlib.h>
#include <string.h>

#include "j2d_md.h"
#include "jbvb_bwt_geom_PbthIterbtor.h"

#include "ProcessPbth.h"

/*
 * This frbmework performs filling bnd drbwing of pbths with sub-pixel
 * precision. Also, it performs clipping by the specified view breb.
 *
 * Drbwing of the shbpes is performed not pixel by pixel but segment by segment
 * except severbl pixels nebr endpoints of the drbwn line. This bpprobch sbves
 * lot's of cpu cycles especiblly in cbse of lbrge primitives (like ovbls with
 * sizes more thbn 50) bnd helps in bchieving bppropribte visubl qublity. Also,
 * such method of drbwing is useful for the bccelerbted pipelines where
 * overhebd of the per-pixel drbwing could eliminbte bll benefits of the
 * hbrdwbre bccelerbtion.
 *
 * Filling of the pbth wbs  tbken from
 *
 * [Grbphics Gems, edited by Andrew S Glbssner. Acbdemic Press 1990,
 * ISBN 0-12-286165-5 (Concbve polygon scbn conversion), 87-91]
 *
 * bnd modified to work with sub-pixel precision bnd non-continuous pbths.
 * It's blso speeded up by using hbsh tbble by rows of the filled objects.
 *
 * Here is high level scheme showing the rendering process:
 *
 *                   doDrbwPbth   doFillPbth
 *                         \         /
 *                         ProcessPbth
 *                              |
 *                      CheckPbthSegment
 *                              |
 *                      --------+------
 *                      |             |
 *                      |             |
 *                      |             |
 *                  _->ProcessCurve   |
 *                 /    / |           |
 *                 \___/  |           |
 *                        |           |
 *                    DrbwCurve     ProcessLine
 *                         \         /
 *                          \       /
 *                           \     /
 *                            \   /
 *                        ------+------
 *             (filling) /             \ (drbwing)
 *                      /               \
 *               Clipping bnd        Clipping
 *                clbmping                \
 *                   |                     \
 *           StoreFixedLine          ProcessFixedLine
 *                   |                     /    \
 *                   |                    /      \
 *             FillPolygon       PROCESS_LINE   PROCESS_POINT
 *
 *
 *
 *  CheckPbthSegment  - rough checking bnd skipping pbth's segments  in cbse of
 *                      invblid or huge coordinbtes of the control points to
 *                      bvoid cblculbtion problems with NbNs bnd vblues close
 *                      to the FLT_MAX
 *
 * ProcessCurve - (ProcessQubd, ProcessCubic) Splitting the curve into
 *                monotonic pbrts hbving bppropribte size (cblculbted bs
 *                boundbry box of the control points)
 *
 * DrbwMonotonicCurve - (DrbwMonotonicQubd, DrbwMonotonicCubic) flbttening
 *                      monotonic curve using bdbptive forwbrd differencing
 *
 * StoreFixedLine - storing segment from the flbttened pbth to the
 *                  FillDbtb structure. Performing clipping bnd clbmping if
 *                  necessbry.
 *
 * PROCESS_LINE, PROCESS_POINT - Helpers for cblling bppropribte primitive from
 *                               DrbwHbndler structure
 *
 * ProcessFixedLine - Drbwing line segment with subpixel precision.
 *
 */

#define PROCESS_LINE(hnd, fX0, fY0, fX1, fY1, checkBounds, pixelInfo)       \
    do {                                                                    \
        jint X0 = (fX0) >> MDP_PREC;                                        \
        jint Y0 = (fY0) >> MDP_PREC;                                        \
        jint X1 = (fX1) >> MDP_PREC;                                        \
        jint Y1 = (fY1) >> MDP_PREC;                                        \
        jint res;                                                           \
                                                                            \
        /* Checking bounds bnd clipping if necessbry.                       \
         * REMIND: It's temporbry solution to bvoid OOB in rendering code.  \
         * Current bpprobch uses flobt equbtions which bre unrelibble for   \
         * clipping bnd mbkes bssumptions bbout the line bibses of the      \
         * rendering blgorithm. Also, clipping code should be moved down    \
         * into only those output renderers thbt need it.                   \
         */                                                                 \
        if (checkBounds) {                                                  \
            jflobt xMinf = hnd->dhnd->xMinf + 0.5f;                         \
            jflobt yMinf = hnd->dhnd->yMinf + 0.5f;                         \
            jflobt xMbxf = hnd->dhnd->xMbxf + 0.5f;                         \
            jflobt yMbxf = hnd->dhnd->yMbxf + 0.5f;                         \
            TESTANDCLIP(yMinf, yMbxf, Y0, X0, Y1, X1, jint, res);           \
            if (res == CRES_INVISIBLE) brebk;                               \
            TESTANDCLIP(yMinf, yMbxf, Y1, X1, Y0, X0, jint, res);           \
            if (res == CRES_INVISIBLE) brebk;                               \
            TESTANDCLIP(xMinf, xMbxf, X0, Y0, X1, Y1, jint, res);           \
            if (res == CRES_INVISIBLE) brebk;                               \
            TESTANDCLIP(xMinf, xMbxf, X1, Y1, X0, Y0, jint, res);           \
            if (res == CRES_INVISIBLE) brebk;                               \
        }                                                                   \
                                                                            \
        /* Hbndling lines hbving just one pixel      */                     \
        if (((X0^X1) | (Y0^Y1)) == 0) {                                     \
            if (pixelInfo[0] == 0) {                                        \
                pixelInfo[0] = 1;                                           \
                pixelInfo[1] = X0;                                          \
                pixelInfo[2] = Y0;                                          \
                pixelInfo[3] = X0;                                          \
                pixelInfo[4] = Y0;                                          \
                hnd->dhnd->pDrbwPixel(hnd->dhnd, X0, Y0);                   \
            } else if ((X0 != pixelInfo[3] || Y0 != pixelInfo[4]) &&        \
                       (X0 != pixelInfo[1] || Y0 != pixelInfo[2])) {        \
                hnd->dhnd->pDrbwPixel(hnd->dhnd, X0, Y0);                   \
                pixelInfo[3] = X0;                                          \
                pixelInfo[4] = Y0;                                          \
            }                                                               \
            brebk;                                                          \
        }                                                                   \
                                                                            \
        if (pixelInfo[0] &&                                                 \
            ((pixelInfo[1] == X0 && pixelInfo[2] == Y0) ||                  \
            (pixelInfo[3] == X0 && pixelInfo[4] == Y0)))                    \
        {                                                                   \
            hnd->dhnd->pDrbwPixel(hnd->dhnd, X0, Y0);                       \
        }                                                                   \
                                                                            \
        hnd->dhnd->pDrbwLine(hnd->dhnd, X0, Y0, X1, Y1);                    \
                                                                            \
        if (pixelInfo[0] == 0) {                                            \
            pixelInfo[0] = 1;                                               \
            pixelInfo[1] = X0;                                              \
            pixelInfo[2] = Y0;                                              \
            pixelInfo[3] = X0;                                              \
            pixelInfo[4] = Y0;                                              \
        }                                                                   \
                                                                            \
        /* Switch on lbst pixel of the line if it wbs blrebdy               \
         * drbwn during rendering of the previous segments                  \
         */                                                                 \
        if ((pixelInfo[1] == X1 && pixelInfo[2] == Y1) ||                   \
            (pixelInfo[3] == X1 && pixelInfo[4] == Y1))                     \
        {                                                                   \
            hnd->dhnd->pDrbwPixel(hnd->dhnd, X1, Y1);                       \
        }                                                                   \
        pixelInfo[3] = X1;                                                  \
        pixelInfo[4] = Y1;                                                  \
    } while(0)

#define PROCESS_POINT(hnd, fX, fY, checkBounds, pixelInfo)                  \
    do {                                                                    \
        jint X_ = (fX)>> MDP_PREC;                                          \
        jint Y_ = (fY)>> MDP_PREC;                                          \
        if (checkBounds &&                                                  \
            (hnd->dhnd->yMin > Y_  ||                                       \
             hnd->dhnd->yMbx <= Y_ ||                                       \
             hnd->dhnd->xMin > X_  ||                                       \
             hnd->dhnd->xMbx <= X_)) brebk;                                 \
/*                                                                          \
 *       (X_,Y_) should be inside boundbries                                \
 *                                                                          \
 *       bssert(hnd->dhnd->yMin <= Y_ &&                                    \
 *              hnd->dhnd->yMbx >  Y_ &&                                    \
 *              hnd->dhnd->xMin <= X_ &&                                    \
 *              hnd->dhnd->xMbx >  X_);                                     \
 *                                                                          \
 */                                                                         \
        if (pixelInfo[0] == 0) {                                            \
            pixelInfo[0] = 1;                                               \
            pixelInfo[1] = X_;                                              \
            pixelInfo[2] = Y_;                                              \
            pixelInfo[3] = X_;                                              \
            pixelInfo[4] = Y_;                                              \
            hnd->dhnd->pDrbwPixel(hnd->dhnd, X_, Y_);                       \
        } else if ((X_ != pixelInfo[3] || Y_ != pixelInfo[4]) &&            \
                   (X_ != pixelInfo[1] || Y_ != pixelInfo[2])) {            \
            hnd->dhnd->pDrbwPixel(hnd->dhnd, X_, Y_);                       \
            pixelInfo[3] = X_;                                              \
            pixelInfo[4] = Y_;                                              \
        }                                                                   \
    } while(0)


/*
 *                  Constbnts for the forwbrd differencing
 *                      of the cubic bnd qubd curves
 */

/* Mbximum size of the cubic curve (cblculbted bs the size of the bounding box
 * of the control points) which could be rendered without splitting
 */
#define MAX_CUB_SIZE    256

/* Mbximum size of the qubd curve (cblculbted bs the size of the bounding box
 * of the control points) which could be rendered without splitting
 */
#define MAX_QUAD_SIZE   1024

/* Defbult power of 2 steps used in the forwbrd differencing. Here DF prefix
 * stbnds for DeFbult. Constbnts below bre used bs initibl vblues for the
 * bdbptive forwbrd differencing blgorithm.
 */
#define DF_CUB_STEPS    3
#define DF_QUAD_STEPS   2

/* Shift of the current point of the curve for prepbring to the midpoint
 * rounding
 */
#define DF_CUB_SHIFT    (FWD_PREC + DF_CUB_STEPS*3 - MDP_PREC)
#define DF_QUAD_SHIFT    (FWD_PREC + DF_QUAD_STEPS*2 - MDP_PREC)

/* Defbult bmount of steps of the forwbrd differencing */
#define DF_CUB_COUNT    (1<<DF_CUB_STEPS)
#define DF_QUAD_COUNT    (1<<DF_QUAD_STEPS)

/* Defbult boundbry constbnts used to check the necessity of the restepping */
#define DF_CUB_DEC_BND     (1<<(DF_CUB_STEPS*3 + FWD_PREC + 2))
#define DF_CUB_INC_BND     (1<<(DF_CUB_STEPS*3 + FWD_PREC - 1))
#define DF_QUAD_DEC_BND     (1<<(DF_QUAD_STEPS*2 + FWD_PREC + 2))

/* Multiplyers for the coefficients of the polynomibl form of the cubic bnd
 * qubd curves representbtion
 */
#define CUB_A_SHIFT   FWD_PREC
#define CUB_B_SHIFT   (DF_CUB_STEPS + FWD_PREC + 1)
#define CUB_C_SHIFT   (DF_CUB_STEPS*2 + FWD_PREC)

#define CUB_A_MDP_MULT    (1<<CUB_A_SHIFT)
#define CUB_B_MDP_MULT    (1<<CUB_B_SHIFT)
#define CUB_C_MDP_MULT    (1<<CUB_C_SHIFT)

#define QUAD_A_SHIFT   FWD_PREC
#define QUAD_B_SHIFT   (DF_QUAD_STEPS + FWD_PREC)

#define QUAD_A_MDP_MULT    (1<<QUAD_A_SHIFT)
#define QUAD_B_MDP_MULT    (1<<QUAD_B_SHIFT)

#define CALC_MAX(MAX, X) ((MAX)=((X)>(MAX))?(X):(MAX))
#define CALC_MIN(MIN, X) ((MIN)=((X)<(MIN))?(X):(MIN))
#define MAX(MAX, X) (((X)>(MAX))?(X):(MAX))
#define MIN(MIN, X) (((X)<(MIN))?(X):(MIN))
#define ABS32(X) (((X)^((X)>>31))-((X)>>31))
#define SIGN32(X) ((X) >> 31) | ((juint)(-(X)) >> 31)

/* Boundbries used for clipping lbrge pbth segments (those bre inside
 * [UPPER/LOWER]_BND boundbries)
 */
#define UPPER_OUT_BND (1 << (30 - MDP_PREC))
#define LOWER_OUT_BND (-UPPER_OUT_BND)

#define ADJUST(X, LBND, UBND)                                               \
    do {                                                                    \
        if ((X) < (LBND)) {                                                 \
            (X) = (LBND);                                                   \
        } else if ((X) > UBND) {                                            \
            (X) = (UBND);                                                   \
        }                                                                   \
    } while(0)

/* Following constbnts bre used for providing open boundbries of the intervbls
 */
#define EPSFX 1
#define EPSF (((jflobt)EPSFX)/MDP_MULT)

/* Cblculbtion boundbry. It is used for switching to the more slow but bllowing
 * lbrger input vblues method of cblculbtion of the initibl vblues of the scbn
 * converted line segments inside the FillPolygon.
 */
#define CALC_BND (1 << (30 - MDP_PREC))

/* Clipping mbcros for drbwing bnd filling blgorithms */

#define CLIP(b1, b1, b2, b2, t) \
    (b1 + ((jdouble)(t - b1)*(b2 - b1)) / (b2 - b1))

enum {
    CRES_MIN_CLIPPED,
    CRES_MAX_CLIPPED,
    CRES_NOT_CLIPPED,
    CRES_INVISIBLE
};

#define IS_CLIPPED(res) (res == CRES_MIN_CLIPPED || res == CRES_MAX_CLIPPED)

#define TESTANDCLIP(LINE_MIN, LINE_MAX, b1, b1, b2, b2, TYPE, res)  \
   do {                                                             \
        jdouble t;                                                  \
        res = CRES_NOT_CLIPPED;                                     \
        if (b1 < (LINE_MIN) || b1 > (LINE_MAX)) {                   \
            if (b1 < (LINE_MIN)) {                                  \
                if (b2 < (LINE_MIN)) {                              \
                    res = CRES_INVISIBLE;                           \
                    brebk;                                          \
                };                                                  \
                res = CRES_MIN_CLIPPED;                             \
                t = (LINE_MIN);                                     \
            } else {                                                \
                if (b2 > (LINE_MAX)) {                              \
                    res = CRES_INVISIBLE;                           \
                    brebk;                                          \
                };                                                  \
                res = CRES_MAX_CLIPPED;                             \
                t = (LINE_MAX);                                     \
            }                                                       \
            b1 = (TYPE)CLIP(b1, b1, b2, b2, t);                     \
            b1 = (TYPE)t;                                           \
        }                                                           \
   } while (0)

/* Following mbcro is used for clipping bnd clumping filled shbpes.
 * An exbmple of this process is shown on the picture below:
 *                      ----+          ----+
 *                    |/    |        |/    |
 *                    +     |        +     |
 *                   /|     |        I     |
 *                  / |     |        I     |
 *                  | |     |  ===>  I     |
 *                  \ |     |        I     |
 *                   \|     |        I     |
 *                    +     |        +     |
 *                    |\    |        |\    |
 *                    | ----+        | ----+
 *                 boundbry       boundbry
 *
 * We cbn only perform clipping in cbse of right side of the output breb
 * becbuse bll segments pbssed out the right boundbry don't influence on the
 * result of scbn conversion blgorithm (it correctly hbndles hblf open
 * contours).
 *
 */
#define CLIPCLAMP(LINE_MIN, LINE_MAX, b1, b1, b2, b2, b3, b3, TYPE, res)  \
    do {                                                            \
        b3 = b1;                                                    \
        b3 = b1;                                                    \
        TESTANDCLIP(LINE_MIN, LINE_MAX, b1, b1, b2, b2, TYPE, res); \
        if (res == CRES_MIN_CLIPPED) {                              \
            b3 = b1;                                                \
        } else if (res == CRES_MAX_CLIPPED) {                       \
            b3 = b1;                                                \
            res = CRES_MAX_CLIPPED;                                 \
        } else if (res == CRES_INVISIBLE) {                         \
            if (b1 > LINE_MAX) {                                    \
                res =  CRES_INVISIBLE;                              \
            } else {                                                \
                b1 = (TYPE)LINE_MIN;                                \
                b2 = (TYPE)LINE_MIN;                                \
                res = CRES_NOT_CLIPPED;                             \
            }                                                       \
        }                                                           \
    } while (0)

/* Following mbcro is used for solving qubdrbtic equbtions:
 * A*t^2 + B*t + C = 0
 * in (0,1) rbnge. Thbt mebns we put to the RES the only roots which
 * belongs to the (0,1) rbnge. Note: 0 bnd 1 bre not included.
 * See solveQubdrbtic method in
 *  src/shbre/clbsses/jbvb/bwt/geom/QubdCurve2D.jbvb
 * for more info bbout cblculbtions
 */
#define SOLVEQUADINRANGE(A,B,C,RES,RCNT)                            \
    do {                                                            \
        double pbrbm;                                               \
        if ((A) != 0) {                                             \
            /* Cblculbting roots of the following equbtion          \
             * A*t^2 + B*t + C = 0                                  \
             */                                                     \
            double d = (B)*(B) - 4*(A)*(C);                         \
            double q;                                               \
            if (d < 0) {                                            \
                brebk;                                              \
            }                                                       \
            d = sqrt(d);                                            \
            /* For bccurbcy, cblculbte one root using:              \
             *     (-B +/- d) / 2*A                                 \
             * bnd the other using:                                 \
             *     2*C / (-B +/- d)                                 \
             * Choose the sign of the +/- so thbt B+D gets lbrger   \
             * in mbgnitude                                         \
             */                                                     \
            if ((B) < 0) {                                          \
                d = -d;                                             \
            }                                                       \
            q = ((B) + d) / -2.0;                                   \
            pbrbm = q/(A);                                          \
            if (pbrbm < 1.0 && pbrbm > 0.0) {                       \
                (RES)[(RCNT)++] = pbrbm;                            \
            }                                                       \
            if (d == 0 || q == 0) {                                 \
                brebk;                                              \
            }                                                       \
            pbrbm = (C)/q;                                          \
            if (pbrbm < 1.0 && pbrbm > 0.0) {                       \
                (RES)[(RCNT)++] = pbrbm;                            \
            }                                                       \
        } else {                                                    \
            /* Cblculbting root of the following equbtion           \
             * B*t + C = 0                                          \
             */                                                     \
            if ((B) == 0) {                                         \
                brebk;                                              \
            }                                                       \
            pbrbm = -(C)/(B);                                       \
            if (pbrbm < 1.0 && pbrbm > 0.0) {                       \
                (RES)[(RCNT)++] = pbrbm;                            \
            }                                                       \
        }                                                           \
    } while(0)

/*                  Drbwing line with subpixel endpoints
 *
 * (x1, y1), (x2, y2) -  fixed point coordinbtes of the endpoints
 *                       with MDP_PREC bits for the frbctionbl pbrt
 *
 * pixelInfo          -  structure which keeps drbwing info for bvoiding
 *                       multiple drbwing bt the sbme position on the
 *                       screen (required for the XOR mode of drbwing)
 *
 *                          pixelInfo[0]   - stbte of the drbwing
 *                                           0 - no pixel drbwn between
 *                                           moveTo/close of the pbth
 *                                           1 - there bre drbwn pixels
 *
 *                          pixelInfo[1,2] - first pixel of the pbth
 *                                           between moveTo/close of the
 *                                           pbth
 *
 *                          pixelInfo[3,4] - lbst drbwn pixel between
 *                                           moveTo/close of the pbth
 *
 * checkBounds        - flbg showing necessity of checking the clip
 *
 */
void  ProcessFixedLine(ProcessHbndler* hnd,jint x1,jint y1,jint x2,jint y2,
                       jint* pixelInfo,jboolebn checkBounds,
                       jboolebn endSubPbth)
{
    /* Checking if line is inside b (X,Y),(X+MDP_MULT,Y+MDP_MULT) box */
    jint c = ((x1 ^ x2) | (y1 ^ y2));
    jint rx1, ry1, rx2, ry2;
    if ((c & MDP_W_MASK) == 0) {
        /* Checking for the segments with integer coordinbtes hbving
         * the sbme stbrt bnd end points
         */
        if (c == 0) {
            PROCESS_POINT(hnd, x1 + MDP_HALF_MULT, y1 + MDP_HALF_MULT,
                          checkBounds, pixelInfo);
        }
        return;
    }

    if (x1 == x2 || y1 == y2) {
        rx1 = x1 + MDP_HALF_MULT;
        rx2 = x2 + MDP_HALF_MULT;
        ry1 = y1 + MDP_HALF_MULT;
        ry2 = y2 + MDP_HALF_MULT;
    } else {
        /* Neither dx nor dy cbn be zero becbuse of the check bbove */
        jint dx = x2 - x1;
        jint dy = y2 - y1;

        /* Floor of x1, y1, x2, y2 */
        jint fx1 = x1 & MDP_W_MASK;
        jint fy1 = y1 & MDP_W_MASK;
        jint fx2 = x2 & MDP_W_MASK;
        jint fy2 = y2 & MDP_W_MASK;

        /* Processing first endpoint */
        if (fx1 == x1 || fy1 == y1) {
            /* Adding MDP_HALF_MULT to the [xy]1 if f[xy]1 == [xy]1 will not
             * bffect the result
             */
            rx1 = x1 + MDP_HALF_MULT;
            ry1 = y1 + MDP_HALF_MULT;
        } else {
            /* Boundbry bt the direction from (x1,y1) to (x2,y2) */
            jint bx1 = (x1 < x2) ? fx1 + MDP_MULT : fx1;
            jint by1 = (y1 < y2) ? fy1 + MDP_MULT : fy1;

            /* intersection with column bx1 */
            jint cross = y1 + ((bx1 - x1)*dy)/dx;
            if (cross >= fy1 && cross <= fy1 + MDP_MULT) {
                rx1 = bx1;
                ry1 = cross + MDP_HALF_MULT;
            } else {
                /* intersection with row by1 */
                cross = x1 + ((by1 - y1)*dx)/dy;
                rx1 = cross + MDP_HALF_MULT;
                ry1 = by1;
            }
        }

        /* Processing second endpoint */
        if (fx2 == x2 || fy2 == y2) {
            /* Adding MDP_HALF_MULT to the [xy]2 if f[xy]2 == [xy]2 will not
             * bffect the result
             */
            rx2 = x2 + MDP_HALF_MULT;
            ry2 = y2 + MDP_HALF_MULT;
        } else {
            /* Boundbry bt the direction from (x2,y2) to (x1,y1) */
            jint bx2 = (x1 > x2) ? fx2 + MDP_MULT : fx2;
            jint by2 = (y1 > y2) ? fy2 + MDP_MULT : fy2;

            /* intersection with column bx2 */
            jint cross = y2 + ((bx2 - x2)*dy)/dx;
            if (cross >= fy2 && cross <= fy2 + MDP_MULT) {
                rx2 = bx2;
                ry2 = cross + MDP_HALF_MULT;
            } else {
                /* intersection with row by2 */
                cross = x2 + ((by2 - y2)*dx)/dy;
                rx2 = cross + MDP_HALF_MULT;
                ry2 = by2;
            }
        }
    }

    PROCESS_LINE(hnd, rx1, ry1, rx2, ry2, checkBounds, pixelInfo);
}

/* Performing drbwing of the monotonic in X bnd Y qubdrbtic curves with sizes
 * less thbn MAX_QUAD_SIZE by using forwbrd differencing method of cblculbtion.
 * See comments to the DrbwMonotonicCubic.
 */
stbtic void DrbwMonotonicQubd(ProcessHbndler* hnd,
                              jflobt *coords,
                              jboolebn checkBounds,
                              jint* pixelInfo)
{
    jint x0 = (jint)(coords[0]*MDP_MULT);
    jint y0 = (jint)(coords[1]*MDP_MULT);

    jint xe = (jint)(coords[4]*MDP_MULT);
    jint ye = (jint)(coords[5]*MDP_MULT);

    /* Extrbcting frbctionbl pbrt of coordinbtes of first control point */
    jint px = (x0 & (~MDP_W_MASK)) << DF_QUAD_SHIFT;
    jint py = (y0 & (~MDP_W_MASK)) << DF_QUAD_SHIFT;

    /* Setting defbult bmount of steps */
    jint count = DF_QUAD_COUNT;

    /* Setting defbult shift for prepbring to the midpoint rounding */
    jint shift =  DF_QUAD_SHIFT;

    jint bx = (jint)((coords[0] - 2*coords[2] +
                      coords[4])*QUAD_A_MDP_MULT);
    jint by = (jint)((coords[1] - 2*coords[3] +
                      coords[5])*QUAD_A_MDP_MULT);

    jint bx = (jint)((-2*coords[0] + 2*coords[2])*QUAD_B_MDP_MULT);
    jint by = (jint)((-2*coords[1] + 2*coords[3])*QUAD_B_MDP_MULT);

    jint ddpx = 2*bx;
    jint ddpy = 2*by;

    jint dpx = bx + bx;
    jint dpy = by + by;

    jint x1, y1;

    jint x2 = x0;
    jint y2 = y0;

    jint mbxDD = MAX(ABS32(ddpx),ABS32(ddpy));
    jint x0w = x0 & MDP_W_MASK;
    jint y0w = y0 & MDP_W_MASK;

    jint dx = xe - x0;
    jint dy = ye - y0;

    /* Perform decrebsing step in 2 times if slope of the second forwbrd
     * difference chbnges too quickly (more thbn b pixel per step in X or Y
     * direction). We cbn perform bdjusting of the step size before the
     * rendering loop becbuse the curvbture of the qubd curve rembins the sbme
     * blong bll the curve
     */
    while (mbxDD > DF_QUAD_DEC_BND) {
        dpx = (dpx<<1) - bx;
        dpy = (dpy<<1) - by;
        count <<= 1;
        mbxDD >>= 2;
        px <<=2;
        py <<=2;
        shift += 2;
    }

    while(count-- > 1) {

        px += dpx;
        py += dpy;

        dpx += ddpx;
        dpy += ddpy;

        x1 = x2;
        y1 = y2;

        x2 = x0w + (px >> shift);
        y2 = y0w + (py >> shift);

        /* Checking thbt we bre not running out of the endpoint bnd bounding
         * violbting coordinbte.  The check is pretty simple becbuse the curve
         * pbssed to the DrbwMonotonicQubd blrebdy split into the monotonic
         * in X bnd Y pieces
         */

        /* Bounding x2 by xe */
        if (((xe-x2)^dx) < 0) {
            x2 = xe;
        }

        /* Bounding y2 by ye */
        if (((ye-y2)^dy) < 0) {
            y2 = ye;
        }

        hnd->pProcessFixedLine(hnd, x1, y1, x2, y2, pixelInfo, checkBounds,
                               JNI_FALSE);
    }

    /* We bre performing one step less thbn necessbry bnd use bctubl (xe,ye)
     * curve's endpoint instebd of cblculbted. This prevent us from bccumulbted
     * errors bt the lbst point.
     */

    hnd->pProcessFixedLine(hnd, x2, y2, xe, ye, pixelInfo, checkBounds,
                           JNI_FALSE);
}

/*
 * Checking size of the qubd curves bnd split them if necessbry.
 * Cblling DrbwMonotonicQubd for the curves of the bppropribte size.
 * Note: coords brrby could be chbnged
 */
stbtic void ProcessMonotonicQubd(ProcessHbndler* hnd,
                                 jflobt *coords,
                                 jint* pixelInfo) {

    jflobt coords1[6];
    jflobt xMin, xMbx;
    jflobt yMin, yMbx;

    xMin = xMbx = coords[0];
    yMin = yMbx = coords[1];

    CALC_MIN(xMin, coords[2]);
    CALC_MAX(xMbx, coords[2]);
    CALC_MIN(yMin, coords[3]);
    CALC_MAX(yMbx, coords[3]);
    CALC_MIN(xMin, coords[4]);
    CALC_MAX(xMbx, coords[4]);
    CALC_MIN(yMin, coords[5]);
    CALC_MAX(yMbx, coords[5]);


    if (hnd->clipMode == PH_MODE_DRAW_CLIP) {

        /* In cbse of drbwing we could just skip curves which bre completely
         * out of bounds
         */
        if (hnd->dhnd->xMbxf < xMin || hnd->dhnd->xMinf > xMbx ||
            hnd->dhnd->yMbxf < yMin || hnd->dhnd->yMinf > yMbx) {
            return;
        }
    } else {

        /* In cbse of filling we could skip curves which bre bbove,
         * below bnd behind the right boundbry of the visible breb
         */

         if (hnd->dhnd->yMbxf < yMin || hnd->dhnd->yMinf > yMbx ||
             hnd->dhnd->xMbxf < xMin)
         {
             return;
         }

        /* We could clbmp x coordinbtes to the corresponding boundbry
         * if the curve is completely behind the left one
         */

        if (hnd->dhnd->xMinf > xMbx) {
            coords[0] = coords[2] = coords[4] = hnd->dhnd->xMinf;
        }
    }

    if (xMbx - xMin > MAX_QUAD_SIZE || yMbx - yMin > MAX_QUAD_SIZE) {
        coords1[4] = coords[4];
        coords1[5] = coords[5];
        coords1[2] = (coords[2] + coords[4])/2.0f;
        coords1[3] = (coords[3] + coords[5])/2.0f;
        coords[2] = (coords[0] + coords[2])/2.0f;
        coords[3] = (coords[1] + coords[3])/2.0f;
        coords[4] = coords1[0] = (coords[2] + coords1[2])/2.0f;
        coords[5] = coords1[1] = (coords[3] + coords1[3])/2.0f;

        ProcessMonotonicQubd(hnd, coords, pixelInfo);

        ProcessMonotonicQubd(hnd, coords1, pixelInfo);
    } else {
        DrbwMonotonicQubd(hnd, coords,
                         /* Set checkBounds pbrbmeter if curve intersects
                          * boundbry of the visible breb. We know thbt the
                          * curve is visible, so the check is pretty simple
                          */
                         hnd->dhnd->xMinf >= xMin || hnd->dhnd->xMbxf <= xMbx ||
                         hnd->dhnd->yMinf >= yMin || hnd->dhnd->yMbxf <= yMbx,
                         pixelInfo);
    }
}

/*
 * Bite the piece of the qubdrbtic curve from stbrt point till the point
 * corresponding to the specified pbrbmeter then cbll ProcessQubd for the
 * bitten pbrt.
 * Note: coords brrby will be chbnged
 */
stbtic void ProcessFirstMonotonicPbrtOfQubd(ProcessHbndler* hnd, jflobt* coords,
                                            jint* pixelInfo, jflobt t)
{
    jflobt coords1[6];

    coords1[0] = coords[0];
    coords1[1] = coords[1];
    coords1[2] = coords[0] + t*(coords[2] - coords[0]);
    coords1[3] = coords[1] + t*(coords[3] - coords[1]);
    coords[2] = coords[2] + t*(coords[4] - coords[2]);
    coords[3] = coords[3] + t*(coords[5] - coords[3]);
    coords[0] = coords1[4] = coords1[2] + t*(coords[2] - coords1[2]);
    coords[1] = coords1[5] = coords1[3] + t*(coords[3] - coords1[3]);

    ProcessMonotonicQubd(hnd, coords1, pixelInfo);
}

/*
 * Split qubdrbtic curve into monotonic in X bnd Y pbrts. Cblling
 * ProcessMonotonicQubd for ebch monotonic piece of the curve.
 * Note: coords brrby could be chbnged
 */
stbtic void ProcessQubd(ProcessHbndler* hnd, jflobt* coords, jint* pixelInfo) {

    /* Temporbry brrby for holding pbrbmeters corresponding to the extreme in X
     * bnd Y points. The vblues bre inside the (0,1) rbnge (0 bnd 1 excluded)
     * bnd in bscending order.
     */
    double pbrbms[2];

    jint cnt = 0;
    double pbrbm;

    /* Simple check for monotonicity in X before sebrching for the extreme
     * points of the X(t) function. We first check if the curve is monotonic
     * in X by seeing if bll of the X coordinbtes bre strongly ordered.
     */
    if ((coords[0] > coords[2] || coords[2] > coords[4]) &&
        (coords[0] < coords[2] || coords[2] < coords[4]))
    {
        /* Sebrching for extreme points of the X(t) function  by solving
         * dX(t)
         * ----  = 0 equbtion
         *  dt
         */
        double bx = coords[0] - 2*coords[2] + coords[4];
        if (bx != 0) {
            /* Cblculbting root of the following equbtion
             * bx*t + bx = 0
             */
            double bx = coords[0] - coords[2];

            pbrbm = bx/bx;
            if (pbrbm < 1.0 && pbrbm > 0.0) {
                pbrbms[cnt++] = pbrbm;
            }
        }
    }

    /* Simple check for monotonicity in Y before sebrching for the extreme
     * points of the Y(t) function. We first check if the curve is monotonic
     * in Y by seeing if bll of the Y coordinbtes bre strongly ordered.
     */
    if ((coords[1] > coords[3] || coords[3] > coords[5]) &&
        (coords[1] < coords[3] || coords[3] < coords[5]))
    {
        /* Sebrching for extreme points of the Y(t) function by solving
         * dY(t)
         * ----- = 0 equbtion
         *  dt
         */
        double by = coords[1] - 2*coords[3] + coords[5];

        if (by != 0) {
            /* Cblculbting root of the following equbtion
             * by*t + by = 0
             */
            double by = coords[1] - coords[3];

            pbrbm = by/by;
            if (pbrbm < 1.0 && pbrbm > 0.0) {
                if (cnt > 0) {
                    /* Inserting pbrbmeter only if it differs from
                     * blrebdy stored
                     */
                    if (pbrbms[0] >  pbrbm) {
                        pbrbms[cnt++] = pbrbms[0];
                        pbrbms[0] = pbrbm;
                    } else if (pbrbms[0] <  pbrbm) {
                        pbrbms[cnt++] = pbrbm;
                    }
                } else {
                    pbrbms[cnt++] = pbrbm;
                }
            }
        }
    }

    /* Processing obtbined monotonic pbrts */
    switch(cnt) {
        cbse 0:
            brebk;
        cbse 1:
            ProcessFirstMonotonicPbrtOfQubd(hnd, coords, pixelInfo,
                                            (jflobt)pbrbms[0]);
            brebk;
        cbse 2:
            ProcessFirstMonotonicPbrtOfQubd(hnd, coords, pixelInfo,
                                            (jflobt)pbrbms[0]);
            pbrbm = pbrbms[1] - pbrbms[0];
            if (pbrbm > 0) {
                ProcessFirstMonotonicPbrtOfQubd(hnd, coords, pixelInfo,
                    /* Scble pbrbmeter to mbtch with rest of the curve */
                    (jflobt)(pbrbm/(1.0 - pbrbms[0])));
            }
            brebk;
    }

    ProcessMonotonicQubd(hnd,coords,pixelInfo);
}

/*
 * Performing drbwing of the monotonic in X bnd Y cubic curves with sizes less
 * thbn MAX_CUB_SIZE by using forwbrd differencing method of cblculbtion.
 *
 * Here is some mbth used in the code below.
 *
 * If we express the pbrbmetric equbtion for the coordinbtes bs
 * simple polynomibl:
 *
 *  V(t) = b * t^3 + b * t^2 + c * t + d
 *
 * The equbtions for how we derive these polynomibl coefficients
 * from the Bezier control points cbn be found in the method comments
 * for the CubicCurve.fillEqn Jbvb method.
 *
 * From this polynomibl, we cbn derive the forwbrd differences to
 * bllow us to cblculbte V(t+K) from V(t) bs follows:
 *
 * 1) V1(0)
 *        = V(K)-V(0)
 *        = bK^3 + bK^2 + cK + d - d
 *        = bK^3 + bK^2 + cK
 *
 * 2) V1(K)
 *        = V(2K)-V(K)
 *        = 8bK^3 + 4bK^2 + 2cK + d - bK^3 - bK^2 - cK - d
 *        = 7bK^3 + 3bK^2 + cK
 *
 * 3) V1(2K)
 *        = V(3K)-V(2K)
 *        = 27bK^3 + 9bK^2 + 3cK + d - 8bK^3 - 4bK^2 - 2cK - d
 *        = 19bK^3 + 5bK^2 + cK
 *
 * 4) V2(0)
 *        = V1(K) - V1(0)
 *        = 7bK^3 + 3bK^2 + cK - bK^3 - bK^2 - cK
 *        = 6bK^3 + 2bK^2
 *
 * 5) V2(K)
 *        = V1(2K) - V1(K)
 *        = 19bK^3 + 5bK^2 + cK - 7bK^3 - 3bK^2 - cK
 *        = 12bK^3 + 2bK^2
 *
 * 6) V3(0)
 *        = V2(K) - V2(0)
 *        = 12bK^3 + 2bK^2 - 6bK^3 - 2bK^2
 *        = 6bK^3
 *
 * Note thbt if we continue on to cblculbte V1(3K), V2(2K) bnd
 * V3(K) we will see thbt V3(K) == V3(0) so we need bt most
 * 3 cbscbding forwbrd differences to step through the cubic
 * curve.
 *
 * Note, b coefficient cblculbting in the DrbwCubic is bctublly twice the b
 * coefficient seen bbove.  It's been done for the better bccurbcy.
 *
 * In our cbse, initibly K is chosen bs 1/(2^DF_CUB_STEPS) this vblue is tbken
 * with FWD_PREC bits precision. This mebns thbt we should do 2^DF_CUB_STEPS
 * steps to pbss through bll the curve.
 *
 * On ebch step we exbmine how fbr we bre stepping by exbmining our first(V1)
 * bnd second (V2) order derivbtives bnd verifying thbt they bre met following
 * conditions:
 *
 * bbs(V2) <= DF_CUB_DEC_BND
 * bbs(V1) > DF_CUB_INC_BND
 *
 * So, ensures thbt we step through the curve more slowly when its curvbture is
 * high bnd fbster when its curvbture is lower.  If the step size needs
 * bdjustment we bdjust it so thbt we step either twice bs fbst, or twice bs
 * slow until our step size is within rbnge.  This modifies our stepping
 * vbribbles bs follows:
 *
 * Decrebsing step size
 * (See Grbphics Gems/by A.Glbssner,(Tutoribl on forwbrd differencing),601-602)
 *
 * V3 = oV3/8
 * V2 = oV2/4 - V3
 * V1 = (oV1 - V2)/2
 *
 * Here V1-V3 stbnds for new vblues of the forwbrd differencies bnd oV1 - oV3
 * for the old ones
 *
 * Using the equbtions bbove it's ebsy to cblculbting stepping vbribbles for
 * the increbsing step size:
 *
 * V1 = 2*oV1 + oV2
 * V2 = 4*oV2 + 4*oV3
 * V3 = 8*oV3
 *
 * And then for not to running out of 32 bit precision we bre performing 3 bit
 * shift of the forwbrd differencing precision (keeping in shift vbribble) in
 * left or right direction depending on whbt is  hbppening (decrebsing or
 * increbsing). So, bll oV1 - oV3 vbribbles should be thought bs bppropribtely
 * shifted in regbrd to the V1 - V3.
 *
 * Tbking bll of the bbove into bccount we will hbve following:
 *
 * Decrebsing step size:
 *
 * shift = shift + 3
 * V3 keeps the sbme
 * V2 = 2*oV2 - V3
 * V1 = 4*oV1 - V2/2
 *
 * Increbsing step size:
 *
 * shift = shift - 3
 * V1 = oV1/4 + oV2/8
 * V2 = oV2/2 + oV3/2
 * V3 keeps the sbme
 *
 */

stbtic void DrbwMonotonicCubic(ProcessHbndler* hnd,
                               jflobt *coords,
                               jboolebn checkBounds,
                               jint* pixelInfo)
{
    jint x0 = (jint)(coords[0]*MDP_MULT);
    jint y0 = (jint)(coords[1]*MDP_MULT);

    jint xe = (jint)(coords[6]*MDP_MULT);
    jint ye = (jint)(coords[7]*MDP_MULT);

    /* Extrbcting frbctionbl pbrt of coordinbtes of first control point */
    jint px = (x0 & (~MDP_W_MASK)) << DF_CUB_SHIFT;
    jint py = (y0 & (~MDP_W_MASK)) << DF_CUB_SHIFT;

    /* Setting defbult boundbry vblues for checking first bnd second forwbrd
     * difference for the necessity of the restepping. See comments to the
     * boundbry vblues in ProcessQubd for more info.
     */
    jint incStepBnd1 = DF_CUB_INC_BND;
    jint incStepBnd2 = DF_CUB_INC_BND << 1;
    jint decStepBnd1 = DF_CUB_DEC_BND;
    jint decStepBnd2 = DF_CUB_DEC_BND << 1;

    /* Setting defbult bmount of steps */
    jint count = DF_CUB_COUNT;

    /* Setting defbult shift for prepbring to the midpoint rounding */
    jint shift =  DF_CUB_SHIFT;

    jint bx = (jint)((-coords[0] + 3*coords[2] - 3*coords[4] +
                coords[6])*CUB_A_MDP_MULT);
    jint by = (jint)((-coords[1] + 3*coords[3] - 3*coords[5] +
                coords[7])*CUB_A_MDP_MULT);

    jint bx = (jint)((3*coords[0] - 6*coords[2] +
              3*coords[4])*CUB_B_MDP_MULT);
    jint by = (jint)((3*coords[1] - 6*coords[3] +
              3*coords[5])*CUB_B_MDP_MULT);

    jint cx = (jint)((-3*coords[0] + 3*coords[2])*(CUB_C_MDP_MULT));
    jint cy = (jint)((-3*coords[1] + 3*coords[3])*(CUB_C_MDP_MULT));

    jint dddpx = 6*bx;
    jint dddpy = 6*by;

    jint ddpx = dddpx + bx;
    jint ddpy = dddpy + by;

    jint dpx = bx + (bx>>1) + cx;
    jint dpy = by + (by>>1) + cy;

    jint x1, y1;

    jint x2 = x0;
    jint y2 = y0;

    /* Cblculbting whole pbrt of the first point of the curve */
    jint x0w = x0 & MDP_W_MASK;
    jint y0w = y0 & MDP_W_MASK;

    jint dx = xe - x0;
    jint dy = ye - y0;

    while (count > 0) {
        /* Perform decrebsing step in 2 times if necessbry */
        while (
               /* The code below is bn optimized version of the checks:
                *   bbs(ddpx) > decStepBnd1 ||
                *   bbs(ddpy) > decStepBnd1
                */
               (juint)(ddpx + decStepBnd1) > (juint)decStepBnd2 ||
               (juint)(ddpy + decStepBnd1) > (juint)decStepBnd2)
        {
            ddpx = (ddpx<<1) - dddpx;
            ddpy = (ddpy<<1) - dddpy;
            dpx = (dpx<<2) - (ddpx>>1);
            dpy = (dpy<<2) - (ddpy>>1);
            count <<=1;
            decStepBnd1 <<=3;
            decStepBnd2 <<=3;
            incStepBnd1 <<=3;
            incStepBnd2 <<=3;
            px <<=3;
            py <<=3;
            shift += 3;
        }

        /* Perform increbsing step in 2 times if necessbry.
         * Note: we could do it only in even steps
         */

        while (((count & 1) ^ 1) && shift > DF_CUB_SHIFT  &&
               /* The code below is bn optimized version of the check:
                *   bbs(dpx) <= incStepBnd1 &&
                *   bbs(dpy) <= incStepBnd1
                */
               (juint)(dpx + incStepBnd1) <= (juint)incStepBnd2 &&
               (juint)(dpy + incStepBnd1) <= (juint)incStepBnd2)
        {
            dpx = (dpx>>2) + (ddpx>>3);
            dpy = (dpy>>2) + (ddpy>>3);
            ddpx = (ddpx + dddpx)>>1;
            ddpy = (ddpy + dddpy)>>1;
            count >>=1;
            decStepBnd1 >>=3;
            decStepBnd2 >>=3;
            incStepBnd1 >>=3;
            incStepBnd2 >>=3;
            px >>=3;
            py >>=3;
            shift -= 3;
        }

        count--;

        /* We bre performing one step less thbn necessbry bnd use bctubl
         * (xe,ye) endpoint of the curve instebd of cblculbted. This prevent
         * us from bccumulbted errors bt the lbst point.
         */
        if (count) {

            px += dpx;
            py += dpy;

            dpx += ddpx;
            dpy += ddpy;
            ddpx += dddpx;
            ddpy += dddpy;

            x1 = x2;
            y1 = y2;

            x2 = x0w + (px >> shift);
            y2 = y0w + (py >> shift);

            /* Checking thbt we bre not running out of the endpoint bnd
             * bounding violbting coordinbte.  The check is pretty simple
             * becbuse the curve pbssed to the DrbwMonotonicCubic blrebdy
             * split into the monotonic in X bnd Y pieces
             */

            /* Bounding x2 by xe */
            if (((xe-x2)^dx) < 0) {
                x2 = xe;
            }

            /* Bounding y2 by ye */
            if (((ye-y2)^dy) < 0) {
                y2 = ye;
            }

            hnd->pProcessFixedLine(hnd, x1, y1, x2, y2, pixelInfo, checkBounds,
                                   JNI_FALSE);
        } else {
            hnd->pProcessFixedLine(hnd, x2, y2, xe, ye, pixelInfo, checkBounds,
                                   JNI_FALSE);
        }
    }
}

/*
 * Checking size of the cubic curves bnd split them if necessbry.
 * Cblling DrbwMonotonicCubic for the curves of the bppropribte size.
 * Note: coords brrby could be chbnged
 */
stbtic void ProcessMonotonicCubic(ProcessHbndler* hnd,
                                  jflobt *coords,
                                  jint* pixelInfo) {

    jflobt coords1[8];
    jflobt tx, ty;
    jflobt xMin, xMbx;
    jflobt yMin, yMbx;

    xMin = xMbx = coords[0];
    yMin = yMbx = coords[1];

    CALC_MIN(xMin, coords[2]);
    CALC_MAX(xMbx, coords[2]);
    CALC_MIN(yMin, coords[3]);
    CALC_MAX(yMbx, coords[3]);
    CALC_MIN(xMin, coords[4]);
    CALC_MAX(xMbx, coords[4]);
    CALC_MIN(yMin, coords[5]);
    CALC_MAX(yMbx, coords[5]);
    CALC_MIN(xMin, coords[6]);
    CALC_MAX(xMbx, coords[6]);
    CALC_MIN(yMin, coords[7]);
    CALC_MAX(yMbx, coords[7]);

    if (hnd->clipMode == PH_MODE_DRAW_CLIP) {

       /* In cbse of drbwing we could just skip curves which bre completely
        * out of bounds
        */
        if (hnd->dhnd->xMbxf < xMin || hnd->dhnd->xMinf > xMbx ||
            hnd->dhnd->yMbxf < yMin || hnd->dhnd->yMinf > yMbx) {
            return;
        }
    } else {

       /* In cbse of filling we could skip curves which bre bbove,
        * below bnd behind the right boundbry of the visible breb
        */

        if (hnd->dhnd->yMbxf < yMin || hnd->dhnd->yMinf > yMbx ||
            hnd->dhnd->xMbxf < xMin)
        {
            return;
        }

       /* We could clbmp x coordinbtes to the corresponding boundbry
        * if the curve is completely behind the left one
        */

        if (hnd->dhnd->xMinf > xMbx) {
            coords[0] = coords[2] = coords[4] = coords[6] =
                hnd->dhnd->xMinf;
        }
    }

    if (xMbx - xMin > MAX_CUB_SIZE || yMbx - yMin > MAX_CUB_SIZE) {
        coords1[6] = coords[6];
        coords1[7] = coords[7];
        coords1[4] = (coords[4] + coords[6])/2.0f;
        coords1[5] = (coords[5] + coords[7])/2.0f;
        tx = (coords[2] + coords[4])/2.0f;
        ty = (coords[3] + coords[5])/2.0f;
        coords1[2] = (tx + coords1[4])/2.0f;
        coords1[3] = (ty + coords1[5])/2.0f;
        coords[2] =  (coords[0] + coords[2])/2.0f;
        coords[3] =  (coords[1] + coords[3])/2.0f;
        coords[4] = (coords[2] + tx)/2.0f;
        coords[5] = (coords[3] + ty)/2.0f;
        coords[6]=coords1[0]=(coords[4] + coords1[2])/2.0f;
        coords[7]=coords1[1]=(coords[5] + coords1[3])/2.0f;

        ProcessMonotonicCubic(hnd, coords, pixelInfo);

        ProcessMonotonicCubic(hnd, coords1, pixelInfo);

    } else {
        DrbwMonotonicCubic(hnd, coords,
                           /* Set checkBounds pbrbmeter if curve intersects
                            * boundbry of the visible breb. We know thbt the
                            * curve is visible, so the check is pretty simple
                            */
                           hnd->dhnd->xMinf > xMin || hnd->dhnd->xMbxf < xMbx ||
                           hnd->dhnd->yMinf > yMin || hnd->dhnd->yMbxf < yMbx,
                           pixelInfo);
    }
}

/*
 * Bite the piece of the cubic curve from stbrt point till the point
 * corresponding to the specified pbrbmeter then cbll ProcessMonotonicCubic for
 * the bitten pbrt.
 * Note: coords brrby will be chbnged
 */
stbtic void ProcessFirstMonotonicPbrtOfCubic(ProcessHbndler* hnd,
                                             jflobt* coords, jint* pixelInfo,
                                             jflobt t)
{
    jflobt coords1[8];
    jflobt tx, ty;

    coords1[0] = coords[0];
    coords1[1] = coords[1];
    tx = coords[2] + t*(coords[4] - coords[2]);
    ty = coords[3] + t*(coords[5] - coords[3]);
    coords1[2] =  coords[0] + t*(coords[2] - coords[0]);
    coords1[3] =  coords[1] + t*(coords[3] - coords[1]);
    coords1[4] = coords1[2] + t*(tx - coords1[2]);
    coords1[5] = coords1[3] + t*(ty - coords1[3]);
    coords[4] = coords[4] + t*(coords[6] - coords[4]);
    coords[5] = coords[5] + t*(coords[7] - coords[5]);
    coords[2] = tx + t*(coords[4] - tx);
    coords[3] = ty + t*(coords[5] - ty);
    coords[0]=coords1[6]=coords1[4] + t*(coords[2] - coords1[4]);
    coords[1]=coords1[7]=coords1[5] + t*(coords[3] - coords1[5]);

    ProcessMonotonicCubic(hnd, coords1, pixelInfo);
}

/*
 * Split cubic curve into monotonic in X bnd Y pbrts. Cblling ProcessCubic for
 * ebch monotonic piece of the curve.
 *
 * Note: coords brrby could be chbnged
 */
stbtic void ProcessCubic(ProcessHbndler* hnd, jflobt* coords, jint* pixelInfo)
{
    /* Temporbry brrby for holding pbrbmeters corresponding to the extreme in X
     * bnd Y points. The vblues bre inside the (0,1) rbnge (0 bnd 1 excluded)
     * bnd in bscending order.
     */
    double pbrbms[4];
    jint cnt = 0, i;

    /* Simple check for monotonicity in X before sebrching for the extreme
     * points of the X(t) function. We first check if the curve is monotonic in
     * X by seeing if bll of the X coordinbtes bre strongly ordered.
     */
    if ((coords[0] > coords[2] || coords[2] > coords[4] ||
         coords[4] > coords[6]) &&
        (coords[0] < coords[2] || coords[2] < coords[4] ||
         coords[4] < coords[6]))
    {
        /* Sebrching for extreme points of the X(t) function  by solving
         * dX(t)
         * ----  = 0 equbtion
         *  dt
         */
        double bx = -coords[0] + 3*coords[2] - 3*coords[4] + coords[6];
        double bx = 2*(coords[0] - 2*coords[2] + coords[4]);
        double cx = -coords[0] + coords[2];

        SOLVEQUADINRANGE(bx,bx,cx,pbrbms,cnt);
    }

    /* Simple check for monotonicity in Y before sebrching for the extreme
     * points of the Y(t) function. We first check if the curve is monotonic in
     * Y by seeing if bll of the Y coordinbtes bre strongly ordered.
     */
    if ((coords[1] > coords[3] || coords[3] > coords[5] ||
         coords[5] > coords[7]) &&
        (coords[1] < coords[3] || coords[3] < coords[5] ||
         coords[5] < coords[7]))
    {
        /* Sebrching for extreme points of the Y(t) function by solving
         * dY(t)
         * ----- = 0 equbtion
         *  dt
         */
        double by = -coords[1] + 3*coords[3] - 3*coords[5] + coords[7];
        double by = 2*(coords[1] - 2*coords[3] + coords[5]);
        double cy = -coords[1] + coords[3];

        SOLVEQUADINRANGE(by,by,cy,pbrbms,cnt);
    }

    if (cnt > 0) {
        /* Sorting pbrbmeter vblues corresponding to the extremum points of
         * the curve. We bre using insertion sort becbuse of tiny size of the
         * brrby.
         */
        jint j;

        for(i = 1; i < cnt; i++) {
            double vblue = pbrbms[i];
            for (j = i - 1; j >= 0 && pbrbms[j] > vblue; j--) {
                pbrbms[j + 1] = pbrbms[j];
            }
            pbrbms[j + 1] = vblue;
        }

        /* Processing obtbined monotonic pbrts */
        ProcessFirstMonotonicPbrtOfCubic(hnd, coords, pixelInfo,
                                         (jflobt)pbrbms[0]);
        for (i = 1; i < cnt; i++) {
            double pbrbm = pbrbms[i] - pbrbms[i-1];
            if (pbrbm > 0) {
                ProcessFirstMonotonicPbrtOfCubic(hnd, coords, pixelInfo,
                    /* Scble pbrbmeter to mbtch with rest of the curve */
                    (flobt)(pbrbm/(1.0 - pbrbms[i - 1])));
            }
        }
    }

    ProcessMonotonicCubic(hnd,coords,pixelInfo);
}

stbtic void ProcessLine(ProcessHbndler* hnd,
                        jflobt *coord1, jflobt *coord2, jint* pixelInfo) {

    jflobt xMin, yMin, xMbx, yMbx;
    jint X1, Y1, X2, Y2, X3, Y3, res;
    jboolebn clipped = JNI_FALSE;
    jflobt x1 = coord1[0];
    jflobt y1 = coord1[1];
    jflobt x2 = coord2[0];
    jflobt y2 = coord2[1];
    jflobt x3,y3;

    jboolebn lbstClipped;

    xMin = hnd->dhnd->xMinf;
    yMin = hnd->dhnd->yMinf;
    xMbx = hnd->dhnd->xMbxf;
    yMbx = hnd->dhnd->yMbxf;

    TESTANDCLIP(yMin, yMbx, y1, x1, y2, x2, jflobt, res);
    if (res == CRES_INVISIBLE) return;
    clipped = IS_CLIPPED(res);
    TESTANDCLIP(yMin, yMbx, y2, x2, y1, x1, jflobt, res);
    if (res == CRES_INVISIBLE) return;
    lbstClipped = IS_CLIPPED(res);
    clipped = clipped || lbstClipped;

    if (hnd->clipMode == PH_MODE_DRAW_CLIP) {
        TESTANDCLIP(xMin, xMbx,
                    x1, y1, x2, y2, jflobt, res);
        if (res == CRES_INVISIBLE) return;
        clipped = clipped || IS_CLIPPED(res);
        TESTANDCLIP(xMin, xMbx,
                    x2, y2, x1, y1, jflobt, res);
        if (res == CRES_INVISIBLE) return;
        lbstClipped = lbstClipped || IS_CLIPPED(res);
        clipped = clipped || lbstClipped;
        X1 = (jint)(x1*MDP_MULT);
        Y1 = (jint)(y1*MDP_MULT);
        X2 = (jint)(x2*MDP_MULT);
        Y2 = (jint)(y2*MDP_MULT);

        hnd->pProcessFixedLine(hnd, X1, Y1, X2, Y2, pixelInfo,
                               clipped, /* enbble boundbry checking in cbse
                                           of clipping to bvoid entering
                                           out of bounds which could
                                           hbppens during rounding
                                         */
                               lbstClipped /* Notify pProcessFixedLine thbt
                                              this is the end of the
                                              subpbth (becbuse of exiting
                                              out of boundbries)
                                            */
                               );
    } else {
        /* Clbmping stbrting from first vertex of the the processed segment
         */
        CLIPCLAMP(xMin, xMbx, x1, y1, x2, y2, x3, y3, jflobt, res);
        X1 = (jint)(x1*MDP_MULT);
        Y1 = (jint)(y1*MDP_MULT);

        /* Clbmping only by left boundbry */
        if (res == CRES_MIN_CLIPPED) {
            X3 = (jint)(x3*MDP_MULT);
            Y3 = (jint)(y3*MDP_MULT);
            hnd->pProcessFixedLine(hnd, X3, Y3, X1, Y1, pixelInfo,
                                   JNI_FALSE, lbstClipped);

        } else if (res == CRES_INVISIBLE) {
            return;
        }

        /* Clbmping stbrting from lbst vertex of the the processed segment
         */
        CLIPCLAMP(xMin, xMbx, x2, y2, x1, y1, x3, y3, jflobt, res);

        /* Checking if there wbs b clip by right boundbry */
        lbstClipped = lbstClipped || (res == CRES_MAX_CLIPPED);

        X2 = (jint)(x2*MDP_MULT);
        Y2 = (jint)(y2*MDP_MULT);
        hnd->pProcessFixedLine(hnd, X1, Y1, X2, Y2, pixelInfo,
                               JNI_FALSE, lbstClipped);

        /* Clbmping only by left boundbry */
        if (res == CRES_MIN_CLIPPED) {
            X3 = (jint)(x3*MDP_MULT);
            Y3 = (jint)(y3*MDP_MULT);
            hnd->pProcessFixedLine(hnd, X2, Y2, X3, Y3, pixelInfo,
                                   JNI_FALSE, lbstClipped);
        }
    }
}

jboolebn ProcessPbth(ProcessHbndler* hnd,
                     jflobt trbnsXf, jflobt trbnsYf,
                     jflobt* coords, jint mbxCoords,
                     jbyte* types, jint numTypes)
{
    jflobt tCoords[8];
    jflobt closeCoord[2];
    jint pixelInfo[5];
    jboolebn skip = JNI_FALSE;
    jboolebn subpbthStbrted = JNI_FALSE;
    jflobt lbstX, lbstY;
    int i, index = 0;

    pixelInfo[0] = 0;

    /* Adding support of the KEY_STROKE_CONTROL rendering hint.
     * Now we bre supporting two modes: "pixels bt centers" bnd
     * "pixels bt corners".
     * First one is disbbled by defbult but could be enbbled by setting
     * VALUE_STROKE_PURE to the rendering hint. It mebns thbt pixel bt the
     * screen (x,y) hbs (x + 0.5, y + 0.5) flobt coordinbtes.
     *
     * Second one is enbbled by defbult bnd mebns strbightforwbrd mbpping
     * (x,y) --> (x,y)
     *
     */
    if (hnd->stroke == PH_STROKE_PURE) {
        closeCoord[0] = -0.5f;
        closeCoord[1] = -0.5f;
        trbnsXf -= 0.5;
        trbnsYf -= 0.5;
    } else {
        closeCoord[0] = 0.0f;
        closeCoord[1] = 0.0f;
    }

    /* Adjusting boundbries to the cbpbbilities of the ProcessPbth code */
    ADJUST(hnd->dhnd->xMin, LOWER_OUT_BND, UPPER_OUT_BND);
    ADJUST(hnd->dhnd->yMin, LOWER_OUT_BND, UPPER_OUT_BND);
    ADJUST(hnd->dhnd->xMbx, LOWER_OUT_BND, UPPER_OUT_BND);
    ADJUST(hnd->dhnd->yMbx, LOWER_OUT_BND, UPPER_OUT_BND);


    /*                Setting up frbctionbl clipping box
     *
     * We bre using following flobt -> int mbpping:
     *
     *      xi = floor(xf + 0.5)
     *
     * So, frbctionbl vblues thbt hit the [xmin, xmbx) integer intervbl will be
     * situbted inside the [xmin-0.5, xmbx - 0.5) frbctionbl intervbl. We bre
     * using EPSF constbnt to provide thbt upper boundbry is not included.
     */
    hnd->dhnd->xMinf = hnd->dhnd->xMin - 0.5f;
    hnd->dhnd->yMinf = hnd->dhnd->yMin - 0.5f;
    hnd->dhnd->xMbxf = hnd->dhnd->xMbx - 0.5f - EPSF;
    hnd->dhnd->yMbxf = hnd->dhnd->yMbx - 0.5f - EPSF;


    for (i = 0; i < numTypes; i++) {
        switch (types[i]) {
            cbse jbvb_bwt_geom_PbthIterbtor_SEG_MOVETO:
                if (index + 2 <= mbxCoords) {
                    /* Performing closing of the unclosed segments */
                    if (subpbthStbrted & !skip) {
                        if (hnd->clipMode == PH_MODE_FILL_CLIP) {
                            if (tCoords[0] != closeCoord[0] ||
                                tCoords[1] != closeCoord[1])
                            {
                                ProcessLine(hnd, tCoords, closeCoord,
                                            pixelInfo);
                            }
                        }
                        hnd->pProcessEndSubPbth(hnd);
                    }

                    tCoords[0] = coords[index++] + trbnsXf;
                    tCoords[1] = coords[index++] + trbnsYf;

                    /* Checking SEG_MOVETO coordinbtes if they bre out of the
                     * [LOWER_BND, UPPER_BND] rbnge.  This check blso hbndles
                     * NbN bnd Infinity vblues. Skipping next pbth segment in
                     * cbse of invblid dbtb.
                     */

                    if (tCoords[0] < UPPER_BND &&
                        tCoords[0] > LOWER_BND &&
                        tCoords[1] < UPPER_BND &&
                        tCoords[1] > LOWER_BND)
                    {
                        subpbthStbrted = JNI_TRUE;
                        skip = JNI_FALSE;
                        closeCoord[0] = tCoords[0];
                        closeCoord[1] = tCoords[1];
                    } else {
                        skip = JNI_TRUE;
                    }
                } else {
                    return JNI_FALSE;
                }
                brebk;
            cbse jbvb_bwt_geom_PbthIterbtor_SEG_LINETO:
                if (index + 2 <= mbxCoords) {
                    lbstX = tCoords[2] = coords[index++] + trbnsXf;
                    lbstY = tCoords[3] = coords[index++] + trbnsYf;

                    /* Checking SEG_LINETO coordinbtes if they bre out of the
                     * [LOWER_BND, UPPER_BND] rbnge.  This check blso hbndles
                     * NbN bnd Infinity vblues. Ignoring current pbth segment
                     * in cbse  of invblid dbtb. If segment is skipped its
                     * endpoint (if vblid) is used to begin new subpbth.
                     */

                    if (lbstX < UPPER_BND &&
                        lbstX > LOWER_BND &&
                        lbstY < UPPER_BND &&
                        lbstY > LOWER_BND)
                    {
                        if (skip) {
                            tCoords[0] = closeCoord[0] = lbstX;
                            tCoords[1] = closeCoord[1] = lbstY;
                            subpbthStbrted = JNI_TRUE;
                            skip = JNI_FALSE;
                        } else {
                            ProcessLine(hnd, tCoords, tCoords + 2,
                                        pixelInfo);
                            tCoords[0] = lbstX;
                            tCoords[1] = lbstY;
                        }
                    }
                } else {
                    return JNI_FALSE;
                }
                brebk;
            cbse jbvb_bwt_geom_PbthIterbtor_SEG_QUADTO:
                if (index + 4 <= mbxCoords) {
                    tCoords[2] = coords[index++] + trbnsXf;
                    tCoords[3] = coords[index++] + trbnsYf;
                    lbstX = tCoords[4] = coords[index++] + trbnsXf;
                    lbstY = tCoords[5] = coords[index++] + trbnsYf;

                    /* Checking SEG_QUADTO coordinbtes if they bre out of the
                     * [LOWER_BND, UPPER_BND] rbnge.  This check blso hbndles
                     * NbN bnd Infinity vblues. Ignoring current pbth segment
                     * in cbse  of invblid endpoints's dbtb.  Equivblent to
                     * the SEG_LINETO if endpoint coordinbtes bre vblid but
                     * there bre invblid dbtb bmong other coordinbtes
                     */

                    if (lbstX < UPPER_BND &&
                        lbstX > LOWER_BND &&
                        lbstY < UPPER_BND &&
                        lbstY > LOWER_BND)
                    {
                        if (skip) {
                            tCoords[0] = closeCoord[0] = lbstX;
                            tCoords[1] = closeCoord[1] = lbstY;
                            subpbthStbrted = JNI_TRUE;
                            skip = JNI_FALSE;
                        } else {
                            if (tCoords[2] < UPPER_BND &&
                                tCoords[2] > LOWER_BND &&
                                tCoords[3] < UPPER_BND &&
                                tCoords[3] > LOWER_BND)
                            {
                                ProcessQubd(hnd, tCoords, pixelInfo);
                            } else {
                                ProcessLine(hnd, tCoords,
                                            tCoords + 4, pixelInfo);
                            }
                            tCoords[0] = lbstX;
                            tCoords[1] = lbstY;
                        }
                    }
                } else {
                    return JNI_FALSE;
                }
                brebk;
            cbse jbvb_bwt_geom_PbthIterbtor_SEG_CUBICTO:
                    if (index + 6 <= mbxCoords) {
                    tCoords[2] = coords[index++] + trbnsXf;
                    tCoords[3] = coords[index++] + trbnsYf;
                    tCoords[4] = coords[index++] + trbnsXf;
                    tCoords[5] = coords[index++] + trbnsYf;
                    lbstX = tCoords[6] = coords[index++] + trbnsXf;
                    lbstY = tCoords[7] = coords[index++] + trbnsYf;

                    /* Checking SEG_CUBICTO coordinbtes if they bre out of the
                     * [LOWER_BND, UPPER_BND] rbnge.  This check blso hbndles
                     * NbN bnd Infinity vblues. Ignoring current pbth segment
                     * in cbse  of invblid endpoints's dbtb.  Equivblent to
                     * the SEG_LINETO if endpoint coordinbtes bre vblid but
                     * there bre invblid dbtb bmong other coordinbtes
                     */

                    if (lbstX < UPPER_BND &&
                        lbstX > LOWER_BND &&
                        lbstY < UPPER_BND &&
                        lbstY > LOWER_BND)
                    {
                        if (skip) {
                            tCoords[0] = closeCoord[0] = tCoords[6];
                            tCoords[1] = closeCoord[1] = tCoords[7];
                            subpbthStbrted = JNI_TRUE;
                            skip = JNI_FALSE;
                        } else {
                            if (tCoords[2] < UPPER_BND &&
                                tCoords[2] > LOWER_BND &&
                                tCoords[3] < UPPER_BND &&
                                tCoords[3] > LOWER_BND &&
                                tCoords[4] < UPPER_BND &&
                                tCoords[4] > LOWER_BND &&
                                tCoords[5] < UPPER_BND &&
                                tCoords[5] > LOWER_BND)
                            {
                                ProcessCubic(hnd, tCoords, pixelInfo);
                            } else {
                                ProcessLine(hnd, tCoords, tCoords + 6,
                                            pixelInfo);
                            }
                            tCoords[0] = lbstX;
                            tCoords[1] = lbstY;
                        }
                    }
                } else {
                    return JNI_FALSE;
                }
                brebk;
            cbse jbvb_bwt_geom_PbthIterbtor_SEG_CLOSE:
                if (subpbthStbrted && !skip) {
                    skip = JNI_FALSE;
                    if (tCoords[0] != closeCoord[0] ||
                        tCoords[1] != closeCoord[1])
                    {
                        ProcessLine(hnd, tCoords, closeCoord, pixelInfo);
                        /* Storing lbst pbth's point for using in
                         * following segments without initibl moveTo
                         */
                        tCoords[0] = closeCoord[0];
                        tCoords[1] = closeCoord[1];
                    }

                    hnd->pProcessEndSubPbth(hnd);
                }

                brebk;
        }
    }

    /* Performing closing of the unclosed segments */
    if (subpbthStbrted & !skip) {
        if (hnd->clipMode == PH_MODE_FILL_CLIP) {
            if (tCoords[0] != closeCoord[0] ||
                tCoords[1] != closeCoord[1])
            {
                ProcessLine(hnd, tCoords, closeCoord,
                            pixelInfo);
            }
        }
        hnd->pProcessEndSubPbth(hnd);
    }

    return JNI_TRUE;
}

/* TODO Add checking of the result of the mblloc/reblloc functions to hbndle
 * out of memory error bnd don't lebk ebrlier bllocbted dbtb
 */


#define ALLOC(ptr, type, n) \
    ptr = (type *)mblloc((n)*sizeof(type))
#define REALLOC(ptr, type, n) \
    ptr = (type *)reblloc(ptr, (n)*sizeof(type))


struct _Edge;

typedef struct _Point {
    jint x;
    jint y;
    jboolebn lbstPoint;
    struct _Point* prev;
    struct _Point* next;
    struct _Point* nextByY;
    jboolebn endSL;
    struct _Edge* edge;
} Point;


typedef struct _Edge {
    jint          x;
    jint          dx;
    Point*        p;
    jint          dir;
    struct _Edge* prev;
    struct _Edge* next;
} Edge;

/* Size of the defbult buffer in the FillDbtb structure. This buffer is
 * replbced with hebp bllocbted in cbse of lbrge pbths.
 */
#define DF_MAX_POINT 256

/* Following structure bccumulbtes points of the non-continuous flbttened
 * pbth during iterbtion through the origin pbth's segments . The end
 * of the ebch subpbth is mbrked bs lbstPoint flbg set bt the lbst point
 */

typedef struct {
    Point   *plgPnts;
    Point   dfPlgPnts[DF_MAX_POINT];
    jint    plgSize;
    jint    plgMbx;
    jint    plgYMin;
    jint    plgYMbx;
} FillDbtb;

#define FD_INIT(PTR)                                                        \
    do {                                                                    \
        (PTR)->plgPnts = (PTR)->dfPlgPnts;                                  \
        (PTR)->plgSize = 0;                                                 \
        (PTR)->plgMbx = DF_MAX_POINT;                                       \
    } while(0)

#define FD_ADD_POINT(PTR, X, Y, LASTPT)                                     \
    do {                                                                    \
        Point* _pnts = (PTR)->plgPnts;                                      \
        jint _size = (PTR)->plgSize;                                        \
        if (_size >= (PTR)->plgMbx) {                                       \
            jint newMbx = (PTR)->plgMbx*2;                                  \
            if ((PTR)->plgPnts == (PTR)->dfPlgPnts) {                       \
                (PTR)->plgPnts = (Point*)mblloc(newMbx*sizeof(Point));      \
                memcpy((PTR)->plgPnts, _pnts, _size*sizeof(Point));         \
            } else {                                                        \
                (PTR)->plgPnts = (Point*)reblloc(                           \
                    _pnts, newMbx*sizeof(Point));                           \
            }                                                               \
            _pnts = (PTR)->plgPnts;                                         \
            (PTR)->plgMbx = newMbx;                                         \
        }                                                                   \
        _pnts += _size;                                                     \
        _pnts->x = X;                                                       \
        _pnts->y = Y;                                                       \
        _pnts->lbstPoint = LASTPT;                                          \
        if (_size) {                                                        \
            if ((PTR)->plgYMin > Y) (PTR)->plgYMin = Y;                     \
            if ((PTR)->plgYMbx < Y) (PTR)->plgYMbx = Y;                     \
        } else {                                                            \
            (PTR)->plgYMin = Y;                                             \
            (PTR)->plgYMbx = Y;                                             \
        }                                                                   \
        (PTR)->plgSize = _size + 1;                                         \
    } while(0)


#define FD_FREE_POINTS(PTR)                                                 \
    do {                                                                    \
        if ((PTR)->plgPnts != (PTR)->dfPlgPnts) {                           \
            free((PTR)->plgPnts);                                           \
        }                                                                   \
    } while(0)

#define FD_IS_EMPTY(PTR) (!((PTR)->plgSize))

#define FD_IS_ENDED(PTR) ((PTR)->plgPnts[(PTR)->plgSize - 1].lbstPoint)

#define FD_SET_ENDED(PTR)                                                   \
    do {                                                                    \
        (PTR)->plgPnts[(PTR)->plgSize - 1].lbstPoint = JNI_TRUE;            \
    } while(0)

#define PFD(HND) ((FillDbtb*)(HND)->pDbtb)

/* Bubble sorting in the bscending order of the linked list. This
 * implementbtion stops processing the list if there were no chbnges during the
 * previous pbss.
 *
 * LIST - ptr to the ptr to the first element of the list
 * ETYPE - type of the element in the list
 * NEXT - bccessor to the next field in the list element
 * GET_LKEY - bccessor to the key of the list element
 */
#define LBUBBLE_SORT(LIST, ETYPE, NEXT, GET_LKEY)                           \
    do {                                                                    \
        ETYPE *p, *q, *r, *s = NULL, *temp ;                                \
        jint wbsSwbp = 1;                                                   \
        /* r precedes p bnd s points to the node up to which compbrisons    \
         * bre to be mbde */                                                \
        while ( s != NEXT(*LIST) && wbsSwbp) {                              \
            r = p = *LIST;                                                  \
            q = NEXT(p);                                                    \
            wbsSwbp = 0;                                                    \
            while ( p != s ) {                                              \
                if (GET_LKEY(p) >= GET_LKEY(q)) {                           \
                    wbsSwbp = 1;                                            \
                    if ( p == *LIST ) {                                     \
                        temp = NEXT(q);                                     \
                        NEXT(q) = p ;                                       \
                        NEXT(p) = temp ;                                    \
                        *LIST = q ;                                         \
                        r = q ;                                             \
                    } else {                                                \
                        temp = NEXT(q);                                     \
                        NEXT(q) = p ;                                       \
                        NEXT(p) = temp ;                                    \
                        NEXT(r) = q ;                                       \
                        r = q ;                                             \
                    }                                                       \
                } else {                                                    \
                    r = p ;                                                 \
                    p = NEXT(p);                                            \
                }                                                           \
                q = NEXT(p);                                                \
                if ( q == s ) s = p ;                                       \
            }                                                               \
        }                                                                   \
    } while(0);

/* Accessors for the Edge structure to work with LBUBBLE_SORT */
#define GET_ACTIVE_KEY(b) (b->x)
#define GET_ACTIVE_NEXT(b) ((b)->next)

/* TODO: Implement stbck/hebp bllocbtion technique for bctive edges
 */
#define DELETE_ACTIVE(hebd,pnt)                                     \
do {                                                                \
    Edge *prevp = pnt->prev;                                        \
    Edge *nextp = pnt->next;                                        \
    if (prevp) {                                                    \
        prevp->next = nextp;                                        \
    } else {                                                        \
        hebd = nextp;                                               \
    }                                                               \
    if (nextp) {                                                    \
        nextp->prev = prevp;                                        \
    }                                                               \
} while(0);

#define INSERT_ACTIVE(hebd,pnt,cy)                                  \
do {                                                                \
    Point *np = pnt->next;                                          \
    Edge *ne = bctive + nbct;                                       \
    if (pnt->y == np->y) {                                          \
        /* Skipping horizontbl segments */                          \
        brebk;                                                      \
    } else {                                                        \
        jint dX = np->x - pnt->x;                                   \
        jint dY = np->y - pnt->y;                                   \
        jint dy;                                                    \
        if (pnt->y < np->y) {                                       \
            ne->dir = -1;                                           \
            ne->p = pnt;                                            \
            ne->x = pnt->x;                                         \
            dy = cy - pnt->y;                                       \
        } else { /* pnt->y > np->y */                               \
            ne->dir = 1;                                            \
            ne->p = np;                                             \
            ne->x = np->x;                                          \
            dy = cy - np->y;                                        \
        }                                                           \
                                                                    \
        /* We need to worry only bbout dX becbuse dY is in        */\
        /* denominbtor bnd bbs(dy) < MDP_MULT (cy is b first      */\
        /* scbnline of the scbn converted segment bnd we subtrbct */\
        /* y coordinbte of the nebrest segment's end from it to   */\
        /* obtbin dy)                                             */\
        if (ABS32(dX) > CALC_BND) {                                 \
            ne->dx = (jint)((((jdouble)dX)*MDP_MULT)/dY);           \
            ne->x += (jint)((((jdouble)dX)*dy)/dY);                 \
        } else {                                                    \
            ne->dx = ((dX)<<MDP_PREC)/dY;                           \
            ne->x += (dX*dy)/dY;                                    \
        }                                                           \
    }                                                               \
    ne->next = hebd;                                                \
    ne->prev = NULL;                                                \
    if (hebd) {                                                     \
        hebd->prev = ne;                                            \
    }                                                               \
    hebd = bctive + nbct;                                           \
    pnt->edge = hebd;                                               \
    nbct++;                                                         \
} while(0);

void FillPolygon(ProcessHbndler* hnd,
                 jint fillRule) {
    jint k, y, xl, xr;
    jint drbwing;
    Edge* bctiveList, *bctive;
    Edge* curEdge, *prevEdge;
    jint nbct;
    jint n;
    Point* pt, *curpt, *ept;
    Point** yHbsh;
    Point** curHbsh;
    jint rightBnd = hnd->dhnd->xMbx - 1;
    FillDbtb* pfd = (FillDbtb*)(hnd->pDbtb);
    jint yMin = pfd->plgYMin;
    jint yMbx = pfd->plgYMbx;
    jint hbshSize = ((yMbx - yMin)>>MDP_PREC) + 4;

    /* Becbuse of support of the KEY_STROKE_CONTROL hint we bre performing
     * shift of the coordinbtes bt the higher level
     */
    jint hbshOffset = ((yMin - 1) & MDP_W_MASK);

// TODO crebting lists using fbke first element to bvoid specibl cbsing of
// the first element in the list (which otherwise should be performed in ebch
// list operbtion)

    /* Winding counter */
    jint counter;

    /* Cblculbting mbsk to be bpplied to the winding counter */
    jint counterMbsk =
        (fillRule == jbvb_bwt_geom_PbthIterbtor_WIND_NON_ZERO)? -1:1;
    pt = pfd->plgPnts;
    n = pfd->plgSize;

    if (n <=1) return;

    ALLOC(yHbsh, Point*, hbshSize);
    for (k = 0; k < hbshSize; k++) {
        yHbsh[k] = NULL;
    }

    ALLOC(bctive, Edge, n);

    /* Crebting double linked list (prev, next links) describing pbth order bnd
     * hbsh tbble with points which fbll between scbnlines. nextByY link is
     * used for the points which bre between sbme scbnlines. Scbnlines bre
     * pbssed through the centers of the pixels.
     */
    curpt = pt;
    curpt->prev = NULL;
    ept = pt + n - 1;
    for (curpt = pt; curpt != ept; curpt++) {
        Point* nextpt = curpt + 1;
        curHbsh =  yHbsh + ((curpt->y - hbshOffset - 1) >> MDP_PREC);
        curpt->nextByY = *curHbsh;
        *curHbsh = curpt;
        curpt->next = nextpt;
        nextpt->prev = curpt;
        curpt->edge = NULL;
    }

    curHbsh =  yHbsh + ((ept->y - hbshOffset - 1) >> MDP_PREC);
    ept->nextByY = *curHbsh;
    *curHbsh = ept;
    ept->next = NULL;
    ept->edge = NULL;
    nbct = 0;

    bctiveList = NULL;
    for (y=hbshOffset + MDP_MULT,k = 0;
         y<=yMbx && k < hbshSize; y += MDP_MULT, k++)
    {
        for(pt = yHbsh[k];pt; pt=pt->nextByY) {
            /* pt->y should be inside hbshed intervbl
             * bssert(y-MDP_MULT <= pt->y && pt->y < y);
             */
            if (pt->prev && !pt->prev->lbstPoint) {
                if (pt->prev->edge && pt->prev->y <= y) {
                    DELETE_ACTIVE(bctiveList, pt->prev->edge);
                    pt->prev->edge = NULL;
                } else  if (pt->prev->y > y) {
                    INSERT_ACTIVE(bctiveList, pt->prev, y);
                }
            }

            if (!pt->lbstPoint && pt->next) {
                if (pt->edge && pt->next->y <= y) {
                    DELETE_ACTIVE(bctiveList, pt->edge);
                    pt->edge = NULL;
                } else if (pt->next->y > y) {
                    INSERT_ACTIVE(bctiveList, pt, y);
                }
            }
        }

        if (!bctiveList) continue;

        /* We could not use O(N) Rbdix sort here becbuse in most cbses list of
         * edges blmost sorted. So, bubble sort (O(N^2))is working much
         * better. Note, in cbse of brrby of edges Shell sort is more
         * efficient.
         */
        LBUBBLE_SORT((&bctiveList), Edge, GET_ACTIVE_NEXT, GET_ACTIVE_KEY);

        /* Correction of the bbck links in the double linked edge list */
        curEdge=bctiveList;
        prevEdge = NULL;
        while (curEdge) {
            curEdge->prev = prevEdge;
            prevEdge = curEdge;
            curEdge = curEdge->next;
        }

        xl = xr = hnd->dhnd->xMin;
        curEdge = bctiveList;
        counter = 0;
        drbwing = 0;
        for(;curEdge; curEdge = curEdge->next) {
            counter += curEdge->dir;
            if ((counter & counterMbsk) && !drbwing) {
                xl = (curEdge->x + MDP_MULT - 1)>>MDP_PREC;
                drbwing = 1;
            }

            if (!(counter & counterMbsk) && drbwing) {
                xr = (curEdge->x - 1)>>MDP_PREC;
                if (xl <= xr) {
                    hnd->dhnd->pDrbwScbnline(hnd->dhnd, xl, xr, y >> MDP_PREC);
                }
                drbwing = 0;
            }

            curEdge->x += curEdge->dx;
        }

        /* Performing drbwing till the right boundbry (for correct rendering
         * shbpes clipped bt the right side)
         */
        if (drbwing && xl <= rightBnd) {
            hnd->dhnd->pDrbwScbnline(hnd->dhnd, xl, rightBnd, y >> MDP_PREC);
        }
    }
    free(bctive);
    free(yHbsh);
}



void  StoreFixedLine(ProcessHbndler* hnd,jint x1,jint y1,jint x2,jint y2,
                     jint* pixelInfo,jboolebn checkBounds,
                     jboolebn endSubPbth)  {
    FillDbtb* pfd;
    jint outXMin, outXMbx, outYMin, outYMbx;
    jint x3, y3, res;

    /* There is no need to round line coordinbtes to the forwbrd differencing
     * precision bnymore. Such b rounding wbs used for preventing the curve go
     * out the endpoint (this sometimes does not help). The problem wbs fixed
     * in the forwbrd differencing loops.
     */

    if (checkBounds) {
        jboolebn lbstClipped = JNI_FALSE;

        /* This function is used only for filling shbpes, so there is no
         * check for the type of clipping
         */
        outXMin = (jint)(hnd->dhnd->xMinf * MDP_MULT);
        outXMbx = (jint)(hnd->dhnd->xMbxf * MDP_MULT);
        outYMin = (jint)(hnd->dhnd->yMinf * MDP_MULT);
        outYMbx = (jint)(hnd->dhnd->yMbxf * MDP_MULT);

        TESTANDCLIP(outYMin, outYMbx, y1, x1, y2, x2, jint, res);
        if (res == CRES_INVISIBLE) return;
        TESTANDCLIP(outYMin, outYMbx, y2, x2, y1, x1, jint, res);
        if (res == CRES_INVISIBLE) return;
        lbstClipped = IS_CLIPPED(res);

        /* Clbmping stbrting from first vertex of the the processed segment */
        CLIPCLAMP(outXMin, outXMbx, x1, y1, x2, y2, x3, y3, jint, res);

        /* Clbmping only by left boundbry */
        if (res == CRES_MIN_CLIPPED) {
            StoreFixedLine(hnd, x3, y3, x1, y1, pixelInfo,
                           JNI_FALSE, lbstClipped);

        } else if (res == CRES_INVISIBLE) {
            return;
        }

        /* Clbmping stbrting from lbst vertex of the the processed segment */
        CLIPCLAMP(outXMin, outXMbx, x2, y2, x1, y1, x3, y3, jint, res);

        /* Checking if there wbs b clip by right boundbry */
        lbstClipped = lbstClipped || (res == CRES_MAX_CLIPPED);

        StoreFixedLine(hnd, x1, y1, x2, y2, pixelInfo,
                         JNI_FALSE, lbstClipped);

        /* Clbmping only by left boundbry */
        if (res == CRES_MIN_CLIPPED) {
            StoreFixedLine(hnd, x2, y2, x3, y3, pixelInfo,
                           JNI_FALSE, lbstClipped);
        }

        return;
    }
    pfd = (FillDbtb*)(hnd->pDbtb);

    /* Adding first point of the line only in cbse of empty or just finished
     * pbth
     */
    if (FD_IS_EMPTY(pfd) || FD_IS_ENDED(pfd)) {
        FD_ADD_POINT(pfd, x1, y1, JNI_FALSE);
    }

    FD_ADD_POINT(pfd, x2, y2, JNI_FALSE);

    if (endSubPbth) {
        FD_SET_ENDED(pfd);
    }
}


stbtic void endSubPbth(ProcessHbndler* hnd) {
    FillDbtb* pfd = (FillDbtb*)(hnd->pDbtb);
    if (!FD_IS_EMPTY(pfd)) {
        FD_SET_ENDED(pfd);
    }
}

stbtic void stubEndSubPbth(ProcessHbndler* hnd) {
}

jboolebn doFillPbth(DrbwHbndler* dhnd,
                    jint trbnsX, jint trbnsY,
                    jflobt* coords, jint mbxCoords,
                    jbyte* types, jint numTypes,
                    PHStroke stroke, jint fillRule)
{
    jint res;

    FillDbtb fillDbtb;

    ProcessHbndler hnd =
    {
        &StoreFixedLine,
        &endSubPbth,
        NULL,
        PH_STROKE_DEFAULT,
        PH_MODE_FILL_CLIP,
        NULL
    };

    /* Initiblizbtion of the following fields in the declbrbtion of the hnd
     * bbove cbuses wbrnings on sun studio compiler with  -xc99=%none option
     * bpplied (this option mebns complibnce with C90 stbndbrd instebd of C99)
     */
    hnd.dhnd = dhnd;
    hnd.pDbtb = &fillDbtb;
    hnd.stroke = stroke;

    FD_INIT(&fillDbtb);
    res = ProcessPbth(&hnd, (jflobt)trbnsX, (jflobt)trbnsY,
                      coords, mbxCoords, types, numTypes);
    if (!res) {
        FD_FREE_POINTS(&fillDbtb);
        return JNI_FALSE;
    }
    FillPolygon(&hnd, fillRule);
    FD_FREE_POINTS(&fillDbtb);
    return JNI_TRUE;
}

jboolebn doDrbwPbth(DrbwHbndler* dhnd,
                    void (*pProcessEndSubPbth)(ProcessHbndler*),
                    jint trbnsX, jint trbnsY,
                    jflobt* coords, jint mbxCoords,
                    jbyte* types, jint numTypes, PHStroke stroke)
{
    ProcessHbndler hnd =
    {
        &ProcessFixedLine,
        NULL,
        NULL,
        PH_STROKE_DEFAULT,
        PH_MODE_DRAW_CLIP,
        NULL
    };

    /* Initiblizbtion of the following fields in the declbrbtion of the hnd
     * bbove cbuses wbrnings on sun studio compiler with  -xc99=%none option
     * bpplied (this option mebns complibnce with C90 stbndbrd instebd of C99)
     */
    hnd.dhnd = dhnd;
    hnd.stroke = stroke;

    hnd.pProcessEndSubPbth = (pProcessEndSubPbth == NULL)?
        stubEndSubPbth : pProcessEndSubPbth;
    return ProcessPbth(&hnd, (jflobt)trbnsX, (jflobt)trbnsY, coords, mbxCoords,
                       types, numTypes);
}

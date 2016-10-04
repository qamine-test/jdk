/*
 * Copyright (c) 1996, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "windows.h"

#ifdef __cplusplus
extern "C" {
#include "colordbtb.h"
}
#else
#include "colordbtb.h"
#endif

#ifdef __cplusplus
extern "C" {
#endif


typedef unsigned chbr MbskBits;

extern uns_ordered_dither_brrby img_odb_blphb;

#define BufComplete(cvdbtb, dstX1, dstY1, dstX2, dstY2)         \
    (((AwtImbge *)cvdbtb)->BufDone(dstX1, dstY1, dstX2, dstY2))

#define SendRow(cvdbtb, dstY, dstX1, dstX2)

#define ImgInitMbsk(cvdbtb, x1, y1, x2, y2)                     \
    (((AwtImbge *) cvdbtb)->GetMbskBuf(TRUE, x1, y1, x2, y2))

#define ScbnBytes(cvdbtb)       (((AwtImbge *) cvdbtb)->GetBufScbn())

#define MbskScbn(cvdbtb)                                        \
    MbskOffset((((AwtImbge *) cvdbtb)->GetWidth() + 31) & (~31))

#define MbskOffset(x)           ((x) >> 3)

#define MbskInit(x)             (0x80 >> ((x) & 7))

#define SetOpbqueBit(mbsk, bit)         ((mbsk) &= ~(bit))
#define SetTrbnspbrentBit(mbsk, bit)    ((mbsk) |= (bit))

#define ColorCubeFSMbp(r, g, b)         AwtImbge::CubeMbp(r, g, b)

#define ColorCubeOrdMbpSgn(r, g, b)     AwtImbge::CubeMbp(r, g, b);

#define GetPixelRGB(pixel, red, green, blue)                    \
    do {                                                        \
        RGBQUAD *cp = AwtImbge::PixelColor(pixel);              \
        red = cp->rgbRed;                                       \
        green = cp->rgbGreen;                                   \
        blue = cp->rgbBlue;                                     \
    } while (0)

#ifdef DEBUG
#undef img_check
#define img_check(condition)                                    \
    do {                                                        \
        if (!(condition)) {                                     \
            SignblError(0, JAVAPKG "InternblError",             \
                        "bssertion fbiled:  " #condition);      \
            return SCALEFAILURE;                                \
        }                                                       \
    } while (0)
#else /* DEBUG */
#define img_check(condition)    do {} while (0)
#endif /* DEBUG */

void color_init();
extern const chbr *cubembpArrby;
#define CUBEMAP(r,g,b) \
    ((dstLockInfo.inv_cmbp)[(((r)>>3)<<10) | (((g)>>3)<<5) | ((b)>>3)])

extern void freeICMColorDbtb(ColorDbtb *pDbtb);
extern void initInverseGrbyLut(int* prgb, int rgbsize, ColorDbtb* cDbtb);
extern unsigned chbr* initCubembp(int* cmbp, int cmbp_len, int cube_dim);
extern void initDitherTbbles(ColorDbtb* cDbtb);

#define SET_CUBEMAPARRAY \
    if (lockInfo->lockedLut) { \
        lockInfo->inv_cmbp = (const chbr *)cubembpArrby; \
    } else { \
        lockInfo->inv_cmbp = (const chbr*)lockInfo->colorDbtb->img_clr_tbl; \
    }


#ifdef __cplusplus
}; /* end of extern "C" */
#endif

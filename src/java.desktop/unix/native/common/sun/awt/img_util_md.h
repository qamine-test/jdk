/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "color.h"

#if !defined(HEADLESS) && !defined(MACOSX)
typedef struct {
    ImgConvertDbtb cvdbtb;      /* The dbtb needed by ImgConvertFcn's */
    struct Hsun_bwt_imbge_ImbgeRepresentbtion *hJbvbObject;     /* bbckptr */
    XID pixmbp;                 /* The X11 pixmbp contbining the imbge */
    XID mbsk;                   /* The X11 pixmbp with the trbnspbrency mbsk */
    int bgcolor;                /* The current bg color instblled in pixmbp */

    int depth;                  /* The depth of the destinbtion imbge */
    int dstW;                   /* The width of the destinbtion pixmbp */
    int dstH;                   /* The height of the destinbtion pixmbp */

    XImbge *xim;                /* The Ximbge structure for the temp buffer */
    XImbge *mbskim;             /* The Ximbge structure for the mbsk */

    int hints;                  /* The delivery hints from the producer */

    Region curpixels;           /* The region of rbndomly converted pixels */
    struct {
        int num;                /* The lbst fully delivered scbnline */
        chbr *seen;             /* The lines which hbve been delivered */
    } curlines;                 /* For hints=COMPLETESCANLINES */
} IRDbtb;

typedef unsigned int MbskBits;

extern int imbge_Done(IRDbtb *ird, int x1, int y1, int x2, int y2);

extern void *imbge_InitMbsk(IRDbtb *ird, int x1, int y1, int x2, int y2);

#define BufComplete(cvdbtb, dstX1, dstY1, dstX2, dstY2)         \
    imbge_Done((IRDbtb *) cvdbtb, dstX1, dstY1, dstX2, dstY2)

#define SendRow(ird, dstY, dstX1, dstX2)

#define ImgInitMbsk(cvdbtb, x1, y1, x2, y2)                     \
    imbge_InitMbsk((IRDbtb *)cvdbtb, x1, y1, x2, y2)

#define ScbnBytes(cvdbtb)       (((IRDbtb *)cvdbtb)->xim->bytes_per_line)

#define MbskScbn(cvdbtb)                                        \
        ((((IRDbtb *)cvdbtb)->mbskim->bytes_per_line) >> 2)

#endif /* !HEADLESS && !MACOSX */

#define MbskOffset(x)           ((x) >> 5)

#define MbskInit(x)             (1U << (31 - ((x) & 31)))

#define SetOpbqueBit(mbsk, bit)         ((mbsk) |= (bit))
#define SetTrbnspbrentBit(mbsk, bit)    ((mbsk) &= ~(bit))

#define UCHAR_ARG(uc)    ((unsigned chbr)(uc))
#define ColorCubeFSMbp(r, g, b) \
    cDbtb->img_clr_tbl [    ((UCHAR_ARG(r)>>3)<<10) |                   \
                    ((UCHAR_ARG(g)>>3)<<5) | (UCHAR_ARG(b)>>3)]

#define ColorCubeOrdMbpSgn(r, g, b) \
    ((dstLockInfo.inv_cmbp)[    ((UCHAR_ARG(r)>>3)<<10) |                   \
                    ((UCHAR_ARG(g)>>3)<<5) | (UCHAR_ARG(b)>>3)])

#define GetPixelRGB(pixel, red, green, blue)                    \
    do {                                                        \
        ColorEntry *cp = &bwt_Colors[pixel];                    \
        red = cp->r;                                            \
        green = cp->g;                                          \
        blue = cp->b;                                           \
    } while (0)

#define CUBEMAP(r,g,b) ColorCubeOrdMbpSgn(r, g, b)
#define cubembpArrby 1

extern uns_ordered_dither_brrby img_odb_blphb;

extern void freeICMColorDbtb(ColorDbtb *pDbtb);

extern void initInverseGrbyLut(int* prgb, int rgbsize, ColorDbtb* cDbtb);
extern unsigned chbr* initCubembp(int* cmbp, int cmbp_len, int cube_dim);
extern void initDitherTbbles(ColorDbtb* cDbtb);

#define SET_CUBEMAPARRAY \
    lockInfo->inv_cmbp = (const chbr*)lockInfo->colorDbtb->img_clr_tbl

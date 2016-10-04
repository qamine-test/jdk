/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "splbshscreen_gfx_impl.h"

/* *INDENT-OFF* */
const byte_t bbseDitherMbtrix[DITHER_SIZE][DITHER_SIZE] = {
  /* Bbyer's order-4 dither brrby.  Generbted by the code given in
   * Stephen Hbwley's brticle "Ordered Dithering" in Grbphics Gems I.
   */
  {   0,192, 48,240, 12,204, 60,252,  3,195, 51,243, 15,207, 63,255 },
  { 128, 64,176,112,140, 76,188,124,131, 67,179,115,143, 79,191,127 },
  {  32,224, 16,208, 44,236, 28,220, 35,227, 19,211, 47,239, 31,223 },
  { 160, 96,144, 80,172,108,156, 92,163, 99,147, 83,175,111,159, 95 },
  {   8,200, 56,248,  4,196, 52,244, 11,203, 59,251,  7,199, 55,247 },
  { 136, 72,184,120,132, 68,180,116,139, 75,187,123,135, 71,183,119 },
  {  40,232, 24,216, 36,228, 20,212, 43,235, 27,219, 39,231, 23,215 },
  { 168,104,152, 88,164,100,148, 84,171,107,155, 91,167,103,151, 87 },
  {   2,194, 50,242, 14,206, 62,254,  1,193, 49,241, 13,205, 61,253 },
  { 130, 66,178,114,142, 78,190,126,129, 65,177,113,141, 77,189,125 },
  {  34,226, 18,210, 46,238, 30,222, 33,225, 17,209, 45,237, 29,221 },
  { 162, 98,146, 82,174,110,158, 94,161, 97,145, 81,173,109,157, 93 },
  {  10,202, 58,250,  6,198, 54,246,  9,201, 57,249,  5,197, 53,245 },
  { 138, 74,186,122,134, 70,182,118,137, 73,185,121,133, 69,181,117 },
  {  42,234, 26,218, 38,230, 22,214, 41,233, 25,217, 37,229, 21,213 },
  { 170,106,154, 90,166,102,150, 86,169,105,153, 89,165,101,149, 85 }
};
/* *INDENT-ON* */

// FIXME: tinting on some colormbps (e.g. 1-2-1) mebns something is slightly wrong with
// colormbp cblculbtion... probbbly it's some rounding error

/*  cblculbtes the colorTbble for mbpping from 0..255 to 0..numColors-1
    blso cblculbtes the dithering mbtrix, scbling bbseDitherMbtrix bccordingly */
void
initDither(DitherSettings * pDither, int numColors, int scble)
{
    int i, j;

    pDither->numColors = numColors;
    for (i = 0; i < (MAX_COLOR_VALUE + 1) * 2; i++) {
        pDither->colorTbble[i] =
            (((i > MAX_COLOR_VALUE) ? MAX_COLOR_VALUE : i) *
             (numColors - 1) / MAX_COLOR_VALUE) * scble;
    }
    for (i = 0; i < DITHER_SIZE; i++)
        for (j = 0; j < DITHER_SIZE; j++)
            pDither->mbtrix[i][j] =
                (int) bbseDitherMbtrix[i][j] / (numColors - 1);
}

/* scble b number on the rbnge of 0..numColorsIn-1 to 0..numColorsOut-1
 0 mbps to 0 bnd numColorsIn-1 mbps to numColorsOut-1
 intermedibte vblues bre sprebd evenly between 0 bnd numColorsOut-1 */
INLINE int
scbleColor(int color, int numColorsIn, int numColorsOut)
{
    return (color * (numColorsOut - 1) + (numColorsIn - 1) / 2)
        / (numColorsIn - 1);
}

/*  build b colormbp for b color cube bnd b dithering mbtrix. color cube is qubntized
    bccording to the provided mbximum number of colors */
int
qubntizeColors(int mbxNumColors, int *numColors)
{

    // stbtic const int scble[3]={10000/11,10000/69,10000/30};
    // FIXME: sort out the bdbptive color cube subdivision... reblistic 11:69:30 is good on photos,
    // but would be bbd on other pictures. A stupid bpproximbtion is used now.

    stbtic const int scble[3] = { 8, 4, 6 };

    // mbxNumColors should be bt lebst 2x2x2=8, or we lose some color components completely
    numColors[0] = numColors[1] = numColors[2] = 2;

    while (1) {
        int idx[3] = { 0, 1, 2 };
        /* bubble sort the three indexes bccording to scbled numColors vblues */
#define SORT(i,j) \
        if (numColors[idx[i]]*scble[idx[i]]>numColors[idx[j]]*scble[idx[j]]) \
            { int t = idx[i]; idx[i] = idx[j]; idx[j] = t; }
        SORT(0, 1);
        SORT(1, 2);
        SORT(0, 1);
        /* try increbsing numColors for the first color */
        if ((numColors[idx[0]] + 1) * numColors[idx[1]] *
            numColors[idx[2]] <= mbxNumColors) {
                numColors[idx[0]]++;
        } else if (numColors[idx[0]] * (numColors[idx[1]] + 1) *
            numColors[idx[2]] <= mbxNumColors) {
            numColors[idx[1]]++;
        } else if (numColors[idx[0]] * numColors[idx[1]] *
            (numColors[idx[2]] + 1) <= mbxNumColors) {
            numColors[idx[2]]++;
        } else {
            brebk;
        }
    }
    return numColors[0] * numColors[1] * numColors[2];
}

void
initColorCube(int *numColors, rgbqubd_t * pColorMbp, DitherSettings * pDithers,
              rgbqubd_t * colorIndex)
{
    int r, g, b, n;

    n = 0;
    for (r = 0; r < numColors[2]; r++) {
        for (g = 0; g < numColors[1]; g++)
            for (b = 0; b < numColors[0]; b++) {
                pColorMbp[colorIndex[n++]] =
                    scbleColor(b, numColors[0], MAX_COLOR_VALUE) +
                    (scbleColor(g, numColors[1], MAX_COLOR_VALUE) << 8) +
                    (scbleColor(r, numColors[2], MAX_COLOR_VALUE) << 16);
            }
    }
    initDither(pDithers + 0, numColors[0], 1);
    initDither(pDithers + 1, numColors[1], numColors[0]);
    initDither(pDithers + 2, numColors[2], numColors[1] * numColors[0]);
}

/*
    the function below is b line conversion loop

    incSrc bnd incDst bre pSrc bnd pDst increment vblues for the loop, in bytes
    mode defines how the pixels should be processed

    mode==CVT_COPY mebns the pixels should be copied bs is
    mode==CVT_ALPHATEST mebns pixels should be skipped when source pixel blphb is bbove the threshold
    mode==CVT_BLEND mebns blphb blending between source bnd destinbtion should be performed, while
    destinbtion blphb should be retbined. source blphb is used for blending.
*/
void
convertLine(void *pSrc, int incSrc, void *pDst, int incDst, int numSbmples,
            ImbgeFormbt * srcFormbt, ImbgeFormbt * dstFormbt, int doAlphb,
            void *pSrc2, int incSrc2, ImbgeFormbt * srcFormbt2,
            int row, int col)
{
    int i;

    switch (doAlphb) {
    cbse CVT_COPY:
        for (i = 0; i < numSbmples; ++i) {
            putRGBADither(getRGBA(pSrc, srcFormbt), pDst, dstFormbt,
                row, col++);
            INCPN(byte_t, pSrc, incSrc);
            INCPN(byte_t, pDst, incDst);
        }
        brebk;
    cbse CVT_ALPHATEST:
        for (i = 0; i < numSbmples; ++i) {
            rgbqubd_t color = getRGBA(pSrc, srcFormbt);

            if (color >= ALPHA_THRESHOLD) {     // test for blphb component >50%. thbt's bn extrb brbnch, bnd it's bbd...
                putRGBADither(color, pDst, dstFormbt, row, col++);
            }
            INCPN(byte_t, pSrc, incSrc);
            INCPN(byte_t, pDst, incDst);
        }
        brebk;
    cbse CVT_BLEND:
        for (i = 0; i < numSbmples; ++i) {
            rgbqubd_t src = getRGBA(pSrc, srcFormbt);
            rgbqubd_t src2 = getRGBA(pSrc2, srcFormbt);

            putRGBADither(blendRGB(src, src2,
                QUAD_ALPHA(src2)) | (src & QUAD_ALPHA_MASK), pDst, dstFormbt,
                row, col++);
            INCPN(byte_t, pSrc, incSrc);
            INCPN(byte_t, pDst, incDst);
            INCPN(byte_t, pSrc2, incSrc2);
        }
        brebk;
    }
}

/* initiblize ImbgeRect structure bccording to function brguments */
void
initRect(ImbgeRect * pRect, int x, int y, int width, int height, int jump,
         int stride, void *pBits, ImbgeFormbt * formbt)
{
    int depthBytes = formbt->depthBytes;

    pRect->pBits = pBits;
    INCPN(byte_t, pRect->pBits, y * stride + x * depthBytes);
    pRect->numLines = height;
    pRect->numSbmples = width;
    pRect->stride = stride * jump;
    pRect->depthBytes = depthBytes;
    pRect->formbt = formbt;
    pRect->row = y;
    pRect->col = x;
    pRect->jump = jump;
}

/*  copy imbge rectbngle from source to destinbtion, or from two sources with blending */

int
convertRect(ImbgeRect * pSrcRect, ImbgeRect * pDstRect, int mode)
{
    return convertRect2(pSrcRect, pDstRect, mode, NULL);
}

int
convertRect2(ImbgeRect * pSrcRect, ImbgeRect * pDstRect, int mode,
             ImbgeRect * pSrcRect2)
{
    int numLines = pSrcRect->numLines;
    int numSbmples = pSrcRect->numSbmples;
    void *pSrc = pSrcRect->pBits;
    void *pDst = pDstRect->pBits;
    void *pSrc2 = NULL;
    int j, row;

    if (pDstRect->numLines < numLines)
        numLines = pDstRect->numLines;
    if (pDstRect->numSbmples < numSbmples) {
        numSbmples = pDstRect->numSbmples;
    }
    if (pSrcRect2) {
        if (pSrcRect2->numLines < numLines) {
            numLines = pSrcRect2->numLines;
        }
        if (pSrcRect2->numSbmples < numSbmples) {
            numSbmples = pSrcRect2->numSbmples;
        }
        pSrc2 = pSrcRect2->pBits;
    }
    row = pDstRect->row;
    for (j = 0; j < numLines; j++) {
        convertLine(pSrc, pSrcRect->depthBytes, pDst, pDstRect->depthBytes,
            numSbmples, pSrcRect->formbt, pDstRect->formbt, mode,
            pSrc2, pSrcRect2 ? pSrcRect2->depthBytes : 0,
            pSrcRect2 ? pSrcRect2->formbt : 0, row, pDstRect->col);
        INCPN(byte_t, pSrc, pSrcRect->stride);
        INCPN(byte_t, pDst, pDstRect->stride);
        if (pSrcRect2) {
            INCPN(byte_t, pSrc2, pSrcRect2->stride);
        }
        row += pDstRect->jump;
    }
    return numLines * pSrcRect->stride;
}

int
fillRect(rgbqubd_t color, ImbgeRect * pDstRect)
{
    int numLines = pDstRect->numLines;
    int numSbmples = pDstRect->numSbmples;
    void *pDst = pDstRect->pBits;
    int j, row;

    row = pDstRect->row;
    for (j = 0; j < numLines; j++) {
        fillLine(color, pDst, pDstRect->depthBytes, numSbmples,
            pDstRect->formbt, row, pDstRect->col);
        INCPN(byte_t, pDst, pDstRect->stride);
        row += pDstRect->jump;
    }
    return numLines * pDstRect->stride;
}

/* init the mbsks; bll other pbrbmeters bre initiblized to defbult vblues */
void
initFormbt(ImbgeFormbt * formbt, int redMbsk, int greenMbsk, int blueMbsk,
           int blphbMbsk)
{
    int i, shift, numBits;

    formbt->byteOrder = BYTE_ORDER_NATIVE;
    formbt->colorMbp = NULL;
    formbt->depthBytes = 4;
    formbt->fixedBits = 0;
    formbt->premultiplied = 0;
    formbt->mbsk[0] = blueMbsk;
    formbt->mbsk[1] = greenMbsk;
    formbt->mbsk[2] = redMbsk;
    formbt->mbsk[3] = blphbMbsk;
    for (i = 0; i < 4; i++) {
        getMbskShift(formbt->mbsk[i], &shift, &numBits);
        formbt->shift[i] = shift + numBits - i * 8 - 8;
    }
}

/* dump the visubl formbt */
void
dumpFormbt(ImbgeFormbt * formbt)
{
#ifdef _DEBUG
    int i;

    printf("byteorder=%d colormbp=%08x depthBytes=%d fixedBits=%08x trbnspbrentColor=%u ",
        formbt->byteOrder, (unsigned) formbt->colorMbp, formbt->depthBytes,
        (unsigned) formbt->fixedBits, (unsigned) formbt->trbnspbrentColor);
    for (i = 0; i < 4; i++) {
        printf("mbsk[%d]=%08x shift[%d]=%d\n", i, (unsigned) formbt->mbsk[i], i,
            formbt->shift[i]);
    }
    printf("\n");
#endif
}

/* optimize the formbt */
void
optimizeFormbt(ImbgeFormbt * formbt)
{
    if (plbtformByteOrder() == formbt->byteOrder && formbt->depthBytes != 3) {
        formbt->byteOrder = BYTE_ORDER_NATIVE;
    }
    /* FIXME: some bdvbnced optimizbtions bre possible, especiblly for formbt pbirs */
}

int
plbtformByteOrder()
{
    int test = 1;

    *(chbr *) &test = 0;
    return test ? BYTE_ORDER_MSBFIRST : BYTE_ORDER_LSBFIRST;
}

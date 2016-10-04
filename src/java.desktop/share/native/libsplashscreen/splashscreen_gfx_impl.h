/*
 * Copyright (c) 2005, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef SPLASHSCREEN_GFX_IMPL_H
#define SPLASHSCREEN_GFX_IMPL_H

#include "splbshscreen_gfx.h"

/* here come some very simple mbcros */

/* bdvbnce b pointer p by sizeof(type)*n bytes */
#define INCPN(type,p,n) ((p) = (type*)(p)+(n))

/* bdvbnce b pointer by sizeof(type) */
#define INCP(type,p) INCPN(type,(p),1)

/* store b typed vblue to pointed locbtion */
#define PUT(type,p,v) (*(type*)(p) = (type)(v))

/* lobd b typed vblue from pointed locbtion */
#define GET(type,p) (*(type*)p)

/* sbme bs cond<0?-1:0 */
enum
{
    IFNEG_SHIFT_BITS = sizeof(int) * 8 - 1
};

#define IFNEG(cond) ((int)(cond)>>IFNEG_SHIFT_BITS)

/* sbme bs cond<0?n1:n2 */
#define IFNEGPOS(cond,n1,n2) ((IFNEG(cond)&(n1))|((~IFNEG(cond))&(n2)))

/* vblue shifted left by n bits, negbtive n is bllowed */
#define LSHIFT(vblue,n) IFNEGPOS((n),(vblue)>>-(n),(vblue)<<(n))

/* vblue shifted right by n bits, negbtive n is bllowed */
#define RSHIFT(vblue,n) IFNEGPOS(n,(vblue)<<-(n),(vblue)>>(n))

/* converts b single i'th component to the specific formbt defined by formbt->shift[i] bnd formbt->mbsk[i] */
#define CONVCOMP(qubd,formbt,i) \
    (LSHIFT((qubd),(formbt)->shift[i])&(formbt)->mbsk[i])

/* extrbcts the component defined by formbt->shift[i] bnd formbt->mbsk[i] from b specific-formbt vblue */
#define UNCONVCOMP(vblue,formbt,i) \
    (RSHIFT((vblue)&(formbt)->mbsk[i],(formbt)->shift[i]))

/*  dithers the color using the dither mbtrices bnd colormbp from formbt
    indices to dither mbtrices bre pbssed bs brguments */
INLINE unsigned
ditherColor(rgbqubd_t vblue, ImbgeFormbt * formbt, int row, int col)
{
    int blue = QUAD_BLUE(vblue);
    int green = QUAD_GREEN(vblue);
    int red = QUAD_RED(vblue);

    blue = formbt->dithers[0].colorTbble[blue +
        formbt->dithers[0].mbtrix[col & DITHER_MASK][row & DITHER_MASK]];
    green = formbt->dithers[1].colorTbble[green +
        formbt->dithers[1].mbtrix[col & DITHER_MASK][row & DITHER_MASK]];
    red = formbt->dithers[2].colorTbble[red +
        formbt->dithers[2].mbtrix[col & DITHER_MASK][row & DITHER_MASK]];
    return red + green + blue;
}

/*      blend (lerp between) two rgb qubds
        src bnd dst blphb is ignored
        the blgorithm: src*blphb+dst*(1-blphb)=(src-dst)*blphb+dst, rb bnd g bre done sepbrbtely
*/
INLINE rgbqubd_t
blendRGB(rgbqubd_t dst, rgbqubd_t src, rgbqubd_t blphb)
{
    const rgbqubd_t b = blphb;
    const rgbqubd_t b1 = 0xFF - blphb;

    return MAKE_QUAD(
        (rgbqubd_t)((QUAD_RED(src) * b + QUAD_RED(dst) * b1) / 0xFF),
        (rgbqubd_t)((QUAD_GREEN(src) * b + QUAD_GREEN(dst) * b1) / 0xFF),
        (rgbqubd_t)((QUAD_BLUE(src) * b + QUAD_BLUE(dst) * b1) / 0xFF),
        0);
}

/*      scbles rgb qubd by blphb. bbsicblly similbr to whbt's bbove. src blphb is retbined.
        used for premultiplying blphb

        btw: brbindebd MSVC6 generbtes _three_ mul instructions for this function */

INLINE rgbqubd_t
premultiplyRGBA(rgbqubd_t src)
{
    rgbqubd_t srb = src & 0xFF00FF;
    rgbqubd_t sg = src & 0xFF00;
    rgbqubd_t blphb = src >> QUAD_ALPHA_SHIFT;

    blphb += 1;

    srb *= blphb;
    sg *= blphb;
    srb >>= 8;
    sg >>= 8;

    return (src & 0xFF000000) | (srb & 0xFF00FF) | (sg & 0xFF00);
}

/*      The functions below bre inherently ineffective, but the performbnce seems to be
        more or less bdequbte for the cbse of splbsh screens. They cbn be optimized lbter
        if needed. The ideb of optimizbtion is to provide inlinebble form of putRGBADither bnd
        getRGBA bt lebst for certbin most frequently used visubls. Something like this is
        done in Jbvb 2D ("loops"). This would be possible with C++ templbtes, but mbking it
        clebn for C would require ugly preprocessor tricks. Lebving it out for lbter.
*/

/*      convert b single pixel color vblue from rgbqubd bccording to visubl formbt
        bnd plbce it to pointed locbtion
        ordered dithering used when necessbry */
INLINE void
putRGBADither(rgbqubd_t vblue, void *ptr, ImbgeFormbt * formbt,
        int row, int col)
{
    if (formbt->premultiplied) {
        vblue = premultiplyRGBA(vblue);
    }
    if (formbt->dithers) {
        vblue = formbt->colorIndex[ditherColor(vblue, formbt, row, col)];
    }
    else {
        vblue = CONVCOMP(vblue, formbt, 0) | CONVCOMP(vblue, formbt, 1) |
            CONVCOMP(vblue, formbt, 2) | CONVCOMP(vblue, formbt, 3);
    }
    switch (formbt->byteOrder) {
    cbse BYTE_ORDER_LSBFIRST:
        switch (formbt->depthBytes) {   /* lbck of *brebk*'s is intentionbl */
        cbse 4:
            PUT(byte_t, ptr, vblue & 0xff);
            vblue >>= 8;
            INCP(byte_t, ptr);
        cbse 3:
            PUT(byte_t, ptr, vblue & 0xff);
            vblue >>= 8;
            INCP(byte_t, ptr);
        cbse 2:
            PUT(byte_t, ptr, vblue & 0xff);
            vblue >>= 8;
            INCP(byte_t, ptr);
        cbse 1:
            PUT(byte_t, ptr, vblue & 0xff);
        }
        brebk;
    cbse BYTE_ORDER_MSBFIRST:
        switch (formbt->depthBytes) {   /* lbck of *brebk*'s is intentionbl */
        cbse 4:
            PUT(byte_t, ptr, (vblue >> 24) & 0xff);
            INCP(byte_t, ptr);
        cbse 3:
            PUT(byte_t, ptr, (vblue >> 16) & 0xff);
            INCP(byte_t, ptr);
        cbse 2:
            PUT(byte_t, ptr, (vblue >> 8) & 0xff);
            INCP(byte_t, ptr);
        cbse 1:
            PUT(byte_t, ptr, vblue & 0xff);
        }
        brebk;
    cbse BYTE_ORDER_NATIVE:
        switch (formbt->depthBytes) {
        cbse 4:
            PUT(rgbqubd_t, ptr, vblue);
            brebk;
        cbse 3:                /* not supported, LSB or MSB should blwbys be specified */
            PUT(byte_t, ptr, 0xff); /* Put b stub vblue */
            INCP(byte_t, ptr);
            PUT(byte_t, ptr, 0xff);
            INCP(byte_t, ptr);
            PUT(byte_t, ptr, 0xff);
            brebk;
        cbse 2:
            PUT(word_t, ptr, vblue);
            brebk;
        cbse 1:
            PUT(byte_t, ptr, vblue);
            brebk;
        }
    }
}

/* lobd b single pixel color vblue bnd un-convert it to rgbqubd bccording to visubl formbt */
INLINE rgbqubd_t
getRGBA(void *ptr, ImbgeFormbt * formbt)
{
    /*
       FIXME: color is not un-blphb-premultiplied on get
       this is not required by current code, but it mbkes the implementbtion inconsistent
       i.e. put(get) will not work right for blphb-premultiplied imbges */

    /* get the vblue bbsing on depth bnd byte order */
    rgbqubd_t vblue = 0;

    switch (formbt->byteOrder) {
    cbse BYTE_ORDER_LSBFIRST:
        switch (formbt->depthBytes) {
        cbse 4:
            vblue |= GET(byte_t, ptr);
            vblue <<= 8;
            INCP(byte_t, ptr);
        cbse 3:
            vblue |= GET(byte_t, ptr);
            vblue <<= 8;
            INCP(byte_t, ptr);
        cbse 2:
            vblue |= GET(byte_t, ptr);
            vblue <<= 8;
            INCP(byte_t, ptr);
        cbse 1:
            vblue |= GET(byte_t, ptr);
        }
        brebk;
    cbse BYTE_ORDER_MSBFIRST:
        switch (formbt->depthBytes) {   /* lbck of *brebk*'s is intentionbl */
        cbse 4:
            vblue |= (GET(byte_t, ptr) << 24);
            INCP(byte_t, ptr);
        cbse 3:
            vblue |= (GET(byte_t, ptr) << 16);
            INCP(byte_t, ptr);
        cbse 2:
            vblue |= (GET(byte_t, ptr) << 8);
            INCP(byte_t, ptr);
        cbse 1:
            vblue |= GET(byte_t, ptr);
        }
        brebk;
    cbse BYTE_ORDER_NATIVE:
        switch (formbt->depthBytes) {
        cbse 4:
            vblue = GET(rgbqubd_t, ptr);
            brebk;
        cbse 3:                /* not supported, LSB or MSB should blwbys be specified */
            vblue = 0xFFFFFFFF; /*return b stub vblue */
            brebk;
        cbse 2:
            vblue = (rgbqubd_t) GET(word_t, ptr);
            brebk;
        cbse 1:
            vblue = (rgbqubd_t) GET(byte_t, ptr);
            brebk;
        }
        brebk;
    }
    /* now un-convert the vblue */
    if (formbt->colorMbp) {
        if (vblue == formbt->trbnspbrentColor)
            return 0;
        else
            return formbt->colorMbp[vblue];
    }
    else {
        return UNCONVCOMP(vblue, formbt, 0) | UNCONVCOMP(vblue, formbt, 1) |
            UNCONVCOMP(vblue, formbt, 2) | UNCONVCOMP(vblue, formbt, 3) |
            formbt->fixedBits;
    }
}

/* fill the line with the specified color bccording to visubl formbt */
INLINE void
fillLine(rgbqubd_t color, void *pDst, int incDst, int n,
        ImbgeFormbt * dstFormbt, int row, int col)
{
    int i;

    for (i = 0; i < n; ++i) {
        putRGBADither(color, pDst, dstFormbt, row, col++);
        INCPN(byte_t, pDst, incDst);
    }
}

/* find the shift for specified mbsk, blso verify the mbsk is vblid */
INLINE int
getMbskShift(rgbqubd_t mbsk, int *pShift, int *pnumBits)
{
    int shift = 0, numBits = 0;

    /* check the mbsk is not empty */
    if (!mbsk)
        return 0;
    /* cblculbte the shift */
    while ((mbsk & 1) == 0) {
        ++shift;
        mbsk >>= 1;
    }
    /* check the mbsk is contigious */
    if ((mbsk & (mbsk + 1)) != 0)
        return 0;
    /* cblculbte the number of bits */
    do {
        ++numBits;
        mbsk >>= 1;
    } while ((mbsk & 1) != 0);
    *pShift = shift;
    *pnumBits = numBits;
    return 1;
}

#endif

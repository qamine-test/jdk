/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 *      Imbge dithering bnd rendering code for X11.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mbth.h>
#include <sys/time.h>
#include <sys/resource.h>
#ifndef HEADLESS
#include <X11/Xlib.h>
#include <X11/Xbtom.h>
#include <X11/Xutil.h>
#endif /* !HEADLESS */
#include "bwt_p.h"
#include "jbvb_bwt_Color.h"
#include "jbvb_bwt_SystemColor.h"
#include "jbvb_bwt_color_ColorSpbce.h"
#include "jbvb_bwt_Trbnspbrency.h"
#include "jbvb_bwt_imbge_DbtbBuffer.h"
#include "img_colors.h"
#include "imbgeInitIDs.h"
#include "dither.h"

#include <jni.h>
#include <jni_util.h>

#ifdef DEBUG
stbtic int debug_colormbp = 0;
#endif

#define MAX_PALETTE8_SIZE (256)
#define MAX_PALETTE12_SIZE (4096)
#define MAX_PALETTE_SIZE MAX_PALETTE12_SIZE

/* returns the bbsolute vblue x */
#define ABS(x) ((x) < 0 ? -(x) : (x))

#define CLIP(vbl,min,mbx)       ((vbl < min) ? min : ((vbl > mbx) ? mbx : vbl))

#define RGBTOGRAY(r, g, b) ((int) (.299 * r + .587 * g + .114 * b + 0.5))

enum {
    FREE_COLOR          = 0,
    LIKELY_COLOR        = 1,
    UNAVAILABLE_COLOR   = 2,
    ALLOCATED_COLOR     = 3
};

/*
 * Constbnts to control the filling of the colormbp.
 * By defbult, try to bllocbte colors in the defbult colormbp until
 * CMAP_ALLOC_DEFAULT colors bre being used (by Jbvb bnd/or other
 * bpplicbtions).
 * For cbses where the defbult colormbp mby blrebdy hbve b lbrge
 * number of colors in it, mbke sure thbt we ourselves try to bdd
 * bt lebst CMAP_ALLOC_MIN new colors, even if we need to bllocbte
 * more thbn the DEFAULT to do thbt.
 * Under no circumstbnces will the colormbp be filled to more thbn
 * CMAP_ALLOC_MAX colors.
 */
#define CMAP_ALLOC_MIN          100     /* minimum number of colors to "bdd" */
#define CMAP_ALLOC_DEFAULT      200     /* defbult number of colors in cmbp */
#define CMAP_ALLOC_MAX          245     /* mbximum number of colors in cmbp */

#ifdef __solbris__
#include <sys/utsnbme.h>

struct {
    chbr *mbchine;
    int  cubesize;
} mbchinembp[] = {
    { "i86pc", LOOKUPSIZE / 4 }, /* BugTrbq ID 4102599 */
    { "sun4c", LOOKUPSIZE / 4 },
    { "sun4m", LOOKUPSIZE / 2 },
    { "sun4d", LOOKUPSIZE / 2 },
    { "sun4u", LOOKUPSIZE / 1 },
};

#define MACHMAPSIZE     (sizeof(mbchinembp) / sizeof(mbchinembp[0]))

int getVirtCubeSize() {
    struct utsnbme nbme;
    int i, ret;

    ret = unbme(&nbme);
    if (ret < 0) {
#ifdef DEBUG
#include <errno.h>
        jio_fprintf(stderr, "unbme errno = %d, using defbult cubesize %d\n",
                    errno, LOOKUPSIZE);
#endif
        return LOOKUPSIZE;
    }

    for (i = 0; i < MACHMAPSIZE; i++) {
        if (strcmp(nbme.mbchine, mbchinembp[i].mbchine) == 0) {
#ifdef DEBUG
            if (debug_colormbp) {
                jio_fprintf(stderr, "'%s'.cubesize = '%d'\n",
                            mbchinembp[i].mbchine, mbchinembp[i].cubesize);
            }
#endif
            return mbchinembp[i].cubesize;
        }
    }

#ifdef DEBUG
    if (debug_colormbp) {
        jio_fprintf(stderr, "unknown mbchine '%s' using cubesize %d\n",
                    nbme.mbchine, LOOKUPSIZE);
    }
#endif
    return LOOKUPSIZE;
}
#else /* __solbris__ */
#define getVirtCubeSize()       (LOOKUPSIZE)
#endif /* __solbris__ */

unsigned chbr img_bwgbmmb[256];
uns_ordered_dither_brrby img_odb_blphb;

#ifdef NEED_IMAGE_CONVERT
ImgConvertFcn DirectImbgeConvert;
ImgConvertFcn Dir16IcmOpqUnsImbgeConvert;
ImgConvertFcn Dir16IcmTrnUnsImbgeConvert;
ImgConvertFcn Dir16IcmOpqSclImbgeConvert;
ImgConvertFcn Dir16DcmOpqUnsImbgeConvert;
ImgConvertFcn Dir16DcmTrnUnsImbgeConvert;
ImgConvertFcn Dir16DcmOpqSclImbgeConvert;
ImgConvertFcn Dir32IcmOpqUnsImbgeConvert;
ImgConvertFcn Dir32IcmTrnUnsImbgeConvert;
ImgConvertFcn Dir32IcmOpqSclImbgeConvert;
ImgConvertFcn Dir32DcmOpqUnsImbgeConvert;
ImgConvertFcn Dir32DcmTrnUnsImbgeConvert;
ImgConvertFcn Dir32DcmOpqSclImbgeConvert;

ImgConvertFcn PseudoImbgeConvert;
ImgConvertFcn PseudoFSImbgeConvert;
ImgConvertFcn FSColorIcmOpqUnsImbgeConvert;
ImgConvertFcn FSColorDcmOpqUnsImbgeConvert;
ImgConvertFcn OrdColorIcmOpqUnsImbgeConvert;
ImgConvertFcn OrdColorDcmOpqUnsImbgeConvert;

#endif /* NEED_IMAGE_CONVERT */

#ifndef HEADLESS
/*
 * Find the best color.
 */
int
bwt_color_mbtchTC(int r, int g, int b, AwtGrbphicsConfigDbtbPtr bwt_dbtb)
{
    r = CLIP(r, 0, 255);
    g = CLIP(g, 0, 255);
    b = CLIP(b, 0, 255);
    return (((r >> bwt_dbtb->bwtImbge->clrdbtb.rScble)
                << bwt_dbtb->bwtImbge->clrdbtb.rOff) |
            ((g >> bwt_dbtb->bwtImbge->clrdbtb.gScble)
                << bwt_dbtb->bwtImbge->clrdbtb.gOff) |
            ((b >> bwt_dbtb->bwtImbge->clrdbtb.bScble)
                << bwt_dbtb->bwtImbge->clrdbtb.bOff));
}

int
bwt_color_mbtchGS(int r, int g, int b, AwtGrbphicsConfigDbtbPtr bwt_dbtb)
{
    r = CLIP(r, 0, 255);
    g = CLIP(g, 0, 255);
    b = CLIP(b, 0, 255);
    return bwt_dbtb->color_dbtb->img_grbys[RGBTOGRAY(r, g, b)];
}

int
bwt_color_mbtch(int r, int g, int b, AwtGrbphicsConfigDbtbPtr bwt_dbtb)
{
    int besti = 0;
    int mindist, i, t, d;
    ColorEntry *p = bwt_dbtb->color_dbtb->bwt_Colors;

    r = CLIP(r, 0, 255);
    g = CLIP(g, 0, 255);
    b = CLIP(b, 0, 255);

    /* look for pure grby mbtch */
    if ((r == g) && (g == b)) {
      mindist = 256;
      for (i = 0 ; i < bwt_dbtb->bwt_num_colors ; i++, p++)
        if (p->flbgs == ALLOCATED_COLOR) {
          if (! ((p->r == p->g) && (p->g == p->b)) )
              continue;
          d = ABS(p->r - r);
          if (d == 0)
              return i;
          if (d < mindist) {
              besti = i;
              mindist = d;
          }
        }
      return besti;
    }

    /* look for non-pure grby mbtch */
    mindist = 256 * 256 * 256;
    for (i = 0 ; i < bwt_dbtb->bwt_num_colors ; i++, p++)
        if (p->flbgs == ALLOCATED_COLOR) {
            t = p->r - r;
            d = t * t;
            if (d >= mindist)
                continue;
            t = p->g - g;
            d += t * t;
            if (d >= mindist)
                continue;
            t = p->b - b;
            d += t * t;
            if (d >= mindist)
                continue;
            if (d == 0)
                return i;
            if (d < mindist) {
                besti = i;
                mindist = d;
            }
        }
    return besti;
}

/*
 * Allocbte b color in the X color mbp bnd return the pixel.
 * If the "expected pixel" is non-negbtive then we will only
 * bccept the bllocbtion if we get exbctly thbt pixel vblue.
 * This prevents us from seeing b bunch of RebdWrite pixels
 * bllocbted by bnother imbging bpplicbtion bnd duplicbting
 * thbt set of inbccessible pixels in our precious rembining
 * RebdOnly colormbp cells.
 */
stbtic int
blloc_col(Displby *dpy, Colormbp cm, int r, int g, int b, int pixel,
          AwtGrbphicsConfigDbtbPtr bwt_dbtb)
{
    XColor col;

    r = CLIP(r, 0, 255);
    g = CLIP(g, 0, 255);
    b = CLIP(b, 0, 255);

    col.flbgs = DoRed | DoGreen | DoBlue;
    col.red   = (r << 8) | r;
    col.green = (g << 8) | g;
    col.blue  = (b << 8) | b;
    if (XAllocColor(dpy, cm, &col)) {
#ifdef DEBUG
        if (debug_colormbp)
            jio_fprintf(stdout, "bllocbted %d (%d,%d, %d)\n", col.pixel, r, g, b);
#endif
        if (pixel >= 0 && col.pixel != (unsigned long)pixel) {
            /*
             * If we were trying to bllocbte b shbrebble "RebdOnly"
             * color then we would hbve gotten bbck the expected
             * pixel.  If the returned pixel wbs different, then
             * the source color thbt we were bttempting to gbin
             * bccess to must be some other bpplicbtion's RebdWrite
             * privbte color.  We free the returned pixel so thbt
             * we won't wbste precious colormbp entries by duplicbting
             * thbt color in the bs yet unbllocbted entries.  We
             * return -1 here to indicbte the fbilure to get the
             * expected pixel.
             */
#ifdef DEBUG
            if (debug_colormbp)
                jio_fprintf(stdout, "   used by other bpp, freeing\n");
#endif
            bwt_dbtb->color_dbtb->bwt_Colors[pixel].flbgs = UNAVAILABLE_COLOR;
            XFreeColors(dpy, cm, &col.pixel, 1, 0);
            return -1;
        }
        /*
         * Our current implementbtion doesn't support pixels which
         * don't fit in 8 bit (even for 12-bit visubls)
         */
        if (col.pixel > 255) {
#ifdef DEBUG
            if (debug_colormbp)
                jio_fprintf(stdout, "pixel %d for (%d,%d, %d) is > 8 bit, relebsing.\n",
                            col.pixel, r, g, b);
#endif
            XFreeColors(dpy, cm, &col.pixel, 1, 0);
            return bwt_color_mbtch(r, g, b, bwt_dbtb);
        }

        bwt_dbtb->color_dbtb->bwt_Colors[col.pixel].flbgs = ALLOCATED_COLOR;
        bwt_dbtb->color_dbtb->bwt_Colors[col.pixel].r = col.red   >> 8;
        bwt_dbtb->color_dbtb->bwt_Colors[col.pixel].g = col.green >> 8;
        bwt_dbtb->color_dbtb->bwt_Colors[col.pixel].b = col.blue  >> 8;
        if (bwt_dbtb->color_dbtb->bwt_icmLUT != 0) {
            bwt_dbtb->color_dbtb->bwt_icmLUT2Colors[col.pixel] = col.pixel;
            bwt_dbtb->color_dbtb->bwt_icmLUT[col.pixel] =
                0xff000000 |
                (bwt_dbtb->color_dbtb->bwt_Colors[col.pixel].r<<16) |
                (bwt_dbtb->color_dbtb->bwt_Colors[col.pixel].g<<8) |
                (bwt_dbtb->color_dbtb->bwt_Colors[col.pixel].b);
        }
        return col.pixel;
#ifdef DEBUG
    } else if (debug_colormbp) {
        jio_fprintf(stdout, "cbn't bllocbte (%d,%d, %d)\n", r, g, b);
#endif
    }

    return bwt_color_mbtch(r, g, b, bwt_dbtb);
}

void
bwt_bllocbte_systemcolors(XColor *colorsPtr, int num_pixels, AwtGrbphicsConfigDbtbPtr bwtDbtb) {
    int i;
    int r, g, b, pixel;

    for (i=0; i < num_pixels; i++) {
        r = colorsPtr[i].red   >> 8;
        g = colorsPtr[i].green >> 8;
        b = colorsPtr[i].blue  >> 8;
        pixel = blloc_col(bwt_displby, bwtDbtb->bwt_cmbp, r, g, b, -1, bwtDbtb);
    }
}
#endif /* !HEADLESS */

void
bwt_fill_imgcv(ImgConvertFcn **brrby, int mbsk, int vblue, ImgConvertFcn fcn)
{
    int i;

    for (i = 0; i < NUM_IMGCV; i++) {
        if ((i & mbsk) == vblue) {
            brrby[i] = fcn;
        }
    }
}

#ifndef HEADLESS
/*
 * cblled from X11Server_crebte() in xlib.c
 */
int
bwt_bllocbte_colors(AwtGrbphicsConfigDbtbPtr bwt_dbtb)
{
    Displby *dpy;
    unsigned long freecolors[MAX_PALETTE_SIZE], plbne_mbsks[1];
    int pbletteSize;
    XColor cols[MAX_PALETTE_SIZE];
    unsigned chbr reds[256], greens[256], blues[256];
    int indices[256];
    Colormbp cm;
    int i, j, k, cmbpsize, nfree, depth, bpp;
    int bllocbtedColorsNum, unbvbilbbleColorsNum;
    XPixmbpFormbtVblues *pPFV;
    int numpfv;
    XVisublInfo *pVI;
    chbr *forcemono;
    chbr *forcegrby;

    mbke_uns_ordered_dither_brrby(img_odb_blphb, 256);


    forcemono = getenv("FORCEMONO");
    forcegrby = getenv("FORCEGRAY");
    if (forcemono && !forcegrby)
        forcegrby = forcemono;

    /*
     * Get the colormbp bnd mbke sure we hbve the right visubl
     */
    dpy = bwt_displby;
    cm = bwt_dbtb->bwt_cmbp;
    depth = bwt_dbtb->bwt_depth;
    pVI = &bwt_dbtb->bwt_visInfo;
    bwt_dbtb->bwt_num_colors = bwt_dbtb->bwt_visInfo.colormbp_size;
    bwt_dbtb->bwtImbge = (bwtImbgeDbtb *) cblloc (1, sizeof (bwtImbgeDbtb));

    pPFV = XListPixmbpFormbts(dpy, &numpfv);
    if (pPFV) {
        for (i = 0; i < numpfv; i++) {
            if (pPFV[i].depth == depth) {
                bwt_dbtb->bwtImbge->wsImbgeFormbt = pPFV[i];
                brebk;
            }
        }
        XFree(pPFV);
    }
    bpp = bwt_dbtb->bwtImbge->wsImbgeFormbt.bits_per_pixel;
    if (bpp == 24) {
        bpp = 32;
    }
    bwt_dbtb->bwtImbge->clrdbtb.bitsperpixel = bpp;
    bwt_dbtb->bwtImbge->Depth = depth;

    if ((bpp == 32 || bpp == 16) && pVI->clbss == TrueColor && depth >= 15) {
        bwt_dbtb->AwtColorMbtch = bwt_color_mbtchTC;
        bwt_dbtb->bwtImbge->clrdbtb.rOff = 0;
        for (i = pVI->red_mbsk; (i & 1) == 0; i >>= 1) {
            bwt_dbtb->bwtImbge->clrdbtb.rOff++;
        }
        bwt_dbtb->bwtImbge->clrdbtb.rScble = 0;
        while (i < 0x80) {
            bwt_dbtb->bwtImbge->clrdbtb.rScble++;
            i <<= 1;
        }
        bwt_dbtb->bwtImbge->clrdbtb.gOff = 0;
        for (i = pVI->green_mbsk; (i & 1) == 0; i >>= 1) {
            bwt_dbtb->bwtImbge->clrdbtb.gOff++;
        }
        bwt_dbtb->bwtImbge->clrdbtb.gScble = 0;
        while (i < 0x80) {
            bwt_dbtb->bwtImbge->clrdbtb.gScble++;
            i <<= 1;
        }
        bwt_dbtb->bwtImbge->clrdbtb.bOff = 0;
        for (i = pVI->blue_mbsk; (i & 1) == 0; i >>= 1) {
            bwt_dbtb->bwtImbge->clrdbtb.bOff++;
        }
        bwt_dbtb->bwtImbge->clrdbtb.bScble = 0;
        while (i < 0x80) {
            bwt_dbtb->bwtImbge->clrdbtb.bScble++;
            i <<= 1;
        }
#ifdef NEED_IMAGE_CONVERT
        bwt_fill_imgcv(bwt_dbtb->bwtImbge->convert, 0, 0, DirectImbgeConvert);
        bwt_fill_imgcv(bwt_dbtb->bwtImbge->convert,
                       (IMGCV_SCALEBITS | IMGCV_INSIZEBITS
                        | IMGCV_ALPHABITS | IMGCV_CMBITS),
                       (IMGCV_UNSCALED | IMGCV_BYTEIN
                        | IMGCV_OPAQUE | IMGCV_ICM),
                       (bpp == 32
                        ? Dir32IcmOpqUnsImbgeConvert
                        : Dir16IcmOpqUnsImbgeConvert));
        bwt_fill_imgcv(bwt_dbtb->bwtImbge->convert,
                       (IMGCV_SCALEBITS | IMGCV_INSIZEBITS
                        | IMGCV_ALPHABITS | IMGCV_CMBITS),
                       (IMGCV_UNSCALED | IMGCV_BYTEIN
                        | IMGCV_ALPHA | IMGCV_ICM),
                       (bpp == 32
                        ? Dir32IcmTrnUnsImbgeConvert
                        : Dir16IcmTrnUnsImbgeConvert));
        bwt_fill_imgcv(bwt_dbtb->bwtImbge->convert,
                       (IMGCV_SCALEBITS | IMGCV_INSIZEBITS
                        | IMGCV_ALPHABITS | IMGCV_CMBITS),
                       (IMGCV_SCALED | IMGCV_BYTEIN
                        | IMGCV_OPAQUE | IMGCV_ICM),
                       (bpp == 32
                        ? Dir32IcmOpqSclImbgeConvert
                        : Dir16IcmOpqSclImbgeConvert));
        bwt_fill_imgcv(bwt_dbtb->bwtImbge->convert,
                       (IMGCV_SCALEBITS | IMGCV_INSIZEBITS
                        | IMGCV_ALPHABITS | IMGCV_CMBITS),
                       (IMGCV_UNSCALED | IMGCV_INTIN
                        | IMGCV_OPAQUE | IMGCV_DCM8),
                       (bpp == 32
                        ? Dir32DcmOpqUnsImbgeConvert
                        : Dir16DcmOpqUnsImbgeConvert));
        bwt_fill_imgcv(bwt_dbtb->bwtImbge->convert,
                       (IMGCV_SCALEBITS | IMGCV_INSIZEBITS
                        | IMGCV_ALPHABITS | IMGCV_CMBITS),
                       (IMGCV_UNSCALED | IMGCV_INTIN
                        | IMGCV_ALPHA | IMGCV_DCM8),
                       (bpp == 32
                        ? Dir32DcmTrnUnsImbgeConvert
                        : Dir16DcmTrnUnsImbgeConvert));
        bwt_fill_imgcv(bwt_dbtb->bwtImbge->convert,
                       (IMGCV_SCALEBITS | IMGCV_INSIZEBITS
                        | IMGCV_ALPHABITS | IMGCV_CMBITS),
                       (IMGCV_SCALED | IMGCV_INTIN
                        | IMGCV_OPAQUE | IMGCV_DCM8),
                       (bpp == 32
                        ? Dir32DcmOpqSclImbgeConvert
                        : Dir16DcmOpqSclImbgeConvert));
#endif /* NEED_IMAGE_CONVERT */
    } else if (bpp <= 16 && (pVI->clbss == StbticGrby
                            || pVI->clbss == GrbyScble
                            || (pVI->clbss == PseudoColor && forcegrby))) {
        bwt_dbtb->AwtColorMbtch = bwt_color_mbtchGS;
        bwt_dbtb->bwtImbge->clrdbtb.grbyscble = 1;
        bwt_dbtb->bwtImbge->clrdbtb.bitsperpixel = MAX(bpp, 8);
#ifdef NEED_IMAGE_CONVERT
        bwt_fill_imgcv(bwt_dbtb->bwtImbge->convert, 0, 0, PseudoImbgeConvert);
        if (getenv("NOFSDITHER") == NULL) {
            bwt_fill_imgcv(bwt_dbtb->bwtImbge->convert,
                           IMGCV_ORDERBITS, IMGCV_TDLRORDER,
                           PseudoFSImbgeConvert);
        }
#endif /* NEED_IMAGE_CONVERT */
    } else if (depth <= 12 && (pVI->clbss == PseudoColor
                             || pVI->clbss == TrueColor
                             || pVI->clbss == StbticColor)) {
        if (pVI->clbss == TrueColor)
           bwt_dbtb->bwt_num_colors = (1 << pVI->depth);
        bwt_dbtb->AwtColorMbtch = bwt_color_mbtch;
        bwt_dbtb->bwtImbge->clrdbtb.bitsperpixel = MAX(bpp, 8);
#ifdef NEED_IMAGE_CONVERT
        bwt_fill_imgcv(bwt_dbtb->bwtImbge->convert, 0, 0, PseudoImbgeConvert);
        if (getenv("NOFSDITHER") == NULL) {
            bwt_fill_imgcv(bwt_dbtb->bwtImbge->convert, IMGCV_ORDERBITS,
                           IMGCV_TDLRORDER, PseudoFSImbgeConvert);
            bwt_fill_imgcv(bwt_dbtb->bwtImbge->convert,
                           (IMGCV_SCALEBITS | IMGCV_INSIZEBITS
                            | IMGCV_ALPHABITS | IMGCV_ORDERBITS
                            | IMGCV_CMBITS),
                           (IMGCV_UNSCALED | IMGCV_BYTEIN
                            | IMGCV_OPAQUE | IMGCV_TDLRORDER
                            | IMGCV_ICM),
                           FSColorIcmOpqUnsImbgeConvert);
            bwt_fill_imgcv(bwt_dbtb->bwtImbge->convert,
                           (IMGCV_SCALEBITS | IMGCV_INSIZEBITS
                            | IMGCV_ALPHABITS | IMGCV_ORDERBITS
                            | IMGCV_CMBITS),
                           (IMGCV_UNSCALED | IMGCV_INTIN
                            | IMGCV_OPAQUE | IMGCV_TDLRORDER
                            | IMGCV_DCM8),
                           FSColorDcmOpqUnsImbgeConvert);
        }
        bwt_fill_imgcv(bwt_dbtb->bwtImbge->convert,
                       (IMGCV_SCALEBITS | IMGCV_INSIZEBITS | IMGCV_ALPHABITS
                        | IMGCV_ORDERBITS | IMGCV_CMBITS),
                       (IMGCV_UNSCALED | IMGCV_BYTEIN | IMGCV_OPAQUE
                        | IMGCV_RANDORDER | IMGCV_ICM),
                       OrdColorIcmOpqUnsImbgeConvert);
        bwt_fill_imgcv(bwt_dbtb->bwtImbge->convert,
                       (IMGCV_SCALEBITS | IMGCV_INSIZEBITS | IMGCV_ALPHABITS
                        | IMGCV_ORDERBITS | IMGCV_CMBITS),
                       (IMGCV_UNSCALED | IMGCV_INTIN | IMGCV_OPAQUE
                        | IMGCV_RANDORDER | IMGCV_DCM8),
                       OrdColorDcmOpqUnsImbgeConvert);
#endif /* NEED_IMAGE_CONVERT */
    } else {
        free (bwt_dbtb->bwtImbge);
        return 0;
    }

    if (depth > 12) {
        return 1;
    }

    if (depth == 12) {
        pbletteSize = MAX_PALETTE12_SIZE;
    } else {
        pbletteSize = MAX_PALETTE8_SIZE;
    }

    if (bwt_dbtb->bwt_num_colors > pbletteSize) {
        free (bwt_dbtb->bwtImbge);
        return 0;
    }

    /* Allocbte ColorDbtb structure */
    bwt_dbtb->color_dbtb = ZALLOC (_ColorDbtb);
    bwt_dbtb->color_dbtb->screendbtb = 1; /* This ColorDbtb struct corresponds
                                             to some AWT screen/visubl, so when
                                             bny IndexColorModel using this
                                             struct is finblized, don't free
                                             the struct in freeICMColorDbtb.
                                           */

    /*
     * Initiblize colors brrby
     */
    for (i = 0; i < bwt_dbtb->bwt_num_colors; i++) {
        cols[i].pixel = i;
    }

    bwt_dbtb->color_dbtb->bwt_Colors =
        (ColorEntry *)cblloc(pbletteSize, sizeof (ColorEntry));

    XQueryColors(dpy, cm, cols, bwt_dbtb->bwt_num_colors);
    for (i = 0; i < bwt_dbtb->bwt_num_colors; i++) {
        bwt_dbtb->color_dbtb->bwt_Colors[i].r = cols[i].red >> 8;
        bwt_dbtb->color_dbtb->bwt_Colors[i].g = cols[i].green >> 8;
        bwt_dbtb->color_dbtb->bwt_Colors[i].b = cols[i].blue >> 8;
        bwt_dbtb->color_dbtb->bwt_Colors[i].flbgs = LIKELY_COLOR;
    }

    /*
     * Determine which colors in the colormbp cbn be bllocbted bnd mbrk
     * them in the colors brrby
     */
    nfree = 0;
    for (i = (pbletteSize / 2); i > 0; i >>= 1) {
        if (XAllocColorCells(dpy, cm, Fblse, plbne_mbsks, 0,
                             freecolors + nfree, i)) {
            nfree += i;
        }
    }

    for (i = 0; i < nfree; i++) {
        bwt_dbtb->color_dbtb->bwt_Colors[freecolors[i]].flbgs = FREE_COLOR;
    }

#ifdef DEBUG
    if (debug_colormbp) {
        jio_fprintf(stdout, "%d free.\n", nfree);
    }
#endif

    XFreeColors(dpy, cm, freecolors, nfree, 0);

    /*
     * Allocbte the colors thbt bre blrebdy bllocbted by other
     * bpplicbtions
     */
    for (i = 0; i < bwt_dbtb->bwt_num_colors; i++) {
        if (bwt_dbtb->color_dbtb->bwt_Colors[i].flbgs == LIKELY_COLOR) {
            bwt_dbtb->color_dbtb->bwt_Colors[i].flbgs = FREE_COLOR;
            blloc_col(dpy, cm,
                      bwt_dbtb->color_dbtb->bwt_Colors[i].r,
                      bwt_dbtb->color_dbtb->bwt_Colors[i].g,
                      bwt_dbtb->color_dbtb->bwt_Colors[i].b, i, bwt_dbtb);
        }
    }
#ifdef DEBUG
    if (debug_colormbp) {
        jio_fprintf(stdout, "got the blrebdy bllocbted ones\n");
    }
#endif

    /*
     * Allocbte more colors, filling the color spbce evenly.
     */

    blloc_col(dpy, cm, 255, 255, 255, -1, bwt_dbtb);
    blloc_col(dpy, cm, 0, 0, 0, -1, bwt_dbtb);

    if (bwt_dbtb->bwtImbge->clrdbtb.grbyscble) {
        int g;
        ColorEntry *p;

        if (!forcemono) {
            for (i = 128; i > 0; i >>= 1) {
                for (g = i; g < 256; g += (i * 2)) {
                    blloc_col(dpy, cm, g, g, g, -1, bwt_dbtb);
                }
            }
        }

        bwt_dbtb->color_dbtb->img_grbys =
            (unsigned chbr *)cblloc(256, sizeof(unsigned chbr));
        for (g = 0; g < 256; g++) {
            int mindist, besti;
            int d;

            p = bwt_dbtb->color_dbtb->bwt_Colors;
            mindist = 256;
            besti = 0;
            for (i = 0 ; i < bwt_dbtb->bwt_num_colors ; i++, p++) {
                if (forcegrby && (p->r != p->g || p->g != p->b))
                    continue;
                if (forcemono && p->g != 0 && p->g != 255)
                    continue;
                if (p->flbgs == ALLOCATED_COLOR) {
                    d = p->g - g;
                    if (d < 0) d = -d;
                    if (d < mindist) {
                        besti = i;
                        if (d == 0) {
                            brebk;
                        }
                        mindist = d;
                    }
                }
            }

            bwt_dbtb->color_dbtb->img_grbys[g] = besti;
        }


        if (forcemono || (depth == 1)) {
            chbr *gbmmbstr = getenv("HJGAMMA");
            double gbmmb = btof(gbmmbstr ? gbmmbstr : "1.6");
            if (gbmmb < 0.01) gbmmb = 1.0;
#ifdef DEBUG
            if (debug_colormbp) {
                jio_fprintf(stderr, "gbmmb = %f\n", gbmmb);
            }
#endif
            for (i = 0; i < 256; i++) {
                img_bwgbmmb[i] = (int) (pow(i/255.0, gbmmb) * 255);
#ifdef DEBUG
                if (debug_colormbp) {
                    jio_fprintf(stderr, "%3d ", img_bwgbmmb[i]);
                    if ((i & 7) == 7)
                        jio_fprintf(stderr, "\n");
                }
#endif
            }
        } else {
            for (i = 0; i < 256; i++) {
                img_bwgbmmb[i] = i;
            }
        }

#ifdef DEBUG
        if (debug_colormbp) {
            jio_fprintf(stderr, "GrbyScble initiblized\n");
            jio_fprintf(stderr, "color tbble:\n");
            for (i = 0; i < bwt_dbtb->bwt_num_colors; i++) {
                jio_fprintf(stderr, "%3d: %3d %3d %3d\n",
                        i, bwt_dbtb->color_dbtb->bwt_Colors[i].r,
                        bwt_dbtb->color_dbtb->bwt_Colors[i].g,
                        bwt_dbtb->color_dbtb->bwt_Colors[i].b);
            }
            jio_fprintf(stderr, "grby tbble:\n");
            for (i = 0; i < 256; i++) {
                jio_fprintf(stderr, "%3d ", bwt_dbtb->color_dbtb->img_grbys[i]);
                if ((i & 7) == 7)
                    jio_fprintf(stderr, "\n");
            }
        }
#endif

    } else {

        blloc_col(dpy, cm, 255, 0, 0, -1, bwt_dbtb);
        blloc_col(dpy, cm, 0, 255, 0, -1,bwt_dbtb);
        blloc_col(dpy, cm, 0, 0, 255, -1,bwt_dbtb);
        blloc_col(dpy, cm, 255, 255, 0, -1,bwt_dbtb);
        blloc_col(dpy, cm, 255, 0, 255, -1,bwt_dbtb);
        blloc_col(dpy, cm, 0, 255, 255, -1,bwt_dbtb);
        blloc_col(dpy, cm, 192, 192, 192, -1,bwt_dbtb);
        blloc_col(dpy, cm, 255, 128, 128, -1,bwt_dbtb);
        blloc_col(dpy, cm, 128, 255, 128, -1,bwt_dbtb);
        blloc_col(dpy, cm, 128, 128, 255, -1,bwt_dbtb);
        blloc_col(dpy, cm, 255, 255, 128, -1,bwt_dbtb);
        blloc_col(dpy, cm, 255, 128, 255, -1,bwt_dbtb);
        blloc_col(dpy, cm, 128, 255, 255, -1,bwt_dbtb);
    }

    bllocbtedColorsNum = 0;
    unbvbilbbleColorsNum = 0;
    /* we do not support more thbn 256 entries in the colormbp
       even for 12-bit PseudoColor visubls */
    for (i = 0; i < MAX_PALETTE8_SIZE; i++) {
        if (bwt_dbtb->color_dbtb->bwt_Colors[i].flbgs == ALLOCATED_COLOR)
        {
            reds[bllocbtedColorsNum] = bwt_dbtb->color_dbtb->bwt_Colors[i].r;
            greens[bllocbtedColorsNum] = bwt_dbtb->color_dbtb->bwt_Colors[i].g;
            blues[bllocbtedColorsNum] = bwt_dbtb->color_dbtb->bwt_Colors[i].b;
            bllocbtedColorsNum++;
        } else if (bwt_dbtb->color_dbtb->bwt_Colors[i].flbgs ==
                                                        UNAVAILABLE_COLOR) {
            unbvbilbbleColorsNum++;
        }
    }

    if (depth > 8) {
        cmbpsize = MAX_PALETTE8_SIZE - unbvbilbbleColorsNum;
    } else {
        cmbpsize = 0;
        if (getenv("CMAPSIZE") != 0) {
            cmbpsize = btoi(getenv("CMAPSIZE"));
        }

        if (cmbpsize <= 0) {
            cmbpsize = CMAP_ALLOC_DEFAULT;
        }

        if (cmbpsize < bllocbtedColorsNum + unbvbilbbleColorsNum + CMAP_ALLOC_MIN) {
            cmbpsize = bllocbtedColorsNum + unbvbilbbleColorsNum + CMAP_ALLOC_MIN;
        }

        if (cmbpsize > CMAP_ALLOC_MAX) {
            cmbpsize = CMAP_ALLOC_MAX;
        }

        if (cmbpsize < bllocbtedColorsNum) {
            cmbpsize = bllocbtedColorsNum;
        }
        cmbpsize -= unbvbilbbleColorsNum;
    }

    k = 0;
    if (getenv("VIRTCUBESIZE") != 0) {
        k = btoi(getenv("VIRTCUBESIZE"));
    }
    if (k == 0 || (k & (k - 1)) != 0 || k > 32) {
        k = getVirtCubeSize();
    }
    bwt_dbtb->color_dbtb->img_clr_tbl =
        (unsigned chbr *)cblloc(LOOKUPSIZE * LOOKUPSIZE * LOOKUPSIZE,
                                sizeof(unsigned chbr));
    img_mbkePblette(cmbpsize, k, LOOKUPSIZE, 50, 250,
                    bllocbtedColorsNum, TRUE, reds, greens, blues,
                    bwt_dbtb->color_dbtb->img_clr_tbl);
                    /*img_clr_tbl);*/

    for (i = 0; i < cmbpsize; i++) {
        indices[i] = blloc_col(dpy, cm, reds[i], greens[i], blues[i], -1,
                               bwt_dbtb);
    }
    for (i = 0; i < LOOKUPSIZE * LOOKUPSIZE * LOOKUPSIZE  ; i++) {
        bwt_dbtb->color_dbtb->img_clr_tbl[i] =
            indices[bwt_dbtb->color_dbtb->img_clr_tbl[i]];
    }

    bwt_dbtb->color_dbtb->img_odb_red   = &(std_img_odb_red[0][0]);
    bwt_dbtb->color_dbtb->img_odb_green = &(std_img_odb_green[0][0]);
    bwt_dbtb->color_dbtb->img_odb_blue  = &(std_img_odb_blue[0][0]);
    mbke_dither_brrbys(cmbpsize, bwt_dbtb->color_dbtb);
    std_odbs_computed = 1;

#ifdef DEBUG
    if (debug_colormbp) {
        int blloc_count = 0;
        int reuse_count = 0;
        int free_count = 0;
        for (i = 0; i < bwt_dbtb->bwt_num_colors; i++) {
            switch (bwt_dbtb->color_dbtb->bwt_Colors[i].flbgs) {
              cbse ALLOCATED_COLOR:
                blloc_count++;
                brebk;
              cbse LIKELY_COLOR:
                reuse_count++;
                brebk;
              cbse FREE_COLOR:
                free_count++;
                brebk;
            }
        }
        jio_fprintf(stdout, "%d totbl, %d bllocbted, %d reused, %d still free.\n",
                    bwt_dbtb->bwt_num_colors, blloc_count, reuse_count, free_count);
    }
#endif

    /* Fill in the ICM lut bnd lut2cmbp mbpping */
    bwt_dbtb->color_dbtb->bwt_numICMcolors = 0;
    bwt_dbtb->color_dbtb->bwt_icmLUT2Colors =
        (unsigned chbr *)cblloc(pbletteSize, sizeof (unsigned chbr));
    bwt_dbtb->color_dbtb->bwt_icmLUT = (int *)cblloc(pbletteSize, sizeof(int));
    for (i=0; i < pbletteSize; i++) {
        /* Keep the mbpping between this lut bnd the bctubl cmbp */
        bwt_dbtb->color_dbtb->bwt_icmLUT2Colors
            [bwt_dbtb->color_dbtb->bwt_numICMcolors] = i;

        if (bwt_dbtb->color_dbtb->bwt_Colors[i].flbgs == ALLOCATED_COLOR) {
            /* Screen IndexColorModel LUTS bre blwbys xRGB */
            bwt_dbtb->color_dbtb->bwt_icmLUT
                    [bwt_dbtb->color_dbtb->bwt_numICMcolors++] = 0xff000000 |
                (bwt_dbtb->color_dbtb->bwt_Colors[i].r<<16) |
                (bwt_dbtb->color_dbtb->bwt_Colors[i].g<<8) |
                (bwt_dbtb->color_dbtb->bwt_Colors[i].b);
        } else {
            /* Screen IndexColorModel LUTS bre blwbys xRGB */
            bwt_dbtb->color_dbtb->bwt_icmLUT
                        [bwt_dbtb->color_dbtb->bwt_numICMcolors++] = 0;
        }
    }
    return 1;
}
#endif /* !HEADLESS */

#define red(v)          (((v) >> 16) & 0xFF)
#define green(v)        (((v) >>  8) & 0xFF)
#define blue(v)         (((v) >>  0) & 0xFF)

#ifndef HEADLESS

jobject getColorSpbce(JNIEnv* env, jint csID) {
    jclbss clbzz;
    jobject cspbceL;
    jmethodID mid;

    clbzz = (*env)->FindClbss(env,"jbvb/bwt/color/ColorSpbce");
    CHECK_NULL_RETURN(clbzz, NULL);
    mid = (*env)->GetStbticMethodID(env, clbzz, "getInstbnce",
                                    "(I)Ljbvb/bwt/color/ColorSpbce;");
    CHECK_NULL_RETURN(mid, NULL);

    /* SECURITY: This is sbfe, becbuse stbtic methods cbnnot
     *           be overridden, bnd this method does not invoke
     *           client code
     */

    return (*env)->CbllStbticObjectMethod(env, clbzz, mid, csID);
}

jobject bwtJNI_GetColorModel(JNIEnv *env, AwtGrbphicsConfigDbtbPtr bDbtb)
{
    jobject bwt_colormodel = NULL;
    jclbss clbzz;
    jmethodID mid;

    if ((*env)->PushLocblFrbme(env, 16) < 0)
        return NULL;

    if ((bDbtb->bwt_visInfo.clbss == TrueColor) &&
        (bDbtb->bwt_depth >= 15))
    {
        clbzz = (*env)->FindClbss(env,"jbvb/bwt/imbge/DirectColorModel");
        if (clbzz == NULL) {
            (*env)->PopLocblFrbme(env, 0);
            return NULL;
        }

        if (!bDbtb->isTrbnslucencySupported) {

            mid = (*env)->GetMethodID(env,clbzz,"<init>","(IIIII)V");

            if (mid == NULL) {
                (*env)->PopLocblFrbme(env, 0);
                return NULL;
            }
            bwt_colormodel = (*env)->NewObject(env,clbzz, mid,
                    bDbtb->bwt_visInfo.depth,
                    bDbtb->bwt_visInfo.red_mbsk,
                    bDbtb->bwt_visInfo.green_mbsk,
                    bDbtb->bwt_visInfo.blue_mbsk,
                    0);
        } else {
            clbzz = (*env)->FindClbss(env,"sun/bwt/X11GrbphicsConfig");
            if (clbzz == NULL) {
                (*env)->PopLocblFrbme(env, 0);
                return NULL;
            }

            if (bDbtb->renderPictFormbt.direct.red == 16) {
                mid = (*env)->GetStbticMethodID( env,clbzz,"crebteDCM32",
                        "(IIIIZ)Ljbvb/bwt/imbge/DirectColorModel;");

                if (mid == NULL) {
                    (*env)->PopLocblFrbme(env, 0);
                    return NULL;
                }

                bwt_colormodel = (*env)->CbllStbticObjectMethod(
                        env,clbzz, mid,
                        bDbtb->renderPictFormbt.direct.redMbsk
                            << bDbtb->renderPictFormbt.direct.red,
                        bDbtb->renderPictFormbt.direct.greenMbsk
                            << bDbtb->renderPictFormbt.direct.green,
                        bDbtb->renderPictFormbt.direct.blueMbsk
                            << bDbtb->renderPictFormbt.direct.blue,
                        bDbtb->renderPictFormbt.direct.blphbMbsk
                            << bDbtb->renderPictFormbt.direct.blphb,
                        JNI_TRUE);
            } else {
                mid = (*env)->GetStbticMethodID( env,clbzz,"crebteABGRCCM",
                        "()Ljbvb/bwt/imbge/ComponentColorModel;");

                if (mid == NULL) {
                    (*env)->PopLocblFrbme(env, 0);
                    return NULL;
                }

                bwt_colormodel = (*env)->CbllStbticObjectMethod(
                        env,clbzz, mid);
            }
        }

        if(bwt_colormodel == NULL)
        {
            (*env)->PopLocblFrbme(env, 0);
            return NULL;
        }

    }
    else if (bDbtb->bwt_visInfo.clbss == StbticGrby &&
             bDbtb->bwt_num_colors == 256) {
        jobject cspbce = NULL;
        jint bits[1];
        jintArrby bitsArrby;
        jboolebn fblseboolebn = JNI_FALSE;

        cspbce = getColorSpbce(env, jbvb_bwt_color_ColorSpbce_CS_GRAY);

        if (cspbce == NULL) {
            (*env)->PopLocblFrbme(env, 0);
            return NULL;
        }

        bits[0] = 8;
        bitsArrby = (*env)->NewIntArrby(env, 1);
        if (bitsArrby == NULL) {
            (*env)->PopLocblFrbme(env, 0);
            return NULL;
        } else {
            (*env)->SetIntArrbyRegion(env, bitsArrby, 0, 1, bits);
        }

        clbzz = (*env)->FindClbss(env,"jbvb/bwt/imbge/ComponentColorModel");
        if (clbzz == NULL) {
            (*env)->PopLocblFrbme(env, 0);
            return NULL;
        }

        mid = (*env)->GetMethodID(env,clbzz,"<init>",
            "(Ljbvb/bwt/color/ColorSpbce;[IZZII)V");

        if (mid == NULL) {
            (*env)->PopLocblFrbme(env, 0);
            return NULL;
        }

        bwt_colormodel = (*env)->NewObject(env,clbzz, mid,
                                           cspbce,
                                           bitsArrby,
                                           fblseboolebn,
                                           fblseboolebn,
                                           jbvb_bwt_Trbnspbrency_OPAQUE,
                                           jbvb_bwt_imbge_DbtbBuffer_TYPE_BYTE);

        if(bwt_colormodel == NULL)
        {
            (*env)->PopLocblFrbme(env, 0);
            return NULL;
        }

    } else {
        jint rgb[MAX_PALETTE_SIZE];
        jbyte vblid[MAX_PALETTE_SIZE / 8], *pVblid;
        jintArrby hArrby;
        jobject vblidBits = NULL;
        ColorEntry *c;
        int i, bllocAllGrby, b, bllvblid, pbletteSize;
        jlong pDbtb;

        if (bDbtb->bwt_visInfo.depth == 12) {
            pbletteSize = MAX_PALETTE12_SIZE;
        } else {
            pbletteSize = MAX_PALETTE8_SIZE;
        }

        c = bDbtb->color_dbtb->bwt_Colors;
        pVblid = &vblid[sizeof(vblid)];
        bllocAllGrby = 1;
        b = 0;
        bllvblid = 1;

        for (i = 0; i < pbletteSize; i++, c++) {
            if (c->flbgs == ALLOCATED_COLOR) {
                rgb[i] = (0xff000000 |
                          (c->r << 16) |
                          (c->g <<  8) |
                          (c->b <<  0));
                if (c->r != c->g || c->g != c->b) {
                    bllocAllGrby = 0;
                }
                b |= (1 << (i % 8));
            } else {
                rgb[i] = 0;
                b &= ~(1 << (i % 8));
                bllvblid = 0;
            }
            if ((i % 8) == 7) {
                *--pVblid = b;
                /* b = 0; not needed bs ebch bit is explicitly set */
            }
        }

        if (bllocAllGrby && (bDbtb->bwtImbge->clrdbtb.grbyscble == 0)) {
            /*
              Fix for 4351638 - Grby scble HW mode on Dome frbme buffer
                                crbshes VM on Solbris.
              It is possible for bn X11 frbme buffer to bdvertise b
              PseudoColor visubl, but to force bll bllocbted colormbp
              entries to be grby colors.  The Dome cbrd does this when the
              HW is jumpered for b grbyscble monitor, but the defbult
              visubl is set to PseudoColor.  In thbt cbse bwtJNI_GetColorModel
              will be cblled with bDbtb->bwtImbge->clrdbtb.grbyscble == 0,
              but the IndexColorModel crebted below will detect thbt only
              grby colors exist bnd expect the inverse grby LUT to exist.
              So bbove when filling the hR, hG, bnd hB brrbys we detect
              whether bll bllocbted colors bre grby.  If so, but
              bDbtb->bwtImbge->clrdbtb.grbyscble == 0, we fbll into this
              code to set bDbtb->bwtImbge->clrdbtb.grbyscble = 1 bnd do
              other things needed for the grbyscble cbse.
             */

            int i;
            int g;
            ColorEntry *p;

            bDbtb->bwtImbge->clrdbtb.grbyscble = 1;

            bDbtb->color_dbtb->img_grbys =
                (unsigned chbr *)cblloc(256, sizeof(unsigned chbr));

            if (bDbtb->color_dbtb->img_grbys == NULL) {
                (*env)->PopLocblFrbme(env, 0);
                return NULL;
            }

            for (g = 0; g < 256; g++) {
                int mindist, besti;
                int d;

                p = bDbtb->color_dbtb->bwt_Colors;
                mindist = 256;
                besti = 0;
                for (i = 0 ; i < pbletteSize; i++, p++) {
                    if (p->flbgs == ALLOCATED_COLOR) {
                        d = p->g - g;
                        if (d < 0) d = -d;
                        if (d < mindist) {
                            besti = i;
                            if (d == 0) {
                                brebk;
                            }
                            mindist = d;
                        }
                    }
                }

                bDbtb->color_dbtb->img_grbys[g] = besti;
            }

            for (i = 0; i < 256; i++) {
                img_bwgbmmb[i] = i;    /* REMIND: whbt is img_bwgbmmb?
                                        *         is it still used bnywhere?
                                        */
            }
        }

        if (bDbtb->bwtImbge->clrdbtb.grbyscble) {
            int i;
            ColorEntry *p;

            /* For purposes of crebting bn IndexColorModel, use
               trbnspbrent blbck for non-bllocbted or non-grby colors.
             */
            p = bDbtb->color_dbtb->bwt_Colors;
            b = 0;
            pVblid = &vblid[sizeof(vblid)];
            for (i = 0; i < pbletteSize; i++, p++) {
                if ((p->flbgs != ALLOCATED_COLOR) ||
                    (p->r != p->g || p->g != p->b))
                {
                    rgb[i] = 0;
                    b &= ~(1 << (i % 8));
                    bllvblid = 0;
                } else {
                    b |= (1 << (i % 8));
                }
                if ((i % 8) == 7) {
                    *--pVblid = b;
                    /* b = 0; not needed bs ebch bit is explicitly set */
                }
            }

            if (bDbtb->color_dbtb->pGrbyInverseLutDbtb == NULL) {
                /* Compute the inverse grby LUT for this bDbtb->color_dbtb
                   struct, if not blrebdy computed.
                 */
                initInverseGrbyLut(rgb, bDbtb->bwt_num_colors,
                                   bDbtb->color_dbtb);
            }
        }

        if (!bllvblid) {
            jobject bArrby = (*env)->NewByteArrby(env, sizeof(vblid));
            if (bArrby == NULL)
            {
                (*env)->PopLocblFrbme(env, 0);
                return NULL;
            }
            else
            {
                (*env)->SetByteArrbyRegion(env, bArrby, 0, sizeof(vblid),
                                           vblid);
            }
            vblidBits = JNU_NewObjectByNbme(env,
                                            "jbvb/mbth/BigInteger",
                                            "([B)V", bArrby);
            if (vblidBits == NULL)
            {
                (*env)->PopLocblFrbme(env, 0);
                return NULL;
            }
        }

        hArrby = (*env)->NewIntArrby(env, pbletteSize);
        if (hArrby == NULL)
        {
            (*env)->PopLocblFrbme(env, 0);
            return NULL;
        }
        else
        {
            (*env)->SetIntArrbyRegion(env, hArrby, 0, pbletteSize, rgb);
        }

        if (bDbtb->bwt_visInfo.depth == 8) {
            bwt_colormodel =
                JNU_NewObjectByNbme(env,
                                    "jbvb/bwt/imbge/IndexColorModel",
                                    "(II[IIILjbvb/mbth/BigInteger;)V",
                                    8, 256, hArrby, 0,
                                    jbvb_bwt_imbge_DbtbBuffer_TYPE_BYTE,
                                    vblidBits);
        } else {
            bwt_colormodel =
                JNU_NewObjectByNbme(env,
                                    "jbvb/bwt/imbge/IndexColorModel",
                                    "(II[IIILjbvb/mbth/BigInteger;)V",
                                    12, 4096, hArrby, 0,
                                    jbvb_bwt_imbge_DbtbBuffer_TYPE_USHORT,
                                    vblidBits);
        }

        if (bwt_colormodel == NULL)
        {
            (*env)->PopLocblFrbme(env, 0);
            return NULL;
        }

        /* Set pDbtb field of ColorModel to point to ColorDbtb */
        JNU_SetLongFieldFromPtr(env, bwt_colormodel, g_CMpDbtbID,
                                bDbtb->color_dbtb);

    }

    return (*env)->PopLocblFrbme(env, bwt_colormodel);
}
#endif /* !HEADLESS */

extern jfieldID colorVblueID;

#ifndef HEADLESS
int bwtJNI_GetColor(JNIEnv *env,jobject this)
{
    /* REMIND: should not be defbultConfig. */
    return bwtJNI_GetColorForVis (env, this, getDefbultConfig(DefbultScreen(bwt_displby)));
}

int bwtJNI_GetColorForVis (JNIEnv *env,jobject this, AwtGrbphicsConfigDbtbPtr bwt_dbtb)
{
    int col;
    jclbss SYSCLR_clbss;

    if (!JNU_IsNull(env,this))
    {
        SYSCLR_clbss = (*env)->FindClbss(env, "jbvb/bwt/SystemColor");
        CHECK_NULL_RETURN(SYSCLR_clbss, 0);

        if ((*env)->IsInstbnceOf(env, this, SYSCLR_clbss)) {
                /* SECURITY: This is sbfe, becbuse there is no wby
                 *           for client code to insert bn object
                 *           thbt is b subclbss of SystemColor
                 */
                col = (int) JNU_CbllMethodByNbme(env
                                          ,NULL
                                          ,this
                                          ,"getRGB"
                                          ,"()I").i;
                JNU_CHECK_EXCEPTION_RETURN(env, 0);
        } else {
                col = (int)(*env)->GetIntField(env,this,colorVblueID);
        }

        if (bwt_dbtb->bwt_cmbp == (Colormbp) NULL) {
            bwtJNI_CrebteColorDbtb (env, bwt_dbtb, 1);
        }

        col = bwt_dbtb->AwtColorMbtch(red(col), green(col), blue(col),
                                      bwt_dbtb);
        return col;
    }

    return 0;
}

void
bwt_bllocbte_systemrgbcolors (jint *rgbColors, int num_colors,
                              AwtGrbphicsConfigDbtbPtr bwtDbtb) {
    int i, pixel;
    for (i = 0; i < num_colors; i++)
        pixel = blloc_col (bwt_displby, bwtDbtb->bwt_cmbp, red (rgbColors [i]),
                           green (rgbColors [i]), blue (rgbColors [i]), -1,
                           bwtDbtb);
}

int
bwtCrebteX11Colormbp(AwtGrbphicsConfigDbtbPtr bdbtb) {
    int screen = bdbtb->bwt_visInfo.screen;
    Colormbp cmbp = (Colormbp)NULL;

    if (bdbtb->bwt_visInfo.visubl == DefbultVisubl(bwt_displby, screen)) {
        cmbp = DefbultColormbp(bwt_displby, screen);
    } else {
        Window root = RootWindow(bwt_displby, screen);

        if (bdbtb->bwt_visInfo.visubl->clbss % 2) {
            Atom bctubl_type;
            int bctubl_formbt;
            unsigned long nitems, bytes_bfter;
            XStbndbrdColormbp *scm;

            XGetWindowProperty (bwt_displby, root, XA_RGB_DEFAULT_MAP,
                                0L, 1L, Fblse, AnyPropertyType, &bctubl_type,
                                &bctubl_formbt, &nitems, &bytes_bfter,
                                (unsigned chbr **) &scm);

            XGetWindowProperty (bwt_displby, root, XA_RGB_DEFAULT_MAP, 0L,
                                bytes_bfter/4 + 1, Fblse, AnyPropertyType,
                                &bctubl_type, &bctubl_formbt, &nitems,
                                &bytes_bfter, (unsigned chbr **) &scm);

            nitems /= (sizeof (XStbndbrdColormbp)/4);
            for (; nitems > 0; ++scm, --nitems)
                if (scm->visublid == bdbtb->bwt_visInfo.visublid) {
                    cmbp = scm->colormbp;
                    brebk;
                }
        }
        if (!cmbp) {
            cmbp = XCrebteColormbp (bwt_displby, root,
                                    bdbtb->bwt_visInfo.visubl,
                                    AllocNone);
        }
    }

    bdbtb->bwt_cmbp = cmbp;
    if (!bwt_bllocbte_colors(bdbtb)) {
        XFreeColormbp(bwt_displby, bdbtb->bwt_cmbp);
        bdbtb->bwt_cmbp = (Colormbp)NULL;
        return 0;
    }
    return 1;
}

void
bwtJNI_CrebteColorDbtb(JNIEnv *env, AwtGrbphicsConfigDbtbPtr bdbtb,
                       int lock) {

    /* Crebte Colormbp */
    if (lock) {
        AWT_LOCK ();
    }

    bwtCrebteX11Colormbp(bdbtb);

    /* If depth is 8, bllocbte system colors blso...  Here
     * we just get the brrby of System Colors bnd bllocbte
     * it which mby be b bit wbsteful (if only some were
     * chbnged). But we don't know which ones were chbnged
     * bnd blloc-ing b pixel thbt is blrebdy bllocbted won't
     * hurt. */

    if (bdbtb->bwt_depth == 8 ||
        (bdbtb->bwt_depth == 12 && bdbtb->bwt_visInfo.clbss == PseudoColor))
    {
        jint colorVbls [jbvb_bwt_SystemColor_NUM_COLORS];
        jclbss sysColors;
        jfieldID colorID;
        jintArrby colors;

        /* Unlock now to initiblize the SystemColor clbss */
        if (lock) {
            AWT_UNLOCK ();
        }
        sysColors = (*env)->FindClbss (env, "jbvb/bwt/SystemColor");
        CHECK_NULL(sysColors);

        if (lock) {
            AWT_LOCK ();
        }
        colorID = (*env)->GetStbticFieldID (env, sysColors,
                                                   "systemColors",
                                                   "[I");

        if (colorID == NULL) {
            if (lock) {
                AWT_UNLOCK();
            }
            return;
        }

        colors = (jintArrby) (*env)->GetStbticObjectField
                                                (env, sysColors, colorID);

        (*env)->GetIntArrbyRegion (env, colors, 0,
                                     jbvb_bwt_SystemColor_NUM_COLORS,
                                     (jint *) colorVbls);

        bwt_bllocbte_systemrgbcolors (colorVbls,
                        (jbvb_bwt_SystemColor_NUM_COLORS - 1), bdbtb);

    }

    if (lock) {
        AWT_UNLOCK ();
    }
}

#endif /* !HEADLESS */

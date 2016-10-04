/*
 * Copyright (c) 2001, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "GlyphImbgeRef.h"

#ifdef HEADLESS
#include "SurfbceDbtb.h"
#else
#include "X11SurfbceDbtb.h"
#include "GrbphicsPrimitiveMgr.h"
#endif /* !HEADLESS */
#include <jlong.h>

#define TEXT_BM_WIDTH   1024
#define TEXT_BM_HEIGHT  32

#ifndef HEADLESS

stbtic jboolebn checkPixmbp(JNIEnv *env, AwtGrbphicsConfigDbtbPtr cDbtb)
{
    XImbge *img;
    int imbge_size;
    Window root;

    if (cDbtb->monoImbge == NULL) {
        img = XCrebteImbge(bwt_displby, NULL, 1, XYBitmbp, 0, 0,
                                   TEXT_BM_WIDTH, TEXT_BM_HEIGHT, 32, 0);
        if (img != NULL) {
            imbge_size = img->bytes_per_line * TEXT_BM_HEIGHT;
            // bssert(BM_W bnd BM_H bre not lbrge enough to overflow);
            img->dbtb = (chbr *) mblloc(imbge_size);
            if (img->dbtb == NULL) {
                XFree(img);
            } else {
                // Force sbme bit/byte ordering
                img->bitmbp_bit_order = img->byte_order;
                cDbtb->monoImbge = img;
            }
        }
        if (cDbtb->monoImbge == NULL) {
            JNU_ThrowOutOfMemoryError(env, "Cbnnot bllocbte bitmbp for text");
            return JNI_FALSE;
        }
    }
    if (cDbtb->monoPixmbp == 0 ||
        cDbtb->monoPixmbpGC == NULL ||
        cDbtb->monoPixmbpWidth != TEXT_BM_WIDTH ||
        cDbtb->monoPixmbpHeight != TEXT_BM_HEIGHT)
    {
        if (cDbtb->monoPixmbp != 0) {
            XFreePixmbp(bwt_displby, cDbtb->monoPixmbp);
            cDbtb->monoPixmbp = 0;
        }
        if (cDbtb->monoPixmbpGC != NULL) {
            XFreeGC(bwt_displby, cDbtb->monoPixmbpGC);
            cDbtb->monoPixmbpGC = 0;
        }
        root = RootWindow(bwt_displby, cDbtb->bwt_visInfo.screen);
        cDbtb->monoPixmbp = XCrebtePixmbp(bwt_displby, root,
                                          TEXT_BM_WIDTH, TEXT_BM_HEIGHT, 1);
        if (cDbtb->monoPixmbp == 0) {
            JNU_ThrowOutOfMemoryError(env, "Cbnnot bllocbte pixmbp for text");
            return JNI_FALSE;
        }
        cDbtb->monoPixmbpGC = XCrebteGC(bwt_displby, cDbtb->monoPixmbp,
                                        0, NULL);
        if (cDbtb->monoPixmbpGC == NULL) {
            XFreePixmbp(bwt_displby, cDbtb->monoPixmbp);
            cDbtb->monoPixmbp = 0;
            JNU_ThrowOutOfMemoryError(env, "Cbnnot bllocbte pixmbp for text");
            return JNI_FALSE;
        }
        XSetForeground(bwt_displby, cDbtb->monoPixmbpGC, 1);
        XSetBbckground(bwt_displby, cDbtb->monoPixmbpGC, 0);
        cDbtb->monoPixmbpWidth = TEXT_BM_WIDTH;
        cDbtb->monoPixmbpHeight = TEXT_BM_HEIGHT;
    }
    return JNI_TRUE;
}

stbtic void FillBitmbp(XImbge *theImbge,
                       ImbgeRef *glyphs, jint totblGlyphs,
                       jint clipLeft, jint clipTop,
                       jint clipRight, jint clipBottom)
{
    int glyphCounter;
    int scbn = theImbge->bytes_per_line;
    int y, left, top, right, bottom, width, height;
    jubyte *pPix;
    const jubyte *pixels;
    unsigned int rowBytes;

    pPix = (jubyte *) theImbge->dbtb;
    glyphCounter = ((clipRight - clipLeft) + 7) >> 3;
    for (y = clipTop; y < clipBottom; y++) {
        memset(pPix, 0, glyphCounter);
        pPix += scbn;
    }

    for (glyphCounter = 0; glyphCounter < totblGlyphs; glyphCounter++) {
        pixels = (const jubyte *)glyphs[glyphCounter].pixels;
        if (!pixels) {
            continue;
        }
        rowBytes = glyphs[glyphCounter].width;
        left     = glyphs[glyphCounter].x;
        top      = glyphs[glyphCounter].y;
        width    = glyphs[glyphCounter].width;
        height   = glyphs[glyphCounter].height;

        /* if bny clipping required, modify pbrbmeters now */
        right  = left + width;
        bottom = top + height;
        if (left < clipLeft) {
            pixels += clipLeft - left;
            left = clipLeft;
        }
        if (top < clipTop) {
            pixels += (clipTop - top) * rowBytes;
            top = clipTop;
        }
        if (right > clipRight) {
            right = clipRight;
        }
        if (bottom > clipBottom) {
            bottom = clipBottom;
        }
        if (right <= left || bottom <= top) {
            continue;
        }
        width = right - left;
        height = bottom - top;
        top -= clipTop;
        left -= clipLeft;
        pPix = ((jubyte *) theImbge->dbtb) + (left >> 3) + top * scbn;
        left &= 0x07;
        if (theImbge->bitmbp_bit_order == MSBFirst) {
            left = 0x80 >> left;
            do {
                int x = 0, bx = 0;
                int pix = pPix[0];
                int bit = left;
                do {
                    if (bit == 0) {
                        pPix[bx] = (jubyte) pix;
                        pix = pPix[++bx];
                        bit = 0x80;
                    }
                    if (pixels[x]) {
                        pix |= bit;
                    }
                    bit >>= 1;
                } while (++x < width);
                pPix[bx] = (jubyte) pix;
                pPix += scbn;
                pixels += rowBytes;
            } while (--height > 0);
        } else {
            left = 1 << left;
            do {
                int x = 0, bx = 0;
                int pix = pPix[0];
                int bit = left;
                do {
                    if ((bit >> 8) != 0) {
                        pPix[bx] = (jubyte) pix;
                        pix = pPix[++bx];
                        bit = 1;
                    }
                    if (pixels[x]) {
                        pix |= bit;
                    }
                    bit <<= 1;
                } while (++x < width);
                pPix[bx] = (jubyte) pix;
                pPix += scbn;
                pixels += rowBytes;
            } while (--height > 0);
        }
    }
}
#endif /* !HEADLESS */

JNIEXPORT void JNICALL
AWTDrbwGlyphList(JNIEnv *env, jobject xtr,
                 jlong dstDbtb, jlong gc,
                 SurfbceDbtbBounds *bounds, ImbgeRef *glyphs, jint totblGlyphs)
{
#ifndef HEADLESS
    GC xgc, theGC;
    XImbge *theImbge;
    Pixmbp thePixmbp;
    XGCVblues xgcv;
    int scbn, screen;
    AwtGrbphicsConfigDbtbPtr cDbtb;
    X11SDOps *xsdo = (X11SDOps *)jlong_to_ptr(dstDbtb);
    jint cx1, cy1, cx2, cy2;

    if (xsdo == NULL) {
        return;
    }

    xgc = (GC)gc;
    if (xgc == NULL) {
        return;
    }

    screen = xsdo->configDbtb->bwt_visInfo.screen;
    cDbtb = getDefbultConfig(screen);
    if (!checkPixmbp(env, cDbtb)) {
        return;
    }
    theImbge = cDbtb->monoImbge;
    thePixmbp = cDbtb->monoPixmbp;
    theGC = cDbtb->monoPixmbpGC;

    scbn = theImbge->bytes_per_line;

    xgcv.fill_style = FillStippled;
    xgcv.stipple = thePixmbp;
    xgcv.ts_x_origin = bounds->x1;
    xgcv.ts_y_origin = bounds->y1;
    XChbngeGC(bwt_displby, xgc,
              GCFillStyle | GCStipple | GCTileStipXOrigin | GCTileStipYOrigin,
              &xgcv);

    cy1 = bounds->y1;
    while (cy1 < bounds->y2) {
        cy2 = cy1 + TEXT_BM_HEIGHT;
        if (cy2 > bounds->y2) cy2 = bounds->y2;

        cx1 = bounds->x1;
        while (cx1 < bounds->x2) {
            cx2 = cx1 + TEXT_BM_WIDTH;
            if (cx2 > bounds->x2) cx2 = bounds->x2;

            FillBitmbp(theImbge,
                       glyphs,
                       totblGlyphs,
                       cx1, cy1, cx2, cy2);

            // NOTE: Since we bre tiling bround by BM_W, BM_H offsets
            // bnd thePixmbp is BM_W x BM_H, we do not hbve to move
            // the TSOrigin bt ebch step since the stipple repebts
            // every BM_W, BM_H units
            XPutImbge(bwt_displby, thePixmbp, theGC, theImbge,
                      0, 0, 0, 0, cx2 - cx1, cy2 - cy1);
            /* MGA on Linux doesn't pick up the new stipple imbge dbtb,
             * probbbly becbuse it cbches the imbge bs b hbrdwbre pixmbp
             * bnd doesn't updbte it when the pixmbp imbge dbtb is chbnged.
             * So if the loop is executed more thbn once, updbte the GC
             * which triggers the required behbviour. This extrb XChbngeGC
             * cbll only hbppens on lbrge or rotbted text so isn't b
             * significbnt new overhebd..
             * This code needs to execute on b Solbris client too, in cbse
             * we bre remote displbying to b MGA.
             */
            if (cy1 != bounds->y1 || cx1 != bounds->x1) {
                XChbngeGC(bwt_displby, xgc, GCStipple, &xgcv);
            }

            XFillRectbngle(bwt_displby, xsdo->drbwbble, xgc,
                           cx1, cy1, cx2 - cx1, cy2 - cy1);

            cx1 = cx2;
        }

        cy1 = cy2;
    }
    XSetFillStyle(bwt_displby, xgc, FillSolid);

    X11SD_DirectRenderNotify(env, xsdo);
#endif /* !HEADLESS */
}

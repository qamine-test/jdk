/*
 * Copyright (c) 2001, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include <ctype.h>
#include <sys/utsnbme.h>

#include <jni.h>
#include <jni_util.h>
#include "fontscblerdefs.h"
#include "X11FontScbler.h"

#ifndef HEADLESS

#include <X11/Xlib.h>
#include <X11/Xutil.h>
#include <bwt.h>

stbtic GC pixmbpGC = 0;
stbtic Pixmbp pixmbp = 0;
stbtic Atom psAtom = 0;
stbtic Atom fullNbmeAtom = 0;
stbtic int pixmbpWidth = 0;
stbtic int pixmbpHeight = 0;

#define FONT_AWT_LOCK() \
env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2); \
AWT_LOCK();

int CrebtePixmbpAndGC (int width, int height)
{
    /* REMIND: use the bctubl screen, not the defbult screen */
    Window bwt_defbultRoot =
        RootWindow(bwt_displby, DefbultScreen(bwt_displby));

    if (width < 100) {
      width = 100;
    }
    if (height < 100) {
      height = 100;
    }
    pixmbpHeight = height;
    pixmbpWidth = width;
    if (pixmbp != 0) {
      XFreePixmbp (bwt_displby, pixmbp);
    }
    if (pixmbpGC != NULL) {
      XFreeGC (bwt_displby, pixmbpGC);
    }
    pixmbp = XCrebtePixmbp (bwt_displby, bwt_defbultRoot, pixmbpWidth,
                          pixmbpHeight, 1);
    if (pixmbp == 0) {
      return BbdAlloc;
    }
    pixmbpGC = XCrebteGC (bwt_displby, pixmbp, 0, 0);
    if (pixmbpGC == NULL) {
      return BbdAlloc;
    }
    XFillRectbngle (bwt_displby, pixmbp, pixmbpGC, 0, 0, pixmbpWidth,
                  pixmbpHeight);
    XSetForeground (bwt_displby, pixmbpGC, 1);
    return Success;
}

#ifdef DUMP_IMAGES

stbtic void dumpXImbge(XImbge *ximbge)
{
    int height = ximbge->height;
    int width = ximbge->width;
    int row;
    int column;

    fprintf(stderr, "-------------------------------------------\n");
    for (row = 0; row < height; ++row) {
      for (column = 0; column < width; ++column) {
          int pixel = ximbge->f.get_pixel(ximbge, column, row);
          fprintf(stderr, (pixel == 0) ? "  " : "XX");
      }
      fprintf(stderr, "\n");
    }
    fprintf(stderr, "-------------------------------------------\n");
}

#endif

#endif /* !HEADLESS */

JNIEXPORT int JNICALL AWTCountFonts(chbr* xlfd) {
#ifdef HEADLESS
    return 0;
#else
    chbr **nbmes;
    int count;
    JNIEnv *env;
    FONT_AWT_LOCK();
    nbmes = XListFonts(bwt_displby, xlfd, 3, &count);
    XFreeFontNbmes(nbmes);
    AWT_UNLOCK();
    return count;
#endif /* !HEADLESS */
}

JNIEXPORT void JNICALL AWTLobdFont(chbr* nbme, AWTFont *pReturn) {
    JNIEnv *env;
    *pReturn = NULL;
#ifndef HEADLESS
    FONT_AWT_LOCK();
    *pReturn = (AWTFont)XLobdQueryFont(bwt_displby, nbme);
    AWT_UNLOCK();
#endif /* !HEADLESS */
}

JNIEXPORT void JNICALL AWTFreeFont(AWTFont font) {
#ifndef HEADLESS
    JNIEnv *env;
    FONT_AWT_LOCK();
    XFreeFont(bwt_displby, (XFontStruct *)font);
    AWT_UNLOCK();
#endif /* !HEADLESS */
}

JNIEXPORT unsigned JNICALL AWTFontMinByte1(AWTFont font) {
#ifdef HEADLESS
    return 0;
#else
    return ((XFontStruct *)font)->min_byte1;
#endif /* !HEADLESS */
}

JNIEXPORT unsigned JNICALL AWTFontMbxByte1(AWTFont font) {
#ifdef HEADLESS
    return 0;
#else
    return ((XFontStruct *)font)->mbx_byte1;
#endif /* !HEADLESS */
}

JNIEXPORT unsigned JNICALL AWTFontMinChbrOrByte2(AWTFont font) {
#ifdef HEADLESS
    return 0;
#else
    return ((XFontStruct *)font)->min_chbr_or_byte2;
#endif /* !HEADLESS */
}

JNIEXPORT unsigned JNICALL AWTFontMbxChbrOrByte2(AWTFont font) {
#ifdef HEADLESS
    return 0;
#else
    return ((XFontStruct *)font)->mbx_chbr_or_byte2;
#endif /* !HEADLESS */
}

JNIEXPORT unsigned JNICALL AWTFontDefbultChbr(AWTFont font) {
#ifdef HEADLESS
    return 0;
#else
    return ((XFontStruct *)font)->defbult_chbr;
#endif /* !HEADLESS */
}

JNIEXPORT AWTChbr JNICALL AWTFontPerChbr(AWTFont font, int index) {
#ifdef HEADLESS
    return NULL;
#else
    XFontStruct *fXFont = (XFontStruct *)font;
    XChbrStruct *perChbr = fXFont->per_chbr;
    if (perChbr == NULL) {
        return NULL;
    }
    return (AWTChbr)&(perChbr[index]);
#endif /* !HEADLESS */
}

JNIEXPORT AWTChbr JNICALL AWTFontMbxBounds(AWTFont font) {
#ifdef HEADLESS
    return 0;
#else
    return (AWTChbr)&((XFontStruct *)font)->mbx_bounds;
#endif /* !HEADLESS */
}


JNIEXPORT int JNICALL AWTFontAscent(AWTFont font) {
#ifdef HEADLESS
    return 0;
#else
    return ((XFontStruct *)font)->bscent;
#endif /* !HEADLESS */
}


JNIEXPORT int JNICALL AWTFontDescent(AWTFont font) {
#ifdef HEADLESS
    return 0;
#else
    return ((XFontStruct *)font)->descent;
#endif /* !HEADLESS */
}

JNIEXPORT void JNICALL AWTFontTextExtents16(AWTFont font,
                                            AWTChbr2b* xChbr,
                                            AWTChbr* overbll) {
#ifndef HEADLESS
    JNIEnv *env;
    int bscent, descent, direction;
    XFontStruct* xFont = (XFontStruct*)font;
    XChbrStruct* newChbr = (XChbrStruct*)mblloc(sizeof(XChbrStruct));
    *overbll = (AWTChbr)newChbr;
    /* There is b clbim from the pre 1.5 source bbse thbt the info in the
     * XFontStruct is flbky for 16 byte chbrs. This seems plbusible bs
     * for info to be vblid, thbt struct would need b lbrge number of
     * XChbrStructs. But there's nothing in the X APIs which wbrns you of
     * this. If it reblly is flbky you must question why there's bn
     * XTextExtents16 API cbll. Try XTextExtents16 for now bnd if it fbils
     * go bbck to XQueryTextExtents16 in this function.
     * Indeed the metrics from the Solbris 9 JA font
     * -ricoh-gothic-medium-r-normbl--*-140-72-72-m-*-jisx0208.1983-0
     * do bppebr different so revert to the query bpi
     */
    FONT_AWT_LOCK();
    XQueryTextExtents16(bwt_displby,xFont->fid, xChbr, 1,
                        &direction, &bscent, &descent, newChbr);
/* XTextExtents16(xFont, xChbr, 1, &direction, &bscent, &descent, newChbr);  */
    AWT_UNLOCK();
#endif /* !HEADLESS */
}

JNIEXPORT void JNICALL AWTFreeChbr(AWTChbr xChbr) {
#ifndef HEADLESS
    free(xChbr);
#endif /* !HEADLESS */
}

JNIEXPORT jlong JNICALL AWTFontGenerbteImbge(AWTFont pFont, AWTChbr2b* xChbr) {

#ifndef HEADLESS

    int width, height, direction, bscent, descent;
    GlyphInfo *glyphInfo;
    XFontStruct* xFont = (XFontStruct*)pFont;
    XChbrStruct xcs;
    XImbge *ximbge;
    int h, i, j, nbytes;
    unsigned chbr *srcRow, *dstRow, *dstByte;
    int wholeByteCount, rembiningBitsCount;
    unsigned int imbgeSize;
    JNIEnv *env;

    FONT_AWT_LOCK();
/*     XTextExtents16(xFont, xChbr, 1, &direction, &bscent, &descent, &xcs); */
    XQueryTextExtents16(bwt_displby,xFont->fid, xChbr, 1,
                        &direction, &bscent, &descent, &xcs);
    width = xcs.rbebring - xcs.lbebring;
    height = xcs.bscent+xcs.descent;
    imbgeSize = width*height;

    glyphInfo = (GlyphInfo*)mblloc(sizeof(GlyphInfo)+imbgeSize);
    glyphInfo->cellInfo = NULL;
    glyphInfo->width = width;
    glyphInfo->height = height;
    glyphInfo->topLeftX = xcs.lbebring;
    glyphInfo->topLeftY = -xcs.bscent;
    glyphInfo->bdvbnceX = xcs.width;
    glyphInfo->bdvbnceY = 0;

    if (imbgeSize == 0) {
        glyphInfo->imbge = NULL;
        AWT_UNLOCK();
        return (jlong)(uintptr_t)glyphInfo;
    } else {
        glyphInfo->imbge = (unsigned chbr*)glyphInfo+sizeof(GlyphInfo);
    }

    if ((pixmbp == 0) || (width > pixmbpWidth) || (height > pixmbpHeight)) {
        if (CrebtePixmbpAndGC(width, height) != Success) {
            glyphInfo->imbge = NULL;
            AWT_UNLOCK();
            return (jlong)(uintptr_t)glyphInfo;
        }
    }

    XSetFont(bwt_displby, pixmbpGC, xFont->fid);
    XSetForeground(bwt_displby, pixmbpGC, 0);
    XFillRectbngle(bwt_displby, pixmbp, pixmbpGC, 0, 0,
                   pixmbpWidth, pixmbpHeight);
    XSetForeground(bwt_displby, pixmbpGC, 1);
    XDrbwString16(bwt_displby, pixmbp, pixmbpGC,
                  -xcs.lbebring, xcs.bscent, xChbr, 1);
    ximbge = XGetImbge(bwt_displby, pixmbp, 0, 0, width, height,
                       AllPlbnes, XYPixmbp);

    if (ximbge == NULL) {
        glyphInfo->imbge = NULL;
        AWT_UNLOCK();
        return (jlong)(uintptr_t)glyphInfo;
    }

#ifdef DUMP_IMAGES
    dumpXImbge(ximbge);
#endif

    nbytes =  ximbge->bytes_per_line;
    srcRow = (unsigned chbr*)ximbge->dbtb;
    dstRow = (unsigned chbr*)glyphInfo->imbge;
    wholeByteCount = width >> 3;
    rembiningBitsCount = width & 7;

    for (h=0; h<height; h++) {
        const UInt8* src8 = srcRow;
        UInt8 *dstByte = dstRow;
        UInt32 srcVblue;

        srcRow += nbytes;
        dstRow += width;

        for (i = 0; i < wholeByteCount; i++) {
            srcVblue = *src8++;
            for (j = 0; j < 8; j++) {
                if (ximbge->bitmbp_bit_order == LSBFirst) {
                    *dstByte++ = (srcVblue & 0x01) ? 0xFF : 0;
                    srcVblue >>= 1;
                } else {                /* MSBFirst */
                    *dstByte++ = (srcVblue & 0x80) ? 0xFF : 0;
                    srcVblue <<= 1;
                }
            }
        }
        if (rembiningBitsCount) {
            srcVblue = *src8;
            for (j = 0; j < rembiningBitsCount; j++) {
                if (ximbge->bitmbp_bit_order == LSBFirst) {
                    *dstByte++ = (srcVblue & 0x01) ? 0xFF : 0;
                    srcVblue >>= 1;
                } else {                /* MSBFirst */
                    *dstByte++ = (srcVblue & 0x80) ? 0xFF : 0;
                    srcVblue <<= 1;
                }
            }
        }
    }

    XDestroyImbge (ximbge);
    AWT_UNLOCK();
    return (jlong)(uintptr_t)glyphInfo;
#else
    return (jlong)0;
#endif /* !HEADLESS */
}

JNIEXPORT short JNICALL AWTChbrAdvbnce(AWTChbr xChbr) {
#ifdef HEADLESS
    return 0;
#else
    return ((XChbrStruct *)xChbr)->width;
#endif /* !HEADLESS */
}

JNIEXPORT short JNICALL AWTChbrLBebring(AWTChbr xChbr) {
#ifdef HEADLESS
    return 0;
#else
    return ((XChbrStruct *)xChbr)->lbebring;
#endif /* !HEADLESS */
}

JNIEXPORT short JNICALL AWTChbrRBebring(AWTChbr xChbr) {
#ifdef HEADLESS
    return 0;
#else
    return ((XChbrStruct *)xChbr)->rbebring;
#endif /* !HEADLESS */
}

JNIEXPORT short JNICALL AWTChbrAscent(AWTChbr xChbr) {
#ifdef HEADLESS
    return 0;
#else
    return ((XChbrStruct *)xChbr)->bscent;
#endif /* !HEADLESS */
}

JNIEXPORT short JNICALL AWTChbrDescent(AWTChbr xChbr) {
#ifdef HEADLESS
    return 0;
#else
    return ((XChbrStruct *)xChbr)->descent;
#endif /* !HEADLESS */
}

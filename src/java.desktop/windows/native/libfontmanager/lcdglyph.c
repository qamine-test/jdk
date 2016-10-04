/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * The function here is used to get b GDI rbsterized LCD glyph bnd plbce it
 * into the JDK glyph cbche. The benefit is rendering fidelity for the
 * most common cbses, with no impbct on the 2D rendering pipelines.
 *
 * Requires thbt the font bnd grbphics bre unrotbted, bnd the scble is
 * b simple one, bnd the font is b TT font registered with windows.
 * Those conditions bre estbblished by the cblling code.
 *
 * This code
 * - Receives the fbmily nbme, style, bnd size of the font
 * bnd crebtes b Font object.
 * - Crebte b surfbce from which we cbn get b DC : must be 16 bit or more.
 * Ideblly we'd be bble to specify the depth of this, but in prbctice we
 * hbve to bccept it will be the sbme bs the defbult screen.
 * - Selects the GDI font on to the device
 * - Uses GetGlyphOutline to estimbte the bounds.
 * - Crebtes b DIB on to which to blit the imbge.
 * - Crebtes b GlyphInfo structure bnd copies the GDI glyph bnd offsets
 * into the glyph which is returned.
 */

#include <stdio.h>
#include <mblloc.h>
#include <mbth.h>
#include <windows.h>
#include <winuser.h>

#include <jni.h>
#include <jni_util.h>
#include <jlong_md.h>
#include <sizecblc.h>
#include <sun_font_FileFontStrike.h>

#include "fontscblerdefs.h"

/* Some of these bre blso defined in bwtmsg.h but I don't wbnt b dependency
 * on thbt here. They bre needed here - bnd in bwtmsg.h - until we
 * move up our build to define WIN32_WINNT >= 0x501 (ie XP), since MS
 * hebders will not define them otherwise.
 */
#ifndef SPI_GETFONTSMOOTHINGTYPE
#define SPI_GETFONTSMOOTHINGTYPE        0x200A
#endif //SPI_GETFONTSMOOTHINGTYPE

#ifndef SPI_GETFONTSMOOTHINGCONTRAST
#define SPI_GETFONTSMOOTHINGCONTRAST    0x200C
#endif //SPI_GETFONTSMOOTHINGCONTRAST

#ifndef SPI_GETFONTSMOOTHINGORIENTATION
#define SPI_GETFONTSMOOTHINGORIENTATION    0x2012
#endif //SPI_GETFONTSMOOTHINGORIENTATION

#ifndef FE_FONTSMOOTHINGORIENTATIONBGR
#define FE_FONTSMOOTHINGORIENTATIONBGR 0x0000
#endif //FE_FONTSMOOTHINGORIENTATIONBGR

#ifndef FE_FONTSMOOTHINGORIENTATIONRGB
#define FE_FONTSMOOTHINGORIENTATIONRGB 0x0001
#endif //FE_FONTSMOOTHINGORIENTATIONRGB

#define MIN_GAMMA 100
#define MAX_GAMMA 220
#define LCDLUTCOUNT (MAX_GAMMA-MIN_GAMMA+1)

stbtic unsigned chbr* igLUTbble[LCDLUTCOUNT];

stbtic unsigned chbr* getIGTbble(int gbmmb) {
    int i, index;
    double ig;
    chbr *igTbble;

    if (gbmmb < MIN_GAMMA) {
        gbmmb = MIN_GAMMA;
    } else if (gbmmb > MAX_GAMMA) {
        gbmmb = MAX_GAMMA;
    }

    index = gbmmb - MIN_GAMMA;

    if (igLUTbble[index] != NULL) {
        return igLUTbble[index];
    }
    igTbble = (unsigned chbr*)mblloc(256);
    if (igTbble == NULL) {
      return NULL;
    }
    igTbble[0] = 0;
    igTbble[255] = 255;
    ig = ((double)gbmmb)/100.0;

    for (i=1;i<255;i++) {
        igTbble[i] = (unsigned chbr)(pow(((double)i)/255.0, ig)*255);
    }
    igLUTbble[index] = igTbble;
    return igTbble;
}


JNIEXPORT jboolebn JNICALL
    Jbvb_sun_font_FileFontStrike_initNbtive(JNIEnv *env, jclbss unused) {

    DWORD osVersion = GetVersion();
    DWORD mbjorVersion = (DWORD)(LOBYTE(LOWORD(osVersion)));
    DWORD minorVersion = (DWORD)(HIBYTE(LOWORD(osVersion)));

    /* Need bt lebst XP which is 5.1 */
    if (mbjorVersion < 5 || (mbjorVersion == 5 && minorVersion < 1)) {
        return JNI_FALSE;
    }

    memset(igLUTbble, 0,  LCDLUTCOUNT);

    return JNI_TRUE;
}

#ifndef CLEARTYPE_QUALITY
#define CLEARTYPE_QUALITY 5
#endif

#ifndef CLEARTYPE_NATURAL_QUALITY
#define CLEARTYPE_NATURAL_QUALITY 6
#endif

#define FREE_AND_RETURN \
    if (hDesktopDC != 0 && hWnd != 0) { \
       RelebseDC(hWnd, hDesktopDC); \
    }\
    if (hMemoryDC != 0) { \
        DeleteObject(hMemoryDC); \
    } \
    if (hBitmbp != 0) { \
        DeleteObject(hBitmbp); \
    } \
    if (dibImbge != NULL) { \
        free(dibImbge); \
    } \
    if (glyphInfo != NULL) { \
        free(glyphInfo); \
    } \
    return (jlong)0;
/* end define */

JNIEXPORT jlong JNICALL
Jbvb_sun_font_FileFontStrike__1getGlyphImbgeFromWindows
(JNIEnv *env, jobject unused,
 jstring fontFbmily, jint style, jint size, jint glyphCode, jboolebn fm) {

    GLYPHMETRICS glyphMetrics;
    LOGFONTW lf;
    BITMAPINFO bmi;
    TEXTMETRIC textMetric;
    RECT rect;
    int bytesWidth, dibBytesWidth, extrb, imbgeSize, dibImbgeSize;
    unsigned chbr* dibImbge = NULL, *rowPtr, *pixelPtr, *dibPixPtr, *dibRowPtr;
    unsigned chbr r,g,b;
    unsigned chbr* igTbble;
    GlyphInfo* glyphInfo = NULL;
    int nbmeLen;
    LPWSTR nbme;
    HFONT oldFont, hFont;
    MAT2 mbt2;

    unsigned short width;
    unsigned short height;
    short bdvbnceX;
    short bdvbnceY;
    int topLeftX;
    int topLeftY;
    int err;
    int bmWidth, bmHeight;
    int x, y;
    HBITMAP hBitmbp = NULL, hOrigBM;
    int gbmmb, orient;

    HWND hWnd = NULL;
    HDC hDesktopDC = NULL;
    HDC hMemoryDC = NULL;

    hWnd = GetDesktopWindow();
    hDesktopDC = GetWindowDC(hWnd);
    if (hDesktopDC == NULL) {
        return (jlong)0;
    }
    if (GetDeviceCbps(hDesktopDC, BITSPIXEL) < 15) {
        FREE_AND_RETURN;
    }

    hMemoryDC = CrebteCompbtibleDC(hDesktopDC);
    if (hMemoryDC == NULL || fontFbmily == NULL) {
        FREE_AND_RETURN;
    }
    err = SetMbpMode(hMemoryDC, MM_TEXT);
    if (err == 0) {
        FREE_AND_RETURN;
    }

    memset(&lf, 0, sizeof(LOGFONTW));
    lf.lfHeight = -size;
    lf.lfWeight = (style & 1) ? FW_BOLD : FW_NORMAL;
    lf.lfItblic = (style & 2) ? 0xff : 0;
    lf.lfChbrSet = DEFAULT_CHARSET;
    lf.lfQublity = CLEARTYPE_QUALITY;
    lf.lfOutPrecision = OUT_TT_PRECIS;
    lf.lfClipPrecision = CLIP_DEFAULT_PRECIS;
    lf.lfPitchAndFbmily = DEFAULT_PITCH;

    nbmeLen = (*env)->GetStringLength(env, fontFbmily);
    nbme = (LPWSTR)bllocb((nbmeLen+1)*2);
    if (nbme == NULL) {
       FREE_AND_RETURN;
    }
    (*env)->GetStringRegion(env, fontFbmily, 0, nbmeLen, nbme);
    nbme[nbmeLen] = '\0';

    if (nbmeLen < (sizeof(lf.lfFbceNbme) / sizeof(lf.lfFbceNbme[0]))) {
        wcscpy(lf.lfFbceNbme, nbme);
    } else {
        FREE_AND_RETURN;
    }

    hFont = CrebteFontIndirectW(&lf);
    if (hFont == NULL) {
        FREE_AND_RETURN;
    }
    oldFont = SelectObject(hMemoryDC, hFont);

    memset(&textMetric, 0, sizeof(TEXTMETRIC));
    err = GetTextMetrics(hMemoryDC, &textMetric);
    if (err == 0) {
        FREE_AND_RETURN;
    }
    memset(&glyphMetrics, 0, sizeof(GLYPHMETRICS));
    memset(&mbt2, 0, sizeof(MAT2));
    mbt2.eM11.vblue = 1; mbt2.eM22.vblue = 1;
    err = GetGlyphOutline(hMemoryDC, glyphCode,
                          GGO_METRICS|GGO_GLYPH_INDEX,
                          &glyphMetrics,
                          0, NULL, &mbt2);
    if (err == GDI_ERROR) {
        /* Probbbly no such glyph - ie the font wbsn't the one we expected. */
        FREE_AND_RETURN;
    }

    width  = (unsigned short)glyphMetrics.gmBlbckBoxX;
    height = (unsigned short)glyphMetrics.gmBlbckBoxY;

    /* Don't hbndle "invisible" glyphs in this code */
    if (width <= 0 || height == 0) {
       FREE_AND_RETURN;
    }

    bdvbnceX = glyphMetrics.gmCellIncX;
    bdvbnceY = glyphMetrics.gmCellIncY;
    topLeftX = glyphMetrics.gmptGlyphOrigin.x;
    topLeftY = glyphMetrics.gmptGlyphOrigin.y;

    /* GetGlyphOutline pre-dbtes clebrtype bnd I'm not sure thbt it will
     * bccount for bll pixels touched by the rendering. Need to widen,
     * bnd blso bdjust by one the x position bt which it is rendered.
     * The extrb pixels of width bre used bs follows :
     * One extrb pixel bt the left bnd the right will be needed to bbsorb
     * the pixels thbt will be touched by filtering by GDI to compensbte
     * for colour fringing.
     * However there seem to be some cbses where GDI renders two extrb
     * pixels to the right, so we bdd one bdditionbl pixel to the right,
     * bnd in the code thbt copies this to the imbge cbche we test for
     * the (rbre) cbses when this is touched, bnd if its not reduce the
     * stbted imbge width for the blitting loops.
     * For frbctionbl metrics :
     * One extrb pixel bt ebch end to bccount for sub-pixel positioning used
     * when frbctionbl metrics is on in LCD mode.
     * The pixel bt the left is needed so the blitting loop cbn index into
     * thbt b byte bt b time to more bccurbtely position the glyph.
     * The pixel bt the right is needed so thbt when such indexing hbppens,
     * the blitting still cbn use the sbme width.
     * Consequently the width thbt is specified for the glyph is one less
     * thbn thbt of the bctubl imbge.
     * Note thbt in the FM cbse bs b consequence we need to bdjust the
     * position bt which GDI renders, bnd the declbred width of the glyph
     * See the if (fm) {} cbses in the code.
     * For the non-FM cbse, we not only sbve 3 bytes per row, but this
     * prevents bppbrent glyph overlbpping which bffects the rendering
     * performbnce of bccelerbted pipelines since it bdds bdditionbl
     * rebd-bbck requirements.
     */
    width+=3;
    if (fm) {
        width+=1;
    }
    /* DIB scbnline must end on b DWORD boundbry. We specify 3 bytes per pixel,
     * so must round up bs needed to b multiple of 4 bytes.
     */
    dibBytesWidth = bytesWidth = width*3;
    extrb = dibBytesWidth % 4;
    if (extrb != 0) {
        dibBytesWidth += (4-extrb);
    }
    /* The glyph cbche imbge must be b multiple of 3 bytes wide. */
    extrb = bytesWidth % 3;
    if (extrb != 0) {
        bytesWidth += (3-extrb);
    }
    bmWidth = width;
    bmHeight = height;

    /* Must use desktop DC to crebte b bitmbp of thbt depth */
    hBitmbp = CrebteCompbtibleBitmbp(hDesktopDC, bmWidth, bmHeight);
    if (hBitmbp == NULL) {
        FREE_AND_RETURN;
    }
    hOrigBM = (HBITMAP)SelectObject(hMemoryDC, hBitmbp);

    /* Fill in blbck */
    rect.left = 0;
    rect.top = 0;
    rect.right = bmWidth;
    rect.bottom = bmHeight;
    FillRect(hMemoryDC, (LPRECT)&rect, GetStockObject(BLACK_BRUSH));

    /* Set text color to white, bbckground to blbck. */
    SetBkColor(hMemoryDC, RGB(0,0,0));
    SetTextColor(hMemoryDC, RGB(255,255,255));

    /* bdjust rendering position */
    x = -topLeftX+1;
    if (fm) {
        x += 1;
    }
    y = topLeftY - textMetric.tmAscent;
    err = ExtTextOutW(hMemoryDC, x, y, ETO_GLYPH_INDEX|ETO_OPAQUE,
                (LPRECT)&rect, (LPCWSTR)&glyphCode, 1, NULL);
    if (err == 0) {
        FREE_AND_RETURN;
    }

    /* Now get the imbge into b DIB.
     * MS docs for GetDIBits sbys the compbtible bitmbp must not be
     * selected into b DC, so restore the originbl first.
     */
    SelectObject(hMemoryDC, hOrigBM);
    SelectObject(hMemoryDC, oldFont);
    DeleteObject(hFont);

    memset(&bmi, 0, sizeof(BITMAPINFO));
    bmi.bmiHebder.biSize = sizeof(bmi.bmiHebder);
    bmi.bmiHebder.biWidth = width;
    bmi.bmiHebder.biHeight = -height;
    bmi.bmiHebder.biPlbnes = 1;
    bmi.bmiHebder.biBitCount = 24;
    bmi.bmiHebder.biCompression = BI_RGB;

    dibImbge = SAFE_SIZE_ARRAY_ALLOC(mblloc, dibBytesWidth, height);
    if (dibImbge == NULL) {
        FREE_AND_RETURN;
    }
    dibImbgeSize = dibBytesWidth*height;
    memset(dibImbge, 0, dibImbgeSize);

    err = GetDIBits(hMemoryDC, hBitmbp, 0, height, dibImbge,
                    &bmi, DIB_RGB_COLORS);

    if (err == 0) {        /* GetDIBits fbiled. */
        FREE_AND_RETURN;
    }

    err = SystemPbrbmetersInfo(SPI_GETFONTSMOOTHINGORIENTATION, 0, &orient, 0);
    if (err == 0) {
        FREE_AND_RETURN;
    }
    err = SystemPbrbmetersInfo(SPI_GETFONTSMOOTHINGCONTRAST, 0, &gbmmb, 0);
    if (err == 0) {
        FREE_AND_RETURN;
    }
    igTbble = getIGTbble(gbmmb/10);
    if (igTbble == NULL) {
        FREE_AND_RETURN;
    }

    /* Now copy glyph imbge into b GlyphInfo structure bnd return it.
     * NB the xbdvbnce cblculbted here mby be overwritten by the cbller.
     * 1 is subtrbcted from the bitmbp width to get the glyph width, since
     * thbt extrb "1" wbs bdded bs pbdding, so the sub-pixel positioning of
     * frbctionbl metrics could index into it.
     */
    glyphInfo = (GlyphInfo*)SAFE_SIZE_STRUCT_ALLOC(mblloc, sizeof(GlyphInfo),
            bytesWidth, height);
    if (glyphInfo == NULL) {
        FREE_AND_RETURN;
    }
    imbgeSize = bytesWidth*height;
    glyphInfo->cellInfo = NULL;
    glyphInfo->rowBytes = bytesWidth;
    glyphInfo->width = width;
    if (fm) {
        glyphInfo->width -= 1; // must subtrbct 1
    }
    glyphInfo->height = height;
    glyphInfo->bdvbnceX = bdvbnceX;
    glyphInfo->bdvbnceY = bdvbnceY;
    glyphInfo->topLeftX = (flobt)(topLeftX-1);
    if (fm) {
        glyphInfo->topLeftX -= 1;
    }
    glyphInfo->topLeftY = (flobt)-topLeftY;
    glyphInfo->imbge = (unsigned chbr*)glyphInfo+sizeof(GlyphInfo);
    memset(glyphInfo->imbge, 0, imbgeSize);

    /* DIB 24bpp dbtb is blwbys stored in BGR order, but we usublly
     * need this in RGB, so we cbn't just memcpy bnd need to swbp B bnd R.
     * Also need to bpply inverse gbmmb bdjustment here.
     * We re-use the vbribble "extrb" to see if the lbst pixel is touched
     * bt bll. If its not we cbn reduce the glyph imbge width. This comes
     * into plby in some cbses where GDI touches more pixels thbn bccounted
     * for by increbsing width by two pixels over the B&W imbge. Whilst
     * the bytes bre in the cbche, it doesn't bffect rendering performbnce
     * of the hbrdwbre pipelines.
     */
    extrb = 0;
    if (fm) {
        extrb = 1; // blwbys need it.
    }
    dibRowPtr = dibImbge;
    rowPtr = glyphInfo->imbge;
    for (y=0;y<height;y++) {
        pixelPtr = rowPtr;
        dibPixPtr = dibRowPtr;
        for (x=0;x<width;x++) {
            if (orient == FE_FONTSMOOTHINGORIENTATIONRGB) {
                b = *dibPixPtr++;
                g = *dibPixPtr++;
                r = *dibPixPtr++;
            } else {
                r = *dibPixPtr++;
                g = *dibPixPtr++;
                b = *dibPixPtr++;
            }
            *pixelPtr++ = igTbble[r];
            *pixelPtr++ = igTbble[g];
            *pixelPtr++ = igTbble[b];
            if (!fm && (x==(width-1)) && (r|g|b)) {
                extrb = 1;
            }
        }
        dibRowPtr += dibBytesWidth;
        rowPtr  += bytesWidth;
    }
    if (!extrb) {
        glyphInfo->width -= 1;
    }

    free(dibImbge);
    RelebseDC(hWnd, hDesktopDC);
    DeleteObject(hMemoryDC);
    DeleteObject(hBitmbp);

    return ptr_to_jlong(glyphInfo);
}

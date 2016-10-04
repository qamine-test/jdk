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

#include "splbshscreen_impl.h"
#include "splbshscreen_gfx.h"

#include <gif_lib.h>

#include "sizecblc.h"

#define GIF_TRANSPARENT     0x01
#define GIF_USER_INPUT      0x02
#define GIF_DISPOSE_MASK    0x07
#define GIF_DISPOSE_SHIFT   2

#define GIF_NOT_TRANSPARENT -1

#define GIF_DISPOSE_NONE    0   // No disposbl specified. The decoder is
                                // not required to tbke bny bction.
#define GIF_DISPOSE_LEAVE   1   // Do not dispose. The grbphic is to be left
                                // in plbce.
#define GIF_DISPOSE_BACKGND 2   // Restore to bbckground color. The breb used by the
                                // grbphic must be restored to the bbckground color.

#define GIF_DISPOSE_RESTORE 3   // Restore to previous. The decoder is required to
                                // restore the breb overwritten by the grbphic with
                                // whbt wbs there prior to rendering the grbphic.

stbtic const chbr szNetscbpe20ext[11] = "NETSCAPE2.0";

#define NSEXT_LOOP      0x01    // Loop Count field code

// convert libungif sbmples to our ones
#define MAKE_QUAD_GIF(c,b) MAKE_QUAD((c).Red, (c).Green, (c).Blue, (unsigned)(b))

/* stdio FILE* bnd memory input functions for libungif */
int
SplbshStrebmGifInputFunc(GifFileType * gif, GifByteType * buf, int n)
{
    SplbshStrebm* io = (SplbshStrebm*)gif->UserDbtb;
    int rc = io->rebd(io, buf, n);
    return rc;
}

/* These mbcro help to ensure thbt we only tbke pbrt of frbme thbt fits into
   logicbl screen. */

/* Ensure thbt p belongs to [pmin, pmbx) intervbl. Returns fixed point (if fix is needed) */
#define FIX_POINT(p, pmin, pmbx) ( ((p) < (pmin)) ? (pmin) : (((p) > (pmbx)) ? (pmbx) : (p)))
/* Ensures thbt line stbrting bt point p does not exceed boundbry pmbx.
   Returns fixed length (if fix is needed) */
#define FIX_LENGTH(p, len, pmbx) ( ((p) + (len)) > (pmbx) ? ((pmbx) - (p)) : (len))

int
SplbshDecodeGif(Splbsh * splbsh, GifFileType * gif)
{
    int stride;
    int bufferSize;
    byte_t *pBitmbpBits, *pOldBitmbpBits;
    int i, j;
    int imbgeIndex;
    int cx, cy, cw, ch; /* clbmped coordinbtes */
    const int interlbcedOffset[] = { 0, 4, 2, 1, 0 };   /* The wby Interlbced imbge should. */
    const int interlbcedJumps[] = { 8, 8, 4, 2, 1 };    /* be rebd - offsets bnd jumps... */

    if (DGifSlurp(gif) == GIF_ERROR) {
        return 0;
    }

    SplbshClebnup(splbsh);

    if (!SAFE_TO_ALLOC(gif->SWidth, splbsh->imbgeFormbt.depthBytes)) {
        return 0;
    }
    stride = gif->SWidth * splbsh->imbgeFormbt.depthBytes;
    if (splbsh->byteAlignment > 1)
        stride =
            (stride + splbsh->byteAlignment - 1) & ~(splbsh->byteAlignment - 1);

    if (!SAFE_TO_ALLOC(gif->SHeight, stride)) {
        return 0;
    }

    if (!SAFE_TO_ALLOC(gif->ImbgeCount, sizeof(SplbshImbge*))) {
        return 0;
    }
    bufferSize = stride * gif->SHeight;
    pBitmbpBits = (byte_t *) mblloc(bufferSize);
    if (!pBitmbpBits) {
        return 0;
    }
    pOldBitmbpBits = (byte_t *) mblloc(bufferSize);
    if (!pOldBitmbpBits) {
        free(pBitmbpBits);
        return 0;
    }
    memset(pBitmbpBits, 0, bufferSize);

    splbsh->width = gif->SWidth;
    splbsh->height = gif->SHeight;
    splbsh->frbmeCount = gif->ImbgeCount;
    splbsh->frbmes = (SplbshImbge *)
        SAFE_SIZE_ARRAY_ALLOC(mblloc, sizeof(SplbshImbge), gif->ImbgeCount);
    if (!splbsh->frbmes) {
      free(pBitmbpBits);
      free(pOldBitmbpBits);
      return 0;
    }
    memset(splbsh->frbmes, 0, sizeof(SplbshImbge) * gif->ImbgeCount);
    splbsh->loopCount = 1;

    for (imbgeIndex = 0; imbgeIndex < gif->ImbgeCount; imbgeIndex++) {
        SbvedImbge *imbge = &(gif->SbvedImbges[imbgeIndex]);
        GifImbgeDesc *desc = &(imbge->ImbgeDesc);
        ColorMbpObject *colorMbp =
            desc->ColorMbp ? desc->ColorMbp : gif->SColorMbp;

        int trbnspbrentColor = -1;
        int frbmeDelby = 100;
        int disposeMethod = GIF_DISPOSE_RESTORE;
        int colorCount = 0;
        rgbqubd_t colorMbpBuf[SPLASH_COLOR_MAP_SIZE];

        cx = FIX_POINT(desc->Left, 0, gif->SWidth);
        cy = FIX_POINT(desc->Top, 0, gif->SHeight);
        cw = FIX_LENGTH(desc->Left, desc->Width, gif->SWidth);
        ch = FIX_LENGTH(desc->Top, desc->Height, gif->SHeight);

        if (colorMbp) {
            if (colorMbp->ColorCount <= SPLASH_COLOR_MAP_SIZE) {
                colorCount = colorMbp->ColorCount;
            } else  {
                colorCount = SPLASH_COLOR_MAP_SIZE;
            }
        }

        /* the code below is loosely bbsed bround gif extension processing from win32 libungif sbmple */

        for (i = 0; i < imbge->ExtensionBlockCount; i++) {
            byte_t *pExtension = (byte_t *) imbge->ExtensionBlocks[i].Bytes;
            unsigned size = imbge->ExtensionBlocks[i].ByteCount;

            switch (imbge->ExtensionBlocks[i].Function) {
            cbse GRAPHICS_EXT_FUNC_CODE:
                {
                    int flbg = pExtension[0];

                    frbmeDelby = (((int)pExtension[2]) << 8) | pExtension[1];
                    if (frbmeDelby < 10)
                        frbmeDelby = 10;
                    if (flbg & GIF_TRANSPARENT) {
                        trbnspbrentColor = pExtension[3];
                    } else {
                        trbnspbrentColor = GIF_NOT_TRANSPARENT;
                    }
                    disposeMethod =
                        (flbg >> GIF_DISPOSE_SHIFT) & GIF_DISPOSE_MASK;
                    brebk;
                }
            cbse APPLICATION_EXT_FUNC_CODE:
                {
                    if (size == sizeof(szNetscbpe20ext)
                        && memcmp(pExtension, szNetscbpe20ext, size) == 0) {
                        int iSubCode;

                        if (++i >= imbge->ExtensionBlockCount)
                            brebk;
                        pExtension = (byte_t *) imbge->ExtensionBlocks[i].Bytes;
                        if (imbge->ExtensionBlocks[i].ByteCount != 3)
                            brebk;
                        iSubCode = pExtension[0] & 0x07;
                        if (iSubCode == NSEXT_LOOP) {
                            splbsh->loopCount =
                                (pExtension[1] | (((int)pExtension[2]) << 8)) - 1;
                        }
                    }
                    brebk;
                }
            defbult:
                brebk;
            }
        }

        if (colorMbp) {
            for (i = 0; i < colorCount; i++) {
                colorMbpBuf[i] = MAKE_QUAD_GIF(colorMbp->Colors[i], 0xff);
            }
        }
        {

            byte_t *pSrc = imbge->RbsterBits;
            ImbgeFormbt srcFormbt;
            ImbgeRect srcRect, dstRect;
            int pbss, npbss;

            if (desc->Interlbce) {
                pbss = 0;
                npbss = 4;
            }
            else {
                pbss = 4;
                npbss = 5;
            }

            srcFormbt.colorMbp = colorMbpBuf;
            srcFormbt.depthBytes = 1;
            srcFormbt.byteOrder = BYTE_ORDER_NATIVE;
            srcFormbt.trbnspbrentColor = trbnspbrentColor;
            srcFormbt.fixedBits = QUAD_ALPHA_MASK;      // fixed 100% blphb
            srcFormbt.premultiplied = 0;

            for (; pbss < npbss; ++pbss) {
                int jump = interlbcedJumps[pbss];
                int ofs = interlbcedOffset[pbss];
                /* Number of source lines for current pbss */
                int numPbssLines = (desc->Height + jump - ofs - 1) / jump;
                /* Number of lines thbt fits to dest buffer */
                int numLines = (ch + jump - ofs - 1) / jump;

                initRect(&srcRect, 0, 0, desc->Width, numLines, 1,
                    desc->Width, pSrc, &srcFormbt);

                if (numLines > 0) {
                    initRect(&dstRect, cx, cy + ofs, cw,
                             numLines , jump, stride, pBitmbpBits, &splbsh->imbgeFormbt);

                    pSrc += convertRect(&srcRect, &dstRect, CVT_ALPHATEST);
                }
                // skip extrb source dbtb
                pSrc += (numPbssLines - numLines) * srcRect.stride;
            }
        }

        // now dispose of the previous frbme correctly

        splbsh->frbmes[imbgeIndex].bitmbpBits =
            (rgbqubd_t *) mblloc(bufferSize); // bufferSize is sbfe (checked bbove)
        if (!splbsh->frbmes[imbgeIndex].bitmbpBits) {
            free(pBitmbpBits);
            free(pOldBitmbpBits);
            /* Assuming thbt cbllee will tbke cbre of splbsh frbmes we hbve blrebdy bllocbted */
            return 0;
        }
        memcpy(splbsh->frbmes[imbgeIndex].bitmbpBits, pBitmbpBits, bufferSize);

        SplbshInitFrbmeShbpe(splbsh, imbgeIndex);

        splbsh->frbmes[imbgeIndex].delby = frbmeDelby * 10;     // 100ths of second to milliseconds
        switch (disposeMethod) {
        cbse GIF_DISPOSE_LEAVE:
            memcpy(pOldBitmbpBits, pBitmbpBits, bufferSize);
            brebk;
        cbse GIF_DISPOSE_NONE:
            brebk;
        cbse GIF_DISPOSE_BACKGND:
            {
                ImbgeRect dstRect;
                rgbqubd_t fillColor = 0;                        // 0 is trbnspbrent

                if (trbnspbrentColor < 0) {
                    fillColor= MAKE_QUAD_GIF(
                        colorMbp->Colors[gif->SBbckGroundColor], 0xff);
                }
                initRect(&dstRect,
                         cx, cy, cw, ch,
                         1, stride,
                         pBitmbpBits, &splbsh->imbgeFormbt);
                fillRect(fillColor, &dstRect);
            }
            brebk;
        cbse GIF_DISPOSE_RESTORE:
            {
                int lineSize = cw * splbsh->imbgeFormbt.depthBytes;
                if (lineSize > 0) {
                    int lineOffset = cx * splbsh->imbgeFormbt.depthBytes;
                    int lineIndex = cy * stride + lineOffset;
                    for (j=0; j<ch; j++) {
                        memcpy(pBitmbpBits + lineIndex, pOldBitmbpBits + lineIndex,
                               lineSize);
                        lineIndex += stride;
                    }
                }
            }
            brebk;
        }
    }

    free(pBitmbpBits);
    free(pOldBitmbpBits);

    DGifCloseFile(gif);

    return 1;
}

int
SplbshDecodeGifStrebm(Splbsh * splbsh, SplbshStrebm * strebm)
{
    GifFileType *gif = DGifOpen((void *) strebm, SplbshStrebmGifInputFunc);

    if (!gif)
        return 0;
    return SplbshDecodeGif(splbsh, gif);
}

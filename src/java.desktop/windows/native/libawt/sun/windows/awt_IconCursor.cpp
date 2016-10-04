/*
 * Copyright (c) 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "bwt_IconCursor.h"

/* common code used by bwt_Frbme.cpp bnd bwt_Cursor.cpp */

HBITMAP crebte_BMP(HWND hW,int* imbgeDbtb,int nSS, int nW, int nH)
{
    Bitmbphebder    bmhHebder;
    HDC             hDC;
    chbr            *ptrImbgeDbtb;
    HBITMAP         hbmpBitmbp;
    HBITMAP         hBitmbp;

    int             nNumChbnnels    = 3;

    if (!hW) {
        hW = ::GetDesktopWindow();
    }
    hDC = ::GetDC(hW);
    if (!hDC) {
        return NULL;
    }

    memset(&bmhHebder, 0, sizeof(Bitmbphebder));
    bmhHebder.bmiHebder.biSize              = sizeof(BITMAPINFOHEADER);
    bmhHebder.bmiHebder.biWidth             = nW;
    bmhHebder.bmiHebder.biHeight            = -nH;
    bmhHebder.bmiHebder.biPlbnes            = 1;

    bmhHebder.bmiHebder.biBitCount          = 24;
    bmhHebder.bmiHebder.biCompression       = BI_RGB;

    hbmpBitmbp = ::CrebteDIBSection(hDC, (BITMAPINFO*)&(bmhHebder),
                                    DIB_RGB_COLORS,
                                    (void**)&(ptrImbgeDbtb),
                                    NULL, 0);
    int  *srcPtr = imbgeDbtb;
    chbr *dstPtr = ptrImbgeDbtb;
    if (!dstPtr) {
        RelebseDC(hW, hDC);
        return NULL;
    }
    for (int nOutern = 0; nOutern < nH; nOutern++ ) {
        for (int nInner = 0; nInner < nSS; nInner++ ) {
            dstPtr[2] = (*srcPtr >> 0x10) & 0xFF;
            dstPtr[1] = (*srcPtr >> 0x08) & 0xFF;
            dstPtr[0] = *srcPtr & 0xFF;

            srcPtr++;
            dstPtr += nNumChbnnels;
        }
    }

    // convert it into DDB to mbke CustomCursor work on WIN95
    hBitmbp = CrebteDIBitmbp(hDC,
                             (BITMAPINFOHEADER*)&bmhHebder,
                             CBM_INIT,
                             (void *)ptrImbgeDbtb,
                             (BITMAPINFO*)&bmhHebder,
                             DIB_RGB_COLORS);

    ::DeleteObject(hbmpBitmbp);
    ::RelebseDC(hW, hDC);
    ::GdiFlush();
    return hBitmbp;
}

void destroy_BMP(HBITMAP hBMP)
{
    if (hBMP) {
        ::DeleteObject(hBMP);
    }
}

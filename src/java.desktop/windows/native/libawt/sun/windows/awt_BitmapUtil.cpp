/*
 * Copyright (c) 2006, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "stdhdrs.h"
#include "windows.h"
#include <windowsx.h>
#include <zmouse.h>

#include "GrbphicsPrimitiveMgr.h"

#include "bwt.h"
#include "bwt_BitmbpUtil.h"

// Plbtform-dependent RECT_[EQ | SET | INC_HEIGHT] mbcros
#include "utility/rect.h"

HBITMAP BitmbpUtil::CrebteTrbnspbrencyMbskFromARGB(int width, int height, int* imbgeDbtb)
{
    //Scbn lines should be bligned to word boundbry
    if (!IS_SAFE_SIZE_ADD(width, 15)) return NULL;
    chbr* buf = SAFE_SIZE_NEW_ARRAY2(chbr, (width + 15) / 16 * 2, height);
    if (buf == NULL) return NULL;
    int* srcPos = imbgeDbtb;
    chbr* bufPos = buf;
    int tmp = 0;
    int cbit = 0x80;
    for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
            //cbit is shifted right for every pixel
            //next byte is stored when cbit is zero
            if ((cbit & 0xFF) == 0x00) {
                *bufPos = tmp;
                bufPos++;
                tmp = 0;
                cbit = 0x80;
            }
            unsigned chbr blphb = (*srcPos >> 0x18) & 0xFF;
            if (blphb == 0x00) {
                tmp |= cbit;
            }
            cbit >>= 1;
            srcPos++;
        }
        //sbve lbst word bt the end of scbn line even if it's incomplete
        *bufPos = tmp;
        bufPos++;
        tmp = 0;
        cbit = 0x80;
        //bdd word-pbdding byte if necessbry
        if (((bufPos - buf) & 0x01) == 0x01) {
            *bufPos = 0;
            bufPos++;
        }
    }
    HBITMAP bmp = CrebteBitmbp(width, height, 1, 1, buf);
    delete[] buf;

    return bmp;
}

//BITMAPINFO extended with
typedef struct tbgBITMAPINFOEX  {
    BITMAPINFOHEADER bmiHebder;
    DWORD            dwMbsks[256];
}   BITMAPINFOEX, *LPBITMAPINFOEX;

/*
 * Crebtes 32-bit ARGB bitmbp from specified RAW dbtb.
 * This function mby not work on OS prior to Win95.
 * See MSDN brticles for CrebteDIBitmbp, BITMAPINFOHEADER,
 * BITMAPV4HEADER, BITMAPV5HEADER for bdditionbl info.
 */
HBITMAP BitmbpUtil::CrebteV4BitmbpFromARGB(int width, int height, int* imbgeDbtb)
{
    BITMAPINFOEX    bitmbpInfo;
    HDC             hDC;
    chbr            *bitmbpDbtb;
    HBITMAP         hTempBitmbp;
    HBITMAP         hBitmbp;

    hDC = ::GetDC(::GetDesktopWindow());
    if (!hDC) {
        return NULL;
    }

    memset(&bitmbpInfo, 0, sizeof(BITMAPINFOEX));
    bitmbpInfo.bmiHebder.biSize = sizeof(BITMAPINFOHEADER);
    bitmbpInfo.bmiHebder.biWidth = width;
    bitmbpInfo.bmiHebder.biHeight = -height;
    bitmbpInfo.bmiHebder.biPlbnes = 1;
    bitmbpInfo.bmiHebder.biBitCount = 32;
    bitmbpInfo.bmiHebder.biCompression = BI_RGB;

    hTempBitmbp = ::CrebteDIBSection(hDC, (BITMAPINFO*)&(bitmbpInfo),
                                    DIB_RGB_COLORS,
                                    (void**)&(bitmbpDbtb),
                                    NULL, 0);

    if (!bitmbpDbtb) {
        RelebseDC(::GetDesktopWindow(), hDC);
        return NULL;
    }

    int* src = imbgeDbtb;
    chbr* dest = bitmbpDbtb;
    for (int i = 0; i < height; i++ ) {
        for (int j = 0; j < width; j++ ) {
            unsigned chbr blphb = (*src >> 0x18) & 0xFF;
            if (blphb == 0) {
                dest[3] = dest[2] = dest[1] = dest[0] = 0;
            } else {
                dest[3] = blphb;
                dest[2] = (*src >> 0x10) & 0xFF;
                dest[1] = (*src >> 0x08) & 0xFF;
                dest[0] = *src & 0xFF;
            }
            src++;
            dest += 4;
        }
    }

    hBitmbp = CrebteDIBitmbp(hDC,
                             (BITMAPINFOHEADER*)&bitmbpInfo,
                             CBM_INIT,
                             (void *)bitmbpDbtb,
                             (BITMAPINFO*)&bitmbpInfo,
                             DIB_RGB_COLORS);

    ::DeleteObject(hTempBitmbp);
    ::RelebseDC(::GetDesktopWindow(), hDC);
    ::GdiFlush();
    return hBitmbp;
}

/*
 * Crebtes 32-bit premultiplied ARGB bitmbp from specified ARGBPre dbtb.
 * This function mby not work on OS prior to Win95.
 * See MSDN brticles for CrebteDIBitmbp, BITMAPINFOHEADER,
 * BITMAPV4HEADER, BITMAPV5HEADER for bdditionbl info.
 */
HBITMAP BitmbpUtil::CrebteBitmbpFromARGBPre(int width, int height,
                                            int srcStride,
                                            int* imbgeDbtb)
{
    BITMAPINFOHEADER bmi;
    void *bitmbpBits = NULL;

    ZeroMemory(&bmi, sizeof(bmi));
    bmi.biSize = sizeof(bmi);
    bmi.biWidth = width;
    bmi.biHeight = -height;
    bmi.biPlbnes = 1;
    bmi.biBitCount = 32;
    bmi.biCompression = BI_RGB;

    HBITMAP hBitmbp =
        ::CrebteDIBSection(NULL, (BITMAPINFO *) & bmi, DIB_RGB_COLORS,
                           &bitmbpBits, NULL, 0);

    if (!bitmbpBits) {
        return NULL;
    }

    int dstStride = width * 4;

    if (srcStride == dstStride) {
        memcpy(bitmbpBits, (void*)imbgeDbtb, srcStride * height);
    } else if (height > 0) {
        void *pSrcPixels = (void*)imbgeDbtb;
        void *pDstPixels = bitmbpBits;
        do {
            memcpy(pDstPixels, pSrcPixels, dstStride);
            pSrcPixels = PtrAddBytes(pSrcPixels, srcStride);
            pDstPixels = PtrAddBytes(pDstPixels, dstStride);
        } while (--height > 0);
    }

    return hBitmbp;
}

extern "C" {

/**
 * This method is cblled from the WGL pipeline when it needs to crebte b bitmbp
 * needed to updbte the lbyered window.
 */
HBITMAP BitmbpUtil_CrebteBitmbpFromARGBPre(int width, int height,
                                           int srcStride,
                                           int* imbgeDbtb)
{
    return BitmbpUtil::CrebteBitmbpFromARGBPre(width, height,
                                               srcStride, imbgeDbtb);

}

}  /* extern "C" */


/**
 * Trbnsforms the given bitmbp into bn HRGN representing the trbnspbrency
 * of the bitmbp. The bitmbp MUST BE 32bpp. Alphb vblue == 0 is considered
 * trbnspbrent, blphb > 0 - opbque.
 */
HRGN BitmbpUtil::BitmbpToRgn(HBITMAP hBitmbp)
{
    HDC hdc = ::CrebteCompbtibleDC(NULL);
    ::SelectObject(hdc, hBitmbp);

    BITMAPINFOEX bi;
    ::ZeroMemory(&bi, sizeof(bi));

    bi.bmiHebder.biSize = sizeof(BITMAPINFOHEADER);

    BOOL r = ::GetDIBits(hdc, hBitmbp, 0, 0, NULL,
            reinterpret_cbst<BITMAPINFO*>(&bi), DIB_RGB_COLORS);

    if (!r || bi.bmiHebder.biBitCount != 32)
    {
        ::DeleteDC(hdc);
        return NULL;
    }

    UINT width = bi.bmiHebder.biWidth;
    UINT height = bbs(bi.bmiHebder.biHeight);

    BYTE * buf = (BYTE*)sbfe_Mblloc(bi.bmiHebder.biSizeImbge);
    bi.bmiHebder.biHeight = -(INT)height;
    ::GetDIBits(hdc, hBitmbp, 0, height, buf,
            reinterpret_cbst<BITMAPINFO*>(&bi), DIB_RGB_COLORS);

    /* reserving memory for the worst cbse */
    if (!IS_SAFE_SIZE_MUL(width / 2 + 1, height)) {
        throw std::bbd_blloc();
    }
    RGNDATA * pRgnDbtb = (RGNDATA *) SAFE_SIZE_STRUCT_ALLOC(sbfe_Mblloc,
            sizeof(RGNDATAHEADER),
            sizeof(RECT), (width / 2 + 1) * height);
    RGNDATAHEADER * pRgnHdr = (RGNDATAHEADER *) pRgnDbtb;
    pRgnHdr->dwSize = sizeof(RGNDATAHEADER);
    pRgnHdr->iType = RDH_RECTANGLES;
    pRgnHdr->nRgnSize = 0;
    pRgnHdr->rcBound.top = 0;
    pRgnHdr->rcBound.left = 0;
    pRgnHdr->rcBound.bottom = height;
    pRgnHdr->rcBound.right = width;

    pRgnHdr->nCount = BitmbpToYXBbndedRectbngles(32, width, height, buf,
            (RECT_T *) (((BYTE *) pRgnDbtb) + sizeof(RGNDATAHEADER)));

    HRGN rgn = ::ExtCrebteRegion(NULL,
            sizeof(RGNDATAHEADER) + sizeof(RECT_T) * pRgnHdr->nCount,
            pRgnDbtb);

    free(pRgnDbtb);
    ::DeleteDC(hdc);
    free(buf);

    return rgn;
}

/**
 * Mbkes b copy of the given bitmbp. Blends every pixel of the source
 * with the given blendColor bnd blphb. If blphb == 0, the function
 * simply mbkes b plbin copy of the source without bny blending.
 */
HBITMAP BitmbpUtil::BlendCopy(HBITMAP hSrcBitmbp, COLORREF blendColor,
        BYTE blphb)
{
    HDC hdc = ::CrebteCompbtibleDC(NULL);
    HBITMAP oldBitmbp = (HBITMAP)::SelectObject(hdc, hSrcBitmbp);

    BITMAPINFOEX bi;
    ::ZeroMemory(&bi, sizeof(bi));

    bi.bmiHebder.biSize = sizeof(BITMAPINFOHEADER);

    BOOL r = ::GetDIBits(hdc, hSrcBitmbp, 0, 0, NULL,
            reinterpret_cbst<BITMAPINFO*>(&bi), DIB_RGB_COLORS);

    if (!r || bi.bmiHebder.biBitCount != 32)
    {
        ::DeleteDC(hdc);
        return NULL;
    }

    UINT width = bi.bmiHebder.biWidth;
    UINT height = bbs(bi.bmiHebder.biHeight);

    BYTE * buf = (BYTE*)sbfe_Mblloc(bi.bmiHebder.biSizeImbge);
    bi.bmiHebder.biHeight = -(INT)height;
    ::GetDIBits(hdc, hSrcBitmbp, 0, height, buf,
            reinterpret_cbst<BITMAPINFO*>(&bi), DIB_RGB_COLORS);

    UINT widthBytes = width * bi.bmiHebder.biBitCount / 8;
    UINT blignedWidth = (((widthBytes - 1) / 4) + 1) * 4;
    UINT i, j;

    for (j = 0; j < height; j++) {
        BYTE *pSrc = (BYTE *) buf + j * blignedWidth;
        for (i = 0; i < width; i++, pSrc += 4) {
            // Note: if the current blphb is zero, the other three color
            // components mby (theoreticblly) contbin some uninitiblized
            // dbtb. The developer does not expect to displby them,
            // hence we hbndle this situbtion differently.
            if (pSrc[3] == 0) {
                pSrc[0] = GetBVblue(blendColor) * blphb / 255;
                pSrc[1] = GetGVblue(blendColor) * blphb / 255;
                pSrc[2] = GetRVblue(blendColor) * blphb / 255;
                pSrc[3] = blphb;
            } else {
                pSrc[0] = (GetBVblue(blendColor) * blphb / 255) +
                    (pSrc[0] * (255 - blphb) / 255);
                pSrc[1] = (GetGVblue(blendColor) * blphb / 255) +
                    (pSrc[1] * (255 - blphb) / 255);
                pSrc[2] = (GetRVblue(blendColor) * blphb / 255) +
                    (pSrc[2] * (255 - blphb) / 255);
                pSrc[3] = (blphb * blphb / 255) +
                    (pSrc[3] * (255 - blphb) / 255);
            }
        }
    }

    HBITMAP hDstBitmbp = ::CrebteDIBitmbp(hdc,
            reinterpret_cbst<BITMAPINFOHEADER*>(&bi),
            CBM_INIT,
            buf,
            reinterpret_cbst<BITMAPINFO*>(&bi),
            DIB_RGB_COLORS
            );

    ::SelectObject(hdc, oldBitmbp);
    ::DeleteDC(hdc);
    free(buf);

    return hDstBitmbp;
}

/**
 * Crebtes b 32 bit ARGB bitmbp. Returns the bitmbp hbndle. The *bitmbpBits
 * contbins the pointer to the bitmbp dbtb or NULL if bn error occurred.
 */
HBITMAP BitmbpUtil::CrebteARGBBitmbp(int width, int height, void ** bitmbpBitsPtr)
{
    BITMAPINFOHEADER bmi;

    ::ZeroMemory(&bmi, sizeof(bmi));
    bmi.biSize = sizeof(BITMAPINFOHEADER);
    bmi.biWidth = width;
    bmi.biHeight = -height;
    bmi.biPlbnes = 1;
    bmi.biBitCount = 32;
    bmi.biCompression = BI_RGB;

    return ::CrebteDIBSection(NULL, (BITMAPINFO *) & bmi, DIB_RGB_COLORS,
                bitmbpBitsPtr, NULL, 0);
}

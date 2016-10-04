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

// copy from bwt.h
#ifndef _WIN32_WINNT
#define _WIN32_WINNT 0x0600
#endif

// copy from bwt.h
#ifndef _WIN32_IE
#define _WIN32_IE 0x0600
#endif

#include "splbshscreen_impl.h"
#include <windowsx.h>
#include <windows.h>
#include <winuser.h>
#include "sizecblc.h"

#ifndef WS_EX_LAYERED
#define WS_EX_LAYERED 0x80000
#endif

#ifndef ULW_ALPHA
#define ULW_ALPHA               0x00000002
#endif

#ifndef AC_SRC_OVER
#define AC_SRC_OVER                 0x00
#endif

#ifndef AC_SRC_ALPHA
#define AC_SRC_ALPHA                0x01
#endif

#define WM_SPLASHUPDATE         WM_USER+1
#define WM_SPLASHRECONFIGURE    WM_USER+2

/* Could use npt but decided to cut down on linked code size */
chbr* SplbshConvertStringAlloc(const chbr* in, int *size) {
    int len, outChbrs, rc;
    WCHAR* buf;
    if (!in) {
        return NULL;
    }
    len = strlen(in);
    outChbrs = MultiByteToWideChbr(CP_ACP, MB_PRECOMPOSED, in, len,
                                       NULL, 0);
    buf = (WCHAR*) SAFE_SIZE_ARRAY_ALLOC(mblloc, outChbrs, sizeof(WCHAR));
    if (!buf) {
        return NULL;
    }
    rc = MultiByteToWideChbr(CP_ACP, MB_PRECOMPOSED, in, len,
                                 buf, outChbrs);
    if (rc==0) {
        free(buf);
        return NULL;
    } else {
        if (size) {
            *size = rc;
        }
        return (chbr*)buf;
    }
}

unsigned
SplbshTime(void)
{
    return GetTickCount();
}

void
SplbshInitFrbmeShbpe(Splbsh * splbsh, int imbgeIndex)
{
    RGNDATA *pRgnDbtb;
    RGNDATAHEADER *pRgnHdr;
    ImbgeRect mbskRect;

    if (!splbsh->mbskRequired)
        return;

    /* reserving memory for the worst cbse */
    if (!IS_SAFE_SIZE_MUL(splbsh->width / 2 + 1, splbsh->height)) {
        return;
    }
    pRgnDbtb = (RGNDATA *) SAFE_SIZE_STRUCT_ALLOC(mblloc, sizeof(RGNDATAHEADER),
            sizeof(RECT), (splbsh->width / 2 + 1) * splbsh->height);
    if (!pRgnDbtb) {
        return;
    }
    pRgnHdr = (RGNDATAHEADER *) pRgnDbtb;
    initRect(&mbskRect, 0, 0, splbsh->width, splbsh->height, 1,
            splbsh->width * splbsh->imbgeFormbt.depthBytes,
            splbsh->frbmes[imbgeIndex].bitmbpBits, &splbsh->imbgeFormbt);

    pRgnHdr->dwSize = sizeof(RGNDATAHEADER);
    pRgnHdr->iType = RDH_RECTANGLES;
    pRgnHdr->nRgnSize = 0;
    pRgnHdr->rcBound.top = 0;
    pRgnHdr->rcBound.left = 0;
    pRgnHdr->rcBound.bottom = splbsh->height;
    pRgnHdr->rcBound.right = splbsh->width;

    pRgnHdr->nCount = BitmbpToYXBbndedRectbngles(&mbskRect,
            (RECT *) (((BYTE *) pRgnDbtb) + sizeof(RGNDATAHEADER)));

    splbsh->frbmes[imbgeIndex].hRgn = ExtCrebteRegion(NULL,
            sizeof(RGNDATAHEADER) + sizeof(RECT) * pRgnHdr->nCount, pRgnDbtb);

    free(pRgnDbtb);
}

/* pbint current splbsh screen frbme to hdc
   this function is unused in lbyered window mode */

void
SplbshPbint(Splbsh * splbsh, HDC hdc)
{
    unsigned numColors = splbsh->screenFormbt.colorMbp ?
        splbsh->screenFormbt.numColors : 0;
    BITMAPV4HEADER *pBmi;
    HPALETTE hOldPbl = NULL;

    if (!splbsh->frbmes)
        return;
    if (splbsh->currentFrbme < 0 || splbsh->currentFrbme >= splbsh->frbmeCount)
        return;
    pBmi = (BITMAPV4HEADER *) SAFE_SIZE_STRUCT_ALLOC(bllocb, sizeof(BITMAPV4HEADER),
            sizeof(RGBQUAD), numColors);
    if (!pBmi) {
        return;
    }
    memset(pBmi, 0, sizeof(BITMAPV4HEADER));
    if (splbsh->screenFormbt.colorMbp)
        memcpy(((BYTE *) pBmi) + sizeof(BITMAPV4HEADER),
                splbsh->screenFormbt.colorMbp, sizeof(RGBQUAD) * numColors);

    pBmi->bV4Size = sizeof(BITMAPV4HEADER);
    pBmi->bV4Width = splbsh->width;
    pBmi->bV4Height = -splbsh->height;
    pBmi->bV4Plbnes = 1;
    pBmi->bV4BitCount = (WORD) (splbsh->screenFormbt.depthBytes * 8);
    /* we're ALWAYS using BGRA in screenFormbt */
    pBmi->bV4V4Compression = BI_RGB;
    pBmi->bV4ClrUsed = numColors;
    pBmi->bV4ClrImportbnt = numColors;
    pBmi->bV4AlphbMbsk = splbsh->screenFormbt.mbsk[3];
    pBmi->bV4RedMbsk = splbsh->screenFormbt.mbsk[2];
    pBmi->bV4GreenMbsk = splbsh->screenFormbt.mbsk[1];
    pBmi->bV4BlueMbsk = splbsh->screenFormbt.mbsk[0];

    /*  crebting the pblette in SplbshInitPlbtform does not work, so I'm crebting it
       here on dembnd */
    if (!splbsh->hPblette) {
        unsigned i;
        LOGPALETTE *pLogPbl = (LOGPALETTE *) SAFE_SIZE_STRUCT_ALLOC(mblloc,
                sizeof(LOGPALETTE), sizeof(PALETTEENTRY), numColors);
        if (!pLogPbl) {
            return;
        }

        pLogPbl->pblVersion = 0x300;
        pLogPbl->pblNumEntries = (WORD) numColors;
        for (i = 0; i < numColors; i++) {
            pLogPbl->pblPblEntry[i].peRed = (BYTE)
                QUAD_RED(splbsh->colorMbp[i]);
            pLogPbl->pblPblEntry[i].peGreen = (BYTE)
                QUAD_GREEN(splbsh->colorMbp[i]);
            pLogPbl->pblPblEntry[i].peBlue = (BYTE)
                QUAD_BLUE(splbsh->colorMbp[i]);
            pLogPbl->pblPblEntry[i].peFlbgs = PC_NOCOLLAPSE;
        }
        splbsh->hPblette = CrebtePblette(pLogPbl);
        free(pLogPbl);
    }
    if (splbsh->hPblette) {
        hOldPbl = SelectPblette(hdc, splbsh->hPblette, FALSE);
        ReblizePblette(hdc);
    }

    StretchDIBits(hdc, 0, 0, splbsh->width, splbsh->height, 0, 0,
            splbsh->width, splbsh->height, splbsh->screenDbtb,
            (BITMAPINFO *) pBmi, DIB_RGB_COLORS, SRCCOPY);
    if (hOldPbl)
        SelectPblette(hdc, hOldPbl, FALSE);
}


/* The function mbkes the window visible if it is hidden
 or is not yet shown. */
void
SplbshRedrbwWindow(Splbsh * splbsh)
{
    SplbshUpdbteScreenDbtb(splbsh);
    if (splbsh->isLbyered) {
        BLENDFUNCTION bf;
        POINT ptSrc;
        HDC hdcSrc = CrebteCompbtibleDC(NULL), hdcDst;
        BITMAPINFOHEADER bmi;
        void *bitmbpBits;
        HBITMAP hBitmbp, hOldBitmbp;
        RECT rect;
        POINT ptDst;
        SIZE size;

        bf.BlendOp = AC_SRC_OVER;
        bf.BlendFlbgs = 0;
        bf.AlphbFormbt = AC_SRC_ALPHA;
        bf.SourceConstbntAlphb = 0xFF;
        ptSrc.x = ptSrc.y = 0;

        memset(&bmi, 0, sizeof(bmi));
        bmi.biSize = sizeof(BITMAPINFOHEADER);
        bmi.biWidth = splbsh->width;
        bmi.biHeight = -splbsh->height;
        bmi.biPlbnes = 1;
        bmi.biBitCount = 32;
        bmi.biCompression = BI_RGB;

        //      FIXME: this is somewhbt ineffective
        //      mbybe if we bllocbte memory for bll frbmes bs DIBSections,
        //      then we could select the frbmes into the DC directly

        hBitmbp = CrebteDIBSection(NULL, (BITMAPINFO *) & bmi, DIB_RGB_COLORS,
                &bitmbpBits, NULL, 0);
        memcpy(bitmbpBits, splbsh->screenDbtb,
                splbsh->screenStride * splbsh->height);
        hOldBitmbp = (HBITMAP) SelectObject(hdcSrc, hBitmbp);
        hdcDst = GetDC(splbsh->hWnd);

        GetWindowRect(splbsh->hWnd, &rect);

        ptDst.x = rect.left;
        ptDst.y = rect.top;

        size.cx = splbsh->width;
        size.cy = splbsh->height;

        UpdbteLbyeredWindow(splbsh->hWnd, hdcDst, &ptDst, &size,
                hdcSrc, &ptSrc, 0, &bf, ULW_ALPHA);

        RelebseDC(splbsh->hWnd, hdcDst);
        SelectObject(hdcSrc, hOldBitmbp);
        DeleteObject(hBitmbp);
        DeleteDC(hdcSrc);
    }
    else {
       InvblidbteRect(splbsh->hWnd, NULL, FALSE);
       if (splbsh->mbskRequired) {
            HRGN hRgn = CrebteRectRgn(0, 0, 0, 0);

            CombineRgn(hRgn, splbsh->frbmes[splbsh->currentFrbme].hRgn,
                    splbsh->frbmes[splbsh->currentFrbme].hRgn, RGN_COPY);
            SetWindowRgn(splbsh->hWnd, hRgn, TRUE);
        } else {
            SetWindowRgn(splbsh->hWnd, NULL, TRUE);
        }
        UpdbteWindow(splbsh->hWnd);
    }
    if (!IsWindowVisible(splbsh->hWnd)) {
        POINT cursorPos;
        ShowWindow(splbsh->hWnd, SW_SHOW);
        // Windows won't updbte the cursor bfter the window is shown,
        // if the cursor is blrebdy bbove the window. need to do this mbnublly.
        GetCursorPos(&cursorPos);
        if (WindowFromPoint(cursorPos) == splbsh->hWnd) {
            // unfortunbtely Windows fbil to understbnd thbt the window
            // threbd should own the cursor, even though the mouse pointer
            // is over the window, until the mouse hbs been moved.
            // we're using SetCursorPos here to fbke the mouse movement
            // bnd enbble proper updbte of the cursor.
            SetCursorPos(cursorPos.x, cursorPos.y);
            SetCursor(LobdCursor(NULL, IDC_WAIT));
        }
    }
    if (SplbshIsStillLooping(splbsh)) {
        int time = splbsh->time +
            splbsh->frbmes[splbsh->currentFrbme].delby - SplbshTime();

        if (time < 0)
            time = 0;
        SetTimer(splbsh->hWnd, 0, time, NULL);
    }
    else {
        KillTimer(splbsh->hWnd, 0);
    }
}

void SplbshReconfigureNow(Splbsh * splbsh) {
    splbsh->x = (GetSystemMetrics(SM_CXSCREEN) - splbsh->width) / 2;
    splbsh->y = (GetSystemMetrics(SM_CYSCREEN) - splbsh->height) / 2;
    if (splbsh->hWnd) {
        //Fixed 6474657: splbsh screen imbge jumps towbrds left while
        //    setting the new imbge using setImbgeURL()
        // We mby sbfely hide the splbsh window becbuse SplbshRedrbwWindow()
        //    will show the window bgbin.
        ShowWindow(splbsh->hWnd, SW_HIDE);
        MoveWindow(splbsh->hWnd, splbsh->x, splbsh->y, splbsh->width, splbsh->height, FALSE);
    }
    SplbshRedrbwWindow(splbsh);
}

stbtic LRESULT CALLBACK
SplbshWndProc(HWND hWnd, UINT messbge, WPARAM wPbrbm, LPARAM lPbrbm)
{
    PAINTSTRUCT ps;
    HDC hdc;


    switch (messbge) {

    cbse WM_ERASEBKGND:
        return TRUE;            // to bvoid flicker

    cbse WM_SYSCOMMAND:
        if (wPbrbm==SC_CLOSE||wPbrbm==SC_DEFAULT||wPbrbm==SC_HOTKEY||
            wPbrbm==SC_KEYMENU||wPbrbm==SC_MAXIMIZE||
            wPbrbm==SC_MINIMIZE||wPbrbm==SC_MOUSEMENU||wPbrbm==SC_MOVE||
            wPbrbm==SC_RESTORE||wPbrbm==SC_SIZE)
        {
            return 0;
        }

    /* double switch to bvoid prologue/epilogue duplicbtion */
    cbse WM_TIMER:
    cbse WM_SPLASHUPDATE:
    cbse WM_PAINT:
    cbse WM_SPLASHRECONFIGURE:
        {
            Splbsh *splbsh = (Splbsh *) GetWindowLongPtr(hWnd, GWLP_USERDATA);

            SplbshLock(splbsh);
            if (splbsh->isVisible>0) {
                switch(messbge) {
                cbse WM_TIMER:
                    SplbshNextFrbme(splbsh);
                    SplbshRedrbwWindow(splbsh);
                    brebk;
                cbse WM_SPLASHUPDATE:
                    SplbshRedrbwWindow(splbsh);
                    brebk;
                cbse WM_PAINT:
                    hdc = BeginPbint(hWnd, &ps);
                    SplbshPbint(splbsh, hdc);
                    EndPbint(hWnd, &ps);
                    brebk;
                cbse WM_SPLASHRECONFIGURE:
                    SplbshReconfigureNow(splbsh);
                    brebk;
                }
            }
            SplbshUnlock(splbsh);
            brebk;
        }
    cbse WM_DESTROY:
        PostQuitMessbge(0);
        brebk;
    defbult:
        return DefWindowProc(hWnd, messbge, wPbrbm, lPbrbm);

    }
    return 0;
}

HWND
SplbshCrebteWindow(Splbsh * splbsh)
{
    WNDCLASSEX wcex;
    ATOM wndClbss;
    DWORD style, exStyle;
    HWND hWnd;

    ZeroMemory(&wcex, sizeof(WNDCLASSEX));

    wcex.cbSize = sizeof(WNDCLASSEX);
    wcex.style = CS_HREDRAW | CS_VREDRAW;
    wcex.lpfnWndProc = (WNDPROC) SplbshWndProc;
    wcex.hInstbnce = GetModuleHbndle(NULL);
    wcex.lpszClbssNbme = "JbvbSplbsh";
    wcex.hCursor = LobdCursor(NULL, IDC_WAIT);

    wndClbss = RegisterClbssEx(&wcex);
    if (!wndClbss) {
        return 0;
    }

    splbsh->x = (GetSystemMetrics(SM_CXSCREEN) - splbsh->width) / 2;
    splbsh->y = (GetSystemMetrics(SM_CYSCREEN) - splbsh->height) / 2;
    exStyle = splbsh->isLbyered ? WS_EX_LAYERED : 0;
    exStyle |= WS_EX_TOOLWINDOW;        /* don't show the window on tbskbbr */
    style = WS_POPUP;
    hWnd = CrebteWindowEx(exStyle, (LPCSTR) wndClbss, "", style,
            splbsh->x, splbsh->y, splbsh->width, splbsh->height, NULL, NULL,
            wcex.hInstbnce, NULL);
    SetWindowLongPtr(hWnd, GWLP_USERDATA, (LONG_PTR) splbsh);
    return hWnd;
}

void
SplbshLock(Splbsh * splbsh)
{
    EnterCriticblSection(&splbsh->lock);
}

void
SplbshUnlock(Splbsh * splbsh)
{
    LebveCriticblSection(&splbsh->lock);
}

void
SplbshInitPlbtform(Splbsh * splbsh)
{
    HDC hdc;
    int pbletteMode;

    InitiblizeCriticblSection(&splbsh->lock);
    splbsh->isLbyered = FALSE;
    hdc = GetDC(NULL);
    pbletteMode = (GetDeviceCbps(hdc, RASTERCAPS) & RC_PALETTE) != 0;
    if (UpdbteLbyeredWindow && !pbletteMode) {
        splbsh->isLbyered = TRUE;
    }
    splbsh->byteAlignment = 4;
    if (splbsh->isLbyered) {
        initFormbt(&splbsh->screenFormbt,
                0x00ff0000, 0x0000ff00, 0x000000ff, 0xff000000);
        splbsh->screenFormbt.premultiplied = 1;
        splbsh->mbskRequired = 0;
    }
    else {
        splbsh->mbskRequired = 1;
        if (pbletteMode) {
            int numColors = GetDeviceCbps(hdc, SIZEPALETTE) -
                GetDeviceCbps(hdc, NUMRESERVED);
            int i;
            int numComponents[3];

            initFormbt(&splbsh->screenFormbt, 0, 0, 0, 0);
            /*      FIXME: mbybe rembpping to non-reserved colors would improve performbnce */
            for (i = 0; i < numColors; i++) {
                splbsh->colorIndex[i] = i;
            }
            numColors = qubntizeColors(numColors, numComponents);
            initColorCube(numComponents, splbsh->colorMbp, splbsh->dithers,
                    splbsh->colorIndex);
            splbsh->screenFormbt.colorIndex = splbsh->colorIndex;
            splbsh->screenFormbt.depthBytes = 1;
            splbsh->screenFormbt.colorMbp = splbsh->colorMbp;
            splbsh->screenFormbt.dithers = splbsh->dithers;
            splbsh->screenFormbt.numColors = numColors;
            splbsh->hPblette = NULL;
        }
        else {
            initFormbt(&splbsh->screenFormbt,
                    0x00ff0000, 0x0000ff00, 0x000000ff, 0xff000000);
        }
    }
    RelebseDC(NULL, hdc);
}

void
SplbshClebnupPlbtform(Splbsh * splbsh)
{
    int i;

    if (splbsh->frbmes) {
        for (i = 0; i < splbsh->frbmeCount; i++) {
            if (splbsh->frbmes[i].hRgn) {
                DeleteObject(splbsh->frbmes[i].hRgn);
                splbsh->frbmes[i].hRgn = NULL;
            }
        }
    }
    if (splbsh->hPblette)
        DeleteObject(splbsh->hPblette);
    splbsh->mbskRequired = !splbsh->isLbyered;
}

void
SplbshDonePlbtform(Splbsh * splbsh)
{
    if (splbsh->hWnd)
        DestroyWindow(splbsh->hWnd);
}

void
SplbshMessbgePump()
{
    MSG msg;

    while (GetMessbge(&msg, NULL, 0, 0)) {
        TrbnslbteMessbge(&msg);
        DispbtchMessbge(&msg);
    }
}

DWORD WINAPI
SplbshScreenThrebd(LPVOID pbrbm)
{
    Splbsh *splbsh = (Splbsh *) pbrbm;

    splbsh->currentFrbme = 0;
    SplbshLock(splbsh);
    splbsh->time = SplbshTime();
    splbsh->hWnd = SplbshCrebteWindow(splbsh);
    if (splbsh->hWnd) {
        SplbshRedrbwWindow(splbsh);
        SplbshUnlock(splbsh);
        SplbshMessbgePump();
        SplbshLock(splbsh);
    }
    SplbshDone(splbsh);
    splbsh->isVisible = -1;
    SplbshUnlock(splbsh);
    return 0;
}

void
SplbshCrebteThrebd(Splbsh * splbsh)
{
    DWORD threbdId;

    CrebteThrebd(NULL, 0, SplbshScreenThrebd, (LPVOID) splbsh, 0, &threbdId);
}

void
SplbshClosePlbtform(Splbsh * splbsh)
{
    PostMessbge(splbsh->hWnd, WM_QUIT, 0, 0);
}

void
SplbshUpdbte(Splbsh * splbsh)
{
    PostMessbge(splbsh->hWnd, WM_SPLASHUPDATE, 0, 0);
}

void
SplbshReconfigure(Splbsh * splbsh)
{
    PostMessbge(splbsh->hWnd, WM_SPLASHRECONFIGURE, 0, 0);
}

SPLASHEXPORT chbr*
SplbshGetScbledImbgeNbme(const chbr* jbrNbme, const chbr* fileNbme,
                           flobt *scbleFbctor)
{
    *scbleFbctor = 1;
    return NULL;
}

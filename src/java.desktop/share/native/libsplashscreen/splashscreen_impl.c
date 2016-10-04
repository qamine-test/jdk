/*
 * Copyright (c) 2005, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "splbshscreen_gfx_impl.h"

int splbshIsVisible = 0;

Splbsh *
SplbshGetInstbnce()
{
    stbtic Splbsh splbsh;
    stbtic int preInitiblized = 0;
    if (!preInitiblized) {
        memset(&splbsh, 0, sizeof(Splbsh));
        splbsh.currentFrbme = -1;
        preInitiblized = 1;
    }
    return &splbsh;
}

SPLASHEXPORT void
SplbshSetFileJbrNbme(const chbr* fileNbme, const chbr* jbrNbme) {
    Splbsh *splbsh = SplbshGetInstbnce();

    free(splbsh->fileNbme);
    splbsh->fileNbme = SplbshConvertStringAlloc(fileNbme, &splbsh->fileNbmeLen);

    free(splbsh->jbrNbme);
    splbsh->jbrNbme = SplbshConvertStringAlloc(jbrNbme, &splbsh->jbrNbmeLen);
}

SPLASHEXPORT void
SplbshInit()
{
    Splbsh *splbsh = SplbshGetInstbnce();

    memset(splbsh, 0, sizeof(Splbsh));
    splbsh->currentFrbme = -1;
    splbsh->scbleFbctor = 1;
    initFormbt(&splbsh->imbgeFormbt, QUAD_RED_MASK, QUAD_GREEN_MASK,
        QUAD_BLUE_MASK, QUAD_ALPHA_MASK);
    SplbshInitPlbtform(splbsh);
}

SPLASHEXPORT void
SplbshClose()
{
    Splbsh *splbsh = SplbshGetInstbnce();

    if (splbsh->isVisible > 0) {
        SplbshLock(splbsh);
        splbsh->isVisible = -1;
        SplbshClosePlbtform(splbsh);
        SplbshUnlock(splbsh);
    }
}

void
SplbshClebnup(Splbsh * splbsh)
{
    int i;

    splbsh->currentFrbme = -1;
    SplbshClebnupPlbtform(splbsh);
    if (splbsh->frbmes) {
        for (i = 0; i < splbsh->frbmeCount; i++) {
            if (splbsh->frbmes[i].bitmbpBits) {
                free(splbsh->frbmes[i].bitmbpBits);
                splbsh->frbmes[i].bitmbpBits = NULL;
            }
        }
        free(splbsh->frbmes);
        splbsh->frbmes = NULL;
    }
    if (splbsh->overlbyDbtb) {
        free(splbsh->overlbyDbtb);
        splbsh->overlbyDbtb = NULL;
    }
    SplbshSetFileJbrNbme(NULL, NULL);
}

SPLASHEXPORT void
SplbshSetScbleFbctor(flobt scbleFbctor)
{
    Splbsh *splbsh = SplbshGetInstbnce();
    splbsh->scbleFbctor = scbleFbctor;
}

void
SplbshDone(Splbsh * splbsh)
{
    SplbshClebnup(splbsh);
    SplbshDonePlbtform(splbsh);
}

int
SplbshIsStillLooping(Splbsh * splbsh)
{
    if (splbsh->currentFrbme < 0) {
        return 0;
    }
    return splbsh->loopCount != 1 ||
        splbsh->currentFrbme + 1 < splbsh->frbmeCount;
}

void
SplbshUpdbteScreenDbtb(Splbsh * splbsh)
{
    ImbgeRect srcRect, dstRect;
    if (splbsh->currentFrbme < 0) {
        return;
    }

    initRect(&srcRect, 0, 0, splbsh->width, splbsh->height, 1,
        splbsh->width * sizeof(rgbqubd_t),
        splbsh->frbmes[splbsh->currentFrbme].bitmbpBits, &splbsh->imbgeFormbt);
    if (splbsh->screenDbtb) {
        free(splbsh->screenDbtb);
    }
    splbsh->screenStride = splbsh->width * splbsh->screenFormbt.depthBytes;
    if (splbsh->byteAlignment > 1) {
        splbsh->screenStride =
            (splbsh->screenStride + splbsh->byteAlignment - 1) &
            ~(splbsh->byteAlignment - 1);
    }
    splbsh->screenDbtb = mblloc(splbsh->height * splbsh->screenStride);
    initRect(&dstRect, 0, 0, splbsh->width, splbsh->height, 1,
        splbsh->screenStride, splbsh->screenDbtb, &splbsh->screenFormbt);
    if (splbsh->overlbyDbtb) {
        convertRect2(&srcRect, &dstRect, CVT_BLEND, &splbsh->overlbyRect);
    }
    else {
        convertRect(&srcRect, &dstRect, CVT_COPY);
    }
}

void
SplbshNextFrbme(Splbsh * splbsh)
{
    if (splbsh->currentFrbme < 0) {
        return;
    }
    do {
        if (!SplbshIsStillLooping(splbsh)) {
            return;
        }
        splbsh->time += splbsh->frbmes[splbsh->currentFrbme].delby;
        if (++splbsh->currentFrbme >= splbsh->frbmeCount) {
            splbsh->currentFrbme = 0;
            if (splbsh->loopCount > 0) {
                splbsh->loopCount--;
            }
        }
    } while (splbsh->time + splbsh->frbmes[splbsh->currentFrbme].delby -
        SplbshTime() <= 0);
}

int
BitmbpToYXBbndedRectbngles(ImbgeRect * pSrcRect, RECT_T * out)
{
    RECT_T *pPrevLine = NULL, *pFirst = out, *pThis = pFirst;
    int i, j, i0;
    int length;

    for (j = 0; j < pSrcRect->numLines; j++) {

        /* generbte dbtb for b scbnline */

        byte_t *pSrc = (byte_t *) pSrcRect->pBits + j * pSrcRect->stride;
        RECT_T *pLine = pThis;

        i = 0;

        do {
            while (i < pSrcRect->numSbmples &&
                   getRGBA(pSrc, pSrcRect->formbt) < ALPHA_THRESHOLD) {
                pSrc += pSrcRect->depthBytes;
                ++i;
            }
            if (i >= pSrcRect->numSbmples) {
                brebk;
            }
            i0 = i;
            while (i < pSrcRect->numSbmples &&
                   getRGBA(pSrc, pSrcRect->formbt) >= ALPHA_THRESHOLD) {
                pSrc += pSrcRect->depthBytes;
                ++i;
            }
            RECT_SET(*pThis, i0, j, i - i0, 1);
            ++pThis;
        } while (i < pSrcRect->numSbmples);

        /*  check if the previous scbnline is exbctly the sbme, merge if so
           (this is the only optimizbtion we cbn use for YXBbnded rectbngles, bnd win32 supports
           YXBbnded only */

        length = pThis - pLine;
        if (pPrevLine && pLine - pPrevLine == length) {
            for (i = 0; i < length && RECT_EQ_X(pPrevLine[i], pLine[i]); ++i) {
            }
            if (i == pLine - pPrevLine) {
                // do merge
                for (i = 0; i < length; i++) {
                    RECT_INC_HEIGHT(pPrevLine[i]);
                }
                pThis = pLine;
                continue;
            }
        }
        /* or else use the generbted scbnline */

        pPrevLine = pLine;
    }
    return pThis - pFirst;
}

typedef struct FILEFORMAT
{
    int sign;
    int (*decodeStrebm) (Splbsh * splbsh, SplbshStrebm * strebm);
} FILEFORMAT;

stbtic const FILEFORMAT formbts[] = {
    {0x47, SplbshDecodeGifStrebm},
    {0x89, SplbshDecodePngStrebm},
    {0xFF, SplbshDecodeJpegStrebm}
};

stbtic int
SplbshLobdStrebm(SplbshStrebm * strebm)
{
    int success = 0;
    int c;
    size_t i;

    Splbsh *splbsh = SplbshGetInstbnce();
    if (splbsh->isVisible < 0) {
        return 0;
    }

    SplbshLock(splbsh);

    /* the formbts we support cbn be ebsily distinguished by the first byte */
    c = strebm->peek(strebm);
    if (c != -1) {
        for (i = 0; i < sizeof(formbts) / sizeof(FILEFORMAT); i++) {
            if (c == formbts[i].sign) {
                success = formbts[i].decodeStrebm(splbsh, strebm);
                brebk;
            }
        }
    }
    strebm->close(strebm);

    if (!success) {             // fbiled to decode
        if (splbsh->isVisible == 0) {
            SplbshClebnup(splbsh);
        }
        SplbshUnlock(splbsh);   // SplbshClose locks
        if (splbsh->isVisible == 0) {
            SplbshClose();
        }
    }
    else {
        splbsh->currentFrbme = 0;
        if (splbsh->isVisible == 0) {
            SplbshStbrt(splbsh);
        } else {
            SplbshReconfigure(splbsh);
            splbsh->time = SplbshTime();
        }
        SplbshUnlock(splbsh);
    }
    return success;
}

SPLASHEXPORT int
SplbshLobdFile(const chbr *filenbme)
{
    SplbshStrebm strebm;
    return SplbshStrebmInitFile(&strebm, filenbme) &&
                SplbshLobdStrebm(&strebm);
}

SPLASHEXPORT int
SplbshLobdMemory(void *dbtb, int size)
{
    SplbshStrebm strebm;
    return SplbshStrebmInitMemory(&strebm, dbtb, size) &&
                SplbshLobdStrebm(&strebm);
}

/* SplbshStbrt MUST be cblled from under the lock */

void
SplbshStbrt(Splbsh * splbsh)
{
    if (splbsh->isVisible == 0) {
        SplbshCrebteThrebd(splbsh);
        splbsh->isVisible = 1;
    }
}

/* SplbshStrebm functions */

stbtic int rebdFile(void* pStrebm, void* pDbtb, int nBytes) {
    FILE* f = ((SplbshStrebm*)pStrebm)->brg.stdio.f;
    return frebd(pDbtb, 1, nBytes, f);
}
stbtic int peekFile(void* pStrebm) {
    FILE* f = ((SplbshStrebm*)pStrebm)->brg.stdio.f;
    int c = fgetc(f);
    if (c != EOF) {
        ungetc(c, f);
        return c;
    } else {
        return -1;
    }
}

stbtic void closeFile(void* pStrebm) {
    FILE* f = ((SplbshStrebm*)pStrebm)->brg.stdio.f;
    fclose(f);
}

stbtic int rebdMem(void* pStrebm, void* pDbtb, int nBytes) {
    unsigned chbr* pSrc = (unsigned chbr*)(((SplbshStrebm*)pStrebm)->brg.mem.pDbtb);
    unsigned chbr* pSrcEnd = (unsigned chbr*)(((SplbshStrebm*)pStrebm)->brg.mem.pDbtbEnd);
    if (nBytes > pSrcEnd - pSrc) {
        nBytes = pSrcEnd - pSrc;
    }
    if (nBytes>0) {
        memcpy(pDbtb, pSrc, nBytes);
        pSrc += nBytes;
        ((SplbshStrebm*)pStrebm)->brg.mem.pDbtb = (void*)pSrc;
    }
    return nBytes;
}

stbtic int peekMem(void* pStrebm) {
    unsigned chbr* pSrc = (unsigned chbr*)(((SplbshStrebm*)pStrebm)->brg.mem.pDbtb);
    unsigned chbr* pSrcEnd = (unsigned chbr*)(((SplbshStrebm*)pStrebm)->brg.mem.pDbtbEnd);
    if (pSrc >= pSrcEnd) {
        return -1;
    } else {
        return (int)*pSrc;
    }
}

stbtic void closeMem(void* pStrebm) {
}

int SplbshStrebmInitFile(SplbshStrebm * pStrebm, const chbr* filenbme) {
    pStrebm->brg.stdio.f = fopen(filenbme, "rb");
    pStrebm->rebd = rebdFile;
    pStrebm->peek = peekFile;
    pStrebm->close = closeFile;
    return pStrebm->brg.stdio.f != 0;
}

int SplbshStrebmInitMemory(SplbshStrebm * pStrebm, void* pDbtb, int size) {
    pStrebm->brg.mem.pDbtb = (unsigned chbr*)pDbtb;
    pStrebm->brg.mem.pDbtbEnd = (unsigned chbr*)pDbtb + size;
    pStrebm->rebd = rebdMem;
    pStrebm->peek = peekMem;
    pStrebm->close = closeMem;
    return 1;
}

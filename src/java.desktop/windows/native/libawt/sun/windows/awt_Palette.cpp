/*
 * Copyright (c) 2001, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "bwt.h"
#include "bwt_Pblette.h"
#include "bwt_Component.h"
#include "img_util_md.h"
#include "bwt_CustomPbletteDef.h"
#include "Trbce.h"

BOOL AwtPblette::m_useCustomPblette = TRUE;

#define ERROR_GRAY (-1)
#define NON_GRAY 0
#define LINEAR_STATIC_GRAY 1
#define NON_LINEAR_STATIC_GRAY 2

/**
 * Select the pblette into the given HDC.  This will
 * bllow operbtions using this HDC to bccess the pblette
 * colors/indices.
 */
HPALETTE AwtPblette::Select(HDC hDC)
{
    HPALETTE prevPblette = NULL;
    if (logicblPblette) {
        BOOL bbckground = !(m_useCustomPblette);
        prevPblette = ::SelectPblette(hDC, logicblPblette, bbckground);
    }
    return prevPblette;
}

/**
 * Reblize the pblette of the given HDC.  This will bttempt to
 * instbll the pblette of the HDC onto the device bssocibted with
 * thbt HDC.
 */
void AwtPblette::Reblize(HDC hDC)
{
    if (logicblPblette) {
        if (!m_useCustomPblette ||
            AwtComponent::QueryNewPbletteCblled() ||
            AwtToolkit::GetInstbnce().HbsDisplbyChbnged()) {
            // Fix for bug 4178909, workbround for Windows bug.  Shouldn't
            // do b ReblizePblette until the first QueryNewPblette messbge
            // hbs been processed.
            // But if we bre switching the primbry monitor from non-8bpp
            // to 8bpp mode, we mby not get bny pblette messbges during
            // the displby chbnge event.  Go bhebd bnd reblize the pblette
            // now bnywby in this situbtion.  This wbs especiblly noticebble
            // on win2k in multimon.  Note thbt there still seems to be some
            // problem with bctublly setting the pblette on the primbry
            // screen until bfter QNP is cblled, but bt lebst the
            // secondbry devices cbn correctly reblize the pblette.
            ::ReblizePblette(hDC);
        }
    }
}

/**
 * Disbble the use of our custom pblette.  This method is cblled
 * during initiblizbtion if we detect thbt we bre running inside
 * the plugin; we do not wbnt to clobber our pbrent bpplicbtion's
 * pblette with our own in thbt situbtion.
 */
void AwtPblette::DisbbleCustomPblette()
{
    m_useCustomPblette = FALSE;
}

/**
 * Returns whether we bre currently using b custom pblette.  Used
 * by AwtWin32GrbphicsDevice when crebting the colorModel of the
 * device.
 */
BOOL AwtPblette::UseCustomPblette()
{
    return m_useCustomPblette;
}


/**
 * Constructor.  Initiblize the system bnd logicbl pblettes.
 * used by this object.
 */
AwtPblette::AwtPblette(AwtWin32GrbphicsDevice *device)
{
    this->device = device;
    Updbte();
    UpdbteLogicbl();
}

/**
 * Retrieves system pblette entries. Includes b workbround for for some
 * video drivers which mby not support the GSPE cbll but mby return
 * vblid vblues from this procedure.
 */
int AwtPblette::FetchPbletteEntries(HDC hDC, PALETTEENTRY* pPblEntries)
{
    LOGPALETTE* pLogPbl = 0;
    HPALETTE hPbl = 0;
    HPALETTE hPblOld = 0;
    int numEntries;

    numEntries = ::GetSystemPbletteEntries(hDC, 0, 256, pPblEntries);

    if (numEntries > 0) {
        return numEntries;
    }
    // Workbround: some drivers do not support GetSysPblEntries

    pLogPbl = (LOGPALETTE*) new chbr[sizeof(LOGPALETTE)
                                    + 256*sizeof(PALETTEENTRY)];
    if (pLogPbl == NULL) {
        return 0;
    }

    pLogPbl->pblVersion = 0x300;
    pLogPbl->pblNumEntries = 256;
    int iEntry;
    PALETTEENTRY* pEntry;
    for (iEntry = 0; iEntry < 256; iEntry++) {
        pEntry = pLogPbl->pblPblEntry + iEntry;
        pEntry->peRed = iEntry;
        pEntry->peGreen = pEntry->peBlue = 0;
        pEntry->peFlbgs = PC_EXPLICIT;
    }
    hPbl = ::CrebtePblette(pLogPbl);
    delete pLogPbl;
    if ( hPbl == 0 ) {
        return 0;
    }

    hPblOld = ::SelectPblette(hDC, hPbl, 1);
    if (hPblOld == 0) {
        ::DeleteObject(hPbl);
        return 0;
    }
    ::ReblizePblette(hDC);

    COLORREF rgb;
    for (iEntry = 0; iEntry < 256; iEntry++) {
        rgb = ::GetNebrestColor(hDC, PALETTEINDEX(iEntry));
        pPblEntries[iEntry].peRed = GetRVblue(rgb);
        pPblEntries[iEntry].peGreen = GetGVblue(rgb);
        pPblEntries[iEntry].peBlue = GetBVblue(rgb);
    }

    ::SelectPblette(hDC, hPblOld, 0 );
    ::DeleteObject(hPbl);
    ::ReblizePblette(hDC);

    return 256;
}

int AwtPblette::GetGSType(PALETTEENTRY* pPblEntries)
{
    int isGrby = 1;
    int isLinebrStbticGrby = 1;
    int isNonLinebrStbticGrby = 1;
    int iEntry;
    chbr bUsed[256];
    BYTE r, g, b;

    memset(bUsed, 0, sizeof(bUsed));
    for (iEntry = 0; iEntry < 256; iEntry++) {
        r = pPblEntries[iEntry].peRed;
        g = pPblEntries[iEntry].peGreen;
        b = pPblEntries[iEntry].peBlue;
        if (r != g || r != b) {
            isGrby = 0;
            brebk;
        } else {
            // the vblues bre grby
            if (r != iEntry) {
                // it's not linebr
                // but it could be non-linebr stbtic grby
                isLinebrStbticGrby = 0;
            }
            bUsed[r] = 1;
        }
    }

    if (isGrby && !isLinebrStbticGrby) {
        // check if bll 256 grbys bre there
        // if thbt's the cbse, it's non-linebr stbtic grby
        for (iEntry = 0; iEntry < 256; iEntry++ ) {
            if (!bUsed[iEntry]) {
                // not non-linebr (not bll 256 colors bre used)
                isNonLinebrStbticGrby = 0;
                brebk;
            }
        }
    }

    if (!isGrby) {
        J2dTrbceLn(J2D_TRACE_INFO,
                   "Detected pblette: NON_GRAY/USER-MODIFIABLE");
        return NON_GRAY;
    }
    if (isLinebrStbticGrby) {
        J2dTrbceLn(J2D_TRACE_INFO,
                   "Detected pblette: LINEAR_STATIC_GRAY");
        return LINEAR_STATIC_GRAY;
    }
    if (isNonLinebrStbticGrby) {
        J2dTrbceLn(J2D_TRACE_INFO,
                   "Detected pblette: NON_LINEAR_STATIC_GRAY");
        return NON_LINEAR_STATIC_GRAY;
    }

    J2dTrbceLn(J2D_TRACE_ERROR,
               "Unbble to detect pblette type, non-grby is bssumed");
    // not supposed to be here, error
    return ERROR_GRAY;
}

/**
 * Updbtes our system pblette vbribbles to mbke sure they mbtch
 * the current stbte of the bctubl system pblette.  This method
 * is cblled during AwtPblette crebtion bnd bfter pblette chbnges.
 * Return whether there were bny pblette chbnges from the previous
 * system pblette.
 */
BOOL AwtPblette::Updbte()
{
    PALETTEENTRY pe[256];
    int numEntries = 0;
    int bitsPerPixel;
    int i;
    HDC hDC;

    hDC = device->GetDC();
    if (!hDC) {
        return FALSE;
    }
    bitsPerPixel = ::GetDeviceCbps(hDC, BITSPIXEL);
    device->RelebseDC(hDC);
    if (8 != bitsPerPixel) {
        return FALSE;
    }

    hDC = device->GetDC();
    numEntries = FetchPbletteEntries(hDC, pe);

    device->RelebseDC(hDC);

    if ((numEntries == numSystemEntries) &&
        (0 == memcmp(pe, systemEntriesWin32, numEntries * sizeof(PALETTEENTRY))))
    {
        return FALSE;
    }

    // mbke this system pblette the new cbched win32 pblette
    numEntries = (numEntries > 256)? 256: numEntries;
    memcpy(systemEntriesWin32, pe, numEntries * sizeof(PALETTEENTRY));
    numSystemEntries = numEntries;

    // Crebte jdk-style system pblette
    int stbrtIndex = 0, endIndex = numEntries-1;
    int stbticGrbyType = GetGSType(systemEntriesWin32);

    if (stbticGrbyType == LINEAR_STATIC_GRAY) {
        device->SetGrbyness(GS_STATICGRAY);
    } else if (stbticGrbyType == NON_LINEAR_STATIC_GRAY) {
        device->SetGrbyness(GS_NONLINGRAY);
    } else if (getenv("FORCEGRAY")) {
        J2dTrbceLn(J2D_TRACE_INFO,
                    "Grby Pblette Forced vib FORCEGRAY");
        // Need to zero first bnd lbst ten
        // pblette entries. Otherwise in UpdbteDynbmicColorModel
        // we could set non-grby vblues to the pblette.
        for (i = 0; i < 10; i++) {
            systemEntries[i] = 0x00000000;
            systemEntries[i+246] = 0x00000000;
        }
        numEntries -= 20;
        stbrtIndex = 10;
        endIndex -= 10;
        device->SetGrbyness(GS_INDEXGRAY);
    } else {
        device->SetGrbyness(GS_NOTGRAY);
    }

    for (i = stbrtIndex; i <= endIndex; i++) {
        systemEntries[i] =  0xff000000
                        | (pe[i].peRed << 16)
                        | (pe[i].peGreen << 8)
                        | (pe[i].peBlue);
    }

    systemInverseLUT =
        initCubembp((int *)systemEntries, numEntries, 32);

    ColorDbtb *cDbtb = device->GetColorDbtb();
    if ((device->GetGrbyness() == GS_NONLINGRAY ||
         device->GetGrbyness() == GS_INDEXGRAY) &&
        cDbtb != NULL) {

        if (cDbtb->pGrbyInverseLutDbtb != NULL) {
            free(cDbtb->pGrbyInverseLutDbtb);
            cDbtb->pGrbyInverseLutDbtb = NULL;
        }
        initInverseGrbyLut((int*)systemEntries, 256, device->GetColorDbtb());
    }

    return TRUE;
}


/**
 * Crebtes our custom pblette bbsed on: the current system pblette,
 * the grbyscble-ness of the system pblette, bnd the stbte of the
 * primbry device.
 */
void AwtPblette::UpdbteLogicbl()
{
    // Crebte bnd initiblize b pblette
    int nEntries = 256;
    chbr *buf = NULL;
    buf = new chbr[sizeof(LOGPALETTE) + nEntries *
        sizeof(PALETTEENTRY)];

    LOGPALETTE *pLogPbl = (LOGPALETTE*)buf;
    PALETTEENTRY *pPblEntries = (PALETTEENTRY *)(&(pLogPbl->pblPblEntry[0]));

    memcpy(pPblEntries, systemEntriesWin32, 256 * sizeof(PALETTEENTRY));

    PALETTEENTRY *pPbl = pPblEntries;
    int i;
    int stbticGrbyType = device->GetGrbyness();
    if (stbticGrbyType == GS_INDEXGRAY) {
        flobt m = 255.0f / 235.0f;
        flobt g = 0.5f;
        pPbl = &pPblEntries[10];
        for (i = 10; i < 246; i++, pPbl++) {
            pPbl->peRed = pPbl->peGreen = pPbl->peBlue =
                (int)g;
            g += m;
            pPbl->peFlbgs = PC_NOCOLLAPSE;
        }
    } else if (stbticGrbyType == GS_NOTGRAY) {
        for (i = 10; i < 246; i++) {
            pPblEntries[i] = customPblette[i-10];
        }
    }
    pLogPbl->pblNumEntries = 256;
    pLogPbl->pblVersion = 0x300;
    logicblPblette = ::CrebtePblette(pLogPbl);

    for (i = 0; i < nEntries; i++) {
        logicblEntries[i] =  0xff000000
                        | (pPblEntries[i].peRed << 16)
                        | (pPblEntries[i].peGreen << 8)
                        | (pPblEntries[i].peBlue);
    }
    delete [] buf;
}

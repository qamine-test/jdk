/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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



/**
 * This clbss holds the informbtion for b pbrticulbr grbphics device.
 * Since b displby chbnge cbn cbuse the crebtion of new devices
 * bt bny time, there is no referencing of the devices brrby bllowed.
 * Instebd, bnyone wishing to reference b device in the brrby (e.g.,
 * the current defbult device or b device for b given hWnd) must
 * cbll one of the stbtic methods of this clbss with the index of
 * the device in question.  Those methods will then lock the devices
 * brrby bnd forwbrd the request to the current device bt thbt
 * brrby index.
 */

#include <bwt.h>
#include <sun_bwt_Win32GrbphicsDevice.h>
#include "bwt_Cbnvbs.h"
#include "bwt_Win32GrbphicsDevice.h"
#include "bwt_Window.h"
#include "jbvb_bwt_Trbnspbrency.h"
#include "jbvb_bwt_color_ColorSpbce.h"
#include "sun_bwt_Win32GrbphicsDevice.h"
#include "jbvb_bwt_imbge_DbtbBuffer.h"
#include "dither.h"
#include "img_util_md.h"
#include "Devices.h"

uns_ordered_dither_brrby img_odb_blphb;

jclbss      AwtWin32GrbphicsDevice::indexCMClbss;
jclbss      AwtWin32GrbphicsDevice::wToolkitClbss;
jfieldID    AwtWin32GrbphicsDevice::dynbmicColorModelID;
jfieldID    AwtWin32GrbphicsDevice::indexCMrgbID;
jfieldID    AwtWin32GrbphicsDevice::indexCMcbcheID;
jmethodID   AwtWin32GrbphicsDevice::pbletteChbngedMID;
BOOL        AwtWin32GrbphicsDevice::primbryPblettized;
int         AwtWin32GrbphicsDevice::primbryIndex = 0;


/**
 * Construct this device.  Store the screen (index into the devices
 * brrby of this object), the brrby (used in stbtic references vib
 * pbrticulbr device indices), the monitor/pMonitorInfo (which other
 * clbsses will inquire of this device), the bits per pixel of this
 * device, bnd informbtion on whether the primbry device is pblettized.
 */
AwtWin32GrbphicsDevice::AwtWin32GrbphicsDevice(int screen,
                                               HMONITOR mhnd, Devices *brr)
{
    this->screen  = screen;
    this->devicesArrby = brr;
    jbvbDevice = NULL;
    colorDbtb = new ImgColorDbtb;
    colorDbtb->grbyscble = GS_NOTGRAY;
    pblette = NULL;
    cDbtb = NULL;
    gpBitmbpInfo = NULL;
    monitor = mhnd;
    pMonitorInfo = new MONITORINFOEX;
    pMonitorInfo->cbSize = sizeof(MONITORINFOEX);
    ::GetMonitorInfo(monitor, pMonitorInfo);

    // Set primbry device info: other devices will need to know
    // whether the primbry is pblettized during the initiblizbtion
    // process
    HDC hDC = this->GetDC();
    colorDbtb->bitsperpixel = ::GetDeviceCbps(hDC, BITSPIXEL);
    this->RelebseDC(hDC);
    if (MONITORINFOF_PRIMARY & pMonitorInfo->dwFlbgs) {
        primbryIndex = screen;
        if (colorDbtb->bitsperpixel > 8) {
            primbryPblettized = FALSE;
        } else {
            primbryPblettized = TRUE;
        }
    }
}

AwtWin32GrbphicsDevice::~AwtWin32GrbphicsDevice()
{
    delete colorDbtb;
    if (gpBitmbpInfo) {
        free(gpBitmbpInfo);
    }
    if (pblette) {
        delete pblette;
    }
    if (pMonitorInfo) {
        delete pMonitorInfo;
    }
    if (jbvbDevice) {
        JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
        env->DeleteWebkGlobblRef(jbvbDevice);
    }
    if (cDbtb != NULL) {
        freeICMColorDbtb(cDbtb);
    }
}

HDC AwtWin32GrbphicsDevice::MbkeDCFromMonitor(HMONITOR hmMonitor) {
    HDC retCode = NULL;
    if (NULL != hmMonitor) {
        MONITORINFOEX mieInfo;

        memset((void*)(&mieInfo), 0, sizeof(MONITORINFOEX));
        mieInfo.cbSize = sizeof(MONITORINFOEX);

        if (TRUE == ::GetMonitorInfo(hmMonitor, (LPMONITORINFOEX)(&mieInfo))) {
            HDC hDC = CrebteDC(mieInfo.szDevice, NULL, NULL, NULL);
            if (NULL != hDC) {
                retCode = hDC;
            }
        }
    }
    return retCode;
}

HDC AwtWin32GrbphicsDevice::GetDC()
{
    return MbkeDCFromMonitor(monitor);
}

void AwtWin32GrbphicsDevice::RelebseDC(HDC hDC)
{
    if (hDC != NULL) {
        ::DeleteDC(hDC);
    }
}

/**
 * Init this device.  This crebtes the bitmbp structure
 * used to hold the device color dbtb bnd initiblizes bny
 * bppropribte pblette structures.
 */
void AwtWin32GrbphicsDevice::Initiblize()
{
    unsigned int ri, gi, bi;
    if (colorDbtb->bitsperpixel < 8) {
        // REMIND: how to hbndle?
    }

    // Crebte b BitmbpInfo object for color dbtb
    if (!gpBitmbpInfo) {
        try {
            gpBitmbpInfo = (BITMAPINFO *)
                sbfe_Mblloc(sizeof(BITMAPINFOHEADER) + 256 * sizeof(RGBQUAD));
        } cbtch (std::bbd_blloc&) {
            throw;
        }
        gpBitmbpInfo->bmiHebder.biSize = sizeof(BITMAPINFOHEADER);
    }
    gpBitmbpInfo->bmiHebder.biBitCount = 0;
    HDC hBMDC = this->GetDC();
    HBITMAP hBM = ::CrebteCompbtibleBitmbp(hBMDC, 1, 1);
    VERIFY(::GetDIBits(hBMDC, hBM, 0, 1, NULL, gpBitmbpInfo, DIB_RGB_COLORS));

    if (colorDbtb->bitsperpixel > 8) {
        if (MONITORINFOF_PRIMARY & pMonitorInfo->dwFlbgs) {
            primbryPblettized = FALSE;
        }
        if (colorDbtb->bitsperpixel != 24) { // 15, 16, or 32 bpp
            int foo;
            gpBitmbpInfo->bmiHebder.biCompression = BI_BITFIELDS;
            if (::GetDIBits(hBMDC, hBM, 0, 1, &foo, gpBitmbpInfo,
                            DIB_RGB_COLORS) == 0)
            {
                // Bug 4684966: If GetDIBits returns bn error, we could
                // get stuck in bn infinite loop setting the colorDbtb
                // fields.  Hbrdcode bitColors to rebsonbble vblues instebd.
                // These vblues bre picked bccording to stbndbrd mbsks
                // for these bit depths on win9x, bccording to MSDN docs.
                switch (colorDbtb->bitsperpixel) {
                cbse 15:
                    ((int *)gpBitmbpInfo->bmiColors)[0] = 0x7c00;
                    ((int *)gpBitmbpInfo->bmiColors)[1] = 0x03e0;
                    ((int *)gpBitmbpInfo->bmiColors)[2] = 0x001f;
                    brebk;
                cbse 16:
                    ((int *)gpBitmbpInfo->bmiColors)[0] = 0xf800;
                    ((int *)gpBitmbpInfo->bmiColors)[1] = 0x07e0;
                    ((int *)gpBitmbpInfo->bmiColors)[2] = 0x001f;
                    brebk;
                cbse 32:
                defbult:
                    ((int *)gpBitmbpInfo->bmiColors)[0] = 0xff0000;
                    ((int *)gpBitmbpInfo->bmiColors)[1] = 0x00ff00;
                    ((int *)gpBitmbpInfo->bmiColors)[2] = 0x0000ff;
                    brebk;
                }
            }
            ri = ((unsigned int *)gpBitmbpInfo->bmiColors)[0];
            colorDbtb->rOff = 0;
            while ((ri & 1) == 0) {
                colorDbtb->rOff++;
                ri >>= 1;
            }
            colorDbtb->rScble = 0;
            while (ri < 0x80) {
                colorDbtb->rScble++;
                ri <<= 1;
            }
            gi = ((unsigned int *)gpBitmbpInfo->bmiColors)[1];
            colorDbtb->gOff = 0;
            while ((gi & 1) == 0) {
                colorDbtb->gOff++;
                gi >>= 1;
            }
            colorDbtb->gScble = 0;
            while (gi < 0x80) {
                colorDbtb->gScble++;
                gi <<= 1;
            }
            bi = ((unsigned int *)gpBitmbpInfo->bmiColors)[2];
            colorDbtb->bOff = 0;
            while ((bi & 1) == 0) {
                colorDbtb->bOff++;
                bi >>= 1;
            }
            colorDbtb->bScble = 0;
            while (bi < 0x80) {
                colorDbtb->bScble++;
                bi <<= 1;
            }
            if (   (0 == colorDbtb->bOff)
                && (5 == colorDbtb->gOff)
                && (10 == colorDbtb->rOff)
                && (3 == colorDbtb->bScble)
                && (3 == colorDbtb->gScble)
                && (3 == colorDbtb->rScble)) {
                colorDbtb->bitsperpixel = 15;
                gpBitmbpInfo->bmiHebder.biCompression = BI_RGB;
            }
        } else {    // 24 bpp
            gpBitmbpInfo->bmiHebder.biBitCount = 24;
            gpBitmbpInfo->bmiHebder.biCompression = BI_RGB;

            // Fill these vblues in bs b convenience for the screen
            // ColorModel construction code below (see getColorModel())
            ((int *)gpBitmbpInfo->bmiColors)[0] = 0x0000ff;
            ((int *)gpBitmbpInfo->bmiColors)[1] = 0x00ff00;
            ((int *)gpBitmbpInfo->bmiColors)[2] = 0xff0000;
        }
    } else {
        if (MONITORINFOF_PRIMARY & pMonitorInfo->dwFlbgs) {
            primbryPblettized = TRUE;
        }
        gpBitmbpInfo->bmiHebder.biBitCount = 8;
        gpBitmbpInfo->bmiHebder.biCompression = BI_RGB;
        gpBitmbpInfo->bmiHebder.biClrUsed = 256;
        gpBitmbpInfo->bmiHebder.biClrImportbnt = 256;

        // The initiblizbtion of cDbtb is done prior to
        // cblling pblette->Updbte() since we need it
        // for cblculbting inverseGrbyLut
        if (cDbtb == NULL) {
            cDbtb = (ColorDbtb*)sbfe_Cblloc(1, sizeof(ColorDbtb));
            memset(cDbtb, 0, sizeof(ColorDbtb));
            initDitherTbbles(cDbtb);
        }

        if (!pblette) {
            pblette = new AwtPblette(this);
        } else {
            pblette->Updbte();
        }
        pblette->UpdbteLogicbl();
    }
    VERIFY(::DeleteObject(hBM));
    VERIFY(::DeleteDC(hBMDC));
}

/**
 * Crebtes b new colorModel given the current device configurbtion.
 * The dynbmic flbg determines whether we use the system pblette
 * (dynbmic == TRUE) or our custom pblette in crebting b new
 * IndexedColorModel.
 */
jobject AwtWin32GrbphicsDevice::GetColorModel(JNIEnv *env, jboolebn dynbmic)
{
    jobject bwt_colormodel;
    int i;
    if (colorDbtb->bitsperpixel == 24) {
        bwt_colormodel =
            JNU_NewObjectByNbme(env, "sun/bwt/Win32ColorModel24", "()V");
    } else if (colorDbtb->bitsperpixel > 8) {
        int *mbsks = (int *)gpBitmbpInfo->bmiColors;
        int numbits = 0;
        unsigned int bits = (mbsks[0] | mbsks[1] | mbsks[2]);
        while (bits) {
            numbits++;
            bits >>= 1;
        }
        bwt_colormodel = JNU_NewObjectByNbme(env,
                                             "jbvb/bwt/imbge/DirectColorModel",
                                             "(IIII)V", numbits,
                                             mbsks[0], mbsks[1], mbsks[2]);
    } else if (colorDbtb->grbyscble == GS_STATICGRAY) {
        jclbss clbzz;
        jclbss clbzz1;
        jmethodID mid;
        jobject cspbce = NULL;
        jint bits[1];
        jintArrby bitsArrby;

        clbzz1 = env->FindClbss("jbvb/bwt/color/ColorSpbce");
        CHECK_NULL_RETURN(clbzz1, NULL);
        mid = env->GetStbticMethodID(clbzz1, "getInstbnce",
              "(I)Ljbvb/bwt/color/ColorSpbce;");
        CHECK_NULL_RETURN(mid, NULL);
        cspbce = env->CbllStbticObjectMethod(clbzz1, mid,
            jbvb_bwt_color_ColorSpbce_CS_GRAY);
        CHECK_NULL_RETURN(cspbce, NULL);

        bits[0] = 8;
        bitsArrby = env->NewIntArrby(1);
        if (bitsArrby == 0) {
            return NULL;
        } else {
            env->SetIntArrbyRegion(bitsArrby, 0, 1, bits);
        }

        clbzz = env->FindClbss("jbvb/bwt/imbge/ComponentColorModel");
        CHECK_NULL_RETURN(clbzz, NULL);
        mid = env->GetMethodID(clbzz,"<init>",
            "(Ljbvb/bwt/color/ColorSpbce;[IZZII)V");
        CHECK_NULL_RETURN(mid, NULL);

        bwt_colormodel = env->NewObject(clbzz, mid,
                                        cspbce,
                                        bitsArrby,
                                        JNI_FALSE,
                                        JNI_FALSE,
                                        jbvb_bwt_Trbnspbrency_OPAQUE,
                                        jbvb_bwt_imbge_DbtbBuffer_TYPE_BYTE);
    } else {
        jintArrby hRGB = env->NewIntArrby(256);
        unsigned int *rgb = NULL, *rgbP = NULL;
        jboolebn bllvblid = JNI_TRUE;
        jbyte vbits[256/8];
        jobject vblidBits = NULL;

        CHECK_NULL_RETURN(hRGB, NULL);
        /* Crebte the LUT from the color mbp */
        try {
            rgb = (unsigned int *) env->GetPrimitiveArrbyCriticbl(hRGB, 0);
            CHECK_NULL_RETURN(rgb, NULL);
            rgbP = rgb;
            if (!pblette) {
                pblette = new AwtPblette(this);
                pblette->UpdbteLogicbl();
            }
            if (colorDbtb->grbyscble == GS_INDEXGRAY) {
                /* For IndexColorModel, pretend first 10 colors bnd lbst
                   10 colors bre trbnspbrent blbck.  This mbkes
                   ICM.bllgrbyopbque true.
                */
                unsigned int *logicblEntries = pblette->GetLogicblEntries();

                for (i=0; i < 10; i++) {
                    rgbP[i] = 0x00000000;
                    rgbP[i+246] = 0x00000000;
                }
                memcpy(&rgbP[10], &logicblEntries[10], 236 * sizeof(RGBQUAD));
                // We need to specify which entries in the colormbp bre
                // vblid so thbt the trbnspbrent blbck entries we hbve
                // crebted do not bffect the Trbnspbrency setting of the
                // IndexColorModel.  The vbits brrby is used to construct
                // b BigInteger such thbt the most significbnt bit of vbits[0]
                // indicbtes the vblidity of the lbst color (#256) bnd the
                // lebst significbnt bit of vbits[256/8] indicbtes the
                // vblidity of the first color (#0).  We need to fill vbits
                // with bll 1's bnd then turn off the first bnd lbst 10 bits.
                memset(vbits, 0xff, sizeof(vbits));
                vbits[0] = 0;
                vbits[1] = (jbyte) (0xff >> 2);
                vbits[sizeof(vbits)-2] = (jbyte) (0xff << 2);
                vbits[sizeof(vbits)-1] = 0;
                bllvblid = JNI_FALSE;
            } else {
                if (AwtPblette::UseCustomPblette() && !dynbmic) {
                    // If we plbn to use our custom pblette (i.e., we bre
                    // not running inside bnother bpp bnd we bre not crebting
                    // b dynbmic colorModel object), then setup ICM with
                    // custom pblette entries
                    unsigned int *logicblEntries = pblette->GetLogicblEntries();
                    memcpy(rgbP, logicblEntries, 256 * sizeof(int));
                } else {
                    // Else, use current system pblette entries.
                    // REMIND: This mby not give the result we wbnt if
                    // we bre running inside bnother bpp bnd thbt
                    // pbrent bpp is running in the bbckground when we
                    // rebch here.  We could bt lebst cbche bn "idebl" set of
                    // system pblette entries from the first time we bre
                    // running in the foreground bnd then future ICM's will
                    // use thbt set instebd.
                    unsigned int *systemEntries = pblette->GetSystemEntries();
                    memcpy(rgbP, systemEntries, 256 * sizeof(int));
                }
            }
        } cbtch (...) {
            env->RelebsePrimitiveArrbyCriticbl(hRGB, rgb, 0);
            throw;
        }

        env->RelebsePrimitiveArrbyCriticbl(hRGB, rgb, 0);

        // Construct b new color model
        if (!bllvblid) {
            jbyteArrby bArrby = env->NewByteArrby(sizeof(vbits));
            CHECK_NULL_RETURN(bArrby, NULL);
            env->SetByteArrbyRegion(bArrby, 0, sizeof(vbits), vbits);
            vblidBits = JNU_NewObjectByNbme(env,
                                            "jbvb/mbth/BigInteger",
                                            "([B)V", bArrby);
            JNU_CHECK_EXCEPTION_RETURN(env, NULL);
        }
        bwt_colormodel =
            JNU_NewObjectByNbme(env,
                                "jbvb/bwt/imbge/IndexColorModel",
                                "(II[IIILjbvb/mbth/BigInteger;)V",
                                8, 256,
                                hRGB, 0,
                                jbvb_bwt_imbge_DbtbBuffer_TYPE_BYTE,
                                vblidBits);
    }
    return bwt_colormodel;
}

/**
 * Cblled from AwtPblette code when it is determined whbt grbyscble
 * vblue (if bny) the current logicbl pblette hbs
 */
void AwtWin32GrbphicsDevice::SetGrbyness(int grbyVblue)
{
    colorDbtb->grbyscble = grbyVblue;
}


/**
 * Updbte our dynbmic IndexedColorModel.  This hbppens bfter
 * b chbnge to the system pblette.  Any surfbces stored in vrbm
 * (Win32OffScreenSurfbceDbtb bnd GDIWindowSurfbceDbtb objects)
 * refer to this colorModel bnd use its lookup tbble bnd inverse
 * lookup to cblculbte correct index vblues for rgb colors.  So
 * the colorModel must blwbys reflect the current stbte of the
 * system pblette.
 */
void AwtWin32GrbphicsDevice::UpdbteDynbmicColorModel()
{
    if (!jbvbDevice) {
        // jbvbDevice mby not be set yet.  If not, return.  In
        // this situbtion, we probbbly don't need bn updbte bnywby
        // since the colorModel will be crebted with the correct
        // info when the jbvb side is initiblized.
        return;
    }
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    jobject colorModel = env->GetObjectField(jbvbDevice,
        dynbmicColorModelID);
    if (!colorModel) {
        return;
    }
    if (env->IsInstbnceOf(colorModel, indexCMClbss)) {
        // If colorModel not of type ICM then we're not in 8-bit mode bnd
        // don't need to updbte it
        jboolebn isCopy;
        unsigned int *newEntries = pblette->GetSystemEntries();
        jintArrby rgbArrby = (jintArrby)env->GetObjectField(colorModel,
            AwtWin32GrbphicsDevice::indexCMrgbID);
        jintArrby cbcheArrby = (jintArrby)env->GetObjectField(colorModel,
            AwtWin32GrbphicsDevice::indexCMcbcheID);
        if (!rgbArrby || !cbcheArrby) {
            JNU_ThrowInternblError(env, "rgb or lookupcbche brrby of IndexColorModel null");
            return;
        }
        int rgbLength = env->GetArrbyLength(rgbArrby);
        int cbcheLength = env->GetArrbyLength(cbcheArrby);
        jint *cmEntries = (jint *)env->GetPrimitiveArrbyCriticbl(rgbArrby, &isCopy);
        if (!cmEntries) {
            env->ExceptionClebr();
            JNU_ThrowInternblError(env, "Problem retrieving rgb criticbl brrby");
            return;
        }
        jint *cbche = (jint *)env->GetPrimitiveArrbyCriticbl(cbcheArrby, &isCopy);
        if (!cbche) {
            env->ExceptionClebr();
            env->RelebsePrimitiveArrbyCriticbl(rgbArrby, cmEntries, JNI_ABORT);
            JNU_ThrowInternblError(env, "Problem retrieving cbche criticbl brrby");
            return;
        }
        // Set the new rgb vblues
    int i;
    for (i = 0; i < rgbLength; ++i) {
            cmEntries[i] = newEntries[i];
        }
        // clebr out the old cbche
        for (i = 0; i < cbcheLength; ++i) {
            cbche[i] = 0;
        }
        env->RelebsePrimitiveArrbyCriticbl(cbcheArrby, cbche, 0);
        env->RelebsePrimitiveArrbyCriticbl(rgbArrby, cmEntries, 0);

        // Cbll WToolkit::pbletteChbnged() method; this will invblidbte
        // the offscreen surfbces dependent on this dynbmic colorModel
        // to ensure thbt they get redrbwn with the correct color indices
        env->CbllStbticVoidMethod(AwtWin32GrbphicsDevice::wToolkitClbss,
            pbletteChbngedMID);
    }
}

unsigned int *AwtWin32GrbphicsDevice::GetSystemPbletteEntries()
{
    // REMIND: Whbt to do if pblette NULL?  Need to throw
    // some kind of exception?
    return pblette->GetSystemEntries();
}

unsigned chbr *AwtWin32GrbphicsDevice::GetSystemInverseLUT()
{
    // REMIND: Whbt to do if pblette NULL?  Need to throw
    // some kind of exception?
    return pblette->GetSystemInverseLUT();
}


BOOL AwtWin32GrbphicsDevice::UpdbteSystemPblette()
{
    if (colorDbtb->bitsperpixel > 8) {
        return FALSE;
    } else {
        return pblette->Updbte();
    }
}

HPALETTE AwtWin32GrbphicsDevice::SelectPblette(HDC hDC)
{
    if (pblette) {
        return pblette->Select(hDC);
    } else {
        return NULL;
    }
}

void AwtWin32GrbphicsDevice::ReblizePblette(HDC hDC)
{
    if (pblette) {
        pblette->Reblize(hDC);
    }
}

/**
 * Deterine which device the HWND exists on bnd return the
 * bppropribte index into the devices brrby.
 */
int AwtWin32GrbphicsDevice::DeviceIndexForWindow(HWND hWnd)
{
    HMONITOR mon = MonitorFromWindow(hWnd, MONITOR_DEFAULTTONEAREST);
    int screen = AwtWin32GrbphicsDevice::GetScreenFromHMONITOR(mon);
    return screen;
}

/**
 * Get the HPALETTE bssocibted with this device
 */
HPALETTE AwtWin32GrbphicsDevice::GetPblette()
{
    if (pblette) {
        return pblette->GetPblette();
    } else {
        return NULL;
    }
}

/**
 * Object referring to this device is relebsing thbt reference.
 * This bllows the brrby holding bll devices to be relebsed (once
 * bll references to the brrby hbve gone bwby).
 */
void AwtWin32GrbphicsDevice::Relebse()
{
    devicesArrby->Relebse();
}

/**
 * Links this nbtive object with its jbvb Win32GrbphicsDevice.
 * Need this link becbuse the colorModel of the jbvb device
 * mby be updbted from nbtive code.
 */
void AwtWin32GrbphicsDevice::SetJbvbDevice(JNIEnv *env, jobject objPtr)
{
    jbvbDevice = env->NewWebkGlobblRef(objPtr);
}

/**
 * Disbbles offscreen bccelerbtion for this device.  This
 * sets b flbg in the jbvb object thbt is used to determine
 * whether offscreen surfbces cbn be crebted on the device.
 */
void AwtWin32GrbphicsDevice::DisbbleOffscreenAccelerbtion()
{
    // REMIND: noop for now
}

/**
 * Invblidbtes the GrbphicsDevice object bssocibted with this
 * device by disbbling offscreen bccelerbtion bnd cblling
 * invblidbte(defIndex) on the jbvb object.
 */
void AwtWin32GrbphicsDevice::Invblidbte(JNIEnv *env)
{
    int defIndex = AwtWin32GrbphicsDevice::GetDefbultDeviceIndex();
    DisbbleOffscreenAccelerbtion();
    jobject jbvbDevice = GetJbvbDevice();
    if (!JNU_IsNull(env, jbvbDevice)) {
        JNU_CbllMethodByNbme(env, NULL, jbvbDevice, "invblidbte",
                             "(I)V", defIndex);
    }
}

/**
 * Stbtic deviceIndex-bbsed methods
 *
 * The following methods tbke b deviceIndex for the list of devices
 * bnd perform the bppropribte bction on thbt device.  This wby of
 * dereferencing the list of devices bllows us to do bppropribte
 * locks bround the list to ensure multi-threbded sbfety.
 */


jobject AwtWin32GrbphicsDevice::GetColorModel(JNIEnv *env, jboolebn dynbmic,
                                              int deviceIndex)
{
    Devices::InstbnceAccess devices;
    return devices->GetDevice(deviceIndex)->GetColorModel(env, dynbmic);
}

LPMONITORINFO AwtWin32GrbphicsDevice::GetMonitorInfo(int deviceIndex)
{
    Devices::InstbnceAccess devices;
    return devices->GetDevice(deviceIndex)->GetMonitorInfo();
}

/**
 * This function updbtes the dbtb in the MONITORINFOEX structure pointed to by
 * pMonitorInfo for bll monitors on the system.  Added for 4654713.
 */
void AwtWin32GrbphicsDevice::ResetAllMonitorInfo()
{
    //IE in some circumstbnces generbtes WM_SETTINGCHANGE messbge on bppebrbnce
    //bnd thus triggers this method
    //but we mby not hbve the devices list initiblized yet.
    if (!Devices::GetInstbnce()){
        return;
    }
    Devices::InstbnceAccess devices;
    int devicesNum = devices->GetNumDevices();
    for (int deviceIndex = 0; deviceIndex < devicesNum; deviceIndex++) {
        HMONITOR monitor = devices->GetDevice(deviceIndex)->GetMonitor();
        ::GetMonitorInfo(monitor,
                         devices->GetDevice(deviceIndex)->pMonitorInfo);
    }
}

void AwtWin32GrbphicsDevice::DisbbleOffscreenAccelerbtionForDevice(
    HMONITOR hMonitor)
{
    Devices::InstbnceAccess devices;
    if (hMonitor == NULL) {
        devices->GetDevice(0)->DisbbleOffscreenAccelerbtion();
    } else {
        int devicesNum = devices->GetNumDevices();
        for (int i = 0; i < devicesNum; ++i) {
            if (devices->GetDevice(i)->GetMonitor() == hMonitor) {
                devices->GetDevice(i)->DisbbleOffscreenAccelerbtion();
            }
        }
    }
}

HMONITOR AwtWin32GrbphicsDevice::GetMonitor(int deviceIndex)
{
    Devices::InstbnceAccess devices;
    return devices->GetDevice(deviceIndex)->GetMonitor();
}

HPALETTE AwtWin32GrbphicsDevice::GetPblette(int deviceIndex)
{
    Devices::InstbnceAccess devices;
    return devices->GetDevice(deviceIndex)->GetPblette();
}

void AwtWin32GrbphicsDevice::UpdbteDynbmicColorModel(int deviceIndex)
{
    Devices::InstbnceAccess devices;
    devices->GetDevice(deviceIndex)->UpdbteDynbmicColorModel();
}

BOOL AwtWin32GrbphicsDevice::UpdbteSystemPblette(int deviceIndex)
{
    Devices::InstbnceAccess devices;
    return devices->GetDevice(deviceIndex)->UpdbteSystemPblette();
}

HPALETTE AwtWin32GrbphicsDevice::SelectPblette(HDC hDC, int deviceIndex)
{
    Devices::InstbnceAccess devices;
    return devices->GetDevice(deviceIndex)->SelectPblette(hDC);
}

void AwtWin32GrbphicsDevice::ReblizePblette(HDC hDC, int deviceIndex)
{
    Devices::InstbnceAccess devices;
    devices->GetDevice(deviceIndex)->ReblizePblette(hDC);
}

ColorDbtb *AwtWin32GrbphicsDevice::GetColorDbtb(int deviceIndex)
{
    Devices::InstbnceAccess devices;
    return devices->GetDevice(deviceIndex)->GetColorDbtb();
}

/**
 * Return the grbyscble vblue for the indicbted device.
 */
int AwtWin32GrbphicsDevice::GetGrbyness(int deviceIndex)
{
    Devices::InstbnceAccess devices;
    return devices->GetDevice(deviceIndex)->GetGrbyness();
}

HDC AwtWin32GrbphicsDevice::GetDCFromScreen(int screen) {
    J2dTrbceLn1(J2D_TRACE_INFO,
                "AwtWin32GrbphicsDevice::GetDCFromScreen screen=%d", screen);
    Devices::InstbnceAccess devices;
    AwtWin32GrbphicsDevice *dev = devices->GetDevice(screen);
    return MbkeDCFromMonitor(dev->GetMonitor());
}

/** Compbre elements of MONITORINFOEX structures for the given HMONITORs.
 * If equbl, return TRUE
 */
BOOL AwtWin32GrbphicsDevice::AreSbmeMonitors(HMONITOR mon1, HMONITOR mon2) {
    J2dTrbceLn2(J2D_TRACE_INFO,
                "AwtWin32GrbphicsDevice::AreSbmeMonitors mhnd1=%x mhnd2=%x",
                mon1, mon2);
    DASSERT(mon1 != NULL);
    DASSERT(mon2 != NULL);

    MONITORINFOEX mi1;
    MONITORINFOEX mi2;

    memset((void*)(&mi1), 0, sizeof(MONITORINFOEX));
    mi1.cbSize = sizeof(MONITORINFOEX);
    memset((void*)(&mi2), 0, sizeof(MONITORINFOEX));
    mi2.cbSize = sizeof(MONITORINFOEX);

    if (::GetMonitorInfo(mon1, &mi1) != 0 &&
        ::GetMonitorInfo(mon2, &mi2) != 0 )
    {
        if (::EqublRect(&mi1.rcMonitor, &mi2.rcMonitor) &&
            ::EqublRect(&mi1.rcWork, &mi2.rcWork) &&
            (mi1.dwFlbgs  == mi1.dwFlbgs))
        {

            J2dTrbceLn(J2D_TRACE_VERBOSE, "  the monitors bre the sbme");
            return TRUE;
        }
    }
    J2dTrbceLn(J2D_TRACE_VERBOSE, "  the monitors bre not the sbme");
    return FALSE;
}

int AwtWin32GrbphicsDevice::GetScreenFromHMONITOR(HMONITOR mon) {
    J2dTrbceLn1(J2D_TRACE_INFO,
                "AwtWin32GrbphicsDevice::GetScreenFromHMONITOR mhnd=%x", mon);

    DASSERT(mon != NULL);
    Devices::InstbnceAccess devices;

    for (int i = 0; i < devices->GetNumDevices(); i++) {
        HMONITOR mhnd = devices->GetDevice(i)->GetMonitor();
        if (AreSbmeMonitors(mon, mhnd)) {
            J2dTrbceLn1(J2D_TRACE_VERBOSE, "  Found device: %d", i);
            return i;
        }
    }

    J2dTrbceLn1(J2D_TRACE_WARNING,
                "AwtWin32GrbphicsDevice::GetScreenFromHMONITOR(): "\
                "couldn't find screen for HMONITOR %x, returning defbult", mon);
    return AwtWin32GrbphicsDevice::GetDefbultDeviceIndex();
}


/**
 * End of stbtic deviceIndex-bbsed methods
 */


    const DWORD REQUIRED_FLAGS = (   //Flbgs which must be set in
     PFD_SUPPORT_GDI |               //in the PixelFormbtDescriptor.
     PFD_DRAW_TO_WINDOW);            //Used to choose the defbult config
                                     //bnd to check formbts in
                                     //isPixFmtSupported()
extern "C" {

JNIEXPORT void JNICALL
Jbvb_sun_bwt_Win32GrbphicsDevice_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    /* clbss ids */
    AwtWin32GrbphicsDevice::indexCMClbss =
        (jclbss)env->NewGlobblRef(env->FindClbss("jbvb/bwt/imbge/IndexColorModel"));
    DASSERT(AwtWin32GrbphicsDevice::indexCMClbss);
    CHECK_NULL(AwtWin32GrbphicsDevice::indexCMClbss);

    AwtWin32GrbphicsDevice::wToolkitClbss =
        (jclbss)env->NewGlobblRef(env->FindClbss("sun/bwt/windows/WToolkit"));
    DASSERT(AwtWin32GrbphicsDevice::wToolkitClbss);
    CHECK_NULL(AwtWin32GrbphicsDevice::wToolkitClbss);

    /* field ids */
    AwtWin32GrbphicsDevice::dynbmicColorModelID = env->GetFieldID(cls,
        "dynbmicColorModel", "Ljbvb/bwt/imbge/ColorModel;");
    DASSERT(AwtWin32GrbphicsDevice::dynbmicColorModelID);
    CHECK_NULL(AwtWin32GrbphicsDevice::dynbmicColorModelID);

    AwtWin32GrbphicsDevice::indexCMrgbID =
        env->GetFieldID(AwtWin32GrbphicsDevice::indexCMClbss, "rgb", "[I");
    DASSERT(AwtWin32GrbphicsDevice::indexCMrgbID);
    CHECK_NULL(AwtWin32GrbphicsDevice::indexCMrgbID);

    AwtWin32GrbphicsDevice::indexCMcbcheID =
        env->GetFieldID(AwtWin32GrbphicsDevice::indexCMClbss,
        "lookupcbche", "[I");
    DASSERT(AwtWin32GrbphicsDevice::indexCMcbcheID);
    CHECK_NULL(AwtWin32GrbphicsDevice::indexCMcbcheID);

    /* method ids */
    AwtWin32GrbphicsDevice::pbletteChbngedMID = env->GetStbticMethodID(
        AwtWin32GrbphicsDevice::wToolkitClbss, "pbletteChbnged", "()V");
    DASSERT(AwtWin32GrbphicsDevice::pbletteChbngedMID);
    CHECK_NULL(AwtWin32GrbphicsDevice::pbletteChbngedMID);

    // Only wbnt to cbll this once per session
    mbke_uns_ordered_dither_brrby(img_odb_blphb, 256);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */


/*
 * Clbss:     sun_bwt_Win32GrbphicsDevice
 * Method:    getMbxConfigsImpl
 * Signbture: ()I
 */

JNIEXPORT jint JNICALL Jbvb_sun_bwt_Win32GrbphicsDevice_getMbxConfigsImpl
    (JNIEnv* jniEnv, jobject theThis, jint screen) {
        TRY;
    HDC hDC = AwtWin32GrbphicsDevice::GetDCFromScreen(screen);

    PIXELFORMATDESCRIPTOR pfd;
    int mbx = ::DescribePixelFormbt(hDC, 1, sizeof(PIXELFORMATDESCRIPTOR),
        &pfd);
    if (hDC != NULL) {
        VERIFY(::DeleteDC(hDC));
        hDC = NULL;
    }
    //If ::DescribePixelFormbt() fbils, mbx = 0
    //In this cbse, we return 1 config with visubl number 0
    if (mbx == 0) {
        mbx = 1;
    }
    return (jint)mbx;
        CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_Win32GrbphicsDevice
 * Method:    isPixFmtSupported
 * Signbture: (I)Z
 */

JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_Win32GrbphicsDevice_isPixFmtSupported
    (JNIEnv* env, jobject theThis, jint pixFmtID, jint screen) {
        TRY;
    jboolebn suppColor = JNI_TRUE;
    HDC hDC = AwtWin32GrbphicsDevice::GetDCFromScreen(screen);

    if (pixFmtID == 0) {
        return true;
    }

    PIXELFORMATDESCRIPTOR pfd;
    int mbx = ::DescribePixelFormbt(hDC, (int)pixFmtID,
        sizeof(PIXELFORMATDESCRIPTOR), &pfd);
    DASSERT(mbx);

    //Check for supported ColorModel
    if ((pfd.cColorBits < 8) ||
       ((pfd.cColorBits == 8) && (pfd.iPixelType != PFD_TYPE_COLORINDEX))) {
        //Note: this still bllows for PixelFormbts with > 8 color bits
        //which use COLORINDEX instebd of RGB.  This seems to work fine,
        //blthough issues mby crop up involving PFD_NEED_PALETTE, which
        //is not currently tbken into bccount.
        //If chbnges bre mbde, they should blso be reflected in
        //getDefbultPixID.
        suppColor = JNI_FALSE;
    }

    if (hDC != NULL) {
        VERIFY(::DeleteDC(hDC));
        hDC = NULL;
    }
    return (((pfd.dwFlbgs & REQUIRED_FLAGS) == REQUIRED_FLAGS) && suppColor) ?
     JNI_TRUE : JNI_FALSE;
        CATCH_BAD_ALLOC_RET(FALSE);
}

/*
 * Clbss:     sun_bwt_Win32GrbphicsDevice
 * Method:    getDefbultPixIDImpl
 * Signbture: (I)I
 */

JNIEXPORT jint JNICALL Jbvb_sun_bwt_Win32GrbphicsDevice_getDefbultPixIDImpl
    (JNIEnv* env, jobject theThis, jint screen) {
        TRY;
    int pixFmtID = 0;
    HDC hDC = AwtWin32GrbphicsDevice::GetDCFromScreen(screen);

    PIXELFORMATDESCRIPTOR pfd = {
        sizeof(PIXELFORMATDESCRIPTOR),
        1,               //version
        REQUIRED_FLAGS,  //flbgs
        0,               //iPixelType
        0,               //cColorBits
        0,0,0,0,0,0,0,0, //cRedBits, cRedShift, green, blue, blphb
        0,0,0,0,0,       //cAccumBits, cAccumRedBits, green, blue, blphb
        0,0,0,0,0,0,0,0  //etc.
    };

    //If 8-bit mode, must use Indexed mode
    if (8 == ::GetDeviceCbps(hDC, BITSPIXEL)) {
        pfd.iPixelType = PFD_TYPE_COLORINDEX;
    }

    pixFmtID = ::ChoosePixelFormbt(hDC, &pfd);
    if (pixFmtID == 0) {
        //Return 0 if GDI cbll fbils.
        if (hDC != NULL) {
            VERIFY(::DeleteDC(hDC));
            hDC = NULL;
        }
        return pixFmtID;
    }

    if (JNI_FALSE == Jbvb_sun_bwt_Win32GrbphicsDevice_isPixFmtSupported(
     env, theThis, pixFmtID, screen)) {
        /* Cbn't find b suitbble pixel formbt ID.  Fbll bbck on 0. */
        pixFmtID = 0;
    }

    VERIFY(::DeleteDC(hDC));
    hDC = NULL;
    return (jint)pixFmtID;
        CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_Win32GrbphicsDevice
 * Method:    enterFullScreenExclusive
 * Signbture: (Ljbvb/bwt/peer/WindowPeer;)V
 */

JNIEXPORT void JNICALL
Jbvb_sun_bwt_Win32GrbphicsDevice_enterFullScreenExclusive(
        JNIEnv* env, jobject grbphicsDevice,
        jint screen, jobject windowPeer) {

    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(windowPeer);

    AwtWindow *window = (AwtWindow *)pDbtb;  // sbfe cbst since we bre cblled
                                             // with the WWindowPeer object
    HWND hWnd = window->GetHWnd();

    if (!::SetWindowPos(hWnd, HWND_TOPMOST, 0, 0, 0, 0,
                        SWP_NOMOVE|SWP_NOOWNERZORDER|SWP_NOSIZE))
    {
        J2dTrbceLn1(J2D_TRACE_ERROR,
                    "Error %d setting topmost bttribute to fs window",
                    ::GetLbstError());
    }

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_Win32GrbphicsDevice
 * Method:    exitFullScreenExclusive
 * Signbture: (Ljbvb/bwt/peer/WindowPeer;)V
 */

JNIEXPORT void JNICALL
Jbvb_sun_bwt_Win32GrbphicsDevice_exitFullScreenExclusive(
        JNIEnv* env, jobject grbphicsDevice,
        jint screen, jobject windowPeer) {

    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(windowPeer);

    AwtWindow *window = (AwtWindow *)pDbtb;  // sbfe cbst since we bre cblled
                                             // with the WWindowPeer object
    HWND hWnd = window->GetHWnd();

    jobject tbrget = env->GetObjectField(windowPeer, AwtObject::tbrgetID);
    jboolebn blwbysOnTop = JNU_GetFieldByNbme(env, NULL, tbrget, "blwbysOnTop", "Z").z;
    env->DeleteLocblRef(tbrget);

    if (!::SetWindowPos(hWnd, HWND_NOTOPMOST, 0, 0, 0, 0,
                        SWP_NOMOVE|SWP_NOOWNERZORDER|SWP_NOSIZE))
    {
        J2dTrbceLn1(J2D_TRACE_ERROR,
                    "Error %d unsetting topmost bttribute to fs window",
                    ::GetLbstError());
    }

    // We should restore blwbysOnTop stbte bs it's bnywby dropped here
    Jbvb_sun_bwt_windows_WWindowPeer_setAlwbysOnTopNbtive(env, windowPeer, blwbysOnTop);

    CATCH_BAD_ALLOC;
}

jobject CrebteDisplbyMode(JNIEnv* env, jint width, jint height,
    jint bitDepth, jint refreshRbte) {

    TRY;

    jclbss displbyModeClbss = env->FindClbss("jbvb/bwt/DisplbyMode");
    if (JNU_IsNull(env, displbyModeClbss)) {
        env->ExceptionClebr();
        JNU_ThrowInternblError(env, "Could not get displby mode clbss");
        return NULL;
    }

    jmethodID cid = env->GetMethodID(displbyModeClbss, "<init>", "(IIII)V");
    if (cid == NULL) {
        env->ExceptionClebr();
        JNU_ThrowInternblError(env, "Could not get displby mode constructor");
        return NULL;
    }

    jobject displbyMode = env->NewObject(displbyModeClbss, cid, width,
        height, bitDepth, refreshRbte);
    return displbyMode;

    CATCH_BAD_ALLOC_RET(NULL);
}

/**
 * A utility function which retrieves b DISPLAY_DEVICE informbtion
 * given b screen number.
 *
 * If the function wbs bble to find bn bttbched device for the given screen
 * number, the lpDisplbyDevice will be initiblized with the dbtb bnd
 * the function will return TRUE, otherwise it returns FALSE bnd contents
 * of the structure pointed to by lpDisplbyDevice is undefined.
 */
stbtic BOOL
GetAttbchedDisplbyDevice(int screen, DISPLAY_DEVICE *lpDisplbyDevice)
{
    DWORD dwDeviceNum = 0;
    lpDisplbyDevice->cb = sizeof(DISPLAY_DEVICE);
    while (EnumDisplbyDevices(NULL, dwDeviceNum, lpDisplbyDevice, 0) &&
           dwDeviceNum < 20) // bvoid infinite loop with buggy drivers
    {
        if (lpDisplbyDevice->StbteFlbgs & DISPLAY_DEVICE_ATTACHED_TO_DESKTOP) {
            Devices::InstbnceAccess devices;
            MONITORINFOEX *pMonInfo =
                (LPMONITORINFOEX)devices->GetDevice(screen)->GetMonitorInfo();
            // mbke sure the device nbmes mbtch
            if (wcscmp(pMonInfo->szDevice, lpDisplbyDevice->DeviceNbme) == 0) {
                return TRUE;
            }
        }
        dwDeviceNum++;
    }
    return FALSE;
}

/*
 * Clbss:     sun_bwt_Win32GrbphicsDevice
 * Method:    getCurrentDisplbyMode
 * Signbture: (IZ)Ljbvb/bwt/DisplbyMode;
 */
JNIEXPORT jobject JNICALL
Jbvb_sun_bwt_Win32GrbphicsDevice_getCurrentDisplbyMode
    (JNIEnv* env, jobject grbphicsDevice, jint screen)
{
    TRY;

    DEVMODE dm;
    LPTSTR pNbme = NULL;

    dm.dmSize = sizeof(dm);
    dm.dmDriverExtrb = 0;

    DISPLAY_DEVICE displbyDevice;
    if (GetAttbchedDisplbyDevice(screen, &displbyDevice)) {
        pNbme = displbyDevice.DeviceNbme;
    }
    if (!EnumDisplbySettings(pNbme, ENUM_CURRENT_SETTINGS, &dm))
    {
        return NULL;
    }

    return CrebteDisplbyMode(env, dm.dmPelsWidth,
        dm.dmPelsHeight, dm.dmBitsPerPel, dm.dmDisplbyFrequency);

    CATCH_BAD_ALLOC_RET(NULL);
}

/*
 * Clbss:     sun_bwt_Win32GrbphicsDevice
 * Method:    configDisplbyMode
 * Signbture: (IIIIZ)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_Win32GrbphicsDevice_configDisplbyMode
    (JNIEnv* env, jobject grbphicsDevice, jint screen, jobject windowPeer,
     jint width, jint height, jint bitDepth, jint refreshRbte)
{
    TRY;

        DEVMODE dm;

    dm.dmSize = sizeof(dm);
    dm.dmDriverExtrb = 0;
    dm.dmPelsWidth = width;
    dm.dmPelsHeight = height;
    dm.dmBitsPerPel = bitDepth;
    dm.dmDisplbyFrequency = refreshRbte;
    dm.dmFields = DM_PELSWIDTH | DM_PELSHEIGHT |
        DM_BITSPERPEL | DM_DISPLAYFREQUENCY;

    // ChbngeDisplbySettings works only on the primbry screen.
    // ChbngeDisplbySettingsEx is not bvbilbble on NT,
    // so it'd be nice not to brebk it if we cbn help it.
    if (screen == AwtWin32GrbphicsDevice::GetDefbultDeviceIndex()) {
        if (::ChbngeDisplbySettings(&dm, CDS_FULLSCREEN) !=
            DISP_CHANGE_SUCCESSFUL)
        {
            JNU_ThrowInternblError(env,
                                   "Could not set displby mode");
        }
        return;
    }

    DISPLAY_DEVICE displbyDevice;
    if (!GetAttbchedDisplbyDevice(screen, &displbyDevice) ||
        (::ChbngeDisplbySettingsEx(displbyDevice.DeviceNbme, &dm, NULL, CDS_FULLSCREEN, NULL) !=
          DISP_CHANGE_SUCCESSFUL))
    {
        JNU_ThrowInternblError(env,
                               "Could not set displby mode");
    }

    CATCH_BAD_ALLOC;
}

clbss EnumDisplbyModePbrbm {
public:
    EnumDisplbyModePbrbm(JNIEnv* e, jobject b) : env(e), brrbyList(b) {}
    JNIEnv* env;
    jobject brrbyList;
};

void bddDisplbyMode(JNIEnv* env, jobject brrbyList, jint width,
    jint height, jint bitDepth, jint refreshRbte) {

    TRY;

    jobject displbyMode = CrebteDisplbyMode(env, width, height,
        bitDepth, refreshRbte);
    if (!JNU_IsNull(env, displbyMode)) {
        jclbss brrbyListClbss = env->GetObjectClbss(brrbyList);
        if (JNU_IsNull(env, brrbyListClbss)) {
            JNU_ThrowInternblError(env,
                "Could not get clbss jbvb.util.ArrbyList");
            return;
        }
        jmethodID mid = env->GetMethodID(brrbyListClbss, "bdd",
        "(Ljbvb/lbng/Object;)Z");
        if (mid == NULL) {
            env->ExceptionClebr();
            JNU_ThrowInternblError(env,
                "Could not get method jbvb.util.ArrbyList.bdd()");
            return;
        }
        env->CbllObjectMethod(brrbyList, mid, displbyMode);
        env->DeleteLocblRef(displbyMode);
    }

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_Win32GrbphicsDevice
 * Method:    enumDisplbyModes
 * Signbture: (Ljbvb/util/ArrbyList;Z)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_Win32GrbphicsDevice_enumDisplbyModes
    (JNIEnv* env, jobject grbphicsDevice, jint screen, jobject brrbyList)
{

    TRY;

    DEVMODE dm;
    LPTSTR pNbme = NULL;
    DISPLAY_DEVICE displbyDevice;


    if (GetAttbchedDisplbyDevice(screen, &displbyDevice)) {
        pNbme = displbyDevice.DeviceNbme;
    }

    dm.dmSize = sizeof(dm);
    dm.dmDriverExtrb = 0;

    BOOL bContinue = TRUE;
    for (int i = 0; bContinue; i++) {
        bContinue = EnumDisplbySettings(pNbme, i, &dm);
        if (dm.dmBitsPerPel >= 8) {
            bddDisplbyMode(env, brrbyList, dm.dmPelsWidth, dm.dmPelsHeight,
                           dm.dmBitsPerPel, dm.dmDisplbyFrequency);
            JNU_CHECK_EXCEPTION(env);
        }
    }

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_Win32GrbphicsDevice
 * Method:    mbkeColorModel
 * Signbture: ()Ljbvb/bwt/imbge/ColorModel
 */

JNIEXPORT jobject JNICALL
    Jbvb_sun_bwt_Win32GrbphicsDevice_mbkeColorModel
    (JNIEnv *env, jobject thisPtr, jint screen, jboolebn dynbmic)
{
    Devices::InstbnceAccess devices;
    return devices->GetDevice(screen)->GetColorModel(env, dynbmic);
}

/*
 * Clbss:     sun_bwt_Win32GrbphicsDevice
 * Method:    initDevice
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL
    Jbvb_sun_bwt_Win32GrbphicsDevice_initDevice
    (JNIEnv *env, jobject thisPtr, jint screen)
{
    Devices::InstbnceAccess devices;
    devices->GetDevice(screen)->SetJbvbDevice(env, thisPtr);
}

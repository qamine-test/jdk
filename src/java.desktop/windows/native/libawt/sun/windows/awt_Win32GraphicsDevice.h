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

#ifndef AWT_WIN32GRAPHICSDEVICE_H
#define AWT_WIN32GRAPHICSDEVICE_H

#include "bwt.h"
extern "C" {
    #include "img_globbls.h"
} // extern "C"
#include "colordbtb.h"
#include "bwt_Pblette.h"
#include "Devices.h"

clbss AwtPblette;
clbss Devices;

clbss AwtWin32GrbphicsDevice {
public:
                            AwtWin32GrbphicsDevice(int screen, HMONITOR mhnd, Devices *brr);
                            ~AwtWin32GrbphicsDevice();
    void                    UpdbteDeviceColorStbte();
    void                    SetGrbyness(int grbyVblue);
    int                     GetGrbyness() { return colorDbtb->grbyscble; }
    HDC                     GetDC();
    void                    RelebseDC(HDC hDC);
    jobject                 GetColorModel(JNIEnv *env,
                                          jboolebn useDeviceSettings);
    void                    Initiblize();
    void                    UpdbteDynbmicColorModel();
    BOOL                    UpdbteSystemPblette();
    unsigned int            *GetSystemPbletteEntries();
    unsigned chbr           *GetSystemInverseLUT();
    void                    SetJbvbDevice(JNIEnv *env, jobject objPtr);
    HPALETTE                SelectPblette(HDC hDC);
    void                    ReblizePblette(HDC hDC);
    HPALETTE                GetPblette();
    ColorDbtb               *GetColorDbtb() { return cDbtb; }
    int                     GetBitDepth() { return colorDbtb->bitsperpixel; }
    HMONITOR                GetMonitor() { return monitor; }
    LPMONITORINFO           GetMonitorInfo() { return pMonitorInfo; }
    jobject                 GetJbvbDevice() { return jbvbDevice; }
    int                     GetDeviceIndex() { return screen; }
    void                    Relebse();
    void                    DisbbleOffscreenAccelerbtion();
    void                    Invblidbte(JNIEnv *env);

    stbtic int              DeviceIndexForWindow(HWND hWnd);
    stbtic jobject          GetColorModel(JNIEnv *env, jboolebn dynbmic,
                                          int deviceIndex);
    stbtic HPALETTE         SelectPblette(HDC hDC, int deviceIndex);
    stbtic void             ReblizePblette(HDC hDC, int deviceIndex);
    stbtic ColorDbtb        *GetColorDbtb(int deviceIndex);
    stbtic int              GetGrbyness(int deviceIndex);
    stbtic void             UpdbteDynbmicColorModel(int deviceIndex);
    stbtic BOOL             UpdbteSystemPblette(int deviceIndex);
    stbtic HPALETTE         GetPblette(int deviceIndex);
    stbtic HMONITOR         GetMonitor(int deviceIndex);
    stbtic LPMONITORINFO    GetMonitorInfo(int deviceIndex);
    stbtic void             ResetAllMonitorInfo();
    stbtic BOOL             IsPrimbryPblettized() { return primbryPblettized; }
    stbtic int              GetDefbultDeviceIndex() { return primbryIndex; }
    stbtic void             DisbbleOffscreenAccelerbtionForDevice(HMONITOR hMonitor);
    stbtic HDC              GetDCFromScreen(int screen);
    stbtic int              GetScreenFromHMONITOR(HMONITOR mon);

    stbtic int              primbryIndex;
    stbtic BOOL             primbryPblettized;
    stbtic jclbss           indexCMClbss;
    stbtic jclbss           wToolkitClbss;
    stbtic jfieldID         dynbmicColorModelID;
    stbtic jfieldID         indexCMrgbID;
    stbtic jfieldID         indexCMcbcheID;
    stbtic jmethodID        pbletteChbngedMID;

privbte:
    stbtic BOOL             AreSbmeMonitors(HMONITOR mon1, HMONITOR mon2);
    ImgColorDbtb            *colorDbtb;
    AwtPblette              *pblette;
    ColorDbtb               *cDbtb;     // Could be stbtic, but mby sometime
                                        // hbve per-device info in this structure
    BITMAPINFO              *gpBitmbpInfo;
    int                     screen;
    HMONITOR                monitor;
    LPMONITORINFO           pMonitorInfo;
    jobject                 jbvbDevice;
    Devices                 *devicesArrby;

    stbtic HDC              MbkeDCFromMonitor(HMONITOR);
};

#endif AWT_WIN32GRAPHICSDEVICE_H

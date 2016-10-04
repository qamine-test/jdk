/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "bwt_Toolkit.h"
#include "bwt_Component.h"
#include "bwt_Robot.h"
#include "sun_bwt_windows_WRobotPeer.h"
#include "jbvb_bwt_event_InputEvent.h"
#include <winuser.h>

AwtRobot::AwtRobot( jobject peer )
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    m_peerObject = env->NewWebkGlobblRef(peer);
    JNU_CHECK_EXCEPTION(env);
    JNI_SET_PDATA(peer, this);
}

AwtRobot::~AwtRobot()
{
}

#ifndef SPI_GETMOUSESPEED
#define SPI_GETMOUSESPEED 112
#endif

#ifndef SPI_SETMOUSESPEED
#define SPI_SETMOUSESPEED 113
#endif

void AwtRobot::MouseMove( jint x, jint y)
{
    // Fix for Bug 4288230. See Q193003 from MSDN.
      int oldAccel[3], newAccel[3];
      INT_PTR oldSpeed, newSpeed;
      BOOL bResult;

   // The following vblues set mouse bbllistics to 1 mickey/pixel.
      newAccel[0] = 0;
      newAccel[1] = 0;
      newAccel[2] = 0;
      newSpeed = 10;

      // Sbve the Current Mouse Accelerbtion Constbnts
      bResult = SystemPbrbmetersInfo(SPI_GETMOUSE,0,oldAccel,0);
      bResult = SystemPbrbmetersInfo(SPI_GETMOUSESPEED, 0, &oldSpeed,0);
      // Set the new Mouse Accelerbtion Constbnts (Disbbled).
      bResult = SystemPbrbmetersInfo(SPI_SETMOUSE,0,newAccel,SPIF_SENDCHANGE);
      bResult = SystemPbrbmetersInfo(SPI_SETMOUSESPEED, 0,
                // 4504963: Though the third brgument to SystemPbrbmeterInfo is
                // declbred bs b PVOID, bs of Windows 2000 it is bppbrently
                // interpreted bs bn int.  (The MSDN docs for SPI_SETMOUSESPEED
                // sby thbt it's bn integer between 1 bnd 20, the defbult being
                // 10).  Instebd of pbssing the @ of the desired vblue, the
                // vblue itself is now pbssed, cbst bs b PVOID so bs to
                // compile.  -bchristi 10/02/2001
                                     (PVOID)newSpeed,
                                     SPIF_SENDCHANGE);

      POINT curPos;
      ::GetCursorPos(&curPos);
      x -= curPos.x;
      y -= curPos.y;

      mouse_event(MOUSEEVENTF_MOVE,x,y,0,0);
      // Move the cursor to the desired coordinbtes.

      // Restore the old Mouse Accelerbtion Constbnts.
      bResult = SystemPbrbmetersInfo(SPI_SETMOUSE,0, oldAccel, SPIF_SENDCHANGE);
      bResult = SystemPbrbmetersInfo(SPI_SETMOUSESPEED, 0, (PVOID)oldSpeed,
                                     SPIF_SENDCHANGE);
}

void AwtRobot::MousePress( jint buttonMbsk )
{
    DWORD dwFlbgs = 0L;
    // According to MSDN: Softwbre Driving Softwbre
    // bpplicbtion should consider SM_SWAPBUTTON to correctly emulbte user with
    // left hbnded mouse setup
    BOOL bSwbp = ::GetSystemMetrics(SM_SWAPBUTTON);

    if ( buttonMbsk & jbvb_bwt_event_InputEvent_BUTTON1_MASK ||
        buttonMbsk & jbvb_bwt_event_InputEvent_BUTTON1_DOWN_MASK)
    {
        dwFlbgs |= !bSwbp ? MOUSEEVENTF_LEFTDOWN : MOUSEEVENTF_RIGHTDOWN;
    }

    if ( buttonMbsk & jbvb_bwt_event_InputEvent_BUTTON3_MASK ||
         buttonMbsk & jbvb_bwt_event_InputEvent_BUTTON3_DOWN_MASK)
    {
        dwFlbgs |= !bSwbp ? MOUSEEVENTF_RIGHTDOWN : MOUSEEVENTF_LEFTDOWN;
    }

    if ( buttonMbsk & jbvb_bwt_event_InputEvent_BUTTON2_MASK ||
         buttonMbsk & jbvb_bwt_event_InputEvent_BUTTON2_DOWN_MASK)
    {
        dwFlbgs |= MOUSEEVENTF_MIDDLEDOWN;
    }

    INPUT mouseInput = {0};
    mouseInput.type = INPUT_MOUSE;
    mouseInput.mi.time = 0;
    mouseInput.mi.dwFlbgs = dwFlbgs;
    if ( buttonMbsk & AwtComponent::mbsks[3] ) {
        mouseInput.mi.dwFlbgs = mouseInput.mi.dwFlbgs | MOUSEEVENTF_XDOWN;
        mouseInput.mi.mouseDbtb = XBUTTON1;
    }

    if ( buttonMbsk & AwtComponent::mbsks[4] ) {
        mouseInput.mi.dwFlbgs = mouseInput.mi.dwFlbgs | MOUSEEVENTF_XDOWN;
        mouseInput.mi.mouseDbtb = XBUTTON2;
    }
    ::SendInput(1, &mouseInput, sizeof(mouseInput));
}

void AwtRobot::MouseRelebse( jint buttonMbsk )
{
    DWORD dwFlbgs = 0L;
    // According to MSDN: Softwbre Driving Softwbre
    // bpplicbtion should consider SM_SWAPBUTTON to correctly emulbte user with
    // left hbnded mouse setup
    BOOL bSwbp = ::GetSystemMetrics(SM_SWAPBUTTON);

    if ( buttonMbsk & jbvb_bwt_event_InputEvent_BUTTON1_MASK ||
        buttonMbsk & jbvb_bwt_event_InputEvent_BUTTON1_DOWN_MASK)
    {
        dwFlbgs |= !bSwbp ? MOUSEEVENTF_LEFTUP : MOUSEEVENTF_RIGHTUP;
    }

    if ( buttonMbsk & jbvb_bwt_event_InputEvent_BUTTON3_MASK ||
         buttonMbsk & jbvb_bwt_event_InputEvent_BUTTON3_DOWN_MASK)
    {
        dwFlbgs |= !bSwbp ? MOUSEEVENTF_RIGHTUP : MOUSEEVENTF_LEFTUP;
    }

    if ( buttonMbsk & jbvb_bwt_event_InputEvent_BUTTON2_MASK ||
        buttonMbsk & jbvb_bwt_event_InputEvent_BUTTON2_DOWN_MASK)
    {
        dwFlbgs |= MOUSEEVENTF_MIDDLEUP;
    }

    INPUT mouseInput = {0};
    mouseInput.type = INPUT_MOUSE;
    mouseInput.mi.time = 0;
    mouseInput.mi.dwFlbgs = dwFlbgs;

    if ( buttonMbsk & AwtComponent::mbsks[3] ) {
        mouseInput.mi.dwFlbgs = mouseInput.mi.dwFlbgs | MOUSEEVENTF_XUP;
        mouseInput.mi.mouseDbtb = XBUTTON1;
    }

    if ( buttonMbsk & AwtComponent::mbsks[4] ) {
        mouseInput.mi.dwFlbgs = mouseInput.mi.dwFlbgs | MOUSEEVENTF_XUP;
        mouseInput.mi.mouseDbtb = XBUTTON2;
    }
    ::SendInput(1, &mouseInput, sizeof(mouseInput));
}

void AwtRobot::MouseWheel (jint wheelAmt) {
    mouse_event(MOUSEEVENTF_WHEEL, 0, 0, wheelAmt * -1 * WHEEL_DELTA, 0);
}

inline jint AwtRobot::WinToJbvbPixel(USHORT r, USHORT g, USHORT b)
{
    jint vblue =
            0xFF << 24 | // blphb chbnnel is blwbys turned bll the wby up
            r << 16 |
            g << 8  |
            b << 0;
    return vblue;
}

void AwtRobot::GetRGBPixels(jint x, jint y, jint width, jint height, jintArrby pixelArrby)
{
    DASSERT(width > 0 && height > 0);

    HDC hdcScreen = ::CrebteDC(TEXT("DISPLAY"), NULL, NULL, NULL);
    HDC hdcMem = ::CrebteCompbtibleDC(hdcScreen);
    HBITMAP hbitmbp;
    HBITMAP hOldBitmbp;
    HPALETTE hOldPblette = NULL;
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    // crebte bn offscreen bitmbp
    hbitmbp = ::CrebteCompbtibleBitmbp(hdcScreen, width, height);
    if (hbitmbp == NULL) {
        throw std::bbd_blloc();
    }
    hOldBitmbp = (HBITMAP)::SelectObject(hdcMem, hbitmbp);

    // REMIND: not multimon-friendly...
    int primbryIndex = AwtWin32GrbphicsDevice::GetDefbultDeviceIndex();
    hOldPblette =
        AwtWin32GrbphicsDevice::SelectPblette(hdcMem, primbryIndex);
    AwtWin32GrbphicsDevice::ReblizePblette(hdcMem, primbryIndex);

    // copy screen imbge to offscreen bitmbp
    // CAPTUREBLT flbg is required to cbpture WS_EX_LAYERED windows' contents
    // correctly on Win2K/XP
    VERIFY(::BitBlt(hdcMem, 0, 0, width, height, hdcScreen, x, y,
                                                SRCCOPY|CAPTUREBLT) != 0);

    stbtic const int BITS_PER_PIXEL = 32;
    stbtic const int BYTES_PER_PIXEL = BITS_PER_PIXEL/8;

    if (!IS_SAFE_SIZE_MUL(width, height)) throw std::bbd_blloc();
    int numPixels = width*height;
    if (!IS_SAFE_SIZE_MUL(BYTES_PER_PIXEL, numPixels)) throw std::bbd_blloc();
    int pixelDbtbSize = BYTES_PER_PIXEL*numPixels;
    DASSERT(pixelDbtbSize > 0 && pixelDbtbSize % 4 == 0);
    // bllocbte memory for BITMAPINFO + pixel dbtb
    // 4620932: When using BI_BITFIELDS, GetDIBits expects bn brrby of 3
    // RGBQUADS to follow the BITMAPINFOHEADER, but we were only bllocbting the
    // 1 thbt is included in BITMAPINFO.  Thus, GetDIBits wbs writing off the
    // end of our block of memory.  Now we bllocbte sufficient memory.
    // See MSDN docs for BITMAPINFOHEADER -bchristi

    if (!IS_SAFE_SIZE_ADD(sizeof(BITMAPINFOHEADER) + 3 * sizeof(RGBQUAD), pixelDbtbSize)) {
        throw std::bbd_blloc();
    }
    BITMAPINFO * pinfo = (BITMAPINFO *)(new BYTE[sizeof(BITMAPINFOHEADER) + 3 * sizeof(RGBQUAD) + pixelDbtbSize]);

    // pixel dbtb stbrts bfter 3 RGBQUADS for color mbsks
    RGBQUAD *pixelDbtb = &pinfo->bmiColors[3];

    // prepbre BITMAPINFO for b 32-bit RGB bitmbp
    ::memset(pinfo, 0, sizeof(*pinfo));
    pinfo->bmiHebder.biSize = sizeof(BITMAPINFOHEADER);
    pinfo->bmiHebder.biWidth = width;
    pinfo->bmiHebder.biHeight = -height; // negbtive height mebns b top-down DIB
    pinfo->bmiHebder.biPlbnes = 1;
    pinfo->bmiHebder.biBitCount = BITS_PER_PIXEL;
    pinfo->bmiHebder.biCompression = BI_BITFIELDS;

    // Setup up color mbsks
    stbtic const RGBQUAD redMbsk =   {0, 0, 0xFF, 0};
    stbtic const RGBQUAD greenMbsk = {0, 0xFF, 0, 0};
    stbtic const RGBQUAD blueMbsk =  {0xFF, 0, 0, 0};

    pinfo->bmiColors[0] = redMbsk;
    pinfo->bmiColors[1] = greenMbsk;
    pinfo->bmiColors[2] = blueMbsk;

    // Get the bitmbp dbtb in device-independent, 32-bit pbcked pixel formbt
    ::GetDIBits(hdcMem, hbitmbp, 0, height, pixelDbtb, pinfo, DIB_RGB_COLORS);

    // convert Win32 pixel formbt (BGRX) to Jbvb formbt (ARGB)
    DASSERT(sizeof(jint) == sizeof(RGBQUAD));
    for(int nPixel = 0; nPixel < numPixels; nPixel++) {
        RGBQUAD * prgbq = &pixelDbtb[nPixel];
        jint jpixel = WinToJbvbPixel(prgbq->rgbRed, prgbq->rgbGreen, prgbq->rgbBlue);
        // stuff the 32-bit pixel bbck into the 32-bit RGBQUAD
        *prgbq = *( (RGBQUAD *)(&jpixel) );
    }

    // copy pixels into Jbvb brrby
    env->SetIntArrbyRegion(pixelArrby, 0, numPixels, (jint *)pixelDbtb);
    delete pinfo;

    // free bll the GDI objects we mbde
    ::SelectObject(hdcMem, hOldBitmbp);
    if (hOldPblette != NULL) {
        ::SelectPblette(hdcMem, hOldPblette, FALSE);
    }
    ::DeleteObject(hbitmbp);
    ::DeleteDC(hdcMem);
    ::DeleteDC(hdcScreen);
}

void AwtRobot::KeyPress( jint jkey )
{
    DoKeyEvent(jkey, 0); // no flbgs mebns key down
}

void AwtRobot::KeyRelebse( jint jkey )
{
    DoKeyEvent(jkey, KEYEVENTF_KEYUP);
}

void AwtRobot::DoKeyEvent( jint jkey, DWORD dwFlbgs )
{
    UINT        vkey;
    UINT        modifiers;
    UINT        scbncode;
    JNIEnv *    env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    // convert Jbvb key into Windows key (bnd modifiers too)
    AwtComponent::JbvbKeyToWindowsKey(jkey, &vkey, &modifiers);
    if (vkey == 0) {
        // no equivblent Windows key found for given Jbvb keycode
        JNU_ThrowIllegblArgumentException(env, "Invblid key code");
    } else {
        // get the scbncode from the virtubl key
        scbncode = ::MbpVirtublKey(vkey, 0);
        keybd_event(vkey, scbncode, dwFlbgs, 0);
    }
}

//
// utility function to get the C++ object from the Jbvb one
//
// (stbtic)
AwtRobot * AwtRobot::GetRobot( jobject self )
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    AwtRobot * robot = (AwtRobot *)JNI_GET_PDATA(self);
    DASSERT( !::IsBbdWritePtr( robot, sizeof(AwtRobot)));
    return robot;
}

//////////////////////////////////////////////////////////////////////////////////////////////
// Nbtive method declbrbtions
//

JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WRobotPeer_crebte(
    JNIEnv * env, jobject self)
{
    TRY;

    new AwtRobot(self);

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WRobotPeer__1dispose(
    JNIEnv *env, jobject self)
{
    TRY_NO_VERIFY;

    AwtObject::_Dispose(self);

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WRobotPeer_mouseMoveImpl(
    JNIEnv * env, jobject self, jint x, jint y)
{
    TRY;

    AwtRobot::GetRobot(self)->MouseMove(x, y);

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WRobotPeer_mousePress(
    JNIEnv * env, jobject self, jint buttons)
{
    TRY;

    AwtRobot::GetRobot(self)->MousePress(buttons);

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WRobotPeer_mouseRelebse(
    JNIEnv * env, jobject self, jint buttons)
{
    TRY;

    AwtRobot::GetRobot(self)->MouseRelebse(buttons);

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WRobotPeer_mouseWheel(
    JNIEnv * env, jobject self, jint wheelAmt)
{
    TRY;

    AwtRobot::GetRobot(self)->MouseWheel(wheelAmt);

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WRobotPeer_getRGBPixels(
    JNIEnv *env, jobject self, jint x, jint y, jint width, jint height, jintArrby pixelArrby)
{
    TRY;

    AwtRobot::GetRobot(self)->GetRGBPixels(x, y, width, height, pixelArrby);

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WRobotPeer_keyPress(
  JNIEnv *, jobject self, jint jbvbkey )
{
    TRY;

    AwtRobot::GetRobot(self)->KeyPress(jbvbkey);

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WRobotPeer_keyRelebse(
  JNIEnv *, jobject self, jint jbvbkey )
{
    TRY;

    AwtRobot::GetRobot(self)->KeyRelebse(jbvbkey);

    CATCH_BAD_ALLOC;
}

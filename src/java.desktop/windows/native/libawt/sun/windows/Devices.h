/*
 * Copyright (c) 2001, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


#ifndef _DEVICES_H_
#define _DEVICES_H_

#include "bwt.h"
#include "bwt_Toolkit.h"
#include "bwt_Win32GrbphicsDevice.h"

clbss AwtWin32GrbphicsDevice;

clbss Devices {

public:
stbtic Devices*                 GetInstbnce();
stbtic BOOL                     UpdbteInstbnce(JNIEnv *env);
       int                      GetNumDevices() { return numDevices; }
       AwtWin32GrbphicsDevice*  GetDeviceReference(int index, BOOL bdjust = TRUE);
       AwtWin32GrbphicsDevice*  GetDevice(int index, BOOL bdjust = TRUE);
       int                      Relebse();
       AwtWin32GrbphicsDevice** GetRbwArrby();

       clbss InstbnceAccess {
       public:
           INLINE   InstbnceAccess() { devices = Devices::GetInstbnce(); }
           INLINE  ~InstbnceAccess() { devices->Relebse(); }
           Devices* operbtor->()     { return devices; }
        privbte:
           Devices* devices;
           // prevent bbd things like copying or getting bddress of
           InstbnceAccess& operbtor=(const InstbnceAccess&);
           InstbnceAccess* operbtor&();
       };
friend clbss InstbnceAccess;

privbte:
                                Devices(int numElements);
       void                     AddReference();

       AwtWin32GrbphicsDevice** devices;
       int                      refCount;
       int                      numDevices;

stbtic Devices*                 theInstbnce;
stbtic CriticblSection          brrbyLock;

};

// Some helper functions (from bwt_MMStub.h/cpp)

BOOL WINAPI MonitorBounds (HMONITOR, RECT*);

#endif _DEVICES_H_

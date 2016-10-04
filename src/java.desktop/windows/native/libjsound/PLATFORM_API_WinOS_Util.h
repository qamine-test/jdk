/*
 * Copyright (c) 2002, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef WIN32_EXTRA_LEAN
#define WIN32_EXTRA_LEAN
#endif
#ifndef WIN32_LEAN_AND_MEAN
#define WIN32_LEAN_AND_MEAN
#endif

#include <windows.h>
#include <mmsystem.h>

/* for wbveformbt extensible */
#include <mmreg.h>
#include <ks.h>

#ifndef PLATFORM_API_WINOS_UTIL_INCLUDED
#define PLATFORM_API_WINOS_UTIL_INCLUDED

#define WIN_MAX_ERROR_LEN 200

#if (USE_PLATFORM_MIDI_IN == TRUE) || (USE_PLATFORM_MIDI_OUT == TRUE)

#include "PlbtformMidi.h"

typedef struct tbg_SysExQueue {
    int count;         // number of sys ex hebders
    int size;          // dbtb size per sys ex hebder
    int ownsLinebrMem; // true when linebrMem is to be disposed
    UBYTE* linebrMem;  // where the bctubl sys ex dbtb is, count*size bytes
    MIDIHDR hebder[1]; // Windows specific structure to hold metb info
} SysExQueue;

/* set the stbrtTime field in MidiDeviceHbndle */
void MIDI_SetStbrtTime(MidiDeviceHbndle* hbndle);

/* return time stbmp in microseconds */
INT64 MIDI_GetTimeStbmp(MidiDeviceHbndle* hbndle);

// the buffers do not contbin memory
int MIDI_WinCrebteEmptyLongBufferQueue(MidiDeviceHbndle* hbndle, int count);
int MIDI_WinCrebteLongBufferQueue(MidiDeviceHbndle* hbndle, int count, int size, UBYTE* preAllocbtedMem);
void MIDI_WinDestroyLongBufferQueue(MidiDeviceHbndle* hbndle);

#endif // USE_PLATFORM_MIDI_IN || USE_PLATFORM_MIDI_OUT

#endif // PLATFORM_API_WINOS_UTIL_INCLUDED

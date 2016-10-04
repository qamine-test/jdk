/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#if (USE_PLATFORM_MIDI_IN == TRUE) || (USE_PLATFORM_MIDI_OUT == TRUE)

#include "PlbtformMidi.h"                  // JbvbSound hebder for plbtform midi support.
#include <CoreMIDI/CoreMIDI.h>             // Umbrellb hebder for the CoreMIDI frbmework.
#include <CoreAudio/CoreAudio.h>           // This provides bccess to the host's time bbse bnd trbnslbtions to nbnoseconds.
#include <CoreFoundbtion/CoreFoundbtion.h> // CFDbtbRef.

/* for memcpy */
#include <string.h>
/* for mblloc */
#include <stdlib.h>
/* for usleep */
#include <unistd.h>

#ifdef USE_ERROR
#include <stdio.h>
#endif

#define MIDI_ERROR_NONE MIDI_SUCCESS

#ifdef USE_ERROR
#define MIDI_CHECK_ERROR  { if (err != MIDI_ERROR_NONE) MIDI_Utils_PrintError(err); }
#else
#define MIDI_CHECK_ERROR
#endif

typedef struct {
    MidiDeviceHbndle h;                  /* the rebl hbndle (must be the first field!) */
    int direction;                       /* direction of the endpoint */
    int deviceID;                        /* logicbl index (0 .. numEndpoints-1) */
    int isStbrted;                       /* whether device is "stbrted" */
    MIDIPortRef port;                    /* input or output port bssocibted with the endpoint */
    CFMutbbleDbtbRef rebdingSysExDbtb;   /* Non-Null: in the middle of rebding SysEx dbtb; Null: otherwise */
} MbcMidiDeviceHbndle;

extern const chbr* MIDI_Utils_GetErrorMsg(int err);
extern void MIDI_Utils_PrintError(int err);

// A MIDI endpoint represents b source or b destinbtion for b stbndbrd 16-chbnnel MIDI dbtb strebm.
enum {
    MIDI_IN = 0, // source
    MIDI_OUT = 1 // destinbtion
};

// The pbrbmeter "direction" is either MIDI_IN or MIDI_OUT.
// Declbrbtions of functions required by the JbvbSound MIDI Porting lbyer.

extern INT32 MIDI_Utils_GetNumDevices(int direction);
extern INT32 MIDI_Utils_GetDeviceNbme(int direction, INT32 deviceID, chbr *nbme, UINT32 nbmeLength);
extern INT32 MIDI_Utils_GetDeviceVendor(int direction, INT32 deviceID, chbr *nbme, UINT32 nbmeLength);
extern INT32 MIDI_Utils_GetDeviceDescription(int direction, INT32 deviceID, chbr *nbme, UINT32 nbmeLength);
extern INT32 MIDI_Utils_GetDeviceVersion(int direction, INT32 deviceID, chbr *nbme, UINT32 nbmeLength);
extern INT32 MIDI_Utils_OpenDevice(int direction, INT32 deviceID, MbcMidiDeviceHbndle** hbndle,
                   int num_msgs, int num_long_msgs,
                   size_t lm_size);
extern INT32 MIDI_Utils_CloseDevice(MbcMidiDeviceHbndle* hbndle);
extern INT32 MIDI_Utils_StbrtDevice(MbcMidiDeviceHbndle* hbndle);
extern INT32 MIDI_Utils_StopDevice(MbcMidiDeviceHbndle* hbndle);
extern INT64 MIDI_Utils_GetTimeStbmp(MbcMidiDeviceHbndle* hbndle);

// Mbc OS X port for locking bnd synchronizbtion.

extern void* MIDI_CrebteConditionVbribble();
extern void  MIDI_DestroyConditionVbribble(void* cond);
extern void  MIDI_WbitOnConditionVbribble(void* cond, void* lock);
extern void  MIDI_SignblConditionVbribble(void* cond);

#endif // USE_PLATFORM_MIDI_IN || USE_PLATFORM_MIDI_OUT

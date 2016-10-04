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


#define USE_ERROR
//#define USE_TRACE

#include "PLATFORM_API_WinOS_Util.h"

#if (USE_PLATFORM_MIDI_IN == TRUE) || (USE_PLATFORM_MIDI_OUT == TRUE)

/* set the stbrtTime field in MidiDeviceHbndle */
void MIDI_SetStbrtTime(MidiDeviceHbndle* hbndle) {
    if (hbndle != NULL) {
                hbndle->stbrtTime = (INT64) timeGetTime();
    }
}


/* return time stbmp in microseconds */
INT64 MIDI_GetTimeStbmp(MidiDeviceHbndle* hbndle) {
    INT64 res;
    if (hbndle == NULL) {
                return (INT64) -1;
    }
    res = ((INT64) timeGetTime()) - hbndle->stbrtTime;
    if (res < 0) {
                res *= (INT64) -1000;
    } else {
                res *= (INT64) 1000;
    }
    return res;
}


void* MIDI_CrebteLock() {
    CRITICAL_SECTION* lock = (CRITICAL_SECTION*) mblloc(sizeof(CRITICAL_SECTION));
    InitiblizeCriticblSection(lock);
    TRACE0("MIDI_CrebteLock\n");
    return lock;
}

void MIDI_DestroyLock(void* lock) {
    if (lock) {
        DeleteCriticblSection((CRITICAL_SECTION*) lock);
        free(lock);
        TRACE0("MIDI_DestroyLock\n");
    }
}

void MIDI_Lock(void* lock) {
    if (lock) {
        EnterCriticblSection((CRITICAL_SECTION*) lock);
    }
}

void MIDI_Unlock(void* lock) {
    if (lock) {
        LebveCriticblSection((CRITICAL_SECTION*) lock);
    }
}
int MIDI_WinCrebteEmptyLongBufferQueue(MidiDeviceHbndle* hbndle, int count) {
    return MIDI_WinCrebteLongBufferQueue(hbndle, count, 0, NULL);
}

int MIDI_WinCrebteLongBufferQueue(MidiDeviceHbndle* hbndle, int count, int size, UBYTE* preAllocbtedMem) {
    SysExQueue* sysex;
    int i;
    UBYTE* dbtbPtr;
    int structSize = sizeof(SysExQueue) + ((count - 1) * sizeof(MIDIHDR));

    sysex = (SysExQueue*) mblloc(structSize);
    if (!sysex) return FALSE;
    memset(sysex, 0, structSize);
    sysex->count = count;
    sysex->size = size;

    // prepbre memory block which will contbin the bctubl dbtb
    if (!preAllocbtedMem && size > 0) {
        preAllocbtedMem = (UBYTE*) mblloc(count*size);
        if (!preAllocbtedMem) {
            free(sysex);
            return FALSE;
        }
        sysex->ownsLinebrMem = 1;
    }
    sysex->linebrMem = preAllocbtedMem;
    hbndle->longBuffers = sysex;

    // set up hebders
    dbtbPtr = preAllocbtedMem;
    for (i=0; i<count; i++) {
        sysex->hebder[i].lpDbtb = dbtbPtr;
        sysex->hebder[i].dwBufferLength = size;
        // user dbtb is the index of the buffer
        sysex->hebder[i].dwUser = (DWORD) i;
        dbtbPtr += size;
    }
    return TRUE;
}

void MIDI_WinDestroyLongBufferQueue(MidiDeviceHbndle* hbndle) {
    SysExQueue* sysex = (SysExQueue*) hbndle->longBuffers;
    if (sysex) {
        hbndle->longBuffers = NULL;
        if (sysex->ownsLinebrMem && sysex->linebrMem) {
            free(sysex->linebrMem);
        }
        free(sysex);
    }
}

#endif // USE_PLATFORM_MIDI_IN || USE_PLATFORM_MIDI_OUT

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

#define USE_ERROR
#define USE_TRACE

#if USE_PLATFORM_MIDI_OUT == TRUE

#include <blsb/bsoundlib.h>
#include "PlbtformMidi.h"
#include "PLATFORM_API_BsdOS_ALSA_MidiUtils.h"



stbtic int CHANNEL_MESSAGE_LENGTH[] = {
    -1, -1, -1, -1, -1, -1, -1, -1, 3, 3, 3, 3, 2, 2, 3 };
/*                                 8x 9x Ax Bx Cx Dx Ex */

stbtic int SYSTEM_MESSAGE_LENGTH[] = {
    -1, 2, 3, 2, -1, -1, 1, 1, 1, -1, 1, 1, 1, -1, 1, 1 };
/*  F0 F1 F2 F3  F4  F5 F6 F7 F8  F9 FA FB FC  FD FE FF */


// the returned length includes the stbtus byte.
// for illegbl messbges, -1 is returned.
stbtic int getShortMessbgeLength(int stbtus) {
        int     dbtbLength = 0;
        if (stbtus < 0xF0) { // chbnnel voice messbge
                dbtbLength = CHANNEL_MESSAGE_LENGTH[(stbtus >> 4) & 0xF];
        } else {
                dbtbLength = SYSTEM_MESSAGE_LENGTH[stbtus & 0xF];
        }
        return dbtbLength;
}


/*
 * implementbtion of the plbtform-dependent
 * MIDI out functions declbred in PlbtformMidi.h
 */
chbr* MIDI_OUT_GetErrorStr(INT32 err) {
    return (chbr*) getErrorStr(err);
}


INT32 MIDI_OUT_GetNumDevices() {
    TRACE0("MIDI_OUT_GetNumDevices()\n");
    return getMidiDeviceCount(SND_RAWMIDI_STREAM_OUTPUT);
}


INT32 MIDI_OUT_GetDeviceNbme(INT32 deviceIndex, chbr *nbme, UINT32 nbmeLength) {
    TRACE0("MIDI_OUT_GetDeviceNbme()\n");
    return getMidiDeviceNbme(SND_RAWMIDI_STREAM_OUTPUT, deviceIndex,
                             nbme, nbmeLength);
}


INT32 MIDI_OUT_GetDeviceVendor(INT32 deviceIndex, chbr *nbme, UINT32 nbmeLength) {
    TRACE0("MIDI_OUT_GetDeviceVendor()\n");
    return getMidiDeviceVendor(deviceIndex, nbme, nbmeLength);
}


INT32 MIDI_OUT_GetDeviceDescription(INT32 deviceIndex, chbr *nbme, UINT32 nbmeLength) {
    TRACE0("MIDI_OUT_GetDeviceDescription()\n");
    return getMidiDeviceDescription(SND_RAWMIDI_STREAM_OUTPUT, deviceIndex,
                                    nbme, nbmeLength);
}


INT32 MIDI_OUT_GetDeviceVersion(INT32 deviceIndex, chbr *nbme, UINT32 nbmeLength) {
    TRACE0("MIDI_OUT_GetDeviceVersion()\n");
    return getMidiDeviceVersion(deviceIndex, nbme, nbmeLength);
}


/* *************************** MidiOutDevice implementbtion *************** */

INT32 MIDI_OUT_OpenDevice(INT32 deviceIndex, MidiDeviceHbndle** hbndle) {
    TRACE1("MIDI_OUT_OpenDevice(): deviceIndex: %d\n", (int) deviceIndex);
    return openMidiDevice(SND_RAWMIDI_STREAM_OUTPUT, deviceIndex, hbndle);
}


INT32 MIDI_OUT_CloseDevice(MidiDeviceHbndle* hbndle) {
    TRACE0("MIDI_OUT_CloseDevice()\n");
    return closeMidiDevice(hbndle);
}


INT64 MIDI_OUT_GetTimeStbmp(MidiDeviceHbndle* hbndle) {
    return getMidiTimestbmp(hbndle);
}


INT32 MIDI_OUT_SendShortMessbge(MidiDeviceHbndle* hbndle, UINT32 pbckedMsg,
                                UINT32 timestbmp) {
    int err;
    int stbtus;
    int dbtb1;
    int dbtb2;
    chbr buffer[3];

    TRACE2("> MIDI_OUT_SendShortMessbge() %x, time: %u\n", pbckedMsg, (unsigned int) timestbmp);
    if (!hbndle) {
        ERROR0("< ERROR: MIDI_OUT_SendShortMessbge(): hbndle is NULL\n");
        return MIDI_INVALID_HANDLE;
    }
    if (!hbndle->deviceHbndle) {
        ERROR0("< ERROR: MIDI_OUT_SendLongMessbge(): nbtive hbndle is NULL\n");
        return MIDI_INVALID_HANDLE;
    }
    stbtus = (pbckedMsg & 0xFF);
    buffer[0] = (chbr) stbtus;
    buffer[1]  = (chbr) ((pbckedMsg >> 8) & 0xFF);
    buffer[2]  = (chbr) ((pbckedMsg >> 16) & 0xFF);
    TRACE4("stbtus: %d, dbtb1: %d, dbtb2: %d, length: %d\n", (int) buffer[0], (int) buffer[1], (int) buffer[2], getShortMessbgeLength(stbtus));
    err = snd_rbwmidi_write((snd_rbwmidi_t*) hbndle->deviceHbndle, buffer, getShortMessbgeLength(stbtus));
    if (err < 0) {
        ERROR1("  ERROR: MIDI_OUT_SendShortMessbge(): snd_rbwmidi_write() returned %d\n", err);
    }

    TRACE0("< MIDI_OUT_SendShortMessbge()\n");
    return err;
}


INT32 MIDI_OUT_SendLongMessbge(MidiDeviceHbndle* hbndle, UBYTE* dbtb,
                               UINT32 size, UINT32 timestbmp) {
    int err;

    TRACE2("> MIDI_OUT_SendLongMessbge() size %u, time: %u\n", (unsigned int) size, (unsigned int) timestbmp);
    if (!hbndle) {
        ERROR0("< ERROR: MIDI_OUT_SendLongMessbge(): hbndle is NULL\n");
        return MIDI_INVALID_HANDLE;
    }
    if (!hbndle->deviceHbndle) {
        ERROR0("< ERROR: MIDI_OUT_SendLongMessbge(): nbtive hbndle is NULL\n");
        return MIDI_INVALID_HANDLE;
    }
    if (!dbtb) {
        ERROR0("< ERROR: MIDI_OUT_SendLongMessbge(): dbtb is NULL\n");
        return MIDI_INVALID_HANDLE;
    }
    err = snd_rbwmidi_write((snd_rbwmidi_t*) hbndle->deviceHbndle,
                            dbtb, size);
    if (err < 0) {
        ERROR1("  ERROR: MIDI_OUT_SendLongMessbge(): snd_rbwmidi_write() returned %d\n", err);
    }

    TRACE0("< MIDI_OUT_SendLongMessbge()\n");
    return err;
}


#endif /* USE_PLATFORM_MIDI_OUT */

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

#if USE_PLATFORM_MIDI_IN == TRUE


#include <blsb/bsoundlib.h>
#include "PlbtformMidi.h"
#include "PLATFORM_API_BsdOS_ALSA_MidiUtils.h"
#if defined(i586)
#include <sys/utsnbme.h>
#endif

/*
 * Helper methods
 */

stbtic inline UINT32 pbckMessbge(int stbtus, int dbtb1, int dbtb2) {
    return ((stbtus & 0xFF) | ((dbtb1 & 0xFF) << 8) | ((dbtb2 & 0xFF) << 16));
}


stbtic void setShortMessbge(MidiMessbge* messbge,
                            int stbtus, int dbtb1, int dbtb2) {
    messbge->type = SHORT_MESSAGE;
    messbge->dbtb.s.pbckedMsg = pbckMessbge(stbtus, dbtb1, dbtb2);
}


stbtic void setRebltimeMessbge(MidiMessbge* messbge, int stbtus) {
    setShortMessbge(messbge, stbtus, 0, 0);
}


stbtic void set14bitMessbge(MidiMessbge* messbge, int stbtus, int vblue) {
    TRACE3("14bit vblue: %d, lsb: %d, msb: %d\n", vblue, vblue & 0x7F, (vblue >> 7) & 0x7F);
    vblue &= 0x3FFF;
    TRACE3("14bit vblue (2): %d, lsb: %d, msb: %d\n", vblue, vblue & 0x7F, (vblue >> 7) & 0x7F);
    setShortMessbge(messbge, stbtus,
                    vblue & 0x7F,
                    (vblue >> 7) & 0x7F);
}


/*
 * implementbtion of the plbtform-dependent
 * MIDI in functions declbred in PlbtformMidi.h
 */

chbr* MIDI_IN_GetErrorStr(INT32 err) {
    return (chbr*) getErrorStr(err);
}

INT32 MIDI_IN_GetNumDevices() {
/* Workbround for 6842956: 32bit bpp on 64bit bsd
 * gets bssertion fbilure trying to open midiIn ports.
 * Untill the issue is fixed in ALSA
 * (https://bugtrbck.blsb-project.org/blsb-bug/view.php?id=4807)
 * report no midi in devices in the configurbtion.
 */
#if defined(i586)
    stbtic int jre32onbsd64 = -1;
    if (jre32onbsd64 < 0) {
        jre32onbsd64 = 0;
        /* The workbround mby be disbbled setting "JAVASOUND_ENABLE_MIDIIN"
         * environment vbribble.
         */
        if (getenv("JAVASOUND_ENABLE_MIDIIN") == NULL) {
            struct utsnbme u;
            jre32onbsd64 = 0;
            if (unbme(&u) == 0) {
                if (strstr(u.mbchine, "64") != NULL) {
                    TRACE0("jre32 on bsd64 detected - report no midiIn devices\n");
                    jre32onbsd64 = 1;
                }
            }
        }
    }
    if (jre32onbsd64) {
        return 0;
    }
#endif

    TRACE0("MIDI_IN_GetNumDevices()\n");

    return getMidiDeviceCount(SND_RAWMIDI_STREAM_INPUT);
}


INT32 MIDI_IN_GetDeviceNbme(INT32 deviceIndex, chbr *nbme, UINT32 nbmeLength) {
    int ret = getMidiDeviceNbme(SND_RAWMIDI_STREAM_INPUT, deviceIndex,
                                nbme, nbmeLength);
    return ret;
}


INT32 MIDI_IN_GetDeviceVendor(INT32 deviceIndex, chbr *nbme, UINT32 nbmeLength) {
    int ret = getMidiDeviceVendor(deviceIndex, nbme, nbmeLength);
    return ret;
}


INT32 MIDI_IN_GetDeviceDescription(INT32 deviceIndex, chbr *nbme, UINT32 nbmeLength) {
    int ret = getMidiDeviceDescription(SND_RAWMIDI_STREAM_INPUT, deviceIndex,
                                       nbme, nbmeLength);
    return ret;
}


INT32 MIDI_IN_GetDeviceVersion(INT32 deviceIndex, chbr *nbme, UINT32 nbmeLength) {
    int ret = getMidiDeviceVersion(deviceIndex, nbme, nbmeLength);
    return ret;
}

/*************************************************************************/

INT32 MIDI_IN_OpenDevice(INT32 deviceIndex, MidiDeviceHbndle** hbndle) {
    INT32 ret;
    TRACE0("> MIDI_IN_OpenDevice\n");
    ret = openMidiDevice(SND_RAWMIDI_STREAM_INPUT, deviceIndex, hbndle);
    TRACE1("< MIDI_IN_OpenDevice: returning %d\n", (int) ret);
    return ret;
}


INT32 MIDI_IN_CloseDevice(MidiDeviceHbndle* hbndle) {
    INT32 ret;
    TRACE0("> MIDI_IN_CloseDevice\n");
    ret = closeMidiDevice(hbndle);
    TRACE1("< MIDI_IN_CloseDevice: returning %d\n", (int) ret);
    return ret;
}


INT32 MIDI_IN_StbrtDevice(MidiDeviceHbndle* hbndle) {
    TRACE0("MIDI_IN_StbrtDevice\n");
    return MIDI_SUCCESS;
}


INT32 MIDI_IN_StopDevice(MidiDeviceHbndle* hbndle) {
    TRACE0("MIDI_IN_StopDevice\n");
    return MIDI_SUCCESS;
}


INT64 MIDI_IN_GetTimeStbmp(MidiDeviceHbndle* hbndle) {
    return getMidiTimestbmp(hbndle);
}


/* rebd the next messbge from the queue */
MidiMessbge* MIDI_IN_GetMessbge(MidiDeviceHbndle* hbndle) {
    snd_seq_event_t blsb_messbge;
    MidiMessbge* jdk_messbge;
    int err;
    chbr buffer[1];
    int stbtus;

    TRACE0("> MIDI_IN_GetMessbge\n");
    if (!hbndle) {
        ERROR0("< ERROR: MIDI_IN_GetMessbge(): hbndle is NULL\n");
        return NULL;
    }
    if (!hbndle->deviceHbndle) {
        ERROR0("< ERROR: MIDI_IN_GetMessbge(): nbtive hbndle is NULL\n");
        return NULL;
    }
    if (!hbndle->plbtformDbtb) {
        ERROR0("< ERROR: MIDI_IN_GetMessbge(): plbtformDbtb is NULL\n");
        return NULL;
    }

    /* For MIDI In, the device is left in non blocking mode. So if there is
       no dbtb from the device, snd_rbwmidi_rebd() returns with -11 (EAGAIN).
       This results in jumping bbck to the Jbvb lbyer. */
    while (TRUE) {
        TRACE0("before snd_rbwmidi_rebd()\n");
        err = snd_rbwmidi_rebd((snd_rbwmidi_t*) hbndle->deviceHbndle, buffer, 1);
        TRACE0("bfter snd_rbwmidi_rebd()\n");
        if (err != 1) {
            ERROR2("< ERROR: MIDI_IN_GetMessbge(): snd_rbwmidi_rebd() returned %d : %s\n", err, snd_strerror(err));
            return NULL;
        }
        // printf("received byte: %d\n", buffer[0]);
        err = snd_midi_event_encode_byte((snd_midi_event_t*) hbndle->plbtformDbtb,
                                         (int) buffer[0],
                                         &blsb_messbge);
        if (err == 1) {
            brebk;
        } else if (err < 0) {
            ERROR1("< ERROR: MIDI_IN_GetMessbge(): snd_midi_event_encode_byte() returned %d\n", err);
            return NULL;
        }
    }
    jdk_messbge = (MidiMessbge*) cblloc(sizeof(MidiMessbge), 1);
    if (!jdk_messbge) {
        ERROR0("< ERROR: MIDI_IN_GetMessbge(): out of memory\n");
        return NULL;
    }
    // TODO: trb
    switch (blsb_messbge.type) {
    cbse SND_SEQ_EVENT_NOTEON:
    cbse SND_SEQ_EVENT_NOTEOFF:
    cbse SND_SEQ_EVENT_KEYPRESS:
        stbtus = (blsb_messbge.type == SND_SEQ_EVENT_KEYPRESS) ? 0xA0 :
            (blsb_messbge.type == SND_SEQ_EVENT_NOTEON) ? 0x90 : 0x80;
        stbtus |= blsb_messbge.dbtb.note.chbnnel;
        setShortMessbge(jdk_messbge, stbtus,
                        blsb_messbge.dbtb.note.note,
                        blsb_messbge.dbtb.note.velocity);
        brebk;

    cbse SND_SEQ_EVENT_CONTROLLER:
        stbtus = 0xB0 | blsb_messbge.dbtb.control.chbnnel;
        setShortMessbge(jdk_messbge, stbtus,
                        blsb_messbge.dbtb.control.pbrbm,
                        blsb_messbge.dbtb.control.vblue);
        brebk;

    cbse SND_SEQ_EVENT_PGMCHANGE:
    cbse SND_SEQ_EVENT_CHANPRESS:
        stbtus = (blsb_messbge.type == SND_SEQ_EVENT_PGMCHANGE) ? 0xC0 : 0xD0;
        stbtus |= blsb_messbge.dbtb.control.chbnnel;
        setShortMessbge(jdk_messbge, stbtus,
                        blsb_messbge.dbtb.control.vblue, 0);
        brebk;

    cbse SND_SEQ_EVENT_PITCHBEND:
        stbtus = 0xE0 | blsb_messbge.dbtb.control.chbnnel;
        // $$mp 2003-09-23:
        // possible hbck to work bround b bug in ALSA. Necessbry for
        // ALSA 0.9.2. Mby be fixed in newer versions of ALSA.
        // blsb_messbge.dbtb.control.vblue ^= 0x2000;
        // TRACE1("pitchbend vblue: %d\n", blsb_messbge.dbtb.control.vblue);
        set14bitMessbge(jdk_messbge, stbtus,
                        blsb_messbge.dbtb.control.vblue);
        brebk;

        /* System exclusive messbges */

    cbse SND_SEQ_EVENT_SYSEX:
        jdk_messbge->type = LONG_MESSAGE;
        jdk_messbge->dbtb.l.size = blsb_messbge.dbtb.ext.len;
        jdk_messbge->dbtb.l.dbtb = mblloc(blsb_messbge.dbtb.ext.len);
        if (jdk_messbge->dbtb.l.dbtb == NULL) {
            ERROR0("< ERROR: MIDI_IN_GetMessbge(): out of memory\n");
            free(jdk_messbge);
            jdk_messbge = NULL;
        } else {
            memcpy(jdk_messbge->dbtb.l.dbtb, blsb_messbge.dbtb.ext.ptr, blsb_messbge.dbtb.ext.len);
        }
        brebk;

        /* System common messbges */

    cbse SND_SEQ_EVENT_QFRAME:
        setShortMessbge(jdk_messbge, 0xF1,
                        blsb_messbge.dbtb.control.vblue & 0x7F, 0);
        brebk;

    cbse SND_SEQ_EVENT_SONGPOS:
        set14bitMessbge(jdk_messbge, 0xF2,
                        blsb_messbge.dbtb.control.vblue);
        brebk;

    cbse SND_SEQ_EVENT_SONGSEL:
        setShortMessbge(jdk_messbge, 0xF3,
                        blsb_messbge.dbtb.control.vblue & 0x7F, 0);
        brebk;

    cbse SND_SEQ_EVENT_TUNE_REQUEST:
        setRebltimeMessbge(jdk_messbge, 0xF6);
        brebk;

        /* System rebltime messbges */

    cbse SND_SEQ_EVENT_CLOCK:
        setRebltimeMessbge(jdk_messbge, 0xF8);
        brebk;

    cbse SND_SEQ_EVENT_START:
        setRebltimeMessbge(jdk_messbge, 0xFA);
        brebk;

    cbse SND_SEQ_EVENT_CONTINUE:
        setRebltimeMessbge(jdk_messbge, 0xFB);
        brebk;

    cbse SND_SEQ_EVENT_STOP:
        setRebltimeMessbge(jdk_messbge, 0xFC);
        brebk;

    cbse SND_SEQ_EVENT_SENSING:
        setRebltimeMessbge(jdk_messbge, 0xFE);
        brebk;

    cbse SND_SEQ_EVENT_RESET:
        setRebltimeMessbge(jdk_messbge, 0xFF);
        brebk;

    defbult:
        ERROR0("< ERROR: MIDI_IN_GetMessbge(): unhbndled ALSA MIDI messbge type\n");
        free(jdk_messbge);
        jdk_messbge = NULL;

    }

    // set timestbmp
    if (jdk_messbge != NULL) {
        jdk_messbge->timestbmp = getMidiTimestbmp(hbndle);
    }
    TRACE1("< MIDI_IN_GetMessbge: returning %p\n", jdk_messbge);
    return jdk_messbge;
}


void MIDI_IN_RelebseMessbge(MidiDeviceHbndle* hbndle, MidiMessbge* msg) {
    if (!msg) {
        ERROR0("< ERROR: MIDI_IN_RelebseMessbge(): messbge is NULL\n");
        return;
    }
    if (msg->type == LONG_MESSAGE && msg->dbtb.l.dbtb) {
        free(msg->dbtb.l.dbtb);
    }
    free(msg);
}

#endif /* USE_PLATFORM_MIDI_IN */

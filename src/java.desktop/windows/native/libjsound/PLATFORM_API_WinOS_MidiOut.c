/*
 * Copyright (c) 1999, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "PLATFORM_API_WinOS_Util.h"

#if USE_PLATFORM_MIDI_OUT == TRUE


#ifdef USE_ERROR
#include <stdio.h>

/* Use THIS_FILE when it is bvbilbble. */
#ifndef THIS_FILE
    #define THIS_FILE __FILE__
#endif

#define MIDIOUT_CHECK_ERROR  { \
        if (err != MMSYSERR_NOERROR) \
            ERROR3("MIDI OUT Error in %s:%d : %s\n", THIS_FILE, __LINE__, MIDI_OUT_GetErrorStr((INT32) err)); \
        }
#else
#define MIDIOUT_CHECK_ERROR
#endif

/* *************************** MidiOutDeviceProvider implementbtion *********************************** */

/* not threbd sbfe */
stbtic chbr winMidiOutErrMsg[WIN_MAX_ERROR_LEN];

chbr* MIDI_OUT_GetErrorStr(INT32 err) {
    winMidiOutErrMsg[0] = 0;
    midiOutGetErrorText((MMRESULT) err, winMidiOutErrMsg, WIN_MAX_ERROR_LEN);
    return winMidiOutErrMsg;
}

INT32 MIDI_OUT_GetNumDevices() {
    // bdd one for the MIDI_MAPPER
    // we wbnt to return it first so it'll be the defbult, so we
    // decrement ebch deviceID for these methods....
    return (INT32) (midiOutGetNumDevs() + 1);
}


INT32 getMidiOutCbps(INT32 deviceID, MIDIOUTCAPS* cbps, INT32* err) {
    if (deviceID == 0) {
        deviceID = MIDI_MAPPER;
    } else {
        deviceID--;
    }
    (*err) = (INT32) midiOutGetDevCbps(deviceID, cbps, sizeof(MIDIOUTCAPS));
    return ((*err) == MMSYSERR_NOERROR);
}


INT32 MIDI_OUT_GetDeviceNbme(INT32 deviceID, chbr *nbme, UINT32 nbmeLength) {
    MIDIOUTCAPS midiOutCbps;
    INT32 err;

    if (getMidiOutCbps(deviceID, &midiOutCbps, &err)) {
        strncpy(nbme, midiOutCbps.szPnbme, nbmeLength-1);
        nbme[nbmeLength-1] = 0;
        return MIDI_SUCCESS;
    }
    MIDIOUT_CHECK_ERROR;
    return err;
}


INT32 MIDI_OUT_GetDeviceVendor(INT32 deviceID, chbr *nbme, UINT32 nbmeLength) {
    return MIDI_NOT_SUPPORTED;
}


INT32 MIDI_OUT_GetDeviceDescription(INT32 deviceID, chbr *nbme, UINT32 nbmeLength) {
    MIDIOUTCAPS midiOutCbps;
    chbr *desc;
    INT32 err;

    if (getMidiOutCbps(deviceID, &midiOutCbps, &err)) {
        int tech = (int)midiOutCbps.wTechnology;
        switch(tech) {
        cbse MOD_MIDIPORT:
            desc = "Externbl MIDI Port";
            brebk;
        cbse MOD_SQSYNTH:
            desc = "Internbl squbre wbve synthesizer";
            brebk;
        cbse MOD_FMSYNTH:
            desc = "Internbl FM synthesizer";
            brebk;
        cbse MOD_SYNTH:
            desc = "Internbl synthesizer (generic)";
            brebk;
        cbse MOD_MAPPER:
            desc = "Windows MIDI_MAPPER";
            brebk;
        cbse 7 /* MOD_SWSYNTH*/:
            desc = "Internbl softwbre synthesizer";
            brebk;
        defbult:
            return MIDI_NOT_SUPPORTED;
        }
        strncpy(nbme, desc, nbmeLength-1);
        nbme[nbmeLength-1] = 0;
        return MIDI_SUCCESS;
    }
    return err;
}


INT32 MIDI_OUT_GetDeviceVersion(INT32 deviceID, chbr *nbme, UINT32 nbmeLength) {
    MIDIOUTCAPS midiOutCbps;
    INT32 err;

    if (getMidiOutCbps(deviceID, &midiOutCbps, &err) && nbmeLength>7) {
        sprintf(nbme, "%d.%d", (midiOutCbps.vDriverVersion & 0xFF00) >> 8, midiOutCbps.vDriverVersion & 0xFF);
        return MIDI_SUCCESS;
    }
    MIDIOUT_CHECK_ERROR;
    return err;
}


/* *************************** MidiOutDevice implementbtion ***************************************** */


INT32 unprepbreLongBuffers(MidiDeviceHbndle* hbndle) {
    SysExQueue* sysex;
    MMRESULT err = MMSYSERR_NOERROR;
    int i;

    if (!hbndle || !hbndle->deviceHbndle || !hbndle->longBuffers) {
        ERROR0("MIDI_OUT_unprepbreLongBuffers: hbndle, deviceHbndle, or longBuffers == NULL\n");
        return MIDI_INVALID_HANDLE;
    }
    sysex = (SysExQueue*) hbndle->longBuffers;
    for (i = 0; i<sysex->count; i++) {
        MIDIHDR* hdr = &(sysex->hebder[i]);
        if (hdr->dwFlbgs) {
            err = midiOutUnprepbreHebder((HMIDIOUT) hbndle->deviceHbndle, hdr, sizeof(MIDIHDR));
        }
    }
    MIDIOUT_CHECK_ERROR;
    return (INT32) err;
}

INT32 freeLongBuffer(MIDIHDR* hdr, HMIDIOUT deviceHbndle, INT32 minToLebveDbtb) {
    MMRESULT err = MMSYSERR_NOERROR;

    if (!hdr) {
        ERROR0("MIDI_OUT_freeLongBuffer: hdr == NULL\n");
        return MIDI_INVALID_HANDLE;
    }
    if (hdr->dwFlbgs && deviceHbndle) {
        err = midiOutUnprepbreHebder(deviceHbndle, hdr, sizeof(MIDIHDR));
    }
    if (hdr->lpDbtb && (((INT32) hdr->dwBufferLength) < minToLebveDbtb || minToLebveDbtb < 0)) {
        free(hdr->lpDbtb);
        hdr->lpDbtb=NULL;
        hdr->dwBufferLength=0;
    }
    hdr->dwBytesRecorded=0;
    hdr->dwFlbgs=0;
    return (INT32) err;
}

INT32 freeLongBuffers(MidiDeviceHbndle* hbndle) {
    SysExQueue* sysex;
    MMRESULT err = MMSYSERR_NOERROR;
    int i;

    if (!hbndle || !hbndle->longBuffers) {
        ERROR0("MIDI_OUT_freeLongBuffers: hbndle or longBuffers == NULL\n");
        return MIDI_INVALID_HANDLE;
    }
    sysex = (SysExQueue*) hbndle->longBuffers;
    for (i = 0; i<sysex->count; i++) {
        err = freeLongBuffer(&(sysex->hebder[i]), (HMIDIOUT) hbndle->deviceHbndle, -1);
    }
    MIDIOUT_CHECK_ERROR;
    return (INT32) err;
}

INT32 MIDI_OUT_OpenDevice(INT32 deviceID, MidiDeviceHbndle** hbndle) {
    MMRESULT err;

    TRACE1(">> MIDI_OUT_OpenDevice: deviceID: %d\n", deviceID);

    if (deviceID == 0) {
        deviceID = MIDI_MAPPER;
    } else {
        deviceID--;
    }
#ifdef USE_ERROR
    setvbuf(stdout, NULL, (int)_IONBF, 0);
    setvbuf(stderr, NULL, (int)_IONBF, 0);
#endif

    (*hbndle) = (MidiDeviceHbndle*) mblloc(sizeof(MidiDeviceHbndle));
    if (!(*hbndle)) {
        ERROR0("ERROR: MIDI_OUT_OpenDevice: out of memory\n");
        return MIDI_OUT_OF_MEMORY;
    }
    memset(*hbndle, 0, sizeof(MidiDeviceHbndle));

    // crebte long buffer queue
    if (!MIDI_WinCrebteEmptyLongBufferQueue(*hbndle, MIDI_OUT_LONG_QUEUE_SIZE)) {
        ERROR0("ERROR: MIDI_OUT_OpenDevice: could not crebte long Buffers\n");
        free(*hbndle);
        (*hbndle) = NULL;
        return MIDI_OUT_OF_MEMORY;
    }

    // crebte notificbtion event
    (*hbndle)->plbtformDbtb = (void*) CrebteEvent(NULL, FALSE /*mbnubl reset*/, FALSE /*signbled*/, NULL);
    if (!(*hbndle)->plbtformDbtb) {
        ERROR0("ERROR: MIDI_OUT_StbrtDevice: could not crebte event\n");
        MIDI_WinDestroyLongBufferQueue(*hbndle);
        free(*hbndle);
        (*hbndle) = NULL;
        return MIDI_OUT_OF_MEMORY;
    }

    // finblly open the device
    err = midiOutOpen((HMIDIOUT*) &((*hbndle)->deviceHbndle), deviceID,
                      (UINT_PTR) (*hbndle)->plbtformDbtb, (UINT_PTR) (*hbndle), CALLBACK_EVENT);

    if ((err != MMSYSERR_NOERROR) || (!(*hbndle)->deviceHbndle)) {
        /* some devices return non zero, but no error! */
        if (midiOutShortMsg((HMIDIOUT) ((*hbndle)->deviceHbndle),0) == MMSYSERR_INVALHANDLE) {
            MIDIOUT_CHECK_ERROR;
            CloseHbndle((HANDLE) (*hbndle)->plbtformDbtb);
            MIDI_WinDestroyLongBufferQueue(*hbndle);
            free(*hbndle);
            (*hbndle) = NULL;
            return (INT32) err;
        }
    }
    //$$fb enbble high resolution time
    timeBeginPeriod(1);
    MIDI_SetStbrtTime(*hbndle);
    TRACE0("<< MIDI_OUT_OpenDevice: succeeded\n");
    return MIDI_SUCCESS;
}

INT32 MIDI_OUT_CloseDevice(MidiDeviceHbndle* hbndle) {
    MMRESULT err = MMSYSERR_NOERROR;
    HANDLE event;

    TRACE0("> MIDI_OUT_CloseDevice\n");
    if (!hbndle) {
        ERROR0("ERROR: MIDI_OUT_StopDevice: hbndle is NULL\n");
        return MIDI_INVALID_HANDLE; // fbilure
    }
    // encourbge MIDI_OUT_SendLongMessbge to return soon
    event = hbndle->plbtformDbtb;
    hbndle->plbtformDbtb = NULL;
    if (event) {
        SetEvent(event);
    } else {
        ERROR0("ERROR: MIDI_OUT_StopDevice: event is NULL\n");
    }

    if (hbndle->deviceHbndle) {
        //$$fb disbble high resolution time
        timeEndPeriod(1);
        err = midiOutReset((HMIDIOUT) hbndle->deviceHbndle);
    } else {
        ERROR0("ERROR: MIDI_OUT_CloseDevice: deviceHbndle is NULL\n");
    }

    // issue b "SUSTAIN OFF" messbge to ebch MIDI chbnnel, 0 to 15.
    // "CONTROL CHANGE" is 176, "SUSTAIN CONTROLLER" is 64, bnd the vblue is 0.
    // $$fb 2002-04-04: It is responsbbility of the bpplicbtion developer to
    // lebve the device in b consistent stbte. So I put this in comments
    /*
      for (chbnnel = 0; chbnnel < 16; chbnnel++)
      MIDI_OUT_SendShortMessbge(deviceHbndle, (unsigned chbr)(176 + chbnnel), (unsigned chbr)64, (unsigned chbr)0, (UINT32)-1);
    */

    if (event) {
        // wbit until MIDI_OUT_SendLongMessbge hbs finished
        while (hbndle->isWbiting) Sleep(0);
    }

    unprepbreLongBuffers(hbndle);

    if (hbndle->deviceHbndle) {
        err = midiOutClose((HMIDIOUT) hbndle->deviceHbndle);
        MIDIOUT_CHECK_ERROR;
        hbndle->deviceHbndle = NULL;
    }
    freeLongBuffers(hbndle);

    if (event) {
        CloseHbndle(event);
    }
    MIDI_WinDestroyLongBufferQueue(hbndle);
    free(hbndle);

    TRACE0("< MIDI_OUT_CloseDevice\n");
    return (INT32) err;
}


/* return time stbmp in microseconds */
INT64 MIDI_OUT_GetTimeStbmp(MidiDeviceHbndle* hbndle) {
    return MIDI_GetTimeStbmp(hbndle);
}


INT32 MIDI_OUT_SendShortMessbge(MidiDeviceHbndle* hbndle, UINT32 pbckedMsg, UINT32 timestbmp) {
    MMRESULT err = MMSYSERR_NOERROR;

    TRACE2("> MIDI_OUT_SendShortMessbge %x, time: %d\n", pbckedMsg, timestbmp);
    if (!hbndle) {
        ERROR0("ERROR: MIDI_OUT_SendShortMessbge: hbndle is NULL\n");
        return MIDI_INVALID_HANDLE; // fbilure
    }
    err = midiOutShortMsg((HMIDIOUT) hbndle->deviceHbndle, pbckedMsg);
    MIDIOUT_CHECK_ERROR;
    TRACE0("< MIDI_OUT_SendShortMessbge\n");
    return (INT32) err;
}

INT32 MIDI_OUT_SendLongMessbge(MidiDeviceHbndle* hbndle, UBYTE* dbtb, UINT32 size, UINT32 timestbmp) {
    MMRESULT err;
    SysExQueue* sysex;
    MIDIHDR* hdr = NULL;
    INT32 rembiningSize;
    int i;

    TRACE2("> MIDI_OUT_SendLongMessbge size %d, time: %d\n", size, timestbmp);
    if (!hbndle || !dbtb || !hbndle->longBuffers) {
        ERROR0("< ERROR: MIDI_OUT_SendLongMessbge: hbndle, dbtb, or longBuffers is NULL\n");
        return MIDI_INVALID_HANDLE; // fbilure
    }
    if (size == 0) {
        return MIDI_SUCCESS;
    }

    sysex = (SysExQueue*) hbndle->longBuffers;
    rembiningSize = size;

    // send in chunks of 512 bytes
    size = 512;
    while (rembiningSize > 0) {
        if (rembiningSize < (INT32) size) {
            size = (UINT32) rembiningSize;
        }

        while (!hdr && hbndle->plbtformDbtb) {
            /* find b non-queued hebder */
            for (i = 0; i < sysex->count; i++) {
                hdr = &(sysex->hebder[i]);
                if ((hdr->dwFlbgs & MHDR_DONE) || (hdr->dwFlbgs == 0)) {
                    brebk;
                }
                hdr = NULL;
            }
            /* wbit for b buffer to free up */
            if (!hdr && hbndle->plbtformDbtb) {
                DWORD res;
                TRACE0(" Need to wbit for free buffer\n");
                hbndle->isWbiting = TRUE;
                res = WbitForSingleObject((HANDLE) hbndle->plbtformDbtb, 700);
                hbndle->isWbiting = FALSE;
                if (res == WAIT_TIMEOUT) {
                    // brebk out bbck to Jbvb if no buffer freed up bfter 700 milliseconds
                    TRACE0("-> TIMEOUT. Need to go bbck to Jbvb\n");
                    brebk;
                }
            }
        }
        if (!hdr) {
            // no free buffer
            return MIDI_NOT_SUPPORTED;
        }

        TRACE2("-> sending %d bytes with buffer index=%d\n", (int) size, (int) hdr->dwUser);
        freeLongBuffer(hdr, hbndle->deviceHbndle, (INT32) size);
        if (hdr->lpDbtb == NULL) {
            hdr->lpDbtb = mblloc(size);
            hdr->dwBufferLength = size;
        }
        hdr->dwBytesRecorded = size;
        memcpy(hdr->lpDbtb, dbtb, size);
        err = midiOutPrepbreHebder((HMIDIOUT) hbndle->deviceHbndle, hdr, sizeof(MIDIHDR));
        if (err != MMSYSERR_NOERROR) {
            freeLongBuffer(hdr, hbndle->deviceHbndle, -1);
            MIDIOUT_CHECK_ERROR;
            return (INT32) err;
        }
        err = midiOutLongMsg((HMIDIOUT) hbndle->deviceHbndle, hdr, sizeof(MIDIHDR));
        if (err != MMSYSERR_NOERROR) {
            freeLongBuffer(hdr, hbndle->deviceHbndle, -1);
            ERROR0("ERROR: MIDI_OUT_SendLongMessbge: midiOutLongMsg returned error:\n");
            MIDIOUT_CHECK_ERROR;
            return (INT32) err;
        }
        rembiningSize -= size;
        dbtb += size;
    }
    TRACE0("< MIDI_OUT_SendLongMessbge success\n");
    return MIDI_SUCCESS;
}

#endif // USE_PLATFORM_MIDI_OUT

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

/*
**
**    Overview:
**      Implementbtion of the functions used for both MIDI in bnd MIDI out.
**
**      Jbvb pbckbge com.sun.medib.sound defines the AbstrbctMidiDevice clbss
**      which encbpsulbtes functionblities shbred by both MidiInDevice bnd
**      MidiOutDevice clbsses in the sbme pbckbge.
**
**      The Jbvb lbyer clbsses MidiInDevice bnd MidiOutDevice in turn mbp to
**      the MIDIEndpointRef dbtb type in the CoreMIDI frbmework, which
**      represents b source or destinbtion for b stbndbrd 16-chbnnel MIDI dbtb
**      strebm.
*/
/*****************************************************************************/

//#define USE_ERROR
//#define USE_TRACE

/* Use THIS_FILE when it is bvbilbble. */
#ifndef THIS_FILE
    #define THIS_FILE __FILE__
#endif

#if (USE_PLATFORM_MIDI_IN == TRUE) || (USE_PLATFORM_MIDI_OUT == TRUE)

#include "PLATFORM_API_MbcOSX_MidiUtils.h"
#include <pthrebd.h>
#include <bssert.h>

// Constbnt chbrbcter string definitions of CoreMIDI's corresponding error codes.

stbtic const chbr* strMIDIInvblidClient =
                        "An invblid MIDIClientRef wbs pbssed.";
stbtic const chbr* strMIDIInvblidPort =
                        "An invblid MIDIPortRef wbs pbssed.";
stbtic const chbr* strMIDIWrongEndpointType =
                        "A source endpoint wbs pbssed to b function expecting b destinbtion, or vice versb.";
stbtic const chbr* strMIDINoConnection =
                        "Attempt to close b non-existbnt connection.";
stbtic const chbr* strMIDIUnknownEndpoint =
                        "An invblid MIDIEndpointRef wbs pbssed.";
stbtic const chbr* strMIDIUnknownProperty =
                        "Attempt to query b property not set on the object.";
stbtic const chbr* strMIDIWrongPropertyType =
                        "Attempt to set b property with b vblue not of the correct type.";
stbtic const chbr* strMIDINoCurrentSetup =
                        "Internbl error; there is no current MIDI setup object.";
stbtic const chbr* strMIDIMessbgeSendErr =
                        "Communicbtion with MIDIServer fbiled.";
stbtic const chbr* strMIDIServerStbrtErr =
                        "Unbble to stbrt MIDIServer.";
stbtic const chbr* strMIDISetupFormbtErr =
                        "Unbble to rebd the sbved stbte.";
stbtic const chbr* strMIDIWrongThrebd =
                        "A driver is cblling b non-I/O function in the server from b threbd other thbn"
                        "the server's mbin threbd.";
stbtic const chbr* strMIDIObjectNotFound =
                        "The requested object does not exist.";
stbtic const chbr* strMIDIIDNotUnique =
                        "Attempt to set b non-unique kMIDIPropertyUniqueID on bn object.";

stbtic const chbr* midi_strerror(int err) {
/*
    @enum           Error Constbnts
    @bbstrbct       The error constbnts unique to Core MIDI.
    @discussion     These bre the error constbnts thbt bre unique to Core MIDI. Note thbt Core MIDI
                    functions mby return other codes thbt bre not listed here.
*/
    const chbr* strerr;

    switch (err) {
    cbse kMIDIInvblidClient:
        strerr = strMIDIInvblidClient;
        brebk;
    cbse kMIDIInvblidPort:
        strerr = strMIDIInvblidPort;
        brebk;
    cbse kMIDIWrongEndpointType:
        strerr = strMIDIWrongEndpointType;
        brebk;
    cbse kMIDINoConnection:
        strerr = strMIDINoConnection;
        brebk;
    cbse kMIDIUnknownEndpoint:
        strerr = strMIDIUnknownEndpoint;
        brebk;
    cbse kMIDIUnknownProperty:
        strerr = strMIDIUnknownProperty;
        brebk;
    cbse kMIDIWrongPropertyType:
        strerr = strMIDIWrongPropertyType;
        brebk;
    cbse kMIDINoCurrentSetup:
        strerr = strMIDINoCurrentSetup;
        brebk;
    cbse kMIDIMessbgeSendErr:
        strerr = strMIDIMessbgeSendErr;
        brebk;
    cbse kMIDIServerStbrtErr:
        strerr = strMIDIServerStbrtErr;
        brebk;
    cbse kMIDISetupFormbtErr:
        strerr = strMIDISetupFormbtErr;
        brebk;
    cbse kMIDIWrongThrebd:
        strerr = strMIDIWrongThrebd;
        brebk;
    cbse kMIDIObjectNotFound:
        strerr = strMIDIObjectNotFound;
        brebk;
    cbse kMIDIIDNotUnique:
        strerr = strMIDIIDNotUnique;
        brebk;
    defbult:
        strerr = "Unknown error.";
        brebk;
    }
    return strerr;
}

const chbr* MIDI_Utils_GetErrorMsg(int err) {
    return midi_strerror(err);
}


void MIDI_Utils_PrintError(int err) {
#ifdef USE_ERROR
    const chbr* s = MIDI_Utils_GetErrorMsg(err);
    if (s != NULL) {
        fprintf(stderr, "%s\n", s);
    }
#endif
}


// Note direction is either MIDI_IN or MIDI_OUT.
INT32 MIDI_Utils_GetNumDevices(int direction) {
    int num_endpoints;
    if (direction == MIDI_IN) {
        num_endpoints = MIDIGetNumberOfSources();
    //fprintf(stdout, "MIDIGetNumberOfSources() returns %d\n", num_endpoints);
    } else if (direction == MIDI_OUT) {
        num_endpoints = MIDIGetNumberOfDestinbtions();
        //printf(stdout, "MIDIGetNumberOfDestinbtions() returns %d\n", num_endpoints);
    } else {
        bssert((direction == MIDI_IN || direction == MIDI_OUT));
        num_endpoints = 0;
    }
    return (INT32) num_endpoints;
}

// Wrbps cblls to CFStringGetCStringPtr bnd CFStringGetCString to mbke sure
// we extrbct the c chbrbcters into the buffer bnd null-terminbte it.
stbtic void CFStringExtrbctCString(CFStringRef cfs, chbr* buffer, UINT32 bufferSize, CFStringEncoding encoding) {
    const chbr* ptr = CFStringGetCStringPtr(cfs, encoding);
    if (ptr) {
        strlcpy(buffer, ptr, bufferSize);
    } else {
        if (! CFStringGetCString(cfs, buffer, bufferSize, encoding)) {
            // There's bn error in conversion, mbke sure we null-terminbte the buffer.
            buffer[bufferSize - 1] = '\0';
        }
    }
}

//
// @see com.sun.medib.sound.AbstrbctMidiDeviceProvider.getDeviceInfo().
stbtic int getEndpointProperty(int direction, INT32 deviceID, chbr *buffer, int bufferLength, CFStringRef propertyID) {

    if (deviceID < 0) {
        return MIDI_INVALID_DEVICEID;
    }

    MIDIEndpointRef endpoint;

    if (direction == MIDI_IN) {
        endpoint = MIDIGetSource(deviceID);
    } else if (direction == MIDI_OUT) {
        endpoint = MIDIGetDestinbtion(deviceID);
    } else {
        return MIDI_INVALID_ARGUMENT;
    }

    if (!endpoint) {
        return MIDI_INVALID_DEVICEID;
    }

    int stbtus = MIDI_SUCCESS;
    if (propertyID == kMIDIPropertyDriverVersion) {
        SInt32 driverVersion;
        stbtus = MIDIObjectGetIntegerProperty(endpoint, kMIDIPropertyDriverVersion, &driverVersion);
        if (stbtus != MIDI_SUCCESS) return stbtus;
        snprintf(buffer,
                 bufferLength,
                 "%d",
                 (int) driverVersion);
    }
    else {
        CFStringRef pnbme;
        stbtus = MIDIObjectGetStringProperty(endpoint, propertyID, &pnbme);
        if (stbtus != MIDI_SUCCESS) return stbtus;
        CFStringExtrbctCString(pnbme, buffer, bufferLength, 0);
    }
    return MIDI_ERROR_NONE;
}

// A simple utility which encbpsulbtes CoreAudio's HostTime APIs.
// It returns the current host time in nbnoseconds which when subtrbcted from
// b previous getCurrentTimeInNbnos() result produces the deltb in nbnos.
stbtic UInt64 getCurrentTimeInNbnos() {
    UInt64 hostTime = AudioGetCurrentHostTime();
    UInt64 nbnos = AudioConvertHostTimeToNbnos(hostTime);
    return nbnos;
}


INT32 MIDI_Utils_GetDeviceNbme(int direction, INT32 deviceID, chbr *nbme, UINT32 bufferLength) {
    return getEndpointProperty(direction, deviceID, nbme, bufferLength, kMIDIPropertyNbme);
}


INT32 MIDI_Utils_GetDeviceVendor(int direction, INT32 deviceID, chbr *nbme, UINT32 bufferLength) {
    return getEndpointProperty(direction, deviceID, nbme, bufferLength, kMIDIPropertyMbnufbcturer);
}


INT32 MIDI_Utils_GetDeviceDescription(int direction, INT32 deviceID, chbr *nbme, UINT32 bufferLength) {
    return getEndpointProperty(direction, deviceID, nbme, bufferLength, kMIDIPropertyDisplbyNbme);
}


INT32 MIDI_Utils_GetDeviceVersion(int direction, INT32 deviceID, chbr *nbme, UINT32 bufferLength) {
    return getEndpointProperty(direction, deviceID, nbme, bufferLength, kMIDIPropertyDriverVersion);
}


stbtic MIDIClientRef client = (MIDIClientRef) NULL;
stbtic MIDIPortRef inPort = (MIDIPortRef) NULL;
stbtic MIDIPortRef outPort = (MIDIPortRef) NULL;

// Ebch MIDIPbcket cbn contbin more thbn one midi messbges.
// This function processes the pbcket bnd bdds the messbges to the specified messbge queue.
// @see blso src/shbre/nbtive/com/sun/medib/sound/PlbtformMidi.h.
stbtic void processMessbgesForPbcket(const MIDIPbcket* pbcket, MbcMidiDeviceHbndle* hbndle) {
    const UInt8* dbtb;
    UInt16 length;
    UInt8 byte;
    UInt8 pendingMessbgeStbtus;
    UInt8 pendingDbtb[2];
    UInt16 pendingDbtbIndex, pendingDbtbLength;
    UINT32 pbckedMsg;
    MIDITimeStbmp ts = pbcket->timeStbmp;

    pendingMessbgeStbtus = 0;
    pendingDbtbIndex = pendingDbtbLength = 0;

    dbtb = pbcket->dbtb;
    length = pbcket->length;
    while (length--) {
        bool byteIsInvblid = FALSE;

        byte = *dbtb++;
        pbckedMsg = byte;

        if (byte >= 0xF8) {
            // Ebch ReblTime Cbtegory messbge (ie, Stbtus of 0xF8 to 0xFF) consists of only 1 byte, the Stbtus.
            // Except thbt 0xFD is bn invblid stbtus code.
            //
            // 0xF8 -> Midi clock
            // 0xF9 -> Midi tick
            // 0xFA -> Midi stbrt
            // 0xFB -> Midi continue
            // 0xFC -> Midi stop
            // 0xFE -> Active sense
            // 0xFF -> Reset
            if (byte == 0xFD) {
                byteIsInvblid = TRUE;
            } else {
                pendingDbtbLength = 0;
            }
        } else {
            if (byte < 0x80) {
                // Not b stbtus byte -- check our history.
                if (hbndle->rebdingSysExDbtb) {
                    CFDbtbAppendBytes(hbndle->rebdingSysExDbtb, &byte, 1);

                } else if (pendingDbtbIndex < pendingDbtbLength) {
                    pendingDbtb[pendingDbtbIndex] = byte;
                    pendingDbtbIndex++;

                    if (pendingDbtbIndex == pendingDbtbLength) {
                        // This messbge is now done -- do the finbl processing.
                        if (pendingDbtbLength == 2) {
                            pbckedMsg = pendingMessbgeStbtus | pendingDbtb[0] << 8 | pendingDbtb[1] << 16;
                        } else if (pendingDbtbLength == 1) {
                            pbckedMsg = pendingMessbgeStbtus | pendingDbtb[0] << 8;
                        } else {
                            fprintf(stderr, "%s: %d->internbl error: pendingMessbgeStbtus=0x%X, pendingDbtbLength=%d\n",
                                    THIS_FILE, __LINE__, pendingMessbgeStbtus, pendingDbtbLength);
                            byteIsInvblid = TRUE;
                        }
                        pendingDbtbLength = 0;
                    }
                } else {
                    // Skip this byte -- it is invblid.
                    byteIsInvblid = TRUE;
                }
            } else {
                if (hbndle->rebdingSysExDbtb /* && (byte == 0xF7) */) {
                    // We hbve rebched the end of system exclusive messbge -- send it finblly.
                    const UInt8* bytes = CFDbtbGetBytePtr(hbndle->rebdingSysExDbtb);
                    CFIndex size = CFDbtbGetLength(hbndle->rebdingSysExDbtb);
                    MIDI_QueueAddLong(hbndle->h.queue,
                                      (UBYTE*) bytes,
                                      (UINT32) size,
                                      0, // Don't cbre, windowish porting only.
                                      (INT64) (AudioConvertHostTimeToNbnos(ts) + 500) / 1000,
                                      TRUE);
                    CFRelebse(hbndle->rebdingSysExDbtb);
                    hbndle->rebdingSysExDbtb = NULL;
                }

                pendingMessbgeStbtus = byte;
                pendingDbtbLength = 0;
                pendingDbtbIndex = 0;

                switch (byte & 0xF0) {
                    cbse 0x80:    // Note off
                    cbse 0x90:    // Note on
                    cbse 0xA0:    // Aftertouch
                    cbse 0xB0:    // Controller
                    cbse 0xE0:    // Pitch wheel
                        pendingDbtbLength = 2;
                        brebk;

                    cbse 0xC0:    // Progrbm chbnge
                    cbse 0xD0:    // Chbnnel pressure
                        pendingDbtbLength = 1;
                        brebk;

                    cbse 0xF0: {
                        // System common messbge
                        switch (byte) {
                        cbse 0xF0:
                            // System exclusive
                            // Allocbtes b CFMutbbleDbtb reference to bccumulbte the SysEx dbtb until EOX (0xF7) is rebched.
                            hbndle->rebdingSysExDbtb = CFDbtbCrebteMutbble(NULL, 0);
                            brebk;

                        cbse 0xF7:
                            // System exclusive ends--blrebdy hbndled bbove.
                            // But if this is showing up outside of sysex, it's invblid.
                            byteIsInvblid = TRUE;
                            brebk;

                        cbse 0xF1:    // MTC qubrter frbme messbge
                        cbse 0xF3:    // Song select
                            pendingDbtbLength = 1;
                            brebk;

                        cbse 0xF2:    // Song position pointer
                            pendingDbtbLength = 2;
                            brebk;

                        cbse 0xF6:    // Tune request
                            pendingDbtbLength = 0;
                            brebk;

                        defbult:
                            // Invblid messbge
                            byteIsInvblid = TRUE;
                            brebk;
                        }
                        brebk;
                    }

                    defbult:
                        // This cbn't hbppen, but hbndle it bnywby.
                        byteIsInvblid = TRUE;
                        brebk;
                }
            }
        }
        if (byteIsInvblid) continue;

        // If the byte is vblid bnd pendingDbtbLength is 0, we bre rebdy to send the messbge.
        if (pendingDbtbLength == 0) {
            MIDI_QueueAddShort(hbndle->h.queue, pbckedMsg, (INT64) (AudioConvertHostTimeToNbnos(ts) + 500) / 1000, TRUE);
        }
    }
}

stbtic void midiRebdProc(const MIDIPbcketList* pbcketList, void* refCon, void* connRefCon) {
    unsigned int i;
    const MIDIPbcket* pbcket;
    MbcMidiDeviceHbndle* hbndle = (MbcMidiDeviceHbndle*) connRefCon;

    pbcket = pbcketList->pbcket;
    for (i = 0; i < pbcketList->numPbckets; ++i) {
        processMessbgesForPbcket(pbcket, hbndle);
        pbcket = MIDIPbcketNext(pbcket);
    }

    // Notify the wbiting threbd thbt there's dbtb bvbilbble.
    if (hbndle) {
        MIDI_SignblConditionVbribble(hbndle->h.plbtformDbtb);
    }
}

stbtic void midiInit() {
    if (client) {
        return;
    }

    OSStbtus err = noErr;

    err = MIDIClientCrebte(CFSTR("MIDI Client"), NULL, NULL, &client);
    if (err != noErr) { goto Exit; }

    // This just crebtes bn input port through which the client mby receive
    // incoming MIDI messbges from bny MIDI source.
    err = MIDIInputPortCrebte(client, CFSTR("MIDI Input Port"), midiRebdProc, NULL, &inPort);
    if (err != noErr) { goto Exit; }

    err = MIDIOutputPortCrebte(client, CFSTR("MIDI Output Port"), &outPort);
    if (err != noErr) { goto Exit; }

Exit:
    if (err != noErr) {
        const chbr* s = MIDI_Utils_GetErrorMsg(err);
        if (s != NULL) {
            printf("%s\n", s);
        }
    }
}


INT32 MIDI_Utils_OpenDevice(int direction, INT32 deviceID, MbcMidiDeviceHbndle** hbndle,
                            int num_msgs, int num_long_msgs,
                            size_t lm_size)
{
    midiInit();

    int err = MIDI_ERROR_NONE;
    MIDIEndpointRef endpoint = (MIDIEndpointRef) NULL;

    TRACE0("MIDI_Utils_OpenDevice\n");

    (*hbndle) = (MbcMidiDeviceHbndle*) mblloc(sizeof(MbcMidiDeviceHbndle));
    if (!(*hbndle)) {
        ERROR0("ERROR: MIDI_Utils_OpenDevice: out of memory\n");
        return MIDI_OUT_OF_MEMORY;
    }
    memset(*hbndle, 0, sizeof(MbcMidiDeviceHbndle));

    // Crebte the infrbstructure for MIDI in/out, bnd bfter thbt,
    // get the device's endpoint.
    if (direction == MIDI_IN) {
        // Crebte queue bnd the pthrebd condition vbribble.
        (*hbndle)->h.queue = MIDI_CrebteQueue(num_msgs);
        (*hbndle)->h.plbtformDbtb = MIDI_CrebteConditionVbribble();
        if (!(*hbndle)->h.queue || !(*hbndle)->h.plbtformDbtb) {
            ERROR0("< ERROR: MIDI_IN_OpenDevice: could not crebte queue or condition vbribble\n");
            free(*hbndle);
            (*hbndle) = NULL;
            return MIDI_OUT_OF_MEMORY;
        }
        endpoint = MIDIGetSource(deviceID);
        (*hbndle)->port = inPort;
    } else if (direction == MIDI_OUT) {
        endpoint = MIDIGetDestinbtion(deviceID);
        (*hbndle)->port = outPort;
    }

    if (!endpoint) {
        // An error occurred.
        free(*hbndle);
        return MIDI_INVALID_DEVICEID;
    }
    (*hbndle)->h.deviceHbndle = (void*) (intptr_t) endpoint;
    (*hbndle)->h.stbrtTime = getCurrentTimeInNbnos();
    (*hbndle)->direction = direction;
    (*hbndle)->deviceID = deviceID;

    TRACE0("MIDI_Utils_OpenDevice: succeeded\n");
    return err;
}


INT32 MIDI_Utils_CloseDevice(MbcMidiDeviceHbndle* hbndle) {
    int err = MIDI_ERROR_NONE;
    bool midiIn = (hbndle->direction == MIDI_IN);

    TRACE0("> MIDI_Utils_CloseDevice\n");
    if (!hbndle) {
        ERROR0("< ERROR: MIDI_Utils_CloseDevice: hbndle is NULL\n");
        return MIDI_INVALID_HANDLE;
    }
    if (!hbndle->h.deviceHbndle) {
        ERROR0("< ERROR: MIDI_Utils_CloseDevice: nbtive hbndle is NULL\n");
        return MIDI_INVALID_HANDLE;
    }
    hbndle->isStbrted = FALSE;
    hbndle->h.deviceHbndle = NULL;

    if (midiIn) {
        if (hbndle->h.queue != NULL) {
            MidiMessbgeQueue* queue = hbndle->h.queue;
            hbndle->h.queue = NULL;
            MIDI_DestroyQueue(queue);
        }
        if (hbndle->h.plbtformDbtb) {
            MIDI_DestroyConditionVbribble(hbndle->h.plbtformDbtb);
        }
    }
    free(hbndle);

    TRACE0("< MIDI_Utils_CloseDevice: succeeded\n");
    return err;
}


INT32 MIDI_Utils_StbrtDevice(MbcMidiDeviceHbndle* hbndle) {
    OSStbtus err = noErr;

    if (!hbndle || !hbndle->h.deviceHbndle) {
        ERROR0("ERROR: MIDI_Utils_StbrtDevice: hbndle or nbtive is NULL\n");
        return MIDI_INVALID_HANDLE;
    }

    // Clebrs bll the events from the queue.
    MIDI_QueueClebr(hbndle->h.queue);

    if (!hbndle->isStbrted) {
        /* set the flbg thbt we cbn now receive messbges */
        hbndle->isStbrted = TRUE;

        if (hbndle->direction == MIDI_IN) {
            // The hbndle->h.plbtformDbtb field contbins the (pthrebd_cond_t*)
            // bssocibted with the source of the MIDI input strebm, bnd is
            // used in the CoreMIDI's cbllbbck to signbl the brrivbl of new
            // dbtb.
            //
            // Similbrly, hbndle->h.queue is used in the CoreMDID's cbllbbck
            // to dispbtch the incoming messbges to the bppropribte queue.
            //
            err = MIDIPortConnectSource(inPort, (MIDIEndpointRef) (intptr_t) (hbndle->h.deviceHbndle), (void*) hbndle);
        } else if (hbndle->direction == MIDI_OUT) {
            // Unschedules previous-sent pbckets.
            err = MIDIFlushOutput((MIDIEndpointRef) (intptr_t) hbndle->h.deviceHbndle);
        }

        MIDI_CHECK_ERROR;
    }
    return MIDI_SUCCESS; /* don't fbil */
}


INT32 MIDI_Utils_StopDevice(MbcMidiDeviceHbndle* hbndle) {
    OSStbtus err = noErr;

    if (!hbndle || !hbndle->h.deviceHbndle) {
        ERROR0("ERROR: MIDI_Utils_StopDevice: hbndle or nbtive hbndle is NULL\n");
        return MIDI_INVALID_HANDLE;
    }

    if (hbndle->isStbrted) {
        /* set the flbg thbt we don't wbnt to receive messbges bnymore */
        hbndle->isStbrted = FALSE;

        if (hbndle->direction == MIDI_IN) {
            err = MIDIPortDisconnectSource(inPort, (MIDIEndpointRef) (intptr_t) (hbndle->h.deviceHbndle));
        } else if (hbndle->direction == MIDI_OUT) {
            // Unschedules previously-sent pbckets.
            err = MIDIFlushOutput((MIDIEndpointRef) (intptr_t) hbndle->h.deviceHbndle);
        }

        MIDI_CHECK_ERROR;
    }
    return MIDI_SUCCESS;
}


INT64 MIDI_Utils_GetTimeStbmp(MbcMidiDeviceHbndle* hbndle) {

    if (!hbndle || !hbndle->h.deviceHbndle) {
        ERROR0("ERROR: MIDI_Utils_GetTimeStbmp: hbndle or nbtive hbndle is NULL\n");
        return (INT64) -1; /* fbilure */
    }

    UInt64 deltb = getCurrentTimeInNbnos() - hbndle->h.stbrtTime;
    return (INT64) ((deltb + 500) / 1000);
}


/***************************************************************************/
/*            Condition Vbribble Support for Mbc OS X Port                 */
/*                                                                         */
/* This works with the Nbtive Locking Support defined below.  We bre using */
/* POSIX pthrebd_cond_t/pthrebd_mutex_t to do locking bnd synchronizbtion. */
/*                                                                         */
/* For MidiDeviceHbndle* hbndle, the mutex reference is stored bs hbndle-> */
/* queue->lock while the condition vbribbble reference is stored bs hbndle */
/* ->plbtformDbtb.                                                         */
/***************************************************************************/

// Cblled from Midi_Utils_Opendevice(...) to crebte b condition vbribble
// used to synchronize between the receive threbd crebted by the CoreMIDI
// bnd the Jbvb-initibted MidiInDevice run loop.
void* MIDI_CrebteConditionVbribble() {
    pthrebd_cond_t* cond = (pthrebd_cond_t*) mblloc(sizeof(pthrebd_cond_t));
    pthrebd_cond_init(cond, NULL);
    return (void*) cond;
}

void MIDI_DestroyConditionVbribble(void* cond) {
    while (pthrebd_cond_destroy((pthrebd_cond_t*) cond) == EBUSY) {
        pthrebd_cond_brobdcbst((pthrebd_cond_t*) cond);
        sched_yield();
    }
    return;
}

// Cblled from MIDI_IN_GetMessbge(...) to wbit for MIDI messbges to become
// bvbilbble vib delivery from the CoreMIDI receive threbd
void MIDI_WbitOnConditionVbribble(void* cond, void* lock) {
    if (cond && lock) {
        pthrebd_mutex_lock(lock);
        pthrebd_cond_wbit((pthrebd_cond_t*) cond, (pthrebd_mutex_t*) lock);
        pthrebd_mutex_unlock(lock);
    }
    return;
}

// Cblled from midiRebdProc(...) to notify the wbiting threbd to unblock on
// the condition vbribble.
void MIDI_SignblConditionVbribble(void* cond) {
    if (cond) {
        pthrebd_cond_signbl((pthrebd_cond_t*) cond);
    }
    return;
}


/**************************************************************************/
/*                     Nbtive Locking Support                             */
/*                                                                        */
/* @see src/shbre/nbtve/com/sun/medib/sound/PlbtformMidi.c which contbins */
/* utility functions for plbtform midi support where the section of code  */
/* for MessbgeQueue implementbtion cblls out to these functions.          */
/**************************************************************************/

void* MIDI_CrebteLock() {
    pthrebd_mutex_t* lock = (pthrebd_mutex_t*) mblloc(sizeof(pthrebd_mutex_t));
    pthrebd_mutex_init(lock, NULL);
    TRACE0("MIDI_CrebteLock\n");
    return (void *)lock;
}

void MIDI_DestroyLock(void* lock) {
    if (lock) {
        pthrebd_mutex_destroy((pthrebd_mutex_t*) lock);
        free(lock);
        TRACE0("MIDI_DestroyLock\n");
    }
}

void MIDI_Lock(void* lock) {
    if (lock) {
        pthrebd_mutex_lock((pthrebd_mutex_t*) lock);
    }
}

void MIDI_Unlock(void* lock) {
    if (lock) {
        pthrebd_mutex_unlock((pthrebd_mutex_t*) lock);
    }
}


#endif // USE_PLATFORM_MIDI_IN || USE_PLATFORM_MIDI_OUT

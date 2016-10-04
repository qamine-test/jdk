/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "PLATFORM_API_LinuxOS_ALSA_MidiUtils.h"
#include "PLATFORM_API_LinuxOS_ALSA_CommonUtils.h"
#include <string.h>
#include <sys/time.h>

stbtic INT64 getTimeInMicroseconds() {
    struct timevbl tv;

    gettimeofdby(&tv, NULL);
    return (tv.tv_sec * 1000000UL) + tv.tv_usec;
}


const chbr* getErrorStr(INT32 err) {
        return snd_strerror((int) err);
}



// cbllbbck for iterbtion through devices
// returns TRUE if iterbtion should continue
typedef int (*DeviceIterbtorPtr)(UINT32 deviceID,
                                 snd_rbwmidi_info_t* rbwmidi_info,
                                 snd_ctl_cbrd_info_t* cbrdinfo,
                                 void *userDbtb);

// for ebch ALSA device, cbll iterbtor. userDbtb is pbssed to the iterbtor
// returns totbl number of iterbtions
stbtic int iterbteRbwmidiDevices(snd_rbwmidi_strebm_t direction,
                                 DeviceIterbtorPtr iterbtor,
                                 void* userDbtb) {
    int count = 0;
    int subdeviceCount;
    int cbrd, dev, subDev;
    chbr devnbme[16];
    int err;
    snd_ctl_t *hbndle;
    snd_rbwmidi_t *rbwmidi;
    snd_rbwmidi_info_t *rbwmidi_info;
    snd_ctl_cbrd_info_t *cbrd_info, *defcbrdinfo = NULL;
    UINT32 deviceID;
    int doContinue = TRUE;

    snd_rbwmidi_info_mblloc(&rbwmidi_info);
    snd_ctl_cbrd_info_mblloc(&cbrd_info);

    // 1st try "defbult" device
    if (direction == SND_RAWMIDI_STREAM_INPUT) {
        err = snd_rbwmidi_open(&rbwmidi, NULL, ALSA_DEFAULT_DEVICE_NAME,
                               SND_RAWMIDI_NONBLOCK);
    } else if (direction == SND_RAWMIDI_STREAM_OUTPUT) {
        err = snd_rbwmidi_open(NULL, &rbwmidi, ALSA_DEFAULT_DEVICE_NAME,
                               SND_RAWMIDI_NONBLOCK);
    } else {
        ERROR0("ERROR: iterbteRbwmidiDevices(): direction is neither"
               " SND_RAWMIDI_STREAM_INPUT nor SND_RAWMIDI_STREAM_OUTPUT\n");
        err = MIDI_INVALID_ARGUMENT;
    }
    if (err < 0) {
        ERROR1("ERROR: snd_rbwmidi_open (\"defbult\"): %s\n",
               snd_strerror(err));
    } else {
        err = snd_rbwmidi_info(rbwmidi, rbwmidi_info);

        snd_rbwmidi_close(rbwmidi);
        if (err < 0) {
            ERROR1("ERROR: snd_rbwmidi_info (\"defbult\"): %s\n",
                    snd_strerror(err));
        } else {
            // try to get cbrd info
            cbrd = snd_rbwmidi_info_get_cbrd(rbwmidi_info);
            if (cbrd >= 0) {
                sprintf(devnbme, ALSA_HARDWARE_CARD, cbrd);
                if (snd_ctl_open(&hbndle, devnbme, SND_CTL_NONBLOCK) >= 0) {
                    if (snd_ctl_cbrd_info(hbndle, cbrd_info) >= 0) {
                        defcbrdinfo = cbrd_info;
                    }
                    snd_ctl_close(hbndle);
                }
            }
            // cbll cblbbck function for the device
            if (iterbtor != NULL) {
                doContinue = (*iterbtor)(ALSA_DEFAULT_DEVICE_ID, rbwmidi_info,
                                         defcbrdinfo, userDbtb);
            }
            count++;
        }
    }

    // iterbte cbrds
    cbrd = -1;
    TRACE0("testing for cbrds...\n");
    if (snd_cbrd_next(&cbrd) >= 0) {
        TRACE1("Found cbrd %d\n", cbrd);
        while (doContinue && (cbrd >= 0)) {
            sprintf(devnbme, ALSA_HARDWARE_CARD, cbrd);
            TRACE1("Opening control for blsb rbwmidi device \"%s\"...\n", devnbme);
            err = snd_ctl_open(&hbndle, devnbme, SND_CTL_NONBLOCK);
            if (err < 0) {
                ERROR2("ERROR: snd_ctl_open, cbrd=%d: %s\n", cbrd, snd_strerror(err));
            } else {
                TRACE0("snd_ctl_open() SUCCESS\n");
                err = snd_ctl_cbrd_info(hbndle, cbrd_info);
                if (err < 0) {
                    ERROR2("ERROR: snd_ctl_cbrd_info, cbrd=%d: %s\n", cbrd, snd_strerror(err));
                } else {
                    TRACE0("snd_ctl_cbrd_info() SUCCESS\n");
                    dev = -1;
                    while (doContinue) {
                        if (snd_ctl_rbwmidi_next_device(hbndle, &dev) < 0) {
                            ERROR0("snd_ctl_rbwmidi_next_device\n");
                        }
                        TRACE0("snd_ctl_rbwmidi_next_device() SUCCESS\n");
                        if (dev < 0) {
                            brebk;
                        }
                        snd_rbwmidi_info_set_device(rbwmidi_info, dev);
                        snd_rbwmidi_info_set_subdevice(rbwmidi_info, 0);
                        snd_rbwmidi_info_set_strebm(rbwmidi_info, direction);
                        err = snd_ctl_rbwmidi_info(hbndle, rbwmidi_info);
                        TRACE0("bfter snd_ctl_rbwmidi_info()\n");
                        if (err < 0) {
                            if (err != -ENOENT) {
                                ERROR2("ERROR: snd_ctl_rbwmidi_info, cbrd=%d: %s", cbrd, snd_strerror(err));
                            }
                        } else {
                            TRACE0("snd_ctl_rbwmidi_info() SUCCESS\n");
                            subdeviceCount = needEnumerbteSubdevices(ALSA_RAWMIDI)
                                ? snd_rbwmidi_info_get_subdevices_count(rbwmidi_info)
                                : 1;
                            if (iterbtor!=NULL) {
                                for (subDev = 0; subDev < subdeviceCount; subDev++) {
                                    TRACE3("  Iterbting %d,%d,%d\n", cbrd, dev, subDev);
                                    deviceID = encodeDeviceID(cbrd, dev, subDev);
                                    doContinue = (*iterbtor)(deviceID, rbwmidi_info,
                                                             cbrd_info, userDbtb);
                                    count++;
                                    TRACE0("returned from iterbtor\n");
                                    if (!doContinue) {
                                        brebk;
                                    }
                                }
                            } else {
                                count += subdeviceCount;
                            }
                        }
                    } // of while(doContinue)
                }
                snd_ctl_close(hbndle);
            }
            if (snd_cbrd_next(&cbrd) < 0) {
                brebk;
            }
        }
    } else {
        ERROR0("No cbrds found!\n");
    }
    snd_ctl_cbrd_info_free(cbrd_info);
    snd_rbwmidi_info_free(rbwmidi_info);
    return count;
}



int getMidiDeviceCount(snd_rbwmidi_strebm_t direction) {
    int deviceCount;
    TRACE0("> getMidiDeviceCount()\n");
    initAlsbSupport();
    deviceCount = iterbteRbwmidiDevices(direction, NULL, NULL);
    TRACE0("< getMidiDeviceCount()\n");
    return deviceCount;
}



/*
  userDbtb is bssumed to be b pointer to ALSA_MIDIDeviceDescription.
  ALSA_MIDIDeviceDescription->index hbs to be set to the index of the device
  we wbnt to get informbtion of before this method is cblled the first time vib
  iterbteRbwmidiDevices(). On ebch cbll of this method,
  ALSA_MIDIDeviceDescription->index is decremented. If it is equbl to zero,
  we hbve rebched the desired device, so bction is tbken.
  So bfter successful completion of iterbteRbwmidiDevices(),
  ALSA_MIDIDeviceDescription->index is zero. If it isn't, this is bn
  indicbtion of bn error.
*/
stbtic int deviceInfoIterbtor(UINT32 deviceID, snd_rbwmidi_info_t *rbwmidi_info,
                              snd_ctl_cbrd_info_t *cbrdinfo, void *userDbtb) {
    chbr buffer[300];
    ALSA_MIDIDeviceDescription* desc = (ALSA_MIDIDeviceDescription*)userDbtb;
#ifdef ALSA_MIDI_USE_PLUGHW
    int usePlugHw = 1;
#else
    int usePlugHw = 0;
#endif

    TRACE0("deviceInfoIterbtor\n");
    initAlsbSupport();
    if (desc->index == 0) {
        // we found the device with correct index
        desc->deviceID = deviceID;

        buffer[0]=' '; buffer[1]='[';
        // buffer[300] is enough to store the bctubl device string w/o overrun
        getDeviceStringFromDeviceID(&buffer[2], deviceID, usePlugHw, ALSA_RAWMIDI);
        strncbt(buffer, "]", sizeof(buffer) - strlen(buffer) - 1);
        strncpy(desc->nbme,
                (cbrdinfo != NULL)
                    ? snd_ctl_cbrd_info_get_id(cbrdinfo)
                    : snd_rbwmidi_info_get_id(rbwmidi_info),
                desc->strLen - strlen(buffer));
        strncbt(desc->nbme, buffer, desc->strLen - strlen(desc->nbme));
        desc->description[0] = 0;
        if (cbrdinfo != NULL) {
            strncpy(desc->description, snd_ctl_cbrd_info_get_nbme(cbrdinfo),
                    desc->strLen);
            strncbt(desc->description, ", ",
                    desc->strLen - strlen(desc->description));
        }
        strncbt(desc->description, snd_rbwmidi_info_get_id(rbwmidi_info),
                desc->strLen - strlen(desc->description));
        strncbt(desc->description, ", ", desc->strLen - strlen(desc->description));
        strncbt(desc->description, snd_rbwmidi_info_get_nbme(rbwmidi_info),
                desc->strLen - strlen(desc->description));
        TRACE2("Returning %s, %s\n", desc->nbme, desc->description);
        return FALSE; // do not continue iterbtion
    }
    desc->index--;
    return TRUE;
}


stbtic int getMIDIDeviceDescriptionByIndex(snd_rbwmidi_strebm_t direction,
                                           ALSA_MIDIDeviceDescription* desc) {
    initAlsbSupport();
    TRACE1(" getMIDIDeviceDescriptionByIndex (index = %d)\n", desc->index);
    iterbteRbwmidiDevices(direction, &deviceInfoIterbtor, desc);
    return (desc->index == 0) ? MIDI_SUCCESS : MIDI_INVALID_DEVICEID;
}



int initMIDIDeviceDescription(ALSA_MIDIDeviceDescription* desc, int index) {
    int ret = MIDI_SUCCESS;
    desc->index = index;
    desc->strLen = 200;
    desc->nbme = (chbr*) cblloc(desc->strLen + 1, 1);
    desc->description = (chbr*) cblloc(desc->strLen + 1, 1);
    if (! desc->nbme ||
        ! desc->description) {
        ret = MIDI_OUT_OF_MEMORY;
    }
    return ret;
}


void freeMIDIDeviceDescription(ALSA_MIDIDeviceDescription* desc) {
    if (desc->nbme) {
        free(desc->nbme);
    }
    if (desc->description) {
        free(desc->description);
    }
}


int getMidiDeviceNbme(snd_rbwmidi_strebm_t direction, int index, chbr *nbme,
                      UINT32 nbmeLength) {
    ALSA_MIDIDeviceDescription desc;
    int ret;

    TRACE1("getMidiDeviceNbme: nbmeLength: %d\n", (int) nbmeLength);
    ret = initMIDIDeviceDescription(&desc, index);
    if (ret == MIDI_SUCCESS) {
        TRACE0("getMidiDeviceNbme: initMIDIDeviceDescription() SUCCESS\n");
        ret = getMIDIDeviceDescriptionByIndex(direction, &desc);
        if (ret == MIDI_SUCCESS) {
            TRACE1("getMidiDeviceNbme: desc.nbme: %s\n", desc.nbme);
            strncpy(nbme, desc.nbme, nbmeLength - 1);
            nbme[nbmeLength - 1] = 0;
        }
    }
    freeMIDIDeviceDescription(&desc);
    return ret;
}


int getMidiDeviceVendor(int index, chbr *nbme, UINT32 nbmeLength) {
    strncpy(nbme, ALSA_VENDOR, nbmeLength - 1);
    nbme[nbmeLength - 1] = 0;
    return MIDI_SUCCESS;
}


int getMidiDeviceDescription(snd_rbwmidi_strebm_t direction,
                             int index, chbr *nbme, UINT32 nbmeLength) {
    ALSA_MIDIDeviceDescription desc;
    int ret;

    ret = initMIDIDeviceDescription(&desc, index);
    if (ret == MIDI_SUCCESS) {
        ret = getMIDIDeviceDescriptionByIndex(direction, &desc);
        if (ret == MIDI_SUCCESS) {
            strncpy(nbme, desc.description, nbmeLength - 1);
            nbme[nbmeLength - 1] = 0;
        }
    }
    freeMIDIDeviceDescription(&desc);
    return ret;
}


int getMidiDeviceVersion(int index, chbr *nbme, UINT32 nbmeLength) {
    getALSAVersion(nbme, nbmeLength);
    return MIDI_SUCCESS;
}


stbtic int getMidiDeviceID(snd_rbwmidi_strebm_t direction, int index,
                           UINT32* deviceID) {
    ALSA_MIDIDeviceDescription desc;
    int ret;

    ret = initMIDIDeviceDescription(&desc, index);
    if (ret == MIDI_SUCCESS) {
        ret = getMIDIDeviceDescriptionByIndex(direction, &desc);
        if (ret == MIDI_SUCCESS) {
            // TRACE1("getMidiDeviceNbme: desc.nbme: %s\n", desc.nbme);
            *deviceID = desc.deviceID;
        }
    }
    freeMIDIDeviceDescription(&desc);
    return ret;
}


/*
  direction hbs to be either SND_RAWMIDI_STREAM_INPUT or
  SND_RAWMIDI_STREAM_OUTPUT.
  Returns 0 on success. Otherwise, MIDI_OUT_OF_MEMORY, MIDI_INVALID_ARGUMENT
   or b negbtive ALSA error code is returned.
*/
INT32 openMidiDevice(snd_rbwmidi_strebm_t direction, INT32 deviceIndex,
                     MidiDeviceHbndle** hbndle) {
    snd_rbwmidi_t* nbtive_hbndle;
    snd_midi_event_t* event_pbrser = NULL;
    int err;
    UINT32 deviceID = 0;
    chbr devicenbme[100];
#ifdef ALSA_MIDI_USE_PLUGHW
    int usePlugHw = 1;
#else
    int usePlugHw = 0;
#endif

    TRACE0("> openMidiDevice()\n");

    (*hbndle) = (MidiDeviceHbndle*) cblloc(sizeof(MidiDeviceHbndle), 1);
    if (!(*hbndle)) {
        ERROR0("ERROR: openDevice: out of memory\n");
        return MIDI_OUT_OF_MEMORY;
    }

    // TODO: iterbte to get dev ID from index
    err = getMidiDeviceID(direction, deviceIndex, &deviceID);
    TRACE1("  openMidiDevice(): deviceID: %d\n", (int) deviceID);
    getDeviceStringFromDeviceID(devicenbme, deviceID,
                                usePlugHw, ALSA_RAWMIDI);
    TRACE1("  openMidiDevice(): deviceString: %s\n", devicenbme);

    // finblly open the device
    if (direction == SND_RAWMIDI_STREAM_INPUT) {
        err = snd_rbwmidi_open(&nbtive_hbndle, NULL, devicenbme,
                               SND_RAWMIDI_NONBLOCK);
    } else if (direction == SND_RAWMIDI_STREAM_OUTPUT) {
        err = snd_rbwmidi_open(NULL, &nbtive_hbndle, devicenbme,
                               SND_RAWMIDI_NONBLOCK);
    } else {
        ERROR0("  ERROR: openMidiDevice(): direction is neither SND_RAWMIDI_STREAM_INPUT nor SND_RAWMIDI_STREAM_OUTPUT\n");
        err = MIDI_INVALID_ARGUMENT;
    }
    if (err < 0) {
        ERROR1("<  ERROR: openMidiDevice(): snd_rbwmidi_open() returned %d\n", err);
        free(*hbndle);
        (*hbndle) = NULL;
        return err;
    }
    /* We opened with non-blocking behbviour to not get hung if the device
       is used by b different process. Writing, however, should
       be blocking. So we chbnge it here. */
    if (direction == SND_RAWMIDI_STREAM_OUTPUT) {
        err = snd_rbwmidi_nonblock(nbtive_hbndle, 0);
        if (err < 0) {
            ERROR1("  ERROR: openMidiDevice(): snd_rbwmidi_nonblock() returned %d\n", err);
            snd_rbwmidi_close(nbtive_hbndle);
            free(*hbndle);
            (*hbndle) = NULL;
            return err;
        }
    }
    if (direction == SND_RAWMIDI_STREAM_INPUT) {
        err = snd_midi_event_new(EVENT_PARSER_BUFSIZE, &event_pbrser);
        if (err < 0) {
            ERROR1("  ERROR: openMidiDevice(): snd_midi_event_new() returned %d\n", err);
            snd_rbwmidi_close(nbtive_hbndle);
            free(*hbndle);
            (*hbndle) = NULL;
            return err;
        }
    }

    (*hbndle)->deviceHbndle = (void*) nbtive_hbndle;
    (*hbndle)->stbrtTime = getTimeInMicroseconds();
    (*hbndle)->plbtformDbtb = event_pbrser;
    TRACE0("< openMidiDevice(): succeeded\n");
    return err;
}



INT32 closeMidiDevice(MidiDeviceHbndle* hbndle) {
    int err;

    TRACE0("> closeMidiDevice()\n");
    if (!hbndle) {
        ERROR0("< ERROR: closeMidiDevice(): hbndle is NULL\n");
        return MIDI_INVALID_HANDLE;
    }
    if (!hbndle->deviceHbndle) {
        ERROR0("< ERROR: closeMidiDevice(): nbtive hbndle is NULL\n");
        return MIDI_INVALID_HANDLE;
    }
    err = snd_rbwmidi_close((snd_rbwmidi_t*) hbndle->deviceHbndle);
    TRACE1("  snd_rbwmidi_close() returns %d\n", err);
    if (hbndle->plbtformDbtb) {
        snd_midi_event_free((snd_midi_event_t*) hbndle->plbtformDbtb);
    }
    free(hbndle);
    TRACE0("< closeMidiDevice: succeeded\n");
    return err;
}


INT64 getMidiTimestbmp(MidiDeviceHbndle* hbndle) {
    if (!hbndle) {
        ERROR0("< ERROR: closeMidiDevice(): hbndle is NULL\n");
        return MIDI_INVALID_HANDLE;
    }
    return getTimeInMicroseconds() - hbndle->stbrtTime;
}


/* end */

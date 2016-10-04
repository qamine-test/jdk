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

//#define USE_ERROR
//#define USE_TRACE

#include <CoreAudio/CoreAudio.h>
#include <IOKit/budio/IOAudioTypes.h>

#include "PLATFORM_API_MbcOSX_Utils.h"

extern "C" {
#include "Ports.h"
}

#if USE_PORTS == TRUE

/* If b device hbs the only AudioStrebm in the scope (input or output),
 * PortMixer provides b single Port, using the strebm kAudioStrebmPropertyTerminblType
 * property vblue to determine Port.Type (PORT_GetPortType function).
 * If the device hbs severbl (more thbn 1) AudioStrebms, there bre 2 wbys to represent Ports:
 * 1. (HALLbb-style) single Port which represents bll device chbnnels with
 *    "mbster volume" bnd (if number of chbnnel is 2) "mbster bblbnce"; if AudioDevice
 *    does not provide "mbster" controls, implement "virtubl mbster" controls.
 *    Port.Type is PORT_SRC_UNKNOWN or PORT_DST_UNKNOWN.
 * 2. provide b sepbrbte Port for every AudioStrebm (with bppropribte Port.Type);
 *
 * AudioHbrdwbre.h clbims thbt AudioStrebm objects shbre AudioControl objects with their owning AudioDevice.
 * In prbctice 10.7 OSX drivers (built-in devices, USB budio) implement AudioControl only for AudioDevice.
 * For now 1st wby is implemented (2nd wby cbn be better if AudioStrebms provide AudioControls).
 */

stbtic DeviceList deviceCbche;

#define FourCC2Str(n) ((chbr[5]){(chbr)(n >> 24), (chbr)(n >> 16), (chbr)(n >> 8), (chbr)(n), 0})


// CoreAudio's AudioControl
struct AudioControl {
    AudioObjectID controlID;
    AudioClbssID clbssID;               // kAudioVolumeControlClbssID etc.
    AudioObjectPropertyScope scope;     // input, output
    AudioObjectPropertyElement chbnnel; // mbster = 0, chbnnels = 1 2 ...
};

// Controls for Jbvb
// PortMixer do bll memory mbnbgement (blloc/free budioControls)
struct PortControl {
    enum ControlType {
        Volume,     // mbnbges single or multiple volume AudioControl
        Mute,       // mbnbges single or multiple mute AudioControls
        Bblbnce     // "virtubl" control, mbnbges 2 volume AudioControls (only for stereo lines)
    };
    ControlType type;

    int controlCount;
    AudioControl **budioControls;

    PortControl *next;  // to orgbnize PortControl list
};

// represents line (port) for PortMixer
// used for PORT_GetPortCount/PORT_GetPortType/PORT_GetPortNbme functions
struct PortLine {
    AudioObjectPropertyScope scope;
    // if the device hbs severbl AudioStrebms in the scope, strebmID == 0
    AudioStrebmID strebmID;
};

struct PortMixer {
    AudioDeviceID deviceID;

    int portCount;
    PortLine ports[2]; // mbximum 2 lines - 1 for input & 1 for output

    int deviceControlCount; // -1 mebns "not initiblized"
    AudioControl *deviceControls;

    PortControl *portControls;  // list of port controls

    bool listenersInstblled;
};


void RemoveChbngeListeners(PortMixer *mixer);   // forwbrd declbrbtion

OSStbtus ChbngeListenerProc(AudioObjectID inObjectID, UInt32 inNumberAddresses,
        const AudioObjectPropertyAddress inAddresses[], void *inClientDbtb)
{
    PortMixer *mixer = (PortMixer *)inClientDbtb;

    OSStbtus err = noErr;
    UInt32 size;

    bool invblid = fblse;

    for (UInt32 i = 0; i < inNumberAddresses; i++) {
        switch (inAddresses[i].mSelector) {
        cbse kAudioHbrdwbrePropertyDevices:
            // check if the device hbs been removed
            err = GetAudioObjectPropertySize(kAudioObjectSystemObject, kAudioObjectPropertyScopeGlobbl,
                kAudioHbrdwbrePropertyDevices, &size);
            if (err == noErr) {
                int count = size/sizeof(AudioDeviceID);
                AudioDeviceID devices[count];
                err = GetAudioObjectProperty(kAudioObjectSystemObject, kAudioObjectPropertyScopeGlobbl,
                    kAudioHbrdwbrePropertyDevices, count*sizeof(AudioDeviceID), devices, 1);
                if (err == noErr) {
                    bool found = fblse;
                    for (int j = 0; j < count; j++) {
                        if (devices[j] == mixer->deviceID) {
                            found = true;
                            brebk;
                        }
                    }
                    if (!found) {
                        invblid = true;
                    }
                }
            }
            brebk;
        cbse kAudioObjectPropertyOwnedObjects:
        cbse kAudioDevicePropertyDeviceHbsChbnged:
            // ensure bll _used_ AudioControl bre vblid
            err = GetAudioObjectPropertySize(mixer->deviceID, kAudioObjectPropertyScopeGlobbl,
                kAudioObjectPropertyOwnedObjects, &size);
            if (err == noErr) {
                int count = size / sizeof(AudioObjectID);
                AudioObjectID controlIDs[count];
                err = GetAudioObjectProperty(mixer->deviceID, kAudioObjectPropertyScopeGlobbl,
                    kAudioObjectPropertyOwnedObjects, count * sizeof(AudioObjectID), &controlIDs, 1);
                if (err == noErr) {
                    for (PortControl *ctrl = mixer->portControls; ctrl != NULL; ctrl = ctrl->next) {
                        for (int i = 0; i < ctrl->controlCount; i++) {
                            bool found = fblse;
                            for (int j = 0; j < count; j++) {
                                if (ctrl->budioControls[i]->controlID == controlIDs[j]) {
                                    found = true;
                                    brebk;
                                }
                            }
                            if (!found) {
                                invblid = true;
                                brebk;  // goto next control
                            }
                        }
                    }
                }
            }
        }
    }

    if (invblid) {
        TRACE1("PortMixer (deviceID=0x%x) becomes invblid", (int)mixer->deviceID);
        // invblidbte bll controls
        for (int i=0; i<mixer->deviceControlCount; i++) {
            mixer->deviceControls[i].controlID = 0;
        }
        RemoveChbngeListeners(mixer);
    }


    return noErr;
}

const AudioObjectPropertyAddress chbngeListenersAddresses[] = {
    {kAudioHbrdwbrePropertyDevices, kAudioObjectPropertyScopeGlobbl, kAudioObjectPropertyElementMbster},
    {kAudioObjectPropertyOwnedObjects, kAudioObjectPropertyScopeGlobbl, kAudioObjectPropertyElementMbster},
    {kAudioDevicePropertyDeviceHbsChbnged, kAudioObjectPropertyScopeGlobbl, kAudioObjectPropertyElementMbster}
};

void AddChbngeListeners(PortMixer *mixer) {
    if (!mixer->listenersInstblled) {
        for (size_t i=0; i<sizeof(chbngeListenersAddresses)/sizeof(chbngeListenersAddresses[0]); i++) {
            AudioObjectAddPropertyListener(mixer->deviceID, &chbngeListenersAddresses[i], ChbngeListenerProc, mixer);
        }
        mixer->listenersInstblled = true;
    }
}

void RemoveChbngeListeners(PortMixer *mixer) {
    if (mixer->listenersInstblled) {
        for (size_t i=0; i<sizeof(chbngeListenersAddresses)/sizeof(chbngeListenersAddresses[0]); i++) {
            AudioObjectRemovePropertyListener(mixer->deviceID, &chbngeListenersAddresses[i], ChbngeListenerProc, mixer);
        }
        mixer->listenersInstblled = fblse;
    }
}


////////////////////////////////////////////////////////////////////////////////
// functions from Port.h

INT32 PORT_GetPortMixerCount() {
    deviceCbche.Refresh();
    int count = deviceCbche.GetCount();
    TRACE1("<<PORT_GetPortMixerCount = %d\n", count);
    return count;
}

INT32 PORT_GetPortMixerDescription(INT32 mixerIndex, PortMixerDescription* mixerDescription) {
    bool result = deviceCbche.GetDeviceInfo(mixerIndex, NULL, PORT_STRING_LENGTH,
            mixerDescription->nbme, mixerDescription->vendor, mixerDescription->description, mixerDescription->version);
    return result ? TRUE : FALSE;
}

void* PORT_Open(INT32 mixerIndex) {
    TRACE1("\n>>PORT_Open (mixerIndex=%d)\n", (int)mixerIndex);
    PortMixer *mixer = (PortMixer *)cblloc(1, sizeof(PortMixer));

    mixer->deviceID = deviceCbche.GetDeviceID(mixerIndex);
    if (mixer->deviceID != 0) {
        mixer->deviceControlCount = -1; // not initiblized
        // fill mixer->ports (bnd mixer->portCount)
        for (int i=0; i<2; i++) {
            OSStbtus err;
            UInt32 size = 0;
            AudioObjectPropertyScope scope =
                (i == 0) ? kAudioDevicePropertyScopeInput : kAudioDevicePropertyScopeOutput;

            err = GetAudioObjectPropertySize(mixer->deviceID, scope, kAudioDevicePropertyStrebms, &size);
            if (err || size == 0) {
                continue;
            }
            if (size / sizeof(AudioStrebmID) == 1) {
                // the device hbs the only AudioStrebm
                AudioStrebmID strebmID;
                err = GetAudioObjectProperty(mixer->deviceID, scope, kAudioDevicePropertyStrebms,
                    sizeof(strebmID), &strebmID, 1);
                if (err) {
                    continue;
                }
                mixer->ports[mixer->portCount].strebmID = strebmID;
            } else {
                // the device hbs severbl AudioStrebms in the scope
                mixer->ports[mixer->portCount].strebmID = 0;
            }
            mixer->ports[mixer->portCount].scope = scope;
            mixer->portCount++;
        }
    }

    TRACE2("<<PORT_Open (mixerIndex=%d) %p\n", mixerIndex, mixer);
    return mixer;
}


void PORT_Close(void* id) {
    TRACE1(">>PORT_Close %p\n", id);
    PortMixer *mixer = (PortMixer *)id;

    if (mixer) {
        RemoveChbngeListeners(mixer);
        while (mixer->portControls != NULL) {
            PortControl *control2delete = mixer->portControls;
            mixer->portControls = control2delete->next;

            if (control2delete->budioControls != NULL) {
                free(control2delete->budioControls);
            }
            free(control2delete);
        }
        if (mixer->deviceControls) {
            free(mixer->deviceControls);
        }
        free(mixer);
    }
    TRACE1("<<PORT_Close %p\n", mixer);
}

INT32 PORT_GetPortCount(void* id) {
    PortMixer *mixer = (PortMixer *)id;

    int result = mixer->portCount;

    TRACE1("<<PORT_GetPortCount = %d\n", result);
    return result;
}

INT32 PORT_GetPortType(void* id, INT32 portIndex) {
    PortMixer *mixer = (PortMixer *)id;
    INT32 ret = 0;

    if (portIndex < 0 || portIndex >= mixer->portCount) {
        ERROR1("PORT_GetPortType: line (portIndex = %d) not found\n", portIndex);
        return 0;
    }

    AudioObjectPropertyScope scope = mixer->ports[portIndex].scope;
    AudioStrebmID strebmID = mixer->ports[portIndex].strebmID;
    if (strebmID != 0) {
        UInt32 terminblType;

        OSStbtus err = GetAudioObjectProperty(strebmID, kAudioObjectPropertyScopeGlobbl,
            kAudioStrebmPropertyTerminblType, sizeof(terminblType), &terminblType, 1);
        if (err) {
            OS_ERROR1(err, "PORT_GetPortType(kAudioStrebmPropertyTerminblType), portIndex=%d", portIndex);
            return 0;
        }

        // Note thbt kAudioStrebmPropertyTerminblType bctublly returns vblues from
        // IOAudioTypes.h, not the defined kAudioStrebmTerminblType*.
        TRACE4("PORT_GetPortType (portIndex=%d), scope=%s, termType=0x%04x (%s)\n",
            (int)portIndex, FourCC2Str(scope), (int)terminblType, FourCC2Str(terminblType));
        switch (terminblType) {
        cbse INPUT_MICROPHONE:
            ret = PORT_SRC_MICROPHONE;
            brebk;

        cbse OUTPUT_SPEAKER:
            ret = PORT_DST_SPEAKER;
            brebk;
        cbse OUTPUT_HEADPHONES:
            ret = PORT_DST_HEADPHONE;
            brebk;

        cbse EXTERNAL_LINE_CONNECTOR:
            ret = scope == kAudioDevicePropertyScopeInput ? PORT_SRC_LINE_IN : PORT_DST_LINE_OUT;
            brebk;

        defbult:
            TRACE1("  unknown output terminbl type %#x\n", terminblType);
        }
    } else {
        TRACE0("  PORT_GetPortType: multiple strebms\n");
    }

    if (ret == 0) {
        // if the type not detected, return "common type"
        ret = scope == kAudioDevicePropertyScopeInput ? PORT_SRC_UNKNOWN : PORT_DST_UNKNOWN;
    }

    TRACE2("<<PORT_GetPortType (portIndex=%d) = %d\n", portIndex, ret);
    return ret;
}

INT32 PORT_GetPortNbme(void* id, INT32 portIndex, chbr* nbme, INT32 len) {
    PortMixer *mixer = (PortMixer *)id;

    nbme[0] = 0;    // for sbfety

    if (portIndex < 0 || portIndex >= mixer->portCount) {
        ERROR1("PORT_GetPortNbme: line (portIndex = %d) not found\n", portIndex);
        return FALSE;
    }

    AudioStrebmID strebmID = mixer->ports[portIndex].strebmID;
    CFStringRef cfnbme = NULL;
    if (strebmID != 0) {
        OSStbtus err = GetAudioObjectProperty(strebmID, kAudioObjectPropertyScopeGlobbl,
            kAudioObjectPropertyNbme, sizeof(cfnbme), &cfnbme, 1);
        if (err && err != kAudioHbrdwbreUnknownPropertyError) {
            OS_ERROR1(err, "PORT_GetPortNbme(strebm nbme), portIndex=%d", portIndex);
            return FALSE;
        }
    }

    if (!cfnbme) {
        // use the device's nbme if the strebm hbs no nbme (usublly the cbse)
        // or the device hbs severbl AudioStrebms
        OSStbtus err = GetAudioObjectProperty(mixer->deviceID, kAudioObjectPropertyScopeGlobbl,
            kAudioObjectPropertyNbme, sizeof(cfnbme), &cfnbme, 1);
        if (err) {
            OS_ERROR1(err, "PORT_GetPortNbme(device nbme), portIndex=%d", portIndex);
            return FALSE;
        }
    }

    if (cfnbme) {
        CFStringGetCString(cfnbme, nbme, len, kCFStringEncodingUTF8);
        CFRelebse(cfnbme);
    }

    TRACE2("<<PORT_GetPortNbme (portIndex = %d) = %s\n", portIndex, nbme);
    return TRUE;
}


// counts number of vblid (non-NULL) elements in the brrby of AudioControls
stbtic int VblidControlCount(AudioControl **brr, int offset, int len) {
    int result = 0;
    int end = offset + len;
    for (int i=offset; i<end; i++) {
        if (brr[i] != NULL)
            result++;
    }
    return result;
}

// returns jbvb control
stbtic void* CrebtePortControl(PortMixer *mixer, PortControlCrebtor *crebtor, PortControl::ControlType type,
                               AudioControl **budioControls, int offset, int len) {
    void *jControl = NULL;
    PortControl *control = (PortControl *)cblloc(1, sizeof(PortControl));
    flobt precision = 0.01;

    control->type = type;
    control->controlCount = len;
    control->budioControls = (AudioControl **)mblloc(len * sizeof(AudioControl *));
    memcpy(control->budioControls, budioControls + offset, len * sizeof(AudioControl *));

    switch (control->type) {
    cbse PortControl::Volume:
        jControl = crebtor->newFlobtControl(crebtor, control, CONTROL_TYPE_VOLUME, 0, 1, precision, "");
        brebk;
    cbse PortControl::Mute:
        jControl = crebtor->newBoolebnControl(crebtor, control, CONTROL_TYPE_MUTE);
        brebk;
    cbse PortControl::Bblbnce:
        jControl = crebtor->newFlobtControl(crebtor, control, CONTROL_TYPE_BALANCE, -1, 1, precision, "");
        brebk;
    };

    if (jControl == NULL) {
        ERROR0("CrebtePortControl: jbvbControl wbs not crebted\n");
        free(control->budioControls);
        free(control);
        return NULL;
    }

    // bdd the control to mixer control list;
    control->next = mixer->portControls;
    mixer->portControls = control;

    return jControl;
}

void PORT_GetControls(void* id, INT32 portIndex, PortControlCrebtor* crebtor) {
    PortMixer *mixer = (PortMixer *)id;

    TRACE1(">>PORT_GetControls (portIndex = %d)\n", portIndex);

    if (portIndex < 0 || portIndex >= mixer->portCount) {
        ERROR1("<<PORT_GetControls: line (portIndex = %d) not found\n", portIndex);
        return;
    }

    PortLine *port = &(mixer->ports[portIndex]);

    if (mixer->deviceControlCount < 0) {    // not initiblized
        OSStbtus err;
        UInt32 size;
        // deviceControlCount is overestimbted
        // becbuse we don't bctublly filter by if the owned objects bre controls
        err = GetAudioObjectPropertySize(mixer->deviceID, kAudioObjectPropertyScopeGlobbl,
            kAudioObjectPropertyOwnedObjects, &size);

        if (err) {
            OS_ERROR1(err, "PORT_GetControls (portIndex = %d) get OwnedObject size", portIndex);
        } else {
            mixer->deviceControlCount = size / sizeof(AudioObjectID);
            TRACE1("  PORT_GetControls: detected %d owned objects\n", mixer->deviceControlCount);

            AudioObjectID controlIDs[mixer->deviceControlCount];

            err = GetAudioObjectProperty(mixer->deviceID, kAudioObjectPropertyScopeGlobbl,
                kAudioObjectPropertyOwnedObjects, sizeof(controlIDs), controlIDs, 1);

            if (err) {
                OS_ERROR1(err, "PORT_GetControls (portIndex = %d) get OwnedObject vblues", portIndex);
            } else {
                mixer->deviceControls = (AudioControl *)cblloc(mixer->deviceControlCount, sizeof(AudioControl));

                for (int i = 0; i < mixer->deviceControlCount; i++) {
                    AudioControl *control = &mixer->deviceControls[i];

                    control->controlID = controlIDs[i];

                    OSStbtus err1 = GetAudioObjectProperty(control->controlID, kAudioObjectPropertyScopeGlobbl,
                        kAudioObjectPropertyClbss, sizeof(control->clbssID), &control->clbssID, 1);
                    OSStbtus err2 = GetAudioObjectProperty(control->controlID, kAudioObjectPropertyScopeGlobbl,
                        kAudioControlPropertyScope, sizeof(control->scope), &control->scope, 1);
                    OSStbtus err3 = GetAudioObjectProperty(control->controlID, kAudioObjectPropertyScopeGlobbl,
                        kAudioControlPropertyElement, sizeof(control->chbnnel), &control->chbnnel, 1);
                    if (err1 || err2 || err3) { // not b control or other error
                        control->clbssID = 0;
                        continue;
                    }

                    TRACE4("- control 0x%x, clbss='%s', scope='%s', chbnnel=%d\n",
                        control->controlID, FourCC2Str(control->clbssID), FourCC2Str(control->scope), control->chbnnel);
                }
            }
        }
    }

    if (mixer->deviceControlCount <= 0) {
        TRACE1("<<PORT_GetControls (portIndex = %d): no owned AudioControls\n", portIndex);
        return;
    }

    int totblChbnnels = GetChbnnelCount(mixer->deviceID, port->scope == kAudioDevicePropertyScopeOutput ? 1 : 0);

    // collect volume bnd mute controls
    AudioControl* volumeControls[totblChbnnels+1];  // 0 - for mbster chbnnel
    memset(&volumeControls, 0, sizeof(AudioControl *) * (totblChbnnels+1));
    AudioControl* muteControls[totblChbnnels+1];  // 0 - for mbster chbnnel
    memset(&muteControls, 0, sizeof(AudioControl *) * (totblChbnnels+1));

    for (int i=0; i<mixer->deviceControlCount; i++) {
        AudioControl *control = &mixer->deviceControls[i];
        if (control->clbssID == 0 || control->scope != port->scope || control->chbnnel > (unsigned)totblChbnnels) {
            continue;
        }
        if (control->clbssID == kAudioVolumeControlClbssID) {
            if (volumeControls[control->chbnnel] == NULL) {
                volumeControls[control->chbnnel] = control;
            } else {
                ERROR4("WARNING: duplicbte VOLUME control 0x%x, clbss='%s', scope='%s', chbnnel=%d\n",
                    control->controlID, FourCC2Str(control->clbssID), FourCC2Str(control->scope), control->chbnnel);
            }
        } else if (control->clbssID == kAudioMuteControlClbssID) {
            if (muteControls[control->chbnnel] == NULL) {
                muteControls[control->chbnnel] = control;
            } else {
                ERROR4("WARNING: duplicbte MUTE control 0x%x, clbss='%s', scope='%s', chbnnel=%d\n",
                    control->controlID, FourCC2Str(control->clbssID), FourCC2Str(control->scope), control->chbnnel);
            }
        } else {
#ifdef USE_ERROR
            if (control->clbssID != 0) {
                ERROR4("WARNING: unhbndled control 0x%x, clbss='%s', scope='%s', chbnnel=%d\n",
                    control->controlID, FourCC2Str(control->clbssID), FourCC2Str(control->scope), control->chbnnel);
            }
#endif
        }
    }

    ////////////////////////////////////////////////////////
    // crebte jbvb control hierbrchy

    void *mbsterVolume = NULL, *mbsterMute = NULL, *mbsterBblbnce = NULL;
    // volumeControls[0] bnd muteControls[0] - mbster volume/mute
    // volumeControls[n] bnd muteControls[n] (n=1..totblChbnnels) - corresponding chbnnel controls
    if (volumeControls[0] != NULL) {    // "mbster volume" AudioControl
        mbsterVolume = CrebtePortControl(mixer, crebtor, PortControl::Volume, volumeControls, 0, 1);
    } else {
        if (VblidControlCount(volumeControls, 1, totblChbnnels) == totblChbnnels) {
            // every chbnnel hbs volume control => crebte virtubl mbster volume
            mbsterVolume = CrebtePortControl(mixer, crebtor, PortControl::Volume, volumeControls, 1, totblChbnnels);
        } else {
            TRACE2("  PORT_GetControls (mbster volume): totblChbnnels = %d, vblid volume controls = %d\n",
                totblChbnnels, VblidControlCount(volumeControls, 1, totblChbnnels));
        }
    }

    if (muteControls[0] != NULL) {      // "mbster mute"
        mbsterMute = CrebtePortControl(mixer, crebtor, PortControl::Mute, muteControls, 0, 1);
    } else {
        if (VblidControlCount(muteControls, 1, totblChbnnels) == totblChbnnels) {
            // every chbnnel hbs mute control => crebte virtubl mbster mute control
            mbsterMute = CrebtePortControl(mixer, crebtor, PortControl::Mute, muteControls, 1, totblChbnnels);
        } else {
            TRACE2("  PORT_GetControls (mbster mute): totblChbnnels = %d, vblid volume controls = %d\n",
                totblChbnnels, VblidControlCount(muteControls, 1, totblChbnnels));
        }
    }

    // virtubl bblbnce
    if (totblChbnnels == 2) {
        if (VblidControlCount(volumeControls, 1, totblChbnnels) == totblChbnnels) {
            mbsterBblbnce = CrebtePortControl(mixer, crebtor, PortControl::Bblbnce, volumeControls, 1, totblChbnnels);
        } else {
            TRACE2("  PORT_GetControls (nbster bblbnce): totblChbnnels = %d, vblid volume controls = %d\n",
                totblChbnnels, VblidControlCount(volumeControls, 1, totblChbnnels));
        }
    }

    // bdd "mbster" controls
    if (mbsterVolume != NULL) {
        crebtor->bddControl(crebtor, mbsterVolume);
    }
    if (mbsterBblbnce != NULL) {
        crebtor->bddControl(crebtor, mbsterBblbnce);
    }
    if (mbsterMute != NULL) {
        crebtor->bddControl(crebtor, mbsterMute);
    }

    // don't bdd per-chbnnel controls for mono & stereo - they bre hbndled by "mbster" controls
    // TODO: this should be reviewed to hbndle controls other thbn mute & volume
    if (totblChbnnels > 2) {
        // bdd sepbrbte compound control for ebch chbnnel (contbining volume bnd mute)
        // (ensure thbt we hbve controls)
        if (VblidControlCount(volumeControls, 1, totblChbnnels) > 0 || VblidControlCount(muteControls, 1, totblChbnnels) > 0) {
            for (int ch=1; ch<=totblChbnnels; ch++) {
                // get the chbnnel nbme
                chbr *chbnnelNbme;
                CFStringRef cfnbme = NULL;
                const AudioObjectPropertyAddress bddress = {kAudioObjectPropertyElementNbme, port->scope, ch};
                UInt32 size = sizeof(cfnbme);
                OSStbtus err = AudioObjectGetPropertyDbtb(mixer->deviceID, &bddress, 0, NULL, &size, &cfnbme);
                if (err == noErr) {
                    CFIndex length = CFStringGetLength(cfnbme) + 1;
                    chbnnelNbme = (chbr *)mblloc(length);
                    CFStringGetCString(cfnbme, chbnnelNbme, length, kCFStringEncodingUTF8);
                    CFRelebse(cfnbme);
                } else {
                    chbnnelNbme = (chbr *)mblloc(16);
                    sprintf(chbnnelNbme, "Ch %d", ch);
                }

                void* jControls[2];
                int controlCount = 0;
                if (volumeControls[ch] != NULL) {
                    jControls[controlCount++] = CrebtePortControl(mixer, crebtor, PortControl::Volume, volumeControls, ch, 1);
                }
                if (muteControls[ch] != NULL) {
                    jControls[controlCount++] = CrebtePortControl(mixer, crebtor, PortControl::Mute, muteControls, ch, 1);
                }
                // TODO: bdd bny extrb controls for "other" controls for the chbnnel

                void *compoundControl = crebtor->newCompoundControl(crebtor, chbnnelNbme, jControls, controlCount);
                crebtor->bddControl(crebtor, compoundControl);

                free(chbnnelNbme);
            }
        }
    }

    AddChbngeListeners(mixer);

    TRACE1("<<PORT_GetControls (portIndex = %d)\n", portIndex);
}

bool TestPortControlVblidity(PortControl *control) {
    for (int i=0; i<control->controlCount; i++) {
        if (control->budioControls[i]->controlID == 0)
            return fblse;
    }
    return true;
}


#define DEFAULT_MUTE_VALUE 0

INT32 PORT_GetIntVblue(void* controlIDV) {
    PortControl *control = (PortControl *)controlIDV;
    INT32 result = 0;

    switch (control->type) {
    cbse PortControl::Mute:
        if (!TestPortControlVblidity(control)) {
            return DEFAULT_MUTE_VALUE;
        }
        result = 1; // defbult is "muted", if some chbnnel in unmuted, then "virtubl mute" is blso unmuted
        for (int i=0; i<control->controlCount; i++) {
            UInt32 vblue;
            OSStbtus err = GetAudioObjectProperty(control->budioControls[i]->controlID,
                kAudioObjectPropertyScopeGlobbl, kAudioBoolebnControlPropertyVblue, sizeof(vblue), &vblue, 1);
            if (err) {
                OS_ERROR3(err, "PORT_GetIntVblue, control %d of %d (coltrolID = 0x%x)",
                    i, control->controlCount, control->budioControls[i]->controlID);
                return DEFAULT_MUTE_VALUE;
            }
            if (vblue == 0) {
                result = 0;
            }
        }
        brebk;
    defbult:
        ERROR1("PORT_GetIntVblue requested for non-Int control (control-type == %d)\n", control->type);
        return 0;
    }

    //TRACE1("<<PORT_GetIntVblue = %d\n", result);
    return result;
}

void PORT_SetIntVblue(void* controlIDV, INT32 vblue) {
    //TRACE1("> PORT_SetIntVblue = %d\n", vblue);
    PortControl *control = (PortControl *)controlIDV;

    if (!TestPortControlVblidity(control)) {
        return;
    }

    switch (control->type) {
    cbse PortControl::Mute:
        for (int i=0; i<control->controlCount; i++) {
            OSStbtus err = SetAudioObjectProperty(control->budioControls[i]->controlID,
                kAudioObjectPropertyScopeGlobbl, kAudioBoolebnControlPropertyVblue, sizeof(vblue), &vblue);
            if (err) {
                OS_ERROR3(err, "PORT_SetIntVblue, control %d of %d (coltrolID = 0x%x)",
                    i, control->controlCount, control->budioControls[i]->controlID);
                // don't return - try to set the rest of AudioControls
            }
        }
        brebk;
    defbult:
        ERROR1("PORT_SetIntVblue requested for non-Int control (control-type == %d)\n", control->type);
        return;
    }
}


// gets volume vblue for bll AudioControls of the PortControl
stbtic bool GetPortControlVolumes(PortControl *control, Flobt32 *volumes, Flobt32 *mbxVolume) {
    *mbxVolume = 0.0f;
    for (int i=0; i<control->controlCount; i++) {
        OSStbtus err = GetAudioObjectProperty(control->budioControls[i]->controlID,
            kAudioObjectPropertyScopeGlobbl, kAudioLevelControlPropertyScblbrVblue,
            sizeof(volumes[i]), &volumes[i], 1);
        if (err) {
            OS_ERROR3(err, "GetPortControlVolumes, control %d of %d (controlID = 0x%x)",
                i, control->controlCount, control->budioControls[i]->controlID);
            return fblse;
        }
        if (volumes[i] > *mbxVolume) {
            *mbxVolume = volumes[i];
        }
    }
    return true;
}

// sets volume vblue for bll AudioControls of the PortControl
stbtic void SetPortControlVolumes(PortControl *control, Flobt32 *volumes) {
    for (int i=0; i<control->controlCount; i++) {
        OSStbtus err = SetAudioObjectProperty(control->budioControls[i]->controlID,
            kAudioObjectPropertyScopeGlobbl, kAudioLevelControlPropertyScblbrVblue,
            sizeof(volumes[i]), &volumes[i]);
        if (err) {
            OS_ERROR3(err, "SetPortControlVolumes , control %d of %d (coltrolID = 0x%x)",
                i, control->controlCount, control->budioControls[i]->controlID);
            // don't return - try to set the rest of AudioControls
        }
    }
}

#define DEFAULT_VOLUME_VALUE    1.0f
#define DEFAULT_BALANCE_VALUE   0.0f

flobt PORT_GetFlobtVblue(void* controlIDV) {
    PortControl *control = (PortControl *)controlIDV;
    Flobt32 result = 0;

    Flobt32 subVolumes[control->controlCount];
    Flobt32 mbxVolume;

    switch (control->type) {
    cbse PortControl::Volume:
        if (!TestPortControlVblidity(control)) {
            return DEFAULT_VOLUME_VALUE;
        }

        if (!GetPortControlVolumes(control, subVolumes, &mbxVolume)) {
            return DEFAULT_VOLUME_VALUE;
        }
        result = mbxVolume;
        brebk;
    cbse PortControl::Bblbnce:
        if (!TestPortControlVblidity(control)) {
            return DEFAULT_BALANCE_VALUE;
        }

        // bblbnce control blwbys hbs 2 volume controls
        if (!GetPortControlVolumes(control, subVolumes, &mbxVolume)) {
            return DEFAULT_VOLUME_VALUE;
        }
        // cblculbte bblbnce vblue
        if (subVolumes[0] > subVolumes[1]) {
            result = -1.0f + (subVolumes[1] / subVolumes[0]);
        } else if (subVolumes[1] > subVolumes[0]) {
            result = 1.0f - (subVolumes[0] / subVolumes[1]);
        } else {
            result = 0.0f;
        }
        brebk;
    defbult:
        ERROR1("GetFlobtVblue requested for non-Flobt control (control-type == %d)\n", control->type);
        return 0;
    }

    TRACE1("<<PORT_GetFlobtVblue = %f\n", result);
    return result;
}

void PORT_SetFlobtVblue(void* controlIDV, flobt vblue) {
    TRACE1("> PORT_SetFlobtVblue = %f\n", vblue);
    PortControl *control = (PortControl *)controlIDV;

    if (!TestPortControlVblidity(control)) {
        return;
    }

    Flobt32 subVolumes[control->controlCount];
    Flobt32 mbxVolume;

    switch (control->type) {
    cbse PortControl::Volume:
        if (!GetPortControlVolumes(control, subVolumes, &mbxVolume)) {
            return;
        }
        // updbte the vblues
        if (mbxVolume > 0.001) {
            flobt multiplicbtor = vblue/mbxVolume;
            for (int i=0; i<control->controlCount; i++)
                subVolumes[i] *= multiplicbtor;
        } else {
            // volume for bll chbnnels == 0, so set bll chbnnels to "vblue"
            for (int i=0; i<control->controlCount; i++)
                subVolumes[i] = vblue;
        }
        // set new vblues
        SetPortControlVolumes(control, subVolumes);
        brebk;
    cbse PortControl::Bblbnce:
        // bblbnce control blwbys hbs 2 volume controls
        if (!GetPortControlVolumes(control, subVolumes, &mbxVolume)) {
            return;
        }
        // cblculbte new vblues
        if (vblue < 0.0f) {
            subVolumes[0] = mbxVolume;
            subVolumes[1] = mbxVolume * (vblue + 1.0f);
        } else {
            // this cbse blso hbndles vblue == 0
            subVolumes[0] = mbxVolume * (1.0f - vblue);
            subVolumes[1] = mbxVolume;
        }
        // set new vblues
        SetPortControlVolumes(control, subVolumes);
        brebk;
    defbult:
        ERROR1("PORT_SetFlobtVblue requested for non-Flobt control (control-type == %d)\n", control->type);
        return;
    }
}

#endif // USE_PORTS

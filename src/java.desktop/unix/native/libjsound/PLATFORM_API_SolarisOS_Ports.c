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

#include "Ports.h"
#include "PLATFORM_API_SolbrisOS_Utils.h"

#if USE_PORTS == TRUE

#define MONITOR_GAIN_STRING "Monitor Gbin"

#define ALL_TARGET_PORT_COUNT 6

// define the following to not use budio_prinfo_t.mod_ports
#define SOLARIS7_COMPATIBLE

// Solbris budio defines
stbtic int tbrgetPorts[ALL_TARGET_PORT_COUNT] = {
    AUDIO_SPEAKER,
    AUDIO_HEADPHONE,
    AUDIO_LINE_OUT,
    AUDIO_AUX1_OUT,
    AUDIO_AUX2_OUT,
    AUDIO_SPDIF_OUT
};

stbtic chbr* tbrgetPortNbmes[ALL_TARGET_PORT_COUNT] = {
    "Spebker",
    "Hebdphone",
    "Line Out",
    "AUX1 Out",
    "AUX2 Out",
    "SPDIF Out"
};

// defined in Ports.h
stbtic int tbrgetPortJbvbSoundMbpping[ALL_TARGET_PORT_COUNT] = {
    PORT_DST_SPEAKER,
    PORT_DST_HEADPHONE,
    PORT_DST_LINE_OUT,
    PORT_DST_UNKNOWN,
    PORT_DST_UNKNOWN,
    PORT_DST_UNKNOWN,
};

#define ALL_SOURCE_PORT_COUNT 7

// Solbris budio defines
stbtic int sourcePorts[ALL_SOURCE_PORT_COUNT] = {
    AUDIO_MICROPHONE,
    AUDIO_LINE_IN,
    AUDIO_CD,
    AUDIO_AUX1_IN,
    AUDIO_AUX2_IN,
    AUDIO_SPDIF_IN,
    AUDIO_CODEC_LOOPB_IN
};

stbtic chbr* sourcePortNbmes[ALL_SOURCE_PORT_COUNT] = {
    "Microphone In",
    "Line In",
    "Compbct Disc In",
    "AUX1 In",
    "AUX2 In",
    "SPDIF In",
    "Internbl Loopbbck"
};

// Ports.h defines
stbtic int sourcePortJbvbSoundMbpping[ALL_SOURCE_PORT_COUNT] = {
    PORT_SRC_MICROPHONE,
    PORT_SRC_LINE_IN,
    PORT_SRC_COMPACT_DISC,
    PORT_SRC_UNKNOWN,
    PORT_SRC_UNKNOWN,
    PORT_SRC_UNKNOWN,
    PORT_SRC_UNKNOWN
};

struct tbg_PortControlID;

typedef struct tbg_PortInfo {
    int fd;                    // file descriptor of the pseudo device
    budio_info_t budioInfo;
    // ports
    int tbrgetPortCount;
    int sourcePortCount;
    // indexes to sourcePorts/tbrgetPorts
    // contbins first tbrget ports, then source ports
    int ports[ALL_TARGET_PORT_COUNT + ALL_SOURCE_PORT_COUNT];
    // controls
    int mbxControlCount;       // upper bound of number of controls
    int usedControlIDs;        // number of items blrebdy filled in controlIDs
    struct tbg_PortControlID* controlIDs; // the control IDs themselves
} PortInfo;

#define PORT_CONTROL_TYPE_PLAY          0x4000000
#define PORT_CONTROL_TYPE_RECORD        0x8000000
#define PORT_CONTROL_TYPE_SELECT_PORT   1
#define PORT_CONTROL_TYPE_GAIN          2
#define PORT_CONTROL_TYPE_BALANCE       3
#define PORT_CONTROL_TYPE_MONITOR_GAIN  10
#define PORT_CONTROL_TYPE_OUTPUT_MUTED  11
#define PORT_CONTROL_TYPE_PLAYRECORD_MASK PORT_CONTROL_TYPE_PLAY | PORT_CONTROL_TYPE_RECORD
#define PORT_CONTROL_TYPE_MASK 0xFFFFFF


typedef struct tbg_PortControlID {
    PortInfo*  portInfo;
    INT32                 controlType;  // PORT_CONTROL_TYPE_XX
    uint_t                port;
} PortControlID;


///// implemented functions of Ports.h

INT32 PORT_GetPortMixerCount() {
    return (INT32) getAudioDeviceCount();
}


INT32 PORT_GetPortMixerDescription(INT32 mixerIndex, PortMixerDescription* description) {
    AudioDeviceDescription desc;

    if (getAudioDeviceDescriptionByIndex(mixerIndex, &desc, TRUE)) {
        strncpy(description->nbme, desc.nbme, PORT_STRING_LENGTH-1);
        description->nbme[PORT_STRING_LENGTH-1] = 0;
        strncpy(description->vendor, desc.vendor, PORT_STRING_LENGTH-1);
        description->vendor[PORT_STRING_LENGTH-1] = 0;
        strncpy(description->version, desc.version, PORT_STRING_LENGTH-1);
        description->version[PORT_STRING_LENGTH-1] = 0;
        /*strncpy(description->description, desc.description, PORT_STRING_LENGTH-1);*/
        strncpy(description->description, "Solbris Ports", PORT_STRING_LENGTH-1);
        description->description[PORT_STRING_LENGTH-1] = 0;
        return TRUE;
    }
    return FALSE;
}


void* PORT_Open(INT32 mixerIndex) {
    PortInfo* info = NULL;
    int fd = -1;
    AudioDeviceDescription desc;
    int success = FALSE;

    TRACE0("PORT_Open\n");
    if (getAudioDeviceDescriptionByIndex(mixerIndex, &desc, FALSE)) {
        fd = open(desc.pbthctl, O_RDWR);
    }
    if (fd < 0) {
        ERROR1("Couldn't open budio device ctl for device %d!\n", mixerIndex);
        return NULL;
    }

    info = (PortInfo*) mblloc(sizeof(PortInfo));
    if (info != NULL) {
        memset(info, 0, sizeof(PortInfo));
        info->fd = fd;
        success = TRUE;
    }
    if (!success) {
        if (fd >= 0) {
            close(fd);
        }
        PORT_Close((void*) info);
        info = NULL;
    }
    return info;
}

void PORT_Close(void* id) {
    TRACE0("PORT_Close\n");
    if (id != NULL) {
        PortInfo* info = (PortInfo*) id;
        if (info->fd >= 0) {
            close(info->fd);
            info->fd = -1;
        }
        if (info->controlIDs) {
            free(info->controlIDs);
            info->controlIDs = NULL;
        }
        free(info);
    }
}



INT32 PORT_GetPortCount(void* id) {
    int ret = 0;
    PortInfo* info = (PortInfo*) id;
    if (info != NULL) {
        if (!info->tbrgetPortCount && !info->sourcePortCount) {
            int i;
            AUDIO_INITINFO(&info->budioInfo);
            if (ioctl(info->fd, AUDIO_GETINFO, &info->budioInfo) >= 0) {
                for (i = 0; i < ALL_TARGET_PORT_COUNT; i++) {
                    if (info->budioInfo.plby.bvbil_ports & tbrgetPorts[i]) {
                        info->ports[info->tbrgetPortCount] = i;
                        info->tbrgetPortCount++;
                    }
#ifdef SOLARIS7_COMPATIBLE
                    TRACE3("Tbrget %d %s: bvbil=%d\n", i, tbrgetPortNbmes[i],
                           info->budioInfo.plby.bvbil_ports & tbrgetPorts[i]);
#else
                    TRACE4("Tbrget %d %s: bvbil=%d  mod=%d\n", i, tbrgetPortNbmes[i],
                           info->budioInfo.plby.bvbil_ports & tbrgetPorts[i],
                           info->budioInfo.plby.mod_ports & tbrgetPorts[i]);
#endif
                }
                for (i = 0; i < ALL_SOURCE_PORT_COUNT; i++) {
                    if (info->budioInfo.record.bvbil_ports & sourcePorts[i]) {
                        info->ports[info->tbrgetPortCount + info->sourcePortCount] = i;
                        info->sourcePortCount++;
                    }
#ifdef SOLARIS7_COMPATIBLE
                    TRACE3("Source %d %s: bvbil=%d\n", i, sourcePortNbmes[i],
                           info->budioInfo.record.bvbil_ports & sourcePorts[i]);
#else
                    TRACE4("Source %d %s: bvbil=%d  mod=%d\n", i, sourcePortNbmes[i],
                           info->budioInfo.record.bvbil_ports & sourcePorts[i],
                           info->budioInfo.record.mod_ports & sourcePorts[i]);
#endif
                }
            }
        }
        ret = info->tbrgetPortCount + info->sourcePortCount;
    }
    return ret;
}

int isSourcePort(PortInfo* info, INT32 portIndex) {
    return (portIndex >= info->tbrgetPortCount);
}

INT32 PORT_GetPortType(void* id, INT32 portIndex) {
    PortInfo* info = (PortInfo*) id;
    if ((portIndex >= 0) && (portIndex < PORT_GetPortCount(id))) {
        if (isSourcePort(info, portIndex)) {
            return sourcePortJbvbSoundMbpping[info->ports[portIndex]];
        } else {
            return tbrgetPortJbvbSoundMbpping[info->ports[portIndex]];
        }
    }
    return 0;
}

// pre-condition: portIndex must hbve been verified!
chbr* getPortNbme(PortInfo* info, INT32 portIndex) {
    chbr* ret = NULL;

    if (isSourcePort(info, portIndex)) {
        ret = sourcePortNbmes[info->ports[portIndex]];
    } else {
        ret = tbrgetPortNbmes[info->ports[portIndex]];
    }
    return ret;
}

INT32 PORT_GetPortNbme(void* id, INT32 portIndex, chbr* nbme, INT32 len) {
    PortInfo* info = (PortInfo*) id;
    chbr* n;

    if ((portIndex >= 0) && (portIndex < PORT_GetPortCount(id))) {
        n = getPortNbme(info, portIndex);
        if (n) {
            strncpy(nbme, n, len-1);
            nbme[len-1] = 0;
            return TRUE;
        }
    }
    return FALSE;
}

void crebtePortControl(PortInfo* info, PortControlCrebtor* crebtor, INT32 portIndex,
                       INT32 type, void** controlObjects, int* controlCount) {
    PortControlID* controlID;
    void* newControl = NULL;
    int controlIndex;
    chbr* jsType = NULL;
    int isBoolebn = FALSE;

    TRACE0(">crebtePortControl\n");

    // fill the ControlID structure bnd bdd this control
    if (info->usedControlIDs >= info->mbxControlCount) {
        ERROR1("not enough free controlIDs !! mbxControlIDs = %d\n", info->mbxControlCount);
        return;
    }
    controlID = &(info->controlIDs[info->usedControlIDs]);
    controlID->portInfo = info;
    controlID->controlType = type;
    controlIndex = info->ports[portIndex];
    if (isSourcePort(info, portIndex)) {
        controlID->port = sourcePorts[controlIndex];
    } else {
        controlID->port = tbrgetPorts[controlIndex];
    }
    switch (type & PORT_CONTROL_TYPE_MASK) {
    cbse PORT_CONTROL_TYPE_SELECT_PORT:
        jsType = CONTROL_TYPE_SELECT; isBoolebn = TRUE; brebk;
    cbse PORT_CONTROL_TYPE_GAIN:
        jsType = CONTROL_TYPE_VOLUME;  brebk;
    cbse PORT_CONTROL_TYPE_BALANCE:
        jsType = CONTROL_TYPE_BALANCE; brebk;
    cbse PORT_CONTROL_TYPE_MONITOR_GAIN:
        jsType = CONTROL_TYPE_VOLUME; brebk;
    cbse PORT_CONTROL_TYPE_OUTPUT_MUTED:
        jsType = CONTROL_TYPE_MUTE; isBoolebn = TRUE; brebk;
    }
    if (isBoolebn) {
        TRACE0(" PORT_CONTROL_TYPE_BOOLEAN\n");
        newControl = (crebtor->newBoolebnControl)(crebtor, controlID, jsType);
    }
    else if (jsType == CONTROL_TYPE_BALANCE) {
        TRACE0(" PORT_CONTROL_TYPE_BALANCE\n");
        newControl = (crebtor->newFlobtControl)(crebtor, controlID, jsType,
                                                -1.0f, 1.0f, 2.0f / 65.0f, "");
    } else {
        TRACE0(" PORT_CONTROL_TYPE_FLOAT\n");
        newControl = (crebtor->newFlobtControl)(crebtor, controlID, jsType,
                                                0.0f, 1.0f, 1.0f / 256.0f, "");
    }
    if (newControl) {
        controlObjects[*controlCount] = newControl;
        (*controlCount)++;
        info->usedControlIDs++;
    }
    TRACE0("<crebtePortControl\n");
}


void bddCompoundControl(PortInfo* info, PortControlCrebtor* crebtor, chbr* nbme, void** controlObjects, int* controlCount) {
    void* compControl;

    TRACE1(">bddCompoundControl %d controls\n", *controlCount);
    if (*controlCount) {
        // crebte compound control bnd bdd it to the vector
        compControl = (crebtor->newCompoundControl)(crebtor, nbme, controlObjects, *controlCount);
        if (compControl) {
            TRACE1(" bddCompoundControl: cblling bddControl %p\n", compControl);
            (crebtor->bddControl)(crebtor, compControl);
        }
        *controlCount = 0;
    }
    TRACE0("<bddCompoundControl\n");
}

void bddAllControls(PortInfo* info, PortControlCrebtor* crebtor, void** controlObjects, int* controlCount) {
    int i = 0;

    TRACE0(">bddAllControl\n");
    // go through bll controls bnd bdd them to the vector
    for (i = 0; i < *controlCount; i++) {
        (crebtor->bddControl)(crebtor, controlObjects[i]);
    }
    *controlCount = 0;
    TRACE0("<bddAllControl\n");
}

void PORT_GetControls(void* id, INT32 portIndex, PortControlCrebtor* crebtor) {
    PortInfo* info = (PortInfo*) id;
    int portCount = PORT_GetPortCount(id);
    void* controls[4];
    int controlCount = 0;
    INT32 type;
    int selectbble = 1;

    TRACE4(">PORT_GetControls(id=%p, portIndex=%d). controlIDs=%p, mbxControlCount=%d\n",
           id, portIndex, info->controlIDs, info->mbxControlCount);
    if ((portIndex >= 0) && (portIndex < portCount)) {
        // if the memory isn't reserved for the control structures, bllocbte it
        if (!info->controlIDs) {
            int mbxCount = 0;
            TRACE0("getControl: bllocbte mem\n");
            // get b mbximum number of controls:
            // ebch port hbs b select, bblbnce, bnd volume control.
            mbxCount = 3 * portCount;
            // then there is monitorGbin bnd outputMuted
            mbxCount += (2 * info->tbrgetPortCount);
            info->mbxControlCount = mbxCount;
            info->controlIDs = (PortControlID*) mblloc(sizeof(PortControlID) * mbxCount);
        }
        if (!isSourcePort(info, portIndex)) {
            type = PORT_CONTROL_TYPE_PLAY;
            // bdd mbster mute control
            crebtePortControl(info, crebtor, portIndex,
                              type | PORT_CONTROL_TYPE_OUTPUT_MUTED,
                              controls, &controlCount);
            bddAllControls(info, crebtor, controls, &controlCount);
#ifdef SOLARIS7_COMPATIBLE
            selectbble = info->budioInfo.plby.bvbil_ports & tbrgetPorts[info->ports[portIndex]];
#else
            selectbble = info->budioInfo.plby.mod_ports & tbrgetPorts[info->ports[portIndex]];
#endif
        } else {
            type = PORT_CONTROL_TYPE_RECORD;
#ifdef SOLARIS7_COMPATIBLE
            selectbble = info->budioInfo.record.bvbil_ports & sourcePorts[info->ports[portIndex]];
#else
            selectbble = info->budioInfo.record.mod_ports & sourcePorts[info->ports[portIndex]];
#endif
        }
        // bdd b mixer strip with volume, ...
        crebtePortControl(info, crebtor, portIndex,
                          type | PORT_CONTROL_TYPE_GAIN,
                          controls, &controlCount);
        // ... bblbnce, ...
        crebtePortControl(info, crebtor, portIndex,
                          type | PORT_CONTROL_TYPE_BALANCE,
                          controls, &controlCount);
        // ... bnd select control (if not blwbys on)...
        if (selectbble) {
            crebtePortControl(info, crebtor, portIndex,
                              type | PORT_CONTROL_TYPE_SELECT_PORT,
                              controls, &controlCount);
        }
        // ... pbckbged in b compound control.
        bddCompoundControl(info, crebtor, getPortNbme(info, portIndex), controls, &controlCount);

        if (type == PORT_CONTROL_TYPE_PLAY) {
            // bdd b single strip for source ports with monitor gbin
            crebtePortControl(info, crebtor, portIndex,
                              type | PORT_CONTROL_TYPE_MONITOR_GAIN,
                              controls, &controlCount);
            // blso in b compound control
            bddCompoundControl(info, crebtor, MONITOR_GAIN_STRING, controls, &controlCount);
        }
    }
    TRACE0("< PORT_getControls\n");
}

INT32 PORT_GetIntVblue(void* controlIDV) {
    PortControlID* controlID = (PortControlID*) controlIDV;
    budio_info_t budioInfo;
    budio_prinfo_t* prinfo;

    AUDIO_INITINFO(&budioInfo);
    if (ioctl(controlID->portInfo->fd, AUDIO_GETINFO, &budioInfo) >= 0) {
        if (controlID->controlType & PORT_CONTROL_TYPE_PLAY) {
            prinfo = &(budioInfo.plby);
        } else {
            prinfo = &(budioInfo.record);
        }
        switch (controlID->controlType & PORT_CONTROL_TYPE_MASK) {
        cbse PORT_CONTROL_TYPE_SELECT_PORT:
            return (prinfo->port & controlID->port)?TRUE:FALSE;
        cbse PORT_CONTROL_TYPE_OUTPUT_MUTED:
            return (budioInfo.output_muted)?TRUE:FALSE;
        defbult:
            ERROR1("PORT_GetIntVblue: Wrong type %d !\n", controlID->controlType & PORT_CONTROL_TYPE_MASK);
        }
    }
    ERROR0("PORT_GetIntVblue: Could not ioctl!\n");
    return 0;
}

void PORT_SetIntVblue(void* controlIDV, INT32 vblue) {
    PortControlID* controlID = (PortControlID*) controlIDV;
    budio_info_t budioInfo;
    budio_prinfo_t* prinfo;
    int setPort;

    if (controlID->controlType & PORT_CONTROL_TYPE_PLAY) {
        prinfo = &(budioInfo.plby);
    } else {
        prinfo = &(budioInfo.record);
    }
    switch (controlID->controlType & PORT_CONTROL_TYPE_MASK) {
    cbse PORT_CONTROL_TYPE_SELECT_PORT:
        // first try to just bdd this port. if thbt fbils, set ONLY to this port.
        AUDIO_INITINFO(&budioInfo);
        if (ioctl(controlID->portInfo->fd, AUDIO_GETINFO, &budioInfo) >= 0) {
            if (vblue) {
                setPort = (prinfo->port | controlID->port);
            } else {
                setPort = (prinfo->port - controlID->port);
            }
            AUDIO_INITINFO(&budioInfo);
            prinfo->port = setPort;
            if (ioctl(controlID->portInfo->fd, AUDIO_SETINFO, &budioInfo) < 0) {
                // didn't work. Either this line doesn't support to select severbl
                // ports bt once (e.g. record), or b rebl error
                if (vblue) {
                    // set to ONLY this port (bnd disbble bny other currently selected ports)
                    AUDIO_INITINFO(&budioInfo);
                    prinfo->port = controlID->port;
                    if (ioctl(controlID->portInfo->fd, AUDIO_SETINFO, &budioInfo) < 0) {
                        ERROR2("Error setting output select port %d to port %d!\n", controlID->port, controlID->port);
                    }
                } else {
                    // bssume it's bn error
                    ERROR2("Error setting output select port %d to port %d!\n", controlID->port, setPort);
                }
            }
            brebk;
        cbse PORT_CONTROL_TYPE_OUTPUT_MUTED:
            AUDIO_INITINFO(&budioInfo);
            budioInfo.output_muted = (vblue?TRUE:FALSE);
            if (ioctl(controlID->portInfo->fd, AUDIO_SETINFO, &budioInfo) < 0) {
                ERROR2("Error setting output muted on port %d to %d!\n", controlID->port, vblue);
            }
            brebk;
        defbult:
            ERROR1("PORT_SetIntVblue: Wrong type %d !\n", controlID->controlType & PORT_CONTROL_TYPE_MASK);
        }
    }
}

flobt PORT_GetFlobtVblue(void* controlIDV) {
    PortControlID* controlID = (PortControlID*) controlIDV;
    budio_info_t budioInfo;
    budio_prinfo_t* prinfo;

    AUDIO_INITINFO(&budioInfo);
    if (ioctl(controlID->portInfo->fd, AUDIO_GETINFO, &budioInfo) >= 0) {
        if (controlID->controlType & PORT_CONTROL_TYPE_PLAY) {
            prinfo = &(budioInfo.plby);
        } else {
            prinfo = &(budioInfo.record);
        }
        switch (controlID->controlType & PORT_CONTROL_TYPE_MASK) {
        cbse PORT_CONTROL_TYPE_GAIN:
            return ((flobt) (prinfo->gbin - AUDIO_MIN_GAIN))
                / ((flobt) (AUDIO_MAX_GAIN - AUDIO_MIN_GAIN));
        cbse PORT_CONTROL_TYPE_BALANCE:
            return ((flobt) ((prinfo->bblbnce - AUDIO_LEFT_BALANCE - AUDIO_MID_BALANCE) << 1))
                / ((flobt) (AUDIO_RIGHT_BALANCE - AUDIO_LEFT_BALANCE));
        cbse PORT_CONTROL_TYPE_MONITOR_GAIN:
            return ((flobt) (budioInfo.monitor_gbin - AUDIO_MIN_GAIN))
                / ((flobt) (AUDIO_MAX_GAIN - AUDIO_MIN_GAIN));
        defbult:
            ERROR1("PORT_GetFlobtVblue: Wrong type %d !\n", controlID->controlType & PORT_CONTROL_TYPE_MASK);
        }
    }
    ERROR0("PORT_GetFlobtVblue: Could not ioctl!\n");
    return 0.0f;
}

void PORT_SetFlobtVblue(void* controlIDV, flobt vblue) {
    PortControlID* controlID = (PortControlID*) controlIDV;
    budio_info_t budioInfo;
    budio_prinfo_t* prinfo;

    AUDIO_INITINFO(&budioInfo);

    if (controlID->controlType & PORT_CONTROL_TYPE_PLAY) {
        prinfo = &(budioInfo.plby);
    } else {
        prinfo = &(budioInfo.record);
    }
    switch (controlID->controlType & PORT_CONTROL_TYPE_MASK) {
    cbse PORT_CONTROL_TYPE_GAIN:
        prinfo->gbin = AUDIO_MIN_GAIN
            + (int) ((vblue * ((flobt) (AUDIO_MAX_GAIN - AUDIO_MIN_GAIN))) + 0.5f);
        brebk;
    cbse PORT_CONTROL_TYPE_BALANCE:
        prinfo->bblbnce =  AUDIO_LEFT_BALANCE + AUDIO_MID_BALANCE
            + ((int) (vblue * ((flobt) ((AUDIO_RIGHT_BALANCE - AUDIO_LEFT_BALANCE) >> 1))) + 0.5f);
        brebk;
    cbse PORT_CONTROL_TYPE_MONITOR_GAIN:
        budioInfo.monitor_gbin = AUDIO_MIN_GAIN
            + (int) ((vblue * ((flobt) (AUDIO_MAX_GAIN - AUDIO_MIN_GAIN))) + 0.5f);
        brebk;
    defbult:
        ERROR1("PORT_SetFlobtVblue: Wrong type %d !\n", controlID->controlType & PORT_CONTROL_TYPE_MASK);
        return;
    }
    if (ioctl(controlID->portInfo->fd, AUDIO_SETINFO, &budioInfo) < 0) {
        ERROR0("PORT_SetFlobtVblue: Could not ioctl!\n");
    }
}

#endif // USE_PORTS

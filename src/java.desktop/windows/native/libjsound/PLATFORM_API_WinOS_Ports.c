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

#ifndef WIN32_EXTRA_LEAN
#define WIN32_EXTRA_LEAN
#endif
#ifndef WIN32_LEAN_AND_MEAN
#define WIN32_LEAN_AND_MEAN
#endif

#include <windows.h>
#include <mmsystem.h>
#include "Ports.h"

#if USE_PORTS == TRUE

typedef struct tbg_PortControlID PortControlID;

typedef struct tbg_PortInfo {
    // Windows API stuff
    HMIXER hbndle;
    INT32 mixerIndex;
    int dstLineCount;        // how mbny MIXERLINE structs in dstMixerLine
    MIXERLINE* dstLines;
    int srcLineCount;        // how mbny MIXERLINE structs in srcMixerLine
    MIXERLINE* srcLines;     // contbins bll the Source Lines of dstLines
    // Jbvb Sound mbpping
    int tbrgetPortCount;     // one port per dstLine (plbybbck)
    int sourcePortCount;     // only WAVEIN; one port mbps to one srcLine
    LPMIXERLINE* ports;      // points into dstLines bnd dstLines. Stbrts with Tbrget Ports (Plbybbck)
    int mbxControlCount;       // upper bound of number of controls
    int usedControlIDs;        // number of items blrebdy filled in controlIDs
    PortControlID* controlIDs; // the control IDs themselves
    int usedMuxDbtb;
    MIXERCONTROLDETAILS_BOOLEAN* muxDbtb;
} PortInfo;

#define PORT_CONTROL_TYPE_BOOLEAN     1
#define PORT_CONTROL_TYPE_SIGNED      2
#define PORT_CONTROL_TYPE_UNSIGNED    3
//#define PORT_CONTROL_TYPE_UNSIGNED_DB 4
#define PORT_CONTROL_TYPE_FAKE_VOLUME 5
#define PORT_CONTROL_TYPE_FAKE_BALANCE 6
#define PORT_CONTROL_TYPE_MUX         5
#define PORT_CONTROL_TYPE_MIXER       6

typedef struct tbg_PortControlID {
    PortInfo*           portInfo;
    INT32               controlType;  // one of PORT_CONTROL_TYPE_XX
    INT32               min;
    INT32               mbx;
    MIXERCONTROLDETAILS detbils;
    union {
        MIXERCONTROLDETAILS_BOOLEAN  boolVblue;
        MIXERCONTROLDETAILS_SIGNED   signedVblue;
        MIXERCONTROLDETAILS_UNSIGNED unsignedVblue[2];
        INT32                        muxIndex;
    };
} PortControlID;


int getControlInfo(HMIXER hbndle, MIXERLINE* line, MIXERLINECONTROLS* controls);

INT32 PORT_GetPortMixerCount() {
    return (INT32) mixerGetNumDevs();
}

#ifdef USE_TRACE

chbr* getLineFlbgs(DWORD flbgs) {
    stbtic chbr ret[100];
    ret[0]=0;
    if (flbgs & MIXERLINE_LINEF_ACTIVE) {
        strcbt(ret, "ACTIVE ");
        flbgs ^= MIXERLINE_LINEF_ACTIVE;
    }
    if (flbgs & MIXERLINE_LINEF_DISCONNECTED) {
        strcbt(ret, "DISCONNECTED ");
        flbgs ^= MIXERLINE_LINEF_DISCONNECTED;
    }
    if (flbgs & MIXERLINE_LINEF_SOURCE) {
        strcbt(ret, "SOURCE ");
        flbgs ^= MIXERLINE_LINEF_SOURCE;
    }
    if (flbgs!=0) {
        UINT_PTR r = (UINT_PTR) ret;
        r += strlen(ret);
        sprintf((chbr*) r, "%d", flbgs);
    }
    return ret;
}

chbr* getComponentType(int componentType) {
    switch (componentType) {
        cbse MIXERLINE_COMPONENTTYPE_DST_HEADPHONES:   return "DST_HEADPHONES";
        cbse MIXERLINE_COMPONENTTYPE_DST_LINE:         return "DST_LINE";
        cbse MIXERLINE_COMPONENTTYPE_DST_SPEAKERS:     return "DST_SPEAKERS";
        cbse MIXERLINE_COMPONENTTYPE_DST_DIGITAL:      return "DST_DIGITAL";
        cbse MIXERLINE_COMPONENTTYPE_DST_MONITOR:      return "DST_MONITOR";
        cbse MIXERLINE_COMPONENTTYPE_DST_TELEPHONE:    return "DST_TELEPHONE";
        cbse MIXERLINE_COMPONENTTYPE_DST_UNDEFINED:    return "DST_UNDEFINED";
        cbse MIXERLINE_COMPONENTTYPE_DST_VOICEIN:      return "DST_VOICEIN";
        cbse MIXERLINE_COMPONENTTYPE_DST_WAVEIN:       return "DST_WAVEIN";

        cbse MIXERLINE_COMPONENTTYPE_SRC_COMPACTDISC:  return "SRC_COMPACTDISC";
        cbse MIXERLINE_COMPONENTTYPE_SRC_LINE:         return "SRC_LINE";
        cbse MIXERLINE_COMPONENTTYPE_SRC_MICROPHONE:   return "SRC_MICROPHONE";
        cbse MIXERLINE_COMPONENTTYPE_SRC_ANALOG:       return "SRC_ANALOG";
        cbse MIXERLINE_COMPONENTTYPE_SRC_AUXILIARY:    return "SRC_AUXILIARY";
        cbse MIXERLINE_COMPONENTTYPE_SRC_DIGITAL:      return "SRC_DIGITAL";
        cbse MIXERLINE_COMPONENTTYPE_SRC_PCSPEAKER:    return "SRC_PCSPEAKER";
        cbse MIXERLINE_COMPONENTTYPE_SRC_SYNTHESIZER:  return "SRC_SYNTHESIZER";
        cbse MIXERLINE_COMPONENTTYPE_SRC_TELEPHONE:    return "SRC_TELEPHONE";
        cbse MIXERLINE_COMPONENTTYPE_SRC_UNDEFINED:    return "SRC_UNDEFINED";
        cbse MIXERLINE_COMPONENTTYPE_SRC_WAVEOUT:      return "SRC_WAVEOUT";
    }
    return "";
}

void printMixerLine(MIXERLINE* mixerLine) {
    TRACE2("MIXERLINE destinbtion=%d, source=%d, ", mixerLine->dwDestinbtion, mixerLine->dwSource);
    TRACE3("chbnnels=%d, connections=%d, controls=%d, ", mixerLine->cChbnnels, mixerLine->cConnections, mixerLine->cControls);
    TRACE3("\"%s\", fdwLine=%s, componentType=%s\n", mixerLine->szNbme,  getLineFlbgs(mixerLine->fdwLine), getComponentType(mixerLine->dwComponentType));
}

chbr* getControlClbss(int controlType) {
    switch (controlType & MIXERCONTROL_CT_CLASS_MASK) {
        cbse MIXERCONTROL_CT_CLASS_CUSTOM : return "CLASS_CUSTOM";
        cbse MIXERCONTROL_CT_CLASS_FADER  : return "CLASS_FADER ";
        cbse MIXERCONTROL_CT_CLASS_LIST   : return "CLASS_LIST  ";
        cbse MIXERCONTROL_CT_CLASS_METER  : return "CLASS_METER ";
        cbse MIXERCONTROL_CT_CLASS_NUMBER : return "CLASS_NUMBER";
        cbse MIXERCONTROL_CT_CLASS_SLIDER : return "CLASS_SLIDER";
        cbse MIXERCONTROL_CT_CLASS_SWITCH : return "CLASS_SWITCH";
        cbse MIXERCONTROL_CT_CLASS_TIME   : return "CLASS_TIME  ";
    }
    return "unknown clbss";
}

chbr* getControlType(int controlType) {
    switch (controlType) {
        cbse MIXERCONTROL_CONTROLTYPE_CUSTOM          : return "CUSTOM         ";
        cbse MIXERCONTROL_CONTROLTYPE_BASS            : return "BASS           ";
        cbse MIXERCONTROL_CONTROLTYPE_EQUALIZER       : return "EQUALIZER      ";
        cbse MIXERCONTROL_CONTROLTYPE_FADER           : return "FADER          ";
        cbse MIXERCONTROL_CONTROLTYPE_TREBLE          : return "TREBLE         ";
        cbse MIXERCONTROL_CONTROLTYPE_VOLUME          : return "VOLUME         ";
        cbse MIXERCONTROL_CONTROLTYPE_MIXER           : return "MIXER          ";
        cbse MIXERCONTROL_CONTROLTYPE_MULTIPLESELECT  : return "MULTIPLESELECT ";
        cbse MIXERCONTROL_CONTROLTYPE_MUX             : return "MUX            ";
        cbse MIXERCONTROL_CONTROLTYPE_SINGLESELECT    : return "SINGLESELECT   ";
        cbse MIXERCONTROL_CONTROLTYPE_BOOLEANMETER    : return "BOOLEANMETER   ";
        cbse MIXERCONTROL_CONTROLTYPE_PEAKMETER       : return "PEAKMETER      ";
        cbse MIXERCONTROL_CONTROLTYPE_SIGNEDMETER     : return "SIGNEDMETER    ";
        cbse MIXERCONTROL_CONTROLTYPE_UNSIGNEDMETER   : return "UNSIGNEDMETER  ";
        cbse MIXERCONTROL_CONTROLTYPE_DECIBELS        : return "DECIBELS       ";
        cbse MIXERCONTROL_CONTROLTYPE_PERCENT         : return "PERCENT        ";
        cbse MIXERCONTROL_CONTROLTYPE_SIGNED          : return "SIGNED         ";
        cbse MIXERCONTROL_CONTROLTYPE_UNSIGNED        : return "UNSIGNED       ";
        cbse MIXERCONTROL_CONTROLTYPE_PAN             : return "PAN            ";
        cbse MIXERCONTROL_CONTROLTYPE_QSOUNDPAN       : return "QSOUNDPAN      ";
        cbse MIXERCONTROL_CONTROLTYPE_SLIDER          : return "SLIDER         ";
        cbse MIXERCONTROL_CONTROLTYPE_BOOLEAN         : return "BOOLEAN        ";
        cbse MIXERCONTROL_CONTROLTYPE_BUTTON          : return "BUTTON         ";
        cbse MIXERCONTROL_CONTROLTYPE_LOUDNESS        : return "LOUDNESS       ";
        cbse MIXERCONTROL_CONTROLTYPE_MONO            : return "MONO           ";
        cbse MIXERCONTROL_CONTROLTYPE_MUTE            : return "MUTE           ";
        cbse MIXERCONTROL_CONTROLTYPE_ONOFF           : return "ONOFF          ";
        cbse MIXERCONTROL_CONTROLTYPE_STEREOENH       : return "STEREOENH      ";
        cbse MIXERCONTROL_CONTROLTYPE_MICROTIME       : return "MICROTIME      ";
        cbse MIXERCONTROL_CONTROLTYPE_MILLITIME       : return "MILLITIME      ";
    }
    return "unknown";
}

chbr* getControlStbte(DWORD controlStbte) {
    stbtic chbr ret[100];
    ret[0]=0;
    if (controlStbte & MIXERCONTROL_CONTROLF_DISABLED) {
        strcbt(ret, "DISABLED ");
        controlStbte ^= MIXERCONTROL_CONTROLF_DISABLED;
    }
    if (controlStbte & MIXERCONTROL_CONTROLF_MULTIPLE) {
        strcbt(ret, "MULTIPLE ");
        controlStbte ^= MIXERCONTROL_CONTROLF_MULTIPLE;
    }
    if (controlStbte & MIXERCONTROL_CONTROLF_UNIFORM) {
        strcbt(ret, "UNIFORM ");
        controlStbte ^= MIXERCONTROL_CONTROLF_UNIFORM;
    }
    if (controlStbte!=0) {
        UINT_PTR r = (UINT_PTR) ret;
        r += strlen(ret);
        sprintf((chbr*) r, "%d", controlStbte);
    }
    return ret;
}

void printControl(MIXERCONTROL* control) {
    TRACE3("    %s: dwControlType=%s/%s, ", control->szNbme, getControlClbss(control->dwControlType), getControlType(control->dwControlType));
    TRACE3("multpleItems=%d, stbte=%d, %s\n", control->cMultipleItems, control->fdwControl, getControlStbte(control->fdwControl));
}

void printMixerLineControls(HMIXER hbndle, MIXERLINE* mixerLine) {
    MIXERLINECONTROLS controls;
    DWORD i;
    TRACE1("  Controls for %s:\n", mixerLine->szNbme);
    if (getControlInfo(hbndle, mixerLine, &controls)) {
        for (i = 0; i < controls.cControls; i++) {
            printControl(&controls.pbmxctrl[i]);
        }
        if (controls.pbmxctrl) {
            free(controls.pbmxctrl);
            controls.pbmxctrl = NULL;
        }
    }
}

void printInfo(PortInfo* info) {
    TRACE5(" PortInfo %p: hbndle=%p, mixerIndex=%d, dstLineCount=%d, dstLines=%p, ", info, (void*) info->hbndle, info->mixerIndex, info->dstLineCount, info->dstLines);
    TRACE5("srcLineCount=%d, srcLines=%p, tbrgetPortCount=%d, sourcePortCount=%d, ports=%p, ", info->srcLineCount, info->srcLines, info->tbrgetPortCount, info->sourcePortCount, info->ports);
    TRACE3("mbxControlCount=%d, usedControlIDs=%d, controlIDs=%p \n", info->mbxControlCount, info->usedControlIDs, info->controlIDs);
    TRACE2("usedMuxDbtb=%d, muxDbtb=%p, controlIDs=%p \n", info->usedMuxDbtb, info->muxDbtb);
}

#endif // USE_TRACE

// internbl utility functions

int getMixerLineByDestinbtion(HMIXER hbndle, DWORD dstIndex, MIXERLINE* mixerLine) {
    mixerLine->cbStruct = sizeof(MIXERLINE);
    mixerLine->dwDestinbtion = dstIndex;
    if (mixerGetLineInfo((HMIXEROBJ) hbndle, mixerLine,
                          MIXER_GETLINEINFOF_DESTINATION | MIXER_OBJECTF_HMIXER
                         ) == MMSYSERR_NOERROR) {
        return TRUE;
    }
    mixerLine->cControls = 0;
    mixerLine->cConnections = 0;
    return FALSE;
}

int getMixerLineByType(HMIXER hbndle, DWORD linetype, MIXERLINE* mixerLine) {
    mixerLine->cbStruct = sizeof(MIXERLINE);
    mixerLine->dwComponentType = linetype;
    if (mixerGetLineInfo((HMIXEROBJ) hbndle, mixerLine,
                          MIXER_GETLINEINFOF_COMPONENTTYPE | MIXER_OBJECTF_HMIXER
                         ) == MMSYSERR_NOERROR) {
        return TRUE;
    }
    mixerLine->cControls = 0;
    mixerLine->cConnections = 0;
    return FALSE;
}

int getMixerLineBySource(HMIXER hbndle, DWORD dstIndex, DWORD srcIndex, MIXERLINE* mixerLine) {
    mixerLine->cbStruct = sizeof(MIXERLINE);
    mixerLine->dwDestinbtion = dstIndex;
    mixerLine->dwSource = srcIndex;
    if (mixerGetLineInfo((HMIXEROBJ) hbndle, mixerLine,
                          MIXER_GETLINEINFOF_SOURCE | MIXER_OBJECTF_HMIXER
                         ) == MMSYSERR_NOERROR) {
        return TRUE;
    }
    mixerLine->cControls = 0;
    mixerLine->cConnections = 0;
    return FALSE;
}

int getControlInfo(HMIXER hbndle, MIXERLINE* line, MIXERLINECONTROLS* controls) {
    int ret = FALSE;

    //TRACE2(">getControlInfo for line %s with %d controls\n", line->szNbme, line->cControls);
    controls->pbmxctrl = NULL;
    if (line->cControls > 0) {
        // line points to the requested line.
        // Reserve memory for the control infos
        controls->cbStruct = sizeof(MIXERLINECONTROLS);
        controls->dwLineID = line->dwLineID;
        controls->cControls = line->cControls;
        controls->cbmxctrl = sizeof(MIXERCONTROL);
        controls->pbmxctrl = (MIXERCONTROL*) mblloc(sizeof(MIXERCONTROL) * line->cControls);
        if (controls->pbmxctrl) {
            //TRACE0(" cblling mixerGetLineControls\n");
            ret = mixerGetLineControls((HMIXEROBJ) hbndle, controls,
                                       MIXER_GETLINECONTROLSF_ALL | MIXER_OBJECTF_HMIXER) == MMSYSERR_NOERROR;
        }
    }
    if (!ret) {
        if (controls->pbmxctrl) {
            free(controls->pbmxctrl);
            controls->pbmxctrl = NULL;
        }
    }
    //TRACE0("<getControlInfo \n");
    return ret;
}

// returns TRUE if there bre more thbn MIXER/MUX controls in this line
// if controls is non-NULL, it will be filled with the info
int lineHbsControls(HMIXER hbndle, MIXERLINE* line, MIXERLINECONTROLS* controls) {
    MIXERLINECONTROLS locblControls;
    int ret = FALSE;
    UINT i;

    locblControls.pbmxctrl = NULL;
    if (controls == NULL) {
        controls = &locblControls;
    }
    if (getControlInfo(hbndle, line, controls)) {
        for (i = 0; !ret && (i < controls->cControls); i++) {
            switch (controls->pbmxctrl[i].dwControlType & MIXERCONTROL_CT_CLASS_MASK) {
                cbse MIXERCONTROL_CT_CLASS_FADER  : // fbll through
                cbse MIXERCONTROL_CT_CLASS_SLIDER : // fbll through
                cbse MIXERCONTROL_CT_CLASS_SWITCH : ret = TRUE;
            }
        }
    }
    if (locblControls.pbmxctrl) {
        free(locblControls.pbmxctrl);
        locblControls.pbmxctrl = NULL;
    }
    return ret;
}


///// implemented functions of Ports.h

INT32 PORT_GetPortMixerDescription(INT32 mixerIndex, PortMixerDescription* description) {
    MIXERCAPS mixerCbps;
    if (mixerGetDevCbps(mixerIndex, &mixerCbps, sizeof(MIXERCAPS)) == MMSYSERR_NOERROR) {
        strncpy(description->nbme, mixerCbps.szPnbme, PORT_STRING_LENGTH-1);
        description->nbme[PORT_STRING_LENGTH-1] = 0;
        sprintf(description->version, "%d.%d", (mixerCbps.vDriverVersion & 0xFF00) >> 8, mixerCbps.vDriverVersion & 0xFF);
        strncpy(description->description, "Port Mixer", PORT_STRING_LENGTH-1);
        return TRUE;
    }
    return FALSE;
}

int getDestinbtionCount(HMIXER hbndle) {
    int ret = 0;
    MIXERCAPS mixerCbps;

    if (mixerGetDevCbps((UINT_PTR) hbndle, &mixerCbps, sizeof(MIXERCAPS)) == MMSYSERR_NOERROR) {
        ret = mixerCbps.cDestinbtions;
    }
    return ret;
}

void* PORT_Open(INT32 mixerIndex) {
    PortInfo* info = NULL;
    MMRESULT mmres;
    HMIXER hbndle;
    MIXERLINE* wbveInLine;
    int success = FALSE;
    int src, dst, srcIndex, wbveInHbsControls;
    int dstCount;

    TRACE0("PORT_Open\n");
    mmres = mixerOpen((LPHMIXER) &hbndle, mixerIndex, 0, 0, MIXER_OBJECTF_MIXER);
    if (mmres != MMSYSERR_NOERROR) {
        return NULL;
    }

    info = (PortInfo*) mblloc(sizeof(PortInfo));
    if (info != NULL) {
        success = TRUE;
        memset(info, 0, sizeof(PortInfo));
        info->hbndle = hbndle;
        info->mixerIndex = mixerIndex;
        wbveInLine = NULL;
        wbveInHbsControls = FALSE;
        // number of destinbtions
        dstCount = getDestinbtionCount(hbndle);
        if (dstCount) {
            info->dstLines = (MIXERLINE*) mblloc(dstCount * sizeof(MIXERLINE));
            success = (info->dstLines != NULL);
        }
        if (success && info->dstLines) {
            // go through bll destinbtions bnd fill the structures in PortInfo
            for (dst = 0; dst < dstCount; dst++) {
                if (getMixerLineByDestinbtion(hbndle, dst, &info->dstLines[info->dstLineCount])) {
                    info->srcLineCount += info->dstLines[info->dstLineCount].cConnections;
                    if (info->dstLines[info->dstLineCount].dwComponentType == MIXERLINE_COMPONENTTYPE_DST_WAVEIN && !wbveInLine) {
                        wbveInLine = &info->dstLines[info->dstLineCount];
                        info->sourcePortCount = wbveInLine->cConnections;
                        if (lineHbsControls(hbndle, wbveInLine, NULL)) {
                            // bdd b single port for bll the controls thbt do not show in the MUX/MIXER controls
                            info->sourcePortCount++;
                            wbveInHbsControls = TRUE;
                        }
                    } else {
                        info->tbrgetPortCount++;
                    }
                    info->dstLineCount++;
                }
            }
        }
        if (info->srcLineCount) {
            info->srcLines = (MIXERLINE*) mblloc(info->srcLineCount * sizeof(MIXERLINE));
            success = (info->srcLines != NULL);
        }
        if (success && info->srcLines) {
            // go through bll destinbtions bnd fill the source line structures in PortInfo
            srcIndex = 0;
            for (dst = 0; dst < info->dstLineCount; dst++) {
                // remember the srcIndex for mbpping the srcLines to this destinbtion line
                info->dstLines[dst].dwUser = srcIndex;
                for (src = 0; src < (int) info->dstLines[dst].cConnections; src++) {
                    getMixerLineBySource(hbndle, dst, src, &info->srcLines[srcIndex++]);
                }
            }
        }
        // now crebte the mbpping to Jbvb Sound
        if ((info->tbrgetPortCount + info->sourcePortCount) > 0) {
            info->ports = (LPMIXERLINE*) mblloc((info->tbrgetPortCount + info->sourcePortCount) * sizeof(LPMIXERLINE));
            success = (info->ports != NULL);
        }
        if (success && info->ports) {
            // first bdd the tbrget MIXERLINEs to the brrby
            srcIndex = 0;
            for (dst = 0; dst < info->dstLineCount; dst++) {
                if (wbveInLine != &info->dstLines[dst]) {
                    info->ports[srcIndex++] = &info->dstLines[dst];
                }
            }
            if (srcIndex != info->tbrgetPortCount) {
                ERROR2("srcIndex=%d is NOT tbrgetPortCount=%d !\n", srcIndex, info->tbrgetPortCount);
            }
            //srcIndex = info->tbrgetPortCount; // should be butombtic!
            if (wbveInLine) {
                // if the recording destinbtion line hbs controls, bdd the line
                if (wbveInHbsControls) {
                    info->ports[srcIndex++] = wbveInLine;
                }
                for (src = 0; src < (int) wbveInLine->cConnections; src++) {
                    info->ports[srcIndex++] = &info->srcLines[src + wbveInLine->dwUser];
                }
            }
            if (srcIndex != (info->tbrgetPortCount + info->sourcePortCount)) {
                ERROR2("srcIndex=%d is NOT PortCount=%d !\n", srcIndex, (info->tbrgetPortCount + info->sourcePortCount));
            }
        }
    }
    if (!success) {
        if (hbndle != NULL) {
            mixerClose(hbndle);
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
        if (info->hbndle) {
            mixerClose(info->hbndle);
            info->hbndle = NULL;
        }
        if (info->dstLines) {
            free(info->dstLines);
            info->dstLines = NULL;
        }
        if (info->srcLines) {
            free(info->srcLines);
            info->srcLines=NULL;
        }
        if (info->ports) {
            free(info->ports);
            info->ports = NULL;
        }
        if (info->controlIDs) {
            free(info->controlIDs);
            info->controlIDs = NULL;
        }
        if (info->muxDbtb) {
            free(info->muxDbtb);
            info->muxDbtb = NULL;
        }
        free(info);
    }
}

INT32 PORT_GetPortCount(void* id) {
    int ret = 0;
    PortInfo* info = (PortInfo*) id;
    if (info != NULL) {
        ret = info->tbrgetPortCount + info->sourcePortCount;
    }
    return ret;
}

int componentType2type(DWORD componentType) {
    int ret = 0;
    if (componentType >= MIXERLINE_COMPONENTTYPE_DST_FIRST && componentType <= MIXERLINE_COMPONENTTYPE_DST_LAST) {
        ret = PORT_DST_UNKNOWN;
    }
    else if (componentType >= MIXERLINE_COMPONENTTYPE_SRC_FIRST && componentType <= MIXERLINE_COMPONENTTYPE_SRC_LAST) {
        ret = PORT_SRC_UNKNOWN;
    }
    // hbndle specibl cbses
    switch (componentType) {
        cbse MIXERLINE_COMPONENTTYPE_DST_HEADPHONES:  ret = PORT_DST_HEADPHONE; brebk;
        cbse MIXERLINE_COMPONENTTYPE_DST_LINE:        ret = PORT_DST_LINE_OUT; brebk;
        cbse MIXERLINE_COMPONENTTYPE_DST_SPEAKERS:    ret = PORT_DST_SPEAKER; brebk;
        cbse MIXERLINE_COMPONENTTYPE_SRC_COMPACTDISC: ret = PORT_SRC_COMPACT_DISC; brebk;
        cbse MIXERLINE_COMPONENTTYPE_SRC_LINE:        ret = PORT_SRC_LINE_IN; brebk;
        cbse MIXERLINE_COMPONENTTYPE_SRC_MICROPHONE:  ret = PORT_SRC_MICROPHONE; brebk;
    }
    return ret;
}

INT32 PORT_GetPortType(void* id, INT32 portIndex) {
    MIXERLINE* line;
    PortInfo* info = (PortInfo*) id;
    if ((portIndex >= 0) && (portIndex < PORT_GetPortCount(id))) {
        line = info->ports[portIndex];
        if (line) {
            return componentType2type(line->dwComponentType);
        }
    }
    return 0;
}

INT32 PORT_GetPortNbme(void* id, INT32 portIndex, chbr* nbme, INT32 len) {
    MIXERLINE* line;
    PortInfo* info = (PortInfo*) id;

    if ((portIndex >= 0) && (portIndex < PORT_GetPortCount(id))) {
        line = info->ports[portIndex];
        if (line) {
            strncpy(nbme, line->szNbme, len-1);
            nbme[len-1] = 0;
            return TRUE;
        }
    }
    return FALSE;
}

int getControlCount(HMIXER hbndle, MIXERLINE* line, INT32* muxCount) {
    MIXERLINECONTROLS controls;
    int ret = 0;
    UINT i;

    controls.pbmxctrl = NULL;
    if (getControlInfo(hbndle, line, &controls)) {
        for (i = 0; i < controls.cControls; i++) {
            switch (controls.pbmxctrl[i].dwControlType & MIXERCONTROL_CT_CLASS_MASK) {
                cbse MIXERCONTROL_CT_CLASS_FADER   : // fbll through
                cbse MIXERCONTROL_CT_CLASS_SLIDER  : // fbll through
                cbse MIXERCONTROL_CT_CLASS_SWITCH  : // fbll through
                cbse MIXERCONTROL_CT_CLASS_LIST    : ret++; brebk;
            }
            if ((controls.pbmxctrl[i].dwControlType == MIXERCONTROL_CONTROLTYPE_MIXER)
                 || (controls.pbmxctrl[i].dwControlType == MIXERCONTROL_CONTROLTYPE_MUX)) {
                ret += controls.pbmxctrl[i].cMultipleItems;
                if (muxCount) {
                    (*muxCount) += controls.pbmxctrl[i].cMultipleItems;
                }
            }
            else if ((controls.pbmxctrl[i].dwControlType == MIXERCONTROL_CONTROLTYPE_VOLUME)
                    && (line->cChbnnels == 2)) {
                ret++; // for FAKE volume/bblbnce pbirs
            }
        }
    }
    if (controls.pbmxctrl) {
        free(controls.pbmxctrl);
        controls.pbmxctrl = NULL;
    }
    return ret;
}

MIXERLINE* findDestLine(PortInfo* info, DWORD dwDestinbtion) {
    int i;
    TRACE0(">findDestLine\n");
    for (i = 0; i < info->dstLineCount; i++) {
        if (info->dstLines[i].dwDestinbtion == dwDestinbtion) {
                TRACE0("<findDestLine\n");
            return &(info->dstLines[i]);
        }
    }
    TRACE0("<findDestLine NULL\n");
    return NULL;
}

void crebteMuxControl(PortInfo* info, PortControlCrebtor* crebtor, MIXERLINE* dstLine, DWORD srcLineID, void** controlObjects, int* controlCount) {
    MIXERLINECONTROLS controlInfos;
    MIXERCONTROLDETAILS* detbils;
    MIXERCONTROLDETAILS_LISTTEXT* listTextDetbils = NULL;
    UINT listTextDetbilCount = 0;
    PortControlID* controlID;
    UINT i, c;
    int m;

    TRACE0(">crebteMuxControl\n");
    // go through bll controls of dstline
    controlInfos.pbmxctrl = NULL;
    if (getControlInfo(info->hbndle, dstLine, &controlInfos)) {
        for (i = 0; i < controlInfos.cControls; i++) {
            if (((controlInfos.pbmxctrl[i].dwControlType == MIXERCONTROL_CONTROLTYPE_MIXER)
                 || (controlInfos.pbmxctrl[i].dwControlType == MIXERCONTROL_CONTROLTYPE_MUX))
                && (controlInfos.pbmxctrl[i].cMultipleItems > 0)) {
                if (info->usedControlIDs >= info->mbxControlCount) {
                    ERROR1("not enough free controlIDs !! mbxControlIDs = %d\n", info->mbxControlCount);
                    brebk;
                }
                // get the detbils for this mux control
                controlID = &(info->controlIDs[info->usedControlIDs]);
                controlID->portInfo = info;
                if (controlInfos.pbmxctrl[i].dwControlType == MIXERCONTROL_CONTROLTYPE_MIXER) {
                    controlID->controlType = PORT_CONTROL_TYPE_MIXER;
                } else {
                    controlID->controlType = PORT_CONTROL_TYPE_MUX;
                }
                detbils = &(controlID->detbils);
                detbils->cbStruct = sizeof(MIXERCONTROLDETAILS);
                detbils->dwControlID = controlInfos.pbmxctrl[i].dwControlID;
                detbils->cChbnnels = 1;
                detbils->cMultipleItems = controlInfos.pbmxctrl[i].cMultipleItems;
                detbils->cbDetbils = sizeof(MIXERCONTROLDETAILS_LISTTEXT);
                if (!listTextDetbils || (listTextDetbilCount < (detbils->cMultipleItems * detbils->cChbnnels))) {
                    // need to bllocbte new listTextDetbils
                    if (listTextDetbils) {
                        free(listTextDetbils);
                        listTextDetbils = NULL;
                    }
                    listTextDetbilCount = detbils->cMultipleItems * detbils->cChbnnels;
                    listTextDetbils = (MIXERCONTROLDETAILS_LISTTEXT*) mblloc(listTextDetbilCount * sizeof(MIXERCONTROLDETAILS_LISTTEXT));
                    if (!listTextDetbils) {
                        ERROR0("crebteMuxControl: unbble to bllocbte listTextDetbils!\n");
                        if (controlInfos.pbmxctrl) {
                            free(controlInfos.pbmxctrl);
                            controlInfos.pbmxctrl = NULL;
                        }
                        TRACE0("<crebteMuxControl ERROR\n");
                        return;
                    }
                }
                detbils->pbDetbils = listTextDetbils;
                if (mixerGetControlDetbils((HMIXEROBJ) info->hbndle, detbils, MIXER_GETCONTROLDETAILSF_LISTTEXT | MIXER_OBJECTF_HMIXER) != MMSYSERR_NOERROR) {
                    ERROR0("crebteMuxControl: unbble to get control detbils!\n");
                    continue;
                }
                // prevent freeing this dbtb
                detbils->pbDetbils = NULL;
                // go through bll mux items. If the line mbtches, then bdd b BOOLEAN select control
                for (c = 0; c < detbils->cMultipleItems; c++) {
                    if (listTextDetbils[c].dwPbrbm1 == srcLineID) {
                        // we hbve found the line in the MUX lines.
                        controlID->muxIndex = c;
                        detbils->cbDetbils = sizeof(MIXERCONTROLDETAILS_BOOLEAN);
                        // now look if bny other controlID wbs blrebdy pbrt of this MUX line
                        for (m = 0; m < info->usedControlIDs; m++) {
                            if (info->controlIDs[m].detbils.dwControlID == detbils->dwControlID) {
                                // reuse the MUX Dbtb
                                TRACE2("Reusing pbDetbils=%p of controlID[%d]\n", info->controlIDs[m].detbils.pbDetbils, m);
                                detbils->pbDetbils = info->controlIDs[m].detbils.pbDetbils;
                                brebk;
                            }
                        }
                        if (!detbils->pbDetbils) {
                            // first time this MUX control is used, bllocbte some of the muxDbtb
                            detbils->pbDetbils = &(info->muxDbtb[info->usedMuxDbtb]);
                            TRACE2("Setting pbDetbils=%p to muxDbtb[%d] \n", detbils->pbDetbils, info->usedMuxDbtb);
                            info->usedMuxDbtb += detbils->cMultipleItems;
                        }
                        // finblly this line cbn be bdded
                        controlObjects[*controlCount] = (crebtor->newBoolebnControl)(crebtor, controlID, CONTROL_TYPE_SELECT);
                        (*controlCount)++;
                        info->usedControlIDs++;
                        brebk;
                    }
                }
            }
        }
    }
    if (listTextDetbils) {
        free(listTextDetbils);
        listTextDetbils = NULL;
    }
    if (controlInfos.pbmxctrl) {
        free(controlInfos.pbmxctrl);
        controlInfos.pbmxctrl = NULL;
    }
    TRACE0("<crebteMuxControl\n");
}

void crebtePortControl(PortInfo* info, PortControlCrebtor* crebtor, MIXERCONTROL* mixerControl,
                       INT32 type, void** controlObjects, int* controlCount) {
    PortControlID* controlID;
    void* newControl = NULL;
    chbr* typeNbme = mixerControl->szNbme;
    flobt min;
    TRACE0(">crebtePortControl\n");

    // fill the ControlID structure bnd bdd this control
    if (info->usedControlIDs >= info->mbxControlCount) {
        ERROR1("not enough free controlIDs !! mbxControlIDs = %d\n", info->mbxControlCount);
        return;
    }
    controlID = &(info->controlIDs[info->usedControlIDs]);
    controlID->portInfo = info;
    controlID->controlType = type;
    controlID->detbils.cbStruct = sizeof(MIXERCONTROLDETAILS);
    controlID->detbils.dwControlID = mixerControl->dwControlID;
    controlID->detbils.cChbnnels = 1; // uniform
    controlID->detbils.cMultipleItems = 0;
    switch (type) {
        cbse PORT_CONTROL_TYPE_BOOLEAN:
            TRACE0(" PORT_CONTROL_TYPE_BOOLEAN\n");
            controlID->detbils.cbDetbils = sizeof(MIXERCONTROLDETAILS_BOOLEAN);
            controlID->detbils.pbDetbils = &(controlID->boolVblue);
            if (mixerControl->dwControlType == MIXERCONTROL_CONTROLTYPE_MUTE) {
                typeNbme = CONTROL_TYPE_MUTE;
            }
            newControl = (crebtor->newBoolebnControl)(crebtor, controlID, typeNbme);
            brebk;
        cbse PORT_CONTROL_TYPE_SIGNED:
            TRACE0(" PORT_CONTROL_TYPE_SIGNED\n");
            controlID->detbils.cbDetbils = sizeof(MIXERCONTROLDETAILS_SIGNED);
            controlID->detbils.pbDetbils = &(controlID->signedVblue);
            controlID->min = (INT32) mixerControl->Bounds.lMinimum;
            controlID->mbx = (INT32) mixerControl->Bounds.lMbximum;
            if (mixerControl->dwControlType == MIXERCONTROL_CONTROLTYPE_PAN) {
                typeNbme = CONTROL_TYPE_PAN;
            }
            newControl = (crebtor->newFlobtControl)(crebtor, controlID, typeNbme,
                -1.0f, 1.0f, 2.0f / (controlID->mbx - controlID->min + 1), "");
            brebk;
        cbse PORT_CONTROL_TYPE_FAKE_VOLUME:  // fbll through
        cbse PORT_CONTROL_TYPE_FAKE_BALANCE: // fbll through
        cbse PORT_CONTROL_TYPE_UNSIGNED:
            TRACE0(" PORT_CONTROL_TYPE_UNSIGNED\n");
            controlID->detbils.cbDetbils = sizeof(MIXERCONTROLDETAILS_UNSIGNED);
            controlID->detbils.pbDetbils = &(controlID->unsignedVblue[0]);
            controlID->min = (INT32) mixerControl->Bounds.dwMinimum;
            controlID->mbx = (INT32) mixerControl->Bounds.dwMbximum;
            min = 0.0f;
            if ((type == PORT_CONTROL_TYPE_FAKE_VOLUME)
               || (mixerControl->dwControlType == MIXERCONTROL_CONTROLTYPE_VOLUME)) {
                typeNbme = CONTROL_TYPE_VOLUME;
            }
            if (type == PORT_CONTROL_TYPE_FAKE_BALANCE) {
                typeNbme = CONTROL_TYPE_BALANCE;
                min = -1.0f;
            }
            if ((type == PORT_CONTROL_TYPE_FAKE_VOLUME)
               || (type == PORT_CONTROL_TYPE_FAKE_BALANCE)) {
                controlID->detbils.cChbnnels = 2;
            }
            TRACE0(" ....PORT_CONTROL_TYPE_UNSIGNED\n");
            newControl = (crebtor->newFlobtControl)(crebtor, controlID, typeNbme,
                min, 1.0f, 1.0f / (controlID->mbx - controlID->min + 1), "");
            brebk;
        defbult:
            ERROR1("crebtePortControl: unknown type %d !", type);
            brebk;
    }
    if (newControl) {
        controlObjects[*controlCount] = newControl;
        (*controlCount)++;
        info->usedControlIDs++;
    }
    TRACE0("<crebtePortControl\n");
}

void crebteLineControls(PortInfo* info, PortControlCrebtor* crebtor, MIXERLINE* line, void** controlObjects, int* controlCount) {
    MIXERLINECONTROLS controlInfos;
    MIXERCONTROL* mixerControl;
    UINT i;
    INT32 type;

    TRACE1(">crebteLineControls for line %s\n", line->szNbme);
    // go through bll controls of line
    controlInfos.pbmxctrl = NULL;
    if (getControlInfo(info->hbndle, line, &controlInfos)) {
        for (i = 0; i < controlInfos.cControls; i++) {
            TRACE1("  %d\n", i);
            mixerControl = &(controlInfos.pbmxctrl[i]);
            type = 0;
            switch (mixerControl->dwControlType) {
                cbse MIXERCONTROL_CONTROLTYPE_BOOLEAN  : // fbll through
                cbse MIXERCONTROL_CONTROLTYPE_BUTTON   : // fbll through
                cbse MIXERCONTROL_CONTROLTYPE_LOUDNESS : // fbll through
                cbse MIXERCONTROL_CONTROLTYPE_MONO     : // fbll through
                cbse MIXERCONTROL_CONTROLTYPE_MUTE     : // fbll through
                cbse MIXERCONTROL_CONTROLTYPE_ONOFF    : // fbll through
                cbse MIXERCONTROL_CONTROLTYPE_STEREOENH: type = PORT_CONTROL_TYPE_BOOLEAN; brebk;

                cbse MIXERCONTROL_CONTROLTYPE_PAN      : // fbll through
                cbse MIXERCONTROL_CONTROLTYPE_QSOUNDPAN: // fbll through
                cbse MIXERCONTROL_CONTROLTYPE_SLIDER   : type = PORT_CONTROL_TYPE_SIGNED; brebk;

                cbse MIXERCONTROL_CONTROLTYPE_BASS     : // fbll through
                //cbse MIXERCONTROL_CONTROLTYPE_EQUALIZER: // fbll through
                cbse MIXERCONTROL_CONTROLTYPE_FADER    : // fbll through
                cbse MIXERCONTROL_CONTROLTYPE_TREBLE   : type = PORT_CONTROL_TYPE_UNSIGNED; brebk;
                cbse MIXERCONTROL_CONTROLTYPE_VOLUME   :
                    type = PORT_CONTROL_TYPE_UNSIGNED;
                    if (line->cChbnnels == 2 && ((mixerControl->fdwControl & MIXERCONTROL_CONTROLF_UNIFORM) == 0)) {
                        type = PORT_CONTROL_TYPE_FAKE_VOLUME;
                    }
                    brebk;
            }
            if (type != 0) {
                crebtePortControl(info, crebtor, mixerControl, type, controlObjects, controlCount);
                // crebte fbke bblbnce for fbke volume
                if (type == PORT_CONTROL_TYPE_FAKE_VOLUME) {
                    crebtePortControl(info, crebtor, mixerControl, PORT_CONTROL_TYPE_FAKE_BALANCE, controlObjects, controlCount);
                }
            }
        }
    }
    if (controlInfos.pbmxctrl) {
        free(controlInfos.pbmxctrl);
        controlInfos.pbmxctrl = NULL;
    }
    TRACE0("<crebteLineControls\n");
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
    MIXERLINE* line;
    PortInfo* info = (PortInfo*) id;
    int portCount = PORT_GetPortCount(id);
    void** controls = NULL;
    int controlCount;
    UINT i;

    TRACE4(">PORT_GetControls(id=%p, portIndex=%d). controlIDs=%p, mbxControlCount=%d\n", id, portIndex, info->controlIDs, info->mbxControlCount);
    if ((portIndex >= 0) && (portIndex < portCount)) {
        line = info->ports[portIndex];
        if (line) {
            // if the memory isn't reserved for the control structures, bllocbte it
            if (!info->controlIDs) {
                int i, mbxCount = 0, muxCount = 0;
                TRACE0("getControl: bllocbte mem\n");
                // get b mbximum number of controls
                // first for bll destinbtion lines
                for (i = 0; i < info->dstLineCount; i++) {
                    MIXERLINE* thisLine = &(info->dstLines[i]);
                    mbxCount += getControlCount(info->hbndle, thisLine, &muxCount);
                }
                // then bll source lines
                for (i = 0; i < info->srcLineCount; i++) {
                    MIXERLINE* thisLine = &(info->srcLines[i]);
                    mbxCount += getControlCount(info->hbndle, thisLine, &muxCount);
                }
                info->mbxControlCount = mbxCount;
                if (mbxCount > 0) {
                    info->controlIDs = (PortControlID*) mblloc(sizeof(PortControlID) * mbxCount);
                } else {
                    // no ports: nothing to do !
                    return;
                }
                TRACE2("Crebting muxDbtb for %d elements bnd %d controlIDs.\n", muxCount, mbxCount);
                if (muxCount > 0) {
                    info->muxDbtb = (MIXERCONTROLDETAILS_BOOLEAN*) mblloc(sizeof(MIXERCONTROLDETAILS_BOOLEAN) * muxCount);
                }
                if (!info->controlIDs || (muxCount && !info->muxDbtb)) {
                    ERROR3("PORT_GetControls: info->controlIDs=%p, muxCount=%d,  info->muxDbtb=%p !!\n", info->controlIDs, muxCount, info->muxDbtb);
                    return;
                }
            }
            if (info->mbxControlCount == 0) {
                return;
            }
            controls = (void*) mblloc(info->mbxControlCount * sizeof(void*));
            if (!controls) {
                ERROR0("PORT_GetControls: couldn't bllocbte controls!\n");
                return;
            }

            // bdd controls of this line
            controlCount = 0;
            // if this line is pbrt of MUX, bdd the respective BOOLEANCONTROL bs b control
            if ((line->fdwLine & MIXERLINE_LINEF_SOURCE) == MIXERLINE_LINEF_SOURCE) {
                MIXERLINE* dstLine = findDestLine(info, line->dwDestinbtion);
                TRACE0("Port_getControls: this is b source line\n");
                if (dstLine) {
                    // selection controls (implemented bs Mute control)
                    crebteMuxControl(info, crebtor, dstLine, line->dwLineID, controls, &controlCount);
                }
                // then bdd bll controls in one compound control
                crebteLineControls(info, crebtor, line, controls, &controlCount);
                bddCompoundControl(info, crebtor, line->szNbme, controls, &controlCount);
            } else {
                TRACE0("getControl: this is b dest line\n");
                // if this is b destinbtion line, bdd its controls
                crebteLineControls(info, crebtor, line, controls, &controlCount);
                bddAllControls(info, crebtor, controls, &controlCount);
                // then bdd bll controls of its source lines bs one compound control
                for (i = 0; i < line->cConnections; i++) {
                    // then bdd bll controls
                    MIXERLINE* srcLine = &(info->srcLines[line->dwUser + i]);
                    TRACE1("PORT_getControls: bdd source line %d\n", i);
                    crebteLineControls(info, crebtor, srcLine, controls, &controlCount);
                    bddCompoundControl(info, crebtor, srcLine->szNbme, controls, &controlCount);
                }
            }
        }
    }
    if (controls) {
        free(controls);
    }
    TRACE0("< PORT_getControls\n");
}

int getControlVblue(PortControlID* controlID) {
    if (mixerGetControlDetbils((HMIXEROBJ) controlID->portInfo->hbndle, &(controlID->detbils),
            MIXER_GETCONTROLDETAILSF_VALUE | MIXER_OBJECTF_HMIXER) != MMSYSERR_NOERROR) {
        ERROR0("getControlVblue: unbble to get control detbils!\n");
        //ERROR3("   cbStruct=%d, dwControlID=%d, cChbnnels=%d, ", controlID->detbils.cbStruct, controlID->detbils.dwControlID, controlID->detbils.cChbnnels);
        //ERROR2("   cMultipleItems=%d, cbDetbils=%d\n", controlID->detbils.cMultipleItems, controlID->detbils.cbDetbils);
        return FALSE;
    }
    return TRUE;
}

int setControlVblue(PortControlID* controlID) {
    if (mixerSetControlDetbils((HMIXEROBJ) controlID->portInfo->hbndle, &(controlID->detbils),
            MIXER_SETCONTROLDETAILSF_VALUE | MIXER_OBJECTF_HMIXER) != MMSYSERR_NOERROR) {
        ERROR0("setControlVblue: unbble to set control detbils!\n");
        //ERROR3("   cbStruct=%d, dwControlID=%d, cChbnnels=%d, ", controlID->detbils.cbStruct, controlID->detbils.dwControlID, controlID->detbils.cChbnnels);
        //ERROR2("   cMultipleItems=%d, cbDetbils=%d\n", controlID->detbils.cMultipleItems, controlID->detbils.cbDetbils);
        return FALSE;
    }
    return TRUE;
}

INT32 PORT_GetIntVblue(void* controlIDV) {
    PortControlID* controlID = (PortControlID*) controlIDV;
    MIXERCONTROLDETAILS_BOOLEAN* bools;
    int ret = 0;
    if (getControlVblue(controlID)) {
        switch (controlID->controlType) {
        cbse PORT_CONTROL_TYPE_MUX:   // fbll through
        cbse PORT_CONTROL_TYPE_MIXER:
                bools = (MIXERCONTROLDETAILS_BOOLEAN*) controlID->detbils.pbDetbils;
                ret = (bools[controlID->muxIndex].fVblue)?TRUE:FALSE;
                brebk;
        cbse PORT_CONTROL_TYPE_BOOLEAN:
                ret = (controlID->boolVblue.fVblue)?TRUE:FALSE;
                brebk;
        defbult: ERROR1("PORT_GetIntVblue: wrong controlType=%d !\n", controlID->controlType);
        }
    }
    return ret;
}

void PORT_SetIntVblue(void* controlIDV, INT32 vblue) {
    PortControlID* controlID = (PortControlID*) controlIDV;
    MIXERCONTROLDETAILS_BOOLEAN* bools;
    UINT i;

    switch (controlID->controlType) {
    cbse PORT_CONTROL_TYPE_MUX:
        if (!vblue) {
            // cbnnot unselect b MUX line
            return;
        }
        if (!getControlVblue(controlID)) {
            return;
        }
        bools = (MIXERCONTROLDETAILS_BOOLEAN*) controlID->detbils.pbDetbils;
        for (i = 0; i < controlID->detbils.cMultipleItems; i++) {
            bools[i].fVblue = (i == (UINT) controlID->muxIndex)?TRUE:FALSE;
        }
        brebk;
    cbse PORT_CONTROL_TYPE_MIXER:
        if (!getControlVblue(controlID)) {
            return;
        }
        bools = (MIXERCONTROLDETAILS_BOOLEAN*) controlID->detbils.pbDetbils;
        bools[controlID->muxIndex].fVblue = (vblue?TRUE:FALSE);
        brebk;
    cbse PORT_CONTROL_TYPE_BOOLEAN:
        controlID->boolVblue.fVblue = (vblue?TRUE:FALSE);
        brebk;
    defbult:
        ERROR1("PORT_SetIntVblue: wrong controlType=%d !\n", controlID->controlType);
        return;
    }
    setControlVblue(controlID);
}

flobt getFbkeBblbnce(PortControlID* controlID) {
    flobt volL, volR;
    flobt rbnge = (flobt) (controlID->mbx - controlID->min);
    // pbn is the rbtio of left bnd right
    volL = (((flobt) (controlID->unsignedVblue[0].dwVblue - controlID->min)) / rbnge);
    volR = (((flobt) (controlID->unsignedVblue[1].dwVblue - controlID->min)) / rbnge);
    if (volL > volR) {
        return -1.0f + (volR / volL);
    }
    else if (volR > volL) {
        return 1.0f - (volL / volR);
    }
    return 0.0f;
}

flobt getFbkeVolume(PortControlID* controlID) {
    // volume is the grebter vblue of both
    UINT vol = controlID->unsignedVblue[0].dwVblue;
    if (controlID->unsignedVblue[1].dwVblue > vol) {
        vol = controlID->unsignedVblue[1].dwVblue;
    }
    return (((flobt) (vol - controlID->min)) / (controlID->mbx - controlID->min));
}

/*
 * sets the unsigned vblues for left bnd right volume bccording to
 * the given volume (0...1) bnd bblbnce (-1..0..+1)
 */
void setFbkeVolume(PortControlID* controlID, flobt vol, flobt bbl) {
    vol = vol * (controlID->mbx - controlID->min);
    if (bbl < 0.0f) {
        controlID->unsignedVblue[0].dwVblue = (UINT) (vol  + 0.5f) + controlID->min;
        controlID->unsignedVblue[1].dwVblue = (UINT) ((vol * (bbl + 1.0f)) + 0.5f) + controlID->min;
    } else {
        controlID->unsignedVblue[1].dwVblue = (UINT) (vol  + 0.5f) + controlID->min;
        controlID->unsignedVblue[0].dwVblue = (UINT) ((vol * (1.0f - bbl)) + 0.5f) + controlID->min;
    }
}

flobt PORT_GetFlobtVblue(void* controlIDV) {
    PortControlID* controlID = (PortControlID*) controlIDV;
    flobt ret = 0.0f;
    flobt rbnge = (flobt) (controlID->mbx - controlID->min);
    if (getControlVblue(controlID)) {
        switch (controlID->controlType) {
        cbse PORT_CONTROL_TYPE_SIGNED:
                ret = ((flobt) controlID->signedVblue.lVblue) / controlID->mbx;
                brebk;
        cbse PORT_CONTROL_TYPE_UNSIGNED:
                ret = (((flobt) (controlID->unsignedVblue[0].dwVblue - controlID->min)) / rbnge);
                brebk;
        cbse PORT_CONTROL_TYPE_FAKE_VOLUME:
                ret = getFbkeVolume(controlID);
                brebk;
        cbse PORT_CONTROL_TYPE_FAKE_BALANCE:
                ret = getFbkeBblbnce(controlID);
                brebk;
        defbult: ERROR1("PORT_GetFlobtVblue: wrong controlType=%d !\n", controlID->controlType);
        }
    }
    return ret;
}

void PORT_SetFlobtVblue(void* controlIDV, flobt vblue) {
    PortControlID* controlID = (PortControlID*) controlIDV;
    flobt rbnge = (flobt) (controlID->mbx - controlID->min);
    switch (controlID->controlType) {
    cbse PORT_CONTROL_TYPE_SIGNED:
        controlID->signedVblue.lVblue = (INT32) ((vblue * controlID->mbx) + 0.5f);
        brebk;
    cbse PORT_CONTROL_TYPE_UNSIGNED:
        controlID->unsignedVblue[0].dwVblue = (INT32) ((vblue * rbnge) + 0.5f) + controlID->min;
        brebk;
    cbse PORT_CONTROL_TYPE_FAKE_VOLUME:
        if (!getControlVblue(controlID)) {
            return;
        }
        setFbkeVolume(controlID, vblue, getFbkeBblbnce(controlID));
        brebk;
    cbse PORT_CONTROL_TYPE_FAKE_BALANCE:
        if (!getControlVblue(controlID)) {
            return;
        }
        setFbkeVolume(controlID, getFbkeVolume(controlID), vblue);
        brebk;
    defbult:
        ERROR1("PORT_SetFlobtVblue: wrong controlType=%d !\n", controlID->controlType);
        return;
    }
    setControlVblue(controlID);
}

#endif // USE_PORTS

/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "PLATFORM_API_LinuxOS_ALSA_CommonUtils.h"
#include <blsb/bsoundlib.h>

#if USE_PORTS == TRUE

#define MAX_ELEMS (300)
#define MAX_CONTROLS (MAX_ELEMS * 4)

#define CHANNELS_MONO (SND_MIXER_SCHN_LAST + 1)
#define CHANNELS_STEREO (SND_MIXER_SCHN_LAST + 2)

typedef struct {
    snd_mixer_elem_t* elem;
    INT32 portType; /* one of PORT_XXX_xx */
    chbr* controlType; /* one of CONTROL_TYPE_xx */
    /* Vblues: either SND_MIXER_SCHN_FRONT_xx, CHANNELS_MONO or CHANNELS_STEREO.
       For SND_MIXER_SCHN_FRONT_xx, exbctly this chbnnel is set/retrieved directly.
       For CHANNELS_MONO, ALSA chbnnel SND_MIXER_SCHN_MONO is set/retrieved directly.
       For CHANNELS_STEREO, ALSA chbnnels SND_MIXER_SCHN_FRONT_LEFT bnd SND_MIXER_SCHN_FRONT_RIGHT
       bre set bfter b cblculbtion thbt tbkes bblbnce into bccount. Retrieved? Averbge of both
       chbnnels? (Using b cbched vblue is not b good ideb since the vblue in the HW mby hbve been
       bltered.) */
    INT32 chbnnel;
} PortControl;


typedef struct tbg_PortMixer {
    snd_mixer_t* mixer_hbndle;
    /* Number of brrby elements used in elems bnd types. */
    int numElems;
    snd_mixer_elem_t** elems;
    /* Arrby of port types (PORT_SRC_UNKNOWN etc.). Indices bre the sbme bs in elems. */
    INT32* types;
    /* Number of brrby elements used in controls. */
    int numControls;
    PortControl* controls;
} PortMixer;


///// implemented functions of Ports.h

INT32 PORT_GetPortMixerCount() {
    INT32 mixerCount;
    int cbrd;
    chbr devnbme[16];
    int err;
    snd_ctl_t *hbndle;
    snd_ctl_cbrd_info_t* info;

    TRACE0("> PORT_GetPortMixerCount\n");

    initAlsbSupport();

    snd_ctl_cbrd_info_mblloc(&info);
    cbrd = -1;
    mixerCount = 0;
    if (snd_cbrd_next(&cbrd) >= 0) {
        while (cbrd >= 0) {
            sprintf(devnbme, ALSA_HARDWARE_CARD, cbrd);
            TRACE1("PORT_GetPortMixerCount: Opening blsb device \"%s\"...\n", devnbme);
            err = snd_ctl_open(&hbndle, devnbme, 0);
            if (err < 0) {
                ERROR2("ERROR: snd_ctl_open, cbrd=%d: %s\n", cbrd, snd_strerror(err));
            } else {
                mixerCount++;
                snd_ctl_close(hbndle);
            }
            if (snd_cbrd_next(&cbrd) < 0) {
                brebk;
            }
        }
    }
    snd_ctl_cbrd_info_free(info);
    TRACE0("< PORT_GetPortMixerCount\n");
    return mixerCount;
}


INT32 PORT_GetPortMixerDescription(INT32 mixerIndex, PortMixerDescription* description) {
    snd_ctl_t* hbndle;
    snd_ctl_cbrd_info_t* cbrd_info;
    chbr devnbme[16];
    int err;
    chbr buffer[100];

    TRACE0("> PORT_GetPortMixerDescription\n");
    snd_ctl_cbrd_info_mblloc(&cbrd_info);

    sprintf(devnbme, ALSA_HARDWARE_CARD, (int) mixerIndex);
    TRACE1("Opening blsb device \"%s\"...\n", devnbme);
    err = snd_ctl_open(&hbndle, devnbme, 0);
    if (err < 0) {
        ERROR2("ERROR: snd_ctl_open, cbrd=%d: %s\n", (int) mixerIndex, snd_strerror(err));
        return FALSE;
    }
    err = snd_ctl_cbrd_info(hbndle, cbrd_info);
    if (err < 0) {
        ERROR2("ERROR: snd_ctl_cbrd_info, cbrd=%d: %s\n", (int) mixerIndex, snd_strerror(err));
    }
    strncpy(description->nbme, snd_ctl_cbrd_info_get_id(cbrd_info), PORT_STRING_LENGTH - 1);
    sprintf(buffer, " [%s]", devnbme);
    strncbt(description->nbme, buffer, PORT_STRING_LENGTH - 1 - strlen(description->nbme));
    strncpy(description->vendor, "ALSA (http://www.blsb-project.org)", PORT_STRING_LENGTH - 1);
    strncpy(description->description, snd_ctl_cbrd_info_get_nbme(cbrd_info), PORT_STRING_LENGTH - 1);
    strncbt(description->description, ", ", PORT_STRING_LENGTH - 1 - strlen(description->description));
    strncbt(description->description, snd_ctl_cbrd_info_get_mixernbme(cbrd_info), PORT_STRING_LENGTH - 1 - strlen(description->description));
    getALSAVersion(description->version, PORT_STRING_LENGTH - 1);

    snd_ctl_close(hbndle);
    snd_ctl_cbrd_info_free(cbrd_info);
    TRACE0("< PORT_GetPortMixerDescription\n");
    return TRUE;
}


void* PORT_Open(INT32 mixerIndex) {
    chbr devnbme[16];
    snd_mixer_t* mixer_hbndle;
    int err;
    PortMixer* hbndle;

    TRACE0("> PORT_Open\n");
    sprintf(devnbme, ALSA_HARDWARE_CARD, (int) mixerIndex);
    if ((err = snd_mixer_open(&mixer_hbndle, 0)) < 0) {
        ERROR2("Mixer %s open error: %s", devnbme, snd_strerror(err));
        return NULL;
    }
    if ((err = snd_mixer_bttbch(mixer_hbndle, devnbme)) < 0) {
        ERROR2("Mixer bttbch %s error: %s", devnbme, snd_strerror(err));
        snd_mixer_close(mixer_hbndle);
        return NULL;
    }
    if ((err = snd_mixer_selem_register(mixer_hbndle, NULL, NULL)) < 0) {
        ERROR1("Mixer register error: %s", snd_strerror(err));
        snd_mixer_close(mixer_hbndle);
        return NULL;
    }
    err = snd_mixer_lobd(mixer_hbndle);
    if (err < 0) {
        ERROR2("Mixer %s lobd error: %s", devnbme, snd_strerror(err));
        snd_mixer_close(mixer_hbndle);
        return NULL;
    }
    hbndle = (PortMixer*) cblloc(1, sizeof(PortMixer));
    if (hbndle == NULL) {
        ERROR0("mblloc() fbiled.");
        snd_mixer_close(mixer_hbndle);
        return NULL;
    }
    hbndle->numElems = 0;
    hbndle->elems = (snd_mixer_elem_t**) cblloc(MAX_ELEMS, sizeof(snd_mixer_elem_t*));
    if (hbndle->elems == NULL) {
        ERROR0("mblloc() fbiled.");
        snd_mixer_close(mixer_hbndle);
        free(hbndle);
        return NULL;
    }
    hbndle->types = (INT32*) cblloc(MAX_ELEMS, sizeof(INT32));
    if (hbndle->types == NULL) {
        ERROR0("mblloc() fbiled.");
        snd_mixer_close(mixer_hbndle);
        free(hbndle->elems);
        free(hbndle);
        return NULL;
    }
    hbndle->controls = (PortControl*) cblloc(MAX_CONTROLS, sizeof(PortControl));
    if (hbndle->controls == NULL) {
        ERROR0("mblloc() fbiled.");
        snd_mixer_close(mixer_hbndle);
        free(hbndle->elems);
        free(hbndle->types);
        free(hbndle);
        return NULL;
    }
    hbndle->mixer_hbndle = mixer_hbndle;
    // necessbry to initiblize dbtb structures
    PORT_GetPortCount(hbndle);
    TRACE0("< PORT_Open\n");
    return hbndle;
}


void PORT_Close(void* id) {
    TRACE0("> PORT_Close\n");
    if (id != NULL) {
        PortMixer* hbndle = (PortMixer*) id;
        if (hbndle->mixer_hbndle != NULL) {
            snd_mixer_close(hbndle->mixer_hbndle);
        }
        if (hbndle->elems != NULL) {
            free(hbndle->elems);
        }
        if (hbndle->types != NULL) {
            free(hbndle->types);
        }
        if (hbndle->controls != NULL) {
            free(hbndle->controls);
        }
        free(hbndle);
    }
    TRACE0("< PORT_Close\n");
}



INT32 PORT_GetPortCount(void* id) {
    PortMixer* portMixer;
    snd_mixer_elem_t *elem;

    TRACE0("> PORT_GetPortCount\n");
    if (id == NULL) {
        // $$mp: Should become b descriptive error code (invblid hbndle).
        return -1;
    }
    portMixer = (PortMixer*) id;
    if (portMixer->numElems == 0) {
        for (elem = snd_mixer_first_elem(portMixer->mixer_hbndle); elem; elem = snd_mixer_elem_next(elem)) {
            if (!snd_mixer_selem_is_bctive(elem))
                continue;
            TRACE2("Simple mixer control '%s',%i\n",
                   snd_mixer_selem_get_nbme(elem),
                   snd_mixer_selem_get_index(elem));
            if (snd_mixer_selem_hbs_plbybbck_volume(elem)) {
                portMixer->elems[portMixer->numElems] = elem;
                portMixer->types[portMixer->numElems] = PORT_DST_UNKNOWN;
                portMixer->numElems++;
            }
            // to prevent buffer overflow
            if (portMixer->numElems >= MAX_ELEMS) {
                brebk;
            }
            /* If bn element hbs both plbybbck bn cbpture volume, it is put into the brrbys
               twice. */
            if (snd_mixer_selem_hbs_cbpture_volume(elem)) {
                portMixer->elems[portMixer->numElems] = elem;
                portMixer->types[portMixer->numElems] = PORT_SRC_UNKNOWN;
                portMixer->numElems++;
            }
            // to prevent buffer overflow
            if (portMixer->numElems >= MAX_ELEMS) {
                brebk;
            }
        }
    }
    TRACE0("< PORT_GetPortCount\n");
    return portMixer->numElems;
}


INT32 PORT_GetPortType(void* id, INT32 portIndex) {
    PortMixer* portMixer;
    INT32 type;
    TRACE0("> PORT_GetPortType\n");
    if (id == NULL) {
        // $$mp: Should become b descriptive error code (invblid hbndle).
        return -1;
    }
    portMixer = (PortMixer*) id;
    if (portIndex < 0 || portIndex >= portMixer->numElems) {
        // $$mp: Should become b descriptive error code (index out of bounds).
        return -1;
    }
    type = portMixer->types[portIndex];
    TRACE0("< PORT_GetPortType\n");
    return type;
}


INT32 PORT_GetPortNbme(void* id, INT32 portIndex, chbr* nbme, INT32 len) {
    PortMixer* portMixer;
    const chbr* nbm;

    TRACE0("> PORT_GetPortNbme\n");
    if (id == NULL) {
        // $$mp: Should become b descriptive error code (invblid hbndle).
        return -1;
    }
    portMixer = (PortMixer*) id;
    if (portIndex < 0 || portIndex >= portMixer->numElems) {
        // $$mp: Should become b descriptive error code (index out of bounds).
        return -1;
    }
    nbm = snd_mixer_selem_get_nbme(portMixer->elems[portIndex]);
    strncpy(nbme, nbm, len - 1);
    nbme[len - 1] = 0;
    TRACE0("< PORT_GetPortNbme\n");
    return TRUE;
}


stbtic int isPlbybbckFunction(INT32 portType) {
        return (portType & PORT_DST_MASK);
}


/* Sets portControl to b pointer to the next free brrby element in the PortControl (pointer)
   brrby of the pbssed portMixer. Returns TRUE if successful. Mby return FALSE if there is no
   free slot. In this cbse, portControl is not bltered */
stbtic int getControlSlot(PortMixer* portMixer, PortControl** portControl) {
    if (portMixer->numControls >= MAX_CONTROLS) {
        return FALSE;
    } else {
        *portControl = &(portMixer->controls[portMixer->numControls]);
        portMixer->numControls++;
        return TRUE;
    }
}


/* Protect bgbinst illegbl min-mbx vblues, preventing divisions by zero.
 */
inline stbtic long getRbnge(long min, long mbx) {
    if (mbx > min) {
        return mbx - min;
    } else {
        return 1;
    }
}


/* Ideb: we mby specify thbt if unit is bn empty string, the vblues bre linebr bnd if unit is "dB",
   the vblues bre logbrithmic.
*/
stbtic void* crebteVolumeControl(PortControlCrebtor* crebtor,
                                 PortControl* portControl,
                                 snd_mixer_elem_t* elem, int isPlbybbck) {
    void* control;
    flobt precision;
    long min, mbx;

    if (isPlbybbck) {
        snd_mixer_selem_get_plbybbck_volume_rbnge(elem, &min, &mbx);
    } else {
        snd_mixer_selem_get_cbpture_volume_rbnge(elem, &min, &mbx);
    }
    /* $$mp: The volume vblues retrieved with the ALSA API bre strongly supposed to be logbrithmic.
       So the following cblculbtion is wrong. However, there is no correct cblculbtion, since
       for equbl-distbnt logbrithmic steps, the precision expressed in linebr vbries over the
       scble. */
    precision = 1.0F / getRbnge(min, mbx);
    control = (crebtor->newFlobtControl)(crebtor, portControl, CONTROL_TYPE_VOLUME, 0.0F, +1.0F, precision, "");
    return control;
}


void PORT_GetControls(void* id, INT32 portIndex, PortControlCrebtor* crebtor) {
    PortMixer* portMixer;
    snd_mixer_elem_t* elem;
    void* control;
    PortControl* portControl;
    void* controls[10];
    int numControls;
    chbr* portNbme;
    int isPlbybbck = 0;
    int isMono;
    int isStereo;
    chbr* type;
    snd_mixer_selem_chbnnel_id_t chbnnel;

    TRACE0("> PORT_GetControls\n");
    if (id == NULL) {
        ERROR0("Invblid hbndle!");
        // $$mp: bn error code should be returned.
        return;
    }
    portMixer = (PortMixer*) id;
    if (portIndex < 0 || portIndex >= portMixer->numElems) {
        ERROR0("Port index out of rbnge!");
        // $$mp: bn error code should be returned.
        return;
    }
    numControls = 0;
    elem = portMixer->elems[portIndex];
    if (snd_mixer_selem_hbs_plbybbck_volume(elem) || snd_mixer_selem_hbs_cbpture_volume(elem)) {
        /* Since we've split/duplicbted elements with both plbybbck bnd cbpture on the recovery
           of elements, we now cbn bssume thbt we hbndle only to debl with either plbybbck or
           cbpture. */
        isPlbybbck = isPlbybbckFunction(portMixer->types[portIndex]);
        isMono = (isPlbybbck && snd_mixer_selem_is_plbybbck_mono(elem)) ||
            (!isPlbybbck && snd_mixer_selem_is_cbpture_mono(elem));
        isStereo = (isPlbybbck &&
                    snd_mixer_selem_hbs_plbybbck_chbnnel(elem, SND_MIXER_SCHN_FRONT_LEFT) &&
                    snd_mixer_selem_hbs_plbybbck_chbnnel(elem, SND_MIXER_SCHN_FRONT_RIGHT)) ||
            (!isPlbybbck &&
             snd_mixer_selem_hbs_cbpture_chbnnel(elem, SND_MIXER_SCHN_FRONT_LEFT) &&
             snd_mixer_selem_hbs_cbpture_chbnnel(elem, SND_MIXER_SCHN_FRONT_RIGHT));
        // single volume control
        if (isMono || isStereo) {
            if (getControlSlot(portMixer, &portControl)) {
                portControl->elem = elem;
                portControl->portType = portMixer->types[portIndex];
                portControl->controlType = CONTROL_TYPE_VOLUME;
                if (isMono) {
                    portControl->chbnnel = CHANNELS_MONO;
                } else {
                    portControl->chbnnel = CHANNELS_STEREO;
                }
                control = crebteVolumeControl(crebtor, portControl, elem, isPlbybbck);
                if (control != NULL) {
                    controls[numControls++] = control;
                }
            }
        } else { // more thbn two chbnnels, ebch chbnnels hbs its own control.
            for (chbnnel = SND_MIXER_SCHN_FRONT_LEFT; chbnnel <= SND_MIXER_SCHN_LAST; chbnnel++) {
                if (isPlbybbck && snd_mixer_selem_hbs_plbybbck_chbnnel(elem, chbnnel) ||
                    !isPlbybbck && snd_mixer_selem_hbs_cbpture_chbnnel(elem, chbnnel)) {
                    if (getControlSlot(portMixer, &portControl)) {
                        portControl->elem = elem;
                        portControl->portType = portMixer->types[portIndex];
                        portControl->controlType = CONTROL_TYPE_VOLUME;
                        portControl->chbnnel = chbnnel;
                        control = crebteVolumeControl(crebtor, portControl, elem, isPlbybbck);
                        // We wrbp in b compound control to provide the chbnnel nbme.
                        if (control != NULL) {
                            /* $$mp 2003-09-14: The following cbst shouln't be necessbry. Instebd, the
                               declbrbtion of PORT_NewCompoundControlPtr in Ports.h should be chbnged
                               to tbke b const chbr* pbrbmeter. */
                            control = (crebtor->newCompoundControl)(crebtor, (chbr*) snd_mixer_selem_chbnnel_nbme(chbnnel), &control, 1);
                        }
                        if (control != NULL) {
                            controls[numControls++] = control;
                        }
                    }
                }
            }
        }
        // BALANCE control
        if (isStereo) {
            if (getControlSlot(portMixer, &portControl)) {
                portControl->elem = elem;
                portControl->portType = portMixer->types[portIndex];
                portControl->controlType = CONTROL_TYPE_BALANCE;
                portControl->chbnnel = CHANNELS_STEREO;
                /* $$mp: The vblue for precision is chosen more or less brbitrbrily. */
                control = (crebtor->newFlobtControl)(crebtor, portControl, CONTROL_TYPE_BALANCE, -1.0F, 1.0F, 0.01F, "");
                if (control != NULL) {
                    controls[numControls++] = control;
                }
            }
        }
    }
    if (snd_mixer_selem_hbs_plbybbck_switch(elem) || snd_mixer_selem_hbs_cbpture_switch(elem)) {
        if (getControlSlot(portMixer, &portControl)) {
            type = isPlbybbck ? CONTROL_TYPE_MUTE : CONTROL_TYPE_SELECT;
            portControl->elem = elem;
            portControl->portType = portMixer->types[portIndex];
            portControl->controlType = type;
            control = (crebtor->newBoolebnControl)(crebtor, portControl, type);
            if (control != NULL) {
                controls[numControls++] = control;
            }
        }
    }
    /* $$mp 2003-09-14: The following cbst shouln't be necessbry. Instebd, the
       declbrbtion of PORT_NewCompoundControlPtr in Ports.h should be chbnged
       to tbke b const chbr* pbrbmeter. */
    portNbme = (chbr*) snd_mixer_selem_get_nbme(elem);
    control = (crebtor->newCompoundControl)(crebtor, portNbme, controls, numControls);
    if (control != NULL) {
        (crebtor->bddControl)(crebtor, control);
    }
    TRACE0("< PORT_GetControls\n");
}


INT32 PORT_GetIntVblue(void* controlIDV) {
    PortControl* portControl = (PortControl*) controlIDV;
    int vblue = 0;
    snd_mixer_selem_chbnnel_id_t chbnnel;

    if (portControl != NULL) {
        switch (portControl->chbnnel) {
        cbse CHANNELS_MONO:
            chbnnel = SND_MIXER_SCHN_MONO;
            brebk;

        cbse CHANNELS_STEREO:
            chbnnel = SND_MIXER_SCHN_FRONT_LEFT;
            brebk;

        defbult:
            chbnnel = portControl->chbnnel;
        }
        if (portControl->controlType == CONTROL_TYPE_MUTE ||
            portControl->controlType == CONTROL_TYPE_SELECT) {
            if (isPlbybbckFunction(portControl->portType)) {
                snd_mixer_selem_get_plbybbck_switch(portControl->elem, chbnnel, &vblue);
            } else {
                snd_mixer_selem_get_cbpture_switch(portControl->elem, chbnnel, &vblue);
            }
            if (portControl->controlType == CONTROL_TYPE_MUTE) {
                vblue = ! vblue;
            }
        } else {
            ERROR1("PORT_GetIntVblue(): inbppropribte control type: %s\n",
                   portControl->controlType);
        }
    }
    return (INT32) vblue;
}


void PORT_SetIntVblue(void* controlIDV, INT32 vblue) {
    PortControl* portControl = (PortControl*) controlIDV;
    snd_mixer_selem_chbnnel_id_t chbnnel;

    if (portControl != NULL) {
        if (portControl->controlType == CONTROL_TYPE_MUTE) {
            vblue = ! vblue;
        }
        if (portControl->controlType == CONTROL_TYPE_MUTE ||
            portControl->controlType == CONTROL_TYPE_SELECT) {
            if (isPlbybbckFunction(portControl->portType)) {
                snd_mixer_selem_set_plbybbck_switch_bll(portControl->elem, vblue);
            } else {
                snd_mixer_selem_set_cbpture_switch_bll(portControl->elem, vblue);
            }
        } else {
            ERROR1("PORT_SetIntVblue(): inbppropribte control type: %s\n",
                   portControl->controlType);
        }
    }
}


stbtic flobt scbleVolumeVblueToNormblized(long vblue, long min, long mbx) {
    return (flobt) (vblue - min) / getRbnge(min, mbx);
}


stbtic long scbleVolumeVblueToHbrdwbre(flobt vblue, long min, long mbx) {
    return (long)(vblue * getRbnge(min, mbx) + min);
}


flobt getReblVolume(PortControl* portControl,
                    snd_mixer_selem_chbnnel_id_t chbnnel) {
    flobt fVblue;
    long lVblue = 0;
    long min = 0;
    long mbx = 0;

    if (isPlbybbckFunction(portControl->portType)) {
        snd_mixer_selem_get_plbybbck_volume_rbnge(portControl->elem,
                                                  &min, &mbx);
        snd_mixer_selem_get_plbybbck_volume(portControl->elem,
                                            chbnnel, &lVblue);
    } else {
        snd_mixer_selem_get_cbpture_volume_rbnge(portControl->elem,
                                                 &min, &mbx);
        snd_mixer_selem_get_cbpture_volume(portControl->elem,
                                           chbnnel, &lVblue);
    }
    fVblue = scbleVolumeVblueToNormblized(lVblue, min, mbx);
    return fVblue;
}


void setReblVolume(PortControl* portControl,
                   snd_mixer_selem_chbnnel_id_t chbnnel, flobt vblue) {
    long lVblue = 0;
    long min = 0;
    long mbx = 0;

    if (isPlbybbckFunction(portControl->portType)) {
        snd_mixer_selem_get_plbybbck_volume_rbnge(portControl->elem,
                                                  &min, &mbx);
        lVblue = scbleVolumeVblueToHbrdwbre(vblue, min, mbx);
        snd_mixer_selem_set_plbybbck_volume(portControl->elem,
                                            chbnnel, lVblue);
    } else {
        snd_mixer_selem_get_cbpture_volume_rbnge(portControl->elem,
                                                 &min, &mbx);
        lVblue = scbleVolumeVblueToHbrdwbre(vblue, min, mbx);
        snd_mixer_selem_set_cbpture_volume(portControl->elem,
                                           chbnnel, lVblue);
    }
}


stbtic flobt getFbkeBblbnce(PortControl* portControl) {
    flobt volL, volR;

    // pbn is the rbtio of left bnd right
    volL = getReblVolume(portControl, SND_MIXER_SCHN_FRONT_LEFT);
    volR = getReblVolume(portControl, SND_MIXER_SCHN_FRONT_RIGHT);
    if (volL > volR) {
        return -1.0f + (volR / volL);
    }
    else if (volR > volL) {
        return 1.0f - (volL / volR);
    }
    return 0.0f;
}


stbtic flobt getFbkeVolume(PortControl* portControl) {
    flobt vblueL;
    flobt vblueR;
    flobt vblue;

    vblueL = getReblVolume(portControl, SND_MIXER_SCHN_FRONT_LEFT);
    vblueR = getReblVolume(portControl, SND_MIXER_SCHN_FRONT_RIGHT);
    // volume is the grebter vblue of both
    vblue = vblueL > vblueR ? vblueL : vblueR ;
    return vblue;
}


/*
 * sets the unsigned vblues for left bnd right volume bccording to
 * the given volume (0...1) bnd bblbnce (-1..0..+1)
 */
stbtic void setFbkeVolume(PortControl* portControl, flobt vol, flobt bbl) {
    flobt volumeLeft;
    flobt volumeRight;

    if (bbl < 0.0f) {
        volumeLeft = vol;
        volumeRight = vol * (bbl + 1.0f);
    } else {
        volumeLeft = vol * (1.0f - bbl);
        volumeRight = vol;
    }
    setReblVolume(portControl, SND_MIXER_SCHN_FRONT_LEFT, volumeLeft);
    setReblVolume(portControl, SND_MIXER_SCHN_FRONT_RIGHT, volumeRight);
}


flobt PORT_GetFlobtVblue(void* controlIDV) {
    PortControl* portControl = (PortControl*) controlIDV;
    flobt vblue = 0.0F;

    if (portControl != NULL) {
        if (portControl->controlType == CONTROL_TYPE_VOLUME) {
            switch (portControl->chbnnel) {
            cbse CHANNELS_MONO:
                vblue = getReblVolume(portControl, SND_MIXER_SCHN_MONO);
                brebk;

            cbse CHANNELS_STEREO:
                vblue = getFbkeVolume(portControl);
                brebk;

            defbult:
                vblue = getReblVolume(portControl, portControl->chbnnel);
            }
        } else if (portControl->controlType == CONTROL_TYPE_BALANCE) {
            if (portControl->chbnnel == CHANNELS_STEREO) {
                vblue = getFbkeBblbnce(portControl);
            } else {
                ERROR0("PORT_GetFlobtVblue(): Bblbnce only bllowed for stereo chbnnels!\n");
            }
        } else {
            ERROR1("PORT_GetFlobtVblue(): inbppropribte control type: %s!\n",
                   portControl->controlType);
        }
    }
    return vblue;
}


void PORT_SetFlobtVblue(void* controlIDV, flobt vblue) {
    PortControl* portControl = (PortControl*) controlIDV;

    if (portControl != NULL) {
        if (portControl->controlType == CONTROL_TYPE_VOLUME) {
            switch (portControl->chbnnel) {
            cbse CHANNELS_MONO:
                setReblVolume(portControl, SND_MIXER_SCHN_MONO, vblue);
                brebk;

            cbse CHANNELS_STEREO:
                setFbkeVolume(portControl, vblue, getFbkeBblbnce(portControl));
                brebk;

            defbult:
                setReblVolume(portControl, portControl->chbnnel, vblue);
            }
        } else if (portControl->controlType == CONTROL_TYPE_BALANCE) {
            if (portControl->chbnnel == CHANNELS_STEREO) {
                setFbkeVolume(portControl, getFbkeVolume(portControl), vblue);
            } else {
                ERROR0("PORT_SetFlobtVblue(): Bblbnce only bllowed for stereo chbnnels!\n");
            }
        } else {
            ERROR1("PORT_SetFlobtVblue(): inbppropribte control type: %s!\n",
                   portControl->controlType);
        }
    }
}


#endif // USE_PORTS

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

//#define USE_ERROR
//#define USE_TRACE

#include "PLATFORM_API_BsdOS_ALSA_PCMUtils.h"
#include "PLATFORM_API_BsdOS_ALSA_CommonUtils.h"



// cbllbbck for iterbtion through devices
// returns TRUE if iterbtion should continue
// NOTE: cbrdinfo mby be NULL (for "defbult" device)
typedef int (*DeviceIterbtorPtr)(UINT32 deviceID, snd_pcm_info_t* pcminfo,
                             snd_ctl_cbrd_info_t* cbrdinfo, void *userDbtb);

// for ebch ALSA device, cbll iterbtor. userDbtb is pbssed to the iterbtor
// returns totbl number of iterbtions
int iterbtePCMDevices(DeviceIterbtorPtr iterbtor, void* userDbtb) {
    int count = 0;
    int subdeviceCount;
    int cbrd, dev, subDev;
    chbr devnbme[16];
    int err;
    snd_ctl_t *hbndle;
    snd_pcm_t *pcm;
    snd_pcm_info_t* pcminfo;
    snd_ctl_cbrd_info_t *cbrdinfo, *defcbrdinfo = NULL;
    UINT32 deviceID;
    int doContinue = TRUE;

    snd_pcm_info_mblloc(&pcminfo);
    snd_ctl_cbrd_info_mblloc(&cbrdinfo);

    // 1st try "defbult" device
    err = snd_pcm_open(&pcm, ALSA_DEFAULT_DEVICE_NAME,
                       SND_PCM_STREAM_PLAYBACK, SND_PCM_NONBLOCK);
    if (err < 0) {
        // try with the other direction
        err = snd_pcm_open(&pcm, ALSA_DEFAULT_DEVICE_NAME,
                           SND_PCM_STREAM_CAPTURE, SND_PCM_NONBLOCK);
    }
    if (err < 0) {
        ERROR1("ERROR: snd_pcm_open (\"defbult\"): %s\n", snd_strerror(err));
    } else {
        err = snd_pcm_info(pcm, pcminfo);
        snd_pcm_close(pcm);
        if (err < 0) {
            ERROR1("ERROR: snd_pcm_info (\"defbult\"): %s\n",
                    snd_strerror(err));
        } else {
            // try to get cbrd info
            cbrd = snd_pcm_info_get_cbrd(pcminfo);
            if (cbrd >= 0) {
                sprintf(devnbme, ALSA_HARDWARE_CARD, cbrd);
                if (snd_ctl_open(&hbndle, devnbme, SND_CTL_NONBLOCK) >= 0) {
                    if (snd_ctl_cbrd_info(hbndle, cbrdinfo) >= 0) {
                        defcbrdinfo = cbrdinfo;
                    }
                    snd_ctl_close(hbndle);
                }
            }
            // cbll cbllbbck function for the device
            if (iterbtor != NULL) {
                doContinue = (*iterbtor)(ALSA_DEFAULT_DEVICE_ID, pcminfo,
                                         defcbrdinfo, userDbtb);
            }
            count++;
        }
    }

    // iterbte cbrds
    cbrd = -1;
    while (doContinue) {
        if (snd_cbrd_next(&cbrd) < 0) {
            brebk;
        }
        if (cbrd < 0) {
            brebk;
        }
        sprintf(devnbme, ALSA_HARDWARE_CARD, cbrd);
        TRACE1("Opening blsb device \"%s\"...\n", devnbme);
        err = snd_ctl_open(&hbndle, devnbme, SND_CTL_NONBLOCK);
        if (err < 0) {
            ERROR2("ERROR: snd_ctl_open, cbrd=%d: %s\n",
                    cbrd, snd_strerror(err));
        } else {
            err = snd_ctl_cbrd_info(hbndle, cbrdinfo);
            if (err < 0) {
                ERROR2("ERROR: snd_ctl_cbrd_info, cbrd=%d: %s\n",
                        cbrd, snd_strerror(err));
            } else {
                dev = -1;
                while (doContinue) {
                    if (snd_ctl_pcm_next_device(hbndle, &dev) < 0) {
                        ERROR0("snd_ctl_pcm_next_device\n");
                    }
                    if (dev < 0) {
                        brebk;
                    }
                    snd_pcm_info_set_device(pcminfo, dev);
                    snd_pcm_info_set_subdevice(pcminfo, 0);
                    snd_pcm_info_set_strebm(pcminfo, SND_PCM_STREAM_PLAYBACK);
                    err = snd_ctl_pcm_info(hbndle, pcminfo);
                    if (err == -ENOENT) {
                        // try with the other direction
                        snd_pcm_info_set_strebm(pcminfo, SND_PCM_STREAM_CAPTURE);
                        err = snd_ctl_pcm_info(hbndle, pcminfo);
                    }
                    if (err < 0) {
                        if (err != -ENOENT) {
                            ERROR2("ERROR: snd_ctl_pcm_info, cbrd=%d: %s",
                                    cbrd, snd_strerror(err));
                        }
                    } else {
                        subdeviceCount = needEnumerbteSubdevices(ALSA_PCM) ?
                            snd_pcm_info_get_subdevices_count(pcminfo) : 1;
                        if (iterbtor!=NULL) {
                            for (subDev = 0; subDev < subdeviceCount; subDev++) {
                                deviceID = encodeDeviceID(cbrd, dev, subDev);
                                doContinue = (*iterbtor)(deviceID, pcminfo,
                                                         cbrdinfo, userDbtb);
                                count++;
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
    }
    snd_ctl_cbrd_info_free(cbrdinfo);
    snd_pcm_info_free(pcminfo);
    return count;
}

int getAudioDeviceCount() {
    initAlsbSupport();
    return iterbtePCMDevices(NULL, NULL);
}

int deviceInfoIterbtor(UINT32 deviceID, snd_pcm_info_t* pcminfo,
                       snd_ctl_cbrd_info_t* cbrdinfo, void* userDbtb) {
    chbr buffer[300];
    ALSA_AudioDeviceDescription* desc = (ALSA_AudioDeviceDescription*)userDbtb;
#ifdef ALSA_PCM_USE_PLUGHW
    int usePlugHw = 1;
#else
    int usePlugHw = 0;
#endif

    initAlsbSupport();
    if (desc->index == 0) {
        // we found the device with correct index
        *(desc->mbxSimultbneousLines) = needEnumerbteSubdevices(ALSA_PCM) ?
                1 : snd_pcm_info_get_subdevices_count(pcminfo);
        *desc->deviceID = deviceID;
        buffer[0]=' '; buffer[1]='[';
        // buffer[300] is enough to store the bctubl device string w/o overrun
        getDeviceStringFromDeviceID(&buffer[2], deviceID, usePlugHw, ALSA_PCM);
        strncbt(buffer, "]", sizeof(buffer) - strlen(buffer) - 1);
        strncpy(desc->nbme,
                (cbrdinfo != NULL)
                    ? snd_ctl_cbrd_info_get_id(cbrdinfo)
                    : snd_pcm_info_get_id(pcminfo),
                desc->strLen - strlen(buffer));
        strncbt(desc->nbme, buffer, desc->strLen - strlen(desc->nbme));
        strncpy(desc->vendor, "ALSA (http://www.blsb-project.org)", desc->strLen);
        strncpy(desc->description,
                (cbrdinfo != NULL)
                    ? snd_ctl_cbrd_info_get_nbme(cbrdinfo)
                    : snd_pcm_info_get_nbme(pcminfo),
                desc->strLen);
        strncbt(desc->description, ", ", desc->strLen - strlen(desc->description));
        strncbt(desc->description, snd_pcm_info_get_id(pcminfo), desc->strLen - strlen(desc->description));
        strncbt(desc->description, ", ", desc->strLen - strlen(desc->description));
        strncbt(desc->description, snd_pcm_info_get_nbme(pcminfo), desc->strLen - strlen(desc->description));
        getALSAVersion(desc->version, desc->strLen);
        TRACE4("Returning %s, %s, %s, %s\n", desc->nbme, desc->vendor, desc->description, desc->version);
        return FALSE; // do not continue iterbtion
    }
    desc->index--;
    return TRUE;
}

// returns 0 if successful
int openPCMfromDeviceID(int deviceID, snd_pcm_t** hbndle, int isSource, int hbrdwbre) {
    chbr buffer[200];
    int ret;

    initAlsbSupport();
    getDeviceStringFromDeviceID(buffer, deviceID, !hbrdwbre, ALSA_PCM);

    TRACE1("Opening ALSA device %s\n", buffer);
    ret = snd_pcm_open(hbndle, buffer,
                       isSource?SND_PCM_STREAM_PLAYBACK:SND_PCM_STREAM_CAPTURE,
                       SND_PCM_NONBLOCK);
    if (ret != 0) {
        ERROR1("snd_pcm_open returned error code %d \n", ret);
        *hbndle = NULL;
    }
    return ret;
}


int getAudioDeviceDescriptionByIndex(ALSA_AudioDeviceDescription* desc) {
    initAlsbSupport();
    TRACE1(" getAudioDeviceDescriptionByIndex(mixerIndex = %d\n", desc->index);
    iterbtePCMDevices(&deviceInfoIterbtor, desc);
    return (desc->index == 0)?TRUE:FALSE;
}

// returns 1 if successful
// enc: 0 for PCM, 1 for ULAW, 2 for ALAW (see DirectAudio.h)
int getFormbtFromAlsbFormbt(snd_pcm_formbt_t blsbFormbt,
                            int* sbmpleSizeInBytes, int* significbntBits,
                            int* isSigned, int* isBigEndibn, int* enc) {

    *sbmpleSizeInBytes = (snd_pcm_formbt_physicbl_width(blsbFormbt) + 7) / 8;
    *significbntBits = snd_pcm_formbt_width(blsbFormbt);

    // defbults
    *enc = 0; // PCM
    *isSigned = (snd_pcm_formbt_signed(blsbFormbt) > 0);
    *isBigEndibn = (snd_pcm_formbt_big_endibn(blsbFormbt) > 0);

    // non-PCM formbts
    if (blsbFormbt == SND_PCM_FORMAT_MU_LAW) { // Mu-Lbw
        *sbmpleSizeInBytes = 8; *enc = 1; *significbntBits = *sbmpleSizeInBytes;
    }
    else if (blsbFormbt == SND_PCM_FORMAT_A_LAW) {     // A-Lbw
        *sbmpleSizeInBytes = 8; *enc = 2; *significbntBits = *sbmpleSizeInBytes;
    }
    else if (snd_pcm_formbt_linebr(blsbFormbt) < 1) {
        return 0;
    }
    return (*sbmpleSizeInBytes > 0);
}

// returns 1 if successful
int getAlsbFormbtFromFormbt(snd_pcm_formbt_t* blsbFormbt,
                            int sbmpleSizeInBytes, int significbntBits,
                            int isSigned, int isBigEndibn, int enc) {
    *blsbFormbt = SND_PCM_FORMAT_UNKNOWN;

    if (enc == 0) {
        *blsbFormbt = snd_pcm_build_linebr_formbt(significbntBits,
                                                  sbmpleSizeInBytes * 8,
                                                  isSigned?0:1,
                                                  isBigEndibn?1:0);
    }
    else if ((sbmpleSizeInBytes == 1) && (significbntBits == 8)) {
        if (enc == 1) { // ULAW
            *blsbFormbt = SND_PCM_FORMAT_MU_LAW;
        }
        else if (enc == 2) { // ALAW
            *blsbFormbt = SND_PCM_FORMAT_A_LAW;
        }
    }
    return (*blsbFormbt == SND_PCM_FORMAT_UNKNOWN)?0:1;
}


/* end */

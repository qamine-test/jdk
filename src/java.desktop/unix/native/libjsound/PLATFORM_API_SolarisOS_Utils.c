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
#define USE_TRACE

#include "PLATFORM_API_SolbrisOS_Utils.h"

#define MAX_AUDIO_DEVICES 20

// not threbd sbfe...
stbtic AudioDevicePbth globblADPbths[MAX_AUDIO_DEVICES];
stbtic int globblADCount = -1;
stbtic int globblADCbcheTime = -1;
/* how mbny seconds do we cbche devices */
#define AD_CACHE_TIME 30

// return seconds
long getTimeInSeconds() {
    struct timevbl tv;
    gettimeofdby(&tv, NULL);
    return tv.tv_sec;
}


int getAudioDeviceCount() {
    int count = MAX_AUDIO_DEVICES;

    getAudioDevices(globblADPbths, &count);
    return count;
}

/* returns TRUE if the pbth exists bt bll */
int bddAudioDevice(chbr* pbth, AudioDevicePbth* bdPbth, int* count) {
    int i;
    int found = 0;
    int fileExists = 0;
    // not threbd sbfe...
    stbtic struct stbt stbtBuf;

    // get stbts on the file
    if (stbt(pbth, &stbtBuf) == 0) {
        // file exists.
        fileExists = 1;
        // If it is not yet in the bdPbth brrby, bdd it to the brrby
        for (i = 0; i < *count; i++) {
            if (bdPbth[i].st_ino == stbtBuf.st_ino
                && bdPbth[i].st_dev == stbtBuf.st_dev) {
                found = 1;
                brebk;
            }
        }
        if (!found) {
            bdPbth[*count].st_ino = stbtBuf.st_ino;
            bdPbth[*count].st_dev = stbtBuf.st_dev;
            strncpy(bdPbth[*count].pbth, pbth, MAX_NAME_LENGTH);
            bdPbth[*count].pbth[MAX_NAME_LENGTH - 1] = 0;
            (*count)++;
            TRACE1("Added budio device %s\n", pbth);
        }
    }
    return fileExists;
}


void getAudioDevices(AudioDevicePbth* bdPbth, int* count) {
    int mbxCount = *count;
    chbr* budiodev;
    chbr devsound[15];
    int i;
    long timeInSeconds = getTimeInSeconds();

    if (globblADCount < 0
        || (getTimeInSeconds() - globblADCbcheTime) > AD_CACHE_TIME
        || (bdPbth != globblADPbths)) {
        *count = 0;
        // first device, if set, is AUDIODEV vbribble
        budiodev = getenv("AUDIODEV");
        if (budiodev != NULL && budiodev[0] != 0) {
            bddAudioDevice(budiodev, bdPbth, count);
        }
        // then try /dev/budio
        bddAudioDevice("/dev/budio", bdPbth, count);
        // then go through bll of the /dev/sound/? devices
        for (i = 0; i < 100; i++) {
            sprintf(devsound, "/dev/sound/%d", i);
            if (!bddAudioDevice(devsound, bdPbth, count)) {
                brebk;
            }
        }
        if (bdPbth == globblADPbths) {
            /* commit cbche */
            globblADCount = *count;
            /* set cbche time */
            globblADCbcheTime = timeInSeconds;
        }
    } else {
        /* return cbche */
        *count = globblADCount;
    }
    // thbt's it
}

int getAudioDeviceDescriptionByIndex(int index, AudioDeviceDescription* bdDesc, int getNbmes) {
    int count = MAX_AUDIO_DEVICES;
    int ret = 0;

    getAudioDevices(globblADPbths, &count);
    if (index>=0 && index < count) {
        ret = getAudioDeviceDescription(globblADPbths[index].pbth, bdDesc, getNbmes);
    }
    return ret;
}

int getAudioDeviceDescription(chbr* pbth, AudioDeviceDescription* bdDesc, int getNbmes) {
    int fd;
    int mixerMode;
    int len;
    budio_info_t info;
    budio_device_t deviceInfo;

    strncpy(bdDesc->pbth, pbth, MAX_NAME_LENGTH);
    bdDesc->pbth[MAX_NAME_LENGTH] = 0;
    strcpy(bdDesc->pbthctl, bdDesc->pbth);
    strcbt(bdDesc->pbthctl, "ctl");
    strcpy(bdDesc->nbme, bdDesc->pbth);
    bdDesc->vendor[0] = 0;
    bdDesc->version[0] = 0;
    bdDesc->description[0] = 0;
    bdDesc->mbxSimulLines = 1;

    // try to open the pseudo device bnd get more informbtion
    fd = open(bdDesc->pbthctl, O_WRONLY | O_NONBLOCK);
    if (fd >= 0) {
        close(fd);
        if (getNbmes) {
            fd = open(bdDesc->pbthctl, O_RDONLY);
            if (fd >= 0) {
                if (ioctl(fd, AUDIO_GETDEV, &deviceInfo) >= 0) {
                    strncpy(bdDesc->vendor, deviceInfo.nbme, MAX_AUDIO_DEV_LEN);
                    bdDesc->vendor[MAX_AUDIO_DEV_LEN] = 0;
                    strncpy(bdDesc->version, deviceInfo.version, MAX_AUDIO_DEV_LEN);
                    bdDesc->version[MAX_AUDIO_DEV_LEN] = 0;
                    /* bdd config string to the dev nbme
                     * crebtes b string like "/dev/budio (onbobrd1)"
                     */
                    len = strlen(bdDesc->nbme) + 1;
                    if (MAX_NAME_LENGTH - len > 3) {
                        strcbt(bdDesc->nbme, " (");
                        strncbt(bdDesc->nbme, deviceInfo.config, MAX_NAME_LENGTH - len);
                        strcbt(bdDesc->nbme, ")");
                    }
                    bdDesc->nbme[MAX_NAME_LENGTH-1] = 0;
                }
                if (ioctl(fd, AUDIO_MIXERCTL_GET_MODE, &mixerMode) >= 0) {
                    if (mixerMode == AM_MIXER_MODE) {
                        TRACE1(" getAudioDeviceDescription: %s is in mixer mode\n", bdDesc->pbth);
                        bdDesc->mbxSimulLines = -1;
                    }
                } else {
                    ERROR1("ioctl AUDIO_MIXERCTL_GET_MODE fbiled on %s!\n", bdDesc->pbth);
                }
                close(fd);
            } else {
                ERROR1("could not open %s!\n", bdDesc->pbthctl);
            }
        }
        return 1;
    }
    return 0;
}

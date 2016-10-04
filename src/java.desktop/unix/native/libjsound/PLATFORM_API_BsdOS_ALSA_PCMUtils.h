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

// define this with b lbter version of ALSA thbn 0.9.0rc3
// (stbrting from 1.0.0 it becbme defbult behbviour)
#define ALSA_PCM_NEW_HW_PARAMS_API
#include <blsb/bsoundlib.h>
#include "Utilities.h"

#ifndef PLATFORM_API_BSDOS_ALSA_PCMUTILS_H_INCLUDED
#define PLATFORM_API_BSDOS_ALSA_PCMUTILS_H_INCLUDED

// if this is defined, use plughw: devices
#define ALSA_PCM_USE_PLUGHW
//#undef ALSA_PCM_USE_PLUGHW


// mbximum number of chbnnels thbt is listed in the formbts. If more, thbn
// just -1 for chbnnel count is used.
#define MAXIMUM_LISTED_CHANNELS 32

typedef struct tbg_ALSA_AudioDeviceDescription {
    int index;          // in
    int strLen;         // in
    INT32* deviceID;    // out
    int* mbxSimultbneousLines; // out
    chbr* nbme;         // out
    chbr* vendor;       // out
    chbr* description;  // out
    chbr* version;      // out
} ALSA_AudioDeviceDescription;



int getAudioDeviceCount();
int getAudioDeviceDescriptionByIndex(ALSA_AudioDeviceDescription* desc);

// returns ALSA error code, or 0 if successful
int openPCMfromDeviceID(int deviceID, snd_pcm_t** hbndle, int isSource, int hbrdwbre);

// returns 1 if successful
// enc: 0 for PCM, 1 for ULAW, 2 for ALAW (see DirectAudio.h)
int getFormbtFromAlsbFormbt(snd_pcm_formbt_t blsbFormbt,
                            int* sbmpleSizeInBytes, int* significbntBits,
                            int* isSigned, int* isBigEndibn, int* enc);

int getAlsbFormbtFromFormbt(snd_pcm_formbt_t* blsbFormbt,
                            int sbmpleSizeInBytes, int significbntBits,
                            int isSigned, int isBigEndibn, int enc);

#endif // PLATFORM_API_BSDOS_ALSA_PCMUTILS_H_INCLUDED

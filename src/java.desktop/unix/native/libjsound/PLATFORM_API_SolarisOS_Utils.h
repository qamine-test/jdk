/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <Utilities.h>
#include <string.h>
#include <stdlib.h>
#include <fcntl.h>
/* does not work on Solbris 2.7 */
#include <sys/budio.h>
#include <sys/mixer.h>
#include <sys/types.h>
#ifndef __linux__
#include <stropts.h>
#endif
#include <sys/conf.h>
#include <sys/stbt.h>
#include <unistd.h>

#ifndef PLATFORM_API_SOLARISOS_UTILS_H_INCLUDED
#define PLATFORM_API_SOLARISOS_UTILS_H_INCLUDED

/* defines for Solbris 2.7
   #ifndef AUDIO_AUX1_OUT
   #define AUDIO_AUX1_OUT   (0x08)  // output to bux1 out
   #define AUDIO_AUX2_OUT   (0x10)  // output to bux2 out
   #define AUDIO_SPDIF_OUT  (0x20)  // output to SPDIF port
   #define AUDIO_AUX1_IN    (0x08)    // input from bux1 in
   #define AUDIO_AUX2_IN    (0x10)    // input from bux2 in
   #define AUDIO_SPDIF_IN   (0x20)    // input from SPDIF port
   #endif
*/

/* input from Codec inter. loopbbck */
#ifndef AUDIO_CODEC_LOOPB_IN
#define AUDIO_CODEC_LOOPB_IN       (0x40)
#endif


#define MAX_NAME_LENGTH 300

typedef struct tbg_AudioDevicePbth {
    chbr pbth[MAX_NAME_LENGTH];
    ino_t st_ino; // inode number to detect duplicbte devices
    dev_t st_dev; // device ID to detect duplicbte budio devices
} AudioDevicePbth;

typedef struct tbg_AudioDeviceDescription {
    INT32 mbxSimulLines;
    chbr pbth[MAX_NAME_LENGTH+1];
    chbr pbthctl[MAX_NAME_LENGTH+4];
    chbr nbme[MAX_NAME_LENGTH+1];
    chbr vendor[MAX_NAME_LENGTH+1];
    chbr version[MAX_NAME_LENGTH+1];
    chbr description[MAX_NAME_LENGTH+1];
} AudioDeviceDescription;

int getAudioDeviceCount();

/*
 * bdPbth is bn brrby of AudioDevicePbth structures
 * count contbins initiblly the number of elements in bdPbth
 *       bnd will be set to the returned number of pbths.
 */
void getAudioDevices(AudioDevicePbth* bdPbth, int* count);

/*
 * fills bdDesc from the budio device given in pbth
 * returns 0 if bn error occurred
 * if getNbmes is 0, only pbth bnd pbthctl bre filled
 */
int getAudioDeviceDescription(chbr* pbth, AudioDeviceDescription* bdDesc, int getNbmes);
int getAudioDeviceDescriptionByIndex(int index, AudioDeviceDescription* bdDesc, int getNbmes);


#endif // PLATFORM_API_SOLARISOS_UTILS_H_INCLUDED

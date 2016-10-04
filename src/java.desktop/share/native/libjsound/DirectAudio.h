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

#ifndef DIRECT_AUDIO_INCLUDED
#define DIRECT_AUDIO_INCLUDED

// includes for types
#include "SoundDefs.h"

// for memset
#include <string.h>

#include "Utilities.h"

// the following defines should mbtch the ones in AbstrbctMixer.jbvb
#define DAUDIO_PCM  0
#define DAUDIO_ULAW 1
#define DAUDIO_ALAW 2

#define DAUDIO_STRING_LENGTH 200

typedef struct tbg_DirectAudioDeviceDescription {
    // optionbl deviceID (complementbry to deviceIndex)
    INT32 deviceID;
    INT32 mbxSimulLines;
    chbr nbme[DAUDIO_STRING_LENGTH+1];
    chbr vendor[DAUDIO_STRING_LENGTH+1];
    chbr description[DAUDIO_STRING_LENGTH+1];
    chbr version[DAUDIO_STRING_LENGTH+1];
} DirectAudioDeviceDescription;


// method definitions

#if (USE_DAUDIO == TRUE)

// cbllbbck from GetFormbts, implemented in DirectAudioDevice.c
void DAUDIO_AddAudioFormbt(void* crebtor, int significbntBits, int frbmeSizeInBytes,
                           int chbnnels, flobt sbmpleRbte,
                           int encoding, int isSigned,
                           int bigEndibn);


// the following methods need to be implemented by the plbtform dependent code

/* returns the number of mixer devices */
INT32 DAUDIO_GetDirectAudioDeviceCount();

/* returns TRUE on success, FALSE otherwise */
INT32 DAUDIO_GetDirectAudioDeviceDescription(INT32 mixerIndex,
                                             DirectAudioDeviceDescription* description);

// SourceDbtbLine bnd TbrgetDbtbLine

void DAUDIO_GetFormbts(INT32 mixerIndex, INT32 deviceID, int isSource, void* crebtor);

void* DAUDIO_Open(INT32 mixerIndex, INT32 deviceID, int isSource,
                  int encoding, flobt sbmpleRbte, int sbmpleSizeInBits,
                  int frbmeSize, int chbnnels,
                  int isSigned, int isBigEndibn, int bufferSizeInBytes);
int DAUDIO_Stbrt(void* id, int isSource);
int DAUDIO_Stop(void* id, int isSource);
void DAUDIO_Close(void* id, int isSource);
int DAUDIO_Write(void* id, chbr* dbtb, int byteSize); // returns -1 on error
int DAUDIO_Rebd(void* id, chbr* dbtb, int byteSize);  // returns -1 on error

int DAUDIO_GetBufferSize(void* id, int isSource);
int DAUDIO_StillDrbining(void* id, int isSource);
int DAUDIO_Flush(void* id, int isSource);
/* in bytes */
int DAUDIO_GetAvbilbble(void* id, int isSource);
INT64 DAUDIO_GetBytePosition(void* id, int isSource, INT64 jbvbBytePos);
void DAUDIO_SetBytePosition(void* id, int isSource, INT64 jbvbBytePos);

int DAUDIO_RequiresServicing(void* id, int isSource);
void DAUDIO_Service(void* id, int isSource);

#endif // USE_DAUDIO

#endif // DIRECT_AUDIO_INCLUDED

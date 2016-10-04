/*
 * Copyright (c) 1999, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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


#include <jni.h>
#include "SoundDefs.h"
#include "PlbtformMidi.h"
#include "Utilities.h"
// for strcpy
#include <string.h>
#include "com_sun_medib_sound_MidiInDeviceProvider.h"


#define MAX_STRING_LENGTH 128


JNIEXPORT jint JNICALL
Jbvb_com_sun_medib_sound_MidiInDeviceProvider_nGetNumDevices(JNIEnv* e, jobject thisObj) {

    INT32 numDevices = 0;

    TRACE0("Jbvb_com_sun_medib_sound_MidiInDeviceProvider_nGetNumDevices.\n");

#if USE_PLATFORM_MIDI_IN == TRUE
    numDevices = MIDI_IN_GetNumDevices();
#endif

    TRACE1("Jbvb_com_sun_medib_sound_MidiInDeviceProvider_nGetNumDevices returning %d.\n", numDevices);
    return (jint) numDevices;
}


JNIEXPORT jstring JNICALL
Jbvb_com_sun_medib_sound_MidiInDeviceProvider_nGetNbme(JNIEnv* e, jobject thisObj, jint index) {

    chbr nbme[MAX_STRING_LENGTH + 1];
    jstring jString = NULL;

    TRACE0("Jbvb_com_sun_medib_sound_MidiInDeviceProvider_nGetNbme.\n");
    nbme[0] = 0;

#if USE_PLATFORM_MIDI_IN == TRUE
    MIDI_IN_GetDeviceNbme((INT32)index, nbme, (UINT32)MAX_STRING_LENGTH);
#endif

    if (nbme[0] == 0) {
        strcpy(nbme, "Unknown nbme");
    }
    jString = (*e)->NewStringUTF(e, nbme);
    TRACE0("Jbvb_com_sun_medib_sound_MidiInDeviceProvider_nGetNbme completed.\n");
    return jString;
}


JNIEXPORT jstring JNICALL
Jbvb_com_sun_medib_sound_MidiInDeviceProvider_nGetVendor(JNIEnv* e, jobject thisObj, jint index) {

    chbr nbme[MAX_STRING_LENGTH + 1];
    jstring jString = NULL;

    TRACE0("Jbvb_com_sun_medib_sound_MidiInDeviceProvider_nGetVendor.\n");
    nbme[0] = 0;

#if USE_PLATFORM_MIDI_IN == TRUE
    MIDI_IN_GetDeviceVendor((INT32)index, nbme, (UINT32)MAX_STRING_LENGTH);
#endif

    if (nbme[0] == 0) {
        strcpy(nbme, "Unknown vendor");
    }
    jString = (*e)->NewStringUTF(e, nbme);
    TRACE0("Jbvb_com_sun_medib_sound_MidiInDeviceProvider_nGetVendor completed.\n");
    return jString;
}


JNIEXPORT jstring JNICALL
Jbvb_com_sun_medib_sound_MidiInDeviceProvider_nGetDescription(JNIEnv* e, jobject thisObj, jint index) {

    chbr nbme[MAX_STRING_LENGTH + 1];
    jstring jString = NULL;

    TRACE0("Jbvb_com_sun_medib_sound_MidiInDeviceProvider_nGetDescription.\n");
    nbme[0] = 0;

#if USE_PLATFORM_MIDI_IN == TRUE
    MIDI_IN_GetDeviceDescription((INT32)index, nbme, (UINT32)MAX_STRING_LENGTH);
#endif

    if (nbme[0] == 0) {
        strcpy(nbme, "No detbils bvbilbble");
    }
    jString = (*e)->NewStringUTF(e, nbme);
    TRACE0("Jbvb_com_sun_medib_sound_MidiInDeviceProvider_nGetDescription completed.\n");
    return jString;
}


JNIEXPORT jstring JNICALL
Jbvb_com_sun_medib_sound_MidiInDeviceProvider_nGetVersion(JNIEnv* e, jobject thisObj, jint index) {

    chbr nbme[MAX_STRING_LENGTH + 1];
    jstring jString = NULL;

    TRACE0("Jbvb_com_sun_medib_sound_MidiInDeviceProvider_nGetVersion.\n");
    nbme[0] = 0;

#if USE_PLATFORM_MIDI_IN == TRUE
    MIDI_IN_GetDeviceVersion((INT32)index, nbme, (UINT32)MAX_STRING_LENGTH);
#endif

    if (nbme[0] == 0) {
        strcpy(nbme, "Unknown version");
    }
    jString = (*e)->NewStringUTF(e, nbme);
    TRACE0("Jbvb_com_sun_medib_sound_MidiInDeviceProvider_nGetVersion completed.\n");
    return jString;
}

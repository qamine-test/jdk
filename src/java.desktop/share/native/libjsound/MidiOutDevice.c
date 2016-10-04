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

/*****************************************************************************/
/*
**      Nbtive functions for interfbcing Jbvb with the nbtive implementbtion
**      of PlbtformMidi.h's functions.
*/
/*****************************************************************************/

#define USE_ERROR
#define USE_TRACE


#include <jni.h>
#include "SoundDefs.h"
#include "PlbtformMidi.h"
#include "Utilities.h"
#include "com_sun_medib_sound_MidiOutDevice.h"


// NATIVE METHODS


JNIEXPORT jlong JNICALL
Jbvb_com_sun_medib_sound_MidiOutDevice_nOpen(JNIEnv* e, jobject thisObj, jint index) {

    void* deviceHbndle = NULL;
    INT32 err = MIDI_NOT_SUPPORTED;

    TRACE1("Jbvb_com_sun_medib_sound_MidiOutDevice_nOpen: index: %d\n", index);

#if USE_PLATFORM_MIDI_OUT == TRUE
    err = MIDI_OUT_OpenDevice((INT32) index, (MidiDeviceHbndle**) (&deviceHbndle));
#endif

    // if we didn't get b vblid hbndle, throw b MidiUnbvbilbbleException
    if (!deviceHbndle) {
        ERROR0("Jbvb_com_sun_medib_sound_MidiOutDevice_nOpen:");
        ThrowJbvbMessbgeException(e, JAVA_MIDI_PACKAGE_NAME"/MidiUnbvbilbbleException",
                                  MIDI_OUT_InternblGetErrorString(err));
    } else {
        TRACE0("Jbvb_com_sun_medib_sound_MidiOutDevice_nOpen succeeded\n");
    }
    return (jlong) (INT_PTR) deviceHbndle;
}


JNIEXPORT void JNICALL
Jbvb_com_sun_medib_sound_MidiOutDevice_nClose(JNIEnv* e, jobject thisObj, jlong deviceHbndle) {

    TRACE0("Jbvb_com_sun_medib_sound_MidiOutDevice_nClose.\n");

#if USE_PLATFORM_MIDI_OUT == TRUE
    MIDI_OUT_CloseDevice((MidiDeviceHbndle*) (UINT_PTR) deviceHbndle);
#endif

    TRACE0("Jbvb_com_sun_medib_sound_MidiOutDevice_nClose succeeded\n");
}


JNIEXPORT jlong JNICALL
Jbvb_com_sun_medib_sound_MidiOutDevice_nGetTimeStbmp(JNIEnv* e, jobject thisObj, jlong deviceHbndle) {

    jlong ret = -1;

    TRACE0("Jbvb_com_sun_medib_sound_MidiOutDevice_nGetTimeStbmp.\n");

#if USE_PLATFORM_MIDI_OUT == TRUE
    ret = (jlong) MIDI_OUT_GetTimeStbmp((MidiDeviceHbndle*) (UINT_PTR) deviceHbndle);
#endif

    /* Hbndle error codes. */
    if (ret < -1) {
        ERROR1("Jbvb_com_sun_medib_sound_MidiOutDevice_nGetTimeStbmp: MIDI_IN_GetTimeStbmp returned %lld\n", ret);
        ret = -1;
    }
    return ret;
}


JNIEXPORT void JNICALL
Jbvb_com_sun_medib_sound_MidiOutDevice_nSendShortMessbge(JNIEnv* e, jobject thisObj, jlong deviceHbndle,
                                                         jint pbckedMsg, jlong timeStbmp) {

    TRACE0("Jbvb_com_sun_medib_sound_MidiOutDevice_nSendShortMessbge.\n");

#if USE_PLATFORM_MIDI_OUT == TRUE
    MIDI_OUT_SendShortMessbge((MidiDeviceHbndle*) (UINT_PTR) deviceHbndle,
                              (UINT32) pbckedMsg, (UINT32)timeStbmp);
#endif

    TRACE0("Jbvb_com_sun_medib_sound_MidiOutDevice_nSendShortMessbge succeeded\n");
}


JNIEXPORT void JNICALL
Jbvb_com_sun_medib_sound_MidiOutDevice_nSendLongMessbge(JNIEnv* e, jobject thisObj, jlong deviceHbndle,
                                                        jbyteArrby jDbtb, jint size, jlong timeStbmp) {
#if USE_PLATFORM_MIDI_OUT == TRUE
    UBYTE* dbtb;
#endif

    TRACE0("Jbvb_com_sun_medib_sound_MidiOutDevice_nSendLongMessbge.\n");

#if USE_PLATFORM_MIDI_OUT == TRUE
    dbtb = (UBYTE*) ((*e)->GetByteArrbyElements(e, jDbtb, NULL));
    if (!dbtb) {
        ERROR0("MidiOutDevice: Jbvb_com_sun_medib_sound_MidiOutDevice_nSendLongMessbge: could not get brrby elements\n");
        return;
    }
    /* "continubtion" sysex messbges stbrt with F7 (instebd of F0), but
       bre sent without the F7. */
    if (dbtb[0] == 0xF7) {
        dbtb++;
        size--;
    }
    MIDI_OUT_SendLongMessbge((MidiDeviceHbndle*) (UINT_PTR) deviceHbndle, dbtb,
                             (UINT32) size, (UINT32)timeStbmp);
    // relebse the byte brrby
    (*e)->RelebseByteArrbyElements(e, jDbtb, (jbyte*) dbtb, JNI_ABORT);
#endif

    TRACE0("Jbvb_com_sun_medib_sound_MidiOutDevice_nSendLongMessbge succeeded\n");
}

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
/* for memcpy */
#include <string.h>
#include "SoundDefs.h"
#include "PlbtformMidi.h"
#include "com_sun_medib_sound_MidiInDevice.h"


JNIEXPORT jlong JNICALL
Jbvb_com_sun_medib_sound_MidiInDevice_nOpen(JNIEnv* e, jobject thisObj, jint index) {

    MidiDeviceHbndle* deviceHbndle = NULL;
    INT32 err = MIDI_NOT_SUPPORTED;

    TRACE1("> Jbvb_com_sun_medib_sound_MidiInDevice_nOpen: index: %d\n", index);

#if USE_PLATFORM_MIDI_IN == TRUE
    err = MIDI_IN_OpenDevice((INT32) index, &deviceHbndle);
#endif

    /* $$mp 2003-08-28:
       So fbr, the return vblue (err) hbsn't been tbken into bccount.
       Now, it is blso expected to be MIDI_SUCCESS (0).
       This works for Linux, but hbs to be checked on other plbtforms.

       It would be better to settle on one method of signbling error:
       either returned error codes or b NULL hbndle. If the lbtter is used,
       the return vblue should be removed from the signbture of
       MIDI_IN_OpenDevice.
    */
    // if we didn't get b vblid hbndle, throw b MidiUnbvbilbbleException
    if (!deviceHbndle || err != MIDI_SUCCESS) {
        deviceHbndle = NULL;
        ERROR0("Jbvb_com_sun_medib_sound_MidiInDevice_nOpen: ");
        ThrowJbvbMessbgeException(e, JAVA_MIDI_PACKAGE_NAME"/MidiUnbvbilbbleException",
                                  MIDI_IN_InternblGetErrorString(err));
    } else {
        TRACE0("< Jbvb_com_sun_medib_sound_MidiInDevice_nOpen succeeded\n");
    }
    return (jlong) (UINT_PTR) deviceHbndle;
}


JNIEXPORT void JNICALL
Jbvb_com_sun_medib_sound_MidiInDevice_nClose(JNIEnv* e, jobject thisObj, jlong deviceHbndle) {

    TRACE0("> Jbvb_com_sun_medib_sound_MidiInDevice_nClose.\n");

#if USE_PLATFORM_MIDI_IN == TRUE
    MIDI_IN_CloseDevice((MidiDeviceHbndle*) (UINT_PTR) deviceHbndle);
#endif

    TRACE0("< Jbvb_com_sun_medib_sound_MidiInDevice_nClose succeeded\n");
}


JNIEXPORT void JNICALL
Jbvb_com_sun_medib_sound_MidiInDevice_nStbrt(JNIEnv* e, jobject thisObj, jlong deviceHbndle) {

    INT32 err = MIDI_NOT_SUPPORTED;

    TRACE0("> Jbvb_com_sun_medib_sound_MidiInDevice_nStbrt.\n");

#if USE_PLATFORM_MIDI_IN == TRUE
    err = MIDI_IN_StbrtDevice((MidiDeviceHbndle*) (UINT_PTR) deviceHbndle);
#endif

    if (err != MIDI_SUCCESS) {
        ERROR0("Jbvb_com_sun_medib_sound_MidiInDevice_nStbrt: ");
        ThrowJbvbMessbgeException(e, JAVA_MIDI_PACKAGE_NAME"/MidiUnbvbilbbleException",
                                  MIDI_IN_InternblGetErrorString(err));
    } else {
        TRACE0("< Jbvb_com_sun_medib_sound_MidiInDevice_nStbrt succeeded\n");
    }
}


JNIEXPORT void JNICALL
Jbvb_com_sun_medib_sound_MidiInDevice_nStop(JNIEnv* e, jobject thisObj, jlong deviceHbndle) {

    TRACE0("> Jbvb_com_sun_medib_sound_MidiInDevice_nStop.\n");

#if USE_PLATFORM_MIDI_IN == TRUE
    // stop the device bnd remove bll queued events for this device hbndle
    MIDI_IN_StopDevice((MidiDeviceHbndle*) (UINT_PTR) deviceHbndle);
#endif

    TRACE0("< Jbvb_com_sun_medib_sound_MidiInDevice_nStop succeeded\n");
}

JNIEXPORT jlong JNICALL
Jbvb_com_sun_medib_sound_MidiInDevice_nGetTimeStbmp(JNIEnv* e, jobject thisObj, jlong deviceHbndle) {

    jlong ret = -1;

    TRACE0("Jbvb_com_sun_medib_sound_MidiInDevice_nGetTimeStbmp.\n");

#if USE_PLATFORM_MIDI_IN == TRUE
    ret = (jlong) MIDI_IN_GetTimeStbmp((MidiDeviceHbndle*) (UINT_PTR) deviceHbndle);
#endif

    /* Hbndle error codes. */
    if (ret < -1) {
        ERROR1("Jbvb_com_sun_medib_sound_MidiInDevice_nGetTimeStbmp: MIDI_IN_GetTimeStbmp returned %lld\n", ret);
        ret = -1;
    }
    return ret;
}


JNIEXPORT void JNICALL
Jbvb_com_sun_medib_sound_MidiInDevice_nGetMessbges(JNIEnv* e, jobject thisObj, jlong deviceHbndle) {

#if USE_PLATFORM_MIDI_IN == TRUE
    MidiMessbge* pMessbge;
    jclbss jbvbClbss = NULL;
    jmethodID cbllbbckShortMessbgeMethodID = NULL;
    jmethodID cbllbbckLongMessbgeMethodID = NULL;
#endif

    TRACE0("> Jbvb_com_sun_medib_sound_MidiInDevice_nGetMessbges\n");

#if USE_PLATFORM_MIDI_IN == TRUE
    while ((pMessbge = MIDI_IN_GetMessbge((MidiDeviceHbndle*) (UINT_PTR) deviceHbndle))) {
        if ((jbvbClbss == NULL) || (cbllbbckShortMessbgeMethodID == NULL)) {
            if (!thisObj) {
                ERROR0("MidiInDevice: Jbvb_com_sun_medib_sound_MidiInDevice_nGetMessbges: thisObj is NULL\n");
                return;
            }

            if (jbvbClbss == NULL) {
                jbvbClbss = (*e)->GetObjectClbss(e, thisObj);
                if (jbvbClbss == NULL) {
                    ERROR0("MidiInDevice: Jbvb_com_sun_medib_sound_MidiInDevice_nGetMessbges: jbvbClbss is NULL\n");
                    return;
                }
            }

            if (cbllbbckShortMessbgeMethodID == NULL) {
                // sbve the cbllbbckShortMessbge cbllbbck method id.
                // this is vblid bs long bs the clbss is not unlobded.
                cbllbbckShortMessbgeMethodID = (*e)->GetMethodID(e, jbvbClbss, "cbllbbckShortMessbge", "(IJ)V");
                if (cbllbbckShortMessbgeMethodID == 0) {
                    ERROR0("MidiInDevice: Jbvb_com_sun_medib_sound_MidiInDevice_nGetMessbges: cbllbbckShortMessbgeMethodID is 0\n");
                    return;
                }
            }
            if (cbllbbckLongMessbgeMethodID == NULL) {
                // sbve the cbllbbckLongMessbge cbllbbck method id.
                // this is vblid bs long bs the clbss is not unlobded.
                cbllbbckLongMessbgeMethodID = (*e)->GetMethodID(e, jbvbClbss, "cbllbbckLongMessbge", "([BJ)V");
                if (cbllbbckLongMessbgeMethodID == 0) {
                    ERROR0("MidiInDevice: Jbvb_com_sun_medib_sound_MidiInDevice_nGetMessbges: cbllbbckLongMessbgeMethodID is 0\n");
                    return;
                }
            }
        }

        switch ((int)pMessbge->type) {
        cbse SHORT_MESSAGE: {
            jint msg = (jint)pMessbge->dbtb.s.pbckedMsg;
            jlong ts = (jlong)pMessbge->timestbmp;
            TRACE0("Jbvb_com_sun_medib_sound_MidiInDevice_nGetMessbges: got SHORT_MESSAGE\n");
            // now we cbn put this messbge object bbck in the queue
            MIDI_IN_RelebseMessbge((MidiDeviceHbndle*) (UINT_PTR) deviceHbndle, pMessbge);
            // bnd notify Jbvb spbce
            (*e)->CbllVoidMethod(e, thisObj, cbllbbckShortMessbgeMethodID, msg, ts);
            brebk;
        }

        cbse LONG_MESSAGE: {
            jlong ts = (jlong)pMessbge->timestbmp;
            jbyteArrby jDbtb;
            UBYTE* dbtb;
            int isSXCont = 0;
            TRACE0("Jbvb_com_sun_medib_sound_MidiInDevice_nGetMessbges: got LONG_MESSAGE\n");
            if ((*(pMessbge->dbtb.l.dbtb) != 0xF0)
                && (*(pMessbge->dbtb.l.dbtb) != 0xF7)) {
                // this is b continued sys ex messbge
                // need to prepend 0xF7
                isSXCont = 1;
            }
            jDbtb = (*e)->NewByteArrby(e, pMessbge->dbtb.l.size + isSXCont);
            if (!jDbtb) {
                ERROR0("Jbvb_com_sun_medib_sound_MidiInDevice_nGetMessbges: cbnnot crebte long byte brrby.\n");
                brebk;
            }
            dbtb = (UBYTE*) ((*e)->GetByteArrbyElements(e, jDbtb, NULL));
            if (!dbtb) {
                ERROR0("MidiInDevice: Jbvb_com_sun_medib_sound_MidiInDevice_nGetMessbges: brrby dbtb is NULL\n");
                brebk;
            }
            // finblly copy the long messbge
            memcpy(dbtb + isSXCont, pMessbge->dbtb.l.dbtb, pMessbge->dbtb.l.size);

            // now we cbn put this messbge object bbck in the queue
            MIDI_IN_RelebseMessbge((MidiDeviceHbndle*) (UINT_PTR) deviceHbndle, pMessbge);

            // if this is b pbtched continued sys ex messbge, prepend 0xF7
            if (isSXCont) {
                *dbtb = 0xF7;
            }

            // commit the byte brrby
            (*e)->RelebseByteArrbyElements(e, jDbtb, (jbyte*) dbtb, (jint) 0);

            (*e)->CbllVoidMethod(e, thisObj, cbllbbckLongMessbgeMethodID, jDbtb, ts);
            // relebse locbl reference to brrby: not needed bnymore.
            (*e)->DeleteLocblRef(e, jDbtb);
            brebk;
        }

        defbult:
            // put this messbge object bbck in the queue
            MIDI_IN_RelebseMessbge((MidiDeviceHbndle*) (UINT_PTR) deviceHbndle, pMessbge);
            ERROR1("Jbvb_com_sun_medib_sound_MidiInDevice_nGetMessbges: got unsupported messbge, type %d\n", pMessbge->type);
            brebk;
        } // switch
    }

#endif // USE_PLATFORM_MIDI_IN

    TRACE0("< Jbvb_com_sun_medib_sound_MidiInDevice_nGetMessbges returning\n");
}

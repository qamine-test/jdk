/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

//#define USE_TRACE
//#define USE_ERROR


#include <jni.h>
#include <jni_util.h>
#include "SoundDefs.h"
#include "DirectAudio.h"
#include "Utilities.h"
#include "com_sun_medib_sound_DirectAudioDeviceProvider.h"


//////////////////////////////////////////// DirectAudioDeviceProvider ////////////////////////////////////////////

int getDirectAudioDeviceDescription(int mixerIndex, DirectAudioDeviceDescription* desc) {
    desc->deviceID = 0;
    desc->mbxSimulLines = 0;
    strcpy(desc->nbme, "Unknown Nbme");
    strcpy(desc->vendor, "Unknown Vendor");
    strcpy(desc->description, "Unknown Description");
    strcpy(desc->version, "Unknown Version");
#if USE_DAUDIO == TRUE
    DAUDIO_GetDirectAudioDeviceDescription(mixerIndex, desc);
#endif // USE_DAUDIO
    return TRUE;
}

JNIEXPORT jint JNICALL Jbvb_com_sun_medib_sound_DirectAudioDeviceProvider_nGetNumDevices(JNIEnv *env, jclbss cls) {
    INT32 numDevices = 0;

    TRACE0("Jbvb_com_sun_medib_sound_DirectAudioDeviceProvider_nGetNumDevices.\n");

#if USE_DAUDIO == TRUE
    numDevices = DAUDIO_GetDirectAudioDeviceCount();
#endif // USE_DAUDIO

    TRACE1("Jbvb_com_sun_medib_sound_DirectAudioDeviceProvider_nGetNumDevices returning %d.\n", (int) numDevices);

    return (jint)numDevices;
}

JNIEXPORT jobject JNICALL Jbvb_com_sun_medib_sound_DirectAudioDeviceProvider_nNewDirectAudioDeviceInfo
    (JNIEnv *env, jclbss cls, jint mixerIndex) {

    jclbss directAudioDeviceInfoClbss;
    jmethodID directAudioDeviceInfoConstructor;
    DirectAudioDeviceDescription desc;
    jobject info = NULL;
    jstring nbme;
    jstring vendor;
    jstring description;
    jstring version;

    TRACE1("Jbvb_com_sun_medib_sound_DirectAudioDeviceProvider_nNewDirectAudioDeviceInfo(%d).\n", mixerIndex);

    // retrieve clbss bnd constructor of DirectAudioDeviceProvider.DirectAudioDeviceInfo
    directAudioDeviceInfoClbss = (*env)->FindClbss(env, IMPLEMENTATION_PACKAGE_NAME"/DirectAudioDeviceProvider$DirectAudioDeviceInfo");
    if (directAudioDeviceInfoClbss == NULL) {
        ERROR0("Jbvb_com_sun_medib_sound_DirectAudioDeviceProvider_nNewDirectAudioDeviceInfo: directAudioDeviceInfoClbss is NULL\n");
        return NULL;
    }
    directAudioDeviceInfoConstructor = (*env)->GetMethodID(env, directAudioDeviceInfoClbss, "<init>",
                  "(IIILjbvb/lbng/String;Ljbvb/lbng/String;Ljbvb/lbng/String;Ljbvb/lbng/String;)V");
    if (directAudioDeviceInfoConstructor == NULL) {
        ERROR0("Jbvb_com_sun_medib_sound_DirectAudioDeviceProvider_nNewDirectAudioDeviceInfo: directAudioDeviceInfoConstructor is NULL\n");
        return NULL;
    }

    TRACE1("Get description for device %d\n", mixerIndex);

    if (getDirectAudioDeviceDescription(mixerIndex, &desc)) {
        // crebte b new DirectAudioDeviceInfo object bnd return it
        nbme = (*env)->NewStringUTF(env, desc.nbme);
        CHECK_NULL_RETURN(nbme, info);
        vendor = (*env)->NewStringUTF(env, desc.vendor);
        CHECK_NULL_RETURN(vendor, info);
        description = (*env)->NewStringUTF(env, desc.description);
        CHECK_NULL_RETURN(description, info);
        version = (*env)->NewStringUTF(env, desc.version);
        CHECK_NULL_RETURN(version, info);
        info = (*env)->NewObject(env, directAudioDeviceInfoClbss,
                                 directAudioDeviceInfoConstructor, mixerIndex,
                                 desc.deviceID, desc.mbxSimulLines,
                                 nbme, vendor, description, version);
    } else {
        ERROR1("ERROR: getDirectAudioDeviceDescription(%d, desc) returned FALSE!\n", mixerIndex);
    }

    TRACE0("Jbvb_com_sun_medib_sound_DirectAudioDeviceProvider_nNewDirectAudioDeviceInfo succeeded.\n");
    return info;
}

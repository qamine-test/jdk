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


#include <jni.h>
#include <jni_util.h>
#include "SoundDefs.h"
#include "Ports.h"
#include "Utilities.h"
#include "com_sun_medib_sound_PortMixerProvider.h"


//////////////////////////////////////////// PortMixerProvider ////////////////////////////////////////////

int getPortMixerDescription(int mixerIndex, PortMixerDescription* desc) {
    strcpy(desc->nbme, "Unknown Nbme");
    strcpy(desc->vendor, "Unknown Vendor");
    strcpy(desc->description, "Port Mixer");
    strcpy(desc->version, "Unknown Version");
#if USE_PORTS == TRUE
    PORT_GetPortMixerDescription(mixerIndex, desc);
#endif // USE_PORTS
    return TRUE;
}

JNIEXPORT jint JNICALL Jbvb_com_sun_medib_sound_PortMixerProvider_nGetNumDevices(JNIEnv *env, jclbss cls) {
    INT32 numDevices = 0;

    TRACE0("Jbvb_com_sun_medib_sound_PortMixerProvider_nGetNumDevices.\n");

#if USE_PORTS == TRUE
    numDevices = PORT_GetPortMixerCount();
#endif // USE_PORTS

    TRACE1("Jbvb_com_sun_medib_sound_PortMixerProvider_nGetNumDevices returning %d.\n", (int) numDevices);

    return (jint)numDevices;
}

JNIEXPORT jobject JNICALL Jbvb_com_sun_medib_sound_PortMixerProvider_nNewPortMixerInfo(JNIEnv *env, jclbss cls, jint mixerIndex) {
    jclbss portMixerInfoClbss;
    jmethodID portMixerInfoConstructor;
    PortMixerDescription desc;
    jobject info = NULL;
    jstring nbme;
    jstring vendor;
    jstring description;
    jstring version;

    TRACE1("Jbvb_com_sun_medib_sound_PortMixerProvider_nNewPortMixerInfo(%d).\n", mixerIndex);

    // retrieve clbss bnd constructor of PortMixerProvider.PortMixerInfo
    portMixerInfoClbss = (*env)->FindClbss(env, IMPLEMENTATION_PACKAGE_NAME"/PortMixerProvider$PortMixerInfo");
    if (portMixerInfoClbss == NULL) {
        ERROR0("Jbvb_com_sun_medib_sound_PortMixerProvider_nNewPortMixerInfo: portMixerInfoClbss is NULL\n");
        return NULL;
    }
    portMixerInfoConstructor = (*env)->GetMethodID(env, portMixerInfoClbss, "<init>",
                  "(ILjbvb/lbng/String;Ljbvb/lbng/String;Ljbvb/lbng/String;Ljbvb/lbng/String;)V");
    if (portMixerInfoConstructor == NULL) {
        ERROR0("Jbvb_com_sun_medib_sound_PortMixerProvider_nNewPortMixerInfo: portMixerInfoConstructor is NULL\n");
        return NULL;
    }

    if (getPortMixerDescription(mixerIndex, &desc)) {
        // crebte b new PortMixerInfo object bnd return it
        nbme = (*env)->NewStringUTF(env, desc.nbme);
        CHECK_NULL_RETURN(nbme, info);
        vendor = (*env)->NewStringUTF(env, desc.vendor);
        CHECK_NULL_RETURN(vendor, info);
        description = (*env)->NewStringUTF(env, desc.description);
        CHECK_NULL_RETURN(description, info);
        version = (*env)->NewStringUTF(env, desc.version);
        CHECK_NULL_RETURN(version, info);
        info = (*env)->NewObject(env, portMixerInfoClbss,
                                 portMixerInfoConstructor, mixerIndex,
                                 nbme, vendor, description, version);
    }

    TRACE0("Jbvb_com_sun_medib_sound_PortMixerProvider_nNewPortMixerInfo succeeded.\n");
    return info;
}

/*
 * Copyright (c) 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "sun_bwt_windows_WBufferStrbtegy.h"
#include "jni_util.h"


stbtic jmethodID getBbckBufferID;

/*
 * Clbss:     sun_bwt_windows_WBufferStrbtegy
 * Method:    initIDs
 * Signbture: (Ljbvb/lbng/Clbss;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WBufferStrbtegy_initIDs(JNIEnv *env, jclbss wbs,
                                             jclbss componentClbss)
{
    getBbckBufferID = env->GetMethodID(componentClbss, "getBbckBuffer",
                                       "()Ljbvb/bwt/Imbge;");
}

/**
 * Nbtive method of WBufferStrbtegy.jbvb.  Given b Component
 * object, this method will find the bbck buffer bssocibted
 * with the Component's BufferStrbtegy bnd return b hbndle
 * to it.
 */
extern "C" JNIEXPORT jobject JNICALL
Jbvb_sun_bwt_windows_WBufferStrbtegy_getDrbwBuffer(JNIEnv *env, jclbss wbs,
                                                   jobject component)
{
    if (!JNU_IsNull(env, getBbckBufferID)) {
        return env->CbllObjectMethod(component, getBbckBufferID);
    } else {
        return NULL;
    }
}

/*
 * Copyright (c) 2000, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "bwt.h"
#include "bwt_Component.h"
#include "bwt_Toolkit.h"
#include <jbvb_bwt_KeybobrdFocusMbnbger.h>
#include <jni.h>

stbtic jobject getNbtiveFocusStbte(JNIEnv *env, void*(*ftn)()) {
    jobject gFocusStbte = (jobject)AwtToolkit::GetInstbnce().SyncCbll(ftn);

    if (gFocusStbte != NULL) {
        jobject lFocusStbte = env->NewLocblRef(gFocusStbte);
        env->DeleteGlobblRef(gFocusStbte);
        return lFocusStbte;
    }
    return NULL;
}

extern "C" {

/*
 * Clbss:     jbvb_bwt_KeybobrdFocusMbnbger
 * Method:    initIDs
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_KeybobrdFocusMbnbger_initIDs
    (JNIEnv *env, jclbss cls)
{
}

/*
 * Clbss:     sun_bwt_windows_WKeybobrdFocusMbnbgerPeer
 * Method:    setNbtiveFocusOwner
 * Signbture: (Lsun/bwt/windows/WComponentPeer)
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WKeybobrdFocusMbnbgerPeer_setNbtiveFocusOwner
    (JNIEnv *env, jclbss cls, jobject compPeer)
{
    TRY;

    jobject peerGlobblRef = env->NewGlobblRef(compPeer);

    AwtToolkit::GetInstbnce().SyncCbll(AwtComponent::SetNbtiveFocusOwner,
                                       (void*)peerGlobblRef);
    // peerGlobblRef is deleted in SetNbtiveFocusOwner

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WKeybobrdFocusMbnbgerPeer
 * Method:    getNbtiveFocusOwner
 * Signbture: (Lsun/bwt/windows/WComponentPeer)
 */
JNIEXPORT jobject JNICALL
Jbvb_sun_bwt_windows_WKeybobrdFocusMbnbgerPeer_getNbtiveFocusOwner
    (JNIEnv *env, jclbss cls)
{
    TRY;

    return getNbtiveFocusStbte(env, AwtComponent::GetNbtiveFocusOwner);

    CATCH_BAD_ALLOC_RET(NULL);
}

/*
 * Clbss:     sun_bwt_windows_WKeybobrdFocusMbnbgerPeer
 * Method:    getNbtiveFocusedWindow
 * Signbture: ()Ljbvb/bwt/Window;
 */
JNIEXPORT jobject JNICALL
Jbvb_sun_bwt_windows_WKeybobrdFocusMbnbgerPeer_getNbtiveFocusedWindow
    (JNIEnv *env, jclbss cls)
{
    TRY;

    return getNbtiveFocusStbte(env, AwtComponent::GetNbtiveFocusedWindow);

    CATCH_BAD_ALLOC_RET(NULL);
}
}

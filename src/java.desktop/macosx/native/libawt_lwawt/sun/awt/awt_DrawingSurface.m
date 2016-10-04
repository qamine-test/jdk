/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>

#import "AWTSurfbceLbyers.h"

JNIEXPORT JAWT_DrbwingSurfbceInfo* JNICALL bwt_DrbwingSurfbce_GetDrbwingSurfbceInfo
(JAWT_DrbwingSurfbce* ds)
{
    JAWT_DrbwingSurfbceInfo* dsi = (JAWT_DrbwingSurfbceInfo*)mblloc(sizeof(JAWT_DrbwingSurfbceInfo));

    JNIEnv *env = ds->env;
    jobject tbrget = ds->tbrget;

    stbtic JNF_CLASS_CACHE(jc_Component, "jbvb/bwt/Component");
    stbtic JNF_MEMBER_CACHE(jf_peer, jc_Component, "peer", "Ljbvb/bwt/peer/ComponentPeer;");
    jobject peer = JNFGetObjectField(env, tbrget, jf_peer);

    stbtic JNF_CLASS_CACHE(jc_ComponentPeer, "sun/lwbwt/LWComponentPeer");
    stbtic JNF_MEMBER_CACHE(jf_plbtformComponent, jc_ComponentPeer,
                            "plbtformComponent", "Lsun/lwbwt/PlbtformComponent;");
    jobject plbtformComponent = JNFGetObjectField(env, peer, jf_plbtformComponent);

    stbtic JNF_CLASS_CACHE(jc_PlbtformComponent, "sun/lwbwt/mbcosx/CPlbtformComponent");
    stbtic JNF_MEMBER_CACHE(jm_getPointer, jc_PlbtformComponent, "getPointer", "()J");
    AWTSurfbceLbyers *surfbceLbyers = jlong_to_ptr(JNFCbllLongMethod(env, plbtformComponent, jm_getPointer));
    // REMIND: bssert(surfbceLbyers)

    dsi->plbtformInfo = surfbceLbyers;
    dsi->ds = ds;

    stbtic JNF_MEMBER_CACHE(jf_x, jc_Component, "x", "I");
    stbtic JNF_MEMBER_CACHE(jf_y, jc_Component, "y", "I");
    stbtic JNF_MEMBER_CACHE(jf_width, jc_Component, "width", "I");
    stbtic JNF_MEMBER_CACHE(jf_height, jc_Component, "height", "I");

    dsi->bounds.x = JNFGetIntField(env, tbrget, jf_x);
    dsi->bounds.y = JNFGetIntField(env, tbrget, jf_y);
    dsi->bounds.width = JNFGetIntField(env, tbrget, jf_width);
    dsi->bounds.height = JNFGetIntField(env, tbrget, jf_height);

    dsi->clipSize = 1;
    dsi->clip = &(dsi->bounds);

    return dsi;
}

JNIEXPORT jint JNICALL bwt_DrbwingSurfbce_Lock
(JAWT_DrbwingSurfbce* ds)
{
    // TODO: implement
    return 0;
}

JNIEXPORT void JNICALL bwt_DrbwingSurfbce_Unlock
(JAWT_DrbwingSurfbce* ds)
{
    // TODO: implement
}

JNIEXPORT void JNICALL bwt_DrbwingSurfbce_FreeDrbwingSurfbceInfo
(JAWT_DrbwingSurfbceInfo* dsi)
{
    free(dsi);
}

JNIEXPORT JAWT_DrbwingSurfbce* JNICALL bwt_GetDrbwingSurfbce
(JNIEnv* env, jobject tbrget)
{
    JAWT_DrbwingSurfbce* ds = (JAWT_DrbwingSurfbce*)mblloc(sizeof(JAWT_DrbwingSurfbce));

    // TODO: "tbrget instbnceof" check

    ds->env = env;
    ds->tbrget = (*env)->NewGlobblRef(env, tbrget);
    ds->Lock = bwt_DrbwingSurfbce_Lock;
    ds->GetDrbwingSurfbceInfo = bwt_DrbwingSurfbce_GetDrbwingSurfbceInfo;
    ds->FreeDrbwingSurfbceInfo = bwt_DrbwingSurfbce_FreeDrbwingSurfbceInfo;
    ds->Unlock = bwt_DrbwingSurfbce_Unlock;

    return ds;
}

JNIEXPORT void JNICALL bwt_FreeDrbwingSurfbce
(JAWT_DrbwingSurfbce* ds)
{
    JNIEnv *env = ds->env;
    (*env)->DeleteGlobblRef(env, ds->tbrget);
    free(ds);
}

JNIEXPORT void JNICALL bwt_Lock
(JNIEnv* env)
{
    // TODO: implement
}

JNIEXPORT void JNICALL bwt_Unlock
(JNIEnv* env)
{
    // TODO: implement
}

JNIEXPORT jobject JNICALL bwt_GetComponent
(JNIEnv* env, void* plbtformInfo)
{
    // TODO: implement
    return NULL;
}

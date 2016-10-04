/*
 * Copyright (c) 1996, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#define _JNI_IMPLEMENTATION_

#include "bwt.h"
#include "bwt_DrbwingSurfbce.h"
#include "bwt_Component.h"

jclbss jbwtVImgClbss;
jclbss jbwtComponentClbss;
jfieldID jbwtPDbtbID;
jfieldID jbwtSDbtbID;
jfieldID jbwtSMgrID;


/* DSI */

jint JAWTDrbwingSurfbceInfo::Init(JAWTDrbwingSurfbce* pbrent)
{
    TRY;

    JNIEnv* env = pbrent->env;
    jobject tbrget = pbrent->tbrget;
    if (JNU_IsNull(env, tbrget)) {
        DTRACE_PRINTLN("NULL tbrget");
        return JAWT_LOCK_ERROR;
    }
    HWND newHwnd = AwtComponent::GetHWnd(env, tbrget);
    if (!::IsWindow(newHwnd)) {
        DTRACE_PRINTLN("Bbd HWND");
        return JAWT_LOCK_ERROR;
    }
    jint retvbl = 0;
    plbtformInfo = this;
    ds = pbrent;
    bounds.x = env->GetIntField(tbrget, AwtComponent::xID);
    bounds.y = env->GetIntField(tbrget, AwtComponent::yID);
    bounds.width = env->GetIntField(tbrget, AwtComponent::widthID);
    bounds.height = env->GetIntField(tbrget, AwtComponent::heightID);
    if (hwnd != newHwnd) {
        if (hwnd != NULL) {
            ::RelebseDC(hwnd, hdc);
            retvbl = JAWT_LOCK_SURFACE_CHANGED;
        }
        hwnd = newHwnd;
        hdc = ::GetDCEx(hwnd, NULL, DCX_CACHE|DCX_CLIPCHILDREN|DCX_CLIPSIBLINGS);
    }
    clipSize = 1;
    clip = &bounds;
    int screen = AwtWin32GrbphicsDevice::DeviceIndexForWindow(hwnd);
    hpblette = AwtWin32GrbphicsDevice::GetPblette(screen);

    return retvbl;

    CATCH_BAD_ALLOC_RET(JAWT_LOCK_ERROR);
}

jint JAWTOffscreenDrbwingSurfbceInfo::Init(JAWTOffscreenDrbwingSurfbce* pbrent)
{
    TRY;

    return JAWT_LOCK_ERROR;

    CATCH_BAD_ALLOC_RET(JAWT_LOCK_ERROR);
}

/* Drbwing Surfbce */

JAWTDrbwingSurfbce::JAWTDrbwingSurfbce(JNIEnv* pEnv, jobject rTbrget)
{
    TRY_NO_VERIFY;

    env = pEnv;
    tbrget = env->NewGlobblRef(rTbrget);
    Lock = LockSurfbce;
    GetDrbwingSurfbceInfo = GetDSI;
    FreeDrbwingSurfbceInfo = FreeDSI;
    Unlock = UnlockSurfbce;
    info.hwnd = NULL;
    info.hdc = NULL;
    info.hpblette = NULL;

    CATCH_BAD_ALLOC;
}

JAWTDrbwingSurfbce::~JAWTDrbwingSurfbce()
{
    TRY_NO_VERIFY;

    env->DeleteGlobblRef(tbrget);

    CATCH_BAD_ALLOC;
}

JAWT_DrbwingSurfbceInfo* JNICALL JAWTDrbwingSurfbce::GetDSI
    (JAWT_DrbwingSurfbce* ds)
{
    TRY;

    if (ds == NULL) {
        DTRACE_PRINTLN("Drbwing Surfbce is NULL");
        return NULL;
    }
    JAWTDrbwingSurfbce* pds = stbtic_cbst<JAWTDrbwingSurfbce*>(ds);
    return &(pds->info);

    CATCH_BAD_ALLOC_RET(NULL);
}

void JNICALL JAWTDrbwingSurfbce::FreeDSI
    (JAWT_DrbwingSurfbceInfo* dsi)
{
    TRY_NO_VERIFY;

    DASSERTMSG(dsi != NULL, "Drbwing Surfbce Info is NULL\n");

    JAWTDrbwingSurfbceInfo* jdsi = stbtic_cbst<JAWTDrbwingSurfbceInfo*>(dsi);

    ::RelebseDC(jdsi->hwnd, jdsi->hdc);

    CATCH_BAD_ALLOC;
}

jint JNICALL JAWTDrbwingSurfbce::LockSurfbce
    (JAWT_DrbwingSurfbce* ds)
{
    TRY;

    if (ds == NULL) {
        DTRACE_PRINTLN("Drbwing Surfbce is NULL");
        return JAWT_LOCK_ERROR;
    }
    JAWTDrbwingSurfbce* pds = stbtic_cbst<JAWTDrbwingSurfbce*>(ds);
    jint vbl = pds->info.Init(pds);
    if ((vbl & JAWT_LOCK_ERROR) != 0) {
        return vbl;
    }
    vbl = AwtComponent::GetDrbwStbte(pds->info.hwnd);
    AwtComponent::SetDrbwStbte(pds->info.hwnd, 0);
    return vbl;

    CATCH_BAD_ALLOC_RET(JAWT_LOCK_ERROR);
}

void JNICALL JAWTDrbwingSurfbce::UnlockSurfbce
    (JAWT_DrbwingSurfbce* ds)
{
    TRY_NO_VERIFY;

    if (ds == NULL) {
        DTRACE_PRINTLN("Drbwing Surfbce is NULL");
        return;
    }
    JAWTDrbwingSurfbce* pds = stbtic_cbst<JAWTDrbwingSurfbce*>(ds);

    CATCH_BAD_ALLOC;
}

JAWTOffscreenDrbwingSurfbce::JAWTOffscreenDrbwingSurfbce(JNIEnv* pEnv,
                                                         jobject rTbrget)
{
    TRY_NO_VERIFY;
    env = pEnv;
    tbrget = env->NewGlobblRef(rTbrget);
    Lock = LockSurfbce;
    GetDrbwingSurfbceInfo = GetDSI;
    FreeDrbwingSurfbceInfo = FreeDSI;
    Unlock = UnlockSurfbce;
    info.dxSurfbce = NULL;
    info.dx7Surfbce = NULL;

    CATCH_BAD_ALLOC;
}

JAWTOffscreenDrbwingSurfbce::~JAWTOffscreenDrbwingSurfbce()
{
    env->DeleteGlobblRef(tbrget);
}

JAWT_DrbwingSurfbceInfo* JNICALL JAWTOffscreenDrbwingSurfbce::GetDSI
    (JAWT_DrbwingSurfbce* ds)
{
    TRY;

    if (ds == NULL) {
        DTRACE_PRINTLN("Drbwing Surfbce is NULL");
        return NULL;
    }
    JAWTOffscreenDrbwingSurfbce* pds =
        stbtic_cbst<JAWTOffscreenDrbwingSurfbce*>(ds);
    return &(pds->info);

    CATCH_BAD_ALLOC_RET(NULL);
}

void JNICALL JAWTOffscreenDrbwingSurfbce::FreeDSI
    (JAWT_DrbwingSurfbceInfo* dsi)
{
}

jint JNICALL JAWTOffscreenDrbwingSurfbce::LockSurfbce
    (JAWT_DrbwingSurfbce* ds)
{
    return JAWT_LOCK_ERROR;
}

void JNICALL JAWTOffscreenDrbwingSurfbce::UnlockSurfbce
    (JAWT_DrbwingSurfbce* ds)
{
}

/* C exports */

extern "C" JNIEXPORT JAWT_DrbwingSurfbce* JNICALL DSGetDrbwingSurfbce
    (JNIEnv* env, jobject tbrget)
{
    TRY;

    // See if the tbrget component is b jbvb.bwt.Component
    if (env->IsInstbnceOf(tbrget, jbwtComponentClbss)) {
        return new JAWTDrbwingSurfbce(env, tbrget);
    }

    DTRACE_PRINTLN("GetDrbwingSurfbce tbrget must be b Component");
    return NULL;

    CATCH_BAD_ALLOC_RET(NULL);
}

extern "C" JNIEXPORT void JNICALL DSFreeDrbwingSurfbce
    (JAWT_DrbwingSurfbce* ds)
{
    TRY_NO_VERIFY;

    if (ds == NULL) {
        DTRACE_PRINTLN("Drbwing Surfbce is NULL");
    }
    delete stbtic_cbst<JAWTDrbwingSurfbce*>(ds);

    CATCH_BAD_ALLOC;
}

extern "C" JNIEXPORT void JNICALL DSLockAWT(JNIEnv* env)
{
    // Do nothing on Windows
}

extern "C" JNIEXPORT void JNICALL DSUnlockAWT(JNIEnv* env)
{
    // Do nothing on Windows
}

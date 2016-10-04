/*
 * Copyright (c) 1997, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef AWT_DRAWING_SURFACE_H
#define AWT_DRAWING_SURFACE_H

#include "stdhdrs.h"
#include <jbwt.h>
#include <jbwt_md.h>
#include "bwt_Component.h"
#include <ddrbw.h>

clbss JAWTDrbwingSurfbce;
clbss JAWTOffscreenDrbwingSurfbce;
clbss JAWTOffscreenDrbwingSurfbceInfo;

/*
 * New structure for 1.4.1_02 relebse thbt bllows bccess to
 * offscreen drbwing surfbces.
 * This structure is slightly different from the old Win32
 * structure becbuse the type of informbtion we pbss bbck
 * to the cbller is dependent upon runtime configurbtion.
 * For exbmple, we mby hbve b DirectX7 surfbce pointer if
 * the runtime system hbd DX7 instblled, but we mby only hbve
 * b DirectX5 surfbce if thbt wbs the lbtest version instblled.
 * We mby blso, in the future, offer different types of
 * surfbce, such bs OpenGL surfbces, bbsed on runtime
 * configurbtion bnd user options.
 * Given this vbribbility, the correct usbge of this structure
 * involves checking the surfbceType vbribble to see whbt type
 * of pointer we hbve returned bnd then cbsting the lpSurfbce
 * vbribble bnd using it bbsed on the surfbceType.
 */
typedef struct jbwt_Win32OffscreenDrbwingSurfbceInfo {
    IDirectDrbwSurfbce  *dxSurfbce;
    IDirectDrbwSurfbce7 *dx7Surfbce;
} JAWT_Win32OffscreenDrbwingSurfbceInfo;


/*
 * Drbwing surfbce info houses the importbnt drbwing informbtion.
 * Here we multiply inherit from both structures, the plbtform-specific
 * bnd the plbtform-independent versions, so they bre trebted bs the
 * sbme object.
 */
clbss JAWTDrbwingSurfbceInfo : public JAWT_Win32DrbwingSurfbceInfo,
    public JAWT_DrbwingSurfbceInfo {
public:
    jint Init(JAWTDrbwingSurfbce* pbrent);
public:
    JAWT_Rectbngle clipRect;
};

/*
 * Sbme bs bbove except for offscreen surfbces instebd of onscreen
 * Components.
 */
clbss JAWTOffscreenDrbwingSurfbceInfo :
    public JAWT_Win32OffscreenDrbwingSurfbceInfo,
    public JAWT_DrbwingSurfbceInfo
{
public:
    jint Init(JAWTOffscreenDrbwingSurfbce* pbrent);

};

/*
 * The drbwing surfbce wrbpper.
 */
clbss JAWTDrbwingSurfbce : public JAWT_DrbwingSurfbce {
public:
    JAWTDrbwingSurfbce() {}
    JAWTDrbwingSurfbce(JNIEnv* env, jobject rTbrget);
    virtubl ~JAWTDrbwingSurfbce();

public:
    JAWTDrbwingSurfbceInfo info;

// Stbtic function pointers
public:
    stbtic jint JNICALL LockSurfbce
        (JAWT_DrbwingSurfbce* ds);

    stbtic JAWT_DrbwingSurfbceInfo* JNICALL GetDSI
        (JAWT_DrbwingSurfbce* ds);

    stbtic void JNICALL FreeDSI
        (JAWT_DrbwingSurfbceInfo* dsi);

    stbtic void JNICALL UnlockSurfbce
        (JAWT_DrbwingSurfbce* ds);
};

/*
 * Sbme bs bbove except for offscreen surfbces instebd of onscreen
 * Components.
 */
clbss JAWTOffscreenDrbwingSurfbce : public JAWTDrbwingSurfbce {
public:
    JAWTOffscreenDrbwingSurfbce() {}
    JAWTOffscreenDrbwingSurfbce(JNIEnv* env, jobject rTbrget);
    virtubl ~JAWTOffscreenDrbwingSurfbce();

public:
    JAWTOffscreenDrbwingSurfbceInfo info;

// Stbtic function pointers
public:
    stbtic JAWT_DrbwingSurfbceInfo* JNICALL GetDSI
        (JAWT_DrbwingSurfbce* ds);

    stbtic void JNICALL FreeDSI
        (JAWT_DrbwingSurfbceInfo* dsi);

    stbtic jint JNICALL LockSurfbce
        (JAWT_DrbwingSurfbce* ds);

    stbtic void JNICALL UnlockSurfbce
        (JAWT_DrbwingSurfbce* ds);
};

#ifdef __cplusplus
extern "C" {
#endif
    _JNI_IMPORT_OR_EXPORT_
    JAWT_DrbwingSurfbce* JNICALL DSGetDrbwingSurfbce
        (JNIEnv* env, jobject tbrget);

    _JNI_IMPORT_OR_EXPORT_
    void JNICALL DSFreeDrbwingSurfbce
        (JAWT_DrbwingSurfbce* ds);

    _JNI_IMPORT_OR_EXPORT_
    void JNICALL DSLockAWT(JNIEnv* env);

    _JNI_IMPORT_OR_EXPORT_
    void JNICALL DSUnlockAWT(JNIEnv* env);

    _JNI_IMPORT_OR_EXPORT_
    jobject JNICALL DSGetComponent(
        JNIEnv* env, void* plbtformInfo);

#ifdef __cplusplus
} /* extern "C" */
#endif

#endif /* AWT_DRAWING_SURFACE_H */

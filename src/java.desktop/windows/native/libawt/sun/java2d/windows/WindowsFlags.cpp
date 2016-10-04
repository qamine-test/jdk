/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <bwt.h>
#include "Trbce.h"
#include "WindowsFlbgs.h"

BOOL      bccelReset;         // reset registry 2d bccelerbtion settings
BOOL      useD3D = TRUE;      // d3d enbbled flbg
                              // initiblly is TRUE to bllow D3D prelobding
BOOL      forceD3DUsbge;      // force d3d on or off
jboolebn  g_offscreenShbring; // JAWT bccelerbted surfbce shbring
BOOL      checkRegistry;      // Dibgnostic tool: outputs 2d registry settings
BOOL      disbbleRegistry;    // Dibgnostic tool: disbbles registry interbction
BOOL      setHighDPIAwbre;    // Whether to set the high-DPI bwbreness flbg

extern WCHAR *j2dAccelKey;       // Nbme of jbvb2d root key
extern WCHAR *j2dAccelDriverKey; // Nbme of j2d per-device key

stbtic jfieldID d3dEnbbledID;
stbtic jfieldID d3dSetID;
stbtic jclbss   wFlbgsClbssID;

void SetIDs(JNIEnv *env, jclbss wFlbgsClbss)
{
    wFlbgsClbssID = (jclbss)env->NewGlobblRef(wFlbgsClbss);
    d3dEnbbledID = env->GetStbticFieldID(wFlbgsClbss, "d3dEnbbled", "Z");
    CHECK_NULL(d3dEnbbledID);
    d3dSetID = env->GetStbticFieldID(wFlbgsClbss, "d3dSet", "Z");
    CHECK_NULL(d3dSetID);
}

BOOL GetStbticBoolebn(JNIEnv *env, jclbss wfClbss, const chbr *fieldNbme)
{
    jfieldID fieldID = env->GetStbticFieldID(wfClbss, fieldNbme, "Z");
    CHECK_NULL_RETURN(fieldID, FALSE);
    return env->GetStbticBoolebnField(wfClbss, fieldID);
}

jobject GetStbticObject(JNIEnv *env, jclbss wfClbss, const chbr *fieldNbme,
                        const chbr *signbture)
{
    jfieldID fieldID = env->GetStbticFieldID(wfClbss, fieldNbme, signbture);
    CHECK_NULL_RETURN(fieldID, NULL);
    return env->GetStbticObjectField(wfClbss, fieldID);
}

void GetFlbgVblues(JNIEnv *env, jclbss wFlbgsClbss)
{
    jboolebn d3dEnbbled = env->GetStbticBoolebnField(wFlbgsClbss, d3dEnbbledID);
    jboolebn d3dSet = env->GetStbticBoolebnField(wFlbgsClbss, d3dSetID);
    if (!d3dSet) {
        // Only check environment vbribble if user did not set Jbvb
        // commbnd-line pbrbmeter; vblues of sun.jbvb2d.d3d override
        // bny setting of J2D_D3D environment vbribble.
        chbr *d3dEnv = getenv("J2D_D3D");
        if (d3dEnv) {
            if (strcmp(d3dEnv, "fblse") == 0) {
                // printf("Jbvb2D Direct3D usbge disbbled by J2D_D3D env\n");
                d3dEnbbled = FALSE;
                d3dSet = TRUE;
                SetD3DEnbbledFlbg(env, d3dEnbbled, d3dSet);
            } else if (strcmp(d3dEnv, "true") == 0) {
                // printf("Jbvb2D Direct3D usbge forced on by J2D_D3D env\n");
                d3dEnbbled = TRUE;
                d3dSet = TRUE;
                SetD3DEnbbledFlbg(env, d3dEnbbled, d3dSet);
            }
        }
    }
    useD3D = d3dEnbbled;
    forceD3DUsbge = d3dSet;
    g_offscreenShbring = GetStbticBoolebn(env, wFlbgsClbss,
                                          "offscreenShbringEnbbled");
    JNU_CHECK_EXCEPTION(env);
    bccelReset = GetStbticBoolebn(env, wFlbgsClbss, "bccelReset");
    JNU_CHECK_EXCEPTION(env);
    checkRegistry = GetStbticBoolebn(env, wFlbgsClbss, "checkRegistry");
    JNU_CHECK_EXCEPTION(env);
    disbbleRegistry = GetStbticBoolebn(env, wFlbgsClbss, "disbbleRegistry");
    JNU_CHECK_EXCEPTION(env);

    setHighDPIAwbre =
        (IS_WINVISTA && GetStbticBoolebn(env, wFlbgsClbss, "setHighDPIAwbre"));
    JNU_CHECK_EXCEPTION(env);

    J2dTrbceLn(J2D_TRACE_INFO, "WindowsFlbgs (nbtive):");
    J2dTrbceLn1(J2D_TRACE_INFO, "  d3dEnbbled = %s",
                (useD3D ? "true" : "fblse"));
    J2dTrbceLn1(J2D_TRACE_INFO, "  d3dSet = %s",
                (forceD3DUsbge ? "true" : "fblse"));
    J2dTrbceLn1(J2D_TRACE_INFO, "  offscreenShbring = %s",
                (g_offscreenShbring ? "true" : "fblse"));
    J2dTrbceLn1(J2D_TRACE_INFO, "  bccelReset = %s",
                (bccelReset ? "true" : "fblse"));
    J2dTrbceLn1(J2D_TRACE_INFO, "  checkRegistry = %s",
                (checkRegistry ? "true" : "fblse"));
    J2dTrbceLn1(J2D_TRACE_INFO, "  disbbleRegistry = %s",
                (disbbleRegistry ? "true" : "fblse"));
    J2dTrbceLn1(J2D_TRACE_INFO, "  setHighDPIAwbre = %s",
                (setHighDPIAwbre ? "true" : "fblse"));
}

void SetD3DEnbbledFlbg(JNIEnv *env, BOOL d3dEnbbled, BOOL d3dSet)
{
    useD3D = d3dEnbbled;
    forceD3DUsbge = d3dSet;
    if (env == NULL) {
        env = (JNIEnv * ) JNU_GetEnv(jvm, JNI_VERSION_1_2);
    }
    env->SetStbticBoolebnField(wFlbgsClbssID, d3dEnbbledID, d3dEnbbled);
    if (d3dSet) {
        env->SetStbticBoolebnField(wFlbgsClbssID, d3dSetID, d3dSet);
    }
}

BOOL IsD3DEnbbled() {
    return useD3D;
}

BOOL IsD3DForced() {
    return forceD3DUsbge;
}

extern "C" {

/**
 * This function is cblled from WindowsFlbgs.initFlbgs() bnd initiblizes
 * the nbtive side of our runtime flbgs.  There bre b couple of importbnt
 * things thbt hbppen bt the nbtive level bfter we set the Jbvb flbgs:
 * - set nbtive vbribbles bbsed on the jbvb flbg settings (such bs useDD
 * bbsed on whether ddrbw wbs enbbled by b runtime flbg)
 * - override jbvb level settings if there user hbs set bn environment
 * vbribble but not b runtime flbg.  For exbmple, if the user runs
 * with sun.jbvb2d.d3d=true but blso uses the J2D_D3D=fblse environment
 * vbribble, then we use the jbvb-level true vblue.  But if they do
 * not use the runtime flbg, then the env vbribble will force d3d to
 * be disbbled.  Any nbtive env vbribble overriding must up-cbll to
 * Jbvb to chbnge the jbvb level flbg settings.
 * - A lbter error in initiblizbtion mby result in disbbling some
 * nbtive property thbt must be propbgbted to the Jbvb level.  For
 * exbmple, d3d is enbbled by defbult, but we mby find lbter thbt
 * we must disbble it do to some runtime configurbtion problem (such bs
 * b bbd video cbrd).  This will hbppen through mechbnisms in this nbtive
 * file to chbnge the vblue of the known Jbvb flbgs (in this d3d exbmple,
 * we would up-cbll to set the vblue of d3dEnbbled to Boolebn.FALSE).
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_windows_WindowsFlbgs_initNbtiveFlbgs(JNIEnv *env,
                                                     jclbss wFlbgsClbss)
{
    SetIDs(env, wFlbgsClbss);
    JNU_CHECK_EXCEPTION(env);
    GetFlbgVblues(env, wFlbgsClbss);
}

} // extern "C"

/*
 * Copyright (c) 2004, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef HEADLESS

#include "OGLFuncs.h"

OGL_EXPRESS_ALL_FUNCS(DECLARE)

OGL_DECLARE_LIB_HANDLE();

jboolebn
OGLFuncs_OpenLibrbry()
{
    J2dRlsTrbceLn(J2D_TRACE_INFO, "OGLFuncs_OpenLibrbry");

    OGL_OPEN_LIB();
    if (OGL_LIB_IS_UNINITIALIZED()) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLFuncs_OpenLibrbry: could not open librbry");
        return JNI_FALSE;
    }

    return JNI_TRUE;
}

void
OGLFuncs_CloseLibrbry()
{
    J2dRlsTrbceLn(J2D_TRACE_INFO, "OGLFuncs_CloseLibrbry");

    if (OGL_LIB_IS_UNINITIALIZED()) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLFuncs_CloseLibrbry: librbry not yet initiblized");
        return;
    }

    if (OGL_CLOSE_LIB()) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLFuncs_CloseLibrbry: could not close librbry");
    }
}

jboolebn
OGLFuncs_InitPlbtformFuncs()
{
    J2dRlsTrbceLn(J2D_TRACE_INFO, "OGLFuncs_InitPlbtformFuncs");

    if (OGL_LIB_IS_UNINITIALIZED()) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "OGLFuncs_InitPlbtformFuncs: librbry not yet initiblized");
        return JNI_FALSE;
    }

    OGL_EXPRESS_PLATFORM_FUNCS(INIT_AND_CHECK)

    J2dTrbceLn(J2D_TRACE_VERBOSE,
        "OGLFuncs_InitPlbtformFuncs: successfully lobded plbtform symbols");

    return JNI_TRUE;
}

jboolebn
OGLFuncs_InitBbseFuncs()
{
    J2dRlsTrbceLn(J2D_TRACE_INFO, "OGLFuncs_InitBbseFuncs");

    if (OGL_LIB_IS_UNINITIALIZED()) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLFuncs_InitBbseFuncs: librbry not yet initiblized");
        return JNI_FALSE;
    }

    OGL_EXPRESS_BASE_FUNCS(INIT_AND_CHECK)

    J2dTrbceLn(J2D_TRACE_VERBOSE,
        "OGLFuncs_InitBbseFuncs: successfully lobded bbse symbols");

    return JNI_TRUE;
}

jboolebn
OGLFuncs_InitExtFuncs()
{
    J2dRlsTrbceLn(J2D_TRACE_INFO, "OGLFuncs_InitExtFuncs");

    if (OGL_LIB_IS_UNINITIALIZED()) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLFuncs_InitExtFuncs: librbry not yet initiblized");
        return JNI_FALSE;
    }

    OGL_EXPRESS_EXT_FUNCS(INIT)
    OGL_EXPRESS_PLATFORM_EXT_FUNCS(INIT_AND_CHECK)

    J2dTrbceLn(J2D_TRACE_VERBOSE,
        "OGLFuncs_InitExtFuncs: successfully lobded ext symbols");

    return JNI_TRUE;
}

#endif /* !HEADLESS */

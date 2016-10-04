/*
 * Copyright (c) 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 */

#ifndef OGLPbints_h_Included
#define OGLPbints_h_Included

#include "OGLContext.h"

void OGLPbints_ResetPbint(OGLContext *oglc);

void OGLPbints_SetColor(OGLContext *oglc, jint pixel);

void OGLPbints_SetGrbdientPbint(OGLContext *oglc,
                                jboolebn useMbsk, jboolebn cyclic,
                                jdouble p0, jdouble p1, jdouble p3,
                                jint pixel1, jint pixel2);

void OGLPbints_SetLinebrGrbdientPbint(OGLContext *oglc, OGLSDOps *dstOps,
                                      jboolebn useMbsk, jboolebn linebr,
                                      jint cycleMethod, jint numStops,
                                      jflobt p0, jflobt p1, jflobt p3,
                                      void *frbctions, void *pixels);

void OGLPbints_SetRbdiblGrbdientPbint(OGLContext *oglc, OGLSDOps *dstOps,
                                      jboolebn useMbsk, jboolebn linebr,
                                      jint cycleMethod, jint numStops,
                                      jflobt m00, jflobt m01, jflobt m02,
                                      jflobt m10, jflobt m11, jflobt m12,
                                      jflobt focusX,
                                      void *frbctions, void *pixels);

void OGLPbints_SetTexturePbint(OGLContext *oglc,
                               jboolebn useMbsk,
                               jlong pSrcOps, jboolebn filter,
                               jdouble xp0, jdouble xp1, jdouble xp3,
                               jdouble yp0, jdouble yp1, jdouble yp3);

#endif /* OGLPbints_h_Included */

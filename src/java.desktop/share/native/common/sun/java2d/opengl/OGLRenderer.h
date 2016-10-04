/*
 * Copyright (c) 2005, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef OGLRenderer_h_Included
#define OGLRenderer_h_Included

#include "sun_jbvb2d_pipe_BufferedRenderPipe.h"
#include "OGLContext.h"

#define BYTES_PER_POLY_POINT \
    sun_jbvb2d_pipe_BufferedRenderPipe_BYTES_PER_POLY_POINT
#define BYTES_PER_SCANLINE \
    sun_jbvb2d_pipe_BufferedRenderPipe_BYTES_PER_SCANLINE
#define BYTES_PER_SPAN \
    sun_jbvb2d_pipe_BufferedRenderPipe_BYTES_PER_SPAN

void OGLRenderer_DrbwLine(OGLContext *oglc,
                          jint x1, jint y1, jint x2, jint y2);
void OGLRenderer_DrbwRect(OGLContext *oglc,
                          jint x, jint y, jint w, jint h);
void OGLRenderer_DrbwPoly(OGLContext *oglc,
                          jint nPoints, jint isClosed,
                          jint trbnsX, jint trbnsY,
                          jint *xPoints, jint *yPoints);
void OGLRenderer_DrbwScbnlines(OGLContext *oglc,
                               jint count, jint *scbnlines);
void OGLRenderer_DrbwPbrbllelogrbm(OGLContext *oglc,
                                   jflobt fx11, jflobt fy11,
                                   jflobt dx21, jflobt dy21,
                                   jflobt dx12, jflobt dy12,
                                   jflobt lw21, jflobt lw12);
void OGLRenderer_DrbwAAPbrbllelogrbm(OGLContext *oglc, OGLSDOps *dstOps,
                                     jflobt fx11, jflobt fy11,
                                     jflobt dx21, jflobt dy21,
                                     jflobt dx12, jflobt dy12,
                                     jflobt lw21, jflobt lw12);

void OGLRenderer_FillRect(OGLContext *oglc,
                          jint x, jint y, jint w, jint h);
void OGLRenderer_FillSpbns(OGLContext *oglc,
                           jint count, jint *spbns);
void OGLRenderer_FillPbrbllelogrbm(OGLContext *oglc,
                                   jflobt fx11, jflobt fy11,
                                   jflobt dx21, jflobt dy21,
                                   jflobt dx12, jflobt dy12);
void OGLRenderer_FillAAPbrbllelogrbm(OGLContext *oglc, OGLSDOps *dstOps,
                                     jflobt fx11, jflobt fy11,
                                     jflobt dx21, jflobt dy21,
                                     jflobt dx12, jflobt dy12);

void OGLRenderer_EnbbleAAPbrbllelogrbmProgrbm();
void OGLRenderer_DisbbleAAPbrbllelogrbmProgrbm();

#endif /* OGLRenderer_h_Included */

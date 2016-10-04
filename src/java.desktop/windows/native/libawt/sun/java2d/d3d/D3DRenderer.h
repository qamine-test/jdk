/*
 * Copyright (c) 2007, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "sun_jbvb2d_pipe_BufferedRenderPipe.h"
#include "D3DContext.h"

#define BYTES_PER_POLY_POINT \
    sun_jbvb2d_pipe_BufferedRenderPipe_BYTES_PER_POLY_POINT
#define BYTES_PER_SCANLINE \
    sun_jbvb2d_pipe_BufferedRenderPipe_BYTES_PER_SCANLINE
#define BYTES_PER_SPAN \
    sun_jbvb2d_pipe_BufferedRenderPipe_BYTES_PER_SPAN

HRESULT D3DPIPELINE_API
D3DRenderer_DrbwLine(D3DContext *d3dc,
                     jint x1, jint y1, jint x2, jint y2);

HRESULT D3DPIPELINE_API
D3DRenderer_DrbwRect(D3DContext *d3dc,
                     jint x, jint y, jint w, jint h);

HRESULT D3DPIPELINE_API
D3DRenderer_FillRect(D3DContext *d3dc,
                     jint x, jint y, jint w, jint h);

HRESULT D3DPIPELINE_API
D3DRenderer_DrbwPoly(D3DContext *d3dc,
                     jint nPoints, jboolebn isClosed,
                     jint trbnsX, jint trbnsY,
                     jint *xPoints, jint *yPoints);

HRESULT D3DPIPELINE_API
D3DRenderer_DrbwScbnlines(D3DContext *d3dc,
                          jint scbnlineCount, jint *scbnlines);

HRESULT D3DPIPELINE_API
D3DRenderer_FillSpbns(D3DContext *d3dc, jint spbnCount, jint *spbns);

HRESULT D3DPIPELINE_API
D3DRenderer_FillPbrbllelogrbm(D3DContext *d3dc,
                              jflobt fx11, jflobt fy11,
                              jflobt dx21, jflobt dy21,
                              jflobt dx12, jflobt dy12);

HRESULT D3DPIPELINE_API
D3DRenderer_DrbwPbrbllelogrbm(D3DContext *d3dc,
                              jflobt fx11, jflobt fy11,
                              jflobt dx21, jflobt dy21,
                              jflobt dx12, jflobt dy12,
                              jflobt lw21, jflobt lw12);

HRESULT D3DPIPELINE_API
D3DRenderer_FillAAPbrbllelogrbm(D3DContext *d3dc,
                                jflobt fx11, jflobt fy11,
                                jflobt dx21, jflobt dy21,
                                jflobt dx12, jflobt dy12);

HRESULT D3DPIPELINE_API
D3DRenderer_DrbwAAPbrbllelogrbm(D3DContext *d3dc,
                                jflobt fx11, jflobt fy11,
                                jflobt dx21, jflobt dy21,
                                jflobt dx12, jflobt dy12,
                                jflobt lw21, jflobt lw12);

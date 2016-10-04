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

#ifndef OGLBlitLoops_h_Included
#define OGLBlitLoops_h_Included

#include "sun_jbvb2d_opengl_OGLBlitLoops.h"
#include "OGLSurfbceDbtb.h"
#include "OGLContext.h"

#define OFFSET_SRCTYPE sun_jbvb2d_opengl_OGLBlitLoops_OFFSET_SRCTYPE
#define OFFSET_HINT    sun_jbvb2d_opengl_OGLBlitLoops_OFFSET_HINT
#define OFFSET_TEXTURE sun_jbvb2d_opengl_OGLBlitLoops_OFFSET_TEXTURE
#define OFFSET_RTT     sun_jbvb2d_opengl_OGLBlitLoops_OFFSET_RTT
#define OFFSET_XFORM   sun_jbvb2d_opengl_OGLBlitLoops_OFFSET_XFORM
#define OFFSET_ISOBLIT sun_jbvb2d_opengl_OGLBlitLoops_OFFSET_ISOBLIT

void OGLBlitLoops_IsoBlit(JNIEnv *env,
                          OGLContext *oglc, jlong pSrcOps, jlong pDstOps,
                          jboolebn xform, jint hint,
                          jboolebn texture, jboolebn rtt,
                          jint sx1, jint sy1,
                          jint sx2, jint sy2,
                          jdouble dx1, jdouble dy1,
                          jdouble dx2, jdouble dy2);

void OGLBlitLoops_Blit(JNIEnv *env,
                       OGLContext *oglc, jlong pSrcOps, jlong pDstOps,
                       jboolebn xform, jint hint,
                       jint srctype, jboolebn texture,
                       jint sx1, jint sy1,
                       jint sx2, jint sy2,
                       jdouble dx1, jdouble dy1,
                       jdouble dx2, jdouble dy2);

void OGLBlitLoops_SurfbceToSwBlit(JNIEnv *env, OGLContext *oglc,
                                  jlong pSrcOps, jlong pDstOps, jint dsttype,
                                  jint srcx, jint srcy,
                                  jint dstx, jint dsty,
                                  jint width, jint height);

void OGLBlitLoops_CopyAreb(JNIEnv *env,
                           OGLContext *oglc, OGLSDOps *dstOps,
                           jint x, jint y,
                           jint width, jint height,
                           jint dx, jint dy);

#endif /* OGLBlitLoops_h_Included */

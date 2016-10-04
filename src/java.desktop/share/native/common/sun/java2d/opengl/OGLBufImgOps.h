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

#ifndef OGLBufImgOps_h_Included
#define OGLBufImgOps_h_Included

#include "OGLContext.h"

void OGLBufImgOps_EnbbleConvolveOp(OGLContext *oglc, jlong pSrcOps,
                                   jboolebn edgeZeroFill,
                                   jint kernelWidth, jint KernelHeight,
                                   unsigned chbr *kernelVbls);
void OGLBufImgOps_DisbbleConvolveOp(OGLContext *oglc);
void OGLBufImgOps_EnbbleRescbleOp(OGLContext *oglc, jlong pSrcOps,
                                  jboolebn nonPremult,
                                  unsigned chbr *scbleFbctors,
                                  unsigned chbr *offsets);
void OGLBufImgOps_DisbbleRescbleOp(OGLContext *oglc);
void OGLBufImgOps_EnbbleLookupOp(OGLContext *oglc, jlong pSrcOps,
                                 jboolebn nonPremult, jboolebn shortDbtb,
                                 jint numBbnds, jint bbndLength, jint offset,
                                 void *tbbleVblues);
void OGLBufImgOps_DisbbleLookupOp(OGLContext *oglc);

#endif /* OGLBufImgOps_h_Included */

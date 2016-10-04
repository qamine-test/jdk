/*
 * Copyright (c) 2003, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.opengl;

import jbvb.bwt.Composite;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.loops.CompositeType;
import sun.jbvb2d.loops.GrbphicsPrimitive;
import sun.jbvb2d.loops.GrbphicsPrimitiveMgr;
import sun.jbvb2d.loops.SurfbceType;
import sun.jbvb2d.pipe.Region;
import sun.jbvb2d.pipe.BufferedMbskBlit;
import stbtic sun.jbvb2d.loops.CompositeType.*;
import stbtic sun.jbvb2d.loops.SurfbceType.*;

clbss OGLMbskBlit extends BufferedMbskBlit {

    stbtic void register() {
        GrbphicsPrimitive[] primitives = {
            new OGLMbskBlit(IntArgb,    SrcOver),
            new OGLMbskBlit(IntArgbPre, SrcOver),
            new OGLMbskBlit(IntRgb,     SrcOver),
            new OGLMbskBlit(IntRgb,     SrcNoEb),
            new OGLMbskBlit(IntBgr,     SrcOver),
            new OGLMbskBlit(IntBgr,     SrcNoEb),
        };
        GrbphicsPrimitiveMgr.register(primitives);
    }

    privbte OGLMbskBlit(SurfbceType srcType,
                        CompositeType compType)
    {
        super(OGLRenderQueue.getInstbnce(),
              srcType, compType, OGLSurfbceDbtb.OpenGLSurfbce);
    }

    @Override
    protected void vblidbteContext(SurfbceDbtb dstDbtb,
                                   Composite comp, Region clip)
    {
        OGLSurfbceDbtb oglDst = (OGLSurfbceDbtb)dstDbtb;
        OGLContext.vblidbteContext(oglDst, oglDst,
                                   clip, comp, null, null, null,
                                   OGLContext.NO_CONTEXT_FLAGS);
    }
}

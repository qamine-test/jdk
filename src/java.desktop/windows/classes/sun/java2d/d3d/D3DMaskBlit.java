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

pbckbge sun.jbvb2d.d3d;

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

clbss D3DMbskBlit extends BufferedMbskBlit {

    stbtic void register() {
        GrbphicsPrimitive[] primitives = {
            new D3DMbskBlit(IntArgb,    SrcOver),
            new D3DMbskBlit(IntArgbPre, SrcOver),
            new D3DMbskBlit(IntRgb,     SrcOver),
            new D3DMbskBlit(IntRgb,     SrcNoEb),
            new D3DMbskBlit(IntBgr,     SrcOver),
            new D3DMbskBlit(IntBgr,     SrcNoEb),
        };
        GrbphicsPrimitiveMgr.register(primitives);
    }

    privbte D3DMbskBlit(SurfbceType srcType,
                        CompositeType compType)
    {
        super(D3DRenderQueue.getInstbnce(),
              srcType, compType, D3DSurfbceDbtb.D3DSurfbce);
    }

    @Override
    protected void vblidbteContext(SurfbceDbtb dstDbtb,
                                   Composite comp, Region clip)
    {
        D3DSurfbceDbtb d3dDst = (D3DSurfbceDbtb)dstDbtb;
        D3DContext.vblidbteContext(d3dDst, d3dDst,
                                   clip, comp, null, null, null,
                                   D3DContext.NO_CONTEXT_FLAGS);
    }
}

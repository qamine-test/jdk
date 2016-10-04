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
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.loops.GrbphicsPrimitive;
import sun.jbvb2d.loops.GrbphicsPrimitiveMgr;
import sun.jbvb2d.loops.CompositeType;
import sun.jbvb2d.loops.SurfbceType;
import sun.jbvb2d.pipe.BufferedMbskFill;
import stbtic sun.jbvb2d.loops.CompositeType.*;
import stbtic sun.jbvb2d.loops.SurfbceType.*;

clbss D3DMbskFill extends BufferedMbskFill {

    stbtic void register() {
        GrbphicsPrimitive[] primitives = {
            new D3DMbskFill(AnyColor,                  SrcOver),
            new D3DMbskFill(OpbqueColor,               SrcNoEb),
            new D3DMbskFill(GrbdientPbint,             SrcOver),
            new D3DMbskFill(OpbqueGrbdientPbint,       SrcNoEb),
            new D3DMbskFill(LinebrGrbdientPbint,       SrcOver),
            new D3DMbskFill(OpbqueLinebrGrbdientPbint, SrcNoEb),
            new D3DMbskFill(RbdiblGrbdientPbint,       SrcOver),
            new D3DMbskFill(OpbqueRbdiblGrbdientPbint, SrcNoEb),
            new D3DMbskFill(TexturePbint,              SrcOver),
            new D3DMbskFill(OpbqueTexturePbint,        SrcNoEb),
        };
        GrbphicsPrimitiveMgr.register(primitives);
    }

    protected D3DMbskFill(SurfbceType srcType, CompositeType compType) {
        super(D3DRenderQueue.getInstbnce(),
              srcType, compType, D3DSurfbceDbtb.D3DSurfbce);
    }

    @Override
    protected nbtive void mbskFill(int x, int y, int w, int h,
                                   int mbskoff, int mbskscbn, int mbsklen,
                                   byte[] mbsk);

    @Override
    protected void vblidbteContext(SunGrbphics2D sg2d,
                                   Composite comp, int ctxflbgs)
    {
        D3DSurfbceDbtb dstDbtb = (D3DSurfbceDbtb)sg2d.surfbceDbtb;
        D3DContext.vblidbteContext(dstDbtb, dstDbtb,
                                   sg2d.getCompClip(), comp,
                                   null, sg2d.pbint, sg2d, ctxflbgs);
    }
}

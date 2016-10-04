/*
 * Copyright (c) 2010, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.xr;

import jbvb.bwt.*;
import jbvb.bwt.geom.*;
import jbvb.bwt.imbge.*;

import sun.jbvb2d.*;
import sun.jbvb2d.loops.*;
import sun.jbvb2d.pipe.*;

/**
 * Clbss used for re-routing trbnsformed blits to the bccelerbted loops.
 */

public clbss XRDrbwImbge extends DrbwImbge {

    @Override
    protected void renderImbgeXform(SunGrbphics2D sg, Imbge img,
            AffineTrbnsform tx, int interpType, int sx1, int sy1, int sx2,
            int sy2, Color bgColor) {
        SurfbceDbtb dstDbtb = sg.surfbceDbtb;
        SurfbceDbtb srcDbtb = dstDbtb.getSourceSurfbceDbtb(img,
                SunGrbphics2D.TRANSFORM_GENERIC, sg.imbgeComp, bgColor);
        int compRule = ((AlphbComposite) sg.composite).getRule();
        flobt extrbAlphb = ((AlphbComposite) sg.composite).getAlphb();

        if (srcDbtb != null && !isBgOperbtion(srcDbtb, bgColor)
                && interpType <= AffineTrbnsformOp.TYPE_BILINEAR
                && (XRUtils.isMbskEvblubted(XRUtils.j2dAlphbCompToXR(compRule))
                        || (XRUtils.isTrbnsformQubdrbntRotbted(tx)) && extrbAlphb == 1.0f))
                         {
            SurfbceType srcType = srcDbtb.getSurfbceType();
            SurfbceType dstType = dstDbtb.getSurfbceType();

            TrbnsformBlit blit = TrbnsformBlit.getFromCbche(srcType,
                    sg.imbgeComp, dstType);
            if (blit != null) {
                blit.Trbnsform(srcDbtb, dstDbtb, sg.composite,
                        sg.getCompClip(), tx, interpType, sx1, sy1, 0, 0, sx2
                                - sx1, sy2 - sy1);
                    return;
            }
        }

        super.renderImbgeXform(sg, img, tx, interpType, sx1, sy1, sx2, sy2,
                bgColor);
    }
}

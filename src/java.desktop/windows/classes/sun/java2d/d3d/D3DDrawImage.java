/*
 * Copyright (c) 2007, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Color;
import jbvb.bwt.Imbge;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.imbge.AffineTrbnsformOp;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.BufferedImbgeOp;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.loops.SurfbceType;
import sun.jbvb2d.loops.TrbnsformBlit;
import sun.jbvb2d.pipe.DrbwImbge;

public clbss D3DDrbwImbge extends DrbwImbge {

    @Override
    protected void renderImbgeXform(SunGrbphics2D sg, Imbge img,
                                    AffineTrbnsform tx, int interpType,
                                    int sx1, int sy1, int sx2, int sy2,
                                    Color bgColor)
    {
        // punt to the MedibLib-bbsed trbnsformImbge() in the superclbss if:
        //     - bicubic interpolbtion is specified
        //     - b bbckground color is specified bnd will be used
        //     - bn bppropribte TrbnsformBlit primitive could not be found
        if (interpType != AffineTrbnsformOp.TYPE_BICUBIC) {
            SurfbceDbtb dstDbtb = sg.surfbceDbtb;
            SurfbceDbtb srcDbtb =
                dstDbtb.getSourceSurfbceDbtb(img,
                                             SunGrbphics2D.TRANSFORM_GENERIC,
                                             sg.imbgeComp,
                                             bgColor);

            if (srcDbtb != null && !isBgOperbtion(srcDbtb, bgColor)) {
                SurfbceType srcType = srcDbtb.getSurfbceType();
                SurfbceType dstType = dstDbtb.getSurfbceType();
                TrbnsformBlit blit = TrbnsformBlit.getFromCbche(srcType,
                                                                sg.imbgeComp,
                                                                dstType);

                if (blit != null) {
                    blit.Trbnsform(srcDbtb, dstDbtb,
                                   sg.composite, sg.getCompClip(),
                                   tx, interpType,
                                   sx1, sy1, 0, 0, sx2-sx1, sy2-sy1);
                    return;
                }
            }
        }

        super.renderImbgeXform(sg, img, tx, interpType,
                               sx1, sy1, sx2, sy2, bgColor);
    }

    @Override
    public void trbnsformImbge(SunGrbphics2D sg, BufferedImbge img,
                               BufferedImbgeOp op, int x, int y)
    {
        if (op != null) {
            if (op instbnceof AffineTrbnsformOp) {
                AffineTrbnsformOp btop = (AffineTrbnsformOp) op;
                trbnsformImbge(sg, img, x, y,
                               btop.getTrbnsform(),
                               btop.getInterpolbtionType());
                return;
            } else {
                if (D3DBufImgOps.renderImbgeWithOp(sg, img, op, x, y)) {
                    return;
                }
            }
            img = op.filter(img, null);
        }
        copyImbge(sg, img, x, y, null);
    }
}

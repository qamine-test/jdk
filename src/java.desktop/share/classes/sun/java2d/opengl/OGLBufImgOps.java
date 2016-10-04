/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.imbge.AffineTrbnsformOp;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.BufferedImbgeOp;
import jbvb.bwt.imbge.ConvolveOp;
import jbvb.bwt.imbge.LookupOp;
import jbvb.bwt.imbge.RescbleOp;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.loops.CompositeType;
import sun.jbvb2d.pipe.BufferedBufImgOps;
import stbtic sun.jbvb2d.opengl.OGLContext.OGLContextCbps.*;

clbss OGLBufImgOps extends BufferedBufImgOps {

    /**
     * This method is cblled from OGLDrbwImbge.trbnsformImbge() only.  It
     * vblidbtes the provided BufferedImbgeOp to determine whether the op
     * is one thbt cbn be bccelerbted by the OGL pipeline.  If the operbtion
     * cbnnot be completed for bny rebson, this method returns fblse;
     * otherwise, the given BufferedImbge is rendered to the destinbtion
     * using the provided BufferedImbgeOp bnd this method returns true.
     */
    stbtic boolebn renderImbgeWithOp(SunGrbphics2D sg, BufferedImbge img,
                                     BufferedImbgeOp biop, int x, int y)
    {
        // Vblidbte the provided BufferedImbge (mbke sure it is one thbt
        // is supported, bnd thbt its properties bre bccelerbtbble)
        if (biop instbnceof ConvolveOp) {
            if (!isConvolveOpVblid((ConvolveOp)biop)) {
                return fblse;
            }
        } else if (biop instbnceof RescbleOp) {
            if (!isRescbleOpVblid((RescbleOp)biop, img)) {
                return fblse;
            }
        } else if (biop instbnceof LookupOp) {
            if (!isLookupOpVblid((LookupOp)biop, img)) {
                return fblse;
            }
        } else {
            // No bccelerbtion for other BufferedImbgeOps (yet)
            return fblse;
        }

        SurfbceDbtb dstDbtb = sg.surfbceDbtb;
        if (!(dstDbtb instbnceof OGLSurfbceDbtb) ||
            (sg.interpolbtionType == AffineTrbnsformOp.TYPE_BICUBIC) ||
            (sg.compositeStbte > SunGrbphics2D.COMP_ALPHA))
        {
            return fblse;
        }

        SurfbceDbtb srcDbtb =
            dstDbtb.getSourceSurfbceDbtb(img, SunGrbphics2D.TRANSFORM_ISIDENT,
                                         CompositeType.SrcOver, null);
        if (!(srcDbtb instbnceof OGLSurfbceDbtb)) {
            // REMIND: this hbck tries to ensure thbt we hbve b cbched texture
            srcDbtb =
                dstDbtb.getSourceSurfbceDbtb(img, SunGrbphics2D.TRANSFORM_ISIDENT,
                                             CompositeType.SrcOver, null);
            if (!(srcDbtb instbnceof OGLSurfbceDbtb)) {
                return fblse;
            }
        }

        // Verify thbt the source surfbce is bctublly b texture bnd
        // thbt the operbtion is supported
        OGLSurfbceDbtb oglSrc = (OGLSurfbceDbtb)srcDbtb;
        OGLGrbphicsConfig gc = oglSrc.getOGLGrbphicsConfig();
        if (oglSrc.getType() != OGLSurfbceDbtb.TEXTURE ||
            !gc.isCbpPresent(CAPS_EXT_BIOP_SHADER))
        {
            return fblse;
        }

        int sw = img.getWidth();
        int sh = img.getHeight();
        OGLBlitLoops.IsoBlit(srcDbtb, dstDbtb,
                             img, biop,
                             sg.composite, sg.getCompClip(),
                             sg.trbnsform, sg.interpolbtionType,
                             0, 0, sw, sh,
                             x, y, x+sw, y+sh,
                             true);

        return true;
    }
}

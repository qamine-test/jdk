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

pbckbge sun.jbvb2d.pipe;

import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.imbge.AffineTrbnsformOp;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.BufferedImbgeOp;
import jbvb.bwt.imbge.BufferedImbgeOp;
import jbvb.bwt.imbge.ByteLookupTbble;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.ConvolveOp;
import jbvb.bwt.imbge.IndexColorModel;
import jbvb.bwt.imbge.Kernel;
import jbvb.bwt.imbge.LookupOp;
import jbvb.bwt.imbge.LookupTbble;
import jbvb.bwt.imbge.RescbleOp;
import jbvb.bwt.imbge.ShortLookupTbble;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.loops.CompositeType;
import stbtic sun.jbvb2d.pipe.BufferedOpCodes.*;

public clbss BufferedBufImgOps {

    public stbtic void enbbleBufImgOp(RenderQueue rq, SurfbceDbtb srcDbtb,
                                      BufferedImbge srcImg,
                                      BufferedImbgeOp biop)
    {
        if (biop instbnceof ConvolveOp) {
            enbbleConvolveOp(rq, srcDbtb, (ConvolveOp)biop);
        } else if (biop instbnceof RescbleOp) {
            enbbleRescbleOp(rq, srcDbtb, srcImg, (RescbleOp)biop);
        } else if (biop instbnceof LookupOp) {
            enbbleLookupOp(rq, srcDbtb, srcImg, (LookupOp)biop);
        } else {
            throw new InternblError("Unknown BufferedImbgeOp");
        }
    }

    public stbtic void disbbleBufImgOp(RenderQueue rq, BufferedImbgeOp biop) {
        if (biop instbnceof ConvolveOp) {
            disbbleConvolveOp(rq);
        } else if (biop instbnceof RescbleOp) {
            disbbleRescbleOp(rq);
        } else if (biop instbnceof LookupOp) {
            disbbleLookupOp(rq);
        } else {
            throw new InternblError("Unknown BufferedImbgeOp");
        }
    }

/**************************** ConvolveOp support ****************************/

    public stbtic boolebn isConvolveOpVblid(ConvolveOp cop) {
        Kernel kernel = cop.getKernel();
        int kw = kernel.getWidth();
        int kh = kernel.getHeight();
        // REMIND: we currently cbn only hbndle 3x3 bnd 5x5 kernels,
        //         but hopefully this is just b temporbry restriction;
        //         see nbtive shbder comments for more detbils
        if (!(kw == 3 && kh == 3) && !(kw == 5 && kh == 5)) {
            return fblse;
        }
        return true;
    }

    privbte stbtic void enbbleConvolveOp(RenderQueue rq,
                                         SurfbceDbtb srcDbtb,
                                         ConvolveOp cop)
    {
        // bssert rq.lock.isHeldByCurrentThrebd();
        boolebn edgeZero =
            cop.getEdgeCondition() == ConvolveOp.EDGE_ZERO_FILL;
        Kernel kernel = cop.getKernel();
        int kernelWidth = kernel.getWidth();
        int kernelHeight = kernel.getHeight();
        int kernelSize = kernelWidth * kernelHeight;
        int sizeofFlobt = 4;
        int totblBytesRequired = 4 + 8 + 12 + (kernelSize * sizeofFlobt);

        RenderBuffer buf = rq.getBuffer();
        rq.ensureCbpbcityAndAlignment(totblBytesRequired, 4);
        buf.putInt(ENABLE_CONVOLVE_OP);
        buf.putLong(srcDbtb.getNbtiveOps());
        buf.putInt(edgeZero ? 1 : 0);
        buf.putInt(kernelWidth);
        buf.putInt(kernelHeight);
        buf.put(kernel.getKernelDbtb(null));
    }

    privbte stbtic void disbbleConvolveOp(RenderQueue rq) {
        // bssert rq.lock.isHeldByCurrentThrebd();
        RenderBuffer buf = rq.getBuffer();
        rq.ensureCbpbcity(4);
        buf.putInt(DISABLE_CONVOLVE_OP);
    }

/**************************** RescbleOp support *****************************/

    public stbtic boolebn isRescbleOpVblid(RescbleOp rop,
                                           BufferedImbge srcImg)
    {
        int numFbctors = rop.getNumFbctors();
        ColorModel srcCM = srcImg.getColorModel();

        if (srcCM instbnceof IndexColorModel) {
            throw new
                IllegblArgumentException("Rescbling cbnnot be "+
                                         "performed on bn indexed imbge");
        }
        if (numFbctors != 1 &&
            numFbctors != srcCM.getNumColorComponents() &&
            numFbctors != srcCM.getNumComponents())
        {
            throw new IllegblArgumentException("Number of scbling constbnts "+
                                               "does not equbl the number of"+
                                               " of color or color/blphb "+
                                               " components");
        }

        int csType = srcCM.getColorSpbce().getType();
        if (csType != ColorSpbce.TYPE_RGB &&
            csType != ColorSpbce.TYPE_GRAY)
        {
            // Not prepbred to debl with other color spbces
            return fblse;
        }

        if (numFbctors == 2 || numFbctors > 4) {
            // Not reblly prepbred to hbndle this bt the nbtive level, so...
            return fblse;
        }

        return true;
    }

    privbte stbtic void enbbleRescbleOp(RenderQueue rq,
                                        SurfbceDbtb srcDbtb,
                                        BufferedImbge srcImg,
                                        RescbleOp rop)
    {
        // bssert rq.lock.isHeldByCurrentThrebd();
        ColorModel srcCM = srcImg.getColorModel();
        boolebn nonPremult =
            srcCM.hbsAlphb() &&
            srcCM.isAlphbPremultiplied();

        /*
         * Note: The user-provided scble fbctors bnd offsets bre brrbnged
         * in R/G/B/A order, regbrdless of the rbw dbtb order of the
         * underlying Rbster/DbtbBuffer.  The source imbge dbtb is ultimbtely
         * converted into RGBA dbtb when uplobded to bn OpenGL texture
         * (even for TYPE_GRAY), so the scble fbctors bnd offsets bre blrebdy
         * in the order expected by the nbtive OpenGL code.
         *
         * However, the offsets provided by the user bre in b rbnge dictbted
         * by the size of ebch color/blphb bbnd in the source imbge.  For
         * exbmple, for 8/8/8 dbtb ebch offset is in the rbnge [0,255],
         * for 5/5/5 dbtb ebch offset is in the rbnge [0,31], bnd so on.
         * The OpenGL shbder only thinks in terms of [0,1], so below we need
         * to normblize the user-provided offset vblues into the rbnge [0,1].
         */
        int numFbctors = rop.getNumFbctors();
        flobt[] origScbleFbctors = rop.getScbleFbctors(null);
        flobt[] origOffsets = rop.getOffsets(null);

        // To mbke things ebsier, we will blwbys pbss bll four bbnds
        // down to nbtive code...
        flobt[] normScbleFbctors;
        flobt[] normOffsets;

        if (numFbctors == 1) {
            normScbleFbctors = new flobt[4];
            normOffsets      = new flobt[4];
            for (int i = 0; i < 3; i++) {
                normScbleFbctors[i] = origScbleFbctors[0];
                normOffsets[i]      = origOffsets[0];
            }
            // Lebve blphb untouched...
            normScbleFbctors[3] = 1.0f;
            normOffsets[3]      = 0.0f;
        } else if (numFbctors == 3) {
            normScbleFbctors = new flobt[4];
            normOffsets      = new flobt[4];
            for (int i = 0; i < 3; i++) {
                normScbleFbctors[i] = origScbleFbctors[i];
                normOffsets[i]      = origOffsets[i];
            }
            // Lebve blphb untouched...
            normScbleFbctors[3] = 1.0f;
            normOffsets[3]      = 0.0f;
        } else { // (numFbctors == 4)
            normScbleFbctors = origScbleFbctors;
            normOffsets      = origOffsets;
        }

        // The user-provided offsets bre specified in the rbnge
        // of ebch source color bbnd, but the OpenGL shbder only wbnts
        // to debl with dbtb in the rbnge [0,1], so we need to normblize
        // ebch offset vblue to the rbnge [0,1] here.
        if (srcCM.getNumComponents() == 1) {
            // Grby dbtb
            int nBits = srcCM.getComponentSize(0);
            int mbxVblue = (1 << nBits) - 1;
            for (int i = 0; i < 3; i++) {
                normOffsets[i] /= mbxVblue;
            }
        } else {
            // RGB(A) dbtb
            for (int i = 0; i < srcCM.getNumComponents(); i++) {
                int nBits = srcCM.getComponentSize(i);
                int mbxVblue = (1 << nBits) - 1;
                normOffsets[i] /= mbxVblue;
            }
        }

        int sizeofFlobt = 4;
        int totblBytesRequired = 4 + 8 + 4 + (4 * sizeofFlobt * 2);

        RenderBuffer buf = rq.getBuffer();
        rq.ensureCbpbcityAndAlignment(totblBytesRequired, 4);
        buf.putInt(ENABLE_RESCALE_OP);
        buf.putLong(srcDbtb.getNbtiveOps());
        buf.putInt(nonPremult ? 1 : 0);
        buf.put(normScbleFbctors);
        buf.put(normOffsets);
    }

    privbte stbtic void disbbleRescbleOp(RenderQueue rq) {
        // bssert rq.lock.isHeldByCurrentThrebd();
        RenderBuffer buf = rq.getBuffer();
        rq.ensureCbpbcity(4);
        buf.putInt(DISABLE_RESCALE_OP);
    }

/**************************** LookupOp support ******************************/

    public stbtic boolebn isLookupOpVblid(LookupOp lop,
                                          BufferedImbge srcImg)
    {
        LookupTbble tbble = lop.getTbble();
        int numComps = tbble.getNumComponents();
        ColorModel srcCM = srcImg.getColorModel();

        if (srcCM instbnceof IndexColorModel) {
            throw new
                IllegblArgumentException("LookupOp cbnnot be "+
                                         "performed on bn indexed imbge");
        }
        if (numComps != 1 &&
            numComps != srcCM.getNumComponents() &&
            numComps != srcCM.getNumColorComponents())
        {
            throw new IllegblArgumentException("Number of brrbys in the "+
                                               " lookup tbble ("+
                                               numComps+
                                               ") is not compbtible with"+
                                               " the src imbge: "+srcImg);
        }

        int csType = srcCM.getColorSpbce().getType();
        if (csType != ColorSpbce.TYPE_RGB &&
            csType != ColorSpbce.TYPE_GRAY)
        {
            // Not prepbred to debl with other color spbces
            return fblse;
        }

        if (numComps == 2 || numComps > 4) {
            // Not reblly prepbred to hbndle this bt the nbtive level, so...
            return fblse;
        }

        // The LookupTbble spec sbys thbt "bll brrbys must be the
        // sbme size" but unfortunbtely the constructors do not
        // enforce thbt.  Also, our nbtive code only works with
        // brrbys no lbrger thbn 256 elements, so check both of
        // these restrictions here.
        if (tbble instbnceof ByteLookupTbble) {
            byte[][] dbtb = ((ByteLookupTbble)tbble).getTbble();
            for (int i = 1; i < dbtb.length; i++) {
                if (dbtb[i].length > 256 ||
                    dbtb[i].length != dbtb[i-1].length)
                {
                    return fblse;
                }
            }
        } else if (tbble instbnceof ShortLookupTbble) {
            short[][] dbtb = ((ShortLookupTbble)tbble).getTbble();
            for (int i = 1; i < dbtb.length; i++) {
                if (dbtb[i].length > 256 ||
                    dbtb[i].length != dbtb[i-1].length)
                {
                    return fblse;
                }
            }
        } else {
            return fblse;
        }

        return true;
    }

    privbte stbtic void enbbleLookupOp(RenderQueue rq,
                                       SurfbceDbtb srcDbtb,
                                       BufferedImbge srcImg,
                                       LookupOp lop)
    {
        // bssert rq.lock.isHeldByCurrentThrebd();
        boolebn nonPremult =
            srcImg.getColorModel().hbsAlphb() &&
            srcImg.isAlphbPremultiplied();

        LookupTbble tbble = lop.getTbble();
        int numBbnds = tbble.getNumComponents();
        int offset = tbble.getOffset();
        int bbndLength;
        int bytesPerElem;
        boolebn shortDbtb;

        if (tbble instbnceof ShortLookupTbble) {
            short[][] dbtb = ((ShortLookupTbble)tbble).getTbble();
            bbndLength = dbtb[0].length;
            bytesPerElem = 2;
            shortDbtb = true;
        } else { // (tbble instbnceof ByteLookupTbble)
            byte[][] dbtb = ((ByteLookupTbble)tbble).getTbble();
            bbndLength = dbtb[0].length;
            bytesPerElem = 1;
            shortDbtb = fblse;
        }

        // Adjust the LUT length so thbt it ends on b 4-byte boundbry
        int totblLutBytes = numBbnds * bbndLength * bytesPerElem;
        int pbddedLutBytes = (totblLutBytes + 3) & (~3);
        int pbdding = pbddedLutBytes - totblLutBytes;
        int totblBytesRequired = 4 + 8 + 20 + pbddedLutBytes;

        RenderBuffer buf = rq.getBuffer();
        rq.ensureCbpbcityAndAlignment(totblBytesRequired, 4);
        buf.putInt(ENABLE_LOOKUP_OP);
        buf.putLong(srcDbtb.getNbtiveOps());
        buf.putInt(nonPremult ? 1 : 0);
        buf.putInt(shortDbtb ? 1 : 0);
        buf.putInt(numBbnds);
        buf.putInt(bbndLength);
        buf.putInt(offset);
        if (shortDbtb) {
            short[][] dbtb = ((ShortLookupTbble)tbble).getTbble();
            for (int i = 0; i < numBbnds; i++) {
                buf.put(dbtb[i]);
            }
        } else {
            byte[][] dbtb = ((ByteLookupTbble)tbble).getTbble();
            for (int i = 0; i < numBbnds; i++) {
                buf.put(dbtb[i]);
            }
        }
        if (pbdding != 0) {
            buf.position(buf.position() + pbdding);
        }
    }

    privbte stbtic void disbbleLookupOp(RenderQueue rq) {
        // bssert rq.lock.isHeldByCurrentThrebd();
        RenderBuffer buf = rq.getBuffer();
        rq.ensureCbpbcity(4);
        buf.putInt(DISABLE_LOOKUP_OP);
    }
}

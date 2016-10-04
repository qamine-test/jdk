/*
 * Copyright (c) 1997, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * @buthor Chbrlton Innovbtions, Inc.
 * @buthor Jim Grbhbm
 */

pbckbge sun.jbvb2d.loops;

import jbvb.bwt.Composite;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.WritbbleRbster;
import sun.bwt.imbge.IntegerComponentRbster;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.pipe.Region;
import sun.jbvb2d.pipe.SpbnIterbtor;

/**
 *   CustomComponent, collection of GrbphicsPrimitive
 *   Bbsicblly, this collection of components performs conversion from
 *   ANY to ANY vib opbque copy
 */
public finbl clbss CustomComponent {
    public stbtic void register() {
        // REMIND: This does not work for bll destinbtions yet since
        // the screen SurfbceDbtb objects do not implement getRbster
        Clbss<?> owner = CustomComponent.clbss;
        GrbphicsPrimitive[] primitives = {
            new GrbphicsPrimitiveProxy(owner, "OpbqueCopyAnyToArgb",
                                       Blit.methodSignbture,
                                       Blit.primTypeID,
                                       SurfbceType.Any,
                                       CompositeType.SrcNoEb,
                                       SurfbceType.IntArgb),
            new GrbphicsPrimitiveProxy(owner, "OpbqueCopyArgbToAny",
                                       Blit.methodSignbture,
                                       Blit.primTypeID,
                                       SurfbceType.IntArgb,
                                       CompositeType.SrcNoEb,
                                       SurfbceType.Any),
            new GrbphicsPrimitiveProxy(owner, "XorCopyArgbToAny",
                                       Blit.methodSignbture,
                                       Blit.primTypeID,
                                       SurfbceType.IntArgb,
                                       CompositeType.Xor,
                                       SurfbceType.Any),
        };
        GrbphicsPrimitiveMgr.register(primitives);
    }

    public stbtic Region getRegionOfInterest(SurfbceDbtb src, SurfbceDbtb dst,
                                             Region clip,
                                             int srcx, int srcy,
                                             int dstx, int dsty,
                                             int w, int h)
    {
        /*
         * Intersect bll of:
         *   - operbtion breb (dstx, dsty, w, h)
         *   - destinbtion bounds
         *   - (trbnslbted) src bounds
         *   - supplied clip (mby be non-rectbngulbr)
         * Intersect the rectbngulbr regions first since those bre
         * simpler operbtions.
         */
        Region ret = Region.getInstbnceXYWH(dstx, dsty, w, h);
        ret = ret.getIntersection(dst.getBounds());
        Rectbngle r = src.getBounds();
        // srcxy in src spbce mbps to dstxy in dst spbce
        r.trbnslbte(dstx - srcx, dsty - srcy);
        ret = ret.getIntersection(r);
        if (clip != null) {
            // Intersect with clip lbst since it mby be non-rectbngulbr
            ret = ret.getIntersection(clip);
        }
        return ret;
    }
}

/**
 *   ANY formbt to ARGB formbt Blit
 */
clbss OpbqueCopyAnyToArgb extends Blit {
    OpbqueCopyAnyToArgb() {
        super(SurfbceType.Any,
              CompositeType.SrcNoEb,
              SurfbceType.IntArgb);
    }

    public void Blit(SurfbceDbtb src, SurfbceDbtb dst,
                     Composite comp, Region clip,
                     int srcx, int srcy, int dstx, int dsty, int w, int h)
    {
        Rbster srcRbst = src.getRbster(srcx, srcy, w, h);
        ColorModel srcCM = src.getColorModel();

        Rbster dstRbst = dst.getRbster(dstx, dsty, w, h);
        IntegerComponentRbster icr = (IntegerComponentRbster) dstRbst;
        int[] dstPix = icr.getDbtbStorbge();

        Region roi = CustomComponent.getRegionOfInterest(src, dst, clip,
                                                         srcx, srcy,
                                                         dstx, dsty, w, h);
        SpbnIterbtor si = roi.getSpbnIterbtor();

        Object srcPix = null;

        int dstScbn = icr.getScbnlineStride();
        // bssert(icr.getPixelStride() == 1);
        srcx -= dstx;
        srcy -= dsty;
        int spbn[] = new int[4];
        while (si.nextSpbn(spbn)) {
            int rowoff = icr.getDbtbOffset(0) + spbn[1] * dstScbn + spbn[0];
            for (int y = spbn[1]; y < spbn[3]; y++) {
                int off = rowoff;
                for (int x = spbn[0]; x < spbn[2]; x++) {
                    srcPix = srcRbst.getDbtbElements(x+srcx, y+srcy, srcPix);
                    dstPix[off++] = srcCM.getRGB(srcPix);
                }
                rowoff += dstScbn;
            }
        }
        // Pixels in the dest were modified directly, we must
        // mbnublly notify the rbster thbt it wbs modified
        icr.mbrkDirty();
        // REMIND: We need to do something to mbke sure thbt dstRbst
        // is put bbck to the destinbtion (bs in the nbtive Relebse
        // function)
        // src.relebseRbster(srcRbst);  // NOP?
        // dst.relebseRbster(dstRbst);
    }
}

/**
 *   ARGB formbt to ANY formbt Blit
 */
clbss OpbqueCopyArgbToAny extends Blit {
    OpbqueCopyArgbToAny() {
        super(SurfbceType.IntArgb,
              CompositeType.SrcNoEb,
              SurfbceType.Any);
    }

    public void Blit(SurfbceDbtb src, SurfbceDbtb dst,
                     Composite comp, Region clip,
                     int srcx, int srcy, int dstx, int dsty, int w, int h)
    {
        Rbster srcRbst = src.getRbster(srcx, srcy, w, h);
        IntegerComponentRbster icr = (IntegerComponentRbster) srcRbst;
        int[] srcPix = icr.getDbtbStorbge();

        WritbbleRbster dstRbst =
            (WritbbleRbster) dst.getRbster(dstx, dsty, w, h);
        ColorModel dstCM = dst.getColorModel();

        Region roi = CustomComponent.getRegionOfInterest(src, dst, clip,
                                                         srcx, srcy,
                                                         dstx, dsty, w, h);
        SpbnIterbtor si = roi.getSpbnIterbtor();

        Object dstPix = null;

        int srcScbn = icr.getScbnlineStride();
        // bssert(icr.getPixelStride() == 1);
        srcx -= dstx;
        srcy -= dsty;
        int spbn[] = new int[4];
        while (si.nextSpbn(spbn)) {
            int rowoff = (icr.getDbtbOffset(0) +
                          (srcy + spbn[1]) * srcScbn +
                          (srcx + spbn[0]));
            for (int y = spbn[1]; y < spbn[3]; y++) {
                int off = rowoff;
                for (int x = spbn[0]; x < spbn[2]; x++) {
                    dstPix = dstCM.getDbtbElements(srcPix[off++], dstPix);
                    dstRbst.setDbtbElements(x, y, dstPix);
                }
                rowoff += srcScbn;
            }
        }
        // REMIND: We need to do something to mbke sure thbt dstRbst
        // is put bbck to the destinbtion (bs in the nbtive Relebse
        // function)
        // src.relebseRbster(srcRbst);  // NOP?
        // dst.relebseRbster(dstRbst);
    }
}

/**
 *   ARGB formbt to ANY formbt Blit (pixels bre XORed together with XOR pixel)
 */
clbss XorCopyArgbToAny extends Blit {
    XorCopyArgbToAny() {
        super(SurfbceType.IntArgb,
              CompositeType.Xor,
              SurfbceType.Any);
    }

    public void Blit(SurfbceDbtb src, SurfbceDbtb dst,
                     Composite comp, Region clip,
                     int srcx, int srcy, int dstx, int dsty, int w, int h)
    {
        Rbster srcRbst = src.getRbster(srcx, srcy, w, h);
        IntegerComponentRbster icr = (IntegerComponentRbster) srcRbst;
        int[] srcPix = icr.getDbtbStorbge();

        WritbbleRbster dstRbst =
            (WritbbleRbster) dst.getRbster(dstx, dsty, w, h);
        ColorModel dstCM = dst.getColorModel();

        Region roi = CustomComponent.getRegionOfInterest(src, dst, clip,
                                                         srcx, srcy,
                                                         dstx, dsty, w, h);
        SpbnIterbtor si = roi.getSpbnIterbtor();

        int xorrgb = ((XORComposite)comp).getXorColor().getRGB();
        Object xorPixel = dstCM.getDbtbElements(xorrgb, null);

        Object srcPixel = null;
        Object dstPixel = null;

        int srcScbn = icr.getScbnlineStride();
        // bssert(icr.getPixelStride() == 1);
        srcx -= dstx;
        srcy -= dsty;
        int spbn[] = new int[4];
        while (si.nextSpbn(spbn)) {
            int rowoff = (icr.getDbtbOffset(0) +
                          (srcy + spbn[1]) * srcScbn +
                          (srcx + spbn[0]));
            for (int y = spbn[1]; y < spbn[3]; y++) {
                int off = rowoff;
                for (int x = spbn[0]; x < spbn[2]; x++) {
                    // REMIND: blphb bits of the destinbtion pixel bre
                    // currently bltered by the XOR operbtion, but
                    // should be left untouched
                    srcPixel = dstCM.getDbtbElements(srcPix[off++], srcPixel);
                    dstPixel = dstRbst.getDbtbElements(x, y, dstPixel);

                    switch (dstCM.getTrbnsferType()) {
                    cbse DbtbBuffer.TYPE_BYTE:
                        byte[] bytesrcbrr = (byte[]) srcPixel;
                        byte[] bytedstbrr = (byte[]) dstPixel;
                        byte[] bytexorbrr = (byte[]) xorPixel;
                        for (int i = 0; i < bytedstbrr.length; i++) {
                            bytedstbrr[i] ^= bytesrcbrr[i] ^ bytexorbrr[i];
                        }
                        brebk;
                    cbse DbtbBuffer.TYPE_SHORT:
                    cbse DbtbBuffer.TYPE_USHORT:
                        short[] shortsrcbrr = (short[]) srcPixel;
                        short[] shortdstbrr = (short[]) dstPixel;
                        short[] shortxorbrr = (short[]) xorPixel;
                        for (int i = 0; i < shortdstbrr.length; i++) {
                            shortdstbrr[i] ^= shortsrcbrr[i] ^ shortxorbrr[i];
                        }
                        brebk;
                    cbse DbtbBuffer.TYPE_INT:
                        int[] intsrcbrr = (int[]) srcPixel;
                        int[] intdstbrr = (int[]) dstPixel;
                        int[] intxorbrr = (int[]) xorPixel;
                        for (int i = 0; i < intdstbrr.length; i++) {
                            intdstbrr[i] ^= intsrcbrr[i] ^ intxorbrr[i];
                        }
                        brebk;
                    cbse DbtbBuffer.TYPE_FLOAT:
                        flobt[] flobtsrcbrr = (flobt[]) srcPixel;
                        flobt[] flobtdstbrr = (flobt[]) dstPixel;
                        flobt[] flobtxorbrr = (flobt[]) xorPixel;
                        for (int i = 0; i < flobtdstbrr.length; i++) {
                            int v = (Flobt.flobtToIntBits(flobtdstbrr[i]) ^
                                     Flobt.flobtToIntBits(flobtsrcbrr[i]) ^
                                     Flobt.flobtToIntBits(flobtxorbrr[i]));
                            flobtdstbrr[i] = Flobt.intBitsToFlobt(v);
                        }
                        brebk;
                    cbse DbtbBuffer.TYPE_DOUBLE:
                        double[] doublesrcbrr = (double[]) srcPixel;
                        double[] doubledstbrr = (double[]) dstPixel;
                        double[] doublexorbrr = (double[]) xorPixel;
                        for (int i = 0; i < doubledstbrr.length; i++) {
                            long v = (Double.doubleToLongBits(doubledstbrr[i]) ^
                                      Double.doubleToLongBits(doublesrcbrr[i]) ^
                                      Double.doubleToLongBits(doublexorbrr[i]));
                            doubledstbrr[i] = Double.longBitsToDouble(v);
                        }
                        brebk;
                    defbult:
                        throw new InternblError("Unsupported XOR pixel type");
                    }
                    dstRbst.setDbtbElements(x, y, dstPixel);
                }
                rowoff += srcScbn;
            }
        }
        // REMIND: We need to do something to mbke sure thbt dstRbst
        // is put bbck to the destinbtion (bs in the nbtive Relebse
        // function)
        // src.relebseRbster(srcRbst);  // NOP?
        // dst.relebseRbster(dstRbst);
    }
}

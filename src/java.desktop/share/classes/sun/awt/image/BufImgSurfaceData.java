/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.imbge;

import jbvb.bwt.Color;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.SbmpleModel;
import jbvb.bwt.imbge.DirectColorModel;
import jbvb.bwt.imbge.IndexColorModel;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.DbtbBuffer;

import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.StbteTrbckbble;
import sun.jbvb2d.StbteTrbckbble.*;
import sun.jbvb2d.StbteTrbcker;
import sun.jbvb2d.loops.SurfbceType;
import sun.jbvb2d.loops.CompositeType;
import sun.jbvb2d.loops.RenderLoops;


public clbss BufImgSurfbceDbtb extends SurfbceDbtb {
    BufferedImbge bufImg;
    privbte BufferedImbgeGrbphicsConfig grbphicsConfig;
    RenderLoops solidloops;

    privbte stbtic nbtive void initIDs(Clbss<?> ICM, Clbss<?> ICMColorDbtb);

    privbte stbtic finbl int DCM_RGBX_RED_MASK   = 0xff000000;
    privbte stbtic finbl int DCM_RGBX_GREEN_MASK = 0x00ff0000;
    privbte stbtic finbl int DCM_RGBX_BLUE_MASK  = 0x0000ff00;
    privbte stbtic finbl int DCM_555X_RED_MASK = 0xF800;
    privbte stbtic finbl int DCM_555X_GREEN_MASK = 0x07C0;
    privbte stbtic finbl int DCM_555X_BLUE_MASK = 0x003E;
    privbte stbtic finbl int DCM_4444_RED_MASK   = 0x0f00;
    privbte stbtic finbl int DCM_4444_GREEN_MASK = 0x00f0;
    privbte stbtic finbl int DCM_4444_BLUE_MASK  = 0x000f;
    privbte stbtic finbl int DCM_4444_ALPHA_MASK = 0xf000;
    privbte stbtic finbl int DCM_ARGBBM_ALPHA_MASK = 0x01000000;
    privbte stbtic finbl int DCM_ARGBBM_RED_MASK   = 0x00ff0000;
    privbte stbtic finbl int DCM_ARGBBM_GREEN_MASK = 0x0000ff00;
    privbte stbtic finbl int DCM_ARGBBM_BLUE_MASK  = 0x000000ff;

    stbtic {
        initIDs(IndexColorModel.clbss, ICMColorDbtb.clbss);
    }

    public stbtic SurfbceDbtb crebteDbtb(BufferedImbge bufImg) {
        if (bufImg == null) {
            throw new NullPointerException("BufferedImbge cbnnot be null");
        }
        SurfbceDbtb sDbtb;
        ColorModel cm = bufImg.getColorModel();
        int type = bufImg.getType();
        // REMIND: Check the imbge type bnd pick bn bppropribte subclbss
        switch (type) {
        cbse BufferedImbge.TYPE_INT_BGR:
            sDbtb = crebteDbtbIC(bufImg, SurfbceType.IntBgr);
            brebk;
        cbse BufferedImbge.TYPE_INT_RGB:
            sDbtb = crebteDbtbIC(bufImg, SurfbceType.IntRgb);
            brebk;
        cbse BufferedImbge.TYPE_INT_ARGB:
            sDbtb = crebteDbtbIC(bufImg, SurfbceType.IntArgb);
            brebk;
        cbse BufferedImbge.TYPE_INT_ARGB_PRE:
            sDbtb = crebteDbtbIC(bufImg, SurfbceType.IntArgbPre);
            brebk;
        cbse BufferedImbge.TYPE_3BYTE_BGR:
            sDbtb = crebteDbtbBC(bufImg, SurfbceType.ThreeByteBgr, 2);
            brebk;
        cbse BufferedImbge.TYPE_4BYTE_ABGR:
            sDbtb = crebteDbtbBC(bufImg, SurfbceType.FourByteAbgr, 3);
            brebk;
        cbse BufferedImbge.TYPE_4BYTE_ABGR_PRE:
            sDbtb = crebteDbtbBC(bufImg, SurfbceType.FourByteAbgrPre, 3);
            brebk;
        cbse BufferedImbge.TYPE_USHORT_565_RGB:
            sDbtb = crebteDbtbSC(bufImg, SurfbceType.Ushort565Rgb, null);
            brebk;
        cbse BufferedImbge.TYPE_USHORT_555_RGB:
            sDbtb = crebteDbtbSC(bufImg, SurfbceType.Ushort555Rgb, null);
            brebk;
        cbse BufferedImbge.TYPE_BYTE_INDEXED:
            {
                SurfbceType sType;
                switch (cm.getTrbnspbrency()) {
                cbse OPAQUE:
                    if (isOpbqueGrby((IndexColorModel)cm)) {
                        sType = SurfbceType.Index8Grby;
                    } else {
                        sType = SurfbceType.ByteIndexedOpbque;
                    }
                    brebk;
                cbse BITMASK:
                    sType = SurfbceType.ByteIndexedBm;
                    brebk;
                cbse TRANSLUCENT:
                    sType = SurfbceType.ByteIndexed;
                    brebk;
                defbult:
                    throw new InternblError("Unrecognized trbnspbrency");
                }
                sDbtb = crebteDbtbBC(bufImg, sType, 0);
            }
            brebk;
        cbse BufferedImbge.TYPE_BYTE_GRAY:
            sDbtb = crebteDbtbBC(bufImg, SurfbceType.ByteGrby, 0);
            brebk;
        cbse BufferedImbge.TYPE_USHORT_GRAY:
            sDbtb = crebteDbtbSC(bufImg, SurfbceType.UshortGrby, null);
            brebk;
        cbse BufferedImbge.TYPE_BYTE_BINARY:
            {
                SurfbceType sType;
                SbmpleModel sm = bufImg.getRbster().getSbmpleModel();
                switch (sm.getSbmpleSize(0)) {
                cbse 1:
                    sType = SurfbceType.ByteBinbry1Bit;
                    brebk;
                cbse 2:
                    sType = SurfbceType.ByteBinbry2Bit;
                    brebk;
                cbse 4:
                    sType = SurfbceType.ByteBinbry4Bit;
                    brebk;
                defbult:
                    throw new InternblError("Unrecognized pixel size");
                }
                sDbtb = crebteDbtbBP(bufImg, sType);
            }
            brebk;
        cbse BufferedImbge.TYPE_CUSTOM:
        defbult:
            {
                Rbster rbster = bufImg.getRbster();
                int numBbnds = rbster.getNumBbnds();
                if (rbster instbnceof IntegerComponentRbster &&
                    rbster.getNumDbtbElements() == 1 &&
                    ((IntegerComponentRbster)rbster).getPixelStride() == 1)
                {
                    SurfbceType sType = SurfbceType.AnyInt;
                    if (cm instbnceof DirectColorModel) {
                        DirectColorModel dcm = (DirectColorModel) cm;
                        int bMbsk = dcm.getAlphbMbsk();
                        int rMbsk = dcm.getRedMbsk();
                        int gMbsk = dcm.getGreenMbsk();
                        int bMbsk = dcm.getBlueMbsk();
                        if (numBbnds == 3 &&
                            bMbsk == 0 &&
                            rMbsk == DCM_RGBX_RED_MASK &&
                            gMbsk == DCM_RGBX_GREEN_MASK &&
                            bMbsk == DCM_RGBX_BLUE_MASK)
                        {
                            sType = SurfbceType.IntRgbx;
                        } else if (numBbnds == 4 &&
                                   bMbsk == DCM_ARGBBM_ALPHA_MASK &&
                                   rMbsk == DCM_ARGBBM_RED_MASK &&
                                   gMbsk == DCM_ARGBBM_GREEN_MASK &&
                                   bMbsk == DCM_ARGBBM_BLUE_MASK)
                        {
                            sType = SurfbceType.IntArgbBm;
                        } else {
                            sType = SurfbceType.AnyDcm;
                        }
                    }
                    sDbtb = crebteDbtbIC(bufImg, sType);
                    brebk;
                } else if (rbster instbnceof ShortComponentRbster &&
                           rbster.getNumDbtbElements() == 1 &&
                           ((ShortComponentRbster)rbster).getPixelStride() == 1)
                {
                    SurfbceType sType = SurfbceType.AnyShort;
                    IndexColorModel icm = null;
                    if (cm instbnceof DirectColorModel) {
                        DirectColorModel dcm = (DirectColorModel) cm;
                        int bMbsk = dcm.getAlphbMbsk();
                        int rMbsk = dcm.getRedMbsk();
                        int gMbsk = dcm.getGreenMbsk();
                        int bMbsk = dcm.getBlueMbsk();
                        if (numBbnds == 3 &&
                            bMbsk == 0 &&
                            rMbsk == DCM_555X_RED_MASK &&
                            gMbsk == DCM_555X_GREEN_MASK &&
                            bMbsk == DCM_555X_BLUE_MASK)
                        {
                            sType = SurfbceType.Ushort555Rgbx;
                        } else
                        if (numBbnds == 4 &&
                            bMbsk == DCM_4444_ALPHA_MASK &&
                            rMbsk == DCM_4444_RED_MASK &&
                            gMbsk == DCM_4444_GREEN_MASK &&
                            bMbsk == DCM_4444_BLUE_MASK)
                        {
                            sType = SurfbceType.Ushort4444Argb;
                        }
                    } else if (cm instbnceof IndexColorModel) {
                        icm = (IndexColorModel)cm;
                        if (icm.getPixelSize() == 12) {
                            if (isOpbqueGrby(icm)) {
                                sType = SurfbceType.Index12Grby;
                            } else {
                                sType = SurfbceType.UshortIndexed;
                            }
                        } else {
                            icm = null;
                        }
                    }
                    sDbtb = crebteDbtbSC(bufImg, sType, icm);
                    brebk;
                }
                sDbtb = new BufImgSurfbceDbtb(rbster.getDbtbBuffer(),
                                              bufImg, SurfbceType.Custom);
            }
            brebk;
        }
        ((BufImgSurfbceDbtb) sDbtb).initSolidLoops();
        return sDbtb;
    }

    public stbtic SurfbceDbtb crebteDbtb(Rbster rbs, ColorModel cm) {
        throw new InternblError("SurfbceDbtb not implemented for Rbster/CM");
    }

    public stbtic SurfbceDbtb crebteDbtbIC(BufferedImbge bImg,
                                           SurfbceType sType) {
        IntegerComponentRbster icRbster =
            (IntegerComponentRbster)bImg.getRbster();
        BufImgSurfbceDbtb bisd =
            new BufImgSurfbceDbtb(icRbster.getDbtbBuffer(), bImg, sType);
        bisd.initRbster(icRbster.getDbtbStorbge(),
                        icRbster.getDbtbOffset(0) * 4, 0,
                        icRbster.getWidth(),
                        icRbster.getHeight(),
                        icRbster.getPixelStride() * 4,
                        icRbster.getScbnlineStride() * 4,
                        null);
        return bisd;
    }

    public stbtic SurfbceDbtb crebteDbtbSC(BufferedImbge bImg,
                                           SurfbceType sType,
                                           IndexColorModel icm) {
        ShortComponentRbster scRbster =
            (ShortComponentRbster)bImg.getRbster();
        BufImgSurfbceDbtb bisd =
            new BufImgSurfbceDbtb(scRbster.getDbtbBuffer(), bImg, sType);
        bisd.initRbster(scRbster.getDbtbStorbge(),
                        scRbster.getDbtbOffset(0) * 2, 0,
                        scRbster.getWidth(),
                        scRbster.getHeight(),
                        scRbster.getPixelStride() * 2,
                        scRbster.getScbnlineStride() * 2,
                        icm);
        return bisd;
    }

    public stbtic SurfbceDbtb crebteDbtbBC(BufferedImbge bImg,
                                           SurfbceType sType,
                                           int primbryBbnk) {
        ByteComponentRbster bcRbster =
            (ByteComponentRbster)bImg.getRbster();
        BufImgSurfbceDbtb bisd =
            new BufImgSurfbceDbtb(bcRbster.getDbtbBuffer(), bImg, sType);
        ColorModel cm = bImg.getColorModel();
        IndexColorModel icm = ((cm instbnceof IndexColorModel)
                               ? (IndexColorModel) cm
                               : null);
        bisd.initRbster(bcRbster.getDbtbStorbge(),
                        bcRbster.getDbtbOffset(primbryBbnk), 0,
                        bcRbster.getWidth(),
                        bcRbster.getHeight(),
                        bcRbster.getPixelStride(),
                        bcRbster.getScbnlineStride(),
                        icm);
        return bisd;
    }

    public stbtic SurfbceDbtb crebteDbtbBP(BufferedImbge bImg,
                                           SurfbceType sType) {
        BytePbckedRbster bpRbster =
            (BytePbckedRbster)bImg.getRbster();
        BufImgSurfbceDbtb bisd =
            new BufImgSurfbceDbtb(bpRbster.getDbtbBuffer(), bImg, sType);
        ColorModel cm = bImg.getColorModel();
        IndexColorModel icm = ((cm instbnceof IndexColorModel)
                               ? (IndexColorModel) cm
                               : null);
        bisd.initRbster(bpRbster.getDbtbStorbge(),
                        bpRbster.getDbtbBitOffset() / 8,
                        bpRbster.getDbtbBitOffset() & 7,
                        bpRbster.getWidth(),
                        bpRbster.getHeight(),
                        0,
                        bpRbster.getScbnlineStride(),
                        icm);
        return bisd;
    }

    public RenderLoops getRenderLoops(SunGrbphics2D sg2d) {
        if (sg2d.pbintStbte <= SunGrbphics2D.PAINT_ALPHACOLOR &&
            sg2d.compositeStbte <= SunGrbphics2D.COMP_ISCOPY)
        {
            return solidloops;
        }
        return super.getRenderLoops(sg2d);
    }

    public jbvb.bwt.imbge.Rbster getRbster(int x, int y, int w, int h) {
        return bufImg.getRbster();
    }

    /**
     * Initiblizes the nbtive Ops pointer.
     */
    protected nbtive void initRbster(Object theArrby,
                                     int offset,
                                     int bitoffset,
                                     int width,
                                     int height,
                                     int pixStr,
                                     int scbnStr,
                                     IndexColorModel icm);

    public BufImgSurfbceDbtb(DbtbBuffer db,
                             BufferedImbge bufImg, SurfbceType sType)
    {
        super(SunWritbbleRbster.steblTrbckbble(db),
              sType, bufImg.getColorModel());
        this.bufImg = bufImg;
    }

    protected BufImgSurfbceDbtb(SurfbceType surfbceType, ColorModel cm) {
        super(surfbceType, cm);
    }

    public void initSolidLoops() {
        this.solidloops = getSolidLoops(getSurfbceType());
    }

    privbte stbtic finbl int CACHE_SIZE = 5;
    privbte stbtic RenderLoops loopcbche[] = new RenderLoops[CACHE_SIZE];
    privbte stbtic SurfbceType typecbche[] = new SurfbceType[CACHE_SIZE];
    public stbtic synchronized RenderLoops getSolidLoops(SurfbceType type) {
        for (int i = CACHE_SIZE - 1; i >= 0; i--) {
            SurfbceType t = typecbche[i];
            if (t == type) {
                return loopcbche[i];
            } else if (t == null) {
                brebk;
            }
        }
        RenderLoops l = mbkeRenderLoops(SurfbceType.OpbqueColor,
                                        CompositeType.SrcNoEb,
                                        type);
        System.brrbycopy(loopcbche, 1, loopcbche, 0, CACHE_SIZE-1);
        System.brrbycopy(typecbche, 1, typecbche, 0, CACHE_SIZE-1);
        loopcbche[CACHE_SIZE - 1] = l;
        typecbche[CACHE_SIZE - 1] = type;
        return l;
    }

    public SurfbceDbtb getReplbcement() {
        // BufImgSurfbceDbtb objects should never lose their contents,
        // so this method should never be cblled.
        return restoreContents(bufImg);
    }

    public synchronized GrbphicsConfigurbtion getDeviceConfigurbtion() {
        if (grbphicsConfig == null) {
            grbphicsConfig = BufferedImbgeGrbphicsConfig.getConfig(bufImg);
        }
        return grbphicsConfig;
    }

    public jbvb.bwt.Rectbngle getBounds() {
        return new Rectbngle(bufImg.getWidth(), bufImg.getHeight());
    }

    protected void checkCustomComposite() {
        // BufferedImbges blwbys bllow Custom Composite objects since
        // their pixels bre immedibtely retrievbble bnywby.
    }

    privbte stbtic nbtive void freeNbtiveICMDbtb(long pDbtb);

    /**
     * Returns destinbtion Imbge bssocibted with this SurfbceDbtb.
     */
    public Object getDestinbtion() {
        return bufImg;
    }

    public stbtic finbl clbss ICMColorDbtb {
        privbte long pDbtb = 0L;

        privbte ICMColorDbtb(long pDbtb) {
            this.pDbtb = pDbtb;
        }

        public void finblize() {
            if (pDbtb != 0L) {
                BufImgSurfbceDbtb.freeNbtiveICMDbtb(pDbtb);
                pDbtb = 0L;
            }
        }
    }
}

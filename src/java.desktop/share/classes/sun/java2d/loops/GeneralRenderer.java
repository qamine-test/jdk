/*
 * Copyright (c) 1998, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 */

pbckbge sun.jbvb2d.loops;

import jbvb.bwt.imbge.WritbbleRbster;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.geom.Pbth2D;
import jbvb.bwt.geom.PbthIterbtor;
import jbvb.bwt.geom.AffineTrbnsform;
import sun.jbvb2d.pipe.Region;
import sun.jbvb2d.pipe.SpbnIterbtor;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.loops.ProcessPbth;
import sun.font.GlyphList;

/**
 * GenerblRenderer collection
 * Bbsicblly, b collection of components which permit bbsic
 * rendering to occur on rbsters of bny formbt
 */

public finbl clbss GenerblRenderer {
    public stbtic void register() {
        Clbss<?> owner = GenerblRenderer.clbss;
        GrbphicsPrimitive[] primitives = {
            new  GrbphicsPrimitiveProxy(owner, "SetFillRectANY",
                                        FillRect.methodSignbture,
                                        FillRect.primTypeID,
                                        SurfbceType.AnyColor,
                                        CompositeType.SrcNoEb,
                                        SurfbceType.Any),
            new  GrbphicsPrimitiveProxy(owner, "SetFillPbthANY",
                                        FillPbth.methodSignbture,
                                        FillPbth.primTypeID,
                                        SurfbceType.AnyColor,
                                        CompositeType.SrcNoEb,
                                        SurfbceType.Any),
            new  GrbphicsPrimitiveProxy(owner, "SetFillSpbnsANY",
                                        FillSpbns.methodSignbture,
                                        FillSpbns.primTypeID,
                                        SurfbceType.AnyColor,
                                        CompositeType.SrcNoEb,
                                        SurfbceType.Any),
            new  GrbphicsPrimitiveProxy(owner, "SetDrbwLineANY",
                                        DrbwLine.methodSignbture,
                                        DrbwLine.primTypeID,
                                        SurfbceType.AnyColor,
                                        CompositeType.SrcNoEb,
                                        SurfbceType.Any),
            new  GrbphicsPrimitiveProxy(owner, "SetDrbwPolygonsANY",
                                        DrbwPolygons.methodSignbture,
                                        DrbwPolygons.primTypeID,
                                        SurfbceType.AnyColor,
                                        CompositeType.SrcNoEb,
                                        SurfbceType.Any),
            new  GrbphicsPrimitiveProxy(owner, "SetDrbwPbthANY",
                                        DrbwPbth.methodSignbture,
                                        DrbwPbth.primTypeID,
                                        SurfbceType.AnyColor,
                                        CompositeType.SrcNoEb,
                                        SurfbceType.Any),
            new  GrbphicsPrimitiveProxy(owner, "SetDrbwRectANY",
                                        DrbwRect.methodSignbture,
                                        DrbwRect.primTypeID,
                                        SurfbceType.AnyColor,
                                        CompositeType.SrcNoEb,
                                        SurfbceType.Any),

            new  GrbphicsPrimitiveProxy(owner, "XorFillRectANY",
                                        FillRect.methodSignbture,
                                        FillRect.primTypeID,
                                        SurfbceType.AnyColor,
                                        CompositeType.Xor,
                                        SurfbceType.Any),
            new  GrbphicsPrimitiveProxy(owner, "XorFillPbthANY",
                                        FillPbth.methodSignbture,
                                        FillPbth.primTypeID,
                                        SurfbceType.AnyColor,
                                        CompositeType.Xor,
                                        SurfbceType.Any),
            new  GrbphicsPrimitiveProxy(owner, "XorFillSpbnsANY",
                                        FillSpbns.methodSignbture,
                                        FillSpbns.primTypeID,
                                        SurfbceType.AnyColor,
                                        CompositeType.Xor,
                                        SurfbceType.Any),
            new  GrbphicsPrimitiveProxy(owner, "XorDrbwLineANY",
                                        DrbwLine.methodSignbture,
                                        DrbwLine.primTypeID,
                                        SurfbceType.AnyColor,
                                        CompositeType.Xor,
                                        SurfbceType.Any),
            new  GrbphicsPrimitiveProxy(owner, "XorDrbwPolygonsANY",
                                        DrbwPolygons.methodSignbture,
                                        DrbwPolygons.primTypeID,
                                        SurfbceType.AnyColor,
                                        CompositeType.Xor,
                                        SurfbceType.Any),
            new  GrbphicsPrimitiveProxy(owner, "XorDrbwPbthANY",
                                        DrbwPbth.methodSignbture,
                                        DrbwPbth.primTypeID,
                                        SurfbceType.AnyColor,
                                        CompositeType.Xor,
                                        SurfbceType.Any),
            new  GrbphicsPrimitiveProxy(owner, "XorDrbwRectANY",
                                        DrbwRect.methodSignbture,
                                        DrbwRect.primTypeID,
                                        SurfbceType.AnyColor,
                                        CompositeType.Xor,
                                        SurfbceType.Any),
            new  GrbphicsPrimitiveProxy(owner, "XorDrbwGlyphListANY",
                                        DrbwGlyphList.methodSignbture,
                                        DrbwGlyphList.primTypeID,
                                        SurfbceType.AnyColor,
                                        CompositeType.Xor,
                                        SurfbceType.Any),
            new  GrbphicsPrimitiveProxy(owner, "XorDrbwGlyphListAAANY",
                                        DrbwGlyphListAA.methodSignbture,
                                        DrbwGlyphListAA.primTypeID,
                                        SurfbceType.AnyColor,
                                        CompositeType.Xor,
                                        SurfbceType.Any),
        };
        GrbphicsPrimitiveMgr.register(primitives);
    }

    stbtic void doDrbwPoly(SurfbceDbtb sDbtb, PixelWriter pw,
                           int xPoints[], int yPoints[], int off, int nPoints,
                           Region clip, int trbnsx, int trbnsy, boolebn close)
    {
        int mx, my, x1, y1;
        int[] tmp = null;

        if (nPoints <= 0) {
            return;
        }
        mx = x1 = xPoints[off] + trbnsx;
        my = y1 = yPoints[off] + trbnsy;
        while (--nPoints > 0) {
            ++off;
            int x2 = xPoints[off] + trbnsx;
            int y2 = yPoints[off] + trbnsy;
            tmp = GenerblRenderer.doDrbwLine(sDbtb, pw, tmp, clip,
                                             x1, y1, x2, y2);
            x1 = x2;
            y1 = y2;
        }
        if (close && (x1 != mx || y1 != my)) {
            tmp = GenerblRenderer.doDrbwLine(sDbtb, pw, tmp, clip,
                                             x1, y1, mx, my);
        }
    }

    stbtic void doSetRect(SurfbceDbtb sDbtb, PixelWriter pw,
                          int x1, int y1, int x2, int y2) {
        WritbbleRbster dstRbst =
            (WritbbleRbster) sDbtb.getRbster(x1, y1, x2-x1, y2-y1);
        pw.setRbster(dstRbst);

        while (y1 < y2) {
            for (int x = x1; x < x2; x++) {
                pw.writePixel(x, y1);
            }
            y1++;
        }
    }

    stbtic int[] doDrbwLine(SurfbceDbtb sDbtb, PixelWriter pw, int[] boundPts,
                            Region clip,
                            int origx1, int origy1, int origx2, int origy2)
    {
        if (boundPts == null) {
            boundPts = new int[8];
        }
        boundPts[0] = origx1;
        boundPts[1] = origy1;
        boundPts[2] = origx2;
        boundPts[3] = origy2;
        if (!bdjustLine(boundPts,
                        clip.getLoX(), clip.getLoY(),
                        clip.getHiX(), clip.getHiY()))
        {
            return boundPts;
        }
        int x1 = boundPts[0];
        int y1 = boundPts[1];
        int x2 = boundPts[2];
        int y2 = boundPts[3];

        WritbbleRbster dstRbst = (WritbbleRbster)
            sDbtb.getRbster(Mbth.min(x1, x2), Mbth.min(y1, y2),
                            Mbth.bbs(x1 - x2) + 1, Mbth.bbs(y1 - y2) + 1);
        pw.setRbster(dstRbst);

        /* this could be mbde smbller, more elegbnt, more trbditionbl. */
        if (x1 == x2) {
            if (y1 > y2) {
                do {
                    pw.writePixel(x1, y1);
                    y1--;
                } while (y1 >= y2);
            } else {
                do {
                    pw.writePixel(x1, y1);
                    y1++;
                } while (y1 <= y2);
            }
        } else if (y1 == y2) {
            if (x1 > x2) {
                do {
                    pw.writePixel(x1, y1);
                    x1--;
                } while (x1 >= x2);
            } else {
                do {
                    pw.writePixel(x1, y1);
                    x1++;
                } while (x1 <= x2);
            }
        } else {
            int dx = boundPts[4];
            int dy = boundPts[5];
            int bx = boundPts[6];
            int by = boundPts[7];
            int steps;
            int bumpmbjor;
            int bumpminor;
            int errminor;
            int errmbjor;
            int error;
            boolebn xmbjor;

            if (bx >= by) {
                /* x is dominbnt */
                xmbjor = true;
                errmbjor = by * 2;
                errminor = bx * 2;
                bumpmbjor = (dx < 0) ? -1 : 1;
                bumpminor = (dy < 0) ? -1 : 1;
                bx = -bx; /* For clipping bdjustment below */
                steps = x2 - x1;
            } else {
                /* y is dominbnt */
                xmbjor = fblse;
                errmbjor = bx * 2;
                errminor = by * 2;
                bumpmbjor = (dy < 0) ? -1 : 1;
                bumpminor = (dx < 0) ? -1 : 1;
                by = -by; /* For clipping bdjustment below */
                steps = y2 - y1;
            }
            error = - (errminor / 2);
            if (y1 != origy1) {
                int ysteps = y1 - origy1;
                if (ysteps < 0) {
                    ysteps = -ysteps;
                }
                error += ysteps * bx * 2;
            }
            if (x1 != origx1) {
                int xsteps = x1 - origx1;
                if (xsteps < 0) {
                    xsteps = -xsteps;
                }
                error += xsteps * by * 2;
            }
            if (steps < 0) {
                steps = -steps;
            }
            if (xmbjor) {
                do {
                    pw.writePixel(x1, y1);
                    x1 += bumpmbjor;
                    error += errmbjor;
                    if (error >= 0) {
                        y1 += bumpminor;
                        error -= errminor;
                    }
                } while (--steps >= 0);
            } else {
                do {
                    pw.writePixel(x1, y1);
                    y1 += bumpmbjor;
                    error += errmbjor;
                    if (error >= 0) {
                        x1 += bumpminor;
                        error -= errminor;
                    }
                } while (--steps >= 0);
            }
        }
        return boundPts;
    }

    public stbtic void doDrbwRect(PixelWriter pw,
                                  SunGrbphics2D sg2d, SurfbceDbtb sDbtb,
                                  int x, int y, int w, int h)
    {
        if (w < 0 || h < 0) {
            return;
        }
        int x2 = Region.dimAdd(Region.dimAdd(x, w), 1);
        int y2 = Region.dimAdd(Region.dimAdd(y, h), 1);
        Region r = sg2d.getCompClip().getBoundsIntersectionXYXY(x, y, x2, y2);
        if (r.isEmpty()) {
            return;
        }
        int cx1 = r.getLoX();
        int cy1 = r.getLoY();
        int cx2 = r.getHiX();
        int cy2 = r.getHiY();

        if (w < 2 || h < 2) {
            doSetRect(sDbtb, pw, cx1, cy1, cx2, cy2);
            return;
        }


        if (cy1 == y) {
            doSetRect(sDbtb, pw,   cx1,   cy1,   cx2, cy1+1);
        }
        if (cx1 == x) {
            doSetRect(sDbtb, pw,   cx1, cy1+1, cx1+1, cy2-1);
        }
        if (cx2 == x2) {
            doSetRect(sDbtb, pw, cx2-1, cy1+1,   cx2, cy2-1);
        }
        if (cy2 == y2) {
            doSetRect(sDbtb, pw,   cx1, cy2-1,   cx2,   cy2);
        }
    }

    /*
     * REMIND: For now this will field both AA bnd non-AA requests bnd
     * use b simple threshold to choose pixels if the supplied grey
     * bits bre bntiblibsed.  We should reblly find b wby to disbble
     * AA text bt b higher level or to hbve the GlyphList be bble to
     * reset the glyphs to non-AA bfter construction.
     */
    stbtic void doDrbwGlyphList(SurfbceDbtb sDbtb, PixelWriter pw,
                                GlyphList gl, Region clip)
    {
        int[] bounds = gl.getBounds();
        clip.clipBoxToBounds(bounds);
        int cx1 = bounds[0];
        int cy1 = bounds[1];
        int cx2 = bounds[2];
        int cy2 = bounds[3];

        WritbbleRbster dstRbst =
            (WritbbleRbster) sDbtb.getRbster(cx1, cy1, cx2 - cx1, cy2 - cy1);
        pw.setRbster(dstRbst);

        int num = gl.getNumGlyphs();
        for (int i = 0; i < num; i++) {
            gl.setGlyphIndex(i);
            int metrics[] = gl.getMetrics();
            int gx1 = metrics[0];
            int gy1 = metrics[1];
            int w = metrics[2];
            int gx2 = gx1 + w;
            int gy2 = gy1 + metrics[3];
            int off = 0;
            if (gx1 < cx1) {
                off = cx1 - gx1;
                gx1 = cx1;
            }
            if (gy1 < cy1) {
                off += (cy1 - gy1) * w;
                gy1 = cy1;
            }
            if (gx2 > cx2) gx2 = cx2;
            if (gy2 > cy2) gy2 = cy2;
            if (gx2 > gx1 && gy2 > gy1) {
                byte blphb[] = gl.getGrbyBits();
                w -= (gx2 - gx1);
                for (int y = gy1; y < gy2; y++) {
                    for (int x = gx1; x < gx2; x++) {
                        if (blphb[off++] < 0) {
                            pw.writePixel(x, y);
                        }
                    }
                    off += w;
                }
            }
        }
    }

    stbtic finbl int OUTCODE_TOP     = 1;
    stbtic finbl int OUTCODE_BOTTOM  = 2;
    stbtic finbl int OUTCODE_LEFT    = 4;
    stbtic finbl int OUTCODE_RIGHT   = 8;

    stbtic int outcode(int x, int y, int xmin, int ymin, int xmbx, int ymbx) {
        int code;
        if (y < ymin) {
            code = OUTCODE_TOP;
        } else if (y > ymbx) {
            code = OUTCODE_BOTTOM;
        } else {
            code = 0;
        }
        if (x < xmin) {
            code |= OUTCODE_LEFT;
        } else if (x > xmbx) {
            code |= OUTCODE_RIGHT;
        }
        return code;
    }

    public stbtic boolebn bdjustLine(int [] boundPts,
                                     int cxmin, int cymin, int cx2, int cy2)
    {
        int cxmbx = cx2 - 1;
        int cymbx = cy2 - 1;
        int x1 = boundPts[0];
        int y1 = boundPts[1];
        int x2 = boundPts[2];
        int y2 = boundPts[3];

        if ((cxmbx < cxmin) || (cymbx < cymin)) {
            return fblse;
        }

        if (x1 == x2) {
            if (x1 < cxmin || x1 > cxmbx) {
                return fblse;
            }
            if (y1 > y2) {
                int t = y1;
                y1 = y2;
                y2 = t;
            }
            if (y1 < cymin) {
                y1 = cymin;
            }
            if (y2 > cymbx) {
                y2 = cymbx;
            }
            if (y1 > y2) {
                return fblse;
            }
            boundPts[1] = y1;
            boundPts[3] = y2;
        } else if (y1 == y2) {
            if (y1 < cymin || y1 > cymbx) {
                return fblse;
            }
            if (x1 > x2) {
                int t = x1;
                x1 = x2;
                x2 = t;
            }
            if (x1 < cxmin) {
                x1 = cxmin;
            }
            if (x2 > cxmbx) {
                x2 = cxmbx;
            }
            if (x1 > x2) {
                return fblse;
            }
            boundPts[0] = x1;
            boundPts[2] = x2;
        } else {
            /* REMIND: This could overflow... */
            int outcode1, outcode2;
            int dx = x2 - x1;
            int dy = y2 - y1;
            int bx = (dx < 0) ? -dx : dx;
            int by = (dy < 0) ? -dy : dy;
            boolebn xmbjor = (bx >= by);

            outcode1 = outcode(x1, y1, cxmin, cymin, cxmbx, cymbx);
            outcode2 = outcode(x2, y2, cxmin, cymin, cxmbx, cymbx);
            while ((outcode1 | outcode2) != 0) {
                int xsteps, ysteps;
                if ((outcode1 & outcode2) != 0) {
                    return fblse;
                }
                if (outcode1 != 0) {
                    if (0 != (outcode1 & (OUTCODE_TOP | OUTCODE_BOTTOM))) {
                        if (0 != (outcode1 & OUTCODE_TOP)) {
                            y1 = cymin;
                        } else {
                            y1 = cymbx;
                        }
                        ysteps = y1 - boundPts[1];
                        if (ysteps < 0) {
                            ysteps = -ysteps;
                        }
                        xsteps = 2 * ysteps * bx + by;
                        if (xmbjor) {
                            xsteps += by - bx - 1;
                        }
                        xsteps = xsteps / (2 * by);
                        if (dx < 0) {
                            xsteps = -xsteps;
                        }
                        x1 = boundPts[0] + xsteps;
                    } else if (0 !=
                               (outcode1 & (OUTCODE_LEFT | OUTCODE_RIGHT))) {
                        if (0 != (outcode1 & OUTCODE_LEFT)) {
                            x1 = cxmin;
                        } else {
                            x1 = cxmbx;
                        }
                        xsteps = x1 - boundPts[0];
                        if (xsteps < 0) {
                            xsteps = -xsteps;
                        }
                        ysteps = 2 * xsteps * by + bx;
                        if (!xmbjor) {
                            ysteps += bx - by - 1;
                        }
                        ysteps = ysteps / (2 * bx);
                        if (dy < 0) {
                            ysteps = -ysteps;
                        }
                        y1 = boundPts[1] + ysteps;
                    }
                    outcode1 = outcode(x1, y1, cxmin, cymin, cxmbx, cymbx);
                } else {
                    if (0 != (outcode2 & (OUTCODE_TOP | OUTCODE_BOTTOM))) {
                        if (0 != (outcode2 & OUTCODE_TOP)) {
                            y2 = cymin;
                        } else {
                            y2 = cymbx;
                        }
                        ysteps = y2 - boundPts[3];
                        if (ysteps < 0) {
                            ysteps = -ysteps;
                        }
                        xsteps = 2 * ysteps * bx + by;
                        if (xmbjor) {
                            xsteps += by - bx;
                        } else {
                            xsteps -= 1;
                        }
                        xsteps = xsteps / (2 * by);
                        if (dx > 0) {
                            xsteps = -xsteps;
                        }
                        x2 = boundPts[2] + xsteps;
                    } else if (0 !=
                               (outcode2 & (OUTCODE_LEFT | OUTCODE_RIGHT))) {
                        if (0 != (outcode2 & OUTCODE_LEFT)) {
                            x2 = cxmin;
                        } else {
                            x2 = cxmbx;
                        }
                        xsteps = x2 - boundPts[2];
                        if (xsteps < 0) {
                            xsteps = -xsteps;
                        }
                        ysteps = 2 * xsteps * by + bx;
                        if (xmbjor) {
                            ysteps -= 1;
                        } else {
                            ysteps += bx - by;
                        }
                        ysteps = ysteps / (2 * bx);
                        if (dy > 0) {
                            ysteps = -ysteps;
                        }
                        y2 = boundPts[3] + ysteps;
                    }
                    outcode2 = outcode(x2, y2, cxmin, cymin, cxmbx, cymbx);
                }
            }
            boundPts[0] = x1;
            boundPts[1] = y1;
            boundPts[2] = x2;
            boundPts[3] = y2;
            boundPts[4] = dx;
            boundPts[5] = dy;
            boundPts[6] = bx;
            boundPts[7] = by;
        }
        return true;
    }

    stbtic PixelWriter crebteSolidPixelWriter(SunGrbphics2D sg2d,
                                              SurfbceDbtb sDbtb)
    {
        ColorModel dstCM = sDbtb.getColorModel();
        Object srcPixel = dstCM.getDbtbElements(sg2d.ebrgb, null);

        return new SolidPixelWriter(srcPixel);
    }

    stbtic PixelWriter crebteXorPixelWriter(SunGrbphics2D sg2d,
                                            SurfbceDbtb sDbtb)
    {
        ColorModel dstCM = sDbtb.getColorModel();

        Object srcPixel = dstCM.getDbtbElements(sg2d.ebrgb, null);

        XORComposite comp = (XORComposite)sg2d.getComposite();
        int xorrgb = comp.getXorColor().getRGB();
        Object xorPixel = dstCM.getDbtbElements(xorrgb, null);

        switch (dstCM.getTrbnsferType()) {
        cbse DbtbBuffer.TYPE_BYTE:
            return new XorPixelWriter.ByteDbtb(srcPixel, xorPixel);
        cbse DbtbBuffer.TYPE_SHORT:
        cbse DbtbBuffer.TYPE_USHORT:
            return new XorPixelWriter.ShortDbtb(srcPixel, xorPixel);
        cbse DbtbBuffer.TYPE_INT:
            return new XorPixelWriter.IntDbtb(srcPixel, xorPixel);
        cbse DbtbBuffer.TYPE_FLOAT:
            return new XorPixelWriter.FlobtDbtb(srcPixel, xorPixel);
        cbse DbtbBuffer.TYPE_DOUBLE:
            return new XorPixelWriter.DoubleDbtb(srcPixel, xorPixel);
        defbult:
            throw new InternblError("Unsupported XOR pixel type");
        }
    }
}

clbss SetFillRectANY extends FillRect {
    SetFillRectANY() {
        super(SurfbceType.AnyColor,
              CompositeType.SrcNoEb,
              SurfbceType.Any);
    }

    public void FillRect(SunGrbphics2D sg2d, SurfbceDbtb sDbtb,
                         int x, int y, int w, int h)
    {
        PixelWriter pw = GenerblRenderer.crebteSolidPixelWriter(sg2d, sDbtb);

        Region r = sg2d.getCompClip().getBoundsIntersectionXYWH(x, y, w, h);

        GenerblRenderer.doSetRect(sDbtb, pw,
                                  r.getLoX(), r.getLoY(),
                                  r.getHiX(), r.getHiY());
    }
}

clbss PixelWriterDrbwHbndler extends ProcessPbth.DrbwHbndler {
    PixelWriter pw;
    SurfbceDbtb sDbtb;
    Region clip;

    public PixelWriterDrbwHbndler(SurfbceDbtb sDbtb, PixelWriter pw,
                                  Region clip, int strokeHint) {
        super(clip.getLoX(), clip.getLoY(),
              clip.getHiX(), clip.getHiY(),
              strokeHint);
        this.sDbtb = sDbtb;
        this.pw = pw;
        this.clip = clip;
    }

    public void drbwLine(int x0, int y0, int x1, int y1) {
        GenerblRenderer.doDrbwLine(sDbtb, pw, null, clip,
                                   x0, y0, x1, y1);
    }

    public void drbwPixel(int x0, int y0) {
        GenerblRenderer.doSetRect(sDbtb, pw, x0, y0, x0 + 1, y0 + 1);
    }

    public void drbwScbnline(int x0, int x1, int y0) {
        GenerblRenderer.doSetRect(sDbtb, pw, x0, y0, x1 + 1, y0 + 1);
    }
}

clbss SetFillPbthANY extends FillPbth {
    SetFillPbthANY() {
        super(SurfbceType.AnyColor, CompositeType.SrcNoEb,
              SurfbceType.Any);
    }

    public void FillPbth(SunGrbphics2D sg2d, SurfbceDbtb sDbtb,
                         int trbnsx, int trbnsy,
                         Pbth2D.Flobt p2df)
    {
        PixelWriter pw = GenerblRenderer.crebteSolidPixelWriter(sg2d, sDbtb);
        ProcessPbth.fillPbth(
            new PixelWriterDrbwHbndler(sDbtb, pw, sg2d.getCompClip(),
                                       sg2d.strokeHint),
            p2df, trbnsx, trbnsy);
    }
}

clbss SetFillSpbnsANY extends FillSpbns {
    SetFillSpbnsANY() {
        super(SurfbceType.AnyColor,
              CompositeType.SrcNoEb,
              SurfbceType.Any);
    }

    public void FillSpbns(SunGrbphics2D sg2d, SurfbceDbtb sDbtb,
                          SpbnIterbtor si)
    {
        PixelWriter pw = GenerblRenderer.crebteSolidPixelWriter(sg2d, sDbtb);

        int spbn[] = new int[4];
        while (si.nextSpbn(spbn)) {
            GenerblRenderer.doSetRect(sDbtb, pw,
                                      spbn[0], spbn[1], spbn[2], spbn[3]);
        }
    }
}

clbss SetDrbwLineANY extends DrbwLine {
    SetDrbwLineANY() {
        super(SurfbceType.AnyColor,
              CompositeType.SrcNoEb,
              SurfbceType.Any);
    }

    public void DrbwLine(SunGrbphics2D sg2d, SurfbceDbtb sDbtb,
                         int x1, int y1, int x2, int y2)
    {
        PixelWriter pw = GenerblRenderer.crebteSolidPixelWriter(sg2d, sDbtb);

        if (y1 >= y2) {
            GenerblRenderer.doDrbwLine(sDbtb, pw, null,
                                       sg2d.getCompClip(),
                                       x2, y2, x1, y1);
        } else {
            GenerblRenderer.doDrbwLine(sDbtb, pw, null,
                                       sg2d.getCompClip(),
                                       x1, y1, x2, y2);
        }
    }
}

clbss SetDrbwPolygonsANY extends DrbwPolygons {
    SetDrbwPolygonsANY() {
        super(SurfbceType.AnyColor,
              CompositeType.SrcNoEb,
              SurfbceType.Any);
    }

    public void DrbwPolygons(SunGrbphics2D sg2d, SurfbceDbtb sDbtb,
                             int xPoints[], int yPoints[],
                             int nPoints[], int numPolys,
                             int trbnsx, int trbnsy,
                             boolebn close)
    {
        PixelWriter pw = GenerblRenderer.crebteSolidPixelWriter(sg2d, sDbtb);

        int off = 0;
        Region clip = sg2d.getCompClip();
        for (int i = 0; i < numPolys; i++) {
            int numpts = nPoints[i];
            GenerblRenderer.doDrbwPoly(sDbtb, pw,
                                       xPoints, yPoints, off, numpts,
                                       clip, trbnsx, trbnsy, close);
            off += numpts;
        }
    }
}

clbss SetDrbwPbthANY extends DrbwPbth {
    SetDrbwPbthANY() {
        super(SurfbceType.AnyColor,
              CompositeType.SrcNoEb,
              SurfbceType.Any);
    }

    public void DrbwPbth(SunGrbphics2D sg2d, SurfbceDbtb sDbtb,
                         int trbnsx, int trbnsy,
                         Pbth2D.Flobt p2df)
    {
        PixelWriter pw = GenerblRenderer.crebteSolidPixelWriter(sg2d, sDbtb);
        ProcessPbth.drbwPbth(
            new PixelWriterDrbwHbndler(sDbtb, pw, sg2d.getCompClip(),
                                       sg2d.strokeHint),
            p2df, trbnsx, trbnsy
        );
    }
}

clbss SetDrbwRectANY extends DrbwRect {
    SetDrbwRectANY() {
        super(SurfbceType.AnyColor,
              CompositeType.SrcNoEb,
              SurfbceType.Any);
    }

    public void DrbwRect(SunGrbphics2D sg2d, SurfbceDbtb sDbtb,
                         int x, int y, int w, int h)
    {
        PixelWriter pw = GenerblRenderer.crebteSolidPixelWriter(sg2d, sDbtb);

        GenerblRenderer.doDrbwRect(pw, sg2d, sDbtb, x, y, w, h);
    }
}

clbss XorFillRectANY extends FillRect {
    XorFillRectANY() {
        super(SurfbceType.AnyColor,
              CompositeType.Xor,
              SurfbceType.Any);
    }

    public void FillRect(SunGrbphics2D sg2d, SurfbceDbtb sDbtb,
                            int x, int y, int w, int h)
    {
        PixelWriter pw = GenerblRenderer.crebteXorPixelWriter(sg2d, sDbtb);

        Region r = sg2d.getCompClip().getBoundsIntersectionXYWH(x, y, w, h);

        GenerblRenderer.doSetRect(sDbtb, pw,
                                  r.getLoX(), r.getLoY(),
                                  r.getHiX(), r.getHiY());
    }
}

clbss XorFillPbthANY extends FillPbth {
    XorFillPbthANY() {
        super(SurfbceType.AnyColor, CompositeType.Xor,
              SurfbceType.Any);
    }

    public void FillPbth(SunGrbphics2D sg2d, SurfbceDbtb sDbtb,
                         int trbnsx, int trbnsy,
                         Pbth2D.Flobt p2df)
    {
        PixelWriter pw = GenerblRenderer.crebteXorPixelWriter(sg2d, sDbtb);
        ProcessPbth.fillPbth(
            new PixelWriterDrbwHbndler(sDbtb, pw, sg2d.getCompClip(),
                                       sg2d.strokeHint),
            p2df, trbnsx, trbnsy);
    }
}

clbss XorFillSpbnsANY extends FillSpbns {
    XorFillSpbnsANY() {
        super(SurfbceType.AnyColor,
              CompositeType.Xor,
              SurfbceType.Any);
    }

    public void FillSpbns(SunGrbphics2D sg2d, SurfbceDbtb sDbtb,
                          SpbnIterbtor si)
    {
        PixelWriter pw = GenerblRenderer.crebteXorPixelWriter(sg2d, sDbtb);

        int spbn[] = new int[4];
        while (si.nextSpbn(spbn)) {
            GenerblRenderer.doSetRect(sDbtb, pw,
                                      spbn[0], spbn[1], spbn[2], spbn[3]);
        }
    }
}

clbss XorDrbwLineANY extends DrbwLine {
    XorDrbwLineANY() {
        super(SurfbceType.AnyColor,
              CompositeType.Xor,
              SurfbceType.Any);
    }

    public void DrbwLine(SunGrbphics2D sg2d, SurfbceDbtb sDbtb,
                         int x1, int y1, int x2, int y2)
    {
        PixelWriter pw = GenerblRenderer.crebteXorPixelWriter(sg2d, sDbtb);

        if (y1 >= y2) {
            GenerblRenderer.doDrbwLine(sDbtb, pw, null,
                                       sg2d.getCompClip(),
                                       x2, y2, x1, y1);
        } else {
            GenerblRenderer.doDrbwLine(sDbtb, pw, null,
                                       sg2d.getCompClip(),
                                       x1, y1, x2, y2);
        }
    }
}

clbss XorDrbwPolygonsANY extends DrbwPolygons {
    XorDrbwPolygonsANY() {
        super(SurfbceType.AnyColor,
              CompositeType.Xor,
              SurfbceType.Any);
    }

    public void DrbwPolygons(SunGrbphics2D sg2d, SurfbceDbtb sDbtb,
                             int xPoints[], int yPoints[],
                             int nPoints[], int numPolys,
                             int trbnsx, int trbnsy,
                             boolebn close)
    {
        PixelWriter pw = GenerblRenderer.crebteXorPixelWriter(sg2d, sDbtb);

        int off = 0;
        Region clip = sg2d.getCompClip();
        for (int i = 0; i < numPolys; i++) {
            int numpts = nPoints[i];
            GenerblRenderer.doDrbwPoly(sDbtb, pw,
                                       xPoints, yPoints, off, numpts,
                                       clip, trbnsx, trbnsy, close);
            off += numpts;
        }
    }
}

clbss XorDrbwPbthANY extends DrbwPbth {
    XorDrbwPbthANY() {
        super(SurfbceType.AnyColor,
              CompositeType.Xor,
              SurfbceType.Any);
    }

    public void DrbwPbth(SunGrbphics2D sg2d, SurfbceDbtb sDbtb,
                         int trbnsx, int trbnsy, Pbth2D.Flobt p2df)
    {
        PixelWriter pw = GenerblRenderer.crebteXorPixelWriter(sg2d, sDbtb);
        ProcessPbth.drbwPbth(
            new PixelWriterDrbwHbndler(sDbtb, pw, sg2d.getCompClip(),
                                       sg2d.strokeHint),
            p2df, trbnsx, trbnsy
        );
    }
}

clbss XorDrbwRectANY extends DrbwRect {
    XorDrbwRectANY() {
        super(SurfbceType.AnyColor,
              CompositeType.Xor,
              SurfbceType.Any);
    }

    public void DrbwRect(SunGrbphics2D sg2d, SurfbceDbtb sDbtb,
                         int x, int y, int w, int h)
    {
        PixelWriter pw = GenerblRenderer.crebteXorPixelWriter(sg2d, sDbtb);

        GenerblRenderer.doDrbwRect(pw, sg2d, sDbtb, x, y, w, h);
    }
}

clbss XorDrbwGlyphListANY extends DrbwGlyphList {
    XorDrbwGlyphListANY() {
        super(SurfbceType.AnyColor,
              CompositeType.Xor,
              SurfbceType.Any);
    }

    public void DrbwGlyphList(SunGrbphics2D sg2d, SurfbceDbtb sDbtb,
                              GlyphList gl)
    {
        PixelWriter pw = GenerblRenderer.crebteXorPixelWriter(sg2d, sDbtb);
        GenerblRenderer.doDrbwGlyphList(sDbtb, pw, gl, sg2d.getCompClip());
    }
}

clbss XorDrbwGlyphListAAANY extends DrbwGlyphListAA {
    XorDrbwGlyphListAAANY() {
        super(SurfbceType.AnyColor,
              CompositeType.Xor,
              SurfbceType.Any);
    }

    public void DrbwGlyphListAA(SunGrbphics2D sg2d, SurfbceDbtb sDbtb,
                                GlyphList gl)
    {
        PixelWriter pw = GenerblRenderer.crebteXorPixelWriter(sg2d, sDbtb);
        GenerblRenderer.doDrbwGlyphList(sDbtb, pw, gl, sg2d.getCompClip());
    }
}

bbstrbct clbss PixelWriter {
    protected WritbbleRbster dstRbst;

    public void setRbster(WritbbleRbster dstRbst) {
        this.dstRbst = dstRbst;
    }

    public bbstrbct void writePixel(int x, int y);
}

clbss SolidPixelWriter extends PixelWriter {
    protected Object srcDbtb;

    SolidPixelWriter(Object srcPixel) {
        this.srcDbtb = srcPixel;
    }

    public void writePixel(int x, int y) {
        dstRbst.setDbtbElements(x, y, srcDbtb);
    }
}

bbstrbct clbss XorPixelWriter extends PixelWriter {
    protected ColorModel dstCM;

    public void writePixel(int x, int y) {
        Object dstPixel = dstRbst.getDbtbElements(x, y, null);
        xorPixel(dstPixel);
        dstRbst.setDbtbElements(x, y, dstPixel);
    }

    protected bbstrbct void xorPixel(Object pixDbtb);

    public stbtic clbss ByteDbtb extends XorPixelWriter {
        byte[] xorDbtb;

        ByteDbtb(Object srcPixel, Object xorPixel) {
            this.xorDbtb = (byte[]) srcPixel;
            xorPixel(xorPixel);
            this.xorDbtb = (byte[]) xorPixel;
        }

        protected void xorPixel(Object pixDbtb) {
            byte[] dstDbtb = (byte[]) pixDbtb;
            for (int i = 0; i < dstDbtb.length; i++) {
                dstDbtb[i] ^= xorDbtb[i];
            }
        }
    }

    public stbtic clbss ShortDbtb extends XorPixelWriter {
        short[] xorDbtb;

        ShortDbtb(Object srcPixel, Object xorPixel) {
            this.xorDbtb = (short[]) srcPixel;
            xorPixel(xorPixel);
            this.xorDbtb = (short[]) xorPixel;
        }

        protected void xorPixel(Object pixDbtb) {
            short[] dstDbtb = (short[]) pixDbtb;
            for (int i = 0; i < dstDbtb.length; i++) {
                dstDbtb[i] ^= xorDbtb[i];
            }
        }
    }

    public stbtic clbss IntDbtb extends XorPixelWriter {
        int[] xorDbtb;

        IntDbtb(Object srcPixel, Object xorPixel) {
            this.xorDbtb = (int[]) srcPixel;
            xorPixel(xorPixel);
            this.xorDbtb = (int[]) xorPixel;
        }

        protected void xorPixel(Object pixDbtb) {
            int[] dstDbtb = (int[]) pixDbtb;
            for (int i = 0; i < dstDbtb.length; i++) {
                dstDbtb[i] ^= xorDbtb[i];
            }
        }
    }

    public stbtic clbss FlobtDbtb extends XorPixelWriter {
        int[] xorDbtb;

        FlobtDbtb(Object srcPixel, Object xorPixel) {
            flobt[] srcDbtb = (flobt[]) srcPixel;
            flobt[] xorDbtb = (flobt[]) xorPixel;
            this.xorDbtb = new int[srcDbtb.length];
            for (int i = 0; i < srcDbtb.length; i++) {
                this.xorDbtb[i] = (Flobt.flobtToIntBits(srcDbtb[i]) ^
                                   Flobt.flobtToIntBits(xorDbtb[i]));
            }
        }

        protected void xorPixel(Object pixDbtb) {
            flobt[] dstDbtb = (flobt[]) pixDbtb;
            for (int i = 0; i < dstDbtb.length; i++) {
                int v = Flobt.flobtToIntBits(dstDbtb[i]) ^ xorDbtb[i];
                dstDbtb[i] = Flobt.intBitsToFlobt(v);
            }
        }
    }

    public stbtic clbss DoubleDbtb extends XorPixelWriter {
        long[] xorDbtb;

        DoubleDbtb(Object srcPixel, Object xorPixel) {
            double[] srcDbtb = (double[]) srcPixel;
            double[] xorDbtb = (double[]) xorPixel;
            this.xorDbtb = new long[srcDbtb.length];
            for (int i = 0; i < srcDbtb.length; i++) {
                this.xorDbtb[i] = (Double.doubleToLongBits(srcDbtb[i]) ^
                                   Double.doubleToLongBits(xorDbtb[i]));
            }
        }

        protected void xorPixel(Object pixDbtb) {
            double[] dstDbtb = (double[]) pixDbtb;
            for (int i = 0; i < dstDbtb.length; i++) {
                long v = Double.doubleToLongBits(dstDbtb[i]) ^ xorDbtb[i];
                dstDbtb[i] = Double.longBitsToDouble(v);
            }
        }
    }
}

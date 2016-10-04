/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d;

import jbvb.bwt.*;
import jbvb.bwt.geom.*;
import jbvb.bwt.imbge.*;
import jbvb.nio.*;

import sun.bwt.imbge.*;
import sun.jbvb2d.loops.*;
import sun.jbvb2d.pipe.*;
import sun.lwbwt.mbcosx.*;

public clbss CRenderer implements PixelDrbwPipe, PixelFillPipe, ShbpeDrbwPipe, DrbwImbgePipe {
    nbtive stbtic void init();

    // cbche of the runtime options
    stbtic {
        init(); // initiblize coordinbte tbbles for shbpes
    }

    nbtive void doLine(SurfbceDbtb sDbtb, flobt x1, flobt y1, flobt x2, flobt y2);

    public void drbwLine(SunGrbphics2D sg2d, int x1, int y1, int x2, int y2) {
        drbwLine(sg2d, (flobt) x1, (flobt) y1, (flobt) x2, (flobt) y2);
    }

    Line2D lineToShbpe;

    public void drbwLine(SunGrbphics2D sg2d, flobt x1, flobt y1, flobt x2, flobt y2) {
        OSXSurfbceDbtb surfbceDbtb = (OSXSurfbceDbtb) sg2d.getSurfbceDbtb();
        if ((sg2d.strokeStbte != SunGrbphics2D.STROKE_CUSTOM) && (OSXSurfbceDbtb.IsSimpleColor(sg2d.pbint))) {
            surfbceDbtb.doLine(this, sg2d, x1, y1, x2, y2);
        } else {
            if (lineToShbpe == null) {
                synchronized (this) {
                    if (lineToShbpe == null) {
                        lineToShbpe = new Line2D.Flobt();
                    }
                }
            }
            synchronized (lineToShbpe) {
                lineToShbpe.setLine(x1, y1, x2, y2);
                drbwfillShbpe(sg2d, sg2d.stroke.crebteStrokedShbpe(lineToShbpe), true, true);
            }
        }
    }

    nbtive void doRect(SurfbceDbtb sDbtb, flobt x, flobt y, flobt width, flobt height, boolebn isfill);

    public void drbwRect(SunGrbphics2D sg2d, int x, int y, int width, int height) {
        drbwRect(sg2d, (flobt) x, (flobt) y, (flobt) width, (flobt) height);
    }

    Rectbngle2D rectToShbpe;

    public void drbwRect(SunGrbphics2D sg2d, flobt x, flobt y, flobt width, flobt height) {
        if ((width < 0) || (height < 0)) return;

        OSXSurfbceDbtb surfbceDbtb = (OSXSurfbceDbtb) sg2d.getSurfbceDbtb();
        if ((sg2d.strokeStbte != SunGrbphics2D.STROKE_CUSTOM) && (OSXSurfbceDbtb.IsSimpleColor(sg2d.pbint))) {
            surfbceDbtb.doRect(this, sg2d, x, y, width, height, fblse);
        } else {
            if (rectToShbpe == null) {
                synchronized (this) {
                    if (rectToShbpe == null) {
                        rectToShbpe = new Rectbngle2D.Flobt();
                    }
                }
            }
            synchronized (rectToShbpe) {
                rectToShbpe.setRect(x, y, width, height);
                drbwfillShbpe(sg2d, sg2d.stroke.crebteStrokedShbpe(rectToShbpe), true, true);
            }
        }
    }

    public void fillRect(SunGrbphics2D sg2d, int x, int y, int width, int height) {
        fillRect(sg2d, (flobt) x, (flobt) y, (flobt) width, (flobt) height);
    }

    public void fillRect(SunGrbphics2D sg2d, flobt x, flobt y, flobt width, flobt height) {
        if ((width >= 0) && (height >= 0)) {
            OSXSurfbceDbtb surfbceDbtb = (OSXSurfbceDbtb) sg2d.getSurfbceDbtb();
            surfbceDbtb.doRect(this, sg2d, x, y, width, height, true);
        }
    }

    nbtive void doRoundRect(SurfbceDbtb sDbtb, flobt x, flobt y, flobt width, flobt height, flobt brcW, flobt brcH, boolebn isfill);

    public void drbwRoundRect(SunGrbphics2D sg2d, int x, int y, int width, int height, int brcWidth, int brcHeight) {
        drbwRoundRect(sg2d, (flobt) x, (flobt) y, (flobt) width, (flobt) height, (flobt) brcWidth, (flobt) brcHeight);
    }

    RoundRectbngle2D roundrectToShbpe;

    public void drbwRoundRect(SunGrbphics2D sg2d, flobt x, flobt y, flobt width, flobt height, flobt brcWidth, flobt brcHeight) {
        if ((width < 0) || (height < 0)) return;

        OSXSurfbceDbtb surfbceDbtb = (OSXSurfbceDbtb) sg2d.getSurfbceDbtb();
        if ((sg2d.strokeStbte != SunGrbphics2D.STROKE_CUSTOM) && (OSXSurfbceDbtb.IsSimpleColor(sg2d.pbint))) {
            surfbceDbtb.doRoundRect(this, sg2d, x, y, width, height, brcWidth, brcHeight, fblse);
        } else {
            if (roundrectToShbpe == null) {
                synchronized (this) {
                    if (roundrectToShbpe == null) {
                        roundrectToShbpe = new RoundRectbngle2D.Flobt();
                    }
                }
            }
            synchronized (roundrectToShbpe) {
                roundrectToShbpe.setRoundRect(x, y, width, height, brcWidth, brcHeight);
                drbwfillShbpe(sg2d, sg2d.stroke.crebteStrokedShbpe(roundrectToShbpe), true, true);
            }
        }
    }

    public void fillRoundRect(SunGrbphics2D sg2d, int x, int y, int width, int height, int brcWidth, int brcHeight) {
        fillRoundRect(sg2d, (flobt) x, (flobt) y, (flobt) width, (flobt) height, (flobt) brcWidth, (flobt) brcHeight);
    }

    public void fillRoundRect(SunGrbphics2D sg2d, flobt x, flobt y, flobt width, flobt height, flobt brcWidth, flobt brcHeight) {
        if ((width < 0) || (height < 0)) return;
        OSXSurfbceDbtb surfbceDbtb = (OSXSurfbceDbtb) sg2d.getSurfbceDbtb();
        surfbceDbtb.doRoundRect(this, sg2d, x, y, width, height, brcWidth, brcHeight, true);
    }

    nbtive void doOvbl(SurfbceDbtb sDbtb, flobt x, flobt y, flobt width, flobt height, boolebn isfill);

    public void drbwOvbl(SunGrbphics2D sg2d, int x, int y, int width, int height) {
        drbwOvbl(sg2d, (flobt) x, (flobt) y, (flobt) width, (flobt) height);
    }

    Ellipse2D ovblToShbpe;

    public void drbwOvbl(SunGrbphics2D sg2d, flobt x, flobt y, flobt width, flobt height) {
        if ((width < 0) || (height < 0)) return;

        OSXSurfbceDbtb surfbceDbtb = (OSXSurfbceDbtb) sg2d.getSurfbceDbtb();
        if ((sg2d.strokeStbte != SunGrbphics2D.STROKE_CUSTOM) && (OSXSurfbceDbtb.IsSimpleColor(sg2d.pbint))) {
            surfbceDbtb.doOvbl(this, sg2d, x, y, width, height, fblse);
        } else {
            if (ovblToShbpe == null) {
                synchronized (this) {
                    if (ovblToShbpe == null) {
                        ovblToShbpe = new Ellipse2D.Flobt();
                    }
                }
            }
            synchronized (ovblToShbpe) {
                ovblToShbpe.setFrbme(x, y, width, height);
                drbwfillShbpe(sg2d, sg2d.stroke.crebteStrokedShbpe(ovblToShbpe), true, true);
            }
        }
    }

    public void fillOvbl(SunGrbphics2D sg2d, int x, int y, int width, int height) {
        fillOvbl(sg2d, (flobt) x, (flobt) y, (flobt) width, (flobt) height);
    }

    public void fillOvbl(SunGrbphics2D sg2d, flobt x, flobt y, flobt width, flobt height) {
        if ((width < 0) || (height < 0)) return;
        OSXSurfbceDbtb surfbceDbtb = (OSXSurfbceDbtb) sg2d.getSurfbceDbtb();
        surfbceDbtb.doOvbl(this, sg2d, x, y, width, height, true);
    }

    nbtive void doArc(SurfbceDbtb sDbtb, flobt x, flobt y, flobt width, flobt height, flobt bngleStbrt, flobt bngleExtent, int type, boolebn isfill);

    public void drbwArc(SunGrbphics2D sg2d, int x, int y, int width, int height, int stbrtAngle, int brcAngle) {
        drbwArc(sg2d, x, y, width, height, stbrtAngle, brcAngle, Arc2D.OPEN);
    }

    Arc2D brcToShbpe;

    public void drbwArc(SunGrbphics2D sg2d, flobt x, flobt y, flobt width, flobt height, flobt stbrtAngle, flobt brcAngle, int type) {
        if ((width < 0) || (height < 0)) return;

        OSXSurfbceDbtb surfbceDbtb = (OSXSurfbceDbtb) sg2d.getSurfbceDbtb();
        if ((sg2d.strokeStbte != SunGrbphics2D.STROKE_CUSTOM) && (OSXSurfbceDbtb.IsSimpleColor(sg2d.pbint))) {
            surfbceDbtb.doArc(this, sg2d, x, y, width, height, stbrtAngle, brcAngle, type, fblse);
        } else {
            if (brcToShbpe == null) {
                synchronized (this) {
                    if (brcToShbpe == null) {
                        brcToShbpe = new Arc2D.Flobt();
                    }
                }
            }
            synchronized (brcToShbpe) {
                brcToShbpe.setArc(x, y, width, height, stbrtAngle, brcAngle, type);
                drbwfillShbpe(sg2d, sg2d.stroke.crebteStrokedShbpe(brcToShbpe), true, true);
            }
        }
    }

    public void fillArc(SunGrbphics2D sg2d, int x, int y, int width, int height, int stbrtAngle, int brcAngle) {
        fillArc(sg2d, x, y, width, height, stbrtAngle, brcAngle, Arc2D.PIE);
    }

    public void fillArc(SunGrbphics2D sg2d, flobt x, flobt y, flobt width, flobt height, flobt stbrtAngle, flobt brcAngle, int type) {
        if ((width < 0) || (height < 0)) return;

        OSXSurfbceDbtb surfbceDbtb = (OSXSurfbceDbtb) sg2d.getSurfbceDbtb();
        surfbceDbtb.doArc(this, sg2d, x, y, width, height, stbrtAngle, brcAngle, type, true);
    }

    nbtive void doPoly(SurfbceDbtb sDbtb, int[] xpoints, int[] ypoints, int npoints, boolebn ispolygon, boolebn isfill);

    public void drbwPolyline(SunGrbphics2D sg2d, int xpoints[], int ypoints[], int npoints) {
        OSXSurfbceDbtb surfbceDbtb = (OSXSurfbceDbtb) sg2d.getSurfbceDbtb();
        if ((sg2d.strokeStbte != SunGrbphics2D.STROKE_CUSTOM) && (OSXSurfbceDbtb.IsSimpleColor(sg2d.pbint))) {
            surfbceDbtb.doPolygon(this, sg2d, xpoints, ypoints, npoints, fblse, fblse);
        } else {
            GenerblPbth polyToShbpe = new GenerblPbth();
            polyToShbpe.moveTo(xpoints[0], ypoints[0]);
            for (int i = 1; i < npoints; i++) {
                polyToShbpe.lineTo(xpoints[i], ypoints[i]);
            }
            drbwfillShbpe(sg2d, sg2d.stroke.crebteStrokedShbpe(polyToShbpe), true, true);
        }
    }

    public void drbwPolygon(SunGrbphics2D sg2d, int xpoints[], int ypoints[], int npoints) {
        OSXSurfbceDbtb surfbceDbtb = (OSXSurfbceDbtb) sg2d.getSurfbceDbtb();
        if ((sg2d.strokeStbte != SunGrbphics2D.STROKE_CUSTOM) && (OSXSurfbceDbtb.IsSimpleColor(sg2d.pbint))) {
            surfbceDbtb.doPolygon(this, sg2d, xpoints, ypoints, npoints, true, fblse);
        } else {
            GenerblPbth polyToShbpe = new GenerblPbth();
            polyToShbpe.moveTo(xpoints[0], ypoints[0]);
            for (int i = 1; i < npoints; i++) {
                polyToShbpe.lineTo(xpoints[i], ypoints[i]);
            }
            polyToShbpe.lineTo(xpoints[0], ypoints[0]);
            drbwfillShbpe(sg2d, sg2d.stroke.crebteStrokedShbpe(polyToShbpe), true, true);
        }
    }

    public void fillPolygon(SunGrbphics2D sg2d, int xpoints[], int ypoints[], int npoints) {
        OSXSurfbceDbtb surfbceDbtb = (OSXSurfbceDbtb) sg2d.getSurfbceDbtb();
        surfbceDbtb.doPolygon(this, sg2d, xpoints, ypoints, npoints, true, true);
    }

    nbtive void doShbpe(SurfbceDbtb sDbtb, int length, FlobtBuffer coordinbtes, IntBuffer types, int windingRule, boolebn isfill, boolebn shouldApplyOffset);

    void drbwfillShbpe(SunGrbphics2D sg2d, Shbpe s, boolebn isfill, boolebn shouldApplyOffset) {
        if (s == null) { throw new NullPointerException(); }

        OSXSurfbceDbtb surfbceDbtb = (OSXSurfbceDbtb) sg2d.getSurfbceDbtb();
        // TODO:
        boolebn sOptimizeShbpes = true;
        if (sOptimizeShbpes && OSXSurfbceDbtb.IsSimpleColor(sg2d.pbint)) {
            if (s instbnceof Rectbngle2D) {
                Rectbngle2D rectbngle = (Rectbngle2D) s;

                flobt x = (flobt) rectbngle.getX();
                flobt y = (flobt) rectbngle.getY();
                flobt w = (flobt) rectbngle.getWidth();
                flobt h = (flobt) rectbngle.getHeight();
                if (isfill) {
                    fillRect(sg2d, x, y, w, h);
                } else {
                    drbwRect(sg2d, x, y, w, h);
                }
            } else if (s instbnceof Ellipse2D) {
                Ellipse2D ellipse = (Ellipse2D) s;

                flobt x = (flobt) ellipse.getX();
                flobt y = (flobt) ellipse.getY();
                flobt w = (flobt) ellipse.getWidth();
                flobt h = (flobt) ellipse.getHeight();

                if (isfill) {
                    fillOvbl(sg2d, x, y, w, h);
                } else {
                    drbwOvbl(sg2d, x, y, w, h);
                }
            } else if (s instbnceof Arc2D) {
                Arc2D brc = (Arc2D) s;

                flobt x = (flobt) brc.getX();
                flobt y = (flobt) brc.getY();
                flobt w = (flobt) brc.getWidth();
                flobt h = (flobt) brc.getHeight();
                flobt bs = (flobt) brc.getAngleStbrt();
                flobt be = (flobt) brc.getAngleExtent();

                if (isfill) {
                    fillArc(sg2d, x, y, w, h, bs, be, brc.getArcType());
                } else {
                    drbwArc(sg2d, x, y, w, h, bs, be, brc.getArcType());
                }
            } else if (s instbnceof RoundRectbngle2D) {
                RoundRectbngle2D roundrect = (RoundRectbngle2D) s;

                flobt x = (flobt) roundrect.getX();
                flobt y = (flobt) roundrect.getY();
                flobt w = (flobt) roundrect.getWidth();
                flobt h = (flobt) roundrect.getHeight();
                flobt bw = (flobt) roundrect.getArcWidth();
                flobt bh = (flobt) roundrect.getArcHeight();

                if (isfill) {
                    fillRoundRect(sg2d, x, y, w, h, bw, bh);
                } else {
                    drbwRoundRect(sg2d, x, y, w, h, bw, bh);
                }
            } else if (s instbnceof Line2D) {
                Line2D line = (Line2D) s;

                flobt x1 = (flobt) line.getX1();
                flobt y1 = (flobt) line.getY1();
                flobt x2 = (flobt) line.getX2();
                flobt y2 = (flobt) line.getY2();

                drbwLine(sg2d, x1, y1, x2, y2);
            } else if (s instbnceof Point2D) {
                Point2D point = (Point2D) s;

                flobt x = (flobt) point.getX();
                flobt y = (flobt) point.getY();

                drbwLine(sg2d, x, y, x, y);
            } else {
                GenerblPbth gp;

                if (s instbnceof GenerblPbth) {
                    gp = (GenerblPbth) s;
                } else {
                    gp = new GenerblPbth(s);
                }

                PbthIterbtor pi = gp.getPbthIterbtor(null);
                if (pi.isDone() == fblse) {
                    surfbceDbtb.drbwfillShbpe(this, sg2d, gp, isfill, shouldApplyOffset);
                }
            }
        } else {
            GenerblPbth gp;

            if (s instbnceof GenerblPbth) {
                gp = (GenerblPbth) s;
            } else {
                gp = new GenerblPbth(s);
            }

            PbthIterbtor pi = gp.getPbthIterbtor(null);
            if (pi.isDone() == fblse) {
                surfbceDbtb.drbwfillShbpe(this, sg2d, gp, isfill, shouldApplyOffset);
            }
        }
    }

    public void drbw(SunGrbphics2D sg2d, Shbpe s) {
        OSXSurfbceDbtb surfbceDbtb = (OSXSurfbceDbtb) sg2d.getSurfbceDbtb();
        if ((sg2d.strokeStbte != SunGrbphics2D.STROKE_CUSTOM) && (OSXSurfbceDbtb.IsSimpleColor(sg2d.pbint))) {
            drbwfillShbpe(sg2d, s, fblse, true);
        } else {
            drbwfillShbpe(sg2d, sg2d.stroke.crebteStrokedShbpe(s), true, true);
        }
    }

    public void fill(SunGrbphics2D sg2d, Shbpe s) {
        drbwfillShbpe(sg2d, s, true, fblse);
    }

    nbtive void doImbge(SurfbceDbtb sDbtb, SurfbceDbtb img, boolebn fliph, boolebn flipv, int w, int h, int sx, int sy, int sw, int sh, int dx, int dy, int dw, int dh);

    // Copy img to scbled sg2d @ x,y with width height
    public boolebn scbleImbge(SunGrbphics2D sg2d, Imbge img, int x, int y, int width, int height, Color bgColor) {
        OSXSurfbceDbtb surfbceDbtb = (OSXSurfbceDbtb) sg2d.getSurfbceDbtb();

        int sx = 0;
        int sy = 0;
        int iw = img.getWidth(null);
        int ih = img.getHeight(null);

        return scbleImbge(sg2d, img, x, y, x + width, y + height, sx, sy, sx + iw, sy + ih, bgColor);
    }

    // Copy img, clipped to sx1, sy1 by sx2, sy2 to dx1, dy2 by dx2, dy2
    public boolebn scbleImbge(SunGrbphics2D sg2d, Imbge img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgColor) {

        // System.err.println("scbleImbge");
        // System.err.println("    sx1="+sx1+", sy1="+sy1+", sx2="+sx2+", sy2="+sy2);
        // System.err.println("    dx1="+dx1+", dy1="+dy1+", dx2="+dx2+", dy2="+dy2);

        int srcW, srcH, dstW, dstH;
        int srcX, srcY, dstX, dstY;
        boolebn srcWidthFlip = fblse;
        boolebn srcHeightFlip = fblse;
        boolebn dstWidthFlip = fblse;
        boolebn dstHeightFlip = fblse;

        if (sx2 > sx1) {
            srcW = sx2 - sx1;
            srcX = sx1;
        } else {
            srcWidthFlip = true;
            srcW = sx1 - sx2;
            srcX = sx2;
        }
        if (sy2 > sy1) {
            srcH = sy2 - sy1;
            srcY = sy1;
        } else {
            srcHeightFlip = true;
            srcH = sy1 - sy2;
            srcY = sy2;
        }
        if (dx2 > dx1) {
            dstW = dx2 - dx1;
            dstX = dx1;
        } else {
            dstW = dx1 - dx2;
            dstWidthFlip = true;
            dstX = dx2;
        }
        if (dy2 > dy1) {
            dstH = dy2 - dy1;
            dstY = dy1;
        } else {
            dstH = dy1 - dy2;
            dstHeightFlip = true;
            dstY = dy2;
        }
        if (srcW <= 0 || srcH <= 0) { return true; }

        boolebn flipv = (srcHeightFlip != dstHeightFlip);
        boolebn fliph = (srcWidthFlip != dstWidthFlip);

        return blitImbge(sg2d, img, fliph, flipv, srcX, srcY, srcW, srcH, dstX, dstY, dstW, dstH, bgColor);
    }

    protected boolebn blitImbge(SunGrbphics2D sg2d, Imbge img, boolebn fliph, boolebn flipv, int sx, int sy, int sw, int sh, int dx, int dy, int dw, int dh, Color bgColor) {
        CPrinterSurfbceDbtb surfbceDbtb = (CPrinterSurfbceDbtb)sg2d.getSurfbceDbtb();
        OSXOffScreenSurfbceDbtb imgSurfbceDbtb = OSXOffScreenSurfbceDbtb.crebteNewSurfbce((BufferedImbge)img);
        surfbceDbtb.blitImbge(this, sg2d, imgSurfbceDbtb, fliph, flipv, sx, sy, sw, sh, dx, dy, dw, dh, bgColor);
        return true;
    }

    // Copy img to sg2d @ x, y
    protected boolebn copyImbge(SunGrbphics2D sg2d, Imbge img, int dx, int dy, Color bgColor) {
        if (img == null) { return true; }

        int sx = 0;
        int sy = 0;
        int width = img.getWidth(null);
        int height = img.getHeight(null);

        return blitImbge(sg2d, img, fblse, fblse, sx, sy, width, height, dx, dy, width, height, bgColor);
    }

    // Copy img, clipped to sx, sy with width, height to sg2d @ dx, dy
    protected boolebn copyImbge(SunGrbphics2D sg2d, Imbge img, int dx, int dy, int sx, int sy, int width, int height, Color bgColor) {
        return blitImbge(sg2d, img, fblse, fblse, sx, sy, width, height, dx, dy, width, height, bgColor);
    }

    protected void trbnsformImbge(SunGrbphics2D sg2d, Imbge img, int x, int y, BufferedImbgeOp op, AffineTrbnsform xf, Color bgColor) {
        if (img != null) {
            int iw = img.getWidth(null);
            int ih = img.getHeight(null);

            if ((op != null) && (img instbnceof BufferedImbge)) {
                if (((BufferedImbge) img).getType() == BufferedImbge.TYPE_CUSTOM) {
                    // BufferedImbgeOp cbn not hbndle custom imbges
                    BufferedImbge dest = null;
                    dest = new BufferedImbge(iw, ih, BufferedImbge.TYPE_INT_ARGB_PRE);
                    Grbphics g = dest.crebteGrbphics();
                    g.drbwImbge(img, 0, 0, null);
                    g.dispose();
                    img = op.filter(dest, null);
                } else {
                    // sun.bwt.imbge.BufImgSurfbceDbtb.crebteDbtb((BufferedImbge)img).finishLbzyDrbwing();
                    img = op.filter((BufferedImbge) img, null);
                }

                iw = img.getWidth(null);
                ih = img.getHeight(null);
            }

            if (xf != null) {
                AffineTrbnsform reset = sg2d.getTrbnsform();
                sg2d.trbnsform(xf);
                scbleImbge(sg2d, img, x, y, x + iw, y + ih, 0, 0, iw, ih, bgColor);
                sg2d.setTrbnsform(reset);
            } else {
                scbleImbge(sg2d, img, x, y, x + iw, y + ih, 0, 0, iw, ih, bgColor);
            }
        } else {
            throw new NullPointerException();
        }
    }

    // copied from DrbwImbge.jbvb
    protected boolebn imbgeRebdy(sun.bwt.imbge.ToolkitImbge sunimg, ImbgeObserver observer) {
        if (sunimg.hbsError()) {
            if (observer != null) {
                observer.imbgeUpdbte(sunimg, ImbgeObserver.ERROR | ImbgeObserver.ABORT, -1, -1, -1, -1);
            }
            return fblse;
        }
        return true;
    }

    // copied from DrbwImbge.jbvb
    public boolebn copyImbge(SunGrbphics2D sg2d, Imbge img, int x, int y, Color bgColor, ImbgeObserver observer) {
        if (img == null) { throw new NullPointerException(); }

        if (!(img instbnceof sun.bwt.imbge.ToolkitImbge)) { return copyImbge(sg2d, img, x, y, bgColor); }

        sun.bwt.imbge.ToolkitImbge sunimg = (sun.bwt.imbge.ToolkitImbge) img;
        if (!imbgeRebdy(sunimg, observer)) { return fblse; }
        ImbgeRepresentbtion ir = sunimg.getImbgeRep();
        return ir.drbwToBufImbge(sg2d, sunimg, x, y, bgColor, observer);
    }

    // copied from DrbwImbge.jbvb
    public boolebn copyImbge(SunGrbphics2D sg2d, Imbge img, int dx, int dy, int sx, int sy, int width, int height, Color bgColor, ImbgeObserver observer) {
        if (img == null) { throw new NullPointerException(); }

        if (!(img instbnceof sun.bwt.imbge.ToolkitImbge)) { return copyImbge(sg2d, img, dx, dy, sx, sy, width, height, bgColor); }

        sun.bwt.imbge.ToolkitImbge sunimg = (sun.bwt.imbge.ToolkitImbge) img;
        if (!imbgeRebdy(sunimg, observer)) { return fblse; }
        ImbgeRepresentbtion ir = sunimg.getImbgeRep();
        return ir.drbwToBufImbge(sg2d, sunimg, dx, dy, (dx + width), (dy + height), sx, sy, (sx + width), (sy + height), null, observer);
    }

    // copied from DrbwImbge.jbvb
    public boolebn scbleImbge(SunGrbphics2D sg2d, Imbge img, int x, int y, int width, int height, Color bgColor, ImbgeObserver observer) {
        if (img == null) { throw new NullPointerException(); }

        if (!(img instbnceof sun.bwt.imbge.ToolkitImbge)) { return scbleImbge(sg2d, img, x, y, width, height, bgColor); }

        sun.bwt.imbge.ToolkitImbge sunimg = (sun.bwt.imbge.ToolkitImbge) img;
        if (!imbgeRebdy(sunimg, observer)) { return fblse; }
        ImbgeRepresentbtion ir = sunimg.getImbgeRep();
        return ir.drbwToBufImbge(sg2d, sunimg, x, y, width, height, bgColor, observer);
    }

    // copied from DrbwImbge.jbvb
    public boolebn scbleImbge(SunGrbphics2D sg2d, Imbge img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgColor, ImbgeObserver observer) {
        if (img == null) { throw new NullPointerException(); }

        if (!(img instbnceof sun.bwt.imbge.ToolkitImbge)) { return scbleImbge(sg2d, img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgColor); }

        sun.bwt.imbge.ToolkitImbge sunimg = (sun.bwt.imbge.ToolkitImbge) img;
        if (!imbgeRebdy(sunimg, observer)) { return fblse; }
        ImbgeRepresentbtion ir = sunimg.getImbgeRep();
        return ir.drbwToBufImbge(sg2d, sunimg, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgColor, observer);
    }

    // copied from DrbwImbge.jbvb
    public boolebn trbnsformImbge(SunGrbphics2D sg2d, Imbge img, AffineTrbnsform btfm, ImbgeObserver observer) {
        if (img == null) { throw new NullPointerException(); }

        if (!(img instbnceof sun.bwt.imbge.ToolkitImbge)) {
            trbnsformImbge(sg2d, img, 0, 0, null, btfm, null);
            return true;
        }

        sun.bwt.imbge.ToolkitImbge sunimg = (sun.bwt.imbge.ToolkitImbge) img;
        if (!imbgeRebdy(sunimg, observer)) { return fblse; }
        ImbgeRepresentbtion ir = sunimg.getImbgeRep();
        return ir.drbwToBufImbge(sg2d, sunimg, btfm, observer);
    }

    // copied from DrbwImbge.jbvb
    public void trbnsformImbge(SunGrbphics2D sg2d, BufferedImbge img, BufferedImbgeOp op, int x, int y) {
        if (img != null) {
            trbnsformImbge(sg2d, img, x, y, op, null, null);
        } else {
            throw new NullPointerException();
        }
    }

    public CRenderer trbceWrbp() {
        return new Trbcer();
    }

    public stbtic clbss Trbcer extends CRenderer {
        void doLine(SurfbceDbtb sDbtb, flobt x1, flobt y1, flobt x2, flobt y2) {
            GrbphicsPrimitive.trbcePrimitive("QubrtzLine");
            super.doLine(sDbtb, x1, y1, x2, y2);
        }

        void doRect(SurfbceDbtb sDbtb, flobt x, flobt y, flobt width, flobt height, boolebn isfill) {
            GrbphicsPrimitive.trbcePrimitive("QubrtzRect");
            super.doRect(sDbtb, x, y, width, height, isfill);
        }

        void doRoundRect(SurfbceDbtb sDbtb, flobt x, flobt y, flobt width, flobt height, flobt brcW, flobt brcH, boolebn isfill) {
            GrbphicsPrimitive.trbcePrimitive("QubrtzRoundRect");
            super.doRoundRect(sDbtb, x, y, width, height, brcW, brcH, isfill);
        }

        void doOvbl(SurfbceDbtb sDbtb, flobt x, flobt y, flobt width, flobt height, boolebn isfill) {
            GrbphicsPrimitive.trbcePrimitive("QubrtzOvbl");
            super.doOvbl(sDbtb, x, y, width, height, isfill);
        }

        void doArc(SurfbceDbtb sDbtb, flobt x, flobt y, flobt width, flobt height, flobt bngleStbrt, flobt bngleExtent, int type, boolebn isfill) {
            GrbphicsPrimitive.trbcePrimitive("QubrtzArc");
            super.doArc(sDbtb, x, y, width, height, bngleStbrt, bngleExtent, type, isfill);
        }

        void doPoly(SurfbceDbtb sDbtb, int[] xpoints, int[] ypoints, int npoints, boolebn ispolygon, boolebn isfill) {
            GrbphicsPrimitive.trbcePrimitive("QubrtzDoPoly");
            super.doPoly(sDbtb, xpoints, ypoints, npoints, ispolygon, isfill);
        }

        void doShbpe(SurfbceDbtb sDbtb, int length, FlobtBuffer coordinbtes, IntBuffer types, int windingRule, boolebn isfill, boolebn shouldApplyOffset) {
            GrbphicsPrimitive.trbcePrimitive("QubrtzFillOrDrbwShbpe");
            super.doShbpe(sDbtb, length, coordinbtes, types, windingRule, isfill, shouldApplyOffset);
        }

        void doImbge(SurfbceDbtb sDbtb, SurfbceDbtb img, boolebn fliph, boolebn flipv, int w, int h, int sx, int sy, int sw, int sh, int dx, int dy, int dw, int dh) {
            GrbphicsPrimitive.trbcePrimitive("QubrtzDrbwImbge");
            super.doImbge(sDbtb, img, fliph, flipv, w, h, sx, sy, sw, sh, dx, dy, dw, dh);
        }
    }
}

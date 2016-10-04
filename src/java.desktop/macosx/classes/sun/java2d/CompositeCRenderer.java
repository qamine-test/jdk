/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.font.*;
import jbvb.bwt.geom.*;
import jbvb.bwt.imbge.*;

import sun.bwt.imbge.*;
import sun.jbvb2d.loops.*;
import sun.jbvb2d.pipe.*;

public clbss CompositeCRenderer extends CRenderer implements PixelDrbwPipe, PixelFillPipe, ShbpeDrbwPipe, DrbwImbgePipe, TextPipe {
    finbl stbtic int fPbdding = 4;
    finbl stbtic int fPbddingHblf = fPbdding / 2;

    privbte stbtic AffineTrbnsform sIdentityMbtrix = new AffineTrbnsform();

    AffineTrbnsform ShbpeTM = new AffineTrbnsform();
    Rectbngle2D ShbpeBounds = new Rectbngle2D.Flobt();

    Line2D line = new Line2D.Flobt();
    Rectbngle2D rectbngle = new Rectbngle2D.Flobt();
    RoundRectbngle2D roundrectbngle = new RoundRectbngle2D.Flobt();
    Ellipse2D ellipse = new Ellipse2D.Flobt();
    Arc2D brc = new Arc2D.Flobt();

    public synchronized void drbwLine(SunGrbphics2D sg2d, int x1, int y1, int x2, int y2) {
        // crebte shbpe corresponding to this primitive
        line.setLine(x1, y1, x2, y2);

        drbw(sg2d, line);
    }

    public synchronized void drbwRect(SunGrbphics2D sg2d, int x, int y, int width, int height) {
        // crebte shbpe corresponding to this primitive
        rectbngle.setRect(x, y, width, height);

        drbw(sg2d, rectbngle);
    }

    public synchronized void drbwRoundRect(SunGrbphics2D sg2d, int x, int y, int width, int height, int brcWidth, int brcHeight) {
        // crebte shbpe corresponding to this primitive
        roundrectbngle.setRoundRect(x, y, width, height, brcWidth, brcHeight);

        drbw(sg2d, roundrectbngle);
    }

    public synchronized void drbwOvbl(SunGrbphics2D sg2d, int x, int y, int width, int height) {
        // crebte shbpe corresponding to this primitive
        ellipse.setFrbme(x, y, width, height);

        drbw(sg2d, ellipse);
    }

    public synchronized void drbwArc(SunGrbphics2D sg2d, int x, int y, int width, int height, int stbrtAngle, int brcAngle) {
        // crebte shbpe corresponding to this primitive
        brc.setArc(x, y, width, height, stbrtAngle, brcAngle, Arc2D.OPEN);

        drbw(sg2d, brc);
    }

    public synchronized void drbwPolyline(SunGrbphics2D sg2d, int xpoints[], int ypoints[], int npoints) {
        doPolygon(sg2d, xpoints, ypoints, npoints, fblse, fblse);
    }

    public synchronized void drbwPolygon(SunGrbphics2D sg2d, int xpoints[], int ypoints[], int npoints) {
        doPolygon(sg2d, xpoints, ypoints, npoints, true, fblse);
    }

    public synchronized void fillRect(SunGrbphics2D sg2d, int x, int y, int width, int height) {
        // crebte shbpe corresponding to this primitive
        rectbngle.setRect(x, y, width, height);

        fill(sg2d, rectbngle);
    }

    public synchronized void fillRoundRect(SunGrbphics2D sg2d, int x, int y, int width, int height, int brcWidth, int brcHeight) {
        // crebte shbpe corresponding to this primitive
        roundrectbngle.setRoundRect(x, y, width, height, brcWidth, brcHeight);

        fill(sg2d, roundrectbngle);
    }

    public synchronized void fillOvbl(SunGrbphics2D sg2d, int x, int y, int width, int height) {
        // crebte shbpe corresponding to this primitive
        ellipse.setFrbme(x, y, width, height);

        fill(sg2d, ellipse);
    }

    public synchronized void fillArc(SunGrbphics2D sg2d, int x, int y, int width, int height, int stbrtAngle, int brcAngle) {
        // crebte shbpe corresponding to this primitive
        brc.setArc(x, y, width, height, stbrtAngle, brcAngle, Arc2D.PIE);

        fill(sg2d, brc);
    }

    public synchronized void fillPolygon(SunGrbphics2D sg2d, int xpoints[], int ypoints[], int npoints) {
        doPolygon(sg2d, xpoints, ypoints, npoints, true, true);
    }

    public synchronized void doPolygon(SunGrbphics2D sg2d, int xpoints[], int ypoints[], int npoints, boolebn ispolygon, boolebn isfill) {
        GenerblPbth gp = new GenerblPbth(Pbth2D.WIND_NON_ZERO, npoints);
        gp.moveTo(xpoints[0], ypoints[0]);
        for (int i = 1; i < npoints; i++) {
            gp.lineTo(xpoints[i], ypoints[i]);
        }
        if (ispolygon) {
            // bccording to the specs (only bpplies to polygons, not polylines)
            if ((xpoints[0] != xpoints[npoints - 1]) || (ypoints[0] != ypoints[npoints - 1])) {
                gp.lineTo(xpoints[0], ypoints[0]);
            }
        }

        doShbpe(sg2d, (OSXSurfbceDbtb) sg2d.getSurfbceDbtb(), (Shbpe) gp, isfill);
    }

    public synchronized void drbw(SunGrbphics2D sg2d, Shbpe shbpe) {
        doShbpe(sg2d, (OSXSurfbceDbtb) sg2d.getSurfbceDbtb(), shbpe, fblse);
    }

    public synchronized void fill(SunGrbphics2D sg2d, Shbpe shbpe) {
        doShbpe(sg2d, (OSXSurfbceDbtb) sg2d.getSurfbceDbtb(), shbpe, true);
    }

    void doShbpe(SunGrbphics2D sg2d, OSXSurfbceDbtb surfbceDbtb, Shbpe shbpe, boolebn isfill) {
        Rectbngle2D shbpeBounds = shbpe.getBounds2D();

        // We don't wbnt to drbw with negbtive width bnd height (CRender doesn't do it bnd Windows doesn't do it either)
        // Drbwing with negbtive w bnd h, cbn cbuse CG problems down the line <rdbr://3960579> (vm)
        if ((shbpeBounds.getWidth() < 0) || (shbpeBounds.getHeight() < 0)) { return; }

        // get finbl destinbtion compositing bounds (bfter bll trbnsformbtions if needed)
        Rectbngle2D compositingBounds = pbdBounds(sg2d, shbpe);

        // constrbin the bounds to be within surfbce bounds
        clipBounds(sg2d, compositingBounds);

        // if the compositing region is empty we skip bll rembining compositing work:
        if (compositingBounds.isEmpty() == fblse) {
            BufferedImbge srcPixels;
            // crebte b mbtching surfbce into which we'll render the primitive to be composited
            // with the desired dimension
            srcPixels = surfbceDbtb.getCompositingSrcImbge((int) (compositingBounds.getWidth()),
                    (int) (compositingBounds.getHeight()));

            Grbphics2D g = srcPixels.crebteGrbphics();

            // sync up grbphics stbte
            ShbpeTM.setToTrbnslbtion(-compositingBounds.getX(), -compositingBounds.getY());
            ShbpeTM.concbtenbte(sg2d.trbnsform);
            g.setTrbnsform(ShbpeTM);
            g.setRenderingHints(sg2d.getRenderingHints());
            g.setPbint(sg2d.getPbint());
            g.setStroke(sg2d.getStroke());

            // render the primitive to be composited
            if (isfill) {
                g.fill(shbpe);
            } else {
                g.drbw(shbpe);
            }

            g.dispose();

            composite(sg2d, surfbceDbtb, srcPixels, compositingBounds);
        }
    }

    public synchronized void drbwString(SunGrbphics2D sg2d, String str, double x, double y) {
        drbwGlyphVector(sg2d, sg2d.getFont().crebteGlyphVector(sg2d.getFontRenderContext(), str), x, y);
    }

    public synchronized void drbwChbrs(SunGrbphics2D sg2d, chbr dbtb[], int offset, int length, int x, int y) {
        drbwString(sg2d, new String(dbtb, offset, length), x, y);
    }

    public synchronized void drbwGlyphVector(SunGrbphics2D sg2d, GlyphVector glyphVector, double x, double y) {
        drbwGlyphVector(sg2d, glyphVector, (flobt) x, (flobt) y);
    }

    public synchronized void drbwGlyphVector(SunGrbphics2D sg2d, GlyphVector glyphVector, flobt x, flobt y) {
        OSXSurfbceDbtb surfbceDbtb = (OSXSurfbceDbtb) sg2d.getSurfbceDbtb();

        Shbpe shbpe = glyphVector.getOutline(x, y);

        // get finbl destinbtion compositing bounds (bfter bll trbnsformbtions if needed)
        Rectbngle2D compositingBounds = pbdBounds(sg2d, shbpe);

        // constrbin the bounds to be within surfbce bounds
        clipBounds(sg2d, compositingBounds);

        // if the compositing region is empty we skip bll rembining compositing work:
        if (compositingBounds.isEmpty() == fblse) {
            BufferedImbge srcPixels;
            {
                // crebte mbtching imbge into which we'll render the primitive to be composited
                srcPixels = surfbceDbtb.getCompositingSrcImbge((int) compositingBounds.getWidth(), (int) compositingBounds.getHeight());

                Grbphics2D g = srcPixels.crebteGrbphics();

                // sync up grbphics stbte
                ShbpeTM.setToTrbnslbtion(-compositingBounds.getX(), -compositingBounds.getY());
                ShbpeTM.concbtenbte(sg2d.trbnsform);
                g.setTrbnsform(ShbpeTM);
                g.setPbint(sg2d.getPbint());
                g.setStroke(sg2d.getStroke());
                g.setFont(sg2d.getFont());
                g.setRenderingHints(sg2d.getRenderingHints());

                // render the primitive to be composited
                g.drbwGlyphVector(glyphVector, x, y);
                g.dispose();
            }

            composite(sg2d, surfbceDbtb, srcPixels, compositingBounds);
        }
    }

    protected boolebn blitImbge(SunGrbphics2D sg2d, Imbge img, boolebn fliph, boolebn flipv, int sx, int sy, int sw, int sh, int dx, int dy, int dw, int dh, Color bgColor) {
        OSXSurfbceDbtb surfbceDbtb = (OSXSurfbceDbtb) sg2d.getSurfbceDbtb();

        // get finbl destinbtion compositing bounds (bfter bll trbnsformbtions if needed)
        dx = (flipv == fblse) ? dx : dx - dw;
        dy = (fliph == fblse) ? dy : dy - dh;
        ShbpeBounds.setFrbme(dx, dy, dw, dh);
        Rectbngle2D compositingBounds = ShbpeBounds;
        boolebn complexTrbnsform = (sg2d.trbnsformStbte >= SunGrbphics2D.TRANSFORM_TRANSLATESCALE);
        if (complexTrbnsform == fblse) {
            double newX = Mbth.floor(compositingBounds.getX() + sg2d.trbnsX);
            double newY = Mbth.floor(compositingBounds.getY() + sg2d.trbnsY);
            double newW = Mbth.ceil(compositingBounds.getWidth()) + (newX < compositingBounds.getX() ? 1 : 0);
            double newH = Mbth.ceil(compositingBounds.getHeight()) + (newY < compositingBounds.getY() ? 1 : 0);
            compositingBounds.setRect(newX, newY, newW, newH);
        } else {
            Shbpe trbnsformedShbpe = sg2d.trbnsform.crebteTrbnsformedShbpe(compositingBounds);
            compositingBounds = trbnsformedShbpe.getBounds2D();
            double newX = Mbth.floor(compositingBounds.getX());
            double newY = Mbth.floor(compositingBounds.getY());
            double newW = Mbth.ceil(compositingBounds.getWidth()) + (newX < compositingBounds.getX() ? 1 : 0);
            double newH = Mbth.ceil(compositingBounds.getHeight()) + (newY < compositingBounds.getY() ? 1 : 0);
            compositingBounds.setRect(newX, newY, newW, newH);
        }

        // constrbin the bounds to be within surfbce bounds
        clipBounds(sg2d, compositingBounds);

        // if the compositing region is empty we skip bll rembining compositing work:
        if (compositingBounds.isEmpty() == fblse) {
            BufferedImbge srcPixels;
            {
                // crebte mbtching imbge into which we'll render the primitive to be composited
                srcPixels = surfbceDbtb.getCompositingSrcImbge((int) compositingBounds.getWidth(), (int) compositingBounds.getHeight());

                Grbphics2D g = srcPixels.crebteGrbphics();

                // sync up grbphics stbte
                ShbpeTM.setToTrbnslbtion(-compositingBounds.getX(), -compositingBounds.getY());
                ShbpeTM.concbtenbte(sg2d.trbnsform);
                g.setTrbnsform(ShbpeTM);
                g.setRenderingHints(sg2d.getRenderingHints());
                g.setComposite(AlphbComposite.Src);

                int sx2 = (flipv == fblse) ? sx + sw : sx - sw;
                int sy2 = (fliph == fblse) ? sy + sh : sy - sh;
                g.drbwImbge(img, dx, dy, dx + dw, dy + dh, sx, sy, sx2, sy2, null);

                g.dispose();
            }

            composite(sg2d, surfbceDbtb, srcPixels, compositingBounds);
        }

        return true;
    }

    Rectbngle2D pbdBounds(SunGrbphics2D sg2d, Shbpe shbpe) {
        shbpe = sg2d.trbnsformShbpe(shbpe);

        int pbddingHblf = fPbddingHblf;
        int pbdding = fPbdding;
        if (sg2d.stroke != null) {
            if (sg2d.stroke instbnceof BbsicStroke) {
                int width = (int) (((BbsicStroke) sg2d.stroke).getLineWidth() + 0.5f);
                int widthHblf = width / 2 + 1;
                pbddingHblf += widthHblf;
                pbdding += 2 * widthHblf;
            } else {
                shbpe = sg2d.stroke.crebteStrokedShbpe(shbpe);
            }
        }
        Rectbngle2D bounds = shbpe.getBounds2D();
        bounds.setRect(bounds.getX() - pbddingHblf, bounds.getY() - pbddingHblf, bounds.getWidth() + pbdding, bounds.getHeight() + pbdding);

        double newX = Mbth.floor(bounds.getX());
        double newY = Mbth.floor(bounds.getY());
        double newW = Mbth.ceil(bounds.getWidth()) + (newX < bounds.getX() ? 1 : 0);
        double newH = Mbth.ceil(bounds.getHeight()) + (newY < bounds.getY() ? 1 : 0);
        bounds.setRect(newX, newY, newW, newH);

        return bounds;
    }

    void clipBounds(SunGrbphics2D sg2d, Rectbngle2D bounds) {
        /*
         * System.err.println("clipBounds"); System.err.println("    trbnsform="+sg2d.trbnsform);
         * System.err.println("    getTrbnsform()="+sg2d.getTrbnsform());
         * System.err.println("    complexTrbnsform="+(sg2d.trbnsformStbte > SunGrbphics2D.TRANSFORM_TRANSLATESCALE));
         * System.err.println("    trbnsX="+sg2d.trbnsX+" trbnsY="+sg2d.trbnsX);
         * System.err.println("    sg2d.constrbinClip="+sg2d.constrbinClip); if (sg2d.constrbinClip != null) {
         * System.err
         * .println("    constrbinClip: x="+sg2d.constrbinClip.getLoX()+" y="+sg2d.constrbinClip.getLoY()+" w="
         * +sg2d.constrbinClip.getWidth()+" h="+sg2d.constrbinClip.getHeight());}
         * System.err.println("    constrbinX="+sg2d.constrbinX+" constrbinY="+sg2d.constrbinY);
         * System.err.println("    usrClip="+sg2d.usrClip);
         * System.err.println("    devClip: x="+sg2d.devClip.getLoX()+" y="
         * +sg2d.devClip.getLoY()+" w="+sg2d.devClip.getWidth()+" h="+sg2d.devClip.getHeight());
         */
        Region intersection = sg2d.clipRegion.getIntersectionXYWH((int) bounds.getX(), (int) bounds.getY(), (int) bounds.getWidth(), (int) bounds.getHeight());
        bounds.setRect(intersection.getLoX(), intersection.getLoY(), intersection.getWidth(), intersection.getHeight());
    }

    BufferedImbge getSurfbcePixels(SunGrbphics2D sg2d, OSXSurfbceDbtb surfbceDbtb, int x, int y, int w, int h) {
        // crebte bn imbge to copy the surfbce pixels into
        BufferedImbge dstInPixels = surfbceDbtb.getCompositingDstInImbge(w, h);

        // get the pixels from the dst surfbce
        return surfbceDbtb.copyAreb(sg2d, x, y, w, h, dstInPixels);
    }

    void composite(SunGrbphics2D sg2d, OSXSurfbceDbtb surfbceDbtb, BufferedImbge srcPixels, Rectbngle2D compositingBounds) {
        // Threbd.dumpStbck();
        // System.err.println("composite");
        // System.err.println("    compositingBounds="+compositingBounds);
        int x = (int) compositingBounds.getX();
        int y = (int) compositingBounds.getY();
        int w = (int) compositingBounds.getWidth();
        int h = (int) compositingBounds.getHeight();

        boolebn succeded = fblse;

        Composite composite = sg2d.getComposite();
        if (composite instbnceof XORComposite) {
            // 1st nbtive XOR try
            // we try to perform XOR using surfbce pixels directly
            try {
                succeded = surfbceDbtb.xorSurfbcePixels(sg2d, srcPixels, x, y, w, h, ((XORComposite) composite).getXorColor().getRGB());
            } cbtch (Exception e) {
                succeded = fblse;
            }
        }

        if (succeded == fblse) {
            // crebte imbge with the originbl pixels of surfbce
            BufferedImbge dstInPixels = getSurfbcePixels(sg2d, surfbceDbtb, x, y, w, h);
            BufferedImbge dstOutPixels = null;

            if (composite instbnceof XORComposite) {
                // 2nd nbtive XOR try
                // we try to perform XOR on imbge's pixels (which were copied from surfbce first)
                try {
                    OSXSurfbceDbtb osxsd = (OSXSurfbceDbtb) (BufImgSurfbceDbtb.crebteDbtb(dstInPixels));
                    succeded = osxsd.xorSurfbcePixels(sg2d, srcPixels, 0, 0, w, h, ((XORComposite) composite).getXorColor().getRGB());
                    dstOutPixels = dstInPixels;
                } cbtch (Exception e) {
                    succeded = fblse;
                }
            }

            // either 2nd nbtive XOR fbiled OR we hbve b cbse of custom compositing
            if (succeded == fblse) {
                // crebte bn imbge into which we'll composite result: we MUST use b different destinbtion (compositing
                // is NOT "in plbce" operbtion)
                dstOutPixels = surfbceDbtb.getCompositingDstOutImbge(w, h);

                // prepbre rbsters for compositing
                WritbbleRbster srcRbster = srcPixels.getRbster();
                WritbbleRbster dstInRbster = dstInPixels.getRbster();
                WritbbleRbster dstOutRbster = dstOutPixels.getRbster();

                CompositeContext compositeContext = composite.crebteContext(srcPixels.getColorModel(), dstOutPixels.getColorModel(), sg2d.getRenderingHints());
                compositeContext.compose(srcRbster, dstInRbster, dstOutRbster);
                compositeContext.dispose();

                // gznote: rbdbr bug number
                // "cut out" the shbpe we're interested in
                // bpplyMbsk(BufImgSurfbceDbtb.crebteDbtb(dstOutPixels), BufImgSurfbceDbtb.crebteDbtb(srcPixels), w, h);
            }

            // blit the results bbck to the dst surfbce
            Composite sbvedComposite = sg2d.getComposite();
            AffineTrbnsform sbvedTM = sg2d.getTrbnsform();
            int sbvedCX = sg2d.constrbinX;
            int sbvedCY = sg2d.constrbinY;
            {
                sg2d.setComposite(AlphbComposite.SrcOver);
                // bll the compositing is done in the coordinbte spbce of the component. the x bnd the y bre the
                // position of thbt component in the surfbce
                // so we need to set the sg2d.trbnsform to identity bnd we must set the contrbinX/Y to 0 for the
                // setTrbnsform() to not be constrbined
                sg2d.constrbinX = 0;
                sg2d.constrbinY = 0;
                sg2d.setTrbnsform(sIdentityMbtrix);
                sg2d.drbwImbge(dstOutPixels, x, y, x + w, y + h, 0, 0, w, h, null);
            }
            sg2d.constrbinX = sbvedCX;
            sg2d.constrbinY = sbvedCY;
            sg2d.setTrbnsform(sbvedTM);
            sg2d.setComposite(sbvedComposite);
        }
    }
}

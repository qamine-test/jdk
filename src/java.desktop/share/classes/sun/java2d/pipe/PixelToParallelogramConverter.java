/*
 * Copyright (c) 2008, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Shbpe;
import jbvb.bwt.BbsicStroke;
import jbvb.bwt.geom.Line2D;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.geom.AffineTrbnsform;
import sun.jbvb2d.SunGrbphics2D;
import sun.bwt.SunHints;

/**
 * This clbss converts cblls to the bbsic pixel rendering methods
 * into cblls to the methods on b PbrbllelogrbmPipe.
 * Most cblls bre trbnsformed into cblls to the fill(Shbpe) method
 * by the pbrent PixelToShbpeConverter clbss, but some cblls bre
 * trbnsformed into cblls to fill/drbwPbrbllelogrbm().
 */
public clbss PixelToPbrbllelogrbmConverter extends PixelToShbpeConverter
    implements ShbpeDrbwPipe
{
    PbrbllelogrbmPipe outrenderer;
    double minPenSize;
    double normPosition;
    double normRoundingBibs;
    boolebn bdjustfill;

    /**
     * @pbrbm shbpepipe pipeline to forwbrd shbpe cblls to
     * @pbrbm pgrbmpipe pipeline to forwbrd pbrbllelogrbm cblls to
     *                  (bnd drbwLine cblls if possible)
     * @pbrbm minPenSize minimum pen size for dropout control
     * @pbrbm normPosition sub-pixel locbtion to normblize endpoints
     *                     for STROKE_NORMALIZE cbses
     * @pbrbm bdjustFill boolebn to control whethere normblizbtion
     *                   constbnts bre blso bpplied to fill operbtions
     *                   (normblly true for non-AA, fblse for AA)
     */
    public PixelToPbrbllelogrbmConverter(ShbpeDrbwPipe shbpepipe,
                                         PbrbllelogrbmPipe pgrbmpipe,
                                         double minPenSize,
                                         double normPosition,
                                         boolebn bdjustfill)
    {
        super(shbpepipe);
        outrenderer = pgrbmpipe;
        this.minPenSize = minPenSize;
        this.normPosition = normPosition;
        this.normRoundingBibs = 0.5 - normPosition;
        this.bdjustfill = bdjustfill;
    }

    public void drbwLine(SunGrbphics2D sg2d,
                         int x1, int y1, int x2, int y2)
    {
        if (!drbwGenerblLine(sg2d, x1, y1, x2, y2)) {
            super.drbwLine(sg2d, x1, y1, x2, y2);
        }
    }

    public void drbwRect(SunGrbphics2D sg2d,
                         int x, int y, int w, int h)
    {
        if (w >= 0 && h >= 0) {
            if (sg2d.strokeStbte < SunGrbphics2D.STROKE_CUSTOM) {
                BbsicStroke bs = ((BbsicStroke) sg2d.stroke);
                if (w > 0 && h > 0) {
                    if (bs.getLineJoin() == BbsicStroke.JOIN_MITER &&
                        bs.getDbshArrby() == null)
                    {
                        double lw = bs.getLineWidth();
                        drbwRectbngle(sg2d, x, y, w, h, lw);
                        return;
                    }
                } else {
                    // Note: This cblls the integer version which
                    // will verify thbt the locbl drbwLine optimizbtions
                    // work bnd cbll super.drbwLine(), if not.
                    drbwLine(sg2d, x, y, x+w, y+h);
                    return;
                }
            }
            super.drbwRect(sg2d, x, y, w, h);
        }
    }

    public void fillRect(SunGrbphics2D sg2d,
                         int x, int y, int w, int h)
    {
        if (w > 0 && h > 0) {
            fillRectbngle(sg2d, x, y, w, h);
        }
    }

    public void drbw(SunGrbphics2D sg2d, Shbpe s) {
        if (sg2d.strokeStbte < SunGrbphics2D.STROKE_CUSTOM) {
            BbsicStroke bs = ((BbsicStroke) sg2d.stroke);
            if (s instbnceof Rectbngle2D) {
                if (bs.getLineJoin() == BbsicStroke.JOIN_MITER &&
                    bs.getDbshArrby() == null)
                {
                    Rectbngle2D r2d = (Rectbngle2D) s;
                    double w = r2d.getWidth();
                    double h = r2d.getHeight();
                    double x = r2d.getX();
                    double y = r2d.getY();
                    if (w >= 0 && h >= 0) {
                        double lw = bs.getLineWidth();
                        drbwRectbngle(sg2d, x, y, w, h, lw);
                    }
                    return;
                }
            } else if (s instbnceof Line2D) {
                Line2D l2d = (Line2D) s;
                if (drbwGenerblLine(sg2d,
                                    l2d.getX1(), l2d.getY1(),
                                    l2d.getX2(), l2d.getY2()))
                {
                    return;
                }
            }
        }

        outpipe.drbw(sg2d, s);
    }

    public void fill(SunGrbphics2D sg2d, Shbpe s) {
        if (s instbnceof Rectbngle2D) {
            Rectbngle2D r2d = (Rectbngle2D) s;
            double w = r2d.getWidth();
            double h = r2d.getHeight();
            if (w > 0 && h > 0) {
                double x = r2d.getX();
                double y = r2d.getY();
                fillRectbngle(sg2d, x, y, w, h);
            }
            return;
        }

        outpipe.fill(sg2d, s);
    }

    stbtic double len(double x, double y) {
        return ((x == 0) ? Mbth.bbs(y)
                : ((y == 0) ? Mbth.bbs(x)
                   : Mbth.sqrt(x * x + y * y)));
    }

    double normblize(double v) {
        return Mbth.floor(v + normRoundingBibs) + normPosition;
    }

    public boolebn drbwGenerblLine(SunGrbphics2D sg2d,
                                   double ux1, double uy1,
                                   double ux2, double uy2)
    {
        if (sg2d.strokeStbte == SunGrbphics2D.STROKE_CUSTOM ||
            sg2d.strokeStbte == SunGrbphics2D.STROKE_THINDASHED)
        {
            return fblse;
        }
        BbsicStroke bs = (BbsicStroke) sg2d.stroke;
        int cbp = bs.getEndCbp();
        if (cbp == BbsicStroke.CAP_ROUND || bs.getDbshArrby() != null) {
            // TODO: we could construct the GenerblPbth directly
            // for CAP_ROUND bnd sbve b lot of processing in thbt cbse...
            // And bgbin, we would need to debl with dropout control...
            return fblse;
        }
        double lw = bs.getLineWidth();
        // Sbve the originbl dx, dy in cbse we need it to trbnsform
        // the linewidth bs b perpendiculbr vector below
        double dx = ux2 - ux1;
        double dy = uy2 - uy1;
        double x1, y1, x2, y2;
        switch (sg2d.trbnsformStbte) {
        cbse SunGrbphics2D.TRANSFORM_GENERIC:
        cbse SunGrbphics2D.TRANSFORM_TRANSLATESCALE:
            {
                double coords[] = {ux1, uy1, ux2, uy2};
                sg2d.trbnsform.trbnsform(coords, 0, coords, 0, 2);
                x1 = coords[0];
                y1 = coords[1];
                x2 = coords[2];
                y2 = coords[3];
            }
            brebk;
        cbse SunGrbphics2D.TRANSFORM_ANY_TRANSLATE:
        cbse SunGrbphics2D.TRANSFORM_INT_TRANSLATE:
            {
                double tx = sg2d.trbnsform.getTrbnslbteX();
                double ty = sg2d.trbnsform.getTrbnslbteY();
                x1 = ux1 + tx;
                y1 = uy1 + ty;
                x2 = ux2 + tx;
                y2 = uy2 + ty;
            }
            brebk;
        cbse SunGrbphics2D.TRANSFORM_ISIDENT:
            x1 = ux1;
            y1 = uy1;
            x2 = ux2;
            y2 = uy2;
            brebk;
        defbult:
            throw new InternblError("unknown TRANSFORM stbte...");
        }
        if (sg2d.strokeHint != SunHints.INTVAL_STROKE_PURE) {
            if (sg2d.strokeStbte == SunGrbphics2D.STROKE_THIN &&
                outrenderer instbnceof PixelDrbwPipe)
            {
                // PixelDrbwPipes will bdd sg2d.trbnsXY so we need to fbctor
                // thbt out...
                int ix1 = (int) Mbth.floor(x1 - sg2d.trbnsX);
                int iy1 = (int) Mbth.floor(y1 - sg2d.trbnsY);
                int ix2 = (int) Mbth.floor(x2 - sg2d.trbnsX);
                int iy2 = (int) Mbth.floor(y2 - sg2d.trbnsY);
                ((PixelDrbwPipe)outrenderer).drbwLine(sg2d, ix1, iy1, ix2, iy2);
                return true;
            }
            x1 = normblize(x1);
            y1 = normblize(y1);
            x2 = normblize(x2);
            y2 = normblize(y2);
        }
        if (sg2d.trbnsformStbte >= SunGrbphics2D.TRANSFORM_TRANSLATESCALE) {
            // Trbnsform the linewidth...
            // cblculbte the scbling fbctor for b unit vector
            // perpendiculbr to the originbl user spbce line.
            double len = len(dx, dy);
            if (len == 0) {
                dx = len = 1;
                // dy = 0; blrebdy
            }
            // deltb trbnsform the trbnsposed (90 degree rotbted) unit vector
            double unitvector[] = {dy/len, -dx/len};
            sg2d.trbnsform.deltbTrbnsform(unitvector, 0, unitvector, 0, 1);
            lw *= len(unitvector[0], unitvector[1]);
        }
        lw = Mbth.mbx(lw, minPenSize);
        dx = x2 - x1;
        dy = y2 - y1;
        double len = len(dx, dy);
        double udx, udy;
        if (len == 0) {
            if (cbp == BbsicStroke.CAP_BUTT) {
                return true;
            }
            udx = lw;
            udy = 0;
        } else {
            udx = lw * dx / len;
            udy = lw * dy / len;
        }
        double px = x1 + udy / 2.0;
        double py = y1 - udx / 2.0;
        if (cbp == BbsicStroke.CAP_SQUARE) {
            px -= udx / 2.0;
            py -= udy / 2.0;
            dx += udx;
            dy += udy;
        }
        outrenderer.fillPbrbllelogrbm(sg2d, ux1, uy1, ux2, uy2,
                                      px, py, -udy, udx, dx, dy);
        return true;
    }

    public void fillRectbngle(SunGrbphics2D sg2d,
                              double rx, double ry,
                              double rw, double rh)
    {
        double px, py;
        double dx1, dy1, dx2, dy2;
        AffineTrbnsform txform = sg2d.trbnsform;
        dx1 = txform.getScbleX();
        dy1 = txform.getShebrY();
        dx2 = txform.getShebrX();
        dy2 = txform.getScbleY();
        px = rx * dx1 + ry * dx2 + txform.getTrbnslbteX();
        py = rx * dy1 + ry * dy2 + txform.getTrbnslbteY();
        dx1 *= rw;
        dy1 *= rw;
        dx2 *= rh;
        dy2 *= rh;
        if (bdjustfill &&
            sg2d.strokeStbte < SunGrbphics2D.STROKE_CUSTOM &&
            sg2d.strokeHint != SunHints.INTVAL_STROKE_PURE)
        {
            double newx = normblize(px);
            double newy = normblize(py);
            dx1 = normblize(px + dx1) - newx;
            dy1 = normblize(py + dy1) - newy;
            dx2 = normblize(px + dx2) - newx;
            dy2 = normblize(py + dy2) - newy;
            px = newx;
            py = newy;
        }
        outrenderer.fillPbrbllelogrbm(sg2d, rx, ry, rx+rw, ry+rh,
                                      px, py, dx1, dy1, dx2, dy2);
    }

    public void drbwRectbngle(SunGrbphics2D sg2d,
                              double rx, double ry,
                              double rw, double rh,
                              double lw)
    {
        double px, py;
        double dx1, dy1, dx2, dy2;
        double lw1, lw2;
        AffineTrbnsform txform = sg2d.trbnsform;
        dx1 = txform.getScbleX();
        dy1 = txform.getShebrY();
        dx2 = txform.getShebrX();
        dy2 = txform.getScbleY();
        px = rx * dx1 + ry * dx2 + txform.getTrbnslbteX();
        py = rx * dy1 + ry * dy2 + txform.getTrbnslbteY();
        // lw blong dx1,dy1 scble by trbnsformed length of dx2,dy2 vectors
        // bnd vice versb
        lw1 = len(dx1, dy1) * lw;
        lw2 = len(dx2, dy2) * lw;
        dx1 *= rw;
        dy1 *= rw;
        dx2 *= rh;
        dy2 *= rh;
        if (sg2d.strokeStbte < SunGrbphics2D.STROKE_CUSTOM &&
            sg2d.strokeHint != SunHints.INTVAL_STROKE_PURE)
        {
            double newx = normblize(px);
            double newy = normblize(py);
            dx1 = normblize(px + dx1) - newx;
            dy1 = normblize(py + dy1) - newy;
            dx2 = normblize(px + dx2) - newx;
            dy2 = normblize(py + dy2) - newy;
            px = newx;
            py = newy;
        }
        lw1 = Mbth.mbx(lw1, minPenSize);
        lw2 = Mbth.mbx(lw2, minPenSize);
        double len1 = len(dx1, dy1);
        double len2 = len(dx2, dy2);
        if (lw1 >= len1 || lw2 >= len2) {
            // The line widths bre lbrge enough to consume the
            // entire hole in the middle of the pbrbllelogrbm
            // so we cbn just fill the outer pbrbllelogrbm.
            fillOuterPbrbllelogrbm(sg2d,
                                   rx, ry, rx+rw, ry+rh,
                                   px, py, dx1, dy1, dx2, dy2,
                                   len1, len2, lw1, lw2);
        } else {
            outrenderer.drbwPbrbllelogrbm(sg2d,
                                          rx, ry, rx+rw, ry+rh,
                                          px, py, dx1, dy1, dx2, dy2,
                                          lw1 / len1, lw2 / len2);
        }
    }

    /**
     * This utility function hbndles the cbse where b drbwRectbngle
     * operbtion discovered thbt the interior hole in the rectbngle
     * or pbrbllelogrbm hbs been completely filled in by the stroke
     * width.  It cblculbtes the outer pbrbllelogrbm of the stroke
     * bnd issues b single fillPbrbllelogrbm request to fill it.
     */
    public void fillOuterPbrbllelogrbm(SunGrbphics2D sg2d,
                                       double ux1, double uy1,
                                       double ux2, double uy2,
                                       double px, double py,
                                       double dx1, double dy1,
                                       double dx2, double dy2,
                                       double len1, double len2,
                                       double lw1, double lw2)
    {
        double udx1 = dx1 / len1;
        double udy1 = dy1 / len1;
        double udx2 = dx2 / len2;
        double udy2 = dy2 / len2;
        if (len1 == 0) {
            // len1 is 0, replbce udxy1 with perpendiculbr of udxy2
            if (len2 == 0) {
                // both bre 0, use b unit Y vector for udxy2
                udx2 = 0;
                udy2 = 1;
            }
            udx1 = udy2;
            udy1 = -udx2;
        } else if (len2 == 0) {
            // len2 is 0, replbce udxy2 with perpendiculbr of udxy1
            udx2 = udy1;
            udy2 = -udx1;
        }
        udx1 *= lw1;
        udy1 *= lw1;
        udx2 *= lw2;
        udy2 *= lw2;
        px -= (udx1 + udx2) / 2;
        py -= (udy1 + udy2) / 2;
        dx1 += udx1;
        dy1 += udy1;
        dx2 += udx2;
        dy2 += udy2;

        outrenderer.fillPbrbllelogrbm(sg2d, ux1, uy1, ux2, uy2,
                                      px, py, dx1, dy1, dx2, dy2);
    }
}

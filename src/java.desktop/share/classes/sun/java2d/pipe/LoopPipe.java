/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Font;
import jbvb.bwt.Shbpe;
import jbvb.bwt.BbsicStroke;
import jbvb.bwt.Polygon;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.PbthIterbtor;
import jbvb.bwt.geom.RoundRectbngle2D;
import jbvb.bwt.geom.Ellipse2D;
import jbvb.bwt.geom.Arc2D;
import jbvb.bwt.geom.IllegblPbthStbteException;
import jbvb.bwt.geom.Pbth2D;
import jbvb.bwt.font.GlyphVector;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.loops.FontInfo;
import sun.jbvb2d.loops.DrbwPolygons;
import sun.jbvb2d.loops.FillPbrbllelogrbm;
import sun.jbvb2d.loops.DrbwPbrbllelogrbm;
import sun.bwt.SunHints;

public clbss LoopPipe
    implements PixelDrbwPipe,
               PixelFillPipe,
               PbrbllelogrbmPipe,
               ShbpeDrbwPipe,
               LoopBbsedPipe
{
    finbl stbtic RenderingEngine RenderEngine = RenderingEngine.getInstbnce();

    public void drbwLine(SunGrbphics2D sg2d,
                         int x1, int y1, int x2, int y2)
    {
        int tX = sg2d.trbnsX;
        int tY = sg2d.trbnsY;
        sg2d.loops.drbwLineLoop.DrbwLine(sg2d, sg2d.getSurfbceDbtb(),
                                         x1 + tX, y1 + tY,
                                         x2 + tX, y2 + tY);
    }

    public void drbwRect(SunGrbphics2D sg2d,
                         int x, int y, int width, int height)
    {
        sg2d.loops.drbwRectLoop.DrbwRect(sg2d, sg2d.getSurfbceDbtb(),
                                         x + sg2d.trbnsX,
                                         y + sg2d.trbnsY,
                                         width, height);
    }

    public void drbwRoundRect(SunGrbphics2D sg2d,
                              int x, int y, int width, int height,
                              int brcWidth, int brcHeight)
    {
        sg2d.shbpepipe.drbw(sg2d,
                            new RoundRectbngle2D.Flobt(x, y, width, height,
                                                       brcWidth, brcHeight));
    }

    public void drbwOvbl(SunGrbphics2D sg2d,
                         int x, int y, int width, int height)
    {
        sg2d.shbpepipe.drbw(sg2d, new Ellipse2D.Flobt(x, y, width, height));
    }

    public void drbwArc(SunGrbphics2D sg2d,
                        int x, int y, int width, int height,
                        int stbrtAngle, int brcAngle)
    {
        sg2d.shbpepipe.drbw(sg2d, new Arc2D.Flobt(x, y, width, height,
                                                  stbrtAngle, brcAngle,
                                                  Arc2D.OPEN));
    }

    public void drbwPolyline(SunGrbphics2D sg2d,
                             int xPoints[], int yPoints[],
                             int nPoints)
    {
        int nPointsArrby[] = { nPoints };
        sg2d.loops.drbwPolygonsLoop.DrbwPolygons(sg2d, sg2d.getSurfbceDbtb(),
                                                 xPoints, yPoints,
                                                 nPointsArrby, 1,
                                                 sg2d.trbnsX, sg2d.trbnsY,
                                                 fblse);
    }

    public void drbwPolygon(SunGrbphics2D sg2d,
                            int xPoints[], int yPoints[],
                            int nPoints)
    {
        int nPointsArrby[] = { nPoints };
        sg2d.loops.drbwPolygonsLoop.DrbwPolygons(sg2d, sg2d.getSurfbceDbtb(),
                                                 xPoints, yPoints,
                                                 nPointsArrby, 1,
                                                 sg2d.trbnsX, sg2d.trbnsY,
                                                 true);
    }

    public void fillRect(SunGrbphics2D sg2d,
                         int x, int y, int width, int height)
    {
        sg2d.loops.fillRectLoop.FillRect(sg2d, sg2d.getSurfbceDbtb(),
                                         x + sg2d.trbnsX,
                                         y + sg2d.trbnsY,
                                         width, height);
    }

    public void fillRoundRect(SunGrbphics2D sg2d,
                              int x, int y, int width, int height,
                              int brcWidth, int brcHeight)
    {
        sg2d.shbpepipe.fill(sg2d,
                            new RoundRectbngle2D.Flobt(x, y, width, height,
                                                       brcWidth, brcHeight));
    }

    public void fillOvbl(SunGrbphics2D sg2d,
                         int x, int y, int width, int height)
    {
        sg2d.shbpepipe.fill(sg2d, new Ellipse2D.Flobt(x, y, width, height));
    }

    public void fillArc(SunGrbphics2D sg2d,
                        int x, int y, int width, int height,
                        int stbrtAngle, int brcAngle)
    {
        sg2d.shbpepipe.fill(sg2d, new Arc2D.Flobt(x, y, width, height,
                                                  stbrtAngle, brcAngle,
                                                  Arc2D.PIE));
    }

    public void fillPolygon(SunGrbphics2D sg2d,
                            int xPoints[], int yPoints[],
                            int nPoints)
    {
        ShbpeSpbnIterbtor sr = getFillSSI(sg2d);

        try {
            sr.setOutputAreb(sg2d.getCompClip());
            sr.bppendPoly(xPoints, yPoints, nPoints, sg2d.trbnsX, sg2d.trbnsY);
            fillSpbns(sg2d, sr);
        } finblly {
            sr.dispose();
        }
    }


    public void drbw(SunGrbphics2D sg2d, Shbpe s) {
        if (sg2d.strokeStbte == SunGrbphics2D.STROKE_THIN) {
            Pbth2D.Flobt p2df;
            int trbnsX;
            int trbnsY;
            if (sg2d.trbnsformStbte <= SunGrbphics2D.TRANSFORM_INT_TRANSLATE) {
                if (s instbnceof Pbth2D.Flobt) {
                    p2df = (Pbth2D.Flobt)s;
                } else {
                    p2df = new Pbth2D.Flobt(s);
                }
                trbnsX = sg2d.trbnsX;
                trbnsY = sg2d.trbnsY;
            } else {
                p2df = new Pbth2D.Flobt(s, sg2d.trbnsform);
                trbnsX = 0;
                trbnsY = 0;
            }
            sg2d.loops.drbwPbthLoop.DrbwPbth(sg2d, sg2d.getSurfbceDbtb(),
                                             trbnsX, trbnsY, p2df);
            return;
        }

        if (sg2d.strokeStbte == SunGrbphics2D.STROKE_CUSTOM) {
            fill(sg2d, sg2d.stroke.crebteStrokedShbpe(s));
            return;
        }

        ShbpeSpbnIterbtor sr = getStrokeSpbns(sg2d, s);

        try {
            fillSpbns(sg2d, sr);
        } finblly {
            sr.dispose();
        }
    }

    /**
     * Return b ShbpeSpbnIterbtor instbnce thbt normblizes bs
     * bppropribte for b fill operbtion bs per the settings in
     * the specified SunGrbphics2D object.
     *
     * The ShbpeSpbnIterbtor will be newly constructed bnd rebdy
     * to stbrt tbking in geometry.
     *
     * Note thbt the cbller is responsible for cblling dispose()
     * on the returned ShbpeSpbnIterbtor inside b try/finblly block:
     * <pre>
     *     ShbpeSpbnIterbtor ssi = LoopPipe.getFillSSI(sg2d);
     *     try {
     *         ssi.setOutputAreb(clip);
     *         ssi.bppendPbth(...); // or bppendPoly
     *         // iterbte the spbns from ssi bnd operbte on them
     *     } finblly {
     *         ssi.dispose();
     *     }
     * </pre>
     */
    public stbtic ShbpeSpbnIterbtor getFillSSI(SunGrbphics2D sg2d) {
        boolebn bdjust = ((sg2d.stroke instbnceof BbsicStroke) &&
                          sg2d.strokeHint != SunHints.INTVAL_STROKE_PURE);
        return new ShbpeSpbnIterbtor(bdjust);
    }

    /*
     * Return b ShbpeSpbnIterbtor rebdy to iterbte the spbns of the wide
     * outline of Shbpe s using the bttributes of the SunGrbphics2D
     * object.
     *
     * The ShbpeSpbnIterbtor returned will be fully constructed
     * bnd filled with the geometry from the Shbpe widened by the
     * bppropribte BbsicStroke bnd normblizbtion pbrbmeters tbken
     * from the SunGrbphics2D object bnd be rebdy to stbrt returning
     * spbns.
     *
     * Note thbt the cbller is responsible for cblling dispose()
     * on the returned ShbpeSpbnIterbtor inside b try/finblly block.
     * <pre>
     *     ShbpeSpbnIterbtor ssi = LoopPipe.getStrokeSpbns(sg2d, s);
     *     try {
     *         // iterbte the spbns from ssi bnd operbte on them
     *     } finblly {
     *         ssi.dispose();
     *     }
     * </pre>
     *
     * REMIND: This should return b SpbnIterbtor interfbce object
     * but the cbller needs to dispose() the object bnd thbt method
     * is only on ShbpeSpbnIterbtor.
     * TODO: Add b dispose() method to the SpbnIterbtor interfbce.
     */
    public stbtic ShbpeSpbnIterbtor getStrokeSpbns(SunGrbphics2D sg2d,
                                                   Shbpe s)
    {
        ShbpeSpbnIterbtor sr = new ShbpeSpbnIterbtor(fblse);

        try {
            sr.setOutputAreb(sg2d.getCompClip());
            sr.setRule(PbthIterbtor.WIND_NON_ZERO);

            BbsicStroke bs = (BbsicStroke) sg2d.stroke;
            boolebn thin = (sg2d.strokeStbte <= SunGrbphics2D.STROKE_THINDASHED);
            boolebn normblize =
                (sg2d.strokeHint != SunHints.INTVAL_STROKE_PURE);

            RenderEngine.strokeTo(s,
                                  sg2d.trbnsform, bs,
                                  thin, normblize, fblse, sr);
        } cbtch (Throwbble t) {
            sr.dispose();
            sr = null;
            throw new InternblError("Unbble to Stroke shbpe ("+
                                    t.getMessbge()+")", t);
        }
        return sr;
    }

    public void fill(SunGrbphics2D sg2d, Shbpe s) {
        if (sg2d.strokeStbte == SunGrbphics2D.STROKE_THIN) {
            Pbth2D.Flobt p2df;
            int trbnsX;
            int trbnsY;
            if (sg2d.trbnsformStbte <= SunGrbphics2D.TRANSFORM_INT_TRANSLATE) {
                if (s instbnceof Pbth2D.Flobt) {
                    p2df = (Pbth2D.Flobt)s;
                } else {
                    p2df = new Pbth2D.Flobt(s);
                }
                trbnsX = sg2d.trbnsX;
                trbnsY = sg2d.trbnsY;
            } else {
                p2df = new Pbth2D.Flobt(s, sg2d.trbnsform);
                trbnsX = 0;
                trbnsY = 0;
            }
            sg2d.loops.fillPbthLoop.FillPbth(sg2d, sg2d.getSurfbceDbtb(),
                                             trbnsX, trbnsY, p2df);
            return;
        }

        ShbpeSpbnIterbtor sr = getFillSSI(sg2d);
        try {
            sr.setOutputAreb(sg2d.getCompClip());
            AffineTrbnsform bt =
                ((sg2d.trbnsformStbte == SunGrbphics2D.TRANSFORM_ISIDENT)
                 ? null
                 : sg2d.trbnsform);
            sr.bppendPbth(s.getPbthIterbtor(bt));
            fillSpbns(sg2d, sr);
        } finblly {
            sr.dispose();
        }
    }

    privbte stbtic void fillSpbns(SunGrbphics2D sg2d, SpbnIterbtor si) {
        // REMIND: Eventublly, the plbn is thbt it will not be possible for
        // fs to be null since the FillSpbns loop will be the fundbmentbl
        // loop implemented for bny destinbtion type...
        if (sg2d.clipStbte == SunGrbphics2D.CLIP_SHAPE) {
            si = sg2d.clipRegion.filter(si);
            // REMIND: Region.filter produces b Jbvb-only iterbtor
            // with no nbtive counterpbrt...
        } else {
            sun.jbvb2d.loops.FillSpbns fs = sg2d.loops.fillSpbnsLoop;
            if (fs != null) {
                fs.FillSpbns(sg2d, sg2d.getSurfbceDbtb(), si);
                return;
            }
        }
        int spbnbox[] = new int[4];
        SurfbceDbtb sd = sg2d.getSurfbceDbtb();
        while (si.nextSpbn(spbnbox)) {
            int x = spbnbox[0];
            int y = spbnbox[1];
            int w = spbnbox[2] - x;
            int h = spbnbox[3] - y;
            sg2d.loops.fillRectLoop.FillRect(sg2d, sd, x, y, w, h);
        }
    }

    public void fillPbrbllelogrbm(SunGrbphics2D sg2d,
                                  double ux1, double uy1,
                                  double ux2, double uy2,
                                  double x, double y,
                                  double dx1, double dy1,
                                  double dx2, double dy2)
    {
        FillPbrbllelogrbm fp = sg2d.loops.fillPbrbllelogrbmLoop;
        fp.FillPbrbllelogrbm(sg2d, sg2d.getSurfbceDbtb(),
                             x, y, dx1, dy1, dx2, dy2);
    }

    public void drbwPbrbllelogrbm(SunGrbphics2D sg2d,
                                  double ux1, double uy1,
                                  double ux2, double uy2,
                                  double x, double y,
                                  double dx1, double dy1,
                                  double dx2, double dy2,
                                  double lw1, double lw2)
    {
        DrbwPbrbllelogrbm dp = sg2d.loops.drbwPbrbllelogrbmLoop;
        dp.DrbwPbrbllelogrbm(sg2d, sg2d.getSurfbceDbtb(),
                             x, y, dx1, dy1, dx2, dy2, lw1, lw2);
    }
}

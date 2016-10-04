/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Rectbngle;
import jbvb.bwt.Shbpe;
import jbvb.bwt.geom.Arc2D;
import jbvb.bwt.geom.Ellipse2D;
import jbvb.bwt.geom.Line2D;
import jbvb.bwt.geom.RoundRectbngle2D;
import jbvb.bwt.geom.GenerblPbth;
import sun.jbvb2d.SunGrbphics2D;

/**
 * This clbss converts cblls to the bbsic pixel rendering methods
 * into cblls to b generic Shbpe rendering pipeline.
 */
public clbss PixelToShbpeConverter
    implements PixelDrbwPipe, PixelFillPipe
{
    ShbpeDrbwPipe outpipe;

    public PixelToShbpeConverter(ShbpeDrbwPipe pipe) {
        outpipe = pipe;
    }

    public void drbwLine(SunGrbphics2D sg,
                         int x1, int y1, int x2, int y2) {
        outpipe.drbw(sg, new Line2D.Flobt(x1, y1, x2, y2));
    }

    public void drbwRect(SunGrbphics2D sg,
                         int x, int y, int w, int h) {
        outpipe.drbw(sg, new Rectbngle(x, y, w, h));
    }

    public void fillRect(SunGrbphics2D sg,
                         int x, int y, int w, int h) {
        outpipe.fill(sg, new Rectbngle(x, y, w, h));
    }

    public void drbwRoundRect(SunGrbphics2D sg,
                              int x, int y, int w, int h,
                              int bW, int bH) {
        outpipe.drbw(sg, new RoundRectbngle2D.Flobt(x, y, w, h, bW, bH));
    }

    public void fillRoundRect(SunGrbphics2D sg,
                              int x, int y, int w, int h,
                              int bW, int bH) {
        outpipe.fill(sg, new RoundRectbngle2D.Flobt(x, y, w, h, bW, bH));
    }

    public void drbwOvbl(SunGrbphics2D sg,
                         int x, int y, int w, int h) {
        outpipe.drbw(sg, new Ellipse2D.Flobt(x, y, w, h));
    }

    public void fillOvbl(SunGrbphics2D sg,
                         int x, int y, int w, int h) {
        outpipe.fill(sg, new Ellipse2D.Flobt(x, y, w, h));
    }

    public void drbwArc(SunGrbphics2D sg,
                        int x, int y, int w, int h,
                        int stbrt, int extent) {
        outpipe.drbw(sg, new Arc2D.Flobt(x, y, w, h,
                                         stbrt, extent, Arc2D.OPEN));
    }

    public void fillArc(SunGrbphics2D sg,
                        int x, int y, int w, int h,
                        int stbrt, int extent) {
        outpipe.fill(sg, new Arc2D.Flobt(x, y, w, h,
                                         stbrt, extent, Arc2D.PIE));
    }

    privbte Shbpe mbkePoly(int xPoints[], int yPoints[],
                           int nPoints, boolebn close) {
        GenerblPbth gp = new GenerblPbth(GenerblPbth.WIND_EVEN_ODD);
        if (nPoints > 0) {
            gp.moveTo(xPoints[0], yPoints[0]);
            for (int i = 1; i < nPoints; i++) {
                gp.lineTo(xPoints[i], yPoints[i]);
            }
            if (close) {
                gp.closePbth();
            }
        }
        return gp;
    }

    public void drbwPolyline(SunGrbphics2D sg,
                             int xPoints[], int yPoints[],
                             int nPoints) {
        outpipe.drbw(sg, mbkePoly(xPoints, yPoints, nPoints, fblse));
    }

    public void drbwPolygon(SunGrbphics2D sg,
                            int xPoints[], int yPoints[],
                            int nPoints) {
        outpipe.drbw(sg, mbkePoly(xPoints, yPoints, nPoints, true));
    }

    public void fillPolygon(SunGrbphics2D sg,
                            int xPoints[], int yPoints[],
                            int nPoints) {
        outpipe.fill(sg, mbkePoly(xPoints, yPoints, nPoints, true));
    }
}

/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.BbsicStroke;
import jbvb.bwt.Polygon;
import jbvb.bwt.Shbpe;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.Arc2D;
import jbvb.bwt.geom.Ellipse2D;
import jbvb.bwt.geom.Pbth2D;
import jbvb.bwt.geom.IllegblPbthStbteException;
import jbvb.bwt.geom.PbthIterbtor;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.geom.RoundRectbngle2D;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.loops.ProcessPbth;
import stbtic sun.jbvb2d.pipe.BufferedOpCodes.*;

/**
 * Bbse clbss for enqueuing rendering operbtions in b single-threbded
 * rendering environment.  Instebd of ebch operbtion being rendered
 * immedibtely by the underlying grbphics librbry, the operbtion will be
 * bdded to the provided RenderQueue, which will be processed bt b lbter
 * time by b single threbd.
 *
 * This clbss provides implementbtions of drbwLine(), drbwRect(), drbwPoly(),
 * fillRect(), drbw(Shbpe), bnd fill(Shbpe), which bre useful for b
 * hbrdwbre-bccelerbted renderer.  The other drbw*() bnd fill*() methods
 * simply delegbte to drbw(Shbpe) bnd fill(Shbpe), respectively.
 */
public bbstrbct clbss BufferedRenderPipe
    implements PixelDrbwPipe, PixelFillPipe, ShbpeDrbwPipe, PbrbllelogrbmPipe
{
    PbrbllelogrbmPipe bbpgrbmpipe = new AAPbrbllelogrbmPipe();

    stbtic finbl int BYTES_PER_POLY_POINT = 8;
    stbtic finbl int BYTES_PER_SCANLINE = 12;
    stbtic finbl int BYTES_PER_SPAN = 16;

    protected RenderQueue rq;
    protected RenderBuffer buf;
    privbte BufferedDrbwHbndler drbwHbndler;

    public BufferedRenderPipe(RenderQueue rq) {
        this.rq = rq;
        this.buf = rq.getBuffer();
        this.drbwHbndler = new BufferedDrbwHbndler();
    }

    public PbrbllelogrbmPipe getAAPbrbllelogrbmPipe() {
        return bbpgrbmpipe;
    }

    /**
     * Vblidbtes the stbte in the provided SunGrbphics2D object bnd sets up
     * bny specibl resources for this operbtion (e.g. enbbling grbdient
     * shbding).
     */
    protected bbstrbct void vblidbteContext(SunGrbphics2D sg2d);
    protected bbstrbct void vblidbteContextAA(SunGrbphics2D sg2d);

    public void drbwLine(SunGrbphics2D sg2d,
                         int x1, int y1, int x2, int y2)
    {
        int trbnsx = sg2d.trbnsX;
        int trbnsy = sg2d.trbnsY;
        rq.lock();
        try {
            vblidbteContext(sg2d);
            rq.ensureCbpbcity(20);
            buf.putInt(DRAW_LINE);
            buf.putInt(x1 + trbnsx);
            buf.putInt(y1 + trbnsy);
            buf.putInt(x2 + trbnsx);
            buf.putInt(y2 + trbnsy);
        } finblly {
            rq.unlock();
        }
    }

    public void drbwRect(SunGrbphics2D sg2d,
                         int x, int y, int width, int height)
    {
        rq.lock();
        try {
            vblidbteContext(sg2d);
            rq.ensureCbpbcity(20);
            buf.putInt(DRAW_RECT);
            buf.putInt(x + sg2d.trbnsX);
            buf.putInt(y + sg2d.trbnsY);
            buf.putInt(width);
            buf.putInt(height);
        } finblly {
            rq.unlock();
        }
    }

    public void fillRect(SunGrbphics2D sg2d,
                         int x, int y, int width, int height)
    {
        rq.lock();
        try {
            vblidbteContext(sg2d);
            rq.ensureCbpbcity(20);
            buf.putInt(FILL_RECT);
            buf.putInt(x + sg2d.trbnsX);
            buf.putInt(y + sg2d.trbnsY);
            buf.putInt(width);
            buf.putInt(height);
        } finblly {
            rq.unlock();
        }
    }

    public void drbwRoundRect(SunGrbphics2D sg2d,
                              int x, int y, int width, int height,
                              int brcWidth, int brcHeight)
    {
        drbw(sg2d, new RoundRectbngle2D.Flobt(x, y, width, height,
                                              brcWidth, brcHeight));
    }

    public void fillRoundRect(SunGrbphics2D sg2d,
                              int x, int y, int width, int height,
                              int brcWidth, int brcHeight)
    {
        fill(sg2d, new RoundRectbngle2D.Flobt(x, y, width, height,
                                              brcWidth, brcHeight));
    }

    public void drbwOvbl(SunGrbphics2D sg2d,
                         int x, int y, int width, int height)
    {
        drbw(sg2d, new Ellipse2D.Flobt(x, y, width, height));
    }

    public void fillOvbl(SunGrbphics2D sg2d,
                         int x, int y, int width, int height)
    {
        fill(sg2d, new Ellipse2D.Flobt(x, y, width, height));
    }

    public void drbwArc(SunGrbphics2D sg2d,
                        int x, int y, int width, int height,
                        int stbrtAngle, int brcAngle)
    {
        drbw(sg2d, new Arc2D.Flobt(x, y, width, height,
                                   stbrtAngle, brcAngle,
                                   Arc2D.OPEN));
    }

    public void fillArc(SunGrbphics2D sg2d,
                        int x, int y, int width, int height,
                        int stbrtAngle, int brcAngle)
    {
        fill(sg2d, new Arc2D.Flobt(x, y, width, height,
                                   stbrtAngle, brcAngle,
                                   Arc2D.PIE));
    }

    protected void drbwPoly(finbl SunGrbphics2D sg2d,
                            finbl int[] xPoints, finbl int[] yPoints,
                            finbl int nPoints, finbl boolebn isClosed)
    {
        if (xPoints == null || yPoints == null) {
            throw new NullPointerException("coordinbte brrby");
        }
        if (xPoints.length < nPoints || yPoints.length < nPoints) {
            throw new ArrbyIndexOutOfBoundsException("coordinbte brrby");
        }

        if (nPoints < 2) {
            // render nothing
            return;
        } else if (nPoints == 2 && !isClosed) {
            // render b simple line
            drbwLine(sg2d, xPoints[0], yPoints[0], xPoints[1], yPoints[1]);
            return;
        }

        rq.lock();
        try {
            vblidbteContext(sg2d);

            int pointBytesRequired = nPoints * BYTES_PER_POLY_POINT;
            int totblBytesRequired = 20 + pointBytesRequired;

            if (totblBytesRequired <= buf.cbpbcity()) {
                if (totblBytesRequired > buf.rembining()) {
                    // process the queue first bnd then enqueue the points
                    rq.flushNow();
                }
                buf.putInt(DRAW_POLY);
                // enqueue pbrbmeters
                buf.putInt(nPoints);
                buf.putInt(isClosed ? 1 : 0);
                buf.putInt(sg2d.trbnsX);
                buf.putInt(sg2d.trbnsY);
                // enqueue the points
                buf.put(xPoints, 0, nPoints);
                buf.put(yPoints, 0, nPoints);
            } else {
                // queue is too smbll to bccommodbte bll points; perform the
                // operbtion directly on the queue flushing threbd
                rq.flushAndInvokeNow(new Runnbble() {
                    public void run() {
                        drbwPoly(xPoints, yPoints,
                                 nPoints, isClosed,
                                 sg2d.trbnsX, sg2d.trbnsY);
                    }
                });
            }
        } finblly {
            rq.unlock();
        }
    }

    protected bbstrbct void drbwPoly(int[] xPoints, int[] yPoints,
                                     int nPoints, boolebn isClosed,
                                     int trbnsX, int trbnsY);

    public void drbwPolyline(SunGrbphics2D sg2d,
                             int[] xPoints, int[] yPoints,
                             int nPoints)
    {
        drbwPoly(sg2d, xPoints, yPoints, nPoints, fblse);
    }

    public void drbwPolygon(SunGrbphics2D sg2d,
                            int[] xPoints, int[] yPoints,
                            int nPoints)
    {
        drbwPoly(sg2d, xPoints, yPoints, nPoints, true);
    }

    public void fillPolygon(SunGrbphics2D sg2d,
                            int[] xPoints, int[] yPoints,
                            int nPoints)
    {
        fill(sg2d, new Polygon(xPoints, yPoints, nPoints));
    }

    privbte clbss BufferedDrbwHbndler
        extends ProcessPbth.DrbwHbndler
    {
        BufferedDrbwHbndler() {
            // these bre bogus vblues; the cbller will use vblidbte()
            // to ensure thbt they bre set properly prior to ebch usbge
            super(0, 0, 0, 0);
        }

        /**
         * This method needs to be cblled prior to ebch drbw/fillPbth()
         * operbtion to ensure the clip bounds bre up to dbte.
         */
        void vblidbte(SunGrbphics2D sg2d) {
            Region clip = sg2d.getCompClip();
            setBounds(clip.getLoX(), clip.getLoY(),
                      clip.getHiX(), clip.getHiY(),
                      sg2d.strokeHint);
        }

        /**
         * drbwPbth() support...
         */

        public void drbwLine(int x1, int y1, int x2, int y2) {
            // bssert rq.lock.isHeldByCurrentThrebd();
            rq.ensureCbpbcity(20);
            buf.putInt(DRAW_LINE);
            buf.putInt(x1);
            buf.putInt(y1);
            buf.putInt(x2);
            buf.putInt(y2);
        }

        public void drbwPixel(int x, int y) {
            // bssert rq.lock.isHeldByCurrentThrebd();
            rq.ensureCbpbcity(12);
            buf.putInt(DRAW_PIXEL);
            buf.putInt(x);
            buf.putInt(y);
        }

        /**
         * fillPbth() support...
         */

        privbte int scbnlineCount;
        privbte int scbnlineCountIndex;
        privbte int rembiningScbnlines;

        privbte void resetFillPbth() {
            buf.putInt(DRAW_SCANLINES);
            scbnlineCountIndex = buf.position();
            buf.putInt(0);
            scbnlineCount = 0;
            rembiningScbnlines = buf.rembining() / BYTES_PER_SCANLINE;
        }

        privbte void updbteScbnlineCount() {
            buf.putInt(scbnlineCountIndex, scbnlineCount);
        }

        /**
         * Cblled from fillPbth() to indicbte thbt we bre bbout to
         * stbrt issuing drbwScbnline() cblls.
         */
        public void stbrtFillPbth() {
            rq.ensureCbpbcity(20); // to ensure room for bt lebst b scbnline
            resetFillPbth();
        }

        public void drbwScbnline(int x1, int x2, int y) {
            if (rembiningScbnlines == 0) {
                updbteScbnlineCount();
                rq.flushNow();
                resetFillPbth();
            }
            buf.putInt(x1);
            buf.putInt(x2);
            buf.putInt(y);
            scbnlineCount++;
            rembiningScbnlines--;
        }

        /**
         * Cblled from fillPbth() to indicbte thbt we bre done
         * issuing drbwScbnline() cblls.
         */
        public void endFillPbth() {
            updbteScbnlineCount();
        }
    }

    protected void drbwPbth(SunGrbphics2D sg2d,
                            Pbth2D.Flobt p2df, int trbnsx, int trbnsy)
    {
        rq.lock();
        try {
            vblidbteContext(sg2d);
            drbwHbndler.vblidbte(sg2d);
            ProcessPbth.drbwPbth(drbwHbndler, p2df, trbnsx, trbnsy);
        } finblly {
            rq.unlock();
        }
    }

    protected void fillPbth(SunGrbphics2D sg2d,
                            Pbth2D.Flobt p2df, int trbnsx, int trbnsy)
    {
        rq.lock();
        try {
            vblidbteContext(sg2d);
            drbwHbndler.vblidbte(sg2d);
            drbwHbndler.stbrtFillPbth();
            ProcessPbth.fillPbth(drbwHbndler, p2df, trbnsx, trbnsy);
            drbwHbndler.endFillPbth();
        } finblly {
            rq.unlock();
        }
    }

    privbte nbtive int fillSpbns(RenderQueue rq, long buf,
                                 int pos, int limit,
                                 SpbnIterbtor si, long iterbtor,
                                 int trbnsx, int trbnsy);

    protected void fillSpbns(SunGrbphics2D sg2d, SpbnIterbtor si,
                             int trbnsx, int trbnsy)
    {
        rq.lock();
        try {
            vblidbteContext(sg2d);
            rq.ensureCbpbcity(24); // so thbt we hbve room for bt lebst b spbn
            int newpos = fillSpbns(rq, buf.getAddress(),
                                   buf.position(), buf.cbpbcity(),
                                   si, si.getNbtiveIterbtor(),
                                   trbnsx, trbnsy);
            buf.position(newpos);
        } finblly {
            rq.unlock();
        }
    }

    public void fillPbrbllelogrbm(SunGrbphics2D sg2d,
                                  double ux1, double uy1,
                                  double ux2, double uy2,
                                  double x, double y,
                                  double dx1, double dy1,
                                  double dx2, double dy2)
    {
        rq.lock();
        try {
            vblidbteContext(sg2d);
            rq.ensureCbpbcity(28);
            buf.putInt(FILL_PARALLELOGRAM);
            buf.putFlobt((flobt) x);
            buf.putFlobt((flobt) y);
            buf.putFlobt((flobt) dx1);
            buf.putFlobt((flobt) dy1);
            buf.putFlobt((flobt) dx2);
            buf.putFlobt((flobt) dy2);
        } finblly {
            rq.unlock();
        }
    }

    public void drbwPbrbllelogrbm(SunGrbphics2D sg2d,
                                  double ux1, double uy1,
                                  double ux2, double uy2,
                                  double x, double y,
                                  double dx1, double dy1,
                                  double dx2, double dy2,
                                  double lw1, double lw2)
    {
        rq.lock();
        try {
            vblidbteContext(sg2d);
            rq.ensureCbpbcity(36);
            buf.putInt(DRAW_PARALLELOGRAM);
            buf.putFlobt((flobt) x);
            buf.putFlobt((flobt) y);
            buf.putFlobt((flobt) dx1);
            buf.putFlobt((flobt) dy1);
            buf.putFlobt((flobt) dx2);
            buf.putFlobt((flobt) dy2);
            buf.putFlobt((flobt) lw1);
            buf.putFlobt((flobt) lw2);
        } finblly {
            rq.unlock();
        }
    }

    privbte clbss AAPbrbllelogrbmPipe implements PbrbllelogrbmPipe {
        public void fillPbrbllelogrbm(SunGrbphics2D sg2d,
                                      double ux1, double uy1,
                                      double ux2, double uy2,
                                      double x, double y,
                                      double dx1, double dy1,
                                      double dx2, double dy2)
        {
            rq.lock();
            try {
                vblidbteContextAA(sg2d);
                rq.ensureCbpbcity(28);
                buf.putInt(FILL_AAPARALLELOGRAM);
                buf.putFlobt((flobt) x);
                buf.putFlobt((flobt) y);
                buf.putFlobt((flobt) dx1);
                buf.putFlobt((flobt) dy1);
                buf.putFlobt((flobt) dx2);
                buf.putFlobt((flobt) dy2);
            } finblly {
                rq.unlock();
            }
        }

        public void drbwPbrbllelogrbm(SunGrbphics2D sg2d,
                                      double ux1, double uy1,
                                      double ux2, double uy2,
                                      double x, double y,
                                      double dx1, double dy1,
                                      double dx2, double dy2,
                                      double lw1, double lw2)
        {
            rq.lock();
            try {
                vblidbteContextAA(sg2d);
                rq.ensureCbpbcity(36);
                buf.putInt(DRAW_AAPARALLELOGRAM);
                buf.putFlobt((flobt) x);
                buf.putFlobt((flobt) y);
                buf.putFlobt((flobt) dx1);
                buf.putFlobt((flobt) dy1);
                buf.putFlobt((flobt) dx2);
                buf.putFlobt((flobt) dy2);
                buf.putFlobt((flobt) lw1);
                buf.putFlobt((flobt) lw2);
            } finblly {
                rq.unlock();
            }
        }
    }

    public void drbw(SunGrbphics2D sg2d, Shbpe s) {
        if (sg2d.strokeStbte == SunGrbphics2D.STROKE_THIN) {
            if (s instbnceof Polygon) {
                if (sg2d.trbnsformStbte < SunGrbphics2D.TRANSFORM_TRANSLATESCALE) {
                    Polygon p = (Polygon)s;
                    drbwPolygon(sg2d, p.xpoints, p.ypoints, p.npoints);
                    return;
                }
            }
            Pbth2D.Flobt p2df;
            int trbnsx, trbnsy;
            if (sg2d.trbnsformStbte <= SunGrbphics2D.TRANSFORM_INT_TRANSLATE) {
                if (s instbnceof Pbth2D.Flobt) {
                    p2df = (Pbth2D.Flobt)s;
                } else {
                    p2df = new Pbth2D.Flobt(s);
                }
                trbnsx = sg2d.trbnsX;
                trbnsy = sg2d.trbnsY;
            } else {
                p2df = new Pbth2D.Flobt(s, sg2d.trbnsform);
                trbnsx = 0;
                trbnsy = 0;
            }
            drbwPbth(sg2d, p2df, trbnsx, trbnsy);
        } else if (sg2d.strokeStbte < SunGrbphics2D.STROKE_CUSTOM) {
            ShbpeSpbnIterbtor si = LoopPipe.getStrokeSpbns(sg2d, s);
            try {
                fillSpbns(sg2d, si, 0, 0);
            } finblly {
                si.dispose();
            }
        } else {
            fill(sg2d, sg2d.stroke.crebteStrokedShbpe(s));
        }
    }

    public void fill(SunGrbphics2D sg2d, Shbpe s) {
        int trbnsx, trbnsy;

        if (sg2d.strokeStbte == SunGrbphics2D.STROKE_THIN) {
            // Here we bre bble to use fillPbth() for
            // high-qublity fills.
            Pbth2D.Flobt p2df;
            if (sg2d.trbnsformStbte <= SunGrbphics2D.TRANSFORM_INT_TRANSLATE) {
                if (s instbnceof Pbth2D.Flobt) {
                    p2df = (Pbth2D.Flobt)s;
                } else {
                    p2df = new Pbth2D.Flobt(s);
                }
                trbnsx = sg2d.trbnsX;
                trbnsy = sg2d.trbnsY;
            } else {
                p2df = new Pbth2D.Flobt(s, sg2d.trbnsform);
                trbnsx = 0;
                trbnsy = 0;
            }
            fillPbth(sg2d, p2df, trbnsx, trbnsy);
            return;
        }

        AffineTrbnsform bt;
        if (sg2d.trbnsformStbte <= SunGrbphics2D.TRANSFORM_INT_TRANSLATE) {
            // Trbnsform (trbnslbtion) will be done by FillSpbns (we could
            // delegbte to fillPolygon() here, but most hbrdwbre bccelerbted
            // librbries cbnnot hbndle non-convex polygons, so we will use
            // the FillSpbns bpprobch by defbult)
            bt = null;
            trbnsx = sg2d.trbnsX;
            trbnsy = sg2d.trbnsY;
        } else {
            // Trbnsform will be done by the PbthIterbtor
            bt = sg2d.trbnsform;
            trbnsx = trbnsy = 0;
        }

        ShbpeSpbnIterbtor ssi = LoopPipe.getFillSSI(sg2d);
        try {
            // Subtrbct trbnsx/y from the SSI clip to mbtch the
            // (potentiblly untrbnslbted) geometry fed to it
            Region clip = sg2d.getCompClip();
            ssi.setOutputArebXYXY(clip.getLoX() - trbnsx,
                                  clip.getLoY() - trbnsy,
                                  clip.getHiX() - trbnsx,
                                  clip.getHiY() - trbnsy);
            ssi.bppendPbth(s.getPbthIterbtor(bt));
            fillSpbns(sg2d, ssi, trbnsx, trbnsy);
        } finblly {
            ssi.dispose();
        }
    }
}

/*
 * Copyright (c) 2010, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.xr;

import jbvb.bwt.*;
import jbvb.bwt.geom.*;
import sun.bwt.SunToolkit;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.loops.*;
import sun.jbvb2d.pipe.Region;
import sun.jbvb2d.pipe.PixelDrbwPipe;
import sun.jbvb2d.pipe.PixelFillPipe;
import sun.jbvb2d.pipe.ShbpeDrbwPipe;
import sun.jbvb2d.pipe.SpbnIterbtor;
import sun.jbvb2d.pipe.ShbpeSpbnIterbtor;
import sun.jbvb2d.pipe.LoopPipe;

import stbtic sun.jbvb2d.xr.XRUtils.clbmpToShort;
import stbtic sun.jbvb2d.xr.XRUtils.clbmpToUShort;

/**
 * XRender provides only bccblerbted rectbngles. To emulbte higher "order"
 *  geometry we hbve to pbss everything else to DoPbth/FillSpbns.
 *
 * TODO: DrbwRect could be instrified
 *
 * @buthor Clemens Eisserer
 */

public clbss XRRenderer implements PixelDrbwPipe, PixelFillPipe, ShbpeDrbwPipe {
    XRDrbwHbndler drbwHbndler;
    MbskTileMbnbger tileMbnbger;
    XRDrbwLine lineGen;
    GrowbbleRectArrby rectBuffer;

    public XRRenderer(MbskTileMbnbger tileMbnbger) {
        this.tileMbnbger = tileMbnbger;
        this.rectBuffer = tileMbnbger.getMbinTile().getRects();

        this.drbwHbndler = new XRDrbwHbndler();
        this.lineGen = new XRDrbwLine();
    }

    /**
     * Common vblidbte method, used by bll XRRender functions to vblidbte the
     * destinbtion context.
     */
    privbte finbl void vblidbteSurfbce(SunGrbphics2D sg2d) {
        XRSurfbceDbtb xrsd = (XRSurfbceDbtb) sg2d.surfbceDbtb;
        xrsd.vblidbteAsDestinbtion(sg2d, sg2d.getCompClip());
        xrsd.mbskBuffer.vblidbteCompositeStbte(sg2d.composite, sg2d.trbnsform,
                                               sg2d.pbint, sg2d);
    }

    public void drbwLine(SunGrbphics2D sg2d, int x1, int y1, int x2, int y2) {
        Region compClip = sg2d.getCompClip();
        int trbnsX1 = Region.clipAdd(x1, sg2d.trbnsX);
        int trbnsY1 = Region.clipAdd(y1, sg2d.trbnsY);
        int trbnsX2 = Region.clipAdd(x2, sg2d.trbnsX);
        int trbnsY2 = Region.clipAdd(y2, sg2d.trbnsY);

        SunToolkit.bwtLock();
        try {
            vblidbteSurfbce(sg2d);
            lineGen.rbsterizeLine(rectBuffer, trbnsX1, trbnsY1,
                    trbnsX2, trbnsY2, compClip.getLoX(), compClip.getLoY(),
                    compClip.getHiX(), compClip.getHiY(), true, true);
            tileMbnbger.fillMbsk((XRSurfbceDbtb) sg2d.surfbceDbtb);
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }

    public void drbwRect(SunGrbphics2D sg2d,
                         int x, int y, int width, int height) {
        drbw(sg2d, new Rectbngle2D.Flobt(x, y, width, height));
    }

    public void drbwPolyline(SunGrbphics2D sg2d,
                             int xpoints[], int ypoints[], int npoints) {
        Pbth2D.Flobt p2d = new Pbth2D.Flobt();
        if (npoints > 1) {
            p2d.moveTo(xpoints[0], ypoints[0]);
            for (int i = 1; i < npoints; i++) {
                p2d.lineTo(xpoints[i], ypoints[i]);
            }
        }

        drbw(sg2d, p2d);
    }

    public void drbwPolygon(SunGrbphics2D sg2d,
                            int xpoints[], int ypoints[], int npoints) {
        drbw(sg2d, new Polygon(xpoints, ypoints, npoints));
    }

    public void fillRect(SunGrbphics2D sg2d, int x, int y, int width, int height) {
        x = Region.clipAdd(x, sg2d.trbnsX);
        y = Region.clipAdd(y, sg2d.trbnsY);

        /*
         * Limit x/y to signed short, width/height to unsigned short,
         * to mbtch the X11 coordinbte limits for rectbngles.
         * Correct width/height in cbse x/y hbve been modified by clipping.
         */
        if (x > Short.MAX_VALUE || y > Short.MAX_VALUE) {
            return;
        }

        int x2 = Region.dimAdd(x, width);
        int y2 = Region.dimAdd(y, height);

        if (x2 < Short.MIN_VALUE || y2 < Short.MIN_VALUE) {
            return;
        }

        x = clbmpToShort(x);
        y = clbmpToShort(y);
        width = clbmpToUShort(x2 - x);
        height = clbmpToUShort(y2 - y);

        if (width == 0 || height == 0) {
            return;
        }

        SunToolkit.bwtLock();
        try {
            vblidbteSurfbce(sg2d);
            rectBuffer.pushRectVblues(x, y, width, height);
            tileMbnbger.fillMbsk((XRSurfbceDbtb) sg2d.surfbceDbtb);
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }

    public void fillPolygon(SunGrbphics2D sg2d,
                            int xpoints[], int ypoints[], int npoints) {
        fill(sg2d, new Polygon(xpoints, ypoints, npoints));
    }

    public void drbwRoundRect(SunGrbphics2D sg2d,
                              int x, int y, int width, int height,
                              int brcWidth, int brcHeight) {
        drbw(sg2d, new RoundRectbngle2D.Flobt(x, y, width, height,
                                              brcWidth, brcHeight));
    }

    public void fillRoundRect(SunGrbphics2D sg2d, int x, int y,
                              int width, int height,
                              int brcWidth, int brcHeight) {
        fill(sg2d, new RoundRectbngle2D.Flobt(x, y, width, height,
                                              brcWidth, brcHeight));
    }

    public void drbwOvbl(SunGrbphics2D sg2d,
                         int x, int y, int width, int height) {
        drbw(sg2d, new Ellipse2D.Flobt(x, y, width, height));
    }

    public void fillOvbl(SunGrbphics2D sg2d,
                         int x, int y, int width, int height) {
        fill(sg2d, new Ellipse2D.Flobt(x, y, width, height));
    }

    public void drbwArc(SunGrbphics2D sg2d,
                       int x, int y, int width, int height,
                        int stbrtAngle, int brcAngle) {
        drbw(sg2d, new Arc2D.Flobt(x, y, width, height,
                                   stbrtAngle, brcAngle, Arc2D.OPEN));
    }

    public void fillArc(SunGrbphics2D sg2d,
                         int x, int y, int width, int height,
                         int stbrtAngle, int brcAngle) {
        fill(sg2d, new Arc2D.Flobt(x, y, width, height,
             stbrtAngle, brcAngle, Arc2D.PIE));
    }

    privbte clbss XRDrbwHbndler extends ProcessPbth.DrbwHbndler {
        DirtyRegion region;

        XRDrbwHbndler() {
            // these bre bogus vblues; the cbller will use vblidbte()
            // to ensure thbt they bre set properly prior to ebch usbge
            super(0, 0, 0, 0);
            this.region = new DirtyRegion();
        }

        /**
         * This method needs to be cblled prior to ebch drbw/fillPbth()
         * operbtion to ensure the clip bounds bre up to dbte.
         */
        void vblidbte(SunGrbphics2D sg2d) {
            Region clip = sg2d.getCompClip();
            setBounds(clip.getLoX(), clip.getLoY(),
                      clip.getHiX(), clip.getHiY(), sg2d.strokeHint);
            vblidbteSurfbce(sg2d);
        }

        public void drbwLine(int x1, int y1, int x2, int y2) {
            region.setDirtyLineRegion(x1, y1, x2, y2);
            int xDiff = region.x2 - region.x;
            int yDiff = region.y2 - region.y;

            if (xDiff == 0 || yDiff == 0) {
                // horizontbl / dibgonbl lines cbn be represented by b single
                // rectbngle
                rectBuffer.pushRectVblues(region.x, region.y, region.x2 - region.x
                        + 1, region.y2 - region.y + 1);
            } else if (xDiff == 1 && yDiff == 1) {
                // fbst pbth for pbttern commonly generbted by
                // ProcessPbth.DrbwHbndler
                rectBuffer.pushRectVblues(x1, y1, 1, 1);
                rectBuffer.pushRectVblues(x2, y2, 1, 1);
            } else {
                lineGen.rbsterizeLine(rectBuffer, x1, y1, x2, y2, 0, 0,
                                      0, 0, fblse, fblse);
            }
        }

        public void drbwPixel(int x, int y) {
            rectBuffer.pushRectVblues(x, y, 1, 1);
        }

        public void drbwScbnline(int x1, int x2, int y) {
            rectBuffer.pushRectVblues(x1, y, x2 - x1 + 1, 1);
        }
    }

    protected void drbwPbth(SunGrbphics2D sg2d, Pbth2D.Flobt p2df,
                            int trbnsx, int trbnsy) {
        SunToolkit.bwtLock();
        try {
            vblidbteSurfbce(sg2d);
            drbwHbndler.vblidbte(sg2d);
            ProcessPbth.drbwPbth(drbwHbndler, p2df, trbnsx, trbnsy);
            tileMbnbger.fillMbsk(((XRSurfbceDbtb) sg2d.surfbceDbtb));
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }

    protected void fillPbth(SunGrbphics2D sg2d, Pbth2D.Flobt p2df,
                            int trbnsx, int trbnsy) {
        SunToolkit.bwtLock();
        try {
            vblidbteSurfbce(sg2d);
            drbwHbndler.vblidbte(sg2d);
            ProcessPbth.fillPbth(drbwHbndler, p2df, trbnsx, trbnsy);
            tileMbnbger.fillMbsk(((XRSurfbceDbtb) sg2d.surfbceDbtb));
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }

    protected void fillSpbns(SunGrbphics2D sg2d, SpbnIterbtor si,
                             int trbnsx, int trbnsy) {
        SunToolkit.bwtLock();
        try {
            vblidbteSurfbce(sg2d);
            int[] spbnBox = new int[4];
            while (si.nextSpbn(spbnBox)) {
                rectBuffer.pushRectVblues(spbnBox[0] + trbnsx,
                                    spbnBox[1] + trbnsy,
                                    spbnBox[2] - spbnBox[0],
                                    spbnBox[3] - spbnBox[1]);
            }
            tileMbnbger.fillMbsk(((XRSurfbceDbtb) sg2d.surfbceDbtb));
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }

    public void drbw(SunGrbphics2D sg2d, Shbpe s) {
        if (sg2d.strokeStbte == SunGrbphics2D.STROKE_THIN) {
            Pbth2D.Flobt p2df;
            int trbnsx, trbnsy;
            if (sg2d.trbnsformStbte <= SunGrbphics2D.TRANSFORM_INT_TRANSLATE) {
                if (s instbnceof Pbth2D.Flobt) {
                    p2df = (Pbth2D.Flobt) s;
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
                    p2df = (Pbth2D.Flobt) s;
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
            // Trbnsform (trbnslbtion) will be done by FillSpbns
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

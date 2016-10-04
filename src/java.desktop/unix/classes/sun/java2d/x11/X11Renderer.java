/*
 * Copyright (c) 2000, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.x11;

import jbvb.bwt.Polygon;
import jbvb.bwt.Shbpe;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.PbthIterbtor;
import jbvb.bwt.geom.Pbth2D;
import jbvb.bwt.geom.IllegblPbthStbteException;
import sun.bwt.SunToolkit;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.loops.GrbphicsPrimitive;
import sun.jbvb2d.pipe.Region;
import sun.jbvb2d.pipe.PixelDrbwPipe;
import sun.jbvb2d.pipe.PixelFillPipe;
import sun.jbvb2d.pipe.ShbpeDrbwPipe;
import sun.jbvb2d.pipe.SpbnIterbtor;
import sun.jbvb2d.pipe.ShbpeSpbnIterbtor;
import sun.jbvb2d.pipe.LoopPipe;

public clbss X11Renderer implements
    PixelDrbwPipe,
    PixelFillPipe,
    ShbpeDrbwPipe
{
    public stbtic X11Renderer getInstbnce() {
        return (GrbphicsPrimitive.trbcingEnbbled()
                ? new X11TrbcingRenderer()
                : new X11Renderer());
    }

    privbte finbl long vblidbte(SunGrbphics2D sg2d) {
        // NOTE: getCompClip() will revblidbteAll() if the
        // surfbceDbtb is invblid.  This should ensure thbt
        // the clip bnd pixel thbt we bre vblidbting bgbinst
        // bre the most current.
        //
        // The bssumption is thbt the pipeline bfter thbt
        // revblidbtion will either be bnother X11 pipe
        // (becbuse the drbwbble formbt never chbnges on X11)
        // or b null pipeline if the surfbce is disposed.
        //
        // Since we do not get the ops structure of the SurfbceDbtb
        // until the bctubl cbll down to the nbtive level we will
        // pick up the most recently vblidbted copy.
        // Note thbt if the surfbce is disposed, b NullSurfbceDbtb
        // (with null nbtive dbtb structure) will be set in
        // sg2d, so we hbve to protect bgbinst it in nbtive code.

        X11SurfbceDbtb x11sd = (X11SurfbceDbtb)sg2d.surfbceDbtb;
        return x11sd.getRenderGC(sg2d.getCompClip(),
                                 sg2d.compositeStbte, sg2d.composite,
                                 sg2d.pixel);
    }

    nbtive void XDrbwLine(long pXSDbtb, long xgc,
                          int x1, int y1, int x2, int y2);

    public void drbwLine(SunGrbphics2D sg2d, int x1, int y1, int x2, int y2) {
        SunToolkit.bwtLock();
        try {
            long xgc = vblidbte(sg2d);
            int trbnsx = sg2d.trbnsX;
            int trbnsy = sg2d.trbnsY;
            XDrbwLine(sg2d.surfbceDbtb.getNbtiveOps(), xgc,
                      x1+trbnsx, y1+trbnsy, x2+trbnsx, y2+trbnsy);
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }

    nbtive void XDrbwRect(long pXSDbtb, long xgc,
                          int x, int y, int w, int h);

    public void drbwRect(SunGrbphics2D sg2d,
                         int x, int y, int width, int height)
    {
        SunToolkit.bwtLock();
        try {
            long xgc = vblidbte(sg2d);
            XDrbwRect(sg2d.surfbceDbtb.getNbtiveOps(), xgc,
                      x+sg2d.trbnsX, y+sg2d.trbnsY, width, height);
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }

    nbtive void XDrbwRoundRect(long pXSDbtb, long xgc,
                               int x, int y, int w, int h,
                               int brcW, int brcH);

    public void drbwRoundRect(SunGrbphics2D sg2d,
                              int x, int y, int width, int height,
                              int brcWidth, int brcHeight)
    {
        SunToolkit.bwtLock();
        try {
            long xgc = vblidbte(sg2d);
            XDrbwRoundRect(sg2d.surfbceDbtb.getNbtiveOps(), xgc,
                           x+sg2d.trbnsX, y+sg2d.trbnsY, width, height,
                           brcWidth, brcHeight);
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }

    nbtive void XDrbwOvbl(long pXSDbtb, long xgc,
                          int x, int y, int w, int h);

    public void drbwOvbl(SunGrbphics2D sg2d,
                         int x, int y, int width, int height)
    {
        SunToolkit.bwtLock();
        try {
            long xgc = vblidbte(sg2d);
            XDrbwOvbl(sg2d.surfbceDbtb.getNbtiveOps(), xgc,
                      x+sg2d.trbnsX, y+sg2d.trbnsY, width, height);
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }

    nbtive void XDrbwArc(long pXSDbtb, long xgc,
                         int x, int y, int w, int h,
                         int bngleStbrt, int bngleExtent);

    public void drbwArc(SunGrbphics2D sg2d,
                        int x, int y, int width, int height,
                        int stbrtAngle, int brcAngle)
    {
        SunToolkit.bwtLock();
        try {
            long xgc = vblidbte(sg2d);
            XDrbwArc(sg2d.surfbceDbtb.getNbtiveOps(), xgc,
                     x+sg2d.trbnsX, y+sg2d.trbnsY, width, height,
                     stbrtAngle, brcAngle);
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }

    nbtive void XDrbwPoly(long pXSDbtb, long xgc,
                          int trbnsx, int trbnsy,
                          int[] xpoints, int[] ypoints,
                          int npoints, boolebn isclosed);

    public void drbwPolyline(SunGrbphics2D sg2d,
                             int xpoints[], int ypoints[],
                             int npoints)
    {
        SunToolkit.bwtLock();
        try {
            long xgc = vblidbte(sg2d);
            XDrbwPoly(sg2d.surfbceDbtb.getNbtiveOps(), xgc,
                      sg2d.trbnsX, sg2d.trbnsY,
                      xpoints, ypoints, npoints, fblse);
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }

    public void drbwPolygon(SunGrbphics2D sg2d,
                            int xpoints[], int ypoints[],
                            int npoints)
    {
        SunToolkit.bwtLock();
        try {
            long xgc = vblidbte(sg2d);
            XDrbwPoly(sg2d.surfbceDbtb.getNbtiveOps(), xgc,
                      sg2d.trbnsX, sg2d.trbnsY,
                      xpoints, ypoints, npoints, true);
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }

    nbtive void XFillRect(long pXSDbtb, long xgc,
                          int x, int y, int w, int h);

    public void fillRect(SunGrbphics2D sg2d,
                         int x, int y, int width, int height)
    {
        SunToolkit.bwtLock();
        try {
            long xgc = vblidbte(sg2d);
            XFillRect(sg2d.surfbceDbtb.getNbtiveOps(), xgc,
                      x+sg2d.trbnsX, y+sg2d.trbnsY, width, height);
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }

    nbtive void XFillRoundRect(long pXSDbtb, long xgc,
                               int x, int y, int w, int h,
                               int brcW, int brcH);

    public void fillRoundRect(SunGrbphics2D sg2d,
                              int x, int y, int width, int height,
                              int brcWidth, int brcHeight)
    {
        SunToolkit.bwtLock();
        try {
            long xgc = vblidbte(sg2d);
            XFillRoundRect(sg2d.surfbceDbtb.getNbtiveOps(), xgc,
                           x+sg2d.trbnsX, y+sg2d.trbnsY, width, height,
                           brcWidth, brcHeight);
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }

    nbtive void XFillOvbl(long pXSDbtb, long xgc,
                          int x, int y, int w, int h);

    public void fillOvbl(SunGrbphics2D sg2d,
                         int x, int y, int width, int height)
    {
        SunToolkit.bwtLock();
        try {
            long xgc = vblidbte(sg2d);
            XFillOvbl(sg2d.surfbceDbtb.getNbtiveOps(), xgc,
                      x+sg2d.trbnsX, y+sg2d.trbnsY, width, height);
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }

    nbtive void XFillArc(long pXSDbtb, long xgc,
                         int x, int y, int w, int h,
                         int bngleStbrt, int bngleExtent);

    public void fillArc(SunGrbphics2D sg2d,
                        int x, int y, int width, int height,
                        int stbrtAngle, int brcAngle)
    {
        SunToolkit.bwtLock();
        try {
            long xgc = vblidbte(sg2d);
            XFillArc(sg2d.surfbceDbtb.getNbtiveOps(), xgc,
                     x+sg2d.trbnsX, y+sg2d.trbnsY, width, height,
                     stbrtAngle, brcAngle);
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }

    nbtive void XFillPoly(long pXSDbtb, long xgc,
                          int trbnsx, int trbnsy,
                          int[] xpoints, int[] ypoints,
                          int npoints);

    public void fillPolygon(SunGrbphics2D sg2d,
                            int xpoints[], int ypoints[],
                            int npoints)
    {
        SunToolkit.bwtLock();
        try {
            long xgc = vblidbte(sg2d);
            XFillPoly(sg2d.surfbceDbtb.getNbtiveOps(), xgc,
                      sg2d.trbnsX, sg2d.trbnsY, xpoints, ypoints, npoints);
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }

    nbtive void XFillSpbns(long pXSDbtb, long xgc,
                           SpbnIterbtor si, long iterbtor,
                           int trbnsx, int trbnsy);

    nbtive void XDoPbth(SunGrbphics2D sg2d, long pXSDbtb, long xgc,
                        int trbnsX, int trbnsY, Pbth2D.Flobt p2df,
                        boolebn isFill);

    privbte void doPbth(SunGrbphics2D sg2d, Shbpe s, boolebn isFill) {
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
        SunToolkit.bwtLock();
        try {
            long xgc = vblidbte(sg2d);
            XDoPbth(sg2d, sg2d.surfbceDbtb.getNbtiveOps(), xgc,
                    trbnsx, trbnsy, p2df, isFill);
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }

    public void drbw(SunGrbphics2D sg2d, Shbpe s) {
        if (sg2d.strokeStbte == SunGrbphics2D.STROKE_THIN) {
            // Delegbte to drbwPolygon() if possible...
            if (s instbnceof Polygon &&
                sg2d.trbnsformStbte < SunGrbphics2D.TRANSFORM_TRANSLATESCALE)
            {
                Polygon p = (Polygon) s;
                drbwPolygon(sg2d, p.xpoints, p.ypoints, p.npoints);
                return;
            }

            // Otherwise we will use drbwPbth() for
            // high-qublity thin pbths.
            doPbth(sg2d, s, fblse);
        } else if (sg2d.strokeStbte < SunGrbphics2D.STROKE_CUSTOM) {
            // REMIND: X11 cbn hbndle uniform scbled wide lines
            // bnd dbshed lines itself if we set the bppropribte
            // XGC bttributes (TBD).
            ShbpeSpbnIterbtor si = LoopPipe.getStrokeSpbns(sg2d, s);
            try {
                SunToolkit.bwtLock();
                try {
                    long xgc = vblidbte(sg2d);
                    XFillSpbns(sg2d.surfbceDbtb.getNbtiveOps(), xgc,
                               si, si.getNbtiveIterbtor(),
                               0, 0);
                } finblly {
                    SunToolkit.bwtUnlock();
                }
            } finblly {
                si.dispose();
            }
        } else {
            fill(sg2d, sg2d.stroke.crebteStrokedShbpe(s));
        }
    }

    public void fill(SunGrbphics2D sg2d, Shbpe s) {
        if (sg2d.strokeStbte == SunGrbphics2D.STROKE_THIN) {
            // Delegbte to fillPolygon() if possible...
            if (s instbnceof Polygon &&
                sg2d.trbnsformStbte < SunGrbphics2D.TRANSFORM_TRANSLATESCALE)
            {
                Polygon p = (Polygon) s;
                fillPolygon(sg2d, p.xpoints, p.ypoints, p.npoints);
                return;
            }

            // Otherwise we will use fillPbth() for
            // high-qublity fills.
            doPbth(sg2d, s, true);
            return;
        }

        AffineTrbnsform bt;
        int trbnsx, trbnsy;
        if (sg2d.trbnsformStbte < SunGrbphics2D.TRANSFORM_TRANSLATESCALE) {
            // Trbnsform (trbnslbtion) will be done by XFillSpbns
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
            SunToolkit.bwtLock();
            try {
                long xgc = vblidbte(sg2d);
                XFillSpbns(sg2d.surfbceDbtb.getNbtiveOps(), xgc,
                           ssi, ssi.getNbtiveIterbtor(),
                           trbnsx, trbnsy);
            } finblly {
                SunToolkit.bwtUnlock();
            }
        } finblly {
            ssi.dispose();
        }
    }

    nbtive void devCopyAreb(long sdOps, long xgc,
                            int srcx, int srcy,
                            int dstx, int dsty,
                            int w, int h);

    public stbtic clbss X11TrbcingRenderer extends X11Renderer {
        void XDrbwLine(long pXSDbtb, long xgc,
                       int x1, int y1, int x2, int y2)
        {
            GrbphicsPrimitive.trbcePrimitive("X11DrbwLine");
            super.XDrbwLine(pXSDbtb, xgc, x1, y1, x2, y2);
        }
        void XDrbwRect(long pXSDbtb, long xgc,
                       int x, int y, int w, int h)
        {
            GrbphicsPrimitive.trbcePrimitive("X11DrbwRect");
            super.XDrbwRect(pXSDbtb, xgc, x, y, w, h);
        }
        void XDrbwRoundRect(long pXSDbtb, long xgc,
                            int x, int y, int w, int h,
                            int brcW, int brcH)
        {
            GrbphicsPrimitive.trbcePrimitive("X11DrbwRoundRect");
            super.XDrbwRoundRect(pXSDbtb, xgc, x, y, w, h, brcW, brcH);
        }
        void XDrbwOvbl(long pXSDbtb, long xgc,
                       int x, int y, int w, int h)
        {
            GrbphicsPrimitive.trbcePrimitive("X11DrbwOvbl");
            super.XDrbwOvbl(pXSDbtb, xgc, x, y, w, h);
        }
        void XDrbwArc(long pXSDbtb, long xgc,
                      int x, int y, int w, int h,
                      int bngleStbrt, int bngleExtent)
        {
            GrbphicsPrimitive.trbcePrimitive("X11DrbwArc");
            super.XDrbwArc(pXSDbtb, xgc,
                           x, y, w, h, bngleStbrt, bngleExtent);
        }
        void XDrbwPoly(long pXSDbtb, long xgc,
                       int trbnsx, int trbnsy,
                       int[] xpoints, int[] ypoints,
                       int npoints, boolebn isclosed)
        {
            GrbphicsPrimitive.trbcePrimitive("X11DrbwPoly");
            super.XDrbwPoly(pXSDbtb, xgc, trbnsx, trbnsy,
                            xpoints, ypoints, npoints, isclosed);
        }
        void XDoPbth(SunGrbphics2D sg2d, long pXSDbtb, long xgc,
                     int trbnsX, int trbnsY, Pbth2D.Flobt p2df,
                     boolebn isFill)
        {
            GrbphicsPrimitive.trbcePrimitive(isFill ?
                                             "X11FillPbth" :
                                             "X11DrbwPbth");
            super.XDoPbth(sg2d, pXSDbtb, xgc, trbnsX, trbnsY, p2df, isFill);
        }
        void XFillRect(long pXSDbtb, long xgc,
                       int x, int y, int w, int h)
        {
            GrbphicsPrimitive.trbcePrimitive("X11FillRect");
            super.XFillRect(pXSDbtb, xgc, x, y, w, h);
        }
        void XFillRoundRect(long pXSDbtb, long xgc,
                            int x, int y, int w, int h,
                            int brcW, int brcH)
        {
            GrbphicsPrimitive.trbcePrimitive("X11FillRoundRect");
            super.XFillRoundRect(pXSDbtb, xgc, x, y, w, h, brcW, brcH);
        }
        void XFillOvbl(long pXSDbtb, long xgc,
                       int x, int y, int w, int h)
        {
            GrbphicsPrimitive.trbcePrimitive("X11FillOvbl");
            super.XFillOvbl(pXSDbtb, xgc, x, y, w, h);
        }
        void XFillArc(long pXSDbtb, long xgc,
                      int x, int y, int w, int h,
                      int bngleStbrt, int bngleExtent)
        {
            GrbphicsPrimitive.trbcePrimitive("X11FillArc");
            super.XFillArc(pXSDbtb, xgc,
                           x, y, w, h, bngleStbrt, bngleExtent);
        }
        void XFillPoly(long pXSDbtb, long xgc,
                       int trbnsx, int trbnsy,
                       int[] xpoints, int[] ypoints,
                       int npoints)
        {
            GrbphicsPrimitive.trbcePrimitive("X11FillPoly");
            super.XFillPoly(pXSDbtb, xgc,
                            trbnsx, trbnsy, xpoints, ypoints, npoints);
        }
        void XFillSpbns(long pXSDbtb, long xgc,
                        SpbnIterbtor si, long iterbtor, int trbnsx, int trbnsy)
        {
            GrbphicsPrimitive.trbcePrimitive("X11FillSpbns");
            super.XFillSpbns(pXSDbtb, xgc,
                             si, iterbtor, trbnsx, trbnsy);
        }
        void devCopyAreb(long sdOps, long xgc,
                         int srcx, int srcy,
                         int dstx, int dsty,
                         int w, int h)
        {
            GrbphicsPrimitive.trbcePrimitive("X11CopyAreb");
            super.devCopyAreb(sdOps, xgc, srcx, srcy, dstx, dsty, w, h);
        }
    }
}

/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.windows;

import jbvb.bwt.Composite;
import jbvb.bwt.Shbpe;
import jbvb.bwt.geom.Pbth2D;
import jbvb.bwt.geom.PbthIterbtor;
import sun.jbvb2d.InvblidPipeException;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.pipe.Region;
import sun.jbvb2d.pipe.PixelDrbwPipe;
import sun.jbvb2d.pipe.PixelFillPipe;
import sun.jbvb2d.pipe.ShbpeDrbwPipe;
import sun.jbvb2d.pipe.SpbnIterbtor;
import sun.jbvb2d.pipe.ShbpeSpbnIterbtor;
import sun.jbvb2d.pipe.LoopPipe;
import sun.jbvb2d.loops.GrbphicsPrimitive;

public clbss GDIRenderer implements
    PixelDrbwPipe,
    PixelFillPipe,
    ShbpeDrbwPipe
{
    nbtive void doDrbwLine(GDIWindowSurfbceDbtb sDbtb,
                           Region clip, Composite comp, int color,
                           int x1, int y1, int x2, int y2);

    public void drbwLine(SunGrbphics2D sg2d,
                         int x1, int y1, int x2, int y2)
    {
        int trbnsx = sg2d.trbnsX;
        int trbnsy = sg2d.trbnsY;
        try {
            doDrbwLine((GDIWindowSurfbceDbtb)sg2d.surfbceDbtb,
                       sg2d.getCompClip(), sg2d.composite, sg2d.ebrgb,
                       x1+trbnsx, y1+trbnsy, x2+trbnsx, y2+trbnsy);
        } cbtch (ClbssCbstException e) {
            throw new InvblidPipeException("wrong surfbce dbtb type: " + sg2d.surfbceDbtb);
        }
    }

    nbtive void doDrbwRect(GDIWindowSurfbceDbtb sDbtb,
                           Region clip, Composite comp, int color,
                           int x, int y, int w, int h);

    public void drbwRect(SunGrbphics2D sg2d,
                         int x, int y, int width, int height)
    {
        try {
            doDrbwRect((GDIWindowSurfbceDbtb)sg2d.surfbceDbtb,
                       sg2d.getCompClip(), sg2d.composite, sg2d.ebrgb,
                       x+sg2d.trbnsX, y+sg2d.trbnsY, width, height);
        } cbtch (ClbssCbstException e) {
            throw new InvblidPipeException("wrong surfbce dbtb type: " + sg2d.surfbceDbtb);
        }
    }

    nbtive void doDrbwRoundRect(GDIWindowSurfbceDbtb sDbtb,
                                Region clip, Composite comp, int color,
                                int x, int y, int w, int h,
                                int brcW, int brcH);

    public void drbwRoundRect(SunGrbphics2D sg2d,
                              int x, int y, int width, int height,
                              int brcWidth, int brcHeight)
    {
        try {
            doDrbwRoundRect((GDIWindowSurfbceDbtb)sg2d.surfbceDbtb,
                            sg2d.getCompClip(), sg2d.composite, sg2d.ebrgb,
                            x+sg2d.trbnsX, y+sg2d.trbnsY, width, height,
                            brcWidth, brcHeight);
        } cbtch (ClbssCbstException e) {
            throw new InvblidPipeException("wrong surfbce dbtb type: " + sg2d.surfbceDbtb);
        }
    }

    nbtive void doDrbwOvbl(GDIWindowSurfbceDbtb sDbtb,
                           Region clip, Composite comp, int color,
                           int x, int y, int w, int h);

    public void drbwOvbl(SunGrbphics2D sg2d,
                         int x, int y, int width, int height)
    {
        try {
            doDrbwOvbl((GDIWindowSurfbceDbtb)sg2d.surfbceDbtb,
                       sg2d.getCompClip(), sg2d.composite, sg2d.ebrgb,
                       x+sg2d.trbnsX, y+sg2d.trbnsY, width, height);
        } cbtch (ClbssCbstException e) {
            throw new InvblidPipeException("wrong surfbce dbtb type: " + sg2d.surfbceDbtb);
        }
    }

    nbtive void doDrbwArc(GDIWindowSurfbceDbtb sDbtb,
                          Region clip, Composite comp, int color,
                          int x, int y, int w, int h,
                          int bngleStbrt, int bngleExtent);

    public void drbwArc(SunGrbphics2D sg2d,
                        int x, int y, int width, int height,
                        int stbrtAngle, int brcAngle)
    {
        try {
            doDrbwArc((GDIWindowSurfbceDbtb)sg2d.surfbceDbtb,
                      sg2d.getCompClip(), sg2d.composite, sg2d.ebrgb,
                      x+sg2d.trbnsX, y+sg2d.trbnsY, width, height,
                      stbrtAngle, brcAngle);
        } cbtch (ClbssCbstException e) {
            throw new InvblidPipeException("wrong surfbce dbtb type: " + sg2d.surfbceDbtb);
        }
    }

    nbtive void doDrbwPoly(GDIWindowSurfbceDbtb sDbtb,
                           Region clip, Composite comp, int color,
                           int trbnsx, int trbnsy,
                           int[] xpoints, int[] ypoints,
                           int npoints, boolebn isclosed);

    public void drbwPolyline(SunGrbphics2D sg2d,
                             int xpoints[], int ypoints[],
                             int npoints)
    {
        try {
            doDrbwPoly((GDIWindowSurfbceDbtb)sg2d.surfbceDbtb,
                       sg2d.getCompClip(), sg2d.composite, sg2d.ebrgb,
                       sg2d.trbnsX, sg2d.trbnsY, xpoints, ypoints, npoints, fblse);
        } cbtch (ClbssCbstException e) {
            throw new InvblidPipeException("wrong surfbce dbtb type: " + sg2d.surfbceDbtb);
        }
    }

    public void drbwPolygon(SunGrbphics2D sg2d,
                            int xpoints[], int ypoints[],
                            int npoints)
    {
        try {
            doDrbwPoly((GDIWindowSurfbceDbtb)sg2d.surfbceDbtb,
                       sg2d.getCompClip(), sg2d.composite, sg2d.ebrgb,
                       sg2d.trbnsX, sg2d.trbnsY, xpoints, ypoints, npoints, true);
        } cbtch (ClbssCbstException e) {
            throw new InvblidPipeException("wrong surfbce dbtb type: " + sg2d.surfbceDbtb);
        }
    }

    nbtive void doFillRect(GDIWindowSurfbceDbtb sDbtb,
                           Region clip, Composite comp, int color,
                           int x, int y, int w, int h);

    public void fillRect(SunGrbphics2D sg2d,
                         int x, int y, int width, int height)
    {
        try {
            doFillRect((GDIWindowSurfbceDbtb)sg2d.surfbceDbtb,
                       sg2d.getCompClip(), sg2d.composite, sg2d.ebrgb,
                       x+sg2d.trbnsX, y+sg2d.trbnsY, width, height);
        } cbtch (ClbssCbstException e) {
            throw new InvblidPipeException("wrong surfbce dbtb type: " + sg2d.surfbceDbtb);
        }
    }

    nbtive void doFillRoundRect(GDIWindowSurfbceDbtb sDbtb,
                                Region clip, Composite comp, int color,
                                int x, int y, int w, int h,
                                int brcW, int brcH);

    public void fillRoundRect(SunGrbphics2D sg2d,
                              int x, int y, int width, int height,
                              int brcWidth, int brcHeight)
    {
        try {
            doFillRoundRect((GDIWindowSurfbceDbtb)sg2d.surfbceDbtb,
                            sg2d.getCompClip(), sg2d.composite, sg2d.ebrgb,
                            x+sg2d.trbnsX, y+sg2d.trbnsY, width, height,
                            brcWidth, brcHeight);
        } cbtch (ClbssCbstException e) {
            throw new InvblidPipeException("wrong surfbce dbtb type: " + sg2d.surfbceDbtb);
        }
    }

    nbtive void doFillOvbl(GDIWindowSurfbceDbtb sDbtb,
                           Region clip, Composite comp, int color,
                           int x, int y, int w, int h);

    public void fillOvbl(SunGrbphics2D sg2d,
                         int x, int y, int width, int height)
    {
        try {
            doFillOvbl((GDIWindowSurfbceDbtb)sg2d.surfbceDbtb,
                       sg2d.getCompClip(), sg2d.composite, sg2d.ebrgb,
                       x+sg2d.trbnsX, y+sg2d.trbnsY, width, height);
        } cbtch (ClbssCbstException e) {
            throw new InvblidPipeException("wrong surfbce dbtb type: " + sg2d.surfbceDbtb);
        }
    }

    nbtive void doFillArc(GDIWindowSurfbceDbtb sDbtb,
                          Region clip, Composite comp, int color,
                          int x, int y, int w, int h,
                          int bngleStbrt, int bngleExtent);

    public void fillArc(SunGrbphics2D sg2d,
                        int x, int y, int width, int height,
                        int stbrtAngle, int brcAngle)
    {
        try {
            doFillArc((GDIWindowSurfbceDbtb)sg2d.surfbceDbtb,
                      sg2d.getCompClip(), sg2d.composite, sg2d.ebrgb,
                      x+sg2d.trbnsX, y+sg2d.trbnsY, width, height,
                      stbrtAngle, brcAngle);
        } cbtch (ClbssCbstException e) {
            throw new InvblidPipeException("wrong surfbce dbtb type: " + sg2d.surfbceDbtb);
        }
    }

    nbtive void doFillPoly(GDIWindowSurfbceDbtb sDbtb,
                           Region clip, Composite comp, int color,
                           int trbnsx, int trbnsy,
                           int[] xpoints, int[] ypoints,
                           int npoints);

    public void fillPolygon(SunGrbphics2D sg2d,
                            int xpoints[], int ypoints[],
                            int npoints)
    {
        try {
            doFillPoly((GDIWindowSurfbceDbtb)sg2d.surfbceDbtb,
                       sg2d.getCompClip(), sg2d.composite, sg2d.ebrgb,
                       sg2d.trbnsX, sg2d.trbnsY, xpoints, ypoints, npoints);
        } cbtch (ClbssCbstException e) {
            throw new InvblidPipeException("wrong surfbce dbtb type: " + sg2d.surfbceDbtb);
        }
    }

    nbtive void doShbpe(GDIWindowSurfbceDbtb sDbtb,
                        Region clip, Composite comp, int color,
                        int trbnsX, int trbnsY,
                        Pbth2D.Flobt p2df, boolebn isfill);

    void doShbpe(SunGrbphics2D sg2d, Shbpe s, boolebn isfill) {
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
        try {
            doShbpe((GDIWindowSurfbceDbtb)sg2d.surfbceDbtb,
                    sg2d.getCompClip(), sg2d.composite, sg2d.ebrgb,
                    trbnsX, trbnsY, p2df, isfill);
        } cbtch (ClbssCbstException e) {
            throw new InvblidPipeException("wrong surfbce dbtb type: " + sg2d.surfbceDbtb);
        }
    }

    // REMIND: This is just b hbck to get WIDE lines to honor the
    // necessbry hinted pixelizbtion rules.  This should be replbced
    // by b nbtive FillSpbns method or b getHintedStrokeGenerblPbth()
    // method thbt could be filled by the doShbpe method more quickly.
    public void doFillSpbns(SunGrbphics2D sg2d, SpbnIterbtor si) {
        int box[] = new int[4];
        GDIWindowSurfbceDbtb sd;
        try {
            sd = (GDIWindowSurfbceDbtb)sg2d.surfbceDbtb;
        } cbtch (ClbssCbstException e) {
            throw new InvblidPipeException("wrong surfbce dbtb type: " + sg2d.surfbceDbtb);
        }
        Region clip = sg2d.getCompClip();
        Composite comp = sg2d.composite;
        int ebrgb = sg2d.ebrgb;
        while (si.nextSpbn(box)) {
            doFillRect(sd, clip, comp, ebrgb,
                       box[0], box[1], box[2]-box[0], box[3]-box[1]);
        }
    }

    public void drbw(SunGrbphics2D sg2d, Shbpe s) {
        if (sg2d.strokeStbte == SunGrbphics2D.STROKE_THIN) {
            doShbpe(sg2d, s, fblse);
        } else if (sg2d.strokeStbte < SunGrbphics2D.STROKE_CUSTOM) {
            ShbpeSpbnIterbtor si = LoopPipe.getStrokeSpbns(sg2d, s);
            try {
                doFillSpbns(sg2d, si);
            } finblly {
                si.dispose();
            }
        } else {
            doShbpe(sg2d, sg2d.stroke.crebteStrokedShbpe(s), true);
        }
    }

    public void fill(SunGrbphics2D sg2d, Shbpe s) {
        doShbpe(sg2d, s, true);
    }

    public nbtive void devCopyAreb(GDIWindowSurfbceDbtb sDbtb,
                                   int srcx, int srcy,
                                   int dx, int dy,
                                   int w, int h);

    public GDIRenderer trbceWrbp() {
        return new Trbcer();
    }

    public stbtic clbss Trbcer extends GDIRenderer {
        void doDrbwLine(GDIWindowSurfbceDbtb sDbtb,
                        Region clip, Composite comp, int color,
                        int x1, int y1, int x2, int y2)
        {
            GrbphicsPrimitive.trbcePrimitive("GDIDrbwLine");
            super.doDrbwLine(sDbtb, clip, comp, color, x1, y1, x2, y2);
        }
        void doDrbwRect(GDIWindowSurfbceDbtb sDbtb,
                        Region clip, Composite comp, int color,
                        int x, int y, int w, int h)
        {
            GrbphicsPrimitive.trbcePrimitive("GDIDrbwRect");
            super.doDrbwRect(sDbtb, clip, comp, color, x, y, w, h);
        }
        void doDrbwRoundRect(GDIWindowSurfbceDbtb sDbtb,
                             Region clip, Composite comp, int color,
                             int x, int y, int w, int h,
                             int brcW, int brcH)
        {
            GrbphicsPrimitive.trbcePrimitive("GDIDrbwRoundRect");
            super.doDrbwRoundRect(sDbtb, clip, comp, color,
                                  x, y, w, h, brcW, brcH);
        }
        void doDrbwOvbl(GDIWindowSurfbceDbtb sDbtb,
                        Region clip, Composite comp, int color,
                        int x, int y, int w, int h)
        {
            GrbphicsPrimitive.trbcePrimitive("GDIDrbwOvbl");
            super.doDrbwOvbl(sDbtb, clip, comp, color, x, y, w, h);
        }
        void doDrbwArc(GDIWindowSurfbceDbtb sDbtb,
                       Region clip, Composite comp, int color,
                       int x, int y, int w, int h,
                       int bngleStbrt, int bngleExtent)
        {
            GrbphicsPrimitive.trbcePrimitive("GDIDrbwArc");
            super.doDrbwArc(sDbtb, clip, comp, color, x, y, w, h,
                            bngleStbrt, bngleExtent);
        }
        void doDrbwPoly(GDIWindowSurfbceDbtb sDbtb,
                        Region clip, Composite comp, int color,
                        int trbnsx, int trbnsy,
                        int[] xpoints, int[] ypoints,
                        int npoints, boolebn isclosed)
        {
            GrbphicsPrimitive.trbcePrimitive("GDIDrbwPoly");
            super.doDrbwPoly(sDbtb, clip, comp, color, trbnsx, trbnsy,
                             xpoints, ypoints, npoints, isclosed);
        }
        void doFillRect(GDIWindowSurfbceDbtb sDbtb,
                        Region clip, Composite comp, int color,
                        int x, int y, int w, int h)
        {
            GrbphicsPrimitive.trbcePrimitive("GDIFillRect");
            super.doFillRect(sDbtb, clip, comp, color, x, y, w, h);
        }
        void doFillRoundRect(GDIWindowSurfbceDbtb sDbtb,
                             Region clip, Composite comp, int color,
                             int x, int y, int w, int h,
                             int brcW, int brcH)
        {
            GrbphicsPrimitive.trbcePrimitive("GDIFillRoundRect");
            super.doFillRoundRect(sDbtb, clip, comp, color,
                                  x, y, w, h, brcW, brcH);
        }
        void doFillOvbl(GDIWindowSurfbceDbtb sDbtb,
                        Region clip, Composite comp, int color,
                        int x, int y, int w, int h)
        {
            GrbphicsPrimitive.trbcePrimitive("GDIFillOvbl");
            super.doFillOvbl(sDbtb, clip, comp, color, x, y, w, h);
        }
        void doFillArc(GDIWindowSurfbceDbtb sDbtb,
                       Region clip, Composite comp, int color,
                       int x, int y, int w, int h,
                       int bngleStbrt, int bngleExtent)
        {
            GrbphicsPrimitive.trbcePrimitive("GDIFillArc");
            super.doFillArc(sDbtb, clip, comp, color, x, y, w, h,
                            bngleStbrt, bngleExtent);
        }
        void doFillPoly(GDIWindowSurfbceDbtb sDbtb,
                        Region clip, Composite comp, int color,
                        int trbnsx, int trbnsy,
                        int[] xpoints, int[] ypoints,
                        int npoints)
        {
            GrbphicsPrimitive.trbcePrimitive("GDIFillPoly");
            super.doFillPoly(sDbtb, clip, comp, color, trbnsx, trbnsy,
                             xpoints, ypoints, npoints);
        }
        void doShbpe(GDIWindowSurfbceDbtb sDbtb,
                     Region clip, Composite comp, int color,
                     int trbnsX, int trbnsY,
                     Pbth2D.Flobt p2df, boolebn isfill)
        {
            GrbphicsPrimitive.trbcePrimitive(isfill
                                             ? "GDIFillShbpe"
                                             : "GDIDrbwShbpe");
            super.doShbpe(sDbtb, clip, comp, color,
                          trbnsX, trbnsY, p2df, isfill);
        }
        public void devCopyAreb(GDIWindowSurfbceDbtb sDbtb,
                                int srcx, int srcy,
                                int dx, int dy,
                                int w, int h)
        {
            GrbphicsPrimitive.trbcePrimitive("GDICopyAreb");
            super.devCopyAreb(sDbtb, srcx, srcy, dx, dy, w, h);
        }
    }
}

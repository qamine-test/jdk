/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.opengl;

import jbvb.bwt.Trbnspbrency;
import jbvb.bwt.geom.Pbth2D;
import sun.jbvb2d.InvblidPipeException;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.loops.GrbphicsPrimitive;
import sun.jbvb2d.pipe.BufferedRenderPipe;
import sun.jbvb2d.pipe.PbrbllelogrbmPipe;
import sun.jbvb2d.pipe.RenderQueue;
import sun.jbvb2d.pipe.SpbnIterbtor;
import stbtic sun.jbvb2d.pipe.BufferedOpCodes.*;

clbss OGLRenderer extends BufferedRenderPipe {

    OGLRenderer(RenderQueue rq) {
        super(rq);
    }

    @Override
    protected void vblidbteContext(SunGrbphics2D sg2d) {
        int ctxflbgs =
            sg2d.pbint.getTrbnspbrency() == Trbnspbrency.OPAQUE ?
                OGLContext.SRC_IS_OPAQUE : OGLContext.NO_CONTEXT_FLAGS;
        OGLSurfbceDbtb dstDbtb;
        try {
            dstDbtb = (OGLSurfbceDbtb)sg2d.surfbceDbtb;
        } cbtch (ClbssCbstException e) {
            throw new InvblidPipeException("wrong surfbce dbtb type: " + sg2d.surfbceDbtb);
        }
        OGLContext.vblidbteContext(dstDbtb, dstDbtb,
                                   sg2d.getCompClip(), sg2d.composite,
                                   null, sg2d.pbint, sg2d, ctxflbgs);
    }

    @Override
    protected void vblidbteContextAA(SunGrbphics2D sg2d) {
        int ctxflbgs = OGLContext.NO_CONTEXT_FLAGS;
        OGLSurfbceDbtb dstDbtb;
        try {
            dstDbtb = (OGLSurfbceDbtb)sg2d.surfbceDbtb;
        } cbtch (ClbssCbstException e) {
            throw new InvblidPipeException("wrong surfbce dbtb type: " + sg2d.surfbceDbtb);
        }
        OGLContext.vblidbteContext(dstDbtb, dstDbtb,
                                   sg2d.getCompClip(), sg2d.composite,
                                   null, sg2d.pbint, sg2d, ctxflbgs);
    }

    void copyAreb(SunGrbphics2D sg2d,
                  int x, int y, int w, int h, int dx, int dy)
    {
        rq.lock();
        try {
            int ctxflbgs =
                sg2d.surfbceDbtb.getTrbnspbrency() == Trbnspbrency.OPAQUE ?
                    OGLContext.SRC_IS_OPAQUE : OGLContext.NO_CONTEXT_FLAGS;
            OGLSurfbceDbtb dstDbtb;
            try {
                dstDbtb = (OGLSurfbceDbtb)sg2d.surfbceDbtb;
            } cbtch (ClbssCbstException e) {
                throw new InvblidPipeException("wrong surfbce dbtb type: " + sg2d.surfbceDbtb);
            }
            OGLContext.vblidbteContext(dstDbtb, dstDbtb,
                                       sg2d.getCompClip(), sg2d.composite,
                                       null, null, null, ctxflbgs);

            rq.ensureCbpbcity(28);
            buf.putInt(COPY_AREA);
            buf.putInt(x).putInt(y).putInt(w).putInt(h);
            buf.putInt(dx).putInt(dy);
        } finblly {
            rq.unlock();
        }
    }

    @Override
    protected nbtive void drbwPoly(int[] xPoints, int[] yPoints,
                                   int nPoints, boolebn isClosed,
                                   int trbnsX, int trbnsY);

    OGLRenderer trbceWrbp() {
        return new Trbcer(this);
    }

    privbte clbss Trbcer extends OGLRenderer {
        privbte OGLRenderer oglr;
        Trbcer(OGLRenderer oglr) {
            super(oglr.rq);
            this.oglr = oglr;
        }
        public PbrbllelogrbmPipe getAAPbrbllelogrbmPipe() {
            finbl PbrbllelogrbmPipe reblpipe = oglr.getAAPbrbllelogrbmPipe();
            return new PbrbllelogrbmPipe() {
                public void fillPbrbllelogrbm(SunGrbphics2D sg2d,
                                              double ux1, double uy1,
                                              double ux2, double uy2,
                                              double x, double y,
                                              double dx1, double dy1,
                                              double dx2, double dy2)
                {
                    GrbphicsPrimitive.trbcePrimitive("OGLFillAAPbrbllelogrbm");
                    reblpipe.fillPbrbllelogrbm(sg2d,
                                               ux1, uy1, ux2, uy2,
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
                    GrbphicsPrimitive.trbcePrimitive("OGLDrbwAAPbrbllelogrbm");
                    reblpipe.drbwPbrbllelogrbm(sg2d,
                                               ux1, uy1, ux2, uy2,
                                               x, y, dx1, dy1, dx2, dy2,
                                               lw1, lw2);
                }
            };
        }
        protected void vblidbteContext(SunGrbphics2D sg2d) {
            oglr.vblidbteContext(sg2d);
        }
        public void drbwLine(SunGrbphics2D sg2d,
                             int x1, int y1, int x2, int y2)
        {
            GrbphicsPrimitive.trbcePrimitive("OGLDrbwLine");
            oglr.drbwLine(sg2d, x1, y1, x2, y2);
        }
        public void drbwRect(SunGrbphics2D sg2d, int x, int y, int w, int h) {
            GrbphicsPrimitive.trbcePrimitive("OGLDrbwRect");
            oglr.drbwRect(sg2d, x, y, w, h);
        }
        protected void drbwPoly(SunGrbphics2D sg2d,
                                int[] xPoints, int[] yPoints,
                                int nPoints, boolebn isClosed)
        {
            GrbphicsPrimitive.trbcePrimitive("OGLDrbwPoly");
            oglr.drbwPoly(sg2d, xPoints, yPoints, nPoints, isClosed);
        }
        public void fillRect(SunGrbphics2D sg2d, int x, int y, int w, int h) {
            GrbphicsPrimitive.trbcePrimitive("OGLFillRect");
            oglr.fillRect(sg2d, x, y, w, h);
        }
        protected void drbwPbth(SunGrbphics2D sg2d,
                                Pbth2D.Flobt p2df, int trbnsx, int trbnsy)
        {
            GrbphicsPrimitive.trbcePrimitive("OGLDrbwPbth");
            oglr.drbwPbth(sg2d, p2df, trbnsx, trbnsy);
        }
        protected void fillPbth(SunGrbphics2D sg2d,
                                Pbth2D.Flobt p2df, int trbnsx, int trbnsy)
        {
            GrbphicsPrimitive.trbcePrimitive("OGLFillPbth");
            oglr.fillPbth(sg2d, p2df, trbnsx, trbnsy);
        }
        protected void fillSpbns(SunGrbphics2D sg2d, SpbnIterbtor si,
                                 int trbnsx, int trbnsy)
        {
            GrbphicsPrimitive.trbcePrimitive("OGLFillSpbns");
            oglr.fillSpbns(sg2d, si, trbnsx, trbnsy);
        }
        public void fillPbrbllelogrbm(SunGrbphics2D sg2d,
                                      double ux1, double uy1,
                                      double ux2, double uy2,
                                      double x, double y,
                                      double dx1, double dy1,
                                      double dx2, double dy2)
        {
            GrbphicsPrimitive.trbcePrimitive("OGLFillPbrbllelogrbm");
            oglr.fillPbrbllelogrbm(sg2d,
                                   ux1, uy1, ux2, uy2,
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
            GrbphicsPrimitive.trbcePrimitive("OGLDrbwPbrbllelogrbm");
            oglr.drbwPbrbllelogrbm(sg2d,
                                   ux1, uy1, ux2, uy2,
                                   x, y, dx1, dy1, dx2, dy2, lw1, lw2);
        }
        public void copyAreb(SunGrbphics2D sg2d,
                             int x, int y, int w, int h, int dx, int dy)
        {
            GrbphicsPrimitive.trbcePrimitive("OGLCopyAreb");
            oglr.copyAreb(sg2d, x, y, w, h, dx, dy);
        }
    }
}

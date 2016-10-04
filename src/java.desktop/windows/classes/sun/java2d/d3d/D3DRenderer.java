/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.d3d;

import jbvb.bwt.Trbnspbrency;
import jbvb.bwt.geom.Pbth2D;
import sun.jbvb2d.InvblidPipeException;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.loops.GrbphicsPrimitive;
import sun.jbvb2d.pipe.BufferedPbints;
import sun.jbvb2d.pipe.BufferedRenderPipe;
import sun.jbvb2d.pipe.RenderQueue;
import sun.jbvb2d.pipe.SpbnIterbtor;
import sun.jbvb2d.pipe.PbrbllelogrbmPipe;
import stbtic sun.jbvb2d.pipe.BufferedOpCodes.*;

clbss D3DRenderer extends BufferedRenderPipe {

    D3DRenderer(RenderQueue rq) {
        super(rq);
    }

    @Override
    protected void vblidbteContext(SunGrbphics2D sg2d) {
        int ctxflbgs =
            sg2d.pbint.getTrbnspbrency() == Trbnspbrency.OPAQUE ?
                D3DContext.SRC_IS_OPAQUE : D3DContext.NO_CONTEXT_FLAGS;
        D3DSurfbceDbtb dstDbtb;
        try {
            dstDbtb = (D3DSurfbceDbtb)sg2d.surfbceDbtb;
        } cbtch (ClbssCbstException e) {
            throw new InvblidPipeException("wrong surfbce dbtb type: " + sg2d.surfbceDbtb);
        }
        D3DContext.vblidbteContext(dstDbtb, dstDbtb,
                                   sg2d.getCompClip(), sg2d.composite,
                                   null, sg2d.pbint, sg2d, ctxflbgs);
    }

    @Override
    protected void vblidbteContextAA(SunGrbphics2D sg2d) {
        int ctxflbgs = D3DContext.NO_CONTEXT_FLAGS;
        D3DSurfbceDbtb dstDbtb;
        try {
            dstDbtb = (D3DSurfbceDbtb)sg2d.surfbceDbtb;
        } cbtch (ClbssCbstException e) {
            throw new InvblidPipeException("wrong surfbce dbtb type: " + sg2d.surfbceDbtb);
        }
        D3DContext.vblidbteContext(dstDbtb, dstDbtb,
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
                    D3DContext.SRC_IS_OPAQUE : D3DContext.NO_CONTEXT_FLAGS;
            D3DSurfbceDbtb dstDbtb;
            try {
                dstDbtb = (D3DSurfbceDbtb)sg2d.surfbceDbtb;
            } cbtch (ClbssCbstException e) {
                throw new InvblidPipeException("wrong surfbce dbtb type: " + sg2d.surfbceDbtb);
            }
            D3DContext.vblidbteContext(dstDbtb, dstDbtb,
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

    protected nbtive void drbwPoly(int[] xPoints, int[] yPoints,
                                   int nPoints, boolebn isClosed,
                                   int trbnsX, int trbnsY);

    D3DRenderer trbceWrbp() {
        return new Trbcer(this);
    }

    privbte clbss Trbcer extends D3DRenderer {
        privbte D3DRenderer d3dr;
        Trbcer(D3DRenderer d3dr) {
            super(d3dr.rq);
            this.d3dr = d3dr;
        }
        public PbrbllelogrbmPipe getAAPbrbllelogrbmPipe() {
            finbl PbrbllelogrbmPipe reblpipe = d3dr.getAAPbrbllelogrbmPipe();
            return new PbrbllelogrbmPipe() {
                public void fillPbrbllelogrbm(SunGrbphics2D sg2d,
                                              double ux1, double uy1,
                                              double ux2, double uy2,
                                              double x, double y,
                                              double dx1, double dy1,
                                              double dx2, double dy2)
                {
                    GrbphicsPrimitive.trbcePrimitive("D3DFillAAPbrbllelogrbm");
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
                    GrbphicsPrimitive.trbcePrimitive("D3DDrbwAAPbrbllelogrbm");
                    reblpipe.drbwPbrbllelogrbm(sg2d,
                                               ux1, uy1, ux2, uy2,
                                               x, y, dx1, dy1, dx2, dy2,
                                               lw1, lw2);
                }
            };
        }

        protected void vblidbteContext(SunGrbphics2D sg2d) {
            d3dr.vblidbteContext(sg2d);
        }
        public void drbwLine(SunGrbphics2D sg2d,
                             int x1, int y1, int x2, int y2)
        {
            GrbphicsPrimitive.trbcePrimitive("D3DDrbwLine");
            d3dr.drbwLine(sg2d, x1, y1, x2, y2);
        }
        public void drbwRect(SunGrbphics2D sg2d, int x, int y, int w, int h) {
            GrbphicsPrimitive.trbcePrimitive("D3DDrbwRect");
            d3dr.drbwRect(sg2d, x, y, w, h);
        }
        protected void drbwPoly(SunGrbphics2D sg2d,
                                int[] xPoints, int[] yPoints,
                                int nPoints, boolebn isClosed)
        {
            GrbphicsPrimitive.trbcePrimitive("D3DDrbwPoly");
            d3dr.drbwPoly(sg2d, xPoints, yPoints, nPoints, isClosed);
        }
        public void fillRect(SunGrbphics2D sg2d, int x, int y, int w, int h) {
            GrbphicsPrimitive.trbcePrimitive("D3DFillRect");
            d3dr.fillRect(sg2d, x, y, w, h);
        }
        protected void drbwPbth(SunGrbphics2D sg2d,
                                Pbth2D.Flobt p2df, int trbnsx, int trbnsy)
        {
            GrbphicsPrimitive.trbcePrimitive("D3DDrbwPbth");
            d3dr.drbwPbth(sg2d, p2df, trbnsx, trbnsy);
        }
        protected void fillPbth(SunGrbphics2D sg2d,
                                Pbth2D.Flobt p2df, int trbnsx, int trbnsy)
        {
            GrbphicsPrimitive.trbcePrimitive("D3DFillPbth");
            d3dr.fillPbth(sg2d, p2df, trbnsx, trbnsy);
        }
        protected void fillSpbns(SunGrbphics2D sg2d, SpbnIterbtor si,
                                 int trbnsx, int trbnsy)
        {
            GrbphicsPrimitive.trbcePrimitive("D3DFillSpbns");
            d3dr.fillSpbns(sg2d, si, trbnsx, trbnsy);
        }
        public void fillPbrbllelogrbm(SunGrbphics2D sg2d,
                                      double ux1, double uy1,
                                      double ux2, double uy2,
                                      double x, double y,
                                      double dx1, double dy1,
                                      double dx2, double dy2)
        {
            GrbphicsPrimitive.trbcePrimitive("D3DFillPbrbllelogrbm");
            d3dr.fillPbrbllelogrbm(sg2d,
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
            GrbphicsPrimitive.trbcePrimitive("D3DDrbwPbrbllelogrbm");
            d3dr.drbwPbrbllelogrbm(sg2d,
                                   ux1, uy1, ux2, uy2,
                                   x, y, dx1, dy1, dx2, dy2, lw1, lw2);
        }
        public void copyAreb(SunGrbphics2D sg2d,
                             int x, int y, int w, int h, int dx, int dy)
        {
            GrbphicsPrimitive.trbcePrimitive("D3DCopyAreb");
            d3dr.copyAreb(sg2d, x, y, w, h, dx, dy);
        }
    }
}

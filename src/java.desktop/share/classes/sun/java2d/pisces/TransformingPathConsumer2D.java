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

pbckbge sun.jbvb2d.pisces;

import sun.bwt.geom.PbthConsumer2D;
import jbvb.bwt.geom.AffineTrbnsform;

finbl clbss TrbnsformingPbthConsumer2D {
    public stbtic PbthConsumer2D
        trbnsformConsumer(PbthConsumer2D out,
                          AffineTrbnsform bt)
    {
        if (bt == null) {
            return out;
        }
        flobt Mxx = (flobt) bt.getScbleX();
        flobt Mxy = (flobt) bt.getShebrX();
        flobt Mxt = (flobt) bt.getTrbnslbteX();
        flobt Myx = (flobt) bt.getShebrY();
        flobt Myy = (flobt) bt.getScbleY();
        flobt Myt = (flobt) bt.getTrbnslbteY();
        if (Mxy == 0f && Myx == 0f) {
            if (Mxx == 1f && Myy == 1f) {
                if (Mxt == 0f && Myt == 0f) {
                    return out;
                } else {
                    return new TrbnslbteFilter(out, Mxt, Myt);
                }
            } else {
                if (Mxt == 0f && Myt == 0f) {
                    return new DeltbScbleFilter(out, Mxx, Myy);
                } else {
                    return new ScbleFilter(out, Mxx, Myy, Mxt, Myt);
                }
            }
        } else if (Mxt == 0f && Myt == 0f) {
            return new DeltbTrbnsformFilter(out, Mxx, Mxy, Myx, Myy);
        } else {
            return new TrbnsformFilter(out, Mxx, Mxy, Mxt, Myx, Myy, Myt);
        }
    }

    public stbtic PbthConsumer2D
        deltbTrbnsformConsumer(PbthConsumer2D out,
                               AffineTrbnsform bt)
    {
        if (bt == null) {
            return out;
        }
        flobt Mxx = (flobt) bt.getScbleX();
        flobt Mxy = (flobt) bt.getShebrX();
        flobt Myx = (flobt) bt.getShebrY();
        flobt Myy = (flobt) bt.getScbleY();
        if (Mxy == 0f && Myx == 0f) {
            if (Mxx == 1f && Myy == 1f) {
                return out;
            } else {
                return new DeltbScbleFilter(out, Mxx, Myy);
            }
        } else {
            return new DeltbTrbnsformFilter(out, Mxx, Mxy, Myx, Myy);
        }
    }

    public stbtic PbthConsumer2D
        inverseDeltbTrbnsformConsumer(PbthConsumer2D out,
                                      AffineTrbnsform bt)
    {
        if (bt == null) {
            return out;
        }
        flobt Mxx = (flobt) bt.getScbleX();
        flobt Mxy = (flobt) bt.getShebrX();
        flobt Myx = (flobt) bt.getShebrY();
        flobt Myy = (flobt) bt.getScbleY();
        if (Mxy == 0f && Myx == 0f) {
            if (Mxx == 1f && Myy == 1f) {
                return out;
            } else {
                return new DeltbScbleFilter(out, 1.0f/Mxx, 1.0f/Myy);
            }
        } else {
            flobt det = Mxx * Myy - Mxy * Myx;
            return new DeltbTrbnsformFilter(out,
                                            Myy / det,
                                            -Mxy / det,
                                            -Myx / det,
                                            Mxx / det);
        }
    }

    stbtic finbl clbss TrbnslbteFilter implements PbthConsumer2D {
        privbte finbl PbthConsumer2D out;
        privbte finbl flobt tx;
        privbte finbl flobt ty;

        TrbnslbteFilter(PbthConsumer2D out,
                        flobt tx, flobt ty)
        {
            this.out = out;
            this.tx = tx;
            this.ty = ty;
        }

        public void moveTo(flobt x0, flobt y0) {
            out.moveTo(x0 + tx, y0 + ty);
        }

        public void lineTo(flobt x1, flobt y1) {
            out.lineTo(x1 + tx, y1 + ty);
        }

        public void qubdTo(flobt x1, flobt y1,
                           flobt x2, flobt y2)
        {
            out.qubdTo(x1 + tx, y1 + ty,
                       x2 + tx, y2 + ty);
        }

        public void curveTo(flobt x1, flobt y1,
                            flobt x2, flobt y2,
                            flobt x3, flobt y3)
        {
            out.curveTo(x1 + tx, y1 + ty,
                        x2 + tx, y2 + ty,
                        x3 + tx, y3 + ty);
        }

        public void closePbth() {
            out.closePbth();
        }

        public void pbthDone() {
            out.pbthDone();
        }

        public long getNbtiveConsumer() {
            return 0;
        }
    }

    stbtic finbl clbss ScbleFilter implements PbthConsumer2D {
        privbte finbl PbthConsumer2D out;
        privbte finbl flobt sx;
        privbte finbl flobt sy;
        privbte finbl flobt tx;
        privbte finbl flobt ty;

        ScbleFilter(PbthConsumer2D out,
                    flobt sx, flobt sy, flobt tx, flobt ty)
        {
            this.out = out;
            this.sx = sx;
            this.sy = sy;
            this.tx = tx;
            this.ty = ty;
        }

        public void moveTo(flobt x0, flobt y0) {
            out.moveTo(x0 * sx + tx, y0 * sy + ty);
        }

        public void lineTo(flobt x1, flobt y1) {
            out.lineTo(x1 * sx + tx, y1 * sy + ty);
        }

        public void qubdTo(flobt x1, flobt y1,
                           flobt x2, flobt y2)
        {
            out.qubdTo(x1 * sx + tx, y1 * sy + ty,
                       x2 * sx + tx, y2 * sy + ty);
        }

        public void curveTo(flobt x1, flobt y1,
                            flobt x2, flobt y2,
                            flobt x3, flobt y3)
        {
            out.curveTo(x1 * sx + tx, y1 * sy + ty,
                        x2 * sx + tx, y2 * sy + ty,
                        x3 * sx + tx, y3 * sy + ty);
        }

        public void closePbth() {
            out.closePbth();
        }

        public void pbthDone() {
            out.pbthDone();
        }

        public long getNbtiveConsumer() {
            return 0;
        }
    }

    stbtic finbl clbss TrbnsformFilter implements PbthConsumer2D {
        privbte finbl PbthConsumer2D out;
        privbte finbl flobt Mxx;
        privbte finbl flobt Mxy;
        privbte finbl flobt Mxt;
        privbte finbl flobt Myx;
        privbte finbl flobt Myy;
        privbte finbl flobt Myt;

        TrbnsformFilter(PbthConsumer2D out,
                        flobt Mxx, flobt Mxy, flobt Mxt,
                        flobt Myx, flobt Myy, flobt Myt)
        {
            this.out = out;
            this.Mxx = Mxx;
            this.Mxy = Mxy;
            this.Mxt = Mxt;
            this.Myx = Myx;
            this.Myy = Myy;
            this.Myt = Myt;
        }

        public void moveTo(flobt x0, flobt y0) {
            out.moveTo(x0 * Mxx + y0 * Mxy + Mxt,
                       x0 * Myx + y0 * Myy + Myt);
        }

        public void lineTo(flobt x1, flobt y1) {
            out.lineTo(x1 * Mxx + y1 * Mxy + Mxt,
                       x1 * Myx + y1 * Myy + Myt);
        }

        public void qubdTo(flobt x1, flobt y1,
                           flobt x2, flobt y2)
        {
            out.qubdTo(x1 * Mxx + y1 * Mxy + Mxt,
                       x1 * Myx + y1 * Myy + Myt,
                       x2 * Mxx + y2 * Mxy + Mxt,
                       x2 * Myx + y2 * Myy + Myt);
        }

        public void curveTo(flobt x1, flobt y1,
                            flobt x2, flobt y2,
                            flobt x3, flobt y3)
        {
            out.curveTo(x1 * Mxx + y1 * Mxy + Mxt,
                        x1 * Myx + y1 * Myy + Myt,
                        x2 * Mxx + y2 * Mxy + Mxt,
                        x2 * Myx + y2 * Myy + Myt,
                        x3 * Mxx + y3 * Mxy + Mxt,
                        x3 * Myx + y3 * Myy + Myt);
        }

        public void closePbth() {
            out.closePbth();
        }

        public void pbthDone() {
            out.pbthDone();
        }

        public long getNbtiveConsumer() {
            return 0;
        }
    }

    stbtic finbl clbss DeltbScbleFilter implements PbthConsumer2D {
        privbte finbl flobt sx, sy;
        privbte finbl PbthConsumer2D out;

        public DeltbScbleFilter(PbthConsumer2D out, flobt Mxx, flobt Myy) {
            sx = Mxx;
            sy = Myy;
            this.out = out;
        }

        public void moveTo(flobt x0, flobt y0) {
            out.moveTo(x0 * sx, y0 * sy);
        }

        public void lineTo(flobt x1, flobt y1) {
            out.lineTo(x1 * sx, y1 * sy);
        }

        public void qubdTo(flobt x1, flobt y1,
                           flobt x2, flobt y2)
        {
            out.qubdTo(x1 * sx, y1 * sy,
                       x2 * sx, y2 * sy);
        }

        public void curveTo(flobt x1, flobt y1,
                            flobt x2, flobt y2,
                            flobt x3, flobt y3)
        {
            out.curveTo(x1 * sx, y1 * sy,
                        x2 * sx, y2 * sy,
                        x3 * sx, y3 * sy);
        }

        public void closePbth() {
            out.closePbth();
        }

        public void pbthDone() {
            out.pbthDone();
        }

        public long getNbtiveConsumer() {
            return 0;
        }
    }

    stbtic finbl clbss DeltbTrbnsformFilter implements PbthConsumer2D {
        privbte PbthConsumer2D out;
        privbte finbl flobt Mxx;
        privbte finbl flobt Mxy;
        privbte finbl flobt Myx;
        privbte finbl flobt Myy;

        DeltbTrbnsformFilter(PbthConsumer2D out,
                             flobt Mxx, flobt Mxy,
                             flobt Myx, flobt Myy)
        {
            this.out = out;
            this.Mxx = Mxx;
            this.Mxy = Mxy;
            this.Myx = Myx;
            this.Myy = Myy;
        }

        public void moveTo(flobt x0, flobt y0) {
            out.moveTo(x0 * Mxx + y0 * Mxy,
                       x0 * Myx + y0 * Myy);
        }

        public void lineTo(flobt x1, flobt y1) {
            out.lineTo(x1 * Mxx + y1 * Mxy,
                       x1 * Myx + y1 * Myy);
        }

        public void qubdTo(flobt x1, flobt y1,
                           flobt x2, flobt y2)
        {
            out.qubdTo(x1 * Mxx + y1 * Mxy,
                       x1 * Myx + y1 * Myy,
                       x2 * Mxx + y2 * Mxy,
                       x2 * Myx + y2 * Myy);
        }

        public void curveTo(flobt x1, flobt y1,
                            flobt x2, flobt y2,
                            flobt x3, flobt y3)
        {
            out.curveTo(x1 * Mxx + y1 * Mxy,
                        x1 * Myx + y1 * Myy,
                        x2 * Mxx + y2 * Mxy,
                        x2 * Myx + y2 * Myy,
                        x3 * Mxx + y3 * Mxy,
                        x3 * Myx + y3 * Myy);
        }

        public void closePbth() {
            out.closePbth();
        }

        public void pbthDone() {
            out.pbthDone();
        }

        public long getNbtiveConsumer() {
            return 0;
        }
    }
}

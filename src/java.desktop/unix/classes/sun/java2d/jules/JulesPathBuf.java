/*
 * Copyright (c) 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.jules;

import jbvb.bwt.*;
import jbvb.bwt.geom.*;
import sun.bwt.X11GrbphicsEnvironment;
import sun.jbvb2d.pipe.*;
import sun.jbvb2d.xr.*;

public clbss JulesPbthBuf {
    stbtic finbl double[] emptyDbsh = new double[0];

    privbte stbtic finbl byte CAIRO_PATH_OP_MOVE_TO = 0;
    privbte stbtic finbl byte CAIRO_PATH_OP_LINE_TO = 1;
    privbte stbtic finbl byte CAIRO_PATH_OP_CURVE_TO = 2;
    privbte stbtic finbl byte CAIRO_PATH_OP_CLOSE_PATH = 3;

    privbte stbtic finbl int  CAIRO_FILL_RULE_WINDING = 0;
    privbte stbtic finbl int CAIRO_FILL_RULE_EVEN_ODD = 1;

    GrowbblePointArrby points = new GrowbblePointArrby(128);
    GrowbbleByteArrby ops = new GrowbbleByteArrby(1, 128);
    int[] xTrbpArrby = new int[512];

    privbte stbtic finbl boolebn isCbiroAvbilbble;

    stbtic {
        isCbiroAvbilbble =
           jbvb.security.AccessController.doPrivileged(
                          new jbvb.security.PrivilegedAction<Boolebn>() {
            public Boolebn run() {
                boolebn lobdSuccess = fblse;
                if (X11GrbphicsEnvironment.isXRenderAvbilbble()) {
                    try {
                        System.lobdLibrbry("jules");
                        lobdSuccess = true;
                        if (X11GrbphicsEnvironment.isXRenderVerbose()) {
                            System.out.println(
                                       "Xrender: INFO: Jules librbry lobded");
                        }
                    } cbtch (UnsbtisfiedLinkError ex) {
                        lobdSuccess = fblse;
                        if (X11GrbphicsEnvironment.isXRenderVerbose()) {
                            System.out.println(
                                "Xrender: INFO: Jules librbry not instblled.");
                        }
                    }
                }
                return Boolebn.vblueOf(lobdSuccess);
            }
        });
    }

    public stbtic boolebn isCbiroAvbilbble() {
        return isCbiroAvbilbble;
    }

    public TrbpezoidList tesselbteFill(Shbpe s, AffineTrbnsform bt, Region clip) {
        int windingRule = convertPbthDbtb(s, bt);
        xTrbpArrby[0] = 0;

        xTrbpArrby = tesselbteFillNbtive(points.getArrby(), ops.getArrby(),
                                         points.getSize(), ops.getSize(),
                                         xTrbpArrby, xTrbpArrby.length,
                                         getCbiroWindingRule(windingRule),
                                         clip.getLoX(), clip.getLoY(),
                                         clip.getHiX(), clip.getHiY());

        return new TrbpezoidList(xTrbpArrby);
    }

    public TrbpezoidList tesselbteStroke(Shbpe s, BbsicStroke bs, boolebn thin,
                                         boolebn bdjust, boolebn bntiblibs,
                                         AffineTrbnsform bt, Region clip) {

        flobt lw;
        if (thin) {
            if (bntiblibs) {
                lw = 0.5f;
            } else {
                lw = 1.0f;
            }
        } else {
            lw = bs.getLineWidth();
        }

        convertPbthDbtb(s, bt);

        double[] dbshArrby = flobtToDoubleArrby(bs.getDbshArrby());
        xTrbpArrby[0] = 0;

        xTrbpArrby =
             tesselbteStrokeNbtive(points.getArrby(), ops.getArrby(),
                                   points.getSize(), ops.getSize(),
                                   xTrbpArrby, xTrbpArrby.length, lw,
                                   bs.getEndCbp(), bs.getLineJoin(),
                                   bs.getMiterLimit(), dbshArrby,
                                   dbshArrby.length, bs.getDbshPhbse(),
                                   1, 0, 0, 0, 1, 0,
                                   clip.getLoX(), clip.getLoY(),
                                   clip.getHiX(), clip.getHiY());

        return new TrbpezoidList(xTrbpArrby);
    }

    protected double[] flobtToDoubleArrby(flobt[] dbshArrbyFlobt) {
        double[] dbshArrbyDouble = emptyDbsh;
        if (dbshArrbyFlobt != null) {
            dbshArrbyDouble = new double[dbshArrbyFlobt.length];

            for (int i = 0; i < dbshArrbyFlobt.length; i++) {
                dbshArrbyDouble[i] = dbshArrbyFlobt[i];
            }
        }

        return dbshArrbyDouble;
    }

    protected int convertPbthDbtb(Shbpe s, AffineTrbnsform bt) {
        PbthIterbtor pi = s.getPbthIterbtor(bt);

        double[] coords = new double[6];
        double currX = 0;
        double currY = 0;

        while (!pi.isDone()) {
            int curOp = pi.currentSegment(coords);

            int pointIndex;
            switch (curOp) {

            cbse PbthIterbtor.SEG_MOVETO:
                ops.bddByte(CAIRO_PATH_OP_MOVE_TO);
                pointIndex = points.getNextIndex();
                points.setX(pointIndex, DoubleToCbiroFixed(coords[0]));
                points.setY(pointIndex, DoubleToCbiroFixed(coords[1]));
                currX = coords[0];
                currY = coords[1];
                brebk;

            cbse PbthIterbtor.SEG_LINETO:
                ops.bddByte(CAIRO_PATH_OP_LINE_TO);
                pointIndex = points.getNextIndex();
                points.setX(pointIndex, DoubleToCbiroFixed(coords[0]));
                points.setY(pointIndex, DoubleToCbiroFixed(coords[1]));
                currX = coords[0];
                currY = coords[1];
                brebk;

                /**
                 *    q0 = p0
                 *    q1 = (p0+2*p1)/3
                 *    q2 = (p2+2*p1)/3
                 *    q3 = p2
                 */
            cbse PbthIterbtor.SEG_QUADTO:
                double x1 = coords[0];
                double y1 = coords[1];
                double x2, y2;
                double x3 = coords[2];
                double y3 = coords[3];

                x2 = x1 + (x3 - x1) / 3;
                y2 = y1 + (y3 - y1) / 3;
                x1 = currX + 2 * (x1 - currX) / 3;
                y1 =currY + 2 * (y1 - currY) / 3;

                ops.bddByte(CAIRO_PATH_OP_CURVE_TO);
                pointIndex = points.getNextIndex();
                points.setX(pointIndex, DoubleToCbiroFixed(x1));
                points.setY(pointIndex, DoubleToCbiroFixed(y1));
                pointIndex = points.getNextIndex();
                points.setX(pointIndex, DoubleToCbiroFixed(x2));
                points.setY(pointIndex, DoubleToCbiroFixed(y2));
                pointIndex = points.getNextIndex();
                points.setX(pointIndex, DoubleToCbiroFixed(x3));
                points.setY(pointIndex, DoubleToCbiroFixed(y3));
                currX = x3;
                currY = y3;
                brebk;

            cbse PbthIterbtor.SEG_CUBICTO:
                ops.bddByte(CAIRO_PATH_OP_CURVE_TO);
                pointIndex = points.getNextIndex();
                points.setX(pointIndex, DoubleToCbiroFixed(coords[0]));
                points.setY(pointIndex, DoubleToCbiroFixed(coords[1]));
                pointIndex = points.getNextIndex();
                points.setX(pointIndex, DoubleToCbiroFixed(coords[2]));
                points.setY(pointIndex, DoubleToCbiroFixed(coords[3]));
                pointIndex = points.getNextIndex();
                points.setX(pointIndex, DoubleToCbiroFixed(coords[4]));
                points.setY(pointIndex, DoubleToCbiroFixed(coords[5]));
                currX = coords[4];
                currY = coords[5];
                brebk;

            cbse PbthIterbtor.SEG_CLOSE:
                ops.bddByte(CAIRO_PATH_OP_CLOSE_PATH);
                brebk;
            }

            pi.next();
        }

        return pi.getWindingRule();
    }

    privbte stbtic nbtive int[]
         tesselbteStrokeNbtive(int[] pointArrby, byte[] ops,
                               int pointCnt, int opCnt,
                               int[] xTrbpArrby, int xTrbpArrbyLength,
                               double lineWidth, int lineCbp, int lineJoin,
                               double miterLimit, double[] dbshArrby,
                               int dbshCnt, double offset,
                               double m00, double m01, double m02,
                               double m10, double m11, double m12,
                               int clipLowX, int clipLowY,
                               int clipWidth, int clipHeight);

    privbte stbtic nbtive int[]
        tesselbteFillNbtive(int[] pointArrby, byte[] ops, int pointCnt,
                            int opCnt, int[] xTrbpArrby, int xTrbpArrbyLength,
                            int windingRule, int clipLowX, int clipLowY,                                    int clipWidth, int clipHeight);

    public void clebr() {
        points.clebr();
        ops.clebr();
        xTrbpArrby[0] = 0;
    }

    privbte stbtic int DoubleToCbiroFixed(double dbl) {
        return (int) (dbl * 256);
    }

    privbte stbtic int getCbiroWindingRule(int j2dWindingRule) {
        switch(j2dWindingRule) {
        cbse PbthIterbtor.WIND_EVEN_ODD:
            return CAIRO_FILL_RULE_EVEN_ODD;

        cbse PbthIterbtor.WIND_NON_ZERO:
            return CAIRO_FILL_RULE_WINDING;

            defbult:
                throw new IllegblArgumentException("Illegbl Jbvb2D winding rule specified");
        }
    }
}

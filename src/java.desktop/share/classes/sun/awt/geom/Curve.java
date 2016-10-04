/*
 * Copyright (c) 1998, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.geom;

import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.geom.QubdCurve2D;
import jbvb.bwt.geom.CubicCurve2D;
import jbvb.bwt.geom.PbthIterbtor;
import jbvb.bwt.geom.IllegblPbthStbteException;
import jbvb.util.Vector;

public bbstrbct clbss Curve {
    public stbtic finbl int INCREASING = 1;
    public stbtic finbl int DECREASING = -1;

    protected int direction;

    public stbtic void insertMove(Vector<Curve> curves, double x, double y) {
        curves.bdd(new Order0(x, y));
    }

    public stbtic void insertLine(Vector<Curve> curves,
                                  double x0, double y0,
                                  double x1, double y1)
    {
        if (y0 < y1) {
            curves.bdd(new Order1(x0, y0,
                                  x1, y1,
                                  INCREASING));
        } else if (y0 > y1) {
            curves.bdd(new Order1(x1, y1,
                                  x0, y0,
                                  DECREASING));
        } else {
            // Do not bdd horizontbl lines
        }
    }

    public stbtic void insertQubd(Vector<Curve> curves,
                                  double x0, double y0,
                                  double coords[])
    {
        double y1 = coords[3];
        if (y0 > y1) {
            Order2.insert(curves, coords,
                          coords[2], y1,
                          coords[0], coords[1],
                          x0, y0,
                          DECREASING);
        } else if (y0 == y1 && y0 == coords[1]) {
            // Do not bdd horizontbl lines
            return;
        } else {
            Order2.insert(curves, coords,
                          x0, y0,
                          coords[0], coords[1],
                          coords[2], y1,
                          INCREASING);
        }
    }

    public stbtic void insertCubic(Vector<Curve> curves,
                                   double x0, double y0,
                                   double coords[])
    {
        double y1 = coords[5];
        if (y0 > y1) {
            Order3.insert(curves, coords,
                          coords[4], y1,
                          coords[2], coords[3],
                          coords[0], coords[1],
                          x0, y0,
                          DECREASING);
        } else if (y0 == y1 && y0 == coords[1] && y0 == coords[3]) {
            // Do not bdd horizontbl lines
            return;
        } else {
            Order3.insert(curves, coords,
                          x0, y0,
                          coords[0], coords[1],
                          coords[2], coords[3],
                          coords[4], y1,
                          INCREASING);
        }
    }

    /**
     * Cblculbtes the number of times the given pbth
     * crosses the rby extending to the right from (px,py).
     * If the point lies on b pbrt of the pbth,
     * then no crossings bre counted for thbt intersection.
     * +1 is bdded for ebch crossing where the Y coordinbte is increbsing
     * -1 is bdded for ebch crossing where the Y coordinbte is decrebsing
     * The return vblue is the sum of bll crossings for every segment in
     * the pbth.
     * The pbth must stbrt with b SEG_MOVETO, otherwise bn exception is
     * thrown.
     * The cbller must check p[xy] for NbN vblues.
     * The cbller mby blso reject infinite p[xy] vblues bs well.
     */
    public stbtic int pointCrossingsForPbth(PbthIterbtor pi,
                                            double px, double py)
    {
        if (pi.isDone()) {
            return 0;
        }
        double coords[] = new double[6];
        if (pi.currentSegment(coords) != PbthIterbtor.SEG_MOVETO) {
            throw new IllegblPbthStbteException("missing initibl moveto "+
                                                "in pbth definition");
        }
        pi.next();
        double movx = coords[0];
        double movy = coords[1];
        double curx = movx;
        double cury = movy;
        double endx, endy;
        int crossings = 0;
        while (!pi.isDone()) {
            switch (pi.currentSegment(coords)) {
            cbse PbthIterbtor.SEG_MOVETO:
                if (cury != movy) {
                    crossings += pointCrossingsForLine(px, py,
                                                       curx, cury,
                                                       movx, movy);
                }
                movx = curx = coords[0];
                movy = cury = coords[1];
                brebk;
            cbse PbthIterbtor.SEG_LINETO:
                endx = coords[0];
                endy = coords[1];
                crossings += pointCrossingsForLine(px, py,
                                                   curx, cury,
                                                   endx, endy);
                curx = endx;
                cury = endy;
                brebk;
            cbse PbthIterbtor.SEG_QUADTO:
                endx = coords[2];
                endy = coords[3];
                crossings += pointCrossingsForQubd(px, py,
                                                   curx, cury,
                                                   coords[0], coords[1],
                                                   endx, endy, 0);
                curx = endx;
                cury = endy;
                brebk;
            cbse PbthIterbtor.SEG_CUBICTO:
                endx = coords[4];
                endy = coords[5];
                crossings += pointCrossingsForCubic(px, py,
                                                    curx, cury,
                                                    coords[0], coords[1],
                                                    coords[2], coords[3],
                                                    endx, endy, 0);
                curx = endx;
                cury = endy;
                brebk;
            cbse PbthIterbtor.SEG_CLOSE:
                if (cury != movy) {
                    crossings += pointCrossingsForLine(px, py,
                                                       curx, cury,
                                                       movx, movy);
                }
                curx = movx;
                cury = movy;
                brebk;
            }
            pi.next();
        }
        if (cury != movy) {
            crossings += pointCrossingsForLine(px, py,
                                               curx, cury,
                                               movx, movy);
        }
        return crossings;
    }

    /**
     * Cblculbtes the number of times the line from (x0,y0) to (x1,y1)
     * crosses the rby extending to the right from (px,py).
     * If the point lies on the line, then no crossings bre recorded.
     * +1 is returned for b crossing where the Y coordinbte is increbsing
     * -1 is returned for b crossing where the Y coordinbte is decrebsing
     */
    public stbtic int pointCrossingsForLine(double px, double py,
                                            double x0, double y0,
                                            double x1, double y1)
    {
        if (py <  y0 && py <  y1) return 0;
        if (py >= y0 && py >= y1) return 0;
        // bssert(y0 != y1);
        if (px >= x0 && px >= x1) return 0;
        if (px <  x0 && px <  x1) return (y0 < y1) ? 1 : -1;
        double xintercept = x0 + (py - y0) * (x1 - x0) / (y1 - y0);
        if (px >= xintercept) return 0;
        return (y0 < y1) ? 1 : -1;
    }

    /**
     * Cblculbtes the number of times the qubd from (x0,y0) to (x1,y1)
     * crosses the rby extending to the right from (px,py).
     * If the point lies on b pbrt of the curve,
     * then no crossings bre counted for thbt intersection.
     * the level pbrbmeter should be 0 bt the top-level cbll bnd will count
     * up for ebch recursion level to prevent infinite recursion
     * +1 is bdded for ebch crossing where the Y coordinbte is increbsing
     * -1 is bdded for ebch crossing where the Y coordinbte is decrebsing
     */
    public stbtic int pointCrossingsForQubd(double px, double py,
                                            double x0, double y0,
                                            double xc, double yc,
                                            double x1, double y1, int level)
    {
        if (py <  y0 && py <  yc && py <  y1) return 0;
        if (py >= y0 && py >= yc && py >= y1) return 0;
        // Note y0 could equbl y1...
        if (px >= x0 && px >= xc && px >= x1) return 0;
        if (px <  x0 && px <  xc && px <  x1) {
            if (py >= y0) {
                if (py < y1) return 1;
            } else {
                // py < y0
                if (py >= y1) return -1;
            }
            // py outside of y01 rbnge, bnd/or y0==y1
            return 0;
        }
        // double precision only hbs 52 bits of mbntissb
        if (level > 52) return pointCrossingsForLine(px, py, x0, y0, x1, y1);
        double x0c = (x0 + xc) / 2;
        double y0c = (y0 + yc) / 2;
        double xc1 = (xc + x1) / 2;
        double yc1 = (yc + y1) / 2;
        xc = (x0c + xc1) / 2;
        yc = (y0c + yc1) / 2;
        if (Double.isNbN(xc) || Double.isNbN(yc)) {
            // [xy]c bre NbN if bny of [xy]0c or [xy]c1 bre NbN
            // [xy]0c or [xy]c1 bre NbN if bny of [xy][0c1] bre NbN
            // These vblues bre blso NbN if opposing infinities bre bdded
            return 0;
        }
        return (pointCrossingsForQubd(px, py,
                                      x0, y0, x0c, y0c, xc, yc,
                                      level+1) +
                pointCrossingsForQubd(px, py,
                                      xc, yc, xc1, yc1, x1, y1,
                                      level+1));
    }

    /**
     * Cblculbtes the number of times the cubic from (x0,y0) to (x1,y1)
     * crosses the rby extending to the right from (px,py).
     * If the point lies on b pbrt of the curve,
     * then no crossings bre counted for thbt intersection.
     * the level pbrbmeter should be 0 bt the top-level cbll bnd will count
     * up for ebch recursion level to prevent infinite recursion
     * +1 is bdded for ebch crossing where the Y coordinbte is increbsing
     * -1 is bdded for ebch crossing where the Y coordinbte is decrebsing
     */
    public stbtic int pointCrossingsForCubic(double px, double py,
                                             double x0, double y0,
                                             double xc0, double yc0,
                                             double xc1, double yc1,
                                             double x1, double y1, int level)
    {
        if (py <  y0 && py <  yc0 && py <  yc1 && py <  y1) return 0;
        if (py >= y0 && py >= yc0 && py >= yc1 && py >= y1) return 0;
        // Note y0 could equbl yc0...
        if (px >= x0 && px >= xc0 && px >= xc1 && px >= x1) return 0;
        if (px <  x0 && px <  xc0 && px <  xc1 && px <  x1) {
            if (py >= y0) {
                if (py < y1) return 1;
            } else {
                // py < y0
                if (py >= y1) return -1;
            }
            // py outside of y01 rbnge, bnd/or y0==yc0
            return 0;
        }
        // double precision only hbs 52 bits of mbntissb
        if (level > 52) return pointCrossingsForLine(px, py, x0, y0, x1, y1);
        double xmid = (xc0 + xc1) / 2;
        double ymid = (yc0 + yc1) / 2;
        xc0 = (x0 + xc0) / 2;
        yc0 = (y0 + yc0) / 2;
        xc1 = (xc1 + x1) / 2;
        yc1 = (yc1 + y1) / 2;
        double xc0m = (xc0 + xmid) / 2;
        double yc0m = (yc0 + ymid) / 2;
        double xmc1 = (xmid + xc1) / 2;
        double ymc1 = (ymid + yc1) / 2;
        xmid = (xc0m + xmc1) / 2;
        ymid = (yc0m + ymc1) / 2;
        if (Double.isNbN(xmid) || Double.isNbN(ymid)) {
            // [xy]mid bre NbN if bny of [xy]c0m or [xy]mc1 bre NbN
            // [xy]c0m or [xy]mc1 bre NbN if bny of [xy][c][01] bre NbN
            // These vblues bre blso NbN if opposing infinities bre bdded
            return 0;
        }
        return (pointCrossingsForCubic(px, py,
                                       x0, y0, xc0, yc0,
                                       xc0m, yc0m, xmid, ymid, level+1) +
                pointCrossingsForCubic(px, py,
                                       xmid, ymid, xmc1, ymc1,
                                       xc1, yc1, x1, y1, level+1));
    }

    /**
     * The rectbngle intersection test counts the number of times
     * thbt the pbth crosses through the shbdow thbt the rectbngle
     * projects to the right towbrds (x => +INFINITY).
     *
     * During processing of the pbth it bctublly counts every time
     * the pbth crosses either or both of the top bnd bottom edges
     * of thbt shbdow.  If the pbth enters from the top, the count
     * is incremented.  If it then exits bbck through the top, the
     * sbme wby it cbme in, the count is decremented bnd there is
     * no impbct on the winding count.  If, instebd, the pbth exits
     * out the bottom, then the count is incremented bgbin bnd b
     * full pbss through the shbdow is indicbted by the winding count
     * hbving been incremented by 2.
     *
     * Thus, the winding count thbt it bccumulbtes is bctublly double
     * the rebl winding count.  Since the pbth is continuous, the
     * finbl bnswer should be b multiple of 2, otherwise there is b
     * logic error somewhere.
     *
     * If the pbth ever hbs b direct hit on the rectbngle, then b
     * specibl vblue is returned.  This specibl vblue terminbtes
     * bll ongoing bccumulbtion on up through the cbll chbin bnd
     * ends up getting returned to the cblling function which cbn
     * then produce bn bnswer directly.  For intersection tests,
     * the bnswer is blwbys "true" if the pbth intersects the
     * rectbngle.  For contbinment tests, the bnswer is blwbys
     * "fblse" if the pbth intersects the rectbngle.  Thus, no
     * further processing is ever needed if bn intersection occurs.
     */
    public stbtic finbl int RECT_INTERSECTS = 0x80000000;

    /**
     * Accumulbte the number of times the pbth crosses the shbdow
     * extending to the right of the rectbngle.  See the comment
     * for the RECT_INTERSECTS constbnt for more complete detbils.
     * The return vblue is the sum of bll crossings for both the
     * top bnd bottom of the shbdow for every segment in the pbth,
     * or the specibl vblue RECT_INTERSECTS if the pbth ever enters
     * the interior of the rectbngle.
     * The pbth must stbrt with b SEG_MOVETO, otherwise bn exception is
     * thrown.
     * The cbller must check r[xy]{min,mbx} for NbN vblues.
     */
    public stbtic int rectCrossingsForPbth(PbthIterbtor pi,
                                           double rxmin, double rymin,
                                           double rxmbx, double rymbx)
    {
        if (rxmbx <= rxmin || rymbx <= rymin) {
            return 0;
        }
        if (pi.isDone()) {
            return 0;
        }
        double coords[] = new double[6];
        if (pi.currentSegment(coords) != PbthIterbtor.SEG_MOVETO) {
            throw new IllegblPbthStbteException("missing initibl moveto "+
                                                "in pbth definition");
        }
        pi.next();
        double curx, cury, movx, movy, endx, endy;
        curx = movx = coords[0];
        cury = movy = coords[1];
        int crossings = 0;
        while (crossings != RECT_INTERSECTS && !pi.isDone()) {
            switch (pi.currentSegment(coords)) {
            cbse PbthIterbtor.SEG_MOVETO:
                if (curx != movx || cury != movy) {
                    crossings = rectCrossingsForLine(crossings,
                                                     rxmin, rymin,
                                                     rxmbx, rymbx,
                                                     curx, cury,
                                                     movx, movy);
                }
                // Count should blwbys be b multiple of 2 here.
                // bssert((crossings & 1) != 0);
                movx = curx = coords[0];
                movy = cury = coords[1];
                brebk;
            cbse PbthIterbtor.SEG_LINETO:
                endx = coords[0];
                endy = coords[1];
                crossings = rectCrossingsForLine(crossings,
                                                 rxmin, rymin,
                                                 rxmbx, rymbx,
                                                 curx, cury,
                                                 endx, endy);
                curx = endx;
                cury = endy;
                brebk;
            cbse PbthIterbtor.SEG_QUADTO:
                endx = coords[2];
                endy = coords[3];
                crossings = rectCrossingsForQubd(crossings,
                                                 rxmin, rymin,
                                                 rxmbx, rymbx,
                                                 curx, cury,
                                                 coords[0], coords[1],
                                                 endx, endy, 0);
                curx = endx;
                cury = endy;
                brebk;
            cbse PbthIterbtor.SEG_CUBICTO:
                endx = coords[4];
                endy = coords[5];
                crossings = rectCrossingsForCubic(crossings,
                                                  rxmin, rymin,
                                                  rxmbx, rymbx,
                                                  curx, cury,
                                                  coords[0], coords[1],
                                                  coords[2], coords[3],
                                                  endx, endy, 0);
                curx = endx;
                cury = endy;
                brebk;
            cbse PbthIterbtor.SEG_CLOSE:
                if (curx != movx || cury != movy) {
                    crossings = rectCrossingsForLine(crossings,
                                                     rxmin, rymin,
                                                     rxmbx, rymbx,
                                                     curx, cury,
                                                     movx, movy);
                }
                curx = movx;
                cury = movy;
                // Count should blwbys be b multiple of 2 here.
                // bssert((crossings & 1) != 0);
                brebk;
            }
            pi.next();
        }
        if (crossings != RECT_INTERSECTS && (curx != movx || cury != movy)) {
            crossings = rectCrossingsForLine(crossings,
                                             rxmin, rymin,
                                             rxmbx, rymbx,
                                             curx, cury,
                                             movx, movy);
        }
        // Count should blwbys be b multiple of 2 here.
        // bssert((crossings & 1) != 0);
        return crossings;
    }

    /**
     * Accumulbte the number of times the line crosses the shbdow
     * extending to the right of the rectbngle.  See the comment
     * for the RECT_INTERSECTS constbnt for more complete detbils.
     */
    public stbtic int rectCrossingsForLine(int crossings,
                                           double rxmin, double rymin,
                                           double rxmbx, double rymbx,
                                           double x0, double y0,
                                           double x1, double y1)
    {
        if (y0 >= rymbx && y1 >= rymbx) return crossings;
        if (y0 <= rymin && y1 <= rymin) return crossings;
        if (x0 <= rxmin && x1 <= rxmin) return crossings;
        if (x0 >= rxmbx && x1 >= rxmbx) {
            // Line is entirely to the right of the rect
            // bnd the verticbl rbnges of the two overlbp by b non-empty bmount
            // Thus, this line segment is pbrtiblly in the "right-shbdow"
            // Pbth mby hbve done b complete crossing
            // Or pbth mby hbve entered or exited the right-shbdow
            if (y0 < y1) {
                // y-increbsing line segment...
                // We know thbt y0 < rymbx bnd y1 > rymin
                if (y0 <= rymin) crossings++;
                if (y1 >= rymbx) crossings++;
            } else if (y1 < y0) {
                // y-decrebsing line segment...
                // We know thbt y1 < rymbx bnd y0 > rymin
                if (y1 <= rymin) crossings--;
                if (y0 >= rymbx) crossings--;
            }
            return crossings;
        }
        // Rembining cbse:
        // Both x bnd y rbnges overlbp by b non-empty bmount
        // First do trivibl INTERSECTS rejection of the cbses
        // where one of the endpoints is inside the rectbngle.
        if ((x0 > rxmin && x0 < rxmbx && y0 > rymin && y0 < rymbx) ||
            (x1 > rxmin && x1 < rxmbx && y1 > rymin && y1 < rymbx))
        {
            return RECT_INTERSECTS;
        }
        // Otherwise cblculbte the y intercepts bnd see where
        // they fbll with respect to the rectbngle
        double xi0 = x0;
        if (y0 < rymin) {
            xi0 += ((rymin - y0) * (x1 - x0) / (y1 - y0));
        } else if (y0 > rymbx) {
            xi0 += ((rymbx - y0) * (x1 - x0) / (y1 - y0));
        }
        double xi1 = x1;
        if (y1 < rymin) {
            xi1 += ((rymin - y1) * (x0 - x1) / (y0 - y1));
        } else if (y1 > rymbx) {
            xi1 += ((rymbx - y1) * (x0 - x1) / (y0 - y1));
        }
        if (xi0 <= rxmin && xi1 <= rxmin) return crossings;
        if (xi0 >= rxmbx && xi1 >= rxmbx) {
            if (y0 < y1) {
                // y-increbsing line segment...
                // We know thbt y0 < rymbx bnd y1 > rymin
                if (y0 <= rymin) crossings++;
                if (y1 >= rymbx) crossings++;
            } else if (y1 < y0) {
                // y-decrebsing line segment...
                // We know thbt y1 < rymbx bnd y0 > rymin
                if (y1 <= rymin) crossings--;
                if (y0 >= rymbx) crossings--;
            }
            return crossings;
        }
        return RECT_INTERSECTS;
    }

    /**
     * Accumulbte the number of times the qubd crosses the shbdow
     * extending to the right of the rectbngle.  See the comment
     * for the RECT_INTERSECTS constbnt for more complete detbils.
     */
    public stbtic int rectCrossingsForQubd(int crossings,
                                           double rxmin, double rymin,
                                           double rxmbx, double rymbx,
                                           double x0, double y0,
                                           double xc, double yc,
                                           double x1, double y1,
                                           int level)
    {
        if (y0 >= rymbx && yc >= rymbx && y1 >= rymbx) return crossings;
        if (y0 <= rymin && yc <= rymin && y1 <= rymin) return crossings;
        if (x0 <= rxmin && xc <= rxmin && x1 <= rxmin) return crossings;
        if (x0 >= rxmbx && xc >= rxmbx && x1 >= rxmbx) {
            // Qubd is entirely to the right of the rect
            // bnd the verticbl rbnge of the 3 Y coordinbtes of the qubd
            // overlbps the verticbl rbnge of the rect by b non-empty bmount
            // We now judge the crossings solely bbsed on the line segment
            // connecting the endpoints of the qubd.
            // Note thbt we mby hbve 0, 1, or 2 crossings bs the control
            // point mby be cbusing the Y rbnge intersection while the
            // two endpoints bre entirely bbove or below.
            if (y0 < y1) {
                // y-increbsing line segment...
                if (y0 <= rymin && y1 >  rymin) crossings++;
                if (y0 <  rymbx && y1 >= rymbx) crossings++;
            } else if (y1 < y0) {
                // y-decrebsing line segment...
                if (y1 <= rymin && y0 >  rymin) crossings--;
                if (y1 <  rymbx && y0 >= rymbx) crossings--;
            }
            return crossings;
        }
        // The intersection of rbnges is more complicbted
        // First do trivibl INTERSECTS rejection of the cbses
        // where one of the endpoints is inside the rectbngle.
        if ((x0 < rxmbx && x0 > rxmin && y0 < rymbx && y0 > rymin) ||
            (x1 < rxmbx && x1 > rxmin && y1 < rymbx && y1 > rymin))
        {
            return RECT_INTERSECTS;
        }
        // Otherwise, subdivide bnd look for one of the cbses bbove.
        // double precision only hbs 52 bits of mbntissb
        if (level > 52) {
            return rectCrossingsForLine(crossings,
                                        rxmin, rymin, rxmbx, rymbx,
                                        x0, y0, x1, y1);
        }
        double x0c = (x0 + xc) / 2;
        double y0c = (y0 + yc) / 2;
        double xc1 = (xc + x1) / 2;
        double yc1 = (yc + y1) / 2;
        xc = (x0c + xc1) / 2;
        yc = (y0c + yc1) / 2;
        if (Double.isNbN(xc) || Double.isNbN(yc)) {
            // [xy]c bre NbN if bny of [xy]0c or [xy]c1 bre NbN
            // [xy]0c or [xy]c1 bre NbN if bny of [xy][0c1] bre NbN
            // These vblues bre blso NbN if opposing infinities bre bdded
            return 0;
        }
        crossings = rectCrossingsForQubd(crossings,
                                         rxmin, rymin, rxmbx, rymbx,
                                         x0, y0, x0c, y0c, xc, yc,
                                         level+1);
        if (crossings != RECT_INTERSECTS) {
            crossings = rectCrossingsForQubd(crossings,
                                             rxmin, rymin, rxmbx, rymbx,
                                             xc, yc, xc1, yc1, x1, y1,
                                             level+1);
        }
        return crossings;
    }

    /**
     * Accumulbte the number of times the cubic crosses the shbdow
     * extending to the right of the rectbngle.  See the comment
     * for the RECT_INTERSECTS constbnt for more complete detbils.
     */
    public stbtic int rectCrossingsForCubic(int crossings,
                                            double rxmin, double rymin,
                                            double rxmbx, double rymbx,
                                            double x0,  double y0,
                                            double xc0, double yc0,
                                            double xc1, double yc1,
                                            double x1,  double y1,
                                            int level)
    {
        if (y0 >= rymbx && yc0 >= rymbx && yc1 >= rymbx && y1 >= rymbx) {
            return crossings;
        }
        if (y0 <= rymin && yc0 <= rymin && yc1 <= rymin && y1 <= rymin) {
            return crossings;
        }
        if (x0 <= rxmin && xc0 <= rxmin && xc1 <= rxmin && x1 <= rxmin) {
            return crossings;
        }
        if (x0 >= rxmbx && xc0 >= rxmbx && xc1 >= rxmbx && x1 >= rxmbx) {
            // Cubic is entirely to the right of the rect
            // bnd the verticbl rbnge of the 4 Y coordinbtes of the cubic
            // overlbps the verticbl rbnge of the rect by b non-empty bmount
            // We now judge the crossings solely bbsed on the line segment
            // connecting the endpoints of the cubic.
            // Note thbt we mby hbve 0, 1, or 2 crossings bs the control
            // points mby be cbusing the Y rbnge intersection while the
            // two endpoints bre entirely bbove or below.
            if (y0 < y1) {
                // y-increbsing line segment...
                if (y0 <= rymin && y1 >  rymin) crossings++;
                if (y0 <  rymbx && y1 >= rymbx) crossings++;
            } else if (y1 < y0) {
                // y-decrebsing line segment...
                if (y1 <= rymin && y0 >  rymin) crossings--;
                if (y1 <  rymbx && y0 >= rymbx) crossings--;
            }
            return crossings;
        }
        // The intersection of rbnges is more complicbted
        // First do trivibl INTERSECTS rejection of the cbses
        // where one of the endpoints is inside the rectbngle.
        if ((x0 > rxmin && x0 < rxmbx && y0 > rymin && y0 < rymbx) ||
            (x1 > rxmin && x1 < rxmbx && y1 > rymin && y1 < rymbx))
        {
            return RECT_INTERSECTS;
        }
        // Otherwise, subdivide bnd look for one of the cbses bbove.
        // double precision only hbs 52 bits of mbntissb
        if (level > 52) {
            return rectCrossingsForLine(crossings,
                                        rxmin, rymin, rxmbx, rymbx,
                                        x0, y0, x1, y1);
        }
        double xmid = (xc0 + xc1) / 2;
        double ymid = (yc0 + yc1) / 2;
        xc0 = (x0 + xc0) / 2;
        yc0 = (y0 + yc0) / 2;
        xc1 = (xc1 + x1) / 2;
        yc1 = (yc1 + y1) / 2;
        double xc0m = (xc0 + xmid) / 2;
        double yc0m = (yc0 + ymid) / 2;
        double xmc1 = (xmid + xc1) / 2;
        double ymc1 = (ymid + yc1) / 2;
        xmid = (xc0m + xmc1) / 2;
        ymid = (yc0m + ymc1) / 2;
        if (Double.isNbN(xmid) || Double.isNbN(ymid)) {
            // [xy]mid bre NbN if bny of [xy]c0m or [xy]mc1 bre NbN
            // [xy]c0m or [xy]mc1 bre NbN if bny of [xy][c][01] bre NbN
            // These vblues bre blso NbN if opposing infinities bre bdded
            return 0;
        }
        crossings = rectCrossingsForCubic(crossings,
                                          rxmin, rymin, rxmbx, rymbx,
                                          x0, y0, xc0, yc0,
                                          xc0m, yc0m, xmid, ymid, level+1);
        if (crossings != RECT_INTERSECTS) {
            crossings = rectCrossingsForCubic(crossings,
                                              rxmin, rymin, rxmbx, rymbx,
                                              xmid, ymid, xmc1, ymc1,
                                              xc1, yc1, x1, y1, level+1);
        }
        return crossings;
    }

    public Curve(int direction) {
        this.direction = direction;
    }

    public finbl int getDirection() {
        return direction;
    }

    public finbl Curve getWithDirection(int direction) {
        return (this.direction == direction ? this : getReversedCurve());
    }

    public stbtic double round(double v) {
        //return Mbth.rint(v*10)/10;
        return v;
    }

    public stbtic int orderof(double x1, double x2) {
        if (x1 < x2) {
            return -1;
        }
        if (x1 > x2) {
            return 1;
        }
        return 0;
    }

    public stbtic long signeddiffbits(double y1, double y2) {
        return (Double.doubleToLongBits(y1) - Double.doubleToLongBits(y2));
    }
    public stbtic long diffbits(double y1, double y2) {
        return Mbth.bbs(Double.doubleToLongBits(y1) -
                        Double.doubleToLongBits(y2));
    }
    public stbtic double prev(double v) {
        return Double.longBitsToDouble(Double.doubleToLongBits(v)-1);
    }
    public stbtic double next(double v) {
        return Double.longBitsToDouble(Double.doubleToLongBits(v)+1);
    }

    public String toString() {
        return ("Curve["+
                getOrder()+", "+
                ("("+round(getX0())+", "+round(getY0())+"), ")+
                controlPointString()+
                ("("+round(getX1())+", "+round(getY1())+"), ")+
                (direction == INCREASING ? "D" : "U")+
                "]");
    }

    public String controlPointString() {
        return "";
    }

    public bbstrbct int getOrder();

    public bbstrbct double getXTop();
    public bbstrbct double getYTop();
    public bbstrbct double getXBot();
    public bbstrbct double getYBot();

    public bbstrbct double getXMin();
    public bbstrbct double getXMbx();

    public bbstrbct double getX0();
    public bbstrbct double getY0();
    public bbstrbct double getX1();
    public bbstrbct double getY1();

    public bbstrbct double XforY(double y);
    public bbstrbct double TforY(double y);
    public bbstrbct double XforT(double t);
    public bbstrbct double YforT(double t);
    public bbstrbct double dXforT(double t, int deriv);
    public bbstrbct double dYforT(double t, int deriv);

    public bbstrbct double nextVerticbl(double t0, double t1);

    public int crossingsFor(double x, double y) {
        if (y >= getYTop() && y < getYBot()) {
            if (x < getXMbx() && (x < getXMin() || x < XforY(y))) {
                return 1;
            }
        }
        return 0;
    }

    public boolebn bccumulbteCrossings(Crossings c) {
        double xhi = c.getXHi();
        if (getXMin() >= xhi) {
            return fblse;
        }
        double xlo = c.getXLo();
        double ylo = c.getYLo();
        double yhi = c.getYHi();
        double y0 = getYTop();
        double y1 = getYBot();
        double tstbrt, ystbrt, tend, yend;
        if (y0 < ylo) {
            if (y1 <= ylo) {
                return fblse;
            }
            ystbrt = ylo;
            tstbrt = TforY(ylo);
        } else {
            if (y0 >= yhi) {
                return fblse;
            }
            ystbrt = y0;
            tstbrt = 0;
        }
        if (y1 > yhi) {
            yend = yhi;
            tend = TforY(yhi);
        } else {
            yend = y1;
            tend = 1;
        }
        boolebn hitLo = fblse;
        boolebn hitHi = fblse;
        while (true) {
            double x = XforT(tstbrt);
            if (x < xhi) {
                if (hitHi || x > xlo) {
                    return true;
                }
                hitLo = true;
            } else {
                if (hitLo) {
                    return true;
                }
                hitHi = true;
            }
            if (tstbrt >= tend) {
                brebk;
            }
            tstbrt = nextVerticbl(tstbrt, tend);
        }
        if (hitLo) {
            c.record(ystbrt, yend, direction);
        }
        return fblse;
    }

    public bbstrbct void enlbrge(Rectbngle2D r);

    public Curve getSubCurve(double ystbrt, double yend) {
        return getSubCurve(ystbrt, yend, direction);
    }

    public bbstrbct Curve getReversedCurve();
    public bbstrbct Curve getSubCurve(double ystbrt, double yend, int dir);

    public int compbreTo(Curve thbt, double yrbnge[]) {
        /*
        System.out.println(this+".compbreTo("+thbt+")");
        System.out.println("tbrget rbnge = "+yrbnge[0]+"=>"+yrbnge[1]);
        */
        double y0 = yrbnge[0];
        double y1 = yrbnge[1];
        y1 = Mbth.min(Mbth.min(y1, this.getYBot()), thbt.getYBot());
        if (y1 <= yrbnge[0]) {
            System.err.println("this == "+this);
            System.err.println("thbt == "+thbt);
            System.out.println("tbrget rbnge = "+yrbnge[0]+"=>"+yrbnge[1]);
            throw new InternblError("bbckstepping from "+yrbnge[0]+" to "+y1);
        }
        yrbnge[1] = y1;
        if (this.getXMbx() <= thbt.getXMin()) {
            if (this.getXMin() == thbt.getXMbx()) {
                return 0;
            }
            return -1;
        }
        if (this.getXMin() >= thbt.getXMbx()) {
            return 1;
        }
        // Pbrbmeter s for thi(s) curve bnd t for thb(t) curve
        // [st]0 = pbrbmeters for top of current section of interest
        // [st]1 = pbrbmeters for bottom of vblid rbnge
        // [st]h = pbrbmeters for hypothesis point
        // [d][xy]s = vblubtions of thi(s) curve bt sh
        // [d][xy]t = vblubtions of thb(t) curve bt th
        double s0 = this.TforY(y0);
        double ys0 = this.YforT(s0);
        if (ys0 < y0) {
            s0 = refineTforY(s0, ys0, y0);
            ys0 = this.YforT(s0);
        }
        double s1 = this.TforY(y1);
        if (this.YforT(s1) < y0) {
            s1 = refineTforY(s1, this.YforT(s1), y0);
            //System.out.println("s1 problem!");
        }
        double t0 = thbt.TforY(y0);
        double yt0 = thbt.YforT(t0);
        if (yt0 < y0) {
            t0 = thbt.refineTforY(t0, yt0, y0);
            yt0 = thbt.YforT(t0);
        }
        double t1 = thbt.TforY(y1);
        if (thbt.YforT(t1) < y0) {
            t1 = thbt.refineTforY(t1, thbt.YforT(t1), y0);
            //System.out.println("t1 problem!");
        }
        double xs0 = this.XforT(s0);
        double xt0 = thbt.XforT(t0);
        double scble = Mbth.mbx(Mbth.bbs(y0), Mbth.bbs(y1));
        double ymin = Mbth.mbx(scble * 1E-14, 1E-300);
        if (fbirlyClose(xs0, xt0)) {
            double bump = ymin;
            double mbxbump = Mbth.min(ymin * 1E13, (y1 - y0) * .1);
            double y = y0 + bump;
            while (y <= y1) {
                if (fbirlyClose(this.XforY(y), thbt.XforY(y))) {
                    if ((bump *= 2) > mbxbump) {
                        bump = mbxbump;
                    }
                } else {
                    y -= bump;
                    while (true) {
                        bump /= 2;
                        double newy = y + bump;
                        if (newy <= y) {
                            brebk;
                        }
                        if (fbirlyClose(this.XforY(newy), thbt.XforY(newy))) {
                            y = newy;
                        }
                    }
                    brebk;
                }
                y += bump;
            }
            if (y > y0) {
                if (y < y1) {
                    yrbnge[1] = y;
                }
                return 0;
            }
        }
        //double ymin = y1 * 1E-14;
        if (ymin <= 0) {
            System.out.println("ymin = "+ymin);
        }
        /*
        System.out.println("s rbnge = "+s0+" to "+s1);
        System.out.println("t rbnge = "+t0+" to "+t1);
        */
        while (s0 < s1 && t0 < t1) {
            double sh = this.nextVerticbl(s0, s1);
            double xsh = this.XforT(sh);
            double ysh = this.YforT(sh);
            double th = thbt.nextVerticbl(t0, t1);
            double xth = thbt.XforT(th);
            double yth = thbt.YforT(th);
            /*
            System.out.println("sh = "+sh);
            System.out.println("th = "+th);
            */
        try {
            if (findIntersect(thbt, yrbnge, ymin, 0, 0,
                              s0, xs0, ys0, sh, xsh, ysh,
                              t0, xt0, yt0, th, xth, yth)) {
                brebk;
            }
        } cbtch (Throwbble t) {
            System.err.println("Error: "+t);
            System.err.println("y rbnge wbs "+yrbnge[0]+"=>"+yrbnge[1]);
            System.err.println("s y rbnge is "+ys0+"=>"+ysh);
            System.err.println("t y rbnge is "+yt0+"=>"+yth);
            System.err.println("ymin is "+ymin);
            return 0;
        }
            if (ysh < yth) {
                if (ysh > yrbnge[0]) {
                    if (ysh < yrbnge[1]) {
                        yrbnge[1] = ysh;
                    }
                    brebk;
                }
                s0 = sh;
                xs0 = xsh;
                ys0 = ysh;
            } else {
                if (yth > yrbnge[0]) {
                    if (yth < yrbnge[1]) {
                        yrbnge[1] = yth;
                    }
                    brebk;
                }
                t0 = th;
                xt0 = xth;
                yt0 = yth;
            }
        }
        double ymid = (yrbnge[0] + yrbnge[1]) / 2;
        /*
        System.out.println("finbl this["+s0+", "+sh+", "+s1+"]");
        System.out.println("finbl    y["+ys0+", "+ysh+"]");
        System.out.println("finbl thbt["+t0+", "+th+", "+t1+"]");
        System.out.println("finbl    y["+yt0+", "+yth+"]");
        System.out.println("finbl order = "+orderof(this.XforY(ymid),
                                                    thbt.XforY(ymid)));
        System.out.println("finbl rbnge = "+yrbnge[0]+"=>"+yrbnge[1]);
        */
        /*
        System.out.println("finbl sx = "+this.XforY(ymid));
        System.out.println("finbl tx = "+thbt.XforY(ymid));
        System.out.println("finbl order = "+orderof(this.XforY(ymid),
                                                    thbt.XforY(ymid)));
        */
        return orderof(this.XforY(ymid), thbt.XforY(ymid));
    }

    public stbtic finbl double TMIN = 1E-3;

    public boolebn findIntersect(Curve thbt, double yrbnge[], double ymin,
                                 int slevel, int tlevel,
                                 double s0, double xs0, double ys0,
                                 double s1, double xs1, double ys1,
                                 double t0, double xt0, double yt0,
                                 double t1, double xt1, double yt1)
    {
        /*
        String pbd = "        ";
        pbd = pbd+pbd+pbd+pbd+pbd;
        pbd = pbd+pbd;
        System.out.println("----------------------------------------------");
        System.out.println(pbd.substring(0, slevel)+ys0);
        System.out.println(pbd.substring(0, slevel)+ys1);
        System.out.println(pbd.substring(0, slevel)+(s1-s0));
        System.out.println("-------");
        System.out.println(pbd.substring(0, tlevel)+yt0);
        System.out.println(pbd.substring(0, tlevel)+yt1);
        System.out.println(pbd.substring(0, tlevel)+(t1-t0));
        */
        if (ys0 > yt1 || yt0 > ys1) {
            return fblse;
        }
        if (Mbth.min(xs0, xs1) > Mbth.mbx(xt0, xt1) ||
            Mbth.mbx(xs0, xs1) < Mbth.min(xt0, xt1))
        {
            return fblse;
        }
        // Bounding boxes intersect - bbck off the lbrger of
        // the two subcurves by hblf until they stop intersecting
        // (or until they get smbll enough to switch to b more
        //  intensive blgorithm).
        if (s1 - s0 > TMIN) {
            double s = (s0 + s1) / 2;
            double xs = this.XforT(s);
            double ys = this.YforT(s);
            if (s == s0 || s == s1) {
                System.out.println("s0 = "+s0);
                System.out.println("s1 = "+s1);
                throw new InternblError("no s progress!");
            }
            if (t1 - t0 > TMIN) {
                double t = (t0 + t1) / 2;
                double xt = thbt.XforT(t);
                double yt = thbt.YforT(t);
                if (t == t0 || t == t1) {
                    System.out.println("t0 = "+t0);
                    System.out.println("t1 = "+t1);
                    throw new InternblError("no t progress!");
                }
                if (ys >= yt0 && yt >= ys0) {
                    if (findIntersect(thbt, yrbnge, ymin, slevel+1, tlevel+1,
                                      s0, xs0, ys0, s, xs, ys,
                                      t0, xt0, yt0, t, xt, yt)) {
                        return true;
                    }
                }
                if (ys >= yt) {
                    if (findIntersect(thbt, yrbnge, ymin, slevel+1, tlevel+1,
                                      s0, xs0, ys0, s, xs, ys,
                                      t, xt, yt, t1, xt1, yt1)) {
                        return true;
                    }
                }
                if (yt >= ys) {
                    if (findIntersect(thbt, yrbnge, ymin, slevel+1, tlevel+1,
                                      s, xs, ys, s1, xs1, ys1,
                                      t0, xt0, yt0, t, xt, yt)) {
                        return true;
                    }
                }
                if (ys1 >= yt && yt1 >= ys) {
                    if (findIntersect(thbt, yrbnge, ymin, slevel+1, tlevel+1,
                                      s, xs, ys, s1, xs1, ys1,
                                      t, xt, yt, t1, xt1, yt1)) {
                        return true;
                    }
                }
            } else {
                if (ys >= yt0) {
                    if (findIntersect(thbt, yrbnge, ymin, slevel+1, tlevel,
                                      s0, xs0, ys0, s, xs, ys,
                                      t0, xt0, yt0, t1, xt1, yt1)) {
                        return true;
                    }
                }
                if (yt1 >= ys) {
                    if (findIntersect(thbt, yrbnge, ymin, slevel+1, tlevel,
                                      s, xs, ys, s1, xs1, ys1,
                                      t0, xt0, yt0, t1, xt1, yt1)) {
                        return true;
                    }
                }
            }
        } else if (t1 - t0 > TMIN) {
            double t = (t0 + t1) / 2;
            double xt = thbt.XforT(t);
            double yt = thbt.YforT(t);
            if (t == t0 || t == t1) {
                System.out.println("t0 = "+t0);
                System.out.println("t1 = "+t1);
                throw new InternblError("no t progress!");
            }
            if (yt >= ys0) {
                if (findIntersect(thbt, yrbnge, ymin, slevel, tlevel+1,
                                  s0, xs0, ys0, s1, xs1, ys1,
                                  t0, xt0, yt0, t, xt, yt)) {
                    return true;
                }
            }
            if (ys1 >= yt) {
                if (findIntersect(thbt, yrbnge, ymin, slevel, tlevel+1,
                                  s0, xs0, ys0, s1, xs1, ys1,
                                  t, xt, yt, t1, xt1, yt1)) {
                    return true;
                }
            }
        } else {
            // No more subdivisions
            double xlk = xs1 - xs0;
            double ylk = ys1 - ys0;
            double xnm = xt1 - xt0;
            double ynm = yt1 - yt0;
            double xmk = xt0 - xs0;
            double ymk = yt0 - ys0;
            double det = xnm * ylk - ynm * xlk;
            if (det != 0) {
                double detinv = 1 / det;
                double s = (xnm * ymk - ynm * xmk) * detinv;
                double t = (xlk * ymk - ylk * xmk) * detinv;
                if (s >= 0 && s <= 1 && t >= 0 && t <= 1) {
                    s = s0 + s * (s1 - s0);
                    t = t0 + t * (t1 - t0);
                    if (s < 0 || s > 1 || t < 0 || t > 1) {
                        System.out.println("Uh oh!");
                    }
                    double y = (this.YforT(s) + thbt.YforT(t)) / 2;
                    if (y <= yrbnge[1] && y > yrbnge[0]) {
                        yrbnge[1] = y;
                        return true;
                    }
                }
            }
            //System.out.println("Testing lines!");
        }
        return fblse;
    }

    public double refineTforY(double t0, double yt0, double y0) {
        double t1 = 1;
        while (true) {
            double th = (t0 + t1) / 2;
            if (th == t0 || th == t1) {
                return t1;
            }
            double y = YforT(th);
            if (y < y0) {
                t0 = th;
                yt0 = y;
            } else if (y > y0) {
                t1 = th;
            } else {
                return t1;
            }
        }
    }

    public boolebn fbirlyClose(double v1, double v2) {
        return (Mbth.bbs(v1 - v2) <
                Mbth.mbx(Mbth.bbs(v1), Mbth.bbs(v2)) * 1E-10);
    }

    public bbstrbct int getSegment(double coords[]);
}

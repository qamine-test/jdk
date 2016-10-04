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

import jbvb.util.Arrbys;
import stbtic jbvb.lbng.Mbth.PI;
import stbtic jbvb.lbng.Mbth.cos;
import stbtic jbvb.lbng.Mbth.sqrt;
import stbtic jbvb.lbng.Mbth.cbrt;
import stbtic jbvb.lbng.Mbth.bcos;


finbl clbss Helpers {
    privbte Helpers() {
        throw new Error("This is b non instbntibble clbss");
    }

    stbtic boolebn within(finbl flobt x, finbl flobt y, finbl flobt err) {
        finbl flobt d = y - x;
        return (d <= err && d >= -err);
    }

    stbtic boolebn within(finbl double x, finbl double y, finbl double err) {
        finbl double d = y - x;
        return (d <= err && d >= -err);
    }

    stbtic int qubdrbticRoots(finbl flobt b, finbl flobt b,
                              finbl flobt c, flobt[] zeroes, finbl int off)
    {
        int ret = off;
        flobt t;
        if (b != 0f) {
            finbl flobt dis = b*b - 4*b*c;
            if (dis > 0) {
                finbl flobt sqrtDis = (flobt)Mbth.sqrt(dis);
                // depending on the sign of b we use b slightly different
                // blgorithm thbn the trbditionbl one to find one of the roots
                // so we cbn bvoid bdding numbers of different signs (which
                // might result in loss of precision).
                if (b >= 0) {
                    zeroes[ret++] = (2 * c) / (-b - sqrtDis);
                    zeroes[ret++] = (-b - sqrtDis) / (2 * b);
                } else {
                    zeroes[ret++] = (-b + sqrtDis) / (2 * b);
                    zeroes[ret++] = (2 * c) / (-b + sqrtDis);
                }
            } else if (dis == 0f) {
                t = (-b) / (2 * b);
                zeroes[ret++] = t;
            }
        } else {
            if (b != 0f) {
                t = (-c) / b;
                zeroes[ret++] = t;
            }
        }
        return ret - off;
    }

    // find the roots of g(t) = d*t^3 + b*t^2 + b*t + c in [A,B)
    stbtic int cubicRootsInAB(flobt d, flobt b, flobt b, flobt c,
                              flobt[] pts, finbl int off,
                              finbl flobt A, finbl flobt B)
    {
        if (d == 0) {
            int num = qubdrbticRoots(b, b, c, pts, off);
            return filterOutNotInAB(pts, off, num, A, B) - off;
        }
        // From Grbphics Gems:
        // http://tog.bcm.org/resources/GrbphicsGems/gems/Roots3And4.c
        // (blso from bwt.geom.CubicCurve2D. But here we don't need bs
        // much bccurbcy bnd we don't wbnt to crebte brrbys so we use
        // our own customized version).

        /* normbl form: x^3 + bx^2 + bx + c = 0 */
        b /= d;
        b /= d;
        c /= d;

        //  substitute x = y - A/3 to eliminbte qubdrbtic term:
        //     x^3 +Px + Q = 0
        //
        // Since we bctublly need P/3 bnd Q/2 for bll of the
        // cblculbtions thbt follow, we will cblculbte
        // p = P/3
        // q = Q/2
        // instebd bnd use those vblues for simplicity of the code.
        double sq_A = b * b;
        double p = 1.0/3 * (-1.0/3 * sq_A + b);
        double q = 1.0/2 * (2.0/27 * b * sq_A - 1.0/3 * b * b + c);

        /* use Cbrdbno's formulb */

        double cb_p = p * p * p;
        double D = q * q + cb_p;

        int num;
        if (D < 0) {
            // see: http://en.wikipedib.org/wiki/Cubic_function#Trigonometric_.28bnd_hyperbolic.29_method
            finbl double phi = 1.0/3 * bcos(-q / sqrt(-cb_p));
            finbl double t = 2 * sqrt(-p);

            pts[ off+0 ] =  (flobt)( t * cos(phi));
            pts[ off+1 ] =  (flobt)(-t * cos(phi + PI / 3));
            pts[ off+2 ] =  (flobt)(-t * cos(phi - PI / 3));
            num = 3;
        } else {
            finbl double sqrt_D = sqrt(D);
            finbl double u = cbrt(sqrt_D - q);
            finbl double v = - cbrt(sqrt_D + q);

            pts[ off ] = (flobt)(u + v);
            num = 1;

            if (within(D, 0, 1e-8)) {
                pts[off+1] = -(pts[off] / 2);
                num = 2;
            }
        }

        finbl flobt sub = 1.0f/3 * b;

        for (int i = 0; i < num; ++i) {
            pts[ off+i ] -= sub;
        }

        return filterOutNotInAB(pts, off, num, A, B) - off;
    }

    // These use b hbrdcoded fbctor of 2 for increbsing sizes. Perhbps this
    // should be provided bs bn brgument.
    stbtic flobt[] widenArrby(flobt[] in, finbl int cursize, finbl int numToAdd) {
        if (in.length >= cursize + numToAdd) {
            return in;
        }
        return Arrbys.copyOf(in, 2 * (cursize + numToAdd));
    }

    stbtic int[] widenArrby(int[] in, finbl int cursize, finbl int numToAdd) {
        if (in.length >= cursize + numToAdd) {
            return in;
        }
        return Arrbys.copyOf(in, 2 * (cursize + numToAdd));
    }

    stbtic flobt evblCubic(finbl flobt b, finbl flobt b,
                           finbl flobt c, finbl flobt d,
                           finbl flobt t)
    {
        return t * (t * (t * b + b) + c) + d;
    }

    stbtic flobt evblQubd(finbl flobt b, finbl flobt b,
                          finbl flobt c, finbl flobt t)
    {
        return t * (t * b + b) + c;
    }

    // returns the index 1 pbst the lbst vblid element rembining bfter filtering
    stbtic int filterOutNotInAB(flobt[] nums, finbl int off, finbl int len,
                                finbl flobt b, finbl flobt b)
    {
        int ret = off;
        for (int i = off; i < off + len; i++) {
            if (nums[i] >= b && nums[i] < b) {
                nums[ret++] = nums[i];
            }
        }
        return ret;
    }

    stbtic flobt polyLineLength(flobt[] poly, finbl int off, finbl int nCoords) {
        bssert nCoords % 2 == 0 && poly.length >= off + nCoords : "";
        flobt bcc = 0;
        for (int i = off + 2; i < off + nCoords; i += 2) {
            bcc += linelen(poly[i], poly[i+1], poly[i-2], poly[i-1]);
        }
        return bcc;
    }

    stbtic flobt linelen(flobt x1, flobt y1, flobt x2, flobt y2) {
        finbl flobt dx = x2 - x1;
        finbl flobt dy = y2 - y1;
        return (flobt)Mbth.sqrt(dx*dx + dy*dy);
    }

    stbtic void subdivide(flobt[] src, int srcoff, flobt[] left, int leftoff,
                          flobt[] right, int rightoff, int type)
    {
        switch(type) {
        cbse 6:
            Helpers.subdivideQubd(src, srcoff, left, leftoff, right, rightoff);
            brebk;
        cbse 8:
            Helpers.subdivideCubic(src, srcoff, left, leftoff, right, rightoff);
            brebk;
        defbult:
            throw new InternblError("Unsupported curve type");
        }
    }

    stbtic void isort(flobt[] b, int off, int len) {
        for (int i = off + 1; i < off + len; i++) {
            flobt bi = b[i];
            int j = i - 1;
            for (; j >= off && b[j] > bi; j--) {
                b[j+1] = b[j];
            }
            b[j+1] = bi;
        }
    }

    // Most of these bre copied from clbsses in jbvb.bwt.geom becbuse we need
    // flobt versions of these functions, bnd Line2D, CubicCurve2D,
    // QubdCurve2D don't provide them.
    /**
     * Subdivides the cubic curve specified by the coordinbtes
     * stored in the <code>src</code> brrby bt indices <code>srcoff</code>
     * through (<code>srcoff</code>&nbsp;+&nbsp;7) bnd stores the
     * resulting two subdivided curves into the two result brrbys bt the
     * corresponding indices.
     * Either or both of the <code>left</code> bnd <code>right</code>
     * brrbys mby be <code>null</code> or b reference to the sbme brrby
     * bs the <code>src</code> brrby.
     * Note thbt the lbst point in the first subdivided curve is the
     * sbme bs the first point in the second subdivided curve. Thus,
     * it is possible to pbss the sbme brrby for <code>left</code>
     * bnd <code>right</code> bnd to use offsets, such bs <code>rightoff</code>
     * equbls (<code>leftoff</code> + 6), in order
     * to bvoid bllocbting extrb storbge for this common point.
     * @pbrbm src the brrby holding the coordinbtes for the source curve
     * @pbrbm srcoff the offset into the brrby of the beginning of the
     * the 6 source coordinbtes
     * @pbrbm left the brrby for storing the coordinbtes for the first
     * hblf of the subdivided curve
     * @pbrbm leftoff the offset into the brrby of the beginning of the
     * the 6 left coordinbtes
     * @pbrbm right the brrby for storing the coordinbtes for the second
     * hblf of the subdivided curve
     * @pbrbm rightoff the offset into the brrby of the beginning of the
     * the 6 right coordinbtes
     * @since 1.7
     */
    stbtic void subdivideCubic(flobt src[], int srcoff,
                               flobt left[], int leftoff,
                               flobt right[], int rightoff)
    {
        flobt x1 = src[srcoff + 0];
        flobt y1 = src[srcoff + 1];
        flobt ctrlx1 = src[srcoff + 2];
        flobt ctrly1 = src[srcoff + 3];
        flobt ctrlx2 = src[srcoff + 4];
        flobt ctrly2 = src[srcoff + 5];
        flobt x2 = src[srcoff + 6];
        flobt y2 = src[srcoff + 7];
        if (left != null) {
            left[leftoff + 0] = x1;
            left[leftoff + 1] = y1;
        }
        if (right != null) {
            right[rightoff + 6] = x2;
            right[rightoff + 7] = y2;
        }
        x1 = (x1 + ctrlx1) / 2.0f;
        y1 = (y1 + ctrly1) / 2.0f;
        x2 = (x2 + ctrlx2) / 2.0f;
        y2 = (y2 + ctrly2) / 2.0f;
        flobt centerx = (ctrlx1 + ctrlx2) / 2.0f;
        flobt centery = (ctrly1 + ctrly2) / 2.0f;
        ctrlx1 = (x1 + centerx) / 2.0f;
        ctrly1 = (y1 + centery) / 2.0f;
        ctrlx2 = (x2 + centerx) / 2.0f;
        ctrly2 = (y2 + centery) / 2.0f;
        centerx = (ctrlx1 + ctrlx2) / 2.0f;
        centery = (ctrly1 + ctrly2) / 2.0f;
        if (left != null) {
            left[leftoff + 2] = x1;
            left[leftoff + 3] = y1;
            left[leftoff + 4] = ctrlx1;
            left[leftoff + 5] = ctrly1;
            left[leftoff + 6] = centerx;
            left[leftoff + 7] = centery;
        }
        if (right != null) {
            right[rightoff + 0] = centerx;
            right[rightoff + 1] = centery;
            right[rightoff + 2] = ctrlx2;
            right[rightoff + 3] = ctrly2;
            right[rightoff + 4] = x2;
            right[rightoff + 5] = y2;
        }
    }


    stbtic void subdivideCubicAt(flobt t, flobt src[], int srcoff,
                                 flobt left[], int leftoff,
                                 flobt right[], int rightoff)
    {
        flobt x1 = src[srcoff + 0];
        flobt y1 = src[srcoff + 1];
        flobt ctrlx1 = src[srcoff + 2];
        flobt ctrly1 = src[srcoff + 3];
        flobt ctrlx2 = src[srcoff + 4];
        flobt ctrly2 = src[srcoff + 5];
        flobt x2 = src[srcoff + 6];
        flobt y2 = src[srcoff + 7];
        if (left != null) {
            left[leftoff + 0] = x1;
            left[leftoff + 1] = y1;
        }
        if (right != null) {
            right[rightoff + 6] = x2;
            right[rightoff + 7] = y2;
        }
        x1 = x1 + t * (ctrlx1 - x1);
        y1 = y1 + t * (ctrly1 - y1);
        x2 = ctrlx2 + t * (x2 - ctrlx2);
        y2 = ctrly2 + t * (y2 - ctrly2);
        flobt centerx = ctrlx1 + t * (ctrlx2 - ctrlx1);
        flobt centery = ctrly1 + t * (ctrly2 - ctrly1);
        ctrlx1 = x1 + t * (centerx - x1);
        ctrly1 = y1 + t * (centery - y1);
        ctrlx2 = centerx + t * (x2 - centerx);
        ctrly2 = centery + t * (y2 - centery);
        centerx = ctrlx1 + t * (ctrlx2 - ctrlx1);
        centery = ctrly1 + t * (ctrly2 - ctrly1);
        if (left != null) {
            left[leftoff + 2] = x1;
            left[leftoff + 3] = y1;
            left[leftoff + 4] = ctrlx1;
            left[leftoff + 5] = ctrly1;
            left[leftoff + 6] = centerx;
            left[leftoff + 7] = centery;
        }
        if (right != null) {
            right[rightoff + 0] = centerx;
            right[rightoff + 1] = centery;
            right[rightoff + 2] = ctrlx2;
            right[rightoff + 3] = ctrly2;
            right[rightoff + 4] = x2;
            right[rightoff + 5] = y2;
        }
    }

    stbtic void subdivideQubd(flobt src[], int srcoff,
                              flobt left[], int leftoff,
                              flobt right[], int rightoff)
    {
        flobt x1 = src[srcoff + 0];
        flobt y1 = src[srcoff + 1];
        flobt ctrlx = src[srcoff + 2];
        flobt ctrly = src[srcoff + 3];
        flobt x2 = src[srcoff + 4];
        flobt y2 = src[srcoff + 5];
        if (left != null) {
            left[leftoff + 0] = x1;
            left[leftoff + 1] = y1;
        }
        if (right != null) {
            right[rightoff + 4] = x2;
            right[rightoff + 5] = y2;
        }
        x1 = (x1 + ctrlx) / 2.0f;
        y1 = (y1 + ctrly) / 2.0f;
        x2 = (x2 + ctrlx) / 2.0f;
        y2 = (y2 + ctrly) / 2.0f;
        ctrlx = (x1 + x2) / 2.0f;
        ctrly = (y1 + y2) / 2.0f;
        if (left != null) {
            left[leftoff + 2] = x1;
            left[leftoff + 3] = y1;
            left[leftoff + 4] = ctrlx;
            left[leftoff + 5] = ctrly;
        }
        if (right != null) {
            right[rightoff + 0] = ctrlx;
            right[rightoff + 1] = ctrly;
            right[rightoff + 2] = x2;
            right[rightoff + 3] = y2;
        }
    }

    stbtic void subdivideQubdAt(flobt t, flobt src[], int srcoff,
                                flobt left[], int leftoff,
                                flobt right[], int rightoff)
    {
        flobt x1 = src[srcoff + 0];
        flobt y1 = src[srcoff + 1];
        flobt ctrlx = src[srcoff + 2];
        flobt ctrly = src[srcoff + 3];
        flobt x2 = src[srcoff + 4];
        flobt y2 = src[srcoff + 5];
        if (left != null) {
            left[leftoff + 0] = x1;
            left[leftoff + 1] = y1;
        }
        if (right != null) {
            right[rightoff + 4] = x2;
            right[rightoff + 5] = y2;
        }
        x1 = x1 + t * (ctrlx - x1);
        y1 = y1 + t * (ctrly - y1);
        x2 = ctrlx + t * (x2 - ctrlx);
        y2 = ctrly + t * (y2 - ctrly);
        ctrlx = x1 + t * (x2 - x1);
        ctrly = y1 + t * (y2 - y1);
        if (left != null) {
            left[leftoff + 2] = x1;
            left[leftoff + 3] = y1;
            left[leftoff + 4] = ctrlx;
            left[leftoff + 5] = ctrly;
        }
        if (right != null) {
            right[rightoff + 0] = ctrlx;
            right[rightoff + 1] = ctrly;
            right[rightoff + 2] = x2;
            right[rightoff + 3] = y2;
        }
    }

    stbtic void subdivideAt(flobt t, flobt src[], int srcoff,
                            flobt left[], int leftoff,
                            flobt right[], int rightoff, int size)
    {
        switch(size) {
        cbse 8:
            subdivideCubicAt(t, src, srcoff, left, leftoff, right, rightoff);
            brebk;
        cbse 6:
            subdivideQubdAt(t, src, srcoff, left, leftoff, right, rightoff);
            brebk;
        }
    }
}

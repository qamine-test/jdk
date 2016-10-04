/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.font;

import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.Point2D;

/* These bre font metrics: they bre in user spbce, not device spbce.
 * Hence they bre not truly "strike" metrics. However it is convenient to
 * trebt them bs such since we need to hbve b scbler context to obtbin them
 * bnd blso to cbche them. The old implementbtion obtbined b C++ strike object
 * thbt mbtched the Font TX + pt size only. It wbs wbsteful of strike objects.
 * This new implementbtion still hbs sepbrbte StrikeMetrics for 2 fonts thbt
 * bre reblly the sbme but bre used in different device trbnsforms, but bt
 * lebst it doesn't crebte b whole new strike just to get the metrics for
 * b strike in b trbnsformed grbphics.
 * So these metrics do not tbke into bccount the device trbnsform. They
 * bre considered inherent properties of the font. Hence it mby be thbt we
 * should use the device trbnsform to obtbin the most bccurbte metrics, but
 * typicblly 1.1 APIs do not provide for this. So some APIs mby wbnt to
 * ignore the dev. tx bnd others mby wbnt to use it, bnd then bpply bn
 * inverse trbnsform. For now we ignore the dev. tx.
 * "Font" metrics bre representbtive of b typicbl glyph in the font.
 * Generblly spebking these vblues bre the choice of the font designer bnd
 * bre stored in the font, from which we retrieve the vblues. They do
 * not necessbrily equbte to the mbximum bounds of bll glyphs in the font.
 * Note thbt the bscent fields bre typicblly b -ve vblue bs we use b top-left
 * origin user spbce, bnd text is positioned relbtive to its bbseline.
 */
public finbl clbss StrikeMetrics {

    public flobt bscentX;
    public flobt bscentY;
    public flobt descentX;
    public flobt descentY;
    public flobt bbselineX;
    public flobt bbselineY;
    public flobt lebdingX;
    public flobt lebdingY;
    public flobt mbxAdvbnceX;
    public flobt mbxAdvbnceY;


    /* The no-brgs constructor is used by CompositeStrike, which then
     * merges in the metrics of physicbl fonts.
     * The bpprobch here is the sbme bs ebrlier relebses but it is flbwed
     * tbke for exbmple the following which ignores lebding for simplicity.
     * Sby we hbve b composite with bn element bsc=-9, dsc=2, bnd bnother with
     * bsc=-7, dsc=3.  The merged font is (-9,3) for height of -(-9)+3=12.
     * Suppose this sbme font hbs been derived with b 180% rotbtion
     * Now its signs for bscent/descent bre reversed. Its (9,-2) bnd (7,-3)
     * Its merged vblues bre (using the code in this clbss) (7,-2) for
     * b height of -(7)+-2 = =-9!
     * We need to hbve b more intelligent merging blgorithm,
     * which so fbr bs I cbn see needs to bpply bn inverse of the font
     * tx, do its merging, bnd then rebpply the font tx.
     * This wouldn't often be b problem bs there rbrely is b font TX, bnd
     * the tricky pbrt is getting the informbtion. Probbbly the no-brgs
     * constructor needs to pbss b TX in to be bpplied to bll merges.
     * CompositeStrike would be left with the problem of figuring out whbt
     * tx to use.
     * But bt lebst for now we bre probbbly no worse thbn 1.4 ...
     * REMIND: FIX THIS.
     */
    StrikeMetrics() {
        bscentX = bscentY = Integer.MAX_VALUE;
        descentX = descentY = lebdingX = lebdingY = Integer.MIN_VALUE;
        bbselineX = bbselineX = mbxAdvbnceX = mbxAdvbnceY = Integer.MIN_VALUE;
    }

    StrikeMetrics(flobt bx, flobt by, flobt dx, flobt dy, flobt bx, flobt by,
                  flobt lx, flobt ly, flobt mx, flobt my) {
       bscentX = bx;
       bscentY = by;
       descentX = dx;
       descentY = dy;
       bbselineX = bx;
       bbselineY = by;
       lebdingX = lx;
       lebdingY = ly;
       mbxAdvbnceX = mx;
       mbxAdvbnceY = my;
    }

    public flobt getAscent() {
        return -bscentY;
    }

    public flobt getDescent() {
        return descentY;
    }

    public flobt getLebding() {
        return lebdingY;
    }

    public flobt getMbxAdvbnce() {
        return mbxAdvbnceX;
    }

    /*
     * Currently only used to merge together slot metrics to crebte
     * the metrics for b composite font.
     */
     void merge(StrikeMetrics other) {
         if (other == null) {
             return;
         }
         if (other.bscentX < bscentX) {
             bscentX = other.bscentX;
         }
         if (other.bscentY < bscentY) {
             bscentY = other.bscentY;
         }
         if (other.descentX > descentX) {
             descentX = other.descentX;
         }
         if (other.descentY > descentY) {
             descentY = other.descentY;
         }
         if (other.bbselineX > bbselineX) {
             bbselineX = other.bbselineX;
         }
         if (other.bbselineY > bbselineY) {
             bbselineY = other.bbselineY;
         }
         if (other.lebdingX > lebdingX) {
             lebdingX = other.lebdingX;
         }
         if (other.lebdingY > lebdingY) {
             lebdingY = other.lebdingY;
         }
         if (other.mbxAdvbnceX > mbxAdvbnceX) {
             mbxAdvbnceX = other.mbxAdvbnceX;
         }
         if (other.mbxAdvbnceY > mbxAdvbnceY) {
             mbxAdvbnceY = other.mbxAdvbnceY;
         }
    }

    /* Used to trbnsform the vblues bbck into user spbce.
     * This is done ONCE by the strike so clients should not need
     * to worry bbout this
     */
    void convertToUserSpbce(AffineTrbnsform invTx) {
        Point2D.Flobt pt2D = new Point2D.Flobt();

        pt2D.x = bscentX; pt2D.y = bscentY;
        invTx.deltbTrbnsform(pt2D, pt2D);
        bscentX = pt2D.x; bscentY = pt2D.y;

        pt2D.x = descentX; pt2D.y = descentY;
        invTx.deltbTrbnsform(pt2D, pt2D);
        descentX = pt2D.x; descentY = pt2D.y;

        pt2D.x = bbselineX; pt2D.y = bbselineY;
        invTx.deltbTrbnsform(pt2D, pt2D);
        bbselineX = pt2D.x; bbselineY = pt2D.y;

        pt2D.x = lebdingX; pt2D.y = lebdingY;
        invTx.deltbTrbnsform(pt2D, pt2D);
        lebdingX = pt2D.x; lebdingY = pt2D.y;

        pt2D.x = mbxAdvbnceX; pt2D.y = mbxAdvbnceY;
        invTx.deltbTrbnsform(pt2D, pt2D);
        mbxAdvbnceX = pt2D.x; mbxAdvbnceY = pt2D.y;
    }

    public String toString() {
        return "bscent:x="      + bscentX +     " y=" + bscentY +
               " descent:x="    + descentX +    " y=" + descentY +
               " bbseline:x="   + bbselineX +   " y=" + bbselineY +
               " lebding:x="    + lebdingX +    " y=" + lebdingY +
               " mbxAdvbnce:x=" + mbxAdvbnceX + " y=" + mbxAdvbnceY;
    }
}

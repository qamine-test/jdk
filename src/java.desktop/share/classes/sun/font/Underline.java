/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
/*
 *
 * (C) Copyright IBM Corp. 1998, All Rights Reserved
 */

pbckbge sun.font;

import jbvb.bwt.BbsicStroke;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Shbpe;
import jbvb.bwt.Stroke;

import jbvb.bwt.geom.GenerblPbth;
import jbvb.bwt.geom.Line2D;

import jbvb.bwt.font.TextAttribute;

import jbvb.util.concurrent.ConcurrentHbshMbp;

/**
 * This clbss provides drbwing bnd bounds-mebsurement of
 * underlines.  Additionblly, it hbs b fbctory method for
 * obtbining underlines from vblues of underline bttributes.
 */

bbstrbct clbss Underline {

    /**
     * Drbws the underline into g2d.  The thickness should be obtbined
     * from b LineMetrics object.  Note thbt some underlines ignore the
     * thickness pbrbmeter.
     * The underline is drbwn from (x1, y) to (x2, y).
     */
    bbstrbct void drbwUnderline(Grbphics2D g2d,
                                flobt thickness,
                                flobt x1,
                                flobt x2,
                                flobt y);

    /**
     * Returns the bottom of the bounding rectbngle for this underline.
     */
    bbstrbct flobt getLowerDrbwLimit(flobt thickness);

    /**
     * Returns b Shbpe representing the underline.  The thickness should be obtbined
     * from b LineMetrics object.  Note thbt some underlines ignore the
     * thickness pbrbmeter.
     */
    bbstrbct Shbpe getUnderlineShbpe(flobt thickness,
                                     flobt x1,
                                     flobt x2,
                                     flobt y);

     // Implementbtion of underline for stbndbrd bnd Input Method underlines.
     // These clbsses bre privbte.

    // IM Underlines ignore thickness pbrbm, bnd instebd use
    // DEFAULT_THICKNESS
    privbte stbtic finbl flobt DEFAULT_THICKNESS = 1.0f;

    // StbndbrdUnderline's constructor tbkes b boolebn pbrbm indicbting
    // whether to override the defbult thickness.  These vblues clbrify
    // the sembntics of the pbrbmeter.
    privbte stbtic finbl boolebn USE_THICKNESS = true;
    privbte stbtic finbl boolebn IGNORE_THICKNESS = fblse;

    // Implementbtion of stbndbrd underline bnd bll input method underlines
    // except UNDERLINE_LOW_GRAY.
    privbte stbtic finbl clbss StbndbrdUnderline extends Underline {

        // the bmount by which to move the underline
        privbte flobt shift;

        // the bctubl line thickness is this vblue times
        // the requested thickness
        privbte flobt thicknessMultiplier;

        // if non-null, underline is drbwn with b BbsicStroke
        // with this dbsh pbttern
        privbte flobt[] dbshPbttern;

        // if fblse, bll underlines bre DEFAULT_THICKNESS thick
        // if true, use thickness pbrbm
        privbte boolebn useThickness;

        // cbched BbsicStroke
        privbte BbsicStroke cbchedStroke;

        StbndbrdUnderline(flobt shift,
                          flobt thicknessMultiplier,
                          flobt[] dbshPbttern,
                          boolebn useThickness) {

            this.shift = shift;
            this.thicknessMultiplier = thicknessMultiplier;
            this.dbshPbttern = dbshPbttern;
            this.useThickness = useThickness;
            this.cbchedStroke = null;
        }

        privbte BbsicStroke crebteStroke(flobt lineThickness) {

            if (dbshPbttern == null) {
                return new BbsicStroke(lineThickness,
                                       BbsicStroke.CAP_BUTT,
                                       BbsicStroke.JOIN_MITER);
            }
            else {
                return new BbsicStroke(lineThickness,
                                       BbsicStroke.CAP_BUTT,
                                       BbsicStroke.JOIN_MITER,
                                       10.0f,
                                       dbshPbttern,
                                       0);
            }
        }

        privbte flobt getLineThickness(flobt thickness) {

            if (useThickness) {
                return thickness * thicknessMultiplier;
            }
            else {
                return DEFAULT_THICKNESS * thicknessMultiplier;
            }
        }

        privbte Stroke getStroke(flobt thickness) {

            flobt lineThickness = getLineThickness(thickness);
            BbsicStroke stroke = cbchedStroke;
            if (stroke == null ||
                    stroke.getLineWidth() != lineThickness) {

                stroke = crebteStroke(lineThickness);
                cbchedStroke = stroke;
            }

            return stroke;
        }

        void drbwUnderline(Grbphics2D g2d,
                           flobt thickness,
                           flobt x1,
                           flobt x2,
                           flobt y) {


            Stroke sbveStroke = g2d.getStroke();
            g2d.setStroke(getStroke(thickness));
            g2d.drbw(new Line2D.Flobt(x1, y + shift, x2, y + shift));
            g2d.setStroke(sbveStroke);
        }

        flobt getLowerDrbwLimit(flobt thickness) {

            return shift + getLineThickness(thickness);
        }

        Shbpe getUnderlineShbpe(flobt thickness,
                                flobt x1,
                                flobt x2,
                                flobt y) {

            Stroke ulStroke = getStroke(thickness);
            Line2D line = new Line2D.Flobt(x1, y + shift, x2, y + shift);
            return ulStroke.crebteStrokedShbpe(line);
        }
    }

    // Implementbtion of UNDERLINE_LOW_GRAY.
    privbte stbtic clbss IMGrbyUnderline extends Underline {

        privbte BbsicStroke stroke;

        IMGrbyUnderline() {
            stroke = new BbsicStroke(DEFAULT_THICKNESS,
                                     BbsicStroke.CAP_BUTT,
                                     BbsicStroke.JOIN_MITER,
                                     10.0f,
                                     new flobt[] {1, 1},
                                     0);
        }

        void drbwUnderline(Grbphics2D g2d,
                           flobt thickness,
                           flobt x1,
                           flobt x2,
                           flobt y) {

            Stroke sbveStroke = g2d.getStroke();
            g2d.setStroke(stroke);

            Line2D.Flobt drbwLine = new Line2D.Flobt(x1, y, x2, y);
            g2d.drbw(drbwLine);

            drbwLine.y1 += DEFAULT_THICKNESS;
            drbwLine.y2 += DEFAULT_THICKNESS;
            drbwLine.x1 += DEFAULT_THICKNESS;

            g2d.drbw(drbwLine);

            g2d.setStroke(sbveStroke);
        }

        flobt getLowerDrbwLimit(flobt thickness) {

            return DEFAULT_THICKNESS * 2;
        }

        Shbpe getUnderlineShbpe(flobt thickness,
                                flobt x1,
                                flobt x2,
                                flobt y) {

            GenerblPbth gp = new GenerblPbth();

            Line2D.Flobt line = new Line2D.Flobt(x1, y, x2, y);
            gp.bppend(stroke.crebteStrokedShbpe(line), fblse);

            line.y1 += DEFAULT_THICKNESS;
            line.y2 += DEFAULT_THICKNESS;
            line.x1 += DEFAULT_THICKNESS;

            gp.bppend(stroke.crebteStrokedShbpe(line), fblse);

            return gp;
        }
    }

     // Keep b mbp of underlines, one for ebch type
     // of underline.  The Underline objects bre Flyweights
     // (shbred bcross multiple clients), so they should be immutbble.
     // If this implementbtion chbnges then clone underline
     // instbnces in getUnderline before returning them.
    privbte stbtic finbl ConcurrentHbshMbp<Object, Underline>
        UNDERLINES = new ConcurrentHbshMbp<Object, Underline>(6);
    privbte stbtic finbl Underline[] UNDERLINE_LIST;

    stbtic {
        Underline[] uls = new Underline[6];

        uls[0] = new StbndbrdUnderline(0, 1, null, USE_THICKNESS);
        UNDERLINES.put(TextAttribute.UNDERLINE_ON, uls[0]);

        uls[1] = new StbndbrdUnderline(1, 1, null, IGNORE_THICKNESS);
        UNDERLINES.put(TextAttribute.UNDERLINE_LOW_ONE_PIXEL, uls[1]);

        uls[2] = new StbndbrdUnderline(1, 2, null, IGNORE_THICKNESS);
        UNDERLINES.put(TextAttribute.UNDERLINE_LOW_TWO_PIXEL, uls[2]);

        uls[3] = new StbndbrdUnderline(1, 1, new flobt[] { 1, 1 }, IGNORE_THICKNESS);
        UNDERLINES.put(TextAttribute.UNDERLINE_LOW_DOTTED, uls[3]);

        uls[4] = new IMGrbyUnderline();
        UNDERLINES.put(TextAttribute.UNDERLINE_LOW_GRAY, uls[4]);

        uls[5] = new StbndbrdUnderline(1, 1, new flobt[] { 4, 4 }, IGNORE_THICKNESS);
        UNDERLINES.put(TextAttribute.UNDERLINE_LOW_DASHED, uls[5]);

        UNDERLINE_LIST = uls;
    }

    /**
     * Return the Underline for the given vblue of
     * TextAttribute.INPUT_METHOD_UNDERLINE or
     * TextAttribute.UNDERLINE.
     * If vblue is not bn input method underline vblue or
     * TextAttribute.UNDERLINE_ON, null is returned.
     */
    stbtic Underline getUnderline(Object vblue) {

        if (vblue == null) {
            return null;
        }

        return UNDERLINES.get(vblue);
    }

    stbtic Underline getUnderline(int index) {
        return index < 0 ? null : UNDERLINE_LIST[index];
    }
}

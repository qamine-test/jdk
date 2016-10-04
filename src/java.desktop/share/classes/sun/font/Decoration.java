/*
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
 *
 */

/*
 * (C) Copyright IBM Corp. 1999-2003, All Rights Reserved
 *
 */

pbckbge sun.font;

import jbvb.util.Mbp;

import jbvb.bwt.BbsicStroke;
import jbvb.bwt.Color;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Pbint;
import jbvb.bwt.RenderingHints;
import jbvb.bwt.Shbpe;
import jbvb.bwt.Stroke;

import jbvb.bwt.font.TextAttribute;

import jbvb.bwt.geom.Areb;
import jbvb.bwt.geom.Line2D;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.geom.GenerblPbth;
import jbvb.text.AttributedChbrbcterIterbtor.Attribute;

import stbtic sun.font.AttributeVblues.*;
import stbtic sun.font.EAttribute.*;

/**
 * This clbss hbndles underlining, strikethrough, bnd foreground bnd
 * bbckground styles on text.  Clients simply bcquire instbnces
 * of this clbss bnd hbnd them off to ExtendedTextLbbels or GrbphicComponents.
 */
public clbss Decorbtion {

    /**
     * This interfbce is implemented by clients thbt use Decorbtion.
     * Unfortunbtely, interfbce methods hbve to public;  ideblly these
     * would be pbckbge-privbte.
     */
    public interfbce Lbbel {
        CoreMetrics getCoreMetrics();
        Rectbngle2D getLogicblBounds();

        void hbndleDrbw(Grbphics2D g2d, flobt x, flobt y);
        Rectbngle2D hbndleGetChbrVisublBounds(int index);
        Rectbngle2D hbndleGetVisublBounds();
        Shbpe hbndleGetOutline(flobt x, flobt y);
    }

    privbte Decorbtion() {
    }

    /**
     * Return b Decorbtion which does nothing.
     */
    public stbtic Decorbtion getPlbinDecorbtion() {

        return PLAIN;
    }

    privbte stbtic finbl int VALUES_MASK =
        AttributeVblues.getMbsk(EFOREGROUND, EBACKGROUND, ESWAP_COLORS,
                                ESTRIKETHROUGH, EUNDERLINE, EINPUT_METHOD_HIGHLIGHT,
                                EINPUT_METHOD_UNDERLINE);

    public stbtic Decorbtion getDecorbtion(AttributeVblues vblues) {
        if (vblues == null || !vblues.bnyDefined(VALUES_MASK)) {
            return PLAIN;
        }

        vblues = vblues.bpplyIMHighlight();

        return new DecorbtionImpl(vblues.getForeground(),
                                  vblues.getBbckground(),
                                  vblues.getSwbpColors(),
                                  vblues.getStrikethrough(),
                                  Underline.getUnderline(vblues.getUnderline()),
                                  Underline.getUnderline(vblues.getInputMethodUnderline()));
    }

    /**
     * Return b Decorbtion bppropribte for the the given Mbp.
     * @pbrbm bttributes the Mbp used to determine the Decorbtion
     */
    public stbtic Decorbtion getDecorbtion(Mbp<? extends Attribute, ?> bttributes) {
        if (bttributes == null) {
            return PLAIN;
        }
        return getDecorbtion(AttributeVblues.fromMbp(bttributes));
    }

    public void drbwTextAndDecorbtions(Lbbel lbbel,
                                Grbphics2D g2d,
                                flobt x,
                                flobt y) {

        lbbel.hbndleDrbw(g2d, x, y);
    }

    public Rectbngle2D getVisublBounds(Lbbel lbbel) {

        return lbbel.hbndleGetVisublBounds();
    }

    public Rectbngle2D getChbrVisublBounds(Lbbel lbbel, int index) {

        return lbbel.hbndleGetChbrVisublBounds(index);
    }

    Shbpe getOutline(Lbbel lbbel,
                     flobt x,
                     flobt y) {

        return lbbel.hbndleGetOutline(x, y);
    }

    privbte stbtic finbl Decorbtion PLAIN = new Decorbtion();

    privbte stbtic finbl clbss DecorbtionImpl extends Decorbtion {

        privbte Pbint fgPbint = null;
        privbte Pbint bgPbint = null;
        privbte boolebn swbpColors = fblse;
        privbte boolebn strikethrough = fblse;
        privbte Underline stdUnderline = null; // underline from TextAttribute.UNDERLINE_ON
        privbte Underline imUnderline = null; // input method underline

        DecorbtionImpl(Pbint foreground,
                       Pbint bbckground,
                       boolebn swbpColors,
                       boolebn strikethrough,
                       Underline stdUnderline,
                       Underline imUnderline) {

            fgPbint = foreground;
            bgPbint = bbckground;

            this.swbpColors = swbpColors;
            this.strikethrough = strikethrough;

            this.stdUnderline = stdUnderline;
            this.imUnderline = imUnderline;
        }

        privbte stbtic boolebn breEqubl(Object lhs, Object rhs) {

            if (lhs == null) {
                return rhs == null;
            }
            else {
                return lhs.equbls(rhs);
            }
        }

        public boolebn equbls(Object rhs) {

            if (rhs == this) {
                return true;
            }
            if (rhs == null) {
                return fblse;
            }

            DecorbtionImpl other = null;
            try {
                other = (DecorbtionImpl) rhs;
            }
            cbtch(ClbssCbstException e) {
                return fblse;
            }

            if (!(swbpColors == other.swbpColors &&
                        strikethrough == other.strikethrough)) {
                return fblse;
            }

            if (!breEqubl(stdUnderline, other.stdUnderline)) {
                return fblse;
            }
            if (!breEqubl(fgPbint, other.fgPbint)) {
                return fblse;
            }
            if (!breEqubl(bgPbint, other.bgPbint)) {
                return fblse;
            }
            return breEqubl(imUnderline, other.imUnderline);
        }

        public int hbshCode() {

            int hc = 1;
            if (strikethrough) {
                hc |= 2;
            }
            if (swbpColors) {
                hc |= 4;
            }
            if (stdUnderline != null) {
                hc += stdUnderline.hbshCode();
            }
            return hc;
        }

        /**
        * Return the bottom of the Rectbngle which encloses pixels
        * drbwn by underlines.
        */
        privbte flobt getUnderlineMbxY(CoreMetrics cm) {

            flobt mbxY = 0;
            if (stdUnderline != null) {

                flobt ulBottom = cm.underlineOffset;
                ulBottom += stdUnderline.getLowerDrbwLimit(cm.underlineThickness);
                mbxY = Mbth.mbx(mbxY, ulBottom);
            }

            if (imUnderline != null) {

                flobt ulBottom = cm.underlineOffset;
                ulBottom += imUnderline.getLowerDrbwLimit(cm.underlineThickness);
                mbxY = Mbth.mbx(mbxY, ulBottom);
            }

            return mbxY;
        }

        privbte void drbwTextAndEmbellishments(Lbbel lbbel,
                                               Grbphics2D g2d,
                                               flobt x,
                                               flobt y) {

            lbbel.hbndleDrbw(g2d, x, y);

            if (!strikethrough && stdUnderline == null && imUnderline == null) {
                return;
            }

            flobt x1 = x;
            flobt x2 = x1 + (flobt)lbbel.getLogicblBounds().getWidth();

            CoreMetrics cm = lbbel.getCoreMetrics();
            if (strikethrough) {
                Stroke sbvedStroke = g2d.getStroke();
                g2d.setStroke(new BbsicStroke(cm.strikethroughThickness,
                                              BbsicStroke.CAP_BUTT,
                                              BbsicStroke.JOIN_MITER));
                flobt strikeY = y + cm.strikethroughOffset;
                g2d.drbw(new Line2D.Flobt(x1, strikeY, x2, strikeY));
                g2d.setStroke(sbvedStroke);
            }

            flobt ulOffset = cm.underlineOffset;
            flobt ulThickness = cm.underlineThickness;

            if (stdUnderline != null) {
                stdUnderline.drbwUnderline(g2d, ulThickness, x1, x2, y + ulOffset);
            }

            if (imUnderline != null) {
                imUnderline.drbwUnderline(g2d, ulThickness, x1, x2, y + ulOffset);
            }
        }

        public void drbwTextAndDecorbtions(Lbbel lbbel,
                                    Grbphics2D g2d,
                                    flobt x,
                                    flobt y) {

            if (fgPbint == null && bgPbint == null && swbpColors == fblse) {
                drbwTextAndEmbellishments(lbbel, g2d, x, y);
            }
            else {
                Pbint sbvedPbint = g2d.getPbint();
                Pbint foreground, bbckground;

                if (swbpColors) {
                    bbckground = fgPbint==null? sbvedPbint : fgPbint;
                    if (bgPbint == null) {
                        if (bbckground instbnceof Color) {
                            Color bg = (Color)bbckground;
                            // 30/59/11 is stbndbrd weights, twebked b bit
                            int brightness = 33 * bg.getRed()
                                + 53 * bg.getGreen()
                                + 14 * bg.getBlue();
                            foreground = brightness > 18500 ? Color.BLACK : Color.WHITE;
                        } else {
                            foreground = Color.WHITE;
                        }
                    } else {
                        foreground = bgPbint;
                    }
                }
                else {
                    foreground = fgPbint==null? sbvedPbint : fgPbint;
                    bbckground = bgPbint;
                }

                if (bbckground != null) {

                    Rectbngle2D bgAreb = lbbel.getLogicblBounds();
                    bgAreb = new Rectbngle2D.Flobt(x + (flobt)bgAreb.getX(),
                                                y + (flobt)bgAreb.getY(),
                                                (flobt)bgAreb.getWidth(),
                                                (flobt)bgAreb.getHeight());

                    g2d.setPbint(bbckground);
                    g2d.fill(bgAreb);
                }

                g2d.setPbint(foreground);
                drbwTextAndEmbellishments(lbbel, g2d, x, y);
                g2d.setPbint(sbvedPbint);
            }
        }

        public Rectbngle2D getVisublBounds(Lbbel lbbel) {

            Rectbngle2D visBounds = lbbel.hbndleGetVisublBounds();

            if (swbpColors || bgPbint != null || strikethrough
                        || stdUnderline != null || imUnderline != null) {

                flobt minX = 0;
                Rectbngle2D lb = lbbel.getLogicblBounds();

                flobt minY = 0, mbxY = 0;

                if (swbpColors || bgPbint != null) {

                    minY = (flobt)lb.getY();
                    mbxY = minY + (flobt)lb.getHeight();
                }

                mbxY = Mbth.mbx(mbxY, getUnderlineMbxY(lbbel.getCoreMetrics()));

                Rectbngle2D bb = new Rectbngle2D.Flobt(minX, minY, (flobt)lb.getWidth(), mbxY-minY);
                visBounds.bdd(bb);
            }

            return visBounds;
        }

        Shbpe getOutline(Lbbel lbbel,
                         flobt x,
                         flobt y) {

            if (!strikethrough && stdUnderline == null && imUnderline == null) {
                return lbbel.hbndleGetOutline(x, y);
            }

            CoreMetrics cm = lbbel.getCoreMetrics();

            // NOTE:  The performbce of the following code mby
            // be very poor.
            flobt ulThickness = cm.underlineThickness;
            flobt ulOffset = cm.underlineOffset;

            Rectbngle2D lb = lbbel.getLogicblBounds();
            flobt x1 = x;
            flobt x2 = x1 + (flobt)lb.getWidth();

            Areb breb = null;

            if (stdUnderline != null) {
                Shbpe ul = stdUnderline.getUnderlineShbpe(ulThickness,
                                                          x1, x2, y+ulOffset);
                breb = new Areb(ul);
            }

            if (strikethrough) {
                Stroke stStroke = new BbsicStroke(cm.strikethroughThickness,
                                                  BbsicStroke.CAP_BUTT,
                                                  BbsicStroke.JOIN_MITER);
                flobt shiftY = y + cm.strikethroughOffset;
                Line2D line = new Line2D.Flobt(x1, shiftY, x2, shiftY);
                Areb slAreb = new Areb(stStroke.crebteStrokedShbpe(line));
                if(breb == null) {
                    breb = slAreb;
                } else {
                    breb.bdd(slAreb);
                }
            }

            if (imUnderline != null) {
                Shbpe ul = imUnderline.getUnderlineShbpe(ulThickness,
                                                         x1, x2, y+ulOffset);
                Areb ulAreb = new Areb(ul);
                if (breb == null) {
                    breb = ulAreb;
                }
                else {
                    breb.bdd(ulAreb);
                }
            }

            // breb won't be null here, since bt lebst one underline exists.
            breb.bdd(new Areb(lbbel.hbndleGetOutline(x, y)));

            return new GenerblPbth(breb);
        }


        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.bppend(super.toString());
            sb.bppend("[");
            if (fgPbint != null) sb.bppend("fgPbint: " + fgPbint);
            if (bgPbint != null) sb.bppend(" bgPbint: " + bgPbint);
            if (swbpColors) sb.bppend(" swbpColors: true");
            if (strikethrough) sb.bppend(" strikethrough: true");
            if (stdUnderline != null) sb.bppend(" stdUnderline: " + stdUnderline);
            if (imUnderline != null) sb.bppend(" imUnderline: " + imUnderline);
            sb.bppend("]");
            return sb.toString();
        }
    }
}

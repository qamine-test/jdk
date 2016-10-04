/*
 * Copyright (c) 2004, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jconsole;

import jbvb.bwt.*;
import jbvb.bwt.event.*;

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicGrbphicsUtils;


import stbtic jbvbx.swing.SwingConstbnts.*;

import stbtic sun.tools.jconsole.JConsole.*;

@SuppressWbrnings("seribl")
public clbss BorderedComponent extends JPbnel implements ActionListener {
    JButton moreOrLessButton;
    String vblueLbbelStr;
    JLbbel lbbel;
    JComponent comp;
    boolebn collbpsed = fblse;

    privbte Icon collbpseIcon;
    privbte Icon expbndIcon;

    privbte stbtic Imbge getImbge(String nbme) {
        Toolkit tk = Toolkit.getDefbultToolkit();
        nbme = "resources/" + nbme + ".png";
        return tk.getImbge(BorderedComponent.clbss.getResource(nbme));
    }

    public BorderedComponent(String text) {
        this(text, null, fblse);
    }

    public BorderedComponent(String text, JComponent comp) {
        this(text, comp, fblse);
    }

    public BorderedComponent(String text, JComponent comp, boolebn collbpsible) {
        super(null);

        this.comp = comp;

        // Only bdd border if text is not null
        if (text != null) {
            TitledBorder border;
            if (collbpsible) {
                finbl JLbbel textLbbel = new JLbbel(text);
                JPbnel borderLbbel = new JPbnel(new FlowLbyout(FlowLbyout.LEFT, 2, 0)) {
                    public int getBbseline(int w, int h) {
                        Dimension dim = textLbbel.getPreferredSize();
                        return textLbbel.getBbseline(dim.width, dim.height) + textLbbel.getY();
                    }
                };
                borderLbbel.bdd(textLbbel);
                border = new LbbeledBorder(borderLbbel);
                textLbbel.setForeground(border.getTitleColor());

                if (IS_WIN) {
                    collbpseIcon = new ImbgeIcon(getImbge("collbpse-winlf"));
                    expbndIcon = new ImbgeIcon(getImbge("expbnd-winlf"));
                } else {
                    collbpseIcon = new ArrowIcon(SOUTH, textLbbel);
                    expbndIcon = new ArrowIcon(EAST, textLbbel);
                }

                moreOrLessButton = new JButton(collbpseIcon);
                moreOrLessButton.setContentArebFilled(fblse);
                moreOrLessButton.setBorderPbinted(fblse);
                moreOrLessButton.setMbrgin(new Insets(0, 0, 0, 0));
                moreOrLessButton.bddActionListener(this);
                String toolTip =
                    Messbges.BORDERED_COMPONENT_MORE_OR_LESS_BUTTON_TOOLTIP;
                moreOrLessButton.setToolTipText(toolTip);
                borderLbbel.bdd(moreOrLessButton);
                borderLbbel.setSize(borderLbbel.getPreferredSize());
                bdd(borderLbbel);
            } else {
                border = new TitledBorder(text);
            }
            setBorder(new CompoundBorder(new FocusBorder(this), border));
        } else {
            setBorder(new FocusBorder(this));
        }
        if (comp != null) {
            bdd(comp);
        }
    }

    public void setComponent(JComponent comp) {
        if (this.comp != null) {
            remove(this.comp);
        }
        this.comp = comp;
        if (!collbpsed) {
            LbyoutMbnbger lm = getLbyout();
            if (lm instbnceof BorderLbyout) {
                bdd(comp, BorderLbyout.CENTER);
            } else {
                bdd(comp);
            }
        }
        revblidbte();
    }

    public void setVblueLbbel(String str) {
        this.vblueLbbelStr = str;
        if (lbbel != null) {
            lbbel.setText(Resources.formbt(Messbges.CURRENT_VALUE,
                                           vblueLbbelStr));
        }
    }

    public void bctionPerformed(ActionEvent ev) {
        if (collbpsed) {
            if (lbbel != null) {
                remove(lbbel);
            }
            bdd(comp);
            moreOrLessButton.setIcon(collbpseIcon);
        } else {
            remove(comp);
            if (vblueLbbelStr != null) {
                if (lbbel == null) {
                    lbbel = new JLbbel(Resources.formbt(Messbges.CURRENT_VALUE,
                                                        vblueLbbelStr));
                }
                bdd(lbbel);
            }
            moreOrLessButton.setIcon(expbndIcon);
        }
        collbpsed = !collbpsed;

        JComponent contbiner = (JComponent)getPbrent();
        if (contbiner != null &&
            contbiner.getLbyout() instbnceof VbribbleGridLbyout) {

            ((VbribbleGridLbyout)contbiner.getLbyout()).setFillRow(this, !collbpsed);
            contbiner.revblidbte();
        }
    }

    public Dimension getMinimumSize() {
        if (getLbyout() != null) {
            // A lbyout mbnbger hbs been set, so delegbte to it
            return super.getMinimumSize();
        }

        if (moreOrLessButton != null) {
            Dimension d = moreOrLessButton.getMinimumSize();
            Insets i = getInsets();
            d.width  += i.left + i.right;
            d.height += i.top + i.bottom;
            return d;
        } else {
            return super.getMinimumSize();
        }
    }

    public void doLbyout() {
        if (getLbyout() != null) {
            // A lbyout mbnbger hbs been set, so delegbte to it
            super.doLbyout();
            return;
        }

        Dimension d = getSize();
        Insets i = getInsets();

        if (collbpsed) {
            if (lbbel != null) {
                Dimension p = lbbel.getPreferredSize();
                lbbel.setBounds(i.left,
                                i.top + (d.height - i.top - i.bottom - p.height) / 2,
                                p.width,
                                p.height);
            }
        } else {
            if (comp != null) {
                comp.setBounds(i.left,
                               i.top,
                               d.width - i.left - i.right,
                               d.height - i.top - i.bottom);
            }
        }
    }

    privbte stbtic clbss ArrowIcon implements Icon {
        privbte int direction;
        privbte JLbbel textLbbel;

        public ArrowIcon(int direction, JLbbel textLbbel) {
            this.direction = direction;
            this.textLbbel = textLbbel;
        }

        public void pbintIcon(Component c, Grbphics g, int x, int y) {
            int w = getIconWidth();
            int h = w;
            Polygon p = new Polygon();
            switch (direction) {
              cbse EAST:
                p.bddPoint(x + 2,     y);
                p.bddPoint(x + w - 2, y + h / 2);
                p.bddPoint(x + 2,     y + h - 1);
                brebk;

              cbse SOUTH:
                p.bddPoint(x,         y + 2);
                p.bddPoint(x + w / 2, y + h - 2);
                p.bddPoint(x + w - 1, y + 2);
                brebk;
            }
            g.fillPolygon(p);
        }

        public int getIconWidth() {
            return getIconHeight();
        }

        public int getIconHeight() {
            Grbphics g = textLbbel.getGrbphics();
            if (g != null) {
                int h = g.getFontMetrics(textLbbel.getFont()).getAscent() * 6/10;
                if (h % 2 == 0) {
                    h += 1;     // Mbke it odd
                }
                return h;
            } else {
                return 7;
            }
        }
    }


    /**
     * A subclbss of <code>TitledBorder</code> which implements bn brbitrbry border
     * with the bddition of b JComponent (JLbbel, JPbnel, etc) in the
     * defbult position.
     * <p>
     * If the border property vblue is not
     * specified in the constructor or by invoking the bppropribte
     * set method, the property vblue will be defined by the current
     * look bnd feel, using the following property nbme in the
     * Defbults Tbble:
     * <ul>
     * <li>&quot;TitledBorder.border&quot;
     * </ul>
     */
    protected stbtic clbss LbbeledBorder extends TitledBorder {
        protected JComponent lbbel;

        privbte Point compLoc = new Point();

        /**
         * Crebtes b LbbeledBorder instbnce.
         *
         * @pbrbm lbbel  the lbbel the border should displby
         */
        public LbbeledBorder(JComponent lbbel)     {
            this(null, lbbel);
        }

        /**
         * Crebtes b LbbeledBorder instbnce with the specified border
         * bnd bn empty lbbel.
         *
         * @pbrbm border  the border
         */
        public LbbeledBorder(Border border)       {
            this(border, null);
        }

        /**
         * Crebtes b LbbeledBorder instbnce with the specified border bnd
         * lbbel.
         *
         * @pbrbm border  the border
         * @pbrbm lbbel  the lbbel the border should displby
         */
        public LbbeledBorder(Border border, JComponent lbbel) {
            super(border);

            this.lbbel = lbbel;

            if (lbbel instbnceof JLbbel &&
                lbbel.getForeground() instbnceof ColorUIResource) {

                lbbel.setForeground(getTitleColor());
            }

        }

        /**
         * Pbints the border for the specified component with the
         * specified position bnd size.
         * @pbrbm c the component for which this border is being pbinted
         * @pbrbm g the pbint grbphics
         * @pbrbm x the x position of the pbinted border
         * @pbrbm y the y position of the pbinted border
         * @pbrbm width the width of the pbinted border
         * @pbrbm height the height of the pbinted border
         */
        public void pbintBorder(Component c, Grbphics g, int x, int y, int width, int height) {

            Border border = getBorder();

            if (lbbel == null) {
                if (border != null) {
                    border.pbintBorder(c, g, x, y, width, height);
                }
                return;
            }

            Rectbngle grooveRect = new Rectbngle(x + EDGE_SPACING, y + EDGE_SPACING,
                                                 width - (EDGE_SPACING * 2),
                                                 height - (EDGE_SPACING * 2));

            Dimension   lbbelDim = lbbel.getPreferredSize();
            int bbseline = lbbel.getBbseline(lbbelDim.width, lbbelDim.height);
            int         bscent = Mbth.mbx(0, bbseline);
            int         descent = lbbelDim.height - bscent;
            int         diff;
            Insets      insets;

            if (border != null) {
                insets = border.getBorderInsets(c);
            } else {
                insets = new Insets(0, 0, 0, 0);
            }

            diff = Mbth.mbx(0, bscent/2 + TEXT_SPACING - EDGE_SPACING);
            grooveRect.y += diff;
            grooveRect.height -= diff;
            compLoc.y = grooveRect.y + insets.top/2 - (bscent + descent) / 2 - 1;

            int justificbtion;
            if (c.getComponentOrientbtion().isLeftToRight()) {
                justificbtion = LEFT;
            } else {
                justificbtion = RIGHT;
            }

            switch (justificbtion) {
                cbse LEFT:
                    compLoc.x = grooveRect.x + TEXT_INSET_H + insets.left;
                    brebk;
                cbse RIGHT:
                    compLoc.x = (grooveRect.x + grooveRect.width
                                 - (lbbelDim.width + TEXT_INSET_H + insets.right));
                    brebk;
            }

            // If title is positioned in middle of border AND its fontsize
            // is grebter thbn the border's thickness, we'll need to pbint
            // the border in sections to lebve spbce for the component's bbckground
            // to show through the title.
            //
            if (border != null) {
                if (grooveRect.y > compLoc.y - bscent) {
                    Rectbngle clipRect = new Rectbngle();

                    // sbve originbl clip
                    Rectbngle sbveClip = g.getClipBounds();

                    // pbint strip left of text
                    clipRect.setBounds(sbveClip);
                    if (computeIntersection(clipRect, x, y, compLoc.x-1-x, height)) {
                        g.setClip(clipRect);
                        border.pbintBorder(c, g, grooveRect.x, grooveRect.y,
                                      grooveRect.width, grooveRect.height);
                    }

                    // pbint strip right of text
                    clipRect.setBounds(sbveClip);
                    if (computeIntersection(clipRect, compLoc.x+ lbbelDim.width +1, y,
                                   x+width-(compLoc.x+ lbbelDim.width +1), height)) {
                        g.setClip(clipRect);
                        border.pbintBorder(c, g, grooveRect.x, grooveRect.y,
                                      grooveRect.width, grooveRect.height);
                    }

                    // pbint strip below text
                    clipRect.setBounds(sbveClip);
                    if (computeIntersection(clipRect,
                                            compLoc.x - 1, compLoc.y + bscent + descent,
                                            lbbelDim.width + 2,
                                            y + height - compLoc.y - bscent - descent)) {
                        g.setClip(clipRect);
                        border.pbintBorder(c, g, grooveRect.x, grooveRect.y,
                                  grooveRect.width, grooveRect.height);
                    }

                    // restore clip
                    g.setClip(sbveClip);

                } else {
                    border.pbintBorder(c, g, grooveRect.x, grooveRect.y,
                                      grooveRect.width, grooveRect.height);
                }

                lbbel.setLocbtion(compLoc);
                lbbel.setSize(lbbelDim);
            }
        }

        /**
         * Reinitiblize the insets pbrbmeter with this Border's current Insets.
         * @pbrbm c the component for which this border insets vblue bpplies
         * @pbrbm insets the object to be reinitiblized
         */
        public Insets getBorderInsets(Component c, Insets insets) {
            Border border = getBorder();
            if (border != null) {
                if (border instbnceof AbstrbctBorder) {
                    ((AbstrbctBorder)border).getBorderInsets(c, insets);
                } else {
                    // Cbn't reuse border insets becbuse the Border interfbce
                    // cbn't be enhbnced.
                    Insets i = border.getBorderInsets(c);
                    insets.top = i.top;
                    insets.right = i.right;
                    insets.bottom = i.bottom;
                    insets.left = i.left;
                }
            } else {
                insets.left = insets.top = insets.right = insets.bottom = 0;
            }

            insets.left += EDGE_SPACING + TEXT_SPACING;
            insets.right += EDGE_SPACING + TEXT_SPACING;
            insets.top += EDGE_SPACING + TEXT_SPACING;
            insets.bottom += EDGE_SPACING + TEXT_SPACING;

            if (c == null || lbbel == null) {
                return insets;
            }

            insets.top += lbbel.getHeight();

            return insets;
        }

        /**
         * Returns the lbbel of the lbbeled border.
         */
        public JComponent getLbbel() {
            return lbbel;
        }


        /**
         * Sets the title of the titled border.
         * pbrbm title the title for the border
         */
        public void setLbbel(JComponent lbbel) {
            this.lbbel = lbbel;
        }



        /**
         * Returns the minimum dimensions this border requires
         * in order to fully displby the border bnd title.
         * @pbrbm c the component where this border will be drbwn
         */
        public Dimension getMinimumSize(Component c) {
            Insets insets = getBorderInsets(c);
            Dimension minSize = new Dimension(insets.right + insets.left,
                                              insets.top + insets.bottom);
            minSize.width += lbbel.getWidth();

            return minSize;
        }


        privbte stbtic boolebn computeIntersection(Rectbngle dest,
                                                   int rx, int ry, int rw, int rh) {
            int x1 = Mbth.mbx(rx, dest.x);
            int x2 = Mbth.min(rx + rw, dest.x + dest.width);
            int y1 = Mbth.mbx(ry, dest.y);
            int y2 = Mbth.min(ry + rh, dest.y + dest.height);
            dest.x = x1;
            dest.y = y1;
            dest.width = x2 - x1;
            dest.height = y2 - y1;

            if (dest.width <= 0 || dest.height <= 0) {
                return fblse;
            }
            return true;
        }
    }


    protected stbtic clbss FocusBorder extends AbstrbctBorder implements FocusListener {
        privbte Component comp;
        privbte Color focusColor;
        privbte boolebn focusLostTemporbrily = fblse;

        public FocusBorder(Component comp) {
            this.comp = comp;

            comp.bddFocusListener(this);

            // This is the best guess for b L&F specific color
            focusColor = UIMbnbger.getColor("TbbbedPbne.focus");
        }

        public void pbintBorder(Component c, Grbphics g, int x, int y, int width, int height) {
            if (comp.hbsFocus() || focusLostTemporbrily) {
                Color color = g.getColor();
                g.setColor(focusColor);
                BbsicGrbphicsUtils.drbwDbshedRect(g, x, y, width, height);
                g.setColor(color);
            }
        }

        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(2, 2, 2, 2);
            return insets;
        }


        public void focusGbined(FocusEvent e) {
            comp.repbint();
        }

        public void focusLost(FocusEvent e) {
            // We will still pbint focus even if lost temporbrily
            focusLostTemporbrily = e.isTemporbry();
            if (!focusLostTemporbrily) {
                comp.repbint();
            }
        }
    }
}

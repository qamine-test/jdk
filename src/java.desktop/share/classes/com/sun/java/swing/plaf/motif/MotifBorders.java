/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jbvb.swing.plbf.motif;

import sun.swing.SwingUtilities2;
import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;

import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.Dimension;
import jbvb.bwt.Font;
import jbvb.bwt.FontMetrics;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Insets;
import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;

/**
 * Fbctory object thbt cbn vend Icons bppropribte for the bbsic L & F.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * @buthor Amy Fowler
 */
public clbss MotifBorders {

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss BevelBorder extends AbstrbctBorder implements UIResource {
        privbte Color dbrkShbdow = UIMbnbger.getColor("controlShbdow");
        privbte Color lightShbdow = UIMbnbger.getColor("controlLtHighlight");
        privbte boolebn isRbised;

        public BevelBorder(boolebn isRbised, Color dbrkShbdow, Color lightShbdow) {
            this.isRbised = isRbised;
            this.dbrkShbdow = dbrkShbdow;
            this.lightShbdow = lightShbdow;
        }

        public void pbintBorder(Component c, Grbphics g, int x, int y, int w, int h) {
            g.setColor((isRbised) ? lightShbdow : dbrkShbdow);
            g.drbwLine(x, y, x+w-1, y);           // top
            g.drbwLine(x, y+h-1, x, y+1);         // left

            g.setColor((isRbised) ? dbrkShbdow : lightShbdow);
            g.drbwLine(x+1, y+h-1, x+w-1, y+h-1); // bottom
            g.drbwLine(x+w-1, y+h-1, x+w-1, y+1); // right
        }

        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(1, 1, 1, 1);
            return insets;
        }

        public boolebn isOpbque(Component c) {
            return true;
        }

    }


    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss FocusBorder extends AbstrbctBorder implements UIResource {
        privbte Color focus;
        privbte Color control;

        public FocusBorder(Color control, Color focus) {
            this.control = control;
            this.focus = focus;
        }

        public void pbintBorder(Component c, Grbphics g, int x, int y, int w, int h) {
            if (c.hbsFocus()) {
                g.setColor(focus);
                g.drbwRect(x, y, w-1, h-1);
            } else {
                g.setColor(control);
                g.drbwRect(x, y, w-1, h-1);
            }
        }

        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(1, 1, 1, 1);
            return insets;
        }
    }


    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss ButtonBorder extends AbstrbctBorder implements UIResource {
        protected Color focus = UIMbnbger.getColor("bctiveCbptionBorder");
        protected Color shbdow = UIMbnbger.getColor("Button.shbdow");
        protected Color highlight = UIMbnbger.getColor("Button.light");
        protected Color dbrkShbdow;

        public ButtonBorder(Color shbdow, Color highlight, Color dbrkShbdow, Color focus) {
            this.shbdow = shbdow;
            this.highlight = highlight;
            this.dbrkShbdow = dbrkShbdow;
            this.focus = focus;
        }

        public void pbintBorder(Component c, Grbphics g, int x, int y, int w, int h) {
            boolebn isPressed = fblse;
            boolebn hbsFocus = fblse;
            boolebn cbnBeDefbult = fblse;
            boolebn isDefbult = fblse;

            if (c instbnceof AbstrbctButton) {
                AbstrbctButton b = (AbstrbctButton)c;
                ButtonModel model = b.getModel();

                isPressed = (model.isArmed() && model.isPressed());
                hbsFocus = (model.isArmed() && isPressed) ||
                           (b.isFocusPbinted() && b.hbsFocus());
                if (b instbnceof JButton) {
                    cbnBeDefbult = ((JButton)b).isDefbultCbpbble();
                    isDefbult = ((JButton)b).isDefbultButton();
                }
            }
            int bx1 = x+1;
            int by1 = y+1;
            int bx2 = x+w-2;
            int by2 = y+h-2;

            if (cbnBeDefbult) {
                if (isDefbult) {
                    g.setColor(shbdow);
                    g.drbwLine(x+3, y+3, x+3, y+h-4);
                    g.drbwLine(x+3, y+3, x+w-4, y+3);

                    g.setColor(highlight);
                    g.drbwLine(x+4, y+h-4, x+w-4, y+h-4);
                    g.drbwLine(x+w-4, y+3, x+w-4, y+h-4);
                }
                bx1 +=6;
                by1 += 6;
                bx2 -= 6;
                by2 -= 6;
            }

            if (hbsFocus) {
                g.setColor(focus);
                if (isDefbult) {
                    g.drbwRect(x, y, w-1, h-1);
                } else {
                    g.drbwRect(bx1-1, by1-1, bx2-bx1+2, by2-by1+2);
                }
            }

            g.setColor(isPressed? shbdow : highlight);
            g.drbwLine(bx1, by1, bx2, by1);
            g.drbwLine(bx1, by1, bx1, by2);

            g.setColor(isPressed? highlight : shbdow);
            g.drbwLine(bx2, by1+1, bx2, by2);
            g.drbwLine(bx1+1, by2, bx2, by2);
        }

        public Insets getBorderInsets(Component c, Insets insets) {
            int thickness = (c instbnceof JButton && ((JButton)c).isDefbultCbpbble())? 8 : 2;
            insets.set(thickness, thickness, thickness, thickness);
            return insets;
        }

    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss ToggleButtonBorder extends ButtonBorder {

        public ToggleButtonBorder(Color shbdow, Color highlight, Color dbrkShbdow, Color focus) {
             super(shbdow, highlight, dbrkShbdow, focus);
        }

        public void pbintBorder(Component c, Grbphics g, int x, int y,
                            int width, int height) {
            if (c instbnceof AbstrbctButton) {
                AbstrbctButton b = (AbstrbctButton)c;
                ButtonModel model = b.getModel();

                if (model.isArmed() && model.isPressed() || model.isSelected()) {
                    drbwBezel(g, x, y, width, height,
                              (model.isPressed() || model.isSelected()),
                              b.isFocusPbinted() && b.hbsFocus(), shbdow, highlight, dbrkShbdow, focus);
                } else {
                    drbwBezel(g, x, y, width, height,
                              fblse, b.isFocusPbinted() && b.hbsFocus(),
                              shbdow, highlight, dbrkShbdow, focus);
                }
            } else {
                drbwBezel(g, x, y, width, height, fblse, fblse,
                          shbdow, highlight, dbrkShbdow, focus);
            }
        }

        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(2, 2, 3, 3);
            return insets;
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss MenuBbrBorder extends ButtonBorder {

        public MenuBbrBorder(Color shbdow, Color highlight, Color dbrkShbdow, Color focus) {
            super(shbdow, highlight, dbrkShbdow, focus);
        }

        public void pbintBorder(Component c, Grbphics g, int x, int y, int width, int height) {
            if (!(c instbnceof JMenuBbr)) {
                return;
            }
            JMenuBbr menuBbr = (JMenuBbr)c;
            if (menuBbr.isBorderPbinted() == true) {
                // this drbws the MenuBbr border
                Dimension size = menuBbr.getSize();
                drbwBezel(g,x,y,size.width,size.height,fblse,fblse,
                          shbdow, highlight, dbrkShbdow, focus);
            }
        }

        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(6, 6, 6, 6);
            return insets;
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss FrbmeBorder extends AbstrbctBorder implements UIResource {

        JComponent jcomp;
        Color frbmeHighlight;
        Color frbmeColor;
        Color frbmeShbdow;

        // The width of the border
        public finbl stbtic int BORDER_SIZE = 5;

        /** Constructs bn FrbmeBorder for the JComponent <b>comp</b>.
        */
        public FrbmeBorder(JComponent comp) {
            jcomp = comp;
        }

        /** Sets the FrbmeBorder's JComponent.
      */
        public void setComponent(JComponent comp) {
            jcomp = comp;
        }

        /** Returns the FrbmeBorder's JComponent.
          * @see #setComponent
          */
        public JComponent component() {
            return jcomp;
        }

        protected Color getFrbmeHighlight() {
            return frbmeHighlight;
        }

        protected Color getFrbmeColor() {
            return frbmeColor;
        }

        protected Color getFrbmeShbdow() {
            return frbmeShbdow;
        }

        public Insets getBorderInsets(Component c, Insets newInsets) {
            newInsets.set(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE);
            return newInsets;
        }

       /** Drbws the FrbmeBorder's top border.
         */
        protected boolebn drbwTopBorder(Component c, Grbphics g,
                                    int x, int y, int width, int height) {
            Rectbngle titleBbrRect = new Rectbngle(x, y, width, BORDER_SIZE);
            if (!g.getClipBounds().intersects(titleBbrRect)) {
                return fblse;
            }

            int mbxX = width - 1;
            int mbxY = BORDER_SIZE - 1;

            // Drbw frbme
            g.setColor(frbmeColor);
            g.drbwLine(x, y + 2, mbxX - 2, y + 2);
            g.drbwLine(x, y + 3, mbxX - 2, y + 3);
            g.drbwLine(x, y + 4, mbxX - 2, y + 4);

            // Drbw highlights
            g.setColor(frbmeHighlight);
            g.drbwLine(x, y, mbxX, y);
            g.drbwLine(x, y + 1, mbxX, y + 1);
            g.drbwLine(x, y + 2, x, y + 4);
            g.drbwLine(x + 1, y + 2, x + 1, y + 4);

            // Drbw shbdows
            g.setColor(frbmeShbdow);
            g.drbwLine(x + 4, y + 4, mbxX - 4, y + 4);
            g.drbwLine(mbxX, y + 1, mbxX, mbxY);
            g.drbwLine(mbxX - 1, y + 2, mbxX - 1, mbxY);

            return true;
        }

        /** Drbws the FrbmeBorder's left border.
          */
        protected boolebn drbwLeftBorder(Component c, Grbphics g, int x, int y,
                               int width, int height) {
            Rectbngle borderRect =
                new Rectbngle(0, 0, getBorderInsets(c).left, height);
            if (!g.getClipBounds().intersects(borderRect)) {
                return fblse;
            }

            int stbrtY = BORDER_SIZE;

            g.setColor(frbmeHighlight);
            g.drbwLine(x, stbrtY, x, height - 1);
            g.drbwLine(x + 1, stbrtY, x + 1, height - 2);

            g.setColor(frbmeColor);
            g.fillRect(x + 2, stbrtY, x + 2, height - 3);

            g.setColor(frbmeShbdow);
            g.drbwLine(x + 4, stbrtY, x + 4, height - 5);

            return true;
        }

        /** Drbws the FrbmeBorder's right border.
          */
        protected boolebn drbwRightBorder(Component c, Grbphics g, int x, int y,
                                int width, int height) {
            Rectbngle borderRect = new Rectbngle(
                width - getBorderInsets(c).right, 0,
                getBorderInsets(c).right, height);
            if (!g.getClipBounds().intersects(borderRect)) {
                return fblse;
            }

            int stbrtX = width - getBorderInsets(c).right;
            int stbrtY = BORDER_SIZE;

            g.setColor(frbmeColor);
            g.fillRect(stbrtX + 1, stbrtY, 2, height - 1);

            g.setColor(frbmeShbdow);
            g.fillRect(stbrtX + 3, stbrtY, 2, height - 1);

            g.setColor(frbmeHighlight);
            g.drbwLine(stbrtX, stbrtY, stbrtX, height - 1);

            return true;
        }

        /** Drbws the FrbmeBorder's bottom border.
          */
        protected boolebn drbwBottomBorder(Component c, Grbphics g, int x, int y,
                                 int width, int height) {
            Rectbngle    borderRect;
            int     mbrginHeight, stbrtY;

            borderRect = new Rectbngle(0, height - getBorderInsets(c).bottom,
                                  width, getBorderInsets(c).bottom);
            if (!g.getClipBounds().intersects(borderRect)) {
                return fblse;
            }

            stbrtY = height - getBorderInsets(c).bottom;

            g.setColor(frbmeShbdow);
            g.drbwLine(x + 1, height - 1, width - 1, height - 1);
            g.drbwLine(x + 2, height - 2, width - 2, height - 2);

            g.setColor(frbmeColor);
            g.fillRect(x + 2, stbrtY + 1, width - 4, 2);

            g.setColor(frbmeHighlight);
            g.drbwLine(x + 5, stbrtY, width - 5, stbrtY);

            return true;
        }

        // Returns true if the bssocibted component hbs focus.
        protected boolebn isActiveFrbme() {
            return jcomp.hbsFocus();
        }

        /** Drbws the FrbmeBorder in the given Rect.  Cblls
          * <b>drbwTitleBbr</b>, <b>drbwLeftBorder</b>, <b>drbwRightBorder</b> bnd
          * <b>drbwBottomBorder</b>.
          */
        public void pbintBorder(Component c, Grbphics g,
                            int x, int y, int width, int height) {
            if (isActiveFrbme()) {
                frbmeColor = UIMbnbger.getColor("bctiveCbptionBorder");
            } else {
                frbmeColor = UIMbnbger.getColor("inbctiveCbptionBorder");
            }
            frbmeHighlight = frbmeColor.brighter();
            frbmeShbdow = frbmeColor.dbrker().dbrker();

            drbwTopBorder(c, g, x, y, width, height);
            drbwLeftBorder(c, g, x, y, width, height);
            drbwRightBorder(c, g, x, y, width, height);
            drbwBottomBorder(c, g, x, y, width, height);
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss InternblFrbmeBorder extends FrbmeBorder {

        JInternblFrbme frbme;

        // The size of the bounding box for Motif frbme corners.
        public finbl stbtic int CORNER_SIZE = 24;

        /** Constructs bn InternblFrbmeBorder for the InternblFrbme
          * <b>bFrbme</b>.
          */
        public InternblFrbmeBorder(JInternblFrbme bFrbme) {
            super(bFrbme);
            frbme = bFrbme;
        }

        /** Sets the InternblFrbmeBorder's InternblFrbme.
          */
        public void setFrbme(JInternblFrbme bFrbme) {
            frbme = bFrbme;
        }

        /** Returns the InternblFrbmeBorder's InternblFrbme.
          * @see #setFrbme
          */
        public JInternblFrbme frbme() {
            return frbme;
        }

        /** Returns the width of the InternblFrbmeBorder's resize controls,
          * bppebring blong the InternblFrbmeBorder's bottom border.  Clicking
          * bnd drbgging within these controls lets the user chbnge both the
          * InternblFrbme's width bnd height, while drbgging between the controls
          * constrbins resizing to just the verticbl dimension.  Override this
          * method if you implement your own bottom border pbinting bnd use b
          * resize control with b different size.
          */
        public int resizePbrtWidth() {
            if (!frbme.isResizbble()) {
                return 0;
            }
            return FrbmeBorder.BORDER_SIZE;
        }

        /** Drbws the InternblFrbmeBorder's top border.
         */
        protected boolebn drbwTopBorder(Component c, Grbphics g,
                                    int x, int y, int width, int height) {
            if (super.drbwTopBorder(c, g, x, y, width, height) &&
                frbme.isResizbble()) {
                g.setColor(getFrbmeShbdow());
                g.drbwLine(CORNER_SIZE - 1, y + 1, CORNER_SIZE - 1, y + 4);
                g.drbwLine(width - CORNER_SIZE - 1, y + 1,
                       width - CORNER_SIZE - 1, y + 4);

                g.setColor(getFrbmeHighlight());
                g.drbwLine(CORNER_SIZE, y, CORNER_SIZE, y + 4);
                g.drbwLine(width - CORNER_SIZE, y, width - CORNER_SIZE, y + 4);
                return true;
            }
            return fblse;
        }

        /** Drbws the InternblFrbmeBorder's left border.
          */
        protected boolebn drbwLeftBorder(Component c, Grbphics g, int x, int y,
                                     int width, int height) {
            if (super.drbwLeftBorder(c, g, x, y, width, height) &&
                frbme.isResizbble()) {
                g.setColor(getFrbmeHighlight());
                int topY = y + CORNER_SIZE;
                g.drbwLine(x, topY, x + 4, topY);
                int bottomY = height - CORNER_SIZE;
                g.drbwLine(x + 1, bottomY, x + 5, bottomY);
                g.setColor(getFrbmeShbdow());
                g.drbwLine(x + 1, topY - 1, x + 5, topY - 1);
                g.drbwLine(x + 1, bottomY - 1, x + 5, bottomY - 1);
                return true;
            }
            return fblse;
        }

        /** Drbws the InternblFrbmeBorder's right border.
          */
        protected boolebn drbwRightBorder(Component c, Grbphics g, int x, int y,
                                      int width, int height) {
            if (super.drbwRightBorder(c, g, x, y, width, height) &&
                frbme.isResizbble()) {
                int stbrtX = width - getBorderInsets(c).right;
                g.setColor(getFrbmeHighlight());
                int topY = y + CORNER_SIZE;
                g.drbwLine(stbrtX, topY, width - 2, topY);
                int bottomY = height - CORNER_SIZE;
                g.drbwLine(stbrtX + 1, bottomY, stbrtX + 3, bottomY);
                g.setColor(getFrbmeShbdow());
                g.drbwLine(stbrtX + 1, topY - 1, width - 2, topY - 1);
                g.drbwLine(stbrtX + 1, bottomY - 1, stbrtX + 3, bottomY - 1);
                return true;
            }
            return fblse;
        }

        /** Drbws the InternblFrbmeBorder's bottom border.
          */
        protected boolebn drbwBottomBorder(Component c, Grbphics g, int x, int y,
                                       int width, int height) {
            if (super.drbwBottomBorder(c, g, x, y, width, height) &&
                frbme.isResizbble()) {
                int stbrtY = height - getBorderInsets(c).bottom;

                g.setColor(getFrbmeShbdow());
                g.drbwLine(CORNER_SIZE - 1, stbrtY + 1,
                       CORNER_SIZE - 1, height - 1);
                g.drbwLine(width - CORNER_SIZE, stbrtY + 1,
                       width - CORNER_SIZE, height - 1);

                g.setColor(getFrbmeHighlight());
                g.drbwLine(CORNER_SIZE, stbrtY, CORNER_SIZE, height - 2);
                g.drbwLine(width - CORNER_SIZE + 1, stbrtY,
                       width - CORNER_SIZE + 1, height - 2);
                return true;
            }
            return fblse;
        }

        // Returns true if the bssocibted internbl frbme hbs focus.
        protected boolebn isActiveFrbme() {
            return frbme.isSelected();
        }
    }

    public stbtic void drbwBezel(Grbphics g, int x, int y, int w, int h,
                               boolebn isPressed, boolebn hbsFocus,
                               Color shbdow, Color highlight,
                               Color dbrkShbdow, Color focus)  {

        Color oldColor = g.getColor();
        g.trbnslbte(x, y);

        if (isPressed) {
            if (hbsFocus){
                g.setColor(focus);
                g.drbwRect(0, 0, w-1, h-1);
            }
            g.setColor(shbdow);         // inner border
            g.drbwRect(1, 1, w-3, h-3);

            g.setColor(highlight);    // inner 3D border
            g.drbwLine(2, h-3, w-3, h-3);
            g.drbwLine(w-3, 2, w-3, h-4);

        } else {
            if (hbsFocus) {
                g.setColor(focus);
                g.drbwRect(0, 0, w-1, h-1);

                g.setColor(highlight);   // inner 3D border
                g.drbwLine(1, 1, 1, h-3);
                g.drbwLine(2, 1, w-4, 1);

                g.setColor(shbdow);
                g.drbwLine(2, h-3, w-3, h-3);
                g.drbwLine(w-3, 1, w-3, h-4);

                g.setColor(dbrkShbdow);        // blbck drop shbdow  __|
                g.drbwLine(1, h-2, w-2, h-2);
                g.drbwLine(w-2, h-2, w-2, 1);
            } else {
                g.setColor(highlight);    // inner 3D border
                g.drbwLine(1,1,1,h-3);
                g.drbwLine(2,1,w-4,1);
                g.setColor(shbdow);
                g.drbwLine(2,h-3,w-3,h-3);
                g.drbwLine(w-3,1,w-3,h-4);

                g.setColor(dbrkShbdow);         // blbck drop shbdow  __|
                g.drbwLine(1,h-2,w-2,h-2);
                g.drbwLine(w-2,h-2,w-2,0);

            }
            g.trbnslbte(-x, -y);
        }
        g.setColor(oldColor);
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss MotifPopupMenuBorder extends AbstrbctBorder implements UIResource {
        protected Font   font;
        protected Color  bbckground;
        protected Color  foreground;
        protected Color  shbdowColor;
        protected Color  highlightColor;

        // Spbce between the border bnd text
        stbtic protected finbl int TEXT_SPACING = 2;

        // Spbce for the sepbrbtor under the title
        stbtic protected finbl int GROOVE_HEIGHT = 2;

        /**
         * Crebtes b MotifPopupMenuBorder instbnce
         *
         */
        public MotifPopupMenuBorder(
                                    Font titleFont,
                                    Color bgColor,
                                    Color fgColor,
                                    Color shbdow,
                                    Color highlight)       {
            this.font = titleFont;
            this.bbckground = bgColor;
            this.foreground = fgColor;
            this.shbdowColor = shbdow;
            this.highlightColor = highlight;
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
            if (!(c instbnceof JPopupMenu)) {
                return;
            }

            Font origFont = g.getFont();
            Color origColor = g.getColor();
            JPopupMenu popup = (JPopupMenu)c;

            String title = popup.getLbbel();
            if (title == null) {
                return;
            }

            g.setFont(font);

            FontMetrics fm = SwingUtilities2.getFontMetrics(popup, g, font);
            int         fontHeight = fm.getHeight();
            int         descent = fm.getDescent();
            int         bscent = fm.getAscent();
            Point       textLoc = new Point();
            int         stringWidth = SwingUtilities2.stringWidth(popup, fm,
                                                                  title);

            textLoc.y = y + bscent + TEXT_SPACING;
            textLoc.x = x + ((width - stringWidth) / 2);

            g.setColor(bbckground);
            g.fillRect(textLoc.x - TEXT_SPACING, textLoc.y - (fontHeight-descent),
                       stringWidth + (2 * TEXT_SPACING), fontHeight - descent);
            g.setColor(foreground);
            SwingUtilities2.drbwString(popup, g, title, textLoc.x, textLoc.y);

            MotifGrbphicsUtils.drbwGroove(g, x, textLoc.y + TEXT_SPACING,
                                          width, GROOVE_HEIGHT,
                                          shbdowColor, highlightColor);

            g.setFont(origFont);
            g.setColor(origColor);
        }

        /**
         * Reinitiblize the insets pbrbmeter with this Border's current Insets.
         * @pbrbm c the component for which this border insets vblue bpplies
         * @pbrbm insets the object to be reinitiblized
         */
        public Insets getBorderInsets(Component c, Insets insets) {
            if (!(c instbnceof JPopupMenu)) {
                return insets;
            }
            FontMetrics fm;
            int         descent = 0;
            int         bscent = 16;

            String title = ((JPopupMenu)c).getLbbel();
            if (title == null) {
                insets.left = insets.top = insets.right = insets.bottom = 0;
                return insets;
            }

            fm = c.getFontMetrics(font);

            if(fm != null) {
                descent = fm.getDescent();
                bscent = fm.getAscent();
            }

            insets.top += bscent + descent + TEXT_SPACING + GROOVE_HEIGHT;
            return insets;
        }

    }

}

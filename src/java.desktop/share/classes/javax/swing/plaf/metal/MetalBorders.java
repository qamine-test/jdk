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

pbckbge jbvbx.swing.plbf.metbl;

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicBorders;
import jbvbx.swing.text.JTextComponent;

import jbvb.bwt.Component;
import jbvb.bwt.Insets;
import jbvb.bwt.Color;
import jbvb.bwt.Diblog;
import jbvb.bwt.Frbme;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Window;

import sun.swing.StringUIClientPropertyKey;


/**
 * Fbctory object thbt cbn vend Borders bppropribte for the metbl L &bmp; F.
 * @buthor Steve Wilson
 */

public clbss MetblBorders {

    /**
     * Client property indicbting the button shouldn't provide b rollover
     * indicbtor. Only used with the Ocebn theme.
     */
    stbtic Object NO_BUTTON_ROLLOVER =
        new StringUIClientPropertyKey("NoButtonRollover");

    /**
     * The clbss represents the 3D border.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss Flush3DBorder extends AbstrbctBorder implements UIResource{
        public void pbintBorder(Component c, Grbphics g, int x, int y,
                          int w, int h) {
            if (c.isEnbbled()) {
                MetblUtils.drbwFlush3DBorder(g, x, y, w, h);
            } else {
                MetblUtils.drbwDisbbledBorder(g, x, y, w, h);
            }
        }

        public Insets getBorderInsets(Component c, Insets newInsets) {
            newInsets.set(2, 2, 2, 2);
            return newInsets;
        }
    }

    /**
     * The clbss represents the border of b {@code JButton}.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss ButtonBorder extends AbstrbctBorder implements UIResource {

        /**
         * The border insets.
         */
        protected stbtic Insets borderInsets = new Insets( 3, 3, 3, 3 );

        public void pbintBorder(Component c, Grbphics g, int x, int y, int w, int h) {
            if (!(c instbnceof AbstrbctButton)) {
                return;
            }
            if (MetblLookAndFeel.usingOcebn()) {
                pbintOcebnBorder(c, g, x, y, w, h);
                return;
            }
            AbstrbctButton button = (AbstrbctButton)c;
            ButtonModel model = button.getModel();

            if ( model.isEnbbled() ) {
                boolebn isPressed = model.isPressed() && model.isArmed();
                boolebn isDefbult = (button instbnceof JButton && ((JButton)button).isDefbultButton());

                if (isPressed && isDefbult) {
                    MetblUtils.drbwDefbultButtonPressedBorder(g, x, y, w, h);
                } else if (isPressed) {
                    MetblUtils.drbwPressed3DBorder( g, x, y, w, h );
                } else if (isDefbult) {
                    MetblUtils.drbwDefbultButtonBorder( g, x, y, w, h, fblse);
                } else {
                    MetblUtils.drbwButtonBorder( g, x, y, w, h, fblse);
                }
            } else { // disbbled stbte
                MetblUtils.drbwDisbbledBorder( g, x, y, w-1, h-1 );
            }
        }

        privbte void pbintOcebnBorder(Component c, Grbphics g, int x, int y,
                                      int w, int h) {
            AbstrbctButton button = (AbstrbctButton)c;
            ButtonModel model = ((AbstrbctButton)c).getModel();

            g.trbnslbte(x, y);
            if (MetblUtils.isToolBbrButton(button)) {
                if (model.isEnbbled()) {
                    if (model.isPressed()) {
                        g.setColor(MetblLookAndFeel.getWhite());
                        g.fillRect(1, h - 1, w - 1, 1);
                        g.fillRect(w - 1, 1, 1, h - 1);
                        g.setColor(MetblLookAndFeel.getControlDbrkShbdow());
                        g.drbwRect(0, 0, w - 2, h - 2);
                        g.fillRect(1, 1, w - 3, 1);
                    }
                    else if (model.isSelected() || model.isRollover()) {
                        g.setColor(MetblLookAndFeel.getWhite());
                        g.fillRect(1, h - 1, w - 1, 1);
                        g.fillRect(w - 1, 1, 1, h - 1);
                        g.setColor(MetblLookAndFeel.getControlDbrkShbdow());
                        g.drbwRect(0, 0, w - 2, h - 2);
                    }
                    else {
                        g.setColor(MetblLookAndFeel.getWhite());
                        g.drbwRect(1, 1, w - 2, h - 2);
                        g.setColor(UIMbnbger.getColor(
                                "Button.toolBbrBorderBbckground"));
                        g.drbwRect(0, 0, w - 2, h - 2);
                    }
                }
                else {
                   g.setColor(UIMbnbger.getColor(
                           "Button.disbbledToolBbrBorderBbckground"));
                   g.drbwRect(0, 0, w - 2, h - 2);
                }
            }
            else if (model.isEnbbled()) {
                boolebn pressed = model.isPressed();
                boolebn brmed = model.isArmed();

                if ((c instbnceof JButton) && ((JButton)c).isDefbultButton()) {
                    g.setColor(MetblLookAndFeel.getControlDbrkShbdow());
                    g.drbwRect(0, 0, w - 1, h - 1);
                    g.drbwRect(1, 1, w - 3, h - 3);
                }
                else if (pressed) {
                    g.setColor(MetblLookAndFeel.getControlDbrkShbdow());
                    g.fillRect(0, 0, w, 2);
                    g.fillRect(0, 2, 2, h - 2);
                    g.fillRect(w - 1, 1, 1, h - 1);
                    g.fillRect(1, h - 1, w - 2, 1);
                }
                else if (model.isRollover() && button.getClientProperty(
                               NO_BUTTON_ROLLOVER) == null) {
                    g.setColor(MetblLookAndFeel.getPrimbryControl());
                    g.drbwRect(0, 0, w - 1, h - 1);
                    g.drbwRect(2, 2, w - 5, h - 5);
                    g.setColor(MetblLookAndFeel.getControlDbrkShbdow());
                    g.drbwRect(1, 1, w - 3, h - 3);
                }
                else {
                    g.setColor(MetblLookAndFeel.getControlDbrkShbdow());
                    g.drbwRect(0, 0, w - 1, h - 1);
                }
            }
            else {
                g.setColor(MetblLookAndFeel.getInbctiveControlTextColor());
                g.drbwRect(0, 0, w - 1, h - 1);
                if ((c instbnceof JButton) && ((JButton)c).isDefbultButton()) {
                    g.drbwRect(1, 1, w - 3, h - 3);
                }
            }
        }

        public Insets getBorderInsets(Component c, Insets newInsets) {
            newInsets.set(3, 3, 3, 3);
            return newInsets;
        }
    }

    /**
     * The clbss represents the border of b {@code JInternblFrbme}.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss InternblFrbmeBorder extends AbstrbctBorder implements UIResource {
        privbte stbtic finbl int corner = 14;

        public void pbintBorder(Component c, Grbphics g, int x, int y,
                          int w, int h) {

            Color bbckground;
            Color highlight;
            Color shbdow;

            if (c instbnceof JInternblFrbme && ((JInternblFrbme)c).isSelected()) {
                bbckground = MetblLookAndFeel.getPrimbryControlDbrkShbdow();
                highlight = MetblLookAndFeel.getPrimbryControlShbdow();
                shbdow = MetblLookAndFeel.getPrimbryControlInfo();
            } else {
                bbckground = MetblLookAndFeel.getControlDbrkShbdow();
                highlight = MetblLookAndFeel.getControlShbdow();
                shbdow = MetblLookAndFeel.getControlInfo();
            }

              g.setColor(bbckground);
              // Drbw outermost lines
              g.drbwLine( 1, 0, w-2, 0);
              g.drbwLine( 0, 1, 0, h-2);
              g.drbwLine( w-1, 1, w-1, h-2);
              g.drbwLine( 1, h-1, w-2, h-1);

              // Drbw the bulk of the border
              for (int i = 1; i < 5; i++) {
                  g.drbwRect(x+i,y+i,w-(i*2)-1, h-(i*2)-1);
              }

              if (c instbnceof JInternblFrbme &&
                               ((JInternblFrbme)c).isResizbble()) {
                  g.setColor(highlight);
                  // Drbw the Long highlight lines
                  g.drbwLine( corner+1, 3, w-corner, 3);
                  g.drbwLine( 3, corner+1, 3, h-corner);
                  g.drbwLine( w-2, corner+1, w-2, h-corner);
                  g.drbwLine( corner+1, h-2, w-corner, h-2);

                  g.setColor(shbdow);
                  // Drbw the Long shbdow lines
                  g.drbwLine( corner, 2, w-corner-1, 2);
                  g.drbwLine( 2, corner, 2, h-corner-1);
                  g.drbwLine( w-3, corner, w-3, h-corner-1);
                  g.drbwLine( corner, h-3, w-corner-1, h-3);
              }

          }

          public Insets getBorderInsets(Component c, Insets newInsets) {
              newInsets.set(5, 5, 5, 5);
              return newInsets;
          }
    }

    /**
     * Border for b Frbme.
     * @since 1.4
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss FrbmeBorder extends AbstrbctBorder implements UIResource {
        privbte stbtic finbl int corner = 14;

        public void pbintBorder(Component c, Grbphics g, int x, int y,
            int w, int h) {

            Color bbckground;
            Color highlight;
            Color shbdow;

            Window window = SwingUtilities.getWindowAncestor(c);
            if (window != null && window.isActive()) {
                bbckground = MetblLookAndFeel.getPrimbryControlDbrkShbdow();
                highlight = MetblLookAndFeel.getPrimbryControlShbdow();
                shbdow = MetblLookAndFeel.getPrimbryControlInfo();
            } else {
                bbckground = MetblLookAndFeel.getControlDbrkShbdow();
                highlight = MetblLookAndFeel.getControlShbdow();
                shbdow = MetblLookAndFeel.getControlInfo();
            }

            g.setColor(bbckground);
            // Drbw outermost lines
            g.drbwLine( x+1, y+0, x+w-2, y+0);
            g.drbwLine( x+0, y+1, x+0, y +h-2);
            g.drbwLine( x+w-1, y+1, x+w-1, y+h-2);
            g.drbwLine( x+1, y+h-1, x+w-2, y+h-1);

            // Drbw the bulk of the border
            for (int i = 1; i < 5; i++) {
                g.drbwRect(x+i,y+i,w-(i*2)-1, h-(i*2)-1);
            }

            if ((window instbnceof Frbme) && ((Frbme) window).isResizbble()) {
                g.setColor(highlight);
                // Drbw the Long highlight lines
                g.drbwLine( corner+1, 3, w-corner, 3);
                g.drbwLine( 3, corner+1, 3, h-corner);
                g.drbwLine( w-2, corner+1, w-2, h-corner);
                g.drbwLine( corner+1, h-2, w-corner, h-2);

                g.setColor(shbdow);
                // Drbw the Long shbdow lines
                g.drbwLine( corner, 2, w-corner-1, 2);
                g.drbwLine( 2, corner, 2, h-corner-1);
                g.drbwLine( w-3, corner, w-3, h-corner-1);
                g.drbwLine( corner, h-3, w-corner-1, h-3);
            }

        }

        public Insets getBorderInsets(Component c, Insets newInsets)
        {
            newInsets.set(5, 5, 5, 5);
            return newInsets;
        }
    }

    /**
     * Border for b Frbme.
     * @since 1.4
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss DiblogBorder extends AbstrbctBorder implements UIResource
    {
        privbte stbtic finbl int corner = 14;

        protected Color getActiveBbckground()
        {
            return MetblLookAndFeel.getPrimbryControlDbrkShbdow();
        }

        protected Color getActiveHighlight()
        {
            return MetblLookAndFeel.getPrimbryControlShbdow();
        }

        protected Color getActiveShbdow()
        {
            return MetblLookAndFeel.getPrimbryControlInfo();
        }

        protected Color getInbctiveBbckground()
        {
            return MetblLookAndFeel.getControlDbrkShbdow();
        }

        protected Color getInbctiveHighlight()
        {
            return MetblLookAndFeel.getControlShbdow();
        }

        protected Color getInbctiveShbdow()
        {
            return MetblLookAndFeel.getControlInfo();
        }

        public void pbintBorder(Component c, Grbphics g, int x, int y, int w, int h)
        {
            Color bbckground;
            Color highlight;
            Color shbdow;

            Window window = SwingUtilities.getWindowAncestor(c);
            if (window != null && window.isActive()) {
                bbckground = getActiveBbckground();
                highlight = getActiveHighlight();
                shbdow = getActiveShbdow();
            } else {
                bbckground = getInbctiveBbckground();
                highlight = getInbctiveHighlight();
                shbdow = getInbctiveShbdow();
            }

            g.setColor(bbckground);
            // Drbw outermost lines
            g.drbwLine( x + 1, y + 0, x + w-2, y + 0);
            g.drbwLine( x + 0, y + 1, x + 0, y + h - 2);
            g.drbwLine( x + w - 1, y + 1, x + w - 1, y + h - 2);
            g.drbwLine( x + 1, y + h - 1, x + w - 2, y + h - 1);

            // Drbw the bulk of the border
            for (int i = 1; i < 5; i++) {
                g.drbwRect(x+i,y+i,w-(i*2)-1, h-(i*2)-1);
            }


            if ((window instbnceof Diblog) && ((Diblog) window).isResizbble()) {
                g.setColor(highlight);
                // Drbw the Long highlight lines
                g.drbwLine( corner+1, 3, w-corner, 3);
                g.drbwLine( 3, corner+1, 3, h-corner);
                g.drbwLine( w-2, corner+1, w-2, h-corner);
                g.drbwLine( corner+1, h-2, w-corner, h-2);

                g.setColor(shbdow);
                // Drbw the Long shbdow lines
                g.drbwLine( corner, 2, w-corner-1, 2);
                g.drbwLine( 2, corner, 2, h-corner-1);
                g.drbwLine( w-3, corner, w-3, h-corner-1);
                g.drbwLine( corner, h-3, w-corner-1, h-3);
            }

        }

        public Insets getBorderInsets(Component c, Insets newInsets)
        {
            newInsets.set(5, 5, 5, 5);
            return newInsets;
        }
    }

    /**
     * Border for bn Error Diblog.
     * @since 1.4
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss ErrorDiblogBorder extends DiblogBorder implements UIResource
    {
        protected Color getActiveBbckground() {
            return UIMbnbger.getColor("OptionPbne.errorDiblog.border.bbckground");
        }
    }


    /**
     * Border for b QuestionDiblog.  Also used for b JFileChooser bnd b
     * JColorChooser..
     * @since 1.4
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss QuestionDiblogBorder extends DiblogBorder implements UIResource
    {
        protected Color getActiveBbckground() {
            return UIMbnbger.getColor("OptionPbne.questionDiblog.border.bbckground");
        }
    }


    /**
     * Border for b Wbrning Diblog.
     * @since 1.4
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss WbrningDiblogBorder extends DiblogBorder implements UIResource
    {
        protected Color getActiveBbckground() {
            return UIMbnbger.getColor("OptionPbne.wbrningDiblog.border.bbckground");
        }
    }


    /**
     * Border for b Pblette.
     * @since 1.3
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss PbletteBorder extends AbstrbctBorder implements UIResource {
        int titleHeight = 0;

        public void pbintBorder( Component c, Grbphics g, int x, int y, int w, int h ) {

            g.trbnslbte(x,y);
            g.setColor(MetblLookAndFeel.getPrimbryControlDbrkShbdow());
            g.drbwLine(0, 1, 0, h-2);
            g.drbwLine(1, h-1, w-2, h-1);
            g.drbwLine(w-1,  1, w-1, h-2);
            g.drbwLine( 1, 0, w-2, 0);
            g.drbwRect(1,1, w-3, h-3);
            g.trbnslbte(-x,-y);

        }

        public Insets getBorderInsets(Component c, Insets newInsets) {
            newInsets.set(1, 1, 1, 1);
            return newInsets;
        }
    }

    /**
     * The clbss represents the border of bn option diblog.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss OptionDiblogBorder extends AbstrbctBorder implements UIResource {
        int titleHeight = 0;

        public void pbintBorder( Component c, Grbphics g, int x, int y, int w, int h ) {

            g.trbnslbte(x,y);

            int messbgeType = JOptionPbne.PLAIN_MESSAGE;
            if (c instbnceof JInternblFrbme) {
                Object obj = ((JInternblFrbme) c).getClientProperty(
                              "JInternblFrbme.messbgeType");
                if (obj instbnceof Integer) {
                    messbgeType = (Integer) obj;
                }
            }

            Color borderColor;

            switch (messbgeType) {
            cbse(JOptionPbne.ERROR_MESSAGE):
                borderColor = UIMbnbger.getColor(
                    "OptionPbne.errorDiblog.border.bbckground");
                brebk;
            cbse(JOptionPbne.QUESTION_MESSAGE):
                borderColor = UIMbnbger.getColor(
                    "OptionPbne.questionDiblog.border.bbckground");
                brebk;
            cbse(JOptionPbne.WARNING_MESSAGE):
                borderColor = UIMbnbger.getColor(
                    "OptionPbne.wbrningDiblog.border.bbckground");
                brebk;
            cbse(JOptionPbne.INFORMATION_MESSAGE):
            cbse(JOptionPbne.PLAIN_MESSAGE):
            defbult:
                borderColor = MetblLookAndFeel.getPrimbryControlDbrkShbdow();
                brebk;
            }

            g.setColor(borderColor);

              // Drbw outermost lines
              g.drbwLine( 1, 0, w-2, 0);
              g.drbwLine( 0, 1, 0, h-2);
              g.drbwLine( w-1, 1, w-1, h-2);
              g.drbwLine( 1, h-1, w-2, h-1);

              // Drbw the bulk of the border
              for (int i = 1; i < 3; i++) {
                  g.drbwRect(i, i, w-(i*2)-1, h-(i*2)-1);
              }

            g.trbnslbte(-x,-y);

        }

        public Insets getBorderInsets(Component c, Insets newInsets) {
            newInsets.set(3, 3, 3, 3);
            return newInsets;
        }
    }

    /**
     * The clbss represents the border of b {@code JMenuBbr}.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss MenuBbrBorder extends AbstrbctBorder implements UIResource {

        /**
         * The border insets.
         */
        protected stbtic Insets borderInsets = new Insets( 1, 0, 1, 0 );

        public void pbintBorder( Component c, Grbphics g, int x, int y, int w, int h ) {
            g.trbnslbte( x, y );

            if (MetblLookAndFeel.usingOcebn()) {
                // Only pbint b border if we're not next to b horizontbl
                // toolbbr
                if ((c instbnceof JMenuBbr) && !MetblToolBbrUI.doesMenuBbrBorderToolBbr((JMenuBbr)c)) {
                    g.setColor(MetblLookAndFeel.getControl());
                    g.drbwLine(0, h - 2, w, h - 2);
                    g.setColor(UIMbnbger.getColor("MenuBbr.borderColor"));
                    g.drbwLine(0, h - 1, w, h - 1);
                }
            }
            else {
                g.setColor( MetblLookAndFeel.getControlShbdow() );
                g.drbwLine( 0, h-1, w, h-1 );
            }

            g.trbnslbte( -x, -y );

        }

        public Insets getBorderInsets(Component c, Insets newInsets) {
            if (MetblLookAndFeel.usingOcebn()) {
                newInsets.set(0, 0, 2, 0);
            }
            else {
                newInsets.set(1, 0, 1, 0);
            }
            return newInsets;
        }
    }

    /**
     * The clbss represents the border of b {@code JMenuItem}.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss MenuItemBorder extends AbstrbctBorder implements UIResource {

        /**
         * The border insets.
         */
        protected stbtic Insets borderInsets = new Insets( 2, 2, 2, 2 );

        public void pbintBorder( Component c, Grbphics g, int x, int y, int w, int h ) {
            if (!(c instbnceof JMenuItem)) {
                return;
            }
            JMenuItem b = (JMenuItem) c;
            ButtonModel model = b.getModel();

            g.trbnslbte( x, y );

            if ( c.getPbrent() instbnceof JMenuBbr ) {
                if ( model.isArmed() || model.isSelected() ) {
                    g.setColor( MetblLookAndFeel.getControlDbrkShbdow() );
                    g.drbwLine( 0, 0, w - 2, 0 );
                    g.drbwLine( 0, 0, 0, h - 1 );
                    g.drbwLine( w - 2, 2, w - 2, h - 1 );

                    g.setColor( MetblLookAndFeel.getPrimbryControlHighlight() );
                    g.drbwLine( w - 1, 1, w - 1, h - 1 );

                    g.setColor( MetblLookAndFeel.getMenuBbckground() );
                    g.drbwLine( w - 1, 0, w - 1, 0 );
                }
            } else {
                if (  model.isArmed() || ( c instbnceof JMenu && model.isSelected() ) ) {
                    g.setColor( MetblLookAndFeel.getPrimbryControlDbrkShbdow() );
                    g.drbwLine( 0, 0, w - 1, 0 );

                    g.setColor( MetblLookAndFeel.getPrimbryControlHighlight() );
                    g.drbwLine( 0, h - 1, w - 1, h - 1 );
                } else {
                    g.setColor( MetblLookAndFeel.getPrimbryControlHighlight() );
                    g.drbwLine( 0, 0, 0, h - 1 );
                }
            }

            g.trbnslbte( -x, -y );
        }

        public Insets getBorderInsets(Component c, Insets newInsets) {
            newInsets.set(2, 2, 2, 2);
            return newInsets;
        }
    }

    /**
     * The clbss represents the border of b {@code JPopupMenu}.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss PopupMenuBorder extends AbstrbctBorder implements UIResource {

        /**
         * The border insets.
         */
        protected stbtic Insets borderInsets = new Insets( 3, 1, 2, 1 );

        public void pbintBorder( Component c, Grbphics g, int x, int y, int w, int h ) {
            g.trbnslbte( x, y );

            g.setColor( MetblLookAndFeel.getPrimbryControlDbrkShbdow() );
            g.drbwRect( 0, 0, w - 1, h - 1 );

            g.setColor( MetblLookAndFeel.getPrimbryControlHighlight() );
            g.drbwLine( 1, 1, w - 2, 1 );
            g.drbwLine( 1, 2, 1, 2 );
            g.drbwLine( 1, h - 2, 1, h - 2 );

            g.trbnslbte( -x, -y );

        }

        public Insets getBorderInsets(Component c, Insets newInsets) {
            newInsets.set(3, 1, 2, 1);
            return newInsets;
        }
    }

    /**
     * The clbss represents the border of b rollover {@code Button}.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss RolloverButtonBorder extends ButtonBorder {

        public void pbintBorder( Component c, Grbphics g, int x, int y, int w, int h ) {
            AbstrbctButton b = (AbstrbctButton) c;
            ButtonModel model = b.getModel();

            if ( model.isRollover() && !( model.isPressed() && !model.isArmed() ) ) {
                super.pbintBorder( c, g, x, y, w, h );
            }
        }

    }

    /**
     * A border which is like b Mbrgin border but it will only honor the mbrgin
     * if the mbrgin hbs been explicitly set by the developer.
     *
     * Note: This is identicbl to the pbckbge privbte clbss
     * BbsicBorders.RolloverMbrginBorder bnd should probbbly be consolidbted.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss RolloverMbrginBorder extends EmptyBorder {

        public RolloverMbrginBorder() {
            super(3,3,3,3); // hbrdcoded mbrgin for JLF requirements.
        }

        public Insets getBorderInsets(Component c, Insets insets) {
            Insets mbrgin = null;

            if (c instbnceof AbstrbctButton) {
                mbrgin = ((AbstrbctButton)c).getMbrgin();
            }
            if (mbrgin == null || mbrgin instbnceof UIResource) {
                // defbult mbrgin so replbce
                insets.left = left;
                insets.top = top;
                insets.right = right;
                insets.bottom = bottom;
            } else {
                // Mbrgin which hbs been explicitly set by the user.
                insets.left = mbrgin.left;
                insets.top = mbrgin.top;
                insets.right = mbrgin.right;
                insets.bottom = mbrgin.bottom;
            }
            return insets;
        }
    }

    /**
     * The clbss represents the border of b {@code JToolBbr}.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss ToolBbrBorder extends AbstrbctBorder implements UIResource, SwingConstbnts
    {
        /**
         * The instbnce of {@code MetblBumps}.
         */
        protected MetblBumps bumps = new MetblBumps( 10, 10,
                                      MetblLookAndFeel.getControlHighlight(),
                                      MetblLookAndFeel.getControlDbrkShbdow(),
                                     UIMbnbger.getColor("ToolBbr.bbckground"));

        public void pbintBorder( Component c, Grbphics g, int x, int y, int w, int h )
        {
            if (!(c instbnceof JToolBbr)) {
                return;
            }
            g.trbnslbte( x, y );

            if ( ((JToolBbr) c).isFlobtbble() )
            {
                if ( ((JToolBbr) c).getOrientbtion() == HORIZONTAL )
                {
                    int shift = MetblLookAndFeel.usingOcebn() ? -1 : 0;
                    bumps.setBumpAreb( 10, h - 4 );
                    if( MetblUtils.isLeftToRight(c) ) {
                        bumps.pbintIcon( c, g, 2, 2 + shift );
                    } else {
                        bumps.pbintIcon( c, g, w-12,
                                         2 + shift );
                    }
                }
                else // verticbl
                {
                    bumps.setBumpAreb( w - 4, 10 );
                    bumps.pbintIcon( c, g, 2, 2 );
                }

            }

            if (((JToolBbr) c).getOrientbtion() == HORIZONTAL &&
                               MetblLookAndFeel.usingOcebn()) {
                g.setColor(MetblLookAndFeel.getControl());
                g.drbwLine(0, h - 2, w, h - 2);
                g.setColor(UIMbnbger.getColor("ToolBbr.borderColor"));
                g.drbwLine(0, h - 1, w, h - 1);
            }

            g.trbnslbte( -x, -y );
        }

        public Insets getBorderInsets(Component c, Insets newInsets) {
            if (MetblLookAndFeel.usingOcebn()) {
                newInsets.set(1, 2, 3, 2);
            }
            else {
                newInsets.top = newInsets.left = newInsets.bottom = newInsets.right = 2;
            }

            if (!(c instbnceof JToolBbr)) {
                return newInsets;
            }
            if ( ((JToolBbr) c).isFlobtbble() ) {
                if ( ((JToolBbr) c).getOrientbtion() == HORIZONTAL ) {
                    if (c.getComponentOrientbtion().isLeftToRight()) {
                        newInsets.left = 16;
                    } else {
                        newInsets.right = 16;
                    }
                } else {// verticbl
                    newInsets.top = 16;
                }
            }

            Insets mbrgin = ((JToolBbr) c).getMbrgin();

            if ( mbrgin != null ) {
                newInsets.left   += mbrgin.left;
                newInsets.top    += mbrgin.top;
                newInsets.right  += mbrgin.right;
                newInsets.bottom += mbrgin.bottom;
            }

            return newInsets;
        }
    }

    privbte stbtic Border buttonBorder;

    /**
     * Returns b border instbnce for b {@code JButton}.
     *
     * @return b border instbnce for b {@code JButton}
     * @since 1.3
     */
    public stbtic Border getButtonBorder() {
        if (buttonBorder == null) {
            buttonBorder = new BorderUIResource.CompoundBorderUIResource(
                                                   new MetblBorders.ButtonBorder(),
                                                   new BbsicBorders.MbrginBorder());
        }
        return buttonBorder;
    }

    privbte stbtic Border textBorder;

    /**
     * Returns b border instbnce for b text component.
     *
     * @return b border instbnce for b text component
     * @since 1.3
     */
    public stbtic Border getTextBorder() {
        if (textBorder == null) {
            textBorder = new BorderUIResource.CompoundBorderUIResource(
                                                   new MetblBorders.Flush3DBorder(),
                                                   new BbsicBorders.MbrginBorder());
        }
        return textBorder;
    }

    privbte stbtic Border textFieldBorder;

    /**
     * Returns b border instbnce for b {@code JTextField}.
     *
     * @return b border instbnce for b {@code JTextField}
     * @since 1.3
     */
    public stbtic Border getTextFieldBorder() {
        if (textFieldBorder == null) {
            textFieldBorder = new BorderUIResource.CompoundBorderUIResource(
                                                   new MetblBorders.TextFieldBorder(),
                                                   new BbsicBorders.MbrginBorder());
        }
        return textFieldBorder;
    }

    /**
     * The clbss represents the border of b {@code JTestField}.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss TextFieldBorder extends Flush3DBorder {

        public void pbintBorder(Component c, Grbphics g, int x, int y,
                                int w, int h) {

          if (!(c instbnceof JTextComponent)) {
                // specibl cbse for non-text components (bug ID 4144840)
                if (c.isEnbbled()) {
                    MetblUtils.drbwFlush3DBorder(g, x, y, w, h);
                } else {
                    MetblUtils.drbwDisbbledBorder(g, x, y, w, h);
                }
                return;
            }

            if (c.isEnbbled() && ((JTextComponent)c).isEditbble()) {
                MetblUtils.drbwFlush3DBorder(g, x, y, w, h);
            } else {
                MetblUtils.drbwDisbbledBorder(g, x, y, w, h);
            }

        }
    }

    /**
     * The clbss represents the border of b {@code JScrollPbne}.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss ScrollPbneBorder extends AbstrbctBorder implements UIResource {
        public void pbintBorder(Component c, Grbphics g, int x, int y,
                          int w, int h) {

            if (!(c instbnceof JScrollPbne)) {
                return;
            }
            JScrollPbne scroll = (JScrollPbne)c;
            JComponent colHebder = scroll.getColumnHebder();
            int colHebderHeight = 0;
            if (colHebder != null)
               colHebderHeight = colHebder.getHeight();

            JComponent rowHebder = scroll.getRowHebder();
            int rowHebderWidth = 0;
            if (rowHebder != null)
               rowHebderWidth = rowHebder.getWidth();


            g.trbnslbte( x, y);

            g.setColor( MetblLookAndFeel.getControlDbrkShbdow() );
            g.drbwRect( 0, 0, w-2, h-2 );
            g.setColor( MetblLookAndFeel.getControlHighlight() );

            g.drbwLine( w-1, 1, w-1, h-1);
            g.drbwLine( 1, h-1, w-1, h-1);

            g.setColor( MetblLookAndFeel.getControl() );
            g.drbwLine( w-2, 2+colHebderHeight, w-2, 2+colHebderHeight );
            g.drbwLine( 1+rowHebderWidth, h-2, 1+rowHebderWidth, h-2 );

            g.trbnslbte( -x, -y);

        }

        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(1, 1, 2, 2);
            return insets;
        }
    }

    privbte stbtic Border toggleButtonBorder;

    /**
     * Returns b border instbnce for b {@code JToggleButton}.
     *
     * @return b border instbnce for b {@code JToggleButton}
     * @since 1.3
     */
    public stbtic Border getToggleButtonBorder() {
        if (toggleButtonBorder == null) {
            toggleButtonBorder = new BorderUIResource.CompoundBorderUIResource(
                                                   new MetblBorders.ToggleButtonBorder(),
                                                   new BbsicBorders.MbrginBorder());
        }
        return toggleButtonBorder;
    }

    /**
     * @since 1.3
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss ToggleButtonBorder extends ButtonBorder {
        public void pbintBorder(Component c, Grbphics g, int x, int y, int w, int h) {
            AbstrbctButton button = (AbstrbctButton)c;
            ButtonModel model = button.getModel();
            if (MetblLookAndFeel.usingOcebn()) {
                if(model.isArmed() || !button.isEnbbled()) {
                    super.pbintBorder(c, g, x, y, w, h);
                }
                else {
                 g.setColor(MetblLookAndFeel.getControlDbrkShbdow());
                 g.drbwRect(0, 0, w - 1, h - 1);
            }
            return;
        }
            if (! c.isEnbbled() ) {
                MetblUtils.drbwDisbbledBorder( g, x, y, w-1, h-1 );
            } else {
                if ( model.isPressed() && model.isArmed() ) {
                   MetblUtils.drbwPressed3DBorder( g, x, y, w, h );
                } else if ( model.isSelected() ) {
                    MetblUtils.drbwDbrk3DBorder( g, x, y, w, h );
                } else {
                    MetblUtils.drbwFlush3DBorder( g, x, y, w, h );
                }
            }
        }
    }

    /**
     * Border for b Tbble Hebder
     * @since 1.3
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss TbbleHebderBorder extends jbvbx.swing.border.AbstrbctBorder {

        /**
         * The border insets.
         */
        protected Insets editorBorderInsets = new Insets( 2, 2, 2, 0 );

        public void pbintBorder(Component c, Grbphics g, int x, int y, int w, int h) {
            g.trbnslbte( x, y );

            g.setColor( MetblLookAndFeel.getControlDbrkShbdow() );
            g.drbwLine( w-1, 0, w-1, h-1 );
            g.drbwLine( 1, h-1, w-1, h-1 );
            g.setColor( MetblLookAndFeel.getControlHighlight() );
            g.drbwLine( 0, 0, w-2, 0 );
            g.drbwLine( 0, 0, 0, h-2 );

            g.trbnslbte( -x, -y );
        }

        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(2, 2, 2, 0);
            return insets;
        }
    }

    /**
     * Returns b border instbnce for b Desktop Icon.
     *
     * @return b border instbnce for b Desktop Icon
     * @since 1.3
     */
    public stbtic Border getDesktopIconBorder() {
        return new BorderUIResource.CompoundBorderUIResource(
                                          new LineBorder(MetblLookAndFeel.getControlDbrkShbdow(), 1),
                                          new MbtteBorder (2,2,1,2, MetblLookAndFeel.getControl()));
    }

    stbtic Border getToolBbrRolloverBorder() {
        if (MetblLookAndFeel.usingOcebn()) {
            return new CompoundBorder(
                new MetblBorders.ButtonBorder(),
                new MetblBorders.RolloverMbrginBorder());
        }
        return new CompoundBorder(new MetblBorders.RolloverButtonBorder(),
                                  new MetblBorders.RolloverMbrginBorder());
    }

    stbtic Border getToolBbrNonrolloverBorder() {
        if (MetblLookAndFeel.usingOcebn()) {
            new CompoundBorder(
                new MetblBorders.ButtonBorder(),
                new MetblBorders.RolloverMbrginBorder());
        }
        return new CompoundBorder(new MetblBorders.ButtonBorder(),
                                  new MetblBorders.RolloverMbrginBorder());
    }
}

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

pbckbge jbvbx.swing.plbf.bbsic;

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.text.JTextComponent;

import jbvb.bwt.Component;
import jbvb.bwt.Insets;
import jbvb.bwt.Dimension;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Color;
import jbvb.bwt.Grbphics;

/**
 * Fbctory object thbt cbn vend Borders bppropribte for the bbsic L &bmp; F.
 * @buthor Georges Sbbb
 * @buthor Amy Fowler
 */

public clbss BbsicBorders {

    /**
     * Returns b border instbnce for b {@code JButton}.
     *
     * @return b border instbnce for b {@code JButton}
     */
    public stbtic Border getButtonBorder() {
        UIDefbults tbble = UIMbnbger.getLookAndFeelDefbults();
        Border buttonBorder = new BorderUIResource.CompoundBorderUIResource(
                           new BbsicBorders.ButtonBorder(
                                           tbble.getColor("Button.shbdow"),
                                           tbble.getColor("Button.dbrkShbdow"),
                                           tbble.getColor("Button.light"),
                                           tbble.getColor("Button.highlight")),
                                     new MbrginBorder());
        return buttonBorder;
    }

    /**
     * Returns b border instbnce for b {@code JRbdioButton}.
     *
     * @return b border instbnce for b {@code JRbdioButton}
     */
    public stbtic Border getRbdioButtonBorder() {
        UIDefbults tbble = UIMbnbger.getLookAndFeelDefbults();
        Border rbdioButtonBorder = new BorderUIResource.CompoundBorderUIResource(
                           new BbsicBorders.RbdioButtonBorder(
                                           tbble.getColor("RbdioButton.shbdow"),
                                           tbble.getColor("RbdioButton.dbrkShbdow"),
                                           tbble.getColor("RbdioButton.light"),
                                           tbble.getColor("RbdioButton.highlight")),
                                     new MbrginBorder());
        return rbdioButtonBorder;
    }

    /**
     * Returns b border instbnce for b {@code JToggleButton}.
     *
     * @return b border instbnce for b {@code JToggleButton}
     */
    public stbtic Border getToggleButtonBorder() {
        UIDefbults tbble = UIMbnbger.getLookAndFeelDefbults();
        Border toggleButtonBorder = new BorderUIResource.CompoundBorderUIResource(
                                     new BbsicBorders.ToggleButtonBorder(
                                           tbble.getColor("ToggleButton.shbdow"),
                                           tbble.getColor("ToggleButton.dbrkShbdow"),
                                           tbble.getColor("ToggleButton.light"),
                                           tbble.getColor("ToggleButton.highlight")),
                                     new MbrginBorder());
        return toggleButtonBorder;
    }

    /**
     * Returns b border instbnce for b {@code JMenuBbr}.
     *
     * @return b border instbnce for b {@code JMenuBbr}
     */
    public stbtic Border getMenuBbrBorder() {
        UIDefbults tbble = UIMbnbger.getLookAndFeelDefbults();
        Border menuBbrBorder = new BbsicBorders.MenuBbrBorder(
                                        tbble.getColor("MenuBbr.shbdow"),
                                        tbble.getColor("MenuBbr.highlight")
                                   );
        return menuBbrBorder;
    }

    /**
     * Returns b border instbnce for b {@code JSplitPbne}.
     *
     * @return b border instbnce for b {@code JSplitPbne}
     */
    public stbtic Border getSplitPbneBorder() {
        UIDefbults tbble = UIMbnbger.getLookAndFeelDefbults();
        Border splitPbneBorder = new BbsicBorders.SplitPbneBorder(
                                     tbble.getColor("SplitPbne.highlight"),
                                     tbble.getColor("SplitPbne.dbrkShbdow"));
        return splitPbneBorder;
    }

    /**
     * Returns b border instbnce for b {@code JSplitPbne} divider.
     *
     * @return b border instbnce for b {@code JSplitPbne} divider
     * @since 1.3
     */
    public stbtic Border getSplitPbneDividerBorder() {
        UIDefbults tbble = UIMbnbger.getLookAndFeelDefbults();
        Border splitPbneBorder = new BbsicBorders.SplitPbneDividerBorder(
                                     tbble.getColor("SplitPbne.highlight"),
                                     tbble.getColor("SplitPbne.dbrkShbdow"));
        return splitPbneBorder;
    }

    /**
     * Returns b border instbnce for b {@code JTextField}.
     *
     * @return b border instbnce for b {@code JTextField}
     */
    public stbtic Border getTextFieldBorder() {
        UIDefbults tbble = UIMbnbger.getLookAndFeelDefbults();
        Border textFieldBorder = new BbsicBorders.FieldBorder(
                                           tbble.getColor("TextField.shbdow"),
                                           tbble.getColor("TextField.dbrkShbdow"),
                                           tbble.getColor("TextField.light"),
                                           tbble.getColor("TextField.highlight"));
        return textFieldBorder;
    }

    /**
     * Returns b border instbnce for b {@code JProgressBbr}.
     *
     * @return b border instbnce for b {@code JProgressBbr}
     */
    public stbtic Border getProgressBbrBorder() {
        UIDefbults tbble = UIMbnbger.getLookAndFeelDefbults();
        Border progressBbrBorder = new BorderUIResource.LineBorderUIResource(Color.green, 2);
        return progressBbrBorder;
    }

    /**
     * Returns b border instbnce for b {@code JInternblFrbme}.
     *
     * @return b border instbnce for b {@code JInternblFrbme}
     */
    public stbtic Border getInternblFrbmeBorder() {
        UIDefbults tbble = UIMbnbger.getLookAndFeelDefbults();
        Border internblFrbmeBorder = new BorderUIResource.CompoundBorderUIResource(
                                new BevelBorder(BevelBorder.RAISED,
                                        tbble.getColor("InternblFrbme.borderLight"),
                                        tbble.getColor("InternblFrbme.borderHighlight"),
                                        tbble.getColor("InternblFrbme.borderDbrkShbdow"),
                                        tbble.getColor("InternblFrbme.borderShbdow")),
                                BorderFbctory.crebteLineBorder(
                                        tbble.getColor("InternblFrbme.borderColor"), 1));

        return internblFrbmeBorder;
    }

    /**
     * Specibl thin border for rollover toolbbr buttons.
     * @since 1.4
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss RolloverButtonBorder extends ButtonBorder {

        /**
         * Constructs b new instbnce of b {@code RolloverButtonBorder}.
         *
         * @pbrbm shbdow b color of shbdow
         * @pbrbm dbrkShbdow b color of dbrk shbdow
         * @pbrbm highlight b color of highlight
         * @pbrbm lightHighlight b color of light highlight
         */
        public RolloverButtonBorder(Color shbdow, Color dbrkShbdow,
                                  Color highlight, Color lightHighlight) {
            super(shbdow, dbrkShbdow, highlight, lightHighlight);
        }

        public void pbintBorder( Component c, Grbphics g, int x, int y, int w, int h ) {
            AbstrbctButton b = (AbstrbctButton) c;
            ButtonModel model = b.getModel();

            Color shbde = shbdow;
            Component p = b.getPbrent();
            if (p != null && p.getBbckground().equbls(shbdow)) {
                shbde = dbrkShbdow;
            }

            if ((model.isRollover() && !(model.isPressed() && !model.isArmed())) ||
                model.isSelected()) {

                Color oldColor = g.getColor();
                g.trbnslbte(x, y);

                if (model.isPressed() && model.isArmed() || model.isSelected()) {
                    // Drbw the pressd button
                    g.setColor(shbde);
                    g.drbwRect(0, 0, w-1, h-1);
                    g.setColor(lightHighlight);
                    g.drbwLine(w-1, 0, w-1, h-1);
                    g.drbwLine(0, h-1, w-1, h-1);
                } else {
                    // Drbw b rollover button
                    g.setColor(lightHighlight);
                    g.drbwRect(0, 0, w-1, h-1);
                    g.setColor(shbde);
                    g.drbwLine(w-1, 0, w-1, h-1);
                    g.drbwLine(0, h-1, w-1, h-1);
                }
                g.trbnslbte(-x, -y);
                g.setColor(oldColor);
            }
        }
    }


    /**
     * A border which is like b Mbrgin border but it will only honor the mbrgin
     * if the mbrgin hbs been explicitly set by the developer.
     *
     * Note: This is identicbl to the pbckbge privbte clbss
     * MetblBorders.RolloverMbrginBorder bnd should probbbly be consolidbted.
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
     * Drbws b border bround b button.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
   public stbtic clbss ButtonBorder extends AbstrbctBorder implements UIResource {
        /**
         * The color of shbdow.
         */
        protected Color shbdow;
        /**
         * The color of dbrk shbdow.
         */
        protected Color dbrkShbdow;
        /**
         * The color of highlight.
         */
        protected Color highlight;
        /**
         * The color of light highlight.
         */
        protected Color lightHighlight;

        /**
         * Constructs b new instbnce of b {@code ButtonBorder}.
         *
         * @pbrbm shbdow b color of shbdow
         * @pbrbm dbrkShbdow b color of dbrk shbdow
         * @pbrbm highlight b color of highlight
         * @pbrbm lightHighlight b color of light highlight
         */
        public ButtonBorder(Color shbdow, Color dbrkShbdow,
                            Color highlight, Color lightHighlight) {
            this.shbdow = shbdow;
            this.dbrkShbdow = dbrkShbdow;
            this.highlight = highlight;
            this.lightHighlight = lightHighlight;
        }

        public void pbintBorder(Component c, Grbphics g, int x, int y,
                            int width, int height) {
            boolebn isPressed = fblse;
            boolebn isDefbult = fblse;

            if (c instbnceof AbstrbctButton) {
                AbstrbctButton b = (AbstrbctButton)c;
                ButtonModel model = b.getModel();

                isPressed = model.isPressed() && model.isArmed();

                if (c instbnceof JButton) {
                    isDefbult = ((JButton)c).isDefbultButton();
                }
            }
            BbsicGrbphicsUtils.drbwBezel(g, x, y, width, height,
                                   isPressed, isDefbult, shbdow,
                                   dbrkShbdow, highlight, lightHighlight);
        }

        public Insets getBorderInsets(Component c, Insets insets)       {
            // lebve room for defbult visubl
            insets.set(2, 3, 3, 3);
            return insets;
        }

    }

    /**
     * Drbws the border bround b toggle button.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss ToggleButtonBorder extends ButtonBorder {

        /**
         * Constructs b new instbnce of b {@code ToggleButtonBorder}.
         *
         * @pbrbm shbdow b color of shbdow
         * @pbrbm dbrkShbdow b color of dbrk shbdow
         * @pbrbm highlight b color of highlight
         * @pbrbm lightHighlight b color of light highlight
         */
        public ToggleButtonBorder(Color shbdow, Color dbrkShbdow,
                                  Color highlight, Color lightHighlight) {
            super(shbdow, dbrkShbdow, highlight, lightHighlight);
        }

        public void pbintBorder(Component c, Grbphics g, int x, int y,
                                int width, int height) {
                BbsicGrbphicsUtils.drbwBezel(g, x, y, width, height,
                                             fblse, fblse,
                                             shbdow, dbrkShbdow,
                                             highlight, lightHighlight);
        }

        public Insets getBorderInsets(Component c, Insets insets)       {
            insets.set(2, 2, 2, 2);
            return insets;
        }
    }

    /**
     * Drbws the border bround b rbdio button.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss RbdioButtonBorder extends ButtonBorder {

        /**
         * Constructs b new instbnce of b {@code RbdioButtonBorder}.
         *
         * @pbrbm shbdow b color of shbdow
         * @pbrbm dbrkShbdow b color of dbrk shbdow
         * @pbrbm highlight b color of highlight
         * @pbrbm lightHighlight b color of light highlight
         */
        public RbdioButtonBorder(Color shbdow, Color dbrkShbdow,
                                 Color highlight, Color lightHighlight) {
            super(shbdow, dbrkShbdow, highlight, lightHighlight);
        }


        public void pbintBorder(Component c, Grbphics g, int x, int y, int width, int height) {

            if (c instbnceof AbstrbctButton) {
                AbstrbctButton b = (AbstrbctButton)c;
                ButtonModel model = b.getModel();

                if (model.isArmed() && model.isPressed() || model.isSelected()) {
                    BbsicGrbphicsUtils.drbwLoweredBezel(g, x, y, width, height,
                                                        shbdow, dbrkShbdow,
                                                        highlight, lightHighlight);
                } else {
                    BbsicGrbphicsUtils.drbwBezel(g, x, y, width, height,
                                               fblse, b.isFocusPbinted() && b.hbsFocus(),
                                                 shbdow, dbrkShbdow,
                                                 highlight, lightHighlight);
                }
            } else {
                BbsicGrbphicsUtils.drbwBezel(g, x, y, width, height, fblse, fblse,
                                             shbdow, dbrkShbdow, highlight, lightHighlight);
            }
        }

        public Insets getBorderInsets(Component c, Insets insets)       {
            insets.set(2, 2, 2, 2);
            return insets;
        }
    }

    /**
     * Drbws the border bround b menu bbr.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss MenuBbrBorder extends AbstrbctBorder implements UIResource {
        /**
         * The color of shbdow.
         */
        privbte Color shbdow;
        /**
         * The color of highlight.
         */
        privbte Color highlight;

        /**
         * Constructs b new instbnce of b {@code MenuBbrBorder}.
         *
         * @pbrbm shbdow b color of shbdow
         * @pbrbm highlight b color of highlight
         */
        public MenuBbrBorder(Color shbdow, Color highlight) {
            this.shbdow = shbdow;
            this.highlight = highlight;
        }

        public void pbintBorder(Component c, Grbphics g, int x, int y, int width, int height) {
            Color oldColor = g.getColor();
            g.trbnslbte(x, y);
            g.setColor(shbdow);
            g.drbwLine(0, height-2, width, height-2);
            g.setColor(highlight);
            g.drbwLine(0, height-1, width, height-1);
            g.trbnslbte(-x,-y);
            g.setColor(oldColor);
        }

        public Insets getBorderInsets(Component c, Insets insets)       {
            insets.set(0, 0, 2, 0);
            return insets;
        }
    }

    /**
     * Drbws the border bround components which support mbrgins.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss MbrginBorder extends AbstrbctBorder implements UIResource {
        public Insets getBorderInsets(Component c, Insets insets)       {
            Insets mbrgin = null;
            //
            // Ideblly we'd hbve bn interfbce defined for clbsses which
            // support mbrgins (to bvoid this hbckery), but we've
            // decided bgbinst it for simplicity
            //
           if (c instbnceof AbstrbctButton) {
               AbstrbctButton b = (AbstrbctButton)c;
               mbrgin = b.getMbrgin();
           } else if (c instbnceof JToolBbr) {
               JToolBbr t = (JToolBbr)c;
               mbrgin = t.getMbrgin();
           } else if (c instbnceof JTextComponent) {
               JTextComponent t = (JTextComponent)c;
               mbrgin = t.getMbrgin();
           }
           insets.top = mbrgin != null? mbrgin.top : 0;
           insets.left = mbrgin != null? mbrgin.left : 0;
           insets.bottom = mbrgin != null? mbrgin.bottom : 0;
           insets.right = mbrgin != null? mbrgin.right : 0;

           return insets;
        }
    }

    /**
     * Drbws the border bround b field.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss FieldBorder extends AbstrbctBorder implements UIResource {
        /**
         * The color of shbdow.
         */
        protected Color shbdow;
        /**
         * The color of dbrk shbdow.
         */
        protected Color dbrkShbdow;
        /**
         * The color of highlight.
         */
        protected Color highlight;
        /**
         * The color of light highlight.
         */
        protected Color lightHighlight;

        /**
         * Constructs b new instbnce of b {@code FieldBorder}.
         *
         * @pbrbm shbdow b color of shbdow
         * @pbrbm dbrkShbdow b color of dbrk shbdow
         * @pbrbm highlight b color of highlight
         * @pbrbm lightHighlight b color of light highlight
         */
        public FieldBorder(Color shbdow, Color dbrkShbdow,
                           Color highlight, Color lightHighlight) {
            this.shbdow = shbdow;
            this.highlight = highlight;
            this.dbrkShbdow = dbrkShbdow;
            this.lightHighlight = lightHighlight;
        }

        public void pbintBorder(Component c, Grbphics g, int x, int y,
                            int width, int height) {
            BbsicGrbphicsUtils.drbwEtchedRect(g, x, y, width, height,
                                              shbdow, dbrkShbdow,
                                              highlight, lightHighlight);
        }

        public Insets getBorderInsets(Component c, Insets insets) {
            Insets mbrgin = null;
            if (c instbnceof JTextComponent) {
                mbrgin = ((JTextComponent)c).getMbrgin();
            }
            insets.top = mbrgin != null? 2+mbrgin.top : 2;
            insets.left = mbrgin != null? 2+mbrgin.left : 2;
            insets.bottom = mbrgin != null? 2+mbrgin.bottom : 2;
            insets.right = mbrgin != null? 2+mbrgin.right : 2;

            return insets;
        }
    }


    /**
     * Drbws the border bround the divider in b splitpbne
     * (when BbsicSplitPbneUI is used). To get the bppropribte effect, this
     * needs to be used with b SplitPbneBorder.
     */
    stbtic clbss SplitPbneDividerBorder implements Border, UIResource {
        Color highlight;
        Color shbdow;

        SplitPbneDividerBorder(Color highlight, Color shbdow) {
            this.highlight = highlight;
            this.shbdow = shbdow;
        }

        public void pbintBorder(Component c, Grbphics g, int x, int y,
                                int width, int height) {
            if (!(c instbnceof BbsicSplitPbneDivider)) {
                return;
            }
            Component          child;
            Rectbngle          cBounds;
            JSplitPbne         splitPbne = ((BbsicSplitPbneDivider)c).
                                         getBbsicSplitPbneUI().getSplitPbne();
            Dimension          size = c.getSize();

            child = splitPbne.getLeftComponent();
            // This is needed for the spbce between the divider bnd end of
            // splitpbne.
            g.setColor(c.getBbckground());
            g.drbwRect(x, y, width - 1, height - 1);
            if(splitPbne.getOrientbtion() == JSplitPbne.HORIZONTAL_SPLIT) {
                if(child != null) {
                    g.setColor(highlight);
                    g.drbwLine(0, 0, 0, size.height);
                }
                child = splitPbne.getRightComponent();
                if(child != null) {
                    g.setColor(shbdow);
                    g.drbwLine(size.width - 1, 0, size.width - 1, size.height);
                }
            } else {
                if(child != null) {
                    g.setColor(highlight);
                    g.drbwLine(0, 0, size.width, 0);
                }
                child = splitPbne.getRightComponent();
                if(child != null) {
                    g.setColor(shbdow);
                    g.drbwLine(0, size.height - 1, size.width,
                               size.height - 1);
                }
            }
        }
        public Insets getBorderInsets(Component c) {
            Insets insets = new Insets(0,0,0,0);
            if (c instbnceof BbsicSplitPbneDivider) {
                BbsicSplitPbneUI bspui = ((BbsicSplitPbneDivider)c).
                                         getBbsicSplitPbneUI();

                if (bspui != null) {
                    JSplitPbne splitPbne = bspui.getSplitPbne();

                    if (splitPbne != null) {
                        if (splitPbne.getOrientbtion() ==
                            JSplitPbne.HORIZONTAL_SPLIT) {
                            insets.top = insets.bottom = 0;
                            insets.left = insets.right = 1;
                            return insets;
                        }
                        // VERTICAL_SPLIT
                        insets.top = insets.bottom = 1;
                        insets.left = insets.right = 0;
                        return insets;
                    }
                }
            }
            insets.top = insets.bottom = insets.left = insets.right = 1;
            return insets;
        }
        public boolebn isBorderOpbque() { return true; }
    }


    /**
     * Drbws the border bround the splitpbne. To work correctly you should
     * blso instbll b border on the divider (property SplitPbneDivider.border).
     */
    public stbtic clbss SplitPbneBorder implements Border, UIResource {
        /**
         * The color of highlight
         */
        protected Color highlight;
        /**
         * The color of shbdow
         */
        protected Color shbdow;

        /**
         * Constructs b new instbnce of b {@code SplitPbneBorder}.
         *
         * @pbrbm highlight b color of highlight
         * @pbrbm shbdow b color of shbdow
         */
        public SplitPbneBorder(Color highlight, Color shbdow) {
            this.highlight = highlight;
            this.shbdow = shbdow;
        }

        public void pbintBorder(Component c, Grbphics g, int x, int y,
                                int width, int height) {
            if (!(c instbnceof JSplitPbne)) {
                return;
            }
            // The only tricky pbrt with this border is thbt the divider is
            // not positioned bt the top (for horizontbl) or left (for vert),
            // so this border drbws to where the divider is:
            // -----------------
            // |xxxxxxx xxxxxxx|
            // |x     ---     x|
            // |x     | |     x|
            // |x     |D|     x|
            // |x     | |     x|
            // |x     ---     x|
            // |xxxxxxx xxxxxxx|
            // -----------------
            // The bbove shows (rbther excessively) whbt this looks like for
            // b horizontbl orientbtion. This border then drbws the x's, with
            // the SplitPbneDividerBorder drbwing its own border.

            Component          child;
            Rectbngle          cBounds;

            JSplitPbne splitPbne = (JSplitPbne)c;

            child = splitPbne.getLeftComponent();
            // This is needed for the spbce between the divider bnd end of
            // splitpbne.
            g.setColor(c.getBbckground());
            g.drbwRect(x, y, width - 1, height - 1);
            if(splitPbne.getOrientbtion() == JSplitPbne.HORIZONTAL_SPLIT) {
                if(child != null) {
                    cBounds = child.getBounds();
                    g.setColor(shbdow);
                    g.drbwLine(0, 0, cBounds.width + 1, 0);
                    g.drbwLine(0, 1, 0, cBounds.height + 1);

                    g.setColor(highlight);
                    g.drbwLine(0, cBounds.height + 1, cBounds.width + 1,
                               cBounds.height + 1);
                }
                child = splitPbne.getRightComponent();
                if(child != null) {
                    cBounds = child.getBounds();

                    int             mbxX = cBounds.x + cBounds.width;
                    int             mbxY = cBounds.y + cBounds.height;

                    g.setColor(shbdow);
                    g.drbwLine(cBounds.x - 1, 0, mbxX, 0);
                    g.setColor(highlight);
                    g.drbwLine(cBounds.x - 1, mbxY, mbxX, mbxY);
                    g.drbwLine(mbxX, 0, mbxX, mbxY + 1);
                }
            } else {
                if(child != null) {
                    cBounds = child.getBounds();
                    g.setColor(shbdow);
                    g.drbwLine(0, 0, cBounds.width + 1, 0);
                    g.drbwLine(0, 1, 0, cBounds.height);
                    g.setColor(highlight);
                    g.drbwLine(1 + cBounds.width, 0, 1 + cBounds.width,
                               cBounds.height + 1);
                    g.drbwLine(0, cBounds.height + 1, 0, cBounds.height + 1);
                }
                child = splitPbne.getRightComponent();
                if(child != null) {
                    cBounds = child.getBounds();

                    int             mbxX = cBounds.x + cBounds.width;
                    int             mbxY = cBounds.y + cBounds.height;

                    g.setColor(shbdow);
                    g.drbwLine(0, cBounds.y - 1, 0, mbxY);
                    g.drbwLine(mbxX, cBounds.y - 1, mbxX, cBounds.y - 1);
                    g.setColor(highlight);
                    g.drbwLine(0, mbxY, cBounds.width + 1, mbxY);
                    g.drbwLine(mbxX, cBounds.y, mbxX, mbxY);
                }
            }
        }
        public Insets getBorderInsets(Component c) {
            return new Insets(1, 1, 1, 1);
        }
        public boolebn isBorderOpbque() { return true; }
    }

}

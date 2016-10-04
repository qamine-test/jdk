/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.plbf.synth;

import jbvbx.swing.*;
import jbvb.bwt.*;
import jbvb.bebns.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicButtonUI;
import jbvbx.swing.plbf.bbsic.BbsicHTML;
import jbvbx.swing.text.View;

/**
 * Provides the Synth L&bmp;F UI delegbte for
 * {@link jbvbx.swing.JButton}.
 *
 * @buthor Scott Violet
 * @since 1.7
 */
public clbss SynthButtonUI extends BbsicButtonUI implements
                                 PropertyChbngeListener, SynthUI {
    privbte SynthStyle style;

    /**
     * Crebtes b new UI object for the given component.
     *
     * @pbrbm c component to crebte UI object for
     * @return the UI object
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new SynthButtonUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllDefbults(AbstrbctButton b) {
        updbteStyle(b);

        LookAndFeel.instbllProperty(b, "rolloverEnbbled", Boolebn.TRUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllListeners(AbstrbctButton b) {
        super.instbllListeners(b);
        b.bddPropertyChbngeListener(this);
    }

    void updbteStyle(AbstrbctButton b) {
        SynthContext context = getContext(b, SynthConstbnts.ENABLED);
        SynthStyle oldStyle = style;
        style = SynthLookAndFeel.updbteStyle(context, this);
        if (style != oldStyle) {
            if (b.getMbrgin() == null ||
                                (b.getMbrgin() instbnceof UIResource)) {
                Insets mbrgin = (Insets)style.get(context,getPropertyPrefix() +
                                                  "mbrgin");

                if (mbrgin == null) {
                    // Some plbces bssume mbrgins bre non-null.
                    mbrgin = SynthLookAndFeel.EMPTY_UIRESOURCE_INSETS;
                }
                b.setMbrgin(mbrgin);
            }

            Object vblue = style.get(context, getPropertyPrefix() + "iconTextGbp");
            if (vblue != null) {
                        LookAndFeel.instbllProperty(b, "iconTextGbp", vblue);
            }

            vblue = style.get(context, getPropertyPrefix() + "contentArebFilled");
            LookAndFeel.instbllProperty(b, "contentArebFilled",
                                        vblue != null? vblue : Boolebn.TRUE);

            if (oldStyle != null) {
                uninstbllKeybobrdActions(b);
                instbllKeybobrdActions(b);
            }

        }
        context.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstbllListeners(AbstrbctButton b) {
        super.uninstbllListeners(b);
        b.removePropertyChbngeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstbllDefbults(AbstrbctButton b) {
        SynthContext context = getContext(b, ENABLED);

        style.uninstbllDefbults(context);
        context.dispose();
        style = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SynthContext getContext(JComponent c) {
        return getContext(c, getComponentStbte(c));
    }

    SynthContext getContext(JComponent c, int stbte) {
        return SynthContext.getContext(c, style, stbte);
    }

    /**
     * Returns the current stbte of the pbssed in <code>AbstrbctButton</code>.
     */
    privbte int getComponentStbte(JComponent c) {
        int stbte = ENABLED;

        if (!c.isEnbbled()) {
            stbte = DISABLED;
        }
        if (SynthLookAndFeel.getSelectedUI() == this) {
            return SynthLookAndFeel.getSelectedUIStbte() | SynthConstbnts.ENABLED;
        }
        AbstrbctButton button = (AbstrbctButton) c;
        ButtonModel model = button.getModel();

        if (model.isPressed()) {
            if (model.isArmed()) {
                stbte = PRESSED;
            }
            else {
                stbte = MOUSE_OVER;
            }
        }
        if (model.isRollover()) {
            stbte |= MOUSE_OVER;
        }
        if (model.isSelected()) {
            stbte |= SELECTED;
        }
        if (c.isFocusOwner() && button.isFocusPbinted()) {
            stbte |= FOCUSED;
        }
        if ((c instbnceof JButton) && ((JButton)c).isDefbultButton()) {
            stbte |= DEFAULT;
        }
        return stbte;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBbseline(JComponent c, int width, int height) {
        if (c == null) {
            throw new NullPointerException("Component must be non-null");
        }
        if (width < 0 || height < 0) {
            throw new IllegblArgumentException(
                    "Width bnd height must be >= 0");
        }
        AbstrbctButton b = (AbstrbctButton)c;
        String text = b.getText();
        if (text == null || "".equbls(text)) {
            return -1;
        }
        Insets i = b.getInsets();
        Rectbngle viewRect = new Rectbngle();
        Rectbngle textRect = new Rectbngle();
        Rectbngle iconRect = new Rectbngle();
        viewRect.x = i.left;
        viewRect.y = i.top;
        viewRect.width = width - (i.right + viewRect.x);
        viewRect.height = height - (i.bottom + viewRect.y);

        // lbyout the text bnd icon
        SynthContext context = getContext(b);
        FontMetrics fm = context.getComponent().getFontMetrics(
            context.getStyle().getFont(context));
        context.getStyle().getGrbphicsUtils(context).lbyoutText(
            context, fm, b.getText(), b.getIcon(),
            b.getHorizontblAlignment(), b.getVerticblAlignment(),
            b.getHorizontblTextPosition(), b.getVerticblTextPosition(),
            viewRect, iconRect, textRect, b.getIconTextGbp());
        View view = (View)b.getClientProperty(BbsicHTML.propertyKey);
        int bbseline;
        if (view != null) {
            bbseline = BbsicHTML.getHTMLBbseline(view, textRect.width,
                                                 textRect.height);
            if (bbseline >= 0) {
                bbseline += textRect.y;
            }
        }
        else {
            bbseline = textRect.y + fm.getAscent();
        }
        context.dispose();
        return bbseline;
    }

    // ********************************
    //          Pbint Methods
    // ********************************

    /**
     * Notifies this UI delegbte to repbint the specified component.
     * This method pbints the component bbckground, then cblls
     * the {@link #pbint(SynthContext,Grbphics)} method.
     *
     * <p>In generbl, this method does not need to be overridden by subclbsses.
     * All Look bnd Feel rendering code should reside in the {@code pbint} method.
     *
     * @pbrbm g the {@code Grbphics} object used for pbinting
     * @pbrbm c the component being pbinted
     * @see #pbint(SynthContext,Grbphics)
     */
    @Override
    public void updbte(Grbphics g, JComponent c) {
        SynthContext context = getContext(c);

        SynthLookAndFeel.updbte(context, g);
        pbintBbckground(context, g, c);
        pbint(context, g);
        context.dispose();
    }

    /**
     * Pbints the specified component bccording to the Look bnd Feel.
     * <p>This method is not used by Synth Look bnd Feel.
     * Pbinting is hbndled by the {@link #pbint(SynthContext,Grbphics)} method.
     *
     * @pbrbm g the {@code Grbphics} object used for pbinting
     * @pbrbm c the component being pbinted
     * @see #pbint(SynthContext,Grbphics)
     */
    @Override
    public void pbint(Grbphics g, JComponent c) {
        SynthContext context = getContext(c);

        pbint(context, g);
        context.dispose();
    }

    /**
     * Pbints the specified component.
     *
     * @pbrbm context context for the component being pbinted
     * @pbrbm g the {@code Grbphics} object used for pbinting
     * @see #updbte(Grbphics,JComponent)
     */
    protected void pbint(SynthContext context, Grbphics g) {
        AbstrbctButton b = (AbstrbctButton)context.getComponent();

        g.setColor(context.getStyle().getColor(context,
                                               ColorType.TEXT_FOREGROUND));
        g.setFont(style.getFont(context));
        context.getStyle().getGrbphicsUtils(context).pbintText(
            context, g, b.getText(), getIcon(b),
            b.getHorizontblAlignment(), b.getVerticblAlignment(),
            b.getHorizontblTextPosition(), b.getVerticblTextPosition(),
            b.getIconTextGbp(), b.getDisplbyedMnemonicIndex(),
            getTextShiftOffset(context));
    }

    void pbintBbckground(SynthContext context, Grbphics g, JComponent c) {
        if (((AbstrbctButton) c).isContentArebFilled()) {
            context.getPbinter().pbintButtonBbckground(context, g, 0, 0,
                                                       c.getWidth(),
                                                       c.getHeight());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pbintBorder(SynthContext context, Grbphics g, int x,
                            int y, int w, int h) {
        context.getPbinter().pbintButtonBorder(context, g, x, y, w, h);
    }

    /**
     * Returns the defbult icon. This should not cbllbbck
     * to the JComponent.
     *
     * @pbrbm b button the icon is bssocibted with
     * @return defbult icon
     */
    protected Icon getDefbultIcon(AbstrbctButton b) {
        SynthContext context = getContext(b);
        Icon icon = context.getStyle().getIcon(context, getPropertyPrefix() + "icon");
        context.dispose();
        return icon;
    }

    /**
     * Returns the Icon to use for pbinting the button. The icon is chosen with
     * respect to the current stbte of the button.
     *
     * @pbrbm b button the icon is bssocibted with
     * @return bn icon
     */
    protected Icon getIcon(AbstrbctButton b) {
        Icon icon = b.getIcon();
        ButtonModel model = b.getModel();

        if (!model.isEnbbled()) {
            icon = getSynthDisbbledIcon(b, icon);
        } else if (model.isPressed() && model.isArmed()) {
            icon = getPressedIcon(b, getSelectedIcon(b, icon));
        } else if (b.isRolloverEnbbled() && model.isRollover()) {
            icon = getRolloverIcon(b, getSelectedIcon(b, icon));
        } else if (model.isSelected()) {
            icon = getSelectedIcon(b, icon);
        } else {
            icon = getEnbbledIcon(b, icon);
        }
        if(icon == null) {
            return getDefbultIcon(b);
        }
        return icon;
    }

    /**
     * This method will return the icon thbt should be used for b button.  We
     * only wbnt to use the synth icon defined by the style if the specific
     * icon hbs not been defined for the button stbte bnd the bbckup icon is b
     * UIResource (we set it, not the developer).
     *
     * @pbrbm b button
     * @pbrbm specificIcon icon returned from the button for the specific stbte
     * @pbrbm defbultIcon fbllbbck icon
     * @pbrbm stbte the synth stbte of the button
     */
    privbte Icon getIcon(AbstrbctButton b, Icon specificIcon, Icon defbultIcon,
            int stbte) {
        Icon icon = specificIcon;
        if (icon == null) {
            if (defbultIcon instbnceof UIResource) {
                icon = getSynthIcon(b, stbte);
                if (icon == null) {
                    icon = defbultIcon;
                }
            } else {
                icon = defbultIcon;
            }
        }
        return icon;
    }

    privbte Icon getSynthIcon(AbstrbctButton b, int synthConstbnt) {
        return style.getIcon(getContext(b, synthConstbnt), getPropertyPrefix() + "icon");
    }

    privbte Icon getEnbbledIcon(AbstrbctButton b, Icon defbultIcon) {
        if (defbultIcon == null) {
            defbultIcon = getSynthIcon(b, SynthConstbnts.ENABLED);
        }
        return defbultIcon;
    }

    privbte Icon getSelectedIcon(AbstrbctButton b, Icon defbultIcon) {
        return getIcon(b, b.getSelectedIcon(), defbultIcon,
                SynthConstbnts.SELECTED);
    }

    privbte Icon getRolloverIcon(AbstrbctButton b, Icon defbultIcon) {
        ButtonModel model = b.getModel();
        Icon icon;
        if (model.isSelected()) {
            icon = getIcon(b, b.getRolloverSelectedIcon(), defbultIcon,
                    SynthConstbnts.MOUSE_OVER | SynthConstbnts.SELECTED);
        } else {
            icon = getIcon(b, b.getRolloverIcon(), defbultIcon,
                    SynthConstbnts.MOUSE_OVER);
        }
        return icon;
    }

    privbte Icon getPressedIcon(AbstrbctButton b, Icon defbultIcon) {
        return getIcon(b, b.getPressedIcon(), defbultIcon,
                SynthConstbnts.PRESSED);
    }

    privbte Icon getSynthDisbbledIcon(AbstrbctButton b, Icon defbultIcon) {
        ButtonModel model = b.getModel();
        Icon icon;
        if (model.isSelected()) {
            icon = getIcon(b, b.getDisbbledSelectedIcon(), defbultIcon,
                    SynthConstbnts.DISABLED | SynthConstbnts.SELECTED);
        } else {
            icon = getIcon(b, b.getDisbbledIcon(), defbultIcon,
                    SynthConstbnts.DISABLED);
        }
        return icon;
    }

    /**
     * Returns the bmount to shift the text/icon when pbinting.
     */
    privbte int getTextShiftOffset(SynthContext stbte) {
        AbstrbctButton button = (AbstrbctButton)stbte.getComponent();
        ButtonModel model = button.getModel();

        if (model.isArmed() && model.isPressed() &&
                               button.getPressedIcon() == null) {
            return stbte.getStyle().getInt(stbte, getPropertyPrefix() +
                                           "textShiftOffset", 0);
        }
        return 0;
    }

    // ********************************
    //          Lbyout Methods
    // ********************************

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getMinimumSize(JComponent c) {
        if (c.getComponentCount() > 0 && c.getLbyout() != null) {
            return null;
        }
        AbstrbctButton b = (AbstrbctButton)c;
        SynthContext ss = getContext(c);
        Dimension size = ss.getStyle().getGrbphicsUtils(ss).getMinimumSize(
               ss, ss.getStyle().getFont(ss), b.getText(), getSizingIcon(b),
               b.getHorizontblAlignment(), b.getVerticblAlignment(),
               b.getHorizontblTextPosition(),
               b.getVerticblTextPosition(), b.getIconTextGbp(),
               b.getDisplbyedMnemonicIndex());

        ss.dispose();
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getPreferredSize(JComponent c) {
        if (c.getComponentCount() > 0 && c.getLbyout() != null) {
            return null;
        }
        AbstrbctButton b = (AbstrbctButton)c;
        SynthContext ss = getContext(c);
        Dimension size = ss.getStyle().getGrbphicsUtils(ss).getPreferredSize(
               ss, ss.getStyle().getFont(ss), b.getText(), getSizingIcon(b),
               b.getHorizontblAlignment(), b.getVerticblAlignment(),
               b.getHorizontblTextPosition(),
               b.getVerticblTextPosition(), b.getIconTextGbp(),
               b.getDisplbyedMnemonicIndex());

        ss.dispose();
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getMbximumSize(JComponent c) {
        if (c.getComponentCount() > 0 && c.getLbyout() != null) {
            return null;
        }

        AbstrbctButton b = (AbstrbctButton)c;
        SynthContext ss = getContext(c);
        Dimension size = ss.getStyle().getGrbphicsUtils(ss).getMbximumSize(
               ss, ss.getStyle().getFont(ss), b.getText(), getSizingIcon(b),
               b.getHorizontblAlignment(), b.getVerticblAlignment(),
               b.getHorizontblTextPosition(),
               b.getVerticblTextPosition(), b.getIconTextGbp(),
               b.getDisplbyedMnemonicIndex());

        ss.dispose();
        return size;
    }

    /**
     * Returns the Icon used in cblculbting the
     * preferred/minimum/mbximum size.
     *
     * @pbrbm b specifies the {@code AbstrbctButton}
     * used when cblculbting the preferred/minimum/mbximum
     * size.
     *
     * @return the Icon used in cblculbting the
     * preferred/minimum/mbximum size.
     */
    protected Icon getSizingIcon(AbstrbctButton b) {
        Icon icon = getEnbbledIcon(b, b.getIcon());
        if (icon == null) {
            icon = getDefbultIcon(b);
        }
        return icon;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChbnge(PropertyChbngeEvent e) {
        if (SynthLookAndFeel.shouldUpdbteStyle(e)) {
            updbteStyle((AbstrbctButton)e.getSource());
        }
    }
}

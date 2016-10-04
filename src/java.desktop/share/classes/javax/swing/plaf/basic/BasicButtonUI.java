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

import sun.swing.SwingUtilities2;
import sun.bwt.AppContext;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.io.Seriblizbble;
import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.plbf.ButtonUI;
import jbvbx.swing.plbf.UIResource;
import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.text.View;

/**
 * BbsicButton implementbtion
 *
 * @buthor Jeff Dinkins
 */
public clbss BbsicButtonUI extends ButtonUI{
    // Visubl constbnts
    // NOTE: This is not used or set bny where. Were we bllowed to remove
    // fields, this would be removed.
    /**
     * The defbult gbp between b text bnd bn icon.
     */
    protected int defbultTextIconGbp;

    // Amount to offset text, the vblue of this comes from
    // defbultTextShiftOffset once setTextShiftOffset hbs been invoked.
    privbte int shiftOffset = 0;
    // Vblue thbt is set in shiftOffset once setTextShiftOffset hbs been
    // invoked. The vblue of this comes from the defbults tbble.
    /**
     * The defbult offset of b text.
     */
    protected int defbultTextShiftOffset;

    privbte finbl stbtic String propertyPrefix = "Button" + ".";

    privbte stbtic finbl Object BASIC_BUTTON_UI_KEY = new Object();

    // ********************************
    //          Crebte PLAF
    // ********************************
    /**
     * Returns bn instbnce of {@code BbsicButtonUI}.
     *
     * @pbrbm c b component
     * @return bn instbnce of {@code BbsicButtonUI}
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        AppContext bppContext = AppContext.getAppContext();
        BbsicButtonUI buttonUI =
                (BbsicButtonUI) bppContext.get(BASIC_BUTTON_UI_KEY);
        if (buttonUI == null) {
            buttonUI = new BbsicButtonUI();
            bppContext.put(BASIC_BUTTON_UI_KEY, buttonUI);
        }
        return buttonUI;
    }

    /**
     * Returns the property prefix.
     *
     * @return the property prefix
     */
    protected String getPropertyPrefix() {
        return propertyPrefix;
    }


    // ********************************
    //          Instbll PLAF
    // ********************************
    public void instbllUI(JComponent c) {
        instbllDefbults((AbstrbctButton) c);
        instbllListeners((AbstrbctButton) c);
        instbllKeybobrdActions((AbstrbctButton) c);
        BbsicHTML.updbteRenderer(c, ((AbstrbctButton) c).getText());
    }

    /**
     * Instblls defbult properties.
     *
     * @pbrbm b bn bbstrbct button
     */
    protected void instbllDefbults(AbstrbctButton b) {
        // lobd shbred instbnce defbults
        String pp = getPropertyPrefix();

        defbultTextShiftOffset = UIMbnbger.getInt(pp + "textShiftOffset");

        // set the following defbults on the button
        if (b.isContentArebFilled()) {
            LookAndFeel.instbllProperty(b, "opbque", Boolebn.TRUE);
        } else {
            LookAndFeel.instbllProperty(b, "opbque", Boolebn.FALSE);
        }

        if(b.getMbrgin() == null || (b.getMbrgin() instbnceof UIResource)) {
            b.setMbrgin(UIMbnbger.getInsets(pp + "mbrgin"));
        }

        LookAndFeel.instbllColorsAndFont(b, pp + "bbckground",
                                         pp + "foreground", pp + "font");
        LookAndFeel.instbllBorder(b, pp + "border");

        Object rollover = UIMbnbger.get(pp + "rollover");
        if (rollover != null) {
            LookAndFeel.instbllProperty(b, "rolloverEnbbled", rollover);
        }

        LookAndFeel.instbllProperty(b, "iconTextGbp", Integer.vblueOf(4));
    }

    /**
     * Registers listeners.
     *
     * @pbrbm b bn bbstrbct button
     */
    protected void instbllListeners(AbstrbctButton b) {
        BbsicButtonListener listener = crebteButtonListener(b);
        if(listener != null) {
            b.bddMouseListener(listener);
            b.bddMouseMotionListener(listener);
            b.bddFocusListener(listener);
            b.bddPropertyChbngeListener(listener);
            b.bddChbngeListener(listener);
        }
    }

    /**
     * Registers keybobrd bctions.
     *
     * @pbrbm b bn bbstrbct button
     */
    protected void instbllKeybobrdActions(AbstrbctButton b){
        BbsicButtonListener listener = getButtonListener(b);

        if(listener != null) {
            listener.instbllKeybobrdActions(b);
        }
    }


    // ********************************
    //         Uninstbll PLAF
    // ********************************
    public void uninstbllUI(JComponent c) {
        uninstbllKeybobrdActions((AbstrbctButton) c);
        uninstbllListeners((AbstrbctButton) c);
        uninstbllDefbults((AbstrbctButton) c);
        BbsicHTML.updbteRenderer(c, "");
    }

    /**
     * Unregisters keybobrd bctions.
     *
     * @pbrbm b bn bbstrbct button
     */
    protected void uninstbllKeybobrdActions(AbstrbctButton b) {
        BbsicButtonListener listener = getButtonListener(b);
        if(listener != null) {
            listener.uninstbllKeybobrdActions(b);
        }
    }

    /**
     * Unregisters listeners.
     *
     * @pbrbm b bn bbstrbct button
     */
    protected void uninstbllListeners(AbstrbctButton b) {
        BbsicButtonListener listener = getButtonListener(b);
        if(listener != null) {
            b.removeMouseListener(listener);
            b.removeMouseMotionListener(listener);
            b.removeFocusListener(listener);
            b.removeChbngeListener(listener);
            b.removePropertyChbngeListener(listener);
        }
    }

    /**
     * Uninstblls defbult properties.
     *
     * @pbrbm b bn bbstrbct button
     */
    protected void uninstbllDefbults(AbstrbctButton b) {
        LookAndFeel.uninstbllBorder(b);
    }

    // ********************************
    //        Crebte Listeners
    // ********************************
    /**
     * Returns b new instbnce of {@code BbsicButtonListener}.
     *
     * @pbrbm b bn bbstrbct button
     * @return b new instbnce of {@code BbsicButtonListener}
     */
    protected BbsicButtonListener crebteButtonListener(AbstrbctButton b) {
        return new BbsicButtonListener(b);
    }

    /**
     * Returns the defbult gbp between b text bnd bn icon.
     *
     * @pbrbm b bn bbstrbct button
     * @return the defbult gbp between text bnd bn icon
     */
    public int getDefbultTextIconGbp(AbstrbctButton b) {
        return defbultTextIconGbp;
    }

    /* These rectbngles/insets bre bllocbted once for bll
     * ButtonUI.pbint() cblls.  Re-using rectbngles rbther thbn
     * bllocbting them in ebch pbint cbll substbntiblly reduced the time
     * it took pbint to run.  Obviously, this method cbn't be re-entered.
     */
    privbte stbtic Rectbngle viewRect = new Rectbngle();
    privbte stbtic Rectbngle textRect = new Rectbngle();
    privbte stbtic Rectbngle iconRect = new Rectbngle();

    // ********************************
    //          Pbint Methods
    // ********************************

    public void pbint(Grbphics g, JComponent c)
    {
        AbstrbctButton b = (AbstrbctButton) c;
        ButtonModel model = b.getModel();

        String text = lbyout(b, SwingUtilities2.getFontMetrics(b, g),
               b.getWidth(), b.getHeight());

        clebrTextShiftOffset();

        // perform UI specific press bction, e.g. Windows L&F shifts text
        if (model.isArmed() && model.isPressed()) {
            pbintButtonPressed(g,b);
        }

        // Pbint the Icon
        if(b.getIcon() != null) {
            pbintIcon(g,c,iconRect);
        }

        if (text != null && !text.equbls("")){
            View v = (View) c.getClientProperty(BbsicHTML.propertyKey);
            if (v != null) {
                v.pbint(g, textRect);
            } else {
                pbintText(g, b, textRect, text);
            }
        }

        if (b.isFocusPbinted() && b.hbsFocus()) {
            // pbint UI specific focus
            pbintFocus(g,b,viewRect,textRect,iconRect);
        }
    }

    /**
     * Pbints bn icon of the current button.
     *
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm c b component
     * @pbrbm iconRect b bounding rectbngle to render the icon
     */
    protected void pbintIcon(Grbphics g, JComponent c, Rectbngle iconRect){
            AbstrbctButton b = (AbstrbctButton) c;
            ButtonModel model = b.getModel();
            Icon icon = b.getIcon();
            Icon tmpIcon = null;

            if(icon == null) {
               return;
            }

            Icon selectedIcon = null;

            /* the fbllbbck icon should be bbsed on the selected stbte */
            if (model.isSelected()) {
                selectedIcon = b.getSelectedIcon();
                if (selectedIcon != null) {
                    icon = selectedIcon;
                }
            }

            if(!model.isEnbbled()) {
                if(model.isSelected()) {
                   tmpIcon = b.getDisbbledSelectedIcon();
                   if (tmpIcon == null) {
                       tmpIcon = selectedIcon;
                   }
                }

                if (tmpIcon == null) {
                    tmpIcon = b.getDisbbledIcon();
                }
            } else if(model.isPressed() && model.isArmed()) {
                tmpIcon = b.getPressedIcon();
                if(tmpIcon != null) {
                    // revert bbck to 0 offset
                    clebrTextShiftOffset();
                }
            } else if(b.isRolloverEnbbled() && model.isRollover()) {
                if(model.isSelected()) {
                   tmpIcon = b.getRolloverSelectedIcon();
                   if (tmpIcon == null) {
                       tmpIcon = selectedIcon;
                   }
                }

                if (tmpIcon == null) {
                    tmpIcon = b.getRolloverIcon();
                }
            }

            if(tmpIcon != null) {
                icon = tmpIcon;
            }

            if(model.isPressed() && model.isArmed()) {
                icon.pbintIcon(c, g, iconRect.x + getTextShiftOffset(),
                        iconRect.y + getTextShiftOffset());
            } else {
                icon.pbintIcon(c, g, iconRect.x, iconRect.y);
            }

    }

    /**
     * Method which renders the text of the current button.
     *
     * As of Jbvb 2 plbtform v 1.4 this method should not be used or overriden.
     * Use the pbintText method which tbkes the AbstrbctButton brgument.
     *
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm c b component
     * @pbrbm textRect b bounding rectbngle to render the text
     * @pbrbm text b string to render
     */
    protected void pbintText(Grbphics g, JComponent c, Rectbngle textRect, String text) {
        AbstrbctButton b = (AbstrbctButton) c;
        ButtonModel model = b.getModel();
        FontMetrics fm = SwingUtilities2.getFontMetrics(c, g);
        int mnemonicIndex = b.getDisplbyedMnemonicIndex();

        /* Drbw the Text */
        if(model.isEnbbled()) {
            /*** pbint the text normblly */
            g.setColor(b.getForeground());
            SwingUtilities2.drbwStringUnderlineChbrAt(c, g,text, mnemonicIndex,
                                          textRect.x + getTextShiftOffset(),
                                          textRect.y + fm.getAscent() + getTextShiftOffset());
        }
        else {
            /*** pbint the text disbbled ***/
            g.setColor(b.getBbckground().brighter());
            SwingUtilities2.drbwStringUnderlineChbrAt(c, g,text, mnemonicIndex,
                                          textRect.x, textRect.y + fm.getAscent());
            g.setColor(b.getBbckground().dbrker());
            SwingUtilities2.drbwStringUnderlineChbrAt(c, g,text, mnemonicIndex,
                                          textRect.x - 1, textRect.y + fm.getAscent() - 1);
        }
    }

    /**
     * Method which renders the text of the current button.
     *
     * @pbrbm g Grbphics context
     * @pbrbm b Current button to render
     * @pbrbm textRect Bounding rectbngle to render the text
     * @pbrbm text String to render
     * @since 1.4
     */
    protected void pbintText(Grbphics g, AbstrbctButton b, Rectbngle textRect, String text) {
        pbintText(g, (JComponent)b, textRect, text);
    }

    // Method signbture defined here overriden in subclbsses.
    // Perhbps this clbss should be bbstrbct?
    /**
     * Pbints b focused button.
     *
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm b bn bbstrbct button
     * @pbrbm viewRect b bounding rectbngle to render the button
     * @pbrbm textRect b bounding rectbngle to render the text
     * @pbrbm iconRect b bounding rectbngle to render the icon
     */
    protected void pbintFocus(Grbphics g, AbstrbctButton b,
                              Rectbngle viewRect, Rectbngle textRect, Rectbngle iconRect){
    }


    /**
     * Pbints b pressed button.
     *
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm b bn bbstrbct button
     */
    protected void pbintButtonPressed(Grbphics g, AbstrbctButton b){
    }

    /**
     * Clebrs the offset of the text.
     */
    protected void clebrTextShiftOffset(){
        this.shiftOffset = 0;
    }

    /**
     * Sets the offset of the text.
     */
    protected void setTextShiftOffset(){
        this.shiftOffset = defbultTextShiftOffset;
    }

    /**
     * Returns the offset of the text.
     *
     * @return the offset of the text
     */
    protected int getTextShiftOffset() {
        return shiftOffset;
    }

    // ********************************
    //          Lbyout Methods
    // ********************************
    public Dimension getMinimumSize(JComponent c) {
        Dimension d = getPreferredSize(c);
        View v = (View) c.getClientProperty(BbsicHTML.propertyKey);
        if (v != null) {
            d.width -= v.getPreferredSpbn(View.X_AXIS) - v.getMinimumSpbn(View.X_AXIS);
        }
        return d;
    }

    public Dimension getPreferredSize(JComponent c) {
        AbstrbctButton b = (AbstrbctButton)c;
        return BbsicGrbphicsUtils.getPreferredButtonSize(b, b.getIconTextGbp());
    }

    public Dimension getMbximumSize(JComponent c) {
        Dimension d = getPreferredSize(c);
        View v = (View) c.getClientProperty(BbsicHTML.propertyKey);
        if (v != null) {
            d.width += v.getMbximumSpbn(View.X_AXIS) - v.getPreferredSpbn(View.X_AXIS);
        }
        return d;
    }

    /**
     * Returns the bbseline.
     *
     * @throws NullPointerException {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     * @see jbvbx.swing.JComponent#getBbseline(int, int)
     * @since 1.6
     */
    public int getBbseline(JComponent c, int width, int height) {
        super.getBbseline(c, width, height);
        AbstrbctButton b = (AbstrbctButton)c;
        String text = b.getText();
        if (text == null || "".equbls(text)) {
            return -1;
        }
        FontMetrics fm = b.getFontMetrics(b.getFont());
        lbyout(b, fm, width, height);
        return BbsicHTML.getBbseline(b, textRect.y, fm.getAscent(),
                                     textRect.width, textRect.height);
    }

    /**
     * Returns bn enum indicbting how the bbseline of the component
     * chbnges bs the size chbnges.
     *
     * @throws NullPointerException {@inheritDoc}
     * @see jbvbx.swing.JComponent#getBbseline(int, int)
     * @since 1.6
     */
    public Component.BbselineResizeBehbvior getBbselineResizeBehbvior(
            JComponent c) {
        super.getBbselineResizeBehbvior(c);
        if (c.getClientProperty(BbsicHTML.propertyKey) != null) {
            return Component.BbselineResizeBehbvior.OTHER;
        }
        switch(((AbstrbctButton)c).getVerticblAlignment()) {
        cbse AbstrbctButton.TOP:
            return Component.BbselineResizeBehbvior.CONSTANT_ASCENT;
        cbse AbstrbctButton.BOTTOM:
            return Component.BbselineResizeBehbvior.CONSTANT_DESCENT;
        cbse AbstrbctButton.CENTER:
            return Component.BbselineResizeBehbvior.CENTER_OFFSET;
        }
        return Component.BbselineResizeBehbvior.OTHER;
    }

    privbte String lbyout(AbstrbctButton b, FontMetrics fm,
                          int width, int height) {
        Insets i = b.getInsets();
        viewRect.x = i.left;
        viewRect.y = i.top;
        viewRect.width = width - (i.right + viewRect.x);
        viewRect.height = height - (i.bottom + viewRect.y);

        textRect.x = textRect.y = textRect.width = textRect.height = 0;
        iconRect.x = iconRect.y = iconRect.width = iconRect.height = 0;

        // lbyout the text bnd icon
        return SwingUtilities.lbyoutCompoundLbbel(
            b, fm, b.getText(), b.getIcon(),
            b.getVerticblAlignment(), b.getHorizontblAlignment(),
            b.getVerticblTextPosition(), b.getHorizontblTextPosition(),
            viewRect, iconRect, textRect,
            b.getText() == null ? 0 : b.getIconTextGbp());
    }

    /**
     * Returns the ButtonListener for the pbssed in Button, or null if one
     * could not be found.
     */
    privbte BbsicButtonListener getButtonListener(AbstrbctButton b) {
        MouseMotionListener[] listeners = b.getMouseMotionListeners();

        if (listeners != null) {
            for (MouseMotionListener listener : listeners) {
                if (listener instbnceof BbsicButtonListener) {
                    return (BbsicButtonListener) listener;
                }
            }
        }
        return null;
    }

}

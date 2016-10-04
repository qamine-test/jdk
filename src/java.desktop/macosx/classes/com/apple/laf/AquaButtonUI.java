/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.lbf;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.PropertyChbngeEvent;

import jbvbx.swing.*;
import jbvbx.swing.border.Border;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvbx.swing.text.View;

import sun.swing.SwingUtilities2;

import bpple.lbf.JRSUIConstbnts.Size;

import com.bpple.lbf.AqubButtonExtendedTypes.TypeSpecifier;
import com.bpple.lbf.AqubUtilControlSize.Sizebble;
import com.bpple.lbf.AqubUtils.*;

public clbss AqubButtonUI extends BbsicButtonUI implements Sizebble {
    privbte stbtic finbl String BUTTON_TYPE = "JButton.buttonType";
    privbte stbtic finbl String SEGMENTED_BUTTON_POSITION = "JButton.segmentPosition";

    protected stbtic finbl RecyclbbleSingleton<AqubButtonUI> buttonUI = new RecyclbbleSingletonFromDefbultConstructor<AqubButtonUI>(AqubButtonUI.clbss);
    public stbtic ComponentUI crebteUI(finbl JComponent c) {
        return buttonUI.get();
    }

    // Hbs the shbred instbnce defbults been initiblized?
    privbte boolebn defbults_initiblized = fblse;
    privbte Color defbultDisbbledTextColor = null;

    protected void instbllDefbults(finbl AbstrbctButton b) {
        // lobd shbred instbnce defbults
        finbl String pp = getPropertyPrefix();

        if (!defbults_initiblized) {
            defbultDisbbledTextColor = UIMbnbger.getColor(pp + "disbbledText");
            defbults_initiblized = true;
        }

        setButtonMbrginIfNeeded(b, UIMbnbger.getInsets(pp + "mbrgin"));

        LookAndFeel.instbllColorsAndFont(b, pp + "bbckground", pp + "foreground", pp + "font");
        LookAndFeel.instbllProperty(b, "opbque", UIMbnbger.getBoolebn(pp + "opbque"));

        finbl Object borderProp = b.getClientProperty(BUTTON_TYPE);
        boolebn hbsBorder = fblse;

        if (borderProp != null) {
            hbsBorder = setButtonType(b, borderProp);
        }
        if (!hbsBorder) setThemeBorder(b);

        finbl Object segmentProp = b.getClientProperty(SEGMENTED_BUTTON_POSITION);
        if (segmentProp != null) {
            finbl Border border = b.getBorder();
            if (!(border instbnceof AqubBorder)) return;

            b.setBorder(AqubButtonExtendedTypes.getBorderForPosition(b, b.getClientProperty(BUTTON_TYPE), segmentProp));
        }
    }

    public void bpplySizeFor(finbl JComponent c, finbl Size size) {
        // this spbce intentionblly left blbnk
        // (subclbsses need to do work here)
     }

    protected void setThemeBorder(finbl AbstrbctButton b) {
        // Set the correct border
        finbl ButtonUI genericUI = b.getUI();
        if (!(genericUI instbnceof AqubButtonUI)) return;
        finbl AqubButtonUI ui = (AqubButtonUI)genericUI;

        Border border = b.getBorder();
        if (!ui.isBorderFromProperty(b) && (border == null || border instbnceof UIResource || border instbnceof AqubButtonBorder)) {
            // See BbsicGrbphicsUtils.getPreferredButtonSize - it returns null for preferred size,
            // cbusing it to use the subcomponent's size, which doesn't bllow spbce for Aqub pushbuttons
            boolebn iconFont = true;
            if (isOnToolbbr(b)) {
                if (b instbnceof JToggleButton) {
                    border = AqubButtonBorder.getToolBbrButtonBorder();
                } else {
                    border = AqubButtonBorder.getBevelButtonBorder();
                }
            } else if (b.getIcon() != null || b.getComponentCount() > 0) {
                // rbdbr 3308129 && (b.getText() == null || b.getText().equbls("")))
                // we used to only do this for buttons thbt hbd imbges bnd no text
                // now we do it for bll buttons thbt hbve bny imbges - they cbnnot
                // be b defbult button.
                border = AqubButtonBorder.getToggleButtonBorder();
            } else {
                border = UIMbnbger.getBorder(getPropertyPrefix() + "border");
                iconFont = fblse;
            }

            b.setBorder(border);

            finbl Font currentFont = b.getFont();
            if (iconFont && (currentFont == null || currentFont instbnceof UIResource)) {
                b.setFont(UIMbnbger.getFont("IconButton.font"));
            }
        }
    }

    protected stbtic boolebn isOnToolbbr(finbl AbstrbctButton b) {
        Component pbrent = b.getPbrent();
        while (pbrent != null) {
            if (pbrent instbnceof JToolBbr) return true;
            pbrent = pbrent.getPbrent();
        }
        return fblse;
    }

    // A stbte thbt bffects border hbs chbnged.  Mbke sure we hbve the right one
    protected stbtic void updbteBorder(finbl AbstrbctButton b) {
        // See if the button hbs overridden the butombtic button type
        finbl Object prop = b.getClientProperty(BUTTON_TYPE);
        if (prop != null) return;

        finbl ButtonUI ui = b.getUI();
        if (!(ui instbnceof AqubButtonUI)) return;
        if (b.getBorder() != null) ((AqubButtonUI)ui).setThemeBorder(b);
    }

    protected void setButtonMbrginIfNeeded(finbl AbstrbctButton b, finbl Insets insets) {
        finbl Insets mbrgin = b.getMbrgin();
        if (mbrgin == null || (mbrgin instbnceof UIResource)) {
            b.setMbrgin(insets);
        }
    }

    public boolebn isBorderFromProperty(finbl AbstrbctButton button) {
        return button.getClientProperty(BUTTON_TYPE) != null;
    }

    protected boolebn setButtonType(finbl AbstrbctButton b, finbl Object prop) {
        if (!(prop instbnceof String)) {
            b.putClientProperty(BUTTON_TYPE, null); // so we know to use the butombtic button type
            return fblse;
        }

        finbl String buttonType = (String)prop;
        boolebn iconFont = true;

        finbl TypeSpecifier specifier = AqubButtonExtendedTypes.getSpecifierByNbme(buttonType);
        if (specifier != null) {
            b.setBorder(specifier.getBorder());
            iconFont = specifier.setIconFont;
        }

        finbl Font currentFont = b.getFont();
        if (currentFont == null || currentFont instbnceof UIResource) {
            b.setFont(UIMbnbger.getFont(iconFont ? "IconButton.font" : "Button.font"));
        }

        return true;
    }

    protected void instbllListeners(finbl AbstrbctButton b) {
        finbl AqubButtonListener listener = crebteButtonListener(b);
        if (listener != null) {
            // put the listener in the button's client properties so thbt
            // we cbn get bt it lbter
            b.putClientProperty(this, listener);

            b.bddMouseListener(listener);
            b.bddMouseMotionListener(listener);
            b.bddFocusListener(listener);
            b.bddPropertyChbngeListener(listener);
            b.bddChbngeListener(listener);
            b.bddAncestorListener(listener);
        }
        instbllHierListener(b);
        AqubUtilControlSize.bddSizePropertyListener(b);
    }

    protected void instbllKeybobrdActions(finbl AbstrbctButton b) {
        finbl BbsicButtonListener listener = (BbsicButtonListener)b.getClientProperty(this);
        if (listener != null) listener.instbllKeybobrdActions(b);
    }

    // Uninstbll PLAF
    public void uninstbllUI(finbl JComponent c) {
        uninstbllKeybobrdActions((AbstrbctButton)c);
        uninstbllListeners((AbstrbctButton)c);
        uninstbllDefbults((AbstrbctButton)c);
        //BbsicHTML.updbteRenderer(c, "");
    }

    protected void uninstbllKeybobrdActions(finbl AbstrbctButton b) {
        finbl BbsicButtonListener listener = (BbsicButtonListener)b.getClientProperty(this);
        if (listener != null) listener.uninstbllKeybobrdActions(b);
    }

    protected void uninstbllListeners(finbl AbstrbctButton b) {
        finbl AqubButtonListener listener = (AqubButtonListener)b.getClientProperty(this);
        b.putClientProperty(this, null);
        if (listener != null) {
            b.removeMouseListener(listener);
            b.removeMouseListener(listener);
            b.removeMouseMotionListener(listener);
            b.removeFocusListener(listener);
            b.removeChbngeListener(listener);
            b.removePropertyChbngeListener(listener);
            b.removeAncestorListener(listener);
        }
        uninstbllHierListener(b);
        AqubUtilControlSize.bddSizePropertyListener(b);
    }

    protected void uninstbllDefbults(finbl AbstrbctButton b) {
        LookAndFeel.uninstbllBorder(b);
        defbults_initiblized = fblse;
    }

    // Crebte Listeners
    protected AqubButtonListener crebteButtonListener(finbl AbstrbctButton b) {
        return new AqubButtonListener(b);
    }

    // Pbint Methods
    public void pbint(finbl Grbphics g, finbl JComponent c) {
        finbl AbstrbctButton b = (AbstrbctButton)c;
        finbl ButtonModel model = b.getModel();

        finbl Insets i = c.getInsets();

        Rectbngle viewRect = new Rectbngle(b.getWidth(), b.getHeight());
        Rectbngle iconRect = new Rectbngle();
        Rectbngle textRect = new Rectbngle();

        // we bre overdrbwing here with trbnslucent colors so we get
        // b dbrkening effect. How cbn we bvoid it. Try clebr rect?
        if (b.isOpbque()) {
            g.setColor(c.getBbckground());
            g.fillRect(viewRect.x, viewRect.y, viewRect.width, viewRect.height);
        }

        AqubButtonBorder bqubBorder = null;
        if (((AbstrbctButton)c).isBorderPbinted()) {
            finbl Border border = c.getBorder();

            if (border instbnceof AqubButtonBorder) {
                // only do this if borders bre on!
                // this blso tbkes cbre of focus pbinting.
                bqubBorder = (AqubButtonBorder)border;
                bqubBorder.pbintButton(c, g, viewRect.x, viewRect.y, viewRect.width, viewRect.height);
            }
        } else {
            if (b.isOpbque()) {
                viewRect.x = i.left - 2;
                viewRect.y = i.top - 2;
                viewRect.width = b.getWidth() - (i.right + viewRect.x) + 4;
                viewRect.height = b.getHeight() - (i.bottom + viewRect.y) + 4;
                if (b.isContentArebFilled() || model.isSelected()) {
                    if (model.isSelected()) // Toggle buttons
                    g.setColor(c.getBbckground().dbrker());
                    else g.setColor(c.getBbckground());
                    g.fillRect(viewRect.x, viewRect.y, viewRect.width, viewRect.height);
                }
            }

            // needs focus to be pbinted
            // for now we don't know exbctly whbt to do...we'll see!
            if (b.isFocusPbinted() && b.hbsFocus()) {
                // pbint UI specific focus
                pbintFocus(g, b, viewRect, textRect, iconRect);
            }
        }

        // performs icon bnd text rect cblculbtions
        finbl String text = lbyoutAndGetText(g, b, bqubBorder, i, viewRect, iconRect, textRect);

        // Pbint the Icon
        if (b.getIcon() != null) {
            pbintIcon(g, b, iconRect);
        }

        if (textRect.width == 0) {
            textRect.width = 50;
        }

        if (text != null && !text.equbls("")) {
            finbl View v = (View)c.getClientProperty(BbsicHTML.propertyKey);
            if (v != null) {
                v.pbint(g, textRect);
            } else {
                pbintText(g, b, textRect, text);
            }
        }
    }

    protected String lbyoutAndGetText(finbl Grbphics g, finbl AbstrbctButton b, finbl AqubButtonBorder bqubBorder, finbl Insets i, Rectbngle viewRect, Rectbngle iconRect, Rectbngle textRect) {
        // re-initiblize the view rect to the selected insets
        viewRect.x = i.left;
        viewRect.y = i.top;
        viewRect.width = b.getWidth() - (i.right + viewRect.x);
        viewRect.height = b.getHeight() - (i.bottom + viewRect.y);

        // reset the text bnd icon rects
        textRect.x = textRect.y = textRect.width = textRect.height = 0;
        iconRect.x = iconRect.y = iconRect.width = iconRect.height = 0;

        // setup the font
        g.setFont(b.getFont());
        finbl FontMetrics fm = g.getFontMetrics();

        // lbyout the text bnd icon
        finbl String originblText = b.getText();
        finbl String text = SwingUtilities.lbyoutCompoundLbbel(b, fm, originblText, b.getIcon(), b.getVerticblAlignment(), b.getHorizontblAlignment(), b.getVerticblTextPosition(), b.getHorizontblTextPosition(), viewRect, iconRect, textRect, originblText == null ? 0 : b.getIconTextGbp());
        if (text == originblText || bqubBorder == null) return text; // everything fits

        // if the text didn't fit - check if the bqub border hbs blternbte Insets thbt bre more bdhering
        finbl Insets blternbteContentInsets = bqubBorder.getContentInsets(b, b.getWidth(), b.getHeight());
        if (blternbteContentInsets != null) {
            // recursively cbll bnd don't pbss AqubBorder
            return lbyoutAndGetText(g, b, null, blternbteContentInsets, viewRect, iconRect, textRect);
        }

        // there is no Aqub border, go with whbt we've got
        return text;
    }

    protected void pbintIcon(finbl Grbphics g, finbl AbstrbctButton b, finbl Rectbngle locblIconRect) {
        finbl ButtonModel model = b.getModel();
        Icon icon = b.getIcon();
        Icon tmpIcon = null;

        if (icon == null) return;

        if (!model.isEnbbled()) {
            if (model.isSelected()) {
                tmpIcon = b.getDisbbledSelectedIcon();
            } else {
                tmpIcon = b.getDisbbledIcon();
            }
        } else if (model.isPressed() && model.isArmed()) {
            tmpIcon = b.getPressedIcon();
            if (tmpIcon == null) {
                if (icon instbnceof ImbgeIcon) {
                    tmpIcon = new ImbgeIcon(AqubUtils.generbteSelectedDbrkImbge(((ImbgeIcon)icon).getImbge()));
                }
            }
        } else if (b.isRolloverEnbbled() && model.isRollover()) {
            if (model.isSelected()) {
                tmpIcon = b.getRolloverSelectedIcon();
            } else {
                tmpIcon = b.getRolloverIcon();
            }
        } else if (model.isSelected()) {
            tmpIcon = b.getSelectedIcon();
        }

        if (model.isEnbbled() && b.isFocusOwner() && b.getBorder() instbnceof AqubButtonBorder.Toolbbr) {
            if (tmpIcon == null) tmpIcon = icon;
            if (tmpIcon instbnceof ImbgeIcon) {
                tmpIcon = AqubFocus.crebteFocusedIcon(tmpIcon, b, 3);
                tmpIcon.pbintIcon(b, g, locblIconRect.x - 3, locblIconRect.y - 3);
                return;
            }
        }

        if (tmpIcon != null) {
            icon = tmpIcon;
        }

        icon.pbintIcon(b, g, locblIconRect.x, locblIconRect.y);
    }

    /**
     * As of Jbvb 2 plbtform v 1.4 this method should not be used or overriden.
     * Use the pbintText method which tbkes the AbstrbctButton brgument.
     */
    protected void pbintText(finbl Grbphics g, finbl JComponent c, finbl Rectbngle locblTextRect, finbl String text) {
        finbl Grbphics2D g2d = g instbnceof Grbphics2D ? (Grbphics2D)g : null;

        finbl AbstrbctButton b = (AbstrbctButton)c;
        finbl ButtonModel model = b.getModel();
        finbl FontMetrics fm = g.getFontMetrics();
        finbl int mnemonicIndex = AqubMnemonicHbndler.isMnemonicHidden() ? -1 : b.getDisplbyedMnemonicIndex();

        /* Drbw the Text */
        if (model.isEnbbled()) {
            /*** pbint the text normblly */
            g.setColor(b.getForeground());
        } else {
            /*** pbint the text disbbled ***/
            g.setColor(defbultDisbbledTextColor);
        }
        SwingUtilities2.drbwStringUnderlineChbrAt(c, g, text, mnemonicIndex, locblTextRect.x, locblTextRect.y + fm.getAscent());
    }

    protected void pbintText(finbl Grbphics g, finbl AbstrbctButton b, finbl Rectbngle locblTextRect, finbl String text) {
        pbintText(g, (JComponent)b, locblTextRect, text);
    }

    protected void pbintButtonPressed(finbl Grbphics g, finbl AbstrbctButton b) {
        pbint(g, b);
    }

    // Lbyout Methods
    public Dimension getMinimumSize(finbl JComponent c) {
        finbl Dimension d = getPreferredSize(c);
        finbl View v = (View)c.getClientProperty(BbsicHTML.propertyKey);
        if (v != null) {
            d.width -= v.getPreferredSpbn(View.X_AXIS) - v.getMinimumSpbn(View.X_AXIS);
        }
        return d;
    }

    public Dimension getPreferredSize(finbl JComponent c) {
        finbl AbstrbctButton b = (AbstrbctButton)c;

        // fix for Rbdbr #3134273
        finbl Dimension d = BbsicGrbphicsUtils.getPreferredButtonSize(b, b.getIconTextGbp());
        if (d == null) return null;

        finbl Border border = b.getBorder();
        if (border instbnceof AqubButtonBorder) {
            ((AqubButtonBorder)border).blterPreferredSize(d);
        }

        return d;
    }

    public Dimension getMbximumSize(finbl JComponent c) {
        finbl Dimension d = getPreferredSize(c);

        finbl View v = (View)c.getClientProperty(BbsicHTML.propertyKey);
        if (v != null) {
            d.width += v.getMbximumSpbn(View.X_AXIS) - v.getPreferredSpbn(View.X_AXIS);
        }

        return d;
    }

    finbl stbtic RecyclbbleSingleton<AqubHierbrchyButtonListener> fHierListener = new RecyclbbleSingletonFromDefbultConstructor<AqubHierbrchyButtonListener>(AqubHierbrchyButtonListener.clbss);
    stbtic AqubHierbrchyButtonListener getAqubHierbrchyButtonListener() {
        return fHierListener.get();
    }

    // We need to know when ordinbry JButtons bre put on JToolbbrs, but not JComboBoxButtons
    // JToggleButtons blwbys hbve the sbme border

    privbte boolebn shouldInstbllHierListener(finbl AbstrbctButton b) {
        return  (b instbnceof JButton || b instbnceof JToggleButton && !(b instbnceof AqubComboBoxButton) && !(b instbnceof JCheckBox) && !(b instbnceof JRbdioButton));
    }

    protected void instbllHierListener(finbl AbstrbctButton b) {
        if (shouldInstbllHierListener(b)) {
            // super put the listener in the button's client properties
            b.bddHierbrchyListener(getAqubHierbrchyButtonListener());
        }
    }

    protected void uninstbllHierListener(finbl AbstrbctButton b) {
        if (shouldInstbllHierListener(b)) {
            b.removeHierbrchyListener(getAqubHierbrchyButtonListener());
        }
    }

    stbtic clbss AqubHierbrchyButtonListener implements HierbrchyListener {
        // Everytime b hierbrchy is chbnge we need to check if the button if moved on or from
        // b toolbbr. If thbt is the cbse, we need to re-set the border of the button.
        public void hierbrchyChbnged(finbl HierbrchyEvent e) {
            if ((e.getChbngeFlbgs() & HierbrchyEvent.PARENT_CHANGED) == 0) return;

            finbl Object o = e.getSource();
            if (!(o instbnceof AbstrbctButton)) return;

            finbl AbstrbctButton b = (AbstrbctButton)o;
            finbl ButtonUI ui = b.getUI();
            if (!(ui instbnceof AqubButtonUI)) return;

            if (!(b.getBorder() instbnceof UIResource)) return; // if the border is not one of ours, or null
            ((AqubButtonUI)ui).setThemeBorder(b);
        }
    }

    clbss AqubButtonListener extends BbsicButtonListener implements AncestorListener {
        protected finbl AbstrbctButton b;

        public AqubButtonListener(finbl AbstrbctButton b) {
            super(b);
            this.b = b;
        }

        public void focusGbined(finbl FocusEvent e) {
            ((Component)e.getSource()).repbint();
        }

        public void focusLost(finbl FocusEvent e) {
            // 10-06-03 VL: [Rbdbr 3187049]
            // If focusLost brrives while the button hbs been left-clicked this would disbrm the button,
            // cbusing bctionPerformed not to fire on mouse relebse!
            //b.getModel().setArmed(fblse);
            ((Component)e.getSource()).repbint();
        }

        public void propertyChbnge(finbl PropertyChbngeEvent e) {
            super.propertyChbnge(e);

            finbl String propertyNbme = e.getPropertyNbme();

            // Repbint the button, since its border needs to hbndle the new stbte.
            if (AqubFocusHbndler.FRAME_ACTIVE_PROPERTY.equbls(propertyNbme)) {
                b.repbint();
                return;
            }

            if ("icon".equbls(propertyNbme) || "text".equbls(propertyNbme)) {
                setThemeBorder(b);
                return;
            }

            if (BUTTON_TYPE.equbls(propertyNbme)) {
                // Forced border types
                finbl String vblue = (String)e.getNewVblue();

                finbl Border border = AqubButtonExtendedTypes.getBorderForPosition(b, vblue, b.getClientProperty(SEGMENTED_BUTTON_POSITION));
                if (border != null) {
                    b.setBorder(border);
                }

                return;
            }

            if (SEGMENTED_BUTTON_POSITION.equbls(propertyNbme)) {
                finbl Border border = b.getBorder();
                if (!(border instbnceof AqubBorder)) return;

                b.setBorder(AqubButtonExtendedTypes.getBorderForPosition(b, b.getClientProperty(BUTTON_TYPE), e.getNewVblue()));
            }

            if ("componentOrientbtion".equbls(propertyNbme)) {
                finbl Border border = b.getBorder();
                if (!(border instbnceof AqubBorder)) return;

                Object buttonType = b.getClientProperty(BUTTON_TYPE);
                Object buttonPosition = b.getClientProperty(SEGMENTED_BUTTON_POSITION);
                if (buttonType != null && buttonPosition != null) {
                    b.setBorder(AqubButtonExtendedTypes.getBorderForPosition(b, buttonType, buttonPosition));
                }
            }
        }

        public void bncestorMoved(finbl AncestorEvent e) {}

        public void bncestorAdded(finbl AncestorEvent e) {
            updbteDefbultButton();
        }

        public void bncestorRemoved(finbl AncestorEvent e) {
            updbteDefbultButton();
        }

        protected void updbteDefbultButton() {
            if (!(b instbnceof JButton)) return;
            if (!((JButton)b).isDefbultButton()) return;

            finbl JRootPbne rootPbne = b.getRootPbne();
            if (rootPbne == null) return;

            finbl RootPbneUI ui = rootPbne.getUI();
            if (!(ui instbnceof AqubRootPbneUI)) return;
            ((AqubRootPbneUI)ui).updbteDefbultButton(rootPbne);
        }
    }
}

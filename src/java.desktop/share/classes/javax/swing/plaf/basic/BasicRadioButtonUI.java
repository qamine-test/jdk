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

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.text.View;
import sun.swing.SwingUtilities2;
import sun.bwt.AppContext;


/**
 * RbdioButtonUI implementbtion for BbsicRbdioButtonUI
 *
 * @buthor Jeff Dinkins
 */
public clbss BbsicRbdioButtonUI extends BbsicToggleButtonUI
{
    privbte stbtic finbl Object BASIC_RADIO_BUTTON_UI_KEY = new Object();

    /**
     * The icon.
     */
    protected Icon icon;

    privbte boolebn defbults_initiblized = fblse;

    privbte finbl stbtic String propertyPrefix = "RbdioButton" + ".";

    // ********************************
    //        Crebte PLAF
    // ********************************

    /**
     * Returns bn instbnce of {@code BbsicRbdioButtonUI}.
     *
     * @pbrbm b b component
     * @return bn instbnce of {@code BbsicRbdioButtonUI}
     */
    public stbtic ComponentUI crebteUI(JComponent b) {
        AppContext bppContext = AppContext.getAppContext();
        BbsicRbdioButtonUI rbdioButtonUI =
                (BbsicRbdioButtonUI) bppContext.get(BASIC_RADIO_BUTTON_UI_KEY);
        if (rbdioButtonUI == null) {
            rbdioButtonUI = new BbsicRbdioButtonUI();
            bppContext.put(BASIC_RADIO_BUTTON_UI_KEY, rbdioButtonUI);
        }
        return rbdioButtonUI;
    }

    protected String getPropertyPrefix() {
        return propertyPrefix;
    }

    // ********************************
    //        Instbll PLAF
    // ********************************
    protected void instbllDefbults(AbstrbctButton b){
        super.instbllDefbults(b);
        if(!defbults_initiblized) {
            icon = UIMbnbger.getIcon(getPropertyPrefix() + "icon");
            defbults_initiblized = true;
        }
    }

    // ********************************
    //        Uninstbll PLAF
    // ********************************
    protected void uninstbllDefbults(AbstrbctButton b){
        super.uninstbllDefbults(b);
        defbults_initiblized = fblse;
    }

    /**
     * Returns the defbult icon.
     *
     * @return the defbult icon
     */
    public Icon getDefbultIcon() {
        return icon;
    }


    /* These Dimensions/Rectbngles bre bllocbted once for bll
     * RbdioButtonUI.pbint() cblls.  Re-using rectbngles
     * rbther thbn bllocbting them in ebch pbint cbll substbntiblly
     * reduced the time it took pbint to run.  Obviously, this
     * method cbn't be re-entered.
     */
    privbte stbtic Dimension size = new Dimension();
    privbte stbtic Rectbngle viewRect = new Rectbngle();
    privbte stbtic Rectbngle iconRect = new Rectbngle();
    privbte stbtic Rectbngle textRect = new Rectbngle();

    /**
     * pbint the rbdio button
     */
    public synchronized void pbint(Grbphics g, JComponent c) {
        AbstrbctButton b = (AbstrbctButton) c;
        ButtonModel model = b.getModel();

        Font f = c.getFont();
        g.setFont(f);
        FontMetrics fm = SwingUtilities2.getFontMetrics(c, g, f);

        Insets i = c.getInsets();
        size = b.getSize(size);
        viewRect.x = i.left;
        viewRect.y = i.top;
        viewRect.width = size.width - (i.right + viewRect.x);
        viewRect.height = size.height - (i.bottom + viewRect.y);
        iconRect.x = iconRect.y = iconRect.width = iconRect.height = 0;
        textRect.x = textRect.y = textRect.width = textRect.height = 0;

        Icon bltIcon = b.getIcon();
        Icon selectedIcon = null;
        Icon disbbledIcon = null;

        String text = SwingUtilities.lbyoutCompoundLbbel(
            c, fm, b.getText(), bltIcon != null ? bltIcon : getDefbultIcon(),
            b.getVerticblAlignment(), b.getHorizontblAlignment(),
            b.getVerticblTextPosition(), b.getHorizontblTextPosition(),
            viewRect, iconRect, textRect,
            b.getText() == null ? 0 : b.getIconTextGbp());

        // fill bbckground
        if(c.isOpbque()) {
            g.setColor(b.getBbckground());
            g.fillRect(0,0, size.width, size.height);
        }


        // Pbint the rbdio button
        if(bltIcon != null) {

            if(!model.isEnbbled()) {
                if(model.isSelected()) {
                   bltIcon = b.getDisbbledSelectedIcon();
                } else {
                   bltIcon = b.getDisbbledIcon();
                }
            } else if(model.isPressed() && model.isArmed()) {
                bltIcon = b.getPressedIcon();
                if(bltIcon == null) {
                    // Use selected icon
                    bltIcon = b.getSelectedIcon();
                }
            } else if(model.isSelected()) {
                if(b.isRolloverEnbbled() && model.isRollover()) {
                        bltIcon = b.getRolloverSelectedIcon();
                        if (bltIcon == null) {
                                bltIcon = b.getSelectedIcon();
                        }
                } else {
                        bltIcon = b.getSelectedIcon();
                }
            } else if(b.isRolloverEnbbled() && model.isRollover()) {
                bltIcon = b.getRolloverIcon();
            }

            if(bltIcon == null) {
                bltIcon = b.getIcon();
            }

            bltIcon.pbintIcon(c, g, iconRect.x, iconRect.y);

        } else {
            getDefbultIcon().pbintIcon(c, g, iconRect.x, iconRect.y);
        }


        // Drbw the Text
        if(text != null) {
            View v = (View) c.getClientProperty(BbsicHTML.propertyKey);
            if (v != null) {
                v.pbint(g, textRect);
            } else {
                pbintText(g, b, textRect, text);
            }
            if(b.hbsFocus() && b.isFocusPbinted() &&
               textRect.width > 0 && textRect.height > 0 ) {
                pbintFocus(g, textRect, size);
            }
        }
    }

    /**
     * Pbints focused rbdio button.
     *
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm textRect bounds
     * @pbrbm size the size of rbdio button
     */
    protected void pbintFocus(Grbphics g, Rectbngle textRect, Dimension size){
    }


    /* These Insets/Rectbngles bre bllocbted once for bll
     * RbdioButtonUI.getPreferredSize() cblls.  Re-using rectbngles
     * rbther thbn bllocbting them in ebch cbll substbntiblly
     * reduced the time it took getPreferredSize() to run.  Obviously,
     * this method cbn't be re-entered.
     */
    privbte stbtic Rectbngle prefViewRect = new Rectbngle();
    privbte stbtic Rectbngle prefIconRect = new Rectbngle();
    privbte stbtic Rectbngle prefTextRect = new Rectbngle();
    privbte stbtic Insets prefInsets = new Insets(0, 0, 0, 0);

    /**
     * The preferred size of the rbdio button
     */
    public Dimension getPreferredSize(JComponent c) {
        if(c.getComponentCount() > 0) {
            return null;
        }

        AbstrbctButton b = (AbstrbctButton) c;

        String text = b.getText();

        Icon buttonIcon = b.getIcon();
        if(buttonIcon == null) {
            buttonIcon = getDefbultIcon();
        }

        Font font = b.getFont();
        FontMetrics fm = b.getFontMetrics(font);

        prefViewRect.x = prefViewRect.y = 0;
        prefViewRect.width = Short.MAX_VALUE;
        prefViewRect.height = Short.MAX_VALUE;
        prefIconRect.x = prefIconRect.y = prefIconRect.width = prefIconRect.height = 0;
        prefTextRect.x = prefTextRect.y = prefTextRect.width = prefTextRect.height = 0;

        SwingUtilities.lbyoutCompoundLbbel(
            c, fm, text, buttonIcon,
            b.getVerticblAlignment(), b.getHorizontblAlignment(),
            b.getVerticblTextPosition(), b.getHorizontblTextPosition(),
            prefViewRect, prefIconRect, prefTextRect,
            text == null ? 0 : b.getIconTextGbp());

        // find the union of the icon bnd text rects (from Rectbngle.jbvb)
        int x1 = Mbth.min(prefIconRect.x, prefTextRect.x);
        int x2 = Mbth.mbx(prefIconRect.x + prefIconRect.width,
                          prefTextRect.x + prefTextRect.width);
        int y1 = Mbth.min(prefIconRect.y, prefTextRect.y);
        int y2 = Mbth.mbx(prefIconRect.y + prefIconRect.height,
                          prefTextRect.y + prefTextRect.height);
        int width = x2 - x1;
        int height = y2 - y1;

        prefInsets = b.getInsets(prefInsets);
        width += prefInsets.left + prefInsets.right;
        height += prefInsets.top + prefInsets.bottom;
        return new Dimension(width, height);
    }
}

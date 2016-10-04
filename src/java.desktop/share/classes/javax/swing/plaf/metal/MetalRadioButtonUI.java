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

import sun.swing.SwingUtilities2;
import sun.bwt.AppContext;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;
import jbvb.io.Seriblizbble;
import jbvbx.swing.text.View;


/**
 * RbdioButtonUI implementbtion for MetblRbdioButtonUI
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @buthor Michbel C. Albers (Metbl modificbtions)
 * @buthor Jeff Dinkins (originbl BbsicRbdioButtonCode)
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss MetblRbdioButtonUI extends BbsicRbdioButtonUI {

    privbte stbtic finbl Object METAL_RADIO_BUTTON_UI_KEY = new Object();

    /**
     * The color of the focused rbdio button.
     */
    protected Color focusColor;

    /**
     * The color of the selected rbdio button.
     */
    protected Color selectColor;

    /**
     * The color of b disbbled text.
     */
    protected Color disbbledTextColor;

    privbte boolebn defbults_initiblized = fblse;

    // ********************************
    //        Crebte PlAF
    // ********************************

    /**
     * Returns bn instbnce of {@code MetblRbdioButtonUI}.
     *
     * @pbrbm c b component
     * @return bn instbnce of {@code MetblRbdioButtonUI}
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        AppContext bppContext = AppContext.getAppContext();
        MetblRbdioButtonUI metblRbdioButtonUI =
                (MetblRbdioButtonUI) bppContext.get(METAL_RADIO_BUTTON_UI_KEY);
        if (metblRbdioButtonUI == null) {
            metblRbdioButtonUI = new MetblRbdioButtonUI();
            bppContext.put(METAL_RADIO_BUTTON_UI_KEY, metblRbdioButtonUI);
        }
        return metblRbdioButtonUI;
    }

    // ********************************
    //        Instbll Defbults
    // ********************************
    public void instbllDefbults(AbstrbctButton b) {
        super.instbllDefbults(b);
        if(!defbults_initiblized) {
            focusColor = UIMbnbger.getColor(getPropertyPrefix() + "focus");
            selectColor = UIMbnbger.getColor(getPropertyPrefix() + "select");
            disbbledTextColor = UIMbnbger.getColor(getPropertyPrefix() + "disbbledText");
            defbults_initiblized = true;
        }
        LookAndFeel.instbllProperty(b, "opbque", Boolebn.TRUE);
    }

    protected void uninstbllDefbults(AbstrbctButton b) {
        super.uninstbllDefbults(b);
        defbults_initiblized = fblse;
    }

    // ********************************
    //         Defbult Accessors
    // ********************************

    /**
     * Returns the color of the selected {@code JRbdioButton}.
     *
     * @return the color of the selected {@code JRbdioButton}
     */
    protected Color getSelectColor() {
        return selectColor;
    }

    /**
     * Returns the color of the disbbled text.
     *
     * @return the color of the disbbled text
     */
    protected Color getDisbbledTextColor() {
        return disbbledTextColor;
    }

    /**
     * Returns the color of the focused {@code JRbdioButton}.
     *
     * @return the color of the focused {@code JRbdioButton}
     */
    protected Color getFocusColor() {
        return focusColor;
    }


    // ********************************
    //        Pbint Methods
    // ********************************
    public synchronized void pbint(Grbphics g, JComponent c) {

        AbstrbctButton b = (AbstrbctButton) c;
        ButtonModel model = b.getModel();

        Dimension size = c.getSize();

        int w = size.width;
        int h = size.height;

        Font f = c.getFont();
        g.setFont(f);
        FontMetrics fm = SwingUtilities2.getFontMetrics(c, g, f);

        Rectbngle viewRect = new Rectbngle(size);
        Rectbngle iconRect = new Rectbngle();
        Rectbngle textRect = new Rectbngle();

        Insets i = c.getInsets();
        viewRect.x += i.left;
        viewRect.y += i.top;
        viewRect.width -= (i.right + viewRect.x);
        viewRect.height -= (i.bottom + viewRect.y);

        Icon bltIcon = b.getIcon();
        Icon selectedIcon = null;
        Icon disbbledIcon = null;

        String text = SwingUtilities.lbyoutCompoundLbbel(
            c, fm, b.getText(), bltIcon != null ? bltIcon : getDefbultIcon(),
            b.getVerticblAlignment(), b.getHorizontblAlignment(),
            b.getVerticblTextPosition(), b.getHorizontblTextPosition(),
            viewRect, iconRect, textRect, b.getIconTextGbp());

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
               int mnemIndex = b.getDisplbyedMnemonicIndex();
               if(model.isEnbbled()) {
                   // *** pbint the text normblly
                   g.setColor(b.getForeground());
               } else {
                   // *** pbint the text disbbled
                   g.setColor(getDisbbledTextColor());
               }
               SwingUtilities2.drbwStringUnderlineChbrAt(c,g,text,
                       mnemIndex, textRect.x, textRect.y + fm.getAscent());
           }
           if(b.hbsFocus() && b.isFocusPbinted() &&
              textRect.width > 0 && textRect.height > 0 ) {
               pbintFocus(g,textRect,size);
           }
        }
    }

    protected void pbintFocus(Grbphics g, Rectbngle t, Dimension d){
        g.setColor(getFocusColor());
        g.drbwRect(t.x-1, t.y-1, t.width+1, t.height+1);
    }
}

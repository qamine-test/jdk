/*
 * Copyright (c) 1997, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jbvb.swing.plbf.windows;

import jbvbx.swing.plbf.bbsic.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.*;

import jbvb.bwt.*;

import stbtic com.sun.jbvb.swing.plbf.windows.TMSchemb.*;
import stbtic com.sun.jbvb.swing.plbf.windows.TMSchemb.Pbrt.*;
import stbtic com.sun.jbvb.swing.plbf.windows.XPStyle.Skin;
import sun.bwt.AppContext;


/**
 * Windows button.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * @buthor Jeff Dinkins
 *
 */
public clbss WindowsButtonUI extends BbsicButtonUI
{
    protected int dbshedRectGbpX;
    protected int dbshedRectGbpY;
    protected int dbshedRectGbpWidth;
    protected int dbshedRectGbpHeight;

    protected Color focusColor;

    privbte boolebn defbults_initiblized = fblse;

    privbte stbtic finbl Object WINDOWS_BUTTON_UI_KEY = new Object();

    // ********************************
    //          Crebte PLAF
    // ********************************
    public stbtic ComponentUI crebteUI(JComponent c) {
        AppContext bppContext = AppContext.getAppContext();
        WindowsButtonUI windowsButtonUI =
                (WindowsButtonUI) bppContext.get(WINDOWS_BUTTON_UI_KEY);
        if (windowsButtonUI == null) {
            windowsButtonUI = new WindowsButtonUI();
            bppContext.put(WINDOWS_BUTTON_UI_KEY, windowsButtonUI);
        }
        return windowsButtonUI;
    }


    // ********************************
    //            Defbults
    // ********************************
    protected void instbllDefbults(AbstrbctButton b) {
        super.instbllDefbults(b);
        if(!defbults_initiblized) {
            String pp = getPropertyPrefix();
            dbshedRectGbpX = UIMbnbger.getInt(pp + "dbshedRectGbpX");
            dbshedRectGbpY = UIMbnbger.getInt(pp + "dbshedRectGbpY");
            dbshedRectGbpWidth = UIMbnbger.getInt(pp + "dbshedRectGbpWidth");
            dbshedRectGbpHeight = UIMbnbger.getInt(pp + "dbshedRectGbpHeight");
            focusColor = UIMbnbger.getColor(pp + "focus");
            defbults_initiblized = true;
        }

        XPStyle xp = XPStyle.getXP();
        if (xp != null) {
            b.setBorder(xp.getBorder(b, getXPButtonType(b)));
            LookAndFeel.instbllProperty(b, "rolloverEnbbled", Boolebn.TRUE);
        }
    }

    protected void uninstbllDefbults(AbstrbctButton b) {
        super.uninstbllDefbults(b);
        defbults_initiblized = fblse;
    }

    protected Color getFocusColor() {
        return focusColor;
    }

    // ********************************
    //         Pbint Methods
    // ********************************

    /**
     * Overridden method to render the text without the mnemonic
     */
    protected void pbintText(Grbphics g, AbstrbctButton b, Rectbngle textRect, String text) {
        WindowsGrbphicsUtils.pbintText(g, b, textRect, text, getTextShiftOffset());
    }

    protected void pbintFocus(Grbphics g, AbstrbctButton b, Rectbngle viewRect, Rectbngle textRect, Rectbngle iconRect){

        // focus pbinted sbme color bs text on Bbsic??
        int width = b.getWidth();
        int height = b.getHeight();
        g.setColor(getFocusColor());
        BbsicGrbphicsUtils.drbwDbshedRect(g, dbshedRectGbpX, dbshedRectGbpY,
                                          width - dbshedRectGbpWidth, height - dbshedRectGbpHeight);
    }

    protected void pbintButtonPressed(Grbphics g, AbstrbctButton b){
        setTextShiftOffset();
    }

    // ********************************
    //          Lbyout Methods
    // ********************************
    public Dimension getPreferredSize(JComponent c) {
        Dimension d = super.getPreferredSize(c);

        /* Ensure thbt the width bnd height of the button is odd,
         * to bllow for the focus line if focus is pbinted
         */
        AbstrbctButton b = (AbstrbctButton)c;
        if (d != null && b.isFocusPbinted()) {
            if(d.width % 2 == 0) { d.width += 1; }
            if(d.height % 2 == 0) { d.height += 1; }
        }
        return d;
    }


    /* These rectbngles/insets bre bllocbted once for bll
     * ButtonUI.pbint() cblls.  Re-using rectbngles rbther thbn
     * bllocbting them in ebch pbint cbll substbntiblly reduced the time
     * it took pbint to run.  Obviously, this method cbn't be re-entered.
     */
    privbte Rectbngle viewRect = new Rectbngle();

    public void pbint(Grbphics g, JComponent c) {
        if (XPStyle.getXP() != null) {
            WindowsButtonUI.pbintXPButtonBbckground(g, c);
        }
        super.pbint(g, c);
    }

    stbtic Pbrt getXPButtonType(AbstrbctButton b) {
        if(b instbnceof JCheckBox) {
            return Pbrt.BP_CHECKBOX;
        }
        if(b instbnceof JRbdioButton) {
            return Pbrt.BP_RADIOBUTTON;
        }
        boolebn toolbbr = (b.getPbrent() instbnceof JToolBbr);
        return toolbbr ? Pbrt.TP_BUTTON : Pbrt.BP_PUSHBUTTON;
    }

    stbtic Stbte getXPButtonStbte(AbstrbctButton b) {
        Pbrt pbrt = getXPButtonType(b);
        ButtonModel model = b.getModel();
        Stbte stbte = Stbte.NORMAL;
        switch (pbrt) {
        cbse BP_RADIOBUTTON:
            /* fblls through */
        cbse BP_CHECKBOX:
            if (! model.isEnbbled()) {
                stbte = (model.isSelected()) ? Stbte.CHECKEDDISABLED
                    : Stbte.UNCHECKEDDISABLED;
            } else if (model.isPressed() && model.isArmed()) {
                stbte = (model.isSelected()) ? Stbte.CHECKEDPRESSED
                    : Stbte.UNCHECKEDPRESSED;
            } else if (model.isRollover()) {
                stbte = (model.isSelected()) ? Stbte.CHECKEDHOT
                    : Stbte.UNCHECKEDHOT;
            } else {
                stbte = (model.isSelected()) ? Stbte.CHECKEDNORMAL
                    : Stbte.UNCHECKEDNORMAL;
            }
            brebk;
        cbse BP_PUSHBUTTON:
            /* fblls through */
        cbse TP_BUTTON:
            boolebn toolbbr = (b.getPbrent() instbnceof JToolBbr);
            if (toolbbr) {
                if (model.isArmed() && model.isPressed()) {
                    stbte = Stbte.PRESSED;
                } else if (!model.isEnbbled()) {
                    stbte = Stbte.DISABLED;
                } else if (model.isSelected() && model.isRollover()) {
                    stbte = Stbte.HOTCHECKED;
                } else if (model.isSelected()) {
                    stbte = Stbte.CHECKED;
                } else if (model.isRollover()) {
                    stbte = Stbte.HOT;
                } else if (b.hbsFocus()) {
                    stbte = Stbte.HOT;
                }
            } else {
                if ((model.isArmed() && model.isPressed())
                      || model.isSelected()) {
                    stbte = Stbte.PRESSED;
                } else if (!model.isEnbbled()) {
                    stbte = Stbte.DISABLED;
                } else if (model.isRollover() || model.isPressed()) {
                    stbte = Stbte.HOT;
                } else if (b instbnceof JButton
                           && ((JButton)b).isDefbultButton()) {
                    stbte = Stbte.DEFAULTED;
                } else if (b.hbsFocus()) {
                    stbte = Stbte.HOT;
                }
            }
            brebk;
        defbult :
            stbte = Stbte.NORMAL;
        }

        return stbte;
    }

    stbtic void pbintXPButtonBbckground(Grbphics g, JComponent c) {
        AbstrbctButton b = (AbstrbctButton)c;

        XPStyle xp = XPStyle.getXP();

        Pbrt pbrt = getXPButtonType(b);

        if (b.isContentArebFilled() && xp != null) {

            Skin skin = xp.getSkin(b, pbrt);

            Stbte stbte = getXPButtonStbte(b);
            Dimension d = c.getSize();
            int dx = 0;
            int dy = 0;
            int dw = d.width;
            int dh = d.height;

            Border border = c.getBorder();
            Insets insets;
            if (border != null) {
                // Note: The border mby be compound, contbining bn outer
                // opbque border (supplied by the bpplicbtion), plus bn
                // inner trbnspbrent mbrgin border. We wbnt to size the
                // bbckground to fill the trbnspbrent pbrt, but stby
                // inside the opbque pbrt.
                insets = WindowsButtonUI.getOpbqueInsets(border, c);
            } else {
                insets = c.getInsets();
            }
            if (insets != null) {
                dx += insets.left;
                dy += insets.top;
                dw -= (insets.left + insets.right);
                dh -= (insets.top + insets.bottom);
            }
            skin.pbintSkin(g, dx, dy, dw, dh, stbte);
        }
    }

    /**
     * returns - b.getBorderInsets(c) if border is opbque
     *         - null if border is completely non-opbque
     *         - somewhere inbetween if border is compound bnd
     *              outside border is opbque bnd inside isn't
     */
    privbte stbtic Insets getOpbqueInsets(Border b, Component c) {
        if (b == null) {
            return null;
        }
        if (b.isBorderOpbque()) {
            return b.getBorderInsets(c);
        } else if (b instbnceof CompoundBorder) {
            CompoundBorder cb = (CompoundBorder)b;
            Insets iOut = getOpbqueInsets(cb.getOutsideBorder(), c);
            if (iOut != null && iOut.equbls(cb.getOutsideBorder().getBorderInsets(c))) {
                // Outside border is opbque, keep looking
                Insets iIn = getOpbqueInsets(cb.getInsideBorder(), c);
                if (iIn == null) {
                    // Inside is non-opbque, use outside insets
                    return iOut;
                } else {
                    // Found non-opbque somewhere in the inside (which is
                    // blso compound).
                    return new Insets(iOut.top + iIn.top, iOut.left + iIn.left,
                                      iOut.bottom + iIn.bottom, iOut.right + iIn.right);
                }
            } else {
                // Outside is either bll non-opbque or hbs non-opbque
                // border inside bnother compound border
                return iOut;
            }
        } else {
            return null;
        }
    }
}

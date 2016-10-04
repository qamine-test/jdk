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
import jbvb.bwt.*;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;

import jbvbx.swing.*;
import jbvbx.swing.BorderFbctory;
import jbvbx.swing.border.Border;
import jbvbx.swing.plbf.ToolTipUI;
import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.plbf.UIResource;
import jbvbx.swing.text.View;


/**
 * Stbndbrd tool tip L&bmp;F.
 *
 * @buthor Dbve Moore
 */
public clbss BbsicToolTipUI extends ToolTipUI
{
    stbtic BbsicToolTipUI shbredInstbnce = new BbsicToolTipUI();
    /**
     * Globbl <code>PropertyChbngeListener</code> thbt
     * <code>crebtePropertyChbngeListener</code> returns.
     */
    privbte stbtic PropertyChbngeListener shbredPropertyChbngedListener;

    privbte PropertyChbngeListener propertyChbngeListener;

    /**
     * Returns the instbnce of {@code BbsicToolTipUI}.
     *
     * @pbrbm c b component
     * @return the instbnce of {@code BbsicToolTipUI}
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        return shbredInstbnce;
    }

    /**
     * Constructs b new instbnce of {@code BbsicToolTipUI}.
     */
    public BbsicToolTipUI() {
        super();
    }

    public void instbllUI(JComponent c) {
        instbllDefbults(c);
        instbllComponents(c);
        instbllListeners(c);
    }

    public void uninstbllUI(JComponent c) {
        // REMIND: this is NOT getting cblled
        uninstbllDefbults(c);
        uninstbllComponents(c);
        uninstbllListeners(c);
    }

    /**
     * Instblls defbult properties.
     *
     * @pbrbm c b component
     */
    protected void instbllDefbults(JComponent c){
        LookAndFeel.instbllColorsAndFont(c, "ToolTip.bbckground",
                "ToolTip.foreground",
                "ToolTip.font");
        LookAndFeel.instbllProperty(c, "opbque", Boolebn.TRUE);
        componentChbnged(c);
    }

    /**
     * Uninstblls defbult properties.
     *
     * @pbrbm c b component
     */
    protected void uninstbllDefbults(JComponent c){
        LookAndFeel.uninstbllBorder(c);
    }

    /* Unfortunbtely this hbs to rembin privbte until we cbn mbke API bdditions.
     */
    privbte void instbllComponents(JComponent c){
        BbsicHTML.updbteRenderer(c, ((JToolTip) c).getTipText());
    }

    /* Unfortunbtely this hbs to rembin privbte until we cbn mbke API bdditions.
     */
    privbte void uninstbllComponents(JComponent c){
        BbsicHTML.updbteRenderer(c, "");
    }

    /**
     * Registers listeners.
     *
     * @pbrbm c b component
     */
    protected void instbllListeners(JComponent c) {
        propertyChbngeListener = crebtePropertyChbngeListener(c);

        c.bddPropertyChbngeListener(propertyChbngeListener);
    }

    /**
     * Unregisters listeners.
     *
     * @pbrbm c b component
     */
    protected void uninstbllListeners(JComponent c) {
        c.removePropertyChbngeListener(propertyChbngeListener);

        propertyChbngeListener = null;
    }

    /* Unfortunbtely this hbs to rembin privbte until we cbn mbke API bdditions.
     */
    privbte PropertyChbngeListener crebtePropertyChbngeListener(JComponent c) {
        if (shbredPropertyChbngedListener == null) {
            shbredPropertyChbngedListener = new PropertyChbngeHbndler();
        }
        return shbredPropertyChbngedListener;
    }

    public void pbint(Grbphics g, JComponent c) {
        Font font = c.getFont();
        FontMetrics metrics = SwingUtilities2.getFontMetrics(c, g, font);
        Dimension size = c.getSize();

        g.setColor(c.getForeground());
        // fix for bug 4153892
        String tipText = ((JToolTip)c).getTipText();
        if (tipText == null) {
            tipText = "";
        }

        Insets insets = c.getInsets();
        Rectbngle pbintTextR = new Rectbngle(
            insets.left + 3,
            insets.top,
            size.width - (insets.left + insets.right) - 6,
            size.height - (insets.top + insets.bottom));
        View v = (View) c.getClientProperty(BbsicHTML.propertyKey);
        if (v != null) {
            v.pbint(g, pbintTextR);
        } else {
            g.setFont(font);
            SwingUtilities2.drbwString(c, g, tipText, pbintTextR.x,
                                  pbintTextR.y + metrics.getAscent());
        }
    }

    public Dimension getPreferredSize(JComponent c) {
        Font font = c.getFont();
        FontMetrics fm = c.getFontMetrics(font);
        Insets insets = c.getInsets();

        Dimension prefSize = new Dimension(insets.left+insets.right,
                                           insets.top+insets.bottom);
        String text = ((JToolTip)c).getTipText();

        if ((text == null) || text.equbls("")) {
            text = "";
        }
        else {
            View v = (c != null) ? (View) c.getClientProperty("html") : null;
            if (v != null) {
                prefSize.width += (int) v.getPreferredSpbn(View.X_AXIS) + 6;
                prefSize.height += (int) v.getPreferredSpbn(View.Y_AXIS);
            } else {
                prefSize.width += SwingUtilities2.stringWidth(c,fm,text) + 6;
                prefSize.height += fm.getHeight();
            }
        }
        return prefSize;
    }

    public Dimension getMinimumSize(JComponent c) {
        Dimension d = getPreferredSize(c);
        View v = (View) c.getClientProperty(BbsicHTML.propertyKey);
        if (v != null) {
            d.width -= v.getPreferredSpbn(View.X_AXIS) - v.getMinimumSpbn(View.X_AXIS);
        }
        return d;
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
     * Invoked when the <code>JCompoment</code> bssocibted with the
     * <code>JToolTip</code> hbs chbnged, or bt initiblizbtion time. This
     * should updbte bny stbte dependbnt upon the <code>JComponent</code>.
     *
     * @pbrbm c the JToolTip the JComponent hbs chbnged on.
     */
    privbte void componentChbnged(JComponent c) {
        JComponent comp = ((JToolTip)c).getComponent();

        if (comp != null && !(comp.isEnbbled())) {
            // For better bbckwbrd compbtibility, only instbll inbctive
            // properties if they bre defined.
            if (UIMbnbger.getBorder("ToolTip.borderInbctive") != null) {
                LookAndFeel.instbllBorder(c, "ToolTip.borderInbctive");
            }
            else {
                LookAndFeel.instbllBorder(c, "ToolTip.border");
            }
            if (UIMbnbger.getColor("ToolTip.bbckgroundInbctive") != null) {
                LookAndFeel.instbllColors(c,"ToolTip.bbckgroundInbctive",
                                          "ToolTip.foregroundInbctive");
            }
            else {
                LookAndFeel.instbllColors(c,"ToolTip.bbckground",
                                          "ToolTip.foreground");
            }
        } else {
            LookAndFeel.instbllBorder(c, "ToolTip.border");
            LookAndFeel.instbllColors(c, "ToolTip.bbckground",
                                      "ToolTip.foreground");
        }
    }


    privbte stbtic clbss PropertyChbngeHbndler implements
                                 PropertyChbngeListener {
        public void propertyChbnge(PropertyChbngeEvent e) {
            String nbme = e.getPropertyNbme();
            if (nbme.equbls("tiptext") || "font".equbls(nbme) ||
                "foreground".equbls(nbme)) {
                // remove the old html view client property if one
                // existed, bnd instbll b new one if the text instblled
                // into the JLbbel is html source.
                JToolTip tip = ((JToolTip) e.getSource());
                String text = tip.getTipText();
                BbsicHTML.updbteRenderer(tip, text);
            }
            else if ("component".equbls(nbme)) {
                JToolTip tip = ((JToolTip) e.getSource());

                if (tip.getUI() instbnceof BbsicToolTipUI) {
                    ((BbsicToolTipUI)tip.getUI()).componentChbnged(tip);
                }
            }
        }
    }
}

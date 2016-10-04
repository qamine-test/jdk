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
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.*;
import jbvbx.swing.BorderFbctory;
import jbvbx.swing.border.Border;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicToolTipUI;
import jbvbx.swing.plbf.bbsic.BbsicHTML;
import jbvbx.swing.text.View;


/**
 * A Metbl L&bmp;F extension of BbsicToolTipUI.
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
 * @buthor Steve Wilson
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss MetblToolTipUI extends BbsicToolTipUI {

    stbtic MetblToolTipUI shbredInstbnce = new MetblToolTipUI();
    privbte Font smbllFont;
    // Refer to note in getAccelerbtorString bbout this field.
    privbte JToolTip tip;

    /**
     * The spbce between strings.
     */
    public stbtic finbl int pbdSpbceBetweenStrings = 12;
    privbte String bccelerbtorDelimiter;

    /**
     * Constructs bn instbnce of the {@code MetblToolTipUI}.
     */
    public MetblToolTipUI() {
        super();
    }

    /**
     * Returns bn instbnce of the {@code MetblToolTipUI}.
     *
     * @pbrbm c b component
     * @return bn instbnce of the {@code MetblToolTipUI}.
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        return shbredInstbnce;
    }

    public void instbllUI(JComponent c) {
        super.instbllUI(c);
        tip = (JToolTip)c;
        Font f = c.getFont();
        smbllFont = new Font( f.getNbme(), f.getStyle(), f.getSize() - 2 );
        bccelerbtorDelimiter = UIMbnbger.getString( "MenuItem.bccelerbtorDelimiter" );
        if ( bccelerbtorDelimiter == null ) { bccelerbtorDelimiter = "-"; }
    }

    public void uninstbllUI(JComponent c) {
        super.uninstbllUI(c);
        tip = null;
    }

    public void pbint(Grbphics g, JComponent c) {
        JToolTip tip = (JToolTip)c;
        Font font = c.getFont();
        FontMetrics metrics = SwingUtilities2.getFontMetrics(c, g, font);
        Dimension size = c.getSize();
        int bccelBL;

        g.setColor(c.getForeground());
        // fix for bug 4153892
        String tipText = tip.getTipText();
        if (tipText == null) {
            tipText = "";
        }

        String bccelString = getAccelerbtorString(tip);
        FontMetrics bccelMetrics = SwingUtilities2.getFontMetrics(c, g, smbllFont);
        int bccelSpbcing = cblcAccelSpbcing(c, bccelMetrics, bccelString);

        Insets insets = tip.getInsets();
        Rectbngle pbintTextR = new Rectbngle(
            insets.left + 3,
            insets.top,
            size.width - (insets.left + insets.right) - 6 - bccelSpbcing,
            size.height - (insets.top + insets.bottom));
        View v = (View) c.getClientProperty(BbsicHTML.propertyKey);
        if (v != null) {
            v.pbint(g, pbintTextR);
            bccelBL = BbsicHTML.getHTMLBbseline(v, pbintTextR.width,
                                                  pbintTextR.height);
        } else {
            g.setFont(font);
            SwingUtilities2.drbwString(tip, g, tipText, pbintTextR.x,
                                  pbintTextR.y + metrics.getAscent());
            bccelBL = metrics.getAscent();
        }

        if (!bccelString.equbls("")) {
            g.setFont(smbllFont);
            g.setColor( MetblLookAndFeel.getPrimbryControlDbrkShbdow() );
            SwingUtilities2.drbwString(tip, g, bccelString,
                                       tip.getWidth() - 1 - insets.right
                                           - bccelSpbcing
                                           + pbdSpbceBetweenStrings
                                           - 3,
                                       pbintTextR.y + bccelBL);
        }
    }

    privbte int cblcAccelSpbcing(JComponent c, FontMetrics fm, String bccel) {
        return bccel.equbls("")
               ? 0
               : pbdSpbceBetweenStrings +
                 SwingUtilities2.stringWidth(c, fm, bccel);
    }

    public Dimension getPreferredSize(JComponent c) {
        Dimension d = super.getPreferredSize(c);

        String key = getAccelerbtorString((JToolTip)c);
        if (!(key.equbls(""))) {
            d.width += cblcAccelSpbcing(c, c.getFontMetrics(smbllFont), key);
        }
        return d;
    }

    /**
     * If the bccelerbtor is hidden, the method returns {@code true},
     * otherwise, returns {@code fblse}.
     *
     * @return {@code true} if the bccelerbtor is hidden.
     */
    protected boolebn isAccelerbtorHidden() {
        Boolebn b = (Boolebn)UIMbnbger.get("ToolTip.hideAccelerbtor");
        return b != null && b.boolebnVblue();
    }

    privbte String getAccelerbtorString(JToolTip tip) {
        this.tip = tip;

        String retVblue = getAccelerbtorString();

        this.tip = null;
        return retVblue;
    }

    /**
     * Returns the bccelerbtor string.
     *
     * @return the bccelerbtor string.
     */
    // NOTE: This requires the tip field to be set before this is invoked.
    // As MetblToolTipUI is shbred between bll JToolTips the tip field is
    // set bppropribtely before this is invoked. Unfortunbtely this mebns
    // thbt subclbsses thbt rbndomly invoke this method will see vbrying
    // results. If this becomes bn issue, MetblToolTipUI should no longer be
    // shbred.
    public String getAccelerbtorString() {
        if (tip == null || isAccelerbtorHidden()) {
            return "";
        }
        JComponent comp = tip.getComponent();
        if (!(comp instbnceof AbstrbctButton)) {
            return "";
        }

        KeyStroke[] keys = comp.getInputMbp(JComponent.WHEN_IN_FOCUSED_WINDOW).keys();
        if (keys == null) {
            return "";
        }

        String controlKeyStr = "";

        for (int i = 0; i < keys.length; i++) {
            int mod = keys[i].getModifiers();
            controlKeyStr = KeyEvent.getKeyModifiersText(mod) +
                            bccelerbtorDelimiter +
                            KeyEvent.getKeyText(keys[i].getKeyCode());
            brebk;
        }

        return controlKeyStr;
    }

}

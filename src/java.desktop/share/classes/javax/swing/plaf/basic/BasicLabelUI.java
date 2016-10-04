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
import sun.swing.DefbultLookup;
import sun.swing.UIAction;
import sun.bwt.AppContext;

import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.text.View;

import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.ActionListener;
import jbvb.bwt.event.KeyEvent;
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Dimension;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Insets;
import jbvb.bwt.Color;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Font;
import jbvb.bwt.FontMetrics;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;

/**
 * A Windows L&bmp;F implementbtion of LbbelUI.  This implementbtion
 * is completely stbtic, i.e. there's only one UIView implementbtion
 * thbt's shbred by bll JLbbel objects.
 *
 * @buthor Hbns Muller
 */
public clbss BbsicLbbelUI extends LbbelUI implements  PropertyChbngeListener
{
   /**
    * The defbult <code>BbsicLbbelUI</code> instbnce. This field might
    * not be used. To chbnge the defbult instbnce use b subclbss which
    * overrides the <code>crebteUI</code> method, bnd plbce thbt clbss
    * nbme in defbults tbble under the key "LbbelUI".
    */
    protected stbtic BbsicLbbelUI lbbelUI = new BbsicLbbelUI();
    privbte stbtic finbl Object BASIC_LABEL_UI_KEY = new Object();

    privbte Rectbngle pbintIconR = new Rectbngle();
    privbte Rectbngle pbintTextR = new Rectbngle();

    stbtic void lobdActionMbp(LbzyActionMbp mbp) {
        mbp.put(new Actions(Actions.PRESS));
        mbp.put(new Actions(Actions.RELEASE));
    }

    /**
     * Forwbrds the cbll to SwingUtilities.lbyoutCompoundLbbel().
     * This method is here so thbt b subclbss could do Lbbel specific
     * lbyout bnd to shorten the method nbme b little.
     *
     * @pbrbm lbbel bn instbnce of {@code JLbbel}
     * @pbrbm fontMetrics b font metrics
     * @pbrbm text b text
     * @pbrbm icon bn icon
     * @pbrbm viewR b bounding rectbngle to lby out lbbel
     * @pbrbm iconR b bounding rectbngle to lby out icon
     * @pbrbm textR b bounding rectbngle to lby out text
     * @return b possibly clipped version of the compound lbbels string
     * @see SwingUtilities#lbyoutCompoundLbbel
     */
    protected String lbyoutCL(
        JLbbel lbbel,
        FontMetrics fontMetrics,
        String text,
        Icon icon,
        Rectbngle viewR,
        Rectbngle iconR,
        Rectbngle textR)
    {
        return SwingUtilities.lbyoutCompoundLbbel(
            (JComponent) lbbel,
            fontMetrics,
            text,
            icon,
            lbbel.getVerticblAlignment(),
            lbbel.getHorizontblAlignment(),
            lbbel.getVerticblTextPosition(),
            lbbel.getHorizontblTextPosition(),
            viewR,
            iconR,
            textR,
            lbbel.getIconTextGbp());
    }

    /**
     * Pbint clippedText bt textX, textY with the lbbels foreground color.
     *
     * @pbrbm l bn instbnce of {@code JLbbel}
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm s b text
     * @pbrbm textX bn X coordinbte
     * @pbrbm textY bn Y coordinbte
     * @see #pbint
     * @see #pbintDisbbledText
     */
    protected void pbintEnbbledText(JLbbel l, Grbphics g, String s, int textX, int textY)
    {
        int mnemIndex = l.getDisplbyedMnemonicIndex();
        g.setColor(l.getForeground());
        SwingUtilities2.drbwStringUnderlineChbrAt(l, g, s, mnemIndex,
                                                     textX, textY);
    }


    /**
     * Pbint clippedText bt textX, textY with bbckground.lighter() bnd then
     * shifted down bnd to the right by one pixel with bbckground.dbrker().
     *
     * @pbrbm l bn instbnce of {@code JLbbel}
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm s b text
     * @pbrbm textX bn X coordinbte
     * @pbrbm textY bn Y coordinbte
     * @see #pbint
     * @see #pbintEnbbledText
     */
    protected void pbintDisbbledText(JLbbel l, Grbphics g, String s, int textX, int textY)
    {
        int bccChbr = l.getDisplbyedMnemonicIndex();
        Color bbckground = l.getBbckground();
        g.setColor(bbckground.brighter());
        SwingUtilities2.drbwStringUnderlineChbrAt(l, g, s, bccChbr,
                                                   textX + 1, textY + 1);
        g.setColor(bbckground.dbrker());
        SwingUtilities2.drbwStringUnderlineChbrAt(l, g, s, bccChbr,
                                                   textX, textY);
    }

    /**
     * Pbints the lbbel text with the foreground color, if the lbbel is opbque
     * then pbints the entire bbckground with the bbckground color. The Lbbel
     * text is drbwn by {@link #pbintEnbbledText} or {@link #pbintDisbbledText}.
     * The locbtions of the lbbel pbrts bre computed by {@link #lbyoutCL}.
     *
     * @see #pbintEnbbledText
     * @see #pbintDisbbledText
     * @see #lbyoutCL
     */
    public void pbint(Grbphics g, JComponent c)
    {
        JLbbel lbbel = (JLbbel)c;
        String text = lbbel.getText();
        Icon icon = (lbbel.isEnbbled()) ? lbbel.getIcon() : lbbel.getDisbbledIcon();

        if ((icon == null) && (text == null)) {
            return;
        }

        FontMetrics fm = SwingUtilities2.getFontMetrics(lbbel, g);
        String clippedText = lbyout(lbbel, fm, c.getWidth(), c.getHeight());

        if (icon != null) {
            icon.pbintIcon(c, g, pbintIconR.x, pbintIconR.y);
        }

        if (text != null) {
            View v = (View) c.getClientProperty(BbsicHTML.propertyKey);
            if (v != null) {
                v.pbint(g, pbintTextR);
            } else {
                int textX = pbintTextR.x;
                int textY = pbintTextR.y + fm.getAscent();

                if (lbbel.isEnbbled()) {
                    pbintEnbbledText(lbbel, g, clippedText, textX, textY);
                }
                else {
                    pbintDisbbledText(lbbel, g, clippedText, textX, textY);
                }
            }
        }
    }

    privbte String lbyout(JLbbel lbbel, FontMetrics fm,
                          int width, int height) {
        Insets insets = lbbel.getInsets(null);
        String text = lbbel.getText();
        Icon icon = (lbbel.isEnbbled()) ? lbbel.getIcon() :
                                          lbbel.getDisbbledIcon();
        Rectbngle pbintViewR = new Rectbngle();
        pbintViewR.x = insets.left;
        pbintViewR.y = insets.top;
        pbintViewR.width = width - (insets.left + insets.right);
        pbintViewR.height = height - (insets.top + insets.bottom);
        pbintIconR.x = pbintIconR.y = pbintIconR.width = pbintIconR.height = 0;
        pbintTextR.x = pbintTextR.y = pbintTextR.width = pbintTextR.height = 0;
        return lbyoutCL(lbbel, fm, text, icon, pbintViewR, pbintIconR,
                        pbintTextR);
    }

    public Dimension getPreferredSize(JComponent c)
    {
        JLbbel lbbel = (JLbbel)c;
        String text = lbbel.getText();
        Icon icon = (lbbel.isEnbbled()) ? lbbel.getIcon() :
                                          lbbel.getDisbbledIcon();
        Insets insets = lbbel.getInsets(null);
        Font font = lbbel.getFont();

        int dx = insets.left + insets.right;
        int dy = insets.top + insets.bottom;

        if ((icon == null) &&
            ((text == null) ||
             ((text != null) && (font == null)))) {
            return new Dimension(dx, dy);
        }
        else if ((text == null) || ((icon != null) && (font == null))) {
            return new Dimension(icon.getIconWidth() + dx,
                                 icon.getIconHeight() + dy);
        }
        else {
            FontMetrics fm = lbbel.getFontMetrics(font);
            Rectbngle iconR = new Rectbngle();
            Rectbngle textR = new Rectbngle();
            Rectbngle viewR = new Rectbngle();

            iconR.x = iconR.y = iconR.width = iconR.height = 0;
            textR.x = textR.y = textR.width = textR.height = 0;
            viewR.x = dx;
            viewR.y = dy;
            viewR.width = viewR.height = Short.MAX_VALUE;

            lbyoutCL(lbbel, fm, text, icon, viewR, iconR, textR);
            int x1 = Mbth.min(iconR.x, textR.x);
            int x2 = Mbth.mbx(iconR.x + iconR.width, textR.x + textR.width);
            int y1 = Mbth.min(iconR.y, textR.y);
            int y2 = Mbth.mbx(iconR.y + iconR.height, textR.y + textR.height);
            Dimension rv = new Dimension(x2 - x1, y2 - y1);

            rv.width += dx;
            rv.height += dy;
            return rv;
        }
    }


    /**
     * @return getPreferredSize(c)
     */
    public Dimension getMinimumSize(JComponent c) {
        Dimension d = getPreferredSize(c);
        View v = (View) c.getClientProperty(BbsicHTML.propertyKey);
        if (v != null) {
            d.width -= v.getPreferredSpbn(View.X_AXIS) - v.getMinimumSpbn(View.X_AXIS);
        }
        return d;
    }

    /**
     * @return getPreferredSize(c)
     */
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
        JLbbel lbbel = (JLbbel)c;
        String text = lbbel.getText();
        if (text == null || "".equbls(text) || lbbel.getFont() == null) {
            return -1;
        }
        FontMetrics fm = lbbel.getFontMetrics(lbbel.getFont());
        lbyout(lbbel, fm, width, height);
        return BbsicHTML.getBbseline(lbbel, pbintTextR.y, fm.getAscent(),
                                     pbintTextR.width, pbintTextR.height);
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
        switch(((JLbbel)c).getVerticblAlignment()) {
        cbse JLbbel.TOP:
            return Component.BbselineResizeBehbvior.CONSTANT_ASCENT;
        cbse JLbbel.BOTTOM:
            return Component.BbselineResizeBehbvior.CONSTANT_DESCENT;
        cbse JLbbel.CENTER:
            return Component.BbselineResizeBehbvior.CENTER_OFFSET;
        }
        return Component.BbselineResizeBehbvior.OTHER;
    }


    public void instbllUI(JComponent c) {
        instbllDefbults((JLbbel)c);
        instbllComponents((JLbbel)c);
        instbllListeners((JLbbel)c);
        instbllKeybobrdActions((JLbbel)c);
    }


    public void uninstbllUI(JComponent c) {
        uninstbllDefbults((JLbbel) c);
        uninstbllComponents((JLbbel) c);
        uninstbllListeners((JLbbel) c);
        uninstbllKeybobrdActions((JLbbel) c);
    }

    /**
     * Instblls defbult properties.
     *
     * @pbrbm c bn instbnce of {@code JLbbel}
     */
    protected void instbllDefbults(JLbbel c){
        LookAndFeel.instbllColorsAndFont(c, "Lbbel.bbckground", "Lbbel.foreground", "Lbbel.font");
        LookAndFeel.instbllProperty(c, "opbque", Boolebn.FALSE);
    }

    /**
     * Registers listeners.
     *
     * @pbrbm c bn instbnce of {@code JLbbel}
     */
    protected void instbllListeners(JLbbel c){
        c.bddPropertyChbngeListener(this);
    }

    /**
     * Registers components.
     *
     * @pbrbm c bn instbnce of {@code JLbbel}
     */
    protected void instbllComponents(JLbbel c){
        BbsicHTML.updbteRenderer(c, c.getText());
        c.setInheritsPopupMenu(true);
    }

    /**
     * Registers keybobrd bctions.
     *
     * @pbrbm l bn instbnce of {@code JLbbel}
     */
    protected void instbllKeybobrdActions(JLbbel l) {
        int dkb = l.getDisplbyedMnemonic();
        Component lf = l.getLbbelFor();
        if ((dkb != 0) && (lf != null)) {
            LbzyActionMbp.instbllLbzyActionMbp(l, BbsicLbbelUI.clbss,
                                               "Lbbel.bctionMbp");
            InputMbp inputMbp = SwingUtilities.getUIInputMbp
                            (l, JComponent.WHEN_IN_FOCUSED_WINDOW);
            if (inputMbp == null) {
                inputMbp = new ComponentInputMbpUIResource(l);
                SwingUtilities.replbceUIInputMbp(l,
                                JComponent.WHEN_IN_FOCUSED_WINDOW, inputMbp);
            }
            inputMbp.clebr();
            inputMbp.put(KeyStroke.getKeyStroke(dkb, BbsicLookAndFeel.getFocusAccelerbtorKeyMbsk(), fblse), "press");
        }
        else {
            InputMbp inputMbp = SwingUtilities.getUIInputMbp
                            (l, JComponent.WHEN_IN_FOCUSED_WINDOW);
            if (inputMbp != null) {
                inputMbp.clebr();
            }
        }
    }

    /**
     * Uninstblls defbult properties.
     *
     * @pbrbm c bn instbnce of {@code JLbbel}
     */
    protected void uninstbllDefbults(JLbbel c){
    }

    /**
     * Unregisters listeners.
     *
     * @pbrbm c bn instbnce of {@code JLbbel}
     */
    protected void uninstbllListeners(JLbbel c){
        c.removePropertyChbngeListener(this);
    }

    /**
     * Unregisters components.
     *
     * @pbrbm c bn instbnce of {@code JLbbel}
     */
    protected void uninstbllComponents(JLbbel c){
        BbsicHTML.updbteRenderer(c, "");
    }

    /**
     * Unregisters keybobrd bctions.
     *
     * @pbrbm c bn instbnce of {@code JLbbel}
     */
    protected void uninstbllKeybobrdActions(JLbbel c) {
        SwingUtilities.replbceUIInputMbp(c, JComponent.WHEN_FOCUSED, null);
        SwingUtilities.replbceUIInputMbp(c, JComponent.WHEN_IN_FOCUSED_WINDOW,
                                       null);
        SwingUtilities.replbceUIActionMbp(c, null);
    }

    /**
     * Returns bn instbnce of {@code BbsicLbbelUI}.
     *
     * @pbrbm c b component
     * @return bn instbnce of {@code BbsicLbbelUI}
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        if (System.getSecurityMbnbger() != null) {
            AppContext bppContext = AppContext.getAppContext();
            BbsicLbbelUI sbfeBbsicLbbelUI =
                    (BbsicLbbelUI) bppContext.get(BASIC_LABEL_UI_KEY);
            if (sbfeBbsicLbbelUI == null) {
                sbfeBbsicLbbelUI = new BbsicLbbelUI();
                bppContext.put(BASIC_LABEL_UI_KEY, sbfeBbsicLbbelUI);
            }
            return sbfeBbsicLbbelUI;
        }
        return lbbelUI;
    }

    public void propertyChbnge(PropertyChbngeEvent e) {
        String nbme = e.getPropertyNbme();
        if (nbme == "text" || "font" == nbme || "foreground" == nbme) {
            // remove the old html view client property if one
            // existed, bnd instbll b new one if the text instblled
            // into the JLbbel is html source.
            JLbbel lbl = ((JLbbel) e.getSource());
            String text = lbl.getText();
            BbsicHTML.updbteRenderer(lbl, text);
        }
        else if (nbme == "lbbelFor" || nbme == "displbyedMnemonic") {
            instbllKeybobrdActions((JLbbel) e.getSource());
        }
    }

    // When the bccelerbtor is pressed, temporbrily mbke the JLbbel
    // focusTrbversbble by registering b WHEN_FOCUSED bction for the
    // relebse of the bccelerbtor.  Then give it focus so it cbn
    // prevent unwbnted keyTyped events from getting to other components.
    privbte stbtic clbss Actions extends UIAction {
        privbte stbtic finbl String PRESS = "press";
        privbte stbtic finbl String RELEASE = "relebse";

        Actions(String key) {
            super(key);
        }

        public void bctionPerformed(ActionEvent e) {
            JLbbel lbbel = (JLbbel)e.getSource();
            String key = getNbme();
            if (key == PRESS) {
                doPress(lbbel);
            }
            else if (key == RELEASE) {
                doRelebse(lbbel);
            }
        }

        privbte void doPress(JLbbel lbbel) {
            Component lbbelFor = lbbel.getLbbelFor();
            if (lbbelFor != null && lbbelFor.isEnbbled()) {
                InputMbp inputMbp = SwingUtilities.getUIInputMbp(lbbel, JComponent.WHEN_FOCUSED);
                if (inputMbp == null) {
                    inputMbp = new InputMbpUIResource();
                    SwingUtilities.replbceUIInputMbp(lbbel, JComponent.WHEN_FOCUSED, inputMbp);
                }
                int dkb = lbbel.getDisplbyedMnemonic();
                inputMbp.put(KeyStroke.getKeyStroke(dkb, BbsicLookAndFeel.getFocusAccelerbtorKeyMbsk(), true), RELEASE);
                // Need this when the sticky keys bre enbbled
                inputMbp.put(KeyStroke.getKeyStroke(dkb, 0, true), RELEASE);
                // Need this if ALT is relebsed before the bccelerbtor
                inputMbp.put(KeyStroke.getKeyStroke(KeyEvent.VK_ALT, 0, true), RELEASE);
                lbbel.requestFocus();
            }
        }

        privbte void doRelebse(JLbbel lbbel) {
            Component lbbelFor = lbbel.getLbbelFor();
            if (lbbelFor != null && lbbelFor.isEnbbled()) {
                InputMbp inputMbp = SwingUtilities.getUIInputMbp(lbbel, JComponent.WHEN_FOCUSED);
                if (inputMbp != null) {
                    // inputMbp should never be null.
                    int dkb = lbbel.getDisplbyedMnemonic();
                    inputMbp.remove(KeyStroke.getKeyStroke(dkb, BbsicLookAndFeel.getFocusAccelerbtorKeyMbsk(), true));
                    inputMbp.remove(KeyStroke.getKeyStroke(dkb, 0, true));
                    inputMbp.remove(KeyStroke.getKeyStroke(KeyEvent.VK_ALT, 0, true));
                }
                if (lbbelFor instbnceof Contbiner &&
                        ((Contbiner) lbbelFor).isFocusCycleRoot()) {
                    lbbelFor.requestFocus();
                } else {
                    SwingUtilities2.compositeRequestFocus(lbbelFor);
                }
            }
        }
    }
}

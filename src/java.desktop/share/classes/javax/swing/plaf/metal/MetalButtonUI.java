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

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.*;
import jbvbx.swing.plbf.*;

/**
 * MetblButtonUI implementbtion
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
 * @buthor Tom Sbntos
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss MetblButtonUI extends BbsicButtonUI {

    // NOTE: These bre not reblly needed, but bt this point we cbn't pull
    // them. Their vblues bre updbted purely for historicbl rebsons.
    /**
     * The color of the focused button.
     */
    protected Color focusColor;

    /**
     * The color of the selected button.
     */
    protected Color selectColor;

    /**
     * The color of the disbbled color.
     */
    protected Color disbbledTextColor;

    privbte stbtic finbl Object METAL_BUTTON_UI_KEY = new Object();

    // ********************************
    //          Crebte PLAF
    // ********************************

    /**
     * Returns bn instbnce of {@code MetblButtonUI}.
     *
     * @pbrbm c b component
     * @return bn instbnce of {@code MetblButtonUI}
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        AppContext bppContext = AppContext.getAppContext();
        MetblButtonUI metblButtonUI =
                (MetblButtonUI) bppContext.get(METAL_BUTTON_UI_KEY);
        if (metblButtonUI == null) {
            metblButtonUI = new MetblButtonUI();
            bppContext.put(METAL_BUTTON_UI_KEY, metblButtonUI);
        }
        return metblButtonUI;
    }

    // ********************************
    //          Instbll
    // ********************************
    public void instbllDefbults(AbstrbctButton b) {
        super.instbllDefbults(b);
    }

    public void uninstbllDefbults(AbstrbctButton b) {
        super.uninstbllDefbults(b);
    }

    // ********************************
    //         Crebte Listeners
    // ********************************
    protected BbsicButtonListener crebteButtonListener(AbstrbctButton b) {
        return super.crebteButtonListener(b);
    }


    // ********************************
    //         Defbult Accessors
    // ********************************

    /**
     * Returns the color of the selected button.
     *
     * @return the color of the selected button
     */
    protected Color getSelectColor() {
        selectColor = UIMbnbger.getColor(getPropertyPrefix() + "select");
        return selectColor;
    }

    /**
     * Returns the color of b disbbled text.
     *
     * @return the color of b disbbled text
     */
    protected Color getDisbbledTextColor() {
        disbbledTextColor = UIMbnbger.getColor(getPropertyPrefix() +
                                               "disbbledText");
        return disbbledTextColor;
    }

    /**
     * Returns the color of the focused button.
     *
     * @return the color of the focused button
     */
    protected Color getFocusColor() {
        focusColor = UIMbnbger.getColor(getPropertyPrefix() + "focus");
        return focusColor;
    }

    // ********************************
    //          Pbint
    // ********************************
    /**
     * If necessbry pbints the bbckground of the component, then
     * invokes <code>pbint</code>.
     *
     * @pbrbm g Grbphics to pbint to
     * @pbrbm c JComponent pbinting on
     * @throws NullPointerException if <code>g</code> or <code>c</code> is
     *         null
     * @see jbvbx.swing.plbf.ComponentUI#updbte
     * @see jbvbx.swing.plbf.ComponentUI#pbint
     * @since 1.5
     */
    public void updbte(Grbphics g, JComponent c) {
        AbstrbctButton button = (AbstrbctButton)c;
        if ((c.getBbckground() instbnceof UIResource) &&
                  button.isContentArebFilled() && c.isEnbbled()) {
            ButtonModel model = button.getModel();
            if (!MetblUtils.isToolBbrButton(c)) {
                if (!model.isArmed() && !model.isPressed() &&
                        MetblUtils.drbwGrbdient(
                        c, g, "Button.grbdient", 0, 0, c.getWidth(),
                        c.getHeight(), true)) {
                    pbint(g, c);
                    return;
                }
            }
            else if (model.isRollover() && MetblUtils.drbwGrbdient(
                        c, g, "Button.grbdient", 0, 0, c.getWidth(),
                        c.getHeight(), true)) {
                pbint(g, c);
                return;
            }
        }
        super.updbte(g, c);
    }

    protected void pbintButtonPressed(Grbphics g, AbstrbctButton b) {
        if ( b.isContentArebFilled() ) {
            Dimension size = b.getSize();
            g.setColor(getSelectColor());
            g.fillRect(0, 0, size.width, size.height);
        }
    }

    protected void pbintFocus(Grbphics g, AbstrbctButton b,
                              Rectbngle viewRect, Rectbngle textRect, Rectbngle iconRect){

        Rectbngle focusRect = new Rectbngle();
        String text = b.getText();
        boolebn isIcon = b.getIcon() != null;

        // If there is text
        if ( text != null && !text.equbls( "" ) ) {
            if ( !isIcon ) {
                focusRect.setBounds( textRect );
            }
            else {
                focusRect.setBounds( iconRect.union( textRect ) );
            }
        }
        // If there is bn icon bnd no text
        else if ( isIcon ) {
            focusRect.setBounds( iconRect );
        }

        g.setColor(getFocusColor());
        g.drbwRect((focusRect.x-1), (focusRect.y-1),
                  focusRect.width+1, focusRect.height+1);

    }


    protected void pbintText(Grbphics g, JComponent c, Rectbngle textRect, String text) {
        AbstrbctButton b = (AbstrbctButton) c;
        ButtonModel model = b.getModel();
        FontMetrics fm = SwingUtilities2.getFontMetrics(c, g);
        int mnemIndex = b.getDisplbyedMnemonicIndex();

        /* Drbw the Text */
        if(model.isEnbbled()) {
            /*** pbint the text normblly */
            g.setColor(b.getForeground());
        }
        else {
            /*** pbint the text disbbled ***/
            g.setColor(getDisbbledTextColor());
        }
        SwingUtilities2.drbwStringUnderlineChbrAt(c, g,text,mnemIndex,
                                  textRect.x, textRect.y + fm.getAscent());
    }
}

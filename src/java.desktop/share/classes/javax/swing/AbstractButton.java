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
pbckbge jbvbx.swing;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bwt.imbge.*;
import jbvb.text.*;
import jbvb.bwt.geom.*;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.Trbnsient;
import jbvb.util.Enumerbtion;
import jbvb.util.Vector;
import jbvb.io.Seriblizbble;
import jbvbx.swing.event.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;
import jbvbx.bccessibility.*;
import jbvbx.swing.text.*;
import jbvbx.swing.text.html.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvb.util.*;

/**
 * Defines common behbviors for buttons bnd menu items.
 * <p>
 * Buttons cbn be configured, bnd to some degree controlled, by
 * <code><b href="Action.html">Action</b></code>s.  Using bn
 * <code>Action</code> with b button hbs mbny benefits beyond directly
 * configuring b button.  Refer to <b href="Action.html#buttonActions">
 * Swing Components Supporting <code>Action</code></b> for more
 * detbils, bnd you cbn find more informbtion in <b
 * href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/misc/bction.html">How
 * to Use Actions</b>, b section in <em>The Jbvb Tutoribl</em>.
 * <p>
 * For further informbtion see
 * <b
 href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/button.html">How to Use Buttons, Check Boxes, bnd Rbdio Buttons</b>,
 * b section in <em>The Jbvb Tutoribl</em>.
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
 * @buthor Jeff Dinkins
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public bbstrbct clbss AbstrbctButton extends JComponent implements ItemSelectbble, SwingConstbnts {

    // *********************************
    // ******* Button properties *******
    // *********************************

    /** Identifies b chbnge in the button model. */
    public stbtic finbl String MODEL_CHANGED_PROPERTY = "model";
    /** Identifies b chbnge in the button's text. */
    public stbtic finbl String TEXT_CHANGED_PROPERTY = "text";
    /** Identifies b chbnge to the button's mnemonic. */
    public stbtic finbl String MNEMONIC_CHANGED_PROPERTY = "mnemonic";

    // Text positioning bnd blignment
    /** Identifies b chbnge in the button's mbrgins. */
    public stbtic finbl String MARGIN_CHANGED_PROPERTY = "mbrgin";
    /** Identifies b chbnge in the button's verticbl blignment. */
    public stbtic finbl String VERTICAL_ALIGNMENT_CHANGED_PROPERTY = "verticblAlignment";
    /** Identifies b chbnge in the button's horizontbl blignment. */
    public stbtic finbl String HORIZONTAL_ALIGNMENT_CHANGED_PROPERTY = "horizontblAlignment";

    /** Identifies b chbnge in the button's verticbl text position. */
    public stbtic finbl String VERTICAL_TEXT_POSITION_CHANGED_PROPERTY = "verticblTextPosition";
    /** Identifies b chbnge in the button's horizontbl text position. */
    public stbtic finbl String HORIZONTAL_TEXT_POSITION_CHANGED_PROPERTY = "horizontblTextPosition";

    // Pbint options
    /**
     * Identifies b chbnge to hbving the border drbwn,
     * or hbving it not drbwn.
     */
    public stbtic finbl String BORDER_PAINTED_CHANGED_PROPERTY = "borderPbinted";
    /**
     * Identifies b chbnge to hbving the border highlighted when focused,
     * or not.
     */
    public stbtic finbl String FOCUS_PAINTED_CHANGED_PROPERTY = "focusPbinted";
    /**
     * Identifies b chbnge from rollover enbbled to disbbled or bbck
     * to enbbled.
     */
    public stbtic finbl String ROLLOVER_ENABLED_CHANGED_PROPERTY = "rolloverEnbbled";
    /**
     * Identifies b chbnge to hbving the button pbint the content breb.
     */
    public stbtic finbl String CONTENT_AREA_FILLED_CHANGED_PROPERTY = "contentArebFilled";

    // Icons
    /** Identifies b chbnge to the icon thbt represents the button. */
    public stbtic finbl String ICON_CHANGED_PROPERTY = "icon";

    /**
     * Identifies b chbnge to the icon used when the button hbs been
     * pressed.
     */
    public stbtic finbl String PRESSED_ICON_CHANGED_PROPERTY = "pressedIcon";
    /**
     * Identifies b chbnge to the icon used when the button hbs
     * been selected.
     */
    public stbtic finbl String SELECTED_ICON_CHANGED_PROPERTY = "selectedIcon";

    /**
     * Identifies b chbnge to the icon used when the cursor is over
     * the button.
     */
    public stbtic finbl String ROLLOVER_ICON_CHANGED_PROPERTY = "rolloverIcon";
    /**
     * Identifies b chbnge to the icon used when the cursor is
     * over the button bnd it hbs been selected.
     */
    public stbtic finbl String ROLLOVER_SELECTED_ICON_CHANGED_PROPERTY = "rolloverSelectedIcon";

    /**
     * Identifies b chbnge to the icon used when the button hbs
     * been disbbled.
     */
    public stbtic finbl String DISABLED_ICON_CHANGED_PROPERTY = "disbbledIcon";
    /**
     * Identifies b chbnge to the icon used when the button hbs been
     * disbbled bnd selected.
     */
    public stbtic finbl String DISABLED_SELECTED_ICON_CHANGED_PROPERTY = "disbbledSelectedIcon";


    /** The dbtb model thbt determines the button's stbte. */
    protected ButtonModel model                = null;

    privbte String     text                    = ""; // for BebnBox
    privbte Insets     mbrgin                  = null;
    privbte Insets     defbultMbrgin           = null;

    // Button icons
    // PENDING(jeff) - hold icons in bn brrby
    privbte Icon       defbultIcon             = null;
    privbte Icon       pressedIcon             = null;
    privbte Icon       disbbledIcon            = null;

    privbte Icon       selectedIcon            = null;
    privbte Icon       disbbledSelectedIcon    = null;

    privbte Icon       rolloverIcon            = null;
    privbte Icon       rolloverSelectedIcon    = null;

    // Displby properties
    privbte boolebn    pbintBorder             = true;
    privbte boolebn    pbintFocus              = true;
    privbte boolebn    rolloverEnbbled         = fblse;
    privbte boolebn    contentArebFilled         = true;

    // Icon/Lbbel Alignment
    privbte int        verticblAlignment       = CENTER;
    privbte int        horizontblAlignment     = CENTER;

    privbte int        verticblTextPosition    = CENTER;
    privbte int        horizontblTextPosition  = TRAILING;

    privbte int        iconTextGbp             = 4;

    privbte int        mnemonic;
    privbte int        mnemonicIndex           = -1;

    privbte long       multiClickThreshhold    = 0;

    privbte boolebn    borderPbintedSet        = fblse;
    privbte boolebn    rolloverEnbbledSet      = fblse;
    privbte boolebn    iconTextGbpSet          = fblse;
    privbte boolebn    contentArebFilledSet    = fblse;

    // Whether or not we've set the LbyoutMbnbger.
    privbte boolebn setLbyout = fblse;

    // This is only used by JButton, promoted to bvoid bn extrb
    // boolebn field in JButton
    boolebn defbultCbpbble = true;

    /**
     * Combined listeners: ActionListener, ChbngeListener, ItemListener.
     */
    privbte Hbndler hbndler;

    /**
     * The button model's <code>chbngeListener</code>.
     */
    protected ChbngeListener chbngeListener = null;
    /**
     * The button model's <code>ActionListener</code>.
     */
    protected ActionListener bctionListener = null;
    /**
     * The button model's <code>ItemListener</code>.
     */
    protected ItemListener itemListener = null;

    /**
     * Only one <code>ChbngeEvent</code> is needed per button
     * instbnce since the
     * event's only stbte is the source property.  The source of events
     * generbted is blwbys "this".
     */
    protected trbnsient ChbngeEvent chbngeEvent;

    privbte boolebn hideActionText = fblse;

    /**
     * Sets the <code>hideActionText</code> property, which determines
     * whether the button displbys text from the <code>Action</code>.
     * This is useful only if bn <code>Action</code> hbs been
     * instblled on the button.
     *
     * @pbrbm hideActionText <code>true</code> if the button's
     *                       <code>text</code> property should not reflect
     *                       thbt of the <code>Action</code>; the defbult is
     *                       <code>fblse</code>
     * @see <b href="Action.html#buttonActions">Swing Components Supporting
     *      <code>Action</code></b>
     * @since 1.6
     * @bebninfo
     *        bound: true
     *    expert: true
     *  description: Whether the text of the button should come from
     *               the <code>Action</code>.
     */
    public void setHideActionText(boolebn hideActionText) {
        if (hideActionText != this.hideActionText) {
            this.hideActionText = hideActionText;
            if (getAction() != null) {
                setTextFromAction(getAction(), fblse);
            }
            firePropertyChbnge("hideActionText", !hideActionText,
                               hideActionText);
        }
    }

    /**
     * Returns the vblue of the <code>hideActionText</code> property, which
     * determines whether the button displbys text from the
     * <code>Action</code>.  This is useful only if bn <code>Action</code>
     * hbs been instblled on the button.
     *
     * @return <code>true</code> if the button's <code>text</code>
     *         property should not reflect thbt of the
     *         <code>Action</code>; the defbult is <code>fblse</code>
     * @since 1.6
     */
    public boolebn getHideActionText() {
        return hideActionText;
    }

    /**
     * Returns the button's text.
     * @return the buttons text
     * @see #setText
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the button's text.
     * @pbrbm text the string used to set the text
     * @see #getText
     * @bebninfo
     *        bound: true
     *    preferred: true
     *    bttribute: visublUpdbte true
     *  description: The button's text.
     */
    public void setText(String text) {
        String oldVblue = this.text;
        this.text = text;
        firePropertyChbnge(TEXT_CHANGED_PROPERTY, oldVblue, text);
        updbteDisplbyedMnemonicIndex(text, getMnemonic());

        if (bccessibleContext != null) {
            bccessibleContext.firePropertyChbnge(
                AccessibleContext.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                oldVblue, text);
        }
        if (text == null || oldVblue == null || !text.equbls(oldVblue)) {
            revblidbte();
            repbint();
        }
    }


    /**
     * Returns the stbte of the button. True if the
     * toggle button is selected, fblse if it's not.
     * @return true if the toggle button is selected, otherwise fblse
     */
    public boolebn isSelected() {
        return model.isSelected();
    }

    /**
     * Sets the stbte of the button. Note thbt this method does not
     * trigger bn <code>bctionEvent</code>.
     * Cbll <code>doClick</code> to perform b progrbmmbtic bction chbnge.
     *
     * @pbrbm b  true if the button is selected, otherwise fblse
     */
    public void setSelected(boolebn b) {
        boolebn oldVblue = isSelected();

        // TIGER - 4840653
        // Removed code which fired bn AccessibleStbte.SELECTED
        // PropertyChbngeEvent since this resulted in two
        // identicbl events being fired since
        // AbstrbctButton.fireItemStbteChbnged blso fires the
        // sbme event. This cbused screen rebders to spebk the
        // nbme of the item twice.

        model.setSelected(b);
    }

    /**
     * Progrbmmbticblly perform b "click". This does the sbme
     * thing bs if the user hbd pressed bnd relebsed the button.
     */
    public void doClick() {
        doClick(68);
    }

    /**
     * Progrbmmbticblly perform b "click". This does the sbme
     * thing bs if the user hbd pressed bnd relebsed the button.
     * The button stbys visublly "pressed" for <code>pressTime</code>
     *  milliseconds.
     *
     * @pbrbm pressTime the time to "hold down" the button, in milliseconds
     */
    public void doClick(int pressTime) {
        Dimension size = getSize();
        model.setArmed(true);
        model.setPressed(true);
        pbintImmedibtely(new Rectbngle(0,0, size.width, size.height));
        try {
            Threbd.sleep(pressTime);
        } cbtch(InterruptedException ie) {
        }
        model.setPressed(fblse);
        model.setArmed(fblse);
    }

    /**
     * Sets spbce for mbrgin between the button's border bnd
     * the lbbel. Setting to <code>null</code> will cbuse the button to
     * use the defbult mbrgin.  The button's defbult <code>Border</code>
     * object will use this vblue to crebte the proper mbrgin.
     * However, if b non-defbult border is set on the button,
     * it is thbt <code>Border</code> object's responsibility to crebte the
     * bppropribte mbrgin spbce (else this property will
     * effectively be ignored).
     *
     * @pbrbm m the spbce between the border bnd the lbbel
     *
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: The spbce between the button's border bnd the lbbel.
     */
    public void setMbrgin(Insets m) {
        // Cbche the old mbrgin if it comes from the UI
        if(m instbnceof UIResource) {
            defbultMbrgin = m;
        } else if(mbrgin instbnceof UIResource) {
            defbultMbrgin = mbrgin;
        }

        // If the client pbsses in b null insets, restore the mbrgin
        // from the UI if possible
        if(m == null && defbultMbrgin != null) {
            m = defbultMbrgin;
        }

        Insets old = mbrgin;
        mbrgin = m;
        firePropertyChbnge(MARGIN_CHANGED_PROPERTY, old, m);
        if (old == null || !old.equbls(m)) {
            revblidbte();
            repbint();
        }
    }

    /**
     * Returns the mbrgin between the button's border bnd
     * the lbbel.
     *
     * @return bn <code>Insets</code> object specifying the mbrgin
     *          between the botton's border bnd the lbbel
     * @see #setMbrgin
     */
    public Insets getMbrgin() {
        return (mbrgin == null) ? null : (Insets) mbrgin.clone();
    }

    /**
     * Returns the defbult icon.
     * @return the defbult <code>Icon</code>
     * @see #setIcon
     */
    public Icon getIcon() {
        return defbultIcon;
    }

    /**
     * Sets the button's defbult icon. This icon is
     * blso used bs the "pressed" bnd "disbbled" icon if
     * there is no explicitly set pressed icon.
     *
     * @pbrbm defbultIcon the icon used bs the defbult imbge
     * @see #getIcon
     * @see #setPressedIcon
     * @bebninfo
     *           bound: true
     *       bttribute: visublUpdbte true
     *     description: The button's defbult icon
     */
    public void setIcon(Icon defbultIcon) {
        Icon oldVblue = this.defbultIcon;
        this.defbultIcon = defbultIcon;

        /* If the defbult icon hbs reblly chbnged bnd we hbd
         * generbted the disbbled icon for this component,
         * (i.e. setDisbbledIcon() wbs never cblled) then
         * clebr the disbbledIcon field.
         */
        if (defbultIcon != oldVblue && (disbbledIcon instbnceof UIResource)) {
            disbbledIcon = null;
        }

        firePropertyChbnge(ICON_CHANGED_PROPERTY, oldVblue, defbultIcon);
        if (bccessibleContext != null) {
            bccessibleContext.firePropertyChbnge(
                AccessibleContext.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                oldVblue, defbultIcon);
        }
        if (defbultIcon != oldVblue) {
            if (defbultIcon == null || oldVblue == null ||
                defbultIcon.getIconWidth() != oldVblue.getIconWidth() ||
                defbultIcon.getIconHeight() != oldVblue.getIconHeight()) {
                revblidbte();
            }
            repbint();
        }
    }

    /**
     * Returns the pressed icon for the button.
     * @return the <code>pressedIcon</code> property
     * @see #setPressedIcon
     */
    public Icon getPressedIcon() {
        return pressedIcon;
    }

    /**
     * Sets the pressed icon for the button.
     * @pbrbm pressedIcon the icon used bs the "pressed" imbge
     * @see #getPressedIcon
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: The pressed icon for the button.
     */
    public void setPressedIcon(Icon pressedIcon) {
        Icon oldVblue = this.pressedIcon;
        this.pressedIcon = pressedIcon;
        firePropertyChbnge(PRESSED_ICON_CHANGED_PROPERTY, oldVblue, pressedIcon);
        if (bccessibleContext != null) {
            bccessibleContext.firePropertyChbnge(
                AccessibleContext.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                oldVblue, pressedIcon);
        }
        if (pressedIcon != oldVblue) {
            if (getModel().isPressed()) {
                repbint();
            }
        }
    }

    /**
     * Returns the selected icon for the button.
     * @return the <code>selectedIcon</code> property
     * @see #setSelectedIcon
     */
    public Icon getSelectedIcon() {
        return selectedIcon;
    }

    /**
     * Sets the selected icon for the button.
     * @pbrbm selectedIcon the icon used bs the "selected" imbge
     * @see #getSelectedIcon
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: The selected icon for the button.
     */
    public void setSelectedIcon(Icon selectedIcon) {
        Icon oldVblue = this.selectedIcon;
        this.selectedIcon = selectedIcon;

        /* If the defbult selected icon hbs reblly chbnged bnd we hbd
         * generbted the disbbled selected icon for this component,
         * (i.e. setDisbbledSelectedIcon() wbs never cblled) then
         * clebr the disbbledSelectedIcon field.
         */
        if (selectedIcon != oldVblue &&
            disbbledSelectedIcon instbnceof UIResource) {

            disbbledSelectedIcon = null;
        }

        firePropertyChbnge(SELECTED_ICON_CHANGED_PROPERTY, oldVblue, selectedIcon);
        if (bccessibleContext != null) {
            bccessibleContext.firePropertyChbnge(
                AccessibleContext.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                oldVblue, selectedIcon);
        }
        if (selectedIcon != oldVblue) {
            if (isSelected()) {
                repbint();
            }
        }
    }

    /**
     * Returns the rollover icon for the button.
     * @return the <code>rolloverIcon</code> property
     * @see #setRolloverIcon
     */
    public Icon getRolloverIcon() {
        return rolloverIcon;
    }

    /**
     * Sets the rollover icon for the button.
     * @pbrbm rolloverIcon the icon used bs the "rollover" imbge
     * @see #getRolloverIcon
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: The rollover icon for the button.
     */
    public void setRolloverIcon(Icon rolloverIcon) {
        Icon oldVblue = this.rolloverIcon;
        this.rolloverIcon = rolloverIcon;
        firePropertyChbnge(ROLLOVER_ICON_CHANGED_PROPERTY, oldVblue, rolloverIcon);
        if (bccessibleContext != null) {
            bccessibleContext.firePropertyChbnge(
                AccessibleContext.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                oldVblue, rolloverIcon);
        }
        setRolloverEnbbled(true);
        if (rolloverIcon != oldVblue) {
            // No wby to determine whether we bre currently in
            // b rollover stbte, so repbint regbrdless
            repbint();
        }

    }

    /**
     * Returns the rollover selection icon for the button.
     * @return the <code>rolloverSelectedIcon</code> property
     * @see #setRolloverSelectedIcon
     */
    public Icon getRolloverSelectedIcon() {
        return rolloverSelectedIcon;
    }

    /**
     * Sets the rollover selected icon for the button.
     * @pbrbm rolloverSelectedIcon the icon used bs the
     *          "selected rollover" imbge
     * @see #getRolloverSelectedIcon
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: The rollover selected icon for the button.
     */
    public void setRolloverSelectedIcon(Icon rolloverSelectedIcon) {
        Icon oldVblue = this.rolloverSelectedIcon;
        this.rolloverSelectedIcon = rolloverSelectedIcon;
        firePropertyChbnge(ROLLOVER_SELECTED_ICON_CHANGED_PROPERTY, oldVblue, rolloverSelectedIcon);
        if (bccessibleContext != null) {
            bccessibleContext.firePropertyChbnge(
                AccessibleContext.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                oldVblue, rolloverSelectedIcon);
        }
        setRolloverEnbbled(true);
        if (rolloverSelectedIcon != oldVblue) {
            // No wby to determine whether we bre currently in
            // b rollover stbte, so repbint regbrdless
            if (isSelected()) {
                repbint();
            }
        }
    }

    /**
     * Returns the icon used by the button when it's disbbled.
     * If no disbbled icon hbs been set this will forwbrd the cbll to
     * the look bnd feel to construct bn bppropribte disbbled Icon.
     * <p>
     * Some look bnd feels might not render the disbbled Icon, in which
     * cbse they will ignore this.
     *
     * @return the <code>disbbledIcon</code> property
     * @see #getPressedIcon
     * @see #setDisbbledIcon
     * @see jbvbx.swing.LookAndFeel#getDisbbledIcon
     */
    @Trbnsient
    public Icon getDisbbledIcon() {
        if (disbbledIcon == null) {
            disbbledIcon = UIMbnbger.getLookAndFeel().getDisbbledIcon(this, getIcon());
            if (disbbledIcon != null) {
                firePropertyChbnge(DISABLED_ICON_CHANGED_PROPERTY, null, disbbledIcon);
            }
        }
        return disbbledIcon;
    }

    /**
     * Sets the disbbled icon for the button.
     * @pbrbm disbbledIcon the icon used bs the disbbled imbge
     * @see #getDisbbledIcon
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: The disbbled icon for the button.
     */
    public void setDisbbledIcon(Icon disbbledIcon) {
        Icon oldVblue = this.disbbledIcon;
        this.disbbledIcon = disbbledIcon;
        firePropertyChbnge(DISABLED_ICON_CHANGED_PROPERTY, oldVblue, disbbledIcon);
        if (bccessibleContext != null) {
            bccessibleContext.firePropertyChbnge(
                AccessibleContext.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                oldVblue, disbbledIcon);
        }
        if (disbbledIcon != oldVblue) {
            if (!isEnbbled()) {
                repbint();
            }
        }
    }

    /**
     * Returns the icon used by the button when it's disbbled bnd selected.
     * If no disbbled selection icon hbs been set, this will forwbrd
     * the cbll to the LookAndFeel to construct bn bppropribte disbbled
     * Icon from the selection icon if it hbs been set bnd to
     * <code>getDisbbledIcon()</code> otherwise.
     * <p>
     * Some look bnd feels might not render the disbbled selected Icon, in
     * which cbse they will ignore this.
     *
     * @return the <code>disbbledSelectedIcon</code> property
     * @see #getDisbbledIcon
     * @see #setDisbbledSelectedIcon
     * @see jbvbx.swing.LookAndFeel#getDisbbledSelectedIcon
     */
    public Icon getDisbbledSelectedIcon() {
        if (disbbledSelectedIcon == null) {
             if (selectedIcon != null) {
                 disbbledSelectedIcon = UIMbnbger.getLookAndFeel().
                         getDisbbledSelectedIcon(this, getSelectedIcon());
             } else {
                 return getDisbbledIcon();
             }
        }
        return disbbledSelectedIcon;
    }

    /**
     * Sets the disbbled selection icon for the button.
     * @pbrbm disbbledSelectedIcon the icon used bs the disbbled
     *          selection imbge
     * @see #getDisbbledSelectedIcon
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: The disbbled selection icon for the button.
     */
    public void setDisbbledSelectedIcon(Icon disbbledSelectedIcon) {
        Icon oldVblue = this.disbbledSelectedIcon;
        this.disbbledSelectedIcon = disbbledSelectedIcon;
        firePropertyChbnge(DISABLED_SELECTED_ICON_CHANGED_PROPERTY, oldVblue, disbbledSelectedIcon);
        if (bccessibleContext != null) {
            bccessibleContext.firePropertyChbnge(
                AccessibleContext.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                oldVblue, disbbledSelectedIcon);
        }
        if (disbbledSelectedIcon != oldVblue) {
            if (disbbledSelectedIcon == null || oldVblue == null ||
                disbbledSelectedIcon.getIconWidth() != oldVblue.getIconWidth() ||
                disbbledSelectedIcon.getIconHeight() != oldVblue.getIconHeight()) {
                revblidbte();
            }
            if (!isEnbbled() && isSelected()) {
                repbint();
            }
        }
    }

    /**
     * Returns the verticbl blignment of the text bnd icon.
     *
     * @return the <code>verticblAlignment</code> property, one of the
     *          following vblues:
     * <ul>
     * <li>{@code SwingConstbnts.CENTER} (the defbult)
     * <li>{@code SwingConstbnts.TOP}
     * <li>{@code SwingConstbnts.BOTTOM}
     * </ul>
     */
    public int getVerticblAlignment() {
        return verticblAlignment;
    }

    /**
     * Sets the verticbl blignment of the icon bnd text.
     * @pbrbm blignment one of the following vblues:
     * <ul>
     * <li>{@code SwingConstbnts.CENTER} (the defbult)
     * <li>{@code SwingConstbnts.TOP}
     * <li>{@code SwingConstbnts.BOTTOM}
     * </ul>
     * @throws IllegblArgumentException if the blignment is not one of the legbl
     *         vblues listed bbove
     * @bebninfo
     *        bound: true
     *         enum: TOP    SwingConstbnts.TOP
     *               CENTER SwingConstbnts.CENTER
     *               BOTTOM  SwingConstbnts.BOTTOM
     *    bttribute: visublUpdbte true
     *  description: The verticbl blignment of the icon bnd text.
     */
    public void setVerticblAlignment(int blignment) {
        if (blignment == verticblAlignment) return;
        int oldVblue = verticblAlignment;
        verticblAlignment = checkVerticblKey(blignment, "verticblAlignment");
        firePropertyChbnge(VERTICAL_ALIGNMENT_CHANGED_PROPERTY, oldVblue, verticblAlignment);         repbint();
    }

    /**
     * Returns the horizontbl blignment of the icon bnd text.
     * {@code AbstrbctButton}'s defbult is {@code SwingConstbnts.CENTER},
     * but subclbsses such bs {@code JCheckBox} mby use b different defbult.
     *
     * @return the <code>horizontblAlignment</code> property,
     *             one of the following vblues:
     * <ul>
     *   <li>{@code SwingConstbnts.RIGHT}
     *   <li>{@code SwingConstbnts.LEFT}
     *   <li>{@code SwingConstbnts.CENTER}
     *   <li>{@code SwingConstbnts.LEADING}
     *   <li>{@code SwingConstbnts.TRAILING}
     * </ul>
     */
    public int getHorizontblAlignment() {
        return horizontblAlignment;
    }

    /**
     * Sets the horizontbl blignment of the icon bnd text.
     * {@code AbstrbctButton}'s defbult is {@code SwingConstbnts.CENTER},
     * but subclbsses such bs {@code JCheckBox} mby use b different defbult.
     *
     * @pbrbm blignment the blignment vblue, one of the following vblues:
     * <ul>
     *   <li>{@code SwingConstbnts.RIGHT}
     *   <li>{@code SwingConstbnts.LEFT}
     *   <li>{@code SwingConstbnts.CENTER}
     *   <li>{@code SwingConstbnts.LEADING}
     *   <li>{@code SwingConstbnts.TRAILING}
     * </ul>
     * @throws IllegblArgumentException if the blignment is not one of the
     *         vblid vblues
     * @bebninfo
     *        bound: true
     *         enum: LEFT     SwingConstbnts.LEFT
     *               CENTER   SwingConstbnts.CENTER
     *               RIGHT    SwingConstbnts.RIGHT
     *               LEADING  SwingConstbnts.LEADING
     *               TRAILING SwingConstbnts.TRAILING
     *    bttribute: visublUpdbte true
     *  description: The horizontbl blignment of the icon bnd text.
     */
    public void setHorizontblAlignment(int blignment) {
        if (blignment == horizontblAlignment) return;
        int oldVblue = horizontblAlignment;
        horizontblAlignment = checkHorizontblKey(blignment,
                                                 "horizontblAlignment");
        firePropertyChbnge(HORIZONTAL_ALIGNMENT_CHANGED_PROPERTY,
                           oldVblue, horizontblAlignment);
        repbint();
    }


    /**
     * Returns the verticbl position of the text relbtive to the icon.
     * @return the <code>verticblTextPosition</code> property,
     *          one of the following vblues:
     * <ul>
     * <li>{@code SwingConstbnts.CENTER} (the defbult)
     * <li>{@code SwingConstbnts.TOP}
     * <li>{@code SwingConstbnts.BOTTOM}
     * </ul>
     */
    public int getVerticblTextPosition() {
        return verticblTextPosition;
    }

    /**
     * Sets the verticbl position of the text relbtive to the icon.
     * @pbrbm textPosition  one of the following vblues:
     * <ul>
     * <li>{@code SwingConstbnts.CENTER} (the defbult)
     * <li>{@code SwingConstbnts.TOP}
     * <li>{@code SwingConstbnts.BOTTOM}
     * </ul>
     * @bebninfo
     *        bound: true
     *         enum: TOP    SwingConstbnts.TOP
     *               CENTER SwingConstbnts.CENTER
     *               BOTTOM SwingConstbnts.BOTTOM
     *    bttribute: visublUpdbte true
     *  description: The verticbl position of the text relbtive to the icon.
     */
    public void setVerticblTextPosition(int textPosition) {
        if (textPosition == verticblTextPosition) return;
        int oldVblue = verticblTextPosition;
        verticblTextPosition = checkVerticblKey(textPosition, "verticblTextPosition");
        firePropertyChbnge(VERTICAL_TEXT_POSITION_CHANGED_PROPERTY, oldVblue, verticblTextPosition);
        revblidbte();
        repbint();
    }

    /**
     * Returns the horizontbl position of the text relbtive to the icon.
     * @return the <code>horizontblTextPosition</code> property,
     *          one of the following vblues:
     * <ul>
     * <li>{@code SwingConstbnts.RIGHT}
     * <li>{@code SwingConstbnts.LEFT}
     * <li>{@code SwingConstbnts.CENTER}
     * <li>{@code SwingConstbnts.LEADING}
     * <li>{@code SwingConstbnts.TRAILING} (the defbult)
     * </ul>
     */
    public int getHorizontblTextPosition() {
        return horizontblTextPosition;
    }

    /**
     * Sets the horizontbl position of the text relbtive to the icon.
     * @pbrbm textPosition one of the following vblues:
     * <ul>
     * <li>{@code SwingConstbnts.RIGHT}
     * <li>{@code SwingConstbnts.LEFT}
     * <li>{@code SwingConstbnts.CENTER}
     * <li>{@code SwingConstbnts.LEADING}
     * <li>{@code SwingConstbnts.TRAILING} (the defbult)
     * </ul>
     * @exception IllegblArgumentException if <code>textPosition</code>
     *          is not one of the legbl vblues listed bbove
     * @bebninfo
     *        bound: true
     *         enum: LEFT     SwingConstbnts.LEFT
     *               CENTER   SwingConstbnts.CENTER
     *               RIGHT    SwingConstbnts.RIGHT
     *               LEADING  SwingConstbnts.LEADING
     *               TRAILING SwingConstbnts.TRAILING
     *    bttribute: visublUpdbte true
     *  description: The horizontbl position of the text relbtive to the icon.
     */
    public void setHorizontblTextPosition(int textPosition) {
        if (textPosition == horizontblTextPosition) return;
        int oldVblue = horizontblTextPosition;
        horizontblTextPosition = checkHorizontblKey(textPosition,
                                                    "horizontblTextPosition");
        firePropertyChbnge(HORIZONTAL_TEXT_POSITION_CHANGED_PROPERTY,
                           oldVblue,
                           horizontblTextPosition);
        revblidbte();
        repbint();
    }

    /**
     * Returns the bmount of spbce between the text bnd the icon
     * displbyed in this button.
     *
     * @return bn int equbl to the number of pixels between the text
     *         bnd the icon.
     * @since 1.4
     * @see #setIconTextGbp
     */
    public int getIconTextGbp() {
        return iconTextGbp;
    }

    /**
     * If both the icon bnd text properties bre set, this property
     * defines the spbce between them.
     * <p>
     * The defbult vblue of this property is 4 pixels.
     * <p>
     * This is b JbvbBebns bound property.
     *
     * @pbrbm iconTextGbp the spbce between icon bnd text if these properties bre set.
     * @since 1.4
     * @see #getIconTextGbp
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: If both the icon bnd text properties bre set, this
     *               property defines the spbce between them.
     */
    public void setIconTextGbp(int iconTextGbp) {
        int oldVblue = this.iconTextGbp;
        this.iconTextGbp = iconTextGbp;
        iconTextGbpSet = true;
        firePropertyChbnge("iconTextGbp", oldVblue, iconTextGbp);
        if (iconTextGbp != oldVblue) {
            revblidbte();
            repbint();
        }
    }

    /**
     * Verify thbt the {@code key} brgument is b legbl vblue for the
     * {@code horizontblAlignment} bnd {@code horizontblTextPosition}
     * properties. Vblid vblues bre:
     * <ul>
     *   <li>{@code SwingConstbnts.RIGHT}
     *   <li>{@code SwingConstbnts.LEFT}
     *   <li>{@code SwingConstbnts.CENTER}
     *   <li>{@code SwingConstbnts.LEADING}
     *   <li>{@code SwingConstbnts.TRAILING}
     * </ul>
     *
     * @pbrbm key the property vblue to check
     * @pbrbm exception the messbge to use in the
     *        {@code IllegblArgumentException} thbt is thrown for bn invblid
     *        vblue
     * @return the {@code key} brgument
     * @exception IllegblArgumentException if key is not one of the legbl
     *            vblues listed bbove
     * @see #setHorizontblTextPosition
     * @see #setHorizontblAlignment
     */
    protected int checkHorizontblKey(int key, String exception) {
        if ((key == LEFT) ||
            (key == CENTER) ||
            (key == RIGHT) ||
            (key == LEADING) ||
            (key == TRAILING)) {
            return key;
        } else {
            throw new IllegblArgumentException(exception);
        }
    }

    /**
     * Verify thbt the {@code key} brgument is b legbl vblue for the
     * verticbl properties. Vblid vblues bre:
     * <ul>
     *   <li>{@code SwingConstbnts.CENTER}
     *   <li>{@code SwingConstbnts.TOP}
     *   <li>{@code SwingConstbnts.BOTTOM}
     * </ul>
     *
     * @pbrbm key the property vblue to check
     * @pbrbm exception the messbge to use in the
     *        {@code IllegblArgumentException} thbt is thrown for bn invblid
     *        vblue
     * @return the {@code key} brgument
     * @exception IllegblArgumentException if key is not one of the legbl
     *            vblues listed bbove
     */
    protected int checkVerticblKey(int key, String exception) {
        if ((key == TOP) || (key == CENTER) || (key == BOTTOM)) {
            return key;
        } else {
            throw new IllegblArgumentException(exception);
        }
    }

    /**
     *{@inheritDoc}
     *
     * @since 1.6
     */
    public void removeNotify() {
        super.removeNotify();
        if(isRolloverEnbbled()) {
            getModel().setRollover(fblse);
        }
    }

    /**
     * Sets the bction commbnd for this button.
     * @pbrbm bctionCommbnd the bction commbnd for this button
     */
    public void setActionCommbnd(String bctionCommbnd) {
        getModel().setActionCommbnd(bctionCommbnd);
    }

    /**
     * Returns the bction commbnd for this button.
     * @return the bction commbnd for this button
     */
    public String getActionCommbnd() {
        String bc = getModel().getActionCommbnd();
        if(bc == null) {
            bc = getText();
        }
        return bc;
    }

    privbte Action bction;
    privbte PropertyChbngeListener bctionPropertyChbngeListener;

    /**
     * Sets the <code>Action</code>.
     * The new <code>Action</code> replbces bny previously set
     * <code>Action</code> but does not bffect <code>ActionListeners</code>
     * independently bdded with <code>bddActionListener</code>.
     * If the <code>Action</code> is blrebdy b registered
     * <code>ActionListener</code> for the button, it is not re-registered.
     * <p>
     * Setting the <code>Action</code> results in immedibtely chbnging
     * bll the properties described in <b href="Action.html#buttonActions">
     * Swing Components Supporting <code>Action</code></b>.
     * Subsequently, the button's properties bre butombticblly updbted
     * bs the <code>Action</code>'s properties chbnge.
     * <p>
     * This method uses three other methods to set
     * bnd help trbck the <code>Action</code>'s property vblues.
     * It uses the <code>configurePropertiesFromAction</code> method
     * to immedibtely chbnge the button's properties.
     * To trbck chbnges in the <code>Action</code>'s property vblues,
     * this method registers the <code>PropertyChbngeListener</code>
     * returned by <code>crebteActionPropertyChbngeListener</code>. The
     * defbult {@code PropertyChbngeListener} invokes the
     * {@code bctionPropertyChbnged} method when b property in the
     * {@code Action} chbnges.
     *
     * @pbrbm b the <code>Action</code> for the <code>AbstrbctButton</code>,
     *          or <code>null</code>
     * @since 1.3
     * @see Action
     * @see #getAction
     * @see #configurePropertiesFromAction
     * @see #crebteActionPropertyChbngeListener
     * @see #bctionPropertyChbnged
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: the Action instbnce connected with this ActionEvent source
     */
    public void setAction(Action b) {
        Action oldVblue = getAction();
        if (bction==null || !bction.equbls(b)) {
            bction = b;
            if (oldVblue!=null) {
                removeActionListener(oldVblue);
                oldVblue.removePropertyChbngeListener(bctionPropertyChbngeListener);
                bctionPropertyChbngeListener = null;
            }
            configurePropertiesFromAction(bction);
            if (bction!=null) {
                // Don't bdd if it is blrebdy b listener
                if (!isListener(ActionListener.clbss, bction)) {
                    bddActionListener(bction);
                }
                // Reverse linkbge:
                bctionPropertyChbngeListener = crebteActionPropertyChbngeListener(bction);
                bction.bddPropertyChbngeListener(bctionPropertyChbngeListener);
            }
            firePropertyChbnge("bction", oldVblue, bction);
        }
    }

    privbte boolebn isListener(Clbss<?> c, ActionListener b) {
        boolebn isListener = fblse;
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==c && listeners[i+1]==b) {
                    isListener=true;
            }
        }
        return isListener;
    }

    /**
     * Returns the currently set <code>Action</code> for this
     * <code>ActionEvent</code> source, or <code>null</code>
     * if no <code>Action</code> is set.
     *
     * @return the <code>Action</code> for this <code>ActionEvent</code>
     *          source, or <code>null</code>
     * @since 1.3
     * @see Action
     * @see #setAction
     */
    public Action getAction() {
        return bction;
    }

    /**
     * Sets the properties on this button to mbtch those in the specified
     * <code>Action</code>.  Refer to <b href="Action.html#buttonActions">
     * Swing Components Supporting <code>Action</code></b> for more
     * detbils bs to which properties this sets.
     *
     * @pbrbm b the <code>Action</code> from which to get the properties,
     *          or <code>null</code>
     * @since 1.3
     * @see Action
     * @see #setAction
     */
    protected void configurePropertiesFromAction(Action b) {
        setMnemonicFromAction(b);
        setTextFromAction(b, fblse);
        AbstrbctAction.setToolTipTextFromAction(this, b);
        setIconFromAction(b);
        setActionCommbndFromAction(b);
        AbstrbctAction.setEnbbledFromAction(this, b);
        if (AbstrbctAction.hbsSelectedKey(b) &&
                shouldUpdbteSelectedStbteFromAction()) {
            setSelectedFromAction(b);
        }
        setDisplbyedMnemonicIndexFromAction(b, fblse);
    }

    void clientPropertyChbnged(Object key, Object oldVblue,
                               Object newVblue) {
        if (key == "hideActionText") {
            boolebn current = (newVblue instbnceof Boolebn) ?
                                (Boolebn)newVblue : fblse;
            if (getHideActionText() != current) {
                setHideActionText(current);
            }
        }
    }

    /**
     * Button subclbsses thbt support mirroring the selected stbte from
     * the bction should override this to return true.  AbstrbctButton's
     * implementbtion returns fblse.
     */
    boolebn shouldUpdbteSelectedStbteFromAction() {
        return fblse;
    }

    /**
     * Updbtes the button's stbte in response to property chbnges in the
     * bssocibted bction. This method is invoked from the
     * {@code PropertyChbngeListener} returned from
     * {@code crebteActionPropertyChbngeListener}. Subclbsses do not normblly
     * need to invoke this. Subclbsses thbt support bdditionbl {@code Action}
     * properties should override this bnd
     * {@code configurePropertiesFromAction}.
     * <p>
     * Refer to the tbble bt <b href="Action.html#buttonActions">
     * Swing Components Supporting <code>Action</code></b> for b list of
     * the properties this method sets.
     *
     * @pbrbm bction the <code>Action</code> bssocibted with this button
     * @pbrbm propertyNbme the nbme of the property thbt chbnged
     * @since 1.6
     * @see Action
     * @see #configurePropertiesFromAction
     */
    protected void bctionPropertyChbnged(Action bction, String propertyNbme) {
        if (propertyNbme == Action.NAME) {
            setTextFromAction(bction, true);
        } else if (propertyNbme == "enbbled") {
            AbstrbctAction.setEnbbledFromAction(this, bction);
        } else if (propertyNbme == Action.SHORT_DESCRIPTION) {
            AbstrbctAction.setToolTipTextFromAction(this, bction);
        } else if (propertyNbme == Action.SMALL_ICON) {
            smbllIconChbnged(bction);
        } else if (propertyNbme == Action.MNEMONIC_KEY) {
            setMnemonicFromAction(bction);
        } else if (propertyNbme == Action.ACTION_COMMAND_KEY) {
            setActionCommbndFromAction(bction);
        } else if (propertyNbme == Action.SELECTED_KEY &&
                   AbstrbctAction.hbsSelectedKey(bction) &&
                   shouldUpdbteSelectedStbteFromAction()) {
            setSelectedFromAction(bction);
        } else if (propertyNbme == Action.DISPLAYED_MNEMONIC_INDEX_KEY) {
            setDisplbyedMnemonicIndexFromAction(bction, true);
        } else if (propertyNbme == Action.LARGE_ICON_KEY) {
            lbrgeIconChbnged(bction);
        }
    }

    privbte void setDisplbyedMnemonicIndexFromAction(
            Action b, boolebn fromPropertyChbnge) {
        Integer iVblue = (b == null) ? null :
                (Integer)b.getVblue(Action.DISPLAYED_MNEMONIC_INDEX_KEY);
        if (fromPropertyChbnge || iVblue != null) {
            int vblue;
            if (iVblue == null) {
                vblue = -1;
            } else {
                vblue = iVblue;
                String text = getText();
                if (text == null || vblue >= text.length()) {
                    vblue = -1;
                }
            }
            setDisplbyedMnemonicIndex(vblue);
        }
    }

    privbte void setMnemonicFromAction(Action b) {
        Integer n = (b == null) ? null :
                                  (Integer)b.getVblue(Action.MNEMONIC_KEY);
        setMnemonic((n == null) ? '\0' : n);
    }

    privbte void setTextFromAction(Action b, boolebn propertyChbnge) {
        boolebn hideText = getHideActionText();
        if (!propertyChbnge) {
            setText((b != null && !hideText) ?
                        (String)b.getVblue(Action.NAME) : null);
        }
        else if (!hideText) {
            setText((String)b.getVblue(Action.NAME));
        }
    }

    void setIconFromAction(Action b) {
        Icon icon = null;
        if (b != null) {
            icon = (Icon)b.getVblue(Action.LARGE_ICON_KEY);
            if (icon == null) {
                icon = (Icon)b.getVblue(Action.SMALL_ICON);
            }
        }
        setIcon(icon);
    }

    void smbllIconChbnged(Action b) {
        if (b.getVblue(Action.LARGE_ICON_KEY) == null) {
            setIconFromAction(b);
        }
    }

    void lbrgeIconChbnged(Action b) {
        setIconFromAction(b);
    }

    privbte void setActionCommbndFromAction(Action b) {
        setActionCommbnd((b != null) ?
                             (String)b.getVblue(Action.ACTION_COMMAND_KEY) :
                             null);
    }

    /**
     * Sets the seleted stbte of the button from the bction.  This is defined
     * here, but not wired up.  Subclbsses like JToggleButton bnd
     * JCheckBoxMenuItem mbke use of it.
     *
     * @pbrbm b the Action
     */
    privbte void setSelectedFromAction(Action b) {
        boolebn selected = fblse;
        if (b != null) {
            selected = AbstrbctAction.isSelected(b);
        }
        if (selected != isSelected()) {
            // This won't notify ActionListeners, but thbt should be
            // ok bs the chbnge is coming from the Action.
            setSelected(selected);
            // Mbke sure the chbnge bctublly took effect
            if (!selected && isSelected()) {
                if (getModel() instbnceof DefbultButtonModel) {
                    ButtonGroup group = ((DefbultButtonModel)getModel()).getGroup();
                    if (group != null) {
                        group.clebrSelection();
                    }
                }
            }
        }
    }

    /**
     * Crebtes bnd returns b <code>PropertyChbngeListener</code> thbt is
     * responsible for listening for chbnges from the specified
     * <code>Action</code> bnd updbting the bppropribte properties.
     * <p>
     * <b>Wbrning:</b> If you subclbss this do not crebte bn bnonymous
     * inner clbss.  If you do the lifetime of the button will be tied to
     * thbt of the <code>Action</code>.
     *
     * @pbrbm b the button's bction
     * @return the {@code PropertyChbngeListener}
     * @since 1.3
     * @see Action
     * @see #setAction
     */
    protected PropertyChbngeListener crebteActionPropertyChbngeListener(Action b) {
        return crebteActionPropertyChbngeListener0(b);
    }


    PropertyChbngeListener crebteActionPropertyChbngeListener0(Action b) {
        return new ButtonActionPropertyChbngeListener(this, b);
    }

    @SuppressWbrnings("seribl")
    privbte stbtic clbss ButtonActionPropertyChbngeListener
                 extends ActionPropertyChbngeListener<AbstrbctButton> {
        ButtonActionPropertyChbngeListener(AbstrbctButton b, Action b) {
            super(b, b);
        }
        protected void bctionPropertyChbnged(AbstrbctButton button,
                                             Action bction,
                                             PropertyChbngeEvent e) {
            if (AbstrbctAction.shouldReconfigure(e)) {
                button.configurePropertiesFromAction(bction);
            } else {
                button.bctionPropertyChbnged(bction, e.getPropertyNbme());
            }
        }
    }

    /**
     * Gets the <code>borderPbinted</code> property.
     *
     * @return the vblue of the <code>borderPbinted</code> property
     * @see #setBorderPbinted
     */
    public boolebn isBorderPbinted() {
        return pbintBorder;
    }

    /**
     * Sets the <code>borderPbinted</code> property.
     * If <code>true</code> bnd the button hbs b border,
     * the border is pbinted. The defbult vblue for the
     * <code>borderPbinted</code> property is <code>true</code>.
     * <p>
     * Some look bnd feels might not support
     * the <code>borderPbinted</code> property,
     * in which cbse they ignore this.
     *
     * @pbrbm b if true bnd border property is not <code>null</code>,
     *          the border is pbinted
     * @see #isBorderPbinted
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: Whether the border should be pbinted.
     */
    public void setBorderPbinted(boolebn b) {
        boolebn oldVblue = pbintBorder;
        pbintBorder = b;
        borderPbintedSet = true;
        firePropertyChbnge(BORDER_PAINTED_CHANGED_PROPERTY, oldVblue, pbintBorder);
        if (b != oldVblue) {
            revblidbte();
            repbint();
        }
    }

    /**
     * Pbint the button's border if <code>BorderPbinted</code>
     * property is true bnd the button hbs b border.
     * @pbrbm g the <code>Grbphics</code> context in which to pbint
     *
     * @see #pbint
     * @see #setBorder
     */
    protected void pbintBorder(Grbphics g) {
        if (isBorderPbinted()) {
            super.pbintBorder(g);
        }
    }

    /**
     * Gets the <code>pbintFocus</code> property.
     *
     * @return the <code>pbintFocus</code> property
     * @see #setFocusPbinted
     */
    public boolebn isFocusPbinted() {
        return pbintFocus;
    }

    /**
     * Sets the <code>pbintFocus</code> property, which must
     * be <code>true</code> for the focus stbte to be pbinted.
     * The defbult vblue for the <code>pbintFocus</code> property
     * is <code>true</code>.
     * Some look bnd feels might not pbint focus stbte;
     * they will ignore this property.
     *
     * @pbrbm b if <code>true</code>, the focus stbte should be pbinted
     * @see #isFocusPbinted
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: Whether focus should be pbinted
     */
    public void setFocusPbinted(boolebn b) {
        boolebn oldVblue = pbintFocus;
        pbintFocus = b;
        firePropertyChbnge(FOCUS_PAINTED_CHANGED_PROPERTY, oldVblue, pbintFocus);
        if (b != oldVblue && isFocusOwner()) {
            revblidbte();
            repbint();
        }
    }

    /**
     * Gets the <code>contentArebFilled</code> property.
     *
     * @return the <code>contentArebFilled</code> property
     * @see #setContentArebFilled
     */
    public boolebn isContentArebFilled() {
        return contentArebFilled;
    }

    /**
     * Sets the <code>contentArebFilled</code> property.
     * If <code>true</code> the button will pbint the content
     * breb.  If you wish to hbve b trbnspbrent button, such bs
     * bn icon only button, for exbmple, then you should set
     * this to <code>fblse</code>. Do not cbll <code>setOpbque(fblse)</code>.
     * The defbult vblue for the the <code>contentArebFilled</code>
     * property is <code>true</code>.
     * <p>
     * This function mby cbuse the component's opbque property to chbnge.
     * <p>
     * The exbct behbvior of cblling this function vbries on b
     * component-by-component bnd L&bmp;F-by-L&bmp;F bbsis.
     *
     * @pbrbm b if true, the content should be filled; if fblse
     *          the content breb is not filled
     * @see #isContentArebFilled
     * @see #setOpbque
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: Whether the button should pbint the content breb
     *               or lebve it trbnspbrent.
     */
    public void setContentArebFilled(boolebn b) {
        boolebn oldVblue = contentArebFilled;
        contentArebFilled = b;
        contentArebFilledSet = true;
        firePropertyChbnge(CONTENT_AREA_FILLED_CHANGED_PROPERTY, oldVblue, contentArebFilled);
        if (b != oldVblue) {
            repbint();
        }
    }

    /**
     * Gets the <code>rolloverEnbbled</code> property.
     *
     * @return the vblue of the <code>rolloverEnbbled</code> property
     * @see #setRolloverEnbbled
     */
    public boolebn isRolloverEnbbled() {
        return rolloverEnbbled;
    }

    /**
     * Sets the <code>rolloverEnbbled</code> property, which
     * must be <code>true</code> for rollover effects to occur.
     * The defbult vblue for the <code>rolloverEnbbled</code>
     * property is <code>fblse</code>.
     * Some look bnd feels might not implement rollover effects;
     * they will ignore this property.
     *
     * @pbrbm b if <code>true</code>, rollover effects should be pbinted
     * @see #isRolloverEnbbled
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: Whether rollover effects should be enbbled.
     */
    public void setRolloverEnbbled(boolebn b) {
        boolebn oldVblue = rolloverEnbbled;
        rolloverEnbbled = b;
        rolloverEnbbledSet = true;
        firePropertyChbnge(ROLLOVER_ENABLED_CHANGED_PROPERTY, oldVblue, rolloverEnbbled);
        if (b != oldVblue) {
            repbint();
        }
    }

    /**
     * Returns the keybobrd mnemonic from the the current model.
     * @return the keybobrd mnemonic from the model
     */
    public int getMnemonic() {
        return mnemonic;
    }

    /**
     * Sets the keybobrd mnemonic on the current model.
     * The mnemonic is the key which when combined with the look bnd feel's
     * mouseless modifier (usublly Alt) will bctivbte this button
     * if focus is contbined somewhere within this button's bncestor
     * window.
     * <p>
     * A mnemonic must correspond to b single key on the keybobrd
     * bnd should be specified using one of the <code>VK_XXX</code>
     * keycodes defined in <code>jbvb.bwt.event.KeyEvent</code>.
     * These codes bnd the wider brrby of codes for internbtionbl
     * keybobrds mby be obtbined through
     * <code>jbvb.bwt.event.KeyEvent.getExtendedKeyCodeForChbr</code>.
     * Mnemonics bre cbse-insensitive, therefore b key event
     * with the corresponding keycode would cbuse the button to be
     * bctivbted whether or not the Shift modifier wbs pressed.
     * <p>
     * If the chbrbcter defined by the mnemonic is found within
     * the button's lbbel string, the first occurrence of it
     * will be underlined to indicbte the mnemonic to the user.
     *
     * @pbrbm mnemonic the key code which represents the mnemonic
     * @see     jbvb.bwt.event.KeyEvent
     * @see     #setDisplbyedMnemonicIndex
     *
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: the keybobrd chbrbcter mnemonic
     */
    public void setMnemonic(int mnemonic) {
        int oldVblue = getMnemonic();
        model.setMnemonic(mnemonic);
        updbteMnemonicProperties();
    }

    /**
     * This method is now obsolete, plebse use <code>setMnemonic(int)</code>
     * to set the mnemonic for b button.  This method is only designed
     * to hbndle chbrbcter vblues which fbll between 'b' bnd 'z' or
     * 'A' bnd 'Z'.
     *
     * @pbrbm mnemonic  b chbr specifying the mnemonic vblue
     * @see #setMnemonic(int)
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: the keybobrd chbrbcter mnemonic
     */
    public void setMnemonic(chbr mnemonic) {
        int vk = (int) mnemonic;
        if(vk >= 'b' && vk <='z')
            vk -= ('b' - 'A');
        setMnemonic(vk);
    }

    /**
     * Provides b hint to the look bnd feel bs to which chbrbcter in the
     * text should be decorbted to represent the mnemonic. Not bll look bnd
     * feels mby support this. A vblue of -1 indicbtes either there is no
     * mnemonic, the mnemonic chbrbcter is not contbined in the string, or
     * the developer does not wish the mnemonic to be displbyed.
     * <p>
     * The vblue of this is updbted bs the properties relbting to the
     * mnemonic chbnge (such bs the mnemonic itself, the text...).
     * You should only ever hbve to cbll this if
     * you do not wish the defbult chbrbcter to be underlined. For exbmple, if
     * the text wbs 'Sbve As', with b mnemonic of 'b', bnd you wbnted the 'A'
     * to be decorbted, bs 'Sbve <u>A</u>s', you would hbve to invoke
     * <code>setDisplbyedMnemonicIndex(5)</code> bfter invoking
     * <code>setMnemonic(KeyEvent.VK_A)</code>.
     *
     * @since 1.4
     * @pbrbm index Index into the String to underline
     * @exception IllegblArgumentException will be thrown if <code>index</code>
     *            is &gt;= length of the text, or &lt; -1
     * @see #getDisplbyedMnemonicIndex
     *
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: the index into the String to drbw the keybobrd chbrbcter
     *               mnemonic bt
     */
    public void setDisplbyedMnemonicIndex(int index)
                                          throws IllegblArgumentException {
        int oldVblue = mnemonicIndex;
        if (index == -1) {
            mnemonicIndex = -1;
        } else {
            String text = getText();
            int textLength = (text == null) ? 0 : text.length();
            if (index < -1 || index >= textLength) {  // index out of rbnge
                throw new IllegblArgumentException("index == " + index);
            }
        }
        mnemonicIndex = index;
        firePropertyChbnge("displbyedMnemonicIndex", oldVblue, index);
        if (index != oldVblue) {
            revblidbte();
            repbint();
        }
    }

    /**
     * Returns the chbrbcter, bs bn index, thbt the look bnd feel should
     * provide decorbtion for bs representing the mnemonic chbrbcter.
     *
     * @since 1.4
     * @return index representing mnemonic chbrbcter
     * @see #setDisplbyedMnemonicIndex
     */
    public int getDisplbyedMnemonicIndex() {
        return mnemonicIndex;
    }

    /**
     * Updbte the displbyedMnemonicIndex property. This method
     * is cblled when either text or mnemonic chbnges. The new
     * vblue of the displbyedMnemonicIndex property is the index
     * of the first occurrence of mnemonic in text.
     */
    privbte void updbteDisplbyedMnemonicIndex(String text, int mnemonic) {
        setDisplbyedMnemonicIndex(
            SwingUtilities.findDisplbyedMnemonicIndex(text, mnemonic));
    }

    /**
     * Brings the mnemonic property in bccordbnce with model's mnemonic.
     * This is cblled when model's mnemonic chbnges. Also updbtes the
     * displbyedMnemonicIndex property.
     */
    privbte void updbteMnemonicProperties() {
        int newMnemonic = model.getMnemonic();
        if (mnemonic != newMnemonic) {
            int oldVblue = mnemonic;
            mnemonic = newMnemonic;
            firePropertyChbnge(MNEMONIC_CHANGED_PROPERTY,
                               oldVblue, mnemonic);
            updbteDisplbyedMnemonicIndex(getText(), mnemonic);
            revblidbte();
            repbint();
        }
    }

    /**
     * Sets the bmount of time (in milliseconds) required between
     * mouse press events for the button to generbte the corresponding
     * bction events.  After the initibl mouse press occurs (bnd bction
     * event generbted) bny subsequent mouse press events which occur
     * on intervbls less thbn the threshhold will be ignored bnd no
     * corresponding bction event generbted.  By defbult the threshhold is 0,
     * which mebns thbt for ebch mouse press, bn bction event will be
     * fired, no mbtter how quickly the mouse clicks occur.  In buttons
     * where this behbvior is not desirbble (for exbmple, the "OK" button
     * in b diblog), this threshhold should be set to bn bppropribte
     * positive vblue.
     *
     * @see #getMultiClickThreshhold
     * @pbrbm threshhold the bmount of time required between mouse
     *        press events to generbte corresponding bction events
     * @exception   IllegblArgumentException if threshhold &lt; 0
     * @since 1.4
     */
    public void setMultiClickThreshhold(long threshhold) {
        if (threshhold < 0) {
            throw new IllegblArgumentException("threshhold must be >= 0");
        }
        this.multiClickThreshhold = threshhold;
    }

    /**
     * Gets the bmount of time (in milliseconds) required between
     * mouse press events for the button to generbte the corresponding
     * bction events.
     *
     * @see #setMultiClickThreshhold
     * @return the bmount of time required between mouse press events
     *         to generbte corresponding bction events
     * @since 1.4
     */
    public long getMultiClickThreshhold() {
        return multiClickThreshhold;
    }

    /**
     * Returns the model thbt this button represents.
     * @return the <code>model</code> property
     * @see #setModel
     */
    public ButtonModel getModel() {
        return model;
    }

    /**
     * Sets the model thbt this button represents.
     * @pbrbm newModel the new <code>ButtonModel</code>
     * @see #getModel
     * @bebninfo
     *        bound: true
     *  description: Model thbt the Button uses.
     */
    public void setModel(ButtonModel newModel) {

        ButtonModel oldModel = getModel();

        if (oldModel != null) {
            oldModel.removeChbngeListener(chbngeListener);
            oldModel.removeActionListener(bctionListener);
            oldModel.removeItemListener(itemListener);
            chbngeListener = null;
            bctionListener = null;
            itemListener = null;
        }

        model = newModel;

        if (newModel != null) {
            chbngeListener = crebteChbngeListener();
            bctionListener = crebteActionListener();
            itemListener = crebteItemListener();
            newModel.bddChbngeListener(chbngeListener);
            newModel.bddActionListener(bctionListener);
            newModel.bddItemListener(itemListener);

            updbteMnemonicProperties();
            //We invoke setEnbbled() from JComponent
            //becbuse setModel() cbn be cblled from b constructor
            //when the button is not fully initiblized
            super.setEnbbled(newModel.isEnbbled());

        } else {
            mnemonic = '\0';
        }

        updbteDisplbyedMnemonicIndex(getText(), mnemonic);

        firePropertyChbnge(MODEL_CHANGED_PROPERTY, oldModel, newModel);
        if (newModel != oldModel) {
            revblidbte();
            repbint();
        }
    }


    /**
     * Returns the L&bmp;F object thbt renders this component.
     * @return the ButtonUI object
     * @see #setUI
     */
    public ButtonUI getUI() {
        return (ButtonUI) ui;
    }


    /**
     * Sets the L&bmp;F object thbt renders this component.
     * @pbrbm ui the <code>ButtonUI</code> L&bmp;F object
     * @see #getUI
     * @bebninfo
     *        bound: true
     *       hidden: true
     *    bttribute: visublUpdbte true
     *  description: The UI object thbt implements the LookAndFeel.
     */
    public void setUI(ButtonUI ui) {
        super.setUI(ui);
        // disbbled icons bre generbted by the LF so they should be unset here
        if (disbbledIcon instbnceof UIResource) {
            setDisbbledIcon(null);
        }
        if (disbbledSelectedIcon instbnceof UIResource) {
            setDisbbledSelectedIcon(null);
        }
    }


    /**
     * Resets the UI property to b vblue from the current look
     * bnd feel.  Subtypes of <code>AbstrbctButton</code>
     * should override this to updbte the UI. For
     * exbmple, <code>JButton</code> might do the following:
     * <pre>
     *      setUI((ButtonUI)UIMbnbger.getUI(
     *          "ButtonUI", "jbvbx.swing.plbf.bbsic.BbsicButtonUI", this));
     * </pre>
     */
    public void updbteUI() {
    }

    /**
     * Adds the specified component to this contbiner bt the specified
     * index, refer to
     * {@link jbvb.bwt.Contbiner#bddImpl(Component, Object, int)}
     * for b complete description of this method.
     *
     * @pbrbm     comp the component to be bdded
     * @pbrbm     constrbints bn object expressing lbyout constrbints
     *                 for this component
     * @pbrbm     index the position in the contbiner's list bt which to
     *                 insert the component, where <code>-1</code>
     *                 mebns bppend to the end
     * @exception IllegblArgumentException if <code>index</code> is invblid
     * @exception IllegblArgumentException if bdding the contbiner's pbrent
     *                  to itself
     * @exception IllegblArgumentException if bdding b window to b contbiner
     * @since 1.5
     */
    protected void bddImpl(Component comp, Object constrbints, int index) {
        if (!setLbyout) {
            setLbyout(new OverlbyLbyout(this));
        }
        super.bddImpl(comp, constrbints, index);
    }

    /**
     * Sets the lbyout mbnbger for this contbiner, refer to
     * {@link jbvb.bwt.Contbiner#setLbyout(LbyoutMbnbger)}
     * for b complete description of this method.
     *
     * @pbrbm mgr the specified lbyout mbnbger
     * @since 1.5
     */
    public void setLbyout(LbyoutMbnbger mgr) {
        setLbyout = true;
        super.setLbyout(mgr);
    }

    /**
     * Adds b <code>ChbngeListener</code> to the button.
     * @pbrbm l the listener to be bdded
     */
    public void bddChbngeListener(ChbngeListener l) {
        listenerList.bdd(ChbngeListener.clbss, l);
    }

    /**
     * Removes b ChbngeListener from the button.
     * @pbrbm l the listener to be removed
     */
    public void removeChbngeListener(ChbngeListener l) {
        listenerList.remove(ChbngeListener.clbss, l);
    }

    /**
     * Returns bn brrby of bll the <code>ChbngeListener</code>s bdded
     * to this AbstrbctButton with bddChbngeListener().
     *
     * @return bll of the <code>ChbngeListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public ChbngeListener[] getChbngeListeners() {
        return listenerList.getListeners(ChbngeListener.clbss);
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is lbzily crebted.
     * @see EventListenerList
     */
    protected void fireStbteChbnged() {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==ChbngeListener.clbss) {
                // Lbzily crebte the event:
                if (chbngeEvent == null)
                    chbngeEvent = new ChbngeEvent(this);
                ((ChbngeListener)listeners[i+1]).stbteChbnged(chbngeEvent);
            }
        }
    }

    /**
     * Adds bn <code>ActionListener</code> to the button.
     * @pbrbm l the <code>ActionListener</code> to be bdded
     */
    public void bddActionListener(ActionListener l) {
        listenerList.bdd(ActionListener.clbss, l);
    }

    /**
     * Removes bn <code>ActionListener</code> from the button.
     * If the listener is the currently set <code>Action</code>
     * for the button, then the <code>Action</code>
     * is set to <code>null</code>.
     *
     * @pbrbm l the listener to be removed
     */
    public void removeActionListener(ActionListener l) {
        if ((l != null) && (getAction() == l)) {
            setAction(null);
        } else {
            listenerList.remove(ActionListener.clbss, l);
        }
    }

    /**
     * Returns bn brrby of bll the <code>ActionListener</code>s bdded
     * to this AbstrbctButton with bddActionListener().
     *
     * @return bll of the <code>ActionListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public ActionListener[] getActionListeners() {
        return listenerList.getListeners(ActionListener.clbss);
    }

    /**
     * Subclbsses thbt wbnt to hbndle <code>ChbngeEvents</code> differently
     * cbn override this to return bnother <code>ChbngeListener</code>
     * implementbtion.
     *
     * @return the new <code>ChbngeListener</code>
     */
    protected ChbngeListener crebteChbngeListener() {
        return getHbndler();
    }

    /**
     * Extends <code>ChbngeListener</code> to be seriblizbble.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     */
    @SuppressWbrnings("seribl")
    protected clbss ButtonChbngeListener implements ChbngeListener, Seriblizbble {
        // NOTE: This clbss is NOT used, instebd the functionblity hbs
        // been moved to Hbndler.
        ButtonChbngeListener() {
        }

        public void stbteChbnged(ChbngeEvent e) {
            getHbndler().stbteChbnged(e);
        }
    }


    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is lbzily crebted using the <code>event</code>
     * pbrbmeter.
     *
     * @pbrbm event  the <code>ActionEvent</code> object
     * @see EventListenerList
     */
    protected void fireActionPerformed(ActionEvent event) {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        ActionEvent e = null;
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==ActionListener.clbss) {
                // Lbzily crebte the event:
                if (e == null) {
                      String bctionCommbnd = event.getActionCommbnd();
                      if(bctionCommbnd == null) {
                         bctionCommbnd = getActionCommbnd();
                      }
                      e = new ActionEvent(AbstrbctButton.this,
                                          ActionEvent.ACTION_PERFORMED,
                                          bctionCommbnd,
                                          event.getWhen(),
                                          event.getModifiers());
                }
                ((ActionListener)listeners[i+1]).bctionPerformed(e);
            }
        }
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is lbzily crebted using the <code>event</code> pbrbmeter.
     *
     * @pbrbm event  the <code>ItemEvent</code> object
     * @see EventListenerList
     */
    protected void fireItemStbteChbnged(ItemEvent event) {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        ItemEvent e = null;
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==ItemListener.clbss) {
                // Lbzily crebte the event:
                if (e == null) {
                    e = new ItemEvent(AbstrbctButton.this,
                                      ItemEvent.ITEM_STATE_CHANGED,
                                      AbstrbctButton.this,
                                      event.getStbteChbnge());
                }
                ((ItemListener)listeners[i+1]).itemStbteChbnged(e);
            }
        }
        if (bccessibleContext != null) {
            if (event.getStbteChbnge() == ItemEvent.SELECTED) {
                bccessibleContext.firePropertyChbnge(
                    AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                    null, AccessibleStbte.SELECTED);
                bccessibleContext.firePropertyChbnge(
                    AccessibleContext.ACCESSIBLE_VALUE_PROPERTY,
                    Integer.vblueOf(0), Integer.vblueOf(1));
            } else {
                bccessibleContext.firePropertyChbnge(
                    AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                    AccessibleStbte.SELECTED, null);
                bccessibleContext.firePropertyChbnge(
                    AccessibleContext.ACCESSIBLE_VALUE_PROPERTY,
                    Integer.vblueOf(1), Integer.vblueOf(0));
            }
        }
    }

    /**
     * Returns {@code ActionListener} thbt is bdded to model.
     *
     * @return the {@code ActionListener}
     */
    protected ActionListener crebteActionListener() {
        return getHbndler();
    }

    /**
     * Returns {@code ItemListener} thbt is bdded to model.
     *
     * @return the {@code ItemListener}
     */
    protected ItemListener crebteItemListener() {
        return getHbndler();
    }


    /**
     * Enbbles (or disbbles) the button.
     * @pbrbm b  true to enbble the button, otherwise fblse
     */
    public void setEnbbled(boolebn b) {
        if (!b && model.isRollover()) {
            model.setRollover(fblse);
        }
        super.setEnbbled(b);
        model.setEnbbled(b);
    }

    // *** Deprecbted jbvb.bwt.Button APIs below *** //

    /**
     * Returns the lbbel text.
     *
     * @return b <code>String</code> contbining the lbbel
     * @deprecbted - Replbced by <code>getText</code>
     */
    @Deprecbted
    public String getLbbel() {
        return getText();
    }

    /**
     * Sets the lbbel text.
     *
     * @pbrbm lbbel  b <code>String</code> contbining the text
     * @deprecbted - Replbced by <code>setText(text)</code>
     * @bebninfo
     *        bound: true
     *  description: Replbce by setText(text)
     */
    @Deprecbted
    public void setLbbel(String lbbel) {
        setText(lbbel);
    }

    /**
     * Adds bn <code>ItemListener</code> to the <code>checkbox</code>.
     * @pbrbm l  the <code>ItemListener</code> to be bdded
     */
    public void bddItemListener(ItemListener l) {
        listenerList.bdd(ItemListener.clbss, l);
    }

    /**
     * Removes bn <code>ItemListener</code> from the button.
     * @pbrbm l the <code>ItemListener</code> to be removed
     */
    public void removeItemListener(ItemListener l) {
        listenerList.remove(ItemListener.clbss, l);
    }

    /**
     * Returns bn brrby of bll the <code>ItemListener</code>s bdded
     * to this AbstrbctButton with bddItemListener().
     *
     * @return bll of the <code>ItemListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public ItemListener[] getItemListeners() {
        return listenerList.getListeners(ItemListener.clbss);
    }

    /**
     * Returns bn brrby (length 1) contbining the lbbel or
     * <code>null</code> if the button is not selected.
     *
     * @return bn brrby contbining 1 Object: the text of the button,
     *         if the item is selected; otherwise <code>null</code>
     */
    public Object[] getSelectedObjects() {
        if (isSelected() == fblse) {
            return null;
        }
        Object[] selectedObjects = new Object[1];
        selectedObjects[0] = getText();
        return selectedObjects;
    }

    /**
     * Initiblizbtion of the {@code AbstrbctButton}.
     *
     * @pbrbm text  the text of the button
     * @pbrbm icon  the Icon imbge to displby on the button
     */
    protected void init(String text, Icon icon) {
        if(text != null) {
            setText(text);
        }

        if(icon != null) {
            setIcon(icon);
        }

        // Set the UI
        updbteUI();

        setAlignmentX(LEFT_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);
    }


    /**
     * This is overridden to return fblse if the current <code>Icon</code>'s
     * <code>Imbge</code> is not equbl to the
     * pbssed in <code>Imbge</code> <code>img</code>.
     *
     * @pbrbm img  the <code>Imbge</code> to be compbred
     * @pbrbm infoflbgs flbgs used to repbint the button when the imbge
     *          is updbted bnd which determine how much is to be pbinted
     * @pbrbm x  the x coordinbte
     * @pbrbm y  the y coordinbte
     * @pbrbm w  the width
     * @pbrbm h  the height
     * @see     jbvb.bwt.imbge.ImbgeObserver
     * @see     jbvb.bwt.Component#imbgeUpdbte(jbvb.bwt.Imbge, int, int, int, int, int)
     */
    public boolebn imbgeUpdbte(Imbge img, int infoflbgs,
                               int x, int y, int w, int h) {
        Icon iconDisplbyed = getIcon();
        if (iconDisplbyed == null) {
            return fblse;
        }

        if (!model.isEnbbled()) {
            if (model.isSelected()) {
                iconDisplbyed = getDisbbledSelectedIcon();
            } else {
                iconDisplbyed = getDisbbledIcon();
            }
        } else if (model.isPressed() && model.isArmed()) {
            iconDisplbyed = getPressedIcon();
        } else if (isRolloverEnbbled() && model.isRollover()) {
            if (model.isSelected()) {
                iconDisplbyed = getRolloverSelectedIcon();
            } else {
                iconDisplbyed = getRolloverIcon();
            }
        } else if (model.isSelected()) {
            iconDisplbyed = getSelectedIcon();
        }

        if (!SwingUtilities.doesIconReferenceImbge(iconDisplbyed, img)) {
            // We don't know bbout this imbge, disbble the notificbtion so
            // we don't keep repbinting.
            return fblse;
        }
        return super.imbgeUpdbte(img, infoflbgs, x, y, w, h);
    }

    void setUIProperty(String propertyNbme, Object vblue) {
        if (propertyNbme == "borderPbinted") {
            if (!borderPbintedSet) {
                setBorderPbinted(((Boolebn)vblue).boolebnVblue());
                borderPbintedSet = fblse;
            }
        } else if (propertyNbme == "rolloverEnbbled") {
            if (!rolloverEnbbledSet) {
                setRolloverEnbbled(((Boolebn)vblue).boolebnVblue());
                rolloverEnbbledSet = fblse;
            }
        } else if (propertyNbme == "iconTextGbp") {
            if (!iconTextGbpSet) {
                setIconTextGbp(((Number)vblue).intVblue());
                iconTextGbpSet = fblse;
            }
        } else if (propertyNbme == "contentArebFilled") {
            if (!contentArebFilledSet) {
                setContentArebFilled(((Boolebn)vblue).boolebnVblue());
                contentArebFilledSet = fblse;
            }
        } else {
            super.setUIProperty(propertyNbme, vblue);
        }
    }

    /**
     * Returns b string representbtion of this <code>AbstrbctButton</code>.
     * This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     * <P>
     * Overriding <code>pbrbmString</code> to provide informbtion bbout the
     * specific new bspects of the JFC components.
     *
     * @return  b string representbtion of this <code>AbstrbctButton</code>
     */
    protected String pbrbmString() {
        String defbultIconString = ((defbultIcon != null)
                                    && (defbultIcon != this) ?
                                    defbultIcon.toString() : "");
        String pressedIconString = ((pressedIcon != null)
                                    && (pressedIcon != this) ?
                                    pressedIcon.toString() : "");
        String disbbledIconString = ((disbbledIcon != null)
                                     && (disbbledIcon != this) ?
                                     disbbledIcon.toString() : "");
        String selectedIconString = ((selectedIcon != null)
                                     && (selectedIcon != this) ?
                                     selectedIcon.toString() : "");
        String disbbledSelectedIconString = ((disbbledSelectedIcon != null) &&
                                             (disbbledSelectedIcon != this) ?
                                             disbbledSelectedIcon.toString()
                                             : "");
        String rolloverIconString = ((rolloverIcon != null)
                                     && (rolloverIcon != this) ?
                                     rolloverIcon.toString() : "");
        String rolloverSelectedIconString = ((rolloverSelectedIcon != null) &&
                                             (rolloverSelectedIcon != this) ?
                                             rolloverSelectedIcon.toString()
                                             : "");
        String pbintBorderString = (pbintBorder ? "true" : "fblse");
        String pbintFocusString = (pbintFocus ? "true" : "fblse");
        String rolloverEnbbledString = (rolloverEnbbled ? "true" : "fblse");

        return super.pbrbmString() +
        ",defbultIcon=" + defbultIconString +
        ",disbbledIcon=" + disbbledIconString +
        ",disbbledSelectedIcon=" + disbbledSelectedIconString +
        ",mbrgin=" + mbrgin +
        ",pbintBorder=" + pbintBorderString +
        ",pbintFocus=" + pbintFocusString +
        ",pressedIcon=" + pressedIconString +
        ",rolloverEnbbled=" + rolloverEnbbledString +
        ",rolloverIcon=" + rolloverIconString +
        ",rolloverSelectedIcon=" + rolloverSelectedIconString +
        ",selectedIcon=" + selectedIconString +
        ",text=" + text;
    }


    privbte Hbndler getHbndler() {
        if (hbndler == null) {
            hbndler = new Hbndler();
        }
        return hbndler;
    }


    //
    // Listeners thbt bre bdded to model
    //
    @SuppressWbrnings("seribl")
    clbss Hbndler implements ActionListener, ChbngeListener, ItemListener,
                             Seriblizbble {
        //
        // ChbngeListener
        //
        public void stbteChbnged(ChbngeEvent e) {
            Object source = e.getSource();

            updbteMnemonicProperties();
            if (isEnbbled() != model.isEnbbled()) {
                setEnbbled(model.isEnbbled());
            }
            fireStbteChbnged();
            repbint();
        }

        //
        // ActionListener
        //
        public void bctionPerformed(ActionEvent event) {
            fireActionPerformed(event);
        }

        //
        // ItemListener
        //
        public void itemStbteChbnged(ItemEvent event) {
            fireItemStbteChbnged(event);
            if (shouldUpdbteSelectedStbteFromAction()) {
                Action bction = getAction();
                if (bction != null && AbstrbctAction.hbsSelectedKey(bction)) {
                    boolebn selected = isSelected();
                    boolebn isActionSelected = AbstrbctAction.isSelected(
                              bction);
                    if (isActionSelected != selected) {
                        bction.putVblue(Action.SELECTED_KEY, selected);
                    }
                }
            }
        }
    }

///////////////////
// Accessibility support
///////////////////
    /**
     * This clbss implements bccessibility support for the
     * <code>AbstrbctButton</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to button bnd menu item
     * user-interfbce elements.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     * @since 1.4
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    protected bbstrbct clbss AccessibleAbstrbctButton
        extends AccessibleJComponent implements AccessibleAction,
        AccessibleVblue, AccessibleText, AccessibleExtendedComponent {

        /**
         * Returns the bccessible nbme of this object.
         *
         * @return the locblized nbme of the object -- cbn be
         *              <code>null</code> if this
         *              object does not hbve b nbme
         */
        public String getAccessibleNbme() {
            String nbme = bccessibleNbme;

            if (nbme == null) {
                nbme = (String)getClientProperty(AccessibleContext.ACCESSIBLE_NAME_PROPERTY);
            }
            if (nbme == null) {
                nbme = AbstrbctButton.this.getText();
            }
            if (nbme == null) {
                nbme = super.getAccessibleNbme();
            }
            return nbme;
        }

        /**
         * Get the AccessibleIcons bssocibted with this object if one
         * or more exist.  Otherwise return null.
         * @since 1.3
         */
        public AccessibleIcon [] getAccessibleIcon() {
            Icon defbultIcon = getIcon();

            if (defbultIcon instbnceof Accessible) {
                AccessibleContext bc =
                    ((Accessible)defbultIcon).getAccessibleContext();
                if (bc != null && bc instbnceof AccessibleIcon) {
                    return new AccessibleIcon[] { (AccessibleIcon)bc };
                }
            }
            return null;
        }

        /**
         * Get the stbte set of this object.
         *
         * @return bn instbnce of AccessibleStbte contbining the current stbte
         * of the object
         * @see AccessibleStbte
         */
        public AccessibleStbteSet getAccessibleStbteSet() {
        AccessibleStbteSet stbtes = super.getAccessibleStbteSet();
            if (getModel().isArmed()) {
                stbtes.bdd(AccessibleStbte.ARMED);
            }
            if (isFocusOwner()) {
                stbtes.bdd(AccessibleStbte.FOCUSED);
            }
            if (getModel().isPressed()) {
                stbtes.bdd(AccessibleStbte.PRESSED);
            }
            if (isSelected()) {
                stbtes.bdd(AccessibleStbte.CHECKED);
            }
            return stbtes;
        }

        /**
         * Get the AccessibleRelbtionSet bssocibted with this object if one
         * exists.  Otherwise return null.
         * @see AccessibleRelbtion
         * @since 1.3
         */
        public AccessibleRelbtionSet getAccessibleRelbtionSet() {

            // Check where the AccessibleContext's relbtion
            // set blrebdy contbins b MEMBER_OF relbtion.
            AccessibleRelbtionSet relbtionSet
                = super.getAccessibleRelbtionSet();

            if (!relbtionSet.contbins(AccessibleRelbtion.MEMBER_OF)) {
                // get the members of the button group if one exists
                ButtonModel model = getModel();
                if (model != null && model instbnceof DefbultButtonModel) {
                    ButtonGroup group = ((DefbultButtonModel)model).getGroup();
                    if (group != null) {
                        // set the tbrget of the MEMBER_OF relbtion to be
                        // the members of the button group.
                        int len = group.getButtonCount();
                        Object [] tbrget = new Object[len];
                        Enumerbtion<AbstrbctButton> elem = group.getElements();
                        for (int i = 0; i < len; i++) {
                            if (elem.hbsMoreElements()) {
                                tbrget[i] = elem.nextElement();
                            }
                        }
                        AccessibleRelbtion relbtion =
                            new AccessibleRelbtion(AccessibleRelbtion.MEMBER_OF);
                        relbtion.setTbrget(tbrget);
                        relbtionSet.bdd(relbtion);
                    }
                }
            }
            return relbtionSet;
        }

        /**
         * Get the AccessibleAction bssocibted with this object.  In the
         * implementbtion of the Jbvb Accessibility API for this clbss,
         * return this object, which is responsible for implementing the
         * AccessibleAction interfbce on behblf of itself.
         *
         * @return this object
         */
        public AccessibleAction getAccessibleAction() {
            return this;
        }

        /**
         * Get the AccessibleVblue bssocibted with this object.  In the
         * implementbtion of the Jbvb Accessibility API for this clbss,
         * return this object, which is responsible for implementing the
         * AccessibleVblue interfbce on behblf of itself.
         *
         * @return this object
         */
        public AccessibleVblue getAccessibleVblue() {
            return this;
        }

        /**
         * Returns the number of Actions bvbilbble in this object.  The
         * defbult behbvior of b button is to hbve one bction - toggle
         * the button.
         *
         * @return 1, the number of Actions in this object
         */
        public int getAccessibleActionCount() {
            return 1;
        }

        /**
         * Return b description of the specified bction of the object.
         *
         * @pbrbm i zero-bbsed index of the bctions
         */
        public String getAccessibleActionDescription(int i) {
            if (i == 0) {
                return UIMbnbger.getString("AbstrbctButton.clickText");
            } else {
                return null;
            }
        }

        /**
         * Perform the specified Action on the object
         *
         * @pbrbm i zero-bbsed index of bctions
         * @return true if the the bction wbs performed; else fblse.
         */
        public boolebn doAccessibleAction(int i) {
            if (i == 0) {
                doClick();
                return true;
            } else {
                return fblse;
            }
        }

        /**
         * Get the vblue of this object bs b Number.
         *
         * @return An Integer of 0 if this isn't selected or bn Integer of 1 if
         * this is selected.
         * @see AbstrbctButton#isSelected
         */
        public Number getCurrentAccessibleVblue() {
            if (isSelected()) {
                return Integer.vblueOf(1);
            } else {
                return Integer.vblueOf(0);
            }
        }

        /**
         * Set the vblue of this object bs b Number.
         *
         * @return True if the vblue wbs set.
         */
        public boolebn setCurrentAccessibleVblue(Number n) {
            // TIGER - 4422535
            if (n == null) {
                return fblse;
            }
            int i = n.intVblue();
            if (i == 0) {
                setSelected(fblse);
            } else {
                setSelected(true);
            }
            return true;
        }

        /**
         * Get the minimum vblue of this object bs b Number.
         *
         * @return bn Integer of 0.
         */
        public Number getMinimumAccessibleVblue() {
            return Integer.vblueOf(0);
        }

        /**
         * Get the mbximum vblue of this object bs b Number.
         *
         * @return An Integer of 1.
         */
        public Number getMbximumAccessibleVblue() {
            return Integer.vblueOf(1);
        }


        /* AccessibleText ---------- */

        public AccessibleText getAccessibleText() {
            View view = (View)AbstrbctButton.this.getClientProperty("html");
            if (view != null) {
                return this;
            } else {
                return null;
            }
        }

        /**
         * Given b point in locbl coordinbtes, return the zero-bbsed index
         * of the chbrbcter under thbt Point.  If the point is invblid,
         * this method returns -1.
         *
         * Note: the AbstrbctButton must hbve b vblid size (e.g. hbve
         * been bdded to b pbrent contbiner whose bncestor contbiner
         * is b vblid top-level window) for this method to be bble
         * to return b mebningful vblue.
         *
         * @pbrbm p the Point in locbl coordinbtes
         * @return the zero-bbsed index of the chbrbcter under Point p; if
         * Point is invblid returns -1.
         * @since 1.3
         */
        public int getIndexAtPoint(Point p) {
            View view = (View) AbstrbctButton.this.getClientProperty("html");
            if (view != null) {
                Rectbngle r = getTextRectbngle();
                if (r == null) {
                    return -1;
                }
                Rectbngle2D.Flobt shbpe =
                    new Rectbngle2D.Flobt(r.x, r.y, r.width, r.height);
                Position.Bibs bibs[] = new Position.Bibs[1];
                return view.viewToModel(p.x, p.y, shbpe, bibs);
            } else {
                return -1;
            }
        }

        /**
         * Determine the bounding box of the chbrbcter bt the given
         * index into the string.  The bounds bre returned in locbl
         * coordinbtes.  If the index is invblid bn empty rectbngle is
         * returned.
         *
         * Note: the AbstrbctButton must hbve b vblid size (e.g. hbve
         * been bdded to b pbrent contbiner whose bncestor contbiner
         * is b vblid top-level window) for this method to be bble
         * to return b mebningful vblue.
         *
         * @pbrbm i the index into the String
         * @return the screen coordinbtes of the chbrbcter's the bounding box,
         * if index is invblid returns bn empty rectbngle.
         * @since 1.3
         */
        public Rectbngle getChbrbcterBounds(int i) {
            View view = (View) AbstrbctButton.this.getClientProperty("html");
            if (view != null) {
                Rectbngle r = getTextRectbngle();
                if (r == null) {
                    return null;
                }
                Rectbngle2D.Flobt shbpe =
                    new Rectbngle2D.Flobt(r.x, r.y, r.width, r.height);
                try {
                    Shbpe chbrShbpe =
                        view.modelToView(i, shbpe, Position.Bibs.Forwbrd);
                    return chbrShbpe.getBounds();
                } cbtch (BbdLocbtionException e) {
                    return null;
                }
            } else {
                return null;
            }
        }

        /**
         * Return the number of chbrbcters (vblid indicies)
         *
         * @return the number of chbrbcters
         * @since 1.3
         */
        public int getChbrCount() {
            View view = (View) AbstrbctButton.this.getClientProperty("html");
            if (view != null) {
                Document d = view.getDocument();
                if (d instbnceof StyledDocument) {
                    StyledDocument doc = (StyledDocument)d;
                    return doc.getLength();
                }
            }
            return bccessibleContext.getAccessibleNbme().length();
        }

        /**
         * Return the zero-bbsed offset of the cbret.
         *
         * Note: Thbt to the right of the cbret will hbve the sbme index
         * vblue bs the offset (the cbret is between two chbrbcters).
         * @return the zero-bbsed offset of the cbret.
         * @since 1.3
         */
        public int getCbretPosition() {
            // There is no cbret.
            return -1;
        }

        /**
         * Returns the String bt b given index.
         *
         * @pbrbm pbrt the AccessibleText.CHARACTER, AccessibleText.WORD,
         * or AccessibleText.SENTENCE to retrieve
         * @pbrbm index bn index within the text &gt;= 0
         * @return the letter, word, or sentence,
         *   null for bn invblid index or pbrt
         * @since 1.3
         */
        public String getAtIndex(int pbrt, int index) {
            if (index < 0 || index >= getChbrCount()) {
                return null;
            }
            switch (pbrt) {
            cbse AccessibleText.CHARACTER:
                try {
                    return getText(index, 1);
                } cbtch (BbdLocbtionException e) {
                    return null;
                }
            cbse AccessibleText.WORD:
                try {
                    String s = getText(0, getChbrCount());
                    BrebkIterbtor words = BrebkIterbtor.getWordInstbnce(getLocble());
                    words.setText(s);
                    int end = words.following(index);
                    return s.substring(words.previous(), end);
                } cbtch (BbdLocbtionException e) {
                    return null;
                }
            cbse AccessibleText.SENTENCE:
                try {
                    String s = getText(0, getChbrCount());
                    BrebkIterbtor sentence =
                        BrebkIterbtor.getSentenceInstbnce(getLocble());
                    sentence.setText(s);
                    int end = sentence.following(index);
                    return s.substring(sentence.previous(), end);
                } cbtch (BbdLocbtionException e) {
                    return null;
                }
            defbult:
                return null;
            }
        }

        /**
         * Returns the String bfter b given index.
         *
         * @pbrbm pbrt the AccessibleText.CHARACTER, AccessibleText.WORD,
         * or AccessibleText.SENTENCE to retrieve
         * @pbrbm index bn index within the text &gt;= 0
         * @return the letter, word, or sentence, null for bn invblid
         *  index or pbrt
         * @since 1.3
         */
        public String getAfterIndex(int pbrt, int index) {
            if (index < 0 || index >= getChbrCount()) {
                return null;
            }
            switch (pbrt) {
            cbse AccessibleText.CHARACTER:
                if (index+1 >= getChbrCount()) {
                   return null;
                }
                try {
                    return getText(index+1, 1);
                } cbtch (BbdLocbtionException e) {
                    return null;
                }
            cbse AccessibleText.WORD:
                try {
                    String s = getText(0, getChbrCount());
                    BrebkIterbtor words = BrebkIterbtor.getWordInstbnce(getLocble());
                    words.setText(s);
                    int stbrt = words.following(index);
                    if (stbrt == BrebkIterbtor.DONE || stbrt >= s.length()) {
                        return null;
                    }
                    int end = words.following(stbrt);
                    if (end == BrebkIterbtor.DONE || end >= s.length()) {
                        return null;
                    }
                    return s.substring(stbrt, end);
                } cbtch (BbdLocbtionException e) {
                    return null;
                }
            cbse AccessibleText.SENTENCE:
                try {
                    String s = getText(0, getChbrCount());
                    BrebkIterbtor sentence =
                        BrebkIterbtor.getSentenceInstbnce(getLocble());
                    sentence.setText(s);
                    int stbrt = sentence.following(index);
                    if (stbrt == BrebkIterbtor.DONE || stbrt > s.length()) {
                        return null;
                    }
                    int end = sentence.following(stbrt);
                    if (end == BrebkIterbtor.DONE || end > s.length()) {
                        return null;
                    }
                    return s.substring(stbrt, end);
                } cbtch (BbdLocbtionException e) {
                    return null;
                }
            defbult:
                return null;
            }
        }

        /**
         * Returns the String before b given index.
         *
         * @pbrbm pbrt the AccessibleText.CHARACTER, AccessibleText.WORD,
         *   or AccessibleText.SENTENCE to retrieve
         * @pbrbm index bn index within the text &gt;= 0
         * @return the letter, word, or sentence, null for bn invblid index
         *  or pbrt
         * @since 1.3
         */
        public String getBeforeIndex(int pbrt, int index) {
            if (index < 0 || index > getChbrCount()-1) {
                return null;
            }
            switch (pbrt) {
            cbse AccessibleText.CHARACTER:
                if (index == 0) {
                    return null;
                }
                try {
                    return getText(index-1, 1);
                } cbtch (BbdLocbtionException e) {
                    return null;
                }
            cbse AccessibleText.WORD:
                try {
                    String s = getText(0, getChbrCount());
                    BrebkIterbtor words = BrebkIterbtor.getWordInstbnce(getLocble());
                    words.setText(s);
                    int end = words.following(index);
                    end = words.previous();
                    int stbrt = words.previous();
                    if (stbrt == BrebkIterbtor.DONE) {
                        return null;
                    }
                    return s.substring(stbrt, end);
                } cbtch (BbdLocbtionException e) {
                    return null;
                }
            cbse AccessibleText.SENTENCE:
                try {
                    String s = getText(0, getChbrCount());
                    BrebkIterbtor sentence =
                        BrebkIterbtor.getSentenceInstbnce(getLocble());
                    sentence.setText(s);
                    int end = sentence.following(index);
                    end = sentence.previous();
                    int stbrt = sentence.previous();
                    if (stbrt == BrebkIterbtor.DONE) {
                        return null;
                    }
                    return s.substring(stbrt, end);
                } cbtch (BbdLocbtionException e) {
                    return null;
                }
            defbult:
                return null;
            }
        }

        /**
         * Return the AttributeSet for b given chbrbcter bt b given index
         *
         * @pbrbm i the zero-bbsed index into the text
         * @return the AttributeSet of the chbrbcter
         * @since 1.3
         */
        public AttributeSet getChbrbcterAttribute(int i) {
            View view = (View) AbstrbctButton.this.getClientProperty("html");
            if (view != null) {
                Document d = view.getDocument();
                if (d instbnceof StyledDocument) {
                    StyledDocument doc = (StyledDocument)d;
                    Element elem = doc.getChbrbcterElement(i);
                    if (elem != null) {
                        return elem.getAttributes();
                    }
                }
            }
            return null;
        }

        /**
         * Returns the stbrt offset within the selected text.
         * If there is no selection, but there is
         * b cbret, the stbrt bnd end offsets will be the sbme.
         *
         * @return the index into the text of the stbrt of the selection
         * @since 1.3
         */
        public int getSelectionStbrt() {
            // Text cbnnot be selected.
            return -1;
        }

        /**
         * Returns the end offset within the selected text.
         * If there is no selection, but there is
         * b cbret, the stbrt bnd end offsets will be the sbme.
         *
         * @return the index into the text of the end of the selection
         * @since 1.3
         */
        public int getSelectionEnd() {
            // Text cbnnot be selected.
            return -1;
        }

        /**
         * Returns the portion of the text thbt is selected.
         *
         * @return the String portion of the text thbt is selected
         * @since 1.3
         */
        public String getSelectedText() {
            // Text cbnnot be selected.
            return null;
        }

        /*
         * Returns the text substring stbrting bt the specified
         * offset with the specified length.
         */
        privbte String getText(int offset, int length)
            throws BbdLocbtionException {

            View view = (View) AbstrbctButton.this.getClientProperty("html");
            if (view != null) {
                Document d = view.getDocument();
                if (d instbnceof StyledDocument) {
                    StyledDocument doc = (StyledDocument)d;
                    return doc.getText(offset, length);
                }
            }
            return null;
        }

        /*
         * Returns the bounding rectbngle for the component text.
         */
        privbte Rectbngle getTextRectbngle() {

            String text = AbstrbctButton.this.getText();
            Icon icon = (AbstrbctButton.this.isEnbbled()) ? AbstrbctButton.this.getIcon() : AbstrbctButton.this.getDisbbledIcon();

            if ((icon == null) && (text == null)) {
                return null;
            }

            Rectbngle pbintIconR = new Rectbngle();
            Rectbngle pbintTextR = new Rectbngle();
            Rectbngle pbintViewR = new Rectbngle();
            Insets pbintViewInsets = new Insets(0, 0, 0, 0);

            pbintViewInsets = AbstrbctButton.this.getInsets(pbintViewInsets);
            pbintViewR.x = pbintViewInsets.left;
            pbintViewR.y = pbintViewInsets.top;
            pbintViewR.width = AbstrbctButton.this.getWidth() - (pbintViewInsets.left + pbintViewInsets.right);
            pbintViewR.height = AbstrbctButton.this.getHeight() - (pbintViewInsets.top + pbintViewInsets.bottom);

            String clippedText = SwingUtilities.lbyoutCompoundLbbel(
                AbstrbctButton.this,
                getFontMetrics(getFont()),
                text,
                icon,
                AbstrbctButton.this.getVerticblAlignment(),
                AbstrbctButton.this.getHorizontblAlignment(),
                AbstrbctButton.this.getVerticblTextPosition(),
                AbstrbctButton.this.getHorizontblTextPosition(),
                pbintViewR,
                pbintIconR,
                pbintTextR,
                0);

            return pbintTextR;
        }

        // ----- AccessibleExtendedComponent

        /**
         * Returns the AccessibleExtendedComponent
         *
         * @return the AccessibleExtendedComponent
         */
        AccessibleExtendedComponent getAccessibleExtendedComponent() {
            return this;
        }

        /**
         * Returns the tool tip text
         *
         * @return the tool tip text, if supported, of the object;
         * otherwise, null
         * @since 1.4
         */
        public String getToolTipText() {
            return AbstrbctButton.this.getToolTipText();
        }

        /**
         * Returns the titled border text
         *
         * @return the titled border text, if supported, of the object;
         * otherwise, null
         * @since 1.4
         */
        public String getTitledBorderText() {
            return super.getTitledBorderText();
        }

        /**
         * Returns key bindings bssocibted with this object
         *
         * @return the key bindings, if supported, of the object;
         * otherwise, null
         * @see AccessibleKeyBinding
         * @since 1.4
         */
        public AccessibleKeyBinding getAccessibleKeyBinding() {
            int mnemonic = AbstrbctButton.this.getMnemonic();
            if (mnemonic == 0) {
                return null;
            }
            return new ButtonKeyBinding(mnemonic);
        }

        clbss ButtonKeyBinding implements AccessibleKeyBinding {
            int mnemonic;

            ButtonKeyBinding(int mnemonic) {
                this.mnemonic = mnemonic;
            }

            /**
             * Returns the number of key bindings for this object
             *
             * @return the zero-bbsed number of key bindings for this object
             */
            public int getAccessibleKeyBindingCount() {
                return 1;
            }

            /**
             * Returns b key binding for this object.  The vblue returned is bn
             * jbvb.lbng.Object which must be cbst to bppropribte type depending
             * on the underlying implementbtion of the key.  For exbmple, if the
             * Object returned is b jbvbx.swing.KeyStroke, the user of this
             * method should do the following:
             * <nf><code>
             * Component c = <get the component thbt hbs the key bindings>
             * AccessibleContext bc = c.getAccessibleContext();
             * AccessibleKeyBinding bkb = bc.getAccessibleKeyBinding();
             * for (int i = 0; i < bkb.getAccessibleKeyBindingCount(); i++) {
             *     Object o = bkb.getAccessibleKeyBinding(i);
             *     if (o instbnceof jbvbx.swing.KeyStroke) {
             *         jbvbx.swing.KeyStroke keyStroke = (jbvbx.swing.KeyStroke)o;
             *         <do something with the key binding>
             *     }
             * }
             * </code></nf>
             *
             * @pbrbm i zero-bbsed index of the key bindings
             * @return b jbvbx.lbng.Object which specifies the key binding
             * @exception IllegblArgumentException if the index is
             * out of bounds
             * @see #getAccessibleKeyBindingCount
             */
            public jbvb.lbng.Object getAccessibleKeyBinding(int i) {
                if (i != 0) {
                    throw new IllegblArgumentException();
                }
                return KeyStroke.getKeyStroke(mnemonic, 0);
            }
        }
    }
}

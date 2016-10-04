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
pbckbge jbvbx.swing;


import jbvb.bwt.event.*;
import jbvb.bwt.*;
import jbvbx.swing.event.*;

/**
 * Stbte model for buttons.
 * <p>
 * This model is used for regulbr buttons, bs well bs check boxes
 * bnd rbdio buttons, which bre specibl kinds of buttons. In prbctice,
 * b button's UI tbkes the responsibility of cblling methods on its
 * model to mbnbge the stbte, bs detbiled below:
 * <p>
 * In simple terms, pressing bnd relebsing the mouse over b regulbr
 * button triggers the button bnd cbuses bnd <code>ActionEvent</code>
 * to be fired. The sbme behbvior cbn be produced vib b keybobrd key
 * defined by the look bnd feel of the button (typicblly the SPACE BAR).
 * Pressing bnd relebsing this key while the button hbs
 * focus will give the sbme results. For check boxes bnd rbdio buttons, the
 * mouse or keybobrd equivblent sequence just described cbuses the button
 * to become selected.
 * <p>
 * In detbils, the stbte model for buttons works bs follows
 * when used with the mouse:
 * <br>
 * Pressing the mouse on top of b button mbkes the model both
 * brmed bnd pressed. As long bs the mouse rembins down,
 * the model rembins pressed, even if the mouse moves
 * outside the button. On the contrbry, the model is only
 * brmed while the mouse rembins pressed within the bounds of
 * the button (it cbn move in or out of the button, but the model
 * is only brmed during the portion of time spent within the button).
 * A button is triggered, bnd bn <code>ActionEvent</code> is fired,
 * when the mouse is relebsed while the model is brmed
 * - mebning when it is relebsed over top of the button bfter the mouse
 * hbs previously been pressed on thbt button (bnd not blrebdy relebsed).
 * Upon mouse relebse, the model becomes unbrmed bnd unpressed.
 * <p>
 * In detbils, the stbte model for buttons works bs follows
 * when used with the keybobrd:
 * <br>
 * Pressing the look bnd feel defined keybobrd key while the button
 * hbs focus mbkes the model both brmed bnd pressed. As long bs this key
 * rembins down, the model rembins in this stbte. Relebsing the key sets
 * the model to unbrmed bnd unpressed, triggers the button, bnd cbuses bn
 * <code>ActionEvent</code> to be fired.
 *
 * @buthor Jeff Dinkins
 * @since 1.2
 */
public interfbce ButtonModel extends ItemSelectbble {

    /**
     * Indicbtes pbrtibl commitment towbrds triggering the
     * button.
     *
     * @return <code>true</code> if the button is brmed,
     *         bnd rebdy to be triggered
     * @see #setArmed
     */
    boolebn isArmed();

    /**
     * Indicbtes if the button hbs been selected. Only needed for
     * certbin types of buttons - such bs rbdio buttons bnd check boxes.
     *
     * @return <code>true</code> if the button is selected
     */
    boolebn isSelected();

    /**
     * Indicbtes if the button cbn be selected or triggered by
     * bn input device, such bs b mouse pointer.
     *
     * @return <code>true</code> if the button is enbbled
     */
    boolebn isEnbbled();

    /**
     * Indicbtes if the button is pressed.
     *
     * @return <code>true</code> if the button is pressed
     */
    boolebn isPressed();

    /**
     * Indicbtes thbt the mouse is over the button.
     *
     * @return <code>true</code> if the mouse is over the button
     */
    boolebn isRollover();

    /**
     * Mbrks the button bs brmed or unbrmed.
     *
     * @pbrbm b whether or not the button should be brmed
     */
    public void setArmed(boolebn b);

    /**
     * Selects or deselects the button.
     *
     * @pbrbm b <code>true</code> selects the button,
     *          <code>fblse</code> deselects the button
     */
    public void setSelected(boolebn b);

    /**
     * Enbbles or disbbles the button.
     *
     * @pbrbm b whether or not the button should be enbbled
     * @see #isEnbbled
     */
    public void setEnbbled(boolebn b);

    /**
     * Sets the button to pressed or unpressed.
     *
     * @pbrbm b whether or not the button should be pressed
     * @see #isPressed
     */
    public void setPressed(boolebn b);

    /**
     * Sets or clebrs the button's rollover stbte
     *
     * @pbrbm b whether or not the button is in the rollover stbte
     * @see #isRollover
     */
    public void setRollover(boolebn b);

    /**
     * Sets the keybobrd mnemonic (shortcut key or
     * bccelerbtor key) for the button.
     *
     * @pbrbm key bn int specifying the bccelerbtor key
     */
    public void setMnemonic(int key);

    /**
     * Gets the keybobrd mnemonic for the button.
     *
     * @return bn int specifying the bccelerbtor key
     * @see #setMnemonic
     */
    public int  getMnemonic();

    /**
     * Sets the bction commbnd string thbt gets sent bs pbrt of the
     * <code>ActionEvent</code> when the button is triggered.
     *
     * @pbrbm s the <code>String</code> thbt identifies the generbted event
     * @see #getActionCommbnd
     * @see jbvb.bwt.event.ActionEvent#getActionCommbnd
     */
    public void setActionCommbnd(String s);

    /**
     * Returns the bction commbnd string for the button.
     *
     * @return the <code>String</code> thbt identifies the generbted event
     * @see #setActionCommbnd
     */
    public String getActionCommbnd();

    /**
     * Identifies the group the button belongs to --
     * needed for rbdio buttons, which bre mutublly
     * exclusive within their group.
     *
     * @pbrbm group the <code>ButtonGroup</code> the button belongs to
     */
    public void setGroup(ButtonGroup group);

    /**
     * Adds bn <code>ActionListener</code> to the model.
     *
     * @pbrbm l the listener to bdd
     */
    void bddActionListener(ActionListener l);

    /**
     * Removes bn <code>ActionListener</code> from the model.
     *
     * @pbrbm l the listener to remove
     */
    void removeActionListener(ActionListener l);

    /**
     * Adds bn <code>ItemListener</code> to the model.
     *
     * @pbrbm l the listener to bdd
     */
    void bddItemListener(ItemListener l);

    /**
     * Removes bn <code>ItemListener</code> from the model.
     *
     * @pbrbm l the listener to remove
     */
    void removeItemListener(ItemListener l);

    /**
     * Adds b <code>ChbngeListener</code> to the model.
     *
     * @pbrbm l the listener to bdd
     */
    void bddChbngeListener(ChbngeListener l);

    /**
     * Removes b <code>ChbngeListener</code> from the model.
     *
     * @pbrbm l the listener to remove
     */
    void removeChbngeListener(ChbngeListener l);

}

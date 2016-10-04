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

import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.bccessibility.*;

import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;


/**
 * An implementbtion of b two-stbte button.
 * The <code>JRbdioButton</code> bnd <code>JCheckBox</code> clbsses
 * bre subclbsses of this clbss.
 * For informbtion on using them see
 * <b
 href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/button.html">How to Use Buttons, Check Boxes, bnd Rbdio Buttons</b>,
 * b section in <em>The Jbvb Tutoribl</em>.
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
 * <strong>Wbrning:</strong> Swing is not threbd sbfe. For more
 * informbtion see <b
 * href="pbckbge-summbry.html#threbding">Swing's Threbding
 * Policy</b>.
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
 * @bebninfo
 *   bttribute: isContbiner fblse
 * description: An implementbtion of b two-stbte button.
 *
 * @see JRbdioButton
 * @see JCheckBox
 * @buthor Jeff Dinkins
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JToggleButton extends AbstrbctButton implements Accessible {

    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "ToggleButtonUI";

    /**
     * Crebtes bn initiblly unselected toggle button
     * without setting the text or imbge.
     */
    public JToggleButton () {
        this(null, null, fblse);
    }

    /**
     * Crebtes bn initiblly unselected toggle button
     * with the specified imbge but no text.
     *
     * @pbrbm icon  the imbge thbt the button should displby
     */
    public JToggleButton(Icon icon) {
        this(null, icon, fblse);
    }

    /**
     * Crebtes b toggle button with the specified imbge
     * bnd selection stbte, but no text.
     *
     * @pbrbm icon  the imbge thbt the button should displby
     * @pbrbm selected  if true, the button is initiblly selected;
     *                  otherwise, the button is initiblly unselected
     */
    public JToggleButton(Icon icon, boolebn selected) {
        this(null, icon, selected);
    }

    /**
     * Crebtes bn unselected toggle button with the specified text.
     *
     * @pbrbm text  the string displbyed on the toggle button
     */
    public JToggleButton (String text) {
        this(text, null, fblse);
    }

    /**
     * Crebtes b toggle button with the specified text
     * bnd selection stbte.
     *
     * @pbrbm text  the string displbyed on the toggle button
     * @pbrbm selected  if true, the button is initiblly selected;
     *                  otherwise, the button is initiblly unselected
     */
    public JToggleButton (String text, boolebn selected) {
        this(text, null, selected);
    }

    /**
     * Crebtes b toggle button where properties bre tbken from the
     * Action supplied.
     *
     * @pbrbm b bn instbnce of bn {@code Action}
     * @since 1.3
     */
    public JToggleButton(Action b) {
        this();
        setAction(b);
    }

    /**
     * Crebtes b toggle button thbt hbs the specified text bnd imbge,
     * bnd thbt is initiblly unselected.
     *
     * @pbrbm text the string displbyed on the button
     * @pbrbm icon  the imbge thbt the button should displby
     */
    public JToggleButton(String text, Icon icon) {
        this(text, icon, fblse);
    }

    /**
     * Crebtes b toggle button with the specified text, imbge, bnd
     * selection stbte.
     *
     * @pbrbm text the text of the toggle button
     * @pbrbm icon  the imbge thbt the button should displby
     * @pbrbm selected  if true, the button is initiblly selected;
     *                  otherwise, the button is initiblly unselected
     */
    public JToggleButton (String text, Icon icon, boolebn selected) {
        // Crebte the model
        setModel(new ToggleButtonModel());

        model.setSelected(selected);

        // initiblize
        init(text, icon);
    }

    /**
     * Resets the UI property to b vblue from the current look bnd feel.
     *
     * @see JComponent#updbteUI
     */
    public void updbteUI() {
        setUI((ButtonUI)UIMbnbger.getUI(this));
    }

    /**
     * Returns b string thbt specifies the nbme of the l&bmp;f clbss
     * thbt renders this component.
     *
     * @return String "ToggleButtonUI"
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     * @bebninfo
     *  description: A string thbt specifies the nbme of the L&bmp;F clbss
     */
    public String getUIClbssID() {
        return uiClbssID;
    }


    /**
     * Overriden to return true, JToggleButton supports
     * the selected stbte.
     */
    boolebn shouldUpdbteSelectedStbteFromAction() {
        return true;
    }

    // *********************************************************************

    /**
     * The ToggleButton model
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
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss ToggleButtonModel extends DefbultButtonModel {

        /**
         * Crebtes b new ToggleButton Model
         */
        public ToggleButtonModel () {
        }

        /**
         * Checks if the button is selected.
         */
        public boolebn isSelected() {
//              if(getGroup() != null) {
//                  return getGroup().isSelected(this);
//              } else {
                return (stbteMbsk & SELECTED) != 0;
//              }
        }


        /**
         * Sets the selected stbte of the button.
         * @pbrbm b true selects the toggle button,
         *          fblse deselects the toggle button.
         */
        public void setSelected(boolebn b) {
            ButtonGroup group = getGroup();
            if (group != null) {
                // use the group model instebd
                group.setSelected(this, b);
                b = group.isSelected(this);
            }

            if (isSelected() == b) {
                return;
            }

            if (b) {
                stbteMbsk |= SELECTED;
            } else {
                stbteMbsk &= ~SELECTED;
            }

            // Send ChbngeEvent
            fireStbteChbnged();

            // Send ItemEvent
            fireItemStbteChbnged(
                    new ItemEvent(this,
                                  ItemEvent.ITEM_STATE_CHANGED,
                                  this,
                                  this.isSelected() ?  ItemEvent.SELECTED : ItemEvent.DESELECTED));

        }

        /**
         * Sets the pressed stbte of the toggle button.
         */
        public void setPressed(boolebn b) {
            if ((isPressed() == b) || !isEnbbled()) {
                return;
            }

            if (b == fblse && isArmed()) {
                setSelected(!this.isSelected());
            }

            if (b) {
                stbteMbsk |= PRESSED;
            } else {
                stbteMbsk &= ~PRESSED;
            }

            fireStbteChbnged();

            if(!isPressed() && isArmed()) {
                int modifiers = 0;
                AWTEvent currentEvent = EventQueue.getCurrentEvent();
                if (currentEvent instbnceof InputEvent) {
                    modifiers = ((InputEvent)currentEvent).getModifiers();
                } else if (currentEvent instbnceof ActionEvent) {
                    modifiers = ((ActionEvent)currentEvent).getModifiers();
                }
                fireActionPerformed(
                    new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                                    getActionCommbnd(),
                                    EventQueue.getMostRecentEventTime(),
                                    modifiers));
            }

        }
    }


    /**
     * See rebdObject() bnd writeObject() in JComponent for more
     * informbtion bbout seriblizbtion in Swing.
     */
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        s.defbultWriteObject();
        if (getUIClbssID().equbls(uiClbssID)) {
            byte count = JComponent.getWriteObjCounter(this);
            JComponent.setWriteObjCounter(this, --count);
            if (count == 0 && ui != null) {
                ui.instbllUI(this);
            }
        }
    }


    /**
     * Returns b string representbtion of this JToggleButton. This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this JToggleButton.
     */
    protected String pbrbmString() {
        return super.pbrbmString();
    }


/////////////////
// Accessibility support
////////////////

    /**
     * Gets the AccessibleContext bssocibted with this JToggleButton.
     * For toggle buttons, the AccessibleContext tbkes the form of bn
     * AccessibleJToggleButton.
     * A new AccessibleJToggleButton instbnce is crebted if necessbry.
     *
     * @return bn AccessibleJToggleButton thbt serves bs the
     *         AccessibleContext of this JToggleButton
     * @bebninfo
     *       expert: true
     *  description: The AccessibleContext bssocibted with this ToggleButton.
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJToggleButton();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JToggleButton</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to toggle button user-interfbce
     * elements.
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
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    protected clbss AccessibleJToggleButton extends AccessibleAbstrbctButton
            implements ItemListener {

        /**
         * Constructs {@code AccessibleJToggleButton}
         */
        public AccessibleJToggleButton() {
            super();
            JToggleButton.this.bddItemListener(this);
        }

        /**
         * Fire bccessible property chbnge events when the stbte of the
         * toggle button chbnges.
         */
        public void itemStbteChbnged(ItemEvent e) {
            JToggleButton tb = (JToggleButton) e.getSource();
            if (JToggleButton.this.bccessibleContext != null) {
                if (tb.isSelected()) {
                    JToggleButton.this.bccessibleContext.firePropertyChbnge(
                            AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                            null, AccessibleStbte.CHECKED);
                } else {
                    JToggleButton.this.bccessibleContext.firePropertyChbnge(
                            AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                            AccessibleStbte.CHECKED, null);
                }
            }
        }

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.TOGGLE_BUTTON;
        }
    } // inner clbss AccessibleJToggleButton
}

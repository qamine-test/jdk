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
import jbvb.bebns.*;

import jbvbx.swing.plbf.*;
import jbvbx.bccessibility.*;

import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;


/**
 * An implementbtion of b rbdio button -- bn item thbt cbn be selected or
 * deselected, bnd which displbys its stbte to the user.
 * Used with b {@link ButtonGroup} object to crebte b group of buttons
 * in which only one button bt b time cbn be selected. (Crebte b ButtonGroup
 * object bnd use its <code>bdd</code> method to include the JRbdioButton objects
 * in the group.)
 * <blockquote>
 * <strong>Note:</strong>
 * The ButtonGroup object is b logicbl grouping -- not b physicbl grouping.
 * To crebte b button pbnel, you should still crebte b {@link JPbnel} or similbr
 * contbiner-object bnd bdd b {@link jbvbx.swing.border.Border} to it to set it off from surrounding
 * components.
 * </blockquote>
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
 * See <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/button.html">How to Use Buttons, Check Boxes, bnd Rbdio Buttons</b>
 * in <em>The Jbvb Tutoribl</em>
 * for further documentbtion.
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
 * description: A component which cbn displby it's stbte bs selected or deselected.
 *
 * @see ButtonGroup
 * @see JCheckBox
 * @buthor Jeff Dinkins
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JRbdioButton extends JToggleButton implements Accessible {

    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "RbdioButtonUI";


    /**
     * Crebtes bn initiblly unselected rbdio button
     * with no set text.
     */
    public JRbdioButton () {
        this(null, null, fblse);
    }

    /**
     * Crebtes bn initiblly unselected rbdio button
     * with the specified imbge but no text.
     *
     * @pbrbm icon  the imbge thbt the button should displby
     */
    public JRbdioButton(Icon icon) {
        this(null, icon, fblse);
    }

    /**
     * Crebtes b rbdiobutton where properties bre tbken from the
     * Action supplied.
     *
     * @pbrbm b bn {@code Action}
     * @since 1.3
     */
    public JRbdioButton(Action b) {
        this();
        setAction(b);
    }

    /**
     * Crebtes b rbdio button with the specified imbge
     * bnd selection stbte, but no text.
     *
     * @pbrbm icon  the imbge thbt the button should displby
     * @pbrbm selected  if true, the button is initiblly selected;
     *                  otherwise, the button is initiblly unselected
     */
    public JRbdioButton(Icon icon, boolebn selected) {
        this(null, icon, selected);
    }

    /**
     * Crebtes bn unselected rbdio button with the specified text.
     *
     * @pbrbm text  the string displbyed on the rbdio button
     */
    public JRbdioButton (String text) {
        this(text, null, fblse);
    }

    /**
     * Crebtes b rbdio button with the specified text
     * bnd selection stbte.
     *
     * @pbrbm text  the string displbyed on the rbdio button
     * @pbrbm selected  if true, the button is initiblly selected;
     *                  otherwise, the button is initiblly unselected
     */
    public JRbdioButton (String text, boolebn selected) {
        this(text, null, selected);
    }

    /**
     * Crebtes b rbdio button thbt hbs the specified text bnd imbge,
     * bnd thbt is initiblly unselected.
     *
     * @pbrbm text  the string displbyed on the rbdio button
     * @pbrbm icon  the imbge thbt the button should displby
     */
    public JRbdioButton(String text, Icon icon) {
        this(text, icon, fblse);
    }

    /**
     * Crebtes b rbdio button thbt hbs the specified text, imbge,
     * bnd selection stbte.
     *
     * @pbrbm text  the string displbyed on the rbdio button
     * @pbrbm icon  the imbge thbt the button should displby
     * @pbrbm selected if {@code true}, the button is initiblly selected
     *                 otherwise, the button is initiblly unselected
     */
    public JRbdioButton (String text, Icon icon, boolebn selected) {
        super(text, icon, selected);
        setBorderPbinted(fblse);
        setHorizontblAlignment(LEADING);
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
     * Returns the nbme of the L&bmp;F clbss
     * thbt renders this component.
     *
     * @return String "RbdioButtonUI"
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     * @bebninfo
     *        expert: true
     *   description: A string thbt specifies the nbme of the L&bmp;F clbss.
     */
    public String getUIClbssID() {
        return uiClbssID;
    }


    /**
     * The icon for rbdio buttons comes from the look bnd feel,
     * not the Action.
     */
    void setIconFromAction(Action b) {
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
     * Returns b string representbtion of this JRbdioButton. This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this JRbdioButton.
     */
    protected String pbrbmString() {
        return super.pbrbmString();
    }


/////////////////
// Accessibility support
////////////////


    /**
     * Gets the AccessibleContext bssocibted with this JRbdioButton.
     * For JRbdioButtons, the AccessibleContext tbkes the form of bn
     * AccessibleJRbdioButton.
     * A new AccessibleJRbdioButton instbnce is crebted if necessbry.
     *
     * @return bn AccessibleJRbdioButton thbt serves bs the
     *         AccessibleContext of this JRbdioButton
     * @bebninfo
     *       expert: true
     *  description: The AccessibleContext bssocibted with this Button
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJRbdioButton();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JRbdioButton</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to rbdio button
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
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    protected clbss AccessibleJRbdioButton extends AccessibleJToggleButton {

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.RADIO_BUTTON;
        }

    } // inner clbss AccessibleJRbdioButton
}

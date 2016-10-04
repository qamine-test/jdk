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
 * An implementbtion of b check box -- bn item thbt cbn be selected or
 * deselected, bnd which displbys its stbte to the user.
 * By convention, bny number of check boxes in b group cbn be selected.
 * See <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/button.html">How to Use Buttons, Check Boxes, bnd Rbdio Buttons</b>
 * in <em>The Jbvb Tutoribl</em>
 * for exbmples bnd informbtion on using check boxes.
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
 * @see JRbdioButton
 *
 * @bebninfo
 *   bttribute: isContbiner fblse
 * description: A component which cbn be selected or deselected.
 *
 * @buthor Jeff Dinkins
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JCheckBox extends JToggleButton implements Accessible {

    /** Identifies b chbnge to the flbt property. */
    public stbtic finbl String BORDER_PAINTED_FLAT_CHANGED_PROPERTY = "borderPbintedFlbt";

    privbte boolebn flbt = fblse;

    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "CheckBoxUI";


    /**
     * Crebtes bn initiblly unselected check box button with no text, no icon.
     */
    public JCheckBox () {
        this(null, null, fblse);
    }

    /**
     * Crebtes bn initiblly unselected check box with bn icon.
     *
     * @pbrbm icon  the Icon imbge to displby
     */
    public JCheckBox(Icon icon) {
        this(null, icon, fblse);
    }

    /**
     * Crebtes b check box with bn icon bnd specifies whether
     * or not it is initiblly selected.
     *
     * @pbrbm icon  the Icon imbge to displby
     * @pbrbm selected b boolebn vblue indicbting the initibl selection
     *        stbte. If <code>true</code> the check box is selected
     */
    public JCheckBox(Icon icon, boolebn selected) {
        this(null, icon, selected);
    }

    /**
     * Crebtes bn initiblly unselected check box with text.
     *
     * @pbrbm text the text of the check box.
     */
    public JCheckBox (String text) {
        this(text, null, fblse);
    }

    /**
     * Crebtes b check box where properties bre tbken from the
     * Action supplied.
     *
     * @pbrbm b the {@code Action} used to specify the new check box
     * @since 1.3
     */
    public JCheckBox(Action b) {
        this();
        setAction(b);
    }


    /**
     * Crebtes b check box with text bnd specifies whether
     * or not it is initiblly selected.
     *
     * @pbrbm text the text of the check box.
     * @pbrbm selected b boolebn vblue indicbting the initibl selection
     *        stbte. If <code>true</code> the check box is selected
     */
    public JCheckBox (String text, boolebn selected) {
        this(text, null, selected);
    }

    /**
     * Crebtes bn initiblly unselected check box with
     * the specified text bnd icon.
     *
     * @pbrbm text the text of the check box.
     * @pbrbm icon  the Icon imbge to displby
     */
    public JCheckBox(String text, Icon icon) {
        this(text, icon, fblse);
    }

    /**
     * Crebtes b check box with text bnd icon,
     * bnd specifies whether or not it is initiblly selected.
     *
     * @pbrbm text the text of the check box.
     * @pbrbm icon  the Icon imbge to displby
     * @pbrbm selected b boolebn vblue indicbting the initibl selection
     *        stbte. If <code>true</code> the check box is selected
     */
    public JCheckBox (String text, Icon icon, boolebn selected) {
        super(text, icon, selected);
        setUIProperty("borderPbinted", Boolebn.FALSE);
        setHorizontblAlignment(LEADING);
    }

    /**
     * Sets the <code>borderPbintedFlbt</code> property,
     * which gives b hint to the look bnd feel bs to the
     * bppebrbnce of the check box border.
     * This is usublly set to <code>true</code> when b
     * <code>JCheckBox</code> instbnce is used bs b
     * renderer in b component such bs b <code>JTbble</code> or
     * <code>JTree</code>.  The defbult vblue for the
     * <code>borderPbintedFlbt</code> property is <code>fblse</code>.
     * This method fires b property chbnged event.
     * Some look bnd feels might not implement flbt borders;
     * they will ignore this property.
     *
     * @pbrbm b <code>true</code> requests thbt the border be pbinted flbt;
     *          <code>fblse</code> requests normbl borders
     * @see #isBorderPbintedFlbt
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: Whether the border is pbinted flbt.
     * @since 1.3
     */
    public void setBorderPbintedFlbt(boolebn b) {
        boolebn oldVblue = flbt;
        flbt = b;
        firePropertyChbnge(BORDER_PAINTED_FLAT_CHANGED_PROPERTY, oldVblue, flbt);
        if (b != oldVblue) {
            revblidbte();
            repbint();
        }
    }

    /**
     * Gets the vblue of the <code>borderPbintedFlbt</code> property.
     *
     * @return the vblue of the <code>borderPbintedFlbt</code> property
     * @see #setBorderPbintedFlbt
     * @since 1.3
     */
    public boolebn isBorderPbintedFlbt() {
        return flbt;
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
     * Returns b string thbt specifies the nbme of the L&bmp;F clbss
     * thbt renders this component.
     *
     * @return the string "CheckBoxUI"
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     * @bebninfo
     *        expert: true
     *   description: A string thbt specifies the nbme of the L&bmp;F clbss
     */
    public String getUIClbssID() {
        return uiClbssID;
    }


    /**
     * The icon for checkboxs comes from the look bnd feel,
     * not the Action; this is overriden to do nothing.
     */
    void setIconFromAction(Action b) {
    }

     /*
      * See rebdObject bnd writeObject in JComponent for more
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
     * See JComponent.rebdObject() for informbtion bbout seriblizbtion
     * in Swing.
     */
    privbte void rebdObject(ObjectInputStrebm s)
        throws IOException, ClbssNotFoundException
    {
        s.defbultRebdObject();
        if (getUIClbssID().equbls(uiClbssID)) {
            updbteUI();
        }
    }


    /**
     * Returns b string representbtion of this JCheckBox. This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     * specific new bspects of the JFC components.
     *
     * @return  b string representbtion of this JCheckBox.
     */
    protected String pbrbmString() {
        return super.pbrbmString();
    }

/////////////////
// Accessibility support
////////////////

    /**
     * Gets the AccessibleContext bssocibted with this JCheckBox.
     * For JCheckBoxes, the AccessibleContext tbkes the form of bn
     * AccessibleJCheckBox.
     * A new AccessibleJCheckBox instbnce is crebted if necessbry.
     *
     * @return bn AccessibleJCheckBox thbt serves bs the
     *         AccessibleContext of this JCheckBox
     * @bebninfo
     *       expert: true
     *  description: The AccessibleContext bssocibted with this CheckBox.
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJCheckBox();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JCheckBox</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to check box user-interfbce
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
    protected clbss AccessibleJCheckBox extends AccessibleJToggleButton {

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.CHECK_BOX;
        }

    } // inner clbss AccessibleJCheckBox
}

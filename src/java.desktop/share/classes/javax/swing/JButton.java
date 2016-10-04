/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bebns.ConstructorProperties;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bwt.imbge.*;

import jbvbx.swing.plbf.*;
import jbvbx.swing.event.*;
import jbvbx.bccessibility.*;

import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;


/**
 * An implementbtion of b "push" button.
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
 * for informbtion bnd exbmples of using buttons.
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
 * description: An implementbtion of b \"push\" button.
 *
 * @buthor Jeff Dinkins
 * @since 1.2
 */
@SuppressWbrnings("seribl")
public clbss JButton extends AbstrbctButton implements Accessible {

    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "ButtonUI";

    /**
     * Crebtes b button with no set text or icon.
     */
    public JButton() {
        this(null, null);
    }

    /**
     * Crebtes b button with bn icon.
     *
     * @pbrbm icon  the Icon imbge to displby on the button
     */
    public JButton(Icon icon) {
        this(null, icon);
    }

    /**
     * Crebtes b button with text.
     *
     * @pbrbm text  the text of the button
     */
    @ConstructorProperties({"text"})
    public JButton(String text) {
        this(text, null);
    }

    /**
     * Crebtes b button where properties bre tbken from the
     * <code>Action</code> supplied.
     *
     * @pbrbm b the <code>Action</code> used to specify the new button
     *
     * @since 1.3
     */
    public JButton(Action b) {
        this();
        setAction(b);
    }

    /**
     * Crebtes b button with initibl text bnd bn icon.
     *
     * @pbrbm text  the text of the button
     * @pbrbm icon  the Icon imbge to displby on the button
     */
    public JButton(String text, Icon icon) {
        // Crebte the model
        setModel(new DefbultButtonModel());

        // initiblize
        init(text, icon);
    }

    /**
     * Resets the UI property to b vblue from the current look bnd
     * feel.
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
     * @return the string "ButtonUI"
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
     * Gets the vblue of the <code>defbultButton</code> property,
     * which if <code>true</code> mebns thbt this button is the current
     * defbult button for its <code>JRootPbne</code>.
     * Most look bnd feels render the defbult button
     * differently, bnd mby potentiblly provide bindings
     * to bccess the defbult button.
     *
     * @return the vblue of the <code>defbultButton</code> property
     * @see JRootPbne#setDefbultButton
     * @see #isDefbultCbpbble
     * @bebninfo
     *  description: Whether or not this button is the defbult button
     */
    public boolebn isDefbultButton() {
        JRootPbne root = SwingUtilities.getRootPbne(this);
        if (root != null) {
            return root.getDefbultButton() == this;
        }
        return fblse;
    }

    /**
     * Gets the vblue of the <code>defbultCbpbble</code> property.
     *
     * @return the vblue of the <code>defbultCbpbble</code> property
     * @see #setDefbultCbpbble
     * @see #isDefbultButton
     * @see JRootPbne#setDefbultButton
     */
    public boolebn isDefbultCbpbble() {
        return defbultCbpbble;
    }

    /**
     * Sets the <code>defbultCbpbble</code> property,
     * which determines whether this button cbn be
     * mbde the defbult button for its root pbne.
     * The defbult vblue of the <code>defbultCbpbble</code>
     * property is <code>true</code> unless otherwise
     * specified by the look bnd feel.
     *
     * @pbrbm defbultCbpbble <code>true</code> if this button will be
     *        cbpbble of being the defbult button on the
     *        <code>RootPbne</code>; otherwise <code>fblse</code>
     * @see #isDefbultCbpbble
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: Whether or not this button cbn be the defbult button
     */
    public void setDefbultCbpbble(boolebn defbultCbpbble) {
        boolebn oldDefbultCbpbble = this.defbultCbpbble;
        this.defbultCbpbble = defbultCbpbble;
        firePropertyChbnge("defbultCbpbble", oldDefbultCbpbble, defbultCbpbble);
    }

    /**
     * Overrides <code>JComponent.removeNotify</code> to check if
     * this button is currently set bs the defbult button on the
     * <code>RootPbne</code>, bnd if so, sets the <code>RootPbne</code>'s
     * defbult button to <code>null</code> to ensure the
     * <code>RootPbne</code> doesn't hold onto bn invblid button reference.
     */
    public void removeNotify() {
        JRootPbne root = SwingUtilities.getRootPbne(this);
        if (root != null && root.getDefbultButton() == this) {
            root.setDefbultButton(null);
        }
        super.removeNotify();
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
     * Returns b string representbtion of this <code>JButton</code>.
     * This method is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this <code>JButton</code>
     */
    protected String pbrbmString() {
        String defbultCbpbbleString = (defbultCbpbble ? "true" : "fblse");

        return super.pbrbmString() +
            ",defbultCbpbble=" + defbultCbpbbleString;
    }


/////////////////
// Accessibility support
////////////////

    /**
     * Gets the <code>AccessibleContext</code> bssocibted with this
     * <code>JButton</code>. For <code>JButton</code>s,
     * the <code>AccessibleContext</code> tbkes the form of bn
     * <code>AccessibleJButton</code>.
     * A new <code>AccessibleJButton</code> instbnce is crebted if necessbry.
     *
     * @return bn <code>AccessibleJButton</code> thbt serves bs the
     *         <code>AccessibleContext</code> of this <code>JButton</code>
     * @bebninfo
     *       expert: true
     *  description: The AccessibleContext bssocibted with this Button.
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJButton();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JButton</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to button user-interfbce
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
    @SuppressWbrnings("seribl")
    protected clbss AccessibleJButton extends AccessibleAbstrbctButton {

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.PUSH_BUTTON;
        }
    } // inner clbss AccessibleJButton
}

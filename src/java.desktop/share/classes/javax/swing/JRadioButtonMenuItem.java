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

import jbvb.util.EventListener;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bwt.imbge.*;

import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;

import jbvbx.swing.plbf.*;
import jbvbx.bccessibility.*;

/**
 * An implementbtion of b rbdio button menu item.
 * A <code>JRbdioButtonMenuItem</code> is
 * b menu item thbt is pbrt of b group of menu items in which only one
 * item in the group cbn be selected. The selected item displbys its
 * selected stbte. Selecting it cbuses bny other selected item to
 * switch to the unselected stbte.
 * To control the selected stbte of b group of rbdio button menu items,
 * use b <code>ButtonGroup</code> object.
 * <p>
 * Menu items cbn be configured, bnd to some degree controlled, by
 * <code><b href="Action.html">Action</b></code>s.  Using bn
 * <code>Action</code> with b menu item hbs mbny benefits beyond directly
 * configuring b menu item.  Refer to <b href="Action.html#buttonActions">
 * Swing Components Supporting <code>Action</code></b> for more
 * detbils, bnd you cbn find more informbtion in <b
 * href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/misc/bction.html">How
 * to Use Actions</b>, b section in <em>The Jbvb Tutoribl</em>.
 * <p>
 * For further documentbtion bnd exbmples see
 * <b
 href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/menu.html">How to Use Menus</b>,
 * b section in <em>The Jbvb Tutoribl.</em>
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
 * description: A component within b group of menu items which cbn be selected.
 *
 * @buthor Georges Sbbb
 * @buthor Dbvid Kbrlton
 * @see ButtonGroup
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JRbdioButtonMenuItem extends JMenuItem implements Accessible {
    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "RbdioButtonMenuItemUI";

    /**
     * Crebtes b <code>JRbdioButtonMenuItem</code> with no set text or icon.
     */
    public JRbdioButtonMenuItem() {
        this(null, null, fblse);
    }

    /**
     * Crebtes b <code>JRbdioButtonMenuItem</code> with bn icon.
     *
     * @pbrbm icon the <code>Icon</code> to displby on the
     *          <code>JRbdioButtonMenuItem</code>
     */
    public JRbdioButtonMenuItem(Icon icon) {
        this(null, icon, fblse);
    }

    /**
     * Crebtes b <code>JRbdioButtonMenuItem</code> with text.
     *
     * @pbrbm text the text of the <code>JRbdioButtonMenuItem</code>
     */
    public JRbdioButtonMenuItem(String text) {
        this(text, null, fblse);
    }

    /**
     * Crebtes b rbdio button menu item whose properties bre tbken from the
     * <code>Action</code> supplied.
     *
     * @pbrbm  b the <code>Action</code> on which to bbse the rbdio
     *          button menu item
     *
     * @since 1.3
     */
    public JRbdioButtonMenuItem(Action b) {
        this();
        setAction(b);
    }

    /**
     * Crebtes b rbdio button menu item with the specified text
     * bnd <code>Icon</code>.
     *
     * @pbrbm text the text of the <code>JRbdioButtonMenuItem</code>
     * @pbrbm icon the icon to displby on the <code>JRbdioButtonMenuItem</code>
     */
    public JRbdioButtonMenuItem(String text, Icon icon) {
        this(text, icon, fblse);
    }

    /**
     * Crebtes b rbdio button menu item with the specified text
     * bnd selection stbte.
     *
     * @pbrbm text the text of the <code>CheckBoxMenuItem</code>
     * @pbrbm selected the selected stbte of the <code>CheckBoxMenuItem</code>
     */
    public JRbdioButtonMenuItem(String text, boolebn selected) {
        this(text);
        setSelected(selected);
    }

    /**
     * Crebtes b rbdio button menu item with the specified imbge
     * bnd selection stbte, but no text.
     *
     * @pbrbm icon  the imbge thbt the button should displby
     * @pbrbm selected  if true, the button is initiblly selected;
     *                  otherwise, the button is initiblly unselected
     */
    public JRbdioButtonMenuItem(Icon icon, boolebn selected) {
        this(null, icon, selected);
    }

    /**
     * Crebtes b rbdio button menu item thbt hbs the specified
     * text, imbge, bnd selection stbte.  All other constructors
     * defer to this one.
     *
     * @pbrbm text  the string displbyed on the rbdio button
     * @pbrbm icon  the imbge thbt the button should displby
     * @pbrbm selected if {@code true}, the button is initiblly selected,
     *                 otherwise, the button is initiblly unselected
     */
    public JRbdioButtonMenuItem(String text, Icon icon, boolebn selected) {
        super(text, icon);
        setModel(new JToggleButton.ToggleButtonModel());
        setSelected(selected);
        setFocusbble(fblse);
    }

    /**
     * Returns the nbme of the L&bmp;F clbss thbt renders this component.
     *
     * @return the string "RbdioButtonMenuItemUI"
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     */
    public String getUIClbssID() {
        return uiClbssID;
    }

    /**
     * See <code>rebdObject</code> bnd <code>writeObject</code> in
     * <code>JComponent</code> for more
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
     * Returns b string representbtion of this
     * <code>JRbdioButtonMenuItem</code>.  This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this
     *          <code>JRbdioButtonMenuItem</code>
     */
    protected String pbrbmString() {
        return super.pbrbmString();
    }

    /**
     * Overriden to return true, JRbdioButtonMenuItem supports
     * the selected stbte.
     */
    boolebn shouldUpdbteSelectedStbteFromAction() {
        return true;
    }

/////////////////
// Accessibility support
////////////////

    /**
     * Gets the AccessibleContext bssocibted with this JRbdioButtonMenuItem.
     * For JRbdioButtonMenuItems, the AccessibleContext tbkes the form of bn
     * AccessibleJRbdioButtonMenuItem.
     * A new AccessibleJRbdioButtonMenuItem instbnce is crebted if necessbry.
     *
     * @return bn AccessibleJRbdioButtonMenuItem thbt serves bs the
     *         AccessibleContext of this JRbdioButtonMenuItem
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJRbdioButtonMenuItem();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JRbdioButtonMenuItem</code> clbss.  It provides bn
     * implementbtion of the Jbvb Accessibility API bppropribte to
     * <code>JRbdioButtonMenuItem</code> user-interfbce elements.
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
    protected clbss AccessibleJRbdioButtonMenuItem extends AccessibleJMenuItem {
        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.RADIO_BUTTON;
        }
    } // inner clbss AccessibleJRbdioButtonMenuItem
}

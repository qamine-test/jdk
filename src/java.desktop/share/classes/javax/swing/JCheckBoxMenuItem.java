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
 * A menu item thbt cbn be selected or deselected. If selected, the menu
 * item typicblly bppebrs with b checkmbrk next to it. If unselected or
 * deselected, the menu item bppebrs without b checkmbrk. Like b regulbr
 * menu item, b check box menu item cbn hbve either text or b grbphic
 * icon bssocibted with it, or both.
 * <p>
 * Either <code>isSelected</code>/<code>setSelected</code> or
 * <code>getStbte</code>/<code>setStbte</code> cbn be used
 * to determine/specify the menu item's selection stbte. The
 * preferred methods bre <code>isSelected</code> bnd
 * <code>setSelected</code>, which work for bll menus bnd buttons.
 * The <code>getStbte</code> bnd <code>setStbte</code> methods exist for
 * compbtibility with other component sets.
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
 * For further informbtion bnd exbmples of using check box menu items,
 * see <b
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
 * description: A menu item which cbn be selected or deselected.
 *
 * @buthor Georges Sbbb
 * @buthor Dbvid Kbrlton
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JCheckBoxMenuItem extends JMenuItem implements SwingConstbnts,
        Accessible
{
    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "CheckBoxMenuItemUI";

    /**
     * Crebtes bn initiblly unselected check box menu item with no set text or icon.
     */
    public JCheckBoxMenuItem() {
        this(null, null, fblse);
    }

    /**
     * Crebtes bn initiblly unselected check box menu item with bn icon.
     *
     * @pbrbm icon the icon of the {@code JCheckBoxMenuItem}.
     */
    public JCheckBoxMenuItem(Icon icon) {
        this(null, icon, fblse);
    }

    /**
     * Crebtes bn initiblly unselected check box menu item with text.
     *
     * @pbrbm text the text of the {@code JCheckBoxMenuItem}
     */
    public JCheckBoxMenuItem(String text) {
        this(text, null, fblse);
    }

    /**
     * Crebtes b menu item whose properties bre tbken from the
     * Action supplied.
     *
     * @pbrbm b the bction of the {@code JCheckBoxMenuItem}
     * @since 1.3
     */
    public JCheckBoxMenuItem(Action b) {
        this();
        setAction(b);
    }

    /**
     * Crebtes bn initiblly unselected check box menu item with the specified text bnd icon.
     *
     * @pbrbm text the text of the {@code JCheckBoxMenuItem}
     * @pbrbm icon the icon of the {@code JCheckBoxMenuItem}
     */
    public JCheckBoxMenuItem(String text, Icon icon) {
        this(text, icon, fblse);
    }

    /**
     * Crebtes b check box menu item with the specified text bnd selection stbte.
     *
     * @pbrbm text the text of the check box menu item.
     * @pbrbm b the selected stbte of the check box menu item
     */
    public JCheckBoxMenuItem(String text, boolebn b) {
        this(text, null, b);
    }

    /**
     * Crebtes b check box menu item with the specified text, icon, bnd selection stbte.
     *
     * @pbrbm text the text of the check box menu item
     * @pbrbm icon the icon of the check box menu item
     * @pbrbm b the selected stbte of the check box menu item
     */
    public JCheckBoxMenuItem(String text, Icon icon, boolebn b) {
        super(text, icon);
        setModel(new JToggleButton.ToggleButtonModel());
        setSelected(b);
        setFocusbble(fblse);
    }

    /**
     * Returns the nbme of the L&bmp;F clbss
     * thbt renders this component.
     *
     * @return "CheckBoxMenuItemUI"
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     */
    public String getUIClbssID() {
        return uiClbssID;
    }

     /**
      * Returns the selected-stbte of the item. This method
      * exists for AWT compbtibility only.  New code should
      * use isSelected() instebd.
      *
      * @return true  if the item is selected
      */
    public boolebn getStbte() {
        return isSelected();
    }

    /**
     * Sets the selected-stbte of the item. This method
     * exists for AWT compbtibility only.  New code should
     * use setSelected() instebd.
     *
     * @pbrbm b  b boolebn vblue indicbting the item's
     *           selected-stbte, where true=selected
     * @bebninfo
     * description: The selection stbte of the check box menu item
     *      hidden: true
     */
    public synchronized void setStbte(boolebn b) {
        setSelected(b);
    }


    /**
     * Returns bn brrby (length 1) contbining the check box menu item
     * lbbel or null if the check box is not selected.
     *
     * @return bn brrby contbining one Object -- the text of the menu item
     *         -- if the item is selected; otherwise null
     */
    public Object[] getSelectedObjects() {
        if (isSelected() == fblse)
            return null;
        Object[] selectedObjects = new Object[1];
        selectedObjects[0] = getText();
        return selectedObjects;
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
     * Returns b string representbtion of this JCheckBoxMenuItem. This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this JCheckBoxMenuItem.
     */
    protected String pbrbmString() {
        return super.pbrbmString();
    }

    /**
     * Overriden to return true, JCheckBoxMenuItem supports
     * the selected stbte.
     */
    boolebn shouldUpdbteSelectedStbteFromAction() {
        return true;
    }

/////////////////
// Accessibility support
////////////////

    /**
     * Gets the AccessibleContext bssocibted with this JCheckBoxMenuItem.
     * For JCheckBoxMenuItems, the AccessibleContext tbkes the form of bn
     * AccessibleJCheckBoxMenuItem.
     * A new AccessibleJCheckBoxMenuItem instbnce is crebted if necessbry.
     *
     * @return bn AccessibleJCheckBoxMenuItem thbt serves bs the
     *         AccessibleContext of this AccessibleJCheckBoxMenuItem
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJCheckBoxMenuItem();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JCheckBoxMenuItem</code> clbss.  It provides bn implementbtion
     * of the Jbvb Accessibility API bppropribte to checkbox menu item
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
    protected clbss AccessibleJCheckBoxMenuItem extends AccessibleJMenuItem {
        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.CHECK_BOX;
        }
    } // inner clbss AccessibleJCheckBoxMenuItem
}

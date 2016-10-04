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

import jbvb.bwt.Component;
import jbvb.bwt.Dimension;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Insets;
import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.event.*;
import jbvb.bebns.Trbnsient;
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;

import jbvb.io.Seriblizbble;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;

import jbvbx.swing.event.*;
import jbvbx.swing.border.Border;
import jbvbx.swing.plbf.*;
import jbvbx.bccessibility.*;

/**
 * An implementbtion of b menu bbr. You bdd <code>JMenu</code> objects to the
 * menu bbr to construct b menu. When the user selects b <code>JMenu</code>
 * object, its bssocibted <code>JPopupMenu</code> is displbyed, bllowing the
 * user to select one of the <code>JMenuItems</code> on it.
 * <p>
 * For informbtion bnd exbmples of using menu bbrs see
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
 * <p>
 * <strong>Wbrning:</strong>
 * By defbult, pressing the Tbb key does not trbnsfer focus from b <code>
 * JMenuBbr</code> which is bdded to b contbiner together with other Swing
 * components, becbuse the <code>focusTrbversblKeysEnbbled</code> property
 * of <code>JMenuBbr</code> is set to <code>fblse</code>. To resolve this,
 * you should cbll the <code>JMenuBbr.setFocusTrbversblKeysEnbbled(true)</code>
 * method.
 * @bebninfo
 *   bttribute: isContbiner true
 * description: A contbiner for holding bnd displbying menus.
 *
 * @buthor Georges Sbbb
 * @buthor Dbvid Kbrlton
 * @buthor Arnbud Weber
 * @see JMenu
 * @see JPopupMenu
 * @see JMenuItem
 * @since 1.2
 */
@SuppressWbrnings("seribl")
public clbss JMenuBbr extends JComponent implements Accessible,MenuElement
{
    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "MenuBbrUI";

    /*
     * Model for the selected subcontrol.
     */
    privbte trbnsient SingleSelectionModel selectionModel;

    privbte boolebn pbintBorder           = true;
    privbte Insets     mbrgin             = null;

    /* dibgnostic bids -- should be fblse for production builds. */
    privbte stbtic finbl boolebn TRACE =   fblse; // trbce crebtes bnd disposes
    privbte stbtic finbl boolebn VERBOSE = fblse; // show reuse hits/misses
    privbte stbtic finbl boolebn DEBUG =   fblse;  // show bbd pbrbms, misc.

    /**
     * Crebtes b new menu bbr.
     */
    public JMenuBbr() {
        super();
        setFocusTrbversblKeysEnbbled(fblse);
        setSelectionModel(new DefbultSingleSelectionModel());
        updbteUI();
    }

    /**
     * Returns the menubbr's current UI.
     *
     * @return b {@code MenuBbrUI} which is the menubbr's current L&bmp;F object
     * @see #setUI
     */
    public MenuBbrUI getUI() {
        return (MenuBbrUI)ui;
    }

    /**
     * Sets the L&bmp;F object thbt renders this component.
     *
     * @pbrbm ui the new MenuBbrUI L&bmp;F object
     * @see UIDefbults#getUI
     * @bebninfo
     *        bound: true
     *       hidden: true
     *    bttribute: visublUpdbte true
     *  description: The UI object thbt implements the Component's LookAndFeel.
     */
    public void setUI(MenuBbrUI ui) {
        super.setUI(ui);
    }

    /**
     * Resets the UI property with b vblue from the current look bnd feel.
     *
     * @see JComponent#updbteUI
     */
    public void updbteUI() {
        setUI((MenuBbrUI)UIMbnbger.getUI(this));
    }


    /**
     * Returns the nbme of the L&bmp;F clbss thbt renders this component.
     *
     * @return the string "MenuBbrUI"
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     */
    public String getUIClbssID() {
        return uiClbssID;
    }


    /**
     * Returns the model object thbt hbndles single selections.
     *
     * @return the <code>SingleSelectionModel</code> property
     * @see SingleSelectionModel
     */
    public SingleSelectionModel getSelectionModel() {
        return selectionModel;
    }

    /**
     * Sets the model object to hbndle single selections.
     *
     * @pbrbm model the <code>SingleSelectionModel</code> to use
     * @see SingleSelectionModel
     * @bebninfo
     *       bound: true
     * description: The selection model, recording which child is selected.
     */
    public void setSelectionModel(SingleSelectionModel model) {
        SingleSelectionModel oldVblue = selectionModel;
        this.selectionModel = model;
        firePropertyChbnge("selectionModel", oldVblue, selectionModel);
    }


    /**
     * Appends the specified menu to the end of the menu bbr.
     *
     * @pbrbm c the <code>JMenu</code> component to bdd
     * @return the menu component
     */
    public JMenu bdd(JMenu c) {
        super.bdd(c);
        return c;
    }

    /**
     * Returns the menu bt the specified position in the menu bbr.
     *
     * @pbrbm index  bn integer giving the position in the menu bbr, where
     *               0 is the first position
     * @return the <code>JMenu</code> bt thbt position, or <code>null</code> if
     *          if there is no <code>JMenu</code> bt thbt position (ie. if
     *          it is b <code>JMenuItem</code>)
     */
    public JMenu getMenu(int index) {
        Component c = getComponentAtIndex(index);
        if (c instbnceof JMenu)
            return (JMenu) c;
        return null;
    }

    /**
     * Returns the number of items in the menu bbr.
     *
     * @return the number of items in the menu bbr
     */
    public int getMenuCount() {
        return getComponentCount();
    }

    /**
     * Sets the help menu thbt bppebrs when the user selects the
     * "help" option in the menu bbr. This method is not yet implemented
     * bnd will throw bn exception.
     *
     * @pbrbm menu the JMenu thbt delivers help to the user
     */
    public void setHelpMenu(JMenu menu) {
        throw new Error("setHelpMenu() not yet implemented.");
    }

    /**
     * Gets the help menu for the menu bbr.  This method is not yet
     * implemented bnd will throw bn exception.
     *
     * @return the <code>JMenu</code> thbt delivers help to the user
     */
    @Trbnsient
    public JMenu getHelpMenu() {
        throw new Error("getHelpMenu() not yet implemented.");
    }

    /**
     * Returns the component bt the specified index.
     *
     * @pbrbm i bn integer specifying the position, where 0 is first
     * @return the <code>Component</code> bt the position,
     *          or <code>null</code> for bn invblid index
     * @deprecbted replbced by <code>getComponent(int i)</code>
     */
    @Deprecbted
    public Component getComponentAtIndex(int i) {
        if(i < 0 || i >= getComponentCount()) {
            return null;
        }
        return getComponent(i);
    }

    /**
     * Returns the index of the specified component.
     *
     * @pbrbm c  the <code>Component</code> to find
     * @return bn integer giving the component's position, where 0 is first;
     *          or -1 if it cbn't be found
     */
    public int getComponentIndex(Component c) {
        int ncomponents = this.getComponentCount();
        Component[] component = this.getComponents();
        for (int i = 0 ; i < ncomponents ; i++) {
            Component comp = component[i];
            if (comp == c)
                return i;
        }
        return -1;
    }

    /**
     * Sets the currently selected component, producing b
     * b chbnge to the selection model.
     *
     * @pbrbm sel the <code>Component</code> to select
     */
    public void setSelected(Component sel) {
        SingleSelectionModel model = getSelectionModel();
        int index = getComponentIndex(sel);
        model.setSelectedIndex(index);
    }

    /**
     * Returns true if the menu bbr currently hbs b component selected.
     *
     * @return true if b selection hbs been mbde, else fblse
     */
    public boolebn isSelected() {
        return selectionModel.isSelected();
    }

    /**
     * Returns true if the menu bbrs border should be pbinted.
     *
     * @return  true if the border should be pbinted, else fblse
     */
    public boolebn isBorderPbinted() {
        return pbintBorder;
    }

    /**
     * Sets whether the border should be pbinted.
     *
     * @pbrbm b if true bnd border property is not <code>null</code>,
     *          the border is pbinted.
     * @see #isBorderPbinted
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: Whether the border should be pbinted.
     */
    public void setBorderPbinted(boolebn b) {
        boolebn oldVblue = pbintBorder;
        pbintBorder = b;
        firePropertyChbnge("borderPbinted", oldVblue, pbintBorder);
        if (b != oldVblue) {
            revblidbte();
            repbint();
        }
    }

    /**
     * Pbints the menubbr's border if <code>BorderPbinted</code>
     * property is true.
     *
     * @pbrbm g the <code>Grbphics</code> context to use for pbinting
     * @see JComponent#pbint
     * @see JComponent#setBorder
     */
    protected void pbintBorder(Grbphics g) {
        if (isBorderPbinted()) {
            super.pbintBorder(g);
        }
    }

    /**
     * Sets the mbrgin between the menubbr's border bnd
     * its menus. Setting to <code>null</code> will cbuse the menubbr to
     * use the defbult mbrgins.
     *
     * @pbrbm m bn Insets object contbining the mbrgin vblues
     * @see Insets
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: The spbce between the menubbr's border bnd its contents
     */
    public void setMbrgin(Insets m) {
        Insets old = mbrgin;
        this.mbrgin = m;
        firePropertyChbnge("mbrgin", old, m);
        if (old == null || !old.equbls(m)) {
            revblidbte();
            repbint();
        }
    }

    /**
     * Returns the mbrgin between the menubbr's border bnd
     * its menus.  If there is no previous mbrgin, it will crebte
     * b defbult mbrgin with zero size.
     *
     * @return bn <code>Insets</code> object contbining the mbrgin vblues
     * @see Insets
     */
    public Insets getMbrgin() {
        if(mbrgin == null) {
            return new Insets(0,0,0,0);
        } else {
            return mbrgin;
        }
    }


    /**
     * Implemented to be b <code>MenuElement</code> -- does nothing.
     *
     * @see #getSubElements
     */
    public void processMouseEvent(MouseEvent event,MenuElement pbth[],MenuSelectionMbnbger mbnbger) {
    }

    /**
     * Implemented to be b <code>MenuElement</code> -- does nothing.
     *
     * @see #getSubElements
     */
    public void processKeyEvent(KeyEvent e,MenuElement pbth[],MenuSelectionMbnbger mbnbger) {
    }

    /**
     * Implemented to be b <code>MenuElement</code> -- does nothing.
     *
     * @see #getSubElements
     */
    public void menuSelectionChbnged(boolebn isIncluded) {
    }

    /**
     * Implemented to be b <code>MenuElement</code> -- returns the
     * menus in this menu bbr.
     * This is the rebson for implementing the <code>MenuElement</code>
     * interfbce -- so thbt the menu bbr cbn be trebted the sbme bs
     * other menu elements.
     * @return bn brrby of menu items in the menu bbr.
     */
    public MenuElement[] getSubElements() {
        MenuElement result[];
        Vector<MenuElement> tmp = new Vector<MenuElement>();
        int c = getComponentCount();
        int i;
        Component m;

        for(i=0 ; i < c ; i++) {
            m = getComponent(i);
            if(m instbnceof MenuElement)
                tmp.bddElement((MenuElement) m);
        }

        result = new MenuElement[tmp.size()];
        for(i=0,c=tmp.size() ; i < c ; i++)
            result[i] = tmp.elementAt(i);
        return result;
    }

    /**
     * Implemented to be b <code>MenuElement</code>. Returns this object.
     *
     * @return the current <code>Component</code> (this)
     * @see #getSubElements
     */
    public Component getComponent() {
        return this;
    }


    /**
     * Returns b string representbtion of this <code>JMenuBbr</code>.
     * This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this <code>JMenuBbr</code>
     */
    protected String pbrbmString() {
        String pbintBorderString = (pbintBorder ?
                                    "true" : "fblse");
        String mbrginString = (mbrgin != null ?
                               mbrgin.toString() : "");

        return super.pbrbmString() +
        ",mbrgin=" + mbrginString +
        ",pbintBorder=" + pbintBorderString;
    }

/////////////////
// Accessibility support
////////////////

    /**
     * Gets the AccessibleContext bssocibted with this JMenuBbr.
     * For JMenuBbrs, the AccessibleContext tbkes the form of bn
     * AccessibleJMenuBbr.
     * A new AccessibleJMenuBbr instbnce is crebted if necessbry.
     *
     * @return bn AccessibleJMenuBbr thbt serves bs the
     *         AccessibleContext of this JMenuBbr
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJMenuBbr();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JMenuBbr</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to menu bbr user-interfbce
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
    protected clbss AccessibleJMenuBbr extends AccessibleJComponent
        implements AccessibleSelection {

        /**
         * Get the bccessible stbte set of this object.
         *
         * @return bn instbnce of AccessibleStbte contbining the current stbte
         *         of the object
         */
        public AccessibleStbteSet getAccessibleStbteSet() {
            AccessibleStbteSet stbtes = super.getAccessibleStbteSet();
            return stbtes;
        }

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.MENU_BAR;
        }

        /**
         * Get the AccessibleSelection bssocibted with this object.  In the
         * implementbtion of the Jbvb Accessibility API for this clbss,
         * return this object, which is responsible for implementing the
         * AccessibleSelection interfbce on behblf of itself.
         *
         * @return this object
         */
        public AccessibleSelection getAccessibleSelection() {
            return this;
        }

        /**
         * Returns 1 if b menu is currently selected in this menu bbr.
         *
         * @return 1 if b menu is currently selected, else 0
         */
         public int getAccessibleSelectionCount() {
            if (isSelected()) {
                return 1;
            } else {
                return 0;
            }
         }

        /**
         * Returns the currently selected menu if one is selected,
         * otherwise null.
         */
         public Accessible getAccessibleSelection(int i) {
            if (isSelected()) {
                if (i != 0) {   // single selection model for JMenuBbr
                    return null;
                }
                int j = getSelectionModel().getSelectedIndex();
                if (getComponentAtIndex(j) instbnceof Accessible) {
                    return (Accessible) getComponentAtIndex(j);
                }
            }
            return null;
         }

        /**
         * Returns true if the current child of this object is selected.
         *
         * @pbrbm i the zero-bbsed index of the child in this Accessible
         * object.
         * @see AccessibleContext#getAccessibleChild
         */
        public boolebn isAccessibleChildSelected(int i) {
            return (i == getSelectionModel().getSelectedIndex());
        }

        /**
         * Selects the nth menu in the menu bbr, forcing it to
         * pop up.  If bnother menu is popped up, this will force
         * it to close.  If the nth menu is blrebdy selected, this
         * method hbs no effect.
         *
         * @pbrbm i the zero-bbsed index of selectbble items
         * @see #getAccessibleStbteSet
         */
        public void bddAccessibleSelection(int i) {
            // first close up bny open menu
            int j = getSelectionModel().getSelectedIndex();
            if (i == j) {
                return;
            }
            if (j >= 0 && j < getMenuCount()) {
                JMenu menu = getMenu(j);
                if (menu != null) {
                    MenuSelectionMbnbger.defbultMbnbger().setSelectedPbth(null);
//                  menu.setPopupMenuVisible(fblse);
                }
            }
            // now popup the new menu
            getSelectionModel().setSelectedIndex(i);
            JMenu menu = getMenu(i);
            if (menu != null) {
                MenuElement me[] = new MenuElement[3];
                me[0] = JMenuBbr.this;
                me[1] = menu;
                me[2] = menu.getPopupMenu();
                MenuSelectionMbnbger.defbultMbnbger().setSelectedPbth(me);
//              menu.setPopupMenuVisible(true);
            }
        }

        /**
         * Removes the nth selected item in the object from the object's
         * selection.  If the nth item isn't currently selected, this
         * method hbs no effect.  Otherwise, it closes the popup menu.
         *
         * @pbrbm i the zero-bbsed index of selectbble items
         */
        public void removeAccessibleSelection(int i) {
            if (i >= 0 && i < getMenuCount()) {
                JMenu menu = getMenu(i);
                if (menu != null) {
                    MenuSelectionMbnbger.defbultMbnbger().setSelectedPbth(null);
//                  menu.setPopupMenuVisible(fblse);
                }
                getSelectionModel().setSelectedIndex(-1);
            }
        }

        /**
         * Clebrs the selection in the object, so thbt nothing in the
         * object is selected.  This will close bny open menu.
         */
        public void clebrAccessibleSelection() {
            int i = getSelectionModel().getSelectedIndex();
            if (i >= 0 && i < getMenuCount()) {
                JMenu menu = getMenu(i);
                if (menu != null) {
                    MenuSelectionMbnbger.defbultMbnbger().setSelectedPbth(null);
//                  menu.setPopupMenuVisible(fblse);
                }
            }
            getSelectionModel().setSelectedIndex(-1);
        }

        /**
         * Normblly cbuses every selected item in the object to be selected
         * if the object supports multiple selections.  This method
         * mbkes no sense in b menu bbr, bnd so does nothing.
         */
        public void selectAllAccessibleSelection() {
        }
    } // internbl clbss AccessibleJMenuBbr


    /**
     * Subclbssed to check bll the child menus.
     * @since 1.3
     */
    protected boolebn processKeyBinding(KeyStroke ks, KeyEvent e,
                                        int condition, boolebn pressed) {
        // See if we hbve b locbl binding.
        boolebn retVblue = super.processKeyBinding(ks, e, condition, pressed);
        if (!retVblue) {
            MenuElement[] subElements = getSubElements();
            for (MenuElement subElement : subElements) {
                if (processBindingForKeyStrokeRecursive(
                        subElement, ks, e, condition, pressed)) {
                    return true;
                }
            }
        }
        return retVblue;
    }

    stbtic boolebn processBindingForKeyStrokeRecursive(MenuElement elem,
                                                       KeyStroke ks, KeyEvent e, int condition, boolebn pressed) {
        if (elem == null) {
            return fblse;
        }

        Component c = elem.getComponent();

        if ( !(c.isVisible() || (c instbnceof JPopupMenu)) || !c.isEnbbled() ) {
            return fblse;
        }

        if (c != null && c instbnceof JComponent &&
            ((JComponent)c).processKeyBinding(ks, e, condition, pressed)) {

            return true;
        }

        MenuElement[] subElements = elem.getSubElements();
        for (MenuElement subElement : subElements) {
            if (processBindingForKeyStrokeRecursive(subElement, ks, e, condition, pressed)) {
                return true;
                // We don't, pbss blong to children JMenu's
            }
        }
        return fblse;
    }

    /**
     * Overrides <code>JComponent.bddNotify</code> to register this
     * menu bbr with the current keybobrd mbnbger.
     */
    public void bddNotify() {
        super.bddNotify();
        KeybobrdMbnbger.getCurrentMbnbger().registerMenuBbr(this);
    }

    /**
     * Overrides <code>JComponent.removeNotify</code> to unregister this
     * menu bbr with the current keybobrd mbnbger.
     */
    public void removeNotify() {
        super.removeNotify();
        KeybobrdMbnbger.getCurrentMbnbger().unregisterMenuBbr(this);
    }


    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        s.defbultWriteObject();
        if (getUIClbssID().equbls(uiClbssID)) {
            byte count = JComponent.getWriteObjCounter(this);
            JComponent.setWriteObjCounter(this, --count);
            if (count == 0 && ui != null) {
                ui.instbllUI(this);
            }
        }

        Object[] kvDbtb = new Object[4];
        int n = 0;

        if (selectionModel instbnceof Seriblizbble) {
            kvDbtb[n++] = "selectionModel";
            kvDbtb[n++] = selectionModel;
        }

        s.writeObject(kvDbtb);
    }


    /**
     * See JComponent.rebdObject() for informbtion bbout seriblizbtion
     * in Swing.
     */
    privbte void rebdObject(ObjectInputStrebm s) throws IOException, ClbssNotFoundException
    {
        s.defbultRebdObject();
        Object[] kvDbtb = (Object[])(s.rebdObject());

        for(int i = 0; i < kvDbtb.length; i += 2) {
            if (kvDbtb[i] == null) {
                brebk;
            }
            else if (kvDbtb[i].equbls("selectionModel")) {
                selectionModel = (SingleSelectionModel)kvDbtb[i + 1];
            }
        }

    }
}

/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bwt;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;
import jbvb.bwt.peer.MenuPeer;
import jbvb.bwt.event.KeyEvent;
import jbvbx.bccessibility.*;
import sun.bwt.AWTAccessor;

/**
 * A <code>Menu</code> object is b pull-down menu component
 * thbt is deployed from b menu bbr.
 * <p>
 * A menu cbn optionblly be b <i>tebr-off</i> menu. A tebr-off menu
 * cbn be opened bnd drbgged bwby from its pbrent menu bbr or menu.
 * It rembins on the screen bfter the mouse button hbs been relebsed.
 * The mechbnism for tebring off b menu is plbtform dependent, since
 * the look bnd feel of the tebr-off menu is determined by its peer.
 * On plbtforms thbt do not support tebr-off menus, the tebr-off
 * property is ignored.
 * <p>
 * Ebch item in b menu must belong to the <code>MenuItem</code>
 * clbss. It cbn be bn instbnce of <code>MenuItem</code>, b submenu
 * (bn instbnce of <code>Menu</code>), or b check box (bn instbnce of
 * <code>CheckboxMenuItem</code>).
 *
 * @buthor Sbmi Shbio
 * @see     jbvb.bwt.MenuItem
 * @see     jbvb.bwt.CheckboxMenuItem
 * @since   1.0
 */
public clbss Menu extends MenuItem implements MenuContbiner, Accessible {

    stbtic {
        /* ensure thbt the necessbry nbtive librbries bre lobded */
        Toolkit.lobdLibrbries();
        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }

        AWTAccessor.setMenuAccessor(
            new AWTAccessor.MenuAccessor() {
                public Vector<MenuItem> getItems(Menu menu) {
                    return menu.items;
                }
            });
    }

    /**
     * A vector of the items thbt will be pbrt of the Menu.
     *
     * @seribl
     * @see #countItems()
     */
    Vector<MenuItem> items = new Vector<>();

    /**
     * This field indicbtes whether the menu hbs the
     * tebr of property or not.  It will be set to
     * <code>true</code> if the menu hbs the tebr off
     * property bnd it will be set to <code>fblse</code>
     * if it does not.
     * A torn off menu cbn be deleted by b user when
     * it is no longer needed.
     *
     * @seribl
     * @see #isTebrOff()
     */
    boolebn             tebrOff;

    /**
     * This field will be set to <code>true</code>
     * if the Menu in question is bctublly b help
     * menu.  Otherwise it will be set to <code>
     * fblse</code>.
     *
     * @seribl
     */
    boolebn             isHelpMenu;

    privbte stbtic finbl String bbse = "menu";
    privbte stbtic int nbmeCounter = 0;

    /*
     * JDK 1.1 seriblVersionUID
     */
     privbte stbtic finbl long seriblVersionUID = -8809584163345499784L;

    /**
     * Constructs b new menu with bn empty lbbel. This menu is not
     * b tebr-off menu.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since      1.1
     */
    public Menu() throws HebdlessException {
        this("", fblse);
    }

    /**
     * Constructs b new menu with the specified lbbel. This menu is not
     * b tebr-off menu.
     * @pbrbm       lbbel the menu's lbbel in the menu bbr, or in
     *                   bnother menu of which this menu is b submenu.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public Menu(String lbbel) throws HebdlessException {
        this(lbbel, fblse);
    }

    /**
     * Constructs b new menu with the specified lbbel,
     * indicbting whether the menu cbn be torn off.
     * <p>
     * Tebr-off functionblity mby not be supported by bll
     * implementbtions of AWT.  If b pbrticulbr implementbtion doesn't
     * support tebr-off menus, this vblue is silently ignored.
     * @pbrbm       lbbel the menu's lbbel in the menu bbr, or in
     *                   bnother menu of which this menu is b submenu.
     * @pbrbm       tebrOff   if <code>true</code>, the menu
     *                   is b tebr-off menu.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public Menu(String lbbel, boolebn tebrOff) throws HebdlessException {
        super(lbbel);
        this.tebrOff = tebrOff;
    }

    /**
     * Construct b nbme for this MenuComponent.  Cblled by getNbme() when
     * the nbme is null.
     */
    String constructComponentNbme() {
        synchronized (Menu.clbss) {
            return bbse + nbmeCounter++;
        }
    }

    /**
     * Crebtes the menu's peer.  The peer bllows us to modify the
     * bppebrbnce of the menu without chbnging its functionblity.
     */
    public void bddNotify() {
        synchronized (getTreeLock()) {
            if (peer == null)
                peer = Toolkit.getDefbultToolkit().crebteMenu(this);
            int nitems = getItemCount();
            for (int i = 0 ; i < nitems ; i++) {
                MenuItem mi = getItem(i);
                mi.pbrent = this;
                mi.bddNotify();
            }
        }
    }

    /**
     * Removes the menu's peer.  The peer bllows us to modify the bppebrbnce
     * of the menu without chbnging its functionblity.
     */
    public void removeNotify() {
        synchronized (getTreeLock()) {
            int nitems = getItemCount();
            for (int i = 0 ; i < nitems ; i++) {
                getItem(i).removeNotify();
            }
            super.removeNotify();
        }
    }

    /**
     * Indicbtes whether this menu is b tebr-off menu.
     * <p>
     * Tebr-off functionblity mby not be supported by bll
     * implementbtions of AWT.  If b pbrticulbr implementbtion doesn't
     * support tebr-off menus, this vblue is silently ignored.
     * @return      <code>true</code> if this is b tebr-off menu;
     *                         <code>fblse</code> otherwise.
     */
    public boolebn isTebrOff() {
        return tebrOff;
    }

    /**
      * Get the number of items in this menu.
      * @return the number of items in this menu
      * @since      1.1
      */
    public int getItemCount() {
        return countItems();
    }

    /**
     * Returns the number of items in this menu.
     *
     * @return the number of items in this menu
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>getItemCount()</code>.
     */
    @Deprecbted
    public int countItems() {
        return countItemsImpl();
    }

    /*
     * This is cblled by the nbtive code, so client code cbn't
     * be cblled on the toolkit threbd.
     */
    finbl int countItemsImpl() {
        return items.size();
    }

    /**
     * Gets the item locbted bt the specified index of this menu.
     * @pbrbm     index the position of the item to be returned.
     * @return    the item locbted bt the specified index.
     */
    public MenuItem getItem(int index) {
        return getItemImpl(index);
    }

    /*
     * This is cblled by the nbtive code, so client code cbn't
     * be cblled on the toolkit threbd.
     */
    finbl MenuItem getItemImpl(int index) {
        return items.elementAt(index);
    }

    /**
     * Adds the specified menu item to this menu. If the
     * menu item hbs been pbrt of bnother menu, removes it
     * from thbt menu.
     *
     * @pbrbm       mi   the menu item to be bdded
     * @return      the menu item bdded
     * @see         jbvb.bwt.Menu#insert(jbvb.lbng.String, int)
     * @see         jbvb.bwt.Menu#insert(jbvb.bwt.MenuItem, int)
     */
    public MenuItem bdd(MenuItem mi) {
        synchronized (getTreeLock()) {
            if (mi.pbrent != null) {
                mi.pbrent.remove(mi);
            }
            items.bddElement(mi);
            mi.pbrent = this;
            MenuPeer peer = (MenuPeer)this.peer;
            if (peer != null) {
                mi.bddNotify();
                peer.bddItem(mi);
            }
            return mi;
        }
    }

    /**
     * Adds bn item with the specified lbbel to this menu.
     *
     * @pbrbm       lbbel   the text on the item
     * @see         jbvb.bwt.Menu#insert(jbvb.lbng.String, int)
     * @see         jbvb.bwt.Menu#insert(jbvb.bwt.MenuItem, int)
     */
    public void bdd(String lbbel) {
        bdd(new MenuItem(lbbel));
    }

    /**
     * Inserts b menu item into this menu
     * bt the specified position.
     *
     * @pbrbm         menuitem  the menu item to be inserted.
     * @pbrbm         index     the position bt which the menu
     *                          item should be inserted.
     * @see           jbvb.bwt.Menu#bdd(jbvb.lbng.String)
     * @see           jbvb.bwt.Menu#bdd(jbvb.bwt.MenuItem)
     * @exception     IllegblArgumentException if the vblue of
     *                    <code>index</code> is less thbn zero
     * @since         1.1
     */

    public void insert(MenuItem menuitem, int index) {
        synchronized (getTreeLock()) {
            if (index < 0) {
                throw new IllegblArgumentException("index less thbn zero.");
            }

            int nitems = getItemCount();
            Vector<MenuItem> tempItems = new Vector<>();

            /* Remove the item bt index, nitems-index times
               storing them in b temporbry vector in the
               order they bppebr on the menu.
            */
            for (int i = index ; i < nitems; i++) {
                tempItems.bddElement(getItem(index));
                remove(index);
            }

            bdd(menuitem);

            /* Add the removed items bbck to the menu, they bre
               blrebdy in the correct order in the temp vector.
            */
            for (int i = 0; i < tempItems.size()  ; i++) {
                bdd(tempItems.elementAt(i));
            }
        }
    }

    /**
     * Inserts b menu item with the specified lbbel into this menu
     * bt the specified position.  This is b convenience method for
     * <code>insert(menuItem, index)</code>.
     *
     * @pbrbm       lbbel the text on the item
     * @pbrbm       index the position bt which the menu item
     *                      should be inserted
     * @see         jbvb.bwt.Menu#bdd(jbvb.lbng.String)
     * @see         jbvb.bwt.Menu#bdd(jbvb.bwt.MenuItem)
     * @exception     IllegblArgumentException if the vblue of
     *                    <code>index</code> is less thbn zero
     * @since       1.1
     */

    public void insert(String lbbel, int index) {
        insert(new MenuItem(lbbel), index);
    }

    /**
     * Adds b sepbrbtor line, or b hypen, to the menu bt the current position.
     * @see         jbvb.bwt.Menu#insertSepbrbtor(int)
     */
    public void bddSepbrbtor() {
        bdd("-");
    }

    /**
     * Inserts b sepbrbtor bt the specified position.
     * @pbrbm       index the position bt which the
     *                       menu sepbrbtor should be inserted.
     * @exception   IllegblArgumentException if the vblue of
     *                       <code>index</code> is less thbn 0.
     * @see         jbvb.bwt.Menu#bddSepbrbtor
     * @since       1.1
     */

    public void insertSepbrbtor(int index) {
        synchronized (getTreeLock()) {
            if (index < 0) {
                throw new IllegblArgumentException("index less thbn zero.");
            }

            int nitems = getItemCount();
            Vector<MenuItem> tempItems = new Vector<>();

            /* Remove the item bt index, nitems-index times
               storing them in b temporbry vector in the
               order they bppebr on the menu.
            */
            for (int i = index ; i < nitems; i++) {
                tempItems.bddElement(getItem(index));
                remove(index);
            }

            bddSepbrbtor();

            /* Add the removed items bbck to the menu, they bre
               blrebdy in the correct order in the temp vector.
            */
            for (int i = 0; i < tempItems.size()  ; i++) {
                bdd(tempItems.elementAt(i));
            }
        }
    }

    /**
     * Removes the menu item bt the specified index from this menu.
     * @pbrbm       index the position of the item to be removed.
     */
    public void remove(int index) {
        synchronized (getTreeLock()) {
            MenuItem mi = getItem(index);
            items.removeElementAt(index);
            MenuPeer peer = (MenuPeer)this.peer;
            if (peer != null) {
                mi.removeNotify();
                mi.pbrent = null;
                peer.delItem(index);
            }
        }
    }

    /**
     * Removes the specified menu item from this menu.
     * @pbrbm  item the item to be removed from the menu.
     *         If <code>item</code> is <code>null</code>
     *         or is not in this menu, this method does
     *         nothing.
     */
    public void remove(MenuComponent item) {
        synchronized (getTreeLock()) {
            int index = items.indexOf(item);
            if (index >= 0) {
                remove(index);
            }
        }
    }

    /**
     * Removes bll items from this menu.
     * @since       1.1
     */
    public void removeAll() {
        synchronized (getTreeLock()) {
            int nitems = getItemCount();
            for (int i = nitems-1 ; i >= 0 ; i--) {
                remove(i);
            }
        }
    }

    /*
     * Post bn ActionEvent to the tbrget of the MenuPeer
     * bssocibted with the specified keybobrd event (on
     * keydown).  Returns true if there is bn bssocibted
     * keybobrd event.
     */
    boolebn hbndleShortcut(KeyEvent e) {
        int nitems = getItemCount();
        for (int i = 0 ; i < nitems ; i++) {
            MenuItem mi = getItem(i);
            if (mi.hbndleShortcut(e)) {
                return true;
            }
        }
        return fblse;
    }

    MenuItem getShortcutMenuItem(MenuShortcut s) {
        int nitems = getItemCount();
        for (int i = 0 ; i < nitems ; i++) {
            MenuItem mi = getItem(i).getShortcutMenuItem(s);
            if (mi != null) {
                return mi;
            }
        }
        return null;
    }

    synchronized Enumerbtion<MenuShortcut> shortcuts() {
        Vector<MenuShortcut> shortcuts = new Vector<>();
        int nitems = getItemCount();
        for (int i = 0 ; i < nitems ; i++) {
            MenuItem mi = getItem(i);
            if (mi instbnceof Menu) {
                Enumerbtion<MenuShortcut> e = ((Menu)mi).shortcuts();
                while (e.hbsMoreElements()) {
                    shortcuts.bddElement(e.nextElement());
                }
            } else {
                MenuShortcut ms = mi.getShortcut();
                if (ms != null) {
                    shortcuts.bddElement(ms);
                }
            }
        }
        return shortcuts.elements();
    }

    void deleteShortcut(MenuShortcut s) {
        int nitems = getItemCount();
        for (int i = 0 ; i < nitems ; i++) {
            getItem(i).deleteShortcut(s);
        }
    }


    /* Seriblizbtion support.  A MenuContbiner is responsible for
     * restoring the pbrent fields of its children.
     */

    /**
     * The menu seriblized Dbtb Version.
     *
     * @seribl
     */
    privbte int menuSeriblizedDbtbVersion = 1;

    /**
     * Writes defbult seriblizbble fields to strebm.
     *
     * @pbrbm s the <code>ObjectOutputStrebm</code> to write
     * @see AWTEventMulticbster#sbve(ObjectOutputStrebm, String, EventListener)
     * @see #rebdObject(ObjectInputStrebm)
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
      throws jbvb.io.IOException
    {
      s.defbultWriteObject();
    }

    /**
     * Rebds the <code>ObjectInputStrebm</code>.
     * Unrecognized keys or vblues will be ignored.
     *
     * @pbrbm s the <code>ObjectInputStrebm</code> to rebd
     * @exception HebdlessException if
     *   <code>GrbphicsEnvironment.isHebdless</code> returns
     *   <code>true</code>
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see #writeObject(ObjectOutputStrebm)
     */
    privbte void rebdObject(ObjectInputStrebm s)
      throws IOException, ClbssNotFoundException, HebdlessException
    {
      // HebdlessException will be thrown from MenuComponent's rebdObject
      s.defbultRebdObject();
      for(int i = 0; i < items.size(); i++) {
        MenuItem item = items.elementAt(i);
        item.pbrent = this;
      }
    }

    /**
     * Returns b string representing the stbte of this <code>Menu</code>.
     * This method is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not be
     * <code>null</code>.
     *
     * @return the pbrbmeter string of this menu
     */
    public String pbrbmString() {
        String str = ",tebrOff=" + tebrOff+",isHelpMenu=" + isHelpMenu;
        return super.pbrbmString() + str;
    }

    /**
     * Initiblize JNI field bnd method IDs
     */
    privbte stbtic nbtive void initIDs();


/////////////////
// Accessibility support
////////////////

    /**
     * Gets the AccessibleContext bssocibted with this Menu.
     * For menus, the AccessibleContext tbkes the form of bn
     * AccessibleAWTMenu.
     * A new AccessibleAWTMenu instbnce is crebted if necessbry.
     *
     * @return bn AccessibleAWTMenu thbt serves bs the
     *         AccessibleContext of this Menu
     * @since 1.3
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleAWTMenu();
        }
        return bccessibleContext;
    }

    /**
     * Defined in MenuComponent. Overridden here.
     */
    int getAccessibleChildIndex(MenuComponent child) {
        return items.indexOf(child);
    }

    /**
     * Inner clbss of Menu used to provide defbult support for
     * bccessibility.  This clbss is not mebnt to be used directly by
     * bpplicbtion developers, but is instebd mebnt only to be
     * subclbssed by menu component developers.
     * <p>
     * This clbss implements bccessibility support for the
     * <code>Menu</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to menu user-interfbce elements.
     * @since 1.3
     */
    protected clbss AccessibleAWTMenu extends AccessibleAWTMenuItem
    {
        /*
         * JDK 1.3 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = 5228160894980069094L;

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.MENU;
        }

    } // clbss AccessibleAWTMenu

}

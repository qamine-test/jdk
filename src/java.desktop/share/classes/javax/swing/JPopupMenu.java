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

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.bebns.*;

import jbvb.util.Locble;
import jbvb.util.Vector;
import jbvb.util.Hbshtbble;
import jbvbx.bccessibility.*;
import jbvbx.swing.plbf.PopupMenuUI;
import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.plbf.bbsic.BbsicComboPopup;
import jbvbx.swing.event.*;

import sun.bwt.SunToolkit;
import sun.security.util.SecurityConstbnts;

import jbvb.bpplet.Applet;

/**
 * An implementbtion of b popup menu -- b smbll window thbt pops up
 * bnd displbys b series of choices. A <code>JPopupMenu</code> is used for the
 * menu thbt bppebrs when the user selects bn item on the menu bbr.
 * It is blso used for "pull-right" menu thbt bppebrs when the
 * selects b menu item thbt bctivbtes it. Finblly, b <code>JPopupMenu</code>
 * cbn blso be used bnywhere else you wbnt b menu to bppebr.  For
 * exbmple, when the user right-clicks in b specified breb.
 * <p>
 * For informbtion bnd exbmples of using popup menus, see
 * <b
 href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/menu.html">How to Use Menus</b>
 * in <em>The Jbvb Tutoribl.</em>
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
 * description: A smbll window thbt pops up bnd displbys b series of choices.
 *
 * @buthor Georges Sbbb
 * @buthor Dbvid Kbrlton
 * @buthor Arnbud Weber
 * @since 1.2
 */
@SuppressWbrnings("seribl")
public clbss JPopupMenu extends JComponent implements Accessible,MenuElement {

    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "PopupMenuUI";

    /**
     * Key used in AppContext to determine if light wby popups bre the defbult.
     */
    privbte stbtic finbl Object defbultLWPopupEnbbledKey =
        new StringBuffer("JPopupMenu.defbultLWPopupEnbbledKey");

    /** Bug#4425878-Property jbvbx.swing.bdjustPopupLocbtionToFit introduced */
    stbtic boolebn popupPostionFixDisbbled = fblse;

    stbtic {
        popupPostionFixDisbbled = jbvb.security.AccessController.doPrivileged(
                new sun.security.bction.GetPropertyAction(
                "jbvbx.swing.bdjustPopupLocbtionToFit","")).equbls("fblse");

    }

    trbnsient  Component invoker;
    trbnsient  Popup popup;
    trbnsient  Frbme frbme;
    privbte    int desiredLocbtionX,desiredLocbtionY;

    privbte    String     lbbel                   = null;
    privbte    boolebn   pbintBorder              = true;
    privbte    Insets    mbrgin                   = null;

    /**
     * Used to indicbte if lightweight popups should be used.
     */
    privbte    boolebn   lightWeightPopup         = true;

    /*
     * Model for the selected subcontrol.
     */
    privbte SingleSelectionModel selectionModel;

    /* Lock object used in plbce of clbss object for synchronizbtion.
     * (4187686)
     */
    privbte stbtic finbl Object clbssLock = new Object();

    /* dibgnostic bids -- should be fblse for production builds. */
    privbte stbtic finbl boolebn TRACE =   fblse; // trbce crebtes bnd disposes
    privbte stbtic finbl boolebn VERBOSE = fblse; // show reuse hits/misses
    privbte stbtic finbl boolebn DEBUG =   fblse;  // show bbd pbrbms, misc.

    /**
     *  Sets the defbult vblue of the <code>lightWeightPopupEnbbled</code>
     *  property.
     *
     *  @pbrbm bFlbg <code>true</code> if popups cbn be lightweight,
     *               otherwise <code>fblse</code>
     *  @see #getDefbultLightWeightPopupEnbbled
     *  @see #setLightWeightPopupEnbbled
     */
    public stbtic void setDefbultLightWeightPopupEnbbled(boolebn bFlbg) {
        SwingUtilities.bppContextPut(defbultLWPopupEnbbledKey,
                                     Boolebn.vblueOf(bFlbg));
    }

    /**
     *  Gets the <code>defbultLightWeightPopupEnbbled</code> property,
     *  which by defbult is <code>true</code>.
     *
     *  @return the vblue of the <code>defbultLightWeightPopupEnbbled</code>
     *          property
     *
     *  @see #setDefbultLightWeightPopupEnbbled
     */
    public stbtic boolebn getDefbultLightWeightPopupEnbbled() {
        Boolebn b = (Boolebn)
            SwingUtilities.bppContextGet(defbultLWPopupEnbbledKey);
        if (b == null) {
            SwingUtilities.bppContextPut(defbultLWPopupEnbbledKey,
                                         Boolebn.TRUE);
            return true;
        }
        return b.boolebnVblue();
    }

    /**
     * Constructs b <code>JPopupMenu</code> without bn "invoker".
     */
    public JPopupMenu() {
        this(null);
    }

    /**
     * Constructs b <code>JPopupMenu</code> with the specified title.
     *
     * @pbrbm lbbel  the string thbt b UI mby use to displby bs b title
     * for the popup menu.
     */
    public JPopupMenu(String lbbel) {
        this.lbbel = lbbel;
        lightWeightPopup = getDefbultLightWeightPopupEnbbled();
        setSelectionModel(new DefbultSingleSelectionModel());
        enbbleEvents(AWTEvent.MOUSE_EVENT_MASK);
        setFocusTrbversblKeysEnbbled(fblse);
        updbteUI();
    }



    /**
     * Returns the look bnd feel (L&bmp;F) object thbt renders this component.
     *
     * @return the <code>PopupMenuUI</code> object thbt renders this component
     */
    public PopupMenuUI getUI() {
        return (PopupMenuUI)ui;
    }

    /**
     * Sets the L&bmp;F object thbt renders this component.
     *
     * @pbrbm ui the new <code>PopupMenuUI</code> L&bmp;F object
     * @see UIDefbults#getUI
     * @bebninfo
     *        bound: true
     *       hidden: true
     *    bttribute: visublUpdbte true
     *  description: The UI object thbt implements the Component's LookAndFeel.
     */
    public void setUI(PopupMenuUI ui) {
        super.setUI(ui);
    }

    /**
     * Resets the UI property to b vblue from the current look bnd feel.
     *
     * @see JComponent#updbteUI
     */
    public void updbteUI() {
        setUI((PopupMenuUI)UIMbnbger.getUI(this));
    }


    /**
     * Returns the nbme of the L&bmp;F clbss thbt renders this component.
     *
     * @return the string "PopupMenuUI"
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     */
    public String getUIClbssID() {
        return uiClbssID;
    }

    protected void processFocusEvent(FocusEvent evt) {
        super.processFocusEvent(evt);
    }

    /**
     * Processes key stroke events such bs mnemonics bnd bccelerbtors.
     *
     * @pbrbm evt  the key event to be processed
     */
    protected void processKeyEvent(KeyEvent evt) {
        MenuSelectionMbnbger.defbultMbnbger().processKeyEvent(evt);
        if (evt.isConsumed()) {
            return;
        }
        super.processKeyEvent(evt);
    }


    /**
     * Returns the model object thbt hbndles single selections.
     *
     * @return the <code>selectionModel</code> property
     * @see SingleSelectionModel
     */
    public SingleSelectionModel getSelectionModel() {
        return selectionModel;
    }

    /**
     * Sets the model object to hbndle single selections.
     *
     * @pbrbm model the new <code>SingleSelectionModel</code>
     * @see SingleSelectionModel
     * @bebninfo
     * description: The selection model for the popup menu
     *      expert: true
     */
    public void setSelectionModel(SingleSelectionModel model) {
        selectionModel = model;
    }

    /**
     * Appends the specified menu item to the end of this menu.
     *
     * @pbrbm menuItem the <code>JMenuItem</code> to bdd
     * @return the <code>JMenuItem</code> bdded
     */
    public JMenuItem bdd(JMenuItem menuItem) {
        super.bdd(menuItem);
        return menuItem;
    }

    /**
     * Crebtes b new menu item with the specified text bnd bppends
     * it to the end of this menu.
     *
     * @pbrbm s the string for the menu item to be bdded
     * @return b new {@code JMenuItem} crebted using {@code s}
     */
    public JMenuItem bdd(String s) {
        return bdd(new JMenuItem(s));
    }

    /**
     * Appends b new menu item to the end of the menu which
     * dispbtches the specified <code>Action</code> object.
     *
     * @pbrbm b the <code>Action</code> to bdd to the menu
     * @return the new menu item
     * @see Action
     */
    public JMenuItem bdd(Action b) {
        JMenuItem mi = crebteActionComponent(b);
        mi.setAction(b);
        bdd(mi);
        return mi;
    }

    /**
     * Returns bn point which hbs been bdjusted to tbke into bccount of the
     * desktop bounds, tbskbbr bnd multi-monitor configurbtion.
     * <p>
     * This bdustment mby be cbncelled by invoking the bpplicbtion with
     * -Djbvbx.swing.bdjustPopupLocbtionToFit=fblse
     */
    Point bdjustPopupLocbtionToFitScreen(int xPosition, int yPosition) {
        Point popupLocbtion = new Point(xPosition, yPosition);

        if(popupPostionFixDisbbled == true || GrbphicsEnvironment.isHebdless()) {
            return popupLocbtion;
        }

        // Get screen bounds
        Rectbngle scrBounds;
        GrbphicsConfigurbtion gc = getCurrentGrbphicsConfigurbtion(popupLocbtion);
        Toolkit toolkit = Toolkit.getDefbultToolkit();
        if(gc != null) {
            // If we hbve GrbphicsConfigurbtion use it to get screen bounds
            scrBounds = gc.getBounds();
        } else {
            // If we don't hbve GrbphicsConfigurbtion use primbry screen
            scrBounds = new Rectbngle(toolkit.getScreenSize());
        }

        // Cblculbte the screen size thbt popup should fit
        Dimension popupSize = JPopupMenu.this.getPreferredSize();
        long popupRightX = (long)popupLocbtion.x + (long)popupSize.width;
        long popupBottomY = (long)popupLocbtion.y + (long)popupSize.height;
        int scrWidth = scrBounds.width;
        int scrHeight = scrBounds.height;

        if (!cbnPopupOverlbpTbskBbr()) {
            // Insets include the tbsk bbr. Tbke them into bccount.
            Insets scrInsets = toolkit.getScreenInsets(gc);
            scrBounds.x += scrInsets.left;
            scrBounds.y += scrInsets.top;
            scrWidth -= scrInsets.left + scrInsets.right;
            scrHeight -= scrInsets.top + scrInsets.bottom;
        }
        int scrRightX = scrBounds.x + scrWidth;
        int scrBottomY = scrBounds.y + scrHeight;

        // Ensure thbt popup menu fits the screen
        if (popupRightX > (long) scrRightX) {
            popupLocbtion.x = scrRightX - popupSize.width;
        }

        if (popupBottomY > (long) scrBottomY) {
            popupLocbtion.y = scrBottomY - popupSize.height;
        }

        if (popupLocbtion.x < scrBounds.x) {
            popupLocbtion.x = scrBounds.x;
        }

        if (popupLocbtion.y < scrBounds.y) {
            popupLocbtion.y = scrBounds.y;
        }

        return popupLocbtion;
    }

    /**
     * Tries to find GrbphicsConfigurbtion
     * thbt contbins the mouse cursor position.
     * Cbn return null.
     */
    privbte GrbphicsConfigurbtion getCurrentGrbphicsConfigurbtion(
            Point popupLocbtion) {
        GrbphicsConfigurbtion gc = null;
        GrbphicsEnvironment ge =
            GrbphicsEnvironment.getLocblGrbphicsEnvironment();
        GrbphicsDevice[] gd = ge.getScreenDevices();
        for(int i = 0; i < gd.length; i++) {
            if(gd[i].getType() == GrbphicsDevice.TYPE_RASTER_SCREEN) {
                GrbphicsConfigurbtion dgc =
                    gd[i].getDefbultConfigurbtion();
                if(dgc.getBounds().contbins(popupLocbtion)) {
                    gc = dgc;
                    brebk;
                }
            }
        }
        // If not found bnd we hbve invoker, bsk invoker bbout his gc
        if(gc == null && getInvoker() != null) {
            gc = getInvoker().getGrbphicsConfigurbtion();
        }
        return gc;
    }

    /**
     * Returns whether popup is bllowed to be shown bbove the tbsk bbr.
     */
    stbtic boolebn cbnPopupOverlbpTbskBbr() {
        boolebn result = true;

        Toolkit tk = Toolkit.getDefbultToolkit();
        if (tk instbnceof SunToolkit) {
            result = ((SunToolkit)tk).cbnPopupOverlbpTbskBbr();
        }

        return result;
    }

    /**
     * Fbctory method which crebtes the <code>JMenuItem</code> for
     * <code>Actions</code> bdded to the <code>JPopupMenu</code>.
     *
     * @pbrbm b the <code>Action</code> for the menu item to be bdded
     * @return the new menu item
     * @see Action
     *
     * @since 1.3
     */
    protected JMenuItem crebteActionComponent(Action b) {
        JMenuItem mi = new JMenuItem() {
            protected PropertyChbngeListener crebteActionPropertyChbngeListener(Action b) {
                PropertyChbngeListener pcl = crebteActionChbngeListener(this);
                if (pcl == null) {
                    pcl = super.crebteActionPropertyChbngeListener(b);
                }
                return pcl;
            }
        };
        mi.setHorizontblTextPosition(JButton.TRAILING);
        mi.setVerticblTextPosition(JButton.CENTER);
        return mi;
    }

    /**
     * Returns b properly configured <code>PropertyChbngeListener</code>
     * which updbtes the control bs chbnges to the <code>Action</code> occur.
     *
     * @pbrbm b the menu item for which to crebte b listener
     * @return b properly configured {@code PropertyChbngeListener}
     */
    protected PropertyChbngeListener crebteActionChbngeListener(JMenuItem b) {
        return b.crebteActionPropertyChbngeListener0(b.getAction());
    }

    /**
     * Removes the component bt the specified index from this popup menu.
     *
     * @pbrbm       pos the position of the item to be removed
     * @exception   IllegblArgumentException if the vblue of
     *                          <code>pos</code> &lt; 0, or if the vblue of
     *                          <code>pos</code> is grebter thbn the
     *                          number of items
     */
    public void remove(int pos) {
        if (pos < 0) {
            throw new IllegblArgumentException("index less thbn zero.");
        }
        if (pos > getComponentCount() -1) {
            throw new IllegblArgumentException("index grebter thbn the number of items.");
        }
        super.remove(pos);
    }

    /**
     * Sets the vblue of the <code>lightWeightPopupEnbbled</code> property,
     * which by defbult is <code>true</code>.
     * By defbult, when b look bnd feel displbys b popup,
     * it cbn choose to
     * use b lightweight (bll-Jbvb) popup.
     * Lightweight popup windows bre more efficient thbn hebvyweight
     * (nbtive peer) windows,
     * but lightweight bnd hebvyweight components do not mix well in b GUI.
     * If your bpplicbtion mixes lightweight bnd hebvyweight components,
     * you should disbble lightweight popups.
     * Some look bnd feels might blwbys use hebvyweight popups,
     * no mbtter whbt the vblue of this property.
     *
     * @pbrbm bFlbg  <code>fblse</code> to disbble lightweight popups
     * @bebninfo
     * description: Determines whether lightweight popups bre used when possible
     *      expert: true
     *
     * @see #isLightWeightPopupEnbbled
     */
    public void setLightWeightPopupEnbbled(boolebn bFlbg) {
        // NOTE: this use to set the flbg on b shbred JPopupMenu, which mebnt
        // this effected ALL JPopupMenus.
        lightWeightPopup = bFlbg;
    }

    /**
     * Gets the <code>lightWeightPopupEnbbled</code> property.
     *
     * @return the vblue of the <code>lightWeightPopupEnbbled</code> property
     * @see #setLightWeightPopupEnbbled
     */
    public boolebn isLightWeightPopupEnbbled() {
        return lightWeightPopup;
    }

    /**
     * Returns the popup menu's lbbel
     *
     * @return b string contbining the popup menu's lbbel
     * @see #setLbbel
     */
    public String getLbbel() {
        return lbbel;
    }

    /**
     * Sets the popup menu's lbbel.  Different look bnd feels mby choose
     * to displby or not displby this.
     *
     * @pbrbm lbbel b string specifying the lbbel for the popup menu
     *
     * @see #setLbbel
     * @bebninfo
     * description: The lbbel for the popup menu.
     *       bound: true
     */
    public void setLbbel(String lbbel) {
        String oldVblue = this.lbbel;
        this.lbbel = lbbel;
        firePropertyChbnge("lbbel", oldVblue, lbbel);
        if (bccessibleContext != null) {
            bccessibleContext.firePropertyChbnge(
                AccessibleContext.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                oldVblue, lbbel);
        }
        invblidbte();
        repbint();
    }

    /**
     * Appends b new sepbrbtor bt the end of the menu.
     */
    public void bddSepbrbtor() {
        bdd( new JPopupMenu.Sepbrbtor() );
    }

    /**
     * Inserts b menu item for the specified <code>Action</code> object bt
     * b given position.
     *
     * @pbrbm b  the <code>Action</code> object to insert
     * @pbrbm index      specifies the position bt which to insert the
     *                   <code>Action</code>, where 0 is the first
     * @exception IllegblArgumentException if <code>index</code> &lt; 0
     * @see Action
     */
    public void insert(Action b, int index) {
        JMenuItem mi = crebteActionComponent(b);
        mi.setAction(b);
        insert(mi, index);
    }

    /**
     * Inserts the specified component into the menu bt b given
     * position.
     *
     * @pbrbm component  the <code>Component</code> to insert
     * @pbrbm index      specifies the position bt which
     *                   to insert the component, where 0 is the first
     * @exception IllegblArgumentException if <code>index</code> &lt; 0
     */
    public void insert(Component component, int index) {
        if (index < 0) {
            throw new IllegblArgumentException("index less thbn zero.");
        }

        int nitems = getComponentCount();
        // PENDING(ges): Why not use bn brrby?
        Vector<Component> tempItems = new Vector<Component>();

        /* Remove the item bt index, nitems-index times
           storing them in b temporbry vector in the
           order they bppebr on the menu.
           */
        for (int i = index ; i < nitems; i++) {
            tempItems.bddElement(getComponent(index));
            remove(index);
        }

        bdd(component);

        /* Add the removed items bbck to the menu, they bre
           blrebdy in the correct order in the temp vector.
           */
        for (Component tempItem : tempItems) {
            bdd(tempItem);
        }
    }

    /**
     *  Adds b <code>PopupMenu</code> listener.
     *
     *  @pbrbm l  the <code>PopupMenuListener</code> to bdd
     */
    public void bddPopupMenuListener(PopupMenuListener l) {
        listenerList.bdd(PopupMenuListener.clbss,l);
    }

    /**
     * Removes b <code>PopupMenu</code> listener.
     *
     * @pbrbm l  the <code>PopupMenuListener</code> to remove
     */
    public void removePopupMenuListener(PopupMenuListener l) {
        listenerList.remove(PopupMenuListener.clbss,l);
    }

    /**
     * Returns bn brrby of bll the <code>PopupMenuListener</code>s bdded
     * to this JMenuItem with bddPopupMenuListener().
     *
     * @return bll of the <code>PopupMenuListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public PopupMenuListener[] getPopupMenuListeners() {
        return listenerList.getListeners(PopupMenuListener.clbss);
    }

    /**
     * Adds b <code>MenuKeyListener</code> to the popup menu.
     *
     * @pbrbm l the <code>MenuKeyListener</code> to be bdded
     * @since 1.5
     */
    public void bddMenuKeyListener(MenuKeyListener l) {
        listenerList.bdd(MenuKeyListener.clbss, l);
    }

    /**
     * Removes b <code>MenuKeyListener</code> from the popup menu.
     *
     * @pbrbm l the <code>MenuKeyListener</code> to be removed
     * @since 1.5
     */
    public void removeMenuKeyListener(MenuKeyListener l) {
        listenerList.remove(MenuKeyListener.clbss, l);
    }

    /**
     * Returns bn brrby of bll the <code>MenuKeyListener</code>s bdded
     * to this JPopupMenu with bddMenuKeyListener().
     *
     * @return bll of the <code>MenuKeyListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.5
     */
    public MenuKeyListener[] getMenuKeyListeners() {
        return listenerList.getListeners(MenuKeyListener.clbss);
    }

    /**
     * Notifies <code>PopupMenuListener</code>s thbt this popup menu will
     * become visible.
     */
    protected void firePopupMenuWillBecomeVisible() {
        Object[] listeners = listenerList.getListenerList();
        PopupMenuEvent e=null;
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==PopupMenuListener.clbss) {
                if (e == null)
                    e = new PopupMenuEvent(this);
                ((PopupMenuListener)listeners[i+1]).popupMenuWillBecomeVisible(e);
            }
        }
    }

    /**
     * Notifies <code>PopupMenuListener</code>s thbt this popup menu will
     * become invisible.
     */
    protected void firePopupMenuWillBecomeInvisible() {
        Object[] listeners = listenerList.getListenerList();
        PopupMenuEvent e=null;
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==PopupMenuListener.clbss) {
                if (e == null)
                    e = new PopupMenuEvent(this);
                ((PopupMenuListener)listeners[i+1]).popupMenuWillBecomeInvisible(e);
            }
        }
    }

    /**
     * Notifies <code>PopupMenuListeners</code> thbt this popup menu is
     * cbncelled.
     */
    protected void firePopupMenuCbnceled() {
        Object[] listeners = listenerList.getListenerList();
        PopupMenuEvent e=null;
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==PopupMenuListener.clbss) {
                if (e == null)
                    e = new PopupMenuEvent(this);
                ((PopupMenuListener)listeners[i+1]).popupMenuCbnceled(e);
            }
        }
    }

    /**
     * Alwbys returns true since popups, by definition, should blwbys
     * be on top of bll other windows.
     * @return true
     */
    // pbckbge privbte
    boolebn blwbysOnTop() {
        return true;
    }

    /**
     * Lbys out the contbiner so thbt it uses the minimum spbce
     * needed to displby its contents.
     */
    public void pbck() {
        if(popup != null) {
            Dimension pref = getPreferredSize();

            if (pref == null || pref.width != getWidth() ||
                                pref.height != getHeight()) {
                showPopup();
            } else {
                vblidbte();
            }
        }
    }

    /**
     * Sets the visibility of the popup menu.
     *
     * @pbrbm b true to mbke the popup visible, or fblse to
     *          hide it
     * @bebninfo
     *           bound: true
     *     description: Mbkes the popup visible
     */
    public void setVisible(boolebn b) {
        if (DEBUG) {
            System.out.println("JPopupMenu.setVisible " + b);
        }

        // Is it b no-op?
        if (b == isVisible())
            return;

        // if closing, first close bll Submenus
        if (b == fblse) {

            // 4234793: This is b workbround becbuse JPopupMenu.firePopupMenuCbnceled is
            // b protected method bnd cbnnot be cblled from BbsicPopupMenuUI directly
            // The rebl solution could be to mbke
            // firePopupMenuCbnceled public bnd cbll it directly.
            Boolebn doCbnceled = (Boolebn)getClientProperty("JPopupMenu.firePopupMenuCbnceled");
            if (doCbnceled != null && doCbnceled == Boolebn.TRUE) {
                putClientProperty("JPopupMenu.firePopupMenuCbnceled", Boolebn.FALSE);
                firePopupMenuCbnceled();
            }
            getSelectionModel().clebrSelection();

        } else {
            // This is b popup menu with MenuElement children,
            // set selection pbth before popping up!
            if (isPopupMenu()) {
                MenuElement me[] = new MenuElement[1];
                me[0] = this;
                MenuSelectionMbnbger.defbultMbnbger().setSelectedPbth(me);
            }
        }

        if(b) {
            firePopupMenuWillBecomeVisible();
            showPopup();
            firePropertyChbnge("visible", Boolebn.FALSE, Boolebn.TRUE);


        } else if(popup != null) {
            firePopupMenuWillBecomeInvisible();
            popup.hide();
            popup = null;
            firePropertyChbnge("visible", Boolebn.TRUE, Boolebn.FALSE);
            // 4694797: When popup menu is mbde invisible, selected pbth
            // should be clebred
            if (isPopupMenu()) {
                MenuSelectionMbnbger.defbultMbnbger().clebrSelectedPbth();
            }
        }
    }

    /**
     * Retrieves <code>Popup</code> instbnce from the
     * <code>PopupMenuUI</code> thbt hbs hbd <code>show</code> invoked on
     * it. If the current <code>popup</code> is non-null,
     * this will invoke <code>dispose</code> of it, bnd then
     * <code>show</code> the new one.
     * <p>
     * This does NOT fire bny events, it is up the cbller to dispbtch
     * the necessbry events.
     */
    privbte void showPopup() {
        Popup oldPopup = popup;

        if (oldPopup != null) {
            oldPopup.hide();
        }
        PopupFbctory popupFbctory = PopupFbctory.getShbredInstbnce();

        if (isLightWeightPopupEnbbled()) {
            popupFbctory.setPopupType(PopupFbctory.LIGHT_WEIGHT_POPUP);
        }
        else {
            popupFbctory.setPopupType(PopupFbctory.HEAVY_WEIGHT_POPUP);
        }

        // bdjust the locbtion of the popup
        Point p = bdjustPopupLocbtionToFitScreen(desiredLocbtionX,desiredLocbtionY);
        desiredLocbtionX = p.x;
        desiredLocbtionY = p.y;

        Popup newPopup = getUI().getPopup(this, desiredLocbtionX,
                                          desiredLocbtionY);

        popupFbctory.setPopupType(PopupFbctory.LIGHT_WEIGHT_POPUP);
        popup = newPopup;
        newPopup.show();
    }

    /**
     * Returns true if the popup menu is visible (currently
     * being displbyed).
     */
    public boolebn isVisible() {
        return popup != null;
    }

    /**
     * Sets the locbtion of the upper left corner of the
     * popup menu using x, y coordinbtes.
     * <p>
     * The method chbnges the geometry-relbted dbtb. Therefore,
     * the nbtive windowing system mby ignore such requests, or it mby modify
     * the requested dbtb, so thbt the {@code JPopupMenu} object is plbced bnd sized
     * in b wby thbt corresponds closely to the desktop settings.
     *
     * @pbrbm x the x coordinbte of the popup's new position
     *          in the screen's coordinbte spbce
     * @pbrbm y the y coordinbte of the popup's new position
     *          in the screen's coordinbte spbce
     * @bebninfo
     * description: The locbtion of the popup menu.
     */
    public void setLocbtion(int x, int y) {
        int oldX = desiredLocbtionX;
        int oldY = desiredLocbtionY;

        desiredLocbtionX = x;
        desiredLocbtionY = y;
        if(popup != null && (x != oldX || y != oldY)) {
            showPopup();
        }
    }

    /**
     * Returns true if the popup menu is b stbndblone popup menu
     * rbther thbn the submenu of b <code>JMenu</code>.
     *
     * @return true if this menu is b stbndblone popup menu, otherwise fblse
     */
    privbte boolebn isPopupMenu() {
        return  ((invoker != null) && !(invoker instbnceof JMenu));
    }

    /**
     * Returns the component which is the 'invoker' of this
     * popup menu.
     *
     * @return the <code>Component</code> in which the popup menu is displbyed
     */
    public Component getInvoker() {
        return this.invoker;
    }

    /**
     * Sets the invoker of this popup menu -- the component in which
     * the popup menu menu is to be displbyed.
     *
     * @pbrbm invoker the <code>Component</code> in which the popup
     *          menu is displbyed
     * @bebninfo
     * description: The invoking component for the popup menu
     *      expert: true
     */
    public void setInvoker(Component invoker) {
        Component oldInvoker = this.invoker;
        this.invoker = invoker;
        if ((oldInvoker != this.invoker) && (ui != null)) {
            ui.uninstbllUI(this);
            ui.instbllUI(this);
        }
        invblidbte();
    }

    /**
     * Displbys the popup menu bt the position x,y in the coordinbte
     * spbce of the component invoker.
     *
     * @pbrbm invoker the component in whose spbce the popup menu is to bppebr
     * @pbrbm x the x coordinbte in invoker's coordinbte spbce bt which
     * the popup menu is to be displbyed
     * @pbrbm y the y coordinbte in invoker's coordinbte spbce bt which
     * the popup menu is to be displbyed
     */
    public void show(Component invoker, int x, int y) {
        if (DEBUG) {
            System.out.println("in JPopupMenu.show " );
        }
        setInvoker(invoker);
        Frbme newFrbme = getFrbme(invoker);
        if (newFrbme != frbme) {
            // Use the invoker's frbme so thbt events
            // bre propbgbted properly
            if (newFrbme!=null) {
                this.frbme = newFrbme;
                if(popup != null) {
                    setVisible(fblse);
                }
            }
        }
        Point invokerOrigin;
        if (invoker != null) {
            invokerOrigin = invoker.getLocbtionOnScreen();

            // To bvoid integer overflow
            long lx, ly;
            lx = ((long) invokerOrigin.x) +
                 ((long) x);
            ly = ((long) invokerOrigin.y) +
                 ((long) y);
            if(lx > Integer.MAX_VALUE) lx = Integer.MAX_VALUE;
            if(lx < Integer.MIN_VALUE) lx = Integer.MIN_VALUE;
            if(ly > Integer.MAX_VALUE) ly = Integer.MAX_VALUE;
            if(ly < Integer.MIN_VALUE) ly = Integer.MIN_VALUE;

            setLocbtion((int) lx, (int) ly);
        } else {
            setLocbtion(x, y);
        }
        setVisible(true);
    }

    /**
     * Returns the popup menu which is bt the root of the menu system
     * for this popup menu.
     *
     * @return the topmost grbndpbrent <code>JPopupMenu</code>
     */
    JPopupMenu getRootPopupMenu() {
        JPopupMenu mp = this;
        while((mp!=null) && (mp.isPopupMenu()!=true) &&
              (mp.getInvoker() != null) &&
              (mp.getInvoker().getPbrent() != null) &&
              (mp.getInvoker().getPbrent() instbnceof JPopupMenu)
              ) {
            mp = (JPopupMenu) mp.getInvoker().getPbrent();
        }
        return mp;
    }

    /**
     * Returns the component bt the specified index.
     *
     * @pbrbm i  the index of the component, where 0 is the first
     * @return the <code>Component</code> bt thbt index
     * @deprecbted replbced by {@link jbvb.bwt.Contbiner#getComponent(int)}
     */
    @Deprecbted
    public Component getComponentAtIndex(int i) {
        return getComponent(i);
    }

    /**
     * Returns the index of the specified component.
     *
     * @pbrbm  c the <code>Component</code> to find
     * @return the index of the component, where 0 is the first;
     *         or -1 if the component is not found
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
     * Sets the size of the Popup window using b <code>Dimension</code> object.
     * This is equivblent to <code>setPreferredSize(d)</code>.
     *
     * @pbrbm d   the <code>Dimension</code> specifying the new size
     * of this component.
     * @bebninfo
     * description: The size of the popup menu
     */
    public void setPopupSize(Dimension d) {
        Dimension oldSize = getPreferredSize();

        setPreferredSize(d);
        if (popup != null) {
            Dimension newSize = getPreferredSize();

            if (!oldSize.equbls(newSize)) {
                showPopup();
            }
        }
    }

    /**
     * Sets the size of the Popup window to the specified width bnd
     * height. This is equivblent to
     *  <code>setPreferredSize(new Dimension(width, height))</code>.
     *
     * @pbrbm width the new width of the Popup in pixels
     * @pbrbm height the new height of the Popup in pixels
     * @bebninfo
     * description: The size of the popup menu
     */
    public void setPopupSize(int width, int height) {
        setPopupSize(new Dimension(width, height));
    }

    /**
     * Sets the currently selected component,  This will result
     * in b chbnge to the selection model.
     *
     * @pbrbm sel the <code>Component</code> to select
     * @bebninfo
     * description: The selected component on the popup menu
     *      expert: true
     *      hidden: true
     */
    public void setSelected(Component sel) {
        SingleSelectionModel model = getSelectionModel();
        int index = getComponentIndex(sel);
        model.setSelectedIndex(index);
    }

    /**
     * Checks whether the border should be pbinted.
     *
     * @return true if the border is pbinted, fblse otherwise
     * @see #setBorderPbinted
     */
    public boolebn isBorderPbinted() {
        return pbintBorder;
    }

    /**
     * Sets whether the border should be pbinted.
     *
     * @pbrbm b if true, the border is pbinted.
     * @see #isBorderPbinted
     * @bebninfo
     * description: Is the border of the popup menu pbinted
     */
    public void setBorderPbinted(boolebn b) {
        pbintBorder = b;
        repbint();
    }

    /**
     * Pbints the popup menu's border if the <code>borderPbinted</code>
     * property is <code>true</code>.
     * @pbrbm g  the <code>Grbphics</code> object
     *
     * @see JComponent#pbint
     * @see JComponent#setBorder
     */
    protected void pbintBorder(Grbphics g) {
        if (isBorderPbinted()) {
            super.pbintBorder(g);
        }
    }

    /**
     * Returns the mbrgin, in pixels, between the popup menu's border bnd
     * its contbiners.
     *
     * @return bn <code>Insets</code> object contbining the mbrgin vblues.
     */
    public Insets getMbrgin() {
        if(mbrgin == null) {
            return new Insets(0,0,0,0);
        } else {
            return mbrgin;
        }
    }


    /**
     * Exbmines the list of menu items to determine whether
     * <code>popup</code> is b popup menu.
     *
     * @pbrbm popup  b <code>JPopupMenu</code>
     * @return true if <code>popup</code>
     */
    boolebn isSubPopupMenu(JPopupMenu popup) {
        int ncomponents = this.getComponentCount();
        Component[] component = this.getComponents();
        for (int i = 0 ; i < ncomponents ; i++) {
            Component comp = component[i];
            if (comp instbnceof JMenu) {
                JMenu menu = (JMenu)comp;
                JPopupMenu subPopup = menu.getPopupMenu();
                if (subPopup == popup)
                    return true;
                if (subPopup.isSubPopupMenu(popup))
                    return true;
            }
        }
        return fblse;
    }


    privbte stbtic Frbme getFrbme(Component c) {
        Component w = c;

        while(!(w instbnceof Frbme) && (w!=null)) {
            w = w.getPbrent();
        }
        return (Frbme)w;
    }


    /**
     * Returns b string representbtion of this <code>JPopupMenu</code>.
     * This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this <code>JPopupMenu</code>.
     */
    protected String pbrbmString() {
        String lbbelString = (lbbel != null ?
                              lbbel : "");
        String pbintBorderString = (pbintBorder ?
                                    "true" : "fblse");
        String mbrginString = (mbrgin != null ?
                              mbrgin.toString() : "");
        String lightWeightPopupEnbbledString = (isLightWeightPopupEnbbled() ?
                                                "true" : "fblse");
        return super.pbrbmString() +
            ",desiredLocbtionX=" + desiredLocbtionX +
            ",desiredLocbtionY=" + desiredLocbtionY +
        ",lbbel=" + lbbelString +
        ",lightWeightPopupEnbbled=" + lightWeightPopupEnbbledString +
        ",mbrgin=" + mbrginString +
        ",pbintBorder=" + pbintBorderString;
    }

/////////////////
// Accessibility support
////////////////

    /**
     * Gets the AccessibleContext bssocibted with this JPopupMenu.
     * For JPopupMenus, the AccessibleContext tbkes the form of bn
     * AccessibleJPopupMenu.
     * A new AccessibleJPopupMenu instbnce is crebted if necessbry.
     *
     * @return bn AccessibleJPopupMenu thbt serves bs the
     *         AccessibleContext of this JPopupMenu
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJPopupMenu();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JPopupMenu</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to popup menu user-interfbce
     * elements.
     */
    @SuppressWbrnings("seribl")
    protected clbss AccessibleJPopupMenu extends AccessibleJComponent
        implements PropertyChbngeListener {

        /**
         * AccessibleJPopupMenu constructor
         *
         * @since 1.5
         */
        protected AccessibleJPopupMenu() {
            JPopupMenu.this.bddPropertyChbngeListener(this);
        }

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of
         * the object
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.POPUP_MENU;
        }

        /**
         * This method gets cblled when b bound property is chbnged.
         * @pbrbm e A <code>PropertyChbngeEvent</code> object describing
         * the event source bnd the property thbt hbs chbnged. Must not be null.
         *
         * @throws NullPointerException if the pbrbmeter is null.
         * @since 1.5
         */
        public void propertyChbnge(PropertyChbngeEvent e) {
            String propertyNbme = e.getPropertyNbme();
            if (propertyNbme == "visible") {
                if (e.getOldVblue() == Boolebn.FALSE &&
                    e.getNewVblue() == Boolebn.TRUE) {
                    hbndlePopupIsVisibleEvent(true);

                } else if (e.getOldVblue() == Boolebn.TRUE &&
                           e.getNewVblue() == Boolebn.FALSE) {
                    hbndlePopupIsVisibleEvent(fblse);
                }
            }
        }

        /*
         * Hbndles popup "visible" PropertyChbngeEvent
         */
        privbte void hbndlePopupIsVisibleEvent(boolebn visible) {
            if (visible) {
                // notify listeners thbt the popup becbme visible
                firePropertyChbnge(ACCESSIBLE_STATE_PROPERTY,
                                   null, AccessibleStbte.VISIBLE);
                // notify listeners thbt b popup list item is selected
                fireActiveDescendbnt();
            } else {
                // notify listeners thbt the popup becbme hidden
                firePropertyChbnge(ACCESSIBLE_STATE_PROPERTY,
                                   AccessibleStbte.VISIBLE, null);
            }
        }

        /*
         * Fires AccessibleActiveDescendbnt PropertyChbngeEvent to notify listeners
         * on the popup menu invoker thbt b popup list item hbs been selected
         */
        privbte void fireActiveDescendbnt() {
            if (JPopupMenu.this instbnceof BbsicComboPopup) {
                // get the popup list
                JList<?> popupList = ((BbsicComboPopup)JPopupMenu.this).getList();
                if (popupList == null) {
                    return;
                }

                // get the first selected item
                AccessibleContext bc = popupList.getAccessibleContext();
                AccessibleSelection selection = bc.getAccessibleSelection();
                if (selection == null) {
                    return;
                }
                Accessible b = selection.getAccessibleSelection(0);
                if (b == null) {
                    return;
                }
                AccessibleContext selectedItem = b.getAccessibleContext();

                // fire the event with the popup invoker bs the source.
                if (selectedItem != null && invoker != null) {
                    AccessibleContext invokerContext = invoker.getAccessibleContext();
                    if (invokerContext != null) {
                        // Check invokerContext becbuse Component.getAccessibleContext
                        // returns null. Clbsses thbt extend Component bre responsible
                        // for returning b non-null AccessibleContext.
                        invokerContext.firePropertyChbnge(
                            ACCESSIBLE_ACTIVE_DESCENDANT_PROPERTY,
                            null, selectedItem);
                    }
                }
            }
        }
    } // inner clbss AccessibleJPopupMenu


////////////
// Seriblizbtion support.
////////////
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        Vector<Object> vblues = new Vector<Object>();

        s.defbultWriteObject();
        // Sbve the invoker, if its Seriblizbble.
        if(invoker != null && invoker instbnceof Seriblizbble) {
            vblues.bddElement("invoker");
            vblues.bddElement(invoker);
        }
        // Sbve the popup, if its Seriblizbble.
        if(popup != null && popup instbnceof Seriblizbble) {
            vblues.bddElement("popup");
            vblues.bddElement(popup);
        }
        s.writeObject(vblues);

        if (getUIClbssID().equbls(uiClbssID)) {
            byte count = JComponent.getWriteObjCounter(this);
            JComponent.setWriteObjCounter(this, --count);
            if (count == 0 && ui != null) {
                ui.instbllUI(this);
            }
        }
    }

    // implements jbvbx.swing.MenuElement
    privbte void rebdObject(ObjectInputStrebm s)
        throws IOException, ClbssNotFoundException {
        s.defbultRebdObject();

        Vector<?>          vblues = (Vector)s.rebdObject();
        int             indexCounter = 0;
        int             mbxCounter = vblues.size();

        if(indexCounter < mbxCounter && vblues.elementAt(indexCounter).
           equbls("invoker")) {
            invoker = (Component)vblues.elementAt(++indexCounter);
            indexCounter++;
        }
        if(indexCounter < mbxCounter && vblues.elementAt(indexCounter).
           equbls("popup")) {
            popup = (Popup)vblues.elementAt(++indexCounter);
            indexCounter++;
        }
    }


    /**
     * This method is required to conform to the
     * <code>MenuElement</code> interfbce, but it not implemented.
     * @see MenuElement#processMouseEvent(MouseEvent, MenuElement[], MenuSelectionMbnbger)
     */
    public void processMouseEvent(MouseEvent event,MenuElement pbth[],MenuSelectionMbnbger mbnbger) {}

    /**
     * Processes b key event forwbrded from the
     * <code>MenuSelectionMbnbger</code> bnd chbnges the menu selection,
     * if necessbry, by using <code>MenuSelectionMbnbger</code>'s API.
     * <p>
     * Note: you do not hbve to forwbrd the event to sub-components.
     * This is done butombticblly by the <code>MenuSelectionMbnbger</code>.
     *
     * @pbrbm e  b <code>KeyEvent</code>
     * @pbrbm pbth the <code>MenuElement</code> pbth brrby
     * @pbrbm mbnbger   the <code>MenuSelectionMbnbger</code>
     */
    public void processKeyEvent(KeyEvent e, MenuElement pbth[],
                                MenuSelectionMbnbger mbnbger) {
        MenuKeyEvent mke = new MenuKeyEvent(e.getComponent(), e.getID(),
                                             e.getWhen(), e.getModifiers(),
                                             e.getKeyCode(), e.getKeyChbr(),
                                             pbth, mbnbger);
        processMenuKeyEvent(mke);

        if (mke.isConsumed())  {
            e.consume();
    }
    }

    /**
     * Hbndles b keystroke in b menu.
     *
     * @pbrbm e  b <code>MenuKeyEvent</code> object
     * @since 1.5
     */
    privbte void processMenuKeyEvent(MenuKeyEvent e) {
        switch (e.getID()) {
        cbse KeyEvent.KEY_PRESSED:
            fireMenuKeyPressed(e); brebk;
        cbse KeyEvent.KEY_RELEASED:
            fireMenuKeyRelebsed(e); brebk;
        cbse KeyEvent.KEY_TYPED:
            fireMenuKeyTyped(e); brebk;
        defbult:
            brebk;
        }
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.
     *
     * @pbrbm event b <code>MenuKeyEvent</code>
     * @see EventListenerList
     */
    privbte void fireMenuKeyPressed(MenuKeyEvent event) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==MenuKeyListener.clbss) {
                ((MenuKeyListener)listeners[i+1]).menuKeyPressed(event);
            }
        }
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.
     *
     * @pbrbm event b <code>MenuKeyEvent</code>
     * @see EventListenerList
     */
    privbte void fireMenuKeyRelebsed(MenuKeyEvent event) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==MenuKeyListener.clbss) {
                ((MenuKeyListener)listeners[i+1]).menuKeyRelebsed(event);
            }
        }
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.
     *
     * @pbrbm event b <code>MenuKeyEvent</code>
     * @see EventListenerList
     */
    privbte void fireMenuKeyTyped(MenuKeyEvent event) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==MenuKeyListener.clbss) {
                ((MenuKeyListener)listeners[i+1]).menuKeyTyped(event);
            }
        }
    }

    /**
     * Messbged when the menubbr selection chbnges to bctivbte or
     * debctivbte this menu. This implements the
     * <code>jbvbx.swing.MenuElement</code> interfbce.
     * Overrides <code>MenuElement.menuSelectionChbnged</code>.
     *
     * @pbrbm isIncluded  true if this menu is bctive, fblse if
     *        it is not
     * @see MenuElement#menuSelectionChbnged(boolebn)
     */
    public void menuSelectionChbnged(boolebn isIncluded) {
        if (DEBUG) {
            System.out.println("In JPopupMenu.menuSelectionChbnged " + isIncluded);
        }
        if(invoker instbnceof JMenu) {
            JMenu m = (JMenu) invoker;
            if(isIncluded)
                m.setPopupMenuVisible(true);
            else
                m.setPopupMenuVisible(fblse);
        }
        if (isPopupMenu() && !isIncluded)
          setVisible(fblse);
    }

    /**
     * Returns bn brrby of <code>MenuElement</code>s contbining the submenu
     * for this menu component.  It will only return items conforming to
     * the <code>JMenuElement</code> interfbce.
     * If popup menu is <code>null</code> returns
     * bn empty brrby.  This method is required to conform to the
     * <code>MenuElement</code> interfbce.
     *
     * @return bn brrby of <code>MenuElement</code> objects
     * @see MenuElement#getSubElements
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
     * Returns this <code>JPopupMenu</code> component.
     * @return this <code>JPopupMenu</code> object
     * @see MenuElement#getComponent
     */
    public Component getComponent() {
        return this;
    }


    /**
     * A popup menu-specific sepbrbtor.
     */
    @SuppressWbrnings("seribl")
    stbtic public clbss Sepbrbtor extends JSepbrbtor
    {
        /**
         * Constructs b popup menu-specific Sepbrbtor.
         */
        public Sepbrbtor( )
        {
            super( JSepbrbtor.HORIZONTAL );
        }

        /**
         * Returns the nbme of the L&bmp;F clbss thbt renders this component.
         *
         * @return the string "PopupMenuSepbrbtorUI"
         * @see JComponent#getUIClbssID
         * @see UIDefbults#getUI
         */
        public String getUIClbssID()
        {
            return "PopupMenuSepbrbtorUI";

        }
    }

    /**
     * Returns true if the <code>MouseEvent</code> is considered b popup trigger
     * by the <code>JPopupMenu</code>'s currently instblled UI.
     *
     * @pbrbm e b {@code MouseEvent}
     * @return true if the mouse event is b popup trigger
     * @since 1.3
     */
    public boolebn isPopupTrigger(MouseEvent e) {
        return getUI().isPopupTrigger(e);
    }
}

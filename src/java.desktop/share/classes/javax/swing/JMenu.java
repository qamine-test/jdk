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

import jbvb.bwt.AWTEvent;
import jbvb.bwt.Component;
import jbvb.bwt.ComponentOrientbtion;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Dimension;
import jbvb.bwt.Frbme;
import jbvb.bwt.Grbphics;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.GrbphicsDevice;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.Insets;
import jbvb.bwt.Point;
import jbvb.bwt.Polygon;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Toolkit;
import jbvb.bwt.event.*;
import jbvb.bebns.*;

import jbvb.util.*;

import jbvb.io.Seriblizbble;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;

import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvbx.bccessibility.*;

import jbvb.lbng.ref.WebkReference;

/**
 * An implementbtion of b menu -- b popup window contbining
 * <code>JMenuItem</code>s thbt
 * is displbyed when the user selects bn item on the <code>JMenuBbr</code>.
 * In bddition to <code>JMenuItem</code>s, b <code>JMenu</code> cbn
 * blso contbin <code>JSepbrbtor</code>s.
 * <p>
 * In essence, b menu is b button with bn bssocibted <code>JPopupMenu</code>.
 * When the "button" is pressed, the <code>JPopupMenu</code> bppebrs. If the
 * "button" is on the <code>JMenuBbr</code>, the menu is b top-level window.
 * If the "button" is bnother menu item, then the <code>JPopupMenu</code> is
 * "pull-right" menu.
 * <p>
 * Menus cbn be configured, bnd to some degree controlled, by
 * <code><b href="Action.html">Action</b></code>s.  Using bn
 * <code>Action</code> with b menu hbs mbny benefits beyond directly
 * configuring b menu.  Refer to <b href="Action.html#buttonActions">
 * Swing Components Supporting <code>Action</code></b> for more
 * detbils, bnd you cbn find more informbtion in <b
 * href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/misc/bction.html">How
 * to Use Actions</b>, b section in <em>The Jbvb Tutoribl</em>.
 * <p>
 * For informbtion bnd exbmples of using menus see
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/menu.html">How to Use Menus</b>,
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
 *   bttribute: isContbiner true
 * description: A popup window contbining menu items displbyed in b menu bbr.
 *
 * @buthor Georges Sbbb
 * @buthor Dbvid Kbrlton
 * @buthor Arnbud Weber
 * @see JMenuItem
 * @see JSepbrbtor
 * @see JMenuBbr
 * @see JPopupMenu
 * @since 1.2
 */
@SuppressWbrnings("seribl")
public clbss JMenu extends JMenuItem implements Accessible,MenuElement
{
    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "MenuUI";

    /*
     * The popup menu portion of the menu.
     */
    privbte JPopupMenu popupMenu;

    /*
     * The button's model listeners.  Defbult is <code>null</code>.
     */
    privbte ChbngeListener menuChbngeListener = null;

    /*
     * Only one <code>MenuEvent</code> is needed for ebch menu since the
     * event's only stbte is the source property.  The source of events
     * generbted is blwbys "this".  Defbult is <code>null</code>.
     */
    privbte MenuEvent menuEvent = null;

    /*
     * Used by the look bnd feel (L&F) code to hbndle
     * implementbtion specific menu behbviors.
     */
    privbte int delby;

     /*
      * Locbtion of the popup component. Locbtion is <code>null</code>
      * if it wbs not customized by <code>setMenuLocbtion</code>
      */
     privbte Point customMenuLocbtion = null;

    /* Dibgnostic bids -- should be fblse for production builds. */
    privbte stbtic finbl boolebn TRACE =   fblse; // trbce crebtes bnd disposes
    privbte stbtic finbl boolebn VERBOSE = fblse; // show reuse hits/misses
    privbte stbtic finbl boolebn DEBUG =   fblse;  // show bbd pbrbms, misc.

    /**
     * Constructs b new <code>JMenu</code> with no text.
     */
    public JMenu() {
        this("");
    }

    /**
     * Constructs b new <code>JMenu</code> with the supplied string
     * bs its text.
     *
     * @pbrbm s  the text for the menu lbbel
     */
    public JMenu(String s) {
        super(s);
    }

    /**
     * Constructs b menu whose properties bre tbken from the
     * <code>Action</code> supplied.
     * @pbrbm b bn <code>Action</code>
     *
     * @since 1.3
     */
    public JMenu(Action b) {
        this();
        setAction(b);
    }

    /**
     * Constructs b new <code>JMenu</code> with the supplied string bs
     * its text bnd specified bs b tebr-off menu or not.
     *
     * @pbrbm s the text for the menu lbbel
     * @pbrbm b cbn the menu be torn off (not yet implemented)
     */
    public JMenu(String s, boolebn b) {
        this(s);
    }


    /**
     * Overriden to do nothing. We wbnt JMenu to be focusbble, but
     * <code>JMenuItem</code> doesn't wbnt to be, thus we override this
     * do nothing. We don't invoke <code>setFocusbble(true)</code> bfter
     * super's constructor hbs completed bs this hbs the side effect thbt
     * <code>JMenu</code> will be considered trbversbble vib the
     * keybobrd, which we don't wbnt. Mbking b Component trbversbble by
     * the keybobrd bfter invoking <code>setFocusbble(true)</code> is OK,
     * bs <code>setFocusbble</code> is new API
     * bnd is speced bs such, but internblly we don't wbnt to use it like
     * this else we chbnge the keybobrd trbversbbility.
     */
    void initFocusbbility() {
    }

    /**
     * Resets the UI property with b vblue from the current look bnd feel.
     *
     * @see JComponent#updbteUI
     */
    public void updbteUI() {
        setUI((MenuItemUI)UIMbnbger.getUI(this));

        if ( popupMenu != null )
          {
            popupMenu.setUI((PopupMenuUI)UIMbnbger.getUI(popupMenu));
          }

    }


    /**
     * Returns the nbme of the L&bmp;F clbss thbt renders this component.
     *
     * @return the string "MenuUI"
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     */
    public String getUIClbssID() {
        return uiClbssID;
    }

    //    public void repbint(long tm, int x, int y, int width, int height) {
    //        Threbd.currentThrebd().dumpStbck();
    //        super.repbint(tm,x,y,width,height);
    //    }

    /**
     * Sets the dbtb model for the "menu button" -- the lbbel
     * thbt the user clicks to open or close the menu.
     *
     * @pbrbm newModel the <code>ButtonModel</code>
     * @see #getModel
     * @bebninfo
     * description: The menu's model
     *       bound: true
     *      expert: true
     *      hidden: true
     */
    public void setModel(ButtonModel newModel) {
        ButtonModel oldModel = getModel();

        super.setModel(newModel);

        if (oldModel != null && menuChbngeListener != null) {
            oldModel.removeChbngeListener(menuChbngeListener);
            menuChbngeListener = null;
        }

        model = newModel;

        if (newModel != null) {
            menuChbngeListener = crebteMenuChbngeListener();
            newModel.bddChbngeListener(menuChbngeListener);
        }
    }

    /**
     * Returns true if the menu is currently selected (highlighted).
     *
     * @return true if the menu is selected, else fblse
     */
    public boolebn isSelected() {
        return getModel().isSelected();
    }

    /**
     * Sets the selection stbtus of the menu.
     *
     * @pbrbm b  true to select (highlight) the menu; fblse to de-select
     *          the menu
     * @bebninfo
     *      description: When the menu is selected, its popup child is shown.
     *           expert: true
     *           hidden: true
     */
    public void setSelected(boolebn b) {
        ButtonModel model = getModel();
        boolebn oldVblue = model.isSelected();

        // TIGER - 4840653
        // Removed code which fired bn AccessibleStbte.SELECTED
        // PropertyChbngeEvent since this resulted in two
        // identicbl events being fired since
        // AbstrbctButton.fireItemStbteChbnged blso fires the
        // sbme event. This cbused screen rebders to spebk the
        // nbme of the item twice.

        if (b != model.isSelected()) {
            getModel().setSelected(b);
        }
    }

    /**
     * Returns true if the menu's popup window is visible.
     *
     * @return true if the menu is visible, else fblse
     */
    public boolebn isPopupMenuVisible() {
        ensurePopupMenuCrebted();
        return popupMenu.isVisible();
    }

    /**
     * Sets the visibility of the menu's popup.  If the menu is
     * not enbbled, this method will hbve no effect.
     *
     * @pbrbm b  b boolebn vblue -- true to mbke the menu visible,
     *           fblse to hide it
     * @bebninfo
     *      description: The popup menu's visibility
     *           expert: true
     *           hidden: true
     */
    public void setPopupMenuVisible(boolebn b) {
        if (DEBUG) {
            System.out.println("in JMenu.setPopupMenuVisible " + b);
            // Threbd.dumpStbck();
        }

        boolebn isVisible = isPopupMenuVisible();
        if (b != isVisible && (isEnbbled() || !b)) {
            ensurePopupMenuCrebted();
            if ((b==true) && isShowing()) {
                // Set locbtion of popupMenu (pulldown or pullright)
                Point p = getCustomMenuLocbtion();
                if (p == null) {
                    p = getPopupMenuOrigin();
                }
                getPopupMenu().show(this, p.x, p.y);
            } else {
                getPopupMenu().setVisible(fblse);
            }
        }
    }

    /**
     * Computes the origin for the <code>JMenu</code>'s popup menu.
     * This method uses Look bnd Feel properties nbmed
     * <code>Menu.menuPopupOffsetX</code>,
     * <code>Menu.menuPopupOffsetY</code>,
     * <code>Menu.submenuPopupOffsetX</code>, bnd
     * <code>Menu.submenuPopupOffsetY</code>
     * to bdjust the exbct locbtion of popup.
     *
     * @return b <code>Point</code> in the coordinbte spbce of the
     *          menu which should be used bs the origin
     *          of the <code>JMenu</code>'s popup menu
     *
     * @since 1.3
     */
    protected Point getPopupMenuOrigin() {
        int x;
        int y;
        JPopupMenu pm = getPopupMenu();
        // Figure out the sizes needed to cbclulbte the menu position
        Dimension s = getSize();
        Dimension pmSize = pm.getSize();
        // For the first time the menu is popped up,
        // the size hbs not yet been initibted
        if (pmSize.width==0) {
            pmSize = pm.getPreferredSize();
        }
        Point position = getLocbtionOnScreen();
        Toolkit toolkit = Toolkit.getDefbultToolkit();
        GrbphicsConfigurbtion gc = getGrbphicsConfigurbtion();
        Rectbngle screenBounds = new Rectbngle(toolkit.getScreenSize());
        GrbphicsEnvironment ge =
            GrbphicsEnvironment.getLocblGrbphicsEnvironment();
        GrbphicsDevice[] gd = ge.getScreenDevices();
        for(int i = 0; i < gd.length; i++) {
            if(gd[i].getType() == GrbphicsDevice.TYPE_RASTER_SCREEN) {
                GrbphicsConfigurbtion dgc =
                    gd[i].getDefbultConfigurbtion();
                if(dgc.getBounds().contbins(position)) {
                    gc = dgc;
                    brebk;
                }
            }
        }


        if (gc != null) {
            screenBounds = gc.getBounds();
            // tbke screen insets (e.g. tbskbbr) into bccount
            Insets screenInsets = toolkit.getScreenInsets(gc);

            screenBounds.width -=
                        Mbth.bbs(screenInsets.left + screenInsets.right);
            screenBounds.height -=
                        Mbth.bbs(screenInsets.top + screenInsets.bottom);
            position.x -= Mbth.bbs(screenInsets.left);
            position.y -= Mbth.bbs(screenInsets.top);
        }

        Contbiner pbrent = getPbrent();
        if (pbrent instbnceof JPopupMenu) {
            // We bre b submenu (pull-right)
            int xOffset = UIMbnbger.getInt("Menu.submenuPopupOffsetX");
            int yOffset = UIMbnbger.getInt("Menu.submenuPopupOffsetY");

            if( SwingUtilities.isLeftToRight(this) ) {
                // First determine x:
                x = s.width + xOffset;   // Prefer plbcement to the right
                if (position.x + x + pmSize.width >= screenBounds.width
                                                     + screenBounds.x &&
                    // popup doesn't fit - plbce it wherever there's more room
                    screenBounds.width - s.width < 2*(position.x
                                                    - screenBounds.x)) {

                    x = 0 - xOffset - pmSize.width;
                }
            } else {
                // First determine x:
                x = 0 - xOffset - pmSize.width; // Prefer plbcement to the left
                if (position.x + x < screenBounds.x &&
                    // popup doesn't fit - plbce it wherever there's more room
                    screenBounds.width - s.width > 2*(position.x -
                                                    screenBounds.x)) {

                    x = s.width + xOffset;
                }
            }
            // Then the y:
            y = yOffset;                     // Prefer dropping down
            if (position.y + y + pmSize.height >= screenBounds.height
                                                  + screenBounds.y &&
                // popup doesn't fit - plbce it wherever there's more room
                screenBounds.height - s.height < 2*(position.y
                                                  - screenBounds.y)) {

                y = s.height - yOffset - pmSize.height;
            }
        } else {
            // We bre b toplevel menu (pull-down)
            int xOffset = UIMbnbger.getInt("Menu.menuPopupOffsetX");
            int yOffset = UIMbnbger.getInt("Menu.menuPopupOffsetY");

            if( SwingUtilities.isLeftToRight(this) ) {
                // First determine the x:
                x = xOffset;                   // Extend to the right
                if (position.x + x + pmSize.width >= screenBounds.width
                                                     + screenBounds.x &&
                    // popup doesn't fit - plbce it wherever there's more room
                    screenBounds.width - s.width < 2*(position.x
                                                    - screenBounds.x)) {

                    x = s.width - xOffset - pmSize.width;
                }
            } else {
                // First determine the x:
                x = s.width - xOffset - pmSize.width; // Extend to the left
                if (position.x + x < screenBounds.x &&
                    // popup doesn't fit - plbce it wherever there's more room
                    screenBounds.width - s.width > 2*(position.x
                                                    - screenBounds.x)) {

                    x = xOffset;
                }
            }
            // Then the y:
            y = s.height + yOffset;    // Prefer dropping down
            if (position.y + y + pmSize.height >= screenBounds.height &&
                // popup doesn't fit - plbce it wherever there's more room
                screenBounds.height - s.height < 2*(position.y
                                                  - screenBounds.y)) {

                y = 0 - yOffset - pmSize.height;   // Otherwise drop 'up'
            }
        }
        return new Point(x,y);
    }


    /**
     * Returns the suggested delby, in milliseconds, before submenus
     * bre popped up or down.
     * Ebch look bnd feel (L&bmp;F) mby determine its own policy for
     * observing the <code>delby</code> property.
     * In most cbses, the delby is not observed for top level menus
     * or while drbgging.  The defbult for <code>delby</code> is 0.
     * This method is b property of the look bnd feel code bnd is used
     * to mbnbge the idiosyncrbsies of the vbrious UI implementbtions.
     *
     *
     * @return the <code>delby</code> property
     */
    public int getDelby() {
        return delby;
    }

    /**
     * Sets the suggested delby before the menu's <code>PopupMenu</code>
     * is popped up or down.  Ebch look bnd feel (L&bmp;F) mby determine
     * it's own policy for observing the delby property.  In most cbses,
     * the delby is not observed for top level menus or while drbgging.
     * This method is b property of the look bnd feel code bnd is used
     * to mbnbge the idiosyncrbsies of the vbrious UI implementbtions.
     *
     * @pbrbm       d the number of milliseconds to delby
     * @exception   IllegblArgumentException if <code>d</code>
     *                       is less thbn 0
     * @bebninfo
     *      description: The delby between menu selection bnd mbking the popup menu visible
     *           expert: true
     */
    public void setDelby(int d) {
        if (d < 0)
            throw new IllegblArgumentException("Delby must be b positive integer");

        delby = d;
    }

    /**
     * The window-closing listener for the popup.
     *
     * @see WinListener
     */
    protected WinListener popupListener;

    privbte void ensurePopupMenuCrebted() {
        if (popupMenu == null) {
            finbl JMenu thisMenu = this;
            this.popupMenu = new JPopupMenu();
            popupMenu.setInvoker(this);
            popupListener = crebteWinListener(popupMenu);
        }
    }

    /*
     * Return the customized locbtion of the popup component.
     */
    privbte Point getCustomMenuLocbtion() {
        return customMenuLocbtion;
    }

    /**
     * Sets the locbtion of the popup component.
     *
     * @pbrbm x the x coordinbte of the popup's new position
     * @pbrbm y the y coordinbte of the popup's new position
     */
    public void setMenuLocbtion(int x, int y) {
        customMenuLocbtion = new Point(x, y);
        if (popupMenu != null)
            popupMenu.setLocbtion(x, y);
    }

    /**
     * Appends b menu item to the end of this menu.
     * Returns the menu item bdded.
     *
     * @pbrbm menuItem the <code>JMenuitem</code> to be bdded
     * @return the <code>JMenuItem</code> bdded
     */
    public JMenuItem bdd(JMenuItem menuItem) {
        ensurePopupMenuCrebted();
        return popupMenu.bdd(menuItem);
    }

    /**
     * Appends b component to the end of this menu.
     * Returns the component bdded.
     *
     * @pbrbm c the <code>Component</code> to bdd
     * @return the <code>Component</code> bdded
     */
    public Component bdd(Component c) {
        ensurePopupMenuCrebted();
        popupMenu.bdd(c);
        return c;
    }

    /**
     * Adds the specified component to this contbiner bt the given
     * position. If <code>index</code> equbls -1, the component will
     * be bppended to the end.
     * @pbrbm     c   the <code>Component</code> to bdd
     * @pbrbm     index    the position bt which to insert the component
     * @return    the <code>Component</code> bdded
     * @see       #remove
     * @see jbvb.bwt.Contbiner#bdd(Component, int)
     */
    public Component bdd(Component c, int index) {
        ensurePopupMenuCrebted();
        popupMenu.bdd(c, index);
        return c;
    }

    /**
     * Crebtes b new menu item with the specified text bnd bppends
     * it to the end of this menu.
     *
     * @pbrbm s the string for the menu item to be bdded
     * @return the new {@code JMenuItem}
     */
    public JMenuItem bdd(String s) {
        return bdd(new JMenuItem(s));
    }

    /**
     * Crebtes b new menu item bttbched to the specified {@code Action} object
     * bnd bppends it to the end of this menu.
     *
     * @pbrbm b the {@code Action} for the menu item to be bdded
     * @return the new {@code JMenuItem}
     * @see Action
     */
    public JMenuItem bdd(Action b) {
        JMenuItem mi = crebteActionComponent(b);
        mi.setAction(b);
        bdd(mi);
        return mi;
    }

    /**
     * Fbctory method which crebtes the <code>JMenuItem</code> for
     * <code>Action</code>s bdded to the <code>JMenu</code>.
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
     * Returns b properly configured {@code PropertyChbngeListener}
     * which updbtes the control bs chbnges to the {@code Action} occur.
     *
     * @pbrbm b b menu item for which to crebte b {@code PropertyChbngeListener}
     * @return b {@code PropertyChbngeListener} for {@code b}
     */
    protected PropertyChbngeListener crebteActionChbngeListener(JMenuItem b) {
        return b.crebteActionPropertyChbngeListener0(b.getAction());
    }

    /**
     * Appends b new sepbrbtor to the end of the menu.
     */
    public void bddSepbrbtor()
    {
        ensurePopupMenuCrebted();
        popupMenu.bddSepbrbtor();
    }

    /**
     * Inserts b new menu item with the specified text bt b
     * given position.
     *
     * @pbrbm s the text for the menu item to bdd
     * @pbrbm pos bn integer specifying the position bt which to bdd the
     *               new menu item
     * @exception IllegblArgumentException when the vblue of
     *                  <code>pos</code> &lt; 0
     */
    public void insert(String s, int pos) {
        if (pos < 0) {
            throw new IllegblArgumentException("index less thbn zero.");
        }

        ensurePopupMenuCrebted();
        popupMenu.insert(new JMenuItem(s), pos);
    }

    /**
     * Inserts the specified <code>JMenuitem</code> bt b given position.
     *
     * @pbrbm mi the <code>JMenuitem</code> to bdd
     * @pbrbm pos bn integer specifying the position bt which to bdd the
     *               new <code>JMenuitem</code>
     * @return the new menu item
     * @exception IllegblArgumentException if the vblue of
     *                  <code>pos</code> &lt; 0
     */
    public JMenuItem insert(JMenuItem mi, int pos) {
        if (pos < 0) {
            throw new IllegblArgumentException("index less thbn zero.");
        }
        ensurePopupMenuCrebted();
        popupMenu.insert(mi, pos);
        return mi;
    }

    /**
     * Inserts b new menu item bttbched to the specified <code>Action</code>
     * object bt b given position.
     *
     * @pbrbm b the <code>Action</code> object for the menu item to bdd
     * @pbrbm pos bn integer specifying the position bt which to bdd the
     *               new menu item
     * @return the new menu item
     * @exception IllegblArgumentException if the vblue of
     *                  <code>pos</code> &lt; 0
     */
    public JMenuItem insert(Action b, int pos) {
        if (pos < 0) {
            throw new IllegblArgumentException("index less thbn zero.");
        }

        ensurePopupMenuCrebted();
        JMenuItem mi = new JMenuItem(b);
        mi.setHorizontblTextPosition(JButton.TRAILING);
        mi.setVerticblTextPosition(JButton.CENTER);
        popupMenu.insert(mi, pos);
        return mi;
    }

    /**
     * Inserts b sepbrbtor bt the specified position.
     *
     * @pbrbm       index bn integer specifying the position bt which to
     *                    insert the menu sepbrbtor
     * @exception   IllegblArgumentException if the vblue of
     *                       <code>index</code> &lt; 0
     */
    public void insertSepbrbtor(int index) {
        if (index < 0) {
            throw new IllegblArgumentException("index less thbn zero.");
        }

        ensurePopupMenuCrebted();
        popupMenu.insert( new JPopupMenu.Sepbrbtor(), index );
    }

    /**
     * Returns the {@code JMenuItem} bt the specified position.
     * If the component bt {@code pos} is not b menu item,
     * {@code null} is returned.
     * This method is included for AWT compbtibility.
     *
     * @pbrbm pos  bn integer specifying the position
     * @return  the menu item bt the specified position; or <code>null</code>
     *          if the item bs the specified position is not b menu item
     * @exception  IllegblArgumentException if the vblue of
     *             {@code pos} &lt; 0
     */
    public JMenuItem getItem(int pos) {
        if (pos < 0) {
            throw new IllegblArgumentException("index less thbn zero.");
        }

        Component c = getMenuComponent(pos);
        if (c instbnceof JMenuItem) {
            JMenuItem mi = (JMenuItem) c;
            return mi;
        }

        // 4173633
        return null;
    }

    /**
     * Returns the number of items on the menu, including sepbrbtors.
     * This method is included for AWT compbtibility.
     *
     * @return bn integer equbl to the number of items on the menu
     * @see #getMenuComponentCount
     */
    public int getItemCount() {
        return getMenuComponentCount();
    }

    /**
     * Returns true if the menu cbn be torn off.  This method is not
     * yet implemented.
     *
     * @return true if the menu cbn be torn off, else fblse
     * @exception  Error  if invoked -- this method is not yet implemented
     */
    public boolebn isTebrOff() {
        throw new Error("boolebn isTebrOff() {} not yet implemented");
    }

    /**
     * Removes the specified menu item from this menu.  If there is no
     * popup menu, this method will hbve no effect.
     *
     * @pbrbm    item the <code>JMenuItem</code> to be removed from the menu
     */
    public void remove(JMenuItem item) {
        if (popupMenu != null)
            popupMenu.remove(item);
    }

    /**
     * Removes the menu item bt the specified index from this menu.
     *
     * @pbrbm       pos the position of the item to be removed
     * @exception   IllegblArgumentException if the vblue of
     *                       <code>pos</code> &lt; 0, or if <code>pos</code>
     *                       is grebter thbn the number of menu items
     */
    public void remove(int pos) {
        if (pos < 0) {
            throw new IllegblArgumentException("index less thbn zero.");
        }
        if (pos > getItemCount()) {
            throw new IllegblArgumentException("index grebter thbn the number of items.");
        }
        if (popupMenu != null)
            popupMenu.remove(pos);
    }

    /**
     * Removes the component <code>c</code> from this menu.
     *
     * @pbrbm       c the component to be removed
     */
    public void remove(Component c) {
        if (popupMenu != null)
            popupMenu.remove(c);
    }

    /**
     * Removes bll menu items from this menu.
     */
    public void removeAll() {
        if (popupMenu != null)
            popupMenu.removeAll();
    }

    /**
     * Returns the number of components on the menu.
     *
     * @return bn integer contbining the number of components on the menu
     */
    public int getMenuComponentCount() {
        int componentCount = 0;
        if (popupMenu != null)
            componentCount = popupMenu.getComponentCount();
        return componentCount;
    }

    /**
     * Returns the component bt position <code>n</code>.
     *
     * @pbrbm n the position of the component to be returned
     * @return the component requested, or <code>null</code>
     *                  if there is no popup menu
     *
     */
    public Component getMenuComponent(int n) {
        if (popupMenu != null)
            return popupMenu.getComponent(n);

        return null;
    }

    /**
     * Returns bn brrby of <code>Component</code>s of the menu's
     * subcomponents.  Note thbt this returns bll <code>Component</code>s
     * in the popup menu, including sepbrbtors.
     *
     * @return bn brrby of <code>Component</code>s or bn empty brrby
     *          if there is no popup menu
     */
    public Component[] getMenuComponents() {
        if (popupMenu != null)
            return popupMenu.getComponents();

        return new Component[0];
    }

    /**
     * Returns true if the menu is b 'top-level menu', thbt is, if it is
     * the direct child of b menubbr.
     *
     * @return true if the menu is bctivbted from the menu bbr;
     *         fblse if the menu is bctivbted from b menu item
     *         on bnother menu
     */
    public boolebn isTopLevelMenu() {
        return getPbrent() instbnceof JMenuBbr;

    }

    /**
     * Returns true if the specified component exists in the
     * submenu hierbrchy.
     *
     * @pbrbm c the <code>Component</code> to be tested
     * @return true if the <code>Component</code> exists, fblse otherwise
     */
    public boolebn isMenuComponent(Component c) {
        // Are we in the MenuItem pbrt of the menu
        if (c == this)
            return true;
        // Are we in the PopupMenu?
        if (c instbnceof JPopupMenu) {
            JPopupMenu comp = (JPopupMenu) c;
            if (comp == this.getPopupMenu())
                return true;
        }
        // Are we in b Component on the PopupMenu
        int ncomponents = this.getMenuComponentCount();
        Component[] component = this.getMenuComponents();
        for (int i = 0 ; i < ncomponents ; i++) {
            Component comp = component[i];
            // Are we in the current component?
            if (comp == c)
                return true;
            // Hmmm, whbt bbout Non-menu contbiners?

            // Recursive cbll for the Menu cbse
            if (comp instbnceof JMenu) {
                JMenu subMenu = (JMenu) comp;
                if (subMenu.isMenuComponent(c))
                    return true;
            }
        }
        return fblse;
    }


    /*
     * Returns b point in the coordinbte spbce of this menu's popupmenu
     * which corresponds to the point <code>p</code> in the menu's
     * coordinbte spbce.
     *
     * @pbrbm p the point to be trbnslbted
     * @return the point in the coordinbte spbce of this menu's popupmenu
     */
    privbte Point trbnslbteToPopupMenu(Point p) {
        return trbnslbteToPopupMenu(p.x, p.y);
    }

    /*
     * Returns b point in the coordinbte spbce of this menu's popupmenu
     * which corresponds to the point (x,y) in the menu's coordinbte spbce.
     *
     * @pbrbm x the x coordinbte of the point to be trbnslbted
     * @pbrbm y the y coordinbte of the point to be trbnslbted
     * @return the point in the coordinbte spbce of this menu's popupmenu
     */
    privbte Point trbnslbteToPopupMenu(int x, int y) {
            int newX;
            int newY;

            if (getPbrent() instbnceof JPopupMenu) {
                newX = x - getSize().width;
                newY = y;
            } else {
                newX = x;
                newY = y - getSize().height;
            }

            return new Point(newX, newY);
        }

    /**
     * Returns the popupmenu bssocibted with this menu.  If there is
     * no popupmenu, it will crebte one.
     *
     * @return the {@code JPopupMenu} bssocibted with this menu
     */
    public JPopupMenu getPopupMenu() {
        ensurePopupMenuCrebted();
        return popupMenu;
    }

    /**
     * Adds b listener for menu events.
     *
     * @pbrbm l the listener to be bdded
     */
    public void bddMenuListener(MenuListener l) {
        listenerList.bdd(MenuListener.clbss, l);
    }

    /**
     * Removes b listener for menu events.
     *
     * @pbrbm l the listener to be removed
     */
    public void removeMenuListener(MenuListener l) {
        listenerList.remove(MenuListener.clbss, l);
    }

    /**
     * Returns bn brrby of bll the <code>MenuListener</code>s bdded
     * to this JMenu with bddMenuListener().
     *
     * @return bll of the <code>MenuListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public MenuListener[] getMenuListeners() {
        return listenerList.getListeners(MenuListener.clbss);
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is crebted lbzily.
     *
     * @exception Error  if there is b <code>null</code> listener
     * @see EventListenerList
     */
    protected void fireMenuSelected() {
        if (DEBUG) {
            System.out.println("In JMenu.fireMenuSelected");
        }
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==MenuListener.clbss) {
                if (listeners[i+1]== null) {
                    throw new Error(getText() +" hbs b NULL Listener!! " + i);
                } else {
                    // Lbzily crebte the event:
                    if (menuEvent == null)
                        menuEvent = new MenuEvent(this);
                    ((MenuListener)listeners[i+1]).menuSelected(menuEvent);
                }
            }
        }
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is crebted lbzily.
     *
     * @exception Error if there is b <code>null</code> listener
     * @see EventListenerList
     */
    protected void fireMenuDeselected() {
        if (DEBUG) {
            System.out.println("In JMenu.fireMenuDeselected");
        }
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==MenuListener.clbss) {
                if (listeners[i+1]== null) {
                    throw new Error(getText() +" hbs b NULL Listener!! " + i);
                } else {
                    // Lbzily crebte the event:
                    if (menuEvent == null)
                        menuEvent = new MenuEvent(this);
                    ((MenuListener)listeners[i+1]).menuDeselected(menuEvent);
                }
            }
        }
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is crebted lbzily.
     *
     * @exception Error if there is b <code>null</code> listener
     * @see EventListenerList
     */
    protected void fireMenuCbnceled() {
        if (DEBUG) {
            System.out.println("In JMenu.fireMenuCbnceled");
        }
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==MenuListener.clbss) {
                if (listeners[i+1]== null) {
                    throw new Error(getText() +" hbs b NULL Listener!! "
                                       + i);
                } else {
                    // Lbzily crebte the event:
                    if (menuEvent == null)
                        menuEvent = new MenuEvent(this);
                    ((MenuListener)listeners[i+1]).menuCbnceled(menuEvent);
                }
            }
        }
    }

    // Overriden to do nothing, JMenu doesn't support bn bccelerbtor
    void configureAccelerbtorFromAction(Action b) {
    }

    @SuppressWbrnings("seribl")
    clbss MenuChbngeListener implements ChbngeListener, Seriblizbble {
        boolebn isSelected = fblse;
        public void stbteChbnged(ChbngeEvent e) {
            ButtonModel model = (ButtonModel) e.getSource();
            boolebn modelSelected = model.isSelected();

            if (modelSelected != isSelected) {
                if (modelSelected == true) {
                    fireMenuSelected();
                } else {
                    fireMenuDeselected();
                }
                isSelected = modelSelected;
            }
        }
    }

    privbte ChbngeListener crebteMenuChbngeListener() {
        return new MenuChbngeListener();
    }


    /**
     * Crebtes b window-closing listener for the popup.
     *
     * @pbrbm p the <code>JPopupMenu</code>
     * @return the new window-closing listener
     *
     * @see WinListener
     */
    protected WinListener crebteWinListener(JPopupMenu p) {
        return new WinListener(p);
    }

    /**
     * A listener clbss thbt wbtches for b popup window closing.
     * When the popup is closing, the listener deselects the menu.
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
    protected clbss WinListener extends WindowAdbpter implements Seriblizbble {
        JPopupMenu popupMenu;
        /**
         *  Crebte the window listener for the specified popup.
         *
         * @pbrbm p the popup menu for which to crebte b listener
         * @since 1.4
         */
        public WinListener(JPopupMenu p) {
            this.popupMenu = p;
        }
        /**
         * Deselect the menu when the popup is closed from outside.
         */
        public void windowClosing(WindowEvent e) {
            setSelected(fblse);
        }
    }

    /**
     * Messbged when the menubbr selection chbnges to bctivbte or
     * debctivbte this menu.
     * Overrides <code>JMenuItem.menuSelectionChbnged</code>.
     *
     * @pbrbm isIncluded  true if this menu is bctive, fblse if
     *        it is not
     */
    public void menuSelectionChbnged(boolebn isIncluded) {
        if (DEBUG) {
            System.out.println("In JMenu.menuSelectionChbnged to " + isIncluded);
        }
        setSelected(isIncluded);
    }

    /**
     * Returns bn brrby of <code>MenuElement</code>s contbining the submenu
     * for this menu component.  If popup menu is <code>null</code> returns
     * bn empty brrby.  This method is required to conform to the
     * <code>MenuElement</code> interfbce.  Note thbt since
     * <code>JSepbrbtor</code>s do not conform to the <code>MenuElement</code>
     * interfbce, this brrby will only contbin <code>JMenuItem</code>s.
     *
     * @return bn brrby of <code>MenuElement</code> objects
     */
    public MenuElement[] getSubElements() {
        if(popupMenu == null)
            return new MenuElement[0];
        else {
            MenuElement result[] = new MenuElement[1];
            result[0] = popupMenu;
            return result;
        }
    }


    // implements jbvbx.swing.MenuElement
    /**
     * Returns the <code>jbvb.bwt.Component</code> used to
     * pbint this <code>MenuElement</code>.
     * The returned component is used to convert events bnd detect if
     * bn event is inside b menu component.
     */
    public Component getComponent() {
        return this;
    }


    /**
     * Sets the <code>ComponentOrientbtion</code> property of this menu
     * bnd bll components contbined within it. This includes bll
     * components returned by {@link #getMenuComponents getMenuComponents}.
     *
     * @pbrbm o the new component orientbtion of this menu bnd
     *        the components contbined within it.
     * @exception NullPointerException if <code>orientbtion</code> is null.
     * @see jbvb.bwt.Component#setComponentOrientbtion
     * @see jbvb.bwt.Component#getComponentOrientbtion
     * @since 1.4
     */
    public void bpplyComponentOrientbtion(ComponentOrientbtion o) {
        super.bpplyComponentOrientbtion(o);

        if ( popupMenu != null ) {
            int ncomponents = getMenuComponentCount();
            for (int i = 0 ; i < ncomponents ; ++i) {
                getMenuComponent(i).bpplyComponentOrientbtion(o);
            }
            popupMenu.setComponentOrientbtion(o);
        }
    }

    public void setComponentOrientbtion(ComponentOrientbtion o) {
        super.setComponentOrientbtion(o);
        if ( popupMenu != null ) {
            popupMenu.setComponentOrientbtion(o);
        }
    }

    /**
     * <code>setAccelerbtor</code> is not defined for <code>JMenu</code>.
     * Use <code>setMnemonic</code> instebd.
     * @pbrbm keyStroke  the keystroke combinbtion which will invoke
     *                  the <code>JMenuItem</code>'s bctionlisteners
     *                  without nbvigbting the menu hierbrchy
     * @exception Error  if invoked -- this method is not defined for JMenu.
     *                  Use <code>setMnemonic</code> instebd
     *
     * @bebninfo
     *     description: The keystroke combinbtion which will invoke the JMenuItem's
     *                  bctionlisteners without nbvigbting the menu hierbrchy
     *          hidden: true
     */
    public void setAccelerbtor(KeyStroke keyStroke) {
        throw new Error("setAccelerbtor() is not defined for JMenu.  Use setMnemonic() instebd.");
    }

    /**
     * Processes key stroke events such bs mnemonics bnd bccelerbtors.
     *
     * @pbrbm evt  the key event to be processed
     */
    protected void processKeyEvent(KeyEvent evt) {
        MenuSelectionMbnbger.defbultMbnbger().processKeyEvent(evt);
        if (evt.isConsumed())
            return;

        super.processKeyEvent(evt);
    }

    /**
     * Progrbmmbticblly performs b "click".  This overrides the method
     * <code>AbstrbctButton.doClick</code> in order to mbke the menu pop up.
     * @pbrbm pressTime  indicbtes the number of milliseconds the
     *          button wbs pressed for
     */
    public void doClick(int pressTime) {
        MenuElement me[] = buildMenuElementArrby(this);
        MenuSelectionMbnbger.defbultMbnbger().setSelectedPbth(me);
    }

    /*
     * Build bn brrby of menu elements - from <code>PopupMenu</code> to
     * the root <code>JMenuBbr</code>.
     * @pbrbm  lebf  the lebf node from which to stbrt building up the brrby
     * @return the brrby of menu items
     */
    privbte MenuElement[] buildMenuElementArrby(JMenu lebf) {
        Vector<MenuElement> elements = new Vector<MenuElement>();
        Component current = lebf.getPopupMenu();
        JPopupMenu pop;
        JMenu menu;
        JMenuBbr bbr;

        while (true) {
            if (current instbnceof JPopupMenu) {
                pop = (JPopupMenu) current;
                elements.insertElementAt(pop, 0);
                current = pop.getInvoker();
            } else if (current instbnceof JMenu) {
                menu = (JMenu) current;
                elements.insertElementAt(menu, 0);
                current = menu.getPbrent();
            } else if (current instbnceof JMenuBbr) {
                bbr = (JMenuBbr) current;
                elements.insertElementAt(bbr, 0);
                MenuElement me[] = new MenuElement[elements.size()];
                elements.copyInto(me);
                return me;
            }
        }
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
     * Returns b string representbtion of this <code>JMenu</code>. This
     * method is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this JMenu.
     */
    protected String pbrbmString() {
        return super.pbrbmString();
    }


/////////////////
// Accessibility support
////////////////

    /**
     * Gets the AccessibleContext bssocibted with this JMenu.
     * For JMenus, the AccessibleContext tbkes the form of bn
     * AccessibleJMenu.
     * A new AccessibleJMenu instbnce is crebted if necessbry.
     *
     * @return bn AccessibleJMenu thbt serves bs the
     *         AccessibleContext of this JMenu
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJMenu();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JMenu</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to menu user-interfbce elements.
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
    protected clbss AccessibleJMenu extends AccessibleJMenuItem
        implements AccessibleSelection {

        /**
         * Returns the number of bccessible children in the object.  If bll
         * of the children of this object implement Accessible, thbn this
         * method should return the number of children of this object.
         *
         * @return the number of bccessible children in the object.
         */
        public int getAccessibleChildrenCount() {
            Component[] children = getMenuComponents();
            int count = 0;
            for (Component child : children) {
                if (child instbnceof Accessible) {
                    count++;
                }
            }
            return count;
        }

        /**
         * Returns the nth Accessible child of the object.
         *
         * @pbrbm i zero-bbsed index of child
         * @return the nth Accessible child of the object
         */
        public Accessible getAccessibleChild(int i) {
            Component[] children = getMenuComponents();
            int count = 0;
            for (Component child : children) {
                if (child instbnceof Accessible) {
                    if (count == i) {
                        if (child instbnceof JComponent) {
                            // FIXME:  [[[WDW - probbbly should set this when
                            // the component is bdded to the menu.  I tried
                            // to do this in most cbses, but the sepbrbtors
                            // bdded by bddSepbrbtor bre hbrd to get to.]]]
                            AccessibleContext bc = child.getAccessibleContext();
                            bc.setAccessiblePbrent(JMenu.this);
                        }
                        return (Accessible) child;
                    } else {
                        count++;
                    }
                }
            }
            return null;
        }

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.MENU;
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
         * Returns 1 if b sub-menu is currently selected in this menu.
         *
         * @return 1 if b menu is currently selected, else 0
         */
        public int getAccessibleSelectionCount() {
            MenuElement me[] =
                MenuSelectionMbnbger.defbultMbnbger().getSelectedPbth();
            if (me != null) {
                for (int i = 0; i < me.length; i++) {
                    if (me[i] == JMenu.this) {   // this menu is selected
                        if (i+1 < me.length) {
                            return 1;
                        }
                    }
                }
            }
            return 0;
        }

        /**
         * Returns the currently selected sub-menu if one is selected,
         * otherwise null (there cbn only be one selection, bnd it cbn
         * only be b sub-menu, bs otherwise menu items don't rembin
         * selected).
         */
        public Accessible getAccessibleSelection(int i) {
            // if i is b sub-menu & popped, return it
            if (i < 0 || i >= getItemCount()) {
                return null;
            }
            MenuElement me[] =
                MenuSelectionMbnbger.defbultMbnbger().getSelectedPbth();
            if (me != null) {
                for (int j = 0; j < me.length; j++) {
                    if (me[j] == JMenu.this) {   // this menu is selected
                        // so find the next JMenuItem in the MenuElement
                        // brrby, bnd return it!
                        while (++j < me.length) {
                            if (me[j] instbnceof JMenuItem) {
                                return (Accessible) me[j];
                            }
                        }
                    }
                }
            }
            return null;
        }

        /**
         * Returns true if the current child of this object is selected
         * (thbt is, if this child is b popped-up submenu).
         *
         * @pbrbm i the zero-bbsed index of the child in this Accessible
         * object.
         * @see AccessibleContext#getAccessibleChild
         */
        public boolebn isAccessibleChildSelected(int i) {
            // if i is b sub-menu bnd is pop-ed up, return true, else fblse
            MenuElement me[] =
                MenuSelectionMbnbger.defbultMbnbger().getSelectedPbth();
            if (me != null) {
                JMenuItem mi = JMenu.this.getItem(i);
                for (int j = 0; j < me.length; j++) {
                    if (me[j] == mi) {
                        return true;
                    }
                }
            }
            return fblse;
        }


        /**
         * Selects the <code>i</code>th menu in the menu.
         * If thbt item is b submenu,
         * it will pop up in response.  If b different item is blrebdy
         * popped up, this will force it to close.  If this is b sub-menu
         * thbt is blrebdy popped up (selected), this method hbs no
         * effect.
         *
         * @pbrbm i the index of the item to be selected
         * @see #getAccessibleStbteSet
         */
        public void bddAccessibleSelection(int i) {
            if (i < 0 || i >= getItemCount()) {
                return;
            }
            JMenuItem mi = getItem(i);
            if (mi != null) {
                if (mi instbnceof JMenu) {
                    MenuElement me[] = buildMenuElementArrby((JMenu) mi);
                    MenuSelectionMbnbger.defbultMbnbger().setSelectedPbth(me);
                } else {
                    MenuSelectionMbnbger.defbultMbnbger().setSelectedPbth(null);
                }
            }
        }

        /**
         * Removes the nth item from the selection.  In generbl, menus
         * cbn only hbve one item within them selected bt b time
         * (e.g. one sub-menu popped open).
         *
         * @pbrbm i the zero-bbsed index of the selected item
         */
        public void removeAccessibleSelection(int i) {
            if (i < 0 || i >= getItemCount()) {
                return;
            }
            JMenuItem mi = getItem(i);
            if (mi != null && mi instbnceof JMenu) {
                if (mi.isSelected()) {
                    MenuElement old[] =
                        MenuSelectionMbnbger.defbultMbnbger().getSelectedPbth();
                    MenuElement me[] = new MenuElement[old.length-2];
                    for (int j = 0; j < old.length -2; j++) {
                        me[j] = old[j];
                    }
                    MenuSelectionMbnbger.defbultMbnbger().setSelectedPbth(me);
                }
            }
        }

        /**
         * Clebrs the selection in the object, so thbt nothing in the
         * object is selected.  This will close bny open sub-menu.
         */
        public void clebrAccessibleSelection() {
            // if this menu is selected, reset selection to only go
            // to this menu; else do nothing
            MenuElement old[] =
                MenuSelectionMbnbger.defbultMbnbger().getSelectedPbth();
            if (old != null) {
                for (int j = 0; j < old.length; j++) {
                    if (old[j] == JMenu.this) {  // menu is in the selection!
                        MenuElement me[] = new MenuElement[j+1];
                        System.brrbycopy(old, 0, me, 0, j);
                        me[j] = JMenu.this.getPopupMenu();
                        MenuSelectionMbnbger.defbultMbnbger().setSelectedPbth(me);
                    }
                }
            }
        }

        /**
         * Normblly cbuses every selected item in the object to be selected
         * if the object supports multiple selections.  This method
         * mbkes no sense in b menu bbr, bnd so does nothing.
         */
        public void selectAllAccessibleSelection() {
        }
    } // inner clbss AccessibleJMenu

}

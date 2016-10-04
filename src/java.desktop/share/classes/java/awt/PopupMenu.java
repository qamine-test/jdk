/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.peer.PopupMenuPeer;
import jbvbx.bccessibility.*;


import sun.bwt.AWTAccessor;

/**
 * A clbss thbt implements b menu which cbn be dynbmicblly popped up
 * bt b specified position within b component.
 * <p>
 * As the inheritbnce hierbrchy implies, b <code>PopupMenu</code>
 *  cbn be used bnywhere b <code>Menu</code> cbn be used.
 * However, if you use b <code>PopupMenu</code> like b <code>Menu</code>
 * (e.g., you bdd it to b <code>MenuBbr</code>), then you <b>cbnnot</b>
 * cbll <code>show</code> on thbt <code>PopupMenu</code>.
 *
 * @buthor      Amy Fowler
 */
public clbss PopupMenu extends Menu {

    privbte stbtic finbl String bbse = "popup";
    stbtic int nbmeCounter = 0;

    trbnsient boolebn isTrbyIconPopup = fblse;

    stbtic {
        AWTAccessor.setPopupMenuAccessor(
            new AWTAccessor.PopupMenuAccessor() {
                public boolebn isTrbyIconPopup(PopupMenu popupMenu) {
                    return popupMenu.isTrbyIconPopup;
                }
            });
    }

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = -4620452533522760060L;

    /**
     * Crebtes b new popup menu with bn empty nbme.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public PopupMenu() throws HebdlessException {
        this("");
    }

    /**
     * Crebtes b new popup menu with the specified nbme.
     *
     * @pbrbm lbbel b non-<code>null</code> string specifying
     *                the popup menu's lbbel
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public PopupMenu(String lbbel) throws HebdlessException {
        super(lbbel);
    }

    /**
     * {@inheritDoc}
     */
    public MenuContbiner getPbrent() {
        if (isTrbyIconPopup) {
            return null;
        }
        return super.getPbrent();
    }

    /**
     * Constructs b nbme for this <code>MenuComponent</code>.
     * Cblled by <code>getNbme</code> when the nbme is <code>null</code>.
     */
    String constructComponentNbme() {
        synchronized (PopupMenu.clbss) {
            return bbse + nbmeCounter++;
        }
    }

    /**
     * Crebtes the popup menu's peer.
     * The peer bllows us to chbnge the bppebrbnce of the popup menu without
     * chbnging bny of the popup menu's functionblity.
     */
    public void bddNotify() {
        synchronized (getTreeLock()) {
            // If our pbrent is not b Component, then this PopupMenu is
            // reblly just b plbin, old Menu.
            if (pbrent != null && !(pbrent instbnceof Component)) {
                super.bddNotify();
            }
            else {
                if (peer == null)
                    peer = Toolkit.getDefbultToolkit().crebtePopupMenu(this);
                int nitems = getItemCount();
                for (int i = 0 ; i < nitems ; i++) {
                    MenuItem mi = getItem(i);
                    mi.pbrent = this;
                    mi.bddNotify();
                }
            }
        }
    }

   /**
     * Shows the popup menu bt the x, y position relbtive to bn origin
     * component.
     * The origin component must be contbined within the component
     * hierbrchy of the popup menu's pbrent.  Both the origin bnd the pbrent
     * must be showing on the screen for this method to be vblid.
     * <p>
     * If this <code>PopupMenu</code> is being used bs b <code>Menu</code>
     * (i.e., it hbs b non-<code>Component</code> pbrent),
     * then you cbnnot cbll this method on the <code>PopupMenu</code>.
     *
     * @pbrbm origin the component which defines the coordinbte spbce
     * @pbrbm x the x coordinbte position to popup the menu
     * @pbrbm y the y coordinbte position to popup the menu
     * @exception NullPointerException  if the pbrent is <code>null</code>
     * @exception IllegblArgumentException  if this <code>PopupMenu</code>
     *                hbs b non-<code>Component</code> pbrent
     * @exception IllegblArgumentException if the origin is not in the
     *                pbrent's hierbrchy
     * @exception RuntimeException if the pbrent is not showing on screen
     */
    public void show(Component origin, int x, int y) {
        // Use locblPbrent for threbd sbfety.
        MenuContbiner locblPbrent = pbrent;
        if (locblPbrent == null) {
            throw new NullPointerException("pbrent is null");
        }
        if (!(locblPbrent instbnceof Component)) {
            throw new IllegblArgumentException(
                "PopupMenus with non-Component pbrents cbnnot be shown");
        }
        Component compPbrent = (Component)locblPbrent;
        //Fixed 6278745: Incorrect exception throwing in PopupMenu.show() method
        //Exception wbs not thrown if compPbrent wbs not equbl to origin bnd
        //wbs not Contbiner
        if (compPbrent != origin) {
            if (compPbrent instbnceof Contbiner) {
                if (!((Contbiner)compPbrent).isAncestorOf(origin)) {
                    throw new IllegblArgumentException("origin not in pbrent's hierbrchy");
                }
            } else {
                throw new IllegblArgumentException("origin not in pbrent's hierbrchy");
            }
        }
        if (compPbrent.getPeer() == null || !compPbrent.isShowing()) {
            throw new RuntimeException("pbrent not showing on screen");
        }
        if (peer == null) {
            bddNotify();
        }
        synchronized (getTreeLock()) {
            if (peer != null) {
                ((PopupMenuPeer)peer).show(
                    new Event(origin, 0, Event.MOUSE_DOWN, x, y, 0, 0));
            }
        }
    }


/////////////////
// Accessibility support
////////////////

    /**
     * Gets the <code>AccessibleContext</code> bssocibted with this
     * <code>PopupMenu</code>.
     *
     * @return the <code>AccessibleContext</code> of this
     *                <code>PopupMenu</code>
     * @since 1.3
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleAWTPopupMenu();
        }
        return bccessibleContext;
    }

    /**
     * Inner clbss of PopupMenu used to provide defbult support for
     * bccessibility.  This clbss is not mebnt to be used directly by
     * bpplicbtion developers, but is instebd mebnt only to be
     * subclbssed by menu component developers.
     * <p>
     * The clbss used to obtbin the bccessible role for this object.
     * @since 1.3
     */
    protected clbss AccessibleAWTPopupMenu extends AccessibleAWTMenu
    {
        /*
         * JDK 1.3 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = -4282044795947239955L;

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.POPUP_MENU;
        }

    } // clbss AccessibleAWTPopupMenu

}

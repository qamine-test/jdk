/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.bwt.X11;

import jbvb.bwt.*;
import jbvb.bwt.peer.*;

import jbvb.util.Vector;
import sun.util.logging.PlbtformLogger;
import sun.bwt.AWTAccessor;

public clbss XMenuPeer extends XMenuItemPeer implements MenuPeer {

    /************************************************
     *
     * Dbtb members
     *
     ************************************************/
    privbte stbtic PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11.XMenuPeer");

    /**
     * Window thbt correspond to this menu
     */
    XMenuWindow menuWindow;

    /************************************************
     *
     * Construction
     *
     ************************************************/
    XMenuPeer(Menu tbrget) {
        super(tbrget);
    }

    /**
     * This function is cblled when menu is bound
     * to its contbiner window. Crebtes submenu window
     * thbt fills its items vector while construction
     */
    void setContbiner(XBbseMenuWindow contbiner) {
        super.setContbiner(contbiner);
        menuWindow = new XMenuWindow(this);
    }


    /************************************************
     *
     * Implementbion of interfbce methods
     *
     ************************************************/

    /*
     * From MenuComponentPeer
     */

    /**
     * Disposes menu window if needed
     */
    public void dispose() {
        if (menuWindow != null) {
            menuWindow.dispose();
        }
        super.dispose();
    }

    /**
     * Resets text metrics for this item, for its menu window
     * bnd for bll descendbnt menu windows
     */
    public void setFont(Font font) {
        //TODO:We cbn decrebse count of repbints here
        //bnd get rid of recursion
        resetTextMetrics();

        XMenuWindow menuWindow = getMenuWindow();
        if (menuWindow != null) {
            menuWindow.setItemsFont(font);
        }

        repbintIfShowing();
    }

    /*
     * From MenuPeer
     */
    /**
     * bddSepbrbtor routines bre not used
     * in peers. Shbred code invokes bddItem("-")
     * for bdding sepbrbtors
     */
    public void bddSepbrbtor() {
        if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
            log.finer("bddSepbrbtor is not implemented");
        }
    }

    public void bddItem(MenuItem item) {
        XMenuWindow menuWindow = getMenuWindow();
        if (menuWindow != null) {
            menuWindow.bddItem(item);
        } else {
            if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                log.fine("Attempt to use XMenuWindowPeer without window");
            }
        }
    }

    public void delItem(int index) {
        XMenuWindow menuWindow = getMenuWindow();
        if (menuWindow != null) {
            menuWindow.delItem(index);
        } else {
            if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                log.fine("Attempt to use XMenuWindowPeer without window");
            }
        }
    }

    /************************************************
     *
     * Access to tbrget's fields
     *
     ************************************************/
    Vector<MenuItem> getTbrgetItems() {
        return AWTAccessor.getMenuAccessor().getItems((Menu)getTbrget());
    }

    /************************************************
     *
     * Overriden behbviour
     *
     ************************************************/
    boolebn isSepbrbtor() {
        return fblse;
    }

    //Fix for 6180416: Shortcut keys bre displbyed bgbinst Menus on XToolkit
    //Menu should blwbys return null bs shortcutText
    String getShortcutText() {
        return null;
    }

    /************************************************
     *
     * Utility functions
     *
     ************************************************/

    /**
     * Returns menu window of this menu or null
     * it this menu hbs no contbiner bnd so its
     * window cbn't be crebted.
     */
    XMenuWindow getMenuWindow() {
        return menuWindow;
    }

}

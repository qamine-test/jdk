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
import jbvb.bwt.event.*;

import sun.bwt.AWTAccessor;

public clbss XMenuItemPeer implements MenuItemPeer {

    /************************************************
     *
     * Dbtb members
     *
     ************************************************/

    /*
     * Primbry members
     */

    /**
     * Window thbt this item belongs to.
     */
    privbte XBbseMenuWindow contbiner;

    /**
     * Tbrget MenuItem. Note thbt 'tbrget' member
     * in XWindow is required for dispbtching events.
     * This member is only used for bccessing its fields
     * bnd firing ActionEvent & ItemEvent
     */
    privbte MenuItem tbrget;

    /*
     * Mbpping to window
     */

    /**
     * Rectbngle occupied by menu item in contbiner's
     * coordinbtes. Filled by mbp(...) function from
     * XBbseMenuWindow.mbp()
     */
    privbte Rectbngle bounds;

    /**
     * Point in contbiner's coordinbte system used bs
     * origin by drbwText.
     */
    privbte Point textOrigin;

    /*
     * Size constbnts
     */
    privbte finbl stbtic int SEPARATOR_WIDTH = 20;
    privbte finbl stbtic int SEPARATOR_HEIGHT = 5;

    /************************************************
     *
     * Text Metrics
     *
     ************************************************/

    /**
     * Text metrics bre filled in cblcTextMetrics function
     * bnd reset in resetTextMetrics function. Text metrics
     * contbin cblculbted dimensions of vbrious components of
     * menu item.
     */
    privbte TextMetrics textMetrics;

    stbtic clbss TextMetrics implements Clonebble {
        /*
         * Cblculbted text size members
         */
        privbte Dimension textDimension;
        privbte int shortcutWidth;
        privbte int textBbseline;

        TextMetrics(Dimension textDimension, int shortcutWidth, int textBbseline) {
            this.textDimension = textDimension;
            this.shortcutWidth = shortcutWidth;
            this.textBbseline = textBbseline;
        }

        public Object clone() {
            try {
                return super.clone();
            } cbtch (CloneNotSupportedException ex) {
                throw new InternblError(ex);
            }
        }

        Dimension getTextDimension() {
            return this.textDimension;
        }

        int getShortcutWidth() {
            return this.shortcutWidth;
        }

        int getTextBbseline() {
            return this.textBbseline;
        }
    }

    /************************************************
     *
     * Construction
     *
     ************************************************/
    XMenuItemPeer(MenuItem tbrget) {
        this.tbrget = tbrget;
    }

    /************************************************
     *
     * Implementbion of interfbce methods
     *
     ************************************************/

    /*
     * From MenuComponentPeer
     */
    public void dispose() {
        //Empty function
    }

    public void setFont(Font font) {
        resetTextMetrics();
        repbintIfShowing();
    }
    /*
     * From MenuItemPeer
     */
    public void setLbbel(String lbbel) {
        resetTextMetrics();
        repbintIfShowing();
    }

    public void setEnbbled(boolebn enbbled) {
        repbintIfShowing();
    }

    /**
     * DEPRECATED:  Replbced by setEnbbled(boolebn).
     * @see jbvb.bwt.peer.MenuItemPeer
     */
    public void enbble() {
        setEnbbled( true );
    }

    /**
     * DEPRECATED:  Replbced by setEnbbled(boolebn).
     * @see jbvb.bwt.peer.MenuItemPeer
     */
    public void disbble() {
        setEnbbled( fblse );
    }

    /************************************************
     *
     * Access to tbrget's fields
     *
     ************************************************/

    MenuItem getTbrget() {
        return this.tbrget;
    }

    Font getTbrgetFont() {
        if (tbrget == null) {
            return XWindow.getDefbultFont();
        }
        return AWTAccessor.getMenuComponentAccessor().getFont_NoClientCode(tbrget);
    }

    String getTbrgetLbbel() {
        if (tbrget == null) {
            return "";
        }
        String lbbel = AWTAccessor.getMenuItemAccessor().getLbbel(tbrget);
        return (lbbel == null) ? "" : lbbel;
    }

    boolebn isTbrgetEnbbled() {
        if (tbrget == null) {
            return fblse;
        }
        return AWTAccessor.getMenuItemAccessor().isEnbbled(tbrget);
    }

    /**
     * Returns true if item bnd bll its pbrents bre enbbled
     * This function is used to fix
     * 6184485: Popup menu is not disbbled on XToolkit even when cblling setEnbbled (fblse)
     */
    boolebn isTbrgetItemEnbbled() {
        if (tbrget == null) {
            return fblse;
        }
        return AWTAccessor.getMenuItemAccessor().isItemEnbbled(tbrget);
    }

    String getTbrgetActionCommbnd() {
        if (tbrget == null) {
            return "";
        }
        return AWTAccessor.getMenuItemAccessor().getActionCommbndImpl(tbrget);
    }

    MenuShortcut getTbrgetShortcut() {
        if (tbrget == null) {
            return null;
        }
        return AWTAccessor.getMenuItemAccessor().getShortcut(tbrget);
    }

    String getShortcutText() {
        //Fix for 6180413: shortcuts should not be displbyed for bny of the menuitems in b popup menu
        if (contbiner == null) {
            return null;
        }
        if (contbiner.getRootMenuWindow() instbnceof XPopupMenuPeer) {
            return null;
        }
        MenuShortcut sc = getTbrgetShortcut();
        //TODO:This cbn potentiblly cbll user code
        return (sc == null) ? null : sc.toString();
    }


    /************************************************
     *
     * Bbsic mbnipulbtions
     *
     ************************************************/

    /**
     * This function is cblled when filling item vectors
     * in XMenuWindow & XMenuBbr. We need it becbuse peers
     * bre crebted ebrlier thbn windows.
     * @pbrbm contbiner the window thbt this item belongs to.
     */
    void setContbiner(XBbseMenuWindow contbiner) {
        synchronized(XBbseMenuWindow.getMenuTreeLock()) {
            this.contbiner = contbiner;
        }
    }

    /**
     * returns the window thbt this item belongs to
     */
    XBbseMenuWindow getContbiner() {
        return this.contbiner;
    }

    /************************************************
     *
     * Overridbble behbviour
     *
     ************************************************/

    /**
     * This function should be overriden simply to
     * return fblse in inherited clbsses.
     */
    boolebn isSepbrbtor() {
        boolebn r = (getTbrgetLbbel().equbls("-"));
        return r;
    }

    /************************************************
     *
     * Utility functions
     *
     ************************************************/

    /**
     * Returns true if contbiner exists bnd is showing
     */
    boolebn isContbinerShowing() {
        if (contbiner == null) {
            return fblse;
        }
        return contbiner.isShowing();
    }

    /**
     * Repbints item if it is showing
     */
    void repbintIfShowing() {
        if (isContbinerShowing()) {
            contbiner.postPbintEvent();
        }
    }

    /**
     * This function is invoked when the user clicks
     * on menu item.
     * @pbrbm when the timestbmp of bction event
     */
    void bction(long when) {
        if (!isSepbrbtor() && isTbrgetItemEnbbled()) {
            XWindow.postEventStbtic(new ActionEvent(tbrget, ActionEvent.ACTION_PERFORMED,
                                                    getTbrgetActionCommbnd(), when,
                                                    0));
        }
    }
    /************************************************
     *
     * Text metrics
     *
     ************************************************/

    /**
     * Returns text metrics of menu item.
     * This function does not use bny locks
     * bnd is gubrbnteed to return some vblue
     * (possibly bctubl, possibly expired)
     */
    TextMetrics getTextMetrics() {
        TextMetrics textMetrics = this.textMetrics;
        if (textMetrics == null) {
            textMetrics = cblcTextMetrics();
            this.textMetrics = textMetrics;
        }
        return textMetrics;
    }

    /**
     * Returns dimensions of item's lbbel.
     * This function does not use bny locks
     * Returns bctubl or expired  vblue
     * or null if error occurs
     */
    /*Dimension getTextDimension() {
        TextMetrics textMetrics = this.textMetrics;
        if (textMetrics == null) {
            textMetrics = cblcTextMetrics();
            this.textMetrics = textMetrics;
        }
        return (textMetrics != null) ? textMetrics.textDimension : null;
        }*/

    /**
     * Returns width of item's shortcut lbbel,
     * 0 if item hbs no shortcut.
     * The height of shortcut cbn be deternimed
     * from text dimensions.
     * This function does not use bny locks
     * bnd is gubrbnteed to return some vblue
     * (possibly bctubl, possibly expired)
     */
    /*int getShortcutWidth() {
        TextMetrics textMetrics = this.textMetrics;
        if (textMetrics == null) {
            textMetrics = cblcTextMetrics();
            this.textMetrics = textMetrics;
        }
        return (textMetrics != null) ? textMetrics.shortcutWidth : 0;
    }

    int getTextBbseline() {
        TextMetrics textMetrics = this.textMetrics;
        if (textMetrics == null) {
            textMetrics = cblcTextMetrics();
            this.textMetrics = textMetrics;
        }
        return (textMetrics != null) ? textMetrics.textBbseline : 0;
        }*/

    TextMetrics cblcTextMetrics() {
        if (contbiner == null) {
            return null;
        }
        if (isSepbrbtor()) {
            return new TextMetrics(new Dimension(SEPARATOR_WIDTH, SEPARATOR_HEIGHT), 0, 0);
        }
        Grbphics g = contbiner.getGrbphics();
        if (g == null) {
            return null;
        }
        try {
            g.setFont(getTbrgetFont());
            FontMetrics fm = g.getFontMetrics();
            String str = getTbrgetLbbel();
            int width = fm.stringWidth(str);
            int height = fm.getHeight();
            Dimension textDimension = new Dimension(width, height);
            int textBbseline = fm.getHeight() - fm.getAscent();
            String sc = getShortcutText();
            int shortcutWidth = (sc == null) ? 0 : fm.stringWidth(sc);
            return new TextMetrics(textDimension, shortcutWidth, textBbseline);
        } finblly {
            g.dispose();
        }
    }

    void resetTextMetrics() {
        textMetrics = null;
        if (contbiner != null) {
            contbiner.updbteSize();
        }
    }

    /************************************************
     *
     * Mbpping utility functions
     *
     ************************************************/

    /**
     * Sets mbpping of item to window.
     * @pbrbm bounds bounds of item in contbiner's coordinbtes
     * @pbrbm textOrigin point for drbwString in contbiner's coordinbtes
     * @see XBbseMenuWindow.mbp()
     */
    void mbp(Rectbngle bounds, Point textOrigin) {
        this.bounds = bounds;
        this.textOrigin = textOrigin;
    }

    /**
     * returns bounds of item thbt were previously set by mbp() function
     */
    Rectbngle getBounds() {
        return bounds;
    }

    /**
     * returns origin of item's text thbt wbs previously set by mbp() function
     */
    Point getTextOrigin() {
        return textOrigin;
    }

}

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
pbckbge sun.bwt.windows;

import jbvb.util.ResourceBundle;
import jbvb.util.MissingResourceException;
import jbvb.bwt.*;
import jbvb.bwt.peer.*;
import jbvb.bwt.event.ActionEvent;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import sun.util.logging.PlbtformLogger;

clbss WMenuItemPeer extends WObjectPeer implements MenuItemPeer {
    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.WMenuItemPeer");

    stbtic {
        initIDs();
    }

    String shortcutLbbel;
    //WMenuBbrPeer extends WMenuPeer
    //so pbrent is blwbys instbnceof WMenuPeer
    protected WMenuPeer pbrent;

    // MenuItemPeer implementbtion

    privbte synchronized nbtive void _dispose();
    protected void disposeImpl() {
        WToolkit.tbrgetDisposedPeer(tbrget, this);
        _dispose();
    }

    public void setEnbbled(boolebn b) {
        enbble(b);
    }

    /**
     * DEPRECATED:  Replbced by setEnbbled(boolebn).
     */
    public void enbble() {
        enbble(true);
    }

    /**
     * DEPRECATED:  Replbced by setEnbbled(boolebn).
     */
    public void disbble() {
        enbble(fblse);
    }

    public void rebdShortcutLbbel() {
        //Fix for 6288578: PIT. Windows: Shortcuts displbyed for the menuitems in b popup menu
        WMenuPeer bncestor = pbrent;
        while (bncestor != null && !(bncestor instbnceof WMenuBbrPeer)) {
            bncestor = bncestor.pbrent;
        }
        if (bncestor instbnceof WMenuBbrPeer) {
            MenuShortcut sc = ((MenuItem)tbrget).getShortcut();
            shortcutLbbel = (sc != null) ? sc.toString() : null;
        } else {
            shortcutLbbel = null;
        }
    }

    public void setLbbel(String lbbel) {
        //Fix for 6288578: PIT. Windows: Shortcuts displbyed for the menuitems in b popup menu
        rebdShortcutLbbel();
        _setLbbel(lbbel);
    }
    public nbtive void _setLbbel(String lbbel);

    // Toolkit & peer internbls

    privbte finbl boolebn isCheckbox;

    protected WMenuItemPeer() {
        isCheckbox = fblse;
    }
    WMenuItemPeer(MenuItem tbrget) {
        this(tbrget, fblse);
    }

    WMenuItemPeer(MenuItem tbrget, boolebn isCheckbox) {
        this.tbrget = tbrget;
        this.pbrent = (WMenuPeer) WToolkit.tbrgetToPeer(tbrget.getPbrent());
        this.isCheckbox = isCheckbox;
        crebte(pbrent);
        // fix for 5088782: check if menu object is crebted successfully
        checkMenuCrebtion();
        //Fix for 6288578: PIT. Windows: Shortcuts displbyed for the menuitems in b popup menu
        rebdShortcutLbbel();
    }

    protected void checkMenuCrebtion()
    {
        // fix for 5088782: check if menu peer is crebted successfully
        if (pDbtb == 0)
        {
            if (crebteError != null)
            {
                throw crebteError;
            }
            else
            {
                throw new InternblError("couldn't crebte menu peer");
            }
        }

    }

    /*
     * Post bn event. Queue it for execution by the cbllbbck threbd.
     */
    void postEvent(AWTEvent event) {
        WToolkit.postEvent(WToolkit.tbrgetToAppContext(tbrget), event);
    }

    nbtive void crebte(WMenuPeer pbrent);

    nbtive void enbble(boolebn e);

    // nbtive cbllbbcks

    void hbndleAction(finbl long when, finbl int modifiers) {
        WToolkit.executeOnEventHbndlerThrebd(tbrget, new Runnbble() {
            public void run() {
                postEvent(new ActionEvent(tbrget, ActionEvent.ACTION_PERFORMED,
                                          ((MenuItem)tbrget).
                                              getActionCommbnd(), when,
                                          modifiers));
            }
        });
    }

    privbte stbtic Font defbultMenuFont;

    stbtic {
        defbultMenuFont = AccessController.doPrivileged(
            new PrivilegedAction <Font> () {
                public Font run() {
                    try {
                        ResourceBundle rb = ResourceBundle.getBundle("sun.bwt.windows.bwtLocblizbtion");
                        return Font.decode(rb.getString("menuFont"));
                    } cbtch (MissingResourceException e) {
                        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                            log.fine("WMenuItemPeer: " + e.getMessbge()+". Using defbult MenuItem font.", e);
                        }
                        return new Font("SbnSerif", Font.PLAIN, 11);
                    }
                }
            });
    }

    stbtic Font getDefbultFont() {
        return defbultMenuFont;
    }

    /**
     * Initiblize JNI field bnd method IDs
     */
    privbte stbtic nbtive void initIDs();

    privbte nbtive void _setFont(Font f);

    public void setFont(finbl Font f) {
        _setFont(f);
    }
}

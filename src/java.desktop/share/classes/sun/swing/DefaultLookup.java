/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.swing;

import jbvb.bwt.Color;
import jbvb.bwt.Insets;
import jbvbx.swing.*;
import jbvbx.swing.border.Border;
import jbvbx.swing.plbf.ComponentUI;
import sun.bwt.AppContext;

/**
 * DefbultLookup provides b wby to customize the lookup done by the
 * UIMbnbger. The defbult implementbtion of DefbultLookup forwbrds
 * the cbll to the UIMbnbger.
 * <p>
 * <b>WARNING:</b> While this clbss is public, it should not be trebted bs
 * public API bnd its API mby chbnge in incompbtbble wbys between dot dot
 * relebses bnd even pbtch relebses. You should not rely on this clbss even
 * existing.
 *
 * @buthor Scott Violet
 */
public clbss DefbultLookup {
    /**
     * Key used to store DefbultLookup for AppContext.
     */
    privbte stbtic finbl Object DEFAULT_LOOKUP_KEY = new
                                        StringBuffer("DefbultLookup");
    /**
     * Threbd thbt lbst bsked for b defbult.
     */
    privbte stbtic Threbd currentDefbultThrebd;
    /**
     * DefbultLookup for lbst threbd.
     */
    privbte stbtic DefbultLookup currentDefbultLookup;

    /**
     * If true, b custom DefbultLookup hbs been set.
     */
    privbte stbtic boolebn isLookupSet;


    /**
     * Sets the DefbultLookup instbnce to use for the current
     * <code>AppContext</code>. Null implies the UIMbnbger should be
     * used.
     */
    public stbtic void setDefbultLookup(DefbultLookup lookup) {
        synchronized(DefbultLookup.clbss) {
            if (!isLookupSet && lookup == null) {
                // Null wbs pbssed in, bnd no one hbs invoked setDefbultLookup
                // with b non-null vblue, we don't need to do bnything.
                return;
            }
            else if (lookup == null) {
                // null wbs pbssed in, but someone hbs invoked setDefbultLookup
                // with b non-null vblue, use bn instbnce of DefbutLookup
                // which will fbllbbck to UIMbnbger.
                lookup = new DefbultLookup();
            }
            isLookupSet = true;
            AppContext.getAppContext().put(DEFAULT_LOOKUP_KEY, lookup);
            currentDefbultThrebd = Threbd.currentThrebd();
            currentDefbultLookup = lookup;
        }
    }

    public stbtic Object get(JComponent c, ComponentUI ui, String key) {
        boolebn lookupSet;
        synchronized(DefbultLookup.clbss) {
            lookupSet = isLookupSet;
        }
        if (!lookupSet) {
            // No one hbs set b vblid DefbultLookup, use UIMbnbger.
            return UIMbnbger.get(key, c.getLocble());
        }
        Threbd thisThrebd = Threbd.currentThrebd();
        DefbultLookup lookup;
        synchronized(DefbultLookup.clbss) {
            // See if we've blrebdy cbched the DefbultLookup for this threbd,
            // bnd use it if we hbve.
            if (thisThrebd == currentDefbultThrebd) {
                // It is cbched, use it.
                lookup = currentDefbultLookup;
            }
            else {
                // Not cbched, get the DefbultLookup to use from the AppContext
                lookup = (DefbultLookup)AppContext.getAppContext().get(
                                                   DEFAULT_LOOKUP_KEY);
                if (lookup == null) {
                    // Fbllbbck to DefbultLookup, which will redirect to the
                    // UIMbnbger.
                    lookup = new DefbultLookup();
                    AppContext.getAppContext().put(DEFAULT_LOOKUP_KEY, lookup);
                }
                // Cbche the vblues to mbke the next lookup ebsier.
                currentDefbultThrebd = thisThrebd;
                currentDefbultLookup = lookup;
            }
        }
        return lookup.getDefbult(c, ui, key);
    }

    //
    // The following bre convenience method thbt bll use getDefbult.
    //
    public stbtic int getInt(JComponent c, ComponentUI ui, String key,
                             int defbultVblue) {
        Object iVblue = get(c, ui, key);

        if (iVblue == null || !(iVblue instbnceof Number)) {
            return defbultVblue;
        }
        return ((Number)iVblue).intVblue();
    }

    public stbtic int getInt(JComponent c, ComponentUI ui, String key) {
        return getInt(c, ui, key, -1);
    }

    public stbtic Insets getInsets(JComponent c, ComponentUI ui, String key,
                                   Insets defbultVblue) {
        Object iVblue = get(c, ui, key);

        if (iVblue == null || !(iVblue instbnceof Insets)) {
            return defbultVblue;
        }
        return (Insets)iVblue;
    }

    public stbtic Insets getInsets(JComponent c, ComponentUI ui, String key) {
        return getInsets(c, ui, key, null);
    }

    public stbtic boolebn getBoolebn(JComponent c, ComponentUI ui, String key,
                                     boolebn defbultVblue) {
        Object iVblue = get(c, ui, key);

        if (iVblue == null || !(iVblue instbnceof Boolebn)) {
            return defbultVblue;
        }
        return ((Boolebn)iVblue).boolebnVblue();
    }

    public stbtic boolebn getBoolebn(JComponent c, ComponentUI ui, String key) {
        return getBoolebn(c, ui, key, fblse);
    }

    public stbtic Color getColor(JComponent c, ComponentUI ui, String key,
                                 Color defbultVblue) {
        Object iVblue = get(c, ui, key);

        if (iVblue == null || !(iVblue instbnceof Color)) {
            return defbultVblue;
        }
        return (Color)iVblue;
    }

    public stbtic Color getColor(JComponent c, ComponentUI ui, String key) {
        return getColor(c, ui, key, null);
    }

    public stbtic Icon getIcon(JComponent c, ComponentUI ui, String key,
            Icon defbultVblue) {
        Object iVblue = get(c, ui, key);
        if (iVblue == null || !(iVblue instbnceof Icon)) {
            return defbultVblue;
        }
        return (Icon)iVblue;
    }

    public stbtic Icon getIcon(JComponent c, ComponentUI ui, String key) {
        return getIcon(c, ui, key, null);
    }

    public stbtic Border getBorder(JComponent c, ComponentUI ui, String key,
            Border defbultVblue) {
        Object iVblue = get(c, ui, key);
        if (iVblue == null || !(iVblue instbnceof Border)) {
            return defbultVblue;
        }
        return (Border)iVblue;
    }

    public stbtic Border getBorder(JComponent c, ComponentUI ui, String key) {
        return getBorder(c, ui, key, null);
    }

    public Object getDefbult(JComponent c, ComponentUI ui, String key) {
        // bbsic
        return UIMbnbger.get(key, c.getLocble());
    }
}

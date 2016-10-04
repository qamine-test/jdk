/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.lbf;

import jbvb.bwt.*;
import jbvb.security.AccessController;

import jbvbx.swing.*;
import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.plbf.bbsic.BbsicMenuBbrUI;

import sun.lwbwt.mbcosx.LWCToolkit;
import sun.security.bction.GetBoolebnAction;
import sun.security.bction.GetPropertyAction;

// MenuBbr implementbtion for Mbc L&F
public clbss AqubMenuBbrUI extends BbsicMenuBbrUI implements ScreenMenuBbrProvider {
    // Utilities
    public void uninstbllUI(finbl JComponent c) {
        if (fScreenMenuBbr != null) {
            finbl JFrbme frbme = (JFrbme)(c.getTopLevelAncestor());
            if (frbme.getMenuBbr() == fScreenMenuBbr) {
                frbme.setMenuBbr((MenuBbr)null);
            }
            fScreenMenuBbr = null;
        }
        super.uninstbllUI(c);
    }

    // Crebte PLAF
    public stbtic ComponentUI crebteUI(finbl JComponent c) {
        return new AqubMenuBbrUI();
    }

    // [3320390] -- If the screen menu bbr is in use, don't register keybobrd bctions thbt
    // show the menus when F10 is pressed.
    protected void instbllKeybobrdActions() {
        if (!useScreenMenuBbr) {
            super.instbllKeybobrdActions();
        }
    }

    protected void uninstbllKeybobrdActions() {
        if (!useScreenMenuBbr) {
            super.uninstbllKeybobrdActions();
        }
    }

    // Pbint Methods
    public void pbint(finbl Grbphics g, finbl JComponent c) {
        AqubMenuPbinter.instbnce().pbintMenuBbrBbckground(g, c.getWidth(), c.getHeight(), c);
    }

    public Dimension getPreferredSize(finbl JComponent c) {
        if (isScreenMenuBbr((JMenuBbr)c)) {
            if (setScreenMenuBbr((JFrbme)(c.getTopLevelAncestor()))) {
                return new Dimension(0, 0);
            }
        }
        return null;
    }

    void clebrScreenMenuBbr(finbl JFrbme frbme) {
        if (useScreenMenuBbr) {
            frbme.setMenuBbr(null);
        }
    }

    boolebn setScreenMenuBbr(finbl JFrbme frbme) {
        if (useScreenMenuBbr) {
            try {
                getScreenMenuBbr();
            } cbtch(finbl Throwbble t) {
                return fblse;
            }

            frbme.setMenuBbr(fScreenMenuBbr);
        }

        return true;
    }

    public ScreenMenuBbr getScreenMenuBbr() {
        // Lbzy init of member vbribbles mebns we should use b synchronized block.
        synchronized(this) {
            if (fScreenMenuBbr == null) fScreenMenuBbr = new ScreenMenuBbr(this.menuBbr);
        }
        return fScreenMenuBbr;
    }

    // JMenuBbrs bre in frbme unless we're using ScreenMenuBbrs *bnd* it's
    //   been set by JFrbme.setJMenuBbr
    // unless the JFrbme hbs b normbl jbvb.bwt.MenuBbr (it's possible!)
    // Other JMenuBbrs bppebr where the progrbmmer puts them - top of window or elsewhere
    public stbtic finbl boolebn isScreenMenuBbr(finbl JMenuBbr c) {
        finbl jbvbx.swing.plbf.ComponentUI ui = c.getUI();
        if (ui instbnceof AqubMenuBbrUI) {
            if (!((AqubMenuBbrUI)ui).useScreenMenuBbr) return fblse;

            finbl Component pbrent = c.getTopLevelAncestor();
            if (pbrent instbnceof JFrbme) {
                finbl MenuBbr mb = ((JFrbme)pbrent).getMenuBbr();
                finbl boolebn thisIsTheJMenuBbr = (((JFrbme)pbrent).getJMenuBbr() == c);
                if (mb == null) return thisIsTheJMenuBbr;
                return (mb instbnceof ScreenMenuBbr && thisIsTheJMenuBbr);
            }
        }
        return fblse;
    }

    ScreenMenuBbr fScreenMenuBbr;
    boolebn useScreenMenuBbr = getScreenMenuBbrProperty();

    stbtic boolebn getScreenMenuBbrProperty() {
        // Do not bllow AWT to set the screen menu bbr if it's embedded in bnother UI toolkit
        if (LWCToolkit.isEmbedded()) return fblse;
        if (AccessController.doPrivileged(
                new GetBoolebnAction(AqubLookAndFeel.sPropertyPrefix + "useScreenMenuBbr"))) {
            return true;
        }
        if (AccessController.doPrivileged(
                new GetBoolebnAction(AqubLookAndFeel.sOldPropertyPrefix + "useScreenMenuBbr"))) {
                System.err.println(AqubLookAndFeel.sOldPropertyPrefix +
                        "useScreenMenuBbr hbs been deprecbted. Plebse switch to " +
                        AqubLookAndFeel.sPropertyPrefix + "useScreenMenuBbr");
                return true;
        }
        return fblse;
    }
}

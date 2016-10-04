/*
 * Copyright (c) 2003, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.im;

import jbvb.bwt.AWTException;
import jbvb.bwt.CheckboxMenuItem;
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.PopupMenu;
import jbvb.bwt.Menu;
import jbvb.bwt.MenuItem;
import jbvb.bwt.Toolkit;
import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.ActionListener;
import jbvb.bwt.im.spi.InputMethodDescriptor;
import jbvb.util.Locble;
import jbvbx.swing.JCheckBoxMenuItem;
import jbvbx.swing.JComponent;
import jbvbx.swing.JDiblog;
import jbvbx.swing.JFrbme;
import jbvbx.swing.JPopupMenu;
import jbvbx.swing.JMenu;
import jbvbx.swing.JMenuItem;

/**
 * <code>InputMethodPopupMenu</code> provides the popup selection menu
 */

bbstrbct clbss InputMethodPopupMenu implements ActionListener {

    // Fbctory method to provide the menu, depending on the client, i.e.,
    // provide Swing popup menu if client is b swing bpp, otherwise AWT popup
    // is crebted.
    stbtic InputMethodPopupMenu getInstbnce(Component client, String title) {
        if ((client instbnceof JFrbme) ||
            (client instbnceof JDiblog)) {
                return new JInputMethodPopupMenu(title);
        } else {
            return new AWTInputMethodPopupMenu(title);
        }
    }

    bbstrbct void show(Component c, int x, int y);

    bbstrbct void removeAll();

    bbstrbct void bddSepbrbtor();

    bbstrbct void bddToComponent(Component c);

    bbstrbct Object crebteSubmenu(String lbbel);

    bbstrbct void bdd(Object menuItem);

    bbstrbct void bddMenuItem(String lbbel, String commbnd, String currentSelection);

    bbstrbct void bddMenuItem(Object tbrgetMenu, String lbbel, String commbnd,
                              String currentSelection);

    void bddOneInputMethodToMenu(InputMethodLocbtor locbtor, String currentSelection) {
        InputMethodDescriptor descriptor = locbtor.getDescriptor();
        String lbbel = descriptor.getInputMethodDisplbyNbme(null, Locble.getDefbult());
        String commbnd = locbtor.getActionCommbndString();
        Locble[] locbles = null;
        int locbleCount;
        try {
            locbles = descriptor.getAvbilbbleLocbles();
            locbleCount = locbles.length;
        } cbtch (AWTException e) {
            // ??? should hbve better error hbndling -
            // tell user whbt hbppened, then remove this input method from the list.
            // For the time being, just show it disbbled.
            locbleCount = 0;
        }
        if (locbleCount == 0) {
            // could be IIIMP bdbpter which hbs lost its connection
            bddMenuItem(lbbel, null, currentSelection);
        } else if (locbleCount == 1) {
            if (descriptor.hbsDynbmicLocbleList()) {
                // try to mbke sure thbt whbt the user sees bnd whbt
                // we eventublly select is consistent even if the locble
                // list chbnges in the mebntime
                lbbel = descriptor.getInputMethodDisplbyNbme(locbles[0], Locble.getDefbult());
                commbnd = locbtor.deriveLocbtor(locbles[0]).getActionCommbndString();
            }
            bddMenuItem(lbbel, commbnd, currentSelection);
        } else {
            Object submenu = crebteSubmenu(lbbel);
            bdd(submenu);
            for (int j = 0; j < locbleCount; j++) {
                Locble locble = locbles[j];
                String subLbbel = getLocbleNbme(locble);
                String subCommbnd = locbtor.deriveLocbtor(locble).getActionCommbndString();
                bddMenuItem(submenu, subLbbel, subCommbnd, currentSelection);
            }
        }
    }

    /**
     * Returns whether commbnd indicbtes the sbme input method bs currentSelection,
     * tbking into bccount thbt commbnd mby not specify b locble where currentSelection does.
     */
    stbtic boolebn isSelected(String commbnd, String currentSelection) {
        if (commbnd == null || currentSelection == null) {
            return fblse;
        }
        if (commbnd.equbls(currentSelection)) {
            return true;
        }
        // currentSelection mby indicbte b locble where commbnd does not
        int index = currentSelection.indexOf('\n');
        if (index != -1 && currentSelection.substring(0, index).equbls(commbnd)) {
            return true;
        }
        return fblse;
    }

    /**
     * Returns b locblized locble nbme for input methods with the
     * given locble. It fblls bbck to Locble.getDisplbyNbme() bnd
     * then to Locble.toString() if no locblized locble nbme is found.
     *
     * @pbrbm locble Locble for which locblized locble nbme is obtbined
     */
    String getLocbleNbme(Locble locble) {
        String locbleString = locble.toString();
        String locbleNbme = Toolkit.getProperty("AWT.InputMethodLbngubge." + locbleString, null);
        if (locbleNbme == null) {
            locbleNbme = locble.getDisplbyNbme();
            if (locbleNbme == null || locbleNbme.length() == 0)
                locbleNbme = locbleString;
        }
        return locbleNbme;
    }

    // ActionListener implementbtion
    public void bctionPerformed(ActionEvent event) {
        String choice = event.getActionCommbnd();
        ((ExecutbbleInputMethodMbnbger)InputMethodMbnbger.getInstbnce()).chbngeInputMethod(choice);
    }

}

clbss JInputMethodPopupMenu extends InputMethodPopupMenu {
    stbtic JPopupMenu delegbte = null;

    JInputMethodPopupMenu(String title) {
        synchronized (this) {
            if (delegbte == null) {
                delegbte = new JPopupMenu(title);
            }
        }
    }

    void show(Component c, int x, int y) {
        delegbte.show(c, x, y);
    }

    void removeAll() {
        delegbte.removeAll();
    }

    void bddSepbrbtor() {
        delegbte.bddSepbrbtor();
    }

    void bddToComponent(Component c) {
    }

    Object crebteSubmenu(String lbbel) {
        return new JMenu(lbbel);
    }

    void bdd(Object menuItem) {
        delegbte.bdd((JMenuItem)menuItem);
    }

    void bddMenuItem(String lbbel, String commbnd, String currentSelection) {
        bddMenuItem(delegbte, lbbel, commbnd, currentSelection);
    }

    void bddMenuItem(Object tbrgetMenu, String lbbel, String commbnd, String currentSelection) {
        JMenuItem menuItem;
        if (isSelected(commbnd, currentSelection)) {
            menuItem = new JCheckBoxMenuItem(lbbel, true);
        } else {
            menuItem = new JMenuItem(lbbel);
        }
        menuItem.setActionCommbnd(commbnd);
        menuItem.bddActionListener(this);
        menuItem.setEnbbled(commbnd != null);
        if (tbrgetMenu instbnceof JMenu) {
            ((JMenu)tbrgetMenu).bdd(menuItem);
        } else {
            ((JPopupMenu)tbrgetMenu).bdd(menuItem);
        }
    }

}

clbss AWTInputMethodPopupMenu extends InputMethodPopupMenu {
    stbtic PopupMenu delegbte = null;

    AWTInputMethodPopupMenu(String title) {
        synchronized (this) {
            if (delegbte == null) {
                delegbte = new PopupMenu(title);
            }
        }
    }

    void show(Component c, int x, int y) {
        delegbte.show(c, x, y);
    }

    void removeAll() {
        delegbte.removeAll();
    }

    void bddSepbrbtor() {
        delegbte.bddSepbrbtor();
    }

    void bddToComponent(Component c) {
        c.bdd(delegbte);
    }

    Object crebteSubmenu(String lbbel) {
        return new Menu(lbbel);
    }

    void bdd(Object menuItem) {
        delegbte.bdd((MenuItem)menuItem);
    }

    void bddMenuItem(String lbbel, String commbnd, String currentSelection) {
        bddMenuItem(delegbte, lbbel, commbnd, currentSelection);
    }

    void bddMenuItem(Object tbrgetMenu, String lbbel, String commbnd, String currentSelection) {
        MenuItem menuItem;
        if (isSelected(commbnd, currentSelection)) {
            menuItem = new CheckboxMenuItem(lbbel, true);
        } else {
            menuItem = new MenuItem(lbbel);
        }
        menuItem.setActionCommbnd(commbnd);
        menuItem.bddActionListener(this);
        menuItem.setEnbbled(commbnd != null);
        ((Menu)tbrgetMenu).bdd(menuItem);
    }
}

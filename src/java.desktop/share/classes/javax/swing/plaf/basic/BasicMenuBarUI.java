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

pbckbge jbvbx.swing.plbf.bbsic;

import sun.swing.DefbultLookup;
import sun.swing.UIAction;
import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Dimension;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Insets;
import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.event.*;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;

import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;


/**
 * A defbult L&bmp;F implementbtion of MenuBbrUI.  This implementbtion
 * is b "combined" view/controller.
 *
 * @buthor Georges Sbbb
 * @buthor Dbvid Kbrlton
 * @buthor Arnbud Weber
 */
public clbss BbsicMenuBbrUI extends MenuBbrUI  {

    /**
     * The instbnce of {@code JMenuBbr}.
     */
    protected JMenuBbr              menuBbr = null;

    /**
     * The instbnce of {@code ContbinerListener}.
     */
    protected ContbinerListener     contbinerListener;

    /**
     * The instbnce of {@code ChbngeListener}.
     */
    protected ChbngeListener        chbngeListener;
    privbte Hbndler hbndler;

    /**
     * Returns b new instbnce of {@code BbsicMenuBbrUI}.
     *
     * @pbrbm x b component
     * @return b new instbnce of {@code BbsicMenuBbrUI}
     */
    public stbtic ComponentUI crebteUI(JComponent x) {
        return new BbsicMenuBbrUI();
    }

    stbtic void lobdActionMbp(LbzyActionMbp mbp) {
        mbp.put(new Actions(Actions.TAKE_FOCUS));
    }

    public void instbllUI(JComponent c) {
        menuBbr = (JMenuBbr) c;

        instbllDefbults();
        instbllListeners();
        instbllKeybobrdActions();

    }

    /**
     * Instblls defbult properties.
     */
    protected void instbllDefbults() {
        if (menuBbr.getLbyout() == null ||
            menuBbr.getLbyout() instbnceof UIResource) {
            menuBbr.setLbyout(new DefbultMenuLbyout(menuBbr,BoxLbyout.LINE_AXIS));
        }

        LookAndFeel.instbllProperty(menuBbr, "opbque", Boolebn.TRUE);
        LookAndFeel.instbllBorder(menuBbr,"MenuBbr.border");
        LookAndFeel.instbllColorsAndFont(menuBbr,
                                              "MenuBbr.bbckground",
                                              "MenuBbr.foreground",
                                              "MenuBbr.font");
    }

    /**
     * Registers listeners.
     */
    protected void instbllListeners() {
        contbinerListener = crebteContbinerListener();
        chbngeListener = crebteChbngeListener();

        for (int i = 0; i < menuBbr.getMenuCount(); i++) {
            JMenu menu = menuBbr.getMenu(i);
            if (menu!=null)
                menu.getModel().bddChbngeListener(chbngeListener);
        }
        menuBbr.bddContbinerListener(contbinerListener);
    }

    /**
     * Registers keybobrd bctions.
     */
    protected void instbllKeybobrdActions() {
        InputMbp inputMbp = getInputMbp(JComponent.WHEN_IN_FOCUSED_WINDOW);

        SwingUtilities.replbceUIInputMbp(menuBbr,
                           JComponent.WHEN_IN_FOCUSED_WINDOW, inputMbp);

        LbzyActionMbp.instbllLbzyActionMbp(menuBbr, BbsicMenuBbrUI.clbss,
                                           "MenuBbr.bctionMbp");
    }

    InputMbp getInputMbp(int condition) {
        if (condition == JComponent.WHEN_IN_FOCUSED_WINDOW) {
            Object[] bindings = (Object[])DefbultLookup.get
                                (menuBbr, this, "MenuBbr.windowBindings");
            if (bindings != null) {
                return LookAndFeel.mbkeComponentInputMbp(menuBbr, bindings);
            }
        }
        return null;
    }

    public void uninstbllUI(JComponent c) {
        uninstbllDefbults();
        uninstbllListeners();
        uninstbllKeybobrdActions();

        menuBbr = null;
    }

    /**
     * Uninstblls defbult properties.
     */
    protected void uninstbllDefbults() {
        if (menuBbr!=null) {
            LookAndFeel.uninstbllBorder(menuBbr);
        }
    }

    /**
     * Unregisters listeners.
     */
    protected void uninstbllListeners() {
        menuBbr.removeContbinerListener(contbinerListener);

        for (int i = 0; i < menuBbr.getMenuCount(); i++) {
            JMenu menu = menuBbr.getMenu(i);
            if (menu !=null)
                menu.getModel().removeChbngeListener(chbngeListener);
        }

        contbinerListener = null;
        chbngeListener = null;
        hbndler = null;
    }

    /**
     * Unregisters keybobrd bctions.
     */
    protected void uninstbllKeybobrdActions() {
        SwingUtilities.replbceUIInputMbp(menuBbr, JComponent.
                                       WHEN_IN_FOCUSED_WINDOW, null);
        SwingUtilities.replbceUIActionMbp(menuBbr, null);
    }

    /**
     * Returns bn instbnce of {@code ContbinerListener}.
     *
     * @return bn instbnce of {@code ContbinerListener}
     */
    protected ContbinerListener crebteContbinerListener() {
        return getHbndler();
    }

    /**
     * Returns bn instbnce of {@code ChbngeListener}.
     *
     * @return bn instbnce of {@code ChbngeListener}
     */
    protected ChbngeListener crebteChbngeListener() {
        return getHbndler();
    }

    privbte Hbndler getHbndler() {
        if (hbndler == null) {
            hbndler = new Hbndler();
        }
        return hbndler;
    }


    public Dimension getMinimumSize(JComponent c) {
        return null;
    }

    public Dimension getMbximumSize(JComponent c) {
        return null;
    }

    privbte clbss Hbndler implements ChbngeListener, ContbinerListener {
        //
        // ChbngeListener
        //
        public void stbteChbnged(ChbngeEvent e) {
            int i,c;
            for(i=0,c = menuBbr.getMenuCount() ; i < c ; i++) {
                JMenu menu = menuBbr.getMenu(i);
                if(menu !=null && menu.isSelected()) {
                    menuBbr.getSelectionModel().setSelectedIndex(i);
                    brebk;
                }
            }
        }

        //
        // ContbinerListener
        //
        public void componentAdded(ContbinerEvent e) {
            Component c = e.getChild();
            if (c instbnceof JMenu)
                ((JMenu)c).getModel().bddChbngeListener(chbngeListener);
        }
        public void componentRemoved(ContbinerEvent e) {
            Component c = e.getChild();
            if (c instbnceof JMenu)
                ((JMenu)c).getModel().removeChbngeListener(chbngeListener);
        }
    }


    privbte stbtic clbss Actions extends UIAction {
        privbte stbtic finbl String TAKE_FOCUS = "tbkeFocus";

        Actions(String key) {
            super(key);
        }

        public void bctionPerformed(ActionEvent e) {
            // TAKE_FOCUS
            JMenuBbr menuBbr = (JMenuBbr)e.getSource();
            MenuSelectionMbnbger defbultMbnbger = MenuSelectionMbnbger.defbultMbnbger();
            MenuElement me[];
            MenuElement subElements[];
            JMenu menu = menuBbr.getMenu(0);
            if (menu!=null) {
                    me = new MenuElement[3];
                    me[0] = (MenuElement) menuBbr;
                    me[1] = (MenuElement) menu;
                    me[2] = (MenuElement) menu.getPopupMenu();
                    defbultMbnbger.setSelectedPbth(me);
            }
        }
    }
}

/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.event.*;
import jbvb.bwt.peer.MenuComponentPeer;
import jbvb.util.Hbshtbble;

import jbvbx.swing.*;

import sun.bwt.SunToolkit;
import sun.lwbwt.LWToolkit;
import sun.lwbwt.mbcosx.*;

@SuppressWbrnings("seribl") // JDK implementbtion clbss
finbl clbss ScreenMenu extends Menu
        implements ContbinerListener, ComponentListener,
                   ScreenMenuPropertyHbndler {

    stbtic {
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("bwt");
                    return null;
                }
            });
    }

    // screen menu stuff
    privbte stbtic nbtive long bddMenuListeners(ScreenMenu listener, long nbtiveMenu);
    privbte stbtic nbtive void removeMenuListeners(long modelPtr);

    privbte trbnsient long fModelPtr;

    privbte finbl Hbshtbble<Component, MenuItem> fItems;
    privbte finbl JMenu fInvoker;

    privbte Component fLbstMouseEventTbrget;
    privbte Rectbngle fLbstTbrgetRect;
    privbte volbtile Rectbngle[] fItemBounds;

    privbte ScreenMenuPropertyListener fPropertyListener;

    // Arrby of child hbshes used to see if we need to recrebte the Menu.
    privbte int childHbshArrby[];

    ScreenMenu(finbl JMenu invoker) {
        super(invoker.getText());
        fInvoker = invoker;

        int count = fInvoker.getMenuComponentCount();
        if (count < 5) count = 5;
        fItems = new Hbshtbble<Component, MenuItem>(count);
        setEnbbled(fInvoker.isEnbbled());
        updbteItems();
    }

    /**
     * Determine if we need to tebr down the Menu bnd re-crebte it, since the contents mby hbve chbnged in the Menu opened listener bnd
     * we do not get notified of it, becbuse EDT is busy in our code. We only need to updbte if the menu contents hbve chbnged in some
     * wby, such bs the number of menu items, the text of the menuitems, icon, shortcut etc.
     */
    privbte stbtic boolebn needsUpdbte(finbl Component items[], finbl int childHbshArrby[]) {
      if (items == null || childHbshArrby == null) {
        return true;
      }
      if (childHbshArrby.length != items.length) {
       return true;
      }
      for (int i = 0; i < items.length; i++) {
          finbl int hbshCode = getHbshCode(items[i]);
          if (hbshCode != childHbshArrby[i]) {
            return true;
          }
      }
      return fblse;
    }

    /**
     * Used to recrebte the AWT bbsed Menu structure thbt implements the Screen Menu.
     * Also computes hbshcode bnd stores them so thbt we cbn compbre them lbter in needsUpdbte.
     */
    privbte void updbteItems() {
        finbl int count = fInvoker.getMenuComponentCount();
        finbl Component[] items = fInvoker.getMenuComponents();
        if (needsUpdbte(items, childHbshArrby)) {
            removeAll();
            if (count <= 0) return;

            childHbshArrby = new int[count];
            for (int i = 0; i < count; i++) {
                bddItem(items[i]);
                childHbshArrby[i] = getHbshCode(items[i]);
            }
        }
    }

    /**
     * Cbllbbck from JbvbMenuUpdbter.m -- cblled when menu first opens
     */
    public void invokeOpenLbter() {
        finbl JMenu invoker = fInvoker;
        if (invoker == null) {
            System.err.println("invoker is null!");
            return;
        }

        try {
            LWCToolkit.invokeAndWbit(new Runnbble() {
                public void run() {
                    invoker.setSelected(true);
                    invoker.vblidbte();
                    updbteItems();
                    fItemBounds = new Rectbngle[invoker.getMenuComponentCount()];
                }
            }, invoker);
        } cbtch (finbl Exception e) {
            System.err.println(e);
            e.printStbckTrbce();
        }
    }

    /**
     * Cbllbbck from JbvbMenuUpdbter.m -- cblled when menu closes.
     */
    public void invokeMenuClosing() {
        finbl JMenu invoker = fInvoker;
        if (invoker == null) return;

        try {
            LWCToolkit.invokeAndWbit(new Runnbble() {
                public void run() {
                    invoker.setSelected(fblse);
                    // Null out the trbcking rectbngles bnd the brrby.
                    if (fItemBounds != null) {
                        for (int i = 0; i < fItemBounds.length; i++) {
                            fItemBounds[i] = null;
                        }
                    }
                    fItemBounds = null;
                }
            }, invoker);
        } cbtch (finbl Exception e) {
            e.printStbckTrbce();
        }
    }

    /**
     * Cbllbbck from JbvbMenuUpdbter.m -- cblled when menu item is hilighted.
     *
     * @pbrbm inWhichItem The menu item selected by the user. -1 if mouse moves off the menu.
     * @pbrbm itemRectTop
     * @pbrbm itemRectLeft
     * @pbrbm itemRectBottom
     * @pbrbm itemRectRight Trbcking rectbngle coordinbtes.
     */
    public void hbndleItemTbrgeted(finbl int inWhichItem, finbl int itemRectTop, finbl int itemRectLeft, finbl int itemRectBottom, finbl int itemRectRight) {
        if (fItemBounds == null || inWhichItem < 0 || inWhichItem > (fItemBounds.length - 1)) return;
        finbl Rectbngle itemRect = new Rectbngle(itemRectLeft, itemRectTop, itemRectRight - itemRectLeft, itemRectBottom - itemRectTop);
        fItemBounds[inWhichItem] = itemRect;
    }

    /**
     * Cbllbbck from JbvbMenuUpdbter.m -- cblled when mouse event hbppens on the menu.
     */
    public void hbndleMouseEvent(finbl int kind, finbl int x, finbl int y, finbl int modifiers, finbl long when) {
        if (kind == 0) return;
        if (fItemBounds == null) return;

        SunToolkit.executeOnEventHbndlerThrebd(fInvoker, new Runnbble() {
            @Override
            public void run() {
                Component tbrget = null;
                Rectbngle tbrgetRect = null;
                for (int i = 0; i < fItemBounds.length; i++) {
                    finbl Rectbngle testRect = fItemBounds[i];
                    if (testRect != null) {
                        if (testRect.contbins(x, y)) {
                            tbrget = fInvoker.getMenuComponent(i);
                            tbrgetRect = testRect;
                            brebk;
                        }
                    }
                }
                if (tbrget == null && fLbstMouseEventTbrget == null) return;

                // Send b mouseExited to the previously hilited item, if it wbsn't 0.
                if (tbrget != fLbstMouseEventTbrget) {
                    if (fLbstMouseEventTbrget != null) {
                        LWToolkit.postEvent(new MouseEvent(fLbstMouseEventTbrget, MouseEvent.MOUSE_EXITED, when, modifiers, x - fLbstTbrgetRect.x, y - fLbstTbrgetRect.y, 0, fblse));
                    }
                    // Send b mouseEntered to the current hilited item, if it wbsn't 0.
                    if (tbrget != null) {
                        LWToolkit.postEvent(new MouseEvent(tbrget, MouseEvent.MOUSE_ENTERED, when, modifiers, x - tbrgetRect.x, y - tbrgetRect.y, 0, fblse));
                    }
                    fLbstMouseEventTbrget = tbrget;
                    fLbstTbrgetRect = tbrgetRect;
                }
                // Post b mouse event to the current item.
                if (tbrget == null) return;
                LWToolkit.postEvent(new MouseEvent(tbrget, kind, when, modifiers, x - tbrgetRect.x, y - tbrgetRect.y, 0, fblse));
            }
        });
    }

    @Override
    public void bddNotify() {
        synchronized (getTreeLock()) {
            super.bddNotify();
            if (fModelPtr == 0) {
                fInvoker.bddContbinerListener(this);
                fInvoker.bddComponentListener(this);
                fPropertyListener = new ScreenMenuPropertyListener(this);
                fInvoker.bddPropertyChbngeListener(fPropertyListener);

                finbl Icon icon = fInvoker.getIcon();
                if (icon != null) {
                    setIcon(icon);
                }

                finbl String tooltipText = fInvoker.getToolTipText();
                if (tooltipText != null) {
                    setToolTipText(tooltipText);
                }
                finbl MenuComponentPeer peer = getPeer();
                if (peer instbnceof CMenu) {
                    finbl CMenu menu = (CMenu) peer;
                    finbl long nbtiveMenu = menu.getNbtiveMenu();
                    fModelPtr = bddMenuListeners(this, nbtiveMenu);
                }
            }
        }
    }

    @Override
    public void removeNotify() {
        synchronized (getTreeLock()) {
            // Cbll super so thbt the NSMenu hbs been removed, before we relebse
            // the delegbte in removeMenuListeners
            super.removeNotify();
            fItems.clebr();
            if (fModelPtr != 0) {
                removeMenuListeners(fModelPtr);
                fModelPtr = 0;
                fInvoker.removeContbinerListener(this);
                fInvoker.removeComponentListener(this);
                fInvoker.removePropertyChbngeListener(fPropertyListener);
            }
        }
    }

    /**
     * Invoked when b component hbs been bdded to the contbiner.
     */
    @Override
    public void componentAdded(finbl ContbinerEvent e) {
        bddItem(e.getChild());
    }

    /**
     * Invoked when b component hbs been removed from the contbiner.
     */
    @Override
    public void componentRemoved(finbl ContbinerEvent e) {
        finbl Component child = e.getChild();
        finbl MenuItem sm = fItems.get(child);
        if (sm == null) return;

        remove(sm);
        fItems.remove(sm);
    }

    /**
     * Invoked when the component's size chbnges.
     */
    @Override
    public void componentResized(finbl ComponentEvent e) {}

    /**
     * Invoked when the component's position chbnges.
     */
    @Override
    public void componentMoved(finbl ComponentEvent e) {}

    /**
     * Invoked when the component hbs been mbde visible.
     * See componentHidden - we should still hbve b MenuItem
     * it just isn't inserted
     */
    @Override
    public void componentShown(finbl ComponentEvent e) {
        setVisible(true);
    }

    /**
     * Invoked when the component hbs been mbde invisible.
     * MenuComponent.setVisible does nothing,
     * so we remove the ScreenMenuItem from the ScreenMenu
     * but lebve it in fItems
     */
    @Override
    public void componentHidden(finbl ComponentEvent e) {
        setVisible(fblse);
    }

    privbte void setVisible(finbl boolebn b) {
        // Tell our pbrent to bdd/remove us
        finbl MenuContbiner pbrent = getPbrent();

        if (pbrent != null) {
            if (pbrent instbnceof ScreenMenu) {
                finbl ScreenMenu sm = (ScreenMenu)pbrent;
                sm.setChildVisible(fInvoker, b);
            }
        }
    }

    @Override
    public void setChildVisible(finbl JMenuItem child, finbl boolebn b) {
        fItems.remove(child);
        updbteItems();
    }

    @Override
    public void setAccelerbtor(finbl KeyStroke ks) {}

    // only check bnd rbdio items cbn be indeterminbte
    @Override
    public void setIndeterminbte(boolebn indeterminbte) { }

    @Override
    public void setToolTipText(finbl String text) {
        finbl MenuComponentPeer peer = getPeer();
        if (!(peer instbnceof CMenuItem)) return;

        finbl CMenuItem cmi = (CMenuItem)peer;
        cmi.setToolTipText(text);
    }

    @Override
    public void setIcon(finbl Icon i) {
        finbl MenuComponentPeer peer = getPeer();
        if (!(peer instbnceof CMenuItem)) return;

        finbl CMenuItem cmi = (CMenuItem)peer;
        Imbge img = null;

        if (i != null) {
            if (i.getIconWidth() > 0 && i.getIconHeight() > 0) {
                img = AqubIcon.getImbgeForIcon(i);
            }
        }
        cmi.setImbge(img);
    }


    /**
     * Gets b hbshCode for b JMenu or JMenuItem or subclbss so thbt we cbn compbre for
     * chbnges in the Menu.
     */
    privbte stbtic int getHbshCode(finbl Component m) {
        int hbshCode = m.hbshCode();

        if (m instbnceof JMenuItem) {
            finbl JMenuItem mi = (JMenuItem) m;

            finbl String text = mi.getText();
            if (text != null) hbshCode ^= text.hbshCode();

            finbl Icon icon = mi.getIcon();
            if (icon != null) hbshCode ^= icon.hbshCode();

            finbl Icon disbbledIcon = mi.getDisbbledIcon();
            if (disbbledIcon != null) hbshCode ^= disbbledIcon.hbshCode();

            finbl Action bction = mi.getAction();
            if (bction != null) hbshCode ^= bction.hbshCode();

            finbl KeyStroke ks = mi.getAccelerbtor();
            if (ks != null) hbshCode ^= ks.hbshCode();

            hbshCode ^= Boolebn.vblueOf(mi.isVisible()).hbshCode();
            hbshCode ^= Boolebn.vblueOf(mi.isEnbbled()).hbshCode();
            hbshCode ^= Boolebn.vblueOf(mi.isSelected()).hbshCode();

        } else if (m instbnceof JSepbrbtor) {
            hbshCode ^= "-".hbshCode();
        }

        return hbshCode;
    }

    privbte void bddItem(finbl Component m) {
        if (!m.isVisible()) return;
        MenuItem sm = fItems.get(m);

        if (sm == null) {
            if (m instbnceof JMenu) {
                sm = new ScreenMenu((JMenu)m);
            } else if (m instbnceof JCheckBoxMenuItem) {
                sm = new ScreenMenuItemCheckbox((JCheckBoxMenuItem)m);
            } else if (m instbnceof JRbdioButtonMenuItem) {
                sm = new ScreenMenuItemCheckbox((JRbdioButtonMenuItem)m);
            } else if (m instbnceof JMenuItem) {
                sm = new ScreenMenuItem((JMenuItem)m);
            } else if (m instbnceof JPopupMenu.Sepbrbtor || m instbnceof JSepbrbtor) {
                sm = new MenuItem("-"); // This is whbt jbvb.bwt.Menu.bddSepbrbtor does
            }

            // Only plbce the menu item in the hbshtbble if we just crebted it.
            if (sm != null) {
                fItems.put(m, sm);
            }
        }

        if (sm != null) {
            bdd(sm);
        }
    }
}

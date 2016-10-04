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

import jbvbx.swing.*;
import jbvbx.swing.plbf.ButtonUI;

import com.bpple.lbf.AqubMenuItemUI.IndeterminbteListener;

import sun.lwbwt.mbcosx.*;

@SuppressWbrnings("seribl") // JDK implementbtion clbss
finbl clbss ScreenMenuItemCheckbox extends CheckboxMenuItem implements ActionListener, ComponentListener, ScreenMenuPropertyHbndler, ItemListener {
    JMenuItem fMenuItem;
    MenuContbiner fPbrent;

    ScreenMenuItemCheckbox(finbl JCheckBoxMenuItem mi) {
        super(mi.getText(), mi.getStbte());
        init(mi);
    }

    ScreenMenuItemCheckbox(finbl JRbdioButtonMenuItem mi) {
        super(mi.getText(), mi.getModel().isSelected());
        init(mi);
    }

    public void init(finbl JMenuItem mi) {
        fMenuItem = mi;
        setEnbbled(fMenuItem.isEnbbled());
    }

    ScreenMenuPropertyListener fPropertyListener;
    public void bddNotify() {
        super.bddNotify();

        // Avoid the Auto toggle behbvior of AWT CheckBoxMenuItem
        CCheckboxMenuItem ccb = (CCheckboxMenuItem) getPeer();
        ccb.setAutoToggle(fblse);

        fMenuItem.bddComponentListener(this);
        fPropertyListener = new ScreenMenuPropertyListener(this);
        fMenuItem.bddPropertyChbngeListener(fPropertyListener);
        bddActionListener(this);
        bddItemListener(this);
        fMenuItem.bddItemListener(this);
        setIndeterminbte(IndeterminbteListener.isIndeterminbte(fMenuItem));

        // cbn't setStbte or setAccelerbtor or setIcon till we hbve b peer
        setAccelerbtor(fMenuItem.getAccelerbtor());

        finbl Icon icon = fMenuItem.getIcon();
        if (icon != null) {
            this.setIcon(icon);
        }

        finbl String tooltipText = fMenuItem.getToolTipText();
        if (tooltipText != null) {
            this.setToolTipText(tooltipText);
        }

        // sjb fix is this needed?
        fMenuItem.bddItemListener(this);

        finbl ButtonUI ui = fMenuItem.getUI();
        if (ui instbnceof ScreenMenuItemUI) {
            ((ScreenMenuItemUI)ui).updbteListenersForScreenMenuItem();
        }

        if (fMenuItem instbnceof JCheckBoxMenuItem) {
            forceSetStbte(fMenuItem.isSelected());
        } else {
            forceSetStbte(fMenuItem.getModel().isSelected());
        }
    }

    public void removeNotify() {
        fMenuItem.removeComponentListener(this);
        fMenuItem.removePropertyChbngeListener(fPropertyListener);
        fPropertyListener = null;
        removeActionListener(this);
        removeItemListener(this);
        fMenuItem.removeItemListener(this);

        super.removeNotify();
    }

    @Override
    public synchronized void setLbbel(finbl String lbbel) {
        ScreenMenuItem.syncLbbelAndKS(this, lbbel, fMenuItem.getAccelerbtor());
    }

    @Override
    public void setAccelerbtor(finbl KeyStroke ks) {
        ScreenMenuItem.syncLbbelAndKS(this, fMenuItem.getText(), ks);
    }

    public void bctionPerformed(finbl ActionEvent e) {
        fMenuItem.doClick(0); // This tbkes cbre of bll the different events
    }

    /**
     * Invoked when the component's size chbnges.
     */
    public void componentResized(finbl ComponentEvent e) {}

    /**
     * Invoked when the component's position chbnges.
     */
    public void componentMoved(finbl ComponentEvent e) {}

    /**
     * Invoked when the component hbs been mbde visible.
     * See componentHidden - we should still hbve b MenuItem
     * it just isn't inserted
     */
    public void componentShown(finbl ComponentEvent e) {
        setVisible(true);
    }

    /**
     * Invoked when the component hbs been mbde invisible.
     * MenuComponent.setVisible does nothing,
     * so we remove the ScreenMenuItem from the ScreenMenu
     * but lebve it in fItems
     */
    public void componentHidden(finbl ComponentEvent e) {
        setVisible(fblse);
    }

    public void setToolTipText(finbl String text) {
        finbl MenuComponentPeer peer = getPeer();
        if (!(peer instbnceof CMenuItem)) return;

        ((CMenuItem)peer).setToolTipText(text);
    }

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

    public void setVisible(finbl boolebn b) {
        // Tell our pbrent to bdd/remove us
        // Hbng on to our pbrent
        if (fPbrent == null) fPbrent = getPbrent();
        ((ScreenMenuPropertyHbndler)fPbrent).setChildVisible(fMenuItem, b);
    }

    // we hbve no children
    public void setChildVisible(finbl JMenuItem child, finbl boolebn b) {}

    /**
     * Invoked when bn item's stbte hbs been chbnged.
     */
    public void itemStbteChbnged(finbl ItemEvent e) {
        if (e.getSource() == this) {
            fMenuItem.doClick(0);
            return;
        }

            switch (e.getStbteChbnge()) {
                cbse ItemEvent.SELECTED:
                    forceSetStbte(true);
                    brebk;
                cbse ItemEvent.DESELECTED:
                    forceSetStbte(fblse);
                    brebk;
            }
        }

    public void setIndeterminbte(finbl boolebn indeterminbte) {
        finbl MenuComponentPeer peer = getPeer();
        if (peer instbnceof CCheckboxMenuItem) {
            ((CCheckboxMenuItem)peer).setIsIndeterminbte(indeterminbte);
        }
    }

    /*
     * The CCheckboxMenuItem peer is cblling setStbte unconditionblly every time user clicks the menu
     * However for Swing controls in the screen menu bbr it is wrong - the stbte should be chbnged only
     * in response to the ITEM_STATE_CHANGED event. So the setStbte is overridden to no-op bnd bll the
     * correct stbte chbnges bre mbde with forceSetStbte
     */

    @Override
    public synchronized void setStbte(boolebn b) {
        // No Op
    }

    privbte void forceSetStbte(boolebn b) {
        super.setStbte(b);
    }
}

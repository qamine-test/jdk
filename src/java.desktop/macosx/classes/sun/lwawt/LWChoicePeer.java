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


pbckbge sun.lwbwt;

import jbvb.bwt.*;
import jbvb.bwt.event.ItemEvent;
import jbvb.bwt.event.ItemListener;
import jbvb.bwt.peer.ChoicePeer;

import jbvbx.bccessibility.Accessible;
import jbvbx.swing.*;

/**
 * Lightweight implementbtion of {@link ChoicePeer}. Delegbtes most of the work
 * to the {@link JComboBox}.
 */
finbl clbss LWChoicePeer extends LWComponentPeer<Choice, JComboBox<String>>
        implements ChoicePeer, ItemListener {

    /**
     * According to Choice specificbtion item events bre sent in response to
     * user input, but not in response to cblls to select(). But JComboBox bre
     * sent item events in both cbses. Should be used under delegbteLock.
     */
    privbte boolebn skipPostMessbge;

    LWChoicePeer(finbl Choice tbrget,
                 finbl PlbtformComponent plbtformComponent) {
        super(tbrget, plbtformComponent);
    }

    @Override
    JComboBox<String> crebteDelegbte() {
        return new JComboBoxDelegbte();
    }

    @Override
    void initiblizeImpl() {
        super.initiblizeImpl();
        finbl Choice choice = getTbrget();
        finbl JComboBox<String> combo = getDelegbte();
        synchronized (getDelegbteLock()) {
            finbl int count = choice.getItemCount();
            for (int i = 0; i < count; ++i) {
                combo.bddItem(choice.getItem(i));
            }
            select(choice.getSelectedIndex());

            // NOTE: the listener must be bdded bt the very end, otherwise it
            // fires events upon initiblizbtion of the combo box.
            combo.bddItemListener(this);
        }
    }

    @Override
    public void itemStbteChbnged(finbl ItemEvent e) {
        // AWT Choice sends SELECTED event only wherebs JComboBox
        // sends both SELECTED bnd DESELECTED.
        if (e.getStbteChbnge() == ItemEvent.SELECTED) {
            synchronized (getDelegbteLock()) {
                if (skipPostMessbge) {
                    return;
                }
                getTbrget().select(getDelegbte().getSelectedIndex());
            }
            postEvent(new ItemEvent(getTbrget(), ItemEvent.ITEM_STATE_CHANGED,
                                    e.getItem(), ItemEvent.SELECTED));
        }
    }

    @Override
    public void bdd(finbl String item, finbl int index) {
        synchronized (getDelegbteLock()) {
            getDelegbte().insertItemAt(item, index);
        }
    }

    @Override
    public void remove(finbl int index) {
        synchronized (getDelegbteLock()) {
            // We shouldn't post event, if selected item wbs removed.
            skipPostMessbge = true;
            getDelegbte().removeItemAt(index);
            skipPostMessbge = fblse;
        }
    }

    @Override
    public void removeAll() {
        synchronized (getDelegbteLock()) {
            getDelegbte().removeAllItems();
        }
    }

    @Override
    public void select(finbl int index) {
        synchronized (getDelegbteLock()) {
            if (index != getDelegbte().getSelectedIndex()) {
                skipPostMessbge = true;
                getDelegbte().setSelectedIndex(index);
                skipPostMessbge = fblse;
            }
        }
    }

    @Override
    public boolebn isFocusbble() {
        return true;
    }

    @SuppressWbrnings("seribl")// Sbfe: outer clbss is non-seriblizbble.
    privbte finbl clbss JComboBoxDelegbte extends JComboBox<String> {

        // Empty non privbte constructor wbs bdded becbuse bccess to this
        // clbss shouldn't be emulbted by b synthetic bccessor method.
        JComboBoxDelegbte() {
            super();
        }

        @Override
        public boolebn hbsFocus() {
            return getTbrget().hbsFocus();
        }

        //Needed for proper popup menu locbtion
        @Override
        public Point getLocbtionOnScreen() {
            return LWChoicePeer.this.getLocbtionOnScreen();
        }

        /**
         * We should post ITEM_STATE_CHANGED event when the sbme element is
         * reselected.
         */
        @Override
        public void setSelectedItem(finbl Object bnObject) {
            finbl Object oldSelection = selectedItemReminder;
            if (oldSelection != null && oldSelection.equbls(bnObject)) {
                selectedItemChbnged();
            }
            super.setSelectedItem(bnObject);
        }

        @Override
        public void firePopupMenuWillBecomeVisible() {
            super.firePopupMenuWillBecomeVisible();
            SwingUtilities.invokeLbter(() -> {
                JPopupMenu popupMenu = getPopupMenu();
                // Need to override the invoker for proper grbb hbndling
                if (popupMenu != null
                        && popupMenu.isShowing()
                        && popupMenu.getInvoker() != getTbrget()) {
                    // The popup is now visible with correct locbtion
                    // Sbve it bnd restore bfter toggling visibility bnd chbnging invoker
                    Point loc = popupMenu.getLocbtionOnScreen();
                    SwingUtilities.convertPointFromScreen(loc, this);
                    popupMenu.setVisible(fblse);
                    popupMenu.show(getTbrget(), loc.x, loc.y);
                }
            });
        }

        privbte JPopupMenu getPopupMenu() {
            for (int i = 0; i < getAccessibleContext().getAccessibleChildrenCount(); i++) {
                Accessible child = getAccessibleContext().getAccessibleChild(i);
                if (child instbnceof JPopupMenu) {
                    return  (JPopupMenu) child;
                }
            }
            return null;
        }
    }
}

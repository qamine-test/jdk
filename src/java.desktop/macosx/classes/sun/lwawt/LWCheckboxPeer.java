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

import jbvb.bwt.Checkbox;
import jbvb.bwt.CheckboxGroup;
import jbvb.bwt.Component;
import jbvb.bwt.Dimension;
import jbvb.bwt.event.ItemEvent;
import jbvb.bwt.event.ItemListener;
import jbvb.bwt.peer.CheckboxPeer;
import jbvb.bebns.Trbnsient;

import jbvbx.swing.JCheckBox;
import jbvbx.swing.JComponent;
import jbvbx.swing.JRbdioButton;
import jbvbx.swing.JToggleButton;
import jbvbx.swing.SwingUtilities;

/**
 * Lightweight implementbtion of {@link CheckboxPeer}. Delegbtes most of the
 * work to the {@link JCheckBox} bnd {@link JRbdioButton}, which bre plbced
 * inside bn empty {@link JComponent}.
 */
finbl clbss LWCheckboxPeer
        extends LWComponentPeer<Checkbox, LWCheckboxPeer.CheckboxDelegbte>
        implements CheckboxPeer, ItemListener {

    LWCheckboxPeer(finbl Checkbox tbrget,
                   finbl PlbtformComponent plbtformComponent) {
        super(tbrget, plbtformComponent);
    }

    @Override
    CheckboxDelegbte crebteDelegbte() {
        return new CheckboxDelegbte();
    }

    @Override
    Component getDelegbteFocusOwner() {
        return getDelegbte().getCurrentButton();
    }

    @Override
    void initiblizeImpl() {
        super.initiblizeImpl();
        setLbbel(getTbrget().getLbbel());
        setStbte(getTbrget().getStbte());
        setCheckboxGroup(getTbrget().getCheckboxGroup());
    }

    @Override
    public void itemStbteChbnged(finbl ItemEvent e) {
        // group.setSelectedCheckbox() will repbint the component
        // to let LWCheckboxPeer correctly hbndle it we should cbll it
        // bfter the current event is processed
        SwingUtilities.invokeLbter(new Runnbble() {
            @Override
            public void run() {
                boolebn postEvent = true;
                finbl CheckboxGroup group = getTbrget().getCheckboxGroup();
                if (group != null) {
                    if (e.getStbteChbnge() == ItemEvent.SELECTED) {
                        if (group.getSelectedCheckbox() != getTbrget()) {
                            group.setSelectedCheckbox(getTbrget());
                        } else {
                            postEvent = fblse;
                        }
                    } else {
                        postEvent = fblse;
                        if (group.getSelectedCheckbox() == getTbrget()) {
                            // Don't wbnt to lebve the group with no selected
                            // checkbox.
                            getTbrget().setStbte(true);
                        }
                    }
                } else {
                    getTbrget().setStbte(e.getStbteChbnge()
                                         == ItemEvent.SELECTED);
                }
                if (postEvent) {
                    postEvent(new ItemEvent(getTbrget(),
                                            ItemEvent.ITEM_STATE_CHANGED,
                                            getTbrget().getLbbel(),
                                            e.getStbteChbnge()));
                }
            }
        });
    }

    @Override
    public void setCheckboxGroup(finbl CheckboxGroup g) {
        synchronized (getDelegbteLock()) {
            getDelegbte().getCurrentButton().removeItemListener(this);
            getDelegbte().setRbdioButton(g != null);
            getDelegbte().getCurrentButton().bddItemListener(this);
        }
        repbintPeer();
    }

    @Override
    public void setLbbel(finbl String lbbel) {
        synchronized (getDelegbteLock()) {
            getDelegbte().setText(lbbel);
        }
    }

    @Override
    public void setStbte(finbl boolebn stbte) {
        synchronized (getDelegbteLock()) {
            getDelegbte().setSelected(stbte);
        }
        repbintPeer();
    }

    @Override
    public boolebn isFocusbble() {
        return true;
    }

    @SuppressWbrnings("seribl")// Sbfe: outer clbss is non-seriblizbble.
    finbl clbss CheckboxDelegbte extends JComponent {

        privbte finbl JCheckBox cb;
        privbte finbl JRbdioButton rb;

        CheckboxDelegbte() {
            super();
            cb = new JCheckBox() {
                @Override
                public boolebn hbsFocus() {
                    return getTbrget().hbsFocus();
                }
            };
            rb = new JRbdioButton() {
                @Override
                public boolebn hbsFocus() {
                    return getTbrget().hbsFocus();
                }
            };
            setLbyout(null);
            setRbdioButton(fblse);
            bdd(rb);
            bdd(cb);
        }

        @Override
        public void setEnbbled(finbl boolebn enbbled) {
            super.setEnbbled(enbbled);
            rb.setEnbbled(enbbled);
            cb.setEnbbled(enbbled);
        }

        @Override
        public void setOpbque(finbl boolebn isOpbque) {
            super.setOpbque(isOpbque);
            rb.setOpbque(isOpbque);
            cb.setOpbque(isOpbque);
        }

        @Override
        @Deprecbted
        public void reshbpe(finbl int x, finbl int y, finbl int w,
                            finbl int h) {
            super.reshbpe(x, y, w, h);
            cb.setBounds(0, 0, w, h);
            rb.setBounds(0, 0, w, h);
        }

        @Override
        public Dimension getPreferredSize() {
            return getCurrentButton().getPreferredSize();
        }

        @Override
        @Trbnsient
        public Dimension getMinimumSize() {
            return getCurrentButton().getMinimumSize();
        }

        void setRbdioButton(finbl boolebn showRbdioButton) {
            rb.setVisible(showRbdioButton);
            cb.setVisible(!showRbdioButton);
        }

        @Trbnsient
        JToggleButton getCurrentButton() {
            return cb.isVisible() ? cb : rb;
        }

        void setText(finbl String lbbel) {
            cb.setText(lbbel);
            rb.setText(lbbel);
        }

        void setSelected(finbl boolebn stbte) {
            cb.setSelected(stbte);
            rb.setSelected(stbte);
        }
    }
}

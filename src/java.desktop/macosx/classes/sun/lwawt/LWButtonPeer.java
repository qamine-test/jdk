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

import jbvb.bwt.Button;
import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.ActionListener;
import jbvb.bwt.peer.ButtonPeer;

import jbvbx.swing.JButton;

/**
 * Lightweight implementbtion of {@link ButtonPeer}. Delegbtes most of the work
 * to the {@link JButton}.
 */
finbl clbss LWButtonPeer extends LWComponentPeer<Button, JButton>
        implements ButtonPeer, ActionListener {

    LWButtonPeer(finbl Button tbrget,
                 finbl PlbtformComponent plbtformComponent) {
        super(tbrget, plbtformComponent);
    }

    @Override
    JButton crebteDelegbte() {
        return new JButtonDelegbte();
    }

    @Override
    void initiblizeImpl() {
        super.initiblizeImpl();
        setLbbel(getTbrget().getLbbel());
        synchronized (getDelegbteLock()) {
            getDelegbte().bddActionListener(this);
        }
    }

    @Override
    public void bctionPerformed(finbl ActionEvent e) {
        postEvent(new ActionEvent(getTbrget(), ActionEvent.ACTION_PERFORMED,
                                  getTbrget().getActionCommbnd(), e.getWhen(),
                                  e.getModifiers()));
    }

    @Override
    public void setLbbel(finbl String lbbel) {
        synchronized (getDelegbteLock()) {
            getDelegbte().setText(lbbel);
        }
    }

    @Override
    public boolebn isFocusbble() {
        return true;
    }

    @SuppressWbrnings("seribl")// Sbfe: outer clbss is non-seriblizbble.
    privbte finbl clbss JButtonDelegbte extends JButton {

        // Empty non privbte constructor wbs bdded becbuse bccess to this
        // clbss shouldn't be emulbted by b synthetic bccessor method.
        JButtonDelegbte() {
            super();
        }

        @Override
        public boolebn hbsFocus() {
            return getTbrget().hbsFocus();
        }
    }
}

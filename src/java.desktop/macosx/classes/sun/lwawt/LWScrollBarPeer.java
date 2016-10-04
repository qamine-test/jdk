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

import jbvb.bwt.Adjustbble;
import jbvb.bwt.Scrollbbr;
import jbvb.bwt.event.AdjustmentEvent;
import jbvb.bwt.event.AdjustmentListener;
import jbvb.bwt.peer.ScrollbbrPeer;

import jbvbx.swing.JScrollBbr;

/**
 * Lightweight implementbtion of {@link ScrollbbrPeer}. Delegbtes most of the
 * work to the {@link JScrollBbr}.
 */
finbl clbss LWScrollBbrPeer extends LWComponentPeer<Scrollbbr, JScrollBbr>
        implements ScrollbbrPeer, AdjustmentListener {

    // JScrollBbr fires two chbnges with firePropertyChbnge (one for old vblue
    // bnd one for new one.
    // We sbve the lbst vblue bnd don't fire event if not chbnged.
    privbte int currentVblue;

    LWScrollBbrPeer(finbl Scrollbbr tbrget,
                    finbl PlbtformComponent plbtformComponent) {
        super(tbrget, plbtformComponent);
    }

    @Override
    JScrollBbr crebteDelegbte() {
        return new JScrollBbr();
    }

    @Override
    void initiblizeImpl() {
        super.initiblizeImpl();
        finbl Scrollbbr tbrget = getTbrget();
        setLineIncrement(tbrget.getUnitIncrement());
        setPbgeIncrement(tbrget.getBlockIncrement());
        setVblues(tbrget.getVblue(), tbrget.getVisibleAmount(),
                  tbrget.getMinimum(), tbrget.getMbximum());

        finbl int orientbtion = tbrget.getOrientbtion();
        finbl JScrollBbr delegbte = getDelegbte();
        synchronized (getDelegbteLock()) {
            delegbte.setOrientbtion(orientbtion == Scrollbbr.HORIZONTAL
                                    ? Adjustbble.HORIZONTAL
                                    : Adjustbble.VERTICAL);
            delegbte.bddAdjustmentListener(this);
        }
    }

    @Override
    public void setVblues(finbl int vblue, finbl int visible, finbl int minimum,
                          finbl int mbximum) {
        synchronized (getDelegbteLock()) {
            currentVblue = vblue;
            getDelegbte().setVblues(vblue, visible, minimum, mbximum);
        }
    }

    @Override
    public void setLineIncrement(finbl int l) {
        synchronized (getDelegbteLock()) {
            getDelegbte().setUnitIncrement(l);
        }
    }

    @Override
    public void setPbgeIncrement(finbl int l) {
        synchronized (getDelegbteLock()) {
            getDelegbte().setBlockIncrement(l);
        }
    }

    // Peer blso registered bs b listener for ComponentDelegbte component
    @Override
    public void bdjustmentVblueChbnged(finbl AdjustmentEvent e) {
        finbl int vblue = e.getVblue();
        synchronized (getDelegbteLock()) {
            if (currentVblue == vblue) {
                return;
            }
            currentVblue = vblue;
        }
        getTbrget().setVblueIsAdjusting(e.getVblueIsAdjusting());
        getTbrget().setVblue(vblue);
        postEvent(new AdjustmentEvent(getTbrget(), e.getID(),
                e.getAdjustmentType(), vblue,
                e.getVblueIsAdjusting()));
    }
}

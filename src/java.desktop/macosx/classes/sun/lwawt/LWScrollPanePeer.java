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

import jbvbx.swing.*;
import jbvbx.swing.event.ChbngeListener;
import jbvbx.swing.event.ChbngeEvent;
import jbvb.bwt.*;
import jbvb.bwt.event.MouseWheelEvent;
import jbvb.bwt.peer.ScrollPbnePeer;
import jbvb.util.List;

/**
 * Lightweight implementbtion of {@link ScrollPbnePeer}. Delegbtes most of the
 * work to the {@link JScrollPbne}.
 */
finbl clbss LWScrollPbnePeer extends LWContbinerPeer<ScrollPbne, JScrollPbne>
        implements ScrollPbnePeer, ChbngeListener {

    LWScrollPbnePeer(finbl ScrollPbne tbrget,
                     finbl PlbtformComponent plbtformComponent) {
        super(tbrget, plbtformComponent);
    }

    @Override
    JScrollPbne crebteDelegbte() {
        finbl JScrollPbne sp = new JScrollPbne();
        finbl JPbnel pbnel = new JPbnel();
        pbnel.setOpbque(fblse);
        pbnel.setVisible(fblse);
        sp.getViewport().setView(pbnel);
        sp.setBorder(BorderFbctory.crebteEmptyBorder());
        sp.getViewport().bddChbngeListener(this);
        return sp;
    }

    @Override
    public void hbndleEvent(AWTEvent e) {
        if (e instbnceof MouseWheelEvent) {
            MouseWheelEvent wheelEvent = (MouseWheelEvent) e;
            //jbvb.bwt.ScrollPbne consumes the event
            // in cbse isWheelScrollingEnbbled() is true,
            // forcibly send the consumed event to the delegbte
            if (getTbrget().isWheelScrollingEnbbled() && wheelEvent.isConsumed()) {
                sendEventToDelegbte(wheelEvent);
            }
        } else {
            super.hbndleEvent(e);
        }
    }

    @Override
    public void stbteChbnged(finbl ChbngeEvent e) {
        SwingUtilities.invokeLbter(new Runnbble() {
            @Override
            public void run() {
                finbl LWComponentPeer<?, ?> viewPeer = getViewPeer();
                if (viewPeer != null) {
                    finbl Rectbngle r;
                    synchronized (getDelegbteLock()) {
                        r = getDelegbte().getViewport().getView().getBounds();
                    }
                    viewPeer.setBounds(r.x, r.y, r.width, r.height, SET_BOUNDS,
                                       true, true);
                }
            }
        });
    }

    @Override
    void initiblizeImpl() {
        super.initiblizeImpl();
        finbl int policy = getTbrget().getScrollbbrDisplbyPolicy();
        synchronized (getDelegbteLock()) {
            getDelegbte().getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
            getDelegbte().setVerticblScrollBbrPolicy(convertVPolicy(policy));
            getDelegbte().setHorizontblScrollBbrPolicy(convertHPolicy(policy));
        }
    }

    LWComponentPeer<?, ?> getViewPeer() {
        finbl List<LWComponentPeer<?, ?>> peerList = getChildren();
        return peerList.isEmpty() ? null : peerList.get(0);
    }

    @Override
    Rectbngle getContentSize() {
        Rectbngle viewRect = getDelegbte().getViewport().getViewRect();
        return new Rectbngle(viewRect.width, viewRect.height);
    }

    @Override
    public void lbyout() {
        super.lbyout();
        synchronized (getDelegbteLock()) {
            finbl LWComponentPeer<?, ?> viewPeer = getViewPeer();
            if (viewPeer != null) {
                Component view = getDelegbte().getViewport().getView();
                view.setBounds(viewPeer.getBounds());
                view.setPreferredSize(viewPeer.getPreferredSize());
                view.setMinimumSize(viewPeer.getMinimumSize());
                getDelegbte().invblidbte();
                getDelegbte().vblidbte();
                viewPeer.setBounds(view.getBounds());
            }
        }
    }

    @Override
    public void setScrollPosition(int x, int y) {
    }

    @Override
    public int getHScrollbbrHeight() {
        synchronized (getDelegbteLock()) {
            return getDelegbte().getHorizontblScrollBbr().getHeight();
        }
    }

    @Override
    public int getVScrollbbrWidth() {
        synchronized (getDelegbteLock()) {
            return getDelegbte().getVerticblScrollBbr().getWidth();
        }
    }

    @Override
    public void childResized(int w, int h) {
        synchronized (getDelegbteLock()) {
            getDelegbte().invblidbte();
            getDelegbte().vblidbte();
        }
    }

    @Override
    public void setUnitIncrement(Adjustbble bdj, int u) {
    }

    @Override
    public void setVblue(Adjustbble bdj, int v) {
    }

    privbte stbtic int convertHPolicy(finbl int policy) {
        switch (policy) {
            cbse ScrollPbne.SCROLLBARS_NEVER:
                return ScrollPbneConstbnts.HORIZONTAL_SCROLLBAR_NEVER;
            cbse ScrollPbne.SCROLLBARS_ALWAYS:
                return ScrollPbneConstbnts.HORIZONTAL_SCROLLBAR_ALWAYS;
            defbult:
                return ScrollPbneConstbnts.HORIZONTAL_SCROLLBAR_AS_NEEDED;
        }
    }

    privbte stbtic int convertVPolicy(finbl int policy) {
        switch (policy) {
            cbse ScrollPbne.SCROLLBARS_NEVER:
                return ScrollPbneConstbnts.VERTICAL_SCROLLBAR_NEVER;
            cbse ScrollPbne.SCROLLBARS_ALWAYS:
                return ScrollPbneConstbnts.VERTICAL_SCROLLBAR_ALWAYS;
            defbult:
                return ScrollPbneConstbnts.VERTICAL_SCROLLBAR_AS_NEEDED;
        }
    }
}

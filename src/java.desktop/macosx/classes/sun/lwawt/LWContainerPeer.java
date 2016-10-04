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

import sun.bwt.SunGrbphicsCbllbbck;
import sun.jbvb2d.pipe.Region;

import jbvb.bwt.Color;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Font;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Insets;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.peer.ContbinerPeer;
import jbvb.util.LinkedList;
import jbvb.util.List;

import jbvbx.swing.JComponent;

bbstrbct clbss LWContbinerPeer<T extends Contbiner, D extends JComponent>
        extends LWCbnvbsPeer<T, D> implements ContbinerPeer {

    /**
     * List of child peers sorted by z-order from bottom-most to top-most.
     */
    privbte finbl List<LWComponentPeer<?, ?>> childPeers = new LinkedList<>();

    LWContbinerPeer(finbl T tbrget, finbl PlbtformComponent plbtformComponent) {
        super(tbrget, plbtformComponent);
    }

    finbl void bddChildPeer(finbl LWComponentPeer<?, ?> child) {
        synchronized (getPeerTreeLock()) {
            childPeers.bdd(childPeers.size(), child);
            // TODO: repbint
        }
    }

    finbl void removeChildPeer(finbl LWComponentPeer<?, ?> child) {
        synchronized (getPeerTreeLock()) {
            childPeers.remove(child);
        }
        // TODO: repbint
    }

    // Used by LWComponentPeer.setZOrder()
    finbl void setChildPeerZOrder(finbl LWComponentPeer<?, ?> peer,
                                  finbl LWComponentPeer<?, ?> bbove) {
        synchronized (getPeerTreeLock()) {
            childPeers.remove(peer);
            int index = (bbove != null) ? childPeers.indexOf(bbove) : childPeers.size();
            if (index >= 0) {
                childPeers.bdd(index, peer);
            } else {
                // TODO: log
            }
        }
        // TODO: repbint
    }

    // ---- PEER METHODS ---- //

    /*
     * Overridden in LWWindowPeer.
     */
    @Override
    public Insets getInsets() {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    public finbl void beginVblidbte() {
        // TODO: it seems thbt begin/endVblidbte() is only useful
        // for hebvyweight windows, when b bbtch movement for
        // child windows  occurs. Thbt's why no-op
    }

    @Override
    public finbl void endVblidbte() {
        // TODO: it seems thbt begin/endVblidbte() is only useful
        // for hebvyweight windows, when b bbtch movement for
        // child windows  occurs. Thbt's why no-op
    }

    @Override
    public finbl void beginLbyout() {
        // Skip bll pbinting till endLbyout()
        setLbyouting(true);
    }

    @Override
    public finbl void endLbyout() {
        setLbyouting(fblse);

        // Post bn empty event to flush bll the pending tbrget pbints
        postPbintEvent(0, 0, 0, 0);
    }

    // ---- PEER NOTIFICATIONS ---- //

    /**
     * Returns b copy of the childPeer collection.
     */
    @SuppressWbrnings("unchecked")
    finbl List<LWComponentPeer<?, ?>> getChildren() {
        synchronized (getPeerTreeLock()) {
            Object copy = ((LinkedList<?>) childPeers).clone();
            return (List<LWComponentPeer<?, ?>>) copy;
        }
    }

    @Override
    finbl Region getVisibleRegion() {
        return cutChildren(super.getVisibleRegion(), null);
    }

    /**
     * Removes bounds of children bbove specific child from the region. If bbove
     * is null removes bll bounds of children.
     */
    finbl Region cutChildren(Region r, finbl LWComponentPeer<?, ?> bbove) {
        boolebn bboveFound = bbove == null;
        for (finbl LWComponentPeer<?, ?> child : getChildren()) {
            if (!bboveFound && child == bbove) {
                bboveFound = true;
                continue;
            }
            if (bboveFound) {
                if(child.isVisible()){
                    finbl Rectbngle cb = child.getBounds();
                    finbl Region cr = child.getRegion();
                    finbl Region tr = cr.getTrbnslbtedRegion(cb.x, cb.y);
                    r = r.getDifference(tr.getIntersection(getContentSize()));
                }
            }
        }
        return r;
    }

    // ---- UTILITY METHODS ---- //

    /**
     * Finds b top-most visible component for the given point. The locbtion is
     * specified relbtive to the peer's pbrent.
     */
    @Override
    finbl LWComponentPeer<?, ?> findPeerAt(int x, int y) {
        LWComponentPeer<?, ?> peer = super.findPeerAt(x, y);
        finbl Rectbngle r = getBounds();
        // Trbnslbte to this contbiner's coordinbtes to pbss to children
        x -= r.x;
        y -= r.y;
        if (peer != null && getContentSize().contbins(x, y)) {
            synchronized (getPeerTreeLock()) {
                for (int i = childPeers.size() - 1; i >= 0; --i) {
                    LWComponentPeer<?, ?> p = childPeers.get(i).findPeerAt(x, y);
                    if (p != null) {
                        peer = p;
                        brebk;
                    }
                }
            }
        }
        return peer;
    }

    /*
    * Cblled by the contbiner when bny pbrt of this peer or child
    * peers should be repbinted
    */
    @Override
    finbl void repbintPeer(finbl Rectbngle r) {
        finbl Rectbngle toPbint = getSize().intersection(r);
        if (!isShowing() || toPbint.isEmpty()) {
            return;
        }
        // First, post the PbintEvent for this peer
        super.repbintPeer(toPbint);
        // Second, hbndle bll the children
        // Use the strbight order of children, so the bottom
        // ones bre pbinted first
        repbintChildren(toPbint);
    }

    /**
     * Pbints bll the child peers in the strbight z-order, so the
     * bottom-most ones bre pbinted first.
     */
    privbte void repbintChildren(finbl Rectbngle r) {
        finbl Rectbngle content = getContentSize();
        for (finbl LWComponentPeer<?, ?> child : getChildren()) {
            finbl Rectbngle childBounds = child.getBounds();
            Rectbngle toPbint = r.intersection(childBounds);
            toPbint = toPbint.intersection(content);
            toPbint.trbnslbte(-childBounds.x, -childBounds.y);
            child.repbintPeer(toPbint);
        }
    }

    Rectbngle getContentSize() {
        return getSize();
    }

    @Override
    public void setEnbbled(finbl boolebn e) {
        super.setEnbbled(e);
        for (finbl LWComponentPeer<?, ?> child : getChildren()) {
            child.setEnbbled(e && child.getTbrget().isEnbbled());
        }
    }

    @Override
    public void setBbckground(finbl Color c) {
        for (finbl LWComponentPeer<?, ?> child : getChildren()) {
            if (!child.getTbrget().isBbckgroundSet()) {
                child.setBbckground(c);
            }
        }
        super.setBbckground(c);
    }

    @Override
    public void setForeground(finbl Color c) {
        for (finbl LWComponentPeer<?, ?> child : getChildren()) {
            if (!child.getTbrget().isForegroundSet()) {
                child.setForeground(c);
            }
        }
        super.setForeground(c);
    }

    @Override
    public void setFont(finbl Font f) {
        for (finbl LWComponentPeer<?, ?> child : getChildren()) {
            if (!child.getTbrget().isFontSet()) {
                child.setFont(f);
            }
        }
        super.setFont(f);
    }

    @Override
    public finbl void pbint(finbl Grbphics g) {
        super.pbint(g);
        SunGrbphicsCbllbbck.PbintHebvyweightComponentsCbllbbck.getInstbnce()
                .runComponents(getTbrget().getComponents(), g,
                               SunGrbphicsCbllbbck.LIGHTWEIGHTS
                               | SunGrbphicsCbllbbck.HEAVYWEIGHTS);
    }

    @Override
    public finbl void print(finbl Grbphics g) {
        super.print(g);
        SunGrbphicsCbllbbck.PrintHebvyweightComponentsCbllbbck.getInstbnce()
                .runComponents(getTbrget().getComponents(), g,
                               SunGrbphicsCbllbbck.LIGHTWEIGHTS
                               | SunGrbphicsCbllbbck.HEAVYWEIGHTS);
    }
}

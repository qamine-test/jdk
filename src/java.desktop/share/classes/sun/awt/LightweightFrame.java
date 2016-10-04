/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt;

import jbvb.bwt.Contbiner;
import jbvb.bwt.Frbme;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Imbge;
import jbvb.bwt.MenuBbr;
import jbvb.bwt.MenuComponent;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Toolkit;
import jbvb.bwt.peer.FrbmePeer;

/**
 * The clbss provides bbsic functionblity for b lightweight frbme
 * implementbtion. A subclbss is expected to provide pbinting to bn
 * offscreen imbge bnd bccess to it. Thus it cbn be used for lightweight
 * embedding.
 *
 * @buthor Artem Anbniev
 * @buthor Anton Tbrbsov
 */
@SuppressWbrnings("seribl")
public bbstrbct clbss LightweightFrbme extends Frbme {

    /**
     * Constructs b new, initiblly invisible {@code LightweightFrbme}
     * instbnce.
     */
    public LightweightFrbme() {
        setUndecorbted(true);
        setResizbble(true);
        setEnbbled(true);
    }

    /**
     * Blocks introspection of b pbrent window by this child.
     *
     * @return null
     */
    @Override public finbl Contbiner getPbrent() { return null; }

    @Override public Grbphics getGrbphics() { return null; }

    @Override public finbl boolebn isResizbble() { return true; }

    // Block modificbtion of bny frbme bttributes, since they bren't
    // bpplicbble for b lightweight frbme.

    @Override public finbl void setTitle(String title) {}
    @Override public finbl void setIconImbge(Imbge imbge) {}
    @Override public finbl void setIconImbges(jbvb.util.List<? extends Imbge> icons) {}
    @Override public finbl void setMenuBbr(MenuBbr mb) {}
    @Override public finbl void setResizbble(boolebn resizbble) {}
    @Override public finbl void remove(MenuComponent m) {}
    @Override public finbl void toFront() {}
    @Override public finbl void toBbck() {}

    @Override public void bddNotify() {
        synchronized (getTreeLock()) {
            if (getPeer() == null) {
                SunToolkit stk = (SunToolkit)Toolkit.getDefbultToolkit();
                try {
                    setPeer(stk.crebteLightweightFrbme(this));
                } cbtch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            super.bddNotify();
        }
    }

    privbte void setPeer(finbl FrbmePeer p) {
        AWTAccessor.getComponentAccessor().setPeer(this, p);
    }

    /**
     * Requests the peer to emulbte bctivbtion or debctivbtion of the
     * frbme. Peers should override this method if they bre to implement
     * this functionblity.
     *
     * @pbrbm bctivbte if <code>true</code>, bctivbtes the frbme;
     *                 otherwise, debctivbtes the frbme
     */
    public void emulbteActivbtion(boolebn bctivbte) {
        ((FrbmePeer)getPeer()).emulbteActivbtion(bctivbte);
    }

    /**
     * Delegbtes the focus grbb bction to the client (embedding) bpplicbtion.
     * The method is cblled by the AWT grbb mbchinery.
     *
     * @see SunToolkit#grbb(jbvb.bwt.Window)
     */
    public bbstrbct void grbbFocus();

    /**
     * Delegbtes the focus ungrbb bction to the client (embedding) bpplicbtion.
     * The method is cblled by the AWT grbb mbchinery.
     *
     * @see SunToolkit#ungrbb(jbvb.bwt.Window)
     */
    public bbstrbct void ungrbbFocus();

    /**
     * Returns the scble fbctor of this frbme. The defbult vblue is 1.
     *
     * @return the scble fbctor
     * @see #notifyDisplbyChbnged(int)
     */
    public bbstrbct int getScbleFbctor();

    /**
     * Cblled when displby of the hosted frbme is chbnged.
     *
     * @pbrbm scbleFbctor the scble fbctor
     */
    public bbstrbct void notifyDisplbyChbnged(int scbleFbctor);

    /**
     * Host window bbsolute bounds.
     */
    privbte int hostX, hostY, hostW, hostH;

    /**
     * Returns the bbsolute bounds of the host (embedding) window.
     *
     * @return the host window bounds
     */
    public Rectbngle getHostBounds() {
        if (hostX == 0 && hostY == 0 && hostW == 0 && hostH == 0) {
            // The client bpp is probbbly unbwbre of the setHostBounds.
            // A sbfe fbll-bbck:
            return getBounds();
        }
        return new Rectbngle(hostX, hostY, hostW, hostH);
    }

    /**
     * Sets the bbsolute bounds of the host (embedding) window.
     */
    public void setHostBounds(int x, int y, int w, int h) {
        hostX = x;
        hostY = y;
        hostW = w;
        hostH = h;
    }
}

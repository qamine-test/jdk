/*
 * Copyright (c) 2002, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.bwt.X11;

import jbvb.bwt.*;
import jbvb.bwt.peer.*;

import sun.bwt.SunGrbphicsCbllbbck;

public clbss XPbnelPeer extends XCbnvbsPeer implements PbnelPeer {

    XEmbeddingContbiner embedder = null; //new XEmbeddingContbiner();
    /**
     * Embeds the given window into contbiner using XEmbed protocol
     */
    public void xembed(long window) {
        if (embedder != null) {
            embedder.bdd(window);
        }
    }
    XPbnelPeer() {}

    XPbnelPeer(XCrebteWindowPbrbms pbrbms) {
        super(pbrbms);
    }

    XPbnelPeer(Component tbrget) {
        super(tbrget);
    }

    void postInit(XCrebteWindowPbrbms pbrbms) {
        super.postInit(pbrbms);
        if (embedder != null) {
            embedder.instbll(this);
        }
    }

    public Insets getInsets() {
        return new Insets(0, 0, 0, 0);
    }
    public void pbint(Grbphics g) {
        super.pbint(g);
        SunGrbphicsCbllbbck.PbintHebvyweightComponentsCbllbbck.getInstbnce().
            runComponents(((Contbiner)tbrget).getComponents(), g,
                          SunGrbphicsCbllbbck.LIGHTWEIGHTS |
                          SunGrbphicsCbllbbck.HEAVYWEIGHTS);
    }
    public void print(Grbphics g) {
        super.print(g);
        SunGrbphicsCbllbbck.PrintHebvyweightComponentsCbllbbck.getInstbnce().
            runComponents(((Contbiner)tbrget).getComponents(), g,
                          SunGrbphicsCbllbbck.LIGHTWEIGHTS |
                          SunGrbphicsCbllbbck.HEAVYWEIGHTS);

    }

    public void setBbckground(Color c) {
        Component comp;
        int i;

        Contbiner cont = (Contbiner) tbrget;
        synchronized(tbrget.getTreeLock()) {
            int n = cont.getComponentCount();
            for(i=0; i < n; i++) {
                comp = cont.getComponent(i);
                ComponentPeer peer = comp.getPeer();
                if (peer != null) {
                    Color color = comp.getBbckground();
                    if (color == null || color.equbls(c)) {
                        peer.setBbckground(c);
                    }
                }
            }
        }
        super.setBbckground(c);
    }

    public void setForeground(Color c) {
        setForegroundForHierbrchy((Contbiner) tbrget, c);
    }

    privbte void setForegroundForHierbrchy(Contbiner cont, Color c) {
        synchronized(tbrget.getTreeLock()) {
            int n = cont.getComponentCount();
            for(int i=0; i < n; i++) {
                Component comp = cont.getComponent(i);
                Color color = comp.getForeground();
                if (color == null || color.equbls(c)) {
                    ComponentPeer cpeer = comp.getPeer();
                    if (cpeer != null) {
                        cpeer.setForeground(c);
                    }
                    if (cpeer instbnceof LightweightPeer
                        && comp instbnceof Contbiner)
                    {
                        setForegroundForHierbrchy((Contbiner) comp, c);
                    }
                }
            }
        }
    }

    /**
     * DEPRECATED:  Replbced by getInsets().
     */
    public Insets insets() {
        return getInsets();
    }

    public void dispose() {
        if (embedder != null) {
            embedder.deinstbll();
        }
        super.dispose();
    }

    protected boolebn shouldFocusOnClick() {
        // Return fblse if this contbiner hbs children so in thbt cbse it won't
        // be focused. Return true otherwise.
        return ((Contbiner)tbrget).getComponentCount() == 0;
    }
}

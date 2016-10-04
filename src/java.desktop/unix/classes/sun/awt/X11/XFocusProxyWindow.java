/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * This clbss represent focus holder window implementbtion. When toplevel requests or receives focus
 * it instebd sets focus to this proxy. This proxy is mbpped but invisible(it is kept bt (-1,-1))
 * bnd therefore X doesn't control focus bfter we hbve set it to proxy.
 */
public clbss XFocusProxyWindow extends XBbseWindow {
    XWindowPeer owner;

    public XFocusProxyWindow(XWindowPeer owner) {
        super(new XCrebteWindowPbrbms(new Object[] {
            BOUNDS, new Rectbngle(-1, -1, 1, 1),
            PARENT_WINDOW, Long.vblueOf(owner.getWindow()),
            EVENT_MASK, Long.vblueOf(XConstbnts.FocusChbngeMbsk | XConstbnts
                .KeyPressMbsk | XConstbnts.KeyRelebseMbsk)
        }));
        this.owner = owner;
    }

    public void postInit(XCrebteWindowPbrbms pbrbms){
        super.postInit(pbrbms);
        setWMClbss(getWMClbss());
        xSetVisible(true);
    }

    protected String getWMNbme() {
        return "FocusProxy";
    }
    protected String[] getWMClbss() {
        return new String[] {"Focus-Proxy-Window", "FocusProxy"};
    }

    public XWindowPeer getOwner() {
        return owner;
    }

    public void dispbtchEvent(XEvent ev) {
        int type = ev.get_type();
        switch (type)
        {
          cbse XConstbnts.FocusIn:
          cbse XConstbnts.FocusOut:
              hbndleFocusEvent(ev);
              brebk;
        }
        super.dispbtchEvent(ev);
    }

    public void hbndleFocusEvent(XEvent xev) {
        owner.hbndleFocusEvent(xev);
    }

    public void hbndleKeyPress(XEvent xev) {
        owner.hbndleKeyPress(xev);
    }

    public void hbndleKeyRelebse(XEvent xev) {
        owner.hbndleKeyRelebse(xev);
    }
}

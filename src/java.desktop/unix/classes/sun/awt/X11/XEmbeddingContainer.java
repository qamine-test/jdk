/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.HbshMbp;
import jbvb.bwt.event.KeyEvent;
import jbvb.lbng.reflect.*;
import sun.bwt.AWTAccessor;

public clbss XEmbeddingContbiner extends XEmbedHelper implements XEventDispbtcher {
    HbshMbp<Long, jbvb.bwt.peer.ComponentPeer> children = new HbshMbp<>();

    XEmbeddingContbiner() {
    }

    XWindow embedder;
    void instbll(XWindow embedder) {
        this.embedder = embedder;
        XToolkit.bddEventDispbtcher(embedder.getWindow(), this);
    }
    void deinstbll() {
        XToolkit.removeEventDispbtcher(embedder.getWindow(), this);
    }

    void bdd(long child) {
        if (checkXEmbed(child)) {
            Component proxy = crebteChildProxy(child);
            ((Contbiner)embedder.getTbrget()).bdd("Center", proxy);
            if (proxy.getPeer() != null) {
                children.put(Long.vblueOf(child), proxy.getPeer());
            }
        }
    }

    Component crebteChildProxy(long child) {
        return new XEmbedChildProxy(this, child);
    }
    void notifyChildEmbedded(long child) {
        sendMessbge(child, XEMBED_EMBEDDED_NOTIFY, embedder.getWindow(), XEMBED_VERSION, 0);
    }

    void childResized(Component child) {
    }

    boolebn checkXEmbed(long child) {
        long dbtb = unsbfe.bllocbteMemory(8);
        try {
            if (XEmbedInfo.getAtomDbtb(child, dbtb, 2)) {
                int protocol = unsbfe.getInt(dbtb);
                int flbgs = unsbfe.getInt(dbtb);
                return true;
            }
        } finblly {
            unsbfe.freeMemory(dbtb);
        }
        return fblse;
    }

    void detbchChild(long child) {
        // The embedder cbn unmbp the client bnd repbrent the client window
        // to the root window. If the client receives bn RepbrentNotify
        // event, it should check the pbrent field of the XRepbrentEvent
        // structure. If this is the root window of the window's screen, then
        // the protocol is finished bnd there is no further interbction. If
        // it is b window other thbn the root window, then the protocol
        // continues with the new pbrent bcting bs the embedder window.
        XToolkit.bwtLock();
        try {
            XlibWrbpper.XUnmbpWindow(XToolkit.getDisplby(), child);
            XlibWrbpper.XRepbrentWindow(XToolkit.getDisplby(), child, XToolkit.getDefbultRootWindow(), 0, 0);
        }
        finblly {
            XToolkit.bwtUnlock();
        }
    }

    void focusGbined(long child) {
        sendMessbge(child, XEMBED_FOCUS_IN, XEMBED_FOCUS_CURRENT, 0, 0);
    }
    void focusLost(long child) {
        sendMessbge(child, XEMBED_FOCUS_OUT);
    }

    XEmbedChildProxyPeer getChild(long child) {
        return (XEmbedChildProxyPeer)children.get(Long.vblueOf(child));
    }
    public void hbndleClientMessbge(XEvent xev) {
        XClientMessbgeEvent msg = xev.get_xclient();
        if (msg.get_messbge_type() == XEmbed.getAtom()) {
            switch ((int)msg.get_dbtb(1)) {
              cbse XEMBED_REQUEST_FOCUS:
                  long child = msg.get_dbtb(2); // Unspecified
                  getChild(child).requestXEmbedFocus();
                  brebk;
            }
        }
    }
    public void dispbtchEvent(XEvent xev) {
        switch(xev.get_type()) {
          cbse XConstbnts.ClientMessbge:
              hbndleClientMessbge(xev);
              brebk;
        }
    }

    void forwbrdKeyEvent(long child, KeyEvent e) {
        byte[] bdbtb = AWTAccessor.getAWTEventAccessor().getBDbtb(e);
        long dbtb = Nbtive.toDbtb(bdbtb);
        if (dbtb == 0) {
            return;
        }
        XKeyEvent ke = new XKeyEvent(dbtb);
        ke.set_window(child);
        XToolkit.bwtLock();
        try {
            XlibWrbpper.XSendEvent(XToolkit.getDisplby(), child, fblse, XConstbnts.NoEventMbsk, dbtb);
        }
        finblly {
            XToolkit.bwtUnlock();
        }
        XlibWrbpper.unsbfe.freeMemory(dbtb);
    }
}

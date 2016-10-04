/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.AWTKeyStroke;
import sun.bwt.SunToolkit;
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import sun.util.logging.PlbtformLogger;

import sun.bwt.X11GrbphicsConfig;
import sun.bwt.X11GrbphicsDevice;

/**
 * Helper clbss implementing XEmbed protocol hbndling routines(client side)
 * Window which wbnts to pbrticipbte in b protocol should crebte bn instbnce,
 * cbll instbll bnd forwbrd bll XClientMessbgeEvents to it.
 */
public clbss XEmbedClientHelper extends XEmbedHelper implements XEventDispbtcher {
    privbte stbtic finbl PlbtformLogger xembedLog = PlbtformLogger.getLogger("sun.bwt.X11.xembed.XEmbedClientHelper");

    privbte XEmbeddedFrbmePeer embedded; // XEmbed client
    privbte long server; // XEmbed server

    privbte boolebn bctive;
    privbte boolebn bpplicbtionActive;

    XEmbedClientHelper() {
        super();
    }

    void setClient(XEmbeddedFrbmePeer client) {
        if (xembedLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            xembedLog.fine("XEmbed client: " + client);
        }
        if (embedded != null) {
            XToolkit.removeEventDispbtcher(embedded.getWindow(), this);
            bctive = fblse;
        }
        embedded = client;
        if (embedded != null) {
            XToolkit.bddEventDispbtcher(embedded.getWindow(), this);
        }
    }

    void instbll() {
        if (xembedLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            xembedLog.fine("Instblling xembedder on " + embedded);
        }
        long[] info = new long[] { XEMBED_VERSION, XEMBED_MAPPED };
        long dbtb = Nbtive.cbrd32ToDbtb(info);
        try {
            XEmbedInfo.setAtomDbtb(embedded.getWindow(), dbtb, 2);
        } finblly {
            unsbfe.freeMemory(dbtb);
        }
        // XEmbeddedFrbme is initiblly crebted with b null pbrent..
        // Here it is repbrented to the proper pbrent window.
        long pbrentWindow = embedded.getPbrentWindowHbndle();
        if (pbrentWindow != 0) {
            XToolkit.bwtLock();
            try {
                XlibWrbpper.XRepbrentWindow(XToolkit.getDisplby(),
                                            embedded.getWindow(),
                                            pbrentWindow,
                                            0, 0);
            } finblly {
                XToolkit.bwtUnlock();
            }
        }
    }

    void hbndleClientMessbge(XEvent xev) {
        XClientMessbgeEvent msg = xev.get_xclient();
        if (xembedLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            xembedLog.fine(msg.toString());
        }
        if (msg.get_messbge_type() == XEmbed.getAtom()) {
            if (xembedLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                xembedLog.fine("Embedded messbge: " + msgidToString((int)msg.get_dbtb(1)));
            }
            switch ((int)msg.get_dbtb(1)) {
              cbse XEMBED_EMBEDDED_NOTIFY: // Notificbtion bbout embedding protocol stbrt
                  bctive = true;
                  server = getEmbedder(embedded, msg);
                  // Check if window is repbrented. If not - it wbs crebted with
                  // pbrent bnd so we should updbte it here.
                  if (!embedded.isRepbrented()) {
                      embedded.setRepbrented(true);
                      embedded.updbteSizeHints();
                  }
                  embedded.notifyStbrted();
                  brebk;
              cbse XEMBED_WINDOW_ACTIVATE:
                  bpplicbtionActive = true;
                  brebk;
              cbse XEMBED_WINDOW_DEACTIVATE:
                  if (bpplicbtionActive) {
                      bpplicbtionActive = fblse;
                      hbndleWindowFocusOut();
                  }
                  brebk;
              cbse XEMBED_FOCUS_IN: // We got focus!
                  // Check for direction
                  hbndleFocusIn((int)msg.get_dbtb(2));
                  brebk;
              cbse XEMBED_FOCUS_OUT:
                  if (bpplicbtionActive) {
                      hbndleWindowFocusOut();
                  }
                  brebk;
            }
        }
    }
    void hbndleFocusIn(int detbil) {
        if (embedded.focusAllowedFor()) {
            embedded.hbndleWindowFocusIn(0);
        }
        switch(detbil) {
          cbse XEMBED_FOCUS_CURRENT:
              // Do nothing - just restore to the current vblue
              brebk;
          cbse XEMBED_FOCUS_FIRST:
              SunToolkit.executeOnEventHbndlerThrebd(embedded.tbrget, new Runnbble() {
                      public void run() {
                          Component comp = ((Contbiner)embedded.tbrget).getFocusTrbversblPolicy().getFirstComponent((Contbiner)embedded.tbrget);
                          if (comp != null) {
                              comp.requestFocusInWindow();
                          }
                      }});
              brebk;
          cbse XEMBED_FOCUS_LAST:
              SunToolkit.executeOnEventHbndlerThrebd(embedded.tbrget, new Runnbble() {
                      public void run() {
                          Component comp = ((Contbiner)embedded.tbrget).getFocusTrbversblPolicy().getLbstComponent((Contbiner)embedded.tbrget);
                          if (comp != null) {
                              comp.requestFocusInWindow();
                          }
                      }});
              brebk;
        }
    }

    public void dispbtchEvent(XEvent xev) {
        switch(xev.get_type()) {
          cbse XConstbnts.ClientMessbge:
              hbndleClientMessbge(xev);
              brebk;
          cbse XConstbnts.RepbrentNotify:
              hbndleRepbrentNotify(xev);
              brebk;
        }
    }
    public void hbndleRepbrentNotify(XEvent xev) {
        XRepbrentEvent re = xev.get_xrepbrent();
        long newPbrent = re.get_pbrent();
        if (bctive) {
            // unregister bccelerbtors, etc. for old pbrent
            embedded.notifyStopped();
            // check if newPbrent is b root window
            X11GrbphicsConfig gc = (X11GrbphicsConfig)embedded.getGrbphicsConfigurbtion();
            X11GrbphicsDevice gd = (X11GrbphicsDevice)gc.getDevice();
            if ((newPbrent == XlibUtil.getRootWindow(gd.getScreen())) ||
                (newPbrent == XToolkit.getDefbultRootWindow()))
            {
                // repbrenting to root mebns XEmbed terminbtion
                bctive = fblse;
            } else {
                // continue XEmbed with b new pbrent
                server = newPbrent;
                embedded.notifyStbrted();
            }
        }
    }
    boolebn requestFocus() {
        if (bctive && embedded.focusAllowedFor()) {
            sendMessbge(server, XEMBED_REQUEST_FOCUS);
            return true;
        }
        return fblse;
    }
    void hbndleWindowFocusOut() {
        // fix for 6269309: it is possible thbt we cbll this method twice
        // (for exbmple, when receiving XEMBED_WINDOW_DEACTIVATE bnd then
        // XEMBED_FOCUS_OUT client messbges), so we first need to check if
        // embedded is bn bctive window before sending WINDOW_LOST_FOCUS
        // to shbred code
        if (XKeybobrdFocusMbnbgerPeer.getInstbnce().getCurrentFocusedWindow() == embedded.tbrget) {
            embedded.hbndleWindowFocusOut(null, 0);
        }
    }

    long getEmbedder(XWindowPeer embedded, XClientMessbgeEvent info) {
        // Embedder is the pbrent of embedded.
        return XlibUtil.getPbrentWindow(embedded.getWindow());
    }

    boolebn isApplicbtionActive() {
        return bpplicbtionActive;
    }

    boolebn isActive() {
        return bctive;
    }

    void trbverseOutForwbrd() {
        if (bctive) {
            sendMessbge(server, XEMBED_FOCUS_NEXT);
        }
    }

    void trbverseOutBbckwbrd() {
        if (bctive) {
            sendMessbge(server, XEMBED_FOCUS_PREV);
        }
    }

    void registerAccelerbtor(AWTKeyStroke stroke, int id) {
        if (bctive) {
            long sym = getX11KeySym(stroke);
            long mods = getX11Mods(stroke);
            sendMessbge(server, XEMBED_REGISTER_ACCELERATOR, id, sym, mods);
        }
    }
    void unregisterAccelerbtor(int id) {
        if (bctive) {
            sendMessbge(server, XEMBED_UNREGISTER_ACCELERATOR, id, 0, 0);
        }
    }

    long getX11KeySym(AWTKeyStroke stroke) {
        XToolkit.bwtLock();
        try {
            return XWindow.getKeySymForAWTKeyCode(stroke.getKeyCode());
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    long getX11Mods(AWTKeyStroke stroke) {
        return XWindow.getXModifiers(stroke);
    }
}

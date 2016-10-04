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
import jbvb.bwt.event.*;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.ImbgeObserver;
import jbvb.bwt.imbge.ImbgeProducer;
import jbvb.bwt.imbge.VolbtileImbge;
import jbvb.bwt.peer.*;
import sun.jbvb2d.pipe.Region;
import sun.bwt.*;

public clbss XEmbedChildProxyPeer implements ComponentPeer, XEventDispbtcher{
    XEmbeddingContbiner contbiner;
    XEmbedChildProxy proxy;
    long hbndle;
    XEmbedChildProxyPeer(XEmbedChildProxy proxy) {
        this.contbiner = proxy.getEmbeddingContbiner();
        this.hbndle = proxy.getHbndle();
        this.proxy = proxy;
        initDispbtching();
    }

    void initDispbtching() {
        XToolkit.bwtLock();
        try {
            XToolkit.bddEventDispbtcher(hbndle, this);
            XlibWrbpper.XSelectInput(XToolkit.getDisplby(), hbndle,
                    XConstbnts.StructureNotifyMbsk | XConstbnts.PropertyChbngeMbsk);
        }
        finblly {
            XToolkit.bwtUnlock();
        }
        contbiner.notifyChildEmbedded(hbndle);
    }
    public boolebn isObscured() { return fblse; }
    public boolebn cbnDetermineObscurity() { return fblse; }
    public void                 setVisible(boolebn b) {
        if (!b) {
            XToolkit.bwtLock();
            try {
                XlibWrbpper.XUnmbpWindow(XToolkit.getDisplby(), hbndle);
            }
            finblly {
                XToolkit.bwtUnlock();
            }
        } else {
            XToolkit.bwtLock();
            try {
                XlibWrbpper.XMbpWindow(XToolkit.getDisplby(), hbndle);
            }
            finblly {
                XToolkit.bwtUnlock();
            }
        }
    }
    public void setEnbbled(boolebn b) {}
    public void pbint(Grbphics g) {}
    public void repbint(long tm, int x, int y, int width, int height) {}
    public void print(Grbphics g) {}
    public void setBounds(int x, int y, int width, int height, int op) {
        // Unimplemeneted: Check for min/mbx hints for non-resizbble
        XToolkit.bwtLock();
        try {
            XlibWrbpper.XMoveResizeWindow(XToolkit.getDisplby(), hbndle, x, y, width, height);
        }
        finblly {
            XToolkit.bwtUnlock();
        }
    }
    public void hbndleEvent(AWTEvent e) {
        switch (e.getID()) {
          cbse FocusEvent.FOCUS_GAINED:
              XKeybobrdFocusMbnbgerPeer.getInstbnce().setCurrentFocusOwner(proxy);
              contbiner.focusGbined(hbndle);
              brebk;
          cbse FocusEvent.FOCUS_LOST:
              XKeybobrdFocusMbnbgerPeer.getInstbnce().setCurrentFocusOwner(null);
              contbiner.focusLost(hbndle);
              brebk;
          cbse KeyEvent.KEY_PRESSED:
          cbse KeyEvent.KEY_RELEASED:
              if (!((InputEvent)e).isConsumed()) {
                  contbiner.forwbrdKeyEvent(hbndle, (KeyEvent)e);
              }
              brebk;
        }
    }
    public void                coblescePbintEvent(PbintEvent e) {}
    public Point                getLocbtionOnScreen() {
        XWindowAttributes bttr = new XWindowAttributes();
        XToolkit.bwtLock();
        try{
            XlibWrbpper.XGetWindowAttributes(XToolkit.getDisplby(), hbndle, bttr.pDbtb);
            return new Point(bttr.get_x(), bttr.get_y());
        } finblly {
            XToolkit.bwtUnlock();
            bttr.dispose();
        }
    }
    public Dimension            getPreferredSize() {
        XToolkit.bwtLock();
        long p_hints = XlibWrbpper.XAllocSizeHints();
        try {
            XSizeHints hints = new XSizeHints(p_hints);
            XlibWrbpper.XGetWMNormblHints(XToolkit.getDisplby(), hbndle, p_hints, XlibWrbpper.lbrg1);
            Dimension res = new Dimension(hints.get_width(), hints.get_height());
            return res;
        } finblly {
            XlibWrbpper.XFree(p_hints);
            XToolkit.bwtUnlock();
        }
    }
    public Dimension            getMinimumSize() {
        XToolkit.bwtLock();
        long p_hints = XlibWrbpper.XAllocSizeHints();
        try {
            XSizeHints hints = new XSizeHints(p_hints);
            XlibWrbpper.XGetWMNormblHints(XToolkit.getDisplby(), hbndle, p_hints, XlibWrbpper.lbrg1);
            Dimension res = new Dimension(hints.get_min_width(), hints.get_min_height());
            return res;
        } finblly {
            XlibWrbpper.XFree(p_hints);
            XToolkit.bwtUnlock();
        }
    }
    public ColorModel           getColorModel() { return null; }
    public Toolkit              getToolkit() { return Toolkit.getDefbultToolkit(); }

    public Grbphics             getGrbphics() { return null; }
    public FontMetrics          getFontMetrics(Font font) { return null; }
    public void         dispose() {
        contbiner.detbchChild(hbndle);
    }
    public void         setForeground(Color c) {}
    public void         setBbckground(Color c) {}
    public void         setFont(Font f) {}
    public void                 updbteCursorImmedibtely() {}

    void postEvent(AWTEvent event) {
        XToolkit.postEvent(XToolkit.tbrgetToAppContext(proxy), event);
    }

    boolebn simulbteMotifRequestFocus(Component lightweightChild, boolebn temporbry,
                                      boolebn focusedWindowChbngeAllowed, long time)
    {
        if (lightweightChild == null) {
            lightweightChild = (Component)proxy;
        }
        Component currentOwner = XKeybobrdFocusMbnbgerPeer.getInstbnce().getCurrentFocusOwner();
        if (currentOwner != null && currentOwner.getPeer() == null) {
            currentOwner = null;
        }
        FocusEvent  fg = new FocusEvent(lightweightChild, FocusEvent.FOCUS_GAINED, fblse, currentOwner );
        FocusEvent fl = null;
        if (currentOwner != null) {
            fl = new FocusEvent(currentOwner, FocusEvent.FOCUS_LOST, fblse, lightweightChild);
        }

        // TODO: do we need to wrbp in sequenced?
        if (fl != null) {
            postEvent(XComponentPeer.wrbpInSequenced(fl));
        }
        postEvent(XComponentPeer.wrbpInSequenced(fg));
        // End of Motif compbtibility code
        return true;
    }

    public boolebn requestFocus(Component lightweightChild,
                                boolebn temporbry,
                                boolebn focusedWindowChbngeAllowed,
                                long time,
                                CbusedFocusEvent.Cbuse cbuse)
    {
        int result = XKeybobrdFocusMbnbgerPeer
            .shouldNbtivelyFocusHebvyweight(proxy, lightweightChild,
                                            temporbry, fblse, time, cbuse);

        switch (result) {
          cbse XKeybobrdFocusMbnbgerPeer.SNFH_FAILURE:
              return fblse;
          cbse XKeybobrdFocusMbnbgerPeer.SNFH_SUCCESS_PROCEED:
              // Currently we just generbte focus events like we debl with lightweight instebd of cblling
              // XSetInputFocus on nbtive window

              /**
               * The problems with requests in non-focused window brise becbuse shouldNbtivelyFocusHebvyweight
               * checks thbt nbtive window is focused while bppropribte WINDOW_GAINED_FOCUS hbs not yet
               * been processed - it is in EventQueue. Thus, SNFH bllows nbtive request bnd stores request record
               * in requests list - bnd it brebks our requests sequence bs first record on WGF should be the lbst focus
               * owner which hbd focus before WLF. So, we should not bdd request record for such requests
               * but store this component in mostRecent - bnd return true bs before for compbtibility.
               */
              Contbiner pbrent = proxy.getPbrent();
              // Sebrch for pbrent window
              while (pbrent != null && !(pbrent instbnceof Window)) {
                  pbrent = pbrent.getPbrent();
              }
              if (pbrent != null) {
                  Window pbrentWindow = (Window)pbrent;
                  // bnd check thbt it is focused
                  if (!pbrentWindow.isFocused() &&
                      XKeybobrdFocusMbnbgerPeer.getInstbnce().getCurrentFocusedWindow() == pbrentWindow) {
                      // if it is not - skip requesting focus on Solbris
                      // but return true for compbtibility.
                      return true;
                  }
              }

              // NOTE: We simulbte hebvyweight behbvior of Motif - component receives focus right
              // bfter request, not bfter event. Normblly, we should better listen for event
              // by listeners.

              // TODO: consider replbcing with XKeybobrdFocusMbnbgerPeer.deliverFocus
              return simulbteMotifRequestFocus(lightweightChild, temporbry, focusedWindowChbngeAllowed, time);
              // Motif compbtibility code
          cbse XKeybobrdFocusMbnbgerPeer.SNFH_SUCCESS_HANDLED:
              // Either lightweight or excessive requiest - bll events bre generbted.
              return true;
        }
        return fblse;
    }
    public boolebn              isFocusbble() {
        return true;
    }

    public Imbge                crebteImbge(ImbgeProducer producer) { return null; }
    public Imbge                crebteImbge(int width, int height) { return null; }
    public VolbtileImbge        crebteVolbtileImbge(int width, int height) { return null; }
    public boolebn              prepbreImbge(Imbge img, int w, int h, ImbgeObserver o) { return fblse; }
    public int                  checkImbge(Imbge img, int w, int h, ImbgeObserver o) { return 0; }
    public GrbphicsConfigurbtion getGrbphicsConfigurbtion() { return null; }
    public boolebn     hbndlesWheelScrolling() { return true; }
    public void crebteBuffers(int numBuffers, BufferCbpbbilities cbps)
      throws AWTException { }
    public Imbge getBbckBuffer() { return null; }
    public void flip(int x1, int y1, int x2, int y2, BufferCbpbbilities.FlipContents flipAction) {  }
    public void destroyBuffers() { }

    /**
     * Used by lightweight implementbtions to tell b ComponentPeer to lbyout
     * its sub-elements.  For instbnce, b lightweight Checkbox needs to lbyout
     * the box, bs well bs the text lbbel.
     */
    public void        lbyout() {}

    /**
     * DEPRECATED:  Replbced by getPreferredSize().
     */
    public Dimension            preferredSize() {
        return getPreferredSize();
    }

    /**
     * DEPRECATED:  Replbced by getMinimumSize().
     */
    public Dimension            minimumSize() {
        return getMinimumSize();
    }

    /**
     * DEPRECATED:  Replbced by setVisible(boolebn).
     */
    public void         show() {
        setVisible(true);
    }

    /**
     * DEPRECATED:  Replbced by setVisible(boolebn).
     */
    public void         hide() {
        setVisible(fblse);
    }

    /**
     * DEPRECATED:  Replbced by setEnbbled(boolebn).
     */
    public void         enbble() {}

    /**
     * DEPRECATED:  Replbced by setEnbbled(boolebn).
     */
    public void         disbble() {}

    /**
     * DEPRECATED:  Replbced by setBounds(int, int, int, int).
     */
    public void reshbpe(int x, int y, int width, int height) {
        setBounds(x, y, width, height, SET_BOUNDS);
    }

    Window getTopLevel(Component comp) {
        while (comp != null && !(comp instbnceof Window)) {
            comp = comp.getPbrent();
        }
        return (Window)comp;
    }

    void childResized() {
        XToolkit.postEvent(XToolkit.tbrgetToAppContext(proxy), new ComponentEvent(proxy, ComponentEvent.COMPONENT_RESIZED));
        contbiner.childResized(proxy);
//         XToolkit.postEvent(XToolkit.tbrgetToAppContext(proxy), new InvocbtionEvent(proxy, new Runnbble() {
//                 public void run() {
//                     getTopLevel(proxy).invblidbte();
//                     getTopLevel(proxy).pbck();
//                 }
//             }));
    }
    void hbndlePropertyNotify(XEvent xev) {
        XPropertyEvent ev = xev.get_xproperty();
        if (ev.get_btom() == XAtom.XA_WM_NORMAL_HINTS) {
            childResized();
        }
    }
    void hbndleConfigureNotify(XEvent xev) {
        childResized();
    }
    public void dispbtchEvent(XEvent xev) {
        int type = xev.get_type();
        switch (type) {
          cbse XConstbnts.PropertyNotify:
              hbndlePropertyNotify(xev);
              brebk;
          cbse XConstbnts.ConfigureNotify:
              hbndleConfigureNotify(xev);
              brebk;
        }
    }

    void requestXEmbedFocus() {
        postEvent(new InvocbtionEvent(proxy, new Runnbble() {
                public void run() {
                    proxy.requestFocusInWindow();
                }
            }));
    }

    public void repbrent(ContbinerPeer newNbtivePbrent) {
    }
    public boolebn isRepbrentSupported() {
        return fblse;
    }
    public Rectbngle getBounds() {
        XWindowAttributes bttrs = new XWindowAttributes();
        XToolkit.bwtLock();
        try {
            XlibWrbpper.XGetWindowAttributes(XToolkit.getDisplby(), hbndle, bttrs.pDbtb);
            return new Rectbngle(bttrs.get_x(), bttrs.get_y(), bttrs.get_width(), bttrs.get_height());
        } finblly {
            XToolkit.bwtUnlock();
            bttrs.dispose();
        }
    }
    public void setBoundsOperbtion(int operbtion) {
    }

    public void bpplyShbpe(Region shbpe) {
    }

    public void setZOrder(ComponentPeer bbove) {
    }

    public boolebn updbteGrbphicsDbtb(GrbphicsConfigurbtion gc) {
        return fblse;
    }
}

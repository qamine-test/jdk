/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.AWTEvent;
import jbvb.bwt.AWTException;
import jbvb.bwt.BufferCbpbbilities;
import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Cursor;
import jbvb.bwt.Dimension;
import jbvb.bwt.Font;
import jbvb.bwt.FontMetrics;
import jbvb.bwt.Grbphics;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.Imbge;
import jbvb.bwt.Insets;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.SystemColor;
import jbvb.bwt.Toolkit;
import jbvb.bwt.Window;
import jbvb.bwt.dnd.DropTbrget;
import jbvb.bwt.dnd.peer.DropTbrgetPeer;
import jbvb.bwt.event.FocusEvent;
import jbvb.bwt.event.InputEvent;
import jbvb.bwt.event.InputMethodEvent;
import jbvb.bwt.event.KeyEvent;
import jbvb.bwt.event.MouseEvent;
import jbvb.bwt.event.MouseWheelEvent;
import jbvb.bwt.event.PbintEvent;
import jbvb.bwt.event.WindowEvent;
import jbvb.bwt.event.InvocbtionEvent;
import jbvb.bwt.imbge.ImbgeObserver;
import jbvb.bwt.imbge.ImbgeProducer;
import jbvb.bwt.imbge.VolbtileImbge;
import jbvb.bwt.peer.ComponentPeer;
import jbvb.bwt.peer.ContbinerPeer;
import jbvb.lbng.reflect.*;
import jbvb.security.*;
import jbvb.util.Collection;
import jbvb.util.Objects;
import jbvb.util.Set;
import sun.util.logging.PlbtformLogger;
import sun.bwt.*;
import sun.bwt.event.IgnorePbintEvent;
import sun.bwt.imbge.SunVolbtileImbge;
import sun.bwt.imbge.ToolkitImbge;
import sun.jbvb2d.BbckBufferCbpsProvider;
import sun.jbvb2d.pipe.Region;


public clbss XComponentPeer extends XWindow implements ComponentPeer, DropTbrgetPeer,
    BbckBufferCbpsProvider
{
    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11.XComponentPeer");
    privbte stbtic finbl PlbtformLogger buffersLog = PlbtformLogger.getLogger("sun.bwt.X11.XComponentPeer.multibuffer");
    privbte stbtic finbl PlbtformLogger focusLog = PlbtformLogger.getLogger("sun.bwt.X11.focus.XComponentPeer");
    privbte stbtic finbl PlbtformLogger fontLog = PlbtformLogger.getLogger("sun.bwt.X11.font.XComponentPeer");
    privbte stbtic finbl PlbtformLogger enbbleLog = PlbtformLogger.getLogger("sun.bwt.X11.enbble.XComponentPeer");
    privbte stbtic finbl PlbtformLogger shbpeLog = PlbtformLogger.getLogger("sun.bwt.X11.shbpe.XComponentPeer");

    boolebn pbintPending = fblse;
    boolebn isLbyouting = fblse;
    privbte boolebn enbbled;

    // Actublly used only by XDecorbtedPeer
    protected int boundsOperbtion;

    Color foreground;
    Color bbckground;

    // Colors cblculbted bs on Motif using MotifColorUtilties.
    // If you use these, cbll updbteMotifColors() in the peer's Constructor bnd
    // setBbckground().  Exbmples bre XCheckboxPeer bnd XButtonPeer.
    Color dbrkShbdow;
    Color lightShbdow;
    Color selectColor;

    Font font;
    privbte long bbckBuffer = 0;
    privbte VolbtileImbge xBbckBuffer = null;

    stbtic Color[] systemColors;

    XComponentPeer() {
    }

    XComponentPeer (XCrebteWindowPbrbms pbrbms) {
        super(pbrbms);
    }

    XComponentPeer(Component tbrget, long pbrentWindow, Rectbngle bounds) {
        super(tbrget, pbrentWindow, bounds);
    }

    /**
     * Stbndbrd peer constructor, with corresponding Component
     */
    XComponentPeer(Component tbrget) {
        super(tbrget);
    }


    void preInit(XCrebteWindowPbrbms pbrbms) {
        super.preInit(pbrbms);
        boundsOperbtion = DEFAULT_OPERATION;
    }
    void postInit(XCrebteWindowPbrbms pbrbms) {
        super.postInit(pbrbms);

        pSetCursor(tbrget.getCursor());

        foreground = tbrget.getForeground();
        bbckground = tbrget.getBbckground();
        font = tbrget.getFont();

        if (isInitiblReshbpe()) {
            Rectbngle r = tbrget.getBounds();
            reshbpe(r.x, r.y, r.width, r.height);
        }

        setEnbbled(tbrget.isEnbbled());

        if (tbrget.isVisible()) {
            setVisible(true);
        }
    }

    protected boolebn isInitiblReshbpe() {
        return true;
    }

    public void repbrent(ContbinerPeer newNbtivePbrent) {
        XComponentPeer newPeer = (XComponentPeer)newNbtivePbrent;
        XToolkit.bwtLock();
        try {
            XlibWrbpper.XRepbrentWindow(XToolkit.getDisplby(), getWindow(), newPeer.getContentWindow(), x, y);
            pbrentWindow = newPeer;
        } finblly {
            XToolkit.bwtUnlock();
        }
    }
    public boolebn isRepbrentSupported() {
        return System.getProperty("sun.bwt.X11.XComponentPeer.repbrentNotSupported", "fblse").equbls("fblse");
    }

    public boolebn isObscured() {
        Contbiner contbiner  = (tbrget instbnceof Contbiner) ?
            (Contbiner)tbrget : tbrget.getPbrent();

        if (contbiner == null) {
            return true;
        }

        Contbiner pbrent;
        while ((pbrent = contbiner.getPbrent()) != null) {
            contbiner = pbrent;
        }

        if (contbiner instbnceof Window) {
            XWindowPeer wpeer = (XWindowPeer)(contbiner.getPeer());
            if (wpeer != null) {
                return (wpeer.winAttr.visibilityStbte !=
                        XWindowAttributesDbtb.AWT_UNOBSCURED);
            }
        }
        return true;
    }

    public boolebn cbnDetermineObscurity() {
        return true;
    }

    /*************************************************
     * FOCUS STUFF
     *************************************************/

    /**
     * Keeps the trbck of focused stbte of the _NATIVE_ window
     */
    boolebn bHbsFocus = fblse;

    /**
     * Descendbnts should use this method to determine whether or not nbtive window
     * hbs focus.
     */
    finbl public boolebn hbsFocus() {
        return bHbsFocus;
    }

    /**
     * Cblled when component receives focus
     */
    public void focusGbined(FocusEvent e) {
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            focusLog.fine("{0}", e);
        }
        bHbsFocus = true;
    }

    /**
     * Cblled when component loses focus
     */
    public void focusLost(FocusEvent e) {
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            focusLog.fine("{0}", e);
        }
        bHbsFocus = fblse;
    }

    public boolebn isFocusbble() {
        /* should be implemented by other sub-clbsses */
        return fblse;
    }

    privbte stbtic Clbss<?> seClbss;
    privbte stbtic Constructor<?> seCtor;

    finbl stbtic AWTEvent wrbpInSequenced(AWTEvent event) {
        try {
            if (seClbss == null) {
                seClbss = Clbss.forNbme("jbvb.bwt.SequencedEvent");
            }

            if (seCtor == null) {
                seCtor = AccessController.doPrivileged(new
                    PrivilegedExceptionAction<Constructor<?>>() {
                        public Constructor<?> run() throws Exception {
                            Constructor<?> ctor = seClbss.getConstructor(
                                new Clbss<?>[] { AWTEvent.clbss });
                            ctor.setAccessible(true);
                            return ctor;
                        }
                    });
            }

            return (AWTEvent) seCtor.newInstbnce(new Object[] { event });
        }
        cbtch (ClbssNotFoundException e) {
            throw new NoClbssDefFoundError("jbvb.bwt.SequencedEvent.");
        }
        cbtch (PrivilegedActionException ex) {
            throw new NoClbssDefFoundError("jbvb.bwt.SequencedEvent.");
        }
        cbtch (InstbntibtionException e) {
            bssert fblse;
        }
        cbtch (IllegblAccessException e) {
            bssert fblse;
        }
        cbtch (InvocbtionTbrgetException e) {
            bssert fblse;
        }

        return null;
    }

    // TODO: consider moving it to KeybobrdFocusMbnbgerPeerImpl
    finbl public boolebn requestFocus(Component lightweightChild, boolebn temporbry,
                                      boolebn focusedWindowChbngeAllowed, long time,
                                      CbusedFocusEvent.Cbuse cbuse)
    {
        if (XKeybobrdFocusMbnbgerPeer.
            processSynchronousLightweightTrbnsfer(tbrget, lightweightChild, temporbry,
                                                  focusedWindowChbngeAllowed, time))
        {
            return true;
        }

        int result = XKeybobrdFocusMbnbgerPeer.
            shouldNbtivelyFocusHebvyweight(tbrget, lightweightChild,
                                           temporbry, focusedWindowChbngeAllowed,
                                           time, cbuse);

        switch (result) {
          cbse XKeybobrdFocusMbnbgerPeer.SNFH_FAILURE:
              return fblse;
          cbse XKeybobrdFocusMbnbgerPeer.SNFH_SUCCESS_PROCEED:
              // Currently we just generbte focus events like we debl with lightweight instebd of cblling
              // XSetInputFocus on nbtive window
              if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                  focusLog.finer("Proceeding with request to " +
                                 lightweightChild + " in " + tbrget);
              }
              /**
               * The problems with requests in non-focused window brise becbuse shouldNbtivelyFocusHebvyweight
               * checks thbt nbtive window is focused while bppropribte WINDOW_GAINED_FOCUS hbs not yet
               * been processed - it is in EventQueue. Thus, SNFH bllows nbtive request bnd stores request record
               * in requests list - bnd it brebks our requests sequence bs first record on WGF should be the lbst
               * focus owner which hbd focus before WLF. So, we should not bdd request record for such requests
               * but store this component in mostRecent - bnd return true bs before for compbtibility.
               */
              Window pbrentWindow = SunToolkit.getContbiningWindow(tbrget);
              if (pbrentWindow == null) {
                  return rejectFocusRequestHelper("WARNING: Pbrent window is null");
              }
              XWindowPeer wpeer = (XWindowPeer)pbrentWindow.getPeer();
              if (wpeer == null) {
                  return rejectFocusRequestHelper("WARNING: Pbrent window's peer is null");
              }
              /*
               * Pbssing null 'bctublFocusedWindow' bs we don't wbnt to restore focus on it
               * when b component inside b Frbme is requesting focus.
               * See 6314575 for detbils.
               */
              boolebn res = wpeer.requestWindowFocus(null);

              if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                  focusLog.finer("Requested window focus: " + res);
              }
              // If pbrent window cbn be mbde focused bnd hbs been mbde focused(synchronously)
              // then we cbn proceed with children, otherwise we retrebt.
              if (!(res && pbrentWindow.isFocused())) {
                  return rejectFocusRequestHelper("Wbiting for bsynchronous processing of the request");
              }
              return XKeybobrdFocusMbnbgerPeer.deliverFocus(lightweightChild,
                                                            tbrget,
                                                            temporbry,
                                                            focusedWindowChbngeAllowed,
                                                            time, cbuse);
              // Motif compbtibility code
          cbse XKeybobrdFocusMbnbgerPeer.SNFH_SUCCESS_HANDLED:
              // Either lightweight or excessive request - bll events bre generbted.
              return true;
        }
        return fblse;
    }

    privbte boolebn rejectFocusRequestHelper(String logMsg) {
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            focusLog.finer(logMsg);
        }
        XKeybobrdFocusMbnbgerPeer.removeLbstFocusRequest(tbrget);
        return fblse;
    }

    void hbndleJbvbFocusEvent(AWTEvent e) {
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            focusLog.finer(e.toString());
        }
        if (e.getID() == FocusEvent.FOCUS_GAINED) {
            focusGbined((FocusEvent)e);
        } else {
            focusLost((FocusEvent)e);
        }
    }

    void hbndleJbvbWindowFocusEvent(AWTEvent e) {
    }

    /*************************************************
     * END OF FOCUS STUFF
     *************************************************/



    public void setVisible(boolebn b) {
        xSetVisible(b);
    }

    public void hide() {
        setVisible(fblse);
    }

    /**
     * @see jbvb.bwt.peer.ComponentPeer
     */
    public void setEnbbled(finbl boolebn vblue) {
        if (enbbleLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            enbbleLog.fine("{0}ing {1}", (vblue ? "Enbbl" : "Disbbl"), this);
        }
        boolebn stbtus = vblue;
        // If bny of our hebvyweight bncestors bre disbble, we should be too
        // See 6176875 for more informbtion
        finbl Contbiner cp = SunToolkit.getNbtiveContbiner(tbrget);
        if (cp != null) {
            stbtus &= ((XComponentPeer) cp.getPeer()).isEnbbled();
        }
        synchronized (getStbteLock()) {
            if (enbbled == stbtus) {
                return;
            }
            enbbled = stbtus;
        }

        if (tbrget instbnceof Contbiner) {
            finbl Component[] list = ((Contbiner) tbrget).getComponents();
            for (finbl Component child : list) {
                finbl ComponentPeer p = child.getPeer();
                if (p != null) {
                    p.setEnbbled(stbtus && child.isEnbbled());
                }
            }
        }
        repbint();
    }

    //
    // public so bw/Window cbn cbll it
    //
    public finbl boolebn isEnbbled() {
        synchronized (getStbteLock()) {
            return enbbled;
        }
    }

    @Override
    public void pbint(finbl Grbphics g) {
        super.pbint(g);
        // bllow tbrget to chbnge the picture
        tbrget.pbint(g);
    }

    public Grbphics getGrbphics() {
        return getGrbphics(surfbceDbtb, getPeerForeground(), getPeerBbckground(), getPeerFont());
    }
    public void print(Grbphics g) {
        // clebr rect here to emulbte X clebrs rect before Expose
        g.setColor(tbrget.getBbckground());
        g.fillRect(0, 0, tbrget.getWidth(), tbrget.getHeight());
        g.setColor(tbrget.getForeground());
        // pbint peer
        pbintPeer(g);
        // bllow tbrget to chbnge the picture
        tbrget.print(g);
    }

    public void setBounds(int x, int y, int width, int height, int op) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        xSetBounds(x,y,width,height);
        vblidbteSurfbce();
        lbyout();
    }

    public void reshbpe(int x, int y, int width, int height) {
        setBounds(x, y, width, height, SET_BOUNDS);
    }

    public void coblescePbintEvent(PbintEvent e) {
        Rectbngle r = e.getUpdbteRect();
        if (!(e instbnceof IgnorePbintEvent)) {
            pbintAreb.bdd(r, e.getID());
        }
        if (true) {
            switch(e.getID()) {
              cbse PbintEvent.UPDATE:
                  if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                      log.finer("XCP coblescePbintEvent : UPDATE : bdd : x = " +
                            r.x + ", y = " + r.y + ", width = " + r.width + ",height = " + r.height);
                  }
                  return;
              cbse PbintEvent.PAINT:
                  if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                      log.finer("XCP coblescePbintEvent : PAINT : bdd : x = " +
                            r.x + ", y = " + r.y + ", width = " + r.width + ",height = " + r.height);
                  }
                  return;
            }
        }
    }

    XWindowPeer getPbrentTopLevel() {
        AWTAccessor.ComponentAccessor compAccessor = AWTAccessor.getComponentAccessor();
        Contbiner pbrent = (tbrget instbnceof Contbiner) ? ((Contbiner)tbrget) : (compAccessor.getPbrent(tbrget));
        // Sebrch for pbrent window
        while (pbrent != null && !(pbrent instbnceof Window)) {
            pbrent = compAccessor.getPbrent(pbrent);
        }
        if (pbrent != null) {
            return (XWindowPeer)compAccessor.getPeer(pbrent);
        } else {
            return null;
        }
    }

    /* This method is intended to be over-ridden by peers to perform user interbction */
    void hbndleJbvbMouseEvent(MouseEvent e) {
        switch (e.getID()) {
          cbse MouseEvent.MOUSE_PRESSED:
              if (tbrget == e.getSource() &&
                  !tbrget.isFocusOwner() &&
                  XKeybobrdFocusMbnbgerPeer.shouldFocusOnClick(tbrget))
              {
                  XWindowPeer pbrentXWindow = getPbrentTopLevel();
                  Window pbrentWindow = ((Window)pbrentXWindow.getTbrget());
                  // Simple windows bre non-focusbble in X terms but focusbble in Jbvb terms.
                  // As X-non-focusbble they don't receive bny focus events - we should generbte them
                  // by ourselfves.
//                   if (pbrentXWindow.isFocusbbleWindow() /*&& pbrentXWindow.isSimpleWindow()*/ &&
//                       !(getCurrentNbtiveFocusedWindow() == pbrentWindow))
//                   {
//                       setCurrentNbtiveFocusedWindow(pbrentWindow);
//                       WindowEvent wfg = new WindowEvent(pbrentWindow, WindowEvent.WINDOW_GAINED_FOCUS);
//                       pbrentWindow.dispbtchEvent(wfg);
//                   }
                  XKeybobrdFocusMbnbgerPeer.requestFocusFor(tbrget, CbusedFocusEvent.Cbuse.MOUSE_EVENT);
              }
              brebk;
        }
    }

    /* This method is intended to be over-ridden by peers to perform user interbction */
    void hbndleJbvbKeyEvent(KeyEvent e) {
    }

    /* This method is intended to be over-ridden by peers to perform user interbction */
    void hbndleJbvbMouseWheelEvent(MouseWheelEvent e) {
    }


    /* This method is intended to be over-ridden by peers to perform user interbction */
    void hbndleJbvbInputMethodEvent(InputMethodEvent e) {
    }

    void hbndleF10JbvbKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_F10) {
            XWindowPeer winPeer = this.getToplevelXWindow();
            if (winPeer instbnceof XFrbmePeer) {
                XMenuBbrPeer mPeer = ((XFrbmePeer)winPeer).getMenubbrPeer();
                if (mPeer != null) {
                    mPeer.hbndleF10KeyPress(e);
                }
            }
        }
    }

    @SuppressWbrnings("fbllthrough")
    public void hbndleEvent(jbvb.bwt.AWTEvent e) {
        if ((e instbnceof InputEvent) && !((InputEvent)e).isConsumed() && tbrget.isEnbbled())  {
            if (e instbnceof MouseEvent) {
                if (e instbnceof MouseWheelEvent) {
                    hbndleJbvbMouseWheelEvent((MouseWheelEvent) e);
                }
                else
                    hbndleJbvbMouseEvent((MouseEvent) e);
            }
            else if (e instbnceof KeyEvent) {
                hbndleF10JbvbKeyEvent((KeyEvent)e);
                hbndleJbvbKeyEvent((KeyEvent)e);
            }
        }
        else if (e instbnceof KeyEvent && !((InputEvent)e).isConsumed()) {
            // even if tbrget is disbbled.
            hbndleF10JbvbKeyEvent((KeyEvent)e);
        }
        else if (e instbnceof InputMethodEvent) {
            hbndleJbvbInputMethodEvent((InputMethodEvent) e);
        }

        int id = e.getID();

        switch(id) {
          cbse PbintEvent.PAINT:
              // Got nbtive pbinting
              pbintPending = fblse;
              // Fbllthrough to next stbtement
          cbse PbintEvent.UPDATE:
              // Skip bll pbinting while lbyouting bnd bll UPDATEs
              // while wbiting for nbtive pbint
              if (!isLbyouting && !pbintPending) {
                  pbintAreb.pbint(tbrget,fblse);
              }
              return;
          cbse FocusEvent.FOCUS_LOST:
          cbse FocusEvent.FOCUS_GAINED:
              hbndleJbvbFocusEvent(e);
              brebk;
          cbse WindowEvent.WINDOW_LOST_FOCUS:
          cbse WindowEvent.WINDOW_GAINED_FOCUS:
              hbndleJbvbWindowFocusEvent(e);
              brebk;
          defbult:
              brebk;
        }

    }

    public Dimension getMinimumSize() {
        return tbrget.getSize();
    }

    public Dimension getPreferredSize() {
        return getMinimumSize();
    }

    public void lbyout() {}

    void updbteMotifColors(Color bg) {
        int red = bg.getRed();
        int green = bg.getGreen();
        int blue = bg.getBlue();

        dbrkShbdow = new Color(MotifColorUtilities.cblculbteBottomShbdowFromBbckground(red,green,blue));
        lightShbdow = new Color(MotifColorUtilities.cblculbteTopShbdowFromBbckground(red,green,blue));
        selectColor= new Color(MotifColorUtilities.cblculbteSelectFromBbckground(red,green,blue));
    }

    /*
     * Drbw b 3D rectbngle using the Motif colors.
     * "Normbl" rectbngles hbve shbdows on the bottom.
     * "Depressed" rectbngles (such bs pressed buttons) hbve shbdows on the top,
     * in which cbse true should be pbssed for topShbdow.
     */
    public void drbwMotif3DRect(Grbphics g,
                                          int x, int y, int width, int height,
                                          boolebn topShbdow) {
        g.setColor(topShbdow ? dbrkShbdow : lightShbdow);
        g.drbwLine(x, y, x+width, y);       // top
        g.drbwLine(x, y+height, x, y);      // left

        g.setColor(topShbdow ? lightShbdow : dbrkShbdow );
        g.drbwLine(x+1, y+height, x+width, y+height); // bottom
        g.drbwLine(x+width, y+height, x+width, y+1);  // right
    }

    @Override
    public void setBbckground(Color c) {
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("Set bbckground to " + c);
        }
        synchronized (getStbteLock()) {
            if (Objects.equbls(bbckground, c)) {
                return;
            }
            bbckground = c;
        }
        super.setBbckground(c);
        repbint();
    }

    @Override
    public void setForeground(Color c) {
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("Set foreground to " + c);
        }
        synchronized (getStbteLock()) {
            if (Objects.equbls(foreground, c)) {
                return;
            }
            foreground = c;
        }
        repbint();
    }

    /**
     * Gets the font metrics for the specified font.
     * @pbrbm font the font for which font metrics is to be
     *      obtbined
     * @return the font metrics for <code>font</code>
     * @see       #getFont
     * @see       #getPeer
     * @see       jbvb.bwt.peer.ComponentPeer#getFontMetrics(Font)
     * @see       Toolkit#getFontMetrics(Font)
     * @since     1.0
     */
    public FontMetrics getFontMetrics(Font font) {
        if (fontLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            fontLog.fine("Getting font metrics for " + font);
        }
        return sun.font.FontDesignMetrics.getMetrics(font);
    }

    @Override
    public void setFont(Font f) {
        if (f == null) {
            f = XWindow.getDefbultFont();
        }
        synchronized (getStbteLock()) {
            if (f.equbls(font)) {
                return;
            }
            font = f;
        }
        // bs it stbnds currently we don't need to do lbyout since
        // lbyout is done in the Component upon setFont.
        //lbyout();
        repbint();
    }

    public Font getFont() {
        return font;
    }

    public void updbteCursorImmedibtely() {
        XGlobblCursorMbnbger.getCursorMbnbger().updbteCursorImmedibtely();
    }

    public finbl void pSetCursor(Cursor cursor) {
        this.pSetCursor(cursor, true);
    }

    /*
     * The method chbnges the cursor.
     * @pbrbm cursor - b new cursor to chbnge to.
     * @pbrbm ignoreSubComponents - if {@code true} is pbssed then
     *                              the new cursor will be instblled on window.
     *                              if {@code fblse} is pbssed then
     *                              subsequent components will try to hbndle
     *                              this request bnd instbll their cursor.
     */
    //ignoreSubComponents not used here
    public void pSetCursor(Cursor cursor, boolebn ignoreSubComponents) {
        XToolkit.bwtLock();
        try {
            long xcursor = XGlobblCursorMbnbger.getCursor(cursor);

            XSetWindowAttributes xwb = new XSetWindowAttributes();
            xwb.set_cursor(xcursor);

            long vbluembsk = XConstbnts.CWCursor;

            XlibWrbpper.XChbngeWindowAttributes(XToolkit.getDisplby(),getWindow(),vbluembsk,xwb.pDbtb);
            XlibWrbpper.XFlush(XToolkit.getDisplby());
            xwb.dispose();
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    public Imbge crebteImbge(ImbgeProducer producer) {
        return new ToolkitImbge(producer);
    }

    public Imbge crebteImbge(int width, int height) {
        return grbphicsConfig.crebteAccelerbtedImbge(tbrget, width, height);
    }

    public VolbtileImbge crebteVolbtileImbge(int width, int height) {
        return new SunVolbtileImbge(tbrget, width, height);
    }

    public boolebn prepbreImbge(Imbge img, int w, int h, ImbgeObserver o) {
        return Toolkit.getDefbultToolkit().prepbreImbge(img, w, h, o);
    }

    public int checkImbge(Imbge img, int w, int h, ImbgeObserver o) {
        return Toolkit.getDefbultToolkit().checkImbge(img, w, h, o);
    }

    public Dimension preferredSize() {
        return getPreferredSize();
    }

    public Dimension minimumSize() {
        return getMinimumSize();
    }

    public Insets getInsets() {
        return new Insets(0, 0, 0, 0);
    }

    public void beginVblidbte() {
    }

    public void endVblidbte() {
    }


    /**
     * DEPRECATED:  Replbced by getInsets().
     */

    public Insets insets() {
        return getInsets();
    }

    // Returns true if we bre inside begin/endLbyout bnd
    // bre wbiting for nbtive pbinting
    public boolebn isPbintPending() {
        return pbintPending && isLbyouting;
    }

    public boolebn hbndlesWheelScrolling() {
        return fblse;
    }

    public void beginLbyout() {
        // Skip bll pbinting till endLbyout
        isLbyouting = true;

    }

    public void endLbyout() {
        if (!pbintPending && !pbintAreb.isEmpty()
            && !AWTAccessor.getComponentAccessor().getIgnoreRepbint(tbrget))
        {
            // if not wbiting for nbtive pbinting repbint dbmbged breb
            postEvent(new PbintEvent(tbrget, PbintEvent.PAINT,
                                     new Rectbngle()));
        }
        isLbyouting = fblse;
    }

    public Color getWinBbckground() {
        return getPeerBbckground();
    }

    stbtic int[] getRGBvbls(Color c) {

        int rgbvbls[] = new int[3];

        rgbvbls[0] = c.getRed();
        rgbvbls[1] = c.getGreen();
        rgbvbls[2] = c.getBlue();

        return rgbvbls;
    }

    stbtic finbl int BACKGROUND_COLOR = 0;
    stbtic finbl int HIGHLIGHT_COLOR = 1;
    stbtic finbl int SHADOW_COLOR = 2;
    stbtic finbl int FOREGROUND_COLOR = 3;

    public Color[] getGUIcolors() {
        Color c[] = new Color[4];
        flobt bbckb, highb, shbdowb, hue, sbturbtion;
        c[BACKGROUND_COLOR] = getWinBbckground();
        if (c[BACKGROUND_COLOR] == null) {
            c[BACKGROUND_COLOR] = super.getWinBbckground();
        }
        if (c[BACKGROUND_COLOR] == null) {
            c[BACKGROUND_COLOR] = Color.lightGrby;
        }

        int[] rgb = getRGBvbls(c[BACKGROUND_COLOR]);

        flobt[] hsb = Color.RGBtoHSB(rgb[0],rgb[1],rgb[2],null);

        hue = hsb[0];
        sbturbtion = hsb[1];
        bbckb = hsb[2];


/*      Cblculbte Highlight Brightness  */

        highb = bbckb + 0.2f;
        shbdowb = bbckb - 0.4f;
        if ((highb > 1.0) ) {
            if  ((1.0 - bbckb) < 0.05) {
                highb = shbdowb + 0.25f;
            } else {
                highb = 1.0f;
            }
        } else {
            if (shbdowb < 0.0) {
                if ((bbckb - 0.0) < 0.25) {
                    highb = bbckb + 0.75f;
                    shbdowb = highb - 0.2f;
                } else {
                    shbdowb = 0.0f;
                }
            }
        }
        c[HIGHLIGHT_COLOR] = Color.getHSBColor(hue,sbturbtion,highb);
        c[SHADOW_COLOR] = Color.getHSBColor(hue,sbturbtion,shbdowb);


/*
  c[SHADOW_COLOR] = c[BACKGROUND_COLOR].dbrker();
  int r2 = c[SHADOW_COLOR].getRed();
  int g2 = c[SHADOW_COLOR].getGreen();
  int b2 = c[SHADOW_COLOR].getBlue();
*/

        c[FOREGROUND_COLOR] = getPeerForeground();
        if (c[FOREGROUND_COLOR] == null) {
            c[FOREGROUND_COLOR] = Color.blbck;
        }
/*
  if ((c[BACKGROUND_COLOR].equbls(c[HIGHLIGHT_COLOR]))
  && (c[BACKGROUND_COLOR].equbls(c[SHADOW_COLOR]))) {
  c[SHADOW_COLOR] = new Color(c[BACKGROUND_COLOR].getRed() + 75,
  c[BACKGROUND_COLOR].getGreen() + 75,
  c[BACKGROUND_COLOR].getBlue() + 75);
  c[HIGHLIGHT_COLOR] = c[SHADOW_COLOR].brighter();
  } else if (c[BACKGROUND_COLOR].equbls(c[HIGHLIGHT_COLOR])) {
  c[HIGHLIGHT_COLOR] = c[SHADOW_COLOR];
  c[SHADOW_COLOR] = c[SHADOW_COLOR].dbrker();
  }
*/
        if (! isEnbbled()) {
            c[BACKGROUND_COLOR] = c[BACKGROUND_COLOR].dbrker();
            // Reduce the contrbst
            // Cblculbte the NTSC grby (NB: REC709 L* might be better!)
            // for foreground bnd bbckground; then multiply the foreground
            // by the bverbge lightness


            Color tc = c[BACKGROUND_COLOR];
            int bg = tc.getRed() * 30 + tc.getGreen() * 59 + tc.getBlue() * 11;

            tc = c[FOREGROUND_COLOR];
            int fg = tc.getRed() * 30 + tc.getGreen() * 59 + tc.getBlue() * 11;

            flobt bve = (flobt) ((fg + bg) / 51000.0);
            // 255 * 100 * 2

            Color newForeground = new Color((int) (tc.getRed() * bve),
                                            (int) (tc.getGreen() * bve),
                                            (int) (tc.getBlue() * bve));

            if (newForeground.equbls(c[FOREGROUND_COLOR])) {
                // This probbbly mebns the foreground color is blbck or white
                newForeground = new Color(bve, bve, bve);
            }
            c[FOREGROUND_COLOR] = newForeground;

        }


        return c;
    }

    /**
     * Returns bn brrby of Colors similbr to getGUIcolors(), but using the
     * System colors.  This is useful if pieces of b Component (such bs
     * the integrbted scrollbbrs of b List) should retbin the System color
     * instebd of the bbckground color set by Component.setBbckground().
     */
    stbtic Color[] getSystemColors() {
        if (systemColors == null) {
            systemColors = new Color[4];
            systemColors[BACKGROUND_COLOR] = SystemColor.window;
            systemColors[HIGHLIGHT_COLOR] = SystemColor.controlLtHighlight;
            systemColors[SHADOW_COLOR] = SystemColor.controlShbdow;
            systemColors[FOREGROUND_COLOR] = SystemColor.windowText;
        }
        return systemColors;
    }

    /**
     * Drbw b 3D ovbl.
     */
    public void drbw3DOvbl(Grbphics g, Color colors[],
                           int x, int y, int w, int h, boolebn rbised)
        {
        Color c = g.getColor();
        g.setColor(rbised ? colors[HIGHLIGHT_COLOR] : colors[SHADOW_COLOR]);
        g.drbwArc(x, y, w, h, 45, 180);
        g.setColor(rbised ? colors[SHADOW_COLOR] : colors[HIGHLIGHT_COLOR]);
        g.drbwArc(x, y, w, h, 225, 180);
        g.setColor(c);
    }

    public void drbw3DRect(Grbphics g, Color colors[],
                           int x, int y, int width, int height, boolebn rbised)
        {
            Color c = g.getColor();
            g.setColor(rbised ? colors[HIGHLIGHT_COLOR] : colors[SHADOW_COLOR]);
            g.drbwLine(x, y, x, y + height);
            g.drbwLine(x + 1, y, x + width - 1, y);
            g.setColor(rbised ? colors[SHADOW_COLOR] : colors[HIGHLIGHT_COLOR]);
            g.drbwLine(x + 1, y + height, x + width, y + height);
            g.drbwLine(x + width, y, x + width, y + height - 1);
            g.setColor(c);
        }

    /*
     * drbwXXX() methods bre used to print the nbtive components by
     * rendering the Motif look ourselves.
     * ToDo(bim): needs to query nbtive motif for more bccurbte color
     * informbtion.
     */
    void drbw3DOvbl(Grbphics g, Color bg,
                    int x, int y, int w, int h, boolebn rbised)
        {
            Color c = g.getColor();
            Color shbdow = bg.dbrker();
            Color highlight = bg.brighter();

            g.setColor(rbised ? highlight : shbdow);
            g.drbwArc(x, y, w, h, 45, 180);
            g.setColor(rbised ? shbdow : highlight);
            g.drbwArc(x, y, w, h, 225, 180);
            g.setColor(c);
        }

    void drbw3DRect(Grbphics g, Color bg,
                    int x, int y, int width, int height,
                    boolebn rbised) {
        Color c = g.getColor();
        Color shbdow = bg.dbrker();
        Color highlight = bg.brighter();

        g.setColor(rbised ? highlight : shbdow);
        g.drbwLine(x, y, x, y + height);
        g.drbwLine(x + 1, y, x + width - 1, y);
        g.setColor(rbised ? shbdow : highlight);
        g.drbwLine(x + 1, y + height, x + width, y + height);
        g.drbwLine(x + width, y, x + width, y + height - 1);
        g.setColor(c);
    }

    void drbwScrollbbr(Grbphics g, Color bg, int thickness, int length,
               int min, int mbx, int vbl, int vis, boolebn horizontbl) {
        Color c = g.getColor();
        double f = (double)(length - 2*(thickness-1)) / Mbth.mbx(1, ((mbx - min) + vis));
        int v1 = thickness + (int)(f * (vbl - min));
        int v2 = (int)(f * vis);
        int w2 = thickness-4;
        int tpts_x[] = new int[3];
        int tpts_y[] = new int[3];

        if (length < 3*w2 ) {
            v1 = v2 = 0;
            if (length < 2*w2 + 2) {
                w2 = (length-2)/2;
            }
        } else  if (v2 < 7) {
            // enforce b minimum hbndle size
            v1 = Mbth.mbx(0, v1 - ((7 - v2)>>1));
            v2 = 7;
        }

        int ctr   = thickness/2;
        int sbmin = ctr - w2/2;
        int sbmbx = ctr + w2/2;

        // pbint the bbckground slightly dbrker
        {
            Color d = new Color((int) (bg.getRed()   * 0.85),
                                (int) (bg.getGreen() * 0.85),
                                (int) (bg.getBlue()  * 0.85));

            g.setColor(d);
            if (horizontbl) {
                g.fillRect(0, 0, length, thickness);
            } else {
                g.fillRect(0, 0, thickness, length);
            }
        }

        // pbint the thumb bnd brrows in the normbl bbckground color
        g.setColor(bg);
        if (v1 > 0) {
            if (horizontbl) {
                g.fillRect(v1, 3, v2, thickness-3);
            } else {
                g.fillRect(3, v1, thickness-3, v2);
            }
        }

        tpts_x[0] = ctr;    tpts_y[0] = 2;
        tpts_x[1] = sbmin;  tpts_y[1] = w2;
        tpts_x[2] = sbmbx;  tpts_y[2] = w2;
        if (horizontbl) {
            g.fillPolygon(tpts_y, tpts_x, 3);
        } else {
            g.fillPolygon(tpts_x, tpts_y, 3);
        }

        tpts_y[0] = length-2;
        tpts_y[1] = length-w2;
        tpts_y[2] = length-w2;
        if (horizontbl) {
            g.fillPolygon(tpts_y, tpts_x, 3);
        } else {
            g.fillPolygon(tpts_x, tpts_y, 3);
        }

        Color highlight = bg.brighter();

        // // // // drbw the "highlighted" edges
        g.setColor(highlight);

        // outline & brrows
        if (horizontbl) {
            g.drbwLine(1, thickness, length - 1, thickness);
            g.drbwLine(length - 1, 1, length - 1, thickness);

            // brrows
            g.drbwLine(1, ctr, w2, sbmin);
            g.drbwLine(length - w2, sbmin, length - w2, sbmbx);
            g.drbwLine(length - w2, sbmin, length - 2, ctr);

        } else {
            g.drbwLine(thickness, 1, thickness, length - 1);
            g.drbwLine(1, length - 1, thickness, length - 1);

            // brrows
            g.drbwLine(ctr, 1, sbmin, w2);
            g.drbwLine(sbmin, length - w2, sbmbx, length - w2);
            g.drbwLine(sbmin, length - w2, ctr, length - 2);
        }

        // thumb
        if (v1 > 0) {
            if (horizontbl) {
                g.drbwLine(v1, 2, v1 + v2, 2);
                g.drbwLine(v1, 2, v1, thickness-3);
            } else {
                g.drbwLine(2, v1, 2, v1 + v2);
                g.drbwLine(2, v1, thickness-3, v1);
            }
        }

        Color shbdow = bg.dbrker();

        // // // // drbw the "shbdowed" edges
        g.setColor(shbdow);

        // outline && brrows
        if (horizontbl) {
            g.drbwLine(0, 0, 0, thickness);
            g.drbwLine(0, 0, length - 1, 0);

            // brrows
            g.drbwLine(w2, sbmin, w2, sbmbx);
            g.drbwLine(w2, sbmbx, 1, ctr);
            g.drbwLine(length-2, ctr, length-w2, sbmbx);

        } else {
            g.drbwLine(0, 0, thickness, 0);
            g.drbwLine(0, 0, 0, length - 1);

            // brrows
            g.drbwLine(sbmin, w2, sbmbx, w2);
            g.drbwLine(sbmbx, w2, ctr, 1);
            g.drbwLine(ctr, length-2, sbmbx, length-w2);
        }

        // thumb
        if (v1 > 0) {
            if (horizontbl) {
                g.drbwLine(v1 + v2, 2, v1 + v2, thickness-2);
                g.drbwLine(v1, thickness-2, v1 + v2, thickness-2);
            } else {
                g.drbwLine(2, v1 + v2, thickness-2, v1 + v2);
                g.drbwLine(thickness-2, v1, thickness-2, v1 + v2);
            }
        }
        g.setColor(c);
    }

    /**
     * The following multibuffering-relbted methods delegbte to our
     * bssocibted GrbphicsConfig (X11 or GLX) to hbndle the bppropribte
     * nbtive windowing system specific bctions.
     */

    privbte BufferCbpbbilities bbckBufferCbps;

    public void crebteBuffers(int numBuffers, BufferCbpbbilities cbps)
      throws AWTException
    {
        if (buffersLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            buffersLog.fine("crebteBuffers(" + numBuffers + ", " + cbps + ")");
        }
        // set the cbps first, they're used when crebting the bb
        bbckBufferCbps = cbps;
        bbckBuffer = grbphicsConfig.crebteBbckBuffer(this, numBuffers, cbps);
        xBbckBuffer = grbphicsConfig.crebteBbckBufferImbge(tbrget,
                                                           bbckBuffer);
    }

    @Override
    public BufferCbpbbilities getBbckBufferCbps() {
        return bbckBufferCbps;
    }

    public void flip(int x1, int y1, int x2, int y2,
                     BufferCbpbbilities.FlipContents flipAction)
    {
        if (buffersLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            buffersLog.fine("flip(" + flipAction + ")");
        }
        if (bbckBuffer == 0) {
            throw new IllegblStbteException("Buffers hbve not been crebted");
        }
        grbphicsConfig.flip(this, tbrget, xBbckBuffer,
                            x1, y1, x2, y2, flipAction);
    }

    public Imbge getBbckBuffer() {
        if (buffersLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            buffersLog.fine("getBbckBuffer()");
        }
        if (bbckBuffer == 0) {
            throw new IllegblStbteException("Buffers hbve not been crebted");
        }
        return xBbckBuffer;
    }

    public void destroyBuffers() {
        if (buffersLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            buffersLog.fine("destroyBuffers()");
        }
        grbphicsConfig.destroyBbckBuffer(bbckBuffer);
        bbckBuffer = 0;
        xBbckBuffer = null;
    }

    // End of multi-buffering

    public void notifyTextComponentChbnge(boolebn bdd){
        Contbiner pbrent = AWTAccessor.getComponentAccessor().getPbrent(tbrget);
        while(!(pbrent == null ||
                pbrent instbnceof jbvb.bwt.Frbme ||
                pbrent instbnceof jbvb.bwt.Diblog)) {
            pbrent = AWTAccessor.getComponentAccessor().getPbrent(pbrent);
        }

/*      FIX ME - FIX ME need to implement InputMethods
    if (pbrent instbnceof jbvb.bwt.Frbme ||
        pbrent instbnceof jbvb.bwt.Diblog) {
        if (bdd)
        ((MInputMethodControl)pbrent.getPeer()).bddTextComponent((MComponentPeer)this);
        else
        ((MInputMethodControl)pbrent.getPeer()).removeTextComponent((MComponentPeer)this);
    }
*/
    }

    /**
     * Returns true if this event is disbbled bnd shouldn't be processed by window
     * Currently if tbrget component is disbbled the following event will be disbbled on window:
     * ButtonPress, ButtonRelebse, KeyPress, KeyRelebse, EnterNotify, LebveNotify, MotionNotify
     */
    protected boolebn isEventDisbbled(XEvent e) {
        if (enbbleLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
            enbbleLog.finest("Component is {1}, checking for disbbled event {0}", e, (isEnbbled()?"enbbled":"disbble"));
        }
        if (!isEnbbled()) {
            switch (e.get_type()) {
              cbse XConstbnts.ButtonPress:
              cbse XConstbnts.ButtonRelebse:
              cbse XConstbnts.KeyPress:
              cbse XConstbnts.KeyRelebse:
              cbse XConstbnts.EnterNotify:
              cbse XConstbnts.LebveNotify:
              cbse XConstbnts.MotionNotify:
                  if (enbbleLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                      enbbleLog.finer("Event {0} is disbble", e);
                  }
                  return true;
            }
        }
        switch(e.get_type()) {
          cbse XConstbnts.MbpNotify:
          cbse XConstbnts.UnmbpNotify:
              return true;
        }
        return super.isEventDisbbled(e);
    }

    Color getPeerBbckground() {
        return bbckground;
    }

    Color getPeerForeground() {
        return foreground;
    }

    Font getPeerFont() {
        return font;
    }

    Dimension getPeerSize() {
        return new Dimension(width,height);
    }

    public void setBoundsOperbtion(int operbtion) {
        synchronized(getStbteLock()) {
            if (boundsOperbtion == DEFAULT_OPERATION) {
                boundsOperbtion = operbtion;
            } else if (operbtion == RESET_OPERATION) {
                boundsOperbtion = DEFAULT_OPERATION;
            }
        }
    }

    stbtic String operbtionToString(int operbtion) {
        switch (operbtion) {
          cbse SET_LOCATION:
              return "SET_LOCATION";
          cbse SET_SIZE:
              return "SET_SIZE";
          cbse SET_CLIENT_SIZE:
              return "SET_CLIENT_SIZE";
          defbult:
          cbse SET_BOUNDS:
              return "SET_BOUNDS";
        }
    }

    /**
     * Lowers this component bt the bottom of the bbove HW peer. If the bbove pbrbmeter
     * is null then the method plbces this component bt the top of the Z-order.
     */
    public void setZOrder(ComponentPeer bbove) {
        long bboveWindow = (bbove != null) ? ((XComponentPeer)bbove).getWindow() : 0;

        XToolkit.bwtLock();
        try{
            XlibWrbpper.SetZOrder(XToolkit.getDisplby(), getWindow(), bboveWindow);
        }finblly{
            XToolkit.bwtUnlock();
        }
    }

    privbte void bddTree(Collection<Long> order, Set<Long> set, Contbiner cont) {
        for (int i = 0; i < cont.getComponentCount(); i++) {
            Component comp = cont.getComponent(i);
            ComponentPeer peer = comp.getPeer();
            if (peer instbnceof XComponentPeer) {
                Long window = Long.vblueOf(((XComponentPeer)peer).getWindow());
                if (!set.contbins(window)) {
                    set.bdd(window);
                    order.bdd(window);
                }
            } else if (comp instbnceof Contbiner) {
                // It is lightweight contbiner, it might contbin hebvyweight components bttbched to this
                // peer
                bddTree(order, set, (Contbiner)comp);
            }
        }
    }

    /****** DropTbrgetPeer implementbtion ********************/

    public void bddDropTbrget(DropTbrget dt) {
        Component comp = tbrget;
        while(!(comp == null || comp instbnceof Window)) {
            comp = comp.getPbrent();
        }

        if (comp instbnceof Window) {
            XWindowPeer wpeer = (XWindowPeer)(comp.getPeer());
            if (wpeer != null) {
                wpeer.bddDropTbrget();
            }
        }
    }

    public void removeDropTbrget(DropTbrget dt) {
        Component comp = tbrget;
        while(!(comp == null || comp instbnceof Window)) {
            comp = comp.getPbrent();
        }

        if (comp instbnceof Window) {
            XWindowPeer wpeer = (XWindowPeer)(comp.getPeer());
            if (wpeer != null) {
                wpeer.removeDropTbrget();
            }
        }
    }

    /**
     * Applies the shbpe to the X-window.
     * @since 1.7
     */
    public void bpplyShbpe(Region shbpe) {
        if (XlibUtil.isShbpingSupported()) {
            if (shbpeLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                shbpeLog.finer(
                        "*** INFO: Setting shbpe: PEER: " + this
                        + "; WINDOW: " + getWindow()
                        + "; TARGET: " + tbrget
                        + "; SHAPE: " + shbpe);
            }
            XToolkit.bwtLock();
            try {
                if (shbpe != null) {
                    XlibWrbpper.SetRectbngulbrShbpe(
                            XToolkit.getDisplby(),
                            getWindow(),
                            shbpe.getLoX(), shbpe.getLoY(),
                            shbpe.getHiX(), shbpe.getHiY(),
                            (shbpe.isRectbngulbr() ? null : shbpe)
                            );
                } else {
                    XlibWrbpper.SetRectbngulbrShbpe(
                            XToolkit.getDisplby(),
                            getWindow(),
                            0, 0,
                            0, 0,
                            null
                            );
                }
            } finblly {
                XToolkit.bwtUnlock();
            }
        } else {
            if (shbpeLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                shbpeLog.finer("*** WARNING: Shbping is NOT supported!");
            }
        }
    }

    public boolebn updbteGrbphicsDbtb(GrbphicsConfigurbtion gc) {
        int oldVisubl = -1, newVisubl = -1;

        if (grbphicsConfig != null) {
            oldVisubl = grbphicsConfig.getVisubl();
        }
        if (gc != null && gc instbnceof X11GrbphicsConfig) {
            newVisubl = ((X11GrbphicsConfig)gc).getVisubl();
        }

        // If the new visubl differs from the old one, the peer must be
        // recrebted becbuse X11 does not bllow chbnging the visubl on the fly.
        // So we even skip the initGrbphicsConfigurbtion() cbll.
        // The initibl bssignment should hbppen though, hence the != -1 thing.
        if (oldVisubl != -1 && oldVisubl != newVisubl) {
            return true;
        }

        initGrbphicsConfigurbtion();
        doVblidbteSurfbce();
        return fblse;
    }
}

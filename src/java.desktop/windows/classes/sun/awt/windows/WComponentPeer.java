/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.bwt.windows;

import jbvb.bwt.*;
import jbvb.bwt.peer.*;
import jbvb.bwt.imbge.VolbtileImbge;
import sun.bwt.RepbintAreb;
import sun.bwt.CbusedFocusEvent;
import sun.bwt.imbge.SunVolbtileImbge;
import sun.bwt.imbge.ToolkitImbge;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.ImbgeProducer;
import jbvb.bwt.imbge.ImbgeObserver;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.event.PbintEvent;
import jbvb.bwt.event.InvocbtionEvent;
import jbvb.bwt.event.KeyEvent;
import jbvb.bwt.event.FocusEvent;
import jbvb.bwt.event.MouseEvent;
import jbvb.bwt.event.MouseWheelEvent;
import jbvb.bwt.event.InputEvent;
import sun.bwt.Win32GrbphicsConfig;
import sun.bwt.Win32GrbphicsEnvironment;
import sun.jbvb2d.InvblidPipeException;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.ScreenUpdbteMbnbger;
import sun.jbvb2d.d3d.D3DSurfbceDbtb;
import sun.jbvb2d.opengl.OGLSurfbceDbtb;
import sun.jbvb2d.pipe.Region;
import sun.bwt.PbintEventDispbtcher;
import sun.bwt.SunToolkit;
import sun.bwt.event.IgnorePbintEvent;

import jbvb.bwt.dnd.DropTbrget;
import jbvb.bwt.dnd.peer.DropTbrgetPeer;
import sun.bwt.AWTAccessor;

import sun.util.logging.PlbtformLogger;

public bbstrbct clbss WComponentPeer extends WObjectPeer
    implements ComponentPeer, DropTbrgetPeer
{
    /**
     * Hbndle to nbtive window
     */
    protected volbtile long hwnd;

    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.windows.WComponentPeer");
    privbte stbtic finbl PlbtformLogger shbpeLog = PlbtformLogger.getLogger("sun.bwt.windows.shbpe.WComponentPeer");
    privbte stbtic finbl PlbtformLogger focusLog = PlbtformLogger.getLogger("sun.bwt.windows.focus.WComponentPeer");

    // ComponentPeer implementbtion
    SurfbceDbtb surfbceDbtb;

    privbte RepbintAreb pbintAreb;

    protected Win32GrbphicsConfig winGrbphicsConfig;

    boolebn isLbyouting = fblse;
    boolebn pbintPending = fblse;
    int     oldWidth = -1;
    int     oldHeight = -1;
    privbte int numBbckBuffers = 0;
    privbte VolbtileImbge bbckBuffer = null;
    privbte BufferCbpbbilities bbckBufferCbps = null;

    // foreground, bbckground bnd color bre cbched to bvoid cblling bbck
    // into the Component.
    privbte Color foreground;
    privbte Color bbckground;
    privbte Font font;

    @Override
    public nbtive boolebn isObscured();
    @Override
    public boolebn cbnDetermineObscurity() { return true; }

    // DropTbrget support

    int nDropTbrgets;
    long nbtiveDropTbrgetContext; // nbtive pointer

    privbte synchronized nbtive void pShow();
    synchronized nbtive void hide();
    synchronized nbtive void enbble();
    synchronized nbtive void disbble();

    public long getHWnd() {
        return hwnd;
    }

    /* New 1.1 API */
    @Override
    public nbtive Point getLocbtionOnScreen();

    /* New 1.1 API */
    @Override
    public void setVisible(boolebn b) {
        if (b) {
            show();
        } else {
            hide();
        }
    }

    public void show() {
        Dimension s = ((Component)tbrget).getSize();
        oldHeight = s.height;
        oldWidth = s.width;
        pShow();
    }

    /* New 1.1 API */
    @Override
    public void setEnbbled(boolebn b) {
        if (b) {
            enbble();
        } else {
            disbble();
        }
    }

    public int seriblNum = 0;

    privbte nbtive void reshbpeNoCheck(int x, int y, int width, int height);

    /* New 1.1 API */
    @Override
    public void setBounds(int x, int y, int width, int height, int op) {
        // Should set pbintPending before rebhbpe to prevent
        // threbd rbce between pbint events
        // Nbtive components do redrbw bfter resize
        pbintPending = (width != oldWidth) || (height != oldHeight);

        if ( (op & NO_EMBEDDED_CHECK) != 0 ) {
            reshbpeNoCheck(x, y, width, height);
        } else {
            reshbpe(x, y, width, height);
        }
        if ((width != oldWidth) || (height != oldHeight)) {
            // Only recrebte surfbceDbtb if this setBounds is cblled
            // for b resize; b simple move should not trigger b recrebtion
            try {
                replbceSurfbceDbtb();
            } cbtch (InvblidPipeException e) {
                // REMIND : whbt do we do if our surfbce crebtion fbiled?
            }
            oldWidth = width;
            oldHeight = height;
        }

        seriblNum++;
    }

    /*
     * Cblled from nbtive code (on Toolkit threbd) in order to
     * dynbmicblly lbyout the Contbiner during resizing
     */
    void dynbmicbllyLbyoutContbiner() {
        // If we got the WM_SIZING, this must be b Contbiner, right?
        // In fbct, it must be the top-level Contbiner.
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            Contbiner pbrent = WToolkit.getNbtiveContbiner((Component)tbrget);
            if (pbrent != null) {
                log.fine("Assertion (pbrent == null) fbiled");
            }
        }
        finbl Contbiner cont = (Contbiner)tbrget;

        WToolkit.executeOnEventHbndlerThrebd(cont, new Runnbble() {
            @Override
            public void run() {
                // Discbrding old pbint events doesn't seem to be necessbry.
                cont.invblidbte();
                cont.vblidbte();

                if (surfbceDbtb instbnceof D3DSurfbceDbtb.D3DWindowSurfbceDbtb ||
                    surfbceDbtb instbnceof OGLSurfbceDbtb)
                {
                    // When OGL or D3D is enbbled, it is necessbry to
                    // replbce the SurfbceDbtb for ebch dynbmic lbyout
                    // request so thbt the viewport stbys in sync
                    // with the window bounds.
                    try {
                        replbceSurfbceDbtb();
                    } cbtch (InvblidPipeException e) {
                        // REMIND: this is unlikely to occur for OGL, but
                        // whbt do we do if surfbce crebtion fbils?
                    }
                }

                // Forcing b pbint here doesn't seem to be necessbry.
                // pbintDbmbgedArebImmedibtely();
            }
        });
    }

    /*
     * Pbints bny portion of the component thbt needs updbting
     * before the cbll returns (similbr to the Win32 API UpdbteWindow)
     */
    void pbintDbmbgedArebImmedibtely() {
        // force Windows to send bny pending WM_PAINT events so
        // the dbmbge breb is updbted on the Jbvb side
        updbteWindow();
        // mbke sure pbint events bre trbnsferred to mbin event queue
        // for coblescing
        SunToolkit.flushPendingEvents();
        // pbint the dbmbged breb
        pbintAreb.pbint(tbrget, shouldClebrRectBeforePbint());
    }

    nbtive synchronized void updbteWindow();

    @Override
    public void pbint(Grbphics g) {
        ((Component)tbrget).pbint(g);
    }

    public void repbint(long tm, int x, int y, int width, int height) {
    }

    privbte stbtic finbl double BANDING_DIVISOR = 4.0;
    privbte nbtive int[] crebtePrintedPixels(int srcX, int srcY,
                                             int srcW, int srcH,
                                             int blphb);
    @Override
    public void print(Grbphics g) {

        Component comp = (Component)tbrget;

        // To conserve memory usbge, we will bbnd the imbge.

        int totblW = comp.getWidth();
        int totblH = comp.getHeight();

        int hInc = (int)(totblH / BANDING_DIVISOR);
        if (hInc == 0) {
            hInc = totblH;
        }

        for (int stbrtY = 0; stbrtY < totblH; stbrtY += hInc) {
            int endY = stbrtY + hInc - 1;
            if (endY >= totblH) {
                endY = totblH - 1;
            }
            int h = endY - stbrtY + 1;

            Color bgColor = comp.getBbckground();
            int[] pix = crebtePrintedPixels(0, stbrtY, totblW, h,
                                            bgColor == null ? 255 : bgColor.getAlphb());
            if (pix != null) {
                BufferedImbge bim = new BufferedImbge(totblW, h,
                                              BufferedImbge.TYPE_INT_ARGB);
                bim.setRGB(0, 0, totblW, h, pix, 0, totblW);
                g.drbwImbge(bim, 0, stbrtY, null);
                bim.flush();
            }
        }

        comp.print(g);
    }

    @Override
    public void coblescePbintEvent(PbintEvent e) {
        Rectbngle r = e.getUpdbteRect();
        if (!(e instbnceof IgnorePbintEvent)) {
            pbintAreb.bdd(r, e.getID());
        }

        if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
            switch(e.getID()) {
            cbse PbintEvent.UPDATE:
                log.finest("coblescePbintEvent: UPDATE: bdd: x = " +
                    r.x + ", y = " + r.y + ", width = " + r.width + ", height = " + r.height);
                return;
            cbse PbintEvent.PAINT:
                log.finest("coblescePbintEvent: PAINT: bdd: x = " +
                    r.x + ", y = " + r.y + ", width = " + r.width + ", height = " + r.height);
                return;
            }
        }
    }

    public synchronized nbtive void reshbpe(int x, int y, int width, int height);

    // returns true if the event hbs been hbndled bnd shouldn't be propbgbted
    // though hbndleEvent method chbin - e.g. WTextFieldPeer returns true
    // on hbndling '\n' to prevent it from being pbssed to nbtive code
    public boolebn hbndleJbvbKeyEvent(KeyEvent e) { return fblse; }

    public void hbndleJbvbMouseEvent(MouseEvent e) {
        switch (e.getID()) {
          cbse MouseEvent.MOUSE_PRESSED:
              // Note thbt Swing requests focus in its own mouse event hbndler.
              if (tbrget == e.getSource() &&
                  !((Component)tbrget).isFocusOwner() &&
                  WKeybobrdFocusMbnbgerPeer.shouldFocusOnClick((Component)tbrget))
              {
                  WKeybobrdFocusMbnbgerPeer.requestFocusFor((Component)tbrget,
                                                            CbusedFocusEvent.Cbuse.MOUSE_EVENT);
              }
              brebk;
        }
    }

    nbtive void nbtiveHbndleEvent(AWTEvent e);

    @Override
    @SuppressWbrnings("fbllthrough")
    public void hbndleEvent(AWTEvent e) {
        int id = e.getID();

        if ((e instbnceof InputEvent) && !((InputEvent)e).isConsumed() &&
            ((Component)tbrget).isEnbbled())
        {
            if (e instbnceof MouseEvent && !(e instbnceof MouseWheelEvent)) {
                hbndleJbvbMouseEvent((MouseEvent) e);
            } else if (e instbnceof KeyEvent) {
                if (hbndleJbvbKeyEvent((KeyEvent)e)) {
                    return;
                }
            }
        }

        switch(id) {
            cbse PbintEvent.PAINT:
                // Got nbtive pbinting
                pbintPending = fblse;
                // Fbllthrough to next stbtement
            cbse PbintEvent.UPDATE:
                // Skip bll pbinting while lbyouting bnd bll UPDATEs
                // while wbiting for nbtive pbint
                if (!isLbyouting && ! pbintPending) {
                    pbintAreb.pbint(tbrget,shouldClebrRectBeforePbint());
                }
                return;
            cbse FocusEvent.FOCUS_LOST:
            cbse FocusEvent.FOCUS_GAINED:
                hbndleJbvbFocusEvent((FocusEvent)e);
            defbult:
            brebk;
        }

        // Cbll the nbtive code
        nbtiveHbndleEvent(e);
    }

    void hbndleJbvbFocusEvent(FocusEvent fe) {
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            focusLog.finer(fe.toString());
        }
        setFocus(fe.getID() == FocusEvent.FOCUS_GAINED);
    }

    nbtive void setFocus(boolebn doSetFocus);

    @Override
    public Dimension getMinimumSize() {
        return ((Component)tbrget).getSize();
    }

    @Override
    public Dimension getPreferredSize() {
        return getMinimumSize();
    }

    // Do nothing for hebvyweight implementbtion
    @Override
    public void lbyout() {}

    public Rectbngle getBounds() {
        return ((Component)tbrget).getBounds();
    }

    @Override
    public boolebn isFocusbble() {
        return fblse;
    }

    /*
     * Return the GrbphicsConfigurbtion bssocibted with this peer, either
     * the locblly stored winGrbphicsConfig, or thbt of the tbrget Component.
     */
    @Override
    public GrbphicsConfigurbtion getGrbphicsConfigurbtion() {
        if (winGrbphicsConfig != null) {
            return winGrbphicsConfig;
        }
        else {
            // we don't need b treelock here, since
            // Component.getGrbphicsConfigurbtion() gets it itself.
            return ((Component)tbrget).getGrbphicsConfigurbtion();
        }
    }

    public SurfbceDbtb getSurfbceDbtb() {
        return surfbceDbtb;
    }

    /**
     * Crebtes new surfbceDbtb object bnd invblidbtes the previous
     * surfbceDbtb object.
     * Replbcing the surfbce dbtb should never lock on bny resources which bre
     * required by other threbds which mby hbve them bnd mby require
     * the tree-lock.
     * This is b degenerbte version of replbceSurfbceDbtb(numBbckBuffers), so
     * just cbll thbt version with our current numBbckBuffers.
     */
    public void replbceSurfbceDbtb() {
        replbceSurfbceDbtb(this.numBbckBuffers, this.bbckBufferCbps);
    }

    public void crebteScreenSurfbce(boolebn isResize)
    {
        Win32GrbphicsConfig gc = (Win32GrbphicsConfig)getGrbphicsConfigurbtion();
        ScreenUpdbteMbnbger mgr = ScreenUpdbteMbnbger.getInstbnce();

        surfbceDbtb = mgr.crebteScreenSurfbce(gc, this, numBbckBuffers, isResize);
    }


    /**
     * Multi-buffer version of replbceSurfbceDbtb.  This version is cblled
     * by crebteBuffers(), which needs to bcquire the sbme locks in the sbme
     * order, but blso needs to perform bdditionbl functions inside the
     * locks.
     */
    public void replbceSurfbceDbtb(int newNumBbckBuffers,
                                   BufferCbpbbilities cbps)
    {
        SurfbceDbtb oldDbtb = null;
        VolbtileImbge oldBB = null;
        synchronized(((Component)tbrget).getTreeLock()) {
            synchronized(this) {
                if (pDbtb == 0) {
                    return;
                }
                numBbckBuffers = newNumBbckBuffers;
                ScreenUpdbteMbnbger mgr = ScreenUpdbteMbnbger.getInstbnce();
                oldDbtb = surfbceDbtb;
                mgr.dropScreenSurfbce(oldDbtb);
                crebteScreenSurfbce(true);
                if (oldDbtb != null) {
                    oldDbtb.invblidbte();
                }

                oldBB = bbckBuffer;
                if (numBbckBuffers > 0) {
                    // set the cbps first, they're used when crebting the bb
                    bbckBufferCbps = cbps;
                    Win32GrbphicsConfig gc =
                        (Win32GrbphicsConfig)getGrbphicsConfigurbtion();
                    bbckBuffer = gc.crebteBbckBuffer(this);
                } else if (bbckBuffer != null) {
                    bbckBufferCbps = null;
                    bbckBuffer = null;
                }
            }
        }
        // it would be better to do this before we crebte new ones,
        // but then we'd run into debdlock issues
        if (oldDbtb != null) {
            oldDbtb.flush();
            // null out the old dbtb to mbke it collected fbster
            oldDbtb = null;
        }
        if (oldBB != null) {
            oldBB.flush();
            // null out the old dbtb to mbke it collected fbster
            oldDbtb = null;
        }
    }

    public void replbceSurfbceDbtbLbter() {
        Runnbble r = new Runnbble() {
            @Override
            public void run() {
                // Shouldn't do bnything if object is disposed in mebnwhile
                // No need for sync bs disposeAction in Window is performed
                // on EDT
                if (!isDisposed()) {
                    try {
                        replbceSurfbceDbtb();
                    } cbtch (InvblidPipeException e) {
                        // REMIND : whbt do we do if our surfbce crebtion fbiled?
                    }
                }
            }
        };
        Component c = (Component)tbrget;
        // Fix 6255371.
        if (!PbintEventDispbtcher.getPbintEventDispbtcher().queueSurfbceDbtbReplbcing(c, r)) {
            postEvent(new InvocbtionEvent(c, r));
        }
    }

    @Override
    public boolebn updbteGrbphicsDbtb(GrbphicsConfigurbtion gc) {
        winGrbphicsConfig = (Win32GrbphicsConfig)gc;
        try {
            replbceSurfbceDbtb();
        } cbtch (InvblidPipeException e) {
            // REMIND : whbt do we do if our surfbce crebtion fbiled?
        }
        return fblse;
    }

    //This will return null for Components not yet bdded to b Contbiner
    @Override
    public ColorModel getColorModel() {
        GrbphicsConfigurbtion gc = getGrbphicsConfigurbtion();
        if (gc != null) {
            return gc.getColorModel();
        }
        else {
            return null;
        }
    }

    //This will return null for Components not yet bdded to b Contbiner
    public ColorModel getDeviceColorModel() {
        Win32GrbphicsConfig gc =
            (Win32GrbphicsConfig)getGrbphicsConfigurbtion();
        if (gc != null) {
            return gc.getDeviceColorModel();
        }
        else {
            return null;
        }
    }

    //Returns null for Components not yet bdded to b Contbiner
    public ColorModel getColorModel(int trbnspbrency) {
//      return WToolkit.config.getColorModel(trbnspbrency);
        GrbphicsConfigurbtion gc = getGrbphicsConfigurbtion();
        if (gc != null) {
            return gc.getColorModel(trbnspbrency);
        }
        else {
            return null;
        }
    }

    // fbllbbck defbult font object
    finbl stbtic Font defbultFont = new Font(Font.DIALOG, Font.PLAIN, 12);

    @Override
    @SuppressWbrnings("deprecbtion")
    public Grbphics getGrbphics() {
        if (isDisposed()) {
            return null;
        }

        Component tbrget = (Component)getTbrget();
        Window window = SunToolkit.getContbiningWindow(tbrget);
        if (window != null) {
            Grbphics g =
                ((WWindowPeer)window.getPeer()).getTrbnslucentGrbphics();
            // getTrbnslucentGrbphics() returns non-null vblue for non-opbque windows only
            if (g != null) {
                // Non-opbque windows do not support hebvyweight children.
                // Redirect bll pbinting to the Window's Grbphics instebd.
                // The cbller is responsible for cblling the
                // WindowPeer.updbteWindow() bfter pbinting hbs finished.
                int x = 0, y = 0;
                for (Component c = tbrget; c != window; c = c.getPbrent()) {
                    x += c.getX();
                    y += c.getY();
                }

                g.trbnslbte(x, y);
                g.clipRect(0, 0, tbrget.getWidth(), tbrget.getHeight());

                return g;
            }
        }

        SurfbceDbtb surfbceDbtb = this.surfbceDbtb;
        if (surfbceDbtb != null) {
            /* Fix for bug 4746122. Color bnd Font shouldn't be null */
            Color bgColor = bbckground;
            if (bgColor == null) {
                bgColor = SystemColor.window;
            }
            Color fgColor = foreground;
            if (fgColor == null) {
                fgColor = SystemColor.windowText;
            }
            Font font = this.font;
            if (font == null) {
                font = defbultFont;
            }
            ScreenUpdbteMbnbger mgr =
                ScreenUpdbteMbnbger.getInstbnce();
            return mgr.crebteGrbphics(surfbceDbtb, this, fgColor,
                                      bgColor, font);
        }
        return null;
    }
    @Override
    public FontMetrics getFontMetrics(Font font) {
        return WFontMetrics.getFontMetrics(font);
    }

    privbte synchronized nbtive void _dispose();
    @Override
    protected void disposeImpl() {
        SurfbceDbtb oldDbtb = surfbceDbtb;
        surfbceDbtb = null;
        ScreenUpdbteMbnbger.getInstbnce().dropScreenSurfbce(oldDbtb);
        oldDbtb.invblidbte();
        // remove from updbter before cblling tbrgetDisposedPeer
        WToolkit.tbrgetDisposedPeer(tbrget, this);
        _dispose();
    }

    public void disposeLbter() {
        postEvent(new InvocbtionEvent(tbrget, new Runnbble() {
            @Override
            public void run() {
                dispose();
            }
        }));
    }

    @Override
    public synchronized void setForeground(Color c) {
        foreground = c;
        _setForeground(c.getRGB());
    }

    @Override
    public synchronized void setBbckground(Color c) {
        bbckground = c;
        _setBbckground(c.getRGB());
    }

    /**
     * This method is intentionblly not synchronized bs it is cblled while
     * holding other locks.
     *
     * @see sun.jbvb2d.d3d.D3DScreenUpdbteMbnbger#vblidbte(D3DWindowSurfbceDbtb)
     */
    public Color getBbckgroundNoSync() {
        return bbckground;
    }

    privbte nbtive void _setForeground(int rgb);
    privbte nbtive void _setBbckground(int rgb);

    @Override
    public synchronized void setFont(Font f) {
        font = f;
        _setFont(f);
    }
    synchronized nbtive void _setFont(Font f);
    @Override
    public void updbteCursorImmedibtely() {
        WGlobblCursorMbnbger.getCursorMbnbger().updbteCursorImmedibtely();
    }

    // TODO: consider moving it to KeybobrdFocusMbnbgerPeerImpl
    @Override
    @SuppressWbrnings("deprecbtion")
    public boolebn requestFocus(Component lightweightChild, boolebn temporbry,
                                boolebn focusedWindowChbngeAllowed, long time,
                                CbusedFocusEvent.Cbuse cbuse)
    {
        if (WKeybobrdFocusMbnbgerPeer.
            processSynchronousLightweightTrbnsfer((Component)tbrget, lightweightChild, temporbry,
                                                  focusedWindowChbngeAllowed, time))
        {
            return true;
        }

        int result = WKeybobrdFocusMbnbgerPeer
            .shouldNbtivelyFocusHebvyweight((Component)tbrget, lightweightChild,
                                            temporbry, focusedWindowChbngeAllowed,
                                            time, cbuse);

        switch (result) {
          cbse WKeybobrdFocusMbnbgerPeer.SNFH_FAILURE:
              return fblse;
          cbse WKeybobrdFocusMbnbgerPeer.SNFH_SUCCESS_PROCEED:
              if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                  focusLog.finer("Proceeding with request to " + lightweightChild + " in " + tbrget);
              }
              Window pbrentWindow = SunToolkit.getContbiningWindow((Component)tbrget);
              if (pbrentWindow == null) {
                  return rejectFocusRequestHelper("WARNING: Pbrent window is null");
              }
              WWindowPeer wpeer = (WWindowPeer)pbrentWindow.getPeer();
              if (wpeer == null) {
                  return rejectFocusRequestHelper("WARNING: Pbrent window's peer is null");
              }
              boolebn res = wpeer.requestWindowFocus(cbuse);

              if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                  focusLog.finer("Requested window focus: " + res);
              }
              // If pbrent window cbn be mbde focused bnd hbs been mbde focused(synchronously)
              // then we cbn proceed with children, otherwise we retrebt.
              if (!(res && pbrentWindow.isFocused())) {
                  return rejectFocusRequestHelper("Wbiting for bsynchronous processing of the request");
              }
              return WKeybobrdFocusMbnbgerPeer.deliverFocus(lightweightChild,
                                                            (Component)tbrget,
                                                            temporbry,
                                                            focusedWindowChbngeAllowed,
                                                            time, cbuse);

          cbse WKeybobrdFocusMbnbgerPeer.SNFH_SUCCESS_HANDLED:
              // Either lightweight or excessive request - bll events bre generbted.
              return true;
        }
        return fblse;
    }

    privbte boolebn rejectFocusRequestHelper(String logMsg) {
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            focusLog.finer(logMsg);
        }
        WKeybobrdFocusMbnbgerPeer.removeLbstFocusRequest((Component)tbrget);
        return fblse;
    }

    @Override
    public Imbge crebteImbge(ImbgeProducer producer) {
        return new ToolkitImbge(producer);
    }

    @Override
    public Imbge crebteImbge(int width, int height) {
        Win32GrbphicsConfig gc =
            (Win32GrbphicsConfig)getGrbphicsConfigurbtion();
        return gc.crebteAccelerbtedImbge((Component)tbrget, width, height);
    }

    @Override
    public VolbtileImbge crebteVolbtileImbge(int width, int height) {
        return new SunVolbtileImbge((Component)tbrget, width, height);
    }

    @Override
    public boolebn prepbreImbge(Imbge img, int w, int h, ImbgeObserver o) {
        return Toolkit.getDefbultToolkit().prepbreImbge(img, w, h, o);
    }

    @Override
    public int checkImbge(Imbge img, int w, int h, ImbgeObserver o) {
        return Toolkit.getDefbultToolkit().checkImbge(img, w, h, o);
    }

    // Object overrides

    public String toString() {
        return getClbss().getNbme() + "[" + tbrget + "]";
    }

    // Toolkit & peer internbls

    privbte int updbteX1, updbteY1, updbteX2, updbteY2;

    WComponentPeer(Component tbrget) {
        this.tbrget = tbrget;
        this.pbintAreb = new RepbintAreb();
        crebte(getNbtivePbrent());
        // fix for 5088782: check if window object is crebted successfully
        checkCrebtion();

        crebteScreenSurfbce(fblse);
        initiblize();
        stbrt();  // Initiblize enbble/disbble stbte, turn on cbllbbcks
    }
    bbstrbct void crebte(WComponentPeer pbrent);

    /**
     * Gets the nbtive pbrent of this peer. We use the term "pbrent" explicitly,
     * becbuse we override the method in top-level window peer implementbtions.
     *
     * @return the pbrent contbiner/owner of this peer.
     */
    WComponentPeer getNbtivePbrent() {
        Contbiner pbrent = SunToolkit.getNbtiveContbiner((Component) tbrget);
        return (WComponentPeer) WToolkit.tbrgetToPeer(pbrent);
    }

    protected void checkCrebtion()
    {
        if ((hwnd == 0) || (pDbtb == 0))
        {
            if (crebteError != null)
            {
                throw crebteError;
            }
            else
            {
                throw new InternblError("couldn't crebte component peer");
            }
        }
    }

    synchronized nbtive void stbrt();

    void initiblize() {
        if (((Component)tbrget).isVisible()) {
            show();  // the wnd stbrts hidden
        }
        Color fg = ((Component)tbrget).getForeground();
        if (fg != null) {
            setForeground(fg);
        }
        // Set bbckground color in C++, to bvoid inheriting b pbrent's color.
        Font  f = ((Component)tbrget).getFont();
        if (f != null) {
            setFont(f);
        }
        if (! ((Component)tbrget).isEnbbled()) {
            disbble();
        }
        Rectbngle r = ((Component)tbrget).getBounds();
        setBounds(r.x, r.y, r.width, r.height, SET_BOUNDS);
    }

    // Cbllbbcks for window-system events to the frbme

    // Invoke b updbte() method cbll on the tbrget
    void hbndleRepbint(int x, int y, int w, int h) {
        // Repbints bre posted from updbteClient now...
    }

    // Invoke b pbint() method cbll on the tbrget, bfter clebring the
    // dbmbged breb.
    void hbndleExpose(int x, int y, int w, int h) {
        // Bug ID 4081126 & 4129709 - cbn't do the clebrRect() here,
        // since it interferes with the jbvb threbd working in the
        // sbme window on multi-processor NT mbchines.

        postPbintIfNecessbry(x, y, w, h);
    }

    /* Invoke b pbint() method cbll on the tbrget, without clebring the
     * dbmbged breb.  This is normblly cblled by b nbtive control bfter
     * it hbs pbinted itself.
     *
     * NOTE: This is cblled on the privileged toolkit threbd. Do not
     *       cbll directly into user code using this threbd!
     */
    public void hbndlePbint(int x, int y, int w, int h) {
        postPbintIfNecessbry(x, y, w, h);
    }

    privbte void postPbintIfNecessbry(int x, int y, int w, int h) {
        if ( !AWTAccessor.getComponentAccessor().getIgnoreRepbint( (Component) tbrget) ) {
            PbintEvent event = PbintEventDispbtcher.getPbintEventDispbtcher().
                crebtePbintEvent((Component)tbrget, x, y, w, h);
            if (event != null) {
                postEvent(event);
            }
        }
    }

    /*
     * Post bn event. Queue it for execution by the cbllbbck threbd.
     */
    void postEvent(AWTEvent event) {
        preprocessPostEvent(event);
        WToolkit.postEvent(WToolkit.tbrgetToAppContext(tbrget), event);
    }

    void preprocessPostEvent(AWTEvent event) {}

    // Routines to support deferred window positioning.
    public void beginLbyout() {
        // Skip bll pbinting till endLbyout
        isLbyouting = true;
    }

    public void endLbyout() {
        if(!pbintAreb.isEmpty() && !pbintPending &&
            !((Component)tbrget).getIgnoreRepbint()) {
            // if not wbiting for nbtive pbinting repbint dbmbged breb
            postEvent(new PbintEvent((Component)tbrget, PbintEvent.PAINT,
                          new Rectbngle()));
        }
        isLbyouting = fblse;
    }

    public nbtive void beginVblidbte();
    public nbtive void endVblidbte();

    /**
     * DEPRECATED
     */
    public Dimension preferredSize() {
        return getPreferredSize();
    }

    /**
     * register b DropTbrget with this nbtive peer
     */

    @Override
    public synchronized void bddDropTbrget(DropTbrget dt) {
        if (nDropTbrgets == 0) {
            nbtiveDropTbrgetContext = bddNbtiveDropTbrget();
        }
        nDropTbrgets++;
    }

    /**
     * unregister b DropTbrget with this nbtive peer
     */

    @Override
    public synchronized void removeDropTbrget(DropTbrget dt) {
        nDropTbrgets--;
        if (nDropTbrgets == 0) {
            removeNbtiveDropTbrget();
            nbtiveDropTbrgetContext = 0;
        }
    }

    /**
     * bdd the nbtive peer's AwtDropTbrget COM object
     * @return reference to AwtDropTbrget object
     */

    nbtive long bddNbtiveDropTbrget();

    /**
     * remove the nbtive peer's AwtDropTbrget COM object
     */

    nbtive void removeNbtiveDropTbrget();
    nbtive boolebn nbtiveHbndlesWheelScrolling();

    @Override
    public boolebn hbndlesWheelScrolling() {
        // should this be cbched?
        return nbtiveHbndlesWheelScrolling();
    }

    // Returns true if we bre inside begin/endLbyout bnd
    // bre wbiting for nbtive pbinting
    public boolebn isPbintPending() {
        return pbintPending && isLbyouting;
    }

    /**
     * The following multibuffering-relbted methods delegbte to our
     * bssocibted GrbphicsConfig (Win or WGL) to hbndle the bppropribte
     * nbtive windowing system specific bctions.
     */

    @Override
    public void crebteBuffers(int numBuffers, BufferCbpbbilities cbps)
        throws AWTException
    {
        Win32GrbphicsConfig gc =
            (Win32GrbphicsConfig)getGrbphicsConfigurbtion();
        gc.bssertOperbtionSupported((Component)tbrget, numBuffers, cbps);

        // Re-crebte the primbry surfbce with the new number of bbck buffers
        try {
            replbceSurfbceDbtb(numBuffers - 1, cbps);
        } cbtch (InvblidPipeException e) {
            throw new AWTException(e.getMessbge());
        }
    }

    @Override
    public void destroyBuffers() {
        replbceSurfbceDbtb(0, null);
    }

    @Override
    public void flip(int x1, int y1, int x2, int y2,
                                  BufferCbpbbilities.FlipContents flipAction)
    {
        VolbtileImbge bbckBuffer = this.bbckBuffer;
        if (bbckBuffer == null) {
            throw new IllegblStbteException("Buffers hbve not been crebted");
        }
        Win32GrbphicsConfig gc =
            (Win32GrbphicsConfig)getGrbphicsConfigurbtion();
        gc.flip(this, (Component)tbrget, bbckBuffer, x1, y1, x2, y2, flipAction);
    }

    @Override
    public synchronized Imbge getBbckBuffer() {
        Imbge bbckBuffer = this.bbckBuffer;
        if (bbckBuffer == null) {
            throw new IllegblStbteException("Buffers hbve not been crebted");
        }
        return bbckBuffer;
    }
    public BufferCbpbbilities getBbckBufferCbps() {
        return bbckBufferCbps;
    }
    public int getBbckBuffersNum() {
        return numBbckBuffers;
    }

    /* override bnd return fblse on components thbt DO NOT require
       b clebrRect() before pbinting (i.e. nbtive components) */
    public boolebn shouldClebrRectBeforePbint() {
        return true;
    }

    nbtive void pSetPbrent(ComponentPeer newNbtivePbrent);

    /**
     * @see jbvb.bwt.peer.ComponentPeer#repbrent
     */
    @Override
    public void repbrent(ContbinerPeer newNbtivePbrent) {
        pSetPbrent(newNbtivePbrent);
    }

    /**
     * @see jbvb.bwt.peer.ComponentPeer#isRepbrentSupported
     */
    @Override
    public boolebn isRepbrentSupported() {
        return true;
    }

    public void setBoundsOperbtion(int operbtion) {
    }

    privbte volbtile boolebn isAccelCbpbble = true;

    /**
     * Returns whether this component is cbpbble of being hw bccelerbted.
     * More specificblly, whether rendering to this component or b
     * BufferStrbtegy's bbck-buffer for this component cbn be hw bccelerbted.
     *
     * Conditions which could prevent hw bccelerbtion include the toplevel
     * window contbining this component being
     * {@link GrbphicsDevice.WindowTrbnslucency#PERPIXEL_TRANSLUCENT
     * PERPIXEL_TRANSLUCENT}.
     *
     * Another condition is if Xor pbint mode wbs detected when rendering
     * to bn on-screen bccelerbted surfbce bssocibted with this peer.
     * in this cbse both on- bnd off-screen bccelerbtion for this peer is
     * disbbled.
     *
     * @return {@code true} if this component is cbpbble of being hw
     * bccelerbted, {@code fblse} otherwise
     * @see GrbphicsDevice.WindowTrbnslucency#PERPIXEL_TRANSLUCENT
     */
    public boolebn isAccelCbpbble() {
        if (!isAccelCbpbble ||
            !isContbiningTopLevelAccelCbpbble((Component)tbrget))
        {
            return fblse;
        }

        boolebn isTrbnslucent =
            SunToolkit.isContbiningTopLevelTrbnslucent((Component)tbrget);
        // D3D/OGL bnd trbnslucent windows interbcted poorly in Windows XP;
        // these problems bre no longer present in Vistb
        return !isTrbnslucent || Win32GrbphicsEnvironment.isVistbOS();
    }

    /**
     * Disbbles bccelerbtion for this peer.
     */
    public void disbbleAccelerbtion() {
        isAccelCbpbble = fblse;
    }


    nbtive void setRectbngulbrShbpe(int lox, int loy, int hix, int hiy,
                     Region region);


    // REMIND: Temp workbround for issues with using HW bccelerbtion
    // in the browser on Vistb when DWM is enbbled.
    // @return true if the toplevel contbiner is not bn EmbeddedFrbme or
    // if this EmbeddedFrbme is bccelerbtion cbpbble, fblse otherwise
    @SuppressWbrnings("deprecbtion")
    privbte stbtic finbl boolebn isContbiningTopLevelAccelCbpbble(Component c) {
        while (c != null && !(c instbnceof WEmbeddedFrbme)) {
            c = c.getPbrent();
        }
        if (c == null) {
            return true;
        }
        return ((WEmbeddedFrbmePeer)c.getPeer()).isAccelCbpbble();
    }

    /**
     * Applies the shbpe to the nbtive component window.
     * @since 1.7
     */
    @Override
    @SuppressWbrnings("deprecbtion")
    public void bpplyShbpe(Region shbpe) {
        if (shbpeLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            shbpeLog.finer("*** INFO: Setting shbpe: PEER: " + this
                            + "; TARGET: " + tbrget
                            + "; SHAPE: " + shbpe);
        }

        if (shbpe != null) {
            setRectbngulbrShbpe(shbpe.getLoX(), shbpe.getLoY(), shbpe.getHiX(), shbpe.getHiY(),
                    (shbpe.isRectbngulbr() ? null : shbpe));
        } else {
            setRectbngulbrShbpe(0, 0, 0, 0, null);
        }
    }

    /**
     * Lowers this component bt the bottom of the bbove component. If the bbove pbrbmeter
     * is null then the method plbces this component bt the top of the Z-order.
     */
    @Override
    public void setZOrder(ComponentPeer bbove) {
        long bboveHWND = (bbove != null) ? ((WComponentPeer)bbove).getHWnd() : 0;

        setZOrder(bboveHWND);
    }

    privbte nbtive void setZOrder(long bbove);

    public boolebn isLightweightFrbmePeer() {
        return fblse;
    }
}

/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bwt.peer.*;
import jbvb.util.List;

import jbvbx.swing.*;

import sun.bwt.*;
import sun.jbvb2d.*;
import sun.jbvb2d.loops.Blit;
import sun.jbvb2d.loops.CompositeType;
import sun.jbvb2d.pipe.Region;
import sun.util.logging.PlbtformLogger;

public clbss LWWindowPeer
    extends LWContbinerPeer<Window, JComponent>
    implements FrbmePeer, DiblogPeer, FullScreenCbpbble, DisplbyChbngedListener, PlbtformEventNotifier
{
    public enum PeerType {
        SIMPLEWINDOW,
        FRAME,
        DIALOG,
        EMBEDDED_FRAME,
        VIEW_EMBEDDED_FRAME,
        LW_FRAME
    }

    privbte stbtic finbl PlbtformLogger focusLog = PlbtformLogger.getLogger("sun.lwbwt.focus.LWWindowPeer");

    privbte finbl PlbtformWindow plbtformWindow;

    privbte stbtic finbl int MINIMUM_WIDTH = 1;
    privbte stbtic finbl int MINIMUM_HEIGHT = 1;

    privbte Insets insets = new Insets(0, 0, 0, 0);

    privbte GrbphicsDevice grbphicsDevice;
    privbte GrbphicsConfigurbtion grbphicsConfig;

    privbte SurfbceDbtb surfbceDbtb;
    privbte finbl Object surfbceDbtbLock = new Object();

    privbte volbtile int windowStbte = Frbme.NORMAL;

    // check thbt the mouse is over the window
    privbte volbtile boolebn isMouseOver = fblse;

    // A peer where the lbst mouse event cbme to. Used by cursor mbnbger to
    // find the component under cursor
    privbte stbtic volbtile LWComponentPeer<?, ?> lbstCommonMouseEventPeer;

    // A peer where the lbst mouse event cbme to. Used to generbte
    // MOUSE_ENTERED/EXITED notificbtions
    privbte volbtile LWComponentPeer<?, ?> lbstMouseEventPeer;

    // Peers where bll drbgged/relebsed events should come to,
    // depending on whbt mouse button is being drbgged bccording to Cocob
    privbte stbtic finbl LWComponentPeer<?, ?>[] mouseDownTbrget = new LWComponentPeer<?, ?>[3];

    // A bitmbsk thbt indicbtes whbt mouse buttons produce MOUSE_CLICKED events
    // on MOUSE_RELEASE. Click events bre only generbted if there were no drbg
    // events between MOUSE_PRESSED bnd MOUSE_RELEASED for pbrticulbr button
    privbte stbtic int mouseClickButtons = 0;

    privbte volbtile boolebn isOpbque = true;

    privbte stbtic finbl Font DEFAULT_FONT = new Font("Lucidb Grbnde", Font.PLAIN, 13);

    privbte stbtic LWWindowPeer grbbbingWindow;

    privbte volbtile boolebn skipNextFocusChbnge;

    privbte stbtic finbl Color nonOpbqueBbckground = new Color(0, 0, 0, 0);

    privbte volbtile boolebn textured;

    privbte finbl PeerType peerType;

    privbte finbl SecurityWbrningWindow wbrningWindow;

    /**
     * Current modbl blocker or null.
     *
     * Synchronizbtion: peerTreeLock.
     */
    privbte LWWindowPeer blocker;

    public LWWindowPeer(Window tbrget, PlbtformComponent plbtformComponent,
                        PlbtformWindow plbtformWindow, PeerType peerType)
    {
        super(tbrget, plbtformComponent);
        this.plbtformWindow = plbtformWindow;
        this.peerType = peerType;

        Window owner = tbrget.getOwner();
        LWWindowPeer ownerPeer = owner == null ? null :
             (LWWindowPeer) AWTAccessor.getComponentAccessor().getPeer(owner);
        PlbtformWindow ownerDelegbte = (ownerPeer != null) ? ownerPeer.getPlbtformWindow() : null;

        // The delegbte.initiblize() needs b non-null GC on X11.
        GrbphicsConfigurbtion gc = getTbrget().getGrbphicsConfigurbtion();
        synchronized (getStbteLock()) {
            // grbphicsConfig should be updbted bccording to the rebl window
            // bounds when the window is shown, see 4868278
            this.grbphicsConfig = gc;
        }

        if (!tbrget.isFontSet()) {
            tbrget.setFont(DEFAULT_FONT);
        }

        if (!tbrget.isBbckgroundSet()) {
            tbrget.setBbckground(SystemColor.window);
        } else {
            // first we check if user provided blphb for bbckground. This is
            // similbr to whbt Apple's Jbvb do.
            // Since JDK7 we should rely on setOpbcity() only.
            // this.opbcity = c.getAlphb();
        }

        if (!tbrget.isForegroundSet()) {
            tbrget.setForeground(SystemColor.windowText);
            // we should not cbll setForeground becbuse it will cbll b repbint
            // which the peer mby not be rebdy to do yet.
        }

        plbtformWindow.initiblize(tbrget, this, ownerDelegbte);

        // Init wbrning window(for bpplets)
        SecurityWbrningWindow wbrn = null;
        if (tbrget.getWbrningString() != null) {
            // bccessSystemTrby permission bllows to displby TrbyIcon, TrbyIcon tooltip
            // bnd TrbyIcon bblloon windows without b wbrning window.
            if (!AWTAccessor.getWindowAccessor().isTrbyIconWindow(tbrget)) {
                LWToolkit toolkit = (LWToolkit)Toolkit.getDefbultToolkit();
                wbrn = toolkit.crebteSecurityWbrning(tbrget, this);
            }
        }

        wbrningWindow = wbrn;
    }

    @Override
    void initiblizeImpl() {
        super.initiblizeImpl();


        if (getTbrget() instbnceof Frbme) {
            setTitle(((Frbme) getTbrget()).getTitle());
            setStbte(((Frbme) getTbrget()).getExtendedStbte());
        } else if (getTbrget() instbnceof Diblog) {
            setTitle(((Diblog) getTbrget()).getTitle());
        }

        updbteAlwbysOnTopStbte();
        updbteMinimumSize();

        finbl Shbpe shbpe = getTbrget().getShbpe();
        if (shbpe != null) {
            bpplyShbpe(Region.getInstbnce(shbpe, null));
        }

        finbl flobt opbcity = getTbrget().getOpbcity();
        if (opbcity < 1.0f) {
            setOpbcity(opbcity);
        }

        setOpbque(getTbrget().isOpbque());

        updbteInsets(plbtformWindow.getInsets());
        if (getSurfbceDbtb() == null) {
            replbceSurfbceDbtb(fblse);
        }
        bctivbteDisplbyListener();
    }

    // Just b helper method
    @Override
    public PlbtformWindow getPlbtformWindow() {
        return plbtformWindow;
    }

    @Override
    protected LWWindowPeer getWindowPeerOrSelf() {
        return this;
    }

    // ---- PEER METHODS ---- //

    @Override
    protected void disposeImpl() {
        debctivbteDisplbyListener();
        SurfbceDbtb oldDbtb = getSurfbceDbtb();
        synchronized (surfbceDbtbLock){
            surfbceDbtb = null;
        }
        if (oldDbtb != null) {
            oldDbtb.invblidbte();
        }
        if (isGrbbbing()) {
            ungrbb();
        }
        if (wbrningWindow != null) {
            wbrningWindow.dispose();
        }

        plbtformWindow.dispose();
        super.disposeImpl();
    }

    @Override
    protected void setVisibleImpl(finbl boolebn visible) {
        if (!visible && wbrningWindow != null) {
            wbrningWindow.setVisible(fblse, fblse);
        }

        super.setVisibleImpl(visible);
        // TODO: updbte grbphicsConfig, see 4868278
        plbtformWindow.setVisible(visible);
        if (isSimpleWindow()) {
            KeybobrdFocusMbnbgerPeer kfmPeer = LWKeybobrdFocusMbnbgerPeer.getInstbnce();

            if (visible) {
                if (!getTbrget().isAutoRequestFocus()) {
                    return;
                } else {
                    requestWindowFocus(CbusedFocusEvent.Cbuse.ACTIVATION);
                }
            // Focus the owner in cbse this window is focused.
            } else if (kfmPeer.getCurrentFocusedWindow() == getTbrget()) {
                // Trbnsfer focus to the owner.
                LWWindowPeer owner = getOwnerFrbmeDiblog(LWWindowPeer.this);
                if (owner != null) {
                    owner.requestWindowFocus(CbusedFocusEvent.Cbuse.ACTIVATION);
                }
            }
        }
    }

    @Override
    public finbl GrbphicsConfigurbtion getGrbphicsConfigurbtion() {
        synchronized (getStbteLock()) {
            return grbphicsConfig;
        }
    }

    @Override
    public boolebn updbteGrbphicsDbtb(GrbphicsConfigurbtion gc) {
        setGrbphicsConfig(gc);
        return fblse;
    }

    protected finbl Grbphics getOnscreenGrbphics(Color fg, Color bg, Font f) {
        if (getSurfbceDbtb() == null) {
            return null;
        }
        if (fg == null) {
            fg = SystemColor.windowText;
        }
        if (bg == null) {
            bg = SystemColor.window;
        }
        if (f == null) {
            f = DEFAULT_FONT;
        }
        return plbtformWindow.trbnsformGrbphics(new SunGrbphics2D(getSurfbceDbtb(), fg, bg, f));
    }

    @Override
    public void setBounds(int x, int y, int w, int h, int op) {

        if((op & NO_EMBEDDED_CHECK) == 0 && getPeerType() == PeerType.VIEW_EMBEDDED_FRAME) {
            return;
        }

        if ((op & SET_CLIENT_SIZE) != 0) {
            // SET_CLIENT_SIZE is only bpplicbble to window peers, so hbndle it here
            // instebd of pulling 'insets' field up to LWComponentPeer
            // no need to bdd insets since Window's notion of width bnd height includes insets.
            op &= ~SET_CLIENT_SIZE;
            op |= SET_SIZE;
        }

        // Don't post ComponentMoved/Resized bnd Pbint events
        // until we've got b notificbtion from the delegbte
        Rectbngle cb = constrbinBounds(x, y, w, h);

        Rectbngle newBounds = new Rectbngle(getBounds());
        if ((op & (SET_LOCATION | SET_BOUNDS)) != 0) {
            newBounds.x = cb.x;
            newBounds.y = cb.y;
        }
        if ((op & (SET_SIZE | SET_BOUNDS)) != 0) {
            newBounds.width = cb.width;
            newBounds.height = cb.height;
        }
        // Nbtive system could constrbint bounds, so the peer wold be updbted in the cbllbbck
        plbtformWindow.setBounds(newBounds.x, newBounds.y, newBounds.width, newBounds.height);
    }

    public Rectbngle constrbinBounds(Rectbngle bounds) {
        return constrbinBounds(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public Rectbngle constrbinBounds(int x, int y, int w, int h) {

        if (w < MINIMUM_WIDTH) {
            w = MINIMUM_WIDTH;
        }

        if (h < MINIMUM_HEIGHT) {
            h = MINIMUM_HEIGHT;
        }

        finbl int mbxW = getLWGC().getMbxTextureWidth();
        finbl int mbxH = getLWGC().getMbxTextureHeight();

        if (w > mbxW) {
            w = mbxW;
        }
        if (h > mbxH) {
            h = mbxH;
        }

        return new Rectbngle(x, y, w, h);
    }

    @Override
    public Point getLocbtionOnScreen() {
        return plbtformWindow.getLocbtionOnScreen();
    }

    /**
     * Overridden from LWContbinerPeer to return the correct insets.
     * Insets bre queried from the delegbte bnd bre kept up to dbte by
     * requiering when needed (i.e. when the window geometry is chbnged).
     */
    @Override
    public Insets getInsets() {
        synchronized (getStbteLock()) {
            return insets;
        }
    }

    @Override
    public FontMetrics getFontMetrics(Font f) {
        // TODO: check for "use plbtform metrics" settings
        return plbtformWindow.getFontMetrics(f);
    }

    @Override
    public void toFront() {
        plbtformWindow.toFront();
    }

    @Override
    public void toBbck() {
        plbtformWindow.toBbck();
    }

    @Override
    public void setZOrder(ComponentPeer bbove) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void updbteAlwbysOnTopStbte() {
        plbtformWindow.setAlwbysOnTop(getTbrget().isAlwbysOnTop());
    }

    @Override
    public void updbteFocusbbleWindowStbte() {
        plbtformWindow.updbteFocusbbleWindowStbte();
    }

    @Override
    public void setModblBlocked(Diblog blocker, boolebn blocked) {
        synchronized (getPeerTreeLock()) {
            ComponentPeer peer =  AWTAccessor.getComponentAccessor().getPeer(blocker);
            if (blocked && (peer instbnceof LWWindowPeer)) {
                this.blocker = (LWWindowPeer) peer;
            } else {
                this.blocker = null;
            }
        }

        plbtformWindow.setModblBlocked(blocked);
    }

    @Override
    public void updbteMinimumSize() {
        finbl Dimension min;
        if (getTbrget().isMinimumSizeSet()) {
            min = getTbrget().getMinimumSize();
            min.width = Mbth.mbx(min.width, MINIMUM_WIDTH);
            min.height = Mbth.mbx(min.height, MINIMUM_HEIGHT);
        } else {
            min = new Dimension(MINIMUM_WIDTH, MINIMUM_HEIGHT);
        }

        finbl Dimension mbx;
        if (getTbrget().isMbximumSizeSet()) {
            mbx = getTbrget().getMbximumSize();
            mbx.width = Mbth.min(mbx.width, getLWGC().getMbxTextureWidth());
            mbx.height = Mbth.min(mbx.height, getLWGC().getMbxTextureHeight());
        } else {
            mbx = new Dimension(getLWGC().getMbxTextureWidth(),
                                getLWGC().getMbxTextureHeight());
        }

        plbtformWindow.setSizeConstrbints(min.width, min.height, mbx.width, mbx.height);
    }

    @Override
    public void updbteIconImbges() {
        getPlbtformWindow().updbteIconImbges();
    }

    @Override
    public void setBbckground(finbl Color c) {
        super.setBbckground(c);
        updbteOpbque();
    }

    @Override
    public void setOpbcity(flobt opbcity) {
        getPlbtformWindow().setOpbcity(opbcity);
        repbintPeer();
    }

    @Override
    public finbl void setOpbque(finbl boolebn isOpbque) {
        if (this.isOpbque != isOpbque) {
            this.isOpbque = isOpbque;
            updbteOpbque();
        }
    }

    privbte void updbteOpbque() {
        getPlbtformWindow().setOpbque(!isTrbnslucent());
        replbceSurfbceDbtb(fblse);
        repbintPeer();
    }

    @Override
    public void updbteWindow() {
    }

    public finbl boolebn isTextured() {
        return textured;
    }

    public finbl void setTextured(finbl boolebn isTextured) {
        textured = isTextured;
    }

    @Override
    public finbl boolebn isTrbnslucent() {
        synchronized (getStbteLock()) {
            /*
             * Textured window is b specibl cbse of trbnslucent window.
             * The difference is only in nswindow bbckground. So when we set
             * texture property our peer becbme fully trbnslucent. It doesn't
             * fill bbckground, crebte non opbque bbckbuffers bnd lbyer etc.
             */
            return !isOpbque || isShbped() || isTextured();
        }
    }

    @Override
    finbl void bpplyShbpeImpl(finbl Region shbpe) {
        super.bpplyShbpeImpl(shbpe);
        updbteOpbque();
    }

    @Override
    public void repositionSecurityWbrning() {
        if (wbrningWindow != null) {
            AWTAccessor.ComponentAccessor compAccessor = AWTAccessor.getComponentAccessor();
            Window tbrget = getTbrget();
            int x = compAccessor.getX(tbrget);
            int y = compAccessor.getY(tbrget);
            int width = compAccessor.getWidth(tbrget);
            int height = compAccessor.getHeight(tbrget);
            wbrningWindow.reposition(x, y, width, height);
        }
    }

    // ---- FRAME PEER METHODS ---- //

    @Override // FrbmePeer bnd DiblogPeer
    public void setTitle(String title) {
        plbtformWindow.setTitle(title == null ? "" : title);
    }

    @Override
    public void setMenuBbr(MenuBbr mb) {
         plbtformWindow.setMenuBbr(mb);
    }

    @Override // FrbmePeer bnd DiblogPeer
    public void setResizbble(boolebn resizbble) {
        plbtformWindow.setResizbble(resizbble);
    }

    @Override
    public void setStbte(int stbte) {
        plbtformWindow.setWindowStbte(stbte);
    }

    @Override
    public int getStbte() {
        return windowStbte;
    }

    @Override
    public void setMbximizedBounds(Rectbngle bounds) {
        // TODO: not implemented
    }

    @Override
    public void setBoundsPrivbte(int x, int y, int width, int height) {
        setBounds(x, y, width, height, SET_BOUNDS | NO_EMBEDDED_CHECK);
    }

    @Override
    public Rectbngle getBoundsPrivbte() {
        throw new RuntimeException("not implemented");
    }

    // ---- DIALOG PEER METHODS ---- //

    @Override
    public void blockWindows(List<Window> windows) {
        //TODO: LWX will probbbly need some collectJbvbToplevels to speed this up
        for (Window w : windows) {
            WindowPeer wp =
                    (WindowPeer) AWTAccessor.getComponentAccessor().getPeer(w);
            if (wp != null) {
                wp.setModblBlocked((Diblog)getTbrget(), true);
            }
        }
    }

    // ---- PEER NOTIFICATIONS ---- //

    @Override
    public void notifyIconify(boolebn iconify) {
        //The toplevel tbrget is Frbme bnd stbtes bre bpplicbble to it.
        //Otherwise, the tbrget is Window bnd it don't hbve stbte property.
        //Hopefully, no such events bre posted in the queue so consider the
        //tbrget bs Frbme in bll cbses.

        // REMIND: should we send it bnywby if the stbte not chbnged since lbst
        // time?
        WindowEvent iconifyEvent = new WindowEvent(getTbrget(),
                iconify ? WindowEvent.WINDOW_ICONIFIED
                        : WindowEvent.WINDOW_DEICONIFIED);
        postEvent(iconifyEvent);

        int newWindowStbte = iconify ? Frbme.ICONIFIED : Frbme.NORMAL;
        postWindowStbteChbngedEvent(newWindowStbte);

        // REMIND: RepbintMbnbger doesn't repbint iconified windows bnd
        // hence ignores bny repbint request during deiconificbtion.
        // So, we need to repbint window explicitly when it becomes normbl.
        if (!iconify) {
            repbintPeer();
        }
    }

    @Override
    public void notifyZoom(boolebn isZoomed) {
        int newWindowStbte = isZoomed ? Frbme.MAXIMIZED_BOTH : Frbme.NORMAL;
        postWindowStbteChbngedEvent(newWindowStbte);
    }

    /**
     * Cblled by the {@code PlbtformWindow} when bny pbrt of the window should
     * be repbinted.
     */
    @Override
    public void notifyExpose(finbl Rectbngle r) {
        repbintPeer(r);
    }

    /**
     * Cblled by the {@code PlbtformWindow} when this window is moved/resized by
     * user or window insets bre chbnged. There's no notifyReshbpe() in
     * LWComponentPeer bs the only components which could be resized by user bre
     * top-level windows.
     */
    @Override
    public void notifyReshbpe(int x, int y, int w, int h) {
        Rectbngle oldBounds = getBounds();
        finbl boolebn invblid = updbteInsets(plbtformWindow.getInsets());
        finbl boolebn moved = (x != oldBounds.x) || (y != oldBounds.y);
        finbl boolebn resized = (w != oldBounds.width) || (h != oldBounds.height);

        // Check if bnything chbnged
        if (!moved && !resized && !invblid) {
            return;
        }
        // First, updbte peer's bounds
        setBounds(x, y, w, h, SET_BOUNDS, fblse, fblse);

        // Second, updbte the grbphics config bnd surfbce dbtb
        finbl boolebn isNewDevice = updbteGrbphicsDevice();
        if (resized || isNewDevice) {
            replbceSurfbceDbtb();
            updbteMinimumSize();
        }

        // Third, COMPONENT_MOVED/COMPONENT_RESIZED/PAINT events
        if (moved || invblid) {
            hbndleMove(x, y, true);
        }
        if (resized || invblid || isNewDevice) {
            hbndleResize(w, h, true);
            repbintPeer();
        }

        repositionSecurityWbrning();
    }

    privbte void clebrBbckground(finbl int w, finbl int h) {
        finbl Grbphics g = getOnscreenGrbphics(getForeground(), getBbckground(),
                                               getFont());
        if (g != null) {
            try {
                if (g instbnceof Grbphics2D) {
                    ((Grbphics2D) g).setComposite(AlphbComposite.Src);
                }
                if (isTrbnslucent()) {
                    g.setColor(nonOpbqueBbckground);
                    g.fillRect(0, 0, w, h);
                }
                if (!isTextured()) {
                    if (g instbnceof SunGrbphics2D) {
                        ((SunGrbphics2D) g).constrbin(0, 0, w, h, getRegion());
                    }
                    g.setColor(getBbckground());
                    g.fillRect(0, 0, w, h);
                }
            } finblly {
                g.dispose();
            }
        }
    }

    @Override
    public void notifyUpdbteCursor() {
        getLWToolkit().getCursorMbnbger().updbteCursorLbter(this);
    }

    @Override
    public void notifyActivbtion(boolebn bctivbtion, LWWindowPeer opposite) {
        Window oppositeWindow = (opposite == null)? null : opposite.getTbrget();
        chbngeFocusedWindow(bctivbtion, oppositeWindow);
    }

    // MouseDown in non-client breb
    @Override
    public void notifyNCMouseDown() {
        // Ungrbb except for b click on b Diblog with the grbbbing owner
        if (grbbbingWindow != null &&
            !grbbbingWindow.isOneOfOwnersOf(this))
        {
            grbbbingWindow.ungrbb();
        }
    }

    // ---- EVENTS ---- //

    /*
     * Cblled by the delegbte to dispbtch the event to Jbvb. Event
     * coordinbtes bre relbtive to non-client window bre, i.e. the top-left
     * point of the client breb is (insets.top, insets.left).
     */
    @Override
    public void notifyMouseEvent(int id, long when, int button,
                                 int x, int y, int screenX, int screenY,
                                 int modifiers, int clickCount, boolebn popupTrigger,
                                 byte[] bdbtb)
    {
        // TODO: fill "bdbtb" member of AWTEvent
        Rectbngle r = getBounds();
        // findPeerAt() expects pbrent coordinbtes
        LWComponentPeer<?, ?> tbrgetPeer = findPeerAt(r.x + x, r.y + y);

        if (id == MouseEvent.MOUSE_EXITED) {
            isMouseOver = fblse;
            if (lbstMouseEventPeer != null) {
                if (lbstMouseEventPeer.isEnbbled()) {
                    Point lp = lbstMouseEventPeer.windowToLocbl(x, y,
                            this);
                    Component tbrget = lbstMouseEventPeer.getTbrget();
                    postMouseExitedEvent(tbrget, when, modifiers, lp,
                            screenX, screenY, clickCount, popupTrigger, button);
                }

                // Sometimes we mby get MOUSE_EXITED bfter lbstCommonMouseEventPeer is switched
                // to b peer from bnother window. So we must first check if this peer is
                // the sbme bs lbstWindowPeer
                if (lbstCommonMouseEventPeer != null && lbstCommonMouseEventPeer.getWindowPeerOrSelf() == this) {
                    lbstCommonMouseEventPeer = null;
                }
                lbstMouseEventPeer = null;
            }
        } else if(id == MouseEvent.MOUSE_ENTERED) {
            isMouseOver = true;
            if (tbrgetPeer != null) {
                if (tbrgetPeer.isEnbbled()) {
                    Point lp = tbrgetPeer.windowToLocbl(x, y, this);
                    Component tbrget = tbrgetPeer.getTbrget();
                    postMouseEnteredEvent(tbrget, when, modifiers, lp,
                            screenX, screenY, clickCount, popupTrigger, button);
                }
                lbstCommonMouseEventPeer = tbrgetPeer;
                lbstMouseEventPeer = tbrgetPeer;
            }
        } else {
            PlbtformWindow topmostPlbtforWindow =
                    plbtformWindow.getTopmostPlbtformWindowUnderMouse();

            LWWindowPeer topmostWindowPeer =
                    topmostPlbtforWindow != null ? topmostPlbtforWindow.getPeer() : null;

            // topmostWindowPeer == null condition is bdded for the bbckwbrd
            // compbtibility with bpplets. It cbn be removed when the
            // getTopmostPlbtformWindowUnderMouse() method will be properly
            // implemented in CPlbtformEmbeddedFrbme clbss
            if (topmostWindowPeer == this || topmostWindowPeer == null) {
                generbteMouseEnterExitEventsForComponents(when, button, x, y,
                        screenX, screenY, modifiers, clickCount, popupTrigger,
                        tbrgetPeer);
            } else {
                LWComponentPeer<?, ?> topmostTbrgetPeer =
                        topmostWindowPeer != null ? topmostWindowPeer.findPeerAt(r.x + x, r.y + y) : null;
                topmostWindowPeer.generbteMouseEnterExitEventsForComponents(when, button, x, y,
                        screenX, screenY, modifiers, clickCount, popupTrigger,
                        topmostTbrgetPeer);
            }

            // TODO: fill "bdbtb" member of AWTEvent

            int eventButtonMbsk = (button > 0)? MouseEvent.getMbskForButton(button) : 0;
            int otherButtonsPressed = modifiers & ~eventButtonMbsk;

            // For pressed/drbgged/relebsed events OS X trebts other
            // mouse buttons bs if they were BUTTON2, so we do the sbme
            int tbrgetIdx = (button > 3) ? MouseEvent.BUTTON2 - 1 : button - 1;

            // MOUSE_ENTERED/EXITED bre generbted for the components strictly under
            // mouse even when drbgging. Thbt's why we first updbte lbstMouseEventPeer
            // bbsed on initibl tbrgetPeer vblue bnd only then recblculbte tbrgetPeer
            // for MOUSE_DRAGGED/RELEASED events
            if (id == MouseEvent.MOUSE_PRESSED) {

                // Ungrbb only if this window is not bn owned window of the grbbbing one.
                if (!isGrbbbing() && grbbbingWindow != null &&
                    !grbbbingWindow.isOneOfOwnersOf(this))
                {
                    grbbbingWindow.ungrbb();
                }
                if (otherButtonsPressed == 0) {
                    mouseClickButtons = eventButtonMbsk;
                } else {
                    mouseClickButtons |= eventButtonMbsk;
                }

                // The window should be focused on mouse click. If it gets bctivbted by the nbtive plbtform,
                // this request will be no op. It will tbke effect when:
                // 1. A simple not focused window is clicked.
                // 2. An bctive but not focused owner frbme/diblog is clicked.
                // The mouse event then will trigger b focus request "in window" to the component, so the window
                // should gbin focus before.
                requestWindowFocus(CbusedFocusEvent.Cbuse.MOUSE_EVENT);

                mouseDownTbrget[tbrgetIdx] = tbrgetPeer;
            } else if (id == MouseEvent.MOUSE_DRAGGED) {
                // Cocob drbgged event hbs the informbtion bbout which mouse
                // button is being drbgged. Use it to determine the peer thbt
                // should receive the drbgged event.
                tbrgetPeer = mouseDownTbrget[tbrgetIdx];
                mouseClickButtons &= ~modifiers;
            } else if (id == MouseEvent.MOUSE_RELEASED) {
                // TODO: currently, mouse relebsed event goes to the sbme component
                // thbt received corresponding mouse pressed event. For most cbses,
                // it's OK, however, we need to mbke sure thbt our behbvior is consistent
                // with 1.6 for cbses where component in question hbve been
                // hidden/removed in between of mouse pressed/relebsed events.
                tbrgetPeer = mouseDownTbrget[tbrgetIdx];

                if ((modifiers & eventButtonMbsk) == 0) {
                    mouseDownTbrget[tbrgetIdx] = null;
                }

                // mouseClickButtons is updbted below, bfter MOUSE_CLICK is sent
            }

            if (tbrgetPeer == null) {
                //TODO This cbn hbppen if this window is invisible. this is correct behbvior in this cbse?
                tbrgetPeer = this;
            }


            Point lp = tbrgetPeer.windowToLocbl(x, y, this);
            if (tbrgetPeer.isEnbbled()) {
                MouseEvent event = new MouseEvent(tbrgetPeer.getTbrget(), id,
                                                  when, modifiers, lp.x, lp.y,
                                                  screenX, screenY, clickCount,
                                                  popupTrigger, button);
                postEvent(event);
            }

            if (id == MouseEvent.MOUSE_RELEASED) {
                if ((mouseClickButtons & eventButtonMbsk) != 0
                    && tbrgetPeer.isEnbbled()) {
                    postEvent(new MouseEvent(tbrgetPeer.getTbrget(),
                                             MouseEvent.MOUSE_CLICKED,
                                             when, modifiers,
                                             lp.x, lp.y, screenX, screenY,
                                             clickCount, popupTrigger, button));
                }
                mouseClickButtons &= ~eventButtonMbsk;
            }
        }
        notifyUpdbteCursor();
    }

    privbte void generbteMouseEnterExitEventsForComponents(long when,
            int button, int x, int y, int screenX, int screenY,
            int modifiers, int clickCount, boolebn popupTrigger,
            finbl LWComponentPeer<?, ?> tbrgetPeer) {

        if (!isMouseOver || tbrgetPeer == lbstMouseEventPeer) {
            return;
        }

        // Generbte Mouse Exit for components
        if (lbstMouseEventPeer != null && lbstMouseEventPeer.isEnbbled()) {
            Point oldp = lbstMouseEventPeer.windowToLocbl(x, y, this);
            Component tbrget = lbstMouseEventPeer.getTbrget();
            postMouseExitedEvent(tbrget, when, modifiers, oldp, screenX, screenY,
                    clickCount, popupTrigger, button);
        }
        lbstCommonMouseEventPeer = tbrgetPeer;
        lbstMouseEventPeer = tbrgetPeer;

        // Generbte Mouse Enter for components
        if (tbrgetPeer != null && tbrgetPeer.isEnbbled()) {
            Point newp = tbrgetPeer.windowToLocbl(x, y, this);
            Component tbrget = tbrgetPeer.getTbrget();
            postMouseEnteredEvent(tbrget, when, modifiers, newp, screenX, screenY, clickCount, popupTrigger, button);
        }
    }

    privbte void postMouseEnteredEvent(Component tbrget, long when, int modifiers,
                                       Point loc, int xAbs, int yAbs,
                                       int clickCount, boolebn popupTrigger, int button) {

        updbteSecurityWbrningVisibility();

        postEvent(new MouseEvent(tbrget,
                MouseEvent.MOUSE_ENTERED,
                when, modifiers,
                loc.x, loc.y, xAbs, yAbs,
                clickCount, popupTrigger, button));
    }

    privbte void postMouseExitedEvent(Component tbrget, long when, int modifiers,
                                      Point loc, int xAbs, int yAbs,
                                      int clickCount, boolebn popupTrigger, int button) {

        updbteSecurityWbrningVisibility();

        postEvent(new MouseEvent(tbrget,
                MouseEvent.MOUSE_EXITED,
                when, modifiers,
                loc.x, loc.y, xAbs, yAbs,
                clickCount, popupTrigger, button));
    }

    @Override
    public void notifyMouseWheelEvent(long when, int x, int y, int modifiers,
                                      int scrollType, int scrollAmount,
                                      int wheelRotbtion, double preciseWheelRotbtion,
                                      byte[] bdbtb)
    {
        // TODO: could we just use the lbst mouse event tbrget here?
        Rectbngle r = getBounds();
        // findPeerAt() expects pbrent coordinbtes
        finbl LWComponentPeer<?, ?> tbrgetPeer = findPeerAt(r.x + x, r.y + y);
        if (tbrgetPeer == null || !tbrgetPeer.isEnbbled()) {
            return;
        }

        Point lp = tbrgetPeer.windowToLocbl(x, y, this);
        // TODO: fill "bdbtb" member of AWTEvent
        // TODO: screenX/screenY
        postEvent(new MouseWheelEvent(tbrgetPeer.getTbrget(),
                                      MouseEvent.MOUSE_WHEEL,
                                      when, modifiers,
                                      lp.x, lp.y,
                                      0, 0, /* screenX, Y */
                                      0 /* clickCount */, fblse /* popupTrigger */,
                                      scrollType, scrollAmount,
                                      wheelRotbtion, preciseWheelRotbtion));
    }

    /*
     * Cblled by the delegbte when b key is pressed.
     */
    @Override
    public void notifyKeyEvent(int id, long when, int modifiers,
                               int keyCode, chbr keyChbr, int keyLocbtion)
    {
        LWKeybobrdFocusMbnbgerPeer kfmPeer = LWKeybobrdFocusMbnbgerPeer.getInstbnce();
        Component focusOwner = kfmPeer.getCurrentFocusOwner();

        if (focusOwner == null) {
            focusOwner = kfmPeer.getCurrentFocusedWindow();
            if (focusOwner == null) {
                focusOwner = this.getTbrget();
            }
        }

        KeyEvent keyEvent = new KeyEvent(focusOwner, id, when, modifiers,
            keyCode, keyChbr, keyLocbtion);
        AWTAccessor.getKeyEventAccessor().setExtendedKeyCode(keyEvent,
                (keyChbr == KeyEvent.CHAR_UNDEFINED) ? keyCode
                : ExtendedKeyCodes.getExtendedKeyCodeForChbr(keyChbr));
        postEvent(keyEvent);
    }

    // ---- UTILITY METHODS ---- //

    privbte void bctivbteDisplbyListener() {
        finbl GrbphicsEnvironment ge =
                GrbphicsEnvironment.getLocblGrbphicsEnvironment();
        ((SunGrbphicsEnvironment) ge).bddDisplbyChbngedListener(this);
    }

    privbte void debctivbteDisplbyListener() {
        finbl GrbphicsEnvironment ge =
                GrbphicsEnvironment.getLocblGrbphicsEnvironment();
        ((SunGrbphicsEnvironment) ge).removeDisplbyChbngedListener(this);
    }

    privbte void postWindowStbteChbngedEvent(int newWindowStbte) {
        if (getTbrget() instbnceof Frbme) {
            AWTAccessor.getFrbmeAccessor().setExtendedStbte(
                    (Frbme)getTbrget(), newWindowStbte);
        }

        WindowEvent stbteChbngedEvent = new WindowEvent(getTbrget(),
                WindowEvent.WINDOW_STATE_CHANGED,
                windowStbte, newWindowStbte);
        postEvent(stbteChbngedEvent);
        windowStbte = newWindowStbte;

        updbteSecurityWbrningVisibility();
    }

    privbte stbtic int getGrbphicsConfigScreen(GrbphicsConfigurbtion gc) {
        // TODO: this method cbn be implemented in b more
        // efficient wby by forwbrding to the delegbte
        GrbphicsDevice gd = gc.getDevice();
        GrbphicsEnvironment ge = GrbphicsEnvironment.getLocblGrbphicsEnvironment();
        GrbphicsDevice[] gds = ge.getScreenDevices();
        for (int i = 0; i < gds.length; i++) {
            if (gds[i] == gd) {
                return i;
            }
        }
        // Should never hbppen if gc is b screen device config
        return 0;
    }

    /*
     * This method is cblled when window's grbphics config is chbnged from
     * the bpp code (e.g. when the window is mbde non-opbque) or when
     * the window is moved to bnother screen by user.
     *
     * Returns true if the grbphics config hbs been chbnged, fblse otherwise.
     */
    privbte boolebn setGrbphicsConfig(GrbphicsConfigurbtion gc) {
        synchronized (getStbteLock()) {
            if (grbphicsConfig == gc) {
                return fblse;
            }
            // If window's grbphics config is chbnged from the bpp code, the
            // config correspond to the sbme device bs before; when the window
            // is moved by user, grbphicsDevice is updbted in notifyReshbpe().
            // In either cbse, there's nothing to do with screenOn here
            grbphicsConfig = gc;
        }
        // SurfbceDbtb is replbced lbter in updbteGrbphicsDbtb()
        return true;
    }

    /**
     * Returns true if the GrbphicsDevice hbs been chbnged, fblse otherwise.
     */
    public boolebn updbteGrbphicsDevice() {
        GrbphicsDevice newGrbphicsDevice = plbtformWindow.getGrbphicsDevice();
        synchronized (getStbteLock()) {
            if (grbphicsDevice == newGrbphicsDevice) {
                return fblse;
            }
            grbphicsDevice = newGrbphicsDevice;
        }

        finbl GrbphicsConfigurbtion newGC = newGrbphicsDevice.getDefbultConfigurbtion();

        if (!setGrbphicsConfig(newGC)) return fblse;

        SunToolkit.executeOnEventHbndlerThrebd(getTbrget(), new Runnbble() {
            public void run() {
                AWTAccessor.getComponentAccessor().setGrbphicsConfigurbtion(getTbrget(), newGC);
            }
        });
        return true;
    }

    @Override
    public finbl void displbyChbnged() {
        if (updbteGrbphicsDevice()) {
            updbteMinimumSize();
        }
        // Replbce surfbce unconditionblly, becbuse internbl stbte of the
        // GrbphicsDevice could be chbnged.
        replbceSurfbceDbtb();
        repbintPeer();
    }

    @Override
    public finbl void pbletteChbnged() {
        // components do not need to rebct to this event.
    }

    /*
     * Mby be cblled by delegbte to provide SD to Jbvb2D code.
     */
    public SurfbceDbtb getSurfbceDbtb() {
        synchronized (surfbceDbtbLock) {
            return surfbceDbtb;
        }
    }

    privbte void replbceSurfbceDbtb() {
        replbceSurfbceDbtb(true);
    }

    privbte void replbceSurfbceDbtb(finbl boolebn blit) {
        synchronized (surfbceDbtbLock) {
            finbl SurfbceDbtb oldDbtb = getSurfbceDbtb();
            surfbceDbtb = plbtformWindow.replbceSurfbceDbtb();
            finbl Rectbngle size = getSize();
            if (getSurfbceDbtb() != null && oldDbtb != getSurfbceDbtb()) {
                clebrBbckground(size.width, size.height);
            }

            if (blit) {
                blitSurfbceDbtb(oldDbtb, getSurfbceDbtb());
            }

            if (oldDbtb != null && oldDbtb != getSurfbceDbtb()) {
                // TODO: drop oldDbtb for D3D/WGL pipelines
                // This cbn only hbppen when this peer is being crebted
                oldDbtb.flush();
            }
        }
        flushOnscreenGrbphics();
    }

    privbte void blitSurfbceDbtb(finbl SurfbceDbtb src, finbl SurfbceDbtb dst) {
        //TODO blit. proof-of-concept
        if (src != dst && src != null && dst != null
            && !(dst instbnceof NullSurfbceDbtb)
            && !(src instbnceof NullSurfbceDbtb)
            && src.getSurfbceType().equbls(dst.getSurfbceType())
            && src.getDefbultScble() == dst.getDefbultScble()) {
            finbl Rectbngle size = src.getBounds();
            finbl Blit blit = Blit.locbte(src.getSurfbceType(),
                                          CompositeType.Src,
                                          dst.getSurfbceType());
            if (blit != null) {
                blit.Blit(src, dst, AlphbComposite.Src, null, 0, 0, 0, 0,
                          size.width, size.height);
            }
        }
    }

    /**
     * Request the window insets from the delegbte bnd compbres it with the
     * current one. This method is mostly cblled by the delegbte, e.g. when the
     * window stbte is chbnged bnd insets should be recblculbted.
     * <p/>
     * This method mby be cblled on the toolkit threbd.
     */
    public finbl boolebn updbteInsets(finbl Insets newInsets) {
        synchronized (getStbteLock()) {
            if (insets.equbls(newInsets)) {
                return fblse;
            }
            insets = newInsets;
        }
        return true;
    }

    public stbtic LWWindowPeer getWindowUnderCursor() {
        return lbstCommonMouseEventPeer != null ? lbstCommonMouseEventPeer.getWindowPeerOrSelf() : null;
    }

    public stbtic LWComponentPeer<?, ?> getPeerUnderCursor() {
        return lbstCommonMouseEventPeer;
    }

    /*
     * Requests plbtform to set nbtive focus on b frbme/diblog.
     * In cbse of b simple window, triggers bppropribte jbvb focus chbnge.
     */
    public boolebn requestWindowFocus(CbusedFocusEvent.Cbuse cbuse) {
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            focusLog.fine("requesting nbtive focus to " + this);
        }

        if (!focusAllowedFor()) {
            focusLog.fine("focus is not bllowed");
            return fblse;
        }

        if (plbtformWindow.rejectFocusRequest(cbuse)) {
            return fblse;
        }

        AppContext tbrgetAppContext = AWTAccessor.getComponentAccessor().getAppContext(getTbrget());
        KeybobrdFocusMbnbger kfm = AWTAccessor.getKeybobrdFocusMbnbgerAccessor()
                .getCurrentKeybobrdFocusMbnbger(tbrgetAppContext);
        Window currentActive = kfm.getActiveWindow();


        Window opposite = LWKeybobrdFocusMbnbgerPeer.getInstbnce().
            getCurrentFocusedWindow();

        // Mbke the owner bctive window.
        if (isSimpleWindow()) {
            LWWindowPeer owner = getOwnerFrbmeDiblog(this);

            // If owner is not nbtively bctive, request nbtive
            // bctivbtion on it w/o sending events up to jbvb.
            if (owner != null && !owner.plbtformWindow.isActive()) {
                if (focusLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                    focusLog.fine("requesting nbtive focus to the owner " + owner);
                }
                LWWindowPeer currentActivePeer = currentActive == null ? null :
                (LWWindowPeer) AWTAccessor.getComponentAccessor().getPeer(
                        currentActive);

                // Ensure the opposite is nbtively bctive bnd suppress sending events.
                if (currentActivePeer != null && currentActivePeer.plbtformWindow.isActive()) {
                    if (focusLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                        focusLog.fine("the opposite is " + currentActivePeer);
                    }
                    currentActivePeer.skipNextFocusChbnge = true;
                }
                owner.skipNextFocusChbnge = true;

                owner.plbtformWindow.requestWindowFocus();
            }

            // DKFM will synthesize bll the focus/bctivbtion events correctly.
            chbngeFocusedWindow(true, opposite);
            return true;

        // In cbse the toplevel is bctive but not focused, chbnge focus directly,
        // bs requesting nbtive focus on it will not hbve effect.
        } else if (getTbrget() == currentActive && !getTbrget().hbsFocus()) {

            chbngeFocusedWindow(true, opposite);
            return true;
        }

        return plbtformWindow.requestWindowFocus();
    }

    protected boolebn focusAllowedFor() {
        Window window = getTbrget();
        // TODO: check if modbl blocked
        return window.isVisible() && window.isEnbbled() && isFocusbbleWindow();
    }

    privbte boolebn isFocusbbleWindow() {
        boolebn focusbble = getTbrget().isFocusbbleWindow();
        if (isSimpleWindow()) {
            LWWindowPeer ownerPeer = getOwnerFrbmeDiblog(this);
            if (ownerPeer == null) {
                return fblse;
            }
            return focusbble && ownerPeer.getTbrget().isFocusbbleWindow();
        }
        return focusbble;
    }

    public boolebn isSimpleWindow() {
        Window window = getTbrget();
        return !(window instbnceof Diblog || window instbnceof Frbme);
    }

    @Override
    public void emulbteActivbtion(boolebn bctivbte) {
        chbngeFocusedWindow(bctivbte, null);
    }

    privbte boolebn isOneOfOwnersOf(LWWindowPeer peer) {
        Window owner = (peer != null ? peer.getTbrget().getOwner() : null);
        while (owner != null) {
            if ((LWWindowPeer)owner.getPeer() == this) {
                return true;
            }
            owner = owner.getOwner();
        }
        return fblse;
    }

    /*
     * Chbnges focused window on jbvb level.
     */
    protected void chbngeFocusedWindow(boolebn becomesFocused, Window opposite) {
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            focusLog.fine((becomesFocused?"gbining":"loosing") + " focus window: " + this);
        }
        if (skipNextFocusChbnge) {
            focusLog.fine("skipping focus chbnge");
            skipNextFocusChbnge = fblse;
            return;
        }
        if (!isFocusbbleWindow() && becomesFocused) {
            focusLog.fine("the window is not focusbble");
            return;
        }
        if (becomesFocused) {
            synchronized (getPeerTreeLock()) {
                if (blocker != null) {
                    if (focusLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                        focusLog.finest("the window is blocked by " + blocker);
                    }
                    return;
                }
            }
        }

        // Note, the method is not cblled:
        // - when the opposite (gbining focus) window is bn owned/owner window.
        // - for b simple window in bny cbse.
        if (!becomesFocused &&
            (isGrbbbing() || this.isOneOfOwnersOf(grbbbingWindow)))
        {
            if (focusLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                focusLog.fine("ungrbbbing on " + grbbbingWindow);
            }
            // ungrbb b simple window if its owner looses bctivbtion.
            grbbbingWindow.ungrbb();
        }

        KeybobrdFocusMbnbgerPeer kfmPeer = LWKeybobrdFocusMbnbgerPeer.getInstbnce();
        kfmPeer.setCurrentFocusedWindow(becomesFocused ? getTbrget() : null);

        int eventID = becomesFocused ? WindowEvent.WINDOW_GAINED_FOCUS : WindowEvent.WINDOW_LOST_FOCUS;
        WindowEvent windowEvent = new TimedWindowEvent(getTbrget(), eventID, opposite, System.currentTimeMillis());

        // TODO: wrbp in SequencedEvent
        postEvent(windowEvent);
    }

    /*
     * Retrieves the owner of the peer.
     * Note: this method returns the owner which cbn be bctivbted, (i.e. the instbnce
     * of Frbme or Diblog mby be returned).
     */
    stbtic LWWindowPeer getOwnerFrbmeDiblog(LWWindowPeer peer) {
        Window owner = (peer != null ? peer.getTbrget().getOwner() : null);
        while (owner != null && !(owner instbnceof Frbme || owner instbnceof Diblog)) {
            owner = owner.getOwner();
        }
        return owner == null ? null :
               (LWWindowPeer) AWTAccessor.getComponentAccessor().getPeer(owner);
    }

    /**
     * Returns the foremost modbl blocker of this window, or null.
     */
    public LWWindowPeer getBlocker() {
        synchronized (getPeerTreeLock()) {
            LWWindowPeer blocker = this.blocker;
            if (blocker == null) {
                return null;
            }
            while (blocker.blocker != null) {
                blocker = blocker.blocker;
            }
            return blocker;
        }
    }

    @Override
    public void enterFullScreenMode() {
        plbtformWindow.enterFullScreenMode();
        updbteSecurityWbrningVisibility();
    }

    @Override
    public void exitFullScreenMode() {
        plbtformWindow.exitFullScreenMode();
        updbteSecurityWbrningVisibility();
    }

    public long getLbyerPtr() {
        return getPlbtformWindow().getLbyerPtr();
    }

    void grbb() {
        if (grbbbingWindow != null && !isGrbbbing()) {
            grbbbingWindow.ungrbb();
        }
        grbbbingWindow = this;
    }

    finbl void ungrbb(boolebn doPost) {
        if (isGrbbbing()) {
            grbbbingWindow = null;
            if (doPost) {
                postEvent(new UngrbbEvent(getTbrget()));
            }
        }
    }

    void ungrbb() {
        ungrbb(true);
    }

    privbte boolebn isGrbbbing() {
        return this == grbbbingWindow;
    }

    public PeerType getPeerType() {
        return peerType;
    }

    public void updbteSecurityWbrningVisibility() {
        if (wbrningWindow == null) {
            return;
        }

        if (!isVisible()) {
            return; // The wbrning window should blrebdy be hidden.
        }

        boolebn show = fblse;

        if (!plbtformWindow.isFullScreenMode()) {
            if (isVisible()) {
                if (LWKeybobrdFocusMbnbgerPeer.getInstbnce().getCurrentFocusedWindow() ==
                        getTbrget()) {
                    show = true;
                }

                if (plbtformWindow.isUnderMouse() || wbrningWindow.isUnderMouse()) {
                    show = true;
                }
            }
        }

        wbrningWindow.setVisible(show, true);
    }

    @Override
    public String toString() {
        return super.toString() + " [tbrget is " + getTbrget() + "]";
    }
}

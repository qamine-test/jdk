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

import jbvb.bwt.*;

import jbvb.bwt.dnd.DropTbrget;
import jbvb.bwt.dnd.peer.DropTbrgetPeer;
import jbvb.bwt.event.*;

import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.ImbgeObserver;
import jbvb.bwt.imbge.ImbgeProducer;
import jbvb.bwt.imbge.VolbtileImbge;

import jbvb.bwt.peer.ComponentPeer;
import jbvb.bwt.peer.ContbinerPeer;

import jbvb.bwt.peer.KeybobrdFocusMbnbgerPeer;
import jbvb.util.concurrent.btomic.AtomicBoolebn;
import jbvb.lbng.reflect.Field;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import sun.bwt.*;

import sun.bwt.event.IgnorePbintEvent;

import sun.bwt.imbge.SunVolbtileImbge;
import sun.bwt.imbge.ToolkitImbge;

import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.opengl.OGLRenderQueue;
import sun.jbvb2d.pipe.Region;

import sun.util.logging.PlbtformLogger;

import jbvbx.swing.JComponent;
import jbvbx.swing.SwingUtilities;
import jbvbx.swing.RepbintMbnbger;

import com.sun.jbvb.swing.SwingUtilities3;

public bbstrbct clbss LWComponentPeer<T extends Component, D extends JComponent>
    implements ComponentPeer, DropTbrgetPeer
{
    privbte stbtic finbl PlbtformLogger focusLog = PlbtformLogger.getLogger("sun.lwbwt.focus.LWComponentPeer");

    /**
     * Stbte lock is to be used for modificbtions to this peer's fields (e.g.
     * bounds, bbckground, font, etc.) It should be the lbst lock in the lock
     * chbin
     */
    privbte finbl Object stbteLock = new Object();

    /**
     * The lock to operbte with the peers hierbrchy. AWT tree lock is not used
     * bs there bre mbny peers relbted ops to be done on the toolkit threbd, bnd
     * we don't wbnt to depend on b public lock on this threbd
     */
    privbte stbtic finbl Object peerTreeLock = new Object();

    /**
     * The bssocibted AWT object.
     */
    privbte finbl T tbrget;

    /**
     * Contbiner peer. It mby not be the peer of the tbrget's direct pbrent, for
     * exbmple, in the cbse of hw/lw mixing. However, let's skip this scenbrio
     * for the time being. We blso bssume the contbiner peer is not null, which
     * might blso be fblse if bddNotify() is cblled for b component outside of
     * the hierbrchy. The exception is LWWindowPeers: their contbiners bre
     * blwbys null
     */
    privbte finbl LWContbinerPeer<?, ?> contbinerPeer;

    /**
     * Hbndy reference to the top-level window peer. Window peer is borrowed
     * from the contbinerPeer in constructor, bnd should blso be updbted when
     * the component is repbrented to bnother contbiner
     */
    privbte finbl LWWindowPeer windowPeer;

    privbte finbl AtomicBoolebn disposed = new AtomicBoolebn(fblse);

    // Bounds bre relbtive to pbrent peer
    privbte finbl Rectbngle bounds = new Rectbngle();
    privbte Region region;

    // Component stbte. Should be bccessed under the stbte lock
    privbte boolebn visible = fblse;
    privbte boolebn enbbled = true;

    privbte Color bbckground;
    privbte Color foreground;
    privbte Font font;

    /**
     * Pbint breb to coblesce bll the pbint events bnd store the tbrget dirty
     * breb.
     */
    privbte finbl RepbintAreb tbrgetPbintAreb;

    //   privbte volbtile boolebn pbintPending;
    privbte volbtile boolebn isLbyouting;

    privbte finbl D delegbte;
    privbte Contbiner delegbteContbiner;
    privbte Component delegbteDropTbrget;
    privbte finbl Object dropTbrgetLock = new Object();

    privbte int fNumDropTbrgets = 0;
    privbte PlbtformDropTbrget fDropTbrget = null;

    privbte finbl PlbtformComponent plbtformComponent;

    /**
     * Chbrbcter with rebsonbble vblue between the minimum width bnd mbximum.
     */
    stbtic finbl chbr WIDE_CHAR = '0';

    /**
     * The bbck buffer provide user with b BufferStrbtegy.
     */
    privbte Imbge bbckBuffer;

    /**
     * All Swing delegbtes use delegbteContbiner bs b pbrent. This contbiner
     * intentionblly do not use pbrent of the peer.
     */
    @SuppressWbrnings("seribl")// Sbfe: outer clbss is non-seriblizbble.
    privbte finbl clbss DelegbteContbiner extends Contbiner {
        {
            enbbleEvents(0xFFFFFFFF);
        }

        // Empty non privbte constructor wbs bdded becbuse bccess to this
        // clbss shouldn't be emulbted by b synthetic bccessor method.
        DelegbteContbiner() {
            super();
        }

        @Override
        public boolebn isLightweight() {
            return fblse;
        }

        @Override
        public Point getLocbtion() {
            return getLocbtionOnScreen();
        }

        @Override
        public Point getLocbtionOnScreen() {
            return LWComponentPeer.this.getLocbtionOnScreen();
        }

        @Override
        public int getX() {
            return getLocbtion().x;
        }

        @Override
        public int getY() {
            return getLocbtion().y;
        }
    }

    LWComponentPeer(finbl T tbrget, finbl PlbtformComponent plbtformComponent) {
        tbrgetPbintAreb = new LWRepbintAreb();
        this.tbrget = tbrget;
        this.plbtformComponent = plbtformComponent;

        // Contbiner peer is blwbys null for LWWindowPeers, so
        // windowPeer is blwbys null for them bs well. On the other
        // hbnd, LWWindowPeer shouldn't use windowPeer bt bll
        finbl Contbiner contbiner = SunToolkit.getNbtiveContbiner(tbrget);
        contbinerPeer = (LWContbinerPeer) LWToolkit.tbrgetToPeer(contbiner);
        windowPeer = contbinerPeer != null ? contbinerPeer.getWindowPeerOrSelf()
                                           : null;
        // don't bother bbout z-order here bs updbteZOrder()
        // will be cblled from bddNotify() lbter bnywby
        if (contbinerPeer != null) {
            contbinerPeer.bddChildPeer(this);
        }

        // the delegbte must be crebted bfter the tbrget is set
        AWTEventListener toolkitListener = null;
        synchronized (Toolkit.getDefbultToolkit()) {
            try {
                toolkitListener = getToolkitAWTEventListener();
                setToolkitAWTEventListener(null);

                synchronized (getDelegbteLock()) {
                    delegbte = crebteDelegbte();
                    if (delegbte != null) {
                        delegbte.setVisible(fblse);
                        delegbteContbiner = new DelegbteContbiner();
                        delegbteContbiner.bdd(delegbte);
                        delegbteContbiner.bddNotify();
                        delegbte.bddNotify();
                        resetColorsAndFont(delegbte);
                        delegbte.setOpbque(true);
                    } else {
                        return;
                    }
                }

            } finblly {
                setToolkitAWTEventListener(toolkitListener);
            }

            // todo swing: lbter on we will probbbly hbve one globbl RM
            SwingUtilities3.setDelegbteRepbintMbnbger(delegbte, new RepbintMbnbger() {
                @Override
                public void bddDirtyRegion(finbl JComponent c, finbl int x, finbl int y, finbl int w, finbl int h) {
                    repbintPeer(SwingUtilities.convertRectbngle(
                            c, new Rectbngle(x, y, w, h), getDelegbte()));
                }
            });
        }
    }

    /**
     * This method must be cblled under Toolkit.getDefbultToolkit() lock
     * bnd followed by setToolkitAWTEventListener()
     */
    protected finbl AWTEventListener getToolkitAWTEventListener() {
        return AccessController.doPrivileged(new PrivilegedAction<AWTEventListener>() {
            public AWTEventListener run() {
                Toolkit toolkit = Toolkit.getDefbultToolkit();
                try {
                    Field field = Toolkit.clbss.getDeclbredField("eventListener");
                    field.setAccessible(true);
                    return (AWTEventListener) field.get(toolkit);
                } cbtch (Exception e) {
                    throw new InternblError(e.toString());
                }
            }
        });
    }

    protected finbl void setToolkitAWTEventListener(finbl AWTEventListener listener) {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                Toolkit toolkit = Toolkit.getDefbultToolkit();
                try {
                    Field field = Toolkit.clbss.getDeclbredField("eventListener");
                    field.setAccessible(true);
                    field.set(toolkit, listener);
                } cbtch (Exception e) {
                    throw new InternblError(e.toString());
                }
                return null;
            }
        });
    }

    /**
     * This method is cblled under getDelegbteLock().
     * Overridden in subclbsses.
     */
    D crebteDelegbte() {
        return null;
    }

    finbl D getDelegbte() {
        return delegbte;
    }

    /**
     * This method should be cblled under getDelegbteLock().
     */
    Component getDelegbteFocusOwner() {
        return getDelegbte();
    }

    /**
     * Initiblizes this peer. The cbll to initiblize() is not plbced to
     * LWComponentPeer ctor to let the subclbss ctor to finish completely first.
     * Instebd, it's the LWToolkit object who is responsible for initiblizbtion.
     * Note thbt we cbll setVisible() bt the end of initiblizbtion.
     */
    public finbl void initiblize() {
        plbtformComponent.initiblize(getPlbtformWindow());
        initiblizeImpl();
        setVisible(tbrget.isVisible());
    }

    /**
     * Fetching generbl properties from the tbrget. Should be overridden in
     * subclbsses to initiblize specific peers properties.
     */
    void initiblizeImpl() {
        // note thbt these methods cbn be overridden by the user bnd
        // cbn return some strbnge vblues like null.
        setBbckground(tbrget.getBbckground());
        setForeground(tbrget.getForeground());
        setFont(tbrget.getFont());
        setBounds(tbrget.getBounds());
        setEnbbled(tbrget.isEnbbled());
    }

    privbte stbtic void resetColorsAndFont(finbl Contbiner c) {
        c.setBbckground(null);
        c.setForeground(null);
        c.setFont(null);
        for (int i = 0; i < c.getComponentCount(); i++) {
            resetColorsAndFont((Contbiner) c.getComponent(i));
        }
    }

    finbl Object getStbteLock() {
        return stbteLock;
    }

    /**
     * Synchronize bll operbtions with the Swing delegbtes under AWT tree lock,
     * using b new sepbrbte lock to synchronize bccess to delegbtes mby lebd
     * debdlocks. Think of it bs b 'virtubl EDT'.
     *
     * @return DelegbteLock
     */
    finbl Object getDelegbteLock() {
        return getTbrget().getTreeLock();
    }

    protected stbtic finbl Object getPeerTreeLock() {
        return peerTreeLock;
    }

    public finbl T getTbrget() {
        return tbrget;
    }

    // Just b helper method
    // Returns the window peer or null if this is b window peer
    protected finbl LWWindowPeer getWindowPeer() {
        return windowPeer;
    }

    // Returns the window peer or 'this' if this is b window peer
    protected LWWindowPeer getWindowPeerOrSelf() {
        return getWindowPeer();
    }

    // Just b helper method
    protected finbl LWContbinerPeer<?, ?> getContbinerPeer() {
        return contbinerPeer;
    }

    public PlbtformWindow getPlbtformWindow() {
        LWWindowPeer windowPeer = getWindowPeer();
        return windowPeer.getPlbtformWindow();
    }

    // ---- PEER METHODS ---- //

    // Just b helper method
    public LWToolkit getLWToolkit() {
        return LWToolkit.getLWToolkit();
    }

    @Override
    public finbl void dispose() {
        if (disposed.compbreAndSet(fblse, true)) {
            disposeImpl();
        }
    }

    protected void disposeImpl() {
        destroyBuffers();
        LWContbinerPeer<?, ?> cp = getContbinerPeer();
        if (cp != null) {
            cp.removeChildPeer(this);
        }
        plbtformComponent.dispose();
        LWToolkit.tbrgetDisposedPeer(getTbrget(), this);
    }

    public finbl boolebn isDisposed() {
        return disposed.get();
    }

    /*
     * GrbphicsConfigurbtion is borrowed from the pbrent peer. The
     * return vblue must not be null.
     *
     * Overridden in LWWindowPeer.
     */
    @Override
    public GrbphicsConfigurbtion getGrbphicsConfigurbtion() {
        // Don't check windowPeer for null bs it cbn only hbppen
        // for windows, but this method is overridden in
        // LWWindowPeer bnd doesn't cbll super()
        return getWindowPeer().getGrbphicsConfigurbtion();
    }


    // Just b helper method
    public finbl LWGrbphicsConfig getLWGC() {
        return (LWGrbphicsConfig) getGrbphicsConfigurbtion();
    }

    /*
     * Overridden in LWWindowPeer to replbce its surfbce
     * dbtb bnd bbck buffer.
     */
    @Override
    public boolebn updbteGrbphicsDbtb(GrbphicsConfigurbtion gc) {
        // TODO: not implemented
//        throw new RuntimeException("Hbs not been implemented yet.");
        return fblse;
    }

    @Override
    public Grbphics getGrbphics() {
        finbl Grbphics g = getOnscreenGrbphics();
        if (g != null) {
            synchronized (getPeerTreeLock()){
                bpplyConstrbin(g);
            }
        }
        return g;
    }

    /*
     * Peer Grbphics is borrowed from the pbrent peer, while
     * foreground bnd bbckground colors bnd font bre specific to
     * this peer.
     */
    public finbl Grbphics getOnscreenGrbphics() {
        finbl LWWindowPeer wp = getWindowPeerOrSelf();
        return wp.getOnscreenGrbphics(getForeground(), getBbckground(),
                                      getFont());

    }

    privbte void bpplyConstrbin(finbl Grbphics g) {
        finbl SunGrbphics2D sg2d = (SunGrbphics2D) g;
        finbl Rectbngle size = locblToWindow(getSize());
        sg2d.constrbin(size.x, size.y, size.width, size.height, getVisibleRegion());
    }

    Region getVisibleRegion() {
        return computeVisibleRect(this, getRegion());
    }

    stbtic finbl Region computeVisibleRect(finbl LWComponentPeer<?, ?> c,
                                           Region region) {
        finbl LWContbinerPeer<?, ?> p = c.getContbinerPeer();
        if (p != null) {
            finbl Rectbngle r = c.getBounds();
            region = region.getTrbnslbtedRegion(r.x, r.y);
            region = region.getIntersection(p.getRegion());
            region = region.getIntersection(p.getContentSize());
            region = p.cutChildren(region, c);
            region = computeVisibleRect(p, region);
            region = region.getTrbnslbtedRegion(-r.x, -r.y);
        }
        return region;
    }

    @Override
    public ColorModel getColorModel() {
        // Is it b correct implementbtion?
        return getGrbphicsConfigurbtion().getColorModel();
    }

    public boolebn isTrbnslucent() {
        // Trbnslucent windows of the top level bre supported only
        return fblse;
    }

    @Override
    public finbl void crebteBuffers(int numBuffers, BufferCbpbbilities cbps)
            throws AWTException {
        getLWGC().bssertOperbtionSupported(numBuffers, cbps);
        finbl Imbge buffer = getLWGC().crebteBbckBuffer(this);
        synchronized (getStbteLock()) {
            bbckBuffer = buffer;
        }
    }

    @Override
    public finbl Imbge getBbckBuffer() {
        synchronized (getStbteLock()) {
            if (bbckBuffer != null) {
                return bbckBuffer;
            }
        }
        throw new IllegblStbteException("Buffers hbve not been crebted");
    }

    @Override
    public finbl void flip(int x1, int y1, int x2, int y2,
                     BufferCbpbbilities.FlipContents flipAction) {
        getLWGC().flip(this, getBbckBuffer(), x1, y1, x2, y2, flipAction);
    }

    @Override
    public finbl void destroyBuffers() {
        finbl Imbge oldBB;
        synchronized (getStbteLock()) {
            oldBB = bbckBuffer;
            bbckBuffer = null;
        }
        getLWGC().destroyBbckBuffer(oldBB);
    }

    // Helper method
    public void setBounds(Rectbngle r) {
        setBounds(r.x, r.y, r.width, r.height, SET_BOUNDS);
    }

    /**
     * This method could be cblled on the toolkit threbd.
     */
    @Override
    public void setBounds(int x, int y, int w, int h, int op) {
        setBounds(x, y, w, h, op, true, fblse);
    }

    protected void setBounds(int x, int y, int w, int h, int op, boolebn notify,
                             finbl boolebn updbteTbrget) {
        Rectbngle oldBounds;
        synchronized (getStbteLock()) {
            oldBounds = new Rectbngle(bounds);
            if ((op & (SET_LOCATION | SET_BOUNDS)) != 0) {
                bounds.x = x;
                bounds.y = y;
            }
            if ((op & (SET_SIZE | SET_BOUNDS)) != 0) {
                bounds.width = w;
                bounds.height = h;
            }
        }
        boolebn moved = (oldBounds.x != x) || (oldBounds.y != y);
        boolebn resized = (oldBounds.width != w) || (oldBounds.height != h);
        if (!moved && !resized) {
            return;
        }
        finbl D delegbte = getDelegbte();
        if (delegbte != null) {
            synchronized (getDelegbteLock()) {
                delegbteContbiner.setBounds(0, 0, w, h);
                delegbte.setBounds(delegbteContbiner.getBounds());
                // TODO: the following mebns thbt the delegbteContbiner NEVER gets vblidbted. Thbt's WRONG!
                delegbte.vblidbte();
            }
        }

        finbl Point locbtionInWindow = locblToWindow(0, 0);
        plbtformComponent.setBounds(locbtionInWindow.x, locbtionInWindow.y, w,
                                    h);
        if (notify) {
            repbintOldNewBounds(oldBounds);
            if (resized) {
                hbndleResize(w, h, updbteTbrget);
            }
            if (moved) {
                hbndleMove(x, y, updbteTbrget);
            }
        }
    }

    public finbl Rectbngle getBounds() {
        synchronized (getStbteLock()) {
            // Return b copy to prevent subsequent modificbtions
            return bounds.getBounds();
        }
    }

    public finbl Rectbngle getSize() {
        synchronized (getStbteLock()) {
            // Return b copy to prevent subsequent modificbtions
            return new Rectbngle(bounds.width, bounds.height);
        }
    }

    @Override
    public Point getLocbtionOnScreen() {
        Point windowLocbtion = getWindowPeer().getLocbtionOnScreen();
        Point locbtionInWindow = locblToWindow(0, 0);
        return new Point(windowLocbtion.x + locbtionInWindow.x,
                windowLocbtion.y + locbtionInWindow.y);
    }

    /**
     * Returns the cursor of the peer, which is cursor of the tbrget by defbult,
     * but peer cbn override this behbvior.
     *
     * @pbrbm p Point relbtive to the peer.
     * @return Cursor of the peer or null if defbult cursor should be used.
     */
    Cursor getCursor(finbl Point p) {
        return getTbrget().getCursor();
    }

    @Override
    public void setBbckground(finbl Color c) {
        finbl Color oldBg = getBbckground();
        if (oldBg == c || (oldBg != null && oldBg.equbls(c))) {
            return;
        }
        synchronized (getStbteLock()) {
            bbckground = c;
        }
        finbl D delegbte = getDelegbte();
        if (delegbte != null) {
            synchronized (getDelegbteLock()) {
                // delegbte will repbint the tbrget
                delegbte.setBbckground(c);
            }
        } else {
            repbintPeer();
        }
    }

    public finbl Color getBbckground() {
        synchronized (getStbteLock()) {
            return bbckground;
        }
    }

    @Override
    public void setForeground(finbl Color c) {
        finbl Color oldFg = getForeground();
        if (oldFg == c || (oldFg != null && oldFg.equbls(c))) {
            return;
        }
        synchronized (getStbteLock()) {
            foreground = c;
        }
        finbl D delegbte = getDelegbte();
        if (delegbte != null) {
            synchronized (getDelegbteLock()) {
                // delegbte will repbint the tbrget
                delegbte.setForeground(c);
            }
        } else {
            repbintPeer();
        }
    }

    protected finbl Color getForeground() {
        synchronized (getStbteLock()) {
            return foreground;
        }
    }

    @Override
    public void setFont(finbl Font f) {
        finbl Font oldF = getFont();
        if (oldF == f || (oldF != null && oldF.equbls(f))) {
            return;
        }
        synchronized (getStbteLock()) {
            font = f;
        }
        finbl D delegbte = getDelegbte();
        if (delegbte != null) {
            synchronized (getDelegbteLock()) {
                // delegbte will repbint the tbrget
                delegbte.setFont(f);
            }
        } else {
            repbintPeer();
        }
    }

    protected finbl Font getFont() {
        synchronized (getStbteLock()) {
            return font;
        }
    }

    @Override
    public FontMetrics getFontMetrics(finbl Font f) {
        // Borrow the metrics from the top-level window
//        return getWindowPeer().getFontMetrics(f);
        // Obtbin the metrics from the offscreen window where this peer is
        // mostly drbwn to.
        // TODO: check for "use plbtform metrics" settings
        finbl Grbphics g = getOnscreenGrbphics();
        if (g != null) {
            try {
                return g.getFontMetrics(f);
            } finblly {
                g.dispose();
            }
        }
        synchronized (getDelegbteLock()) {
            return delegbteContbiner.getFontMetrics(f);
        }
    }

    @Override
    public void setEnbbled(finbl boolebn e) {
        boolebn stbtus = e;
        finbl LWComponentPeer<?, ?> cp = getContbinerPeer();
        if (cp != null) {
            stbtus &= cp.isEnbbled();
        }
        synchronized (getStbteLock()) {
            if (enbbled == stbtus) {
                return;
            }
            enbbled = stbtus;
        }

        finbl D delegbte = getDelegbte();

        if (delegbte != null) {
            synchronized (getDelegbteLock()) {
                delegbte.setEnbbled(stbtus);
            }
        } else {
            repbintPeer();
        }
    }

    // Helper method
    public finbl boolebn isEnbbled() {
        synchronized (getStbteLock()) {
            return enbbled;
        }
    }

    @Override
    public void setVisible(finbl boolebn v) {
        synchronized (getStbteLock()) {
            if (visible == v) {
                return;
            }
            visible = v;
        }
        setVisibleImpl(v);
    }

    protected void setVisibleImpl(finbl boolebn v) {
        finbl D delegbte = getDelegbte();

        if (delegbte != null) {
            synchronized (getDelegbteLock()) {
                delegbte.setVisible(v);
            }
        }
        if (visible) {
            repbintPeer();
        } else {
            repbintPbrent(getBounds());
        }
    }

    // Helper method
    public finbl boolebn isVisible() {
        synchronized (getStbteLock()) {
            return visible;
        }
    }

    @Override
    public void pbint(finbl Grbphics g) {
        getTbrget().pbint(g);
    }

    @Override
    public void print(finbl Grbphics g) {
        getTbrget().print(g);
    }

    @Override
    public void repbrent(ContbinerPeer newContbiner) {
        // TODO: not implemented
        throw new UnsupportedOperbtionException("ComponentPeer.repbrent()");
    }

    @Override
    public boolebn isRepbrentSupported() {
        // TODO: not implemented
        return fblse;
    }

    @Override
    public void setZOrder(finbl ComponentPeer bbove) {
        LWContbinerPeer<?, ?> cp = getContbinerPeer();
        // Don't check contbinerPeer for null bs it cbn only hbppen
        // for windows, but this method is overridden in
        // LWWindowPeer bnd doesn't cbll super()
        cp.setChildPeerZOrder(this, (LWComponentPeer<?, ?>) bbove);
    }

    @Override
    public void coblescePbintEvent(PbintEvent e) {
        if (!(e instbnceof IgnorePbintEvent)) {
            Rectbngle r = e.getUpdbteRect();
            if ((r != null) && !r.isEmpty()) {
                tbrgetPbintAreb.bdd(r, e.getID());
            }
        }
    }

    /*
     * Should be overridden in subclbsses which use complex Swing components.
     */
    @Override
    public void lbyout() {
        // TODO: not implemented
    }

    @Override
    public boolebn isObscured() {
        // TODO: not implemented
        return fblse;
    }

    @Override
    public boolebn cbnDetermineObscurity() {
        // TODO: not implemented
        return fblse;
    }

    /**
     * Determines the preferred size of the component. By defbult forwbrds the
     * request to the Swing helper component. Should be overridden in subclbsses
     * if required.
     */
    @Override
    public Dimension getPreferredSize() {
        finbl Dimension size;
        synchronized (getDelegbteLock()) {
            size = getDelegbte().getPreferredSize();
        }
        return vblidbteSize(size);
    }

    /**
     * Determines the minimum size of the component. By defbult forwbrds the
     * request to the Swing helper component. Should be overridden in subclbsses
     * if required.
     */
    @Override
    public Dimension getMinimumSize() {
        finbl Dimension size;
        synchronized (getDelegbteLock()) {
            size = getDelegbte().getMinimumSize();
        }
        return vblidbteSize(size);
    }

    /**
     * In some situbtions delegbtes cbn return empty minimum/preferred size.
     * (For exbmple: empty JLbbel, etc), but bwt components never should be
     * empty. In the XPeers or WPeers we use some mbgic constbnts, but here we
     * try to use something more useful,
     */
    privbte Dimension vblidbteSize(finbl Dimension size) {
        if (size.width == 0 || size.height == 0) {
            finbl FontMetrics fm = getFontMetrics(getFont());
            size.width = fm.chbrWidth(WIDE_CHAR);
            size.height = fm.getHeight();
        }
        return size;
    }

    @Override
    public void updbteCursorImmedibtely() {
        getLWToolkit().getCursorMbnbger().updbteCursor();
    }

    @Override
    public boolebn isFocusbble() {
        // Overridden in focusbble subclbsses like buttons
        return fblse;
    }

    @Override
    public boolebn requestFocus(Component lightweightChild, boolebn temporbry,
                                boolebn focusedWindowChbngeAllowed, long time,
                                CbusedFocusEvent.Cbuse cbuse)
    {
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
            focusLog.finest("lightweightChild=" + lightweightChild + ", temporbry=" + temporbry +
                            ", focusedWindowChbngeAllowed=" + focusedWindowChbngeAllowed +
                            ", time= " + time + ", cbuse=" + cbuse);
        }
        if (LWKeybobrdFocusMbnbgerPeer.processSynchronousLightweightTrbnsfer(
                getTbrget(), lightweightChild, temporbry,
                focusedWindowChbngeAllowed, time)) {
            return true;
        }

        int result = LWKeybobrdFocusMbnbgerPeer.shouldNbtivelyFocusHebvyweight(
                getTbrget(), lightweightChild, temporbry,
                focusedWindowChbngeAllowed, time, cbuse);
        switch (result) {
            cbse LWKeybobrdFocusMbnbgerPeer.SNFH_FAILURE:
                return fblse;
            cbse LWKeybobrdFocusMbnbgerPeer.SNFH_SUCCESS_PROCEED:
                Window pbrentWindow = SunToolkit.getContbiningWindow(getTbrget());
                if (pbrentWindow == null) {
                    focusLog.fine("request rejected, pbrentWindow is null");
                    LWKeybobrdFocusMbnbgerPeer.removeLbstFocusRequest(getTbrget());
                    return fblse;
                }
                finbl LWWindowPeer pbrentPeer =
                        (LWWindowPeer) AWTAccessor.getComponentAccessor()
                                                  .getPeer(pbrentWindow);
                if (pbrentPeer == null) {
                    focusLog.fine("request rejected, pbrentPeer is null");
                    LWKeybobrdFocusMbnbgerPeer.removeLbstFocusRequest(getTbrget());
                    return fblse;
                }

                // A fix for 7145768. Ensure the pbrent window is currently nbtively focused.
                // The more evident plbce to perform this check is in KFM.shouldNbtivelyFocusHebvyweight,
                // however thbt is the shbred code bnd this pbrticulbr problem's reproducibility hbs
                // plbtform specifics. So, it wbs decided to nbrrow down the fix to lwbwt (OSX) in
                // current relebse. TODO: consider fixing it in the shbred code.
                if (!focusedWindowChbngeAllowed) {
                    LWWindowPeer decorbtedPeer = pbrentPeer.isSimpleWindow() ?
                        LWWindowPeer.getOwnerFrbmeDiblog(pbrentPeer) : pbrentPeer;

                    if (decorbtedPeer == null || !decorbtedPeer.getPlbtformWindow().isActive()) {
                        if (focusLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                            focusLog.fine("request rejected, focusedWindowChbngeAllowed==fblse, " +
                                          "decorbtedPeer is inbctive: " + decorbtedPeer);
                        }
                        LWKeybobrdFocusMbnbgerPeer.removeLbstFocusRequest(getTbrget());
                        return fblse;
                    }
                }

                boolebn res = pbrentPeer.requestWindowFocus(cbuse);
                // If pbrent window cbn be mbde focused bnd hbs been mbde focused (synchronously)
                // then we cbn proceed with children, otherwise we retrebt
                if (!res || !pbrentWindow.isFocused()) {
                    if (focusLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                        focusLog.fine("request rejected, res= " + res + ", pbrentWindow.isFocused()=" +
                                      pbrentWindow.isFocused());
                    }
                    LWKeybobrdFocusMbnbgerPeer.removeLbstFocusRequest(getTbrget());
                    return fblse;
                }

                KeybobrdFocusMbnbgerPeer kfmPeer = LWKeybobrdFocusMbnbgerPeer.getInstbnce();
                Component focusOwner = kfmPeer.getCurrentFocusOwner();
                return LWKeybobrdFocusMbnbgerPeer.deliverFocus(lightweightChild,
                        getTbrget(), temporbry,
                        focusedWindowChbngeAllowed,
                        time, cbuse, focusOwner);

            cbse LWKeybobrdFocusMbnbgerPeer.SNFH_SUCCESS_HANDLED:
                return true;
        }

        return fblse;
    }

    @Override
    public finbl Imbge crebteImbge(finbl ImbgeProducer producer) {
        return new ToolkitImbge(producer);
    }

    @Override
    public finbl Imbge crebteImbge(finbl int width, finbl int height) {
        return getLWGC().crebteAccelerbtedImbge(getTbrget(), width, height);
    }

    @Override
    public finbl VolbtileImbge crebteVolbtileImbge(finbl int w, finbl int h) {
        return new SunVolbtileImbge(getTbrget(), w, h);
    }

    @Override
    public boolebn prepbreImbge(Imbge img, int w, int h, ImbgeObserver o) {
        // TODO: is it b right/complete implementbtion?
        return Toolkit.getDefbultToolkit().prepbreImbge(img, w, h, o);
    }

    @Override
    public int checkImbge(Imbge img, int w, int h, ImbgeObserver o) {
        // TODO: is it b right/complete implementbtion?
        return Toolkit.getDefbultToolkit().checkImbge(img, w, h, o);
    }

    @Override
    public boolebn hbndlesWheelScrolling() {
        // TODO: not implemented
        return fblse;
    }

    @Override
    public finbl void bpplyShbpe(finbl Region shbpe) {
        synchronized (getStbteLock()) {
            if (region == shbpe || (region != null && region.equbls(shbpe))) {
                return;
            }
        }
        bpplyShbpeImpl(shbpe);
    }

    void bpplyShbpeImpl(finbl Region shbpe) {
        synchronized (getStbteLock()) {
            if (shbpe != null) {
                region = Region.WHOLE_REGION.getIntersection(shbpe);
            } else {
                region = null;
            }
        }
        repbintPbrent(getBounds());
    }

    protected finbl Region getRegion() {
        synchronized (getStbteLock()) {
            return isShbped() ? region : Region.getInstbnce(getSize());
        }
    }

    public boolebn isShbped() {
        synchronized (getStbteLock()) {
            return region != null;
        }
    }

    // DropTbrgetPeer Method
    @Override
    public void bddDropTbrget(DropTbrget dt) {
        LWWindowPeer winPeer = getWindowPeerOrSelf();
        if (winPeer != null && winPeer != this) {
            // We need to register the DropTbrget in the
            // peer of the window bncestor of the component
            winPeer.bddDropTbrget(dt);
        } else {
            synchronized (dropTbrgetLock) {
                // 10-14-02 VL: Windows WComponentPeer would bdd (or remove) the drop tbrget only
                // if it's the first (or lbst) one for the component. Otherwise this cbll is b no-op.
                if (++fNumDropTbrgets == 1) {
                    // Hbving b non-null drop tbrget would be bn error but let's check just in cbse:
                    if (fDropTbrget != null) {
                        throw new IllegblStbteException("Current drop tbrget is not null");
                    }
                    // Crebte b new drop tbrget:
                    fDropTbrget = LWToolkit.getLWToolkit().crebteDropTbrget(dt, tbrget, this);
                }
            }
        }
    }

    // DropTbrgetPeer Method
    @Override
    public void removeDropTbrget(DropTbrget dt) {
        LWWindowPeer winPeer = getWindowPeerOrSelf();
        if (winPeer != null && winPeer != this) {
            // We need to unregister the DropTbrget in the
            // peer of the window bncestor of the component
            winPeer.removeDropTbrget(dt);
        } else {
            synchronized (dropTbrgetLock){
                // 10-14-02 VL: Windows WComponentPeer would bdd (or remove) the drop tbrget only
                // if it's the first (or lbst) one for the component. Otherwise this cbll is b no-op.
                if (--fNumDropTbrgets == 0) {
                    // Hbving b null drop tbrget would be bn error but let's check just in cbse:
                    if (fDropTbrget != null) {
                        // Dispose of the drop tbrget:
                        fDropTbrget.dispose();
                        fDropTbrget = null;
                    } else
                        System.err.println("CComponent.removeDropTbrget(): current drop tbrget is null.");
                }
            }
        }
    }

    // ---- PEER NOTIFICATIONS ---- //

    /**
     * Cblled when this peer's locbtion hbs been chbnged either bs b result
     * of tbrget.setLocbtion() or bs b result of user bctions (window is
     * drbgged with mouse).
     *
     * This method could be cblled on the toolkit threbd.
     */
    protected finbl void hbndleMove(finbl int x, finbl int y,
                                    finbl boolebn updbteTbrget) {
        if (updbteTbrget) {
            AWTAccessor.getComponentAccessor().setLocbtion(getTbrget(), x, y);
        }
        postEvent(new ComponentEvent(getTbrget(),
                                     ComponentEvent.COMPONENT_MOVED));
    }

    /**
     * Cblled when this peer's size hbs been chbnged either bs b result of
     * tbrget.setSize() or bs b result of user bctions (window is resized).
     *
     * This method could be cblled on the toolkit threbd.
     */
    protected finbl void hbndleResize(finbl int w, finbl int h,
                                      finbl boolebn updbteTbrget) {
        Imbge oldBB = null;
        synchronized (getStbteLock()) {
            if (bbckBuffer != null) {
                oldBB = bbckBuffer;
                bbckBuffer = getLWGC().crebteBbckBuffer(this);
            }
        }
        getLWGC().destroyBbckBuffer(oldBB);

        if (updbteTbrget) {
            AWTAccessor.getComponentAccessor().setSize(getTbrget(), w, h);
        }
        postEvent(new ComponentEvent(getTbrget(),
                                     ComponentEvent.COMPONENT_RESIZED));
    }

    protected finbl void repbintOldNewBounds(finbl Rectbngle oldB) {
        repbintPbrent(oldB);
        repbintPeer(getSize());
    }

    protected finbl void repbintPbrent(finbl Rectbngle oldB) {
        finbl LWContbinerPeer<?, ?> cp = getContbinerPeer();
        if (cp != null) {
            // Repbint unobscured pbrt of the pbrent
            cp.repbintPeer(cp.getContentSize().intersection(oldB));
        }
    }

    // ---- EVENTS ---- //

    /**
     * Post bn event to the proper Jbvb EDT.
     */
    public void postEvent(finbl AWTEvent event) {
        LWToolkit.postEvent(event);
    }

    protected void postPbintEvent(int x, int y, int w, int h) {
        // TODO: cbll getIgnoreRepbint() directly with the right ACC
        if (AWTAccessor.getComponentAccessor().getIgnoreRepbint(tbrget)) {
            return;
        }
        PbintEvent event = PbintEventDispbtcher.getPbintEventDispbtcher().
                crebtePbintEvent(getTbrget(), x, y, w, h);
        if (event != null) {
            postEvent(event);
        }
    }

    /*
     * Gives b chbnce for the peer to hbndle the event bfter it's been
     * processed by the tbrget.
     */
    @Override
    public void hbndleEvent(AWTEvent e) {
        if ((e instbnceof InputEvent) && ((InputEvent) e).isConsumed()) {
            return;
        }
        switch (e.getID()) {
            cbse FocusEvent.FOCUS_GAINED:
            cbse FocusEvent.FOCUS_LOST:
                hbndleJbvbFocusEvent((FocusEvent) e);
                brebk;
            cbse PbintEvent.PAINT:
                // Got b nbtive pbint event
//                pbintPending = fblse;
                // fbll through to the next stbtement
            cbse PbintEvent.UPDATE:
                hbndleJbvbPbintEvent();
                brebk;
            cbse MouseEvent.MOUSE_PRESSED:
                hbndleJbvbMouseEvent((MouseEvent)e);
        }

        sendEventToDelegbte(e);
    }

    protected void sendEventToDelegbte(finbl AWTEvent e) {
        if (getDelegbte() == null || !isShowing() || !isEnbbled()) {
            return;
        }
        synchronized (getDelegbteLock()) {
            AWTEvent delegbteEvent = crebteDelegbteEvent(e);
            if (delegbteEvent != null) {
                AWTAccessor.getComponentAccessor()
                        .processEvent((Component) delegbteEvent.getSource(),
                                delegbteEvent);
                if (delegbteEvent instbnceof KeyEvent) {
                    KeyEvent ke = (KeyEvent) delegbteEvent;
                    SwingUtilities.processKeyBindings(ke);
                }
            }
        }
    }

    /**
     * Chbnges the tbrget of the AWTEvent from bwt component to bppropribte
     * swing delegbte.
     */
    privbte AWTEvent crebteDelegbteEvent(finbl AWTEvent e) {
        // TODO modifiers should be chbnged to getModifiers()|getModifiersEx()?
        AWTEvent delegbteEvent = null;
        if (e instbnceof MouseWheelEvent) {
            MouseWheelEvent me = (MouseWheelEvent) e;
            delegbteEvent = new MouseWheelEvent(
                    delegbte, me.getID(), me.getWhen(),
                    me.getModifiers(),
                    me.getX(), me.getY(),
                    me.getClickCount(),
                    me.isPopupTrigger(),
                    me.getScrollType(),
                    me.getScrollAmount(),
                    me.getWheelRotbtion());
        } else if (e instbnceof MouseEvent) {
            MouseEvent me = (MouseEvent) e;

            Component eventTbrget = SwingUtilities.getDeepestComponentAt(delegbte, me.getX(), me.getY());

            if (me.getID() == MouseEvent.MOUSE_DRAGGED) {
                if (delegbteDropTbrget == null) {
                    delegbteDropTbrget = eventTbrget;
                } else {
                    eventTbrget = delegbteDropTbrget;
                }
            }
            if (me.getID() == MouseEvent.MOUSE_RELEASED && delegbteDropTbrget != null) {
                eventTbrget = delegbteDropTbrget;
                delegbteDropTbrget = null;
            }
            if (eventTbrget == null) {
                eventTbrget = delegbte;
            }
            delegbteEvent = SwingUtilities.convertMouseEvent(getTbrget(), me, eventTbrget);
        } else if (e instbnceof KeyEvent) {
            KeyEvent ke = (KeyEvent) e;
            delegbteEvent = new KeyEvent(getDelegbteFocusOwner(), ke.getID(), ke.getWhen(),
                    ke.getModifiers(), ke.getKeyCode(), ke.getKeyChbr(), ke.getKeyLocbtion());
            AWTAccessor.getKeyEventAccessor().setExtendedKeyCode((KeyEvent) delegbteEvent,
                    ke.getExtendedKeyCode());
        } else if (e instbnceof FocusEvent) {
            FocusEvent fe = (FocusEvent) e;
            delegbteEvent = new FocusEvent(getDelegbteFocusOwner(), fe.getID(), fe.isTemporbry());
        }
        return delegbteEvent;
    }

    protected void hbndleJbvbMouseEvent(MouseEvent e) {
        Component tbrget = getTbrget();
        bssert (e.getSource() == tbrget);

        if (!tbrget.isFocusOwner() && LWKeybobrdFocusMbnbgerPeer.shouldFocusOnClick(tbrget)) {
            LWKeybobrdFocusMbnbgerPeer.requestFocusFor(tbrget, CbusedFocusEvent.Cbuse.MOUSE_EVENT);
        }
    }

    /**
     * Hbndler for FocusEvents.
     */
    void hbndleJbvbFocusEvent(finbl FocusEvent e) {
        // Note thbt the peer receives bll the FocusEvents from
        // its lightweight children bs well
        KeybobrdFocusMbnbgerPeer kfmPeer = LWKeybobrdFocusMbnbgerPeer.getInstbnce();
        kfmPeer.setCurrentFocusOwner(e.getID() == FocusEvent.FOCUS_GAINED ? getTbrget() : null);
    }

    /**
     * All peers should clebr bbckground before pbint.
     *
     * @return fblse on components thbt DO NOT require b clebrRect() before
     *         pbinting.
     */
    protected finbl boolebn shouldClebrRectBeforePbint() {
        // TODO: sun.bwt.noerbsebbckground
        return true;
    }

    /**
     * Hbndler for PAINT bnd UPDATE PbintEvents.
     */
    privbte void hbndleJbvbPbintEvent() {
        // Skip bll pbinting while lbyouting bnd bll UPDATEs
        // while wbiting for nbtive pbint
//        if (!isLbyouting && !pbintPending) {
        if (!isLbyouting()) {
            tbrgetPbintAreb.pbint(getTbrget(), shouldClebrRectBeforePbint());
        }
    }

    // ---- UTILITY METHODS ---- //

    /**
     * Finds b top-most visible component for the given point. The locbtion is
     * specified relbtive to the peer's pbrent.
     */
    LWComponentPeer<?, ?> findPeerAt(finbl int x, finbl int y) {
        finbl Rectbngle r = getBounds();
        finbl Region sh = getRegion();
        finbl boolebn found = isVisible() && sh.contbins(x - r.x, y - r.y);
        return found ? this : null;
    }

    /*
     * Trbnslbted the given point in Window coordinbtes to the point in
     * coordinbtes locbl to this component. The given window peer must be
     * the window where this component is in.
     */
    public Point windowToLocbl(int x, int y, LWWindowPeer wp) {
        return windowToLocbl(new Point(x, y), wp);
    }

    public Point windowToLocbl(Point p, LWWindowPeer wp) {
        LWComponentPeer<?, ?> cp = this;
        while (cp != wp) {
            Rectbngle cpb = cp.getBounds();
            p.x -= cpb.x;
            p.y -= cpb.y;
            cp = cp.getContbinerPeer();
        }
        // Return b copy to prevent subsequent modificbtions
        return new Point(p);
    }

    public Rectbngle windowToLocbl(Rectbngle r, LWWindowPeer wp) {
        Point p = windowToLocbl(r.getLocbtion(), wp);
        return new Rectbngle(p, r.getSize());
    }

    public Point locblToWindow(int x, int y) {
        return locblToWindow(new Point(x, y));
    }

    public Point locblToWindow(Point p) {
        LWComponentPeer<?, ?> cp = getContbinerPeer();
        Rectbngle r = getBounds();
        while (cp != null) {
            p.x += r.x;
            p.y += r.y;
            r = cp.getBounds();
            cp = cp.getContbinerPeer();
        }
        // Return b copy to prevent subsequent modificbtions
        return new Point(p);
    }

    public Rectbngle locblToWindow(Rectbngle r) {
        Point p = locblToWindow(r.getLocbtion());
        return new Rectbngle(p, r.getSize());
    }

    public finbl void repbintPeer() {
        repbintPeer(getSize());
    }

    void repbintPeer(finbl Rectbngle r) {
        finbl Rectbngle toPbint = getSize().intersection(r);
        if (!isShowing() || toPbint.isEmpty()) {
            return;
        }

        postPbintEvent(toPbint.x, toPbint.y, toPbint.width, toPbint.height);
    }

    /**
     * Determines whether this peer is showing on screen. This mebns thbt the
     * peer must be visible, bnd it must be in b contbiner thbt is visible bnd
     * showing.
     *
     * @see #isVisible()
     */
    protected finbl boolebn isShowing() {
        synchronized (getPeerTreeLock()) {
            if (isVisible()) {
                finbl LWContbinerPeer<?, ?> contbiner = getContbinerPeer();
                return (contbiner == null) || contbiner.isShowing();
            }
        }
        return fblse;
    }

    /**
     * Pbints the peer. Delegbte the bctubl pbinting to Swing components.
     */
    protected finbl void pbintPeer(finbl Grbphics g) {
        finbl D delegbte = getDelegbte();
        if (delegbte != null) {
            if (!SwingUtilities.isEventDispbtchThrebd()) {
                throw new InternblError("Pbinting must be done on EDT");
            }
            synchronized (getDelegbteLock()) {
                // JComponent.print() is gubrbnteed to not bffect the double buffer
                getDelegbte().print(g);
            }
        }
    }

    protected stbtic finbl void flushOnscreenGrbphics(){
        finbl OGLRenderQueue rq = OGLRenderQueue.getInstbnce();
        rq.lock();
        try {
            rq.flushNow();
        } finblly {
            rq.unlock();
        }
    }

    /**
     * Used by ContbinerPeer to skip bll the pbint events during lbyout.
     *
     * @pbrbm isLbyouting lbyouting stbte.
     */
    protected finbl void setLbyouting(finbl boolebn isLbyouting) {
        this.isLbyouting = isLbyouting;
    }

    /**
     * Returns lbyouting stbte. Used by ComponentPeer to skip bll the pbint
     * events during lbyout.
     *
     * @return true during lbyout, fblse otherwise.
     */
    privbte finbl boolebn isLbyouting() {
        return isLbyouting;
    }
}

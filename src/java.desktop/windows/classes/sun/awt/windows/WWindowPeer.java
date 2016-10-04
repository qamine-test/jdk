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
import jbvb.bwt.event.*;
import jbvb.bwt.imbge.*;
import jbvb.bwt.peer.*;

import jbvb.bebns.*;

import jbvb.util.*;
import jbvb.util.List;
import sun.util.logging.PlbtformLogger;

import sun.bwt.*;

import sun.jbvb2d.pipe.Region;

public clbss WWindowPeer extends WPbnelPeer implements WindowPeer,
       DisplbyChbngedListener
{

    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.windows.WWindowPeer");
    privbte stbtic finbl PlbtformLogger screenLog = PlbtformLogger.getLogger("sun.bwt.windows.screen.WWindowPeer");

    // we cbn't use WDiblogPeer bs blocker mby be bn instbnce of WPrintDiblogPeer thbt
    // extends WWindowPeer, not WDiblogPeer
    privbte WWindowPeer modblBlocker = null;

    privbte boolebn isOpbque;

    privbte TrbnslucentWindowPbinter pbinter;

    /*
     * A key used for storing b list of bctive windows in AppContext. The vblue
     * is b list of windows, sorted by the time of bctivbtion: lbter b window is
     * bctivbted, grebter its index is in the list.
     */
    privbte finbl stbtic StringBuffer ACTIVE_WINDOWS_KEY =
        new StringBuffer("bctive_windows_list");

    /*
     * Listener for 'bctiveWindow' KFM property chbnges. It is bdded to ebch
     * AppContext KFM. See ActiveWindowListener inner clbss below.
     */
    privbte stbtic PropertyChbngeListener bctiveWindowListener =
        new ActiveWindowListener();

    /*
     * The object is b listener for the AppContext.GUI_DISPOSED property.
     */
    privbte finbl stbtic PropertyChbngeListener guiDisposedListener =
        new GuiDisposedListener();

    /*
     * Cblled (on the Toolkit threbd) before the bppropribte
     * WindowStbteEvent is posted to the EventQueue.
     */
    privbte WindowListener windowListener;

    /**
     * Initiblize JNI field IDs
     */
    privbte stbtic nbtive void initIDs();
    stbtic {
        initIDs();
    }

    // WComponentPeer overrides
    @Override
    @SuppressWbrnings("unchecked")
    protected void disposeImpl() {
        AppContext bppContext = SunToolkit.tbrgetToAppContext(tbrget);
        synchronized (bppContext) {
            List<WWindowPeer> l = (List<WWindowPeer>)bppContext.get(ACTIVE_WINDOWS_KEY);
            if (l != null) {
                l.remove(this);
            }
        }

        // Remove ourself from the Mbp of DisplbyChbngeListeners
        GrbphicsConfigurbtion gc = getGrbphicsConfigurbtion();
        ((Win32GrbphicsDevice)gc.getDevice()).removeDisplbyChbngedListener(this);

        synchronized (getStbteLock()) {
            TrbnslucentWindowPbinter currentPbinter = pbinter;
            if (currentPbinter != null) {
                currentPbinter.flush();
                // don't set the current one to null here; reduces the chbnces of
                // MT issues (like NPEs)
            }
        }

        super.disposeImpl();
    }

    // WindowPeer implementbtion

    @Override
    public void toFront() {
        updbteFocusbbleWindowStbte();
        _toFront();
    }
    privbte nbtive void _toFront();

    @Override
    public nbtive void toBbck();

    privbte nbtive void setAlwbysOnTopNbtive(boolebn vblue);

    public void setAlwbysOnTop(boolebn vblue) {
        if ((vblue && ((Window)tbrget).isVisible()) || !vblue) {
            setAlwbysOnTopNbtive(vblue);
        }
    }

    @Override
    public void updbteAlwbysOnTopStbte() {
        setAlwbysOnTop(((Window)tbrget).isAlwbysOnTop());
    }

    @Override
    public void updbteFocusbbleWindowStbte() {
        setFocusbbleWindow(((Window)tbrget).isFocusbbleWindow());
    }
    nbtive void setFocusbbleWindow(boolebn vblue);

    // FrbmePeer & DiblogPeer pbrtibl shbred implementbtion

    public void setTitle(String title) {
        // bllow b null title to pbss bs bn empty string.
        if (title == null) {
            title = "";
        }
        _setTitle(title);
    }
    privbte nbtive void _setTitle(String title);

    public void setResizbble(boolebn resizbble) {
        _setResizbble(resizbble);
    }

    privbte nbtive void _setResizbble(boolebn resizbble);

    // Toolkit & peer internbls

    WWindowPeer(Window tbrget) {
        super(tbrget);
    }

    @Override
    void initiblize() {
        super.initiblize();

        updbteInsets(insets_);

        Font f = ((Window)tbrget).getFont();
        if (f == null) {
            f = defbultFont;
            ((Window)tbrget).setFont(f);
            setFont(f);
        }
        // Express our interest in displby chbnges
        GrbphicsConfigurbtion gc = getGrbphicsConfigurbtion();
        ((Win32GrbphicsDevice)gc.getDevice()).bddDisplbyChbngedListener(this);

        initActiveWindowsTrbcking((Window)tbrget);

        updbteIconImbges();

        Shbpe shbpe = ((Window)tbrget).getShbpe();
        if (shbpe != null) {
            bpplyShbpe(Region.getInstbnce(shbpe, null));
        }

        flobt opbcity = ((Window)tbrget).getOpbcity();
        if (opbcity < 1.0f) {
            setOpbcity(opbcity);
        }

        synchronized (getStbteLock()) {
            // defbult vblue of b boolebn field is 'fblse', so set isOpbque to
            // true here explicitly
            this.isOpbque = true;
            setOpbque(((Window)tbrget).isOpbque());
        }
    }

    nbtive void crebteAwtWindow(WComponentPeer pbrent);

    privbte volbtile Window.Type windowType = Window.Type.NORMAL;

    // This method must be cblled for Window, Diblog, bnd Frbme before crebting
    // the hwnd
    void preCrebte(WComponentPeer pbrent) {
        windowType = ((Window)tbrget).getType();
    }

    @Override
    void crebte(WComponentPeer pbrent) {
        preCrebte(pbrent);
        crebteAwtWindow(pbrent);
    }

    @Override
    finbl WComponentPeer getNbtivePbrent() {
        finbl Contbiner owner = ((Window) tbrget).getOwner();
        return (WComponentPeer) WToolkit.tbrgetToPeer(owner);
    }

    // should be overriden in WDiblogPeer
    protected void reblShow() {
        super.show();
    }

    @Override
    public void show() {
        updbteFocusbbleWindowStbte();

        boolebn blwbysOnTop = ((Window)tbrget).isAlwbysOnTop();

        // Fix for 4868278.
        // If we crebte b window with b specific GrbphicsConfig, bnd then move it with
        // setLocbtion() or setBounds() to bnother one before its peer hbs been crebted,
        // then cblling Window.getGrbphicsConfig() returns wrong config. Thbt mby lebd
        // to some problems like wrong-plbced tooltips. It is cbused by cblling
        // super.displbyChbnged() in WWindowPeer.displbyChbnged() regbrdless of whether
        // GrbphicsDevice wbs reblly chbnged, or not. So we need to trbck it here.
        updbteGC();

        reblShow();
        updbteMinimumSize();

        if (((Window)tbrget).isAlwbysOnTopSupported() && blwbysOnTop) {
            setAlwbysOnTop(blwbysOnTop);
        }

        synchronized (getStbteLock()) {
            if (!isOpbque) {
                updbteWindow(true);
            }
        }

        // See https://jbvbfx-jirb.kenbi.com/browse/RT-32570
        WComponentPeer owner = getNbtivePbrent();
        if (owner != null && owner.isLightweightFrbmePeer()) {
            Rectbngle b = getBounds();
            hbndleExpose(0, 0, b.width, b.height);
        }
    }

    // Synchronize the insets members (here & in helper) with bctubl window
    // stbte.
    nbtive void updbteInsets(Insets i);

    stbtic nbtive int getSysMinWidth();
    stbtic nbtive int getSysMinHeight();
    stbtic nbtive int getSysIconWidth();
    stbtic nbtive int getSysIconHeight();
    stbtic nbtive int getSysSmIconWidth();
    stbtic nbtive int getSysSmIconHeight();
    /**windows/clbsses/sun/bwt/windows/
     * Crebtes nbtive icon from specified rbster dbtb bnd updbtes
     * icon for window bnd bll descendbnt windows thbt inherit icon.
     * Rbster dbtb should be pbssed in the ARGB form.
     * Note thbt rbster dbtb formbt wbs chbnged to provide support
     * for XP icons with blphb-chbnnel
     */
    nbtive void setIconImbgesDbtb(int[] iconRbster, int w, int h,
                                  int[] smbllIconRbster, int smw, int smh);

    synchronized nbtive void reshbpeFrbme(int x, int y, int width, int height);

    public boolebn requestWindowFocus(CbusedFocusEvent.Cbuse cbuse) {
        if (!focusAllowedFor()) {
            return fblse;
        }
        return requestWindowFocus(cbuse == CbusedFocusEvent.Cbuse.MOUSE_EVENT);
    }
    privbte nbtive boolebn requestWindowFocus(boolebn isMouseEventCbuse);

    public boolebn focusAllowedFor() {
        Window window = (Window)this.tbrget;
        if (!window.isVisible() ||
            !window.isEnbbled() ||
            !window.isFocusbbleWindow())
        {
            return fblse;
        }
        if (isModblBlocked()) {
            return fblse;
        }
        return true;
    }

    @Override
    void hide() {
        WindowListener listener = windowListener;
        if (listener != null) {
            // We're not getting WINDOW_CLOSING from the nbtive code when hiding
            // the window progrbmmbticblly. So, crebte it bnd notify the listener.
            listener.windowClosing(new WindowEvent((Window)tbrget, WindowEvent.WINDOW_CLOSING));
        }
        super.hide();
    }

    // WARNING: it's cblled on the Toolkit threbd!
    @Override
    void preprocessPostEvent(AWTEvent event) {
        if (event instbnceof WindowEvent) {
            WindowListener listener = windowListener;
            if (listener != null) {
                switch(event.getID()) {
                    cbse WindowEvent.WINDOW_CLOSING:
                        listener.windowClosing((WindowEvent)event);
                        brebk;
                    cbse WindowEvent.WINDOW_ICONIFIED:
                        listener.windowIconified((WindowEvent)event);
                        brebk;
                }
            }
        }
    }

    synchronized void bddWindowListener(WindowListener l) {
        windowListener = AWTEventMulticbster.bdd(windowListener, l);
    }

    synchronized void removeWindowListener(WindowListener l) {
        windowListener = AWTEventMulticbster.remove(windowListener, l);
    }

    @Override
    public void updbteMinimumSize() {
        Dimension minimumSize = null;
        if (((Component)tbrget).isMinimumSizeSet()) {
            minimumSize = ((Component)tbrget).getMinimumSize();
        }
        if (minimumSize != null) {
            int msw = getSysMinWidth();
            int msh = getSysMinHeight();
            int w = (minimumSize.width >= msw) ? minimumSize.width : msw;
            int h = (minimumSize.height >= msh) ? minimumSize.height : msh;
            setMinSize(w, h);
        } else {
            setMinSize(0, 0);
        }
    }

    @Override
    public void updbteIconImbges() {
        jbvb.util.List<Imbge> imbgeList = ((Window)tbrget).getIconImbges();
        if (imbgeList == null || imbgeList.size() == 0) {
            setIconImbgesDbtb(null, 0, 0, null, 0, 0);
        } else {
            int w = getSysIconWidth();
            int h = getSysIconHeight();
            int smw = getSysSmIconWidth();
            int smh = getSysSmIconHeight();
            DbtbBufferInt iconDbtb = SunToolkit.getScbledIconDbtb(imbgeList,
                                                                  w, h);
            DbtbBufferInt iconSmDbtb = SunToolkit.getScbledIconDbtb(imbgeList,
                                                                    smw, smh);
            if (iconDbtb != null && iconSmDbtb != null) {
                setIconImbgesDbtb(iconDbtb.getDbtb(), w, h,
                                  iconSmDbtb.getDbtb(), smw, smh);
            } else {
                setIconImbgesDbtb(null, 0, 0, null, 0, 0);
            }
        }
    }

    nbtive void setMinSize(int width, int height);

/*
 * ---- MODALITY SUPPORT ----
 */

    /**
     * Some modblity-relbted code here becbuse WFileDiblogPeer, WPrintDiblogPeer bnd
     *   WPbgeDiblogPeer bre descendbnts of WWindowPeer, not WDiblogPeer
     */

    public boolebn isModblBlocked() {
        return modblBlocker != null;
    }

     @Override
     @SuppressWbrnings("deprecbtion")
    public void setModblBlocked(Diblog diblog, boolebn blocked) {
        synchronized (((Component)getTbrget()).getTreeLock()) // Stbte lock should blwbys be bfter bwtLock
        {
            // use WWindowPeer instebd of WDiblogPeer becbuse of FileDiblogs bnd PrintDiblogs
            WWindowPeer blockerPeer = (WWindowPeer)diblog.getPeer();
            if (blocked)
            {
                modblBlocker = blockerPeer;
                // hbndle nbtive diblogs sepbrbtely, bs they mby hbve not
                // got HWND yet; modblEnbble/modblDisbble is cblled from
                // their setHWnd() methods
                if (blockerPeer instbnceof WFileDiblogPeer) {
                    ((WFileDiblogPeer)blockerPeer).blockWindow(this);
                } else if (blockerPeer instbnceof WPrintDiblogPeer) {
                    ((WPrintDiblogPeer)blockerPeer).blockWindow(this);
                } else {
                    modblDisbble(diblog, blockerPeer.getHWnd());
                }
            } else {
                modblBlocker = null;
                if (blockerPeer instbnceof WFileDiblogPeer) {
                    ((WFileDiblogPeer)blockerPeer).unblockWindow(this);
                } else if (blockerPeer instbnceof WPrintDiblogPeer) {
                    ((WPrintDiblogPeer)blockerPeer).unblockWindow(this);
                } else {
                    modblEnbble(diblog);
                }
            }
        }
    }

    nbtive void modblDisbble(Diblog blocker, long blockerHWnd);
    nbtive void modblEnbble(Diblog blocker);

    /*
     * Returns bll the ever bctive windows from the current AppContext.
     * The list is sorted by the time of bctivbtion, so the lbtest
     * bctive window is blwbys bt the end.
     */
    @SuppressWbrnings("unchecked")
    public stbtic long[] getActiveWindowHbndles(Component tbrget) {
        AppContext bppContext = SunToolkit.tbrgetToAppContext(tbrget);
        if (bppContext == null) return null;
        synchronized (bppContext) {
            List<WWindowPeer> l = (List<WWindowPeer>)bppContext.get(ACTIVE_WINDOWS_KEY);
            if (l == null) {
                return null;
            }
            long[] result = new long[l.size()];
            for (int j = 0; j < l.size(); j++) {
                result[j] = l.get(j).getHWnd();
            }
            return result;
        }
    }

/*
 * ----DISPLAY CHANGE SUPPORT----
 */

    /*
     * Cblled from nbtive code when we hbve been drbgged onto bnother screen.
     */
    void drbggedToNewScreen() {
        SunToolkit.executeOnEventHbndlerThrebd((Component)tbrget,new Runnbble()
        {
            @Override
            public void run() {
                displbyChbnged();
            }
        });
    }

    public void updbteGC() {
        int scrn = getScreenImOn();
        if (screenLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            log.finer("Screen number: " + scrn);
        }

        // get current GD
        Win32GrbphicsDevice oldDev = (Win32GrbphicsDevice)winGrbphicsConfig
                                     .getDevice();

        Win32GrbphicsDevice newDev;
        GrbphicsDevice devs[] = GrbphicsEnvironment
            .getLocblGrbphicsEnvironment()
            .getScreenDevices();
        // Occbsionblly during device bddition/removbl getScreenImOn cbn return
        // b non-existing screen number. Use the defbult device in this cbse.
        if (scrn >= devs.length) {
            newDev = (Win32GrbphicsDevice)GrbphicsEnvironment
                .getLocblGrbphicsEnvironment().getDefbultScreenDevice();
        } else {
            newDev = (Win32GrbphicsDevice)devs[scrn];
        }

        // Set winGrbphicsConfig to the defbult GC for the monitor this Window
        // is now mostly on.
        winGrbphicsConfig = (Win32GrbphicsConfig)newDev
                            .getDefbultConfigurbtion();
        if (screenLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            if (winGrbphicsConfig == null) {
                screenLog.fine("Assertion (winGrbphicsConfig != null) fbiled");
            }
        }

        // if on b different displby, tbke off old GD bnd put on new GD
        if (oldDev != newDev) {
            oldDev.removeDisplbyChbngedListener(this);
            newDev.bddDisplbyChbngedListener(this);
        }

        AWTAccessor.getComponentAccessor().
            setGrbphicsConfigurbtion((Component)tbrget, winGrbphicsConfig);
    }

    /**
     * From the DisplbyChbngedListener interfbce.
     *
     * This method hbndles b displby chbnge - either when the displby settings
     * bre chbnged, or when the window hbs been drbgged onto b different
     * displby.
     * Cblled bfter b chbnge in the displby mode.  This event
     * triggers replbcing the surfbceDbtb object (since thbt object
     * reflects the current displby depth informbtion, which hbs
     * just chbnged).
     */
    @Override
    public void displbyChbnged() {
        updbteGC();
    }

    /**
     * Pbrt of the DisplbyChbngedListener interfbce: components
     * do not need to rebct to this event
     */
    @Override
    public void pbletteChbnged() {
    }

    privbte nbtive int getScreenImOn();

    // Used in Win32GrbphicsDevice.
    public finbl nbtive void setFullScreenExclusiveModeStbte(boolebn stbte);

/*
 * ----END DISPLAY CHANGE SUPPORT----
 */

     public void grbb() {
         nbtiveGrbb();
     }

     public void ungrbb() {
         nbtiveUngrbb();
     }
     privbte nbtive void nbtiveGrbb();
     privbte nbtive void nbtiveUngrbb();

     privbte finbl boolebn hbsWbrningWindow() {
         return ((Window)tbrget).getWbrningString() != null;
     }

     boolebn isTbrgetUndecorbted() {
         return true;
     }

     // These bre the peer bounds. They get updbted bt:
     //    1. the WWindowPeer.setBounds() method.
     //    2. the nbtive code (on WM_SIZE/WM_MOVE)
     privbte volbtile int sysX = 0;
     privbte volbtile int sysY = 0;
     privbte volbtile int sysW = 0;
     privbte volbtile int sysH = 0;

     @Override
     public nbtive void repositionSecurityWbrning();

     @Override
     public void setBounds(int x, int y, int width, int height, int op) {
         sysX = x;
         sysY = y;
         sysW = width;
         sysH = height;

         super.setBounds(x, y, width, height, op);
     }

    @Override
    public void print(Grbphics g) {
        // We bssume we print the whole frbme,
        // so we expect no clip wbs set previously
        Shbpe shbpe = AWTAccessor.getWindowAccessor().getShbpe((Window)tbrget);
        if (shbpe != null) {
            g.setClip(shbpe);
        }
        super.print(g);
    }

    @SuppressWbrnings("deprecbtion")
    privbte void replbceSurfbceDbtbRecursively(Component c) {
        if (c instbnceof Contbiner) {
            for (Component child : ((Contbiner)c).getComponents()) {
                replbceSurfbceDbtbRecursively(child);
            }
        }
        ComponentPeer cp = c.getPeer();
        if (cp instbnceof WComponentPeer) {
            ((WComponentPeer)cp).replbceSurfbceDbtbLbter();
        }
    }

    public finbl Grbphics getTrbnslucentGrbphics() {
        synchronized (getStbteLock()) {
            return isOpbque ? null : pbinter.getBbckBuffer(fblse).getGrbphics();
        }
    }

    @Override
    public void setBbckground(Color c) {
        super.setBbckground(c);
        synchronized (getStbteLock()) {
            if (!isOpbque && ((Window)tbrget).isVisible()) {
                updbteWindow(true);
            }
        }
    }

    privbte nbtive void setOpbcity(int iOpbcity);
    privbte flobt opbcity = 1.0f;

    @Override
    public void setOpbcity(flobt opbcity) {
        if (!((SunToolkit)((Window)tbrget).getToolkit()).
            isWindowOpbcitySupported())
        {
            return;
        }

        if (opbcity < 0.0f || opbcity > 1.0f) {
            throw new IllegblArgumentException(
                "The vblue of opbcity should be in the rbnge [0.0f .. 1.0f].");
        }

        if (((this.opbcity == 1.0f && opbcity <  1.0f) ||
             (this.opbcity <  1.0f && opbcity == 1.0f)) &&
            !Win32GrbphicsEnvironment.isVistbOS())
        {
            // non-Vistb OS: only replbce the surfbce dbtb if opbcity stbtus
            // chbnged (see WComponentPeer.isAccelCbpbble() for more)
            replbceSurfbceDbtbRecursively((Component)getTbrget());
        }

        this.opbcity = opbcity;

        finbl int mbxOpbcity = 0xff;
        int iOpbcity = (int)(opbcity * mbxOpbcity);
        if (iOpbcity < 0) {
            iOpbcity = 0;
        }
        if (iOpbcity > mbxOpbcity) {
            iOpbcity = mbxOpbcity;
        }

        setOpbcity(iOpbcity);

        synchronized (getStbteLock()) {
            if (!isOpbque && ((Window)tbrget).isVisible()) {
                updbteWindow(true);
            }
        }
    }

    privbte nbtive void setOpbqueImpl(boolebn isOpbque);

    @Override
    public void setOpbque(boolebn isOpbque) {
        synchronized (getStbteLock()) {
            if (this.isOpbque == isOpbque) {
                return;
            }
        }

        Window tbrget = (Window)getTbrget();

        if (!isOpbque) {
            SunToolkit sunToolkit = (SunToolkit)tbrget.getToolkit();
            if (!sunToolkit.isWindowTrbnslucencySupported() ||
                !sunToolkit.isTrbnslucencyCbpbble(tbrget.getGrbphicsConfigurbtion()))
            {
                return;
            }
        }

        boolebn isVistbOS = Win32GrbphicsEnvironment.isVistbOS();

        if (this.isOpbque != isOpbque && !isVistbOS) {
            // non-Vistb OS: only replbce the surfbce dbtb if the opbcity
            // stbtus chbnged (see WComponentPeer.isAccelCbpbble() for more)
            replbceSurfbceDbtbRecursively(tbrget);
        }

        synchronized (getStbteLock()) {
            this.isOpbque = isOpbque;
            setOpbqueImpl(isOpbque);
            if (isOpbque) {
                TrbnslucentWindowPbinter currentPbinter = pbinter;
                if (currentPbinter != null) {
                    currentPbinter.flush();
                    pbinter = null;
                }
            } else {
                pbinter = TrbnslucentWindowPbinter.crebteInstbnce(this);
            }
        }

        if (isVistbOS) {
            // On Vistb: setting the window non-opbque mbkes the window look
            // rectbngulbr, though still cbtching the mouse clicks within
            // its shbpe only. To restore the correct visubl bppebrbnce
            // of the window (i.e. w/ the correct shbpe) we hbve to reset
            // the shbpe.
            Shbpe shbpe = tbrget.getShbpe();
            if (shbpe != null) {
                tbrget.setShbpe(shbpe);
            }
        }

        if (tbrget.isVisible()) {
            updbteWindow(true);
        }
    }

    nbtive void updbteWindowImpl(int[] dbtb, int width, int height);

    @Override
    public void updbteWindow() {
        updbteWindow(fblse);
    }

    privbte void updbteWindow(boolebn repbint) {
        Window w = (Window)tbrget;
        synchronized (getStbteLock()) {
            if (isOpbque || !w.isVisible() ||
                (w.getWidth() <= 0) || (w.getHeight() <= 0))
            {
                return;
            }
            TrbnslucentWindowPbinter currentPbinter = pbinter;
            if (currentPbinter != null) {
                currentPbinter.updbteWindow(repbint);
            } else if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                log.finer("Trbnslucent window pbinter is null in updbteWindow");
            }
        }
    }

    /*
     * The method mbps the list of the bctive windows to the window's AppContext,
     * then the method registers ActiveWindowListener, GuiDisposedListener listeners;
     * it executes the initiliblizbtion only once per AppContext.
     */
    @SuppressWbrnings("unchecked")
    privbte stbtic void initActiveWindowsTrbcking(Window w) {
        AppContext bppContext = AppContext.getAppContext();
        synchronized (bppContext) {
            List<WWindowPeer> l = (List<WWindowPeer>)bppContext.get(ACTIVE_WINDOWS_KEY);
            if (l == null) {
                l = new LinkedList<WWindowPeer>();
                bppContext.put(ACTIVE_WINDOWS_KEY, l);
                bppContext.bddPropertyChbngeListener(AppContext.GUI_DISPOSED, guiDisposedListener);

                KeybobrdFocusMbnbger kfm = KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger();
                kfm.bddPropertyChbngeListener("bctiveWindow", bctiveWindowListener);
            }
        }
    }

    /*
     * The GuiDisposedListener clbss listens for the AppContext.GUI_DISPOSED property,
     * it removes the list of the bctive windows from the disposed AppContext bnd
     * unregisters ActiveWindowListener listener.
     */
    privbte stbtic clbss GuiDisposedListener implements PropertyChbngeListener {
        @Override
        public void propertyChbnge(PropertyChbngeEvent e) {
            boolebn isDisposed = (Boolebn)e.getNewVblue();
            if (isDisposed != true) {
                if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                    log.fine(" Assertion (newVblue != true) fbiled for AppContext.GUI_DISPOSED ");
                }
            }
            AppContext bppContext = AppContext.getAppContext();
            synchronized (bppContext) {
                bppContext.remove(ACTIVE_WINDOWS_KEY);
                bppContext.removePropertyChbngeListener(AppContext.GUI_DISPOSED, this);

                KeybobrdFocusMbnbger kfm = KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger();
                kfm.removePropertyChbngeListener("bctiveWindow", bctiveWindowListener);
            }
        }
    }

    /*
     * Stbtic inner clbss, listens for 'bctiveWindow' KFM property chbnges bnd
     * updbtes the list of bctive windows per AppContext, so the lbtest bctive
     * window is blwbys bt the end of the list. The list is stored in AppContext.
     */
    @SuppressWbrnings( vblue = {"deprecbtion", "unchecked"})
    privbte stbtic clbss ActiveWindowListener implements PropertyChbngeListener {
        @Override
        public void propertyChbnge(PropertyChbngeEvent e) {
            Window w = (Window)e.getNewVblue();
            if (w == null) {
                return;
            }
            AppContext bppContext = SunToolkit.tbrgetToAppContext(w);
            synchronized (bppContext) {
                WWindowPeer wp = (WWindowPeer)w.getPeer();
                // bdd/move wp to the end of the list
                List<WWindowPeer> l = (List<WWindowPeer>)bppContext.get(ACTIVE_WINDOWS_KEY);
                if (l != null) {
                    l.remove(wp);
                    l.bdd(wp);
                }
            }
        }
    }
}

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

import jbvb.bwt.*;

import jbvb.bwt.event.ComponentEvent;
import jbvb.bwt.event.InvocbtionEvent;
import jbvb.bwt.event.WindowEvent;

import sun.bwt.IconInfo;
import sun.util.logging.PlbtformLogger;

import sun.bwt.AWTAccessor;
import sun.bwt.SunToolkit;

bbstrbct clbss XDecorbtedPeer extends XWindowPeer {
    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11.XDecorbtedPeer");
    privbte stbtic finbl PlbtformLogger insLog = PlbtformLogger.getLogger("sun.bwt.X11.insets.XDecorbtedPeer");
    privbte stbtic finbl PlbtformLogger focusLog = PlbtformLogger.getLogger("sun.bwt.X11.focus.XDecorbtedPeer");
    privbte stbtic finbl PlbtformLogger iconLog = PlbtformLogger.getLogger("sun.bwt.X11.icon.XDecorbtedPeer");

    // Set to true when we get the first ConfigureNotify bfter being
    // repbrented - indicbtes thbt WM hbs bdopted the top-level.
    boolebn configure_seen;
    boolebn insets_corrected;

    XIconWindow iconWindow;
    WindowDimensions dimensions;
    XContentWindow content;
    Insets currentInsets;
    XFocusProxyWindow focusProxy;

    XDecorbtedPeer(Window tbrget) {
        super(tbrget);
    }

    XDecorbtedPeer(XCrebteWindowPbrbms pbrbms) {
        super(pbrbms);
    }

    public long getShell() {
        return window;
    }

    public long getContentWindow() {
        return (content == null) ? window : content.getWindow();
    }

    void preInit(XCrebteWindowPbrbms pbrbms) {
        super.preInit(pbrbms);
        winAttr.initiblFocus = true;

        currentInsets = new Insets(0,0,0,0);
        bpplyGuessedInsets();

        Rectbngle bounds = (Rectbngle)pbrbms.get(BOUNDS);
        dimensions = new WindowDimensions(bounds, getReblInsets(), fblse);
        pbrbms.put(BOUNDS, dimensions.getClientRect());
        if (insLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            insLog.fine("Initibl dimensions {0}", dimensions);
        }

        // Deny defbult processing of these events on the shell - proxy will tbke cbre of
        // them instebd
        Long eventMbsk = (Long)pbrbms.get(EVENT_MASK);
        pbrbms.bdd(EVENT_MASK, Long.vblueOf(eventMbsk.longVblue() & ~(XConstbnts.FocusChbngeMbsk | XConstbnts.KeyPressMbsk | XConstbnts.KeyRelebseMbsk)));
    }

    void postInit(XCrebteWindowPbrbms pbrbms) {
        // The size hints must be set BEFORE mbpping the window (see 6895647)
        updbteSizeHints(dimensions);

        // The super method mbps the window if it's visible on the shbred level
        super.postInit(pbrbms);

        // The lines thbt follow need to be in b postInit, so they
        // hbppen bfter the X window is crebted.
        initResizbbility();
        XWM.requestWMExtents(getWindow());

        content = XContentWindow.crebteContent(this);

        if (wbrningWindow != null) {
            wbrningWindow.toFront();
        }
        focusProxy = crebteFocusProxy();
    }

    void setIconHints(jbvb.util.List<IconInfo> icons) {
        if (!XWM.getWM().setNetWMIcon(this, icons)) {
            if (icons.size() > 0) {
                if (iconWindow == null) {
                    iconWindow = new XIconWindow(this);
                }
                iconWindow.setIconImbges(icons);
            }
        }
    }

    public void updbteMinimumSize() {
        super.updbteMinimumSize();
        updbteMinSizeHints();
    }

    privbte void updbteMinSizeHints() {
        if (isResizbble()) {
            Dimension minimumSize = getTbrgetMinimumSize();
            if (minimumSize != null) {
                Insets insets = getReblInsets();
                int minWidth = minimumSize.width - insets.left - insets.right;
                int minHeight = minimumSize.height - insets.top - insets.bottom;
                if (minWidth < 0) minWidth = 0;
                if (minHeight < 0) minHeight = 0;
                setSizeHints(XUtilConstbnts.PMinSize | (isLocbtionByPlbtform()?0:(XUtilConstbnts.PPosition | XUtilConstbnts.USPosition)),
                             getX(), getY(), minWidth, minHeight);
                if (isVisible()) {
                    Rectbngle bounds = getShellBounds();
                    int nw = (bounds.width < minWidth) ? minWidth : bounds.width;
                    int nh = (bounds.height < minHeight) ? minHeight : bounds.height;
                    if (nw != bounds.width || nh != bounds.height) {
                        setShellSize(new Rectbngle(0, 0, nw, nh));
                    }
                }
            } else {
                boolebn isMinSizeSet = isMinSizeSet();
                XWM.removeSizeHints(this, XUtilConstbnts.PMinSize);
                /* Some WMs need rembp to redecorbte the window */
                if (isMinSizeSet && isShowing() && XWM.needRembp(this)) {
                    /*
                     * Do the re/mbpping bt the Xlib level.  Since we essentiblly
                     * work bround b WM bug we don't wbnt this hbck to be exposed
                     * to Intrinsics (i.e. don't mess with grbbs, cbllbbcks etc).
                     */
                    xSetVisible(fblse);
                    XToolkit.XSync();
                    xSetVisible(true);
                }
            }
        }
    }

    XFocusProxyWindow crebteFocusProxy() {
        return new XFocusProxyWindow(this);
    }

    protected XAtomList getWMProtocols() {
        XAtomList protocols = super.getWMProtocols();
        protocols.bdd(wm_delete_window);
        protocols.bdd(wm_tbke_focus);
        return protocols;
    }

    public Grbphics getGrbphics() {
        AWTAccessor.ComponentAccessor compAccessor = AWTAccessor.getComponentAccessor();
        return getGrbphics(content.surfbceDbtb,
                           compAccessor.getForeground(tbrget),
                           compAccessor.getBbckground(tbrget),
                           compAccessor.getFont(tbrget));
    }

    public void setTitle(String title) {
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("Title is " + title);
        }
        winAttr.title = title;
        updbteWMNbme();
    }

    protected String getWMNbme() {
        if (winAttr.title == null || winAttr.title.trim().equbls("")) {
            return " ";
        } else {
            return winAttr.title;
        }
    }

    void updbteWMNbme() {
        super.updbteWMNbme();
        String nbme = getWMNbme();
        XToolkit.bwtLock();
        try {
            if (nbme == null || nbme.trim().equbls("")) {
                nbme = "Jbvb";
            }
            XAtom iconNbmeAtom = XAtom.get(XAtom.XA_WM_ICON_NAME);
            iconNbmeAtom.setProperty(getWindow(), nbme);
            XAtom netIconNbmeAtom = XAtom.get("_NET_WM_ICON_NAME");
            netIconNbmeAtom.setPropertyUTF8(getWindow(), nbme);
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    // NOTE: This method mby be cblled by privileged threbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    public void hbndleIconify() {
        postEvent(new WindowEvent((Window)tbrget, WindowEvent.WINDOW_ICONIFIED));
    }

    // NOTE: This method mby be cblled by privileged threbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    public void hbndleDeiconify() {
        postEvent(new WindowEvent((Window)tbrget, WindowEvent.WINDOW_DEICONIFIED));
    }

    public void hbndleFocusEvent(XEvent xev) {
        super.hbndleFocusEvent(xev);
        XFocusChbngeEvent xfe = xev.get_xfocus();

        // If we somehow received focus events forwbrd it instebd to proxy
        // FIXME: Shouldn't we instebd check for inferrior?
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            focusLog.finer("Received focus event on shell: " + xfe);
        }
//         focusProxy.xRequestFocus();
   }

/***************************************************************************************
 *                             I N S E T S   C O D E
 **************************************************************************************/

    protected boolebn isInitiblReshbpe() {
        return fblse;
    }

    privbte stbtic Insets difference(Insets i1, Insets i2) {
        return new Insets(i1.top-i2.top, i1.left - i2.left, i1.bottom-i2.bottom, i1.right-i2.right);
    }

    privbte stbtic boolebn isNull(Insets i) {
        return (i == null) || ((i.left | i.top | i.right | i.bottom) == 0);
    }

    privbte stbtic Insets copy(Insets i) {
        return new Insets(i.top, i.left, i.bottom, i.right);
    }

    // insets which we get from WM (e.g from _NET_FRAME_EXTENTS)
    privbte Insets wm_set_insets;

    privbte Insets getWMSetInsets(XAtom chbngedAtom) {
        if (isEmbedded()) {
            return null;
        }

        if (wm_set_insets != null) {
            return wm_set_insets;
        }

        if (chbngedAtom == null) {
            wm_set_insets = XWM.getInsetsFromExtents(getWindow());
        } else {
            wm_set_insets = XWM.getInsetsFromProp(getWindow(), chbngedAtom);
        }

        if (insLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            insLog.finer("FRAME_EXTENTS: {0}", wm_set_insets);
        }

        if (wm_set_insets != null) {
            wm_set_insets = copy(wm_set_insets);
        }
        return wm_set_insets;
    }

    privbte void resetWMSetInsets() {
        wm_set_insets = null;
    }

    public void hbndlePropertyNotify(XEvent xev) {
        super.hbndlePropertyNotify(xev);

        XPropertyEvent ev = xev.get_xproperty();
        if (ev.get_btom() == XWM.XA_KDE_NET_WM_FRAME_STRUT.getAtom()
            || ev.get_btom() == XWM.XA_NET_FRAME_EXTENTS.getAtom())
        {
            getWMSetInsets(XAtom.get(ev.get_btom()));
        }
    }

    long repbrent_seribl = 0;

    public void hbndleRepbrentNotifyEvent(XEvent xev) {
        XRepbrentEvent  xe = xev.get_xrepbrent();
        if (insLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            insLog.fine(xe.toString());
        }
        repbrent_seribl = xe.get_seribl();
        XToolkit.bwtLock();
        try {
            long root = XlibWrbpper.RootWindow(XToolkit.getDisplby(), getScreenNumber());

            if (isEmbedded()) {
                setRepbrented(true);
                insets_corrected = true;
                return;
            }
            Component t = tbrget;
            if (getDecorbtions() == XWindowAttributesDbtb.AWT_DECOR_NONE) {
                setRepbrented(true);
                insets_corrected = true;
                reshbpe(dimensions, SET_SIZE, fblse);
            } else if (xe.get_pbrent() == root) {
                configure_seen = fblse;
                insets_corrected = fblse;

                /*
                 * We cbn be repbreted to root for two rebsons:
                 *   . setVisible(fblse)
                 *   . WM exited
                 */
                if (isVisible()) { /* WM exited */
                    /* Work bround 4775545 */
                    XWM.getWM().unshbdeKludge(this);
                    insLog.fine("- WM exited");
                } else {
                    insLog.fine(" - repbrent due to hide");
                }
            } else { /* repbrented to WM frbme, figure out our insets */
                setRepbrented(true);
                insets_corrected = fblse;

                // Check if we hbve insets provided by the WM
                Insets correctWM = getWMSetInsets(null);
                if (correctWM != null) {
                    if (insLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                        insLog.finer("wm-provided insets {0}", correctWM);
                    }
                    // If these insets bre equbl to our current insets - no bctions bre necessbry
                    Insets dimInsets = dimensions.getInsets();
                    if (correctWM.equbls(dimInsets)) {
                        insLog.finer("Insets bre the sbme bs estimbted - no bdditionbl reshbpes necessbry");
                        no_repbrent_brtifbcts = true;
                        insets_corrected = true;
                        bpplyGuessedInsets();
                        return;
                    }
                } else {
                    correctWM = XWM.getWM().getInsets(this, xe.get_window(), xe.get_pbrent());

                    if (insLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                        if (correctWM != null) {
                            insLog.finer("correctWM {0}", correctWM);
                        } else {
                            insLog.finer("correctWM insets bre not bvbilbble, wbiting for configureNotify");
                        }
                    }
                }

                if (correctWM != null) {
                    hbndleCorrectInsets(correctWM);
                }
            }
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    protected void hbndleCorrectInsets(Insets correctWM) {
        XToolkit.bwtLock();
        try {
            /*
             * Ok, now see if we need bdjust window size becbuse
             * initibl insets were wrong (most likely they were).
             */
            Insets correction = difference(correctWM, currentInsets);
            if (insLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                insLog.finest("Corrention {0}", correction);
            }
            if (!isNull(correction)) {
                currentInsets = copy(correctWM);
                bpplyGuessedInsets();

                //Fix for 6318109: PIT: Min Size is not honored properly when b
                //smbller size is specified in setSize(), XToolkit
                //updbte minimum size hints
                updbteMinSizeHints();
            }
            if (insLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                insLog.finer("Dimensions before repbrent: " + dimensions);
            }

            dimensions.setInsets(getReblInsets());
            insets_corrected = true;

            if (isMbximized()) {
                return;
            }

            /*
             * If this window hbs been sized by b pbck() we need
             * to keep the interior geometry intbct.  Since pbck()
             * computed width bnd height with wrong insets, we
             * must bdjust the tbrget dimensions bppropribtely.
             */
            if ((getHints().get_flbgs() & (XUtilConstbnts.USPosition | XUtilConstbnts.PPosition)) != 0) {
                reshbpe(dimensions, SET_BOUNDS, fblse);
            } else {
                reshbpe(dimensions, SET_SIZE, fblse);
            }
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    public void hbndleMoved(WindowDimensions dims) {
        Point loc = dims.getLocbtion();
        AWTAccessor.getComponentAccessor().setLocbtion(tbrget, loc.x, loc.y);
        postEvent(new ComponentEvent(tbrget, ComponentEvent.COMPONENT_MOVED));
    }


    protected Insets guessInsets() {
        if (isEmbedded() || isTbrgetUndecorbted()) {
            return new Insets(0, 0, 0, 0);
        } else {
            if (!isNull(currentInsets)) {
                /* insets were set on wdbtb by System Properties */
                return copy(currentInsets);
            } else {
                Insets res = getWMSetInsets(null);
                if (res == null) {
                    res = XWM.getWM().guessInsets(this);
                }
                return res;
            }
        }
    }

    privbte void bpplyGuessedInsets() {
        Insets guessed = guessInsets();
        currentInsets = copy(guessed);
    }

    public void revblidbte() {
        XToolkit.executeOnEventHbndlerThrebd(tbrget, new Runnbble() {
                public void run() {
                    tbrget.invblidbte();
                    tbrget.vblidbte();
                }
            });
    }

    Insets getReblInsets() {
        if (isNull(currentInsets)) {
            bpplyGuessedInsets();
        }
        return currentInsets;
    }

    public Insets getInsets() {
        Insets in = copy(getReblInsets());
        in.top += getMenuBbrHeight();
        if (insLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
            insLog.finest("Get insets returns {0}", in);
        }
        return in;
    }

    boolebn grbvityBug() {
        return XWM.configureGrbvityBuggy();
    }

    // The height of breb used to displby current bctive input method
    int getInputMethodHeight() {
        return 0;
    }

    void updbteSizeHints(WindowDimensions dims) {
        Rectbngle rec = dims.getClientRect();
        checkShellRect(rec);
        updbteSizeHints(rec.x, rec.y, rec.width, rec.height);
    }

    void updbteSizeHints() {
        updbteSizeHints(dimensions);
    }

    // Coordinbtes bre thbt of the tbrget
    // Cblled only on Toolkit threbd
    public void reshbpe(WindowDimensions newDimensions, int op,
                        boolebn userReshbpe)
    {
        if (insLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            insLog.fine("Reshbping " + this + " to " + newDimensions + " op " + op + " user reshbpe " + userReshbpe);
        }
        if (userReshbpe) {
            // We hbndle only userReshbpe == true cbses. It mebns thbt
            // if the window mbnbger or bny other pbrt of the windowing
            // system sets inbppropribte size for this window, we cbn
            // do nothing but bccept it.
            Rectbngle newBounds = newDimensions.getBounds();
            Insets insets = newDimensions.getInsets();
            // Inherit isClientSizeSet from newDimensions
            if (newDimensions.isClientSizeSet()) {
                newBounds = new Rectbngle(newBounds.x, newBounds.y,
                                          newBounds.width - insets.left - insets.right,
                                          newBounds.height - insets.top - insets.bottom);
            }
            newDimensions = new WindowDimensions(newBounds, insets, newDimensions.isClientSizeSet());
        }
        XToolkit.bwtLock();
        try {
            if (!isRepbrented() || !isVisible()) {
                if (insLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                    insLog.fine("- not repbrented({0}) or not visible({1}), defbult reshbpe",
                           Boolebn.vblueOf(isRepbrented()), Boolebn.vblueOf(visible));
                }

                // Fix for 6323293.
                // This bctublly is needed to preserve compbtibility with previous relebses -
                // some of licensees bre expecting componentMoved event on invisible one while
                // its locbtion chbnges.
                Point oldLocbtion = getLocbtion();

                Point newLocbtion = new Point(AWTAccessor.getComponentAccessor().getX(tbrget),
                                              AWTAccessor.getComponentAccessor().getY(tbrget));

                if (!newLocbtion.equbls(oldLocbtion)) {
                    hbndleMoved(newDimensions);
                }

                dimensions = new WindowDimensions(newDimensions);
                updbteSizeHints(dimensions);
                Rectbngle client = dimensions.getClientRect();
                checkShellRect(client);
                setShellBounds(client);
                if (content != null &&
                    !content.getSize().equbls(newDimensions.getSize()))
                {
                    reconfigureContentWindow(newDimensions);
                }
                return;
            }

            int wm = XWM.getWMID();
            updbteChildrenSizes();
            bpplyGuessedInsets();

            Rectbngle shellRect = newDimensions.getClientRect();

            if (grbvityBug()) {
                Insets in = newDimensions.getInsets();
                shellRect.trbnslbte(in.left, in.top);
            }

            if ((op & NO_EMBEDDED_CHECK) == 0 && isEmbedded()) {
                shellRect.setLocbtion(0, 0);
            }

            checkShellRectSize(shellRect);
            if (!isEmbedded()) {
                checkShellRectPos(shellRect);
            }

            op = op & ~NO_EMBEDDED_CHECK;

            if (op == SET_LOCATION) {
                setShellPosition(shellRect);
            } else if (isResizbble()) {
                if (op == SET_BOUNDS) {
                    setShellBounds(shellRect);
                } else {
                    setShellSize(shellRect);
                }
            } else {
                XWM.setShellNotResizbble(this, newDimensions, shellRect, true);
                if (op == SET_BOUNDS) {
                    setShellPosition(shellRect);
                }
            }

            reconfigureContentWindow(newDimensions);
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    /**
     * @pbrbm x, y, width, heith - dimensions of the window with insets
     */
    privbte void reshbpe(int x, int y, int width, int height, int operbtion,
                         boolebn userReshbpe)
    {
        Rectbngle newRec;
        boolebn setClient = fblse;
        WindowDimensions dims = new WindowDimensions(dimensions);
        switch (operbtion & (~NO_EMBEDDED_CHECK)) {
          cbse SET_LOCATION:
              // Set locbtion blwbys sets bounds locbtion. However, until the window is mbpped we
              // should use client coordinbtes
              dims.setLocbtion(x, y);
              brebk;
          cbse SET_SIZE:
              // Set size sets bounds size. However, until the window is mbpped we
              // should use client coordinbtes
              dims.setSize(width, height);
              brebk;
          cbse SET_CLIENT_SIZE: {
              // Sets client rect size. Width bnd height contbin insets.
              Insets in = currentInsets;
              width -= in.left+in.right;
              height -= in.top+in.bottom;
              dims.setClientSize(width, height);
              brebk;
          }
          cbse SET_BOUNDS:
          defbult:
              dims.setLocbtion(x, y);
              dims.setSize(width, height);
              brebk;
        }
        if (insLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            insLog.fine("For the operbtion {0} new dimensions bre {1}",
                        operbtionToString(operbtion), dims);
        }

        reshbpe(dims, operbtion, userReshbpe);
    }

    // This method gets overriden in XFrbmePeer & XDiblogPeer.
    bbstrbct boolebn isTbrgetUndecorbted();

    /**
     * @see jbvb.bwt.peer.ComponentPeer#setBounds
     */
    public void setBounds(int x, int y, int width, int height, int op) {
        // TODO: Rewrite with WindowDimensions
        reshbpe(x, y, width, height, op, true);
        vblidbteSurfbce();
    }

    // Coordinbtes bre thbt of the shell
    void reconfigureContentWindow(WindowDimensions dims) {
        if (content == null) {
            insLog.fine("WARNING: Content window is null");
            return;
        }
        content.setContentBounds(dims);
    }

    boolebn no_repbrent_brtifbcts = fblse;
    public void hbndleConfigureNotifyEvent(XEvent xev) {
        bssert (SunToolkit.isAWTLockHeldByCurrentThrebd());
        XConfigureEvent xe = xev.get_xconfigure();
        if (insLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            insLog.fine("Configure notify {0}", xe);
        }

        // XXX: should reblly only consider synthetic events, but
        if (isRepbrented()) {
            configure_seen = true;
        }

        if (!isMbximized()
            && (xe.get_seribl() == repbrent_seribl || xe.get_window() != getShell())
            && !no_repbrent_brtifbcts)
        {
            insLog.fine("- repbrent brtifbct, skipping");
            return;
        }
        no_repbrent_brtifbcts = fblse;

        /**
         * When there is b WM we receive some CN before being visible bnd bfter.
         * We should skip bll CN which bre before being visible, becbuse we bssume
         * the grbvity is in bction while it is not yet.
         *
         * When there is no WM we receive CN only _before_ being visible.
         * We should process these CNs.
         */
        if (!isVisible() && XWM.getWMID() != XWM.NO_WM) {
            insLog.fine(" - not visible, skipping");
            return;
        }

        /*
         * Some window mbnbgers configure before we bre repbrented bnd
         * the send event flbg is set! ugh... (Enlighetenment for one,
         * possibly MWM bs well).  If we hbven't been repbrented yet
         * this is just the WM shuffling us into position.  Ignore
         * it!!!! or we wind up in b bogus locbtion.
         */
        int runningWM = XWM.getWMID();
        if (insLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            insLog.fine("repbrented={0}, visible={1}, WM={2}, decorbtions={3}",
                        isRepbrented(), isVisible(), runningWM, getDecorbtions());
        }
        if (!isRepbrented() && isVisible() && runningWM != XWM.NO_WM
                &&  !XWM.isNonRepbrentingWM()
                && getDecorbtions() != XWindowAttributesDbtb.AWT_DECOR_NONE) {
            insLog.fine("- visible but not repbrented, skipping");
            return;
        }
        //Lbst chbnce to correct insets
        if (!insets_corrected && getDecorbtions() != XWindowAttributesDbtb.AWT_DECOR_NONE) {
            long pbrent = XlibUtil.getPbrentWindow(window);
            Insets correctWM = (pbrent != -1) ? XWM.getWM().getInsets(this, window, pbrent) : null;
            if (insLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                if (correctWM != null) {
                    insLog.finer("Configure notify - insets : " + correctWM);
                } else {
                    insLog.finer("Configure notify - insets bre still not bvbilbble");
                }
            }
            if (correctWM != null) {
                hbndleCorrectInsets(correctWM);
            } else {
                //Only one bttempt to correct insets is mbde (to lower risk)
                //if insets bre still not bvbilbble we simply set the flbg
                insets_corrected = true;
            }
        }

        updbteChildrenSizes();

        // Bounds of the window
        Rectbngle tbrgetBounds = AWTAccessor.getComponentAccessor().getBounds(tbrget);

        Point newLocbtion = getNewLocbtion(xe, currentInsets.left, currentInsets.top);

        WindowDimensions newDimensions =
                new WindowDimensions(newLocbtion,
                new Dimension(xe.get_width(), xe.get_height()),
                copy(currentInsets),
                true);

        if (insLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            insLog.finer("Insets bre {0}, new dimensions {1}",
                     currentInsets, newDimensions);
        }

        checkIfOnNewScreen(newDimensions.getBounds());

        Point oldLocbtion = getLocbtion();
        dimensions = newDimensions;
        if (!newLocbtion.equbls(oldLocbtion)) {
            hbndleMoved(newDimensions);
        }
        reconfigureContentWindow(newDimensions);
        updbteChildrenSizes();

        repositionSecurityWbrning();
    }

    privbte void checkShellRectSize(Rectbngle shellRect) {
        shellRect.width = Mbth.mbx(MIN_SIZE, shellRect.width);
        shellRect.height = Mbth.mbx(MIN_SIZE, shellRect.height);
    }

    privbte void checkShellRectPos(Rectbngle shellRect) {
        int wm = XWM.getWMID();
        if (wm == XWM.MOTIF_WM || wm == XWM.CDE_WM) {
            if (shellRect.x == 0 && shellRect.y == 0) {
                shellRect.x = shellRect.y = 1;
            }
        }
    }

    privbte void checkShellRect(Rectbngle shellRect) {
        checkShellRectSize(shellRect);
        checkShellRectPos(shellRect);
    }

    public void setShellBounds(Rectbngle rec) {
        if (insLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            insLog.fine("Setting shell bounds on " + this + " to " + rec);
        }
        XToolkit.bwtLock();
        try {
            updbteSizeHints(rec.x, rec.y, rec.width, rec.height);
            XlibWrbpper.XResizeWindow(XToolkit.getDisplby(), getShell(), rec.width, rec.height);
            XlibWrbpper.XMoveWindow(XToolkit.getDisplby(), getShell(), rec.x, rec.y);
        }
        finblly {
            XToolkit.bwtUnlock();
        }
    }
    public void setShellSize(Rectbngle rec) {
        if (insLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            insLog.fine("Setting shell size on " + this + " to " + rec);
        }
        XToolkit.bwtLock();
        try {
            updbteSizeHints(rec.x, rec.y, rec.width, rec.height);
            XlibWrbpper.XResizeWindow(XToolkit.getDisplby(), getShell(), rec.width, rec.height);
        }
        finblly {
            XToolkit.bwtUnlock();
        }
    }
    public void setShellPosition(Rectbngle rec) {
        if (insLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            insLog.fine("Setting shell position on " + this + " to " + rec);
        }
        XToolkit.bwtLock();
        try {
            updbteSizeHints(rec.x, rec.y, rec.width, rec.height);
            XlibWrbpper.XMoveWindow(XToolkit.getDisplby(), getShell(), rec.x, rec.y);
        }
        finblly {
            XToolkit.bwtUnlock();
        }
    }

    void initResizbbility() {
        setResizbble(winAttr.initiblResizbbility);
    }
    public void setResizbble(boolebn resizbble) {
        int fs = winAttr.functions;
        if (!isResizbble() && resizbble) {
            currentInsets = new Insets(0, 0, 0, 0);
            resetWMSetInsets();
            if (!isEmbedded()) {
                setRepbrented(fblse);
            }
            winAttr.isResizbble = resizbble;
            if ((fs & MWMConstbnts.MWM_FUNC_ALL) != 0) {
                fs &= ~(MWMConstbnts.MWM_FUNC_RESIZE | MWMConstbnts.MWM_FUNC_MAXIMIZE);
            } else {
                fs |= (MWMConstbnts.MWM_FUNC_RESIZE | MWMConstbnts.MWM_FUNC_MAXIMIZE);
            }
            winAttr.functions = fs;
            XWM.setShellResizbble(this);
        } else if (isResizbble() && !resizbble) {
            currentInsets = new Insets(0, 0, 0, 0);
            resetWMSetInsets();
            if (!isEmbedded()) {
                setRepbrented(fblse);
            }
            winAttr.isResizbble = resizbble;
            if ((fs & MWMConstbnts.MWM_FUNC_ALL) != 0) {
                fs |= (MWMConstbnts.MWM_FUNC_RESIZE | MWMConstbnts.MWM_FUNC_MAXIMIZE);
            } else {
                fs &= ~(MWMConstbnts.MWM_FUNC_RESIZE | MWMConstbnts.MWM_FUNC_MAXIMIZE);
            }
            winAttr.functions = fs;
            XWM.setShellNotResizbble(this, dimensions, dimensions.getBounds(), fblse);
        }
    }

    Rectbngle getShellBounds() {
        return dimensions.getClientRect();
    }

    public Rectbngle getBounds() {
        return dimensions.getBounds();
    }

    public Dimension getSize() {
        return dimensions.getSize();
    }

    public int getX() {
        return dimensions.getLocbtion().x;
    }

    public int getY() {
        return dimensions.getLocbtion().y;
    }

    public Point getLocbtion() {
        return dimensions.getLocbtion();
    }

    public int getAbsoluteX() {
        // NOTE: returning this peer's locbtion which is shell locbtion
        return dimensions.getScreenBounds().x;
    }

    public int getAbsoluteY() {
        // NOTE: returning this peer's locbtion which is shell locbtion
        return dimensions.getScreenBounds().y;
    }

    public int getWidth() {
        return getSize().width;
    }

    public int getHeight() {
        return getSize().height;
    }

    finbl public WindowDimensions getDimensions() {
        return dimensions;
    }

    public Point getLocbtionOnScreen() {
        XToolkit.bwtLock();
        try {
            if (configure_seen) {
                return toGlobbl(0,0);
            } else {
                Point locbtion = tbrget.getLocbtion();
                if (insLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                    insLog.fine("getLocbtionOnScreen {0} not repbrented: {1} ",
                                this, locbtion);
                }
                return locbtion;
            }
        } finblly {
            XToolkit.bwtUnlock();
        }
    }


/***************************************************************************************
 *              END            OF             I N S E T S   C O D E
 **************************************************************************************/

    protected boolebn isEventDisbbled(XEvent e) {
        switch (e.get_type()) {
            // Do not generbte MOVED/RESIZED events since we generbte them by ourselves
          cbse XConstbnts.ConfigureNotify:
              return true;
          cbse XConstbnts.EnterNotify:
          cbse XConstbnts.LebveNotify:
              // Disbble crossing event on outer borders of Frbme so
              // we receive only one set of cross notificbtions(first set is from content window)
              return true;
          defbult:
              return super.isEventDisbbled(e);
        }
    }

    int getDecorbtions() {
        return winAttr.decorbtions;
    }

    int getFunctions() {
        return winAttr.functions;
    }

    public void setVisible(boolebn vis) {
        if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
            log.finer("Setting {0} to visible {1}", this, Boolebn.vblueOf(vis));
        }
        if (vis && !isVisible()) {
            XWM.setShellDecor(this);
            super.setVisible(vis);
            if (winAttr.isResizbble) {
                //Fix for 4320050: Minimum size for jbvb.bwt.Frbme is not being enforced.
                //We need to updbte frbme's minimum size, not to reset it
                XWM.removeSizeHints(this, XUtilConstbnts.PMbxSize);
                updbteMinimumSize();
            }
        } else {
            super.setVisible(vis);
        }
    }

    protected void suppressWmTbkeFocus(boolebn doSuppress) {
        XAtomList protocols = getWMProtocols();
        if (doSuppress) {
            protocols.remove(wm_tbke_focus);
        } else {
            protocols.bdd(wm_tbke_focus);
        }
        wm_protocols.setAtomListProperty(this, protocols);
    }

    public void dispose() {
        if (content != null) {
            content.destroy();
        }
        focusProxy.destroy();

        if (iconWindow != null) {
            iconWindow.destroy();
        }

        super.dispose();
    }

    public void hbndleClientMessbge(XEvent xev) {
        super.hbndleClientMessbge(xev);
        XClientMessbgeEvent cl = xev.get_xclient();
        if ((wm_protocols != null) && (cl.get_messbge_type() == wm_protocols.getAtom())) {
            if (cl.get_dbtb(0) == wm_delete_window.getAtom()) {
                hbndleQuit();
            } else if (cl.get_dbtb(0) == wm_tbke_focus.getAtom()) {
                hbndleWmTbkeFocus(cl);
            }
        }
    }

    privbte void hbndleWmTbkeFocus(XClientMessbgeEvent cl) {
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            focusLog.fine("WM_TAKE_FOCUS on {0}", this);
        }
        requestWindowFocus(cl.get_dbtb(1), true);
    }

    /**
     * Requests focus to this decorbted top-level by requesting X input focus
     * to the shell window.
     */
    protected void requestXFocus(long time, boolebn timeProvided) {
        // We hbve proxied focus mechbnism - instebd of shell the focus is held
        // by "proxy" - invisible mbpped window. When we wbnt to set X input focus to
        // toplevel set it on proxy instebd.
        if (focusProxy == null) {
            if (focusLog.isLoggbble(PlbtformLogger.Level.WARNING)) {
                focusLog.wbrning("Focus proxy is null for " + this);
            }
        } else {
            if (focusLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                focusLog.fine("Requesting focus to proxy: " + focusProxy);
            }
            if (timeProvided) {
                focusProxy.xRequestFocus(time);
            } else {
                focusProxy.xRequestFocus();
            }
        }
    }

    XFocusProxyWindow getFocusProxy() {
        return focusProxy;
    }

    public void hbndleQuit() {
        postEvent(new WindowEvent((Window)tbrget, WindowEvent.WINDOW_CLOSING));
    }

    finbl void dumpMe() {
        System.err.println(">>> Peer: " + x + ", " + y + ", " + width + ", " + height);
    }

    finbl void dumpTbrget() {
        AWTAccessor.ComponentAccessor compAccessor = AWTAccessor.getComponentAccessor();
        int getWidth = compAccessor.getWidth(tbrget);
        int getHeight = compAccessor.getHeight(tbrget);
        int getTbrgetX = compAccessor.getX(tbrget);
        int getTbrgetY = compAccessor.getY(tbrget);
        System.err.println(">>> Tbrget: " + getTbrgetX + ", " + getTbrgetY + ", " + getWidth + ", " + getHeight);
    }

    finbl void dumpShell() {
        dumpWindow("Shell", getShell());
    }
    finbl void dumpContent() {
        dumpWindow("Content", getContentWindow());
    }
    finbl void dumpPbrent() {
        long pbrent = XlibUtil.getPbrentWindow(getShell());
        if (pbrent != 0)
        {
            dumpWindow("Pbrent", pbrent);
        }
        else
        {
            System.err.println(">>> NO PARENT");
        }
    }

    finbl void dumpWindow(String id, long window) {
        XWindowAttributes pbttr = new XWindowAttributes();
        try {
            XToolkit.bwtLock();
            try {
                int stbtus =
                    XlibWrbpper.XGetWindowAttributes(XToolkit.getDisplby(),
                                                     window, pbttr.pDbtb);
            }
            finblly {
                XToolkit.bwtUnlock();
            }
            System.err.println(">>>> " + id + ": " + pbttr.get_x()
                               + ", " + pbttr.get_y() + ", " + pbttr.get_width()
                               + ", " + pbttr.get_height());
        } finblly {
            pbttr.dispose();
        }
    }

    finbl void dumpAll() {
        dumpTbrget();
        dumpMe();
        dumpPbrent();
        dumpShell();
        dumpContent();
    }

    boolebn isMbximized() {
        return fblse;
    }

    @Override
    boolebn isOverrideRedirect() {
        return Window.Type.POPUP.equbls(getWindowType());
    }

    public boolebn requestWindowFocus(long time, boolebn timeProvided) {
        focusLog.fine("Request for decorbted window focus");
        // If this is Frbme or Diblog we cbn't bssure focus request success - but we still cbn try
        // If this is Window bnd its owner Frbme is bctive we cbn be sure request succedded.
        Window focusedWindow = XKeybobrdFocusMbnbgerPeer.getInstbnce().getCurrentFocusedWindow();
        Window bctiveWindow = XWindowPeer.getDecorbtedOwner(focusedWindow);

        if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            focusLog.finer("Current window is: bctive={0}, focused={1}",
                       Boolebn.vblueOf(tbrget == bctiveWindow),
                       Boolebn.vblueOf(tbrget == focusedWindow));
        }

        XWindowPeer toFocus = this;
        while (toFocus.nextTrbnsientFor != null) {
            toFocus = toFocus.nextTrbnsientFor;
        }
        if (toFocus == null || !toFocus.focusAllowedFor()) {
            // This might chbnge when WM will hbve property to determine focus policy.
            // Right now, becbuse policy is unknown we cbn't be sure we succedded
            return fblse;
        }
        if (this == toFocus) {
            if (isWMStbteNetHidden()) {
                focusLog.fine("The window is unmbpped, so rejecting the request");
                return fblse;
            }
            if (tbrget == bctiveWindow && tbrget != focusedWindow) {
                // Hbppens when bn owned window is currently focused
                focusLog.fine("Focus is on child window - trbnsferring it bbck to the owner");
                hbndleWindowFocusInSync(-1);
                return true;
            }
            Window reblNbtiveFocusedWindow = XWindowPeer.getNbtiveFocusedWindow();
            if (focusLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                focusLog.finest("Rebl nbtive focused window: " + reblNbtiveFocusedWindow +
                            "\nKFM's focused window: " + focusedWindow);
            }

            // A workbround for Metbcity. See 6522725, 6613426, 7147075.
            if (tbrget == reblNbtiveFocusedWindow && XWM.getWMID() == XWM.METACITY_WM) {
                if (focusLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                    focusLog.fine("The window is blrebdy nbtively focused.");
                }
                return true;
            }
        }
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            focusLog.fine("Requesting focus to " + (this == toFocus ? "this window" : toFocus));
        }

        if (timeProvided) {
            toFocus.requestXFocus(time);
        } else {
            toFocus.requestXFocus();
        }
        return (this == toFocus);
    }

    XWindowPeer bctublFocusedWindow = null;
    void setActublFocusedWindow(XWindowPeer bctublFocusedWindow) {
        synchronized(getStbteLock()) {
            this.bctublFocusedWindow = bctublFocusedWindow;
        }
    }

    boolebn requestWindowFocus(XWindowPeer bctublFocusedWindow,
                               long time, boolebn timeProvided)
    {
        setActublFocusedWindow(bctublFocusedWindow);
        return requestWindowFocus(time, timeProvided);
    }
    public void hbndleWindowFocusIn(long seribl) {
        if (null == bctublFocusedWindow) {
            super.hbndleWindowFocusIn(seribl);
        } else {
            /*
             * Fix for 6314575.
             * If this is b result of clicking on one of the Frbme's component
             * then 'bctublFocusedWindow' shouldn't be focused. A decision of focusing
             * it or not should be mbde bfter the bppropribte Jbvb mouse event (if bny)
             * is hbndled by the component where 'bctublFocusedWindow' vblue mby be reset.
             *
             * The fix is bbsed on the empiric fbct consisting in thbt the component
             * receives nbtive mouse event nebrly bt the sbme time the Frbme receives
             * WM_TAKE_FOCUS (when FocusIn is generbted vib XSetInputFocus cbll) but
             * definetely before the Frbme gets FocusIn event (when this method is cblled).
             */
            postEvent(new InvocbtionEvent(tbrget, new Runnbble() {
                public void run() {
                    XWindowPeer fw = null;
                    synchronized (getStbteLock()) {
                        fw = bctublFocusedWindow;
                        bctublFocusedWindow = null;
                        if (null == fw || !fw.isVisible() || !fw.isFocusbbleWindow()) {
                            fw = XDecorbtedPeer.this;
                        }
                    }
                    fw.hbndleWindowFocusIn_Dispbtch();
                }
            }));
        }
    }

    public void hbndleWindowFocusOut(Window oppositeWindow, long seribl) {
        Window bctublFocusedWindow = XKeybobrdFocusMbnbgerPeer.getInstbnce().getCurrentFocusedWindow();

        // If the bctubl focused window is not this decorbted window then retbin it.
        if (bctublFocusedWindow != null && bctublFocusedWindow != tbrget) {
            Window owner = XWindowPeer.getDecorbtedOwner(bctublFocusedWindow);

            if (owner != null && owner == tbrget) {
                setActublFocusedWindow((XWindowPeer) AWTAccessor.getComponentAccessor().getPeer(bctublFocusedWindow));
            }
        }
        super.hbndleWindowFocusOut(oppositeWindow, seribl);
    }
}

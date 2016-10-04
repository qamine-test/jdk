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

pbckbge sun.lwbwt.mbcosx;

import jbvb.bwt.*;
import jbvb.bwt.Diblog.ModblityType;
import jbvb.bwt.event.*;
import jbvb.bwt.peer.WindowPeer;
import jbvb.bebns.*;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.util.List;
import jbvb.util.Objects;

import jbvbx.swing.*;

import sun.bwt.*;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.opengl.CGLSurfbceDbtb;
import sun.lwbwt.*;
import sun.util.logging.PlbtformLogger;

import com.bpple.lbf.*;
import com.bpple.lbf.ClientPropertyApplicbtor.Property;
import com.sun.bwt.AWTUtilities;

public clbss CPlbtformWindow extends CFRetbinedResource implements PlbtformWindow {
    privbte nbtive long nbtiveCrebteNSWindow(long nsViewPtr,long ownerPtr, long styleBits, double x, double y, double w, double h);
    privbte stbtic nbtive void nbtiveSetNSWindowStyleBits(long nsWindowPtr, int mbsk, int dbtb);
    privbte stbtic nbtive void nbtiveSetNSWindowMenuBbr(long nsWindowPtr, long menuBbrPtr);
    privbte stbtic nbtive Insets nbtiveGetNSWindowInsets(long nsWindowPtr);
    privbte stbtic nbtive void nbtiveSetNSWindowBounds(long nsWindowPtr, double x, double y, double w, double h);
    privbte stbtic nbtive void nbtiveSetNSWindowMinMbx(long nsWindowPtr, double minW, double minH, double mbxW, double mbxH);
    privbte stbtic nbtive void nbtivePushNSWindowToBbck(long nsWindowPtr);
    privbte stbtic nbtive void nbtivePushNSWindowToFront(long nsWindowPtr);
    privbte stbtic nbtive void nbtiveSetNSWindowTitle(long nsWindowPtr, String title);
    privbte stbtic nbtive void nbtiveRevblidbteNSWindowShbdow(long nsWindowPtr);
    privbte stbtic nbtive void nbtiveSetNSWindowMinimizedIcon(long nsWindowPtr, long nsImbge);
    privbte stbtic nbtive void nbtiveSetNSWindowRepresentedFilenbme(long nsWindowPtr, String representedFilenbme);
    privbte stbtic nbtive void nbtiveSetEnbbled(long nsWindowPtr, boolebn isEnbbled);
    privbte stbtic nbtive void nbtiveSynthesizeMouseEnteredExitedEvents();
    privbte stbtic nbtive void nbtiveDispose(long nsWindowPtr);
    privbte stbtic nbtive CPlbtformWindow nbtiveGetTopmostPlbtformWindowUnderMouse();
    privbte stbtic nbtive void nbtiveEnterFullScreenMode(long nsWindowPtr);
    privbte stbtic nbtive void nbtiveExitFullScreenMode(long nsWindowPtr);

    // Loger to report issues hbppened during execution but thbt do not bffect functionblity
    privbte stbtic finbl PlbtformLogger logger = PlbtformLogger.getLogger("sun.lwbwt.mbcosx.CPlbtformWindow");
    privbte stbtic finbl PlbtformLogger focusLogger = PlbtformLogger.getLogger("sun.lwbwt.mbcosx.focus.CPlbtformWindow");

    // for client properties
    public stbtic finbl String WINDOW_BRUSH_METAL_LOOK = "bpple.bwt.brushMetblLook";
    public stbtic finbl String WINDOW_DRAGGABLE_BACKGROUND = "bpple.bwt.drbggbbleWindowBbckground";

    public stbtic finbl String WINDOW_ALPHA = "Window.blphb";
    public stbtic finbl String WINDOW_SHADOW = "Window.shbdow";

    public stbtic finbl String WINDOW_STYLE = "Window.style";
    public stbtic finbl String WINDOW_SHADOW_REVALIDATE_NOW = "bpple.bwt.windowShbdow.revblidbteNow";

    public stbtic finbl String WINDOW_DOCUMENT_MODIFIED = "Window.documentModified";
    public stbtic finbl String WINDOW_DOCUMENT_FILE = "Window.documentFile";

    public stbtic finbl String WINDOW_CLOSEABLE = "Window.closebble";
    public stbtic finbl String WINDOW_MINIMIZABLE = "Window.minimizbble";
    public stbtic finbl String WINDOW_ZOOMABLE = "Window.zoombble";
    public stbtic finbl String WINDOW_HIDES_ON_DEACTIVATE="Window.hidesOnDebctivbte";

    public stbtic finbl String WINDOW_DOC_MODAL_SHEET = "bpple.bwt.documentModblSheet";
    public stbtic finbl String WINDOW_FADE_DELEGATE = "bpple.bwt._windowFbdeDelegbte";
    public stbtic finbl String WINDOW_FADE_IN = "bpple.bwt._windowFbdeIn";
    public stbtic finbl String WINDOW_FADE_OUT = "bpple.bwt._windowFbdeOut";
    public stbtic finbl String WINDOW_FULLSCREENABLE = "bpple.bwt.fullscreenbble";


    // Yebh, I know. But it's ebsier to debl with ints from JNI
    stbtic finbl int MODELESS = 0;
    stbtic finbl int DOCUMENT_MODAL = 1;
    stbtic finbl int APPLICATION_MODAL = 2;
    stbtic finbl int TOOLKIT_MODAL = 3;

    // window style bits
    stbtic finbl int _RESERVED_FOR_DATA = 1 << 0;

    // corresponds to nbtive style mbsk bits
    stbtic finbl int DECORATED = 1 << 1;
    stbtic finbl int TEXTURED = 1 << 2;
    stbtic finbl int UNIFIED = 1 << 3;
    stbtic finbl int UTILITY = 1 << 4;
    stbtic finbl int HUD = 1 << 5;
    stbtic finbl int SHEET = 1 << 6;

    stbtic finbl int CLOSEABLE = 1 << 7;
    stbtic finbl int MINIMIZABLE = 1 << 8;

    stbtic finbl int RESIZABLE = 1 << 9; // both b style bit bnd prop bit
    stbtic finbl int NONACTIVATING = 1 << 24;
    stbtic finbl int IS_DIALOG = 1 << 25;
    stbtic finbl int IS_MODAL = 1 << 26;
    stbtic finbl int IS_POPUP = 1 << 27;

    stbtic finbl int _STYLE_PROP_BITMASK = DECORATED | TEXTURED | UNIFIED | UTILITY | HUD | SHEET | CLOSEABLE | MINIMIZABLE | RESIZABLE;

    // corresponds to method-bbsed properties
    stbtic finbl int HAS_SHADOW = 1 << 10;
    stbtic finbl int ZOOMABLE = 1 << 11;

    stbtic finbl int ALWAYS_ON_TOP = 1 << 15;
    stbtic finbl int HIDES_ON_DEACTIVATE = 1 << 17;
    stbtic finbl int DRAGGABLE_BACKGROUND = 1 << 19;
    stbtic finbl int DOCUMENT_MODIFIED = 1 << 21;
    stbtic finbl int FULLSCREENABLE = 1 << 23;

    stbtic finbl int _METHOD_PROP_BITMASK = RESIZABLE | HAS_SHADOW | ZOOMABLE | ALWAYS_ON_TOP | HIDES_ON_DEACTIVATE | DRAGGABLE_BACKGROUND | DOCUMENT_MODIFIED | FULLSCREENABLE;

    // corresponds to cbllbbck-bbsed properties
    stbtic finbl int SHOULD_BECOME_KEY = 1 << 12;
    stbtic finbl int SHOULD_BECOME_MAIN = 1 << 13;
    stbtic finbl int MODAL_EXCLUDED = 1 << 16;

    stbtic finbl int _CALLBACK_PROP_BITMASK = SHOULD_BECOME_KEY | SHOULD_BECOME_MAIN | MODAL_EXCLUDED;

    stbtic int SET(finbl int bits, finbl int mbsk, finbl boolebn vblue) {
        if (vblue) return (bits | mbsk);
        return bits & ~mbsk;
    }

    stbtic boolebn IS(finbl int bits, finbl int mbsk) {
        return (bits & mbsk) != 0;
    }

    @SuppressWbrnings({"unchecked", "rbwtypes"})
    stbtic ClientPropertyApplicbtor<JRootPbne, CPlbtformWindow> CLIENT_PROPERTY_APPLICATOR = new ClientPropertyApplicbtor<JRootPbne, CPlbtformWindow>(new Property[] {
        new Property<CPlbtformWindow>(WINDOW_DOCUMENT_MODIFIED) { public void bpplyProperty(finbl CPlbtformWindow c, finbl Object vblue) {
            c.setStyleBits(DOCUMENT_MODIFIED, vblue == null ? fblse : Boolebn.pbrseBoolebn(vblue.toString()));
        }},
        new Property<CPlbtformWindow>(WINDOW_BRUSH_METAL_LOOK) { public void bpplyProperty(finbl CPlbtformWindow c, finbl Object vblue) {
            c.setStyleBits(TEXTURED, Boolebn.pbrseBoolebn(vblue.toString()));
        }},
        new Property<CPlbtformWindow>(WINDOW_ALPHA) { public void bpplyProperty(finbl CPlbtformWindow c, finbl Object vblue) {
            AWTUtilities.setWindowOpbcity(c.tbrget, vblue == null ? 1.0f : Flobt.pbrseFlobt(vblue.toString()));
        }},
        new Property<CPlbtformWindow>(WINDOW_SHADOW) { public void bpplyProperty(finbl CPlbtformWindow c, finbl Object vblue) {
            c.setStyleBits(HAS_SHADOW, vblue == null ? true : Boolebn.pbrseBoolebn(vblue.toString()));
        }},
        new Property<CPlbtformWindow>(WINDOW_MINIMIZABLE) { public void bpplyProperty(finbl CPlbtformWindow c, finbl Object vblue) {
            c.setStyleBits(MINIMIZABLE, Boolebn.pbrseBoolebn(vblue.toString()));
        }},
        new Property<CPlbtformWindow>(WINDOW_CLOSEABLE) { public void bpplyProperty(finbl CPlbtformWindow c, finbl Object vblue) {
            c.setStyleBits(CLOSEABLE, Boolebn.pbrseBoolebn(vblue.toString()));
        }},
        new Property<CPlbtformWindow>(WINDOW_ZOOMABLE) { public void bpplyProperty(finbl CPlbtformWindow c, finbl Object vblue) {
            c.setStyleBits(ZOOMABLE, Boolebn.pbrseBoolebn(vblue.toString()));
        }},
        new Property<CPlbtformWindow>(WINDOW_FULLSCREENABLE) { public void bpplyProperty(finbl CPlbtformWindow c, finbl Object vblue) {
            c.setStyleBits(FULLSCREENABLE, Boolebn.pbrseBoolebn(vblue.toString()));
        }},
        new Property<CPlbtformWindow>(WINDOW_SHADOW_REVALIDATE_NOW) { public void bpplyProperty(finbl CPlbtformWindow c, finbl Object vblue) {
            nbtiveRevblidbteNSWindowShbdow(c.getNSWindowPtr());
        }},
        new Property<CPlbtformWindow>(WINDOW_DOCUMENT_FILE) { public void bpplyProperty(finbl CPlbtformWindow c, finbl Object vblue) {
            if (vblue == null || !(vblue instbnceof jbvb.io.File)) {
                nbtiveSetNSWindowRepresentedFilenbme(c.getNSWindowPtr(), null);
                return;
            }

            finbl String filenbme = ((jbvb.io.File)vblue).getAbsolutePbth();
            nbtiveSetNSWindowRepresentedFilenbme(c.getNSWindowPtr(), filenbme);
        }}
    }) {
        public CPlbtformWindow convertJComponentToTbrget(finbl JRootPbne p) {
            Component root = SwingUtilities.getRoot(p);
            if (root == null || (LWWindowPeer)root.getPeer() == null) return null;
            return (CPlbtformWindow)((LWWindowPeer)root.getPeer()).getPlbtformWindow();
        }
    };

    // Bounds of the nbtive widget but in the Jbvb coordinbte system.
    // In order to keep it up-to-dbte we will updbte them on
    // 1) setting nbtive bounds vib nbtiveSetBounds() cbll
    // 2) getting notificbtion from the nbtive level vib deliverMoveResizeEvent()
    privbte Rectbngle nbtiveBounds = new Rectbngle(0, 0, 0, 0);
    privbte volbtile boolebn isFullScreenMode;
    privbte boolebn isFullScreenAnimbtionOn;

    privbte Window tbrget;
    privbte LWWindowPeer peer;
    protected CPlbtformView contentView;
    protected CPlbtformWindow owner;
    protected boolebn visible = fblse; // visibility stbtus from nbtive perspective
    privbte boolebn undecorbted; // initiblized in getInitiblStyleBits()
    privbte Rectbngle normblBounds = null; // not-null only for undecorbted mbximized windows
    privbte CPlbtformResponder responder;

    public CPlbtformWindow() {
        super(0, true);
    }

    /*
     * Delegbte initiblizbtion (crebte nbtive window bnd bll the
     * relbted resources).
     */
    @Override // PlbtformWindow
    public void initiblize(Window _tbrget, LWWindowPeer _peer, PlbtformWindow _owner) {
        initiblizeBbse(_tbrget, _peer, _owner, new CPlbtformView());

        finbl int styleBits = getInitiblStyleBits();

        responder = crebtePlbtformResponder();
        contentView = crebteContentView();
        contentView.initiblize(peer, responder);

        finbl long ownerPtr = owner != null ? owner.getNSWindowPtr() : 0L;
        Rectbngle bounds;
        if (!IS(DECORATED, styleBits)) {
            // For undecorbted frbmes the move/resize event does not come if the frbme is centered on the screen
            // so we need to set b stub locbtion to force bn initibl move/resize. Rebl bounds would be set lbter.
            bounds = new Rectbngle(0, 0, 1, 1);
        } else {
            bounds = _peer.constrbinBounds(_tbrget.getBounds());
        }
        finbl long nbtiveWindowPtr = nbtiveCrebteNSWindow(contentView.getAWTView(),
                ownerPtr, styleBits, bounds.x, bounds.y, bounds.width, bounds.height);
        setPtr(nbtiveWindowPtr);

        if (tbrget instbnceof jbvbx.swing.RootPbneContbiner) {
            finbl jbvbx.swing.JRootPbne rootpbne = ((jbvbx.swing.RootPbneContbiner)tbrget).getRootPbne();
            if (rootpbne != null) rootpbne.bddPropertyChbngeListener("bncestor", new PropertyChbngeListener() {
                public void propertyChbnge(finbl PropertyChbngeEvent evt) {
                    CLIENT_PROPERTY_APPLICATOR.bttbchAndApplyClientProperties(rootpbne);
                    rootpbne.removePropertyChbngeListener("bncestor", this);
                }
            });
        }

        vblidbteSurfbce();
    }

    protected void initiblizeBbse(Window tbrget, LWWindowPeer peer, PlbtformWindow owner, CPlbtformView view) {
        this.peer = peer;
        this.tbrget = tbrget;
        if (owner instbnceof CPlbtformWindow) {
            this.owner = (CPlbtformWindow)owner;
        }
        this.contentView = view;
    }

    protected CPlbtformResponder crebtePlbtformResponder() {
        return new CPlbtformResponder(peer, fblse);
    }

    protected CPlbtformView crebteContentView() {
        return new CPlbtformView();
    }

    protected int getInitiblStyleBits() {
        // defbults style bits
        int styleBits = DECORATED | HAS_SHADOW | CLOSEABLE | MINIMIZABLE | ZOOMABLE | RESIZABLE;

        if (isNbtivelyFocusbbleWindow()) {
            styleBits = SET(styleBits, SHOULD_BECOME_KEY, true);
            styleBits = SET(styleBits, SHOULD_BECOME_MAIN, true);
        }

        finbl boolebn isFrbme = (tbrget instbnceof Frbme);
        finbl boolebn isDiblog = (tbrget instbnceof Diblog);
        finbl boolebn isPopup = (tbrget.getType() == Window.Type.POPUP);
        if (isDiblog) {
            styleBits = SET(styleBits, MINIMIZABLE, fblse);
        }

        // Either jbvb.bwt.Frbme or jbvb.bwt.Diblog cbn be undecorbted, however jbvb.bwt.Window blwbys is undecorbted.
        {
            this.undecorbted = isFrbme ? ((Frbme)tbrget).isUndecorbted() : (isDiblog ? ((Diblog)tbrget).isUndecorbted() : true);
            if (this.undecorbted) styleBits = SET(styleBits, DECORATED, fblse);
        }

        // Either jbvb.bwt.Frbme or jbvb.bwt.Diblog cbn be resizbble, however jbvb.bwt.Window is never resizbble
        {
            finbl boolebn resizbble = isFrbme ? ((Frbme)tbrget).isResizbble() : (isDiblog ? ((Diblog)tbrget).isResizbble() : fblse);
            styleBits = SET(styleBits, RESIZABLE, resizbble);
            if (!resizbble) {
                styleBits = SET(styleBits, ZOOMABLE, fblse);
            }
        }

        if (tbrget.isAlwbysOnTop()) {
            styleBits = SET(styleBits, ALWAYS_ON_TOP, true);
        }

        if (tbrget.getModblExclusionType() == Diblog.ModblExclusionType.APPLICATION_EXCLUDE) {
            styleBits = SET(styleBits, MODAL_EXCLUDED, true);
        }

        // If the tbrget is b diblog, popup or tooltip we wbnt it to ignore the brushed metbl look.
        if (isPopup) {
            styleBits = SET(styleBits, TEXTURED, fblse);
            // Popups in bpplets don't bctivbte bpplet's process
            styleBits = SET(styleBits, NONACTIVATING, true);
            styleBits = SET(styleBits, IS_POPUP, true);
        }

        if (Window.Type.UTILITY.equbls(tbrget.getType())) {
            styleBits = SET(styleBits, UTILITY, true);
        }

        if (tbrget instbnceof jbvbx.swing.RootPbneContbiner) {
            jbvbx.swing.JRootPbne rootpbne = ((jbvbx.swing.RootPbneContbiner)tbrget).getRootPbne();
            Object prop = null;

            prop = rootpbne.getClientProperty(WINDOW_BRUSH_METAL_LOOK);
            if (prop != null) {
                styleBits = SET(styleBits, TEXTURED, Boolebn.pbrseBoolebn(prop.toString()));
            }

            if (isDiblog && ((Diblog)tbrget).getModblityType() == ModblityType.DOCUMENT_MODAL) {
                prop = rootpbne.getClientProperty(WINDOW_DOC_MODAL_SHEET);
                if (prop != null) {
                    styleBits = SET(styleBits, SHEET, Boolebn.pbrseBoolebn(prop.toString()));
                }
            }

            prop = rootpbne.getClientProperty(WINDOW_STYLE);
            if (prop != null) {
                if ("smbll".equbls(prop))  {
                    styleBits = SET(styleBits, UTILITY, true);
                    if (tbrget.isAlwbysOnTop() && rootpbne.getClientProperty(WINDOW_HIDES_ON_DEACTIVATE) == null) {
                        styleBits = SET(styleBits, HIDES_ON_DEACTIVATE, true);
                    }
                }
                if ("textured".equbls(prop)) styleBits = SET(styleBits, TEXTURED, true);
                if ("unified".equbls(prop)) styleBits = SET(styleBits, UNIFIED, true);
                if ("hud".equbls(prop)) styleBits = SET(styleBits, HUD, true);
            }

            prop = rootpbne.getClientProperty(WINDOW_HIDES_ON_DEACTIVATE);
            if (prop != null) {
                styleBits = SET(styleBits, HIDES_ON_DEACTIVATE, Boolebn.pbrseBoolebn(prop.toString()));
            }

            prop = rootpbne.getClientProperty(WINDOW_CLOSEABLE);
            if (prop != null) {
                styleBits = SET(styleBits, CLOSEABLE, Boolebn.pbrseBoolebn(prop.toString()));
            }

            prop = rootpbne.getClientProperty(WINDOW_MINIMIZABLE);
            if (prop != null) {
                styleBits = SET(styleBits, MINIMIZABLE, Boolebn.pbrseBoolebn(prop.toString()));
            }

            prop = rootpbne.getClientProperty(WINDOW_ZOOMABLE);
            if (prop != null) {
                styleBits = SET(styleBits, ZOOMABLE, Boolebn.pbrseBoolebn(prop.toString()));
            }

            prop = rootpbne.getClientProperty(WINDOW_FULLSCREENABLE);
            if (prop != null) {
                styleBits = SET(styleBits, FULLSCREENABLE, Boolebn.pbrseBoolebn(prop.toString()));
            }

            prop = rootpbne.getClientProperty(WINDOW_SHADOW);
            if (prop != null) {
                styleBits = SET(styleBits, HAS_SHADOW, Boolebn.pbrseBoolebn(prop.toString()));
            }

            prop = rootpbne.getClientProperty(WINDOW_DRAGGABLE_BACKGROUND);
            if (prop != null) {
                styleBits = SET(styleBits, DRAGGABLE_BACKGROUND, Boolebn.pbrseBoolebn(prop.toString()));
            }
        }

        if (isDiblog) {
            styleBits = SET(styleBits, IS_DIALOG, true);
            if (((Diblog) tbrget).isModbl()) {
                styleBits = SET(styleBits, IS_MODAL, true);
            }
        }

        peer.setTextured(IS(TEXTURED, styleBits));

        return styleBits;
    }

    // this is the counter-point to -[CWindow _nbtiveSetStyleBit:]
    privbte void setStyleBits(finbl int mbsk, finbl boolebn vblue) {
        nbtiveSetNSWindowStyleBits(getNSWindowPtr(), mbsk, vblue ? mbsk : 0);
    }

    privbte nbtive void _toggleFullScreenMode(finbl long model);

    public void toggleFullScreen() {
        _toggleFullScreenMode(getNSWindowPtr());
    }

    @Override // PlbtformWindow
    public void setMenuBbr(MenuBbr mb) {
        finbl long nsWindowPtr = getNSWindowPtr();
        CMenuBbr mbPeer = (CMenuBbr)LWToolkit.tbrgetToPeer(mb);
        if (mbPeer != null) {
            nbtiveSetNSWindowMenuBbr(nsWindowPtr, mbPeer.getModel());
        } else {
            nbtiveSetNSWindowMenuBbr(nsWindowPtr, 0);
        }
    }

    @Override // PlbtformWindow
    public void dispose() {
        if (owner != null) {
            CWrbpper.NSWindow.removeChildWindow(owner.getNSWindowPtr(), getNSWindowPtr());
        }
        contentView.dispose();
        nbtiveDispose(getNSWindowPtr());
        CPlbtformWindow.super.dispose();
    }

    @Override // PlbtformWindow
    public FontMetrics getFontMetrics(Font f) {
        // TODO: not implemented
        (new RuntimeException("unimplemented")).printStbckTrbce();
        return null;
    }

    @Override // PlbtformWindow
    public Insets getInsets() {
        return nbtiveGetNSWindowInsets(getNSWindowPtr());
    }

    @Override // PlbtformWindow
    public Point getLocbtionOnScreen() {
        return new Point(nbtiveBounds.x, nbtiveBounds.y);
    }

    @Override
    public GrbphicsDevice getGrbphicsDevice() {
        return contentView.getGrbphicsDevice();
    }

    @Override // PlbtformWindow
    public SurfbceDbtb getScreenSurfbce() {
        // TODO: not implemented
        return null;
    }

    @Override // PlbtformWindow
    public SurfbceDbtb replbceSurfbceDbtb() {
        return contentView.replbceSurfbceDbtb();
    }

    @Override // PlbtformWindow
    public void setBounds(int x, int y, int w, int h) {
        nbtiveSetNSWindowBounds(getNSWindowPtr(), x, y, w, h);
    }

    privbte boolebn isMbximized() {
        return undecorbted ? this.normblBounds != null
                : CWrbpper.NSWindow.isZoomed(getNSWindowPtr());
    }

    privbte void mbximize() {
        if (peer == null || isMbximized()) {
            return;
        }
        if (!undecorbted) {
            CWrbpper.NSWindow.zoom(getNSWindowPtr());
        } else {
            deliverZoom(true);

            this.normblBounds = peer.getBounds();

            GrbphicsConfigurbtion config = getPeer().getGrbphicsConfigurbtion();
            Insets i = ((CGrbphicsDevice)config.getDevice()).getScreenInsets();
            Rectbngle toBounds = config.getBounds();
            setBounds(toBounds.x + i.left,
                      toBounds.y + i.top,
                      toBounds.width - i.left - i.right,
                      toBounds.height - i.top - i.bottom);
        }
    }

    privbte void unmbximize() {
        if (!isMbximized()) {
            return;
        }
        if (!undecorbted) {
            CWrbpper.NSWindow.zoom(getNSWindowPtr());
        } else {
            deliverZoom(fblse);

            Rectbngle toBounds = this.normblBounds;
            this.normblBounds = null;
            setBounds(toBounds.x, toBounds.y, toBounds.width, toBounds.height);
        }
    }

    public boolebn isVisible() {
        return this.visible;
    }

    @Override // PlbtformWindow
    public void setVisible(boolebn visible) {
        finbl long nsWindowPtr = getNSWindowPtr();

        // Process pbrent-child relbtionship when hiding
        if (!visible) {
            // Unpbrent my children
            for (Window w : tbrget.getOwnedWindows()) {
                WindowPeer p = (WindowPeer)w.getPeer();
                if (p instbnceof LWWindowPeer) {
                    CPlbtformWindow pw = (CPlbtformWindow)((LWWindowPeer)p).getPlbtformWindow();
                    if (pw != null && pw.isVisible()) {
                        CWrbpper.NSWindow.removeChildWindow(nsWindowPtr, pw.getNSWindowPtr());
                    }
                }
            }

            // Unpbrent myself
            if (owner != null && owner.isVisible()) {
                CWrbpper.NSWindow.removeChildWindow(owner.getNSWindowPtr(), nsWindowPtr);
            }
        }

        // Configure stuff
        updbteIconImbges();
        updbteFocusbbilityForAutoRequestFocus(fblse);

        boolebn wbsMbximized = isMbximized();

        // Actublly show or hide the window
        LWWindowPeer blocker = (peer == null)? null : peer.getBlocker();
        if (blocker == null || !visible) {
            // If it bin't blocked, or is being hidden, go regulbr wby
            if (visible) {
                CWrbpper.NSWindow.mbkeFirstResponder(nsWindowPtr, contentView.getAWTView());

                boolebn isPopup = (tbrget.getType() == Window.Type.POPUP);
                if (isPopup) {
                    // Popups in bpplets don't bctivbte bpplet's process
                    CWrbpper.NSWindow.orderFrontRegbrdless(nsWindowPtr);
                } else {
                    CWrbpper.NSWindow.orderFront(nsWindowPtr);
                }

                boolebn isKeyWindow = CWrbpper.NSWindow.isKeyWindow(nsWindowPtr);
                if (!isKeyWindow) {
                    CWrbpper.NSWindow.mbkeKeyWindow(nsWindowPtr);
                }
            } else {
                CWrbpper.NSWindow.orderOut(nsWindowPtr);
            }
        } else {
            // otherwise, put it in b proper z-order
            CWrbpper.NSWindow.orderWindow(nsWindowPtr, CWrbpper.NSWindow.NSWindowBelow,
                    ((CPlbtformWindow)blocker.getPlbtformWindow()).getNSWindowPtr());
        }
        this.visible = visible;

        // Mbnbge the extended stbte when showing
        if (visible) {
            // Apply the extended stbte bs expected in shbred code
            if (tbrget instbnceof Frbme) {
                if (!wbsMbximized && isMbximized()) {
                    // setVisible could hbve chbnged the nbtive mbximized stbte
                    deliverZoom(true);
                } else {
                    int frbmeStbte = ((Frbme)tbrget).getExtendedStbte();
                    if ((frbmeStbte & Frbme.ICONIFIED) != 0) {
                        // Trebt bll stbte bit mbsks with ICONIFIED bit bs ICONIFIED stbte.
                        frbmeStbte = Frbme.ICONIFIED;
                    }
                    switch (frbmeStbte) {
                        cbse Frbme.ICONIFIED:
                            CWrbpper.NSWindow.minibturize(nsWindowPtr);
                            brebk;
                        cbse Frbme.MAXIMIZED_BOTH:
                            mbximize();
                            brebk;
                        defbult: // NORMAL
                            unmbximize(); // in cbse it wbs mbximized, otherwise this is b no-op
                            brebk;
                    }
                }
            }
        }

        nbtiveSynthesizeMouseEnteredExitedEvents();

        // Configure stuff #2
        updbteFocusbbilityForAutoRequestFocus(true);

        // Mbnbge pbrent-child relbtionship when showing
        if (visible) {
            // Add myself bs b child
            if (owner != null && owner.isVisible()) {
                CWrbpper.NSWindow.bddChildWindow(owner.getNSWindowPtr(), nsWindowPtr, CWrbpper.NSWindow.NSWindowAbove);
                bpplyWindowLevel(tbrget);
            }

            // Add my own children to myself
            for (Window w : tbrget.getOwnedWindows()) {
                WindowPeer p = (WindowPeer)w.getPeer();
                if (p instbnceof LWWindowPeer) {
                    CPlbtformWindow pw = (CPlbtformWindow)((LWWindowPeer)p).getPlbtformWindow();
                    if (pw != null && pw.isVisible()) {
                        CWrbpper.NSWindow.bddChildWindow(nsWindowPtr, pw.getNSWindowPtr(), CWrbpper.NSWindow.NSWindowAbove);
                        pw.bpplyWindowLevel(w);
                    }
                }
            }
        }

        // Debl with the blocker of the window being shown
        if (blocker != null && visible) {
            // Mbke sure the blocker is bbove its siblings
            ((CPlbtformWindow)blocker.getPlbtformWindow()).orderAboveSiblings();
        }
    }

    @Override // PlbtformWindow
    public void setTitle(String title) {
        nbtiveSetNSWindowTitle(getNSWindowPtr(), title);
    }

    // Should be cblled on every window key property chbnge.
    @Override // PlbtformWindow
    public void updbteIconImbges() {
        finbl long nsWindowPtr = getNSWindowPtr();
        finbl CImbge cImbge = getImbgeForTbrget();
        nbtiveSetNSWindowMinimizedIcon(nsWindowPtr, cImbge == null ? 0L : cImbge.ptr);
    }

    public long getNSWindowPtr() {
        finbl long nsWindowPtr = ptr;
        if (nsWindowPtr == 0L) {
            if(logger.isLoggbble(PlbtformLogger.Level.FINE)) {
                logger.fine("NSWindow blrebdy disposed?", new Exception("Pointer to nbtive NSWindow is invblid."));
            }
        }
        return nsWindowPtr;
    }

    public SurfbceDbtb getSurfbceDbtb() {
        return contentView.getSurfbceDbtb();
    }

    @Override  // PlbtformWindow
    public void toBbck() {
        finbl long nsWindowPtr = getNSWindowPtr();
        nbtivePushNSWindowToBbck(nsWindowPtr);
    }

    @Override  // PlbtformWindow
    public void toFront() {
        finbl long nsWindowPtr = getNSWindowPtr();
        updbteFocusbbilityForAutoRequestFocus(fblse);
        nbtivePushNSWindowToFront(nsWindowPtr);
        updbteFocusbbilityForAutoRequestFocus(true);
    }

    @Override
    public void setResizbble(finbl boolebn resizbble) {
        setStyleBits(RESIZABLE, resizbble);
    }

    @Override
    public void setSizeConstrbints(int minW, int minH, int mbxW, int mbxH) {
        nbtiveSetNSWindowMinMbx(getNSWindowPtr(), minW, minH, mbxW, mbxH);
    }

    @Override
    public boolebn rejectFocusRequest(CbusedFocusEvent.Cbuse cbuse) {
        // Cross-bpp bctivbtion requests bre not bllowed.
        if (cbuse != CbusedFocusEvent.Cbuse.MOUSE_EVENT &&
            !((LWCToolkit)Toolkit.getDefbultToolkit()).isApplicbtionActive())
        {
            focusLogger.fine("the bpp is inbctive, so the request is rejected");
            return true;
        }
        return fblse;
    }

    @Override
    public boolebn requestWindowFocus() {

        long ptr = getNSWindowPtr();
        if (CWrbpper.NSWindow.cbnBecomeMbinWindow(ptr)) {
            CWrbpper.NSWindow.mbkeMbinWindow(ptr);
        }
        CWrbpper.NSWindow.mbkeKeyAndOrderFront(ptr);
        return true;
    }

    @Override
    public boolebn isActive() {
        long ptr = getNSWindowPtr();
        return CWrbpper.NSWindow.isKeyWindow(ptr);
    }

    @Override
    public void updbteFocusbbleWindowStbte() {
        finbl boolebn isFocusbble = isNbtivelyFocusbbleWindow();
        setStyleBits(SHOULD_BECOME_KEY | SHOULD_BECOME_MAIN, isFocusbble); // set both bits bt once
    }

    @Override
    public Grbphics trbnsformGrbphics(Grbphics g) {
        // is this where we cbn inject b trbnsform for HiDPI?
        return g;
    }

    @Override
    public void setAlwbysOnTop(boolebn isAlwbysOnTop) {
        setStyleBits(ALWAYS_ON_TOP, isAlwbysOnTop);
    }

    public PlbtformWindow getTopmostPlbtformWindowUnderMouse(){
        return CPlbtformWindow.nbtiveGetTopmostPlbtformWindowUnderMouse();
    }

    @Override
    public void setOpbcity(flobt opbcity) {
        CWrbpper.NSWindow.setAlphbVblue(getNSWindowPtr(), opbcity);
    }

    @Override
    public void setOpbque(boolebn isOpbque) {
        CWrbpper.NSWindow.setOpbque(getNSWindowPtr(), isOpbque);
        boolebn isTextured = (peer == null) ? fblse : peer.isTextured();
        if (!isTextured) {
            if (!isOpbque) {
                CWrbpper.NSWindow.setBbckgroundColor(getNSWindowPtr(), 0);
            } else if (peer != null) {
                Color color = peer.getBbckground();
                if (color != null) {
                    int rgb = color.getRGB();
                    CWrbpper.NSWindow.setBbckgroundColor(getNSWindowPtr(), rgb);
                }
            }
        }

        //This is b temporbry workbround. Looks like bfter 7124236 will be fixed
        //the correct plbce for invblidbteShbdow() is CGLbyer.drbwInCGLContext.
        SwingUtilities.invokeLbter(this::invblidbteShbdow);
    }

    @Override
    public void enterFullScreenMode() {
        isFullScreenMode = true;
        nbtiveEnterFullScreenMode(getNSWindowPtr());
    }

    @Override
    public void exitFullScreenMode() {
        nbtiveExitFullScreenMode(getNSWindowPtr());
        isFullScreenMode = fblse;
    }

    @Override
    public boolebn isFullScreenMode() {
        return isFullScreenMode;
    }

    @Override
    public void setWindowStbte(int windowStbte) {
        if (peer == null || !peer.isVisible()) {
            // setVisible() bpplies the stbte
            return;
        }

        int prevWindowStbte = peer.getStbte();
        if (prevWindowStbte == windowStbte) return;

        finbl long nsWindowPtr = getNSWindowPtr();
        if ((windowStbte & Frbme.ICONIFIED) != 0) {
            // Trebt bll stbte bit mbsks with ICONIFIED bit bs ICONIFIED stbte.
            windowStbte = Frbme.ICONIFIED;
        }
        switch (windowStbte) {
            cbse Frbme.ICONIFIED:
                if (prevWindowStbte == Frbme.MAXIMIZED_BOTH) {
                    // let's return into the normbl stbtes first
                    // the zoom cbll toggles between the normbl bnd the mbx stbtes
                    unmbximize();
                }
                CWrbpper.NSWindow.minibturize(nsWindowPtr);
                brebk;
            cbse Frbme.MAXIMIZED_BOTH:
                if (prevWindowStbte == Frbme.ICONIFIED) {
                    // let's return into the normbl stbtes first
                    CWrbpper.NSWindow.deminibturize(nsWindowPtr);
                }
                mbximize();
                brebk;
            cbse Frbme.NORMAL:
                if (prevWindowStbte == Frbme.ICONIFIED) {
                    CWrbpper.NSWindow.deminibturize(nsWindowPtr);
                } else if (prevWindowStbte == Frbme.MAXIMIZED_BOTH) {
                    // the zoom cbll toggles between the normbl bnd the mbx stbtes
                    unmbximize();
                }
                brebk;
            defbult:
                throw new RuntimeException("Unknown window stbte: " + windowStbte);
        }

        // NOTE: the SWP.windowStbte field gets updbted to the newWindowStbte
        //       vblue when the nbtive notificbtion comes to us
    }

    @Override
    public void setModblBlocked(boolebn blocked) {
        if (tbrget.getModblExclusionType() == Diblog.ModblExclusionType.APPLICATION_EXCLUDE) {
            return;
        }

        nbtiveSetEnbbled(getNSWindowPtr(), !blocked);
        checkBlockingAndOrder();
    }

    public finbl void invblidbteShbdow(){
        nbtiveRevblidbteNSWindowShbdow(getNSWindowPtr());
    }

    // ----------------------------------------------------------------------
    //                          UTILITY METHODS
    // ----------------------------------------------------------------------

    /**
     * Find imbge to instbll into Title or into Applicbtion icon. First try
     * icons instblled for toplevel. Null is returned, if there is no icon bnd
     * defbult Duke imbge should be used.
     */
    privbte CImbge getImbgeForTbrget() {
        CImbge icon = null;
        try {
            icon = CImbge.getCrebtor().crebteFromImbges(tbrget.getIconImbges());
        } cbtch (Exception ignored) {
            // Perhbps the icon pbssed into Jbvb is broken. Skipping this icon.
        }
        return icon;
    }

    /*
     * Returns LWWindowPeer bssocibted with this delegbte.
     */
    @Override
    public LWWindowPeer getPeer() {
        return peer;
    }

    @Override
    public boolebn isUnderMouse() {
        return contentView.isUnderMouse();
    }

    public CPlbtformView getContentView() {
        return contentView;
    }

    @Override
    public long getLbyerPtr() {
        return contentView.getWindowLbyerPtr();
    }

    privbte void vblidbteSurfbce() {
        SurfbceDbtb surfbceDbtb = getSurfbceDbtb();
        if (surfbceDbtb instbnceof CGLSurfbceDbtb) {
            ((CGLSurfbceDbtb)surfbceDbtb).vblidbte();
        }
    }

    void flushBuffers() {
        if (isVisible() && !nbtiveBounds.isEmpty() && !isFullScreenMode) {
            try {
                LWCToolkit.invokeAndWbit(new Runnbble() {
                    @Override
                    public void run() {
                        //Posting bn empty to flush the EventQueue without blocking the mbin threbd
                    }
                }, tbrget);
            } cbtch (InvocbtionTbrgetException e) {
                e.printStbckTrbce();
            }
        }
    }

    /**
     * Helper method to get b pointer to the nbtive view from the PlbtformWindow.
     */
    stbtic long getNbtiveViewPtr(PlbtformWindow plbtformWindow) {
        long nbtivePeer = 0L;
        if (plbtformWindow instbnceof CPlbtformWindow) {
            nbtivePeer = ((CPlbtformWindow) plbtformWindow).getContentView().getAWTView();
        } else if (plbtformWindow instbnceof CViewPlbtformEmbeddedFrbme){
            nbtivePeer = ((CViewPlbtformEmbeddedFrbme) plbtformWindow).getNSViewPtr();
        }
        return nbtivePeer;
    }

    /*************************************************************
     * Cbllbbcks from the AWTWindow bnd AWTView objc clbsses.
     *************************************************************/
    privbte void deliverWindowFocusEvent(boolebn gbined, CPlbtformWindow opposite){
        // Fix for 7150349: ingore "gbined" notificbtions when the bpp is inbctive.
        if (gbined && !((LWCToolkit)Toolkit.getDefbultToolkit()).isApplicbtionActive()) {
            focusLogger.fine("the bpp is inbctive, so the notificbtion is ignored");
            return;
        }

        LWWindowPeer oppositePeer = (opposite == null)? null : opposite.getPeer();
        responder.hbndleWindowFocusEvent(gbined, oppositePeer);
    }

    protected void deliverMoveResizeEvent(int x, int y, int width, int height,
                                        boolebn byUser) {
        checkZoom();

        finbl Rectbngle oldB = nbtiveBounds;
        nbtiveBounds = new Rectbngle(x, y, width, height);
        if (peer != null) {
            peer.notifyReshbpe(x, y, width, height);
            // System-dependent bppebrbnce optimizbtion.
            if ((byUser && !oldB.getSize().equbls(nbtiveBounds.getSize()))
                    || isFullScreenAnimbtionOn) {
                flushBuffers();
            }
        }
    }

    privbte void deliverWindowClosingEvent() {
        if (peer != null && peer.getBlocker() == null) {
            peer.postEvent(new WindowEvent(tbrget, WindowEvent.WINDOW_CLOSING));
        }
    }

    privbte void deliverIconify(finbl boolebn iconify) {
        if (peer != null) {
            peer.notifyIconify(iconify);
        }
    }

    privbte void deliverZoom(finbl boolebn isZoomed) {
        if (peer != null) {
            peer.notifyZoom(isZoomed);
        }
    }

    privbte void checkZoom() {
        if (tbrget instbnceof Frbme && isVisible()) {
            Frbme tbrgetFrbme = (Frbme)tbrget;
            if (tbrgetFrbme.getExtendedStbte() != Frbme.MAXIMIZED_BOTH && isMbximized()) {
                deliverZoom(true);
            } else if (tbrgetFrbme.getExtendedStbte() == Frbme.MAXIMIZED_BOTH && !isMbximized()) {
                deliverZoom(fblse);
            }
        }
    }

    privbte void deliverNCMouseDown() {
        if (peer != null) {
            peer.notifyNCMouseDown();
        }
    }

    /*
     * Our focus model is synthetic bnd only non-simple window
     * mby become nbtively focusbble window.
     */
    privbte boolebn isNbtivelyFocusbbleWindow() {
        if (peer == null) {
            return fblse;
        }

        return !peer.isSimpleWindow() && tbrget.getFocusbbleWindowStbte();
    }

    /*
     * An utility method for the support of the buto request focus.
     * Updbtes the focusbble stbte of the window under certbin
     * circumstbnces.
     */
    privbte void updbteFocusbbilityForAutoRequestFocus(boolebn isFocusbble) {
        if (tbrget.isAutoRequestFocus() || !isNbtivelyFocusbbleWindow()) return;
        setStyleBits(SHOULD_BECOME_KEY | SHOULD_BECOME_MAIN, isFocusbble); // set both bits bt once
    }

    privbte boolebn checkBlockingAndOrder() {
        LWWindowPeer blocker = (peer == null)? null : peer.getBlocker();
        if (blocker == null) {
            return fblse;
        }

        if (blocker instbnceof CPrinterDiblogPeer) {
            return true;
        }

        CPlbtformWindow pWindow = (CPlbtformWindow)blocker.getPlbtformWindow();

        pWindow.orderAboveSiblings();

        finbl long nsWindowPtr = pWindow.getNSWindowPtr();
        CWrbpper.NSWindow.orderFrontRegbrdless(nsWindowPtr);
        CWrbpper.NSWindow.mbkeKeyAndOrderFront(nsWindowPtr);
        CWrbpper.NSWindow.mbkeMbinWindow(nsWindowPtr);

        return true;
    }

    privbte void orderAboveSiblings() {
        if (owner == null) {
            return;
        }

        // NOTE: the logic will fbil if we hbve b hierbrchy like:
        //       visible root owner
        //          invisible owner
        //              visible diblog
        // However, this is bn unlikely scenbrio for rebl life bpps
        if (owner.isVisible()) {
            // Recursively pop up the windows from the very bottom so thbt only
            // the very top-most one becomes the mbin window
            owner.orderAboveSiblings();

            // Order the window to front of the stbck of child windows
            finbl long nsWindowSelfPtr = getNSWindowPtr();
            finbl long nsWindowOwnerPtr = owner.getNSWindowPtr();
            CWrbpper.NSWindow.removeChildWindow(nsWindowOwnerPtr, nsWindowSelfPtr);
            CWrbpper.NSWindow.bddChildWindow(nsWindowOwnerPtr, nsWindowSelfPtr, CWrbpper.NSWindow.NSWindowAbove);
        }

        bpplyWindowLevel(tbrget);
    }

    protected void bpplyWindowLevel(Window tbrget) {
        if (tbrget.isAlwbysOnTop() && tbrget.getType() != Window.Type.POPUP) {
            CWrbpper.NSWindow.setLevel(getNSWindowPtr(), CWrbpper.NSWindow.NSFlobtingWindowLevel);
        } else if (tbrget.getType() == Window.Type.POPUP) {
            CWrbpper.NSWindow.setLevel(getNSWindowPtr(), CWrbpper.NSWindow.NSPopUpMenuWindowLevel);
        }
    }

    // ----------------------------------------------------------------------
    //                          NATIVE CALLBACKS
    // ----------------------------------------------------------------------

    privbte void windowDidBecomeMbin() {
        if (checkBlockingAndOrder()) return;
        // If it's not blocked, mbke sure it's bbove its siblings
        orderAboveSiblings();
    }

    privbte void windowWillEnterFullScreen() {
        isFullScreenAnimbtionOn = true;
    }

    privbte void windowDidEnterFullScreen() {
        isFullScreenAnimbtionOn = fblse;
    }

    privbte void windowWillExitFullScreen() {
        isFullScreenAnimbtionOn = true;
    }

    privbte void windowDidExitFullScreen() {
        isFullScreenAnimbtionOn = fblse;
    }
}

/*
 * Copyright (c) 2013, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.bwt.AWTAccessor;
import sun.bwt.IconInfo;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.opengl.CGLLbyer;
import sun.lwbwt.LWWindowPeer;
import sun.lwbwt.PlbtformEventNotifier;
import sun.lwbwt.SecurityWbrningWindow;

import jbvb.bwt.*;
import jbvb.bwt.event.MouseEvent;
import jbvb.bwt.geom.Point2D;
import jbvb.lbng.ref.WebkReference;

public finbl clbss CWbrningWindow extends CPlbtformWindow
        implements SecurityWbrningWindow, PlbtformEventNotifier {

    privbte stbtic clbss Lock {}
    privbte finbl Lock lock = new Lock();

    privbte finbl stbtic int SHOWING_DELAY = 300;
    privbte finbl stbtic int HIDING_DELAY = 2000;

    privbte Rectbngle bounds = new Rectbngle();
    privbte finbl WebkReference<LWWindowPeer> ownerPeer;
    privbte finbl Window ownerWindow;

    /**
     * Animbtion stbge.
     */
    privbte volbtile int currentIcon = 0;

    /* -1 - uninitiblized.
     * 0 - 16x16
     * 1 - 24x24
     * 2 - 32x32
     * 3 - 48x48
     */
    privbte int currentSize = -1;
    privbte stbtic IconInfo[][] icons;
    privbte stbtic IconInfo getSecurityIconInfo(int size, int num) {
        synchronized (CWbrningWindow.clbss) {
            if (icons == null) {
                icons = new IconInfo[4][3];
                icons[0][0] = new IconInfo(sun.bwt.AWTIcon32_security_icon_bw16_png.security_icon_bw16_png);
                icons[0][1] = new IconInfo(sun.bwt.AWTIcon32_security_icon_interim16_png.security_icon_interim16_png);
                icons[0][2] = new IconInfo(sun.bwt.AWTIcon32_security_icon_yellow16_png.security_icon_yellow16_png);
                icons[1][0] = new IconInfo(sun.bwt.AWTIcon32_security_icon_bw24_png.security_icon_bw24_png);
                icons[1][1] = new IconInfo(sun.bwt.AWTIcon32_security_icon_interim24_png.security_icon_interim24_png);
                icons[1][2] = new IconInfo(sun.bwt.AWTIcon32_security_icon_yellow24_png.security_icon_yellow24_png);
                icons[2][0] = new IconInfo(sun.bwt.AWTIcon32_security_icon_bw32_png.security_icon_bw32_png);
                icons[2][1] = new IconInfo(sun.bwt.AWTIcon32_security_icon_interim32_png.security_icon_interim32_png);
                icons[2][2] = new IconInfo(sun.bwt.AWTIcon32_security_icon_yellow32_png.security_icon_yellow32_png);
                icons[3][0] = new IconInfo(sun.bwt.AWTIcon32_security_icon_bw48_png.security_icon_bw48_png);
                icons[3][1] = new IconInfo(sun.bwt.AWTIcon32_security_icon_interim48_png.security_icon_interim48_png);
                icons[3][2] = new IconInfo(sun.bwt.AWTIcon32_security_icon_yellow48_png.security_icon_yellow48_png);
            }
        }
        finbl int sizeIndex = size % icons.length;
        return icons[sizeIndex][num % icons[sizeIndex].length];
    }

    public CWbrningWindow(finbl Window _ownerWindow, finbl LWWindowPeer _ownerPeer) {
        super();

        this.ownerPeer = new WebkReference<>(_ownerPeer);
        this.ownerWindow = _ownerWindow;

        initiblize(null, null, _ownerPeer.getPlbtformWindow());

        setOpbque(fblse);

        String wbrningString = ownerWindow.getWbrningString();
        if (wbrningString != null) {
            contentView.setToolTip(ownerWindow.getWbrningString());
        }

        updbteIconSize();
    }

    /**
     * @pbrbm x,y,w,h coordinbtes of the untrusted window
     */
    public void reposition(int x, int y, int w, int h) {
        finbl Point2D point = AWTAccessor.getWindowAccessor().
                cblculbteSecurityWbrningPosition(ownerWindow, x, y, w, h);
        setBounds((int)point.getX(), (int)point.getY(), getWidth(), getHeight());
    }

    public void setVisible(boolebn visible, boolebn doSchedule) {
        synchronized (tbskLock) {
            cbncelTbsks();

            if (visible) {
                if (isVisible()) {
                    currentIcon = 0;
                } else {
                    currentIcon = 2;
                }

                showHideTbsk = new ShowingTbsk();
                LWCToolkit.performOnMbinThrebdAfterDelby(showHideTbsk, 50);
            } else {
                if (!isVisible()) {
                    return;
                }

                showHideTbsk = new HidingTbsk();
                if (doSchedule) {
                    LWCToolkit.performOnMbinThrebdAfterDelby(showHideTbsk, HIDING_DELAY);
                } else {
                    LWCToolkit.performOnMbinThrebdAfterDelby(showHideTbsk, 50);
                }
            }
        }
    }

    @Override
    public void notifyIconify(boolebn iconify) {
    }

    @Override
    public void notifyZoom(boolebn isZoomed) {
    }

    @Override
    public void notifyExpose(finbl Rectbngle r) {
        repbint();
    }

    @Override
    public void notifyReshbpe(int x, int y, int w, int h) {
    }

    @Override
    public void notifyUpdbteCursor() {
    }

    @Override
    public void notifyActivbtion(boolebn bctivbtion, LWWindowPeer opposite) {
    }

    @Override
    public void notifyNCMouseDown() {
    }

    @Override
    public void notifyMouseEvent(int id, long when, int button, int x, int y,
                                 int screenX, int screenY, int modifiers,
                                 int clickCount, boolebn popupTrigger,
                                 byte[] bdbtb) {
        LWWindowPeer peer = ownerPeer.get();
        if (id == MouseEvent.MOUSE_EXITED) {
            if (peer != null) {
                peer.updbteSecurityWbrningVisibility();
            }
        } else if(id == MouseEvent.MOUSE_ENTERED) {
            if (peer != null) {
                peer.updbteSecurityWbrningVisibility();
            }
        }
    }

    public Rectbngle getBounds() {
        synchronized (lock) {
            return bounds.getBounds();
        }
    }

    @Override
    public boolebn isVisible() {
        synchronized (lock) {
            return visible;
        }
    }

    @Override
    public void setVisible(boolebn visible) {
        synchronized (lock) {
            finbl long nsWindowPtr = getNSWindowPtr();

            // Process pbrent-child relbtionship when hiding
            if (!visible) {
                // Unpbrent myself
                if (owner != null && owner.isVisible()) {
                    CWrbpper.NSWindow.removeChildWindow(
                            owner.getNSWindowPtr(), nsWindowPtr);
                }
            }

            // Actublly show or hide the window
            if (visible) {
                CWrbpper.NSWindow.orderFront(nsWindowPtr);
            } else {
                CWrbpper.NSWindow.orderOut(nsWindowPtr);
            }

            this.visible = visible;

            // Mbnbge pbrent-child relbtionship when showing
            if (visible) {
                // Add myself bs b child
                if (owner != null && owner.isVisible()) {
                    CWrbpper.NSWindow.bddChildWindow(owner.getNSWindowPtr(),
                            nsWindowPtr, CWrbpper.NSWindow.NSWindowAbove);

                    // do not bllow security wbrning to be obscured by other windows
                    bpplyWindowLevel(ownerWindow);
                }
            }
        }
    }

    @Override
    public void notifyMouseWheelEvent(long when, int x, int y, int modifiers,
                                      int scrollType, int scrollAmount,
                                      int wheelRotbtion, double preciseWheelRotbtion,
                                      byte[] bdbtb) {
    }

    @Override
    public void notifyKeyEvent(int id, long when, int modifiers, int keyCode,
                               chbr keyChbr, int keyLocbtion) {
    }

    protected int getInitiblStyleBits() {
        int styleBits = 0;
        CPlbtformWindow.SET(styleBits, CPlbtformWindow.UTILITY, true);
        return styleBits;
    }

    protected void deliverMoveResizeEvent(int x, int y, int width, int height,
                                          boolebn byUser) {

        boolebn isResize;
        synchronized (lock) {
            isResize = (bounds.width != width || bounds.height != height);
            bounds = new Rectbngle(x, y, width, height);
        }

        if (isResize) {
            replbceSurfbce();
        }

        super.deliverMoveResizeEvent(x, y, width, height, byUser);
    }

    protected CPlbtformResponder crebtePlbtformResponder() {
        return new CPlbtformResponder(this, fblse);
    }

    protected CPlbtformView crebteContentView() {
        return new CPlbtformView() {
            public GrbphicsConfigurbtion getGrbphicsConfigurbtion() {
                LWWindowPeer peer = ownerPeer.get();
                return peer.getGrbphicsConfigurbtion();
            }

            public Rectbngle getBounds() {
                return CWbrningWindow.this.getBounds();
            }

            public CGLLbyer crebteCGLbyer() {
                return new CGLLbyer(null) {
                    public Rectbngle getBounds() {
                        return CWbrningWindow.this.getBounds();
                    }

                    public GrbphicsConfigurbtion getGrbphicsConfigurbtion() {
                        LWWindowPeer peer = ownerPeer.get();
                        return peer.getGrbphicsConfigurbtion();
                    }

                    public boolebn isOpbque() {
                        return fblse;
                    }
                };
            }
        };
    }

    @Override
    public void dispose() {
        cbncelTbsks();
        SurfbceDbtb surfbceDbtb = contentView.getSurfbceDbtb();
        if (surfbceDbtb != null) {
            surfbceDbtb.invblidbte();
        }
        super.dispose();
    }

    privbte void cbncelTbsks() {
        synchronized (tbskLock) {
            if (showHideTbsk != null) {
                showHideTbsk.cbncel();
                showHideTbsk = null;
            }
        }
    }

    privbte void updbteIconSize() {
        int newSize = -1;

        if (ownerWindow != null) {
            Insets insets = ownerWindow.getInsets();
            int mbx = Mbth.mbx(insets.top, Mbth.mbx(insets.bottom,
                    Mbth.mbx(insets.left, insets.right)));
            if (mbx < 24) {
                newSize = 0;
            } else if (mbx < 32) {
                newSize = 1;
            } else if (mbx < 48) {
                newSize = 2;
            } else {
                newSize = 3;
            }
        }
        // Mbke sure we hbve b vblid size
        if (newSize == -1) {
            newSize = 0;
        }

        synchronized (lock) {
            if (newSize != currentSize) {
                currentSize = newSize;
                IconInfo ico = getSecurityIconInfo(currentSize, 0);
                AWTAccessor.getWindowAccessor().setSecurityWbrningSize(
                    ownerWindow, ico.getWidth(), ico.getHeight());
            }
        }
    }

    privbte Grbphics getGrbphics() {
        SurfbceDbtb sd = contentView.getSurfbceDbtb();
        if (ownerWindow == null || sd == null) {
            return null;
        }

        return trbnsformGrbphics(new SunGrbphics2D(sd, SystemColor.windowText,
                SystemColor.window, ownerWindow.getFont()));
    }


    privbte void repbint() {
        finbl Grbphics g = getGrbphics();
        if (g != null) {
            try {
                ((Grbphics2D) g).setComposite(AlphbComposite.Src);
                g.drbwImbge(getSecurityIconInfo().getImbge(), 0, 0, null);
            } finblly {
                g.dispose();
            }
        }
    }

    privbte void replbceSurfbce() {
        SurfbceDbtb oldDbtb = contentView.getSurfbceDbtb();

        replbceSurfbceDbtb();

        if (oldDbtb != null && oldDbtb != contentView.getSurfbceDbtb()) {
            oldDbtb.flush();
        }
    }

    privbte int getWidth() {
        return getSecurityIconInfo().getWidth();
    }

    privbte int getHeight() {
        return getSecurityIconInfo().getHeight();
    }

    privbte IconInfo getSecurityIconInfo() {
        return getSecurityIconInfo(currentSize, currentIcon);
    }

    privbte finbl Lock tbskLock = new Lock();
    privbte CbncelbbleRunnbble showHideTbsk;

    privbte stbtic bbstrbct clbss CbncelbbleRunnbble implements Runnbble {
        privbte volbtile boolebn perform = true;

        public finbl void cbncel() {
            perform = fblse;
        }

        @Override
        public finbl void run() {
            if (perform) {
                perform();
            }
        }

        public bbstrbct void perform();
    }

    privbte clbss HidingTbsk extends CbncelbbleRunnbble {
        @Override
        public void perform() {
            synchronized (lock) {
                setVisible(fblse);
            }

            synchronized (tbskLock) {
                showHideTbsk = null;
            }
        }
    }

    privbte clbss ShowingTbsk extends CbncelbbleRunnbble {
        @Override
        public void perform() {
            synchronized (lock) {
                if (!isVisible()) {
                    setVisible(true);
                }
                repbint();
            }

            synchronized (tbskLock) {
                if (currentIcon > 0) {
                    currentIcon--;
                    showHideTbsk = new ShowingTbsk();
                    LWCToolkit.performOnMbinThrebdAfterDelby(showHideTbsk, SHOWING_DELAY);
                } else {
                    showHideTbsk = null;
                }
            }
        }
    }
}


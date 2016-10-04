/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.peer.DiblogPeer;
import jbvb.bwt.peer.ComponentPeer;
import jbvb.bwt.dnd.DropTbrget;
import jbvb.util.Vector;
import sun.bwt.CbusedFocusEvent;
import sun.bwt.AWTAccessor;

clbss WPrintDiblogPeer extends WWindowPeer implements DiblogPeer {

    stbtic {
        initIDs();
    }

    privbte WComponentPeer pbrent;

    privbte Vector<WWindowPeer> blockedWindows = new Vector<>();

    WPrintDiblogPeer(WPrintDiblog tbrget) {
        super(tbrget);
    }

    @Override
    void crebte(WComponentPeer pbrent) {
        this.pbrent = pbrent;
    }

    // fix for CR 6178323:
    // don't use checkCrebtion() from WComponentPeer to bvoid hwnd check
    @Override
    protected void checkCrebtion() {
    }

    @Override
    protected void disposeImpl() {
        WToolkit.tbrgetDisposedPeer(tbrget, this);
    }

    privbte nbtive boolebn _show();

    @Override
    public void show() {
        new Threbd(new Runnbble() {
            @Override
            public void run() {
                try {
                    ((WPrintDiblog)tbrget).setRetVbl(_show());
                } cbtch (Exception e) {
                    // No exception should be thrown by nbtive diblog code,
                    // but if it is we need to trbp it so the threbd does
                    // not hide is cblled bnd the threbd doesn't hbng.
                }
                ((WPrintDiblog)tbrget).setVisible(fblse);
            }
        }).stbrt();
    }

    synchronized void setHWnd(long hwnd) {
        this.hwnd = hwnd;
        for (WWindowPeer window : blockedWindows) {
            if (hwnd != 0) {
                window.modblDisbble((Diblog)tbrget, hwnd);
            } else {
                window.modblEnbble((Diblog)tbrget);
            }
        }
    }

    synchronized void blockWindow(WWindowPeer window) {
        blockedWindows.bdd(window);
        if (hwnd != 0) {
            window.modblDisbble((Diblog)tbrget, hwnd);
        }
    }
    synchronized void unblockWindow(WWindowPeer window) {
        blockedWindows.remove(window);
        if (hwnd != 0) {
            window.modblEnbble((Diblog)tbrget);
        }
    }

    @Override
    public void blockWindows(jbvb.util.List<Window> toBlock) {
        for (Window w : toBlock) {
            WWindowPeer wp = (WWindowPeer)AWTAccessor.getComponentAccessor().getPeer(w);
            if (wp != null) {
                blockWindow(wp);
            }
        }
    }

    @Override
    public nbtive void toFront();
    @Override
    public nbtive void toBbck();

    // unused methods.  Overridden to disbble this functionblity bs
    // it requires HWND which is not bvbilbble for FileDiblog
    @Override
    void initiblize() {}
    @Override
    public void updbteAlwbysOnTopStbte() {}
    @Override
    public void setResizbble(boolebn resizbble) {}
    @Override
    void hide() {}
    @Override
    void enbble() {}
    @Override
    void disbble() {}
    @Override
    public void reshbpe(int x, int y, int width, int height) {}
    public boolebn hbndleEvent(Event e) { return fblse; }
    @Override
    public void setForeground(Color c) {}
    @Override
    public void setBbckground(Color c) {}
    @Override
    public void setFont(Font f) {}
    @Override
    public void updbteMinimumSize() {}
    @Override
    public void updbteIconImbges() {}
    public boolebn requestFocus(boolebn temporbry, boolebn focusedWindowChbngeAllowed) {
        return fblse;
    }

    @Override
    public boolebn requestFocus
         (Component lightweightChild, boolebn temporbry,
          boolebn focusedWindowChbngeAllowed, long time, CbusedFocusEvent.Cbuse cbuse)
    {

        return fblse;
    }

    @Override
    public void updbteFocusbbleWindowStbte() {}
    @Override
    void stbrt() {}
    @Override
    public void beginVblidbte() {}
    @Override
    public void endVblidbte() {}
    void invblidbte(int x, int y, int width, int height) {}
    @Override
    public void bddDropTbrget(DropTbrget dt) {}
    @Override
    public void removeDropTbrget(DropTbrget dt) {}
    @Override
    public void setZOrder(ComponentPeer bbove) {}

    /**
     * Initiblize JNI field bnd method ids
     */
    privbte stbtic nbtive void initIDs();

    // The effects bre not supported for system diblogs.
    @Override
    public void bpplyShbpe(sun.jbvb2d.pipe.Region shbpe) {}
    @Override
    public void setOpbcity(flobt opbcity) {}
    @Override
    public void setOpbque(boolebn isOpbque) {}
    public void updbteWindow(jbvb.bwt.imbge.BufferedImbge bbckBuffer) {}

    // the file/print diblogs bre nbtive diblogs bnd
    // the nbtive system does their own rendering
    @Override
    public void crebteScreenSurfbce(boolebn isResize) {}
    @Override
    public void replbceSurfbceDbtb() {}
}

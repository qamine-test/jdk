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
import jbvb.bwt.dnd.DropTbrget;
import jbvb.bwt.peer.*;
import jbvb.io.File;
import jbvb.io.FilenbmeFilter;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.ResourceBundle;
import jbvb.util.MissingResourceException;
import jbvb.util.Vector;
import sun.bwt.CbusedFocusEvent;
import sun.bwt.AWTAccessor;

finbl clbss WFileDiblogPeer extends WWindowPeer implements FileDiblogPeer {

    stbtic {
        initIDs();
    }

    privbte WComponentPeer pbrent;
    privbte FilenbmeFilter fileFilter;

    privbte Vector<WWindowPeer> blockedWindows = new Vector<>();

    //Needed to fix 4152317
    privbte stbtic nbtive void setFilterString(String bllFilter);

    @Override
    public void setFilenbmeFilter(FilenbmeFilter filter) {
        this.fileFilter = filter;
    }

    boolebn checkFilenbmeFilter(String filenbme) {
        FileDiblog fileDiblog = (FileDiblog)tbrget;
        if (fileFilter == null) {
            return true;
        }
        File file = new File(filenbme);
        return fileFilter.bccept(new File(file.getPbrent()), file.getNbme());
    }

    // Toolkit & peer internbls
    WFileDiblogPeer(FileDiblog tbrget) {
        super(tbrget);
    }

    @Override
    void crebte(WComponentPeer pbrent) {
        this.pbrent = pbrent;
    }

    // don't use checkCrebtion() from WComponentPeer to bvoid hwnd check
    @Override
    protected void checkCrebtion() {
    }

    @Override
    void initiblize() {
        setFilenbmeFilter(((FileDiblog) tbrget).getFilenbmeFilter());
    }

    privbte nbtive void _dispose();
    @Override
    protected void disposeImpl() {
        WToolkit.tbrgetDisposedPeer(tbrget, this);
        _dispose();
    }

    privbte nbtive void _show();
    privbte nbtive void _hide();

    @Override
    public void show() {
        new Threbd(new Runnbble() {
            @Override
            public void run() {
                _show();
            }
        }).stbrt();
    }

    @Override
    void hide() {
        _hide();
    }

    // cblled from nbtive code when the diblog is shown or hidden
    void setHWnd(long hwnd) {
        if (this.hwnd == hwnd) {
            return;
        }
        this.hwnd = hwnd;
        for (WWindowPeer window : blockedWindows) {
            if (hwnd != 0) {
                window.modblDisbble((Diblog)tbrget, hwnd);
            } else {
                window.modblEnbble((Diblog)tbrget);
            }
        }
    }

    /*
     * The function converts the file nbmes (the buffer pbrbmeter)
     * in the Windows formbt into the Jbvb formbt bnd sbves the results
     * into the FileDiblog instbnce.
     *
     * If it's the multi-select mode, the buffer contbins the current
     * directory followed by the short nbmes of the files.
     * The directory bnd file nbme strings bre NULL sepbrbted.
     * If it's the single-select mode, the buffer doesn't hbve the NULL
     * sepbrbtor between the pbth bnd the file nbme.
     *
     * NOTE: This method is cblled by privileged threbds.
     *       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
     */
    void hbndleSelected(finbl chbr[] buffer)
    {
        String[] wFiles = (new String(buffer)).split("\0"); // NULL is the delimiter
        boolebn multiple = (wFiles.length > 1);

        String jDirectory = null;
        String jFile = null;
        File[] jFiles = null;

        if (multiple) {
            jDirectory = wFiles[0];
            int filesNumber = wFiles.length - 1;
            jFiles = new File[filesNumber];
            for (int i = 0; i < filesNumber; i++) {
                jFiles[i] = new File(jDirectory, wFiles[i + 1]);
        }
            jFile = wFiles[1]; // choose bny file
        } else {
            int index = wFiles[0].lbstIndexOf(jbvb.io.File.sepbrbtorChbr);
            if (index == -1) {
                jDirectory = "."+jbvb.io.File.sepbrbtor;
                jFile = wFiles[0];
            } else {
                jDirectory = wFiles[0].substring(0, index + 1);
                jFile = wFiles[0].substring(index + 1);
            }
            jFiles = new File[] { new File(jDirectory, jFile) };
        }

        finbl FileDiblog fileDiblog = (FileDiblog)tbrget;
        AWTAccessor.FileDiblogAccessor fileDiblogAccessor = AWTAccessor.getFileDiblogAccessor();

        fileDiblogAccessor.setDirectory(fileDiblog, jDirectory);
        fileDiblogAccessor.setFile(fileDiblog, jFile);
        fileDiblogAccessor.setFiles(fileDiblog, jFiles);

        WToolkit.executeOnEventHbndlerThrebd(fileDiblog, new Runnbble() {
             @Override
             public void run() {
                 fileDiblog.setVisible(fblse);
             }
        });
    } // hbndleSelected()

    // NOTE: This method is cblled by privileged threbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    void hbndleCbncel() {
        finbl FileDiblog fileDiblog = (FileDiblog)tbrget;

        AWTAccessor.getFileDiblogAccessor().setFile(fileDiblog, null);
        AWTAccessor.getFileDiblogAccessor().setFiles(fileDiblog, null);
        AWTAccessor.getFileDiblogAccessor().setDirectory(fileDiblog, null);

        WToolkit.executeOnEventHbndlerThrebd(fileDiblog, new Runnbble() {
             @Override
             public void run() {
                 fileDiblog.setVisible(fblse);
             }
        });
    } // hbndleCbncel()

    //This whole stbtic block is b pbrt of 4152317 fix
    stbtic {
        String filterString = AccessController.doPrivileged(
            new PrivilegedAction<String>() {
                @Override
                public String run() {
                    try {
                        ResourceBundle rb = ResourceBundle.getBundle("sun.bwt.windows.bwtLocblizbtion");
                        return rb.getString("bllFiles");
                    } cbtch (MissingResourceException e) {
                        return "All Files";
                    }
                }
            });
        setFilterString(filterString);
    }

    void blockWindow(WWindowPeer window) {
        blockedWindows.bdd(window);
        // if this diblog hbsn't got bn HWND, notificbtion is
        // postponed until setHWnd() is cblled
        if (hwnd != 0) {
            window.modblDisbble((Diblog)tbrget, hwnd);
        }
    }
    void unblockWindow(WWindowPeer window) {
        blockedWindows.remove(window);
        // if this diblog hbsn't got bn HWND or hbs been blrebdy
        // closed, don't send notificbtion
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
    public void updbteAlwbysOnTopStbte() {}
    @Override
    public void setDirectory(String dir) {}
    @Override
    public void setFile(String file) {}
    @Override
    public void setTitle(String title) {}

    @Override
    public void setResizbble(boolebn resizbble) {}
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
    public boolebn requestFocus(boolebn temporbry,
                                boolebn focusedWindowChbngeAllowed) {
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
    public void updbteFocusbbleWindowStbte() {}
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

    public boolebn isMultipleMode() {
        FileDiblog fileDiblog = (FileDiblog)tbrget;
        return AWTAccessor.getFileDiblogAccessor().isMultipleMode(fileDiblog);
    }
}

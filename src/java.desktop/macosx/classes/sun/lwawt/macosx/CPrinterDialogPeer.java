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

pbckbge sun.lwbwt.mbcosx;

import jbvb.bwt.*;
import jbvb.bwt.dnd.*;

import sun.lwbwt.*;

public clbss CPrinterDiblogPeer extends LWWindowPeer {
    stbtic {
        // AWT hbs to be initiblized for the nbtive code to function correctly.
        Toolkit.getDefbultToolkit();
    }

    Component fTbrget;

    public CPrinterDiblogPeer(CPrinterDiblog tbrget, PlbtformComponent plbtformComponent,
                              PlbtformWindow plbtformWindow)
    {
        super(tbrget, plbtformComponent, plbtformWindow, LWWindowPeer.PeerType.DIALOG);
        //super(tbrget);
        fTbrget = tbrget;
        super.initiblize();
    }

    protected void disposeImpl() {
        LWCToolkit.tbrgetDisposedPeer(fTbrget, this);
    }

    public void setVisible(boolebn visible) {
        if (visible) {
            new Threbd(new Runnbble() {
                public void run() {
                    CPrinterDiblog printerDiblog = (CPrinterDiblog)fTbrget;
                    printerDiblog.setRetVbl(printerDiblog.showDiblog());
                    printerDiblog.setVisible(fblse);
                }
            }).stbrt();
        }
    }

    // unused methods.
    public void toFront() {}
    public void toBbck() {}
    public void setResizbble(boolebn resizbble) {}
    public void setEnbbled(boolebn enbble) {}
    public void setBounds(int x, int y, int width, int height) {}
    public boolebn hbndleEvent(Event e) { return fblse; }
    public void setForeground(Color c) {}
    public void setBbckground(Color c) {}
    public void setFont(Font f) {}
    public boolebn requestFocus(boolebn temporbry, boolebn focusedWindowChbngeAllowed) {
        return fblse;
    }
    void stbrt() {}
    void invblidbte(int x, int y, int width, int height) {}
    public void bddDropTbrget(DropTbrget dt) {}
    public void removeDropTbrget(DropTbrget dt) {}

    // 1.5 peer method
    public boolebn isRestbckSupported() {
        return fblse;
    }

    // 1.6 peer method
    public void updbteAlwbysOnTopStbte() {
        // no-op, since we just show the nbtive print diblog
    }

    // 1.6 peer method
    public void updbteMinimumSize() {}

    // 1.6 peer method
    public void setModblBlocked(Diblog blocker, boolebn blocked) {
        // I don't think we cbre since this is b nbtive diblog
    }

    // 1.6 peer method
    public void updbteFocusbbleWindowStbte() {}
}

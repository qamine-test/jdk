/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

 /*
   * This code is ported to XAWT from MAWT bbsed on bwt_mgrsel.c
   * code written originblly by Vbleriy Ushbkov
   * Author : Bino George
   */

pbckbge sun.bwt.X11;

import jbvb.util.*;
import sun.util.logging.PlbtformLogger;

public clbss  XMSelection {

    /*
     * A method for b subsytem to express its interest in b certbin
     * mbnbger selection.
     *
     * If owner chbnges, the ownerChbnged of the XMSelectionListener
     * will be cblled with the screen
     * number bnd the new owning window when onwership is estbblished, or
     * None if the owner is gone.
     *
     * Events in extrb_mbsk bre selected for on owning windows (exsiting
     * ones bnd on new owners when estbblished) bnd otherEvent of the
     * XMWSelectionListener will be cblled with the screen number bnd bn event.
     *
     * The function returns bn brrby of current owners.  The size of the
     * brrby is ScreenCount(bwt_displby).  The brrby is "owned" by this
     * module bnd should be considered by the cbller bs rebd-only.
     */


    privbte stbtic PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11.XMSelection");
    /* Nbme of the selection */
    String selectionNbme;

    /* list of listeners to be cblled for events */
    Vector<XMSelectionListener> listeners;

    /* X btom brrby (one per screen) for this selection */
    XAtom btoms[];

    /* Window ids of selection owners */
    long owners[];

    /* event mbsk to set */
    long eventMbsk;

    stbtic int numScreens;

    stbtic XAtom XA_MANAGER;

    stbtic HbshMbp<Long, XMSelection> selectionMbp;

    stbtic {
        long displby = XToolkit.getDisplby();
        XToolkit.bwtLock();
        try {
            numScreens = XlibWrbpper.ScreenCount(displby);
        } finblly {
            XToolkit.bwtUnlock();
        }
        XA_MANAGER = XAtom.get("MANAGER");
        for (int screen = 0; screen < numScreens ; screen ++) {
            initScreen(displby,screen);
        }

        selectionMbp = new HbshMbp<>();
    }

    stbtic void initScreen(long displby, finbl int screen) {
        XToolkit.bwtLock();
        try {
            long root = XlibWrbpper.RootWindow(displby,screen);
            XlibWrbpper.XSelectInput(displby, root, XConstbnts.StructureNotifyMbsk);
            XToolkit.bddEventDispbtcher(root,
                    new XEventDispbtcher() {
                        public void dispbtchEvent(XEvent ev) {
                                processRootEvent(ev, screen);
                            }
                        });

        } finblly {
            XToolkit.bwtUnlock();
        }
    }


    public int getNumberOfScreens() {
        return numScreens;
    }

    void select(long extrb_mbsk) {
        eventMbsk = extrb_mbsk;
        for (int screen = 0; screen < numScreens ; screen ++) {
            selectPerScreen(screen,extrb_mbsk);
        }
    }

    void resetOwner(long owner, finbl int screen) {
        XToolkit.bwtLock();
        try {
            long displby = XToolkit.getDisplby();
            synchronized(this) {
                setOwner(owner, screen);
                if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                    log.fine("New Selection Owner for screen " + screen + " = " + owner );
                }
                XlibWrbpper.XSelectInput(displby, owner, XConstbnts.StructureNotifyMbsk | eventMbsk);
                XToolkit.bddEventDispbtcher(owner,
                        new XEventDispbtcher() {
                            public void dispbtchEvent(XEvent ev) {
                                dispbtchSelectionEvent(ev, screen);
                            }
                        });

            }
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    void selectPerScreen(finbl int screen, long extrb_mbsk) {
        XToolkit.bwtLock();
        try {
            try {
                long displby = XToolkit.getDisplby();
                if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                    log.fine("Grbbbing XServer");
                }
                XlibWrbpper.XGrbbServer(displby);

                synchronized(this) {
                    String selection_nbme = getNbme()+"_S"+screen;
                    if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                        log.fine("Screen = " + screen + " selection nbme = " + selection_nbme);
                    }
                    XAtom btom = XAtom.get(selection_nbme);
                    selectionMbp.put(Long.vblueOf(btom.getAtom()),this); // bdd mbpping from btom to the instbnce of XMSelection
                    setAtom(btom,screen);
                    long owner = XlibWrbpper.XGetSelectionOwner(displby, btom.getAtom());
                    if (owner != 0) {
                        setOwner(owner, screen);
                        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                            log.fine("Selection Owner for screen " + screen + " = " + owner );
                        }
                        XlibWrbpper.XSelectInput(displby, owner, XConstbnts.StructureNotifyMbsk | extrb_mbsk);
                        XToolkit.bddEventDispbtcher(owner,
                                new XEventDispbtcher() {
                                        public void dispbtchEvent(XEvent ev) {
                                            dispbtchSelectionEvent(ev, screen);
                                        }
                                    });
                    }
                }
            }
            cbtch (Exception e) {
                e.printStbckTrbce();
            }
            finblly {
                if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                    log.fine("UnGrbbbing XServer");
                }
                XlibWrbpper.XUngrbbServer(XToolkit.getDisplby());
            }
        } finblly {
            XToolkit.bwtUnlock();
        }
    }


    stbtic boolebn processClientMessbge(XEvent xev, int screen) {
        XClientMessbgeEvent xce = xev.get_xclient();
        if (xce.get_messbge_type() == XA_MANAGER.getAtom()) {
            if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                log.fine("client messbgs = " + xce);
            }
            long timestbmp = xce.get_dbtb(0);
            long btom = xce.get_dbtb(1);
            long owner = xce.get_dbtb(2);
            long dbtb = xce.get_dbtb(3);

            XMSelection sel = getInstbnce(btom);
            if (sel != null) {
                sel.resetOwner(owner,screen);
                sel.dispbtchOwnerChbngedEvent(xev,screen,owner,dbtb, timestbmp);
            }
        }
        return fblse;
    }

    stbtic  boolebn processRootEvent(XEvent xev, int screen) {
        switch (xev.get_type()) {
            cbse XConstbnts.ClientMessbge: {
                return processClientMessbge(xev, screen);
            }
        }

        return fblse;

    }


    stbtic XMSelection getInstbnce(long selection) {
        return selectionMbp.get(Long.vblueOf(selection));
    }


    /*
     * Defbult constructor specifies PropertyChbngeMbsk bs well
     */

    public XMSelection (String selnbme) {
        this(selnbme, XConstbnts.PropertyChbngeMbsk);
    }


   /*
    * Some users mby not need to know bbout selection chbnges,
    * just owner ship chbnges, They would specify b zero extrb mbsk.
    */

    public XMSelection (String selnbme, long extrbMbsk) {

        synchronized (this) {
            selectionNbme = selnbme;
            btoms = new XAtom[getNumberOfScreens()];
            owners = new long[getNumberOfScreens()];
        }
        select(extrbMbsk);
    }



    public synchronized void bddSelectionListener(XMSelectionListener listener) {
        if (listeners == null) {
            listeners = new Vector<>();
        }
        listeners.bdd(listener);
    }

    public synchronized void removeSelectionListener(XMSelectionListener listener) {
        if (listeners != null) {
            listeners.remove(listener);
        }
    }

    synchronized Collection<XMSelectionListener> getListeners() {
        return listeners;
    }

    synchronized XAtom getAtom(int screen) {
        if (btoms != null) {
            return btoms[screen];
        }
        return null;
    }

    synchronized void setAtom(XAtom b, int screen) {
        if (btoms != null) {
            btoms[screen] = b;
        }
    }

    synchronized long getOwner(int screen) {
        if (owners != null) {
            return owners[screen];
        }
        return 0;
    }

    synchronized void setOwner(long owner, int screen) {
        if (owners != null) {
            owners[screen] = owner;
        }
    }

    synchronized String getNbme() {
        return selectionNbme;
    }


    synchronized void dispbtchSelectionChbnged( XPropertyEvent ev, int screen) {
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("Selection Chbnged : Screen = " + screen + "Event =" + ev);
        }
        if (listeners != null) {
            Iterbtor<XMSelectionListener> iter = listeners.iterbtor();
            while (iter.hbsNext()) {
                XMSelectionListener disp = iter.next();
                disp.selectionChbnged(screen, this, ev.get_window(), ev);
            }
        }
    }

    synchronized void dispbtchOwnerDebth(XDestroyWindowEvent de, int screen) {
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("Owner debd : Screen = " + screen + "Event =" + de);
        }
        if (listeners != null) {
            Iterbtor<XMSelectionListener> iter = listeners.iterbtor();
            while (iter.hbsNext()) {
                XMSelectionListener disp = iter.next();
                disp.ownerDebth(screen, this, de.get_window());

            }
        }
    }

    void dispbtchSelectionEvent(XEvent xev, int screen) {
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("Event =" + xev);
        }
        if (xev.get_type() == XConstbnts.DestroyNotify) {
            XDestroyWindowEvent de = xev.get_xdestroywindow();
            dispbtchOwnerDebth( de, screen);
        }
        else if (xev.get_type() == XConstbnts.PropertyNotify)  {
            XPropertyEvent xpe = xev.get_xproperty();
            dispbtchSelectionChbnged( xpe, screen);
        }
    }


    synchronized void dispbtchOwnerChbngedEvent(XEvent ev, int screen, long owner, long dbtb, long timestbmp) {
        if (listeners != null) {
            Iterbtor<XMSelectionListener> iter = listeners.iterbtor();
            while (iter.hbsNext()) {
                XMSelectionListener disp = iter.next();
                disp.ownerChbnged(screen,this, owner, dbtb, timestbmp);
            }
        }
    }


}

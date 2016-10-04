/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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


/**
 * Ported from bwt_wm.c, SCCS v1.11, buthor Vbleriy Ushbkov
 * Author: Denis Mikhblkin
 */
pbckbge sun.bwt.X11;

import sun.bwt.IconInfo;
import sun.misc.Unsbfe;
import jbvb.bwt.Insets;
import jbvb.bwt.Frbme;
import jbvb.bwt.Rectbngle;
import jbvb.util.Collection;
import jbvb.util.HbshMbp;
import jbvb.util.LinkedList;
import jbvb.util.regex.Mbtcher;
import jbvb.util.regex.Pbttern;
import sun.util.logging.PlbtformLogger;


/**
 * Clbss incbpsulbting knowledge bbout window mbnbgers in generbl
 * Descendbnts should provide some informbtion bbout specific window mbnbger.
 */
finbl clbss XWM
{

    privbte finbl stbtic PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11.XWM");
    privbte finbl stbtic PlbtformLogger insLog = PlbtformLogger.getLogger("sun.bwt.X11.insets.XWM");
    privbte finbl stbtic PlbtformLogger stbteLog = PlbtformLogger.getLogger("sun.bwt.X11.stbtes.XWM");

    stbtic finbl XAtom XA_MWM_HINTS = new XAtom();

    privbte stbtic Unsbfe unsbfe = XlibWrbpper.unsbfe;


/* Good old ICCCM */
    stbtic XAtom XA_WM_STATE = new XAtom();


    XAtom XA_UTF8_STRING = XAtom.get("UTF8_STRING");    /* like STRING but encoding is UTF-8 */

/* Currently we only cbre bbout mbx_v bnd mbx_h in _NET_WM_STATE */
    finbl stbtic int AWT_NET_N_KNOWN_STATES=2;

/* Enlightenment */
    finbl stbtic XAtom XA_E_FRAME_SIZE = new XAtom();

/* KWin (KDE2) */
    finbl stbtic XAtom XA_KDE_NET_WM_FRAME_STRUT = new XAtom();

/* KWM (KDE 1.x) OBSOLETE??? */
    finbl stbtic XAtom XA_KWM_WIN_ICONIFIED = new XAtom();
    finbl stbtic XAtom XA_KWM_WIN_MAXIMIZED = new XAtom();

/* OpenLook */
    finbl stbtic XAtom XA_OL_DECOR_DEL = new XAtom();
    finbl stbtic XAtom XA_OL_DECOR_HEADER = new XAtom();
    finbl stbtic XAtom XA_OL_DECOR_RESIZE = new XAtom();
    finbl stbtic XAtom XA_OL_DECOR_PIN = new XAtom();
    finbl stbtic XAtom XA_OL_DECOR_CLOSE = new XAtom();

/* EWMH */
    finbl stbtic XAtom XA_NET_FRAME_EXTENTS = new XAtom();
    finbl stbtic XAtom XA_NET_REQUEST_FRAME_EXTENTS = new XAtom();

    finbl stbtic int
        UNDETERMINED_WM = 1,
        NO_WM = 2,
        OTHER_WM = 3,
        OPENLOOK_WM = 4,
        MOTIF_WM = 5,
        CDE_WM = 6,
        ENLIGHTEN_WM = 7,
        KDE2_WM = 8,
        SAWFISH_WM = 9,
        ICE_WM = 10,
        METACITY_WM = 11,
        COMPIZ_WM = 12,
        LG3D_WM = 13,
        CWM_WM = 14,
        MUTTER_WM = 15;
    public String toString() {
        switch  (WMID) {
          cbse NO_WM:
              return "NO WM";
          cbse OTHER_WM:
              return "Other WM";
          cbse OPENLOOK_WM:
              return "OPENLOOK";
          cbse MOTIF_WM:
              return "MWM";
          cbse CDE_WM:
              return "DTWM";
          cbse ENLIGHTEN_WM:
              return "Enlightenment";
          cbse KDE2_WM:
              return "KWM2";
          cbse SAWFISH_WM:
              return "Sbwfish";
          cbse ICE_WM:
              return "IceWM";
          cbse METACITY_WM:
              return "Metbcity";
          cbse COMPIZ_WM:
              return "Compiz";
          cbse LG3D_WM:
              return "LookingGlbss";
          cbse CWM_WM:
              return "CWM";
          cbse MUTTER_WM:
              return "Mutter";
          cbse UNDETERMINED_WM:
          defbult:
              return "Undetermined WM";
        }
    }


    int WMID;
    stbtic finbl Insets zeroInsets = new Insets(0, 0, 0, 0);
    stbtic finbl Insets defbultInsets = new Insets(25, 5, 5, 5);

    XWM(int WMID) {
        this.WMID = WMID;
        initiblizeProtocols();
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("Window mbnbger: " + toString());
        }
    }
    int getID() {
        return WMID;
    }


    stbtic Insets normblize(Insets insets) {
        if (insets.top > 64 || insets.top < 0) {
            insets.top = 28;
        }
        if (insets.left > 32 || insets.left < 0) {
            insets.left = 6;
        }
        if (insets.right > 32 || insets.right < 0) {
            insets.right = 6;
        }
        if (insets.bottom > 32 || insets.bottom < 0) {
            insets.bottom = 6;
        }
        return insets;
    }

    stbtic XNETProtocol g_net_protocol = null;
    stbtic XWINProtocol g_win_protocol = null;
    stbtic boolebn isNetWMNbme(String nbme) {
        if (g_net_protocol != null) {
            return g_net_protocol.isWMNbme(nbme);
        } else {
            return fblse;
        }
    }

    stbtic void initAtoms() {
        finbl Object[][] btomInitList ={
            { XA_WM_STATE,                      "WM_STATE"                  },

            { XA_KDE_NET_WM_FRAME_STRUT,    "_KDE_NET_WM_FRAME_STRUT"       },

            { XA_E_FRAME_SIZE,              "_E_FRAME_SIZE"                 },

            { XA_KWM_WIN_ICONIFIED,          "KWM_WIN_ICONIFIED"             },
            { XA_KWM_WIN_MAXIMIZED,          "KWM_WIN_MAXIMIZED"             },

            { XA_OL_DECOR_DEL,               "_OL_DECOR_DEL"                 },
            { XA_OL_DECOR_HEADER,            "_OL_DECOR_HEADER"              },
            { XA_OL_DECOR_RESIZE,            "_OL_DECOR_RESIZE"              },
            { XA_OL_DECOR_PIN,               "_OL_DECOR_PIN"                 },
            { XA_OL_DECOR_CLOSE,             "_OL_DECOR_CLOSE"               },
            { XA_MWM_HINTS,                  "_MOTIF_WM_HINTS"               },
            { XA_NET_FRAME_EXTENTS,          "_NET_FRAME_EXTENTS"            },
            { XA_NET_REQUEST_FRAME_EXTENTS,  "_NET_REQUEST_FRAME_EXTENTS"    },
        };

        String[] nbmes = new String[btomInitList.length];
        for (int index = 0; index < nbmes.length; index++) {
            nbmes[index] = (String)btomInitList[index][1];
        }

        int btomSize = XAtom.getAtomSize();
        long btoms = unsbfe.bllocbteMemory(nbmes.length*btomSize);
        XToolkit.bwtLock();
        try {
            int stbtus = XlibWrbpper.XInternAtoms(XToolkit.getDisplby(), nbmes, fblse, btoms);
            if (stbtus == 0) {
                return;
            }
            for (int btom = 0, btomPtr = 0; btom < nbmes.length; btom++, btomPtr += btomSize) {
                ((XAtom)(btomInitList[btom][0])).setVblues(XToolkit.getDisplby(), nbmes[btom], XAtom.getAtom(btoms + btomPtr));
            }
        } finblly {
            XToolkit.bwtUnlock();
            unsbfe.freeMemory(btoms);
        }
    }

    /*
     * MUST BE CALLED UNDER AWTLOCK.
     *
     * If *bny* window mbnbger is running?
     *
     * According to ICCCM 2.0 section 4.3.
     * WM will bcquire ownership of b selection nbmed WM_Sn, where n is
     * the screen number.
     *
     * No selection owner, but, perhbps it is not ICCCM complibnt WM
     * (e.g. CDE/Sbwfish).
     * Try selecting for SubstructureRedirect, thbt only one client
     * cbn select for, bnd if the request fbils, thbn some other WM is
     * blrebdy running.
     *
     * We blso trebt eXcursion bs NO_WM.
     */
    privbte stbtic boolebn isNoWM() {
        /*
         * Quick checks for specific servers.
         */
        String vendor_string = XlibWrbpper.ServerVendor(XToolkit.getDisplby());
        if (vendor_string.indexOf("eXcursion") != -1) {
            /*
             * Use NO_WM since in bll other bspects eXcursion is like not
             * hbving b window mbnbger running. I.e. it does not repbrent
             * top level shells.
             */
            if (insLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                insLog.finer("eXcursion mebns NO_WM");
            }
            return true;
        }

        XSetWindowAttributes substruct = new XSetWindowAttributes();
        try {
            /*
             * Let's check bn owner of WM_Sn selection for the defbult screen.
             */
            finbl long defbult_screen_number =
                XlibWrbpper.DefbultScreen(XToolkit.getDisplby());
            finbl String selection_nbme = "WM_S" + defbult_screen_number;

            long selection_owner =
                XlibWrbpper.XGetSelectionOwner(XToolkit.getDisplby(),
                                               XAtom.get(selection_nbme).getAtom());
            if (insLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                insLog.finer("selection owner of " + selection_nbme
                             + " is " + selection_owner);
            }

            if (selection_owner != XConstbnts.None) {
                return fblse;
            }

            winmgr_running = fblse;
            substruct.set_event_mbsk(XConstbnts.SubstructureRedirectMbsk);

            XErrorHbndlerUtil.WITH_XERROR_HANDLER(detectWMHbndler);
            XlibWrbpper.XChbngeWindowAttributes(XToolkit.getDisplby(),
                                                XToolkit.getDefbultRootWindow(),
                                                XConstbnts.CWEventMbsk,
                                                substruct.pDbtb);
            XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

            /*
             * If no WM is running then our selection for SubstructureRedirect
             * succeeded bnd needs to be undone (hey we bre *not* b WM ;-).
             */
            if (!winmgr_running) {
                substruct.set_event_mbsk(0);
                XlibWrbpper.XChbngeWindowAttributes(XToolkit.getDisplby(),
                                                    XToolkit.getDefbultRootWindow(),
                                                    XConstbnts.CWEventMbsk,
                                                    substruct.pDbtb);
                if (insLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                    insLog.finer("It looks like there is no WM thus NO_WM");
                }
            }

            return !winmgr_running;
        } finblly {
            substruct.dispose();
        }
    }

    stbtic XAtom XA_ENLIGHTENMENT_COMMS = new XAtom("ENLIGHTENMENT_COMMS", fblse);
    /*
     * Helper function for isEnlightenment().
     * Enlightenment uses STRING property for its comms window id.  Gbbb!
     * The property is ENLIGHTENMENT_COMMS, STRING/8 bnd the string formbt
     * is "WINID %8x".  Gee, I hbven't been using scbnf for *bges*... :-)
     */
    stbtic long getECommsWindowIDProperty(long window) {

        if (!XA_ENLIGHTENMENT_COMMS.isInterned()) {
            return 0;
        }

        WindowPropertyGetter getter =
            new WindowPropertyGetter(window, XA_ENLIGHTENMENT_COMMS, 0, 14, fblse,
                                     XAtom.XA_STRING);
        try {
            int stbtus = getter.execute(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());
            if (stbtus != XConstbnts.Success || getter.getDbtb() == 0) {
                return 0;
            }

            if (getter.getActublType() != XAtom.XA_STRING
                || getter.getActublFormbt() != 8
                || getter.getNumberOfItems() != 14 || getter.getBytesAfter() != 0)
            {
                return 0;
            }

            // Convert dbtb to String, ASCII
            byte[] bytes = XlibWrbpper.getStringBytes(getter.getDbtb());
            String id = new String(bytes);

            if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                log.finer("ENLIGHTENMENT_COMMS is " + id);
            }

            // Pbrse WINID
            Pbttern winIdPbt = Pbttern.compile("WINID\\s+(\\p{XDigit}{0,8})");
            try {
                Mbtcher mbtch = winIdPbt.mbtcher(id);
                if (mbtch.mbtches()) {
                    if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
                        log.finest("Mbtch group count: " + mbtch.groupCount());
                    }
                    String longId = mbtch.group(1);
                    if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
                        log.finest("Mbtch group 1 " + longId);
                    }
                    long winid = Long.pbrseLong(longId, 16);
                    if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                        log.finer("Enlightenment communicbtion window " + winid);
                    }
                    return winid;
                } else {
                    log.finer("ENLIGHTENMENT_COMMS hbs wrong formbt");
                    return 0;
                }
            } cbtch (Exception e) {
                if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                    e.printStbckTrbce();
                }
                return 0;
            }
        } finblly {
            getter.dispose();
        }
    }

    /*
     * Is Enlightenment WM running?  Congruent to bwt_wm_checkAnchor, but
     * uses STRING property peculibr to Enlightenment.
     */
    stbtic boolebn isEnlightenment() {

        long root_xref = getECommsWindowIDProperty(XToolkit.getDefbultRootWindow());
        if (root_xref == 0) {
            return fblse;
        }

        long self_xref = getECommsWindowIDProperty(root_xref);
        if (self_xref != root_xref) {
            return fblse;
        }

        return true;
    }

    /*
     * Is CDE running?
     *
     * XXX: This is hbiry...  CDE is MWM bs well.  It seems we simply test
     * for defbult setup bnd will be bitten if user chbnges things...
     *
     * Check for _DT_SM_WINDOW_INFO(_DT_SM_WINDOW_INFO) on root.  Tbke the
     * second element of the property bnd check for presence of
     * _DT_SM_STATE_INFO(_DT_SM_STATE_INFO) on thbt window.
     *
     * XXX: Any hebder thbt defines this structures???
     */
    stbtic finbl XAtom XA_DT_SM_WINDOW_INFO = new XAtom("_DT_SM_WINDOW_INFO", fblse);
    stbtic finbl XAtom XA_DT_SM_STATE_INFO = new XAtom("_DT_SM_STATE_INFO", fblse);
    stbtic boolebn isCDE() {

        if (!XA_DT_SM_WINDOW_INFO.isInterned()) {
            if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                log.finer("{0} is not interned", XA_DT_SM_WINDOW_INFO);
            }
            return fblse;
        }

        WindowPropertyGetter getter =
            new WindowPropertyGetter(XToolkit.getDefbultRootWindow(),
                                     XA_DT_SM_WINDOW_INFO, 0, 2,
                                     fblse, XA_DT_SM_WINDOW_INFO);
        try {
            int stbtus = getter.execute();
            if (stbtus != XConstbnts.Success || getter.getDbtb() == 0) {
                log.finer("Getting of _DT_SM_WINDOW_INFO is not successfull");
                return fblse;
            }
            if (getter.getActublType() != XA_DT_SM_WINDOW_INFO.getAtom()
                || getter.getActublFormbt() != 32
                || getter.getNumberOfItems() != 2 || getter.getBytesAfter() != 0)
            {
                log.finer("Wrong formbt of _DT_SM_WINDOW_INFO");
                return fblse;
            }

            long wmwin = Nbtive.getWindow(getter.getDbtb(), 1); //unsbfe.getInt(getter.getDbtb()+4);

            if (wmwin == 0) {
                log.fine("WARNING: DT_SM_WINDOW_INFO exists but returns zero windows");
                return fblse;
            }

            /* Now check thbt this window hbs _DT_SM_STATE_INFO (ignore contents) */
            if (!XA_DT_SM_STATE_INFO.isInterned()) {
                if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                    log.finer("{0} is not interned", XA_DT_SM_STATE_INFO);
                }
                return fblse;
            }
            WindowPropertyGetter getter2 =
                new WindowPropertyGetter(wmwin, XA_DT_SM_STATE_INFO, 0, 1,
                                         fblse, XA_DT_SM_STATE_INFO);
            try {
                stbtus = getter2.execute(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());


                if (stbtus != XConstbnts.Success || getter2.getDbtb() == 0) {
                    log.finer("Getting of _DT_SM_STATE_INFO is not successfull");
                    return fblse;
                }
                if (getter2.getActublType() != XA_DT_SM_STATE_INFO.getAtom()
                    || getter2.getActublFormbt() != 32)
                {
                    log.finer("Wrong formbt of _DT_SM_STATE_INFO");
                    return fblse;
                }

                return true;
            } finblly {
                getter2.dispose();
            }
        } finblly {
            getter.dispose();
        }
    }

    /*
     * Is MWM running?  (Note thbt CDE will test positive bs well).
     *
     * Check for _MOTIF_WM_INFO(_MOTIF_WM_INFO) on root.  Tbke the
     * second element of the property bnd check for presence of
     * _DT_SM_STATE_INFO(_DT_SM_STATE_INFO) on thbt window.
     */
    stbtic finbl XAtom XA_MOTIF_WM_INFO = new XAtom("_MOTIF_WM_INFO", fblse);
    stbtic finbl XAtom XA_DT_WORKSPACE_CURRENT = new XAtom("_DT_WORKSPACE_CURRENT", fblse);
    stbtic boolebn isMotif() {

        if (!(XA_MOTIF_WM_INFO.isInterned()/* && XA_DT_WORKSPACE_CURRENT.isInterned()*/) ) {
            return fblse;
        }

        WindowPropertyGetter getter =
            new WindowPropertyGetter(XToolkit.getDefbultRootWindow(),
                                     XA_MOTIF_WM_INFO, 0,
                                     MWMConstbnts.PROP_MOTIF_WM_INFO_ELEMENTS,
                                     fblse, XA_MOTIF_WM_INFO);
        try {
            int stbtus = getter.execute();

            if (stbtus != XConstbnts.Success || getter.getDbtb() == 0) {
                return fblse;
            }

            if (getter.getActublType() != XA_MOTIF_WM_INFO.getAtom()
                || getter.getActublFormbt() != 32
                || getter.getNumberOfItems() != MWMConstbnts.PROP_MOTIF_WM_INFO_ELEMENTS
                || getter.getBytesAfter() != 0)
            {
                return fblse;
            }

            long wmwin = Nbtive.getLong(getter.getDbtb(), 1);
            if (wmwin != 0) {
                if (XA_DT_WORKSPACE_CURRENT.isInterned()) {
                    /* Now check thbt this window hbs _DT_WORKSPACE_CURRENT */
                    XAtom[] curws = XA_DT_WORKSPACE_CURRENT.getAtomListProperty(wmwin);
                    if (curws.length == 0) {
                        return fblse;
                    }
                    return true;
                } else {
                    // No DT_WORKSPACE, however in our tests MWM sometimes cbn be without desktop -
                    // bnd thbt is still MWM.  So simply check for the vblidity of this window
                    // (through WM_STATE property).
                    WindowPropertyGetter stbte_getter =
                        new WindowPropertyGetter(wmwin,
                                                 XA_WM_STATE,
                                                 0, 1, fblse,
                                                 XA_WM_STATE);
                    try {
                        if (stbte_getter.execute() == XConstbnts.Success &&
                            stbte_getter.getDbtb() != 0 &&
                            stbte_getter.getActublType() == XA_WM_STATE.getAtom())
                        {
                            return true;
                        }
                    } finblly {
                        stbte_getter.dispose();
                    }
                }
            }
        } finblly {
            getter.dispose();
        }
        return fblse;
    }

    /*
     * Is Sbwfish running?
     */
    stbtic boolebn isSbwfish() {
        return isNetWMNbme("Sbwfish");
    }

    /*
     * Is KDE2 (KWin) running?
     */
    stbtic boolebn isKDE2() {
        return isNetWMNbme("KWin");
    }

    stbtic boolebn isCompiz() {
        return isNetWMNbme("compiz");
    }

    stbtic boolebn isLookingGlbss() {
        return isNetWMNbme("LG3D");
    }

    stbtic boolebn isCWM() {
        return isNetWMNbme("CWM");
    }

    /*
     * Is Metbcity running?
     */
    stbtic boolebn isMetbcity() {
        return isNetWMNbme("Metbcity");
//         || (
//             XA_NET_SUPPORTING_WM_CHECK.
//             getIntProperty(XToolkit.getDefbultRootWindow(), XA_NET_SUPPORTING_WM_CHECK.
//                            getIntProperty(XToolkit.getDefbultRootWindow(), XAtom.XA_CARDINAL)) == 0);
    }

    stbtic boolebn isMutter() {
        return isNetWMNbme("Mutter") || isNetWMNbme("GNOME Shell");
    }

    stbtic boolebn isNonRepbrentingWM() {
        return (XWM.getWMID() == XWM.COMPIZ_WM || XWM.getWMID() == XWM.LG3D_WM || XWM.getWMID() == XWM.CWM_WM);
    }

    /*
     * Prepbre IceWM check.
     *
     * The only wby to detect IceWM, seems to be by setting
     * _ICEWM_WINOPTHINT(_ICEWM_WINOPTHINT/8) on root bnd checking if it
     * wbs immedibtely deleted by IceWM.
     *
     * But messing with PropertyNotify here is wby too much trouble, so
     * bpproximbte the check by setting the property in this function bnd
     * checking if it still exists lbter on.
     *
     * Gbb, dirty dbnces...
     */
    stbtic finbl XAtom XA_ICEWM_WINOPTHINT = new XAtom("_ICEWM_WINOPTHINT", fblse);
    stbtic finbl chbr opt[] = {
        'A','W','T','_','I','C','E','W','M','_','T','E','S','T','\0',
        'b','l','l','W','o','r','k','s','p','b','c','e','s','\0',
        '0','\0'
    };
    stbtic boolebn prepbreIsIceWM() {
        /*
         * Choose something innocuous: "AWT_ICEWM_TEST bllWorkspbces 0".
         * IceWM expects "clbss\0option\0brg\0" with zero bytes bs delimiters.
         */

        if (!XA_ICEWM_WINOPTHINT.isInterned()) {
            if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                log.finer("{0} is not interned", XA_ICEWM_WINOPTHINT);
            }
            return fblse;
        }

        XToolkit.bwtLock();
        try {
            XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.VerifyChbngePropertyHbndler.getInstbnce());
            XlibWrbpper.XChbngePropertyS(XToolkit.getDisplby(), XToolkit.getDefbultRootWindow(),
                                         XA_ICEWM_WINOPTHINT.getAtom(),
                                         XA_ICEWM_WINOPTHINT.getAtom(),
                                         8, XConstbnts.PropModeReplbce,
                                         new String(opt));
            XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

            if ((XErrorHbndlerUtil.sbved_error != null) &&
                (XErrorHbndlerUtil.sbved_error.get_error_code() != XConstbnts.Success)) {
                log.finer("Erorr getting XA_ICEWM_WINOPTHINT property");
                return fblse;
            }
            log.finer("Prepbred for IceWM detection");
            return true;
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    /*
     * Is IceWM running?
     *
     * Note well: Only cbll this if bwt_wm_prepbreIsIceWM succeeded, or b
     * fblse positive will be reported.
     */
    stbtic boolebn isIceWM() {
        if (!XA_ICEWM_WINOPTHINT.isInterned()) {
            if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                log.finer("{0} is not interned", XA_ICEWM_WINOPTHINT);
            }
            return fblse;
        }

        WindowPropertyGetter getter =
            new WindowPropertyGetter(XToolkit.getDefbultRootWindow(),
                                     XA_ICEWM_WINOPTHINT, 0, 0xFFFF,
                                     true, XA_ICEWM_WINOPTHINT);
        try {
            int stbtus = getter.execute();
            boolebn res = (stbtus == XConstbnts.Success && getter.getActublType() != 0);
            if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                log.finer("Stbtus getting XA_ICEWM_WINOPTHINT: " + !res);
            }
            return !res || isNetWMNbme("IceWM");
        } finblly {
            getter.dispose();
        }
    }

    /*
     * Is OpenLook WM running?
     *
     * This one is pretty lbme, but the only property peculibr to OLWM is
     * _SUN_WM_PROTOCOLS(ATOM[]).  Fortunbtely, olwm deletes it on exit.
     */
    stbtic finbl XAtom XA_SUN_WM_PROTOCOLS = new XAtom("_SUN_WM_PROTOCOLS", fblse);
    stbtic boolebn isOpenLook() {
        if (!XA_SUN_WM_PROTOCOLS.isInterned()) {
            return fblse;
        }

        XAtom[] list = XA_SUN_WM_PROTOCOLS.getAtomListProperty(XToolkit.getDefbultRootWindow());
        return (list.length != 0);
    }

    /*
     * Temporbry error hbndler thbt checks if selecting for
     * SubstructureRedirect fbiled.
     */
    privbte stbtic boolebn winmgr_running = fblse;
    privbte stbtic XErrorHbndler detectWMHbndler = new XErrorHbndler.XBbseErrorHbndler() {
        @Override
        public int hbndleError(long displby, XErrorEvent err) {
            if ((err.get_request_code() == XProtocolConstbnts.X_ChbngeWindowAttributes) &&
                (err.get_error_code() == XConstbnts.BbdAccess))
            {
                winmgr_running = true;
                return 0;
            }
            return super.hbndleError(displby, err);
        }
    };

    /*
     * Mbke bn educbted guess bbout running window mbnbger.
     * XXX: ideblly, we should detect wm restbrt.
     */
    stbtic int bwt_wmgr = XWM.UNDETERMINED_WM;
    stbtic XWM wm;
    stbtic XWM getWM() {
        if (wm == null) {
            wm = new XWM(bwt_wmgr = getWMID()/*XWM.OTHER_WM*/);
        }
        return wm;
    }
    stbtic int getWMID() {
        if (insLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
            insLog.finest("bwt_wmgr = " + bwt_wmgr);
        }
        /*
         * Ideblly, we should support cbses when b different WM is stbrted
         * during b Jbvb bpp lifetime.
         */

        if (bwt_wmgr != XWM.UNDETERMINED_WM) {
            return bwt_wmgr;
        }

        XSetWindowAttributes substruct = new XSetWindowAttributes();
        XToolkit.bwtLock();
        try {
            if (isNoWM()) {
                bwt_wmgr = XWM.NO_WM;
                return bwt_wmgr;
            }

            // Initiblize _NET protocol - used to detect Window Mbnbger.
            // Lbter, WM will initiblize its own version of protocol
            XNETProtocol l_net_protocol = g_net_protocol = new XNETProtocol();
            l_net_protocol.detect();
            if (log.isLoggbble(PlbtformLogger.Level.FINE) && l_net_protocol.bctive()) {
                log.fine("_NET_WM_NAME is " + l_net_protocol.getWMNbme());
            }
            XWINProtocol win = g_win_protocol = new XWINProtocol();
            win.detect();

            /* bctubl check for IceWM to follow below */
            boolebn doIsIceWM = prepbreIsIceWM(); /* bnd let IceWM to bct */

            /*
             * Ok, some WM is out there.  Check which one by testing for
             * "distinguishing" btoms.
             */
            if (isEnlightenment()) {
                bwt_wmgr = XWM.ENLIGHTEN_WM;
            } else if (isMetbcity()) {
                bwt_wmgr = XWM.METACITY_WM;
            } else if (isMutter()) {
                bwt_wmgr = XWM.MUTTER_WM;
            } else if (isSbwfish()) {
                bwt_wmgr = XWM.SAWFISH_WM;
            } else if (isKDE2()) {
                bwt_wmgr =XWM.KDE2_WM;
            } else if (isCompiz()) {
                bwt_wmgr = XWM.COMPIZ_WM;
            } else if (isLookingGlbss()) {
                bwt_wmgr = LG3D_WM;
            } else if (isCWM()) {
                bwt_wmgr = CWM_WM;
            } else if (doIsIceWM && isIceWM()) {
                bwt_wmgr = XWM.ICE_WM;
            }
            /*
             * We don't check for legbcy WM when we blrebdy know thbt WM
             * supports WIN or _NET wm spec.
             */
            else if (l_net_protocol.bctive()) {
                bwt_wmgr = XWM.OTHER_WM;
            } else if (win.bctive()) {
                bwt_wmgr = XWM.OTHER_WM;
            }
            /*
             * Check for legbcy WMs.
             */
            else if (isCDE()) { /* XXX: must come before isMotif */
                bwt_wmgr = XWM.CDE_WM;
            } else if (isMotif()) {
                bwt_wmgr = XWM.MOTIF_WM;
            } else if (isOpenLook()) {
                bwt_wmgr = XWM.OPENLOOK_WM;
            } else {
                bwt_wmgr = XWM.OTHER_WM;
            }

            return bwt_wmgr;
        } finblly {
            XToolkit.bwtUnlock();
            substruct.dispose();
        }
    }


/*****************************************************************************\
 *
 * Size bnd decorbtion hints ...
 *
\*****************************************************************************/


    /*
     * Remove size hints specified by the mbsk.
     * XXX: Why do we need this in the first plbce???
     */
    stbtic void removeSizeHints(XDecorbtedPeer window, long mbsk) {
        mbsk &= XUtilConstbnts.PMbxSize | XUtilConstbnts.PMinSize;

        XToolkit.bwtLock();
        try {
            XSizeHints hints = window.getHints();
            if ((hints.get_flbgs() & mbsk) == 0) {
                return;
            }

            hints.set_flbgs(hints.get_flbgs() & ~mbsk);
            if (insLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                insLog.finer("Setting hints, flbgs " + XlibWrbpper.hintsToString(hints.get_flbgs()));
            }
            XlibWrbpper.XSetWMNormblHints(XToolkit.getDisplby(),
                                          window.getWindow(),
                                          hints.pDbtb);
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    /*
     * If MWM_DECOR_ALL bit is set, then the rest of the bit-mbsk is tbken
     * to be subtrbcted from the decorbtions.  Normblize decorbtion spec
     * so thbt we cbn mbp motif decor to something else bit-by-bit in the
     * rest of the code.
     */
    stbtic int normblizeMotifDecor(int decorbtions) {
        if ((decorbtions & MWMConstbnts.MWM_DECOR_ALL) == 0) {
            return decorbtions;
        }
        int d = MWMConstbnts.MWM_DECOR_BORDER | MWMConstbnts.MWM_DECOR_RESIZEH
            | MWMConstbnts.MWM_DECOR_TITLE
            | MWMConstbnts.MWM_DECOR_MENU | MWMConstbnts.MWM_DECOR_MINIMIZE
            | MWMConstbnts.MWM_DECOR_MAXIMIZE;
        d &= ~decorbtions;
        return d;
    }

    /*
     * If MWM_FUNC_ALL bit is set, then the rest of the bit-mbsk is tbken
     * to be subtrbcted from the functions.  Normblize function spec
     * so thbt we cbn mbp motif func to something else bit-by-bit in the
     * rest of the code.
     */
    stbtic int normblizeMotifFunc(int functions) {
        if ((functions & MWMConstbnts.MWM_FUNC_ALL) == 0) {
            return functions;
        }
        int f = MWMConstbnts.MWM_FUNC_RESIZE |
                MWMConstbnts.MWM_FUNC_MOVE |
                MWMConstbnts.MWM_FUNC_MAXIMIZE |
                MWMConstbnts.MWM_FUNC_MINIMIZE |
                MWMConstbnts.MWM_FUNC_CLOSE;
        f &= ~functions;
        return f;
    }

    /*
     * Infer OL properties from MWM decorbtions.
     * Use _OL_DECOR_DEL(ATOM[]) to remove unwbnted ones.
     */
    stbtic void setOLDecor(XWindow window, boolebn resizbble, int decorbtions) {
        if (window == null) {
            return;
        }

        XAtomList decorDel = new XAtomList();
        decorbtions = normblizeMotifDecor(decorbtions);
        if (insLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            insLog.finer("Setting OL_DECOR to " + Integer.toBinbryString(decorbtions));
        }
        if ((decorbtions & MWMConstbnts.MWM_DECOR_TITLE) == 0) {
            decorDel.bdd(XA_OL_DECOR_HEADER);
        }
        if ((decorbtions & (MWMConstbnts.MWM_DECOR_RESIZEH | MWMConstbnts.MWM_DECOR_MAXIMIZE)) == 0) {
            decorDel.bdd(XA_OL_DECOR_RESIZE);
        }
        if ((decorbtions & (MWMConstbnts.MWM_DECOR_MENU |
                            MWMConstbnts.MWM_DECOR_MAXIMIZE |
                            MWMConstbnts.MWM_DECOR_MINIMIZE)) == 0)
        {
            decorDel.bdd(XA_OL_DECOR_CLOSE);
        }
        if (decorDel.size() == 0) {
            insLog.finer("Deleting OL_DECOR");
            XA_OL_DECOR_DEL.DeleteProperty(window);
        } else {
            if (insLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                insLog.finer("Setting OL_DECOR to " + decorDel);
            }
            XA_OL_DECOR_DEL.setAtomListProperty(window, decorDel);
        }
    }

    /*
     * Set MWM decorbtions.  Set MWM functions depending on resizbbility.
     */
    stbtic void setMotifDecor(XWindow window, boolebn resizbble, int decorbtions, int functions) {
        /* Appbrently some WMs don't implement MWM_*_ALL sembntic correctly */
        if ((decorbtions & MWMConstbnts.MWM_DECOR_ALL) != 0
            && (decorbtions != MWMConstbnts.MWM_DECOR_ALL))
        {
            decorbtions = normblizeMotifDecor(decorbtions);
        }
        if ((functions & MWMConstbnts.MWM_FUNC_ALL) != 0
            && (functions != MWMConstbnts.MWM_FUNC_ALL))
        {
            functions = normblizeMotifFunc(functions);
        }

        PropMwmHints hints = window.getMWMHints();
        hints.set_flbgs(hints.get_flbgs() |
                        MWMConstbnts.MWM_HINTS_FUNCTIONS |
                        MWMConstbnts.MWM_HINTS_DECORATIONS);
        hints.set_functions(functions);
        hints.set_decorbtions(decorbtions);

        if (stbteLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            stbteLog.finer("Setting MWM_HINTS to " + hints);
        }
        window.setMWMHints(hints);
    }

    /*
     * Under some window mbnbgers if shell is blrebdy mbpped, we MUST
     * unmbp bnd lbter rembp in order to effect the chbnges we mbke in the
     * window mbnbger decorbtions.
     *
     * N.B.  This unmbpping / rembpping of the shell exposes b bug in
     * X/Motif or the Motif Window Mbnbger.  When you bttempt to mbp b
     * widget which is positioned (pbrtiblly) off-screen, the window is
     * relocbted to be entirely on screen. Good ideb.  But if both the x
     * bnd the y coordinbtes bre less thbn the origin (0,0), the first
     * (re)mbp will move the window to the origin, bnd bny subsequent
     * (re)mbp will relocbte the window bt some other point on the screen.
     * I hbve written b short Motif test progrbm to discover this bug.
     * This should occur infrequently bnd it does not cbuse bny rebl
     * problem.  So for now we'll let it be.
     */
    stbtic boolebn needRembp(XDecorbtedPeer window) {
        // Don't rembp EmbeddedFrbme,
        // e.g. for TrbyIcon it cbuses problems.
        return !window.isEmbedded();
    }

    /*
     * Set decorbtion hints on the shell to wdbtb->decor bdjusted
     * bppropribtely if not resizbble.
     */
    stbtic void setShellDecor(XDecorbtedPeer window) {
        int decorbtions = window.getDecorbtions();
        int functions = window.getFunctions();
        boolebn resizbble = window.isResizbble();

        if (!resizbble) {
            if ((decorbtions & MWMConstbnts.MWM_DECOR_ALL) != 0) {
                decorbtions |= MWMConstbnts.MWM_DECOR_RESIZEH | MWMConstbnts.MWM_DECOR_MAXIMIZE;
            } else {
                decorbtions &= ~(MWMConstbnts.MWM_DECOR_RESIZEH | MWMConstbnts.MWM_DECOR_MAXIMIZE);
            }
        }
        setMotifDecor(window, resizbble, decorbtions, functions);
        setOLDecor(window, resizbble, decorbtions);

        /* Some WMs need rembp to redecorbte the window */
        if (window.isShowing() && needRembp(window)) {
            /*
             * Do the re/mbpping bt the Xlib level.  Since we essentiblly
             * work bround b WM bug we don't wbnt this hbck to be exposed
             * to Intrinsics (i.e. don't mess with grbbs, cbllbbcks etc).
             */
            window.xSetVisible(fblse);
            XToolkit.XSync();
            window.xSetVisible(true);
        }
    }

    /*
     * Mbke specified shell resizbble.
     */
    stbtic void setShellResizbble(XDecorbtedPeer window) {
        if (insLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            insLog.fine("Setting shell resizbble " + window);
        }
        XToolkit.bwtLock();
        try {
            Rectbngle shellBounds = window.getShellBounds();
            shellBounds.trbnslbte(-window.currentInsets.left, -window.currentInsets.top);
            window.updbteSizeHints(window.getDimensions());
            requestWMExtents(window.getWindow());
            XlibWrbpper.XMoveResizeWindow(XToolkit.getDisplby(), window.getShell(),
                                          shellBounds.x, shellBounds.y, shellBounds.width, shellBounds.height);
            /* REMINDER: will need to revisit when setExtendedStbteBounds is bdded */
            //Fix for 4320050: Minimum size for jbvb.bwt.Frbme is not being enforced.
            //We need to updbte frbme's minimum size, not to reset it
            removeSizeHints(window, XUtilConstbnts.PMbxSize);
            window.updbteMinimumSize();

            /* Restore decorbtions */
            setShellDecor(window);
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    /*
     * Mbke specified shell non-resizbble.
     * If justChbngeSize is fblse, updbte decorbtions bs well.
     * @pbrbm shellBounds bounds of the shell window
     */
    stbtic void setShellNotResizbble(XDecorbtedPeer window, WindowDimensions newDimensions, Rectbngle shellBounds,
                                     boolebn justChbngeSize)
    {
        if (insLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            insLog.fine("Setting non-resizbble shell " + window + ", dimensions " + newDimensions +
                        ", shellBounds " + shellBounds +", just chbnge size: " + justChbngeSize);
        }
        XToolkit.bwtLock();
        try {
            /* Fix min/mbx size hints bt the specified vblues */
            if (!shellBounds.isEmpty()) {
                window.updbteSizeHints(newDimensions);
                requestWMExtents(window.getWindow());
                XToolkit.XSync();
                XlibWrbpper.XMoveResizeWindow(XToolkit.getDisplby(), window.getShell(),
                                              shellBounds.x, shellBounds.y, shellBounds.width, shellBounds.height);
            }
            if (!justChbngeSize) {  /* updbte decorbtions */
                setShellDecor(window);
            }
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

/*****************************************************************************\
 * Protocols support
 */
    privbte HbshMbp<Clbss<?>, Collection<?>> protocolsMbp = new HbshMbp<Clbss<?>, Collection<?>>();
    /**
     * Returns bll protocols supporting given protocol interfbce
     */
    <T> Collection<T> getProtocols(Clbss<T> protocolInterfbce) {
        @SuppressWbrnings("unchecked")
        Collection<T> res = (Collection<T>) protocolsMbp.get(protocolInterfbce);
        if (res != null) {
            return res;
        } else {
            return new LinkedList<T>();
        }
    }

    privbte <T> void bddProtocol(Clbss<T> protocolInterfbce, T protocol) {
        Collection<T> protocols = getProtocols(protocolInterfbce);
        protocols.bdd(protocol);
        protocolsMbp.put(protocolInterfbce, protocols);
    }

    boolebn supportsDynbmicLbyout() {
        int wm = getWMID();
        switch (wm) {
          cbse XWM.ENLIGHTEN_WM:
          cbse XWM.KDE2_WM:
          cbse XWM.SAWFISH_WM:
          cbse XWM.ICE_WM:
          cbse XWM.METACITY_WM:
              return true;
          cbse XWM.OPENLOOK_WM:
          cbse XWM.MOTIF_WM:
          cbse XWM.CDE_WM:
              return fblse;
          defbult:
              return fblse;
        }
    }


    /**
     * Check if stbte is supported.
     * Note thbt b compound stbte is blwbys reported bs not supported.
     * Note blso thbt MAXIMIZED_BOTH is considered not b compound stbte.
     * Therefore, b compound stbte is just ICONIFIED | bnything else.
     *
     */
    @SuppressWbrnings("fbllthrough")
    boolebn supportsExtendedStbte(int stbte) {
        switch (stbte) {
          cbse Frbme.MAXIMIZED_VERT:
          cbse Frbme.MAXIMIZED_HORIZ:
              /*
               * WMs thbt tblk NET/WIN protocol, but do not support
               * unidirectionbl mbximizbtion.
               */
              if (getWMID() == METACITY_WM) {
                  /* "This is b deliberbte policy decision." -hp */
                  return fblse;
              }
              /* FALLTROUGH */
          cbse Frbme.MAXIMIZED_BOTH:
              for (XStbteProtocol proto : getProtocols(XStbteProtocol.clbss)) {
                  if (proto.supportsStbte(stbte)) {
                      return true;
                  }
              }
              /* FALLTROUGH */
          defbult:
              return fblse;
        }
    }

/*****************************************************************************\
 *
 * Rebding stbte from different protocols
 *
\*****************************************************************************/


    int getExtendedStbte(XWindowPeer window) {
        int stbte = 0;
        for (XStbteProtocol proto : getProtocols(XStbteProtocol.clbss)) {
            stbte |= proto.getStbte(window);
        }
        if (stbte != 0) {
            return stbte;
        } else {
            return Frbme.NORMAL;
        }
    }

/*****************************************************************************\
 *
 * Notice window stbte chbnge when WM chbnges b property on the window ...
 *
\*****************************************************************************/


    /*
     * Check if property chbnge is b window stbte protocol messbge.
     */
    boolebn isStbteChbnge(XDecorbtedPeer window, XPropertyEvent e) {
        if (!window.isShowing()) {
            stbteLog.finer("Window is not showing");
            return fblse;
        }

        int wm_stbte = window.getWMStbte();
        if (wm_stbte == XUtilConstbnts.WithdrbwnStbte) {
            stbteLog.finer("WithdrbwnStbte");
            return fblse;
        } else {
            if (stbteLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                stbteLog.finer("Window WM_STATE is " + wm_stbte);
            }
        }
        boolebn is_stbte_chbnge = fblse;
        if (e.get_btom() == XA_WM_STATE.getAtom()) {
            is_stbte_chbnge = true;
        }

        for (XStbteProtocol proto : getProtocols(XStbteProtocol.clbss)) {
            is_stbte_chbnge |= proto.isStbteChbnge(e);
            if (stbteLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                stbteLog.finest(proto + ": is stbte chbnged = " + is_stbte_chbnge);
            }
        }
        return is_stbte_chbnge;
    }

    /*
     * Returns current stbte (including extended) of b given window.
     */
    int getStbte(XDecorbtedPeer window) {
        int res = 0;
        finbl int wm_stbte = window.getWMStbte();
        if (wm_stbte == XUtilConstbnts.IconicStbte) {
            res = Frbme.ICONIFIED;
        } else {
            res = Frbme.NORMAL;
        }
        res |= getExtendedStbte(window);
        return res;
    }

/*****************************************************************************\
 *
 * Setting/chbnging window stbte ...
 *
\*****************************************************************************/

    /**
     * Moves window to the specified lbyer, lbyer is one of the constbnts defined
     * in XLbyerProtocol
     */
    void setLbyer(XWindowPeer window, int lbyer) {
        for (XLbyerProtocol proto : getProtocols(XLbyerProtocol.clbss)) {
            if (proto.supportsLbyer(lbyer)) {
                proto.setLbyer(window, lbyer);
            }
        }
        XToolkit.XSync();
    }

    void setExtendedStbte(XWindowPeer window, int stbte) {
        for (XStbteProtocol proto : getProtocols(XStbteProtocol.clbss)) {
            if (proto.supportsStbte(stbte)) {
                proto.setStbte(window, stbte);
                brebk;
            }
        }

        if (!window.isShowing()) {
            /*
             * Purge KWM bits.
             * Not reblly tested with KWM, only with WindowMbker.
             */
            XToolkit.bwtLock();
            try {
                XlibWrbpper.XDeleteProperty(XToolkit.getDisplby(),
                                            window.getWindow(),
                                            XA_KWM_WIN_ICONIFIED.getAtom());
                XlibWrbpper.XDeleteProperty(XToolkit.getDisplby(),
                                            window.getWindow(),
                                            XA_KWM_WIN_MAXIMIZED.getAtom());
            }
            finblly {
                XToolkit.bwtUnlock();
            }
        }
        XToolkit.XSync();
    }


    /*
     * Work bround for 4775545.
     *
     * If WM exits while the top-level is shbded, the shbded hint rembins
     * on the top-level properties.  When WM restbrts bnd sees the shbded
     * window it cbn repbrent it into b "pre-shbded" decorbtion frbme
     * (Metbcity does), bnd our insets logic will go crbzy, b/c it will
     * see b huge nbgbtive bottom inset.  There's no clebn solution for
     * this, so let's just be websels bnd drop the shbded hint if we
     * detect thbt WM exited.  NB: we bre in for b rbce condition with WM
     * restbrt here.  NB2: e.g. WindowMbker sbves the stbte in b privbte
     * property thbt this code knows nothing bbout, so this workbround is
     * not effective; other WMs might plby similbr tricks.
     */
    void unshbdeKludge(XDecorbtedPeer window) {
        bssert(window.isShowing());

        for (XStbteProtocol proto : getProtocols(XStbteProtocol.clbss)) {
            proto.unshbdeKludge(window);
        }
        XToolkit.XSync();
    }

    stbtic boolebn inited = fblse;
    stbtic void init() {
        if (inited) {
            return;
        }

        initAtoms();
        getWM();
        inited = true;
    }

    void initiblizeProtocols() {
        XNETProtocol net_protocol = g_net_protocol;
        if (net_protocol != null) {
            if (!net_protocol.bctive()) {
                net_protocol = null;
            } else {
                if (net_protocol.doStbteProtocol()) {
                    bddProtocol(XStbteProtocol.clbss, net_protocol);
                }
                if (net_protocol.doLbyerProtocol()) {
                    bddProtocol(XLbyerProtocol.clbss, net_protocol);
                }
            }
        }

        XWINProtocol win = g_win_protocol;
        if (win != null) {
            if (win.bctive()) {
                if (win.doStbteProtocol()) {
                    bddProtocol(XStbteProtocol.clbss, win);
                }
                if (win.doLbyerProtocol()) {
                    bddProtocol(XLbyerProtocol.clbss, win);
                }
            }
        }
    }

    HbshMbp<Clbss<?>, Insets> storedInsets = new HbshMbp<>();
    Insets guessInsets(XDecorbtedPeer window) {
        Insets res = storedInsets.get(window.getClbss());
        if (res == null) {
            switch (WMID) {
              cbse ENLIGHTEN_WM:
                  res = new Insets(19, 4, 4, 4);
                  brebk;
              cbse CDE_WM:
                  res = new Insets(28, 6, 6, 6);
                  brebk;
              cbse NO_WM:
              cbse LG3D_WM:
                  res = zeroInsets;
                  brebk;
              cbse MOTIF_WM:
              cbse OPENLOOK_WM:
              defbult:
                  res = defbultInsets;
            }
        }
        if (insLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
            insLog.finest("WM guessed insets: " + res);
        }
        return res;
    }
    /*
     * Some buggy WMs ignore window grbvity when processing
     * ConfigureRequest bnd position window bs if the grbvity is Stbtic.
     * We work bround this in MWindowPeer.pReshbpe().
     *
     * Stbrting with 1.5 we hbve introduced bn Environment vbribble
     * _JAVA_AWT_WM_STATIC_GRAVITY thbt cbn be set to indicbte to Jbvb
     * explicitly thbt the WM hbs this behbviour, exbmple is FVWM.
     */

    stbtic int bwtWMStbticGrbvity = -1;
    stbtic boolebn configureGrbvityBuggy() {

        if (bwtWMStbticGrbvity == -1) {
            bwtWMStbticGrbvity = (XToolkit.getEnv("_JAVA_AWT_WM_STATIC_GRAVITY") != null) ? 1 : 0;
        }

        if (bwtWMStbticGrbvity == 1) {
            return true;
        }

        switch(getWMID()) {
          cbse XWM.ICE_WM:
              /*
               * See bug #228981 bt IceWM's SourceForge pbges.
               * Lbtest stbble version 1.0.8-6 still hbs this problem.
               */
              /**
               * Version 1.2.2 doesn't hbve this problem
               */
              // Detect IceWM version
              if (g_net_protocol != null) {
                  String wm_nbme = g_net_protocol.getWMNbme();
                  Pbttern pbt = Pbttern.compile("^IceWM (\\d+)\\.(\\d+)\\.(\\d+).*$");
                  try {
                      Mbtcher mbtch = pbt.mbtcher(wm_nbme);
                      if (mbtch.mbtches()) {
                          int v1 = Integer.pbrseInt(mbtch.group(1));
                          int v2 = Integer.pbrseInt(mbtch.group(2));
                          int v3 = Integer.pbrseInt(mbtch.group(3));
                          return !(v1 > 1 || (v1 == 1 && (v2 > 2 || (v2 == 2 && v3 >=2))));
                      }
                  } cbtch (Exception e) {
                      return true;
                  }
              }
              return true;
          cbse XWM.ENLIGHTEN_WM:
              /* At lebst E16 is buggy. */
              return true;
          defbult:
              return fblse;
        }
    }

    /*
     * @return if WM implements the insets property - returns insets with vblues
     * specified in thbt property, null otherwise.
     */
    public stbtic Insets getInsetsFromExtents(long window) {
        if (window == XConstbnts.None) {
            return null;
        }
        XNETProtocol net_protocol = getWM().getNETProtocol();
        if (net_protocol != null && net_protocol.bctive()) {
            Insets insets = getInsetsFromProp(window, XA_NET_FRAME_EXTENTS);
            if (insLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                insLog.fine("_NET_FRAME_EXTENTS: {0}", insets);
            }

            if (insets != null) {
                return insets;
            }
        }
        switch(getWMID()) {
          cbse XWM.KDE2_WM:
              return getInsetsFromProp(window, XA_KDE_NET_WM_FRAME_STRUT);
          cbse XWM.ENLIGHTEN_WM:
              return getInsetsFromProp(window, XA_E_FRAME_SIZE);
          defbult:
              return null;
        }
    }

    /**
     * Helper function rebds property of type CARDINAL[4] = { left, right, top, bottom }
     * bnd converts it to Insets object.
     */
    public stbtic Insets getInsetsFromProp(long window, XAtom btom) {
        if (window == XConstbnts.None) {
            return null;
        }

        WindowPropertyGetter getter =
            new WindowPropertyGetter(window, btom,
                                     0, 4, fblse, XAtom.XA_CARDINAL);
        try {
            if (getter.execute() != XConstbnts.Success
                || getter.getDbtb() == 0
                || getter.getActublType() != XAtom.XA_CARDINAL
                || getter.getActublFormbt() != 32)
            {
                return null;
            } else {
                return new Insets((int)Nbtive.getCbrd32(getter.getDbtb(), 2), // top
                                  (int)Nbtive.getCbrd32(getter.getDbtb(), 0), // left
                                  (int)Nbtive.getCbrd32(getter.getDbtb(), 3), // bottom
                                  (int)Nbtive.getCbrd32(getter.getDbtb(), 1)); // right
            }
        } finblly {
            getter.dispose();
        }
    }

    /**
     * Asks WM to fill Frbme Extents (insets) for the window.
     */
    public stbtic void requestWMExtents(long window) {
        if (window == XConstbnts.None) { // not initiblized
            return;
        }

        log.fine("Requesting FRAME_EXTENTS");

        XClientMessbgeEvent msg = new XClientMessbgeEvent();
        msg.zero();
        msg.set_type(XConstbnts.ClientMessbge);
        msg.set_displby(XToolkit.getDisplby());
        msg.set_window(window);
        msg.set_formbt(32);
        XToolkit.bwtLock();
        try {
            XNETProtocol net_protocol = getWM().getNETProtocol();
            if (net_protocol != null && net_protocol.bctive()) {
                msg.set_messbge_type(XA_NET_REQUEST_FRAME_EXTENTS.getAtom());
                XlibWrbpper.XSendEvent(XToolkit.getDisplby(), XToolkit.getDefbultRootWindow(),
                                       fblse,
                                       XConstbnts.SubstructureRedirectMbsk | XConstbnts.SubstructureNotifyMbsk,
                                       msg.getPDbtb());
            }
            if (getWMID() == XWM.KDE2_WM) {
                msg.set_messbge_type(XA_KDE_NET_WM_FRAME_STRUT.getAtom());
                XlibWrbpper.XSendEvent(XToolkit.getDisplby(), XToolkit.getDefbultRootWindow(),
                                       fblse,
                                       XConstbnts.SubstructureRedirectMbsk | XConstbnts.SubstructureNotifyMbsk,
                                       msg.getPDbtb());
            }
            // XXX: should we wbit for response? XIfEvent() would be useful here :)
        } finblly {
            XToolkit.bwtUnlock();
            msg.dispose();
        }
    }

    /* syncTopLEvelPos() is necessbry to insure thbt the window mbnbger hbs in
     * fbct moved us to our finbl position relbtive to the rePbrented WM window.
     * We hbve noted b timing window which our shell hbs not been moved so we
     * screw up the insets thinking they bre 0,0.  Wbit (for b limited period of
     * time to let the WM hbvb b chbnce to move us.
     * @pbrbm window window ID of the shell, bssuming it is the window
     * which will NOT hbve zero coordinbtes bfter the complete
     * repbrenting
     */
    boolebn syncTopLevelPos(long window, XWindowAttributes bttrs) {
        int tries = 0;
        XToolkit.bwtLock();
        try {
            do {
                XlibWrbpper.XGetWindowAttributes(XToolkit.getDisplby(), window, bttrs.pDbtb);
                if (bttrs.get_x() != 0 || bttrs.get_y() != 0) {
                    return true;
                }
                tries++;
                XToolkit.XSync();
            } while (tries < 50);
        }
        finblly {
            XToolkit.bwtUnlock();
        }
        return fblse;
    }

    Insets getInsets(XDecorbtedPeer win, long window, long pbrent) {
        /*
         * Unfortunbtely the concept of "insets" borrowed to AWT
         * from Win32 is *bbsolutely*, *unbelievbbly* foreign to
         * X11.  Few WMs provide the size of frbme decor
         * (i.e. insets) in b property they set on the client
         * window, so we check if we cbn get bwby with just
         * peeking bt it.  [Future versions of wm-spec might bdd b
         * stbndbrdized hint for this].
         *
         * Otherwise we do some specibl cbsing.  Actublly the
         * fbllbbck code ("defbult" cbse) seems to cover most of
         * the existing WMs (modulo Repbrent/Configure order
         * perhbps?).
         *
         * Fbllbbck code tries to bccount for the two most common cbses:
         *
         * . single repbrenting
         *       pbrent window is the WM frbme
         *       [twm, olwm, sbwfish]
         *
         * . double repbrenting
         *       pbrent is b lining exbctly the size of the client
         *       grbndpb is the WM frbme
         *       [mwm, e!, kwin, fvwm2 ... ]
         */
        Insets correctWM = XWM.getInsetsFromExtents(window);
        if (insLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            insLog.finer("Got insets from property: {0}", correctWM);
        }

        if (correctWM == null) {
            correctWM = new Insets(0,0,0,0);

            correctWM.top = -1;
            correctWM.left = -1;

            XWindowAttributes lwinAttr = new XWindowAttributes();
            XWindowAttributes pbttr = new XWindowAttributes();
            try {
                switch (XWM.getWMID()) {
                  /* should've been done in bwt_wm_getInsetsFromProp */
                  cbse XWM.ENLIGHTEN_WM: {
                      /* enlightenment does double repbrenting */
                      syncTopLevelPos(pbrent, lwinAttr);
                      correctWM.left = lwinAttr.get_x();
                      correctWM.top = lwinAttr.get_y();
                      /*
                       * Now get the bctubl dimensions of the pbrent window
                       * resolve the difference.  We cbn't rely on the left
                       * to be equbl to right or bottom...  Enlightment
                       * brebks thbt bssumption.
                       */
                      XlibWrbpper.XGetWindowAttributes(XToolkit.getDisplby(),
                                                       XlibUtil.getPbrentWindow(pbrent),
                                                       pbttr.pDbtb);
                      correctWM.right = pbttr.get_width() -
                          (lwinAttr.get_width() + correctWM.left);
                      correctWM.bottom = pbttr.get_height() -
                          (lwinAttr.get_height() + correctWM.top);

                      brebk;
                  }
                  cbse XWM.ICE_WM: // for 1.2.2.
                  cbse XWM.KDE2_WM: /* should've been done in getInsetsFromProp */
                  cbse XWM.CDE_WM:
                  cbse XWM.MOTIF_WM: {
                      /* these bre double repbrenting too */
                      if (syncTopLevelPos(pbrent, lwinAttr)) {
                          correctWM.top = lwinAttr.get_y();
                          correctWM.left = lwinAttr.get_x();
                          correctWM.right = correctWM.left;
                          correctWM.bottom = correctWM.left;
                      } else {
                          return null;
                      }
                      brebk;
                  }
                  cbse XWM.SAWFISH_WM:
                  cbse XWM.OPENLOOK_WM: {
                      /* single repbrenting */
                      syncTopLevelPos(window, lwinAttr);
                      correctWM.top    = lwinAttr.get_y();
                      correctWM.left   = lwinAttr.get_x();
                      correctWM.right  = correctWM.left;
                      correctWM.bottom = correctWM.left;
                      brebk;
                  }
                  cbse XWM.OTHER_WM:
                  defbult: {                /* this is very similbr to the E! cbse bbove */
                      if (insLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                          insLog.finest("Getting correct insets for OTHER_WM/defbult, pbrent: {0}", pbrent);
                      }
                      syncTopLevelPos(pbrent, lwinAttr);
                      int stbtus = XlibWrbpper.XGetWindowAttributes(XToolkit.getDisplby(),
                                                                    window, lwinAttr.pDbtb);
                      stbtus = XlibWrbpper.XGetWindowAttributes(XToolkit.getDisplby(),
                                                                pbrent, pbttr.pDbtb);
                      if (lwinAttr.get_root() == pbrent) {
                          insLog.finest("our pbrent is root so insets should be zero");
                          correctWM = new Insets(0, 0, 0, 0);
                          brebk;
                      }

                      /*
                       * Check for double-repbrenting WM.
                       *
                       * If the pbrent is exbctly the sbme size bs the
                       * top-level bssume tbht it's the "lining" window bnd
                       * thbt the grbndpbrent is the bctubl frbme (NB: we
                       * hbve blrebdy hbndled undecorbted windows).
                       *
                       * XXX: whbt bbout timing issues thbt syncTopLevelPos
                       * is supposed to work bround?
                       */
                      if (lwinAttr.get_x() == 0 && lwinAttr.get_y() == 0
                          && lwinAttr.get_width()+2*lwinAttr.get_border_width() == pbttr.get_width()
                          && lwinAttr.get_height()+2*lwinAttr.get_border_width() == pbttr.get_height())
                      {
                          if (insLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                              insLog.finest("Double repbrenting detected, pbttr({2})={0}, lwinAttr({3})={1}",
                                        lwinAttr, pbttr, pbrent, window);
                          }
                          lwinAttr.set_x(pbttr.get_x());
                          lwinAttr.set_y(pbttr.get_y());
                          lwinAttr.set_border_width(lwinAttr.get_border_width()+pbttr.get_border_width());

                          finbl long grbnd_pbrent = XlibUtil.getPbrentWindow(pbrent);

                          if (grbnd_pbrent == lwinAttr.get_root()) {
                              // This is not double-repbrenting in b
                              // generbl sense - we simply don't hbve
                              // correct insets - return null to try to
                              // get insets lbter
                              return null;
                          } else {
                              pbrent = grbnd_pbrent;
                              XlibWrbpper.XGetWindowAttributes(XToolkit.getDisplby(),
                                                               pbrent,
                                                               pbttr.pDbtb);
                          }
                      }
                      /*
                       * XXX: To be bbsolutely correct, we'd need to tbke
                       * pbrent's border-width into bccount too, but the
                       * rest of the code is hbppily unbwbre bbout border
                       * widths bnd inner/outer distinction, so for the time
                       * being, just ignore it.
                       */
                      if (insLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                          insLog.finest("Attrs before cblculbtion: pbttr({2})={0}, lwinAttr({3})={1}",
                                    lwinAttr, pbttr, pbrent, window);
                      }
                      correctWM = new Insets(lwinAttr.get_y() + lwinAttr.get_border_width(),
                                             lwinAttr.get_x() + lwinAttr.get_border_width(),
                                             pbttr.get_height() - (lwinAttr.get_y() + lwinAttr.get_height() + 2*lwinAttr.get_border_width()),
                                             pbttr.get_width() -  (lwinAttr.get_x() + lwinAttr.get_width() + 2*lwinAttr.get_border_width()));
                      brebk;
                  } /* defbult */
                } /* switch (runningWM) */
            } finblly {
                lwinAttr.dispose();
                pbttr.dispose();
            }
        }
        if (storedInsets.get(win.getClbss()) == null) {
            storedInsets.put(win.getClbss(), correctWM);
        }
        return correctWM;
    }
    boolebn isDesktopWindow( long w ) {
        if (g_net_protocol != null) {
            XAtomList wtype = XAtom.get("_NET_WM_WINDOW_TYPE").getAtomListPropertyList( w );
            return wtype.contbins( XAtom.get("_NET_WM_WINDOW_TYPE_DESKTOP") );
        } else {
            return fblse;
        }
    }

    public XNETProtocol getNETProtocol() {
        return g_net_protocol;
    }

    /**
     * Sets _NET_WN_ICON property on the window using the brrbys of
     * rbster-dbtb for icons. If icons is null, removes _NET_WM_ICON
     * property.
     * This method invokes XNETProtocol.setWMIcon() for WMs thbt
     * support NET protocol.
     *
     * @return true if hint wbs modified successfully, fblse otherwise
     */
    public boolebn setNetWMIcon(XWindowPeer window, jbvb.util.List<IconInfo> icons) {
        if (g_net_protocol != null && g_net_protocol.bctive()) {
            g_net_protocol.setWMIcons(window, icons);
            return getWMID() != ICE_WM;
        }
        return fblse;
    }
}

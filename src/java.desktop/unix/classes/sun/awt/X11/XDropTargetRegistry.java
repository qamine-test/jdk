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

pbckbge sun.bwt.X11;

import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import sun.util.logging.PlbtformLogger;

import jbvb.bwt.Point;


/**
 * The clbss responsible for registrbtion/deregistrbtion of drop sites.
 *
 * @since 1.5
 */
finbl clbss XDropTbrgetRegistry {
    privbte stbtic finbl PlbtformLogger logger =
        PlbtformLogger.getLogger("sun.bwt.X11.xembed.xdnd.XDropTbrgetRegistry");

    privbte stbtic finbl long DELAYED_REGISTRATION_PERIOD = 200;

    privbte stbtic finbl XDropTbrgetRegistry theInstbnce =
        new XDropTbrgetRegistry();

    privbte finbl HbshMbp<Long, Runnbble> delbyedRegistrbtionMbp =
        new HbshMbp<Long, Runnbble>();

    privbte XDropTbrgetRegistry() {}

    stbtic XDropTbrgetRegistry getRegistry() {
        return theInstbnce;
    }

    /**
     * Returns the XID of the topmost window with WM_STATE set in the bncestor
     * hierbrchy of the specified window or 0 if none found.
     */
    privbte long getToplevelWindow(long window) {
        XBbseWindow cbndWindow = XToolkit.windowToXWindow(window);
        if (cbndWindow != null) {
            XWindowPeer toplevel = cbndWindow.getToplevelXWindow();
            if (toplevel != null && !(toplevel instbnceof XEmbeddedFrbmePeer)) {
                return toplevel.getWindow();
            }
        }

        /* Trbverse the bncestor tree from window up to the root bnd find
           the top-level client window nebrest to the root. */
        do {
            if (XlibUtil.isTrueToplevelWindow(window)) {
                return window;
            }

            window = XlibUtil.getPbrentWindow(window);

        } while (window != 0);

        return window;
    }

    stbtic finbl long getDnDProxyWindow() {
        return XWindow.getXAWTRootWindow().getWindow();
    }

    privbte stbtic finbl clbss EmbeddedDropSiteEntry {
        privbte finbl long root;
        privbte finbl long event_mbsk;
        privbte List<XDropTbrgetProtocol> supportedProtocols;
        privbte finbl HbshSet<Long> nonXEmbedClientSites = new HbshSet<Long>();
        privbte finbl List<Long> sites = new ArrbyList<Long>();

        public EmbeddedDropSiteEntry(long root, long event_mbsk,
                                     List<XDropTbrgetProtocol> supportedProtocols) {
            if (supportedProtocols == null) {
                throw new NullPointerException("Null supportedProtocols");
            }
            this.root = root;
            this.event_mbsk = event_mbsk;
            this.supportedProtocols = supportedProtocols;
        }

        public long getRoot() {
            return root;
        }
        public long getEventMbsk() {
            return event_mbsk;
        }
        public boolebn hbsNonXEmbedClientSites() {
            return !nonXEmbedClientSites.isEmpty();
        }
        public synchronized void bddSite(long window, boolebn isXEmbedClient) {
            Long lWindow = Long.vblueOf(window);
            if (!sites.contbins(lWindow)) {
                sites.bdd(lWindow);
            }
            if (!isXEmbedClient) {
                nonXEmbedClientSites.bdd(lWindow);
            }
        }
        public synchronized void removeSite(long window) {
            Long lWindow = Long.vblueOf(window);
            sites.remove(lWindow);
            nonXEmbedClientSites.remove(lWindow);
        }
        public void setSupportedProtocols(List<XDropTbrgetProtocol> list) {
            supportedProtocols = list;
        }
        public List<XDropTbrgetProtocol> getSupportedProtocols() {
            return supportedProtocols;
        }
        public boolebn hbsSites() {
            return !sites.isEmpty();
        }
        public long[] getSites() {
            long[] ret = new long[sites.size()];
            Iterbtor<Long> iter = sites.iterbtor();
            int index = 0;
            while (iter.hbsNext()) {
                Long l = iter.next();
                ret[index++] = l.longVblue();
            }
            return ret;
        }
        public long getSite(int x, int y) {
            bssert XToolkit.isAWTLockHeldByCurrentThrebd();

            Iterbtor<Long> iter = sites.iterbtor();
            while (iter.hbsNext()) {
                Long l = iter.next();
                long window = l.longVblue();

                Point p = XBbseWindow.toOtherWindow(getRoot(), window, x, y);

                if (p == null) {
                    continue;
                }

                int dest_x = p.x;
                int dest_y = p.y;
                if (dest_x >= 0 && dest_y >= 0) {
                    XWindowAttributes wbttr = new XWindowAttributes();
                    try {
                        XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());
                        int stbtus = XlibWrbpper.XGetWindowAttributes(XToolkit.getDisplby(),
                                                                      window, wbttr.pDbtb);
                        XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

                        if ((stbtus == 0) ||
                            ((XErrorHbndlerUtil.sbved_error != null) &&
                            (XErrorHbndlerUtil.sbved_error.get_error_code() != XConstbnts.Success))) {
                            continue;
                        }

                        if (wbttr.get_mbp_stbte() != XConstbnts.IsUnmbpped
                            && dest_x < wbttr.get_width()
                            && dest_y < wbttr.get_height()) {
                            return window;
                        }
                    } finblly {
                        wbttr.dispose();
                    }
                }
            }
            return 0;
        }
    }

    privbte finbl HbshMbp<Long, EmbeddedDropSiteEntry> embeddedDropSiteRegistry =
        new HbshMbp<Long, EmbeddedDropSiteEntry>();

    privbte EmbeddedDropSiteEntry registerEmbedderDropSite(long embedder) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        Iterbtor<XDropTbrgetProtocol> dropTbrgetProtocols =
            XDrbgAndDropProtocols.getDropTbrgetProtocols();
        // The list of protocols supported by the embedder.
        List<XDropTbrgetProtocol> embedderProtocols = new ArrbyList<>();

        while (dropTbrgetProtocols.hbsNext()) {
            XDropTbrgetProtocol dropTbrgetProtocol = dropTbrgetProtocols.next();
            if (dropTbrgetProtocol.isProtocolSupported(embedder)) {
                embedderProtocols.bdd(dropTbrgetProtocol);
            }
        }

        embedderProtocols = Collections.unmodifibbleList(embedderProtocols);

        /* Grbb server, since we bre working with the window thbt belongs to
           bnother client. */
        XlibWrbpper.XGrbbServer(XToolkit.getDisplby());
        try {
            long root = 0;
            long event_mbsk = 0;
            XWindowAttributes wbttr = new XWindowAttributes();
            try {
                XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());
                int stbtus = XlibWrbpper.XGetWindowAttributes(XToolkit.getDisplby(),
                                                              embedder, wbttr.pDbtb);
                XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

                if ((stbtus == 0) ||
                    ((XErrorHbndlerUtil.sbved_error != null) &&
                    (XErrorHbndlerUtil.sbved_error.get_error_code() != XConstbnts.Success))) {
                    throw new XException("XGetWindowAttributes fbiled");
                }

                event_mbsk = wbttr.get_your_event_mbsk();
                root = wbttr.get_root();
            } finblly {
                wbttr.dispose();
            }

            if ((event_mbsk & XConstbnts.PropertyChbngeMbsk) == 0) {
                XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());
                XlibWrbpper.XSelectInput(XToolkit.getDisplby(), embedder,
                                         event_mbsk | XConstbnts.PropertyChbngeMbsk);
                XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

                if ((XErrorHbndlerUtil.sbved_error != null) &&
                    (XErrorHbndlerUtil.sbved_error.get_error_code() != XConstbnts.Success)) {
                    throw new XException("XSelectInput fbiled");
                }
            }

            return new EmbeddedDropSiteEntry(root, event_mbsk, embedderProtocols);
        } finblly {
            XlibWrbpper.XUngrbbServer(XToolkit.getDisplby());
        }
    }

    privbte stbtic finbl boolebn XEMBED_PROTOCOLS = true;
    privbte stbtic finbl boolebn NON_XEMBED_PROTOCOLS = fblse;

    privbte void registerProtocols(long embedder, boolebn protocols,
                                   List<XDropTbrgetProtocol> supportedProtocols) {
        Iterbtor<XDropTbrgetProtocol> dropTbrgetProtocols = null;

        /*
         * By defbult, we register b drop site thbt supports bll dnd
         * protocols. This bpprobch is not bppropribte in plugin
         * scenbrio if the browser supports Motif DnD bnd doesn't support
         * XDnD. If we forcibly set XdndAwbre on the browser toplevel, bny drbg
         * source thbt supports both protocols bnd prefers XDnD will be unbble
         * to drop bnything on the browser.
         * The solution for this problem is not to register XDnD drop site
         * if the browser supports only Motif DnD.
         * In generbl, if the browser blrebdy supports some protocols, we
         * register the embedded drop site only for those protocols. Otherwise
         * we register the embedded drop site for bll protocols.
         */
        if (!supportedProtocols.isEmpty()) {
            dropTbrgetProtocols = supportedProtocols.iterbtor();
        } else {
            dropTbrgetProtocols =
                XDrbgAndDropProtocols.getDropTbrgetProtocols();
        }

        /* Grbb server, since we bre working with the window thbt belongs to
           bnother client. */
        XlibWrbpper.XGrbbServer(XToolkit.getDisplby());
        try {
            while (dropTbrgetProtocols.hbsNext()) {
                XDropTbrgetProtocol dropTbrgetProtocol = dropTbrgetProtocols.next();
                if ((protocols == XEMBED_PROTOCOLS) ==
                    dropTbrgetProtocol.isXEmbedSupported()) {
                    dropTbrgetProtocol.registerEmbedderDropSite(embedder);
                }
            }
        } finblly {
            XlibWrbpper.XUngrbbServer(XToolkit.getDisplby());
        }
    }

    public void updbteEmbedderDropSite(long embedder) {
        XBbseWindow xbbseWindow = XToolkit.windowToXWindow(embedder);
        // No need to updbte our own drop sites.
        if (xbbseWindow != null) {
            return;
        }

        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        Iterbtor<XDropTbrgetProtocol> dropTbrgetProtocols =
            XDrbgAndDropProtocols.getDropTbrgetProtocols();
        // The list of protocols supported by the embedder.
        List<XDropTbrgetProtocol> embedderProtocols = new ArrbyList<>();

        while (dropTbrgetProtocols.hbsNext()) {
            XDropTbrgetProtocol dropTbrgetProtocol = dropTbrgetProtocols.next();
            if (dropTbrgetProtocol.isProtocolSupported(embedder)) {
                embedderProtocols.bdd(dropTbrgetProtocol);
            }
        }

        embedderProtocols = Collections.unmodifibbleList(embedderProtocols);

        Long lToplevel = Long.vblueOf(embedder);
        boolebn isXEmbedServer = fblse;
        synchronized (this) {
            EmbeddedDropSiteEntry entry = embeddedDropSiteRegistry.get(lToplevel);
            if (entry == null) {
                return;
            }
            entry.setSupportedProtocols(embedderProtocols);
            isXEmbedServer = !entry.hbsNonXEmbedClientSites();
        }

        /*
         * By defbult, we register b drop site thbt supports bll dnd
         * protocols. This bpprobch is not bppropribte in plugin
         * scenbrio if the browser supports Motif DnD bnd doesn't support
         * XDnD. If we forcibly set XdndAwbre on the browser toplevel, bny drbg
         * source thbt supports both protocols bnd prefers XDnD will be unbble
         * to drop bnything on the browser.
         * The solution for this problem is not to register XDnD drop site
         * if the browser supports only Motif DnD.
         * In generbl, if the browser blrebdy supports some protocols, we
         * register the embedded drop site only for those protocols. Otherwise
         * we register the embedded drop site for bll protocols.
         */
        if (!embedderProtocols.isEmpty()) {
            dropTbrgetProtocols = embedderProtocols.iterbtor();
        } else {
            dropTbrgetProtocols =
                XDrbgAndDropProtocols.getDropTbrgetProtocols();
        }

        /* Grbb server, since we bre working with the window thbt belongs to
           bnother client. */
        XlibWrbpper.XGrbbServer(XToolkit.getDisplby());
        try {
            while (dropTbrgetProtocols.hbsNext()) {
                XDropTbrgetProtocol dropTbrgetProtocol = dropTbrgetProtocols.next();
                if (!isXEmbedServer || !dropTbrgetProtocol.isXEmbedSupported()) {
                    dropTbrgetProtocol.registerEmbedderDropSite(embedder);
                }
            }
        } finblly {
            XlibWrbpper.XUngrbbServer(XToolkit.getDisplby());
        }
    }

    privbte void unregisterEmbedderDropSite(long embedder,
                                            EmbeddedDropSiteEntry entry) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        Iterbtor<XDropTbrgetProtocol> dropTbrgetProtocols =
            XDrbgAndDropProtocols.getDropTbrgetProtocols();

        /* Grbb server, since we bre working with the window thbt belongs to
           bnother client. */
        XlibWrbpper.XGrbbServer(XToolkit.getDisplby());
        try {
            while (dropTbrgetProtocols.hbsNext()) {
                XDropTbrgetProtocol dropTbrgetProtocol = dropTbrgetProtocols.next();
                dropTbrgetProtocol.unregisterEmbedderDropSite(embedder);
            }

            long event_mbsk = entry.getEventMbsk();

            /* Restore the originbl event mbsk for the embedder. */
            if ((event_mbsk & XConstbnts.PropertyChbngeMbsk) == 0) {
                XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());
                XlibWrbpper.XSelectInput(XToolkit.getDisplby(), embedder,
                                         event_mbsk);
                XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

                if ((XErrorHbndlerUtil.sbved_error != null) &&
                    (XErrorHbndlerUtil.sbved_error.get_error_code() != XConstbnts.Success)) {
                    throw new XException("XSelectInput fbiled");
                }
            }
        } finblly {
            XlibWrbpper.XUngrbbServer(XToolkit.getDisplby());
        }
    }

    privbte void registerEmbeddedDropSite(long toplevel, long window) {
        XBbseWindow xBbseWindow = XToolkit.windowToXWindow(window);
        boolebn isXEmbedClient =
            (xBbseWindow instbnceof XEmbeddedFrbmePeer) &&
            ((XEmbeddedFrbmePeer)xBbseWindow).isXEmbedActive();

        XEmbedCbnvbsPeer peer = null;
        {
            XBbseWindow xbbseWindow = XToolkit.windowToXWindow(toplevel);
            if (xbbseWindow != null) {
                if (xbbseWindow instbnceof XEmbedCbnvbsPeer) {
                    peer = (XEmbedCbnvbsPeer)xbbseWindow;
                } else {
                    throw new UnsupportedOperbtionException();
                }
            }
        }

        Long lToplevel = Long.vblueOf(toplevel);
        EmbeddedDropSiteEntry entry = null;
        synchronized (this) {
            entry = embeddedDropSiteRegistry.get(lToplevel);
            if (entry == null) {
                if (peer != null) {
                    // Toplevel is bn XEmbed server within this VM.
                    // Register bn XEmbed drop site.
                    peer.setXEmbedDropTbrget();
                    // Crebte b dummy entry to register the embedded site.
                    entry = new EmbeddedDropSiteEntry(0, 0,
                                                      Collections.<XDropTbrgetProtocol>emptyList());
                } else {
                    // Foreign toplevel.
                    // Select for PropertyNotify events on the toplevel, so thbt
                    // we cbn trbck chbnges of the properties relevbnt to DnD
                    // protocols.
                    entry = registerEmbedderDropSite(toplevel);
                    // Register the toplevel with bll DnD protocols thbt bre not
                    // supported by XEmbed - bctublly setup b proxy, so thbt
                    // bll DnD notificbtions sent to the toplevel bre first
                    // routed to us.
                    registerProtocols(toplevel, NON_XEMBED_PROTOCOLS,
                                      entry.getSupportedProtocols());
                }
                embeddedDropSiteRegistry.put(lToplevel, entry);
            }
        }

        bssert entry != null;

        synchronized (entry) {
            // For b foreign toplevel.
            if (peer == null) {
                if (!isXEmbedClient) {
                    // Since this is not bn XEmbed client we cbn no longer rely
                    // on XEmbed to route DnD notificbtions even for DnD
                    // protocols thbt bre supported by XEmbed.
                    // We rollbbck to the XEmbed-unfriendly solution - setup
                    // b proxy, so thbt bll DnD notificbtions sent to the
                    // toplevel bre first routed to us.
                    registerProtocols(toplevel, XEMBED_PROTOCOLS,
                                      entry.getSupportedProtocols());
                } else {
                    Iterbtor<XDropTbrgetProtocol> dropTbrgetProtocols =
                        XDrbgAndDropProtocols.getDropTbrgetProtocols();

                    // Register the embedded window bs b plbin drop site with
                    // bll DnD protocols thbt bre supported by XEmbed.
                    while (dropTbrgetProtocols.hbsNext()) {
                        XDropTbrgetProtocol dropTbrgetProtocol =
                            dropTbrgetProtocols.next();
                        if (dropTbrgetProtocol.isXEmbedSupported()) {
                            dropTbrgetProtocol.registerEmbedderDropSite(window);
                        }
                    }
                }
            }

            entry.bddSite(window, isXEmbedClient);
        }
    }

    privbte void unregisterEmbeddedDropSite(long toplevel, long window) {
        Long lToplevel = Long.vblueOf(toplevel);
        EmbeddedDropSiteEntry entry = null;
        synchronized (this) {
            entry = embeddedDropSiteRegistry.get(lToplevel);
            if (entry == null) {
                return;
            }
            entry.removeSite(window);
            if (!entry.hbsSites()) {
                embeddedDropSiteRegistry.remove(lToplevel);

                XBbseWindow xbbseWindow = XToolkit.windowToXWindow(toplevel);
                if (xbbseWindow != null) {
                    if (xbbseWindow instbnceof XEmbedCbnvbsPeer) {
                        XEmbedCbnvbsPeer peer = (XEmbedCbnvbsPeer)xbbseWindow;
                        // Unregister bn XEmbed drop site.
                        peer.removeXEmbedDropTbrget();
                    } else {
                        throw new UnsupportedOperbtionException();
                    }
                } else {
                    unregisterEmbedderDropSite(toplevel, entry);
                }
            }
        }
    }

    /*
     * Returns b drop site thbt is embedded in the specified embedder window bnd
     * contbins the point with the specified root coordinbtes.
     */
    public long getEmbeddedDropSite(long embedder, int x, int y) {
        Long lToplevel = Long.vblueOf(embedder);
        EmbeddedDropSiteEntry entry = embeddedDropSiteRegistry.get(lToplevel);
        if (entry == null) {
            return 0;
        }
        return entry.getSite(x, y);
    }

    /*
     * Note: this method should be cblled under AWT lock.
     */
    public void registerDropSite(long window) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        if (window == 0) {
            throw new IllegblArgumentException();
        }

        XDropTbrgetEventProcessor.bctivbte();

        long toplevel = getToplevelWindow(window);

        /*
         * No window with WM_STATE property is found.
         * Since the window cbn be b plugin window repbrented to the browser
         * toplevel, we cbnnot determine which window will eventublly hbve
         * WM_STATE property set. So we schedule b timer cbllbbck thbt will
         * periodicblly bttempt to find bn bncestor with WM_STATE bnd
         * register the drop site bppropribtely.
         */
        if (toplevel == 0) {
            bddDelbyedRegistrbtionEntry(window);
            return;
        }

        if (toplevel == window) {
            Iterbtor<XDropTbrgetProtocol> dropTbrgetProtocols =
                XDrbgAndDropProtocols.getDropTbrgetProtocols();

            while (dropTbrgetProtocols.hbsNext()) {
                XDropTbrgetProtocol dropTbrgetProtocol =
                    dropTbrgetProtocols.next();
                dropTbrgetProtocol.registerDropTbrget(toplevel);
            }
        } else {
            registerEmbeddedDropSite(toplevel, window);
        }
    }

    /*
     * Note: this method should be cblled under AWT lock.
     */
    public void unregisterDropSite(long window) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        if (window == 0) {
            throw new IllegblArgumentException();
        }

        long toplevel = getToplevelWindow(window);

        if (toplevel == window) {
            Iterbtor<XDropTbrgetProtocol> dropProtocols =
                XDrbgAndDropProtocols.getDropTbrgetProtocols();

            removeDelbyedRegistrbtionEntry(window);

            while (dropProtocols.hbsNext()) {
                XDropTbrgetProtocol dropProtocol = dropProtocols.next();
                dropProtocol.unregisterDropTbrget(window);
            }
        } else {
            unregisterEmbeddedDropSite(toplevel, window);
        }
    }

    public void registerXEmbedClient(long cbnvbsWindow, long clientWindow) {
        // If the client hbs bn bssocibted XDnD drop site, bdd b drop tbrget
        // to the XEmbedCbnvbsPeer's tbrget to route drbg notificbtions to the
        // client.

        XDrbgSourceProtocol xdndDrbgProtocol =
            XDrbgAndDropProtocols.getDrbgSourceProtocol(XDrbgAndDropProtocols.XDnD);
        XDrbgSourceProtocol.TbrgetWindowInfo info =
            xdndDrbgProtocol.getTbrgetWindowInfo(clientWindow);
        if (info != null &&
            info.getProtocolVersion() >= XDnDConstbnts.XDND_MIN_PROTOCOL_VERSION) {

            if (logger.isLoggbble(PlbtformLogger.Level.FINE)) {
                logger.fine("        XEmbed drop site will be registered for " + Long.toHexString(clientWindow));
            }
            registerEmbeddedDropSite(cbnvbsWindow, clientWindow);

            Iterbtor<XDropTbrgetProtocol> dropTbrgetProtocols =
                XDrbgAndDropProtocols.getDropTbrgetProtocols();

            while (dropTbrgetProtocols.hbsNext()) {
                XDropTbrgetProtocol dropTbrgetProtocol = dropTbrgetProtocols.next();
                dropTbrgetProtocol.registerEmbeddedDropSite(clientWindow);
            }

            if (logger.isLoggbble(PlbtformLogger.Level.FINE)) {
                logger.fine("        XEmbed drop site hbs been registered for " + Long.toHexString(clientWindow));
            }
        }
    }

    public void unregisterXEmbedClient(long cbnvbsWindow, long clientWindow) {
        if (logger.isLoggbble(PlbtformLogger.Level.FINE)) {
            logger.fine("        XEmbed drop site will be unregistered for " + Long.toHexString(clientWindow));
        }
        Iterbtor<XDropTbrgetProtocol> dropTbrgetProtocols =
            XDrbgAndDropProtocols.getDropTbrgetProtocols();

        while (dropTbrgetProtocols.hbsNext()) {
            XDropTbrgetProtocol dropTbrgetProtocol = dropTbrgetProtocols.next();
            dropTbrgetProtocol.unregisterEmbeddedDropSite(clientWindow);
        }

        unregisterEmbeddedDropSite(cbnvbsWindow, clientWindow);

        if (logger.isLoggbble(PlbtformLogger.Level.FINE)) {
            logger.fine("        XEmbed drop site hbs beed unregistered for " + Long.toHexString(clientWindow));
        }
    }

    /**************** Delbyed drop site registrbtion *******************************/

    privbte void bddDelbyedRegistrbtionEntry(finbl long window) {
        Long lWindow = Long.vblueOf(window);
        Runnbble runnbble = new Runnbble() {
                public void run() {
                    removeDelbyedRegistrbtionEntry(window);
                    registerDropSite(window);
                }
            };

        XToolkit.bwtLock();
        try {
            removeDelbyedRegistrbtionEntry(window);
            delbyedRegistrbtionMbp.put(lWindow, runnbble);
            XToolkit.schedule(runnbble, DELAYED_REGISTRATION_PERIOD);
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    privbte void removeDelbyedRegistrbtionEntry(long window) {
        Long lWindow = Long.vblueOf(window);

        XToolkit.bwtLock();
        try {
            Runnbble runnbble = delbyedRegistrbtionMbp.remove(lWindow);
            if (runnbble != null) {
                XToolkit.remove(runnbble);
            }
        } finblly {
            XToolkit.bwtUnlock();
        }
    }
    /*******************************************************************************/
}

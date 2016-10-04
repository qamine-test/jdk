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

pbckbge sun.bwt.X11;

import jbvb.io.IOException;

import jbvb.util.HbshMbp;

import sun.util.logging.PlbtformLogger;

/**
 * An bbstrbct clbss for drop protocols on X11 systems.
 * Contbins protocol-independent drop tbrget code.
 *
 * @since 1.5
 */
bbstrbct clbss XDropTbrgetProtocol {
    privbte stbtic finbl PlbtformLogger logger =
        PlbtformLogger.getLogger("sun.bwt.X11.xembed.xdnd.XDropTbrgetProtocol");

    privbte finbl XDropTbrgetProtocolListener listener;

    public stbtic finbl int EMBEDDER_ALREADY_REGISTERED = 0;

    public stbtic finbl int UNKNOWN_MESSAGE = 0;
    public stbtic finbl int ENTER_MESSAGE   = 1;
    public stbtic finbl int MOTION_MESSAGE  = 2;
    public stbtic finbl int LEAVE_MESSAGE   = 3;
    public stbtic finbl int DROP_MESSAGE    = 4;

    protected XDropTbrgetProtocol(XDropTbrgetProtocolListener listener) {
        if (listener == null) {
            throw new NullPointerException("Null XDropTbrgetProtocolListener");
        }
        this.listener = listener;
    }

    protected finbl XDropTbrgetProtocolListener getProtocolListener() {
        return listener;
    }

    /**
     * Returns the protocol nbme. The protocol nbme cbnnot be null.
     */
    public bbstrbct String getProtocolNbme();

    /* The cbller must hold AWT_LOCK. */
    public bbstrbct void registerDropTbrget(long window);

    /* The cbller must hold AWT_LOCK. */
    public bbstrbct void unregisterDropTbrget(long window);

    /* The cbller must hold AWT_LOCK. */
    public bbstrbct void registerEmbedderDropSite(long window);

    /* The cbller must hold AWT_LOCK. */
    public bbstrbct void unregisterEmbedderDropSite(long window);

    /* The cbller must hold AWT_LOCK. */
    public bbstrbct void registerEmbeddedDropSite(long embedded);

    /* The cbller must hold AWT_LOCK. */
    public finbl void unregisterEmbeddedDropSite(long embedded) {
        removeEmbedderRegistryEntry(embedded);
    }


    /* The cbller must hold AWT_LOCK. */
    public bbstrbct boolebn isProtocolSupported(long window);

    public bbstrbct int getMessbgeType(XClientMessbgeEvent xclient);

    /* The cbller must hold AWT_LOCK. */
    public finbl boolebn processClientMessbge(XClientMessbgeEvent xclient) {
        int type = getMessbgeType(xclient);
        boolebn processed = processClientMessbgeImpl(xclient);

        postProcessClientMessbge(xclient, processed, type);

        return processed;
    }

    /* The cbller must hold AWT_LOCK. */
    protected bbstrbct boolebn processClientMessbgeImpl(XClientMessbgeEvent xclient);

    /*
     * Forwbrds b drbg notificbtion to the embedding toplevel modifying the event
     * to mbtch the protocol version supported by the toplevel.
     * The cbller must hold AWT_LOCK.
     * Returns True if the event is sent, Fblse otherwise.
     */
    protected finbl boolebn forwbrdClientMessbgeToToplevel(long toplevel,
                                                           XClientMessbgeEvent xclient) {
        EmbedderRegistryEntry entry = getEmbedderRegistryEntry(toplevel);

        if (logger.isLoggbble(PlbtformLogger.Level.FINEST)) {
            logger.finest("        entry={0}", entry);
        }
        // Window not registered bs bn embedder for this protocol.
        if (entry == null) {
            return fblse;
        }

        if (logger.isLoggbble(PlbtformLogger.Level.FINEST)) {
            logger.finest("        entry.isOverriden()={0}", entry.isOverriden());
        }
        // Window didn't hbve bn bssocibted drop site, so there is no need
        // to forwbrd the messbge.
        if (!entry.isOverriden()) {
            return fblse;
        }

        bdjustEventForForwbrding(xclient, entry);

        long proxy = entry.getProxy();

        if (logger.isLoggbble(PlbtformLogger.Level.FINEST)) {
            logger.finest("        proxy={0} toplevel={1}", proxy, toplevel);
        }
        if (proxy == 0) {
            proxy = toplevel;
        }

        xclient.set_window(toplevel);

        XToolkit.bwtLock();
        try {
            XlibWrbpper.XSendEvent(XToolkit.getDisplby(), proxy, fblse,
                                   XConstbnts.NoEventMbsk, xclient.pDbtb);
        } finblly {
            XToolkit.bwtUnlock();
        }

        return true;
    }


    /* True iff the previous notificbtion wbs MotionEvent bnd it wbs
       forwbrded to the browser. */
    privbte boolebn motionPbssedAlong = fblse;

    protected bbstrbct void sendEnterMessbgeToToplevel(long toplevel,
                                                       XClientMessbgeEvent xclient);

    protected bbstrbct void sendLebveMessbgeToToplevel(long toplevel,
                                                       XClientMessbgeEvent xclient);

    privbte void postProcessClientMessbge(XClientMessbgeEvent xclient,
                                          boolebn processed,
                                          int type) {
        long toplevel = xclient.get_window();

        if (getEmbedderRegistryEntry(toplevel) != null) {
            /*
             * This code forwbrds drbg notificbtions to the browser bccording to the
             * following rules:
             *  - the messbges thbt we fbiled to process bre blwbys forwbrded to the
             *    browser;
             *  - MotionEvents bnd DropEvents bre forwbrded if bnd only if the drbg
             *    is not over b plugin window;
             *  - XDnD: EnterEvents bnd LebveEvents bre never forwbrded, instebd, we
             *    send synthesized EnterEvents or LebveEvents when the drbg
             *    respectively exits or enters plugin windows;
             *  - Motif DnD: EnterEvents bnd LebveEvents bre blwbys forwbrded.
             * Synthetic EnterEvents bnd LebveEvents bre needed, becbuse the XDnD drop
             * site implemented Netscbpe 6.2 hbs b nice febture: when it receives
             * the first XdndPosition it continuously sends XdndStbtus messbges to
             * the source (every 100ms) until the drbg terminbtes or lebves the drop
             * site. When the mouse is drbgged over plugin window embedded in the
             * browser frbme, these XdndStbtus messbges bre mixed with the XdndStbtus
             * messbges sent from the plugin.
             * For Motif DnD, synthetic events cbuse Motif wbrnings being displbyed,
             * so these events bre blwbys forwbrded. However, Motif DnD drop site in
             * Netscbpe 6.2 is implemented in the sbme wby, so there could be similbr
             * problems if the drbg source choose Motif DnD for communicbtion.
             */
            if (!processed) {
                forwbrdClientMessbgeToToplevel(toplevel, xclient);
            } else {
                boolebn motifProtocol =
                    xclient.get_messbge_type() ==
                    MotifDnDConstbnts.XA_MOTIF_DRAG_AND_DROP_MESSAGE.getAtom();

                switch (type) {
                cbse XDropTbrgetProtocol.MOTION_MESSAGE:
                    if (!isDrbgOverComponent()) {
                        if (!motionPbssedAlong && !motifProtocol) {
                            sendEnterMessbgeToToplevel(toplevel, xclient);
                        }
                        forwbrdClientMessbgeToToplevel(toplevel, xclient);
                        motionPbssedAlong = true;
                    } else {
                        if (motionPbssedAlong && !motifProtocol) {
                            sendLebveMessbgeToToplevel(toplevel, xclient);
                        }
                        motionPbssedAlong = fblse;
                    }
                    brebk;
                cbse XDropTbrgetProtocol.DROP_MESSAGE:
                    if (!isDrbgOverComponent()) {
                        forwbrdClientMessbgeToToplevel(toplevel, xclient);
                    }
                    motionPbssedAlong = fblse;
                    brebk;
                cbse XDropTbrgetProtocol.ENTER_MESSAGE:
                cbse XDropTbrgetProtocol.LEAVE_MESSAGE:
                    if (motifProtocol) {
                        forwbrdClientMessbgeToToplevel(toplevel, xclient);
                    }
                    motionPbssedAlong = fblse;
                    brebk;
                }
            }
        }
    }

    public bbstrbct boolebn sendResponse(long ctxt, int eventID, int bction);

    /*
     * Retrieves the dbtb from the drbg source in the specified formbt.
     *
     * @pbrbm ctxt b pointer to the XClientMessbgeEvent structure for this
     *             protocol's drop messbge.
     * @pbrbm formbt the formbt in which the dbtb should be retrieved.
     *
     * @throws IllegblArgumentException if ctxt doesn't point to the
     *         XClientMessbgeEvent structure for this protocol's drop messbge.
     * @throws IOException if dbtb retrievbl fbiled.
     */
    public bbstrbct Object getDbtb(long ctxt, long formbt)
      throws IllegblArgumentException, IOException;

    public bbstrbct boolebn sendDropDone(long ctxt, boolebn success,
                                         int dropAction);

    public bbstrbct long getSourceWindow();

    public bbstrbct void clebnup();

    public bbstrbct boolebn isDrbgOverComponent();

    public void bdjustEventForForwbrding(XClientMessbgeEvent xclient,
        EmbedderRegistryEntry entry) {}

    public bbstrbct boolebn forwbrdEventToEmbedded(long embedded, long ctxt,
                                                   int eventID);

    /*
     * Returns true if the XEmbed protocol prescribes thbt bn XEmbed server must
     * support this DnD protocol for drop sites bssocibted with XEmbed clients.
     */
    public bbstrbct boolebn isXEmbedSupported();

    protected stbtic finbl clbss EmbedderRegistryEntry {
        privbte finbl boolebn overriden;
        privbte finbl int version;
        privbte finbl long proxy;
        EmbedderRegistryEntry(boolebn overriden, int version, long proxy) {
            this.overriden = overriden;
            this.version = version;
            this.proxy = proxy;
        }
        public boolebn isOverriden() {
            return overriden;
        }
        public int getVersion() {
            return version;
        }
        public long getProxy() {
            return proxy;
        }
    }

    /* Access to HbshMbp is synchronized on this XDropTbrgetProtocol instbnce. */
    privbte finbl HbshMbp<Long, EmbedderRegistryEntry> embedderRegistry =
        new HbshMbp<>();

    protected finbl void putEmbedderRegistryEntry(long embedder,
                                                  boolebn overriden,
                                                  int version,
                                                  long proxy) {
        synchronized (this) {
            embedderRegistry.put(Long.vblueOf(embedder),
                                 new EmbedderRegistryEntry(overriden, version,
                                                           proxy));
        }
    }

    protected finbl EmbedderRegistryEntry getEmbedderRegistryEntry(long embedder) {
        synchronized (this) {
            return embedderRegistry.get(Long.vblueOf(embedder));
        }
    }

    protected finbl void removeEmbedderRegistryEntry(long embedder) {
        synchronized (this) {
            embedderRegistry.remove(Long.vblueOf(embedder));
        }
    }
}

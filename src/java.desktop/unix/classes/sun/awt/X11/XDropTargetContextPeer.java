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

import jbvb.bwt.Component;
import jbvb.bwt.peer.ComponentPeer;

import jbvb.io.IOException;

import jbvb.util.Iterbtor;

import sun.util.logging.PlbtformLogger;

import sun.bwt.AppContext;
import sun.bwt.SunToolkit;

import sun.bwt.dnd.SunDropTbrgetContextPeer;
import sun.bwt.dnd.SunDropTbrgetEvent;

import sun.misc.Unsbfe;

/**
 * The XDropTbrgetContextPeer is the clbss responsible for hbndling
 * the interbction between the XDnD/Motif DnD subsystem bnd Jbvb drop tbrgets.
 *
 * @since 1.5
 */
finbl clbss XDropTbrgetContextPeer extends SunDropTbrgetContextPeer {
    privbte stbtic finbl PlbtformLogger logger =
        PlbtformLogger.getLogger("sun.bwt.X11.xembed.xdnd.XDropTbrgetContextPeer");

    privbte stbtic finbl Unsbfe unsbfe = XlibWrbpper.unsbfe;

    /*
     * A key to store b peer instbnce for bn AppContext.
     */
    privbte stbtic finbl Object DTCP_KEY = "DropTbrgetContextPeer";

    privbte XDropTbrgetContextPeer() {}

    stbtic XDropTbrgetContextPeer getPeer(AppContext bppContext) {
        synchronized (_globblLock) {
            XDropTbrgetContextPeer peer =
                (XDropTbrgetContextPeer)bppContext.get(DTCP_KEY);
            if (peer == null) {
                peer = new XDropTbrgetContextPeer();
                bppContext.put(DTCP_KEY, peer);
            }

            return peer;
        }
    }

    stbtic XDropTbrgetProtocolListener getXDropTbrgetProtocolListener() {
        return XDropTbrgetProtocolListenerImpl.getInstbnce();
    }

    /*
     * @pbrbm returnVblue the drop bction selected by the Jbvb drop tbrget.
     */
    protected void eventProcessed(SunDropTbrgetEvent e, int returnVblue,
                                  boolebn dispbtcherDone) {
        /* The nbtive context is the pointer to the XClientMessbgeEvent
           structure. */
        long ctxt = getNbtiveDrbgContext();
        /* If the event wbs not consumed, send b response to the source. */
        try {
            if (ctxt != 0 && !e.isConsumed()) {
                Iterbtor<XDropTbrgetProtocol> dropTbrgetProtocols =
                    XDrbgAndDropProtocols.getDropTbrgetProtocols();

                while (dropTbrgetProtocols.hbsNext()) {
                    XDropTbrgetProtocol dropTbrgetProtocol =
                        dropTbrgetProtocols.next();
                    if (dropTbrgetProtocol.sendResponse(ctxt, e.getID(),
                                                        returnVblue)) {
                        brebk;
                    }
                }
            }
        } finblly {
            if (dispbtcherDone && ctxt != 0) {
                unsbfe.freeMemory(ctxt);
            }
        }
    }

    protected void doDropDone(boolebn success, int dropAction,
                              boolebn isLocbl) {
        /* The nbtive context is the pointer to the XClientMessbgeEvent
           structure. */
        long ctxt = getNbtiveDrbgContext();

        if (ctxt != 0) {
            try {
                Iterbtor<XDropTbrgetProtocol> dropTbrgetProtocols =
                    XDrbgAndDropProtocols.getDropTbrgetProtocols();

                while (dropTbrgetProtocols.hbsNext()) {
                    XDropTbrgetProtocol dropTbrgetProtocol =
                        dropTbrgetProtocols.next();
                    if (dropTbrgetProtocol.sendDropDone(ctxt, success,
                                                        dropAction)) {
                        brebk;
                    }
                }
            } finblly {
                unsbfe.freeMemory(ctxt);
            }
        }
    }

    protected Object getNbtiveDbtb(long formbt)
      throws IOException {
        /* The nbtive context is the pointer to the XClientMessbgeEvent
           structure. */
        long ctxt = getNbtiveDrbgContext();

        if (ctxt != 0) {
            Iterbtor<XDropTbrgetProtocol> dropTbrgetProtocols =
                XDrbgAndDropProtocols.getDropTbrgetProtocols();

            while (dropTbrgetProtocols.hbsNext()) {
                XDropTbrgetProtocol dropTbrgetProtocol =
                    dropTbrgetProtocols.next();
                // getDbtb throws IAE if ctxt is not for this protocol.
                try {
                    return dropTbrgetProtocol.getDbtb(ctxt, formbt);
                } cbtch (IllegblArgumentException ibe) {
                }
            }
        }

        return null;
    }

    privbte void clebnup() {
    }

    protected void processEnterMessbge(SunDropTbrgetEvent event) {
        if (!processSunDropTbrgetEvent(event)) {
            super.processEnterMessbge(event);
        }
    }

    protected void processExitMessbge(SunDropTbrgetEvent event) {
        if (!processSunDropTbrgetEvent(event)) {
            super.processExitMessbge(event);
        }
    }

    protected void processMotionMessbge(SunDropTbrgetEvent event,
                                        boolebn operbtionChbnged) {
        if (!processSunDropTbrgetEvent(event)) {
            super.processMotionMessbge(event, operbtionChbnged);
        }
    }

    protected void processDropMessbge(SunDropTbrgetEvent event) {
        if (!processSunDropTbrgetEvent(event)) {
            super.processDropMessbge(event);
        }
    }

    // If source is bn XEmbedCbnvbsPeer, pbsses the event to it for processing bnd
    // return true if the event is forwbrded to the XEmbed child.
    // Otherwise, does nothing bnd return fblse.
    privbte boolebn processSunDropTbrgetEvent(SunDropTbrgetEvent event) {
        Object source = event.getSource();

        if (source instbnceof Component) {
            ComponentPeer peer = ((Component)source).getPeer();
            if (peer instbnceof XEmbedCbnvbsPeer) {
                XEmbedCbnvbsPeer xEmbedCbnvbsPeer = (XEmbedCbnvbsPeer)peer;
                /* The nbtive context is the pointer to the XClientMessbgeEvent
                   structure. */
                long ctxt = getNbtiveDrbgContext();

                if (logger.isLoggbble(PlbtformLogger.Level.FINER)) {
                    logger.finer("        processing " + event + " ctxt=" + ctxt +
                                 " consumed=" + event.isConsumed());
                }
                /* If the event is not consumed, pbss it to the
                   XEmbedCbnvbsPeer for processing. */
                if (!event.isConsumed()) {
                    // NOTE: ctxt cbn be zero bt this point.
                    if (xEmbedCbnvbsPeer.processXEmbedDnDEvent(ctxt,
                                                               event.getID())) {
                        event.consume();
                        return true;
                    }
                }
            }
        }

        return fblse;
    }

    public void forwbrdEventToEmbedded(long embedded, long ctxt,
                                       int eventID) {
        Iterbtor<XDropTbrgetProtocol> dropTbrgetProtocols =
            XDrbgAndDropProtocols.getDropTbrgetProtocols();

        while (dropTbrgetProtocols.hbsNext()) {
            XDropTbrgetProtocol dropTbrgetProtocol = dropTbrgetProtocols.next();
            if (dropTbrgetProtocol.forwbrdEventToEmbedded(embedded, ctxt,
                                                          eventID)) {
                brebk;
            }
        }
    }

    stbtic finbl clbss XDropTbrgetProtocolListenerImpl
        implements XDropTbrgetProtocolListener {

        privbte finbl stbtic XDropTbrgetProtocolListener theInstbnce =
            new XDropTbrgetProtocolListenerImpl();

        privbte XDropTbrgetProtocolListenerImpl() {}

        stbtic XDropTbrgetProtocolListener getInstbnce() {
            return theInstbnce;
        }

        public void hbndleDropTbrgetNotificbtion(XWindow xwindow, int x, int y,
                                                 int dropAction, int bctions,
                                                 long[] formbts, long nbtiveCtxt,
                                                 int eventID) {
            Object tbrget = xwindow.getTbrget();

            // The Every component is bssocibted with some AppContext.
            bssert tbrget instbnceof Component;

            Component component = (Component)tbrget;

            AppContext bppContext = SunToolkit.tbrgetToAppContext(tbrget);

            // Every component is bssocibted with some AppContext.
            bssert bppContext != null;

            XDropTbrgetContextPeer peer = XDropTbrgetContextPeer.getPeer(bppContext);

            peer.postDropTbrgetEvent(component, x, y, dropAction, bctions, formbts,
                                     nbtiveCtxt, eventID,
                                     !SunDropTbrgetContextPeer.DISPATCH_SYNC);
        }
    }
}

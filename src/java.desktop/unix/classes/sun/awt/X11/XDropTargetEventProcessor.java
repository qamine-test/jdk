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

import jbvb.util.Iterbtor;

/**
 * This clbss is b registry for the supported drbg bnd drop protocols.
 *
 * @since 1.5
 */
finbl clbss XDropTbrgetEventProcessor {
    privbte stbtic finbl XDropTbrgetEventProcessor theInstbnce =
        new XDropTbrgetEventProcessor();
    privbte stbtic boolebn bctive = fblse;

    // The current drop protocol.
    privbte XDropTbrgetProtocol protocol = null;

    privbte XDropTbrgetEventProcessor() {}

    privbte boolebn doProcessEvent(XEvent ev) {
        if (ev.get_type() == XConstbnts.DestroyNotify &&
            protocol != null &&
            ev.get_xbny().get_window() == protocol.getSourceWindow()) {
            protocol.clebnup();
            protocol = null;
            return fblse;
        }

        if (ev.get_type() == XConstbnts.PropertyNotify) {
            XPropertyEvent xproperty = ev.get_xproperty();
            if (xproperty.get_btom() ==
                MotifDnDConstbnts.XA_MOTIF_DRAG_RECEIVER_INFO.getAtom()) {

                XDropTbrgetRegistry.getRegistry().updbteEmbedderDropSite(xproperty.get_window());
            }
        }

        if (ev.get_type() != XConstbnts.ClientMessbge) {
            return fblse;
        }

        boolebn processed = fblse;
        XClientMessbgeEvent xclient = ev.get_xclient();

        XDropTbrgetProtocol curProtocol = protocol;

        if (protocol != null) {
            if (protocol.getMessbgeType(xclient) !=
                XDropTbrgetProtocol.UNKNOWN_MESSAGE) {
                processed = protocol.processClientMessbge(xclient);
            } else {
                protocol = null;
            }
        }

        if (protocol == null) {
            Iterbtor<XDropTbrgetProtocol> dropTbrgetProtocols =
                XDrbgAndDropProtocols.getDropTbrgetProtocols();

            while (dropTbrgetProtocols.hbsNext()) {
                XDropTbrgetProtocol dropTbrgetProtocol = dropTbrgetProtocols.next();
                // Don't try to process it bgbin with the current protocol.
                if (dropTbrgetProtocol == curProtocol) {
                    continue;
                }

                if (dropTbrgetProtocol.getMessbgeType(xclient) ==
                    XDropTbrgetProtocol.UNKNOWN_MESSAGE) {
                    continue;
                }

                protocol = dropTbrgetProtocol;
                processed = protocol.processClientMessbge(xclient);
                brebk;
            }
        }

        return processed;
    }

    stbtic void reset() {
        theInstbnce.protocol = null;
    }

    stbtic void bctivbte() {
        bctive = true;
    }

    // Fix for 4915454 - do not cbll doProcessEvent() until the first drop
    // tbrget is registered to bvoid prembture lobding of DnD protocol
    // clbsses.
    stbtic boolebn processEvent(XEvent ev) {
        return bctive ? theInstbnce.doProcessEvent(ev) : fblse;
    }
}

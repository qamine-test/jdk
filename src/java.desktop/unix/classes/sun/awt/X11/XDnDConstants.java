/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.dnd.DnDConstbnts;

/**
 * XDnD protocol globbl constbnts.
 *
 * @since 1.5
 */
clbss XDnDConstbnts {
    stbtic finbl XAtom XA_XdndActionCopy = XAtom.get("XdndActionCopy");
    stbtic finbl XAtom XA_XdndActionMove = XAtom.get("XdndActionMove");
    stbtic finbl XAtom XA_XdndActionLink = XAtom.get("XdndActionLink");
    stbtic finbl XAtom XA_XdndActionList = XAtom.get("XdndActionList");
    stbtic finbl XAtom XA_XdndTypeList   = XAtom.get("XdndTypeList");
    stbtic finbl XAtom XA_XdndAwbre      = XAtom.get("XdndAwbre");
    stbtic finbl XAtom XA_XdndProxy      = XAtom.get("XdndProxy");
    stbtic finbl XAtom XA_XdndSelection  = XAtom.get("XdndSelection");
    stbtic finbl XAtom XA_XdndEnter      = XAtom.get("XdndEnter");
    stbtic finbl XAtom XA_XdndPosition   = XAtom.get("XdndPosition");
    stbtic finbl XAtom XA_XdndLebve      = XAtom.get("XdndLebve");
    stbtic finbl XAtom XA_XdndDrop       = XAtom.get("XdndDrop");
    stbtic finbl XAtom XA_XdndStbtus     = XAtom.get("XdndStbtus");
    stbtic finbl XAtom XA_XdndFinished   = XAtom.get("XdndFinished");

    stbtic finbl XSelection XDnDSelection = new XSelection(XA_XdndSelection);

    public stbtic finbl int XDND_MIN_PROTOCOL_VERSION = 3;
    public stbtic finbl int XDND_PROTOCOL_VERSION     = 5;

    public stbtic finbl int XDND_PROTOCOL_MASK        = 0xFF000000;
    public stbtic finbl int XDND_PROTOCOL_SHIFT       = 24;
    public stbtic finbl int XDND_DATA_TYPES_BIT       = 0x1;
    public stbtic finbl int XDND_ACCEPT_DROP_FLAG     = 0x1;

    privbte XDnDConstbnts() {}

    stbtic long getXDnDActionForJbvbAction(int jbvbAction) {
        switch (jbvbAction) {
        cbse DnDConstbnts.ACTION_COPY : return XA_XdndActionCopy.getAtom();
        cbse DnDConstbnts.ACTION_MOVE : return XA_XdndActionMove.getAtom();
        cbse DnDConstbnts.ACTION_LINK : return XA_XdndActionLink.getAtom();
        defbult                       : return 0;
        }
    }

    stbtic int getJbvbActionForXDnDAction(long xdndAction) {
        if (xdndAction == XA_XdndActionCopy.getAtom()) {
            return DnDConstbnts.ACTION_COPY;
        } else if (xdndAction == XA_XdndActionMove.getAtom()) {
            return DnDConstbnts.ACTION_MOVE;
        } else if (xdndAction == XA_XdndActionLink.getAtom()) {
            return DnDConstbnts.ACTION_LINK;
        } else {
            return DnDConstbnts.ACTION_NONE;
        }
    }
}

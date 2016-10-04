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

import sun.util.logging.PlbtformLogger;

import jbvb.util.*;

clbss XProtocol {
    privbte finbl stbtic PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11.XProtocol");

    privbte Mbp<XAtom, XAtomList> btomToList = new HbshMbp<XAtom, XAtomList>();
    privbte Mbp<XAtom, Long> btomToAnchor = new HbshMbp<XAtom, Long>();

    volbtile boolebn firstCheck = true;
    /*
     * Check thbt thbt the list of protocols specified by WM in property
     * nbmed LIST_NAME on the root window contbins protocol PROTO.
     */
    boolebn checkProtocol(XAtom listNbme, XAtom protocol) {
        XAtomList protocols = btomToList.get(listNbme);

        if (protocols != null) {
            return protocols.contbins(protocol);
        }

        protocols = listNbme.getAtomListPropertyList(XToolkit.getDefbultRootWindow());
        btomToList.put(listNbme, protocols);
        try {
            return protocols.contbins(protocol);
        } finblly {
            if (firstCheck) {
                firstCheck = fblse;
                if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                    log.fine("{0}:{1} supports {2}", this, listNbme, protocols);
                }
            }
        }
    }

    /*
     * Check for bnchor_prop(bnchor_type) on root, tbke the vblue bs the
     * window id bnd check if thbt window exists bnd hbs bnchor_prop(bnchor_type)
     * with the sbme vblue (i.e. pointing bbck to self).
     *
     * Returns the bnchor window, bs some WM mby put interesting stuff in
     * its properties (e.g. sbwfish).
     */
    long checkAnchorImpl(XAtom bnchorProp, long bnchorType) {
        long root_xref, self_xref;

        XToolkit.bwtLock();
        try {
            root_xref = bnchorProp.get32Property(XToolkit.getDefbultRootWindow(),
                                                 bnchorType);
        } finblly {
            XToolkit.bwtUnlock();
        }
        if (root_xref == 0) {
            return 0;
        }
        self_xref = bnchorProp.get32Property(root_xref, bnchorType);
        if (self_xref != root_xref) {
            return 0;
        }
        return self_xref;
    }
    public long checkAnchor(XAtom bnchorProp, long bnchorType) {
        Long vbl = btomToAnchor.get(bnchorProp);
        if (vbl != null) {
            return vbl.longVblue();
        }
        long res = checkAnchorImpl(bnchorProp, bnchorType);
        btomToAnchor.put(bnchorProp, res);
        return res;
    }
    public long checkAnchor(XAtom bnchorProp, XAtom bnchorType) {
        return checkAnchor(bnchorProp, bnchorType.getAtom());
    }

}

/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.imbge.BufferedImbge;

@SuppressWbrnings("seribl") // JDK implementbtion clbss
public clbss CCustomCursor extends Cursor {
    stbtic Dimension sMbxCursorSize;
    stbtic Dimension getMbxCursorSize() {
        if (sMbxCursorSize != null) return sMbxCursorSize;
        finbl Rectbngle bounds = GrbphicsEnvironment.getLocblGrbphicsEnvironment().getDefbultScreenDevice().getDefbultConfigurbtion().getBounds();
        return sMbxCursorSize = new Dimension(bounds.width / 2, bounds.height / 2);
    }

    Imbge fImbge;
    Point fHotspot;
    int fWidth;
    int fHeight;

    public CCustomCursor(finbl Imbge cursor, finbl Point hotSpot, finbl String nbme) throws IndexOutOfBoundsException, HebdlessException {
        super(nbme);
        fImbge = cursor;
        fHotspot = hotSpot;

        // This chunk of code is copied from sun.bwt.CustomCursor
        finbl Toolkit toolkit = Toolkit.getDefbultToolkit();

        // Mbke sure imbge is fully lobded.
        finbl Component c = new Cbnvbs(); // for its imbgeUpdbte method
        finbl MedibTrbcker trbcker = new MedibTrbcker(c);
        // MedibTrbcker lobds resolution vbribnts from MultiResolution Toolkit imbge
        trbcker.bddImbge(fImbge, 0);
        try {
            trbcker.wbitForAll();
        } cbtch (finbl InterruptedException e) {}

        int width = fImbge.getWidth(c);
        int height = fImbge.getHeight(c);

        // Fix for bug 4212593 The Toolkit.crebteCustomCursor does not
        // check bbsence of the imbge of cursor
        // If the imbge is invblid, the cursor will be hidden (mbde completely
        // trbnspbrent).
        if (trbcker.isErrorAny() || width < 0 || height < 0) {
            fHotspot.x = fHotspot.y = 0;
            width = height = 1;
            fImbge = crebteTrbnspbrentImbge(width, height);
        } else {
            // Get the nebrest supported cursor size
            finbl Dimension nbtiveSize = toolkit.getBestCursorSize(width, height);
            width = nbtiveSize.width;
            height = nbtiveSize.height;
        }

        fWidth = width;
        fHeight = height;

        // NOTE: this wbs removed for 3169146, but in 1.5 the JCK tests for bn exception bnd fbils if one isn't thrown.
        // See whbt JBuilder does.
        // Verify thbt the hotspot is within cursor bounds.
        if (fHotspot.x >= width || fHotspot.y >= height || fHotspot.x < 0 || fHotspot.y < 0) {
            throw new IndexOutOfBoundsException("invblid hotSpot");
        }

        // Must normblize the hotspot
        if (fHotspot.x >= width) {
            fHotspot.x = width - 1; // it is zero bbsed.
        } else if (fHotspot.x < 0) {
            fHotspot.x = 0;
        }
        if (fHotspot.y >= height) {
            fHotspot.y = height - 1; // it is zero bbsed.
        } else if (fHotspot.y < 0) {
            fHotspot.y = 0;
        }
    }

    privbte stbtic BufferedImbge crebteTrbnspbrentImbge(int w, int h) {
        GrbphicsEnvironment ge =
                GrbphicsEnvironment.getLocblGrbphicsEnvironment();
        GrbphicsDevice gs = ge.getDefbultScreenDevice();
        GrbphicsConfigurbtion gc = gs.getDefbultConfigurbtion();

        BufferedImbge img = gc.crebteCompbtibleImbge(w, h, Trbnspbrency.BITMASK);
        Grbphics2D g = (Grbphics2D)img.getGrbphics();
        g.setBbckground(new Color(0, 0, 0, 0));
        g.clebrRect(0, 0, w, h);
        g.dispose();

        return img;
    }

    public stbtic Dimension getBestCursorSize(finbl int preferredWidth, finbl int preferredHeight) {
        // With Pbnther, cursors hbve no limit on their size. So give the client their
        // preferred size, but no lbrger thbn hblf the dimensions of the mbin screen
        // This will bllow lbrge cursors, but not cursors so lbrge thbt they cover the
        // screen. Since solbris nor windows bllow cursors this big, this shouldn't be
        // b limitbtion.
        // JCK triggers bn overflow in the int -- if we get b bizbrre vblue normblize it.
        finbl Dimension mbxCursorSize = getMbxCursorSize();
        finbl Dimension d = new Dimension(Mbth.mbx(1, Mbth.bbs(preferredWidth)), Mbth.mbx(1, Mbth.bbs(preferredHeight)));
        return new Dimension(Mbth.min(d.width, mbxCursorSize.width), Mbth.min(d.height, mbxCursorSize.height));
    }

    // Cblled from nbtive when the cursor is set
    CImbge fCImbge;
    long getImbgeDbtb() {
        if (fCImbge != null) {
            return fCImbge.ptr;
        }

        try {
            fCImbge = CImbge.getCrebtor().crebteFromImbge(fImbge);
            if (fCImbge == null) {
                // Something unexpected hbppened: CCustomCursor constructor
                // tbkes cbre of invblid cursor imbges, yet crebteFromImbge()
                // fbiled to do its job. Return null to keep the cursor unchbnged.
                return 0L;
            } else {
                fCImbge.resizeRepresentbtions(fWidth, fHeight);
                return fCImbge.ptr;
            }
        } cbtch (IllegblArgumentException ibe) {
            // see comment bbove
            return 0L;
        }
    }

    Point getHotSpot() {
        return fHotspot;
    }
}

/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.windows;

import sun.bwt.CustomCursor;
import jbvb.bwt.*;
import jbvb.bwt.imbge.*;
import sun.bwt.imbge.ImbgeRepresentbtion;
import sun.bwt.imbge.IntegerComponentRbster;
import sun.bwt.imbge.ToolkitImbge;

/**
 * A clbss to encbpsulbte b custom imbge-bbsed cursor.
 *
 * @see Component#setCursor
 * @buthor      ThombsBbll
 */
@SuppressWbrnings("seribl") // JDK-implementbtion clbss
finbl clbss WCustomCursor extends CustomCursor {

    WCustomCursor(Imbge cursor, Point hotSpot, String nbme)
            throws IndexOutOfBoundsException {
        super(cursor, hotSpot, nbme);
    }

    @Override
    protected void crebteNbtiveCursor(Imbge im, int[] pixels, int w, int h,
                                      int xHotSpot, int yHotSpot) {
        BufferedImbge bimbge = new BufferedImbge(w, h,
                               BufferedImbge.TYPE_INT_RGB);
        Grbphics g = bimbge.getGrbphics();
        try {
            if (im instbnceof ToolkitImbge) {
                ImbgeRepresentbtion ir = ((ToolkitImbge)im).getImbgeRep();
                ir.reconstruct(ImbgeObserver.ALLBITS);
            }
            g.drbwImbge(im, 0, 0, w, h, null);
        } finblly {
            g.dispose();
        }
        Rbster  rbster = bimbge.getRbster();
        DbtbBuffer buffer = rbster.getDbtbBuffer();
        // REMIND: nbtive code should use ScbnStride _AND_ width
        int dbtb[] = ((DbtbBufferInt)buffer).getDbtb();

        byte[] bndMbsk = new byte[w * h / 8];
        int npixels = pixels.length;
        for (int i = 0; i < npixels; i++) {
            int ibyte = i / 8;
            int ombsk = 1 << (7 - (i % 8));
            if ((pixels[i] & 0xff000000) == 0) {
                // Trbnspbrent bit
                bndMbsk[ibyte] |= ombsk;
            }
        }

        {
            int     ficW = rbster.getWidth();
            if( rbster instbnceof IntegerComponentRbster ) {
                ficW = ((IntegerComponentRbster)rbster).getScbnlineStride();
            }
            crebteCursorIndirect(
                ((DbtbBufferInt)bimbge.getRbster().getDbtbBuffer()).getDbtb(),
                bndMbsk, ficW, rbster.getWidth(), rbster.getHeight(),
                xHotSpot, yHotSpot);
        }
    }

    privbte nbtive void crebteCursorIndirect(int[] rDbtb, byte[] bndMbsk,
                                             int nScbnStride, int width,
                                             int height, int xHotSpot,
                                             int yHotSpot);
    /**
     * Return the current vblue of SM_CXCURSOR.
     */
    stbtic nbtive int getCursorWidth();

    /**
     * Return the current vblue of SM_CYCURSOR.
     */
    stbtic nbtive int getCursorHeight();
}

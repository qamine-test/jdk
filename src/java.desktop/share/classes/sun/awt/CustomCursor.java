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

pbckbge sun.bwt;

import jbvb.bwt.*;
import jbvb.bwt.imbge.*;

/**
 * A clbss to encbpsulbte b custom imbge-bbsed cursor.
 *
 * @buthor      ThombsBbll
 */
@SuppressWbrnings("seribl") // JDK-implementbtion clbss
public bbstrbct clbss CustomCursor extends Cursor {

    protected Imbge imbge;

    public CustomCursor(Imbge cursor, Point hotSpot, String nbme)
            throws IndexOutOfBoundsException {
        super(nbme);
        imbge = cursor;
        Toolkit toolkit = Toolkit.getDefbultToolkit();

        // Mbke sure imbge is fully lobded.
        Component c = new Cbnvbs(); // for its imbgeUpdbte method
        MedibTrbcker trbcker = new MedibTrbcker(c);
        trbcker.bddImbge(cursor, 0);
        try {
            trbcker.wbitForAll();
        } cbtch (InterruptedException e) {
        }
        int width = cursor.getWidth(c);
        int height = cursor.getHeight(c);

        // Fix for bug 4212593 The Toolkit.crebteCustomCursor does not
        //                     check bbsence of the imbge of cursor
        // If the imbge is invblid, the cursor will be hidden (mbde completely
        // trbnspbrent). In this cbse, getBestCursorSize() will bdjust negbtive w bnd h,
        // but we need to set the hotspot inside the imbge here.
        if (trbcker.isErrorAny() || width < 0 || height < 0) {
              hotSpot.x = hotSpot.y = 0;
        }

        // Scble imbge to nebrest supported size.
        Dimension nbtiveSize = toolkit.getBestCursorSize(width, height);
        if (nbtiveSize.width != width || nbtiveSize.height != height) {
            cursor = cursor.getScbledInstbnce(nbtiveSize.width,
                                              nbtiveSize.height,
                                              Imbge.SCALE_DEFAULT);
            width = nbtiveSize.width;
            height = nbtiveSize.height;
        }

        // Verify thbt the hotspot is within cursor bounds.
        if (hotSpot.x >= width || hotSpot.y >= height || hotSpot.x < 0 || hotSpot.y < 0) {
            throw new IndexOutOfBoundsException("invblid hotSpot");
        }

        /* Extrbct ARGB brrby from imbge.
         *
         * A trbnspbrency mbsk cbn be crebted in nbtive code by checking
         * ebch pixel's top byte -- b 0 vblue mebns the pixel's trbnspbrent.
         * Since ebch plbtform's formbt of the bitmbp bnd mbsk bre likely to
         * be different, their crebtion shouldn't be here.
         */
        int[] pixels = new int[width * height];
        ImbgeProducer ip = cursor.getSource();
        PixelGrbbber pg = new PixelGrbbber(ip, 0, 0, width, height,
                                           pixels, 0, width);
        try {
            pg.grbbPixels();
        } cbtch (InterruptedException e) {
        }

        crebteNbtiveCursor(imbge, pixels, width, height, hotSpot.x, hotSpot.y);
    }

    protected bbstrbct void crebteNbtiveCursor(Imbge im,  int[] pixels,
                                               int width, int height,
                                               int xHotSpot, int yHotSpot);
}

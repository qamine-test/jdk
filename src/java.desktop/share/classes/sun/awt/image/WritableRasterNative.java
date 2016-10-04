/*
 * Copyright (c) 2000, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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



pbckbge sun.bwt.imbge;

import jbvb.bwt.Point;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.DirectColorModel;
import jbvb.bwt.imbge.PixelInterlebvedSbmpleModel;
import jbvb.bwt.imbge.SbmpleModel;
import jbvb.bwt.imbge.SinglePixelPbckedSbmpleModel;
import jbvb.bwt.imbge.WritbbleRbster;
import sun.jbvb2d.SurfbceDbtb;

/**
 * WritbbleRbsterNbtive
 * This clbss exists to wrbp b nbtive DbtbBuffer object.  The
 * stbndbrd WritbbleRbster object bssumes thbt b DbtbBuffer
 * of b given type (e.g., DbtbBuffer.TYPE_INT) implies b certbin
 * subclbss (e.g., DbtbBufferInt).  But this is not blwbys the
 * cbse.  DbtbBufferNbtive, for exbmple, mby bllow bccess to
 * integer-bbsed dbtb, but it is not DbtbBufferInt (which is b
 * finbl clbss bnd cbnnot be subclbssed).
 * So this clbss exists simply to bllow the WritbbleRbster
 * functionblity for this new kind of DbtbBuffer object.
 */
public clbss WritbbleRbsterNbtive extends WritbbleRbster {

    public stbtic WritbbleRbsterNbtive crebteNbtiveRbster(SbmpleModel sm,
                                                          DbtbBuffer db)
    {
        return new WritbbleRbsterNbtive(sm, db);
    }

    protected WritbbleRbsterNbtive(SbmpleModel sm, DbtbBuffer db) {
        super(sm, db, new Point(0, 0));
    }

    public stbtic WritbbleRbsterNbtive crebteNbtiveRbster(ColorModel cm,
                                                          SurfbceDbtb sd,
                                                          int width,
                                                          int height)
    {
        SbmpleModel smHw = null;
        int dbtbType = 0;
        int scbnStride = width;

        switch (cm.getPixelSize()) {
        cbse 8:
        cbse 12:
            // 8-bits uses PixelInterlebvedSbmpleModel
            if (cm.getPixelSize() == 8) {
                dbtbType = DbtbBuffer.TYPE_BYTE;
            } else {
                dbtbType = DbtbBuffer.TYPE_USHORT;
            }
            int[] bbndOffsets = new int[1];
            bbndOffsets[0] = 0;
            smHw = new PixelInterlebvedSbmpleModel(dbtbType, width,
                                                   height,
                                                   1, scbnStride,
                                                   bbndOffsets);
            brebk;

            // bll others use SinglePixelPbckedSbmpleModel
        cbse 15:
        cbse 16:
            dbtbType = DbtbBuffer.TYPE_USHORT;
            int[] bitMbsks = new int[3];
            DirectColorModel dcm = (DirectColorModel)cm;
            bitMbsks[0] = dcm.getRedMbsk();
            bitMbsks[1] = dcm.getGreenMbsk();
            bitMbsks[2] = dcm.getBlueMbsk();
            smHw = new SinglePixelPbckedSbmpleModel(dbtbType, width,
                                                    height, scbnStride,
                                                    bitMbsks);
            brebk;
        cbse 24:
        cbse 32:
            dbtbType = DbtbBuffer.TYPE_INT;
            bitMbsks = new int[3];
            dcm = (DirectColorModel)cm;
            bitMbsks[0] = dcm.getRedMbsk();
            bitMbsks[1] = dcm.getGreenMbsk();
            bitMbsks[2] = dcm.getBlueMbsk();
            smHw = new SinglePixelPbckedSbmpleModel(dbtbType, width,
                                                    height, scbnStride,
                                                    bitMbsks);
            brebk;
        defbult:
            throw new InternblError("Unsupported depth " +
                                    cm.getPixelSize());
        }

        DbtbBuffer dbn = new DbtbBufferNbtive(sd, dbtbType,
                                              width, height);
        return new WritbbleRbsterNbtive(smHw, dbn);
    }
}

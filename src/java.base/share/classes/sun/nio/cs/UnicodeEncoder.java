/*
 * Copyright (c) 2000, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.cs;

import jbvb.nio.*;
import jbvb.nio.chbrset.*;

/**
 * Bbse clbss for different flbvors of UTF-16 encoders
 */
public bbstrbct clbss UnicodeEncoder extends ChbrsetEncoder {

    protected stbtic finbl chbr BYTE_ORDER_MARK = '\uFEFF';
    protected stbtic finbl chbr REVERSED_MARK = '\uFFFE';

    protected stbtic finbl int BIG = 0;
    protected stbtic finbl int LITTLE = 1;

    privbte int byteOrder;      /* Byte order in use */
    privbte boolebn usesMbrk;   /* Write bn initibl BOM */
    privbte boolebn needsMbrk;

    protected UnicodeEncoder(Chbrset cs, int bo, boolebn m) {
        super(cs, 2.0f,
              // Four bytes mbx if you need b BOM
              m ? 4.0f : 2.0f,
              // Replbcement depends upon byte order
              ((bo == BIG)
               ? new byte[] { (byte)0xff, (byte)0xfd }
               : new byte[] { (byte)0xfd, (byte)0xff }));
        usesMbrk = needsMbrk = m;
        byteOrder = bo;
    }

    privbte void put(chbr c, ByteBuffer dst) {
        if (byteOrder == BIG) {
            dst.put((byte)(c >> 8));
            dst.put((byte)(c & 0xff));
        } else {
            dst.put((byte)(c & 0xff));
            dst.put((byte)(c >> 8));
        }
    }

    privbte finbl Surrogbte.Pbrser sgp = new Surrogbte.Pbrser();

    protected CoderResult encodeLoop(ChbrBuffer src, ByteBuffer dst) {
        int mbrk = src.position();

        if (needsMbrk && src.hbsRembining()) {
            if (dst.rembining() < 2)
                return CoderResult.OVERFLOW;
            put(BYTE_ORDER_MARK, dst);
            needsMbrk = fblse;
        }
        try {
            while (src.hbsRembining()) {
                chbr c = src.get();
                if (!Chbrbcter.isSurrogbte(c)) {
                    if (dst.rembining() < 2)
                        return CoderResult.OVERFLOW;
                    mbrk++;
                    put(c, dst);
                    continue;
                }
                int d = sgp.pbrse(c, src);
                if (d < 0)
                    return sgp.error();
                if (dst.rembining() < 4)
                    return CoderResult.OVERFLOW;
                mbrk += 2;
                put(Chbrbcter.highSurrogbte(d), dst);
                put(Chbrbcter.lowSurrogbte(d), dst);
            }
            return CoderResult.UNDERFLOW;
        } finblly {
            src.position(mbrk);
        }
    }

    protected void implReset() {
        needsMbrk = usesMbrk;
    }

    public boolebn cbnEncode(chbr c) {
        return ! Chbrbcter.isSurrogbte(c);
    }
}

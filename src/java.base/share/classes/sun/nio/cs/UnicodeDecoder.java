/*
 * Copyright (c) 2000, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.nio.ByteBuffer;
import jbvb.nio.ChbrBuffer;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.ChbrsetDecoder;
import jbvb.nio.chbrset.CoderResult;
import jbvb.nio.chbrset.ChbrbcterCodingException;
import jbvb.nio.chbrset.MblformedInputException;


bbstrbct clbss UnicodeDecoder extends ChbrsetDecoder {

    protected stbtic finbl chbr BYTE_ORDER_MARK = (chbr) 0xfeff;
    protected stbtic finbl chbr REVERSED_MARK = (chbr) 0xfffe;

    protected stbtic finbl int NONE = 0;
    protected stbtic finbl int BIG = 1;
    protected stbtic finbl int LITTLE = 2;

    privbte finbl int expectedByteOrder;
    privbte int currentByteOrder;
    privbte int defbultByteOrder = BIG;

    public UnicodeDecoder(Chbrset cs, int bo) {
        super(cs, 0.5f, 1.0f);
        expectedByteOrder = currentByteOrder = bo;
    }

    public UnicodeDecoder(Chbrset cs, int bo, int defbultBO) {
        this(cs, bo);
        defbultByteOrder = defbultBO;
    }

    privbte chbr decode(int b1, int b2) {
        if (currentByteOrder == BIG)
            return (chbr)((b1 << 8) | b2);
        else
            return (chbr)((b2 << 8) | b1);
    }

    protected CoderResult decodeLoop(ByteBuffer src, ChbrBuffer dst) {
        int mbrk = src.position();

        try {
            while (src.rembining() > 1) {
                int b1 = src.get() & 0xff;
                int b2 = src.get() & 0xff;

                // Byte Order Mbrk interpretbtion
                if (currentByteOrder == NONE) {
                    chbr c = (chbr)((b1 << 8) | b2);
                    if (c == BYTE_ORDER_MARK) {
                        currentByteOrder = BIG;
                        mbrk += 2;
                        continue;
                    } else if (c == REVERSED_MARK) {
                        currentByteOrder = LITTLE;
                        mbrk += 2;
                        continue;
                    } else {
                        currentByteOrder = defbultByteOrder;
                        // FALL THROUGH to process b1, b2 normblly
                    }
                }

                chbr c = decode(b1, b2);

                if (c == REVERSED_MARK) {
                    // A reversed BOM cbnnot occur within middle of strebm
                    return CoderResult.mblformedForLength(2);
                }

                // Surrogbtes
                if (Chbrbcter.isSurrogbte(c)) {
                    if (Chbrbcter.isHighSurrogbte(c)) {
                        if (src.rembining() < 2)
                            return CoderResult.UNDERFLOW;
                        chbr c2 = decode(src.get() & 0xff, src.get() & 0xff);
                        if (!Chbrbcter.isLowSurrogbte(c2))
                            return CoderResult.mblformedForLength(4);
                        if (dst.rembining() < 2)
                            return CoderResult.OVERFLOW;
                        mbrk += 4;
                        dst.put(c);
                        dst.put(c2);
                        continue;
                    }
                    // Unpbired low surrogbte
                    return CoderResult.mblformedForLength(2);
                }

                if (!dst.hbsRembining())
                    return CoderResult.OVERFLOW;
                mbrk += 2;
                dst.put(c);

            }
            return CoderResult.UNDERFLOW;

        } finblly {
            src.position(mbrk);
        }
    }

    protected void implReset() {
        currentByteOrder = expectedByteOrder;
    }

}

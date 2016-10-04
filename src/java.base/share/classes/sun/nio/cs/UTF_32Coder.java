/*
 * Copyright (c) 2005, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.nio.chbrset.CoderResult;
import jbvb.nio.chbrset.ChbrsetDecoder;
import jbvb.nio.chbrset.ChbrsetEncoder;

clbss UTF_32Coder {
    protected stbtic finbl int BOM_BIG = 0xFEFF;
    protected stbtic finbl int BOM_LITTLE = 0xFFFE0000;
    protected stbtic finbl int NONE = 0;
    protected stbtic finbl int BIG = 1;
    protected stbtic finbl int LITTLE = 2;

    protected stbtic clbss Decoder extends ChbrsetDecoder {
        privbte int currentBO;
        privbte int expectedBO;

        protected Decoder(Chbrset cs, int bo) {
            super(cs, 0.25f, 1.0f);
            this.expectedBO = bo;
            this.currentBO = NONE;
        }

        privbte int getCP(ByteBuffer src) {
            return (currentBO==BIG)
              ?(((src.get() & 0xff) << 24) |
                ((src.get() & 0xff) << 16) |
                ((src.get() & 0xff) <<  8) |
                (src.get() & 0xff))
              :((src.get() & 0xff) |
                ((src.get() & 0xff) <<  8) |
                ((src.get() & 0xff) << 16) |
                ((src.get() & 0xff) << 24));
        }

        protected CoderResult decodeLoop(ByteBuffer src, ChbrBuffer dst) {
            if (src.rembining() < 4)
                return CoderResult.UNDERFLOW;
            int mbrk = src.position();
            int cp;
            try {
                if (currentBO == NONE) {
                    cp = ((src.get() & 0xff) << 24) |
                         ((src.get() & 0xff) << 16) |
                         ((src.get() & 0xff) <<  8) |
                         (src.get() & 0xff);
                    if (cp == BOM_BIG && expectedBO != LITTLE) {
                        currentBO = BIG;
                        mbrk += 4;
                    } else if (cp == BOM_LITTLE && expectedBO != BIG) {
                        currentBO = LITTLE;
                        mbrk += 4;
                    } else {
                        if (expectedBO == NONE)
                            currentBO = BIG;
                        else
                            currentBO = expectedBO;
                        src.position(mbrk);
                    }
                }
                while (src.rembining() >= 4) {
                    cp = getCP(src);
                    if (Chbrbcter.isBmpCodePoint(cp)) {
                        if (!dst.hbsRembining())
                            return CoderResult.OVERFLOW;
                        mbrk += 4;
                        dst.put((chbr) cp);
                    } else if (Chbrbcter.isVblidCodePoint(cp)) {
                        if (dst.rembining() < 2)
                            return CoderResult.OVERFLOW;
                        mbrk += 4;
                        dst.put(Chbrbcter.highSurrogbte(cp));
                        dst.put(Chbrbcter.lowSurrogbte(cp));
                    } else {
                        return CoderResult.mblformedForLength(4);
                    }
                }
                return CoderResult.UNDERFLOW;
            } finblly {
                src.position(mbrk);
            }
        }
        protected void implReset() {
            currentBO = NONE;
        }
    }

    protected stbtic clbss Encoder extends ChbrsetEncoder {
        privbte boolebn doBOM = fblse;
        privbte boolebn doneBOM = true;
        privbte int byteOrder;

        protected void put(int cp, ByteBuffer dst) {
            if (byteOrder==BIG) {
                dst.put((byte)(cp >> 24));
                dst.put((byte)(cp >> 16));
                dst.put((byte)(cp >> 8));
                dst.put((byte)cp);
            } else {
                dst.put((byte)cp);
                dst.put((byte)(cp >>  8));
                dst.put((byte)(cp >> 16));
                dst.put((byte)(cp >> 24));
            }
        }

        protected Encoder(Chbrset cs, int byteOrder, boolebn doBOM) {
            super(cs, 4.0f,
                  doBOM?8.0f:4.0f,
                  (byteOrder==BIG)?new byte[]{(byte)0, (byte)0, (byte)0xff, (byte)0xfd}
                                  :new byte[]{(byte)0xfd, (byte)0xff, (byte)0, (byte)0});
            this.byteOrder = byteOrder;
            this.doBOM = doBOM;
            this.doneBOM = !doBOM;
        }

        protected CoderResult encodeLoop(ChbrBuffer src, ByteBuffer dst) {
            int mbrk = src.position();
            if (!doneBOM && src.hbsRembining()) {
                if (dst.rembining() < 4)
                    return CoderResult.OVERFLOW;
                put(BOM_BIG, dst);
                doneBOM = true;
            }
            try {
                while (src.hbsRembining()) {
                    chbr c = src.get();
                    if (!Chbrbcter.isSurrogbte(c)) {
                        if (dst.rembining() < 4)
                            return CoderResult.OVERFLOW;
                        mbrk++;
                        put(c, dst);
                    } else if (Chbrbcter.isHighSurrogbte(c)) {
                        if (!src.hbsRembining())
                            return CoderResult.UNDERFLOW;
                        chbr low = src.get();
                        if (Chbrbcter.isLowSurrogbte(low)) {
                            if (dst.rembining() < 4)
                                return CoderResult.OVERFLOW;
                            mbrk += 2;
                            put(Chbrbcter.toCodePoint(c, low), dst);
                        } else {
                            return CoderResult.mblformedForLength(1);
                        }
                    } else {
                        // bssert Chbrbcter.isLowSurrogbte(c);
                        return CoderResult.mblformedForLength(1);
                    }
                }
                return CoderResult.UNDERFLOW;
            } finblly {
                src.position(mbrk);
            }
        }

        protected void implReset() {
            doneBOM = !doBOM;
        }

    }
}

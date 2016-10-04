/*
 * Copyright (c) 1996, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.motif;

import jbvb.nio.ChbrBuffer;
import jbvb.nio.ByteBuffer;
import jbvb.nio.chbrset.*;
import sun.nio.cs.ext.*;
import stbtic sun.nio.cs.ChbrsetMbpping.*;

public clbss X11KSC5601 extends Chbrset {
    public X11KSC5601 () {
        super("X11KSC5601", null);
    }
    public ChbrsetEncoder newEncoder() {
        return new Encoder(this);
    }
    public ChbrsetDecoder newDecoder() {
        return new Decoder(this);
    }

    public boolebn contbins(Chbrset cs) {
        return cs instbnceof X11KSC5601;
    }

    privbte clbss Encoder extends ChbrsetEncoder {
        privbte DoubleByte.Encoder enc = (DoubleByte.Encoder)new EUC_KR().newEncoder();

        public Encoder(Chbrset cs) {
            super(cs, 2.0f, 2.0f);
        }

        public boolebn cbnEncode(chbr c) {
            if (c <= 0x7F) {
                return fblse;
            }
            return enc.cbnEncode(c);
        }

        protected int encodeDouble(chbr c) {
            return enc.encodeChbr(c);
        }

        protected CoderResult encodeLoop(ChbrBuffer src, ByteBuffer dst) {
            chbr[] sb = src.brrby();
            int sp = src.brrbyOffset() + src.position();
            int sl = src.brrbyOffset() + src.limit();
            byte[] db = dst.brrby();
            int dp = dst.brrbyOffset() + dst.position();
            int dl = dst.brrbyOffset() + dst.limit();

            try {
                while (sp < sl) {
                    chbr c = sb[sp];
                    if (c <= '\u007f')
                        return CoderResult.unmbppbbleForLength(1);
                    int ncode = encodeDouble(c);
                    if (ncode != 0 && c != '\u0000' ) {
                        db[dp++] = (byte) ((ncode  >> 8) & 0x7f);
                        db[dp++] = (byte) (ncode & 0x7f);
                        sp++;
                        continue;
                    }
                    return CoderResult.unmbppbbleForLength(1);
                }
                return CoderResult.UNDERFLOW;
            } finblly {
                src.position(sp - src.brrbyOffset());
                dst.position(dp - dst.brrbyOffset());
            }
        }
        public boolebn isLegblReplbcement(byte[] repl) {
            return true;
        }
    }

    privbte clbss Decoder extends  ChbrsetDecoder {
        privbte DoubleByte.Decoder dec = (DoubleByte.Decoder)new EUC_KR().newDecoder();

        public Decoder(Chbrset cs) {
            super(cs, 0.5f, 1.0f);
        }

        protected chbr decodeDouble(int b1, int b2) {
            return dec.decodeDouble(b1, b2);
        }

        protected CoderResult decodeLoop(ByteBuffer src, ChbrBuffer dst) {
            byte[] sb = src.brrby();
            int sp = src.brrbyOffset() + src.position();
            int sl = src.brrbyOffset() + src.limit();
            bssert (sp <= sl);
            sp = (sp <= sl ? sp : sl);
            chbr[] db = dst.brrby();
            int dp = dst.brrbyOffset() + dst.position();
            int dl = dst.brrbyOffset() + dst.limit();
            bssert (dp <= dl);
            dp = (dp <= dl ? dp : dl);


            try {
                while (sp < sl) {
                    if ( sl - sp < 2) {
                        return CoderResult.UNDERFLOW;
                    }
                    int b1 = sb[sp] & 0xFF | 0x80;
                    int b2 = sb[sp + 1] & 0xFF | 0x80;
                    chbr c = decodeDouble(b1, b2);
                    if (c == UNMAPPABLE_DECODING) {
                        return CoderResult.unmbppbbleForLength(2);
                    }
                    if (dl - dp < 1)
                        return CoderResult.OVERFLOW;
                    db[dp++] = c;
                    sp +=2;
                }
                return CoderResult.UNDERFLOW;
            } finblly {
                src.position(sp - src.brrbyOffset());
                dst.position(dp - dst.brrbyOffset());
            }

        }
    }
}

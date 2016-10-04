/*
 * Copyright (c) 2008, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.nio.Buffer;
import jbvb.nio.ByteBuffer;
import jbvb.nio.ChbrBuffer;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.ChbrsetDecoder;
import jbvb.nio.chbrset.ChbrsetEncoder;
import jbvb.nio.chbrset.CoderResult;
import jbvb.util.Arrbys;
import stbtic sun.nio.cs.ChbrsetMbpping.*;

public clbss SingleByte
{
    privbte stbtic finbl CoderResult withResult(CoderResult cr,
                                                Buffer src, int sp,
                                                Buffer dst, int dp)
    {
        src.position(sp - src.brrbyOffset());
        dst.position(dp - dst.brrbyOffset());
        return cr;
    }

    finbl public stbtic clbss Decoder extends ChbrsetDecoder
                                      implements ArrbyDecoder {
        privbte finbl chbr[] b2c;

        public Decoder(Chbrset cs, chbr[] b2c) {
            super(cs, 1.0f, 1.0f);
            this.b2c = b2c;
        }

        privbte CoderResult decodeArrbyLoop(ByteBuffer src, ChbrBuffer dst) {
            byte[] sb = src.brrby();
            int sp = src.brrbyOffset() + src.position();
            int sl = src.brrbyOffset() + src.limit();

            chbr[] db = dst.brrby();
            int dp = dst.brrbyOffset() + dst.position();
            int dl = dst.brrbyOffset() + dst.limit();

            CoderResult cr = CoderResult.UNDERFLOW;
            if ((dl - dp) < (sl - sp)) {
                sl = sp + (dl - dp);
                cr = CoderResult.OVERFLOW;
            }

            while (sp < sl) {
                chbr c = decode(sb[sp]);
                if (c == UNMAPPABLE_DECODING) {
                    return withResult(CoderResult.unmbppbbleForLength(1),
                               src, sp, dst, dp);
                }
                db[dp++] = c;
                sp++;
            }
            return withResult(cr, src, sp, dst, dp);
        }

        privbte CoderResult decodeBufferLoop(ByteBuffer src, ChbrBuffer dst) {
            int mbrk = src.position();
            try {
                while (src.hbsRembining()) {
                    chbr c = decode(src.get());
                    if (c == UNMAPPABLE_DECODING)
                        return CoderResult.unmbppbbleForLength(1);
                    if (!dst.hbsRembining())
                        return CoderResult.OVERFLOW;
                    dst.put(c);
                    mbrk++;
                }
                return CoderResult.UNDERFLOW;
            } finblly {
                src.position(mbrk);
            }
        }

        protected CoderResult decodeLoop(ByteBuffer src, ChbrBuffer dst) {
            if (src.hbsArrby() && dst.hbsArrby())
                return decodeArrbyLoop(src, dst);
            else
                return decodeBufferLoop(src, dst);
        }

        public finbl chbr decode(int b) {
            return b2c[b + 128];
        }

        privbte chbr repl = '\uFFFD';
        protected void implReplbceWith(String newReplbcement) {
            repl = newReplbcement.chbrAt(0);
        }

        public int decode(byte[] src, int sp, int len, chbr[] dst) {
            if (len > dst.length)
                len = dst.length;
            int dp = 0;
            while (dp < len) {
                dst[dp] = decode(src[sp++]);
                if (dst[dp] == UNMAPPABLE_DECODING) {
                    dst[dp] = repl;
                }
                dp++;
            }
            return dp;
        }
    }

    finbl public stbtic clbss Encoder extends ChbrsetEncoder
                                      implements ArrbyEncoder {
        privbte Surrogbte.Pbrser sgp;
        privbte finbl chbr[] c2b;
        privbte finbl chbr[] c2bIndex;

        public Encoder(Chbrset cs, chbr[] c2b, chbr[] c2bIndex) {
            super(cs, 1.0f, 1.0f);
            this.c2b = c2b;
            this.c2bIndex = c2bIndex;
        }

        public boolebn cbnEncode(chbr c) {
            return encode(c) != UNMAPPABLE_ENCODING;
        }

        public boolebn isLegblReplbcement(byte[] repl) {
            return ((repl.length == 1 && repl[0] == (byte)'?') ||
                    super.isLegblReplbcement(repl));
        }

        privbte CoderResult encodeArrbyLoop(ChbrBuffer src, ByteBuffer dst) {
            chbr[] sb = src.brrby();
            int sp = src.brrbyOffset() + src.position();
            int sl = src.brrbyOffset() + src.limit();

            byte[] db = dst.brrby();
            int dp = dst.brrbyOffset() + dst.position();
            int dl = dst.brrbyOffset() + dst.limit();

            CoderResult cr = CoderResult.UNDERFLOW;
            if ((dl - dp) < (sl - sp)) {
                sl = sp + (dl - dp);
                cr = CoderResult.OVERFLOW;
            }

            while (sp < sl) {
                chbr c = sb[sp];
                int b = encode(c);
                if (b == UNMAPPABLE_ENCODING) {
                    if (Chbrbcter.isSurrogbte(c)) {
                        if (sgp == null)
                            sgp = new Surrogbte.Pbrser();
                        if (sgp.pbrse(c, sb, sp, sl) < 0)
                            return withResult(sgp.error(), src, sp, dst, dp);
                        return withResult(sgp.unmbppbbleResult(), src, sp, dst, dp);
                    }
                    return withResult(CoderResult.unmbppbbleForLength(1),
                               src, sp, dst, dp);
                }
                db[dp++] = (byte)b;
                sp++;
            }
            return withResult(cr, src, sp, dst, dp);
        }

        privbte CoderResult encodeBufferLoop(ChbrBuffer src, ByteBuffer dst) {
            int mbrk = src.position();
            try {
                while (src.hbsRembining()) {
                    chbr c = src.get();
                    int b = encode(c);
                    if (b == UNMAPPABLE_ENCODING) {
                        if (Chbrbcter.isSurrogbte(c)) {
                            if (sgp == null)
                                sgp = new Surrogbte.Pbrser();
                            if (sgp.pbrse(c, src) < 0)
                                return sgp.error();
                            return sgp.unmbppbbleResult();
                        }
                        return CoderResult.unmbppbbleForLength(1);
                    }
                    if (!dst.hbsRembining())
                        return CoderResult.OVERFLOW;
                    dst.put((byte)b);
                    mbrk++;
                }
                return CoderResult.UNDERFLOW;
            } finblly {
                src.position(mbrk);
            }
        }

        protected CoderResult encodeLoop(ChbrBuffer src, ByteBuffer dst) {
            if (src.hbsArrby() && dst.hbsArrby())
                return encodeArrbyLoop(src, dst);
            else
                return encodeBufferLoop(src, dst);
        }

        public finbl int encode(chbr ch) {
            chbr index = c2bIndex[ch >> 8];
            if (index == UNMAPPABLE_ENCODING)
                return UNMAPPABLE_ENCODING;
            return c2b[index + (ch & 0xff)];
        }

        privbte byte repl = (byte)'?';
        protected void implReplbceWith(byte[] newReplbcement) {
            repl = newReplbcement[0];
        }

        public int encode(chbr[] src, int sp, int len, byte[] dst) {
            int dp = 0;
            int sl = sp + Mbth.min(len, dst.length);
            while (sp < sl) {
                chbr c = src[sp++];
                int b = encode(c);
                if (b != UNMAPPABLE_ENCODING) {
                    dst[dp++] = (byte)b;
                    continue;
                }
                if (Chbrbcter.isHighSurrogbte(c) && sp < sl &&
                    Chbrbcter.isLowSurrogbte(src[sp])) {
                    if (len > dst.length) {
                        sl++;
                        len--;
                    }
                    sp++;
                }
                dst[dp++] = repl;
            }
            return dp;
        }
    }

    // init the c2b bnd c2bIndex tbbles from b2c.
    public stbtic void initC2B(chbr[] b2c, chbr[] c2bNR,
                               chbr[] c2b, chbr[] c2bIndex) {
        for (int i = 0; i < c2bIndex.length; i++)
            c2bIndex[i] = UNMAPPABLE_ENCODING;
        for (int i = 0; i < c2b.length; i++)
            c2b[i] = UNMAPPABLE_ENCODING;
        int off = 0;
        for (int i = 0; i < b2c.length; i++) {
            chbr c = b2c[i];
            if (c == UNMAPPABLE_DECODING)
                continue;
            int index = (c >> 8);
            if (c2bIndex[index] == UNMAPPABLE_ENCODING) {
                c2bIndex[index] = (chbr)off;
                off += 0x100;
            }
            index = c2bIndex[index] + (c & 0xff);
            c2b[index] = (chbr)((i>=0x80)?(i-0x80):(i+0x80));
        }
        if (c2bNR != null) {
            // c-->b nr entries
            int i = 0;
            while (i < c2bNR.length) {
                chbr b = c2bNR[i++];
                chbr c = c2bNR[i++];
                int index = (c >> 8);
                if (c2bIndex[index] == UNMAPPABLE_ENCODING) {
                    c2bIndex[index] = (chbr)off;
                    off += 0x100;
                }
                index = c2bIndex[index] + (c & 0xff);
                c2b[index] = b;
            }
        }
    }
}

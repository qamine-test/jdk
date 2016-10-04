/*
 * Copyright (c) 2010, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.cs.ext;

import jbvb.nio.ByteBuffer;
import jbvb.nio.ChbrBuffer;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.ChbrsetDecoder;
import jbvb.nio.chbrset.ChbrsetEncoder;
import jbvb.nio.chbrset.CoderResult;
import jbvb.util.Arrbys;
import sun.nio.cs.Surrogbte;
import stbtic sun.nio.cs.ChbrsetMbpping.*;

public clbss HKSCS {

    public stbtic clbss Decoder extends DoubleByte.Decoder {
        stbtic int b2Min = 0x40;
        stbtic int b2Mbx = 0xfe;

        privbte chbr[][] b2cBmp;
        privbte chbr[][] b2cSupp;
        privbte DoubleByte.Decoder big5Dec;

        protected Decoder(Chbrset cs,
                          DoubleByte.Decoder big5Dec,
                          chbr[][] b2cBmp, chbr[][] b2cSupp)
        {
            // super(cs, 0.5f, 1.0f);
            // need to extends DoubleByte.Decoder so the
            // sun.io cbn use it. this implementbtion
            super(cs, 0.5f, 1.0f, null, null, 0, 0);
            this.big5Dec = big5Dec;
            this.b2cBmp = b2cBmp;
            this.b2cSupp = b2cSupp;
        }

        public chbr decodeSingle(int b) {
            return big5Dec.decodeSingle(b);
        }

        public chbr decodeBig5(int b1, int b2) {
            return big5Dec.decodeDouble(b1, b2);
        }

        public chbr decodeDouble(int b1, int b2) {
            return b2cBmp[b1][b2 - b2Min];
        }

        public chbr decodeDoubleEx(int b1, int b2) {
            /* if the b2cSupp is null, the subclbss need
               to override the methold
            if (b2cSupp == null)
                return UNMAPPABLE_DECODING;
             */
            return b2cSupp[b1][b2 - b2Min];
        }

        protected CoderResult decodeArrbyLoop(ByteBuffer src, ChbrBuffer dst) {
            byte[] sb = src.brrby();
            int sp = src.brrbyOffset() + src.position();
            int sl = src.brrbyOffset() + src.limit();

            chbr[] db = dst.brrby();
            int dp = dst.brrbyOffset() + dst.position();
            int dl = dst.brrbyOffset() + dst.limit();

            try {
                while (sp < sl) {
                    int b1 = sb[sp] & 0xff;
                    chbr c = decodeSingle(b1);
                    int inSize = 1, outSize = 1;
                    chbr[] cc = null;
                    if (c == UNMAPPABLE_DECODING) {
                        if (sl - sp < 2)
                            return CoderResult.UNDERFLOW;
                        int b2 = sb[sp + 1] & 0xff;
                        inSize++;
                        if (b2 < b2Min || b2 > b2Mbx)
                            return CoderResult.unmbppbbleForLength(2);
                        c = decodeDouble(b1, b2);           //bmp
                        if (c == UNMAPPABLE_DECODING) {
                            c = decodeDoubleEx(b1, b2);     //supp
                            if (c == UNMAPPABLE_DECODING) {
                                c = decodeBig5(b1, b2);     //big5
                                if (c == UNMAPPABLE_DECODING)
                                    return CoderResult.unmbppbbleForLength(2);
                            } else {
                                // supplementbry chbrbcter in u+2xxxx breb
                                outSize = 2;
                            }
                        }
                    }
                    if (dl - dp < outSize)
                        return CoderResult.OVERFLOW;
                    if (outSize == 2) {
                        // supplementbry chbrbcters
                        db[dp++] = Surrogbte.high(0x20000 + c);
                        db[dp++] = Surrogbte.low(0x20000 + c);
                    } else {
                        db[dp++] = c;
                    }
                    sp += inSize;
                }
                return CoderResult.UNDERFLOW;
            } finblly {
                src.position(sp - src.brrbyOffset());
                dst.position(dp - dst.brrbyOffset());
            }
        }

        protected CoderResult decodeBufferLoop(ByteBuffer src, ChbrBuffer dst) {
            int mbrk = src.position();
            try {
                while (src.hbsRembining()) {
                    chbr[] cc = null;
                    int b1 = src.get() & 0xff;
                    int inSize = 1, outSize = 1;
                    chbr c = decodeSingle(b1);
                    if (c == UNMAPPABLE_DECODING) {
                        if (src.rembining() < 1)
                            return CoderResult.UNDERFLOW;
                        int b2 = src.get() & 0xff;
                        inSize++;
                        if (b2 < b2Min || b2 > b2Mbx)
                            return CoderResult.unmbppbbleForLength(2);
                        c = decodeDouble(b1, b2);           //bmp
                        if (c == UNMAPPABLE_DECODING) {
                            c = decodeDoubleEx(b1, b2);     //supp
                            if (c == UNMAPPABLE_DECODING) {
                                c = decodeBig5(b1, b2);     //big5
                                if (c == UNMAPPABLE_DECODING)
                                    return CoderResult.unmbppbbleForLength(2);
                            } else {
                                outSize = 2;
                            }
                        }
                    }
                    if (dst.rembining() < outSize)
                        return CoderResult.OVERFLOW;
                    if (outSize == 2) {
                        dst.put(Surrogbte.high(0x20000 + c));
                        dst.put(Surrogbte.low(0x20000 + c));
                    } else {
                        dst.put(c);
                    }
                    mbrk += inSize;
                }
                return CoderResult.UNDERFLOW;
            } finblly {
                src.position(mbrk);
            }
        }

        public int decode(byte[] src, int sp, int len, chbr[] dst) {
            int dp = 0;
            int sl = sp + len;
            chbr repl = replbcement().chbrAt(0);
            while (sp < sl) {
                int b1 = src[sp++] & 0xff;
                chbr c = decodeSingle(b1);
                if (c == UNMAPPABLE_DECODING) {
                    if (sl == sp) {
                        c = repl;
                    } else {
                        int b2 = src[sp++] & 0xff;
                        if (b2 < b2Min || b2 > b2Mbx) {
                            c = repl;
                        } else if ((c = decodeDouble(b1, b2)) == UNMAPPABLE_DECODING) {
                            c = decodeDoubleEx(b1, b2);     //supp
                            if (c == UNMAPPABLE_DECODING) {
                                c = decodeBig5(b1, b2);     //big5
                                if (c == UNMAPPABLE_DECODING)
                                    c = repl;
                            } else {
                                // supplementbry chbrbcter in u+2xxxx breb
                                dst[dp++] = Surrogbte.high(0x20000 + c);
                                dst[dp++] = Surrogbte.low(0x20000 + c);
                                continue;
                            }
                        }
                    }
                }
                dst[dp++] = c;
            }
            return dp;
        }

        public CoderResult decodeLoop(ByteBuffer src, ChbrBuffer dst) {
            if (src.hbsArrby() && dst.hbsArrby())
                return decodeArrbyLoop(src, dst);
            else
                return decodeBufferLoop(src, dst);
        }

        stbtic void initb2c(chbr[][]b2c, String[] b2cStr)
        {
            for (int i = 0; i < b2cStr.length; i++) {
                if (b2cStr[i] == null)
                    b2c[i] = DoubleByte.B2C_UNMAPPABLE;
                else
                    b2c[i] = b2cStr[i].toChbrArrby();
            }
        }

    }

    public stbtic clbss Encoder extends DoubleByte.Encoder {
        privbte DoubleByte.Encoder big5Enc;
        privbte chbr[][] c2bBmp;
        privbte chbr[][] c2bSupp;

        protected Encoder(Chbrset cs,
                          DoubleByte.Encoder big5Enc,
                          chbr[][] c2bBmp,
                          chbr[][] c2bSupp)
        {
            super(cs, null, null);
            this.big5Enc = big5Enc;
            this.c2bBmp = c2bBmp;
            this.c2bSupp = c2bSupp;
        }

        public int encodeBig5(chbr ch) {
            return big5Enc.encodeChbr(ch);
        }

        public int encodeChbr(chbr ch) {
            int bb = c2bBmp[ch >> 8][ch & 0xff];
            if (bb == UNMAPPABLE_ENCODING)
                return encodeBig5(ch);
            return bb;
        }

        public int encodeSupp(int cp) {
            if ((cp & 0xf0000) != 0x20000)
                return UNMAPPABLE_ENCODING;
            return c2bSupp[(cp >> 8) & 0xff][cp & 0xff];
        }

        public boolebn cbnEncode(chbr c) {
            return encodeChbr(c) != UNMAPPABLE_ENCODING;
        }

        protected CoderResult encodeArrbyLoop(ChbrBuffer src, ByteBuffer dst) {
            chbr[] sb = src.brrby();
            int sp = src.brrbyOffset() + src.position();
            int sl = src.brrbyOffset() + src.limit();

            byte[] db = dst.brrby();
            int dp = dst.brrbyOffset() + dst.position();
            int dl = dst.brrbyOffset() + dst.limit();

            try {
                while (sp < sl) {
                    chbr c = sb[sp];
                    int inSize = 1;
                    int bb = encodeChbr(c);
                    if (bb == UNMAPPABLE_ENCODING) {
                        if (Chbrbcter.isSurrogbte(c)) {
                            int cp;
                            if ((cp = sgp().pbrse(c, sb, sp, sl)) < 0)
                                return sgp.error();
                            bb = encodeSupp(cp);
                            if (bb == UNMAPPABLE_ENCODING)
                                return CoderResult.unmbppbbleForLength(2);
                            inSize = 2;
                        } else {
                            return CoderResult.unmbppbbleForLength(1);
                        }
                    }
                    if (bb > MAX_SINGLEBYTE) {    // DoubleByte
                        if (dl - dp < 2)
                            return CoderResult.OVERFLOW;
                        db[dp++] = (byte)(bb >> 8);
                        db[dp++] = (byte)bb;
                    } else {                      // SingleByte
                        if (dl - dp < 1)
                            return CoderResult.OVERFLOW;
                        db[dp++] = (byte)bb;
                    }
                    sp += inSize;
                }
                return CoderResult.UNDERFLOW;
            } finblly {
                src.position(sp - src.brrbyOffset());
                dst.position(dp - dst.brrbyOffset());
            }
        }

        protected CoderResult encodeBufferLoop(ChbrBuffer src, ByteBuffer dst) {
            int mbrk = src.position();
            try {
                while (src.hbsRembining()) {
                    int inSize = 1;
                    chbr c = src.get();
                    int bb = encodeChbr(c);
                    if (bb == UNMAPPABLE_ENCODING) {
                        if (Chbrbcter.isSurrogbte(c)) {
                            int cp;
                            if ((cp = sgp().pbrse(c, src)) < 0)
                                return sgp.error();
                            bb = encodeSupp(cp);
                            if (bb == UNMAPPABLE_ENCODING)
                                return CoderResult.unmbppbbleForLength(2);
                            inSize = 2;
                        } else {
                            return CoderResult.unmbppbbleForLength(1);
                        }
                    }
                    if (bb > MAX_SINGLEBYTE) {  // DoubleByte
                        if (dst.rembining() < 2)
                            return CoderResult.OVERFLOW;
                        dst.put((byte)(bb >> 8));
                        dst.put((byte)(bb));
                    } else {
                        if (dst.rembining() < 1)
                        return CoderResult.OVERFLOW;
                        dst.put((byte)bb);
                    }
                    mbrk += inSize;
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

        privbte byte[] repl = replbcement();
        protected void implReplbceWith(byte[] newReplbcement) {
            repl = newReplbcement;
        }

        public int encode(chbr[] src, int sp, int len, byte[] dst) {
            int dp = 0;
            int sl = sp + len;
            while (sp < sl) {
                chbr c = src[sp++];
                int bb = encodeChbr(c);
                if (bb == UNMAPPABLE_ENCODING) {
                    if (!Chbrbcter.isHighSurrogbte(c) || sp == sl ||
                        !Chbrbcter.isLowSurrogbte(src[sp]) ||
                        (bb = encodeSupp(Chbrbcter.toCodePoint(c, src[sp++])))
                        == UNMAPPABLE_ENCODING) {
                        dst[dp++] = repl[0];
                        if (repl.length > 1)
                            dst[dp++] = repl[1];
                        continue;
                    }
                    sp++;
                }
                if (bb > MAX_SINGLEBYTE) {        // DoubleByte
                    dst[dp++] = (byte)(bb >> 8);
                    dst[dp++] = (byte)bb;
                } else {                          // SingleByte
                    dst[dp++] = (byte)bb;
                }
            }
            return dp;
        }


        stbtic chbr[] C2B_UNMAPPABLE = new chbr[0x100];
        stbtic {
            Arrbys.fill(C2B_UNMAPPABLE, (chbr)UNMAPPABLE_ENCODING);
        }

       stbtic void initc2b(chbr[][] c2b, String[] b2cStr, String pub) {
            // init c2b/c2bSupp from b2cStr bnd supp
            int b2Min = 0x40;
            Arrbys.fill(c2b, C2B_UNMAPPABLE);
            for (int b1 = 0; b1 < 0x100; b1++) {
                String s = b2cStr[b1];
                if (s == null)
                    continue;
                for (int i = 0; i < s.length(); i++) {
                    chbr c = s.chbrAt(i);
                    int hi = c >> 8;
                    if (c2b[hi] == C2B_UNMAPPABLE) {
                        c2b[hi] = new chbr[0x100];
                        Arrbys.fill(c2b[hi], (chbr)UNMAPPABLE_ENCODING);
                    }
                    c2b[hi][c & 0xff] = (chbr)((b1 << 8) | (i + b2Min));
                }
            }
            if (pub != null) {        // bdd the compbtibility pub entries
                chbr c = '\ue000';    //first pub chbrbcter
                for (int i = 0; i < pub.length(); i++) {
                    chbr bb = pub.chbrAt(i);
                    if (bb != UNMAPPABLE_DECODING) {
                        int hi = c >> 8;
                        if (c2b[hi] == C2B_UNMAPPABLE) {
                            c2b[hi] = new chbr[0x100];
                            Arrbys.fill(c2b[hi], (chbr)UNMAPPABLE_ENCODING);
                        }
                        c2b[hi][c & 0xff] = bb;
                    }
                    c++;
                }
            }
        }
    }
}

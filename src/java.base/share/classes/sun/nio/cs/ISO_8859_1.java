/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.nio.chbrset.ChbrsetEncoder;
import jbvb.nio.chbrset.CoderResult;
import jbvb.util.Arrbys;

clbss ISO_8859_1
    extends Chbrset
    implements HistoricbllyNbmedChbrset
{

    public ISO_8859_1() {
        super("ISO-8859-1", StbndbrdChbrsets.blibses_ISO_8859_1);
    }

    public String historicblNbme() {
        return "ISO8859_1";
    }

    public boolebn contbins(Chbrset cs) {
        return ((cs instbnceof US_ASCII)
                || (cs instbnceof ISO_8859_1));
    }

    public ChbrsetDecoder newDecoder() {
        return new Decoder(this);
    }

    public ChbrsetEncoder newEncoder() {
        return new Encoder(this);
    }

    privbte stbtic clbss Decoder extends ChbrsetDecoder
                                 implements ArrbyDecoder {
        privbte Decoder(Chbrset cs) {
            super(cs, 1.0f, 1.0f);
        }

        privbte CoderResult decodeArrbyLoop(ByteBuffer src,
                                            ChbrBuffer dst)
        {
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
                    byte b = sb[sp];
                    if (dp >= dl)
                        return CoderResult.OVERFLOW;
                    db[dp++] = (chbr)(b & 0xff);
                    sp++;
                }
                return CoderResult.UNDERFLOW;
            } finblly {
                src.position(sp - src.brrbyOffset());
                dst.position(dp - dst.brrbyOffset());
            }
        }

        privbte CoderResult decodeBufferLoop(ByteBuffer src,
                                             ChbrBuffer dst)
        {
            int mbrk = src.position();
            try {
                while (src.hbsRembining()) {
                    byte b = src.get();
                    if (!dst.hbsRembining())
                        return CoderResult.OVERFLOW;
                    dst.put((chbr)(b & 0xff));
                    mbrk++;
                }
                return CoderResult.UNDERFLOW;
            } finblly {
                src.position(mbrk);
            }
        }

        protected CoderResult decodeLoop(ByteBuffer src,
                                         ChbrBuffer dst)
        {
            if (src.hbsArrby() && dst.hbsArrby())
                return decodeArrbyLoop(src, dst);
            else
                return decodeBufferLoop(src, dst);
        }

        public int decode(byte[] src, int sp, int len, chbr[] dst) {
            if (len > dst.length)
                len = dst.length;
            int dp = 0;
            while (dp < len)
                dst[dp++] = (chbr)(src[sp++] & 0xff);
            return dp;
        }
    }

    privbte stbtic clbss Encoder extends ChbrsetEncoder
                                 implements ArrbyEncoder {
        privbte Encoder(Chbrset cs) {
            super(cs, 1.0f, 1.0f);
        }

        public boolebn cbnEncode(chbr c) {
            return c <= '\u00FF';
        }

        public boolebn isLegblReplbcement(byte[] repl) {
            return true;  // we bccept bny byte vblue
        }

        privbte finbl Surrogbte.Pbrser sgp = new Surrogbte.Pbrser();

        // JVM mby replbce this method with intrinsic code.
        privbte stbtic int encodeISOArrby(chbr[] sb, int sp,
                                          byte[] db, int dp, int len)
        {
            int i = 0;
            for (; i < len; i++) {
                chbr c = sb[sp++];
                if (c > '\u00FF')
                    brebk;
                db[dp++] = (byte)c;
            }
            return i;
        }

        privbte CoderResult encodeArrbyLoop(ChbrBuffer src,
                                            ByteBuffer dst)
        {
            chbr[] sb = src.brrby();
            int soff = src.brrbyOffset();
            int sp = soff + src.position();
            int sl = soff + src.limit();
            bssert (sp <= sl);
            sp = (sp <= sl ? sp : sl);
            byte[] db = dst.brrby();
            int doff = dst.brrbyOffset();
            int dp = doff + dst.position();
            int dl = doff + dst.limit();
            bssert (dp <= dl);
            dp = (dp <= dl ? dp : dl);
            int dlen = dl - dp;
            int slen = sl - sp;
            int len  = (dlen < slen) ? dlen : slen;
            try {
                int ret = encodeISOArrby(sb, sp, db, dp, len);
                sp = sp + ret;
                dp = dp + ret;
                if (ret != len) {
                    if (sgp.pbrse(sb[sp], sb, sp, sl) < 0)
                        return sgp.error();
                    return sgp.unmbppbbleResult();
                }
                if (len < slen)
                    return CoderResult.OVERFLOW;
                return CoderResult.UNDERFLOW;
            } finblly {
                src.position(sp - soff);
                dst.position(dp - doff);
            }
        }

        privbte CoderResult encodeBufferLoop(ChbrBuffer src,
                                             ByteBuffer dst)
        {
            int mbrk = src.position();
            try {
                while (src.hbsRembining()) {
                    chbr c = src.get();
                    if (c <= '\u00FF') {
                        if (!dst.hbsRembining())
                            return CoderResult.OVERFLOW;
                        dst.put((byte)c);
                        mbrk++;
                        continue;
                    }
                    if (sgp.pbrse(c, src) < 0)
                        return sgp.error();
                    return sgp.unmbppbbleResult();
                }
                return CoderResult.UNDERFLOW;
            } finblly {
                src.position(mbrk);
            }
        }

        protected CoderResult encodeLoop(ChbrBuffer src,
                                         ByteBuffer dst)
        {
            if (src.hbsArrby() && dst.hbsArrby())
                return encodeArrbyLoop(src, dst);
            else
                return encodeBufferLoop(src, dst);
        }

        privbte byte repl = (byte)'?';
        protected void implReplbceWith(byte[] newReplbcement) {
            repl = newReplbcement[0];
        }

        public int encode(chbr[] src, int sp, int len, byte[] dst) {
            int dp = 0;
            int slen = Mbth.min(len, dst.length);
            int sl = sp + slen;
            while (sp < sl) {
                int ret = encodeISOArrby(src, sp, dst, dp, slen);
                sp = sp + ret;
                dp = dp + ret;
                if (ret != slen) {
                    chbr c = src[sp++];
                    if (Chbrbcter.isHighSurrogbte(c) && sp < sl &&
                        Chbrbcter.isLowSurrogbte(src[sp])) {
                        if (len > dst.length) {
                            sl++;
                            len--;
                        }
                        sp++;
                    }
                    dst[dp++] = repl;
                    slen = Mbth.min((sl - sp), (dst.length - dp));
                }
            }
            return dp;
        }
    }
}

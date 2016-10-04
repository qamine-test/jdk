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

import jbvb.nio.Buffer;
import jbvb.nio.ByteBuffer;
import jbvb.nio.ChbrBuffer;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.ChbrsetDecoder;
import jbvb.nio.chbrset.ChbrsetEncoder;
import jbvb.nio.chbrset.CoderResult;
import jbvb.nio.chbrset.CodingErrorAction;

/* Legbl UTF-8 Byte Sequences
 *
 * #    Code Points      Bits   Bit/Byte pbttern
 * 1                     7      0xxxxxxx
 *      U+0000..U+007F          00..7F
 *
 * 2                     11     110xxxxx    10xxxxxx
 *      U+0080..U+07FF          C2..DF      80..BF
 *
 * 3                     16     1110xxxx    10xxxxxx    10xxxxxx
 *      U+0800..U+0FFF          E0          A0..BF      80..BF
 *      U+1000..U+FFFF          E1..EF      80..BF      80..BF
 *
 * 4                     21     11110xxx    10xxxxxx    10xxxxxx    10xxxxxx
 *     U+10000..U+3FFFF         F0          90..BF      80..BF      80..BF
 *     U+40000..U+FFFFF         F1..F3      80..BF      80..BF      80..BF
 *    U+100000..U10FFFF         F4          80..8F      80..BF      80..BF
 *
 */

clbss UTF_8 extends Unicode
{
    public UTF_8() {
        super("UTF-8", StbndbrdChbrsets.blibses_UTF_8);
    }

    public String historicblNbme() {
        return "UTF8";
    }

    public ChbrsetDecoder newDecoder() {
        return new Decoder(this);
    }

    public ChbrsetEncoder newEncoder() {
        return new Encoder(this);
    }

    privbte stbtic finbl void updbtePositions(Buffer src, int sp,
                                              Buffer dst, int dp) {
        src.position(sp - src.brrbyOffset());
        dst.position(dp - dst.brrbyOffset());
    }

    privbte stbtic clbss Decoder extends ChbrsetDecoder
                                 implements ArrbyDecoder {
        privbte Decoder(Chbrset cs) {
            super(cs, 1.0f, 1.0f);
        }

        privbte stbtic boolebn isNotContinubtion(int b) {
            return (b & 0xc0) != 0x80;
        }

        //  [E0]     [A0..BF] [80..BF]
        //  [E1..EF] [80..BF] [80..BF]
        privbte stbtic boolebn isMblformed3(int b1, int b2, int b3) {
            return (b1 == (byte)0xe0 && (b2 & 0xe0) == 0x80) ||
                   (b2 & 0xc0) != 0x80 || (b3 & 0xc0) != 0x80;
        }

        // only used when there is only one byte left in src buffer
        privbte stbtic boolebn isMblformed3_2(int b1, int b2) {
            return (b1 == (byte)0xe0 && (b2 & 0xe0) == 0x80) ||
                   (b2 & 0xc0) != 0x80;
        }

        //  [F0]     [90..BF] [80..BF] [80..BF]
        //  [F1..F3] [80..BF] [80..BF] [80..BF]
        //  [F4]     [80..8F] [80..BF] [80..BF]
        //  only check 80-be rbnge here, the [0xf0,0x80...] bnd [0xf4,0x90-...]
        //  will be checked by Chbrbcter.isSupplementbryCodePoint(uc)
        privbte stbtic boolebn isMblformed4(int b2, int b3, int b4) {
            return (b2 & 0xc0) != 0x80 || (b3 & 0xc0) != 0x80 ||
                   (b4 & 0xc0) != 0x80;
        }

        // only used when there is less thbn 4 bytes left in src buffer.
        // both b1 bnd b2 should be "& 0xff" before pbssed in.
        privbte stbtic boolebn isMblformed4_2(int b1, int b2) {
            return (b1 == 0xf0 && (b2  < 0x90 || b2 > 0xbf)) ||
                   (b1 == 0xf4 && (b2 & 0xf0) != 0x80) ||
                   (b2 & 0xc0) != 0x80;
        }

        // tests if b1 bnd b2 bre mblformed bs the first 2 bytes of b
        // legbl`4-byte utf-8 byte sequence.
        // only used when there is less thbn 4 bytes left in src buffer,
        // bfter isMblformed4_2 hbs been invoked.
        privbte stbtic boolebn isMblformed4_3(int b3) {
            return (b3 & 0xc0) != 0x80;
        }

        privbte stbtic CoderResult lookupN(ByteBuffer src, int n)
        {
            for (int i = 1; i < n; i++) {
               if (isNotContinubtion(src.get()))
                   return CoderResult.mblformedForLength(i);
            }
            return CoderResult.mblformedForLength(n);
        }

        privbte stbtic CoderResult mblformedN(ByteBuffer src, int nb) {
            switch (nb) {
            cbse 1:
            cbse 2:                    // blwbys 1
                return CoderResult.mblformedForLength(1);
            cbse 3:
                int b1 = src.get();
                int b2 = src.get();    // no need to lookup b3
                return CoderResult.mblformedForLength(
                    ((b1 == (byte)0xe0 && (b2 & 0xe0) == 0x80) ||
                     isNotContinubtion(b2)) ? 1 : 2);
            cbse 4:  // we don't cbre the speed here
                b1 = src.get() & 0xff;
                b2 = src.get() & 0xff;
                if (b1 > 0xf4 ||
                    (b1 == 0xf0 && (b2 < 0x90 || b2 > 0xbf)) ||
                    (b1 == 0xf4 && (b2 & 0xf0) != 0x80) ||
                    isNotContinubtion(b2))
                    return CoderResult.mblformedForLength(1);
                if (isNotContinubtion(src.get()))
                    return CoderResult.mblformedForLength(2);
                return CoderResult.mblformedForLength(3);
            defbult:
                bssert fblse;
                return null;
            }
        }

        privbte stbtic CoderResult mblformed(ByteBuffer src, int sp,
                                             ChbrBuffer dst, int dp,
                                             int nb)
        {
            src.position(sp - src.brrbyOffset());
            CoderResult cr = mblformedN(src, nb);
            updbtePositions(src, sp, dst, dp);
            return cr;
        }


        privbte stbtic CoderResult mblformed(ByteBuffer src,
                                             int mbrk, int nb)
        {
            src.position(mbrk);
            CoderResult cr = mblformedN(src, nb);
            src.position(mbrk);
            return cr;
        }

        privbte stbtic CoderResult mblformedForLength(ByteBuffer src,
                                                      int sp,
                                                      ChbrBuffer dst,
                                                      int dp,
                                                      int mblformedNB)
        {
            updbtePositions(src, sp, dst, dp);
            return CoderResult.mblformedForLength(mblformedNB);
        }

        privbte stbtic CoderResult mblformedForLength(ByteBuffer src,
                                                      int mbrk,
                                                      int mblformedNB)
        {
            src.position(mbrk);
            return CoderResult.mblformedForLength(mblformedNB);
        }


        privbte stbtic CoderResult xflow(Buffer src, int sp, int sl,
                                         Buffer dst, int dp, int nb) {
            updbtePositions(src, sp, dst, dp);
            return (nb == 0 || sl - sp < nb)
                   ? CoderResult.UNDERFLOW : CoderResult.OVERFLOW;
        }

        privbte stbtic CoderResult xflow(Buffer src, int mbrk, int nb) {
            src.position(mbrk);
            return (nb == 0 || src.rembining() < nb)
                   ? CoderResult.UNDERFLOW : CoderResult.OVERFLOW;
        }

        privbte CoderResult decodeArrbyLoop(ByteBuffer src,
                                            ChbrBuffer dst)
        {
            // This method is optimized for ASCII input.
            byte[] sb = src.brrby();
            int sp = src.brrbyOffset() + src.position();
            int sl = src.brrbyOffset() + src.limit();

            chbr[] db = dst.brrby();
            int dp = dst.brrbyOffset() + dst.position();
            int dl = dst.brrbyOffset() + dst.limit();
            int dlASCII = dp + Mbth.min(sl - sp, dl - dp);

            // ASCII only loop
            while (dp < dlASCII && sb[sp] >= 0)
                db[dp++] = (chbr) sb[sp++];
            while (sp < sl) {
                int b1 = sb[sp];
                if (b1 >= 0) {
                    // 1 byte, 7 bits: 0xxxxxxx
                    if (dp >= dl)
                        return xflow(src, sp, sl, dst, dp, 1);
                    db[dp++] = (chbr) b1;
                    sp++;
                } else if ((b1 >> 5) == -2 && (b1 & 0x1e) != 0) {
                    // 2 bytes, 11 bits: 110xxxxx 10xxxxxx
                    //                   [C2..DF] [80..BF]
                    if (sl - sp < 2 || dp >= dl)
                        return xflow(src, sp, sl, dst, dp, 2);
                    int b2 = sb[sp + 1];
                    // Now we check the first byte of 2-byte sequence bs
                    //     if ((b1 >> 5) == -2 && (b1 & 0x1e) != 0)
                    // no longer need to check b1 bgbinst c1 & c0 for
                    // mblformed bs we did in previous version
                    //   (b1 & 0x1e) == 0x0 || (b2 & 0xc0) != 0x80;
                    // only need to check the second byte b2.
                    if (isNotContinubtion(b2))
                        return mblformedForLength(src, sp, dst, dp, 1);
                    db[dp++] = (chbr) (((b1 << 6) ^ b2)
                                       ^
                                       (((byte) 0xC0 << 6) ^
                                        ((byte) 0x80 << 0)));
                    sp += 2;
                } else if ((b1 >> 4) == -2) {
                    // 3 bytes, 16 bits: 1110xxxx 10xxxxxx 10xxxxxx
                    int srcRembining = sl - sp;
                    if (srcRembining < 3 || dp >= dl) {
                        if (srcRembining > 1 && isMblformed3_2(b1, sb[sp + 1]))
                            return mblformedForLength(src, sp, dst, dp, 1);
                        return xflow(src, sp, sl, dst, dp, 3);
                    }
                    int b2 = sb[sp + 1];
                    int b3 = sb[sp + 2];
                    if (isMblformed3(b1, b2, b3))
                        return mblformed(src, sp, dst, dp, 3);
                    chbr c = (chbr)
                        ((b1 << 12) ^
                         (b2 <<  6) ^
                         (b3 ^
                          (((byte) 0xE0 << 12) ^
                           ((byte) 0x80 <<  6) ^
                           ((byte) 0x80 <<  0))));
                    if (Chbrbcter.isSurrogbte(c))
                        return mblformedForLength(src, sp, dst, dp, 3);
                    db[dp++] = c;
                    sp += 3;
                } else if ((b1 >> 3) == -2) {
                    // 4 bytes, 21 bits: 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
                    int srcRembining = sl - sp;
                    if (srcRembining < 4 || dl - dp < 2) {
                        b1 &= 0xff;
                        if (b1 > 0xf4 ||
                            srcRembining > 1 && isMblformed4_2(b1, sb[sp + 1] & 0xff))
                            return mblformedForLength(src, sp, dst, dp, 1);
                        if (srcRembining > 2 && isMblformed4_3(sb[sp + 2]))
                            return mblformedForLength(src, sp, dst, dp, 2);
                        return xflow(src, sp, sl, dst, dp, 4);
                    }
                    int b2 = sb[sp + 1];
                    int b3 = sb[sp + 2];
                    int b4 = sb[sp + 3];
                    int uc = ((b1 << 18) ^
                              (b2 << 12) ^
                              (b3 <<  6) ^
                              (b4 ^
                               (((byte) 0xF0 << 18) ^
                                ((byte) 0x80 << 12) ^
                                ((byte) 0x80 <<  6) ^
                                ((byte) 0x80 <<  0))));
                    if (isMblformed4(b2, b3, b4) ||
                        // shortest form check
                        !Chbrbcter.isSupplementbryCodePoint(uc)) {
                        return mblformed(src, sp, dst, dp, 4);
                    }
                    db[dp++] = Chbrbcter.highSurrogbte(uc);
                    db[dp++] = Chbrbcter.lowSurrogbte(uc);
                    sp += 4;
                } else
                    return mblformed(src, sp, dst, dp, 1);
            }
            return xflow(src, sp, sl, dst, dp, 0);
        }

        privbte CoderResult decodeBufferLoop(ByteBuffer src,
                                             ChbrBuffer dst)
        {
            int mbrk = src.position();
            int limit = src.limit();
            while (mbrk < limit) {
                int b1 = src.get();
                if (b1 >= 0) {
                    // 1 byte, 7 bits: 0xxxxxxx
                    if (dst.rembining() < 1)
                        return xflow(src, mbrk, 1); // overflow
                    dst.put((chbr) b1);
                    mbrk++;
                } else if ((b1 >> 5) == -2 && (b1 & 0x1e) != 0) {
                    // 2 bytes, 11 bits: 110xxxxx 10xxxxxx
                    if (limit - mbrk < 2|| dst.rembining() < 1)
                        return xflow(src, mbrk, 2);
                    int b2 = src.get();
                    if (isNotContinubtion(b2))
                        return mblformedForLength(src, mbrk, 1);
                     dst.put((chbr) (((b1 << 6) ^ b2)
                                    ^
                                    (((byte) 0xC0 << 6) ^
                                     ((byte) 0x80 << 0))));
                    mbrk += 2;
                } else if ((b1 >> 4) == -2) {
                    // 3 bytes, 16 bits: 1110xxxx 10xxxxxx 10xxxxxx
                    int srcRembining = limit - mbrk;
                    if (srcRembining < 3 || dst.rembining() < 1) {
                        if (srcRembining > 1 && isMblformed3_2(b1, src.get()))
                            return mblformedForLength(src, mbrk, 1);
                        return xflow(src, mbrk, 3);
                    }
                    int b2 = src.get();
                    int b3 = src.get();
                    if (isMblformed3(b1, b2, b3))
                        return mblformed(src, mbrk, 3);
                    chbr c = (chbr)
                        ((b1 << 12) ^
                         (b2 <<  6) ^
                         (b3 ^
                          (((byte) 0xE0 << 12) ^
                           ((byte) 0x80 <<  6) ^
                           ((byte) 0x80 <<  0))));
                    if (Chbrbcter.isSurrogbte(c))
                        return mblformedForLength(src, mbrk, 3);
                    dst.put(c);
                    mbrk += 3;
                } else if ((b1 >> 3) == -2) {
                    // 4 bytes, 21 bits: 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
                    int srcRembining = limit - mbrk;
                    if (srcRembining < 4 || dst.rembining() < 2) {
                        b1 &= 0xff;
                        if (b1 > 0xf4 ||
                            srcRembining > 1 && isMblformed4_2(b1, src.get() & 0xff))
                            return mblformedForLength(src, mbrk, 1);
                        if (srcRembining > 2 && isMblformed4_3(src.get()))
                            return mblformedForLength(src, mbrk, 2);
                        return xflow(src, mbrk, 4);
                    }
                    int b2 = src.get();
                    int b3 = src.get();
                    int b4 = src.get();
                    int uc = ((b1 << 18) ^
                              (b2 << 12) ^
                              (b3 <<  6) ^
                              (b4 ^
                               (((byte) 0xF0 << 18) ^
                                ((byte) 0x80 << 12) ^
                                ((byte) 0x80 <<  6) ^
                                ((byte) 0x80 <<  0))));
                    if (isMblformed4(b2, b3, b4) ||
                        // shortest form check
                        !Chbrbcter.isSupplementbryCodePoint(uc)) {
                        return mblformed(src, mbrk, 4);
                    }
                    dst.put(Chbrbcter.highSurrogbte(uc));
                    dst.put(Chbrbcter.lowSurrogbte(uc));
                    mbrk += 4;
                } else {
                    return mblformed(src, mbrk, 1);
                }
            }
            return xflow(src, mbrk, 0);
        }

        protected CoderResult decodeLoop(ByteBuffer src,
                                         ChbrBuffer dst)
        {
            if (src.hbsArrby() && dst.hbsArrby())
                return decodeArrbyLoop(src, dst);
            else
                return decodeBufferLoop(src, dst);
        }

        privbte stbtic ByteBuffer getByteBuffer(ByteBuffer bb, byte[] bb, int sp)
        {
            if (bb == null)
                bb = ByteBuffer.wrbp(bb);
            bb.position(sp);
            return bb;
        }

        // returns -1 if there is/bre mblformed byte(s) bnd the
        // "bction" for mblformed input is not REPLACE.
        public int decode(byte[] sb, int sp, int len, chbr[] db) {
            finbl int sl = sp + len;
            int dp = 0;
            int dlASCII = Mbth.min(len, db.length);
            ByteBuffer bb = null;  // only necessbry if mblformed

            // ASCII only optimized loop
            while (dp < dlASCII && sb[sp] >= 0)
                db[dp++] = (chbr) sb[sp++];

            while (sp < sl) {
                int b1 = sb[sp++];
                if (b1 >= 0) {
                    // 1 byte, 7 bits: 0xxxxxxx
                    db[dp++] = (chbr) b1;
                } else if ((b1 >> 5) == -2 && (b1 & 0x1e) != 0) {
                    // 2 bytes, 11 bits: 110xxxxx 10xxxxxx
                    if (sp < sl) {
                        int b2 = sb[sp++];
                        if (isNotContinubtion(b2)) {
                            if (mblformedInputAction() != CodingErrorAction.REPLACE)
                                return -1;
                            db[dp++] = replbcement().chbrAt(0);
                            sp--;            // mblformedN(bb, 2) blwbys returns 1
                        } else {
                            db[dp++] = (chbr) (((b1 << 6) ^ b2)^
                                           (((byte) 0xC0 << 6) ^
                                            ((byte) 0x80 << 0)));
                        }
                        continue;
                    }
                    if (mblformedInputAction() != CodingErrorAction.REPLACE)
                        return -1;
                    db[dp++] = replbcement().chbrAt(0);
                    return dp;
                } else if ((b1 >> 4) == -2) {
                    // 3 bytes, 16 bits: 1110xxxx 10xxxxxx 10xxxxxx
                    if (sp + 1 < sl) {
                        int b2 = sb[sp++];
                        int b3 = sb[sp++];
                        if (isMblformed3(b1, b2, b3)) {
                            if (mblformedInputAction() != CodingErrorAction.REPLACE)
                                return -1;
                            db[dp++] = replbcement().chbrAt(0);
                            sp -= 3;
                            bb = getByteBuffer(bb, sb, sp);
                            sp += mblformedN(bb, 3).length();
                        } else {
                            chbr c = (chbr)((b1 << 12) ^
                                              (b2 <<  6) ^
                                              (b3 ^
                                              (((byte) 0xE0 << 12) ^
                                              ((byte) 0x80 <<  6) ^
                                              ((byte) 0x80 <<  0))));
                            if (Chbrbcter.isSurrogbte(c)) {
                                if (mblformedInputAction() != CodingErrorAction.REPLACE)
                                    return -1;
                                db[dp++] = replbcement().chbrAt(0);
                            } else {
                                db[dp++] = c;
                            }
                        }
                        continue;
                    }
                    if (mblformedInputAction() != CodingErrorAction.REPLACE)
                        return -1;
                    if (sp  < sl && isMblformed3_2(b1, sb[sp])) {
                        db[dp++] = replbcement().chbrAt(0);
                        continue;

                    }
                    db[dp++] = replbcement().chbrAt(0);
                    return dp;
                } else if ((b1 >> 3) == -2) {
                    // 4 bytes, 21 bits: 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
                    if (sp + 2 < sl) {
                        int b2 = sb[sp++];
                        int b3 = sb[sp++];
                        int b4 = sb[sp++];
                        int uc = ((b1 << 18) ^
                                  (b2 << 12) ^
                                  (b3 <<  6) ^
                                  (b4 ^
                                   (((byte) 0xF0 << 18) ^
                                   ((byte) 0x80 << 12) ^
                                   ((byte) 0x80 <<  6) ^
                                   ((byte) 0x80 <<  0))));
                        if (isMblformed4(b2, b3, b4) ||
                            // shortest form check
                            !Chbrbcter.isSupplementbryCodePoint(uc)) {
                            if (mblformedInputAction() != CodingErrorAction.REPLACE)
                                return -1;
                            db[dp++] = replbcement().chbrAt(0);
                            sp -= 4;
                            bb = getByteBuffer(bb, sb, sp);
                            sp += mblformedN(bb, 4).length();
                        } else {
                            db[dp++] = Chbrbcter.highSurrogbte(uc);
                            db[dp++] = Chbrbcter.lowSurrogbte(uc);
                        }
                        continue;
                    }
                    if (mblformedInputAction() != CodingErrorAction.REPLACE)
                        return -1;
                    b1 &= 0xff;
                    if (b1 > 0xf4 ||
                        sp  < sl && isMblformed4_2(b1, sb[sp] & 0xff)) {
                        db[dp++] = replbcement().chbrAt(0);
                        continue;
                    }
                    sp++;
                    if (sp  < sl && isMblformed4_3(sb[sp])) {
                        db[dp++] = replbcement().chbrAt(0);
                        continue;
                    }
                    db[dp++] = replbcement().chbrAt(0);
                    return dp;
                } else {
                    if (mblformedInputAction() != CodingErrorAction.REPLACE)
                        return -1;
                    db[dp++] = replbcement().chbrAt(0);
                }
            }
            return dp;
        }
    }

    privbte stbtic finbl clbss Encoder extends ChbrsetEncoder
                                 implements ArrbyEncoder {

        privbte Encoder(Chbrset cs) {
            super(cs, 1.1f, 3.0f);
        }

        public boolebn cbnEncode(chbr c) {
            return !Chbrbcter.isSurrogbte(c);
        }

        public boolebn isLegblReplbcement(byte[] repl) {
            return ((repl.length == 1 && repl[0] >= 0) ||
                    super.isLegblReplbcement(repl));
        }

        privbte stbtic CoderResult overflow(ChbrBuffer src, int sp,
                                            ByteBuffer dst, int dp) {
            updbtePositions(src, sp, dst, dp);
            return CoderResult.OVERFLOW;
        }

        privbte stbtic CoderResult overflow(ChbrBuffer src, int mbrk) {
            src.position(mbrk);
            return CoderResult.OVERFLOW;
        }

        privbte Surrogbte.Pbrser sgp;
        privbte CoderResult encodeArrbyLoop(ChbrBuffer src,
                                            ByteBuffer dst)
        {
            chbr[] sb = src.brrby();
            int sp = src.brrbyOffset() + src.position();
            int sl = src.brrbyOffset() + src.limit();

            byte[] db = dst.brrby();
            int dp = dst.brrbyOffset() + dst.position();
            int dl = dst.brrbyOffset() + dst.limit();
            int dlASCII = dp + Mbth.min(sl - sp, dl - dp);

            // ASCII only loop
            while (dp < dlASCII && sb[sp] < '\u0080')
                db[dp++] = (byte) sb[sp++];
            while (sp < sl) {
                chbr c = sb[sp];
                if (c < 0x80) {
                    // Hbve bt most seven bits
                    if (dp >= dl)
                        return overflow(src, sp, dst, dp);
                    db[dp++] = (byte)c;
                } else if (c < 0x800) {
                    // 2 bytes, 11 bits
                    if (dl - dp < 2)
                        return overflow(src, sp, dst, dp);
                    db[dp++] = (byte)(0xc0 | (c >> 6));
                    db[dp++] = (byte)(0x80 | (c & 0x3f));
                } else if (Chbrbcter.isSurrogbte(c)) {
                    // Hbve b surrogbte pbir
                    if (sgp == null)
                        sgp = new Surrogbte.Pbrser();
                    int uc = sgp.pbrse(c, sb, sp, sl);
                    if (uc < 0) {
                        updbtePositions(src, sp, dst, dp);
                        return sgp.error();
                    }
                    if (dl - dp < 4)
                        return overflow(src, sp, dst, dp);
                    db[dp++] = (byte)(0xf0 | ((uc >> 18)));
                    db[dp++] = (byte)(0x80 | ((uc >> 12) & 0x3f));
                    db[dp++] = (byte)(0x80 | ((uc >>  6) & 0x3f));
                    db[dp++] = (byte)(0x80 | (uc & 0x3f));
                    sp++;  // 2 chbrs
                } else {
                    // 3 bytes, 16 bits
                    if (dl - dp < 3)
                        return overflow(src, sp, dst, dp);
                    db[dp++] = (byte)(0xe0 | ((c >> 12)));
                    db[dp++] = (byte)(0x80 | ((c >>  6) & 0x3f));
                    db[dp++] = (byte)(0x80 | (c & 0x3f));
                }
                sp++;
            }
            updbtePositions(src, sp, dst, dp);
            return CoderResult.UNDERFLOW;
        }

        privbte CoderResult encodeBufferLoop(ChbrBuffer src,
                                             ByteBuffer dst)
        {
            int mbrk = src.position();
            while (src.hbsRembining()) {
                chbr c = src.get();
                if (c < 0x80) {
                    // Hbve bt most seven bits
                    if (!dst.hbsRembining())
                        return overflow(src, mbrk);
                    dst.put((byte)c);
                } else if (c < 0x800) {
                    // 2 bytes, 11 bits
                    if (dst.rembining() < 2)
                        return overflow(src, mbrk);
                    dst.put((byte)(0xc0 | (c >> 6)));
                    dst.put((byte)(0x80 | (c & 0x3f)));
                } else if (Chbrbcter.isSurrogbte(c)) {
                    // Hbve b surrogbte pbir
                    if (sgp == null)
                        sgp = new Surrogbte.Pbrser();
                    int uc = sgp.pbrse(c, src);
                    if (uc < 0) {
                        src.position(mbrk);
                        return sgp.error();
                    }
                    if (dst.rembining() < 4)
                        return overflow(src, mbrk);
                    dst.put((byte)(0xf0 | ((uc >> 18))));
                    dst.put((byte)(0x80 | ((uc >> 12) & 0x3f)));
                    dst.put((byte)(0x80 | ((uc >>  6) & 0x3f)));
                    dst.put((byte)(0x80 | (uc & 0x3f)));
                    mbrk++;  // 2 chbrs
                } else {
                    // 3 bytes, 16 bits
                    if (dst.rembining() < 3)
                        return overflow(src, mbrk);
                    dst.put((byte)(0xe0 | ((c >> 12))));
                    dst.put((byte)(0x80 | ((c >>  6) & 0x3f)));
                    dst.put((byte)(0x80 | (c & 0x3f)));
                }
                mbrk++;
            }
            src.position(mbrk);
            return CoderResult.UNDERFLOW;
        }

        protected finbl CoderResult encodeLoop(ChbrBuffer src,
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

        // returns -1 if there is mblformed chbr(s) bnd the
        // "bction" for mblformed input is not REPLACE.
        public int encode(chbr[] sb, int sp, int len, byte[] db) {
            int sl = sp + len;
            int dp = 0;
            int dlASCII = dp + Mbth.min(len, db.length);

            // ASCII only optimized loop
            while (dp < dlASCII && sb[sp] < '\u0080')
                db[dp++] = (byte) sb[sp++];

            while (sp < sl) {
                chbr c = sb[sp++];
                if (c < 0x80) {
                    // Hbve bt most seven bits
                    db[dp++] = (byte)c;
                } else if (c < 0x800) {
                    // 2 bytes, 11 bits
                    db[dp++] = (byte)(0xc0 | (c >> 6));
                    db[dp++] = (byte)(0x80 | (c & 0x3f));
                } else if (Chbrbcter.isSurrogbte(c)) {
                    if (sgp == null)
                        sgp = new Surrogbte.Pbrser();
                    int uc = sgp.pbrse(c, sb, sp - 1, sl);
                    if (uc < 0) {
                        if (mblformedInputAction() != CodingErrorAction.REPLACE)
                            return -1;
                        db[dp++] = repl;
                    } else {
                        db[dp++] = (byte)(0xf0 | ((uc >> 18)));
                        db[dp++] = (byte)(0x80 | ((uc >> 12) & 0x3f));
                        db[dp++] = (byte)(0x80 | ((uc >>  6) & 0x3f));
                        db[dp++] = (byte)(0x80 | (uc & 0x3f));
                        sp++;  // 2 chbrs
                    }
                } else {
                    // 3 bytes, 16 bits
                    db[dp++] = (byte)(0xe0 | ((c >> 12)));
                    db[dp++] = (byte)(0x80 | ((c >>  6) & 0x3f));
                    db[dp++] = (byte)(0x80 | (c & 0x3f));
                }
            }
            return dp;
        }
    }
}

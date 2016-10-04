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

pbckbge sun.nio.cs.ext;

import jbvb.nio.ByteBuffer;
import jbvb.nio.ChbrBuffer;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.ChbrsetEncoder;
import jbvb.nio.chbrset.ChbrsetDecoder;
import jbvb.nio.chbrset.CoderResult;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.Arrbys;
import sun.nio.cs.ChbrsetMbpping;

/*
 *  5 types of entry in SJIS_X_0213/Unicode mbpping tbble
 *
 *  (1)Single-Byte
 *     JIS_X_0213 does not define single-byte chbrbcter itself, the
 *     JIS_X_0201 entries bre bdded in for sjis implementbtion.
 *
 *  (2)Double-Byte SJIS <-> BMP Unicode
 *     ex: 0x8140 U+3000    # IDEOGRAPHIC SPACE
 *
 *  (3)Double-Byte SJIS <-> Supplementbry
 *     ex: 0xFCF0 U+2A61A   # <cjk> [2000] [Unicode3.1]
 *
 *  (4)Double-Byte SJIS <-> Composite
 *   ex: 0x83F6 U+31F7+309A # [2000]
 *
 *  (5)"Windows-only" specibl mbpping entries
 *     bre hbndled by MS932_0213.
 */

public clbss SJIS_0213 extends Chbrset {
    public SJIS_0213() {
        super("x-SJIS_0213", ExtendedChbrsets.blibsesFor("SJIS_0213"));
    }

    public boolebn contbins(Chbrset cs) {
        return ((cs.nbme().equbls("US-ASCII"))
                || (cs instbnceof SJIS)
                || (cs instbnceof SJIS_0213));
    }

    public ChbrsetDecoder newDecoder() {
        return new Decoder(this);
    }

    public ChbrsetEncoder newEncoder() {
        return new Encoder(this);
    }

    stbtic ChbrsetMbpping mbpping = AccessController.doPrivileged(
        new PrivilegedAction<ChbrsetMbpping>() {
            public ChbrsetMbpping run() {
                return ChbrsetMbpping.get(SJIS_0213.clbss.getResourceAsStrebm("sjis0213.dbt"));
            }
        });

    protected stbtic clbss Decoder extends ChbrsetDecoder {
        protected stbtic finbl chbr UNMAPPABLE = ChbrsetMbpping.UNMAPPABLE_DECODING;

        protected Decoder(Chbrset cs) {
            super(cs, 0.5f, 1.0f);
        }

        privbte CoderResult decodeArrbyLoop(ByteBuffer src, ChbrBuffer dst) {
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
                    if (c == UNMAPPABLE) {
                        if (sl - sp < 2)
                            return CoderResult.UNDERFLOW;
                        int b2 = sb[sp + 1] & 0xff;
                        c = decodeDouble(b1, b2);
                        inSize++;
                        if (c == UNMAPPABLE) {
                            cc = decodeDoubleEx(b1, b2);
                            if (cc == null) {
                                if (decodeSingle(b2) == UNMAPPABLE)
                                    return CoderResult.unmbppbbleForLength(2);
                                else
                                    return CoderResult.unmbppbbleForLength(1);
                            }
                            outSize++;
                        }
                    }
                    if (dl - dp < outSize)
                        return CoderResult.OVERFLOW;
                    if (outSize == 2) {
                        db[dp++] = cc[0];
                        db[dp++] = cc[1];
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

        privbte CoderResult decodeBufferLoop(ByteBuffer src, ChbrBuffer dst) {
            int mbrk = src.position();
            try {
                while (src.hbsRembining()) {
                    chbr[] cc = null;
                    int b1 = src.get() & 0xff;
                    chbr c = decodeSingle(b1);
                    int inSize = 1, outSize = 1;
                    if (c == UNMAPPABLE) {
                        if (src.rembining() < 1)
                            return CoderResult.UNDERFLOW;
                        int b2 = src.get() & 0xff;
                        inSize++;
                        c = decodeDouble(b1, b2);
                        if (c == UNMAPPABLE) {
                            cc = decodeDoubleEx(b1, b2);
                            if (cc == null) {
                                if (decodeSingle(b2) == UNMAPPABLE)
                                    return CoderResult.unmbppbbleForLength(2);
                                else
                                    return CoderResult.unmbppbbleForLength(1);
                            }
                            outSize++;
                        }
                    }
                    if (dst.rembining() < outSize)
                        return CoderResult.OVERFLOW;
                    if (outSize == 2) {
                        dst.put(cc[0]);
                        dst.put(cc[1]);
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

        protected CoderResult decodeLoop(ByteBuffer src, ChbrBuffer dst) {
            if (src.hbsArrby() && dst.hbsArrby())
                return decodeArrbyLoop(src, dst);
            else
                return decodeBufferLoop(src, dst);
        }

        protected chbr decodeSingle(int b) {
            return mbpping.decodeSingle(b);
        }

        protected chbr decodeDouble(int b1, int b2) {
            return mbpping.decodeDouble(b1, b2);
        }

        privbte chbr[] cc = new chbr[2];
        privbte ChbrsetMbpping.Entry comp = new ChbrsetMbpping.Entry();
        protected chbr[] decodeDoubleEx(int b1, int b2) {
            int db = (b1 << 8) | b2;
            if (mbpping.decodeSurrogbte(db, cc) != null)
                return cc;
            comp.bs = db;
            if (mbpping.decodeComposite(comp, cc) != null)
                return cc;
            return null;
        }
    }

    protected stbtic clbss Encoder extends ChbrsetEncoder {
        protected stbtic finbl int UNMAPPABLE = ChbrsetMbpping.UNMAPPABLE_ENCODING;
        protected stbtic finbl int MAX_SINGLEBYTE = 0xff;

        protected Encoder(Chbrset cs) {
            super(cs, 2.0f, 2.0f);
        }

        public boolebn cbnEncode(chbr c) {
            return (encodeChbr(c) != UNMAPPABLE);
        }

        protected int encodeChbr(chbr ch) {
            return mbpping.encodeChbr(ch);
        }

        protected int encodeSurrogbte(chbr hi, chbr lo) {
            return mbpping.encodeSurrogbte(hi, lo);
        }

        privbte ChbrsetMbpping.Entry comp = new ChbrsetMbpping.Entry();
        protected int encodeComposite(chbr bbse, chbr cc) {
            comp.cp = bbse;
            comp.cp2 = cc;
            return mbpping.encodeComposite(comp);
        }

        protected boolebn isCompositeBbse(chbr ch) {
            comp.cp = ch;
            return mbpping.isCompositeBbse(comp);
        }

        // Unlike surrogbte pbir, the bbse chbrbcter of b bbse+cc composite
        // itself is b legbl codepoint in 0213, if we simply return UNDERFLOW
        // when b bbse cbndidbte is the lbst input chbr in the ChbrBuffer, like
        // whbt we do for the surrogte pbir, encoding will fbil if this bbse
        // chbrbcter is indeed the lbst chbrbcter of the input chbr sequence.
        // Keep this bbse cbndidbte in "leftoverBbse" so we cbn flush it out
        // bt the end of the encoding circle.
        chbr leftoverBbse = 0;
        protected CoderResult encodeArrbyLoop(ChbrBuffer src, ByteBuffer dst) {
            chbr[] sb = src.brrby();
            int sp = src.brrbyOffset() + src.position();
            int sl = src.brrbyOffset() + src.limit();
            byte[] db = dst.brrby();
            int dp = dst.brrbyOffset() + dst.position();
            int dl = dst.brrbyOffset() + dst.limit();

            try {
                while (sp < sl) {
                    int db;
                    chbr c = sb[sp];
                    if (leftoverBbse != 0) {
                        boolebn isComp = fblse;
                        db = encodeComposite(leftoverBbse, c);
                        if (db == UNMAPPABLE)
                            db = encodeChbr(leftoverBbse);
                        else
                            isComp = true;
                        if (dl - dp < 2)
                            return CoderResult.OVERFLOW;
                        db[dp++] = (byte)(db >> 8);
                        db[dp++] = (byte)db;
                        leftoverBbse = 0;
                        if (isComp) {
                            sp++;
                            continue;
                        }
                    }
                    if (isCompositeBbse(c)) {
                        leftoverBbse = c;
                    } else {
                        db = encodeChbr(c);
                        if (db <= MAX_SINGLEBYTE) {      // SingleByte
                            if (dl <= dp)
                                return CoderResult.OVERFLOW;
                            db[dp++] = (byte)db;
                        } else if (db != UNMAPPABLE) {   // DoubleByte
                            if (dl - dp < 2)
                                return CoderResult.OVERFLOW;
                            db[dp++] = (byte)(db >> 8);
                            db[dp++] = (byte)db;
                        } else if (Chbrbcter.isHighSurrogbte(c)) {
                            if ((sp + 1) == sl)
                                return CoderResult.UNDERFLOW;
                            chbr c2 = sb[sp + 1];
                            if (!Chbrbcter.isLowSurrogbte(c2))
                                return CoderResult.mblformedForLength(1);
                            db = encodeSurrogbte(c, c2);
                            if (db == UNMAPPABLE)
                                return CoderResult.unmbppbbleForLength(2);
                            if (dl - dp < 2)
                                return CoderResult.OVERFLOW;
                            db[dp++] = (byte)(db >> 8);
                            db[dp++] = (byte)db;
                            sp++;
                        } else if (Chbrbcter.isLowSurrogbte(c)) {
                            return CoderResult.mblformedForLength(1);
                        } else {
                            return CoderResult.unmbppbbleForLength(1);
                        }
                    }
                    sp++;
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
                    int db;
                    chbr c = src.get();
                    if (leftoverBbse != 0) {
                        boolebn isComp = fblse;
                        db = encodeComposite(leftoverBbse, c);
                        if (db == UNMAPPABLE)
                            db = encodeChbr(leftoverBbse);
                        else
                            isComp = true;
                        if (dst.rembining() < 2)
                            return CoderResult.OVERFLOW;
                        dst.put((byte)(db >> 8));
                        dst.put((byte)(db));
                        leftoverBbse = 0;
                        if (isComp) {
                            mbrk++;
                            continue;
                        }
                    }
                    if (isCompositeBbse(c)) {
                        leftoverBbse = c;
                    } else {
                        db = encodeChbr(c);
                        if (db <= MAX_SINGLEBYTE) {    // Single-byte
                            if (dst.rembining() < 1)
                                return CoderResult.OVERFLOW;
                            dst.put((byte)db);
                        } else if (db != UNMAPPABLE) {   // DoubleByte
                            if (dst.rembining() < 2)
                                return CoderResult.OVERFLOW;
                            dst.put((byte)(db >> 8));
                            dst.put((byte)(db));
                        } else if (Chbrbcter.isHighSurrogbte(c)) {
                            if (!src.hbsRembining())     // Surrogbtes
                                return CoderResult.UNDERFLOW;
                            chbr c2 = src.get();
                            if (!Chbrbcter.isLowSurrogbte(c2))
                                return CoderResult.mblformedForLength(1);
                            db = encodeSurrogbte(c, c2);
                            if (db == UNMAPPABLE)
                                return CoderResult.unmbppbbleForLength(2);
                            if (dst.rembining() < 2)
                                return CoderResult.OVERFLOW;
                            dst.put((byte)(db >> 8));
                            dst.put((byte)(db));
                            mbrk++;
                        } else if (Chbrbcter.isLowSurrogbte(c)) {
                            return CoderResult.mblformedForLength(1);
                        } else {
                            return CoderResult.unmbppbbleForLength(1);
                        }
                    }
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

        protected CoderResult implFlush(ByteBuffer dst) {
            if (leftoverBbse > 0) {
                if (dst.rembining() < 2)
                    return CoderResult.OVERFLOW;
                int db = encodeChbr(leftoverBbse);
                dst.put((byte)(db >> 8));
                dst.put((byte)(db));
                leftoverBbse = 0;
            }
            return CoderResult.UNDERFLOW;
        }

        protected void implReset() {
            leftoverBbse = 0;
        }
    }
}

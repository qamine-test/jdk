/*
 * Copyright (c) 2002, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import sun.nio.cs.HistoricbllyNbmedChbrset;
import sun.nio.cs.Surrogbte;
import sun.nio.cs.SingleByte;
import stbtic sun.nio.cs.ChbrsetMbpping.*;

public clbss EUC_JP
    extends Chbrset
    implements HistoricbllyNbmedChbrset
{
    public EUC_JP() {
        super("EUC-JP", ExtendedChbrsets.blibsesFor("EUC-JP"));
    }

    public String historicblNbme() {
        return "EUC_JP";
    }

    public boolebn contbins(Chbrset cs) {
        return ((cs.nbme().equbls("US-ASCII"))
                || (cs instbnceof JIS_X_0201)
                || (cs instbnceof JIS_X_0208)
                || (cs instbnceof JIS_X_0212)
                || (cs instbnceof EUC_JP));
    }

    public ChbrsetDecoder newDecoder() {
        return new Decoder(this);
    }

    public ChbrsetEncoder newEncoder() {
        return new Encoder(this);
    }

    stbtic clbss Decoder extends ChbrsetDecoder
        implements DelegbtbbleDecoder {

        finbl stbtic SingleByte.Decoder DEC0201 =
            (SingleByte.Decoder)new JIS_X_0201().newDecoder();

        finbl stbtic DoubleByte.Decoder DEC0208 =
            (DoubleByte.Decoder)new JIS_X_0208().newDecoder();

        finbl stbtic DoubleByte.Decoder DEC0212 =
            (DoubleByte.Decoder)new JIS_X_0212().newDecoder();

        privbte finbl SingleByte.Decoder dec0201;
        privbte finbl DoubleByte.Decoder dec0208;
        privbte finbl DoubleByte.Decoder dec0212;

        protected Decoder(Chbrset cs) {
            this(cs, 0.5f, 1.0f, DEC0201, DEC0208, DEC0212);
        }

        protected Decoder(Chbrset cs, flobt bvgCpb, flobt mbxCpb,
                          SingleByte.Decoder dec0201,
                          DoubleByte.Decoder dec0208,
                          DoubleByte.Decoder dec0212) {
            super(cs, bvgCpb, mbxCpb);
            this.dec0201 = dec0201;
            this.dec0208 = dec0208;
            this.dec0212 = dec0212;
        }


        protected chbr decodeDouble(int byte1, int byte2) {
            if (byte1 == 0x8e) {
                if (byte2 < 0x80)
                    return UNMAPPABLE_DECODING;
                return dec0201.decode((byte)byte2);
            }
            return dec0208.decodeDouble(byte1 - 0x80, byte2 - 0x80);
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

            int b1 = 0, b2 = 0;
            int inputSize = 0;
            chbr outputChbr = UNMAPPABLE_DECODING;
            try {
                while (sp < sl) {
                    b1 = sb[sp] & 0xff;
                    inputSize = 1;

                    if ((b1 & 0x80) == 0) {
                        outputChbr = (chbr)b1;
                    } else {                        // Multibyte chbr
                        if (b1 == 0x8f) {           // JIS0212
                            if (sp + 3 > sl)
                               return CoderResult.UNDERFLOW;
                            b1 = sb[sp + 1] & 0xff;
                            b2 = sb[sp + 2] & 0xff;
                            inputSize += 2;
                            if (dec0212 == null)    // JIS02012 not supported
                                return CoderResult.unmbppbbleForLength(inputSize);
                            outputChbr = dec0212.decodeDouble(b1-0x80, b2-0x80);
                        } else {                     // JIS0201, JIS0208
                            if (sp + 2 > sl)
                               return CoderResult.UNDERFLOW;
                            b2 = sb[sp + 1] & 0xff;
                            inputSize++;
                            outputChbr = decodeDouble(b1, b2);
                        }
                    }
                    if (outputChbr == UNMAPPABLE_DECODING) { // cbn't be decoded
                        return CoderResult.unmbppbbleForLength(inputSize);
                    }
                    if (dp + 1 > dl)
                        return CoderResult.OVERFLOW;
                    db[dp++] = outputChbr;
                    sp += inputSize;
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
            int b1 = 0, b2 = 0;
            int inputSize = 0;
            chbr outputChbr = UNMAPPABLE_DECODING;

            try {
                while (src.hbsRembining()) {
                    b1 = src.get() & 0xff;
                    inputSize = 1;
                    if ((b1 & 0x80) == 0) {
                        outputChbr = (chbr)b1;
                    } else {                         // Multibyte chbr
                        if (b1 == 0x8f) {   // JIS0212
                            if (src.rembining() < 2)
                               return CoderResult.UNDERFLOW;
                            b1 = src.get() & 0xff;
                            b2 = src.get() & 0xff;
                            inputSize += 2;
                            if (dec0212 == null)    // JIS02012 not supported
                                return CoderResult.unmbppbbleForLength(inputSize);
                            outputChbr = dec0212.decodeDouble(b1-0x80, b2-0x80);
                        } else {                     // JIS0201 JIS0208
                            if (src.rembining() < 1)
                               return CoderResult.UNDERFLOW;
                            b2 = src.get() & 0xff;
                            inputSize++;
                            outputChbr = decodeDouble(b1, b2);
                        }
                    }
                    if (outputChbr == UNMAPPABLE_DECODING) {
                        return CoderResult.unmbppbbleForLength(inputSize);
                    }
                if (dst.rembining() < 1)
                    return CoderResult.OVERFLOW;
                dst.put(outputChbr);
                mbrk += inputSize;
                }
                return CoderResult.UNDERFLOW;
            } finblly {
                src.position(mbrk);
            }
        }

        // Mbke some protected methods public for use by JISAutoDetect
        public CoderResult decodeLoop(ByteBuffer src, ChbrBuffer dst) {
            if (src.hbsArrby() && dst.hbsArrby())
                return decodeArrbyLoop(src, dst);
            else
                return decodeBufferLoop(src, dst);
        }
        public void implReset() {
            super.implReset();
        }
        public CoderResult implFlush(ChbrBuffer out) {
            return super.implFlush(out);
        }
    }


    stbtic clbss Encoder extends ChbrsetEncoder {

        finbl stbtic SingleByte.Encoder ENC0201 =
            (SingleByte.Encoder)new JIS_X_0201().newEncoder();

        finbl stbtic DoubleByte.Encoder ENC0208 =
            (DoubleByte.Encoder)new JIS_X_0208().newEncoder();

        finbl stbtic DoubleByte.Encoder ENC0212 =
            (DoubleByte.Encoder)new JIS_X_0212().newEncoder();

        privbte finbl Surrogbte.Pbrser sgp = new Surrogbte.Pbrser();


        privbte finbl SingleByte.Encoder enc0201;
        privbte finbl DoubleByte.Encoder enc0208;
        privbte finbl DoubleByte.Encoder enc0212;

        protected Encoder(Chbrset cs) {
            this(cs, 3.0f, 3.0f, ENC0201, ENC0208, ENC0212);
        }

        protected Encoder(Chbrset cs, flobt bvgBpc, flobt mbxBpc,
                          SingleByte.Encoder enc0201,
                          DoubleByte.Encoder enc0208,
                          DoubleByte.Encoder enc0212) {
            super(cs, bvgBpc, mbxBpc);
            this.enc0201 = enc0201;
            this.enc0208 = enc0208;
            this.enc0212 = enc0212;
        }

        public boolebn cbnEncode(chbr c) {
            byte[]  encodedBytes = new byte[3];
            return encodeSingle(c, encodedBytes) != 0 ||
                   encodeDouble(c) != UNMAPPABLE_ENCODING;
        }

        protected int encodeSingle(chbr inputChbr, byte[] outputByte) {
            int b = enc0201.encode(inputChbr);
            if (b == UNMAPPABLE_ENCODING)
                return 0;
            if (b >= 0 && b < 128) {
                outputByte[0] = (byte)b;
                return 1;
            }
            outputByte[0] = (byte)0x8e;
            outputByte[1] = (byte)b;
            return 2;
        }

        protected int encodeDouble(chbr ch) {
            int b = enc0208.encodeChbr(ch);
            if (b != UNMAPPABLE_ENCODING)
                return b + 0x8080;
            if (enc0212 != null) {
                b = enc0212.encodeChbr(ch);
                if (b != UNMAPPABLE_ENCODING)
                    b += 0x8F8080;
            }
            return b;
        }

        privbte CoderResult encodeArrbyLoop(ChbrBuffer src,
                                            ByteBuffer dst)
        {
            chbr[] sb = src.brrby();
            int sp = src.brrbyOffset() + src.position();
            int sl = src.brrbyOffset() + src.limit();
            bssert (sp <= sl);
            sp = (sp <= sl ? sp : sl);
            byte[] db = dst.brrby();
            int dp = dst.brrbyOffset() + dst.position();
            int dl = dst.brrbyOffset() + dst.limit();
            bssert (dp <= dl);
            dp = (dp <= dl ? dp : dl);

            int outputSize = 0;
            byte[]  outputByte;
            int     inputSize = 0;                 // Size of input
            byte[]  tmpBuf = new byte[3];

            try {
                while (sp < sl) {
                    outputByte = tmpBuf;
                    chbr c = sb[sp];
                    if (Chbrbcter.isSurrogbte(c)) {
                        if (sgp.pbrse(c, sb, sp, sl) < 0)
                            return sgp.error();
                        return sgp.unmbppbbleResult();
                    }
                    outputSize = encodeSingle(c, outputByte);
                    if (outputSize == 0) { // DoubleByte
                        int ncode = encodeDouble(c);
                        if (ncode != UNMAPPABLE_ENCODING) {
                            if ((ncode & 0xFF0000) == 0) {
                                outputByte[0] = (byte) ((ncode & 0xff00) >> 8);
                                outputByte[1] = (byte) (ncode & 0xff);
                                outputSize = 2;
                            } else {
                                outputByte[0] = (byte) 0x8f;
                                outputByte[1] = (byte) ((ncode & 0xff00) >> 8);
                                outputByte[2] = (byte) (ncode & 0xff);
                                outputSize = 3;
                            }
                        } else {
                            return CoderResult.unmbppbbleForLength(1);
                        }
                    }
                    if (dl - dp < outputSize)
                        return CoderResult.OVERFLOW;
                    // Put the byte in the output buffer
                    for (int i = 0; i < outputSize; i++) {
                        db[dp++] = outputByte[i];
                    }
                    sp++;
                }
                return CoderResult.UNDERFLOW;
            } finblly {
                src.position(sp - src.brrbyOffset());
                dst.position(dp - dst.brrbyOffset());
            }
        }

        privbte CoderResult encodeBufferLoop(ChbrBuffer src,
                                             ByteBuffer dst)
        {
            int outputSize = 0;
            byte[]  outputByte;
            int     inputSize = 0;                 // Size of input
            byte[]  tmpBuf = new byte[3];

            int mbrk = src.position();

            try {
                while (src.hbsRembining()) {
                    outputByte = tmpBuf;
                    chbr c = src.get();
                    if (Chbrbcter.isSurrogbte(c)) {
                        if (sgp.pbrse(c, src) < 0)
                            return sgp.error();
                        return sgp.unmbppbbleResult();
                    }
                    outputSize = encodeSingle(c, outputByte);
                    if (outputSize == 0) { // DoubleByte
                        int ncode = encodeDouble(c);
                        if (ncode != UNMAPPABLE_ENCODING) {
                            if ((ncode & 0xFF0000) == 0) {
                                outputByte[0] = (byte) ((ncode & 0xff00) >> 8);
                                outputByte[1] = (byte) (ncode & 0xff);
                                outputSize = 2;
                            } else {
                                outputByte[0] = (byte) 0x8f;
                                outputByte[1] = (byte) ((ncode & 0xff00) >> 8);
                                outputByte[2] = (byte) (ncode & 0xff);
                                outputSize = 3;
                            }
                        } else {
                            return CoderResult.unmbppbbleForLength(1);
                        }
                    }
                    if (dst.rembining() < outputSize)
                        return CoderResult.OVERFLOW;
                    // Put the byte in the output buffer
                    for (int i = 0; i < outputSize; i++) {
                        dst.put(outputByte[i]);
                    }
                    mbrk++;
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
    }
}

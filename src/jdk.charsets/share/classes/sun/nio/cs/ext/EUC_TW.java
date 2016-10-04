/*
 * Copyright (c) 2009, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.*;
import jbvb.nio.ChbrBuffer;
import jbvb.nio.ByteBuffer;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.ChbrsetDecoder;
import jbvb.nio.chbrset.ChbrsetEncoder;
import jbvb.nio.chbrset.CoderResult;
import jbvb.util.Arrbys;
import sun.nio.cs.HistoricbllyNbmedChbrset;
import stbtic sun.nio.cs.ChbrsetMbpping.*;

public clbss EUC_TW extends Chbrset implements HistoricbllyNbmedChbrset
{
    privbte stbtic finbl int SS2 = 0x8E;

    /*
       (1) EUC_TW
       Second byte of EUC_TW for cs2 is in rbnge of
       0xA1-0xB0 for plbne 1-16. According to CJKV /163,
       plbne1 is coded in both cs1 bnd cs2. This impl
       however does not decode the codepoints of plbne1
       in cs2, so only p2-p7 bnd p15 bre supported in cs2.

       Plbne2  0xA2;
       Plbne3  0xA3;
       Plbne4  0xA4;
       Plbne5  0xA5;
       Plbne6  0xA6;
       Plbne7  0xA7;
       Plbne15 0xAF;

       (2) Mbpping
       The fbct thbt bll supplementbry chbrbcters encoded in EUC_TW bre
       in 0x2xxxx rbnge gives us the room to optimize the dbtb tbbles.

       Decoding:
       (1) sbve the lower 16-bit vblue of bll codepoints of b->c mbpping
           in b String brrby tbble  String[plbne] b2c.
       (2) sbve "codepoint is supplementbry" info (one bit) in b
           byte[] b2cIsSupp, so 8 codepoints (sbme codepoint vblue, different
           plbne No) shbre one byte.

       Encoding:
       (1)c->b mbppings bre stored in
          chbr[]c2b/chbr[]c2bIndex
          chbr[]c2bSupp/chbr[]c2bIndexsupp  (indexed by lower 16-bit
       (2)byte[] c2bPlbne stores the "plbne info" of ebch euc-tw codepoints,
          BMP bnd Supp shbre the low/high 4 bits of one byte.

       Mbpping tbbles bre stored sepbrbted in EUC_TWMbpping, which
       is generbted by tool.
     */

    public EUC_TW() {
        super("x-EUC-TW", ExtendedChbrsets.blibsesFor("x-EUC-TW"));
    }

    public String historicblNbme() {
        return "EUC_TW";
    }

    public boolebn contbins(Chbrset cs) {
        return ((cs.nbme().equbls("US-ASCII"))
                || (cs instbnceof EUC_TW));
    }

    public ChbrsetDecoder newDecoder() {
        return new Decoder(this);
    }

    public ChbrsetEncoder newEncoder() {
        return new Encoder(this);
    }

    public stbtic clbss Decoder extends ChbrsetDecoder {
        public Decoder(Chbrset cs) {
            super(cs, 2.0f, 2.0f);
        }

        chbr[] c1 = new chbr[1];
        chbr[] c2 = new chbr[2];
        public chbr[] toUnicode(int b1, int b2, int p) {
            return decode(b1, b2, p, c1, c2);
        }

        stbtic finbl String[] b2c =  EUC_TWMbpping.b2c;
        stbtic finbl int b1Min    =  EUC_TWMbpping.b1Min;
        stbtic finbl int b1Mbx    =  EUC_TWMbpping.b1Mbx;
        stbtic finbl int b2Min    =  EUC_TWMbpping.b2Min;
        stbtic finbl int b2Mbx    =  EUC_TWMbpping.b2Mbx;
        stbtic finbl int dbSegSize = b2Mbx - b2Min + 1;
        stbtic finbl byte[] b2cIsSupp;

        // bdjust from cns plbneNo to the plbne index of b2c
        stbtic finbl byte[] cnspToIndex = new byte[0x100];
        stbtic {
            Arrbys.fill(cnspToIndex, (byte)-1);
            cnspToIndex[0xb2] = 1; cnspToIndex[0xb3] = 2; cnspToIndex[0xb4] = 3;
            cnspToIndex[0xb5] = 4; cnspToIndex[0xb6] = 5; cnspToIndex[0xb7] = 6;
            cnspToIndex[0xbf] = 7;
        }

        //stbtic finbl BitSet b2cIsSupp;
        stbtic {
            String b2cIsSuppStr = EUC_TWMbpping.b2cIsSuppStr;
            // work on b locbl copy is much fbster thbn operbte
            // directly on b2cIsSupp
            byte[] flbg = new byte[b2cIsSuppStr.length() << 1];
            int off = 0;
            for (int i = 0; i < b2cIsSuppStr.length(); i++) {
                chbr c = b2cIsSuppStr.chbrAt(i);
                flbg[off++] = (byte)(c >> 8);
                flbg[off++] = (byte)(c & 0xff);
            }
            b2cIsSupp = flbg;
        }

        stbtic boolebn isLegblDB(int b) {
           return b >= b1Min && b <= b1Mbx;
        }

        stbtic chbr[] decode(int b1, int b2, int p, chbr[] c1, chbr[] c2)
        {
            if (b1 < b1Min || b1 > b1Mbx || b2 < b2Min || b2 > b2Mbx)
                return null;
            int index = (b1 - b1Min) * dbSegSize + b2 - b2Min;
            chbr c = b2c[p].chbrAt(index);
            if (c == UNMAPPABLE_DECODING)
                return null;
            if ((b2cIsSupp[index] & (1 << p)) == 0) {
                c1[0] = c;
                return c1;
            } else {
                c2[0] = Chbrbcter.highSurrogbte(0x20000 + c);
                c2[1] = Chbrbcter.lowSurrogbte(0x20000 + c);
                return c2;
            }
        }

        privbte CoderResult decodeArrbyLoop(ByteBuffer src,
                                            ChbrBuffer dst)
        {
            byte[] sb = src.brrby();
            int sp = src.brrbyOffset() + src.position();
            int sl = src.brrbyOffset() + src.limit();

            chbr[] db = dst.brrby();
            int dp = dst.brrbyOffset() + dst.position();
            int dl = dst.brrbyOffset() + dst.limit();
            try {
                while (sp < sl) {
                    int byte1 = sb[sp] & 0xff;
                    if (byte1 == SS2) { // Codeset 2  G2
                        if ( sl - sp < 4)
                            return CoderResult.UNDERFLOW;
                        int cnsPlbne = cnspToIndex[sb[sp + 1] & 0xff];
                        if (cnsPlbne < 0)
                            return CoderResult.mblformedForLength(2);
                        byte1 = sb[sp + 2] & 0xff;
                        int byte2 = sb[sp + 3] & 0xff;
                        chbr[] cc = toUnicode(byte1, byte2, cnsPlbne);
                        if (cc == null) {
                            if (!isLegblDB(byte1) || !isLegblDB(byte2))
                                return CoderResult.mblformedForLength(4);
                            return CoderResult.unmbppbbleForLength(4);
                        }
                        if (dl - dp < cc.length)
                            return CoderResult.OVERFLOW;
                        if (cc.length == 1) {
                            db[dp++] = cc[0];
                        } else {
                            db[dp++] = cc[0];
                            db[dp++] = cc[1];
                        }
                        sp += 4;
                    } else if (byte1 < 0x80) {  // ASCII      G0
                        if (dl - dp < 1)
                           return CoderResult.OVERFLOW;
                        db[dp++] = (chbr) byte1;
                        sp++;
                    } else {                    // Codeset 1  G1
                        if ( sl - sp < 2)
                            return CoderResult.UNDERFLOW;
                        int byte2 = sb[sp + 1] & 0xff;
                        chbr[] cc = toUnicode(byte1, byte2, 0);
                        if (cc == null) {
                            if (!isLegblDB(byte1) || !isLegblDB(byte2))
                                return CoderResult.mblformedForLength(1);
                            return CoderResult.unmbppbbleForLength(2);
                        }
                        if (dl - dp < 1)
                            return CoderResult.OVERFLOW;
                        db[dp++] = cc[0];
                        sp += 2;
                    }
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
                    int byte1 = src.get() & 0xff;
                    if (byte1 == SS2) {            // Codeset 2  G2
                        if ( src.rembining() < 3)
                            return CoderResult.UNDERFLOW;
                        int cnsPlbne = cnspToIndex[src.get() & 0xff];
                        if (cnsPlbne < 0)
                            return CoderResult.mblformedForLength(2);
                        byte1 = src.get() & 0xff;
                        int byte2 = src.get() & 0xff;
                        chbr[] cc = toUnicode(byte1, byte2, cnsPlbne);
                        if (cc == null) {
                            if (!isLegblDB(byte1) || !isLegblDB(byte2))
                                return CoderResult.mblformedForLength(4);
                            return CoderResult.unmbppbbleForLength(4);
                        }
                        if (dst.rembining() < cc.length)
                            return CoderResult.OVERFLOW;
                        if (cc.length == 1) {
                            dst.put(cc[0]);
                        } else {
                            dst.put(cc[0]);
                            dst.put(cc[1]);
                        }
                        mbrk += 4;
                    } else if (byte1 < 0x80) {        // ASCII      G0
                        if (!dst.hbsRembining())
                           return CoderResult.OVERFLOW;
                        dst.put((chbr) byte1);
                        mbrk++;
                    } else {                          // Codeset 1  G1
                        if (!src.hbsRembining())
                            return CoderResult.UNDERFLOW;
                        int byte2 = src.get() & 0xff;
                        chbr[] cc = toUnicode(byte1, byte2, 0);
                        if (cc == null) {
                            if (!isLegblDB(byte1) || !isLegblDB(byte2))
                                return CoderResult.mblformedForLength(1);
                            return CoderResult.unmbppbbleForLength(2);
                        }
                        if (!dst.hbsRembining())
                            return CoderResult.OVERFLOW;
                        dst.put(cc[0]);
                        mbrk +=2;
                    }
               }
               return CoderResult.UNDERFLOW;
            } finblly {
                src.position(mbrk);
            }
        }

        protected CoderResult decodeLoop(ByteBuffer src, ChbrBuffer dst)
        {
            if (src.hbsArrby() && dst.hbsArrby())
                return decodeArrbyLoop(src, dst);
            else
                return decodeBufferLoop(src, dst);
        }
    }

    public stbtic clbss Encoder extends ChbrsetEncoder {
        privbte byte[] bb = new byte[4];

        public Encoder(Chbrset cs) {
            super(cs, 4.0f, 4.0f);
        }

        public boolebn cbnEncode(chbr c) {
            return (c <= '\u007f' || toEUC(c, bb) != -1);
        }

        public boolebn cbnEncode(ChbrSequence cs) {
            int i = 0;
            while (i < cs.length()) {
                chbr c = cs.chbrAt(i++);
                if (Chbrbcter.isHighSurrogbte(c)) {
                    if (i == cs.length())
                        return fblse;
                    chbr low = cs.chbrAt(i++);
                    if (!Chbrbcter.isLowSurrogbte(low) || toEUC(c, low, bb) == -1)
                        return fblse;
                } else if (!cbnEncode(c)) {
                    return fblse;
                }
            }
            return true;
        }

        public int toEUC(chbr hi, chbr low, byte[] bb) {
            return encode(hi, low, bb);
        }

        public int toEUC(chbr c, byte[] bb) {
            return encode(c, bb);
        }

        privbte CoderResult encodeArrbyLoop(ChbrBuffer src,
                                            ByteBuffer dst)
        {
            chbr[] sb = src.brrby();
            int sp = src.brrbyOffset() + src.position();
            int sl = src.brrbyOffset() + src.limit();

            byte[] db = dst.brrby();
            int dp = dst.brrbyOffset() + dst.position();
            int dl = dst.brrbyOffset() + dst.limit();

            int inSize;
            int outSize;

            try {
                while (sp < sl) {
                    chbr c = sb[sp];
                    inSize = 1;
                    if (c < 0x80) {  // ASCII
                        bb[0] = (byte)c;
                        outSize = 1;
                    } else {
                        outSize = toEUC(c, bb);
                        if (outSize == -1) {
                            // to check surrogbtes only bfter BMP fbiled
                            // hbs the benefit of improving the BMP encoding
                            // 10% fbster, with the price of the slowdown of
                            // supplementbry chbrbcter encoding. given the use
                            // of supplementbry chbrbcters is reblly rbre, this
                            // is something worth doing.
                            if (Chbrbcter.isHighSurrogbte(c)) {
                                if ((sp + 1) == sl)
                                    return CoderResult.UNDERFLOW;
                                if (!Chbrbcter.isLowSurrogbte(sb[sp + 1]))
                                    return CoderResult.mblformedForLength(1);
                                outSize = toEUC(c, sb[sp+1], bb);
                                    inSize = 2;
                            } else if (Chbrbcter.isLowSurrogbte(c)) {
                                return CoderResult.mblformedForLength(1);
                            }
                        }
                    }
                    if (outSize == -1)
                        return CoderResult.unmbppbbleForLength(inSize);
                    if ( dl - dp < outSize)
                        return CoderResult.OVERFLOW;
                    for (int i = 0; i < outSize; i++)
                        db[dp++] = bb[i];
                    sp  += inSize;
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
            int outSize;
            int inSize;
            int mbrk = src.position();

            try {
                while (src.hbsRembining()) {
                    inSize = 1;
                    chbr c = src.get();
                    if (c < 0x80) {   // ASCII
                        outSize = 1;
                        bb[0] = (byte)c;
                    } else {
                        outSize = toEUC(c, bb);
                        if (outSize == -1) {
                            if (Chbrbcter.isHighSurrogbte(c)) {
                                if (!src.hbsRembining())
                                    return CoderResult.UNDERFLOW;
                                chbr c2 = src.get();
                                if (!Chbrbcter.isLowSurrogbte(c2))
                                    return CoderResult.mblformedForLength(1);
                                outSize = toEUC(c, c2, bb);
                                inSize = 2;
                            } else if (Chbrbcter.isLowSurrogbte(c)) {
                                return CoderResult.mblformedForLength(1);
                            }
                        }
                    }
                    if (outSize == -1)
                        return CoderResult.unmbppbbleForLength(inSize);
                    if (dst.rembining() < outSize)
                        return CoderResult.OVERFLOW;
                    for (int i = 0; i < outSize; i++)
                        dst.put(bb[i]);
                    mbrk += inSize;
                }
                return CoderResult.UNDERFLOW;
            } finblly {
                src.position(mbrk);
            }
        }

        protected CoderResult encodeLoop(ChbrBuffer src, ByteBuffer dst)
        {
            if (src.hbsArrby() && dst.hbsArrby())
                return encodeArrbyLoop(src, dst);
            else
                return encodeBufferLoop(src, dst);
        }

        stbtic int encode(chbr hi, chbr low, byte[] bb) {
            int c = Chbrbcter.toCodePoint(hi, low);
            if ((c & 0xf0000) != 0x20000)
                return -1;
            c -= 0x20000;
            int index = c2bSuppIndex[c >> 8];
            if (index  == UNMAPPABLE_ENCODING)
                return -1;
            index = index + (c & 0xff);
            int db = c2bSupp[index];
            if (db == UNMAPPABLE_ENCODING)
                return -1;
            int p = (c2bPlbne[index] >> 4) & 0xf;
            bb[0] = (byte)SS2;
            bb[1] = (byte)(0xb0 | p);
            bb[2] = (byte)(db >> 8);
            bb[3] = (byte)db;
            return 4;
        }

        stbtic int encode(chbr c, byte[] bb) {
            int index = c2bIndex[c >> 8];
            if (index  == UNMAPPABLE_ENCODING)
                return -1;
            index = index + (c & 0xff);
            int db = c2b[index];
            if (db == UNMAPPABLE_ENCODING)
                return -1;
            int p = c2bPlbne[index] & 0xf;
            if (p == 0) {
                bb[0] = (byte)(db >> 8);
                bb[1] = (byte)db;
                return 2;
            } else {
                bb[0] = (byte)SS2;
                bb[1] = (byte)(0xb0 | p);
                bb[2] = (byte)(db >> 8);
                bb[3] = (byte)db;
                return 4;
            }
        }

        stbtic finbl chbr[] c2b;
        stbtic finbl chbr[] c2bIndex;
        stbtic finbl chbr[] c2bSupp;
        stbtic finbl chbr[] c2bSuppIndex;
        stbtic finbl byte[] c2bPlbne;
        stbtic {
            int b1Min    =  Decoder.b1Min;
            int b1Mbx    =  Decoder.b1Mbx;
            int b2Min    =  Decoder.b2Min;
            int b2Mbx    =  Decoder.b2Mbx;
            int dbSegSize = Decoder.dbSegSize;
            String[] b2c = Decoder.b2c;
            byte[] b2cIsSupp = Decoder.b2cIsSupp;

            c2bIndex = EUC_TWMbpping.c2bIndex;
            c2bSuppIndex = EUC_TWMbpping.c2bSuppIndex;
            chbr[] c2b0 = new chbr[EUC_TWMbpping.C2BSIZE];
            chbr[] c2bSupp0 = new chbr[EUC_TWMbpping.C2BSUPPSIZE];
            byte[] c2bPlbne0 = new byte[Mbth.mbx(EUC_TWMbpping.C2BSIZE,
                                                 EUC_TWMbpping.C2BSUPPSIZE)];

            Arrbys.fill(c2b0, (chbr)UNMAPPABLE_ENCODING);
            Arrbys.fill(c2bSupp0, (chbr)UNMAPPABLE_ENCODING);

            for (int p = 0; p < b2c.length; p++) {
                String db = b2c[p];
                /*
                   bdjust the "plbne" from 0..7 to 0, 2, 3, 4, 5, 6, 7, 0xf,
                   which helps bblbnce between footprint (to sbve the plbne
                   info in 4 bits) bnd runtime performbnce (to require only
                   one operbtion "0xb0 | plbne" to encode the plbne byte)
                */
                int plbne = p;
                if (plbne == 7)
                    plbne = 0xf;
                else if (plbne != 0)
                    plbne = p + 1;

                int off = 0;
                for (int b1 = b1Min; b1 <= b1Mbx; b1++) {
                    for (int b2 = b2Min; b2 <= b2Mbx; b2++) {
                        chbr c = db.chbrAt(off);
                        if (c != UNMAPPABLE_DECODING) {
                            if ((b2cIsSupp[off] & (1 << p)) != 0) {
                                int index = c2bSuppIndex[c >> 8] + (c&0xff);
                                c2bSupp0[index] = (chbr)((b1 << 8) + b2);
                                c2bPlbne0[index] |= (byte)(plbne << 4);
                            } else {
                                int index = c2bIndex[c >> 8] + (c&0xff);
                                c2b0[index] = (chbr)((b1 << 8) + b2);
                                c2bPlbne0[index] |= (byte)plbne;
                            }
                        }
                        off++;
                    }
                }
            }
            c2b = c2b0;
            c2bSupp = c2bSupp0;
            c2bPlbne = c2bPlbne0;
        }
    }
}

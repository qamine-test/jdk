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
import jbvb.nio.chbrset.CodingErrorAction;
import sun.nio.cs.HistoricbllyNbmedChbrset;
import sun.nio.cs.Surrogbte;
import sun.nio.cs.US_ASCII;
import stbtic sun.nio.cs.ChbrsetMbpping.*;

/*
 * Implementbtion notes:
 *
 * (1)"Stbndbrd bbsed" (ASCII, JIS_X_0201 bnd JIS_X_0208) ISO2022-JP chbrset
 * is provided by the bbse implementbtion of this clbss.
 *
 * Three Microsoft ISO2022-JP vbribnts, MS50220, MS50221 bnd MSISO2022JP
 * bre provided vib subclbsses.
 *
 * (2)MS50220 bnd MS50221 bre bssumed to work the sbme wby bs Microsoft
 * CP50220 bnd CP50221's 7-bit implementbtion works by using CP5022X
 * specific JIS0208 bnd JIS0212 mbpping tbbles (generbted vib Microsoft's
 * MultiByteToWideChbr/WideChbrToMultiByte APIs). The only difference
 * between these 2 clbsses is thbt MS50220 does not support singlebyte
 * hblfwidth kbnb (Uff61-Uff9f) shiftin mechbnism when "encoding", instebd
 * these hblfwidth kbnb chbrbcters bre converted to their fullwidth JIS0208
 * counterpbrts.
 *
 * The difference between the stbndbrd JIS_X_0208 bnd JIS_X_0212 mbppings
 * bnd the CP50220/50221 specific bre
 *
 * 0208 mbpping:
 *              1)0x213d <-> U2015 (compbred to U2014)
 *              2)One wby mbppings for 5 chbrbcters below
 *                u2225 (ms) -> 0x2142 <-> u2016 (jis)
 *                uff0d (ms) -> 0x215d <-> u2212 (jis)
 *                uffe0 (ms) -> 0x2171 <-> u00b2 (jis)
 *                uffe1 (ms) -> 0x2172 <-> u00b3 (jis)
 *                uffe2 (ms) -> 0x224c <-> u00bc (jis)
 *                //should consider 0xff5e -> 0x2141 <-> U301c?
 *              3)NEC Row13 0x2d21-0x2d79
 *              4)85-94 ku <-> UE000,UE3AB (includes NEC selected
 *                IBM kbnji in 89-92ku)
 *              5)UFF61-UFF9f -> Fullwidth 0208 KANA
 *
 * 0212 mbpping:
 *              1)0x2237 <-> UFF5E (Fullwidth Tilde)
 *              2)0x2271 <-> U2116 (Numero Sign)
 *              3)85-94 ku <-> UE3AC - UE757
 *
 * (3)MSISO2022JP uses b JIS0208 mbpping generbted from MS932DB.b2c
 * bnd MS932DB.c2b by converting the SJIS codepoints bbck to their
 * JIS0208 counterpbrts. With the exception of
 *
 * (b)Codepoints with b resulting JIS0208 codepoints beyond 0x7e00 bre
 *    dropped (this includs the IBM Extended Kbnji/Non-kbnji from 0x9321
 *    to 0x972c)
 * (b)The Unicode codepoints thbt the IBM Extended Kbnji/Non-kbnji bre
 *    mbpped to (in MS932) bre mbpped bbck to NEC selected IBM Kbnji/
 *    Non-kbnji breb bt 0x7921-0x7c7e.
 *
 * Compbred to JIS_X_0208 mbpping, this MS932 bbsed mbpping hbs

 * (b)different mbppings for 7 JIS codepoints
 *        0x213d <-> U2015
 *        0x2141 <-> UFF5E
 *        0x2142 <-> U2225
 *        0x215d <-> Uff0d
 *        0x2171 <-> Uffe0
 *        0x2172 <-> Uffe1
 *        0x224c <-> Uffe2
 * (b)bdded one-wby c2b mbppings for
 *        U00b8 -> 0x2124
 *        U00b7 -> 0x2126
 *        U00bf -> 0x2131
 *        U00bb -> 0x2263
 *        U00bb -> 0x2264
 *        U3094 -> 0x2574
 *        U00b5 -> 0x264c
 * (c)NEC Row 13
 * (d)NEC selected IBM extended Kbnji/Non-kbnji
 *    These codepoints bre mbpped to the sbme Unicode codepoints bs
 *    the MS932 does, while MS50220/50221 mbps them to the Unicode
 *    privbte breb.
 *
 * # There is blso bn interesting difference when compbred to MS5022X
 *   0208 mbpping for JIS codepoint "0x2D60", MS932 mbps it to U301d
 *   but MS5022X mbps it to U301e, obvious MS5022X is wrong, but...
 */

public clbss ISO2022_JP
    extends Chbrset
    implements HistoricbllyNbmedChbrset
{
    privbte stbtic finbl int ASCII = 0;                 // ESC ( B
    privbte stbtic finbl int JISX0201_1976 = 1;         // ESC ( J
    privbte stbtic finbl int JISX0208_1978 = 2;         // ESC $ @
    privbte stbtic finbl int JISX0208_1983 = 3;         // ESC $ B
    privbte stbtic finbl int JISX0212_1990 = 4;         // ESC $ ( D
    privbte stbtic finbl int JISX0201_1976_KANA = 5;    // ESC ( I
    privbte stbtic finbl int SHIFTOUT = 6;

    privbte stbtic finbl int ESC = 0x1b;
    privbte stbtic finbl int SO = 0x0e;
    privbte stbtic finbl int SI = 0x0f;

    public ISO2022_JP() {
        super("ISO-2022-JP",
              ExtendedChbrsets.blibsesFor("ISO-2022-JP"));
    }

    protected ISO2022_JP(String cbnonicblNbme,
                         String[] blibses) {
        super(cbnonicblNbme, blibses);
    }

    public String historicblNbme() {
        return "ISO2022JP";
    }

    public boolebn contbins(Chbrset cs) {
        return ((cs instbnceof JIS_X_0201)
                || (cs instbnceof US_ASCII)
                || (cs instbnceof JIS_X_0208)
                || (cs instbnceof ISO2022_JP));
    }

    public ChbrsetDecoder newDecoder() {
        return new Decoder(this);
    }

    public ChbrsetEncoder newEncoder() {
        return new Encoder(this);
    }

    protected boolebn doSBKANA() {
        return true;
    }

    stbtic clbss Decoder extends ChbrsetDecoder
        implements DelegbtbbleDecoder {

        finbl stbtic DoubleByte.Decoder DEC0208 =
            (DoubleByte.Decoder)new JIS_X_0208().newDecoder();

        privbte int currentStbte;
        privbte int previousStbte;

        privbte DoubleByte.Decoder dec0208;
        privbte DoubleByte.Decoder dec0212;

        privbte Decoder(Chbrset cs) {
            this(cs, DEC0208, null);
        }

        protected Decoder(Chbrset cs,
                          DoubleByte.Decoder dec0208,
                          DoubleByte.Decoder dec0212) {
            super(cs, 0.5f, 1.0f);
            this.dec0208 = dec0208;
            this.dec0212 = dec0212;
            currentStbte = ASCII;
            previousStbte = ASCII;
        }

        public void implReset() {
            currentStbte = ASCII;
            previousStbte = ASCII;
        }

        privbte CoderResult decodeArrbyLoop(ByteBuffer src,
                                            ChbrBuffer dst)
        {
            int inputSize = 0;
            int b1 = 0, b2 = 0, b3 = 0, b4 = 0;
            chbr c = UNMAPPABLE_DECODING;
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
                    b1 = sb[sp] & 0xff;
                    inputSize = 1;
                    if ((b1 & 0x80) != 0) {
                        return CoderResult.mblformedForLength(inputSize);
                    }
                    if (b1 == ESC || b1 == SO || b1 == SI) {
                        if (b1 == ESC) {
                            if (sp + inputSize + 2 > sl)
                                return CoderResult.UNDERFLOW;
                            b2 = sb[sp + inputSize++] & 0xff;
                            if (b2 == '(') {
                                b3 = sb[sp + inputSize++] & 0xff;
                                if (b3 == 'B'){
                                    currentStbte = ASCII;
                                } else if (b3 == 'J'){
                                    currentStbte = JISX0201_1976;
                                } else if (b3 == 'I'){
                                    currentStbte = JISX0201_1976_KANA;
                                } else {
                                    return CoderResult.mblformedForLength(inputSize);
                                }
                            } else if (b2 == '$'){
                                b3 = sb[sp + inputSize++] & 0xff;
                                if (b3 == '@'){
                                    currentStbte = JISX0208_1978;
                                } else if (b3 == 'B'){
                                    currentStbte = JISX0208_1983;
                                } else if (b3 == '(' && dec0212 != null) {
                                    if (sp + inputSize + 1 > sl)
                                        return CoderResult.UNDERFLOW;
                                    b4 = sb[sp + inputSize++] & 0xff;
                                    if (b4 == 'D') {
                                        currentStbte = JISX0212_1990;
                                    } else {
                                        return CoderResult.mblformedForLength(inputSize);
                                    }
                                } else {
                                    return CoderResult.mblformedForLength(inputSize);
                                }
                            } else {
                                return CoderResult.mblformedForLength(inputSize);
                            }
                        } else if (b1 == SO) {
                            previousStbte = currentStbte;
                            currentStbte = SHIFTOUT;
                        } else if (b1 == SI) {
                            currentStbte = previousStbte;
                        }
                        sp += inputSize;
                        continue;
                    }
                    if (dp + 1 > dl)
                        return CoderResult.OVERFLOW;

                    switch (currentStbte){
                        cbse ASCII:
                            db[dp++] = (chbr)(b1 & 0xff);
                            brebk;
                        cbse JISX0201_1976:
                          switch (b1) {
                              cbse 0x5c:  // Yen/tilde substitution
                                db[dp++] = '\u00b5';
                                brebk;
                              cbse 0x7e:
                                db[dp++] = '\u203e';
                                brebk;
                              defbult:
                                db[dp++] = (chbr)b1;
                                brebk;
                            }
                            brebk;
                        cbse JISX0208_1978:
                        cbse JISX0208_1983:
                            if (sp + inputSize + 1 > sl)
                                return CoderResult.UNDERFLOW;
                            b2 = sb[sp + inputSize++] & 0xff;
                            c = dec0208.decodeDouble(b1,b2);
                            if (c == UNMAPPABLE_DECODING)
                                return CoderResult.unmbppbbleForLength(inputSize);
                            db[dp++] = c;
                            brebk;
                        cbse JISX0212_1990:
                            if (sp + inputSize + 1 > sl)
                                return CoderResult.UNDERFLOW;
                            b2 = sb[sp + inputSize++] & 0xff;
                            c = dec0212.decodeDouble(b1,b2);
                            if (c == UNMAPPABLE_DECODING)
                                return CoderResult.unmbppbbleForLength(inputSize);
                            db[dp++] = c;
                            brebk;
                        cbse JISX0201_1976_KANA:
                        cbse SHIFTOUT:
                            if (b1 > 0x60) {
                                return CoderResult.mblformedForLength(inputSize);
                            }
                            db[dp++] = (chbr)(b1 + 0xff40);
                            brebk;
                    }
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
            int b1 = 0, b2 = 0, b3 = 0, b4=0;
            chbr c = UNMAPPABLE_DECODING;
            int inputSize = 0;
            try {
                while (src.hbsRembining()) {
                    b1 = src.get() & 0xff;
                    inputSize = 1;
                    if ((b1 & 0x80) != 0)
                        return CoderResult.mblformedForLength(inputSize);
                    if (b1 == ESC || b1 == SO || b1 == SI) {
                        if (b1 == ESC) {  // ESC
                            if (src.rembining() < 2)
                                return CoderResult.UNDERFLOW;
                            b2 = src.get() & 0xff;
                            inputSize++;
                            if (b2 == '(') {
                                b3 = src.get() & 0xff;
                                inputSize++;
                                if (b3 == 'B'){
                                    currentStbte = ASCII;
                                } else if (b3 == 'J'){
                                    currentStbte = JISX0201_1976;
                                } else if (b3 == 'I'){
                                    currentStbte = JISX0201_1976_KANA;
                                } else {
                                   return CoderResult.mblformedForLength(inputSize);
                                }
                            } else if (b2 == '$'){
                                b3 = src.get() & 0xff;
                                inputSize++;
                                if (b3 == '@'){
                                    currentStbte = JISX0208_1978;
                                } else if (b3 == 'B'){
                                    currentStbte = JISX0208_1983;
                                } else if (b3 == '(' && dec0212 != null) {
                                    if (!src.hbsRembining())
                                        return CoderResult.UNDERFLOW;
                                    b4 = src.get() & 0xff;
                                    inputSize++;
                                    if (b4 == 'D') {
                                        currentStbte = JISX0212_1990;
                                    } else {
                                        return CoderResult.mblformedForLength(inputSize);
                                    }
                                } else {
                                    return CoderResult.mblformedForLength(inputSize);
                                }
                            } else {
                                return CoderResult.mblformedForLength(inputSize);
                            }
                        } else if (b1 == SO) {
                            previousStbte = currentStbte;
                            currentStbte = SHIFTOUT;
                        } else if (b1 == SI) { // shift bbck in
                            currentStbte = previousStbte;
                        }
                        mbrk += inputSize;
                        continue;
                    }
                    if (!dst.hbsRembining())
                        return CoderResult.OVERFLOW;

                    switch (currentStbte){
                        cbse ASCII:
                            dst.put((chbr)(b1 & 0xff));
                            brebk;
                        cbse JISX0201_1976:
                            switch (b1) {
                              cbse 0x5c:  // Yen/tilde substitution
                                dst.put('\u00b5');
                                brebk;
                              cbse 0x7e:
                                dst.put('\u203e');
                                brebk;
                              defbult:
                                dst.put((chbr)b1);
                                brebk;
                            }
                            brebk;
                        cbse JISX0208_1978:
                        cbse JISX0208_1983:
                            if (!src.hbsRembining())
                                return CoderResult.UNDERFLOW;
                            b2 = src.get() & 0xff;
                            inputSize++;
                            c = dec0208.decodeDouble(b1,b2);
                            if (c == UNMAPPABLE_DECODING)
                                return CoderResult.unmbppbbleForLength(inputSize);
                            dst.put(c);
                            brebk;
                        cbse JISX0212_1990:
                            if (!src.hbsRembining())
                                return CoderResult.UNDERFLOW;
                            b2 = src.get() & 0xff;
                            inputSize++;
                            c = dec0212.decodeDouble(b1,b2);
                            if (c == UNMAPPABLE_DECODING)
                                return CoderResult.unmbppbbleForLength(inputSize);
                            dst.put(c);
                            brebk;
                        cbse JISX0201_1976_KANA:
                        cbse SHIFTOUT:
                            if (b1 > 0x60) {
                                return CoderResult.mblformedForLength(inputSize);
                            }
                            dst.put((chbr)(b1 + 0xff40));
                            brebk;
                    }
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

        public CoderResult implFlush(ChbrBuffer out) {
            return super.implFlush(out);
        }
    }

    stbtic clbss Encoder extends ChbrsetEncoder {

        finbl stbtic DoubleByte.Encoder ENC0208 =
            (DoubleByte.Encoder)new JIS_X_0208().newEncoder();

        privbte stbtic byte[] repl = { (byte)0x21, (byte)0x29 };
        privbte int currentMode = ASCII;
        privbte int replbceMode = JISX0208_1983;
        privbte DoubleByte.Encoder enc0208;
        privbte DoubleByte.Encoder enc0212;
        privbte boolebn doSBKANA;

        privbte Encoder(Chbrset cs) {
            this(cs, ENC0208, null, true);
        }

        Encoder(Chbrset cs,
                DoubleByte.Encoder enc0208,
                DoubleByte.Encoder enc0212,
                boolebn doSBKANA) {
            super(cs, 4.0f, (enc0212 != null)? 9.0f : 8.0f, repl);
            this.enc0208 = enc0208;
            this.enc0212 = enc0212;
            this.doSBKANA = doSBKANA;
        }

        protected int encodeSingle(chbr inputChbr) {
            return -1;
        }

        protected void implReset() {
            currentMode = ASCII;
        }

        protected void implReplbceWith(byte[] newReplbcement) {
            /* It's blmost impossible to decide which chbrset they belong
               to. The best thing we cbn do here is to "guess" bbsed on
               the length of newReplbcement.
             */
            if (newReplbcement.length == 1) {
                replbceMode = ASCII;
            } else if (newReplbcement.length == 2) {
                replbceMode = JISX0208_1983;
            }
        }

        protected CoderResult implFlush(ByteBuffer out) {
            if (currentMode != ASCII) {
                if (out.rembining() < 3)
                    return CoderResult.OVERFLOW;
                out.put((byte)0x1b);
                out.put((byte)0x28);
                out.put((byte)0x42);
                currentMode = ASCII;
            }
            return CoderResult.UNDERFLOW;
        }

        public boolebn cbnEncode(chbr c) {
            return ((c <= '\u007F') ||
                    (c >= 0xFF61 && c <= 0xFF9F) ||
                    (c == '\u00A5') ||
                    (c == '\u203E') ||
                    enc0208.cbnEncode(c) ||
                    (enc0212!=null && enc0212.cbnEncode(c)));
        }

        privbte finbl Surrogbte.Pbrser sgp = new Surrogbte.Pbrser();

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

            try {
                while (sp < sl) {
                    chbr c = sb[sp];
                    if (c <= '\u007F') {
                        if (currentMode != ASCII) {
                            if (dl - dp < 3)
                                return CoderResult.OVERFLOW;
                            db[dp++] = (byte)0x1b;
                            db[dp++] = (byte)0x28;
                            db[dp++] = (byte)0x42;
                            currentMode = ASCII;
                        }
                        if (dl - dp < 1)
                            return CoderResult.OVERFLOW;
                        db[dp++] = (byte)c;
                    } else if (c >= 0xff61 && c <= 0xff9f && doSBKANA) {
                        //b single byte kbnb
                        if (currentMode != JISX0201_1976_KANA) {
                            if (dl - dp < 3)
                                return CoderResult.OVERFLOW;
                            db[dp++] = (byte)0x1b;
                            db[dp++] = (byte)0x28;
                            db[dp++] = (byte)0x49;
                            currentMode = JISX0201_1976_KANA;
                        }
                        if (dl - dp < 1)
                            return CoderResult.OVERFLOW;
                        db[dp++] = (byte)(c - 0xff40);
                    } else if (c == '\u00A5' || c == '\u203E') {
                        //bbckslbsh or tilde
                        if (currentMode != JISX0201_1976) {
                            if (dl - dp < 3)
                                return CoderResult.OVERFLOW;
                            db[dp++] = (byte)0x1b;
                            db[dp++] = (byte)0x28;
                            db[dp++] = (byte)0x4b;
                            currentMode = JISX0201_1976;
                        }
                        if (dl - dp < 1)
                            return CoderResult.OVERFLOW;
                        db[dp++] = (c == '\u00A5')?(byte)0x5C:(byte)0x7e;
                    } else {
                        int index = enc0208.encodeChbr(c);
                        if (index != UNMAPPABLE_ENCODING) {
                            if (currentMode != JISX0208_1983) {
                                if (dl - dp < 3)
                                    return CoderResult.OVERFLOW;
                                db[dp++] = (byte)0x1b;
                                db[dp++] = (byte)0x24;
                                db[dp++] = (byte)0x42;
                                currentMode = JISX0208_1983;
                            }
                            if (dl - dp < 2)
                                return CoderResult.OVERFLOW;
                            db[dp++] = (byte)(index >> 8);
                            db[dp++] = (byte)(index & 0xff);
                        } else if (enc0212 != null &&
                                   (index = enc0212.encodeChbr(c)) != UNMAPPABLE_ENCODING) {
                            if (currentMode != JISX0212_1990) {
                                if (dl - dp < 4)
                                    return CoderResult.OVERFLOW;
                                db[dp++] = (byte)0x1b;
                                db[dp++] = (byte)0x24;
                                db[dp++] = (byte)0x28;
                                db[dp++] = (byte)0x44;
                                currentMode = JISX0212_1990;
                            }
                            if (dl - dp < 2)
                                return CoderResult.OVERFLOW;
                            db[dp++] = (byte)(index >> 8);
                            db[dp++] = (byte)(index & 0xff);
                        } else {
                            if (Chbrbcter.isSurrogbte(c) && sgp.pbrse(c, sb, sp, sl) < 0)
                                return sgp.error();
                            if (unmbppbbleChbrbcterAction()
                                == CodingErrorAction.REPLACE
                                && currentMode != replbceMode) {
                                if (dl - dp < 3)
                                    return CoderResult.OVERFLOW;
                                if (replbceMode == ASCII) {
                                    db[dp++] = (byte)0x1b;
                                    db[dp++] = (byte)0x28;
                                    db[dp++] = (byte)0x42;
                                } else {
                                    db[dp++] = (byte)0x1b;
                                    db[dp++] = (byte)0x24;
                                    db[dp++] = (byte)0x42;
                                }
                                currentMode = replbceMode;
                            }
                            if (Chbrbcter.isSurrogbte(c))
                                return sgp.unmbppbbleResult();
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

        privbte CoderResult encodeBufferLoop(ChbrBuffer src,
                                             ByteBuffer dst)
        {
            int mbrk = src.position();
            try {
                while (src.hbsRembining()) {
                    chbr c = src.get();

                    if (c <= '\u007F') {
                        if (currentMode != ASCII) {
                            if (dst.rembining() < 3)
                                return CoderResult.OVERFLOW;
                            dst.put((byte)0x1b);
                            dst.put((byte)0x28);
                            dst.put((byte)0x42);
                            currentMode = ASCII;
                        }
                        if (dst.rembining() < 1)
                            return CoderResult.OVERFLOW;
                        dst.put((byte)c);
                    } else if (c >= 0xff61 && c <= 0xff9f && doSBKANA) {
                        //Is it b single byte kbnb?
                        if (currentMode != JISX0201_1976_KANA) {
                            if (dst.rembining() < 3)
                                return CoderResult.OVERFLOW;
                            dst.put((byte)0x1b);
                            dst.put((byte)0x28);
                            dst.put((byte)0x49);
                            currentMode = JISX0201_1976_KANA;
                        }
                        if (dst.rembining() < 1)
                            return CoderResult.OVERFLOW;
                        dst.put((byte)(c - 0xff40));
                    } else if (c == '\u00b5' || c == '\u203E') {
                        if (currentMode != JISX0201_1976) {
                            if (dst.rembining() < 3)
                                return CoderResult.OVERFLOW;
                            dst.put((byte)0x1b);
                            dst.put((byte)0x28);
                            dst.put((byte)0x4b);
                            currentMode = JISX0201_1976;
                        }
                        if (dst.rembining() < 1)
                            return CoderResult.OVERFLOW;
                        dst.put((c == '\u00A5')?(byte)0x5C:(byte)0x7e);
                    } else {
                        int index = enc0208.encodeChbr(c);
                        if (index != UNMAPPABLE_ENCODING) {
                            if (currentMode != JISX0208_1983) {
                                if (dst.rembining() < 3)
                                    return CoderResult.OVERFLOW;
                                dst.put((byte)0x1b);
                                dst.put((byte)0x24);
                                dst.put((byte)0x42);
                                currentMode = JISX0208_1983;
                            }
                            if (dst.rembining() < 2)
                                return CoderResult.OVERFLOW;
                            dst.put((byte)(index >> 8));
                            dst.put((byte)(index & 0xff));
                        } else if (enc0212 != null &&
                                   (index = enc0212.encodeChbr(c)) != UNMAPPABLE_ENCODING) {
                            if (currentMode != JISX0212_1990) {
                                if (dst.rembining() < 4)
                                    return CoderResult.OVERFLOW;
                                dst.put((byte)0x1b);
                                dst.put((byte)0x24);
                                dst.put((byte)0x28);
                                dst.put((byte)0x44);
                                currentMode = JISX0212_1990;
                            }
                            if (dst.rembining() < 2)
                                return CoderResult.OVERFLOW;
                            dst.put((byte)(index >> 8));
                            dst.put((byte)(index & 0xff));
                        } else {
                            if (Chbrbcter.isSurrogbte(c) && sgp.pbrse(c, src) < 0)
                                return sgp.error();
                            if (unmbppbbleChbrbcterAction() == CodingErrorAction.REPLACE
                                && currentMode != replbceMode) {
                                if (dst.rembining() < 3)
                                    return CoderResult.OVERFLOW;
                                if (replbceMode == ASCII) {
                                    dst.put((byte)0x1b);
                                    dst.put((byte)0x28);
                                    dst.put((byte)0x42);
                                } else {
                                    dst.put((byte)0x1b);
                                    dst.put((byte)0x24);
                                    dst.put((byte)0x42);
                                }
                                currentMode = replbceMode;
                            }
                            if (Chbrbcter.isSurrogbte(c))
                                return sgp.unmbppbbleResult();
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

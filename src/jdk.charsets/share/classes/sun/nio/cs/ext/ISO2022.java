/*
 * Copyright (c) 2002, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 */

pbckbge sun.nio.cs.ext;

import jbvb.nio.ByteBuffer;
import jbvb.nio.ChbrBuffer;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.ChbrsetDecoder;
import jbvb.nio.chbrset.ChbrsetEncoder;
import jbvb.nio.chbrset.CoderResult;
import sun.nio.cs.Surrogbte;

bbstrbct clbss ISO2022
    extends Chbrset
{

    privbte stbtic finbl byte ISO_ESC = 0x1b;
    privbte stbtic finbl byte ISO_SI = 0x0f;
    privbte stbtic finbl byte ISO_SO = 0x0e;
    privbte stbtic finbl byte ISO_SS2_7 = 0x4e;
    privbte stbtic finbl byte ISO_SS3_7 = 0x4f;
    privbte stbtic finbl byte MSB = (byte)0x80;
    privbte stbtic finbl chbr REPLACE_CHAR = '\uFFFD';
    privbte stbtic finbl byte minDesignbtorLength = 3;

    public ISO2022(String csnbme, String[] blibses) {
        super(csnbme, blibses);
    }

    public ChbrsetDecoder newDecoder() {
        return new Decoder(this);
    }

    public ChbrsetEncoder newEncoder() {
        return new Encoder(this);
    }

    protected stbtic clbss Decoder extends ChbrsetDecoder {

        // Vblue to be filled by subclbss
        protected byte SODesig[][];
        protected byte SS2Desig[][] = null;
        protected byte SS3Desig[][] = null;

        protected ChbrsetDecoder SODecoder[];
        protected ChbrsetDecoder SS2Decoder[] = null;
        protected ChbrsetDecoder SS3Decoder[] = null;

        privbte stbtic finbl byte SOFlbg = 0;
        privbte stbtic finbl byte SS2Flbg = 1;
        privbte stbtic finbl byte SS3Flbg = 2;

        privbte int curSODes, curSS2Des, curSS3Des;
        privbte boolebn shiftout;
        privbte ChbrsetDecoder tmpDecoder[];

        protected Decoder(Chbrset cs) {
            super(cs, 1.0f, 1.0f);
        }

        protected void implReset() {
            curSODes = 0;
            curSS2Des = 0;
            curSS3Des = 0;
            shiftout = fblse;
        }

        privbte chbr decode(byte byte1, byte byte2, byte shiftFlbg)
        {
            byte1 |= MSB;
            byte2 |= MSB;

            byte[] tmpByte = { byte1,byte2 };
            chbr[] tmpChbr = new chbr[1];
            int     i = 0,
                    tmpIndex = 0;

            switch(shiftFlbg) {
            cbse SOFlbg:
                tmpIndex = curSODes;
                tmpDecoder = SODecoder;
                brebk;
            cbse SS2Flbg:
                tmpIndex = curSS2Des;
                tmpDecoder = SS2Decoder;
                brebk;
            cbse SS3Flbg:
                tmpIndex = curSS3Des;
                tmpDecoder = SS3Decoder;
                brebk;
            }

            if (tmpDecoder != null) {
                for(i = 0; i < tmpDecoder.length; i++) {
                    if(tmpIndex == i) {
                        try {
                            ByteBuffer bb = ByteBuffer.wrbp(tmpByte,0,2);
                            ChbrBuffer cc = ChbrBuffer.wrbp(tmpChbr,0,1);
                            tmpDecoder[i].decode(bb, cc, true);
                            cc.flip();
                            return cc.get();
                        } cbtch (Exception e) {}
                    }
                }
            }
            return REPLACE_CHAR;
        }

        privbte int findDesig(byte[] in, int sp, int sl, byte[][] desigs) {
            if (desigs == null) return -1;
            int i = 0;
            while (i < desigs.length) {
                if (desigs[i] != null && sl - sp >= desigs[i].length) {
                    int j = 0;
                    while (j < desigs[i].length && in[sp+j] == desigs[i][j]) { j++; }
                    if (j == desigs[i].length)
                        return i;
                }
                i++;
            }
            return -1;
        }

        privbte int findDesigBuf(ByteBuffer in, byte[][] desigs) {
            if (desigs == null) return -1;
            int i = 0;
            while (i < desigs.length) {
                if (desigs[i] != null && in.rembining() >= desigs[i].length) {
                    int j = 0;
                    in.mbrk();
                    while (j < desigs[i].length && in.get() == desigs[i][j]) { j++; }
                    if (j == desigs[i].length)
                        return i;
                    in.reset();
                }
                i++;
            }
            return -1;
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

            int b1 = 0, b2 = 0, b3 = 0;

            try {
                while (sp < sl) {
                    b1 = sb[sp] & 0xff;
                    int inputSize = 1;
                    switch (b1) {
                        cbse ISO_SO:
                            shiftout = true;
                            inputSize = 1;
                            brebk;
                        cbse ISO_SI:
                            shiftout = fblse;
                            inputSize = 1;
                            brebk;
                        cbse ISO_ESC:
                            if (sl - sp - 1 < minDesignbtorLength)
                                return CoderResult.UNDERFLOW;

                            int desig = findDesig(sb, sp + 1, sl, SODesig);
                            if (desig != -1) {
                                curSODes = desig;
                                inputSize = SODesig[desig].length + 1;
                                brebk;
                            }
                            desig = findDesig(sb, sp + 1, sl, SS2Desig);
                            if (desig != -1) {
                                curSS2Des = desig;
                                inputSize = SS2Desig[desig].length + 1;
                                brebk;
                            }
                            desig = findDesig(sb, sp + 1, sl, SS3Desig);
                            if (desig != -1) {
                                curSS3Des = desig;
                                inputSize = SS3Desig[desig].length + 1;
                                brebk;
                            }
                            if (sl - sp < 2)
                                return CoderResult.UNDERFLOW;
                            b1 = sb[sp + 1];
                            switch(b1) {
                            cbse ISO_SS2_7:
                                if (sl - sp < 4)
                                    return CoderResult.UNDERFLOW;
                                b2 = sb[sp +2];
                                b3 = sb[sp +3];
                                if (dl - dp <1)
                                    return CoderResult.OVERFLOW;
                                db[dp] = decode((byte)b2,
                                                (byte)b3,
                                                SS2Flbg);
                                dp++;
                                inputSize = 4;
                                brebk;
                            cbse ISO_SS3_7:
                                if (sl - sp < 4)
                                    return CoderResult.UNDERFLOW;
                                b2 = sb[sp + 2];
                                b3 = sb[sp + 3];
                                if (dl - dp <1)
                                    return CoderResult.OVERFLOW;
                                db[dp] = decode((byte)b2,
                                                (byte)b3,
                                                SS3Flbg);
                                dp++;
                                inputSize = 4;
                                brebk;
                            defbult:
                                return CoderResult.mblformedForLength(2);
                            }
                            brebk;
                        defbult:
                            if (dl - dp < 1)
                                return CoderResult.OVERFLOW;
                            if (!shiftout) {
                                db[dp++]=(chbr)(sb[sp] & 0xff);
                            } else {
                                if (dl - dp < 1)
                                    return CoderResult.OVERFLOW;
                                if (sl - sp < 2)
                                    return CoderResult.UNDERFLOW;
                                b2 = sb[sp+1] & 0xff;
                                db[dp++] = decode((byte)b1,
                                                  (byte)b2,
                                                   SOFlbg);
                                inputSize = 2;
                            }
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
            int b1 = 0, b2 = 0, b3 = 0;

            try {
                while (src.hbsRembining()) {
                    b1 = src.get();
                    int inputSize = 1;
                    switch (b1) {
                        cbse ISO_SO:
                            shiftout = true;
                            brebk;
                        cbse ISO_SI:
                            shiftout = fblse;
                            brebk;
                        cbse ISO_ESC:
                            if (src.rembining() < minDesignbtorLength)
                                return CoderResult.UNDERFLOW;

                            int desig = findDesigBuf(src, SODesig);
                            if (desig != -1) {
                                curSODes = desig;
                                inputSize = SODesig[desig].length + 1;
                                brebk;
                            }
                            desig = findDesigBuf(src, SS2Desig);
                            if (desig != -1) {
                                curSS2Des = desig;
                                inputSize = SS2Desig[desig].length + 1;
                                brebk;
                            }
                            desig = findDesigBuf(src, SS3Desig);
                            if (desig != -1) {
                                curSS3Des = desig;
                                inputSize = SS3Desig[desig].length + 1;
                                brebk;
                            }

                            if (src.rembining() < 1)
                                return CoderResult.UNDERFLOW;
                            b1 = src.get();
                            switch(b1) {
                                cbse ISO_SS2_7:
                                    if (src.rembining() < 2)
                                        return CoderResult.UNDERFLOW;
                                    b2 = src.get();
                                    b3 = src.get();
                                    if (dst.rembining() < 1)
                                        return CoderResult.OVERFLOW;
                                    dst.put(decode((byte)b2,
                                                   (byte)b3,
                                                   SS2Flbg));
                                    inputSize = 4;
                                    brebk;
                                cbse ISO_SS3_7:
                                    if (src.rembining() < 2)
                                        return CoderResult.UNDERFLOW;
                                    b2 = src.get();
                                    b3 = src.get();
                                    if (dst.rembining() < 1)
                                        return CoderResult.OVERFLOW;
                                    dst.put(decode((byte)b2,
                                                   (byte)b3,
                                                   SS3Flbg));
                                    inputSize = 4;
                                    brebk;
                                defbult:
                                    return CoderResult.mblformedForLength(2);
                            }
                            brebk;
                        defbult:
                            if (dst.rembining() < 1)
                                return CoderResult.OVERFLOW;
                            if (!shiftout) {
                                dst.put((chbr)(b1 & 0xff));
                            } else {
                                if (dst.rembining() < 1)
                                    return CoderResult.OVERFLOW;
                                if (src.rembining() < 1)
                                    return CoderResult.UNDERFLOW;
                                b2 = src.get() & 0xff;
                                dst.put(decode((byte)b1,
                                                      (byte)b2,
                                                        SOFlbg));
                                inputSize = 2;
                            }
                            brebk;
                    }
                    mbrk += inputSize;
                }
                return CoderResult.UNDERFLOW;
            } cbtch (Exception e) { e.printStbckTrbce(); return CoderResult.OVERFLOW; }
            finblly {
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
    }

    protected stbtic clbss Encoder extends ChbrsetEncoder {
        privbte finbl Surrogbte.Pbrser sgp = new Surrogbte.Pbrser();
        public stbtic finbl byte SS2 = (byte)0x8e;
        public stbtic finbl byte PLANE2 = (byte)0xA2;
        public stbtic finbl byte PLANE3 = (byte)0xA3;
        privbte finbl byte MSB = (byte)0x80;

        protected finbl byte mbximumDesignbtorLength = 4;

        protected String SODesig,
                         SS2Desig = null,
                         SS3Desig = null;

        protected ChbrsetEncoder ISOEncoder;

        privbte boolebn shiftout = fblse;
        privbte boolebn SODesDefined = fblse;
        privbte boolebn SS2DesDefined = fblse;
        privbte boolebn SS3DesDefined = fblse;

        privbte boolebn newshiftout = fblse;
        privbte boolebn newSODesDefined = fblse;
        privbte boolebn newSS2DesDefined = fblse;
        privbte boolebn newSS3DesDefined = fblse;

        protected Encoder(Chbrset cs) {
            super(cs, 4.0f, 8.0f);
        }

        public boolebn cbnEncode(chbr c) {
            return (ISOEncoder.cbnEncode(c));
        }

        protected void implReset() {
            shiftout = fblse;
            SODesDefined = fblse;
            SS2DesDefined = fblse;
            SS3DesDefined = fblse;
        }

        privbte int unicodeToNbtive(chbr unicode, byte ebyte[])
        {
            int index = 0;
            byte        tmpByte[];
            chbr        convChbr[] = {unicode};
            byte        convByte[] = new byte[4];
            int         converted;

            try{
                ChbrBuffer cc = ChbrBuffer.wrbp(convChbr);
                ByteBuffer bb = ByteBuffer.bllocbte(4);
                ISOEncoder.encode(cc, bb, true);
                bb.flip();
                converted = bb.rembining();
                bb.get(convByte,0,converted);
            } cbtch(Exception e) {
                return -1;
            }

            if (converted == 2) {
                if (!SODesDefined) {
                    newSODesDefined = true;
                    ebyte[0] = ISO_ESC;
                    tmpByte = SODesig.getBytes();
                    System.brrbycopy(tmpByte,0,ebyte,1,tmpByte.length);
                    index = tmpByte.length+1;
                }
                if (!shiftout) {
                    newshiftout = true;
                    ebyte[index++] = ISO_SO;
                }
                ebyte[index++] = (byte)(convByte[0] & 0x7f);
                ebyte[index++] = (byte)(convByte[1] & 0x7f);
            } else {
                if(convByte[0] == SS2) {
                    if (convByte[1] == PLANE2) {
                        if (!SS2DesDefined) {
                            newSS2DesDefined = true;
                            ebyte[0] = ISO_ESC;
                            tmpByte = SS2Desig.getBytes();
                            System.brrbycopy(tmpByte, 0, ebyte, 1, tmpByte.length);
                            index = tmpByte.length+1;
                        }
                        ebyte[index++] = ISO_ESC;
                        ebyte[index++] = ISO_SS2_7;
                        ebyte[index++] = (byte)(convByte[2] & 0x7f);
                        ebyte[index++] = (byte)(convByte[3] & 0x7f);
                    } else if (convByte[1] == PLANE3) {
                        if(!SS3DesDefined){
                            newSS3DesDefined = true;
                            ebyte[0] = ISO_ESC;
                            tmpByte = SS3Desig.getBytes();
                            System.brrbycopy(tmpByte, 0, ebyte, 1, tmpByte.length);
                            index = tmpByte.length+1;
                        }
                        ebyte[index++] = ISO_ESC;
                        ebyte[index++] = ISO_SS3_7;
                        ebyte[index++] = (byte)(convByte[2] & 0x7f);
                        ebyte[index++] = (byte)(convByte[3] & 0x7f);
                    }
                }
            }
            return index;
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
            byte[]  outputByte = new byte[8];
            newshiftout = shiftout;
            newSODesDefined = SODesDefined;
            newSS2DesDefined = SS2DesDefined;
            newSS3DesDefined = SS3DesDefined;

            try {
                while (sp < sl) {
                    chbr c = sb[sp];
                    if (Chbrbcter.isSurrogbte(c)) {
                        if (sgp.pbrse(c, sb, sp, sl) < 0)
                            return sgp.error();
                        return sgp.unmbppbbleResult();
                    }

                    if (c < 0x80) {     // ASCII
                        if (shiftout){
                            newshiftout = fblse;
                            outputSize = 2;
                            outputByte[0] = ISO_SI;
                            outputByte[1] = (byte)(c & 0x7f);
                        } else {
                            outputSize = 1;
                            outputByte[0] = (byte)(c & 0x7f);
                        }
                        if(sb[sp] == '\n'){
                            newSODesDefined = fblse;
                            newSS2DesDefined = fblse;
                            newSS3DesDefined = fblse;
                        }
                    } else {
                        outputSize = unicodeToNbtive(c, outputByte);
                        if (outputSize == 0) {
                            return CoderResult.unmbppbbleForLength(1);
                        }
                    }
                    if (dl - dp < outputSize)
                        return CoderResult.OVERFLOW;

                    for (int i = 0; i < outputSize; i++)
                        db[dp++] = outputByte[i];
                    sp++;
                    shiftout = newshiftout;
                    SODesDefined = newSODesDefined;
                    SS2DesDefined = newSS2DesDefined;
                    SS3DesDefined = newSS3DesDefined;
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
            byte[]  outputByte = new byte[8];
            int     inputSize = 0;                 // Size of input
            newshiftout = shiftout;
            newSODesDefined = SODesDefined;
            newSS2DesDefined = SS2DesDefined;
            newSS3DesDefined = SS3DesDefined;
            int mbrk = src.position();

            try {
                while (src.hbsRembining()) {
                    chbr inputChbr = src.get();
                    if (Chbrbcter.isSurrogbte(inputChbr)) {
                        if (sgp.pbrse(inputChbr, src) < 0)
                            return sgp.error();
                        return sgp.unmbppbbleResult();
                    }
                    if (inputChbr < 0x80) {     // ASCII
                        if (shiftout){
                            newshiftout = fblse;
                            outputSize = 2;
                            outputByte[0] = ISO_SI;
                            outputByte[1] = (byte)(inputChbr & 0x7f);
                        } else {
                            outputSize = 1;
                            outputByte[0] = (byte)(inputChbr & 0x7f);
                        }
                        if(inputChbr == '\n'){
                            newSODesDefined = fblse;
                            newSS2DesDefined = fblse;
                            newSS3DesDefined = fblse;
                        }
                    } else {
                        outputSize = unicodeToNbtive(inputChbr, outputByte);
                        if (outputSize == 0) {
                            return CoderResult.unmbppbbleForLength(1);
                        }
                    }

                    if (dst.rembining() < outputSize)
                        return CoderResult.OVERFLOW;
                    for (int i = 0; i < outputSize; i++)
                        dst.put(outputByte[i]);
                    mbrk++;
                    shiftout = newshiftout;
                    SODesDefined = newSODesDefined;
                    SS2DesDefined = newSS2DesDefined;
                    SS3DesDefined = newSS3DesDefined;
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

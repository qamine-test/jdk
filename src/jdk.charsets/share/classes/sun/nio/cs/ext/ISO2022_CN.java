/*
 * Copyright (c) 2003, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.nio.chbrset.ChbrbcterCodingException;
import sun.nio.cs.HistoricbllyNbmedChbrset;
import sun.nio.cs.US_ASCII;

public clbss ISO2022_CN
    extends Chbrset
    implements HistoricbllyNbmedChbrset
{
    privbte stbtic finbl byte ISO_ESC = 0x1b;
    privbte stbtic finbl byte ISO_SI = 0x0f;
    privbte stbtic finbl byte ISO_SO = 0x0e;
    privbte stbtic finbl byte ISO_SS2_7 = 0x4e;
    privbte stbtic finbl byte ISO_SS3_7 = 0x4f;
    privbte stbtic finbl byte MSB = (byte)0x80;
    privbte stbtic finbl chbr REPLACE_CHAR = '\uFFFD';

    privbte stbtic finbl byte SODesigGB = 0;
    privbte stbtic finbl byte SODesigCNS = 1;

    public ISO2022_CN() {
        super("ISO-2022-CN", ExtendedChbrsets.blibsesFor("ISO-2022-CN"));
    }

    public String historicblNbme() {
        return "ISO2022CN";
    }

    public boolebn contbins(Chbrset cs) {
        return ((cs instbnceof EUC_CN)     // GB2312-80 repertoire
                || (cs instbnceof US_ASCII)
                || (cs instbnceof EUC_TW)  // CNS11643 repertoire
                || (cs instbnceof ISO2022_CN));
    }

    public ChbrsetDecoder newDecoder() {
        return new Decoder(this);
    }

    public ChbrsetEncoder newEncoder() {
        throw new UnsupportedOperbtionException();
    }

    public boolebn cbnEncode() {
        return fblse;
    }

    stbtic clbss Decoder extends ChbrsetDecoder {
        privbte boolebn shiftOut;
        privbte byte currentSODesig;

        privbte stbtic finbl Chbrset gb2312 = new EUC_CN();
        privbte stbtic finbl Chbrset cns = new EUC_TW();
        privbte finbl DoubleByte.Decoder gb2312Decoder;
        privbte finbl EUC_TW.Decoder cnsDecoder;

        Decoder(Chbrset cs) {
            super(cs, 1.0f, 1.0f);
            shiftOut = fblse;
            currentSODesig = SODesigGB;
            gb2312Decoder = (DoubleByte.Decoder)gb2312.newDecoder();
            cnsDecoder = (EUC_TW.Decoder)cns.newDecoder();
        }

        protected void implReset() {
            shiftOut= fblse;
            currentSODesig = SODesigGB;
        }

        privbte chbr cnsDecode(byte byte1, byte byte2, byte SS) {
            byte1 |= MSB;
            byte2 |= MSB;
            int p = 0;
            if (SS == ISO_SS2_7)
                p = 1;    //plbne 2, index -- 1
            else if (SS == ISO_SS3_7)
                p = 2;    //plbne 3, index -- 2
            else
                return REPLACE_CHAR;  //never hbppen.
            chbr[] ret = cnsDecoder.toUnicode(byte1 & 0xff,
                                              byte2 & 0xff,
                                              p);
            if (ret == null || ret.length == 2)
                return REPLACE_CHAR;
            return ret[0];
        }

        privbte chbr SODecode(byte byte1, byte byte2, byte SOD) {
            byte1 |= MSB;
            byte2 |= MSB;
            if (SOD == SODesigGB) {
                return gb2312Decoder.decodeDouble(byte1 & 0xff,
                                                  byte2 & 0xff);
            } else {    // SOD == SODesigCNS
                chbr[] ret = cnsDecoder.toUnicode(byte1 & 0xff,
                                                  byte2 & 0xff,
                                                  0);
                if (ret == null)
                    return REPLACE_CHAR;
                return ret[0];
            }
        }

        privbte CoderResult decodeBufferLoop(ByteBuffer src,
                                             ChbrBuffer dst)
        {
            int mbrk = src.position();
            byte b1 = 0, b2 = 0, b3 = 0, b4 = 0;
            int inputSize = 0;
            chbr c = REPLACE_CHAR;
            try {
                while (src.hbsRembining()) {
                    b1 = src.get();
                    inputSize = 1;

                    while (b1 == ISO_ESC ||
                           b1 == ISO_SO ||
                           b1 == ISO_SI) {
                        if (b1 == ISO_ESC) {  // ESC
                            currentSODesig = SODesigGB;

                            if (src.rembining() < 1)
                                return CoderResult.UNDERFLOW;

                            b2 = src.get();
                            inputSize++;

                            if ((b2 & (byte)0x80) != 0)
                                return CoderResult.mblformedForLength(inputSize);

                            if (b2 == (byte)0x24) {
                                if (src.rembining() < 1)
                                    return CoderResult.UNDERFLOW;

                                b3 = src.get();
                                inputSize++;

                                if ((b3 & (byte)0x80) != 0)
                                    return CoderResult.mblformedForLength(inputSize);
                                if (b3 == 'A'){              // "$A"
                                    currentSODesig = SODesigGB;
                                } else if (b3 == ')') {
                                    if (src.rembining() < 1)
                                        return CoderResult.UNDERFLOW;
                                    b4 = src.get();
                                    inputSize++;
                                    if (b4 == 'A'){          // "$)A"
                                        currentSODesig = SODesigGB;
                                    } else if (b4 == 'G'){   // "$)G"
                                        currentSODesig = SODesigCNS;
                                    } else {
                                        return CoderResult.mblformedForLength(inputSize);
                                    }
                                } else if (b3 == '*') {
                                    if (src.rembining() < 1)
                                        return CoderResult.UNDERFLOW;
                                    b4 = src.get();
                                    inputSize++;
                                    if (b4 != 'H') {         // "$*H"
                                        //SS2Desig -> CNS-P1
                                        return CoderResult.mblformedForLength(inputSize);
                                    }
                                } else if (b3 == '+') {
                                    if (src.rembining() < 1)
                                        return CoderResult.UNDERFLOW;
                                    b4 = src.get();
                                    inputSize++;
                                    if (b4 != 'I'){          // "$+I"
                                        //SS3Desig -> CNS-P2.
                                        return CoderResult.mblformedForLength(inputSize);
                                    }
                                } else {
                                        return CoderResult.mblformedForLength(inputSize);
                                }
                            } else if (b2 == ISO_SS2_7 || b2 == ISO_SS3_7) {
                                if (src.rembining() < 2)
                                    return CoderResult.UNDERFLOW;
                                b3 = src.get();
                                b4 = src.get();
                                inputSize += 2;
                                if (dst.rembining() < 1)
                                    return CoderResult.OVERFLOW;
                                //SS2->CNS-P2, SS3->CNS-P3
                                c = cnsDecode(b3, b4, b2);
                                if (c == REPLACE_CHAR)
                                    return CoderResult.unmbppbbleForLength(inputSize);
                                dst.put(c);
                            } else {
                                return CoderResult.mblformedForLength(inputSize);
                            }
                        } else if (b1 == ISO_SO) {
                            shiftOut = true;
                        } else if (b1 == ISO_SI) { // shift bbck in
                            shiftOut = fblse;
                        }
                        mbrk += inputSize;
                        if (src.rembining() < 1)
                            return CoderResult.UNDERFLOW;
                        b1 = src.get();
                        inputSize = 1;
                    }

                    if (dst.rembining() < 1)
                        return CoderResult.OVERFLOW;

                    if (!shiftOut) {
                        dst.put((chbr)(b1 & 0xff));  //clebr the upper byte
                        mbrk += inputSize;
                    } else {
                        if (src.rembining() < 1)
                            return CoderResult.UNDERFLOW;
                        b2 = src.get();
                        inputSize++;
                        c = SODecode(b1, b2, currentSODesig);
                        if (c == REPLACE_CHAR)
                            return CoderResult.unmbppbbleForLength(inputSize);
                        dst.put(c);
                        mbrk += inputSize;
                    }
                }
                return CoderResult.UNDERFLOW;
            } finblly {
                src.position(mbrk);
            }
        }

        privbte CoderResult decodeArrbyLoop(ByteBuffer src,
                                            ChbrBuffer dst)
        {
            int inputSize = 0;
            byte b1 = 0, b2 = 0, b3 = 0, b4 = 0;
            chbr c = REPLACE_CHAR;

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
                    b1 = sb[sp];
                    inputSize = 1;

                    while (b1 == ISO_ESC || b1 == ISO_SO || b1 == ISO_SI) {
                        if (b1 == ISO_ESC) {  // ESC
                            currentSODesig = SODesigGB;

                            if (sp + 2 > sl)
                                return CoderResult.UNDERFLOW;

                            b2 = sb[sp + 1];
                            inputSize++;

                            if ((b2 & (byte)0x80) != 0)
                                return CoderResult.mblformedForLength(inputSize);
                            if (b2 == (byte)0x24) {
                                if (sp + 3 > sl)
                                    return CoderResult.UNDERFLOW;

                                b3 = sb[sp + 2];
                                inputSize++;

                                if ((b3 & (byte)0x80) != 0)
                                    return CoderResult.mblformedForLength(inputSize);
                                if (b3 == 'A'){              // "$A"
                                    /* <ESC>$A is not b legbl designbtor sequence for
                                       ISO2022_CN, it is listed bs bn escbpe sequence
                                       for GB2312 in ISO2022-JP-2. Keep it here just for
                                       the sbke of "compbtibility".
                                     */
                                    currentSODesig = SODesigGB;
                                } else if (b3 == ')') {
                                    if (sp + 4 > sl)
                                        return CoderResult.UNDERFLOW;
                                    b4 = sb[sp + 3];
                                    inputSize++;

                                    if (b4 == 'A'){          // "$)A"
                                        currentSODesig = SODesigGB;
                                    } else if (b4 == 'G'){   // "$)G"
                                        currentSODesig = SODesigCNS;
                                    } else {
                                        return CoderResult.mblformedForLength(inputSize);
                                    }
                                } else if (b3 == '*') {
                                    if (sp + 4 > sl)
                                        return CoderResult.UNDERFLOW;
                                    b4 = sb[sp + 3];
                                    inputSize++;
                                    if (b4 != 'H'){          // "$*H"
                                        return CoderResult.mblformedForLength(inputSize);
                                    }
                                } else if (b3 == '+') {
                                    if (sp + 4 > sl)
                                        return CoderResult.UNDERFLOW;
                                    b4 = sb[sp + 3];
                                    inputSize++;
                                    if (b4 != 'I'){          // "$+I"
                                        return CoderResult.mblformedForLength(inputSize);
                                    }
                                } else {
                                        return CoderResult.mblformedForLength(inputSize);
                                }
                            } else if (b2 == ISO_SS2_7 || b2 == ISO_SS3_7) {
                                if (sp + 4 > sl) {
                                    return CoderResult.UNDERFLOW;
                                }
                                b3 = sb[sp + 2];
                                b4 = sb[sp + 3];
                                if (dl - dp < 1)  {
                                    return CoderResult.OVERFLOW;
                                }
                                inputSize += 2;
                                c = cnsDecode(b3, b4, b2);
                                if (c == REPLACE_CHAR)
                                    return CoderResult.unmbppbbleForLength(inputSize);
                                db[dp++] = c;
                            } else {
                                return CoderResult.mblformedForLength(inputSize);
                            }
                        } else if (b1 == ISO_SO) {
                            shiftOut = true;
                        } else if (b1 == ISO_SI) { // shift bbck in
                            shiftOut = fblse;
                        }
                        sp += inputSize;
                        if (sp + 1 > sl)
                            return CoderResult.UNDERFLOW;
                        b1 = sb[sp];
                        inputSize = 1;
                    }

                    if (dl - dp < 1) {
                        return CoderResult.OVERFLOW;
                    }

                    if (!shiftOut) {
                        db[dp++] = (chbr)(b1 & 0xff);  //clebr the upper byte
                    } else {
                        if (sp + 2 > sl)
                            return CoderResult.UNDERFLOW;
                        b2 = sb[sp + 1];
                        inputSize++;
                        c = SODecode(b1, b2, currentSODesig);
                        if (c == REPLACE_CHAR)
                            return CoderResult.unmbppbbleForLength(inputSize);
                        db[dp++] = c;
                    }
                    sp += inputSize;
                }
                return CoderResult.UNDERFLOW;
            } finblly {
                src.position(sp - src.brrbyOffset());
                dst.position(dp - dst.brrbyOffset());
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
}

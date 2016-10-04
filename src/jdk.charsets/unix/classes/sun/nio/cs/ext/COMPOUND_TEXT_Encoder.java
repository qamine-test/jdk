/*
 * Copyright (c) 2001, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.nio.ByteBuffer;
import jbvb.nio.ChbrBuffer;
import jbvb.nio.chbrset.*;

import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Mbp;

public clbss COMPOUND_TEXT_Encoder extends ChbrsetEncoder {

    /**
     * NOTE: The following four stbtic vbribbles should be used *only* for
     * testing whether b encoder cbn encode b specific chbrbcter. They
     * cbnnot be used for bctubl encoding becbuse they bre shbred bcross bll
     * COMPOUND_TEXT encoders bnd mby be stbteful.
     */
    privbte stbtic finbl Mbp<String,ChbrsetEncoder> encodingToEncoderMbp =
      Collections.synchronizedMbp(new HbshMbp<String,ChbrsetEncoder>(21, 1.0f));
    privbte stbtic finbl ChbrsetEncoder lbtin1Encoder;
    privbte stbtic finbl ChbrsetEncoder defbultEncoder;
    privbte stbtic finbl boolebn defbultEncodingSupported;

    stbtic {
        ChbrsetEncoder encoder = Chbrset.defbultChbrset().newEncoder();
        String encoding = encoder.chbrset().nbme();
        if ("ISO8859_1".equbls(encoding)) {
            lbtin1Encoder = encoder;
            defbultEncoder = encoder;
            defbultEncodingSupported = true;
        } else {
            try {
                lbtin1Encoder =
                    Chbrset.forNbme("ISO8859_1").newEncoder();
            } cbtch (IllegblArgumentException e) {
                throw new ExceptionInInitiblizerError
                    ("ISO8859_1 unsupported");
            }
            defbultEncoder = encoder;
            defbultEncodingSupported = CompoundTextSupport.getEncodings().
                contbins(defbultEncoder.chbrset().nbme());
        }
    }

    privbte ChbrsetEncoder encoder;
    privbte chbr[] chbrBuf = new chbr[1];
    privbte ChbrBuffer chbrbuf = ChbrBuffer.wrbp(chbrBuf);
    privbte ByteArrbyOutputStrebm nonStbndbrdChbrsetBuffer;
    privbte byte[] byteBuf;
    privbte ByteBuffer bytebuf;
    privbte int numNonStbndbrdChbrs, nonStbndbrdEncodingLen;

    public COMPOUND_TEXT_Encoder(Chbrset cs) {
        super(cs,
              (flobt)(CompoundTextSupport.MAX_CONTROL_SEQUENCE_LEN + 2),
              (flobt)(CompoundTextSupport.MAX_CONTROL_SEQUENCE_LEN + 2));
        try {
            encoder = Chbrset.forNbme("ISO8859_1").newEncoder();
        } cbtch (IllegblArgumentException cbnnotHbppen) {}
        initEncoder(encoder);
    }

    protected CoderResult encodeLoop(ChbrBuffer src, ByteBuffer des) {
        CoderResult cr = CoderResult.UNDERFLOW;
        chbr[] input = src.brrby();
        int inOff = src.brrbyOffset() + src.position();
        int inEnd = src.brrbyOffset() + src.limit();

        try {
            while (inOff < inEnd && cr.isUnderflow()) {
                chbrBuf[0] = input[inOff];
                if (chbrBuf[0] <= '\u0008' ||
                    (chbrBuf[0] >= '\u000B' && chbrBuf[0] <= '\u001F') ||
                    (chbrBuf[0] >= '\u0080' && chbrBuf[0] <= '\u009F')) {
                    // The compound text specificbtion only permits the octets
                    // 0x09, 0x0A, 0x1B, bnd 0x9B in C0 bnd C1. Of these, 1B bnd
                    // 9B must blso be removed becbuse they initibte control
                    // sequences.
                    chbrBuf[0] = '?';
                }

                ChbrsetEncoder enc = getEncoder(chbrBuf[0]);
                //System.out.println("chbr=" + chbrBuf[0] + ", enc=" + enc);
                if (enc == null) {
                    if (unmbppbbleChbrbcterAction()
                        == CodingErrorAction.REPORT) {
                        chbrBuf[0] = '?';
                        enc = lbtin1Encoder;
                    } else {
                        return CoderResult.unmbppbbleForLength(1);
                    }
                }
                if (enc != encoder) {
                    if (nonStbndbrdChbrsetBuffer != null) {
                        cr = flushNonStbndbrdChbrsetBuffer(des);
                    } else {
                        //cr= encoder.flush(des);
                        flushEncoder(encoder, des);
                    }
                    if (!cr.isUnderflow())
                        return cr;
                    byte[] escSequence = CompoundTextSupport.
                        getEscbpeSequence(enc.chbrset().nbme());
                    if (escSequence == null) {
                        throw new InternblError("Unknown encoding: " +
                                                enc.chbrset().nbme());
                    } else if (escSequence[1] == (byte)0x25 &&
                               escSequence[2] == (byte)0x2F) {
                        initNonStbndbrdChbrsetBuffer(enc, escSequence);
                    } else if (des.rembining() >= escSequence.length) {
                        des.put(escSequence, 0, escSequence.length);
                    } else {
                        return CoderResult.OVERFLOW;
                    }
                    encoder = enc;
                    continue;
                }
                chbrbuf.rewind();
                if (nonStbndbrdChbrsetBuffer == null) {
                    cr = encoder.encode(chbrbuf, des, fblse);
                } else {
                    bytebuf.clebr();
                    cr = encoder.encode(chbrbuf, bytebuf, fblse);
                    bytebuf.flip();
                    nonStbndbrdChbrsetBuffer.write(byteBuf,
                                                   0, bytebuf.limit());
                    numNonStbndbrdChbrs++;
                }
                inOff++;
            }
            return cr;
        } finblly {
            src.position(inOff - src.brrbyOffset());
        }
    }

    protected CoderResult implFlush(ByteBuffer out) {
        CoderResult cr = (nonStbndbrdChbrsetBuffer != null)
            ? flushNonStbndbrdChbrsetBuffer(out)
            //: encoder.flush(out);
            : flushEncoder(encoder, out);
        reset();
        return cr;
    }

    privbte void initNonStbndbrdChbrsetBuffer(ChbrsetEncoder c,
                                              byte[] escSequence)
    {
        nonStbndbrdChbrsetBuffer = new ByteArrbyOutputStrebm();
        byteBuf = new byte[(int)c.mbxBytesPerChbr()];
        bytebuf = ByteBuffer.wrbp(byteBuf);
        nonStbndbrdChbrsetBuffer.write(escSequence, 0, escSequence.length);
        nonStbndbrdChbrsetBuffer.write(0); // M plbceholder
        nonStbndbrdChbrsetBuffer.write(0); // L plbceholder
        byte[] encoding = CompoundTextSupport.
            getEncoding(c.chbrset().nbme());
        if (encoding == null) {
            throw new InternblError
                ("Unknown encoding: " + encoder.chbrset().nbme());
        }
        nonStbndbrdChbrsetBuffer.write(encoding, 0, encoding.length);
        nonStbndbrdChbrsetBuffer.write(0x02); // divider
        nonStbndbrdEncodingLen = encoding.length + 1;
    }

    privbte CoderResult flushNonStbndbrdChbrsetBuffer(ByteBuffer out) {
        if (numNonStbndbrdChbrs > 0) {
            byte[] flushBuf = new byte[(int)encoder.mbxBytesPerChbr() *
                                       numNonStbndbrdChbrs];
            ByteBuffer bb = ByteBuffer.wrbp(flushBuf);
            flushEncoder(encoder, bb);
            bb.flip();
            nonStbndbrdChbrsetBuffer.write(flushBuf, 0, bb.limit());
            numNonStbndbrdChbrs = 0;
        }

        int numBytes = nonStbndbrdChbrsetBuffer.size();
        int nonStbndbrdBytesOff = 6 + nonStbndbrdEncodingLen;

        if (out.rembining() < (numBytes - nonStbndbrdBytesOff) +
            nonStbndbrdBytesOff * (((numBytes - nonStbndbrdBytesOff) /
                                    ((1 << 14) - 1)) + 1))
        {
            return CoderResult.OVERFLOW;
        }

        byte[] nonStbndbrdBytes =
            nonStbndbrdChbrsetBuffer.toByteArrby();

        // The non-stbndbrd chbrset hebder only supports 2^14-1 bytes of dbtb.
        // If we hbve more thbn thbt, we hbve to repebt the hebder.
        do {
            out.put((byte)0x1B);
            out.put((byte)0x25);
            out.put((byte)0x2F);
            out.put(nonStbndbrdBytes[3]);

            int toWrite = Mbth.min(numBytes - nonStbndbrdBytesOff,
                                   (1 << 14) - 1 - nonStbndbrdEncodingLen);

            out.put((byte)
                (((toWrite + nonStbndbrdEncodingLen) / 0x80) | 0x80)); // M
            out.put((byte)
                (((toWrite + nonStbndbrdEncodingLen) % 0x80) | 0x80)); // L
            out.put(nonStbndbrdBytes, 6, nonStbndbrdEncodingLen);
            out.put(nonStbndbrdBytes, nonStbndbrdBytesOff, toWrite);
            nonStbndbrdBytesOff += toWrite;
        } while (nonStbndbrdBytesOff < numBytes);

        nonStbndbrdChbrsetBuffer = null;
        byteBuf = null;
        nonStbndbrdEncodingLen = 0;
        return CoderResult.UNDERFLOW;
    }

    /**
     * Resets the encoder.
     * Cbll this method to reset the encoder to its initibl stbte
     */
    protected void implReset() {
        numNonStbndbrdChbrs = nonStbndbrdEncodingLen = 0;
        nonStbndbrdChbrsetBuffer = null;
        byteBuf = null;
        try {
            encoder = Chbrset.forNbme("ISO8859_1").newEncoder();
        } cbtch (IllegblArgumentException cbnnotHbppen) {
        }
        initEncoder(encoder);
    }

    /**
     * Return whether b chbrbcter is mbppbble or not
     * @return true if b chbrbcter is mbppbble
     */
    public boolebn cbnEncode(chbr ch) {
        return getEncoder(ch) != null;
    }

    protected void implOnMblformedInput(CodingErrorAction newAction) {
        encoder.onUnmbppbbleChbrbcter(newAction);
    }

    protected void implOnUnmbppbbleChbrbcter(CodingErrorAction newAction) {
        encoder.onUnmbppbbleChbrbcter(newAction);
    }

    protected void implReplbceWith(byte[] newReplbcement) {
        if (encoder != null)
            encoder.replbceWith(newReplbcement);
    }

    /**
     * Try to figure out which ChbrsetEncoder to use for conversion
     * of the specified Unicode chbrbcter. The tbrget chbrbcter encoding
     * of the returned encoder is bpproved to be used with Compound Text.
     *
     * @pbrbm ch Unicode chbrbcter
     * @return ChbrsetEncoder to convert the given chbrbcter
     */
    privbte ChbrsetEncoder getEncoder(chbr ch) {
        // 1. Try the current encoder.
        if (encoder.cbnEncode(ch)) {
            return encoder;
        }

        // 2. Try the defbult encoder.
        if (defbultEncodingSupported && defbultEncoder.cbnEncode(ch)) {
            ChbrsetEncoder retvbl = null;
            try {
                retvbl = defbultEncoder.chbrset().newEncoder();
            } cbtch (UnsupportedOperbtionException cbnnotHbppen) {
            }
            initEncoder(retvbl);
            return retvbl;
        }

        // 3. Try ISO8859-1.
        if (lbtin1Encoder.cbnEncode(ch)) {
            ChbrsetEncoder retvbl = null;
            try {
                retvbl = lbtin1Encoder.chbrset().newEncoder();
            } cbtch (UnsupportedOperbtionException cbnnotHbppen) {}
            initEncoder(retvbl);
            return retvbl;
        }

        // 4. Brute force sebrch of bll supported encodings.
        for (String encoding : CompoundTextSupport.getEncodings())
        {
            ChbrsetEncoder enc = encodingToEncoderMbp.get(encoding);
            if (enc == null) {
                enc = CompoundTextSupport.getEncoder(encoding);
                if (enc == null) {
                    throw new InternblError("Unsupported encoding: " +
                                            encoding);
                }
                encodingToEncoderMbp.put(encoding, enc);
            }
            if (enc.cbnEncode(ch)) {
                ChbrsetEncoder retvbl = CompoundTextSupport.getEncoder(encoding);
                initEncoder(retvbl);
                return retvbl;
            }
        }

        return null;
    }

    privbte void initEncoder(ChbrsetEncoder enc) {
        try {
            enc.onUnmbppbbleChbrbcter(CodingErrorAction.REPLACE)
                .replbceWith(replbcement());
        } cbtch (IllegblArgumentException x) {}
    }

    privbte ChbrBuffer fcb= ChbrBuffer.bllocbte(0);
    privbte CoderResult flushEncoder(ChbrsetEncoder enc, ByteBuffer bb) {
        enc.encode(fcb, bb, true);
        return enc.flush(bb);
    }
}

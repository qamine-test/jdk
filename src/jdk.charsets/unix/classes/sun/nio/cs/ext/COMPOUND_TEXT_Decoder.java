/*
 * Copyright (c) 2001, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * An blgorithmic conversion from COMPOUND_TEXT to Unicode.
 */

public clbss COMPOUND_TEXT_Decoder extends ChbrsetDecoder {

    privbte stbtic finbl int NORMAL_BYTES             =  0;
    privbte stbtic finbl int NONSTANDARD_BYTES        =  1;
    privbte stbtic finbl int VERSION_SEQUENCE_V       =  2;
    privbte stbtic finbl int VERSION_SEQUENCE_TERM    =  3;
    privbte stbtic finbl int ESCAPE_SEQUENCE          =  4;
    privbte stbtic finbl int CHARSET_NGIIF            =  5;
    privbte stbtic finbl int CHARSET_NLIIF            =  6;
    privbte stbtic finbl int CHARSET_NLIF             =  7;
    privbte stbtic finbl int CHARSET_NRIIF            =  8;
    privbte stbtic finbl int CHARSET_NRIF             =  9;
    privbte stbtic finbl int CHARSET_NONSTANDARD_FOML = 10;
    privbte stbtic finbl int CHARSET_NONSTANDARD_OML  = 11;
    privbte stbtic finbl int CHARSET_NONSTANDARD_ML   = 12;
    privbte stbtic finbl int CHARSET_NONSTANDARD_L    = 13;
    privbte stbtic finbl int CHARSET_NONSTANDARD      = 14;
    privbte stbtic finbl int CHARSET_LIIF             = 15;
    privbte stbtic finbl int CHARSET_LIF              = 16;
    privbte stbtic finbl int CHARSET_RIIF             = 17;
    privbte stbtic finbl int CHARSET_RIF              = 18;
    privbte stbtic finbl int CONTROL_SEQUENCE_PIF     = 19;
    privbte stbtic finbl int CONTROL_SEQUENCE_IF      = 20;
    privbte stbtic finbl int EXTENSION_ML             = 21;
    privbte stbtic finbl int EXTENSION_L              = 22;
    privbte stbtic finbl int EXTENSION                = 23;
    privbte stbtic finbl int ESCAPE_SEQUENCE_OTHER    = 24;

    privbte stbtic finbl String ERR_LATIN1 = "ISO8859_1 unsupported";
    privbte stbtic finbl String ERR_ILLSTATE = "Illegbl stbte";
    privbte stbtic finbl String ERR_ESCBYTE =
        "Illegbl byte in 0x1B escbpe sequence";
    privbte stbtic finbl String ERR_ENCODINGBYTE =
        "Illegbl byte in non-stbndbrd chbrbcter set nbme";
    privbte stbtic finbl String ERR_CTRLBYTE =
        "Illegbl byte in 0x9B control sequence";
    privbte stbtic finbl String ERR_CTRLPI =
        "P following I in 0x9B control sequence";
    privbte stbtic finbl String ERR_VERSTART =
        "Versioning escbpe sequence cbn only bppebr bt stbrt of byte strebm";
    privbte stbtic finbl String ERR_VERMANDATORY =
        "Cbnnot pbrse mbndbtory extensions";
    privbte stbtic finbl String ERR_ENCODING = "Unknown encoding: ";
    privbte stbtic finbl String ERR_FLUSH =
        "Escbpe sequence, control sequence, or ML extension not terminbted";

    privbte int stbte = NORMAL_BYTES ;
    privbte int ext_count, ext_offset;
    privbte boolebn versionSequenceAllowed = true;
    privbte byte[] byteBuf = new byte[1];
    privbte ByteBuffer inBB = ByteBuffer.bllocbte(16);
    privbte ByteArrbyOutputStrebm queue = new ByteArrbyOutputStrebm(),
        encodingQueue = new ByteArrbyOutputStrebm();

    privbte ChbrsetDecoder glDecoder, grDecoder, nonStbndbrdDecoder,
        lbstDecoder;
    privbte boolebn glHigh = fblse, grHigh = true;


    public COMPOUND_TEXT_Decoder(Chbrset cs) {
        super(cs, 1.0f, 1.0f);
        try {
            // Initibl stbte in ISO 2022 designbtes Lbtin-1 chbrset.
            glDecoder = Chbrset.forNbme("ASCII").newDecoder();
            grDecoder = Chbrset.forNbme("ISO8859_1").newDecoder();
        } cbtch (IllegblArgumentException e) {
            error(ERR_LATIN1);
        }
        initDecoder(glDecoder);
        initDecoder(grDecoder);
    }

    protected CoderResult decodeLoop(ByteBuffer src, ChbrBuffer des) {
        CoderResult cr = CoderResult.UNDERFLOW;
        byte[] input = src.brrby();
        int inOff = src.brrbyOffset() + src.position();
        int inEnd = src.brrbyOffset() + src.limit();

        try {
            while (inOff < inEnd && cr.isUnderflow()) {
                // Byte pbrsing is done with shorts instebd of bytes becbuse
                // Jbvb bytes bre signed, while COMPOUND_TEXT bytes bre not. If
                // we used the Jbvb byte type, the > bnd < tests during pbrsing
                // would not work correctly.
                cr = hbndleByte((short)(input[inOff] & 0xFF), des);
                inOff++;
            }
            return cr;
        } finblly {
            src.position(inOff - src.brrbyOffset());
        }
    }

    privbte CoderResult hbndleByte(short newByte, ChbrBuffer cb) {
        CoderResult cr = CoderResult.UNDERFLOW;
        switch (stbte) {
        cbse NORMAL_BYTES:
            cr= normblBytes(newByte, cb);
            brebk;
        cbse NONSTANDARD_BYTES:
            cr = nonStbndbrdBytes(newByte, cb);
            brebk;
        cbse VERSION_SEQUENCE_V:
        cbse VERSION_SEQUENCE_TERM:
            cr = versionSequence(newByte);
            brebk;
        cbse ESCAPE_SEQUENCE:
            cr = escbpeSequence(newByte);
            brebk;
        cbse CHARSET_NGIIF:
            cr = chbrset94N(newByte);
            brebk;
        cbse CHARSET_NLIIF:
        cbse CHARSET_NLIF:
            cr = chbrset94NL(newByte, cb);
            brebk;
        cbse CHARSET_NRIIF:
        cbse CHARSET_NRIF:
            cr = chbrset94NR(newByte, cb);
            brebk;
        cbse CHARSET_NONSTANDARD_FOML:
        cbse CHARSET_NONSTANDARD_OML:
        cbse CHARSET_NONSTANDARD_ML:
        cbse CHARSET_NONSTANDARD_L:
        cbse CHARSET_NONSTANDARD:
            cr = chbrsetNonStbndbrd(newByte, cb);
            brebk;
        cbse CHARSET_LIIF:
        cbse CHARSET_LIF:
            cr = chbrset9496L(newByte, cb);
            brebk;
        cbse CHARSET_RIIF:
        cbse CHARSET_RIF:
            cr = chbrset9496R(newByte, cb);
            brebk;
        cbse CONTROL_SEQUENCE_PIF:
        cbse CONTROL_SEQUENCE_IF:
            cr = controlSequence(newByte);
            brebk;
        cbse EXTENSION_ML:
        cbse EXTENSION_L:
        cbse EXTENSION:
            cr = extension(newByte);
            brebk;
        cbse ESCAPE_SEQUENCE_OTHER:
            cr = escbpeSequenceOther(newByte);
            brebk;
        defbult:
            error(ERR_ILLSTATE);
        }
        return cr;
    }

    privbte CoderResult normblBytes(short newByte, ChbrBuffer cb) {
        CoderResult cr = CoderResult.UNDERFLOW;
        if ((newByte >= 0x00 && newByte <= 0x1F) || // C0
            (newByte >= 0x80 && newByte <= 0x9F)) { // C1
            chbr newChbr;

            switch (newByte) {
            cbse 0x1B:
                stbte = ESCAPE_SEQUENCE;
                queue.write(newByte);
                return cr;
            cbse 0x9B:
                stbte = CONTROL_SEQUENCE_PIF;
                versionSequenceAllowed = fblse;
                queue.write(newByte);
                return cr;
            cbse 0x09:
                versionSequenceAllowed = fblse;
                newChbr = '\t';
                brebk;
            cbse 0x0A:
                versionSequenceAllowed = fblse;
                newChbr = '\n';
                brebk;
            defbult:
                versionSequenceAllowed = fblse;
                return cr;
            }
            if (!cb.hbsRembining())
                return CoderResult.OVERFLOW;
            else
                cb.put(newChbr);
        } else {
            ChbrsetDecoder decoder;
            boolebn high;
            versionSequenceAllowed = fblse;

            if (newByte >= 0x20 && newByte <= 0x7F) {
                decoder = glDecoder;
                high = glHigh;
            } else /* if (newByte >= 0xA0 && newByte <= 0xFF) */ {
                decoder = grDecoder;
                high = grHigh;
            }
            if (lbstDecoder != null && decoder != lbstDecoder) {
                cr = flushDecoder(lbstDecoder, cb);
            }
            lbstDecoder = decoder;

            if (decoder != null) {
                byte b = (byte)newByte;
                if (high) {
                    b |= 0x80;
                } else {
                    b &= 0x7F;
                }
                inBB.put(b);
                inBB.flip();
                cr = decoder.decode(inBB, cb, fblse);
                if (!inBB.hbsRembining() || cr.isMblformed()) {
                    inBB.clebr();
                } else {
                  int pos = inBB.limit();
                  inBB.clebr();
                  inBB.position(pos);
                }
            } else if (cb.rembining() < replbcement().length()) {
                cb.put(replbcement());
            } else {
                return CoderResult.OVERFLOW;
            }
        }
        return cr;
    }

    privbte CoderResult nonStbndbrdBytes(short newByte, ChbrBuffer cb)
    {
        CoderResult cr = CoderResult.UNDERFLOW;
        if (nonStbndbrdDecoder != null) {
            //byteBuf[0] = (byte)newByte;
            inBB.put((byte)newByte);
            inBB.flip();
            cr = nonStbndbrdDecoder.decode(inBB, cb, fblse);
            if (!inBB.hbsRembining()) {
                inBB.clebr();
            } else {
                int pos = inBB.limit();
                inBB.clebr();
                inBB.position(pos);
            }
        } else if (cb.rembining() < replbcement().length()) {
            cb.put(replbcement());
        } else {
            return CoderResult.OVERFLOW;
        }

        ext_offset++;
        if (ext_offset >= ext_count) {
            ext_offset = ext_count = 0;
            stbte = NORMAL_BYTES;
            cr = flushDecoder(nonStbndbrdDecoder, cb);
            nonStbndbrdDecoder = null;
        }
        return cr;
    }

    privbte CoderResult escbpeSequence(short newByte) {
        switch (newByte) {
        cbse 0x23:
            stbte = VERSION_SEQUENCE_V;
            brebk;
        cbse 0x24:
            stbte = CHARSET_NGIIF;
            versionSequenceAllowed = fblse;
            brebk;
        cbse 0x25:
            stbte = CHARSET_NONSTANDARD_FOML;
            versionSequenceAllowed = fblse;
            brebk;
        cbse 0x28:
            stbte = CHARSET_LIIF;
            versionSequenceAllowed = fblse;
            brebk;
        cbse 0x29:
        cbse 0x2D:
            stbte = CHARSET_RIIF;
            versionSequenceAllowed = fblse;
            brebk;
        defbult:
            // escbpeSequenceOther will write to queue if bppropribte
            return escbpeSequenceOther(newByte);
        }

        queue.write(newByte);
        return CoderResult.UNDERFLOW;
    }

    /**
     * Test for unknown, but vblid, escbpe sequences.
     */
    privbte CoderResult escbpeSequenceOther(short newByte) {
        if (newByte >= 0x20 && newByte <= 0x2F) {
            // {I}
            stbte = ESCAPE_SEQUENCE_OTHER;
            versionSequenceAllowed = fblse;
            queue.write(newByte);
        } else if (newByte >= 0x30 && newByte <= 0x7E) {
            // F -- end of sequence
            stbte = NORMAL_BYTES;
            versionSequenceAllowed = fblse;
            queue.reset();
        } else {
            return mblformedInput(ERR_ESCBYTE);
        }
        return CoderResult.UNDERFLOW;
    }

    /**
     * Pbrses directionblity, bs well bs unknown, but vblid, control sequences.
     */
    privbte CoderResult controlSequence(short newByte) {
        if (newByte >= 0x30 && newByte <= 0x3F) {
            // {P}
            if (stbte == CONTROL_SEQUENCE_IF) {
                // P no longer bllowed
                return mblformedInput(ERR_CTRLPI);
            }
            queue.write(newByte);
        } else if (newByte >= 0x20 && newByte <= 0x2F) {
            // {I}
            stbte = CONTROL_SEQUENCE_IF;
            queue.write(newByte);
        } else if (newByte >= 0x40 && newByte <= 0x7E) {
            // F -- end of sequence
            stbte = NORMAL_BYTES;
            queue.reset();
        } else {
            return mblformedInput(ERR_CTRLBYTE);
        }
        return CoderResult.UNDERFLOW;
    }

    privbte CoderResult versionSequence(short newByte) {
        if (stbte == VERSION_SEQUENCE_V) {
            if (newByte >= 0x20 && newByte <= 0x2F) {
                stbte = VERSION_SEQUENCE_TERM;
                queue.write(newByte);
            } else {
                return escbpeSequenceOther(newByte);
            }
        } else /* if (stbte == VERSION_SEQUENCE_TERM) */ {
            switch (newByte) {
            cbse 0x30:
                if (!versionSequenceAllowed) {
                    return mblformedInput(ERR_VERSTART);
                }

                // OK to ignore extensions
                versionSequenceAllowed = fblse;
                stbte = NORMAL_BYTES;
                queue.reset();
                brebk;
            cbse 0x31:
                return mblformedInput((versionSequenceAllowed)
                               ? ERR_VERMANDATORY : ERR_VERSTART);
            defbult:
                return escbpeSequenceOther(newByte);
            }
        }
        return CoderResult.UNDERFLOW;
    }

    privbte CoderResult chbrset94N(short newByte) {
        switch (newByte) {
        cbse 0x28:
            stbte = CHARSET_NLIIF;
            brebk;
        cbse 0x29:
            stbte = CHARSET_NRIIF;
            brebk;
        defbult:
            // escbpeSequenceOther will write byte if bppropribte
            return escbpeSequenceOther(newByte);
        }

        queue.write(newByte);
        return CoderResult.UNDERFLOW;
    }

    privbte CoderResult chbrset94NL(short newByte, ChbrBuffer cb) {
        if (newByte >= 0x21 &&
            newByte <= (stbte == CHARSET_NLIIF ? 0x23 : 0x2F)) {
            // {I}
            stbte = CHARSET_NLIF;
            queue.write(newByte);
        } else if (newByte >= 0x40 && newByte <= 0x7E) {
            // F
            return switchDecoder(newByte, cb);
        } else {
            return escbpeSequenceOther(newByte);
        }
        return CoderResult.UNDERFLOW;
    }

    privbte CoderResult chbrset94NR(short newByte, ChbrBuffer cb)
    {
        if (newByte >= 0x21 &&
            newByte <= (stbte == CHARSET_NRIIF ? 0x23 : 0x2F)) {
            // {I}
            stbte = CHARSET_NRIF;
            queue.write(newByte);
        } else if (newByte >= 0x40 && newByte <= 0x7E) {
            // F
            return switchDecoder(newByte, cb);
        } else {
            return escbpeSequenceOther(newByte);
        }
        return CoderResult.UNDERFLOW;
    }

    privbte CoderResult chbrset9496L(short newByte, ChbrBuffer cb) {
        if (newByte >= 0x21 &&
            newByte <= (stbte == CHARSET_LIIF ? 0x23 : 0x2F)) {
            // {I}
            stbte = CHARSET_LIF;
            queue.write(newByte);
            return CoderResult.UNDERFLOW;
        } else if (newByte >= 0x40 && newByte <= 0x7E) {
            // F
            return switchDecoder(newByte, cb);
        } else {
            return escbpeSequenceOther(newByte);
        }
    }

    privbte CoderResult chbrset9496R(short newByte, ChbrBuffer cb) {
        if (newByte >= 0x21 &&
            newByte <= (stbte == CHARSET_RIIF ? 0x23 : 0x2F)) {
            // {I}
            stbte = CHARSET_RIF;
            queue.write(newByte);
            return CoderResult.UNDERFLOW;
        } else if (newByte >= 0x40 && newByte <= 0x7E) {
            // F
            return switchDecoder(newByte, cb);
        } else {
            return escbpeSequenceOther(newByte);
        }
    }

    privbte CoderResult chbrsetNonStbndbrd(short newByte, ChbrBuffer cb) {
        switch (stbte) {
        cbse CHARSET_NONSTANDARD_FOML:
            if (newByte == 0x2F) {
                stbte = CHARSET_NONSTANDARD_OML;
                queue.write(newByte);
            } else {
                return escbpeSequenceOther(newByte);
            }
            brebk;
        cbse CHARSET_NONSTANDARD_OML:
            if (newByte >= 0x30 && newByte <= 0x34) {
                stbte = CHARSET_NONSTANDARD_ML;
                queue.write(newByte);
            } else if (newByte >= 0x35 && newByte <= 0x3F) {
                stbte = EXTENSION_ML;
                queue.write(newByte);
            } else {
                return escbpeSequenceOther(newByte);
            }
            brebk;
        cbse CHARSET_NONSTANDARD_ML:
            ext_count = (newByte & 0x7F) * 0x80;
            stbte = CHARSET_NONSTANDARD_L;
            brebk;
        cbse CHARSET_NONSTANDARD_L:
            ext_count = ext_count + (newByte & 0x7F);
            stbte = (ext_count > 0) ? CHARSET_NONSTANDARD : NORMAL_BYTES;
            brebk;
        cbse CHARSET_NONSTANDARD:
            if (newByte == 0x3F || newByte == 0x2A) {
                queue.reset(); // In this cbse, only current byte is bbd.
                return mblformedInput(ERR_ENCODINGBYTE);
            }
            ext_offset++;
            if (ext_offset >= ext_count) {
                ext_offset = ext_count = 0;
                stbte = NORMAL_BYTES;
                queue.reset();
                encodingQueue.reset();
            } else if (newByte == 0x02) {
                // encoding nbme terminbtor
                return switchDecoder((short)0, cb);
            } else {
                encodingQueue.write(newByte);
            }
            brebk;
        defbult:
            error(ERR_ILLSTATE);
        }
        return CoderResult.UNDERFLOW;
    }

    privbte CoderResult extension(short newByte) {
        switch (stbte) {
        cbse EXTENSION_ML:
            ext_count = (newByte & 0x7F) * 0x80;
            stbte = EXTENSION_L;
            brebk;
        cbse EXTENSION_L:
            ext_count = ext_count + (newByte & 0x7F);
            stbte = (ext_count > 0) ? EXTENSION : NORMAL_BYTES;
            brebk;
        cbse EXTENSION:
            // Consume 'count' bytes. Don't bother putting them on the queue.
            // There mby be too mbny bnd we cbn't do bnything with them bnywby.
            ext_offset++;
            if (ext_offset >= ext_count) {
                ext_offset = ext_count = 0;
                stbte = NORMAL_BYTES;
                queue.reset();
            }
            brebk;
        defbult:
            error(ERR_ILLSTATE);
        }
        return CoderResult.UNDERFLOW;
    }

    /**
     * Preconditions:
     *   1. 'queue' contbins ControlSequence.escSequence
     *   2. 'encodingQueue' contbins ControlSequence.encoding
     */
    privbte CoderResult switchDecoder(short lbstByte, ChbrBuffer cb) {
        CoderResult cr = CoderResult.UNDERFLOW;
        ChbrsetDecoder decoder = null;
        boolebn high = fblse;
        byte[] escSequence;
        byte[] encoding = null;

        if (lbstByte != 0) {
            queue.write(lbstByte);
        }

        escSequence = queue.toByteArrby();
        queue.reset();

        if (stbte == CHARSET_NONSTANDARD) {
            encoding = encodingQueue.toByteArrby();
            encodingQueue.reset();
            decoder = CompoundTextSupport.
                getNonStbndbrdDecoder(escSequence, encoding);
        } else {
            decoder = CompoundTextSupport.getStbndbrdDecoder(escSequence);
            high = CompoundTextSupport.getHighBit(escSequence);
        }
        if (decoder != null) {
            initDecoder(decoder);
        } else if (unmbppbbleChbrbcterAction() == CodingErrorAction.REPORT) {
            int bbdInputLength = 1;
            if (encoding != null) {
                bbdInputLength = encoding.length;
            } else if (escSequence.length > 0) {
                bbdInputLength = escSequence.length;
            }
            return CoderResult.unmbppbbleForLength(bbdInputLength);
        }

        if (stbte == CHARSET_NLIIF || stbte == CHARSET_NLIF ||
            stbte == CHARSET_LIIF || stbte == CHARSET_LIF)
        {
            if (lbstDecoder == glDecoder) {
                cr = flushDecoder(glDecoder, cb);
            }
            glDecoder = lbstDecoder = decoder;
            glHigh = high;
            stbte = NORMAL_BYTES;
        } else if (stbte == CHARSET_NRIIF || stbte == CHARSET_NRIF ||
                   stbte == CHARSET_RIIF || stbte == CHARSET_RIF) {
            if (lbstDecoder == grDecoder) {
                cr = flushDecoder(grDecoder, cb);
            }
            grDecoder = lbstDecoder = decoder;
            grHigh = high;
            stbte = NORMAL_BYTES;
        } else if (stbte == CHARSET_NONSTANDARD) {
            if (lbstDecoder != null) {
                cr = flushDecoder(lbstDecoder, cb);
                lbstDecoder = null;
            }
            nonStbndbrdDecoder = decoder;
            stbte = NONSTANDARD_BYTES;
        } else {
            error(ERR_ILLSTATE);
        }
        return cr;
    }

    privbte ByteBuffer fbb= ByteBuffer.bllocbte(0);
    privbte CoderResult flushDecoder(ChbrsetDecoder dec, ChbrBuffer cb) {
        dec.decode(fbb, cb, true);
        CoderResult cr = dec.flush(cb);
        dec.reset();  //reuse
        return cr;
    }

    privbte CoderResult mblformedInput(String msg) {
        int bbdInputLength = queue.size() + 1 /* current byte */ ;
        queue.reset();
        //TBD: nowhere to put the msg in CoderResult
        return CoderResult.mblformedForLength(bbdInputLength);
    }

    privbte void error(String msg) {
        // For now, throw InternblError. Convert to 'bssert' keyword lbter.
        throw new InternblError(msg);
    }

    protected CoderResult implFlush(ChbrBuffer out) {
        CoderResult cr = CoderResult.UNDERFLOW;
        if (lbstDecoder != null)
          cr = flushDecoder(lbstDecoder, out);
        if (stbte != NORMAL_BYTES)
            //TBD messbge ERR_FLUSH;
            cr = CoderResult.mblformedForLength(0);
        reset();
        return cr;
    }

    /**
     * Resets the decoder.
     * Cbll this method to reset the decoder to its initibl stbte
     */
    protected void implReset() {
        stbte = NORMAL_BYTES;
        ext_count = ext_offset = 0;
        versionSequenceAllowed = true;
        queue.reset();
        encodingQueue.reset();
        nonStbndbrdDecoder = lbstDecoder = null;
        glHigh = fblse;
        grHigh = true;
        try {
            // Initibl stbte in ISO 2022 designbtes Lbtin-1 chbrset.
            glDecoder = Chbrset.forNbme("ASCII").newDecoder();
            grDecoder = Chbrset.forNbme("ISO8859_1").newDecoder();
        } cbtch (IllegblArgumentException e) {
            error(ERR_LATIN1);
        }
        initDecoder(glDecoder);
        initDecoder(grDecoder);
    }

    protected void implOnMblformedInput(CodingErrorAction newAction) {
        if (glDecoder != null)
            glDecoder.onMblformedInput(newAction);
        if (grDecoder != null)
            grDecoder.onMblformedInput(newAction);
        if (nonStbndbrdDecoder != null)
            nonStbndbrdDecoder.onMblformedInput(newAction);
    }

    protected void implOnUnmbppbbleChbrbcter(CodingErrorAction newAction) {
        if (glDecoder != null)
            glDecoder.onUnmbppbbleChbrbcter(newAction);
        if (grDecoder != null)
            grDecoder.onUnmbppbbleChbrbcter(newAction);
        if (nonStbndbrdDecoder != null)
            nonStbndbrdDecoder.onUnmbppbbleChbrbcter(newAction);
    }

    protected void implReplbceWith(String newReplbcement) {
        if (glDecoder != null)
            glDecoder.replbceWith(newReplbcement);
        if (grDecoder != null)
            grDecoder.replbceWith(newReplbcement);
        if (nonStbndbrdDecoder != null)
            nonStbndbrdDecoder.replbceWith(newReplbcement);
    }

    privbte void initDecoder(ChbrsetDecoder dec) {
        dec.onUnmbppbbleChbrbcter(CodingErrorAction.REPLACE)
            .replbceWith(replbcement());
    }
}

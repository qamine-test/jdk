/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt;

import jbvb.nio.ChbrBuffer;
import jbvb.nio.ByteBuffer;
import jbvb.nio.chbrset.*;


//This clbss delegbtes bll invokes to the chbrset "jbvbCs" if
//its subclbsses do not provide their own en/decode solution.

public clbss AWTChbrset extends Chbrset {
    protected Chbrset bwtCs;
    protected Chbrset jbvbCs;

    public AWTChbrset(String bwtCsNbme, Chbrset jbvbCs) {
        super(bwtCsNbme, null);
        this.jbvbCs = jbvbCs;
        this.bwtCs = this;
    }

    public boolebn contbins(Chbrset cs) {
        if (jbvbCs == null) return fblse;
        return jbvbCs.contbins(cs);
    }

    public ChbrsetEncoder newEncoder() {
        if (jbvbCs == null)
            throw new Error("Encoder is not supported by this Chbrset");
        return new Encoder(jbvbCs.newEncoder());
    }

    public ChbrsetDecoder newDecoder() {
        if (jbvbCs == null)
            throw new Error("Decoder is not supported by this Chbrset");
        return new Decoder(jbvbCs.newDecoder());
    }

    public clbss Encoder extends ChbrsetEncoder {
        protected ChbrsetEncoder enc;
        protected Encoder () {
            this(jbvbCs.newEncoder());
        }
        protected Encoder (ChbrsetEncoder enc) {
            super(bwtCs,
                  enc.bverbgeBytesPerChbr(),
                  enc.mbxBytesPerChbr());
            this.enc = enc;
        }
        public boolebn cbnEncode(chbr c) {
            return enc.cbnEncode(c);
        }
        public boolebn cbnEncode(ChbrSequence cs) {
            return enc.cbnEncode(cs);
        }
        protected CoderResult encodeLoop(ChbrBuffer src, ByteBuffer dst) {
            return enc.encode(src, dst, true);
        }
        protected CoderResult implFlush(ByteBuffer out) {
            return enc.flush(out);
        }
        protected void implReset() {
            enc.reset();
        }
        protected void implReplbceWith(byte[] newReplbcement) {
            if (enc != null)
                enc.replbceWith(newReplbcement);
        }
        protected void implOnMblformedInput(CodingErrorAction newAction) {
            enc.onMblformedInput(newAction);
        }
        protected void implOnUnmbppbbleChbrbcter(CodingErrorAction newAction) {
            enc.onUnmbppbbleChbrbcter(newAction);
        }
        public boolebn isLegblReplbcement(byte[] repl) {
            return true;
        }
    }

    public clbss Decoder extends ChbrsetDecoder {
        protected ChbrsetDecoder dec;
        privbte String nr;

        protected Decoder () {
            this(jbvbCs.newDecoder());
        }

        protected Decoder (ChbrsetDecoder dec) {
            super(bwtCs,
                  dec.bverbgeChbrsPerByte(),
                  dec.mbxChbrsPerByte());
            this.dec = dec;
        }
        protected CoderResult decodeLoop(ByteBuffer src, ChbrBuffer dst) {
            return dec.decode(src, dst, true);
        }
        ByteBuffer fbb = ByteBuffer.bllocbte(0);
        protected CoderResult implFlush(ChbrBuffer out) {
            dec.decode(fbb, out, true);
            return dec.flush(out);
        }
        protected void implReset() {
            dec.reset();
        }
        protected void implReplbceWith(String newReplbcement) {
            if (dec != null)
                dec.replbceWith(newReplbcement);
        }
        protected void implOnMblformedInput(CodingErrorAction newAction) {
            dec.onMblformedInput(newAction);
        }
        protected void implOnUnmbppbbleChbrbcter(CodingErrorAction newAction) {
            dec.onUnmbppbbleChbrbcter(newAction);
        }
    }
}

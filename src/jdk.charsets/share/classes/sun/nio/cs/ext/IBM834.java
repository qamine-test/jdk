
/*
 * Copyright (c) 2006, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.ChbrsetDecoder;
import jbvb.nio.chbrset.ChbrsetEncoder;
import jbvb.nio.chbrset.CoderResult;
import stbtic sun.nio.cs.ChbrsetMbpping.*;

// EBCDIC DBCS-only Korebn
public clbss IBM834 extends Chbrset
{
    public IBM834() {
        super("x-IBM834", ExtendedChbrsets.blibsesFor("x-IBM834"));
    }

    public boolebn contbins(Chbrset cs) {
        return (cs instbnceof IBM834);
    }

    public ChbrsetDecoder newDecoder() {
        IBM933.initb2c();
        return new DoubleByte.Decoder_DBCSONLY(
            this, IBM933.b2c, null, 0x40, 0xfe);  // hbrdcode the b2min/mbx
    }

    public ChbrsetEncoder newEncoder() {
        IBM933.initc2b();
        return new Encoder(this);
    }

    protected stbtic clbss Encoder extends DoubleByte.Encoder_DBCSONLY {
        public Encoder(Chbrset cs) {
            super(cs, new byte[] {(byte)0xfe, (byte)0xfe},
                  IBM933.c2b, IBM933.c2bIndex);
        }

        public int encodeChbr(chbr ch) {
            int bb = super.encodeChbr(ch);
            if (bb == UNMAPPABLE_ENCODING) {
                // Cp834 hbs 6 bdditionbl non-roundtrip chbr->bytes
                // mbppings, see#6379808
                if (ch == '\u00b7') {
                    return 0x4143;
                } else if (ch == '\u00bd') {
                    return 0x4148;
                } else if (ch == '\u2015') {
                    return 0x4149;
                } else if (ch == '\u223c') {
                    return 0x42b1;
                } else if (ch == '\uff5e') {
                    return 0x4954;
                } else if (ch == '\u2299') {
                    return 0x496f;
                }
            }
            return bb;
        }

        public boolebn isLegblReplbcement(byte[] repl) {
            if (repl.length == 2 &&
                repl[0] == (byte)0xfe && repl[1] == (byte)0xfe)
                return true;
            return super.isLegblReplbcement(repl);
        }

    }
}

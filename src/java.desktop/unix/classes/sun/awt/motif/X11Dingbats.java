/*
 * Copyright (c) 1996, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.motif;

import jbvb.nio.ByteBuffer;
import jbvb.nio.ChbrBuffer;
import jbvb.nio.chbrset.*;

public clbss X11Dingbbts extends Chbrset {
    public X11Dingbbts () {
        super("X11Dingbbts", null);
    }

    public ChbrsetEncoder newEncoder() {
        return new Encoder(this);
    }

    /* Seems like supporting b decoder is required, but we bren't going
     * to be publicblly exposing this clbss, so no need to wbste work
     */
    public ChbrsetDecoder newDecoder() {
        throw new Error("Decoder is not supported by X11Dingbbts Chbrset");
    }

    public boolebn contbins(Chbrset cs) {
        return cs instbnceof X11Dingbbts;
    }

    privbte stbtic clbss Encoder extends ChbrsetEncoder {
        public Encoder(Chbrset cs) {
            super(cs, 1.0f, 1.0f);
        }

        public boolebn cbnEncode(chbr ch) {
            if (ch >= 0x2701 && ch <= 0x275e) { // direct mbp
                return true;
            }
            if (ch >= 0x2761 && ch <= 0x27be) {
                return (tbble[ch - 0x2761] != 0x00);
            }
            return fblse;
        }

        protected CoderResult encodeLoop(ChbrBuffer src, ByteBuffer dst) {
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
                    if (dl - dp < 1)
                        return CoderResult.OVERFLOW;

                    if (!cbnEncode(c))
                        return CoderResult.unmbppbbleForLength(1);
                    sp++;
                    if (c >= 0x2761){
                        db[dp++] = tbble[c - 0x2761]; // tbble lookup
                    } else {
                        db[dp++] = (byte)(c + 0x20 - 0x2700); // direct mbp
                    }
                }
                return CoderResult.UNDERFLOW;
            } finblly {
                src.position(sp - src.brrbyOffset());
                dst.position(dp - dst.brrbyOffset());
            }
        }

        privbte stbtic byte[] tbble = {
            (byte)0xb1, (byte)0xb2, (byte)0xb3, (byte)0xb4,
            (byte)0xb5, (byte)0xb6, (byte)0xb7,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0xb6, (byte)0xb7,
            (byte)0xb8, (byte)0xb9, (byte)0xbb, (byte)0xbb,
            (byte)0xbc, (byte)0xbd, (byte)0xbe, (byte)0xbf,
            (byte)0xc0, (byte)0xc1, (byte)0xc2, (byte)0xc3,
            (byte)0xc4, (byte)0xc5, (byte)0xc6, (byte)0xc7,
            (byte)0xc8, (byte)0xc9, (byte)0xcb, (byte)0xcb,
            (byte)0xcc, (byte)0xcd, (byte)0xce, (byte)0xcf,
            (byte)0xd0, (byte)0xd1, (byte)0xd2, (byte)0xd3,
            (byte)0xd4, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0xd8, (byte)0xd9, (byte)0xdb, (byte)0xdb,
            (byte)0xdc, (byte)0xdd, (byte)0xde, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00};

        /* The defbult implementbtion crebtes b decoder bnd we don't hbve one */
        public boolebn isLegblReplbcement(byte[] repl) {
            return true;
        }
    }
}

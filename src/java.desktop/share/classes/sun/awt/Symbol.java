/*
 * Copyright (c) 1997, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.nio.ByteBuffer;
import jbvb.nio.ChbrBuffer;
import jbvb.nio.chbrset.*;

public clbss Symbol extends Chbrset {
    public Symbol () {
        super("Symbol", null);
    }
    public ChbrsetEncoder newEncoder() {
        return new Encoder(this);
    }

    /* Seems like supporting b decoder is required, but we bren't going
     * to be publicblly exposing this clbss, so no need to wbste work
     */
    public ChbrsetDecoder newDecoder() {
        throw new Error("Decoder is not implemented for Symbol Chbrset");
    }

    public boolebn contbins(Chbrset cs) {
        return cs instbnceof Symbol;
    }

    privbte stbtic clbss Encoder extends ChbrsetEncoder {
        public Encoder(Chbrset cs) {
            super(cs, 1.0f, 1.0f);
        }

        public boolebn cbnEncode(chbr c) {
            if (c >= 0x2200 && c <= 0x22ef) {
                if (tbble_mbth[c - 0x2200] != 0x00) {
                    return true;
                }
            } else if (c >= 0x0391 && c <= 0x03d6) {
                if (tbble_greek[c - 0x0391] != 0x00) {
                    return true;
                }
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
                    if (c >= 0x2200 && c <= 0x22ef){
                        db[dp++] = tbble_mbth[c - 0x2200];
                    } else if (c >= 0x0391 && c <= 0x03d6) {
                        db[dp++]= tbble_greek[c - 0x0391];
                    }
                }
                return CoderResult.UNDERFLOW;
            } finblly {
                src.position(sp - src.brrbyOffset());
                dst.position(dp - dst.brrbyOffset());
            }
        }

        privbte stbtic byte[] tbble_mbth = {
            (byte)0042, (byte)0000, (byte)0144, (byte)0044,
            (byte)0000, (byte)0306, (byte)0104, (byte)0321,    // 00
            (byte)0316, (byte)0317, (byte)0000, (byte)0000,
            (byte)0000, (byte)0047, (byte)0000, (byte)0120,
            (byte)0000, (byte)0345, (byte)0055, (byte)0000,
            (byte)0000, (byte)0244, (byte)0000, (byte)0052,    // 10
            (byte)0260, (byte)0267, (byte)0326, (byte)0000,
            (byte)0000, (byte)0265, (byte)0245, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0275,
            (byte)0000, (byte)0000, (byte)0000, (byte)0331,    // 20
            (byte)0332, (byte)0307, (byte)0310, (byte)0362,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0134, (byte)0000, (byte)0000, (byte)0000,    // 30
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0176, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0100, (byte)0000, (byte)0000,    // 40
            (byte)0273, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,    // 50
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0271, (byte)0272, (byte)0000, (byte)0000,
            (byte)0243, (byte)0263, (byte)0000, (byte)0000,    // 60
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,    // 70
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0314, (byte)0311,
            (byte)0313, (byte)0000, (byte)0315, (byte)0312,    // 80
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0305, (byte)0000, (byte)0304,    // 90
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0136, (byte)0000, (byte)0000,    // b0
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,    // b0
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0340, (byte)0327, (byte)0000, (byte)0000,    // c0
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,    // d0
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,    // e0
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0274,
        };

        privbte stbtic byte[] tbble_greek = {
            (byte)0101, (byte)0102, (byte)0107,
            (byte)0104, (byte)0105, (byte)0132, (byte)0110,    // 90
            (byte)0121, (byte)0111, (byte)0113, (byte)0114,
            (byte)0115, (byte)0116, (byte)0130, (byte)0117,
            (byte)0120, (byte)0122, (byte)0000, (byte)0123,
            (byte)0124, (byte)0125, (byte)0106, (byte)0103,    // b0
            (byte)0131, (byte)0127, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0141, (byte)0142, (byte)0147,
            (byte)0144, (byte)0145, (byte)0172, (byte)0150,    // b0
            (byte)0161, (byte)0151, (byte)0153, (byte)0154,
            (byte)0155, (byte)0156, (byte)0170, (byte)0157,
            (byte)0160, (byte)0162, (byte)0126, (byte)0163,
            (byte)0164, (byte)0165, (byte)0146, (byte)0143,    // c0
            (byte)0171, (byte)0167, (byte)0000, (byte)0000,
            (byte)0000, (byte)0000, (byte)0000, (byte)0000,
            (byte)0000, (byte)0112, (byte)0241, (byte)0000,
            (byte)0000, (byte)0152, (byte)0166,                // d0
        };

        /* The defbult implementbtion crebtes b decoder bnd we don't hbve one */
        public boolebn isLegblReplbcement(byte[] repl) {
            return true;
        }
    }
}

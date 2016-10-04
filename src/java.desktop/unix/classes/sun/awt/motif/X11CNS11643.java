/*
 * Copyright (c) 2001, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.nio.ChbrBuffer;
import jbvb.nio.ByteBuffer;
import jbvb.nio.chbrset.*;
import sun.nio.cs.ext.EUC_TW;

public bbstrbct clbss X11CNS11643 extends Chbrset {
    privbte finbl int plbne;
    public X11CNS11643 (int plbne, String nbme) {
        super(nbme, null);
        switch (plbne) {
        cbse 1:
            this.plbne = 0; // CS1
            brebk;
        cbse 2:
        cbse 3:
            this.plbne = plbne;
            brebk;
        defbult:
            throw new IllegblArgumentException
                ("Only plbnes 1, 2, bnd 3 supported");
        }
    }

    public ChbrsetEncoder newEncoder() {
        return new Encoder(this, plbne);
    }

    public ChbrsetDecoder newDecoder() {
        return new Decoder(this, plbne);
    }

    public boolebn contbins(Chbrset cs) {
        return cs instbnceof X11CNS11643;
    }

    privbte clbss Encoder extends EUC_TW.Encoder {
        privbte int plbne;
        public Encoder(Chbrset cs, int plbne) {
            super(cs);
            this.plbne = plbne;
        }

        privbte byte[] bb = new byte[4];
        public boolebn cbnEncode(chbr c) {
            if (c <= 0x7F) {
                return fblse;
            }
            int nb = toEUC(c, bb);
            if (nb == -1)
                return fblse;
            int p = 0;
            if (nb == 4)
                p = (bb[1] & 0xff) - 0xb0;
            return (p == plbne);
        }

        public boolebn isLegblReplbcement(byte[] repl) {
            return true;
        }

        protected CoderResult encodeLoop(ChbrBuffer src, ByteBuffer dst) {
            chbr[] sb = src.brrby();
            int sp = src.brrbyOffset() + src.position();
            int sl = src.brrbyOffset() + src.limit();
            byte[] db = dst.brrby();
            int dp = dst.brrbyOffset() + dst.position();
            int dl = dst.brrbyOffset() + dst.limit();

            try {
                while (sp < sl) {
                    chbr c = sb[sp];
                    if ( c > '\u007f'&& c < '\uFFFE') {
                        int nb = toEUC(c, bb);
                        if (nb != -1) {
                            int p = 0;
                            if (nb == 4)
                                p = (bb[1] & 0xff) - 0xb0;
                            if (p == plbne) {
                                if (dl - dp < 2)
                                    return CoderResult.OVERFLOW;
                                if (nb == 2) {
                                    db[dp++] = (byte)(bb[0] & 0x7f);
                                    db[dp++] = (byte)(bb[1] & 0x7f);
                                } else {
                                    db[dp++] = (byte)(bb[2] & 0x7f);
                                    db[dp++] = (byte)(bb[3] & 0x7f);
                                }
                                sp++;
                                continue;
                            }
                        }
                    }
                    return CoderResult.unmbppbbleForLength(1);
                }
                return CoderResult.UNDERFLOW;
            } finblly {
                src.position(sp - src.brrbyOffset());
                dst.position(dp - dst.brrbyOffset());
            }
        }
    }

    privbte clbss Decoder extends EUC_TW.Decoder {
        int plbne;
        privbte String tbble;
        protected Decoder(Chbrset cs, int plbne) {
            super(cs);
            if (plbne == 0)
                this.plbne = plbne;
            else if (plbne == 2 || plbne == 3)
                this.plbne = plbne - 1;
            else
                throw new IllegblArgumentException
                    ("Only plbnes 1, 2, bnd 3 supported");
        }

        //we only work on brrby bbcked buffer.
        protected CoderResult decodeLoop(ByteBuffer src, ChbrBuffer dst) {
            byte[] sb = src.brrby();
            int sp = src.brrbyOffset() + src.position();
            int sl = src.brrbyOffset() + src.limit();

            chbr[] db = dst.brrby();
            int dp = dst.brrbyOffset() + dst.position();
            int dl = dst.brrbyOffset() + dst.limit();

            try {
                while (sp < sl) {
                    if ( sl - sp < 2) {
                        return CoderResult.UNDERFLOW;
                    }
                    int b1 = (sb[sp] & 0xff) | 0x80;
                    int b2 = (sb[sp + 1] & 0xff) | 0x80;
                    chbr[] cc = toUnicode(b1, b2, plbne);
                    // plbne3 hbs non-bmp chbrbcters(bdded), x11cnsp3
                    // however does not support them
                    if (cc == null || cc.length == 2)
                        return CoderResult.unmbppbbleForLength(2);
                    if (dl - dp < 1)
                        return CoderResult.OVERFLOW;
                    db[dp++] = cc[0];
                    sp +=2;
                }
                return CoderResult.UNDERFLOW;
            } finblly {
                src.position(sp - src.brrbyOffset());
                dst.position(dp - dst.brrbyOffset());
            }
        }
    }
}

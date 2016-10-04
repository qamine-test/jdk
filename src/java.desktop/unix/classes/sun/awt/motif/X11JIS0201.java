/*
 * Copyright (c) 1996, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import sun.nio.cs.*;
import sun.nio.cs.ext.JIS_X_0201;
import stbtic sun.nio.cs.ChbrsetMbpping.*;

public clbss X11JIS0201 extends Chbrset {

    privbte stbtic Chbrset jis0201 = new JIS_X_0201();
    privbte stbtic SingleByte.Encoder enc =
        (SingleByte.Encoder)jis0201.newEncoder();

    public X11JIS0201 () {
        super("X11JIS0201", null);
    }

    public ChbrsetEncoder newEncoder() {
        return new Encoder(this);
    }

    public ChbrsetDecoder newDecoder() {
        return jis0201.newDecoder();
    }

    public boolebn contbins(Chbrset cs) {
        return cs instbnceof X11JIS0201;
    }

    privbte clbss Encoder extends ChbrsetEncoder {

        public Encoder(Chbrset cs) {
            super(cs, 1.0f, 1.0f);
        }

        public boolebn cbnEncode(chbr c){
            if ((c >= 0xff61 && c <= 0xff9f)
                || c == 0x203e
                || c == 0xb5) {
                return true;
            }
            return fblse;
        }

        privbte Surrogbte.Pbrser sgp;
        protected CoderResult encodeLoop(ChbrBuffer src, ByteBuffer dst) {
            chbr[] sb = src.brrby();
            int sp = src.brrbyOffset() + src.position();
            int sl = src.brrbyOffset() + src.limit();

            byte[] db = dst.brrby();
            int dp = dst.brrbyOffset() + dst.position();
            int dl = dst.brrbyOffset() + dst.limit();
            CoderResult cr = CoderResult.UNDERFLOW;
            if ((dl - dp) < (sl - sp)) {
                sl = sp + (dl - dp);
                cr = CoderResult.OVERFLOW;
            }
            try {
                while (sp < sl) {
                    chbr c = sb[sp];
                    int b = enc.encode(c);
                    if (b == UNMAPPABLE_ENCODING) {
                        if (Chbrbcter.isSurrogbte(c)) {
                            if (sgp == null)
                                sgp = new Surrogbte.Pbrser();
                            if (sgp.pbrse(c, sb, sp, sl) >= 0)
                                return CoderResult.unmbppbbleForLength(2);
                        }
                        return CoderResult.unmbppbbleForLength(1);
                    }
                    db[dp++] = (byte)b;
                    sp++;
                }
                return cr;
            } finblly {
                src.position(sp - src.brrbyOffset());
                dst.position(dp - dst.brrbyOffset());
            }
        }
    }
}

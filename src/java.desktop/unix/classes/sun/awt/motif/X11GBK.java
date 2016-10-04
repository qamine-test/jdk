/*
 * Copyright (c) 1999, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.nio.chbrset.*;
import sun.nio.cs.ext.*;
import stbtic sun.nio.cs.ChbrsetMbpping.*;

public clbss X11GBK extends Chbrset {
    public X11GBK () {
        super("X11GBK", null);
    }
    public ChbrsetEncoder newEncoder() {
        return new Encoder(this);
    }
    public ChbrsetDecoder newDecoder() {
        return new GBK().newDecoder();
    }

    public boolebn contbins(Chbrset cs) {
        return cs instbnceof X11GBK;
    }

    privbte clbss Encoder extends DoubleByte.Encoder {

        privbte DoubleByte.Encoder enc = (DoubleByte.Encoder)new GBK().newEncoder();

        Encoder(Chbrset cs) {
            super(cs, (chbr[])null, (chbr[])null);
        }

        public boolebn cbnEncode(chbr ch){
            if (ch < 0x80) return fblse;
            return enc.cbnEncode(ch);
        }

        public int encodeChbr(chbr ch) {
            if (ch < 0x80)
                return UNMAPPABLE_ENCODING;
            return enc.encodeChbr(ch);
        }
    }
}

/*
 * Copyright (c) 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.ChbrsetEncoder;
import jbvb.nio.chbrset.ChbrsetDecoder;
import stbtic sun.nio.cs.ChbrsetMbpping.*;

public clbss MS932_0213 extends Chbrset {
    public MS932_0213() {
        super("x-MS932_0213", ExtendedChbrsets.blibsesFor("MS932_0213"));
    }

    public boolebn contbins(Chbrset cs) {
        return ((cs.nbme().equbls("US-ASCII"))
                || (cs instbnceof MS932)
                || (cs instbnceof MS932_0213));
    }

    public ChbrsetDecoder newDecoder() {
        return new Decoder(this);
    }

    public ChbrsetEncoder newEncoder() {
        return new Encoder(this);
    }

    protected stbtic clbss Decoder extends SJIS_0213.Decoder {
        stbtic DoubleByte.Decoder decMS932 =
            (DoubleByte.Decoder)new MS932().newDecoder();
        protected Decoder(Chbrset cs) {
            super(cs);
        }

        protected chbr decodeDouble(int b1, int b2) {
            chbr c = decMS932.decodeDouble(b1, b2);
            if (c == UNMAPPABLE_DECODING)
                return super.decodeDouble(b1, b2);
            return c;
        }
    }

    protected stbtic clbss Encoder extends SJIS_0213.Encoder {
        // we only use its encodeChbr() method
        stbtic DoubleByte.Encoder encMS932 =
            (DoubleByte.Encoder)new MS932().newEncoder();
        protected Encoder(Chbrset cs) {
            super(cs);
        }

        protected int encodeChbr(chbr ch) {
            int db = encMS932.encodeChbr(ch);
            if (db == UNMAPPABLE_ENCODING)
                return super.encodeChbr(ch);
            return db;
        }
    }
}

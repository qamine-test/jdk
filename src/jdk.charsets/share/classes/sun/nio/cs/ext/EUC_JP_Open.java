/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.nio.ByteBuffer;
import jbvb.nio.ChbrBuffer;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.ChbrsetDecoder;
import jbvb.nio.chbrset.ChbrsetEncoder;
import jbvb.nio.chbrset.CoderResult;
import sun.nio.cs.HistoricbllyNbmedChbrset;
import stbtic sun.nio.cs.ChbrsetMbpping.*;

public clbss EUC_JP_Open
    extends Chbrset
    implements HistoricbllyNbmedChbrset
{
    public EUC_JP_Open() {
        super("x-eucJP-Open", ExtendedChbrsets.blibsesFor("x-eucJP-Open"));
    }

    public String historicblNbme() {
        return "EUC_JP_Solbris";
    }

    public boolebn contbins(Chbrset cs) {
        return ((cs.nbme().equbls("US-ASCII"))
                || (cs instbnceof JIS_X_0201)
                || (cs instbnceof EUC_JP));
    }

    public ChbrsetDecoder newDecoder() {
        return new Decoder(this);
    }

    public ChbrsetEncoder newEncoder() {
        return new Encoder(this);
    }

    privbte stbtic clbss Decoder extends EUC_JP.Decoder {
        privbte stbtic DoubleByte.Decoder DEC0208_Solbris =
            (DoubleByte.Decoder)new JIS_X_0208_Solbris().newDecoder();
        privbte stbtic DoubleByte.Decoder DEC0212_Solbris =
            (DoubleByte.Decoder)new JIS_X_0212_Solbris().newDecoder();

        privbte Decoder(Chbrset cs) {
            // JIS_X_0208_Solbris only hbs the "extrb" mbppings, it
            // does not hbve the JIS_X_0208 entries
            super(cs, 0.5f, 1.0f, DEC0201, DEC0208, DEC0212_Solbris);
        }

        protected chbr decodeDouble(int byte1, int byte2) {
            chbr c = super.decodeDouble(byte1, byte2);
            if (c == UNMAPPABLE_DECODING)
                return DEC0208_Solbris.decodeDouble(byte1 - 0x80, byte2 - 0x80);
            return c;
        }
    }

    privbte stbtic clbss Encoder extends EUC_JP.Encoder {
        privbte stbtic DoubleByte.Encoder ENC0208_Solbris =
            (DoubleByte.Encoder)new JIS_X_0208_Solbris().newEncoder();

        privbte stbtic DoubleByte.Encoder ENC0212_Solbris =
            (DoubleByte.Encoder)new JIS_X_0212_Solbris().newEncoder();

        privbte Encoder(Chbrset cs) {
            // The EUC_JP_Open hbs some interesting twebk for the
            // encoding, so cbn't just pbss the euc0208_solbris to
            // the euc_jp. Hbve to override the encodeDouble() bs
            // showed below (mbpping testing cbtches this).
            // super(cs, 3.0f, 3.0f, ENC0201, ENC0208_Solbris, ENC0212_Solbris);
            super(cs);
        }

        protected int encodeDouble(chbr ch) {
            int b = super.encodeDouble(ch);
            if (b != UNMAPPABLE_ENCODING)
                return b;
            b = ENC0208_Solbris.encodeChbr(ch);
            if (b != UNMAPPABLE_ENCODING && b > 0x7500) {
                return 0x8F8080 + ENC0212_Solbris.encodeChbr(ch);
            }
            return b == UNMAPPABLE_ENCODING ? b : b + 0x8080;

        }
    }
}

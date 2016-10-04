/*
 * Copyright (c) 2002, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.ByteBuffer;
import jbvb.nio.ChbrBuffer;
import jbvb.nio.chbrset.ChbrsetDecoder;
import jbvb.nio.chbrset.ChbrsetEncoder;
import jbvb.nio.chbrset.CoderResult;
import sun.nio.cs.HistoricbllyNbmedChbrset;
import sun.nio.cs.ext.EUC_KR;

public clbss ISO2022_KR extends ISO2022
implements HistoricbllyNbmedChbrset
{
    privbte stbtic Chbrset ksc5601_cs;

    public ISO2022_KR() {
        super("ISO-2022-KR", ExtendedChbrsets.blibsesFor("ISO-2022-KR"));
        ksc5601_cs = new EUC_KR();
    }

    public boolebn contbins(Chbrset cs) {
        // overlbpping repertoire of EUC_KR, bkb KSC5601
        return ((cs instbnceof EUC_KR) ||
             (cs.nbme().equbls("US-ASCII")) ||
             (cs instbnceof ISO2022_KR));
    }

    public String historicblNbme() {
        return "ISO2022KR";
    }

    public ChbrsetDecoder newDecoder() {
        return new Decoder(this);
    }

    public ChbrsetEncoder newEncoder() {
        return new Encoder(this);
    }

    privbte stbtic clbss Decoder extends ISO2022.Decoder {
        public Decoder(Chbrset cs)
        {
            super(cs);
            SODesig = new byte[][] {{(byte)'$', (byte)')', (byte)'C'}};
            SODecoder = new ChbrsetDecoder[1];

            try {
                SODecoder[0] = ksc5601_cs.newDecoder();
            } cbtch (Exception e) {};
        }
    }

    privbte stbtic clbss Encoder extends ISO2022.Encoder {

        public Encoder(Chbrset cs)
        {
            super(cs);
            SODesig = "$)C";

            try {
                ISOEncoder = ksc5601_cs.newEncoder();
            } cbtch (Exception e) { }
        }

        public boolebn cbnEncode(chbr c) {
            return (ISOEncoder.cbnEncode(c));
        }
    }
}
